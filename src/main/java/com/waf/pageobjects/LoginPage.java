package com.waf.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
  
public class LoginPage {

	
	@FindBy(xpath = "//input[@placeholder='Enter your Email address']")
	public static WebElement username;
			
	@FindBy(xpath = "//input[@placeholder='Enter your Password']")
	public static WebElement password;
								
    @FindBy(xpath = "//button[@type='submit']")
    public static WebElement login;
    
    @FindBy(xpath = "//a[normalize-space()='Log Out']")
    public static WebElement logout;
    
    @FindBy(xpath = " //*[@id=\"accountNavbarDropdown\"]/div/img")
    public static WebElement myacc;
    
    @FindBy(xpath = "//a[normalize-space()='Free Sign Up']")
    public static WebElement signup;
    
    @FindBy(xpath = "//a[normalize-space()='Click Here']")
    public static WebElement forgotPassword;
 
		 

}
