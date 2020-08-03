package com.innroad.inncenter.tests;

import java.io.IOException;
import java.util.ArrayList;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Policies;
import com.innroad.inncenter.pageobjects.Properties;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class VerifyCheckoutWithManualPaymentForSecondaryGuest extends TestCore {

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
	public void verifyCheckoutWithManualPaymentForSecondaryGuest(String url, String ClientCode, String Username,
			String Password, String CheckInDate, String CheckOutDate, String Adults, String Children, String Rateplan,
			String PromoCode, String IsAssign, String IsDepositOverride, String DepositOverrideAmount,
			String Salutation, String PrimaryFirstName, String PrimaryLastName, String SecondaryFirstName,
			String SecondaryLastName, String PhoneNumber, String AlternativePhone, String Email, String Account,
			String AccountType, String Address1, String Address2, String Address3, String City, String Country,
			String State, String PostalCode, String IsGuesProfile, String PaymentType, String CardNumber,
			String NameOnCard, String CardExpDate, String IsChangeInPayAmount, String ChangedAmountValue,
			String TravelAgent, String MarketSegment, String Referral, String PolicyName, String PolicyType,
			String Chargestype, String Value, String PolicyText, String PolicyDesc, String Season, String RoomClassAbb,
			String RoomClassName, String bedsCount, String maxAdults, String maxPersons, String roomQuantity,
			String RateName, String BaseAmount, String AddtionalAdult, String AdditionalChild, String DisplayName,
			String RatePolicy, String RateDescription, String reservationStatusExpected)
			throws InterruptedException, IOException {

		test_name = "VerifyCheckoutWithManualPaymentForSecondaryGuest";
		testDescription = "Verify check-out should be happened with manual payment method for secondary guest<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682495' target='_blank'><br>"
				+ "Click here to open TestRail: C682495</a>";
		testCategory = "ManualPaymentMethod";
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
		Properties properties = new Properties();
		Policies policy = new Policies();
		RoomClass room_class = new RoomClass();
		Rate rate = new Rate();
		Double depositAmount = 0.0;
		String tripTotal = null;
		String reservationNumber = null;
		String reservationStatus = null;
		PrimaryLastName = PrimaryLastName + Utility.GenerateRandomNumber();
		SecondaryFirstName = SecondaryFirstName + Utility.GenerateRandomNumber();
		String firstRoomNumber = null;
		String secondRoomNumber = null;
		String Date = Utility.getCurrentDate("MM/dd/YY");
		String selectedProperty = null;
		CheckInDate=Utility.getCurrentDate("dd/MM/YY");
		CheckOutDate=Utility.GetNextDate(1, "dd/MM/YY");
		System.out.print(CheckInDate);
		System.out.print(CheckOutDate);

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

		try {
			app_logs.info("======Property Settings=======");
			test_steps.add("======Property Settings=======");
			nav.Setup(driver);
			app_logs.info("Navigate To Setup");
			test_steps.add("Navigate To Setup ");
			nav.Properties(driver);
			app_logs.info("Navigate To Properties");
			test_steps.add("Navigate To Properties ");
			selectedProperty = properties.getProperty(driver, getTest_Steps);
			app_logs.info("Selected Property:" + selectedProperty);
			test_steps.add("Selected Property:" + selectedProperty);
			getTest_Steps.clear();
			getTest_Steps = properties.SearchProperty_Click(driver, selectedProperty, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			nav.click_PropertyOptions(driver, getTest_Steps);
			app_logs.info("Navigate To Properties Options");
			test_steps.add("Navigate To Properties Options ");

			getTest_Steps.clear();
			getTest_Steps = properties.depositRequiredForSaveGaurenteedReservationCheckbox(driver, true, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = properties.depositRecordedAutomaticallySetGaurenteedReservationCheckbox(driver, true,
					getTest_Steps);
			test_steps.addAll(getTest_Steps);

			properties.SaveButton(driver);
			test_steps.add("Clicked Save Button");
			app_logs.info("Clicked Save Button");

			properties.LogOut(driver);
			test_steps.add("Logged out the application");
			app_logs.info("Logged out the application");
			login_CP(driver);
			test_steps.add("Logged into the application again");
			app_logs.info("Logged into the application again");

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
		RoomClassName = RoomClassName + Utility.GenerateRandomNumber();
		try {
			app_logs.info("======Create RoomClass=======");
			test_steps.add("======Create RoomClass=======");
			nav.Setup(driver);
			app_logs.info("Navigate Setup");
			test_steps.add("Navigate Setup");
			nav.RoomClass(driver);
			app_logs.info("Navigate RoomClass");
			test_steps.add("Navigate RoomClass");
			nav.NewRoomClass(driver);
			app_logs.info("NewRoomClass Button Clicked");
			test_steps.add("NewRoomClass Button Clicked");
			getTest_Steps.clear();
			getTest_Steps = room_class.Create_RoomClass(driver, RoomClassName, RoomClassAbb, bedsCount, maxAdults,
					maxPersons, roomQuantity, test, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			getTest_Steps = room_class.CloseOpenedRoomClass(driver, RoomClassName, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			test_steps.add("Sccessfully Created New RoomClass  : " + RoomClassName);
			app_logs.info("Sccessfully Created New RoomClass :  " + RoomClassName);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to create room class successfully", testName, "NewRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to create room class successfully", testName, "NewRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		RateName = RateName + Utility.GenerateRandomNumber();
		try {
			app_logs.info("======Create Rate=======");
			test_steps.add("======Create Rate=======");
			nav.Reservation_Backward_1(driver);
			nav.Inventory(driver);
			app_logs.info("Navigate Inventory");
			test_steps.add("Navigate Inventory");
			nav.Rates1(driver);
			app_logs.info("Navigate Rate");
			test_steps.add("Navigate Rate");
			DisplayName = RateName;
			getTest_Steps.clear();
			getTest_Steps = rate.CreateRate_Season(driver, RateName, maxAdults, maxPersons, BaseAmount, AddtionalAdult,
					AdditionalChild, DisplayName, RatePolicy, RateDescription, RoomClassName, Season, Rateplan,
					getTest_Steps);
			test_steps.addAll(getTest_Steps);
			test_steps.add("Enter all require details and save rate");
			app_logs.info("Enter all require details and save rate");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to create new rate", testName, "CreateNewRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to create new rate", testName, "CreateNewRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			test_steps.add("============= Creating policy ================");
			app_logs.info("============= Creating policy ================");
			nav.Inventory(driver);
			nav.policies(driver);
			policy.ClickNewPolicybutton(driver);
			PolicyName = PolicyName + "_" + Utility.generateRandomString();
			policy.Enter_Policy_Name(driver, PolicyName, getTest_Steps);
			policy.Enter_Policy_Type(driver, PolicyType);
			getTest_Steps.clear();
			getTest_Steps = policy.Deposit_Policy_Attributes(driver, Chargestype, Value, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			policy.Enter_Policy_Desc(driver, PolicyText, PolicyDesc);
			policy.Associate_Sources(driver);
			policy.Associate_Seasons(driver, Season);
			policy.Associate_RoomClasses(driver, RoomClassName);
			app_logs.info("Associating Room Class to policy : " + RoomClassName);
			test_steps.add("Associating Room Class to policy : " + RoomClassName);
			policy.Associate_RatePlan(driver, Rateplan);
			app_logs.info("Associating Rateplan to policy : " + Rateplan);
			test_steps.add("Associating Rateplan to policy : " + Rateplan);
			policy.Save_Policy(driver);
			policy.Close_Policy_Tab(driver);
			test_steps.add("Created Policy : " + PolicyName);
			app_logs.info("Created Policy : " + PolicyName);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to create deposit policy", testName, "DepositPolicy", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to create deposit policy", testName, "DepositPolicy", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Create Reservation
		try {
			app_logs.info("==========CREATING RESERVATION==========");
			test_steps.add("==========CREATING RESERVATION==========");

			nav.Setup(driver);
			nav.Reservation_Backward_1(driver);
			test_steps.add("Navigate to reservation");
			app_logs.info("Navigate to reservation");
			getTest_Steps.clear();
			getTest_Steps = res.click_NewReservation(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.clickAddARoom(driver);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.checkInDates(driver, 0, 0);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.checkOutDates(driver, 1, 0);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.checkInDates(driver, 0, 1);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.checkOutDates(driver,1, 1);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.clickOnFindRooms(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.selectRoom(driver,getTest_Steps, RoomClassName, IsAssign,Account);
			test_steps.addAll(getTest_Steps);

			depositAmount = res.deposit(driver, getTest_Steps, IsDepositOverride, DepositOverrideAmount);
			getTest_Steps = res.selectRoom(driver,getTest_Steps, RoomClassName, IsAssign,Account);
			depositAmount = depositAmount
					+ res.deposit(driver, getTest_Steps, IsDepositOverride, DepositOverrideAmount);

			getTest_Steps.clear();
			getTest_Steps = res.clickNext(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			firstRoomNumber = res.selectRoomNumberInMRB(driver, RoomClassName, 0, 0);
			String SplitStr[] = firstRoomNumber.split(":");
			firstRoomNumber = SplitStr[1].trim();
			app_logs.info("FirstReservationNumber: " + firstRoomNumber);
			res.enter_MailingAddress_OnUnchecked_CreateGuestProfile(driver, test_steps, Salutation, PrimaryFirstName,
					PrimaryLastName, IsGuesProfile);
			res.enter_PaymentDetails(driver, test_steps, PaymentType, CardNumber, NameOnCard, CardExpDate);

			secondRoomNumber = res.selectRoomNumberInMRB(driver, RoomClassName, 0, 1);
			SplitStr = secondRoomNumber.split(":");
			secondRoomNumber = SplitStr[1].trim();
			app_logs.info("SecondReservationNumber: " + secondRoomNumber);

			getTest_Steps.clear();
			getTest_Steps = res.enterSecondaryGuestInfo(driver, getTest_Steps, Salutation, SecondaryFirstName,
					SecondaryLastName);
			test_steps.addAll(getTest_Steps);

			res.enter_MarketSegmentDetails(driver, test_steps, TravelAgent, MarketSegment, Referral);
			res.clickBookNow(driver, test_steps);
			reservationNumber = res.get_ReservationConfirmationNumber(driver, getTest_Steps);
			reservationStatus = res.get_ReservationStatus(driver, getTest_Steps);

			app_logs.info("==========VERIFYING RESERVATION STATUS==========");
			test_steps.add("==========VERIFYING RESERVATION STATUS===========");

			assertEquals(reservationStatus, reservationStatusExpected);
			test_steps.add(
					"Verified Reservation Status in Reservation Confirmation Popup: <b>" + reservationStatus + "</b>");
			app_logs.info("Verified Reservation Status in  Reservation Confirmation Popup: " + reservationStatus);
			res.clickCloseReservationSavePopup(driver, test_steps);
			tripTotal = res.get_TripSummaryTripTotalChargesWithoutCurrency(driver, getTest_Steps);
			tripTotal = tripTotal.replaceAll(" ", "");
			getTest_Steps.clear();
			getTest_Steps = res.Verify_ReservationStatus_Status(driver, getTest_Steps, reservationStatusExpected);
			test_steps.addAll(getTest_Steps);

			res.verify_DepositPolicyAssociated(driver, test_steps, PolicyName);
			res.click_History(driver, test_steps);
			getTest_Steps.clear();
			getTest_Steps = res.verifyHistoryTabForPaymentDetail(driver, getTest_Steps, Date, tripTotal, false,
					PaymentType, PaymentType, CardNumber.substring(12, 16), CardExpDate);
			test_steps.addAll(getTest_Steps);

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

		// Checkin
		String folioCountBefore = null;
		String folioCountAfter = null;
		String updatedPaymentMethod = null;
		try {

			app_logs.info("===========SECONDARY GUEST CHECKIN=========");
			test_steps.add("==========SECONDARY GUEST CHECKIN===========");
			getTest_Steps.clear();
			getTest_Steps = res.ClickOnDetails(driver);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.secondayGuestCheckInButtonClick(driver, Salutation + SecondaryFirstName, false);
			test_steps.addAll(getTest_Steps);

			Wait.wait2Second();
			res.verify_DepositAmountTripSummary(driver, getTest_Steps, CheckInDate, CheckOutDate, Value, depositAmount);
			res.close_FirstOpenedReservation(driver, test_steps);

			res.searchWithGuestName(driver, getTest_Steps, SecondaryFirstName);
			app_logs.info("Successfully Searched Reservation " + SecondaryFirstName);
			test_steps.add("Successfully Searched Reservation " + SecondaryFirstName);
			reservationStatusExpected = "In-House";
			getTest_Steps.clear();
			getTest_Steps = reser_search.verifyReservationStatusWithName(driver, SecondaryFirstName,
					reservationStatusExpected, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			reser_search.SearchAndOpenRes(driver, reservationNumber);
			app_logs.info("Successfully Searched & Opened Reservation " + reservationNumber);
			test_steps.add("Successfully Searched &Opened Reservation " + reservationNumber);
			getTest_Steps.clear();
			getTest_Steps = res.clickEditPaymentMethodInfo(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			updatedPaymentMethod = "Cash";
			res.enter_PaymentDetails(driver, test_steps, updatedPaymentMethod, CardNumber, NameOnCard, CardExpDate);
			getTest_Steps.clear();
			getTest_Steps = res.clickSaveButtonAfterEditPaymentMethod(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			res.click_Folio(driver, getTest_Steps);
			folioCountBefore = res.getFolioOptionCount(driver);
			getTest_Steps.clear();
			getTest_Steps = res.ClickOnDetails(driver);
			test_steps.addAll(getTest_Steps);

			res.click_History(driver, test_steps);

			getTest_Steps.clear();
			getTest_Steps = res.verifyHistoryTabForPaymentDetail(driver, getTest_Steps, Date, tripTotal, true,
					PaymentType, updatedPaymentMethod, CardNumber, CardExpDate);
			test_steps.addAll(getTest_Steps);

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to CheckIn Secondary Guest  ", testName, "SecondaryGuestCheckin",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to CheckIn Secondary Guest ", testName, "SecondaryGuestCheckin",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Checkout
		try {

			app_logs.info("===========SECONDARY GUEST CHECKOUT=========");
			test_steps.add("==========SECONDARY GUEST CHECKOUT===========");

			getTest_Steps.clear();
			getTest_Steps = res.ClickOnDetails(driver);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.secondayGuestCheckoutButtonClick(driver, RoomClassName, secondRoomNumber);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.secondayGuestCheckoutConfirm(driver, Salutation + SecondaryFirstName, false);
			test_steps.addAll(getTest_Steps);

			res.verify_DepositAmountTripSummary(driver, test_steps, CheckInDate, CheckOutDate, Value, depositAmount);
			reservationStatusExpected = "Guaranteed";
			getTest_Steps.clear();
			getTest_Steps = res.Verify_ReservationStatus_Status(driver, getTest_Steps, reservationStatusExpected);
			test_steps.addAll(getTest_Steps);

			res.click_Folio(driver, getTest_Steps);
			folioCountAfter = res.getFolioOptionCount(driver);
			assertEquals(folioCountAfter, folioCountBefore);
			test_steps.add("No additional payment folio item added: Before Checkout Count: " + folioCountBefore
					+ " After Checkout Count: " + folioCountAfter);
			app_logs.info("No additional payment folio item added: Before Checkout Count: " + folioCountBefore
					+ " After Checkout Count: " + folioCountAfter);
			getTest_Steps.clear();
			getTest_Steps = res.ClickOnDetails(driver);
			test_steps.addAll(getTest_Steps);

			res.close_FirstOpenedReservation(driver, test_steps);
			res.searchWithGuestName(driver, getTest_Steps, SecondaryFirstName);

			app_logs.info("Successfully Searched Reservation " + SecondaryFirstName);
			test_steps.add("Successfully Searched Reservation " + SecondaryFirstName);
			reservationStatusExpected = "Departed";
			getTest_Steps.clear();
			getTest_Steps = reser_search.verifyReservationStatusWithName(driver, SecondaryFirstName,
					reservationStatusExpected, getTest_Steps);
			test_steps.addAll(getTest_Steps);

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Checkout Secondary Guest  ", testName, "SecondaryGuestCheckout",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Checkout Secondary Guest ", testName, "SecondaryGuestCheckout",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			test_steps.add("============= Delete policy ================");
			app_logs.info("============= Delete policy ================");
			nav.Inventory(driver);
			nav.policies(driver);
			policy.Delete_Policy(driver, PolicyName);
			test_steps.add("Deleted Policy : " + PolicyName);
			app_logs.info("Deleted Policy : " + PolicyName);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to delete deposit policy", testName, "DepositPolicy", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to delete deposit policy", testName, "DepositPolicy", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("======Delete Rate=======");
			test_steps.add("======Delete Rate=======");
			driver.navigate().refresh();
			nav.Inventory(driver);
			test_steps.add("Navigate to Inventory");
			app_logs.info("Navigate to Inventory");

			// create new method
			getTest_Steps.clear();
			getTest_Steps = nav.secondaryRatesMenuItem(driver);
			test_steps.addAll(getTest_Steps);

			// start new method for delete rate
			getTest_Steps.clear();
			getTest_Steps = rate.deleteRates(driver, RateName);
			getTest_Steps.addAll(getTest_Steps);

			test_steps.add("New Rate has been Deleted successfully");
			app_logs.info("New Rate has been Deleted successfully");

			rate.verifyDeleteRate(driver, RateName);
			test_steps.add("Verify the Deleted Rate : " + RateName);
			app_logs.info("Verify the Deleted Rate " + RateName);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to delete a rate ", testName, "DeleteRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to delete a rate ", testName, "DeleteRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("======Delete RoomClass=======");
			test_steps.add("======Delete RoomClass=======");
			nav.Setup(driver);
			nav.RoomClass(driver);
			room_class.searchClass(driver, RoomClassName);
			app_logs.info("Search");
			room_class.deleteRoomClass(driver, RoomClassName);

			test_steps.add("Sccessfully Deleted RoomClass  : " + RoomClassName);
			app_logs.info("Sccessfully Deleted RoomClass :  " + RoomClassName);
			

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to delete room class ", testName, "RoomClass Delete", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to delete room class successfully", testName, "RoomClass Delete",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("======Property Settings=======");
			test_steps.add("======Property Settings=======");
			nav.Setup(driver);
			app_logs.info("Navigate To Setup");
			test_steps.add("Navigate To Setup ");
			nav.Properties(driver);
			app_logs.info("Navigate To Properties");
			test_steps.add("Navigate To Properties ");
			getTest_Steps.clear();
			getTest_Steps = properties.SearchProperty_Click(driver, selectedProperty, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			nav.click_PropertyOptions(driver, getTest_Steps);
			app_logs.info("Navigate To Properties Options");
			test_steps.add("Navigate To Properties Options ");

			getTest_Steps.clear();
			getTest_Steps = properties.depositRequiredForSaveGaurenteedReservationCheckbox(driver, false,
					getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = properties.depositRecordedAutomaticallySetGaurenteedReservationCheckbox(driver, false,
					getTest_Steps);
			test_steps.addAll(getTest_Steps);

			properties.SaveButton(driver);
			test_steps.add("Clicked Save Button");
			app_logs.info("Clicked Save Button");

			properties.LogOut(driver);
			test_steps.add("Logged out the application");
			app_logs.info("Logged out the application");

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);

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

	}

	@DataProvider
	public Object[][] getData() {

		// return test data from the sheet name provided

		return Utility.getData("VerifyManualPaymentForSecGuest", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
