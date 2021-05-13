package com.virventure.qa.testcases;

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
import com.virventure.qa.pages.HomePage;
import com.virventure.qa.pages.LoginPage;

public class HomePageTest extends TestBase {
	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;
	
	static LoginPage loginpage;
	static HomePage  homepage;
	static HomePageTest homepagetest;
	public HomePageTest() {
		super();
	}

	@BeforeSuite
	public void ExtentReportsetUp() {
		
		extent= new ExtentReports();
		spark=new ExtentSparkReporter("D:\\IRFAN---\\java program\\VirVentureLiveEnvironment_AutomatioFramework\\ExtentReports\\HomePageExtentReport.html");
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
		initialization();
		loginpage = new LoginPage();
		loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		homepage= new HomePage();
		homepagetest = new HomePageTest();
		
	}
	@Test(priority=1)
	public void VVHomePageTitleTest(){
		test=extent.createTest("TC_01 :VV HomePage Title Test ");
		String ExceptedTitle="Sales Dashboard";
		String ActualTitle=homepage.validateHomepageTitle();
		test.info("Actual login Page title is==> "+ driver.getTitle());
		Assert.assertEquals(ActualTitle, ExceptedTitle, "Title not matched");
	}
	
	@Test(priority=2)
	public void VVHomepageLableTest() {
		test=extent.createTest("TC_02 :VV HomePage Lable Test ");	
		boolean lable=homepage.homepageDashboard();
		Assert.assertTrue(lable);
	}
	
	@Test(priority=3)
	public void SalesDashboardSearchTest() throws InterruptedException {
		test=extent.createTest("TC_03 :VV HomePage Sales Dashboard Search Test ");
		homepage.filterByTodaysDate(prop.getProperty("ofRecord"), prop.getProperty("orderTypeFBA"));
		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test cases MethodName Failed ==>" + result.getName());
			test.log(Status.FAIL, "Test cases MethodName Error is==>" + result.getThrowable());
			String screenshotPath=LoginPageTest.getBase64ScreenShots();
			test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "Test cases MethodName Skiped ==>" + result.getName());
			test.log(Status.SKIP, "Test cases MethodName Skiped ==>" + result.getThrowable());	
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test cases MethodName==>" + result.getName());
			String screenshotPath=LoginPageTest.getBase64ScreenShots();
			test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());
		}
		
		driver.quit();
	}
	
	public static String getBase64ScreenShots() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}
	
}
