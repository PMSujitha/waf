package com.waf.testcases;

import java.time.Year;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.waf.base.BaseClass;
import com.waf.pageobjects.OnBoardPage;


public class TestOnBoard extends BaseClass{

	boolean button;
	int currentYear;
	boolean buttonNext;
	
	static Logger logger = Logger.getLogger(TestOnBoard.class);
	

	public void signin() {
		driver.get("https://staging.prophaze.com/login");
		OnBoardPage.username.sendKeys("MELODY.PRABAKARAN@PROPHAZE.COM");
		OnBoardPage.password.sendKeys("F1ndCount");
		OnBoardPage.login.click();
	}
	
	public void logo() {
		try {
		Boolean proLogo = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete "+ "&& typeof arguments[0].naturalWidth != \"undefined\" "	+ "&& arguments[0].naturalWidth > 0", OnBoardPage.logo);
		Assert.assertEquals(proLogo, true);
		logger.info("Prophaze Logo is displayed");}
		catch(Exception e) {
			logger.info(e);
			}
		}
	
	
	public void addApplication() {
		PageFactory.initElements(driver, OnBoardPage.class);
		logger.info("Adding new application begins");
		try {
		OnBoardPage.addApplication.click();
		}
		catch(Exception e) {
			logger.info(e);
			}
		logger.info("clicked");
	}
	
	public void year() {
		currentYear = Year.now().getValue();
		String curYear =String.valueOf(currentYear);
		String footerYear = OnBoardPage.footer.getText();
		if (footerYear.contains(curYear)) {
			logger.info("Footer contains the current year");			
		}else {
			logger.warn("Footer does not contain the current year");
		}
	}
	
	public void fbPage() {
		OnBoardPage.fbIcon.click();
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
		OnBoardPage.twitterIcon.click();
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
		
	public void Onboard() 	{
		String cName = properties.getProperty("clusterName");
		String dName = properties.getProperty("domainName");
		String ePoint = properties.getProperty("endpoint");
		String baseUri = properties.getProperty("uri");
		
		String colorDomain = OnBoardPage.domainIcon.getCssValue("color");
        String bgcolorDomain = OnBoardPage.domainIcon.getCssValue("background-color");
		
        if(!colorDomain.equals(bgcolorDomain)){
		logger.info("Domain icon is highlighted!");
		}
		else{
      	logger.info("Domain icon is not highlighted!");
		}
        
		OnBoardPage.domainName.sendKeys("autowaf&!");
		boolean message = OnBoardPage.popup.isDisplayed();
		Assert.assertEquals(message, true);
		logger.info("Error message is enabled when the user gives the domain name in wrong format");
		
		button = OnBoardPage.nextDomain.isEnabled();
		Assert.assertEquals(button, false);
		logger.info("Button is disabled due to incorrect domain name format");
		OnBoardPage.domainName.clear();
		OnBoardPage.domainName.sendKeys(dName);
		buttonNext = OnBoardPage.nextDomain.isEnabled();
		Assert.assertEquals(buttonNext, true);
		logger.info("Button is enabled as user is given correct domain name format");
		OnBoardPage.nextDomain.click();
		
		String colorEndPoint = OnBoardPage.endPointIcon.getCssValue("color");
        String backcolorEndpoint = OnBoardPage.domainIcon.getCssValue("background-color");
		if(!colorEndPoint.equals(backcolorEndpoint)){
		logger.info("Endpoint icon is highlighted!");
		}
		else{
      	logger.info("Endpoint icon is not highlighted!");
		}
		OnBoardPage.endPoint.sendKeys(ePoint);
			
		String checked = OnBoardPage.httpsProtocol.getAttribute("checked");
		Assert.assertEquals(checked, null);
    	logger.info("Https protocol is chosen by default");
		OnBoardPage.httpProtocol.click();
		OnBoardPage.bothProtocol.click();
		OnBoardPage.httpsProtocol.click();
		logger.info("All the protocol are selectable individually");
		OnBoardPage.nextIp.click();
		
		String colorGeoIcon = OnBoardPage.geoLocationIcon.getCssValue("color");
        String backcolorGeoIcon = OnBoardPage.geoLocationIcon.getCssValue("background-color");
		if(!colorGeoIcon.equals(backcolorGeoIcon)){
		logger.info("Geo Location icon is highlighted!");
		}
		else{
      	logger.info("Geo Locatio icon is not highlighted!");
		}
		logger.info("Cluster options are listed below:");
		Select dropdown = new Select(OnBoardPage.geoLocation);
	    List<WebElement> list = dropdown.getOptions();
	    for (WebElement op  : list) {
			logger.info(op.getText());
		}

	   	OnBoardPage.geoLocation.sendKeys(cName);
	    logger.info("Selected domain is " + cName);
		OnBoardPage.nextCluster.click();
		String colorCname = OnBoardPage.cnameIcon.getCssValue("color");
        String backcolorCname = OnBoardPage.cnameIcon.getCssValue("background-color");
		if(!colorCname.equals(backcolorCname)){
		logger.info("Cname icon is highlighted!");
		}
		else{
      	logger.info("Cname icon is not highlighted!");
		}
		OnBoardPage.nextCname.click();
		
		String colorFullHttp = OnBoardPage.fullHttpIcon.getCssValue("color");
        String backcolorFullHttp = OnBoardPage.fullHttpIcon.getCssValue("background-color");
		if(!colorFullHttp.equals(backcolorFullHttp)){
		logger.info("Full Http icon is highlighted!");
		}
		else{
      	logger.info("Full Http icon is not highlighted!");
		}
		String redirect = OnBoardPage.forceHttps.getAttribute("value");
		Assert.assertEquals(redirect, "1");
    	logger.info("Force Https Redirection is chosen by default");
		OnBoardPage.forceHttp.click();
		OnBoardPage.noRedirection.click();
		OnBoardPage.forceHttps.click();
		logger.info("All the options are selectable individually");
		
		OnBoardPage.cipherSuites.click();
		logger.info("Enabled Advanced Cipher Suites");
		
		OnBoardPage.apiSecurity.click();
		logger.info("Enabled API Security");
		
		OnBoardPage.baseURI.sendKeys(baseUri);
		OnBoardPage.nextFullHttps.click();
		
		OnBoardPage.previousSecure.click();
		OnBoardPage.previousFullHttp.click();
		OnBoardPage.previousCname.click();
		OnBoardPage.previousGeo.click();
		OnBoardPage.previousEndPoint.click();
		logger.info("All the previous page buttons are working");
		
		OnBoardPage.secureIcon.click();
		String colorsecureIcon = OnBoardPage.secureIcon.getCssValue("color");
        String backcolorsecureIcon = OnBoardPage.secureIcon.getCssValue("background-color");
		if(!colorsecureIcon.equals(backcolorsecureIcon)){
		logger.info("Secure icon is highlighted!");
		}
		else{
      	logger.info("Secure icon is not highlighted!");
		}
		OnBoardPage.secureButton.click();
		try {
			if(OnBoardPage.addApplication.isDisplayed()) {
				 logger.info("Successfully completed the on-boarding process with the provided domain and cluster.");
			} 
	    } catch (NoSuchElementException e) {
	    	logger.info("The domain has already been added. Please attempt to use a different domain.");
	    }
	}
	
	
	public void OnboardDuplicate() {
		String cName = properties.getProperty("clusterName");
		String dName = properties.getProperty("domainName");
		String ePoint = properties.getProperty("endpoint");
		String baseUri = properties.getProperty("uri");
		
		OnBoardPage.home.click();
		OnBoardPage.addApplication.click();
		OnBoardPage.domainName.sendKeys(dName);
		OnBoardPage.nextDomain.click();
		OnBoardPage.endPoint.sendKeys(ePoint);
		OnBoardPage.nextIp.click();
		OnBoardPage.geoLocation.sendKeys(cName);
		OnBoardPage.nextCluster.click();
		OnBoardPage.nextCname.click();
		OnBoardPage.cipherSuites.click();
		OnBoardPage.apiSecurity.click();
		OnBoardPage.baseURI.sendKeys(baseUri);
		OnBoardPage.nextFullHttps.click();
		OnBoardPage.secureButton.click();
		try {
			if(OnBoardPage.addApplication.isDisplayed()) {
				logger.warn("On-boarding with a duplicate domain under the same cluster should not be allowed");
			} 
	    } catch (NoSuchElementException e) {
	    	logger.info("Application is restricted to on-board a same domain under the same cluster");
	    }
	}
	
	public void OnboardDupCluster() {
		String cName = properties.getProperty("clusterName");
		String newDomain = properties.getProperty("newDomain");
		String ePoint = properties.getProperty("endpoint");
		String baseUri = properties.getProperty("uri");
		OnBoardPage.home.click();
		OnBoardPage.addApplication.click();
		OnBoardPage.domainName.sendKeys(newDomain);
		OnBoardPage.nextDomain.click();
		OnBoardPage.endPoint.sendKeys(ePoint);
		OnBoardPage.nextIp.click();
		OnBoardPage.geoLocation.sendKeys(cName);
		OnBoardPage.nextCluster.click();
		OnBoardPage.nextCname.click();
		OnBoardPage.cipherSuites.click();
		OnBoardPage.apiSecurity.click();
		OnBoardPage.baseURI.sendKeys(baseUri);
		OnBoardPage.nextFullHttps.click();
		OnBoardPage.secureButton.click();
		try {
			if(OnBoardPage.addApplication.isDisplayed()) {
				logger.info("Successfully completed the onboarding process with the new domain under the same cluster.");
			} 
	    } catch (NoSuchElementException e) {
	    	logger.warn("Application is restricted to on-board a new domain under the same cluster");
	    }
	}
	
	public void OnboardDupDomain() {
		String dName = properties.getProperty("domainName");
		String newCluster = properties.getProperty("newCluster");
		String ePoint = properties.getProperty("endpoint");
		String baseUri = properties.getProperty("uri");
		OnBoardPage.home.click();
		OnBoardPage.addApplication.click();
		OnBoardPage.domainName.sendKeys(dName);
		OnBoardPage.nextDomain.click();
		OnBoardPage.endPoint.sendKeys(ePoint);
		OnBoardPage.nextIp.click();
		OnBoardPage.geoLocation.sendKeys(newCluster);
		OnBoardPage.nextCluster.click();
		OnBoardPage.nextCname.click();
		OnBoardPage.cipherSuites.click();
		OnBoardPage.apiSecurity.click();
		OnBoardPage.baseURI.sendKeys(baseUri);
		OnBoardPage.nextFullHttps.click();
		OnBoardPage.secureButton.click();
		try {
			if(OnBoardPage.addApplication.isDisplayed()) {
				logger.warn("Application should not allow to on-board with a duplicate domain under the new cluster");
			} 
	    } catch (NoSuchElementException e) {
	    	logger.info("Application is restricted to on-board a duplicate domain under the new cluster");
	    }
	}
	
	
	public void selectCluster() {
		String clusterName = properties.getProperty("clusterName");
		
		Select clusterdrop = new Select(OnBoardPage.cluster);
		clusterdrop.selectByVisibleText(clusterName);
	}
	
	public void selectDomain() {
		String domainName = properties.getProperty("domainName");
		
		Select domainDrop = new Select(OnBoardPage.domain);
	    domainDrop.selectByVisibleText(domainName);
	}
		
	public void refreshPage()  {
		
		String dName = properties.getProperty("domainName");
		String newCluster = properties.getProperty("newCluster");
		String ePoint = properties.getProperty("endpoint");
		String baseUri = properties.getProperty("uri");
		
		OnBoardPage.home.click();
		OnBoardPage.addApplication.click();
		OnBoardPage.domainName.sendKeys(dName);
	    driver.navigate().refresh();
		if (OnBoardPage.domainName.getAttribute("value").isEmpty()) {
			logger.info("The user is back on the first onboarding wizard after refreshing.");
		}else {
			logger.warn("Same wizard should not be there after refreshing");
		}
		
		OnBoardPage.domainName.sendKeys(dName);
		OnBoardPage.nextDomain.click();
		OnBoardPage.endPoint.sendKeys(ePoint);
		driver.navigate().refresh();
		if(OnBoardPage.domainName.getText() == "") {
			logger.info("The user is back on the first onboarding wizard after refreshing.");
		}else {
			logger.warn("Same wizard should not be there after refreshing");
		}
	
		OnBoardPage.domainName.sendKeys(dName);
		OnBoardPage.nextDomain.click();
		OnBoardPage.endPoint.sendKeys(ePoint);
		OnBoardPage.nextIp.click();
		OnBoardPage.geoLocation.sendKeys(newCluster);
		driver.navigate().refresh();
		if(OnBoardPage.domainName.getText() == "") {
			logger.info("The user is back on the first onboarding wizard after refreshing.");
		}else {
			logger.warn("Same wizard should not be there after refreshing");
		}
		
		OnBoardPage.domainName.sendKeys(dName);
		OnBoardPage.nextDomain.click();
		OnBoardPage.endPoint.sendKeys(ePoint);
		OnBoardPage.nextIp.click();
		OnBoardPage.geoLocation.sendKeys(newCluster);
		OnBoardPage.nextCluster.click();
		OnBoardPage.nextCname.click();
		driver.navigate().refresh();
		if(OnBoardPage.domainName.getText() == "") {
			logger.info("The user is back on the first onboarding wizard after refreshing.");
		}else {
			logger.warn("Same wizard should not be there after refreshing");
		}
				
		OnBoardPage.domainName.sendKeys(dName);
		OnBoardPage.nextDomain.click();
		OnBoardPage.endPoint.sendKeys(ePoint);
		OnBoardPage.nextIp.click();
		OnBoardPage.geoLocation.sendKeys(newCluster);
		OnBoardPage.nextCluster.click();
		OnBoardPage.nextCname.click();
		OnBoardPage.cipherSuites.click();
		OnBoardPage.apiSecurity.click();
		OnBoardPage.baseURI.sendKeys(baseUri);
		driver.navigate().refresh();
		if(OnBoardPage.domainName.getText() == "") {
			logger.info("The user is back on the first onboarding wizard after refreshing.");
		}else {
			logger.warn("Same wizard should not be there after refreshing");
		}

		OnBoardPage.domainName.sendKeys(dName);
		OnBoardPage.nextDomain.click();
		OnBoardPage.endPoint.sendKeys(ePoint);
		OnBoardPage.nextIp.click();
		OnBoardPage.geoLocation.sendKeys(newCluster);
		OnBoardPage.nextCluster.click();
		OnBoardPage.nextCname.click();
		OnBoardPage.cipherSuites.click();
		OnBoardPage.apiSecurity.click();
		OnBoardPage.baseURI.sendKeys(baseUri);
		OnBoardPage.nextFullHttps.click();
		driver.navigate().refresh();
		if(OnBoardPage.domainName.getText() == "") {
			logger.info("The user is back on the first onboarding wizard after refreshing.");
		}else {
			logger.warn("Same wizard should not be there after refreshing");
		}
	}

    
@Test
public void verifyOnboarding()  {
	testCase = extentReport.createTest("Verify On-boarding Process");
	PageFactory.initElements(driver, OnBoardPage.class);
	logger.info("Login begins");
	signin();
	logger.info("Application logged in with capital letters Email id");
	logo();
	addApplication();
	fbPage();
	twitterPage();
	year();
	Onboard();
	OnboardDuplicate();
	OnboardDupCluster();
	OnboardDupDomain();
	refreshPage();
	logger.info("End of Onboarding Test case");	
	testCase.log(Status.PASS, "User can able to on-board successfully");
 }
}
