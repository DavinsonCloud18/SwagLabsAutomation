package steps;

import static org.junit.Assert.*;

import java.util.List;

import org.openqa.selenium.WebDriver;

import helper.DriverProvider;
import io.cucumber.java.en.*;
import pageObjectModel.CartPage;
import pageObjectModel.InventoryPage;

public class CartSteps {

	WebDriver driver = DriverProvider.getDriver();
	InventoryPage inventoryPage = new InventoryPage(driver);
	CartPage cartPage = new CartPage(driver);

	@When("I click an item in Inventory Page")
	public void i_click_an_item_in_inventory_page() {

		inventoryPage.addAnItemToCart();
	}

	@Then("I see number one displayed in the cart icon")
	public void i_see_number_one_displayed_in_the_cart_icon() {
		int badgeNumber = inventoryPage.getBadgeCartNumber();
		assertEquals(badgeNumber, 1);
	}

	@When("I click the cart icon")
	public void i_click_the_cart_icon() {
		inventoryPage.clickCartIcon();
	}

	@Then("I will see only one item")
	public void i_will_see_only_one_item() {
		int totalItems = cartPage.getTotalItems();
		assertEquals(totalItems, 1);
	}

	@Then("The product name is the same as we added before")
	public void the_product_name_is_the_same_as_we_added_before() {
		String inventoryProductName = inventoryPage.getItemsName(0);
		String cartProductName = cartPage.getItemName(0);
		assertEquals(inventoryProductName, cartProductName);
	}

	@When("I click multiple item in Inventory Page")
	public void i_click_multiple_item_in_inventory_page() {
		inventoryPage.addMultipleItemToCart();
	}

	@Then("I see the number of clicked item displayed in the cart icon")
	public void i_see_the_number_of_clicked_item_displayed_in_the_cart_icon() {
		int badgeCartNumber = inventoryPage.getBadgeCartNumber();
		int totalItems = inventoryPage.getTotalAddedItem();
		assertEquals(badgeCartNumber, totalItems);
	}

	@Then("I will see all the clicked item")
	public void i_will_see_all_the_clicked_item() {
		int inventoryItem = inventoryPage.getBadgeCartNumber();
		int cartItem = cartPage.getTotalItems();
		assertEquals(inventoryItem, cartItem);
	}

	@Then("All The product name is the same as we added before")
	public void all_the_product_name_is_the_same_as_we_added_before() {
		List<String> inventoryItemsName = inventoryPage.getAllItemsName();
		List<String> cartItemsName = cartPage.getAllItemsName();
		assertEquals(inventoryItemsName, cartItemsName);
	}

	@When("I click remove button of the added item")
	public void i_click_remove_button_of_the_added_item() {
		String currentURL = driver.getCurrentUrl();
		if (currentURL.contains("inventory")) {
			inventoryPage.removeItemFromCart(0);
		} else {
			cartPage.removeItemFromCart(0);
		}
	}

	@Then("The number will be missing from the cart")
	public void the_number_will_be_missing_from_the_cart() {
		String currentURL = driver.getCurrentUrl();
		int badgeNumber;
		if (currentURL.contains("inventory")) {
			badgeNumber = inventoryPage.getBadgeCartNumber();
		} else {
			badgeNumber = cartPage.getBadgeCartNumber();
		}
		assertEquals(badgeNumber, 0);
	}

	@When("I click remove button of some added item")
	public void i_click_remove_button_of_some_added_item() {
		String currentURL = driver.getCurrentUrl();
		if (currentURL.contains("inventory")) {
			inventoryPage.removeSomeItem();
		} else {
			cartPage.removeSomeItem();
		}
	}

	@Then("The number in cart icon decrease as much as the removed item")
	public void the_number_in_cart_icon_decrease_as_much_as_the_removed_item() {
		String currentURL = driver.getCurrentUrl();

		int totalItemBeforeRemove;
		int totalItemLeft;
		int totalRemoved;

		if (currentURL.contains("inventory")) {
			totalItemBeforeRemove = inventoryPage.getTotalBeforeRemove();
			totalItemLeft = inventoryPage.getBadgeCartNumber();
			totalRemoved = inventoryPage.getTotalRemoved();
		} else {
			totalItemBeforeRemove = cartPage.getTotalBeforeRemove();
			totalItemLeft = cartPage.getBadgeCartNumber();
			totalRemoved = cartPage.getTotalRemoved();
		}
		assertSame(totalRemoved, totalItemBeforeRemove - totalItemLeft);

	}

	@Then("I will be redirected to Cart Page")
	public void i_will_be_redirected_to_cart_page() {
		boolean isTitleCorrect = cartPage.checkPageIndicator("Your Cart");
		assertTrue(isTitleCorrect);
	}

	@Then("The number will be missing from the cart icon")
	public void the_number_will_be_missing_from_the_cart_icon() {
		String currentURL = driver.getCurrentUrl();
		int cartNumber;
		;
		if (currentURL.contains("inventory")) {
			cartNumber = inventoryPage.getBadgeCartNumber();
		} else {
			cartNumber = cartPage.getBadgeCartNumber();
		}
		assertEquals(cartNumber, 0);
	}

	@Then("The item will be missing from the cart page")
	public void the_item_will_be_missing_from_the_cart_page() {
		String removedItemName = cartPage.getRemovedItemName();
		boolean isExist = cartPage.CheckItemExistence(removedItemName);
		assertFalse(isExist);
	}

}
