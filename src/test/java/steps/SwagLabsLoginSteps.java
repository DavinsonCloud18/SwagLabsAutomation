package steps;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;
import pageObjectModel.InventoryPage;
import pageObjectModel.SwagLabsLoginPage;

public class SwagLabsLoginSteps {
	WebDriver driver;
	InventoryPage inventoryPage;
	SwagLabsLoginPage loginPage;

	@Given("I Open Browser and Access URL {string}")
	public void i_open_browser_and_access_url(String string) {
		this.driver = new ChromeDriver();
		this.inventoryPage = new InventoryPage(this.driver);
		this.loginPage = new SwagLabsLoginPage(this.driver);

		driver.get(string);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Given("I Open Login Page")
	public void i_open_login_page() {
		String expectedLoginLogoText = "Swag Labs";
		boolean isTextCorrect = loginPage.checkLoginLogoText(expectedLoginLogoText);
		assertTrue(isTextCorrect);
	}

	@When("I click login button")
	public void i_click_login_button() {
		loginPage.clickLoginButton();
	}

	@Then("I redirected to inventory page")
	public void i_redirected_to_inventory_page() {
		String expectedAppLogoText = "Swag Labs";
		boolean isTextCorrect = inventoryPage.checkAppLogoText(expectedAppLogoText);
		assertTrue(isTextCorrect);
	}

	@When("I login using credentials below :")
	public void i_login_using_credentials_below(io.cucumber.datatable.DataTable dataTable) {
		Map<String, String> credentials = dataTable.asMap(String.class, String.class);
		String username = credentials.get("Username");
		String password = credentials.get("Password");
		
		if(username == "" || username == null)
			loginPage.inputUsername("");
		else
			loginPage.inputUsername(username);
		
		if(password == "" || password == null)
			loginPage.inputPassword("");
		else
			loginPage.inputPassword(password);
	}

	@When("I input {string} as username")
	public void i_input_as_username(String string) {
		loginPage.inputUsername(string);
	}

	@When("I input {string} as password")
	public void i_input_as_password(String string) {
		loginPage.inputPassword(string);
	}

	@Then("I see alert user has been locked out")
	public void i_see_alert_user_has_been_locked_out() {
		String expectedMessage = "user has been locked out";
		boolean isMessageCorrect = loginPage.checkErrorMessage(expectedMessage);
		assertTrue(isMessageCorrect);
	}

	@Then("I will see error message")
	public void i_will_see_error_message() {
		String username = loginPage.getUsernameValue();
		String password = loginPage.getPasswordValue();
		String expectedError;

		System.out.println("");
		
		if (username == "" || username == null) {
			expectedError = "Username is required";
		} else if (password == "" || password == null) {
			expectedError = "Password is required";
		} else {
			expectedError = "Username and password do not match any user";
		}
		boolean isMessageCorrect = loginPage.checkErrorMessage(expectedError);
		assertTrue(isMessageCorrect);
	}
	
	@Then("I close my browser")
	public void i_close_my_browser() {
		this.driver.close();
		this.driver.quit();
	}
	
}
