package com.abc.googlesearch.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import io.github.bonigarcia.wdm.Config;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExcelUtils;
import utils.Log;

import org.apache.log4j.Logger;
import utils.PropertyManager;

/**
 * 
 * @author Thusitha Bandara
 * @date 20/08/2019
 * 
 *
 */

public class BaseTest {

	static WebDriver driver;
	

	/**
	 * 
	 * @throws Exception
	 * setup method will set the baseURL and the browser from the config file
	 * 
	 */
	@BeforeClass
	public void setup() throws Exception {
		try {
			Log.startLog("Starting the test!");
			String Browser = PropertyManager.getInstance().getBrowser();

			if (Browser.equals("Chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}

			if (Browser.equals("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}

			if (Browser.equals("IE")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}

			if (Browser.equals("Opera")) {
				WebDriverManager.operadriver().setup();
				driver = new OperaDriver();

			}

			if (Browser.equals("Edge")) {
				WebDriverManager.operadriver().setup();
				driver = new EdgeDriver();

			}
			if (Browser.equals("Headless")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless");
				driver = new ChromeDriver(chromeOptions);
			}

			String baseURL = PropertyManager.getInstance().getBaseURL();
			driver.get(baseURL);
			driver.manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @throws Exception
	 * quit the web driver after execution
	 * 
	 */
	@AfterClass
	public void tearDown() throws Exception {
		try {
			if (null != driver) {
				Log.endLog("Ending the test!");
				
				driver.quit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
