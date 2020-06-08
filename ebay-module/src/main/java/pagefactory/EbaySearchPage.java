package pagefactory;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class EbaySearchPage extends BasePage {
	/*
	 * SEARCH SCREEN
	 */
	public EbaySearchPage(AndroidDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(className = "android.widget.EditText")
	public MobileElement edTxt_SearchAnything;

	@FindBy(xpath = "//androidx.appcompat.app.ActionBar.Tab[@content-desc='Recent, tab 1 of 2']/android.widget.TextView")
	public MobileElement btn_Recent;

	@FindBy(xpath = "//androidx.appcompat.app.ActionBar.Tab[@content-desc='Saved, tab 2 of 2']/android.widget.TextView")
	public MobileElement btn_Saved;

	@FindBy(xpath = "//android.widget.ImageButton[@content-desc='Back']")
	public MobileElement btn_Back;

	@FindBy(className = "com.ebay.mobile:id/search_close_btn")
	public MobileElement btn_CloseSearch;

	// ***********
	// RECENT TAB
	// ************
	@FindBy(className = "android.widget.ImageView")
	public List<WebElement> img_MagnifyingGlass;

	@FindBy(xpath = "//*[@text='Search for anything']")
	public MobileElement lbl_SearchForAnything;

	@FindBy(xpath = "//*[@text='Find your next favorite thing']")
	public MobileElement lbl_FindYourNextFavoriteThing;

	// ***********
	// SAVED TAB
	// ************
	@FindBy(xpath = "//*[@text='Save Searches & Members']")
	public MobileElement lbl_SavedSearches;

	@FindBy(xpath = "//*[@text='Save Searches and Members to find them even faster.']")
	public MobileElement lbl_SavedSearchesInfo;

	@FindBy(className = "android.widget.ImageView")
	public MobileElement btn_SignInToView;

	// **************
	// RESULT PAGE
	// **************

	@FindBy(id="com.ebay.mobile:id/button_sort")
	public MobileElement btn_Sort;
	

	@FindBy(id="com.ebay.mobile:id/button_filter")
	public MobileElement btn_Filter;
	
	@FindBy(xpath="//android.widget.Button[@text='SAVE']")
	public MobileElement btn_Save;
	
	@FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout")
	public List<WebElement> lst_SearchResults;
	
	
}
