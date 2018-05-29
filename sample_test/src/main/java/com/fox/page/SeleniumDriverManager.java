package com.fox.page;

import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SeleniumDriverManager {

	public static Logger LOG = Logger.getLogger(SeleniumDriverManager.class);

	private static WebDriver driver;
	private static String myPlatform;
	private static String myBrowser;
	private static String defaultWindow;

	public static String getDefaultWindow() {
		return defaultWindow;
	}

	public static String getMyPlatform() {
		return myPlatform;
	}

	public static String getMyBrowser() {
		return myBrowser;
	}

	public static WebDriver createBrowserInstance(String browser, String url) throws Exception {

		// check if driver is running from previous session
		killDriver();

		LOG.info("Starting new browser session");
		LOG.debug("TestNG parameters are setup as: ");
		LOG.debug("WEBbrowser is:       " + browser);
		if (driver != null) {
			LOG.debug("WebDriver is still running. No need to create a new instance.");
		} else {
			driver = createLocalDriver(browser);

			if (!driver.getTitle().equals("Toppr Login")) {

				Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
				LOG.debug("Browser name:        " + caps.getBrowserName());
				LOG.debug("Browser version:     " + caps.getVersion());
				LOG.debug("OS:                  " + caps.getPlatform());
				LOG.debug("Actual caps: " + caps);
				LOG.debug("###################   ENVIRONMENT VARIABLES: ###################");

				Map<String, String> env = System.getenv();
				for (String envName : env.keySet()) {
					LOG.debug(envName + "=" + env.get(envName));
				}

				LOG.debug("################################################################");

				LOG.info("<<<<Initialization of web page.>>>>>");

				LOG.info("Opening page: " + url);
				try {
					driver.get(url);
				} catch (TimeoutException e) {
					driver.get(url);
				}

				defaultWindow = driver.getWindowHandle();

				LOG.info("<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>");
			}
		}

		return driver;
	}

	public static void killDriver() {
		if (driver != null) {
			LOG.info("Killing selenium driver session");
			driver.quit();

			try {
				LOG.debug("Called timer to wait for 3 seconds");
				Thread.sleep(3 * 1000);
			} catch (InterruptedException e) {
				LOG.error(e);
				e.printStackTrace();
			}

			if (driver != null) {
				LOG.error("Failed to close previous driver session. Force kill by user is recommended.");
				driver = null;
			}

		}
	}

	private static WebDriver createLocalDriver(String browser) {

		String extension = "";
		String osName = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
		if (osName.contains("windows")) {
			extension = ".exe";
		}

		WebDriver driver = null;

		LOG.info("Creating " + browser + " driver");
		if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "geckodriver" + extension);
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", "IEdriverServer.exe");
			driver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("Safari")) {
			driver = new SafariDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver" + extension);
			driver = new ChromeDriver();
		}

		LOG.info("Local webdriver created.");
		return driver;
	}
}
