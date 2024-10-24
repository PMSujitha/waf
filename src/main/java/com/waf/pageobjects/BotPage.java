package com.waf.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BotPage {
	
	@FindBy(xpath = "//div[@class='navbar-nav-wrap']//img[@alt='Prophaze']")
	public static WebElement logo; 
	
	@FindBy(xpath = "//*[@id=\'content\']/div[2]/div/div[1]/p")
	public static WebElement footer;
    
    @FindBy(xpath = "//i[@class='bi-facebook']")
    public static WebElement fbIcon;
    
    @FindBy(xpath = "//div[@aria-label='Close']//i[@class='x1b0d499 x1d69dk1']")
    public static WebElement fbPopup;
    
    @FindBy(xpath = "//i[@class='bi-twitter']")
    public static WebElement twitterIcon;
    
    @FindBy(xpath = "//*[@id=\'layers\']/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[1]/div/div/div/div/div[1]/button/div/svg")
    public static WebElement twitterPopup;

	@FindBy(xpath = "//span[normalize-space()='Bot Mitigation']")
	public static WebElement botMenu;
		
	@FindBy(xpath = "//select[@id='cluster_selector']")
	public static WebElement cluster;
	
	@FindBy(xpath = "//select[@id='domain_selector']")
	public static WebElement domain;
									
	@FindBy(xpath = "//input[@id='btn-apply-domain']")
	public static WebElement apply;
								
    @FindBy(xpath = "//div[normalize-space()='Learning Mode']")
    public static WebElement learningMode;
        
    @FindBy(xpath = "//div[normalize-space()='Active Mode']")
    public static WebElement activeMode;
    
    @FindBy(xpath = "//*[@id=\'btnConfirmation\']")
    public static WebElement confirm;
    
    @FindBy(xpath = "//*[@id=\'btnCancellation\']")
    public static WebElement cancel;
    
    @FindBy(xpath = "//*[@id=\'wafButtonPrompt\']/div/div/div[3]/button")
    public static WebElement modeOk;
    
    @FindBy(xpath = "//a[@class='link']")
    public static WebElement learnMore;
    
    @FindBy(xpath = "//*[@id=\'bot-toggle\']/div/div/div[3]")
    public static WebElement enableButton;
    
    @FindBy(xpath = "//*[@id=\'confirmation-btn\']")
    public static WebElement proceedButton;
    
    @FindBy(xpath = "//*[@id=\'confirmation-prompt\']/div/div/div[3]/button[2]")
    public static WebElement cancelButton;
    
    @FindBy(xpath = "//input[@id='js-Challenge']")
    public static WebElement capchaless;
   
    @FindBy(xpath = "//*[@id=\'bot-protection-form\']/div/div[3]/label")
    public static WebElement capcha;
    
    @FindBy(xpath = "//a[@class='breadcrumb-link']")
    public static WebElement home;
    
    @FindBy(xpath = "//div[@id='checkbox']")
    public static WebElement humanCheck;
    
    @FindBy(xpath = "//*[@id=\"details-button\"]")
    public static WebElement advanced;
    
    @FindBy(xpath = "//*[@id=\"proceed-link\"]")
    public static WebElement proceed;
    
    
    
}
