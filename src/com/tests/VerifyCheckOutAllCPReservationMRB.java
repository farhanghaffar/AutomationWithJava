package com.innroad.inncenter.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Policies;
import com.innroad.inncenter.pageobjects.Properties;
import com.innroad.inncenter.pageobjects.Reports;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.RoomMaintenance;
import com.innroad.inncenter.pageobjects.RoomStatus;
import com.innroad.inncenter.pageobjects.TaskManagement;
import com.innroad.inncenter.pageobjects.Tax;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.WebElements_Policies;

public class VerifyCheckOutAllCPReservationMRB extends TestCore {

	private WebDriver driver = null;
	public static String test_name = null;
	public static String test_nameOne = null;
	public static String test_description = null;
	public static String test_catagory = null;
	ArrayList<String> test_steps = new ArrayList<>();
	String testName = null, paymentMethod = null, paymentMethodOnCheckIn = null;
	RoomClass rc = new RoomClass();
	Navigation nav = new Navigation();
	RoomStatus roomstatus = new RoomStatus();
	CPReservationPage res = new CPReservationPage();
	ArrayList<String> roomCost = new ArrayList<String>();
	ArrayList<String> rooms = new ArrayList<String>();
	ArrayList<String> newRooms = new ArrayList<String>();
	ArrayList<String> roomAbbri = new ArrayList<String>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	List<String> roomNos = new ArrayList<String>();
	List<String> guestNames = new ArrayList<String>();
	String reportContant = null;
	TaskManagement task_mang = new TaskManagement();
	Properties properties = new Properties();
	ReservationSearch revSearch = new ReservationSearch();
	Policies policies = new Policies();
	Tax tax = new Tax();
	Reports report = new Reports();
	Double depositAmount = 0.0;
	String reservation = null, panelGuestName = null, status = null, tripTotalAmounts = null, panelStatus = null,
			propertyName = null, checkOutAmount = null, taxAmout = null, yearDate = null, policyName = null,
			timeZone = null;
	Date currentDate = null, previousDate = null;
	int halfAmountPaid = 0, checkoutBalance = 0;
	String buttonText = null, windowBefore = null;
	boolean isGuestStatementCheckBox = false, generateGuestStatementStatus = false, checkInPaymentWindow = false,
			checkoutPaymentWindow = false, isPolicyGridExit = false;
	String Message = "Are you sure you want to check-out all rooms at once? The Primary Guest will be responsible for all remaining Guest Charges.";
	String checkInAmount = null;

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void CheckOutAll_Reservation(String URL, String ClientCode, String UserName, String Password,
			String RoomClass, String CheckInDate, String CheckOutDate, String Adults, String Children, String Rateplan,
			String PromoCode, String IsSplitRes, String IsAssign, String Salutation, String GuestFirstName,
			String GuestLastName, String PhoneNumber, String AltenativePhone, String Email, String Account,
			String AccountType, String Address1, String Address2, String Address3, String City, String Country,
			String State, String PostalCode, String IsGuesProfile, String IsAddMoreGuestInfo, String PaymentType,
			String CardNumber, String NameOnCard, String TravelAgent, String MarketSegment, String Referral,
			String IsGuesRegistration, String IsOverRideCheckIN, String SetPaymentMethod, String Notes,
			String CheckOutNote, String TakePaymentType) {

		test_name = "Verify CP MRB Check-Out Reservation";
		test_description = "Verify CP MRB Check-Out Reservation <br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682479' target='_blank'>"
				+ "Click here to open TestRail: C682479</a><br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682506' target='_blank'>"
				+ "Click here to open TestRail: C682506</a><br>";
		test_catagory = "CPReservation_CheckOut";
		testName = test_name;

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		// Login
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

		 //Get checkIN and Checkout Date
		try
		{
							
			if ( !(Utility.validateInput(CheckInDate)) ) {
				for (int i = 0; i < GuestFirstName.split("\\|").length; i++) {
					checkInDates.add(Utility.getCurrentDate("dd/MM/yyyy"));
					checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(1), "MM/dd/yyyy", "dd/MM/yyyy"));
				}
			}else {
				checkInDates = Utility.splitInputData(CheckInDate);
				checkOutDates = Utility.splitInputData(CheckOutDate);
			}
			CheckInDate=checkInDates.get(0)+"|"+checkInDates.get(1);
			CheckOutDate=checkOutDates.get(0)+"|"+checkOutDates.get(1);
			app_logs.info(CheckInDate);
			app_logs.info(CheckOutDate);
			
	} catch (Exception e) {
		e.printStackTrace();
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
			Utility.updateReport(e, "Failed to Get CheckIN CheckOutDate", testName, "Reservation", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
			Utility.updateReport(e, "Failed to Get CheckIN CheckOutDate", testName, "Reservation", driver);
		} else {
			Assert.assertTrue(false);
		}
	}
		// Get Abbreviations
		try {
			nav.Setup(driver);
			nav.RoomClass(driver);
			test_steps.add("<b>****Getting Abbreviations****</b>");
			rc.getRoomClassAbbrivations(driver, test_steps, roomAbbri, RoomClass);
			System.out.println(roomAbbri);
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to get room class abbrivations", testName, "Room class", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to get room class abbrivations", testName, "Room class", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Get PropertyName
		try {
			propertyName = properties.getProperty(driver, test_steps);

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

		try {
			nav.Setup(driver);
			nav.Properties(driver);
			nav.open_Property(driver, test_steps, propertyName);
			nav.click_PropertyOptions(driver, test_steps);
			timeZone = nav.get_Property_TimeZone(driver);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Create New Reservation
		try {
			nav.Setup(driver);
			nav.Reservation_Backward_1(driver);
			test_steps.add("<b>****Start Creating New Reservation****</b>");
			res.click_NewReservation(driver, test_steps);

			res.select_Dates(driver, test_steps, CheckInDate, CheckOutDate, Adults, Children, Rateplan, PromoCode,
					IsSplitRes);
			res.clickOnFindRooms(driver, test_steps);
			 res.select_MRBRooms(driver, test_steps, RoomClass, IsAssign, Account);
			String TripAmount = res.getTripSummaryTripTotalAmountBeforeReservation(driver, test_steps);
			halfAmountPaid = (int) (Double.parseDouble(TripAmount) / 2);
			System.out.println("Override Deposit Due Amount : " + halfAmountPaid);
			res.InputdepositDue(driver, test_steps, String.valueOf(halfAmountPaid));
			res.clickNext(driver, test_steps);
			yearDate = Utility.getFutureMonthAndYearForMasterCard(driver, test_steps);
			Random random = new Random();
			int x = random.nextInt(900);
			res.enter_MRB_MailingAddressForMRB(driver, test_steps, Salutation, GuestFirstName + x, GuestLastName + x,
					PhoneNumber, AltenativePhone, Email, Account, AccountType, Address1, Address2, Address3, City,
					Country, State, PostalCode, IsGuesProfile, IsAddMoreGuestInfo, IsSplitRes, rooms);
			if (Account.equalsIgnoreCase("") || Account.isEmpty()) {
				res.enter_PaymentDetails(driver, test_steps, PaymentType, CardNumber, NameOnCard, yearDate);
			}
			System.out.println(rooms);
			res.enter_MarketSegmentDetails(driver, test_steps, TravelAgent, MarketSegment, Referral);
			res.clickBookNow(driver, test_steps);
			reservation = res.get_ReservationConfirmationNumber(driver, test_steps);
			res.clickCloseReservationSavePopup(driver, test_steps);
			panelStatus = res.getPanelStatusStatusName(driver);
			panelGuestName = res.getPanelStatusGuestName(driver);
			roomNos = res.getStayInfoRoomNo(driver, test_steps);
			guestNames = res.getStayInfoGuestName(driver, test_steps);

		}


		catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Check In Policy
		try {
			test_steps.add("<b>****Start Verifying Check In Policy Exist or Not ****</b>");
			res.clickPolicyCollapseIcon(driver, test_steps, "Check In", "No");
			policyName = res.getPolicyfromDetailTab(driver, test_steps);
			if (!policyName.equalsIgnoreCase("No Policy")) 
			{
				test_steps.add("Policy Exist");
			}
			else
			{
				test_steps.add("Policy Doesn't Exist");
			}
			test_steps.add("<b>****Start CheckIn Reservation****</b>");
			res.clickCheckInAllButton(driver, test_steps);
			generateGuestStatementStatus = res.generatGuestReportToggle(driver, test_steps, IsGuesRegistration);
			buttonText = res.completeCheckInProcess(driver, test_steps);
			currentDate = res.getTodaysDate("MM/dd/yyyy", timeZone);
			previousDate = res.getPreviousDate("MM/dd/yyyy", timeZone);
			System.out.println(currentDate + "" + previousDate);

			if (!policyName.equalsIgnoreCase("No Policy")) {
				res.checkedOverRideCheckInAmountCheckBox(driver, test_steps, IsOverRideCheckIN);
				res.setAsMainPayment(driver, test_steps, PaymentType, SetPaymentMethod, currentDate, "Check In");
				checkInAmount = res.get_Checkin_Amount(driver, test_steps);
				paymentMethodOnCheckIn = res.getPaymentMethod(driver, test_steps);
				System.out.println("CheckIn Half Amount : " + checkInAmount);
				res.inputAmountWhileCheckINAndCheckOut(driver, test_steps, checkInAmount);
				res.selectType(driver, test_steps, TakePaymentType);
				// Added Notes and Click Log button
				res.addNotesAndClickLogORPayORAuthorizedButton(driver, test_steps, Notes);
				res.clickCloseButtonOfSuccessModelPopup(driver, test_steps);
			}

			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.getRoomsOnDetailPage(driver, newRooms);
				System.out.println(newRooms);
			}

			status = res.getPanelStatusStatusName(driver);
			test_steps.add("<b>****Start Verifying In-House Status****</b>");
			res.verifyReservationStatusStatus(driver, test_steps, "In-House");
			test_steps.add("<b>****Start Verifying Check-Out All Button ****</b>");
			res.verifyCheckOutAllButton(driver, test_steps);
			tripTotalAmounts = res.get_TripSummaryTripTotalChargesWithoutCurrency(driver, test_steps);
			System.out.println("Trip Aummary Total Amount : " + tripTotalAmounts);

			res.click_Folio(driver, test_steps);
			taxAmout = res.get_Taxes(driver, test_steps);
			System.out.println("Folio -Tax is : " + taxAmout);
			res.click_DeatilsTab(driver, test_steps);
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to CheckIn", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to CheckIN", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Set Property Option --Default to Generate Guest Statement on Check Out --Off
		try {
			test_steps.add(
					"<b>****Set Default to Generate Guest Statement on Check Out- OFF from Property Settings****</b>");
			nav.Setup(driver);
			test_steps.add("Navigated to Setup");
			nav.Properties(driver);
			properties.SearchProperty_Click(driver, propertyName, test_steps);
			test_steps.add("Search Property: <b>" + propertyName + "</b>");
			nav.click_PropertyOptions(driver, test_steps);
			isGuestStatementCheckBox = properties.Click_GenerateGuestStatementCheckBox(driver, test_steps, "No");
			properties.PublishButton(driver);
			test_steps.add("Click Publish Button");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e,
						"Failed to Disabled  Toggle Default to Generate Guest Statement on Check Out from Property Settings",
						testName, "Task Management", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e,
						"Failed to Disabled Toggle Default to Generate Guest Statement on Check Out from Property Settings",
						testName, "Task Management", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Logged Out Login Again
		try {
			Utility.logoutToInnCenter(driver, test_steps);
			login_CP(driver);
			test_steps.add("Logged into the application");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Logout Login", testName, "Task Management", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Logout Login", testName, "Task Management", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verified CheckOut Generate Guest Statement on Check Out --Off
		try {
			//revSearch.basicSearchWithResNumber(driver, reservation,test_steps);
			revSearch.basicSearch_WithResNumber(driver, reservation);
			test_steps.add("Search Reservation By Reservation Number <b>" + reservation + "<b>");
			app_logs.info("Search Reservation By Reservation Number " + reservation);
			test_steps.add("<b>****Verify Generate Guest Statement Toggle- OFF On CheckOut ****</b>");
			res.clickCheckOutAllButton(driver, test_steps);
			res.clickYesButtonOfCheckOutAllConfirmationMsg(driver, test_steps, Message, "CheckOutAll", "", "");
			res.verifyGenerateGuestReportToggle(driver, test_steps, isGuestStatementCheckBox);
			test_steps.add(
					"Verify the 'Generate Guest Statement' switcher is OFF in CHECK-OUT Modal If 'Default to Generate Statement' is UnChecked in the Property Settings"
							+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/682479' target='_blank'>"
							+ "Click here to open TestRail: C682479</a><br>");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Generate Guest Statement OFF", testName, "Task Management",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Generate Guest Statement OFF", testName, "Task Management",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Set Property Option --Default to Generate Guest Statement on Check Out --On
		try {
			test_steps.add(
					"<b>****Set Default to Generate Guest Statement on Check Out- ON from Property Settings****</b>");
			nav.Setup(driver);
			test_steps.add("Navigated to Setup");
			nav.Properties(driver);
			properties.SearchProperty_Click(driver, propertyName, test_steps);
			test_steps.add("Search Property: <b>" + propertyName + "</b>");
			nav.click_PropertyOptions(driver, test_steps);
			isGuestStatementCheckBox = properties.Click_GenerateGuestStatementCheckBox(driver, test_steps, "Yes");
			properties.PublishButton(driver);
			test_steps.add("Click Publish Button");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e,
						"Failed to Enabled Toggle Default to Generate Guest Statement on Check Out from Property Settings",
						testName, "Task Management", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e,
						"Failed to Enabled Toggle Default to Generate Guest Statement on Check Out from Property Settings",
						testName, "Task Management", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Logged Out Login Again
		try {
			Utility.logoutToInnCenter(driver, test_steps);
			login_CP(driver);
			test_steps.add("Logged into the application");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Logout Login", testName, "Task Management", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Logout Login", testName, "Task Management", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verified CheckOut Generate Guest Statement on Check Out --On
		try {
			//revSearch.basicSearchWithResNumber(driver, reservation,test_steps);
			revSearch.basicSearch_WithResNumber(driver, reservation);
			test_steps.add("Search Reservation By Reservation Number <b>" + reservation + "<b>");
			app_logs.info("Search Reservation By Reservation Number " + reservation);
			// get balance
			String balance = res.get_TripSummaryBalance_Amount(driver, test_steps);
			double balanceOne = Double.valueOf(balance);
			checkoutBalance = (int) balanceOne;
			test_steps.add("Get Balance Before Check Out <b>" + checkoutBalance + "</b>");
			app_logs.info(checkoutBalance);
			test_steps.add("<b>****Verify Generate Guest Statement Toggle- ON On CheckOut ****</b>");
			res.clickCheckOutAllButton(driver, test_steps);
			res.clickYesButtonOfCheckOutAllConfirmationMsg(driver, test_steps, Message, "CheckOutAll", "", "");
			res.verifyGenerateGuestReportToggle(driver, test_steps, isGuestStatementCheckBox);
			test_steps.add(
					"Verify the 'Generate Guest Statement' switcher is ON in CHECK-OUT Modal If 'Default to Generate Statement' is Checked in the Property Settings"
							+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/682479' target='_blank'>"
							+ "Click here to open TestRail: C682479</a><br>");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Generate Guest Statement ON", testName, "Task Management",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Generate Guest Statement ON", testName, "Task Management",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// CheckOUt Reservation
		try {
			test_steps.add("<b>****Check-Out All Window****</b>");
			res.verifyReservationPopWindow(driver, "Check Out", panelGuestName, "In-House", reservation, test_steps,
					"Check Out");
			windowBefore = res.getMainWindow(driver, test_steps);
			generateGuestStatementStatus = res.generatGuestReportToggle(driver, test_steps, "Yes");
			res.verifyMessageOfCheckOutWindow(driver, test_steps,
					"Are you sure you want to check out this reservation?");
			res.clickCheckOutButton(driver, test_steps, checkoutBalance);
			int TotalAmountPaid = 0;
			if (!policyName.equalsIgnoreCase("No Policy")) {
				TotalAmountPaid = halfAmountPaid + Integer.parseInt(checkInAmount);
				System.out.println("Total Amount Paid : " + TotalAmountPaid);
			} else {
				TotalAmountPaid = halfAmountPaid;
				System.out.println("Total Amount Paid : " + TotalAmountPaid);
			}
			
			double tripAmount = Double.valueOf(tripTotalAmounts);
			int amt = (int) tripAmount;
			String tripAmountRemaining = String.valueOf(amt - TotalAmountPaid);
			System.out.println("Total Amount Remaining : " + tripAmountRemaining);

			String Balance = null, tripPaid = null;

			if (checkoutBalance > 0) {
				test_steps.add("<b>****Start Verifying Check-Out Payment Widnow ****</b>");

				if (SetPaymentMethod.equals(paymentMethodOnCheckIn)) {
					checkOutAmount = res.verifyCheckOutPaymentWindow(driver, test_steps, "Check Out Payment",
							SetPaymentMethod, TakePaymentType, tripAmountRemaining, "5454", NameOnCard, yearDate);
					res.setAsMainPayment(driver, test_steps, SetPaymentMethod, SetPaymentMethod, previousDate,
							"Check Out");
				} else {

					checkOutAmount = res.verifyCheckOutPaymentWindow(driver, test_steps, "Check Out Payment",
							PaymentType, TakePaymentType, tripAmountRemaining, "5454", NameOnCard, yearDate);

					// Change Payment Method and Set as Main Payment
					res.setAsMainPayment(driver, test_steps, PaymentType, SetPaymentMethod, previousDate, "Check Out");
				}
				paymentMethod = res.getPaymentMethod(driver, test_steps);
				// Added Notes and Click Log button
				res.addNotesAndClickLogORPayORAuthorizedButton(driver, test_steps, CheckOutNote);
				int totalpaidAfterCheckout = TotalAmountPaid + Integer.parseInt(checkOutAmount);
				Balance = String.valueOf(amt - totalpaidAfterCheckout);
				System.out.println("Total Balance Amount  : " + Balance);
				res.switchToTabOne(driver, test_steps);
				test_steps.add("<b>****Start Verifying Check-Out Success Widnow ****</b>");

				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
				String pastDate = dateFormat.format(previousDate);
				System.out.println("Previous Date: " + pastDate);
				res.commonMethodForSuccessfullWindowVerification(driver, test_steps, "Check out Successful", "Approved",
						SetPaymentMethod, CheckOutNote, Balance, TakePaymentType, tripAmountRemaining, "Check Out",
						pastDate);

				res.switchToTabOne(driver, test_steps);
				res.clickCloseButtonOfSuccessModelPopup(driver, test_steps);

				tripPaid = String.valueOf(totalpaidAfterCheckout);
				System.out.println("Trip Total Amount Calculated : " + tripPaid);
			} else {
				res.switchToTabOne(driver, test_steps);
				Balance = String.valueOf((Integer.parseInt(tripAmountRemaining)));
				System.out.println("Total Balance Amount  : " + Balance);

			}

			res.switchToTabOne(driver, test_steps);
			test_steps.add("<b>****Start Verifying Roll Back Button ****</b>");
			res.verifyRollBackButton(driver, test_steps);
			test_steps.add("<b>****Start Verifying DEPARTED Status****</b>");
			res.verifyReservationStatusStatus(driver, test_steps, "DEPARTED");

			// Verified Trip Summary Total
			String tripSummaryTotal = res.get_TripSummaryTripTotalChargesWithoutCurrency(driver, test_steps);
			System.out.println("Trip Summary Total: " + tripSummaryTotal);

			String tripSummaryPaidAgain = res.getTripSummaryPaidAmount(driver, test_steps);
			System.out.println("Trip Summary Paid: " + tripSummaryPaidAgain);
			res.verifyTripSummaryPaid(driver, test_steps, tripSummaryPaidAgain, tripPaid);

			String tripSummaryBalance = res.get_TripSummaryBalance_Amount(driver, test_steps);
			System.out.println("Trip Summary Balance: " + tripSummaryBalance);
			res.verifyTripSummaryBalance(driver, test_steps, tripSummaryBalance, Balance);

			test_steps.add("<b>****Start Verifying Balance****</b>");
			res.verifyReservationStatusBalance(driver, test_steps, Balance);

			if (!policyName.equalsIgnoreCase("No Policy")) {
				test_steps.add("<b>****Start Verifying Folio Details Line Item****</b>");
				res.click_Folio(driver, test_steps);
				res.verifyFolioLineItem(driver, test_steps, previousDate, SetPaymentMethod, SetPaymentMethod,
						checkOutAmount);

			}
			String historyDesc = "Checked out this reservation";
			test_steps.add("<b>****Start Verifying History****</b>");
			// Click History Tab
			res.click_History(driver, test_steps);
			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.verifyHistoryForCheckin(driver, test_steps, historyDesc, rc.Abbreviation.get(0).toString(),
						newRooms.get(0), "Check Out", roomAbbri, newRooms);

			} else {
				res.verifyHistoryForCheckin(driver, test_steps, historyDesc, rc.Abbreviation.get(0).toString(),
						res.primary, "Check Out", roomAbbri, rooms);
			}

			if (!policyName.equalsIgnoreCase("No Policy")) {
				String[] abbs = roomAbbri.get(0).split(":");
				String Finalabb = abbs[1].trim();
				System.out.println(" Room No: " + Finalabb);

				if (res.isRoomReserved || res.isRoomUnAssigned) {
					res.commonMethodToverifyHistoryTabDescriptionForCheckINCheckOut(driver, test_steps, checkOutAmount,
							"Check In", Finalabb, newRooms.get(0), paymentMethod);

				} else {
					res.commonMethodToverifyHistoryTabDescriptionForCheckINCheckOut(driver, test_steps, checkOutAmount,
							"Check In", Finalabb, rooms.get(0), paymentMethod);

				}

			}

			test_steps.add("<b>****Start Verifying Updated By on Payment Item Detail Popup****</b>");
			res.click_Folio(driver, test_steps);
			res.verifyPaymentDetailUpdateBy(driver, NameOnCard, "autouser", PaymentType);
			res.clickPaymentDetailCancel(driver, test_steps);
			test_steps.add("Successfully  Verified  Update By : <b>" + "autouser" + "</b> "
					+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/682506' target='_blank'>"
					+ "Click here to open TestRail: C682506</a> <br>");
			test_steps.add("<b>****Start Verifying Documents****</b>");
			res.click_Documents(driver, test_steps);
			res.verifyDocuments(driver, test_steps, reservation);
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Check Out ", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Check Out ", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verifying Report
		try {
			res.click_Folio(driver, test_steps);
			String totalCharges = res.get_Payments(driver, test_steps);
			test_steps.add("<b>****Start Verifying Guest Statement Report***</b>");
			res.switchToChildWindow(driver, test_steps);
			reportContant = report.verifyGuestStatementReport(driver, test_steps);

			if (generateGuestStatementStatus) {
				DateFormat dateFormat = new SimpleDateFormat("E, MMM dd, yyyy");
				dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
				// String myDate = dateFormat.format(res.current_Date);
				String myDate = dateFormat.format(currentDate);
				System.out.println(myDate);

				String[] name = guestNames.get(0).split(" ");
				String actualName = name[1] + " " + name[2];
				System.out.println(actualName);
				String[] name1 = guestNames.get(1).split(" ");
				String secondName = name1[1] + " " + name1[2];
				System.out.println(secondName);

				String RoomOne = roomNos.get(0);
				String RoomTwo = roomNos.get(1);
				System.out.println(" Room No: " + RoomOne);
				System.out.println(" Room Class: " + RoomTwo);

				List<String> Emails = Arrays.asList(Email.split("\\|"));

				report.Report_Verify_ReportContent(driver, reportContant, "Guest Statement", test_steps);
				test_steps.add("Report Name: <b>" + "Guest Statement" + "</b>");
				Utility.app_logs.info("Report Name: " + "Guest Statement");
				report.Report_Verify_ReportContent(driver, reportContant, "Guest Folio", test_steps);
				test_steps.add("Folio Name: <b>" + "Guest Folio" + "</b>");
				Utility.app_logs.info("Folio Name: " + "Guest Folio");
				report.Report_Verify_ReportContent(driver, reportContant, myDate, test_steps);
				test_steps.add("Date :  <b>" + myDate + "</b>");
				Utility.app_logs.info("Date :  " + myDate);
				report.Report_Verify_ReportContent(driver, reportContant, actualName, test_steps);
				test_steps.add("Primary Gust Name :  <b>" + actualName + "</b>");
				Utility.app_logs.info("Primary Gust Name  :  " + actualName);
				report.Report_Verify_ReportContent(driver, reportContant, Emails.get(0).toString(), test_steps);
				test_steps.add("Primary Email :  <b>" + Emails.get(0).toString() + "</b>");
				Utility.app_logs.info("Primary Email   :  " + Emails.get(0).toString());
				report.Report_Verify_ReportContent(driver, reportContant, Emails.get(1).toString(), test_steps);
				test_steps.add("Secondary Email :  <b>" + Emails.get(1).toString() + "</b>");
				Utility.app_logs.info("Secondary Email   :  " + Emails.get(1).toString());
				report.Report_Verify_ReportContent(driver, reportContant, RoomOne, test_steps);
				test_steps.add("Room One:  <b>" + RoomOne + "</b>");
				Utility.app_logs.info("Room  One:  " + RoomOne);
				report.Report_Verify_ReportContent(driver, reportContant, RoomTwo, test_steps);
				test_steps.add("Room No Two:  <b>" + RoomTwo + "</b>");
				Utility.app_logs.info("Room No Two:  " + RoomTwo);
				report.Report_Verify_ReportContent(driver, reportContant, secondName, test_steps);
				test_steps.add("Secondary Gust Name  :  <b>" + secondName + "</b>");
				Utility.app_logs.info("Secondary Gust Name   :  " + secondName);
				report.Report_Verify_ReportContent(driver, reportContant, reservation, test_steps);
				test_steps.add("Reservation #  :  <b>" + reservation + "</b>");
				Utility.app_logs.info("Reservation #  :  " + reservation);
				report.Report_Verify_ReportContent(driver, reportContant, totalCharges, test_steps);
				test_steps.add("Room Charges  :  <b>" + totalCharges + "</b>");
				Utility.app_logs.info("Room Charges   :  " + totalCharges);
				report.Report_Verify_ReportContent(driver, reportContant, totalCharges, test_steps);
				test_steps.add("Payment Method :  <b>" + SetPaymentMethod + "</b>");
				Utility.app_logs.info("Payment Method   :  " + SetPaymentMethod);
				test_steps.add("Verified The Generate Guest Statement Report Successfully"
						+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/682479' target='_blank'>"
						+ "Click here to open TestRail: C682479</a><br>");

			}
			Utility.deleteFile(Utility.fileName);
			test_steps.add("Deleted File  :  <b>" + Utility.fileName + "</b>");
			Utility.app_logs.info("Deleted File   :  " + Utility.fileName);

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Report ", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Report ", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {

		return Utility.getData("VerifyCheckOutAllCPReservationM", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}
}
