package pagemethods;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import pagefactory.EbaySignInPage;
import utilities.DriverUtil;
import utilities.ExcelUtil;
import utilities.Report;

public class EbaySignInScreen {
	private EbaySignInPage ebaySignInPage;
	private ExcelUtil excelUtil;

	public EbaySignInScreen(AndroidDriver<MobileElement> driver) {
		ebaySignInPage = new EbaySignInPage(driver);
		excelUtil = new ExcelUtil();
		excelUtil.loadExcelData("EBAY", "Record-001");
	}

	/**
	 * SIGN IN SCREEN
	 */

	// TODO: Unable to do due to application restrictions
	public void validateElementsInSignInScreen() {
		Report.skip("Unable to validate sign-in page elements due to app restrictions");
	}

	// TODO: Unable to do due to application restrictions
	public void loginToEbayMobileApp() {
		Report.skip("Unable to sign-in to ebay app due to app restrictions");
	}

	public void navigateToSignInScreenFromHomePage() {
		DriverUtil.checkElementIsDisplayed(ebaySignInPage.icn_EbayMenuSlider);
		DriverUtil.click(ebaySignInPage.icn_EbayMenuSlider);
		DriverUtil.waitForElementToBeDisplayed(ebaySignInPage.icn_SignIn);
		DriverUtil.checkElementIsDisplayed(ebaySignInPage.icn_SignIn);
		DriverUtil.checkElementIsDisplayed(ebaySignInPage.btn_Home);
		DriverUtil.click(ebaySignInPage.btn_Home);
		DriverUtil.implicitWait(2);
		//
	}

	// TODO: Unable to do due to application restrictions
	public void clickOnSignInButton() {
		DriverUtil.click(ebaySignInPage.btn_SignIn);
	}
}
