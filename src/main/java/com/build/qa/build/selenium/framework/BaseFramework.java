package com.build.qa.build.selenium.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseFramework {
	protected WebDriver driver;
	protected Wait<WebDriver> wait;
	private static final Logger LOG = LoggerFactory.getLogger(BaseFramework.class);
	private static final String CONFIG_FILE = "./conf/automation.properties";
	private static final String DRIVER_FIREFOX = "firefox";
	private static final String DRIVER_CHROME = "chrome";
	private static Properties configuration;

	@Rule
	public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

	@BeforeClass
	public static void beforeClass() throws IOException {
		configuration = new Properties();
		FileInputStream input;

		LOG.info("Loading in configuration file.");
		System.out.println("Before opening config..");
		input = new FileInputStream(new File(CONFIG_FILE));
		System.out.println("After opening config..");
		configuration.loadFromXML(input);
		input.close();
	}

	@Before
	public void setUpBefore() {
		DesiredCapabilities capabilities;
		//		 Which driver to use? 
		if (DRIVER_CHROME.equalsIgnoreCase(configuration.getProperty("BROWSER"))) {
			//					capabilities = DesiredCapabilities.chrome();
			String chromeDriverLocation=configuration.getProperty("CHROMELOCATION");
			System.out.println("ChromeDriverLocationChromeDriverLocation:"+chromeDriverLocation);
			System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
			driver = new ChromeDriver();
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--disable-notifications");
//			driver = new ChromeDriver(options);
		}
		else 
			if (DRIVER_FIREFOX.equalsIgnoreCase(configuration.getProperty("BROWSER"))) {
				String firefoxDriverLocation=configuration.getProperty("FIREFOXLOCATIONLOCATION");
				System.out.println("firefoxDriverLocationFirefoxDriverLocation:"+firefoxDriverLocation);
				System.setProperty("webdriver.gecko.driver", firefoxDriverLocation);
				driver = new FirefoxDriver();
				//			capabilities = DesiredCapabilities.firefox();
				//			driver = new FirefoxDriver(capabilities);
			}
		// Define fluent wait
		wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
	}

	protected WebDriver getDriver() {
		return driver;
	}

	protected String getConfiguration(String config) { 
		System.out.println("configuration.getProperty(config): returning is:"+configuration.getProperty(config));
		return configuration.getProperty(config);
	}

	@After
	public void tearDownAfter() {
		LOG.info("Quitting driver.");
		driver.quit();
		driver = null;
	}
}
