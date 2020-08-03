package com.innroad.inncenter.tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

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
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Policies;
import com.innroad.inncenter.pageobjects.Properties;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.RoomStatus;
import com.innroad.inncenter.pageobjects.Tapechart;
import com.innroad.inncenter.pageobjects.TaskManagement;
import com.innroad.inncenter.pageobjects.Tax;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class VerifyCheckInCPReservationMRB extends TestCore {

	// NOTE:- IF WANT TO EXECUTE CHECK-IN WITH 'CHECK-IN POLICY' THEN CHANGE
	// FLAG VALUE "WithPolicy=Yes" IN EXCEL SHEET.
	private WebDriver driver = null;
	public static String test_name = null;
	public static String test_nameOne = null;
	public static String test_description = null;
	public static String test_catagory = null;
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> roomAbbri = new ArrayList<String>();
	ArrayList<String> roomCost = new ArrayList<String>();
	ArrayList<String> rooms = new ArrayList<String>();
	ArrayList<String> newRooms = new ArrayList<String>();
	ArrayList<String> roomButtonStatus = new ArrayList<String>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	List<String> roomCharges = new ArrayList<String>();
	List<String> roomNos = new ArrayList<String>();
	Properties properties = new Properties();
	public static Logger reslogger = Logger.getLogger("VerifyCheckInCPReservationMRB");
	RoomClass rc = new RoomClass();
	Navigation nav = new Navigation();
	RoomStatus roomstatus = new RoomStatus();
	CPReservationPage res = new CPReservationPage();
	Policies policies = new Policies();
	Tapechart tapeChart = new Tapechart();
	TaskManagement task_mang = new TaskManagement();
	Rate rate = new Rate();
	RoomClass roomClass = new RoomClass();
	Tax tax = new Tax();
	Date currentDate = null, previousDate = null;
	Double depositAmount = 0.0;
	String reservation = null, panelGuestName = null, status = null, stayInfoSecondayGuestName = null,
			tripSummaryRoomCharges = null, checkInDate = null, checkOutDate = null;
	String guestFirstName = null, guestLastName = null, yearDate = null, timeZone = null, propertyName = null;
	String Policyname = null;
	String amount = null, paymentMethod = null;

	@BeforeTest(alwaysRun = true)

	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	String testName = null;
	String roomClassNameWithoutNum = "RoomClass";
	String roomClassAvvWithoutNum = "RoomAbb";
	String rateNameWithoutNum = "AutoRate_";
	String randomNum1 = null;
	String randomNum2 = null;
	String roomClassName1 = null;
	String roomClassAbb1 = null;
	String maxAdults = "2", maxPersons = "2", roomQuantity = "1";
	String roomClassName2 = null;
	String roomClassAbb2 = null;
	ArrayList<String> roomNumberAssigned = new ArrayList<>();
	
	String rateName = null;

	@Test(dataProvider = "getData", groups = "Reservation")
	public void CheckIN_Reservation(Map<Object, Object> map) {

		// Removed All Elements from List
		rooms.removeAll(rooms);
		roomCharges.removeAll(roomCharges);
		roomNos.removeAll(roomNos);
		roomAbbri.removeAll(roomAbbri);
		rc.Abbreviation.removeAll(rc.Abbreviation);
		roomButtonStatus.removeAll(roomButtonStatus);
		roomNumberAssigned.removeAll(roomNumberAssigned);
		String policyNameWithoutNum = map.get("PolicyName").toString();
		 randomNum1 = Utility.GenerateRandomNumber();
		 randomNum2 = Utility.GenerateRandomNumber();
		 roomClassName1 = roomClassNameWithoutNum + "One_" + randomNum1;
		 roomClassAbb1 = roomClassAvvWithoutNum+"One_" + randomNum1;
		 roomClassName2 = roomClassNameWithoutNum + "Two_" + randomNum2;
		 roomClassAbb2 = roomClassAvvWithoutNum+"Two_" + randomNum2;
		 rateName = rateNameWithoutNum + randomNum1;
		 

		if (map.get("WithPolicy").toString().equals("No")) {
			test_name = "VerifyCPMRBReservationCheck-InWithoutPolicy";
			test_description = "Verify CP MRB Check-In Reservation Without Check-In Policy <br>"
					+ "<a href='https://innroad.testrail.io/index.php?/cases/view/681833' target='_blank'>"
					+ "Click here to open TestRail: C681833</a><br>"
					+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682469' target='_blank'>"
					+ "Click here to open TestRail: C682469</a><br>"
					+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682505' target='_blank'>"
					+ "Click here to open TestRail: C682505</a> "
					+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/681834' target='_blank'>"
					+ "Click here to open TestRail: C681834</a> <br>";
			test_catagory = "CPReservation_CheckIN";
			testName = test_name;

			app_logs.info("##################################################################################");
			app_logs.info("EXECUTING: " + testName + " TEST.");
			app_logs.info("##################################################################################");
			test_steps.add("<b>==================Verifyig Check-In Functionality WithOut Policy================</b>");
		} else if (map.get("WithPolicy").toString().equals("Yes")) {
			test_name = "VerifyCPMRBReservationCheck-InWithPolicy";
			test_description = "Verify CP MRB Check-In Reservation With Check-In Policy <br>"
					+ "<a href='https://innroad.testrail.io/index.php?/cases/view/681833' target='_blank'>"
					+ "Click here to open TestRail: C681833</a><br>"
					+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682469' target='_blank'>"
					+ "Click here to open TestRail: C682469</a><br>"
					+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682505' target='_blank'>"
					+ "Click here to open TestRail: C682505</a> "
					+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/681834' target='_blank'>"
					+ "Click here to open TestRail: C681834</a> <br>";
			test_catagory = "CPReservation_CheckIN";
			testName = test_name;

			app_logs.info("##################################################################################");
			app_logs.info("EXECUTING: " + testName + " TEST.");
			app_logs.info("##################################################################################");
			test_steps.add("<b>=======================Verifyig Check-In With Policy=======================</b>");
		}

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

		try {

			test_steps.add("========== Creating 1st room class ==========");
			nav.Setup(driver, test_steps);
			nav.RoomClass(driver, test_steps);
			roomClass.SearchButtonClick(driver);
			test_steps.add("Click on Search Button");
			app_logs.info("Click on Search Button");
			roomClass.SearchRoomClass(driver, roomClassNameWithoutNum, test_steps);
			roomClass.deleteRoomClass(driver, roomClassNameWithoutNum);
			nav.clickOnNewRoomClassButton(driver, test_steps);
			roomClass.create_RoomClass(driver, roomClassName1, roomClassAbb1, null, maxAdults, maxPersons, roomQuantity,
					test, test_steps);
			roomNumberAssigned.add(Utility.RoomNo);
			roomClass.CloseOpenedRoomClass(driver, roomClassName1, test_steps);
			nav.NewRoomClass(driver);
			test_steps.add("========== Creating 2nd room class ==========");
			roomClass.create_RoomClass(driver, roomClassName2, roomClassAbb2, null, maxAdults, maxPersons, roomQuantity,
					test, test_steps);
			roomNumberAssigned.add(Utility.RoomNo);
			for (int i = 0; i < roomNumberAssigned.size(); i++) {
				roomNumberAssigned.get(i);
			}

			app_logs.info(roomNumberAssigned);
			roomAbbri.add(roomClassName1 + ":" + roomClassAbb1);
			roomAbbri.add(roomClassName2 + ":" + roomClassAbb2);
			app_logs.info(roomAbbri);
			test_steps.add("========== Creating rate plan and Associating with room class ==========");
			nav.Inventory(driver);
			nav.Rates1(driver);
			rate.deleteRates(driver, rateNameWithoutNum);
			rate.create_Rate(driver, rateName, maxAdults, maxPersons, "100", maxAdults, maxPersons, "RateDisplay",
					"AutomationRatePolicy", "AutomationRateDescription", roomClassName1, roomClassName2, test_steps);
				
			if (map.get("WithPolicy").toString().equals("Yes")) {

				test_steps.add("========== Creating policy and Associating with  room class ==========");
				nav.Inventory(driver, test_steps);
				nav.policies(driver, test_steps);
				Policyname = map.get("PolicyName").toString() + Utility.getTimeStamp();
				policies.deleteAllPolicies(driver, test_steps, map.get("PolicyType").toString(), policyNameWithoutNum);
				policies.NewPolicybutton(driver, map.get("PolicyType").toString(), test_steps);
				Policyname = map.get("PolicyName").toString() + Utility.getTimeStamp();
				policies.Enter_Policy_Name(driver, Policyname, test_steps);
				policies.Enter_Policy_Desc(driver, map.get("PolicyText").toString(), map.get("PolicyDesc").toString());
				test_steps.add("Enter Policy Text and Description: <b>" + map.get("PolicyText").toString() + " AND "
						+ map.get("PolicyDesc").toString() + "</b>");
				policies.Enter_Checkin_Policy_Attributes(driver, map.get("PolicyAttributes").toString(),
						map.get("Percentage").toString());
				policies.Associate_Sources(driver);
				test_steps.add(" Associate Sources");
				policies.Associate_Seasons(driver);
				test_steps.add(" Associate Seasons");
				policies.Associate_RoomClasses(driver, roomClassName1);
				test_steps.add(" Associate Room Classes: " + roomClassName1);
				policies.Associate_RoomClasses(driver, roomClassName2);
				test_steps.add(" Associate Room Classes: " + roomClassName2);
				String rateplan[] = map.get("Rateplan").toString().split("\\|");
				policies.Associate_RatePlan(driver, rateplan[0]);
				test_steps.add(" Associate Rate Plans: " + map.get("Rateplan").toString());
				policies.Save_Policy(driver);
				test_steps.add("Click Save Button");
				test_steps.add("Policy Saved Successfully: <b>" + Policyname + "</b>");

			}

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Create room class and Rates", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Create room class and Rates", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Get checkIN and Checkout Date
		try {

			if (!(Utility.validateInput(map.get("CheckInDate").toString()))) {
				for (int i = 0; i < map.get("GuestFirstName").toString().split("\\|").length; i++) {
					checkInDates.add(Utility.getCurrentDate("dd/MM/yyyy"));
					checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(1), "MM/dd/yyyy", "dd/MM/yyyy"));
				}
			} else {
				checkInDates = Utility.splitInputData(map.get("CheckInDate").toString());
				checkOutDates = Utility.splitInputData(map.get("CheckOutDate").toString());
			}
			checkInDate = checkInDates.get(0) + "|" + checkInDates.get(1);
			checkOutDate = checkOutDates.get(0) + "|" + checkOutDates.get(1);
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

		// Get Time Zone and Property Name

		try {
			propertyName = properties.getProperty(driver, test_steps);
			nav.Setup(driver);
			nav.Properties(driver);
			nav.open_Property(driver, test_steps, propertyName);
			nav.click_PropertyOptions(driver, test_steps);
			timeZone = nav.get_Property_TimeZone(driver);
			properties.uncheck_GuaranteedCheckBoxProperties(driver, test_steps, "No");
			properties.SaveButton(driver);
			currentDate = res.getTodaysDate("MM/dd/yyyy", timeZone);
			previousDate = res.getPreviousDate("MM/dd/yyyy", timeZone);
			System.out.println(currentDate + "" + previousDate);

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

		// Enable Toggle Set Rooms To Dirty On Check-In/Check-Out
		try {
			test_steps.add("<b>****Set Rooms To Dirty On Check-In/Check-Out****</b>");
			nav.TaskManagement_TabExist(driver);
			test_steps.add("Task Management Tab Exist");
			nav.TaskManagement(driver);
			test_steps.add("Click on Task Management");
			app_logs.info("Click on Task Management");
			task_mang.getToggleStatus_CheckInCheckOut(driver);
			if (map.get("WithCheckINCheckOutToggle").toString().equals("Yes")) {
				if (Utility.toggle == true) {
					test_steps.add("Set Rooms To Dirty On Check-In/Check-Out Toggle Already Enabled");
					app_logs.info("Set Rooms To Dirty On Check-In/Check-Out Toggle Already Enabled");
				} else if (Utility.toggle == false) {
					task_mang.SetRoomsToDirtyFlag(driver, true);
					test_steps.add("Successfully Enabled Toggle Set Rooms To Dirty On Check-In/Check-Out ");
					app_logs.info("Successfully Enabled Toggle Set Rooms To Dirty On Check-In/Check-Out");
				}
			} else if (map.get("WithCheckINCheckOutToggle").toString().equals("No")) {
				if (Utility.toggle == true) {
					task_mang.SetRoomsToDirtyFlag(driver, false);
					test_steps.add("Successfully Disabled Toggle Set Rooms To Dirty On Check-In/Check-Out ");
					app_logs.info("Successfully Disabled Toggle Set Rooms To Dirty On Check-In/Check-Out");

				} else if (Utility.toggle == false) {
					test_steps.add("Set Rooms To Dirty On Check-In/Check-Out Toggle Already Disabled");
					app_logs.info("Set Rooms To Dirty On Check-In/Check-Out Toggle Already Disabled");

				}
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Enabled Toggle Set Rooms To Dirty On Check-In/Check-Out", testName,
						"Task Management", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Enabled Toggle Set Rooms To Dirty On Check-In/Check-Out", testName,
						"Task Management", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Create New Reservation
		try {

			nav.Reservation_Backward_2(driver);
			app_logs.info("Navigate to Reservation");
			test_steps.add("<b>****Start Creating New Reservation****</b>");
			res.click_NewReservation(driver, test_steps);
			res.select_Dates(driver, test_steps, checkInDate, checkOutDate, map.get("Adults").toString(),
					map.get("Children").toString(), map.get("Rateplan").toString(), map.get("PromoCode").toString(),
					map.get("IsSplitRes").toString());
			res.clickOnFindRooms(driver, test_steps);

			String roomclass = roomClassName1 + "|" + roomClassName2;
			app_logs.info(roomclass);

			res.selectRoom1(driver, test_steps, roomClassName1, roomNumberAssigned.get(0), "");
			res.selectRoom1(driver, test_steps, roomClassName2, roomNumberAssigned.get(1), "");

			depositAmount = res.deposit(driver, test_steps, map.get("IsDepositOverride").toString(),
					map.get("DepositOverrideAmount").toString());

			res.clickNext(driver, test_steps);
			yearDate = Utility.getFutureMonthAndYearForMasterCard(driver, test_steps);
			Random random = new Random();
			int x = random.nextInt(900);
			guestFirstName = map.get("GuestFirstName").toString() + x;
			guestLastName = map.get("GuestLastName").toString() + x;
			res.enter_MRB_MailingAddressForMRB(driver, test_steps, map.get("Salutation").toString(), guestFirstName,
					guestLastName, map.get("PhoneNumber").toString(), map.get("AltenativePhone").toString(),
					map.get("Email").toString(), map.get("Account").toString(), map.get("AccountType").toString(),
					map.get("Address1").toString(), map.get("Address2").toString(), map.get("Address3").toString(),
					map.get("City").toString(), map.get("Country").toString(), map.get("State").toString(),
					map.get("PostalCode").toString(), map.get("IsGuesProfile").toString(),
					map.get("IsAddMoreGuestInfo").toString(), map.get("IsSplitRes").toString(), rooms);
			if ((map.get("Account").toString().equalsIgnoreCase("") || map.get("Account").toString().isEmpty())) {
				res.enter_PaymentDetails(driver, test_steps, map.get("PaymentType").toString(),
						map.get("CardNumber").toString(), map.get("NameOnCard").toString(), yearDate);
			}

			app_logs.info(rooms);
			res.enter_MarketSegmentDetails(driver, test_steps, map.get("TravelAgent").toString(),
					map.get("MarketSegment").toString(), map.get("Referral").toString());
			res.clickBookNow(driver, test_steps);
			reservation = res.get_ReservationConfirmationNumber(driver, test_steps);
			res.clickCloseReservationSavePopup(driver, test_steps);
			panelGuestName = res.getPanelStatusGuestName(driver);
			status = res.getPanelStatusStatusName(driver);
			roomCharges = res.getStayInfoRoomCharges(driver, test_steps);
			roomNos = res.getStayInfoRoomNo(driver, test_steps);
			res.getStayInfoGuestName(driver, test_steps);
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

		// Check In Policy
		try {

			res.click_DeatilsTab(driver, test_steps);
			tripSummaryRoomCharges = res.get_TripSummaryRoomChargesWithoutCurrency(driver, test_steps);

			test_steps.add("<b>****Start Verifying Check-In Policy In Detail Page****</b>");
			if (map.get("WithPolicy").toString().equals("No")) {
				res.verifyPOLICIESANDDISCLAIMERSNoShowPolicy(driver, test_steps, map.get("NoPolicy").toString(),
						map.get("PolicyType").toString(), map.get("Percentage").toString());
			} else if (map.get("WithPolicy").toString().equals("Yes")) {
				res.verifyCPReservationDetailTabPolicies(driver, test_steps, map.get("PolicyType").toString(),
						map.get("Percentage").toString(), Policyname, map.get("PolicyText").toString());

			}
			test_steps.add("<b>****Start CheckIn Reservation****</b>");
			res.clickCheckInAllButton(driver, test_steps);
			test_steps.add("<b>****CheckIn All Window****</b>");
			res.verifyReservationPopWindow(driver, map.get("HeaderTitle").toString(), panelGuestName, status,
					reservation, test_steps, map.get("PolicyTypeCheckOut").toString());
			test_steps.add("<b>****CheckIn All Window Verifying GUEST CONTACT INFO ****</b>");
			res.clickGuestContactInfoCollapseIcon(driver, test_steps);
			String[] array = guestFirstName.split("\\|");
			String[] array1 = guestLastName.split("\\|");
			String guestFirstNames = Utility.guestFirstName + "|" + array[1];
			String guestLastNames = Utility.guestLastName + "|" + array1[1];
			app_logs.info(guestFirstNames);
			app_logs.info(guestLastNames);

			res.verifyGuestContactInfo(driver, test_steps, map.get("Salutation").toString(), guestFirstNames,
					guestLastNames, map.get("Email").toString(), map.get("PhoneNumber").toString());
			test_steps.add("<b>****CheckIn All Window Verifying STAY INFO ****</b>");
			res.verifyCheckINStayInfo(driver, test_steps, checkInDate, checkOutDate, map.get("Adults").toString(),
					map.get("Children").toString(), map.get("RoomClass").toString(), roomCharges,
					map.get("Salutation").toString(), guestFirstNames, guestLastNames, map.get("Rateplan").toString());
			res.generatGuestReportToggle(driver, test_steps, map.get("IsGuesRegistration").toString());
			res.completeCheckInProcess(driver, test_steps);

			boolean isExist=res.verifyCheckINPaymentWindow(driver);
			if(isExist)
			{
				app_logs.info("Confirm Check IN with payment");
				if (res.isRoomDirty) {
					test_steps.add("Successfully Verify The Dirty Room Status Pop-Up Message"
							+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/682469' target='_blank'>"
							+ "Click here to open TestRail: C682469</a><br>");
				}
				double tripAmount = Double.valueOf(tripSummaryRoomCharges);
				int amt = (int) tripAmount;
				String tripAmounts = String.valueOf(amt);
				app_logs.info(tripAmounts);
				// Verification No Show Payment

				res.commonMethodForPaymentPopUpVerification(driver, test_steps, map.get("CheckInPaymentMsg").toString(),
						yearDate, map.get("NameOnCard").toString(), map.get("CardNo").toString(),
						map.get("PaymentType").toString(), map.get("Authorize").toString(), tripAmounts,
						res.TripSummary_TaxServices, res.TripSummary_RoomCharges, map.get("PolicyType").toString(),
						res.AmountPaid);
				res.verifyReservationPopWindowPolicyName(driver, test_steps, Policyname,
						map.get("PolicyType").toString());
				res.verifyPolicyToolTip(driver, test_steps, Policyname, map.get("PolicyText").toString(),
						map.get("PolicyType").toString());
				res.setAsMainPayment(driver, test_steps, map.get("PaymentType").toString(),
						map.get("PaymentTypeTwo").toString(), previousDate, map.get("PolicyType").toString());
				paymentMethod = res.getPaymentMethod(driver, test_steps);
				// Added Notes and Click Log button

				res.addNotesAndClickLogORPayORAuthorizedButton(driver, test_steps, map.get("Notes").toString());
				test_steps.add("<b>****Start Verifying Cancellation Successful Window****</b>");
				// Verification No Show Successful Window
				amount = res.Amount;
				System.out.println(amount);
				Wait.wait10Second();

				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
				String pastDate = dateFormat.format(previousDate);
				System.out.println("Previous Date: " + pastDate);

				res.commonMethodForSuccessfullWindowVerification(driver, test_steps, map.get("SuccessMsg").toString(),
						map.get("StatusApproved").toString(), map.get("PaymentTypeTwo").toString(),
						map.get("Notes").toString(), map.get("BalanceMoney").toString(), map.get("Capture").toString(),
						amount, map.get("PolicyTypeCheckOut").toString(), pastDate);

				res.clickCloseButtonOfSuccessModelPopup(driver, test_steps);
			}
			else
			{
				app_logs.info("Confirm Check IN");

				if (res.isRoomDirty) {
					test_steps.add("Successfully Verify The Dirty Room Status Pop-Up Message"
							+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/682469' target='_blank'>"
							+ "Click here to open TestRail: C682469</a><br>");
				}
			}
		

			if (res.isRoomReserved || res.isRoomUnAssigned) {
				app_logs.info("Room Changed");
				res.getRoomsOnDetailPage(driver, newRooms);
				System.out.println(newRooms);
			}
            Wait.wait10Second();
			String statusPath = "//span[@class='ng-status'][contains(text(),'In-House')]";
			Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(statusPath)), driver);

			test_steps.add("<b>****Start Verifying In-House Status****</b>");
			res.verifyReservationStatusStatus(driver, test_steps, map.get("Status").toString());
			test_steps.add("<b>****Start Verifying Check-Out All Button ****</b>");
			res.verifyCheckOutAllButton(driver, test_steps);

			if(isExist){
				test_steps.add("<b>****Start Verifying Folio Details Line Item****</b>");
				res.click_Folio(driver, test_steps);
				res.verifyFolioLineItem(driver, test_steps, previousDate, map.get("PaymentTypeTwo").toString(),
						map.get("PaymentTypeTwo").toString(), amount);

			}

			// Click History Tab
			res.click_History(driver, test_steps);
			if (res.isRoomReserved || res.isRoomUnAssigned) {
				app_logs.info("Room Changed");
				res.verifyHistoryForCheckin(driver, test_steps, map.get("HistoryDesc").toString(),
						rc.Abbreviation.get(0).toString(), newRooms.get(0), map.get("Name").toString(), roomAbbri,
						newRooms);

			} else {
				app_logs.info("Room Not Changed");
				res.verifyHistoryForCheckin(driver, test_steps, map.get("HistoryDesc").toString(), roomAbbri.get(0),
						res.primary, map.get("Name").toString(), roomAbbri, rooms);

			}

			if(isExist) {
				if (res.isRoomReserved || res.isRoomUnAssigned) {
					app_logs.info("Room Changed");
					String[] roomAbb = roomAbbri.get(0).split(":");
					String abb = roomAbb[1].trim();
					System.out.println(" Room No: " + abb);

					res.commonMethodToverifyHistoryTabDescriptionForCheckINCheckOut(driver, test_steps, amount,
							map.get("PolicyType").toString(), abb, newRooms.get(0), paymentMethod);

				} else {
					app_logs.info("Room Not Changed");
					String[] roomAbb = roomAbbri.get(0).split(":");
					String abb = roomAbb[1].trim();
					System.out.println(" Room No: " + abb);

					res.commonMethodToverifyHistoryTabDescriptionForCheckINCheckOut(driver, test_steps, amount,
							map.get("PolicyType").toString(), abb, res.primary, paymentMethod);
				}
			}

			if (map.get("WithPolicy").toString().equals("Yes")) {
				test_steps.add("<b>****Start Verifying Updated By on Payment Item Detail Popup****</b>");
				res.click_Folio(driver, test_steps);
				if(isExist)
				{
					res.verifyPaymentDetailUpdateBy(driver, map.get("NameOnCard").toString(),
							map.get("UpdateByName").toString(), paymentMethod);
					res.clickPaymentDetailClose(driver, test_steps);
				}
				else
				{
					res.verifyPaymentDetailUpdateBy(driver, map.get("NameOnCard").toString(),
							map.get("UpdateByName").toString(), map.get("PaymentType").toString());
					res.clickPaymentDetailCancel(driver, test_steps);

				}
			

				test_steps.add("Successfully  Verified  Update By : <b>" + map.get("UpdateByName").toString() + "</b> "

						+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/682505' target='_blank'>"
						+ "Click here to open TestRail: C682505</a> <br>");

			}

			test_steps.add("<b>****Start Verifying Room Status ON GS****</b>");
			nav.Guestservices(driver);
			test_steps.add("Navigated to Guestservices");
			roomstatus.verifyRoomStatusTabEnabled(driver, test_steps);
			roomstatus.verifiedGuestServicesDataLoadedOrNot(driver, test_steps);
			res.verifyRoomStatusAfterCheckedInRoom(driver, test_steps, rooms, roomAbbri,
					map.get("DirtyStatus").toString(), map.get("CleanStatus").toString(),
					map.get("RoomStatus").toString(), map.get("OccupiedStatus").toString());

			nav.Click_ReservationMenu(driver);
			app_logs.info("Navigate to Reservation");
			test_steps.add("<b>****Start Verifying Room Status ON TapeChart****</b>");
			nav.TapeChart(driver);
			test_steps.add("Successfully Navigate to TapeChart");
			tapeChart.TapeChart_Search_MRB(driver, checkInDate, map.get("Adults").toString(), test_steps);
			tapeChart.Verify_CheckedIn_RoomsStatus(driver, test_steps, rooms, roomAbbri,
					map.get("DirtyStatus").toString(), map.get("CleanStatus").toString());

			test_steps.add("Successfully  Verified  Room Status on GS and Tape Chart"
					+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/681834' target='_blank'>"
					+ "Click here to open TestRail: C681834</a> <br>");

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify CheckIn Policy", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify CheckIn Policy", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		}

	}

	@DataProvider
	public Object[][] getData() {

		return Utility.getDataMap("VerifyCheckInCPReservationMRB", excel);

	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
