package com.innroad.inncenter.tests;

import java.awt.Component;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.tools.JavaFileManager.Location;

import org.apache.poi.hssf.record.formula.functions.Offset;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Coordinates;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.RoomMaintenance;
import com.innroad.inncenter.pageobjects.RoomStatus;
import com.innroad.inncenter.pageobjects.TaskList;
import com.innroad.inncenter.pageobjects.TaskManagement;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.webelements.WebElements_RoomStatus;

public class GSRoomStatusVerification extends TestCore {

	private WebDriver driver = null;
	public static String test_name = null;
	public static String test_nameOne = null;
	public static String test_description = null;
	public static String test_catagory = null;
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	ArrayList<String> roomNumbers = new ArrayList<>();
	ArrayList<String> roomMaintance = new ArrayList<String>();
	RoomClass roomClass = new RoomClass();
	public static String roomClassNames;
	public static String roomClassAbbrivations;
	Navigation navigation = new Navigation();
	CPReservationPage reservation = new CPReservationPage();
	ReservationSearch reservationSearch = new ReservationSearch();
	TaskList tasklist = new TaskList();
	RoomMaintenance room_maintenance = new RoomMaintenance();

	TaskManagement taskmang = new TaskManagement();
	RoomStatus roomstatus = new RoomStatus();
	String testName = null, rateNames = null, rateDisplayName = null, zoneName = null, yearDate = null,
			guestFirstOne = null, guestSecondOne = null, reservationNo = null;
	Rate rate = new Rate();

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}



	private void createReservation(String checkInDate, String checkOutDate, String adults, String children,
			String ratePlanName, String salutation, String guestFirstName, String guestLastName, String isGuesProfile,
			String paymentType, String cardNumber, String nameOnCard, String marketSegment, String referral)
			throws InterruptedException, ParseException {
		navigation.Reservation_Backward_2(driver);
		reservation.click_NewReservation(driver, test_steps);
		reservation.select_CheckInDate(driver, test_steps, checkInDate);
		reservation.select_CheckoutDate(driver, test_steps, checkOutDate);
		reservation.enter_Adults(driver, test_steps, adults);
		reservation.enter_Children(driver, test_steps, children);
		reservation.select_Rateplan(driver, test_steps, ratePlanName, "");
		reservation.clickOnFindRooms(driver, test_steps);
		reservation.select_SpecificRoom(driver, test_steps, roomClassNames, "", "");
		reservation.clickNext(driver, test_steps);
		yearDate = Utility.getFutureMonthAndYearForMasterCard(driver, test_steps);
		Random random = new Random();
		int x = random.nextInt(900);
		guestFirstOne = guestFirstName + x;
		guestSecondOne = guestLastName.toString() + x;
		reservation.enter_MailingAddress_OnUnchecked_CreateGuestProfile(driver, test_steps, salutation, guestFirstOne,
				guestSecondOne, isGuesProfile);
		reservation.enter_PaymentDetails(driver, test_steps, paymentType, cardNumber, nameOnCard, yearDate);
		reservation.enter_MarketSegmentDetails(driver, test_steps, "", marketSegment, referral);
		reservation.clickBookNow(driver, test_steps);
	}

	private void checkinAndSearchByOccupied(String notes) throws InterruptedException {
		reservation.clickCheckInButton(driver, test_steps);
		reservation.generatGuestReportToggle(driver, test_steps, "No");
		reservation.completeCheckInProcess(driver, test_steps);
		boolean isPaymentWindow = reservation.getPaymentWindow(driver);
		if (isPaymentWindow) {
			reservation.addNotesAndClickLogORPayORAuthorizedButton(driver, test_steps, notes);
			reservation.clickCloseButtonOfSuccessModelPopup(driver, test_steps);
		}
		reservation.verifyCheckOutButton(driver, test_steps);
		test_steps.add("<b>****Start Searching By Occuiped****</b>");
		navigation.Guestservices(driver);
		test_steps.add("Navigate to Guest Services");
		app_logs.info("Navigate to Guest Services");
		roomstatus.verifyRoomStatusTabEnabled(driver, test_steps);
		roomstatus.verifiedGuestServicesDataLoadedOrNot(driver, test_steps);
		roomstatus.searchByVacantOccupied(driver, "Occupied", test_steps);
		test_steps.add("Successfull Searched By Occuiped: <b>" + "Occupied" + "</b>");
		app_logs.info("Successfull Search By Occupied");
	}

	@Test(dataProvider = "getData", groups = "GuestServices")
	public void gSRoomStatusVerification(String uRL, String clientId, String userName, String password,
			String roomClassName, String roomClassAbbrivation, String maxAdults, String maxPersopns,
			String roomQuantity, String rateName, String displayName, String amount, String ratePolicy,
			String rateDescription, String checkInDate, String checkOutDate, String adults, String children,
			String ratePlanName, String salutation, String guestFirstName, String guestLastName, String isGuesProfile,
			String paymentType, String cardNumber, String nameOnCard, String marketSegment, String referral,
			String notes, String taskName, String category, String categoryType, String details, String remarks,
			String assignee, String subject)

			throws InterruptedException {

		test_name = "GSRoomStatusVerification";
		test_description = "GSRoomStatusVerification<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682444' target='_blank'>"
				+ "Click here to open TestRail: C682444</a><br>";
		test_catagory = "Verification";
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

		// Enable toggle for Inspection
		try {
			test_steps.add("<b>****Enabling Toggle for Inspection****</b>");
			navigation.Setup(driver);
			test_steps.add("Navigated to Setup");
			navigation.TaskManagement_TabExist(driver);
			test_steps.add("Task Management Tab Exist");
			navigation.TaskManagement(driver);
			test_steps.add("Click on Task Management");
			app_logs.info("Click on Task Management");
			taskmang.setInspectionCleaningToggle(driver, true);
			test_steps.add("Successfully Enabled Toggle for Inspection ");
			app_logs.info("Successfully Enabled Toggle for Inspection");
			taskmang.setInspectionCleaningToggleFlagStatus(driver);
			test_steps.add("Successfully Set Toggle Condition Value ");
			app_logs.info("Successfully Set Toggle Condition Value");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Enable toggle for Inspection", "Task Management", "RoomClass",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Enable toggle for Inspection", "Task Management", "RoomClass",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Create Room Class
		try {

			test_steps.add("<b>****Start Creating New Room Class****</b>");
			navigation.RoomClass(driver);
			test_steps.add("Navigated to Room Class");
			roomClassNames = roomClassName + Utility.getTimeStamp();
			roomClassAbbrivations = roomClassAbbrivation + Utility.getTimeStamp();
			navigation.NewRoomClass(driver);
			test_steps.add("Navigated to New Room Class");
			app_logs.info("Navigated to New Room Class");
			roomClass.createRoomClass_MoreThanOneRooms(driver, roomClassName, roomClassAbbrivation, "", maxAdults,
					maxPersopns, roomQuantity, test_steps);
			roomClassNames = Utility.RoomClassName;
			roomClassAbbrivations = Utility.RoomClassabv;
			test_steps.add("Room Class Created: <b>" + roomClassNames + "</>");
			app_logs.info("Room Class Created: " + roomClassNames);

			String quantity = roomQuantity;
//			====== roomclass creations ====
			String roomNumber = Utility.RoomNo;

			roomNumbers.add(roomNumber);
			for (int i = 1; i < Integer.parseInt(quantity); i++) {
				roomNumber = String.valueOf(Integer.parseInt(roomNumber) + 1);
				roomNumbers.add(roomNumber);
			}
			app_logs.info("Room No Are: " + roomNumbers);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Create Room Class", testName, "Room class", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Create Room Class", testName, "Room class", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Get Zone
		try {
			test_steps.add("<b>****Getting Zone****</b>");
			navigation.navigateRoomClassTab(driver);
			roomClass.SearchRoomClass(driver, roomClassNames, test_steps);
			zoneName = roomClass.getSingleRoomClassZone(driver, test_steps, roomClassName, roomClassNames);
			test_steps.add("Zone : <b>" + zoneName + "</b>");
			app_logs.info("Zone: " + zoneName);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Get Zone", testName, "GS", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Get Zone", testName, "GS", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Create Rates and Associate Room Class
		try {
			test_steps.add("<b>****Start Create New Rates and Associate Room Class with Rates****</b>");
			navigation.Inventory(driver);
			test_steps.add("Navigate to Inventory");
			app_logs.info("Navigate to Inventory");
			navigation.Rates1(driver);
			test_steps.add("Navigate to Rates");
			rateNames = rateName + Utility.getTimeStamp();
			rateDisplayName = displayName + Utility.getTimeStamp();
			rate.CreateRate(driver, rateNames, maxAdults, maxPersopns, amount, maxAdults, maxPersopns, rateDisplayName,
					ratePolicy, rateDescription, roomClassNames, test_steps);
			test_steps.add("Successfull Created Rate: <b>" + rateNames + "</b>");
			app_logs.info("Successfull Created Rate: " + rateNames);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Search Rates and Associate Room Class", testName, "Room class",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Search Rates and Associate Room Class", testName, "Room class",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Get checkIN and Checkout Date
		try {
			if (!(Utility.validateInput(checkInDate))) {
				for (int i = 0; i < guestFirstName.split("\\|").length; i++) {
					checkInDates.add(Utility.getCurrentDate("dd/MM/yyyy"));
					checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(1), "MM/dd/yyyy", "dd/MM/yyyy"));
				}
			} else {
				checkInDates = Utility.splitInputData(checkInDate);
				checkOutDates = Utility.splitInputData(checkOutDate);
			}
			checkInDate = checkInDates.get(0);
			checkOutDate = checkOutDates.get(0);
			app_logs.info(checkInDate);
			app_logs.info(checkOutDate);

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

		// Search By Room #
		try {
			test_steps.add("<b>****Search By Room # and Verify Room #****</b>");
			navigation.Guestservices(driver);
			test_steps.add("Navigated to Guestservices");
			roomstatus.verifyRoomStatusTabEnabled(driver, test_steps);
			roomstatus.verifiedGuestServicesDataLoadedOrNot(driver, test_steps);
			roomstatus.searchByRoomHash(driver, Utility.RoomNo, roomClassNames, test_steps);
			test_steps.add("Successfull Searched By Room#: <b>" + Utility.RoomNo + "</b>");
			app_logs.info("Successfull Search By Room# " + Utility.RoomNo);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Search By Room #", testName, "GS", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Search By Room #", testName, "GS", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Search By Room Class
		try {
			test_steps.add("<b>****Start Searching By RoomClass and Verify Room Class****</b>");
			roomstatus.searchByRoom(driver, roomClassNames, test_steps);
			test_steps.add("Successfull Searched By RoomClassName: <b>" + roomClassNames + "</b>");
			app_logs.info("Successfull Search By RoomClassName: " + roomClassNames);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Search By Room #", testName, "GS", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Search By Room #", testName, "GS", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Search By Zone
		try {
			test_steps.add("<b>****Start Searching By Zone and Verify Zone****</b>");
			roomstatus.searchByZone(driver, zoneName, test_steps);
			test_steps.add("Successfull Searched By Zone: <b>" + zoneName + "</b>");
			app_logs.info("Successfull Search By Zone");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Search By Zone", testName, "GS", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Search By Zone", testName, "GS", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Search By Vacant
		try {
			test_steps.add("<b>****Start Searching By Vacant and Verify Vacant****</b>");
			roomstatus.searchByVacantOccupied(driver, "Vacant", test_steps);
			test_steps.add("Successfull Searched By  <b>" + "Vacant" + "</b>");
			app_logs.info("Successfull Search By Vacant");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Search By Vacant", testName, "GS", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Search By Vacant", testName, "GS", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Search By Arrival Due
		try {
			test_steps.add("<b>****Search By Arrival Due****</b>");
			boolean isExist = roomstatus.verifiedByArrivalDue(driver, "Arrival Due", test_steps);
			if (!isExist) {
				test_steps.add("<b>***Arrival Due Doesn't Exist***</b>");
				test_steps.add("<b>****Start Creating Reservation****</b>");
				createReservation(checkInDate, checkOutDate, adults, children, ratePlanName, salutation, guestFirstName,
						guestLastName, isGuesProfile, paymentType, cardNumber, nameOnCard, marketSegment, referral);
				reservationNo = reservation.get_ReservationConfirmationNumber(driver, test_steps);
				reservation.clickCloseReservationSavePopup(driver, test_steps);
				test_steps.add("<b>****Start Searching By Arrival Due****</b>");
				navigation.Guestservices(driver);
				test_steps.add("Navigate to Guest Services");
				app_logs.info("Navigate to Guest Services");
				roomstatus.verifyRoomStatusTabEnabled(driver, test_steps);
				roomstatus.verifiedGuestServicesDataLoadedOrNot(driver, test_steps);
				roomstatus.searchByArrivalDue(driver, "Arrival Due", test_steps);
				test_steps.add("Successfull Searched By Arrival Due: <b>" + "Arrival Due" + "</b>");
				app_logs.info("Successfull Search By Arrival Due");
			}
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Search By Arrival Due", testName, "GS", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Search By Arrival Due", testName, "GS", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Search By Occupied
		try {
			test_steps.add("<b>****Search By Occupied****</b>");
			boolean isExist = roomstatus.verifyByOccupied(driver, "Occupied", test_steps);
			boolean isNUll = Strings.isNullOrEmpty(reservationNo);
			app_logs.info("isRoomStatus Value: " + isExist);
			app_logs.info("isNull Value: " + isNUll);
			if ((!isExist && !isNUll)) {
				test_steps.add("<b>***Occupied Doesn't Exist***</b>");
				test_steps.add("<b>****Start Searching Created Reservation****</b>");
				navigation.Reservation_Backward_2(driver);
				reservationSearch.basicSearchWithResNumber(driver, reservationNo, test_steps);
				checkinAndSearchByOccupied(notes);
			} else if (!isExist && isNUll) {
				test_steps.add("<b>***Occupied Doesn't Exist***</b>");
				test_steps.add("<b>****Start Creating Reservation****</b>");
				createReservation(checkInDate, checkOutDate, adults, children, ratePlanName, salutation, guestFirstName,
						guestLastName, isGuesProfile, paymentType, cardNumber, nameOnCard, marketSegment, referral);
				reservation.clickCloseReservationSavePopup(driver, test_steps);
				checkinAndSearchByOccupied(notes);
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Search By Occupied", testName, "GS", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Search By Occupied", testName, "GS", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Verified Dirty Stat
		try {
			navigation.Reservation_Backward_2(driver);
			navigation.Guestservices(driver);
			test_steps.add("Navigate to Guest Services");
			app_logs.info("Navigate to Guest Services");
			roomstatus.verifyRoomStatusTabEnabled(driver, test_steps);
			roomstatus.verifiedGuestServicesDataLoadedOrNot(driver, test_steps);
			test_steps.add("<b>****Start Verifying Dirty Stats****</b>");
			roomstatus.clickQuickStatsAndVerifyColor(driver, "Dirty", test_steps);
			WebElements_RoomStatus roomStatusElement = new WebElements_RoomStatus(driver);
			roomstatus.verifyTotalRoomCard(driver, test_steps, "Dirty",
					roomStatusElement.RoomStatus_DirtyStatTotalRoomCardStatus,
					roomStatusElement.RoomStatus_DirtyStatSize);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Dirty quick stat", testName, "GS", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Dirty quick stat", testName, "GS", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Verified All Stat
		try {
			test_steps.add("<b>****Start Verifying All Stats****</b>");
			roomstatus.verifyAllStat(driver, test_steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify All quick stat", testName, "GS", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify All quick stat", testName, "GS", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verified Inspection Quick Stat
		try {
			boolean isExist = roomstatus.getQuickStatSize(driver, "Inspection");
			if (!isExist) {
				test_steps.add(
						"<b>****Inspection Count is Zero Creating New Task and Set Task Status as Inspection****</b>");
				navigation.Task_List(driver);
				test_steps.add("Navigate to Task List Tab");
				app_logs.info("Navigate to Task List Tab");
				tasklist.Click_AddTask(driver);
				test_steps.add("Click on Add Task Button");
				app_logs.info("Click on Add Task Button");
				app_logs.info("Room NO:" + Utility.RoomNo);
				tasklist.createNewTaskWithRoomOfThatRoomClass(driver, taskName, category, categoryType, details,
						remarks, assignee, Utility.RoomNo, Utility.RoomClassName);
				test_steps.add("Add Task :<b>" + taskName + "</b>");
				app_logs.info("Add Task: " + taskName);
				tasklist.searchTaskByRoomNo(driver, Utility.RoomNo);
				test_steps.add("Search task By Room No : <b>" + Utility.RoomNo + "</b>");
				app_logs.info("Search task By Room No :" + Utility.RoomNo);
				tasklist.changeTaskStatus(driver, "To Do", "Inspection", test_steps);
				navigation.RoomStatus(driver);
			}
			test_steps.add("<b>****Start Verifying Inspection Stats****</b>");
			roomstatus.clickQuickStatsAndVerifyColor(driver, "Inspection", test_steps);
			WebElements_RoomStatus roomStatusElement = new WebElements_RoomStatus(driver);
			roomstatus.verifyTotalRoomCard(driver, test_steps, "Inspection",
					roomStatusElement.RoomStatus_InspectionRoomCardS, roomStatusElement.RoomStatus_InspectionStatSize);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Inspection quick stat", "GS_RoomClass", "RoomClass", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Inspection quick stat", "GS_RoomClass", "RoomClass", driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		// Verify Clean Quick Stat
		try {
			boolean isExist = roomstatus.getQuickStatSize(driver, "Clean");
			if (!isExist) {
				test_steps.add("<b>****Clean Count is Zero Creating New Task and Set Task Status as Clean****</b>");
				navigation.Task_List(driver);
				test_steps.add("Navigate to Task List Tab");
				app_logs.info("Navigate to Task List Tab");
				tasklist.clickQuickStatesAndUpdateTaskStatus(driver, "Inspection", "Done", Utility.RoomNo, test_steps);
				navigation.RoomStatus(driver);
				test_steps.add("Navigate to  Room Status Tab");
				app_logs.info("Navigate to Room Status Tab");
			}
			test_steps.add("<b>****Start Verifying Clean  Stats****</b>");
			roomstatus.clickQuickStatsAndVerifyColor(driver, "Clean", test_steps);
			WebElements_RoomStatus roomStatusElement = new WebElements_RoomStatus(driver);
			roomstatus.verifyTotalRoomCard(driver, test_steps, "Clean", roomStatusElement.RoomStatus_CleanRoomCardS,
					roomStatusElement.RoomStatus_CleanStatSize);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Clean quick stat", "GS_RoomClass", "RoomClass", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Clean quick stat", "GS_RoomClass", "RoomClass", driver);

			} else {
				Assert.assertTrue(false);
			}
		}
		// Verified Out of Order
		try {
			boolean isExist = roomstatus.getQuickStatSize(driver, "Out of Order");
			if (!isExist) {
				test_steps.add(
						"<b>****Out Of Order Count is Zero Start Creating Room Maintenance With Out Of Order****</b>");
				navigation.RoomMaintenance(driver);
				test_steps.add("Navigated to Room Maintenance");
				roomMaintance = room_maintenance.createNewRoomOutOfOrder(driver, subject + Utility.getTimeStamp(),
						roomNumbers.get(1), roomClassNames, test_steps, details);
				navigation.clickRoomStatusTab(driver);
				app_logs.info("Navigate to Guest Services");
				test_steps.add("Navigated to Guestservices");
				roomstatus.verifyRoomStatusTabEnabled(driver, test_steps);
				roomstatus.verifiedGuestServicesDataLoadedOrNot(driver, test_steps);
			}
			WebElements_RoomStatus roomStatusElement = new WebElements_RoomStatus(driver);
			test_steps.add("<b>**** Start Verfying Out OF Order Stats****</b>");
			roomstatus.clickQuickStatsAndVerifyColor(driver, "Out of Order", test_steps);
			roomstatus.verifyTotalRoomCard(driver, test_steps, "Out of Order",
					roomStatusElement.RoomStatus_OORRoomCardS, roomStatusElement.RoomStatus_OutOfOrderStatSize);
			test_steps.add("Successfully Verified Out OF Order Room Card ");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Out of Order", "GS_RoomClass", "RoomClass", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Out of Order", "GS_RoomClass", "RoomClass", driver);

			} else {
				Assert.assertTrue(false);
			}
		}
       //Verified Sorting Option
		try
		{
			roomstatus.verifySortFuntionality1(driver);
			test_steps.add("Verified Sorting Options <b> <br> Room#, <br>Zone  <br> Arrival Due </b>");
			app_logs.info("Verified Sorting Options Room#, Zone And Arrival Due");
			
		}catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Out of Order", "GS_RoomClass", "RoomClass", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Out of Order", "GS_RoomClass", "RoomClass", driver);

			} else {
				Assert.assertTrue(false);
			}
		}
		// Delete Rate
		try {
			test_steps.add("<b>****Start Deleting Rates****</b>");
			navigation.Inventory_Backward_2(driver);
			test_steps.add("Navigated to Inventory");
			navigation.Rates1(driver);
			rate.deleteRates(driver, rateName);
			test_steps.add("All Rate Deleted Successfully With Name: <b>" + rateName + " </b>");
			app_logs.info("All Rate Deleted Successfully With Name: " + rateName);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Delete Rates ", testName, "Rate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to Delete Rates", testName, "Rate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Delete Room Class
		try {
			test_steps.add("<b>****Delete Room Class****</b>");
			navigation.Setup(driver);
			test_steps.add("Navigated to Setup");
			navigation.RoomClass(driver);
			roomClass.SearchButtonClick(driver);
			test_steps.add("Click on Search Button");
			app_logs.info("Click on Search Button");
			roomClass.SearchRoomClass(driver, roomClassName, test_steps);
			roomClass.deleteRoomClass(driver, roomClassName);
			test_steps.add("All Room Class Deleted Successfully With Name: <b>" + roomClassName + " </b>");
			app_logs.info("All Room Class Deleted Successfully With Name: " + roomClassName);

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Delete Room Class ", testName, "Night Audit", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to Delete Room Class ", testName, "Night Audit", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
	}

	@DataProvider
	public Object[][] getData() {

		// return test data from the sheetname provided

		return Utility.getData("GSRoomStatusVerification", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		// driver.quit();
	}
}
