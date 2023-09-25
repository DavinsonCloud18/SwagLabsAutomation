package pageObjectModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
	
	@FindBy(xpath = "//button[contains(@class,'btn_inventory')]")
	private List<WebElement> allItemsButton;
	
	@FindBy(xpath = "//div[@class = 'inventory_item_name']")
	private List<WebElement> listItemsName;
	
	@FindBy(xpath = "//span[@class = 'shopping_cart_badge']")
	private WebElement cartIcon;
	
	@FindBy(xpath = "//button[normalize-space() = 'Remove']")
	private List<WebElement> allRemoveButton;
	
	private List<Integer> allSelectedItems;
	private int totalItemBeforeRemove;
	private int totalRemoved;
	
	
	public boolean checkAppLogoText(String text) {
		String logoText = this.appLogo.getText();
		boolean isExist = logoText.contains(text);
		return isExist;
	}
	
	public void addAnItemToCart() {
		int totalItems = allItemsButton.size();
		Random rand = new Random();
		int selectedIndex = rand.nextInt(totalItems);
		allItemsButton.get(selectedIndex).click();
	}
	
	public int getBadgeCartNumber() {
		String badgeNumber;
		int convertedNumber;
		
		try {
			badgeNumber = cartIcon.getText();
			convertedNumber = Integer.valueOf(badgeNumber);
		}catch(Exception e){
			convertedNumber = 0;
		}
		
		return convertedNumber;
	}
	
	public void clickCartIcon() {
		this.cartIcon.click();
	}
	
	public String getItemsName(int indexNum) {
		WebElement selectedItems = this.listItemsName.get(indexNum);
		return selectedItems.getText();
	}
	
	public void addMultipleItemToCart() {
		int totalItems = allItemsButton.size();
		allSelectedItems = new ArrayList<Integer>();
		List<Integer> numArray = new ArrayList<Integer>();
		
		for(int i=0;i<totalItems;i++) {
			numArray.add(i);
		}
		
		Collections.shuffle(numArray);
		Random rand = new Random();
		int upperBound = rand.nextInt(totalItems);
		for(int i=0;i<=upperBound;i++) {
			int index = numArray.get(i);
			allItemsButton.get(index).click();
			allSelectedItems.add(index);
		}
	}
	
	public int getTotalAddedItem() {
		return this.allRemoveButton.size();
	}
	
	public List<String> getAllItemsName(){
		this.driver.navigate().back();
		List<String> allNames = new ArrayList<String>();
		for(int i=0;i<this.allSelectedItems.size();i++) {
			int selectedItem = allSelectedItems.get(i);
			String itemName = listItemsName.get(selectedItem).getText();
			allNames.add(itemName);
		}
		this.cartIcon.click();
		return allNames;
	}
	
	public void removeItemFromCart(int index) {
		this.allRemoveButton.get(index).click();
	}
	
	public void removeSomeItem() {
		this.totalItemBeforeRemove = this.getTotalAddedItem();
		Random rand = new Random();
	    this.totalRemoved = rand.nextInt(this.getTotalAddedItem());
	    for(int i=0;i<totalRemoved;i++) {
	    	this.removeItemFromCart(i);
	    }
	}
	
	public int getTotalBeforeRemove() {
		int totalItem;
		try {
			totalItem = this.totalItemBeforeRemove;
		}catch(Exception e) {
			totalItem = 0;
		}
		return totalItem;
	}
	
	public int getTotalRemoved() {
		int totalItem;
		try {
			totalItem = this.totalRemoved;
		}catch(Exception e) {
			totalItem = 0;
		}
		return totalItem;
	}
}
