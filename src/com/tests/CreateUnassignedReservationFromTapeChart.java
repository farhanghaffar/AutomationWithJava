package com.innroad.inncenter.tests;


import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.Tapechart;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class CreateUnassignedReservationFromTapeChart extends TestCore {

	// Automation-1449
	private WebDriver driver = null;

	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> getTest_Steps = new ArrayList<>();
	ArrayList<String> TestName = new ArrayList<>();
	ArrayList<String> TestCategory = new ArrayList<>();
	ArrayList<String> TestDescription = new ArrayList<>();
	String test_name = "CreateUnassignedReservationFromTapeChart";
	public static String test_description = "";
	public static String test_catagory = "";

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {

		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "TapeChart")
	public void createUnassignedReservationFromTapeChart(String RateName, String BaseAmount, String AddtionalAdult,
			String AdditionalChild, String DisplayName, String AssociateSession, String RatePolicy,
			String RateDescription, String RoomClassAbb, String roomClassName, String bedsCount, String maxAdults,
			String maxPersons, String roomQuantity, String StartRoomNumber, String RatePlan, String RateType,
			String RateAttributes, String Interval, String Source, String Adults, String Child, String MarketSegment,
			String Referral, String FirstName, String LastName, String GroupPhn, String GroupAddress,
			String Groupcity, String Groupcountry, String Groupstate, String GroupPostalcode, String BlockName,
			String RoomPerNight,String Salutation) throws InterruptedException, IOException {

		test_name = "CreateUnassignedReservationFromTapeChart";
		test_description = "Create Unassigned Reservation -> Tape Chart<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682441' target='_blank'>"
				+ "Click here to open TestRail: C682441</a>";

		test_catagory = "TapeChart";
		String testName = test_name;
		TestName.add(testName);
		TestDescription.add(test_description);
		TestCategory.add(test_catagory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Navigation nav = new Navigation();
		Tapechart tap = new Tapechart();
		CPReservationPage cpres = new CPReservationPage();
		String RandomNumber = Utility.GenerateRandomNumber(99);
		DisplayName = RateName;
		String ReservationNumber = null;
		String AfterUnassignedReservations = "0";
		String BeforeUnassignedReservations = "0";
		String AfterUnassignedReservationsRC = "0";
		String BeforeUnassignedReservationsRC = "0";
		LastName = LastName + RandomNumber;
		String ReservationName = FirstName + " " + LastName;
		String paymentType = "Cash";


		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);

			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver = getDriver();
			login_CP(driver);
			test_steps.add("Entered appication URL : " + TestCore.envURL);
			test_steps.add("Logged into the application ");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Observe parking lots(Unassigned Reservations) in TapeChart
		try {
			app_logs.info("==========GET UNASSIGNED RESERVATIONS IN UNASSIGNED COLUMN(PARKING LOT)==========");
			test_steps.add("==========GET UNASSIGNED RESERVATIONS IN UNASSIGNED COLUMN(PARKING LOT)==========");
			nav.navTapeChart(driver, test);
			test_steps.add("Navigate TapeChart");
			app_logs.info("Navigate TapeChart");
			getTest_Steps.clear();
			getTest_Steps = tap.TapeChartSearch(driver, Utility.getCurrentDate("MM/dd/yyyy"),
					Utility.GetNextDate(1, "MM/dd/yyyy"), Adults, Child, RatePlan, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			tap.ClickUnassigned(driver);
			app_logs.info("Click Unassigned button");
			test_steps.add("Click Unassigned button");
			BeforeUnassignedReservations = tap.GetandVerifyUnassignedReservationNumber(driver, true);
			app_logs.info("Number of  Unassigned Reservation : " + BeforeUnassignedReservations);
			test_steps.add("Number of  Unassigned Reservations : " + BeforeUnassignedReservations);
			app_logs.info(
					"Successfully verified number of Unassigned Reservation from 'Unassigned Button', 'Unassigned List Header' and Unassigned List of Reservations");
			test_steps.add(
					"Successfully verified number of Unassigned Reservation from 'Unassigned Button', 'Unassigned List Header' and Unassigned List of Reservations");
			app_logs.info("==========GET UNASSIGNED RESERVATIONS OF ROOM CLASS '" + RoomClassAbb + "'==========");
			test_steps.add("==========GET UNASSIGNED RESERVATIONS OF ROOM CLASS '" + RoomClassAbb + "'==========");

			BeforeUnassignedReservationsRC = tap.GetUnassignedRoomClassCount(driver, RoomClassAbb);
			app_logs.info("Number of Unassigned Reservations of Room Class '" + RoomClassAbb + "' are '" + BeforeUnassignedReservationsRC + "'");
			test_steps.add("Number of Unassigned Reservations of Room Class '" + RoomClassAbb + "' are '" + BeforeUnassignedReservationsRC + "'");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Observe parking lots(Unassigned Reservations) in TapeChart",
						testName, "TapeChart", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Observe parking lots(Unassigned Reservations) in TapeChart",
						testName, "TapeChart", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Create Unassigned Reservation From Tape Chart
		try {
			app_logs.info("==========CREATE UNASSIGNED RESERVATION FROM TAPECHART==========");
			test_steps.add("==========CREATE UNASSIGNED RESERVATION FROM TAPECHART==========");
			tap.ClickUnassignedRoomClass(driver, RoomClassAbb, test_steps);
			String roomnumber = cpres.GETRoomNumber(driver, test_steps);
			Assert.assertEquals(roomnumber, "Unassigned", "Failed: Reservation is not unassigned");
			test_steps.add("Verified Room Number is Unassigned");
			cpres.enter_MailingAddress_OnUnchecked_CreateGuestProfile(driver, test_steps, Salutation, FirstName, LastName,
					"no");
			cpres.enter_MarketSegmentDetails(driver, test_steps, "", MarketSegment, Referral);
			cpres.enter_PaymentDetails(driver, test_steps, paymentType, "", "", "");
			cpres.clickBookNow(driver, test_steps);
			ReservationNumber = cpres.get_ReservationConfirmationNumber(driver, test_steps);
			Object ReservationStatus = cpres.get_ReservationStatus(driver, test_steps);
			Assert.assertEquals(ReservationStatus, "Reserved", "Failed: Reservation Status missmatched");
			cpres.clickCloseReservationSavePopup(driver, test_steps);
			roomnumber = cpres.get_RoomNumber(driver, test_steps,"No");
			Assert.assertEquals(roomnumber, "Unassigned", "Failed: Reservation is not unassigned");
			test_steps.add("Verified Room Number is Unassigned");
			String TripTotal = cpres.get_TripSummaryTripTotalChargesWithoutCurrency(driver, getTest_Steps);
			Utility.app_logs.info("Trip Total '" + TripTotal + "'");
			
		} catch (Exception e) {
			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Create unassigned reservation from Tape Chart", testName, "CreateUnassignedReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Create unassigned reservation from Tape Chart", testName, "CreateUnassignedReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Observe parking lots(Unassigned Reservations) in TapeChart
		try {
			app_logs.info("==========VERIFY UNASSIGNED RESERVATIONS IN UNASSIGNED COLUMN(PARKING LOT)==========");
			test_steps.add("==========VERIFY UNASSIGNED RESERVATIONS IN UNASSIGNED COLUMN(PARKING LOT)==========");
			nav.navTapeChart(driver, test);
			test_steps.add("Navigate TapeChart");
			app_logs.info("Navigate TapeChart");
			tap.ClickUnassigned(driver);
			app_logs.info("Click Unassigned button");
			test_steps.add("Click Unassigned button");
			AfterUnassignedReservations = tap.GetandVerifyUnassignedReservationNumber(driver, true);
			app_logs.info("Number of Unassigned Reservations : " + AfterUnassignedReservations);
			test_steps.add("Number of Unassigned Reservations : " + AfterUnassignedReservations);
			app_logs.info(
					"Successfully verified number of Unassigned Reservation from 'Unassigned Button', 'Unassigned List Header' and Unassigned List of Reservations");
			test_steps.add(
					"Successfully verified number of Unassigned Reservation from 'Unassigned Button', 'Unassigned List Header' and Unassigned List of Reservations");
			Assert.assertTrue(
					Integer.parseInt(AfterUnassignedReservations) > Integer.parseInt(BeforeUnassignedReservations),
					"Failed: Unassigned Count not increase");
			app_logs.info("Verified Number of Unassigned Reservations has been incremented");
			test_steps.add("Verified Number of Unassigned Reservations has been incremented");
			tap.VerifyUnassignedReservationColor(driver, ReservationName, Utility.getCurrentDate("E, MMM dd"),
					Utility.GetNextDate(1, "E, MMM dd"), RoomClassAbb,test_steps);
			app_logs.info(
					"Successfully verified Reservation '" + ReservationName + "' Exist in Unassigned Reservations");
			test_steps.add(
					"Successfully verified Reservation '" + ReservationName + "' Exist in Unassigned Reservations");
			tap.VerifyUnassignedReservation(driver, ReservationName, Utility.getCurrentDate("E, MMM dd"),
					Utility.GetNextDate(1, "E, MMM dd"), RoomClassAbb);
			app_logs.info("==========VERIFY UNASSIGNED RESERVATION DETAILS==========");
			test_steps.add("==========VERIFY UNASSIGNED RESERVATION DETAILS==========");
			test_steps.add("Reservation Name : " + ReservationName);
			test_steps.add("Arrival Date : " + Utility.getCurrentDate("E, MMM dd"));
			test_steps.add("Departure Date : " + Utility.GetNextDate(1, "E, MMM dd"));
			test_steps.add("Room Class Abbreviation : " + RoomClassAbb);
			app_logs.info("==========VERIFY UNASSIGNED RESERVATIONS OF ROOM CLASS '" + RoomClassAbb + "'==========");
			test_steps.add("==========VERIFY UNASSIGNED RESERVATIONS OF ROOM CLASS '" + RoomClassAbb + "'==========");

			AfterUnassignedReservationsRC = tap.GetUnassignedRoomClassCount(driver, RoomClassAbb);
			app_logs.info("Number of Unassigned Reservations of Room Class '" + RoomClassAbb + "' are '" + AfterUnassignedReservationsRC + "'");
			test_steps.add("Number of Unassigned Reservations of Room Class '" + RoomClassAbb + "' are '" + AfterUnassignedReservationsRC + "'");
			Assert.assertTrue(
					Integer.parseInt(AfterUnassignedReservationsRC) > Integer.parseInt(BeforeUnassignedReservationsRC),
					"Failed: Unassigned Count not increase");
			app_logs.info("Verified Room Class '" + RoomClassAbb + "' Unassigned Reservations has been incremented");
			test_steps.add("Verified Room Class '" + RoomClassAbb + "' Unassigned Reservations has been incremented");
		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Observe parking lots(Unassigned Reservations) in TapeChart",
						testName, "TapeChart", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Observe parking lots(Unassigned Reservations) in TapeChart",
						testName, "TapeChart", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		//Search Reservation and verify Room Class
		try {
			app_logs.info("==========SEARCH RESERVATION==========");
			test_steps.add("==========SEARCH RESERVATION==========");
			nav.navigateToReservations(driver);
			test_steps.add("Navigate Reservation ");
			app_logs.info("Navigate Reservation ");
			driver.navigate().refresh();
			ReservationSearch resSearch = new ReservationSearch();
			resSearch.basicSearch_WithResNumber(driver, ReservationNumber,false);
			test_steps.add("Reservation Successfully Searched : " + ReservationNumber);
			app_logs.info("Reservation Successfully Searched : " + ReservationNumber);
			app_logs.info("==========VERIFY ROOM==========");
			test_steps.add("==========VERIFY ROOM==========");
			resSearch.verifyReservationRoom(driver, ReservationNumber,roomClassName, test_steps);
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Search Reservation and verify Room Class", testName, "TapeChart",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Search Reservation and verify Room Class", testName, "Reservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheet name provided
		return Utility.getData("CreateUnassignedReservationTP", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}
}
