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
import com.innroad.inncenter.pageobjects.Folio;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Properties;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class VerifyUserToPayByReservationWithCancelPaymentMethod extends TestCore {
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
	public void verifyUserToPayByReservationWithCancelPaymentMethod(String url, String ClientCode, String Username,
			String Password, String CheckInDate, String CheckOutDate, String Adults, String Children, String PromoCode,
			String IsAssign, String IsDepositOverride, String DepositOverrideAmount, String Salutation,
			String FirstName, String LastName, String PhoneNumber, String AlternativePhone, String Email,
			String Account, String AccountType, String Address1, String Address2, String Address3, String City,
			String Country, String State, String PostalCode, String IsGuesProfile, String PaymentType,
			String CardNumber, String NameOnCard, String CardExpDate, String IsChangeInPayAmount,
			String ChangedAmountValue, String TravelAgent, String MarketSegment, String Referral, String RoomClassAbb,
			String RoomClassName, String bedsCount, String maxAdults, String maxPersons, String roomQuantity,
			String Season, String RatePlan, String RateName, String BaseAmount, String AddtionalAdult,
			String AdditionalChild, String DisplayName, String AssociateSession, String RatePolicy,
			String RateDescription) throws InterruptedException, IOException {

		test_name = "VerifyUserToPayByReservationWithCancelIPaymentMethod";
		testDescription = "Verify whether user is able to pay by reservation option with status CANCELLED in payment method<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682508' target='_blank'><br>"
				+ "Click here to open TestRail: C682508</a><br>"
				+ "Verify whether user is able to pay by reservation option with status RESERVED in payment method<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682507' target='_blank'><br>"
				+ "Click here to open TestRail: C682507</a><br>" + "Verify Payment of a reservation via Reservation<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682403' target='_blank'><br>"
				+ "Click here to open TestRail: C682403</a><br>"
				+ "Verify whether user is able to pay by reservation with less than today's date payment method option<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682509' target='_blank'><br>"
				+ "Click here to open TestRail: C682509</a>";

		testCategory = "VerificationofReservationPaymentMehtod";
		String testName = test_name;

		TestName.add(testName);
		TestDescription.add(testDescription);
		TestCategory.add(testCategory);
		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");
		Navigation nav = new Navigation();
		RoomClass roomClass = new RoomClass();
		Rate rate = new Rate();
		ReservationSearch reser_search = new ReservationSearch();
		Properties properties = new Properties();
		CPReservationPage res = new CPReservationPage();
		Folio folio = new Folio();
		String reservationNumberFirst = null;
		String reservationNumberSecond = null;
		String reservationNumberThird = null;

		LastName = LastName + Utility.GenerateRandomNumber();
		RateName = RateName + Utility.GenerateRandomNumber();
		RoomClassName = RoomClassName + Utility.GenerateRandomNumber();
		String tempraryRoomClassName = RoomClassName;
		String totalChargesFirstReservation = null;
		String totalChargesSecondReservation = null;
		Double depositAmount = 0.0;
		String selectedProperty = null;
		String description = null;

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

			nav.click_PropertyOptions(driver, test_steps);

			properties.PropertyOptions_requireCreditCard(driver, test, false, test_steps);

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
			getTest_Steps = roomClass.Create_RoomClass(driver, RoomClassName, RoomClassAbb, bedsCount, maxAdults,
					maxPersons, roomQuantity, test, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			roomClass.CloseOpenedRoomClass(driver, RoomClassName, getTest_Steps);
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
					AdditionalChild, DisplayName, RatePolicy, RateDescription, RoomClassName, Season, RatePlan,
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

		// Create Reservation
		// For Cancel
		try {
			app_logs.info("======CP Reservation Creation For Cancel Status Verification=======");
			test_steps.add("======CP Reservation Creation For Cancel Status Verification=======");
			nav.Reservation_Backward_1(driver);
			res.click_NewReservation(driver, test_steps);
			res.checkInDate(driver, 1);
			res.checkOutDate(driver, 1);
			res.enter_Adults(driver, test_steps, Adults);
			res.enter_Children(driver, test_steps, Children);
			res.select_Rateplan(driver, test_steps, RatePlan, PromoCode);
			res.clickOnFindRooms(driver, test_steps);
			res.selectRoom(driver, test_steps, RoomClassName, IsAssign, Account);
			depositAmount = res.deposit(driver, test_steps, IsDepositOverride, DepositOverrideAmount);
			res.clickNext(driver, test_steps);
			res.enter_ContactName(driver, getTest_Steps, "", FirstName, LastName);
			res.enter_MailingAddress(driver, test_steps, Salutation, FirstName, LastName, PhoneNumber, AlternativePhone,
					Email, Account, AccountType, Address1, Address2, Address3, City, Country, State, PostalCode,
					IsGuesProfile);
			if ((Account.equalsIgnoreCase("") || Account.isEmpty())) {
				res.enter_PaymentDetails(driver, test_steps, PaymentType, CardNumber, NameOnCard, CardExpDate);
			}
			res.selectReferral(driver, Referral);
			res.clickBookNow(driver, test_steps);
			reservationNumberFirst = res.get_ReservationConfirmationNumber(driver, test_steps);
			res.clickCloseReservationSavePopup(driver, test_steps);
			res.change_ReservationStatus(driver, test_steps, "Cancel");
			res.click_Folio(driver, test_steps);
			totalChargesFirstReservation = res.get_TotalCharges(driver, getTest_Steps);
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

		// Second Reservation
		// For Reserved
		// Part-2
		try {
			app_logs.info("======CP Reservation Creation For Reserved Status/Previous Date =======");
			test_steps.add("======CP Reservation Creation For Reserved Status/Previous Date=======");
			res.click_NewReservation(driver, test_steps);
			res.checkInDate(driver, 0);
			res.checkOutDate(driver, 0);
			res.enter_Adults(driver, test_steps, Adults);
			res.enter_Children(driver, test_steps, Children);
			res.select_Rateplan(driver, test_steps, RatePlan, PromoCode);
			res.clickOnFindRooms(driver, test_steps);
			res.selectRoom(driver, test_steps, RoomClassName, IsAssign, Account);
			depositAmount = res.deposit(driver, test_steps, IsDepositOverride, DepositOverrideAmount);
			res.clickNext(driver, test_steps);
			res.enter_ContactName(driver, getTest_Steps, "", FirstName, LastName);
			res.enter_MailingAddress(driver, test_steps, Salutation, FirstName, LastName, PhoneNumber, AlternativePhone,
					Email, Account, AccountType, Address1, Address2, Address3, City, Country, State, PostalCode,
					IsGuesProfile);
			PaymentType = "MC";
			if ((Account.equalsIgnoreCase("") || Account.isEmpty())) {
				res.enter_PaymentDetails(driver, test_steps, PaymentType, CardNumber, NameOnCard, CardExpDate);
			}
			getTest_Steps.clear();
			getTest_Steps = res.selectReferral(driver, Referral);
			test_steps.addAll(getTest_Steps);
			res.clickBookNow(driver, test_steps);
			reservationNumberSecond = res.get_ReservationConfirmationNumber(driver, test_steps);
			res.clickCloseReservationSavePopup(driver, test_steps);
			res.click_Folio(driver, test_steps);
			totalChargesSecondReservation = res.get_TotalCharges(driver, getTest_Steps);
			res.click_DeatilsTab(driver, test_steps);

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
			app_logs.info("======Copy Reservation For Verification of Payment Methods======");
			test_steps.add("=======Copy Reservation For Verification of Payment Methods =======");
			getTest_Steps.clear();
			getTest_Steps = res.clickCopyButton(driver, FirstName);
			test_steps.addAll(getTest_Steps);
			LastName = LastName + Utility.GenerateRandomNumber();
			res.enter_GuestName(driver, getTest_Steps, FirstName, LastName);
			res.clickBookNow(driver, test_steps);
			res.clickCloseReservationSavePopup(driver, test_steps);
			res.closeFirstOpenedReservationUnSavedData(driver, test_steps);
			res.closeFirstOpenedReservationUnSavedData(driver, test_steps);

			app_logs.info("======Search Reservation For Verification of Payment Methods=======");
			test_steps.add("======Search Reservation For Verification of Payment Methods=======");
			driver.navigate().refresh();
			reser_search.basicSearch_WithGuestName(driver, FirstName + " " + LastName);
			reser_search.OpenSearchedReservation(driver, getTest_Steps);
			app_logs.info("Successfully Searched & Opened Reservation " + FirstName + " " + LastName);
			test_steps.add("Successfully Searched &Opened Reservation " + FirstName + " " + LastName);
			reservationNumberThird = res.getReservationConfirmationNumberFromBanner(driver, test_steps);

			getTest_Steps.clear();
			getTest_Steps = res.clickEditPaymentMethodInfo(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			PaymentType = "Reservation";
			res.enterPaymentDetailsWithReservationPaymentMethod(driver, getTest_Steps, PaymentType,
					reservationNumberFirst);
			getTest_Steps.clear();
			getTest_Steps = res.clickSaveButtonAfterEditPaymentMethod(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			app_logs.info("======Pay With Cancelled Reservation as Payment Method=======");
			test_steps.add("======Pay With Cancelled Reservation as Payment Method=======");
			res.click_Folio(driver, test_steps);
			res.click_Pay(driver, test_steps);
			getTest_Steps.clear();
			getTest_Steps = res.verifyPaymentMethodWithReservationInFolio(driver, getTest_Steps, PaymentType,
					reservationNumberFirst);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.payButtonClickInTakePayment(driver, getTest_Steps, totalChargesSecondReservation, true,
					true);
			folio.verifyLineItemCategory(driver, PaymentType.toLowerCase(), test_steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.verifyLineItemDesc(driver, PaymentType.toLowerCase(),
					"Reservation - " + reservationNumberFirst);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			getTest_Steps = folio.verifyLineItemAmount(driver, PaymentType.toLowerCase(),
					totalChargesSecondReservation);
			test_steps.addAll(getTest_Steps);
			folio.ClickSaveFolioButton(driver);
			app_logs.info("Click on Save Folio Button");
			test_steps.add("Click on Save Folio Button");

			app_logs.info("======Pay With Resered/Previous Date Reservation as Payment Method=======");
			test_steps.add("======Pay With Resered/Previous Date Reservation as Payment Method=======");
			res.click_Pay(driver, test_steps);
			res.enterAmountTakePayment(driver, test_steps, IsChangeInPayAmount, totalChargesSecondReservation);
			getTest_Steps.clear();
			getTest_Steps = res.removeAttachedReservationInPayments(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			res.selectPaymentMethodWithReservationInFolio(driver, test_steps, PaymentType, reservationNumberSecond);
			res.payButtonClickInTakePayment(driver, test_steps, totalChargesSecondReservation, false, true);
			folio.verifyLineItemCategory(driver, PaymentType.toLowerCase(), test_steps);
			getTest_Steps.clear();
			getTest_Steps = folio.verifyLineItemDesc(driver, PaymentType.toLowerCase(),
					"Reservation - " + reservationNumberSecond);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.verifyLineItemAmount(driver, PaymentType.toLowerCase(),
					totalChargesSecondReservation);
			test_steps.addAll(getTest_Steps);
			folio.ClickSaveFolioButton(driver);
			app_logs.info(" Click Save Folio Button");
			test_steps.add("Click Save Folio Button");
			Wait.wait2Second();
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
			app_logs.info("======Search Cancelled Reservation & Verification=======");
			test_steps.add("======Search Cancelled Reservation & Verification=======");
			driver.navigate().refresh();
			reser_search.SearchAndOpenRes(driver, reservationNumberFirst);
			app_logs.info("Successfully Searched & Opened Reservation " + reservationNumberFirst);
			test_steps.add("Successfully Searched &Opened Reservation " + reservationNumberFirst);
			res.clickFolio(driver, test_steps);
			folio.verifyLineItemCategory(driver, PaymentType, test_steps);
			getTest_Steps.clear();
			getTest_Steps = folio.verifyLineItemAmount(driver, PaymentType,
					" -" + totalChargesSecondReservation.replace(" ", ""));
			test_steps.addAll(getTest_Steps);

			description = "Res #" + reservationNumberThird + " - " + FirstName + " " + LastName + " -";
			// System.out.print(description);
			System.out.print(folio.getLineItemDescription(driver, PaymentType).contains(description));
			System.out.print(folio.getLineItemDescription(driver, PaymentType).replaceAll(" ", "")
					.contains(description.replaceAll(" ", "")));
			assertEquals(folio.getLineItemDescription(driver, PaymentType).contains(description), true);
			app_logs.info("Description Verified: " + description);
			test_steps.add("Description Verified : " + description);

			String foundPayment = folio.GetPayment(driver);
			foundPayment = foundPayment.replace("(", "");
			foundPayment = foundPayment.replace(")", "");

			String expectedTotalBalance = String.format("%.2f",
					Float.parseFloat(totalChargesFirstReservation) + Float.parseFloat(foundPayment));

			String foundBalance = Float.toString(folio.GetEndingBalance(driver));

			assertEquals(expectedTotalBalance.contains(foundBalance), true);
			System.out.print("Expeetc Balance:" + expectedTotalBalance);

			app_logs.info("Balance Verified: " + foundBalance);
			test_steps.add("Balance Verified : " + foundBalance);
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
			app_logs.info("======Search Reserved/Previous Date Reservation & Verification=======");
			test_steps.add("======Search Reserved/Previous Date Reservation & Verification=======");

			reser_search.SearchAndOpenRes(driver, reservationNumberSecond);
			app_logs.info("Successfully Searched & Opened Reservation " + reservationNumberSecond);
			test_steps.add("Successfully Searched &Opened Reservation " + reservationNumberSecond);
			res.clickFolio(driver, test_steps);
			folio.verifyLineItemCategory(driver, PaymentType, test_steps);
			getTest_Steps.clear();
			getTest_Steps = folio.verifyLineItemAmount(driver, PaymentType,
					"-" + totalChargesSecondReservation.replace(" ", ""));
			test_steps.addAll(getTest_Steps);

			description = "Res #" + reservationNumberThird + " - " + FirstName + " " + LastName + " - ";
			System.out.print(folio.getLineItemDescription(driver, PaymentType).contains(description));

			assertEquals(folio.getLineItemDescription(driver, PaymentType).contains(description), true);
			app_logs.info("Description Verified: " + description);
			test_steps.add("Description Verified : " + description);
			String foundPayment = folio.GetPayment(driver);
			foundPayment = foundPayment.replace("(", "");
			foundPayment = foundPayment.replace(")", "");
			String expectedTotalBalance = String.format("%.2f",
					Float.parseFloat(totalChargesSecondReservation) + Float.parseFloat(foundPayment));

			String balance = Float.toString(folio.GetEndingBalance(driver));

			assertEquals(expectedTotalBalance.contains(balance), true);

			app_logs.info("Balance Verified: " + balance);
			test_steps.add("Balance Verified : " + balance);
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
			tempraryRoomClassName = RoomClassName;
			roomClass.searchClass(driver, tempraryRoomClassName);
			app_logs.info("Search");
			roomClass.deleteRoomClass(driver, tempraryRoomClassName);

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

			nav.click_PropertyOptions(driver, test_steps);

			properties.PropertyOptions_requireCreditCard(driver, test, true, test_steps);

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

		return Utility.getData("VerifyUserPayByResCancelPayment", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		 driver.quit();
	}

}
