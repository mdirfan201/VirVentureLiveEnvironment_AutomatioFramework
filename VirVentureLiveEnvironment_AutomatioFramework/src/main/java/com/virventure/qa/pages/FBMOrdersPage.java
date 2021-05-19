package com.virventure.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.virventure.qa.base.TestBase;

public class FBMOrdersPage extends TestBase{

	public FBMOrdersPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//span[normalize-space()='Orders']")
	WebElement clickOrders;
	@FindBy(xpath="//ul[@class='dropdown-menu']//a[text()='FBM Orders']")
	WebElement ClickfbmOrder;
	@FindBy(xpath="//h2[normalize-space()='Manage FBM Orders']")
	WebElement FBMlable;

	//For Filter search
	@FindBy(xpath="//input[@id='filter_order_date_from']")
	public WebElement txtFromdate;
	@FindBy(xpath="//input[@id='filter_order_date_to']")
	public WebElement txtTodate;
	
	@FindBy(xpath="//div[@id='filter_venue_chosen']//input[@value='Select Some Options']")
	public WebElement txtVenue;
	
	@FindBy(xpath="//div[@id='filter_order_status_chosen']//input[@value='Select Some Options']")
	public WebElement txtOrderStatus;
	@FindBy(xpath="//input[@id='filter_sku']")
	public WebElement txtSKU;

	@FindBy(xpath="//span[text()='--Select Order Processing Type--']")
	public WebElement ProcessingType;
	@FindBy(xpath="//div[@id='filter_order_processing_chosen']//input[@type='text']")
	public WebElement txtProcessingType;

	@FindBy(xpath="//input[@id='filter_order_id']")
	public WebElement txtOrderId;
	@FindBy(xpath="//input[@id='filter_po_number']")
	public WebElement txtPONumber;


	@FindBy(xpath="//span[text()='--Select Business Analytics--']")
	public WebElement BusinessAnalytics;
	@FindBy(xpath="//div[@id='filter_business_analytics_chosen']//input[@type='text']")
	public WebElement txtBusinessAnalytics;

	@FindBy(xpath="//span[text()='--Select Vendor Prefix--']")
	public WebElement Vendor;
	@FindBy(xpath="//div[@id='filter_vendor_chosen']//input[@type='text']")
	public WebElement txtVendor;

	@FindBy(xpath="//span[normalize-space()='--Select Value Range--']")
	public WebElement Value;
	@FindBy(xpath="//div[@id='filter_value_chosen']//input[@type='text']")
	public WebElement txtValue;


	@FindBy(xpath="//span[normalize-space()='--Select option--']")
	public WebElement UnshippedOrders;
	@FindBy(xpath="//div[@id='unshipped_order_chosen']//input[@type='text']")
	public WebElement txtUnshippedOrders;


	@FindBy(xpath="//input[@id='search']")
	public WebElement SearchBTN;
	@FindBy(xpath="//input[@id='clearform']")
	public WebElement ClearBTN;




	public String validateFBMTitle() {
		return driver.getTitle();
	}
	public boolean verifyFBMLable() {
		return FBMlable.isDisplayed();
	}

	public void clickFBMOrder() {
		ClickfbmOrder.click();
	}
	public void clickOrder() {
		clickOrders.click();
	}

	public void FBMFilterValidatebyVenue(String vnu, String orst,String orderid) throws InterruptedException {
		clickOrders.click();
		ClickfbmOrder.click();
		
		driver.findElement(By.xpath("//i[@class='fa fa-angle-down']")).click();
		Thread.sleep(2000);
		
		txtVenue.sendKeys(vnu,Keys.ENTER,Keys.TAB);
		
		txtOrderStatus.sendKeys(orst,Keys.ENTER,Keys.TAB);
		
		txtOrderId.sendKeys(orderid,Keys.ENTER);
		
		SearchBTN.click();
		
		Thread.sleep(2000);
		WebElement ele= driver.findElement(By.xpath("//th[contains(text(),'Serial No')]"));
		WebDriverWait wait= new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//th[contains(text(),'Serial No')]")));
		srcollupToSerialNo();
	}
	public void srcollupToSerialNo() {
		WebElement element=driver.findElement(By.xpath("//th[contains(text(),'Serial No')]"));
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}
	
	
	
	
}

/*public void FBMFilterValidatebyOrderStatus(String orst1, String orst2,String orst3,String orst4) throws InterruptedException {
clickOrders.click();
ClickfbmOrder.click();
driver.findElement(By.xpath("//i[@class='fa fa-angle-down']")).click();
Thread.sleep(2000);
txtOrderStatus.click();
txtOrderStatus.sendKeys(orst1,Keys.ENTER,Keys.TAB);
Actions act= new Actions(driver);
act.moveToElement(txtOrderStatus).build().perform();
txtOrderStatus.sendKeys(orst2,Keys.ENTER,Keys.TAB);

act.moveToElement(txtOrderStatus).build().perform();
txtOrderStatus.sendKeys(orst3,Keys.ENTER,Keys.TAB);
act.moveToElement(txtOrderStatus).build().perform();
txtOrderStatus.sendKeys(orst4,Keys.ENTER,Keys.TAB);

}*/