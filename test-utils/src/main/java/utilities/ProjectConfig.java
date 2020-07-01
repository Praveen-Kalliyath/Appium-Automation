package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;
import libraries.Data;

public class ProjectConfig implements Data {
	/**
	 * @author Praveen Kalliyath
	 * 
	 * @version 1.0
	 * 
	 * @since June 30 2020
	 */

	private static Properties properties;
	private String appFilePath;

	// /**
	// * Constructor
	// */
	// public ProjectConfig() {
	// loadConfig();
	// }

	/**
	 * Loading Properties Files for Framework & Application
	 * 
	 * @exception FileNotFoundException
	 * 
	 * @exception IOException
	 */
	public void loadConfig() {
		try {
			// FRAMEWORK CONFIG FILE
			Log.info("Loading Framework Configuration");
			FileInputStream frameworkFile = new FileInputStream(
					TEST_UTILS_RESOURCE_FOLDER_LOCATION + "framework.properties");

			properties = new Properties();
			properties.load(frameworkFile);
			Log.info("Loaded Framework Properties File");

			Log.info("Configuration File Data Map: " + properties);

			// APPLICATION ENVIRONMENT CONFIG FILE
			getAppConfigFilePath(getProperty("APP_NAME"));
			Log.info(getProperty("APP_NAME") + " Configuration File Location: " + getAppFilePath());
			FileInputStream appFile = new FileInputStream(getAppFilePath());
			properties.load(appFile);
			Log.info("Loaded Application Properties File");

			if (!properties.getProperty("LOCATOR_FILE").isEmpty()) {
				FileInputStream xpathFile = new FileInputStream(
						getAppResouceFolderPath(getProperty("APP_NAME")) + properties.getProperty("LOCATOR_FILE"));
				properties.load(xpathFile);
				Log.info("Loaded Locator Repository Properties File");
			}

			Log.info("Configuration File Data Map: " + properties);
		} catch (FileNotFoundException e) {
			Log.error("Configuration file not found. Error Message:" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.error("Failed to load configuration file. Error Message:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Loading Properties Files
	 * 
	 * @param filePath
	 * 
	 * @exception FileNotFoundException
	 * 
	 * @exception IOException
	 */
	public void loadConfig(String filePath) {
		try {
			// LOADING CONFIG FILE
			Log.info("Loading Configuration File Location: " + filePath);
			FileInputStream fin = new FileInputStream(filePath);
			properties = new Properties();
			properties.load(fin);
			Log.info("Loaded Configuration Properties File");
			Log.info("Configuration File Data Map: " + properties);
		} catch (FileNotFoundException e) {
			Log.error("Configuration file not found. Error Message:" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.error("Failed to load configuration file. Error Message:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Method to get application properties file path
	 * 
	 * @return appName
	 */
	public String getAppConfigFilePath(String appName) {
		Log.info("Getting Path For '" + appName + "' Configuration File");
		switch (appName.toLowerCase()) {
		case "ebay":
			setAppFilePath(EBAY_CONFIG_FILE_LOCATION);
			break;
		default:
			Report.warn(appName + " is not a valid choose. Please Choose a valid app name.");
			break;
		}

		return getAppFilePath();
	}

	/**
	 * Method to get application properties file path
	 * 
	 * @return appName
	 */
	public String getAppResouceFolderPath(String appName) {
		Log.info("Getting Resource Folder Path For '" + appName);
		String resourcePath = "";
		switch (appName.toLowerCase()) {
		case "ebay":
			resourcePath = EBAY_RESOURCE_FOLDER_LOCATION;
			break;
		default:
			Report.warn(appName + " is not a valid choose. Please Choose a valid app name.");
			break;
		}
		return resourcePath;
	}

	/**
	 * Method to write 'Key' & 'Value' to Properties Files
	 * 
	 * @param key
	 * 
	 * @param value
	 * 
	 * @exception ConfigurationException
	 * 
	 * @exception FileNotFoundException
	 * 
	 * @exception IOException
	 */
	public void writeToConfigFile(String key, String value) {
		try {
			File file = new File(EBAY_CONFIG_FILE_LOCATION);
			PropertiesConfiguration configuration = new PropertiesConfiguration();
			PropertiesConfigurationLayout configurationLayout = new PropertiesConfigurationLayout(configuration);
			configurationLayout.load(new InputStreamReader(new FileInputStream(file)));
			configuration.setProperty(key, value);
			configurationLayout.save(new FileWriter(file));
			Log.info("Saved data to properties file.");
			Log.info("Key '[" + key + "]' : Value '[" + value + "]'");
		} catch (ConfigurationException e) {
			Log.error("Exception Occured. Error Message:" + e.getMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			Log.error("Configuration file not found. Error Message:" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.error("Failed to write data to configuration file. Error Message:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Method to get property value
	 * 
	 * @param key
	 * 
	 * @return Property Value
	 */
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	/**
	 * Method to return application property file location
	 * 
	 * @return appFilePath
	 */
	public String getAppFilePath() {
		Log.info("Getting App File Path Value:" + appFilePath);
		return appFilePath;
	}

	/**
	 * Method to get application property file location
	 * 
	 * @param appFilePath
	 */
	public void setAppFilePath(String appFilePath) {
		Log.info("Setting App File Path Value to:" + appFilePath);
		this.appFilePath = appFilePath;
	}
}
