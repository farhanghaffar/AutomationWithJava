package com.innroad.inncenter.tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.OverView;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.RoomMaintenance;
import com.innroad.inncenter.pageobjects.RoomStatus;
import com.innroad.inncenter.pageobjects.Tapechart;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class GSRoomMaintenanceVerifyTapeChartAndRoomStatus extends TestCore {

	private WebDriver driver = null;
	public static String test_name = null;
	public static String test_nameOne = null;
	public static String test_description = null;
	public static String test_catagory = null;
	ArrayList<String> test_steps = new ArrayList<>();
	RoomClass roomClass = new RoomClass();
	public static String roomClassName;
	public static String roomClassAbbrivation;
	Navigation nav = new Navigation();
	CPReservationPage res = new CPReservationPage();
	RoomMaintenance room_maintenance = new RoomMaintenance();
	RoomStatus roomstatus = new RoomStatus();
	OverView overview = new OverView();
	Tapechart tapechart = new Tapechart();
	ArrayList<String> roomCost = new ArrayList<String>();
	ArrayList<String> rooms = new ArrayList<String>();
	List<LocalDate> datesList = new ArrayList<LocalDate>();
	ArrayList<String> roomMaintance = new ArrayList<String>();
	List<String> date = new ArrayList<String>();
	List<String> day = new ArrayList<String>();
	Rate rate = new Rate();
	String guestFirstName = null, guestLastName = null, reservation = null, testName = null, rateName = null,
			rateDisplayName = null;
	String nights = null, startDate = null, endDate = null;

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "GuestServices")
	public void gsRoomMaintenanceVerifyTapeChartAndRoomStatus(String URL, String ClientId, String UserName,
			String Password, String RoomClassName, String RoomClassAbbrivation, String BedsCount, String MaxAdults,
			String MaxPersopns, String RoomQuantity, String RateName, String DisplayName, String Amount,
			String RatePolicy, String RateDescription, String Subject, String Detail, String RatePlan, String AssociateSeason) {
		test_name = "GSRoomMaintenanceVerifyTapeChartAndRoomStatusAndInventory";
		test_description = "GS-Room Maintenance-Making a Room Out Of Order And Verify TapeChart , RoomStatus and Inventory<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682447' target='_blank'>"
				+ "Click here to open TestRail: C682447</a><br>";
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

// Create Room Class		
		try {
			
			test_steps.add("<b>****Start Creating New Room Class****</b>");
			nav.Setup(driver);
			test_steps.add("Navigated to Setup");
			nav.RoomClass(driver);
			test_steps.add("Navigated to Room Class");
			roomClassName = RoomClassName + Utility.getTimeStamp();
			roomClassAbbrivation = RoomClassAbbrivation + Utility.getTimeStamp();
			nav.NewRoomClass(driver);
			test_steps.add("Navigated to New Room Class");
			app_logs.info("Navigated to New Room Class");
			roomClass.create_RoomClass(driver, roomClassName, roomClassAbbrivation, BedsCount, MaxAdults, MaxPersopns, RoomQuantity,
					test, test_steps);

			test_steps.add("Room Class Created: <b>"+roomClassName+"</>");
			app_logs.info(" Room Class Created: "+roomClassName);
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

		// Create Rates and Associate Room Class
		try {
			test_steps.add("<b>****Start Create New Rates and Associate Room Class with Rates****</b>");
			nav.Inventory(driver);
			test_steps.add("Navigate to Inventory");
			app_logs.info("Navigate to Inventory");
			nav.Rates1(driver);
			test_steps.add("Navigate to Rates");
			rateName = RateName + Utility.getTimeStamp();
			rateDisplayName = DisplayName + Utility.getTimeStamp();
			rate.CreateRate(driver, rateName, MaxAdults, MaxPersopns, Amount, MaxAdults, MaxPersopns, rateDisplayName,
					RatePolicy, RateDescription, roomClassName, test_steps);
			
			
			test_steps.add("Successfull Created Rate: <b>" + rateName + "</b>");
			app_logs.info("Successfull Created Rate: " + rateName);

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

		// Create New Out of Order Room
		try {
			test_steps.add("<b>****Start Creating Room Maintenance****</b>");
			nav.Guestservices(driver);
			test_steps.add("Navigated to Guestservices");
			roomstatus.verifyRoomStatusTabEnabled(driver, test_steps);
			roomstatus.verifiedGuestServicesDataLoadedOrNot(driver, test_steps);
			nav.RoomMaintenance(driver);
			test_steps.add("Navigated to RoomMaintenance");
			roomMaintance = room_maintenance.createNewRoomOutOfOrder(driver, Subject + Utility.getTimeStamp(),
					Utility.RoomNo, roomClassName, test_steps, Detail);
			startDate = roomMaintance.get(0);
			endDate = roomMaintance.get(1);
			nights = roomMaintance.get(2);
			test_steps.add("Successfully Created New Out of Order Room");
			app_logs.info("Successfully Created New Out of Order Room");
			datesList = room_maintenance.getDatesAsPerNights(driver, test_steps, startDate, endDate, "MMM dd, yyyy");
			date = room_maintenance.getDateOnlyAsperNights(driver, test_steps, datesList, "MMM dd, yyyy", "dd");
			day = room_maintenance.getDayAsperNights(driver, test_steps, datesList, "MMM dd, yyyy", "E");
	
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Create New Out of Order Room", testName, "RoomCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Create New Out of Order Room", testName, "RoomCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verified Room Status
		try {
			test_steps.add("<b>****Start Verfying Room Status****</b>");
			roomstatus.clickRoomStatusTab(driver);
			roomstatus.searchByRoomHash(driver, Utility.RoomNo, roomClassName, test_steps);
			roomstatus.verifyRoomStatusWithSpecificRoomNo(driver, Utility.RoomNo, roomClassName, "Out of Order",
					test_steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Room Status", testName, "Navigation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Room Status", testName, "Navigation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Navigate to TapeChart
		try {

			test_steps.add("<b>*****Start Verifying Out OF Order Room in Tape Chart*****</b>");
			nav.Reservation_Backward_2(driver);
			test_steps.add("Navigate to Reservation");
			app_logs.info("Navigate to Reservation");
			nav.navTapeChart(driver, test);
			test_steps.add("Successfully Navigate to TapeChart");
			app_logs.info("Successfully Navigate to TapeChart");
			tapechart.verifyOutOfOrder(driver, test_steps, roomClassAbbrivation, Utility.RoomNo);
			test_steps.add("Successfully Verified Out of Order Room in Tape Chart");
			app_logs.info("Successfully Verified Out of Order Room in Tape Chart");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Navigate to TapeChart", testName, "Navigation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Navigate to TapeChart", testName, "Navigation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verify Out of Order On Inventory
		try {
			List<String> roomNoSize = new ArrayList<String>();
			roomNoSize.add(Utility.RoomNo);
			int size = roomNoSize.size();
			test_steps.add("<b>*****Start Verifying Out OF Order Room in Inventory*****</b>");
			nav.Inventory(driver);
			test_steps.add("Navigated to Inventory");
			nav.Inventory_Ratesgrid_Tab(driver, test_steps);
			nav.AvailabilityTab(driver);
			test_steps.add("Click on Availability Tab");
			List<String> dateList = date.subList(1, date.size());
			app_logs.info(dateList);
			List<String> dayList = day.subList(1, day.size());
			app_logs.info(dayList);
			overview.verifyOutOfOrder(driver, test_steps, roomClassName, "OOO", String.valueOf(size), dateList, dayList,
					Integer.parseInt(nights));
			test_steps.add("Successfully Verified Out of Order Room in Inventory");
			app_logs.info("Successfully Verified Out of Order Room in Inventory");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Out of Order Room In Inventory", testName, "RoomCreation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Out of Order Room In Inventory", testName, "RoomCreation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Delete Rate
		try {
			test_steps.add("<b>****Start Deleting Rates****</b>");
			nav.Rates2(driver);
			rate.deleteRates(driver, RateName);
			test_steps.add("All Rate Deleted Successfully With Name: <b>" + RateName + " </b>");
			app_logs.info("All Rate Deleted Successfully With Name: " + RateName);

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
			nav.Setup(driver);
			test_steps.add("Navigated to Setup");
			nav.RoomClass(driver);
			roomClass.SearchButtonClick(driver);
			test_steps.add("Click on Search Button");
			app_logs.info("Click on Search Button");
			roomClass.SearchRoomClass(driver, RoomClassName, test_steps);
			roomClass.deleteRoomClass(driver, RoomClassName);
			test_steps.add("All Room Class Deleted Successfully With Name: <b>" + RoomClassName + " </b>");
			app_logs.info("All Room Class Deleted Successfully With Name: " + RoomClassName);

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

		return Utility.getData("VerifyTapeChartRoomStatus", excel);

	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
