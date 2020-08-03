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
import com.innroad.inncenter.pageobjects.Reports;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class GenerateNetSalesReportForRoomClassAndWeekConsumed extends TestCore {

	private WebDriver driver = null;
	public static String test_name = "";
	public static String test_description = "";
	public static String test_catagory = "";
	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {

		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void generateNetSalesReportForRoomClassAndWeekConsumed(String adults, String children, String rateplan,
			String promoCode, String jsRoomClass, String roomClass, String isAssign, String salutation,
			String guestFirstName, String guestLastName, String phoneNumber, String altenativePhone, String email,
			String account, String accountType, String address1, String address2, String address3, String city,
			String country, String state, String postalCode, String isGuesProfile, String referral, String status,
			String groupByRoomClass, String groupByWeekConsumed, String netSalesReport, String propertyName)
			throws InterruptedException, IOException {

		test_name = "GenerateNetSalesReportForRoomClassAndWeekConsumed";
		test_description = "Generate NetSales Report For RoomClass And Week Consumed.<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682483' target='_blank'>"
				+ "Click here to open TestRail: C682483</a>";
		test_catagory = "NetSalesReport";
		String testName = test_name;

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		ReservationSearch reservationSearch = new ReservationSearch();
		CPReservationPage reservationPage = new CPReservationPage();
		Navigation navigation = new Navigation();
		Reports report = new Reports();

		String randomNumber = Utility.GenerateRandomNumber();
		guestLastName = guestLastName + randomNumber;
		int days = 0;
		String reportStartDate = null;
		String reportEndDate = null;
		String checkInDate = null;
		String checkOutDate = null;
		String firstReservationNumber = null;
		String secondReservationNumber = null;
		String thirdReservationNumber = null;
		String firstReservationStatus = null;
		String secondReservationStatus = null;
		String thirdReservationStatus = null;
		int numberOfReservation = 0;

		if (roomClass.equalsIgnoreCase("N/A")) {
			roomClass = "Double Bed Room";
		}
		if (jsRoomClass.equalsIgnoreCase("N/A")) {
			jsRoomClass = "Junior Suites";
		}
		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);

			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver = getDriver();
			login_NONGS(driver);
			testSteps.add("========== Logged into the application ==========");
			app_logs.info("========== Logged into the application ==========");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
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
		try {
			app_logs.info("========== SELECT PROPERTY ==========");
			testSteps.add("========== SELECT PROPERTY ==========");

			getTestSteps.clear();
			getTestSteps = navigation.selectProperty(driver, propertyName);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to select property", testName, "QAProperty", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to select property", testName, "QAProperty", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Search Reservation Advance Search
		try {
			testSteps.add("========== SEARCH EMPTY WEEK FOR NEW RESERVATIONS ==========");
			app_logs.info("========== SEARCH EMPTY WEEK FOR NEW RESERVATIONS ==========");

			getTestSteps.clear();
			getTestSteps = reservationSearch.clickAdvancedSearch(driver);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Click Advance Search", testName, "AdvanceSearch", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Click Advance Search", testName, "AdvanceSearch", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("Searching for the week with no reservation");
			app_logs.info("Searching for the week with no reservation");

			days = reservationSearch.searchWeekWithNoReservation(driver);
			app_logs.info("Days : " + days);

			reportStartDate = ESTTimeZone.getComingSunday(days = days - 7);
			app_logs.info("Days : " + days);

			checkOutDate = ESTTimeZone.getComingSunday(days = days + 1);
			app_logs.info("Days : " + days);

			days = (days - 1) + 7;
			reportEndDate = ESTTimeZone.getComingSunday(days);
			app_logs.info("Days : " + days);

			reportStartDate = ESTTimeZone.reformatDate(reportStartDate, "MM/dd/yyyy", "dd/MM/yyyy");
			checkInDate = reportStartDate;
			checkOutDate = ESTTimeZone.reformatDate(checkOutDate, "MM/dd/yyyy", "dd/MM/yyyy");
			reportEndDate = ESTTimeZone.reformatDate(reportEndDate, "MM/dd/yyyy", "dd/MM/yyyy");
			app_logs.info("checkInDate + checkoutDate  + ReportStartDate +  ReportEndDate: " + checkInDate + " "
					+ checkOutDate + " " + reportStartDate + " " + reportEndDate);

			testSteps.add("Week found. Start date : " + reportStartDate + " End date : " + reportEndDate);
			app_logs.info("Week found. Start date : " + reportStartDate + " End date : " + reportEndDate);

			getTestSteps.clear();
			getTestSteps = reservationSearch.clickCloseSearch(driver);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Search Empty Week", testName, "AdvanceSearch", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Search Empty Week", testName, "AdvanceSearch", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// Reservation
		try {
			testSteps.add("========== CREATING FIRST RESERVATION WITH ROOMCLASS : " + jsRoomClass + " ==========");
			app_logs.info("========== CREATING FIRST RESERVATION WITH ROOMCLASS : " + jsRoomClass + " ==========");

			getTestSteps.clear();
			getTestSteps = reservationPage.click_NewReservation(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectCheckInDate(driver, checkInDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectCheckoutDate(driver, checkOutDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterAdult(driver, adults);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterChildren(driver, children);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnFindRooms(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectRoom(driver, jsRoomClass, isAssign, account);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.VerifyGuestProfileCheckox(driver, Boolean.parseBoolean(isGuesProfile));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterGuestName(driver, getTestSteps, salutation, guestFirstName,
					guestLastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectReferral(driver, referral);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickBookNow(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			firstReservationNumber = reservationPage.getReservationConfirmationNumber(driver, getTestSteps);
			testSteps.add("Reservation Confirmation Number : " + firstReservationNumber);
			app_logs.info("Reservation Confirmation Number : " + firstReservationNumber);

			firstReservationStatus = reservationPage.getReservationStatus(driver);
			testSteps.add("Reservation Status : " + firstReservationStatus);
			app_logs.info("Reservation Status : " + firstReservationStatus);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickCloseReservationSavePopup(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create First reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to creae First reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			reservationPage.closeReservationTab(driver);
			testSteps.add("Successfully close opened tab");
			app_logs.info("Closed the reservation");

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Close First Reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Close First Reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Reservation
		try {
			testSteps.add("========== CREATING SECOND RESERVATION WITH ROOMCLASS : " + jsRoomClass + " ==========");
			app_logs.info("========== CREATING SECOND RESERVATION WITH ROOMCLASS : " + jsRoomClass + " ==========");

			getTestSteps.clear();
			getTestSteps = reservationPage.click_NewReservation(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectCheckInDate(driver, checkInDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectCheckoutDate(driver, checkOutDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterAdult(driver, adults);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterChildren(driver, children);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnFindRooms(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectRoom(driver, jsRoomClass, isAssign, account);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.VerifyGuestProfileCheckox(driver, Boolean.parseBoolean(isGuesProfile));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterGuestName(driver, getTestSteps, salutation, guestFirstName,
					guestLastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectReferral(driver, referral);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickBookNow(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			secondReservationNumber = reservationPage.getReservationConfirmationNumber(driver, getTestSteps);
			testSteps.add("Reservation Confirmation Number : " + secondReservationNumber);
			app_logs.info("Reservation Confirmation Number : " + secondReservationNumber);

			secondReservationStatus = reservationPage.getReservationStatus(driver);
			testSteps.add("Reservation Status : " + secondReservationStatus);
			app_logs.info("Reservation Status : " + secondReservationStatus);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickCloseReservationSavePopup(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create Second Reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to creae Second Reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			reservationPage.closeReservationTab(driver);
			testSteps.add("Closed the reservation");
			app_logs.info("Closed the reservation");

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Close Second Reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Close Second Reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Reservation
		try {
			testSteps.add("========== CREATING THIRD RESERVATION WITH ROOMCLASS : " + roomClass + " ==========");
			app_logs.info("========== CREATING THIRD RESERVATION WITH ROOMCLASS : " + roomClass + " ==========");

			getTestSteps.clear();
			getTestSteps = reservationPage.click_NewReservation(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectCheckInDate(driver, checkInDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectCheckoutDate(driver, checkOutDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterAdult(driver, adults);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterChildren(driver, children);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnFindRooms(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectRoom(driver, roomClass, isAssign, account);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.VerifyGuestProfileCheckox(driver, Boolean.parseBoolean(isGuesProfile));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterGuestName(driver, getTestSteps, salutation, guestFirstName,
					guestLastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectReferral(driver, referral);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickBookNow(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			thirdReservationNumber = reservationPage.getReservationConfirmationNumber(driver, getTestSteps);
			testSteps.add("Reservation confirmation number: " + thirdReservationNumber);
			app_logs.info("Reservation confirmation number: " + thirdReservationNumber);

			thirdReservationStatus = reservationPage.getReservationStatus(driver);
			testSteps.add("Reservation Status : " + thirdReservationStatus);
			app_logs.info("Reservation Status : " + thirdReservationStatus);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickCloseReservationSavePopup(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create Third Reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to creae Third Reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			reservationPage.closeReservationTab(driver);
			testSteps.add("Closed the reservation");
			app_logs.info("Closed the reservation");
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Close Third Reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Close Third Reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("========== Verifying NetSales report grouped by " + groupByRoomClass + " ==========");
			app_logs.info("========== Verifying NetSales report grouped by " + groupByRoomClass + " ==========");

			navigation.Reports(driver);
			app_logs.info("Navigated To Reports");
			testSteps.add("Navigated To Reports");

			navigation.NetSales(driver);
			app_logs.info("Navigated To NetSales");
			testSteps.add("Navigated To NetSales");

			getTestSteps.clear();
			getTestSteps = report.netSalesGroupBy(driver, groupByRoomClass);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = report.selectNetSalesFromDate(driver, reportStartDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = report.selectNetSalesToDate(driver, reportEndDate);
			testSteps.addAll(getTestSteps);

			report.NetSalesGoButton(driver);
			app_logs.info("Clicked Go button");
			testSteps.add("Clicked Go button");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to enter NetSales report prerequisite", testName,
						"NetSalesReportPrerequisite", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to enter NetSales report prerequisite", testName,
						"NetSalesReportPrerequisite", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		try {
			getTestSteps.clear();
			getTestSteps = report.verifyNetSalesReport(driver, netSalesReport, groupByRoomClass, propertyName,
					reportStartDate, reportEndDate, jsRoomClass, roomClass, numberOfReservation);
			testSteps.addAll(getTestSteps);
			testSteps.add("Verified NetSales report grouped by " + groupByRoomClass);
			app_logs.info("Verified NetSales report grouped by " + groupByRoomClass);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Validate Report", testName, "ValidateReport", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Validate Report", testName, "ValidateReport", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("========== Verifying NetSales report grouped by " + groupByWeekConsumed + " ==========");
			app_logs.info("========== Verifying NetSales report grouped by " + groupByWeekConsumed + " ==========");

			days = days - 1;
			reportEndDate = ESTTimeZone.getComingSunday(days);
			reportEndDate = ESTTimeZone.reformatDate(reportEndDate, "MM/dd/yyyy", "dd/MM/yyyy");
			app_logs.info("Days : " + days);
			app_logs.info("ReportEndDate : " + reportEndDate);

			getTestSteps.clear();
			getTestSteps = report.netSalesGroupBy(driver, groupByWeekConsumed);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = report.selectNetSalesFromDate(driver, reportStartDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = report.selectNetSalesToDate(driver, reportEndDate);
			testSteps.addAll(getTestSteps);

			report.NetSalesGoButton(driver);
			app_logs.info("Clicked Go button");
			testSteps.add("Clicked Go button");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to enter NetSales report prerequisite", testName,
						"NetSalesReportPrerequisite", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to enter NetSales report prerequisite", testName,
						"NetSalesReportPrerequisite", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		try {
			numberOfReservation = 3;
			getTestSteps.clear();
			getTestSteps = report.verifyNetSalesReport(driver, netSalesReport, groupByWeekConsumed, propertyName,
					reportStartDate, reportEndDate, jsRoomClass, roomClass, numberOfReservation);
			testSteps.addAll(getTestSteps);

			testSteps.add("Verified NetSales report grouped by " + groupByWeekConsumed);
			app_logs.info("Verified NetSales report grouped by " + groupByWeekConsumed);

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to validate report group by week consumed", testName, "ValidateReport",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to validate report group by week consumed", testName, "ValidateReport",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}
	}

	@DataProvider
	public Object[][] getData() {

		// return test data from the sheetname provided
		return Utility.getData("GenerateNetSalesReportRCWC", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
