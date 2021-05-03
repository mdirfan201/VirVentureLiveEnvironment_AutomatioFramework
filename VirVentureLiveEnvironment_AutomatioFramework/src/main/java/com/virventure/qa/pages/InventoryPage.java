package com.virventure.qa.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.virventure.qa.base.TestBase;
import com.virventure.qa.util.JavaScriptUtil;

public class InventoryPage extends TestBase{
	
	public InventoryPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//ul[@class='main-nav']//span[text()='Inventory']")
	public WebElement InventoryBtn;
	@FindBy(xpath="//li[@class='open']//ul[@class='dropdown-menu']//li//a[contains(text(), 'Manage Vendors')]")
	public WebElement ManageVendorBtn;
	@FindBy(xpath="//h1[text()='Manage Vendors']")
	public WebElement ManageVendorLable;
	
	
	public String validateTitle() throws InterruptedException {
		InventoryBtn.click();
		ManageVendorBtn.click();
		String parentWindowID=driver.getWindowHandle();
		System.out.println("Parent Window ID is====>" + parentWindowID);
		
		Set<String>windowsIDs =driver.getWindowHandles();
		System.out.println("Total number of Id of Multiple Windiows====>" + windowsIDs.size());
		List<String>windowIDsList= new ArrayList(windowsIDs);
		String ListparentID=windowIDsList.get(0);
		String ListchildID=windowIDsList.get(1);
		System.out.println("ListParent window ID is====> "+ ListparentID);
		System.out.println("Listchild window ID is====> "+ ListchildID);

		driver.switchTo().window(ListchildID);
		System.out.println("Child window title is ===>" + driver.getTitle());
		Thread.sleep(3000);
		return driver.getTitle();
	}
	
	public boolean validateLable() throws InterruptedException {
		InventoryBtn.click();
		ManageVendorBtn.click();
		String parentWindowID=driver.getWindowHandle();
		System.out.println("Parent Window ID is====>" + parentWindowID);
		
		Set<String>windowsIDs =driver.getWindowHandles();
		System.out.println("Total number of Id of Multiple Windiows====>" + windowsIDs.size());
		List<String>windowIDsList= new ArrayList(windowsIDs);
		String ListparentID=windowIDsList.get(0);
		String ListchildID=windowIDsList.get(1);
		System.out.println("ListParent window ID is====> "+ ListparentID);
		System.out.println("Listchild window ID is====> "+ ListchildID);

		driver.switchTo().window(ListchildID);
		System.out.println("Child window title is ===>" + driver.getTitle());
		Thread.sleep(3000);
		WebElement ele= driver.findElement(By.xpath("//h1[text()='Manage Vendors']"));
		JavaScriptUtil.drawBorder(ele, driver);
		return ManageVendorLable.isDisplayed();
	}
	
}
