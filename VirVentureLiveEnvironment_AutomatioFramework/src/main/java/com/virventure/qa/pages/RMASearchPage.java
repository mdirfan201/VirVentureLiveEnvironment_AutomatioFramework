package com.virventure.qa.pages;
import java.text.ParseException;
/***
 * Author Name: Mohammed Irfan
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.mail.imap.Utility.Condition;
import com.virventure.qa.base.TestBase;
import com.virventure.qa.util.JavaScriptUtil;

public class RMASearchPage extends TestBase{

	public RMASearchPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//ul[@class='main-nav']//span[text()='RMA']")
	public WebElement clickRMA;
	@FindBy(xpath="//ul[@class='dropdown-menu']//li//a[contains(text(), 'RMA Search')]")
	public WebElement clickRMASearch;

	@FindBy(xpath="//h2[contains(text(), 'RMA - Search')]")
	public WebElement RMASearchLable;


	//Action: FilterPage
	@FindBy(xpath="//input[@id='filter_order_date_from']")
	public WebElement txtFromDate;
	@FindBy(xpath="//input[@id='filter_order_date_to']")
	public WebElement txtToDate;
	@FindBy(xpath="//textarea[@id='filter_order_id']")
	public WebElement txtOrderId;
	@FindBy(xpath="//input[@id='filter_sku']")
	public WebElement txtSKU;
	//	@FindBy(xpath="//select[@id='rma_type']")
	//	public WebElement txtRMAType;
	@FindBy(xpath="//input[@id='po_number']")
	public WebElement txtPONumber;
	@FindBy(xpath="//input[@id='tracking_number']")
	public WebElement txtTrakinNumber;
	@FindBy(xpath="//input[@id='btn_search']")
	public WebElement FilterBtn;
	@FindBy(xpath="//input[@id='btn_clear']")
	public WebElement ClearBtn;

	//-----Method
	public void ClickRMABtn() {
		clickRMA.click();
	}

	public void ClickRMASearchBtn() {
		clickRMASearch.click();
	}

	public String validateTitlePage() {
		clickRMA.click();
		clickRMASearch.click();
		return driver.getTitle();
	}

	public boolean validateRMALable() {
		clickRMA.click();
		clickRMASearch.click();
		WebElement ele= driver.findElement(By.xpath("//h2[contains(text(), 'RMA - Search')]"));
		JavaScriptUtil.drawBorder(ele, driver);
		return RMASearchLable.isDisplayed();
	}

	public void LsitOfRMASearch() throws InterruptedException {

		clickRMA.click();
		clickRMASearch.click();

		Thread.sleep(50000);
		JavaScriptUtil.WindowScrollBy(driver);
	}
	public void RMASearchFilterByDate(String OrderId, String sku, String PoNo,String TrackingNo) throws InterruptedException, ParseException {

		clickRMA.click();
		clickRMASearch.click();
		txtFromDate.sendKeys("2021-01-01",Keys.TAB);

		txtToDate.sendKeys("2021-04-30",Keys.TAB);
		Thread.sleep(2000);
		txtOrderId.sendKeys(OrderId);
		txtSKU.sendKeys(sku);
		//RMA Reason
		Select sel1= new Select(driver.findElement(By.xpath("//select[@id='filter_rma_type']")));
		sel1.selectByVisibleText("Damaged - Delivered");
		Thread.sleep(2000);
		//RMA Type
		Select sel2= new Select(driver.findElement(By.xpath("//select[@id='rma_type']")));
		sel2.selectByVisibleText("Refund");
		//Case pending from
		Select sel3= new Select(driver.findElement(By.xpath("//select[@id='pending_from']")));
		sel3.selectByIndex(1);
		Thread.sleep(1000);

		txtPONumber.sendKeys(PoNo);
		txtTrakinNumber.sendKeys(TrackingNo);
		Thread.sleep(1000);
		FilterBtn.click();
		Thread.sleep(4000);

		//ClearBtn.click();
	}
	public void RMASearchFilterdata(String OId, String sku, String rmaRea, String rmaTyp) throws InterruptedException {
		clickRMA.click();
		clickRMASearch.click();
		
		txtOrderId.sendKeys(OId);
		txtSKU.sendKeys(sku);
		
		//RMA Reason
		//driver.findElement(By.xpath("//select[@id='filter_rma_type']")).click();
		Select sel1= new Select(driver.findElement(By.xpath("//select[@id='filter_rma_type']")));
		//sel1.selectByVisibleText(rmaRea);
		sel1.selectByValue(rmaRea);
		Thread.sleep(1000);
		//RMA Type
		Select sel2= new Select(driver.findElement(By.xpath("//select[@id='rma_type']")));
		sel2.selectByVisibleText(rmaTyp);
		FilterBtn.click();
		Thread.sleep(1000);
		WebElement element= driver.findElement(By.xpath("//th[contains(text(),'ORDER DATE')]"));
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()",element);
	}
	//	public void RMASearchFilterPageEmpty() {
	//		clickRMA.click();
	//		clickRMASearch.click();
	//		FilterBtn.click();
	//		
	//	}

	public void EditRMASearch() throws InterruptedException {
		boolean isSelected=false;
		clickRMA.click();
		clickRMASearch.click();

		//Thread.sleep(50000);
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@class='btn' and @title='View']")));
		JavaScriptUtil.WindowScrollBy(driver);

		String parentWindowID=driver.getWindowHandle(); //return ID of single browser
		System.out.println("Parent Window ID is====>" + parentWindowID);

		List<WebElement> EditLsit=driver.findElements(By.xpath("//a[@class='btn' and @title='View'] "));
		System.out.println("The tolal size is==>"+EditLsit.size());

		int size=EditLsit.size();
		for(int i=0;i<size-6;i++) {
			isSelected=EditLsit.get(0).isSelected();

			if(!isSelected) {
				EditLsit.get(i).click();
				Thread.sleep(2000);

				Set<String>windowsIDs =driver.getWindowHandles();
				System.out.println("Total number of Id of Multiple Windiows====>" + windowsIDs.size());
				List<String>windowIDsList= new ArrayList(windowsIDs);
				String ListparentID=windowIDsList.get(0);
				String ListchildID=windowIDsList.get(1);
				System.out.println("ListParent window ID is====> "+ ListparentID);
				System.out.println("Listchild window ID is====> "+ ListchildID);

				driver.switchTo().window(ListchildID);
				//driver.manage().window().maximize();
				WebElement ele= driver.findElement(By.xpath("//h2[text()='RMA - Buyer's Cancellation']"));
				JavaScriptUtil.drawBorder(ele, driver);
				Thread.sleep(5000);
				driver.close();
				driver.switchTo().window(ListparentID);

			}
		}


	}
}

/*public void RMASearchFilterPage(String OrderId, String sku, String PoNo,String TrackingNo) throws InterruptedException, ParseException {
	public void RMASearchFilterPage() throws ParseException, InterruptedException {
		clickRMA.click();
		clickRMASearch.click();
		//txtFromDate.sendKeys("2021-01-01",Keys.TAB);
		driver.findElement(By.xpath("//input[@id='filter_order_date_from']")).click();

		String monthYearVal=driver.findElement(By.className("ui-datepicker-title")).getText();
		System.out.println("Current date is=== "+ monthYearVal); //May 2021
		String month=monthYearVal.split("")[0].trim();
		String year=monthYearVal.split("")[1].trim();

		while(!(month.equals("January") && year.equals("2021"))) {

			driver.findElement(By.xpath("//a[@title='Prev']")).click();

			monthYearVal=driver.findElement(By.className("ui-datepicker-title")).getText();
			System.out.println("Current date is=== "+ monthYearVal); //May 2021
			month=monthYearVal.split("")[0].trim();
			year=monthYearVal.split("")[1].trim();	
		}
		driver.findElement(By.xpath("//td//a[text()='1']")).click();
		Thread.sleep(4000);

		/*txtToDate.sendKeys("2021-04-30",Keys.TAB);
		Thread.sleep(2000);
		txtOrderId.sendKeys(OrderId);
		txtSKU.sendKeys(sku);
		Thread.sleep(2000);
		Select sel1= new Select(driver.findElement(By.xpath("//select[@id='filter_rma_type']")));
		sel1.selectByVisibleText("Damaged - Delivered");
		Thread.sleep(2000);
		Select sel2= new Select(driver.findElement(By.xpath("//select[@id='rma_type']")));
		sel2.selectByVisibleText("Refund");
		Select sel3= new Select(driver.findElement(By.xpath("//select[@id='pending_from']")));
		sel3.selectByIndex(1);
		Thread.sleep(3000);

		txtPONumber.sendKeys(PoNo);
		txtTrakinNumber.sendKeys(TrackingNo);
		Thread.sleep(4000);
		FilterBtn.click();
		Thread.sleep(4000);
		JavaScriptUtil.WindowScrollBy(driver);
 */
//ClearBtn.click();

