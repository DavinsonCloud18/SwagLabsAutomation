@Regression @Cart
Feature: Swag Labs Cart Feature

	Background: 
	Given I Open Browser and Access URL "https://www.saucedemo.com/"
	And I input "standard_user" as username
	And I input "secret_sauce" as password
	And I click login button

# Case : Add Item
  @Positive
  Scenario: Add single item to the cart
    When I click an item in Inventory Page
    Then I see number one displayed in the cart icon
    When I click the cart icon
    Then I will be redirected to Cart Page
    And I will see only one item
    And The product name is the same as we added before
    And I close my browser
    
  Scenario: Add multiple item to the cart
    When I click multiple item in Inventory Page
    Then I see the number of clicked item displayed in the cart icon
    When I click the cart icon
    Then I will be redirected to Cart Page
    And I will see all the clicked item
    And All The product name is the same as we added before
    And I close my browser
 
 # Case : Remove Item from Inventory Page
	Scenario: remove an item from the cart in Inventory Page
    When I click an item in Inventory Page
    Then I see number one displayed in the cart icon
    When I click remove button of the added item
    Then The number will be missing from the cart icon
    And I close my browser
	
	Scenario: remove multiple item from the cart in Inventory Page
    When I click multiple item in Inventory Page
    Then I see the number of clicked item displayed in the cart icon
    When I click remove button of some added item
    Then The number in cart icon decrease as much as the removed item
    And I close my browser
    
 # Case : Remove Item from Cart Page
	Scenario: remove an item from the cart in Cart Page
    When I click an item in Inventory Page
    Then I see number one displayed in the cart icon
    When I click the cart icon
    Then I will be redirected to Cart Page
    When I click remove button of the added item
    Then The item will be missing from the cart page
    And The number will be missing from the cart icon
    And I close my browser
	
	Scenario: remove multiple item from the cart in Cart Page
    When I click multiple item in Inventory Page
    Then I see the number of clicked item displayed in the cart icon
    When I click the cart icon
    Then I will be redirected to Cart Page
    When I click remove button of some added item
    Then The number in cart icon decrease as much as the removed item
    And I close my browser
