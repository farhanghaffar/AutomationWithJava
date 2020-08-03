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

public class CreateNewRoomClasses extends TestCore {
	// Before Test
	private WebDriver driver_VRAP = null;

	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> getTest_Steps = new ArrayList<>();

	ArrayList<String> TestName = new ArrayList<>();
	ArrayList<String> TestCategory = new ArrayList<>();
	ArrayList<String> TestDescription = new ArrayList<>();

	// Create room class if not exist
	@Test(dataProvider = "getData", groups = "Accounts")
	public void createNewRoomClasses(String Season, String RatePlan, String RoomClassAbb, String RoomClassName,
			String bedsCount, String maxAdults, String maxPersons, String roomQuantity, String RateName,
			String BaseAmount, String AddtionalAdult, String AdditionalChild, String DisplayName,
			String AssociateSession, String RatePolicy, String RateDescription) {

		String test_name = "CreateNewRoomClasses";
		String test_description = "Create new room classes if not exist <br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/554258' target='_blank'>"
				+ "Click here to open TestRail: C554258</a>";
		String test_catagory = "Room Classes";

		String testName = test_name;
		TestName.add(testName);
		TestDescription.add(test_description);
		TestCategory.add(test_catagory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Navigation nav = new Navigation();
		RoomClass room_class = new RoomClass();
		

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

		boolean isRoomClass_Exist = false;
		try {

			nav.Setup(driver_VRAP);
			nav.RoomClass(driver_VRAP);
			isRoomClass_Exist = room_class.VerifyRoomClassExist(driver_VRAP, RoomClassName);
			System.out.println("isRoomClass_Exist :"+isRoomClass_Exist);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to verify room class exist or not", testName, "RoomClass", driver_VRAP);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to verify room class exist or not", testName, "RoomClass", driver_VRAP);
			}
		}

		// create new room class
		try {

			if (!isRoomClass_Exist) {
				nav.NewRoomClass(driver_VRAP);
				System.out.println("isRoomClass_Exist :"+isRoomClass_Exist);
				getTest_Steps.clear();
				getTest_Steps = room_class.RoomClass_150Rooms(driver_VRAP, RoomClassName, RoomClassAbb, bedsCount,
						maxAdults, maxPersons, roomQuantity, test, getTest_Steps);
				test_steps.addAll(getTest_Steps);

				test_steps.add("Sccessfully Created New RoomClass  : "+RoomClassName);
				app_logs.info("Sccessfully Created New RoomClass :  "+RoomClassName);
				Utility.ClassesName.add(RoomClassName);
			} else {
				test_steps.add(RoomClassName + " already exist");
				app_logs.info(RoomClassName + " already exist");
			}

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
			
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to create room class successfully", testName, "NewRoomClass",
						driver_VRAP);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to create room class successfully", testName, "NewRoomClass",
						driver_VRAP);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {

		return Utility.getData("NewRoomClasses", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closedriver_VRAP() {
		driver_VRAP.quit();
	}

}
