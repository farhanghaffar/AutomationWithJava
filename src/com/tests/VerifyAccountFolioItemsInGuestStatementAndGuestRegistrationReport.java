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
import com.innroad.inncenter.pageobjects.Account;
import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Folio;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyAccountFolioItemsInGuestStatementAndGuestRegistrationReport extends TestCore {

	// Automation-1493
	private WebDriver driver = null;
	ArrayList<String> testNames = new ArrayList<>();
	ArrayList<String> testDescriptions = new ArrayList<>();
	ArrayList<String> testCategories = new ArrayList<>();
	public static String testName = "VerifyAccountFolioItemsInGuestStatementAndGuestRegistrationReport";
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

	@Test(dataProvider = "getData", groups = { "Accounts", "Reports" })
	public void verifyAccountFolioItemsInGuestStatementAndGuestRegistrationReport(String marketSegment, String referral,
			String accountName, String accountFirstName, String accountLastName, String phonenumber,
			String alternativeNumber, String addressLineOne, String addressLineTwo, String addressLineThree,
			String email, String city, String state, String postalCode, String status, String spa,
			String corporateAccount, String spaAmount, String property, String adult,
			String children, String isAssign, String isChecked, String salutation, String paymentType,
			String cardNumber,  String cardExpDate, String reservationFirstName,
			String reservationLastName, String roomClassName, String statusReserved, String guestFolio,
			String roomChargeCategory, String roomChargesLabel, String totalChargesLabel,
			String taxAndServiceChargesLabel, String spaQuantity, String statusInHouse, String guestStatement,
			String guestRegistration

	) throws InterruptedException, IOException {

		testDescription = "Verify that only account folio items are printed in the Guest Statement and Guest Registration report when user prints the report from Folio tab of the reservation attached to a corporate account.<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682514' target='_blank'>"
				+ "Click here to open TestRail: C682514</a>"
				+ "<br>Verify system report with Multiple Folios in a Reservation<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682439' target='_blank'>"
				+ "Click here to open TestRail: C682439</a>"
				+ "<br>Verify that only guest folio items are printed in the Guest Statement and Guest Registration report when user prints the report from Folio tab of the reservation attached to a corporate account.<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682513' target='_blank'>"
				+ "Click here to open TestRail: C682513</a>";
		testCatagory = "Accounts";

		testNames.add(testName);
		testDescriptions.add(testDescription);
		testCategories.add(testCatagory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Navigation navigation = new Navigation();
		Account account = new Account();

		CPReservationPage cpReservation = new CPReservationPage();
		Folio folio = new Folio();

		String randomNumber = Utility.GenerateRandomNumber(999);

		//randomNumber = "56";

		accountName = accountName + randomNumber;
		accountLastName = accountLastName + randomNumber;
		reservationLastName = reservationLastName + randomNumber;
		String reservationName = reservationFirstName + " " + reservationLastName;
		String accountNumber = null;
		String roomChargeDescription = null;
		String reservationNumber = null;
		String taxAndServiceChargesAmount = null;
		String fileName = null;
		String lines[] = null;
		int i = 0;
		String reportType = null;
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
			cardExpDate  = Utility.getNextDate(365, "MM/yy", timeZone);
			app_logs.info("Card Expiry " + cardExpDate);
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

		// navigate to accounts
		try {
			app_logs.info("==========CREATE '" + corporateAccount.toUpperCase() + "' ACCOUNT==========");
			testSteps.add("==========CREATE '" + corporateAccount.toUpperCase() + "' ACCOUNT==========");
			navigation.Accounts(driver);
			testSteps.add("Navigate Accounts");
			app_logs.info("Navigate Accounts");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Navigate Accounts", testName, "NavigateAccounts", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Navigate Accounts", testName, "NavigateAccounts", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// Corporate New account
		try {

			account.ClickNewAccountbutton(driver, corporateAccount);
			testSteps.add("Click New Account Button");
			app_logs.info("Click New Account Button");

			account.AccountDetails(driver, corporateAccount, accountName);
			testSteps.add("Select Acount Type : " + corporateAccount);
			app_logs.info("Select Acount Type : " + corporateAccount);
			testSteps.add("Enter Acount Name : " + accountName);
			app_logs.info("Enter Acount Name : " + accountName);
			String defaultAccountNumber = account.getDefaultAccountNumber(driver);
			app_logs.info("Default Acount Number : " + defaultAccountNumber);
			String number = Utility.GenerateRandomString15Digit();
			account.enterAccountNumber(driver, number);
			testSteps.add("Change Acount Number : " + number);
			app_logs.info("Change Acount Number : " + number);
			getTestSteps.clear();
			getTestSteps = account.AccountAttributes(driver, test, marketSegment, referral, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = account.Mailinginfo(driver, test, accountFirstName, accountLastName, phonenumber,
					alternativeNumber, addressLineOne, addressLineTwo, addressLineThree, email, city, state, postalCode,
					getTestSteps);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Create New Corporate Account", testName, "NewCorporateAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Create New Corporate Account", testName, "NewCorporateAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Check Mailing Info CheckBox
		try {
			getTestSteps.clear();
			getTestSteps = account.Billinginfo(driver, test, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = account.selectPropertyFromAccount(driver, property);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Check Mailing Info CheckBox", testName, "CheckMailingInfoCheckBox",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Check Mailing Info CheckBox", testName, "CheckMailingInfoCheckBox",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Save Account
		try {
			getTestSteps.clear();
			getTestSteps = account.AccountSave(driver, test, accountName, getTestSteps);
			testSteps.addAll(getTestSteps);
			accountNumber = account.getAccountNum(driver);
			testSteps.add("Corporate Account Number '" + accountNumber + "'");
			app_logs.info("Corporate Account Number '" + accountNumber + "'");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Save  Account", testName, "SaveAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Save Account", testName, "SaveAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Verify account
		try {
			app_logs.info("==========SEARCH '" + corporateAccount.toUpperCase() + "' ACCOUNT==========");
			testSteps.add("==========SEARCH '" + corporateAccount.toUpperCase() + "' ACCOUNT==========");
			getTestSteps.clear();
			getTestSteps = account.searchAccount(driver, test, corporateAccount, accountName, accountNumber, status,
					getTestSteps);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify Corporate Account", testName, "VerifyCorporateAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify Corporate Account", testName, "VerifyCorporateAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		
		// Verify Rate in Tape Chart
		try {
			app_logs.info("==========CREATE NEW RESERVATION==========");
			testSteps.add("==========CREATE NEW RESERVATION==========");

			navigation.navigateToReservations(driver);
			testSteps.add("Navigate Reservations");
			app_logs.info("Navigate Reservations");
			cpReservation.click_NewReservation(driver, testSteps);

			String CheckInDate = Utility.getCurrentDate("MM/dd/yyyy", timeZone);
			String CheckoutDate = Utility.getNextDate(1, "MM/dd/yyyy", timeZone);
			cpReservation.EnterDate(driver, "start", CheckInDate);
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

			app_logs.info("==========ASSOCIATE ACCOUNT==========");
			testSteps.add("==========ASSOCIATE ACCOUNT==========");
			cpReservation.selectAccount(driver, testSteps, accountName, accountNumber, corporateAccount);

			getTestSteps.clear();
			cpReservation.enterPaymentDetails(driver, getTestSteps, paymentType, cardNumber, reservationName,
					cardExpDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = cpReservation.selectReferral(driver, referral);
			testSteps.addAll(getTestSteps);

			cpReservation.clickBookNow(driver, testSteps);
			reservationNumber = cpReservation.get_ReservationConfirmationNumber(driver, testSteps);
			app_logs.info("Reservation Number: " + reservationNumber);
			String reservationStatus = cpReservation.get_ReservationStatus(driver, testSteps);
			Assert.assertEquals(reservationStatus, statusReserved, "Failed: Reservation Status missmatched");
			cpReservation.clickCloseReservationSavePopup(driver, testSteps);
			roomNumber = cpReservation.get_RoomNumber(driver, testSteps, "yes");
			testSteps.add("=================== GET CHECKIN POLICY ======================");
			app_logs.info("=================== GET CHECKIN POLICY ======================");
			checkInPolicy = cpReservation.getCheckInPolicyName(driver);
			testSteps.add("CheckIn Policy '" + checkInPolicy + "'");
			app_logs.info("CheckIn Policy '" + checkInPolicy + "'");

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

		try {
			folio.FolioTab(driver);
			Utility.app_logs.info("Click Folio Tab");
			testSteps.add("Click Folio Tab");
			roomChargesWithTax = folio.getLineItemAmount(driver, roomChargeCategory, true);
			roomChargesWithTax = Utility.removeDollarBracketsAndSpaces(roomChargesWithTax);
			testSteps.add("Line item '" + roomChargeCategory + "' amount with Tax '" + roomChargesWithTax + "'");
			app_logs.info("Line item '" + roomChargeCategory + "' amount with Tax '" + roomChargesWithTax + "'");
			roomChargeDescription = folio.getLineItemDescription(driver, roomChargeCategory);
			testSteps.add("Line item '" + roomChargeCategory + "' description is '" + roomChargeDescription + "'");
			app_logs.info("Line item '" + roomChargeCategory + "' description is '" + roomChargeDescription + "'");
			roomChargesTax = folio.getTax(driver, roomChargeCategory);
			roomChargesTax = Utility.removeDollarBracketsAndSpaces(roomChargesTax);
			testSteps.add("Line item '" + roomChargeCategory + "' Tax '" + roomChargesTax + "'");
			app_logs.info("Line item '" + roomChargeCategory + "' Tax '" + roomChargesTax + "'");
			testSteps.add("========== VERIFY ACCOUNT FOLIO ==========");
			app_logs.info("========== VERIFY ACCOUNT FOLIO ==========");
			getTestSteps.clear();
			getTestSteps = folio.FolioExist(driver, guestFolio, accountName, true);
			testSteps.addAll(getTestSteps);
			testSteps.add("========== SELECT ACCOUNT FOLIO ==========");
			app_logs.info("========== SELECT ACCOUNT FOLIO ==========");
			getTestSteps.clear();
			getTestSteps = folio.changeFolioOption(driver, accountName);
			testSteps.addAll(getTestSteps);

			testSteps.add("========== VERIFY NO LINE ITEM EXIST ==========");
			app_logs.info("========== VERIFY NO LINE ITEM EXIST ==========");
			totalCharges = account.getBalance(driver, totalChargesLabel);
			totalCharges = Utility.removeDollarBracketsAndSpaces(totalCharges);
			testSteps.add(totalChargesLabel + " " + totalCharges);
			app_logs.info(totalChargesLabel + " " + totalCharges);
			Assert.assertEquals(totalCharges, "0.00", "Failed: Line Item exist in account Folio");
			testSteps.add("Verified account folio has not have any line item in it");
			app_logs.info("Verified account folio has not have any line item in it");
			testSteps.add(
					"========== ADDING NEW LINE ITEM CATEGORY '" + spa.toUpperCase() + "' IN ACCOUNT FOLIO ==========");
			app_logs.info(
					"========== ADDING NEW LINE ITEM CATEGORY '" + spa.toUpperCase() + "' IN ACCOUNT FOLIO ==========");
			getTestSteps.clear();
			getTestSteps = folio.LineItem(driver, spa, spaQuantity, spaAmount, getTestSteps);
			testSteps.addAll(getTestSteps);
			spaAmountWithTax = folio.getLineItemAmount(driver, spa, true);
			spaAmountWithTax = Utility.removeDollarBracketsAndSpaces(spaAmountWithTax);
			testSteps.add("Line item '" + spa + "' amount with Tax '" + spaAmountWithTax + "'");
			app_logs.info("Line item '" + spa + "' amount with Tax '" + spaAmountWithTax + "'");
			spaTax = folio.getTax(driver, spa);
			spaTax = Utility.removeDollarBracketsAndSpaces(spaTax);
			testSteps.add("Line item '" + spa + "' Tax '" + spaTax + "'");
			app_logs.info("Line item '" + spa + "' Tax '" + spaTax + "'");
			folio.verifyLineItemAdded(driver, spa, spaAmountWithTax, spa);
			testSteps.add("Verified Line item '" + spa + "' has been added successfully");
			app_logs.info("Verified Line item '" + spa + "' has been added successfully");
			getTestSteps.clear();
			getTestSteps = folio.ClickSaveFolioItemsButton(driver);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify Folio Options", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify Folio Options", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			testSteps.add("========== SELECT ACCOUNT FOLIO ==========");
			app_logs.info("========== SELECT ACCOUNT FOLIO ==========");
			getTestSteps.clear();
			getTestSteps = folio.changeFolioOption(driver, accountName);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = cpReservation.clickFolioOptions(driver);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = cpReservation.suppressAccountFolioPrintingCheckBox(driver, false);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = cpReservation.clickFolioDetails(driver);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to SUPPRESS ACCOUNT FOLIO PRINTING CheckBo", testName,
						"SuppressCheckBox", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to SUPPRESS ACCOUNT FOLIO PRINTING CheckBox", testName, "Reservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}


		try {
			 testSteps.add("========== SELECT <b>ACCOUNT FOLIO</b> ==========");
			 app_logs.info("========== SELECT <b>ACCOUNT FOLIO</b> ==========");
			getTestSteps.clear();
			getTestSteps = folio.changeFolioOption(driver, accountName);
			testSteps.addAll(getTestSteps);
			folio.verifyLineItemAdded(driver, spa, spaAmountWithTax, spa);
			testSteps.add("Verified  Line item '" + spa + "' is visible");
			app_logs.info("Verified  Line item '" + spa + "' is visible");
			roomCharges = account.getBalance(driver, roomChargesLabel);
			roomCharges = Utility.removeDollarBracketsAndSpaces(roomCharges);
			testSteps.add(roomChargesLabel + " " + roomCharges);
			app_logs.info(roomChargesLabel + " " + roomCharges);
			taxAndServiceChargesAmount = cpReservation.getBalance(driver, taxAndServiceChargesLabel);
			taxAndServiceChargesAmount = Utility.removeDollarBracketsAndSpaces(taxAndServiceChargesAmount);
			testSteps.add(taxAndServiceChargesLabel + " " + taxAndServiceChargesAmount);
			app_logs.info(taxAndServiceChargesLabel + " " + taxAndServiceChargesAmount);
			totalCharges = account.getBalance(driver, totalChargesLabel);
			totalCharges = Utility.removeDollarBracketsAndSpaces(totalCharges);
			testSteps.add(totalChargesLabel + " " + totalCharges);
			app_logs.info(totalChargesLabel + " " + totalCharges);
			testSteps.add("===================== DOWNLOAD <b>GUEST REGISTRATTION</b> REPORT =====================");
			app_logs.info("===================== DOWNLOAD <b>GUEST REGISTRATTION</b> REPORT =====================");
			getTestSteps.clear();
			getTestSteps = cpReservation.clickReservationReports(driver);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = cpReservation.clickGuestRegistrationOption(driver);
			testSteps.addAll(getTestSteps);
			cpReservation.waitTillNumberOfTabsExist(driver, 2);
			testSteps.add("New tab opened");
			app_logs.info("New tab opened");
			Utility.switchTab(driver, 1);
			testSteps.add("Switch tab");
			app_logs.info("Switch tab");
			reportType = guestRegistration;
			folioName = "Folio Name : " + accountName;
			if (cpReservation.reportDisplayed(driver, testSteps)) {
				cpReservation.downloadReport(driver);
				testSteps.add("Download Report");
				app_logs.info("Download Report");
				fileName = Utility.download_status(driver);
				lines = Utility.checkPDFArray(fileName);
				System.out.println("line size : " + lines.length);
				i = 0;
				for (String line : lines) {
					Utility.app_logs.info("line : " + i + " " + line);
					i++;
				}
				testSteps.add(
						"===================== VERIFY <b>ACCOUNT FOLIO</b> IN <b>GUEST REGISTRATTION</b> REPORT =====================");
				app_logs.info(
						"===================== VERIFY <b>ACCOUNT FOLIO</b> IN <b>GUEST REGISTRATTION</b> REPORT =====================");
				
				testSteps.add("Report Type '" + reportType + "'");
				
				cpReservation.verifyDataExistInReport(driver, lines, reportType, true);
				testSteps.add("Verified Report Type '" + reportType + "'");
				app_logs.info("Verified Report Type '" + reportType + "'");
				cpReservation.verifyDataExistInReport(driver, lines, folioName, true);
				testSteps.add("Verified '" + folioName + "'");
				app_logs.info("Verified '" + folioName + "'");
				lineItemData = Utility.getCurrentDate("MM/dd/yyyy", timeZone) + " " + spa + " " + spa + " "
						+ roomClassName + " : " + roomNumber + " $ " + spaTax + " $ " + spaAmount;
				app_logs.info(lineItemData);
				cpReservation.verifyDataExistInReport(driver, lines, lineItemData, true);
				testSteps.add("Verified Line Item '" + spa + "' exist");
				app_logs.info("Verified Line Item '" + spa + "' exist");
				cpReservation.verifyDataExistInReport(driver, lines, "Room Charges $" + roomCharges, true);
				testSteps.add("Verified Room Charges ' $" + roomCharges + "'");
				app_logs.info("Verified Room Chargs ' $" + roomCharges + "'");
				cpReservation.verifyDataExistInTwoLinesInReport(driver, lines, "Taxes & Service",
						"Taxes & Service Charges $" + spaTax, true);
				testSteps.add("Verified Taxes & Service Charges ' $" + spaTax + "'");
				app_logs.info("Verified Taxes & Service Charges ' $" + spaTax + "'");
				cpReservation.verifyDataExistInReport(driver, lines, "Total Charges $" + totalCharges, true);
				testSteps.add("Verified Total Charges ' $" + totalCharges + "'");
				app_logs.info("Verified Total Charges ' $" + totalCharges + "'");
				
				Utility.closeTabsExcept(driver, 1);
				testSteps.add("Close report's tab and switch to first tab");
				app_logs.info("Close report's tab and switch to first tab");
				
			} else {
				testSteps.add("Failed: Guest Registration Report is not displaying");
			}
			testSteps.add("===================== DOWNLOAD <b>GUEST STATEMENT</b> REPORT =====================");
			app_logs.info("===================== DOWNLOAD <b>GUEST STATEMENT</b> REPORT =====================");
			getTestSteps.clear();
			getTestSteps = cpReservation.clickReservationReports(driver);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = cpReservation.clickGuestStatementOption(driver);
			testSteps.addAll(getTestSteps);
			cpReservation.waitTillNumberOfTabsExist(driver, 2);
			testSteps.add("New tab opened");
			app_logs.info("New tab opened");
			Utility.switchTab(driver, 1);
			testSteps.add("Switch tab");
			app_logs.info("Switch tab");
			reportType = guestStatement;
			folioName = "Folio Name : " + accountName;
			if (cpReservation.reportDisplayed(driver, testSteps)) {
				cpReservation.downloadReport(driver);
				testSteps.add("Download Report");
				app_logs.info("Download Report");
				fileName = Utility.download_status(driver);
				lines = Utility.checkPDFArray(fileName);
				System.out.println("line size : " + lines.length);
				i = 0;
				for (String line : lines) {
					Utility.app_logs.info("line : " + i + " '" + line + "'");
					i++;
				}
				testSteps.add(
						"===================== VERIFY <b>ACCOUNT FOLIO</b> IN <b>GUEST STATEMENT</b> REPORT =====================");
				app_logs.info(
						"===================== VERIFY <b>ACCOUNT FOLIO</b> IN <b>GUEST STATEMENT</b> REPORT =====================");
				cpReservation.verifyDataExistInReport(driver, lines, reportType, true);
				testSteps.add("Verified Report Type '" + reportType + "'");
				app_logs.info("Verified Report Type '" + reportType + "'");
				String invoice = cpReservation.verifyDataExistInReport(driver, lines, "Invoice #:", false);
				testSteps.add("Verified '" + invoice + "' exist");
				app_logs.info("Verified '" + invoice + "' exist");
				lineItemData = Utility.getCurrentDate("MM/dd/yyyy", timeZone) + " " + spa + " " + spa + " "
						+ roomClassName + " : " + roomNumber + " $ " + spaTax + " $ " + spaAmount;
				app_logs.info(lineItemData);
				cpReservation.verifyDataExistInReport(driver, lines, lineItemData, true);
				testSteps.add("Verified Line Item '" + spa + "' exist");
				app_logs.info("Verified Line Item '" + spa + "' exist");
				cpReservation.verifyDataExistInReport(driver, lines, "Room Charges $" + roomCharges, true);
				testSteps.add("Verified Room Charges ' $" + roomCharges + "'");
				app_logs.info("Verified Room Chargs ' $" + roomCharges + "'");
				cpReservation.verifyDataExistInTwoLinesInReport(driver, lines, "Taxes & Service",
						"Taxes & Service Charges $" + spaTax, true);
				testSteps.add("Verified Taxes & Service Charges ' $" + spaTax + "'");
				app_logs.info("Verified Taxes & Service Charges ' $" + spaTax + "'");
				cpReservation.verifyDataExistInReport(driver, lines, "Total Charges $" + totalCharges, true);
				testSteps.add("Verified Total Charges ' $" + totalCharges + "'");
				app_logs.info("Verified Total Charges ' $" + totalCharges + "'");

				Utility.closeTabsExcept(driver, 1);
				testSteps.add("Close report's tab and switch to first tab");
				app_logs.info("Close report's tab and switch to first tab");

			} else {
				testSteps.add("Failed: Guest Registration Report is not displaying");
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify Guest Statement and guest Registration report", testName,
						"VerifyReport", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify Guest Statement and guest Registration report", testName,
						"VerifyReport", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			testSteps.add("========== SELECT <b>GUEST FOLIO</b> ==========");
			app_logs.info("========== SELECT <b>GUEST FOLIO</b> ==========");
			getTestSteps.clear();
			getTestSteps = folio.changeFolioOption(driver, guestFolio);
			testSteps.addAll(getTestSteps);
			folio.verifyLineItemAdded(driver, roomChargeCategory, roomChargesWithTax);
			roomCharges = account.getBalance(driver, roomChargesLabel);
			roomCharges = Utility.removeDollarBracketsAndSpaces(roomCharges);
			testSteps.add(roomChargesLabel + " " + roomCharges);
			app_logs.info(roomChargesLabel + " " + roomCharges);
			taxAndServiceChargesAmount = cpReservation.getBalance(driver, taxAndServiceChargesLabel);
			taxAndServiceChargesAmount = Utility.removeDollarBracketsAndSpaces(taxAndServiceChargesAmount);
			testSteps.add(taxAndServiceChargesLabel + " " + taxAndServiceChargesAmount);
			app_logs.info(taxAndServiceChargesLabel + " " + taxAndServiceChargesAmount);
			totalCharges = account.getBalance(driver, totalChargesLabel);
			totalCharges = Utility.removeDollarBracketsAndSpaces(totalCharges);
			testSteps.add(totalChargesLabel + " " + totalCharges);
			app_logs.info(totalChargesLabel + " " + totalCharges);
			testSteps.add("===================== DOWNLOAD<b> GUEST REGISTRATTION</b> REPORT =====================");
			app_logs.info("===================== DOWNLOAD <b>GUEST REGISTRATTION</b> REPORT =====================");
			getTestSteps.clear();
			getTestSteps = cpReservation.clickReservationReports(driver);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = cpReservation.clickGuestRegistrationOption(driver);
			testSteps.addAll(getTestSteps);
			cpReservation.waitTillNumberOfTabsExist(driver, 2);
			testSteps.add("New tab opened");
			app_logs.info("New tab opened");
			Utility.switchTab(driver, 1);
			testSteps.add("Switch tab");
			app_logs.info("Switch tab");
			reportType = guestRegistration;
			folioName = "Folio Name : Guest Folio";
			lineItemData = Utility.getCurrentDate("MM/dd/yyyy", timeZone) + " " + roomChargeCategory + " "
					+ roomChargeDescription + " " + roomClassName + " : " + roomNumber + " $ " + roomChargesTax + " $ "
					+ roomCharges;
			app_logs.info(lineItemData);

			if (cpReservation.reportDisplayed(driver, testSteps)) {
				cpReservation.downloadReport(driver);
				testSteps.add("Download Report");
				app_logs.info("Download Report");
				fileName = Utility.download_status(driver);
				lines = Utility.checkPDFArray(fileName);
				System.out.println("line size : " + lines.length);
				i = 0;
				for (String line : lines) {
					Utility.app_logs.info("line : " + i + " " + line);
					i++;
				}
				testSteps.add(
						"===================== VERIFY <b>GUEST FOLIO</b> IN<b> GUEST REGISTRATTION</b> REPORT =====================");
				app_logs.info(
						"===================== VERIFY <b>GUEST FOLIO</b> IN <b>GUEST REGISTRATTION</b> REPORT =====================");
				cpReservation.verifyDataExistInReport(driver, lines, reportType, true);
				testSteps.add("Verified Report Type '" + reportType + "'");
				app_logs.info("Verified Report Type '" + reportType + "'");
				cpReservation.verifyDataExistInReport(driver, lines, folioName, true);
				testSteps.add("Verified '" + folioName + "'");
				app_logs.info("Verified '" + folioName + "'");

				cpReservation.verifyDataExistInReport(driver, lines, lineItemData, true);
				testSteps.add("Verified Line Item '" + roomChargeCategory + "' exist");
				app_logs.info("Verified Line Item '" + roomChargeCategory + "' exist");
				cpReservation.verifyDataExistInReport(driver, lines, "Room Charges $" + roomCharges, true);
				testSteps.add("Verified Room Charges ' $" + roomCharges + "'");
				app_logs.info("Verified Room Chargs ' $" + roomCharges + "'");
				cpReservation.verifyDataExistInTwoLinesInReport(driver, lines, "Taxes & Service",
						"Taxes & Service Charges $" + roomChargesTax, true);
				testSteps.add("Verified Taxes & Service Charges ' $" + roomChargesTax + "'");
				app_logs.info("Verified Taxes & Service Charges ' $" + roomChargesTax + "'");
				cpReservation.verifyDataExistInReport(driver, lines, "Total Charges $" + totalCharges, true);
				testSteps.add("Verified Total Charges ' $" + totalCharges + "'");
				app_logs.info("Verified Total Charges ' $" + totalCharges + "'");

				Utility.closeTabsExcept(driver, 1);
				testSteps.add("Close report's tab and switch to first tab");
				app_logs.info("Close report's tab and switch to first tab");
			} else {
				testSteps.add("Failed: Guest Registration Report is not displaying");
			}
			testSteps.add("===================== DOWNLOAD <b>GUEST STATEMENT</b> REPORT =====================");
			app_logs.info("===================== DOWNLOAD <b>GUEST STATEMENT</b> REPORT =====================");
			getTestSteps.clear();
			getTestSteps = cpReservation.clickReservationReports(driver);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = cpReservation.clickGuestStatementOption(driver);
			testSteps.addAll(getTestSteps);
			cpReservation.waitTillNumberOfTabsExist(driver, 2);
			testSteps.add("New tab opened");
			app_logs.info("New tab opened");
			Utility.switchTab(driver, 1);
			testSteps.add("Switch tab");
			app_logs.info("Switch tab");
			reportType = guestStatement;
			folioName = "Folio Name : " + accountName;
			if (cpReservation.reportDisplayed(driver, testSteps)) {
				cpReservation.downloadReport(driver);
				testSteps.add("Download Report");
				app_logs.info("Download Report");
				fileName = Utility.download_status(driver);
				lines = Utility.checkPDFArray(fileName);
				System.out.println("line size : " + lines.length);
				i = 0;
				for (String line : lines) {
					Utility.app_logs.info("line : " + i + " '" + line + "'");
					i++;
				}
				testSteps.add(
						"===================== VERIFY <b>GUEST FOLIO</b> IN <b>GUEST STATEMENT</b> REPORT =====================");
				app_logs.info(
						"===================== VERIFY <b>GUEST FOLIO</b> IN <b>GUEST STATEMENT</b> REPORT =====================");
				cpReservation.verifyDataExistInReport(driver, lines, reportType, true);
				testSteps.add("Verified Report Type '" + reportType + "'");
				app_logs.info("Verified Report Type '" + reportType + "'");
				String invoice = cpReservation.verifyDataExistInReport(driver, lines, "Invoice #:", false);
				testSteps.add("Verified '" + invoice + "' exist");
				app_logs.info("Verified '" + invoice + "' exist");
				cpReservation.verifyDataExistInReport(driver, lines, lineItemData, true);
				testSteps.add("Verified Line Item '" + roomChargeCategory + "' exist");
				app_logs.info("Verified Line Item '" + roomChargeCategory + "' exist");
				cpReservation.verifyDataExistInReport(driver, lines, "Room Charges $" + roomCharges, true);
				testSteps.add("Verified Room Charges ' $" + roomCharges + "'");
				app_logs.info("Verified Room Chargs ' $" + roomCharges + "'");
				cpReservation.verifyDataExistInTwoLinesInReport(driver, lines, "Taxes & Service",
						"Taxes & Service Charges $" + roomChargesTax, true);
				testSteps.add("Verified Taxes & Service Charges ' $" + roomChargesTax + "'");
				app_logs.info("Verified Taxes & Service Charges ' $" + roomChargesTax + "'");
				cpReservation.verifyDataExistInReport(driver, lines, "Total Charges $" + totalCharges, true);
				testSteps.add("Verified Total Charges ' $" + totalCharges + "'");
				app_logs.info("Verified Total Charges ' $" + totalCharges + "'");

				Utility.closeTabsExcept(driver, 1);
				testSteps.add("Close report's tab and switch to first tab");
				app_logs.info("Close report's tab and switch to first tab");

			} else {
				testSteps.add("Failed: Guest Registration Report is not displaying");
			}
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify Guest Statement and guest Registration report", testName,
						"VerifyReport", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify Guest Statement and guest Registration report", testName,
						"VerifyReport", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			testSteps.add("=================== CHECKIN RESERVATION ======================");
			app_logs.info("=================== CHECKIN RESERVATION ======================");
			cpReservation.Click_CheckInButton(driver, testSteps);
			cpReservation.generatGuestReportToggle(driver, testSteps, "Yes");
			
			try {
				if (checkInPolicy.equals("No Policy")) {
					getTestSteps.clear();
					getTestSteps = cpReservation.clickOnConfirmCheckInButton(driver);
					testSteps.addAll(getTestSteps);
					getTestSteps.clear();
					getTestSteps = cpReservation.dirtyRoomCheckInPopUpVerification(driver, getTestSteps);
					testSteps.addAll(getTestSteps);
				} else {
					cpReservation.clickOnProceedToCheckInPaymentButton(driver, testSteps);
					cpReservation.checkLogasExternalPayment(driver, testSteps, true);
					cpReservation.clickLogORPayAuthorizedButton(driver, testSteps);
					cpReservation.checkInPaymentSuccessPopupClose(driver, testSteps);

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			cpReservation.verifySpinerLoading(driver);
			cpReservation.waitTillNumberOfTabsExist(driver, 2);
			testSteps.add("New tab opened");
			app_logs.info("New tab opened");
			Utility.switchTab(driver, 1);
			testSteps.add("Switch tab");
			app_logs.info("Switch tab");
			reportType = "Guest Statement";
			folioName = "Folio Name : " + accountName;
			Assert.assertTrue(cpReservation.reportDisplayed(driver, testSteps), "Failed: Report not displayed");
			Utility.closeTabsExcept(driver, 1);
			testSteps.add("Close report's tab and switch to first tab");
			app_logs.info("Close report's tab and switch to first tab");
			testSteps.add("===================== VERIFY RESERVATION STATUS =====================");
			cpReservation.Verify_ReservationStatus_Status(driver, testSteps, statusInHouse);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed", testName, "ExpediaReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed", testName, "ExpediaReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			testSteps.add("========== SELECT ACCOUNT FOLIO ==========");
			app_logs.info("========== SELECT ACCOUNT FOLIO ==========");
			folio.FolioTab(driver);
			Utility.app_logs.info("Click Folio Tab");
			testSteps.add("Click Folio Tab");
			getTestSteps.clear();
			getTestSteps = folio.changeFolioOption(driver, accountName);
			testSteps.addAll(getTestSteps);
			cpReservation.verify_GuestReportLabelsValidations(driver, testSteps);
			roomCharges = account.getBalance(driver, roomChargesLabel);
			roomCharges = Utility.removeDollarBracketsAndSpaces(roomCharges);
			testSteps.add(roomChargesLabel + " " + roomCharges);
			app_logs.info(roomChargesLabel + " " + roomCharges);
			taxAndServiceChargesAmount = cpReservation.getBalance(driver, taxAndServiceChargesLabel);
			taxAndServiceChargesAmount = Utility.removeDollarBracketsAndSpaces(taxAndServiceChargesAmount);
			testSteps.add(taxAndServiceChargesLabel + " " + taxAndServiceChargesAmount);
			app_logs.info(taxAndServiceChargesLabel + " " + taxAndServiceChargesAmount);
			totalCharges = account.getBalance(driver, totalChargesLabel);
			totalCharges = Utility.removeDollarBracketsAndSpaces(totalCharges);
			testSteps.add(totalChargesLabel + " " + totalCharges);
			app_logs.info(totalChargesLabel + " " + totalCharges);
			testSteps.add(
					"===================== DOWNLOAD <b>GUEST REGISTRATTION WITH TAX BREAKDOWN</b> REPORT =====================");
			app_logs.info(
					"===================== DOWNLOAD <b>GUEST REGISTRATTION WITH TAX BREAKDOWN</b> REPORT =====================");
			getTestSteps.clear();
			getTestSteps = cpReservation.clickGuestRegistartionTaxBreakdown(driver);
			testSteps.addAll(getTestSteps);
			cpReservation.waitTillNumberOfTabsExist(driver, 2);
			testSteps.add("New tab opened");
			app_logs.info("New tab opened");
			Utility.switchTab(driver, 1);
			testSteps.add("Switch tab");
			app_logs.info("Switch tab");
			reportType = guestRegistration;
			folioName = "Folio Name : " + accountName;
			if (cpReservation.reportDisplayed(driver, testSteps)) {
				cpReservation.downloadReport(driver);
				testSteps.add("Download Report");
				app_logs.info("Download Report");
				fileName = Utility.download_status(driver);
				lines = Utility.checkPDFArray(fileName);
				System.out.println("line size : " + lines.length);
				i = 0;
				for (String line : lines) {
					Utility.app_logs.info("line : " + i + " " + line);
					i++;
				}
				testSteps.add(
						"===================== VERIFY <b>ACCOUNT FOLIO</b> IN <b>GUEST REGISTRATTION WITH TAX BREAKDOWN</b> REPORT =====================");
				app_logs.info(
						"===================== VERIFY <b>ACCOUNT FOLIO</b> IN <b>GUEST REGISTRATTION WITH TAX BREAKDOWN</b> REPORT =====================");
				cpReservation.verifyDataExistInReport(driver, lines, reportType, true);
				testSteps.add("Verified Report Type '" + reportType + "'");
				app_logs.info("Verified Report Type '" + reportType + "'");
				cpReservation.verifyDataExistInReport(driver, lines, folioName, true);
				testSteps.add("Verified '" + folioName + "'");
				app_logs.info("Verified '" + folioName + "'");
				lineItemData = Utility.getCurrentDate("MM/dd/yyyy", timeZone) + " " + spa + " " + spa + " "
						+ roomClassName + " : " + roomNumber + " $ " + spaTax + " $ " + spaAmount;
				app_logs.info(lineItemData);
				cpReservation.verifyDataExistInReport(driver, lines, lineItemData, true);
				testSteps.add("Verified Line Item '" + spa + "' exist");
				app_logs.info("Verified Line Item '" + spa + "' exist");
				cpReservation.verifyDataExistInReport(driver, lines, "Room Charges $" + roomCharges, true);
				testSteps.add("Verified Room Charges ' $" + roomCharges + "'");
				app_logs.info("Verified Room Chargs ' $" + roomCharges + "'");
				cpReservation.verifyDataExistInTwoLinesInReport(driver, lines, "Taxes & Service",
						"Taxes & Service Charges $" + spaTax, true);
				testSteps.add("Verified Taxes & Service Charges ' $" + spaTax + "'");
				app_logs.info("Verified Taxes & Service Charges ' $" + spaTax + "'");
				cpReservation.verifyDataExistInReport(driver, lines, "Total Charges $" + totalCharges, true);
				testSteps.add("Verified Total Charges ' $" + totalCharges + "'");
				app_logs.info("Verified Total Charges ' $" + totalCharges + "'");
				//
				Utility.closeTabsExcept(driver, 1);
				testSteps.add("Close report's tab and switch to first tab");
				app_logs.info("Close report's tab and switch to first tab");
				//
			} else {
				testSteps.add("Failed: Guest Registration Report is not displaying");
			}
			testSteps.add(
					"===================== DOWNLOAD <b>GUEST STATEMENT WITH TAX BREAKDOWN</b> REPORT =====================");
			app_logs.info(
					"===================== DOWNLOAD <b>GUEST STATEMENT WITH TAX BREAKDOWN</b> REPORT =====================");
			getTestSteps.clear();
			getTestSteps = cpReservation.clickGuestStatementWithTaxBreakdown(driver);
			testSteps.addAll(getTestSteps);
			cpReservation.waitTillNumberOfTabsExist(driver, 2);
			testSteps.add("New tab opened");
			app_logs.info("New tab opened");
			Utility.switchTab(driver, 1);
			testSteps.add("Switch tab");
			app_logs.info("Switch tab");
			reportType = guestStatement;
			folioName = "Folio Name : " + accountName;
			if (cpReservation.reportDisplayed(driver, testSteps)) {
				cpReservation.downloadReport(driver);
				testSteps.add("Download Report");
				app_logs.info("Download Report");
				fileName = Utility.download_status(driver);
				lines = Utility.checkPDFArray(fileName);
				System.out.println("line size : " + lines.length);
				i = 0;
				for (String line : lines) {
					Utility.app_logs.info("line : " + i + " '" + line + "'");
					i++;
				}
				testSteps.add(
						"===================== VERIFY <b>ACCOUNT FOLIO</b> IN <b>GUEST STATEMENT WITH TAX BREAKDOWN</b> REPORT =====================");
				app_logs.info(
						"===================== VERIFY <b>ACCOUNT FOLIO</b> IN <b>GUEST STATEMENT WITH TAX BREAKDOWN</b> REPORT =====================");
				cpReservation.verifyDataExistInReport(driver, lines, reportType, true);
				testSteps.add("Verified Report Type '" + reportType + "'");
				app_logs.info("Verified Report Type '" + reportType + "'");
				String invoice = cpReservation.verifyDataExistInReport(driver, lines, "Invoice #:", false);
				testSteps.add("Verified '" + invoice + "' exist");
				app_logs.info("Verified '" + invoice + "' exist");
				lineItemData = Utility.getCurrentDate("MM/dd/yyyy", timeZone) + " " + spa + " " + spa + " "
						+ roomClassName + " : " + roomNumber + " $ " + spaTax + " $ " + spaAmount;
				app_logs.info(lineItemData);
				cpReservation.verifyDataExistInReport(driver, lines, lineItemData, true);
				testSteps.add("Verified Line Item '" + spa + "' exist");
				app_logs.info("Verified Line Item '" + spa + "' exist");
				cpReservation.verifyDataExistInReport(driver, lines, "Room Charges $" + roomCharges, true);
				testSteps.add("Verified Room Charges ' $" + roomCharges + "'");
				app_logs.info("Verified Room Chargs ' $" + roomCharges + "'");
				cpReservation.verifyDataExistInTwoLinesInReport(driver, lines, "Taxes & Service",
						"Taxes & Service Charges $" + spaTax, true);
				testSteps.add("Verified Taxes & Service Charges ' $" + spaTax + "'");
				app_logs.info("Verified Taxes & Service Charges ' $" + spaTax + "'");
				cpReservation.verifyDataExistInReport(driver, lines, "Total Charges $" + totalCharges, true);
				testSteps.add("Verified Total Charges ' $" + totalCharges + "'");
				app_logs.info("Verified Total Charges ' $" + totalCharges + "'");

				Utility.closeTabsExcept(driver, 1);
				testSteps.add("Close report's tab and switch to first tab");
				app_logs.info("Close report's tab and switch to first tab");

			}
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to SUPPRESS ACCOUNT FOLIO PRINTING CheckBo", testName,
						"SuppressCheckBox", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to SUPPRESS ACCOUNT FOLIO PRINTING CheckBox", testName, "Reservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

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
		return Utility.getData("VerifyFolioItemsInGuestReport", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		//driver.quit();

	}

}
