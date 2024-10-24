package com.waf.testcases;

import java.time.Year;
import java.util.ArrayList;
import java.util.Set;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.waf.base.BaseClass;
import com.waf.pageobjects.BotPage;
import com.waf.pageobjects.LoginPage;


public class TestBotMitigation extends BaseClass{
	
	int currentYear;
	
	static Logger logger = Logger.getLogger(TestBotMitigation.class);
	
	public void signin() {
		PageFactory.initElements(driver, LoginPage.class);
		driver.navigate().to("https://staging.prophaze.com/login");
		LoginPage.username.sendKeys("MELODY.PRABAKARAN@PROPHAZE.COM");
		LoginPage.password.sendKeys("F1ndCount");
		LoginPage.login.click();
	}
	
	public void botMenu() {
		PageFactory.initElements(driver, BotPage.class);
		BotPage.botMenu.click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://staging.prophaze.com/botmanager");
		logger.info("Bot Mitigation menu leads to the respective page");
	}
	
	public void logo() {
		PageFactory.initElements(driver, BotPage.class);
		Boolean botLogo = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete "+ "&& typeof arguments[0].naturalWidth != \"undefined\" "	+ "&& arguments[0].naturalWidth > 0", BotPage.logo);
    	Assert.assertEquals(botLogo, true);
		logger.info("Prophaze Logo is displayed");
	}
	
	public void year() {
		PageFactory.initElements(driver, BotPage.class);
		currentYear = Year.now().getValue();
		String curYear =String.valueOf(currentYear);
		String footerYear = BotPage.footer.getText();
		if (footerYear.contains(curYear)) {
			logger.info("Footer contains the current year");			
		}else {
			logger.warn("Footer does not contain the current year");
		}
	}
	
	public void fbPage() {
		PageFactory.initElements(driver, BotPage.class);
		BotPage.fbIcon.click();
		Set<String> childWindow = driver.getWindowHandles();
		for (String ch : childWindow) {
			driver.switchTo().window(ch);
		}
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/prophazetech/");
		logger.info("Facebook icon leads to Prophaze facebook page");
		ArrayList<String> fbtab = new ArrayList<String>(driver.getWindowHandles());    
		driver.switchTo().window(fbtab.get(1));
		driver.close();
		driver.switchTo().window(fbtab.get(0));
		
	}
	
	public void twitterPage() {
		PageFactory.initElements(driver, BotPage.class);
		BotPage.twitterIcon.click();
		Set<String> childWindow = driver.getWindowHandles();
		for (String ch : childWindow) {
			driver.switchTo().window(ch);
		}
		if(driver.getCurrentUrl().contains("//x.com/prophaze/")) {
		logger.info("Twitter icon leads to Prophaze twitter page");
		}
		ArrayList<String> twttab = new ArrayList<String>(driver.getWindowHandles());    
		driver.switchTo().window(twttab.get(1));
		driver.close();
		driver.switchTo().window(twttab.get(0));
	}
		
	public void selectCluster() {
		String dName = properties.getProperty("domainName");
		PageFactory.initElements(driver, BotPage.class);
		Select clusterDrop = new Select(BotPage.cluster);
		clusterDrop.selectByVisibleText(dName);
	}
	
	public void selectDomain() {
		PageFactory.initElements(driver, BotPage.class);
		Select domainDrop = new Select(BotPage.domain);
		domainDrop.selectByVisibleText("sample.kubewaf.com");
	}
	
	public void apply() {
		PageFactory.initElements(driver, BotPage.class);
		BotPage.apply.click();
		logger.info("WAF is applied for the selected domain and cluster");
	}
	
	public void modeSelection()  {
		PageFactory.initElements(driver, BotPage.class);
		String status = BotPage.learningMode.getAttribute("class");
		if (status.contains("toggle-off active")) {
		BotPage.learningMode.click();
		driver.switchTo().activeElement();
		BotPage.confirm.click();
		BotPage.modeOk.click();
		logger.info("Enabled active mode");
		}else {
			logger.info("Already enabled active mode");
		}
	
	}
	
	public void aboutBotMitigation()  {
		PageFactory.initElements(driver, BotPage.class);
		BotPage.learnMore.click();
		logger.info("User can learn more about BotMitigation");
		ArrayList<String> lMore = new ArrayList<String>(driver.getWindowHandles());    
		driver.switchTo().window(lMore.get(1));
		Assert.assertEquals(driver.getCurrentUrl(), "https://docs.prophaze.com/user-manual/bot-analysis");
		logger.info("Learn more.. link leads to Bot Analysis page");
		driver.close();
		driver.switchTo().window(lMore.get(0));
	}
	
	public void homeLink() {
		PageFactory.initElements(driver, BotPage.class);
		BotPage.home.click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://staging.prophaze.com/home");
		logger.info("Home link leads to the appropriate page");
		driver.navigate().back();
	}
	
	public void botProtection(){
		PageFactory.initElements(driver, BotPage.class);
		String status = BotPage.enableButton.getAttribute("class");
		if (status.contains("toggle-off active")) {
		BotPage.enableButton.click();
		driver.switchTo().activeElement();
		BotPage.proceedButton.click();
		logger.info("Bot protection is enabled");
		}else {
			logger.info("Bot protection already is enabled");
		}	
	}
	
	public void captchLessChallenge()  {
		PageFactory.initElements(driver, BotPage.class);
		String selectedColor = "#1e73be";
		String rgbaColor = BotPage.capchaless.getCssValue("background-color");
		Color color = Color.fromString(rgbaColor);
		String actualColor = color.asHex();
		if(selectedColor.equals(actualColor)){
    		logger.info("Captcha-less challenge is the default selection");
    		}
        else {
			BotPage.capchaless.click();
			logger.info("Captcha-less challenge is activated");
		}
		((JavascriptExecutor) driver).executeScript("window.open()");
  		ArrayList<String> bot = new ArrayList<String>(driver.getWindowHandles());    //get(1)---Tab(1)
  		driver.switchTo().window(bot.get(1));
  		driver.get("https://sample.kubewaf.com");
  		if(BotPage.advanced.isEnabled()) {
  			BotPage.advanced.click();
  			if(BotPage.proceed.isEnabled()) {
  				BotPage.proceed.click();
  			}
  		}
  		String title = driver.getTitle();
  		Assert.assertEquals(title,"Error 404 (Not Found)!!1");
  		logger.info("Captcha-less challenge is functional" );
  		driver.close();
  		driver.switchTo().window(bot.get(0));
	}
	
	public void captchChallenge()  {
		PageFactory.initElements(driver, BotPage.class);
		String selectedColor = "#1e73be";
		String rgbaColor = BotPage.capcha.getCssValue("background-color");
		Color color = Color.fromString(rgbaColor);
		String actualColor = color.asHex();
		if(selectedColor.equals(actualColor)){
    		logger.info("Captcha challenge is the default selection");
    		}
        else {
			BotPage.capcha.click();
			logger.info("Captcha challenge is activated");
        }
        logger.info("Captcha challenge needs manual testing");
	}
	
	
	@Test
	public void verifyBotMitigation()   {
		testCase=extentReport.createTest("Verify Bot Mitigation Page");
		signin();
		selectCluster();
		selectDomain();
		apply();
		botMenu();
		logo();
		fbPage();
		twitterPage();
		year();
		modeSelection();
		homeLink();
		aboutBotMitigation();
		botProtection();
		captchLessChallenge();
		captchChallenge();
		logger.info("End of Bot Mitigation Test case");
		testCase.log(Status.PASS, "Bot Mitigation works properly");
	}
}
