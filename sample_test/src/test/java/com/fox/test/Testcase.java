package com.fox.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.fox.page.LandingPage;
import com.fox.page.WelcomePage;
import com.fox.utils.ExcelWriter;

public class Testcase extends BaseTest {

	@Test(priority = 1)
	public void createAnAccount() {

		WelcomePage welcomePage = new WelcomePage(seDriver);
		welcomePage.navigateToSignUp();
		welcomePage.enterName();
		welcomePage.enterEmail(email);
		password = welcomePage.enterPassword();
		welcomePage.selectMaleGender();
		welcomePage.enterDOB("01011990");
		welcomePage.clickCreateProfile();
		welcomePage.verifySignUp();
		welcomePage.logout();

		System.out.println(password);

	}

	@Test(priority = 2)
	public void loginActions() throws IOException, InvalidFormatException {

		WelcomePage welcomePage = new WelcomePage(seDriver);
		welcomePage.navigateToSignIn(email, "LfMjGjM");

		LandingPage landingPage = new LandingPage(seDriver);
		landingPage.openShows();

		List<String> foxList = landingPage.getFoxTitle();
		landingPage.clickFxSubTab();
		List<String> fxList = landingPage.getAllFoxTitle();
		landingPage.clickNatGeoSubTab();
		List<String> natGeoList = landingPage.getAllFoxTitle();
		landingPage.clickFoxSportSubTab();
		List<String> foxSportsList = landingPage.getAllFoxTitle();
		landingPage.clickAllShowSubTab();
		List<String> allShowsList = landingPage.getAllFoxTitle();

		List<String> dubListFx = new ArrayList<>();
		List<String> dubListNatGeo = new ArrayList<>();
		List<String> dubListfoxSports = new ArrayList<>();
		List<String> dubListAllShows = new ArrayList<>();

		for (String each : foxList) {
			if (fxList.remove(each))
				dubListFx.add(each);

			if (natGeoList.remove(each))
				dubListNatGeo.add(each);

			if (foxSportsList.remove(each))
				dubListfoxSports.add(each);

			if (allShowsList.remove(each))
				dubListAllShows.add(each);

		}

		if (dubListFx.size() == 0)
			dubListFx.add("No duplicates from fx list");

		if (dubListNatGeo.size() == 0)
			dubListNatGeo.add("No duplicates from fx list");

		if (dubListfoxSports.size() == 0)
			dubListfoxSports.add("No duplicates from fx list");

		if (dubListAllShows.size() == 0)
			dubListAllShows.add("No duplicates from fx list");

		ExcelWriter excelWriter = new ExcelWriter();
		excelWriter.createExcel(foxList, dubListFx, dubListNatGeo, dubListfoxSports, dubListAllShows);

	}

}
