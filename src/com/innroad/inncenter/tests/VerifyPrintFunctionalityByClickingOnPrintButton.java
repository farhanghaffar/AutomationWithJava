package com.innroad.inncenter.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.innroad.inncenter.pageobjects.Account;
import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Folio;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Reports;
import com.innroad.inncenter.pageobjects.Reservation;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyPrintFunctionalityByClickingOnPrintButton extends TestCore {

	// Automation-1493
	private WebDriver driver = null;
	ArrayList<String> testNames = new ArrayList<>();
	ArrayList<String> testDescriptions = new ArrayList<>();
	ArrayList<String> testCategories = new ArrayList<>();
	public static String testName = "VerifyPrintFunctionalityByClickingOnPrintButton";
	public static String testDescription = "";
	public static String testCatagory = "";
	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = { "Reservation", "Reports" })
	public void verifyPrintFunctionalityByClickingOnPrintButton(String marketSegment, String referral, String property,
			String adult, String children, String isAssign, String isChecked, String salutation,
			String reservationFirstName, String reservationLastName, String roomClassName, String statusReserved,
			String guestFolio, String roomChargeCategory, String roomChargesLabel, String totalChargesLabel,
			String taxAndServiceChargesLabel, String statusInHouse, String guestStatement, String guestRegistration,
			String listReport, String mailingDetails, String detailedReservationList, String includeTotalRevenue,
			String reportSource, String reportId, String listReportHeading, String mailingDetailsHeading,
			String detailedReservationListHeading, String clientName) throws InterruptedException, IOException {

		testDescription = "Verify Print functionality by clicking on print button<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/667715' target='_blank'>"
				+ "Click here to open TestRail: C667715</a>";
		testCatagory = "Reservation";

		testNames.add(testName);
		testDescriptions.add(testDescription);
		testCategories.add(testCatagory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Navigation navigation = new Navigation();
		Account account = new Account();
		Reservation reservation = new Reservation();

		CPReservationPage cpReservation = new CPReservationPage();
		ReservationSearch reservationSearch = new ReservationSearch();
		Folio folio = new Folio();
		Reports reports = new Reports();

		String randomNumber = Utility.GenerateRandomNumber(999);

		// randomNumber = "56";

		reservationLastName = reservationLastName + randomNumber;
		String reservationName = reservationFirstName + " " + reservationLastName;
		String iconSource = null;
		String icon = null;
		String roomChargeDescription = null;
		String reservationNumber = null;
		String taxAndServiceChargesAmount = null;
		String fileName = null;
		String lines[] = null;
		int i = 0;
		String reportType = null;
		String reportHeading = null;
		String folioName = null;
		String spaAmountWithTax = null;
		String roomCharges = null;
		String roomChargesWithTax = null;
		String roomChargesTax = null;
		String totalCharges = null;
		String roomNumber = null;
		String spaTax = null;
		String lineItemData = null;
		String checkInPolicy = null;
		String timeZone = null;
		String expectedFileName = null;

		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);

			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver = getDriver();
			login_CP(driver);
			testSteps.add("Logged into the application");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Select Property
		try {

			String defaultProperty = navigation.getDefaultProperty(driver);

			if (!defaultProperty.equals(Utility.ReportsProperty)) {
				boolean isPropertyExist = navigation.isPropertyExist(driver, property);
				if (!isPropertyExist) {
					property = defaultProperty;

				}
			}

			testSteps.add("Selected property: " + property);
			app_logs.info("Selected property: " + property);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Select Property", testName, "SelectProperty", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Select Property", testName, "SelectProperty", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// Get Time Zone
		try {
			testSteps.add("===================GET PROPERTY TIME ZONE======================");
			app_logs.info("===================GET PROPERTY TIME ZONE======================");
			navigation.Setup(driver);
			testSteps.add("Navigate Setup");
			app_logs.info("Navigate Setup");
			navigation.Properties(driver);
			testSteps.add("Navigat Properties");
			app_logs.info("Navigat Properties");
			navigation.open_Property(driver, testSteps, property);
			testSteps.add("Open Property : " + property);
			app_logs.info("Open Property : " + property);
			navigation.click_PropertyOptions(driver, testSteps);
			timeZone = navigation.get_Property_TimeZone(driver);
			navigation.Reservation_Backward(driver);
			testSteps.add("Property Time Zone " + timeZone);
			app_logs.info("Property Time Zone " + timeZone);
			app_logs.info("Curret Time " + Utility.getCurrentDate("MMM dd,yyy: H:m:s", timeZone));
			testSteps.add("Curret Time " + Utility.getCurrentDate("MMM dd,yyy: H:m:s", timeZone));
			timeZone = "America/New_York";

			testSteps.add("Time Zone " + timeZone);
			app_logs.info("Time Zone " + timeZone);
			app_logs.info("Curret Time " + Utility.getCurrentDate("MMM dd,yyy: H:m:s", timeZone));
			testSteps.add("Curret Time " + Utility.getCurrentDate("MMM dd,yyy: H:m:s", timeZone));

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to get Time Zone", testName, "getTimeZone", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to get Time Zone", testName, "getTimeZone", driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		// Create rate
		try {
			app_logs.info("==========CREATE NEW RESERVATION==========");
			testSteps.add("==========CREATE NEW RESERVATION==========");

			// navigation.navigateToReservations(driver);
			testSteps.add("Navigate Reservations");
			app_logs.info("Navigate Reservations");
			cpReservation.click_NewReservation(driver, testSteps);

			String CheckInDate = Utility.getCurrentDate("MM/dd/yyyy", timeZone);
			String CheckoutDate = Utility.getNextDate(1, "MM/dd/yyyy", timeZone);
			try{
				cpReservation.EnterDate(driver, "start", CheckInDate);
			}catch(Exception newReservation){
				cpReservation.click_NewReservation(driver, getTestSteps);
			}
			testSteps.add("Select CheckIn date : " + CheckInDate);
			app_logs.info("Selecting checkin date : " + CheckInDate);
			cpReservation.EnterDate(driver, "end", CheckoutDate);
			testSteps.add("Select Checkout date : " + CheckoutDate);
			app_logs.info("Selecting checkin date : " + CheckoutDate);
			getTestSteps.clear();
			getTestSteps = cpReservation.enterAdult(driver, adult);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = cpReservation.enterChildren(driver, children);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = cpReservation.clickOnFindRooms(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = cpReservation.selectRoom(driver, getTestSteps, roomClassName, isAssign, "");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = cpReservation.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = cpReservation.verifyGuestProfileCheckoxChecked(driver, Boolean.parseBoolean(isChecked));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = cpReservation.enterGuestName(driver, getTestSteps, salutation, reservationFirstName,
					reservationLastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = cpReservation.selectReferral(driver, referral);
			testSteps.addAll(getTestSteps);

			cpReservation.clickBookNow(driver, testSteps);
			reservationNumber = cpReservation.get_ReservationConfirmationNumber(driver, testSteps);
			String reservationStatus = cpReservation.get_ReservationStatus(driver, testSteps);
			Assert.assertEquals(reservationStatus, statusReserved, "Failed: Reservation Status missmatched");
			cpReservation.clickCloseReservationSavePopup(driver, testSteps);
			roomNumber = cpReservation.get_RoomNumber(driver, testSteps, "yes");
			cpReservation.closeReservationTab(driver);
			testSteps.add("Close Reservation");
			app_logs.info("Close Reservation");

		} catch (Exception e) {
			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to VERIFY CREATED RATE IN RESERVATION", testName, "VerifyRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to VERIFY CREATED RATE IN Reservation", testName, "VerifyRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

//reservationNumber = "17264198";
//timeZone = "America/New_York";
		// SEARCH RESERVATION
		try {
			app_logs.info("==========SEARCH RESERVATION==========");
			testSteps.add("==========SEARCH RESERVATION==========");
			reservationSearch.basicSearchWithResNumber(driver, reservationNumber, false);

			testSteps.add("Reservation Successfully Searched : " + reservationNumber);
			app_logs.info("Reservation Successfully Searched : " + reservationNumber);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to search reservation", testName, "SearchReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to search reservation", testName, "SearchReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		reportType = listReport;
		reportHeading = listReportHeading;
		try {
			testSteps.add(
					"===================== DOWNLOAD <b>" + reportType.toUpperCase() + "</b> =====================");
			app_logs.info(
					"===================== DOWNLOAD <b>" + reportType.toUpperCase() + "</b> =====================");
			cpReservation.clickOnPrintIcon(driver);
			testSteps.add("Click Print Icon");
			app_logs.info("Click Print Icon");
			getTestSteps.clear();
			getTestSteps = cpReservation.selectReportTypeButton(driver, reportType, "radio", true, getTestSteps);
			testSteps.addAll(getTestSteps);
			cpReservation.clickPrintReservationReports(driver);
			testSteps.add("Click Print Reservation Report");
			app_logs.info("Click Print Reservation Report");
			cpReservation.waitTillNumberOfTabsExist(driver, 2);
			testSteps.add("New tab opened");
			app_logs.info("New tab opened");
			Utility.switchTab(driver, 1);
			testSteps.add("Switch tab");
			app_logs.info("Switch tab");
			folioName = "Folio Name : " + guestFolio;
			if (cpReservation.reportDisplayed(driver, reportId, testSteps)) {
				fileName = cpReservation.downloadReport(driver, reportSource, reportId);
				testSteps.add("Download Report");
				app_logs.info("Download Report");
				testSteps.add("=====================VERIFY <b>" + reportType.toUpperCase()
						+ "</b> REPORT =====================");
				app_logs.info("===================== VERIFY <b>" + reportType.toUpperCase()
						+ "</b> REPORT =====================");
				testSteps.add("Actual File Name '" + fileName + "'");
				app_logs.info("Actual File Name '" + fileName + "'");
				expectedFileName = clientName + "_" + reportHeading.replace(" ", "") + "_"
						+ Utility.getCurrentDate("yyyyMMdd", timeZone);
				testSteps.add("Expect File Name '" + expectedFileName + "'");
				app_logs.info("Expect File Name '" + expectedFileName + "'");
				Assert.assertTrue(fileName.contains(expectedFileName),
						"Failed : '" + reportType + "' File Name missmatched");
				testSteps.add("Verified Report Name");
				app_logs.info("Verified Report Name");

				lines = Utility.checkPDFArray(fileName);
				System.out.println("line size : " + lines.length);
				i = 0;
				for (String line : lines) {
					Utility.app_logs.info("line : " + i + " '" + line + "'");
					i++;
				}
				cpReservation.verifyDataExistInReport(driver, lines,
						clientName.replaceAll("_", " ") + " " + reportHeading, true);
				testSteps.add("Verified Report Heading '" + reportHeading + "'");
				app_logs.info("Verified Report Heading '" + reportHeading + "'");
				Utility.closeTabsExcept(driver, 1);
				testSteps.add("Close report's tab and switch to first tab");
				app_logs.info("Close report's tab and switch to first tab");

			} else {
				testSteps.add("Failed: <b>" + reportType.toUpperCase() + "</b> Report is not displaying");
			}
		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify <b>" + reportType.toUpperCase() + "</b> Report", testName,
						"PDFReading", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify <b>" + reportType.toUpperCase() + "</b> Report", testName,
						"PDFReading", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// From Here
		reportType = guestStatement;
		reportHeading = guestStatement;
		try {
			testSteps.add("===================== DOWNLOAD <b>" + reportType.toUpperCase()
					+ "</b> REPORT =====================");
			app_logs.info("===================== DOWNLOAD <b>" + reportType.toUpperCase()
					+ "</b> REPORT =====================");
			cpReservation.clickOnPrintIcon(driver);
			testSteps.add("Click Print Icon");
			app_logs.info("Click Print Icon");
			getTestSteps.clear();
			getTestSteps = cpReservation.selectReportTypeButton(driver, reportType, "radio", true, getTestSteps);
			testSteps.addAll(getTestSteps);
			cpReservation.clickPrintReservationReports(driver);
			testSteps.add("Click Print Reservation Report");
			app_logs.info("Click Print Reservation Report");
			cpReservation.waitTillNumberOfTabsExist(driver, 2);
			testSteps.add("New tab opened");
			app_logs.info("New tab opened");
			Utility.switchTab(driver, 1);
			testSteps.add("Switch tab");
			app_logs.info("Switch tab");
			folioName = "Folio Name : " + guestFolio;
			if (cpReservation.reportDisplayed(driver, reportId, testSteps)) {
				fileName = cpReservation.downloadReport(driver, reportSource, reportId);
				testSteps.add("Download Report");
				app_logs.info("Download Report");
				testSteps.add("=====================VERIFY <b>" + reportType.toUpperCase()
						+ "</b> REPORT =====================");
				app_logs.info("===================== VERIFY <b>" + reportType.toUpperCase()
						+ "</b> REPORT =====================");
				testSteps.add("Actual File Name '" + fileName + "'");
				app_logs.info("Actual File Name '" + fileName + "'");
				expectedFileName = clientName + "_" + reportHeading.replace(" ", "") +"WithTaxes_"
						+ Utility.getCurrentDate("yyyyMMdd", timeZone);
				testSteps.add("Expect File Name '" + expectedFileName + "'");
				app_logs.info("Expect File Name '" + expectedFileName + "'");
				Assert.assertTrue(fileName.contains(expectedFileName),
						"Failed : '" + reportType + "' File Name missmatched");
				testSteps.add("Verified Report Name");
				app_logs.info("Verified Report Name");

				lines = Utility.checkPDFArray(fileName);
				System.out.println("line size : " + lines.length);
				i = 0;
				for (String line : lines) {
					Utility.app_logs.info("line : " + i + " '" + line + "'");
					i++;
				}
				cpReservation.verifyDataExistInReport(driver, lines,
						 property + " " + reportHeading.replaceAll(" ", "  "), false);
				testSteps.add("Verified Report Heading '" + reportHeading + "'");
				app_logs.info("Verified Report Heading '" + reportHeading + "'");
				Utility.closeTabsExcept(driver, 1);
				testSteps.add("Close report's tab and switch to first tab");
				app_logs.info("Close report's tab and switch to first tab");

			} else {
				testSteps.add("Failed: <b>" + reportType.toUpperCase() + "</b> Report is not displaying");
			}
		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify <b>" + reportType.toUpperCase() + "</b> Report", testName,
						"PDFReading", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify <b>" + reportType.toUpperCase() + "</b> Report", testName,
						"PDFReading", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		reportType = guestRegistration;
		reportHeading = guestRegistration;
		try {
			testSteps.add("===================== DOWNLOAD <b>" + reportType.toUpperCase()
					+ "</b> REPORT =====================");
			app_logs.info("===================== DOWNLOAD <b>" + reportType.toUpperCase()
					+ "</b> REPORT =====================");
			cpReservation.clickOnPrintIcon(driver);
			testSteps.add("Click Print Icon");
			app_logs.info("Click Print Icon");
			getTestSteps.clear();
			getTestSteps = cpReservation.selectReportTypeButton(driver, reportType, "radio", true, getTestSteps);
			testSteps.addAll(getTestSteps);
			cpReservation.clickPrintReservationReports(driver);
			testSteps.add("Click Print Reservation Report");
			app_logs.info("Click Print Reservation Report");
			cpReservation.waitTillNumberOfTabsExist(driver, 2);
			testSteps.add("New tab opened");
			app_logs.info("New tab opened");
			Utility.switchTab(driver, 1);
			testSteps.add("Switch tab");
			app_logs.info("Switch tab");
			folioName = "Folio Name : " + guestFolio;
			if (cpReservation.reportDisplayed(driver, reportId, testSteps)) {
				fileName = cpReservation.downloadReport(driver, reportSource, reportId);
				testSteps.add("Download Report");
				app_logs.info("Download Report");
				testSteps.add("=====================VERIFY <b>" + reportType.toUpperCase()
						+ "</b> REPORT =====================");
				app_logs.info("===================== VERIFY <b>" + reportType.toUpperCase()
						+ "</b> REPORT =====================");
				testSteps.add("Actual File Name '" + fileName + "'");
				app_logs.info("Actual File Name '" + fileName + "'");
				expectedFileName = clientName + "_" + reportHeading.replace(" ", "") + "FormWithTaxes_"
						+ Utility.getCurrentDate("yyyyMMdd", timeZone);
				testSteps.add("Expect File Name '" + expectedFileName + "'");
				app_logs.info("Expect File Name '" + expectedFileName + "'");
				Assert.assertTrue(fileName.contains(expectedFileName),
						"Failed : '" + reportType + "' File Name missmatched");
				testSteps.add("Verified Report Name");
				app_logs.info("Verified Report Name");

				lines = Utility.checkPDFArray(fileName);
				System.out.println("line size : " + lines.length);
				i = 0;
				for (String line : lines) {
					Utility.app_logs.info("line : " + i + " '" + line + "'");
					i++;
				}
				cpReservation.verifyDataExistInReport(driver, lines,
						 property + " " + reportHeading, false);
				testSteps.add("Verified Report Heading '" + reportHeading + "'");
				app_logs.info("Verified Report Heading '" + reportHeading + "'");
				Utility.closeTabsExcept(driver, 1);
				testSteps.add("Close report's tab and switch to first tab");
				app_logs.info("Close report's tab and switch to first tab");

			} else {
				testSteps.add("Failed: <b>" + reportType.toUpperCase() + "</b> Report is not displaying");
			}
		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify <b>" + reportType.toUpperCase() + "</b> Report", testName,
						"PDFReading", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify <b>" + reportType.toUpperCase() + "</b> Report", testName,
						"PDFReading", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		reportType = mailingDetails;
		reportHeading = mailingDetailsHeading;
		try {
			testSteps.add("===================== DOWNLOAD <b>" + reportType.toUpperCase()
					+ "</b> REPORT =====================");
			app_logs.info("===================== DOWNLOAD <b>" + reportType.toUpperCase()
					+ "</b> REPORT =====================");
			cpReservation.clickOnPrintIcon(driver);
			testSteps.add("Click Print Icon");
			app_logs.info("Click Print Icon");
			getTestSteps.clear();
			getTestSteps = cpReservation.selectReportTypeButton(driver, reportType, "radio", true, getTestSteps);
			testSteps.addAll(getTestSteps);
			cpReservation.clickPrintReservationReports(driver);
			testSteps.add("Click Print Reservation Report");
			app_logs.info("Click Print Reservation Report");
			cpReservation.waitTillNumberOfTabsExist(driver, 2);
			testSteps.add("New tab opened");
			app_logs.info("New tab opened");
			Utility.switchTab(driver, 1);
			testSteps.add("Switch tab");
			app_logs.info("Switch tab");
			folioName = "Folio Name : " + guestFolio;
			if (cpReservation.reportDisplayed(driver, reportId, testSteps)) {
				fileName = cpReservation.downloadReport(driver, reportSource, reportId);
				testSteps.add("Download Report");
				app_logs.info("Download Report");
				testSteps.add("=====================VERIFY <b>" + reportType.toUpperCase()
						+ "</b> REPORT =====================");
				app_logs.info("===================== VERIFY <b>" + reportType.toUpperCase()
						+ "</b> REPORT =====================");
				testSteps.add("Actual File Name '" + fileName + "'");
				app_logs.info("Actual File Name '" + fileName + "'");
				expectedFileName = clientName + "_" + reportHeading.replace(" ", "") + "_"
						+ Utility.getCurrentDate("yyyyMMdd", timeZone);
				testSteps.add("Expect File Name '" + expectedFileName + "'");
				app_logs.info("Expect File Name '" + expectedFileName + "'");
				Assert.assertTrue(fileName.contains(expectedFileName),
						"Failed : '" + reportType + "' File Name missmatched");
				testSteps.add("Verified Report Name");
				app_logs.info("Verified Report Name");

				lines = Utility.checkPDFArray(fileName);
				System.out.println("line size : " + lines.length);
				i = 0;
				for (String line : lines) {
					Utility.app_logs.info("line : " + i + " '" + line + "'");
					i++;
				}
				cpReservation.verifyDataExistInReport(driver, lines,
						 reportHeading + clientName.replaceAll("_", " "), true);
				testSteps.add("Verified Report Heading '" + reportHeading + "'");
				app_logs.info("Verified Report Heading '" + reportHeading + "'");
				Utility.closeTabsExcept(driver, 1);
				testSteps.add("Close report's tab and switch to first tab");
				app_logs.info("Close report's tab and switch to first tab");

			} else {
				testSteps.add("Failed: <b>" + reportType.toUpperCase() + "</b> Report is not displaying");
			}
		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify <b>" + reportType.toUpperCase() + "</b> Report", testName,
						"PDFReading", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify <b>" + reportType.toUpperCase() + "</b> Report", testName,
						"PDFReading", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		reportType = detailedReservationList;
		reportHeading = detailedReservationListHeading;
		try {
			testSteps.add("===================== DOWNLOAD <b>" + reportType.toUpperCase()
					+ "</b> REPORT =====================");
			app_logs.info("===================== DOWNLOAD <b>" + reportType.toUpperCase()
					+ "</b> REPORT =====================");
			cpReservation.clickOnPrintIcon(driver);
			testSteps.add("Click Print Icon");
			app_logs.info("Click Print Icon");
			getTestSteps.clear();
			getTestSteps = cpReservation.selectReportTypeButton(driver, reportType, "radio", true, getTestSteps);
			testSteps.addAll(getTestSteps);
			cpReservation.clickPrintReservationReports(driver);
			testSteps.add("Click Print Reservation Report");
			app_logs.info("Click Print Reservation Report");
			cpReservation.waitTillNumberOfTabsExist(driver, 2);
			testSteps.add("New tab opened");
			app_logs.info("New tab opened");
			Utility.switchTab(driver, 1);
			testSteps.add("Switch tab");
			app_logs.info("Switch tab");
			folioName = "Folio Name : " + guestFolio;
			if (cpReservation.reportDisplayed(driver, reportId, testSteps)) {
				fileName = cpReservation.downloadReport(driver, reportSource, reportId);
				testSteps.add("Download Report");
				app_logs.info("Download Report");
				testSteps.add("=====================VERIFY <b>" + reportType.toUpperCase()
						+ "</b> REPORT =====================");
				app_logs.info("===================== VERIFY <b>" + reportType.toUpperCase()
						+ "</b> REPORT =====================");
				testSteps.add("Actual File Name '" + fileName + "'");
				app_logs.info("Actual File Name '" + fileName + "'");
				expectedFileName =  "ReportingService_" + reportHeading.replace(" ", "") + "_"
						+ Utility.getCurrentDate("yyyyMMdd", timeZone);
				testSteps.add("Expect File Name '" + expectedFileName + "'");
				app_logs.info("Expect File Name '" + expectedFileName + "'");
				Assert.assertTrue(fileName.contains(expectedFileName),
						"Failed : '" + reportType + "' File Name missmatched");
				testSteps.add("Verified Report Name");
				app_logs.info("Verified Report Name");

				lines = Utility.checkPDFArray(fileName);
				System.out.println("line size : " + lines.length);
				i = 0;
				for (String line : lines) {
					Utility.app_logs.info("line : " + i + " '" + line + "'");
					i++;
				}
				cpReservation.verifyDataExistInReport(driver, lines,
						 reportHeading , false);
				testSteps.add("Verified Report Heading '" + reportHeading + "'");
				app_logs.info("Verified Report Heading '" + reportHeading + "'");
				Utility.closeTabsExcept(driver, 1);
				testSteps.add("Close report's tab and switch to first tab");
				app_logs.info("Close report's tab and switch to first tab");

			} else {
				testSteps.add("Failed: <b>" + reportType.toUpperCase() + "</b> Report is not displaying");
			}
		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify <b>" + reportType.toUpperCase() + "</b> Report", testName,
						"PDFReading", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify <b>" + reportType.toUpperCase() + "</b> Report", testName,
						"PDFReading", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// To here

		// Generate Report
		try {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Generate Report", testName, "GenerateReport", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Generate Report", testName, "GenerateReport", driver);
			} else {
				Assert.assertTrue(false);
			}

		}

	}

	@DataProvider
	public Object[][] getData() {

		// return test data from the sheetname provided
		return Utility.getData("VerifyReportPrintFunctionality", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		 driver.quit();

	}

}
