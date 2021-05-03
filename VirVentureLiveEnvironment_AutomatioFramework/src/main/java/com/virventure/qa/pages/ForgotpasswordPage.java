package com.virventure.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.virventure.qa.base.TestBase;

public class ForgotpasswordPage extends TestBase{
	public ForgotpasswordPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[normalize-space()='Forgot password?']")
	public WebElement clickForgotPasswordBtn;
	@FindBy(xpath="//h2[normalize-space()='Forgot password?']")
	public WebElement forgotpagelable;
	
	@FindBy(name="userMail")
	public WebElement txtuserEMail;
	
	@FindBy(xpath="//input[@value='SEND ON MAIL']")
	public WebElement clickSendonMail;	
	@FindBy(xpath="//span[normalize-space()='BACK TO SIGN IN']")
	public WebElement ClickBackToSignIn;
	
	public boolean validateforgatepageLable() {
		
		return forgotpagelable.isDisplayed();
		
	}
	
	public void setforgotpassword(String userEmailId) throws InterruptedException {
		clickForgotPasswordBtn.click();
		
		Thread.sleep(3000);

		txtuserEMail.sendKeys(userEmailId);
		clickSendonMail.click();
	}
	

}
