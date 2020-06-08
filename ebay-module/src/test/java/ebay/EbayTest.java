package ebay;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import libraries.Data;
import pagemethods.EbayHomeScreen;
import pagemethods.EbayItemScreen;
import pagemethods.EbaySearchScreen;
import pagemethods.EbaySignInScreen;
import utilities.BaseUtil;
import utilities.CommonUtil;
import utilities.Report;
import utilities.RetryAnalyzer;

public class EbayTest extends BaseUtil implements Data {
	private EbayHomeScreen ebayHomeScreen;
	private EbaySearchScreen ebaySearchScreen;
	private EbaySignInScreen ebaySignInScreen;
	private EbayItemScreen ebayItemScreen;

	public void init() {
		launchDriver();
		ebayHomeScreen = new EbayHomeScreen(getDriver());
	}

	@BeforeMethod(onlyForGroups = { "Single" })
	public void setup() {
		init();
	}

	@Test(groups = { "Single" })
	public void validateEbayMobileAppHomeScreenTest() {
		try {
			Report.createChildNode(new Object() {
			}.getClass().getEnclosingMethod().getName());

			ebayHomeScreen.validateEbayAppIsOpened();
			ebayHomeScreen.validateBasicElementsInHomeScreen();
		} catch (Exception e) {
			Report.exception(e);
			CommonUtil.softAssertFail("Failed to validate elements in Ebay Home Screen");
		} finally {
			Report.info("Completed validating elements in Ebay Home Screen");
		}
	}

	@Test(groups = { "Single" })
	public void validateSignInToEbayMobileAppTest() {
		try {
			Report.createChildNode(new Object() {
			}.getClass().getEnclosingMethod().getName());

			ebaySignInScreen = new EbaySignInScreen(getDriver());
			ebaySignInScreen.navigateToSignInScreenFromHomePage();
			ebaySignInScreen.validateElementsInSignInScreen();
			ebaySignInScreen.loginToEbayMobileApp();
		} catch (Exception e) {
			Report.exception(e);
			CommonUtil.softAssertFail("Failed to login to Ebay App");
		} finally {
			Report.info("Completed Login To Ebay App");
		}
	}

	@Test(groups = { "Single" })
	public void validateSearchScreenTest() {
		try {
			Report.createChildNode(new Object() {
			}.getClass().getEnclosingMethod().getName());

			ebaySearchScreen = new EbaySearchScreen(getDriver());
			ebaySearchScreen.navigateToSearchScreenFromHomeScreen();
			ebaySearchScreen.validateBackButtonFunctionalityInSearchScreen();
			ebaySearchScreen.navigateToSearchScreenFromHomeScreen();
			ebaySearchScreen.checkBasicElementsInRecentSearchScreen();
			ebaySearchScreen.checkBasicElementsInSavedSearchScreen();
		} catch (Exception e) {
			Report.exception(e);
			CommonUtil.softAssertFail("Failed to valdiate Search screen functionalities");
		} finally {
			Report.info("Completed valdiation of Search screen functionalities");
		}
	}

	@Test(groups = { "Single" })
	public void validateScreenOrientaionInHomeScreenTest() {
		try {
			Report.createChildNode(new Object() {
			}.getClass().getEnclosingMethod().getName());

			ebayHomeScreen.validateScreenOrientation();
		} catch (Exception e) {
			Report.exception(e);
			CommonUtil.softAssertFail("Failed to valdiate Swipe function in Search screen");
		} finally {
			Report.info("Completed valdiation of Swipe screen functionality");
		}
	}

	@Test(groups = { "Single" })
	public void validateSwipeAndScrollGestureFunctionalityInHomeScreenTest() {
		try {
			Report.createChildNode(new Object() {
			}.getClass().getEnclosingMethod().getName());

			ebaySearchScreen = new EbaySearchScreen(getDriver());
			ebaySearchScreen.navigateToSearchScreenFromHomeScreen();
			ebaySearchScreen.validateSwipeGestureFunctionalityInQuickSearch();

			ebayHomeScreen.validateScrollUpAndScrollUpFunctionalityInDailyDealsScreen();

		} catch (Exception e) {
			Report.exception(e);
			CommonUtil.softAssertFail("Failed to valdiate Swipe function in Search screen");
		} finally {
			Report.info("Completed valdiation of Swipe screen functionality");
		}
	}

	@Test(groups = { "Single" })
	public void validateQuickSearchFunctionalityInEbayMobileAppTest() {
		try {
			Report.createChildNode(new Object() {
			}.getClass().getEnclosingMethod().getName());

			ebaySearchScreen = new EbaySearchScreen(getDriver());
			ebaySearchScreen.navigateToSearchScreenFromHomeScreen();
			ebaySearchScreen.validateListIsPopulatedAfterSettingSearchValue();
			ebaySearchScreen.selectValueFromSearchList();
			ebaySearchScreen.validateNavigatingBackToHomeScreenAndClearSearch();
		} catch (Exception e) {
			Report.exception(e);
			e.printStackTrace();
			CommonUtil.softAssertFail("Failed to valdiate Search screen functionalities");
		} finally {
			Report.info("Completed valdiation of Search screen functionalities");
		}
	}

	@Test(groups = { "Single" })
	public void validateSearchForItemAndMatchDetailsFromResultWithItemDetailsInEbayMobileAppTest() {
		try {
			Report.createChildNode(new Object() {
			}.getClass().getEnclosingMethod().getName());

			ebaySearchScreen = new EbaySearchScreen(getDriver());
			ebaySearchScreen.searchForItemAndValidateResult();

			ebayItemScreen = new EbayItemScreen(getDriver());
			ebayItemScreen.validateElementsInItemScreen();
			ebayItemScreen.getItemDetails();
			ebayItemScreen.compareSeachResultDetailsWithItemDetails();

		} catch (Exception e) {
			Report.exception(e);
			e.printStackTrace();
			CommonUtil.softAssertFail("Failed to valdiate Search result");
		} finally {
			Report.info("Completed valdiation of Search result");
		}
	}

	@Test(groups = { "Single" })
	public void addItemToCartAndValidateDetailsInCheckOutScreenTest() {
		try {
			Report.createChildNode(new Object() {
			}.getClass().getEnclosingMethod().getName());

			Report.skip("Unable to add items to cart as user is not able to signIn.");
		} catch (Exception e) {
			Report.exception(e);
			e.printStackTrace();
			CommonUtil.softAssertFail("Failed to valdiate details in CheckOut Screen");
		} finally {
			Report.info("Completed valdiation of CheckOut Details");
		}
	}

	@Test(groups = { "Regression" }, retryAnalyzer = RetryAnalyzer.class)
	public void e2eEbayRegressionTest() {
		try {
			init();
			validateEbayMobileAppHomeScreenTest();
			validateScreenOrientaionInHomeScreenTest();
			validateSwipeAndScrollGestureFunctionalityInHomeScreenTest();
			validateSignInToEbayMobileAppTest();
			validateSearchScreenTest();
			validateQuickSearchFunctionalityInEbayMobileAppTest();
			validateSearchForItemAndMatchDetailsFromResultWithItemDetailsInEbayMobileAppTest();
			addItemToCartAndValidateDetailsInCheckOutScreenTest();
		} catch (Exception e) {
			Report.exception(e);
			e.printStackTrace();
			CommonUtil.softAssertFail("Failed to valdiate e2e Regression Test");
		} finally {
			if (Report.failurePresent())
				Report.logInParentNode(Status.FAIL,
						"Completed valdiation of e2e Regression Test with failures. Please find the result in the Report");
			else
				Report.logInParentNode(Status.PASS,
						"Completed valdiation of e2e Regression Test with Successfully. Please find the result in the Report");
		}
	}

}
