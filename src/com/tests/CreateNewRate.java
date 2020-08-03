package com.innroad.inncenter.tests;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.Account;
import com.innroad.inncenter.pageobjects.Folio;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Policies;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.Reservation;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_On_All_Navigation;

public class CreateNewRate extends TestCore {

	private WebDriver driver_VRAP = null;

	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> getTest_Steps = new ArrayList<>();

	ArrayList<String> TestName = new ArrayList<>();
	ArrayList<String> TestCategory = new ArrayList<>();
	ArrayList<String> TestDescription = new ArrayList<>();

	// Create rate and attach room classes
	@Test(dataProvider = "getData", groups = "Accounts")
	public void createNewRate(String Season, String RatePlan, String RoomClassAbb, String RoomClassName,
			String bedsCount, String maxAdults, String maxPersons, String roomQuantity, String RateName,
			String BaseAmount, String AddtionalAdult, String AdditionalChild, String DisplayName,
			String AssociateSession, String RatePolicy, String RateDescription) {

		String test_name = "CreateNewRate";
		String test_description = "Create new rate if not exist and attch created room classes with it <br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/554258' target='_blank'>"
				+ "Click here to open TestRail: C554258</a>";
		String test_catagory = "Rates";

		String testName = test_name;
		TestName.add(testName);
		TestDescription.add(test_description);
		TestCategory.add(test_catagory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Navigation nav = new Navigation();
		Rate rate = new Rate();
		boolean isRateExist = false;
		boolean noRateExist = false;

		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);

			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver_VRAP = getDriver();
			login_NONGS(driver_VRAP);
			test_steps.add("Logged into the application");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver_VRAP);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver_VRAP);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			
			nav.Inventory(driver_VRAP);
			nav.Rates1(driver_VRAP);
			noRateExist = rate.BeforeSearch(driver_VRAP);
			if (noRateExist == false) {
				isRateExist = rate.SearchRate_RoomClasses(driver_VRAP, RateName, true);
				test_steps.add("New Rate has been Searched successfully");
				app_logs.info("New Rate has been Searched successfully");
			} else {
				test_steps.add("No rate exist in rate list");
				app_logs.info("No rate exist in rate list");
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Delete rate", testName, "Rates", driver_VRAP);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Delete rate", testName, "Rates", driver_VRAP);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Create New Rate and Attach RoomClass
		try {
			System.out.println(Utility.ClassesName.size());
			
			boolean isRoomClassExist = false;

			if (isRateExist) {
				isRoomClassExist = rate.Attach_RoomClasses(driver_VRAP);
				
				if (isRoomClassExist) {
					rate.Done_SaveButton(driver_VRAP);
					if (isRateExist) {
						test_steps.add("Attched season with existing rate");
						app_logs.info("Attched season with existing rate");

					}
					if (isRoomClassExist) {
						test_steps.add("Attched Room classes with existing rate");
						app_logs.info("Attched Room classes with existing rate");

					}
				} else {
					if (!isRoomClassExist) {
						test_steps.add("No Room class exist in room class picker");
					}
					

				}

			} else {

				DisplayName = RateName;
				rate.newRate_RoomClasses(driver_VRAP, RateName, maxAdults, maxPersons, BaseAmount, AddtionalAdult,
						AdditionalChild, DisplayName, RatePolicy, RateDescription, RoomClassName, Utility.SeasonName);
				test_steps.add("Enter all require details and save rate");
				app_logs.info("Enter all require details and save rate");
			}
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to create new rate", testName, "CreateNewRate", driver_VRAP);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to create new rate", testName, "CreateNewRate", driver_VRAP);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {

		return Utility.getData("NewRate", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closedriver_VRAP() {
		 driver_VRAP.quit();
	}

}
