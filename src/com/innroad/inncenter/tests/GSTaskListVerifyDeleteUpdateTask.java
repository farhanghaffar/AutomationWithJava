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
import com.innroad.inncenter.pageobjects.Login;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Reservation;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.TaskList;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class GSTaskListVerifyDeleteUpdateTask extends TestCore {

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

	@Test(dataProvider = "getData", groups = "GuestServices")
	public void gSTaskListVerif_DeleUpdateTask(String url, String ClientCode, String Username, String Password,
			String CheckInDate, String CheckOutDate, String Adults, String Children, String Rateplan, String PromoCode,
			String RoomClass, String IsAssign, String IsDepositOverride, String DepositOverrideAmount,
			String Salutation, String GuestFirstName, String GuestLastName, String PhoneNumber, String AltenativePhone,
			String Email, String Account, String AccountType, String Address1, String Address2, String Address3,
			String City, String Country, String State, String PostalCode, String IsGuesProfile, String PaymentType,
			String CardNumber, String NameOnCard, String CardExpDate, String IsChangeInPayAmount,
			String ChangedAmountValue, String TravelAgent, String MarketSegment, String Referral, String IsAddNotes,
			String NoteType, String Subject, String Description, String IsTask, String TaskCategory, String TaskType,
			String TaskDetails, String TaskRemarks, String TaskDueon, String TaskAssignee, String TaskStatus)
			throws InterruptedException, IOException {

		test_name = "GSTaskListVerif_DeleUpdateTask";
		testDescription = "GS-Task List-Verify the functionality of deleting and updating a task <br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/51111' target='_blank'>"
				+ "Click here to open TestRail: C51111</a>";
		testCategory = "Verification";
		String testName = test_name;

		TestName.add(testName);
		TestDescription.add(testDescription);
		TestCategory.add(testCategory);
		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Navigation nav = new Navigation();
		TaskList tasklist = new TaskList();
		ReservationSearch reser_search = new ReservationSearch();
		CPReservationPage res = new CPReservationPage();
		Double depositAmount = 0.0;
		String reservationNumber = null;
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
		// Create Reservation
		try {
			app_logs.info("======CP Reservation Creation=======");
			test_steps.add("======CP Reservation Creation=======");
			res.click_NewReservation(driver, test_steps);
			res.select_CheckInDate(driver, test_steps, CheckInDate);
			res.select_CheckoutDate(driver, test_steps, CheckOutDate);
			res.enter_Adults(driver, test_steps, Adults);
			res.enter_Children(driver, test_steps, Children);
			res.select_Rateplan(driver, test_steps, Rateplan, PromoCode);
			res.clickOnFindRooms(driver, test_steps);
			res.selectRoom(driver, test_steps, RoomClass, IsAssign, Account);
			depositAmount = res.deposit(driver, test_steps, IsDepositOverride, DepositOverrideAmount);
			res.clickNext(driver, test_steps);
			res.enter_MailingAddress(driver, test_steps, Salutation, GuestFirstName, GuestLastName, PhoneNumber,
					AltenativePhone, Email, Account, AccountType, Address1, Address2, Address3, City, Country, State,
					PostalCode, IsGuesProfile);
			if ((Account.equalsIgnoreCase("") || Account.isEmpty())) {
				res.enter_PaymentDetails(driver, test_steps, PaymentType, CardNumber, NameOnCard, CardExpDate);
			}
			res.enter_MarketSegmentDetails(driver, test_steps, TravelAgent, MarketSegment, Referral);
			res.clickBookNow(driver, test_steps);
			reservationNumber = res.get_ReservationConfirmationNumber(driver, test_steps);
			res.clickCloseReservationSavePopup(driver, test_steps);
			res.close_FirstOpenedReservation(driver, getTest_Steps);

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
		// Add New Task
		try {
			app_logs.info("======Create Task In GuestServices & Assign Reservation=======");
			test_steps.add("======Create Task In GuestServices & Assign Reservation=======");
			nav.Guestservices(driver);
			app_logs.info("Navigate To Guest Services");
			test_steps.add("Navigate To Guest Services ");
			nav.Nav_TaskList(driver);
			app_logs.info("Navigate To Task_List");
			test_steps.add("Navigate To Task List");
			getTest_Steps.clear();
			getTest_Steps = tasklist.Click_AddTask(driver);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tasklist.CreateNewTask(driver, "Reservation", TaskCategory, TaskType, TaskDetails,
					TaskRemarks, TaskAssignee, reservationNumber, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			test_steps.add("Successfully Added New Task");
			app_logs.info("Successfully Added New Task");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Add New Task", testName, "TaskAdd", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Add New Task", testName, "TaskAdd", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Delete Added Task
		try {

			app_logs.info("======Verify Task In Reservation & Detele Task=======");
			test_steps.add("======Verify Task In Reservation & Detele Task=======");
			nav.Reservation_Backward_2(driver);
			app_logs.info("Navigate back to Reservation");
			test_steps.add("Navigate back to Reservation");
			reser_search.SearchAndOpenRes(driver, reservationNumber);
			app_logs.info("Successfully Searched & Opened Reservation " + reservationNumber);
			test_steps.add("Successfully Searched &Opened Reservation " + reservationNumber);
			getTest_Steps.clear();
			getTest_Steps = res.VerifyCreatedTask(driver, TaskType, TaskDetails, TaskStatus, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			getTest_Steps = res.DeleteTask(driver, TaskDetails, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			res.close_FirstOpenedReservation(driver, getTest_Steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify & Delete Added  Task", testName, "Verification", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to  Verify & Delete Added  Task", testName, "Verification", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		String GuestName = GuestFirstName + " " + GuestLastName;
		// Verify Deleted Task
		try {
			app_logs.info("======Verify Deleted Task In TaskList=======");
			test_steps.add("======Verify Deleted Task In TaskList ======");
			nav.Guestservices(driver);
			app_logs.info("Navigate To Guest Services");
			test_steps.add("Navigate To Guest Services");
			nav.Task_List(driver);
			app_logs.info("Navigate To Task_List");
			test_steps.add("Navigate To Task_List");
			tasklist.SearchTask(driver, GuestName);
			test_steps.add("No tasks found for the selected criteria and property.");
			test_steps.add("Successfully Verified Deleted Task");
			app_logs.info("Successfully Verified Deleted  Task");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify Deleted  Task", testName, "Verification", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to  Verify Deleted Task", testName, "Verification", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		String TaskListID_ToDeleteANdCheck = "";
		// Delete Any Task from the TaskList
		try {
			app_logs.info("====== Deleted Any Task In TaskList=======");
			test_steps.add("====== Deleted Any Task In TaskList ======");
			driver.navigate().refresh();
			nav.Task_List(driver);
			app_logs.info("Navigate To Task_List");
			test_steps.add("Navigate To Task_List");
			tasklist.SearchTask(driver, TaskType);
			app_logs.info("Search Task With Type: " + TaskType);
			test_steps.add("Search Task With Type:" + TaskType);
			String beforeTaskDeleteCount = tasklist.GetAllCount(driver, true);
			tasklist.SelectFirstTask(driver);
			test_steps.add("Select the First Task From the List");
			app_logs.info("CSelect the First Task From the List");
			TaskListID_ToDeleteANdCheck = tasklist.GetFirstTaskListID(driver);
			test_steps.add("Get the ID of the Task: " + TaskListID_ToDeleteANdCheck);
			app_logs.info("Get the ID of the Task: " + TaskListID_ToDeleteANdCheck);
			Wait.wait3Second();
			getTest_Steps.clear();
			getTest_Steps = tasklist.DeleteAnyTask(driver, tasklist.getFirstTaskDetailsInTaskList(driver));
			test_steps.addAll(getTest_Steps);
			test_steps.add(
					"Successfully Deleted Task from the list With Reservation Number: " + TaskListID_ToDeleteANdCheck);
			app_logs.info(
					"Successfully Deleted Task from the list With Reservation Number " + TaskListID_ToDeleteANdCheck);
			String afterTaskDeleteCount = tasklist.GetAllCount(driver, false);
			assertEquals(Float.parseFloat(beforeTaskDeleteCount) > Float.parseFloat(afterTaskDeleteCount), true);
			test_steps.add("Count Before Task Delete: " + beforeTaskDeleteCount
					+ " is Greater Than Count After Task Delete: " + afterTaskDeleteCount);
			app_logs.info("Count Before Task Delete: " + beforeTaskDeleteCount
					+ " is Greater Than Count After Task Delete: " + afterTaskDeleteCount);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to  Delete  any Task from the list", testName, "Deletion", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to   Delete any Task from the list", testName, "Deletion", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verify Deleted Task
		try {
			app_logs.info("====== Verify Deleted Task In Reservation=======");
			test_steps.add("====== Verify Deleted  Task In Reservation ======");
			nav.Reservation_Backward_2(driver);
			reser_search.basicSearch_WithResNumberVerification(driver, TaskListID_ToDeleteANdCheck);
			test_steps.add("Successfully Verified On Deleting Task, Reservation is not Deleted");
			app_logs.info("Successfully Verified  On Deleting Task, Reservation is not Deleted");
			res.close_FirstOpenedReservation(driver, getTest_Steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify Deleted Task", testName, "Verification", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to  Verify Deleted Task", testName, "Verification", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		GuestLastName = GuestLastName + Utility.GenerateRandomNumber();
		GuestName = GuestFirstName + " " + GuestLastName;
		try {
			app_logs.info("======CP Reservation Creation=======");
			test_steps.add("======CP Reservation Creation=======");
			res.click_NewReservation(driver, test_steps);
			res.select_CheckInDate(driver, test_steps, CheckInDate);
			res.select_CheckoutDate(driver, test_steps, CheckOutDate);
			res.enter_Adults(driver, test_steps, Adults);
			res.enter_Children(driver, test_steps, Children);
			res.select_Rateplan(driver, test_steps, Rateplan, PromoCode);
			res.clickOnFindRooms(driver, test_steps);
			res.selectRoom(driver, test_steps, RoomClass, IsAssign, Account);
			depositAmount = res.deposit(driver, test_steps, IsDepositOverride, DepositOverrideAmount);
			res.clickNext(driver, test_steps);
			res.enter_MailingAddress(driver, test_steps, Salutation, GuestFirstName, GuestLastName, PhoneNumber,
					AltenativePhone, Email, Account, AccountType, Address1, Address2, Address3, City, Country, State,
					PostalCode, IsGuesProfile);
			if ((Account.equalsIgnoreCase("") || Account.isEmpty())) {
				res.enter_PaymentDetails(driver, test_steps, PaymentType, CardNumber, NameOnCard, CardExpDate);
			}
			res.enter_MarketSegmentDetails(driver, test_steps, TravelAgent, MarketSegment, Referral);
			res.clickBookNow(driver, test_steps);
			reservationNumber = res.get_ReservationConfirmationNumber(driver, test_steps);
			res.clickCloseReservationSavePopup(driver, test_steps);
			res.close_FirstOpenedReservation(driver, getTest_Steps);
			Wait.wait2Second();

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
		// Add New Task
		try {
			app_logs.info("======Create Task In GuestServices & Assign Reservation=======");
			test_steps.add("======Create Task In GuestServices & Assign Reservation=======");
			nav.Guestservices(driver);
			app_logs.info("Navigate To Guest Services");
			test_steps.add("Navigate To Guest Services ");
			nav.Nav_TaskList(driver);
			app_logs.info("Navigate To Task_List");
			test_steps.add("Navigate To Task List");
			getTest_Steps.clear();
			getTest_Steps = tasklist.Click_AddTask(driver);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			getTest_Steps = tasklist.CreateNewTask(driver, "Reservation", TaskCategory, TaskType, TaskDetails,
					TaskRemarks, TaskAssignee, reservationNumber, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			test_steps.add("Successfully Added New Task");
			app_logs.info("Successfully Added New Task");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Add New Task", testName, "TaskAdd", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Add New Task", testName, "TaskAdd", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		String newCaterogy = "Housekeeping";
		String newType = "Full Cleaning";
		String newStatus = "Done";
		String newDetails = "New Details";
		// Edit Task in TaskList
		try {
			tasklist.SearchTask(driver, GuestName);
			test_steps.add("Searched the Task With Name: " + GuestName);
			app_logs.info("Searched the Task With Name: " + GuestName);
			tasklist.SelectFirstTask(driver);
			String activityLogCountBefore = tasklist.GetActivityLogCount(driver);
			System.out.print("toggle:" + activityLogCountBefore);
			Wait.wait3Second();
			tasklist.ClickEditButton(driver);
			test_steps.add("Click the Edit Button of Task ResvertionNumber: " + reservationNumber);
			app_logs.info("Click the Edit Button of Task ResvertionNumber: " + reservationNumber);
			getTest_Steps.clear();
			getTest_Steps = tasklist.EditAnyTask(driver, newCaterogy, newType, newStatus, newDetails, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			test_steps.add("Successfully Edited Task With Reservation Number:  " + reservationNumber);
			app_logs.info("Successfully Edited Task With Reservation Number: " + reservationNumber);
			driver.navigate().refresh();
			nav.Task_List(driver);
			app_logs.info("Navigate To Task_List");
			test_steps.add("Navigate To Task_List");
			tasklist.GetAllCount(driver, true);
			tasklist.SearchTask(driver, GuestName);
			test_steps.add("Searched Again the Task With Name: " + GuestName);
			app_logs.info("Searched Again the Task With Name: " + GuestName);
			tasklist.SelectFirstTask(driver);
			test_steps.add("Select the First Task From the List");
			app_logs.info("Select the First Task From the List");
			String activityLogCountAfter = tasklist.GetActivityLogCount(driver);
			assertEquals(Float.parseFloat(activityLogCountAfter) > Float.parseFloat(activityLogCountBefore), true);
			test_steps.add("Toggle Count Before Task Update: " + activityLogCountBefore
					+ " is Less Than Count After Task Update: " + activityLogCountAfter);
			app_logs.info("Toggle Count Before Task Update: " + activityLogCountBefore
					+ " is Less Than Count After Task Update: " + activityLogCountAfter);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Edit  Task", testName, "Edit", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to EditTask", testName, "Edit", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verify Edited Task
		try {
			app_logs.info("====== Verify Edited Task In Reservation=======");
			test_steps.add("====== Verify Edited  Task In Reservation ======");
			nav.Reservation_Backward_2(driver);
			reser_search.basicSearch_WithResNumber(driver, reservationNumber);
			getTest_Steps.clear();
			getTest_Steps = res.VerifyCreatedTask(driver, newType, newDetails, newStatus, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			test_steps.add("Successfully Verified Task Is Edited in Reservation " + reservationNumber);
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(test_name, testDescription, testCategory, test_steps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestDescription, test_steps);
				Utility.updateReport(e, "Failed to Verify Edited Task", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestDescription, test_steps);
				Utility.updateReport(e, "Failed to Verify  Edited Task", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		}

	}

	@DataProvider
	public Object[][] getData() {

		// return test data from the sheet name provided

		return Utility.getData("GSTaskListVerif_DeleUpdateTask", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		// driver.quit();
	}

}
