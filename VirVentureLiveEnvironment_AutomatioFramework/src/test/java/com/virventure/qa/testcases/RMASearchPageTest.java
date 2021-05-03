package com.virventure.qa.testcases;

import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
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


public class RMASearchPageTest extends TestBase{

	static LoginPage loginpage;
	static RMASearchPage RMAsearchpage;
	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;

	public RMASearchPageTest() {
		super();
	}

	@BeforeSuite
	public void setupExtentReport() {

		extent= new ExtentReports();
		spark=new ExtentSparkReporter("D:\\IRFAN---\\java program\\VirVentureLiveEnvironment_AutomatioFramework"+
								"\\ExtentReports\\RMASearchExtentReport.html");
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
	public void SearchRMAbyFilterTest() throws InterruptedException, ParseException {
		test=extent.createTest("TC_04 :VV Search RMA by Filter Test");
		RMAsearchpage.RMASearchFilterPage("112-9581391-7525837", "SCSP-1289301", "SCSP-12253", "");
		
	}
	@Test(priority=5)
	public void EditRMASearchPageTest() throws InterruptedException {
		test=extent.createTest("TC_05 :VV Edit RMASearch Page Test");
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
		driver.close();
	}
	
	public static String getBase64ScreenShots() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}
}
