package com.innroad.inncenter.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Distribution;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Properties;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.RoomStatus;
import com.innroad.inncenter.properties.OR_Reservation;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_CPReservation;

public class VerifyGenerateGuestStatementswitcherOnInCheckIn extends TestCore {

	private WebDriver driver = null;
	public static String test_name = null;
	public static String test_nameOne = null;
	public static String test_description = null;
	public static String test_catagory = null;
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> roomAbbri = new ArrayList<String>();
	ArrayList<String> roomCost = new ArrayList<String>();
	ArrayList<String> rooms = new ArrayList<String>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	ArrayList<String> newRooms = new ArrayList<String>();
	Navigation nav = new Navigation();
	RoomStatus roomstatus = new RoomStatus();
	CPReservationPage res = new CPReservationPage();
	Properties properties = new Properties();
	ReservationSearch revSearch = new ReservationSearch();
	Distribution distrubution = new Distribution();
	RoomClass rc = new RoomClass();

	String testName = null;
	Double depositAmount = 0.0;
	String reservation = null, status = null, yearDate = null, propertyName = null, policyName = null, timeZone = null;
	boolean isGuestRegistrationCheckBox = false;
	Date currentDate = null, previousDate = null;
	int checkoutBalance = 0;

	@BeforeTest(alwaysRun = true)

	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void verifyGenerateGuestStatementSwitcherOn(String URL, String ClientId, String UserName, String Password,
			String RoomClass, String CheckInDate, String CheckOutDate, String Adults, String Children, String Rateplan,
			String PromoCode, String IsSplitRes, String IsAssign, String Account, String Salutation,
			String GuestFirstName, String GuestLastName, String PhoneNumber, String AltenativePhone, String Email,
			String AccountType, String Address1, String Address2, String Address3, String City, String Country,
			String State, String PostalCode, String IsGuesProfile, String IsAddMoreGuestInfo, String PaymentType,
			String CardNumber, String NameOnCard, String TravelAgent, String MarketSegment, String Referral,
			String ChannelName, String Notes, String CheckOutNote) {
		test_name = "VerifyGenerateGuestRegistrationSwitcherOnInCheckIn";
		test_description = "Verify Generate 'Generate Guest Registration Switcher' While CHECK-IN And Verify Roll Back For DEPARTED Status <br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682720' target='_blank'>"
				+ "Click here to open TestRail: C682720</a><br>";
		test_catagory = "CPReservation_CheckIN";
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
		// Create New Reservation
		try {
			test_steps.add("<b>****Start Creating New Reservation****</b>");
			res.click_NewReservation(driver, test_steps);
			res.select_Dates(driver, test_steps, CheckInDate, CheckOutDate, Adults, Children, Rateplan, PromoCode,
					IsSplitRes);
			res.clickOnFindRooms(driver, test_steps);
			roomCost = res.select_MRBRooms(driver, test_steps, RoomClass, IsAssign, Account);
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

		// Set Property Option --Default to Generate Guest Registration on Check Out --Off
		try {
			test_steps.add(
					"<b>****Set Default to Generate Guest Registration Check In - OFF from Property Settings****</b>");
			nav.Setup(driver);
			test_steps.add("Navigated to Setup");
			nav.Properties(driver);
			nav.open_Property(driver, test_steps, propertyName);
			test_steps.add("Search Property: <b>" + propertyName + "</b>");
			Wait.explicit_wait_absenceofelement(OR_Reservation.Spinner, driver);
			nav.click_PropertyOptions(driver, test_steps);	
			nav.click_PropertyOptions(driver, test_steps);
			isGuestRegistrationCheckBox = properties.clickGenerateGuestRegistrationCheckBoxOnCheckIn(driver, test_steps, "No");
			properties.PublishButton(driver);
			test_steps.add("Click Publish Button");

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Disabled Generate Guest Registration  Check In - OFF", testName,
						"Properties", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Disabled Generate Guest Registration  Check In - OFF", testName,
						"Properties", driver);
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
				Utility.updateReport(e, "Failed to Logout Login", testName, "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Logout Login", testName, "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Verify Generate Guest Registration Check Box On
		try {
			test_steps.add("<b>****Search Reservation****</b>");
			revSearch.basicSearch_WithResNumber(driver, reservation);
			test_steps.add("Search Reservation By Reservation Number <b>" + reservation + "<b>");
			app_logs.info("Search Reservation By Reservation Number " + reservation);
			test_steps.add("<b>****Start CheckIn Reservation****</b>");
			res.clickCheckInAllButton(driver, test_steps);
			test_steps.add("<b>****Verify Generate Guest Registration Toggle- OFF On Check-In ****</b>");
			res.verifyGenerateGuestReportToggle(driver, test_steps, isGuestRegistrationCheckBox);
			test_steps.add(
					"Verify the 'Generate Guest Registration' switcher is OFF in CHECK-IN Modal If 'Default to Generate Guest Registration' is UnChecked in the Property Settings"
							+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/682720' target='_blank'>"
							+ "Click here to open TestRail: C682720</a><br>");

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Generate Guest Registration on Check In - OFF", testName,
						"Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Generate Guest Registration on Check In - OFF", testName,
						"Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Set Property Option --Default to Generate Guest Registration Check In --On
		try {
			test_steps.add(
					"<b>****Set Default to Generate Guest Registration Check In- ON from Property Settings****</b>");
			nav.Setup(driver);
			test_steps.add("Navigated to Setup");
			nav.Properties(driver);
			properties.SearchProperty_Click(driver, propertyName, test_steps);
			test_steps.add("Search Property: <b>" + propertyName + "</b>");
			Wait.explicit_wait_absenceofelement(OR_Reservation.Spinner, driver);
			nav.click_PropertyOptions(driver, test_steps);
			isGuestRegistrationCheckBox = properties.clickGenerateGuestRegistrationCheckBoxOnCheckIn(driver, test_steps, "Yes");
			properties.PublishButton(driver);
			test_steps.add("Click Publish Button");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e,
						"Failed to Enabled Toggle Default to Generate Guest Registration Check In ON from Property Settings",
						testName, "Properties", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e,
						"Failed to Enabled Toggle Default to Generate Guest Registration Check In ON  from Property Settings",
						testName, "Properties", driver);
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
				Utility.updateReport(e, "Failed to Logout Login", testName, "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Logout Login", testName, "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verified CheckOut Generate Guest Statement on Check Out --On
		try {
			test_steps.add("<b>****Search Reservation****</b>");
			revSearch.basicSearch_WithResNumber(driver, reservation);
			test_steps.add("Search Reservation By Reservation Number <b>" + reservation + "<b>");
			app_logs.info("Search Reservation By Reservation Number " + reservation);
			test_steps.add("<b>****Start CheckIn Reservation****</b>");

			res.clickCheckInAllButton(driver, test_steps);
			test_steps.add("<b>****Verify Generate Guest Registration Toggle- ON in Check-In ****</b>");
			res.verifyGenerateGuestReportToggle(driver, test_steps, isGuestRegistrationCheckBox);
			test_steps.add(
					"Verify the 'Generate Guest Registration' switcher is ON in CHECK-IN Modal If 'Default to Generate Guest Registration' is Checked in the Property Settings"
							+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/682720' target='_blank'>"
							+ "Click here to open TestRail: C682720</a><br>");
			

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Generate Guest Statement ON", testName, "Reservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Generate Guest Statement ON", testName, "Reservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		
		
		
	}

	@DataProvider
	public Object[][] getData() {

		return Utility.getData("VerifyGGRegistrationCheckInOn", excel);

	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
