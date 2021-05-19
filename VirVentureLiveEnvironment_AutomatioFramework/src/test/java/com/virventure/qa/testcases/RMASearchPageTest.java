package com.virventure.qa.testcases;

import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.virventure.qa.base.TestBase;
import com.virventure.qa.pages.LoginPage;
import com.virventure.qa.pages.RMASearchPage;
import com.virventure.qa.util.JavaScriptUtil;
import com.virventure.qa.util.TestUtil;


public class RMASearchPageTest extends TestBase{

	static LoginPage loginpage;
	static RMASearchPage RMAsearchpage;
	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;
	String SheetName="RMASearch";
	public RMASearchPageTest() {
		super();
	}

	@BeforeSuite
	public void setupExtentReport() {

		extent= new ExtentReports();
		spark=new ExtentSparkReporter("C:\\Users\\MY-PC.DESKTOP-8EQSD1V\\git\\VirVentureLiveEnvironment_AutomatioFramework\\VirVentureLiveEnvironment_AutomatioFramework\\ExtentReports\\RMASearchExtentReport.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation Test");
		spark.config().setReportName("Mohammed Irfanullah Ansari");
		extent.attachReporter(spark);

		//Setting System/Environment
		extent.setSystemInfo("Author","Mohammed Irfan");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("System","Windows10");
		extent.setSystemInfo("Applicatoin","Eclipse");
		extent.setSystemInfo("Tools","Selenium With Java");
	}
	
	@AfterSuite
	public void tearExtentReport() {
		extent.flush();
		
	}

	@BeforeMethod
	public void setup() {
		test=extent.createTest("Opening the chrome browser");
		initialization();
		loginpage = new LoginPage();
		loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		RMAsearchpage= new RMASearchPage();
	}
	
	@Test(priority=1)
	public void ValidateRMASearchPageTitleTest() throws InterruptedException {
		test=extent.createTest("TC_01 :VV RMA search Page Title Test ");
		Thread.sleep(2000);
		String actualTitle=RMAsearchpage.validateTitlePage();
		String expectedTitle= "RMA - Search";
		Assert.assertEquals(actualTitle, expectedTitle);
		test.info("The Actul Title is ===>"+driver.getTitle());
		
	}
	@Test(priority=2)
	public void ValidateRMASearchPageLableTest() throws InterruptedException {
		test=extent.createTest("TC_02 :VV RMA search Page Lable Test ");
		boolean flag=RMAsearchpage.validateRMALable();
		Assert.assertTrue(flag, "Lable not Diaplayed");
	}
	
	@Test(priority=3)
	public void ValidateListOfRMASearchPageTest() throws InterruptedException {
		test=extent.createTest("TC_03 :VV Validate List Of RMASearch Page Test");
		RMAsearchpage.LsitOfRMASearch();
	}
	
	@Test(priority=4)
	public void SearchRMAbyFilterEmptyFieldsTest() throws InterruptedException, ParseException {
		test=extent.createTest("TC_04 :VV Search RMA by Filter Empty data Test");
		RMAsearchpage.ClickRMABtn();
		RMAsearchpage.ClickRMASearchBtn();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='btn_search']")).click();
		System.out.println(driver.switchTo().alert().getText());
		test.info("The alert msg is====>"+ driver.switchTo().alert().getText());
		
		
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		
	}
	
	
	@Test(priority=5)
	public void SearchRMAbyFilterDateTest() throws InterruptedException, ParseException {
		test=extent.createTest("TC_05 :VV Search RMA by Filter Test");
		RMAsearchpage.RMASearchFilterByDate("112-9581391-7525837", "SCSP-1289301", "SCSP-12253", "");
		
	}
	
	@DataProvider
	public Object[][] getRMASearchData() {
		Object data[][]=TestUtil.getTestData(SheetName);
		return data;
	}
	@Test(priority=6, dataProvider="getRMASearchData")
	public void SearchRMAbyFilterDataTest(String OrderId,String SKU,String RMAReason,String RMAType)throws InterruptedException {
		test=extent.createTest("TC_07 :VV Search RMA by Filter Data Test");
		RMAsearchpage.RMASearchFilterdata(OrderId, SKU, RMAReason, RMAType);
		
	}
	
	@Test(priority=7)
	public void SearchRMAFilterPONumberTest()throws InterruptedException {
		test=extent.createTest("TC_07 :VV Search RMA by Filter PONumber Test");
		SearchRMAbyFilterPONumber();
		
	}
	
	@Test(priority=8)
	public void EditRMASearchPageTest() throws InterruptedException {
		test=extent.createTest("TC_08 :VV Edit RMASearch Page Test");
		RMAsearchpage.EditRMASearch();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, "The TestCase Failed Dur to==>" +result.getName());
			test.log(Status.FAIL, "The Error got in Failed TestCase s==" + result.getThrowable());
			String ScreenshotPath=RMASearchPageTest.getBase64ScreenShots();
			test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotPath).build());
		}else if(result.getStatus()== ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test case Passed="+ result.getName());
			String ScreenshotPath=RMASearchPageTest.getBase64ScreenShots();
			test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotPath).build());
		} else if(result.getStatus()== ITestResult.SKIP) {
			test.log(Status.SKIP, "Test case Passed="+ result.getName());
			String ScreenshotPath=RMASearchPageTest.getBase64ScreenShots();
			test.log(Status.SKIP, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotPath).build());
		} 
	//	driver.close();
	}
	
	public static String getBase64ScreenShots() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}
	
	public static void SearchRMAbyFilterPONumber() throws InterruptedException {
		RMAsearchpage.clickRMA.click();
		RMAsearchpage.clickRMASearch.click();
		//1. YNPO-4 2.SCSP-12253 3. MAHA-1350
		RMAsearchpage.txtPONumber.sendKeys("YNPO-4");
		
		RMAsearchpage.FilterBtn.click();
		Thread.sleep(2000);
		test.info("SS of #PO-NUMBER", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
		scrollupto();
		//RMAsearchpage.ClearBtn.clear();
		
		Thread.sleep(2000);
		driver.navigate().refresh();
		RMAsearchpage.txtPONumber.sendKeys("SCSP-12253");
		RMAsearchpage.FilterBtn.click();
		Thread.sleep(2000);
		test.info("SS of #PO-NUMBER", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
		scrollupto();
		
		Thread.sleep(2000);
		driver.navigate().refresh();
		RMAsearchpage.txtPONumber.sendKeys("MAHA-1350");
		RMAsearchpage.FilterBtn.click();
		Thread.sleep(2000);
		scrollupto();
		//test.info("SS of #PO-NUMBER", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	
	}
	public static void scrollupto() {
		WebElement element= driver.findElement(By.xpath("//th[contains(text(),'ORDER DATE')]"));
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()",element);
	}
}
