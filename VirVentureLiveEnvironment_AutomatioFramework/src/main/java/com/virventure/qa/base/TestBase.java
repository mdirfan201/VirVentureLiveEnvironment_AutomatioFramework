package com.virventure.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.virventure.qa.util.TestUtil;
import com.virventure.qa.util.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public TestBase() {
		try {
			FileInputStream ip= new FileInputStream("C:\\Users\\MY-PC.DESKTOP-8EQSD1V\\git\\VirVentureLiveEnvironment_AutomatioFramework\\VirVentureLiveEnvironment_AutomatioFramework\\src\\main\\java\\com\\virventure\\qa\\config\\config.properties");
			prop= new Properties();
			prop.load(ip);
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	
	public static void initialization() {
		String browsername=prop.getProperty("browser");
		if(browsername.equalsIgnoreCase("chrome")) {
			//System.setProperty("webdriver.chrome.driver", "D:\\irfan\\selenium\\Lstest-All-Driver\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}else if(browsername.equalsIgnoreCase("FireFox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\IRFAN---\\java program\\VirVentureLiveEnvironment_AutomatioFramework\\Browser-Driver\\geckodriver.exe");
			driver= new FirefoxDriver();
		}else if(browsername.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.edge.driver", "D:\\IRFAN---\\java program\\VirVentureLiveEnvironment_AutomatioFramework\\Browser-Driver\\msedgedriver.exe");
			driver= new FirefoxDriver();
		}else {
			
			System.out.println("browser key not available in properties file");
		}
		
		e_driver= new EventFiringWebDriver(driver);
		WebEventListener eventListner= new WebEventListener();
		e_driver.register(eventListner);
		driver= e_driver;
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICT_WAIT_TIMEOUT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
	
	public static void getScreenShot(String testMethodName) {
		TakesScreenshot ts= (TakesScreenshot)driver;
		File src =ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("C:\\Users\\MY-PC.DESKTOP-8EQSD1V\\git\\VirVentureLiveEnvironment_AutomatioFramework\\"+ 
								"VirVentureLiveEnvironment_AutomatioFramework\\ScreenShot\\"+ testMethodName+"_" + ".png"));
		} catch (IOException e) {
			System.out.println("Error while taking ScreenShots");
			e.printStackTrace();
		}
		
	}

}
