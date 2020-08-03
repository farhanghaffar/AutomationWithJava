package com.innroad.inncenter.tests;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.innroad.inncenter.pageobjects.AdvGroups;
import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.Tapechart;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyBlackoutRoom extends TestCore {

	private WebDriver driver = null;

	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> getTest_Steps = new ArrayList<>();

	public static ArrayList<String> test_name = new ArrayList<>();
	public static ArrayList<String> test_description = new ArrayList<>();
	public static ArrayList<String> test_catagory = new ArrayList<>();

	@BeforeTest
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Regression")
	public void verifyBlackoutRoom(String Url, String ClientCode, String UserName, String Password, String Blackout,
			String BlackoutRoom, String Source, String RoomClassAbbreviation, String RoomClassName, String RateName,
			String CheckInDate, String CheckOutDate, String Adults, String Children, String Rateplan, String PromoCode,
			String Salutation, String GuestFirstName, String GuestLastName, String PhoneNumber, String AltenativePhone,
			String Email, String Account, String AccountType, String Address1, String Address2, String Address3,
			String City, String Country, String State, String PostalCode, String IsGuesProfile, String PaymentType,
			String CardNumber, String NameOnCard, String CardExpDate, String IsChangeInPayAmount,
			String ChangedAmountValue, String TravelAgent, String MarketSegment, String Referral, String IsAddNotes,
			String NoteType, String Subject, String Description, String IsTask, String TaskCategory, String TaskType,
			String TaskDetails, String TaskRemarks, String TaskDueon, String TaskAssignee, String TaskStatus,
			String IsAssign, String IsDepositOverride, String DepositOverrideAmount,String IsSplitRes,String Season)
			throws InterruptedException, IOException {

		String testName = "VerifyBlackoutRoom";
		String testDescription = "BlackOut the room<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/554254' target='_blank'>"
				+ "Click here to open TestRail: C554254</a>";
		String testCatagory = "BlackOut Room";

		test_name.add(testName);
		test_description.add(testDescription);
		test_catagory.add(testCatagory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Navigation navigation = new Navigation();
		// distribution = new Distribution();
		Tapechart tapechart = new Tapechart();
		RoomClass roomClass = new RoomClass();
		Rate rate = new Rate();
		//Season sc = new Season();
		//RateQuote rateQuote = new RateQuote();
		//String TripSummaryRoomCharges=null;
		//ArrayList<String> Rooms = new ArrayList<String>();
		new ArrayList<String>();
		//ArrayList<String> roomCost = new ArrayList<String>();
		CPReservationPage reservation = new CPReservationPage();
		AdvGroups advGroups = new AdvGroups();
		String random = Utility.fourDigitgenerateRandomString();
		RoomClassName = RoomClassName + random;
		RoomClassAbbreviation = RoomClassAbbreviation + random;
		String bedsCount="2";
		String maxAdults="4";
		String maxPersopns="8";
		String roomQuantity="3";

		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				System.out.println(Utility.reTry.get(testName));

			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver = getDriver();
			login_NONGS(driver);
			test_steps.add("Logged into the application");
			app_logs.info("Logged into the application");
			navigation.Inventory(driver);
			test_steps.add("Click on inventory tab");
			navigation.Rate(driver);
			RateName = "Blackoutrate" + random;
			//rate.VerifyDeleteRate(driver, "BlockOutRoom");
			rate.deleteRates(driver, "Blackoutrate");
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
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
			test_steps.add("******************* Creating room class **********************");
			app_logs.info("******************* Creating room class **********************");
			navigation.Setup(driver);
			navigation.RoomClass(driver);
			roomClass.SearchRoomClass(driver,"BlockOutRoom", test_steps);
			roomClass.deleteRoomClass(driver, "BlockOutRoom");
			roomClass.selectNewRoomClass(driver);
			roomClass.roomClassRooms(driver, RoomClassName, RoomClassAbbreviation, bedsCount,maxAdults,maxPersopns,roomQuantity, test, test_steps);
			test_steps.add("Successfully created new  roomclass : " + RoomClassName);
			app_logs.info("Successfully created new roomclass : " + RoomClassName);
			navigation.Reservation_Backward_1(driver);
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create room class", "room class", "Failed to create room class",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create room class", "room class", "Failed to create room class",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Inventory
		try {

			navigation.Inventory(driver);
			test_steps.add("Click on inventory tab");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click Inventory", testName, "Inventory", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click Inventory", testName, "Inventory", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// String maxAdults="2";
		 String maxPersons="5";
		 String baseAmount="125";
		 String additionalAdult="2";
		 String additionalChild ="3";
		try {
			test_steps.add("******************* Creating the Rate**********************");
			app_logs.info("******************* Creating the Rate**********************");
			navigation.Inventory(driver);
			test_steps.add("Click on inventory tab");
			navigation.Rate(driver);
			RateName = "Blackoutrate" + random;
			//rate.VerifyDeleteRate(driver, "BlockOutRoom");
			rate.DeleteRate(driver, "Blackoutrate");
			rate.createRate(driver, RateName,maxAdults, maxPersons, baseAmount, additionalAdult,additionalChild, "BlackoutrateName", "BlackoutratePolicy",
					"BlackoutrateDescription", RoomClassName, test_steps);
			navigation.Reservation_Backward_1(driver);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to rate", "rate", "Failed to rate", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create rate", "rate", "Failed to create rate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		//BulkUpdate operations
		test_steps.add("*******************Rooms Bloked  for SingleDay**********************");
		app_logs.info("******************* Rooms Bloked  for SingleDay**********************");
		try {
			navigation.Inventory(driver);
			getTest_Steps.clear();
			rate.rateGrid(driver);
			rate.clickOnRateGridAvilability(driver, test_steps);
			rate.searchAndExpandRoomClassInGrid(driver, RoomClassName, test_steps);
			rate.selectInncenterTab(driver, test_steps);
			rate.clickOnBulkUpdateTab(driver, test_steps);
			rate.selectBulkAvilability(driver, test_steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to find blackout room ", testName, "BlackOut", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to find blackout room ", testName, "BlackOut", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		
		try {
			test_steps.add("*******************Rooms Bloked  for MultipleDays**********************");
			app_logs.info("******************* Rooms Bloked  for MultipleDays**********************");
			

			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date currentDate = new Date();
			// convert date to calendar
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);
			// manipulate date
			c.add(Calendar.DATE, 0); //same with c.add(Calendar.DAY_OF_MONTH, 1);
			// convert calendar to date
			Date currentDatePlusOne = c.getTime();
			String startDate=dateFormat.format(currentDatePlusOne);
			test_steps.add("Season Start Date : "+startDate);
			app_logs.info("Season Start Date : "+startDate);
			dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			currentDate = new Date();
			// convert date to calendar
			c = Calendar.getInstance();
			c.setTime(currentDate);
			// manipulate date
			c.add(Calendar.DATE, 3); //same with c.add(Calendar.DAY_OF_MONTH, 1);
			// convert calendar to date
			currentDatePlusOne = c.getTime();
			String endDate=dateFormat.format(currentDatePlusOne);
			test_steps.add("Season End Date : "+endDate);
			app_logs.info("Season End Date : "+endDate);
			rate.bulkUpdate(driver, test_steps, startDate, endDate);
			rate.verifyBulkUpdateAvilabilityHeadTex(driver, test_steps);
			rate.selectRoomClassInBulkUpadte(driver, RoomClassName, test_steps);
			rate.clickOnBlackOutButton(driver, test_steps);
			rate.clickOnBulkUpdateButton(driver, test_steps);
			rate.clickOnYesUpdateButton(driver, test_steps);
			
		}catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to season", "season", "Failed to season", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create season", "season", "Failed to create season", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		test_steps.add("*************************create reservation without room Black****************************");
		app_logs.info("************************* create reservation without room Black ****************************");

		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date currentDate = new Date();
			// convert date to calendar
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);
			// manipulate date
			c.add(Calendar.DATE, 4); //same with c.add(Calendar.DAY_OF_MONTH, 1);
			// convert calendar to date
			Date currentDatePlusOne = c.getTime();
			String startDate=dateFormat.format(currentDatePlusOne);
			test_steps.add(" Start Date : "+startDate);
			app_logs.info(" Start Date : "+startDate);
			dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			currentDate = new Date();
			// convert date to calendar
			c = Calendar.getInstance();
			c.setTime(currentDate);
			// manipulate date
			c.add(Calendar.DATE, 6); //same with c.add(Calendar.DAY_OF_MONTH, 1);
			// convert calendar to date
			currentDatePlusOne = c.getTime();
			String endDate=dateFormat.format(currentDatePlusOne);
			test_steps.add(" End Date : "+endDate);
			app_logs.info(" End Date : "+endDate);	
			navigation.cpReservation_Backward(driver);
			reservation.click_NewReservation(driver, test_steps);
			reservation.select_CheckInDate(driver, test_steps, CheckInDate);
			reservation.select_CheckoutDate(driver, test_steps,CheckOutDate);
			reservation.enter_Adults(driver, test_steps, Adults);
			reservation.enter_Children(driver, test_steps, Children);
			reservation.select_Rateplan(driver, test_steps, Rateplan, PromoCode);
			reservation.select_Dates(driver, test_steps, startDate, endDate, Adults, Children, Rateplan, PromoCode,IsSplitRes);
			reservation.clickOnFindRooms(driver, test_steps);
			reservation.selectRoom(driver, test_steps, RoomClassName, IsAssign, Account);
			reservation.deposit(driver, test_steps, IsDepositOverride, DepositOverrideAmount);
			reservation.clickNext(driver, test_steps);
			reservation.enter_MailingAddress(driver, test_steps, Salutation, GuestFirstName, GuestLastName, PhoneNumber,
					AltenativePhone, Email, Account, AccountType, Address1, Address2, Address3, City, Country, State,
					PostalCode, IsGuesProfile);
			if ((Account.equalsIgnoreCase("") || Account.isEmpty())) {
				reservation.enter_PaymentDetails(driver, test_steps, PaymentType, CardNumber, NameOnCard, CardExpDate);
			}
			reservation.enter_MarketSegmentDetails(driver, test_steps, TravelAgent, MarketSegment, Referral);
			reservation.clickBookNow(driver, test_steps);
			reservation.get_ReservationConfirmationNumber(driver, test_steps);
			reservation.get_ReservationStatus(driver, test_steps);
			reservation.clickCloseReservationSavePopup(driver, test_steps);
			reservation.get_RoomNumber(driver, test_steps, IsAssign);
			

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}	
			
		// Reservation
		test_steps.add("*************************Very  Black out Room In TapeCaht   ****************************");
		app_logs.info("*************************Select  Black out Room In TapeCaht   ****************************");
		try {
			navigation.navigateToReservations(driver);
			navigation.TapeChart(driver);
            tapechart.verifyBlackOutRoomsInTapeChat(driver,RoomClassName,getTest_Steps);
			//tapechart.Verify_BlackOutRoom(driver, BlackoutRoom);
			test_steps.add("Verify room class status in TapeChart");
			app_logs.info("Verify room class status in TapeChart");

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to find blackout room in Tapchart", testName, "BlackOut", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to find blackout room in Tapchart", testName, "BlackOut", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Reservation
		test_steps.add("*************************Verify Black out Room Avilable In Reservation ****************************");
		app_logs.info("*************************Verify Black out Room Avilable In Reservation ****************************");
		try {
			navigation.navigateToReservations(driver);
			reservation.click_NewReservation(driver, test_steps);
			reservation.select_CheckInDate(driver, test_steps, CheckInDate);
			reservation.select_CheckoutDate(driver, test_steps, CheckOutDate);
			reservation.select_Dates(driver, getTest_Steps, CheckInDate, CheckOutDate, Adults, Children, Rateplan, PromoCode, IsSplitRes);
			reservation.enter_Adults(driver, test_steps, Adults);
			reservation.enter_Children(driver, test_steps, Children);
			reservation.select_Rateplan(driver, test_steps, Rateplan, PromoCode);
			reservation.clickOnFindRooms(driver, test_steps);
			reservation.searcRoom(driver, RoomClassName, test_steps);
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			test_steps.add("*************************Verify RoomClass with In black Out days in Group Black ****************************");
			app_logs.info("*************************Verify  RoomClass with In black Out days in Group Black****************************");
			navigation.Groups(driver);
			app_logs.info("Navigated to GROUPS sub-module page");
			test_steps.add("Navigated to GROUPS sub-module page");

			app_logs.info("Creating new advanced group");
			getTest_Steps.clear();
			String groupAccount = advGroups.createGroupaccount(driver, "Internet", "Other", "AutoGroup" + random,
					"GroupFname", "GroupLName", "3487761234", "Test", "TestCity", "New York", "23456", "United States",
					test_steps);
			test_steps.addAll(getTest_Steps);
			app_logs.info("Clicked on New Account and updating contact info");

			advGroups.SaveAdvGroupValidate(driver, test_steps);
			app_logs.info("Saved the group : " + groupAccount);
			test_steps.add("Saved the group : " + groupAccount);

			getTest_Steps.clear();
			advGroups.EnterBlockName(driver, getTest_Steps, "TestBlack" + random);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			advGroups.SearchGroupCriteria(driver, getTest_Steps, "1");
			advGroups.selectRoomInGroupBlock(driver,RoomClassName , test_steps);
		    //test_steps.addAll(getTest_Steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify rule in GroupBlack", testName, "GroupBlack", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to rule in GroupBlack", testName, "GroupBlack", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
	
		
		
		try {
			test_steps.add("*************************Verify RoomClass Out of block days in Group Black ****************************");
			app_logs.info("*************************Verify RoomClass  Out of block days in Group Black****************************");
			navigation.navigateToReservations(driver);
			navigation.Groups(driver);
			app_logs.info("Navigated to GROUPS sub-module page");
			test_steps.add("Navigated to GROUPS sub-module page");

			app_logs.info("Creating new advanced group");
			getTest_Steps.clear();
			String groupAccount = advGroups.createGroupaccount(driver, "Internet", "Other", "AutoGroup" + random,
					"GroupFname", "GroupLName", "3487761234", "Test", "TestCity", "New York", "23456", "United States",
					test_steps);
			test_steps.addAll(getTest_Steps);
			app_logs.info("Clicked on New Account and updating contact info");

			advGroups.SaveAdvGroupValidate(driver, test_steps);
			app_logs.info("Saved the group : " + groupAccount);
			test_steps.add("Saved the group : " + groupAccount);

			getTest_Steps.clear();
			advGroups.EnterBlockName(driver, getTest_Steps, "TestBlack" + random);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			advGroups.searchGroupCriteriaWithOutBlackDates(driver, "1", test_steps);
			advGroups.selectRoomInGroupBlock(driver,RoomClassName,test_steps);
			test_steps.addAll(getTest_Steps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify rule in GroupBlack", testName, "GroupBlack", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to rule in GroupBlack", testName, "GroupBlack", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
	
		
			test_steps.add("************************* Remove Blackout Room****************************");
			app_logs.info("************************* Remove Blackout Room ****************************");
		
		try {
			navigation.Inventory(driver);
			getTest_Steps.clear();
			rate.rateGrid(driver);
			rate.clickOnRateGridAvilability(driver, test_steps);
			rate.searchAndExpandRoomClassInGrid(driver, RoomClassName, test_steps);
			rate.selectInncenterTab(driver, test_steps);
			//rate.clickOnUpdateButton(driver, test_steps);
			rate.clickOnBulkUpdateTab(driver, test_steps);
			rate.selectBulkAvilability(driver, test_steps);
			//rate.verifyBulkUpdateAvilability(driver, test_steps);
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date currentDate = new Date();
			// convert date to calendar
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);
			// manipulate date
			c.add(Calendar.DATE, 0); //same with c.add(Calendar.DAY_OF_MONTH, 1);
			// convert calendar to date
			Date currentDatePlusOne = c.getTime();
			String startDate=dateFormat.format(currentDatePlusOne);
			test_steps.add(" Remove BlackOut Start Date : "+startDate);
			app_logs.info("Remove BlackOut Start Date: "+startDate);
			dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			currentDate = new Date();
			// convert date to calendar
			c = Calendar.getInstance();
			c.setTime(currentDate);
			// manipulate date
			c.add(Calendar.DATE, 3); //same with c.add(Calendar.DAY_OF_MONTH, 1);
			// convert calendar to date
			currentDatePlusOne = c.getTime();
			String endDate=dateFormat.format(currentDatePlusOne);
			test_steps.add("Season End Date : "+endDate);
			app_logs.info("Season End Date : "+endDate);
			rate.bulkUpdate(driver, test_steps, startDate, endDate);
			rate.verifyBulkUpdateAvilabilityHeadTex(driver, test_steps);
			rate.selectRoomClassInBulkUpadte(driver, RoomClassName, test_steps);
			rate.clickOnAvilableTab(driver, test_steps);
			rate.clickOnBulkUpdateButton(driver, test_steps);
			rate.clickOnYesUpdateButton(driver, test_steps);
		}catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to season", "season", "Failed to season", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create season", "season", "Failed to create season", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		String packageName ="Blackoutrate";
	
		try {
			test_steps.add("***********************Delete all Rates created during test run *************************");
			app_logs.info("*********************** Delete all Rates created during test run *************************");
			navigation.cpReservation_Backward(driver);
			navigation.Inventory(driver);
			navigation.Rate(driver);
			//rate.deleteAllRates(driver, getTest_Steps,"BlockOutRoom");
			rate.deleteRates(driver, "Blackoutrate");
			//rate.VerifyDeleteRate(driver, "BlockOutRoom");
			app_logs.info("Successfully deleted Rate : " + RateName);
			test_steps.add("Successfully deleted Rate : " + RateName);
			
			app_logs.info("==========DELETE ALL RATES THAT START WITH NAME OF " + packageName + "==========");
			test_steps.add("==========DELETE ALL RATES THAT START WITH NAME OF " + packageName + "==========");

			test_steps.add("***********************Delete all room classes created during test run *************************");
			app_logs.info("***********************Delete all room classes created during test run *************************");
			//nav.cpReservation_Backward(driver);
			navigation.Reservation_Backward_1(driver);
			navigation.Setup(driver);
			navigation.RoomClass(driver);
			roomClass.SearchRoomClass(driver,"BlockOutRoom", test_steps);
			roomClass.deleteRoomClass(driver, "BlockOutRoom");
			test_steps.add("Verify the Deleted Rates that started with name of : " + packageName);
			app_logs.info("Verify the Deleted Rates that started with name of : " + packageName);
			
			
			app_logs.info("Successfully deleted setup");
			test_steps.add("Successfully deleted setup");

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Delete Rate,Seanson,Room Class", testName, "Inventory", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Delete Rate,Seanson,Room Class", testName, "Inventory", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {

		// return test data from the sheetname provided

		return Utility.getData("VerifyBlackoutRoom", excel);
	}

	//@AfterClass
	public void closedriver() {
		driver.quit();
	}

}
