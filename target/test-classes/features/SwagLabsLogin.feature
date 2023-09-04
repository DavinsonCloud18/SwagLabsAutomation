@Regression @LoginFeature
Feature: Login to SwagLabs

	Background: 
	Given I Open Browser and Access URL "https://www.saucedemo.com/"
	And I Open Login Page
	
	@Positive
	Scenario Outline: Login using valid credential for Standard User 
	When I login using credentials below : 
		| Username		| <username>		|
		| Password		| <password>		|
	And I click login button
	Then I redirected to inventory page
	And I close my browser
	
	Examples:
	| username								| password			|
	| standard_user						| secret_sauce	|
	| performance_glitch_user | secret_sauce	|
	
	Scenario: Login using valid credential for locked out user
	When I input "locked_out_user" as username
	And I input "secret_sauce" as password
	And I click login button
	Then I see alert user has been locked out
	And I close my browser
	
	@Negative
	Scenario Outline: Login using invalid credential
	When I login using credentials below : 
		| Username		| <username>		|
		| Password		| <password>		|
	And I click login button
	Then I will see error message
	And I close my browser
	
	Examples:
	| username								| password				|
	| test_username						| secret_sauce		|
	| locked_out_user					| false password	|
	|  												| secret_sauce		|
	| test										| 								|
	
	