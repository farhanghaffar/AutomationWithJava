package com.innroad.inncenter.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.innroad.inncenter.pageobjects.OverView;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.RatesGrid;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.Rules;
import com.innroad.inncenter.pageobjects.Season;
import com.innroad.inncenter.pageobjects.Tapechart;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.webelements.Elements_RatesGrid;

public class RatesGridAvailabilityTabValidation extends TestCore {

	// Automation-1686
	private WebDriver driver = null;

	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();
	ArrayList<String> testName = new ArrayList<>();
	ArrayList<String> testCategory = new ArrayList<>();
	ArrayList<String> testDescription = new ArrayList<>();
	String scriptName = "RatesGridAvailabilityTabValidation";
	public static String test_description = "";
	public static String test_category = "";

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {

		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Inventory")
	public void ratesGridAvailabilityTabValidation(String day, String totalOccupancy, String paceVsLastYear,
			String addRatePlan, String bulkUpdate, String rates, String availability, String rules,
			String nightlyRatePlan, String drivedRatePlan, String pakageRatePlan, String intervalRatePlan,
			String bulkUpdateRateTitle, String bulkUpdateAvailabilityTitle, String bulkUpdateRulesTitle,
			String weekendColor, String otherweekDayColor, String nextDays, String totalDaysDisplayed,
			String maximumRoomClassesToExpand, String source, String specificDate, String dateFormat,
			String calendarTodayDay, String blockedStatus, String greenStatus)
			throws InterruptedException, IOException {

		test_description = "Rates V2 - Rates Grid - Availability Tab Validation<br>"
				+ "<a href='https://innroad.atlassian.net/browse/AUTOMATION-1686' target='_blank'>"
				+ "Click here to open Jira: AUTOMATION-1686</a>";

		test_category = "Rates Grid";
		testName.add(scriptName);
		testDescription.add(test_description);
		testCategory.add(test_category);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + scriptName + " TEST.");
		app_logs.info("##################################################################################");

		OverView overView = new OverView();
		Navigation navigation = new Navigation();
		Distribution distribution = new Distribution();
		RatesGrid ratesGrid = new RatesGrid();
		RoomClass roomClass = new RoomClass();
		ArrayList<String> getRoomClasses = new ArrayList<>();
		ArrayList<String> getRoomsNumber = new ArrayList<>();
		ArrayList<String> roomClassesAbbreviation = new ArrayList<>();
		ArrayList<String> activeChannelsList = new ArrayList<String>();
		Elements_RatesGrid ratesGridElements = new Elements_RatesGrid(driver);
		String timeZone = "America/New_York";
		app_logs.info("Time Zone " + timeZone);
		app_logs.info("Curret Time " + Utility.getCurrentDate("MMM dd,yyy: H:m:s", timeZone));

		String propertyName = Utility.WestPointInnProperty;
		String todayDate = "";
		String roomTotalValue = null;
		String roomOOOValue = null;
		String roomReservedValue = null;
		String roomAvailableValue = null;
		String roomAvailablePercentage = null;
		String roomTotalValueData = null;
		String roomOOOValueData = null;
		String roomReservedValueData = null;
		String roomAvailableValueData = null;
		String roomAvailablePercentageData = null;
		String occupancyPercentageValue = null;
		String paceVsLastYesrValue = null;
		String occupancyPercentageValueData = null;
		String paceVsLastYesrValueData = null;
		String roomStatus = null;
		String roomStatusData = null;
		String calendarTodayDate = null;
		try {
			if (!Utility.insertTestName.containsKey(scriptName)) {
				Utility.insertTestName.put(scriptName, scriptName);
				Utility.reTry.put(scriptName, 0);

			} else {
				Utility.reTry.replace(scriptName, 1);
			}
			driver = getDriver();
			loginWPI(driver);
			testSteps.add("Entered appication URL : " + TestCore.envURL);
			testSteps.add("Logged into the application ");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Get Time Zone
		try {
			// testSteps.add("===================GET PROPERTY TIME
			// ZONE======================");
			// app_logs.info("===================GET PROPERTY TIME
			// ZONE======================");
			// navigation.Setup(driver);
			// testSteps.add("Navigate Setup");
			// app_logs.info("Navigate Setup");
			// navigation.Properties(driver);
			// testSteps.add("Navigat Properties");
			// app_logs.info("Navigat Properties");
			// navigation.open_Property(driver, testSteps, propertyName);
			// testSteps.add("Open Property : " + propertyName);
			// app_logs.info("Open Property : " + propertyName);
			// navigation.click_PropertyOptions(driver, testSteps);
			// timeZone = navigation.get_Property_TimeZone(driver);
			// navigation.Reservation_Backward(driver);
			// testSteps.add("Time Zone " + timeZone);
			// app_logs.info("Time Zone " + timeZone);
			// app_logs.info("Curret Time " + Utility.getCurrentDate("MMM
			// dd,yyy: H:m:s", timeZone));

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to get Time Zone", scriptName, "getTimeZone", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to get Time Zone", scriptName, "getTimeZone", driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("==========GET ALL ACTIVE CHANNEL=========");
			app_logs.info("==========GET ALL ACTIVE CHANNEL==========");

			navigation.Inventory(driver);
			testSteps.add("Navigate Setup");
			navigation.Distribution(driver);
			testSteps.add("Navigate Distribution");
			activeChannelsList = distribution.getAllActiveChannelDetails(driver);
			System.out.println(activeChannelsList);
			testSteps.addAll(activeChannelsList);
			System.out.println(" size is:" + activeChannelsList.size());

			app_logs.info("==========GET ALL ACIVE ROOM CLASS==========");
			testSteps.add("==========GET ALL ACIVE ROOM CLASS==========");

			navigation.mainSetupManu(driver);
			app_logs.info("Navigate Setup");
			testSteps.add("Navigate Setup");

			navigation.roomClass(driver);

			testSteps.add("Navigate Room Class");

			getTestSteps.clear();
			getTestSteps = roomClass.selectRoomClassStatus(driver, "Active");
			testSteps.addAll(getTestSteps);

			List<String>[] arrayOfList = new List[3];
			arrayOfList = roomClass.getAllActiveRoomClasses(driver);
			getRoomClasses = (ArrayList<String>) arrayOfList[0];

			getRoomsNumber = (ArrayList<String>) arrayOfList[2];

			roomClassesAbbreviation = (ArrayList<String>) arrayOfList[1];

			app_logs.info(getRoomClasses.size());
			app_logs.info("roomClassesAbbreviation: " + roomClassesAbbreviation.size());
			app_logs.info("getRoomsNumber: " + getRoomsNumber.size());
			for (int i = 0; i < getRoomClasses.size(); i++) {
				System.out.println(
						getRoomClasses.get(i) + "  " + roomClassesAbbreviation.get(i) + "  " + getRoomsNumber.get(i));
			}

			testSteps.addAll(getRoomClasses);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to store room class in arraylist", scriptName, "StoreRoomClass",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to store room class in arraylist", scriptName, "StoreRoomClass",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// navigate to Inventory->Rates Grid
		try {
			navigation.Inventory_Backward_1(driver);
			// navigation.Inventory(driver);
			testSteps.add("Navigate Inventory");
			app_logs.info("Navigate Inventory");
			navigation.Rates_Grid(driver);
			testSteps.add("Navigate Rates Grid");
			app_logs.info("Navigate Rates Grid");
			try {
				getTestSteps.clear();
				getTestSteps = ratesGrid.clickOnAvailability(driver);
				testSteps.addAll(getTestSteps);
			} catch (Exception f) {

				navigation.Rates_Grid(driver);
				getTestSteps.clear();
				getTestSteps = ratesGrid.clickOnAvailability(driver);
				testSteps.addAll(getTestSteps);
			}

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to Navigate Rates Grid", scriptName, "Navigation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to Navigate Rates Grid", scriptName, "Navigation", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// verify
		try {
			app_logs.info(
					"========== READ TOTAL OCCUPANCY % VALUES FOR EACH DATE AS WELL AS PACE VS LAST YEAR ==========");
			testSteps.add(
					"========== READ TOTAL OCCUPANCY % VALUES FOR EACH DATE AS WELL AS PACE VS LAST YEAR==========");
			occupancyPercentageValueData = "Total Occupancy ----: ";
			paceVsLastYesrValueData = "Pace Vs Last Year --: ";
			for (int j = 0; j < Integer.parseInt(totalDaysDisplayed); j++) {
				occupancyPercentageValue = ratesGrid.getOccupancyORPaceDataValue(driver, j, "OccupancyBet");
				paceVsLastYesrValue = ratesGrid.getOccupancyORPaceDataValue(driver, j, "pace");
				occupancyPercentageValueData = occupancyPercentageValueData + "--" + occupancyPercentageValue;
				paceVsLastYesrValueData = paceVsLastYesrValueData + "--" + paceVsLastYesrValue;
			}
			testSteps.add(occupancyPercentageValueData);
			app_logs.info(occupancyPercentageValueData);
			testSteps.add(paceVsLastYesrValueData);
			app_logs.info(paceVsLastYesrValueData);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// verify
		try {
			app_logs.info("========== VERIFY < AND > IN DATE RANGE AND VERIFY DATE RANGE SHOWN IN THE GRID ==========");
			testSteps.add("========== VERIFY < AND > IN DATE RANGE AND VERIFY DATE RANGE SHOWN IN THE GRID ==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyCalendarArrowDisplayed(driver, "left");
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyCalendarArrowDisplayed(driver, "right");
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = ratesGrid.clickCalendar(driver);
			testSteps.addAll(getTestSteps);
			calendarTodayDate = ratesGrid.getCalendarDate(driver, calendarTodayDay, dateFormat, testSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.closeCalendar(driver);
			testSteps.addAll(getTestSteps);
			ratesGrid.verifySelectedDateOnDateRangeCalendar(driver, calendarTodayDate,
					Utility.addDate(19, dateFormat, calendarTodayDate, dateFormat, timeZone), dateFormat, testSteps);

			app_logs.info("========== VERIFY TODAY'S DATE + 20 DAYS SHOULD BE DISPLAYED ==========");
			testSteps.add("========== VERIFY TODAY'S DATE + 20 DAYS SHOULD BE DISPLAYED ==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyHeadingDates(driver, calendarTodayDate, dateFormat, timeZone, getTestSteps);
			testSteps.addAll(getTestSteps);
			app_logs.info("Successfully verified Today's date + 20 days are displaying");
			testSteps.add("Successfully verified Today's date + 20 days are displaying");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// verify
		try {
			app_logs.info("========== VERIFY NEXT TO THE DATE RANGE - MONTH, YEAR SHOULD BE DISPLAYED ==========");
			testSteps.add("========== VERIFY NEXT TO THE DATE RANGE - MONTH, YEAR SHOULD BE DISPLAYED ==========");

			ArrayList<String> monthRange = ratesGrid.calculateMonthRange(driver, calendarTodayDate, dateFormat,
					timeZone);
			ratesGrid.verifyMonthRange(driver, monthRange);
			app_logs.info(
					"Successfully verified Next to the Date range - Month, Year should be displayed (depending on which dates the 20 days in current view fall)");
			testSteps.add(
					"Successfully verified Next to the Date range - Month, Year should be displayed (depending on which dates the 20 days in current view fall)");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// verify
		try {
			app_logs.info(
					"========== VERIFY ON CLICK OF '>' IN DATE CALENDER NEXT SET OF 20 DAYS SHOULD BE SHOWN ==========");
			testSteps.add(
					"========== VERIFY ON CLICK OF '>' IN DATE CALENDER NEXT SET OF 20 DAYS SHOULD BE SHOWN ==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickCalendarArrow(driver, "right");
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyHeadingDates(driver,
					Utility.addDate(20, dateFormat, calendarTodayDate, dateFormat, timeZone), dateFormat, timeZone,
					getTestSteps);
			testSteps.addAll(getTestSteps);
			app_logs.info("Successfully verified Next set of 20 days are displaying");
			testSteps.add("Successfully verified Next set of 20 days are displaying");
			getTestSteps.clear();
			getTestSteps = ratesGrid.clickCalendarArrow(driver, "left");
			testSteps.addAll(getTestSteps);
			getTestSteps = ratesGrid.verifyHeadingDates(driver, calendarTodayDate, dateFormat, timeZone, getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// verify
		try {
			app_logs.info(
					"== VERIFY ON CLICK OF DATE CALENDAR MONTH< DAY NAVIGATION SHOULD BE POSSIBLE ALONG WITH TODAY BUTTON SHOWN WITHIN CALENDAR ==");
			testSteps.add(
					"=VERIFY ON CLICK OF DATE CALENDAR MONTH< DAY NAVIGATION SHOULD BE POSSIBLE ALONG WITH TODAY BUTTON SHOWN WITHIN CALENDAR=");

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickCalendar(driver);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyTodayButtonExistInCalender(driver, false);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.selectCalendarNextDate(driver, nextDays);
			testSteps.addAll(getTestSteps);
			app_logs.info("Successfully verified day navigation is possible in Calendar");
			testSteps.add("Successfully verified day navigation is possible in Calendar");

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyHeadingDates(driver,
					Utility.addDate(Integer.parseInt(nextDays), dateFormat, calendarTodayDate, dateFormat, timeZone),
					dateFormat, timeZone, getTestSteps);
			testSteps.addAll(getTestSteps);
			app_logs.info("==VERIFY WHEN WE SELECT A SPECIFIC DATE(" + specificDate
					+ ") WITHIN CALENDAR THEN FROM THE SELECTED DATE 20 DAYS SHOULD BE SHOWN==");
			testSteps.add("==VERIFY WHEN WE SELECT A SPECIFIC DATE(" + specificDate
					+ ") WITHIN CALENDAR THEN FROM THE SELECTED DATE 20 DAYS SHOULD BE SHOWN==");

			ratesGrid.clickForRateGridCalender(driver, testSteps);
			String endDate = ratesGrid.selectDateFromDatePicker(driver, specificDate, dateFormat, testSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyHeadingDates(driver, specificDate, dateFormat, timeZone, getTestSteps);
			testSteps.addAll(getTestSteps);
			app_logs.info("Successfully verified from the selected Date(" + specificDate + ") 20 days are displaying");
			testSteps.add("Successfully verified from the selected Date(" + specificDate + ") 20 days are displaying");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// verify
		try {
			app_logs.info(
					"========== VERIFY 'TOTAL OCCUPANCE' AND 'PACE VS LAST YEAR' LABEL SHOULD BE SHOWN BELOW DAY HEADING ==========");
			testSteps.add(
					"========== VERIFY 'TOTAL OCCUPANCE' AND 'PACE VS LAST YEAR' LABEL SHOULD BE SHOWN BELOW DAY HEADING ==========");
			ratesGrid.verifyTotalOccupancyLabelExist(driver);
			app_logs.info("Successfully verified 'Total Occupance' is displaying");
			testSteps.add("Successfully verified 'Total Occupance' is displaying");
			ratesGrid.verifyPaceVsLastYearLabelExist(driver);
			app_logs.info("Successfully verified 'Pace vs Last Year label' is displaying");
			testSteps.add("Successfully verified 'Pace vs Last Year label' is displaying");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// verify
		try {
			try {
				getTestSteps.clear();
				getTestSteps = ratesGrid.clickCalendar(driver);
				testSteps.addAll(getTestSteps);
			} catch (Exception f) {
				getTestSteps.clear();
				getTestSteps = ratesGrid.clickCalendar(driver);
				testSteps.addAll(getTestSteps);
			}

			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyTodayButtonExistInCalender(driver, true);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickCalendar(driver);
			testSteps.addAll(getTestSteps);

			calendarTodayDate = ratesGrid.getCalendarDate(driver, calendarTodayDay, dateFormat, testSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.closeCalendar(driver);
			testSteps.addAll(getTestSteps);
			app_logs.info(
					"========== VERIFY ON CLICK OF DATE HEADING HAND SYMBOL 'MONTH DATE INSIGHT' SECTION IS DISPLAYED ON THE RIGHT ==========");
			testSteps.add(
					"========== VERIFY ON CLICK OF DATE HEADING HAND SYMBOL 'MONTH DATE INSIGHT' SECTION IS DISPLAYED ON THE RIGHT ==========");
			int dateIndex = ratesGrid.getHeadingDateIndex(driver, calendarTodayDate, dateFormat);
			String date = Utility.parseDate(calendarTodayDate, dateFormat, "MMMM dd");
			ratesGrid.clickHeadingDate(driver, dateIndex, date);
			app_logs.info("Click on Date : " + date);
			testSteps.add("Click on Date : " + date);
			app_logs.info("Successfully verified 'Month date Insights' section is displayed on the right");
			testSteps.add("Successfully verified 'Month date Insights' section is displayed on the right");
			getTestSteps.clear();
			getTestSteps = ratesGrid.closeMonthDateIndightSideBar(driver, date, getTestSteps);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// verify
		try {
			app_logs.info(
					"========== VERIFY ALL ACTIVE ROOM CLASSES SHOULD BE SHOWN ONE BELOW ANOTHER (IN ASCENDING ORDER) ==========");
			testSteps.add(
					"========== VERIFY ALL ACTIVE ROOM CLASSES SHOULD BE SHOWN ONE BELOW ANOTHER (IN ASCENDING ORDER) ==========");
			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyRoomClasses(driver, getRoomClasses, getTestSteps);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// verify
		try {
			app_logs.info(
					"== VERIFY ON CLICK ON + OF A ROOM CLASS TOTAL, OOO, RESERVED, AVAILABLE LABELS ALONG WITH CORRECT VALUES TO BE SHOWN ===");
			testSteps.add(
					"=== VERIFY ON CLICK ON + OF A ROOM CLASS TOTAL, OOO, RESERVED, AVAILABLE LABELS ALONG WITH CORRECT VALUES TO BE SHOWN ===");

			for (int i = 0; i < getRoomClasses.size(); i++) {
				roomTotalValueData = "Total------:";
				roomOOOValueData = "OOO--------: ";
				roomReservedValueData = "Reserved---: ";
				roomAvailableValueData = "Available--: ";
				roomAvailablePercentageData = "Percentage-: ";
				roomStatusData = "innCenter--: ";
				if (i == Integer.parseInt(maximumRoomClassesToExpand)) {
					break;
				}
				testSteps.add("========= VERIFY Room Class : '" + getRoomClasses.get(i) + "' ===========");
				app_logs.info("========= VERIFY Room Class : '" + getRoomClasses.get(i) + "' ===========");
				ratesGrid.expandRoomClass(driver, i);
				testSteps.add("Expand Room Class");
				app_logs.info("Expand Room Class");
				app_logs.info("Expected Room Total : " + getRoomsNumber.get(i));
				for (int j = 0; j < Integer.parseInt(totalDaysDisplayed); j++) {
					roomTotalValue = ratesGrid.getRoomClassDataValue(driver, j, "Total");
					roomOOOValue = ratesGrid.getRoomClassDataValue(driver, j, "OOO");
					roomReservedValue = ratesGrid.getRoomClassDataValue(driver, j, "Reserved");
					roomAvailableValue = ratesGrid.getRoomClassDataValue(driver, j, "Available");
					roomAvailablePercentage = ratesGrid.getRoomClassDataValue(driver, j, getRoomClasses.get(i));
					roomStatus = ratesGrid.getRoomStatus(driver, j, source, getRoomClasses.get(i));

					roomTotalValueData = roomTotalValueData + "--" + roomTotalValue;
					roomOOOValueData = roomOOOValueData + "--" + roomOOOValue;
					roomReservedValueData = roomReservedValueData + "--" + roomReservedValue;
					roomAvailableValueData = roomAvailableValueData + "--" + roomAvailableValue;
					roomAvailablePercentageData = roomAvailablePercentageData + "-" + roomAvailablePercentage;
					roomStatusData = roomStatusData + "--" + roomStatus;
					assertEquals(roomTotalValue, getRoomsNumber.get(i), "Failed: Room Total Missmatched");
					String calculatedValue = Integer.toString(Integer.parseInt(roomOOOValue)
							+ Integer.parseInt(roomReservedValue) + Integer.parseInt(roomAvailableValue));
					assertEquals(calculatedValue, roomTotalValue,
							"Failed: Sum of all OOO, Reserved and Available values is not equal to total");
					assertEquals(roomAvailablePercentage,
							Integer.toString(
									(Integer.parseInt(roomReservedValue) / Integer.parseInt(roomTotalValue)) * 100)
									+ "%",
							"Failed: Percentafge value not matched");
				}

				testSteps.add(roomAvailablePercentageData);
				app_logs.info(roomAvailablePercentageData);
				testSteps.add(roomTotalValueData);
				app_logs.info(roomTotalValueData);
				testSteps.add(roomOOOValueData);
				app_logs.info(roomOOOValueData);
				testSteps.add(roomReservedValueData);
				app_logs.info(roomReservedValueData);
				testSteps.add(roomAvailableValueData);
				app_logs.info(roomAvailableValueData);
				testSteps.add(roomStatusData);
				app_logs.info(roomStatusData);

				ratesGrid.expandRoomClass(driver, i);
				testSteps.add("Reduce Room Class");
				app_logs.info("Reduce Room Class");
			}
			testSteps.add(
					"Successfully Verified On click on + of a Room class Availability 0%, Total, OOO, Reserved, Available labels along with correct values are displayed.");
			app_logs.info(
					"Successfully Verified On click on + of a Room class Total, OOO, Reserved, Available labels along with correct values are displayed.");
			testSteps.add("Successfully Verified Room class % matches with Total, OOO, Reserved and Available counts");
			app_logs.info("Successfully Verified Room class % matches with Total, OOO, Reserved and Available counts");
			testSteps.add("Successfully Verified Room Status(Available(Green Dots) or Blackout Room)");
			app_logs.info("Successfully Verified Room Status(Available(Green Dots) or Blackout Room)");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// verify
		try {
			app_logs.info(
					"========== VERIFY THE BACKGROUND COLOR FOR WEEK DAYS(LIGHT GREY) AND WEEKENDS(DARK GREY) ==========");
			testSteps.add(
					"========== VERIFY THE BACKGROUND COLOR FOR WEEK DAYS(LIGHT GREY) AND WEEKENDS(DARK GREY) ==========");
			ratesGrid.verifyDatesBackGroungColor(driver, weekendColor, otherweekDayColor, getTestSteps);
			app_logs.info("Successfully verified the background color");
			testSteps.add("Successfully verified the background color");
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// verify
		try {
			app_logs.info("=========== VERIFY ACTIVE CHANNELS IN ROOM CLASS ==========");
			testSteps.add("=========== VERIFY ACTIVE CHANNELS IN ROOM CLASS ===========");

			for (int i = 0; i < getRoomClasses.size(); i++) {
				if (i == Integer.parseInt(maximumRoomClassesToExpand)) {
					break;
				}
				testSteps.add("========= VERIFY Room Class : '" + getRoomClasses.get(i) + "' ===========");
				app_logs.info("========= VERIFY Room Class : '" + getRoomClasses.get(i) + "' ===========");
				ratesGrid.expandRoomClass(driver, i);
				testSteps.add("Expand Room Class");
				app_logs.info("Expand Room Class");
				app_logs.info("Expected Room Total : " + getRoomsNumber.get(i));

				for (int j = 0; j < activeChannelsList.size(); j++) {
					int roomIndex = ratesGrid.getRoomClassIndex(driver, getRoomClasses.get(i));
					getTestSteps.clear();
					getTestSteps = ratesGrid.verifyChannels(driver, getRoomClasses.get(roomIndex), activeChannelsList,
							getTestSteps);
					testSteps.addAll(getTestSteps);
					app_logs.info("=========== FOR A SPECIFIC DATE MAKE CHANNEL(" + activeChannelsList.get(j)
							+ ") BLACKOUT THEN AVAILABLE AND VERIFY ==========");
					testSteps.add("=========== FOR A SPECIFIC DATE MAKE CHANNEL(" + activeChannelsList.get(j)
							+ ") BLACKOUT THEN AVAILABLE AND VERIFY ===========");

					ratesGrid.activeOrBlackoutChannel(driver, calendarTodayDate, dateFormat,
							getRoomClasses.get(roomIndex), activeChannelsList.get(j), blockedStatus);
					app_logs.info("Blackout room for Date '" + calendarTodayDate + "' and Source '" + activeChannelsList.get(j) + "'");
					testSteps.add("Blackout room for Date '" + calendarTodayDate + "' and Source '" + activeChannelsList.get(j) + "'");
					assertEquals(
							ratesGrid.getRoomStatus(driver, calendarTodayDate, dateFormat, activeChannelsList.get(j),
									getRoomClasses.get(roomIndex)),
							blockedStatus, "Failed : Room Status is not Blacked Out");
					app_logs.info(
							"Successfully verified blackout room and 'B' is displaying under room percentage value");
					testSteps.add(
							"Successfully verified blackout room and 'B' is displaying under room percentage value");
					ratesGrid.activeOrBlackoutChannel(driver, calendarTodayDate, dateFormat,
							getRoomClasses.get(roomIndex), activeChannelsList.get(j), greenStatus);
					app_logs.info(
							"Make room Available  for Date '" + calendarTodayDate + "' and Source '" + activeChannelsList.get(j) + "'");
					testSteps.add(
							"Make room Available  for Date '" + calendarTodayDate + "' and Source '" + activeChannelsList.get(j) + "'");
					assertEquals(
							ratesGrid.getRoomStatus(driver, calendarTodayDate, dateFormat, activeChannelsList.get(j),
									getRoomClasses.get(roomIndex)),
							greenStatus, "Failed : Room Status is not Available");
					app_logs.info("Successfully verified Green Dot Appeared showing room as available");
					testSteps.add("Successfully verified Green Dot Appeared showing room as available");
				}

				ratesGrid.expandRoomClass(driver, i);
				testSteps.add("Reduce Room Class");
				app_logs.info("Reduce Room Class");
			}
			testSteps.add(
					"Successfully Verified On click on + of a Room class Availability 0%, Total, OOO, Reserved, Available labels along with correct values are displayed.");
			app_logs.info(
					"Successfully Verified On click on + of a Room class Total, OOO, Reserved, Available labels along with correct values are displayed.");
			testSteps.add("Successfully Verified Room class % matches with Total, OOO, Reserved and Available counts");
			app_logs.info("Successfully Verified Room class % matches with Total, OOO, Reserved and Available counts");
			testSteps.add("Successfully Verified Room Status(Available(Green Dots) or Blackout Room)");
			app_logs.info("Successfully Verified Room Status(Available(Green Dots) or Blackout Room)");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// verify
		try {
			app_logs.info(
					"========== VERIFY FOR A SPECIFIC DATE MAKE A ROOM CLASS CHANNEL AVAILABLE OR BLACKOUT AND VERIFY ==========");
			testSteps.add(
					"========== VERIFY FOR A SPECIFIC DATE MAKE A ROOM CLASS CHANNEL AVAILABLE OR BLACKOUT AND VERIFY ==========");

			int dateIndex = ratesGrid.getHeadingDateIndex(driver, calendarTodayDate, dateFormat);
			int roomIndex = ratesGrid.getRoomClassIndex(driver, getRoomClasses.get(0));
			ratesGrid.expandRoomClass(driver, roomIndex);
			testSteps.add("Expand Room Class");
			app_logs.info("Expand Room Class");
			String status = "*";
			ratesGrid.changeRoomStatus(driver, dateIndex, source, getRoomClasses.get(roomIndex), blockedStatus);
			app_logs.info("Make room Available (if not Available) for Date '" + calendarTodayDate + "' and Source '"
					+ source + "'");
			testSteps.add("Make room Available (if not Available) for Date '" + calendarTodayDate + "' and Source '"
					+ source + "'");
			assertEquals(ratesGrid.getRoomStatus(driver, dateIndex, source, getRoomClasses.get(roomIndex)),
					blockedStatus, "Failed : Room Status is not Available");
			app_logs.info("Successfully verified Green Dot Appeared showing room as available");
			testSteps.add("Successfully verified Green Dot Appeared showing room as available");
			status = "B";
			ratesGrid.changeRoomStatus(driver, dateIndex, source, getRoomClasses.get(roomIndex), greenStatus);
			app_logs.info("Blackout room");
			testSteps.add("Blackout room");
			assertEquals(ratesGrid.getRoomStatus(driver, dateIndex, source, getRoomClasses.get(roomIndex)), greenStatus,
					"Failed : Room Status is not Available");
			app_logs.info("Successfully verified blackout room and 'B' is displaying under room percentage value");
			testSteps.add("Successfully verified blackout room and 'B' is displaying under room percentage value");
			ratesGrid.expandRoomClass(driver, roomIndex);
			testSteps.add("Reduce Room Class");
			app_logs.info("Reduce Room Class");
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify", scriptName, "verify", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("=============VERIFICATION OF AVAILABILITY TABS AND BUTTONS================");

			ratesGrid.clickAndVerifySettingButton(driver);

			ratesGrid.clickAndVerifyAddRatePlanButton(driver);

			ratesGrid.clickAndVerifyBulkUpdateButton(driver);
			testSteps.add("Verified Settings, Add Rate Plan, Bulk Update buttons are visible and enabled");
			ratesGrid.clickAndVerifySettingButton(driver);
			ratesGrid.verifyHeadingAvailabilitySettingMenu(driver, "Availability");
			ratesGrid.verifyHeadingRatesSettingMenu(driver, "Rates");
			ratesGrid.verifyAvailabilityToggleTextSettingMenu(driver, "Show room availability");
			ratesGrid.verifyRatesToggleTextSettingMenu(driver, "Show additional adult and child");
			ratesGrid.verifyToggleButtonAvailablity(driver);
			testSteps.add("Verified On click of Settings - Show 2 menus (Availability and Rates)");
			testSteps.add("Successfully verified Availability and Rates heading and texts and toggle buttons");
			getTestSteps.clear();
			getTestSteps = ratesGrid.clickOnAvailability(driver);
			testSteps.addAll(getTestSteps);

			String getTabDetails = ratesGrid.getDayTabText(driver);
			testSteps.add("Expexted : " + day);
			testSteps.add("Found : " + getTabDetails);
			assertEquals(getTabDetails, day, "Failed to verify day");
			testSteps.add("Verified Day");

			getTabDetails = ratesGrid.getTotalOccupancyTabText(driver);
			testSteps.add("Expexted : " + totalOccupancy);
			testSteps.add("Found : " + getTabDetails);
			assertEquals(getTabDetails, totalOccupancy, "Failed to verify total occupancy");
			testSteps.add("Verified total occupancy");

			getTabDetails = ratesGrid.getPaceVsLastYearTabText(driver);
			testSteps.add("Expexted : " + paceVsLastYear);
			testSteps.add("Found : " + getTabDetails);
			assertEquals(getTabDetails, paceVsLastYear, "Failed to verify pace vs last year");
			testSteps.add("Verified pace vs last year");

			getTabDetails = ratesGrid.getAddRatePlanButtonText(driver);
			testSteps.add("Expexted : " + addRatePlan);
			testSteps.add("Found : " + getTabDetails);
			assertEquals(getTabDetails, addRatePlan, "Failed to verify add rate plan");
			testSteps.add("Verified add rate plan");

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickOnAddRatePlan(driver);
			testSteps.addAll(getTestSteps);

			getTabDetails = ratesGrid.getNightlyRatePlanText(driver);
			testSteps.add("Expexted : " + nightlyRatePlan);
			testSteps.add("Found : " + getTabDetails);
			assertEquals(getTabDetails, nightlyRatePlan, "failed to verify nightly rate plan");
			testSteps.add("Verified nightly rate plan");

			getTabDetails = ratesGrid.getDrivedRatePlanText(driver);
			testSteps.add("Expexted : " + drivedRatePlan);
			testSteps.add("Found : " + getTabDetails);
			assertEquals(getTabDetails, drivedRatePlan, "Failed to verify drived rate plan");
			testSteps.add("Verified drived rate plan");

			getTabDetails = ratesGrid.getIntervalRatePlanText(driver);
			testSteps.add("Expexted : " + intervalRatePlan);
			testSteps.add("Found : " + getTabDetails);
			assertEquals(getTabDetails, intervalRatePlan, "Failed to verify interval rate plan");
			testSteps.add("Verified interval rate plan");

			getTabDetails = ratesGrid.getPackageRatePlanText(driver);
			testSteps.add("Expexted : " + pakageRatePlan);
			testSteps.add("Found : " + getTabDetails);
			assertEquals(getTabDetails, pakageRatePlan, "Failed to verify pakage rate plan");
			testSteps.add("Verified pakage rate plan");
			// nightly rate plan
			getTestSteps.clear();
			getTestSteps = ratesGrid.clickNightlyRatePlan(driver);
			testSteps.addAll(getTestSteps);

			getTabDetails = ratesGrid.getNewRatePlanTabTitleText(driver);
			testSteps.add("Expexted : " + nightlyRatePlan);
			testSteps.add("Found : " + getTabDetails);
			assertEquals(getTabDetails, nightlyRatePlan, "Failed to verify nightly rate plan");
			testSteps.add("Verified nightly rate plan");

			getTestSteps.clear();
			getTestSteps = ratesGrid.closeOpendTabInMainMenu(driver);
			testSteps.addAll(getTestSteps);
			// drived rate plan

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickOnAddRatePlan(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickDrivedRatePlan(driver);
			testSteps.addAll(getTestSteps);

			getTabDetails = ratesGrid.getNewRatePlanTabTitleText(driver);
			testSteps.add("Expexted : " + drivedRatePlan);
			testSteps.add("Found : " + getTabDetails);
			assertEquals(getTabDetails, drivedRatePlan, "Failed to verify drived rate plan");
			testSteps.add("Verified drived rate plan");

			getTestSteps.clear();
			getTestSteps = ratesGrid.closeOpendTabInMainMenu(driver);
			testSteps.addAll(getTestSteps);
			// pakage rate plan

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickOnAddRatePlan(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickPackageRatePlan(driver);
			testSteps.addAll(getTestSteps);

			getTabDetails = ratesGrid.getNewRatePlanTabTitleText(driver);
			testSteps.add("Expexted : " + pakageRatePlan);
			testSteps.add("Found : " + getTabDetails);
			assertEquals(getTabDetails, pakageRatePlan, "Failed to verify pakage rate plan");
			testSteps.add("Verified pakage rate plan");

			getTestSteps.clear();
			getTestSteps = ratesGrid.closeOpendTabInMainMenu(driver);
			testSteps.addAll(getTestSteps);
			// interval rate plan

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickOnAddRatePlan(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickIntervalRatePlan(driver);
			testSteps.addAll(getTestSteps);

			getTabDetails = ratesGrid.getNewRatePlanTabTitleText(driver);
			testSteps.add("Expexted : " + intervalRatePlan);
			testSteps.add("Found : " + getTabDetails);
			assertEquals(getTabDetails, intervalRatePlan, "Failed to verify interval rate plan");
			testSteps.add("Verified interval rate plan");

			getTestSteps.clear();
			getTestSteps = ratesGrid.closeOpendTabInMainMenu(driver);
			testSteps.addAll(getTestSteps);

			// bulk update

			getTabDetails = ratesGrid.getBulkUpdateButtonText(driver);
			testSteps.add("Expexted : " + bulkUpdate);
			testSteps.add("Found : " + getTabDetails);
			assertEquals(getTabDetails, bulkUpdate, "Failed to verify bulk update");
			testSteps.add("Verified bulk update button");

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickOnBulkUpdate(driver);
			testSteps.addAll(getTestSteps);

			getTabDetails = ratesGrid.getRatesBulkUpdateText(driver);
			testSteps.add("Expexted : " + rates);
			testSteps.add("Found : " + getTabDetails);
			assertEquals(getTabDetails, rates, "Failed to verify rates");
			testSteps.add("Verified rates");

			getTabDetails = ratesGrid.getAvailabilityBulkUpdateText(driver);
			testSteps.add("Expexted : " + availability);
			testSteps.add("Found : " + getTabDetails);
			assertEquals(getTabDetails, availability, "Failed to verify availability");
			testSteps.add("Verified availability");

			getTabDetails = ratesGrid.getRulesBulkUpdateText(driver);
			testSteps.add("Expexted : " + rules);
			testSteps.add("Found : " + getTabDetails);
			assertEquals(getTabDetails, rules, "Failed to verify rules");
			testSteps.add("Verified rules");

			// rates
			getTestSteps.clear();
			getTestSteps = ratesGrid.clickRatesBulkUpdate(driver);
			testSteps.addAll(getTestSteps);

			getTabDetails = ratesGrid.getBulkUpdateHeaderTitle(driver);
			assertTrue(getTabDetails.contains(bulkUpdateRateTitle), "Failed to verify Bulk update-Rates");
			testSteps.add("Verified Bulk update-Rates");

			getTestSteps.clear();
			getTestSteps = ratesGrid.closeRateBulkUpdatePopup(driver);
			testSteps.addAll(getTestSteps);
			// availability
			getTestSteps.clear();
			getTestSteps = ratesGrid.clickOnBulkUpdate(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.selectAvailabilityFromBulkUpdate(driver);
			testSteps.addAll(getTestSteps);

			getTabDetails = ratesGrid.getBulkUpdateHeaderTitle(driver);
			assertTrue(getTabDetails.contains(bulkUpdateAvailabilityTitle),
					"Failed to verify bulk update- availability");
			testSteps.add("Verified Bulk update-Availability");

			getTestSteps.clear();
			getTestSteps = ratesGrid.closeRateBulkUpdatePopup(driver);
			testSteps.addAll(getTestSteps);
			// rules
			getTestSteps.clear();
			getTestSteps = ratesGrid.clickOnBulkUpdate(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickRulesBulkUpdate(driver);
			testSteps.addAll(getTestSteps);

			getTabDetails = ratesGrid.getBulkUpdateHeaderTitle(driver);
			assertTrue(getTabDetails.contains(bulkUpdateRulesTitle), "Failed to verify bulk update- Rules");
			testSteps.add("Verified Bulk update-Rules");

			getTestSteps.clear();
			getTestSteps = ratesGrid.closeRateBulkUpdatePopup(driver);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify availibilty tabs and buttons", scriptName, "Navigation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to verify availibilty tabs and buttons", scriptName, "Navigation",
						driver);
			} else {

				Assert.assertTrue(false);
			}
		}
		// Generate Report
		try {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to Generate Report", scriptName, "GenerateReport", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_category, testSteps);
				Utility.updateReport(e, "Failed to Generate Report", scriptName, "GenerateReport", driver);
			} else {
				Assert.assertTrue(false);
			}

		}
	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheet name provided
		return Utility.getData("RatesGridAvailabilityTabValidat", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		// driver.quit();
	}
}
