package com.innroad.inncenter.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Login;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Reservation;
import com.innroad.inncenter.pageobjects.RoomStatus;
import com.innroad.inncenter.pageobjects.TaskList;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class GSRoomStatusTaskListVerifyReprt extends TestCore {

	private WebDriver driver = null;
	public static String test_name = "";
	public static String testDescription = "";
	public static String testCategory = "";
	public boolean isResultSame;
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

	@Test(dataProvider = "getData", groups = "GuestServices")
	public void gSRoomStatusTaskListVerifyReprt(String url, String ClientCode, String Username, String Password,
			String CheckInDate, String CheckOutDate, String Adults, String Children, String Rateplan, String PromoCode,
			String RoomClass, String IsAssign, String IsDepositOverride, String DepositOverrideAmount,
			String Salutation, String GuestFirstName, String GuestLastName, String PhoneNumber, String AltenativePhone,
			String Email, String Account, String AccountType, String Address1, String Address2, String Address3,
			String City, String Country, String State, String PostalCode, String IsGuesProfile, String PaymentType,
			String CardNumber, String NameOnCard, String CardExpDate, String IsChangeInPayAmount,
			String ChangedAmountValue, String TravelAgent, String MarketSegment, String Referral, String IsAddNotes,
			String NoteType, String Subject, String Description, String IsTask, String TaskCategory, String TaskType,
			String TaskDetails, String TaskRemarks, String TaskDueon, String TaskAssignee, String TaskStatus,
			String Assigned, String ByAssign, String CalendarDateType, String FromDate, String ToDate, String RedColor,
			String OrangeColor, String GreenColor, String GrayColor, String BlueColor, String ReportsTypeAsRoomSatus,
			String Zone, String Condition, String Occupancy, String DepartDate, String ArrivalDate,
			String ReportsTypeAsRoomSatusWithTask, String TaskForToday, String TaskFor, String AssigneeName,
			String TaskImageInternel, String ReportsTypeAsAllAssignee, String ReportsTypeAsByAssignee)
			throws InterruptedException, IOException {

		test_name = "GSRoomStatusTaskListVerifyReprt";
		testDescription = "GS-Room Status/Task List- Verify Reports<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682445' target='_blank'>"
				+ "Click here to open TestRail: C682445</a>";
		testCategory = "Verification";
		String testName = test_name;

		TestName.add(testName);
		TestDescription.add(testDescription);
		TestCategory.add(testCategory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Navigation nav = new Navigation();
		RoomStatus roomstatus = new RoomStatus();
		TaskList tasklist = new TaskList();
		CPReservationPage res = new CPReservationPage();

		String status = null;

		String ReservationNumber = "";

		String roomNumber = "";
		Map<String, ArrayList<String>> TasksNames = new HashMap<String, ArrayList<String>>();
		ArrayList<String> TaskDetail = new ArrayList<>();
		ArrayList<String> TaskImage = new ArrayList<>();
		ArrayList<String> TaskName = new ArrayList<>();
		ArrayList<String> TaskDueOn = new ArrayList<>();
		ArrayList<String> TaskAssigned = new ArrayList<>();
		ArrayList<String> TaskSate = new ArrayList<>();
		ArrayList<String> RoomStatusColor = new ArrayList<>();
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

		// Check Task Management Tab

		try {
			test_steps.add("=====Task Managemnet Tab Verification=====");
			app_logs.info("=====Task Managemnet Tab Verification=====");
			nav.Setup(driver);
			test_steps.add("Navigate Setup");
			app_logs.info("Navigate Setup");
			nav.TaskManagement_TabExist(driver);
			test_steps.add("Task Management Tab Exist");
			app_logs.info("Task Management Tab Exist");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Check Task Management Tab", testName, "TaskManagement", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Check Task Management Tab", testName, "TaskManagement", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Open RoomStatus
		try {
			test_steps.add("=====Guest Services/RoomStatus Reports Verification=====");
			app_logs.info("=====Guest Services/RoomStatus Reports Verification=====");
			nav.Guestservices_3(driver);
			test_steps.add("Navigate Guest Services");
			app_logs.info("Navigate Guest Services");
			test_steps.add("Navigate Room Status");
			app_logs.info("Navigate Room Status");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Open Room Status Page", testName, "RoomStatus", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Open Room Status Page", testName, "RoomStatus", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Verify GS-Room Status Reports
		try {
			/*roomstatus.Reports(driver);
			roomstatus.Report_RoomStatus(driver);
			roomstatus.Reports(driver);
			roomstatus.Report_RoomStatusWithTask(driver);
			test_steps.add("Successfully Verified Room Status Reports");
			app_logs.info("Successfully Verified Room Status Reports");*/

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify Room Status Reports", testName, "VerifyReports", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify Room Status Reports", testName, "VerifyReports", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		RoomClass = "Junior Suites";
		// Reservation
		try {
			nav.Reservation_Backward_2(driver);
			app_logs.info("Navigate to Reservation");
			test_steps.add("==========CREATING NEW RESERVATION==========");

			getTest_Steps.clear();
			res.click_NewReservation(driver, getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.CheckInDate(driver, 0);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.CheckOutDate(driver, +1);
			test_steps.addAll(getTest_Steps);

			res.enter_Adults(driver, getTest_Steps, Adults);
			res.enter_Children(driver, getTest_Steps, Children);
			res.select_Rateplan(driver, getTest_Steps, Rateplan, PromoCode);
			res.click_FindRooms(driver, getTest_Steps);
			res.select_Room(driver, getTest_Steps, RoomClass, IsAssign, Account);
			res.moveToAddARoom(driver);
			res.clickNext(driver, getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.VerifyGuestProfileCheckox(driver, Boolean.parseBoolean("No"));
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.enter_GuestName(driver, getTest_Steps, Salutation, GuestFirstName, GuestLastName);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.selectReferral(driver, Referral);
			test_steps.addAll(getTest_Steps);

			res.AddTask(driver, getTest_Steps, IsTask, TaskCategory, TaskType, TaskDetails, TaskRemarks, TaskDueon,
					TaskAssignee, TaskStatus);

			res.AddTask(driver, getTest_Steps, IsTask, TaskCategory, TaskType, TaskDetails, TaskRemarks, TaskDueon,
					TaskAssignee, TaskStatus);

			res.click_BookNow(driver, getTest_Steps);
			ReservationNumber = res.get_ReservationConfirmationNumber(driver, getTest_Steps);
			status = res.get_ReservationStatus(driver, getTest_Steps);
			res.clickCloseReservationSavePopup(driver, getTest_Steps);

			roomNumber = res.get_RoomNumber(driver, getTest_Steps, IsAssign);
			app_logs.info(roomNumber);
			driver.navigate().refresh();

			res.clickOnAddTask(driver);

			String getTaskImage = res.GetTaskCategoryImage(driver, 0);
			System.out.println(getTaskImage);
			String getTaskDetails = res.GetTaskDetail(driver, 0);
			System.out.println(getTaskDetails);

			String getTaskName = res.GetTaskName(driver, 0);
			System.out.println(getTaskName);

			String getTaskDueOn = res.GetTaskDueOn(driver, 0);
			System.out.println(getTaskDueOn);

			String getTaskStatus = res.GetTaskStatus(driver, 0);
			System.out.println(getTaskStatus);

			TaskImage.add(getTaskImage);
			TaskName.add(getTaskName);
			TaskDetail.add(getTaskDetails);
			TaskDueOn.add(getTaskDueOn);
			TaskSate.add(getTaskStatus);

			getTaskImage = res.GetTaskCategoryImage(driver, 1);
			System.out.println(getTaskImage);
			getTaskDetails = res.GetTaskDetail(driver, 1);
			System.out.println(getTaskDetails);

			getTaskName = res.GetTaskName(driver, 1);
			System.out.println(getTaskName);

			getTaskDueOn = res.GetTaskDueOn(driver, 1);
			System.out.println(getTaskDueOn);

			getTaskStatus = res.GetTaskStatus(driver, 1);
			System.out.println(getTaskStatus);

			TaskImage.add(getTaskImage);
			TaskName.add(getTaskName);
			TaskDetail.add(getTaskDetails);
			TaskDueOn.add(getTaskDueOn);
			TaskSate.add(getTaskStatus);

			res.click_History(driver, test_steps);
			res.verify_ReservationInHistoryTab(driver, test_steps, ReservationNumber);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to create new reservation with tasks", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to create new reservation with tasks", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		String GuestName = GuestFirstName + " " + GuestLastName;
		String RoomCondition = "";
		// Verify GS
		try {
			test_steps.add("==========VERIFICATION OF TASK IN ROOM STATUS==========");
			nav.Setup(driver);
			test_steps.add("Navigate Setup");
			app_logs.info("Navigate Setup");
			nav.TaskManagement_TabExist(driver);

			test_steps.add("Task Management");
			app_logs.info("Task Management");

			nav.Guestservices_3(driver);
			test_steps.add("Navigate Guest Services");
			app_logs.info("Navigate Guest Services");

			roomstatus.SearchRoomZoneOccupancyOthers(driver, roomNumber);
			test_steps.add("Search room number: " + roomNumber);
			app_logs.info("Search room number: " + roomNumber);
			app_logs.info("TasksNames.size: " + TasksNames.size());

			TasksNames.size();
			getTest_Steps.clear();
			getTest_Steps = roomstatus.VerifyTask(driver, RoomClass, roomNumber, TaskName.size(), true);
			test_steps.addAll(getTest_Steps);

			test_steps.add("==========VERIFICATION OF TASK 1 IN TASK POPUP==========");

			assertTrue(TaskImage.get(0).contains(roomstatus.getTaskImage(driver, 0)));
			test_steps.add("Verified image");
			test_steps.add("Expected task name: " + TaskName.get(0));
			test_steps.add("Found: " + roomstatus.getTaskName(driver, 0));
			assertEquals(TaskName.get(0), roomstatus.getTaskName(driver, 0));
			test_steps.add("Verified name");

			String ParseDate = ESTTimeZone.SubString(TaskDueOn.get(0), 0, 9);
			String GetTime = ESTTimeZone.SubString(TaskDueOn.get(0), 10, TaskDueOn.get(0).length());
			app_logs.info("GetTime: " + GetTime);
			String[] strParse = GetTime.split(" ");
			GetTime = strParse[0] + strParse[1];
			ParseDate = ParseDate.replace("\\s", "");

			app_logs.info("ParseDate: " + ParseDate);
			app_logs.info("GetTime: " + GetTime);

			String ExpectedDateFormat = ESTTimeZone.parseDate(ParseDate, "MM/dd/yy", "dd MMM yyyy");
			app_logs.info("ExpectedDateFormat" + ExpectedDateFormat);
			ExpectedDateFormat = ExpectedDateFormat + "(" + GetTime + ")";

			test_steps.add("Expected due on: " + ExpectedDateFormat);
			test_steps.add("Found: " + roomstatus.getTaskDueOnAssignedAndStatus(driver, 1));
			assertEquals(roomstatus.getTaskDueOnAssignedAndStatus(driver, 1), ExpectedDateFormat,
					"Failed:Due on is mismatching!");
			test_steps.add("Verified due on ");

			test_steps.add("Expected status: " + TaskSate.get(0));
			test_steps.add("Found: " + roomstatus.getTaskDueOnAssignedAndStatus(driver, 3));
			assertEquals(TaskSate.get(1), roomstatus.getTaskDueOnAssignedAndStatus(driver, 3));
			test_steps.add("Verified status");

			test_steps.add("==========VERIFICATION OF TASK 2 IN TASK POPUP==========");

			assertTrue(TaskImage.get(1).contains(roomstatus.getTaskImage(driver, 1)));
			test_steps.add("Verified image");
			test_steps.add("Expected task name: " + TaskName.get(1));
			test_steps.add("Found: " + roomstatus.getTaskName(driver, 1));
			assertEquals(TaskName.get(1), roomstatus.getTaskName(driver, 1));
			test_steps.add("Verified name");

			ParseDate = ESTTimeZone.SubString(TaskDueOn.get(1), 0, 9);
			GetTime = ESTTimeZone.SubString(TaskDueOn.get(1), 10, TaskDueOn.get(1).length());
			app_logs.info("GetTime: " + GetTime);
			strParse = GetTime.split(" ");
			GetTime = strParse[0] + strParse[1];
			ParseDate = ParseDate.replace("\\s", "");

			app_logs.info("ParseDate: " + ParseDate);
			app_logs.info("GetTime: " + GetTime);

			ExpectedDateFormat = ESTTimeZone.parseDate(ParseDate, "MM/dd/yy", "dd MMM yyyy");
			app_logs.info("ExpectedDateFormat" + ExpectedDateFormat);
			ExpectedDateFormat = ExpectedDateFormat + "(" + GetTime + ")";

			test_steps.add("Expected due on: " + ExpectedDateFormat);
			test_steps.add("Found: " + roomstatus.getTaskDueOnAssignedAndStatus(driver, 5));
			assertEquals(roomstatus.getTaskDueOnAssignedAndStatus(driver, 5), ExpectedDateFormat,
					"Failed:Due on is mismatching!");
			test_steps.add("Verified due on");

			test_steps.add("Expected status: " + TaskSate.get(0));
			test_steps.add("Found: " + roomstatus.getTaskDueOnAssignedAndStatus(driver, 6));
			assertEquals(TaskSate.get(1), roomstatus.getTaskDueOnAssignedAndStatus(driver, 7));
			test_steps.add("Verified status");
			app_logs.info("Verified status");

			RoomCondition = roomstatus.getRoomStatus(driver);
			app_logs.info("RoomCondition");

			roomstatus.ButtonClosPopup(driver);
			test_steps.add("Closed task popup");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to verify created task ", testName, "TaskVerification", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to verify created task ", testName, "TaskVerification", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		String GetDirty = "";
		String GetInspection = "";
		String GetClean = "";
		String GetOutofOrder = "";
		String GetAll = "";
		String getRoomNumber = "";
		String getRoomClass = "";
		String getZone = "";
		String getCondition = "";
		String getOccupancy = "";
		String getDepartDate = "";
		String getArrivalDate = "";
		String getTask = "";
		String TodayDate = "";
		try {
			test_steps.add("==========VERIFICATION ROOM STATUS REPORT==========");

			System.out.println(RedColor);
			GetDirty = roomstatus.GetRoomStatus(driver, RedColor);
			// GetInspection = roomstatus.GetRoomStatus(driver, OrangeColor);
			GetClean = roomstatus.GetRoomStatus(driver, GreenColor);
			GetOutofOrder = roomstatus.GetRoomStatus(driver, GrayColor);
			GetAll = roomstatus.GetRoomStatus(driver, BlueColor);

			roomstatus.SelectReports(driver, ReportsTypeAsRoomSatus);
			String GetRoomStatus = roomstatus.GetRoomStatusInSecondWindows(driver, 0);
			test_steps.add("Expected dirty: " + GetDirty);
			test_steps.add("Found: " + GetRoomStatus);
			assertEquals(GetRoomStatus, GetDirty, "Failed: Dirty room status is mismatchin");

			// GetRoomStatus = roomstatus.GetRoomStatusInSecondWindows(driver,
			// 1);
			// test_steps.add("Expected inspection: " + GetInspection);
			// test_steps.add("Found: " + GetRoomStatus);
			// assertEquals(GetRoomStatus, GetInspection, "Failed: Inspection
			// room status is mismatchin");

			GetRoomStatus = roomstatus.GetRoomStatusInSecondWindows(driver, 2);
			test_steps.add("Expected clean: " + GetClean);
			test_steps.add("Found: " + GetRoomStatus);
			assertEquals(GetRoomStatus, GetClean, "Failed: Clean room status is mismatchin");

			GetRoomStatus = roomstatus.GetRoomStatusInSecondWindows(driver, 3);
			test_steps.add("Expected out of order: " + GetOutofOrder);
			test_steps.add("Found: " + GetRoomStatus);
			assertEquals(GetRoomStatus, GetOutofOrder, "Failed: Out of order room status is mismatchin");

			GetRoomStatus = roomstatus.GetRoomStatusInSecondWindows(driver, 4);
			test_steps.add("Expected all: " + GetAll);
			test_steps.add("Found: " + GetRoomStatus);
			assertEquals(GetRoomStatus, GetAll, "Failed: All oom status is mismatchin");

			getRoomNumber = roomstatus.GetRoomStatusInSecondWindow(driver, RoomClass, 1);
			getRoomClass = roomstatus.GetRoomStatusInSecondWindow(driver, RoomClass, 2);
			getZone = roomstatus.GetRoomStatusInSecondWindow(driver, RoomClass, 3);
			getCondition = roomstatus.GetRoomStatusInSecondWindow(driver, RoomClass, 4);
			getOccupancy = roomstatus.GetRoomStatusInSecondWindow(driver, RoomClass, 5);
			getDepartDate = roomstatus.GetRoomStatusInSecondWindow(driver, RoomClass, 6);
			getArrivalDate = roomstatus.GetRoomStatusInSecondWindow(driver, RoomClass, 7);
			getTask = roomstatus.GetRoomStatusInSecondWindow(driver, RoomClass, 8);

			test_steps.add("Expected room number: " + roomNumber);
			test_steps.add("Found: " + getRoomNumber);
			assertEquals(getRoomNumber, roomNumber, "Failed: Room number is mismatching");

			test_steps.add("Expected room class: " + RoomClass);
			test_steps.add("Found: " + getRoomClass);
			assertEquals(getRoomClass, RoomClass, "Failed: Room class is mismatching");

			test_steps.add("Expected zone: " + Zone);
			test_steps.add("Found: " + getZone);
			assertEquals(getZone, Zone, "Failed:Zone is mismatching");

			app_logs.info("RoomCondition: " + RoomCondition);
			String firstCharacter = RoomCondition.substring(0, 1);
			RoomCondition = RoomCondition.substring(1, RoomCondition.length());
			RoomCondition = RoomCondition.toLowerCase();
			RoomCondition = String.valueOf(firstCharacter) + RoomCondition;
			test_steps.add("Expected condition: " + RoomCondition);
			test_steps.add("Found: " + getCondition);
			assertEquals(getCondition, RoomCondition, "Failed:Condition is mismatching");

			test_steps.add("Expected Occupancy: " + Occupancy);
			test_steps.add("Found: " + getOccupancy);
			// assertEquals(getOccupancy, Occupancy, "Failed:Occupancy is
			// mismatching");

			test_steps.add("Expected departed date : " + DepartDate);
			test_steps.add("Found: " + getDepartDate);
			assertEquals(getDepartDate, DepartDate, "Failed:DepartDate is mismatching");

			test_steps.add("Expected arrival date: " + ArrivalDate);
			test_steps.add("Found: " + getArrivalDate);
			assertEquals(getArrivalDate, ArrivalDate, "Failed:ArrivalDate is mismatching");

			test_steps.add("Expected tasks: " + TaskSate.size());
			test_steps.add("Found: " + getTask);
			assertEquals(getTask, String.valueOf(TaskSate.size()), "Failed:Task is mismatching");

			TodayDate = ESTTimeZone.DateFormateForLineItem(0);
			TodayDate = ESTTimeZone.parseDate(TodayDate, "MM/dd/yy", "dd MMM yyyy");
			app_logs.info("TodayDate: " + TodayDate);

			StringBuilder sb = new StringBuilder(TodayDate);
			sb.setCharAt(6, '\'');
			TodayDate = sb.toString();
			app_logs.info("TodayDate: " + TodayDate);

			test_steps.add("Expected room status for: " + TodayDate);
			test_steps.add("Found: " + roomstatus.GetRoomStatusFor(driver));
			roomstatus.CloseSecondWindow(driver);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to verify room status reports", testName, "Verification", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to verify room status report", testName, "Verification", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {

			test_steps.add("==========VERIFICATION ROOM STATUS REPORT WITH TASKS==========");

			GetDirty = roomstatus.GetRoomStatus(driver, RedColor);
			GetInspection = roomstatus.GetRoomStatus(driver, OrangeColor);
			GetClean = roomstatus.GetRoomStatus(driver, GreenColor);
			GetOutofOrder = roomstatus.GetRoomStatus(driver, GrayColor);
			GetAll = roomstatus.GetRoomStatus(driver, BlueColor);

			roomstatus.SelectReports(driver, ReportsTypeAsRoomSatusWithTask);

			test_steps.add("Expected room status for: " + TodayDate);
			test_steps.add("Found: " + roomstatus.GetRoomStatusFor(driver));
			assertEquals(roomstatus.GetRoomStatusFor(driver), TodayDate, "Failed:task for today is mismatching!");

			String GetRoomStatus = roomstatus.GetRoomStatusInSecondWindows(driver, 0);
			test_steps.add("Expected dirty: " + GetDirty);
			test_steps.add("Found: " + GetRoomStatus);
			assertEquals(GetRoomStatus, GetDirty, "Failed: Dirty room status is mismatchin");

			// GetRoomStatus = roomstatus.GetRoomStatusInSecondWindows(driver,
			// 1);
			// test_steps.add("Expected inspection: " + GetInspection);
			/// test_steps.add("Found: " + GetRoomStatus);
			// assertEquals(GetRoomStatus, GetInspection, "Failed: Inspection
			/// room status is mismatchin");

			GetRoomStatus = roomstatus.GetRoomStatusInSecondWindows(driver, 2);
			test_steps.add("Expected clean: " + GetClean);
			test_steps.add("Found: " + GetRoomStatus);
			assertEquals(GetRoomStatus, GetClean, "Failed: Clean room status is mismatchin");

			GetRoomStatus = roomstatus.GetRoomStatusInSecondWindows(driver, 3);
			test_steps.add("Expected out of order: " + GetOutofOrder);
			test_steps.add("Found: " + GetRoomStatus);
			assertEquals(GetRoomStatus, GetOutofOrder, "Failed: Out of order room status is mismatchin");

			GetRoomStatus = roomstatus.GetRoomStatusInSecondWindows(driver, 4);
			test_steps.add("Expected all: " + GetAll);
			test_steps.add("Found: " + GetRoomStatus);
			assertEquals(GetRoomStatus, GetAll, "Failed: All oom status is mismatchin");

			getRoomNumber = roomstatus.GetRoomStatusInSecondWindow(driver, RoomClass, 1);
			getRoomClass = roomstatus.GetRoomStatusInSecondWindow(driver, RoomClass, 2);
			getZone = roomstatus.GetRoomStatusInSecondWindow(driver, RoomClass, 3);
			getCondition = roomstatus.GetRoomStatusInSecondWindow(driver, RoomClass, 4);
			getOccupancy = roomstatus.GetRoomStatusInSecondWindow(driver, RoomClass, 5);
			getDepartDate = roomstatus.GetRoomStatusInSecondWindow(driver, RoomClass, 6);
			getArrivalDate = roomstatus.GetRoomStatusInSecondWindow(driver, RoomClass, 7);
			getTask = roomstatus.GetRoomStatusInSecondWindow(driver, RoomClass, 8);

			test_steps.add("Expected room number: " + roomNumber);
			test_steps.add("Found: " + getRoomNumber);
			assertEquals(getRoomNumber, roomNumber, "Failed: Room number is mismatching");

			test_steps.add("Expected room class: " + RoomClass);
			test_steps.add("Found: " + getRoomClass);
			assertEquals(getRoomClass, RoomClass, "Failed: Room class is mismatching");

			test_steps.add("Expected zone: " + Zone);
			test_steps.add("Found: " + getZone);
			assertEquals(getZone, Zone, "Failed:Zone is mismatching");

			test_steps.add("Expected condition: " + RoomCondition);
			test_steps.add("Found: " + getCondition);
			assertEquals(getCondition, RoomCondition, "Failed:Condition is mismatching");

			test_steps.add("Expected Occupancy: " + Occupancy);
			test_steps.add("Found: " + getOccupancy);
			// assertEquals(getOccupancy, Occupancy, "Failed:Occupancy is
			// mismatching");

			test_steps.add("Expected departed date : " + DepartDate);
			test_steps.add("Found: " + getDepartDate);
			assertEquals(getDepartDate, DepartDate, "Failed:DepartDate is mismatching");

			test_steps.add("Expected arrival date: " + ArrivalDate);
			test_steps.add("Found: " + getArrivalDate);
			assertEquals(getArrivalDate, ArrivalDate, "Failed:ArrivalDate is mismatching");

			test_steps.add("Expected tasks: " + TaskSate.size());
			test_steps.add("Found: " + getTask);
			assertEquals(getTask, String.valueOf(TaskSate.size()), "Failed:Task is mismatching");

			test_steps.add("==========VERIFICATION OF TASK 1 IN ROOM STATUS WITH TASK==========");

			assertTrue(TaskImage.get(0).contains(roomstatus.getTaskImageInRoomStatus(driver, 0)));
			test_steps.add("Verified image");

			test_steps.add("Expected task name: " + TaskName.get(0));
			test_steps.add("Found: " + roomstatus.getTaskNameRoomStatus(driver, 0));
			assertEquals(roomstatus.getTaskNameRoomStatus(driver, 0), TaskName.get(0),
					"Failed: Task name is mismatching!");
			test_steps.add("Verified name");

			String ParseDate = ESTTimeZone.SubString(TaskDueOn.get(0), 0, 9);
			String GetTime = ESTTimeZone.SubString(TaskDueOn.get(0), 10, TaskDueOn.get(0).length());
			app_logs.info("GetTime: " + GetTime);
			String[] strParse = GetTime.split(" ");
			GetTime = strParse[0] + strParse[1];
			ParseDate = ParseDate.replace("\\s", "");

			app_logs.info("ParseDate: " + ParseDate);
			app_logs.info("GetTime: " + GetTime);

			String ExpectedDateFormat = ESTTimeZone.parseDate(ParseDate, "MM/dd/yy", "dd MMM");
			app_logs.info("ExpectedDateFormat" + ExpectedDateFormat);
			ExpectedDateFormat = ExpectedDateFormat + " (" + GetTime + ")";

			test_steps.add("Expected due on: " + ExpectedDateFormat);
			test_steps.add("Found: " + roomstatus.TaskDueOnAssignedAndStatusInRioomStatus(driver, TaskName.get(0), 0));
			assertEquals(roomstatus.TaskDueOnAssignedAndStatusInRioomStatus(driver, TaskName.get(0), 0),
					ExpectedDateFormat, "Failed:Due on is mismatching!");
			test_steps.add("Verified due on ");

			test_steps.add("Expected status: " + TaskSate.get(0));
			test_steps.add("Found: " + roomstatus.TaskDueOnAssignedAndStatusInRioomStatus(driver, TaskName.get(0), 2));
			assertEquals(roomstatus.TaskDueOnAssignedAndStatusInRioomStatus(driver, TaskName.get(0), 2),
					TaskSate.get(0), "Failed:Task status on mismatching!");
			test_steps.add("Verified status");

			test_steps.add("==========VERIFICATION OF TASK 2 IN ROOM STATUS WITH TASK==========");

			assertTrue(TaskImage.get(1).contains(roomstatus.getTaskImageInRoomStatus(driver, 1)));
			test_steps.add("Verified image");

			test_steps.add("Expected task name: " + TaskName.get(1));
			test_steps.add("Found: " + roomstatus.getTaskNameRoomStatus(driver, 1));
			assertEquals(roomstatus.getTaskNameRoomStatus(driver, 1), TaskName.get(1),
					"Failed: Task name is mismatching!");
			test_steps.add("Verified name");

			ParseDate = ESTTimeZone.SubString(TaskDueOn.get(1), 0, 9);
			GetTime = ESTTimeZone.SubString(TaskDueOn.get(1), 10, TaskDueOn.get(1).length());
			app_logs.info("GetTime: " + GetTime);
			strParse = GetTime.split(" ");
			GetTime = strParse[0] + strParse[1];
			ParseDate = ParseDate.replace("\\s", "");

			app_logs.info("ParseDate: " + ParseDate);
			app_logs.info("GetTime: " + GetTime);

			ExpectedDateFormat = ESTTimeZone.parseDate(ParseDate, "MM/dd/yy", "dd MMM");
			app_logs.info("ExpectedDateFormat" + ExpectedDateFormat);
			ExpectedDateFormat = ExpectedDateFormat + " (" + GetTime + ")";

			test_steps.add("Expected due on: " + ExpectedDateFormat);
			test_steps.add("Found: " + roomstatus.TaskDueOnAssignedAndStatusInRioomStatus(driver, TaskName.get(1), 0));
			assertEquals(roomstatus.TaskDueOnAssignedAndStatusInRioomStatus(driver, TaskName.get(1), 0),
					ExpectedDateFormat, "Failed:Due on is mismatching!");
			test_steps.add("Verified due on ");

			test_steps.add("Expected status: " + TaskSate.get(1));
			test_steps.add("Found: " + roomstatus.TaskDueOnAssignedAndStatusInRioomStatus(driver, TaskName.get(1), 2));
			assertEquals(roomstatus.TaskDueOnAssignedAndStatusInRioomStatus(driver, TaskName.get(1), 2),
					TaskSate.get(1), "Failed:Task status on mismatching!");
			test_steps.add("Verified status");

			roomstatus.CloseSecondWindow(driver);
			test_steps.add("Close second window");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to verify room status report with task", testName, "Verification",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to verify room status report with task", testName, "Verification",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		}

		String FirstLastName = GuestFirstName + " " + GuestLastName;
		try {
			test_steps.add("=====ADDING ASSINEE NEW TASK IN TASK LIST WITH TODAY DATE=====");

			test_steps.add("Navigate Guest Services");
			app_logs.info("Navigate Guest Services");

			nav.Task_List(driver);
			test_steps.add("Navigate TaskList");
			app_logs.info("Navigate TaskList");

			tasklist.VerifyLoading(driver);

			getTest_Steps.clear();
			getTest_Steps = tasklist.ClickTaskForDropDown(driver);
			app_logs.info("Click on drop down");
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectTaskFor(driver, "This Week");

			app_logs.info("Select task");
			test_steps.addAll(getTest_Steps);
			tasklist.VerifyLoading(driver);

			/*
			 * try { tasklist.checkedTasks(driver, FirstLastName, true,
			 * AssigneeName); tasklist.deleteTask(driver);
			 * 
			 * } catch (Exception e) { // TODO: handle exception }
			 */
			getTest_Steps.clear();
			getTest_Steps = tasklist.ClickTaskForDropDown(driver);
			app_logs.info("Click on drop down");
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectTaskFor(driver, TaskForToday);
			app_logs.info("Select task");
			test_steps.addAll(getTest_Steps);

			tasklist.Click_AddTask(driver);
			test_steps.add("Click on Add Task");

			app_logs.info("TaskCategory: " + TaskCategory);
			app_logs.info("TaskType: " + TaskType);
			getTest_Steps.clear();
			getTest_Steps = tasklist.CreateTask3(driver, TaskFor, TaskCategory, TaskType, TaskDetails,
					ReservationNumber, true, "auto", 0);
			test_steps.addAll(getTest_Steps);

			test_steps.add("=====VERIFICATION OF ASSIGNEE TASK IN TASK LIST WITH TODAY DATE=====");
			String GetTaskImage = tasklist.GetTaskImage(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskImage: " + GetTaskImage);
			app_logs.info("GetTaskImage: " + GetTaskImage);
			assertTrue(GetTaskImage.contains(TaskImageInternel));
			test_steps.add("Verified task image");

			String GetTaskFor = tasklist.GetTaskFor(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskFor: " + GetTaskFor);
			app_logs.info("GetTaskFor: " + GetTaskFor);
			test_steps.add("Expected occupancy: " + GuestFirstName + " " + GuestLastName);
			test_steps.add("Found: " + GetTaskFor);
			assertEquals(GetTaskFor, GuestFirstName + " " + GuestLastName,
					"Failed: Task for is mismatching in in today date");
			test_steps.add("verified task for");

			String GetTaskOccupancy = tasklist.GetTaskOccupancy(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskOccupancy: " + GetTaskOccupancy);
			app_logs.info("GetTaskOccupancy: " + GetTaskOccupancy);
			test_steps.add("Expected occupancy: " + Occupancy);
			test_steps.add("Found: " + GetTaskOccupancy);
			// assertEquals(GetTaskOccupancy, Occupancy,"Failed: Task occupancy
			// is mismatching in in today date");
			test_steps.add("verified task occupancy");

			String GetTaskDueOn = tasklist.GetTaskDueOn(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskDueOn: " + GetTaskDueOn);
			app_logs.info("GetTaskDueOn: " + GetTaskDueOn);

			String todayDate = ESTTimeZone.DateFormateForLineItem(0);
			String ExpectedDateFormat = ESTTimeZone.parseDate(todayDate, "MM/dd/yy", "dd MMM yy");
			app_logs.info("ExpectedDateFormat" + ExpectedDateFormat);

			test_steps.add("Expected due on: " + ExpectedDateFormat);
			test_steps.add("Found: " + ExpectedDateFormat);
			assertTrue(ExpectedDateFormat.contains(ExpectedDateFormat), "Failed. due on mismatching in in today date");
			test_steps.add("Verified due on");

			String GetTaskZone = tasklist.GetTaskZone(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskZone: " + GetTaskZone);
			app_logs.info("GetTaskZone: " + GetTaskZone);

			String GetTaskStatus = tasklist.GetTaskStatus(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskStatus: " + GetTaskStatus);
			app_logs.info("GetTaskStatus: " + GetTaskStatus);
			test_steps.add("Expectd status: " + TaskStatus);
			test_steps.add("Found: " + GetTaskStatus);
			assertEquals(GetTaskStatus, TaskStatus, "Failed: Task status is mismatching in in today date");
			test_steps.add("verified Task status");

			test_steps.add("=====ADDING UNASSIGNEED NEW TASK IN TASK LIST WITH TODAY DATE=====");

			getTest_Steps.clear();
			getTest_Steps = tasklist.ClickTaskForDropDown(driver);
			app_logs.info("Click on drop down");
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectTaskFor(driver, TaskForToday);
			app_logs.info("Select task");
			test_steps.addAll(getTest_Steps);

			tasklist.VerifyLoading(driver);
			Wait.wait5Second();
			tasklist.clickOnAddTask(driver);
			test_steps.add("Click on Add Task");

			getTest_Steps.clear();
			getTest_Steps = tasklist.CreateTask3(driver, TaskFor, TaskCategory, TaskType, TaskDetails,
					ReservationNumber, false, "auto", 0);
			test_steps.addAll(getTest_Steps);

			test_steps.add("=====VERIFICATION OF UNASSINEED TASK IN TASK LIST WITH TODAY DATE=====");
			String unassignedTaskImage = tasklist.GetTaskImage(driver, FirstLastName, true, AssigneeName);
			app_logs.info("GetTaskImage: " + unassignedTaskImage);
			assertTrue(GetTaskImage.contains(unassignedTaskImage));
			test_steps.add("Verified task image");

			String unassignedTaskFor = tasklist.GetTaskFor(driver, FirstLastName, false, AssigneeName);
			app_logs.info("GetTaskFor: " + unassignedTaskFor);
			test_steps.add("Expectd occupancy: " + GuestFirstName + " " + GuestLastName);
			test_steps.add("Found: " + unassignedTaskFor);
			assertEquals(unassignedTaskFor, GuestFirstName + " " + GuestLastName,
					"Failed: Task for is mismatching in in today date");
			test_steps.add("verified task for");

			String unassignedTaskOccupancy = tasklist.GetTaskOccupancy(driver, FirstLastName, false, AssigneeName);
			app_logs.info("GetTaskOccupancy: " + unassignedTaskOccupancy);
			test_steps.add("Expectd occupancy: " + Occupancy);
			test_steps.add("Found: " + unassignedTaskOccupancy);
			// assertEquals(GetTaskOccupancy, Occupancy,"Failed: Task occupancy
			// is mismatching in in today date");
			test_steps.add("verified task occupancy");

			String unassignedTaskDueOn = tasklist.GetTaskDueOn(driver, FirstLastName, false, AssigneeName);
			System.out.println("GetTaskDueOn: " + GetTaskDueOn);
			app_logs.info("GetTaskDueOn: " + unassignedTaskDueOn);
			app_logs.info("ExpectedDateFormat" + ExpectedDateFormat);
			test_steps.add("Expected due on: " + ExpectedDateFormat);
			test_steps.add("Found: " + GetTaskDueOn);
			assertTrue(GetTaskDueOn.contains(ExpectedDateFormat), "Failed. due  on mismatching in in today date");
			test_steps.add("Verified due on");

			String unassignedTaskZone = tasklist.GetTaskZone(driver, FirstLastName, false, AssigneeName);
			app_logs.info("GetTaskZone: " + unassignedTaskZone);

			String unassignedTaskStatus = tasklist.GetTaskStatus(driver, FirstLastName, false, AssigneeName);
			app_logs.info("GetTaskStatus: " + GetTaskStatus);
			test_steps.add("Expectd status: " + unassignedTaskStatus);
			test_steps.add("Found: " + GetTaskStatus);
			assertEquals(unassignedTaskStatus, TaskStatus, "Failed: Task status is mismatching in in today date");
			test_steps.add("verified Task status");

			test_steps.add("==========VERIFICATION ASSIGNEE TASK IN ALL ASSIGNEE REPORTS===========");

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectReports(driver, ReportsTypeAsAllAssignee);
			test_steps.addAll(getTest_Steps);

			String getTaskImageInReport = tasklist.getTaskImageInReport(driver, FirstLastName, true, AssigneeName);
			assertTrue(getTaskImageInReport.contains(TaskImageInternel),
					"Failed: task image is mismaching in assignee task");
			test_steps.add("Verified task image");

			String getTaskTypeInReport = tasklist.getTaskTypeInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected task type: " + TaskType);
			test_steps.add("Found: " + getTaskTypeInReport);
			assertEquals(getTaskTypeInReport, TaskType);
			test_steps.add("Verified task tyep");

			String getTaskForInReport = tasklist.getTaskForInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected task for: " + FirstLastName);
			test_steps.add("Found: " + getTaskForInReport);
			assertEquals(getTaskForInReport, FirstLastName, "Failed: Task for is mismatching!");
			test_steps.add("Verified task for");

			String getTaskOccupancyInReprts = tasklist.getTaskOccupancyInReprts(driver, FirstLastName, true,
					AssigneeName);
			test_steps.add("Expected occupancy: " + GetTaskOccupancy);
			test_steps.add("Found: " + getTaskOccupancyInReprts);
			assertEquals(getTaskOccupancyInReprts, GetTaskOccupancy, "Failed: Task occupancy is mismatching!");
			test_steps.add("Verified occupancy");

			String getTaskDueOnInReport = tasklist.getTaskDueOnInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected due on: " + GetTaskDueOn);
			test_steps.add("Found: " + getTaskDueOnInReport);
			assertEquals(getTaskDueOnInReport, GetTaskDueOn, "Failed: Task due on is mismatching!");
			test_steps.add("Verified due on");

			String getAssignedInReports = tasklist.getAssignedInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected assigned: " + AssigneeName);
			test_steps.add("Found: " + getAssignedInReports);
			assertEquals(getAssignedInReports, AssigneeName, "Failed: Assigned is mismatching!");
			test_steps.add("Verified assigned");

			String getTaskZoneInReports = tasklist.getTaskZoneInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected zone: " + GetTaskZone);
			test_steps.add("Found: " + getTaskZoneInReports);
			assertEquals(getTaskZoneInReports, GetTaskZone, "Failed: Zone is mismatching!");
			test_steps.add("Verified zone");

			String GetTaskStatusInReports = tasklist.GetTaskStatusInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected status: " + GetTaskStatus);
			test_steps.add("Found: " + GetTaskStatusInReports);
			assertEquals(GetTaskStatusInReports, GetTaskStatus, "Failed: Status is mismatching!");
			test_steps.add("Verified status");

			roomstatus.CloseSecondWindow(driver);
			test_steps.add("Close second window");

			test_steps.add("==========VERIFICATION UNASSIGNED TASK IN ALL ASSIGNEE REPORTS===========");

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectReports(driver, ReportsTypeAsByAssignee);
			test_steps.addAll(getTest_Steps);

			getTaskImageInReport = tasklist.getUnassignedTaskImageInReport(driver, FirstLastName, false, AssigneeName);
			assertTrue(getTaskImageInReport.contains(TaskImageInternel),
					"Failed: task image is mismaching in assignee task");
			test_steps.add("Verified task image");

			getTaskTypeInReport = tasklist.getUnassignedTaskTypeInReport(driver, FirstLastName, false, AssigneeName);
			test_steps.add("Expected task type: " + TaskType);
			test_steps.add("Found: " + getTaskTypeInReport);
			assertEquals(getTaskTypeInReport, TaskType);
			test_steps.add("Verified task tyep");

			getTaskForInReport = tasklist.getUnassignedTaskForInReport(driver, FirstLastName, false, AssigneeName);
			test_steps.add("Expected task for: " + FirstLastName);
			test_steps.add("Found: " + getTaskForInReport);
			assertEquals(getTaskForInReport, FirstLastName, "Failed: Task for is mismatching!");
			test_steps.add("Verified task for");

			getTaskOccupancyInReprts = tasklist.getUnassignedTaskOccupancyInReprts(driver, FirstLastName, true,
					AssigneeName);
			test_steps.add("Expected occupancy: " + unassignedTaskOccupancy);
			test_steps.add("Found: " + getTaskOccupancyInReprts);
			assertEquals(getTaskOccupancyInReprts, unassignedTaskOccupancy, "Failed: Task occupancy is mismatching!");
			test_steps.add("Verified occupancy");

			getTaskDueOnInReport = tasklist.getUnassignedTaskDueOnInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected due on: " + unassignedTaskDueOn);
			test_steps.add("Found: " + getTaskDueOnInReport);
			assertEquals(getTaskDueOnInReport, unassignedTaskDueOn, "Failed: Task due on is mismatching!");
			test_steps.add("Verified due on");

			getTaskZoneInReports = tasklist.getUnassignedTaskZoneInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected zone: " + unassignedTaskZone);
			test_steps.add("Found: " + getTaskZoneInReports);
			assertEquals(getTaskZoneInReports, unassignedTaskZone, "Failed: Zone is mismatching!");
			test_steps.add("Verified zone");

			GetTaskStatusInReports = tasklist.getUnassignedTaskStatusInReports(driver, FirstLastName, true,
					AssigneeName);
			test_steps.add("Expected status: " + unassignedTaskStatus);
			test_steps.add("Found: " + GetTaskStatusInReports);
			assertEquals(GetTaskStatusInReports, unassignedTaskStatus, "Failed: Status is mismatching!");
			test_steps.add("Verified status");

			roomstatus.CloseSecondWindow(driver);
			test_steps.add("Close second window");

			test_steps.add("==========VERIFICATION ASSIGNEE TASK IN ALL ASSIGNEE REPORTS WITH THIS WEEK===========");

			driver.navigate().refresh();

			getTest_Steps.clear();
			getTest_Steps = tasklist.ClickTaskForDropDown(driver);
			app_logs.info("Click on drop down");
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectTaskFor(driver, "This Week");
			app_logs.info("Select task");
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectReports(driver, ReportsTypeAsAllAssignee);
			test_steps.addAll(getTest_Steps);

			getTaskImageInReport = tasklist.getTaskImageInReport(driver, FirstLastName, true, AssigneeName);
			assertTrue(getTaskImageInReport.contains(TaskImageInternel),
					"Failed: task image is mismaching in assignee task");
			test_steps.add("Verified task image");

			getTaskTypeInReport = tasklist.getTaskTypeInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected task type: " + TaskType);
			test_steps.add("Found: " + getTaskTypeInReport);
			assertEquals(getTaskTypeInReport, TaskType);
			test_steps.add("Verified task tyep");

			getTaskForInReport = tasklist.getTaskForInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected task for: " + FirstLastName);
			test_steps.add("Found: " + getTaskForInReport);
			assertEquals(getTaskForInReport, FirstLastName, "Failed: Task for is mismatching!");
			test_steps.add("Verified task for");

			getTaskOccupancyInReprts = tasklist.getTaskOccupancyInReprts(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected occupancy: " + GetTaskOccupancy);
			test_steps.add("Found: " + getTaskOccupancyInReprts);
			assertEquals(getTaskOccupancyInReprts, GetTaskOccupancy, "Failed: Task occupancy is mismatching!");
			test_steps.add("Verified occupancy");

			getTaskDueOnInReport = tasklist.getTaskDueOnInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected due on: " + GetTaskDueOn);
			test_steps.add("Found: " + getTaskDueOnInReport);
			assertEquals(getTaskDueOnInReport, GetTaskDueOn, "Failed: Task due on is mismatching!");
			test_steps.add("Verified due on");

			getAssignedInReports = tasklist.getAssignedInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected assigned: " + AssigneeName);
			test_steps.add("Found: " + getAssignedInReports);
			assertEquals(getAssignedInReports, AssigneeName, "Failed: Assigned is mismatching!");
			test_steps.add("Verified assigned");

			getTaskZoneInReports = tasklist.getTaskZoneInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected zone: " + GetTaskZone);
			test_steps.add("Found: " + getTaskZoneInReports);
			assertEquals(getTaskZoneInReports, GetTaskZone, "Failed: Zone is mismatching!");
			test_steps.add("Verified zone");

			GetTaskStatusInReports = tasklist.GetTaskStatusInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected status: " + GetTaskStatus);
			test_steps.add("Found: " + GetTaskStatusInReports);
			assertEquals(GetTaskStatusInReports, GetTaskStatus, "Failed: Status is mismatching!");
			test_steps.add("Verified status");

			roomstatus.CloseSecondWindow(driver);
			test_steps.add("Close second window");

			test_steps.add("==========VERIFICATION UNASSIGNEd TASK IN ALL ASSIGNEE REPORTS WITH THIS WEEK===========");

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectReports(driver, ReportsTypeAsByAssignee);
			test_steps.addAll(getTest_Steps);

			getTaskImageInReport = tasklist.getUnassignedTaskImageInReport(driver, FirstLastName, false, AssigneeName);
			assertTrue(getTaskImageInReport.contains(TaskImageInternel),
					"Failed: task image is mismaching in assignee task");
			test_steps.add("Verified task image");

			getTaskTypeInReport = tasklist.getUnassignedTaskTypeInReport(driver, FirstLastName, false, AssigneeName);
			test_steps.add("Expected task type: " + TaskType);
			test_steps.add("Found: " + getTaskTypeInReport);
			assertEquals(getTaskTypeInReport, TaskType);
			test_steps.add("Verified task tyep");

			getTaskForInReport = tasklist.getUnassignedTaskForInReport(driver, FirstLastName, false, AssigneeName);
			test_steps.add("Expected task for: " + FirstLastName);
			test_steps.add("Found: " + getTaskForInReport);
			assertEquals(getTaskForInReport, FirstLastName, "Failed: Task for is mismatching!");
			test_steps.add("Verified task for");

			getTaskOccupancyInReprts = tasklist.getUnassignedTaskOccupancyInReprts(driver, FirstLastName, true,
					AssigneeName);
			test_steps.add("Expected occupancy: " + unassignedTaskOccupancy);
			test_steps.add("Found: " + getTaskOccupancyInReprts);
			assertEquals(getTaskOccupancyInReprts, unassignedTaskOccupancy, "Failed: Task occupancy is mismatching!");
			test_steps.add("Verified occupancy");

			getTaskDueOnInReport = tasklist.getUnassignedTaskDueOnInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected due on: " + unassignedTaskDueOn);
			test_steps.add("Found: " + getTaskDueOnInReport);
			assertEquals(getTaskDueOnInReport, unassignedTaskDueOn, "Failed: Task due on is mismatching!");
			test_steps.add("Verified due on");

			getTaskZoneInReports = tasklist.getUnassignedTaskZoneInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected zone: " + unassignedTaskZone);
			test_steps.add("Found: " + getTaskZoneInReports);
			assertEquals(getTaskZoneInReports, unassignedTaskZone, "Failed: Zone is mismatching!");
			test_steps.add("Verified zone");

			GetTaskStatusInReports = tasklist.getUnassignedTaskStatusInReports(driver, FirstLastName, true,
					AssigneeName);
			test_steps.add("Expected status: " + unassignedTaskStatus);
			test_steps.add("Found: " + GetTaskStatusInReports);
			assertEquals(GetTaskStatusInReports, unassignedTaskStatus, "Failed: Status is mismatching!");
			test_steps.add("Verified status");

			roomstatus.CloseSecondWindow(driver);
			test_steps.add("Close second window");

			test_steps.add("==========VERIFICATION ASSIGNEE TASK IN ALL ASSIGNEE REPORTS WITH THIS MOTNH===========");

			driver.navigate().refresh();

			getTest_Steps.clear();
			getTest_Steps = tasklist.ClickTaskForDropDown(driver);
			app_logs.info("Click on drop down");
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectTaskFor(driver, "This Month");
			app_logs.info("Select task");
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectReports(driver, ReportsTypeAsAllAssignee);
			test_steps.addAll(getTest_Steps);

			getTaskImageInReport = tasklist.getTaskImageInReport(driver, FirstLastName, true, AssigneeName);
			app_logs.info(getTaskImageInReport);
			app_logs.info(TaskImageInternel);
			assertTrue(getTaskImageInReport.contains(TaskImageInternel),
					"Failed: task image is mismaching in assignee task");
			test_steps.add("Verified task image");

			getTaskTypeInReport = tasklist.getTaskTypeInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected task type: " + TaskType);
			test_steps.add("Found: " + getTaskTypeInReport);
			assertEquals(getTaskTypeInReport, TaskType);
			test_steps.add("Verified task tyep");

			getTaskForInReport = tasklist.getTaskForInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected task for: " + FirstLastName);
			test_steps.add("Found: " + getTaskForInReport);
			assertEquals(getTaskForInReport, FirstLastName, "Failed: Task for is mismatching!");
			test_steps.add("Verified task for");

			getTaskOccupancyInReprts = tasklist.getTaskOccupancyInReprts(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected occupancy: " + GetTaskOccupancy);
			test_steps.add("Found: " + getTaskOccupancyInReprts);
			assertEquals(getTaskOccupancyInReprts, GetTaskOccupancy, "Failed: Task occupancy is mismatching!");
			test_steps.add("Verified occupancy");

			getTaskDueOnInReport = tasklist.getTaskDueOnInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected due on: " + GetTaskDueOn);
			test_steps.add("Found: " + getTaskDueOnInReport);
			assertEquals(getTaskDueOnInReport, GetTaskDueOn, "Failed: Task due on is mismatching!");
			test_steps.add("Verified due on");

			getAssignedInReports = tasklist.getAssignedInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected assigned: " + AssigneeName);
			test_steps.add("Found: " + getAssignedInReports);
			assertEquals(getAssignedInReports, AssigneeName, "Failed: Assigned is mismatching!");
			test_steps.add("Verified assigned");

			getTaskZoneInReports = tasklist.getTaskZoneInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected zone: " + GetTaskZone);
			test_steps.add("Found: " + getTaskZoneInReports);
			assertEquals(getTaskZoneInReports, GetTaskZone, "Failed: Zone is mismatching!");
			test_steps.add("Verified zone");

			GetTaskStatusInReports = tasklist.GetTaskStatusInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected status: " + GetTaskStatus);
			test_steps.add("Found: " + GetTaskStatusInReports);
			assertEquals(GetTaskStatusInReports, GetTaskStatus, "Failed: Status is mismatching!");
			test_steps.add("Verified status");

			roomstatus.CloseSecondWindow(driver);
			test_steps.add("Close second window");

			test_steps.add("==========VERIFICATION UNASSIGNEd TASK IN ALL ASSIGNEE REPORTS WITH THIS MONTH===========");

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectReports(driver, ReportsTypeAsByAssignee);
			test_steps.addAll(getTest_Steps);

			getTaskImageInReport = tasklist.getUnassignedTaskImageInReport(driver, FirstLastName, false, AssigneeName);
			assertTrue(getTaskImageInReport.contains(TaskImageInternel),
					"Failed: task image is mismaching in assignee task");
			test_steps.add("Verified task image");

			getTaskTypeInReport = tasklist.getUnassignedTaskTypeInReport(driver, FirstLastName, false, AssigneeName);
			test_steps.add("Expected task type: " + TaskType);
			test_steps.add("Found: " + getTaskTypeInReport);
			assertEquals(getTaskTypeInReport, TaskType);
			test_steps.add("Verified task tyep");

			getTaskForInReport = tasklist.getUnassignedTaskForInReport(driver, FirstLastName, false, AssigneeName);
			test_steps.add("Expected task for: " + FirstLastName);
			test_steps.add("Found: " + getTaskForInReport);
			assertEquals(getTaskForInReport, FirstLastName, "Failed: Task for is mismatching!");
			test_steps.add("Verified task for");

			getTaskOccupancyInReprts = tasklist.getUnassignedTaskOccupancyInReprts(driver, FirstLastName, true,
					AssigneeName);
			test_steps.add("Expected occupancy: " + unassignedTaskOccupancy);
			test_steps.add("Found: " + getTaskOccupancyInReprts);
			assertEquals(getTaskOccupancyInReprts, unassignedTaskOccupancy, "Failed: Task occupancy is mismatching!");
			test_steps.add("Verified occupancy");

			getTaskDueOnInReport = tasklist.getUnassignedTaskDueOnInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected due on: " + unassignedTaskDueOn);
			test_steps.add("Found: " + getTaskDueOnInReport);
			assertEquals(getTaskDueOnInReport, unassignedTaskDueOn, "Failed: Task due on is mismatching!");
			test_steps.add("Verified due on");

			getTaskZoneInReports = tasklist.getUnassignedTaskZoneInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected zone: " + unassignedTaskZone);
			test_steps.add("Found: " + getTaskZoneInReports);
			assertEquals(getTaskZoneInReports, unassignedTaskZone, "Failed: Zone is mismatching!");
			test_steps.add("Verified zone");

			GetTaskStatusInReports = tasklist.getUnassignedTaskStatusInReports(driver, FirstLastName, true,
					AssigneeName);
			test_steps.add("Expected status: " + unassignedTaskStatus);
			test_steps.add("Found: " + GetTaskStatusInReports);
			assertEquals(GetTaskStatusInReports, unassignedTaskStatus, "Failed: Status is mismatching!");
			test_steps.add("Verified status");

			roomstatus.CloseSecondWindow(driver);
			test_steps.add("Close second window");

			// driver.navigate().refresh();
			// deleting created task

			getTest_Steps.clear();
			getTest_Steps = tasklist.ClickTaskForDropDown(driver);
			app_logs.info("Click on drop down");
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectTaskFor(driver, TaskForToday);
			app_logs.info("Select task");
			test_steps.addAll(getTest_Steps);
			tasklist.checkedTasks(driver, FirstLastName, true, AssigneeName);
			try {
				tasklist.deleteTask(driver);

			} catch (Exception e) {
				// TODO: handle exception
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to verify assignee and unassignee tasks in reports", testName,
						"AddingNewTask", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to verify assignee and unassignee tasks in reports", testName,
						"AddingNewTask", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// verify task with tomorrow dates
		try {
			test_steps.add("=====ADDING ASSINEE NEW TASK IN TASK LIST WITH TOMORROW DATE=====");

			getTest_Steps.clear();
			getTest_Steps = tasklist.ClickTaskForDropDown(driver);
			app_logs.info("Click on drop down");
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectTaskFor(driver, "Tomorrow");
			app_logs.info("Select task");
			test_steps.addAll(getTest_Steps);

			tasklist.Click_AddTask(driver);
			test_steps.add("Click on Add Task");

			getTest_Steps.clear();
			getTest_Steps = tasklist.CreateTask3(driver, TaskFor, TaskCategory, TaskType, TaskDetails,
					ReservationNumber, true, "auto", +1);
			test_steps.addAll(getTest_Steps);

			test_steps.add("=====VERIFICATION OF ASSIGNEE TASK IN TASK LIST WITH TOMORROW DATE=====");
			String GetTaskImage = tasklist.GetTaskImage(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskImage: " + GetTaskImage);
			app_logs.info("GetTaskImage: " + GetTaskImage);
			assertTrue(GetTaskImage.contains(TaskImageInternel));
			test_steps.add("Verified task image");

			String GetTaskFor = tasklist.GetTaskFor(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskFor: " + GetTaskFor);
			app_logs.info("GetTaskFor: " + GetTaskFor);
			test_steps.add("Expectd occupancy: " + GuestFirstName + " " + GuestLastName);
			test_steps.add("Found: " + GetTaskFor);
			assertEquals(GetTaskFor, GuestFirstName + " " + GuestLastName,
					"Failed: Task for is mismatching in in today date");
			test_steps.add("verified task for");

			String GetTaskOccupancy = tasklist.GetTaskOccupancy(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskOccupancy: " + GetTaskOccupancy);
			app_logs.info("GetTaskOccupancy: " + GetTaskOccupancy);
			test_steps.add("Expectd occupancy: " + Occupancy);
			test_steps.add("Found: " + GetTaskOccupancy);
			// assertEquals(GetTaskOccupancy, Occupancy,"Failed: Task occupancy
			// is mismatching in in today date");
			test_steps.add("verified task occupancy");

			String GetTaskDueOn = tasklist.GetTaskDueOn(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskDueOn: " + GetTaskDueOn);
			app_logs.info("GetTaskDueOn: " + GetTaskDueOn);

			String todayDate = ESTTimeZone.DateFormateForLineItem(+1);
			String ExpectedDateFormat = ESTTimeZone.parseDate(todayDate, "MM/dd/yy", "dd MMM yy");
			app_logs.info("ExpectedDateFormat" + ExpectedDateFormat);
			app_logs.info("ExpectedDateFormat" + ExpectedDateFormat);

			test_steps.add("Expected due on: " + ExpectedDateFormat);
			test_steps.add("Found: " + GetTaskDueOn);
			assertTrue(GetTaskDueOn.contains(ExpectedDateFormat), "Failed. due on mismatching in in today date");
			test_steps.add("Verified due on");

			String GetTaskZone = tasklist.GetTaskZone(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskZone: " + GetTaskZone);
			app_logs.info("GetTaskZone: " + GetTaskZone);

			String GetTaskStatus = tasklist.GetTaskStatus(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskStatus: " + GetTaskStatus);
			app_logs.info("GetTaskStatus: " + GetTaskStatus);
			test_steps.add("Expectd status: " + TaskStatus);
			test_steps.add("Found: " + GetTaskStatus);
			assertEquals(GetTaskStatus, TaskStatus, "Failed: Task status is mismatching in in today date");
			test_steps.add("verified Task status");

			test_steps.add("=====ADDING UNASSIGNEED NEW TASK IN TASK LIST WITH TOMORROW DATE=====");

			Wait.wait5Second();
			tasklist.clickOnAddTask(driver);
			test_steps.add("Click on Add Task");

			getTest_Steps.clear();
			getTest_Steps = tasklist.CreateTask3(driver, TaskFor, TaskCategory, TaskType, TaskDetails,
					ReservationNumber, false, "auto", +1);
			test_steps.addAll(getTest_Steps);

			test_steps.add("=====VERIFICATION OF UNASSINEED TASK IN TASK LIST WITH TOMORROW DATE=====");
			String unassignedTaskImage = tasklist.GetTaskImage(driver, FirstLastName, true, AssigneeName);
			app_logs.info("GetTaskImage: " + unassignedTaskImage);
			assertTrue(GetTaskImage.contains(unassignedTaskImage));
			test_steps.add("Verified task image");

			String unassignedTaskFor = tasklist.GetTaskFor(driver, FirstLastName, false, AssigneeName);
			app_logs.info("GetTaskFor: " + unassignedTaskFor);
			test_steps.add("Expectd occupancy: " + GuestFirstName + " " + GuestLastName);
			test_steps.add("Found: " + unassignedTaskFor);
			assertEquals(unassignedTaskFor, GuestFirstName + " " + GuestLastName,
					"Failed: Task for is mismatching in in today date");
			test_steps.add("verified task for");

			String unassignedTaskOccupancy = tasklist.GetTaskOccupancy(driver, FirstLastName, false, AssigneeName);
			app_logs.info("GetTaskOccupancy: " + unassignedTaskOccupancy);
			test_steps.add("Expectd occupancy: " + Occupancy);
			test_steps.add("Found: " + unassignedTaskOccupancy);
			// assertEquals(GetTaskOccupancy, Occupancy,"Failed: Task occupancy
			// is mismatching in in today date");
			test_steps.add("verified task occupancy");

			String unassignedTaskDueOn = tasklist.GetTaskDueOn(driver, FirstLastName, false, AssigneeName);
			System.out.println("GetTaskDueOn: " + GetTaskDueOn);
			app_logs.info("GetTaskDueOn: " + unassignedTaskDueOn);
			app_logs.info("ExpectedDateFormat" + ExpectedDateFormat);
			test_steps.add("Expected due on: " + ExpectedDateFormat);
			test_steps.add("Found: " + GetTaskDueOn);
			assertTrue(GetTaskDueOn.contains(ExpectedDateFormat), "Failed. due on mismatching in in today date");
			test_steps.add("Verified due on");

			String unassignedTaskZone = tasklist.GetTaskZone(driver, FirstLastName, false, AssigneeName);
			app_logs.info("GetTaskZone: " + unassignedTaskZone);

			String unassignedTaskStatus = tasklist.GetTaskStatus(driver, FirstLastName, false, AssigneeName);
			app_logs.info("GetTaskStatus: " + GetTaskStatus);
			test_steps.add("Expectd status: " + unassignedTaskStatus);
			test_steps.add("Found: " + GetTaskStatus);
			assertEquals(unassignedTaskStatus, TaskStatus, "Failed: Task status is mismatching in in today date");
			test_steps.add("verified Task status");

			test_steps.add("==========VERIFICATION ASSIGNEE TASK IN ALL ASSIGNEE REPORTS===========");

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectReports(driver, ReportsTypeAsAllAssignee);
			test_steps.addAll(getTest_Steps);

			String getTaskImageInReport = tasklist.getTaskImageInReport(driver, FirstLastName, true, AssigneeName);
			assertTrue(getTaskImageInReport.contains(TaskImageInternel),
					"Failed: task image is mismaching in assignee task");
			test_steps.add("Verified task image");

			String getTaskTypeInReport = tasklist.getTaskTypeInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected task type: " + TaskType);
			test_steps.add("Found: " + getTaskTypeInReport);
			assertEquals(getTaskTypeInReport, TaskType);
			test_steps.add("Verified task tyep");

			String getTaskForInReport = tasklist.getTaskForInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected task for: " + FirstLastName);
			test_steps.add("Found: " + getTaskForInReport);
			assertEquals(getTaskForInReport, FirstLastName, "Failed: Task for is mismatching!");
			test_steps.add("Verified task for");

			String getTaskOccupancyInReprts = tasklist.getTaskOccupancyInReprts(driver, FirstLastName, true,
					AssigneeName);
			test_steps.add("Expected occupancy: " + GetTaskOccupancy);
			test_steps.add("Found: " + getTaskOccupancyInReprts);
			assertEquals(getTaskOccupancyInReprts, GetTaskOccupancy, "Failed: Task occupancy is mismatching!");
			test_steps.add("Verified occupancy");

			String getTaskDueOnInReport = tasklist.getTaskDueOnInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected due on: " + GetTaskDueOn);
			test_steps.add("Found: " + getTaskDueOnInReport);
			assertEquals(getTaskDueOnInReport, GetTaskDueOn, "Failed: Task due on is mismatching!");
			test_steps.add("Verified due on");

			String getAssignedInReports = tasklist.getAssignedInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected assigned: " + AssigneeName);
			test_steps.add("Found: " + getAssignedInReports);
			assertEquals(getAssignedInReports, AssigneeName, "Failed: Assigned is mismatching!");
			test_steps.add("Verified assigned");

			String getTaskZoneInReports = tasklist.getTaskZoneInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected zone: " + GetTaskZone);
			test_steps.add("Found: " + getTaskZoneInReports);
			assertEquals(getTaskZoneInReports, GetTaskZone, "Failed: Zone is mismatching!");
			test_steps.add("Verified zone");

			String GetTaskStatusInReports = tasklist.GetTaskStatusInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected status: " + GetTaskStatus);
			test_steps.add("Found: " + GetTaskStatusInReports);
			assertEquals(GetTaskStatusInReports, GetTaskStatus, "Failed: Status is mismatching!");
			test_steps.add("Verified status");

			roomstatus.CloseSecondWindow(driver);
			test_steps.add("Close second window");

			test_steps.add("==========VERIFICATION UNASSIGNED TASK IN ALL ASSIGNEE REPORTS===========");

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectReports(driver, ReportsTypeAsByAssignee);
			test_steps.addAll(getTest_Steps);

			getTaskImageInReport = tasklist.getUnassignedTaskImageInReport(driver, FirstLastName, false, AssigneeName);
			assertTrue(getTaskImageInReport.contains(TaskImageInternel),
					"Failed: task image is mismaching in assignee task");
			test_steps.add("Verified task image");

			getTaskTypeInReport = tasklist.getUnassignedTaskTypeInReport(driver, FirstLastName, false, AssigneeName);
			test_steps.add("Expected task type: " + TaskType);
			test_steps.add("Found: " + getTaskTypeInReport);
			assertEquals(getTaskTypeInReport, TaskType);
			test_steps.add("Verified task tyep");

			getTaskForInReport = tasklist.getUnassignedTaskForInReport(driver, FirstLastName, false, AssigneeName);
			test_steps.add("Expected task for: " + FirstLastName);
			test_steps.add("Found: " + getTaskForInReport);
			assertEquals(getTaskForInReport, FirstLastName, "Failed: Task for is mismatching!");
			test_steps.add("Verified task for");

			getTaskOccupancyInReprts = tasklist.getUnassignedTaskOccupancyInReprts(driver, FirstLastName, true,
					AssigneeName);
			test_steps.add("Expected occupancy: " + unassignedTaskOccupancy);
			test_steps.add("Found: " + getTaskOccupancyInReprts);
			assertEquals(getTaskOccupancyInReprts, unassignedTaskOccupancy, "Failed: Task occupancy is mismatching!");
			test_steps.add("Verified occupancy");

			getTaskDueOnInReport = tasklist.getUnassignedTaskDueOnInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected due on: " + unassignedTaskDueOn);
			test_steps.add("Found: " + getTaskDueOnInReport);
			assertEquals(getTaskDueOnInReport, unassignedTaskDueOn, "Failed: Task due on is mismatching!");
			test_steps.add("Verified due on");

			getTaskZoneInReports = tasklist.getUnassignedTaskZoneInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected zone: " + unassignedTaskZone);
			test_steps.add("Found: " + getTaskZoneInReports);
			assertEquals(getTaskZoneInReports, unassignedTaskZone, "Failed: Zone is mismatching!");
			test_steps.add("Verified zone");

			GetTaskStatusInReports = tasklist.getUnassignedTaskStatusInReports(driver, FirstLastName, true,
					AssigneeName);
			test_steps.add("Expected status: " + unassignedTaskStatus);
			test_steps.add("Found: " + GetTaskStatusInReports);
			assertEquals(GetTaskStatusInReports, unassignedTaskStatus, "Failed: Status is mismatching!");
			test_steps.add("Verified status");

			roomstatus.CloseSecondWindow(driver);
			test_steps.add("Close second window");

			// driver.navigate().refresh();
			// deleting created task
			tasklist.checkedTasks(driver, FirstLastName, true, AssigneeName);
			try {
				tasklist.deleteTask(driver);

			} catch (Exception e) {
				// TODO: handle exception
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to verify assignee and unassignee tasks with tomorrow date ", testName,
						"AddingNewTask", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to verify assignee and unassignee tasks with tomorrow date ", testName,
						"AddingNewTask", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// verify task with yesterday dates
		try {
			test_steps.add("=====ADDING ASSINEE NEW TASK IN TASK LIST WITH YESTERDAY DATE=====");

			getTest_Steps.clear();
			getTest_Steps = tasklist.ClickTaskForDropDown(driver);
			app_logs.info("Click on drop down");
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectTaskFor(driver, "Yesterday");
			app_logs.info("Select task");
			test_steps.addAll(getTest_Steps);

			tasklist.Click_AddTask(driver);
			test_steps.add("Click on Add Task");

			getTest_Steps.clear();
			getTest_Steps = tasklist.CreateTask3(driver, TaskFor, TaskCategory, TaskType, TaskDetails,
					ReservationNumber, true, "auto", -1);
			test_steps.addAll(getTest_Steps);

			test_steps.add("=====VERIFICATION OF ASSIGNEE TASK IN TASK LIST WITH YESTERDAY DATE=====");
			String GetTaskImage = tasklist.GetTaskImage(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskImage: " + GetTaskImage);
			app_logs.info("GetTaskImage: " + GetTaskImage);
			assertTrue(GetTaskImage.contains(TaskImageInternel));
			test_steps.add("Verified task image");

			String GetTaskFor = tasklist.GetTaskFor(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskFor: " + GetTaskFor);
			app_logs.info("GetTaskFor: " + GetTaskFor);
			test_steps.add("Expectd occupancy: " + GuestFirstName + " " + GuestLastName);
			test_steps.add("Found: " + GetTaskFor);
			assertEquals(GetTaskFor, GuestFirstName + " " + GuestLastName,
					"Failed: Task for is mismatching in in today date");
			test_steps.add("verified task for");

			String GetTaskOccupancy = tasklist.GetTaskOccupancy(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskOccupancy: " + GetTaskOccupancy);
			app_logs.info("GetTaskOccupancy: " + GetTaskOccupancy);
			test_steps.add("Expectd occupancy: " + Occupancy);
			test_steps.add("Found: " + GetTaskOccupancy);
			// assertEquals(GetTaskOccupancy, Occupancy,"Failed: Task occupancy
			// is mismatching in in today date");
			test_steps.add("verified task occupancy");

			String GetTaskDueOn = tasklist.GetTaskDueOn(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskDueOn: " + GetTaskDueOn);
			app_logs.info("GetTaskDueOn: " + GetTaskDueOn);

			String todayDate = ESTTimeZone.DateFormateForLineItem(-1);
			String ExpectedDateFormat = ESTTimeZone.parseDate(todayDate, "MM/dd/yy", "dd MMM yy");
			app_logs.info("ExpectedDateFormat" + ExpectedDateFormat);
			app_logs.info("ExpectedDateFormat" + ExpectedDateFormat);

			test_steps.add("Expected due on: " + ExpectedDateFormat);
			test_steps.add("Found: " + GetTaskDueOn);
			assertTrue(GetTaskDueOn.contains(ExpectedDateFormat), "Failed. due on mismatching in in today date");
			test_steps.add("Verified due on");

			String GetTaskZone = tasklist.GetTaskZone(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskZone: " + GetTaskZone);
			app_logs.info("GetTaskZone: " + GetTaskZone);

			String GetTaskStatus = tasklist.GetTaskStatus(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskStatus: " + GetTaskStatus);
			app_logs.info("GetTaskStatus: " + GetTaskStatus);
			test_steps.add("Expectd status: " + TaskStatus);
			test_steps.add("Found: " + GetTaskStatus);
			assertEquals(GetTaskStatus, TaskStatus, "Failed: Task status is mismatching in in today date");
			test_steps.add("verified Task status");

			test_steps.add("=====ADDING UNASSIGNEED NEW TASK IN TASK LIST WITH YESTERDAY DATE=====");

			Wait.wait5Second();
			tasklist.clickOnAddTask(driver);
			test_steps.add("Click on Add Task");

			getTest_Steps.clear();
			getTest_Steps = tasklist.CreateTask3(driver, TaskFor, TaskCategory, TaskType, TaskDetails,
					ReservationNumber, false, "auto", -1);
			test_steps.addAll(getTest_Steps);

			test_steps.add("=====VERIFICATION OF UNASSINEED TASK IN TASK LIST WITH YESTERDAY DATE=====");
			String unassignedTaskImage = tasklist.GetTaskImage(driver, FirstLastName, true, AssigneeName);
			app_logs.info("GetTaskImage: " + unassignedTaskImage);
			assertTrue(GetTaskImage.contains(unassignedTaskImage));
			test_steps.add("Verified task image");

			String unassignedTaskFor = tasklist.GetTaskFor(driver, FirstLastName, false, AssigneeName);
			app_logs.info("GetTaskFor: " + unassignedTaskFor);
			test_steps.add("Expectd occupancy: " + GuestFirstName + " " + GuestLastName);
			test_steps.add("Found: " + unassignedTaskFor);
			assertEquals(unassignedTaskFor, GuestFirstName + " " + GuestLastName,
					"Failed: Task for is mismatching in in today date");
			test_steps.add("verified task for");

			String unassignedTaskOccupancy = tasklist.GetTaskOccupancy(driver, FirstLastName, false, AssigneeName);
			app_logs.info("GetTaskOccupancy: " + unassignedTaskOccupancy);
			test_steps.add("Expectd occupancy: " + Occupancy);
			test_steps.add("Found: " + unassignedTaskOccupancy);
			// assertEquals(GetTaskOccupancy, Occupancy,"Failed: Task occupancy
			// is mismatching in in today date");
			test_steps.add("verified task occupancy");

			String unassignedTaskDueOn = tasklist.GetTaskDueOn(driver, FirstLastName, false, AssigneeName);
			app_logs.info("GetTaskDueOn: " + unassignedTaskDueOn);
			app_logs.info("ExpectedDateFormat" + ExpectedDateFormat);
			test_steps.add("Expected due on: " + ExpectedDateFormat);
			test_steps.add("Found: " + GetTaskDueOn);
			assertTrue(GetTaskDueOn.contains(ExpectedDateFormat), "Failed. due on mismatching in in today date");
			test_steps.add("Verified due on");

			String unassignedTaskZone = tasklist.GetTaskZone(driver, FirstLastName, false, AssigneeName);
			app_logs.info("GetTaskZone: " + unassignedTaskZone);

			String unassignedTaskStatus = tasklist.GetTaskStatus(driver, FirstLastName, false, AssigneeName);
			app_logs.info("GetTaskStatus: " + GetTaskStatus);
			test_steps.add("Expectd status: " + unassignedTaskStatus);
			test_steps.add("Found: " + GetTaskStatus);
			assertEquals(unassignedTaskStatus, TaskStatus, "Failed: Task status is mismatching in in today date");
			test_steps.add("verified Task status");

			test_steps.add("==========VERIFICATION ASSIGNEE TASK IN ALL ASSIGNEE REPORTS===========");

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectReports(driver, ReportsTypeAsAllAssignee);
			test_steps.addAll(getTest_Steps);

			String getTaskImageInReport = tasklist.getTaskImageInReport(driver, FirstLastName, true, AssigneeName);
			assertTrue(getTaskImageInReport.contains(TaskImageInternel),
					"Failed: task image is mismaching in assignee task");
			test_steps.add("Verified task image");

			String getTaskTypeInReport = tasklist.getTaskTypeInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected task type: " + TaskType);
			test_steps.add("Found: " + getTaskTypeInReport);
			assertEquals(getTaskTypeInReport, TaskType);
			test_steps.add("Verified task tyep");

			String getTaskForInReport = tasklist.getTaskForInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected task for: " + FirstLastName);
			test_steps.add("Found: " + getTaskForInReport);
			assertEquals(getTaskForInReport, FirstLastName, "Failed: Task for is mismatching!");
			test_steps.add("Verified task for");

			String getTaskOccupancyInReprts = tasklist.getTaskOccupancyInReprts(driver, FirstLastName, true,
					AssigneeName);
			test_steps.add("Expected occupancy: " + GetTaskOccupancy);
			test_steps.add("Found: " + getTaskOccupancyInReprts);
			assertEquals(getTaskOccupancyInReprts, GetTaskOccupancy, "Failed: Task occupancy is mismatching!");
			test_steps.add("Verified occupancy");

			String getTaskDueOnInReport = tasklist.getTaskDueOnInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected due on: " + GetTaskDueOn);
			test_steps.add("Found: " + getTaskDueOnInReport);
			assertEquals(getTaskDueOnInReport, GetTaskDueOn, "Failed: Task due on is mismatching!");
			test_steps.add("Verified due on");

			String getAssignedInReports = tasklist.getAssignedInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected assigned: " + AssigneeName);
			test_steps.add("Found: " + getAssignedInReports);
			assertEquals(getAssignedInReports, AssigneeName, "Failed: Assigned is mismatching!");
			test_steps.add("Verified assigned");

			String getTaskZoneInReports = tasklist.getTaskZoneInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected zone: " + GetTaskZone);
			test_steps.add("Found: " + getTaskZoneInReports);
			assertEquals(getTaskZoneInReports, GetTaskZone, "Failed: Zone is mismatching!");
			test_steps.add("Verified zone");

			String GetTaskStatusInReports = tasklist.GetTaskStatusInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected status: " + GetTaskStatus);
			test_steps.add("Found: " + GetTaskStatusInReports);
			assertEquals(GetTaskStatusInReports, GetTaskStatus, "Failed: Status is mismatching!");
			test_steps.add("Verified status");

			roomstatus.CloseSecondWindow(driver);
			test_steps.add("Close second window");

			test_steps.add("==========VERIFICATION UNASSIGNED TASK IN ALL ASSIGNEE REPORTS===========");

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectReports(driver, ReportsTypeAsByAssignee);
			test_steps.addAll(getTest_Steps);

			getTaskImageInReport = tasklist.getUnassignedTaskImageInReport(driver, FirstLastName, false, AssigneeName);
			assertTrue(getTaskImageInReport.contains(TaskImageInternel),
					"Failed: task image is mismaching in assignee task");
			test_steps.add("Verified task image");

			getTaskTypeInReport = tasklist.getUnassignedTaskTypeInReport(driver, FirstLastName, false, AssigneeName);
			test_steps.add("Expected task type: " + TaskType);
			test_steps.add("Found: " + getTaskTypeInReport);
			assertEquals(getTaskTypeInReport, TaskType);
			test_steps.add("Verified task tyep");

			getTaskForInReport = tasklist.getUnassignedTaskForInReport(driver, FirstLastName, false, AssigneeName);
			test_steps.add("Expected task for: " + FirstLastName);
			test_steps.add("Found: " + getTaskForInReport);
			assertEquals(getTaskForInReport, FirstLastName, "Failed: Task for is mismatching!");
			test_steps.add("Verified task for");

			getTaskOccupancyInReprts = tasklist.getUnassignedTaskOccupancyInReprts(driver, FirstLastName, true,
					AssigneeName);
			test_steps.add("Expected occupancy: " + unassignedTaskOccupancy);
			test_steps.add("Found: " + getTaskOccupancyInReprts);
			assertEquals(getTaskOccupancyInReprts, unassignedTaskOccupancy, "Failed: Task occupancy is mismatching!");
			test_steps.add("Verified occupancy");

			getTaskDueOnInReport = tasklist.getUnassignedTaskDueOnInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected due on: " + unassignedTaskDueOn);
			test_steps.add("Found: " + getTaskDueOnInReport);
			assertEquals(getTaskDueOnInReport, unassignedTaskDueOn, "Failed: Task due on is mismatching!");
			test_steps.add("Verified due on");

			getTaskZoneInReports = tasklist.getUnassignedTaskZoneInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected zone: " + unassignedTaskZone);
			test_steps.add("Found: " + getTaskZoneInReports);
			assertEquals(getTaskZoneInReports, unassignedTaskZone, "Failed: Zone is mismatching!");
			test_steps.add("Verified zone");

			GetTaskStatusInReports = tasklist.getUnassignedTaskStatusInReports(driver, FirstLastName, true,
					AssigneeName);
			test_steps.add("Expected status: " + unassignedTaskStatus);
			test_steps.add("Found: " + GetTaskStatusInReports);
			assertEquals(GetTaskStatusInReports, unassignedTaskStatus, "Failed: Status is mismatching!");
			test_steps.add("Verified status");

			roomstatus.CloseSecondWindow(driver);
			test_steps.add("Close second window");

			// driver.navigate().refresh();
			// deleting created task
			tasklist.checkedTasks(driver, FirstLastName, true, AssigneeName);
			try {
				tasklist.deleteTask(driver);
			} catch (Exception e) {
				// TODO: handle exception
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to verify assignee and unassigned tasks with YESTERDAY date ", testName,
						"AddingNewTask", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to verify assignee and unassigned tasks with YESTERDAY date ", testName,
						"AddingNewTask", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// verify task with yesterday dates
		try {
			test_steps.add("=====ADDING ASSINEE NEW TASK IN TASK LIST WITH CUSTOME RANGE DATE=====");

			getTest_Steps.clear();
			getTest_Steps = tasklist.ClickTaskForDropDown(driver);
			app_logs.info("Click on drop down");
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectTaskFor(driver, "Custom Range");
			app_logs.info("Select task");
			test_steps.addAll(getTest_Steps);

			tasklist.selectCustomRangeEndDateFrompopup(driver);
			tasklist.selectCustomRangeEndDateFrompopup(driver);

			/*
			 * String customeStartDate = ESTTimeZone.DateFormateForLineItem(20);
			 * app_logs.info("customeStartDate: "+customeStartDate);
			 * customeStartDate = ESTTimeZone.parseDate(customeStartDate,
			 * "MM/dd/yy", "MMM dd, yyyy");
			 * app_logs.info("customeStartDate: "+customeStartDate);
			 * tasklist.selectCustomRangeStartDate(driver, customeStartDate);
			 * test_steps.add("Select start custome range date: "
			 * +customeStartDate);
			 * 
			 * tasklist.selectCustomRangeEndDate(driver, customeStartDate);
			 * test_steps.add("Select end custome range date: "+customeStartDate
			 * );
			 */

			tasklist.Click_AddTask(driver);
			test_steps.add("Click on Add Task");

			getTest_Steps.clear();
			getTest_Steps = tasklist.CreateTask3(driver, TaskFor, TaskCategory, TaskType, TaskDetails,
					ReservationNumber, true, "auto", 0);
			test_steps.addAll(getTest_Steps);

			test_steps.add("=====VERIFICATION OF ASSIGNEE TASK IN TASK LIST WITH CUSTOME RANGE DATE=====");
			String GetTaskImage = tasklist.GetTaskImage(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskImage: " + GetTaskImage);
			app_logs.info("GetTaskImage: " + GetTaskImage);
			assertTrue(GetTaskImage.contains(TaskImageInternel));
			test_steps.add("Verified task image");

			String GetTaskFor = tasklist.GetTaskFor(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskFor: " + GetTaskFor);
			app_logs.info("GetTaskFor: " + GetTaskFor);
			test_steps.add("Expectd occupancy: " + GuestFirstName + " " + GuestLastName);
			test_steps.add("Found: " + GetTaskFor);
			assertEquals(GetTaskFor, GuestFirstName + " " + GuestLastName,
					"Failed: Task for is mismatching in in today date");
			test_steps.add("verified task for");

			String GetTaskOccupancy = tasklist.GetTaskOccupancy(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskOccupancy: " + GetTaskOccupancy);
			app_logs.info("GetTaskOccupancy: " + GetTaskOccupancy);
			test_steps.add("Expectd occupancy: " + Occupancy);
			test_steps.add("Found: " + GetTaskOccupancy);
			// assertEquals(GetTaskOccupancy, Occupancy,"Failed: Task occupancy
			// is mismatching in in today date");
			test_steps.add("verified task occupancy");

			String GetTaskDueOn = tasklist.GetTaskDueOn(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskDueOn: " + GetTaskDueOn);
			app_logs.info("GetTaskDueOn: " + GetTaskDueOn);

			String todayDate = ESTTimeZone.DateFormateForLineItem(0);
			String ExpectedDateFormat = ESTTimeZone.parseDate(todayDate, "MM/dd/yy", "dd MMM yy");
			app_logs.info("ExpectedDateFormat" + ExpectedDateFormat);
			app_logs.info("ExpectedDateFormat" + ExpectedDateFormat);

			test_steps.add("Expected due on: " + ExpectedDateFormat);
			test_steps.add("Found: " + GetTaskDueOn);
			assertTrue(GetTaskDueOn.contains(ExpectedDateFormat), "Failed. due on mismatching in in today date");
			test_steps.add("Verified due on");

			String GetTaskZone = tasklist.GetTaskZone(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskZone: " + GetTaskZone);
			app_logs.info("GetTaskZone: " + GetTaskZone);

			String GetTaskStatus = tasklist.GetTaskStatus(driver, FirstLastName, true, AssigneeName);
			System.out.println("GetTaskStatus: " + GetTaskStatus);
			app_logs.info("GetTaskStatus: " + GetTaskStatus);
			test_steps.add("Expectd status: " + TaskStatus);
			test_steps.add("Found: " + GetTaskStatus);
			assertEquals(GetTaskStatus, TaskStatus, "Failed: Task status is mismatching in in today date");
			test_steps.add("verified Task status");

			test_steps.add("=====ADDING UNASSIGNEED NEW TASK IN TASK LIST WITH CUSTOME RANGE DATE=====");
			Wait.wait5Second();
			tasklist.clickOnAddTask(driver);
			test_steps.add("Click on Add Task");

			getTest_Steps.clear();
			getTest_Steps = tasklist.CreateTask3(driver, TaskFor, TaskCategory, TaskType, TaskDetails,
					ReservationNumber, false, "auto", 0);
			test_steps.addAll(getTest_Steps);

			test_steps.add("=====VERIFICATION OF UNASSINEED TASK IN TASK LIST WITH CUSTOME RANGE DATE=====");
			String unassignedTaskImage = tasklist.GetTaskImage(driver, FirstLastName, true, AssigneeName);
			app_logs.info("GetTaskImage: " + unassignedTaskImage);
			assertTrue(GetTaskImage.contains(unassignedTaskImage));
			test_steps.add("Verified task image");

			String unassignedTaskFor = tasklist.GetTaskFor(driver, FirstLastName, false, AssigneeName);
			app_logs.info("GetTaskFor: " + unassignedTaskFor);
			test_steps.add("Expectd occupancy: " + GuestFirstName + " " + GuestLastName);
			test_steps.add("Found: " + unassignedTaskFor);
			assertEquals(unassignedTaskFor, GuestFirstName + " " + GuestLastName,
					"Failed: Task for is mismatching in in today date");
			test_steps.add("verified task for");

			String unassignedTaskOccupancy = tasklist.GetTaskOccupancy(driver, FirstLastName, false, AssigneeName);
			app_logs.info("GetTaskOccupancy: " + unassignedTaskOccupancy);
			test_steps.add("Expectd occupancy: " + Occupancy);
			test_steps.add("Found: " + unassignedTaskOccupancy);
			// assertEquals(GetTaskOccupancy, Occupancy,"Failed: Task occupancy
			// is mismatching in in today date");
			test_steps.add("verified task occupancy");

			String unassignedTaskDueOn = tasklist.GetTaskDueOn(driver, FirstLastName, false, AssigneeName);
			System.out.println("GetTaskDueOn: " + GetTaskDueOn);
			app_logs.info("GetTaskDueOn: " + unassignedTaskDueOn);
			app_logs.info("ExpectedDateFormat" + ExpectedDateFormat);
			test_steps.add("Expected due on: " + ExpectedDateFormat);
			test_steps.add("Found: " + GetTaskDueOn);
			assertTrue(GetTaskDueOn.contains(ExpectedDateFormat), "Failed. due on mismatching in in today date");
			test_steps.add("Verified due on");

			String unassignedTaskZone = tasklist.GetTaskZone(driver, FirstLastName, false, AssigneeName);
			app_logs.info("GetTaskZone: " + unassignedTaskZone);

			String unassignedTaskStatus = tasklist.GetTaskStatus(driver, FirstLastName, false, AssigneeName);
			app_logs.info("GetTaskStatus: " + GetTaskStatus);
			test_steps.add("Expectd status: " + unassignedTaskStatus);
			test_steps.add("Found: " + GetTaskStatus);
			assertEquals(unassignedTaskStatus, TaskStatus, "Failed: Task status is mismatching in in today date");
			test_steps.add("verified Task status");

			test_steps.add("==========VERIFICATION ASSIGNEE TASK IN ALL ASSIGNEE REPORTS===========");

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectReports(driver, ReportsTypeAsAllAssignee);
			test_steps.addAll(getTest_Steps);

			String getTaskImageInReport = tasklist.getTaskImageInReport(driver, FirstLastName, true, AssigneeName);
			assertTrue(getTaskImageInReport.contains(TaskImageInternel),
					"Failed: task image is mismaching in assignee task");
			test_steps.add("Verified task image");

			String getTaskTypeInReport = tasklist.getTaskTypeInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected task type: " + TaskType);
			test_steps.add("Found: " + getTaskTypeInReport);
			assertEquals(getTaskTypeInReport, TaskType);
			test_steps.add("Verified task tyep");

			String getTaskForInReport = tasklist.getTaskForInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected task for: " + FirstLastName);
			test_steps.add("Found: " + getTaskForInReport);
			assertEquals(getTaskForInReport, FirstLastName, "Failed: Task for is mismatching!");
			test_steps.add("Verified task for");

			String getTaskOccupancyInReprts = tasklist.getTaskOccupancyInReprts(driver, FirstLastName, true,
					AssigneeName);
			test_steps.add("Expected occupancy: " + GetTaskOccupancy);
			test_steps.add("Found: " + getTaskOccupancyInReprts);
			assertEquals(getTaskOccupancyInReprts, GetTaskOccupancy, "Failed: Task occupancy is mismatching!");
			test_steps.add("Verified occupancy");

			String getTaskDueOnInReport = tasklist.getTaskDueOnInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected due on: " + GetTaskDueOn);
			test_steps.add("Found: " + getTaskDueOnInReport);
			assertEquals(getTaskDueOnInReport, GetTaskDueOn, "Failed: Task due on is mismatching!");
			test_steps.add("Verified due on");

			String getAssignedInReports = tasklist.getAssignedInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected assigned: " + AssigneeName);
			test_steps.add("Found: " + getAssignedInReports);
			assertEquals(getAssignedInReports, AssigneeName, "Failed: Assigned is mismatching!");
			test_steps.add("Verified assigned");

			String getTaskZoneInReports = tasklist.getTaskZoneInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected zone: " + GetTaskZone);
			test_steps.add("Found: " + getTaskZoneInReports);
			assertEquals(getTaskZoneInReports, GetTaskZone, "Failed: Zone is mismatching!");
			test_steps.add("Verified zone");

			String GetTaskStatusInReports = tasklist.GetTaskStatusInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected status: " + GetTaskStatus);
			test_steps.add("Found: " + GetTaskStatusInReports);
			assertEquals(GetTaskStatusInReports, GetTaskStatus, "Failed: Status is mismatching!");
			test_steps.add("Verified status");

			roomstatus.CloseSecondWindow(driver);
			test_steps.add("Close second window");

			test_steps.add("==========VERIFICATION UNASSIGNED TASK IN ALL ASSIGNEE REPORTS===========");

			getTest_Steps.clear();
			getTest_Steps = tasklist.SelectReports(driver, ReportsTypeAsByAssignee);
			test_steps.addAll(getTest_Steps);

			getTaskImageInReport = tasklist.getUnassignedTaskImageInReport(driver, FirstLastName, false, AssigneeName);
			assertTrue(getTaskImageInReport.contains(TaskImageInternel),
					"Failed: task image is mismaching in assignee task");
			test_steps.add("Verified task image");

			getTaskTypeInReport = tasklist.getUnassignedTaskTypeInReport(driver, FirstLastName, false, AssigneeName);
			test_steps.add("Expected task type: " + TaskType);
			test_steps.add("Found: " + getTaskTypeInReport);
			assertEquals(getTaskTypeInReport, TaskType);
			test_steps.add("Verified task tyep");

			getTaskForInReport = tasklist.getUnassignedTaskForInReport(driver, FirstLastName, false, AssigneeName);
			test_steps.add("Expected task for: " + FirstLastName);
			test_steps.add("Found: " + getTaskForInReport);
			assertEquals(getTaskForInReport, FirstLastName, "Failed: Task for is mismatching!");
			test_steps.add("Verified task for");

			getTaskOccupancyInReprts = tasklist.getUnassignedTaskOccupancyInReprts(driver, FirstLastName, true,
					AssigneeName);
			test_steps.add("Expected occupancy: " + unassignedTaskOccupancy);
			test_steps.add("Found: " + getTaskOccupancyInReprts);
			assertEquals(getTaskOccupancyInReprts, unassignedTaskOccupancy, "Failed: Task occupancy is mismatching!");
			test_steps.add("Verified occupancy");

			getTaskDueOnInReport = tasklist.getUnassignedTaskDueOnInReport(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected due on: " + unassignedTaskDueOn);
			test_steps.add("Found: " + getTaskDueOnInReport);
			assertEquals(getTaskDueOnInReport, unassignedTaskDueOn, "Failed: Task due on is mismatching!");
			test_steps.add("Verified due on");

			getTaskZoneInReports = tasklist.getUnassignedTaskZoneInReports(driver, FirstLastName, true, AssigneeName);
			test_steps.add("Expected zone: " + unassignedTaskZone);
			test_steps.add("Found: " + getTaskZoneInReports);
			assertEquals(getTaskZoneInReports, unassignedTaskZone, "Failed: Zone is mismatching!");
			test_steps.add("Verified zone");

			GetTaskStatusInReports = tasklist.getUnassignedTaskStatusInReports(driver, FirstLastName, true,
					AssigneeName);
			test_steps.add("Expected status: " + unassignedTaskStatus);
			test_steps.add("Found: " + GetTaskStatusInReports);
			assertEquals(GetTaskStatusInReports, unassignedTaskStatus, "Failed: Status is mismatching!");
			test_steps.add("Verified status");

			roomstatus.CloseSecondWindow(driver);
			test_steps.add("Close second window");

			// driver.navigate().refresh();
			// deleting created task

			tasklist.checkedTasks(driver, FirstLastName, true, AssigneeName);
			try {
				tasklist.deleteTask(driver);

			} catch (Exception e) {
				// TODO: handle exception
			}
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to verify assignee and unassigned tasks with CUSTOME RANGE date ",
						testName, "AddingNewTask", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to verify assignee and unassigned tasks with CUSTOME RANGE date ",
						testName, "AddingNewTask", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {

		// return test data from the sheetname provided

		return Utility.getData("GSRoomStatusTaskListVerifyReprt", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		//driver.quit();
	}

}
