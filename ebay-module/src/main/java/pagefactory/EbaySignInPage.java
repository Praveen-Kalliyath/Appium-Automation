package pagefactory;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class EbaySignInPage extends BasePage {

	public EbaySignInPage(AndroidDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/*
	 * SIGN IN SCREEN
	 */

	@FindBy(xpath = "//android.widget.TextView[@text='SIGN IN']")
	public MobileElement icn_SignIn;

	@FindBy(id = "")
	public MobileElement inp_UserName;

	@FindBy(id = "")
	public MobileElement inp_Password;

	@FindBy(id = "")
	public MobileElement btn_SignIn;
}
