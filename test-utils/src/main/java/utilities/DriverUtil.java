package utilities;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import libraries.Data;

public class DriverUtil implements Data {
	/**
	 * @author Praveen Kalliyath
	 * 
	 * @version 1.0
	 * 
	 * @since June 30 2020
	 */

	/**
	 * Method to return driver element
	 * 
	 * @param using
	 * 
	 * @return mobileElement
	 */
	public static MobileElement element(String locatorName) {
		MobileElement element = null;
		String[] locator = CommonUtil.getLocator(locatorName);
		String locatorType = locator[0];
		String locatorValue = locator[1];
		try {
			switch (locatorType.toLowerCase()) {
			case "name":
				element = BaseUtil.getDriver().findElementByName(locatorValue);
				break;
			case "id":
				element = BaseUtil.getDriver().findElementById(locatorValue);
				break;
			case "classname":
				element = BaseUtil.getDriver().findElementByClassName(locatorValue);
				break;
			case "linktext":
				element = BaseUtil.getDriver().findElementByLinkText(locatorValue);
				break;
			case "accessid":
				element = BaseUtil.getDriver().findElementByAccessibilityId(locatorValue);
				break;
			case "tagname":
				element = BaseUtil.getDriver().findElementByTagName(locatorValue);
				break;
			case "xpath":
				element = BaseUtil.getDriver().findElementByXPath(locatorValue);
				break;
			case "css":
				element = BaseUtil.getDriver().findElementByCssSelector(locatorValue);
				break;
			case "image":
				element = BaseUtil.getDriver().findElementByImage(locatorValue);
				break;
			case "partialtext":
				element = BaseUtil.getDriver().findElementByPartialLinkText(locatorValue);
				break;
			case "custom":
				element = BaseUtil.getDriver().findElementByCustom(locatorValue);
				break;
			case "ui-text":
				element = BaseUtil.getDriver()
						.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
								+ ".scrollIntoView(new UiSelector().text(" + locatorValue + "))");
				break;
			case "ui-textcontains":
				element = BaseUtil.getDriver()
						.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
								+ ".scrollIntoView(new UiSelector().textContains(" + locatorValue + "))");
				break;
			case "ui-id":
				element = BaseUtil.getDriver()
						.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
								+ ".scrollIntoView(new UiSelector().resourceIdMatches(" + locatorValue + "))");
				break;
			case "ui-id-text":
				element = BaseUtil.getDriver()
						.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
								+ ".scrollIntoView(new UiSelector().resourceIdMatches("
								+ CommonUtil.splitString(locatorValue, ":::")[0] + ")).text("
								+ CommonUtil.splitString(locatorValue, ":::")[1] + "))");
				break;
			default:
				Report.warn(locatorType + " is not a valid choose. Please Choose a valid type.");
				break;
			}
		} catch (Exception e) {
			Log.error("Unable to find element: " + element);
		}
		return element;
	}

	/**
	 * Method to return driver element
	 * 
	 * @param type
	 * 
	 * @param using
	 * 
	 * @return mobileElement
	 */
	public static MobileElement element(String type, String value) {
		MobileElement element = null;
		try {
			switch (type.toLowerCase()) {
			case "name":
				element = BaseUtil.getDriver().findElementByName(value);
				break;
			case "id":
				element = BaseUtil.getDriver().findElementById(value);
				break;
			case "classname":
				element = BaseUtil.getDriver().findElementByClassName(value);
				break;
			case "linktext":
				element = BaseUtil.getDriver().findElementByLinkText(value);
				break;
			case "accessid":
				element = BaseUtil.getDriver().findElementByAccessibilityId(value);
				break;
			case "tagname":
				element = BaseUtil.getDriver().findElementByTagName(value);
				break;
			case "xpath":
				element = BaseUtil.getDriver().findElementByXPath(value);
				break;
			case "css":
				element = BaseUtil.getDriver().findElementByCssSelector(value);
				break;
			case "image":
				element = BaseUtil.getDriver().findElementByImage(value);
				break;
			case "partialtext":
				element = BaseUtil.getDriver().findElementByPartialLinkText(value);
				break;
			case "custom":
				element = BaseUtil.getDriver().findElementByCustom(value);
				break;
			default:
				Report.warn(type + " is not a valid choose. Please Choose a valid type.");
				break;
			}
		} catch (Exception e) {
			Log.error("Unable to find element: " + element);
		}
		return element;
	}

	/**
	 * Method to return driver element
	 * 
	 * @param using
	 * 
	 * @return mobileElement
	 */
	public static List<MobileElement> listElement(String value) {
		List<MobileElement> element = null;
		String type = CommonUtil.getLocator(value)[0];
		value = CommonUtil.getLocator(value)[1];
		switch (type.toLowerCase()) {
		case "name":
			element = BaseUtil.getDriver().findElementsByName(value);
			break;
		case "id":
			element = BaseUtil.getDriver().findElementsById(value);
			break;
		case "classname":
			element = BaseUtil.getDriver().findElementsByClassName(value);
			break;
		case "linktext":
			element = BaseUtil.getDriver().findElementsByLinkText(value);
			break;
		case "accessid":
			element = BaseUtil.getDriver().findElementsByAccessibilityId(value);
			break;
		case "tagname":
			element = BaseUtil.getDriver().findElementsByTagName(value);
			break;
		case "xpath":
			element = BaseUtil.getDriver().findElementsByXPath(value);
			break;
		case "css":
			element = BaseUtil.getDriver().findElementsByCssSelector(value);
			break;
		case "image":
			element = BaseUtil.getDriver().findElementsByImage(value);
			break;
		case "partialtext":
			element = BaseUtil.getDriver().findElementsByPartialLinkText(value);
			break;
		case "custom":
			element = BaseUtil.getDriver().findElementsByCustom(value);
			break;
		default:
			Report.warn(type + " is not a valid choose. Please Choose a valid type.");
			break;
		}
		return element;
	}

	/**
	 * Method to return driver element
	 * 
	 * @param type
	 * 
	 * @param using
	 * 
	 * @return mobileElement
	 */
	public static List<MobileElement> listElement(String type, String value) {
		List<MobileElement> element = null;
		switch (type.toLowerCase()) {
		case "name":
			element = BaseUtil.getDriver().findElementsByName(value);
			break;
		case "id":
			element = BaseUtil.getDriver().findElementsById(value);
			break;
		case "classname":
			element = BaseUtil.getDriver().findElementsByClassName(value);
			break;
		case "linktext":
			element = BaseUtil.getDriver().findElementsByLinkText(value);
			break;
		case "accessid":
			element = BaseUtil.getDriver().findElementsByAccessibilityId(value);
			break;
		case "tagname":
			element = BaseUtil.getDriver().findElementsByTagName(value);
			break;
		case "xpath":
			element = BaseUtil.getDriver().findElementsByXPath(value);
			break;
		case "css":
			element = BaseUtil.getDriver().findElementsByCssSelector(value);
			break;
		case "image":
			element = BaseUtil.getDriver().findElementsByImage(value);
			break;
		case "partialtext":
			element = BaseUtil.getDriver().findElementsByPartialLinkText(value);
			break;
		case "custom":
			element = BaseUtil.getDriver().findElementsByCustom(value);
			break;
		default:
			Report.warn(type + " is not a valid choose. Please Choose a valid type.");
			break;
		}
		return element;
	}

	/**
	 * Click element
	 * 
	 * @param element
	 */
	public static void click(WebElement element) {

		try {
			if (checkElementIsDisplayed(element))
				element.click();
			Report.pass("Clicked on element: " + element.toString());
		} catch (Exception e) {
			Report.exception(e);
			Report.fail("Failed to click element: " + element.toString());
		}
	}

	/**
	 * Click element
	 * 
	 * @param by
	 */
	public static void click(By by) {

		try {
			if (checkElementIsDisplayed(BaseUtil.getDriver().findElement(by)))
				BaseUtil.getDriver().findElement(by).click();
			Report.pass("Clicked on element: " + by.toString());
		} catch (Exception e) {
			Report.exception(e);
			Report.fail("Failed to click element: " + by.toString());
		}
	}

	/**
	 * Get text from element
	 * 
	 * @param element
	 * 
	 * @return element.getText()
	 */
	public static String getText(MobileElement element) {
		Log.info("For " + element.toString() + " :: Element Text: " + element.getText());
		return element.getText();
	}

	/**
	 * Set text to element
	 * 
	 * @param element
	 * 
	 * @param value
	 */
	public static void setText(MobileElement element, String value) {
		Log.info("Setting Text '" + value + "' to Element: " + element.getText());
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * Set text to element
	 * 
	 * @param element
	 * 
	 * @param value
	 */
	public static void setTextAndPressEnter(MobileElement element, String value) {
		Log.info("Setting Text '" + value + "' to Element: " + element.getText());
		element.clear();
		element.sendKeys(value);
		sendEnter();
	}

	/**
	 * Send Enter Key
	 */
	public static void sendEnter() {
		Log.info("Send Enter");
		BaseUtil.getDriver().executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));
	}

	/**
	 * Send Back Key
	 */
	public static void pressBackKey() {
		Log.info("Send Back Key");
		BaseUtil.getDriver().pressKey(new KeyEvent(AndroidKey.BACK));
	}

	/**
	 * CHECK METHODS
	 */

	/**
	 * Check Element Displayed
	 * 
	 * @param element
	 * 
	 * @return boolean
	 */
	public static boolean checkElementIsDisplayed(WebElement element) {
		boolean displayed = false;
		try {
			Log.info("Checking whether element is dispalyed: " + element.toString());
			if (element.isDisplayed()) {
				Report.pass("Element is displayed: " + element.toString());
				displayed = true;
			}
		} catch (Exception e) {
			Report.exception(e);
			displayed = false;
			Report.fail("Element is not displayed");
		}
		return displayed;
	}

	/**
	 * Check Mobile Element Displayed
	 * 
	 * @param element
	 * 
	 * @return boolean
	 */
	public static boolean checkMobileElementIsDisplayed(MobileElement element) {
		Log.info("Checking element is displayed");
		boolean displayed = false;
		try {
			Log.info("Checking whether element is dispalyed: " + element.toString());
			if (element.isDisplayed()) {
				Report.pass("Element is displayed: " + element.toString());
				displayed = true;
			}
		} catch (Exception e) {
			Report.exception(e);
			Report.fail("Element is not displayed");
		}

		Log.info("Returning Boolean: " + displayed);
		return displayed;
	}

	/**
	 * Check Element Displayed
	 * 
	 * @param by
	 * 
	 * @return boolean
	 */
	public static boolean checkElementIsDisplayed(By by) {
		Log.info("Checking whether element is dispalyed: " + by.toString());
		boolean displayed = false;
		try {
			if (BaseUtil.getDriver().findElement(by).isDisplayed()) {
				Report.pass("Element is displayed: " + by.toString());
				displayed = true;
			}
		} catch (Exception e) {
			Report.exception(e);
			Report.fail("Element is not displayed: " + by.toString());
		}
		return displayed;
	}

	/**
	 * Check Element Not Displayed
	 * 
	 * @param element
	 * 
	 * @return boolean
	 */
	public static boolean checkElementIsNotDisplayed(MobileElement element) {
		Log.info("Checking whether element is not dispalyed: " + element.toString());
		boolean notDisplayed = false;
		try {
			if (element.isDisplayed()) {
				Report.fail("Element is displayed: " + element.toString());
			}
		} catch (Exception e) {
			notDisplayed = true;
			Report.pass("Element is not displayed: " + element.toString());
		}
		return notDisplayed;
	}

	/**
	 * Check Element Not Displayed
	 * 
	 * @param by
	 * 
	 * @return boolean
	 */
	public static boolean checkElementIsNotDisplayed(By by) {
		Log.info("Checking whether element is not dispalyed: " + by.toString());
		boolean notDisplayed = false;
		try {
			if (BaseUtil.getDriver().findElement(by).isDisplayed()) {
				Report.fail("Element is displayed: " + by.toString());
			}
		} catch (Exception e) {
			notDisplayed = true;
			Report.pass("Element is not displayed: " + by.toString());
		}
		return notDisplayed;
	}

	/**
	 * Check List Elements Are Visible
	 * 
	 * @param elements
	 * 
	 * @return boolean
	 */
	public static boolean checkListElementsIsDisplayed(List<WebElement> elements) {
		Log.info("Checking whether list element is dispalyed: " + elements.toString());
		boolean displayed;
		webDriveWait().ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.visibilityOfAllElements(elements));
		displayed = true;

		return displayed;
	}

	/**
	 * Get List Element Size
	 * 
	 * @return size
	 */
	public static int getMobileElementSize(List<MobileElement> elements) {
		Log.info("List Elements Size " + elements.size());
		return elements.size();
	}

	/**
	 * Get List Element Size
	 * 
	 * @return size
	 */
	public static int getWebElementElementSize(List<WebElement> elements) {
		Log.info("List Elements Size " + elements.size());
		return elements.size();
	}

	/**
	 * WAIT METHODS
	 */

	/**
	 * Wait For Element
	 * 
	 * @param element
	 * 
	 * @return boolean
	 */
	public static boolean waitForElementToBeDisplayed(WebElement element) {
		Log.info("Waiting for element to be dispalyed: " + element.toString());
		boolean displayed;
		webDriveWait().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
		displayed = true;

		return displayed;
	}

	/**
	 * Wait For Element
	 * 
	 * @param by
	 * 
	 * @return boolean
	 */
	public static boolean waitForElementToBeDisplayed(By by) {
		Log.info("Waiting for element to be dispalyed: " + by.toString());
		boolean displayed;
		webDriveWait().ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.visibilityOfElementLocated(by));
		displayed = true;

		return displayed;
	}

	/**
	 * Wait For List Elements
	 * 
	 * @param elements
	 * 
	 * @return boolean
	 */
	public static boolean waitForElementToBeDisplayed(List<WebElement> elements) {
		Log.info("Waiting for list elements to be dispalyed: " + elements.toString());
		boolean displayed;
		webDriveWait().ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.visibilityOfAllElements(elements));
		displayed = true;

		return displayed;
	}

	/**
	 * Wait For List Elements
	 * 
	 * @param by
	 * 
	 * @return boolean
	 */
	public static boolean waitForListElementsToBeDisplayed(By by) {
		Log.info("Waiting for list elements to be dispalyed: " + by.toString());
		boolean displayed;
		webDriveWait().ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		displayed = true;

		return displayed;
	}

	/**
	 * Wait For List Elements To Be Not Visible
	 * 
	 * @param elements
	 * 
	 * @return boolean
	 */
	public static boolean waitForElementToBeNotDisplayed(List<WebElement> elements) {
		Log.info("Waiting for list elements to be not dispalyed: " + elements.toString());
		boolean displayed;
		webDriveWait().ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.invisibilityOfAllElements(elements));
		displayed = true;

		return displayed;
	}

	/**
	 * Wait For Element To Be Not Visible
	 * 
	 * @param by
	 * 
	 * @return boolean
	 */
	public static boolean waitForElementToBeNotDisplayed(By by) {
		Log.info("Waiting for list elements to be not dispalyed: " + by.toString());
		boolean displayed;
		webDriveWait().ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.invisibilityOfElementLocated(by));
		displayed = true;

		return displayed;
	}

	/**
	 * Wait For Element To Be Not Visible
	 * 
	 * @param element
	 * 
	 * @return boolean
	 */
	public static boolean waitForElementToBeNotDisplayed(MobileElement element) {
		Log.info("Waiting for element to be not dispalyed: " + element.toString());
		boolean displayed;
		webDriveWait().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOf(element));
		displayed = true;

		return displayed;
	}

	/**
	 * Select Value from List
	 * 
	 * @param WebElement
	 * 
	 * @param searchKey
	 */
	public static void selectWebItemValueFromList(List<WebElement> elements, String searchKey) {
		boolean itemFound = false;
		Log.info("Search and Select List Value");
		if (searchKey.isEmpty()) {
			Random random = new Random();
			searchKey = elements.get(random.nextInt(elements.size())).getText();
			Log.info("Search item: '" + searchKey);
		}
		Log.info("Searching for item in list: " + searchKey);
		CommonUtil.softAssertTrue(elements.size() == 0);

		for (WebElement element : elements) {
			String elementText = element.getText();
			Log.info("Searching List Item Value Using EqualIgnoreCase: Element Text: " + elementText);
			if (element.getText().equalsIgnoreCase(searchKey)) {
				click(element);
				itemFound = true;
				Log.info("Selected list item: '" + elementText);
				break;
			}
		}

		if (!itemFound) {
			for (WebElement element : elements) {
				String elementText = element.getText();
				Log.info("Searching List Item Value Using Contains: Element Text: " + elementText);
				if (element.getText().toLowerCase().contains(searchKey.toLowerCase())) {
					click(element);
					itemFound = true;
					Log.info("Selected list item: '" + elementText);
					break;
				}
			}
		}

		if (!itemFound) {
			Random random = new Random();
			searchKey = elements.get(random.nextInt(elements.size())).getText();
			Log.info("Search item: '" + searchKey);
			for (WebElement element : elements) {
				String elementText = element.getText();
				Log.info("Searching List Item Value Using Random: Element Text: " + elementText);
				if (elementText.toLowerCase().contains(searchKey.toLowerCase())) {
					click(element);
					itemFound = true;
					Log.info("Selected list item: '" + elementText);
					break;
				}
			}
		}

		if (!itemFound) {
			Log.error("Search item: '" + searchKey + "' not found");
			CommonUtil.hardAssertFail("Search item: '" + searchKey + "' not found");
		}
	}

	/**
	 * Select Value from List
	 * 
	 * @param mobileElement
	 * 
	 * @param searchKey
	 */
	public static void selectItemValueFromList(List<MobileElement> elements, String searchKey) {
		boolean itemFound = false;
		Log.info("Search and Select List Value");
		if (searchKey.isEmpty()) {
			Random random = new Random();
			searchKey = elements.get(random.nextInt(elements.size())).getText();
			Log.info("Search item: '" + searchKey);
		}
		Log.info("Searching for item in list: " + searchKey);
		CommonUtil.softAssertTrue(elements.size() == 0);

		for (MobileElement element : elements) {
			String elementText = element.getText();
			Log.info("Searching List Item Value Using EqualIgnoreCase: Element Text: " + elementText);
			if (element.getText().equalsIgnoreCase(searchKey)) {
				click(element);
				itemFound = true;
				Log.info("Selected list item: '" + elementText);
				break;
			}
		}

		if (!itemFound) {
			for (MobileElement element : elements) {
				String elementText = element.getText();
				Log.info("Searching List Item Value Using Contains: Element Text: " + elementText);
				if (element.getText().toLowerCase().contains(searchKey.toLowerCase())) {
					click(element);
					itemFound = true;
					Log.info("Selected list item: '" + elementText);
					break;
				}
			}
		}

		if (!itemFound) {
			Random random = new Random();
			searchKey = elements.get(random.nextInt(elements.size())).getText();
			Log.info("Search item: '" + searchKey);
			for (MobileElement element : elements) {
				String elementText = element.getText();
				Log.info("Searching List Item Value Using Random: Element Text: " + elementText);
				if (elementText.toLowerCase().contains(searchKey.toLowerCase())) {
					click(element);
					itemFound = true;
					Log.info("Selected list item: '" + elementText);
					break;
				}
			}
		}

		if (!itemFound) {
			Log.error("Search item: '" + searchKey + "' not found");
			CommonUtil.hardAssertFail("Search item: '" + searchKey + "' not found");
		}
	}

	/**
	 * Select Value from List
	 * 
	 * @param mobileElement
	 * 
	 * @param searchKey
	 */
	public static void selectItemValueFromList(By by, String searchKey) {
		boolean itemFound = false;
		Log.info("Search and Select List Value");
		if (searchKey.isEmpty()) {
			Random random = new Random();
			searchKey = BaseUtil.getDriver().findElements(by)
					.get(random.nextInt(BaseUtil.getDriver().findElements(by).size())).getText();
			Log.info("Search item: '" + searchKey);
		}

		Log.info("Searching for item in list: " + searchKey);
		CommonUtil.softAssertTrue(BaseUtil.getDriver().findElements(by).size() == 0);
		for (MobileElement element : BaseUtil.getDriver().findElements(by)) {
			String elementText = element.getText();
			Log.info("Searching List Item Value Using EqualIgnoreCase: Element Text: " + elementText);
			if (elementText.equalsIgnoreCase(searchKey)) {
				click(element);
				itemFound = true;
				Log.info("Selected search item: '" + elementText);
				break;
			}
		}

		if (!itemFound) {
			for (MobileElement element : BaseUtil.getDriver().findElements(by)) {
				String elementText = element.getText();
				Log.info("Searching List Item Value Using Contains: Element Text: " + elementText);
				if (elementText.toLowerCase().contains(searchKey.toLowerCase())) {
					click(element);
					itemFound = true;
					Log.info("Selected list item: '" + elementText);
					break;
				}
			}
		}

		if (!itemFound) {
			Random random = new Random();
			searchKey = BaseUtil.getDriver().findElements(by)
					.get(random.nextInt(BaseUtil.getDriver().findElements(by).size())).getText();
			Log.info("Search item: '" + searchKey);
			for (MobileElement element : BaseUtil.getDriver().findElements(by)) {
				String elementText = element.getText();
				Log.info("Searching List Item Value Using Random: Element Text: " + elementText);
				if (elementText.toLowerCase().contains(searchKey.toLowerCase())) {
					click(element);
					itemFound = true;
					Log.info("Selected list item: '" + elementText);
					break;
				}
			}
		}

		if (!itemFound) {
			Log.error("Search item: '" + searchKey + "' not found");
			CommonUtil.softAssertFail("Search item: '" + searchKey + "' not found");
		}
	}

	/**
	 * Base64 Screenshot Method, Used to attach screenshot to report.
	 * 
	 * @return screenshot string
	 */
	public static String getBase64Screenshot() {
		Log.info("Getting Base64 Screenshot");
		return BaseUtil.getDriver().getScreenshotAs(OutputType.BASE64);
	}

	/**
	 * WAITS
	 */

	/**
	 * WebDriverWait Method
	 * 
	 * @return new WebDriverWait(driver, DEFAULT_WAIT);
	 */
	public static WebDriverWait webDriveWait() {
		Log.info("Returning WebDriver Wait");
		return new WebDriverWait(BaseUtil.getDriver(), DEFAULT_WAIT);
	}

	/**
	 * WebDriverWait Method
	 * 
	 * @param seconds
	 * 
	 * @return new WebDriverWait(driver, seconds);
	 */
	public static WebDriverWait webDriveWait(long seconds) {
		Log.info("Returning WebDriver Wait");
		return new WebDriverWait(BaseUtil.getDriver(), seconds);
	}

	/**
	 * Implicit Wait
	 */
	public static void implicitWait() {
		Log.info("Waiting for Default Wait '" + DEFAULT_WAIT + "' seconds.");
		BaseUtil.getDriver().manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);
	}

	/**
	 * Implicit Wait
	 * 
	 * @param seconds
	 */
	public static void implicitWait(long seconds) {
		Log.info("Waiting for '" + seconds + "' seconds.");
		if (seconds < 1) {
			seconds = DEFAULT_WAIT;
		}
		BaseUtil.getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	/**
	 * SCROLL & SWIPE METHODS
	 */
	/**
	 * Performs swipe inside an element
	 *
	 * @param element
	 *            the element to swipe
	 * @param direction
	 *            the direction of swipe
	 * @version java-client: 7.3.0
	 **/
	public static void swipeElementAndroid(MobileElement element, String direction) {
		System.out.println("swipeElementAndroid(): dir: '" + direction + "'"); // always
																				// log
																				// your
																				// actions

		// Animation default time:
		// - Android: 300 ms
		// - iOS: 200 ms
		// final value depends on your app and could be greater
		final int ANIMATION_TIME = 200; // ms

		final int PRESS_TIME = 200; // ms

		int edgeBorder;
		PointOption pointOptionStart, pointOptionEnd;

		// init screen variables
		Rectangle rect = element.getRect();
		// sometimes it is needed to configure edgeBorders
		// you can also improve borders to have vertical/horizontal
		// or left/right/up/down border variables
		edgeBorder = 0;

		switch (direction.toUpperCase()) {
		case "DOWN": // from up to down
			pointOptionStart = PointOption.point(rect.x + rect.width / 2, rect.y + edgeBorder);
			pointOptionEnd = PointOption.point(rect.x + rect.width / 2, rect.y + rect.height - edgeBorder);
			break;
		case "UP": // from down to up
			pointOptionStart = PointOption.point(rect.x + rect.width / 2, rect.y + rect.height - edgeBorder);
			pointOptionEnd = PointOption.point(rect.x + rect.width / 2, rect.y + edgeBorder);
			break;
		case "LEFT": // from right to left
			pointOptionStart = PointOption.point(rect.x + rect.width - edgeBorder, rect.y + rect.height / 2);
			pointOptionEnd = PointOption.point(rect.x + edgeBorder, rect.y + rect.height / 2);
			break;
		case "RIGHT": // from left to right
			pointOptionStart = PointOption.point(rect.x + edgeBorder, rect.y + rect.height / 2);
			pointOptionEnd = PointOption.point(rect.x + rect.width - edgeBorder, rect.y + rect.height / 2);
			break;
		default:
			throw new IllegalArgumentException("swipeElementAndroid(): dir: '" + direction + "' NOT supported");
		}

		// execute swipe using TouchAction
		try {
			new TouchAction(BaseUtil.getDriver()).press(pointOptionStart)
					// a bit more reliable when we add small wait
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME))).moveTo(pointOptionEnd).release()
					.perform();
		} catch (Exception e) {
			System.err.println("swipeElementAndroid(): TouchAction FAILED\n" + e.getMessage());
			return;
		}

		// always allow swipe action to complete
		try {
			Thread.sleep(ANIMATION_TIME);
		} catch (InterruptedException e) {
			// ignore
		}
	}

	public enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}

	/**
	 * Performs swipe from the center of screen
	 *
	 * @param dir
	 *            the direction of swipe
	 * @version java-client: 7.3.0
	 **/
	public static void swipeScreen(Direction dir) {
		System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log
																	// your
																	// actions

		// Animation default time:
		// - Android: 300 ms
		// - iOS: 200 ms
		// final value depends on your app and could be greater
		final int ANIMATION_TIME = 200; // ms

		final int PRESS_TIME = 200; // ms

		int edgeBorder = 10; // better avoid edges
		PointOption pointOptionStart, pointOptionEnd;

		// init screen variables
		Dimension dims = BaseUtil.getDriver().manage().window().getSize();

		// init start point = center of screen
		pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

		switch (dir) {
		case DOWN: // center of footer
			pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
			break;
		case UP: // center of header
			pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
			break;
		case LEFT: // center of left side
			pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
			break;
		case RIGHT: // center of right side
			pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
			break;
		default:
			throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
		}

		// execute swipe using TouchAction
		try {
			new TouchAction(BaseUtil.getDriver()).press(pointOptionStart)
					// a bit more reliable when we add small wait
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME))).moveTo(pointOptionEnd).release()
					.perform();
		} catch (Exception e) {
			System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
			return;
		}

		// always allow swipe action to complete
		try {
			Thread.sleep(ANIMATION_TIME);
		} catch (InterruptedException e) {
			// ignore
		}
	}

	/**
	 * Performs small swipe from the center of screen
	 *
	 * @param dir
	 *            the direction of swipe
	 * @version java-client: 7.3.0
	 **/
	public static void swipeScreenSmall(Direction dir) {
		System.out.println("swipeScreenSmall(): dir: '" + dir + "'"); // always
																		// log
																		// your
																		// actions

		// Animation default time:
		// - Android: 300 ms
		// - iOS: 200 ms
		// final value depends on your app and could be greater
		final int ANIMATION_TIME = 200; // ms

		final int PRESS_TIME = 200; // ms

		PointOption pointOptionStart, pointOptionEnd;

		// init screen variables
		Dimension dims = BaseUtil.getDriver().manage().window().getSize();

		// init start point = center of screen
		pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

		// reduce swipe move into multiplier times comparing to swipeScreen move
		int mult = 10; // multiplier
		switch (dir) {
		case DOWN: // center of footer
			pointOptionEnd = PointOption.point(dims.width / 2, (dims.height / 2) + (dims.height / 2) / mult);
			break;
		case UP: // center of header
			pointOptionEnd = PointOption.point(dims.width / 2, (dims.height / 2) - (dims.height / 2) / mult);
			break;
		case LEFT: // center of left side
			pointOptionEnd = PointOption.point((dims.width / 2) - (dims.width / 2) / mult, dims.height / 2);
			break;
		case RIGHT: // center of right side
			pointOptionEnd = PointOption.point((dims.width / 2) + (dims.width / 2) / mult, dims.height / 2);
			break;
		default:
			throw new IllegalArgumentException("swipeScreenSmall(): dir: '" + dir.toString() + "' NOT supported");
		}

		// execute swipe using TouchAction
		try {
			new TouchAction(BaseUtil.getDriver()).press(pointOptionStart)
					// a bit more reliable when we add small wait
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME))).moveTo(pointOptionEnd).release()
					.perform();
		} catch (Exception e) {
			System.err.println("swipeScreenSmall(): TouchAction FAILED\n" + e.getMessage());
			return;
		}

		// always allow swipe action to complete
		try {
			Thread.sleep(ANIMATION_TIME);
		} catch (InterruptedException e) {
			// ignore
		}
	}

	/**
	 * Method to swipe to the beginning. scrollToBeginning (moves exactly by one
	 * view. 10 scrolls max)
	 */
	public static void swipeToTop() {
		// scrollToBeginning (moves exactly by one view. 10 scrolls max)
		try {
			BaseUtil.getDriver().findElement(MobileBy
					.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(10);"));
		} catch (InvalidSelectorException e) {
			Report.pass("Scrolled to Top");
		}
	}

	/**
	 * Method to fling to the beginning. flingToBeginning (performs quick
	 * swipes. 10 swipes max)
	 * 
	 */
	public static void flingToTop() {
		// flingToBeginning (performs quick swipes. 10 swipes max)
		try {
			BaseUtil.getDriver().findElement(MobileBy
					.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).flingToBeginning(10);"));
		} catch (InvalidSelectorException e) {
			Report.pass("Fling to top");
		}
	}

	/**
	 * Method to swipe to the bottom. scrollToEnd (moves exactly by one view. 10
	 * scrolls max)
	 */
	public static void swipeToBottom() {
		// scrollToEnd (moves exactly by one view. 10 scrolls max)
		try {
			BaseUtil.getDriver().findElement(MobileBy
					.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10);"));
		} catch (InvalidSelectorException e) {
			Report.pass("Scrolled to bottom");
		}
	}

	/**
	 * Method to fling to the bottom. flingToEnd (performs quick swipes. 10
	 * swipes max)
	 * 
	 */
	public static void flingToBottom() {
		// flingToEnd (performs quick swipes. 10 swipes max)
		try {
			BaseUtil.getDriver().findElement(
					MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).flingToEnd(10);"));
		} catch (InvalidSelectorException e) {
			Report.pass("Fling to bottom");
		}
	}

	/**
	 * Method to scroll forward. scrollForward (moves exactly by one view. 10
	 * scrolls max)
	 */
	public static void scrollForward() {
		// scrollForward (moves exactly by one view. 10 scrolls max)
		try {
			BaseUtil.getDriver().findElement(MobileBy
					.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));
		} catch (InvalidSelectorException e) {
			Report.pass("Scrolled Forward");
		}
	}

	/**
	 * Method to fling forward. flingForward (performs quick swipes. 10 swipes
	 * max)
	 * 
	 */
	public static void flingForward() {
		// flingForward (performs quick swipes. 10 swipes max)
		try {
			BaseUtil.getDriver().findElement(
					MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).flingForward();"));
		} catch (InvalidSelectorException e) {
			Report.pass("Flinged Forward");
		}
	}

	/**
	 * Method to scroll backward. scrollForward (moves exactly by one view. 10
	 * scrolls max)
	 */
	public static void scrollBackward() {
		// scrollForward (moves exactly by one view. 10 scrolls max)
		try {
			BaseUtil.getDriver().findElement(MobileBy
					.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollBackward();"));
		} catch (InvalidSelectorException e) {
			Report.pass("Scrolled Backward");
		}
	}

	/**
	 * Method to fling backward. flingForward (performs quick swipes. 10 swipes
	 * max)
	 * 
	 */
	public static void flingBackward() {
		// flingForward (performs quick swipes. 10 swipes max)
		try {
			BaseUtil.getDriver().findElement(MobileBy
					.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).flingBackward();"));
		} catch (InvalidSelectorException e) {
			Report.pass("Flinged Backward");
		}
	}

	/**
	 * Method to swipe right
	 */
	public static void swipeRight() {
		Dimension size = BaseUtil.getDriver().manage().window().getSize();
		Log.info(size.height + "'height");
		Log.info(size.width + "'width");
		Log.info("Size: " + size);
		int startPoint = (int) (size.width * 0.99);
		int endPoint = (int) (size.width * 0.15);
		int ScreenPlace = (int) (size.height * 0.40);
		// int y = (int) size.height * 20;
		TouchAction<?> touchAction = new TouchAction<>(BaseUtil.getDriver());
		touchAction.press(PointOption.point(startPoint, ScreenPlace))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
				.moveTo(PointOption.point(endPoint, ScreenPlace)).release().perform();
	}

	/**
	 * Method to swipe right
	 * 
	 * @param swipeCount
	 */
	public static void swipeRight(int swipeCount) {
		Report.info("Swiping Right '" + swipeCount + "' times");
		Dimension size = BaseUtil.getDriver().manage().window().getSize();
		Log.info(size.height + "'height");
		Log.info(size.width + "'width");
		Log.info("Size: " + size);
		int startPoint = (int) (size.width * 0.99);
		int endPoint = (int) (size.width * 0.15);
		int ScreenPlace = (int) (size.height * 0.40);
		// int y = (int) size.height * 20;

		TouchAction<?> touchAction = new TouchAction<>(BaseUtil.getDriver());
		for (int i = 1; i <= swipeCount; i++) {
			Log.info("Swipe Right Count:  " + i);
			touchAction.press(PointOption.point(startPoint, ScreenPlace))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(endPoint, ScreenPlace)).release().perform();
		}
	}

	/**
	 * Method to swipe left
	 */
	public static void swipeLeft() {
		Dimension size = BaseUtil.getDriver().manage().window().getSize();
		Log.info(size.height + "'height");
		Log.info(size.width + "'width");
		Log.info("Size: " + size);
		int startPoint = (int) (size.width * 0.99);
		int endPoint = (int) (size.width * 0.15);
		int ScreenPlace = (int) (size.height * 0.40);
		// int y = (int) size.height * 20;
		TouchAction<?> touchAction = new TouchAction<>(BaseUtil.getDriver());
		touchAction.press(PointOption.point(endPoint, ScreenPlace))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
				.moveTo(PointOption.point(startPoint, ScreenPlace)).release().perform();
	}

	/**
	 * Method to swipe left
	 * 
	 * @param swipeCount
	 */
	public static void swipeLeft(int swipeCount) {
		Report.info("Swiping Left '" + swipeCount + "' times");
		Dimension size = BaseUtil.getDriver().manage().window().getSize();
		Log.info(size.height + "'height");
		Log.info(size.width + "'width");
		Log.info("Size: " + size);
		int startPoint = (int) (size.width * 0.99);
		int endPoint = (int) (size.width * 0.15);
		int ScreenPlace = (int) (size.height * 0.40);

		TouchAction<?> touchAction = new TouchAction<>(BaseUtil.getDriver());
		for (int i = 1; i <= swipeCount; i++) {
			Log.info("Swipe Left Count:  " + i);
			touchAction.press(PointOption.point(endPoint, ScreenPlace))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(startPoint, ScreenPlace)).release().perform();
		}
	}

	/**
	 * Method to return java script executor
	 * 
	 * @return (JavascriptExecutor) driver;
	 */
	public static JavascriptExecutor executor() {
		return (JavascriptExecutor) BaseUtil.getDriver();
	}

	/**
	 * Method to swipe to top of the page
	 */
	public static void swipeUp() {
		Dimension dim = BaseUtil.getDriver().manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		int x = width / 2;
		int top_y = (int) (height * 0.80);
		int bottom_y = (int) (height * 0.20);
		Log.info("Swiping Up to coordinates :" + x + "  " + top_y + " " + -bottom_y);
		TouchAction<?> touchAction = new TouchAction<>(BaseUtil.getDriver());
		touchAction.press(PointOption.point(x, top_y)).moveTo(PointOption.point(x, -bottom_y)).release().perform();
	}

	/**
	 * Method to swipe to top of the page
	 */
	public static void swipeUp(int swipeCount) {
		Report.info("Swiping Up '" + swipeCount + "' times");
		Dimension dim = BaseUtil.getDriver().manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		int x = width / 2;
		int top_y = (int) (height * 0.80);
		int bottom_y = (int) (height * 0.20);
		Log.info("Swiping Up to coordinates :" + x + " " + top_y + " " + bottom_y);
		TouchAction<?> touchAction = new TouchAction<>(BaseUtil.getDriver());
		for (int i = 1; i <= swipeCount; i++) {
			Log.info("Swipe Up Count: " + i);
			touchAction.press(PointOption.point(x, bottom_y + 5)).moveTo(PointOption.point(x, top_y)).release()
					.perform();
		}
	}

	/**
	 * Method to swipe to bottom of the page
	 */
	public static void swipeDown() {
		Dimension dim = BaseUtil.getDriver().manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		int x = width / 2;
		int top_y = (int) (height * 0.90);
		int bottom_y = (int) (height * 0.40);
		Log.info("Swiping down to coordinates :" + x + "  " + top_y + " " + bottom_y);
		TouchAction<?> touchAction = new TouchAction<>(BaseUtil.getDriver());
		touchAction.press(PointOption.point(x, top_y)).moveTo(PointOption.point(x, bottom_y)).release().perform();
	}

	/**
	 * Method to swipe to bottom of the page
	 */
	public static void swipeDown(int swipeCount) {
		Report.info("Swiping Down '" + swipeCount + "' times");
		Dimension dim = BaseUtil.getDriver().manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		int x = width / 2;
		int top_y = (int) (height * 0.90);
		int bottom_y = (int) (height * 0.40);
		Log.info("Swiping down to coordinates :" + x + " " + top_y + " " + bottom_y);
		TouchAction<?> touchAction = new TouchAction<>(BaseUtil.getDriver());
		for (int i = 1; i <= swipeCount; i++) {
			Log.info("Swipe Down Count: " + i);
			touchAction.press(PointOption.point(x, top_y)).moveTo(PointOption.point(x, bottom_y)).release().perform();
		}
	}

	public static void scrollToElement(MobileElement element) {
		Report.info("Scrolling to  element:" + element.toString());
		Dimension dim = BaseUtil.getDriver().manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		TouchAction<?> touchAction = new TouchAction<>(BaseUtil.getDriver());
		touchAction.press(PointOption.point((width / 2), (int) (height * 0.80)))
				.moveTo(PointOption.point(element.getLocation().x, element.getLocation().y)).release().perform();
		Report.info("Scrolled to  element:" + element.toString());
	}

	public static boolean isDisplayed(final By by) {
		Log.info("Checking element is displayed");
		try {
			WebDriverWait driverWait = new WebDriverWait(BaseUtil.getDriver(), 5);
			Log.info("CHECKING... ");
			boolean found = driverWait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
			Log.info("FOUND: " + found);
			return found;
		} catch (Exception e) {
			e.printStackTrace();
			Report.info("DID YOU FIND? NO!");
			return false;
		}

	}

	public static void scrollDownUntilElementVisible(By by) {
		Report.info("Scrolling Until Element Found");
		boolean found = false;
		while (found) {
			if (isDisplayed(by) == true) {
				found = true;
				Report.info("Scrolled To Element Found");
			} else {
				swipeDown();
				Report.info("Scrolling to element:" + by.toString());
			}
		}
	}

	public static void scrollUpUntilElementVisible(MobileElement element) {
		while (DriverUtil.checkElementIsDisplayed(element)) {
			swipeUp();
		}
		Report.info("Scrolled to  element:" + element.toString());
	}

	public static void scrollLeftUntilElementVisible(MobileElement element) {
		while (DriverUtil.checkElementIsDisplayed(element)) {
			swipeLeft();
		}
		Report.info("Scrolled to  element:" + element.toString());
	}

	public static void scrollRightUntilElementVisible(MobileElement element) {
		while (DriverUtil.checkElementIsDisplayed(element)) {
			swipeRight();
		}
		Report.info("Scrolled to  element:" + element.toString());
	}

	/**
	 * TOUCH ACTIONS
	 */

	/**
	 * Method to drag and Drop an element from One Point to Another
	 * 
	 * @param element1
	 * 
	 * @param element2
	 */
	public static void dragAndDrop(MobileElement element1, MobileElement element2) {
		Report.info("Dragging element: " + element1.toString());
		TouchActions touchAction = new TouchActions(BaseUtil.getDriver());
		touchAction.dragAndDrop(element1, element2).perform();
		Report.info("Element " + element1.toString() + " has been droped at destination successfully.");
	}

	/**
	 * Method to drag and Drop an element from One Point to Another
	 * 
	 * @param element1
	 * 
	 * @param xAxis
	 * 
	 * @param yAxis
	 */
	public static void dragAndDrop(MobileElement element, int xAxis, int yAxis) {
		Report.info("Dragging element: " + element.toString());
		TouchActions touchAction = new TouchActions(BaseUtil.getDriver());
		touchAction.dragAndDropBy(element, xAxis, yAxis).perform();
		Report.info("Element " + element.toString() + " has been droped at X: " + xAxis + " Y:" + yAxis
				+ "  destination successfully.");
	}

	/**
	 * Method to long press an element
	 * 
	 * @param element
	 */
	public static void longPress(MobileElement element) {
		Log.info("Long Press element: " + element.toString());
		TouchActions touchAction = new TouchActions(BaseUtil.getDriver());
		touchAction.longPress(element).perform();
		Report.info("Long Press element " + element.toString() + " is successful.");
	}

	/**
	 * Method to press at location
	 * 
	 * @param xAxis
	 * 
	 * @param yAxis
	 */
	public static void pressLocation(int xAxis, int yAxis) {
		Report.info("Pressing at X: " + xAxis + " and Y: " + xAxis + " location.");
		TouchAction<?> touchAction = new TouchAction<>(BaseUtil.getDriver());
		touchAction.press(PointOption.point(xAxis, yAxis));
		touchAction.moveTo(PointOption.point(xAxis, 100));
		touchAction.release();
		touchAction.perform();
		Report.info("Press given at X: " + xAxis + " and Y: " + xAxis + " is ressed successfully.");
	}

	/**
	 * Method to single tap at location
	 * 
	 * @param element
	 */
	public static void singleTapOnElement(MobileElement element) {
		Log.info("Tap element: " + element.toString());
		TouchActions action = new TouchActions(BaseUtil.getDriver());
		action.singleTap(element);
		action.perform();
		Log.info("Performed tap on element: " + element.toString());
	}

	/**
	 * Method to double tap at location
	 * 
	 * @param element
	 */
	public static void doubleTapElement(MobileElement element) {
		Report.info("Double Tap Element: " + element.toString());
		TouchActions touchAction = new TouchActions(BaseUtil.getDriver());
		touchAction.doubleTap(element).perform();
		Report.info("Double Tap Element: " + element.toString() + " is succesfull");
	}

	/**
	 * Method to click an element
	 * 
	 * @param element1
	 * 
	 */
	public static void clickElement(MobileElement element) {
		Report.info("Click Element: " + element.toString());
		TouchActions touchAction = new TouchActions(BaseUtil.getDriver());
		touchAction.click(element).perform();
		Report.info("Click Element: " + element.toString() + " is succesfull");
	}

	/**
	 * Method to double click an element
	 * 
	 * @param element1
	 * 
	 */
	public static void doubleClickElement(MobileElement element) {
		Report.info("Double Click Element: " + element.toString());
		TouchActions touchAction = new TouchActions(BaseUtil.getDriver());
		touchAction.doubleClick(element).perform();
		Report.info("Double Click Element: " + element.toString() + " is succesfull");
	}

	/**
	 * Method to press key
	 * 
	 * @param key
	 * 
	 */
	public void passKey(Keys key) {
		Report.info("Passing Key: " + key);
		TouchActions touchAction = new TouchActions(BaseUtil.getDriver());
		touchAction.keyDown(key).perform();
		touchAction.keyUp(key).perform();
		Report.info("Passed Key: " + key + " succesfully");
	}

	/**
	 * Method to press key on element
	 * 
	 * @param key
	 * 
	 */
	public static void pressKeyOnElement(MobileElement element, Keys key) {
		Report.info("Passing key " + key + " to element: " + element.toString());
		TouchActions touchAction = new TouchActions(BaseUtil.getDriver());
		touchAction.keyDown(element, key).perform();
		touchAction.keyUp(element, key).perform();
		Report.info("Passed key " + key + " to element: " + element.toString());

	}

	/**
	 * Method to gesture down
	 * 
	 * @param xAxis
	 * 
	 * @param yAxis
	 * 
	 */
	public static void gestureDown(int xAxis, int yAxis) {
		Report.info("Applying Gesture to point: X: " + xAxis + " :: Y: " + yAxis);
		TouchActions touchAction = new TouchActions(BaseUtil.getDriver());
		touchAction.down(xAxis, yAxis).perform();
		Report.info("Successfully Applied Gesture to point: X: " + xAxis + " :: Y: " + yAxis);

	}

	/**
	 * Method to move to point
	 * 
	 * @param xAxis
	 * 
	 * @param yAxis
	 * 
	 */
	public static void move(int xAxis, int yAxis) {
		Report.info("Moving to point: X: " + xAxis + " :: Y: " + yAxis);
		TouchActions touchAction = new TouchActions(BaseUtil.getDriver());
		touchAction.move(xAxis, yAxis).perform();
		Report.info("Moved to point: X: " + xAxis + " :: Y: " + yAxis);
	}

	/**
	 * Method to move by offset
	 * 
	 * @param xOffset
	 * 
	 * @param yOffset
	 * 
	 */
	public static void moveToOffset(int xOffset, int yOffset) {
		Report.info("Moving to offset point: X: " + xOffset + " :: Y: " + yOffset);
		TouchActions touchAction = new TouchActions(BaseUtil.getDriver());
		touchAction.moveByOffset(xOffset, yOffset).perform();
		Report.info("Moved to offset point: X: " + xOffset + " :: Y: " + yOffset);
	}

	/**
	 * Method to move element by offset
	 * 
	 * @param xOffset
	 * 
	 * @param yOffset
	 * 
	 */
	public static void moveToElement(MobileElement element, int xOffset, int yOffset) {
		Report.info("Moving to  element:" + element.toString() + " offset point: X: " + xOffset + " :: Y: " + yOffset);
		TouchActions touchAction = new TouchActions(BaseUtil.getDriver());
		touchAction.moveToElement(element, xOffset, yOffset).perform();
		Report.info("Moved to  element:" + element.toString() + " offset point: X: " + xOffset + " :: Y: " + yOffset);
	}

	/**
	 * Method to move to element
	 */
	public static void moveToElement(MobileElement element) {
		Report.info("Moving to  element:" + element.toString());
		TouchActions touchAction = new TouchActions(BaseUtil.getDriver());
		touchAction.moveToElement(element).perform();
		Report.info("Moved to  element:" + element.toString());
	}

	/**
	 * Method to scroll to element by offset
	 * 
	 * @param element
	 * 
	 * @param xOffset
	 * 
	 * @param yOffset
	 * 
	 */
	public static void scrollFromElement(MobileElement element, int xOffset, int yOffset) {
		Report.info("Scroll From Element: " + element.toString() + "to point: X: " + xOffset + " :: Y: " + yOffset);
		TouchActions action = new TouchActions(BaseUtil.getDriver());
		action.scroll(element, xOffset, yOffset);
		action.perform();
		Report.info("Scrolled From Element: " + element.toString() + "to point: X: " + xOffset + " :: Y: " + yOffset);
	}

	/**
	 * Method to scroll by offset
	 * 
	 * @param xOffset
	 * 
	 * @param yOffset
	 * 
	 */
	public static void scrollTo(int xOffset, int yOffset) {
		Report.info("Scroll to point: X: " + xOffset + " :: Y: " + yOffset);
		TouchActions action = new TouchActions(BaseUtil.getDriver());
		action.scroll(xOffset, yOffset);
		action.perform();
		Report.info("Scrolled to point: X: " + xOffset + " :: Y: " + yOffset);
	}

	/**
	 * Method to finger up on screen by coordinates
	 * 
	 * @param xOffset
	 * 
	 * @param yOffset
	 * 
	 */
	public static void fingerUp(int x, int y) {
		Report.info("Applying Finger Up on the screen at : X: " + x + " :: Y: " + y);
		TouchActions action = new TouchActions(BaseUtil.getDriver());
		action.down(x, y);
		action.up((x + 20), (y + 20));
		action.perform();
		Report.info("Applied Finger Up on the screen at : X: " + x + " :: Y: " + y);
	}

	/**
	 * Method to finger up on screen by coordinates
	 */
	public static void fingerUp() {
		Report.info("Applying Finger Up on the screen");
		TouchActions action = new TouchActions(BaseUtil.getDriver());
		action.down(10, 10);
		action.up(20, 20);
		action.perform();
		Report.info("Applied Finger Up on the screen");

	}

	/**
	 * ORIENTATION
	 */

	/**
	 * Method to return current screen orientation
	 * 
	 * @return screen orientation
	 */
	public static ScreenOrientation getCurrentOrientation() {
		Report.info("Current Screen Orientation: " + BaseUtil.getDriver().getOrientation().toString());
		return BaseUtil.getDriver().getOrientation();
	}

	/**
	 * Method to set current screen orientation to portrait
	 */
	public static void setPortraitOrientation() {
		Report.info("Setting Screen Orientation To Portrait");
		if (getCurrentOrientation().toString().equalsIgnoreCase("LANDSCAPE"))
			BaseUtil.getDriver().rotate(ScreenOrientation.PORTRAIT);
		Report.info("Set Screen Orientation To Portrait");
	}

	/**
	 * Method to set current screen orientation to landscape
	 */
	public static void setLandscapeOrientation() {
		Report.info("Setting Screen Orientation To Landscape");
		if (getCurrentOrientation().toString().equalsIgnoreCase("PORTRAIT"))
			BaseUtil.getDriver().rotate(ScreenOrientation.LANDSCAPE);
		Report.info("Set Screen Orientation To Landscape");
	}

	/**
	 * Method to get current activity
	 * 
	 * @exception: io.appium.java_client.AppiumDriver
	 *                 cannot be cast to
	 *                 io.appium.java_client.android.AndroidDriver
	 * 
	 * @return activity
	 */
	public static String getCurrentActivity() {
		AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) BaseUtil.getDriver();
		Report.info("Current Activity: " + driver.currentActivity());
		return driver.currentActivity();
	}

	/**
	 * Method to install application
	 */
	public static void installApp(String appPath) {
		BaseUtil.getDriver().installApp(appPath);
	}

	/**
	 * Method to check application is installed
	 * 
	 * @return boolean
	 */
	public static boolean checkAppIsInstalled() {
		return BaseUtil.getDriver().isAppInstalled(ProjectConfig.getProperty("APP_PACKAGE"));
	}

	/**
	 * Method to terminate application
	 * 
	 * @param appId
	 */
	public static void terminateApp(String appId) {
		BaseUtil.getDriver().terminateApp(appId);
	}

	/**
	 * Method to get device time
	 * 
	 * @return deviceTime
	 */
	public static String getDeviceTime() {
		return BaseUtil.getDriver().getDeviceTime();
	}

	/**
	 * Method to get device time in format
	 * 
	 * @param format
	 * 
	 * @return deviceTime
	 */
	public static String getDeviceTime(String format) {
		return BaseUtil.getDriver().getDeviceTime(format);
	}

	/**
	 * Method to hide Keyboard
	 */
	public static void hideKeyboard() {
		if (BaseUtil.getDriver().isKeyboardShown())
			BaseUtil.getDriver().hideKeyboard();
	}

	/**
	 * Method to get driver status
	 */
	public static void driverStatus() {
		Log.info("Driver: " + BaseUtil.getDriver().getSessionId());
		Log.info("Driver: " + BaseUtil.getDriver());
		Map<String, Object> map = BaseUtil.getDriver().getStatus();
		Log.info(map.toString());
	}

	public static void scrollUsingTouchActionsByElements(MobileElement startElement, MobileElement endElement) {
		TouchAction<?> actions = new TouchAction<>(BaseUtil.getDriver());
		actions.press(PointOption.point(startElement.getLocation().x, startElement.getLocation().y))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
				.moveTo(PointOption.point(endElement.getLocation().x, endElement.getLocation().y)).release().perform();
	}

	/**
	 * Find Element Using Android UIAutomator
	 * 
	 * @param text
	 */
	public static MobileElement findElementByAndroidUIAutomator(String text) {
		return BaseUtil.getDriver().findElementByAndroidUIAutomator(
				"new UiScrollable(" + "new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().textContains('" + text + "'));");
	}

	public static MobileElement findElementByAndroidUIAutomator(String id, String text) {
		return BaseUtil.getDriver().findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".resourceId(\'" + id + "')).scrollIntoView(" + "new UiSelector().text('" + text + "'));");
	}

}
