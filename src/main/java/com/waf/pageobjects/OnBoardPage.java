package com.waf.pageobjects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class OnBoardPage {
	
	@FindBy(xpath = "//input[@placeholder='Enter your Email address']")
	public static WebElement username;
			
	@FindBy(xpath = "//input[@placeholder='Enter your Password']")
	public static WebElement password;
								
    @FindBy(xpath = "//button[@type='submit']")
    public static WebElement login;
    
	@FindBy(xpath = "//span[normalize-space()='Home']")
	public static WebElement home;
	
	@FindBy(xpath = "//div[@class='navbar-vertical-footer-offset']//img[@alt='Prophaze']")
	public static WebElement logo;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[1]/div/div[2]/div[1]/a")
	public static WebElement addApplication;
	
	@FindBy(xpath = "//*[@id=\'content\']/div[2]/div/div[1]/p")
	public static WebElement footer;	
	
	@FindBy(xpath = "//*[@id=\"onboardingProgress\"]/li[1]/a/span")
	public static WebElement domainIcon;
	
	@FindBy(xpath = "//input[@id='domain_name']")
	public static WebElement domainName;	
	
	@FindBy(xpath = "//span[@class='text-danger']")
	public static WebElement popup;
	
	@FindBy(xpath = "//i[@class='bi bi-link fs-4']")
	public static WebElement endPointIcon;
	
	@FindBy(xpath = "//input[@id='ip']")
	public static WebElement endPoint;
	
	@FindBy(xpath = "//label[@for='protocol-http']")
	public static WebElement httpProtocol;
	
	@FindBy(xpath = "//label[@for='protocol-http']")
	public static WebElement httpsProtocol;
	
	@FindBy(xpath = "//label[@for='protocol-both']")
	public static WebElement bothProtocol;
	
	@FindBy(xpath = "//*[@id=\"onboardingProgress\"]/li[3]/a/span/i")
	public static WebElement geoLocationIcon;
	
	@FindBy(xpath = "//select[@id='kluster']")
	public static WebElement geoLocation;
	
	@FindBy(xpath = "//*[@id=\"onboardingProgress\"]/li[4]/a/span/i")
	public static WebElement cnameIcon;
	
	@FindBy(xpath = "//*[@id=\"onboardingProgress\"]/li[5]/a/span/i")
	public static WebElement fullHttpIcon;
	
	@FindBy(xpath = "//label[@for='force_http']")
	public static WebElement forceHttp;
	
	@FindBy(xpath = "//input[@id='force_https']")
	public static WebElement forceHttps;
	
	@FindBy(xpath = "//label[@for='no_redirection']")
	public static WebElement noRedirection;
	
	@FindBy(xpath = "//label[@for='cipher-suites']")
	public static WebElement cipherSuites;
	
	@FindBy(xpath = "//label[@for='api-security']")
	public static WebElement apiSecurity;
	
	@FindBy(xpath = "//input[@id='base-uri-ts-control']")
	public static WebElement baseURI;
	
	@FindBy(xpath = "//button[@id='nextdomain']")
	public static WebElement nextDomain;
	
	@FindBy(xpath = "//button[@id='nextip']")
	public static WebElement nextIp;
	
	@FindBy(xpath = "//button[@onclick='showCname()']")
	public static WebElement nextCluster;
		
	@FindBy(xpath = "//div[@id='cname-details']//button[@type='button'][normalize-space()='Next']")
	public static WebElement nextCname;
	
	@FindBy(xpath = "//*[@id=\"onboardingProgress\"]/li[5]/a/span")
	public static WebElement nextFullHttpsIcon;
	
	@FindBy(xpath = "//div[@id='https-details']//i[@class='bi-chevron-right small']")
	public static WebElement nextFullHttps;
	
	@FindBy(xpath = "//div[@id='endpoint-details']//button[@type='button'][normalize-space()='Previous step']")
	public static WebElement previousEndPoint;
	
	@FindBy(xpath = "//div[@id='region-details']//button[@type='button'][normalize-space()='Previous step']")
	public static WebElement previousGeo;
	
	@FindBy(xpath = "//*[@id=\"https-details\"]/div[5]/div/button[1]")
	public static WebElement previousFullHttp;
	
	@FindBy(xpath = "//div[@id='cname-details']//button[@type='button'][normalize-space()='Previous step']")
	public static WebElement previousCname;
	
	@FindBy(xpath = "//div[@class='row mt-5 align-items-center text-center']//button[@type='button'][normalize-space()='Previous step']")
	public static WebElement previousSecure;
	
	@FindBy(xpath = "//*[@id=\"onboardingProgress\"]/li[6]/a/span/i")
	public static WebElement secureIcon;
	
	@FindBy(id = "btn-endstep")
	public static WebElement secureButton;
			
	@FindBy(xpath = "//select[@id='cluster_selector']")
	public static WebElement cluster;						
	
    @FindBy(xpath = "//*[@id=\'domain_selector\']")
    public static WebElement domain;

    @FindBy(xpath = "//input[@id='btn-apply-domain']")
    public static WebElement apply;
    
    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div/div[1]/p/text()[2]")
    public static WebElement year;
    
    @FindBy(xpath = "//i[@class='bi-facebook']")
    public static WebElement fbIcon;
    
    @FindBy(xpath = "//div[@aria-label='Close']//i[@class='x1b0d499 x1d69dk1']")
    public static WebElement fbPopup;
    
    @FindBy(xpath = "//i[@class='bi-twitter']")
    public static WebElement twitterIcon;
    
    @FindBy(xpath = "//button[@aria-label='Close']//div[@class='css-146c3p1 r-bcqeeo r-qvutc0 r-1qd0xha r-q4m81j r-a023e6 r-rjixqe r-b88u0q r-1awozwy r-6koalj r-18u37iz r-16y2uox r-1777fci']//*[name()='svg']")
    public static WebElement twitterPopup;
    
    @FindBy(xpath = "//*[@id=\"content\"]/div[1]/div/div/div/a")
    public static WebElement dupMessage;
    
   
}
