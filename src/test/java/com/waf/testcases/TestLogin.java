package com.waf.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.waf.base.BaseClass;
import com.waf.pageobjects.LoginPage;
import com.waf.pageobjects.ProfilePage;


public class TestLogin extends BaseClass{

	static Logger logger = Logger.getLogger(TestLogin.class);


	public void masked() {
		PageFactory.initElements(driver, LoginPage.class);
		logger.info("Verification of password visibility");
		String fieldType = LoginPage.password.getAttribute("type");
		Assert.assertEquals(fieldType,"password");
		logger.info("Password is masked");
	}

	public void login() throws IOException {
		String user=null;

		logger.info("Logging in to the application");
		PageFactory.initElements(driver, LoginPage.class);
		File src = new File(".\\Excel\\LoginCredentials.xlsx");
		FileInputStream finput = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(finput);
		XSSFSheet sheet = wb.getSheetAt(0);

		Cell cell;

		for(int i=0; i<sheet.getLastRowNum()-1;i++) {
			cell = sheet.getRow(i+1).getCell(0);
			LoginPage.username.clear();
			LoginPage.username.sendKeys(cell.getStringCellValue());
			user = cell.getStringCellValue();

			cell = sheet.getRow(i+1).getCell(1);
			LoginPage.password.clear();
			LoginPage.password.sendKeys(cell.getStringCellValue());

			LoginPage.login.click();
			if(!user.contains("@prophaze.com")) {
				logger.warn("User does not enter the peroper email id");
			}
			wb.close();
		}	
	}


	public void signout()  {
		PageFactory.initElements(driver, LoginPage.class);
		LoginPage.myacc.click();
		Actions action = new Actions(driver);
		action.moveToElement(LoginPage.logout);
		String color = LoginPage.logout.getCssValue("color");
		String backcolor = LoginPage.logout.getCssValue("background-color");

		if(!color.equals(backcolor)){
			logger.info("Text is highlighted!");
		}
		else{
			logger.info("Text is not highlighted!");
		}
		
		WebDriverWait waitlt = new WebDriverWait(driver,  Duration.ofSeconds(10));
		waitlt.until(ExpectedConditions.elementToBeClickable(LoginPage.logout));
		LoginPage.logout.click();
	
		logger.info("Verification to check whether user can perform any actions after logging out");
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(LoginPage.login));

		try {
			driver.get("https://staging.prophaze.com/home");
			Assert.assertNotEquals(driver.getCurrentUrl(), "https://staging.prophaze.com/home");
			driver.get("https://staging.prophaze.com/clusters");
			Assert.assertNotEquals(driver.getCurrentUrl(), "https://staging.prophaze.com/clusters");
			driver.get("https://staging.prophaze.com/ddos-attack-and-traffic-comparison-chart");
			Assert.assertNotEquals(driver.getCurrentUrl(), "https://staging.prophaze.com/ddos-attack-and-traffic-comparison-chart");
			driver.get("https://staging.prophaze.com/dashboard-attacks-data");
			Assert.assertNotEquals(driver.getCurrentUrl(), "https://staging.prophaze.com/dashboard-attacks-data");
			driver.get("https://staging.prophaze.com/graphMetrics");
			Assert.assertNotEquals(driver.getCurrentUrl(), "https://staging.prophaze.com/graphMetrics");
			logger.info("Application restricted the access after logged out");
		}
		catch(Exception e){
			logger.warn(e);
			logger.warn("Application is able to log in after logged out");
		}

	}

	public void signup() {
		PageFactory.initElements(driver, LoginPage.class);
		LoginPage.signup.click();
		String register = driver.getCurrentUrl();
		if(register.contains("contact")) {
		 logger.info("Navigating to the contact us page");
		}else {
			logger.warn("Navigating to the inappropriate page");
		}
	}

	public void forgotPass() {
		PageFactory.initElements(driver, LoginPage.class);
		driver.navigate().back();
		LoginPage.forgotPassword.click();
		String reset = driver.getCurrentUrl();
		if(reset.contains("password/reset")) {
		 logger.info("Navigating to the password resetting page");
		}else {
			logger.warn("Navigating to the inappropriate page");
		}
	}

	public void profilePage() {
		PageFactory.initElements(driver, ProfilePage.class);
		ProfilePage.profile.click();
		ProfilePage.myAccount.click();
		ProfilePage.profile.click();
		ProfilePage.changePassword.click();

	}
	 private void clearCache() {
	        // Execute JavaScript to clear the browser cache
	        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
	        ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");
	        ((JavascriptExecutor) driver).executeScript("caches.keys().then(function(keys) { keys.forEach(function(key) { caches.delete(key); }); });");
	    }
	

	
	@Test
	public void verifyLogin() throws IOException 
	{
		testCase=extentReport.createTest("Verify Login Page");
		masked();
		logger.info("Login process begins");
		login();
		logger.info("Logged in with valid credentials");
		logger.info("Logging out process begins");
		signout();
		logger.info("Logged out successfully");
		logger.info("Verification of navigating to the contact-us page");
		signup();
		logger.info("Verification of navigating to resetting password page");
		forgotPass();
		logger.info("End of Login Test case");
		clearCache();
		testCase.log(Status.PASS, "User can able to login successfully");
	}
}






