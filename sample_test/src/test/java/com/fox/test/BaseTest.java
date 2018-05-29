package com.fox.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.parsers.FactoryConfigurationError;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.fox.page.SeleniumDriverManager;

public abstract class BaseTest {

	public String baseUrl;
	public String browser;

	public String email;
	public String password;

	public static WebDriver seDriver;

	@Parameters({ "baseUrl", "emailId", "browser" })
	@BeforeSuite(alwaysRun = true)
	public void setUp(String url, String emailId, String browser) throws Exception {

		loggerFactory();

		this.baseUrl = url;
		this.browser = browser;
		this.email = emailId;

		System.out.println("Starting test against: " + url);

		seDriver = SeleniumDriverManager.createBrowserInstance(browser, baseUrl);

	}

	private void loggerFactory() throws FactoryConfigurationError {
		File dir = new File("./logs");
		if (!dir.exists()) {
			dir.mkdir();
		}

		SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
		File file = new File(dir + "/fox-web-" + format.format(Calendar.getInstance().getTime()) + ".log");
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Failed to initialize log file.");
			System.exit(0);
		}

		System.setProperty("logfilename", file.getName());

		DOMConfigurator.configure("log4j.xml");

	}

	@AfterSuite(alwaysRun = true)
	public void cleanup() {
		SeleniumDriverManager.killDriver();
	}

}
