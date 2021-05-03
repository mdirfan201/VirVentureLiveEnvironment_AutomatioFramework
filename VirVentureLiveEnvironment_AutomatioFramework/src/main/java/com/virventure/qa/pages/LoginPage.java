package com.virventure.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.virventure.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@id='uname']")
	public WebElement txtusername;
	
	@FindBy(id="pass")//input[@id='pass']
	public WebElement txtpassword;
	
	@FindBy(xpath="//input[@type='submit' and @name='login']")
	public WebElement loginBtn;

	@FindBy(xpath="//a[normalize-space()='Virventures Inc.']")
	public WebElement virvenrureLogo;
	
	
	//Actios:
	public String validateLoginpageTitle() {
		 return driver.getTitle();
	}
	
	public boolean validateVVimage() {
		return virvenrureLogo.isDisplayed();
	}
	
	public HomePage login(String un, String pwd) {
		txtusername.sendKeys(un);
		txtpassword.sendKeys(pwd);
		loginBtn.click();
		
		return new HomePage();
	}
	public void logout() {
		//Select select= new  Select(driver.findElement(By.xpath("//a[normalize-space()='IrfanullahAnsari']")));
		driver.findElement(By.xpath("//a[normalize-space()='IrfanullahAnsari']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Sign out']")).click();
	}
	
}
