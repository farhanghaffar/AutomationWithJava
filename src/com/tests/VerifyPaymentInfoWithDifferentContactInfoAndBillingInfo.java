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
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyPaymentInfoWithDifferentContactInfoAndBillingInfo extends TestCore{
	private WebDriver driver = null;
	public static String test_name = "";
	public static String test_description = "";
	public static String test_catagory = "";
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> getTest_Steps = new ArrayList<>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	String date = null;

	@BeforeTest(alwaysRun=true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void verifyPaymentInfoWithDifferentContactInfoAndBillingInfo(String url, String ClientCode, String Username, String Password,String PropertyName,String CheckInDate,String CheckOutDate,String Adults,String Children,String Rateplan,String PromoCode,
			String RoomClass,String IsAssign,String IsDepositOverride,String DepositOverrideAmount,String Salutation,String GuestFirstName,String GuestLastName,String PhoneNumber,String AltenativePhone,String Email,String Account,String AccountType,
			String Address1,String Address2,String Address3,String City,String Country,String State,String PostalCode,String IsGuesProfile,String PaymentType,String CardNumber,String NameOnCard,String CardExpDate,
			String TakePaymentType,String IsChangeInPayAmount,String ChangedAmountValue,
			String IsSetAsMainPaymentMethod,String AddPaymentNotes,String TravelAgent,String MarketSegment,String Referral,
			String IsBillingSameAsMailingAddress,String BillingAddress1,String BillingAddress2,String BillingAddress3,String BillingCity,String BillingCountry,String BillingState,String BillingPostalCode,
			String BillingSalutation,String BillingGuestFirstName,String BillingGuestLastName
			) throws InterruptedException, IOException {

		test_name = "VerifyPaymentInfoWithDifferentContactInfoAndBillingInfo";
		test_description = "CP Reservation Creation. Verification of Payment Info With Different Contact Info And Billing Info<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682427' target='_blank'>"
				+ "Click here to open TestRail: C682427</a>";
		test_catagory = "CPReservationCardPayments";
		String testName = test_name+"_"+PaymentType;

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		CPReservationPage CPReservationPage = new CPReservationPage();
		String RandomNumber = Utility.GenerateRandomNumber();
		GuestLastName = GuestLastName + RandomNumber;
		BillingGuestLastName = BillingGuestLastName + RandomNumber;
		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);
			} else {
				Utility.reTry.replace(testName, 1);
			}
			
			// Get CheckIn, CheckOut and TaskDueOn Date
			if (!(Utility.validateInput(CheckInDate))) {
			    for (int i = 0; i < GuestFirstName.split("\\|").length; i++) {
			        checkInDates.add(Utility.getCurrentDate("dd/MM/yyyy"));
			        checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(1), "MM/dd/yyyy", "dd/MM/yyyy"));
			        }
			} else {
			    checkInDates = Utility.splitInputData(CheckInDate);
			    checkOutDates = Utility.splitInputData(CheckOutDate);
			}
			CheckInDate = checkInDates.get(0);
			CheckOutDate = checkOutDates.get(0);

			app_logs.info(CheckInDate);
			app_logs.info(CheckOutDate);
			date=Utility.parseDate(CheckInDate, "dd/MM/yyyy", "MM/dd/yy");
			app_logs.info(date);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Get CheckIn Date and Checkout Date", testName, "ChekIn and CheckOut date", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to Get CheckIn Date and Checkout Date", testName, "ChekIn and CheckOut date", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Login
		try {
			driver = getDriver();
			login_CP(driver);
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
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Reservation
		try {
			CPReservationPage.click_NewReservation(driver, test_steps);
			
			CPReservationPage.select_CheckInDate(driver, test_steps, CheckInDate);
			CPReservationPage.select_CheckoutDate(driver, test_steps, CheckOutDate);
			CPReservationPage.enter_Adults(driver, test_steps, Adults);
			CPReservationPage.enter_Children(driver, test_steps, Children);
			CPReservationPage.select_Rateplan(driver, test_steps, Rateplan,PromoCode);
			CPReservationPage.clickOnFindRooms(driver, test_steps);
			
			CPReservationPage.selectRoom(driver, test_steps, RoomClass, IsAssign,Account);
			
			CPReservationPage.deposit(driver, test_steps, IsDepositOverride, DepositOverrideAmount);
			CPReservationPage.clickNext(driver, test_steps);
			
			test_steps.add("==========Entering Mailing Address==========");
			app_logs.info("==========Entering Mailing Address==========");
			CPReservationPage.enter_MailingAddress(driver, test_steps, Salutation, GuestFirstName, GuestLastName,PhoneNumber,AltenativePhone,Email,Account,AccountType,Address1,Address2,Address3,City,Country,State,PostalCode,IsGuesProfile);
			
			test_steps.add("==========Entering Billing Address==========");
			app_logs.info("==========Entering Billing Address==========");
			CPReservationPage.enter_BillingAddress(driver, test_steps, BillingSalutation, BillingGuestFirstName, BillingGuestLastName, BillingAddress1, BillingAddress2, BillingAddress3, BillingCity, BillingCountry, BillingState, BillingPostalCode, IsBillingSameAsMailingAddress);
			
			if((Account.equalsIgnoreCase("")||Account.isEmpty())) {
				CPReservationPage.enter_PaymentDetails(driver, test_steps, PaymentType, CardNumber, NameOnCard, CardExpDate);
			}
			
			CPReservationPage.enter_MarketSegmentDetails(driver, test_steps, TravelAgent, MarketSegment, Referral);
			
			CPReservationPage.clickBookNow(driver, test_steps);
			test_steps.add("Click on book now");
			app_logs.info("Click on book now");

			CPReservationPage.get_ReservationConfirmationNumber(driver, test_steps);
			CPReservationPage.get_ReservationStatus(driver, test_steps);
			CPReservationPage.clickCloseReservationSavePopup(driver, test_steps);
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create New reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to creae New reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			app_logs.info("==========Verify Payment Method==========");
			test_steps.add("==========Verify Payment Method==========");
			getTest_Steps.clear();
			getTest_Steps = CPReservationPage.VerifyPaymentMethod(driver, getTest_Steps, PaymentType, CardNumber, NameOnCard, CardExpDate);
			test_steps.addAll(getTest_Steps);
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify payment", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify payment", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getData("VerifyPaymentWithDiffCont_Bill", excel);
	}

	@AfterClass(alwaysRun=true)
	public void closeDriver() {
		driver.quit();
	}
}
