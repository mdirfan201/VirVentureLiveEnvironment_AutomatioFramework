package com.virventure.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.virventure.qa.base.TestBase;

public class FBMOrdersPage extends TestBase{
	
	public FBMOrdersPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[normalize-space()='Orders']")
	WebElement clickOrders;
	@FindBy(xpath="//ul[@class='dropdown-menu' ]//li//a[text()='FBM Orders']")
	WebElement ClickfbmOrder;
	@FindBy(xpath="//h2[normalize-space()='Manage FBM Orders']")
	WebElement FBMlable;
	
	
	
	
	public String validateFBMTitle() {
		return driver.getTitle();
	}
	public boolean verifyFBMLable() {
		return FBMlable.isDisplayed();
	}
	
	public void clickFBMOrder() {
		clickOrders.click();
	}
	public void clickOrder() {
		clickOrders.click();
	}
}
