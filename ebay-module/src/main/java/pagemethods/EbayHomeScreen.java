package pagemethods;

import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import pagefactory.EbayHomePage;
import utilities.CommonUtil;
import utilities.DriverUtil;
import utilities.ProjectConfig;
import utilities.Report;

public class EbayHomeScreen {
	private EbayHomePage ebayHomePage;

	public EbayHomeScreen(AndroidDriver<MobileElement> driver) {
		ebayHomePage = new EbayHomePage(driver);
	}

	/*
	 * HOME SCEEN
	 */

	public void validateEbayAppIsOpened() {
		Report.pass("Checking Ebay App Is Opened");
		if (DriverUtil.checkElementIsDisplayed(ebayHomePage.lbl_EbayTitle)) {
			Report.pass("Ebay App Name Found");
		} else {
			Report.fail("Ebay App Name Not Found");
		}
	}

	public void validateBasicElementsInHomeScreen() {
		Report.info("Validating Basic Elements In Home Screen");
		DriverUtil.checkElementIsDisplayed(ebayHomePage.lbl_EbayTitle);
		DriverUtil.checkElementIsDisplayed(ebayHomePage.icn_EbayMenuSlider);
		DriverUtil.checkElementIsDisplayed(ebayHomePage.inp_SearchBox);
		Report.info("Validated Basic Elements In Home Screen");
	}

	public void clickOnSearchBox() {
		DriverUtil.click(ebayHomePage.inp_SearchBox);
		Report.info("Navigating to Search Screen");
		DriverUtil.implicitWait(5);
	}

	public void validateScreenOrientation() {
		Report.info("Updating Screen Orientation");
		if (DriverUtil.getCurrentOrientation().toString().equalsIgnoreCase("PORTRAIT")) {
			DriverUtil.setLandscapeOrientation();
			Report.pass("Updated Screen Orientation to " + DriverUtil.getCurrentOrientation().toString());
		} else {
			DriverUtil.setPortraitOrientation();
			Report.pass("Updated Screen Orientation to " + DriverUtil.getCurrentOrientation().toString());
		}

		if (!DriverUtil.getCurrentOrientation().toString().equalsIgnoreCase("PORTRAIT"))
			DriverUtil.setPortraitOrientation();

		Report.info("Updated Screen Orientation to " + DriverUtil.getCurrentOrientation().toString());
	}

	public void clickOnSeeAllDailyDeals() {
		Report.info("Navigating to Daily Deals Screen");
		DriverUtil.click(DriverUtil.element(ProjectConfig.getProperty("SEE_ALL_DAILY_DEALS")));
		if (DriverUtil.checkElementIsDisplayed(DriverUtil.element(ProjectConfig.getProperty("FEATURED_DEALS")))) {
			Report.pass("User navigated to daily deals section");
		} else {
			Report.fail("User failed to navigate to daily deals section");
		}
	}

	public void validateScrollUpAndScrollUpFunctionalityInDailyDealsScreen() {

		clickOnSeeAllDailyDeals();

		Report.info("Validating Scroll And Swipe Functions");
		DriverUtil.driverStatus();

		DriverUtil.scrollDownUntilElementVisible(
				By.xpath("//android.widget.TextView[@content-desc='Bulk Up Your Pantry']"));

		DriverUtil.swipeDown(2);
		DriverUtil.checkElementIsDisplayed(DriverUtil.element(ProjectConfig.getProperty("TRENDING_DEALS")));
		DriverUtil.swipeDown();
		DriverUtil.checkElementIsDisplayed(DriverUtil.element(ProjectConfig.getProperty("MORE_FEATIURED_DEALS")));

		DriverUtil.swipeRight(5);
		DriverUtil.checkElementIsDisplayed(DriverUtil.element(ProjectConfig.getProperty("AUTOMOTIVE_SECTION")));
		DriverUtil.swipeLeft(5);
		DriverUtil.checkElementIsDisplayed(DriverUtil.element(ProjectConfig.getProperty("FEATURED_DEALS")));
		DriverUtil.swipeUp(4);
		navigateBackToHomeScreenFromUsingSideNavigationScreen();
		Report.info("Validated Scroll And Swipe Functions");
	}

	public void navigateBackToHomeScreenFromUsingSideNavigationScreen(){
		DriverUtil.click(ebayHomePage.icn_EbayMenuSlider);
		DriverUtil.waitForElementToBeDisplayed(ebayHomePage.btn_Home);
		DriverUtil.click(ebayHomePage.btn_Home);
		CommonUtil.sleep(3);
	}

}
