package com.innroad.inncenter.tests;

import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Folio;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.RoomMaintenance;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class BulkCheckInCheckOutReservations extends TestCore {

	private WebDriver driver = null;
	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();
	ArrayList<String> testName = new ArrayList<>();
	ArrayList<String> testDescription = new ArrayList<>();
	ArrayList<String> testCatagory = new ArrayList<>();

	
	@BeforeTest(alwaysRun = true)
	public void checkRunMode() throws InterruptedException, IOException {

		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable("BulkCheckInCheckOutReservations", excel))
			throw new SkipException("Skipping the test - " + testName);

	}

	@Test(dataProvider = "getData", groups = "Reservations")
	public void bulkCheckInCheckOutReservations(String referral, String firstName1, String lastName1, String account,
			String adult, String children, String isAssign, String isChecked, String salutation, String paymentType,
			String cardNumber, String cardName, String cardExpDate, String firstName2, String lastName2,
			String firstName3, String lastName3, String firstName4, String lastName4, String firstName5,
			String lastName5, String message, String roomClassName, String roomClassAbbreviation, String bedsCount,
			String maxAdults, String maxPersons, String roomQuantity, String additionalAdult, String additionalChild,
			String baseAmount, String rateName, String ratePolicy, String rateDescription, String reservedListSize,
			String quoteListSize, String associateSession, String ReservationCategory, String paymentCategory)
			throws InterruptedException {

		String scriptName = "BulkCheckInCheckOutReservations";
		String description = "Verifying the Bulk Check In and Check Out of a Reservation<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/667707' target='_blank'>"
				+ "Click here to open TestRail: C667707</a>";
		String testcatagory = "Reservations";
		testName.add(scriptName);
		testCatagory.add(testcatagory);
		testDescription.add(description);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + scriptName + " TEST.");
		app_logs.info("##################################################################################");

		CPReservationPage reservationPage = new CPReservationPage();
		Navigation navigation = new Navigation();
		ReservationSearch reservationSearch = new ReservationSearch();
		RoomClass roomClass = new RoomClass();
		Rate rate = new Rate();

		String creditCardNumber = "";
		String roomNumber1 = "";
		String roomNumber4 = "";

		String reservationNumber1 = "";
		String reservationNumber4 = "";

		String randomNumber = Utility.GenerateRandomNumber();
		lastName1 = lastName1 + randomNumber;
		lastName3 = lastName3 + randomNumber;

		String guestName1 = firstName3 + " " + lastName3;
		String guestName4 = firstName1 + " " + lastName1;
		String allReservation = "";
		String enterAmount = "0";
		String getRoomChargesInTripSummary = "";
		String getTaxandServicesInTripSummary = "";
		String getTotalTripSummary = "";
		String getBalanceFromTripSummary = "";
		String expecetdAmount = "";
		String tempraryRoomClass = roomClassName;
		Folio folio = new Folio();

		try {
			if (!Utility.insertTestName.containsKey(scriptName)) {
				Utility.insertTestName.put(scriptName, scriptName);
				Utility.reTry.put(scriptName, 0);

			} else {
				Utility.reTry.replace(scriptName, 1);
			}
			driver = getDriver();
			login_CP(driver);
			testSteps.add("Logged into the application");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			testSteps.add("==========DELETE ROOM CLASSE==========");

			navigation.Setup(driver);
			testSteps.add("Click on setup");

			navigation.RoomClass(driver);
			testSteps.add("Click on room classes");

			boolean isRoomClassExist = roomClass.searchClass(driver, roomClassName);
			app_logs.info("Search");
			if (isRoomClassExist) {
				roomClass.deleteRoomClass(driver, roomClassName);
				testSteps.add("Delete room class successfully");
				app_logs.info("Delete room class successfully");
			}

		}

		catch (Exception e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
			Utility.updateReport(e, "Failed to delete room class", scriptName, "RoomClasses", driver);
		} catch (Error e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
			Utility.updateReport(e, "Failed to delete room class", scriptName, "RoomClasses", driver);
		}

		try {
			testSteps.add("===== CREATE NEW ROOM CLASS =====");

			navigation.NewRoomClass(driver);
			testSteps.add("Click on new room class button");

			roomClassName = roomClassName + randomNumber;
			roomClassAbbreviation = roomClassAbbreviation + randomNumber;
			try {
				getTestSteps.clear();
				getTestSteps = roomClass.createRoomClass(driver, roomClassName, roomClassAbbreviation, bedsCount, maxAdults,
						maxPersons, roomQuantity, test, getTestSteps);
				testSteps.addAll(getTestSteps);
			} catch (Exception e) {
				getTestSteps.clear();

				roomClass.roomClassInformation(driver, roomClassName, roomClassAbbreviation, bedsCount, 
						maxAdults, maxPersons,
						roomQuantity, test, getTestSteps);
				testSteps.addAll(getTestSteps);

			}

			testSteps.add("Sccessfully Created New RoomClass " + roomClassName + " Abb : " + roomClassAbbreviation);
			app_logs.info("Sccessfully Created New RoomClass" + roomClassName + " Abb : " + roomClassAbbreviation);

			roomClass.closeRoomClassTab(driver);
			testSteps.add("Close created room class tab");
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create room class successfully", scriptName, "NewRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create room class successfully", scriptName, "NewRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {

			testSteps.add("==========DELETE ALL RATES THAT START WITH NAME OF"+rateName+" ==========");
			try {
				navigation.backToReservation(driver);
			} catch (Exception e) {
				navigation.reservationBackward3(driver);
			}
			navigation.Inventory(driver);
			testSteps.add("Navigate to Inventory");
			app_logs.info("Navigate to Inventory");
		
			getTestSteps.clear();
			getTestSteps = navigation.secondaryRatesMenuItem(driver);
			testSteps.addAll(getTestSteps);

			// start new method for delete rate
			getTestSteps.clear();
			getTestSteps = rate.deleteRates(driver, rateName);
			testSteps.addAll(getTestSteps);
			testSteps.add("New Rate has been Deleted successfully");
			app_logs.info("New Rate has been Deleted successfully");


		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to delete a rate ", scriptName, "DeleteRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to delete a rate ", scriptName, "DeleteRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Create Rate with
		try {
			testSteps.add("==========CREATE NEW RATE==========");
			rateName = rateName + randomNumber;
			String DisplayName = rateName;
			getTestSteps.clear();
			getTestSteps = rate.enterRateInfo(driver, rateName, maxAdults, maxPersons, baseAmount, additionalAdult,
					additionalChild, DisplayName, 
					ratePolicy, rateDescription, roomClassName, associateSession);

			testSteps.addAll(getTestSteps);
			
			
			testSteps.add("New Rate With Name : " + rateName + " Created With & Verified ");
			app_logs.info("New Rate With Name : " + rateName + " Created With & Verified ");
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create  a rate ", scriptName, "NewRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create  a rate ", scriptName, "NewRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("==========CREATEING NEW QUOTE RESERVATION==========");
			app_logs.info("==========CREATEING NEW QUOTE RESERVATION==========");

			navigation.navigateToReservations(driver);
			testSteps.add("Navigate to Reservations");

			getTestSteps.clear();
			getTestSteps = reservationPage.click_NewReservation(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.checkInDate(driver, 0);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.checkOutDate(driver, +1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterAdult(driver, adult);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterChildren(driver, children);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnFindRooms(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectRoom(driver, getTestSteps, roomClassName, isAssign, account);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.verifyGuestProfileCheckoxChecked(driver, Boolean.parseBoolean(isChecked));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterGuestName(driver, getTestSteps, salutation, firstName1, lastName1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservationPage.enterPaymentDetails(driver, getTestSteps, paymentType, cardNumber, cardName, cardExpDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectReferral(driver, referral);
			testSteps.addAll(getTestSteps);

			reservationPage.clickOnQuote(driver);
			testSteps.add("Click on Save Quote");

			reservationNumber4 = reservationPage.getReservationConfirmationNumber(driver, getTestSteps);
			testSteps.add("Reservation confirmation number: " + reservationNumber4);
			app_logs.info("Reservation confirmation number: " + reservationNumber4);

			reservationPage.clickCloseReservationSavePopup(driver, getTestSteps);
			testSteps.add("Sucessfully close save quote popup");

			roomNumber4 = reservationPage.getRoomNumberDetail(driver);
			app_logs.info("Room number for quote reservation : " + roomNumber4);
			testSteps.add("Room number for quote reservation : " + roomNumber4);

			creditCardNumber = reservationPage.getCardNoLast4Digits_ResDetail(driver);

			reservationPage.closeReservationTab(driver);
			testSteps.add("Successfully close opened tab");

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create new quote reservation", scriptName, "CreateReservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create new quote reservation", scriptName, "CreateReservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// CREATE RESERVATION HAVING RESERVED STATUTS

		try {

			testSteps.add("==========CREATE NEW RESERVED RESERVATION==========");
			app_logs.info("==========CREATE NEW RESERVED RESERVATION==========");

			getTestSteps.clear();
			getTestSteps = reservationPage.click_NewReservation(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.checkInDate(driver, 0);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.checkOutDate(driver, +1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterAdult(driver, adult);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterChildren(driver, children);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnFindRooms(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectRoom(driver, getTestSteps, roomClassName, isAssign, account);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.verifyGuestProfileCheckoxChecked(driver, Boolean.parseBoolean(isChecked));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterGuestName(driver, getTestSteps, salutation, firstName3, lastName3);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservationPage.enterPaymentDetails(driver, getTestSteps, paymentType, cardNumber, cardName, cardExpDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectReferral(driver, referral);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickBookNow(driver, getTestSteps);
			testSteps.add("Successfully click Book Now");

			reservationNumber1 = reservationPage.getReservationConfirmationNumber(driver, getTestSteps);
			testSteps.add("Reservation confirmation number: " + reservationNumber1);
			app_logs.info("Reservation confirmation number: " + reservationNumber1);

			reservationPage.clickCloseReservationSavePopup(driver, getTestSteps);
			testSteps.add("Sucessfully close save reservation Popup");

			roomNumber1 = reservationPage.getRoomNumberDetail(driver);
			app_logs.info("Room Number is : " + roomNumber1);
			testSteps.add("Room number for quote reservation : " + roomNumber4);

			getRoomChargesInTripSummary = reservationPage.getRoomChargesInTripSummary(driver);
			app_logs.info("after room charges");
			getTaxandServicesInTripSummary = reservationPage.getTaxandServicesInTripSummary(driver);
			app_logs.info("after tax services");

			reservationPage.closeReservationTab(driver);
			testSteps.add("Close opened tab");

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create new reservation", scriptName, "CreateReservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create new reservation", scriptName, "CreateReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Search for Above Reservations

		try {

			testSteps.add("============SEARCH FOR CREATED RESERVATIONS============");
			allReservation = reservationNumber1 + "," + reservationNumber4;

			reservationSearch.multipleSearchReservationNumber(driver, allReservation);
			testSteps.add("Successfully Search with Multiple Reservation Numbers");

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to Search all created reservation", scriptName, "CreateReservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to Search all created reservation", scriptName, "CreateReservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		String roomClass1 = roomClassAbbreviation + " : " + roomNumber1;
		String roomClass4 = roomClassAbbreviation + " : " + roomNumber4;

		// Bulk Option Check in

		try {

			testSteps.add("============BULK OPTION CHECK IN============");
			app_logs.info("============BULK OPTION CHECK IN============");

			getTestSteps.clear();
			getTestSteps = reservationPage.selectAllSearchedReservationCheckBox(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationSearch.clickBulkOptionCheckInAndVerifyPopUp(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnBulkResservationCanUpdated(driver, "1");
			testSteps.addAll(getTestSteps);

			testSteps.add("============VERIFY RESERVED RESERVATION IN BULK OPTION CHECK IN============");
			app_logs.info("============VERIFY RESERVED RESERVATION IN BULK OPTION CHECK IN============");

			String getGuestName = reservationPage.getGuestName(driver, guestName1, 1);
			testSteps.add("Expected guest name: " + guestName1);
			testSteps.add("Found : " + getGuestName);
			assertEquals(getGuestName, guestName1, "Failed to verify Guest Name");
			testSteps.add("Verified guest name");
			app_logs.info("Verified guest name");

			String getReservationNum1 = reservationPage.getReservationDetails(driver, guestName1, 1, 1);
			testSteps.add("Expected reservation number:" + reservationNumber1);
			testSteps.add("Found : " + getReservationNum1);
			assertEquals(getReservationNum1, reservationNumber1, "Failed to verify Reservation Number");
			testSteps.add("Verified reservation number");
			app_logs.info("Verified reservation number");

			String getRoomNum1 = reservationPage.getReservationDetails(driver, guestName1, 1, 2);
			testSteps.add("Expected room number: " + roomClass1);
			testSteps.add("Found : " + getRoomNum1);
			assertEquals(getRoomNum1, roomClass1, "Failed to verify Room Number");
			testSteps.add("Verified room number");

			String getPayment1 = reservationPage.getReservationDetails(driver, guestName1, 1, 3);
			testSteps.add("Expected payment type: " + paymentType);
			testSteps.add("Found : " + getPayment1);
			assertEquals(getPayment1, paymentType, "Failed to verify PaymentType");
			testSteps.add(" Verified payment type ");

			String getCreditCard1 = reservationPage.getReservationDetails(driver, guestName1, 1, 5);
			// String getCc1 = getCreditCard1.substring(getCreditCard1.length()
			// - 4);
			creditCardNumber = "XXXX" + creditCardNumber;
			testSteps.add("Expected credit card number: " + creditCardNumber);
			testSteps.add("Found : " + getCreditCard1);
			assertEquals(getCreditCard1, creditCardNumber, "Failed to verify Credit Card Number");
			testSteps.add(" Verified card number");

			String getExpiryDate1 = reservationPage.getReservationDetails(driver, guestName1, 1, 6);
			testSteps.add("Expected card expiry date: " + cardExpDate);
			testSteps.add("Found : " + getExpiryDate1);
			assertEquals(getExpiryDate1, cardExpDate, "Failed to verify Card Expiry Date");
			testSteps.add("Verified card expiry date ");

			String amount = reservationPage.getReservationDetails(driver, guestName1, 1, 8);
			testSteps.add("Expected amount: " + enterAmount);
			testSteps.add("Found : " + amount);
			assertEquals(amount, enterAmount, "Failed to verify amount");
			testSteps.add("Verified amount");

			testSteps.add("============VERIFY QUOTE RESERVATION IN BULK CHECK IN============");
			app_logs.info("============VERIFY QUOTE RESERVATION IN BULK CHECK IN============");

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnBulkReservationCanNotUpdated(driver, "1");
			testSteps.addAll(getTestSteps);

			String getGuestName4 = reservationPage.getGuestName(driver, guestName4, 1);
			testSteps.add("Expected guest name: " + guestName4);
			testSteps.add("Found : " + getGuestName4);
			assertEquals(getGuestName4, guestName4, "Failed to verify Guest Name");
			testSteps.add("Verified guest name");

			String getReservationNum4 = reservationPage.getReservationDetails(driver, guestName4, 1, 1);
			testSteps.add("Expected reservation number: " + reservationNumber4);
			testSteps.add("Found : " + getReservationNum4);
			assertEquals(getReservationNum4, reservationNumber4, "Failed to verify Reservation Number");
			testSteps.add("Verified reservation number");

			String getRoomNum4 = reservationPage.getReservationDetails(driver, guestName4, 1, 2);
			testSteps.add("Expected room number: " + roomClass4);
			testSteps.add("Found : " + getRoomNum4);
			assertEquals(getRoomNum4, roomClass4, "Failed to verify Room Number");
			testSteps.add("Verified room number");

			String getMessage1 = reservationPage.getReservationDetails(driver, guestName4, 1, 6);
			testSteps.add("Expected comments: " + message);
			testSteps.add("Found : " + getMessage1);
			assertEquals(getMessage1, message, "Failed to verify comments");
			testSteps.add("Verified comments");

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnProcessButtonInBulkCheckInPopUp(driver);
			testSteps.addAll(getTestSteps);

			testSteps.add("============VERIFY RESERVED RESERVATION IN BULK OPTION CHECK IN AFTER PROCESS============");
			app_logs.info("============VERIFY RESERVED RESERVATION IN BULK OPTION CHECK IN AFTER PROCESS============");

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnBulkReservationCanUpdatedAfterProcess(driver);
			testSteps.addAll(getTestSteps);

			getGuestName = reservationPage.getGuestName(driver, guestName1, 2);
			testSteps.add("Expected guest name: " + guestName1);
			testSteps.add("Found : " + getGuestName);
			assertEquals(getGuestName, guestName1, "Failed to verify Guest Name");
			testSteps.add("Verified guest name");

			getReservationNum1 = reservationPage.getReservationDetails(driver, guestName1, 2, 1);
			testSteps.add("Expected reservation number:" + reservationNumber1);
			testSteps.add("Found : " + getReservationNum1);
			assertEquals(getReservationNum1, reservationNumber1, "Failed to verify Reservation Number");
			testSteps.add("Verified reservation number");

			getRoomNum1 = reservationPage.getReservationDetails(driver, guestName1, 2, 2);
			testSteps.add("Expected room number: " + roomClass1);
			testSteps.add("Found : " + getRoomNum1);
			assertEquals(getRoomNum1, roomClass1, "Failed to verify Room Number");
			testSteps.add("Verified room number");

			getPayment1 = reservationPage.getReservationDetails(driver, guestName1, 2, 3);
			testSteps.add("Expected payment type: " + paymentType);
			testSteps.add("Found : " + getPayment1);
			assertEquals(getPayment1, paymentType, "Failed to verify PaymentType");
			testSteps.add(" Verified payment type ");

			getCreditCard1 = reservationPage.getReservationDetails(driver, guestName1, 2, 5);
			// getCc1 = getCreditCard1.substring(getCreditCard1.length() - 4);
			testSteps.add("Expected credit card number: " + creditCardNumber);
			testSteps.add("Found : " + getCreditCard1);
			assertEquals(getCreditCard1, creditCardNumber, "Failed to verify Credit Card Number");
			testSteps.add(" Verified card number");

			getExpiryDate1 = reservationPage.getReservationDetails(driver, guestName1, 2, 6);
			testSteps.add("Expected card expiry date: " + cardExpDate);
			testSteps.add("Found : " + getExpiryDate1);
			assertEquals(getExpiryDate1, cardExpDate, "Failed to verify Card Expiry Date");
			testSteps.add("Verified card expiry date ");

			amount = reservationPage.getReservationDetails(driver, guestName1, 2, 8);
			testSteps.add("Expected comments: " + enterAmount);
			testSteps.add("Found : " + amount);
			assertEquals(amount, enterAmount, "Failed to verify comments");
			testSteps.add("Verified comments");

			testSteps.add("============VERIFY QUOTE RESERVATION IN BULK CHECK IN AFTER PROCESS============");
			app_logs.info("============VERIFY QUOTE RESERVATION IN BULK CHECK IN AFTER PROCESS============");

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnBulkCheckInReservationCanNotUpdated(driver, "1");
			testSteps.addAll(getTestSteps);

			getGuestName4 = reservationPage.getGuestName(driver, guestName4, 2);
			testSteps.add("Expected Guest Name is " + guestName4);
			testSteps.add("Found : " + getGuestName4);
			assertEquals(getGuestName4, guestName4, "Failed to verify Guest Name");
			testSteps.add("Verified guest name");
			app_logs.info("Verified guest name");

			getReservationNum4 = reservationPage.getReservationDetails(driver, guestName4, 2, 1);
			testSteps.add("Expected Reservation number is " + reservationNumber4);
			testSteps.add("Found : " + getReservationNum4);
			assertEquals(getReservationNum4, reservationNumber4, "Failed to verify Reservation Number");
			testSteps.add("Verified reservation number");
			app_logs.info("Verified reservation number");

			getRoomNum4 = reservationPage.getReservationDetails(driver, guestName4, 2, 2);
			testSteps.add("Expected Room Number is " + roomClass4);
			testSteps.add("Found : " + getRoomNum4);
			assertEquals(getRoomNum4, roomClass4, "Failed to verify Room Number");
			testSteps.add("Verified room number");

			getMessage1 = reservationPage.getReservationDetails(driver, guestName4, 2, 6);
			testSteps.add("Expected Message is " + message);
			testSteps.add("Found : " + getMessage1);
			assertEquals(getMessage1, message, "Failed to verify Message");
			testSteps.add("Verified comments");

			getTestSteps.clear();
			getTestSteps = reservationPage.closeBulkActionPopUp(driver);
			testSteps.addAll(getTestSteps);

			testSteps.add("==========VERIFICATION OF RESERVATIONS STATUS AFTER BULK CHECKIN==========");
			getTestSteps.clear();
			getTestSteps = reservationPage.verifyReservationStatus(driver, reservationNumber1, "In-House");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.verifyReservationStatus(driver, reservationNumber4, "Quote");
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to perform bulk checkin action", scriptName, "BulkCheckIn", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to perform bulk checkin action", scriptName, "BulkCheckIn", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Bulk Option CheckOut
		try {

			testSteps.add("===========BULK CHECK OUT===========");
			app_logs.info("===========BULK CHECK OUT===========");

			driver.navigate().refresh();
			reservationSearch.multipleSearchReservationNumber(driver, allReservation);
			testSteps.add("Searched all Five Restervations at same search");

			getTestSteps.clear();
			getTestSteps = reservationPage.selectAllSearchedReservationCheckBox(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationSearch.selectBulkCheckOut(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnBulkResservationCanUpdated(driver, "1");
			testSteps.addAll(getTestSteps);

			// start verification of bulk checkout here

			testSteps.add("===========VERIFY CHECKIN RESERVATION IN BULK CHECKOUT===========");
			app_logs.info("===========VERIFY CHECKIN RESERVATION IN BULK CHECKOUT===========");

			String getGuestName1 = reservationPage.getGuestName(driver, guestName1, 1);
			testSteps.add("Expected guest name: " + guestName1);
			testSteps.add("Found : " + getGuestName1);
			assertEquals(getGuestName1, guestName1, "Failed to verify Guest Name");
			testSteps.add("Verified guest name");
			app_logs.info("Verified guest name");

			String getReservationNum1 = reservationPage.getReservationDetails(driver, guestName1, 1, 1);
			testSteps.add("Expected reservation number: " + reservationNumber1);
			testSteps.add("Found : " + getReservationNum1);
			assertEquals(getReservationNum1, reservationNumber1, "Failed to verify Reservation Number");
			testSteps.add("Verified reservation number");
			app_logs.info("Verified reservation number");

			String getRoomNum1 = reservationPage.getReservationDetails(driver, guestName1, 1, 2);
			testSteps.add("Expected room number: " + roomClass1);
			testSteps.add("Found : " + getRoomNum1);
			assertEquals(getRoomNum1, roomClass1, "Failed to verify Room Number");
			testSteps.add("Verified room number");
			app_logs.info("Verified reservation number");

			String getPayment1 = reservationPage.getReservationDetails(driver, guestName1, 1, 3);
			testSteps.add("Expected payment type: " + paymentType);
			testSteps.add("Found : " + getPayment1);
			assertEquals(getPayment1, paymentType, "Failed to verify PaymentType");
			testSteps.add("Verified payment type ");
			app_logs.info("Verified payment type ");

			String getCreditCard1 = reservationPage.getReservationDetails(driver, guestName1, 1, 5);
			// String getCc1 = getCreditCard1.substring(getCreditCard1.length()
			// - 4);
			testSteps.add("Expected credit card number:" + creditCardNumber);
			testSteps.add("Found : " + getCreditCard1);
			assertEquals(getCreditCard1, creditCardNumber, "Failed to verify Credit Card Number");
			testSteps.add("Verified card number");
			app_logs.info("Verified card number");

			String getExpiryDate1 = reservationPage.getReservationDetails(driver, guestName1, 1, 6);
			testSteps.add("Expected card expiry Date is " + cardExpDate);
			testSteps.add("Found : " + getExpiryDate1);
			assertEquals(getExpiryDate1, cardExpDate, "Failed to verify Card Expiry Date");
			testSteps.add("Verified card expiry date");
			app_logs.info("Verified card expiry date");

			String amount = reservationPage.getReservationDetails(driver, guestName1, 1, 8);
			expecetdAmount = folio.AddValue(folio.splitString(getRoomChargesInTripSummary),
					folio.splitString(getTaxandServicesInTripSummary));

			String getAmount = folio.splitStringByDot(expecetdAmount);
			testSteps.add("Expected comments: $ " + getAmount);
			testSteps.add("Found : $ " + amount);
			assertEquals(amount, getAmount, "Failed to verify comments");
			testSteps.add("Verified amount");

			testSteps.add("===========VERIFY QUOTE RESERVATION IN BULK IN CHECK OUT===========");
			app_logs.info("===========VERIFY QUOTE RESERVATION IN BULK IN CHECK OUT===========");

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnBulkReservationCanNotUpdated(driver, "1");
			testSteps.addAll(getTestSteps);

			String getGuestName4 = reservationPage.getGuestName(driver, guestName4, 1);
			testSteps.add("Expected Guest Name is " + guestName4);
			testSteps.add("Found : " + getGuestName4);
			assertEquals(getGuestName4, guestName4, "Failed to verify Guest Name");
			testSteps.add("Verified guest name");

			String getReservationNum4 = reservationPage.getReservationDetails(driver, guestName4, 1, 1);
			testSteps.add("Expected Reservation number is " + reservationNumber4);
			testSteps.add("Found : " + getReservationNum4);
			assertEquals(getReservationNum4, reservationNumber4, "Failed to verify Reservation Number");
			testSteps.add("Verified reservation number");

			String getRoomNum4 = reservationPage.getReservationDetails(driver, guestName4, 1, 2);
			testSteps.add("Expected Room Number is " + roomClass4);
			testSteps.add("Found : " + getRoomNum4);
			assertEquals(getRoomNum4, roomClass4, "Failed to verify Room Number");
			testSteps.add("Verified room number");

			String getMessage1 = reservationPage.getReservationDetails(driver, guestName4, 1, 6);
			testSteps.add("Expected Message is " + message);
			testSteps.add("Found : " + getMessage1);
			assertEquals(getMessage1, message, "Failed to verify comments");
			testSteps.add("Verified comments");

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnProcessInBulkCheckOutPopUp(driver);
			testSteps.addAll(getTestSteps);

			testSteps.add("===========VERIFY CHECKIN RESERVATION IN BULK CHECKOUT POPUP AFTER PROCESS===========");
			app_logs.info("===========VERIFY HECKIN RESERVATION IN BULK CHECKOUT POPUP AFTER PROCESS===========");

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnBulkCheckoutReservationCanUpdatedAfterProcess(driver);
			testSteps.addAll(getTestSteps);

			getGuestName1 = reservationPage.getGuestName(driver, guestName1, 2);
			testSteps.add("Expected guest name: " + guestName1);
			testSteps.add("Found : " + getGuestName1);
			assertEquals(getGuestName1, guestName1, "Failed to verify Guest Name");
			testSteps.add("Verified guest name");

			getReservationNum1 = reservationPage.getReservationDetails(driver, guestName1, 2, 1);

			testSteps.add("Expected reservation number: " + reservationNumber1);
			testSteps.add("Found : " + getReservationNum1);
			assertEquals(getReservationNum1, reservationNumber1, "Failed to verify Reservation Number");
			testSteps.add("Verified reservation number");

			getRoomNum1 = reservationPage.getReservationDetails(driver, guestName1, 2, 2);
			testSteps.add("Expected room number: " + roomClass1);
			testSteps.add("Found : " + getRoomNum1);
			assertEquals(getRoomNum1, roomClass1, "Failed to verify Room Number");
			testSteps.add("Verified room number");

			getPayment1 = reservationPage.getReservationDetails(driver, guestName1, 2, 3);
			testSteps.add("Expected payment type: " + paymentType);
			testSteps.add("Found : " + getPayment1);
			assertEquals(getPayment1, paymentType, "Failed to verify PaymentType");
			testSteps.add(" Verified payment type ");

			getCreditCard1 = reservationPage.getReservationDetails(driver, guestName1, 2, 5);
			// getCc1 = getCreditCard1.substring(getCreditCard1.length() - 4);
			testSteps.add("Expected credit card number: " + creditCardNumber);
			testSteps.add("Found : " + getCreditCard1);
			assertEquals(getCreditCard1, creditCardNumber, "Failed to verify Credit Card Number");
			testSteps.add("Verified card number");

			getExpiryDate1 = reservationPage.getReservationDetails(driver, guestName1, 2, 6);
			testSteps.add("Expected card dxpiry date: " + cardExpDate);
			testSteps.add("Found : " + getExpiryDate1);
			assertEquals(getExpiryDate1, cardExpDate, "Failed to verify Card Expiry Date");
			testSteps.add("Verified card expiry date ");

			amount = reservationPage.getReservationDetails(driver, guestName1, 2, 8);
			expecetdAmount = folio.AddValue(folio.splitString(getRoomChargesInTripSummary),
					folio.splitString(getTaxandServicesInTripSummary));

			testSteps.add("Expected comments: $ " + getAmount);
			testSteps.add("Found : $ " + amount);
			assertEquals(amount, getAmount, "Failed to verify comments");
			testSteps.add("Verified amount");

			testSteps.add("===========VERIFY QUOTE RESERVATION IN BULK CHECK OUT AFTER PROCESS===========");
			app_logs.info("===========VERIFY QUOTE RESERVATION IN BULK CHECK OUT AFTER PROCESS===========");

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnBulkCheckInReservationCanNotUpdated(driver, "1");
			testSteps.addAll(getTestSteps);

			getGuestName4 = reservationPage.getGuestName(driver, guestName4, 2);
			testSteps.add("Expected guest name: " + guestName4);
			testSteps.add("Found : " + getGuestName4);
			assertEquals(getGuestName4, guestName4, "Failed to verify Guest Name");
			testSteps.add("Verified guest name");

			getReservationNum4 = reservationPage.getReservationDetails(driver, guestName4, 2, 1);
			testSteps.add("Expected reservation number: " + reservationNumber4);
			testSteps.add("Found : " + getReservationNum4);
			assertEquals(getReservationNum4, reservationNumber4, "Failed to verify Reservation Number");
			testSteps.add("Verified reservation number");

			getRoomNum4 = reservationPage.getReservationDetails(driver, guestName4, 2, 2);
			testSteps.add("Expected room number: " + roomClass4);
			testSteps.add("Found : " + getRoomNum4);
			assertEquals(getRoomNum4, roomClass4, "Failed to verify Room Number");
			testSteps.add("Verified room number");

			getMessage1 = reservationPage.getReservationDetails(driver, guestName4, 2, 6);
			testSteps.add("Expected comments: " + message);
			testSteps.add("Found : " + getMessage1);
			assertEquals(getMessage1, message, "Failed to verify comments");
			testSteps.add("Verified comments");

			getTestSteps.clear();
			getTestSteps = reservationPage.closeBulkActionPopUp(driver);
			testSteps.addAll(getTestSteps);

			testSteps.add("==========VERIFICATION OF RESERVATIONS STATUS AFTER BULK CHECKOUT==========");
			getTestSteps.clear();
			getTestSteps = reservationPage.verifyReservationStatus(driver, reservationNumber1, "Departed");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.verifyReservationStatus(driver, reservationNumber4, "Quote");
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed veirfy resevrtaions during bulk checkout process", scriptName,
						"BulkCheckout", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed veirfy resevrtaions during bulk checkout process", scriptName,
						"BulkCheckout", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verify All The details After Bulk Checkout and Payment Verification

		try {

			testSteps.add("============VERIFICATION OF TRIIP SUMMARY AFTER BULK CHECK IN AND CHECKOUT==============");
			app_logs.info("============VERIFICATION OF TRIIP SUMMARY AFTER BULK CHECK IN AND CHECKOUT==============");

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnGuestNameInSearchReaservation(driver, reservationNumber1);
			testSteps.addAll(getTestSteps);

			
			String getPaid = reservationPage.getPaidInTripSummary(driver);
			testSteps.add("Expected paid: $ " + expecetdAmount);
			testSteps.add("Found: $ " + getPaid);
			assertEquals(getPaid, "$ " + expecetdAmount, "Failed: Paid is mismatching");
			testSteps.add("Verified paid");

			String getBalance = reservationPage.getBalanceInTripSummary(driver);
			String expectedBalance = folio.MinseTwoValue(folio.splitString(getPaid), expecetdAmount);
			testSteps.add("Expected balance: $ " + expectedBalance);
			testSteps.add("Found: " + getBalance);
			assertEquals(getBalance, "$ " + expectedBalance, "Failed: Balance is mismatching!");
			testSteps.add("Verified balance");

			testSteps.add("============VERIFICATION OF GUEST HISTROY AFTER BULK CHECKOUT==============");
			app_logs.info("============VERIFICATION OF GUEST HISTROY AFTER BULK CHECKOUT==============");

			getTestSteps.clear();
			getTestSteps = reservationPage.ClickOnHistory(driver);
			testSteps.addAll(getTestSteps);

			String getHistoryCategory = reservationPage.getHistoryCategory(driver, 0);
			testSteps.add("Expected category: " + ReservationCategory);
			testSteps.add("Found: " + getHistoryCategory);
			assertEquals(getHistoryCategory, ReservationCategory, "Failed: History category is mismatching!");
			testSteps.add("Verified category");

			String getHistoryDate = reservationPage.gettHistoryDate(driver, 0);
			String getDate = ESTTimeZone.DateFormateForLineItem(0);
			testSteps.add("Expected date: " + getDate);
			testSteps.add("Found: " + getHistoryDate);
			assertTrue(getDate.contains(getHistoryDate), "Failed: History date is mismatching!");
			testSteps.add("Verified date");

			String gettHistoryDescription = reservationPage.getHistoryDescription(driver, 0);
			description = "Checked out this reservation";
			testSteps.add("Expected description: " + description);
			testSteps.add("Found: " + gettHistoryDescription);
			assertEquals(gettHistoryDescription, description, "Failed: History description is mismatching!");
			testSteps.add("Verified description");

			testSteps.add("============VERIFICATION OF GUEST HISTROY AFTER PAYMENT==============");
			app_logs.info("============VERIFICATION OF GUEST HISTROY AFTER PAYMENT==============");

			getHistoryCategory = reservationPage.getHistoryCategory(driver, 1);
			testSteps.add("Expected category: " + paymentCategory);
			testSteps.add("Found: " + getHistoryCategory);
			assertEquals(getHistoryCategory, paymentCategory, "Failed: History category is mismatching!");
			testSteps.add("Verified category");

			getHistoryDate = reservationPage.gettHistoryDate(driver, 1);
			testSteps.add("Expected date: " + getDate);
			testSteps.add("Found: " + getHistoryDate);
			assertTrue(getDate.contains(getHistoryDate), "Failed: History date is mismatching!");
			testSteps.add("Verified date");

			// Captured payment for $100.00 using (MC -XXXX5454 02/23)
			String paymentDescription = "Captured payment for $" + expecetdAmount + " using (" + paymentType + " -"
					+ creditCardNumber + " " + cardExpDate + ")";
			String getHistoryDescription = reservationPage.getHistoryDescription(driver, 1);
			description = "Checked out this reservation";
			testSteps.add("Expected description: " + paymentDescription);
			testSteps.add("Found: " + getHistoryDescription);
			assertEquals(getHistoryDescription, paymentDescription, "Failed: History description is mismatching!");
			getTestSteps.add("Verified description");

			testSteps.add("============VERIFICATION OF GUEST HISTROY AFTER CHECK IN==============");
			app_logs.info("============VERIFICATION OF GUEST HISTROY AFTER CHECK IN==============");

			getHistoryCategory = reservationPage.getHistoryCategory(driver, 2);
			testSteps.add("Expected category: " + ReservationCategory);
			testSteps.add("Found: " + getHistoryCategory);
			assertEquals(getHistoryCategory, ReservationCategory, "Failed: History category is mismatching!");
			testSteps.add("Verified category");

			getHistoryDate = reservationPage.gettHistoryDate(driver, 2);
			testSteps.add("Expected date: " + getDate);
			testSteps.add("Found: " + getHistoryDate);
			assertTrue(getDate.contains(getHistoryDate), "Failed: History date is mismatching!");
			testSteps.add("Verified date");

			getHistoryDescription = reservationPage.getHistoryDescription(driver, 2);
			description = "Checked in this reservation";
			testSteps.add("Expected description: " + description);
			testSteps.add("Found: " + getHistoryDescription);
			assertEquals(getHistoryDescription, description, "Failed: History description is mismatching!");
			testSteps.add("Verified description");
			
			reservationPage.closeReservationTab(driver);
			testSteps.add("Successfully close opened tab");

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify trip summary and guest history", scriptName,
						"TripSummary&GuestHistory", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify trip summary and guest history", scriptName,
						"TripSummary&GuestHistory", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			navigation.Inventory(driver);
			testSteps.add("Navigate to Inventory");
			app_logs.info("Navigate to Inventory");
			
			// create new method 
			getTestSteps.clear();
			getTestSteps = navigation.secondaryRatesMenuItem(driver);
			testSteps.addAll(getTestSteps);

			// start new method for delete rate
			getTestSteps.clear();
			getTestSteps = rate.deleteRates(driver, rateName);
			testSteps.addAll(getTestSteps);
			testSteps.add("New Rate has been Deleted successfully");
			app_logs.info("New Rate has been Deleted successfully");

			rate.verifyDeleteRate(driver, rateName);
			testSteps.add("Verify the Deleted Rate : " + rateName);
			app_logs.info("Verify the Deleted Rate " + rateName);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to delete a rate ", scriptName, "DeleteRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to delete a rate ", scriptName, "DeleteRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Delete Room Class try
		try {
			testSteps.add("==========DELETE ROOM CLASSE==========");

			navigation.Setup(driver);
			testSteps.add("Click on setup");

			navigation.RoomClass(driver);
			testSteps.add("Click on room classes");
			roomClass.searchClass(driver, tempraryRoomClass);
			app_logs.info("Search");
			getTestSteps.clear();
			getTestSteps = 	roomClass.deleteRoomClass(driver, tempraryRoomClass);
			testSteps.addAll(testSteps);
			testSteps.add("Delete room class successfully");
			app_logs.info("Delete room class successfully");

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);

		}

		catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to delete room class", scriptName, "RoomClasses", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to delete room class", scriptName, "RoomClasses", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {

		// return test data from the sheet name provided
		return Utility.getData("BulkCheckInCheckOutReservations", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		 driver.quit();

	}
}