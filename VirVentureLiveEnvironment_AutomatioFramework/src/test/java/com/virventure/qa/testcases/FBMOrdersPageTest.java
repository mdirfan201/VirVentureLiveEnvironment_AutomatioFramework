package com.virventure.qa.testcases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import com.virventure.qa.pages.FBMOrdersPage;
import com.virventure.qa.pages.LoginPage;
import com.virventure.qa.util.JavaScriptUtil;
import com.virventure.qa.util.TestUtil;

	
public class FBMOrdersPageTest extends TestBase{
	static LoginPage loginpage;
	static FBMOrdersPage fbmorderpage;
	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;
	String SheetName="FBMOrder";
	public FBMOrdersPageTest() {
		super();
	}
	@BeforeSuite
	public void ExtentReportsetUp() {

		extent= new ExtentReports();
		spark=new ExtentSparkReporter("C:\\Users\\MY-PC.DESKTOP-8EQSD1V\\git\\VirVentureLiveEnvironment_AutomatioFramework\\VirVentureLiveEnvironment_AutomatioFramework\\ExtentReports\\FBMOrdersExtentReport.html");
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
		fbmorderpage= new FBMOrdersPage();
	}
	
	@Test(priority=1)
	public void FBMOrderPageTitleTest() throws InterruptedException {
		test=extent.createTest("TC_01 :VV FBM Order Page Title Test ");
		driver.findElement(By.xpath("//span[normalize-space()='Orders']")).click();
		driver.findElement(By.xpath("//ul[@class='dropdown-menu' ]//li//a[text()='FBM Orders']")).click();
		Thread.sleep(3000);
		String actualTitle=driver.getTitle();
		String expectedTitle= "Manage FBM Orders";
		Assert.assertEquals(actualTitle, expectedTitle);
		test.info("The Actul Title is ===>"+driver.getTitle());
		//driver.close();
	}
	
	@Test(priority=2)
	public void FBMOrderPageLAbleTest() throws InterruptedException {
		test=extent.createTest("TC_02 :VV LFBM Order Page Label Test ");
		driver.findElement(By.xpath("//span[normalize-space()='Orders']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//ul[@class='dropdown-menu' ]//li//a[text()='FBM Orders']")).click();
		Thread.sleep(3000);
		WebElement ele= driver.findElement(By.xpath("//h2[normalize-space()='Manage FBM Orders']"));
		JavaScriptUtil.drawBorder(ele, driver);
		boolean flag= driver.findElement(By.xpath("//h2[normalize-space()='Manage FBM Orders']")).isDisplayed();
		Assert.assertTrue(flag);
		//driver.close();
	}
	
	@Test(priority=3)
	public void FBMOrderPageListTest() throws InterruptedException {
		test=extent.createTest("TC_03 :VV LFBM Order Page List Test ");
		driver.findElement(By.xpath("//span[normalize-space()='Orders']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//ul[@class='dropdown-menu' ]//li//a[text()='FBM Orders']")).click();
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("scrollBy(0,900)");
		Thread.sleep(5000);
		//driver.close();
	}
	@DataProvider
	public Object[][] getFBMData() {
		Object data[][]=TestUtil.getTestData(SheetName);
		return data;
	}
	@Test(priority=4, dataProvider="getFBMData")
	public void FBMOrderPageFilterByVenueTest(String Venue,String OrderStatus,String OrderID) throws InterruptedException {
		test=extent.createTest("TC_04 :VV LFBM Order Page Filter By Venue Test ");
		fbmorderpage.FBMFilterValidatebyVenue(Venue,OrderStatus,OrderID);
		
	}
	
	@Test(priority=5)
	public void FBMOrderPageEditTest() throws InterruptedException {
		boolean isSelected=false;
		test=extent.createTest("TC_05 :VV LFBM Order Page List Test ");
		driver.findElement(By.xpath("//span[normalize-space()='Orders']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//ul[@class='dropdown-menu' ]//li//a[text()='FBM Orders']")).click();
		Thread.sleep(2000);
		String parentWindowID=driver.getWindowHandle(); //return ID of single browser
		System.out.println("Parent Window ID is====>" + parentWindowID);
		
		List<WebElement> updatelist=driver.findElements(By.xpath("//a[@title='Update']"));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		int size=updatelist.size();
		System.out.println("The tolal size is==>"+updatelist.size());
		
		for (int i=0; i<size-6; i++) {
			isSelected=updatelist.get(0).isSelected();
			
			if(!isSelected) {
				updatelist.get(i).click();
				Thread.sleep(2000);
				Set<String> windowsIDs=driver.getWindowHandles();
				System.out.println("Total number of Id of Multiple Windiows====>" + windowsIDs.size());
				List<String> windowIDsList= new ArrayList(windowsIDs);
				String ListparentID=windowIDsList.get(0);
				String ListchildID=windowIDsList.get(1);
				System.out.println("ListParent window ID is====> "+ ListparentID);
				System.out.println("Listchild window ID is====> "+ ListchildID);
				
				driver.switchTo().window(ListchildID);
				System.out.println("Child window title is ===>" + driver.getTitle());
				driver.manage().window().maximize();
				Thread.sleep(3000);
				WebElement NoOfOrderList=driver.findElement(By.xpath("//h3[contains(text(), 'Orders List of')]"));
				JavaScriptUtil.drawBorder(NoOfOrderList, driver);
				test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
				Thread.sleep(2000);
				JavascriptExecutor js= (JavascriptExecutor) driver;
				js.executeScript("scrollBy(0,700)");
				Thread.sleep(2000);
				
				WebElement status= driver.findElement(By.xpath("//select[@id='order_status']"));
				Select select =new Select(status);
				select.selectByValue("7");
				//select.selectByVisibleText("Cancelled");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@title='second']")).click();
				
				Thread.sleep(2000);
				
				System.out.println("The message of Alert box is==>"+driver.switchTo().alert().getText());
				test.info("The message of alert box is===>"+driver.switchTo().alert().getText());
				driver.switchTo().alert().accept();
				Thread.sleep(3000);
				JavascriptExecutor jse= (JavascriptExecutor) driver;
				jse.executeScript("scrollBy(0,1200)");
				WebElement ele=driver.findElement(By.xpath("//select[@id='order_status']"));
				Coordinates coordinate= ((org.openqa.selenium.interactions.Locatable) ele).getCoordinates();
				coordinate.inViewPort();
				JavaScriptUtil.drawBorder(ele, driver);
				Thread.sleep(3000);
				test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
				
				driver.switchTo().window(ListchildID);
				driver.close();
				driver.switchTo().window(ListparentID);
				Thread.sleep(3000);
				//driver.findElement(By.xpath("//a[@id='hide_icon']")).click();
				//driver.findElement(By.xpath("//input[@id='filter_order_id']")).sendKeys("");
			}
		}
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test cases MethodName Failed ==>" + result.getName());
			test.log(Status.FAIL, "Test cases MethodName Error is==>" + result.getThrowable());
			test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "Test cases MethodName Skiped ==>" + result.getName());
			test.log(Status.SKIP, "Test cases MethodName Skiped ==>" + result.getThrowable());	
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test cases MethodName==>" + result.getName());
			//String screenshotPath=LoginPageTest.getBase64ScreenShots();
			test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
		}
		//driver.close();
	}
	
	public static String getBase64ScreenShots() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}
	
	/*@Test(priority=5, dataProvider="getFBMData")
	//@Test
	public void FBMOrderPageFilterByOrderStatusTest(String OrderStatus1,String OrderStatus2,String OrderStatus3,String OrderStatus4) throws InterruptedException {
		test=extent.createTest("TC_04 :VV LFBM Order Page Filter By Venue Test ");
		fbmorderpage.FBMFilterValidatebyOrderStatus(OrderStatus1,OrderStatus2,OrderStatus3,OrderStatus4);
		
	}*/
}
