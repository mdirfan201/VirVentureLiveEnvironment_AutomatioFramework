package com.virventure.qa.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	public static void drawBorder(WebElement element,WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.border= '3px solid red'", element);
		
	}
	
	
	public static String getTitleByJS(WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		String title=js.executeScript("return document.title;").toString();
		return title;
	}
	
	public static void clickElementbyJS(WebElement element,WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public static void generateAlertByJS(String message,WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("alert('"+ message +")");
	}
	
	public static void refreshPageByJS(WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("");
	}
	public static void WindowScrollBy(WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("scrollBy(0,900)");
	}
	
	public static void scrolluptoanelementByJS(WebElement element,WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)",element);
	}
}
