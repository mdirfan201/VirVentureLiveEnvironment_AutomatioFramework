package com.virventure.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.Assert;
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
import com.virventure.qa.pages.ForgotpasswordPage;
import com.virventure.qa.pages.LoginPage;
import com.virventure.qa.util.JavaScriptUtil;
import com.virventure.qa.util.TestUtil;

public class ForgotPageTest extends TestBase{
	
	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;

	static ForgotpasswordPage forgotpasswordpage;
	
	public ForgotPageTest() {
		super();
	}
	@BeforeSuite
	public void ExtentReportsetUp() {

		extent= new ExtentReports();
		spark=new ExtentSparkReporter("D:\\IRFAN---\\java program\\VirVentureLiveEnvironment_AutomatioFramework\\ExtentReports\\LoginPageExtentReport.html");
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
		forgotpasswordpage= new ForgotpasswordPage();
		
	
	}
	/*@Test
	public void validateforgotpageLableTest() throws InterruptedException {
		test=extent.createTest("TC_01 :VV forgot page Lable Test ");
		WebElement elel=driver.findElement(By.xpath("//h2[normalize-space()='Forgot password?']"));
		JavaScriptUtil.drawBorder(elel, driver);
		boolean flag=forgotpasswordpage.validateforgatepageLable();
		Assert.assertTrue(flag);
	}*/
	@Test
	public void forgotPasswordIdTest() throws InterruptedException {
		
		forgotpasswordpage.setforgotpassword(prop.getProperty("forgotpassId"));
		Thread.sleep(3000);
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
