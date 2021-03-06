package com.virventure.qa.testcases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.virventure.qa.pages.InventoryPage;
import com.virventure.qa.pages.LoginPage;

public class InventoryPageTest extends TestBase{
	
	static InventoryPageTest inventorypagetest;
	static InventoryPage inventorypage;
	static LoginPage loginpage;
	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;

	public InventoryPageTest() {
		super();
	}

	@BeforeSuite
	public void setupExtentReport() {
		extent= new ExtentReports();
		spark= new ExtentSparkReporter("D:\\IRFAN---\\java program\\VirVentureLiveEnvironment_AutomatioFramework\\"
				+"ExtentReports\\InventoryPageTestExtentReprot.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Inventory Page Test");
		spark.config().setReportName("Mohammed Irfan Ansari");
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
		test=extent.createTest("Opening the Chrome Broswer");
		initialization();
		loginpage= new LoginPage();
		loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		inventorypage= new InventoryPage();
	}
	
	@Test(priority=1)
	public void validateInventoryManageVendorTitleTest() throws InterruptedException {
		test=extent.createTest("TC_01 :VV validate Inventory Manage-Vendor TitleTest");
		String expectedTitle="Manage Suppliers";
		String actualTitle=inventorypage.validateTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Title not matched");
		test.info("The Excted and Actual Tilte is matched"+ driver.getTitle());
	}
	
	@Test(priority=2)
	public void validateInventoryManageVendorLableTest() throws InterruptedException {
		test=extent.createTest("TC_02 :VV validate Inventory Manage-Vendor Lable Test");
		boolean flag=inventorypage.validateLable();
		Assert.assertTrue(flag);
		driver.switchTo().defaultContent();
	}
	
	
	@Test(priority=3)
	public void InventoryListPageTest() throws InterruptedException{
		test=extent.createTest("TC_03 :VV Inventory List Page Test");
		inventorypage.InventoryList();
		test.info("Test case passed");
	}
	@Test(priority=4)
	public void ManageVendorFilterSearchPageTestEmpty() throws InterruptedException {
		test=extent.createTest("TC_04 :VV ManageVendor Filter Search Page Empty Test");
		inventorypage.clickInventoryBtn();
		inventorypage.clickManageVendorBtn();
		Thread.sleep(3000);
		
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
		driver.findElement(By.xpath("//input[@name='filter']")).click();
		System.out.println("The alter msg is==> "+ driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		test.info("Test case passed");
	}
	@Test(priority=5)
	public void ManageVendorFilterSearchPageTest() throws InterruptedException {
		test=extent.createTest("TC_04 :VV ManageVendor Filter Search Page with data Test");
		inventorypage.ManageVendorFilter("BIBO", "7021 Wolftown-Hood Road");
		test.info("Test case passed");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, "The testcase failed due to ===>"+ result.getName());
			test.log(Status.FAIL, "The testcase failed Error ===>"+ result.getThrowable());
			test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
		}else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "The testcase failed due to ===>"+ result.getName());
			test.log(Status.SKIP, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "The testcase passed ===>"+ result.getName());
			test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
		}
		driver.close();
		driver.quit();
	}
	
	public static String getBase64ScreenShots() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}
}
