package pageObjectModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	WebDriver driver;
	
	public CartPage(WebDriver newDriver) {
		this.driver = newDriver;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(xpath = "//a[contains(@id,'title_link')]")
	private List<WebElement> itemNames;
	
	@FindBy(xpath = "//span[contains(@class,'title')]")
	private WebElement cartPageIndicator;
	
	@FindBy(xpath = "//div[@class = 'inventory_item_name']")
	private List<WebElement> listItemsName;
	
	@FindBy(xpath = "//button[normalize-space() = 'Remove']")
	private List<WebElement> allRemoveButton;
	
	@FindBy(xpath = "//span[@class = 'shopping_cart_badge']")
	private WebElement cartIcon;
	
	private int totalItemBeforeRemove;
	private int totalRemoved;
	private int lastRemoved;
	private List<Integer> removedItems;
	
	
	public String getItemName(int orderNum) {
		List<WebElement> childElements = itemNames.get(orderNum).findElements(By.xpath("*")); 
		return childElements.get(0).getText();
	}
	
	public int getTotalItems() {
		return this.itemNames.size();
	}
	
	public boolean checkPageIndicator(String param) {
		String pageIndicator = this.cartPageIndicator.getText();
		return pageIndicator.contains(param);
	}
	
	public List<String> getAllItemsName(){
		List<String> allNames = new ArrayList<String>();
		for(int i=0;i<this.listItemsName.size();i++) {
			String itemName = listItemsName.get(i).getText();
			allNames.add(itemName);
		}
		return allNames;
	}
	
	public void removeItemFromCart(int index) {
		this.lastRemoved = index;
		this.allRemoveButton.get(index).click();
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
	
	public int getTotalAddedItem() {
		return this.allRemoveButton.size();
	}
	
	public void removeSomeItem() {
		totalItemBeforeRemove = getTotalAddedItem();
		removedItems = new ArrayList<Integer>();
		
		Random rand = new Random();
	    totalRemoved = rand.nextInt(totalItemBeforeRemove);
	    for(int i=0;i<=totalRemoved;i++) {
	    	removeItemFromCart(0);
	    	removedItems.add(i);
	    }
	    totalRemoved += 1;
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
	
	public String getRemovedItemName() {
		if(listItemsName.size() == 0) {
			return "";
		}
		else {
			String removedItemName = listItemsName.get(lastRemoved).getText();
			return removedItemName;
		}
	}
	
	public boolean CheckItemExistence(String itemName) {
		int flag = 0;
		for(int i=0;i<listItemsName.size();i++) {
			String selectedItem = listItemsName.get(i).findElement(By.xpath("*")).getText();
			if(selectedItem.equals(itemName))
				flag += 1;
		}
		return flag == 1 ? true : false;
	}
	
	
}
