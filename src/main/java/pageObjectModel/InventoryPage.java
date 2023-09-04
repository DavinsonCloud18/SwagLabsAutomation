package pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {
	WebDriver driver;
	
	public InventoryPage(WebDriver newDriver) {
		this.driver = newDriver;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(xpath = "//div[@class = 'app_logo']")
	private WebElement appLogo;
	
	public boolean checkAppLogoText(String text) {
		String logoText = this.appLogo.getText();
		boolean isExist = logoText.contains(text);
		return isExist;
	}
	
	
}
