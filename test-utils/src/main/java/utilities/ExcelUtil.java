package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import libraries.Data;

public class ExcelUtil implements Data {
	/**
	 * @author Praveen Kalliyath
	 * 
	 * @version 1.0
	 * 
	 * @since June 30 2020
	 */

	private Sheet excelWSheet;
	private Workbook excelWBook;
	private Cell excelCell;
	private Row excelRow;
	private FileInputStream fin;
	private String excelPath;
	private HashMap<String, String> rowHashMap;
	private DataFormatter dataFormatter;

	/**
	 * Method to set/load excel from file path
	 * 
	 * @param sheetName
	 * 
	 * @exception FileNotFoundException
	 * 
	 * @exception IOException
	 */
	public void setExcelFile(String sheetName) {
		try {
			getAppDataFilePath(ProjectConfig.getProperty("APP_NAME"));
			fin = new FileInputStream(new File(getExcelPath()));
			excelWBook = new XSSFWorkbook(fin);
			excelWSheet = excelWBook.getSheet(sheetName);
			Log.info("Opend excel workbook with sheetname: " + sheetName);
		} catch (FileNotFoundException e) {
			Report.error("Excel File Not Found. Exception Message: " + e.getMessage());
			Report.exception(e);
			e.printStackTrace();
		} catch (IOException e) {
			Report.error("Unable to load excel. Exception Message: " + e.getMessage());
			Report.exception(e);
			e.printStackTrace();
		}

	}

	/**
	 * Method to retrieve excel data in a hash map
	 * 
	 * @param rowNum
	 * 
	 * @param colNum
	 */
	public void getRowHashMapForSpecificColumn(int rowNum, int colNum) {
		dataFormatter = new DataFormatter();
		int curRow = rowNum;
		Iterator<Row> iterator = excelWSheet.iterator();
		rowHashMap = new HashMap<>();
		while (iterator.hasNext()) {
			iterator.next();
			rowHashMap.put(excelWSheet.getRow(curRow).getCell(0).getStringCellValue(),
					dataFormatter.formatCellValue(excelWSheet.getRow(curRow).getCell(colNum)));
			curRow++;
		}
		Log.info("Excel Data Map: " + rowHashMap);
	}

	/**
	 * Method to retrieve excel data in a hash map
	 * 
	 * @param rowNum
	 * 
	 * @param colNum
	 */
	public void getRowHashMapForSpecificColumn(int rowNum, String recordName) {
		dataFormatter = new DataFormatter();
		int curRow = rowNum;
		Iterator<Row> iterator = excelWSheet.iterator();
		rowHashMap = new HashMap<>();
		while (iterator.hasNext()) {
			iterator.next();
			rowHashMap.put(excelWSheet.getRow(curRow).getCell(0).getStringCellValue(), dataFormatter
					.formatCellValue(excelWSheet.getRow(curRow).getCell(getColumnIndexForRecord(rowNum, recordName))));
			curRow++;
		}
		Log.info("Excel Data Map: " + rowHashMap);
	}

	/**
	 * Method to retrieve excel column index using row number and cell value
	 * 
	 * @param rowNum
	 * 
	 * @param colNum
	 * 
	 * @return Cell Value
	 */
	public int getColumnIndexForRecord(int rowNum, String recordName) {
		int colIndex;
		for (colIndex = 0; colIndex < getColumnUsed(rowNum); colIndex++) {
			if (getCellData(rowNum, colIndex).equalsIgnoreCase(recordName)) {
				break;
			}
		}
		return colIndex;
	}

	/**
	 * Method to retrieve excel cell data using row and column number
	 * 
	 * @param rowNum
	 * 
	 * @param colNum
	 * 
	 * @return Cell Value
	 */
	public String getCellData(int rowNum, int colNum) {
		return excelWSheet.getRow(rowNum).getCell(colNum).getStringCellValue();
	}

	/**
	 * Get Last Cell Number From Sheet
	 * 
	 * @return excelWSheet.getLastCellNum()
	 */
	public int getColumnUsed(int rowNum) {
		return excelWSheet.getRow(rowNum).getLastCellNum();
	}

	/**
	 * Get Last Row Number From Sheet
	 * 
	 * @return excelWSheet.getLastRowNum()
	 */
	public int getRowUsed() {
		return excelWSheet.getLastRowNum();
	}

	/**
	 * Get User Row Count From Sheet
	 * 
	 * @return rowCount
	 */
	public int getRowCount() {
		Iterator<Row> iterator = excelWSheet.iterator();
		int rowCount = 0;
		while (iterator.hasNext()) {
			iterator.next();
			rowCount++;
		}

		return rowCount;
	}

	/**
	 * Method to close excel workbook
	 * 
	 * @param key
	 * 
	 * @return value
	 */
	public String getValueForKey(String key) {
		Log.info("Getting row value '" + rowHashMap.get(key) + "' for Key '" + key + "'");
		return rowHashMap.get(key);
	}

	/**
	 * Method to close excel workbook
	 * 
	 * @return hashMap
	 */
	public HashMap<String, String> getRowHashMap() {
		return rowHashMap;
	}

	/**
	 * Method to set value to excel sheet
	 * 
	 * @param rowNum
	 * 
	 * @param colNum
	 * 
	 * @param value
	 * 
	 * @exception FileNotFoundException
	 * 
	 * @exception IOException
	 */
	public void setCellData(int rowNum, int colNum, String value) {
		Report.info("Setting '" + value + "' to excel at row [" + rowNum + "] and column [" + colNum + "]");
		if (getRowUsed() < rowNum) {
			excelRow = excelWSheet.createRow(rowNum);
		}
		excelRow = excelWSheet.getRow(rowNum);
		excelCell = excelRow.getCell(colNum);

		if (excelCell == null) {
			excelCell = excelRow.createCell(colNum);
			excelCell.setCellValue(value);
		} else {
			excelCell.setCellValue(value);
		}

		// WRITING DATA TO EXCEL
		try {
			getAppDataFilePath(ProjectConfig.getProperty("APP_NAME"));
			FileOutputStream fos = new FileOutputStream(getExcelPath());
			excelWBook.write(fos);
			fos.flush();
			fos.close();
			Report.info("Saved '" + value + "' to excel at row [" + rowNum + "] and column [" + colNum + "]");
		} catch (FileNotFoundException e) {
			Report.error("Excel file not found. Error Message: " + e.getMessage());
			Report.exception(e);
			e.printStackTrace();
		} catch (IOException e) {
			Report.error("Failed to write to exel file. Error Message: " + e.getMessage());
			Report.exception(e);
			e.printStackTrace();
		}
	}

	/**
	 * Method to close excel workbook
	 * 
	 * @exception IOException
	 */
	public void closeWorkbook() {
		try {
			fin.close();
			Log.info("Closed Workbook");
		} catch (IOException e) {
			Report.error("Failed to close excel workbook. Error Message: " + e.getMessage());
			Report.exception(e);
			e.printStackTrace();
		}
	}

	/**
	 * Method to set excel workbook location using application name
	 * 
	 * @param appName
	 */
	public void getAppDataFilePath(String appName) {
		Log.info("Getting " + appName + " Excel Data File");
		switch (appName.toLowerCase()) {
		case "ebay":
			setExcelPath(EBAY_RESOURCE_FOLDER_LOCATION + ProjectConfig.getProperty("EBAY_EXCEL_DATA_PATH"));
			break;
		default:
			Report.warn(appName + " is not a valid choose. Please Choose a valid app name.");
			break;

		}
	}

	/**
	 * Method to retrieve excel workbook location
	 * 
	 * @return excelPath
	 */
	public String getExcelPath() {
		Log.info("Getting Excel File Path");
		return excelPath;
	}

	/**
	 * Method to set excel workbook location
	 * 
	 * @param excelPath
	 */
	public void setExcelPath(String excelPath) {
		Log.info("Setting Excel File Path Value To: " + excelPath);
		this.excelPath = excelPath;
	}

	public void loadExcelData(String sheetName, String recordName) {
		setExcelFile(sheetName);
		getRowHashMapForSpecificColumn(0, recordName);
		closeWorkbook();
	}

	/**
	 * Main method to test the utility
	 */
	public static void main(String args[]) {
		ExcelUtil excelUtil = new ExcelUtil();
		excelUtil.setExcelFile("EBAY");
		excelUtil.getRowHashMapForSpecificColumn(0, "Record-001");
		excelUtil.closeWorkbook();
		System.out.println("User Name: " + excelUtil.getValueForKey("User Name"));
	}
}
