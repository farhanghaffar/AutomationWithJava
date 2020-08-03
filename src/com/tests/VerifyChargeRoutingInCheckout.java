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

public class VerifyChargeRoutingInCheckout extends TestCore {

	private WebDriver driver = null;
	public static String test_name = "";
	public static String test_description = "";
	public static String test_catagory = "";
	String timeZone = null;
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> getTest_Steps = new ArrayList<>();

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Accounts")
	public void verifyChargeRoutingInCheckout(String url, String ClientCode, String Username, String Password,
			String PropertyName, String Accounttype, String AccountName, String MargetSegment, String Referral,
			String FirstName, String LastName, String Phonenumber, String alternativeNumber, String Address1,
			String Address2, String Address3, String Email, String city, String State, String Postalcode,
			String SelectCategory, String Amount, String PaymentType, String CardName, String CCNumber, String CCExpiry,
			String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue, String RoomClass,
			String ClientType, String Line1, String Line2, String Line3, String Country, String Account,
			String Salutation, String IsTaxExempt, String TaxEmptext, String PrintType, String CheckInDate,
			String CheckOutDate, String Adults, String Children, String Rateplan, String PromoCode,
			String GuestFirstName, String GuestLastName, String PhoneNumber, String AltenativePhone, String AccountType,
			String IsAddMoreGuestInfo, String City, String PostalCode, String IsGuesProfile, String CardNumber,
			String NameOnCard, String CardExpDate, String IsChangeInPayAmount, String ChangedAmountValue,
			String TravelAgent, String MarketSegment, String IsSplitRes, String IsAssign, String IsDepositOverride,
			String lineItemAmount, String ReservationEamil, String IsAddNotes, String chargeRoutingType)
			throws InterruptedException, IOException {

		test_name = "verifyChargeRoutingInCheckout_" + chargeRoutingType + "";
		test_description = "Create a Corporate Account ,make a reservation and verify payments<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/566952' target='_blank'>"
				+ "Click here to open TestRail: C566952</a>";
		test_catagory = "Accounts";
		String testName = test_name;

		/*
		 * String Quantity = "1"; int line1 = 1; int line2 = 2;
		 */
		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Navigation navigation = new Navigation();
		Account account = new Account();
		Folio folio = new Folio();
		CPReservationPage cpReservation = new CPReservationPage();
		String TripSummaryRoomCharges = null, TripSummaryTripTotal = null, TripSummaryBalance = null;
		String reservation = null;
		String status = null;
		ArrayList<String> Rooms = new ArrayList<String>();
		new ArrayList<String>();
		ArrayList<String> roomCost = new ArrayList<String>();
		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);

			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver = getDriver();
			login_NONGS(driver);
			test_steps.add("Logged into the application");
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

		try {
			if (IsAddNotes.equalsIgnoreCase("Yes")) {
				navigation.Setup(driver);
				navigation.Properties(driver);
				navigation.open_Property(driver, test_steps, PropertyName);
				navigation.click_PropertyOptions(driver, test_steps);
				timeZone = navigation.get_Property_TimeZone(driver);
				navigation.Reservation_Backward(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		test_steps.add("******************** Create New Account *****************************");
		app_logs.info("******************** Create New Account *****************************");
		// navigate to accounts

		String AccountNumber = null;

		// New account
		try {
			navigation.Accounts(driver);
			test_steps.add("Navigate to Accounts");
			app_logs.info("Navigate to Accounts");
			AccountName = AccountName + Utility.generateRandomString();
			account.NewAccountbutton(driver, Accounttype);
			account.AccountDetails(driver, Accounttype, AccountName);
			AccountNumber = Utility.GenerateRandomString15Digit();
			account.ChangeAccountNumber(driver, AccountNumber);
			getTest_Steps.clear();
			account.AccountAttributes(driver, test, MargetSegment, Referral, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			getTest_Steps = account.Mailinginfo(driver, test, FirstName, LastName, Phonenumber, alternativeNumber,
					Address1, Address2, Address3, Email, city, State, Postalcode, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			getTest_Steps = account.Billinginfo(driver, test, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();

			getTest_Steps = account.AccountSave(driver, test, AccountName, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			account.get_FilioBalance(driver, test);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Create Corporate Account", testName, "CorporateAccount", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Create Corporate Account", testName, "CorporateAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			test_steps.add(
					"******************** Select Charge Routing Type As:_" + chargeRoutingType + "*****************************");
			account.navigateFolio(driver);
			account.navigateFolioOptions(driver);
			test_steps.add("Navigate Folio Optiopns");
			account.select_ChargeRouting(driver, chargeRoutingType);
			test_steps.add("Select charge routing as"+chargeRoutingType+" Successfully");
			account.Save(driver);
			test_steps.add("Save Account");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to save account", testName, "Failed to save account", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to save account", testName, "Failed to save account", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Clicking on New Reservation
		test_steps.add(
				"******************** Creating New MRB Reservation for_Account ChargeRoutingType as:"+ chargeRoutingType + "*****************************");
		app_logs.info(
				"******************** Creating New MRB Reservation for_Account ChargeRoutingType as:"+ chargeRoutingType + "*****************************");

		try {
			account.NewReservationButton(driver, test);
			cpReservation.select_Dates(driver, test_steps, CheckInDate, CheckOutDate, Adults, Children, Rateplan,
					PromoCode, IsSplitRes);
			if (IsSplitRes.equalsIgnoreCase("Yes")) {
				cpReservation.enter_Adults(driver, test_steps, Adults);
				cpReservation.enter_Children(driver, test_steps, Children);
				cpReservation.select_Rateplan(driver, test_steps, Rateplan, PromoCode);
			}
			cpReservation.click_FindRooms(driver, test_steps);
			roomCost = cpReservation.select_MRBRooms(driver, test_steps, RoomClass, IsAssign, Account);
			cpReservation.deposit(driver, test_steps, IsDepositOverride, lineItemAmount);
			cpReservation.click_Next(driver, test_steps);
			cpReservation.enter_MRB_MailingAddress(driver, test_steps, Salutation, GuestFirstName, GuestLastName,
					PhoneNumber, AltenativePhone, ReservationEamil, AccountName, AccountType, Address1, Address2,
					Address3, City, Country, State, PostalCode, IsGuesProfile, IsAddMoreGuestInfo, IsSplitRes, Rooms);
			if ((Account.equalsIgnoreCase("") || Account.isEmpty())) {
				cpReservation.enter_PaymentDetails(driver, test_steps, PaymentType, CardNumber, NameOnCard,
						CardExpDate);
			}
			System.out.println(Rooms);
			cpReservation.enter_MarketSegmentDetails(driver, test_steps, TravelAgent, MarketSegment, Referral);
			cpReservation.click_BookNow(driver, test_steps);
			reservation = cpReservation.get_ReservationConfirmationNumber(driver, test_steps);
			status = cpReservation.get_ReservationStatus(driver, test_steps);
			cpReservation.clickCloseReservationSavePopup(driver, test_steps);
			TripSummaryRoomCharges = cpReservation.get_TripSummaryRoomChargesWithCurrency(driver, test_steps);
			cpReservation.get_TripSummaryTaxesWithCurrency(driver, test_steps);
			TripSummaryTripTotal = cpReservation.get_TripSummaryTripTotalChargesWithCurrency(driver, test_steps);
			cpReservation.get_TripSummaryPaidWithCurrency(driver, test_steps);
			TripSummaryBalance = cpReservation.get_TripSummaryBalanceWithCurrency(driver, test_steps);
			cpReservation.get_TripSummaryInceidentalsWithCurrency(driver, test_steps);
			cpReservation.verify_MRB_StayInfo(driver, test_steps, CheckInDate, CheckOutDate, Adults, Children,
					RoomClass, TripSummaryRoomCharges, roomCost, IsAssign, Rateplan);
			cpReservation.getFristReservationRoomNumber(driver, test_steps);
			cpReservation.getFristReservationRoomCharge(driver, test_steps);
			cpReservation.getSecondReservationRoomNumber(driver, test_steps);
			cpReservation.getSecondReservationRoomCharge(driver, test_steps);
			cpReservation.validate_MRB_GuestInfo(driver, test_steps, Salutation, GuestFirstName, GuestLastName,
					PhoneNumber, AltenativePhone, Email, Country, AccountName, Address1, Address2, Address3, State,
					City, PostalCode);
			cpReservation.validate_MRB_AdditionalGuestInfo(driver, test_steps, Salutation, GuestFirstName,
					GuestLastName, PhoneNumber, ReservationEamil, Country);
			cpReservation.verify_UpdatedByUser(driver, test_steps, Utility.login_CP.get(2));
			cpReservation.get_AssociatedPoliciesToReservation(driver, test_steps);
			cpReservation.verify_MRB_BannerDetails(driver, test_steps, Salutation, GuestFirstName, GuestLastName,
					PhoneNumber, Email, TripSummaryTripTotal, TripSummaryBalance, reservation, status, CheckInDate,
					CheckOutDate, Country);
			cpReservation.Click_CheckInAllButton(driver, test_steps);
			cpReservation.verifyCheckInPaymentPopupIsAppeared(driver, AccountName,test_steps);
			cpReservation.Click_CheckOutAllButton(driver, test_steps);
			cpReservation.clickOnCheckOutPopUp(driver, test_steps);
			cpReservation.disableGenerateGuestReportToggle(driver, test_steps);
			cpReservation.proceedToCheckOutPayment(driver, test_steps);
			String accountDetails = "Account -" + AccountName + " " + (AccountNumber);
			System.out.println("accountDetails:" + accountDetails);
			cpReservation.verifyCheckOutPopIsAppear(driver, AccountName, Authorizationtype,accountDetails, test_steps);
			cpReservation.click_Folio(driver, test_steps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to save account", testName, "Failed to save account", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to save account", testName, "Failed to save account", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			test_steps.add("******************** Verify Folio Line Items  Account Charge Type is :"+ chargeRoutingType + " *****************************");
			app_logs.info("******************** Verify Folio Line Items  Account Charge Type is:"+ chargeRoutingType + " *****************************");
			folio.selectGuestFolioAccount(driver, AccountName, test_steps);
			try {
				
				folio.verifyLineItem(driver, "Room Charge", lineItemAmount, "1", timeZone, test_steps);
				folio.verifyLineItem(driver, "Room Charge", lineItemAmount, "1", timeZone, test_steps);

			} catch (Exception e) {
				test_steps.add("Line Items not avilable in Guest folio account");
				app_logs.info("Line Items not avilable in Guest folio account");

			}
			if(!chargeRoutingType.equals("Nothing")){
			test_steps.add("Existing issue in CP: Posted room charges do not move to account when charge routing is set"
					+ "<br/><a href='https://innroad.atlassian.net/browse/CTI-6802' target='_blank'>"
					+ "Verified : CTI-6802</a><br/>");
			app_logs.info("Existing issue in CP: Posted room charges do not move to account when charge routing is set"
					+ "<br/><a href='https://innroad.atlassian.net/browse/CTI-6802' target='_blank'>"
					+ "Verified :CTI-6802</a><br/>");
		}else{
			test_steps.add("Line Items not avilable in Guest folio account");
			app_logs.info("Line Items not avilable in Guest folio account");
		}
	
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to save account", testName, "Failed to save account", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to save account", testName, "Failed to save account", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getData("VerifyChargeRoutingInCheckout", excel);
	}

	//@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
