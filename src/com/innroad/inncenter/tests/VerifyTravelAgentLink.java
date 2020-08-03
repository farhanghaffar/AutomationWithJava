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
import com.innroad.inncenter.pageobjects.Account;
import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyTravelAgentLink extends TestCore {
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
	public void verifyTravelAgentLink(String url, String ClientCode, String Username, String Password,
			String PropertyName, String checkInDate, String checkOutDate, String Adults, String Children,
			String Rateplan, String PromoCode, String RoomClass, String IsAssign, String Salutation,
			String accountFirstName, String accountLastName, String phoneNumber, String alternativeNumber, String email,
			String address1, String address2, String address3, String city, String country, String state,
			String postalCode, String IsGuesProfile, String marketSegment, String referral, String accountName,
			String accountType, String nights, String roomClassAbb, String IsDepositOverride,
			String DepositOverrideAmount) throws InterruptedException, IOException {

		test_name = "Verify Travel Agent Link";
		test_description = "Create Travel Agent and verify Payments <br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682426' target='_blank'>"
				+ "Click here to open TestRail: C682426</a>";
		test_catagory = "Verify Travel Agent Link";
		String testName = test_name;

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		CPReservationPage res = new CPReservationPage();
		Navigation navigation = new Navigation();
		ReservationSearch searchReservation = new ReservationSearch();
		Account account = new Account();

		String accountNumber = null;
		String accountStatus = null;
		String reservationNumber = null;
		String roomNumber = null;
		String reservationStatus = null;
		Double depositAmount = 0.0;
		String AccountSince = Utility.getCurrentDate("MMM dd, YYYY");
		accountName = accountName + Utility.GenerateRandomNumber(999);

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
			app_logs.info("==========CREATE '" + accountType.toUpperCase() + "' ACCOUNT==========");
			testSteps.add("==========CREATE '" + accountType.toUpperCase() + "' ACCOUNT==========");
			navigation.Accounts(driver);
			testSteps.add("Navigate Accounts");
			app_logs.info("Navigate Accounts");
			account.ClickNewAccountbutton(driver, accountType);
			testSteps.add("Click New Account Button");
			app_logs.info("Click New Account Button");

			account.AccountDetails(driver, accountType, accountName);
			testSteps.add("Select Acount Type : " + accountType);
			app_logs.info("Select Acount Type : " + accountType);
			testSteps.add("Enter Acount Name : " + accountName);
			app_logs.info("Enter Acount Name : " + accountName);
			String number = Utility.GenerateRandomString15Digit();
			account.enterAccountNumber(driver, number);
			testSteps.add("Change Acount Number : " + number);
			app_logs.info("Change Acount Number : " + number);
			getTestSteps.clear();
			getTestSteps = account.AccountAttributes(driver, test, marketSegment, referral, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = account.Mailinginfo(driver, test, accountFirstName, accountLastName, phoneNumber,
					alternativeNumber, address1, address2, address3, email, city, state, postalCode, getTestSteps);
			getTestSteps.clear();
			getTestSteps = account.Billinginfo(driver, test, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = account.AccountSave(driver, test, accountName, getTestSteps);
			testSteps.addAll(getTestSteps);
			accountNumber = account.getAccountNum(driver);
			testSteps.add("Travel Agent Account Number '" + accountNumber + "'");
			app_logs.info("Travel Agent Account Number '" + accountNumber + "'");
			accountStatus = account.getAccountStatus(driver);
			account.closeAccountTab(driver);
			testSteps.add("Close Account");
			app_logs.info("Close Account");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Create Account", testName, "AccountCreation", driver);
			} else {

				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to  Create Account", testName, "AccountCreation", driver);
			} else {

				Assert.assertTrue(false);
			}
		}
		// Verify account
		try {
			app_logs.info("==========SEARCH '" + accountType + "' ACCOUNT==========");
			testSteps.add("==========SEARCH '" + accountType + "' ACCOUNT==========");
			getTestSteps.clear();
			getTestSteps = account.searchAccount(driver, test, accountType, accountName, accountNumber, getTestSteps);
			testSteps.addAll(getTestSteps);
			account.verifySearchedAccountDetails(driver, accountName, accountNumber, AccountSince, "0", "0",
					accountStatus, testSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Verify", testName, "Verification", driver);
			} else {

				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Verify", testName, "Verification", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========CREATE RESERVATION AND ATTACH '" + accountType + "' ACCOUNT==========");
			testSteps.add("==========CREATE RESERVATION AND ATTACH '" + accountType + "' ACCOUNT==========");
			navigation.Reservation_Backward_1(driver);
			res.click_NewReservation(driver, testSteps);
			getTestSteps.clear();
			getTestSteps = res.checkInDate(driver, 0);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = res.checkOutDate(driver, +1);
			testSteps.addAll(getTestSteps);

			res.enter_Adults(driver, testSteps, Adults);
			res.enter_Children(driver, testSteps, Children);
			res.select_Rateplan(driver, testSteps, Rateplan, PromoCode);
			res.clickOnFindRooms(driver, testSteps);
			res.selectRoom(driver, testSteps, RoomClass, IsAssign, "");
			depositAmount = res.deposit(driver, testSteps, IsDepositOverride, DepositOverrideAmount);
			res.clickNext(driver, testSteps);
			res.enterGuestName(driver, testSteps, Salutation, accountFirstName, accountLastName);
			res.attachTravelAgentMarketSegmentDetails(driver, testSteps, accountName);
			res.uncheck_CreateGuestProfile(driver, testSteps, IsGuesProfile);
			getTestSteps.clear();
			getTestSteps = res.verifyMarketingInformation(driver, marketSegment, referral);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = res.verifyMailingInformation(driver, address1, address2, address3, city, country, state,
					postalCode);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = res.verifyContactInformation(driver, Salutation, accountFirstName, accountLastName, email,
					phoneNumber, alternativeNumber);
			testSteps.addAll(getTestSteps);
			res.clickBookNow(driver, testSteps);
			testSteps.addAll(getTestSteps);
			reservationStatus = res.getReservationStatus(driver);
			reservationNumber = res.getReservationConfirmationNumber(driver, testSteps);
			getTestSteps.clear();
			getTestSteps = res.clickCloseReservationSavePopup(driver, getTestSteps);
			testSteps.addAll(getTestSteps);
			roomNumber = roomClassAbb + " : " + res.getRoomNo_ResDetail(driver);
			testSteps.add("Room number is:" + roomNumber);
			app_logs.info("Room number is:" + roomNumber);
			checkInDate = res.getArrivalDate_ResDetail(driver);
			checkOutDate = res.getDepartDate_ResDetail(driver);
			getTestSteps.clear();
			getTestSteps = res.verifyTravelAgentAttachedAndClick(driver, accountName, true);
			testSteps.addAll(getTestSteps);
			account.VerifyOpenAccountNameAndNumber(driver, accountName, accountNumber);
			app_logs.info("Travel Agent Account Opened & Verified:" + accountName);
			testSteps.add("Travel Agent Account Opened & Verified:" + accountName);
			account.click_reservationTab(driver, testSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Create Reservation From Account", testName, "ReservationCreation",
						driver);
			} else {

				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Create Reservation From Account", testName, "ReservationCreation",
						driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========VERIFY RESERVATION DETAILS FROM '" + accountType + "' ACCOUNT==========");
			testSteps.add("==========VERIFY RESERVATION DETAILS FROM '" + accountType + "' ACCOUNT==========");
			String getGuestName = searchReservation.getGuestNameAfterSearch(driver, 1);
			testSteps.add("Expected guest name: " + accountFirstName);
			testSteps.add("Found: " + getGuestName);
			assertTrue(getGuestName.contains(accountFirstName));
			testSteps.add("Verified guest name");

			String getReservationNumber = searchReservation.getReservationNumberAfterSearch(driver, 1);
			testSteps.add("Expected reservation number: " + reservationNumber);
			testSteps.add("Found : " + getReservationNumber);
			assertEquals(getReservationNumber, reservationNumber);
			testSteps.add("Verified reservation number");

			String getAdults = searchReservation.getAdultsAfterSearch(driver, 1);
			testSteps.add("Expected adults: " + Adults);
			testSteps.add("Found : " + getAdults);
			assertEquals(getAdults, Adults);
			testSteps.add("Verified adults");

			String getChildren = searchReservation.getChildrenAfterSearch(driver, 1);
			testSteps.add("Expected children: " + Children);
			testSteps.add("Found : " + getChildren);
			assertEquals(getChildren, Children);
			testSteps.add("Verified children");

			String getreservationStatus = searchReservation.getReservationStatusAfterSearch(driver, 1);
			testSteps.add("Expected status: " + reservationStatus);
			testSteps.add("Found : " + getreservationStatus);
			assertEquals(getreservationStatus, reservationStatus);
			testSteps.add("Verified status");

			String getRoom = searchReservation.getRoomAfterSearch(driver, 1);
			testSteps.add("Expected room: " + roomNumber);
			testSteps.add("Found : " + getRoom);
			assertEquals(getRoom, roomNumber);
			testSteps.add("Verified room");

			String getArrivalDate = searchReservation.getArrivalDateAfterSearch(driver, 1);
			//checkInDate =ESTTimeZone.DateFormateForCheckInCheckOut(0);
			checkInDate = ESTTimeZone.parseDate(ESTTimeZone.DateFormateForCheckInCheckOut(0), "dd/MM/yyyy", "MMM dd, yyyy");

			testSteps.add("Expected arrival date: " + checkInDate);
			testSteps.add("Found : " + getArrivalDate);
			assertEquals(getArrivalDate, checkInDate,"Failed to verify arrivla date");
			testSteps.add("Verified arrival date");

			String getDepartDate = searchReservation.getDepartDateAfterSearch(driver, 1);
			checkOutDate = ESTTimeZone.parseDate(ESTTimeZone.DateFormateForCheckInCheckOut(1), "dd/MM/yyyy", "MMM dd, yyyy");
			testSteps.add("Expected depart date: " + checkOutDate);
			testSteps.add("Found : " + getDepartDate);
			assertEquals(getDepartDate, checkOutDate);
			testSteps.add("Verified depart date");

			String getNights = searchReservation.getNightsAfterSearch(driver, 1);
			testSteps.add("Expected night: " + nights);
			testSteps.add("Found : " + getNights);
			assertEquals(getNights, nights);
			testSteps.add("Verified night");

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Verify", testName, "ReservationDetails", driver);
			} else {

				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Verify", testName, "ReservationDetails", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getData("VerifyTravelAgentLink", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		 driver.quit();
	}
}
