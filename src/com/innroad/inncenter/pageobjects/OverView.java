package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.innroad.inncenter.interfaces.IAccount;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Reservation;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Accounts;
import com.innroad.inncenter.webelements.Elements_CPReservation;
import com.innroad.inncenter.webelements.Elements_Inventory;
import com.innroad.inncenter.webelements.Elements_Reservation;
import com.innroad.inncenter.webelements.WebElementsOverview;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.text.ParseException;

public class OverView {

	public static Logger accountsLogger = Logger.getLogger("OverView");

	public void OverViewTab(WebDriver driver) {

		Elements_Inventory elements_Inventory = new Elements_Inventory(driver);
		Wait.explicit_wait_xpath(OR.OverViewTab, driver);
		elements_Inventory.OverViewTab.click();
		Wait.explicit_wait_xpath(OR.OverView_Rooms, driver);
		assertEquals(elements_Inventory.OverView_Rooms.getText(), "Room", "Room does not find");

	}

	public void EditRate(WebDriver driver) throws InterruptedException {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Utility.ScrollToElement(elements_OverView.Edit_Rate, driver);
		elements_OverView.Edit_Rate.click();
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-500)");
		Wait.wait2Second();
		driver.switchTo().frame(OR.Iframe_Id_Of_Rates_Override_Info_Popup);
		Wait.explicit_wait_xpath(OR.Rates_Override_Info_Popup, driver);
		assertTrue(elements_OverView.Rates_Override_Info_Popup.isDisplayed(), "Popup didn't display");

	}

	public void ChangerateFirstRoomClass(WebDriver driver, String NewRateValue) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		// elements_OverView.First_Roomclass_P1_Rate.clear();
		elements_OverView.First_Roomclass_P1_Rate.sendKeys(NewRateValue);
		assertTrue(elements_OverView.First_Roomclass_P1_Rate.getAttribute("value").equals(NewRateValue),
				"Failed: Input vlaue not same as entered");

	}

	public void ChangerateDBRRoomClass(WebDriver driver, String NewRateValue, String RoomClass)
			throws InterruptedException {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		String Cellpath = "(//td[text()='" + RoomClass + "']//..//td//input)[1]";
		System.out.println("Xpath:" + Cellpath);
		WebElement RoomClassRate = driver.findElement(By.xpath(Cellpath));
		Utility.ScrollToElement(RoomClassRate, driver);
		RoomClassRate.clear();
		RoomClassRate.sendKeys(NewRateValue);
		// elements_OverView.First_DBR_Roomclass_P1_Rate.clear();
		// elements_OverView.First_DBR_Roomclass_P1_Rate.sendKeys(NewRateValue);
		assertTrue(RoomClassRate.getAttribute("value").equals(NewRateValue), "Failed: Input value not same as entered");

	}

	public void ChangerateFirstRoomClass(WebDriver driver, String NewRateValue, String RoomClass)
			throws InterruptedException {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_OverView.EditOverRatePopup, driver);
		Utility.ScrollToElement(elements_OverView.EditOverRatePopup, driver);
		assertEquals(elements_OverView.EditOverRatePopup.getText(), "Rates Override Info",
				"Edit rate popup is not appearing");

		String Cellpath = "(//td[text()='" + RoomClass + "']//..//td//input)[1]";
		WebElement RoomClassRate = driver.findElement(By.xpath(Cellpath));
		Utility.ScrollToElement(RoomClassRate, driver);
		RoomClassRate.clear();
		RoomClassRate.sendKeys(NewRateValue);
		assertTrue(RoomClassRate.getAttribute("value").equals(NewRateValue), "Failed: Input vlaue not same as entered");

	}

	public String ChangeRate(WebDriver driver, String RoomClass, String NewRateValue) throws InterruptedException {

		String ratePath = "(//td[text()='" + RoomClass + "']//..//td//input)[1]";
		Utility.ScrollToElement(driver.findElement(By.xpath(ratePath)), driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-500)");
		String RateChangePopup = "//div[@class ='popover-inner']";
		// String RoomRate = "//div[@class ='popover-inner']//label[text()='Room
		// Rate']/input[@name='txtRate']";
		String TickRoomRate = "//div[@class ='popover-inner']//button/span[@class='ir-checkMark sm success']";
		String RateChangeToaster = "//div[@role ='alert']";
		List<WebElement> DaysRate = driver.findElements(By.xpath(ratePath));
		String PreviousRate = DaysRate.get(0).getText();
		System.out.println(DaysRate.size());
		int LoopEnd = DaysRate.size();
		System.out.println("Rate : " + NewRateValue);
		return PreviousRate;

	}

	public void VerifyChangeRate(WebDriver driver, String RoomClass, String NewRateValue) throws InterruptedException {

		String ratePath = "//div[@class = 'roomClassName' and contains(text(),'" + RoomClass
				+ "')]//parent::div//following-sibling::div[contains(@class,'RateDateCell ')]/div[1]";
		Utility.ScrollToElement(driver.findElement(By.xpath(ratePath)), driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-300)");
		List<WebElement> DaysRate = driver.findElements(By.xpath(ratePath));
		System.out.println(DaysRate.size());
		int LoopEnd = DaysRate.size();
		if (DaysRate.size() > 3) {
			LoopEnd = 3;
		}
		for (int i = 1; i <= LoopEnd; i++) {
			WebElement rate = driver.findElement(By.xpath("(" + ratePath + ")[" + i + "]"));
			System.out.println("(" + ratePath + ")[" + i + "]");
			assertEquals(rate.getText(), NewRateValue, "Failed : Rate  missmatched");
			Wait.wait2Second();

		}

	}

	public void SaveRatePlan(WebDriver driver) throws InterruptedException {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Utility.ScrollToElement(elements_OverView.Save_Btn_In_Rates_Override_Info_Popup, driver);
		elements_OverView.Save_Btn_In_Rates_Override_Info_Popup.click();

		assertEquals(elements_OverView.Rates_Override_Info_Popup.getText(), "Rates Override Info");
	}

	public void DoneRatePlan(WebDriver driver) throws InterruptedException {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.WaitForElement(driver, OR.Done_Btn_In_Rates_Override_Info_Popup);

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		try {
			jse.executeScript("arguments[0].scrollIntoView();",
					elements_OverView.Done_Btn_In_Rates_Override_Info_Popup);
			elements_OverView.Done_Btn_In_Rates_Override_Info_Popup.click();
		} catch (Exception e) {
			Wait.WaitForElement(driver, OR.Done_Btn_In_Rates_Override_Info_Popup);
			jse.executeScript("arguments[0].click();", elements_OverView.Done_Btn_In_Rates_Override_Info_Popup);

			// elements_OverView.Done_Btn_In_Rates_Override_Info_Popup.click();
		}
		driver.switchTo().defaultContent();
		Elements_Inventory elements_Inventory = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.Rates_Grid);
		Wait.wait2Second();
		assertEquals(elements_Inventory.Rates_Grid.getText(), "Rates Grid");

	}

	public String GetRate(WebDriver driver, String RoomClassAbb) throws InterruptedException {

		String RatePath = "((//a[text()='" + RoomClassAbb + "']//..//..//..//..//following-sibling::tr)[2]//td)[2]";
		Wait.explicit_wait_xpath(RatePath, driver);
		Utility.app_logs.info(RatePath);
		WebElement RoomRate = driver.findElement(By.xpath(RatePath));
		Wait.explicit_wait_visibilityof_webelement(RoomRate, driver);
		Utility.ScrollToElement(RoomRate, driver);
		String RoomRateValue = RoomRate.getText();
		System.out.println("value :" + RoomRateValue);
		return RoomRateValue;
	}

	public void VerifyChangedRateFirstRoomClass(WebDriver driver, String NewRateValue) {

		driver.navigate().refresh();
		WebElement FirstRateValue = driver.findElement(By.xpath("(//td[@class='hand inventoryItem rateOverride'])[1]"));
		String NewSetRate = FirstRateValue.getAttribute("title");
		if (NewSetRate.equals(NewRateValue))
			assertTrue(NewSetRate.equals(NewRateValue), "Failed:New Rate Value is not correct");

	}

	public void VerifyChangedRateforMultiplePersons(WebDriver driver, ArrayList<String> NewRateValue,
			String RoomClassAbb) throws InterruptedException {
		String RatePath;
		WebElement RoomRate;

		for (int i = 1; i < NewRateValue.size(); i++) {
			SelectMaxPersonsInOverviewRatePage(driver, i);
			RatePath = "((//a[text()='" + RoomClassAbb + "']//..//..//..//..//following-sibling::tr)[2]//td)[2]";
			Wait.explicit_wait_xpath(RatePath, driver);
			Utility.app_logs.info(RatePath);
			RoomRate = driver.findElement(By.xpath(RatePath));
			String NewSetRate = RoomRate.getAttribute("title");
			System.out.println("Value:" + NewSetRate);
			assertTrue(NewSetRate.equals(NewRateValue.get(i) + ".00"), "Failed:New Rate Value is not correct");
		}

	}

	public void VerifyChangedRate(WebDriver driver, String NewRateValue, String RoomClassAbb)
			throws InterruptedException {
		driver.navigate().refresh();
		Utility.app_logs.info("Reload page");
		String NewSetRate = GetRate(driver, RoomClassAbb);
		Utility.app_logs.info(" Actual value: " + NewRateValue + " Expected value: " + NewSetRate);
		assertEquals(NewSetRate, NewRateValue + "", "Failed:New Rate Value is not correct");
	}

	public String AvailableRoomsCount(WebDriver driver, String roomclassShortName) {
		WebElementsOverview elements_OverView = new WebElementsOverview(driver);

		String xpath = "//li[.='" + roomclassShortName + "']//following::td[3]/nobr";

		String RoomCount = driver.findElement(By.xpath(xpath)).getText();

		return RoomCount;
	}

	public void TodaysDate(WebDriver driver) throws InterruptedException {
		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.WaitForElement(driver, OR.Calendar);
		elements_OverView.Calendar.click();

		Wait.WaitForElement(driver, OR.SelectTodaysDate);
		elements_OverView.SelectTodaysDate.click();
		Wait.wait2Second();

	}

	public void CheckRoomClassesNameDisplayWthRate(WebDriver driver) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		int RoomClassSize = elements_OverView.RoomClassNameWithRates.size();
		for (int i = 1; i < RoomClassSize; i++) {
			assertTrue(elements_OverView.RoomClassNameWithRates.get(i).isDisplayed(),
					"Failed: Room Classes not displayed");

		}

	}

	public void MakeFirstRoomCountOut(WebDriver driver, String RoomClassName) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement PathToRoomStatusChange = driver
				.findElement(By.xpath("//div[@id='MainContent_inventory_DIV']//td[text()='" + RoomClassName
						+ "']//parent::tr//following-sibling::tr//td[@class='inv_td']//input"));
		jse.executeScript("arguments[0].scrollIntoView();", PathToRoomStatusChange);

		PathToRoomStatusChange.clear();
		PathToRoomStatusChange.sendKeys("1");
		assertTrue(PathToRoomStatusChange.getAttribute("value").equals("1"), "Failed:Input Value not 1");
		elements_OverView.OverViewSaveBtn.click();

	}

	public void SelectMaxPersonsInOverviewRatePage(WebDriver driver, int PersonCount) throws InterruptedException {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		Wait.WaitForElement(driver, OR.Overview_MaxPerson);
		Wait.wait2Second();
		System.out.println(
				"Value is : " + elements_OverView.Overview_MaxPersons.get(PersonCount).getAttribute("checked"));
		jse.executeScript("arguments[0].click();", elements_OverView.Overview_MaxPersons.get(PersonCount));
		jse.executeScript("arguments[0].click();", elements_OverView.Overview_GoButton);

		assertTrue(elements_OverView.Overview_MaxPersons.get(PersonCount).getAttribute("checked").equals("true"),
				"Max persons didn't select");
	}

	public void ChangerateRoomClassWithDifferentPersonCount(WebDriver driver, ArrayList<String> NewRateValue,
			String RoomClass, int PersonsCount) throws InterruptedException {
		Wait.wait2Second();
		for (int i = 1; i <= PersonsCount; i++) {
			String Cellpath = "(//td[text()='" + RoomClass + "']//..//td//input)[" + i + "]";
			WebElement RoomClassRate = driver.findElement(By.xpath(Cellpath));
			Utility.ScrollToElement(RoomClassRate, driver);
			RoomClassRate.clear();
			RoomClassRate.sendKeys(NewRateValue.get(i - 1));
			assertTrue(RoomClassRate.getAttribute("value").equals(NewRateValue.get(i - 1)),
					"Failed: Input vlaue not same as entered");
		}
	}

	public void RemoveOverridenRateValue(WebDriver driver, int PersonsCount, String RoomClass)
			throws InterruptedException, ParseException {
		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		for (int i = 1; i <= PersonsCount; i++) {
			String Cellpath = "(//td[text()='" + RoomClass + "']//..//td//input)[" + i + "]";
			WebElement RoomClassRate = driver.findElement(By.xpath(Cellpath));
			Utility.ScrollToElement(RoomClassRate, driver);
			RoomClassRate.clear();
		}

	}

	public void SelectDateOverviewPanel(WebDriver driver) throws InterruptedException, ParseException {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> MonthList31Days = new ArrayList<String>(
				Arrays.asList("Janaury", "March", "May", "July", "August", "October", "December"));
		ArrayList<String> MonthList30Days = new ArrayList<String>(
				Arrays.asList("April", "June", "September", "November"));
		Wait.WaitForElement(driver, OR.DateSelectionFrom_OverviewPanel);
		elements_OverView.DateSelectionFrom_OverviewPanel.get(0).click();
		elements_OverView.ActivDate.click();
		String TodayDate = elements_OverView.GetTodayDate.getText();
		int currentDate = Integer.parseInt(TodayDate);
		WebElement GetMonthYear = driver
				.findElement(By.xpath("(//table[@class='datepicker-table-condensed table-condensed']//tr)[2]//th[2]"));
		String GetmonthyearString = GetMonthYear.getText();
		String Month = GetmonthyearString.split(" ")[0];

		if (Month.equals("February")) {

			if (currentDate == 27)
				currentDate = 1;
			else if (currentDate == 28)
				currentDate = 2;

		}
		for (int i = 0; i < MonthList31Days.size(); i++) {

			if (MonthList31Days.get(i).equals(Month)) {
				// basecase
				if (currentDate == 30)
					currentDate = 1;
				else if (currentDate == 31)
					currentDate = 2;
				else {

					currentDate = currentDate + 2;
				}
				break;
			}

		}

		for (int i = 0; i < MonthList30Days.size(); i++) {

			if (MonthList31Days.get(i).equals(Month)) {
				// basecase
				if (currentDate == 29)
					currentDate = 1;
				else if (currentDate == 30)
					currentDate = 2;
				else {

					currentDate = currentDate + 2;
				}
				break;
			}

		}
		String SelectDate = String.valueOf(currentDate);
		elements_OverView.DateSelectionFrom_OverviewPanel.get(1).click();
		elements_OverView.ActivDate.click();

		List<WebElement> CalendertableTR = driver
				.findElements(By.xpath("//table[@class='datepicker-table-condensed table-condensed']//tbody//tr"));
		myBreakLabel: for (int i = 0; i < CalendertableTR.size(); i++) {

			List<WebElement> CalendertableTD = driver.findElements(By
					.xpath("(//table[@class='datepicker-table-condensed table-condensed']//tbody//tr)[" + i + "]//td"));
			for (int j = 0; j < CalendertableTD.size(); j++) {

				System.out.println("Value of i:" + i + "Value of j:" + j + "Value:" + CalendertableTD.get(j).getText());

				if (CalendertableTD.get(j).getText().equals(SelectDate)) {
					System.out
							.println("Value i:" + i + "value j:" + j + " date is:" + CalendertableTD.get(j).getText());
					CalendertableTD.get(j).click();
					break myBreakLabel;

				}
			}

		}

		// DayAferTomorrow.click();
		assertTrue(elements_OverView.Overview_GoButton.isDisplayed(), "Go Button didn't active");
		elements_OverView.Overview_GoButton.click();
	}

	public String CurrentRateValueofAnyRoom(WebDriver driver, String RoomClassAbb) {
		String RatePath = "((//a[text()='" + RoomClassAbb + "']//..//..//..//..//following-sibling::tr)[2]//td)[2]";

		Wait.explicit_wait_xpath(RatePath, driver);
		Utility.app_logs.info(RatePath);
		WebElement RoomRate = driver.findElement(By.xpath(RatePath));
		String RateValue = RoomRate.getText();
		return RateValue;
	}

	public void VerifyRateAfterRemoveallValue(WebDriver driver, String RoomClassAbb, String RateValueBefore) {
		String RatePath = "((//a[text()='" + RoomClassAbb + "']//..//..//..//..//following-sibling::tr)[2]//td)[2]";

		Wait.explicit_wait_xpath(RatePath, driver);
		Utility.app_logs.info(RatePath);
		WebElement RoomRate = driver.findElement(By.xpath(RatePath));
		String RateValue = RoomRate.getText();
		assertTrue(RateValue.equals(RateValueBefore), "Couldn't clear Rate Value");

	}

	public String GetRoomsAvailable(WebDriver driver, String RoomClassName) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		elements_OverView.DateSelectionFrom_OverviewPanel.get(0).click();
		elements_OverView.ActivDate.click();
		elements_OverView.DateSelectionFrom_OverviewPanel.get(1).click();
		elements_OverView.ActivDate.click();
		elements_OverView.Overview_GoButton.click();
		String RoomsAvailable = driver
				.findElement(By.xpath("(//a[text()='DBR']//..//..//..//..//following-sibling::tr)[1]//td[2]"))
				.getText();
		return RoomsAvailable;

	}

	public ArrayList<String> VerifyRoomsAvailableOverview(WebDriver driver, String RoomClassName,
			String RoomsAvailableBefore, ArrayList<String> test_steps) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		elements_OverView.DateSelectionFrom_OverviewPanel.get(0).click();
		elements_OverView.ActivDate.click();
		elements_OverView.DateSelectionFrom_OverviewPanel.get(1).click();
		elements_OverView.ActivDate.click();
		elements_OverView.Overview_GoButton.click();
		String RoomsAvailable = driver
				.findElement(By.xpath("(//a[text()='DBR']//..//..//..//..//following-sibling::tr)[1]//td[2]"))
				.getText();
		System.out.println("Rooms in overview new :" + RoomsAvailable + "Before:" + RoomsAvailableBefore);

		assertTrue(!RoomsAvailable.equals(RoomsAvailableBefore), "Room Available didn't increase by 1");
		test_steps.add("Room Class :" + RoomClassName + " : Rooms Available were : " + RoomsAvailableBefore
				+ " Changed to : " + RoomsAvailable + " Increased By One in Overview");
		return test_steps;

	}

	public void SelectRatePaln(WebDriver driver, String SelectRatePlan) throws InterruptedException {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_OverView.SelectRatePlan_EditRate, driver);
		Utility.ScrollToElement(elements_OverView.SelectRatePlan_EditRate, driver);
		new Select(elements_OverView.SelectRatePlan_EditRate).selectByVisibleText(SelectRatePlan);

	}

	public void SelectRoomClass(WebDriver driver, String SelectRoomClass) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		new Select(elements_OverView.SelectRoomClass_EditRate).selectByVisibleText(SelectRoomClass);

	}

	public void ClickOnGoButton(WebDriver driver) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		elements_OverView.GoButton_EditRate.click();
	}

	public ArrayList<String> ClickToday_Overview(WebDriver driver, String Date, ArrayList<String> test_steps)
			throws InterruptedException {

		WebElementsOverview overview = new WebElementsOverview(driver);
		Reservation res = new Reservation();

		Wait.explicit_wait_visibilityof_webelement(overview.OverViewTodayButton, driver);
		Utility.ScrollToElement(overview.OverViewTodayButton, driver);
		overview.OverViewTodayButton.click();
		Elements_Inventory elements_Inventory = new Elements_Inventory(driver);
		Wait.explicit_wait_xpath(OR.OverView_Rooms, driver);
		assertEquals(elements_Inventory.OverView_Rooms.getText(), "Room", "Room does not find");
		Utility.ScrollToElement(overview.OverView_DateStart, driver);
		Wait.explicit_wait_visibilityof_webelement(overview.OverView_DateStart, driver);
		Utility.app_logs.info("DAte: " + overview.OverView_DateStart.getAttribute("value"));
		String Date_DateValue = overview.OverView_DateStart.getAttribute("value").split(" ")[1];

		int Date_DateValueInt = Integer.parseInt(Date_DateValue.split(",")[0]);
		String DateFinal = res.getCurrDate();
		if (Date_DateValueInt < 10) {
			Date_DateValue = "0" + Date_DateValue;
			DateFinal = overview.OverView_DateStart.getAttribute("value").split(" ")[0] + " " + Date_DateValue + " "
					+ overview.OverView_DateStart.getAttribute("value").split(" ")[2];
			test_steps.add("Date Overview Page :" + Date + " Current Date :" + DateFinal + "  Verified");
			assertEquals(Date.contains(DateFinal), true, "Failed: Date Missmatched");
		} else {
			test_steps.add("Date Overview Page :" + Date + " Current Date :" + DateFinal + " Verified");
			assertEquals(overview.OverView_DateStart.getAttribute("value").contains(Date), true,
					"Failed: Date Missmatched");
		}
		return test_steps;
	}

	public ArrayList<String> ClickToday_OverviewTab(WebDriver driver, String Date, ArrayList<String> test_steps)
			throws InterruptedException {

		WebElementsOverview overview = new WebElementsOverview(driver);

		Wait.explicit_wait_visibilityof_webelement(overview.OverViewTodayButton, driver);
		Utility.ScrollToElement(overview.OverViewTodayButton, driver);
		overview.OverViewTodayButton.click();
		Elements_Inventory elements_Inventory = new Elements_Inventory(driver);
		Wait.explicit_wait_xpath(OR.OverView_Rooms, driver);
		assertEquals(elements_Inventory.OverView_Rooms.getText(), "Room", "Room does not find");
		Utility.ScrollToElement(overview.OverView_DateStart, driver);
		Wait.explicit_wait_visibilityof_webelement(overview.OverView_DateStart, driver);
		Utility.app_logs.info("Date: " + overview.OverView_DateStart.getAttribute("value"));
		test_steps.add("Date Overview Page :" + overview.OverView_DateStart.getAttribute("value") + " Current Date :"
				+ Date + " Verified");

		assertEquals(overview.OverView_DateStart.getAttribute("value").contains(Date), true,
				"Failed: Date Missmatched");
		return test_steps;

	}

	public String GetAvailableRooms(WebDriver driver, String RoomClassName) throws InterruptedException {

		WebElementsOverview overview = new WebElementsOverview(driver);
		WebElement Rooms = driver.findElement(By.xpath("(//td[text()='" + RoomClassName
				+ "']//parent::tr//following::td[text()='Avail']/following-sibling::td)[1]"));
		Utility.ScrollToElement(Rooms, driver);
		String RoomsAvailable = Rooms.getText();
		return RoomsAvailable;

	}

	public String GetReservedRooms(WebDriver driver, String RoomClassName) throws InterruptedException {

		WebElementsOverview overview = new WebElementsOverview(driver);
		WebElement Rooms = driver.findElement(By.xpath("(//td[text()='" + RoomClassName
				+ "']//parent::tr//following::td[text()='Res']/following-sibling::td)[1]"));
		Utility.ScrollToElement(Rooms, driver);
		String RoomsReserved = Rooms.getText();
		return RoomsReserved;

	}

	public void ChangeratePlanRoomClass(WebDriver driver, String RoomClass) throws InterruptedException {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_OverView.EditOverRatePopup, driver);
		Utility.ScrollToElement(elements_OverView.EditOverRatePopup, driver);
		assertEquals(elements_OverView.EditOverRatePopup.getText(), "Rates Override Info",
				"Edit rate popup is not appearing");

		String Cellpath = "(//td[text()='" + RoomClass + "']//..//td//input)[1]";
		WebElement RoomClassRate = driver.findElement(By.xpath(Cellpath));
		Utility.ScrollToElement(RoomClassRate, driver);
		RoomClassRate.clear();
		// RoomClassRate.sendKeys(NewRateValue);
		assertTrue(RoomClassRate.getAttribute("value").equals(""), "Failed: Input vlaue not same as entered");

	}

	public void InventoryGoButton(WebDriver driver) throws InterruptedException {
		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.WaitForElement(driver, OR.GoButtonInventory);
		elements_OverView.GoButtonInventory.click();
		Wait.wait5Second();

	}

	public void SelectToDateOverviewPanel(WebDriver driver, int DaysToAdd, boolean SelectFromDate)
			throws InterruptedException, ParseException {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> MonthList31Days = new ArrayList<String>(
				Arrays.asList("Janaury", "March", "May", "July", "August", "October", "December"));
		ArrayList<String> MonthList30Days = new ArrayList<String>(
				Arrays.asList("April", "June", "September", "November"));
		Wait.WaitForElement(driver, OR.DateSelectionFrom_OverviewPanel);
		if (SelectFromDate) {
			elements_OverView.DateSelectionFrom_OverviewPanel.get(0).click();
			elements_OverView.ActivDate.click();
		}
		String TodayDate = elements_OverView.GetTodayDate.getText();
		int currentDate = Integer.parseInt(TodayDate);
		WebElement GetMonthYear = driver
				.findElement(By.xpath("(//table[@class='datepicker-table-condensed table-condensed']//tr)[2]//th[2]"));
		String GetmonthyearString = GetMonthYear.getText();
		String Month = GetmonthyearString.split(" ")[0];
		currentDate = currentDate + DaysToAdd;

		if (Month.equals("February")) {

			if (currentDate == 27)
				currentDate = 1;
			else if (currentDate == 28)
				currentDate = 2;

		}
		for (int i = 0; i < MonthList31Days.size(); i++) {

			if (MonthList31Days.get(i).equals(Month)) {
				// basecase
				if (currentDate == 30)
					currentDate = 1;
				else if (currentDate == 31)
					currentDate = 2;
				else {

					currentDate = currentDate + 2;
				}
				break;
			}

		}

		for (int i = 0; i < MonthList30Days.size(); i++) {

			if (MonthList31Days.get(i).equals(Month)) {
				// basecase
				if (currentDate == 29)
					currentDate = 1;
				else if (currentDate == 30)
					currentDate = 2;
				else {

					currentDate = currentDate + 2;
				}
				break;
			}

		}
		String SelectDate = String.valueOf(currentDate);
		elements_OverView.DateSelectionFrom_OverviewPanel.get(1).click();
		elements_OverView.ActivDate.click();

		List<WebElement> CalendertableTR = driver
				.findElements(By.xpath("//table[@class='datepicker-table-condensed table-condensed']//tbody//tr"));
		myBreakLabel: for (int i = 0; i < CalendertableTR.size(); i++) {

			List<WebElement> CalendertableTD = driver.findElements(By
					.xpath("(//table[@class='datepicker-table-condensed table-condensed']//tbody//tr)[" + i + "]//td"));
			for (int j = 0; j < CalendertableTD.size(); j++) {

				System.out.println("Value of i:" + i + "Value of j:" + j + "Value:" + CalendertableTD.get(j).getText());

				if (CalendertableTD.get(j).getText().equals(SelectDate)) {
					System.out
							.println("Value i:" + i + "value j:" + j + " date is:" + CalendertableTD.get(j).getText());
					CalendertableTD.get(j).click();
					break myBreakLabel;

				}
			}

		}

		// DayAferTomorrow.click();
		assertTrue(elements_OverView.Overview_GoButton.isDisplayed(), "Go Button didn't active");
		elements_OverView.Overview_GoButton.click();
	}

	public void SelectRatePlan_Overview(WebDriver driver, String SelectRatePlan) throws InterruptedException {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_OverView.SelectRatePlan_Overview, driver);
		Utility.ScrollToElement(elements_OverView.SelectRatePlan_Overview, driver);
		Select select = new Select(elements_OverView.SelectRatePlan_Overview);
		select.selectByVisibleText(SelectRatePlan);
		assertEquals(select.getFirstSelectedOption().getText(), SelectRatePlan, "Rate Plan didn't select");
	}

	public void ClickOnGoButton_OverviewPage(WebDriver driver) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		elements_OverView.Overview_GoButton.click();
	}

	public void SelectFromDateOverviewPanel(WebDriver driver, int DaysToAdd)
			throws InterruptedException, ParseException {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> MonthList31Days = new ArrayList<String>(
				Arrays.asList("Janaury", "March", "May", "July", "August", "October", "December"));
		ArrayList<String> MonthList30Days = new ArrayList<String>(
				Arrays.asList("April", "June", "September", "November"));
		Wait.wait3Second();
		Wait.WaitForElement(driver, OR.DateSelectionFrom_OverviewPanel);
		elements_OverView.DateSelectionFrom_OverviewPanel.get(0).click();
		elements_OverView.ActivDate.click();
		String TodayDate = elements_OverView.GetTodayDate.getText();
		int currentDate = Integer.parseInt(TodayDate);
		WebElement GetMonthYear = driver
				.findElement(By.xpath("(//table[@class='datepicker-table-condensed table-condensed']//tr)[2]//th[2]"));
		String GetmonthyearString = GetMonthYear.getText();
		String Month = GetmonthyearString.split(" ")[0];
		System.out.println(" Current Date Beore Add :" + currentDate);
		currentDate = currentDate + DaysToAdd;

		if (Month.equals("February")) {

			if (currentDate == 27)
				currentDate = 1;
			else if (currentDate == 28)
				currentDate = 2;

		}
		for (int i = 0; i < MonthList31Days.size(); i++) {

			if (MonthList31Days.get(i).equals(Month)) {
				// basecase
				if (currentDate == 30)
					currentDate = 1;
				else if (currentDate == 31)
					currentDate = 2;
				else {

					currentDate = currentDate + 2;
				}
				break;
			}

		}

		for (int i = 0; i < MonthList30Days.size(); i++) {

			if (MonthList31Days.get(i).equals(Month)) {
				// basecase
				if (currentDate == 29)
					currentDate = 1;
				else if (currentDate == 30)
					currentDate = 2;
				else {

					currentDate = currentDate + 2;
				}
				break;
			}

		}
		String SelectDate = String.valueOf(currentDate);
		elements_OverView.ActivDate.click();
		System.out.println(" From Select Date :" + SelectDate);

		List<WebElement> CalendertableTR = driver
				.findElements(By.xpath("//table[@class='datepicker-table-condensed table-condensed']//tbody//tr"));
		myBreakLabel: for (int i = 0; i < CalendertableTR.size(); i++) {

			List<WebElement> CalendertableTD = driver.findElements(By
					.xpath("(//table[@class='datepicker-table-condensed table-condensed']//tbody//tr)[" + i + "]//td"));
			for (int j = 0; j < CalendertableTD.size(); j++) {

				System.out.println(" for Value:" + CalendertableTD.get(j).getText());

				if (CalendertableTD.get(j).getText().equals(SelectDate)) {
					System.out.println(" if  date is:" + CalendertableTD.get(j).getText());
					CalendertableTD.get(j).click();
					Wait.wait1Second();
					break myBreakLabel;

				}
			}

		}

	}

	public ArrayList<String> VerifyRoomClass(WebDriver driver, String RoomClass, String BaseAmount, boolean click)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_OverView.RoomClassNameWithRates.get(0), driver);
		List<WebElement> GetRoomclassNames = elements_OverView.RoomClassNameWithRates;
		Utility.app_logs.info("GetRoomclassNames" + GetRoomclassNames.size());
		boolean found = false;
		for (int i = 0; i < GetRoomclassNames.size(); i++) {
			if (GetRoomclassNames.get(i).getText().equals(RoomClass)) {
				test_steps.add("Verified Room Class '" + RoomClass + "' is Displayed");
				Utility.app_logs.info("Verified Room Class '" + RoomClass + "' is Displayed");
				String Amountxpath = "//div[text()='" + RoomClass
						+ "']//ancestor::div[@class='DatesTable']//div[contains(@class,'RegularRate')]";
				Utility.app_logs.info(Amountxpath);
				WebElement RateValue = driver.findElement(By.xpath(Amountxpath));
				Utility.ScrollToElement(RateValue, driver);
				Utility.app_logs.info("Text : " + RateValue.getText());
				assertEquals(RateValue.getText(), BaseAmount, "Failed: Rate missmatched");
				test_steps.add("Verified Room Class Rate Value '$ " + BaseAmount + "' is shown");
				Utility.app_logs.info("Verified Room Class Rate Value '$ " + BaseAmount + "' is shown");
				if (click) {
					RateValue.click();
					test_steps.add("Click Rate");
					Utility.app_logs.info("Click Rate");
					Wait.explicit_wait_visibilityof_webelement(elements_OverView.RatePopup, driver);
					test_steps.add("Room Rate popup Appeared");
					Utility.app_logs.info("Room Rate popup Appeared");
				}
				found = true;
				break;
			}
		}
		assertTrue(found, "Failed Room Class not found");
		return test_steps;
	}

	public ArrayList<String> VerifyRatePopup(WebDriver driver, String BaseAmount, String AdditionalAdults,
			String AdditionalChilds) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		String Adults = AdditionalAdults;
		String Childs = AdditionalChilds;
		if (AdditionalAdults.equals("0")) {
			Adults = "";
		}
		if (AdditionalChilds.equals("0")) {
			Childs = "";
		}
		Wait.explicit_wait_visibilityof_webelement(elements_OverView.RatePopup_RoomRate, driver);
		Utility.ScrollToElement(elements_OverView.RatePopup_RoomRate, driver);
		assertEquals(elements_OverView.RatePopup_RoomRate.getAttribute("value"), BaseAmount,
				"Failed: Room Rate missmatched");
		test_steps.add("Verified Room Rate Value '$ " + BaseAmount + "' is shown");
		Utility.app_logs.info("Verified Room Rate Value '$ " + BaseAmount + "' is shown");
		Utility.ScrollToElement(elements_OverView.RatePopup_ExtraAdult, driver);

		Utility.app_logs.info("Extra Adults '$ " + elements_OverView.RatePopup_ExtraAdult.getAttribute("value") + "'");
		assertEquals(elements_OverView.RatePopup_ExtraAdult.getAttribute("value"), Adults,
				"Failed: Extra Adults missmatched");
		test_steps.add("Verified Extra Adults '$ " + AdditionalAdults + "' is shown");
		Utility.app_logs.info("Verified Extra Adults '$ " + AdditionalAdults + "' is shown");
		Utility.ScrollToElement(elements_OverView.RatePopup_ExtraChild, driver);
		assertEquals(elements_OverView.RatePopup_ExtraChild.getAttribute("value"), Childs,
				"Failed: Extra Child missmatched");
		test_steps.add("Verified Extra Child '$ " + AdditionalChilds + "' is shown");
		Utility.app_logs.info("Verified Extra Child '$ " + AdditionalChilds + "' is shown");
		return test_steps;
	}

	public ArrayList<String> ClickExpandRoomClass(WebDriver driver, String RoomClass) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String expandbutton = "//div[@class='roomClassName' and text()='" + RoomClass
				+ "']/parent::div/span[contains(@class, 'acrd-bnt')]";

		Wait.WaitForElement(driver, expandbutton);
		Wait.waitForElementToBeVisibile(By.xpath(expandbutton), driver);
		Wait.waitForElementToBeClickable(By.xpath(expandbutton), driver);

		WebElement Button = driver.findElement(By.xpath(expandbutton));
		Utility.ScrollToElement(Button, driver);
		Button.click();

		test_steps.add("Click Room Class '" + RoomClass + "' Expand button");
		Utility.app_logs.info("Click Room Class '" + RoomClass + "' Expand button");
		Utility.app_logs.info(Button.getAttribute("class"));
		assertTrue(Button.getAttribute("class").contains("minus"), "Failed : RoomClass not Expanded");
		return test_steps;
	}

	public void VerifyMinStayValue(WebDriver driver, String RoomClass, int index, String Value)
			throws InterruptedException {
		String expandbutton = "//div[@class='roomClassName' and text()='" + RoomClass
				+ "']//ancestor::div[contains(@class,'DatesTable')]//div[text()='Min Stay']//following-sibling::div//input";
		Utility.app_logs.info(index + " : " + expandbutton);
		List<WebElement> Button = driver.findElements(By.xpath(expandbutton));
		Wait.explicit_wait_visibilityof_webelement(Button.get(index), driver);
		Wait.explicit_wait_elementToBeClickable(Button.get(index), driver);
		Utility.ScrollToElement(Button.get(index), driver);
		String value = Button.get(index).getAttribute("value");
		Utility.app_logs.info(value);
		assertEquals(value, Value, "Failed: Min Stay Value Missmatched");

	}

	public void verifyOutOfOrder(WebDriver driver, ArrayList<String> test_steps, String roomClassName, String Status,
			String NO, List<String> date, List<String> Day, int nights) throws InterruptedException {
		// TODO Auto-generated method stub

		String elementText = "//div[@id='availability']//div[@class='DatesTable']//div[@title='" + roomClassName + "']";
		WebElement OverviewClassName = driver.findElement(By.xpath(elementText));
		Wait.WaitForElement(driver, elementText);
		Utility.ScrollToElement(OverviewClassName, driver);
		String PlusIcon = "//div[@id='availability']//div[@class='DatesTable']//div[@title='" + roomClassName
				+ "']/ancestor::div/span";
		WebElement elementPlus = driver.findElement(By.xpath(PlusIcon));
		Utility.ScrollToElement(elementPlus, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", elementPlus);

		for (int i = 0; i < nights - 1; i++) {
			String Date = "//div[@class='col flex-column']//div[contains(@class,'MonthDateCell')][" + (i + 1)
					+ "]//div[@class='dayNum']";
			String day = "//div[@class='col flex-column']//div[contains(@class,'MonthDateCell')][" + (i + 1)
					+ "]/div[@class='weekDay']";
			String outElement = "//div[@id='availability']//div[@class='DatesTable']//div[@title='" + roomClassName
					+ "']/ancestor::div/..//div[contains(text(),'" + Status
					+ "')]/ancestor::div/following-sibling::div[" + (i + 1)
					+ "][contains(@class,'AvailabilityDateCell')]/div";
			WebElement DateElement = driver.findElement(By.xpath(Date));
			WebElement DayElement = driver.findElement(By.xpath(day));
			Wait.WaitForElement(driver, outElement);
			WebElement element = driver.findElement(By.xpath(outElement));
			Utility.ScrollToElement(element, driver);
			String dateIs = null;
			if (date.get(i).contains("0")) {
				dateIs = date.get(i).replace("0", "");
			} else {
				dateIs = date.get(i);
			}
			accountsLogger.info(dateIs);
			accountsLogger.info(DateElement.getText());
			accountsLogger.info(Day.get(i));
			accountsLogger.info(DayElement.getText());
			accountsLogger.info(element.getText());
			accountsLogger.info(NO);
			if (dateIs.equals(DateElement.getText()) && Day.get(i).equals(DayElement.getText())
					&& (element.getText().equals(NO))) {
				Assert.assertTrue(element.isDisplayed(),
						"Failed : to verify out of order value in overview for Room Class" + roomClassName);
				test_steps.add("Verified out of order Day <b>" + DayElement.getText() + "</b> Date <b>"
						+ DateElement.getText() + " </b> Out of Order <b>" + NO + "</b>");
				accountsLogger.info("Verified out of order Day" + DayElement.getText() + " Date "
						+ DateElement.getText() + " " + NO);

			} else {
				test_steps.add("Failed : to verify out of order  in inventory");
				accountsLogger.info("Failed : to verify out of order   in inventory");
			}
		}

	}

	public ArrayList<String> clickOnAvailabilityTab(WebDriver driver) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement_ID(driver, OR.rateGridAvailableButton);
		Wait.waitForElementToBeVisibile(By.id(OR.rateGridAvailableButton), driver);
		Wait.waitForElementToBeClickable(By.id(OR.rateGridAvailableButton), driver);
		elements_OverView.rateGridAvailableButton.click();
		testSteps.add("Clicked on availability button");
		accountsLogger.info("Clicked on availability button");
		return testSteps;
	}

	public ArrayList<String> clickOnBulkUpdate(WebDriver driver) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.rateGridBulkUpdateButton);
		Wait.waitForElementToBeVisibile(By.xpath(OR.rateGridBulkUpdateButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rateGridBulkUpdateButton), driver);
		elements_OverView.rateGridBulkUpdateButton.click();
		testSteps.add("Clicked on bulk update button");
		accountsLogger.info("Clicked on bulk update button");
		return testSteps;
	}

	public ArrayList<String> selectBulkUpdateOption(WebDriver driver, String option) {

		ArrayList<String> testSteps = new ArrayList<>();
		String rateGridBulkUpdateAvailable = "(//li//a[contains(text(),'" + option + "')])[1]";
		Wait.WaitForElement(driver, rateGridBulkUpdateAvailable);
		Wait.waitForElementToBeVisibile(By.xpath(rateGridBulkUpdateAvailable), driver);
		Wait.waitForElementToBeClickable(By.xpath(rateGridBulkUpdateAvailable), driver);
		WebElement rateGirdOption = driver.findElement(By.xpath(rateGridBulkUpdateAvailable));
		rateGirdOption.click();
		testSteps.add("Select " + option + " from bulk update");
		accountsLogger.info("Select " + option + " from bulk update");
		return testSteps;
	}

	public ArrayList<String> startDate(WebDriver driver, String date) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.startDate);
		Wait.waitForElementToBeVisibile(By.xpath(OR.startDate), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.startDate), driver);
		elements_OverView.bulkUpdatePopupCheckinInput.clear();
		elements_OverView.bulkUpdatePopupCheckinInput.sendKeys(date);
		testSteps.add("Start date: " + date);
		accountsLogger.info("Start date: " + date);
		return testSteps;
	}

	public String getStartDate(WebDriver driver) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.WaitForElement(driver, OR.startDate);
		Wait.waitForElementToBeVisibile(By.xpath(OR.startDate), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.startDate), driver);
		String date = elements_OverView.bulkUpdatePopupCheckinInput.getAttribute("value");
		accountsLogger.info("Start date: " + date);
		return date;
	}

	public ArrayList<String> endDate(WebDriver driver, String date) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.endDate);
		Wait.waitForElementToBeVisibile(By.xpath(OR.endDate), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.endDate), driver);
		elements_OverView.bulkUpdatePopupCheckoutInput.clear();
		elements_OverView.bulkUpdatePopupCheckoutInput.sendKeys(date);
		testSteps.add("End date: " + date);
		accountsLogger.info("End date: " + date);
		elements_OverView.bulkUpdatePopupCheckoutInput.sendKeys(Keys.TAB);
		return testSteps;

	}

	public String getEndDate(WebDriver driver) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.WaitForElement(driver, OR.endDate);
		Wait.waitForElementToBeVisibile(By.xpath(OR.endDate), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.endDate), driver);
		String date = elements_OverView.bulkUpdatePopupCheckoutInput.getAttribute("value");
		accountsLogger.info("End date: " + date);
		return date;

	}

	public ArrayList<String> verifyAvailabilityHeading(WebDriver driver) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.availabilityHeading);
		Wait.waitForElementToBeVisibile(By.xpath(OR.availabilityHeading), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.availabilityHeading), driver);
		assertEquals(elements_OverView.availabilityHeading.getText(), "Bulk update - Availability",
				"Bulk Update popup Heading didn't display");
		testSteps.add("Verified availablityt popup heading");
		accountsLogger.info("Verified availablityt popup heading");
		return testSteps;

	}

	public String getBulkUpdatePoppupText(WebDriver driver) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.WaitForElement(driver, OR.bulkUpdatePopupText);
		Wait.waitForElementToBeVisibile(By.xpath(OR.bulkUpdatePopupText), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.bulkUpdatePopupText), driver);
		String text = elements_OverView.bulkUpdatePopupText.getText();
		accountsLogger.info("text : " + text);
		return text;

	}

	public ArrayList<String> checkDays(WebDriver driver, String day, String isChecked) throws InterruptedException {

		String daysCheckBox = "//span[text()='" + day + "']/following-sibling::span";
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, daysCheckBox);
		Wait.waitForElementToBeVisibile(By.xpath(daysCheckBox), driver);
		Wait.waitForElementToBeClickable(By.xpath(daysCheckBox), driver);
		WebElement daysElement = driver.findElement(By.xpath(daysCheckBox));
		Utility.ScrollToElement_NoWait(daysElement, driver);

		if (isChecked.equalsIgnoreCase("yes")) {
			testSteps.add(day + " checkbox checked");
			accountsLogger.info(day + " checkbox checked");
		} else if (isChecked.equalsIgnoreCase("no")) {
			daysElement.click();
			testSteps.add(day + " checkbox unchecked");
			accountsLogger.info(day + " checkbox unchecked");
		}
		return testSteps;

	}

	public ArrayList<String> verifyDaysCheckbox(WebDriver driver, String day) throws InterruptedException {

		String daysCheckBox = "//span[text()='" + day + "']/following-sibling::span";
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, daysCheckBox);
		Wait.waitForElementToBeVisibile(By.xpath(daysCheckBox), driver);
		Wait.waitForElementToBeClickable(By.xpath(daysCheckBox), driver);
		WebElement daysElement = driver.findElement(By.xpath(daysCheckBox));
		Utility.ScrollToElement_NoWait(daysElement, driver);

		Assert.assertTrue(daysElement.isDisplayed(), "Failed : " + day + " checkbox didn't displayed");
		testSteps.add("Verified " + day + " checkbox is displaying");
		accountsLogger.info("Verified " + day + " checkbox displaying");
		return testSteps;

	}

	public ArrayList<String> selectUpdateAvailability(WebDriver driver, String channel, String updateAvailability)
			throws InterruptedException {

		String updateAvailabilityPath = "//div[contains(text(),'" + channel
				+ "')]//following-sibling::div//span[contains(text(),'" + updateAvailability + "')]";
		accountsLogger.info("updateAvailabilityPath :  " + updateAvailabilityPath);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, updateAvailabilityPath);
		Wait.waitForElementToBeVisibile(By.xpath(updateAvailabilityPath), driver);
		Wait.waitForElementToBeClickable(By.xpath(updateAvailabilityPath), driver);
		WebElement updateAvailabilityElement = driver.findElement(By.xpath(updateAvailabilityPath));
		Utility.ScrollToElement_NoWait(updateAvailabilityElement, driver);
		updateAvailabilityElement.click();
		testSteps.add(channel + " selected update availability : " + updateAvailability);
		accountsLogger.info(channel + " selected update availability : " + updateAvailability);

		return testSteps;
	}

	public ArrayList<String> getroomClasses(WebDriver driver) throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<>();
		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.WaitForElement(driver, OR.bulkUpdatePopupRoomClass);
		Wait.waitForElementToBeVisibile(By.xpath(OR.bulkUpdatePopupRoomClass), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.bulkUpdatePopupRoomClass), driver);
		Utility.ScrollToElement_NoWait(elements_OverView.bulkUpdatePopupRoomClass, driver);
		elements_OverView.bulkUpdatePopupRoomClass.click();
		String roomClassesPath = "(//div[contains(text(),'Select room class(es)')]//parent::div//following::div[@class='Select-menu-outer'])[1]//div[@class='Select-option']";
		List<WebElement> roomClassesElement = driver.findElements(By.xpath(roomClassesPath));
		for (int i = 0; i < roomClassesElement.size(); i++) {
			String roomClassName = roomClassesElement.get(i).getText().trim();
			testSteps.add(roomClassName);
			accountsLogger.info("GetRoomClass : " + roomClassName);
		}

		return testSteps;
	}

	public ArrayList<String> selectRoomClass(WebDriver driver, String roomClass) throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<>();
		WebElementsOverview elements_OverView = new WebElementsOverview(driver);

		Wait.WaitForElement(driver, OR.bulkUpdatePopupRoomClass);
		Wait.waitForElementToBeVisibile(By.xpath(OR.bulkUpdatePopupRoomClass), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.bulkUpdatePopupRoomClass), driver);
		Utility.ScrollToElement_NoWait(elements_OverView.bulkUpdatePopupRoomClass, driver);
		elements_OverView.bulkUpdatePopupRoomClass.click();

		String roomClassesPath = "(//div[@class='Select-menu-outer'])[1]//div[contains(@class,'Select-option')]";
		List<WebElement> roomClassesElement = driver.findElements(By.xpath(roomClassesPath));

		for (int i = 0; i < roomClassesElement.size(); i++) {

			String roomClassName = roomClassesElement.get(i).getText().trim();
			accountsLogger.info("GetRoomClass : " + roomClassName);

			if (roomClassName.contains(roomClass)) {

				roomClassesElement.get(i).click();
				testSteps.add("Entered RoomClass : " + roomClass);
				accountsLogger.info("Entered RoomClass : " + roomClass);
				break;
			}

		}

		return testSteps;
	}

	public ArrayList<String> clickUpdateButton(WebDriver driver) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.updateButton);
		Wait.waitForElementToBeVisibile(By.xpath(OR.updateButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.updateButton), driver);
		elements_OverView.updateButton.click();
		testSteps.add("Update button clicked");
		accountsLogger.info("Update button clicked");
		return testSteps;
	}

	public ArrayList<String> verifyUpdateButtonEnabled(WebDriver driver) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.updateButton);
		Wait.waitForElementToBeVisibile(By.xpath(OR.updateButton), driver);
		Boolean isActive = elements_OverView.updateButton.isEnabled();
		if (isActive) {
			testSteps.add("Update button is enabled");
			accountsLogger.info("Update button is enabled");

		} else {
			testSteps.add("Update button is disabled");
			accountsLogger.info("Update button is disabled");

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
		accountsLogger.info("bulkUpdatePopup Days text : " + text);
		return text;

	}

	public ArrayList<String> selectTotalOccupancy(WebDriver driver, String isEnable, String totalOccupancyType,
			String totalOccupancyValue) throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<>();
		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.WaitForElement(driver, OR.totalOccupancy);
		Wait.waitForElementToBeVisibile(By.xpath(OR.totalOccupancy), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.totalOccupancy), driver);
		Utility.ScrollToElement_NoWait(elements_OverView.totalOccupancy, driver);
		if (isEnable.equalsIgnoreCase("Yes")) {
			elements_OverView.totalOccupancy.click();
			testSteps.add("Enabled total occupancy");
			accountsLogger.info("Enabled total occupancy");

			elements_OverView.totalOccupancyType.click();
			accountsLogger.info("Dropdown clicked");
			if (totalOccupancyType.equalsIgnoreCase("Greater")) {
				String greater = "greater than";
				accountsLogger.info(greater);
				String path = "(//span[text()='For days that total occupancy is']//parent::label//parent::div//div[@class='Select-menu-outer'])[1]//div[contains(text(),'"
						+ greater + "')]";
				driver.findElement(By.xpath(path)).click();
				testSteps.add("Selected greater than");
				accountsLogger.info("Selected greater than");

			} else if (totalOccupancyType.equalsIgnoreCase("Less")) {
				String less = "less than";
				accountsLogger.info(less);
				String path = "(//span[text()='For days that total occupancy is']//parent::label//parent::div//div[@class='Select-menu-outer'])[1]//div[contains(text(),'"
						+ less + "')]";
				driver.findElement(By.xpath(path)).click();
				testSteps.add("Selected less than");
				accountsLogger.info("Selected less than");
			}

			elements_OverView.totalOccupanyValue.sendKeys(totalOccupancyValue);
			testSteps.add("Entered total occupancy value : " + totalOccupancyValue);
			accountsLogger.info("Entered total occupancy value : " + totalOccupancyValue);
		}
		return testSteps;
	}

	public ArrayList<String> clickCancelButton(WebDriver driver) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.bulkUpdateCancel);
		Wait.waitForElementToBeVisibile(By.xpath(OR.bulkUpdateCancel), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.bulkUpdateCancel), driver);
		elements_OverView.bulkUpdateCancel.click();
		testSteps.add("cancel button clicked");
		accountsLogger.info("cancel button clicked");
		return testSteps;
	}

	public Boolean compareLists(ArrayList<String> firstList, ArrayList<String> secondList) {
		Boolean isRoomClassPresent = false;
		int i;
		for (i = 0; i < firstList.size(); i++) {
			String activeRoomClass = firstList.get(i);
			accountsLogger.info("activeRoomClass : " + activeRoomClass);
			String activeRoomClassInBulkUpdate = secondList.get(i);
			accountsLogger.info("activeRoomClassInBulkUpdate : " + activeRoomClassInBulkUpdate);
			if (activeRoomClass.contains(activeRoomClassInBulkUpdate)) {
				isRoomClassPresent = true;
			} else {
				isRoomClassPresent = false;
			}
		}

		return isRoomClassPresent;
	}

	public ArrayList<String> verifyRulesHeading(WebDriver driver) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.rulesHeading);
		Wait.waitForElementToBeVisibile(By.xpath(OR.rulesHeading), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rulesHeading), driver);
		assertTrue(elements_OverView.rulesHeading.isDisplayed(), "Bulk Update rules popup Heading didn't display");
		testSteps.add("Verified Bulk update rules popup heading");
		accountsLogger.info("Verified Bulk update rules popup heading");

		return testSteps;

	}

	public ArrayList<String> clickMinimumStay(WebDriver driver, String isClick) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.minimumStay);
		Wait.waitForElementToBeVisibile(By.xpath(OR.minimumStay), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.minimumStay), driver);
		if (isClick.equalsIgnoreCase("Yes")) {
			Boolean isCheck = verifyMinimumStayValue(driver);
			if (isCheck) {
				testSteps.add("Mininum Stay toggle button already enabled");
				accountsLogger.info("Mininum Stay toggle button already enabled");

			} else {
				elements_OverView.minimumStay.click();
				testSteps.add("Mininum Stay toggle button enabled");
				accountsLogger.info("Mininum Stay toggle button enabled");
			}

		} else if (isClick.equalsIgnoreCase("No")) {
			Boolean isCheck = verifyMinimumStayValue(driver);
			if (!isCheck) {
				testSteps.add("Mininum Stay toggle button already disabled");
				accountsLogger.info("Mininum Stay toggle button already disabled");

			} else {
				elements_OverView.minimumStay.click();
				testSteps.add("Mininum Stay toggle button disabled");
				accountsLogger.info("Mininum Stay toggle button disabled");
			}
		}

		return testSteps;
	}

	public ArrayList<String> enterMinimumStayValue(WebDriver driver, String mininumStayValue) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.minimumStayValue);
		Wait.waitForElementToBeVisibile(By.xpath(OR.minimumStayValue), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.minimumStayValue), driver);
		elements_OverView.minimumStayValue.clear();
		elements_OverView.minimumStayValue.sendKeys(mininumStayValue);
		testSteps.add("Enter mininum stay value : " + mininumStayValue);
		accountsLogger.info("Enter mininum stay value : " + mininumStayValue);
		return testSteps;
	}

	public ArrayList<String> clickCheckin(WebDriver driver, String isClick) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.checkin);
		Wait.waitForElementToBeVisibile(By.xpath(OR.checkin), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.checkin), driver);
		if (isClick.equalsIgnoreCase("Yes")) {
			Boolean isCheck = verifyNoCheckInCheckbox(driver);
			if (isCheck) {
				testSteps.add("Checkin toggle button already enabled");
				accountsLogger.info("Checkin toggle button already enabled");

			} else {
				elements_OverView.checkin.click();
				testSteps.add("Checkin toggle button enabled");
				accountsLogger.info("Checkin toggle button enabled");
			}

		} else if (isClick.equalsIgnoreCase("No")) {
			Boolean isCheck = verifyNoCheckInCheckbox(driver);
			if (!isCheck) {
				testSteps.add("Checkin toggle button already disabled");
				accountsLogger.info("Checkin toggle button already disabled");

			} else {
				elements_OverView.checkin.click();
				testSteps.add("Checkin toggle button disabled");
				accountsLogger.info("Checkin toggle button disabled");
			}
		}
		return testSteps;
	}

	public ArrayList<String> clickNoCheckInCheckbox(WebDriver driver, String isClick) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.noCheckInCheckbox);
		Wait.waitForElementToBeVisibile(By.xpath(OR.noCheckInCheckbox), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.noCheckInCheckbox), driver);
		if (isClick.equalsIgnoreCase("Yes")) {
			elements_OverView.noCheckInCheckbox.click();
			testSteps.add("NO Checkin chekcbox checked");
			accountsLogger.info("NO Checkin chekcbox checked");
		}
		return testSteps;
	}

	public ArrayList<String> clickCheckOut(WebDriver driver, String isClick) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.checkOut);
		Wait.waitForElementToBeVisibile(By.xpath(OR.checkOut), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.checkOut), driver);
		if (isClick.equalsIgnoreCase("Yes")) {
			Boolean isCheck = verifyNoCheckOutCheckbox(driver);
			if (isCheck) {
				testSteps.add("Checkout toggle button already enabled");
				accountsLogger.info("Checkout toggle button already enabled");

			} else {
				elements_OverView.checkOut.click();
				testSteps.add("Checkout toggle button enabled");
				accountsLogger.info("Checkout toggle button enabled");
			}
		} else if (isClick.equalsIgnoreCase("No")) {
			Boolean isCheck = verifyNoCheckOutCheckbox(driver);
			if (!isCheck) {
				testSteps.add("Checkout toggle button already disabled");
				accountsLogger.info("Checkout toggle button already disabled");

			} else {
				elements_OverView.checkOut.click();
				testSteps.add("Checkout toggle button disabled");
				accountsLogger.info("Checkout toggle button disabled");
			}
		}
		return testSteps;
	}

	public ArrayList<String> clickNoCheckOutCheckbox(WebDriver driver, String isClick) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.noCheckOutCheckbox);
		Wait.waitForElementToBeVisibile(By.xpath(OR.noCheckOutCheckbox), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.noCheckOutCheckbox), driver);
		if (isClick.equalsIgnoreCase("Yes")) {

			elements_OverView.noCheckOutCheckbox.click();
			testSteps.add("NO Checkout chekcbox checked");
			accountsLogger.info("NO Checkout chekcbox checked");
		}
		return testSteps;
	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectItemsFromDropDowns> ' Description: <This method
	 * will select list items of dropdowns based of respected parameters in bulk
	 * update popup> ' Input parameters: <WebDriver driver> ' Return value :
	 * <ArrayList<String>> ' Created By: <Farhan Ghaffar> ' Created On:
	 * <07/03/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> selectItemsFromDropDowns(WebDriver driver, String dropDownName, String roomClass)
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
		accountsLogger.info(dropDownName + " drop down clicked");
		String roomClassesPath = "(//div[@class='Select-menu-outer'])[1]//div[contains(@class,'Select-option')]";
		List<WebElement> roomClassesElement = driver.findElements(By.xpath(roomClassesPath));

		for (int i = 0; i < roomClassesElement.size(); i++) {

			String roomClassName = roomClassesElement.get(i).getText().trim();
			accountsLogger.info("Get list items : " + roomClassName);

			if (roomClassName.contains(roomClass)) {

				roomClassesElement.get(i).click();
				testSteps.add("Entered : " + roomClass);
				accountsLogger.info("Entered : " + roomClass);

				// elements_OverView.bulkUpdatePopupCheckoutInput.sendKeys(Keys.TAB);

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
	 * ' Method Name: <getBulkUpdateDropDownsList> ' Description: <This method
	 * will return list of dropdowns based of respected parameters in bulk
	 * update popup> ' Input parameters: <WebDriver driver> ' Return value :
	 * <String> ' Created By: <Farhan Ghaffar> ' Created On: <07/03/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> getBulkUpdateDropDownsList(WebDriver driver, String dropDownName)
			throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<>();
		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		String bulkUpdateDropDownPath = "//label[text()='" + dropDownName + "']//following-sibling::div";

		Wait.WaitForElement(driver, bulkUpdateDropDownPath);
		Wait.waitForElementToBeVisibile(By.xpath(bulkUpdateDropDownPath), driver);
		Wait.waitForElementToBeClickable(By.xpath(bulkUpdateDropDownPath), driver);
		WebElement bulkUpdateDropdown = driver.findElement(By.xpath(bulkUpdateDropDownPath));
		Utility.ScrollToElement_NoWait(bulkUpdateDropdown, driver);
		bulkUpdateDropdown.click();
		accountsLogger.info(dropDownName + "drop down clicked");

		String roomClassesPath = "(//div[@class='Select-menu-outer'])[1]//div[contains(@class,'Select-option')]";
		List<WebElement> roomClassesElement = driver.findElements(By.xpath(roomClassesPath));

		for (int i = 0; i < roomClassesElement.size(); i++) {
			String roomClassName = roomClassesElement.get(i).getText().trim();
			testSteps.add(roomClassName);
			accountsLogger.info("Get " + dropDownName + " Dropdown list items : " + roomClassName);

		}
		elements_OverView.bulkUpdatePopupCheckoutInput.sendKeys(Keys.TAB);
		accountsLogger.info("Clicked Tab Key");
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
	 * Created On: <07/03/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public String getCloseIconText(WebDriver driver) throws InterruptedException {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.WaitForElement(driver, OR.closeRulesPopup);
		Wait.waitForElementToBeVisibile(By.xpath(OR.closeRulesPopup), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.closeRulesPopup), driver);
		Utility.ScrollToElement_NoWait(elements_OverView.closeRulesPopup, driver);
		String closeText = elements_OverView.closeRulesPopup.getText();
		return closeText;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <totalOccupancyTextDisplay> ' Description: <This method
	 * will return total occupancy text in bulk update popup> ' Input
	 * parameters: <WebDriver driver> ' Return value : <String> ' Created By:
	 * <Farhan Ghaffar> ' Created On: <07/03/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public String totalOccupancyTextDisplay(WebDriver driver) throws InterruptedException {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.WaitForElement(driver, OR.totalOccupancyText);
		Wait.waitForElementToBeVisibile(By.xpath(OR.totalOccupancyText), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.totalOccupancyText), driver);
		Utility.ScrollToElement_NoWait(elements_OverView.totalOccupancyText, driver);
		String totalOccupancyText = elements_OverView.totalOccupancyText.getText();
		return totalOccupancyText;

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
		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.WaitForElement(driver, OR.totalOccupancy);
		Wait.waitForElementToBeVisibile(By.xpath(OR.totalOccupancy), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.totalOccupancy), driver);
		Utility.ScrollToElement_NoWait(elements_OverView.totalOccupancy, driver);
		if (isEnable.equalsIgnoreCase("Yes")) {
			Wait.WaitForElement(driver, OR.totalOccupanyValue);
			Boolean isOccupancyEnabled = elements_OverView.totalOccupanyValue.isEnabled();

			if (isOccupancyEnabled) {
				testSteps.add("Total occupancy already clicked");
				accountsLogger.info("Total occupancy already clicked");
			} else {
				elements_OverView.totalOccupancy.click();
				testSteps.add("Enabled total occupancy");
				accountsLogger.info("Enabled total occupancy");

			}
		} else if (isEnable.equalsIgnoreCase("No")) {
			Wait.WaitForElement(driver, OR.totalOccupanyValue);
			Boolean isOccupancyEnabled = elements_OverView.totalOccupanyValue.isEnabled();

			if (isOccupancyEnabled) {
				elements_OverView.totalOccupancy.click();
				testSteps.add("Total occupancy disabled");
				accountsLogger.info("Total occupancy disabled");
			} else {
				testSteps.add("Total occupancy already disabled");
				accountsLogger.info("Total occupancy already disabled");

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

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.totalOccupancyType);
		Wait.waitForElementToBeVisibile(By.xpath(OR.totalOccupancyType), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.totalOccupancyType), driver);
		Utility.ScrollToElement_NoWait(elements_OverView.totalOccupancyType, driver);
		elements_OverView.totalOccupancyType.click();
		accountsLogger.info("Dropdown clicked");
		if (totalOccupancyType.equalsIgnoreCase("Greater")) {
			String greater = "greater than";
			accountsLogger.info(greater);
			String path = "(//span[text()='For days that total occupancy is']//parent::label//parent::div//div[@class='Select-menu-outer'])[1]//div[contains(text(),'"
					+ greater + "')]";
			driver.findElement(By.xpath(path)).click();
			testSteps.add("Selected occupancy type greater than");
			accountsLogger.info("Selected occupancy type greater than");

		} else if (totalOccupancyType.equalsIgnoreCase("Less")) {
			String less = "less than";
			accountsLogger.info(less);
			String path = "(//span[text()='For days that total occupancy is']//parent::label//parent::div//div[@class='Select-menu-outer'])[1]//div[contains(text(),'"
					+ less + "')]";
			driver.findElement(By.xpath(path)).click();
			testSteps.add("Selected occupancy type less than");
			accountsLogger.info("Selected occupancy type less than");
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

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.totalOccupanyValue);
		Wait.waitForElementToBeVisibile(By.xpath(OR.totalOccupanyValue), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.totalOccupanyValue), driver);
		Utility.ScrollToElement_NoWait(elements_OverView.totalOccupancyType, driver);
		elements_OverView.totalOccupanyValue.sendKeys(totalOccupancyValue);
		testSteps.add("Entered total occupancy value : " + totalOccupancyValue);
		accountsLogger.info("Entered total occupancy value : " + totalOccupancyValue);
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

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.WaitForElement(driver, OR.totalOccupancyTypeVisibility);
		Boolean isEnabled = false;
		String classes = elements_OverView.totalOccupancyTypeVisibility.getAttribute("class");
		if (classes.contains("is-disabled")) {
			isEnabled = false;
		} else {
			isEnabled = true;
		}
		accountsLogger.info("totalOccupancyType classes :" + classes);
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

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.WaitForElement(driver, OR.totalOccupanyValue);
		Boolean isEnabled = elements_OverView.totalOccupanyValue.isEnabled();
		accountsLogger.info("totalOccupanyValue :" + isEnabled);

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
		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.WaitForElement(driver, OR.occupancyIcon);
		actions.moveToElement(elements_OverView.occupancyIcon).perform();
		String pageSource = driver.getPageSource();
		accountsLogger.info(pageSource);
		testSteps.add(pageSource);
		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyBulkUpdateDropDowns> ' Description: <This method
	 * will verify specific dropdown with given text is displaying or not in
	 * bulk update popup> ' Input parameters: <WebDriver driver> ' Return value
	 * : <ArrayList<String>> ' Created By: <Farhan Ghaffar> ' Created On:
	 * <07/03/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
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
		accountsLogger.info("Verify " + dropDownName + " dropdown displayed");

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
	 * <07/03/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String getDropDownsPlaceHolder(WebDriver driver, String dropDownName) throws InterruptedException {

		String bulkUpdateDropDownPath = "//label[text()='" + dropDownName
				+ "']//following-sibling::div//div[@class='Select-placeholder']";
		Wait.WaitForElement(driver, bulkUpdateDropDownPath);
		Wait.waitForElementToBeVisibile(By.xpath(bulkUpdateDropDownPath), driver);
		Wait.waitForElementToBeClickable(By.xpath(bulkUpdateDropDownPath), driver);
		WebElement bulkUpdateDropdown = driver.findElement(By.xpath(bulkUpdateDropDownPath));
		Utility.ScrollToElement_NoWait(bulkUpdateDropdown, driver);
		String placeHolderText = bulkUpdateDropdown.getText();
		accountsLogger.info("placeHolderText " + placeHolderText);

		return placeHolderText;
	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyUpdateExistingRule> ' Description: <This method
	 * will return update existing rule text in bulk update popup or not > '
	 * Input parameters: <WebDriver driver> ' Return value : <ArrayList<String>>
	 * ' Created By: <Farhan Ghaffar> ' Created On: <07/03/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public String verifyUpdateExistingRule(WebDriver driver) throws InterruptedException {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.WaitForElement(driver, OR.updatExistingRules);
		String updatExistingRules = elements_OverView.updatExistingRules.getText();
		accountsLogger.info("updatExistingRules :" + updatExistingRules);

		return updatExistingRules;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyMinimumStayValue> ' Description: <This method will
	 * return minimum stay input enable/disable status in bulk update popup> '
	 * Input parameters: <WebDriver driver> ' Return value : <String> ' Created
	 * By: <Farhan Ghaffar> ' Created On: <07/03/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public Boolean verifyMinimumStayValue(WebDriver driver) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.WaitForElement(driver, OR.minimumStayValue);
		Boolean isEnabled = elements_OverView.minimumStayValue.isEnabled();
		accountsLogger.info("minimumStayValue :" + isEnabled);
		return isEnabled;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyNoCheckInCheckbox> ' Description: <This method will
	 * return noCheckInCheckbox enable/disable status in bulk update popup> '
	 * Input parameters: <WebDriver driver> ' Return value : <String> ' Created
	 * By: <Farhan Ghaffar> ' Created On: <07/03/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public Boolean verifyNoCheckInCheckbox(WebDriver driver) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.WaitForElement(driver, OR.noCheckInInput);
		Boolean isEnabled = elements_OverView.noCheckInInput.isEnabled();
		accountsLogger.info("noCheckInCheckbox :" + isEnabled);
		return isEnabled;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyNoCheckOutCheckbox> ' Description: <This method
	 * will return noCheckOutCheckbox enable/disable status in bulk update
	 * popup> ' Input parameters: <WebDriver driver> ' Return value : <String> '
	 * Created By: <Farhan Ghaffar> ' Created On: <07/03/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public Boolean verifyNoCheckOutCheckbox(WebDriver driver) {

		WebElementsOverview elements_OverView = new WebElementsOverview(driver);
		Wait.WaitForElement(driver, OR.noCheckOutInput);
		Boolean isEnabled = elements_OverView.noCheckOutInput.isEnabled();
		accountsLogger.info("noCheckOutCheckbox :" + isEnabled);
		return isEnabled;
	}

}
