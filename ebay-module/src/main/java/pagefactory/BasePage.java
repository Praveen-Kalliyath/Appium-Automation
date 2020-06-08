package pagefactory;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage {

	public BasePage(AndroidDriver<MobileElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath = "//android.widget.ImageView[@content-desc='eBay']")
	public MobileElement lbl_EbayTitle;

	@FindBy(xpath = "//android.widget.ImageButton[contains(@content-desc,'Main navigation')]")
	public MobileElement icn_EbayMenuSlider;

	@FindBy(xpath = "//android.widget.TextView[@text!='']")
	public List<MobileElement> lst_DropDownItems;

	@FindBy(xpath = "//android.widget.TextView[@text!='']")
	public List<WebElement> lst_DropDownValues;

	@FindBy(xpath = "//android.widget.CheckedTextView[@text='Home']")
	public MobileElement btn_Home;

	@FindBy(xpath = "//android.widget.TextView[@content-desc='Search']")
	public MobileElement btn_MenuSearch;

}
