package com.innroad.inncenter.tests;

import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.Account;
import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Reservation;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;

public class VerifyListReportUsingBasicAndAdvancedSearch extends TestCore {

	// AUTOMATION-1496
	private WebDriver driver = null;
	ArrayList<String> TestCategory = new ArrayList<>();
	ArrayList<String> TestDescription = new ArrayList<>();
	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();
	ArrayList<String> TestName = new ArrayList<>();

	// Before Test
	@BeforeTest
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable("VerifyListReportUsingBasicAndAdvancedSearch", excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reports")
	public void verifyListReportUsingBasicAndAdvancedSearch(String salutation, String guestFirstName,
			String guestLastName, String paymentType, String cardNumber, String nameOnCard, String cardExpDate,
			String referral, String isGuesProfile, String roomClass, String isAssign, String account, String children,
			String adults, String checkOutDate, String checkInDate, String roomClassAbb, String source, String ratePlan,
			String reservedBy, String address, String firstName, String lastName, String nights, String status,
			String other, String accountFirstName, String accountLastName, String phoneNumber, String alternativeNumber,
			String firstAddress, String secondAddress, String thirdAddress, String email, String city, String state,
			String postalcode, String accountType, String accountName, String country) {

		String testName = "VerifyListReportUsingBasicAndAdvancedSearch";
		String test_description = "Verify reports of search with guest in basic search name<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682521' " + "target='_blank'>"
				+ "Click here to open TestRail: C682521</a><br/>"
				+ "Verify the reports of Search with guest name in Advanced search<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682522' " + "target='_blank'>"
				+ "Click here to open TestRail: C682522</a><br/>";
		String test_catagory = "Reports";
		TestName.add(testName);
		TestDescription.add(test_description);
		TestCategory.add(test_catagory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		CPReservationPage reservation = new CPReservationPage();
		Account newAccount = new Account();
		String firstRandomNumber = Utility.generateRandomNumber();
		String secondRandomNumber = Utility.generateRandomNumber();
		String thirdRandomNumber = Utility.generateRandomNumber();
		String fourthRandomNumber = Utility.generateRandomNumber();
		ReservationSearch searchReservation = new ReservationSearch();
		guestFirstName = guestFirstName + firstRandomNumber;
		guestLastName = guestLastName + firstRandomNumber;

		lastName = lastName + secondRandomNumber;
		firstName = firstName + secondRandomNumber;

		accountFirstName = accountFirstName + thirdRandomNumber;
		accountLastName = accountLastName + thirdRandomNumber;
		accountName = accountName + secondRandomNumber;

		String firstGuestName = guestFirstName + " " + lastName; // Guest Name of first reservation
		String secondGuestName = guestFirstName + " " + guestLastName; // Guest Name of Second reservation
		String thirdGuestName = firstName + " " + guestLastName; // Guest Name of Third reservation
		String fourthGuestName = accountFirstName + " " + accountLastName;
		Navigation navigation = new Navigation();

		String firstReservationNumber = ""; // Reservation number of first reservation
		String secondReservationNumber = ""; // Reservation number of Second reservation
		String thirdReservationNumber = ""; // Reservation number of Third reservation
		String fourthReservationNumber = "";
		String firstRoomNumber = ""; // Room number of first reservation
		String secondRoomNumber = ""; // Room number of Second reservation
		String thirdRoomNumber = ""; // Room number of Third reservation
		String fourthRoomNumber = "";
		String fileName = "";
		String reservationStatus = "";
		String roomCharges = "";
		String accountNumber = "";
		String[] lines = null;
		String checkIn = "";
		String checkOut = "";
		String totalRevenue = "";
		String getAccountName = "";

		String tempCheckIn = null;
		String tempCheckOut = null;

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
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {

				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("=============CREATE NEW RESERVATION (1)================");
			app_logs.info("=============CREATE NEW RESERVATION (1)================");

			getTestSteps.clear();
			reservation.click_NewReservation(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			checkInDate = Utility.getCurrentDate("MM/dd/yyyy");
			checkIn = ESTTimeZone.reformatDate(checkInDate, "MM/dd/yyyy", "MMM dd, yyyy");

			System.out.println("checkin date" + checkIn);

			checkOutDate = Utility.GetNextDate(1, "MM/dd/yyyy");
			checkOut = ESTTimeZone.reformatDate(checkOutDate, "MM/dd/yyyy", "MMM dd, yyyy");
			System.out.println("checkin date" + checkOut);

			getTestSteps.clear();
			getTestSteps = reservation.checkInDate(driver, 0);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkOutDate(driver, +1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.enter_Adults(driver, getTestSteps, adults);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.enter_Children(driver, getTestSteps, children);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.select_Rateplan(driver, getTestSteps, ratePlan, "");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickOnFindRooms(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			app_logs.info("isAssign: " + isAssign);
			isAssign = "Yes";
			reservation.selectRoom(driver, getTestSteps, roomClass, isAssign, account);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.uncheck_CreateGuestProfile(driver, getTestSteps, isGuesProfile);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.enter_GuestName(driver, getTestSteps, salutation, guestFirstName, lastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.enter_PaymentDetails(driver, getTestSteps, paymentType, cardNumber, nameOnCard, cardExpDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.selectReferral(driver, referral);
			testSteps.add("Successfully Selected Referral as :" + referral);

			getTestSteps.clear();
			reservation.clickBookNow(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			firstReservationNumber = reservation.getReservationConfirmationNumber(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickCloseReservationSavePopup(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			firstRoomNumber = roomClassAbb + " : " + reservation.getRoomNo_ResDetail(driver);
			testSteps.add("Room number is:" + firstRoomNumber);
			app_logs.info("Room number is:" + firstRoomNumber);

			getTestSteps.clear();
			roomCharges = reservation.getRoomCharge_TripSummary(driver);
			testSteps.add("Room charges is: " + roomCharges);

			Float total = Float.parseFloat(roomCharges) + Float.parseFloat(roomCharges);
			totalRevenue = total.toString();

			reservation.closeReservationTab(driver);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create New reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create New reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("=============CREATE A NEW RESERVATION (2)================");
			app_logs.info("=============CREATE A NEW RESERVATION (2)================");

			getTestSteps.clear();
			reservation.click_NewReservation(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkInDate(driver, 0);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkOutDate(driver, +1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.enter_Adults(driver, getTestSteps, adults);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.enter_Children(driver, getTestSteps, children);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.select_Rateplan(driver, getTestSteps, ratePlan, "");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickOnFindRooms(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.selectRoom(driver, getTestSteps, roomClass, isAssign, account);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.uncheck_CreateGuestProfile(driver, getTestSteps, isGuesProfile);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.enter_GuestName(driver, getTestSteps, salutation, guestFirstName, guestLastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.enter_PaymentDetails(driver, getTestSteps, paymentType, cardNumber, nameOnCard, cardExpDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.selectReferral(driver, referral);
			testSteps.add("Successfully Selected Referral as :" + referral);

			getTestSteps.clear();
			reservation.clickBookNow(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			secondReservationNumber = reservation.getReservationConfirmationNumber(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickCloseReservationSavePopup(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			secondRoomNumber = roomClassAbb + " : " + reservation.getRoomNo_ResDetail(driver);
			testSteps.add("Room number is:" + firstRoomNumber);
			app_logs.info("Room number is:" + firstRoomNumber);

			// navigation.Reservation(driver);

			reservation.closeReservationTab(driver);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create New reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create New reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("=============CREATE NEW RESERVATION (3)================");
			app_logs.info("=============CREATE NEW RESERVATION (3)================");

			getTestSteps.clear();
			reservation.click_NewReservation(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkInDate(driver, 0);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkOutDate(driver, +1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.enter_Adults(driver, getTestSteps, adults);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.enter_Children(driver, getTestSteps, children);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.select_Rateplan(driver, getTestSteps, ratePlan, "");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickOnFindRooms(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.selectRoom(driver, getTestSteps, roomClass, isAssign, account);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.uncheck_CreateGuestProfile(driver, getTestSteps, isGuesProfile);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.enter_GuestName(driver, getTestSteps, salutation, firstName, guestLastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.enter_PaymentDetails(driver, getTestSteps, paymentType, cardNumber, nameOnCard, cardExpDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.selectReferral(driver, referral);
			testSteps.add("Successfully Selected Referral as :" + referral);

			getTestSteps.clear();
			reservation.clickBookNow(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			thirdReservationNumber = reservation.getReservationConfirmationNumber(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickCloseReservationSavePopup(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			thirdRoomNumber = roomClassAbb + " : " + reservation.getRoomNo_ResDetail(driver);
			testSteps.add("Room number is:" + firstRoomNumber);
			app_logs.info("Room number is:" + firstRoomNumber);

			// navigation.Reservation(driver);

			reservation.closeReservationTab(driver);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create New reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create New reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Search with first name
		try {

			testSteps.add("====================SEARCH RESERVATION WITH  FIRST NAME==============");
			app_logs.info("====================SEARCH RESERVATION WITH  FIRST NAME==============");

			getTestSteps.clear();
			getTestSteps = searchReservation.basicSearchWithGuestName(driver, guestFirstName);
			testSteps.addAll(getTestSteps);

			String getGuestName = searchReservation.getGuestNameAfterSearch(driver, 1);
			testSteps.add("Expected guest name: " + guestFirstName);
			testSteps.add("Found: " + getGuestName);
			assertTrue(getGuestName.contains(guestFirstName));
			testSteps.add("Verified guest name");

			getGuestName = searchReservation.getGuestNameAfterSearch(driver, 2);
			testSteps.add("Expected guest name: " + guestFirstName);
			testSteps.add("Found: " + getGuestName);
			assertTrue(getGuestName.contains(guestFirstName));
			testSteps.add("Verified guest name");

			String getReservationNumber = searchReservation.getReservationNumberAfterSearch(driver, 1);
			testSteps.add("Expected reservation number: " + firstReservationNumber);
			testSteps.add("Found : " + getReservationNumber);
			assertEquals(getReservationNumber, firstReservationNumber);
			testSteps.add("Verified reservation number");

			getReservationNumber = searchReservation.getReservationNumberAfterSearch(driver, 2);
			testSteps.add("Expected reservation number: " + secondReservationNumber);
			testSteps.add("Found : " + getReservationNumber);
			assertEquals(getReservationNumber, secondReservationNumber);
			testSteps.add("Verified reservation number");

			String getAdults = searchReservation.getAdultsAfterSearch(driver, 1);
			testSteps.add("Expected adults: " + adults);
			testSteps.add("Found : " + getAdults);
			assertEquals(getAdults, adults);
			testSteps.add("Verified adults");

			getAdults = searchReservation.getAdultsAfterSearch(driver, 2);
			testSteps.add("Expected adults: " + adults);
			testSteps.add("Found : " + getAdults);
			assertEquals(getAdults, adults);
			testSteps.add("Verified adults");

			String getChildren = searchReservation.getChildrenAfterSearch(driver, 1);
			testSteps.add("Expected children: " + children);
			testSteps.add("Found : " + getChildren);
			assertEquals(getChildren, children);
			testSteps.add("Verified children");

			getChildren = searchReservation.getChildrenAfterSearch(driver, 2);
			testSteps.add("Expected children: " + children);
			testSteps.add("Found : " + getChildren);
			assertEquals(getChildren, children);
			testSteps.add("Verified children");

			String getreservationStatus = searchReservation.getReservationStatusAfterSearch(driver, 1);
			testSteps.add("Expected status: " + status);
			testSteps.add("Found : " + getreservationStatus);
			assertEquals(getreservationStatus, status);
			testSteps.add("Verified status");

			getreservationStatus = searchReservation.getReservationStatusAfterSearch(driver, 2);
			testSteps.add("Expected status: " + status);
			testSteps.add("Found : " + getreservationStatus);
			assertEquals(getreservationStatus, status);
			testSteps.add("Verified status");

			String getRoom = searchReservation.getRoomAfterSearch(driver, 1);
			testSteps.add("Expected room: " + firstRoomNumber);
			testSteps.add("Found : " + getRoom);
			assertEquals(getRoom, firstRoomNumber);
			testSteps.add("Verified room");

			getRoom = searchReservation.getRoomAfterSearch(driver, 2);
			testSteps.add("Expected room: " + secondRoomNumber);
			testSteps.add("Found : " + getRoom);
			assertEquals(getRoom, secondRoomNumber);
			testSteps.add("Verified room");

			String getArrivalDate = searchReservation.getArrivalDateAfterSearch(driver, 1);
			testSteps.add("Expected arrival date: " + checkIn);
			testSteps.add("Found : " + getArrivalDate);
			assertEquals(getArrivalDate, checkIn);
			testSteps.add("Verified arrival date");

			getArrivalDate = searchReservation.getArrivalDateAfterSearch(driver, 2);
			testSteps.add("Expected arrival date: " + checkIn);
			testSteps.add("Found : " + getArrivalDate);
			assertEquals(getArrivalDate, checkIn);
			testSteps.add("Verified arrival date");

			String getDepartDate = searchReservation.getDepartDateAfterSearch(driver, 1);
			testSteps.add("Expected depart date: " + checkOut);
			testSteps.add("Found : " + getDepartDate);
			assertEquals(getDepartDate, checkOut);
			testSteps.add("Verified depart date");

			getDepartDate = searchReservation.getDepartDateAfterSearch(driver, 2);
			testSteps.add("Expected depart date: " + checkOut);
			testSteps.add("Found : " + getDepartDate);
			assertEquals(getDepartDate, checkOut);
			testSteps.add("Verified depart date");

			String getNights = searchReservation.getNightsAfterSearch(driver, 1);
			testSteps.add("Expected night: " + nights);
			testSteps.add("Found : " + getNights);
			assertEquals(getNights, nights);
			testSteps.add("Verified night");

			getNights = searchReservation.getNightsAfterSearch(driver, 2);
			testSteps.add("Expected night: " + nights);
			testSteps.add("Found : " + getNights);
			assertEquals(getNights, nights);
			testSteps.add("Verified night");

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed Basic Search with first name", testName, "searchReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed Basic Search with first name", testName, "searchReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("====================OPEN RESERVATION REPORT POPUP==============");
			app_logs.info("====================OPEN RESERVATION REPORT POPUP==============");

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnPrintIcon(driver);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to open reservation report popup", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to open reservation report popup", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("====================DOWNLOAD GUEST RESERVATION REPORT==============");
			app_logs.info("====================DOWNLOAD GUEST RESERVATION REPORT==============");

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnPrintButton(driver);
			testSteps.addAll(getTestSteps);

			searchReservation.downloadPDFReport(driver);

			getTestSteps.clear();
			fileName = Utility.download_status(driver);
			testSteps.add("Download Successful");

			app_logs.info("fileName : " + fileName);
			lines = Utility.checkPDF_Array(fileName);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to download guest reservation report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to download guest reservation report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps
					.add("====================VERIFY GUEST RESERVATION REPORT AFTER SEARCHED FIRST NAME==============");
			app_logs.info(
					"====================VERIFY GUEST RESERVATION REPORT AFTER SEARCHED FIRST NAME==============");

			ArrayList<String> componentNames = new ArrayList<>();
			ArrayList<String> componentValue = new ArrayList<>();

			componentValue.add(guestFirstName);
			componentNames.add("Guest first name");

			componentValue.add(guestFirstName);
			componentNames.add("Guest first name for 2nd reservation");

			componentValue.add(guestLastName);
			componentNames.add("guest last name");

			componentValue.add(lastName);
			componentNames.add("guest last name for 2nd reservation");

			componentValue.add(status);
			componentNames.add("reservation status");

			componentValue.add(status);
			componentNames.add("reservation status for 2nd reservation");

			componentValue.add(firstRoomNumber);
			componentNames.add("room number");

			componentValue.add(secondRoomNumber);
			componentNames.add("2nd room number");

			componentValue.add(totalRevenue);
			componentNames.add("Total Revenue");

			componentValue.add(checkIn);
			componentNames.add("arrival date");

			componentValue.add(checkIn);
			componentNames.add("arrival date");

			componentValue.add(checkOut);
			componentNames.add("depart date");

			componentValue.add(checkOut);
			componentNames.add("depart date");

			componentValue.add(adults);
			componentNames.add("adults");

			componentValue.add(adults);
			componentNames.add("adults");

			componentValue.add(children);
			componentNames.add("children");

			componentValue.add(children);
			componentNames.add("children");

			componentValue.add(roomCharges);
			componentNames.add("Room");

			componentValue.add(roomCharges);
			componentNames.add("Room");

			componentValue.add(firstReservationNumber);
			componentNames.add("reservation number");

			componentValue.add(secondReservationNumber);
			componentNames.add("2nd reservation number");

			componentValue.add(nights);
			componentNames.add("Nights");

			componentValue.add(nights);
			componentNames.add("Nights");

			componentValue.add(other);
			componentNames.add("other");

			componentValue.add(other);
			componentNames.add("other");

			int count = 0;

			for (String line : lines) {
				if (line.contains("OtherRoomOtherRoom#")) {
					break;

				}
				count = count + 1;
			}

			// here to verify guest name exist or not
			boolean isGuestNameShowing = false;
			for (int i = 0; i < count; i++) {
				if (lines[i].equals(firstGuestName) || lines[i].equals(secondGuestName)) {
					isGuestNameShowing = true;
					break;
				}
			}
			assertEquals(isGuestNameShowing, false, "Guest name is showing!");
			testSteps.add("Verified guest name is not reflecting in print report after search first name");
			app_logs.info("lines: " + lines.toString());
			for (int i = 0; i < componentNames.size(); i++) {

				boolean isValueReflecting = false;
				for (String line : lines) {
					if (line.contains(componentValue.get(i))) {
						isValueReflecting = true;
						break;
					} else {

						if (componentNames.get(i).equals("arrival date")) {
							String[] str = checkIn.split(" ");
							tempCheckIn = str[0] + "  " + str[1] + " " + str[2];
							tempCheckIn = ESTTimeZone.reformatDate(tempCheckIn, "MMM  dd, yyyy", "MMM  d, yyyy");
							app_logs.info("######################################################");
							app_logs.info(" tempCheckIn : " + tempCheckIn);
							app_logs.info("######################################################");
							if (line.contains(tempCheckIn)) {
								isValueReflecting = true;
								break;
							}
						}

						if (componentNames.get(i).equals("depart date")) {

							String[] str = checkOut.split(" ");
							tempCheckOut = str[0] + "  " + str[1] + " " + str[2];
							tempCheckOut = ESTTimeZone.reformatDate(tempCheckOut, "MMM  dd, yyyy", "MMM  d, yyyy");
							app_logs.info("######################################################");
							app_logs.info(" tempCheckOut : " + tempCheckOut);
							app_logs.info("######################################################");
							if (line.contains(tempCheckOut)) {
								isValueReflecting = true;
								break;
							}
						}

					}

				}
				assertEquals(isValueReflecting, true, "Expected: " + componentNames.get(i) + " not found ");
				testSteps.add("Verified " + componentNames.get(i));
				app_logs.info("Verified " + componentNames.get(i));
				isValueReflecting = false;

			}

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// search with last name

		try {

			testSteps.add("====================SEARCH RESERVATION WITH  LAST NAME==============");
			app_logs.info("====================SEARCH RESERVATION WITH  LAST NAME==============");

			driver.findElement(By.xpath(OR.BasicGuestName)).clear();

			getTestSteps.clear();
			getTestSteps = searchReservation.basicSearchWithGuestName(driver, guestLastName);
			testSteps.addAll(getTestSteps);

			String getGuestName = searchReservation.getGuestNameAfterSearch(driver, 1);
			testSteps.add("Expected guest name: " + guestLastName);
			testSteps.add("Found: " + getGuestName);
			assertTrue(getGuestName.contains(guestLastName));
			testSteps.add("Verified guest name");

			getGuestName = searchReservation.getGuestNameAfterSearch(driver, 2);
			testSteps.add("Expected guest name: " + guestLastName);
			testSteps.add("Found: " + getGuestName);
			assertTrue(getGuestName.contains(guestLastName));
			testSteps.add("Verified guest name");

			String getReservationNumber = searchReservation.getReservationNumberAfterSearch(driver, 1);
			testSteps.add("Expected reservation number: " + secondReservationNumber);
			testSteps.add("Found : " + getReservationNumber);
			assertEquals(getReservationNumber, secondReservationNumber);
			testSteps.add("Verified reservation number");

			getReservationNumber = searchReservation.getReservationNumberAfterSearch(driver, 2);
			testSteps.add("Expected reservation number: " + thirdReservationNumber);
			testSteps.add("Found : " + getReservationNumber);
			assertEquals(getReservationNumber, thirdReservationNumber);
			testSteps.add("Verified reservation number");

			String getAdults = searchReservation.getAdultsAfterSearch(driver, 1);
			testSteps.add("Expected adults: " + adults);
			testSteps.add("Found : " + getAdults);
			assertEquals(getAdults, adults);
			testSteps.add("Verified adults");

			getAdults = searchReservation.getAdultsAfterSearch(driver, 2);
			testSteps.add("Expected adults: " + adults);
			testSteps.add("Found : " + getAdults);
			assertEquals(getAdults, adults);
			testSteps.add("Verified adults");

			String getChildren = searchReservation.getChildrenAfterSearch(driver, 1);
			testSteps.add("Expected children: " + children);
			testSteps.add("Found : " + getChildren);
			assertEquals(getChildren, children);
			testSteps.add("Verified children");

			getChildren = searchReservation.getChildrenAfterSearch(driver, 2);
			testSteps.add("Expected children: " + children);
			testSteps.add("Found : " + getChildren);
			assertEquals(getChildren, children);
			testSteps.add("Verified children");

			String getreservationStatus = searchReservation.getReservationStatusAfterSearch(driver, 1);
			testSteps.add("Expected status: " + status);
			testSteps.add("Found : " + getreservationStatus);
			assertEquals(getreservationStatus, status);
			testSteps.add("Verified status");

			getreservationStatus = searchReservation.getReservationStatusAfterSearch(driver, 2);
			testSteps.add("Expected status: " + status);
			testSteps.add("Found : " + getreservationStatus);
			assertEquals(getreservationStatus, status);
			testSteps.add("Verified status");

			String getRoom = searchReservation.getRoomAfterSearch(driver, 1);
			testSteps.add("Expected room: " + secondRoomNumber);
			testSteps.add("Found : " + getRoom);
			assertEquals(getRoom, secondRoomNumber);
			testSteps.add("Verified room");

			getRoom = searchReservation.getRoomAfterSearch(driver, 2);
			testSteps.add("Expected room: " + thirdRoomNumber);
			testSteps.add("Found : " + getRoom);
			assertEquals(getRoom, thirdRoomNumber);
			testSteps.add("Verified room");

			String getArrivalDate = searchReservation.getArrivalDateAfterSearch(driver, 1);
			testSteps.add("Expected arrival date: " + checkIn);
			testSteps.add("Found : " + getArrivalDate);
			assertEquals(getArrivalDate, checkIn);
			testSteps.add("Verified arrival date");

			getArrivalDate = searchReservation.getArrivalDateAfterSearch(driver, 2);
			testSteps.add("Expected arrival date: " + checkIn);
			testSteps.add("Found : " + getArrivalDate);
			assertEquals(getArrivalDate, checkIn);
			testSteps.add("Verified arrival date");

			String getDepartDate = searchReservation.getDepartDateAfterSearch(driver, 1);
			testSteps.add("Expected depart date: " + checkOut);
			testSteps.add("Found : " + getDepartDate);
			assertEquals(getDepartDate, checkOut);
			testSteps.add("Verified depart date");

			getDepartDate = searchReservation.getDepartDateAfterSearch(driver, 2);
			testSteps.add("Expected depart date: " + checkOut);
			testSteps.add("Found : " + getDepartDate);
			assertEquals(getDepartDate, checkOut);
			testSteps.add("Verified depart date");

			String getNights = searchReservation.getNightsAfterSearch(driver, 1);
			testSteps.add("Expected night: " + nights);
			testSteps.add("Found : " + getNights);
			assertEquals(getNights, nights);
			testSteps.add("Verified night");

			getNights = searchReservation.getNightsAfterSearch(driver, 2);
			testSteps.add("Expected night: " + nights);
			testSteps.add("Found : " + getNights);
			assertEquals(getNights, nights);
			testSteps.add("Verified night");

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed Basic Search with last name", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed Basic Search with last name", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("====================OPEN RESERVATION REPORT POPUP==============");
			app_logs.info("====================OPEN RESERVATION REPORT POPUP==============");

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnPrintIcon(driver);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to open reservation report popup", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to open reservation report popup", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("====================DOWNLOAD GUEST RESERVATION REPORT==============");
			app_logs.info("====================DOWNLOAD GUEST RESERVATION REPORT==============");

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnPrintButton(driver);
			testSteps.addAll(getTestSteps);

			searchReservation.downloadPDFReport(driver);

			getTestSteps.clear();
			fileName = Utility.download_status(driver);
			testSteps.add("Download Successful");

			app_logs.info("fileName : " + fileName);
			lines = Utility.checkPDF_Array(fileName);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to download guest reservation report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to download guest reservation report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("====================VERIFY GUEST RESERVATION REPORT AFTER SEARCHED LAST NAME==============");
			app_logs.info("====================VERIFY GUEST RESERVATION REPORT AFTER SEARCHED LAST NAME==============");

			ArrayList<String> componentNames = new ArrayList<>();
			ArrayList<String> componentValue = new ArrayList<>();

			componentValue.add(guestFirstName);
			componentNames.add("Guest first name");

			componentValue.add(firstName);
			componentNames.add("Guest first name for 2nd reservation");

			componentValue.add(guestLastName);
			componentNames.add("guest last name");

			componentValue.add(guestLastName);
			componentNames.add("guest last name for 2nd reservation");

			componentValue.add(status);
			componentNames.add("reservation status");

			componentValue.add(status);
			componentNames.add("reservation status for 2nd reservation");

			componentValue.add(secondRoomNumber);
			componentNames.add("room number");

			componentValue.add(thirdRoomNumber);
			componentNames.add("2nd room number");

			componentValue.add(totalRevenue);
			componentNames.add("Total Revenue");

			componentValue.add(checkIn);
			componentNames.add("arrival date");

			componentValue.add(checkIn);
			componentNames.add("arrival date");

			componentValue.add(checkOut);
			componentNames.add("depart date");

			componentValue.add(checkOut);
			componentNames.add("depart date");

			componentValue.add(adults);
			componentNames.add("adults");

			componentValue.add(adults);
			componentNames.add("adults");

			componentValue.add(children);
			componentNames.add("children");

			componentValue.add(children);
			componentNames.add("children");

			componentValue.add(roomCharges);
			componentNames.add("Room");

			componentValue.add(roomCharges);
			componentNames.add("Room");

			componentValue.add(secondReservationNumber);
			componentNames.add("reservation number");

			componentValue.add(thirdReservationNumber);
			componentNames.add("2nd reservation number");

			componentValue.add(nights);
			componentNames.add("Nights");

			componentValue.add(nights);
			componentNames.add("Nights");

			componentValue.add(other);
			componentNames.add("other");

			componentValue.add(other);
			componentNames.add("other");

			int count = 0;

			for (String line : lines) {
				if (line.contains("OtherRoomOtherRoom#")) {
					break;

				}
				count = count + 1;
			}

			// here to verify guest name exist or not
			boolean isGuestNameShowing = false;
			for (int i = 0; i < count; i++) {
				if (lines[i].equals(secondGuestName) || lines[i].equals(thirdGuestName)) {
					isGuestNameShowing = true;
					break;
				}
			}
			assertEquals(isGuestNameShowing, false, "Guest name is showing!");
			testSteps.add("Verified guest name is not reflecting in print report");

			for (int i = 0; i < componentNames.size(); i++) {

				boolean isValueReflecting = false;
				for (String line : lines) {
					if (line.contains(componentValue.get(i))) {
						isValueReflecting = true;
						app_logs.info(line);
						break;
					} else {
						if (componentNames.get(i).equals("arrival date")) {
							if (line.contains(tempCheckIn)) {
								isValueReflecting = true;
								break;
							}
						}

						if (componentNames.get(i).equals("depart date")) {
							if (line.contains(tempCheckOut)) {
								isValueReflecting = true;
								break;
							}
						}
					}

				}
				testSteps.add("Verified " + componentNames.get(i));
				app_logs.info("Verified " + componentNames.get(i));
				assertEquals(isValueReflecting, true, "Expected: " + componentNames.get(i) + " not found ");
				isValueReflecting = false;

			}

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("====================CREATE NEW ACCOUNT==============");
			app_logs.info("====================CREATE NEW ACCOUNT==============");

			accountNumber = Utility.GenerateRandomString15Digit();

			navigation.Accounts_NavIcon(driver);

			getTestSteps.clear();
			newAccount.ClickNewAccountbutton(driver, accountType);
			testSteps.addAll(getTestSteps);

			newAccount.AccountDetails(driver, accountType, accountName);
			testSteps.add("Enter Account name : " + accountName);

			newAccount.enterAccountNumber(driver, accountNumber);

			getTestSteps.clear();
			getTestSteps = newAccount.mailingInfo(driver, accountFirstName, accountLastName, phoneNumber, firstAddress,
					email, city, state, postalcode);
			testSteps.addAll(getTestSteps);

			newAccount.Billinginfo(driver);

			newAccount.Save(driver);
			testSteps.add("click on save");

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed tocreate account", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create account", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// create reservation for advance search
		try {
			testSteps
					.add("====================CREATE NEW RESERVATION AND ATTACHED ABOVE CREATED ACCOUNT==============");
			app_logs.info(
					"====================CREATE NEW RESERVATION AND ATTACHED ABOVE CREATED ACCOUNT==============");

			navigation.navigateToReservations(driver);

			getTestSteps.clear();
			reservation.click_NewReservation(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkInDate(driver, 0);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkOutDate(driver, +1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.enter_Adults(driver, getTestSteps, adults);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.enter_Children(driver, getTestSteps, children);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.select_Rateplan(driver, getTestSteps, ratePlan, "");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickOnFindRooms(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.selectRoom(driver, getTestSteps, roomClass, isAssign, account);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.enter_GuestName(driver, getTestSteps, salutation, accountFirstName, accountLastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.uncheck_CreateGuestProfile(driver, getTestSteps, "No");
			testSteps.addAll(getTestSteps);

			app_logs.info("==========ASSOCIATE ACCOUNT==========");
			testSteps.add("==========ASSOCIATE ACCOUNT==========");
			reservation.selectAccount(driver, getTestSteps, accountName, accountType);

			testSteps.add("Add account number" + accountNumber);
			testSteps.add("Verified account number is attached with reservation: " + accountNumber);

			getTestSteps.clear();
			reservation.selectReferral(driver, referral);
			testSteps.add("Successfully Selected Referral as :" + referral);

			getTestSteps.clear();
			reservation.clickBookNow(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			fourthReservationNumber = reservation.getReservationConfirmationNumber(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickCloseReservationSavePopup(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getAccountName = reservation.getAccountName(driver);
			testSteps.add("Expected account name: " + accountName);
			testSteps.add("Found: " + getAccountName);
			assertEquals(getAccountName, accountName, "Failed to verify attached account");
			testSteps.add("Verified account is attached");

			getTestSteps.clear();
			fourthRoomNumber = roomClassAbb + " : " + reservation.getRoomNo_ResDetail(driver);
			testSteps.add("Room number is:" + firstRoomNumber);
			app_logs.info("Room number is:" + firstRoomNumber);

			getTestSteps.clear();
			roomCharges = reservation.getRoomCharge_TripSummary(driver);
			testSteps.add("Room charges is: " + roomCharges);

			reservation.closeReservationTab(driver);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("====================ADVANCED RESERATION SEARCH WITH FIRST NAME==============");
			app_logs.info("====================ADVANCED RESERATION SEARCH WITH FIRST NAME==============");

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnAdvance(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = searchReservation.advanceSearchWithGuestName(driver, accountFirstName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnSearchButton(driver);
			testSteps.addAll(getTestSteps);

			String getGuestName = searchReservation.getGuestNameAfterSearch(driver, 1);
			testSteps.add("Expected guest name: " + accountFirstName);
			testSteps.add("Found: " + getGuestName);
			assertTrue(getGuestName.contains(accountFirstName));
			testSteps.add("Verified guest name");

			String getReservationNumber = searchReservation.getReservationNumberAfterSearch(driver, 1);
			testSteps.add("Expected reservation number: " + fourthReservationNumber);
			testSteps.add("Found : " + getReservationNumber);
			assertEquals(getReservationNumber, fourthReservationNumber);
			testSteps.add("Verified reservation number");

			String getAdults = searchReservation.getAdultsAfterSearch(driver, 1);
			testSteps.add("Expected adults: " + adults);
			testSteps.add("Found : " + getAdults);
			assertEquals(getAdults, adults);
			testSteps.add("Verified adults");

			String getChildren = searchReservation.getChildrenAfterSearch(driver, 1);
			testSteps.add("Expected children: " + children);
			testSteps.add("Found : " + getChildren);
			assertEquals(getChildren, children);
			testSteps.add("Verified children");

			String getreservationStatus = searchReservation.getReservationStatusAfterSearch(driver, 1);
			testSteps.add("Expected status: " + status);
			testSteps.add("Found : " + getreservationStatus);
			assertEquals(getreservationStatus, status);
			testSteps.add("Verified status");

			String getRoom = searchReservation.getRoomAfterSearch(driver, 1);
			testSteps.add("Expected room: " + fourthRoomNumber);
			testSteps.add("Found : " + getRoom);
			assertEquals(getRoom, fourthRoomNumber);
			testSteps.add("Verified room");

			String getArrivalDate = searchReservation.getArrivalDateAfterSearch(driver, 1);
			testSteps.add("Expected arrival date: " + checkIn);
			testSteps.add("Found : " + getArrivalDate);
			assertEquals(getArrivalDate, checkIn);
			testSteps.add("Verified arrival date");

			String getDepartDate = searchReservation.getDepartDateAfterSearch(driver, 1);
			testSteps.add("Expected depart date: " + checkOut);
			testSteps.add("Found : " + getDepartDate);
			assertEquals(getDepartDate, checkOut);
			testSteps.add("Verified depart date");

			String getNights = searchReservation.getNightsAfterSearch(driver, 1);
			testSteps.add("Expected night: " + nights);
			testSteps.add("Found : " + getNights);
			assertEquals(getNights, nights);
			testSteps.add("Verified night");

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to search with first name", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to search with first name", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add(
					"====================VERIFY GUEST RESERVATION REPORT AFTER SEARCH WITH FIRST NAME==============");
			app_logs.info(
					"====================VERIFY GUEST RESERVATION REPORT AFTER SEARCH WITH FIRST NAME==============");

			getTestSteps = searchReservation.clickOnPrintIcon(driver);

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnPrintButton(driver);
			testSteps.addAll(getTestSteps);

			searchReservation.downloadPDFReport(driver);

			getTestSteps.clear();
			fileName = Utility.download_status(driver);
			testSteps.add("Download Successful");

			app_logs.info("fileName : " + fileName);
			lines = Utility.checkPDF_Array(fileName);

			ArrayList<String> componentNames = new ArrayList<>();
			ArrayList<String> componentValue = new ArrayList<>();

			componentValue.add(accountFirstName);
			componentNames.add("Guest first name");

			componentValue.add(accountLastName);
			componentNames.add("guest last name");

			componentValue.add(status);
			componentNames.add("reservation status");

			componentValue.add(fourthRoomNumber);
			componentNames.add("room number");

			componentValue.add(checkIn);
			componentNames.add("arrival date");

			componentValue.add(checkOut);
			componentNames.add("depart date");

			componentValue.add(adults);
			componentNames.add("adults");

			componentValue.add(children);
			componentNames.add("children");

			componentValue.add(roomCharges);
			componentNames.add("Room");

			componentValue.add(fourthReservationNumber);
			componentNames.add("reservation number");

			componentValue.add(nights);
			componentNames.add("Nights");

			componentValue.add(other);
			componentNames.add("other");

			int count = 0;
			System.out.println(lines.toString());
			for (String line : lines) {
				System.out.println("line: " + line);
				if (line.contains("OtherRoomOtherRoom#")) {
					break;

				}
				count = count + 1;
			}

			// here to verify guest name exist or not
			System.out.println("count: " + count);
			boolean isGuestNameShowing = false;
			for (int i = 0; i < count; i++) {
				if (lines[i].equals(fourthGuestName)) {
					isGuestNameShowing = true;
					break;
				}
			}

			assertEquals(isGuestNameShowing, false, "Guest name is showing!");
			testSteps.add("Verified guest name is not reflecting in report");

			for (int i = 0; i < componentNames.size(); i++) {

				boolean isValueReflecting = false;
				for (String line : lines) {
					if (line.contains(componentValue.get(i))) {
						isValueReflecting = true;
						break;
					} else {
						if (componentNames.get(i).equals("arrival date")) {
							if (line.contains(tempCheckIn)) {
								isValueReflecting = true;
								break;
							}
						}

						if (componentNames.get(i).equals("depart date")) {
							if (line.contains(tempCheckOut)) {
								isValueReflecting = true;
								break;
							}
						}
					}

				}
				assertEquals(isValueReflecting, true, "Expected: " + componentNames.get(i) + " not found ");
				testSteps.add("Verified " + componentNames.get(i));
				app_logs.info("Verified " + componentNames.get(i));
				isValueReflecting = false;

			}

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add(
					"====================ADVANCED RESERVATION SEARCH WITH EMAIL ADDRESS AND PHONE NUMBER==============");
			app_logs.info(
					"====================ADVANCED RESERVATION SEARCH WITH EMAIL ADDRESS AND PHONE NUMBER==============");

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnAdvance(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = searchReservation.advanceSearchWithEmailAndPhoneNumber(driver, email, phoneNumber);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnSearchButton(driver);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to search with email and phone number", testName, "Reservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to search with email and phone number", testName, "Reservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add(
					"====================VERIFY GUEST RESERVATION REPORT AFTER SEARCH WITH EMAIL ADDRESS AND PHONE NUMBER==============");
			app_logs.info(
					"====================VERIFY GUEST RESERVATION REPORT AFTER SEARCH WITH EMAIL ADDRESS AND PHONE NUMBER==============");

			getTestSteps = searchReservation.clickOnPrintIcon(driver);

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnPrintButton(driver);
			testSteps.addAll(getTestSteps);

			searchReservation.downloadPDFReport(driver);

			getTestSteps.clear();
			fileName = Utility.download_status(driver);
			testSteps.add("Download Successful");

			app_logs.info("fileName : " + fileName);
			lines = Utility.checkPDF_Array(fileName);

			ArrayList<String> componentNames = new ArrayList<>();
			ArrayList<String> componentValue = new ArrayList<>();

			componentValue.add(accountFirstName);
			componentNames.add("Guest first name");

			componentValue.add(accountLastName);
			componentNames.add("guest last name");

			componentValue.add(status);
			componentNames.add("reservation status");

			componentValue.add(fourthRoomNumber);
			componentNames.add("room number");

			componentValue.add(checkIn);
			componentNames.add("arrival date");

			componentValue.add(checkOut);
			componentNames.add("depart date");

			componentValue.add(adults);
			componentNames.add("adults");

			componentValue.add(children);
			componentNames.add("children");

			componentValue.add(roomCharges);
			componentNames.add("Room");

			componentValue.add(fourthReservationNumber);
			componentNames.add("reservation number");

			componentValue.add(nights);
			componentNames.add("Nights");

			componentValue.add(other);
			componentNames.add("other");

			componentValue.add(email);
			componentNames.add("Email");

			componentValue.add(phoneNumber);
			componentNames.add("Phone Number");

			int count = 0;

			for (String line : lines) {
				System.out.println("line: " + line);
				if (line.contains("OtherRoomOtherRoom#")) {
					break;

				}
				count = count + 1;
			}

			// here to verify guest name exist or not
			boolean isEmailShowing = false;
			boolean isPhoneShowing = false;
			System.out.println("email: " + email);
			System.out.println("phoneNumber: " + phoneNumber);
			for (int i = 0; i < count; i++) {
				if (lines[i].contains(email)) {
					isEmailShowing = true;
					break;
				}

			}
			assertEquals(isEmailShowing, true, "Email is not showing!");
			testSteps.add("Verified email is reflecting in print report");
			for (int i = 0; i < count; i++) {

				if (lines[i].contains(phoneNumber)) {
					isPhoneShowing = true;
					break;
				}
			}
			assertEquals(isPhoneShowing, true, "Phone number is not showing!");
			testSteps.add("Verified Phone number is reflecting in print report");

			for (int i = 0; i < componentNames.size(); i++) {

				boolean isValueReflecting = false;
				for (String line : lines) {
					if (line.contains(componentValue.get(i))) {
						isValueReflecting = true;
						break;
					} else {
						if (componentNames.get(i).equals("arrival date")) {
							if (line.contains(tempCheckIn)) {
								isValueReflecting = true;
								break;
							}
						}

						if (componentNames.get(i).equals("depart date")) {
							if (line.contains(tempCheckOut)) {
								isValueReflecting = true;
								break;
							}
						}
					}

				}
				assertEquals(isValueReflecting, true, "Expected: " + componentNames.get(i) + " not found ");
				testSteps.add("Verified " + componentNames.get(i));
				app_logs.info("Verified " + componentNames.get(i));
				isValueReflecting = false;

			}

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("====================ADVANCED RESERVATION SEARCH WITH COUNTRY AND STATE==============");
			app_logs.info("====================ADVANCED RESERVATION SEARCH WITH COUNTRY AND STATE==============");

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnAdvance(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = searchReservation.advanceSearchWithCountry(driver, country);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = searchReservation.advanceSearchWithState(driver, state);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnSearchButton(driver);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to search with country and state", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to search with country and state", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add(
					"====================VERIFY GUEST RESERVATION REPORT AFTER SEARCH WITH COUNTRY AND STATE==============");
			app_logs.info(
					"====================VERIFY GUEST RESERVATION REPORT AFTER SEARCH WITH COUNTRY AND STATE==============");

			getTestSteps = searchReservation.clickOnPrintIcon(driver);

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnPrintButton(driver);
			testSteps.addAll(getTestSteps);

			Wait.wait15Second();

			searchReservation.downloadPDFReport(driver);

			getTestSteps.clear();
			fileName = Utility.download_status(driver);
			testSteps.add("Download Successful");

			app_logs.info("fileName : " + fileName);
			lines = Utility.checkPDF_Array(fileName);

			ArrayList<String> componentNames = new ArrayList<>();
			ArrayList<String> componentValue = new ArrayList<>();

			componentValue.add(accountFirstName);
			componentNames.add("Guest first name");

			componentValue.add(accountLastName);
			componentNames.add("guest last name");

			componentValue.add(status);
			componentNames.add("reservation status");

			componentValue.add(fourthRoomNumber);
			componentNames.add("room number");

			componentValue.add(checkIn);
			componentNames.add("arrival date");

			componentValue.add(checkOut);
			componentNames.add("depart date");

			componentValue.add(adults);
			componentNames.add("adults");

			componentValue.add(children);
			componentNames.add("children");

			componentValue.add(roomCharges);
			componentNames.add("Room");

			componentValue.add(fourthReservationNumber);
			componentNames.add("reservation number");

			componentValue.add(nights);
			componentNames.add("Nights");

			componentValue.add(other);
			componentNames.add("other");

			componentValue.add(country);
			componentNames.add("country");

			componentValue.add(state);
			componentNames.add("state");

			int count = 0;

			for (String line : lines) {
				System.out.println("line: " + line);
				if (line.contains("OtherRoomOtherRoom#")) {
					break;

				}
				count = count + 1;
			}

			// here to verify guest name exist or not
			boolean isCountryShowing = false;
			boolean isStateShowing = false;
			for (int i = 0; i < count; i++) {
				if (lines[i].contains(country)) {
					isCountryShowing = true;
					break;
				}
			}
			assertEquals(isCountryShowing, true, "Failed: Country is not showing!");
			testSteps.add("Verified Country is reflecting in print report");
			for (int i = 0; i < count; i++) {
				if (lines[i].contains(state)) {
					isStateShowing = true;
					break;
				}
			}
			assertEquals(isStateShowing, true, "Failed: State is not showing!");
			testSteps.add("Verified state is reflecting in print report");

			for (int i = 0; i < componentNames.size(); i++) {

				boolean isValueReflecting = false;
				for (String line : lines) {
					if (line.contains(componentValue.get(i))) {
						isValueReflecting = true;
						break;
					} else {
						if (componentNames.get(i).equals("arrival date")) {
							if (line.contains(tempCheckIn)) {
								isValueReflecting = true;
								break;
							}
						}

						if (componentNames.get(i).equals("depart date")) {
							if (line.contains(tempCheckOut)) {
								isValueReflecting = true;
								break;
							}
						}
					}

				}
				assertEquals(isValueReflecting, true, "Expected: " + componentNames.get(i) + " not found ");
				testSteps.add("Verified " + componentNames.get(i));
				app_logs.info("Verified " + componentNames.get(i));
				isValueReflecting = false;

			}

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("====================ADVANCED RESERVATION SEARCH WITH ACCOUNT NUMBER==============");
			app_logs.info("====================ADVANCED RESERVATION SEARCH WITH ACCOUNT NUMBER==============");

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnAdvance(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = searchReservation.advanceSearchWithAccountNumber(driver, accountNumber);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnSearchButton(driver);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to search with account number", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to search with account number", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add(
					"====================VERIFY GUEST RESERVATION REPORT AFTER SEARCH WITH ACCOUNT NUMBER==============");
			app_logs.info(
					"====================VERIFY GUEST RESERVATION REPORT AFTER SEARCH WITH ACCOUNT NUMBER==============");

			getTestSteps = searchReservation.clickOnPrintIcon(driver);

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnPrintButton(driver);
			testSteps.addAll(getTestSteps);

			searchReservation.downloadPDFReport(driver);

			getTestSteps.clear();
			fileName = Utility.download_status(driver);
			testSteps.add("Download Successful");

			app_logs.info("fileName : " + fileName);
			lines = Utility.checkPDF_Array(fileName);

			ArrayList<String> componentNames = new ArrayList<>();
			ArrayList<String> componentValue = new ArrayList<>();

			componentValue.add(accountFirstName);
			componentNames.add("Guest first name");

			componentValue.add(accountLastName);
			componentNames.add("guest last name");

			componentValue.add(status);
			componentNames.add("reservation status");

			componentValue.add(fourthRoomNumber);
			componentNames.add("room number");

			componentValue.add(checkIn);
			componentNames.add("arrival date");

			componentValue.add(checkOut);
			componentNames.add("depart date");

			componentValue.add(adults);
			componentNames.add("adults");

			componentValue.add(children);
			componentNames.add("children");

			componentValue.add(roomCharges);
			componentNames.add("Room");

			componentValue.add(fourthReservationNumber);
			componentNames.add("reservation number");

			componentValue.add(nights);
			componentNames.add("Nights");

			componentValue.add(other);
			componentNames.add("other");

			componentValue.add(accountNumber);
			componentNames.add("Account Number");

			int count = 0;

			for (String line : lines) {
				if (line.contains("OtherRoomOtherRoom#")) {
					break;

				}
				count = count + 1;
			}

			// here to verify guest name exist or not
			boolean isAccountNumberShowing = false;
			for (int i = 0; i < count; i++) {
				if (lines[i].contains(accountNumber)) {
					isAccountNumberShowing = true;
					break;
				}

			}
			assertEquals(isAccountNumberShowing, true, "Failed: Account number is not showing!");
			testSteps.add("Verified account number is reflecting in print report");

			for (int i = 0; i < componentNames.size(); i++) {

				boolean isValueReflecting = false;
				for (String line : lines) {
					if (line.contains(componentValue.get(i))) {
						isValueReflecting = true;
						break;
					} else {
						if (componentNames.get(i).equals("arrival date")) {
							if (line.contains(tempCheckIn)) {
								isValueReflecting = true;
								break;
							}
						}

						if (componentNames.get(i).equals("depart date")) {
							if (line.contains(tempCheckOut)) {
								isValueReflecting = true;
								break;
							}
						}
					}

				}
				assertEquals(isValueReflecting, true, "Expected: " + componentNames.get(i) + " not found ");
				testSteps.add("Verified " + componentNames.get(i));
				app_logs.info("Verified " + componentNames.get(i));
				isValueReflecting = false;

			}

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		String tempCheckInDate = null;
		String tempCheckOutDate = null;
		try {

			testSteps.add(
					"====================ADVANCED RESERVATION SEARCH WITH STAY FROM AND STAY TO FIELD==============");
			app_logs.info(
					"====================ADVANCED RESERVATION SEARCH WITH STAY FROM AND STAY TO FIELD==============");

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnAdvance(driver);
			testSteps.addAll(getTestSteps);

			tempCheckInDate = Utility.getCurrentDate("MM/dd/yyyy");
			System.out.println("checkin date" + checkInDate);
			tempCheckOutDate = Utility.GetNextDate(1, "MM/dd/yyyy");
			System.out.println("checkOut date" + tempCheckOutDate);

			getTestSteps.clear();
			getTestSteps = searchReservation.advanceSearchWithCheckinDate(driver, tempCheckInDate);
			app_logs.info(checkIn);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = searchReservation.advanceSearchWithCheckoutDate(driver, tempCheckOutDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnSearchButton(driver);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to search with stay from and stay to info", testName, "Reservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to search with stay from and stay to info", testName, "Reservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add(
					"====================VERIFY GUEST RESERVATION REPORT AFTER SEARCH WITH STAY FROM AND STAY TO FIELD==============");
			app_logs.info(
					"====================VERIFY GUEST RESERVATION REPORT AFTER SEARCH WITH STAY FROM AND STAY TO FIELD==============");

			getTestSteps = searchReservation.clickOnPrintIcon(driver);

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnPrintButton(driver);
			testSteps.addAll(getTestSteps);

			searchReservation.downloadPDFReport(driver);

			getTestSteps.clear();
			fileName = Utility.download_status(driver);
			testSteps.add("Download Successful");

			app_logs.info("fileName : " + fileName);
			lines = Utility.checkPDF_Array(fileName);

			ArrayList<String> componentNames = new ArrayList<>();
			ArrayList<String> componentValue = new ArrayList<>();

			componentValue.add(accountFirstName);
			componentNames.add("Guest first name");

			componentValue.add(accountLastName);
			componentNames.add("guest last name");

			componentValue.add(status);
			componentNames.add("reservation status");

			componentValue.add(fourthRoomNumber);
			componentNames.add("room number");

			componentValue.add(checkIn);
			componentNames.add("arrival date");

			componentValue.add(checkOut);
			componentNames.add("depart date");

			componentValue.add(adults);
			componentNames.add("adults");

			componentValue.add(children);
			componentNames.add("children");

			componentValue.add(roomCharges);
			componentNames.add("Room");

			componentValue.add(fourthReservationNumber);
			componentNames.add("reservation number");

			componentValue.add(nights);
			componentNames.add("Nights");

			componentValue.add(other);
			componentNames.add("other");

			int count = 0;

			for (String line : lines) {

				if (line.contains("OtherRoomOtherRoom#")) {

					break;

				}
				count = count + 1;
			}
			System.out.println(count);
			// here to verify stayinfo exist or not

			boolean isCheckIn = false;
			boolean isCheckOut = false;
			for (int i = 0; i < count; i++) {
				System.out.println(lines[i]);
				if (lines[i].contains(tempCheckInDate)) {
					isCheckIn = true;
					break;
				}

			}
			for (int i = 0; i < count; i++) {
				if (lines[i].contains(tempCheckOutDate)) {
					isCheckOut = true;
					break;
				}
			}

			assertEquals(isCheckIn, true, "stay from info is not showing!");
			testSteps.add("Verified stay from info is reflecting in print report");

			assertEquals(isCheckOut, true, "stay to info is not showing!");
			testSteps.add("Verified stay to info is reflecting in print report");

			for (int i = 0; i < componentNames.size(); i++) {

				boolean isValueReflecting = false;
				for (String line : lines) {
					if (line.contains(componentValue.get(i))) {
						isValueReflecting = true;
						break;
					} else {
						if (componentNames.get(i).equals("arrival date")) {
							if (line.contains(tempCheckIn)) {
								isValueReflecting = true;
								break;
							}
						}

						if (componentNames.get(i).equals("depart date")) {
							if (line.contains(tempCheckOut)) {
								isValueReflecting = true;
								break;
							}
						}
					}
				}
				assertEquals(isValueReflecting, true, "Expected: " + componentNames.get(i) + " not found ");
				testSteps.add("Verified " + componentNames.get(i));
				app_logs.info("Verified " + componentNames.get(i));
				isValueReflecting = false;

			}

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("====================ADVANCED RESERVATION SEARCH WITH REFERRAL==============");
			app_logs.info("====================ADVANCED RESERVATION SEARCH WITH REFERRAL==============");

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnAdvance(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = searchReservation.advanceSearchWithReferral(driver, referral);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnSearchButton(driver);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to search with referral", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to search with referral", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add(
					"====================VERIFY GUEST RESERVATION REPORT AFTER SEARCH WITH REFERRAL==============");
			app_logs.info(
					"====================VERIFY GUEST RESERVATION REPORT AFTER SEARCH WITH REFERRAL==============");

			getTestSteps = searchReservation.clickOnPrintIcon(driver);

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnPrintButton(driver);
			testSteps.addAll(getTestSteps);

			Wait.wait10Second();

			searchReservation.downloadPDFReport(driver);

			getTestSteps.clear();
			fileName = Utility.download_status(driver);
			testSteps.add("Download Successful");

			app_logs.info("fileName : " + fileName);
			lines = Utility.checkPDF_Array(fileName);

			ArrayList<String> componentNames = new ArrayList<>();
			ArrayList<String> componentValue = new ArrayList<>();

			componentValue.add(accountFirstName);
			componentNames.add("Guest first name");

			componentValue.add(accountLastName);
			componentNames.add("guest last name");

			componentValue.add(status);
			componentNames.add("reservation status");

			componentValue.add(fourthRoomNumber);
			componentNames.add("room number");

			componentValue.add(checkIn);
			componentNames.add("arrival date");

			componentValue.add(checkOut);
			componentNames.add("depart date");

			componentValue.add(adults);
			componentNames.add("adults");

			componentValue.add(children);
			componentNames.add("children");

			componentValue.add(roomCharges);
			componentNames.add("Room");

			componentValue.add(fourthReservationNumber);
			componentNames.add("reservation number");

			componentValue.add(nights);
			componentNames.add("Nights");

			componentValue.add(other);
			componentNames.add("other");

			componentValue.add(referral);
			componentNames.add("referral");

			int count = 0;

			for (String line : lines) {
				if (line.contains("OtherRoomOtherRoom#")) {
					break;

				}
				count = count + 1;
			}

			// here to verify guest name exist or not
			boolean isReferralShowing = false;
			for (int i = 0; i < count; i++) {
				if (lines[i].contains(referral)) {
					isReferralShowing = true;
					break;
				}
			}
			assertEquals(isReferralShowing, true, "Failed: Referral is not showing!");
			testSteps.add("Verified referral is reflecting in print report");

			for (int i = 0; i < componentNames.size(); i++) {

				boolean isValueReflecting = false;
				for (String line : lines) {
					if (line.contains(componentValue.get(i))) {
						isValueReflecting = true;
						break;
					} else {
						if (componentNames.get(i).equals("arrival date")) {
							if (line.contains(tempCheckIn)) {
								isValueReflecting = true;
								break;
							}
						}

						if (componentNames.get(i).equals("depart date")) {
							if (line.contains(tempCheckOut)) {
								isValueReflecting = true;
								break;
							}
						}
					}

				}
				assertEquals(isValueReflecting, true, "Expected: " + componentNames.get(i) + " not found ");
				testSteps.add("Verified " + componentNames.get(i));
				app_logs.info("Verified " + componentNames.get(i));
				isValueReflecting = false;

			}
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify report", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getData("VerifyReportsOfSearchWithName", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
