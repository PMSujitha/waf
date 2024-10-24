package com.waf.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;



public class BaseClass {  
	
	    public static WebDriver driver = null;
		public static Properties properties = null;
		public static ExtentReports extentReport; 
		public static ExtentHtmlReporter htmlReporter;
		public static ExtentTest testCase;
		
		static Logger logger = Logger.getLogger(BaseClass.class); //FDP
		
		public Properties loadPropertyFile() throws IOException {
			FileInputStream fileInputStream = new FileInputStream("Configuration\\Config.properties");
			properties = new Properties();
			properties.load(fileInputStream);
			return properties;
		}
		
		
		@BeforeSuite
		public void fileSetup() throws IOException  {
			
		    PropertyConfigurator.configure("Configuration\\log4j.properties");
		    logger.info("WAF Test Begins");
		    logger.info("Loading the property file");
		    loadPropertyFile();
		    
		    extentReport = new ExtentReports();
		    htmlReporter = new ExtentHtmlReporter("ExtentReport.html");
		    extentReport.attachReporter(htmlReporter);
		}
		
	//Browser Launch
		@BeforeClass
			public void browserLaunch()  {
			    
				String browser = properties.getProperty("browser");
				String url = properties.getProperty("url");
				
							
				if(browser.equalsIgnoreCase("chrome")) {
				  WebDriverManager.chromedriver().setup(); 
				  logger.info("Launching Chrome");
				  driver = new ChromeDriver();
				  driver.manage().window().maximize();
			  }
			  
			  else if(browser.equalsIgnoreCase("firefox")) {
				  WebDriverManager.firefoxdriver().setup();
				  logger.info("Launching Firefox");
				  driver = new FirefoxDriver();
				  driver.manage().window().maximize();
			 }
			  
			  else if(browser.equalsIgnoreCase("edge")) {
				  WebDriverManager.edgedriver().setup();
				  logger.info("Launching Edge");
				  driver = new EdgeDriver();
				  driver.manage().window().maximize();
			  }
			  
			  else {
				  logger.warn("Enter the browser name correctly");
			  }
				
				logger.info("Navigating to Application");
				driver.get(url);
				//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		
		
			@AfterClass
			public void closeBrowser() {
				logger.info("Execution done. Closing the browser");
				driver.quit(); 
			}
			
			@AfterSuite
			public void tearDown(){
				extentReport.flush();
			}
}

