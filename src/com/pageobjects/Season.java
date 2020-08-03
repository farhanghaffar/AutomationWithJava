package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.innroad.inncenter.interfaces.IReports;
import com.innroad.inncenter.interfaces.ISeason;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Setup;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_NewRoomClass;
import com.innroad.inncenter.webelements.Elements_On_All_Navigation;
import com.innroad.inncenter.webelements.Elements_Reports;
import com.innroad.inncenter.webelements.WebElements_Create_Seasons;

public class Season implements ISeason {

	public static Logger seasonLogger = Logger.getLogger("Season");

	@Override
	public void NewSeasonButtonClick(WebDriver driver) throws InterruptedException {

		// TODO Auto-generated method stub
		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Season.New_Season_Button.click();
		Wait.wait2Second();
		try {
			Wait.explicit_wait_visibilityof_webelement(Season.Season_Grid, driver);
			assertTrue(Season.Season_Grid.isDisplayed(), "Faied: Season Page not Displayed");
		} catch (Exception e) {
			driver.navigate().refresh();
			Season.New_Season_Button.click();
			Wait.explicit_wait_visibilityof_webelement(Season.Season_Grid, driver);
			assertTrue(Season.Season_Grid.isDisplayed(), "Faied: Season Page not Displayed");
		}
	}

	@Override
	public void SeasonDeatils(WebDriver driver, String SeasonName, String Status) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Season.NG_Season_Name.sendKeys(SeasonName);
		Season.Season_Status.click();
		new Select(Season.Season_Status).selectByVisibleText(Status);
		Season.NG_Season_Save.click();

	}

	public ArrayList<String> SeasonDeatils_NameValidation(WebDriver driver, String SeasonName, String Status)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		// TODO Auto-generated method stub
		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);

		Season.NG_Season_Name.sendKeys(SeasonName);
		test_steps.add("Season Name Entered Successfully");
		Season.Season_Status.click();
		new Select(Season.Season_Status).selectByVisibleText(Status);
		test_steps.add("Season status Selected");

		assertTrue(Season.NG_Season_Save.isEnabled(), "Season_save_button is Disabled");
		test_steps.add("Save Button is enabled");
		assertTrue(Season.NG_Season_Reset.isEnabled(), "Season_save_button is Disabled");
		test_steps.add("Reset Button is enabled");
		Season.NG_Season_Save.click();

		// assertTrue(Season.FillMendatoryField.isDisplayed(),"Validation
		// Message is not displaying");
		// test_steps.add("Please Fill the Mandatory Fields");
		assertTrue(Season.First_Date_RequiredField.isDisplayed(), "Validation Message is not displaying");
		test_steps.add("First Date is Required");
		assertTrue(Season.End_Date_RequiredField.isDisplayed(), "Validation Message is not displaying");
		test_steps.add("End date is Required");
		return test_steps;
	}

	public ArrayList<String> SeasonAttributes_Validation(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		ArrayList<String> test_steps = new ArrayList<String>();
		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Season.NG_Season_start_Date.click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Season.SeasonDate_Today);
		test_steps.add("First Date is entered");
		Wait.wait3Second();
		Season.NG_Season_Save.click();
		// assertTrue(Season.FillMendatoryField.isDisplayed(),"Validation
		// Message is not displaying");
		// test_steps.add("Please Fill the Mandatory Fields");
		assertTrue(Season.End_Date_RequiredField.isDisplayed(), "Validation Message is not displaying");
		test_steps.add("End date is Required");
		Wait.wait3Second();
		Season.NG_Season_End_Date.click();
		jse.executeScript("arguments[0].click();", Season.SeasonEndDate_Day);
		return test_steps;
	}

	public ArrayList<String> CloseSeason(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		ArrayList<String> test_steps = new ArrayList<String>();
		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Season.Close_Season.click();
		Wait.wait3Second();
		Wait.explicit_wait_visibilityof_webelement(Season.Unsaved_Data_PopUp, driver);
		Season.Unsaved_Data_PopUp.isDisplayed();
		assertTrue(Season.Unsaved_Data_PopUp.isDisplayed(), "Unsaved_Data_PopUp is not displaying");
		test_steps.add("Do you want to close the season popup appears");
		Wait.explicit_wait_visibilityof_webelement(Season.Unsaved_Yes_Button, driver);
		Utility.ScrollToElement(Season.Unsaved_Yes_Button, driver);
		test_steps.add("Yes button is ensbaled");

		Wait.wait3Second();
		Wait.explicit_wait_visibilityof_webelement(Season.Unsaved_No_Button, driver);
		Utility.ScrollToElement(Season.Unsaved_No_Button, driver);
		Season.Unsaved_No_Button.click();
		test_steps.add("No button is Clicked");
		Wait.wait3Second();
		assertTrue(Season.NG_Season_Save.isEnabled(), "Season_save_button is Disabled");
		assertTrue(Season.NG_Season_Reset.isEnabled(), "Season_save_button is Disabled");
		return test_steps;
	}

	public ArrayList<String> EffectiveOn(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		ArrayList<String> test_steps = new ArrayList<String>();
		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Wait.explicit_wait_visibilityof_webelement(Season.CheckboxMonday, driver);

		Season.CheckboxMonday.click();
		Season.CheckboxTuesday.click();
		Season.CheckboxWednesday.click();
		Season.CheckboxThursday.click();
		Season.CheckboxFriday.click();
		Season.CheckboxSaturday.click();
		Season.CheckboxSunday.click();
		Wait.wait3Second();
		test_steps.add("All days are unchecked");
		assertTrue(Season.NG_Season_Save.isEnabled(), "Season_save_button is Disabled");
		Season.NG_Season_Save.click();
		// assertTrue(Season.FillMendatoryField.isDisplayed(),"Validation
		// Message is not displaying");
		// test_steps.add("Please fill the mandatory fields");
		assertTrue(Season.EffectiveDays_ValidationMsg.isDisplayed(), "Validation Message is not displaying");
		test_steps.add("Select atleast one day to create a new season");
		Wait.explicit_wait_visibilityof_webelement(Season.CheckboxMonday, driver);
		Season.CheckboxMonday.click();
		Season.CheckboxTuesday.click();
		Season.CheckboxWednesday.click();
		Season.CheckboxThursday.click();
		Season.CheckboxFriday.click();
		Season.CheckboxSaturday.click();
		Season.CheckboxSunday.click();
		test_steps.add("All days are checked");
		assertTrue(Season.NG_Season_Save.isEnabled(), "Season_save_button is Disabled");
		return test_steps;
	}

	@Override
	public void SaveSeason(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Wait.explicit_wait_visibilityof_webelement(Season.NG_Season_Save, driver);
		assertTrue(Season.NG_Season_Save.isEnabled(), "Season_save_button is Disabled");
		Season.NG_Season_Save.click();
		Wait.wait3Second();
	}

	@Override
	public ArrayList<String> SeasonAttributes(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		ArrayList<String> test_steps = new ArrayList<String>();

		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Season.NG_Season_start_Date.click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Season.SeasonDate_Today);
		test_steps.add("Season Start Date selected");

		Season.NG_Season_End_Date.click();
		jse.executeScript("arguments[0].click();", Season.SeasonEndDate_Day);
		test_steps.add("Season End Date selected ");

		return test_steps;
	}

	public void SearchButtonClick(WebDriver driver) throws Exception {

		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Season.Seasons_SearchButton.click();
		Wait.wait3Second();
		while (!Season.Seasons_TableShow.isDisplayed()) {
			Wait.wait1Second();
		}
		assertTrue(Season.Seasons_TableShow.isDisplayed(), "Failed: Season Table not displayed");
	}

	public void SelectSeasonFromTable(WebDriver driver) throws Exception {

		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Season.Seasons_FirstActiveClass.click();
		Wait.wait3Second();
		assertTrue(Season.Seasons_SeasonDetailsPage.isDisplayed(), "Failed: Season Details Page not displayed");

	}

	public void UpdateSeasonInfo(WebDriver driver) throws Exception {

		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Season.NG_Season_start_Date.click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Season.SeasonDate_Today);

	}

	public String GetSeasonName(WebDriver driver) throws Exception {

		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		String SeasonName = Season.NG_Season_Name.getAttribute("value");
		return SeasonName;

	}

	public String GetUpdatesDate(WebDriver driver) throws Exception {

		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		String SeasonUpdatedDate = Season.Seasons_FirstActiveClass_Date.getText();
		Utility.app_logs.info(SeasonUpdatedDate);
		String temp = SeasonUpdatedDate.split(",")[0];
		Utility.app_logs.info("temp: " + temp);
		String year = SeasonUpdatedDate.split(",")[1];
		Utility.app_logs.info("Year: " + year);
		String month = temp.split(" ")[0];
		Utility.app_logs.info("month: " + month);
		temp = temp.split(" ")[1];

		int date = Integer.parseInt(temp);
		Utility.app_logs.info("Date: " + date);
		if (date < 10) {
			String Date = Integer.toString(date);
			SeasonUpdatedDate = month + " " + Date + "," + year;

		} else {
			System.out.println("Same Date");

		}
		// date.spli
		Utility.app_logs.info(SeasonUpdatedDate);
		return SeasonUpdatedDate;

	}

	public ArrayList<String> SetSeasonPeriod(WebDriver driver, int months, ArrayList<String> test_steps)
			throws ParseException, InterruptedException {

		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Wait.explicit_wait_visibilityof_webelement(Season.NG_Season_start_Date, driver);
		Utility.ScrollToElement(Season.NG_Season_start_Date, driver);
		Season.NG_Season_start_Date.click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Season.SeasonDate_Today);
		String Date = Season.NG_Season_start_Date.getAttribute("value");
		Utility.app_logs.info(Date);
		SimpleDateFormat DateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date StartDate = DateFormat.parse(Date);

		Utility.app_logs.info("Season Start Date: " + Date);
		test_steps.add("Season Start Date: " + Date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(StartDate);
		cal.add(Calendar.MONTH, months);
		Date EndDate = cal.getTime();
		String endDate = DateFormat.format(EndDate);
		Season.NG_Season_End_Date.sendKeys(endDate);
		Utility.app_logs.info("Season End Date: " + endDate);
		test_steps.add("Season End Date: " + endDate);
		return test_steps;
	}

	public ArrayList<String> CheckEffectiveDay(WebDriver driver, String Day, boolean checked,
			ArrayList<String> test_steps) throws InterruptedException {
		String path = "//input[contains(@data-bind,'" + Day + "')]";
		WebElement CheckBox = driver.findElement(By.xpath(path));
		Wait.explicit_wait_visibilityof_webelement(CheckBox, driver);
		Utility.ScrollToElement(CheckBox, driver);
		if (checked && !CheckBox.isSelected()) {

			CheckBox.click();
			Utility.app_logs.info("Effective On Day " + Day + " Checked");
			test_steps.add("Effective On Day " + Day + " Checked");

		} else if (!checked && CheckBox.isSelected()) {
			CheckBox.click();
			Utility.app_logs.info("Effective On Day " + Day + " UnChecked");
			test_steps.add("Effective On Day " + Day + " UnChecked");
		}
		assertEquals(CheckBox.isSelected(), checked, "Failed CheckBox is not In Required State");
		return test_steps;
	}

	public ArrayList<String> DeleteSeason(WebDriver driver, String seasonName, ArrayList<String> test_steps)
			throws InterruptedException {

		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Wait.explicit_wait_visibilityof_webelement(Season.SelectItemsPerPage_Seasons, driver);
		Utility.ScrollToElement(Season.SelectItemsPerPage_Seasons, driver);
		new Select(Season.SelectItemsPerPage_Seasons).selectByVisibleText("100");

		Thread.sleep(10000);
		String path = "//span[contains(@data-bind,'SeasonSelect') and text()='" + seasonName
				+ "']//parent::td//following-sibling::td[contains(@data-bind,'SeasonDelete')]/input";
		WebElement checkBox = driver.findElement(By.xpath(path));
		Wait.explicit_wait_visibilityof_webelement(checkBox, driver);
		Utility.ScrollToElement(checkBox, driver);
		checkBox.click();
		Utility.app_logs.info("Check CheckBox");
		test_steps.add("Check CheckBox");
		// Wait.explicit_wait_visibilityof_webelement(Season.DeleteSeason,
		// driver);
		Wait.explicit_wait_visibilityof_webelement_150(Season.DeleteSeason, driver);
		Wait.wait2Second();
		Utility.ScrollToElement(Season.DeleteSeason, driver);
		Season.DeleteSeason.click();
		return test_steps;

	}

	public ArrayList<String> SeasonDeatils_FirstNameValidation(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		// TODO Auto-generated method stub
		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Season.NG_Season_Name.clear();
		assertTrue(Season.NG_Season_Save.isEnabled(), "Season_save_button is Disabled");
		test_steps.add("Save Button is enabled");
		assertTrue(Season.NG_Season_Reset.isEnabled(), "Season_save_button is Disabled");
		test_steps.add("Reset Button is enabled");
		Season.NG_Season_Save.click();

		// assertTrue(Season.FillMendatoryField.isDisplayed(),"Validation
		// Message is not displaying");
		// test_steps.add("Please Fill the Mandatory Fields");
		assertTrue(Season.First_Name_RequiredField.isDisplayed(), "Validation Message is not displaying");
		test_steps.add("First Name is required as it is a mandatory field");
		return test_steps;
	}

	public void SeasonDeatilsPositive(WebDriver driver, String SeasonName, String Status) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Wait.explicit_wait_visibilityof_webelement(Season.NG_Season_Name, driver);
		Season.NG_Season_Name.sendKeys(SeasonName);
		Season.Season_Status.click();
		new Select(Season.Season_Status).selectByVisibleText(Status);

	}

	public void SeasonAttributesDates(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Season.NG_Season_start_Date.click();
		Wait.wait1Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Season.SeasonDate_Today);
		Season.NG_Season_End_Date.click();
		Wait.wait1Second();
		jse.executeScript("arguments[0].click();", Season.SeasonEndDate_Day);
		Wait.wait1Second();

	}

	public void SaveSeason(WebDriver driver, String seasonName) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Wait.explicit_wait_visibilityof_webelement(Season.NG_Season_Save, driver);
		assertTrue(Season.NG_Season_Save.isDisplayed(), "Failed: Season Cannot be saved");
		Season.NG_Season_Save.click();
		try {
			// Wait.explicit_wait_visibilityof_webelement(Season.Toaster_Message,
			// driver);
			String msg = Season.Toaster_Message.getText();
			Utility.app_logs.info("Save Season Toaster Message: " + msg);
			assertEquals(msg, seasonName + " has been saved successfully.", "Failed to save season");
		} catch (Exception e) {
			System.err.println("Toaster not Displayed");
		}
	}

	public String getCurrentDate() {
		final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// calendar.add(Calendar.DAY_OF_YEAR, 1);
		return format.format(calendar.getTime());
	}

	public String getNextDate(int Day) {
		final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, Day);
		return format.format(calendar.getTime());
	}

	public void seasonStartEndDate(WebDriver driver, int Days) {
		driver.findElement(By.xpath("//input[contains(@placeholder,'Start Date')]")).sendKeys(getCurrentDate());
		driver.findElement(By.xpath("//input[contains(@placeholder,'End Date')]")).sendKeys(getNextDate(Days));
	}

	public void closeTab(WebDriver driver) throws InterruptedException {
		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Season.Close_Season);
		Wait.wait2Second();
		Season.Close_Season.click();
		Wait.wait2Second();
	}

	public void searchWithStatus(WebDriver driver, String status) throws InterruptedException {

		Select sel = new Select(driver.findElement(By.xpath("//select[contains(@data-bind,'value: selectedStatus')]")));
		sel.selectByVisibleText(status);
		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Season.Seasons_SearchButton.click();
		Wait.wait3Second();
		assertTrue(Season.Seasons_TableShow.isDisplayed(), "Failed: Season Table not displayed");

	}

	public void OpenSeason(WebDriver driver, String SeasonName) throws InterruptedException {
		WebElement newSeason = SearchNewSeason(driver, SeasonName);
		Utility.ScrollToElement(newSeason, driver);
		newSeason.click();
	}

	public WebElement SearchNewSeason(WebDriver driver, String seasonName) throws InterruptedException {

		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Wait.explicit_wait_visibilityof_webelement(Season.SelectItemsPerPage_Seasons, driver);
		Utility.ScrollToElement(Season.SelectItemsPerPage_Seasons, driver);
		new Select(Season.SelectItemsPerPage_Seasons).selectByVisibleText("100");

		Wait.wait3Second();

		String path = "//span[contains(@data-bind,'SeasonSelect') and text()='" + seasonName + "']" + "//parent::td";
		WebElement season = driver.findElement(By.xpath(path));
		Wait.explicit_wait_visibilityof_webelement(season, driver);
		Utility.ScrollToElement(season, driver);
		assertEquals(season.getText(), seasonName, "Failed to create new Season");

		return season;
	}

	public String UpdateSeasonName(WebDriver driver, String SeasonName) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Season.NG_Season_Name.sendKeys("_Updated");
		return SeasonName += "_Updated";
	}

	public ArrayList<String> SeasonDeatilsUpdate(WebDriver driver, String SeasonName, String Status)
			throws InterruptedException {
		// TODO Auto-generated method stub
		ArrayList<String> test_steps = new ArrayList<String>();

		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Season.NG_Season_Name.sendKeys(SeasonName);
		Season.Season_Status.click();
		new Select(Season.Season_Status).selectByVisibleText(Status);
		test_steps.add("Enter Season Name: " + SeasonName);
		return test_steps;
	}

	public void NewSeasonCreation(WebDriver driver, String SeasonName, String Status, ArrayList<String> test_steps)
			throws InterruptedException {

		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);

		Wait.WaitForElement(driver, OR.NewSeason_Button);
		Season.NewSeason_Button.click();
		test_steps.add("Clicked on New Season Button");

		Wait.WaitForElement(driver, OR.NG_Season_Name);
		Season.NG_Season_Name.sendKeys(SeasonName);
		test_steps.add("Entered Season Name  " + SeasonName);
		Season.Season_Status.click();
		new Select(Season.Season_Status).selectByVisibleText(Status);
		test_steps.add("Selected the Status as " + Status);
		seasonLogger.info("Selected the Status as " + Status);

		Wait.explicit_wait_visibilityof_webelement(Season.NG_Season_start_Date, driver);
		Season.NG_Season_start_Date.click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Season.SelectSeasonTodayDate);
		test_steps.add("Selected Today Date: " + Utility.GettingDate(driver));
		seasonLogger.info("Selected Today Date: " + Utility.GettingDate(driver));

		String onlytodayDate = Utility.GettingDate(driver);
		int todayDate = Integer.parseInt(onlytodayDate);
		String onlynextWeekDate = Utility.GeetingNextWeek(driver);
		int nextWeekDate = Integer.parseInt(onlynextWeekDate);

		Wait.explicit_wait_visibilityof_webelement(Season.NG_Season_End_Date, driver);
		Season.NG_Season_End_Date.click();

		if (todayDate > nextWeekDate) {
			seasonLogger.info("Clicked on Next Month");
			Wait.explicit_wait_visibilityof_webelement(Season.ClickOnNext, driver);
			Season.ClickOnNext.click();

			test_steps.add("Clicked on Next Month");
		}

		String cellpath = "//td[text()='" + nextWeekDate + "']";
		WebElement End_Date = driver.findElement(By.xpath(cellpath));
		Wait.explicit_wait_visibilityof_webelement(End_Date, driver);
		// Utility.ScrollToElement(End_Date, driver);
		End_Date.click();
		seasonLogger.info("selected Next Week of " + nextWeekDate + "th date");
		test_steps.add("Selected Next Week of " + nextWeekDate + "th date");

		Season.NG_Season_Save.click();
		test_steps.add("Clicked on Save");

		test_steps.add("Weekly Season is created Successfully with the name of " + SeasonName);
		test_steps
				.add("Weekly Season is created from Today " + todayDate + "th to Next Week of " + nextWeekDate + "th");
		seasonLogger.info("Weekly Season is created Successfully with the name of " + SeasonName);
		seasonLogger
				.info("Weekly Season is created from Today " + todayDate + "th to Next Week of " + nextWeekDate + "th");
	}

	public ArrayList<String> SelectStartDate(WebDriver driver, int days) throws InterruptedException, ParseException {

		ArrayList<String> test_steps = new ArrayList<String>();
		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);

		String startDate = Utility.getDatePast_FutureDate(days);
		String date = Season.EnterSeasonStartDate.getAttribute("value");
		// Season.EnterSeasonStartDate.clear();
		for (int i = 0; i < date.length(); i++) {
			Season.EnterSeasonStartDate.sendKeys(Keys.BACK_SPACE);
		}
		Season.EnterSeasonStartDate.sendKeys(startDate);
		test_steps.add("Enter start date  : " + startDate);
		return test_steps;
	}

	public ArrayList<String> SelectEndDate(WebDriver driver, int days) throws InterruptedException, ParseException {

		// days as considering how many days u like select in future and past
		ArrayList<String> test_steps = new ArrayList<String>();
		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		String endDate = Utility.getDatePast_FutureDate(days);
		String date = Season.EnterSeasonEndDate.getAttribute("value");
		for (int i = 0; i < date.length(); i++) {
			Season.EnterSeasonEndDate.sendKeys(Keys.BACK_SPACE);
		}
		Season.EnterSeasonEndDate.sendKeys(endDate);
		test_steps.add("Enter end date : " + endDate);
		return test_steps;
	}

	public boolean SearchSeason(WebDriver driver, String SeasonName, boolean isClick)
			throws InterruptedException, ParseException {

		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Wait.WaitForElement(driver, OR_Setup.SeasonPagination);
		Wait.explicit_wait_visibilityof_webelement(Season.SeasonPagination.get(0), driver);
		String path = "//table//tr//td//a[text()='" + SeasonName + "']";
		System.out.println(path);
		boolean isSeasonExist = false;
		System.out.println(SeasonName);
		if (Season.SeasonPagination.size() > 3) {
			for (int i = 2; i < Season.SeasonPagination.size(); i++) {
				List<WebElement> element = driver.findElements(By.xpath(path));
				System.out.println("element : " + element.size());
				if (element.size() > 0) {
					WebElement element1 = driver.findElement(By.xpath(path));
					Utility.ScrollToElement(element1, driver);
					isSeasonExist = true;
				} else {
					Utility.ScrollToElement(Season.SeasonPagination.get(i), driver);
					Season.SeasonPagination.get(i).click();
					Wait.wait3Second();
					Wait.explicit_wait_visibilityof_webelement(Season.SeasonPagination.get(i), driver);
				}
			}
		} else {

			List<WebElement> element1 = driver.findElements(By.xpath(path));
			if (element1.size() == 1) {
				WebElement element_season = driver.findElement(By.xpath(path));
				Utility.ScrollToElement(element_season, driver);
				isSeasonExist = true;
			} else {
				isSeasonExist = false;
			}

		}
		// assertEquals(isSeasonExist, true, "Falied : newely created season
		// does not find");

		if (isClick && isSeasonExist) {
			try {
				WebElement element1 = driver.findElement(By.xpath(path));
				Utility.ScrollToElement(element1, driver);
				element1.click();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		return isSeasonExist;
	}

	public void CloseSeasonTab(WebDriver driver) throws InterruptedException, ParseException {

		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Wait.WaitForElement(driver, OR_Setup.CloseSeasonTab);
		Wait.explicit_wait_visibilityof_webelement(Season.CloseSeasonTab, driver);
		Season.CloseSeasonTab.click();

		Wait.WaitForElement(driver, OR_Setup.SearchSeason_Btn);
		Wait.explicit_wait_visibilityof_webelement(Season.SearchSeason_Btn, driver);

	}

	public void SearchSeasonButton(WebDriver driver) throws InterruptedException, ParseException {

		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);

		Wait.WaitForElement(driver, OR_Setup.SearchSeason_Btn);
		Wait.explicit_wait_visibilityof_webelement(Season.SearchSeason_Btn, driver);
		Wait.explicit_wait_elementToBeClickable(Season.SearchSeason_Btn, driver);
		Season.SearchSeason_Btn.click();

		Wait.WaitForElement(driver, OR_Setup.SearchSeason_Btn);
		Wait.explicit_wait_visibilityof_webelement(Season.SearchSeason_Btn, driver);
		Wait.explicit_wait_elementToBeClickable(Season.SearchSeason_Btn, driver);

	}

	public boolean SearchSeasonButton_Toaster(WebDriver driver) throws InterruptedException, ParseException {

		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);

		// Wait.WaitForElement(driver, OR.SearchSeason_Btn);
		Wait.explicit_wait_visibilityof_webelement(Season.SearchSeason_Btn, driver);
		// Wait.explicit_wait_elementToBeClickable(Season.SearchSeason_Btn,
		// driver);
		Season.SearchSeason_Btn.click();
		boolean isSeasonExist = true;
		try {
			System.out.println("in search try");
			// Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
			Wait.explicit_wait_visibilityof_webelement_3(Season.Toaster_Message, driver);
			String message = Season.Toaster_Message.getText();
			Season.Toaster_Message.click();
			System.out.println(message);
			if (message.contains("No records meet your criteria")) {
				System.out.println("in if");
				isSeasonExist = false;
			}

		} catch (Exception e) {
			isSeasonExist = true;
		}
		return isSeasonExist;

	}

	public ArrayList<String> SetSeasonPeriod(WebDriver driver, String StartDate, String endDate,
			ArrayList<String> test_steps) throws ParseException, InterruptedException {

		WebElements_Create_Seasons Season = new WebElements_Create_Seasons(driver);
		Wait.explicit_wait_visibilityof_webelement(Season.NG_Season_start_Date, driver);
		Utility.ScrollToElement(Season.NG_Season_start_Date, driver);
		Season.NG_Season_start_Date.sendKeys(StartDate);
		Utility.app_logs.info("Season Start Date: " + StartDate);
		test_steps.add("Season Start Date: " + StartDate);
		assertEquals(Season.NG_Season_start_Date.getAttribute("value"), StartDate, "Failed: Start Date missmatced");
		Utility.ScrollToElement(Season.NG_Season_End_Date, driver);
		Season.NG_Season_End_Date.sendKeys(endDate);
		Utility.app_logs.info("Season End Date: " + endDate);
		test_steps.add("Season End Date: " + endDate);
		assertEquals(Season.NG_Season_End_Date.getAttribute("value"), endDate, "Failed: End Date missmatced");
		return test_steps;
	}

}
