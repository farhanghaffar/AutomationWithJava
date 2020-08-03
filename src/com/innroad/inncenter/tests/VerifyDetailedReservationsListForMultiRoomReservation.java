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
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.Reservation;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.Tapechart;
import com.innroad.inncenter.properties.OR_Reservation;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class VerifyDetailedReservationsListForMultiRoomReservation extends TestCore {

	// Automation-1494
	private WebDriver driver = null;

	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();
	ArrayList<String> testName = new ArrayList<>();
	ArrayList<String> testCategory = new ArrayList<>();
	ArrayList<String> testDescription = new ArrayList<>();
	public static String description = "";
	public static String testCatagory = "";

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {

		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservations")
	public void verifyDetailedReservationsListForMultiRoomReservation(String rateName, String baseAmount,
			String addtionalAdult, String additionalChild, String displayName, String associateSession,
			String RatePolicy, String RateDescriptio, String juniorRoomClassAbbreviation, String roomClassName,
			String bedsCount, String maxAdults, String maxPersons, String roomQuantity, String startRoomNumber,
			String ratePlan, String rateType, String RateAttribute, String Interval, String source, String adultsValue,
			String childrenValue, String marketSegment, String referral, String guestFirstName, String guestLastName,
			String salutation, String roomCharge, String phonenumber, String alternativeNumber, String firstAddress,
			String secondAddress, String thirdAddress, String email, String city, String state, String postalcode,
			String accounttype, String adults, String children, String secndRoomClassAbbreviation,
			String secondRoomClassName, String accountFirstName, String accountLastName, String firstNoteType,
			String secondNoteType, String thirdNoteType, String note, String noteDescription, String reservedBy,
			String paymentType, String cardNumber, String CardName, String cardExpiry)
			throws InterruptedException, IOException {

		String scriptName = "VerifyDetailedReservationsListForMultiRoomReservation";
		description = "Desktop : Verify 'Detailed Reservations List' for Multi room reservation.<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682472' target='_blank'>"
				+ "Click here to open TestRail: C682472</a>";

		testName.add(scriptName);
		testDescription.add(description);
		testCategory.add("Reservation");

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + scriptName + " TEST.");
		app_logs.info("##################################################################################");

		Navigation navigation = new Navigation();

		ReservationSearch reservationSearch = new ReservationSearch();
		CPReservationPage reservaionPage = new CPReservationPage();
		String randomNumber = Utility.GenerateRandomNumber();
		Reservation reservation = new Reservation();
		Folio folio = new Folio();
		Account account = new Account();
		ArrayList<String> selectedRoomsList = new ArrayList<>();

		String primaryGuestFirstName = guestFirstName + "1";
		String additionalGuestFirstName = guestFirstName + "2";
		guestLastName = guestLastName + randomNumber;
		accountLastName = accountLastName + randomNumber;
		String accountName = accountFirstName + accountLastName;
		String primaryRoomNote1Subject = primaryGuestFirstName + "Note1";
		String primaryRoomNote2Subject = primaryGuestFirstName + "Note2";
		String primaryRoomNote3Subject = primaryGuestFirstName + "Note3";
		String primaryRoomNoteDescription = primaryGuestFirstName + noteDescription;

		String additionalRoomNote1Subject = additionalGuestFirstName + "Note1";
		String additionalRoomNote2Subject = additionalGuestFirstName + "Note2";
		String additionalRoomNote3Subject = additionalGuestFirstName + "Note3";
		String additionalRoomNoteDescription = additionalGuestFirstName + noteDescription;
		String reservationNumber = "";
		String tripTotal = null;
		String balance = null;
		String taxes = null;
		String roomChargeAmount = null;
		String totalCharges = null;
		String amountwithTax = null;
		String amount = null;
		String folioBalance = null;
		String checkIn = "";
		String checkOut = "";
		String Payments = "0.00";
		String firstRoom = "";
		String secondRoom = "";
		String reservationStatus = "";
		String accountNumber="";
		accountNumber = Utility.GenerateRandomString15Digit();


		try {
			if (!Utility.insertTestName.containsKey(scriptName)) {
				Utility.insertTestName.put(scriptName, scriptName);
				Utility.reTry.put(scriptName, 0);

			} else {
				Utility.reTry.replace(scriptName, 1);
			}
			driver = getDriver();
			login_CP(driver);
			testSteps.add("Entered appication URL : " + TestCore.envURL);
			testSteps.add("Logged into the application ");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// navigate to accounts
		try {
			navigation.Accounts(driver);
			testSteps.add("Navigate Accounts");
			app_logs.info("Navigate Accounts");
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Navigate Accounts", scriptName, "NavigateAccounts", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Navigate Accounts", scriptName, "NavigateAccounts", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// New account
		try {
			app_logs.info("==========CREATE '" + accounttype + "' ACCOUNT==========");
			testSteps.add("==========CREATE '" + accounttype + "' ACCOUNT==========");
			account.ClickNewAccountbutton(driver, accounttype);
			testSteps.add("Click New Account Button");
			app_logs.info("Click New Account Button");
			account.AccountDetails(driver, accounttype, accountName);
			testSteps.add("Select Acount Type : " + accounttype);
			app_logs.info("Select Acount Type : " + accounttype);
			testSteps.add("Enter Acount Name : " + accountName);
			app_logs.info("Enter Acount Name : " + accountName);
			
			account.enterAccountNumber(driver, accountNumber);
			getTestSteps.clear();
			getTestSteps = account.AccountAttributes(driver, test, marketSegment, referral, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = account.Mailinginfo(driver, test, accountFirstName, accountLastName, phonenumber,
					alternativeNumber, firstAddress, secondAddress, thirdAddress, email, city, state, postalcode,
					getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = account.Billinginfo(driver, test, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = account.AccountSave(driver, test, accountName, getTestSteps);
			testSteps.addAll(getTestSteps);
			Utility.Account_Number = account.getAccountNum(driver);
			account.closeAccountTab(driver);
			testSteps.add("Close Account");
			app_logs.info("Close Account");
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Create New Account", scriptName, "NewAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Create New Account", scriptName, "NewAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verify account
		try {
			app_logs.info("==========SEARCH '" + accounttype + "' ACCOUNT==========");
			testSteps.add("==========SEARCH '" + accounttype + "' ACCOUNT==========");
			getTestSteps.clear();
			getTestSteps = account.searchAccount(driver, test, accounttype, accountName, Utility.Account_Number,
					"Active", getTestSteps);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Verify Account", scriptName, "VerifyAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Verify Account", scriptName, "VerifyAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Create MRB reservation
		try {
			app_logs.info("==========CREATE MRB ROOM RESERVATION==========");
			testSteps.add("==========CREATE MRB ROOM RESERVATION==========");
			navigation.navigateToReservations(driver);
			testSteps.add("Navigate Reservations");
			app_logs.info("Navigate Reservations");
			reservaionPage.click_NewReservation(driver, testSteps);
			reservaionPage.clickAddARoom(driver);

			String CheckInDate = Utility.getCurrentDate("MM/dd/yyyy");
			checkIn = ESTTimeZone.parseDate(CheckInDate, "MM/dd/yyyy", "MMM dd, yyyy");

			String CheckoutDate = Utility.GetNextDate(1, "MM/dd/yyyy");
			checkOut = ESTTimeZone.parseDate(CheckoutDate, "MM/dd/yyyy", "MMM dd, yyyy");

			reservaionPage.enterDate(driver, "start", CheckInDate, "1");
			testSteps.add("Select First Room CheckIn Date : " + CheckInDate);
			app_logs.info("Select First Room CheckIn Date : " + CheckInDate);

			reservaionPage.enterDate(driver, "end", CheckoutDate, "1");
			testSteps.add("Select First Room CheckOut Date : " + CheckoutDate);
			app_logs.info("Select First Room CheckOut Date : " + CheckoutDate);

			reservaionPage.enterAdultsChildren(driver, adults, adultsValue, 1);
			testSteps.add("Enter First Room Adults : " + adultsValue);
			app_logs.info("Enter First Room Adults : " + adultsValue);
			reservaionPage.enterAdultsChildren(driver, children, childrenValue, 1);
			testSteps.add("Enter First Room Adults : " + childrenValue);
			app_logs.info("Enter First Room Adults : " + childrenValue);
			reservaionPage.selectRateplan(driver, ratePlan, "", 1);
			testSteps.add("Select First Room Rate Plan : " + ratePlan);
			app_logs.info("Select First Room Rate Plan : " + ratePlan);

			reservaionPage.enterDate(driver, "start", CheckInDate, "2");
			testSteps.add("Select Second Room CheckIn Date : " + CheckInDate);
			app_logs.info("Select Second Room CheckIn Date : " + CheckInDate);
			reservaionPage.enterDate(driver, "end", CheckoutDate, "2");
			testSteps.add("Select Second Room CheckOut Date : " + CheckoutDate);
			app_logs.info("Select Second Room CheckOut Date : " + CheckoutDate);

			reservaionPage.enterAdultsChildren(driver, adults, adultsValue, 2);
			testSteps.add("Enter Second Room Adults : " + adultsValue);
			app_logs.info("Enter Second Room Adults : " + adultsValue);
			reservaionPage.enterAdultsChildren(driver, children, childrenValue, 2);
			testSteps.add("Enter Second Room Adults : " + childrenValue);
			app_logs.info("Enter Second Room Adults : " + childrenValue);

			reservaionPage.selectRateplan(driver, ratePlan, "", 2);
			testSteps.add("Select Second Room Rate Plan : " + ratePlan);
			app_logs.info("Select Second Room Rate Plan : " + ratePlan);

			reservaionPage.clickOnFindRooms(driver, testSteps);
			reservaionPage.selectRoom(driver, roomClassName, 2, 1, getTestSteps);
			
			reservaionPage.moveToAddARoom(driver);
			reservaionPage.selectRoom(driver, secondRoomClassName, 2, 2, getTestSteps);

			
			reservaionPage.clickNext(driver, getTestSteps);

		} catch (Exception e) {

			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Search Room", scriptName, "SEARCHROOM", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Search Room", scriptName, "SEARCHROOMS", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// ADD PRIMARY ROOM
		try {
			app_logs.info("==========PRIMARY ROOM DETAILS==========");
			testSteps.add("==========PRIMARY ROOM DETAILS==========");
			reservaionPage.addPrimaryRoom(driver, getTestSteps, selectedRoomsList);
			reservaionPage.enterPrimaryGuestName(driver, primaryGuestFirstName, guestLastName);
			testSteps.add("Primary Room Guest First Name : " + primaryGuestFirstName);
			app_logs.info("Primary Room Guest First Name : " + primaryGuestFirstName);
			testSteps.add("Primary Room Guest Last Name : " + guestLastName);
			app_logs.info("Primary Room Guest Last Name : " + guestLastName);
		} catch (Exception e) {

			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Fill Primary Room info", scriptName, "TapeChart", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Fill Primary Room Info", scriptName, "TapeChart", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Associate Account
		try {
			app_logs.info("==========ASSOCIATE ACCOUNT==========");
			testSteps.add("==========ASSOCIATE ACCOUNT==========");
			reservaionPage.selectAccount(driver, getTestSteps, accountName, accounttype);
		} catch (Exception e) {

			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Associate Account", scriptName, "AssociateAccount", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Associate Account", scriptName, "AssociateAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// ADDITIONAL ROOM DETAILS
		try {
			app_logs.info("=========ADDITIONAL ROOM DETAILS==========");
			testSteps.add("==========ADDITIONAL ROOM DETAILS==========");
			reservaionPage.addAdditionalRoom(driver, testSteps, selectedRoomsList);
			reservaionPage.enterAdditionalGuestName(driver, additionalGuestFirstName, guestLastName);
			testSteps.add("Additional Room Guest First Name : " + additionalGuestFirstName);
			app_logs.info("Additional Room Guest First Name : " + additionalGuestFirstName);
			testSteps.add("Additional Room Guest Last Name : " + guestLastName);
			app_logs.info("Additional Room Guest Last Name : " + guestLastName);

		} catch (Exception e) {

			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to enter ADDITIONAL ROOM DETAILS", scriptName, "ADDITIONALROOM",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to enter ADDITIONAL ROOM DETAILS", scriptName, "ADDITIONALROOM",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// make payment
		try {

			reservaionPage.enter_PaymentDetails(driver, getTestSteps, paymentType, cardNumber, CardName, cardExpiry);

		} catch (Exception e) {

			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to enter payment details", scriptName, "Payment", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to enter payment details", scriptName, "Payment", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Add Notes
		String selectedRoomNumberForA = selectedRoomsList.get(0).split(":")[1].trim();
		String selectedRoomNumberForB = selectedRoomsList.get(1).split(":")[1].trim();

		try {
			firstRoom = juniorRoomClassAbbreviation.trim() + ": " + selectedRoomsList.get(0).split(":")[1].trim();
			secondRoom = secndRoomClassAbbreviation.trim() + ": " + selectedRoomsList.get(1).split(":")[1].trim();

			app_logs.info("==========ADDING NOTES FOR ROOM '" + selectedRoomsList.get(0) + "'==========");
			testSteps.add("==========ADDING NOTES FOR ROOM '" + selectedRoomsList.get(0) + "'==========");

			reservaionPage.AddNotes(driver, testSteps, selectedRoomsList.get(0), firstNoteType, primaryRoomNote1Subject,
					primaryRoomNoteDescription);
			reservaionPage.verifyNotesExist(driver, firstNoteType, firstRoom, primaryRoomNote1Subject,
					primaryRoomNoteDescription, Utility.getCurrentDate("MM/dd/ yy"), true);
			reservaionPage.AddNotes(driver, testSteps, selectedRoomsList.get(0), secondNoteType,
					primaryRoomNote2Subject, primaryRoomNoteDescription);
			reservaionPage.verifyNotesExist(driver, secondNoteType, firstRoom, primaryRoomNote2Subject,
					primaryRoomNoteDescription, Utility.getCurrentDate("MM/dd/ yy"), true);
			reservaionPage.AddNotes(driver, testSteps, selectedRoomsList.get(0), thirdNoteType, primaryRoomNote3Subject,
					primaryRoomNoteDescription);
			reservaionPage.verifyNotesExist(driver, thirdNoteType, firstRoom, primaryRoomNote3Subject,
					primaryRoomNoteDescription, Utility.getCurrentDate("MM/dd/ yy"), true);

			app_logs.info("==========ADDING NOTES FOR ROOM '" + selectedRoomsList.get(1) + "'==========");
			testSteps.add("==========ADDING NOTES FOR ROOM '" + selectedRoomsList.get(1) + "'==========");
			reservaionPage.AddNotes(driver, testSteps, selectedRoomsList.get(1), firstNoteType,
					additionalRoomNote1Subject, additionalRoomNoteDescription);
			reservaionPage.verifyNotesExist(driver, firstNoteType, secondRoom, additionalRoomNote1Subject,
					additionalRoomNoteDescription, Utility.getCurrentDate("MM/dd/ yy"), true);
			reservaionPage.AddNotes(driver, testSteps, selectedRoomsList.get(1), secondNoteType,
					additionalRoomNote2Subject, additionalRoomNoteDescription);
			reservaionPage.verifyNotesExist(driver, secondNoteType, secondRoom, additionalRoomNote2Subject,
					additionalRoomNoteDescription, Utility.getCurrentDate("MM/dd/ yy"), true);
			reservaionPage.AddNotes(driver, testSteps, selectedRoomsList.get(1), thirdNoteType,
					additionalRoomNote3Subject, additionalRoomNoteDescription);
			reservaionPage.verifyNotesExist(driver, thirdNoteType, secondRoom, additionalRoomNote3Subject,
					additionalRoomNoteDescription, Utility.getCurrentDate("MM/dd/ yy"), true);
			app_logs.info("==========VERIFYING ADDED NOTES==========");
			testSteps.add("==========VERIFYING ADDED NOTES==========");
			app_logs.info("Successfully verified created notes exist.");
			testSteps.add("Successfully verified created notes exist.");
		} catch (Exception e) {

			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Add Notes", scriptName, "AddNotes", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Add Notes", scriptName, "AddNotes", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Book Reservation
		try {
			reservaionPage.click_BookNow(driver, getTestSteps);
			reservationNumber = reservaionPage.get_ReservationConfirmationNumber(driver, getTestSteps);
			reservationStatus = reservaionPage.get_ReservationStatus(driver, getTestSteps);
			Assert.assertEquals(reservationStatus, "Reserved", "Failed: Reservation Status missmatched");
			reservaionPage.clickCloseReservationSavePopup(driver, testSteps);

		} catch (Exception e) {

			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Book Reservation", scriptName, "BookReservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Book Reservation", scriptName, "BookReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// GETTING RESERVATION DETAIL
		try {
			app_logs.info("==========GETTING RESERVATION DETAIL==========");
			testSteps.add("==========GETTING RESERVATION DETAIL==========");

			getTestSteps.clear();
			getTestSteps = folio.folioTab(driver);
			testSteps.addAll(getTestSteps);
			amountwithTax = reservation.getLineItemAmount(driver, roomCharge, true);
			testSteps.add("Room Charges Amount with Tax : " + amountwithTax);
			app_logs.info("Room Charges Amount with Tax : " + amountwithTax);

			amount = reservation.getLineItemAmount(driver, roomCharge, false);
			testSteps.add("Room Charges Amount without Tax : " + amount);
			app_logs.info("Room Charges Amount without Tax : " + amount);
			balance = reservaionPage.getBalance_Header(driver);
			testSteps.add("Balance : " + balance);
			app_logs.info("Balance : " + balance);
			tripTotal = reservaionPage.getTripTotal_Header(driver);
			testSteps.add("Trip Total : " + tripTotal);
			app_logs.info("Trip Total : " + tripTotal);
			roomChargeAmount = reservaionPage.get_RoomCharge(driver, getTestSteps);
			testSteps.add("Room Charge : " + roomChargeAmount);
			app_logs.info("Room Charge : " + roomChargeAmount);
			taxes = reservaionPage.get_Taxes(driver, getTestSteps);
			testSteps.add("Taxes : " + taxes);
			app_logs.info("Taxes : " + taxes);
			totalCharges = folio.getTotalCharges(driver);
			testSteps.add("Total Charge : " + totalCharges);
			app_logs.info("Total Charge : " + totalCharges);
			folioBalance = reservation.GetBalance_Folio(driver);
			testSteps.add("Folio Balance : " + folioBalance);
			app_logs.info("Folio Balance : " + folioBalance);
			reservaionPage.closeReservationTab(driver);
			testSteps.add("Close Reservation");
			app_logs.info("Close Reservation");
		} catch (Exception e) {

			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to GET RESERVATION DETAIL", scriptName, "GETDETAILS", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to GET RESERVATION DETAILs", scriptName, "GETDETAILS", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// SEARCH RESERVATION
		try {
			app_logs.info("==========SEARCH RESERVATION==========");
			testSteps.add("==========SEARCH RESERVATION==========");
			reservationSearch.basicSearchWithResNumber(driver, reservationNumber, false);

			testSteps.add("Reservation Successfully Searched : " + reservationNumber);
			app_logs.info("Reservation Successfully Searched : " + reservationNumber);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Search Reservation", scriptName, "SearchReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to search reservation", scriptName, "SearchReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			reservaionPage.clickOnPrintIcon(driver);
			reservaionPage.clickOnDetailedReservationList(driver);

			getTestSteps.clear();
			getTestSteps = reservaionPage.selectReportTyeAsPDF(driver);
			testSteps.addAll(getTestSteps);

			String fileName = Utility.download_status(driver);
			
			app_logs.info("fileName : " + fileName);
			
			String[] lines = Utility.checkPDF_Array(fileName);
		
			// reservaionPage.clickOnPrintIcon(driver);
			ArrayList<String> componentNames = new ArrayList<>();
			ArrayList<String> componentValue = new ArrayList<>();

			componentValue.add(primaryGuestFirstName);
			componentNames.add("Guest first name");

			componentValue.add(guestLastName);
			componentNames.add("Guest last name");

			componentValue.add(reservationStatus);
			componentNames.add("room status");

			componentValue.add(selectedRoomNumberForA);
			componentNames.add("room number");

			componentValue.add(juniorRoomClassAbbreviation);
			componentNames.add("room class abbreviation");

			componentValue.add(accountName);
			componentNames.add("account name");

			componentValue.add(marketSegment);
			componentNames.add("market segment");
			componentValue.add(referral);
			componentNames.add("referral");

			checkIn = ESTTimeZone.DateFormateForLineItem(0);
			checkIn = ESTTimeZone. parseDate(checkIn, "MM/dd/yyyy", "MMM d, yyyy");
			app_logs.info("checkIn: "+checkIn);
			componentValue.add(checkIn);
			componentNames.add("arrival date");
			
			checkOut = ESTTimeZone.DateFormateForLineItem(+1);
			checkOut = ESTTimeZone. parseDate(checkOut, "MM/dd/yyyy", "MMM d, yyyy");
			app_logs.info("checkOut: "+checkOut);
			componentValue.add(checkOut);
			componentNames.add("depart date");

			componentValue.add(adultsValue);
			componentNames.add("number of guest");

			componentValue.add(ratePlan);
			componentNames.add("rate plan");

			componentValue.add(ratePlan);
			componentNames.add("rate plan");

			String reserveOnDate = checkIn; 
			componentValue.add(reserveOnDate);
			componentNames.add("reserved on");

			componentValue.add(source);
			componentNames.add("source");

			componentValue.add(reservedBy);
			componentNames.add("reserved by");

			componentValue.add(totalCharges);
			componentNames.add("total charges");

			componentValue.add(amountwithTax);
			componentNames.add("total amount");

			componentValue.add(Payments);
			componentNames.add("payments");

			componentValue.add(folioBalance);
			componentNames.add("balance");

			componentValue.add(primaryRoomNote1Subject);
			componentNames.add("notes 1 subject");

			componentValue.add(primaryRoomNoteDescription);
			componentNames.add("notes 1 description");

			componentValue.add(primaryRoomNote2Subject);
			componentNames.add("notes 2 subject");

			componentValue.add(primaryRoomNoteDescription);
			componentNames.add("notes 2 description");

			componentValue.add(primaryRoomNote3Subject);
			componentNames.add("notes 3 subject");

			componentValue.add(primaryRoomNoteDescription);
			componentNames.add("notes 3 description");

			componentValue.add(paymentType);
			componentNames.add(" payment type");

			testSteps.add("============VERIFICATION OF ROOM " + firstRoom + " IN DOWNLOADED PDF REPORT");
			int count = 0;
			String  tempCheckIn = null;
			String  tempCheckOut = null;
			String  tempReserveOnDate = null;
			
			for (String line : lines) {

				System.out.println(count + " : " + line);
			}

			for (int i = 0; i < componentNames.size(); i++) {

				boolean isValueReflecting = false;
				for (String line : lines) {
					if (line.contains(componentValue.get(i))) {
						isValueReflecting = true;
						break;
					}
					else{
						
						if (componentNames.get(i).equals("arrival date"))
						{

								String[] str = checkIn.split(" " );
								tempCheckIn = str[0] + "  " + str[1] + " " + str[2];
								app_logs.info(" tempCheckIn : " + tempCheckIn);
								if (line.contains(tempCheckIn)) {
									isValueReflecting = true;
									break;
								}
						}
						
						if (componentNames.get(i).equals("depart date"))
						{
							
								String[] str = checkOut.split(" " );
								tempCheckOut = str[0] + "  " + str[1] + " " + str[2];
								app_logs.info(" tempCheckOut : " + tempCheckOut);
								if (line.contains(tempCheckOut)) {
									isValueReflecting = true;
									break;
								}
						}
						
						if (componentNames.get(i).equals("reserved on"))
						{
							
									String[] str = reserveOnDate.split(" " );
									tempReserveOnDate = str[0] + "  " + str[1] + " " + str[2];
									app_logs.info(" tempReserveOnDate : " + tempReserveOnDate);
									if (line.contains(tempReserveOnDate)) {
										isValueReflecting = true;
										break;
									}
						}
					}
				}
				
				assertEquals(isValueReflecting, true, "Expected: " + componentNames.get(i) + " not found ");
				testSteps.add("Verified " + componentNames.get(i));
				isValueReflecting = false;
				count = 0;
			}

			componentValue.clear();
			componentNames.clear();

			componentValue.add(additionalGuestFirstName);
			componentNames.add("Guest first name");

			componentValue.add(guestLastName);
			componentNames.add("Guest last name");

			componentValue.add(reservationStatus);
			componentNames.add("room status");

			componentValue.add(selectedRoomNumberForB);
			componentNames.add("room number");

			componentValue.add(secndRoomClassAbbreviation);
			componentNames.add("room class abbrevaition");

			componentValue.add(accountName);
			componentNames.add("account name");

			componentValue.add(marketSegment);
			componentNames.add("market segment");
			componentValue.add(referral);
			componentNames.add("referral");

			app_logs.info("checkIn: "+checkIn);
			componentValue.add(checkIn);
			componentNames.add("arrival date");

			componentValue.add(checkOut);
			componentNames.add("depart date");

			componentValue.add(adultsValue);
			componentNames.add("number of guest");

			componentValue.add(ratePlan);
			componentNames.add("rate plan");

			componentValue.add(ratePlan);
			componentNames.add("rate plan");

			componentValue.add(checkIn);
			componentNames.add("reserved on");

			componentValue.add(source);
			componentNames.add("source");

			componentValue.add(reservedBy);
			componentNames.add("reserved by");

			componentValue.add(totalCharges);
			componentNames.add("total charges");

			componentValue.add(amountwithTax);
			componentNames.add("total amount");

			componentValue.add(Payments);
			componentNames.add("payments");

			componentValue.add(folioBalance);
			componentNames.add("balance");

			componentValue.add(additionalRoomNote1Subject);
			componentNames.add("notes 1 subject");

			componentValue.add(additionalRoomNote2Subject);
			componentNames.add("notes 2 subject");

			componentValue.add(additionalRoomNote3Subject);
			componentNames.add("notes 3 subject");

			componentValue.add(additionalRoomNoteDescription);
			componentNames.add("notes 1 description");

			componentValue.add(additionalRoomNoteDescription);
			componentNames.add("notes 2 description");

			componentValue.add(additionalRoomNoteDescription);
			componentNames.add("notes 3 description");

			componentValue.add(paymentType);
			componentNames.add(" payment type");

			testSteps.add("============VERIFICATION OF ROOM " + secondRoom + " IN DOWNLOADED PDF REPORT");
			app_logs.info("lines: "+lines.toString());
			
			for (int i = 0; i < componentNames.size(); i++) {
				boolean isValueReflecting = false;
				for (String line : lines) {
					if (line.contains(componentValue.get(i))) {
						isValueReflecting = true;
						break;
					}
					else{
						
						if (componentNames.get(i).equals("arrival date"))
						{
							
								if (line.contains(tempCheckIn)) {
									isValueReflecting = true;
									break;
								}
						}
						
						if (componentNames.get(i).equals("depart date"))
						{
								if (line.contains(tempCheckOut)) {
									isValueReflecting = true;
									break;
								}
					
						}
						if (componentNames.get(i).equals("reserved on"))
						{
							
								if (line.contains(tempReserveOnDate)) {
									isValueReflecting = true;
									break;
								}
						}
					}
				}
				
				assertEquals(isValueReflecting, true, "Expected: " + componentNames.get(i) + " not found ");
				testSteps.add("Verified " + componentNames.get(i));
				isValueReflecting = false;
				count = 0;
			}

			reservaionPage.clickOnCloseReservationReports(driver);

		} catch (Exception e) {

			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to reservation details in pdf", scriptName, "PDFReading", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to reservation details in pdf", scriptName, "PDFReading", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// ADVANCE SEARCH RESERVATION
		try {
			app_logs.info("==========VERIFY RESERVATION IN ADVANCED SEARCH==========");
			testSteps.add("==========VERIFY RESERVATION IN ADVANCED SEARCH==========");

			getTestSteps.clear();
			getTestSteps = reservationSearch.clickOnAdvanced(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationSearch.guestInfo(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationSearch.reservationInfo(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationSearch.marketingInfo(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationSearch.advanceSearchWithReservationNumber(driver, reservationNumber);
			testSteps.addAll(getTestSteps);
			
			getTestSteps.clear();
			getTestSteps = reservationSearch.clickOnSearchButton(driver);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========VERIFY " + firstRoom + " IN ADVANCED SEARCH==========");
			testSteps.add("==========VERIFY " + firstRoom + " IN ADVANCED SEARCH==========");

			String guestName = primaryGuestFirstName + " " + guestLastName;
			System.out.println(guestName);
			String guestDetail = reservationSearch.getReservationDetails(driver, guestName, 1);
			assertEquals(guestDetail, accountName, "Failed: Account name is mismatching");
			testSteps.add("Verified account name");

			guestDetail = reservationSearch.getReservationDetails(driver, guestName, 2);
			assertEquals(guestDetail, reservationNumber, "Failed: Reservation number is mismatching");
			testSteps.add("Verified reservation number");

			guestDetail = reservationSearch.getReservationDetails(driver, guestName, 4);
			assertEquals(guestDetail, "MR", "Failed: Reservation type is mismatching");
			testSteps.add("Verified reservation type");

			guestDetail = reservationSearch.getReservationDetails(driver, guestName, 6);
			assertEquals(guestDetail, adultsValue, "Failed: Adult is mismatching");
			testSteps.add("Verified  adult");

			guestDetail = reservationSearch.getReservationDetails(driver, guestName, 8);
			assertEquals(guestDetail, reservationStatus, "Failed: reservation status is mismatching");
			testSteps.add("Verified  reservation status");

			String[] splitString = firstRoom.split(":");
			firstRoom = splitString[0].trim() + " : " + splitString[1].trim();
			guestDetail = reservationSearch.getReservationDetails(driver, guestName, 9);
			assertEquals(guestDetail, firstRoom, "Failed: room is mismatching");
			testSteps.add("Verified  room");

			guestDetail = reservationSearch.getReservationDetails(driver, guestName, 10);
			guestDetail = ESTTimeZone.parseDate(guestDetail, "MMM dd, yyyy", "MMM d, yyyy");
			assertEquals(guestDetail, checkIn, "Failed: Arrive date is mismatching");
			testSteps.add("Verified arrive date");

			guestDetail = reservationSearch.getReservationDetails(driver, guestName, 11);
			guestDetail = ESTTimeZone.parseDate(guestDetail, "MMM dd, yyyy", "MMM d, yyyy");
			assertEquals(guestDetail, checkOut, "Failed: Depart date is mismatching");
			testSteps.add("Verified depart date");

			app_logs.info("==========VERIFY " + secondRoom + " IN ADVANCED SEARCH==========");
			testSteps.add("==========VERIFY " + secondRoom + " IN ADVANCED SEARCH==========");

			guestName = additionalGuestFirstName + " " + guestLastName;
			guestDetail = reservationSearch.getReservationDetails(driver, guestName, 1);
			assertEquals(guestDetail, accountName, "Failed: Account name is mismatching");
			testSteps.add("Verified account name");

			guestDetail = reservationSearch.getReservationDetails(driver, guestName, 2);
			assertEquals(guestDetail, reservationNumber, "Failed: Reservation number is mismatching");
			testSteps.add("Verified reservation number");

			guestDetail = reservationSearch.getReservationDetails(driver, guestName, 4);
			assertEquals(guestDetail, "MR", "Failed: Reservation type is mismatching");
			testSteps.add("Verified reservation type");

			guestDetail = reservationSearch.getReservationDetails(driver, guestName, 6);
			assertEquals(guestDetail, adultsValue, "Failed: Adult is mismatching");
			testSteps.add("Verified  adult");

			guestDetail = reservationSearch.getReservationDetails(driver, guestName, 8);
			assertEquals(guestDetail, reservationStatus, "Failed: reservation status is mismatching");
			testSteps.add("Verified reservation status");

			splitString = secondRoom.split(":");
			secondRoom = splitString[0].trim() + " : " + splitString[1].trim();
			guestDetail = reservationSearch.getReservationDetails(driver, guestName, 9);
			assertEquals(guestDetail, secondRoom, "Failed: room is mismatching");
			testSteps.add("Verified  room");

			guestDetail = reservationSearch.getReservationDetails(driver, guestName, 10);
			guestDetail = ESTTimeZone.parseDate(guestDetail, "MMM dd, yyyy", "MMM d, yyyy");
			assertEquals(guestDetail, checkIn, "Failed: Arrive date is mismatching");
			testSteps.add("Verified arrive date");

			guestDetail = reservationSearch.getReservationDetails(driver, guestName, 11);
			guestDetail = ESTTimeZone.parseDate(guestDetail, "MMM dd, yyyy", "MMM d, yyyy");
			assertEquals(guestDetail, checkOut, "Failed: Depart date is mismatching");
			testSteps.add("Verified depart date");

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify reservation in advanced", scriptName, "SearchReservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify reservation in advanced", scriptName, "SearchReservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheet name provided
		return Utility.getData("VerifyDetailedReservationsListF", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {

		 driver.quit();
	}
}
