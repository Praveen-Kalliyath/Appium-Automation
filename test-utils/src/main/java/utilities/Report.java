package utilities;

import java.io.IOException;
import java.net.InetAddress;
import java.io.File;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import libraries.Data;

public class Report implements Data {
	/*
	 * @author Praveen Kalliyath
	 * 
	 * @version 1.0
	 * 
	 * @since June 05 2020
	 */
	private static ExtentReports extentReports;
	private static ExtentTest extentTest;
	private static ExtentTest childNode;
	private static boolean failurePresent = false;

	/*
	 * Method To Create Extent Report
	 */
	public static void createReport() {
		Log.info("Creating Execution Report");
		Log.info("Creating Report In Location: " + REPORT_LOCATION);
		File dir = new File(REPORT_LOCATION);
		if (!dir.exists())
			dir.mkdir();
		ExtentHtmlReporter aventReporter = new ExtentHtmlReporter(REPORT_LOCATION);
		extentReports = new ExtentReports();
		extentReports.attachReporter(aventReporter);

		try {
			extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
			extentReports.setSystemInfo("Host Name", InetAddress.getLocalHost().getHostName());
			extentReports.setSystemInfo("IP Address", InetAddress.getLocalHost().getHostAddress());
			extentReports.setSystemInfo("OS Version", System.getProperty("os.name"));
			extentReports.setSystemInfo("Java Version", System.getProperty("user.java"));
		} catch (Exception e) {
			Log.error("Failed set system information to the report. Error Message: " + e.getMessage());
		}
	}

	/*
	 * Method To Create Extent Test
	 * 
	 * @return extentTest
	 */
	public static ExtentTest createTest(String testName) {
		Log.info("Created Report Section: '" + testName + "'");
		extentTest = extentReports.createTest(testName);
		return extentTest;
	}

	/*
	 * Method To Create Extent Test
	 * 
	 * @return childNode
	 */
	public static ExtentTest createChildNode(String nodeName) {
		Log.info("Created Test Section Node: '" + nodeName + "'");
		childNode = extentTest.createNode(nodeName);
		return childNode;
	}

	/*
	 * Method To Flush & Save Extent Report
	 */
	public static void flushReport() {
		if (extentReports != null) {
			extentReports.flush();
			Log.info("Flushed Report.");
			Log.info("Saved Report In Location: " + REPORT_LOCATION);
		}
	}

	/*
	 * Method To Log Report To Parent Node
	 * 
	 * @param status
	 * 
	 * @param message
	 */
	public static void logInParentNode(Status status, String message) {
		extentTest.log(status, message).assignCategory(status.toString());
		childNode = null;
	}

	/*
	 * Method To Log Link Details
	 * 
	 * @param linkName
	 * 
	 * @param path
	 */
	public static void link(String linkName, String link) {
		Log.info(linkName + " : " + link);
		if (childNode != null) {
			childNode.info("Please refer the link provided: <a href=" + link + ">" + linkName + "</a>");
		} else {
			extentTest.info("Please refer the link provided: <a href=" + link + ">" + linkName + "</a>");
		}
	}

	/*
	 * Method To Log Info
	 * 
	 * @param message
	 */
	public static void info(String message) {
		Log.info(message);
		if (childNode != null) {
			childNode.info(message);
		} else {
			extentTest.info(message);
		}
	}

	/*
	 * Method To Log Pass
	 * 
	 * @param message
	 */
	public static void pass(String message) {
		Log.info(message);
		if (childNode != null) {
			childNode.pass(message);
		} else {
			extentTest.pass(message);
		}
	}

	/*
	 * Method To Log Pass With Screenshot
	 * 
	 * @param message
	 */
	public static void passWithScreenshot(String message) {
		Log.info(message);
		try {
			if (childNode != null) {
				childNode.pass(message, MediaEntityBuilder
						.createScreenCaptureFromBase64String(DriverUtil.getBase64Screenshot()).build());
			} else {
				extentTest.pass(message, MediaEntityBuilder
						.createScreenCaptureFromBase64String(DriverUtil.getBase64Screenshot()).build());
			}
		} catch (IOException e) {
			Log.fatal(e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * Method To Log Fail
	 * 
	 * @param message
	 */
	public static void fail(String message) {
		Log.error(message);
		failurePresent = true;
		try {
			if (childNode != null) {
				childNode.fail(message, MediaEntityBuilder
						.createScreenCaptureFromBase64String(DriverUtil.getBase64Screenshot()).build());
			} else {
				extentTest.fail(message, MediaEntityBuilder
						.createScreenCaptureFromBase64String(DriverUtil.getBase64Screenshot()).build());
			}
		} catch (IOException e) {
			Log.fatal(e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * Method To Log Warning
	 * 
	 * @param message
	 */
	public static void warn(String message) {
		Log.warn(message);
		try {
			if (childNode != null) {
				childNode.warning(message, MediaEntityBuilder
						.createScreenCaptureFromBase64String(DriverUtil.getBase64Screenshot()).build());
			} else {
				extentTest.warning(message, MediaEntityBuilder
						.createScreenCaptureFromBase64String(DriverUtil.getBase64Screenshot()).build());
			}
		} catch (IOException e) {
			Log.fatal(e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * Method To Log Error
	 * 
	 * @param message
	 */
	public static void error(String message) {
		Log.error(message);
		failurePresent = true;
		try {
			if (childNode != null) {
				childNode
						.error(message, MediaEntityBuilder
								.createScreenCaptureFromBase64String(DriverUtil.getBase64Screenshot()).build())
						.assignCategory("Error");
			} else {
				extentTest
						.error(message, MediaEntityBuilder
								.createScreenCaptureFromBase64String(DriverUtil.getBase64Screenshot()).build())
						.assignCategory("Error");
			}
		} catch (IOException e) {
			Log.fatal(e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * Method To Log Fatal
	 * 
	 * @param message
	 */
	public static void fatal(String message) {
		Log.fatal(message);
		failurePresent = true;
		try {
			if (childNode != null) {
				childNode.fatal(message, MediaEntityBuilder
						.createScreenCaptureFromBase64String(DriverUtil.getBase64Screenshot()).build());
			} else {
				extentTest.fatal(message, MediaEntityBuilder
						.createScreenCaptureFromBase64String(DriverUtil.getBase64Screenshot()).build());
			}
		} catch (IOException e) {
			Log.fatal(e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * Method To Log Exception
	 * 
	 * @param excepion
	 */
	public static void exception(Exception exception) {
		failurePresent = true;
		Log.fatal("Exception Occured. " + exception.getMessage());
		try {
			if (childNode != null) {
				childNode.fail(exception, MediaEntityBuilder
						.createScreenCaptureFromBase64String(DriverUtil.getBase64Screenshot()).build());
			} else {
				extentTest.fail(exception, MediaEntityBuilder
						.createScreenCaptureFromBase64String(DriverUtil.getBase64Screenshot()).build());
			}
		} catch (IOException e) {
			Log.fatal(e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * Method To Log Skip
	 * 
	 * @param message
	 */
	public static void skip(String message) {
		Log.info("Skipped: " + message);
		if (childNode != null) {
			childNode.skip(message);
		} else {
			extentTest.skip(message);
		}
	}

	/*
	 * Method To Log Debug
	 * 
	 * @param message
	 */
	public static void debug(String message) {
		Log.debug(message);
		if (childNode != null) {
			childNode.debug(message);
		} else {
			extentTest.debug(message);
		}
	}

	/*
	 * Method To Log Using Status
	 * 
	 * @param message
	 */
	public static void log(Status status, String message) {
		Log.info(status + " : " + message);
		if (childNode != null) {
			childNode.log(status, message);
		} else {
			extentTest.log(status, message);
		}
	}

	/*
	 * Method To Log Using Status
	 * 
	 * @param message
	 */
	public static void logWithScreenShot(Status status, String message) {
		Log.info(status + " : " + message);
		try {
			if (childNode != null) {
				childNode.log(status, message, MediaEntityBuilder
						.createScreenCaptureFromBase64String(DriverUtil.getBase64Screenshot()).build());
			} else {
				extentTest.log(status, message, MediaEntityBuilder
						.createScreenCaptureFromBase64String(DriverUtil.getBase64Screenshot()).build());
			}
		} catch (IOException e) {
			Log.fatal(e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * Method To Log Label
	 * 
	 * @param message
	 */
	public static void label(String message) {
		Log.info("LABEL: " + message);
		Markup label = MarkupHelper.createLabel(message, ExtentColor.BLUE);
		if (childNode != null) {
			childNode.info(label);
		} else {
			extentTest.info(label);
		}
	}

	/*
	 * Method To Get Status
	 * 
	 * @return status
	 */
	public static String status() {
		if (childNode != null) {
			return childNode.getStatus().toString();
		} else {
			return extentTest.getStatus().toString();
		}
	}

	public static boolean failurePresent() {
		return failurePresent;
	}

}
