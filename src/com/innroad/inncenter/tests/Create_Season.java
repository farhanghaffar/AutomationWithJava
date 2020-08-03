package com.innroad.inncenter.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.pageobjects.Login;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.Season;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class Create_Season extends TestCore {

	private WebDriver driver = null;
	public static ArrayList<String> test_name = new ArrayList<String>();
	public static ArrayList<String> test_description = new ArrayList<String>();
	public static ArrayList<String> test_catagory = new ArrayList<String>();
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> getTest_Steps = new ArrayList<>();

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Inventory")
	public void create_Season(String url, String ClientCode, String Username, String Password, String SeasonName,
			String Status, String RateName, String MaxAdult, String MaxPerson, String BaseAmount, String AddtionalAdult,
			String AdditionalChild, String DisplayName, String RatePolicy, String RateDescription, String RoomClass)
			throws InterruptedException, IOException {

		test_name.add("Create_Season");
		String testDescription = "Create new season for one year";

		test_description.add(testDescription);
		test_catagory.add("Season");
		String testName = test_name.get(0);

		app_logs.info("##################################################################################");
		app_logs.info(testName);
		app_logs.info("##################################################################################");

		Navigation navigation = new Navigation();
		Season season = new Season();
		boolean isAnySeasonExist = true;
		boolean isSeasonExist = false;

		// Logging to application
		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);

			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver = getDriver();
			login_NONGS(driver);
			test_steps.add("Logged into the application");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// navigate to Inventory->Seasons
		try {

			navigation.Inventory(driver);
			navigation.Seasons(driver);
			test_steps.add("Navigate to Seasons");
			app_logs.info("Navigate to Seasons");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Navigate Seasons", testName, "Navigation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Navigate Seasons", testName, "Navigation", driver);
			} else {

				Assert.assertTrue(false);
			}
		}
		// search create season
		try {
			isAnySeasonExist = season.SearchSeasonButton_Toaster(driver);
			System.out.println(isAnySeasonExist);
			test_steps.add("Click on search button");
			if (isAnySeasonExist) {
				System.out.println("before search");
				isSeasonExist = season.SearchSeason(driver, SeasonName,true);
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to seasrch season", testName, "Season", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to seasrch season", testName, "", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// create new season
		try {

			if (!isSeasonExist) {
				
			
			season.NewSeasonButtonClick(driver);
			Utility.SeasonName = SeasonName;
			getTest_Steps.clear();
			getTest_Steps = season.SeasonDeatilsUpdate(driver, SeasonName, Status);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = season.SelectStartDate(driver, -30);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = season.SelectEndDate(driver, +334);
			test_steps.addAll(getTest_Steps);
			
			season.SaveSeason(driver, SeasonName);
			test_steps.add("Click on save button");
			
			test_steps.add("Create new season successfully");
			}
			else{
				Utility.SeasonName = SeasonName;
				getTest_Steps.clear();
				getTest_Steps = season.SelectStartDate(driver, -30);
				test_steps.addAll(getTest_Steps);

				getTest_Steps.clear();
				getTest_Steps = season.SelectEndDate(driver, +334);
				test_steps.addAll(getTest_Steps);
				
				season.SaveSeason(driver, SeasonName);
				test_steps.add("Click on save button");
				
				test_steps.add("Update season start and end date");
			}


		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create new season", testName, "SeasonCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create new season", testName, "SeasonCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		

		
		
		// close tab
		try {
			season.CloseSeasonTab(driver);
			test_steps.add("Close tab");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to close tab", testName, "Season", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to close tab", testName, "", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// search create season
		try {
			season.SearchSeasonButton(driver);
			test_steps.add("Click on search button");
			season.SearchSeason(driver, SeasonName,false);
			test_steps.add("Newly created season searched successfully");
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to seasrch season", testName, "Season", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to seasrch season", testName, "", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {

		// return test data from the sheet name provided

		return Utility.getData("Create_Season", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closedriver() {
		 driver.quit();
	}

}
