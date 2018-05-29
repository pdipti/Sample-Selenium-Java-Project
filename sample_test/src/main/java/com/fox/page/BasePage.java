package com.fox.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	public WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.initPage();
	}

	public void initPage() {
		PageFactory.initElements(this.driver, this);

	}

	public void pageLoadTimeOut(long time, TimeUnit timeunits) {
		this.driver.manage().timeouts().pageLoadTimeout(time, timeunits);

	}

}
