package com.fox.page;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WelcomePage extends BasePage {

	public WelcomePage(WebDriver driver) {
		super(driver);
	}

	WebDriverWait wait = new WebDriverWait(driver, 20);

	@FindBy(id = "path-1")
	private WebElement userIcon;

	@FindBy(className = "Account_signUp_3SpTs")
	private WebElement signUpBtn;

	@FindBy(className = "Account_signIn_Q0B7n")
	private WebElement signInBtn;

	@FindBy(className = "Account_signinField_mdMZF")
	private List<WebElement> signInFields;

	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div[2]/div/div[3]/button")
	private WebElement doneBtn;

	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div[4]/div[1]/input")
	private WebElement fname;

	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div[9]/div[1]/div/div/div")
	private WebElement gender;

	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div[4]/div[2]/input")
	private WebElement lname;

	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div[5]/input")
	private WebElement email;

	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div[7]/div/input")
	private WebElement password;

	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div[9]/div[1]/div/div[2]/a[1]/div")
	private WebElement male;

	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div[9]/div[1]/div/div[2]/a[2]/div")
	private WebElement female;

	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div[9]/div[2]/input")
	private WebElement dob;

	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div[12]/button")
	private WebElement createProfile;

	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div[4]/div[4]/button")
	private WebElement loginBtn;

	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div[2]/div/div[2]/a[4]")
	private WebElement signOut;
	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div[2]/div/div[3]/button[1]")
	private WebElement confirmSignOut;

	@FindBy(xpath = "//*[text()='Thanks for Signing Up!']")
	private WebElement thankYouMsg;

	public void navigateToSignUp() {
		userIcon.click();
		signUpBtn.click();

	}

	public void navigateToSignIn(String email, String password) {
		userIcon.click();
		signInBtn.click();
		signInFields.get(0).sendKeys(email);
		signInFields.get(1).sendKeys(password);
		loginBtn.click();
	}

	public void logout() {
		userIcon.click();
		signOut.click();
		confirmSignOut.click();
	}

	public void enterName() {
		fname.sendKeys(RandomStringUtils.randomAlphabetic(5).toLowerCase());
		lname.sendKeys(RandomStringUtils.randomAlphabetic(5).toLowerCase());

	}

	public void enterEmail(String emailId) {
		email.sendKeys(emailId);

	}

	public String enterPassword() {
		String ranpass = RandomStringUtils.randomAlphabetic(7);
		password.sendKeys(ranpass);
		return ranpass;

	}

	public void selectMaleGender() {

		gender.click();
		male.click();

	}

	public void selectFemaleGender() {

		gender.click();
		female.click();
	}

	public void enterDOB(String date) {
		dob.sendKeys(date);
	}

	public void clickCreateProfile() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", createProfile);
	}

	public void verifySignUp() {
		wait.until(ExpectedConditions.visibilityOf(thankYouMsg));
		Assert.assertTrue(thankYouMsg.isDisplayed());
		doneBtn.click();
	}

}
