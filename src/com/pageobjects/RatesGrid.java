package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;

import com.innroad.inncenter.pages.RateGridPage;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_RateGrid;
import com.innroad.inncenter.properties.OR_RatesGrid;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Inventory;
import com.innroad.inncenter.webelements.Elements_RatesGrid;

public class RatesGrid {

	public static Logger logger = Logger.getLogger("NightlyRate");

	/*
	 * #########################################################################
	 * #########################################################################
	 * ######################## ' Method Name: <clickForRateGridCalender> '
	 * Description: <This method will Click and open Rate Grid Date Calender > '
	 * Input parameters: < WebDriver driver > ' Return value: <void> ' Created
	 * By: <Muhammad Haider> ' Created On: <MM/dd/yyyy> <07/01/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void clickForRateGridCalender(WebDriver driver, ArrayList<String> test_steps) {

		Elements_RatesGrid element = new Elements_RatesGrid(driver);
		waitForSweetLoading(driver);

		Wait.explicit_wait_visibilityof_webelement_120(element.RATE_GRID_CALENDAR_ICON, driver);
		Wait.explicit_wait_elementToBeClickable(element.RATE_GRID_CALENDAR_ICON, driver);
		element.RATE_GRID_CALENDAR_ICON.click();

		try {
			Wait.explicit_wait_10sec(driver.findElement(By.xpath("//div[@class='DayPicker-Caption']/div")), driver);
		} catch (Exception e) {
			Utility.clickThroughJavaScript(driver, element.RATE_GRID_CALENDAR_ICON);
		}

		test_steps.add("Rate Grid Calender Icon Clicked");
		Utility.app_logs.info("Rate Grid Calender Icon Clicked");

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectDateFromDatePicker> ' Description: < Select Date
	 * From HTML DatePiker or calendar, prerequisite : calendar should be
	 * displayed > ' Input parameters: < WebDriver driver,String desiredDate,
	 * String format > ' Return value: <void> ' Created By: <Muhammad Haider> '
	 * ' Created On: <MM/dd/yyyy> <07/01/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String selectDateFromDatePicker(WebDriver driver, String desiredDate, String desiredDateFormat,
			ArrayList<String> test_steps) {
		logger.info("==========SELECT DATE FROM DATE PICKER==========");
		test_steps.add("==========SELECT DATE FROM DATE PICKER==========");
		String selectedMonthYearPath = "//div[@class='DayPicker-Caption']/div";
		String nextMonthBtnPath = "//button[contains(@class,'FloatRight')]";
		String previousMonthBtnPath = "//button[contains(@class,'FloatLeft')]";

		String selectedMonthYearTxt = driver.findElement(By.xpath(selectedMonthYearPath)).getText();
		logger.info("Found Mounth Year : " + selectedMonthYearTxt);
		String foundYear = Utility.parseDate(selectedMonthYearTxt, "MMMMM yyyy", "yyyy");
		logger.info("Parsed Year : " + foundYear);
		String foundMonth = Utility.parseDate(selectedMonthYearTxt, "MMMMM yyyy", "MM");
		logger.info("Parsed Month : " + foundMonth);

		logger.info("Desired Date : " + desiredDate);
		String desiredDay = Utility.parseDate(desiredDate, desiredDateFormat, "dd");
		logger.info("Parsed Desired Day : " + desiredDay);
		String desiredMonth = Utility.parseDate(desiredDate, desiredDateFormat, "MM");
		logger.info("Parsed Desired Month : " + desiredMonth);
		String desiredYear = Utility.parseDate(desiredDate, desiredDateFormat, "yyyy");
		logger.info("Parsed Desired Year : " + desiredYear);

		logger.info("===========CHECKING YEAR CONDITION==========");
		if (!foundYear.equals(desiredYear)) {
			int foundYearIntParssed = Integer.parseInt(foundYear);
			int desiredYearIntParssed = Integer.parseInt(desiredYear);

			if (foundYearIntParssed < desiredYearIntParssed) {
				logger.info("Found Year : " + foundYearIntParssed + " is Less than " + "Desired Year : "
						+ desiredYearIntParssed);
				while (foundYearIntParssed != desiredYearIntParssed) {
					Utility.clickThroughJavaScript(driver, driver.findElement(By.xpath(nextMonthBtnPath)));
					logger.info("NEXT ARROW CLICKED FOR YEAR ");
					selectedMonthYearTxt = driver.findElement(By.xpath(selectedMonthYearPath)).getText();
					foundYear = Utility.parseDate(selectedMonthYearTxt, "MMMMM yyyy", "yyyy");
					foundMonth = Utility.parseDate(selectedMonthYearTxt, "MMMMM yyyy", "MM");
					foundYearIntParssed = Integer.parseInt(foundYear);
					logger.info("NEW FOUND YEAR : " + foundYearIntParssed);
				}
			} else if (foundYearIntParssed > desiredYearIntParssed) {
				logger.info("Found Year : " + foundYearIntParssed + " is Greater than " + "Desired Year : "
						+ desiredYearIntParssed);
				while (foundYearIntParssed != desiredYearIntParssed) {
					Utility.clickThroughJavaScript(driver, driver.findElement(By.xpath(previousMonthBtnPath)));
					logger.info("PREVIOUS ARROW CLICKED FOR YEAR ");
					selectedMonthYearTxt = driver.findElement(By.xpath(selectedMonthYearPath)).getText();
					foundYear = Utility.parseDate(selectedMonthYearTxt, "MMMMM yyyy", "yyyy");
					foundMonth = Utility.parseDate(selectedMonthYearTxt, "MMMMM yyyy", "MM");
					foundYearIntParssed = Integer.parseInt(foundYear);
					logger.info("NEW FOUND YEAR : " + foundYearIntParssed);
				}
			}
		}

		logger.info("===========CHECKING MONTH CONDITION==========");

		if (!foundMonth.equals(desiredMonth)) {
			int foundMonthIntParssed = Integer.parseInt(foundMonth);
			int desiredMonthIntParssed = Integer.parseInt(desiredMonth);

			if (foundMonthIntParssed < desiredMonthIntParssed) {
				logger.info("Found Month : " + foundMonthIntParssed + " is Less than " + "Desired Month : "
						+ desiredMonthIntParssed);
				while (foundMonthIntParssed != desiredMonthIntParssed) {
					Utility.clickThroughJavaScript(driver, driver.findElement(By.xpath(nextMonthBtnPath)));
					logger.info("NEXT ARROW CLICKED FOR Month ");
					selectedMonthYearTxt = driver.findElement(By.xpath(selectedMonthYearPath)).getText();
					foundYear = Utility.parseDate(selectedMonthYearTxt, "MMMMM yyyy", "yyyy");
					foundMonth = Utility.parseDate(selectedMonthYearTxt, "MMMMM yyyy", "MM");
					foundMonthIntParssed = Integer.parseInt(foundMonth);
					logger.info("NEW FOUND MONTH : " + foundMonthIntParssed);
				}
			} else if (foundMonthIntParssed > desiredMonthIntParssed) {
				logger.info("Found Month : " + foundMonthIntParssed + " is Greater than " + "Desired Month : "
						+ desiredMonthIntParssed);
				while (foundMonthIntParssed != desiredMonthIntParssed) {
					Utility.clickThroughJavaScript(driver, driver.findElement(By.xpath(previousMonthBtnPath)));
					logger.info("PREVIOUS ARROW CLICKED FOR Month ");
					selectedMonthYearTxt = driver.findElement(By.xpath(selectedMonthYearPath)).getText();
					foundYear = Utility.parseDate(selectedMonthYearTxt, "MMMMM yyyy", "yyyy");
					foundMonth = Utility.parseDate(selectedMonthYearTxt, "MMMMM yyyy", "MM");
					foundMonthIntParssed = Integer.parseInt(foundMonth);
					logger.info("NEW FOUND MONTH : " + foundMonthIntParssed);
				}
			}
		}

		logger.info("===========SELECTING DESIRED DAY==========");
		// div[@aria-label='Fri May 08 2020']

		driver.findElement(By.xpath(
				"//div[@aria-label='" + Utility.parseDate(desiredDate, desiredDateFormat, "EE MMM dd yyyy") + "']"))
				.click();

		test_steps.add("Selected Date : " + desiredDate);
		logger.info("==========DATE SELECTED FROM DATE PICKER==========");
		test_steps.add("==========DATE SELECTED FROM DATE PICKER==========");

		return Utility.getCustomDate(desiredDate, desiredDateFormat, desiredDateFormat, 19);
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ######################## ' Method Name:
	 * <verifySelectedDateOnDateRangeCalendar> ' Description: <This method will
	 * Verify Selected Date on Date Rage Calendar > ' Input parameters: <
	 * WebDriver driver,String startDate, String endDate,String format,
	 * ArrayList<String> test_steps > ' Return value: <void> ' Created By:
	 * <Muhammad Haider> ' Created On: <MM/dd/yyyy> <07/01/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifySelectedDateOnDateRangeCalendar(WebDriver driver, String startDate, String endDate, String format,
			ArrayList<String> test_steps) {
		Elements_RatesGrid element = new Elements_RatesGrid(driver);

		String expectedDate = Utility.parseDate(startDate, format, "MMM d") + " - "
				+ Utility.parseDate(endDate, format, "MMM d");

		String foundDate = element.RATE_GRID_CALENDAR_ICON.getText();
		StringTokenizer str = new StringTokenizer(foundDate, "-");
		String actualDate = Utility.parseDate(str.nextToken().trim(), "MMM d", "MMM d") + " - "
				+ Utility.parseDate(str.nextToken().trim(), "MMM d", "MMM d");

		assertEquals(actualDate, expectedDate, "Failed To Verify Selected Date on Date Range Calendar");
		logger.info("Successfully Verified Selected Date : " + actualDate);
		test_steps.add("Successfully Verified Selected Date : " + actualDate);
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ######################## ' Method Name: <verifySelectedDateOnHeader> '
	 * Description: <This method will Verify Selected Date on Occupancy Table
	 * Header > ' Input parameters: < WebDriver driver,String startDate, String
	 * endDate,String format, ArrayList<String> test_steps > ' Return value:
	 * <void> ' Created By: <Muhammad Haider> ' Created On: <MM/dd/yyyy>
	 * <07/01/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifySelectedDateOnHeader(WebDriver driver, String startDate, String endDate, String format,
			ArrayList<String> test_steps) {
		Elements_RatesGrid element = new Elements_RatesGrid(driver);

		String startDateYear = Utility.parseDate(startDate, format, "yyyy");
		logger.info("Start Date Year : " + startDateYear);

		String endDateYear = Utility.parseDate(endDate, format, "yyyy");
		logger.info("End Date Year : " + endDateYear);

		String startDateMonth = Utility.parseDate(startDate, format, "MM");
		logger.info("Start Date Month : " + startDateMonth);

		String endtDateMonth = Utility.parseDate(endDate, format, "MM");
		logger.info("End Date Month : " + endtDateMonth);

		if (startDateYear == endDateYear) {

			if (startDateMonth == endtDateMonth) {
				String foundDate = element.DATE_FROM_GRID_HEADER.get(0).getText();
				assertEquals(Utility.parseDate(foundDate, "MMM, yyyy", "MMM, yyyy"),
						Utility.parseDate(endDate, format, "MMM, yyyy"), "Failed To Verify Grid Header Date");
				logger.info("Successfully Verified Selected Date From Grid Header : " + foundDate);
				test_steps.add("Successfully Verified Selected Date From Grid Header : " + foundDate);
			} else if (Integer.parseInt(startDateMonth) < Integer.parseInt(endtDateMonth)) {

				String foundStartMonth = element.DATE_FROM_GRID_HEADER.get(0).getText();
				assertEquals(Utility.parseDate(foundStartMonth, "MMM, yyyy", "MMM, yyyy"),
						Utility.parseDate(startDate, format, "MMM, yyyy"), "Failed To Verify Grid Header Date");
				logger.info("Successfully Verified Selected Start Date From Grid Header : " + foundStartMonth);
				test_steps.add("Successfully Verified Selected Start Date From Grid Header : " + foundStartMonth);

				String foundEndMonth = element.DATE_FROM_GRID_HEADER.get(1).getText();
				assertEquals(Utility.parseDate(foundEndMonth, "MMM, yyyy", "MMM, yyyy"),
						Utility.parseDate(endDate, format, "MMM, yyyy"), "Failed To Verify Grid Header Date");
				logger.info("Successfully Verified Selected End Date From Grid Header : " + foundEndMonth);
				test_steps.add("Successfully Verified Selected End Date From Grid Header : " + foundEndMonth);
			}

		} else if (Integer.parseInt(startDateYear) < Integer.parseInt(endDateYear)) {
			String foundStartMonth = element.DATE_FROM_GRID_HEADER.get(0).getText();
			assertEquals(Utility.parseDate(foundStartMonth, "MMM, yyyy", "MMM, yyyy"),
					Utility.parseDate(startDate, format, "MMM, yyyy"), "Failed To Verify Grid Header Date");
			logger.info("Successfully Verified Selected Start Date From Grid Header : " + foundStartMonth);
			test_steps.add("Successfully Verified Selected Start Date From Grid Header : " + foundStartMonth);

			String foundEndMonth = element.DATE_FROM_GRID_HEADER.get(1).getText();
			assertEquals(Utility.parseDate(foundEndMonth, "MMM, yyyy", "MMM, yyyy"),
					Utility.parseDate(endDate, format, "MMM, yyyy"), "Failed To Verify Grid Header Date");
			logger.info("Successfully Verified Selected End Date From Grid Header : " + foundEndMonth);
			test_steps.add("Successfully Verified Selected End Date From Grid Header : " + foundEndMonth);
		}

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ######################## ' Method Name: <verifyNoOfColumnsRateGrid> '
	 * Description: <This method will Verify No Of Columns size and verify
	 * Visibility of each column > ' Input parameters: < WebDriver driver,
	 * ArrayList<String> test_steps > ' Return value: <void> ' Created By:
	 * <Muhammad Haider> ' Created On: <MM/dd/yyyy> <07/01/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifyNoOfColumnsRateGrid(WebDriver driver, ArrayList<String> test_steps) {
		Elements_RatesGrid element = new Elements_RatesGrid(driver);
		int foundFirstSize = element.TOTAL_OCCUPANCY_TABLE_DATE_COLUMNS.size();
		int foundSecondSize = element.ROOMCLASSES_TABLE_DATE_COLUMNS.size();

		assertEquals(foundFirstSize, 20, "Failed To Verify Occupacy Table Date Column Size");
		test_steps.add("Successfully Verified Occupacy Table Date Column Size : " + foundFirstSize);
		logger.info("Successfully Verified Occupacy Table Date Column Size : " + foundFirstSize);

		for (int i = 0; i < foundFirstSize; i++) {
			assertTrue(element.TOTAL_OCCUPANCY_TABLE_DATE_COLUMNS.get(i).isDisplayed(),
					"Failed To Verify Occupacy Table Date Column Displayed");
			test_steps.add("Successfully Verified Occupacy Table Date Column Displayed : " + (i + 1));
			logger.info("Successfully Verified Occupacy Table Date Column Displayed : " + (i + 1));
		}

		assertEquals(foundSecondSize, 20, "Failed To Verify RoomClass Table Date Column Size");
		test_steps.add("Successfully Verified RoomClass Table Date Column Size : " + foundFirstSize);
		logger.info("Successfully Verified RoomClass Table Date Column Size : " + foundFirstSize);

		for (int i = 0; i < foundSecondSize; i++) {
			assertTrue(element.ROOMCLASSES_TABLE_DATE_COLUMNS.get(i).isDisplayed(),
					"Failed To Verify RoomClass Table Date Column Displayed");
			test_steps.add("Successfully Verified RoomClass Table Date Column Displayed : " + (i + 1));
			logger.info("Successfully Verified RoomClass Table Date Column Displayed : " + (i + 1));
		}
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ######################## ' Method Name: <waitForSweetLoading> '
	 * Description: <This method will Wait For Sweet Loading > ' Input
	 * parameters: < WebDriver driver > ' Return value: <void> ' Created By:
	 * <Muhammad Haider> ' Created On: <MM/dd/yyyy> <07/01/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void waitForSweetLoading(WebDriver driver) {
		try {
			Wait.waitForElementToBeGone(driver,
					driver.findElement(By.xpath("//div[@class='sweet-loading text-center d-block']")), 5);
		} catch (Exception e) {
			Utility.app_logs.info("No Sweet Loading");
		}
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ######################## ' Method Name: <clickRateGridAddRatePlan> '
	 * Description: <This method will Click Add Rate Plan on Rate Grid > ' Input
	 * parameters: < WebDriver driver > ' Return value: <ArrayList<String>> '
	 * Created By: <Muhammad Haider> ' Created On: <MM/dd/yyyy> <07/02/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> clickRateGridAddRatePlan(WebDriver driver) {

		Elements_RatesGrid element = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, RateGridPage.ADD_RATE_PLAN);
		Wait.waitForElementToBeVisibile(By.xpath(RateGridPage.ADD_RATE_PLAN), driver);
		Wait.waitForElementToBeClickable(By.xpath(RateGridPage.ADD_RATE_PLAN), driver);
		element.ADD_RATE_PLAN.click();
		testSteps.add("RateGrid Add Rate Plan clicked");
		logger.info("RateGrid Add Rate Plan clicked");
		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ######################## ' Method Name: <clickRateGridAddRatePlan> '
	 * Description: <This method will Select Option Precondition : Add Rate Plan
	 * Already Clicked and Options Are Visible > ' Input parameters: < WebDriver
	 * driver > ' Return value: <ArrayList<String>> ' Created By: <Muhammad
	 * Haider> ' Created On: <MM/dd/yyyy> <07/02/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> clickRateGridAddRatePlanOption(WebDriver driver, String option) {

		ArrayList<String> testSteps = new ArrayList<>();
		String optionPath = "(//li//a[contains(text(),'" + option + "')])[1]";
		Wait.WaitForElement(driver, optionPath);
		Wait.waitForElementToBeVisibile(By.xpath(optionPath), driver);
		Wait.waitForElementToBeClickable(By.xpath(optionPath), driver);
		WebElement rateGirdOption = driver.findElement(By.xpath(optionPath));
		rateGirdOption.click();
		testSteps.add("RateGrid Add Rate Plan option clicked : " + option);
		logger.info("RateGrid Add Rate Plan option clicked : " + option);
		return testSteps;
	}

	public static Logger ratesGridLogger = Logger.getLogger("RatesGrid");

	// Added By Adhnan 7/1/2020
	public ArrayList<String> verifyHeadingDates(WebDriver driver, String currentDate, String format, String timeZone,
			ArrayList<String> getTestSteps) throws ParseException, InterruptedException {
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.HEADER_DATES_ROW_DAY_NUMBER);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.HEADER_DATES_ROW_DAY_NUMBER), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.HEADER_DATES_ROW_DAY_NUMBER), driver);
		String expectedDayNumber = Utility.addDate(0, format, currentDate, "d", timeZone);
		String expectedWeekDay = Utility.addDate(0, format, currentDate, "E", timeZone);
		String foundDayNumber = null;
		String foundWeekDay = null;
		for (int i = 0; i < 20; i++) {
			ratesGridLogger.info("Date Column: " + i);
			ratesGridLogger.info("Expexted Day Number : " + expectedDayNumber);
			ratesGridLogger.info("Expexted Week Day : " + expectedWeekDay);
			Utility.ScrollToElement(ratesGrid.HEADER_DATES_ROW_DAY_NUMBER.get(i), driver);
			foundDayNumber = ratesGrid.HEADER_DATES_ROW_DAY_NUMBER.get(i).getText();
			foundWeekDay = ratesGrid.HEADER_DATES_ROW_WEEKDAY.get(i).getText();
			ratesGridLogger.info("Found Day Number : " + foundDayNumber);
			ratesGridLogger.info("Found Week Day : " + foundWeekDay);
			expectedDayNumber = Utility.addDate(i, format, currentDate, "d", timeZone);
			expectedWeekDay = Utility.addDate(i, format, currentDate, "E", timeZone);
			assertEquals(foundDayNumber, expectedDayNumber, "Failed: Day number missmatched");
			assertEquals(foundWeekDay, expectedWeekDay, "Failed: Week Day missmatched");
		}

		return getTestSteps;
	}

	public ArrayList<String> clickOnAvailability(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();

		Elements_Inventory element = new Elements_Inventory(driver);

		Wait.WaitForElement(driver, OR.CLICK_ON_AVAILABILITY);
		Wait.waitForElementToBeClickable(By.xpath(OR.CLICK_ON_AVAILABILITY), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.CLICK_ON_AVAILABILITY), driver);
		element.clickOnAvailability.click();
		testSteps.add("Click on Availability");

		return testSteps;
	}

	public ArrayList<String> clickOnBulkUpdate(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.BULK_UPDATE_BUTTON);
		Wait.waitForElementToBeVisibile(By.xpath(OR.BULK_UPDATE_BUTTON), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.BULK_UPDATE_BUTTON), driver);
		element.bulkUpdateButton.click();
		testSteps.add("Click bulk update button");

		return testSteps;
	}

	public String getBulkUpdateButtonText(WebDriver driver) {

		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.BULK_UPDATE_BUTTON);
		Wait.waitForElementToBeVisibile(By.xpath(OR.BULK_UPDATE_BUTTON), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.BULK_UPDATE_BUTTON), driver);
		String getbulkUpdateText = element.bulkUpdateButton.getText();

		return getbulkUpdateText;
	}

	public String getDayTabText(WebDriver driver) {
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.DAY_TAB_TEXT);
		Wait.waitForElementToBeVisibile(By.xpath(OR.DAY_TAB_TEXT), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.DAY_TAB_TEXT), driver);
		String dayTabText = element.dayTabText.getText();

		return dayTabText;
	}

	public String getTotalOccupancyTabText(WebDriver driver) {
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.TOTAL_OCCUPANCY_TAB_TEXT);
		Wait.waitForElementToBeVisibile(By.xpath(OR.TOTAL_OCCUPANCY_TAB_TEXT), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.TOTAL_OCCUPANCY_TAB_TEXT), driver);
		String totalOccupancyTabText = element.totalOccupancyTabText.getText();

		return totalOccupancyTabText;
	}

	public String getPaceVsLastYearTabText(WebDriver driver) {
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.PACE_VS_LAST_YEAR_TAB_TEXT);
		Wait.waitForElementToBeVisibile(By.xpath(OR.PACE_VS_LAST_YEAR_TAB_TEXT), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.PACE_VS_LAST_YEAR_TAB_TEXT), driver);
		String paceVsLastYearTabText = element.paceVsLastYearTabText.getText();

		return paceVsLastYearTabText;
	}

	public String getAddRatePlanButtonText(WebDriver driver) {
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.ADD_RATE_PLAN_BUTTON);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ADD_RATE_PLAN_BUTTON), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.ADD_RATE_PLAN_BUTTON), driver);
		String addRatePlanButton = element.addRatePlanButton.getText();

		return addRatePlanButton;
	}

	public ArrayList<String> clickOnAddRatePlan(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.ADD_RATE_PLAN_BUTTON);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ADD_RATE_PLAN_BUTTON), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.ADD_RATE_PLAN_BUTTON), driver);
		element.addRatePlanButton.click();
		testSteps.add("Click add rate plan button");

		return testSteps;
	}

	public String getNightlyRatePlanText(WebDriver driver) {
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.NIGHTLY_RATE_PLAN);
		Wait.waitForElementToBeVisibile(By.xpath(OR.NIGHTLY_RATE_PLAN), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.NIGHTLY_RATE_PLAN), driver);
		String nightlyRatePlan = element.nightlyRatePlan.getText();

		return nightlyRatePlan;
	}

	public ArrayList<String> clickNightlyRatePlan(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.NIGHTLY_RATE_PLAN);
		Wait.waitForElementToBeVisibile(By.xpath(OR.NIGHTLY_RATE_PLAN), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.NIGHTLY_RATE_PLAN), driver);
		element.nightlyRatePlan.click();
		testSteps.add("Click on nightly rate plan");

		return testSteps;
	}

	public String getDrivedRatePlanText(WebDriver driver) {
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.DERIVED_RATE_PLAN);
		Wait.waitForElementToBeVisibile(By.xpath(OR.DERIVED_RATE_PLAN), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.DERIVED_RATE_PLAN), driver);
		String drivedRatePlan = element.drivedRatePlan.getText();

		return drivedRatePlan;
	}

	public ArrayList<String> clickDrivedRatePlan(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.DERIVED_RATE_PLAN);
		Wait.waitForElementToBeVisibile(By.xpath(OR.DERIVED_RATE_PLAN), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.DERIVED_RATE_PLAN), driver);
		element.drivedRatePlan.click();
		testSteps.add("Click on drived rate plan");

		return testSteps;
	}

	public String getPackageRatePlanText(WebDriver driver) {
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.PACKAGE_RATE_PLAN);
		Wait.waitForElementToBeVisibile(By.xpath(OR.PACKAGE_RATE_PLAN), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.PACKAGE_RATE_PLAN), driver);
		String packageRatePlan = element.packageRatePlan.getText();

		return packageRatePlan;
	}

	public ArrayList<String> clickPackageRatePlan(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.PACKAGE_RATE_PLAN);
		Wait.waitForElementToBeVisibile(By.xpath(OR.PACKAGE_RATE_PLAN), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.PACKAGE_RATE_PLAN), driver);
		element.packageRatePlan.click();
		testSteps.add("Click on pakage rate plan");

		return testSteps;
	}

	public String getIntervalRatePlanText(WebDriver driver) {
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.INTERVAL_RATE_PLAN);
		Wait.waitForElementToBeVisibile(By.xpath(OR.INTERVAL_RATE_PLAN), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.INTERVAL_RATE_PLAN), driver);
		String intervalRatePlan = element.intervalRatePlan.getText();

		return intervalRatePlan;
	}

	public ArrayList<String> clickIntervalRatePlan(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.INTERVAL_RATE_PLAN);
		Wait.waitForElementToBeVisibile(By.xpath(OR.INTERVAL_RATE_PLAN), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.INTERVAL_RATE_PLAN), driver);
		element.intervalRatePlan.click();
		testSteps.add("Click interval rate plan");

		return testSteps;
	}

	public String getRatesBulkUpdateText(WebDriver driver) {
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.SELECT_RATES_BULK_UPDATE);
		Wait.waitForElementToBeVisibile(By.xpath(OR.SELECT_RATES_BULK_UPDATE), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.SELECT_RATES_BULK_UPDATE), driver);
		String ratesBulkUpdate = element.selectRatesBulkUpdate.getText();

		return ratesBulkUpdate;
	}

	public ArrayList<String> clickRatesBulkUpdate(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.SELECT_RATES_BULK_UPDATE);
		Wait.waitForElementToBeVisibile(By.xpath(OR.SELECT_RATES_BULK_UPDATE), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.SELECT_RATES_BULK_UPDATE), driver);
		element.selectRatesBulkUpdate.click();
		testSteps.add("Click Rates in bulk update");

		return testSteps;
	}

	public String getRulesBulkUpdateText(WebDriver driver) {
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.SELECT_RULES_BULK_UPDATE);
		Wait.waitForElementToBeVisibile(By.xpath(OR.SELECT_RULES_BULK_UPDATE), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.SELECT_RULES_BULK_UPDATE), driver);
		String rulesBulkUpdate = element.selectRulesBulkUpdate.getText();

		return rulesBulkUpdate;
	}

	public ArrayList<String> clickRulesBulkUpdate(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.SELECT_RULES_BULK_UPDATE);
		Wait.waitForElementToBeVisibile(By.xpath(OR.SELECT_RULES_BULK_UPDATE), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.SELECT_RULES_BULK_UPDATE), driver);
		element.selectRulesBulkUpdate.click();
		testSteps.add("Click rules in bulk update");

		return testSteps;
	}

	public String getAvailabilityBulkUpdateText(WebDriver driver) {
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.SELECT_AVAILABILITY_BULK_UPDATE);
		Wait.waitForElementToBeVisibile(By.xpath(OR.SELECT_AVAILABILITY_BULK_UPDATE), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.SELECT_AVAILABILITY_BULK_UPDATE), driver);
		String availabilityText = element.selectAvailability.getText();

		return availabilityText;
	}

	public ArrayList<String> selectAvailabilityFromBulkUpdate(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.SELECT_AVAILABILITY_BULK_UPDATE);
		Wait.waitForElementToBeVisibile(By.xpath(OR.SELECT_AVAILABILITY_BULK_UPDATE), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.SELECT_AVAILABILITY_BULK_UPDATE), driver);
		element.selectAvailability.click();
		testSteps.add("Availability selected");

		return testSteps;
	}

	public ArrayList<String> closeOpendTabInMainMenu(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.MAIN_MENU_CLOSE_BUTTON);
		Wait.waitForElementToBeVisibile(By.xpath(OR.MAIN_MENU_CLOSE_BUTTON), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.MAIN_MENU_CLOSE_BUTTON), driver);
		element.mainMenueCloseButton.click();
		testSteps.add("Close opened tab");

		return testSteps;
	}

	public String getNewRatePlanTabTitleText(WebDriver driver) {
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.NEW_RATE_PLAN_TAB_TITLE);
		Wait.waitForElementToBeVisibile(By.xpath(OR.NEW_RATE_PLAN_TAB_TITLE), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.NEW_RATE_PLAN_TAB_TITLE), driver);
		String nightlyRatePlanTabTitle = element.newRatePlanTabTitle.getText();

		return nightlyRatePlanTabTitle;
	}

	public ArrayList<String> closeRateBulkUpdatePopup(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.CLOSE_RATE_BULK_UPDATE_POPUP);
		Wait.waitForElementToBeVisibile(By.xpath(OR.CLOSE_RATE_BULK_UPDATE_POPUP), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.CLOSE_RATE_BULK_UPDATE_POPUP), driver);
		element.closeRateBulkUpdatePopup.click();
		testSteps.add("Close rate bulk update popup");

		return testSteps;
	}

	public String getBulkUpdateHeaderTitle(WebDriver driver) {
		Elements_Inventory element = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.BULK_UPDATE_HEADER);
		Wait.waitForElementToBeVisibile(By.xpath(OR.BULK_UPDATE_HEADER), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.BULK_UPDATE_HEADER), driver);
		String heading = element.bulkUpdateHeader.getText();

		return heading;
	}

	// Added By Adhnan 7/1/2020
	public ArrayList<String> clickCalendarArrow(WebDriver driver, String arrow) throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<String>();
		String xpath = "//span[@class='" + arrow + "-arrow']//parent::button";
		Wait.WaitForElement(driver, xpath);
		Wait.waitForElementToBeClickable(By.xpath(xpath), driver);
		Wait.waitForElementToBeVisibile(By.xpath(xpath), driver);
		WebElement arrowButton = driver.findElement(By.xpath(xpath));
		Utility.ScrollToElement(arrowButton, driver);
		arrowButton.click();
		testSteps.add("Click " + arrow + " arrow of Calendar");
		ratesGridLogger.info("Click " + arrow + " arrow of Calendar");

		return testSteps;
	}

	// Added By Adhnan 7/1/2020
	public ArrayList<String> calculateMonthRange(WebDriver driver, String currentDate, String format, String timeZone)
			throws ParseException, InterruptedException {
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.HEADER_DATES_ROW_DAY_NUMBER);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.HEADER_DATES_ROW_DAY_NUMBER), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.HEADER_DATES_ROW_DAY_NUMBER), driver);
		String monthValue = Utility.addDate(0, format, currentDate, "MMMM, yyyy", timeZone);
		String nextmonthValue = null;
		ArrayList<String> monthRange = new ArrayList<String>();
		monthRange.add(monthValue);
		ratesGridLogger.info("Current month : " + monthValue);
		for (int i = 0; i < 20; i++) {
			nextmonthValue = Utility.addDate(i, format, currentDate, "MMMM, yyyy", timeZone);
			if (!monthValue.equals(nextmonthValue)) {
				monthValue = nextmonthValue;
				monthRange.add(monthValue);
				ratesGridLogger.info("Next month : " + monthValue);
			}
		}
		return monthRange;
	}

	// Added By Adhnan 7/1/2020
	public void verifyMonthRange(WebDriver driver, ArrayList<String> monthRange)
			throws ParseException, InterruptedException {
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.DATE_RANGE_MONTH_YEAR);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.DATE_RANGE_MONTH_YEAR), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.DATE_RANGE_MONTH_YEAR), driver);
		String expectedMonth = monthRange.get(0);
		String foundMonth = null;
		for (int i = 0; i < ratesGrid.DATE_RANGE_MONTH_YEAR.size(); i++) {
			ratesGridLogger.info("Month : " + i);
			expectedMonth = monthRange.get(i);
			ratesGridLogger.info("Expexted month : " + expectedMonth);
			Utility.ScrollToElement(ratesGrid.HEADER_DATES_ROW_DAY_NUMBER.get(i), driver);
			foundMonth = ratesGrid.DATE_RANGE_MONTH_YEAR.get(i).getText();
			ratesGridLogger.info("Found month : " + foundMonth);
			assertEquals(foundMonth, expectedMonth, "Failed:month missmatched");
		}
	}

	// Added By Adhnan 7/1/2020
	public ArrayList<String> clickCalendar(WebDriver driver) throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.DATE_RANGE_CALENDAR);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.DATE_RANGE_CALENDAR), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.DATE_RANGE_CALENDAR), driver);
		Utility.ScrollToElement(ratesGrid.DATE_RANGE_CALENDAR, driver);
		ratesGrid.DATE_RANGE_CALENDAR.click();
		testSteps.add("Click Date Range Calendar");
		ratesGridLogger.info("Click date range Calendar");
		Wait.WaitForElement(driver, OR_RatesGrid.CALENDER_TODAY_BUTTON);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.CALENDER_TODAY_BUTTON), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.CALENDER_TODAY_BUTTON), driver);
		return testSteps;
	}

	// Added By Adhnan 7/1/2020
	public ArrayList<String> verifyTodayButtonExistInCalender(WebDriver driver, boolean click)
			throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.CALENDER_TODAY_BUTTON);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.CALENDER_TODAY_BUTTON), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.CALENDER_TODAY_BUTTON), driver);
		assertTrue(ratesGrid.CALENDER_TODAY_BUTTON.isDisplayed(), "Failed: Today, Button is not displayed");
		testSteps.add("Verified 'Today Button' is visible with in calendar");
		ratesGridLogger.info("Verified 'Today Button' is visible with in calendar");
		if (click) {
			Utility.ScrollToElement(ratesGrid.CALENDER_TODAY_BUTTON, driver);
			ratesGrid.CALENDER_TODAY_BUTTON.click();
			testSteps.add("Click 'Today Button' of Calendar");
			ratesGridLogger.info("Click 'Today Button' of Calendar");
		}
		return testSteps;
	}

	// Added By Adhnan 7/1/2020
	public ArrayList<String> selectCalendarNextDate(WebDriver driver, String nextDays) throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<String>();
		String xpath = "(//div[contains(@class,'selectedDays')]//following-sibling::div)[" + nextDays + "]";
		Wait.WaitForElement(driver, xpath);
		Wait.waitForElementToBeClickable(By.xpath(xpath), driver);
		Wait.waitForElementToBeVisibile(By.xpath(xpath), driver);
		WebElement nextDate = driver.findElement(By.xpath(xpath));
		Utility.ScrollToElement(nextDate, driver);
		nextDate.click();
		testSteps.add("Select '" + nextDays + "' next Day of selected Date of Calendar");
		ratesGridLogger.info("Select '" + nextDays + "' next Day of selected Date of Calendar");

		return testSteps;
	}

	// Added By Adhnan 7/1/2020
	public boolean getElementVisibility(WebDriver driver, WebElement element) throws InterruptedException {
		boolean visible = element.isDisplayed();
		ratesGridLogger.info("Element visibility : " + visible);
		return visible;
	}

	// Added By Adhnan 7/1/2020
	public boolean verifyTotalOccupancyLabelExist(WebDriver driver) {
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.TOTAL_OCCUPANCY_LABEL);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.TOTAL_OCCUPANCY_LABEL), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.TOTAL_OCCUPANCY_LABEL), driver);
		assertTrue(ratesGrid.TOTAL_OCCUPANCY_LABEL.isDisplayed(), "Failed: Total Occupancy Label not found");
		return ratesGrid.TOTAL_OCCUPANCY_LABEL.isDisplayed();
	}

	// Added By Adhnan 7/1/2020
	public boolean verifyPaceVsLastYearLabelExist(WebDriver driver) {

		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.PACE_VS_LAST_YEAR_LABEL);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.PACE_VS_LAST_YEAR_LABEL), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.PACE_VS_LAST_YEAR_LABEL), driver);
		assertTrue(ratesGrid.PACE_VS_LAST_YEAR_LABEL.isDisplayed(), "Failed: Pace Vs Last Year Label not found");

		return ratesGrid.PACE_VS_LAST_YEAR_LABEL.isDisplayed();
	}

	// Added By Adhnan 7/2/2020
	public void clickHeadingDate(WebDriver driver, int index, String date) throws InterruptedException {
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.HEADER_DATES_ROW_DAY_NUMBER);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.HEADER_DATES_ROW_DAY_NUMBER), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.HEADER_DATES_ROW_DAY_NUMBER), driver);
		Utility.ScrollToElement(ratesGrid.HEADER_DATES_ROW_DAY_NUMBER.get(index), driver);
		ratesGrid.HEADER_DATES_ROW_DAY_NUMBER.get(index).click();
		String xpath = "//h1[@class='undefined hide-mobile' and text()='" + date + " Insights']";
		ratesGridLogger.info(xpath);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-400)");
		Wait.WaitForElement(driver, xpath);
		Wait.waitForElementToBeVisibile(By.xpath(xpath), driver);
		assertTrue(driver.findElement(By.xpath(xpath)).isDisplayed(), "Failed: Month Date Insight side bar not appear");
	}

	// Added By Adhnan 7/1/2020
	public ArrayList<String> closeMonthDateIndightSideBar(WebDriver driver, String date, ArrayList<String> getTestSteps)
			throws InterruptedException {

		String xpath = "//h1[@class='undefined hide-mobile' and text()='" + date + " Insights']/i";
		Wait.WaitForElement(driver, xpath);
		Wait.waitForElementToBeClickable(By.xpath(xpath), driver);
		Wait.waitForElementToBeVisibile(By.xpath(xpath), driver);
		WebElement closeButton = driver.findElement(By.xpath(xpath));
		Utility.ScrollToElement(closeButton, driver);
		closeButton.click();
		getTestSteps.add("Click Close button of Insight Side Bar");
		ratesGridLogger.info("Click Close button of Insight Side Bar");
		getTestSteps.add(date + " Insight Side Bar successfully closed.");
		ratesGridLogger.info(date + " Insight Side Bar successfully closed.");
		return getTestSteps;
	}

	public void verifyRoomClasses(WebDriver driver, ArrayList<String> getRoomClasses) throws InterruptedException {
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.ROOM_CLASSES_NAMES);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.ROOM_CLASSES_NAMES), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.ROOM_CLASSES_NAMES), driver);
		String expectedRoomClass = null;
		String foundRoomClass = null;
		String previousRoomClass = null;
		for (int i = 0; i < getRoomClasses.size(); i++) {
			ratesGridLogger.info("Room Class " + (i + 1));
			expectedRoomClass = getRoomClasses.get(i);
			ratesGridLogger.info("Expected Room Class : " + expectedRoomClass);
			Utility.ScrollToElement(ratesGrid.ROOM_CLASSES_NAMES.get(i), driver);
			foundRoomClass = ratesGrid.ROOM_CLASSES_NAMES.get(i).getText();
			ratesGridLogger.info("Found Room Class : " + foundRoomClass);
			assertEquals(foundRoomClass, expectedRoomClass, "Failed: Room Class missmatched");
			if (previousRoomClass == null) {
				previousRoomClass = foundRoomClass;
			} else {
				if (!(previousRoomClass.charAt(0) <= foundRoomClass.charAt(0))) {
					// assertTrue(false,"Failed: Room Classes are not in
					// ascending order");
				}
			}
			previousRoomClass = foundRoomClass;

		}

	}

	public void expandRoomClass(WebDriver driver, int index) throws InterruptedException {
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.ROOM_CLASS_EXPAND_BUTTON);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.ROOM_CLASS_EXPAND_BUTTON), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.ROOM_CLASS_EXPAND_BUTTON), driver);
		Utility.ScrollToElement(ratesGrid.ROOM_CLASS_EXPAND_BUTTON.get(index), driver);
		try {
			ratesGrid.ROOM_CLASS_EXPAND_BUTTON.get(index).click();
		} catch (Exception e) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-400)");
			ratesGrid.ROOM_CLASS_EXPAND_BUTTON.get(index).click();
		}
	}

	public String getRoomClassDataValue(WebDriver driver, int index, String label) throws InterruptedException {
		String xpath = "(//div[contains(@class,'RoomClassHeader ')]//div[@class='roomClassName' and text()='" + label
				+ "']//parent::div//following-sibling::div/div[1])";
		Wait.WaitForElement(driver, xpath);
		Wait.waitForElementToBeClickable(By.xpath(xpath), driver);
		Wait.waitForElementToBeVisibile(By.xpath(xpath), driver);
		java.util.List<WebElement> labelValues = driver.findElements(By.xpath(xpath));
		Utility.ScrollToElement(labelValues.get(index), driver);
		return labelValues.get(index).getText();
	}

	public ArrayList<String> clickAndVerifySettingButton(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_RatesGrid element = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.SETTING_BUTTON_RATE_GRID);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.SETTING_BUTTON_RATE_GRID), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.SETTING_BUTTON_RATE_GRID), driver);
		assertEquals(element.settingButtonRateGrid.isEnabled(), true);
		testSteps.add("Setting button is clickable");
		ratesGridLogger.info("Setting button is clickable");
		element.settingButtonRateGrid.click();
		return testSteps;
	}

	public ArrayList<String> clickAndVerifyAddRatePlanButton(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_RatesGrid element = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.ADD_RATE_PLAN_BUTTON);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.ADD_RATE_PLAN_BUTTON), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.ADD_RATE_PLAN_BUTTON), driver);
		assertEquals(element.addRatePlanButton.isEnabled(), true);
		testSteps.add("Add rate plan button is clickable");
		ratesGridLogger.info("Add rate plan button is clickable");
		element.addRatePlanButton.click();
		return testSteps;
	}

	public ArrayList<String> clickAndVerifyBulkUpdateButton(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_RatesGrid element = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.BULK_UPDATE_BUTTON);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.BULK_UPDATE_BUTTON), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.BULK_UPDATE_BUTTON), driver);
		assertEquals(element.bulkUpdateButton.isEnabled(), true);
		testSteps.add("Bulk update button is clickable");
		ratesGridLogger.info("Bulk update button is clickable");
		element.bulkUpdateButton.click();
		return testSteps;
	}

	public ArrayList<String> verifyHeadingAvailabilitySettingMenu(WebDriver driver, String expectedHeading) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_RatesGrid element = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.HEADING_AVAILABILITY_SETTING);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.HEADING_AVAILABILITY_SETTING), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.HEADING_AVAILABILITY_SETTING), driver);
		String getHeadingText = element.headingAvailabilitySetting.getText();
		testSteps.add("Expected : " + expectedHeading);
		testSteps.add("Found : " + getHeadingText);
		assertEquals(getHeadingText, expectedHeading);
		testSteps.add("Verified heading is : " + getHeadingText);
		return testSteps;
	}

	public ArrayList<String> verifyHeadingRatesSettingMenu(WebDriver driver, String expectedHeading) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_RatesGrid element = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.HEADING_RATES_SETTING);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.HEADING_RATES_SETTING), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.HEADING_RATES_SETTING), driver);
		String getHeadingText = element.headingRatesSetting.getText();
		testSteps.add("Expected : " + expectedHeading);
		testSteps.add("Found : " + getHeadingText);
		assertEquals(getHeadingText, expectedHeading);
		testSteps.add("Verified heading is : " + getHeadingText);
		return testSteps;
	}

	public ArrayList<String> verifyAvailabilityToggleTextSettingMenu(WebDriver driver, String expectedText) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_RatesGrid element = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.AVAILABILITY_TOGGLE_TEXT);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.AVAILABILITY_TOGGLE_TEXT), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.AVAILABILITY_TOGGLE_TEXT), driver);
		String getText = element.availabiltyToggleText.getText();
		testSteps.add("Expected : " + expectedText);
		testSteps.add("Found : " + getText);
		assertEquals(getText, expectedText);
		testSteps.add("Verified text is : " + getText);
		return testSteps;
	}

	public ArrayList<String> verifyRatesToggleTextSettingMenu(WebDriver driver, String expectedText) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_RatesGrid element = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.RATES_TOGGLE_TEXT);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.RATES_TOGGLE_TEXT), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.RATES_TOGGLE_TEXT), driver);
		String getText = element.ratesToggleText.getText();
		testSteps.add("Expected : " + expectedText);
		testSteps.add("Found : " + getText);
		assertEquals(getText, expectedText);
		testSteps.add("Verified text is : " + getText);
		return testSteps;
	}

	public ArrayList<String> verifyToggleButtonAvailablity(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_RatesGrid element = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.SETTING_AVAILABILITY_TOGGLE_BUTTON);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.SETTING_AVAILABILITY_TOGGLE_BUTTON), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.SETTING_AVAILABILITY_TOGGLE_BUTTON), driver);
		if (element.settingAvailabilityToggleButton.isEnabled()) {
			testSteps.add("Toggle Button is verified");
			ratesGridLogger.info("Toggle Button is verified");

		} else {
			element.settingAvailabilityToggleButton.click();
			testSteps.add("Toggle Button is verified");
			ratesGridLogger.info("Toggle Button is verified");
		}
		return testSteps;
	}

	public ArrayList<String> verifyToggleButtonRates(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_RatesGrid element = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.SETTING_RATES_TOGGLE_BUTTON);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.SETTING_RATES_TOGGLE_BUTTON), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.SETTING_RATES_TOGGLE_BUTTON), driver);
		if (element.settingRatesToggleButton.isEnabled()) {
			testSteps.add("Toggle Button is verified");
			ratesGridLogger.info("Toggle Button is verified");

		} else {
			element.settingRatesToggleButton.click();
			testSteps.add("Toggle Button is verified");
			ratesGridLogger.info("Toggle Button is verified");
		}
		return testSteps;
	}

	// Added By Adhnan 7/3/2020
	public ArrayList<String> verifyDatesBackGroungColor(WebDriver driver, String weekendColor, String weekDayColor,
			ArrayList<String> getTestSteps) throws ParseException, InterruptedException {
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.HEADER_DATES_ROW_DAY_NUMBER);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.HEADER_DATES_ROW_DAY_NUMBER), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.HEADER_DATES_ROW_DAY_NUMBER), driver);
		Wait.WaitForElement(driver, OR_RatesGrid.HEADER_DATES_BACKGROUND_COLOR);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.HEADER_DATES_BACKGROUND_COLOR), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.HEADER_DATES_BACKGROUND_COLOR), driver);
		String color = null;
		String foundWeekDay = null;
		for (int i = 0; i < 20; i++) {
			ratesGridLogger.info("Date Column: " + i);
			Utility.ScrollToElement(ratesGrid.HEADER_DATES_ROW_DAY_NUMBER.get(i), driver);
			foundWeekDay = ratesGrid.HEADER_DATES_ROW_WEEKDAY.get(i).getText();
			ratesGridLogger.info("Found Week Day : " + foundWeekDay);
			if (foundWeekDay.equals("Fri") || foundWeekDay.equals("Sat")) {
				ratesGridLogger.info("Weekend Found");
				Utility.ScrollToElement(ratesGrid.HEADER_DATES_BACKGROUND_COLOR.get(i), driver);
				color = ratesGrid.HEADER_DATES_ROW_WEEKDAY.get(i).getCssValue("background-color");
				ratesGridLogger.info("Cell Color : " + color);
				color = ratesGrid.HEADER_DATES_BACKGROUND_COLOR.get(i).getCssValue("background-color");
				ratesGridLogger.info("Cell Color : " + color);

				assertEquals(color, weekendColor, "Failed: Week Day Color missmatched");
			} else {
				color = ratesGrid.HEADER_DATES_ROW_WEEKDAY.get(i).getCssValue("background-color");
				ratesGridLogger.info("Cell Color : " + color);
				color = ratesGrid.HEADER_DATES_BACKGROUND_COLOR.get(i).getCssValue("background-color");
				ratesGridLogger.info("Cell Color : " + color);
				assertEquals(color, weekDayColor, "Failed: Week Day Color missmatched");
			}
		}

		return getTestSteps;
	}

	// Added By Adhnan 7/7/2020
	public String getRoomStatus(WebDriver driver, int index, String source, String roomClassName)
			throws InterruptedException {
		String xpath = "//div[contains(@class,'RoomClassHeader ')]//div[@class='roomClassName' and text()='" + source
				+ "']//parent::div/following-sibling::div";
		Wait.WaitForElement(driver, xpath);
		Wait.waitForElementToBeClickable(By.xpath(xpath), driver);
		Wait.waitForElementToBeVisibile(By.xpath(xpath), driver);
		java.util.List<WebElement> labelValues = driver.findElements(By.xpath(xpath));
		Utility.ScrollToElement(labelValues.get(index), driver);
		String roomStatus = labelValues.get(index).getAttribute("class");
		if (roomStatus.contains("NoBlacked")) {
			roomStatus = "*";
		} else {
			roomStatus = "B";
		}
		if (roomStatus.equals("B")) {
			xpath = "(//div[contains(@class,'RoomClassHeader ')]//div[@class='roomClassName' and text()='"
					+ roomClassName + "']//parent::div/following-sibling::div)[" + (index + 1)
					+ "]/div[@class='Blackout']/span";
			Wait.WaitForElement(driver, xpath);
			Wait.waitForElementToBeClickable(By.xpath(xpath), driver);
			Wait.waitForElementToBeVisibile(By.xpath(xpath), driver);
			WebElement blackout = driver.findElement(By.xpath(xpath));
			Utility.ScrollToElement(blackout, driver);
			assertEquals(blackout.getText(), "B", "Failed: B is not visible under Percentage value of the room");
		}
		return roomStatus;
	}

	// Added By Adhnan 7/7/2020
	public ArrayList<String> verifyCalendarArrowDisplayed(WebDriver driver, String arrow) throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<String>();
		String xpath = "//span[@class='" + arrow + "-arrow']//parent::button";
		Wait.WaitForElement(driver, xpath);
		Wait.waitForElementToBeClickable(By.xpath(xpath), driver);
		Wait.waitForElementToBeVisibile(By.xpath(xpath), driver);
		WebElement arrowButton = driver.findElement(By.xpath(xpath));
		Utility.ScrollToElement(arrowButton, driver);
		assertTrue(arrowButton.isDisplayed(), "Failed: " + arrow + " arrow of Calendar not displaying");
		assertTrue(arrowButton.isEnabled(), "Failed: " + arrow + " arrow of Calendar not Enabled");
		testSteps.add("Verified " + arrow + " arrow of Calendar");
		ratesGridLogger.info("Verified " + arrow + " arrow of Calendar");

		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickSettingButton> ' Description: <Clicks The Settings
	 * Button > ' ' Input parameters: <WebDriver> ' Return: <ArrayList<String>>
	 * Created By: <Aqsa Manzoor> ' Created On: <30 June 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> clickSettingButton(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.explicit_wait_visibilityof_webelement(ratesGrid.RATESGRID_SETTING_BUTTON, driver);
		Utility.ScrollToElement(ratesGrid.RATESGRID_SETTING_BUTTON, driver);
		ratesGrid.RATESGRID_SETTING_BUTTON.click();
		test_steps.add("Rates Grid Setting Button Clicked");
		ratesGridLogger.info("Rates Grid Setting Button Clicked");
		Wait.explicit_wait_visibilityof_webelement(ratesGrid.RATESGRID_SETTING_CONTAINER, driver);
		assertEquals(ratesGrid.RATESGRID_SETTING_CONTAINER.isDisplayed(), true,
				"Failed:Setting Container didn't Display");

		test_steps.add("Rates Grid Setting Container Displayed");
		ratesGridLogger.info("Rates Grid Setting Container Displayed");
		return test_steps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickSettingButton> ' Description: <Clicks The Settings
	 * Button > ' ' Input parameters: <WebDriver> ' Return: <ArrayList<String>>
	 * Created By: <Aqsa Manzoor> ' Created On: <30 June 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> changeStateShowAdditionalAdultAdditionalChilToggalSettingContainer(WebDriver driver,
			boolean isToggleEnable) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		String toggleEnableColor = "rgba(62, 168, 244, 1)";
		String toggleDisableColor = "rgba(204, 204, 204, 1)";
		Wait.explicit_wait_visibilityof_webelement(ratesGrid.SHOW_ADDITIONALADULT_CHILD_TOGGLE, driver);
		Utility.ScrollToElement(ratesGrid.SHOW_ADDITIONALADULT_CHILD_TOGGLE, driver);
		if (isToggleEnable) {

			if (ratesGrid.SHOW_ADDITIONALADULT_CHILD_TOGGLE.getCssValue("background-color")
					.equals(toggleDisableColor)) {

				ratesGrid.SHOW_ADDITIONALADULT_CHILD_TOGGLE.click();
				Wait.wait5Second();
				assertEquals(ratesGrid.SHOW_ADDITIONALADULT_CHILD_TOGGLE.getCssValue("background-color"),
						toggleEnableColor, "Failed:Toggle didn't Enable");
			}
			test_steps.add("Rates Grid Setting: Show Additional Adul/Child Toggle Enable");
			ratesGridLogger.info("Rates Grid Setting: Show Additional Adul/Child Toggle Enable");
		} else {

			if (ratesGrid.SHOW_ADDITIONALADULT_CHILD_TOGGLE.getCssValue("background-color").equals(toggleEnableColor)) {

				ratesGrid.SHOW_ADDITIONALADULT_CHILD_TOGGLE.click();
				Wait.wait5Second();
				assertEquals(ratesGrid.SHOW_ADDITIONALADULT_CHILD_TOGGLE.getCssValue("background-color"),
						toggleDisableColor, "Failed:Toggle didn't Disable");
			}
			test_steps.add("Rates Grid Setting: Show Additional Adul/Child Toggle Disable");
			ratesGridLogger.info("Rates Grid Setting: Show Additional Adul/Child Toggle Disable");
		}
		return test_steps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickBulkUpdate> ' Description: <Clicks Bulk Update
	 * Button & Open Specific Type > ' ' Input parameters: <WebDriver,String> '
	 * Return: <ArrayList<String>> Created By: <Aqsa Manzoor> ' Created On: <30
	 * June 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> clickRateGridBulkUpdateRates(WebDriver driver) throws InterruptedException {
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_RatesGrid.rateGridBulkUpdateRates);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.rateGridBulkUpdateRates), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.rateGridBulkUpdateRates), driver);
		ratesGrid.rateGridBulkUpdateRates.click();
		testSteps.add("RateGrid Bulk Update Rates option clicked");
		ratesGridLogger.info("RateGrid Bulk Update Rates option clicked");
		return testSteps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectBulkUpdateRatesOption> ' Description: <Select the
	 * Option Given in Update Rate Options > ' ' Input parameters:
	 * <WebDriver,String> ' Return: <ArrayList<String>> Created By: <Aqsa
	 * Manzoor> ' Created On: <30 June 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> selectBulkUpdateRatesOption(WebDriver driver, String updateRateType)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);

		Map<Integer, String> updateRateOptionsDictionary = new HashMap<Integer, String>();
		updateRateOptionsDictionary.put(0, "EnterNewRate");
		updateRateOptionsDictionary.put(1, "UpdateEachRatebyValue");
		updateRateOptionsDictionary.put(2, "RemoveOverrides");

		int rateTypeIndex = -1;

		for (Entry<Integer, String> entry : updateRateOptionsDictionary.entrySet()) {
			if (entry.getValue().equalsIgnoreCase(updateRateType)) {
				System.out.println(entry.getKey());
				rateTypeIndex = entry.getKey();
			}
		}

		Utility.ScrollToElement(ratesGrid.UPDATERATES_OPTIONS.get(rateTypeIndex), driver);
		ratesGrid.UPDATERATES_OPTIONS.get(rateTypeIndex).click();

		return test_steps;

	}

	/*
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectBulkUpdateRatesOption> ' Description: <Select the
	 * Option Given in Update Rate Options > ' ' Input parameters:
	 * <WebDriver,String> ' Return: <ArrayList<String>> Created By: <Aqsa
	 * Manzoor> ' Created On: <30 June 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> updateRoomsByRoomClassToggle(WebDriver driver, boolean isToggleOn)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.UPDATERATE_UPDATERATEBYROOMCLASS_TOGGLE);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.UPDATERATE_UPDATERATEBYROOMCLASS_TOGGLE), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.UPDATERATE_UPDATERATEBYROOMCLASS_TOGGLE), driver);
		String toggleState = ratesGrid.UPDATERATE_UPDATERATEBYROOMCLASS_TOGGLE.getAttribute("aria-checked");
		if (isToggleOn && !Boolean.parseBoolean(toggleState)) {

			ratesGrid.UPDATERATE_UPDATERATEBYROOMCLASS_TOGGLE.click();
		} else if (!isToggleOn && Boolean.parseBoolean(toggleState)) {

			ratesGrid.UPDATERATE_UPDATERATEBYROOMCLASS_TOGGLE.click();
		}
		test_steps.add("Update By Room Class Toggle State:" + toggleState);
		ratesGridLogger.info("Update By Room Class Toggle State:" + toggleState);
		return test_steps;

	}

	/*
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectBulkUpdateRatesOption> ' Description: <Select the
	 * Option Given in Update Rate Options > ' ' Input parameters:
	 * <WebDriver,String> ' Return: <ArrayList<String>> Created By: <Aqsa
	 * Manzoor> ' Created On: <30 June 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> updateNightlyAdultChildRate(WebDriver driver, boolean isUpdateRoomClassByRateToggleOn,
			String numberofRoomClasses, String nightlyRate, String additionalAdult, String additionalChild)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		for (int i = 0; i < Integer.parseInt(numberofRoomClasses); i++) {
			Wait.WaitForElement(driver, OR_RatesGrid.UPDATE_RATE_NEWRATE_NIGHTLYRATE);
			Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.UPDATE_RATE_NEWRATE_NIGHTLYRATE), driver);
			Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.UPDATE_RATE_NEWRATE_NIGHTLYRATE), driver);
			ratesGrid.UPDATE_RATE_NEWRATE_NIGHTLYRATE.get(i).sendKeys(nightlyRate);
			ratesGrid.UPDATE_RATE_NEWRATE_ADDITIONALADULT.get(i).sendKeys(additionalAdult);
			ratesGrid.UPDATE_RATE_NEWRATE_ADDITIONALCHILD.get(i).sendKeys(additionalChild);

		}

		test_steps.add("Update By Room Class Toggle State:");
		ratesGridLogger.info("Update By Room Class Toggle State:");
		return test_steps;

	}

	/*
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectBulkUpdateRatesOption> ' Description: <Select the
	 * Option Given in Update Rate Options > ' ' Input parameters:
	 * <WebDriver,String> ' Return: <ArrayList<String>> Created By: <Aqsa
	 * Manzoor> ' Created On: <30 June 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> selectRateIncreaseDecreaseOption(WebDriver driver, String changeType)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.UPDATE_RATE_UPDATEBYVALUE_SELECTDROPDOWN_FIRST);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.UPDATE_RATE_UPDATEBYVALUE_SELECTDROPDOWN_FIRST), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.UPDATE_RATE_UPDATEBYVALUE_SELECTDROPDOWN_FIRST), driver);
		ratesGrid.UPDATE_RATE_UPDATEBYVALUE_SELECTDROPDOWN_FIRST.click();

		String path = "(//span[text()='each rate by']/preceding-sibling::div//div[@class='Select-menu-outer'])[1]//div[contains(text(),'"
				+ changeType + "')]";
		driver.findElement(By.xpath(path)).click();
		test_steps.add(changeType + "  option select");
		ratesGridLogger.info(changeType + " option select");

		test_steps.add("Update By Room Class Toggle State:");
		ratesGridLogger.info("Update By Room Class Toggle State:");
		return test_steps;

	}

	public ArrayList<String> selectRateCurrencyOption(WebDriver driver, String currencyType)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.UPDATE_RATE_UPDATEBYVALUE_SELECTDROPDOWN_SECOND);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.UPDATE_RATE_UPDATEBYVALUE_SELECTDROPDOWN_SECOND), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.UPDATE_RATE_UPDATEBYVALUE_SELECTDROPDOWN_SECOND),
				driver);
		ratesGrid.UPDATE_RATE_UPDATEBYVALUE_SELECTDROPDOWN_SECOND.click();

		String path = "(//span[text()='each rate by']/following-sibling::div//div[@class='Select-menu-outer'])[1]//div[contains(text(),'"
				+ currencyType + "')]";
		driver.findElement(By.xpath(path)).click();
		test_steps.add(currencyType + "  option select");
		ratesGridLogger.info(currencyType + " option select");
		ratesGrid.UPDATE_RATE_UPDATEBYVALUE_SELECTDROPDOWN_SECOND.click();

		test_steps.add("Update By Room Class Toggle State:");
		ratesGridLogger.info("Update By Room Class Toggle State:");
		return test_steps;

	}

	public ArrayList<String> enterRateValueForUpdateRate(WebDriver driver, String updateByValue)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.UPDATE_RATE_UPDATEBYVALUE_VALUEFIELD);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.UPDATE_RATE_UPDATEBYVALUE_VALUEFIELD), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.UPDATE_RATE_UPDATEBYVALUE_VALUEFIELD), driver);

		ratesGrid.UPDATE_RATE_UPDATEBYVALUE_VALUEFIELD.sendKeys(updateByValue);

		test_steps.add("Update By Room Class Toggle State:");
		ratesGridLogger.info("Update By Room Class Toggle State:");
		return test_steps;

	}

	public ArrayList<String> clickRateGridBulkUpdate(WebDriver driver) {

		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_RatesGrid.rateGridBulkUpdate);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.rateGridBulkUpdate), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.rateGridBulkUpdate), driver);
		ratesGrid.rateGridBulkUpdate.click();
		testSteps.add("RateGrid Bulk Update clicked");
		ratesGridLogger.info("RateGrid Bulk Update clicked");
		return testSteps;
	}

	public ArrayList<String> clickRateGridBulkUpdateAvailable(WebDriver driver) {

		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_RatesGrid.rateGridBulkUpdateAvailable);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.rateGridBulkUpdateAvailable), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.rateGridBulkUpdateAvailable), driver);
		ratesGrid.rateGridBulkUpdateAvailable.click();
		testSteps.add("RateGrid Bulk Update Available option clicked");
		ratesGridLogger.info("RateGrid Bulk Update Available option clicked");
		return testSteps;
	}

	public ArrayList<String> startDate(WebDriver driver, String date) {

		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_RatesGrid.bulkUpdatePopupCheckinInput);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.bulkUpdatePopupCheckinInput), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.bulkUpdatePopupCheckinInput), driver);
		ratesGrid.bulkUpdatePopupCheckinInput.clear();
		ratesGrid.bulkUpdatePopupCheckinInput.sendKeys(date);
		testSteps.add("Start date: " + date);
		ratesGridLogger.info("Start date: " + date);
		return testSteps;
	}

	public ArrayList<String> endDate(WebDriver driver, String date) {

		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_RatesGrid.bulkUpdatePopupCheckoutInput);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.bulkUpdatePopupCheckoutInput), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.bulkUpdatePopupCheckoutInput), driver);
		ratesGrid.bulkUpdatePopupCheckoutInput.clear();
		ratesGrid.bulkUpdatePopupCheckoutInput.sendKeys(date);
		testSteps.add("End date: " + date);
		ratesGridLogger.info("End date: " + date);
		ratesGrid.bulkUpdatePopupCheckoutInput.sendKeys(Keys.TAB);
		return testSteps;

	}

	public String bulkUpdatePoppupText(WebDriver driver) {

		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.bulkUpdatePopupText);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.bulkUpdatePopupText), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.bulkUpdatePopupText), driver);
		String text = ratesGrid.bulkUpdatePopupText.getText();
		ratesGridLogger.info("Verified Bulk update popup text : " + text);
		return text;

	}

	public ArrayList<String> bulkUpdatePoppupDayCheck(WebDriver driver, String day, String isChecked)
			throws InterruptedException {

		String daysCheckBox = "//span[text()='" + day + "']/following-sibling::span";
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, daysCheckBox);
		Wait.waitForElementToBeVisibile(By.xpath(daysCheckBox), driver);
		Wait.waitForElementToBeClickable(By.xpath(daysCheckBox), driver);
		WebElement daysElement = driver.findElement(By.xpath(daysCheckBox));
		Utility.ScrollToElement_NoWait(daysElement, driver);

		if (isChecked.equalsIgnoreCase("yes")) {
			testSteps.add(day + " checkbox checked");
			ratesGridLogger.info(day + " checkbox checked");
		} else if (isChecked.equalsIgnoreCase("no")) {
			daysElement.click();
			testSteps.add(day + " checkbox unchecked");
			ratesGridLogger.info(day + " checkbox unchecked");
		}
		return testSteps;

	}

	public ArrayList<String> bulkUpdatePoppupUpdateAvailability(WebDriver driver, String channel,
			String updateAvailability) throws InterruptedException {

		String updateAvailabilityPath = "//div[contains(text(),'" + channel
				+ "')]//following-sibling::div//span[contains(text(),'" + updateAvailability + "')]";
		ratesGridLogger.info("updateAvailabilityPath :  " + updateAvailabilityPath);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, updateAvailabilityPath);
		Wait.waitForElementToBeVisibile(By.xpath(updateAvailabilityPath), driver);
		Wait.waitForElementToBeClickable(By.xpath(updateAvailabilityPath), driver);
		WebElement updateAvailabilityElement = driver.findElement(By.xpath(updateAvailabilityPath));
		Utility.ScrollToElement_NoWait(updateAvailabilityElement, driver);
		updateAvailabilityElement.click();
		testSteps.add(channel + " selected update availability is " + updateAvailability);
		ratesGridLogger.info(channel + " selected update availability is " + updateAvailability);

		return testSteps;
	}

	public ArrayList<String> getBulkUpdatePoppupRoomClass(WebDriver driver) throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.bulkUpdatePopupRoomClass);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.bulkUpdatePopupRoomClass), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.bulkUpdatePopupRoomClass), driver);
		Utility.ScrollToElement_NoWait(ratesGrid.bulkUpdatePopupRoomClass, driver);
		ratesGrid.bulkUpdatePopupRoomClass.click();
		String roomClassesPath = "(//div[contains(text(),'Select room class(es)')]//parent::div//following::div[@class='Select-menu-outer'])[1]//div[@class='Select-option']";
		List<WebElement> roomClassesElement = driver.findElements(By.xpath(roomClassesPath));
		for (int i = 0; i < roomClassesElement.size(); i++) {
			String roomClassName = roomClassesElement.get(i).getText().trim();
			testSteps.add(roomClassName);
			ratesGridLogger.info("GetRoomClass : " + roomClassName);
		}

		return testSteps;
	}

	public ArrayList<String> bulkUpdatePoppupRoomClass(WebDriver driver, String roomClass) throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);

		Wait.WaitForElement(driver, OR_RatesGrid.bulkUpdatePopupRoomClass);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.bulkUpdatePopupRoomClass), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.bulkUpdatePopupRoomClass), driver);
		Utility.ScrollToElement_NoWait(ratesGrid.bulkUpdatePopupRoomClass, driver);
		ratesGrid.bulkUpdatePopupRoomClass.click();

		String roomClassesPath = "(//div[contains(text(),'Select room class(es)')]//parent::div//following::div[@class='Select-menu-outer'])[1]//div[contains(@class,'Select-option')]";
		List<WebElement> roomClassesElement = driver.findElements(By.xpath(roomClassesPath));
		System.out.println("Room Class Before for" + roomClass);
		System.out.println("Room Class Size " + roomClassesElement.size());

		for (int i = 0; i < roomClassesElement.size(); i++) {

			String roomClassName = roomClassesElement.get(i).getText().trim();
			testSteps.add("GetRoomClass : " + roomClassName);
			ratesGridLogger.info("GetRoomClass : " + roomClassName);
			System.out.println("ROom CLass get:" + roomClass);

			if (roomClassName.contains(roomClass)) {

				roomClassesElement.get(i).click();
				testSteps.add("Entered RoomClass : " + roomClass);
				ratesGridLogger.info("Entered RoomClass : " + roomClass);

				ratesGrid.bulkUpdatePopupCheckoutInput.sendKeys(Keys.TAB);
				testSteps.add("Clicked Tab Key");
				ratesGridLogger.info("Clicked Tab Key");

				break;
			}

		}

		return testSteps;
	}

	public ArrayList<String> clickBulkUpdatePopupUpdateButton(WebDriver driver) {

		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_RatesGrid.bulkUpdatePopupUpdateButton);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.bulkUpdatePopupUpdateButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.bulkUpdatePopupUpdateButton), driver);
		ratesGrid.bulkUpdatePopupUpdateButton.click();
		testSteps.add("Update button clicked");
		ratesGridLogger.info("Update button clicked");
		return testSteps;
	}

	public ArrayList<String> bulkUpdatePopupUpdateButtonEnable(WebDriver driver) {

		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_RatesGrid.bulkUpdatePopupUpdateButton);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.bulkUpdatePopupUpdateButton), driver);
		Boolean isActive = ratesGrid.bulkUpdatePopupUpdateButton.isEnabled();
		if (isActive) {
			testSteps.add("Update button is enabled");
			ratesGridLogger.info("Update button is enabled");

		} else {
			testSteps.add("Update button is disabled");
			ratesGridLogger.info("Update button is disabled");

		}
		return testSteps;
	}

	public String bulkUpdatePoppupTotalDays(WebDriver driver) {

		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.bulkUpdatePopupDays);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.bulkUpdatePopupDays), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.bulkUpdatePopupDays), driver);
		String text = ratesGrid.bulkUpdatePopupDays.getText();
		ratesGridLogger.info("bulkUpdatePopupDays text : " + text);
		return text;

	}

	public ArrayList<String> clickBulkUpdateCancelButton(WebDriver driver) {

		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_RatesGrid.bulkUpdateCancel);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.bulkUpdateCancel), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.bulkUpdateCancel), driver);
		ratesGrid.bulkUpdateCancel.click();
		testSteps.add("cancel button clicked");
		ratesGridLogger.info("cancel button clicked");
		return testSteps;
	}

	public Boolean compareLists(ArrayList<String> firstList, ArrayList<String> secondList) {
		Boolean isRoomClassPresent = false;
		int i;
		for (i = 0; i < firstList.size(); i++) {
			String activeRoomClass = firstList.get(i);
			ratesGridLogger.info("activeRoomClass : " + activeRoomClass);
			String activeRoomClassInBulkUpdate = secondList.get(i);
			ratesGridLogger.info("activeRoomClassInBulkUpdate : " + activeRoomClassInBulkUpdate);
			if (activeRoomClass.contains(activeRoomClassInBulkUpdate)) {
				isRoomClassPresent = true;
			} else {
				isRoomClassPresent = false;
			}
		}

		return isRoomClassPresent;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickRatePlanArrow> ' Description: < Move to Rate Plan
	 * Drop Down Box and Click on Arrow > ' Input parameters: < WebDriver
	 * driver,String test_steps> ' Return value: <void> ' Created By: <Gangotri
	 * Sikheria> ' ' Created On: <MM/dd/yyyy> <07/01/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public void clickRatePlanArrow(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.ratePlanSelectValue);
		Utility.ScrollToElement(elements.ratesTab, driver);
		elements.ratePlanArrow.click();
		test_steps.add("Click Rate Plan Arrow");
		logger.info("Click Rate Plan Arrow");

	}

	public void clickRatePlanArrowOpenIcon(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		// Wait.WaitForElement(driver, OR_RateGrid.ratePlanSelectValue);
		Utility.ScrollToElement(elements.ratesTab, driver);
		// elements.ratePlanOpenArrowIcon.click();
		Utility.clickThroughJavaScript(driver, elements.ratePlanOpenArrowIcon);
		test_steps.add("Click Rate Plan Arrow Open Icon");
		logger.info("Click Rate Plan Arrow Open Icon");

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getRatePlanNames> ' Description: < Get all the Rate Plan
	 * Name> ' Input parameters: < WebDriver driver,String test_steps> ' Return
	 * value: <ArrayList<String>> ' Created By: <Gangotri Sikheria> ' ' Created
	 * On: <MM/dd/yyyy> <07/01/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> getRatePlanNames(WebDriver driver) throws InterruptedException {
		ArrayList<String> getNames = new ArrayList<String>();
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.ratePlanNamesList);
		Utility.ScrollToElement(elements.ratePlanNamesList.get(0), driver);
		for (WebElement ele : elements.ratePlanNamesList) {
			getNames.add(ele.getAttribute("aria-label"));
		}
		logger.info("Get List of All Rate Plans: " + getNames);
		return getNames;
	}

	public ArrayList<String> getRatePlanNamesCategoryWise(WebDriver driver, String colorName)
			throws InterruptedException {
		ArrayList<String> getNames = new ArrayList<String>();
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.ratePlanNamesList);
		Utility.ScrollToElement(elements.ratePlanNamesList.get(0), driver);
		for (int i = 0; i < elements.ratePlanNamesList.size(); i++) {
			Utility.ScrollToElement(elements.ratePlansColor.get(i), driver);
			String color = elements.ratePlansColor.get(i).getCssValue("color");
			String colorName1 = Color.fromString(color).asHex();
			if (colorName1.toLowerCase().trim().equals(colorName.toLowerCase().trim())) {
				getNames.add(elements.ratePlanNamesList.get(i).getAttribute("aria-label"));
			}
		}
		logger.info("Get List of All Rate Plans: " + getNames);
		return getNames;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getRatePlanColor> ' Description: < Get all the Rate Plan
	 * Names Color> ' Input parameters: < WebDriver driver,String test_steps> '
	 * Return value: <ArrayList<String>> ' Created By: <Gangotri Sikheria> ' '
	 * Created On: <MM/dd/yyyy> <07/01/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> getRatePlanColor(WebDriver driver) throws InterruptedException {
		ArrayList<String> getColor = new ArrayList<String>();
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.ratePlansColor);
		Utility.ScrollToElement(elements.ratePlansColor.get(0), driver);
		for (int i = 0; i < elements.ratePlansColor.size(); i++) {

			getColor.add(elements.ratePlanNamesList.get(i).getAttribute("aria-label") + "--"
					+ elements.ratePlansColor.get(i).getCssValue("color"));

		}
		logger.info("Get the Color of All Rate Plans: " + getColor);
		return getColor;
	}

	public String getRatePlanColorCategoryWise(WebDriver driver, ArrayList<String> ratePlans, String colorName)
			throws InterruptedException {

		String getColor = null;
		for (String str : ratePlans) {
			String path = "//div[@role='option'and contains(@aria-label,'" + str + "')]/div";
			WebElement element = driver.findElement(By.xpath(path));
			Utility.ScrollToElement(element, driver);
			String attributeValue = element.getAttribute("style");
			String[] array = attributeValue.split(":");
			String arrayTwo[] = array[1].split(";");
			getColor = arrayTwo[0];
			getColor = Color.fromString(getColor).asHex();

		}
		logger.info("Get the Color of All Rate Plans: " + getColor);
		return getColor;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectRatePlan> ' Description: < Select Specific Rate
	 * Plan from drop down box> ' Input parameters: < WebDriver driver,String
	 * test_steps, Strinf ratePlanName> ' Return value: <String>' Created By:
	 * <Gangotri Sikheria> ' ' Created On: <MM/dd/yyyy> <07/02/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String selectAndReturnSpecificRatePlan(WebDriver driver, ArrayList<String> test_steps, String ratePlanName)
			throws InterruptedException {
		String path = "//div[@class='Select-menu-outer']/div[@class='Select-menu']/div[@aria-label='" + ratePlanName
				+ "']";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		assertTrue(element.isDisplayed(), "rate plan name dosen't exist");
		element.click();
		test_steps.add("Select Rate Plan from Drop Down Box: <b>" + ratePlanName + "</b>");
		logger.info("Select Rate Plan from Drop Down Box: " + ratePlanName);
		return ratePlanName;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <sizeOfAllRatePlan> ' Description: < Give size the list of
	 * Rate Plan> ' Input parameters: < WebDriver driver> ' Return value:
	 * <String>' Created By: <Gangotri Sikheria> ' ' Created On: <MM/dd/yyyy>
	 * <07/02/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public int sizeOfAllRatePlan(WebDriver driver) throws InterruptedException {
		int size = 0;
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.ratePlanNamesList);
		Utility.ScrollToElement(elements.ratePlanNamesList.get(0), driver);
		size = elements.ratePlanNamesList.size();
		logger.info("Rate Plan Size is: " + size);
		return size;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickExpandIconOfRatePlan> ' Description: < Click Expand
	 * icon of rate plan> ' Input parameters: < WebDriver
	 * driver,ArrayList<String> test_steps, boolean isboolean> ' Return value:
	 * <NA>' Created By: <Gangotri Sikheria> ' ' Created On: <MM/dd/yyyy>
	 * <07/02/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void clickExpandIconOfRatePlan(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.ratePlanNamesExpanCollapseIcon);
		Utility.ScrollToElement(elements.ratesTab, driver);
		boolean isExist = Utility.isElementDisplayed(driver, By.xpath(OR_RateGrid.bestAvailableRate));
		if (isExist) {
			elements.ratePlanNamesExpanCollapseIcon.click();
			test_steps.add("Click Expand Icon Of Rate Plan");
			logger.info("Click Expand Icon Of Rate Plan");
		} else if (!isExist) {

			test_steps.add("Click Expand Icon Of Rate Plan");
			logger.info("Click Expand Icon Of Rate Plan");
		}

		assertTrue(elements.ratePlanMinusIcon.isDisplayed(), "Failed verified Minus Icon");
		test_steps.add("Collapse Icon Of Rate Plan is Displayed");
		logger.info("Collapse Icon Of Rate Plan is Displayed");

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickCollapseIconOfRatePlan> ' Description: < Click
	 * Collapse icon of rate plan> ' Input parameters: < WebDriver
	 * driver,ArrayList<String> test_steps, boolean isboolean> ' Return value:
	 * <NA>' Created By: <Gangotri Sikheria> ' ' Created On: <MM/dd/yyyy>
	 * <07/02/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void clickCollapseIconOfRatePlan(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.ratePlanNamesExpanCollapseIcon);
		Utility.ScrollToElement(elements.ratesTab, driver);

		boolean isExist = Utility.isElementDisplayed(driver, By.xpath(OR_RateGrid.bestAvailableRate));
		if (isExist) {

			test_steps.add("Click Collapse Icon Of Rate Plan");
			logger.info("Click Collapse Icon Of Rate Plan");
		} else if (!isExist) {
			elements.ratePlanNamesExpanCollapseIcon.click();
			test_steps.add("Click Collapse Icon Of Rate Plan");
			logger.info("Click Collapse Icon Of Rate Plan");
		}

		assertTrue(elements.ratePlanPlusIcon.isDisplayed(), "Failed verified Plus Icon");
		test_steps.add("Expand Icon Of Rate Plan is Displayed");
		logger.info("Expand Icon Of Rate Plan is Displayed");
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <searchRateAndSelectRate> ' Description: < Search Rate and
	 * Select Rate Plan from Drop Down Box> ' Input parameters: < WebDriver
	 * driver,ArrayList<String> test_steps, String ratePlanName> ' Return value:
	 * <NA>' Created By: <Gangotri Sikheria> ' ' Created On: <MM/dd/yyyy>
	 * <07/02/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void searchRateAndSelectRate(WebDriver driver, ArrayList<String> test_steps, String ratePlanName)
			throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.ratePlanSelectInput);
		Utility.ScrollToElement(elements.ratePlanSelectInput, driver);
		Actions action = new Actions(driver);
		action.moveToElement(elements.ratePlanInputBox).clickAndHold();
		logger.info("Click and Hold Rate Plan Input Box");
		elements.ratePlanInputBox.sendKeys(ratePlanName);
		test_steps.add("Enter Rate Plan <b>" + ratePlanName + "</b>");
		logger.info("Enter Rate Plan " + ratePlanName);
		String path = "//div[@role='option'and contains(@aria-label,'" + ratePlanName + "')]";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		assertTrue(element.isDisplayed(), "rate plan name dosen't exist");
		Utility.clickThroughAction(driver, element);
		test_steps.add("Select Rate Plan from Drop Down Box: <b>" + ratePlanName + "</b>");
		logger.info("Select Rate Plan from Drop Down Box: " + ratePlanName);

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getrateGridRoomClass> ' Description: < Get all the Room
	 * Class of Rate Grid> ' Input parameters: < WebDriver driver> ' Return
	 * value: <ArrayList<String>> ' Created By: <Gangotri Sikheria> ' ' Created
	 * On: <MM/dd/yyyy> <07/02/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> getrateGridRoomClass(WebDriver driver) throws InterruptedException {
		ArrayList<String> roomClasses = new ArrayList<String>();
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.ratePlanSelectValue);
		Utility.ScrollToElement(elements.ratesTab, driver);
		for (WebElement ele : elements.rateGridAllRoomClass) {
			roomClasses.add(ele.getAttribute("title"));
		}
		logger.info("Get List of All RoomClass: " + roomClasses);
		return roomClasses;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <expandRoomClass and collapseRoomClass> ' Description: <
	 * Clicking on Expand and Collapse icon of Specific Room Class> ' Input
	 * parameters: < WebDriver driver,ArrayList<String> testSteps, String
	 * roomClassName> ' Return value: <NA> ' Created By: <Gangotri Sikheria> ' '
	 * Created On: <MM/dd/yyyy> <07/02/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void expandRoomClass(WebDriver driver, ArrayList<String> testSteps, String roomClassName)
			throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		String path = "//div[@class='DatesTable']//div[contains(text(),'" + roomClassName + "')]/parent::div/span";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		try {
			element.click();
		} catch (Exception e) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-400)");
			element.click();
		}

		String minusPath = "//div[@class='DatesTable']//div[contains(text(),'" + roomClassName
				+ "')]/parent::div/span[contains(@class,'ir-minus')]";
		boolean collapseExist = driver.findElement(By.xpath(minusPath)).isDisplayed();
		if (collapseExist) {
			testSteps.add(" Expand Room Class <b>" + roomClassName + "</b>");
			logger.info("Expand Room Class " + roomClassName);
			WebElement minus = driver.findElement(By.xpath(minusPath));
			Utility.ScrollToElement(minus, driver);
			assertTrue(minus.isDisplayed(), "Failed to verify collapse icon");
			testSteps.add("Collapse icon Displayed For  Room Class <b>" + roomClassName + "</b>");
			logger.info("Collapse icon Displayed For Room Class " + roomClassName);
		}

	}
	
	public void expandRoomClassWithoutMinus(WebDriver driver, ArrayList<String> testSteps, String roomClassName)
			throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		String path = "//div[@class='DatesTable']//div[contains(text(),'" + roomClassName + "')]/parent::div/span";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		try {
			element.click();
		} catch (Exception e) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-400)");
			element.click();
		}

//		String minusPath = "//div[@class='DatesTable']//div[contains(text(),'" + roomClassName
//				+ "')]/parent::div/span[contains(@class,'ir-minus')]";
//		boolean collapseExist = driver.findElement(By.xpath(minusPath)).isDisplayed();
//		if (collapseExist) {
//			testSteps.add(" Expand Room Class <b>" + roomClassName + "</b>");
//			logger.info("Expand Room Class " + roomClassName);
//			WebElement minus = driver.findElement(By.xpath(minusPath));
//			Utility.ScrollToElement(minus, driver);
//			assertTrue(minus.isDisplayed(), "Failed to verify collapse icon");
//			testSteps.add("Collapse icon Displayed For  Room Class <b>" + roomClassName + "</b>");
//			logger.info("Collapse icon Displayed For Room Class " + roomClassName);
//		}

	}

	public void collapseRoomClass(WebDriver driver, ArrayList<String> testSteps, String roomClassName)
			throws InterruptedException {
		String path = "//div[@class='DatesTable']//div[contains(text(),'" + roomClassName + "')]/parent::div/span";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		// element.click();
		Utility.clickThroughJavaScript(driver, element);
		String plusPath = "//div[@class='DatesTable']//div[contains(text(),'" + roomClassName
				+ "')]/parent::div/span[contains(@class,'ir-plus')]";
		boolean expandExist = driver.findElement(By.xpath(plusPath)).isDisplayed();
		if (expandExist) {
			testSteps.add("Collapse Room Class <b>" + roomClassName + "</b>");
			logger.info("Collapse Room Class " + roomClassName);
			WebElement plus = driver.findElement(By.xpath(plusPath));
			Utility.ScrollToElement(plus, driver);
			assertTrue(plus.isDisplayed(), "Failed to verify collapse icon");
			testSteps.add("Expand icon Displayed For  Room Class <b>" + roomClassName + "</b>");
			logger.info("Expand icon Displayed For Room Class " + roomClassName);
		}

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getChannelofSpecificRoomClass> ' Description: < get
	 * Channels of Specific Room Class> ' Input parameters: < WebDriver
	 * driver,ArrayList<String> testSteps, String roomClassName> ' Return value:
	 * <Channel Name> ' Created By: <Gangotri Sikheria> ' ' Created On:
	 * <MM/dd/yyyy> <07/09/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> getChannelofSpecificRoomClass(WebDriver driver, ArrayList<String> testSteps,
			String roomClassName) throws InterruptedException {
		ArrayList<String> channelName = new ArrayList<String>();
		String path = "//div[@class='DatesTable']//div[contains(@title,'" + roomClassName + "')]"
				+ "/ancestor::div/..//following-sibling::div//div[contains(@class,'complexView')]"
				+ "//div[@class='roomClassName']";
		Wait.WaitForElement(driver, path);
		List<WebElement> elements = driver.findElements(By.xpath(path));
		Utility.ScrollToElement(elements.get(0), driver);
		for (WebElement ele : elements) {
			channelName.add(ele.getAttribute("title"));
		}
		logger.info("Room Class " + roomClassName + "Channels Are: " + channelName);
		return channelName;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyChannelForSpecificClass> ' Description: < Verifying
	 * channel which is associated with rate plan> ' Input parameters: <
	 * WebDriver driver,ArrayList<String> testSteps, String roomClassName,
	 * String ratePlanName> ' Return value: <NA> ' Created By: <Gangotri
	 * Sikheria> ' ' Created On: <MM/dd/yyyy> <07/02/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifyChannelForSpecificClass(WebDriver driver, ArrayList<String> testSteps, String roomClassName,
			String channelName, String ratePlanName) throws InterruptedException {
		String path = "//div[@class='DatesTable']//div[contains(@title,'" + roomClassName
				+ "')]/ancestor::div/..//following-sibling::div//div[@title='" + channelName + "']";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		assertTrue(element.isDisplayed(), "Failed to verify channel for specific room class");
		testSteps.add("Channel <b>" + channelName + "</b> Associated with Rate Plan <b>" + ratePlanName + "</b>");
		logger.info("Channel " + channelName + "Associated with Rate Plan" + ratePlanName);

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <expandChannel and collapseChannel> ' Description: < click
	 * expand and collapse icon of channel and verify iconn> ' Input parameters:
	 * < WebDriver driver,ArrayList<String> testSteps, String roomClassName,
	 * String channelName> ' Return value: <NA> ' Created By: <Gangotri
	 * Sikheria> ' ' Created On: <MM/dd/yyyy> <07/09/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void expandChannel(WebDriver driver, ArrayList<String> testSteps, String roomClassName, String channelName)
			throws InterruptedException {
		String path = "//div[@class='DatesTable']//div[contains(@title,'" + roomClassName + "')]"
				+ "/ancestor::div/..//following-sibling::div//div[@title='" + channelName + "']/parent::div/span";
		// Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver, 5);
		Utility.clickThroughJavaScript(driver, element);
		// element.click();
		String minusPath = "//div[@class='DatesTable']//div[contains(@title,'" + roomClassName + "')]"
				+ "/ancestor::div/..//following-sibling::div//div[@title='" + channelName
				+ "']/parent::div/span[contains(@class,'ir-minus')]";
		boolean collapseExist = driver.findElement(By.xpath(minusPath)).isDisplayed();
		if (collapseExist) {
			testSteps.add("Expand Channel <b>" + channelName + "</b>");
			logger.info("Expand Channel " + channelName);
			WebElement minus = driver.findElement(By.xpath(minusPath));
			Utility.ScrollToElement(minus, driver);
			assertTrue(minus.isDisplayed(), "Failed to verify collapse icon");
			testSteps.add("Collapse icon Displayed For  Channel <b>" + channelName + "</b>");
			logger.info("Collapse icon Displayed For Channel " + channelName);
		}

	}
	
	
	public void expandChannelWithoutMinus(WebDriver driver, ArrayList<String> testSteps, String roomClassName, String channelName)
			throws InterruptedException {
		String path = "//div[@class='DatesTable']//div[contains(@title,'" + roomClassName + "')]"
				+ "/ancestor::div/..//following-sibling::div//div[@title='" + channelName + "']/parent::div/span";
		// Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver, 5);
		Utility.clickThroughJavaScript(driver, element);
		// element.click();
		String minusPath = "//div[@class='DatesTable']//div[contains(@title,'" + roomClassName + "')]"
				+ "/ancestor::div/..//following-sibling::div//div[@title='" + channelName
				+ "']/parent::div/span[contains(@class,'ir-minus')]";
//		boolean collapseExist = driver.findElement(By.xpath(minusPath)).isDisplayed();
//		if (collapseExist) {
//			testSteps.add("Expand Channel <b>" + channelName + "</b>");
//			logger.info("Expand Channel " + channelName);
//			WebElement minus = driver.findElement(By.xpath(minusPath));
//			Utility.ScrollToElement(minus, driver);
//			assertTrue(minus.isDisplayed(), "Failed to verify collapse icon");
//			testSteps.add("Collapse icon Displayed For  Channel <b>" + channelName + "</b>");
//			logger.info("Collapse icon Displayed For Channel " + channelName);
//		}

	}

	public void collapseChannel(WebDriver driver, ArrayList<String> testSteps, String roomClassName, String channelName)
			throws InterruptedException {
		String path = "//div[@class='DatesTable']//div[contains(@title,'" + roomClassName + "')]"
				+ "/ancestor::div/..//following-sibling::div//div[@title='" + channelName + "']/parent::div/span";
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver, 5);
		Utility.clickThroughJavaScript(driver, element);
		String plusPath = "//div[@class='DatesTable']//div[contains(@title,'" + roomClassName + "')]"
				+ "/ancestor::div/..//following-sibling::div//div[@title='" + channelName
				+ "']/parent::div/span[contains(@class,'ir-plus')]";
		boolean expandExist = driver.findElement(By.xpath(plusPath)).isDisplayed();
		if (expandExist) {
			testSteps.add("Collapse Channel <b>" + channelName + "</b>");
			logger.info("Click Expand icon of Room Class " + channelName);
			WebElement plus = driver.findElement(By.xpath(plusPath));
			Utility.ScrollToElement(plus, driver);
			assertTrue(plus.isDisplayed(), "Failed to verify expand icon");
			testSteps.add("Expand icon Displayed For Channel <b>" + channelName + "</b>");
			logger.info("Expand icon Displayed For Channel " + channelName);

		}

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getRuleHeader> ' Description: < Get all Rule Header> '
	 * Input parameters: < WebDriver driver,ArrayList<String> testSteps,String
	 * roomClass, String channelName> ' Return value: <Rule Headers > ' Created
	 * By: <Gangotri Sikheria> ' ' Created On: <MM/dd/yyyy> <07/09/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> getRuleHeader(WebDriver driver, ArrayList<String> testSteps, String roomClass,
			String channelName) throws InterruptedException {
		ArrayList<String> ruleHeader = new ArrayList<String>();
		String path = "//div[@class='DatesTable']//div[contains(@title,'" + roomClass + "')]"
				+ "/ancestor::div/..//following-sibling::div//div[@title='" + channelName + "']"
				+ "/ancestor::div/..//following-sibling::div//div[contains(@class,'RuleHeader')]";
		Wait.WaitForElement(driver, path);
		List<WebElement> elements = driver.findElements(By.xpath(path));
		Utility.ScrollToElement(elements.get(0), driver);
		for (WebElement ele : elements) {
			ruleHeader.add(ele.getText());
		}
		logger.info("Rule Header <b>" + ruleHeader + "</b>");
		return ruleHeader;
	}

	public void hoverRuleRate(WebDriver driver, String roomClass, String rate, ArrayList<String> testSteps)
			throws InterruptedException {
		String roomClassPath = "//div[contains(text(),'" + roomClass + "')]";
		Wait.WaitForElement(driver, roomClassPath);
		WebElement elementRoomClass = driver.findElement(By.xpath(roomClassPath));
		Utility.ScrollToViewElementINMiddle(driver, elementRoomClass);
		String path = "//div[contains(text(),'" + roomClass + "')]"
				+ "/parent::div/following-sibling::div/div[contains(@class,'Override')and text()='" + rate + "']";

		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.hoverOnElement(driver, element);
		testSteps.add("Hover Rule Rate of Room Class<b>" + roomClass + "</b>");
		logger.info("Hover Rule Rate of Room Class" + roomClass);
	}

	public void hoverRuleIndication(WebDriver driver, String roomClass, ArrayList<String> testSteps)
			throws InterruptedException {

		String path = "//div[contains(text(),'" + roomClass
				+ "')]/parent::div/following-sibling::div/div[@class='RuleIndication']/span";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToViewElementINMiddle(driver, element);
		Utility.hoverOnElement(driver, element);
		testSteps.add("Hover Rule Indication");
		logger.info("Hover Rule Indication");
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyRuleDate> amnd <getRuleDate> ' Description: < get
	 * and Verify Rate Grid Popup date > ' Input parameters: <WebDriver
	 * driver,ArrayList<String> testSteps, String date> ' Return value: <String
	 * ,NA> ' Created By: <Gangotri Sikheria> ' ' Created On: <MM/dd/yyyy>
	 * <07/02/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String getRuleDate(WebDriver driver, ArrayList<String> testSteps) throws InterruptedException {
		String ruleDate = null;
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.rateGridPopupDate);
		ruleDate = elements.rateGridPopupDate.getText();
		testSteps.add("Rule  Date: <b>" + elements.rateGridPopupDate.getText() + "</b>");
		logger.info("Rule Date: " + elements.rateGridPopupDate.getText());
		return ruleDate;
	}

	public String getRuleDate(WebDriver driver) throws InterruptedException {
		String ruleDate = null;
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.rateGridPopupDate);
		ruleDate = elements.rateGridPopupDate.getText();
		logger.info(" Date: " + elements.rateGridPopupDate.getText());
		return ruleDate;
	}

	public void verifyRuleDate(WebDriver driver, ArrayList<String> testSteps, String date) throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.rateGridPopupDate);
		assertEquals(elements.rateGridPopupDate.getText(), date, "Failed to verify rate grid popup date");
		testSteps.add("Verified  Date: <b>" + elements.rateGridPopupDate.getText() + "</b>");
		logger.info("Verified Date: " + elements.rateGridPopupDate.getText());
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyRuleRate> ' Description: < Verify Rate Grid Popup
	 * Rate> ' Input parameters: <WebDriver driver,ArrayList<String> testSteps,
	 * String rate> ' Return value: <NA> ' Created By: <Gangotri Sikheria> ' '
	 * Created On: <MM/dd/yyyy> <07/02/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String getRuleRate(WebDriver driver, ArrayList<String> testSteps) throws InterruptedException {
		String ruleRate = null;
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.popoverRate);
		ruleRate = elements.popoverRate.getText();
		testSteps.add("Rule  Rate: <b>" + elements.popoverRate.getText() + "</b>");
		logger.info("Rule Rate: " + elements.popoverRate.getText());
		return ruleRate;
	}

	public void verifyRuleRate(WebDriver driver, ArrayList<String> testSteps, String rate) throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.popoverRate);
		String ruleRate = elements.popoverRate.getText();
		logger.info(ruleRate);
		rate = "$" + rate;
		logger.info(rate);
		assertEquals(ruleRate, rate, "Failed to verify rate grid popup rate");
		testSteps.add("Verified Rate: <b>" + ruleRate + "</b>");
		logger.info("Verified Rate: " + ruleRate);

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyRuleLabel> ' Description: < Verify Popup Rule
	 * Label> ' Input parameters: <WebDriver driver,ArrayList<String> testSteps,
	 * String labelName> ' Return value: <NA> ' Created By: <Gangotri Sikheria>
	 * ' ' Created On: <MM/dd/yyyy> <07/02/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public String getRuleLabel(WebDriver driver, ArrayList<String> testSteps) throws InterruptedException {
		String ruleLabel = null;
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.popupRuleHeader);
		ruleLabel = elements.popupRuleHeader.getText();
		testSteps.add("Rule  Label: <b>" + elements.popupRuleHeader.getText() + "</b>");
		logger.info("Rule Label: " + elements.popupRuleHeader.getText());
		return ruleLabel;
	}

	public void verifyRuleLabel(WebDriver driver, ArrayList<String> testSteps, String labelName)
			throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.popupRuleHeader);
		assertEquals(elements.popupRuleHeader.getText(), labelName, "Failed to verify rate grid popup label");
		testSteps.add("Verified Rule Label: <b>" + elements.popupRuleHeader.getText() + "</b>");
		logger.info("Verified  Rule Label: " + elements.popupRuleHeader.getText());
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyRuleAppliedLabel> ' Description: < Verify Popup
	 * Rule Applied Label> ' Input parameters: <WebDriver
	 * driver,ArrayList<String> testSteps, String ruleAppliedLabel> ' Return
	 * value: <NA> ' Created By: <Gangotri Sikheria> ' ' Created On:
	 * <MM/dd/yyyy> <07/02/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public String getRuleAppliedLabel(WebDriver driver, ArrayList<String> testSteps) throws InterruptedException {
		String ruleAppliedLabel = null;
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.rateGridPopupRuleIndectionLabel);
		ruleAppliedLabel = elements.rateGridPopupRuleIndectionLabel.getText();
		testSteps.add("Rule  Applied Label: <b>" + elements.rateGridPopupRuleIndectionLabel.getText() + "</b>");
		logger.info("Rule Applied Label: " + elements.rateGridPopupRuleIndectionLabel.getText());
		return ruleAppliedLabel;
	}

	public void verifyRuleAppliedLabel(WebDriver driver, ArrayList<String> testSteps, String ruleAppliedLabel)
			throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.rateGridPopupRuleIndectionLabel);
		assertTrue(elements.ruleIndecationIcon.isDisplayed(), "Failed to Rule Applied Legend");
		assertEquals(elements.rateGridPopupRuleIndectionLabel.getText(), ruleAppliedLabel,
				"Failed to verify rate grid popup rule applied label");
		testSteps.add("Verified Rule  Applied Label: <b>" + elements.rateGridPopupRuleIndectionLabel.getText()
				+ "</b><b>-- Rule Applied Iocn is Displayed</b>");
		logger.info("Verified Rule  Applied Label: " + elements.rateGridPopupRuleIndectionLabel.getText()
				+ "Rule Applied Iocn is Displayed");

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyNightsMinimum> ' Description: < Verify Popup Rule
	 * Minimum Night> ' Input parameters: <WebDriver driver,ArrayList<String>
	 * testSteps, String minimumNights> ' Return value: <NA> ' Created By:
	 * <Gangotri Sikheria> ' ' Created On: <MM/dd/yyyy> <07/02/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifyNightsMinimum(WebDriver driver, ArrayList<String> testSteps, String minimumNights)
			throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.rateGridPopupMinimumNights);
		Utility.ScrollToElement(elements.rateGridPopupMinimumNights, driver);
		minimumNights = "" + minimumNights + " night min";
		assertEquals(elements.rateGridPopupMinimumNights.getText(), minimumNights,
				"Failed to verify rate grid popup rule minimum nights");
		testSteps.add("Verified Minimum Nights: <b>" + elements.rateGridPopupRuleIndectionLabel.getText() + "</b>");
		logger.info("Verified   Minimum Nights: " + elements.rateGridPopupRuleIndectionLabel.getText());
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyMinStay> ' Description: < Verifying Min Stay of Day
	 * on which date for room class> ' Input parameters: < WebDriver
	 * driver,ArrayList<String> testSteps, String weekDay, String dayNum,String
	 * roomClass, String channelName,String ruleAppliedOn,String nights> '
	 * Return value: <NA> ' Created By: <Gangotri Sikheria> ' ' Created On:
	 * <MM/dd/yyyy> <07/02/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public void verifyMinStay(WebDriver driver, ArrayList<String> testSteps, String weekDay, String dayNum,
			String roomClass, String channelName, String ruleAppliedOn, String nights) throws InterruptedException {
		String path = "//div[div[(text()='" + weekDay + "')]and div[(text()='" + dayNum + "')]]"
				+ "//ancestor::div//div[contains(text(),'" + roomClass + "')]/ancestor::div//div[@title='" + channelName
				+ "']" + "/ancestor::div//div[text()='" + ruleAppliedOn + "']/parent::div//input[@value='" + nights
				+ "']";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		assertTrue(element.isDisplayed(), "Failed to verify min stay nights");
		testSteps.add("Verified Min Stay Nights: <b>" + nights + "</b> For Room Class <b>" + roomClass
				+ "</b> for Channel <b>" + channelName + "</b>");
		logger.info(
				"Verified Min Stay Nights:" + nights + " For Room Class " + roomClass + " for Channel" + channelName);

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyNoCheckIn> ' Description: < Verifying No Check In
	 * for the day and which date set for room class> ' Input parameters: <
	 * WebDriver driver,ArrayList<String> testSteps, String weekDay, String
	 * dayNum,String roomClass, String channelName,String ruleAppliedOn> '
	 * Return value: <NA> ' Created By: <Gangotri Sikheria> ' ' Created On:
	 * <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public void verifyNoCheckIn(WebDriver driver, ArrayList<String> testSteps, String weekDay, String dayNum,
			String roomClass, String channelName, String ruleAppliedOn) throws InterruptedException {
		String path = "//div[div[(text()='" + weekDay + "')]and div[(text()='" + dayNum + "')]]"
				+ "//ancestor::div//div[contains(text(),'" + roomClass + "')]/ancestor::div//div[@title='" + channelName
				+ "']" + "/ancestor::div//div[text()='" + ruleAppliedOn
				+ "']/parent::div//div[@class='rt-onHover  enabled']";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		assertTrue(element.isDisplayed(), "Failed to verify No Check In ");
		testSteps.add("Verified No Check In on Day <b>" + weekDay + " </b>and Date <b>" + dayNum
				+ "</b> For Room Class <b>" + roomClass + "</b>");
		logger.info("Verified No Check In on Day" + weekDay + "and Date" + dayNum + "For Room Class" + roomClass);
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyNoCheckOut> ' Description: < Verifying No Checkout
	 * for the day and which date set for room class> ' Input parameters: <
	 * WebDriver driver,ArrayList<String> testSteps, String weekDay, String
	 * dayNum,String roomClass, String channelName,String ruleAppliedOn> '
	 * Return value: <NA> ' Created By: <Gangotri Sikheria> ' ' Created On:
	 * <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifyNoCheckOut(WebDriver driver, ArrayList<String> testSteps, String weekDay, String dayNum,
			String roomClass, String channelName, String ruleAppliedOn) throws InterruptedException {
		String path = "//div[div[(text()='" + weekDay + "')]and div[(text()='" + dayNum + "')]]"
				+ "//ancestor::div//div[contains(text(),'" + roomClass + "')]/ancestor::div//div[@title='" + channelName
				+ "']" + "/ancestor::div//div[text()='" + ruleAppliedOn
				+ "']/parent::div//div[@class='rt-onHover  enabled']";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		assertTrue(element.isDisplayed(), "Failed to verify No Check In ");
		testSteps.add("Verified No Check Out on Day <b>" + weekDay + " </b>and Date <b>" + dayNum
				+ "</b> For Room Class <b>" + roomClass + "</b>");
		logger.info("Verified No Check Out on Day" + weekDay + "and Date" + dayNum + "For Room Class" + roomClass);
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyNoCheckInAndOutColor> ' Description: < Verifying No
	 * Checkout for the day and which date set for room class> ' Input
	 * parameters: < WebDriver driver,ArrayList<String> testSteps, String
	 * weekDay, String dayNum,String roomClass, String channelName,String
	 * ruleAppliedOn,String colorName> ' Return value: <color> ' Created By:
	 * <Gangotri Sikheria> ' ' Created On: <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String verifyNoCheckInAndOutColor(WebDriver driver, ArrayList<String> testSteps, String weekDay,
			String dayNum, String roomClass, String channelName, String ruleAppliedOn, String colorName)
			throws InterruptedException {
		String color = null;
		String path = "//div[div[(text()='" + weekDay + "')]and div[(text()='" + dayNum + "')]]"
				+ "//ancestor::div//div[contains(text(),'" + roomClass + "')]/ancestor::div//div[@title='" + channelName
				+ "']" + "/ancestor::div//div[text()='" + ruleAppliedOn
				+ "']/parent::div//div[@class='rt-onHover  enabled']";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		Utility.hoverOnElement(driver, element);
		color = element.getCssValue("color");
		assertEquals(color, colorName, "Failed to verify Color");
		testSteps.add("Verified Color <b>" + color + " </b>");
		logger.info("Verified Color " + color);
		return path;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <updateRoomRate> ' Description: < Update Rate Amount and
	 * verified message rate saved successfully> ' Input parameters: < WebDriver
	 * driver,ArrayList<String> testSteps,String roomClass, String oldRate,
	 * String newRate> ' Return value: <New rates> ' Created By: <Gangotri
	 * Sikheria> ' ' Created On: <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String updateRoomRate(WebDriver driver, ArrayList<String> testSteps, String roomClass, String oldRate,
			String newRate) throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		String path = "//div[contains(text(),'" + roomClass
				+ "')]/parent::div/following-sibling::div/div[contains(@class,'RegularRate')and text()='" + oldRate
				+ "']";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToViewElementINMiddle(driver, element);
		element.click();
		elements.rateGridRoomRate.clear();
		elements.rateGridRoomRate.sendKeys(newRate);
		testSteps.add("Enter New Rate: <b>" + newRate + "</b>");
		logger.info("Enter New Rate: " + newRate);
		elements.rateGridSuccess.click();
		testSteps.add("Click on Success Icon");
		logger.info("Click on Success Icon");
		Wait.WaitForElement(driver, OR_RateGrid.rateSavedMessage);
		assertTrue(elements.rateSavedMessage.isDisplayed(), "Failed to Verify Success Message");
		testSteps.add("Verified Message : <b> Rate saved successfully </b>");
		logger.info("Verified Message : Rate saved successfully");
		String pathNewRate = "//div[contains(text(),'" + roomClass
				+ "')]/parent::div/following-sibling::div/div[contains(@class,'Override')and text()='" + newRate + "']";
		Wait.WaitForElement(driver, pathNewRate);
		WebElement elementOne = driver.findElement(By.xpath(pathNewRate));
		Utility.ScrollToElement(elementOne, driver);
		assertTrue(elementOne.isDisplayed(), "Failed to Verify New Rate");
		testSteps.add("Update Rate is : <b> " + newRate + " </b> Old Rate is: " + oldRate + "</b>");
		logger.info("Update Rate is :" + newRate + " Old Rate is: " + oldRate);
		return newRate;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <updateExtraAdults> ' Description: < Update Extra Adults>
	 * ' Input parameters: < WebDriver driver,ArrayList<String> testSteps,String
	 * roomClass, String rate, String adults> ' Return value: <NA> ' Created By:
	 * <Gangotri Sikheria> ' ' Created On: <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void updateExtraAdults(WebDriver driver, ArrayList<String> testSteps, String roomClass, String rate,
			String adults) throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		String path = "//div[contains(text(),'" + roomClass
				+ "')]/parent::div/following-sibling::div/div[contains(@class,'RegularRate')and text()='" + rate + "']";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		element.click();
		elements.rateGridExtraAd.clear();
		elements.rateGridExtraAd.sendKeys(adults);
		testSteps.add("Enter Extra Adults is: <b>" + adults + "</b>");
		logger.info("Enter Extra Adults is: " + adults);
		elements.rateGridSuccess.click();
		testSteps.add("Click on Success Icon");
		logger.info("Click on Success Icon");
		Wait.waitTillElementDisplayed(driver, OR_RateGrid.rateOverrideLoading);
		assertTrue(elements.rateSavedMessage.isDisplayed(), "Failed to Verify Success Message");
		testSteps.add("Verified Message : <b> Rate saved successfully </b>");
		logger.info("Verified Message : Rate saved successfully");

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <updateExtraChild> ' Description: < Update Extra Child > '
	 * Input parameters: < WebDriver driver,ArrayList<String> testSteps,String
	 * roomClass, String rate, String child> ' Return value: <NA> ' Created By:
	 * <Gangotri Sikheria> ' ' Created On: <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void updateExtraChild(WebDriver driver, ArrayList<String> testSteps, String roomClass, String rate,
			String child) throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		String path = "//div[contains(text(),'" + roomClass
				+ "')]/parent::div/following-sibling::div/div[contains(@class,'RegularRate')and text()='" + rate + "']";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		element.click();
		elements.rateGridExtraCh.clear();
		elements.rateGridExtraCh.sendKeys(child);
		testSteps.add("Enter Extra Adults is: <b>" + child + "</b>");
		logger.info("Enter Extra Adults is: " + child);
		elements.rateGridSuccess.click();
		testSteps.add("Click on Success Icon");
		logger.info("Click on Success Icon");
		Wait.waitTillElementDisplayed(driver, OR_RateGrid.rateOverrideLoading);
		assertTrue(elements.rateSavedMessage.isDisplayed(), "Failed to Verify Success Message");
		testSteps.add("Verified Message : <b> Rate saved successfully </b>");
		logger.info("Verified Message : Rate saved successfully");

	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyOverrideRateColor> ' Description: < Verified
	 * OverRide Rate Color> ' Input parameters: < WebDriver
	 * driver,ArrayList<String> testSteps,String roomClass,String rate, String
	 * colorName> ' Return value: <NA> ' Created By: <Gangotri Sikheria> ' '
	 * Created On: <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public void verifyRagularRateColor(WebDriver driver, ArrayList<String> testSteps, String roomClass, String rate,
			String colorName) throws InterruptedException {
		String path1 = "//div[contains(text(),'" + roomClass + "')]";
		String path = "//div[contains(text(),'" + roomClass
				+ "')]/parent::div/following-sibling::div/div[contains(@class,'RegularRate')and text()='" + rate + "']";
		Wait.WaitForElement(driver, path1);
		Utility.ScrollToViewElementINMiddle(driver, driver.findElement(By.xpath(path1)));
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToViewElementINMiddle(driver, element);
		String color = element.getCssValue("color");
		String colorName1 = Color.fromString(color).asHex();
		logger.info(colorName1);
		assertEquals(colorName1.toLowerCase().trim(), colorName.toLowerCase().trim(), "Failed to verify Color");
		testSteps.add("Verified Ragular Rate Color <b>" + colorName + " </b>");
		logger.info("Verified Ragular Rate Color " + colorName);
	}

	public void verifyOverrideRateColor(WebDriver driver, ArrayList<String> testSteps, String roomClass, String rate,
			String colorName) throws InterruptedException {
		String path1 = "//div[contains(text(),'" + roomClass + "')]";
		String path = "//div[contains(text(),'" + roomClass
				+ "')]/parent::div/following-sibling::div/div[contains(@class,'Override')and text()='" + rate + "']";
		Wait.WaitForElement(driver, path1);
		Utility.ScrollToViewElementINMiddle(driver, driver.findElement(By.xpath(path1)));
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToViewElementINMiddle(driver, element);
		String color = element.getCssValue("color");
		String colorName1 = Color.fromString(color).asHex();
		logger.info(colorName1);
		assertEquals(colorName1.toLowerCase().trim(), colorName.toLowerCase().trim(), "Failed to verify Color");
		testSteps.add("Verified OverRide Rate Color <b>" + colorName + " </b>");
		logger.info("Verified OverRide Rate Color " + colorName);
	}

	public void verifyRuleIndicationAndColor(WebDriver driver, ArrayList<String> testSteps, String roomClass,
			String colorName) throws InterruptedException {
		String path1 = "//div[contains(text(),'" + roomClass + "')]";
		String path = "//div[contains(text(),'" + roomClass
				+ "')]/parent::div/following-sibling::div/div[@class='RuleIndication']/span";
		Wait.WaitForElement(driver, path1);
		Utility.ScrollToViewElementINMiddle(driver, driver.findElement(By.xpath(path1)));
		WebElement element = driver.findElement(By.xpath(path));
		assertTrue(element.isDisplayed(), "Failed to verify Rule Indication");
		testSteps.add("Verified Rule Indication Icon for Room Class <b>" + roomClass + " </b>");
		logger.info("Verified Rule Indication Icon for Room Class " + roomClass);
		Utility.ScrollToViewElementINMiddle(driver, element);
		String color = element.getCssValue("color");
		String colorName1 = Color.fromString(color).asHex();
		logger.info(colorName1);
		assertEquals(colorName1.toLowerCase().trim(), colorName.toLowerCase().trim(), "Failed to verify Color");
		testSteps.add("Verified Rule Indication Icon Color <b>" + colorName + " </b>");
		logger.info("Verified Rule Indication Icon Color " + colorName);
	}

	public void verifyCheckInAndCheckoutColor(WebDriver driver, ArrayList<String> testSteps, String roomClass,
			String channelName, String ruleAppliedOn, String colorName) throws InterruptedException {
		String path1 = "//div[contains(text(),'" + roomClass + "')]";
		// String
		// path="//div[contains(text(),'"+roomClass+"')]/parent::div/following-sibling::div/div[@class='RuleIndication']/span";
		String path = "//div[contains(text(),'" + roomClass + "')]/ancestor::div//div[@title='" + channelName + "']"
				+ "/ancestor::div//div[text()='" + ruleAppliedOn + "']/parent::div//div[@class='rt-onHover  enabled']";
		Wait.WaitForElement(driver, path1);
		Utility.ScrollToViewElementINMiddle(driver, driver.findElement(By.xpath(path1)));
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToViewElementINMiddle(driver, element);
		String color = element.getCssValue("color");
		String colorName1 = Color.fromString(color).asHex();
		logger.info(colorName1);
		assertEquals(colorName1.toLowerCase().trim(), colorName.toLowerCase().trim(), "Failed to verify Color");
		testSteps.add("Verified " + ruleAppliedOn + " Icon Color <b>" + colorName + " </b>");
		logger.info("Verified " + ruleAppliedOn + " Icon Color" + colorName);
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyRateGridPopupRateChangeLabel> ' Description: <
	 * Verified Rate Change Label> ' Input parameters: < WebDriver
	 * driver,ArrayList<String> testSteps, String rateChangeLabel> ' Return
	 * value: <NA> ' Created By: <Gangotri Sikheria> ' ' Created On:
	 * <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String getRateChangeLabel(WebDriver driver, ArrayList<String> testSteps) throws InterruptedException {
		String label = null;
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.rateGridPopupRateChangeHeader);
		label = elements.rateGridPopupRuleIndectionLabel.getText();
		testSteps.add("Rate Change Label: <b>" + elements.rateGridPopupRuleIndectionLabel.getText() + "</b>");
		logger.info("Rate Change  Label: " + elements.rateGridPopupRuleIndectionLabel.getText());
		return label;
	}

	public void verifyRateChangeLabel(WebDriver driver, ArrayList<String> testSteps, String rateChangeLabel)
			throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.rateGridPopupRateChangeHeader);
		assertEquals(elements.rateGridPopupRuleIndectionLabel.getText(), rateChangeLabel,
				"Failed to verify Rate Change Label");
		testSteps.add("Verified Label: <b>" + elements.rateGridPopupRuleIndectionLabel.getText() + "</b>");
		logger.info("Verified Label: " + elements.rateGridPopupRuleIndectionLabel.getText());
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyRulesLabels> ' Description: < Verified any Label
	 * Such as Updated by:, Updated on:, Previous price:,No check out, No check
	 * in,Previous price:,night min> ' Input parameters: < WebDriver
	 * driver,ArrayList<String> testSteps, String label> ' Return value: <NA> '
	 * Created By: <Gangotri Sikheria> ' ' Created On: <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> getRulesLabels(WebDriver driver, ArrayList<String> testSteps) throws InterruptedException {
		ArrayList<String> labels = new ArrayList<String>();
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		for (WebElement ele : elements.ruleLabels) {
			labels.add(ele.getText());
		}
		logger.info("Rule Label Are:" + labels);
		return labels;
	}

	public String verifyRulesLabels(WebDriver driver, String label) throws InterruptedException {

		String path = "//span[@class='rules-labels'and contains(text(),'" + label + "')]";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		assertTrue(element.isDisplayed(), "Failed to Verify label");
		// testSteps.add("Verified Label : <b> "+label+" </b>");
		logger.info("Verified Label :" + label);
		return label;
	}

	public boolean checkAndGetLabelsfromList(WebDriver driver, ArrayList<String> list, String label)
			throws InterruptedException {
		boolean isExist = false;
		for (String element : list) {
			if (element == label) {
				isExist = true;
				break;
			}
		}

		// Print the result
		logger.info("Is " + label + " present in the List: " + isExist);
		return isExist;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyChangeValue> ' Description: < Verified Changed
	 * Value like Updated by:, Updated on:,Previous price:> ' Input parameters:
	 * < WebDriver driver,ArrayList<String> testSteps, String label> ' Return
	 * value: <NA> ' Created By: <Gangotri Sikheria> ' ' Created On:
	 * <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifyChangeValue(WebDriver driver, String changeValue) throws InterruptedException {
		String path = "//span[@class='change-value'and contains(text(),'" + changeValue + "')]";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		assertTrue(element.isDisplayed(), "Failed to Verify Change Value");
		logger.info("Verified Change Value :" + changeValue);
	}

	public void verifyPreviousPfice(WebDriver driver, String changeValue) throws InterruptedException {
		String path = "//span[@class='change-value'and contains(normalize-space(.), '" + changeValue + "')]";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		assertTrue(element.isDisplayed(), "Failed to Verify Change Value");
		logger.info("Verified Change Value :" + changeValue);
	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyImagesOnRulePopup> ' Description: < Verified all
	 * images> ' Input parameters: < WebDriver driver,ArrayList<String>
	 * testSteps,String roomClass, String rate, String ruleName,String image> '
	 * Return value: <NA> ' Created By: <Gangotri Sikheria> ' ' Created On:
	 * <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> getRuleImages(WebDriver driver, ArrayList<String> testSteps) throws InterruptedException {
		ArrayList<String> images = new ArrayList<String>();
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.rulePropertyImage);
		for (WebElement element : elements.rulePropertyImage) {
			images.add(element.getAttribute("src"));
		}
		logger.info("Rule Images Are: " + images);
		return images;
	}

	public void verifyImagesOnRulePopup(WebDriver driver, ArrayList<String> testSteps, String roomClass, String rate,
			String ruleName, String image) throws InterruptedException {

		String path = "//div[contains(text(),'" + roomClass
				+ "')]/parent::div/following-sibling::div/div[contains(@class,'Override')and text()='" + rate + "']";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		Utility.hoverOnElement(driver, element);
		String imagePath = "//img[contains(@src,'" + image + "')]";
		WebElement elementImage = driver.findElement(By.xpath(imagePath));
		Utility.ScrollToElement(elementImage, driver);
		assertTrue(element.isDisplayed(), "Failed to Verify Images");
		if (ruleName.equals("Updated by")) {
			testSteps.add("Verified Updated by Image ");
			logger.info("Verified Updated by Image ");
		} else if (ruleName.equals("Updated on")) {
			testSteps.add("Verified Updated on Image ");
			logger.info("Verified Updated on Image ");
		} else if (ruleName.equals("night")) {
			testSteps.add("Verified Night Min Image ");
			logger.info("Verified Night Min Image ");
		} else if (ruleName.equals("NoCheckOut")) {
			testSteps.add("Verified No Check Out Image ");
			logger.info("Verified No Check Out Image ");
		} else if (ruleName.equals("NoCheckIn ")) {
			testSteps.add("Verified No Check In Image ");
			logger.info("Verified No Check In Image ");
		}

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <removeOverRide> ' Description: <remoaved over ride> '
	 * Input parameters: < WebDriver driver,ArrayList<String> testSteps,String
	 * roomClass, String rate> ' Return value: <NA> ' Created By: <Gangotri
	 * Sikheria> ' ' Created On: <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void removeOverRide(WebDriver driver, ArrayList<String> testSteps, String roomClass, String rate,
			String oldRate) throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		String path = "//div[contains(text(),'" + roomClass
				+ "')]/parent::div/following-sibling::div/div[contains(@class,'Override')and text()='" + rate + "']";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		// Utility.ScrollToElement(element, driver);
		Utility.ScrollToViewElementINMiddle(driver, element);
		element.click();
		elements.rateGridremoveOverRide.click();
		testSteps.add("Remove OverRide");
		logger.info("Remove OverRide");
		// Wait.waitTillElementDisplayed(driver,
		// OR_RateGrid.rateOverrideLoading);
		Wait.WaitForElement(driver, OR_RateGrid.rateOverrideMessage);
		assertTrue(elements.rateOverrideMessage.isDisplayed(), "Failed to Verify Delete Message");
		testSteps.add("Verified Message : <b> Deleted rate override successfully </b>");
		logger.info("Verified Message : Deleted rate override successfully");
		String regulerRate = "//div[contains(text(),'" + roomClass
				+ "')]/parent::div/following-sibling::div/div[contains(@class,'RegularRate')and text()='" + oldRate
				+ "']";
		WebElement elementRegular = driver.findElement(By.xpath(regulerRate));
		Utility.ScrollToElement(elementRegular, driver);
		assertTrue(elementRegular.isDisplayed(), "Failed to Verify Regular Rate");
		testSteps.add("Displayed old Rate : <b>" + oldRate + "</b>");
		logger.info("Displayed old Rate : " + oldRate);

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <updateMinStay> ' Description: <Update Min Stay> ' Input
	 * parameters: < WebDriver driver,ArrayList<String> testSteps, String
	 * weekDay, String dayNum,String roomClass, String channelName,String
	 * ruleAppliedOn,String nights, String newNight> ' Return value: <NA> '
	 * Created By: <Gangotri Sikheria> ' ' Created On: <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void updateRuleForMinStay(WebDriver driver, ArrayList<String> testSteps, String weekDay, String dayNum,
			String roomClass, String channelName, String ruleAppliedOn, String nights, String newNight)
			throws InterruptedException, ParseException {

		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		String path = "//div[div[(text()='" + weekDay + "')]and div[(text()='" + dayNum + "')]]"
				+ "//ancestor::div//div[contains(text(),'" + roomClass + "')]/ancestor::div//div[@title='" + channelName
				+ "']" + "/ancestor::div//div[text()='" + ruleAppliedOn + "']/parent::div//input[@value='']";

		String ruleAppliedPath = "//div[div[(text()='" + weekDay + "')]and div[(text()='" + dayNum + "')]]"
				+ "//ancestor::div//div[contains(text(),'" + roomClass + "')]/ancestor::div//div[@title='" + channelName
				+ "']" + "/ancestor::div//div[text()='" + ruleAppliedOn + "']";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToViewElementINMiddle(driver, element);
		element.click();
		testSteps.add("Click on Min Stay Box");
		element.clear();
		testSteps.add("Clear Min Stay ");
		element.sendKeys(newNight);
		testSteps.add("Input Min Stay : <b>" + newNight + "</b>");
		WebElement elementMinStay = driver.findElement(By.xpath(ruleAppliedPath));
		Utility.ScrollToElement(elementMinStay, driver);
		element.sendKeys(Keys.TAB);
		Wait.WaitForElement(driver, OR_RateGrid.rateGridRuleSavedMessage);
		assertTrue(elements.rateGridRuleSavedMessage.isDisplayed(), "Failed to Verify Rule Saved Message");
		testSteps.add("Update Rule for <b>" + ruleAppliedOn + "</b> Message : <b> Rule saved successfully </b>");
		logger.info("Update Rule for " + ruleAppliedOn + "Message :  Rule saved successfully");
		String pathOne = "//div[div[(text()='" + weekDay + "')]and div[(text()='" + dayNum + "')]]"
				+ "//ancestor::div//div[contains(text(),'" + roomClass + "')]/ancestor::div//div[@title='" + channelName
				+ "']" + "/ancestor::div//div[text()='" + ruleAppliedOn + "']/parent::div//input[@value='" + newNight
				+ "']";
		WebElement elementOne = driver.findElement(By.xpath(pathOne));
		Utility.ScrollToElement(elementOne, driver);
		assertEquals(elementOne.getAttribute("value"), newNight, "Failed to Verify Updated Min Stay");
		testSteps.add("Verified Updated Min Stay is :<b>" + newNight + "</b> Old Min Stay is : <b>" + nights + "</b>");
		logger.info("Verified Updated Min Stay is :" + newNight + "Old Min Stay is : " + nights);
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <updateMinStay> ' Description: <Update Min Stay> ' Input
	 * parameters: < WebDriver driver,ArrayList<String> testSteps, String
	 * weekDay, String dayNum,String roomClass, String channelName,String
	 * ruleAppliedOn,String nights, String newNight> ' Return value: <NA> '
	 * Created By: <Gangotri Sikheria> ' ' Created On: <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void updateRuleForNoCheckInAndOut(WebDriver driver, ArrayList<String> testSteps, String weekDay,
			String dayNum, String roomClass, String channelName, String ruleAppliedOn) throws InterruptedException {
		Wait.wait10Second();
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		String path = "//div[div[(text()='" + weekDay + "')]and div[(text()='" + dayNum + "')]]"
				+ "//ancestor::div//div[contains(text(),'" + roomClass + "')]/ancestor::div//div[@title='" + channelName
				+ "']" + "/ancestor::div//div[text()='" + ruleAppliedOn
				+ "']/parent::div//div[@class='rt-onHover  has-noValue enabled']";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToViewElementINMiddle(driver, element);
		Utility.hoverOnElement(driver, element);
		element.click();
		Wait.WaitForElement(driver, OR_RateGrid.rateGridRuleSavedMessage);
		assertTrue(elements.rateGridRuleSavedMessage.isDisplayed(), "Failed to Verify Rule Saved Message");
		testSteps.add("Update Rule for <b>" + ruleAppliedOn + "</b> Message : <b> Rule saved successfully </b>");
		logger.info("Update Rule for " + ruleAppliedOn + "Message :  Rule saved successfully");

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyEditIcon> ' Description: <Verify Edit Icon> ' Input
	 * parameters: < WebDriver driver,ArrayList<String> testSteps> ' Return
	 * value: <NA> ' Created By: <Gangotri Sikheria> ' ' Created On:
	 * <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifyEditIcon(WebDriver driver, ArrayList<String> testSteps) {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		assertTrue(elements.rateEditIcon.isDisplayed(), "Failed to Edit icon");
		testSteps.add("Verified Edit Icon Displayed");
		logger.info("Verified Edit Icon Displayed");

	}

	public void clickEditIcon(WebDriver driver, ArrayList<String> testSteps) throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.rateEditIcon);
		Utility.ScrollToViewElementINMiddle(driver, elements.rateEditIcon);
		elements.rateEditIcon.click();
		testSteps.add("Click Edit Icon ");
		logger.info("Click Edit Icon");

	}

	public void verifyRatePlaninEditMode(WebDriver driver, ArrayList<String> testSteps, String ratePlan)
			throws InterruptedException {
		String path = "//a[@data-id='EDIT_RATEPLAN_TABID'and text()='" + ratePlan + "']";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		assertTrue(element.isDisplayed(), "Failed to Edited rate Plan");
		testSteps.add("Edited Rate Plan is: <b>" + ratePlan + "</b>");
		logger.info("Edited Rate Plan is: " + ratePlan);

	}

	public void closeRatePlan(WebDriver driver, ArrayList<String> testSteps, String ratePlan)
			throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.ratePLanCloseIcon);
		Utility.ScrollToElement(elements.ratePLanCloseIcon, driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RateGrid.ratePLanCloseIcon), driver, 5);
		Utility.clickThroughJavaScript(driver, elements.ratePLanCloseIcon);
		// elements.ratePLanCloseIcon.click();
		testSteps.add("Close Rate Plan : <b>" + ratePlan + "</b>");
		logger.info("Close Rate Plan :" + ratePlan);
		Wait.WaitForElement(driver, OR_RateGrid.ratesTab);
		Wait.waitUntilPageLoadNotCompleted(driver, 30);
		testSteps.add("Navigated to RateGrid");
		logger.info("Navigated to RateGrid");

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyDeleteIcon> ' Description: <Verify Delete Icon> '
	 * Input parameters: < WebDriver driver,ArrayList<String> testSteps> '
	 * Return value: <NA> ' Created By: <Gangotri Sikheria> ' ' Created On:
	 * <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifyDeleteIcon(WebDriver driver, ArrayList<String> testSteps) {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		assertTrue(elements.rateDeleteIcon.isDisplayed(), "Failed to Delete icon");
		testSteps.add("Verified Delete Icon Displayed");
		logger.info("Verified Delete Icon Displayed");

	}

	public void clickDeleteIcon(WebDriver driver, ArrayList<String> testSteps) throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.rateDeleteIcon);
		Utility.ScrollToViewElementINMiddle(driver, elements.rateDeleteIcon);
		elements.rateDeleteIcon.click();
		testSteps.add("Click Delete icon");
		logger.info("Click Delete icon");

	}

	public void verifyDeletedMsg(WebDriver driver, ArrayList<String> testSteps, String msg)
			throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.ratePlanDeleteMsg);
		Utility.ScrollToElement(elements.ratePlanDeleteMsg, driver);
		String message = elements.ratePlanDeleteMsg.getText().replaceAll("[\\n\\t ]", "");
		logger.info(message);
		msg = msg.replaceAll("[\\n\\t ]", "");
		assertEquals(msg, message, "Failed to verify deleted rate plan message");
		testSteps.add("Verified Message : <b>" + message + "</b>");
		logger.info("Verified Message : " + message);
	}

	public void clickDeleteButton(WebDriver driver, ArrayList<String> testSteps) throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.rateDeleteButton);
		Utility.ScrollToElement(elements.rateDeleteButton, driver);
		Utility.clickThroughJavaScript(driver, elements.rateDeleteButton);
		testSteps.add("Click Delete Button");
		logger.info("Click Delete Button");
		Wait.WaitForElement(driver, OR_RateGrid.ratePlanDeleteMessage);
		assertTrue(elements.ratePlanDeleteMessage.isDisplayed(), "Failed to Verify Rate Plan Delete Message");
		testSteps.add("Verified  Message : <b> Rate Plan deleted successfully </b>");
		logger.info("Verified  Message : Rate Plan deleted successfully");
		Wait.WaitForElement(driver, OR_RateGrid.ratesTab);
		Wait.waitUntilPageLoadNotCompleted(driver, 30);
		logger.info("Navigated to RateGrid");

	}

	public void verifyDeletedRatePlan(WebDriver driver, ArrayList<String> testSteps, String ratePlan,
			ArrayList<String> list) {
		boolean isExist = !list.contains(ratePlan);
		if (isExist) {
			testSteps.add("Deleted Rate Plan Doesn't Exist: <b>" + ratePlan + "</b>");
			logger.info("Deleted Rate Plan Doesn't Exist: " + ratePlan);
		}

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyRatePlanType> ' Description: <Verify Rate Plan
	 * Type> ' Input parameters: < WebDriver driver,ArrayList<String>
	 * testSteps,String ratePlanType> ' Return value: <NA> ' Created By:
	 * <Gangotri Sikheria> ' ' Created On: <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifyRatePlanType(WebDriver driver, ArrayList<String> testSteps, String ratePlanType)
			throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		String ratePlantype = elements.ratePlanDesc.getText();
		Utility.ScrollToElement(elements.ratePlanDesc, driver);
		assertEquals(ratePlantype.toLowerCase().trim(), ratePlanType.toLowerCase().trim(),
				"Failed to verify Rate Plan Type");
		testSteps.add("Verified Rate Plan Type <b>" + ratePlanType + "</b>");
		logger.info("Verified Rate Plan Type " + ratePlanType);

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyRoomClassColor> ' Description: <Verified Room Class
	 * Color> ' Input parameters: < WebDriver driver,ArrayList<String>
	 * testSteps,String roomClassName, String colorName> ' Return value: <NA> '
	 * Created By: <Gangotri Sikheria> ' ' Created On: <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifyRoomClassColor(WebDriver driver, String roomClassName, String colorName)
			throws InterruptedException {
		String color = null;
		String path = "//div[contains(text(),'" + roomClassName + "')]";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToViewElementINMiddle(driver, element);
		color = element.getCssValue("color");
		String colorName1 = Color.fromString(color).asHex();
		logger.info(colorName1);
		assertEquals(colorName1.toLowerCase().trim(), colorName.toLowerCase().trim(),
				"Failed to verify Color of specific room class");
		// testSteps.add("Verified Color <b>"+color+" </b>");
		logger.info("Verified Room Class Color " + color);

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getRateGridRoomClass> ' Description: <Get All Active Room
	 * Class of Rate Plan> ' Input parameters: < WebDriver driver> ' Return
	 * value: <ArrayList<String>> ' Created By: <Gangotri Sikheria> ' ' Created
	 * On: <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> getRateGridRoomClass(WebDriver driver) {
		ArrayList<String> roomClasses = new ArrayList<String>();
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.rateGridRoomClass);
		for (WebElement element : elements.rateGridRoomClass) {
			roomClasses.add(element.getText());

		}
		logger.info("Room Class Are:  " + roomClasses);
		return roomClasses;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifiedRateGridAllActiveRoomClass> ' Description:
	 * <verified active Room Class> ' Input parameters: < WebDriver driver> '
	 * Return value: <ArrayList<String>> ' Created By: <Gangotri Sikheria> ' '
	 * Created On: <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifiedRateGridAllActiveRoomClass(WebDriver driver, ArrayList<String> testSteps,
			ArrayList<String> roomClassesListOne, ArrayList<String> roomClassesListTwo) {

		Wait.WaitForElement(driver, OR_RateGrid.rateGridRoomClass);
		Collections.sort(roomClassesListOne);
		Collections.sort(roomClassesListTwo);
		boolean isEqual = roomClassesListOne.equals(roomClassesListTwo);
		logger.info(isEqual);
		assertEquals(isEqual, true, "Failed to active room class");
		testSteps.add("Verified Active Room Class");
		logger.info("Verified Active Room Class");

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifiedConditionsPromoCode> ' Description: <verified
	 * Condition promo Code> ' Input parameters: < WebDriver
	 * driver,ArrayList<String> testSteps,String promoCode> ' Return value:
	 * <ArrayList<String>> ' Created By: <Gangotri Sikheria> ' ' Created On:
	 * <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifiedConditionsPromoCode(WebDriver driver, ArrayList<String> testSteps, String promoCode)
			throws InterruptedException {
		String promocodeText = "Guest must enter promo code " + promoCode + "";
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Utility.ScrollToElement(elements.ratesTab, driver);
		String path = "//div[@class='rate-plan-description']/following-sibling::div";
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		String conditionText = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML;",
				element);
		logger.info(conditionText);
		assertTrue(conditionText.contains(promocodeText), "Failed to verify Rate Promo Code");
		testSteps.add("Verified Conditions PromoCode <b>" + promocodeText + "</b>");
		logger.info("Verified Conditions PromoCode " + promocodeText);

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifiedConditionsStayNight> ' Description: <verified
	 * Condition Stay Night> ' Input parameters: < WebDriver
	 * driver,ArrayList<String> testSteps,String night> ' Return value:
	 * <ArrayList<String>> ' Created By: <Gangotri Sikheria> ' ' Created On:
	 * <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifiedConditionsStayNight(WebDriver driver, ArrayList<String> testSteps, String night)
			throws InterruptedException {
		String nightText = "Guest must stay " + night + " nights";
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Utility.ScrollToElement(elements.ratesTab, driver);
		String path = "//div[@class='rate-plan-description']/following-sibling::div";
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		String conditionText = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML;",
				element);
		logger.info(conditionText);
		assertTrue(conditionText.contains(nightText), "Failed to verify Rate night text");
		testSteps.add("Verified Conditions Stay Nights <b>" + nightText + "</b>");
		logger.info("Verified Conditions Stay Nights " + nightText);

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifiedConditionsBookAdvanceDay> ' Description:
	 * <verified Condition Book Now> ' Input parameters: < WebDriver
	 * driver,ArrayList<String> testSteps,String minDay,String maxDay> ' Return
	 * value: <ArrayList<String>> ' Created By: <Gangotri Sikheria> ' ' Created
	 * On: <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifiedConditionsBookAdvanceDay(WebDriver driver, ArrayList<String> testSteps, String minDay,
			String maxDay) throws InterruptedException {
		String advanceMinText = "Guest must purchase at least " + minDay + " days in advance";
		String advanceMaxText = "Guest must purchase " + minDay + " to " + maxDay + " days in advance";

		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Utility.ScrollToElement(elements.ratesTab, driver);
		String path = "//div[@class='rate-plan-description']/following-sibling::div";
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		String conditionText = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML;",
				element);
		String conditionTextSpace = conditionText.replaceAll("[\\n\\t ]", "");
		logger.info(conditionTextSpace);
		advanceMinText = advanceMinText.replaceAll("[\\n\\t ]", "");
		advanceMaxText = advanceMaxText.replaceAll("[\\n\\t ]", "");
		logger.info(advanceMinText);
		logger.info(advanceMaxText);
		if (advanceMaxText.contains(maxDay)) {
			assertTrue(conditionTextSpace.contains(advanceMaxText), "Failed to verify Rate advance text");
			testSteps.add("Verified Conditions Advance Day <b>" + advanceMaxText + "</b>");
			logger.info("Verified Conditions Advance Day" + advanceMaxText);
		} else if (advanceMinText.contains(minDay)) {
			assertTrue(conditionTextSpace.contains(advanceMinText), "Failed to verify Rate advance text");
			testSteps.add("Verified Conditions Advance Day <b>" + advanceMinText + "</b>");
			logger.info("Verified Conditions Advance Day" + advanceMinText);
		}

	}

	public String getRatePlanDescription(WebDriver driver, ArrayList<String> testSteps) {
		String description = null;
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.ratePlanDesc);
		description = elements.ratePlanDesc.getText();
		testSteps.add("Rate Plan Description is <b>" + description + "</b>");
		logger.info("Rate Plan Description is" + description);
		return description;
	}

	public String getRateConditionsDescription(WebDriver driver, ArrayList<String> testSteps) {
		String conditionDesc = null;
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		boolean isExist = elements.ratePlanConditionDesc.isDisplayed();
		if (isExist) {
			testSteps.add("<b>============Get Rate Plan Conditions============</b>");
			conditionDesc = elements.ratePlanConditionDesc.getText();
			testSteps.add("Rate Plan Conditions Description is <b>" + conditionDesc + "</b>");
			logger.info("Rate Plan Conditions Description is" + conditionDesc);
		}

		return conditionDesc;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getActiveAndInactiveRatePlanNames> ' Description: <This
	 * method will return rate Plan List Active/InActive based on passed String>
	 * ' Input parameters: <WebDriver driver,String ratePlanType> ' Return value
	 * : <String> ' Created By: <Farhan Ghaffar> ' Created On: <07/08/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> getInActiveRatePlanNames(WebDriver driver, String ratePlanType)
			throws InterruptedException {
		ArrayList<String> getNames = new ArrayList<String>();
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		Wait.WaitForElement(driver, OR_RatesGrid.ratePlanNamesList);
		for (int i = 0; i < elements.ratePlanNames.size(); i++) {

			Utility.ScrollToElement(elements.ratePlanNames.get(i), driver);

			if (ratePlanType.equals("inactive")) {

				if (elements.ratePlanNames.get(i).getText().contains("[Inactive]")) {
					elements.ratePlanNames.get(i).click();
					if (elements.getRatePlanDescriptionList.size() > 0) {
						if (!elements.getRatePlanDescription.getText().contains("Interval")) {
							getNames.add(elements.selectedRatePlanName.getText());

						}
					}
					jse.executeScript("window.scrollBy(0,-400)");
					elements.ratePlanArrow.click();
				}

			}
		}
		logger.info("Inacivt size:" + getNames.size());
		return getNames;
	}

	public ArrayList<String> getActiveRatePlanNames(WebDriver driver, String ratePlanType) throws InterruptedException {
		ArrayList<String> getNames = new ArrayList<String>();
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.ratePlanNamesList);
		int size = elements.ratePlanNames.size();
		logger.info(size);
		for (int i = 0; i < size; i++) {
			if (i > 0) {
				Wait.wait2Second();
				elements.ratePlanArrow.click();

			}
			Utility.ScrollToElement(elements.ratePlanNames.get(i), driver);

			String rate = elements.ratePlanNames.get(i).getText();
			if (ratePlanType.equals("active")) {
				elements.ratePlanNames.get(i).click();

				if (elements.getRatePlanDescriptionList.size() > 0) {
					if (!elements.getRatePlanDescription.getText().contains("Interval")
							&& !rate.equals("Manual Override") && !rate.contains("[Inactive]")) {
						System.out.println("Active added if:" + rate);

						getNames.add(rate);
						logger.info(rate);

					}
				}

				else if (elements.getRatePlanDescriptionList.size() == 0 && !rate.equals("Manual Override")) {
					System.out.println("Active added Else:" + rate);
					getNames.add(rate);
					logger.info(rate);

				}
			}

		}

		logger.info(getNames.size());
		return getNames;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <removeAllRoomClass> ' Description: <This method will
	 * remove corresponding item from dropdowns in bulk update popup> ' Input
	 * parameters: <WebDriver driver, String dropDownName, String roomClass> '
	 * Return value : <ArrayList<String>> ' Created By: <Farhan Ghaffar> '
	 * Created On: <07/13/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> removeSelectedOptionFromField(WebDriver driver, String dropDownName, String selectedOption)
			throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<>();

		String roomClassesRemovePath = "//label[text()='" + dropDownName
				+ "']//following-sibling::div//span[contains(text(),'" + selectedOption
				+ "')]//preceding::span[@class='Select-value-icon'][1]";
		driver.findElement(By.xpath(roomClassesRemovePath)).click();
		ratesGridLogger.info("click on (" + selectedOption + ") remove icon");
		testSteps.add("click on (" + selectedOption + ") remove icon");

		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getRoomClassListSize> ' Description: <This method will
	 * return list size of specific dropdowns in rate grid > ' Input parameters:
	 * <WebDriver driver, String dropDownName, String dropDownOption> ' Return
	 * value : <int > ' Created By: <Farhan Ghaffar> ' Created On: <07/13/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public int getDropDownOptionsSize(WebDriver driver, String dropDownName, String dropDownOption)
			throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<>();
		String bulkUpdateDropDownPath = "(//label[text()='" + dropDownName
				+ "']//following-sibling::div//input[@role='combobox'])[1]";

		Wait.WaitForElement(driver, bulkUpdateDropDownPath);
		Wait.waitForElementToBeVisibile(By.xpath(bulkUpdateDropDownPath), driver);
		Wait.waitForElementToBeClickable(By.xpath(bulkUpdateDropDownPath), driver);
		WebElement bulkUpdateDropdown = driver.findElement(By.xpath(bulkUpdateDropDownPath));
		Utility.ScrollToElement_NoWait(bulkUpdateDropdown, driver);
		bulkUpdateDropdown.sendKeys(dropDownOption);
		ratesGridLogger.info("Entered " + dropDownName + " name : " + dropDownOption);
		testSteps.add("Entered " + dropDownName + " name : " + dropDownOption);

		String roomClassesPath = "(//div[@class='Select-menu-outer'])[1]//div[contains(@class,'Select-option')]";
		List<WebElement> roomClassesElement = driver.findElements(By.xpath(roomClassesPath));
		System.out.println("roomClassesElement: " + roomClassesElement.size());

		return roomClassesElement.size();
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectBulkUpdateOption> ' Description: <This method will
	 * select Bulk Update type w.r.t option > ' Input parameters: <WebDriver
	 * driver, String dropDownName, String dropDownOption> ' Return value : <int
	 * > ' Created By: <Farhan Ghaffar> ' Created On: <07/13/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> selectBulkUpdateOption(WebDriver driver, String option) {

		ArrayList<String> testSteps = new ArrayList<>();
		String rateGridBulkUpdateAvailable = "(//li//a[contains(text(),'" + option + "')])[1]";
		Wait.WaitForElement(driver, rateGridBulkUpdateAvailable);
		Wait.waitForElementToBeVisibile(By.xpath(rateGridBulkUpdateAvailable), driver);
		Wait.waitForElementToBeClickable(By.xpath(rateGridBulkUpdateAvailable), driver);
		WebElement rateGirdOption = driver.findElement(By.xpath(rateGridBulkUpdateAvailable));
		rateGirdOption.click();
		testSteps.add("Select " + option + " from bulk update");
		ratesGridLogger.info("Select " + option + " from bulk update");
		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickTotalOccupancy> ' Description: <This method will
	 * click total occupancy in bulk update popup> ' Input parameters:
	 * <WebDriver driver> ' Return value : <ArrayList<String>> ' Created By:
	 * <Farhan Ghaffar> ' Created On: <07/03/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> clickTotalOccupancy(WebDriver driver, String isEnable) throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.totalOccupancy);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.totalOccupancy), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.totalOccupancy), driver);
		Utility.ScrollToElement_NoWait(ratesGrid.totalOccupancy, driver);
		if (isEnable.equalsIgnoreCase("Yes")) {
			Wait.WaitForElement(driver, OR_RatesGrid.totalOccupanyValue);
			Boolean isOccupancyEnabled = ratesGrid.totalOccupanyValue.isEnabled();

			if (isOccupancyEnabled) {
				testSteps.add("Total occupancy already clicked");
				ratesGridLogger.info("Total occupancy already clicked");
			} else {
				ratesGrid.totalOccupancy.click();
				testSteps.add("Enabled total occupancy");
				ratesGridLogger.info("Enabled total occupancy");

			}
		} else if (isEnable.equalsIgnoreCase("No")) {
			Wait.WaitForElement(driver, OR_RatesGrid.totalOccupanyValue);
			Boolean isOccupancyEnabled = ratesGrid.totalOccupanyValue.isEnabled();

			if (isOccupancyEnabled) {
				ratesGrid.totalOccupancy.click();
				testSteps.add("Total occupancy disabled");
				ratesGridLogger.info("Total occupancy disabled");
			} else {
				testSteps.add("Total occupancy already disabled");
				ratesGridLogger.info("Total occupancy already disabled");

			}
		}
		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectTotalOccupancyType> ' Description: <This method
	 * will select total occupancy type in bulk update popup> ' Input
	 * parameters: <WebDriver driver> ' Return value : <ArrayList<String>> '
	 * Created By: <Farhan Ghaffar> ' Created On: <07/03/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> selectTotalOccupancyType(WebDriver driver, String totalOccupancyType)
			throws InterruptedException {

		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_RatesGrid.totalOccupancyType);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.totalOccupancyType), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.totalOccupancyType), driver);
		Utility.ScrollToElement_NoWait(ratesGrid.totalOccupancyType, driver);
		ratesGrid.totalOccupancyType.click();
		ratesGridLogger.info("Dropdown clicked");
		if (totalOccupancyType.equalsIgnoreCase("Greater")) {
			String greater = "greater than";
			ratesGridLogger.info(greater);
			String path = "(//span[text()='For days that total occupancy is']//parent::label//parent::div//div[@class='Select-menu-outer'])[1]//div[contains(text(),'"
					+ greater + "')]";
			driver.findElement(By.xpath(path)).click();
			testSteps.add("Selected occupancy type greater than");
			ratesGridLogger.info("Selected occupancy type greater than");

		} else if (totalOccupancyType.equalsIgnoreCase("Less")) {
			String less = "less than";
			ratesGridLogger.info(less);
			String path = "(//span[text()='For days that total occupancy is']//parent::label//parent::div//div[@class='Select-menu-outer'])[1]//div[contains(text(),'"
					+ less + "')]";
			driver.findElement(By.xpath(path)).click();
			testSteps.add("Selected occupancy type less than");
			ratesGridLogger.info("Selected occupancy type less than");
		}

		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <enterOccupancyValue> ' Description: <This method will
	 * enter total occupancy value in bulk update popup> ' Input parameters:
	 * <WebDriver driver, String totalOccupancyValue> ' Return value :
	 * <ArrayList<String>> ' Created By: <Farhan Ghaffar> ' Created On:
	 * <07/03/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> enterOccupancyValue(WebDriver driver, String totalOccupancyValue)
			throws InterruptedException {

		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_RatesGrid.totalOccupanyValue);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.totalOccupanyValue), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.totalOccupanyValue), driver);
		Utility.ScrollToElement_NoWait(ratesGrid.totalOccupancyType, driver);
		ratesGrid.totalOccupanyValue.sendKeys(totalOccupancyValue);
		testSteps.add("Entered total occupancy value : " + totalOccupancyValue);
		ratesGridLogger.info("Entered total occupancy value : " + totalOccupancyValue);
		return testSteps;
	}

	public ArrayList<String> verifyOccupancyValueInputField(WebDriver driver) throws InterruptedException {

		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_RatesGrid.totalOccupanyValue);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.totalOccupanyValue), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.totalOccupanyValue), driver);
		Utility.ScrollToElement_NoWait(ratesGrid.totalOccupancyType, driver);
		for (int i = 0; i < specialChars.length(); i++) {
			ratesGrid.totalOccupanyValue.sendKeys(Character.toString(specialChars.charAt(i)));
		}

		assertEquals(ratesGrid.totalOccupanyValue.getAttribute("value").isEmpty(), true,
				"Failed: Occupancy Input Field Taking Special Characters");
		testSteps.add("Verified: Occupancy Input Field Not Taking Special Characters");
		ratesGridLogger.info("Verified: Occupancy Input Field Not Taking Special Characters");
		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyTotalOccupancyType> ' Description: <This method
	 * will return total occupancy type enable/disable status in bulk update
	 * popup> ' Input parameters: <WebDriver driver> ' Return value : <String> '
	 * Created By: <Farhan Ghaffar> ' Created On: <07/03/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public Boolean verifyTotalOccupancyType(WebDriver driver) throws InterruptedException {

		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.totalOccupancyTypeVisibility);
		Boolean isEnabled = false;
		String classes = ratesGrid.totalOccupancyTypeVisibility.getAttribute("class");
		if (classes.contains("is-disabled")) {
			isEnabled = false;
		} else {
			isEnabled = true;
		}
		ratesGridLogger.info("totalOccupancyType classes :" + classes);
		return isEnabled;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyTotalOccupanyValue> ' Description: <This method
	 * will return total occupancy value enable/disable status in bulk update
	 * popup> ' Input parameters: <WebDriver driver> ' Return value : <String> '
	 * Created By: <Farhan Ghaffar> ' Created On: <07/03/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public Boolean verifyTotalOccupanyValue(WebDriver driver) throws InterruptedException {

		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.totalOccupanyValue);
		Boolean isEnabled = ratesGrid.totalOccupanyValue.isEnabled();
		ratesGridLogger.info("totalOccupanyValue :" + isEnabled);

		return isEnabled;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyTotalOccupanyIcon> ' Description: <This method will
	 * return total occupancy icon text in bulk update popup> ' Input
	 * parameters: <WebDriver driver> ' Return value : <ArrayList<String>> '
	 * Created By: <Farhan Ghaffar> ' Created On: <07/03/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> verifyTotalOccupanyIcon(WebDriver driver) throws InterruptedException {

		// Instantiate Action Class
		Actions actions = new Actions(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.occupancyIcon);
		actions.moveToElement(ratesGrid.OccupancyIcon).perform();
		String pageSource = driver.getPageSource();
		ratesGridLogger.info(pageSource);
		testSteps.add(pageSource);
		return testSteps;
	}

	public ArrayList<String> selectItemsFromDropDownsValidation(WebDriver driver, String dropDownName,
			String selectedOption) throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<>();
		// Base Case If All (Both) InActive/Active Rate Plan Selected
		String ratePlanActiveRateSelected = "//label[text()='Rate Plan']//following-sibling::div//div[1]//span[contains(text(),'All Active')]";
		String ratePlanInActiveRateSelected = "//label[text()='Rate Plan']//following-sibling::div//div[1]//span[contains(text(),'All Inactive')]";
		String bulkUpdateDropDownPath = "//label[text()='" + dropDownName + "']//following-sibling::div//div[1]";
		Wait.WaitForElement(driver, bulkUpdateDropDownPath);
		Wait.waitForElementToBeVisibile(By.xpath(bulkUpdateDropDownPath), driver);
		Wait.waitForElementToBeClickable(By.xpath(bulkUpdateDropDownPath), driver);
		WebElement bulkUpdateDropdown = driver.findElement(By.xpath(bulkUpdateDropDownPath));
		WebElement ratePlanActiveRateSelectedElement = driver.findElement(By.xpath(ratePlanActiveRateSelected));
		WebElement ratePlanInActiveRateSelectedElement = driver.findElement(By.xpath(ratePlanInActiveRateSelected));
		Utility.ScrollToElement_NoWait(bulkUpdateDropdown, driver);
		String selectionElementsPath = "(//div[@class='Select-menu-outer'])[1]//div[contains(@class,'Select-option')]";
		System.out.print("Selction:" + ratePlanActiveRateSelectedElement.isSelected());
		System.out.print("Selction:" + ratePlanActiveRateSelectedElement.isEnabled());
		System.out.print("Selction:" + ratePlanActiveRateSelectedElement.isDisplayed());

		if (ratePlanActiveRateSelectedElement.isSelected() && ratePlanInActiveRateSelectedElement.isSelected()) {
			bulkUpdateDropdown.click();
			testSteps.add(dropDownName + " drop down clicked");
			ratesGridLogger.info(dropDownName + " drop down clicked");
			List<WebElement> selectionElementsList = driver.findElements(By.xpath(selectionElementsPath));

		} else {
			List<WebElement> selectionElementsList = driver.findElements(By.xpath(selectionElementsPath));

			// Just For RatePlan Drop-down Validation
			if (dropDownName.equalsIgnoreCase("Rate Plan")) {
				// If All Active RatePlan selected
				if (selectedOption.contains("All Active Rate Plans")) {
					int countToSaveInActiveRates = 0;
					for (int i = 0; i < selectionElementsList.size(); i++) {
						String selectionElementName = selectionElementsList.get(i).getText().trim();

						if (selectionElementName.toLowerCase().contains("inactive")) {
							countToSaveInActiveRates = countToSaveInActiveRates + 1;
							testSteps.add("RatePlan  : " + selectionElementName);
							ratesGridLogger.info("RatePlan  : " + selectionElementName);
						}

					}
					assertEquals(countToSaveInActiveRates, selectionElementsList.size(),
							"Failed: Not All Rate Plans are InActive");
					testSteps.add("Verified InActive RatePlan Count  : " + countToSaveInActiveRates);
					ratesGridLogger.info("Verified InActive RatePlan Count  : " + countToSaveInActiveRates);

				}
				// If All inActive RatePlan Selected
				if (selectedOption.contains("All Inactive Rate Plans")) {
					int countToSaveActiveRates = 0;
					for (int i = 0; i < selectionElementsList.size(); i++) {
						String selectionElementName = selectionElementsList.get(i).getText().trim();

						if (!selectionElementName.toLowerCase().contains("Inactive")) {
							countToSaveActiveRates++;
							testSteps.add("RatePlan  : " + selectionElementName);
							ratesGridLogger.info("RatePlan  : " + selectionElementName);
						}

					}
					assertEquals(countToSaveActiveRates, selectionElementsList.size(),
							"Failed: Not All Rate Plans are InActive");
					testSteps.add("Verified InActive RatePlan Count  : " + countToSaveActiveRates);
					ratesGridLogger.info("Verified InActive RatePlan Count  : " + countToSaveActiveRates);

				}
			}
		}
		return testSteps;
	}

	public ArrayList<String> selectItemsFromDropDowns(WebDriver driver, String dropDownName, String selectionElement)
			throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<>();

		String bulkUpdateDropDownPath = "//label[text()='" + dropDownName + "']//following-sibling::div//div[1]";
		Wait.WaitForElement(driver, bulkUpdateDropDownPath);
		Wait.waitForElementToBeVisibile(By.xpath(bulkUpdateDropDownPath), driver);
		Wait.waitForElementToBeClickable(By.xpath(bulkUpdateDropDownPath), driver);
		WebElement bulkUpdateDropdown = driver.findElement(By.xpath(bulkUpdateDropDownPath));
		Utility.ScrollToElement_NoWait(bulkUpdateDropdown, driver);
		bulkUpdateDropdown.click();
		testSteps.add(dropDownName + " drop down clicked");
		ratesGridLogger.info(dropDownName + " drop down clicked");
		String selectionElementsPath = "(//div[@class='Select-menu-outer'])[1]//div[contains(@class,'Select-option')]";
		List<WebElement> selectionElementsList = driver.findElements(By.xpath(selectionElementsPath));

		for (int i = 0; i < selectionElementsList.size(); i++) {
			String selectionElementName = selectionElementsList.get(i).getText().trim();
			ratesGridLogger.info("Get list items : " + selectionElementName);

			if (selectionElementName.contains(selectionElement)) {

				selectionElementsList.get(i).click();
				testSteps.add("Entered : " + selectionElement);
				ratesGridLogger.info("Entered : " + selectionElement);
				break;
			}

		}

		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyDropDownDisableOnAllListSelection> ' Description:
	 * <This method will verify RatePlan/RoomCLass/Source DropDown is disabled
	 * after Selecting all list String> ' Input parameters: <WebDriver
	 * driver,String dropDownName,String dropDownSelector> ' Return value :
	 * ArrayList<String> ' Created By: Aqsa Manzoor> ' Created On: <07/14/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> verifyDropDownDisableOnAllListSelection(WebDriver driver, String dropDownName,
			String dropDownSelector) throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<>();
		if (dropDownSelector.contains("All")) {

			// String noResultsFound = "//span[text()='no results found']";
			String bulkUpdateDropDownPath = "//label[text()='" + dropDownName + "']//following-sibling::div//div[1]";
			Wait.WaitForElement(driver, bulkUpdateDropDownPath);
			Wait.waitForElementToBeVisibile(By.xpath(bulkUpdateDropDownPath), driver);
			Wait.waitForElementToBeClickable(By.xpath(bulkUpdateDropDownPath), driver);
			WebElement bulkUpdateDropdown = driver.findElement(By.xpath(bulkUpdateDropDownPath));
			Utility.ScrollToElement_NoWait(bulkUpdateDropdown, driver);
			bulkUpdateDropdown.click();
			testSteps.add(dropDownName + " drop down clicked");
			ratesGridLogger.info(dropDownName + " drop down clicked");
			String selectionElementsPath = "(//div[@class='Select-menu-outer'])[1]//div[contains(@class,'Select-option')]";
			try {

				List<WebElement> selectionElementsList = driver.findElements(By.xpath(selectionElementsPath));
				if (selectionElementsList.get(0).isDisplayed()) {
					System.out.print(" if");
					testSteps.add("Failed: The Dropdown List :" + dropDownName + "  show");
					ratesGridLogger.info("Failed:  The Dropdown List :" + dropDownName + " show");
				} else {
					testSteps.add("Verified: The Dropdown List :" + dropDownName + " didn't show");
					ratesGridLogger.info("Verified:  The Dropdown List :" + dropDownName + " didn't show");
				}
			} catch (Exception e) {
				testSteps.add("Verified: The Dropdown List :" + dropDownName + " didn't show");
				ratesGridLogger.info("Verified:  The Dropdown List :" + dropDownName + " didn't show");

			}

		}
		return testSteps;

	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyCloseIcon> ' Description: <This method will return
	 * close icon text in bulk update popup> ' Input parameters: <WebDriver
	 * driver> ' Return value : <String> ' Created By: <Farhan Ghaffar> '
	 * Created On: <07/08/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public String getCloseIconText(WebDriver driver) throws InterruptedException {

		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.BULKUPDATE_RATES_CLOSEBUTTON);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.BULKUPDATE_RATES_CLOSEBUTTON), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.BULKUPDATE_RATES_CLOSEBUTTON), driver);
		Utility.ScrollToElement_NoWait(elements.BULKUPDATE_RATES_CLOSEBUTTON, driver);
		String closeText = elements.BULKUPDATE_RATES_CLOSEBUTTON.getText();
		return closeText;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyPercentageSignOccupancyValueField> ' Description:
	 * <This method will verifies the color of the selected Source> ' Input
	 * parameters: <WebDriver driver,String dropdownSelector> ' Return value :
	 * <ArrayList<String>> ' Created By: <Aqsa Manzoor> ' Created On:
	 * <07/14/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> verifyColorCodeofSources(WebDriver driver, String dropDownSelector) {
		ArrayList<String> testSteps = new ArrayList<>();
		String expectedColorCode = "#ed6a5a";
		String colorPath = "";
		if (dropDownSelector.contains("All")) {
			colorPath = "(//span[contains(text(),'All')]/ancestor::div[@class='Select-value'])[3]";

		} else {

			colorPath = "//span[contains(text(),'" + dropDownSelector + "')]/ancestor::div[@class='Select-value']";
		}
		Wait.WaitForElement(driver, colorPath);
		Wait.waitForElementToBeVisibile(By.xpath(colorPath), driver);
		WebElement colorPathElement = driver.findElement(By.xpath(colorPath));
		String foundColor = colorPathElement.getCssValue("background-color");
		String foundColorCode = Color.fromString(foundColor).asHex();

		assertEquals(foundColorCode, expectedColorCode, "Failed: Sources Element Color didn't match");

		testSteps.add("Sources Element Color matched");
		ratesGridLogger.info("Sources Element Color matched");
		return testSteps;

	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyNoRoomsSelectedInUpdateRates> ' Description: <This
	 * method will verify When No rroom is Selected in RoomClass Dropdown it
	 * shows in Update Rate By roomClass List after Selecting all list String> '
	 * Input parameters: <WebDriver driver,String roomClassName > ' Return value
	 * : ArrayList<String> ' Created By: Aqsa Manzoor> ' Created On:
	 * <07/14/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> verifyNoRoomsSelectedInUpdateRates(WebDriver driver, String roomClassName) {
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();

		if (!roomClassName.isEmpty()) {
			String removeRoomClassButton = "//span[contains(text(),'" + roomClassName
					+ "')]/preceding-sibling::span[@class='Select-value-icon']";
			WebElement removeRoomClassButtoneElement = driver.findElement(By.xpath(removeRoomClassButton));
			removeRoomClassButtoneElement.click();

		}
		assertEquals(ratesGrid.NO_ROOM_CLASS_SELECTED.isDisplayed(), true, "Failed: No rooms selected didn't show");
		testSteps.add("Verified:" + ratesGrid.NO_ROOM_CLASS_SELECTED.getText());
		ratesGridLogger.info("Verified:" + ratesGrid.NO_ROOM_CLASS_SELECTED.getText());
		return testSteps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <updateNightlyRate> ' Description: <This method will send
	 * values to Nightly Rate In Update Rate Type By New Rate shows in Update
	 * Rate By roomClass List after Selecting all list String> ' Input
	 * parameters: <WebDriver driver,int index,String nightlyRate > ' Return
	 * value : ArrayList<String> ' Created By: Aqsa Manzoor> ' Created On:
	 * <07/10/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> updateNightlyRate(WebDriver driver, int index, String nightlyRate)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);

		Wait.WaitForElement(driver, OR_RatesGrid.UPDATE_RATE_NEWRATE_NIGHTLYRATE);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.UPDATE_RATE_NEWRATE_NIGHTLYRATE), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.UPDATE_RATE_NEWRATE_NIGHTLYRATE), driver);
		ratesGrid.UPDATE_RATE_NEWRATE_NIGHTLYRATE.get(index).sendKeys(nightlyRate);

		test_steps.add("Updated Nighlty Rate");
		ratesGridLogger.info("Updated Nighlty Rate");
		return test_steps;

	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <updateNightlyRate> ' Description: <This method will send
	 * values to Additional Adult Rate In Update Rate Type By New Rate shows in
	 * Update Rate By roomClass List after Selecting all list String> ' Input
	 * parameters: <WebDriver driver,int index,String nightlyRate > ' Return
	 * value : ArrayList<String> ' Created By: Aqsa Manzoor> ' Created On:
	 * <07/10/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> updateAdditionalAdultRate(WebDriver driver, int index, String additionalAdult)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);

		Wait.WaitForElement(driver, OR_RatesGrid.UPDATE_RATE_NEWRATE_ADDITIONALADULT);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.UPDATE_RATE_NEWRATE_ADDITIONALADULT), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.UPDATE_RATE_NEWRATE_ADDITIONALADULT), driver);
		ratesGrid.UPDATE_RATE_NEWRATE_ADDITIONALADULT.get(index).sendKeys(additionalAdult);

		test_steps.add("Updated Additional Adult Rate");
		ratesGridLogger.info("Updated Additional Adult Rate");
		return test_steps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <updateNightlyRate> ' Description: <This method will send
	 * values to Additional Child Rate In Update Rate Type By New Rate shows in
	 * Update Rate By roomClass List after Selecting all list String> ' Input
	 * parameters: <WebDriver driver,int index,String nightlyRate > ' Return
	 * value : ArrayList<String> ' Created By: Aqsa Manzoor> ' Created On:
	 * <07/10/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> updateAdditionalChildRate(WebDriver driver, int index, String additionalChild)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.UPDATE_RATE_NEWRATE_ADDITIONALCHILD);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.UPDATE_RATE_NEWRATE_ADDITIONALCHILD), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.UPDATE_RATE_NEWRATE_ADDITIONALCHILD), driver);

		ratesGrid.UPDATE_RATE_NEWRATE_ADDITIONALCHILD.get(index).sendKeys(additionalChild);

		test_steps.add("Updated Additional Child Rate");
		ratesGridLogger.info("Updated Additional Child Rate");
		return test_steps;

	}

	/*
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectBulkUpdateRatesOption> ' Description: <Select the
	 * Option Given in Update Rate Options > ' ' Input parameters:
	 * <WebDriver,String> ' Return: <ArrayList<String>> Created By: <Aqsa
	 * Manzoor> ' Created On: <30 June 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> selectBulkUpdateRatesOption(WebDriver driver, int rateTypeIndex)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Utility.ScrollToElement(ratesGrid.UPDATERATES_OPTIONS.get(rateTypeIndex), driver);
		ratesGrid.UPDATERATES_OPTIONS.get(rateTypeIndex).click();

		return test_steps;

	}

	/*
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickYesUpdateButton> ' Description: <Thie method will
	 * Click Yes Button After Update Rate Button is Clicked > ' ' Input
	 * parameters: <WebDriver> ' Return: <ArrayList<String>> Created By: <Aqsa
	 * Manzoor> ' Created On: <07 July 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> clickYesUpdateButton(WebDriver driver) {

		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_RatesGrid.UPDATE_RATE_CONFIRMATION_YES_UPDATE_BUTTON);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.UPDATE_RATE_CONFIRMATION_YES_UPDATE_BUTTON), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.UPDATE_RATE_CONFIRMATION_YES_UPDATE_BUTTON), driver);
		ratesGrid.UPDATE_RATE_CONFIRMATION_YES_UPDATE_BUTTON.click();
		testSteps.add("Yes,Update button clicked");
		ratesGridLogger.info("Yes,Update button clicked");
		return testSteps;
	}

	/*
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <previousStartEndDateValidation> ' Description: <Thie
	 * method will verify that The previous Date is Disable in CheckIn CHeckout
	 * Calendar > ' ' Input parameters: <WebDriver,String> ' Return:
	 * <ArrayList<String>> Created By: <Aqsa Manzoor> ' Created On: <07 July
	 * 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> previousStartEndDateValidation(WebDriver driver, String previousDate)
			throws InterruptedException {

		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		String activeColorCode = "rgba(0, 0, 0, 1)";
		String inActiveColorCode = "rgba(204, 204, 204, 1)";
		Wait.WaitForElement(driver, OR_RatesGrid.UPDATE_RATE_DATE_PICKER);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.UPDATE_RATE_DATE_PICKER), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.UPDATE_RATE_DATE_PICKER), driver);
		previousDate = previousDate.split("/")[1];
		ratesGrid.UPDATE_RATE_DATE_PICKER.get(0).click();
		String previousValueStartDatePath = "//label[text()='Start']/following-sibling::div//span//*[contains(text(),'"
				+ previousDate + "')]";
		WebElement previousValueStartDatePathElement = driver.findElement(By.xpath(previousValueStartDatePath));
		assertEquals(previousValueStartDatePathElement.getCssValue("color").equals(inActiveColorCode), true,
				"Failed: previous Date is Active");
		assertEquals(previousValueStartDatePathElement.getCssValue("color").equals(activeColorCode), false,
				"Failed: previous Date is Active");
		ratesGridLogger.info("Previous Date in CheckIn Calendar is InActive");
		testSteps.add("Previous Date in CheckIn Calendar is InActive");
		Wait.wait5Second();
		ratesGrid.UPDATE_RATE_DATE_PICKER.get(1).click();
		String previousValueEndDatePath = "//label[text()='End']/following-sibling::div//span//*[contains(text(),'"
				+ previousDate + "')]";
		WebElement previousValueEndDatePathElement = driver.findElement(By.xpath(previousValueEndDatePath));

		assertEquals(previousValueEndDatePathElement.getCssValue("color").equals(inActiveColorCode), true,
				"Failed: previous Date is Active");
		assertEquals(previousValueEndDatePathElement.getCssValue("color").equals(activeColorCode), false,
				"Failed: previous Date is Active");
		ratesGridLogger.info("Previous Date in Checkout Calendar is InActive");
		testSteps.add("Previous Date in Checkout Calendar is InActive");

		return testSteps;

	}

	public ArrayList<String> bulkUpdatePoppupHeading(WebDriver driver, String type) {

		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		String bulkUpdatePopupHeading = "//div[text()='Bulk update - " + type + "']";
		Wait.WaitForElement(driver, bulkUpdatePopupHeading);
		Wait.waitForElementToBeVisibile(By.xpath(bulkUpdatePopupHeading), driver);
		Wait.waitForElementToBeClickable(By.xpath(bulkUpdatePopupHeading), driver);
		WebElement bulkUpdatePopupHeadingElement = driver.findElement(By.xpath(bulkUpdatePopupHeading));
		assertTrue(bulkUpdatePopupHeadingElement.isDisplayed(),
				"Bulk Update " + type + " popup Heading didn't display");
		testSteps.add("Verified Bulk update " + type + "  popup heading");
		ratesGridLogger.info("Verified Bulk update " + type + "  popup heading");
		return testSteps;

	}

	public ArrayList<String> selectRoomClass(WebDriver driver, String roomClass) throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);

		Wait.WaitForElement(driver, OR_RatesGrid.bulkUpdatePopupRoomClass);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.bulkUpdatePopupRoomClass), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.bulkUpdatePopupRoomClass), driver);
		Utility.ScrollToElement_NoWait(ratesGrid.bulkUpdatePopupRoomClass, driver);
		ratesGrid.bulkUpdatePopupRoomClass.click();
		String roomClassesPath = "(//div[@class='Select-menu-outer'])[1]//div[contains(@class,'Select-option')]";
		List<WebElement> roomClassesElement = driver.findElements(By.xpath(roomClassesPath));
		System.out.println("Room Class Before for" + roomClass);
		System.out.println("Room Class Size " + roomClassesElement.size());

		for (int i = 0; i < roomClassesElement.size(); i++) {

			String roomClassName = roomClassesElement.get(i).getText().trim();
			ratesGridLogger.info("GetRoomClass : " + roomClassName);

			if (roomClassName.contains(roomClass)) {

				roomClassesElement.get(i).click();
				testSteps.add("Entered RoomClass : " + roomClass);
				ratesGridLogger.info("Entered RoomClass : " + roomClass);
				break;
			}

		}

		return testSteps;
	}

	public String getTotalDaysText(WebDriver driver, String daysText) {

		String daysTextPath = "//p[contains(text(),'" + daysText + "')]";
		WebElement daysTextElement = driver.findElement(By.xpath(daysTextPath));
		Wait.WaitForElement(driver, daysTextPath);
		Wait.waitForElementToBeVisibile(By.xpath(daysTextPath), driver);
		Wait.waitForElementToBeClickable(By.xpath(daysTextPath), driver);
		String text = daysTextElement.getText();
		ratesGridLogger.info("bulkUpdatePopup Days text : " + text);
		return text;

	}

	public String getStartDate(WebDriver driver) {

		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.START_DATE_INPUT);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.START_DATE_INPUT), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.START_DATE_INPUT), driver);
		String date = elements.startDateInput.getAttribute("value");
		ratesGridLogger.info("Start date: " + date);
		return date;
	}

	public ArrayList<String> verifyStartDateCalendar(WebDriver driver, String startDate)
			throws InterruptedException, ParseException {

		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_RatesGrid.START_DATE_INPUT);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.START_DATE_INPUT), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.START_DATE_INPUT), driver);
		elements.startDateInput.click();
		testSteps.add("Click start date");
		ratesGridLogger.info("Click start date");

		ratesGridLogger.info("==========VERIFYING MONTH YEAR HEADING IN CALENDAR==========");
		testSteps.add("==========VERIFYING MONTH YEAR HEADING IN CALENDAR==========");
		startDate = ESTTimeZone.reformatDate(startDate, "MM/dd/yyyy", "dd/MM/yyyy");

		String monthYear = Utility.get_MonthYear(startDate);
		String header = "(//label[text()='Start']//following-sibling::div//input//following::div[text()='" + monthYear
				+ "'])[1]";
		WebElement headerElement = driver.findElement(By.xpath(header));
		ratesGridLogger.info("Expected  : " + monthYear);
		testSteps.add("Expected Day : " + monthYear);

		String getHeader = headerElement.getText();
		ratesGridLogger.info("Found : " + getHeader);
		testSteps.add("Found : " + getHeader);

		Assert.assertEquals(getHeader, monthYear, "Failed : Header MonthYear doesn't match");
		testSteps.add("Verified month year heading in start date calendar");
		ratesGridLogger.info("Verified month year heading in start date calendar");

		ratesGridLogger.info("==========VERIFYING DEFAULT SELECTED DAY==========");
		testSteps.add("==========VERIFYING DEFAULT SELECTED DAY==========");

		String day = Utility.getDay(startDate);
		ratesGridLogger.info("Expected Day : " + day);
		testSteps.add("Expected Day : " + day);

		String selectedDay = elements.StartDateSelectedDay.getText();
		ratesGridLogger.info("Found : " + selectedDay);
		testSteps.add("Found : " + selectedDay);

		Assert.assertEquals(day, selectedDay, "Failed to match selected day");
		testSteps.add("Verified selected day");
		ratesGridLogger.info("Verified selected day");

		Assert.assertTrue(elements.TodayDateButton.isDisplayed(), "Failed today button doesn't exist");
		testSteps.add("Verified that today button is displaying at the footer of start date calendar");
		ratesGridLogger.info("Verified that today button is displaying at the footer of start date calendar");

		elements.startDateInput.sendKeys(Keys.TAB);
		return testSteps;
	}

	public String getEndDate(WebDriver driver) {

		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.END_DATE_INPUT);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.END_DATE_INPUT), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.END_DATE_INPUT), driver);
		String date = elements.EndDateInput.getAttribute("value");
		ratesGridLogger.info("End date: " + date);
		return date;

	}

	public ArrayList<String> verifyEndDateCalendar(WebDriver driver, String endDate)
			throws InterruptedException, ParseException {

		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_RatesGrid.END_DATE_INPUT);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.END_DATE_INPUT), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.END_DATE_INPUT), driver);
		elements.EndDateInput.click();
		ratesGridLogger.info("==========VERIFYING MONTH YEAR HEADING IN CALENDAR==========");
		testSteps.add("==========VERIFYING MONTH YEAR HEADING IN CALENDAR==========");

		endDate = ESTTimeZone.reformatDate(endDate, "MM/dd/yyyy", "dd/MM/yyyy");
		String monthYear = Utility.get_MonthYear(endDate);
		ratesGridLogger.info("Expected  : " + monthYear);
		testSteps.add("Expected Day : " + monthYear);

		String header = "(//label[text()='End']//following-sibling::div//input//following::div[text()='" + monthYear
				+ "'])[1]";
		WebElement headerElement = driver.findElement(By.xpath(header));
		String getHeader = headerElement.getText();
		ratesGridLogger.info("Found : " + getHeader);
		testSteps.add("Found : " + getHeader);

		Assert.assertEquals(getHeader, monthYear, "Failed : Header MonthYear doesn't match");
		testSteps.add("Verified month year heading in end date calendar");
		ratesGridLogger.info("Verified month year heading in end date calendar");

		ratesGridLogger.info("==========VERIFYING DEFAULT SELECTED DAY==========");
		testSteps.add("==========VERIFYING DEFAULT SELECTED DAY==========");

		String day = Utility.getDay(endDate);
		ratesGridLogger.info("Expected Day : " + day);
		testSteps.add("Expected Day : " + day);

		String selectedDay = elements.EndDateSelectedDay.getText();
		ratesGridLogger.info("Found : " + selectedDay);
		testSteps.add("Found : " + selectedDay);

		Assert.assertEquals(day, selectedDay, "Failed to match selected day");
		testSteps.add("Verified selected day");
		ratesGridLogger.info("Verified selected day");

		Assert.assertTrue(elements.TodayDateButton.isDisplayed(), "Failed today button doesn't exist");
		testSteps.add("Verified that today button is displaying at the footer of calendar");
		ratesGridLogger.info("Verified that today button is displaying at the footer of calendar");

		elements.EndDateInput.sendKeys(Keys.TAB);
		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyDaysCheckbox> ' Description: <This method will
	 * check whether day checkbox is displaying or not in bulk update> ' Input
	 * parameters: <WebDriver driver, String day> ' Return value :
	 * <ArrayList<String>> ' Created By: <Farhan Ghaffar> ' Created On:
	 * <07/13/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> verifyDaysCheckbox(WebDriver driver, String day) throws InterruptedException {

		String daysCheckBox = "//span[text()='" + day + "']//following-sibling::span";
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, daysCheckBox);
		Wait.waitForElementToBeVisibile(By.xpath(daysCheckBox), driver);
		Wait.waitForElementToBeClickable(By.xpath(daysCheckBox), driver);
		WebElement daysElement = driver.findElement(By.xpath(daysCheckBox));

		Assert.assertTrue(daysElement.isDisplayed(), "Failed : " + day + " checkbox didn't display");
		testSteps.add("Verified " + day + " checkbox is displaying");
		ratesGridLogger.info("Verified " + day + " checkbox is displaying");
		return testSteps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <totalOccupancyTextDisplay> ' Description: <This method
	 * will return the text mentioned in occupancy row> ' Input parameters:
	 * <WebDriver driver> ' Return value : <String>> ' Created By: <Farhan
	 * Ghaffar> ' Created On: <07/14/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String totalOccupancyTextDisplay(WebDriver driver) throws InterruptedException {

		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.TOTAL_OCCUPANCY_TEXT);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.TOTAL_OCCUPANCY_TEXT), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.TOTAL_OCCUPANCY_TEXT), driver);
		Utility.ScrollToElement_NoWait(elements.TotalOccupancyText, driver);
		String totalOccupancyText = elements.TotalOccupancyText.getText();
		return totalOccupancyText;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verificationTotalOccupanyIcon> ' Description: <This
	 * method will return the tooltip clause text in occupancy row> ' Input
	 * parameters: <WebDriver driver> ' Return value : <String>> ' Created By:
	 * <Farhan Ghaffar> ' Created On: <07/14/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String verificationTotalOccupanyIcon(WebDriver driver) throws InterruptedException {
		Actions actions = new Actions(driver);
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.OCCUPANCY_ICON);
		actions.moveToElement(elements.OccupancyIcon).perform();
		String getClause = elements.OcupancyClause.getText();
		ratesGridLogger.info(getClause);
		return getClause;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyDaysInRangeOfDate> ' Description: <This method will
	 * verify the days in date range popup title> ' Input parameters: <WebDriver
	 * driver> ' Return value : <ArrayList<String>> ' Created By: <Aqsa Manzoor>
	 * ' Created On: <07/14/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String verifyDaysInRangeOfDate(WebDriver driver) throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.NO_DAYS_MATCH);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.NO_DAYS_MATCH), driver);
		String getClause = elements.NO_DAYS_MATCH.getText();
		ratesGridLogger.info(getClause);
		return getClause;
	}

	public boolean getDayState(WebDriver driver, String day) throws InterruptedException {

		String daysCheckBox = "//span[text()='" + day + "']/following-sibling::span";
		Wait.WaitForElement(driver, daysCheckBox);
		Wait.waitForElementToBeVisibile(By.xpath(daysCheckBox), driver);
		Wait.waitForElementToBeClickable(By.xpath(daysCheckBox), driver);
		WebElement daysElement = driver.findElement(By.xpath(daysCheckBox));
		Utility.ScrollToElement_NoWait(daysElement, driver);

		return daysElement.isSelected();

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyTotalOccupancyTypeValues> ' Description: <This
	 * method will return total occupancy type value in bulk update popup> '
	 * Input parameters: <WebDriver driver> ' Return value : <ArrayList<String>>
	 * ' Created By: <Farhan Ghaffar> ' Created On: <07/08/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> verifyTotalOccupancyTypeValues(WebDriver driver, String totalOccupancyType)
			throws InterruptedException {

		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_RatesGrid.TOTAL_OCCUPANCY_TYPE);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.TOTAL_OCCUPANCY_TYPE), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.TOTAL_OCCUPANCY_TYPE), driver);
		Utility.ScrollToElement_NoWait(elements.TotalOccupancyType, driver);
		elements.TotalOccupancyType.click();
		ratesGridLogger.info("Dropdown clicked");

		String path = "(//span[text()='For days that total occupancy is']//parent::label//parent::div//div[@class='Select-menu-outer'])[1]//div[contains(text(),'"
				+ totalOccupancyType + "')]";
		WebElement type = driver.findElement(By.xpath(path));
		String getType = type.getText();
		ratesGridLogger.info(getType);
		Assert.assertTrue(type.isDisplayed(), "Failed to verify occupancy type value : " + totalOccupancyType);
		ratesGridLogger.info("Verified " + totalOccupancyType + " value");
		testSteps.add("Verified " + totalOccupancyType + " value");
		elements.TotalOccupancyType.click();
		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyPercentageSignOccupancyValueField> ' Description:
	 * <This method will verifies the % Sign in Occupancy Input Field> ' Input
	 * parameters: <WebDriver driver ' Return value : <ArrayList<String>> '
	 * Created By: <Aqsa Manzoor> ' Created On: <07/14/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> verifyPercentageSignOccupancyValueField(WebDriver driver) {
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();

		assertEquals(ratesGrid.OCCUPANCY_PERCENTAGE_SIGN.isDisplayed(), true,
				"Failed: Occupancy Input Field Tab didn't have % sign");

		assertEquals(ratesGrid.OCCUPANCY_PERCENTAGE_SIGN.getText(), "%",

				"Failed: Occupancy Input Field Tab didn't have % sign");
		testSteps.add("Occupancy Input Field Tab have % sign");
		ratesGridLogger.info("Occupancy Input Field Tab have % sign");
		return testSteps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyPercentageSignOccupancyValueField> ' Description:
	 * <This method will verifies the color of the selected RatePlan> ' Input
	 * parameters: <WebDriver driver,String dropdownSelector> ' Return value :
	 * <ArrayList<String>> ' Created By: <Aqsa Manzoor> ' Created On:
	 * <07/14/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> verifyColorCodeofRatePlans(WebDriver driver, String dropDownSelector) {
		ArrayList<String> testSteps = new ArrayList<>();
		String expectedColorCode = "#3ea8f4";
		String colorPath = "";
		if (dropDownSelector.contains("All")) {
			colorPath = "(//span[contains(text(),'All')]/ancestor::div[@class='Select-value'])[1]";

		} else {

			colorPath = "//span[contains(text(),'" + dropDownSelector + "')]/ancestor::div[@class='Select-value']";
		}
		Wait.WaitForElement(driver, colorPath);
		Wait.waitForElementToBeVisibile(By.xpath(colorPath), driver);
		WebElement colorPathElement = driver.findElement(By.xpath(colorPath));
		System.out.print("COlorL:" + colorPathElement.getCssValue("background-color"));
		String foundColor = colorPathElement.getCssValue("background-color");
		String foundColorCode = Color.fromString(foundColor).asHex();

		assertEquals(foundColorCode, expectedColorCode, "Failed: Rate Plan Element Color didn't match");

		testSteps.add("Rate Plan Element Color matched");
		ratesGridLogger.info("Rate Plan Element Color matched");
		return testSteps;

	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyPercentageSignOccupancyValueField> ' Description:
	 * <This method will verifies the color of the selected RoomClass> ' Input
	 * parameters: <WebDriver driver,String dropdownSelector> ' Return value :
	 * <ArrayList<String>> ' Created By: <Aqsa Manzoor> ' Created On:
	 * <07/14/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> verifyColorCodeofRoomClasses(WebDriver driver, String dropDownSelector) {
		ArrayList<String> testSteps = new ArrayList<>();
		String expectedColorCode = "#9bc1bc";
		String colorPath = "";
		if (dropDownSelector.contains("All")) {
			colorPath = "(//span[contains(text(),'All')]/ancestor::div[@class='Select-value'])[2]";

		} else {

			colorPath = "//span[contains(text(),'" + dropDownSelector + "')]/ancestor::div[@class='Select-value']";
		}
		Wait.WaitForElement(driver, colorPath);
		Wait.waitForElementToBeVisibile(By.xpath(colorPath), driver);
		WebElement colorPathElement = driver.findElement(By.xpath(colorPath));

		String foundColor = colorPathElement.getCssValue("background-color");
		String foundColorCode = Color.fromString(foundColor).asHex();

		assertEquals(foundColorCode, expectedColorCode, "Failed:Room Classes Element Color didn't match");

		testSteps.add("Room Classes Element Color matched");
		ratesGridLogger.info("Room Classes Element Color matched");
		return testSteps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyDropDownsPlaceHolder> ' Description: <This method
	 * will return specific dropdown with given place holder text in bulk update
	 * popup> ' Input parameters: <WebDriver driver> ' Return value :
	 * <ArrayList<String>> ' Created By: <Farhan Ghaffar> ' Created On:
	 * <07/08/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * 
	 */
	public ArrayList<String> verifyBulkUpdateDropDowns(WebDriver driver, String dropDownName)
			throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<>();
		String bulkUpdateDropDownPath = "//label[text()='" + dropDownName + "']//following-sibling::div";
		Wait.WaitForElement(driver, bulkUpdateDropDownPath);
		Wait.waitForElementToBeVisibile(By.xpath(bulkUpdateDropDownPath), driver);
		Wait.waitForElementToBeClickable(By.xpath(bulkUpdateDropDownPath), driver);
		WebElement bulkUpdateDropdown = driver.findElement(By.xpath(bulkUpdateDropDownPath));
		Utility.ScrollToElement_NoWait(bulkUpdateDropdown, driver);
		Assert.assertTrue(bulkUpdateDropdown.isDisplayed(), "Failed : " + dropDownName + " dropdown didn't display");
		testSteps.add("Verify " + dropDownName + " dropdown displayed");
		ratesGridLogger.info("Verify " + dropDownName + " dropdown displayed");

		return testSteps;
	}

	public String getDropDownsPlaceHolder(WebDriver driver, String dropDownName) throws InterruptedException {

		String bulkUpdateDropDownPath = "//label[text()='" + dropDownName
				+ "']//following-sibling::div//div[@class='Select-placeholder']";
		Wait.WaitForElement(driver, bulkUpdateDropDownPath);
		Wait.waitForElementToBeVisibile(By.xpath(bulkUpdateDropDownPath), driver);
		Wait.waitForElementToBeClickable(By.xpath(bulkUpdateDropDownPath), driver);
		WebElement bulkUpdateDropdown = driver.findElement(By.xpath(bulkUpdateDropDownPath));
		Utility.ScrollToElement_NoWait(bulkUpdateDropdown, driver);
		String placeHolderText = bulkUpdateDropdown.getText();
		ratesGridLogger.info("placeHolderText " + placeHolderText);

		return placeHolderText;
	}

	public int getRoomClassListSize(WebDriver driver, String dropDownName, String dropDownOption)
			throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<>();
		String bulkUpdateDropDownPath = "(//label[text()='" + dropDownName
				+ "']//following-sibling::div//input[@role='combobox'])[1]";

		Wait.WaitForElement(driver, bulkUpdateDropDownPath);
		Wait.waitForElementToBeVisibile(By.xpath(bulkUpdateDropDownPath), driver);
		Wait.waitForElementToBeClickable(By.xpath(bulkUpdateDropDownPath), driver);
		WebElement bulkUpdateDropdown = driver.findElement(By.xpath(bulkUpdateDropDownPath));
		Utility.ScrollToElement_NoWait(bulkUpdateDropdown, driver);
		bulkUpdateDropdown.sendKeys(dropDownOption);
		ratesGridLogger.info("Entered " + dropDownName + " name : " + dropDownOption);
		testSteps.add("Entered " + dropDownName + " name : " + dropDownOption);

		String roomClassesPath = "(//div[@class='Select-menu-outer'])[1]//div[contains(@class,'Select-option')]";
		List<WebElement> roomClassesElement = driver.findElements(By.xpath(roomClassesPath));
		System.out.println("roomClassesElement: " + roomClassesElement.size());

		return roomClassesElement.size();
	}

	/*
	 * #####################################################################
	 * ####
	 * #####################################################################
	 * #### ########################
	 * 
	 * ' Method Name: <clickUpdateButton> ' Description: <This method will
	 * retunr sleected item text from dropdown in bulk update popup> ' Input
	 * parameters: <WebDriver driver, String dropDownName, String roomClass> '
	 * Return value : <String> ' Created By: <Farhan Ghaffar> ' Created On:
	 * <07/13/2020>
	 * 
	 * #####################################################################
	 * ####
	 * #####################################################################
	 * #### ########################
	 */

	public String getAllRoomClassText(WebDriver driver, String dropDownName, String roomClass)
			throws InterruptedException {

		String roomClassesPath = "//label[text()='" + dropDownName
				+ "']//following-sibling::div//span[contains(text(),'" + roomClass + "')]";
		WebElement roomClassesElement = driver.findElement(By.xpath(roomClassesPath));
		String getAllRoomClass = roomClassesElement.getText().trim();
		ratesGridLogger.info("getAllRoomClass : " + getAllRoomClass);
		return getAllRoomClass;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <removeAllRoomClass> ' Description: <This method will
	 * remove corresponding item from dropdowns in bulk update popup> ' Input
	 * parameters: <WebDriver driver, String dropDownName, String roomClass> '
	 * Return value : <ArrayList<String>> ' Created By: <Farhan Ghaffar> '
	 * Created On: <07/13/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> removeAllRoomClass(WebDriver driver, String dropDownName, String roomClass)
			throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<>();

		String roomClassesRemovePath = "//label[text()='" + dropDownName
				+ "']//following-sibling::div//span[contains(text(),'" + roomClass
				+ "')]//preceding::span[@class='Select-value-icon'][1]";
		driver.findElement(By.xpath(roomClassesRemovePath)).click();
		ratesGridLogger.info("click on (" + roomClass + ") remove icon");
		testSteps.add("click on (" + roomClass + ") remove icon");

		return testSteps;
	}

	/*
	 * #########################################################################
	 * ########################
	 * 
	 * Method Name: <getBulkUpdateDropDownsList> ' Description: <This method
	 * will return list of dropdowns based of respected parameters in bulk
	 * update popup> ' Input parameters: <WebDriver driver> ' Return value :
	 * <String> ' Created By: <Farhan Ghaffar> ' Created On: <07/08/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> getBulkUpdateDropDownsList(WebDriver driver, String dropDownName)
			throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<>();
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		String bulkUpdateDropDownPath = "//label[text()='" + dropDownName + "']//following-sibling::div";

		Wait.WaitForElement(driver, bulkUpdateDropDownPath);
		Wait.waitForElementToBeVisibile(By.xpath(bulkUpdateDropDownPath), driver);
		Wait.waitForElementToBeClickable(By.xpath(bulkUpdateDropDownPath), driver);
		WebElement bulkUpdateDropdown = driver.findElement(By.xpath(bulkUpdateDropDownPath));
		Utility.ScrollToElement_NoWait(bulkUpdateDropdown, driver);
		bulkUpdateDropdown.click();
		ratesGridLogger.info(dropDownName + " drop down clicked");

		String roomClassesPath = "(//div[@class='Select-menu-outer'])[1]//div[contains(@class,'Select-option')]";
		List<WebElement> roomClassesElement = driver.findElements(By.xpath(roomClassesPath));

		for (int i = 0; i < roomClassesElement.size(); i++) {
			String roomClassName = roomClassesElement.get(i).getText().trim();
			testSteps.add(roomClassName);
			ratesGridLogger.info("Get " + dropDownName + " Dropdown list items : " + roomClassName);

		}
		elements.EndDateInput.sendKeys(Keys.TAB);
		ratesGridLogger.info("Clicked Tab Key");
		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <daysInRangeOfDateOKButton> ' Description: <This method
	 * will click the Ok Button when days in date range popup appears> ' Input
	 * parameters: <WebDriver driver> ' Return value : <ArrayList<String>> '
	 * Created By: <Aqsa Manzoor> ' Created On: <07/14/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> daysInRangeOfDateOKButton(WebDriver driver) throws InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		ArrayList<String> testSteps = new ArrayList<>();

		Wait.WaitForElement(driver, OR_RatesGrid.NO_DATS_MATCH_OK_BUTTON);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.NO_DATS_MATCH_OK_BUTTON), driver);
		elements.NO_DATS_MATCH_OK_BUTTON.click();
		ratesGridLogger.info("OK Button Clicked");
		testSteps.add("OK Button Clicked");
		return testSteps;

	}

	public String getDefaultRatePlan(WebDriver driver, ArrayList<String> testSteps) throws InterruptedException {
		String defaultRatePlan = null;
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.defaultRatePlan);
		defaultRatePlan = elements.defaultRatePlan.getText();
		testSteps.add("Default Rate Plan is : <b>" + defaultRatePlan + "</b>");
		logger.info("Default Rate Plan is :" + defaultRatePlan);
		return defaultRatePlan;
	}

	public ArrayList<String> getRegularRates(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		ArrayList<String> bestRoomClass = new ArrayList<String>();
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.bestAvailableRoomClass);
		Utility.ScrollToElement(elements.ratesTab, driver);
		for (WebElement ele : elements.regularRates) {
			bestRoomClass.add(ele.getText());
		}
		test_steps.add(
				"Best Available Rates :  <b>" + (bestRoomClass.toString().replace("[", "").replace("]", "")) + "</b>");
		logger.info("Best Rates Are :  " + bestRoomClass);
		return bestRoomClass;

	}

	public String getBestAvailableRoomClass(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		String bestRoomClass = null;
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.bestAvailableRoomClass);
		Utility.ScrollToElement(elements.ratesTab, driver);
		bestRoomClass = elements.bestAvailableRoomClass.getText();
		bestRoomClass = bestRoomClass.replaceAll("[\\n\\t ]", "");
		test_steps.add("Best Available Room Class No Is:  <b>" + bestRoomClass + "</b>");
		logger.info("Best Available Room Class No Is:  " + bestRoomClass);
		return bestRoomClass;

	}

	public int getSizeOfRateGridRoomClass(WebDriver driver) throws InterruptedException {
		int roomClassesSize = 0;
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.ratePlanSelectValue);
		Utility.ScrollToElement(elements.ratesTab, driver);
		roomClassesSize = elements.rateGridAllRoomClass.size();
		logger.info("RoomClass Size id: " + roomClassesSize);
		return roomClassesSize;
	}

	public ArrayList<String> verifyRoomClasses(WebDriver driver, ArrayList<String> getRoomClasses,
			ArrayList<String> testSteps) throws InterruptedException {
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.ROOM_CLASSES_NAMES);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.ROOM_CLASSES_NAMES), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.ROOM_CLASSES_NAMES), driver);
		String expectedRoomClass = null;
		String foundRoomClass = null;
		String previousRoomClass = null;
		boolean inorder = true;
		for (int i = 0; i < getRoomClasses.size(); i++) {
			ratesGridLogger.info("Room Class " + (i + 1));
			expectedRoomClass = getRoomClasses.get(i);
			ratesGridLogger.info("Expected Room Class : " + expectedRoomClass);
			Utility.ScrollToElement(ratesGrid.ROOM_CLASSES_NAMES.get(i), driver);
			foundRoomClass = ratesGrid.ROOM_CLASSES_NAMES.get(i).getText();
			ratesGridLogger.info("Found Room Class : " + foundRoomClass);
			assertEquals(foundRoomClass, expectedRoomClass, "Failed: Room Class missmatched");
			if (previousRoomClass == null) {
				previousRoomClass = foundRoomClass;
			} else {
				if (!(previousRoomClass.charAt(0) <= foundRoomClass.charAt(0))) {
					inorder = false;
				}
			}
			previousRoomClass = foundRoomClass;

		}
		ratesGridLogger.info("Successfully verified All Active Room classes are shown one below another");
		testSteps.add("Successfully verified All Active Room classes are shown one below another");
		if (inorder) {
			ratesGridLogger.info("Successfully verified All Active Room classes are in ascending order.");
			testSteps.add("Successfully verified All Active Room classes are in ascending order.");
		} else {
			ratesGridLogger.info("Failed: Room classes are not in ascending order");
			testSteps.add("Failed: Room classes are not in ascending order");
		}
		return testSteps;
	}

	public ArrayList<String> getRoomClassAvailibilityDataValues(WebDriver driver, int index, String roomClassName)
			throws InterruptedException {
		ArrayList<String> data = new ArrayList<String>();
		String xpath = "//div[contains(@class,'RoomClassHeader ')]//div[@class='roomClassName'and contains(@title,'"
				+ roomClassName
				+ "')]//parent::div/parent::div//following-sibling::div[contains(@class,'availability-row')]//div";

		List<WebElement> labelValues = driver.findElements(By.xpath(xpath));
		for (WebElement ele : labelValues) {
			Utility.ScrollToViewElementINMiddle(driver, ele);
			data.add(ele.getText());
		}
		logger.info(data);
		return data;
	}

	public ArrayList<String> getChannelDataValues(WebDriver driver, int index, String roomClassName, String channelName)
			throws InterruptedException {
		ArrayList<String> data = new ArrayList<String>();
		String xpath = "(//div[contains(@class,'RoomClassHeader ')]//div[@class='roomClassName'and contains(@title,'"
				+ roomClassName + "')]//parent::div/parent::div//following-sibling::div//div[contains(@class,'d-flex')]"
				+ "//div[@title='" + channelName + "']//parent::div//following-sibling::div/div[1])";
		List<WebElement> labelValues = driver.findElements(By.xpath(xpath));
		for (WebElement ele : labelValues) { // Wait.WaitForElement(driver,
												// xpath);
			Utility.ScrollToViewElementINMiddle(driver, ele);
			data.add(ele.getText());
		}
		logger.info(data);
		return data;
	}

	public void getRuleAppliedForRoomClass(WebDriver driver, String roomClassName, ArrayList<String> testSteps)
			throws InterruptedException {
		String xpath = "(//div[contains(@class,'RoomClassHeader ')]//div[@class='roomClassName'and contains(@title,'"
				+ roomClassName + "')]" + "//parent::div//following-sibling::div/div[1])/following-sibling::div";
		List<WebElement> labelValues = null;
		String date = null, dayNum = null, weekDay = null;
		ArrayList<String> ruleLabels = new ArrayList<String>();
		ArrayList<String> ruleImages = new ArrayList<String>();
		boolean isExist = Utility.isElementPresent(driver, By.xpath(xpath));
		if (isExist) {
			testSteps.add("============Rule Applied For <b>" + roomClassName + "</b>============");

			Wait.WaitForElement(driver, xpath);
			labelValues = driver.findElements(By.xpath(xpath));
			for (int i = 0; i < labelValues.size(); i++) {
				Utility.ScrollToViewElementINMiddle(driver, labelValues.get(i));
				Utility.hoverOnElement(driver, labelValues.get(i));
				date = getRuleDate(driver, testSteps);
				String[] array = date.split(",");
				dayNum = array[0];
				logger.info(dayNum);
				String[] array1 = array[1].split(" ");
				weekDay = array1[2];
				logger.info(weekDay);
				getRuleRate(driver, testSteps);
				ruleLabels = getRulesLabels(driver);
				ruleImages = getRuleImages(driver);
				for (int j = 0; j < ruleImages.size(); j++) {
					testSteps.add("<img src='" + ruleImages.get(j) + "' width='15' height='15'>" + "<b>-- "
							+ ruleLabels.get(j) + " </b>");
				}
				testSteps.add("Rule Applied ON Week Day:- <b>" + dayNum + " </b>Date:- <b>" + dayNum + "</b>");
				testSteps.add("-:--:--:--:--:--:--:--:--:--:--:--:--:--:--:--:--:--:--:--:--:--:-");

			}

		}

	}

	public String getRoomRate(WebDriver driver) {
		String roomRate = null;
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		roomRate = elements.rateGridRoomRate.getAttribute("value");
		return roomRate;
	}

	public String getExtraAdult(WebDriver driver) {
		String roomRate = null;
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		roomRate = elements.rateGridExtraAd.getAttribute("value");
		return roomRate;
	}

	public String getExtraChild(WebDriver driver) {
		String roomRate = null;
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		roomRate = elements.rateGridExtraCh.getAttribute("value");
		return roomRate;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyRulesLabels> ' Description: < Verified any Label
	 * Such as Updated by:, Updated on:, Previous price:,No check out, No check
	 * in,Previous price:,night min> ' Input parameters: < WebDriver
	 * driver,ArrayList<String> testSteps, String label> ' Return value: <NA> '
	 * Created By: <Gangotri Sikheria> ' ' Created On: <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> getRulesLabels(WebDriver driver) throws InterruptedException {
		ArrayList<String> labels = new ArrayList<String>();
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		for (WebElement ele : elements.ruleLabels) {
			labels.add(ele.getText());
		}
		logger.info("Rule Label Are:" + labels);
		return labels;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyImagesOnRulePopup> ' Description: < Verified all
	 * images> ' Input parameters: < WebDriver driver,ArrayList<String>
	 * testSteps,String roomClass, String rate, String ruleName,String image> '
	 * Return value: <NA> ' Created By: <Gangotri Sikheria> ' ' Created On:
	 * <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> getRuleImages(WebDriver driver) throws InterruptedException {
		ArrayList<String> images = new ArrayList<String>();
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RateGrid.rulePropertyImage);
		for (WebElement element : elements.rulePropertyImage) {
			images.add(element.getAttribute("src"));
		}
		logger.info("Rule Images Are: " + images);
		return images;
	}

	public ArrayList<String> getRoomClassValues(WebDriver driver, int index, String roomClass)
			throws InterruptedException {
		ArrayList<String> data = new ArrayList<String>();
		String xpath = "(//div[contains(@class,'RoomClassHeader ')]//div[@class='roomClassName'and contains(@title,'"
				+ roomClass + "')]//parent::div//following-sibling::div/div[1])";
		List<WebElement> labelValues = driver.findElements(By.xpath(xpath));
		for (WebElement ele : labelValues) {
			Utility.ScrollToViewElementINMiddle(driver, ele);
			data.add(ele.getText());
		}

		return data;
	}

	public ArrayList<String> getChannelofAllRoomClass(WebDriver driver, ArrayList<String> testSteps,
			String roomClassName) throws InterruptedException {
		ArrayList<String> channelName = new ArrayList<String>();
		String path = "//div[@class='DatesTable']//div[contains(@title,'" + roomClassName + "')]"
				+ "/parent::div/parent::div/following-sibling::div//div[@class='roomClassName']";

		boolean isExist = Utility.isElementPresent(driver, By.xpath(path));
		if (isExist) {
			Wait.WaitForElement(driver, path);
			List<WebElement> elements = driver.findElements(By.xpath(path));
			Utility.ScrollToElement(elements.get(0), driver);
			for (WebElement ele : elements) {
				channelName.add(ele.getAttribute("title"));
			}
			logger.info("Room Class " + roomClassName + " Channels Are: " + channelName);
		}

		else if (!isExist) {
			testSteps.add("No Channel Associated");
		}
		return channelName;
	}

	public ArrayList<String> getRuleDataValues(WebDriver driver, int index, String roomClassName, String channelName,
			String ruleName) throws InterruptedException {
		ArrayList<String> data = new ArrayList<String>();
		String xpath = "//div[@class='DatesTable']//div[contains(@title,'" + roomClassName + "')]"
				+ "/ancestor::div/..//following-sibling::div//div[@title='" + channelName + "']"
				+ "/ancestor::div/..//following-sibling::div//div[contains(@class,'RuleHeader') and text()='" + ruleName
				+ "']/following-sibling::div//input";
		Wait.WaitForElement(driver, xpath);
		List<WebElement> labelValues = driver.findElements(By.xpath(xpath));
		for (WebElement ele : labelValues) {
			Utility.ScrollToViewElementINMiddle(driver, ele);
			data.add(ele.getAttribute("value"));
		}
		logger.info(data);
		return data;
	}

	public ArrayList<String> getRuleDataValue(WebDriver driver, int index, String roomClassName, String channelName,
			String ruleName) throws InterruptedException {
		ArrayList<String> data = new ArrayList<String>();
		String xpath = "//div[@class='DatesTable']//div[contains(@title,'" + roomClassName + "')]"
				+ "/ancestor::div/..//following-sibling::div//div[@title='" + channelName + "']"
				+ "/ancestor::div/..//following-sibling::div//div[contains(@class,'RuleHeader') and text()='" + ruleName
				+ "']/following-sibling::div/div/div";
		Wait.WaitForElement(driver, xpath);
		List<WebElement> labelValues = driver.findElements(By.xpath(xpath));
		for (WebElement ele : labelValues) {
			Utility.ScrollToViewElementINMiddle(driver, ele);
			data.add(ele.getText());
		}
		logger.info(data);
		return data;
	}

	public void getOverRideAndNonOverrideValueForRoomClass(WebDriver driver, String roomClassName,
			ArrayList<String> testSteps) throws InterruptedException {
		String xpath = "//div[contains(@class,'RoomClassHeader ')]//div[@class='roomClassName'and contains(@title,'"
				+ roomClassName + "')]" + "//parent::div//following-sibling::div/div[1][not(contains(text(),'--'))]";
		List<WebElement> labelValues = null;
		String roomRate = null, extraChild = null, extraAdult = null;
		boolean isExist = Utility.isElementPresent(driver, By.xpath(xpath));
		if (isExist) {
			testSteps.add("========Get Room Rate, Extra Adult and Extra Child For <b>" + roomClassName + "</b>======");

			Wait.WaitForElement(driver, xpath);
			labelValues = driver.findElements(By.xpath(xpath));
			for (int i = 0; i < labelValues.size(); i++) {
				Utility.ScrollToViewElementINMiddle(driver, labelValues.get(i));
				Utility.clickThroughJavaScript(driver, labelValues.get(i));
				roomRate = getRoomRate(driver);
				extraChild = getExtraAdult(driver);
				extraAdult = getExtraChild(driver);
				if (extraChild.isEmpty() && extraAdult.isEmpty()) {
					testSteps.add("Room Rate:- <b>" + roomRate + " </b> Extra Adults:- <b>" + extraAdult
							+ "NA </b> Extra Child:- <b>" + extraChild + "NA </b>");

				} else if (extraChild.isEmpty()) {
					testSteps.add("Room Rate:- <b>" + roomRate + " </b> Extra Adults:- <b>" + extraAdult
							+ " </b> Extra Child:- <b>" + extraChild + "NA </b>");

				} else if (extraAdult.isEmpty()) {
					testSteps.add("Room Rate:- <b>" + roomRate + " </b> Extra Adults:- <b>" + extraAdult
							+ "NA </b> Extra Child:- <b>" + extraChild + " </b>");

				} else {
					testSteps.add("Room Rate:- <b>" + roomRate + " </b> Extra Adults:- <b>" + extraAdult
							+ " </b> Extra Child:- <b>" + extraChild + " </b>");

				}

			}

		}

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyRoomClassColor> ' Description: <Verified Room Class
	 * Color> ' Input parameters: < WebDriver driver,ArrayList<String>
	 * testSteps,String roomClassName, String colorName> ' Return value: <NA> '
	 * Created By: <Gangotri Sikheria> ' ' Created On: <MM/dd/yyyy> <07/08/2020>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public String getRoomClassColor(WebDriver driver, String roomClassName) throws InterruptedException {
		String color = null;
		String path = "//div[contains(text(),'" + roomClassName + "')]";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToViewElementINMiddle(driver, element);
		color = element.getCssValue("color");
		String colorName1 = Color.fromString(color).asHex();
		logger.info(colorName1);
		return colorName1;

	}

	public String getOverRideValues(WebDriver driver, String valueFor) throws InterruptedException {
		String value = null;
		String path = "//span[@class='rules-labels'and contains(text(),'" + valueFor + "')]/following-sibling::span";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		value = element.getText();
		logger.info(" Value Are :" + value);
		return value;
	}

	public void getRateColor(WebDriver driver, ArrayList<String> testSteps, String roomClass)
			throws InterruptedException {
		String path = "(//div[contains(@class,'RoomClassHeader ')]//div[@class='roomClassName'and contains(@title,'"
				+ roomClass + "')]"
				+ "//parent::div//following-sibling::div/div[1])[contains(@class,'RegularRate')and not(contains(text(),'--'))]";
		List<WebElement> labelValues = null;
		String colorName = null, rate = null;
		boolean isExist = Utility.isElementPresent(driver, By.xpath(path));
		if (isExist) {
			testSteps.add("========Rate Available for Room Class<b> " + roomClass + "</b> Get Rate Color========");
			Wait.WaitForElement(driver, path);
			labelValues = driver.findElements(By.xpath(path));

			for (int i = 0; i < labelValues.size(); i++) {
				Utility.ScrollToViewElementINMiddle(driver, labelValues.get(i));
				rate = labelValues.get(i).getText();
				String color = labelValues.get(i).getCssValue("color");
				colorName = Color.fromString(color).asHex();
				testSteps.add("Rate:- <b>" + rate + " </b>Color is <b>" + colorName + " </b>");
				logger.info("Rate:- " + rate + " Color is " + colorName);
			}
		}
	}

	public ArrayList<String> updateRate(WebDriver driver, ArrayList<String> testSteps, String roomClass)
			throws InterruptedException {
		ArrayList<String> ratesAre = new ArrayList<String>();
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		String path = "(//div[contains(@class,'RoomClassHeader ')]//div[@class='roomClassName'and contains(@title,'"
				+ roomClass + "')]"
				+ "//parent::div//following-sibling::div/div[1])[contains(@class,'RegularRate')and not(contains(text(),'--'))]";
		List<WebElement> labelValues = null;
		String oldRate = null, newRate = null;
		boolean isExist = Utility.isElementPresent(driver, By.xpath(path));
		if (isExist) {
			testSteps.add("========Update Rate for Room Class<b> " + roomClass + "</b>========");

			Wait.WaitForElement(driver, path);
			labelValues = driver.findElements(By.xpath(path));

			if (labelValues.size() > 0) {
				Utility.ScrollToViewElementINMiddle(driver, labelValues.get(0));
				Utility.clickThroughJavaScript(driver, labelValues.get(0));
				oldRate = getRoomRate(driver);
				ratesAre.add(oldRate);
				if (oldRate.contains(".")) {
					Double doubleValue = Double.parseDouble(oldRate) + 50;
					newRate = String.valueOf(doubleValue);
					ratesAre.add(newRate);
				} else {
					int newRates = Integer.parseInt(oldRate) + 50;
					newRate = String.valueOf(newRates);
					ratesAre.add(newRate);
				}

				elements.rateGridRoomRate.clear();
				elements.rateGridRoomRate.sendKeys(newRate);
				testSteps.add("Enter New Rate: <b>" + newRate + "</b>");
				logger.info("Enter New Rate: " + newRate);
				elements.rateGridSuccess.click();
				testSteps.add("Click on Success Icon");
				logger.info("Click on Success Icon");
				Wait.WaitForElement(driver, OR_RateGrid.rateSavedMessage);
				assertTrue(elements.rateSavedMessage.isDisplayed(), "Failed to Verify Success Message");
				testSteps.add("Verified Message : <b> Rate saved successfully </b>");
				logger.info("Verified Message : Rate saved successfully");
				String pathNewRate = "//div[contains(text(),'" + roomClass
						+ "')]/parent::div/following-sibling::div/div[contains(@class,'Override')and text()='" + newRate
						+ "']";
				Wait.WaitForElement(driver, pathNewRate);
				WebElement elementOne = driver.findElement(By.xpath(pathNewRate));
				Utility.ScrollToElement(elementOne, driver);
				assertTrue(elementOne.isDisplayed(), "Failed to Verify New Rate");
				testSteps.add("Update Rate is : <b> " + newRate + " </b> Old Rate is: " + oldRate + "</b>");
				logger.info("Update Rate is :" + newRate + " Old Rate is: " + oldRate);

			}

		}
		return ratesAre;
	}

	public String getOverrideRateColor(WebDriver driver, ArrayList<String> testSteps, String roomClass, String rate)
			throws InterruptedException {
		String path1 = "//div[contains(text(),'" + roomClass + "')]";
		String path = "//div[contains(text(),'" + roomClass
				+ "')]/parent::div/following-sibling::div/div[contains(@class,'Override')and text()='" + rate + "']";
		Wait.WaitForElement(driver, path1);
		Utility.ScrollToViewElementINMiddle(driver, driver.findElement(By.xpath(path1)));
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToViewElementINMiddle(driver, element);
		String color = element.getCssValue("color");
		String colorName1 = Color.fromString(color).asHex();
		logger.info(colorName1);
		testSteps.add("Get OverRide Rate Color <b>" + colorName1 + " </b>");
		logger.info("Get OverRide Rate Color " + colorName1);
		return colorName1;
	}

	public boolean getUpdateBoolValue(WebDriver driver, String roomClass, String channelName, String ruleAppliedOn) {
		String xpath = "//div[@class='DatesTable']//div[contains(@title,'" + roomClass + "')]"
				+ "/ancestor::div/..//following-sibling::div//div[@title='" + channelName + "']"
				+ "/ancestor::div/..//following-sibling::div//div[contains(@class,'RuleHeader') and text()='"
				+ ruleAppliedOn + "']" + "/following-sibling::div//input[not(@value='')]";
		boolean isExist = Utility.isElementPresent(driver, By.xpath(xpath));
		return isExist;
	}

	public ArrayList<String> updateRuleForMinStay(WebDriver driver, ArrayList<String> testSteps, String roomClass,
			String channelName, String ruleAppliedOn) throws InterruptedException, ParseException {

		ArrayList<String> minStayValue = new ArrayList<String>();
		String oldMinStayValue = null, newStayValue = null;
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);

		String xpath = "//div[@class='DatesTable']//div[contains(@title,'" + roomClass + "')]"
				+ "/ancestor::div/..//following-sibling::div//div[@title='" + channelName + "']"
				+ "/ancestor::div/..//following-sibling::div//div[contains(@class,'RuleHeader') and text()='"
				+ ruleAppliedOn + "']" + "/following-sibling::div//input[not(@value='')]";
		String ruleAppliedPath = "//div[@class='DatesTable']//div[contains(@title,'" + roomClass + "')]"
				+ "/ancestor::div/..//following-sibling::div//div[@title='" + channelName + "']"
				+ "/ancestor::div/..//following-sibling::div//div[contains(@class,'RuleHeader') and text()='"
				+ ruleAppliedOn + "']";

		List<WebElement> element = driver.findElements(By.xpath(xpath));
		minStayValue.add(oldMinStayValue);
		oldMinStayValue = element.get(0).getAttribute("value");
		Utility.ScrollToViewElementINMiddle(driver, element.get(0));
		element.get(0).click();
		testSteps.add("Click on Min Stay Box");

		int newStayValues = Integer.parseInt(oldMinStayValue) + 2;
		newStayValue = String.valueOf(newStayValues);
		minStayValue.add(newStayValue);

		element.get(0).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		element.get(0).sendKeys(Keys.BACK_SPACE);
		testSteps.add("Clear Min Stay ");
		element.get(0).sendKeys(newStayValue);
		testSteps.add("Input Min Stay : <b>" + newStayValue + "</b>");
		WebElement elementMinStay = driver.findElement(By.xpath(ruleAppliedPath));
		Utility.ScrollToElement(elementMinStay, driver);
		element.get(0).sendKeys(Keys.TAB);
		Wait.WaitForElement(driver, OR_RateGrid.rateGridRuleSavedMessage);
		assertTrue(elements.rateGridRuleSavedMessage.isDisplayed(), "Failed to Verify Rule Saved Message");
		testSteps.add("Update Rule for <b>" + ruleAppliedOn + "</b> Message : <b> Rule saved successfully </b>");
		logger.info("Update Rule for " + ruleAppliedOn + "Message :  Rule saved successfully");
		String pathOne = "//div[@class='DatesTable']//div[contains(@title,'" + roomClass + "')]"
				+ "/ancestor::div/..//following-sibling::div//div[@title='" + channelName + "']"
				+ "/ancestor::div/..//following-sibling::div//div[contains(@class,'RuleHeader') and text()='"
				+ ruleAppliedOn + "']" + "/following-sibling::div//input[@value='" + newStayValue + "']";
		WebElement elementOne = driver.findElement(By.xpath(pathOne));
		Utility.ScrollToElement(elementOne, driver);
		assertEquals(elementOne.getAttribute("value"), newStayValue, "Failed to Verify Updated Min Stay");
		testSteps.add("Verified Updated Min Stay is :<b>" + newStayValue + " </b> Old Min Stay is: <b>"
				+ oldMinStayValue + "</b>");
		logger.info("Verified Updated Min Stay is :" + newStayValue + " Old Min Stay is: " + oldMinStayValue);

		return minStayValue;

	}

	public void updateRuleForNoCheckInAndOut(WebDriver driver, ArrayList<String> testSteps, String roomClass,
			String channelName, String ruleAppliedOn) throws InterruptedException {
		Wait.wait10Second();
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		String path = "//div[@class='DatesTable']//div[contains(@title,'" + roomClass + "')]"
				+ "/ancestor::div/..//following-sibling::div//div[@title='" + channelName + "']"
				+ "/ancestor::div/..//following-sibling::div//div[contains(@class,'RuleHeader') and text()='"
				+ ruleAppliedOn + "']" + "/parent::div//div[@class='rt-onHover  has-noValue enabled']";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToViewElementINMiddle(driver, element);
		Utility.hoverOnElement(driver, element);
		element.click();
		Wait.WaitForElement(driver, OR_RateGrid.rateGridRuleSavedMessage);
		assertTrue(elements.rateGridRuleSavedMessage.isDisplayed(), "Failed to Verify Rule Saved Message");
		testSteps.add("Update Rule for <b>" + ruleAppliedOn + " </b> Message : <b> Rule saved successfully </b>");
		logger.info("Update Rule for " + ruleAppliedOn + " Message :  Rule saved successfully");

	}

	public String getCheckInAndCheckoutColor(WebDriver driver, ArrayList<String> testSteps, String roomClass,
			String channelName, String ruleAppliedOn) throws InterruptedException {

		String path1 = "//div[contains(text(),'" + roomClass + "')]";
		String path = "//div[contains(text(),'" + roomClass + "')]/ancestor::div//div[@title='" + channelName + "']"
				+ "/ancestor::div//div[text()='" + ruleAppliedOn + "']/parent::div//div[@class='rt-onHover  enabled']";
		Wait.WaitForElement(driver, path1);
		Utility.ScrollToViewElementINMiddle(driver, driver.findElement(By.xpath(path1)));
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToViewElementINMiddle(driver, element);
		String color = element.getCssValue("color");
		String colorName1 = Color.fromString(color).asHex();
		logger.info(colorName1);
		testSteps.add(ruleAppliedOn + "  Color <b>" + colorName1 + " </b>");
		logger.info(ruleAppliedOn + "  Color" + colorName1);
		return colorName1;
	}

	// added by Adhnan 7/8/2020
	public String getOccupancyORPaceDataValue(WebDriver driver, int index, String label) throws InterruptedException {
		String xpath = "//div[@class='d-flex ir-border-grid top-header-row undefined']//following-sibling::div/div[contains(@class,'"
				+ label + "')]/div";
		Wait.WaitForElement(driver, xpath);
		Wait.waitForElementToBeClickable(By.xpath(xpath), driver);
		Wait.waitForElementToBeVisibile(By.xpath(xpath), driver);
		java.util.List<WebElement> labelValues = driver.findElements(By.xpath(xpath));
		Utility.ScrollToElement(labelValues.get(index), driver);
		return labelValues.get(index).getText();
	}

	// Added By Adhnan 7/7/2020
	public String getRoomStatus(WebDriver driver, String date, String dateFormat, String source, String roomClassName)
			throws InterruptedException, ParseException {
		int index = getHeadingDateIndex(driver, date, dateFormat);
		String xpath = "//div[contains(@class,'RoomClassHeader ')]//div[@class='roomClassName' and text()='" + source
				+ "']//parent::div/following-sibling::div";
		Wait.WaitForElement(driver, xpath);
		Wait.waitForElementToBeClickable(By.xpath(xpath), driver);
		Wait.waitForElementToBeVisibile(By.xpath(xpath), driver);
		java.util.List<WebElement> labelValues = driver.findElements(By.xpath(xpath));
		Utility.ScrollToElement(labelValues.get(index), driver);
		String roomStatus = labelValues.get(index).getAttribute("class");
		if (roomStatus.contains("NoBlacked")) {
			roomStatus = "*";
		} else {
			roomStatus = "B";
		}
		if (roomStatus.equals("B")) {
			xpath = "(//div[contains(@class,'RoomClassHeader ')]//div[@class='roomClassName' and text()='"
					+ roomClassName + "']//parent::div/following-sibling::div)[" + (index + 1)
					+ "]/div[@class='Blackout']/span";
			Wait.WaitForElement(driver, xpath);
			Wait.waitForElementToBeClickable(By.xpath(xpath), driver);
			Wait.waitForElementToBeVisibile(By.xpath(xpath), driver);
			WebElement blackout = driver.findElement(By.xpath(xpath));
			Utility.ScrollToElement(blackout, driver);
			assertEquals(blackout.getText(), "B", "Failed: B is not visible under Percentage value of the room");
		}
		return roomStatus;
	}

	// Added By Adhnan 7/7/2020
	public void changeRoomStatus(WebDriver driver, int index, String source, String roomClassName, String status)
			throws InterruptedException {
		String xpath = "//div[contains(@class,'RoomClassHeader ')]//div[@class='roomClassName' and text()='" + source
				+ "']//parent::div/following-sibling::div";
		Wait.WaitForElement(driver, xpath);
		Wait.waitForElementToBeClickable(By.xpath(xpath), driver);
		Wait.waitForElementToBeVisibile(By.xpath(xpath), driver);
		java.util.List<WebElement> labelValues = driver.findElements(By.xpath(xpath));
		Utility.ScrollToElement(labelValues.get(index), driver);
		String roomStatus = labelValues.get(index).getAttribute("class");
		Utility.app_logs.info("Initial room Status : " + roomStatus);
		if (roomStatus.contains("NoBlacked")) {
			roomStatus = "*";
		} else {
			roomStatus = "B";
		}
		if (roomStatus.equals(status)) {
			Utility.app_logs.info("Room is already in reguired State");
		} else {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-400)");
			labelValues.get(index).click();
			try {
				Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath("//div[@class='css-kwlyb4']")), 120);
			} catch (Exception e) {

			}
		}

		Utility.app_logs.info("Initial room Status : " + roomStatus);
	}

	// Added By Adhnan 7/8/2020
	public int getHeadingDateIndex(WebDriver driver, String date, String format)
			throws ParseException, InterruptedException {
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.HEADER_DATES_ROW_DAY_NUMBER);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.HEADER_DATES_ROW_DAY_NUMBER), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.HEADER_DATES_ROW_DAY_NUMBER), driver);
		String expectedDayNumber = Utility.parseDate(date, format, "d");
		String expectedWeekDay = Utility.parseDate(date, format, "E");
		String foundDayNumber = null;
		String foundWeekDay = null;
		int foundIndex = -1;
		for (int i = 0; i < 20; i++) {
			ratesGridLogger.info("Date Column: " + i);
			ratesGridLogger.info("Expexted Day Number : " + expectedDayNumber);
			ratesGridLogger.info("Expexted Week Day : " + expectedWeekDay);
			Utility.ScrollToElement(ratesGrid.HEADER_DATES_ROW_DAY_NUMBER.get(i), driver);
			foundDayNumber = ratesGrid.HEADER_DATES_ROW_DAY_NUMBER.get(i).getText();
			foundWeekDay = ratesGrid.HEADER_DATES_ROW_WEEKDAY.get(i).getText();
			ratesGridLogger.info("Found Day Number : " + foundDayNumber);
			ratesGridLogger.info("Found Week Day : " + foundWeekDay);
			if (foundDayNumber.equals(expectedDayNumber) && foundWeekDay.equals(expectedWeekDay)) {
				foundIndex = i;
				break;
			}
		}
		ratesGridLogger.info("Expected Date Index: " + foundIndex);
		return foundIndex;
	}

	// Added By Adhnan 7/8/2020
	public int getRoomClassIndex(WebDriver driver, String roomClass) throws InterruptedException {
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.ROOM_CLASSES_NAMES);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.ROOM_CLASSES_NAMES), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.ROOM_CLASSES_NAMES), driver);
		java.util.List<WebElement> roomClasses = driver.findElements(By.xpath(OR_RatesGrid.ROOM_CLASSES_NAMES));
		int foundIndex = -1;
		String foundRoomClass = null;
		for (int i = 0; i < roomClasses.size(); i++) {
			ratesGridLogger.info("Room Class " + (i + 1));
			Utility.ScrollToElement(ratesGrid.ROOM_CLASSES_NAMES.get(i), driver);
			foundRoomClass = ratesGrid.ROOM_CLASSES_NAMES.get(i).getText();
			ratesGridLogger.info("Expected Room Class : " + roomClass);
			ratesGridLogger.info("Found Room Class : " + foundRoomClass);
			if (foundRoomClass.equals(roomClass)) {
				foundIndex = i;
				break;
			}

		}

		ratesGridLogger.info("Expected Room Class Index: " + foundIndex);
		return foundIndex;

	}

	// added by Adhnan 7/9/2020
	public void activeOrBlackoutChannel(WebDriver driver, String date, String dateFormat, String roomClassName,
			String channel, String status) throws ParseException, InterruptedException {
		int dateIndex = getHeadingDateIndex(driver, date, dateFormat);
		changeRoomStatus(driver, dateIndex, channel, roomClassName, status);

	}

	// Added By Adhnan 7/8/2020
	public String getCalendarDate(WebDriver driver, String day, String requiredFormat, ArrayList<String> testSteps)
			throws InterruptedException {

		String xpath = "//div[contains(@class,'" + day + "')]";
		Wait.WaitForElement(driver, xpath);
		Wait.waitForElementToBeClickable(By.xpath(xpath), driver);
		Wait.waitForElementToBeVisibile(By.xpath(xpath), driver);
		WebElement requiredDay = driver.findElement(By.xpath(xpath));
		Utility.ScrollToElement(requiredDay, driver);
		String requiredDate = requiredDay.getAttribute("aria-label");
		ratesGridLogger.info("Calender '" + day + "' date is '" + requiredDate + "'");
		requiredDate = Utility.parseDate(requiredDate, "E MMM dd yyyy", requiredFormat);
		testSteps.add("Calender '" + day + "' date is '" + requiredDate + "'");
		ratesGridLogger.info("Calender '" + day + "' date is '" + requiredDate + "'");
		return requiredDate;
	}

	// Added By Adhnan 7/8/2020
	public ArrayList<String> closeCalendar(WebDriver driver) throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_RatesGrid.DATE_RANGE_CALENDAR);
		Wait.waitForElementToBeClickable(By.xpath(OR_RatesGrid.DATE_RANGE_CALENDAR), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_RatesGrid.DATE_RANGE_CALENDAR), driver);
		Utility.ScrollToElement(ratesGrid.DATE_RANGE_CALENDAR, driver);
		ratesGrid.DATE_RANGE_CALENDAR.click();
		testSteps.add("Close Date Range Calendar");
		ratesGridLogger.info("Close date range Calendar");
		try {
			Wait.waitForElementToBeGone(driver, ratesGrid.DATE_RANGE_CALENDAR, 30);
		} catch (Exception e) {

		}
		return testSteps;
	}

	// added by Adhnan 7/9/2020
	public ArrayList<String> verifyChannels(WebDriver driver, String roomClassName, ArrayList<String> channels,
			ArrayList<String> testSteps) throws InterruptedException {
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);
		String xpath = "//div[contains(@class,'RoomClassHeader ')]//div[@class='roomClassName' and text()='"
				+ roomClassName + "']//ancestor::div[@class='DatesTable']/div[last()]//div[@class='roomClassName']";
		Wait.WaitForElement(driver, xpath);
		Wait.waitForElementToBeClickable(By.xpath(xpath), driver);
		Wait.waitForElementToBeVisibile(By.xpath(xpath), driver);
		java.util.List<WebElement> roomClassChannels = driver.findElements(By.xpath(xpath));
		String expectedChannel = null;
		String foundChannel = null;
		boolean inorder = true;
		assertEquals(channels.size(), roomClassChannels.size(), "Failed: Channels size missmatched");
		for (int i = 0; i < channels.size(); i++) {
			ratesGridLogger.info("Channel " + (i + 1));
			expectedChannel = channels.get(i);
			ratesGridLogger.info("Expected Room Class : " + expectedChannel);
			Utility.ScrollToElement(ratesGrid.ROOM_CLASSES_NAMES.get(i), driver);
			foundChannel = roomClassChannels.get(i).getText();
			ratesGridLogger.info("Found Room Class : " + foundChannel);
			assertEquals(foundChannel, expectedChannel, "Failed: Room Class missmatched");

		}
		ratesGridLogger.info("Successfully verified All Active Channels Present in Room Class '" + roomClassName + "'");
		testSteps.add("Successfully verified All Active Channels Present in Room Class '" + roomClassName + "'");

		return testSteps;
	}

	public HashMap<String, String> getRatesOfRoomClassAndChannel(WebDriver driver, String startDate, String endDate,
			String roomClass, String channelName) throws ParseException, InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		ArrayList<String> channelRates = new ArrayList<String>();
		ArrayList<String> datesList = new ArrayList<String>();
		HashMap<String, String> rates = new HashMap<String, String>();
		ArrayList<String> dayList = new ArrayList<String>();
		ArrayList<String> dateList = new ArrayList<String>();
		List<Date> dates = new ArrayList<Date>();

		Date fromDate = Utility.convertStringtoDateFormat("dd/MM/yyyy", startDate);
		logger.info("Start Date: " + fromDate);
		Date toDate = Utility.convertStringtoDateFormat("dd/MM/yyyy", endDate);
		logger.info("End Date: " + toDate);
		dates = Utility.getDateRangeBetweenfromAndToDate(fromDate, toDate);
		logger.info("Dates Are: " + dates);

		for (Date d : dates) {
			dayList.add(Utility.getDateOnlyFromCompleteDate(d));
			dateList.add(Utility.convertDateFormattoString("dd/MM/yyyy", d));
		}

		logger.info("Day's  from Start date and End Date: " + dayList);
		logger.info("Date List from Start date and End Date: " + dateList);
		for (WebElement ele : elements.rateGridDay) {
			datesList.add(ele.getText());
		}

		logger.info("Rate Grid Day's Are: " + datesList);
		channelRates = getChannelDataValues(driver, roomClass, channelName);

		for (int i = 0; i < datesList.size(); i++) {
			for (int j = 0; j < dayList.size(); j++) {
				if (datesList.get(i).equals(dayList.get(j))) {

					if (channelRates.get(i).equals("--")) {
						rates.put(dateList.get(j), "NA");
					} else {
						rates.put(dateList.get(j), channelRates.get(i));
					}
				}
			}

		}
		logger.info("Rates as per  Date  " + rates);
		return rates;

	}

	public ArrayList<String> getChannelDataValues(WebDriver driver, String roomClassName, String channelName)
			throws InterruptedException {
		ArrayList<String> data = new ArrayList<String>();
		String xpath = "(//div[contains(@class,'RoomClassHeader ')]//div[@class='roomClassName'and contains(@title,'"
				+ roomClassName + "')]//parent::div/parent::div//following-sibling::div//div[contains(@class,'d-flex')]"
				+ "//div[@title='" + channelName + "']//parent::div//following-sibling::div/div[1])";
		List<WebElement> labelValues = driver.findElements(By.xpath(xpath));
		for (WebElement ele : labelValues) {
			Utility.ScrollToViewElementINMiddle(driver, ele);
			data.add(ele.getText());
		}
		logger.info(data);
		return data;
	}

	public ArrayList<String> getRuleDataValuesForMinStay(WebDriver driver, String roomClassName, String channelName,
			String ruleName) throws InterruptedException {
		ArrayList<String> data = new ArrayList<String>();
		String xpath = "//div[@class='DatesTable']//div[contains(@title,'" + roomClassName + "')]"
				+ "/ancestor::div/..//following-sibling::div//div[@title='" + channelName + "']"
				+ "/ancestor::div/..//following-sibling::div//div[contains(@class,'RuleHeader') and text()='" + ruleName
				+ "']/following-sibling::div//input";
		Wait.WaitForElement(driver, xpath);
		List<WebElement> labelValues = driver.findElements(By.xpath(xpath));
		for (WebElement ele : labelValues) {
			Utility.ScrollToViewElementINMiddle(driver, ele);
			data.add(ele.getAttribute("value"));
		}
		logger.info(data);
		return data;
	}

	public HashMap<String, String> getMinStayRulesOfRoomClassAndChannel(WebDriver driver, String startDate,
			String endDate, String roomClass, String channelName, String headerName)
			throws ParseException, InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		ArrayList<String> rulesValue = new ArrayList<String>();
		ArrayList<String> datesList = new ArrayList<String>();
		HashMap<String, String> rules = new HashMap<String, String>();
		ArrayList<String> dayList = new ArrayList<String>();
		ArrayList<String> dateList = new ArrayList<String>();
		List<Date> dates = new ArrayList<Date>();

		Date fromDate = Utility.convertStringtoDateFormat("dd/MM/yyyy", startDate);
		logger.info("Start Date: " + fromDate);
		Date toDate = Utility.convertStringtoDateFormat("dd/MM/yyyy", endDate);
		logger.info("End Date: " + toDate);
		dates = Utility.getDateRangeBetweenfromAndToDate(fromDate, toDate);
		logger.info("Dates Are: " + dates);

		for (Date d : dates) {
			dayList.add(Utility.getDateOnlyFromCompleteDate(d));
			dateList.add(Utility.convertDateFormattoString("dd/MM/yyyy", d));
		}

		logger.info("Day's  from Start date and End Date: " + dayList);
		logger.info("Date List from Start date and End Date: " + dateList);
		for (WebElement ele : elements.rateGridDay) {
			datesList.add(ele.getText());
		}

		logger.info("Rate Grid Day's Are: " + datesList);
		rulesValue = getRuleDataValuesForMinStay(driver, roomClass, channelName, headerName);

		for (int i = 0; i < datesList.size(); i++) {
			for (int j = 0; j < dayList.size(); j++) {
				if (datesList.get(i).equals(dayList.get(j))) {

					if (rulesValue.get(i).isEmpty()) {
						rules.put(dateList.get(j), "NA");
					} else {
						rules.put(dateList.get(j), rulesValue.get(i));
					}
				}
			}

		}
		logger.info("Rule as per  Date  " + rules);
		return rules;

	}

	public HashMap<String, String> getCheckInCheckOutRulesOfRoomClassAndChannel(WebDriver driver, String startDate,
			String endDate, String roomClass, String channelName, String headerName)
			throws ParseException, InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		ArrayList<String> rulesValue = new ArrayList<String>();
		ArrayList<String> datesList = new ArrayList<String>();
		HashMap<String, String> rules = new HashMap<String, String>();
		ArrayList<String> dayList = new ArrayList<String>();
		ArrayList<String> dateList = new ArrayList<String>();
		List<Date> dates = new ArrayList<Date>();

		Date fromDate = Utility.convertStringtoDateFormat("dd/MM/yyyy", startDate);
		logger.info("Start Date: " + fromDate);
		Date toDate = Utility.convertStringtoDateFormat("dd/MM/yyyy", endDate);
		logger.info("End Date: " + toDate);
		dates = Utility.getDateRangeBetweenfromAndToDate(fromDate, toDate);
		logger.info("Dates Are: " + dates);

		for (Date d : dates) {
			dayList.add(Utility.getDateOnlyFromCompleteDate(d));
			dateList.add(Utility.convertDateFormattoString("dd/MM/yyyy", d));
		}

		logger.info("Day's  from Start date and End Date: " + dayList);
		logger.info("Date List from Start date and End Date: " + dateList);
		for (WebElement ele : elements.rateGridDay) {
			datesList.add(ele.getText());
		}

		logger.info("Rate Grid Day's Are: " + datesList);
		rulesValue = getRuleDataValueForCheckInCheckOut(driver, roomClass, channelName, headerName);

		for (int i = 0; i < datesList.size(); i++) {
			for (int j = 0; j < dayList.size(); j++) {
				if (datesList.get(i).equals(dayList.get(j))) {

					if (rulesValue.get(i).isEmpty()) {
						rules.put(dateList.get(j), "NA");
					} else if (rulesValue.get(i).equals("?")) {
						rules.put(dateList.get(j), rulesValue.get(i).replaceAll("?", "YES"));
					}
				}
			}

		}
		logger.info("Rule as per  Date  " + rules);
		return rules;

	}

	public ArrayList<String> getRuleDataValueForCheckInCheckOut(WebDriver driver, String roomClassName,
			String channelName, String ruleName) throws InterruptedException {
		ArrayList<String> data = new ArrayList<String>();
		String xpath = "//div[@class='DatesTable']//div[contains(@title,'" + roomClassName + "')]"
				+ "/ancestor::div/..//following-sibling::div//div[@title='" + channelName + "']"
				+ "/ancestor::div/..//following-sibling::div//div[contains(@class,'RuleHeader') and text()='" + ruleName
				+ "']/following-sibling::div/div/div";
		Wait.WaitForElement(driver, xpath);
		List<WebElement> labelValues = driver.findElements(By.xpath(xpath));
		for (WebElement ele : labelValues) {
			Utility.ScrollToViewElementINMiddle(driver, ele);
			data.add(ele.getText());
		}
		logger.info(data);
		return data;
	}

	public HashMap<String, String> getOverrideOfRoomClass(WebDriver driver, String startDate, String endDate,
			String roomClass) throws ParseException, InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		ArrayList<String> roomRates = new ArrayList<String>();
		ArrayList<String> extraAdults = new ArrayList<String>();
		ArrayList<String> extraChilds = new ArrayList<String>();
		ArrayList<String> dayListOfOverRide = new ArrayList<String>();
		ArrayList<String> datesList = new ArrayList<String>();
		HashMap<String, String> roomRatesExtraAdultsExtraChild = new HashMap<String, String>();
		ArrayList<String> dayList = new ArrayList<String>();
		ArrayList<String> dateList = new ArrayList<String>();
		List<Date> dates = new ArrayList<Date>();

		Date fromDate = Utility.convertStringtoDateFormat("dd/MM/yyyy", startDate);
		logger.info("Start Date: " + fromDate);
		Date toDate = Utility.convertStringtoDateFormat("dd/MM/yyyy", endDate);
		logger.info("End Date: " + toDate);
		dates = Utility.getDateRangeBetweenfromAndToDate(fromDate, toDate);
		logger.info("Dates Are: " + dates);

		for (Date d : dates) {
			dayList.add(Utility.getDateOnlyFromCompleteDate(d));
			dateList.add(Utility.convertDateFormattoString("dd/MM/yyyy", d));
		}

		logger.info("Day's  from Start date and End Date: " + dayList);
		logger.info("Date List from Start date and End Date: " + dateList);
		for (WebElement ele : elements.rateGridDay) {
			datesList.add(ele.getText());
		}

		logger.info("Rate Grid Day's Are: " + datesList);
		roomRates = getRateExAdExChForRoomClass(driver, roomClass, extraAdults, extraChilds, dayListOfOverRide);

		for (int i = 0; i < datesList.size(); i++) {
			for (int j = 0; j < dayList.size(); j++) {

				for (int k = 0; k < dayListOfOverRide.size(); k++) {
					if (datesList.get(i).equals(dayList.get(j))) {
						if (dayList.get(j).equals(dayListOfOverRide.get(k))) {
							roomRatesExtraAdultsExtraChild.put(dateList.get(j), " RRate: " + roomRates.get(k)
									+ " ExCh: " + extraAdults.get(k) + " ExAd: " + extraChilds.get(k));
						}
					}

				}

			}

		}

		logger.info("Rule as per  Date  " + roomRatesExtraAdultsExtraChild);
		return roomRatesExtraAdultsExtraChild;

	}

	public ArrayList<String> getRateExAdExChForChannel(WebDriver driver, String roomClassName, String channelName,
			ArrayList<String> extraAdults, ArrayList<String> extraChilds, ArrayList<String> dayNum)
			throws InterruptedException {
		String xpath = "//div[contains(@class,'RoomClassHeader ')]//div[@class='roomClassName'and contains(@title,'"
				+ roomClassName + "')]"
				+ "//parent::div/parent::div//following-sibling::div//div[contains(@class,'d-flex')]" + "//div[@title='"
				+ channelName + "']//parent::div//following-sibling::div/div[1][not(contains(text(),'--'))]";
		ArrayList<String> roomRates = new ArrayList<String>();
		List<WebElement> overRideList = null;
		String roomRate = null, extraChild = null, extraAdult = null;
		Wait.WaitForElement(driver, xpath);
		overRideList = driver.findElements(By.xpath(xpath));
		for (int i = 0; i < overRideList.size(); i++) {
			Utility.ScrollToViewElementINMiddle(driver, overRideList.get(i));
			Utility.clickThroughJavaScript(driver, overRideList.get(i));
			roomRate = getRoomRate(driver);
			extraChild = getExtraAdult(driver);
			extraAdult = getExtraChild(driver);
			roomRates.add(roomRate);
			extraAdults.add(extraAdult);
			extraChilds.add(extraChild);
		}
		for (int i = 0; i < overRideList.size(); i++) {
			Utility.ScrollToViewElementINMiddle(driver, overRideList.get(i));
			Utility.hoverOnElement(driver, overRideList.get(i));
			String date = getRuleDate(driver);
			String[] array = date.split(",");
			String[] array1 = array[1].split(" ");
			dayNum.add(array1[2]);

		}
		logger.info(roomRates);
		logger.info(extraAdults);
		logger.info(extraChilds);
		logger.info(dayNum);
		return roomRates;

	}

	public HashMap<String, String> getOverrideOfRoomClassChannel(WebDriver driver, String startDate, String endDate,
			String roomClass, String channelName) throws ParseException, InterruptedException {
		Elements_RatesGrid elements = new Elements_RatesGrid(driver);
		ArrayList<String> roomRates = new ArrayList<String>();
		ArrayList<String> extraAdults = new ArrayList<String>();
		ArrayList<String> extraChilds = new ArrayList<String>();
		ArrayList<String> dayListOfOverRide = new ArrayList<String>();
		ArrayList<String> datesList = new ArrayList<String>();
		HashMap<String, String> roomRatesExtraAdultsExtraChild = new HashMap<String, String>();
		ArrayList<String> dayList = new ArrayList<String>();
		ArrayList<String> dateList = new ArrayList<String>();
		List<Date> dates = new ArrayList<Date>();

		Date fromDate = Utility.convertStringtoDateFormat("dd/MM/yyyy", startDate);
		logger.info("Start Date: " + fromDate);
		Date toDate = Utility.convertStringtoDateFormat("dd/MM/yyyy", endDate);
		logger.info("End Date: " + toDate);
		dates = Utility.getDateRangeBetweenfromAndToDate(fromDate, toDate);
		logger.info("Dates Are: " + dates);

		for (Date d : dates) {
			dayList.add(Utility.getDateOnlyFromCompleteDate(d));
			dateList.add(Utility.convertDateFormattoString("dd/MM/yyyy", d));
		}

		logger.info("Day's  from Start date and End Date: " + dayList);
		logger.info("Date List from Start date and End Date: " + dateList);
		for (WebElement ele : elements.rateGridDay) {
			datesList.add(ele.getText());
		}

		logger.info("Rate Grid Day's Are: " + datesList);
		roomRates = getRateExAdExChForChannel(driver, roomClass, channelName, extraAdults, extraChilds,
				dayListOfOverRide);

		for (int i = 0; i < datesList.size(); i++) {
			for (int j = 0; j < dayList.size(); j++) {
				for (int k = 0; k < dayListOfOverRide.size(); k++) {
					if (datesList.get(i).equals(dayList.get(j))) {
						if (dayList.get(j).equals(dayListOfOverRide.get(k))) {
							roomRatesExtraAdultsExtraChild.put(dateList.get(j), "RRate: " + roomRates.get(k) + "ExAd: "
									+ extraAdults.get(k) + "ExCh: " + extraChilds.get(k));

						}

					} else {
						roomRatesExtraAdultsExtraChild.put(dateList.get(j), "RRate: NA ExAd: NA ExCh: NA");
					}
				}

			}

		}
		logger.info("Rule as per  Date  " + roomRatesExtraAdultsExtraChild);
		return roomRatesExtraAdultsExtraChild;

	}

	public ArrayList<String> getRateExAdExChForRoomClass(WebDriver driver, String roomClassName,
			ArrayList<String> extraAdults, ArrayList<String> extraChilds, ArrayList<String> dayNum)
			throws InterruptedException {
		String xpath = "//div[contains(@class,'RoomClassHeader ')]//div[@class='roomClassName'and contains(@title,'"
				+ roomClassName + "')]" + "//parent::div//following-sibling::div/div[1][not(contains(text(),'--'))]";

		ArrayList<String> roomRates = new ArrayList<String>();
		List<WebElement> overRideList = null;
		String roomRate = null, extraChild = null, extraAdult = null;
		Wait.WaitForElement(driver, xpath);
		overRideList = driver.findElements(By.xpath(xpath));
		for (int i = 0; i < overRideList.size(); i++) {
			Utility.ScrollToViewElementINMiddle(driver, overRideList.get(i));
			Utility.clickThroughJavaScript(driver, overRideList.get(i));
			roomRate = getRoomRate(driver);
			extraChild = getExtraAdult(driver);
			extraAdult = getExtraChild(driver);
			roomRates.add(roomRate);
			extraAdults.add(extraAdult);
			extraChilds.add(extraChild);
		}
		for (int i = 0; i < overRideList.size(); i++) {
			Utility.ScrollToViewElementINMiddle(driver, overRideList.get(i));
			Utility.hoverOnElement(driver, overRideList.get(i));
			String date = getRuleDate(driver);
			String[] array = date.split(",");
			String[] array1 = array[1].split(" ");
			dayNum.add(array1[2]);

		}
		logger.info(roomRates);
		logger.info(extraAdults);
		logger.info(extraChilds);
		logger.info(dayNum);
		return roomRates;

	}

	public ArrayList<String> bulkUpdateRateGridSymbolVerification(WebDriver driver, String clientSymbol)
			throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<String>();
		String symbol = "//span[text()='" + clientSymbol + "']";
		Wait.WaitForElement(driver, symbol);
		List<WebElement> symbolList = driver.findElements(By.xpath(symbol));
		for (int i = 0; i < symbolList.size(); i++) {

			String symbolFound = symbolList.get(i).getText();
			assertEquals(symbolFound, clientSymbol, "Failed: Symbol didn't match");
			logger.info("Symbol Verified:" + symbolFound);

		}
		testSteps.add("Symbol Verified:" + clientSymbol);
		return testSteps;

	}
	// Verification of As per Update/aqsa

	public ArrayList<String> verifyRateUpdate(WebDriver driver, int numberofDays, String nightlyRate,
			String updateRatesType, String rateCurrencyType, String rateChangeValue,
			ArrayList<String> beforeUpdateRateValues, ArrayList<String> afterUpdateRateValues) {
		ArrayList<String> testSteps = new ArrayList<String>();

		int rateExpected = 0;

		for (int i = 0; i <= numberofDays; i++) {

			logger.info("Nighlty Rate Plan Expected In RatesGrid:" + nightlyRate);
			testSteps.add("Nighlty Rate Plan Expected In RatesGrid:" + nightlyRate);
			String rateFound = afterUpdateRateValues.get(i);
			logger.info("Nighlty Rate Plan Found In RatesGrid:" + rateFound);
			testSteps.add("Nighlty Rate Plan Found In RatesGrid:" + rateFound);
			if (updateRatesType.equalsIgnoreCase("EnterNewRate")) {
				assertEquals(afterUpdateRateValues.get(i).equals(nightlyRate.split("\\.")[0]), true,
						"Failed: Nightly Rate didn't update");

			}

			// Increase/Decrease Verification

			else if (updateRatesType.equalsIgnoreCase("Increase")) {

				if (rateCurrencyType.equals(Utility.clientCurrency)) {

					// Parse and the add the rateChangeValue
					rateExpected = Integer.parseInt(beforeUpdateRateValues.get(i)) + Integer.parseInt(rateChangeValue);

					logger.info("Nighlty Rate Plan Expected In Reservation Find Rooms:" + rateExpected);
					testSteps.add(
							"Nighlty Rate Plan Expected In Reservation Find Rooms:" + beforeUpdateRateValues.get(i));
					// Need to remove dollar sign here
					assertEquals(rateFound.equals(Integer.toString(rateExpected)), true,
							"Failed: Nightly Rate didn't update");
				}
				// Increase is in Percentage
				else {

					// Parse and the add the rateChangeValue
					double percentageIncrease = (Integer.parseInt(beforeUpdateRateValues.get(i))
							* Integer.parseInt(rateChangeValue)) / 100;
					percentageIncrease = Math.ceil(percentageIncrease);
					rateExpected = Integer.parseInt(beforeUpdateRateValues.get(i)) + ((int) percentageIncrease);
					logger.info("Nighlty Rate Plan Expected In Reservation Find Rooms:" + rateExpected);
					testSteps.add("Nighlty Rate Plan Expected In Reservation Find Rooms:" + rateExpected);

					assertEquals(rateFound.equals(Integer.toString(rateExpected)), true,
							"Failed: Nightly Rate didn't update");

				}
			}

			else if (updateRatesType.equalsIgnoreCase("Decrease")) {

				if (rateCurrencyType.equals(Utility.clientCurrency)) {

					// Parse and the subtract the
					// rateChangeValue

					rateExpected = Integer.parseInt(beforeUpdateRateValues.get(i)) - Integer.parseInt(rateChangeValue);

					logger.info("Nighlty Rate Plan Expected In Reservation Find Rooms:" + rateExpected);
					testSteps.add(
							"Nighlty Rate Plan Expected In Reservation Find Rooms:" + beforeUpdateRateValues.get(i));
					// Need to remove dollar sign here
					logger.info("Nighlty Rate Plan Expected In Reservation Find Rooms:" + rateExpected);
					testSteps.add("Nighlty Rate Plan Expected In Reservation Find Rooms:" + rateExpected);

					assertEquals(rateFound.equals(Integer.toString(rateExpected)), true,
							"Failed: Nightly Rate didn't update");
				}

				// Decrease is in Percentage
				else {

					// Parse and the subtract the rateChangeValue
					double percentageDecrease = (Integer.parseInt(beforeUpdateRateValues.get(i))
							* Integer.parseInt(rateChangeValue)) / 100;
					percentageDecrease = Math.ceil(percentageDecrease);
					rateExpected = Integer.parseInt(beforeUpdateRateValues.get(i)) - (int) percentageDecrease;
					assertEquals(rateFound.equals(Integer.toString(rateExpected)), true,
							"Failed: Nightly Rate didn't update");

				}
			}

			else {
				assertEquals(afterUpdateRateValues.get(i).equals(beforeUpdateRateValues.get(i)), true,
						"Failed: Nightly Rate didn't update");
			}
			logger.info("Verified");
			testSteps.add("Verified");

		}
		return testSteps;

	}

	public void clickMinusChannel(WebDriver driver, String roomClassName, String channelName)
			throws InterruptedException {
		String minusPath = "//div[@class='DatesTable']//div[contains(@title,'" + roomClassName + "')]"
				+ "/ancestor::div/..//following-sibling::div//div[@title='" + channelName
				+ "']/parent::div/span[contains(@class,'ir-minus')]";
		boolean collapseExist = driver.findElement(By.xpath(minusPath)).isDisplayed();
		if (collapseExist) {

			WebElement minus = driver.findElement(By.xpath(minusPath));
			Utility.ScrollToElement(minus, driver);
			assertTrue(minus.isDisplayed(), "Failed to verify collapse icon");

			try {
				minus.click();
			} catch (Exception e) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,-400)");
				minus.click();
			}

		}
	}

	public void clickMinusroomClass(WebDriver driver, String roomClassName) throws InterruptedException {
		String minusPath = "//div[@class='DatesTable']//div[contains(text(),'" + roomClassName
				+ "')]/parent::div/span[contains(@class,'ir-minus')]";
		boolean collapseExist = driver.findElement(By.xpath(minusPath)).isDisplayed();
		if (collapseExist) {

			WebElement minus = driver.findElement(By.xpath(minusPath));
			Utility.ScrollToElement(minus, driver);
			assertTrue(minus.isDisplayed(), "Failed to verify collapse icon");

			try {
				minus.click();
			} catch (Exception e) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,-400)");
				minus.click();
			}

		}

	}

}
