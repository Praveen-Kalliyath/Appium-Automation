package utilities;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.Status;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import libraries.Data;

public class BaseUtil implements Data {
	/*
	 * @author Praveen Kalliyath
	 * 
	 * @version 1.0
	 * 
	 * @since June 05 2020
	 */
	private ProjectConfig config;
	private DesiredCapabilities capabilities;
	private String appFilePath;

	private static AndroidDriver<MobileElement> driver;

	/*
	 * TestNG Before Suite Annotation
	 */
	@BeforeSuite
	public void initialize() {
		config = new ProjectConfig();
		config.loadConfig();
		Report.createReport();
	}

	/*
	 * TestNG Before Method Annotation
	 * 
	 * @param: Methods
	 */
	@BeforeMethod()
	public void setReportSection(Method method) {
		Log.info("##########################################################################################");
		Log.info("# Running Method: " + method.getName());
		Log.info("##########################################################################################");
		Report.createTest(method.getName());
	}

	/*
	 * TestNG After Method Annotation
	 */
	@AfterMethod
	public void afterTestSteps(ITestResult testResult) {
		Report.createChildNode(new Object() {
		}.getClass().getEnclosingMethod().getName());
		if (CommonUtil.getSoftAssert() != null)
			CommonUtil.assertAll();
		if (testResult.getStatus() == ITestResult.FAILURE) {
			Report.fail("Execution has failures. Please fix them");
		}
	}

	/*
	 * TestNG After Suite Annotation
	 */
	@AfterSuite
	public void tearDown() {
		if (driver != null)
			Report.logWithScreenShot(Status.INFO, "Closing App Screenshot");
		else
			Report.log(Status.INFO, "Closing App Screenshot");
		
		Report.flushReport();
		closeApp();

		Log.info("Completed Execution");
	}

	/*
	 * Launch Appium Driver Method
	 */
	public void launchDriver() {
		try {
			Report.createChildNode("Setting Driver Capabilities");
			if (driver == null) {
				Report.info("Launching Appium Driver");
				URL url = new URL("http://" + ProjectConfig.getProperty("APPIUM_PORT_ID") + "/wd/hub");
				Report.info("Connecting to Appium Server Port: " + ProjectConfig.getProperty("APPIUM_PORT_ID"));

				capabilities = new DesiredCapabilities();

				// DEFAULT CAPABILITIES
				setCapabilities();

				switch (ProjectConfig.getProperty("APP_TYPE").toLowerCase()) {
				case "native":
					setNativeAppCapabilities();
					break;
				case "web":
					setWebAppCapabilities();
					break;
				default:
					Report.warn(ProjectConfig.getProperty("APP_TYPE")
							+ "is not a valid value. Please provide an valid app type.");
					break;
				}

				driver = new AndroidDriver<MobileElement>(url, getCapabilities());
				Log.info("Driver Initialized");
				Report.info("Application Started");

				driver.manage().timeouts().implicitlyWait(APP_LAUNCH_WAIT, TimeUnit.SECONDS);
				Report.logWithScreenShot(Status.INFO, "Opening App Screenshot");
			}
		} catch (MalformedURLException e) {
			Log.error("Malformed URL Exception: " + e.getMessage());
			Report.exception(e);
			e.printStackTrace();
		}
	}

	/*
	 * Desired Capability Method
	 */
	private void setCapabilities() {
		Report.info("Setting Desired Capabilities");

		capabilities.setCapability("deviceName", ProjectConfig.getProperty("DEVICE_NAME"));
		Report.info("DEVICE NAME: " + ProjectConfig.getProperty("DEVICE_NAME"));

		capabilities.setCapability("udid", ProjectConfig.getProperty("DEVICE_ID"));
		Report.info("DEVICE UDID: " + ProjectConfig.getProperty("DEVICE_ID"));

		capabilities.setCapability("platformName", ProjectConfig.getProperty("PLATFORM_NAME"));
		Report.info("PLATFORM NAME: " + ProjectConfig.getProperty("PLATFORM_NAME"));

		capabilities.setCapability("platformVersion", ProjectConfig.getProperty("PLATFORM_VERSION"));
		Report.info("PLATFORM VERSION: " + ProjectConfig.getProperty("PLATFORM_VERSION"));

		if (ProjectConfig.getProperty("USE_EMULATOR").equalsIgnoreCase("true"))
			capabilities.setCapability("avd", ProjectConfig.getProperty("AVD_NAME"));

		// capabilities.setCapability("automationName", "uiautomator2");
		// capabilities.setCapability("browserName", "");

	}

	/*
	 * Native Apps Capability Method
	 */
	private void setNativeAppCapabilities() {
		Report.info("Setting Native Apps Capabilities");

		capabilities.setCapability("appPackage", ProjectConfig.getProperty("APP_PACKAGE"));
		Report.info("APP PACKAGE: " + ProjectConfig.getProperty("APP_PACKAGE"));

		String appActivity = "";
		Log.info("Debug Activity Value: " + ProjectConfig.getProperty("DEBUG_ACTIVITY"));
		if (ProjectConfig.getProperty("DEBUG_ACTIVITY").equalsIgnoreCase("true"))
			appActivity = ProjectConfig.getProperty("DEBUG_CUSTOM_APP_ACTIVITY");
		else
			appActivity = ProjectConfig.getProperty("APP_ACTIVITY");
		capabilities.setCapability("appActivity", appActivity);
		Report.info("APP ACTIVITY: " + appActivity);

		boolean noSign = false;
		if (ProjectConfig.getProperty("NO_SIGN").equalsIgnoreCase("true"))
			noSign = true;
		capabilities.setCapability("noSign", noSign);
		Report.info("NO SIGN: " + ProjectConfig.getProperty("NO_SIGN"));

		applicationFileLocation(ProjectConfig.getProperty("APP_NAME"));
		capabilities.setCapability("app", getAppFilePath());
	}

	/*
	 * Web Apps Capability Method
	 */
	private void setWebAppCapabilities() {
		Report.info("Setting Web Apps Capabilities");
	}

	/*
	 * Closing App
	 */
	private void closeApp() {
		Log.info("Closing App");
		driver.closeApp();
	}

	/*
	 * Returning Andorid Driver
	 * 
	 * @return driver
	 */
	public static AndroidDriver<MobileElement> getDriver() {
		return driver;

	}

	/*
	 * Setting Application File Location
	 */
	public void applicationFileLocation(String appName) {
		Log.info("Searching for Application File Location using Application Name: " + appName);
		switch (appName.toLowerCase()) {
		case "ebay":
			setAppFilePath(EBAY_RESOURCE_FOLDER_LOCATION + ProjectConfig.getProperty("EBAY_APK_PATH"));
			break;
		default:
			Report.warn(appName + "is not a valid value. Please provide an valid app name.");
			break;
		}

	}

	/*
	 * Getter method to retrieve application file location using instance
	 * variable
	 * 
	 * @return appFilePath
	 */
	public String getAppFilePath() {
		Log.info("Getting application file path: " + appFilePath);
		return appFilePath;
	}

	/*
	 * Setter method to set application file location to instance variable
	 * 
	 * @param appFilePath
	 */
	public void setAppFilePath(String appFilePath) {
		Log.info("Setting application file path value to: " + appFilePath);
		this.appFilePath = appFilePath;
	}

	/*
	 * Getter method to retrieve desired capabilities object using instance
	 * variable
	 * 
	 * @return capabilities
	 */
	public DesiredCapabilities getCapabilities() {
		return capabilities;
	}
}
