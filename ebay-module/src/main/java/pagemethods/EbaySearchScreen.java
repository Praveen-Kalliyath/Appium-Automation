package pagemethods;

import java.util.HashMap;
import java.util.List;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import pagefactory.EbaySearchPage;
import utilities.CommonUtil;
import utilities.DataFaker;
import utilities.DriverUtil;
import utilities.ExcelUtil;
import utilities.Log;
import utilities.ProjectConfig;
import utilities.Report;

public class EbaySearchScreen {
	private EbaySearchPage ebaySearchPage;
	private EbayHomeScreen ebayHomeScreen;
	private ExcelUtil excelUtil;
	private static HashMap<String, Object> searchDescriptionMap;

	public EbaySearchScreen(AndroidDriver<MobileElement> driver) {
		ebaySearchPage = new EbaySearchPage(driver);
		ebayHomeScreen = new EbayHomeScreen(driver);
		excelUtil = new ExcelUtil();
		excelUtil.loadExcelData("EBAY", "Record-001");
	}

	/**
	 * SEARCH SCREEN
	 */
	public void checkBasicElementsInRecentSearchScreen() {
		Report.info("Validating Basic Elements In Recent Search Screen");
		clickOnSearchRecentTab();
		DriverUtil.implicitWait(3);
		DriverUtil.checkElementIsDisplayed(ebaySearchPage.edTxt_SearchAnything);
		DriverUtil.checkElementIsDisplayed(ebaySearchPage.btn_Back);
		DriverUtil.checkElementIsDisplayed(ebaySearchPage.btn_Recent);
		DriverUtil.checkElementIsDisplayed(ebaySearchPage.btn_Saved);
		DriverUtil.checkListElementsIsDisplayed(ebaySearchPage.img_MagnifyingGlass);
		DriverUtil.checkElementIsDisplayed(ebaySearchPage.lbl_SearchForAnything);
		DriverUtil.checkElementIsDisplayed(ebaySearchPage.lbl_FindYourNextFavoriteThing);
		Report.info("Validated Basic Elements In Recent Search Screen");
	}

	public void checkBasicElementsInSavedSearchScreen() {
		Report.info("Validating Basic Elements In Saved Search Screen");
		clickOnSearchSavedTab();
		DriverUtil.implicitWait(3);
		DriverUtil.checkElementIsDisplayed(ebaySearchPage.edTxt_SearchAnything);
		DriverUtil.checkElementIsDisplayed(ebaySearchPage.btn_Back);
		DriverUtil.checkElementIsDisplayed(ebaySearchPage.btn_Recent);
		DriverUtil.checkElementIsDisplayed(ebaySearchPage.btn_Saved);
		DriverUtil.checkListElementsIsDisplayed(ebaySearchPage.img_MagnifyingGlass);
		DriverUtil.checkElementIsDisplayed(ebaySearchPage.lbl_SavedSearches);
		DriverUtil.checkElementIsDisplayed(ebaySearchPage.lbl_SavedSearchesInfo);
		DriverUtil.checkElementIsDisplayed(ebaySearchPage.btn_SignInToView);
		Report.info("Validated Basic Elements In Saved Search Screen");
	}

	public void validateListIsPopulatedAfterSettingSearchValue() {
		Report.info("Validating Whether List Items Populated In Search Screen After Setting Search Value");
		clickOnSearchBoxAndSetText(DataFaker.getRandomItemName());
		DriverUtil.waitForElementToBeDisplayed(ebaySearchPage.lst_DropDownValues);
		CommonUtil.softAssertTrue(DriverUtil.checkElementIsDisplayed(ebaySearchPage.lst_DropDownItems.get(0)));
		if (DriverUtil.checkElementIsDisplayed(ebaySearchPage.lst_DropDownItems.get(0))) {
			Report.pass("Search Item List Is Displayed");
		} else {
			Report.fail("Search Item List Is Not Displayed");
		}
		Report.info("Validated Whether List Items Elements In Search Screen After Setting Search Value");
	}

	public void selectValueFromSearchList() {
		Report.info("Selecting Value From Search Result Item List");
		if (DriverUtil.checkElementIsDisplayed(ebaySearchPage.lst_DropDownItems.get(0))) {
			{
				Report.pass("Search Item List Is Displayed");
				Log.info("List  Size: " + DriverUtil.getMobileElementSize(ebaySearchPage.lst_DropDownItems));
			}
			DriverUtil.selectItemValueFromList(ebaySearchPage.lst_DropDownItems,
					excelUtil.getValueForKey("Search Item"));
		} else {
			Report.fail("Search Item List Is Not Displayed");
		}
	}

	private void navigatingBackToQuickSearch() {
		Report.info("Navigating back to quick search screen");
		clickOnMenuSearchButton();
		if (DriverUtil.checkElementIsDisplayed(ebaySearchPage.edTxt_SearchAnything)) {
			Report.pass("User navigated to Quick Search screen from search results");
		} else {
			Report.fail("User failed to navigated to Quick Search screen from search results");
		}
	}

	public void validateNavigatingBackToHomeScreenAndClearSearch() {
		Report.info("Validating Navigation Back To Quick Search Screen From Result Screen");
		CommonUtil.sleep(5);
		DriverUtil.waitForElementToBeDisplayed(DriverUtil.element("SAVE_SEARCH_TOOLTIP"));
		DriverUtil.click(DriverUtil.element("SAVE_SEARCH_TOOLTIP"));
		CommonUtil.sleep(2);
		ebayHomeScreen.navigateBackToHomeScreenFromUsingSideNavigationScreen();
		Report.info("Validated Navigation Back To Home Screen From Result Screen");
	}

	public void searchForItemAndValidateResult() {
		Report.info("Search For Item And Retrieving Details From Search Result Screen");
		CommonUtil.sleep(3);
		navigateToSearchScreenFromHomeScreen();
		DriverUtil.setTextAndPressEnter(ebaySearchPage.edTxt_SearchAnything, excelUtil.getValueForKey("Search Item"));
		CommonUtil.sleep(5);
		DriverUtil.waitForElementToBeDisplayed(DriverUtil.element("SAVE_SEARCH_TOOLTIP"));
		DriverUtil.click(DriverUtil.element("SAVE_SEARCH_TOOLTIP"));
		CommonUtil.sleep(2);

		setSearchDescriptionMap(getSearchResultToHashMap());
	}

	public HashMap<String, Object> getSearchResultToHashMap() {
		CommonUtil.sleep(5);
		DriverUtil.checkListElementsIsDisplayed(ebaySearchPage.lst_SearchResults);

		// Getting Random Item From List Displayed
		int index = CommonUtil.getRandomIndex(DriverUtil.getWebElementElementSize(ebaySearchPage.lst_SearchResults));
		// if (index == 0)
		// index = Integer.parseInt(
		// CommonUtil.getRandomNumberInRange(1,
		// DriverUtil.getElementSize(ebaySearchPage.lst_SearchResults)));

		// Getting Search Result Text
		List<MobileElement> listItem = DriverUtil.listElement("xpath",
				"//androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[" + (index + 1)
						+ "]//android.widget.TextView");

		HashMap<String, Object> searchMap = new HashMap<String, Object>();

		searchMap.put("Item Description", DriverUtil.getText(listItem.get(0)));
		searchMap.put("Item Condition", DriverUtil.getText(listItem.get(1)));
		searchMap.put("Item Price", DriverUtil.getText(listItem.get(2)));

		Log.info("Search Description Map: " + searchMap);

		Log.info("Item Description: " + searchMap.get("Item Description") + " :: Item Price: "
				+ searchMap.get("Item Price") + " :: Item Condition: " + searchMap.get("Item Condition"));

		// Selecting List Item
		DriverUtil.click(ebaySearchPage.lst_SearchResults.get(index));
		CommonUtil.sleep(5);

		return searchMap;
	}

	private void clickOnSearchBoxAndSetText(String text) {
		DriverUtil.checkElementIsDisplayed(ebaySearchPage.edTxt_SearchAnything);
		DriverUtil.click(ebaySearchPage.edTxt_SearchAnything);
		DriverUtil.setText(ebaySearchPage.edTxt_SearchAnything, text);
		DriverUtil.implicitWait(3);
	}

	public void validateBackButtonFunctionalityInSearchScreen() {
		Report.info("Validating Back Button Functionality In Search Screen");
		navigateToSearchScreenFromHomeScreen();
		DriverUtil.waitForElementToBeDisplayed(ebaySearchPage.btn_Back);
		clickOnBackButton();
		DriverUtil.waitForElementToBeDisplayed(ebaySearchPage.lbl_EbayTitle);
		if (DriverUtil.checkElementIsDisplayed(ebaySearchPage.lbl_EbayTitle))
			Report.pass("User navigated back to Ebay home screen on clicking back button in search screen");
		else
			Report.fail("User navigated back to Ebay home screen on clicking back button in search screen");
	}

	public void validateCloseSearchButtonFunctionalityInSearchScreen() {
		Report.info("Validating Close Button Functionality In Search Screen");
		DriverUtil.setText(ebaySearchPage.edTxt_SearchAnything, DataFaker.getRandomItemName());
		DriverUtil.waitForElementToBeDisplayed(ebaySearchPage.lst_DropDownValues);
		DriverUtil.checkElementIsDisplayed(ebaySearchPage.btn_CloseSearch);
		clickOnCloseSearchButton();

		if (DriverUtil.waitForElementToBeNotDisplayed(ebaySearchPage.lst_DropDownValues))
			Report.pass("List is closed on clicking close seach button");
		else
			Report.fail("Failed to close list after clicking close seach button");
	}

	public void navigateToHomeScreenFromSearchScreen() {
		Report.info("Navigatig to Home Screen from Search Screen");
		clickOnBackButton();
	}

	public void navigateToSearchScreenFromHomeScreen() {
		Report.info("Navigatig to Search Screen from Home Screen");
		try {
			if (ebaySearchPage.lbl_EbayTitle.isDisplayed()) {
				ebayHomeScreen.clickOnSearchBox();
			}
		} catch (Exception e) {
			Log.warn(e.getMessage());
			// e.printStackTrace();
		}

		DriverUtil.waitForElementToBeDisplayed(ebaySearchPage.edTxt_SearchAnything);
		if (DriverUtil.checkElementIsDisplayed(ebaySearchPage.edTxt_SearchAnything)) {
			Report.pass("User navigated to Search Screen on clicking search box in home screen");
		} else {
			Report.fail("User navigated to Search Screen on clicking search box in home screen");
		}
	}

	public void validateSwipeGestureFunctionalityInQuickSearch() {
		Report.info("Validating Swipe Gesture Functionality In Search Screen");
		DriverUtil.checkElementIsDisplayed(ebaySearchPage.lbl_SearchForAnything);
		DriverUtil.swipeRight();
		DriverUtil.checkElementIsDisplayed(ebaySearchPage.lbl_SavedSearches);
		DriverUtil.swipeLeft();
		DriverUtil.checkElementIsDisplayed(ebaySearchPage.lbl_SearchForAnything);
		Report.info("Validated Swipe Gesture Functionality In Search Screen");
		navigateToHomeScreenFromSearchScreen();
//		DriverUtil.pressBackKey();
		CommonUtil.sleep(2);
	}

	private void clickOnSearchRecentTab() {
		DriverUtil.implicitWait(3);
		DriverUtil.click(ebaySearchPage.btn_Recent);
	}

	private void clickOnSearchSavedTab() {
		DriverUtil.implicitWait(3);
		DriverUtil.click(ebaySearchPage.btn_Saved);
	}

	private void clickOnBackButton() {
		DriverUtil.implicitWait(3);
		DriverUtil.click(ebaySearchPage.btn_Back);
	}

	private void clickOnCloseSearchButton() {
		DriverUtil.implicitWait(3);
		DriverUtil.click(ebaySearchPage.btn_CloseSearch);
	}

	private void clickOnMenuSearchButton() {
		DriverUtil.waitForElementToBeDisplayed(ebaySearchPage.btn_MenuSearch);
		DriverUtil.click(DriverUtil.element("MENU_SEARCH"));
	}

	public static HashMap<String, Object> getSearchDescriptionMap() {
		return searchDescriptionMap;
	}

	public void setSearchDescriptionMap(HashMap<String, Object> searchDescMap) {
		searchDescriptionMap = searchDescMap;
	}
}
