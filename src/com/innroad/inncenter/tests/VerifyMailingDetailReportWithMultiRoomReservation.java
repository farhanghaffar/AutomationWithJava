package com.innroad.inncenter.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class VerifyMailingDetailReportWithMultiRoomReservation extends TestCore {

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
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);

	}

	@Test(dataProvider = "getData", groups = "Reservations")
	public void VerifyMailingDetailReportWithMultipleRoomReservation(
			String adult, String children, String rateplan,
			String roomClass, String isAssign, String salutation, String guestFirstName, String guestLastName,
			String isGuesProfile, String referral, String secondFirstName, String phoneNumber, String altenativePhone,
			String email, String firstAddress, String secondAddress, String thirdAddress, String city, String country,
			String state, String postalCode, String paymentMethod, String cardNumber, String nameOnCard,
			String cardExpDate, String additionalPhoneNumber, String isBillingAddressSame,
			String isBillingAddressNotSame, String guestInfoTitle, String reservationInfoTitle,
			String marketingInfoTitle, String night, String roomClassAbr) throws InterruptedException {
		
		app_logs.info("email: " + email);
		
		String scriptName = "VerifyMailingDetailReportWithMultiRoomReservation";
		String description = "Verify mailing detail report with multi room reservation<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682474' target='_blank'>"
				+ "Click here to open TestRail: C682474</a>";
		String testcatagory = "Reservations";
		testName.add(scriptName);
		testCatagory.add(testcatagory);
		testDescription.add(description);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + scriptName + " TEST.");
		app_logs.info("##################################################################################");

		CPReservationPage reservation = new CPReservationPage();
		ESTTimeZone estTimeZone = new ESTTimeZone();
		ReservationSearch searchReservation = new ReservationSearch();
		String firstRandom = Utility.GenerateRandomNumber();
		String checkIn = "";
		String checkOut = "";
		String printOnDate = "";
		String printOnDateOneminuteBack = "";
		String firstRoomNumber = "";
		String secondRoomNumber = "";
		String reservationNumber = "";
		String accountType = "";
		String account = "";
		String fileName = "";
		String[] lines = null;
		String status = "";
		
		guestFirstName = guestFirstName + firstRandom;
		guestLastName = guestLastName + firstRandom;
		secondFirstName = secondFirstName + firstRandom;
		String checkInDate = "";
		String checkOutDate = "";
		String primaryGuestName = guestFirstName + " " + guestLastName;
		String additionalGuestName = secondFirstName + " " + guestLastName;

		String firstReservationName = guestFirstName + " " + guestLastName;
		String secondReservationName = secondFirstName + " " + guestLastName;

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

			testSteps.add("================CREATE NEW RESERVATION WITH MULTI ROOM SELECTION============");
			app_logs.info("================CREATE NEW RESERVATION WITH MULTI ROOM SELECTION============");

			getTestSteps.clear();
			getTestSteps = reservation.click_NewReservation(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			checkInDate = Utility.getCurrentDate("MM/dd/yyyy");
			checkIn = estTimeZone.parseDate(checkInDate, "MM/dd/yyyy", "MMM dd, yyyy");

			System.out.println("checkin date" + checkIn);

			checkOutDate = Utility.GetNextDate(1, "MM/dd/yyyy");
			checkOut = estTimeZone.parseDate(checkOutDate, "MM/dd/yyyy", "MMM dd, yyyy");
			System.out.println("checkin date" + checkOut);

			getTestSteps.clear();
			getTestSteps = reservation.clickAddARoom(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkInDates(driver, 0, 0);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkOutDates(driver, +1, 0);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkInDates(driver, 0, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkOutDates(driver, +1, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.clickOnFindRooms(driver, testSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.selectRoom(driver, getTestSteps, roomClass, isAssign, account);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.selectRoom(driver, getTestSteps, roomClass, isAssign, account);
			testSteps.addAll(getTestSteps);

			reservation.moveToAddARoom(driver);
			getTestSteps.clear();
			getTestSteps = reservation.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			firstRoomNumber = reservation.selectRoomNumberInMRB(driver, roomClass, 0, 0);
			String SplitStr[] = firstRoomNumber.split(":");
			firstRoomNumber = SplitStr[1].trim();
			firstRoomNumber = roomClassAbr + " : " + firstRoomNumber;
			app_logs.info("First room number: " + firstRoomNumber);

			app_logs.info("email: " + email);
			getTestSteps.clear();
			reservation.enter_MailingAddress(driver, getTestSteps, salutation, guestFirstName, guestLastName,
					phoneNumber, altenativePhone, email, account, accountType, firstAddress, secondAddress,
					thirdAddress, city, country, state, postalCode, isGuesProfile);
			testSteps.addAll(getTestSteps);

			secondRoomNumber = reservation.selectRoomNumberInMRB(driver, roomClass, 0, 1);
			SplitStr = secondRoomNumber.split(":");
			secondRoomNumber = SplitStr[1].trim();
			secondRoomNumber = roomClassAbr + " : " + secondRoomNumber;
			app_logs.info("Second room number: " + secondRoomNumber);

			getTestSteps.clear();
			getTestSteps = reservation.enterAdditionalGuestName(driver, secondFirstName, guestLastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.enterAdditionalEmailAndPhoneNumber(driver, email, additionalPhoneNumber);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.enterPaymentDetails(driver, getTestSteps, paymentMethod, cardNumber, nameOnCard, cardExpDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.uncheck_BillingAddresSameAsMailingAddress(driver, getTestSteps, isBillingAddressNotSame);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.uncheck_BillingAddresSameAsMailingAddress(driver, getTestSteps, isBillingAddressSame);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.selectReferral(driver, referral);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickBookNow(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservationNumber = reservation.get_ReservationConfirmationNumber(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			status = reservation.get_ReservationStatus(driver, getTestSteps);
			app_logs.info("status :" + status);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickCloseReservationSavePopup(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			reservation.closeReservationTab(driver);

		} catch (Exception e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
			Utility.updateReport(e, "Failed to create new reservation with multi room selection", scriptName,
					"Reservation", driver);
		} catch (Error e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
			Utility.updateReport(e, "Failed to create new reservation with multi room selection", scriptName,
					"Reservation", driver);
		}
		try {
			testSteps.add("===============BASIC RESERVATION SEARCH=================");
			app_logs.info("===============BASIC RESERVATION SEARCH=================");

			getTestSteps.clear();
			getTestSteps = searchReservation.basicSearchWithGuestName(driver, guestLastName);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
			Utility.updateReport(e, "Failed to search", scriptName, "Reservation", driver);
		} catch (Error e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
			Utility.updateReport(e, "Failed to search", scriptName, "Reservation", driver);
		}

		try {

			testSteps.add("====================DOWNLOAD GUEST RESERVATION REPORT==============");
			app_logs.info("====================DOWNLOAD GUEST RESERVATION REPORT==============");

			Wait.wait10Second();
			getTestSteps.clear();
			getTestSteps = reservation.clickOnPrintIcon(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.clickOnPrintButton(driver);
			testSteps.addAll(getTestSteps);

			printOnDate = ESTTimeZone.getDateWithTime(0);
			System.out.println(printOnDate);

			printOnDateOneminuteBack = ESTTimeZone.getDateWithTime(-1);
			System.out.println(printOnDateOneminuteBack);

			reservation.downloadPDFReport(driver);

			getTestSteps.clear();
			fileName = Utility.download_status(driver);
			testSteps.add("Download Successful");

			app_logs.info("fileName : " + fileName);
			lines = Utility.checkPDF_Array(fileName);

		} catch (Exception e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
			Utility.updateReport(e, "Failed to download", scriptName, "Reservation", driver);
		} catch (Error e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
			Utility.updateReport(e, "Failed to download", scriptName, "Reservation", driver);
		}

		try {

			testSteps.add("====================VERIFY GUEST RESERVATION REPORT==============");
			app_logs.info("====================VERIFY GUEST RESERVATION REPORT==============");

			ArrayList<String> componentNames = new ArrayList<>();
			ArrayList<String> componentValue = new ArrayList<>();

			// Primary Room verification
			componentValue.add(firstReservationName);
			componentNames.add("first reservation name");

			componentValue.add(reservationNumber);
			componentNames.add("Reservation number");

			componentValue.add(firstAddress);
			componentNames.add("Mailing address");

			componentValue.add(firstAddress);
			componentNames.add("Billing address");

			componentValue.add(secondAddress);
			componentNames.add("Mailing address");

			componentValue.add(secondAddress);
			componentNames.add("Billing address");

			componentValue.add(thirdAddress);
			componentNames.add("Mailing address");

			componentValue.add(thirdAddress);
			componentNames.add("Billing address");

			componentValue.add(city);
			componentNames.add("Mailing address city");

			componentValue.add(city);
			componentNames.add("Billing address city");

			componentValue.add(country);
			componentNames.add("Mailing address country");

			componentValue.add(country);
			componentNames.add("Billing address country");

			componentValue.add(postalCode);
			componentNames.add("Mailing address postal code");

			componentValue.add(postalCode);
			componentNames.add("Billing address postal code");

			componentValue.add(email);
			componentNames.add("Mailing address email");

			componentValue.add(email);
			componentNames.add("billing address email");

			componentValue.add(phoneNumber);
			componentNames.add("Mailing address phone number");

			componentValue.add(phoneNumber);
			componentNames.add("billing address phone number");

			// Secondary room verification
			componentValue.add(secondReservationName);
			componentNames.add("second reservation name");

			componentValue.add(reservationNumber);
			componentNames.add("Reservation number");

			componentValue.add(firstAddress);
			componentNames.add("Mailing address");

			componentValue.add(firstAddress);
			componentNames.add("Billing address");

			componentValue.add(secondAddress);
			componentNames.add("Mailing address");

			componentValue.add(secondAddress);
			componentNames.add("Billing address");

			componentValue.add(thirdAddress);
			componentNames.add("Mailing address");

			componentValue.add(thirdAddress);
			componentNames.add("Billing address");

			componentValue.add(city);
			componentNames.add("Mailing address city");

			componentValue.add(city);
			componentNames.add("Billing address city");

			componentValue.add(country);
			componentNames.add("Mailing address country");

			componentValue.add(country);
			componentNames.add("Billing address country");

			componentValue.add(postalCode);
			componentNames.add("Mailing address postal code");

			componentValue.add(postalCode);
			componentNames.add("Billing address postal code");

			componentValue.add(email);
			componentNames.add("Mailing email");

			componentValue.add(email);
			componentNames.add("Billing email");

			componentValue.add(additionalPhoneNumber);
			componentNames.add("Mailing address phone number");

			componentValue.add(additionalPhoneNumber);
			componentNames.add("Billing address phone number");

			String printDate = "";
			for (String line : lines) {
				System.out.println(line);
				if (line.contains("Printed On :")) {
					printDate = line;
				}

			}
			String[] stringSplit = printDate.split(":");
			printDate = stringSplit[1] + ":" + stringSplit[2];
			System.out.println("expec: " + printOnDate);
			System.out.println("printdate: " + printDate);

			for (int i = 0; i < componentNames.size(); i++) {

				boolean isValueReflecting = false;
				for (String line : lines) {
					if (line.contains(componentValue.get(i))) {
						isValueReflecting = true;
						break;
					}
				}

				assertEquals(isValueReflecting, true, "Expected: " + componentNames.get(i) + " not found ");
				testSteps.add("Verified " + componentNames.get(i));
				isValueReflecting = false;

			}
			boolean isDate = false;
			if (printDate.equals(printOnDate)) {
				isDate = true;
				System.out.println("in if of one");
				testSteps.add("Expected print on date: " + printOnDate);
				testSteps.add("Found: " + printDate);
				assertEquals(printDate, printOnDate, "Failed: Print on date is mismatching");
				testSteps.add("Verify print on date");
			}
			if (printDate.equals(printOnDateOneminuteBack)) {
				isDate = true;
				System.out.println("in if of 2nd");
				testSteps.add("Expected print on date: " + printOnDateOneminuteBack);
				testSteps.add("Found: " + printDate);
				assertEquals(printDate, printOnDateOneminuteBack, "Failed: Print on date is mismatching");
				testSteps.add("Verify print on date");
			}
			assertEquals(isDate, true, "Failed: print date did not find");

		} catch (Exception e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
			Utility.updateReport(e, "Failed to verify report", scriptName, "Reservation", driver);
		} catch (Error e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
			Utility.updateReport(e, "Failed to verify report", scriptName, "Reservation", driver);
		}

		try {
			testSteps.add(
					"====================ADVANCED RESERATION SEARCH WITH MULTI ROOM RESERVATION NUMBER==============");
			app_logs.info(
					"====================ADVANCED RESERATION SEARCH WITH MULTI ROOM RESERVATION NUMBER==============");

			getTestSteps.clear();
			getTestSteps = reservation.clickOnAdvance(driver);
			testSteps.addAll(getTestSteps);

			String getGuestInfoText = reservation.getGuestInfoText(driver);
			testSteps.add("Expected title: " + guestInfoTitle);
			testSteps.add("Found : " + getGuestInfoText);
			assertEquals(getGuestInfoText, guestInfoTitle);
			testSteps.add("Verified title Guest Info section");

			String getReservationInfoText = reservation.getReservationInfoText(driver);
			testSteps.add("Expected title: " + reservationInfoTitle);
			testSteps.add("Found : " + getReservationInfoText);
			assertEquals(getReservationInfoText, reservationInfoTitle);
			testSteps.add("Verified title Reservation Info section");

			String getMarketingInfoText = reservation.getMarketingInfoText(driver);
			testSteps.add("Expected title: " + marketingInfoTitle);
			testSteps.add("Found : " + getMarketingInfoText);
			assertEquals(getMarketingInfoText, marketingInfoTitle);
			testSteps.add("Verified title marketing Info section");

			getTestSteps.clear();
			getTestSteps = searchReservation.advanceSearchWithReservationNumber(driver, reservationNumber);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = searchReservation.clickOnSearchButton(driver);
			testSteps.addAll(getTestSteps);

			String getGuestName = reservation.getGuestNameAfterSearch(driver, 1);
			testSteps.add("Expected guest name: " + guestLastName);
			testSteps.add("Found: " + getGuestName);
			assertTrue(getGuestName.contains(guestLastName));
			testSteps.add("Verified guest name");

			getGuestName = reservation.getGuestNameAfterSearch(driver, 2);
			testSteps.add("Expected guest name: " + guestLastName);
			testSteps.add("Found: " + getGuestName);
			assertTrue(getGuestName.contains(guestLastName));
			testSteps.add("Verified guest name");

			String getReservationNumber = reservation.getReservationNumberAfterSearch(driver, 1);
			testSteps.add("Expected reservation number: " + reservationNumber);
			testSteps.add("Found : " + getReservationNumber);
			assertEquals(getReservationNumber, reservationNumber);
			testSteps.add("Verified reservation number");

			getReservationNumber = reservation.getReservationNumberAfterSearch(driver, 2);
			testSteps.add("Expected reservation number: " + reservationNumber);
			testSteps.add("Found : " + getReservationNumber);
			assertEquals(getReservationNumber, reservationNumber);
			testSteps.add("Verified reservation number");

			String getAdults = reservation.getAdultsAfterSearch(driver, 1);
			testSteps.add("Expected adults: " + adult);
			testSteps.add("Found : " + getAdults);
			assertEquals(getAdults, adult);
			testSteps.add("Verified adults");

			getAdults = reservation.getAdultsAfterSearch(driver, 2);
			testSteps.add("Expected adults: " + adult);
			testSteps.add("Found : " + getAdults);
			assertEquals(getAdults, adult);
			testSteps.add("Verified adults");

			String getChildren = reservation.getChildrenAfterSearch(driver, 1);
			testSteps.add("Expected children: " + children);
			testSteps.add("Found : " + getChildren);
			assertEquals(getChildren, children);
			testSteps.add("Verified children");

			getChildren = reservation.getChildrenAfterSearch(driver, 2);
			testSteps.add("Expected children: " + children);
			testSteps.add("Found : " + getChildren);
			assertEquals(getChildren, children);
			testSteps.add("Verified children");

			String getreservationStatus = reservation.getReservationStatusAfterSearch(driver, 1);
			testSteps.add("Expected status: " + status);
			testSteps.add("Found : " + getreservationStatus);
			assertEquals(getreservationStatus, status);
			testSteps.add("Verified status");

			getreservationStatus = reservation.getReservationStatusAfterSearch(driver, 2);
			testSteps.add("Expected status: " + status);
			testSteps.add("Found : " + getreservationStatus);
			assertEquals(getreservationStatus, status);
			testSteps.add("Verified status");

			String getRoom = reservation.getRoomNumberAdvacedSearchInMRB(driver, primaryGuestName);
			testSteps.add("Expected room: " + firstRoomNumber);
			testSteps.add("Found : " + getRoom);
			assertEquals(getRoom, firstRoomNumber);
			testSteps.add("Verified room");

			getRoom = reservation.getRoomNumberAdvacedSearchInMRB(driver, additionalGuestName);
			testSteps.add("Expected room: " + secondRoomNumber);
			testSteps.add("Found : " + getRoom);
			assertEquals(getRoom, secondRoomNumber);
			testSteps.add("Verified room");

			String getArrivalDate = reservation.getArrivalDateAfterSearch(driver, 1);
			testSteps.add("Expected arrival date: " + checkIn);
			testSteps.add("Found : " + getArrivalDate);
			assertEquals(getArrivalDate, checkIn);
			testSteps.add("Verified arrival date");

			getArrivalDate = reservation.getArrivalDateAfterSearch(driver, 2);
			testSteps.add("Expected arrival date: " + checkIn);
			testSteps.add("Found : " + getArrivalDate);
			assertEquals(getArrivalDate, checkIn);
			testSteps.add("Verified arrival date");

			String getDepartDate = reservation.getDepartDateAfterSearch(driver, 1);
			testSteps.add("Expected depart date: " + checkOut);
			testSteps.add("Found : " + getDepartDate);
			assertEquals(getDepartDate, checkOut);
			testSteps.add("Verified depart date");

			getDepartDate = reservation.getDepartDateAfterSearch(driver, 2);
			testSteps.add("Expected depart date: " + checkOut);
			testSteps.add("Found : " + getDepartDate);
			assertEquals(getDepartDate, checkOut);
			testSteps.add("Verified depart date");

			String getNights = reservation.getNightsAfterSearch(driver, 1);
			testSteps.add("Expected night: " + night);
			testSteps.add("Found : " + getNights);
			assertEquals(getNights, night);
			testSteps.add("Verified night");

			getNights = reservation.getNightsAfterSearch(driver, 2);
			testSteps.add("Expected night: " + night);
			testSteps.add("Found : " + getNights);
			assertEquals(getNights, night);
			testSteps.add("Verified night");

		} catch (Exception e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
			Utility.updateReport(e, "Failed to advanced search", scriptName, "Reservation", driver);
		} catch (Error e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
			Utility.updateReport(e, "Failed to advance search", scriptName, "Reservation", driver);
		}

		try {

			testSteps.add("====================DOWNLOAD GUEST RESERVATION REPORT WITH ADVANCED SEARCH==============");
			app_logs.info("====================DOWNLOAD GUEST RESERVATION REPORT WITH ADVANCED SEARCH==============");

			getTestSteps.clear();
			getTestSteps = reservation.clickOnPrintIcon(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.clickOnPrintButton(driver);
			testSteps.addAll(getTestSteps);

			printOnDate = ESTTimeZone.getDateWithTime(0);
			System.out.println(printOnDate);

			printOnDateOneminuteBack = ESTTimeZone.getDateWithTime(-1);
			System.out.println(printOnDateOneminuteBack);

			reservation.downloadPDFReport(driver);

			getTestSteps.clear();
			fileName = Utility.download_status(driver);
			testSteps.add("Download Successful");

			app_logs.info("fileName : " + fileName);
			lines = Utility.checkPDF_Array(fileName);

		} catch (Exception e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
			Utility.updateReport(e, "Failed to download advanced search report", scriptName, "Reservation", driver);
		} catch (Error e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
			Utility.updateReport(e, "Failed to download advance search report", scriptName, "Reservation", driver);
		}

		try {

			testSteps.add(
					"====================VERIFY ADVANCE GUEST RESERVATION REPORT WITH RESERVATION NUMBER SEARCH==============");
			app_logs.info(
					"====================VERIFY ADVANCE GUEST RESERVATION REPORT WITH RESERVATION NUMBER SEARCH==============");

			ArrayList<String> componentNames = new ArrayList<>();
			ArrayList<String> componentValue = new ArrayList<>();

			// Primary Room verification
			componentValue.add(firstReservationName);
			componentNames.add("first reservation name");

			componentValue.add(reservationNumber);
			componentNames.add("Reservation number");

			componentValue.add(firstAddress);
			componentNames.add("Mailing address");

			componentValue.add(firstAddress);
			componentNames.add("Billing address");

			componentValue.add(secondAddress);
			componentNames.add("Mailing address");

			componentValue.add(secondAddress);
			componentNames.add("Billing address");

			componentValue.add(thirdAddress);
			componentNames.add("Mailing address");

			componentValue.add(thirdAddress);
			componentNames.add("Billing address");

			componentValue.add(city);
			componentNames.add("Mailing address city");

			componentValue.add(city);
			componentNames.add("Billing address city");

			componentValue.add(country);
			componentNames.add("Mailing address country");

			componentValue.add(country);
			componentNames.add("Billing address country");

			componentValue.add(postalCode);
			componentNames.add("Mailing address postal code");

			componentValue.add(postalCode);
			componentNames.add("Billing address postal code");

			componentValue.add(email);
			componentNames.add("Mailing address email");

			componentValue.add(email);
			componentNames.add("Billing address email");

			componentValue.add(phoneNumber);
			componentNames.add("Mailing address phone number");

			componentValue.add(phoneNumber);
			componentNames.add("Billing address phone number");

			// Secondary room verification
			componentValue.add(secondReservationName);
			componentNames.add("second reservation name");

			componentValue.add(reservationNumber);
			componentNames.add("Reservation number");

			componentValue.add(firstAddress);
			componentNames.add("Mailing address");

			componentValue.add(firstAddress);
			componentNames.add("Billing address");

			componentValue.add(secondAddress);
			componentNames.add("Mailing address");

			componentValue.add(secondAddress);
			componentNames.add("Billing address");

			componentValue.add(thirdAddress);
			componentNames.add("Mailing address");

			componentValue.add(thirdAddress);
			componentNames.add("Billing address");

			componentValue.add(city);
			componentNames.add("Mailing address city");

			componentValue.add(city);
			componentNames.add("Billing address city");

			componentValue.add(country);
			componentNames.add("Mailing address country");

			componentValue.add(country);
			componentNames.add("Billing address country");

			componentValue.add(postalCode);
			componentNames.add("Mailing address postal code");

			componentValue.add(postalCode);
			componentNames.add("Billing address postal code");

			componentValue.add(email);
			componentNames.add("Mailing address email");

			componentValue.add(email);
			componentNames.add("Billing address email");

			componentValue.add(additionalPhoneNumber);
			componentNames.add("Mailing address phone number");

			componentValue.add(additionalPhoneNumber);
			componentNames.add("Billing address phone number");

			String printDate = "";
			for (String line : lines) {
				System.out.println();
				if (line.contains("Printed On :")) {
					printDate = line;
				}

			}
			String[] stringSplit = printDate.split(":");
			printDate = stringSplit[1] + ":" + stringSplit[2];
			System.out.println("expec: " + printOnDate);
			System.out.println("printdate: " + printDate);

			for (int i = 0; i < componentNames.size(); i++) {

				boolean isValueReflecting = false;
				for (String line : lines) {
					if (line.contains(componentValue.get(i))) {
						isValueReflecting = true;
						break;
					}

				}
				assertEquals(isValueReflecting, true, "Expected: " + componentNames.get(i) + " not found ");
				testSteps.add("Verified " + componentNames.get(i));
				isValueReflecting = false;

			}
			boolean isDate = false;
			if (printDate.equals(printOnDate)) {
				isDate = true;
				System.out.println("in if of one");
				testSteps.add("Expected print on date: " + printOnDate);
				testSteps.add("Found: " + printDate);
				assertEquals(printDate, printOnDate, "Failed: Print on date is mismatching");
				testSteps.add("Verify print on date");
			}
			if (printDate.equals(printOnDateOneminuteBack)) {
				isDate = true;
				System.out.println("in if of 2nd");
				testSteps.add("Expected print on date: " + printOnDateOneminuteBack);
				testSteps.add("Found: " + printDate);
				assertEquals(printDate, printOnDateOneminuteBack, "Failed: Print on date is mismatching");
				testSteps.add("Verify print on date");
			}
			assertEquals(isDate, true, "Failed: print date did not find");
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
		} catch (Exception e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
			Utility.updateReport(e, "Failed to verify report", scriptName, "Reservation", driver);
		} catch (Error e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
			Utility.updateReport(e, "Failed to verify report", scriptName, "Reservation", driver);
		}

	}

	@DataProvider
	public Object[][] getData() {

		// return test data from the sheet name provided
		return Utility.getData("VerifyMailingDetailReport", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		// driver.quit();

	}

}
