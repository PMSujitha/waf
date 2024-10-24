package com.waf.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage {
			
		@FindBy(xpath = "//a[@id='accountNavbarDropdown']//img[@alt='Melody Sujitha Prabakaran']")
		public static WebElement profile;
				
		@FindBy(xpath = "//a[normalize-space()='My Account']")
		public static WebElement myAccount;
									
	    @FindBy(xpath = "//a[normalize-space()='Change Password']")
	    public static WebElement changePassword;
			 

}
