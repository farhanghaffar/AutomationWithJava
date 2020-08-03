package com.innroad.inncenter.tests;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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
import com.innroad.inncenter.pageobjects.Policies;
import com.innroad.inncenter.pageobjects.Properties;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.properties.OR_Reservation;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.WebElements_Policies;

public class VerifyRollBackNoShowCancelInHouseDepartedStatus extends TestCore {
	private WebDriver driver = null;
	public static String test_name = null;
	public static String test_nameOne = null;
	public static String test_description = null;
	public static String test_catagory = null;
	ArrayList<String> teststeps = new ArrayList<>();
	ArrayList<String> roomAbbri = new ArrayList<String>();
	ArrayList<String> roomCost = new ArrayList<String>();
	ArrayList<String> rooms = new ArrayList<String>();
	ArrayList<String> newRooms = new ArrayList<String>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	Navigation nav = new Navigation();
	CPReservationPage res = new CPReservationPage();
	Distribution distrubution = new Distribution();
	Policies policies = new Policies();
	Properties properties = new Properties();
	RoomClass rc = new RoomClass();
	String testName = null;
	String reservation = null, status = null, yearDate = null;
	String policyname = null, paymentMethod = null, timeZone = null, propertyName = null, policyName = null,
			getNoShowAmount = null, getCancelAmount = null, getCheckInAmount = null;
	Date currentDate = null, previousDate = null;
	public static Logger rollBack = Logger.getLogger("VerifyRollBackNoShowCancelInHouseDepartedStatus");
	int checkoutBalance = 0;
	String loading = "(//div[@class='ir-loader-in'])";

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void verifyRollBackNoShowCancelInHouseDepartedStatus(String URL, String ClientId, String UserName,
			String Password, String RoomClass, String CheckInDate, String CheckOutDate, String Adults, String Children,
			String Rateplan, String PromoCode, String IsSplitRes, String IsAssign, String Account, String Salutation,
			String GuestFirstName, String GuestLastName, String PhoneNumber, String AltenativePhone, String Email,
			String AccountType, String Address1, String Address2, String Address3, String City, String Country,
			String State, String PostalCode, String IsGuesProfile, String IsAddMoreGuestInfo, String PaymentType,
			String CardNumber, String NameOnCard, String TravelAgent, String MarketSegment, String Referral,
			String ChannelName, String Notes, String Reason, String PolicyName, String PolicyText, String PolicyDesc,
			String RoomChargesPercentage, String CheckOutNote, String PolicyArrtibute, String BeyondOption,
			String Daysofreservation) {
		test_name = "VerifyRollBackNoShowCancelInHouseDepartedStatus";
		test_description = "VerifyRollBackNoShowCancelInHouseDepartedStatusw<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682433' target='_blank'>"
				+ "Click here to open TestRail: C682433</a><br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682432' target='_blank'>"
				+ "Click here to open TestRail: C682432</a><br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682459' target='_blank'>"
				+ "Click here to open TestRail: C682459</a><br>";
		test_catagory = "CPReservation_RollBack";
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
			teststeps.add("Logged into the application");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
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
		// Get checkIN and Checkout Date
		try {
			if (!(Utility.validateInput(CheckInDate))) {
				for (int i = 0; i < GuestFirstName.split("\\|").length; i++) {
					checkInDates.add(Utility.getCurrentDate("dd/MM/yyyy"));
					checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(1), "MM/dd/yyyy", "dd/MM/yyyy"));
				}
			} else {
				checkInDates = Utility.splitInputData(CheckInDate);
				checkOutDates = Utility.splitInputData(CheckOutDate);
			}
			CheckInDate = checkInDates.get(0) + "|" + checkInDates.get(1);
			CheckOutDate = checkOutDates.get(0) + "|" + checkOutDates.get(1);
			app_logs.info(CheckInDate);
			app_logs.info(CheckOutDate);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to Get CheckIN CheckOutDate", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to Get CheckIN CheckOutDate", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Get Default Status of Channel
		try {
			teststeps.add("<b>****Getting Default Status of Distribution Channel****</b>");
			nav.Inventory(driver);
			teststeps.add("Navigated to Inventory");
			app_logs.info("Navigated to Inventory");
			nav.Distribution(driver);
			teststeps.add("Click on Distribution");
			app_logs.info("Click on Distribution");
			status = distrubution.getDefaultStatusOfChannel(driver, teststeps, ChannelName);
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to get room class abbrivations", testName, "Room class", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to get room class abbrivations", testName, "Room class", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Verify No Show Policy existing
		try {
			teststeps.add("<b>****Check No Show Policy if Already Exist****</b>");
			nav.Inventory(driver);
			nav.policies(driver);
			teststeps.add("Navigate To Policies Tab");
			policies.Select_PolicyType(driver, "No Show", teststeps);
			policies.ClickSearchPolicy(driver, teststeps);
			WebElements_Policies policiesElement = new WebElements_Policies(driver);
			boolean isPolicyGridExit = policiesElement.Policy_TableShow.isDisplayed();
			if (isPolicyGridExit) {
				teststeps.add("No Show Policies Exist ");
				app_logs.info("No Show Policies Exist");

			} else {
				teststeps.add("No Show Policy Doesn't Exist Create New Policy");
				app_logs.info("No Show Policy Doesn't Exist Create New Policy");
				policies.Select_PolicyType(driver, "All", teststeps);
				teststeps.add("<b>****Start Creating New Policy****</b>");
				policies.NewPolicybutton(driver, "No Show".toString(), teststeps);
				policyname = PolicyName + Utility.getTimeStamp();
				policies.Enter_Policy_Name(driver, policyname, teststeps);
				policies.Enter_Policy_Desc(driver, PolicyText, PolicyDesc);
				teststeps.add("Enter Policy Text and Description: <b>" + PolicyText + " AND " + PolicyDesc + "</b>");
				policies.Enter_NoShow_policy_Attributes_RC_Percentage(driver, RoomChargesPercentage);
				teststeps.add("Enter Room Charges Percentages:<b> " + RoomChargesPercentage + "</b>");
				policies.Associate_Sources(driver);
				teststeps.add(" Associate Sources");
				policies.Associate_Seasons(driver);
				teststeps.add(" Associate Seasons");
				policies.Associate_RoomClasses(driver);
				teststeps.add(" Associate Room Classes");
				policies.Associate_RatePlans(driver);
				teststeps.add(" Associate Rate Plans");
				policies.Save_Policy(driver);
				teststeps.add("Click Save Button");
				teststeps.add("Policy Saved Successfully: <b>" + policyname + "</b>");

			}

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to to Verify Policy", testName, "Room class", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to Verify Policy", testName, "Room class", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verify Cancel policy Exist
		try {
			teststeps.add("<b>****Check  Cancel Policy if Already Exist****</b>");
			nav.Inventory(driver);
			nav.policies(driver);
			teststeps.add("Navigate To Policies Tab");
			policies.Select_PolicyType(driver, "Cancellation", teststeps);
			policies.ClickSearchPolicy(driver, teststeps);
			WebElements_Policies policiesElement = new WebElements_Policies(driver);
			boolean isPolicyGridExit = policiesElement.Policy_TableShow.isDisplayed();
			if (isPolicyGridExit) {
				teststeps.add("Cancellation Policies Exist ");
				app_logs.info("Cancellation Policies Exist");

			} else {
				teststeps.add("Cancellation Policy Doesn't Exist Create New Policy");
				app_logs.info("Cancellation Policy Doesn't Exist Create New Policy");
				policies.Select_PolicyType(driver, "All", teststeps);
				teststeps.add("<b>****Start Creating New Policy****</b>");
				policies.NewPolicybutton(driver, "Cancellation", teststeps);
				policyname = PolicyName + Utility.getTimeStamp();
				policies.Enter_Policy_Name(driver, policyname, teststeps);
				policies.Enter_Policy_Desc(driver, PolicyText, PolicyDesc);
				teststeps.add("Enter Policy Text and Description: <b>" + PolicyText + " AND " + PolicyDesc + "</b>");
				policies.Cancellation_policy_Attributes(driver, PolicyArrtibute, RoomChargesPercentage, BeyondOption,
						Daysofreservation, teststeps);
				policies.Associate_Sources(driver);
				teststeps.add(" Associate Sources");
				policies.Associate_Seasons(driver);
				teststeps.add(" Associate Seasons");
				policies.Associate_RoomClasses(driver);
				teststeps.add(" Associate Room Classes");
				policies.Associate_RatePlans(driver);
				teststeps.add(" Associate Rate Plans");
				policies.Save_Policy(driver);
				teststeps.add("Click Save Button");
				teststeps.add("Policy Saved Successfully: <b>" + policyname + "</b>");
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to to Verify Policy", testName, "Room class", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to Verify Policy", testName, "Room class", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Get Time Zone and Property Name

		try {
			propertyName = properties.getProperty(driver, teststeps);
			nav.Setup(driver);
			nav.Properties(driver);
			nav.open_Property(driver, teststeps, propertyName);
			nav.click_PropertyOptions(driver, teststeps);
			timeZone = nav.get_Property_TimeZone(driver);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Get Abbreviations
		try {
			nav.RoomClass(driver);
			teststeps.add("<b>****Getting Abbreviations****</b>");
			rc.getRoomClassAbbrivations(driver, teststeps, roomAbbri, RoomClass);
			System.out.println(roomAbbri);
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to get room class abbrivations", testName, "Room class", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to get room class abbrivations", testName, "Room class", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Create New Reservation
		try {
			nav.Reservation_Backward_1(driver);
			teststeps.add("<b>****Start Creating New Reservation****</b>");
			res.click_NewReservation(driver, teststeps);
			res.select_Dates(driver, teststeps, CheckInDate, CheckOutDate, Adults, Children, Rateplan, PromoCode,
					IsSplitRes);
			res.clickOnFindRooms(driver, teststeps);
			res.select_MRBRooms(driver, teststeps, RoomClass, IsAssign, Account);
			res.clickNext(driver, teststeps);
			yearDate = Utility.getFutureMonthAndYearForMasterCard(driver, teststeps);
			Random random = new Random();
			int x = random.nextInt(900);
			res.enter_MRB_MailingAddressForMRB(driver, teststeps, Salutation, GuestFirstName + x, GuestLastName + x,
					PhoneNumber, AltenativePhone, Email, Account, AccountType, Address1, Address2, Address3, City,
					Country, State, PostalCode, IsGuesProfile, IsAddMoreGuestInfo, IsSplitRes, rooms);
			if (Account.equalsIgnoreCase("") || Account.isEmpty()) {
				res.enter_PaymentDetails(driver, teststeps, PaymentType, CardNumber, NameOnCard, yearDate);
			}
			System.out.println(rooms);
			res.enter_MarketSegmentDetails(driver, teststeps, TravelAgent, MarketSegment, Referral);
			res.clickBookNow(driver, teststeps);
			reservation = res.get_ReservationConfirmationNumber(driver, teststeps);
			res.clickCloseReservationSavePopup(driver, teststeps);
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// No Show Reservation
		try {
			currentDate = res.getTodaysDate("MM/dd/yyyy", timeZone);
			previousDate = res.getPreviousDate("MM/dd/yyyy", timeZone);
			String tripSummaryTotal = null, tripSummaryPaid = null, tripSummaryBalance = null,
					tripSummaryRoomCharges = null, tripSummaryIncidentals = null, tripSummaryTaxAndServices = null;
			double tripSummaryTotalAmount = 0.00, tripSummaryPaidAmount = 0.00, tripSummaryBalanceAmount = 0.00,
					tripSummaryRoomChargesAmount = 0.00, tripSummaryIncidentalsAmount = 0.00,
					tripSummaryTaxAndServicesAmount = 0.00;

			teststeps.add("<b>===========================Amount Before RollBack===========================</b>");

			// Get Amount of Trip Summary
			tripSummaryTotal = res.get_TripSummaryTripTotalChargesWithoutCurrency(driver, teststeps);
			tripSummaryTotalAmount = Double.valueOf(tripSummaryTotal);
			tripSummaryTotal = String.format("%.2f",tripSummaryTotalAmount);
			rollBack.info("Trip Summary Total: " + tripSummaryTotal);

			tripSummaryPaid = res.getTripSummaryPaidAmount(driver, teststeps);
			tripSummaryPaidAmount = Double.valueOf(tripSummaryPaid);
			tripSummaryPaid = String.format("%.2f",tripSummaryPaidAmount);
			rollBack.info("Trip Summary Paid: " + tripSummaryPaid);

			tripSummaryBalance = res.get_TripSummaryBalance_Amount(driver, teststeps);
			tripSummaryBalanceAmount = Double.valueOf(tripSummaryBalance);
			tripSummaryBalance = String.format("%.2f",tripSummaryBalanceAmount);
			rollBack.info("Trip Summary Balance: " + tripSummaryBalance);

			tripSummaryRoomCharges = res.get_TripSummaryRoomChargesWithoutCurrency(driver, teststeps);
			tripSummaryRoomChargesAmount = Double.valueOf(tripSummaryRoomCharges);
			tripSummaryRoomCharges = String.format("%.2f",tripSummaryRoomChargesAmount);
			rollBack.info("Trip Summary Room Charges: " + tripSummaryRoomCharges);

			tripSummaryIncidentals = res.get_TripSummaryInceidentalsWithoutCurrency(driver, teststeps);
			tripSummaryIncidentalsAmount = Double.valueOf(tripSummaryIncidentals);
			tripSummaryIncidentals = String.format("%.2f",tripSummaryIncidentalsAmount);
			rollBack.info("Trip Summary Incidentals: " + tripSummaryIncidentals);

			tripSummaryTaxAndServices = res.get_TripSummaryTaxesWithoutCurrency(driver, teststeps);
			tripSummaryTaxAndServicesAmount = Double.valueOf(tripSummaryTaxAndServices);
			tripSummaryTaxAndServices = String.format("%.2f",tripSummaryTaxAndServicesAmount);
			rollBack.info("Trip Summary Tax & Services: " + tripSummaryTaxAndServices);

			teststeps.add("<b>===========================Start No Show The Reservation===========================</b>");

			// Select NO SHOW
			res.reservationStatusPanelSelectStatus(driver, "No Show", teststeps);
			res.CompleteNoShowProcess(driver, teststeps);
			boolean isExist = res.verifyConfirmYesButton(driver);
			app_logs.info("Confirm Message Displayed or Not : " + isExist);
			if (isExist) {
				res.clickYesButtonOfConfirmTheNoShowProcessPopupModel(driver, teststeps);
			}
			  boolean isSuccessExist = res.verifyNoShowSuccessFull(driver); 
			  app_logs.info("Confirm Success Window Displayed or Not : " + isSuccessExist);
			  if(isSuccessExist) 
			  { res.clickCloseButtonOfSuccessModelPopup(driver,
			  teststeps); 
			  }
			 

			boolean isNoShowWPaymentExist = res.getNoShowHeaderWindow(driver);
			app_logs.info("Payment Window Displayed or Not : " + isNoShowWPaymentExist);
			if (isNoShowWPaymentExist) {
				paymentMethod = res.getPaymentMethod(driver, teststeps);
				getNoShowAmount = res.getAmountFromPaymentVerificationPage(driver);
				rollBack.info("No Show Paid: " + getNoShowAmount);
				res.addNotesAndClickLogORPayORAuthorizedButton(driver, teststeps, Notes);
				// Click Close button of No Show Successful Window
				res.clickCloseButtonOfSuccessModelPopup(driver, teststeps);

				double balance = tripSummaryBalanceAmount - Double.valueOf(getNoShowAmount);							
				tripSummaryBalance = String.format("%.2f",balance);
				rollBack.info("Trip Summary Balance After Now Show: " + tripSummaryBalance);	
				tripSummaryPaid = getNoShowAmount;
				rollBack.info("Trip Summary Paid After Now Show: " + tripSummaryPaid);

			}

			teststeps.add("<b>****Start Verifying Roll Back Button ****</b>");
			// Verified Roll Back Button Enabled
			res.verifyRollBackButton(driver, teststeps);
			teststeps.add("<b>****Start Verifying No Show Status****</b>");
			res.verifyReservationStatusStatus(driver, teststeps, "NO SHOW");

			if (isNoShowWPaymentExist) 
			{
			teststeps.add("<b>****Start Verifying No Show Fee in Folio****</b>");
			res.click_Folio(driver, teststeps);
			String roomCharges = res.getFolioRoomCharges(driver);
			String tax = res.getFolioTaxServices(driver);
			double amount = Double.valueOf(roomCharges) + Double.valueOf(tax);
			String getAmount = String.format("%.2f", amount);
			app_logs.info(getAmount);
			res.click_FolioDetail_DropDownBox(driver, teststeps);
			String[] abb = roomAbbri.get(0).split(":");
			String finalAbb = abb[1].trim();
			System.out.println(" Room No: " + finalAbb);
			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.clickFolioDetailOptionValue(driver, teststeps, finalAbb, rooms.get(0));
			} else {
				res.clickFolioDetailOptionValue(driver, teststeps, finalAbb, rooms.get(0));
			}
			Date date=new SimpleDateFormat("dd/MM/yyyy").parse(checkInDates.get(0));  
			app_logs.info(date);
			
			res.verifyFolioLineItem(driver, teststeps, date, "No Show Fee", "No Show Fee", getAmount);

			res.click_FolioDetail_DropDownBox(driver, teststeps);
			String[] abbOne = roomAbbri.get(1).split(":");
			String finalAbbOne = abbOne[1].trim();
			System.out.println(" Room No: " + finalAbb);
			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.clickFolioDetailOptionValue(driver, teststeps, finalAbbOne, rooms.get(1));
			} else {
				res.clickFolioDetailOptionValue(driver, teststeps, finalAbbOne, rooms.get(1));
			}

			String roomChargesSecondRoom = res.getFolioRoomCharges(driver);
			String taxRoom = res.getFolioTaxServices(driver);
			double amountSecondRoom = Double.valueOf(roomChargesSecondRoom) + Double.valueOf(taxRoom);
			String getAmountSecondRoom = String.format("%.2f", amountSecondRoom);
			app_logs.info(getAmountSecondRoom);				
			res.verifyFolioLineItem(driver, teststeps, date, "No Show Fee", "No Show Fee", getAmountSecondRoom);
			}
			
			teststeps.add("<b>****Start Verifying Rollback****</b>");
			res.clickRollBackButton(driver, teststeps);
			res.verifyRollBackMsg(driver, teststeps, "Are you sure you want to re-open this reservation?");
			res.clickYesButtonRollBackMsg(driver, teststeps);
			String statusPath = "//span[@class='ng-status'][contains(text(),'" + status + "')]";
			Wait.explicit_wait_absenceofelement(loading, driver);
			Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(statusPath)), driver);
			teststeps.add("<b>****Start Verifying Check In  Button ****</b>");
			res.verifyCheckInAllButton(driver, teststeps);
			teststeps.add("<b>****Start Verifying RESERVED Status****</b>");
			res.verifyReservationStatusStatus(driver, teststeps, status);

			teststeps.add(
					"<b>===========================Start Verifying Amount After RollBack For No Show===========================</b>");

			teststeps.add("<b>****Start Verifying Reservation Panel Balance****</b>");
			res.verifyReservationPanelBalance(driver, teststeps, tripSummaryBalance);

			teststeps.add("<b>****Start Verifying Trip Summary****</b>");
			String tripSummaryPaidAfterRollBack = res.getTripSummaryPaidAmount(driver, teststeps);
			rollBack.info("Trip Summary Paid: " + tripSummaryPaidAfterRollBack);
			rollBack.info(tripSummaryPaid);
			res.verifyTripSummaryPaidAmount(driver, teststeps, tripSummaryPaidAfterRollBack, tripSummaryPaid);

			String tripSummaryBalanceAfterRollBack = res.get_TripSummaryBalance_Amount(driver, teststeps);
			rollBack.info("Trip Summary Balance: " + tripSummaryBalanceAfterRollBack);
			res.verifyTripSummaryBalanceAmount(driver, teststeps, tripSummaryBalanceAfterRollBack, tripSummaryBalance);

			if (isNoShowWPaymentExist) 
			{
			teststeps.add("<b>****Start Verifying No Show Fee in Folio After Roll Back For No Show ****</b>");
			res.click_Folio(driver, teststeps);
			res.checkedDisplayVoidItem(driver, teststeps);
			for (int i = 0; i < roomAbbri.size(); i++) {
				res.click_FolioDetail_DropDownBox(driver, teststeps);
				String[] abbrivation = roomAbbri.get(i).split(":");
				String finalAbbrivation = abbrivation[1].trim();
				System.out.println(" Room No: " + finalAbbrivation);
				if (res.isRoomReserved || res.isRoomUnAssigned) {
					res.clickFolioDetailOptionValue(driver, teststeps, finalAbbrivation, rooms.get(i));
				} else {
					res.clickFolioDetailOptionValue(driver, teststeps, finalAbbrivation, rooms.get(i));
				}
				
				Date date=new SimpleDateFormat("dd/MM/yyyy").parse(checkInDates.get(0));  
				app_logs.info(date);
				res.verifyFolioLineItem(driver, teststeps, date, "No Show Fee", "No Show Fee", "0");
			}
			}
			teststeps.add("<b>****Start Verifying History****</b>");
			// Click History Tab
			res.click_History(driver, teststeps);
			res.verifyHistoryTabDescriptionForRollBack(driver, teststeps);

			teststeps.add("<b>===========================Start Cancel The Reservation===========================</b>");
			// Select Cancellation
			res.reservationStatusPanelSelectStatus(driver, "Cancel", teststeps);
			res.addResonOnCancelModelPopup(driver, teststeps, Reason);
			res.CompleteCancelProcess(driver, teststeps);
			boolean isCancelReservationExist = res.getCancelReservationHeaderWindow(driver);
			if (isCancelReservationExist) {
				paymentMethod = res.getPaymentMethod(driver, teststeps);
				getCancelAmount = res.getAmountFromPaymentVerificationPage(driver);
				rollBack.info("Cancel Paid: " + getCancelAmount);
				res.addNotesAndClickLogORPayORAuthorizedButton(driver, teststeps, Notes);
				res.clickCloseButtonOfSuccessModelPopup(driver, teststeps);

				/*double balance = Double.valueOf(tripSummaryBalance) - Double.valueOf(getCancelAmount);
				tripSummaryBalance =String.format("%.2f",balance);*/
				
				double balance=Double.valueOf(tripSummaryBalanceAfterRollBack)+Double.valueOf(getCancelAmount);
				tripSummaryBalance=String.format("%.2f",balance);
				if (isNoShowWPaymentExist) 
				{
			//	tripSummaryPaid = String.format("%.2f",(Double.valueOf(getCancelAmount) + Double.valueOf(getNoShowAmount)));
				tripSummaryPaid = String.format("%.2f",(Double.valueOf(getNoShowAmount) - Double.valueOf(getCancelAmount)));
				}
				else
				{
					tripSummaryPaid = String.format("%.2f",Double.valueOf(getCancelAmount));
					
				}
				rollBack.info("Trip Summary Balance After Cancel: " + tripSummaryBalance);
				rollBack.info("Trip Summary Paid  After Cancel: " + tripSummaryPaid);

			}

			teststeps.add("<b>****Start Verifying Roll Back Button ****</b>");
			// Verified Roll Back Button Enabled
			res.verifyRollBackButton(driver, teststeps);
			teststeps.add("<b>****Start Verifying Cancelled Status****</b>");
			res.verifyReservationStatusStatus(driver, teststeps, "Cancelled");

			if (isCancelReservationExist)
			{
			teststeps.add("<b>****Start Verifying Cancellation Fee in Folio****</b>");
			res.click_Folio(driver, teststeps);
			String roomChargesOne = res.getFolioRoomCharges(driver);
			String taxAmount = res.getFolioTaxServices(driver);
			double amountOne = Double.valueOf(roomChargesOne) + Double.valueOf(taxAmount);
			String getCancelAmount = String.format("%.2f", amountOne);
			app_logs.info(getCancelAmount);
			res.click_FolioDetail_DropDownBox(driver, teststeps);
			String[] abbCancel = roomAbbri.get(0).split(":");
			String finalAbbCancel = abbCancel[1].trim();
			System.out.println(" Room No: " + finalAbbCancel);
			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.clickFolioDetailOptionValue(driver, teststeps, finalAbbCancel, rooms.get(0));
			} else {
				res.clickFolioDetailOptionValue(driver, teststeps, finalAbbCancel, rooms.get(0));
			}

		
			Date date=new SimpleDateFormat("dd/MM/yyyy").parse(checkInDates.get(0));  
			app_logs.info(date);
			
			res.verifyFolioLineItem(driver, teststeps, date, "Cancellation Fee", "Cancellation Fee",
					getCancelAmount);

			res.click_FolioDetail_DropDownBox(driver, teststeps);
			String[] abbCancelSecond = roomAbbri.get(1).split(":");
			String finalAbbCancelSecond = abbCancelSecond[1].trim();
			System.out.println(" Room No: " + finalAbbCancelSecond);
			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.clickFolioDetailOptionValue(driver, teststeps, finalAbbCancelSecond, rooms.get(1));
			} else {
				res.clickFolioDetailOptionValue(driver, teststeps, finalAbbCancelSecond, rooms.get(1));
			}

			String roomChargesSecond = res.getFolioRoomCharges(driver);
			String taxAmountSecond = res.getFolioTaxServices(driver);
			double amountSecond = Double.valueOf(roomChargesSecond) + Double.valueOf(taxAmountSecond);
			String getCancelAmountSecond = String.format("%.2f", amountSecond);
			app_logs.info(getCancelAmountSecond);
				res.verifyFolioLineItem(driver, teststeps, date, "Cancellation Fee", "Cancellation Fee",
					getCancelAmountSecond);

			}
			teststeps.add("<b>****Start Verifying Rollback****</b>");
			res.clickRollBackButton(driver, teststeps);
			res.verifyRollBackMsg(driver, teststeps, "Are you sure you want to re-open this reservation?");
			res.clickYesButtonRollBackMsg(driver, teststeps);
			Wait.explicit_wait_absenceofelement(loading, driver);
			Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(statusPath)), driver);
			teststeps.add("<b>****Start Verifying Check In  Button ****</b>");
			res.verifyCheckInAllButton(driver, teststeps);
			teststeps.add("<b>****Start Verifying RESERVED Status****</b>");
			res.verifyReservationStatusStatus(driver, teststeps, status);

			teststeps.add(
					"<b>============Start Verifying Amount After RollBack For Cancellation=============/b>");

			teststeps.add("<b>****Start Verifying Reservation Panel Balance****</b>");
			res.verifyReservationPanelBalance(driver, teststeps, tripSummaryBalance);

			teststeps.add("<b>****Start Verifying Trip Summary****</b>");

			String tripSummaryPaidForCancel = res.getTripSummaryPaidAmount(driver, teststeps);
			rollBack.info("Trip Summary Paid: " + tripSummaryPaidForCancel);
			res.verifyTripSummaryPaidAmount(driver, teststeps, tripSummaryPaidForCancel, tripSummaryPaid);

			String tripSummaryBalanceForCancel = res.get_TripSummaryBalance_Amount(driver, teststeps);
			rollBack.info("Trip Summary Balance: " + tripSummaryBalanceForCancel);
			res.verifyTripSummaryBalanceAmount(driver, teststeps, tripSummaryBalanceForCancel, tripSummaryBalance);

			if (isCancelReservationExist)
			{
			teststeps.add("<b>****Start Verifying Cancellation Fee in Folio After Roll Back****</b>");
			res.click_Folio(driver, teststeps);
			res.checkedDisplayVoidItem(driver, teststeps);
			for (int i = 0; i < roomAbbri.size(); i++) {
				res.click_FolioDetail_DropDownBox(driver, teststeps);
				String[] abbAfterRollBack = roomAbbri.get(i).split(":");
				String finalAbbAfterRollBack = abbAfterRollBack[1].trim();
				System.out.println(" Room No: " + finalAbbAfterRollBack);
				if (res.isRoomReserved || res.isRoomUnAssigned) {
					res.clickFolioDetailOptionValue(driver, teststeps, finalAbbAfterRollBack, rooms.get(i));
				} else {
					res.clickFolioDetailOptionValue(driver, teststeps, finalAbbAfterRollBack, rooms.get(i));
				}
				Date date=new SimpleDateFormat("dd/MM/yyyy").parse(checkInDates.get(0));  
				app_logs.info(date);
				res.verifyFolioLineItem(driver, teststeps, date, "Cancellation Fee", "Cancellation Fee", "0");

			}
			}
			teststeps.add("<b>****Start Verifying History****</b>");
			// Click History Tab
			res.click_History(driver, teststeps);
			res.verifyHistoryTabDescriptionForRollBack(driver, teststeps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to No Show Roll Back", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to No Show Roll Back", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		}

		// Start Check In
		try {
			res.click_DeatilsTab(driver, teststeps);
			teststeps.add("<b>****Start Verifying Check In Policy Exist or Not ****</b>");
			res.clickPolicyCollapseIcon(driver, teststeps, "Check In", "No");
			policyName = res.getPolicyfromDetailTab(driver, teststeps);
			if (!policyName.equalsIgnoreCase("No Policy")) {
				teststeps.add("Policy Exist");
			} else {
				teststeps.add("Policy Doesn't Exist");
			}

			teststeps.add("<b>****Start Check In****</b>");
			res.clickCheckInAllButton(driver, teststeps);
			res.generatGuestReportToggle(driver, teststeps, "No");
			res.completeCheckInProcess(driver, teststeps);

			if (!policyName.equalsIgnoreCase("No Policy")) {
				res.addNotesAndClickLogORPayORAuthorizedButton(driver, teststeps, Notes);
				res.clickCloseButtonOfSuccessModelPopup(driver, teststeps);
			}
			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.getRoomsOnDetailPage(driver, newRooms);
				System.out.println(newRooms);
			}
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to Start CheckIn", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to Start CheckIn", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Start Roll Back
		try {
			teststeps.add("<b>****Start Verifying Check Out All Button ****</b>");
			res.verifyCheckOutAllButton(driver, teststeps);
			teststeps.add("<b>****Start Verifying Stay Info Check Out  Button ****</b>");
			List<String> RoomClassOne = Arrays.asList(RoomClass.split("\\|"));
			for (int i = 0; i < RoomClassOne.size(); i++) {
				res.verifyStayInfoCheckOutButtonForMRB(driver, teststeps, RoomClassOne.get(i));
			}

			teststeps.add("<b>****Start Verifying IN-HOUSE Status****</b>");
			res.verifyReservationStatusStatus(driver, teststeps, "In-House");
			teststeps.add("<b>****Start Roll Back All****</b>");
			res.reservationStatusPanelSelectStatus(driver, "Rollback All", teststeps);
			res.verifyRollBackMsg(driver, teststeps, "Are you sure you want to re-open this reservation?");
			res.clickYesButtonRollBackMsg(driver, teststeps);
			Wait.waitTillElementDisplayed(driver, loading);
			teststeps.add("<b>****Start Verifying Check In  Button ****</b>");
			res.verifyCheckInAllButton(driver, teststeps);

			List<String> geRoomClass = Arrays.asList(RoomClass.split("\\|"));
			for (int i = 0; i < geRoomClass.size(); i++) {

				res.verifyStayInfoCheckINButtonForMRB(driver, teststeps, geRoomClass.get(i));
			}

			teststeps.add("<b>****Start Verifying RESERVED Status****</b>");
			res.verifyReservationStatusStatus(driver, teststeps, status);

			teststeps.add("<b>****Start Verifying History****</b>");
			String message = "Rolled back this reservation to Reserved";
			// Click History Tab
			res.click_History(driver, teststeps);
			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.verifyHistoryForCheckin(driver, teststeps, message, rc.Abbreviation.get(0).toString(),
						newRooms.get(0), "RollBack", roomAbbri, newRooms);

			} else {
				res.verifyHistoryForCheckin(driver, teststeps, message, rc.Abbreviation.get(0).toString(), res.primary,
						"RollBack", roomAbbri, rooms);
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to Roll Back All", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to Roll Back All", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Start Check In
		try {
			res.clickCheckInAllButton(driver, teststeps);
			teststeps.add("<b>****Start Check In****</b>");
			res.generatGuestReportToggle(driver, teststeps, "No");
			res.completeCheckInProcess(driver, teststeps);
			if (!policyName.equalsIgnoreCase("No Policy")) {
				res.addNotesAndClickLogORPayORAuthorizedButton(driver, teststeps, Notes);
				Wait.WaitForElement(driver, OR_Reservation.NoShowSuccess_ModelHeader);
				res.clickCloseButtonOfSuccessModelPopup(driver, teststeps);
			}
			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.getRoomsOnDetailPage(driver, newRooms);
				System.out.println(newRooms);
			}
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to Start CheckIn", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to Start CheckIn", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Start CheckOut
		try {
			res.click_DeatilsTab(driver, teststeps);
			// get balance
			String balance = res.get_TripSummaryBalance_Amount(driver, teststeps);
			double balanceOne = Double.valueOf(balance);
			checkoutBalance = (int) balanceOne;
			teststeps.add("Get Balance Before Check Out <b>" + checkoutBalance + "</b>");
			app_logs.info(checkoutBalance);

			teststeps.add("<b>****Start Check Out****</b>");
			res.clickCheckOutAllButton(driver, teststeps);
			String message = "Are you sure you want to check-out all rooms at once? The Primary Guest will be responsible for all remaining Guest Charges.";
			res.clickYesButtonOfCheckOutAllConfirmationMsg(driver, teststeps, message, "CheckOutAll", "", "");
			res.generatGuestReportToggle(driver, teststeps, "No");
			res.clickCheckOutButton(driver, teststeps, checkoutBalance);

			if (checkoutBalance > 0) {
				Wait.explicit_wait_absenceofelement(OR_Reservation.Spinner, driver);

				res.addNotesAndClickLogORPayORAuthorizedButton(driver, teststeps, CheckOutNote);
				Wait.WaitForElement(driver, OR_Reservation.NoShowSuccess_ModelHeader);
				res.clickCloseButtonOfSuccessModelPopup(driver, teststeps);

			}

			teststeps.add("<b>****Start Verifying Roll Back Button ****</b>");
			res.verifyRollBackButton(driver, teststeps);
			teststeps.add("<b>****Start Verifying DEPARTED Status****</b>");
			res.verifyReservationStatusStatus(driver, teststeps, "DEPARTED");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to Start CheckOut", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to Start CheckOut", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verifying Roll Back
		try {
			teststeps.add("<b>****Start Verifying Rollback****</b>");
			res.clickRollBackButton(driver, teststeps);
			String rollBackMsg = "Are you sure you want to re-open this reservation?";
			res.verifyRollBackMsg(driver, teststeps, rollBackMsg);
			res.clickCloseRollBackMsg(driver, teststeps);
			teststeps.add("Successfully Verified Message <b>" + rollBackMsg + "</b>and Close Roll Back Popup"
					+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/682432' target='_blank'>"
					+ "Click here to open TestRail: C682432</a> <br>");
			res.clickRollBackButton(driver, teststeps);
			res.clickYesButtonRollBackMsg(driver, teststeps);
			Wait.waitTillElementDisplayed(driver, loading);
			teststeps.add("<b>****Start Verifying Check In  Button ****</b>");
			res.verifyCheckInAllButton(driver, teststeps);

			List<String> roomClassOne = Arrays.asList(RoomClass.split("\\|"));
			for (int i = 0; i < roomClassOne.size(); i++) {

				res.verifyStayInfoCheckINButtonForMRB(driver, teststeps, roomClassOne.get(i));
			}

			teststeps.add("<b>****Start Verifying RESERVED Status****</b>");
			res.verifyReservationStatusStatus(driver, teststeps, status);

			teststeps.add("<b>****Start Verifying History****</b>");
			String historyDesc = "Rolled back this reservation to Reserved";
			// Click History Tab
			res.click_History(driver, teststeps);
			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.verifyHistoryForCheckin(driver, teststeps, historyDesc, rc.Abbreviation.get(0).toString(),
						newRooms.get(0), "RollBack", roomAbbri, newRooms);

			} else {
				res.verifyHistoryForCheckin(driver, teststeps, historyDesc, rc.Abbreviation.get(0).toString(),
						res.primary, "RollBack", roomAbbri, rooms);
			}

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to Verify Roll Back ", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, teststeps);
				Utility.updateReport(e, "Failed to Verify Roll Back ", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {

		return Utility.getData("VerifyRollBackFunctionality", excel);

	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
