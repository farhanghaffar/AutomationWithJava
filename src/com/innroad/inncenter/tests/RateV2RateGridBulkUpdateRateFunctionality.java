package com.innroad.inncenter.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class RateV2RateGridBulkUpdateRateFunctionality extends TestCore {
	private WebDriver driver = null;
	public static String test_name = "";
	public static String test_description = "";
	public static String test_category = "";
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> getTest_steps = new ArrayList<>();

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
			String additionalChild, String rateChangeValue, String rateCurrencyType)
			throws InterruptedException, IOException, ParseException {

		test_name = "RateV2RateGridBulkUpdateRateFunctionality_" + updateRatesType;
		test_description = "Rates V2 - Rates Grid - Bulk Update popup - Rates functionality<br>"
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
		String Day = Utility.getCurrentDate("EEE,MM/dd/YYYY");
		Day = Day.split(",")[0];
		System.out.print("Day os:" + Day);
		int size = 0;
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
			test_steps.add("Logged into the application");
			app_logs.info("Logged into the application");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
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

			test_steps.add("==========GET ALL ACTIVE CHANNEL=========");
			app_logs.info("==========GET ALL ACTIVE CHANNEL==========");

			navigation.Inventory(driver);
			test_steps.add("Navigate Setup");
			navigation.Distribution(driver);
			test_steps.add("Navigate Distribution");
			activeChannelsList = distribution.getAllActiveChannelDetails(driver);
			test_steps.add("Active Channel:" + activeChannelsList);
			app_logs.info("Active Channel:" + activeChannelsList);
			test_steps.add("==========GET ALL ROOMCLASSES=========");
			app_logs.info("==========GET ALL ROOMCLASSES==========");
			navigation.Setup(driver);
			test_steps.add("Navigate Setup");
			navigation.RoomClass(driver);
			test_steps.add("Navigate RoomClass");
			try {
				roomClass.searchButtonClick(driver);
				activeRoomClassesNames = roomClass.getAllRoomClasses(driver);
				test_steps.add("Active RoomClasses:" + activeRoomClassesNames);
				app_logs.info("Active RoomClasses:" + activeRoomClassesNames);
			} catch (Exception e) {
				roomClassList = roomClass.getAllActiveRoomClasses(driver);
				activeRoomClassesNames = (ArrayList<String>) roomClassList[0];

				test_steps.add("Active RoomClasses:" + activeRoomClassesNames);
				app_logs.info("Active RoomClasses:" + activeRoomClassesNames);
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to get Pre-Requisits", testName, "Channels/RoomClasses", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed toget Pre-Requisits", testName, "Channels/RoomClasses", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			test_steps.add("==========PRE-VERIFICATION=========");
			app_logs.info("==========PRE-VERIFICATION==========");
			navigation.Inventory(driver);
			test_steps.add("Navigate Inventory");
			navigation.Rates_Grid(driver);
			app_logs.info("Navigate RatesGrid");
			test_steps.add("Navigate RatesGrid");
			getTest_steps.clear();
			getTest_steps = ratesGrid.clickSettingButton(driver);
			test_steps.addAll(getTest_steps);
			getTest_steps.clear();
			getTest_steps = ratesGrid.changeStateShowAdditionalAdultAdditionalChilToggalSettingContainer(driver, true);
			test_steps.addAll(getTest_steps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to get Pre-Requisits", testName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to click Next button", testName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Get Rate Plan Name and Color
		try {
			test_steps.add("==========GETTING RATE PLANS=========");
			app_logs.info("==========PGETTING RATE PLANS==========");
			ratesGrid.clickRatePlanArrow(driver, test_steps);
			activeRatePlanNames = ratesGrid.getActiveRatePlanNames(driver, "active");
			app_logs.info("rate plan size for active: " + activeRatePlanNames.size());
			test_steps.add("rate plan size for inactive: " + activeRatePlanNames.size());
			driver.navigate().refresh();
			ratesGrid.clickRatePlanArrow(driver, test_steps);
			inactiveRatePlanNames = ratesGrid.getInActiveRatePlanNames(driver, "inactive");
			app_logs.info("rate plan size for inactive: " + inactiveRatePlanNames.size());
			test_steps.add("rate plan size for inactive: " + inactiveRatePlanNames.size());

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to get rateplans", test_name, "Rateplans", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to get rateplans", test_name, "Rateplans", driver);
			}
		}

		try {

			getTest_steps.clear();
			getTest_steps = ratesGrid.clickOnBulkUpdate(driver);
			test_steps.addAll(getTest_steps);

			getTest_steps.clear();
			getTest_steps = ratesGrid.selectBulkUpdateOption(driver, bulkUpdateType);
			test_steps.addAll(getTest_steps);

			getTest_steps.clear();
			getTest_steps = ratesGrid.bulkUpdatePoppupHeading(driver, bulkUpdateType);
			test_steps.addAll(getTest_steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {

			test_steps.add("==========SEELCT START DATE==========");
			app_logs.info("==========SEELCT START DATE==========");
			getTest_steps.clear();
			getTest_steps = ratesGrid.startDate(driver, checkInDate);
			test_steps.addAll(getTest_steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to select start", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to select start", test_name, "BulkUpdate", driver);
			}
		}

		try {
			test_steps.add("==========SEELCT END DATE==========");
			app_logs.info("==========SEELCT END DATE==========");

			getTest_steps.clear();
			getTest_steps = ratesGrid.endDate(driver, checkOutDate);
			test_steps.addAll(getTest_steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to select end date", test_name, "RatesPopup", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to select end date", test_name, "RatesPopup", driver);
			}
		}

		try {
			app_logs.info("==========CHECKING/UNCHECKING DAYS==========");
			test_steps.add("==========CHECKING/UNCHECKING DAYS==========");

			getTest_steps.clear();
			getTest_steps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Sun", sunday);
			test_steps.addAll(getTest_steps);

			getTest_steps.clear();
			getTest_steps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Mon", monday);
			test_steps.addAll(getTest_steps);

			getTest_steps.clear();
			getTest_steps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Tue", tuesday);
			test_steps.addAll(getTest_steps);

			getTest_steps.clear();
			getTest_steps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Wed", wednesday);
			test_steps.addAll(getTest_steps);

			getTest_steps.clear();
			getTest_steps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Thu", thursday);
			test_steps.addAll(getTest_steps);

			getTest_steps.clear();
			getTest_steps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Fri", friday);
			test_steps.addAll(getTest_steps);

			getTest_steps.clear();
			getTest_steps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Sat", saturday);
			test_steps.addAll(getTest_steps);
			// If Current Day is not checked
			getTest_steps.clear();
			getTest_steps = ratesGrid.bulkUpdatePoppupDayCheck(driver, Day, "yes");
			test_steps.addAll(getTest_steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to check/uncheck days", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to check/uncheck days", test_name, "BulkUpdate", driver);
			}
		}

		// Occupancy
		try {

			getTest_steps.clear();
			getTest_steps = ratesGrid.clickTotalOccupancy(driver, isTotalOccupancyOn);
			test_steps.addAll(getTest_steps);

			if (isTotalOccupancyOn.equalsIgnoreCase("Yes")) {

				getTest_steps.clear();
				getTest_steps = ratesGrid.selectTotalOccupancyType(driver, totalOccupancyType);
				test_steps.addAll(getTest_steps);

				getTest_steps.clear();
				getTest_steps = ratesGrid.enterOccupancyValue(driver, totalOccupancyValue);
				test_steps.addAll(getTest_steps);
			}

		} catch (

		Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to select total occupancy type", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to select total occupancy type", test_name, "BulkUpdate", driver);
			}
		}

		try {
			app_logs.info("==========SELECTING RATE PLAN==========");
			test_steps.add("==========SELECTING RATE PLAN==========");

			String[] ratePlanArray = ratePlansName.split("\\|");
			for (String str : ratePlanArray) {
				if (str.equalsIgnoreCase("All Active Rate Plans")) {
					str = str + " (" + (activeRatePlanNames.size() - 1) + ")";

				}
				System.out.print(" Rate Plan size:" + (activeRatePlanNames.size()));
				System.out.print(" Rate Plan InActive size:" + (inactiveRatePlanNames.size()));

				getTest_steps.clear();
				getTest_steps = ratesGrid.selectItemsFromDropDowns(driver, "Rate Plan", str);
				test_steps.addAll(getTest_steps);
				getTest_steps.clear();
				getTest_steps = ratesGrid.verifyDropDownDisableOnAllListSelection(driver, "Rate Plan", str);
				test_steps.addAll(getTest_steps);

			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to select rate plan", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to select rate plan", test_name, "BulkUpdate", driver);
			}
		}
		try {

			String[] roomClassArray = roomClassName.split("\\|");
			for (String str : roomClassArray) {
				if (str.equalsIgnoreCase("All room classes")) {
					str = str + " (" + activeRoomClassesNames.size() + ")";
					System.out.print(" String is:" + str);
				}
				getTest_steps.clear();
				getTest_steps = ratesGrid.selectRoomClass(driver, str);
				test_steps.addAll(getTest_steps);
				getTest_steps.clear();
				getTest_steps = ratesGrid.verifyDropDownDisableOnAllListSelection(driver, "Room class", str);
				test_steps.addAll(getTest_steps);

			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to select roomclass", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to select roomclass", test_name, "BulkUpdate", driver);
			}
		}

		try {

			app_logs.info("==========SELECTING SOURCE==========");
			test_steps.add("==========SELECTING SOURCE==========");

			String[] channelArray = channel.split("\\|");
			for (String str : channelArray) {
				if (str.equalsIgnoreCase("All sources")) {
					str = str + " (" + activeChannelsList.size() + ")";
				}
				getTest_steps.clear();
				getTest_steps = ratesGrid.selectItemsFromDropDowns(driver, "Source", str);
				test_steps.addAll(getTest_steps);
				getTest_steps.clear();
				getTest_steps = ratesGrid.verifyDropDownDisableOnAllListSelection(driver, "Source", str);
				test_steps.addAll(getTest_steps);
				ratesGrid.verifyColorCodeofSources(driver, str);

			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to select source", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to select source", test_name, "BulkUpdate", driver);
			}
		}

		try {
			app_logs.info("==========UPDATE RATES==========");
			test_steps.add("==========UPDATE RATES==========");

			// Checks Rate Update Type
			if (updateRatesType.equalsIgnoreCase("EnterNewRate")) {
				getTest_steps.clear();
				getTest_steps = ratesGrid.selectBulkUpdateRatesOption(driver, 0);
				test_steps.addAll(getTest_steps);

				getTest_steps.clear();
				getTest_steps = ratesGrid.updateRoomsByRoomClassToggle(driver,
						Boolean.parseBoolean(updateRateByRoomClass));
				test_steps.addAll(getTest_steps);

				String[] nightlyRateArray = nightlyRate.split("\\|");
				int nightArrayLength = 1;
				if (updateRateByRoomClass.equalsIgnoreCase("True")) {
					nightArrayLength = nightlyRateArray.length;
				}
				String[] additionalAdultArray = additionalAdults.split("\\|");
				String[] additionalChildArray = additionalAdults.split("\\|");
				// Check Length of NightlyRate List and Input Values
				for (int i = 0; i < nightArrayLength; i++) {

					getTest_steps.clear();
					getTest_steps = ratesGrid.updateNightlyRate(driver, i, nightlyRateArray[i]);
					test_steps.addAll(getTest_steps);

					getTest_steps.clear();
					getTest_steps = ratesGrid.updateAdditionalAdultRate(driver, i, additionalAdultArray[i]);
					test_steps.addAll(getTest_steps);

					getTest_steps.clear();
					getTest_steps = ratesGrid.updateAdditionalChildRate(driver, i, additionalChildArray[i]);
					test_steps.addAll(getTest_steps);

				}

			} else if (updateRatesType.equalsIgnoreCase("Increase") || updateRatesType.equalsIgnoreCase("Decrease")) {
				getTest_steps.clear();
				getTest_steps = ratesGrid.selectBulkUpdateRatesOption(driver, 1);
				test_steps.addAll(getTest_steps);

				getTest_steps.clear();
				getTest_steps = ratesGrid.selectRateIncreaseDecreaseOption(driver, updateRatesType);
				test_steps.addAll(getTest_steps);

				getTest_steps.clear();
				getTest_steps = ratesGrid.enterRateValueForUpdateRate(driver, rateChangeValue);
				test_steps.addAll(getTest_steps);

				getTest_steps.clear();
				getTest_steps = ratesGrid.selectRateCurrencyOption(driver, rateCurrencyType);
				test_steps.addAll(getTest_steps);

			} else {

				getTest_steps.clear();
				getTest_steps = ratesGrid.selectBulkUpdateRatesOption(driver, 2);
				test_steps.addAll(getTest_steps);

			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to select update rate option", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to select update rate option", test_name, "BulkUpdate", driver);
			}
		}

		try {

			getTest_steps.clear();
			getTest_steps = ratesGrid.clickBulkUpdatePopupUpdateButton(driver);
			test_steps.addAll(getTest_steps);

			getTest_steps.clear();
			getTest_steps = ratesGrid.clickYesUpdateButton(driver);
			test_steps.addAll(getTest_steps);
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to click update", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to click update", test_name, "BulkUpdate", driver);
			}
		}
	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getData("BulkUpdateRatesFunctionality", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		 driver.quit();
	}

}
