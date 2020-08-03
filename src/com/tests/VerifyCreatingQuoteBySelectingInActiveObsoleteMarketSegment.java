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
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.pageobjects.Account;
import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Groups;
import com.innroad.inncenter.pageobjects.GuestHistory;
import com.innroad.inncenter.pageobjects.ListManagement;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Reports;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class VerifyCreatingQuoteBySelectingInActiveObsoleteMarketSegment extends TestCore {

	private WebDriver driver = null;
	public static String test_name = "";
	public static String testDescription = "";
	public static String testCategory = "";
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> getTest_Steps = new ArrayList<>();
	ArrayList<String> TestName = new ArrayList<>();
	ArrayList<String> TestCategory = new ArrayList<>();
	ArrayList<String> TestDescription = new ArrayList<>();

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void verifyCreatingQuoteBySelectingInActiveObsoleteMarketSegment(String url, String ClientCode,
			String Username, String Password, String CheckInDate, String CheckOutDate, String Adults, String Children,
			String Rateplan, String PromoCode, String RoomClass, String IsAssign, String IsDepositOverride,
			String DepositOverrideAmount, String Salutation, String GuestFirstName, String GuestLastName,
			String FirstName, String LastName, String PhoneNumber, String AlternativePhone, String Email,
			String Account, String AccountType, String Address1, String Address2, String Address3, String City,
			String Country, String State, String PostalCode, String IsGuesProfile, String PaymentType,
			String CardNumber, String NameOnCard, String CardExpDate, String IsChangeInPayAmount,
			String ChangedAmountValue, String TravelAgent, String MarketSegment, String Referral, String Description,
			String NewItemNameActive, String NewItemNameInActive, String NewItemNameObsolete, String RatePlanStatus)
			throws InterruptedException, IOException {

		test_name = "VerifyCreatingQuoteBySelectingInActiveObsoleteMarketSegment";
		testDescription = "Verify creating Quote by selecting In Active/Obsolete Market Segment in Market Info section<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682488' target='_blank'><br>"
				+ "Click here to open TestRail: C682488</a>";
		testCategory = "QuoteCreation";
		String testName = test_name;

		TestName.add(testName);
		TestDescription.add(testDescription);
		TestCategory.add(testCategory);
		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		ReservationSearch reser_search = new ReservationSearch();
		CPReservationPage res = new CPReservationPage();
		Navigation nav = new Navigation();
		ListManagement listManagement = new ListManagement();
		Account account = new Account();
		GuestHistory guesthistory = new GuestHistory();
		Reports reports = new Reports();
		Groups groups = new Groups();
		Double depositAmount = 0.0;
		String reservationNumber = null;
		GuestLastName = GuestLastName + Utility.GenerateRandomNumber();

		try {

			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);

			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver = getDriver();
			login_CP(driver);
			test_steps.add("Logged into the application");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to login", "GS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);

			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to login", "GS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		NewItemNameActive = NewItemNameActive + Utility.GenerateRandomNumber();
		NewItemNameInActive = NewItemNameInActive + Utility.GenerateRandomNumber();
		NewItemNameObsolete = NewItemNameObsolete + Utility.GenerateRandomNumber();

		try {
			app_logs.info("======Market Segments Creation=======");
			test_steps.add("======Market Segments Creation=======");
			nav.Setup(driver);
			app_logs.info("Navigate to Setup");
			test_steps.add("Navigate to Setup");
			nav.ListManagemnet(driver);
			app_logs.info("Navigate to ListManagement");
			test_steps.add("Navigate to ListManagement");
			listManagement.SelectFilter(driver, "Market Segment", test_steps);
			listManagement.NewItemCreation(driver, NewItemNameActive, Description, test_steps);
			listManagement.SaveButtonClick(driver, test_steps);
			listManagement.NewItemCreation(driver, NewItemNameInActive, Description, test_steps);
			listManagement.SaveButtonClick(driver, test_steps);
			listManagement.SelectRatePlan_ChangeStatus(driver, NewItemNameInActive, RatePlanStatus, getTest_Steps);
			listManagement.NewItemCreation(driver, NewItemNameObsolete, Description, test_steps);
			listManagement.SaveButtonClick(driver, test_steps);
			RatePlanStatus = "Obsolete";
			getTest_Steps.clear();
			getTest_Steps = listManagement.SelectRatePlan_ChangeStatus(driver, NewItemNameObsolete, RatePlanStatus,
					getTest_Steps);
			test_steps.addAll(getTest_Steps);
		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Create MarketSegments ", testName, "MarketSegments", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Create MarketSegments", testName, "MarketSegments", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Create Reservation

		try {
			app_logs.info("======CP Reservation Creation=======");
			test_steps.add("======CP Reservation Creation=======");
			nav.Reservation_Backward_1(driver);
			res.click_NewReservation(driver, test_steps);
			
			/*res.CheckInDate(driver, 0);
			res.CheckOutDate(driver, 0);*/
			
			getTest_Steps.clear();
			getTest_Steps = res.checkInDate(driver, 0);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.checkOutDate(driver, +1);
			test_steps.addAll(getTest_Steps);
			
			res.enter_Adults(driver, test_steps, Adults);
			res.enter_Children(driver, test_steps, Children);
			res.select_Rateplan(driver, test_steps, Rateplan, PromoCode);
			res.clickOnFindRooms(driver, test_steps);
			res.selectRoom(driver, RoomClass, IsAssign);
			depositAmount = res.deposit(driver, test_steps, IsDepositOverride, DepositOverrideAmount);
			res.clickNext(driver, test_steps);
			res.enter_ContactName(driver, getTest_Steps, "", FirstName, LastName);
			res.enter_MailingAddress(driver, test_steps, Salutation, GuestFirstName, GuestLastName, PhoneNumber,
					AlternativePhone, Email, Account, AccountType, Address1, Address2, Address3, City, Country, State,
					PostalCode, IsGuesProfile);
			if ((Account.equalsIgnoreCase("") || Account.isEmpty())) {
				res.enter_PaymentDetails(driver, test_steps, PaymentType, CardNumber, NameOnCard, CardExpDate);
			}
			app_logs.info("======Verify Market Segements Active/InActive/Obsolete=======");
			test_steps.add("======Verify Market Segements Active/InActive/Obsolete=======");
			res.verifyMarketSegmentDropDown(driver, test_steps, NewItemNameActive, true);
			res.verifyMarketSegmentDropDown(driver, test_steps, NewItemNameInActive, false);
			res.verifyMarketSegmentDropDown(driver, test_steps, NewItemNameObsolete, false);
			Wait.wait2Second();
			res.enter_MarketSegmentDetails(driver, test_steps, TravelAgent, MarketSegment, Referral);
			res.click_Quote(driver, test_steps);
			reservationNumber = res.get_ReservationConfirmationNumber(driver, test_steps);
			res.verifyQuoteConfirmetionPopupWithStatusCodeAndConfirmationNumber(driver, reservationNumber, "Quote",
					test_steps);
			res.close_FirstOpenedReservation(driver, test_steps);

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Create Reservation ", testName, "CreateReservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Create Reservation", testName, "CreateReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		

		try {
			app_logs.info("======Verify Market Segements Active/InActive/Obsolete In Account=======");
			test_steps.add("======Verify Market Segements Active/InActive/Obsolete In Account=======");
			nav.Accounts(driver);
			app_logs.info("Navigate to Account");
			test_steps.add("Navigate to Account");
			account.ClickNewAccountbutton(driver, "Corporate/Member Accounts");
			app_logs.info("Click New Account Button");
			test_steps.add("Click New Account Button");
			account.verifyMarketSegmentDropDownAccount(driver, test_steps, MarketSegment, true);
			account.verifyMarketSegmentDropDownAccount(driver, test_steps, MarketSegment, false);
			account.verifyMarketSegmentDropDownAccount(driver, test_steps, MarketSegment, false);

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify MarketSegment Dropdown Account", testName, "Account", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify MarketSegment Dropdown Account", testName, "Account", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		
		try {
			app_logs.info("======Verify Market Segements Active/InActive/Obsolete In Groups=======");
			test_steps.add("======Verify Market Segements Active/InActive/Obsolete In Groups=======");
			nav.Reservation_Backward_1(driver);
			app_logs.info("Navigate back to Reservation");
			test_steps.add("Navigate back to Reservation");
			nav.Groups(driver);
			
			/*nav.navigateToReservations(driver);
			test_steps.add("Navigate Reservations");
			app_logs.info("Navigate Reservations");*/

			//driver.navigate().refresh();
			/*nav.secondaryGroupsManu(driver);
			test_steps.add("Navigate Groups");
			app_logs.info(" Navigate Groups");*/
			
			app_logs.info("Navigate to Groups");
			test_steps.add("Navigate to Groups");
			groups.click_NewAccount(driver, test, test_steps);
			groups.verifyMarketSegmentDropDownGroups(driver, test_steps, MarketSegment, true);
			groups.verifyMarketSegmentDropDownGroups(driver, test_steps, MarketSegment, false);
			groups.verifyMarketSegmentDropDownGroups(driver, test_steps, MarketSegment, false);

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify MarketSegment Dropdown Groups", testName, "Groups", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify MarketSegment Dropdown Groups", testName, "Groups", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			nav.Reservation_Backward_1(driver);
			
			app_logs.info("======Verify Market Segements Active/InActive/Obsolete In AdvanceSearch=======");
			test_steps.add("======Verify Market Segements Active/InActive/Obsolete In AdvanceSearch=======");
			reser_search.clickAdvanceReservationButton(driver);
			app_logs.info("Navigate to AdvanceSearch");
			test_steps.add("Navigate to AdvanceSearch");
			res.verifyMarketSegmentDropDownAdvanceSearch(driver, test_steps, MarketSegment, true);
			res.verifyMarketSegmentDropDownAdvanceSearch(driver, test_steps, MarketSegment, false);
			res.verifyMarketSegmentDropDownAdvanceSearch(driver, test_steps, MarketSegment, false);
			reser_search.clickCloseAdvanceSearchButton(driver);

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify MarketSegment Dropdown AdvanceSearch", testName,
						"AdvanceSearch", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify MarketSegment Dropdown AdvanceSearch", testName,
						"AdvanceSearch", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			app_logs.info("======Verify Market Segements Active/InActive/Obsolete In GuestHistory=======");
			test_steps.add("======Verify Market Segements Active/InActive/Obsolete In GuestHistory=======");
			//nav.Reservation_Backward_1(driver);
			app_logs.info("Navigate back to Reservation");
			test_steps.add("Navigate back to Reservation");
			nav.GuestHistory(driver);
			app_logs.info("Navigate to GuestHistory");
			test_steps.add("Navigate to GuestHistory");
			guesthistory.clickOnNewHistoryAccount(driver);
			app_logs.info("New Account Button Clicked");
			test_steps.add("New Account Button Clicked");
			account.verifyMarketSegmentDropDownAccount(driver, test_steps, MarketSegment, true);
			account.verifyMarketSegmentDropDownAccount(driver, test_steps, MarketSegment, false);
			account.verifyMarketSegmentDropDownAccount(driver, test_steps, MarketSegment, false);

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify MarketSegment Dropdown GuestHistory", testName,
						"GuestHistory", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify MarketSegment Dropdown GuestHistory", testName,
						"GuestHistory", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("======Verify Market Segements Active/InActive/Obsolete In LedgerBalance Report=======");
			test_steps.add("======Verify Market Segements Active/InActive/Obsolete In LedgerBalance Report=======");
			nav.Reservation_Backward_1(driver);
			app_logs.info("Navigate back to Reservation");
			test_steps.add("Navigate back to Reservation");
			nav.Reports(driver);
			nav.LedgerBalances(driver);
			app_logs.info("Navigate to LedgerBalance Report");
			test_steps.add("Navigate to LedgerBalance Report");
			reports.verifyMarketSegmentDropDownLedgerBalanceReport(driver, test_steps, MarketSegment, true);
			reports.verifyMarketSegmentDropDownLedgerBalanceReport(driver, test_steps, MarketSegment, false);
			reports.verifyMarketSegmentDropDownLedgerBalanceReport(driver, test_steps, MarketSegment, false);

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify MarketSegment Dropdown LedgerBalance Report", testName,
						"LedgerBalance Report", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify MarketSegment Dropdown LedgerBalance Report", testName,
						"LedgerBalance Report", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			app_logs.info("======Market Segments Delete=======");
			test_steps.add("======Market Segments Delete=======");
			nav.Setup(driver);
			app_logs.info("Navigate to Setup");
			test_steps.add("Navigate to Setup");
			nav.ListManagemnet(driver);
			app_logs.info("Navigate to ListManagement");
			test_steps.add("Navigate to ListManagement");
			listManagement.SelectFilter(driver, "Market Segment", test_steps);
			RatePlanStatus = "InActive";
			listManagement.SelectRatePlan_ChangeStatus(driver, NewItemNameActive, RatePlanStatus, getTest_Steps);
			listManagement.SaveButtonClick(driver, test_steps);
			listManagement.SelectStatus(driver, RatePlanStatus, test_steps);
			listManagement.DeleteCustomInActiveRatePlan(driver, NewItemNameActive, test_steps);
			listManagement.DeleteCustomInActiveRatePlan(driver, NewItemNameInActive, test_steps);
			RatePlanStatus = "Obsolete";
			listManagement.SelectStatus(driver, RatePlanStatus, test_steps);
			listManagement.DeleteCustomInActiveRatePlan(driver, NewItemNameObsolete, test_steps);
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Delete MarketSegments ", testName, "MarketSegments", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Delete MarketSegments", testName, "MarketSegments", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {

		// return test data from the sheet name provided

		return Utility.getData("VerifyCreatingQuoteInMarketSeg", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
