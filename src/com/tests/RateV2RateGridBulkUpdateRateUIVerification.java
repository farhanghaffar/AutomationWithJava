package com.innroad.inncenter.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.Distribution;
import com.innroad.inncenter.pageobjects.Login;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.RatesGrid;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class RateV2RateGridBulkUpdateRateUIVerification extends TestCore {
	private WebDriver driver = null;
	public static String test_name = "";
	public static String test_description = "";
	public static String test_category = "";
	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void rateV2RateGridBulkUpdateRateFunctionality(String bulkUpdateType, String checkInDate,
			String checkOutDate, String sunday, String monday, String tuesday, String wednesday, String thursday,
			String friday, String saturday, String isTotalOccupancyOn, String totalOccupancyType,
			String totalOccupancyValue, String ratePlansName, String roomClassName, String channel,
			String updateRatesType, String updateRateByRoomClass, String nightlyRate, String additionalAdults,
			String additionalChild, String rateChangeValue, String rateCurrencyType, String allSources,
			String allRoomClasses, String allActiveRatePlans, String allInActiveRatePlans)
			throws InterruptedException, IOException, ParseException {

		test_name = "RateV2RateGridBulkUpdateRatUIValidation_" + updateRatesType;
		test_description = "Rates V2 - Rates Grid - Bulk Update popup - Rates UI Validation<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682418' target='_blank'>"
				+ "Click here to open TestRail: C682418</a>";
		test_category = "RateV2_RatesGrid";
		String testName = test_name;

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Navigation navigation = new Navigation();
		RoomClass roomClass = new RoomClass();
		Distribution distribution = new Distribution();
		RatesGrid ratesGrid = new RatesGrid();
		List<String>[] roomClassList = new List[3];
		ArrayList<String> activeChannelsList = new ArrayList<String>();

		ArrayList<String> activeRoomClassesNames = new ArrayList<String>();
		ArrayList<String> activeRatePlanNames = new ArrayList<>();
		ArrayList<String> inactiveRatePlanNames = new ArrayList<>();
		checkInDate = Utility.getCurrentDate("MM/dd/YYYY");
		checkOutDate = Utility.GetNextDate(1, "MM/dd/YYYY");
		int days = ESTTimeZone.numberOfDaysBetweenDates(checkInDate, checkOutDate) + 1;
		String previousDate = Utility.GetNextDate(-1, "MM/d/YYYY");
		String Day = Utility.getCurrentDate("EEE,MM/dd/YYYY");
		Day = Day.split(",")[0];
		System.out.print("Day os:" + Day);

		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);
			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver = getDriver();
			Login login = new Login();
			login.login(driver, "https://www.app.qainnroad.com", "autorate", "autouser", "Auto@123");
			testSteps.add("Logged into the application");
			app_logs.info("Logged into the application");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
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

		// Get All RoomClasses/Distribution
		try {

			testSteps.add("==========GET ALL ACTIVE CHANNEL=========");
			app_logs.info("==========GET ALL ACTIVE CHANNEL==========");

			navigation.Inventory(driver);
			testSteps.add("Navigate Setup");
			navigation.Distribution(driver);
			testSteps.add("Navigate RoomClass");
			activeChannelsList = distribution.getAllActiveChannelDetails(driver);
			testSteps.add("==========GET ALL ROOMCLASSES=========");
			app_logs.info("==========GET ALL ROOMCLASSES==========");
			navigation.Setup(driver);
			testSteps.add("Navigate Setup");
			navigation.RoomClass(driver);
			testSteps.add("Navigate RoomClass");
			try {
				roomClass.searchButtonClick(driver);
				activeRoomClassesNames = roomClass.getAllRoomClasses(driver);
				testSteps.add("Active RoomClasses:" + activeRoomClassesNames);
				app_logs.info("Active RoomClasses:" + activeRoomClassesNames);
			} catch (Exception e) {
				roomClassList = roomClass.getAllActiveRoomClasses(driver);
				activeRoomClassesNames = (ArrayList<String>) roomClassList[0];

				testSteps.add("Active RoomClasses:" + activeRoomClassesNames);
				app_logs.info("Active RoomClasses:" + activeRoomClassesNames);
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to get Pre-Requisits", testName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to click Next button", testName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("==========PRE-VERIFICATION=========");
			app_logs.info("==========PRE-VERIFICATION==========");
			navigation.Inventory(driver);
			testSteps.add("Navigate Inventory");
			navigation.Rates_Grid(driver);
			app_logs.info("Navigate RatesGrid");
			testSteps.add("Navigate RatesGrid");
			getTestSteps.clear();
			getTestSteps = ratesGrid.clickSettingButton(driver);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = ratesGrid.changeStateShowAdditionalAdultAdditionalChilToggalSettingContainer(driver, true);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to get Pre-Requisits", testName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to click Next button", testName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Get Rate Plan Name and Color
		try {

			testSteps.add("==========GETTING RATE PLANS=========");
			app_logs.info("==========PGETTING RATE PLANS==========");
			ratesGrid.clickRatePlanArrow(driver, testSteps);
			activeRatePlanNames = ratesGrid.getActiveRatePlanNames(driver, "active");
			app_logs.info("rate plan size for active: " + activeRatePlanNames.size());
			testSteps.add("rate plan size for inactive: " + activeRatePlanNames.size());
			driver.navigate().refresh();
			ratesGrid.clickRatePlanArrow(driver, testSteps);
			inactiveRatePlanNames = ratesGrid.getInActiveRatePlanNames(driver, "inactive");
			app_logs.info("rate plan size for inactive: " + inactiveRatePlanNames.size());
			testSteps.add("rate plan size for inactive: " + inactiveRatePlanNames.size());
		} catch (

		Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}
		// Rate Update Page Validations

		try {
			app_logs.info("***********RATE UPDATE PAGE VALIDATIONS**********");
			testSteps.add("***********RATE UPDATE PAGE VALIDATIONS**********");

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickOnBulkUpdate(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.selectBulkUpdateOption(driver, bulkUpdateType);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rate popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rate popup", test_name, "BulkUpdate", driver);
			}
		}

		try {
			app_logs.info("==========VERIFYING CLOSE ICON==========");
			testSteps.add("==========VERIFYING CLOSE ICON==========");

			app_logs.info("Expected : " + "\\×\\");
			testSteps.add("Expected : " + "\\×\\");

			String closeText = ratesGrid.getCloseIconText(driver);
			app_logs.info("Found : " + "\\" + closeText + "\\");
			testSteps.add("Found : " + "\\" + closeText + "\\");

			Assert.assertEquals("\\" + closeText + "\\", "\\×\\", "Failed to match close text");

			testSteps.add("Verified close sign");
			app_logs.info("Verified close sign");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify bulk update close icon", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify bulk update close icon", test_name, "BulkUpdate", driver);
			}
		}
		try {

			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePoppupHeading(driver, bulkUpdateType);
			testSteps.addAll(getTestSteps);

			String exectedPopupText = "This one-time update will adjust rates for all days that match your criteria.\n"
					+ "This update will not be recurring.";
			testSteps.add("Expected Bulk Update Popup Text : " + exectedPopupText);
			app_logs.info("Expected Bulk Update Popup Text : " + exectedPopupText);
			String updatePopupText = ratesGrid.bulkUpdatePoppupText(driver);
			testSteps.add("Found : " + updatePopupText);
			app_logs.info("Found : " + updatePopupText);
			Assert.assertEquals(updatePopupText, exectedPopupText, "Failed to verify text");
			testSteps.add("Verified bulk update popup text");
			app_logs.info("Verified bulk update popup text");
			// Update Button Should be Disabled At Start
			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePopupUpdateButtonEnable(driver);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify bulk update heading", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify bulk update heading", test_name, "BulkUpdate", driver);
			}

		}
		// Date Validation Section
		try {
			app_logs.info("***********DATE VALIDATIONS**********");
			testSteps.add("***********DATE VALIDATIONS**********");

			app_logs.info("==========VERIFYING START DATE==========");
			testSteps.add("==========VERIFYING START DATE==========");

			app_logs.info("Expected Date : " + checkInDate);
			testSteps.add("Expected Date : " + checkInDate);

			String getStartDate = ratesGrid.getStartDate(driver);
			app_logs.info("Found : " + getStartDate);
			testSteps.add("Found : " + getStartDate);

			Assert.assertEquals(getStartDate, checkInDate, "Failed to match start date");
			testSteps.add("Verified start date");
			app_logs.info("Verified start date");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {
			app_logs.info("==========VERIFYING START DATE CALENDAR==========");
			testSteps.add("==========VERIFYING START DATE CALENDAR==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyStartDateCalendar(driver, checkInDate);
			testSteps.addAll(getTestSteps);

			testSteps.add("Verified start date calendar");
			app_logs.info("Verified start date calendar");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {

			app_logs.info("==========VERIFYING END DATE==========");
			testSteps.add("==========VERIFYING END DATE==========");

			app_logs.info("Expected Date : " + checkInDate);
			testSteps.add("Expected Date : " + checkInDate);

			String getEndDate = ratesGrid.getEndDate(driver);
			app_logs.info("Found : " + getEndDate);
			testSteps.add("Found : " + getEndDate);

			Assert.assertEquals(getEndDate, checkInDate, "Failed to match end date");
			testSteps.add("Verified end date");
			app_logs.info("Verified end date");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {

			app_logs.info("==========VERIFYING END DATE CALENDAR==========");
			testSteps.add("==========VERIFYING END DATE CALENDAR==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyEndDateCalendar(driver, checkInDate);
			testSteps.addAll(getTestSteps);

			testSteps.add("Verified end date calendar");
			app_logs.info("Verified end date calendar");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}
		try {

			testSteps.add("==========SEELCT START DATE==========");
			app_logs.info("==========SEELCT START DATE==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.previousStartEndDateValidation(driver, previousDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.startDate(driver, checkInDate);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to select start", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to select start", test_name, "BulkUpdate", driver);
			}
		}

		try {
			testSteps.add("==========SEELCT END DATE==========");
			app_logs.info("==========SEELCT END DATE==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.endDate(driver, checkOutDate);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to select end date", test_name, "RatesPopup", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to select end date", test_name, "RatesPopup", driver);
			}
		}
		// Days Validation Section

		try {

			app_logs.info("***********DAYS VALIDATIONS**********");
			testSteps.add("***********DAYS VALIDATIONS**********");

			app_logs.info("==========VERIFYING DAYS CHEKCBOXES DISPLAYED==========");
			testSteps.add("==========VERIFYING DAYS CHEKCBOXES DISPLAYED==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyDaysCheckbox(driver, "Sun");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyDaysCheckbox(driver, "Mon");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyDaysCheckbox(driver, "Tue");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyDaysCheckbox(driver, "Wed");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyDaysCheckbox(driver, "Thu");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyDaysCheckbox(driver, "Fri");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyDaysCheckbox(driver, "Sat");
			testSteps.addAll(getTestSteps);

			testSteps.add("Verified days checkboxes");
			app_logs.info("Verified days checkboxes");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}
		boolean isCurrentDaySelected = false;
		try {
			app_logs.info("==========CHECKING/UNCHECKING DAYS==========");
			testSteps.add("==========CHECKING/UNCHECKING DAYS==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Sun", sunday);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Mon", monday);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Tue", tuesday);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Wed", wednesday);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Thu", thursday);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Fri", friday);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Sat", saturday);
			testSteps.addAll(getTestSteps);
			isCurrentDaySelected = ratesGrid.getDayState(driver, Day);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to check/uncheck days", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to check/uncheck days", test_name, "BulkUpdate", driver);
			}
		}
		// Occupancy Validation Sections

		try {
			app_logs.info("***********OCCUPANCY VALIDATIONS**********");
			testSteps.add("***********OCCUPANCY VALIDATIONS**********");

			app_logs.info("==========VERIFYING TOTAL OCCUPANCY TEXT==========");
			testSteps.add("==========VERIFYING TOTAL OCCUPANCY TEXT==========");

			String expectedOccupanyText = "For days that total occupancy is";
			testSteps.add("Expected : " + expectedOccupanyText);
			app_logs.info("Expected : " + expectedOccupanyText);

			String totalOccupancyText = ratesGrid.totalOccupancyTextDisplay(driver);
			testSteps.add("Found : " + totalOccupancyText);
			app_logs.info("Found : " + totalOccupancyText);

			Assert.assertEquals(totalOccupancyText, expectedOccupanyText, "Failed to match total occupancy text");
			testSteps.add("Verified total occupancy text");
			app_logs.info("Verified total occupancy text");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {

			app_logs.info("==========VERIFYING OCCUPANCY CLAUSE TEXT BY HOVERING OVER CLAUSE ICON==========");
			testSteps.add("==========VERIFYING OCCUPANCY CLAUSE TEXT BY HOVERING OVER CLAUSE ICON==========");

			String expectedClause = "This clause only makes updates according to the total occupancy setting as of the time of the update. It will NOT make updates in the future when total occupancy reaches what is set.";
			testSteps.add("Expected : " + expectedClause);
			app_logs.info("Expected : " + expectedClause);

			String getClause = ratesGrid.verificationTotalOccupanyIcon(driver);
			testSteps.add("Found : " + getClause);
			app_logs.info("Found : " + getClause);

			// Assert.assertEquals(getClause, expectedClause, "Failed to match
			// occupancy clause text");
			testSteps.add("Verified occupancy clause text by hovering over cluase icon");
			app_logs.info("Verified occupancy clause text by hovering over cluase icon");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {

			app_logs.info("==========VERIFYING TOTAL OCCUPANCY DISABLED BY DAFAULT==========");
			testSteps.add("==========VERIFYING TOTAL OCCUPANCY DISABLED BY DAFAULT==========");

			Boolean isOccupancyTypeEnable = ratesGrid.verifyTotalOccupancyType(driver);
			app_logs.info("isOccupancyTypeEnable : " + isOccupancyTypeEnable);

			Boolean isOccupancyValueEnable = ratesGrid.verifyTotalOccupanyValue(driver);
			app_logs.info("isOccupancyValueEnable : " + isOccupancyValueEnable);

			if (!isOccupancyValueEnable && !isOccupancyTypeEnable) {
				testSteps.add("Verified total occupancy disabled by default");
				app_logs.info("Verified total occupancy disabled by default");
			} else {
				Assert.assertTrue(false, "Failed : Found total occupancy enabled by default");

			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {

			app_logs.info("==========VERIFYING TOTAL OCCUPANCY TYPE DISABLED BY DAFAULT==========");
			testSteps.add("==========VERIFYING TOTAL OCCUPANCY TYPE DISABLED BY DAFAULT==========");

			Boolean isOccupancyTypeEnable = ratesGrid.verifyTotalOccupancyType(driver);
			app_logs.info("isOccupancyTypeEnable : " + isOccupancyTypeEnable);

			if (!isOccupancyTypeEnable) {
				testSteps.add("Verified total occupancy type disabled by default");
				app_logs.info("Verified total occupancy type disabled by default");
			} else {
				Assert.assertTrue(false, "Failed : Found total occupancy type enabled by default");
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {

			app_logs.info("==========VERIFYING TOTAL OCCUPANCY VALUE DISABLED BY DAFAULT==========");
			testSteps.add("==========VERIFYING TOTAL OCCUPANCY VALUE DISABLED BY DAFAULT==========");

			Boolean isOccupancyValueEnable = ratesGrid.verifyTotalOccupanyValue(driver);
			app_logs.info("isOccupancyValueEnable : " + isOccupancyValueEnable);

			if (!isOccupancyValueEnable) {
				testSteps.add("Verified total occupancy value disabled by default");
				app_logs.info("Verified total occupancy value disabled by default");
			} else {
				Assert.assertTrue(false, "Failed : Found total occupancy value enabled by default");
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {
			app_logs.info("==========VERIFYING TOTAL OCCUPANCY ENABLED AFTRE CLICKING OCCUPANCY BUTTON==========");
			testSteps.add("==========VERIFYING TOTAL OCCUPANCY ENABLED AFTRE CLICKING OCCUPANCY BUTTON==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickTotalOccupancy(driver, isTotalOccupancyOn);
			testSteps.addAll(getTestSteps);

			Boolean isOccupancyTypeEnable = ratesGrid.verifyTotalOccupancyType(driver);
			app_logs.info("isOccupancyTypeEnable : " + isOccupancyTypeEnable);

			Boolean isOccupancyValueEnable = ratesGrid.verifyTotalOccupanyValue(driver);
			app_logs.info("isOccupancyValueEnable : " + isOccupancyValueEnable);

			if (isOccupancyValueEnable && isOccupancyTypeEnable) {
				testSteps.add("Verified total occupancy enabled");
				app_logs.info("Verified total occupancy enabled");
			} else {
				Assert.assertTrue(false, "Failed : Found total occupancy disabled");
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {

			app_logs.info("==========VERIFYING TOTAL OCCUPANCY TYPE ENABLED==========");
			testSteps.add("==========VERIFYING TOTAL OCCUPANCY TYPE ENABLED==========");

			Boolean isOccupancyTypeEnable = ratesGrid.verifyTotalOccupancyType(driver);
			app_logs.info("isOccupancyTypeEnable : " + isOccupancyTypeEnable);

			if (isOccupancyTypeEnable) {
				testSteps.add("Verified total occupancy type enabled");
				app_logs.info("Verified total occupancy type enabled");
			} else {
				Assert.assertTrue(false, "Failed : Found total occupancy type enabled");
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {

			app_logs.info("==========VERIFYING TOTAL OCCUPANCY TYPE DROPDOWN VALUES==========");
			testSteps.add("==========VERIFYING TOTAL OCCUPANCY TYPE DROPDOWN VALUES==========");

			String[] occupancyType = totalOccupancyType.split("\\|");
			for (String str : occupancyType) {
				getTestSteps.clear();
				getTestSteps = ratesGrid.verifyTotalOccupancyTypeValues(driver, str);
				testSteps.addAll(getTestSteps);
			}

			app_logs.info("Verified total occupancy type values");
			testSteps.add("Verified total occupancy type values");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {

			app_logs.info("==========VERIFYING TOTAL OCCUPANCY VALUE ENABLED==========");
			testSteps.add("==========VERIFYING TOTAL OCCUPANCY VALUE ENABLED==========");

			Boolean isOccupancyValueEnable = ratesGrid.verifyTotalOccupanyValue(driver);
			app_logs.info("isOccupancyValueEnable : " + isOccupancyValueEnable);

			if (isOccupancyValueEnable) {
				testSteps.add("Verified total occupancy value enabled");
				app_logs.info("Verified total occupancy value enabled");
			} else {
				testSteps.add("Verified total occupancy value disabled");
				app_logs.info("Verified total occupancy value disabled");
				Assert.assertTrue(false, "Failed : Found total occupancy value disabled");
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickTotalOccupancy(driver, isTotalOccupancyOn);
			testSteps.addAll(getTestSteps);

			if (isTotalOccupancyOn.equalsIgnoreCase("Yes")) {

				getTestSteps.clear();
				getTestSteps = ratesGrid.selectTotalOccupancyType(driver, totalOccupancyType);
				testSteps.addAll(getTestSteps);
				getTestSteps.clear();
				getTestSteps = ratesGrid.verifyOccupancyValueInputField(driver);
				testSteps.addAll(getTestSteps);
				getTestSteps.clear();
				getTestSteps = ratesGrid.verifyPercentageSignOccupancyValueField(driver);
				testSteps.addAll(getTestSteps);

				getTestSteps.clear();
				getTestSteps = ratesGrid.enterOccupancyValue(driver, totalOccupancyValue);
				testSteps.addAll(getTestSteps);
			}

		} catch (

		Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to select total occupancy type", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to select total occupancy type", test_name, "BulkUpdate", driver);
			}
		}

		try {
			app_logs.info("***********RATE PLAN VALIDATIONS**********");
			testSteps.add("***********RATE PLAN VALIDATIONS**********");

			app_logs.info("==========VERIFYING RATEPLAN DROPDOWN IS DISPLAYING==========");
			testSteps.add("==========VERIFYING RATEPLAN DROPDOWN IS DISPLAYING==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyBulkUpdateDropDowns(driver, "Rate Plan");
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {
			app_logs.info("==========VERIFYING RATEPLAN PLACEHOLDER TEXT==========");
			testSteps.add("==========VERIFYING RATEPLAN PLACEHOLDER TEXT==========");

			String expectedPlaceHolder = "Choose existing rate plan(s)";
			app_logs.info("Expected : " + expectedPlaceHolder);
			testSteps.add("Expected : " + expectedPlaceHolder);

			String placeHolderText = ratesGrid.getDropDownsPlaceHolder(driver, "Rate Plan");
			app_logs.info("Found : " + placeHolderText);
			testSteps.add("Found : " + placeHolderText);

			Assert.assertEquals(placeHolderText, expectedPlaceHolder,
					"Failed to match pplace holder text for Rate Plan");
			app_logs.info("Verified rateplan place holder");
			testSteps.add("Verified rateplan place holder");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}
		try {

			app_logs.info("==========SELECTING ALL ACTIVE RATES PLAN==========");
			testSteps.add("==========SELECTING ALL ACTIVE RATES PLAN==========");

			int activeRatePlansSize;
			getTestSteps.clear();
			getTestSteps = ratesGrid.selectItemsFromDropDowns(driver, "Rate Plan", allActiveRatePlans);
			testSteps.addAll(getTestSteps);

			String getAllRatePlan = ratesGrid.getAllRoomClassText(driver, "Rate Plan", allActiveRatePlans);
			getAllRatePlan = getAllRatePlan.replace("All Active Rate Plans (", "");
			getAllRatePlan = getAllRatePlan.replace(")", "");
			activeRatePlansSize = Integer.parseInt(getAllRatePlan);

			testSteps.add("Expected active plan size: " + activeRatePlanNames.size());
			app_logs.info("Found : " + activeRatePlansSize);
			testSteps.add("Found : " + activeRatePlansSize);
			assertEquals(activeRatePlansSize, activeRatePlanNames.size(),
					"Failed: Total active rate plan size is mismatching");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify total active rate plan", test_name,
						"VerifyTotalActiveRatePlan", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify total active rate plan", test_name,
						"VerifyTotalActiveRatePlan", driver);
			}
		}

		try {

			app_logs.info("==========SELECTING ALL InACTIVE RATES PLAN==========");
			testSteps.add("==========SELECTING ALL InACTIVE RATES PLAN==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.selectItemsFromDropDowns(driver, "Rate Plan", allInActiveRatePlans);
			testSteps.addAll(getTestSteps);

			String getAllRatePlan = ratesGrid.getAllRoomClassText(driver, "Rate Plan", allInActiveRatePlans);
			getAllRatePlan = getAllRatePlan.replace("All Inactive Rate Plans (", "");
			app_logs.info("getAllRatePlan : " + getAllRatePlan);
			getAllRatePlan = getAllRatePlan.replace(")", "");
			app_logs.info("getAllRatePlan : " + getAllRatePlan);

			int inActiveRatePlansSize = Integer.parseInt(getAllRatePlan);
			testSteps.add("Expected inactive plan size: " + inactiveRatePlanNames.size());
			app_logs.info("Found : " + inActiveRatePlansSize);
			testSteps.add("Found : " + inActiveRatePlansSize);
			assertEquals(inActiveRatePlansSize, inactiveRatePlanNames.size(),
					"Failed: Total inactive rate plan size is mismatching");

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyColorCodeofRatePlans(driver, allInActiveRatePlans);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify inactive rate plan size", test_name, "VerifyInactiveRateplan",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify inactive rate plan size", test_name, "VerifyInactiveRateplan",
						driver);
			}
		}

		try {

			app_logs.info(
					"==========VERIFYING NO RATE PLAN OPTIONS ARE SHOWING IN RATE PLAN DROP DWON AFTER SELECTED ALL RATE PLAN==========");
			testSteps.add(
					"==========VERIFYING NO RATE PLAN OPTIONS ARE SHOWING IN RATE PLAN DROP DWON AFTER SELECTED ALL RATE PLAN==========");

			int getRatePlanDropDownOptionSize = ratesGrid.getDropDownOptionsSize(driver, "Rate Plan",
					activeRatePlanNames.get(0));

			app_logs.info("Get rate plan drop down size after selecetd all active rate plan: "
					+ getRatePlanDropDownOptionSize);
			testSteps.add("Get rate plan drop down size after selecetd all active rate plan: "
					+ getRatePlanDropDownOptionSize);

			assertEquals(getRatePlanDropDownOptionSize, 0,
					"Failed: Rate plan drop down size is mismatching after selected all active rate plans");
			testSteps.add("Verified not option is showing in rate plan dropdown after selecting all active rate plans");

			getTestSteps.clear();
			getTestSteps = ratesGrid.removeSelectedOptionFromField(driver, "Rate Plan", allActiveRatePlans);
			testSteps.addAll(getTestSteps);

			getRatePlanDropDownOptionSize = ratesGrid.getDropDownOptionsSize(driver, "Rate Plan",
					inactiveRatePlanNames.get(0));
			app_logs.info("Get rate plan drop down size after seleceted all inactive rate plan: "
					+ getRatePlanDropDownOptionSize);
			testSteps.add("Get rate plan drop down size after selecetd all active and inative rate plan: "
					+ getRatePlanDropDownOptionSize);
			assertEquals(getRatePlanDropDownOptionSize, 0,
					"Failed: Rate plan drop down size is mismatching after selected all inctive rate plan");
			testSteps.add(
					"Verified not option is showing in rate plan dropdown after selecting all inactive rate plans");

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyDropDownDisableOnAllListSelection(driver, "Rate Plan", allInActiveRatePlans);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = ratesGrid.removeSelectedOptionFromField(driver, "Rate Plan", allInActiveRatePlans);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e,
						"Failed to verify drop down option after selected all active and inactive rate plans",
						test_name, "VerifySelectedOption", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e,
						"Failed to verify drop down option after selected all active and inactive rate plans",
						test_name, "BulkUpdate", driver);
			}
		}

		try {
			app_logs.info("==========SELECTING RATE PLAN==========");
			testSteps.add("==========SELECTING RATE PLAN==========");

			String[] ratePlanArray = ratePlansName.split("\\|");
			for (String str : ratePlanArray) {

				getTestSteps.clear();
				getTestSteps = ratesGrid.selectItemsFromDropDowns(driver, "Rate Plan", str);
				testSteps.addAll(getTestSteps);

			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to select rate plan", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to select rate plan", test_name, "BulkUpdate", driver);
			}
		}

		// Room Class Validation Steps
		try {

			app_logs.info("***********ROOMCLASSES VALIDATIONS**********");
			testSteps.add("***********ROOMCLASSES VALIDATIONS**********");

			app_logs.info("==========VERIFYING ROOMCLASSES DROPDOWN IS DISPLAYING==========");
			testSteps.add("==========VERIFYING ROOMCLASSES DROPDOWN IS DISPLAYING==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyBulkUpdateDropDowns(driver, "Room class");
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {
			app_logs.info("==========VERIFYING ROOMCLASSES PLACEHOLDER TEXT==========");
			testSteps.add("==========VERIFYING ROOMCLASSES PLACEHOLDER TEXT==========");

			String expectedPlaceHolder = "Select room class(es)";
			app_logs.info("Expected : " + expectedPlaceHolder);
			testSteps.add("Expected : " + expectedPlaceHolder);

			String placeHolderText = ratesGrid.getDropDownsPlaceHolder(driver, "Room class");
			app_logs.info("Found : " + placeHolderText);
			testSteps.add("Found : " + placeHolderText);

			Assert.assertEquals(placeHolderText, expectedPlaceHolder,
					"Failed to match place holder text for Room class");
			app_logs.info("Verified room class place holder");
			testSteps.add("Verified room class place holder");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {
			app_logs.info("==========VERIFYING ACTIVE ROOMCLASSESS ARE SHOWING IN BULK UPDATE POPUP==========");
			testSteps.add("==========VERIFYING ACTIVE ROOMCLASSESS ARE SHOWING IN BULK UPDATE POPUP==========");

			app_logs.info("GETTING ROOMCLASSES");
			testSteps.add("GETTING ROOMCLASSES");

			ArrayList<String> getDropDownItems = new ArrayList<>();
			getDropDownItems = ratesGrid.getBulkUpdateDropDownsList(driver, "Room class");
			testSteps.add("Get List of All Room classes: <b>" + getDropDownItems + "</b>");

			app_logs.info("getDropDownItems : " + getDropDownItems.size());

			getDropDownItems.remove(0);
			Boolean isRoomClassPresent = ratesGrid.compareLists(activeRoomClassesNames, getDropDownItems);
			app_logs.info("isRoomClassPresent : " + isRoomClassPresent);
			Assert.assertTrue(isRoomClassPresent, "Failed to compare roomclasses");

			app_logs.info("Verified active room classes are showing in bulk update popup");
			testSteps.add("Verified active room classes are showing in bulk update popup");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {
			if (!allRoomClasses.isEmpty()) {
				app_logs.info("==========VERIFYING  ALL ROOM CLASS TEXT WITH LIST SIZE==========");
				testSteps.add("==========VERIFYING  ALL ROOM CLASS TEXT WITH LIST SIZE==========");

				getTestSteps.clear();
				getTestSteps = ratesGrid.selectRoomClass(driver, allRoomClasses);
				testSteps.addAll(getTestSteps);

				app_logs.info("Expected : " + activeRoomClassesNames.size());
				testSteps.add("Expected : " + activeRoomClassesNames.size());

				String getAllRoomClass = ratesGrid.getAllRoomClassText(driver, "Room class", allRoomClasses);
				getAllRoomClass = getAllRoomClass.replace("All room classes (", "");
				app_logs.info("getAllRoomClass : " + getAllRoomClass);
				getAllRoomClass = getAllRoomClass.replace(")", "");
				app_logs.info("getAllRoomClass : " + getAllRoomClass);
				app_logs.info("Found : " + getAllRoomClass.trim());
				testSteps.add("Found : " + getAllRoomClass);

				Assert.assertEquals(getAllRoomClass.trim(), String.valueOf(activeRoomClassesNames.size()),
						"Failed to match dropdown list size");
				app_logs.info("Verified room class list size");
				testSteps.add("Verified room class list size");

				app_logs.info("==========VERIFYING NO ROOMCLASSESS ARE SHOWING IN BULK UPDATE POPUP==========");
				testSteps.add("==========VERIFYING NO ROOMCLASSESS ARE SHOWING IN BULK UPDATE POPUP==========");

				int getRoomClassDropDownOptionSize = ratesGrid.getRoomClassListSize(driver, "Room class",
						allRoomClasses);

				app_logs.info("Get room class drop down size after selecetd all room class: "
						+ getRoomClassDropDownOptionSize);
				testSteps.add("Get room class drop down size after selecetd all room class: "
						+ getRoomClassDropDownOptionSize);
				assertEquals(getRoomClassDropDownOptionSize, 0,
						"Failed: Room classes drop down size is mismatching after selected all active room classes");
				testSteps.add("Verified not option is showing in room classes dropdown");
				getTestSteps.clear();
				getTestSteps = ratesGrid.verifyDropDownDisableOnAllListSelection(driver, "Room class", allSources);
				testSteps.addAll(getTestSteps);

				getTestSteps.clear();
				getTestSteps = ratesGrid.removeAllRoomClass(driver, "Room class", allRoomClasses);
				testSteps.addAll(getTestSteps);

			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {

			app_logs.info("Verified update button is disbaled before selecting roomclasses");
			testSteps.add("Verified update button is disbaled before selecting roomclasses");
			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePopupUpdateButtonEnable(driver);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========VERIFYING ACTIVE ROOMCLASSESS ARE SHOWING IN BULK UPDATE POPUP==========");
			testSteps.add("==========VERIFYING ACTIVE ROOMCLASSESS ARE SHOWING IN BULK UPDATE POPUP==========");

			app_logs.info("GETTING ROOMCLASSES");
			testSteps.add("GETTING ROOMCLASSES");

			ArrayList<String> getDropDownItems = new ArrayList<>();
			getDropDownItems = ratesGrid.getBulkUpdateDropDownsList(driver, "Room class");
			testSteps.add("Get List of All Room classes: <b>" + getDropDownItems + "</b>");

			app_logs.info("getDropDownItems : " + getDropDownItems.size());
			app_logs.info("activeroomclassed : " + activeRoomClassesNames.size());
			app_logs.info("activeroomclassed : " + activeRoomClassesNames);

			getDropDownItems.remove(0);
			Assert.assertEquals(getDropDownItems, activeRoomClassesNames, "Failed to compare roomclasses");

			app_logs.info("Verified active room classes are showing in bulk update popup");
			testSteps.add("Verified active room classes are showing in bulk update popup");

			String[] roomClassArray = roomClassName.split("\\|");
			for (String str : roomClassArray) {
				getTestSteps.clear();
				getTestSteps = ratesGrid.selectRoomClass(driver, str);
				testSteps.addAll(getTestSteps);
				testSteps.addAll(getTestSteps);
			}

			app_logs.info("==========VERIFYING UPDATE BUTTON IS DISABLED BEFORE SELECTING  ROOMCLASS==========");
			testSteps.add("==========VERIFYING UPDATE BUTTON IS DISABLED BEFORE SELECTING  ROOMCLASS==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePopupUpdateButtonEnable(driver);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to click cancel button", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to click cancel button", test_name, "BulkUpdate", driver);
			}
		}
		// Sources DropDown Validation

		try {

			app_logs.info("***********SOURCES VALIDATIONS**********");
			testSteps.add("***********SOURCES VALIDATIONS**********");

			app_logs.info("==========VERIFYING SOURCE DROPDOWN IS DISPLAYING==========");
			testSteps.add("==========VERIFYING SOURCE DROPDOWN IS DISPLAYING==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyBulkUpdateDropDowns(driver, "Source");
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {
			app_logs.info("==========VERIFYING SOURCES PLACEHOLDER TEXT==========");
			testSteps.add("==========VERIFYING SOURCES PLACEHOLDER TEXT==========");

			String expectedPlaceHolder = "Select source(s)";
			app_logs.info("Expected : " + expectedPlaceHolder);
			testSteps.add("Expected : " + expectedPlaceHolder);

			String placeHolderText = ratesGrid.getDropDownsPlaceHolder(driver, "Source");
			app_logs.info("Found : " + placeHolderText);
			testSteps.add("Found : " + placeHolderText);

			Assert.assertEquals(placeHolderText, expectedPlaceHolder, "Failed to match place holder text for Source");
			app_logs.info("Verified source place holder");
			testSteps.add("Verified source place holder");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {
			app_logs.info("==========VERIFYING SOURCES ARE SHOWING IN BULK UPDATE POPUP==========");
			testSteps.add("==========VERIFYING SOURCES ARE SHOWING IN BULK UPDATE POPUP==========");

			app_logs.info("GETTING SOURCES");
			testSteps.add("GETTING SOURCES");

			ArrayList<String> getDropDownItems = new ArrayList<>();
			getDropDownItems = ratesGrid.getBulkUpdateDropDownsList(driver, "Source");
			testSteps.add("Get List of All Sources: <b>" + getDropDownItems + "</b>");
			Boolean isSourcePresent = false;
			for (int i = 0; i < activeChannelsList.size(); i++) {
				if (getDropDownItems.contains(activeChannelsList.get(i))) {
					isSourcePresent = true;
				} else {
					isSourcePresent = false;
				}
			}
			app_logs.info(isSourcePresent);
			Assert.assertTrue(isSourcePresent, "Failed to match sources");
			app_logs.info("Verified sources are showing in bulk update popup");
			testSteps.add("Verified sources are showing in bulk update popup");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {
			app_logs.info("==========VERIFYING  ALL SOURCE TEXT WITH LIST SIZE==========");
			testSteps.add("==========VERIFYING  ALL SOURCE TEXT WITH LIST SIZE==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.selectItemsFromDropDowns(driver, "Source", allSources);
			testSteps.addAll(getTestSteps);

			app_logs.info("Expected : " + activeChannelsList.size());
			testSteps.add("Expected : " + activeChannelsList.size());

			String getAllSource = ratesGrid.getAllRoomClassText(driver, "Source", allSources);
			getAllSource = getAllSource.replace("All sources (", "");
			app_logs.info("getAllSource : " + getAllSource);
			getAllSource = getAllSource.replace(")", "");
			app_logs.info("getAllSource : " + getAllSource);
			app_logs.info("Found : " + getAllSource.trim());
			testSteps.add("Found : " + getAllSource);

			Assert.assertEquals(getAllSource.trim(), String.valueOf(activeChannelsList.size()),
					"Failed to match drop down list size");
			app_logs.info("Verified source list size");
			testSteps.add("Verified source list size");

			app_logs.info("==========VERIFYING NO SOURCE ARE SHOWING IN BULK UPDATE POPUP==========");
			testSteps.add("==========VERIFYING NO SOURCE ARE SHOWING IN BULK UPDATE POPUP==========");

			int getSourceDropDownOptionSize = ratesGrid.getRoomClassListSize(driver, "Source", allSources);

			app_logs.info("Get sources drop down size after selecetd all source: " + getSourceDropDownOptionSize);
			testSteps.add("Get sources drop down size after selecetd all source: " + getSourceDropDownOptionSize);
			assertEquals(getSourceDropDownOptionSize, 0,
					"Failed: sources drop down size is mismatching after selected all active sources");
			testSteps.add("Verified not option is showing in sources dropdown");

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyDropDownDisableOnAllListSelection(driver, "Source", allSources);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.removeAllRoomClass(driver, "Source", allSources);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {

			app_logs.info("==========VERIFYING ACTIVE SOURCES ARE SHOWING IN BULK UPDATE POPUP==========");
			testSteps.add("==========VERIFYING ACTIVE SOURCES ARE SHOWING IN BULK UPDATE POPUP==========");

			app_logs.info("GETTING SOURCES");
			testSteps.add("GETTING SOURCES");

			ArrayList<String> getDropDownItems = new ArrayList<>();
			getDropDownItems = ratesGrid.getBulkUpdateDropDownsList(driver, "Source");
			testSteps.add("Get List of All Sources: <b>" + getDropDownItems + "</b>");

			app_logs.info("getDropDownItems : " + getDropDownItems.size());

			getDropDownItems.remove(0);

			Assert.assertEquals(getDropDownItems, activeChannelsList, "Failed to compare Sources");

			app_logs.info("Verified active sources are showing in bulk update popup");
			testSteps.add("Verified active sources are showing in bulk update popup");

			app_logs.info("==========SELECTING SOURCE==========");
			testSteps.add("==========SELECTING SOURCE==========");

			String[] channelArray = channel.split("\\|");
			for (String str : channelArray) {
				getTestSteps.clear();
				getTestSteps = ratesGrid.selectItemsFromDropDowns(driver, "Source", str);
				testSteps.addAll(getTestSteps);
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to select source", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to select source", test_name, "BulkUpdate", driver);
			}
		}
		// Update Rate Plan Validations

		try {
			app_logs.info("***********UPDATE RATE PLAN VALIDATIONS**********");
			testSteps.add("***********UPDATE RATE PLAN VALIDATIONS**********");

			app_logs.info("==========UPDATE RATES==========");
			testSteps.add("==========UPDATE RATES==========");

			// Checks Rate Update Type
			if (updateRatesType.equalsIgnoreCase("EnterNewRate")) {
				getTestSteps.clear();
				getTestSteps = ratesGrid.selectBulkUpdateRatesOption(driver, 0);
				testSteps.addAll(getTestSteps);

				getTestSteps.clear();
				getTestSteps = ratesGrid.updateRoomsByRoomClassToggle(driver,
						Boolean.parseBoolean(updateRateByRoomClass));
				testSteps.addAll(getTestSteps);

				getTestSteps.clear();
				getTestSteps = ratesGrid.verifyNoRoomsSelectedInUpdateRates(driver, roomClassName);
				testSteps.addAll(getTestSteps);
				String[] roomClassArray = roomClassName.split("\\|");
				for (String str : roomClassArray) {
					getTestSteps.clear();
					getTestSteps = ratesGrid.selectRoomClass(driver, str);
					testSteps.addAll(getTestSteps);
					testSteps.addAll(getTestSteps);
				}

				String[] nightlyRateArray = nightlyRate.split("\\|");
				int nightArrayLength = 1;
				if (updateRateByRoomClass.equalsIgnoreCase("True")) {
					nightArrayLength = nightlyRateArray.length;
				}
				String[] additionalAdultArray = additionalAdults.split("\\|");
				String[] additionalChildArray = additionalAdults.split("\\|");
				// Check Length of NightlyRate List and Input Values
				for (int i = 0; i < nightArrayLength; i++) {

					getTestSteps.clear();
					getTestSteps = ratesGrid.updateNightlyRate(driver, i, nightlyRateArray[i]);
					testSteps.addAll(getTestSteps);

					getTestSteps.clear();
					getTestSteps = ratesGrid.updateAdditionalAdultRate(driver, i, additionalAdultArray[i]);
					testSteps.addAll(getTestSteps);

					getTestSteps.clear();
					getTestSteps = ratesGrid.updateAdditionalChildRate(driver, i, additionalChildArray[i]);
					testSteps.addAll(getTestSteps);

				}

			} else if (updateRatesType.equalsIgnoreCase("Increase") || updateRatesType.equalsIgnoreCase("Decrease")) {
				getTestSteps.clear();
				getTestSteps = ratesGrid.selectBulkUpdateRatesOption(driver, 1);
				testSteps.addAll(getTestSteps);

				getTestSteps.clear();
				getTestSteps = ratesGrid.selectRateIncreaseDecreaseOption(driver, updateRatesType);
				testSteps.addAll(getTestSteps);

				getTestSteps.clear();
				getTestSteps = ratesGrid.enterRateValueForUpdateRate(driver, rateChangeValue);
				testSteps.addAll(getTestSteps);

				getTestSteps.clear();
				getTestSteps = ratesGrid.selectRateCurrencyOption(driver, rateCurrencyType);
				testSteps.addAll(getTestSteps);

			}
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to click cancel button", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to click cancel button", test_name, "BulkUpdate", driver);
			}
		}

		try {

			app_logs.info("==========VERIFYING TOTAL NUMBER OF DAYS==========");
			testSteps.add("==========VERIFYING TOTAL NUMBER OF DAYS==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickBulkUpdatePopupUpdateButton(driver);
			testSteps.addAll(getTestSteps);
			if (!isCurrentDaySelected) {
				String expectedWarning = "No days match the parameters you set. Please adjust the parameters to include at least 1 day";
				testSteps.add("Expected : " + expectedWarning);
				app_logs.info("Expected : " + expectedWarning);

				String getWarning = ratesGrid.verifyDaysInRangeOfDate(driver);
				testSteps.add("Found : " + getWarning);
				app_logs.info("Found : " + getWarning);

				Assert.assertEquals(getWarning, expectedWarning, "Failed to match Days within Date Range Warning");
				testSteps.add("Verified Days within Date Range Warning Popup");
				app_logs.info("Verified Days within Date Range Warning Popup");

				getTestSteps.clear();
				getTestSteps = ratesGrid.daysInRangeOfDateOKButton(driver);
				testSteps.addAll(getTestSteps);

				// If Current Day is not checked
				getTestSteps.clear();
				getTestSteps = ratesGrid.bulkUpdatePoppupDayCheck(driver, Day, "yes");
				testSteps.addAll(getTestSteps);

				getTestSteps.clear();
				getTestSteps = ratesGrid.clickBulkUpdatePopupUpdateButton(driver);
				testSteps.addAll(getTestSteps);
				isCurrentDaySelected = ratesGrid.getDayState(driver, Day);

				if (isCurrentDaySelected) {
					if (days < 1) {
						days = 1;
					}
					String expectedDays = "Rates  will be updated for " + days + " day(s) within this date range.";
					testSteps.add("Expected total days : " + expectedDays);
					app_logs.info("Expected total days : " + expectedDays);
					String totalDays = ratesGrid.getTotalDaysText(driver, bulkUpdateType);
					testSteps.add("Found : " + totalDays);
					app_logs.info("Found : " + totalDays);
					// Assert.assertEquals(totalDays, expectedDays, "Failed to
					// match
					// //
					// total days");
					testSteps.add("Verified total number of days");
					app_logs.info("Verified total number of days");

				}
			}

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickYesUpdateButton(driver);
			testSteps.addAll(getTestSteps);
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify total number of days", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify total number of days", test_name, "BulkUpdate", driver);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getData("BulkUpdateRatesUI", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		 driver.quit();
	}

}
