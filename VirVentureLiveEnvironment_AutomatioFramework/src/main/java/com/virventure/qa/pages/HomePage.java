package com.virventure.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.virventure.qa.base.TestBase;

public class HomePage extends TestBase {
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[normalize-space()='Orders']")
	WebElement clickOrders;
	@FindBy(xpath="//ul[@class='dropdown-menu' ]//li//a[text()='FBM Orders']")
	WebElement ClickfbmOrder;
	
//	@FindBy(xpath="//a[text()='Virventures Inc.']")
//	public WebElement homepageLogo;
	
	@FindBy(xpath="//h2[contains(text(),'Sales Dashboard')]")
	public WebElement homepageDashboardLable;
	
	@FindBy(xpath="//span[normalize-space()=\"Today's Date\"]")
	public WebElement filterby;
	
	@FindBy(xpath="//div[@id='filter_by_chosen']//input[@type='text']")
	public WebElement txtfilterbychosen;
	
	@FindBy(xpath="//input[@id='filter_date_from']")
	public WebElement txtdatefrom;
	
	@FindBy(xpath="//input[@id='filter_date_to']")
	public WebElement txtdateto;
	
	@FindBy(xpath="//span[normalize-space()='10']")//Clicking Of record
	public WebElement redoredod;
	@FindBy(xpath="//div[@id='top_records_chosen']//input[@type='text']")
	public WebElement txtchoserecord; //Entering record vlue
	
	@FindBy(xpath="//span[normalize-space()='Both']")
	public WebElement OrderType;
	@FindBy(xpath="//div[@id='fba_fbm_chosen']//input[@type='text']")
	public WebElement txtOrderType;     //1.FBA or 2. FBM 
	
	@FindBy(xpath="//div[@id='venue_chosen']//ul[@class='chosen-choices']") 
	public WebElement Venue;
	@FindBy(xpath="//div[@id='venue_chosen']//input[@value='Select Some Options']")
	public WebElement txtVenue;
	
	@FindBy(xpath="//div[@id='vendor_chosen']//a[@class='chosen-single']")
	public WebElement clickVendors;
	@FindBy(xpath="//div[@id='vendor_chosen']//input[@type='text']")
	public WebElement txtVendors;
	
	
	@FindBy(xpath="//span[normalize-space()='All Orders']")
	public WebElement IsPrime;
	@FindBy(xpath="//div[@id='prime_filter_chosen']//input[@type='text']")
	public WebElement txtIsPrime;
	
	@FindBy(xpath="//button[normalize-space()='Select all']")
	public WebElement OrderstatusBtn;
	
	@FindBy(xpath="//div[@id='report_type_chosen']//a[@class='chosen-single']")
	public WebElement ReportType;
	@FindBy(xpath="//div[@id='report_type_chosen']//input[@type='text']")
	public WebElement txtReportType;
	
	@FindBy(xpath="//input[@id='btn_search']")
	public WebElement clickSearchBtn;
	
	//Actios:
		public String validateHomepageTitle() {
			 return driver.getTitle();
		}
		
		public boolean homepageDashboard() {
			return homepageDashboardLable.isDisplayed();
		}
		
		public void clickFBMOrder() {
			clickOrders.click();
		}
		public void clickOrder() {
			clickOrders.click();
		}
		
		public void filterByTodaysDate(String record, String OT) throws InterruptedException {
			redoredod.click();
			txtchoserecord.sendKeys(record,Keys.ENTER);
			Thread.sleep(2000);
			OrderType.click();
			txtOrderType.sendKeys(OT,Keys.ENTER);
			Thread.sleep(3000);
			Venue.click();
			txtVenue.sendKeys("All",Keys.ENTER);
			txtVenue.sendKeys("Amazon.com",Keys.ENTER);
			txtVenue.sendKeys("Walmart.com",Keys.ENTER);
			Thread.sleep(3000);
			clickVendors.click();
			txtVendors.sendKeys("All", Keys.ENTER);
			Thread.sleep(3000);
			IsPrime.click();
			txtIsPrime.sendKeys("prime",Keys.ENTER);
			Thread.sleep(3000);
			OrderstatusBtn.click();
			ReportType.click();
			txtReportType.sendKeys("Top 10 SKUs in Quantity", Keys.ENTER);
			clickSearchBtn.click();
		}
	
		public void filterByDate() throws InterruptedException {
			filterby.click();
			txtfilterbychosen.sendKeys("Last 7",Keys.ENTER);
			redoredod.click();
			txtchoserecord.sendKeys("25",Keys.ENTER);
			
		}
		public static void OrdersModule() {
//			WebElement oder=driver.findElement(By.xpath("//span[text()='Orders']"));
//			Select sel= new Select(oder);
//			sel.selectByVisibleText("FBM Orders");
			driver.findElement(By.xpath("//body/div[@id='navigation']/div[@class='container-fluid']/ul[@class='main-nav']/li[@class='open']/a[1]")).click();
		}

}
