package com.fox.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends BasePage {

	public LandingPage(WebDriver driver) {
		super(driver);
	}

	WebDriverWait wait = new WebDriverWait(driver, 20);

	@FindBy(xpath = "/html/body/div[1]/div/header/div/div/div/div/div[2]/div/a[1]")
	private WebElement showsTab;

	@FindBy(xpath = "/html/body/div[1]/div/main/div/div/div[1]/div[2]/div/div/div/div[2]/div[1]/a[2]")
	private WebElement fxSubTab;

	@FindBy(xpath = "/html/body/div[1]/div/main/div/div/div[1]/div[2]/div/div/div/div[2]/div[1]/a[3]")
	private WebElement natGeoSubTab;

	@FindBy(xpath = "/html/body/div[1]/div/main/div/div/div[1]/div[2]/div/div/div/div[2]/div[1]/a[4]")
	private WebElement foxSportsSubTab;

	@FindBy(xpath = "/html/body/div[1]/div/main/div/div/div[1]/div[2]/div/div/div/div[2]/div[1]/a[5]")
	private WebElement allShowSubTab;

	public void clickFxSubTab() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		fxSubTab.click();
	}

	public void clickNatGeoSubTab() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		natGeoSubTab.click();
	}

	public void clickFoxSportSubTab() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		foxSportsSubTab.click();
	}

	public void clickAllShowSubTab() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		allShowSubTab.click();
	}

	public void openShows() {
		wait.until(ExpectedConditions.visibilityOf(showsTab));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		showsTab.click();
	}

	public List<String> getFoxTitle() {
		List<String> title = new ArrayList<>();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> foxTiles = driver.findElements(By.className("Tile_details_9UwiV"));

		for (int i = foxTiles.size() - 1; i > foxTiles.size() - 5; --i) {
			try {
				title.add(foxTiles.get(i).findElement(By.xpath(".//div/a/div/div")).getText());
			} catch (NoSuchElementException e) {
				title.add(foxTiles.get(i).findElement(By.xpath(".//div/a/div")).getText());
			}
		}
		return title;
	}

	public List<String> getAllFoxTitle() {
		List<String> title = new ArrayList<>();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> foxTiles = driver.findElements(By.className("Tile_details_9UwiV"));

		for (int i = 0; i < foxTiles.size(); i++) {
			try {
				title.add(foxTiles.get(i).findElement(By.xpath(".//div/a/div/div")).getText());
			} catch (NoSuchElementException e) {
				title.add(foxTiles.get(i).findElement(By.xpath(".//div/a/div")).getText());
			}
		}
		return title;
	}

}
