package pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLabsLoginPage {
	WebDriver driver;
	
	public SwagLabsLoginPage(WebDriver newDriver) {
		this.driver = newDriver;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(id = "user-name")
	private WebElement usernameField;
	
	@FindBy(id = "password")
	private WebElement passwordField;
	
	@FindBy(id = "login-button")
	private WebElement loginButton;
	
	@FindBy(xpath = "//div[@class = 'login_logo']")
	private WebElement loginLogo;
	
	@FindBy(xpath = "//h3[@data-test = \"error\"]")
	private WebElement errorMessage;
	
	public void inputUsername(String username) {
		this.usernameField.sendKeys(username);
	}
	
	public String getUsernameValue() {
		return this.usernameField.getAttribute("value");
	}
	
	public void inputPassword(String password) {
		this.passwordField.sendKeys(password);
	}
	
	public String getPasswordValue() {
		return this.passwordField.getAttribute("value");
	}
	
	public void clickLoginButton() {
		this.loginButton.click();
	}
	
	public boolean checkLoginLogoText(String text) {
		String logoText = this.loginLogo.getText();
		boolean isExist = logoText.contains(text);
		return isExist;
	}
	
	public boolean checkErrorMessage(String message) {
		String errorMessageText = this.errorMessage.getText();
		boolean isExist = errorMessageText.contains(message);
		return isExist;
	}
	
	
}
