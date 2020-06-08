package pagefactory;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class EbayItemPage extends BasePage {

	public EbayItemPage(AndroidDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(id = "com.ebay.mobile:id/textview_item_name")
	public MobileElement txt_ItemDescription;

	@FindBy(id = "com.ebay.mobile:id/textview_item_price")
	public MobileElement txt_ItemPrice;

	@FindBy(id = "com.ebay.mobile:id/converted_prices_textview")
	public MobileElement txt_ItemConvertedPrice;

	@FindBy(id = "com.ebay.mobile:id/about_this_item_title")
	public MobileElement txt_AboutItemTitle;

	@FindBy(id = "com.ebay.mobile:id/button_buying_options")
	public MobileElement btn_BuyingOptions;
	
	@FindBy(id = "com.ebay.mobile:id/button_watch")
	public MobileElement btn_Watch;
}
