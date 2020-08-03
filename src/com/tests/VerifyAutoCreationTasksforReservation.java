package com.innroad.inncenter.tests;

import java.util.ArrayList;

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
import com.innroad.inncenter.pageobjects.Properties;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.Reports;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.TaskManagement;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class VerifyAutoCreationTasksforReservation extends TestCore{
	
	private WebDriver driver = null;
	public static String test_name = null;
	public static String test_nameOne = null;
	public static String test_description = null;
	public static String test_catagory = null;
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	Navigation navigation = new Navigation();
	Properties properties = new Properties();
	RoomClass roomClass = new RoomClass();
	Reports report = new Reports();
	Rate rate = new Rate();
	CPReservationPage reservationPage = new CPReservationPage();
	ReservationSearch revSearch = new ReservationSearch();
	TaskManagement taskmang = new TaskManagement();
	ArrayList<String> roomNumberAssigned = new ArrayList<>();
	String  randomNum = null,yearDate = null, reservation = null, categoryName=null, checkInTask=null,checkOutTask=null,
					checkInDateTime=null,checkOutDateTime=null,checkInDueTime=null,checkOutDueTime=null;;
			
	String testName = this.getClass().getSimpleName().trim();
	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
		
	}
	
	private void handelingCatchError(Error e, String desc, String category, String module) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
			Utility.updateReport(e, desc, category, module, driver);
		} else {
			Assert.assertTrue(false);
		}
	}

	private void handelingCatchException(Exception e, String desc, String category, String module) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
			Utility.updateReport(e, desc, category, module, driver);
		} else {
			Assert.assertTrue(false);
		}
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void verifyAutoCreationTasksforReservation(String category,String taskName, String maxAdults,
			 String checkInDate, String checkOutDate, String salutation,String guestFirstName, String guestLastName, 
			 String paymentType, String cardNumber, String nameOnCard,
			String marketSegment, String referral)
	{
		test_description = testName + "<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682450' target='_blank'>"
				+ "Click here to open TestRail: C682450</a><br>";
		test_catagory = "CPReservation_CheckOut";
		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");
		
		randomNum = Utility.GenerateRandomNumber();
		categoryName=category;
	

		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);
			} else {
				Utility.reTry.replace(testName, 1);
			}

		
			// Get CheckIN and Checkout Date

			if (!(Utility.validateInput(checkInDate))) {
				for (int i = 0; i < guestFirstName.split("\\|").length; i++) {
					checkInDates.add(Utility.getCurrentDate("dd/MM/yyyy"));
					checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(2), "MM/dd/yyyy", "dd/MM/yyyy"));
				}
			} else {
				checkInDates = Utility.splitInputData(checkInDate);
				checkOutDates = Utility.splitInputData(checkOutDate);
			}
			checkInDate = checkInDates.get(0);
			checkOutDate = checkOutDates.get(0);
			app_logs.info(checkInDate);
			app_logs.info(checkOutDate);
			checkInDateTime=Utility.parseDate(Utility.getDatePast_FutureDate(1), "MM/dd/yyyy", "dd/MM/yyyy");
			app_logs.info(checkInDateTime);
			
			
		} catch (Exception e) {
			handelingCatchException(e, "Failed to Get Check In Date and Checkout Date", testName, "Pre-Requisites");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Get Check In Date and Checkout Date", testName, "Pre-Requisites");
		}

		// Login
		try {
			driver = getDriver();
			login_CP(driver);
			test_steps.add("Logged into the application");
			app_logs.info("Logged into the application");
			
		} catch (Exception e) {
			handelingCatchException(e, "Failed to login", testName, "Login");
		} catch (Error e) {
			handelingCatchError(e, "Failed to login", testName, "Login");
		}
        
		try
		{
			test_steps.add("========== Delete Category ==========");
			navigation.setup(driver);
			test_steps.add("Navigated to Setup");
			navigation.taskManagement_TabExist(driver);
			test_steps.add("Task Management Tab Exist");
			navigation.taskManagement(driver);
			test_steps.add("Click on Task Management");
			app_logs.info("Click on Task Management");
			taskmang.deleteAllCategories(driver, categoryName,test_steps);
		}catch (Exception e) {
			handelingCatchException(e, "Failed to Delete Category", testName, "Task Management");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Delete Category", testName, "Task Management");
		}
		
		try
		{
			test_steps.add("========== Create Category and New Task ==========");
			categoryName=categoryName+"_"+randomNum;
			taskmang.createTaskCategory(driver, categoryName,test_steps);
			app_logs.info("Created Category: " + categoryName);
			checkInTask=taskName+"_CheckIn_"+randomNum;
			app_logs.info(checkInTask);
			checkOutTask=taskName+"_CheckOut_"+randomNum;
			app_logs.info(checkOutTask);
			taskmang.createTask(driver, checkInTask, categoryName);
			app_logs.info("Create Task: " + checkInTask);
			checkInDueTime=taskmang.getTaskDueTime(driver, checkInTask);
			taskmang.enableAutoCreate(driver, checkInTask, true);
			test_steps.add("Enable Auto Create");
			app_logs.info("Enable Auto Create");
			taskmang.setFrequency(driver, "Every Day", checkInTask);
			test_steps.add("Set Frequency : <b>" + "Every Day" +"</b>");
			app_logs.info(" Set Frequency :" + "Every Day");
			test_steps.add("Task Created : <b>" + checkInTask +"</b>");
			app_logs.info(" Task Created: " + checkInTask);
			taskmang.createTask(driver, checkOutTask, categoryName);
			checkOutDueTime=taskmang.getTaskDueTime(driver, checkOutTask);			
			app_logs.info("Create Task: " + checkOutTask);
			taskmang.enableAutoCreate(driver, checkOutTask, true);
			test_steps.add("Enable Auto Create");
			app_logs.info("Enable Auto Create");
			taskmang.setFrequency(driver, "None", checkOutTask);
			test_steps.add("Set Frequency : <b>" + "None" +"</b>");
			app_logs.info(" Set Frequency :" + "None");
			taskmang.scheduleOnCheckOut(driver, checkOutTask, true);
			test_steps.add("Schedule On CheckOut");
			app_logs.info("Schedule On CheckOut");
			test_steps.add("Task Created : <b>" + checkOutTask +"</b>");
			app_logs.info(" Task Created: " + checkOutTask);
			
		}catch (Exception e) {
			handelingCatchException(e, "Failed to Create New Category and task", testName, "Task Management");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Create New Category and task", testName, "Task Management");
		}
	// Create Reservation
				try {
					test_steps.add("<b>==========Start Creating Reservation==========</b>");
					navigation.reservation_Backward_2(driver);
					app_logs.info("Navigate to Reservation");
					reservationPage.click_NewReservation(driver, test_steps);
					reservationPage.select_CheckInDate(driver, test_steps, checkInDate);
					reservationPage.select_CheckoutDate(driver, test_steps, checkOutDate);
					reservationPage.enter_Adults(driver, test_steps, maxAdults);
					reservationPage.clickOnFindRooms(driver, test_steps);
					reservationPage.select_SpecificRoom(driver, test_steps, "Double Bed Room", "", "");
					reservationPage.clickNext(driver, test_steps);
					yearDate = Utility.getFutureMonthAndYearForMasterCard(driver, test_steps);
					reservationPage.enter_MailingAddress_OnUnchecked_CreateGuestProfile(driver, test_steps, salutation,
							guestFirstName+ randomNum, guestLastName+ randomNum, "No");
					reservationPage.enter_PaymentDetails(driver, test_steps, paymentType, cardNumber, nameOnCard, yearDate);
					reservationPage.enter_MarketSegmentDetails(driver, test_steps, "", marketSegment, referral);
					reservationPage.clickBookNow(driver, test_steps);
					reservation = reservationPage.get_ReservationConfirmationNumber(driver, test_steps);
					reservationPage.clickCloseReservationSavePopup(driver, test_steps);
				} catch (Exception e) {
					handelingCatchException(e, "Failed to Create Reservation", testName, "Reservation");
				} catch (Error e) {
					handelingCatchError(e, "Failed to Create Reservation", testName, "Reservation");
				}
				
					
				// Check In
				try {
					test_steps.add("<b>==========Start Check In==========</b>");
					reservationPage.clickCheckInButton(driver, test_steps);
					reservationPage.generatGuestReportToggle(driver, test_steps, "No");
					reservationPage.completeCheckInProcessSingleRev(driver, test_steps);
					String loading = "(//div[@class='ir-loader-in'])";
					String statusPath = "//span[@class='ng-status'][contains(text(),'In-House')]";
					Wait.explicit_wait_absenceofelement(loading, driver);
					Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(statusPath)), driver);
					test_steps.add("<b>==========Start Verifying In-House Status==========</b>");
					reservationPage.verifyReservationStatusStatus(driver, test_steps, "IN-HOUSE");
					test_steps.add("<b>==========Start Verifying Check-Out Button ==========</b>");
					reservationPage.verifyCheckOutButton(driver, test_steps);

				} catch (Exception e) {
					handelingCatchException(e, "Failed to Check In Reservation", testName, "Reservation");
				} catch (Error e) {
					handelingCatchError(e, "Failed to Check In Reservation", testName, "Reservation");
				}
				
				try
				{
					
					revSearch.closeReservation(driver, guestFirstName);	
					app_logs.info("Close the browser");
					Wait.wait60Second();
					revSearch.basicSearch_WithResNumber(driver, reservation);	
					driver.navigate().refresh();
					Wait.wait60Second();
					test_steps.add("<b>==========Verify Reservation Task==========</b>");
					checkInDateTime=Utility.parseDate(checkInDateTime, "dd/MM/yyyy", "MM/dd/ yy");
					app_logs.info(checkInDateTime);
					checkOutDateTime=Utility.parseDate(checkOutDate, "dd/MM/yyyy", "MM/dd/ yy");
					app_logs.info(checkOutDateTime);
					String finalCheckInDateTime=checkInDateTime+" "+checkInDueTime;
					app_logs.info(finalCheckInDateTime);
					String finalCheckOutDateTime=checkOutDateTime+" "+checkOutDueTime;
					app_logs.info(finalCheckOutDateTime);
				
					reservationPage.verifyReservationTask(driver, checkInTask, "Automatically generated at checkin",finalCheckInDateTime,"To Do", test_steps);
					reservationPage.verifyReservationTask(driver, checkOutTask, "Automatically generated at checkin",finalCheckOutDateTime,"To Do", test_steps);
						
				}
				catch (Exception e) {
					handelingCatchException(e, "Failed to Verify Check In/Out Task", testName, "Reservation");
				} catch (Error e) {
					handelingCatchError(e, "Failed to Verify Check In/Out Task", testName, "Reservation");
				}
				
				try
				{
					test_steps.add("========== Delete Category ==========");
					navigation.setup(driver);
					test_steps.add("Navigated to Setup");
					navigation.taskManagement_TabExist(driver);
					test_steps.add("Task Management Tab Exist");
					navigation.taskManagement(driver);
					test_steps.add("Click on Task Management");
					app_logs.info("Click on Task Management");
					taskmang.deleteAllCategories(driver, categoryName,test_steps);
					 RetryFailedTestCases.count = Utility.reset_count;
				     Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				}catch (Exception e) {
					handelingCatchException(e, "Failed to Delete Category", testName, "Task Management");
				} catch (Error e) {
					handelingCatchError(e, "Failed to Delete Category", testName, "Task Management");
				}
	}
	@DataProvider
	public Object[][] getData() {

		return Utility.getData("VerifyAutoCreationTasksforReser", excel);

	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
