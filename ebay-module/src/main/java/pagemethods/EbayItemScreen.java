package pagemethods;

import java.util.HashMap;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import pagefactory.EbayItemPage;
import utilities.CommonUtil;
import utilities.DriverUtil;
import utilities.Log;
import utilities.ProjectConfig;
import utilities.Report;

public class EbayItemScreen {

	private EbayItemPage ebayItemPage;
	private static HashMap<String, Object> itemDescriptionHashMap;

	public EbayItemScreen(AndroidDriver<MobileElement> driver) {
		ebayItemPage = new EbayItemPage(driver);
	}

	public void validateElementsInItemScreen() {
		Report.info("Validating Basic Elements In Items Screen");
		DriverUtil.checkElementIsDisplayed(ebayItemPage.txt_ItemDescription);
		DriverUtil.checkElementIsDisplayed(ebayItemPage.txt_ItemPrice);
		DriverUtil.checkElementIsDisplayed(ebayItemPage.txt_ItemConvertedPrice);
		DriverUtil.checkElementIsDisplayed(ebayItemPage.btn_BuyingOptions);
		DriverUtil.checkElementIsDisplayed(ebayItemPage.btn_Watch);
		Report.info("Validated Basic Elements In Items Screen");
	}

	public void getItemDetails() {
		Report.info("Retrieving Item Details From Items Screen");
		HashMap<String, Object> itemMap = new HashMap<String, Object>();

		itemMap.put("Item Description", DriverUtil.getText(ebayItemPage.txt_ItemDescription));
		itemMap.put("Item Price", DriverUtil.getText(ebayItemPage.txt_ItemConvertedPrice));
		itemMap.put("Item Condition", "New");

		Log.info("Item Description Map: " + itemMap);

		setItemDescriptionHashMap(itemMap);
		Report.info("Retrieved Item Details From Items Screen");

	}

	public void compareSeachResultDetailsWithItemDetails() {
		Report.info("Comparing Details Of Seach Result With Item Screen");
		CommonUtil.compareHashMap(EbaySearchScreen.getSearchDescriptionMap(), getItemDescriptHashMap());
		CommonUtil.compareActualWithExpected("Item Description: ",
				EbaySearchScreen.getSearchDescriptionMap().get("Item Description").toString(),
				getItemDescriptHashMap().get("Item Description"));
		CommonUtil.compareActualWithExpected("Item Price: ",
				CommonUtil.roundNumber(CommonUtil.getDigitsFromString(
						EbaySearchScreen.getSearchDescriptionMap().get("Item Price").toString())),
				CommonUtil.roundNumber(CommonUtil.getDigitsFromString(
						getItemDescriptHashMap().get("Item Price").toString())));
		CommonUtil.compareActualWithExpected("Item Condition: ",
				EbaySearchScreen.getSearchDescriptionMap().get("Item Condition"),
				getItemDescriptHashMap().get("Item Condition"));
	}

	public static HashMap<String, Object> getItemDescriptHashMap() {
		return itemDescriptionHashMap;
	}

	public void setItemDescriptionHashMap(HashMap<String, Object> itemDescHashMap) {
		itemDescriptionHashMap = itemDescHashMap;
	}
}
