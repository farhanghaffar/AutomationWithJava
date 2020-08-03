package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.innroad.inncenter.interfaces.ITapeChart;
import com.innroad.inncenter.pages.NewTapeChart;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Reservation;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_CPReservation;
import com.innroad.inncenter.webelements.Elements_Reservation;
import com.innroad.inncenter.webelements.Elements_TapeChart;
import com.relevantcodes.extentreports.ExtentTest;

public class Tapechart implements ITapeChart {

	public static Logger tapechartLogger = Logger.getLogger("TapChart");

	public static String RoomNumber;
	public static String unassignedResCountTapechart;
	public static String RoomClassName;
	public static String availableRooms;
	public static String roomsAvailableAfterCreatingRes;
	public static String unassignedResCountTapechartAfterCreatingRes;
	public static String unassignedGuestNameTapechart;
	public static String row;
	public static String roomsAvailableAfterCreatingQuoteRes;
	ArrayList<String> test_steps = new ArrayList<>();

	public void TapChartLink(WebDriver driver) {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);

		TapeChart.Tape_Chart.click();
		Wait.explicit_wait_xpath(OR.Select_Arrival_Date, driver);

	}

	public void tapeChartSearch(WebDriver driver, String TapeChartAdults, String TapeChartChildrens, String PromoCode)
			throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.WaitForElement(driver, OR.Select_Arrival_Date);
		Wait.explicit_wait_elementToBeClickable(TapeChart.Select_Arrival_Date, driver);
		Utility.ScrollToElement(TapeChart.Select_Arrival_Date, driver);
		TapeChart.Select_Arrival_Date.click();
		TapeChart.Click_Today.click();
		Wait.wait2Second();
		TapeChart.Enter_Adults_Tapehchart.clear();
		TapeChart.Enter_Children_Tapechart.clear();
		TapeChart.Enter_Adults_Tapehchart.sendKeys(TapeChartAdults);
		TapeChart.Enter_Children_Tapechart.sendKeys(TapeChartChildrens);
		TapeChart.Enter_promoCode_Tapechart.clear();
		TapeChart.Enter_promoCode_Tapechart.sendKeys(PromoCode);
		TapeChart.Click_Search_TapeChart.click();
		tapechartLogger.info("Click Search");
		try {
			Wait.explicit_wait_visibilityof_webelement(TapeChart.TapeChartAvailableSlot, driver);
		} catch (Exception e) {
			TapeChart.Click_Search_TapeChart.click();
			tapechartLogger.info("Again Click Search");
			// Wait.explicit_wait_visibilityof_webelement(TapeChart.TapeChartAvailableSlot,driver);
		}
	}

	public void TapeChartSearch(WebDriver driver, String TapeChartAdults) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Select_Arrival_Date, driver);
		TapeChart.Select_Arrival_Date.click();
		TapeChart.Click_Today.click();
		Wait.wait2Second();
		TapeChart.Enter_Adults_Tapehchart.clear();
		TapeChart.Enter_Adults_Tapehchart.sendKeys(TapeChartAdults);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", TapeChart.Click_Search_TapeChart);

		/*
		 * try {
		 * Wait.explicit_wait_visibilityof_webelement(TapeChart.Rule_Broken.get(
		 * 0), driver); } catch (Exception e) {
		 * TapeChart.Click_Search_TapeChart.click();
		 * Wait.explicit_wait_visibilityof_webelement(TapeChart.Rule_Broken.get(
		 * 0), driver);
		 * 
		 * }
		 */
	}

	public void searchNewQuoteReservationInTapechart(WebDriver driver) throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);

		String quoteResGuestName = "//div[contains(text(), '" + Reservation.nameGuest + "')]";

		if (driver.findElement(By.xpath(quoteResGuestName)).getText().contains(Reservation.nameGuest)) {

			roomsAvailableAfterCreatingQuoteRes = driver
					.findElement(By.xpath("(//div[contains(text(), '" + Reservation.nameGuest
							+ "')]//following::div[contains(text(),'# Rooms Available')])[1]/ ../div[2]/div[2]"))
					.getText();
			tapechartLogger.info("Rooms Available After Creating Reservation: " + roomsAvailableAfterCreatingQuoteRes);
		}

		Wait.wait5Second();

	}

	public void availableRoomsValidations(WebDriver driver, String quoteResAvailableRooms, ExtentTest test) {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);

		int quoteResRoomsAvailable = Integer.parseInt(quoteResAvailableRooms) - 1;

		int availableRoomsAfterCreatingQuoteRes = Integer.parseInt(roomsAvailableAfterCreatingQuoteRes);
		test_steps.add("Available Rooms in Tapechart " + quoteResRoomsAvailable);
		if (availableRoomsAfterCreatingQuoteRes == quoteResRoomsAvailable) {

			tapechartLogger.info("Successfully Validated Rooms available");

		}
	}

	public void TapeChartSearch_GivenDate(WebDriver driver, String TapeChartAdults, String TapeChartChildrens,
			String PromoCode) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Select_Arrival_Date, driver);
		TapeChart.Select_Arrival_Date.click();
		TapeChart.Click_Today.click();
		Wait.wait2Second();
		TapeChart.Enter_Adults_Tapehchart.clear();
		TapeChart.Enter_Children_Tapechart.clear();
		TapeChart.Enter_Adults_Tapehchart.sendKeys(TapeChartAdults);
		TapeChart.Enter_Children_Tapechart.sendKeys(TapeChartChildrens);
		TapeChart.Enter_promoCode_Tapechart.clear();
		TapeChart.Enter_promoCode_Tapechart.sendKeys(PromoCode);
		TapeChart.Click_Search_TapeChart.click();
		Wait.wait2Second();

	}

	public void TapeChartSearch_RatePlan(WebDriver driver, String TapeChartAdults, String TapeChartChildrens,
			String PromoCode) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Select_Arrival_Date, driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.Select_Arrival_Date, driver);
		Utility.ScrollToElement(TapeChart.Select_Arrival_Date, driver);
		TapeChart.Select_Arrival_Date.click();
		TapeChart.Click_Today.click();
		Wait.wait2Second();
		TapeChart.Enter_Adults_Tapehchart.clear();
		TapeChart.Enter_Adults_Tapehchart.sendKeys(TapeChartAdults);
		assertTrue(TapeChart.Enter_Adults_Tapehchart.getAttribute("value").contains(TapeChartAdults),
				"Failed: Adult set");
		TapeChart.Enter_Children_Tapechart.clear();
		TapeChart.Enter_Children_Tapechart.sendKeys(TapeChartChildrens);
		assertTrue(TapeChart.Enter_Children_Tapechart.getAttribute("value").contains(TapeChartChildrens),
				"Failed: Children set");
		Wait.explicit_wait_xpath(OR.Click_Tapechart_RackRate, driver);
		TapeChart.Click_Tapechart_RackRate.click();
		Wait.explicit_wait_xpath(OR.Select_Rack_Rate, driver);
		TapeChart.Select_Rack_Rate.click();
		Wait.wait2Second();
		TapeChart.Enter_promoCode_Tapechart.sendKeys(PromoCode);
		assertTrue(TapeChart.Enter_promoCode_Tapechart.getAttribute("value").contains(PromoCode),
				"Failed: RomoCode Set");
		TapeChart.Click_Search_TapeChart.click();
		tapechartLogger.info("Click Search");
		try {
			Wait.explicit_wait_visibilityof_webelement_120(TapeChart.TapeChartAvailableSlot, driver);
		} catch (Exception e) {
			TapeChart.Click_Search_TapeChart.click();
			tapechartLogger.info("Again Click Search");
			Wait.explicit_wait_visibilityof_webelement_120(TapeChart.TapeChartAvailableSlot, driver);
		}

	}

	public void TapeChartSearch_NextDate(WebDriver driver, String TapeChartAdults, String TapeChartChildrens,
			String PromoCode) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		driver.navigate().refresh();
		Utility.app_logs.info("Refresh Page");
		try {
			Wait.explicit_wait_visibilityof_webelement_150(TapeChart.Select_Arrival_Date, driver);
		} catch (Exception e) {
			Utility.app_logs.info("In catch");
			Navigation nav = new Navigation();
			nav.TapeChart(driver);
			Utility.app_logs.info("Click TapeChart");
			Wait.explicit_wait_visibilityof_webelement_350(TapeChart.Select_Arrival_Date, driver);
		}
		TapeChart.Select_Arrival_Date.click();
		Wait.wait2Second();
		String ActiveDate = TapeChart.GetActiveDate.getText();
		int currentDate = Integer.parseInt(ActiveDate);
		currentDate = currentDate + 1;
		String SelectDate = String.valueOf(currentDate);

		try {
			System.out.println(
					"(//div[@class='datepicker-days']//table/tbody/tr/td[@class='today day']//following-sibling::td[@class!='old day'])[1]");
			driver.findElements(By
					.xpath("(//div[@class='datepicker-days']//table/tbody/tr/td[@class='today day']//following-sibling::td[@class!='old day'])[1]"))
					.get(0).click();

		} catch (Exception e) {
			Utility.app_logs.info("Today is saturday");
			String Path = "(//div[@class='datepicker-days']//table/tbody/tr/td[@class='today day']//parent::tr//following-sibling::tr//td[@class!='old day'])[1]";
			System.out.println(Path);
			driver.findElement(By.xpath(Path)).click();
		}
		Wait.wait2Second();

		// Wait.explicit_wait_xpath(OR.Click_Tomorrow);
		// TapeChart.Click_Tomorrow.click();
		Wait.wait2Second();
		TapeChart.Enter_Adults_Tapehchart.clear();
		TapeChart.Enter_Adults_Tapehchart.sendKeys(TapeChartAdults);
		assertTrue(TapeChart.Enter_Adults_Tapehchart.getAttribute("value").contains(TapeChartAdults),
				"Failed: Adult set");
		TapeChart.Enter_Children_Tapechart.clear();
		TapeChart.Enter_Children_Tapechart.sendKeys(TapeChartChildrens);
		assertTrue(TapeChart.Enter_Children_Tapechart.getAttribute("value").contains(TapeChartChildrens),
				"Failed: Children set");
		Wait.explicit_wait_xpath(OR.Click_Tapechart_RackRate, driver);
		TapeChart.Click_Tapechart_RackRate.click();
		Wait.explicit_wait_xpath(OR.Select_Rack_Rate, driver);
		TapeChart.Select_Rack_Rate.click();
		Wait.wait2Second();
		TapeChart.Enter_promoCode_Tapechart.sendKeys(PromoCode);
		assertTrue(TapeChart.Enter_promoCode_Tapechart.getAttribute("value").contains(PromoCode),
				"Failed: RomoCode Set");
		TapeChart.Click_Search_TapeChart.click();
		tapechartLogger.info("Click Search");
		try {
			Wait.explicit_wait_visibilityof_webelement(TapeChart.TapeChartAvailableSlot, driver);
		} catch (Exception e) {
			TapeChart.Click_Search_TapeChart.click();
			tapechartLogger.info("Again Click Search");
			Wait.explicit_wait_visibilityof_webelement(TapeChart.TapeChartAvailableSlot, driver);
		}

	}

	public void VerifyRule(WebDriver driver) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", TapeChart.Rule_Broken.get(0));
		Wait.wait2Second();
		jse.executeScript("window.scrollBy(0,-300)");
		Wait.wait2Second();
		TapeChart.Rule_Broken.get(0).click();
		// jse.executeScript("arguments[0].click();",
		// TapeChart.Rule_Broken.get(0));
		// TapeChart.Rule_Broken.get(5).click();
		// Wait.wait2Second();
		Wait.explicit_wait_xpath(OR.Rule_Broken_PopUp, driver);
		Wait.wait2Second();
		assertEquals(TapeChart.Rule_Broken_PopUp.getText(), "Rules Broken", "Rule broken popup is not showing");
		TapeChart.Rule_Broken_Cancel.click();
		// Wait.wait2Second();
	}

	public void VerifyRule(WebDriver driver, String RoomClassAbb, String RuleName, String RuleDescription,
			boolean isBroken) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		String rate = "RackRate";
		if (isBroken) {
			rate = "NoRate";
		}
		String Path = "(//div[text()='" + RoomClassAbb
				+ "']//ancestor::div[@class='roomRatesChart']//div[contains(@class,'tapechartdatecell Available " + rate
				+ "')])[1]";

		WebElement Room = driver.findElement(By.xpath(Path));
		Wait.explicit_wait_visibilityof_webelement_350(Room, driver);
		Utility.ScrollToElement(Room, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-300)");
		Wait.wait2Second();

		if (isBroken) {
			Room.click();
			Wait.explicit_wait_visibilityof_webelement(TapeChart.Rule_Broken_PopUp, driver);
			assertEquals(TapeChart.Rule_Broken_PopUp.isDisplayed(), true, "Rule broken popup is not Displayed");
			assertEquals(TapeChart.Rule_Broken_PopUp.getText(), "Rules Broken", "Rule broken popup is not showing");
			String ruleName = driver
					.findElement(
							By.xpath("//*[@id='rulesBrokenConfirmation']//td[contains(@data-bind,'text: RuleName')]"))
					.getText();
			assertEquals(ruleName, RuleName, "Rule Not Verified, RuleName not matched");
			String ruleDesc = driver
					.findElement(By
							.xpath("//*[@id='rulesBrokenConfirmation']//td[contains(@data-bind,'text: RuleDescription')]"))
					.getText();
			assertEquals(ruleDesc, RuleDescription, "Rule Not Verified, Rule Desc not matched");
			TapeChart.Rule_Broken_Cancel.click();
		} else {
			try {
				Room.click();
				Wait.wait2Second();
				assertEquals(TapeChart.Rule_Broken_PopUp.isDisplayed(), false, "Rule broken popup is Displayed");

				Wait.explicit_wait_visibilityof_webelement(
						driver.findElement(By.xpath(OR.OpenedReservation_CloseButton)), driver);
				Utility.ScrollToElement(driver.findElement(By.xpath(OR.OpenedReservation_CloseButton)), driver);
				driver.findElement(By.xpath(OR.OpenedReservation_CloseButton)).click();

				driver.navigate().refresh();
			} catch (Exception e) {
				System.out.println("in catch");
				String available = driver.findElement(By.xpath(Path + "//div[@class='guest_display_name_short']"))
						.getText();
				assertEquals(available, "Available", "Failed Available not Found");
				;
			}
		}

		Wait.wait2Second();
	}

	public void clickAvailableRoomClass(WebDriver driver) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);

		TapeChart.Click_First_Available.click();
		Wait.wait3Second();
		if (TapeChart.Rules_Broken_popup.isDisplayed()) {
			Wait.explicit_wait_xpath(OR.Rules_Broken_popup, driver);

			TapeChart.Click_Book_icon_Tapechart.click();
		} else {
			System.out.println("Rules are not broken");
		}

	}

	public void click_Unassigned(WebDriver driver) throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		// WebElement ElementClick =
		// driver.findElement(By.xpath(OR.Click_Unassigned_Tapechart));

		/*
		 * Actions action = new Actions(driver);
		 * action.moveToElement(ElementClick).click(ElementClick).build().
		 * perform();
		 * action.moveToElement(ElementClick).click(ElementClick).build().
		 * perform();
		 */

		if (TestCore.targetBrowser.equalsIgnoreCase("firefox")) {

			WebElement ElementClick = driver.findElement(By.xpath(OR.Click_Unassigned_Tapechart));

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ElementClick);
			jse.executeScript("window.scrollBy(0,-400)", "");

			Actions action = new Actions(driver);
			action.moveToElement(ElementClick).click(ElementClick).build().perform();
			// action.moveToElement(ElementClick).click(ElementClick).build().perform();

		} else if (TestCore.targetBrowser.equalsIgnoreCase("chrome")) {

			WebElement ElementClick = driver.findElement(By.xpath(OR.Click_Unassigned_Tapechart));
			System.out.println("Chrome");
			Wait.WaitForElement(driver, OR.Click_Unassigned_Tapechart);
			Wait.explicit_wait_elementToBeClickable(ElementClick, driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ElementClick);
			jse.executeScript("window.scrollBy(0,-400)", "");
			ElementClick.click();
		}

		// TapeChart.Click_Unassigned_Tapechart.click();
		// Wait.explicit_wait_xpath(OR.New_Reservation_Tab);
		Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);
	}

	public void Verify_BlackOutRoom(WebDriver driver, String BlackoutRoom) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		TapeChart.ReservationsLink.click();
		Wait.wait2Second();
		try {
			TapeChart.TapeChart.click();
		} catch (Exception e) {
			TapeChart.Tape_Chart.click();
		}
		Wait.wait2Second();
		String blackout = TapeChart.BlackOutCell.get(0).getText();
		assertEquals(blackout, "B", "Value does not match");
		Wait.wait2Second();
		/*
		 * 
		 * Wait.wait2Second(); TapeChart.DatePickerIcon.get(5).click();
		 * Wait.wait2Second(); TapeChart.SelectDate.click();
		 * TapeChart.Quote_SearchButton.click(); Wait.wait2Second(); String
		 * BookButtonPath = "//a[text()='" + BlackoutRoom + <<<<<<< HEAD
		 * "']//ancestor::tr//following-sibling::button[@title='Book']";
		 * WebElement BookButton = driver.findElement(By.xpath(BookButtonPath));
		 * ======= "']//ancestor::tr//following-sibling::button[@title='Book']";
		 * WebElement BookButton = driver.findElement(By.xpath(BookButtonPath));
		 * >>>>>>> ScriptRefactoring_Vishali Utility.ScrollToElement(BookButton,
		 * driver); BookButton.click(); Wait.wait2Second();
		 * 
		 * assertEquals(TapeChart.BlackOutAlert.getText(), "Blackouts Alert!",
		 * "Blackout alert box does not display");
		 * 
		 * TapeChart.Blackout_OkButton.click(); Wait.wait2Second();
		 */

	}

	public void Verify_SyndicateRoom(WebDriver driver) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();
		String Syndicate = TapeChart.SyndicateCell.getText();
		assertEquals(Syndicate, "S", "Vlaue does not match");

	}

	public ArrayList<String> Verify_SyndicateRoom(WebDriver driver, String RoomClassAbb, String RoomNumber,
			ArrayList<String> TestSteps) throws InterruptedException {

		Elements_TapeChart elements_TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();
		String PathRoomCell = "//div[text()='" + RoomClassAbb
				+ "'  and contains(@class,'roomclassname')]//ancestor::div[@class='roomClasses']//child::div[@title='"
				+ RoomNumber
				+ "']//parent::div//child::div[@class='guest_display_name_short'and text()='S']//ancestor::div[contains(@class,'tapechartdatecell')]//child::div[@class='DatesContainer']";

		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String Path = "//div[text()='" + RoomClassAbb + "' ]//ancestor::div[@class='roomClasses']//child::div[@title='"
				+ RoomNumber
				+ "']//following-sibling::div//span//div[contains(@class,'Syndicate')]//div//div//div//div[text()='S']";
		WebElement room = driver.findElement(By.xpath(Path));
		Wait.explicit_wait_visibilityof_webelement(room, driver);
		Utility.ScrollToElement(room, driver);
		jse.executeScript("window.scrollBy(0,-300)");
		Wait.wait2Second();
		assertEquals(room.getText(), "S", "Syndicate room is not showing in tape chart");
		jse.executeScript("arguments[0].click();", room);

		WebElement elementRoomCell = driver.findElement(By.xpath(PathRoomCell));
		try {
			System.out.println("in try");
			elementRoomCell.click();
			Wait.wait1Second();
		} catch (Exception e) {
			System.out.println("in catch");
			jse.executeScript("arguments[0].click();", elementRoomCell);
		}
		if (elements_TapeChart.ListRuleBrokenPopUp.size() > 0 && elements_TapeChart.RuleBrokenBook_Btn.isDisplayed()) {
			elements_TapeChart.RuleBrokenBook_Btn.click();

		}
		TestSteps.add("Click syndicate room from tapechart");
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(OR.ReservationDetailPage)), driver);

		return TestSteps;
	}

	public void Verify_RoomClass(WebDriver driver, String RoomClass) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		System.out.println("ROOMCLS:" + RoomClass);
		String RoomClassPath = "//div[.='" + RoomClass + "']";
		WebElement RoomClassAbb = driver.findElement(By.xpath(RoomClassPath));
		Wait.explicit_wait_visibilityof_webelement(RoomClassAbb, driver);
		jse.executeScript("arguments[0].scrollIntoView(true);", RoomClassAbb);
		String RoomClass_Name = RoomClassAbb.getText();
		assertEquals(RoomClass_Name, RoomClass, "Room class does not find");

	}

	public void Verify_RoomClass1(WebDriver driver, String RoomClass) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement RoomClassName = driver.findElement(By.xpath("//div[.='" + RoomClass + "']"));
		Wait.explicit_wait_visibilityof_webelement(RoomClassName, driver);
		jse.executeScript("arguments[0].scrollIntoView(true);", RoomClassName);
		String RoomClass_Name = RoomClassName.getText();
		System.out.println("RoomClass1" + RoomClassName + "Roomclass2" + RoomClass_Name);
		assertEquals(RoomClass_Name, RoomClass, "Room class does not find");

	}

	public void Verify_RoomMaintenance(WebDriver driver, String RoomNumber) throws InterruptedException {

		String path = "//div[@title='" + RoomNumber
				+ "']//following-sibling::div//span/div/div/div/div/div[text()='Out']";
		WebElement find_room = driver.findElement(By.xpath(path));

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Utility.ScrollToElement(find_room, driver);
		Wait.wait2Second();
		jse.executeScript("window.scrollBy(0,-300)");
		Wait.wait2Second();
		assertEquals(find_room.getText(), "Out", "Out of order room does not find in tapechart");

		driver.navigate().refresh();

	}

	public void CreateReservation_RoomMaintenance(WebDriver driver, String RoomNumber) throws InterruptedException {

		String path = "//div[@title='" + RoomNumber
				+ "']//following-sibling::div//span/div/div/div/div/div[text()='Available']";
		WebElement find_room = driver.findElement(By.xpath(path));
		Wait.explicit_wait_visibilityof_webelement(find_room, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", find_room);
		Wait.explicit_wait_visibilityof_webelement(find_room, driver);
		jse.executeScript("window.scrollBy(0,-300)");
		Wait.explicit_wait_visibilityof_webelement(find_room, driver);
		assertEquals(find_room.getText(), "Available", "Available room does not find in tapechart");

	}

	public void VeirfyUnAssignedList(WebDriver driver) throws InterruptedException, ParseException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		TapeChart.Unassigned_Button.click();

		int Unassigned_ListSize = driver.findElements(By.xpath(OR.UnassignedList)).size();
		int StartDate = 1;
		int EndDate = 3;
		ArrayList<String> startDate = new ArrayList<>();

		for (int i = 0; i < Unassigned_ListSize; i++) {
			try {
				startDate.add(TapeChart.UnsignedList_Date.get(StartDate).getText());
			} catch (Exception e) {

				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].scrollIntoView(true);", TapeChart.UnsignedList_Date.get(StartDate));
				Wait.wait2Second();
				TapeChart.UnsignedList_Date.get(StartDate).getText();
			}
			StartDate = StartDate + 6;
			EndDate = EndDate + 6;

		}
		boolean ascendingOrder = false;
		DateFormat df = new SimpleDateFormat("E, MMM dd yyyy");
		for (int i = 0; i < startDate.size(); i++) {
			for (int j = i; j < startDate.size(); j++) {
				Date res1 = df.parse(startDate.get(i).toString() + " 2018");
				Date res2 = df.parse(startDate.get(j).toString() + " 2018");
				if (res1.equals(res2) || res1.before(res2)) {
					ascendingOrder = true;
				}
				assertEquals(ascendingOrder, true, "Unsigned list is not Ascending Order");
			}

		}

	}

	public void EarlyCheckout(WebDriver driver, String TapeChartAdults, String TapeChartChildrens, String PromoCode)
			throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Select_Arrival_Date, driver);
		TapeChart.Select_Arrival_Date.click();
		TapeChart.Click_Today.click();
		/*
		 * String GetTodayDate = TapeChart.GetTodayDate.getText(); if
		 * (GetTodayDate.equals("1")){ String DatePickerPath =
		 * "//table[@class='datepicker-table-condensed table-condensed']//td[.='29']"
		 * ; boolean element_size =
		 * driver.findElement(By.xpath(xpathExpression)) }
		 */
		Wait.wait2Second();

		TapeChart.Enter_Adults_Tapehchart.sendKeys(TapeChartAdults);
		TapeChart.Enter_Children_Tapechart.sendKeys(TapeChartChildrens);
		// TapeChart.Click_Tapechart_Rateplan.click();
		// Wait.wait5Second();
		// TapeChart.Select_Rack_Rate.click();
		// Wait.wait3Second();
		TapeChart.Enter_promoCode_Tapechart.sendKeys(PromoCode);
		TapeChart.Click_Search_TapeChart.click();
		Wait.wait2Second();

	}

	public void VerifyReservationLimitView(WebDriver driver) throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("arguments[0].scrollIntoView(true);", TapeChart.Res_View_Limit_Element);

		WebElement ele = driver.findElement(By.xpath(
				"//*[@id=\"bpjscontainer_53\"]/div/div[2]/div[2]/div[3]/div[2]/div[1]/div[5]/div[2]/span/span/div/div/div[2]/div"));
		Actions action = new Actions(driver);
		action.moveToElement(ele).build().perform();

	}

	public void VerifyReservationLimitView(WebDriver driver, String RoomName, String RoomNumber)
			throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		if (RoomName.equals("Double Bed Room")) {
			RoomName = "DBR";
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// WebElement ele = driver.findElement(By.xpath(
		// "//*[@id=\"bpjscontainer_53\"]/div/div[2]/div[2]/div[3]/div[2]/div[1]/div[5]/div[2]/span/span/div/div/div[2]/div"));
		WebElement ele = driver.findElement(By.xpath("//div[contains(text(),'" + RoomName
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer']"));
		jse.executeScript("arguments[0].scrollIntoView(true);", ele);

		Actions action = new Actions(driver);
		action.moveToElement(ele).build().perform();

	}

	public void VerifyUnassignedFooterRow(WebDriver driver) throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		TapeChart.Unassigned_Button.click();
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("arguments[0].scrollIntoView(true);", TapeChart.DBR_Unassigned_FooterRow);

		// boolean DBR_Unassigned_FooterRowLink =
		// TapeChart.DBR_Unassigned_FooterRow.isEnabled();
		boolean DBR_Unassigned_FooterRowLink = isClickable(TapeChart.DBR_Unassigned_FooterRow, driver);
		assertEquals(DBR_Unassigned_FooterRowLink, false, "DBR_Unassigned_FooterRowLink is clickable");
		Wait.wait2Second();

		jse.executeScript("arguments[0].scrollIntoView(true);", TapeChart.JR_Unssigned_FooterRow);
		boolean JR_Unassigned_FooterRowLink = TapeChart.JR_Unssigned_FooterRow.isDisplayed();
		assertEquals(JR_Unassigned_FooterRowLink, false, "JR_Unassigned_FooterRowLink is clickable");
		Wait.wait2Second();

		jse.executeScript("arguments[0].scrollIntoView(true);", TapeChart.PR_Unssigned_FooterRow);
		boolean PR_Unassigned_FooterRowLink = TapeChart.PR_Unssigned_FooterRow.isDisplayed();
		assertEquals(PR_Unassigned_FooterRowLink, false, "PR_Unassigned_FooterRowLink is clickable");

	}

	public static boolean isClickable(WebElement el, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 6);
			wait.until(ExpectedConditions.elementToBeClickable(el));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void click_Unassigned_RoomClass(WebDriver driver, String RoomClass) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		String CellPath = "//div[contains(text(),'" + RoomClass
				+ "')  and contains(@class,'roomclassname')]//ancestor::div[@class='roomRatesChart']/child::div[last()]// div[contains(text(),'Unassigned')]";
		WebElement Unassigned_Reserv_Room = driver.findElement(By.xpath(CellPath));

		jse.executeScript("arguments[0].scrollIntoView(true);", Unassigned_Reserv_Room);
		jse.executeScript("window.scrollBy(0,-300)");
		Wait.waitUntilPresenceOfElementLocated(CellPath, driver);
		jse.executeScript("arguments[0].click();", Unassigned_Reserv_Room);
		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.NewReservation_ModuleLoading)), 150);
		// TapeChart.Unassigned_Reserv_Room.click();

	}

	public void DragReservtion(WebDriver driver) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		WebElement From = driver.findElement(By.xpath(OR.ReservationToDrag));
		WebElement To = driver.findElement(By.xpath(OR.NewPlaceForReserv));
		// Using Action class for drag and drop.
		Actions act = new Actions(driver);
		// Dragged and dropped
		Action dragAndDrop = act.clickAndHold(From).moveToElement(To).release(To).build();
		dragAndDrop.perform();
		Wait.wait15Second();
		TapeChart.ConfirmChangesButton.click();
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
		assertTrue(TapeChart.Toaster_Message.isDisplayed(), "Toast Message is'nt Displayed & element not moved");
		Wait.wait2Second();
		MoveBackReservation(driver);

	}

	private void MoveBackReservation(WebDriver driver) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		WebElement From = driver.findElement(By.xpath(OR.ReservationToDragBack));
		WebElement To = driver.findElement(By.xpath(OR.PreviousPlaceofReservation));
		// Using Action class for drag and drop.
		Actions act = new Actions(driver);
		// Dragged and dropped
		Action dragAndDrop = act.clickAndHold(From).moveToElement(To).release(To).build();
		dragAndDrop.perform();
		Wait.wait2Second();
		TapeChart.ConfirmChangesButton.click();
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
		assertTrue(TapeChart.Toaster_Message.isDisplayed(), "Toast Message is'nt Displayed & element not moved");
	}

	public void ExtendReservation(WebDriver driver, String RoomNumber, String RoomClass) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();
		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		Wait.wait2Second();
		String path = "//div[contains(text(),'" + RoomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer']";
		Wait.explicit_wait_xpath(path, driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(path)), driver);
		int size = driver.findElements(By.xpath(path)).size();
		int preWidth = 0;
		if (size > 1) {
			List<WebElement> ele = driver.findElements(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele.get(size - 1));
			Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.wait2Second();
			int divWidth = ele.get(size - 1).getSize().getWidth();
			System.out.println("Pre Width : " + divWidth);
			preWidth = divWidth;
			int divHeight = ele.get(size - 1).getSize().getHeight();
			// Get half of Height
			int halfHeight = divHeight / 2;
			Actions builder = new Actions(driver);
			Action resizable = builder.moveToElement(ele.get(size - 1), divWidth, halfHeight).clickAndHold()
					.moveByOffset(divWidth, 0).release().build();
			resizable.perform();
		} else {
			WebElement ele = driver.findElement(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele);
			// Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-300)");
			// Wait.wait2Second();
			int divWidth = ele.getSize().getWidth();
			System.out.println("Pre Width : " + divWidth);
			preWidth = divWidth;
			int divHeight = ele.getSize().getHeight();
			// Get half of Height
			int halfHeight = divHeight / 2;
			Actions builder = new Actions(driver);
			Action resizable = builder.moveToElement(ele, divWidth, halfHeight).clickAndHold().moveByOffset(divWidth, 0)
					.release().build();
			resizable.perform();
		}

		Wait.explicit_wait_visibilityof_webelement_350(TapeChart.CheckIn, driver);
		assertEquals(TapeChart.CheckIn.getText(), "Check In", "Check in doesnot display");
		assertEquals(TapeChart.CheckOut.getText(), "Check Out", "Check out does not display");
		assertEquals(TapeChart.TripTotal.getText(), "Trip Total", "Trip total does not display");
		assertEquals(TapeChart.BalanceDue.getText(), "Balance Due", "Balance due does not display");
		TapeChart.ConfirmChangeReservation_Button.click();

		// verify toaster message here
		try {
			// Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message,
			// driver);
			assertEquals(TapeChart.Toaster_Message.getText(), "Reservation has been updated successfully",
					"Reservation does not expand");
		} catch (Exception e) {
			System.err.println("Toaster not Displayed");
		}
		int size2 = driver.findElements(By.xpath(path)).size();

		if (size > 1) {
			List<WebElement> ele = driver.findElements(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele.get(size - 1));
			Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.wait2Second();
			int divWidth2 = ele.get(size - 1).getSize().getWidth();
			System.out.println("Post Width : " + divWidth2);
			assertTrue(divWidth2 > preWidth, "Failed : Reservation not Extended");
		} else {
			WebElement ele = driver.findElement(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele);
			// Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-300)");
			// Wait.wait2Second();
			int divWidth2 = ele.getSize().getWidth();
			System.out.println("Post Width : " + divWidth2);
			assertTrue(divWidth2 > preWidth, "Failed : Reservation not Extended");
		}

	}

	public ArrayList<String> ConfirmExtendReservation(WebDriver driver, String RoomNumber, String RoomName,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();
		int divWidth = 0;
		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		Wait.wait2Second();
		String path = "(//div[@class='row ratesrow']//div[text()='" + RoomName + "']/../../div/div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer'])[last()]";

		int size = driver.findElements(By.xpath(path)).size();
		if (size > 1) {
			List<WebElement> ele = driver.findElements(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele.get(size - 1));
			Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.wait2Second();
			divWidth = ele.get(size - 1).getSize().getWidth();
			int divHeight = ele.get(size - 1).getSize().getHeight();
			// Get half of Height
			int halfHeight = divHeight / 2;
			Actions builder = new Actions(driver);
			Action resizable = builder.moveToElement(ele.get(size - 1), divWidth, halfHeight).clickAndHold()
					.moveByOffset(divWidth, 0).release().build();
			resizable.perform();
		} else {
			WebElement ele = driver.findElement(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele);
			Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.wait2Second();
			divWidth = ele.getSize().getWidth();
			int divHeight = ele.getSize().getHeight();
			// Get half of Height
			int halfHeight = divHeight / 2;
			Actions builder = new Actions(driver);
			Action resizable = builder.moveToElement(ele, divWidth, halfHeight).clickAndHold().moveByOffset(divWidth, 0)
					.release().build();
			resizable.perform();
		}

		Wait.explicit_wait_visibilityof_webelement(TapeChart.ConfirmChangeReservation_Button, driver);
		assertEquals(TapeChart.ConfirmChangeReservation_Button.getText(), "Confirm Changes",
				"Confirm button does not display");
		TapeChart.ConfirmChangeReservation_Button.click();
		try {
			Wait.waitForElementToBeGone(driver, TapeChart.ConfirmChangeReservation_Button, 30);
		} catch (Exception e) {
			Utility.app_logs.info("Again click Confrim Changes Button");
			Wait.explicit_wait_visibilityof_webelement(TapeChart.ConfirmChangeReservation_Button, driver);
			TapeChart.ConfirmChangeReservation_Button.click();
			Wait.waitForElementToBeGone(driver, TapeChart.ConfirmChangeReservation_Button, 30);
		}
		// verify toaster message here
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
		assertEquals(TapeChart.Toaster_Message.getText(), "Reservation has been updated successfully",
				"Reservation does not expand");
		test_steps.add("Reservation has been Updated Successfully");
		// verify size change
		Wait.wait2Second();

		if (size > 1) {
			List<WebElement> ele = driver.findElements(By.xpath(path));
			int AfterCancel_divWidth = ele.get(size - 1).getSize().getWidth();
			assertNotEquals(AfterCancel_divWidth, divWidth, "Reservation has not been expanded");
		} else {
			WebElement ele = driver.findElement(By.xpath(path));
			int AfterCancel_divWidth = ele.getSize().getWidth();
			assertNotEquals(AfterCancel_divWidth, divWidth, "Reservation has not been expanded");
		}

		return test_steps;

	}

	public void CancelExtendedReservation(WebDriver driver, String RoomNumber, String RoomClassAbb)
			throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();
		int divWidth = 0;
		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		Wait.wait2Second();
		String path = "//div[contains(text(),'" + RoomClassAbb
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer']";

		int size = driver.findElements(By.xpath(path)).size();
		if (size > 1) {
			List<WebElement> ele = driver.findElements(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele.get(size - 1));
			Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.wait2Second();
			divWidth = ele.get(size - 1).getSize().getWidth();
			int divHeight = ele.get(size - 1).getSize().getHeight();
			// Get half of Height
			int halfHeight = divHeight / 2;
			Actions builder = new Actions(driver);
			Action resizable = builder.moveToElement(ele.get(size - 1), divWidth, halfHeight).clickAndHold()
					.moveByOffset(divWidth, 0).release().build();
			resizable.perform();
		} else {
			WebElement ele = driver.findElement(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele);
			Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.wait2Second();
			divWidth = ele.getSize().getWidth();
			int divHeight = ele.getSize().getHeight();
			// Get half of Height
			int halfHeight = divHeight / 2;
			Actions builder = new Actions(driver);
			Action resizable = builder.moveToElement(ele, divWidth, halfHeight).clickAndHold().moveByOffset(divWidth, 0)
					.release().build();
			resizable.perform();
		}

		Wait.wait5Second();

		assertEquals(TapeChart.CancelChangeReservation_Button.getText(), "Cancel", "Cancel button does not display");
		TapeChart.CancelChangeReservation_Button.click();
		Wait.wait2Second();

		if (size > 1) {
			List<WebElement> ele = driver.findElements(By.xpath(path));
			int AfterCancel_divWidth = ele.get(size - 1).getSize().getWidth();
			assertEquals(AfterCancel_divWidth, divWidth, "Reservation has been expand after cancel");
		} else {
			WebElement ele = driver.findElement(By.xpath(path));
			int AfterCancel_divWidth = ele.getSize().getWidth();
			assertEquals(AfterCancel_divWidth, divWidth, "Reservation has been expand after cancel");
		}

	}

	public void VerifyResevation_handles(WebDriver driver, String RoomNumber) throws InterruptedException {

		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		Wait.wait2Second();
		String path = "//div[@title='" + RoomNumber + "']//following-sibling::div//div[@class='DatesContainer']";

		int size = driver.findElements(By.xpath(path)).size();
		if (size > 1) {
			List<WebElement> hoverElement = driver.findElements(By.xpath(path));
			int before_hover_width = hoverElement.get(size - 1).getSize().getWidth();

			Actions builder = new Actions(driver);
			builder.moveToElement(hoverElement.get(size - 1)).perform();
			int after_hover_width = hoverElement.get(size - 1).getSize().getWidth();
			assertNotEquals(after_hover_width, before_hover_width, "handles does not appear on hover");

		}

		else {

			WebElement hoverElement = driver.findElement(By.xpath(path));
			int before_hover_width = hoverElement.getSize().getWidth();

			Actions builder = new Actions(driver);
			builder.moveToElement(hoverElement).perform();
			int after_hover_width = hoverElement.getSize().getWidth();
			assertNotEquals(after_hover_width, before_hover_width, "handles does not appear on hover");

		}

	}

	public void TapeChartSearch_1Day(WebDriver driver, String TapeChartAdults, String TapeChartChildrens,
			String PromoCode) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Select_Arrival_Date, driver);
		TapeChart.Select_Arrival_Date.click();
		TapeChart.Click_Today.click();
		Wait.wait2Second();

		TapeChart.Enter_Adults_Tapehchart.sendKeys(TapeChartAdults);
		TapeChart.Enter_Children_Tapechart.sendKeys(TapeChartChildrens);

		TapeChart.TapeChart_1DayButton.click();

		TapeChart.Enter_promoCode_Tapechart.sendKeys(PromoCode);
		TapeChart.Click_Search_TapeChart.click();

		Wait.wait2Second();

	}

	public void FindAvailableSlot(WebDriver driver) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", TapeChart.TapeChartAvailableSlot);
		jse.executeScript("window.scrollBy(0,-300)");
		Wait.wait2Second();
		TapeChart.TapeChartAvailableSlot.click();

	}

	public void ClickAvailableSlot(WebDriver driver) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].scrollIntoView(true);", TapeChart.ClickTapeChartAvailableSlot);
			try {
				Wait.wait2Second();
				TapeChart.ClickTapeChartAvailableSlot.click();

			} catch (Exception e) {
				jse.executeScript("window.scrollBy(0,-300)");
				Wait.wait2Second();
				TapeChart.ClickTapeChartAvailableSlot.click();
				System.out.println("Scrol back 300");

			}
			Wait.waitUntilPresenceOfElementLocated(OR.ReservationDetailPage, driver);
		} catch (Exception e) {
			if (TapeChart.Rules_Broken_popup.isDisplayed()) {
				Wait.explicit_wait_xpath(OR.Rules_Broken_popup, driver);

				TapeChart.Click_Book_icon_Tapechart.click();
			} else {
				System.out.println("Rules are not broken");
			}
		}
		assertTrue(ReservationPage.ReservationPage.isDisplayed(), "Reservation Page didn't load");

	}

	public void ClickAvailableSlot(WebDriver driver, String RoomClass) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		String CellPath = "//div[contains(text(),'" + RoomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::"
				+ "div//following-sibling::div[contains(@class,'DatesContainer')]//ancestor::"
				+ "div[contains(@class,'tapechartdatecell Available')]";
		Wait.WaitForElement(driver, CellPath);
		WebElement AvailableSlot = driver.findElement(By.xpath(CellPath));
		Wait.explicit_wait_visibilityof_webelement(AvailableSlot, driver);
		try {
			// jse.executeScript("arguments[0].scrollIntoView(true);",
			// AvailableSlot);
			Utility.ScrollToElement(AvailableSlot, driver);
			try {
				Wait.wait2Second();
				AvailableSlot.click();

			} catch (Exception e) {
				jse.executeScript("window.scrollBy(0,-300)");
				Wait.wait2Second();
				AvailableSlot.click();
				System.out.println("Scrol back 300");

			}
			Wait.waitUntilPresenceOfElementLocated(OR.ReservationDetailPage, driver);
		} catch (Exception e) {
			if (TapeChart.Rules_Broken_popup.isDisplayed()) {
				Wait.WaitForElement(driver, OR.Rules_Broken_popup);

				TapeChart.Click_Book_icon_Tapechart.click();
			} else {
				System.out.println("Rules are not broken");
			}
		}
		assertTrue(ReservationPage.ReservationPage.isDisplayed(), "Reservation Page didn't load");

	}

	public void ClickAvailableSlot(WebDriver driver, ArrayList test_steps, String RoomClass)
			throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String CellPath = "//div[contains(text(),'" + RoomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::"
				+ "div//following-sibling::div[contains(@class,'DatesContainer')]//ancestor::"
				+ "div[contains(@class,'tapechartdatecell Available')]";
		Wait.explicit_wait_xpath(CellPath, driver);
		WebElement AvailableSlot = driver.findElement(By.xpath(CellPath));
		Wait.explicit_wait_visibilityof_webelement(AvailableSlot, driver);
		try {
			jse.executeScript("arguments[0].scrollIntoView(true);", AvailableSlot);
			try {
				Wait.wait2Second();
				AvailableSlot.click();

			} catch (Exception e) {
				jse.executeScript("window.scrollBy(0,-300)");
				Wait.wait2Second();
				AvailableSlot.click();
				test_steps.add("Clicking on available slot from tape chart");

			}
			Wait.waitUntilPresenceOfElementLocated(OR.ReservationDetailPage, driver);
		} catch (Exception e) {
			if (TapeChart.Rules_Broken_popup.isDisplayed()) {
				// Wait.explicit_wait_xpath(OR.Rules_Broken_popup, driver);
				Wait.wait5Second();
				Wait.WaitForElement(driver, OR.Click_Book_icon_Tapechart);
				TapeChart.Click_Book_icon_Tapechart.click();
				test_steps.add("Clicking on book icon");
			} else {
				System.out.println("Rules are not broken");
			}
		}

	}

	public void getUnassignedTapechartResCount(WebDriver driver) throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait5Second();
		// Wait.WaitForElement(driver, OR.getUnsassignedResCountTapechart);
		String unassignedResCountTP = TapeChart.getUnsassignedResCountTapechart.getText();
		String unassignedTapechart1 = unassignedResCountTP.replaceAll("[^a-zA-Z0-9]", "");
		// unassignedResCountTapechart=tapechartUnassigned1.replaceAll("[]",
		// "");
		unassignedResCountTapechart = unassignedTapechart1.replaceAll("UNASSIGNED", "");
		tapechartLogger.info("Unassigned Reservation Count from Tapechart before creating the reservation: "
				+ unassignedResCountTapechart);
		Wait.wait5Second();
	}

	public void getUnassignedResCountTapechartAfterCreatingRes(WebDriver driver, ExtentTest test)
			throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait10Second();
		String unassignedResCountTP1 = TapeChart.getUnsassignedResCountTapechart.getText();
		String unassignedTapechart2 = unassignedResCountTP1.replaceAll("[^a-zA-Z0-9]", "");
		unassignedResCountTapechartAfterCreatingRes = unassignedTapechart2.replaceAll("UNASSIGNED", "");
		tapechartLogger.info("Unassigned Reservation from TapechartCount after creating the reservation: "
				+ unassignedResCountTapechartAfterCreatingRes);
		Wait.wait5Second();
	}

	public void getUnassignedReservationCountValidationsInTapechart(WebDriver driver, ExtentTest test) {

		int unassignedCountTapechartAfterCreatingReservation = Integer
				.parseInt(unassignedResCountTapechartAfterCreatingRes);
		int unassignedCountTapechartBefore = Integer.parseInt(unassignedResCountTapechart) + 1;

		if (unassignedCountTapechartAfterCreatingReservation == unassignedCountTapechartBefore) {

			tapechartLogger.info(" Successfully validated unassigned Reservation Count in Tapechart ");
		}
	}

	public String GetAvailableSlot_RoomNumber(WebDriver driver, String RoomClass) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		String RoomNumber = "";
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String CellPath = "//div[text()='" + RoomClass
				+ "'  and contains(@class,'roomclassname')]//parent::*//following-sibling::"
				+ "div//following-sibling::div[contains(@class,'DatesContainer')]//ancestor::"
				+ "div[contains(@class,'tapechartdatecell Available')]//ancestor::div[contains(@class,'roomsrow')]/child::div[contains(@class,'roomnumber')]/span";
		WebElement AvailableSlot = driver.findElement(By.xpath(CellPath));
		jse.executeScript("arguments[0].scrollIntoView(true);", AvailableSlot);
		try {
			Wait.wait2Second();
			RoomNumber = AvailableSlot.getText();
			Utility.app_logs.info("Room NUmber " + RoomNumber);

		} catch (Exception e) {
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.wait2Second();

			RoomNumber = AvailableSlot.getText();
			System.out.println("Scrol back 300");
		}

		Utility.app_logs.info("Room NUmber " + RoomNumber);
		return RoomNumber;

	}

	public void ClickAvailableSlot(WebDriver driver, String RoomClass, String PropertyNumber)
			throws InterruptedException {

		Wait.wait10Second();
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);

		WebElement Property2 = driver
				.findElement(By.xpath("(" + OR.Property_ExpandButton + ")[" + PropertyNumber + "]"));
		Utility.ScrollToElement(Property2, driver);
		String PropertyName = driver
				.findElement(By.xpath("(//div [@class='propertyclickbutton'])[" + PropertyNumber + "]")).getText();
		Property2.click();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String CellPath = "//div [@class='propertyclickbutton' and text ()='" + PropertyName
				+ "']//parent::div//following-sibling::div//child::div[contains(@class,'roomclassname') and text()='"
				+ RoomClass
				+ "']//parent::*//following-sibling::div//following-sibling::div[contains(@class,'DatesContainer')]//ancestor::div[contains(@class,'tapechartdatecell Available')]";

		List<WebElement> AvailableSlots = driver.findElements(By.xpath(CellPath));
		System.out.println("Total Available slots in RoomClass : " + RoomClass + " are " + AvailableSlots.size());
		assertTrue(AvailableSlots.size() > 1, "Failed : No room Available");

		WebElement AvailableSlot = AvailableSlots.get(0);
		try {
			jse.executeScript("arguments[0].scrollIntoView(true);", AvailableSlot);
			try {
				Wait.wait2Second();
				AvailableSlot.click();

			} catch (Exception e) {
				jse.executeScript("window.scrollBy(0,-300)");
				Wait.wait2Second();
				AvailableSlot.click();
				System.out.println("Scrol back 300");

			}
			Wait.waitUntilPresenceOfElementLocated(OR.ReservationDetailPage, driver);
			assertEquals(driver.findElement(By.xpath(OR.Get_Property_Name)).getText(), PropertyName,
					"Failed: PropertyName missmatched");
		} catch (Exception e) {
			if (TapeChart.Rules_Broken_popup.isDisplayed()) {
				Wait.explicit_wait_xpath(OR.Rules_Broken_popup, driver);

				TapeChart.Click_Book_icon_Tapechart.click();
			} else {
				System.out.println("Rules are not broken");
			}
			Wait.waitUntilPresenceOfElementLocated(OR.ReservationDetailPage, driver);
			assertEquals(driver.findElement(By.xpath(OR.Get_Property_Name)).getText(), PropertyName,
					"Failed: PropertyName missmatched");

		}

	}

	public void ClickReservationTapChart(WebDriver driver) throws InterruptedException {

		WebElement Reservation = driver
				.findElement(By.xpath("//div[@title='325']//following-sibling::div//div[@class='DatesContainer']"));
		Reservation.click();

	}

	public void ClickReservation(WebDriver driver, String RoomNumber) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();
		System.out.println("ROomNum:" + RoomNumber);
		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		Wait.wait2Second();
		String path = "(//div[@title='" + RoomNumber
				+ "']//following-sibling::div//div[@class='DatesContainer']//parent::div)[1]";
		WebElement Reservation = driver.findElement(By.xpath(path));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Reservation);
		// Reservation.click();

	}

	public void ExtendReservation_Verify(WebDriver driver, String RoomNumber)
			throws InterruptedException, ParseException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();

		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		Wait.wait2Second();
		String path = "(//div[@title='" + RoomNumber
				+ "']//following-sibling::div//div[@class='DatesContainer'])[last()]";

		int size = driver.findElements(By.xpath(path)).size();
		if (size > 1) {
			List<WebElement> ele = driver.findElements(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele.get(size - 1));
			Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.wait2Second();
			int divWidth = ele.get(size - 1).getSize().getWidth();
			int divHeight = ele.get(size - 1).getSize().getHeight();
			// Get half of Height
			int halfHeight = divHeight / 2;
			Actions builder = new Actions(driver);
			Action resizable = builder.moveToElement(ele.get(size - 1), divWidth, halfHeight).clickAndHold()
					.moveByOffset(divWidth, 0).release().build();
			resizable.perform();
		} else {
			WebElement ele = driver.findElement(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele);
			Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.wait2Second();
			int divWidth = ele.getSize().getWidth();
			int divHeight = ele.getSize().getHeight();
			// Get half of Height
			int halfHeight = divHeight / 2;
			Actions builder = new Actions(driver);
			Action resizable = builder.moveToElement(ele, divWidth, halfHeight).clickAndHold().moveByOffset(divWidth, 0)
					.release().build();
			resizable.perform();
		}

		Wait.wait5Second();
		VerifyExtendDates(driver);
		TapeChart.ConfirmChangesButton.click();

		// verify toaster message here
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
		assertEquals(TapeChart.Toaster_Message.getText(), "Reservation has been updated successfully",
				"Reservation does not expand");

	}

	public void VerifyExtendDates(WebDriver driver) throws ParseException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.NewDate_CheckOut, driver);
		String newDate = TapeChart.NewDate_CheckOut.getText();
		System.out.println(newDate);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.PreviousDate_CheckOut, driver);
		System.out.println(TapeChart.PreviousDate_CheckOut.getText());
		String PreviousDate = TapeChart.PreviousDate_CheckOut.getText().substring(4);

		System.out.println(PreviousDate);
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		System.out.println(sdf.parse(PreviousDate).before(sdf.parse(newDate)));
		assertTrue(sdf.parse(PreviousDate).before(sdf.parse(newDate)), "Failed: Extended dates");

	}

	public void VerifyReduceDates(WebDriver driver) throws ParseException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		try {
			Wait.explicit_wait_visibilityof_webelement(TapeChart.NewDate_CheckOut, driver);
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(TapeChart.NewDate_CheckOut, driver);
		}
		String newDate = TapeChart.NewDate_CheckOut.getText();
		System.out.println(newDate);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.PreviousDate_CheckOut, driver);
		System.out.println(TapeChart.PreviousDate_CheckOut.getText());
		String PreviousDate = TapeChart.PreviousDate_CheckOut.getText().substring(4);

		System.out.println(PreviousDate);
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		System.out.println(sdf.parse(newDate).before(sdf.parse(PreviousDate)));
		assertTrue(sdf.parse(newDate).before(sdf.parse(PreviousDate)), "Failed: Extended dates");

	}

	public void FindReservationToMove(WebDriver driver, String RoomNumber) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();

		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		Wait.wait2Second();
		String path = "//div[@title='" + RoomNumber + "']//following-sibling::div//div[@class='DatesContainer']";
		WebElement From = driver.findElement(By.xpath(path));
		driver.findElement(By
				.xpath("//*[@id='bpjscontainer_53']/div/div[2]/div[2]/div[3]/div[2]/div[1]/div[]/div[2]/span/div[7]"));
		WebElement To = driver.findElement(By.xpath(OR.PreviousPlaceofReservation));
		;

		// Using Action class for drag and drop.
		Actions act = new Actions(driver);
		// Dragged and dropped
		Action dragAndDrop = act.clickAndHold(From).moveToElement(To).release(To).build();
		dragAndDrop.perform();
		Wait.wait5Second();

		TapeChart.ConfirmChangesButton.click();

		// verify toaster message here
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
		assertEquals(TapeChart.Toaster_Message.getText(), "Reservation has been updated successfully",
				"Reservation does not expand");

	}

	public void MoveReservation(WebDriver driver, String RoomNumber, String RoomClass) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();

		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		System.out.println(RoomNumber);
		Wait.wait2Second();
		String path = "//div[contains(text(),'" + RoomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer']";
		System.out.println("PTah:" + path);
		int nextRoom = Integer.parseInt(RoomNumber) + 1;
		String NextRoom = Integer.toString(nextRoom);
		String ToPath = "//div[contains(text(),'" + RoomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div/div[@title='" + NextRoom
				+ "']//following-sibling::div//div[@class='tapechartdatecell Available RackRate']";
		try {
			System.out.println(driver.findElement(By.xpath(ToPath)).isDisplayed());
		} catch (Exception e) {
			for (int i = 0; i <= 10; i++) {
				ToPath = "//div[contains(text(),'" + RoomClass
						+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div/div[@title='"
						+ NextRoom + "']//following-sibling::div//div[@class='DatesContainer']";
				String Reservationpath = "//div[contains(text(),'" + RoomClass
						+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div/div[@title='"
						+ NextRoom
						+ "']//following-sibling::div//div[@class='DatesContainer']//preceding-sibling::div//child::div[@class='guest_display_name_short']";

				System.out.println("To pTah:" + ToPath + "ReserPath:" + Reservationpath);
				String ReservationName = driver.findElement(By.xpath(Reservationpath)).getText();
				System.out.println(ReservationName);
				if (ReservationName.contains("Available")) {
					break;
				} else {
					nextRoom = Integer.parseInt(NextRoom) + 1;
					NextRoom = Integer.toString(nextRoom);
				}
			}
		}
		System.out.println("To pTah:" + ToPath);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(path)));

		jse.executeScript("window.scrollBy(0,-300)");
		System.out.println("Scrolled to the reservation");
		WebElement From = driver.findElement(By.xpath(path));
		Point Location = From.getLocation();

		Wait.explicit_wait_xpath(ToPath, driver);
		WebElement To = driver.findElement(By.xpath(ToPath));

		// Using Action class for drag and drop.
		Actions act = new Actions(driver);
		// Dragged and dropped
		act.dragAndDrop(From, To).build().perform();

		Wait.wait2Second();
		if (TapeChart.Rules_Broken_popup.isDisplayed()) {
			Wait.explicit_wait_xpath(OR.Rules_Broken_popup, driver);
			TapeChart.Click_Book_icon_Tapechart.click();
			System.out.println("Rules are broken");
		} else {
			System.out.println("Rules are not broken");
		}
		Wait.wait5Second();

		TapeChart.ConfirmChangesButton.click();

		// verify toaster message here
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
		assertEquals(TapeChart.Toaster_Message.getText(), "Reservation has been updated successfully",
				"Reservation does not Move");

	}

	public ArrayList<String> MoveReservation_Cancel(WebDriver driver, String RoomNumber, String RoomClass,
			String RoomClass2, ArrayList<String> test_steps) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();

		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		System.out.println(RoomNumber);
		Wait.wait2Second();
		String path = "(//div[contains(text(),'" + RoomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div/div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer'])[last()-1]";
		String ToPath = "//div[contains(text(),'" + RoomClass2
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::"
				+ "div//following-sibling::div[contains(@class,'DatesContainer')]//ancestor::"
				+ "div[contains(@class,'tapechartdatecell Available')]";
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].scrollIntoView(true);",
		// driver.findElement(By.xpath(path)));
		Utility.ScrollToElement(driver.findElement(By.xpath(path)), driver);
		jse.executeScript("window.scrollBy(0,-200)");
		Wait.wait2Second();
		System.out.println("Scrolled to the reservation");
		WebElement From = driver.findElement(By.xpath(path));
		Point Location = From.getLocation();

		Wait.explicit_wait_xpath(ToPath, driver);
		WebElement To = driver.findElement(By.xpath(ToPath));

		// Using Action class for drag and drop.
		Actions act = new Actions(driver);
		// Dragged and dropped
		act.dragAndDrop(From, To).build().perform();
		test_steps.add("Drag the reservation to Next date from RoomClass: '" + RoomClass + "' to '" + RoomClass2 + "'");
		Wait.wait2Second();
		try {
			Wait.waitUntilPresenceOfElementLocated(OR.ReservationUpdate_Popup, driver);
			assertTrue(TapeChart.ReservationUpdate_Popup.isDisplayed(), "Reservation Update popup is not open");
		} catch (Exception e) {
			act = new Actions(driver);
			// Dragged and dropped
			act.dragAndDrop(From, To).build().perform();
			test_steps.add(
					"Drag the reservation to Next date from RoomClass: '" + RoomClass + "' to '" + RoomClass2 + "'");
			Wait.wait2Second();
			Wait.waitUntilPresenceOfElementLocated(OR.ReservationUpdate_Popup, driver);
			assertTrue(TapeChart.ReservationUpdate_Popup.isDisplayed(), "Reservation Update popup is not open");
		} catch (Error e) {
			act = new Actions(driver);
			// Dragged and dropped
			act.dragAndDrop(From, To).build().perform();
			test_steps.add(
					"Drag the reservation to Next date from RoomClass: '" + RoomClass + "' to '" + RoomClass2 + "'");
			Wait.wait2Second();
			Wait.waitUntilPresenceOfElementLocated(OR.ReservationUpdate_Popup, driver);
			assertTrue(TapeChart.ReservationUpdate_Popup.isDisplayed(), "Reservation Update popup is not open");
		}

		test_steps.add("Reservation Update Popup Appears");
		Wait.explicit_wait_visibilityof_webelement(TapeChart.CancelChangesButton, driver);
		Utility.ScrollToElement(TapeChart.CancelChangesButton, driver);
		TapeChart.CancelChangesButton.click();
		test_steps.add("Click Cancel Reservation Update");
		try {
			Wait.waitForElementToBeGone(driver, TapeChart.CancelChangesButton, 30);
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(TapeChart.CancelChangesButton, driver);
			Utility.ScrollToElement(TapeChart.CancelChangesButton, driver);
			TapeChart.CancelChangesButton.click();
			test_steps.add("Click Cancel Reservation Update");
			Wait.waitForElementToBeGone(driver, TapeChart.CancelChangesButton, 30);
		}
		test_steps.add("Reservation Update Popup Disappears");
		Thread.sleep(3000);
		assertEquals(driver.findElement(By.xpath(path)).getLocation(), Location, "Failed: Location has been Changed");
		test_steps.add("Reservation is Displayed in it's Original Position");
		return test_steps;

	}

	public void ClickAnyReservation(WebDriver driver) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("(" + OR.ClickReservation + ")[2]")));
		Wait.wait2Second();
		jse.executeScript("window.scrollBy(0,-300)");
		// TapeChart.ClickReservation.click();
		driver.findElement(By.xpath("(" + OR.ClickReservation + ")[1]")).click();
		Wait.wait2Second();

	}

	public void VerifyRate_TapeChart(WebDriver driver, String RateValue) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		String NewSetRate = TapeChart.FirstRoomClass_Rate_In_Tapechart.getText();
		assertTrue(NewSetRate.equals("$" + RateValue), "Failed:New Rate Value is not correct");

	}

	public void VerifyRate_TapeChart(WebDriver driver, String RateValue, String RoomClassAbb)
			throws InterruptedException {

		String RoomClassRatePath = "//div[text()='" + RoomClassAbb
				+ "' and contains(@class,'roomclassname')]//..//div//div";
		System.out.println(RoomClassRatePath);

		List<WebElement> RoomRate = driver.findElements(By.xpath(RoomClassRatePath));
		Wait.explicit_wait_visibilityof_webelement(RoomRate.get(0), driver);
		Utility.ScrollToElement(RoomRate.get(0), driver);
		String NewSetRate = RoomRate.get(0).getText();
		System.out.println("TAPCHART : " + NewSetRate);
		System.out.println("rate value : " + RateValue);
		assertEquals(NewSetRate, "$" + RateValue, "new rate value is not founded");
	}

	public void TapeChartSearch_Checkin(WebDriver driver, String Checkin) throws InterruptedException, ParseException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		SimpleDateFormat resultDateFormat = new SimpleDateFormat("MMM dd, yyyy");
		SimpleDateFormat CheckInDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date ArrivalDate = resultDateFormat.parse(Checkin);
		String CheckinDate = CheckInDateFormat.format(ArrivalDate);
		System.out.println(CheckinDate);
		Wait.waitUntilPresenceOfElementLocated(OR.TapeChart_CheckIn, driver);
		TapeChart.TapeChart_CheckIn.sendKeys(CheckinDate);
		assertTrue(TapeChart.TapeChart_CheckIn.getAttribute("value").contains(CheckinDate), "Failed: CheckIn");

		TapeChart.Enter_Adults_Tapehchart.clear();
		TapeChart.Enter_Adults_Tapehchart.sendKeys("1");
		assertTrue(TapeChart.Enter_Adults_Tapehchart.getAttribute("value").contains("1"), "Failed: Adult set");
		TapeChart.Enter_Children_Tapechart.clear();
		TapeChart.Click_Search_TapeChart.click();
		Wait.wait2Second();

	}

	public void ExtendReservation_Room(WebDriver driver, String Room) throws InterruptedException, ParseException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();
		String RoomClass = Room.split("\\:")[0].replaceAll("\\s+", "");
		String RoomNumber = Room.split("\\:")[1].replaceAll("\\s+", "");
		System.out.println("RC:" + RoomClass + " RN:" + RoomNumber);
		Wait.wait2Second();
		String path = "(//div[@class='row ratesrow']//div[text()='" + RoomClass + "']/../../div/div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer'])[last()]";

		Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(path)), driver);
		int size = driver.findElements(By.xpath(path)).size();

		System.out.println("Sixe is :" + size);
		if (size > 1) {
			List<WebElement> ele = driver.findElements(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele.get(size - 1));
			Utility.ScrollToElement(ele.get(size - 1), driver);
			Utility.app_logs.info("Scroll");
			jse.executeScript("window.scrollBy(0,-500)");
			Utility.app_logs.info("Scroll back");
			Wait.wait2Second();
			int divWidth = ele.get(size - 1).getSize().getWidth();
			int divHeight = ele.get(size - 1).getSize().getHeight();
			// Get half of Height
			int halfHeight = divHeight / 2;
			Actions builder = new Actions(driver);
			Action resizable = builder.moveToElement(ele.get(size - 1), divWidth, halfHeight).clickAndHold()
					.moveByOffset(divWidth, 0).release().build();
			resizable.perform();
		} else {
			WebElement ele = driver.findElement(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			Utility.ScrollToElement(ele, driver);
			Utility.app_logs.info("Scroll ");
			jse.executeScript("window.scrollBy(0,-500)");
			Utility.app_logs.info("Scroll back");
			Wait.wait2Second();
			int divWidth = ele.getSize().getWidth();
			int divHeight = ele.getSize().getHeight();
			// Get half of Height
			int halfHeight = divHeight / 2;
			Actions builder = new Actions(driver);
			Action resizable = builder.moveToElement(ele, divWidth, halfHeight).clickAndHold().moveByOffset(divWidth, 0)
					.release().build();
			resizable.perform();
		}
		Wait.wait2Second();
		if (TapeChart.Rules_Broken_popup.isDisplayed()) {
			Wait.explicit_wait_xpath(OR.Rules_Broken_popup, driver);
			TapeChart.Click_Book_icon_Tapechart.click();
			System.out.println("Rules are broken");
		} else {
			System.out.println("Rules are not broken");
		}
		VerifyExtendDates(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.ConfirmChangesButton, driver);

		TapeChart.ConfirmChangesButton.click();

		// verify toaster message here
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
		assertEquals(TapeChart.Toaster_Message.getText(), "Reservation has been updated successfully",
				"Reservation does not expand");

	}

	public void ReduceReservation(WebDriver driver, String RoomNumber, String RoomName)
			throws InterruptedException, ParseException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();
		int divWidth = 0;
		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		Wait.wait2Second();
		String path = "(//div[@class='row ratesrow']//div[text()='" + RoomName + "']/../../div/div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer'])[last()]";
		System.out.println(path);
		Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(path)), driver);
		int size = driver.findElements(By.xpath(path)).size();
		System.out.println("Sixe is :" + size);
		int preWidth = 0;
		if (size > 1) {
			List<WebElement> ele = driver.findElements(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele.get(size - 1));
			Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.wait2Second();
			divWidth = ele.get(size - 1).getSize().getWidth();
			System.out.println("Pre Width Reduce : " + divWidth);
			preWidth = divWidth;
			int divHeight = ele.get(size - 1).getSize().getHeight();
			// Get half of Height
			int halfHeight = divHeight / 2;
			Actions builder = new Actions(driver);
			Action resizable = builder.moveToElement(ele.get(size - 1), divWidth, halfHeight).clickAndHold()
					.moveByOffset(-divWidth, 0).release().build();
			resizable.perform();
		} else {
			WebElement ele = driver.findElement(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele);
			Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.wait2Second();
			divWidth = ele.getSize().getWidth();
			System.out.println("Pre Width Reduce : " + divWidth);
			preWidth = divWidth;
			int divHeight = ele.getSize().getHeight();
			// Get half of Height
			int halfHeight = divHeight / 2;
			Actions builder = new Actions(driver);
			Action resizable = builder.moveToElement(ele, divWidth, halfHeight).clickAndHold()
					.moveByOffset(-divWidth, 0).release().build();
			resizable.perform();

		}
		Wait.wait2Second();
		if (TapeChart.Rules_Broken_popup.isDisplayed()) {
			Wait.explicit_wait_xpath(OR.Rules_Broken_popup, driver);
			TapeChart.Click_Book_icon_Tapechart.click();
			System.out.println("Rules are broken");
		} else {
			System.out.println("Rules are not broken");
		}
		Wait.wait2Second();
		VerifyReduceDates(driver);

		TapeChart.ConfirmChangesButton.click();

		try {
			Wait.waitForElementToBeGone(driver, TapeChart.ConfirmChangesButton, 10000);
			System.out.println("confirmed clicked");
		} catch (Exception e) {
			TapeChart.ConfirmChangesButton.click();
			Wait.waitForElementToBeGone(driver, TapeChart.ConfirmChangesButton, 10000);
		}

		try {
			// verify toaster message here
			// Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message,
			// driver);
			assertEquals(TapeChart.Toaster_Message.getText(), "Reservation has been updated successfully",
					"Reservation does not Reduce");
		} catch (Exception e) {
			System.err.println("Toast Not Displayed");
		}
		if (size > 1) {
			List<WebElement> ele = driver.findElements(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele.get(size - 1));
			Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.wait2Second();
			divWidth = ele.get(size - 1).getSize().getWidth();
			System.out.println("Post Width Reduce : " + divWidth);
			assertTrue(divWidth < preWidth, "Failed : Reservation not Reduced");

		} else {
			WebElement ele = driver.findElement(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele);
			Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.wait2Second();
			divWidth = ele.getSize().getWidth();
			System.out.println("Post Width Reduce : " + divWidth);
			assertTrue(divWidth < preWidth, "Failed : Reservation not Reduced");
		}

	}

	public void ReduceReservation_Room(WebDriver driver, String Room) throws InterruptedException, ParseException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();
		String RoomClass = Room.split("\\:")[0].replaceAll("\\s+", "");
		String RoomNumber = Room.split("\\:")[1].replaceAll("\\s+", "");
		System.out.println("RC:" + RoomClass + " RN:" + RoomNumber);
		Wait.wait2Second();
		String path = "(//div[@class='row ratesrow']//div[text()='" + RoomClass + "']/../../div/div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer'])[last()]";

		Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(path)), driver);
		int size = driver.findElements(By.xpath(path)).size();
		System.out.println("Sixe is :" + size);
		if (size > 1) {
			List<WebElement> ele = driver.findElements(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele.get(size - 1));
			Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.wait2Second();
			int divWidth = ele.get(size - 1).getSize().getWidth();
			int divHeight = ele.get(size - 1).getSize().getHeight();
			// Get half of Height
			int halfHeight = divHeight / 2;
			Actions builder = new Actions(driver);
			Action resizable = builder.moveToElement(ele.get(size - 1), divWidth, halfHeight).clickAndHold()
					.moveByOffset(-divWidth, 0).release().build();
			resizable.perform();
		} else {
			WebElement ele = driver.findElement(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele);
			Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.wait2Second();
			int divWidth = ele.getSize().getWidth();
			int divHeight = ele.getSize().getHeight();
			// Get half of Height
			int halfHeight = divHeight / 2;
			Actions builder = new Actions(driver);
			Action resizable = builder.moveToElement(ele, divWidth, halfHeight).clickAndHold()
					.moveByOffset(-divWidth, 0).release().build();
			resizable.perform();
		}
		Wait.wait2Second();
		if (TapeChart.Rules_Broken_popup.isDisplayed()) {
			Wait.explicit_wait_xpath(OR.Rules_Broken_popup, driver);
			TapeChart.Click_Book_icon_Tapechart.click();
			System.out.println("Rules are broken");
		} else {
			System.out.println("Rules are not broken");
		}
		VerifyReduceDates(driver);

		TapeChart.ConfirmChangesButton.click();

		// verify toaster message here
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
		assertEquals(TapeChart.Toaster_Message.getText(), "Reservation has been updated successfully",
				"Reservation does not Reduce");

	}

	public void Verify_RoomClass_AfterRoomMainModification(WebDriver driver, String RoomNumber, String RoomClassAbb,
			boolean isOut) throws InterruptedException {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// String RoomName = RoomClass.split(" : ")[0];
		// String RoomNumber = RoomClass.split(" : ")[1];
		// System.out.println(RoomName);
		Wait.wait2Second();

		String path = "(//div[@class='row ratesrow']//div[text()='" + RoomClassAbb + "']/../../div/div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer'])";
		Wait.wait2Second();
		List<WebElement> ele = driver.findElements(By.xpath(path));

		Wait.wait2Second();
		String eleText = "Available";
		if (isOut) {
			for (int i = 0; i < ele.size(); i++) {
				if (i < 2) {
					jse.executeScript("arguments[0].scrollIntoView(true);", ele.get(i));
					WebElement parent = ele.get(i).findElement(By.xpath(".."));
					System.out.println(parent.getText());
					if (parent.getText().equalsIgnoreCase("Out")) {
						eleText = parent.getText();
						break;
					}
				}
			}
			assertTrue(eleText.equals("Out"), "Failed: RoomMain_Item not modified");
		} else {
			for (int i = 0; i < ele.size(); i++) {
				if (i < 1) {
					jse.executeScript("arguments[0].scrollIntoView(true);", ele.get(i));
					WebElement parent = ele.get(i).findElement(By.xpath(".."));
					System.out.println(parent.getText());
					if (parent.getText().equalsIgnoreCase("Out")) {
						eleText = parent.getText();
						assertTrue(eleText.equals("Out"), "Failed: RoomMain_Item not modified");
						break;
					}
				} else if (i == 1) {
					jse.executeScript("arguments[0].scrollIntoView(true);", ele.get(i));
					WebElement parent = ele.get(i).findElement(By.xpath(".."));
					System.out.println(parent.getText());
					if (!parent.getText().equalsIgnoreCase("Out")) {
						eleText = parent.getText();
						assertTrue(!eleText.equals("Out"), "Failed: RoomMain_Item Displayed Out");
						break;
					}
				}
			}

		}
	}

	public void Verify_RoomClass_AfterRoomMainDeletion(WebDriver driver, String RoomNumber, String RoomClassAbb)
			throws InterruptedException {

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		Wait.wait2Second();
		String path = "(//div[@class='row ratesrow']//div[text()='" + RoomClassAbb + "']/../../div/div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer'])[last()]";

		List<WebElement> ele = driver.findElements(By.xpath(path));

		Wait.wait2Second();
		String eleText = "Available";
		for (int i = 0; i < ele.size(); i++) {
			jse.executeScript("arguments[0].scrollIntoView(true);", ele.get(i));
			WebElement parent = ele.get(i).findElement(By.xpath(".."));
			System.out.println(parent.getText());
			if (parent.getText().equalsIgnoreCase("Out")) {
				eleText = parent.getText();
				break;
			}
		}

		assertTrue(!eleText.equals("Out"), "Failed: RoomMain_Item not deleted");

	}

	public void ClickEmptyCell(WebDriver driver, String roomClass) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();
		String EmptyCellPath = "//div[contains(text(),'" + roomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//following-sibling::div[contains(@class,'noResAvail')]";
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		List<WebElement> EmptyCells = driver.findElements(By.xpath(EmptyCellPath));
		int TotalEmptyCells = EmptyCells.size();
		System.out.println("Total Cells :" + TotalEmptyCells);
		for (int i = 1; i < TotalEmptyCells; i++) {
			String CellPath = "(" + EmptyCellPath + ")[" + i + "]";
			WebElement cell = driver.findElement(By.xpath(CellPath));
			System.out.println(CellPath);
			jse.executeScript("arguments[0].scrollIntoView(true);", cell);
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.wait5Second();
			// jse.executeScript("arguments[0].click();", cell);
			try {
				int width = cell.getSize().getWidth();
				int height = cell.getSize().getHeight();
				Actions act = new Actions(driver);
				act.moveToElement(cell).moveByOffset((width / 2) - 2, (height / 2) - 2);
				Wait.wait2Second();
				act.click().perform();
				Wait.wait2Second();
				Utility.app_logs.info("Clicked on Empty Cell");
				if (TapeChart.Rules_Broken_popup.isDisplayed()) {
					Wait.explicit_wait_xpath(OR.Rules_Broken_popup, driver);

					TapeChart.Click_Book_icon_Tapechart.click();
				} else {
					System.out.println("Rules are not broken");
				}

				Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);
				if (driver.findElement(By.xpath(OR.OpenedReservation_Name)).getText().contains("New Reser...")) {
					break;
				} else {
					driver.findElement(By.xpath(OR.OpenedReservation_CloseButton)).click();
					Wait.explicit_wait_xpath(EmptyCellPath, driver);
				}
			} catch (Exception e) {
				Utility.app_logs.info("No reservation tab open");

			}
		}

	}

	public void ExtendRes_VerifyField(WebDriver driver, String RoomNumber) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();
		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		Wait.wait2Second();
		String path = "//div[@title='" + RoomNumber + "']//following-sibling::div//div[@class='DatesContainer']";

		int size = driver.findElements(By.xpath(path)).size();
		if (size > 1) {
			List<WebElement> ele = driver.findElements(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele.get(size - 1));
			Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.wait2Second();
			int divWidth = ele.get(size - 1).getSize().getWidth();
			int divHeight = ele.get(size - 1).getSize().getHeight();
			// Get half of Height
			int halfHeight = divHeight / 2;
			Actions builder = new Actions(driver);
			Action resizable = builder.moveToElement(ele.get(size - 1), divWidth, halfHeight).clickAndHold()
					.moveByOffset(divWidth, 0).release().build();
			resizable.perform();
		} else {
			WebElement ele = driver.findElement(By.xpath(path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele);
			Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.wait2Second();
			int divWidth = ele.getSize().getWidth();
			int divHeight = ele.getSize().getHeight();
			// Get half of Height
			int halfHeight = divHeight / 2;
			Actions builder = new Actions(driver);
			Action resizable = builder.moveToElement(ele, divWidth, halfHeight).clickAndHold().moveByOffset(divWidth, 0)
					.release().build();
			resizable.perform();
		}

		Wait.wait10Second();
		assertEquals(TapeChart.CheckIn.getText(), "Check In", "Check in does not display");
		assertEquals(TapeChart.CheckOut.getText(), "Check Out", "Check out does not display");
		assertEquals(TapeChart.TripTotal.getText(), "Trip Total", "Trip total does not display");
		assertEquals(TapeChart.BalanceDue.getText(), "Balance Due", "Balance due does not display");

	}

	public void MoveReservation_WithoutConfirm(WebDriver driver, String RoomNumber, String RoomClass)
			throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();

		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		System.out.println(RoomNumber);
		Wait.wait2Second();
		String path = "//div[contains(text(),'" + RoomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer']";
		Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(path)), driver);
		int nextRoom = Integer.parseInt(RoomNumber) + 1;
		String NextRoom = Integer.toString(nextRoom);
		String ToPath = "//div[@title='" + NextRoom
				+ "']//following-sibling::div//div[@class='tapechartdatecell Available RackRate']";
		try {
			System.out.println(driver.findElement(By.xpath(ToPath)).isDisplayed());
		} catch (Exception e) {
			for (int i = 0; i <= 10; i++) {
				ToPath = "//div[contains(text(),'" + RoomClass
						+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div/div[@title='"
						+ NextRoom + "']//following-sibling::div//div[@class='DatesContainer']";
				String Reservationpath = "//div[contains(text(),'" + RoomClass
						+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div/div[@title='"
						+ NextRoom
						+ "']//following-sibling::div//div[@class='DatesContainer']//preceding-sibling::div//child::div[@class='guest_display_name_short']";

				System.out.println("To pTah:" + ToPath + "ReserPath:" + Reservationpath);
				WebElement Reservation = driver.findElement(By.xpath(Reservationpath));
				Wait.explicit_wait_visibilityof_webelement(Reservation, driver);
				Utility.ScrollToElement(Reservation, driver);
				String ReservationName = Reservation.getText();
				System.out.println(ReservationName);
				if (ReservationName.contains("Available")) {
					break;
				} else {
					nextRoom = Integer.parseInt(NextRoom) + 1;
					NextRoom = Integer.toString(nextRoom);
				}
			}
		}

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(path)));

		jse.executeScript("window.scrollBy(0,-300)");
		System.out.println("Scrolled to the reservation");
		WebElement From = driver.findElement(By.xpath(path));
		Point Location = From.getLocation();

		Wait.explicit_wait_xpath(ToPath, driver);
		WebElement To = driver.findElement(By.xpath(ToPath));

		// Using Action class for drag and drop.
		Actions act = new Actions(driver);
		// Dragged and dropped
		act.dragAndDrop(From, To).build().perform();

		try {
			// verify toaster message here
			Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.Toaster_Message)), driver);
			System.out.println(TapeChart.Toaster_Message.getText());
			assertEquals(TapeChart.Toaster_Message.getText(), "Reservation(s) moved Successfully",
					"Reservation does not Move");
			Utility.app_logs.info("Confirm popup doesnot display");
		} catch (Exception e) {
			if (TapeChart.Rules_Broken_popup.isDisplayed()) {
				Wait.explicit_wait_xpath(OR.Rules_Broken_popup, driver);
				TapeChart.Click_Book_icon_Tapechart.click();
				System.out.println("Rules are broken");
			} else {
				System.out.println("Rules are not broken");
			}
			try {
				// verify toaster message here
				Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.Toaster_Message)), driver);
				System.out.println(TapeChart.Toaster_Message.getText());
				assertEquals(TapeChart.Toaster_Message.getText(), "Reservation(s) moved Successfully",
						"Reservation does not Move");
				Utility.app_logs.info("Confirm popup doesnot display");
			} catch (Exception g) {
				Wait.explicit_wait_visibilityof_webelement(TapeChart.ConfirmChangesButton, driver);
				TapeChart.ConfirmChangesButton.click();
				// verify toaster message here
				Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.Toaster_Message)), driver);
				Utility.app_logs.info(TapeChart.Toaster_Message.getText());
				assertEquals(TapeChart.Toaster_Message.getText(), "Reservation has been updated successfully",
						"Reservation does not Reduce");
			}
		}
	}

	public ArrayList<String> MoveReservationSameClass_WithoutConfirm(WebDriver driver, String RoomNumber,
			String RoomClass, String RoomClass2, ArrayList<String> test_steps) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();

		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		System.out.println(RoomNumber);
		Wait.wait2Second();
		String path = "(//div[contains(text(),'" + RoomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer'])[last()]";
		int nextRoom = Integer.parseInt(RoomNumber) + 1;
		Utility.app_logs.info(path);
		String NextRoom = RoomNumber;
		System.out.println("RoomClass:" + RoomClass);
		String ToPath = "//div[contains(text(),'" + RoomClass2
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::"
				+ "div//following-sibling::div[contains(@class,'DatesContainer')]//ancestor::"
				+ "div[contains(@class,'tapechartdatecell Available')]";
		// String ToPath = "//div[contains(text(),'" + RoomClass
		// + "') and
		// contains(@class,'roomclassname')]//parent::*//following-sibling::div//following-sibling::div//div[@class='tapechartdatecell
		// Available RackRate']";
		Utility.app_logs.info(ToPath);
		List<WebElement> AvailableRooms = driver.findElements(By.xpath(ToPath));
		Utility.ScrollToElement(driver.findElement(By.xpath(path)), driver);
		Wait.wait2Second();
		if (AvailableRooms.size() == 0) {
			assertTrue(false, "Failed No Room Available for the selected criteria");
		} else {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			// jse.executeScript("arguments[0].scrollIntoView(true);",
			// driver.findElement(By.xpath(path)));
			jse.executeScript("window.scrollBy(0,-200)");

			System.out.println("Scrolled to the reservation");
			WebElement From = driver.findElement(By.xpath(path));
			Point Location = From.getLocation();

			Wait.explicit_wait_xpath(ToPath, driver);
			WebElement To = driver.findElement(By.xpath(ToPath));

			// Using Action class for drag and drop.
			Actions act = new Actions(driver);
			// Dragged and dropped
			act.dragAndDrop(From, To).build().perform();
			// System.out.print("From:" + From + "To:" + To);
			test_steps.add("Change Reservation Date");
			Utility.app_logs.info("Change Reservation Date");
			Wait.explicit_wait_visibilityof_webelement(TapeChart.OverBookingPopup, driver);
			try {
				assertTrue(TapeChart.OverBookingPopup.isDisplayed(), "Failed: OverBooking Popup not appear");
			} catch (Exception e) {
				Utility.ScrollToElement(driver.findElement(By.xpath(path)), driver);
				Wait.wait2Second();
				act = new Actions(driver);
				// Dragged and dropped
				act.dragAndDrop(From, To).build().perform();
				// System.out.print("From:" + From + "To:" + To);
				test_steps.add("Change Reservation Date");
				Utility.app_logs.info("Change Reservation Date");
				Wait.explicit_wait_visibilityof_webelement(TapeChart.OverBookingPopup, driver);
				assertTrue(TapeChart.OverBookingPopup.isDisplayed(), "Failed: OverBooking Popup not appear");
			} catch (Error e) {
				Utility.ScrollToElement(driver.findElement(By.xpath(path)), driver);
				Wait.wait2Second();
				act = new Actions(driver);
				// Dragged and dropped
				act.dragAndDrop(From, To).build().perform();
				// System.out.print("From:" + From + "To:" + To);
				test_steps.add("Change Reservation Date");
				Utility.app_logs.info("Change Reservation Date");
				Wait.explicit_wait_visibilityof_webelement(TapeChart.OverBookingPopup, driver);
				assertTrue(TapeChart.OverBookingPopup.isDisplayed(), "Failed: OverBooking Popup not appear");
			}
			test_steps.add("Over Booking popup Appear");
			Utility.app_logs.info("Over Booking popup Appear");
			Wait.explicit_wait_visibilityof_webelement(TapeChart.OverBookingPopup_Continue, driver);
			Utility.ScrollToElement(TapeChart.OverBookingPopup_Continue, driver);
			TapeChart.OverBookingPopup_Continue.click();
			test_steps.add("Click on Continue Button of Over Booking popup ");
			Utility.app_logs.info("Click Continue Over Booking popup ");
			Wait.waitUntilPresenceOfElementLocated(OR.ReservationUpdate_Popup, driver);
			assertTrue(TapeChart.ReservationUpdate_Popup.isDisplayed(), "Reservation Update popup is not open");

			test_steps.add("Reservation Update Popup Appears");
			Wait.explicit_wait_visibilityof_webelement(TapeChart.ConfirmChanges_Button, driver);
			Utility.ScrollToElement(TapeChart.ConfirmChanges_Button, driver);
			TapeChart.ConfirmChanges_Button.click();
			test_steps.add("Click Confirm Changes Reservation Update");
			try {
				Wait.waitForElementToBeGone(driver, TapeChart.ConfirmChanges_Button, 30);
			} catch (Exception e) {
				Wait.explicit_wait_visibilityof_webelement(TapeChart.ConfirmChanges_Button, driver);
				Utility.ScrollToElement(TapeChart.ConfirmChanges_Button, driver);
				TapeChart.ConfirmChanges_Button.click();
				test_steps.add("Click Confirm Changes Reservation Update");
				Wait.waitForElementToBeGone(driver, TapeChart.ConfirmChanges_Button, 30);
			}
			test_steps.add("Reservation Update Popup Disappears");
			Thread.sleep(3000);
		}
		return test_steps;
	}

	public ArrayList<String> MoveReservationSameClass_WithoutConfirm_NoOverbook(WebDriver driver, String RoomNumber,
			String RoomClass, ArrayList<String> test_steps) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();

		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		System.out.println(RoomNumber);
		Wait.wait2Second();
		String path = "(//div[contains(text(),'" + RoomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer'])[last()]";
		int nextRoom = Integer.parseInt(RoomNumber) + 1;
		Utility.app_logs.info(path);
		String NextRoom = RoomNumber;
		System.out.println("RoomClass:" + RoomClass);
		String ToPath = "//div[contains(text(),'" + RoomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//following-sibling::div//div[@class='tapechartdatecell Available RackRate']";
		Utility.app_logs.info(ToPath);
		List<WebElement> AvailableRooms = driver.findElements(By.xpath(ToPath));
		Utility.ScrollToElement(driver.findElement(By.xpath(path)), driver);
		if (AvailableRooms.size() == 0) {
			assertTrue(false, "Failed No Room Available for the selected criteria");
		} else {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			// jse.executeScript("arguments[0].scrollIntoView(true);",
			// driver.findElement(By.xpath(path)));

			// jse.executeScript("window.scrollBy(0,-300)");

			System.out.println("Scrolled to the reservation");
			WebElement From = driver.findElement(By.xpath(path));
			Point Location = From.getLocation();

			Wait.explicit_wait_xpath(ToPath, driver);
			WebElement To = driver.findElement(By.xpath(ToPath));

			// Using Action class for drag and drop.
			Actions act = new Actions(driver);
			// Dragged and dropped
			act.dragAndDrop(From, To).build().perform();
			// System.out.print("From:" + From + "To:" + To);
			test_steps.add("Change Reservation Date");
			Utility.app_logs.info("Change Reservation Date");
			try {
				Wait.explicit_wait_visibilityof_webelement(TapeChart.OverBookingPopup, driver);
			} catch (Exception e) {
				test_steps.add("No OverBooking popup Appeared");
				Utility.app_logs.info("No OverBooking popup Appeared");
			}
			Wait.explicit_wait_visibilityof_webelement(TapeChart.ConfirmChangesButton, driver);
		}
		return test_steps;
	}

	public void MoveReservation1(WebDriver driver, String RoomNumber, String RoomClass, String ReservationNo)
			throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();

		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		System.out.println(RoomNumber);
		Wait.wait2Second();
		String path = "(//div[contains(text(),'" + RoomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer'])";
		int nextRoom = Integer.parseInt(RoomNumber) + 1;
		String NextRoom = Integer.toString(nextRoom);
		// String ToPath = "//div[@title='" + NextRoom
		// + "']//following-sibling::div//div[@class='tapechartdatecell
		// Available RackRate']";
		String ToPath = "//div[contains(text(),'" + RoomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div/div[@title>'"
				+ RoomNumber
				+ "']//following-sibling::div//div[@class='DatesContainer']/../div//div[text()='Available']";

		try {
			System.out.println(ToPath);
			System.out.println(driver.findElement(By.xpath(ToPath)).isDisplayed());
		} catch (Exception e) {
			for (int i = 0; i <= 10; i++) {
				ToPath = "//div[contains(text(),'" + RoomClass
						+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div/div[@title='"
						+ NextRoom + "']//following-sibling::div//div[@class='DatesContainer']";
				String Reservationpath = "//div[contains(text(),'" + RoomClass
						+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div/div[@title='"
						+ NextRoom
						+ "']//following-sibling::div//div[@class='DatesContainer']//preceding-sibling::div//child::div[@class='guest_display_name_short']";

				System.out.println("To pTah:" + ToPath + "ReserPath:" + Reservationpath);
				String ReservationName = driver.findElement(By.xpath(Reservationpath)).getText();
				System.out.println(ReservationName);
				if (ReservationName.contains("Available")) {
					break;
				} else {
					nextRoom = Integer.parseInt(NextRoom) + 1;
					NextRoom = Integer.toString(nextRoom);
				}
			}
		}

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(path)));

		int sizePath = driver.findElements(By.xpath(path)).size();
		if (sizePath > 1) {
			for (int i = 1; i <= sizePath; i++) {
				WebElement ele = driver.findElement(By.xpath(path + "[" + i + "]"));
				jse.executeScript("arguments[0].scrollIntoView(true);", ele);
				jse.executeScript("arguments[0].click();", ele);
				Wait.explicit_wait_xpath(OR.Get_Confirmation_Number, driver);
				boolean flag = driver.findElement(By.xpath(OR.Get_Confirmation_Number)).getText().equals(ReservationNo);
				if (flag) {
					path = path + "[" + i + "]";
					System.out.println("New Path : " + path);
					Elements_Reservation ReservationPage = new Elements_Reservation(driver);
					Utility.ScrollToElement(ReservationPage.closeReservation, driver);
					Wait.wait1Second();
					ReservationPage.closeReservation.click();
					Wait.wait2Second();
					if (ReservationPage.AlertForTab.isDisplayed()) {

						ReservationPage.AlertForTab_Yes_Btn.click();
					}
					Wait.wait1Second();
					break;
				}
			}
		}

		jse.executeScript("window.scrollBy(0,-300)");
		System.out.println("Scrolled to the reservation");
		WebElement From = driver.findElement(By.xpath(path));
		Point Location = From.getLocation();

		Wait.explicit_wait_xpath(ToPath, driver);
		WebElement To = driver.findElement(By.xpath(ToPath));

		// Using Action class for drag and drop.
		Actions act = new Actions(driver);
		// Dragged and dropped
		act.dragAndDrop(From, To).build().perform();

		Wait.wait2Second();
		if (TapeChart.Rules_Broken_popup.isDisplayed()) {
			Wait.explicit_wait_xpath(OR.Rules_Broken_popup, driver);
			TapeChart.Click_Book_icon_Tapechart.click();
			System.out.println("Rules are broken");
		} else {
			System.out.println("Rules are not broken");
		}
		Wait.wait2Second();
		if (TapeChart.ConfirmChangesButton.isDisplayed()) {
			Wait.explicit_wait_visibilityof_webelement(TapeChart.ConfirmChangesButton, driver);
			TapeChart.ConfirmChangesButton.click();

			try {
				Wait.waitForElementToBeGone(driver, TapeChart.ConfirmChangesButton, 90);
			} catch (Exception e) {
				Utility.app_logs.info("Again Click Confirm changes");
				Wait.explicit_wait_visibilityof_webelement(TapeChart.ConfirmChangesButton, driver);
				TapeChart.ConfirmChangesButton.click();
				Wait.waitForElementToBeGone(driver, TapeChart.ConfirmChangesButton, 90);
			}
		}
		// verify toaster message here
		try {
			// Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message,
			// driver);
			// assertEquals(TapeChart.Toaster_Message.getText(), "Reservation(s)
			// moved Successfully","Reservation does not Move");

			String msg = TapeChart.Toaster_Message.getText();
			tapechartLogger.info(msg);
		} catch (Exception e) {
			System.err.println("Toaster not Displayed");
		}

	}

	public void VerifyGrpResevation_nohandles(WebDriver driver, String RoomNumber) throws InterruptedException {

		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		Wait.wait2Second();
		String path = "//div[@title='" + RoomNumber + "']//following-sibling::div//div[@class='DatesContainer']";

		int size = driver.findElements(By.xpath(path)).size();
		if (size > 1) {
			List<WebElement> hoverElement = driver.findElements(By.xpath(path));
			int before_hover_width = hoverElement.get(size - 1).getSize().getWidth();

			Actions builder = new Actions(driver);
			builder.moveToElement(hoverElement.get(size - 1)).perform();
			int after_hover_width = hoverElement.get(size - 1).getSize().getWidth();
			assertEquals(after_hover_width, before_hover_width, "handles appear on hover");

		}

		else {

			WebElement hoverElement = driver.findElement(By.xpath(path));
			int before_hover_width = hoverElement.getSize().getWidth();

			Actions builder = new Actions(driver);
			builder.moveToElement(hoverElement).perform();
			int after_hover_width = hoverElement.getSize().getWidth();
			assertEquals(after_hover_width, before_hover_width, "handles t appear on hover");

		}

	}

	public void VerifyOutStatusRoomClass(WebDriver driver) {

		String path = "//div[@title='501']//following-sibling::div//span/div/div/div/div/div[text()='Out']";
		WebElement find_room = driver.findElement(By.xpath(path));
		Wait.explicit_wait_visibilityof_webelement(find_room, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", find_room);
		Wait.explicit_wait_visibilityof_webelement(find_room, driver);
		jse.executeScript("window.scrollBy(0,-300)");
		Wait.explicit_wait_visibilityof_webelement(find_room, driver);
		assertEquals(find_room.getText(), "Out", "Out room does not find in tapechart");

	}

	public void ClickUnassignedButton(WebDriver driver) throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Utility.ScrollToElement(TapeChart.Unassigned_Button, driver);
		TapeChart.Unassigned_Button.click();
		assertTrue(TapeChart.UnassignedList.isDisplayed(), "Failed:List not show");
	}

	public void MoveUnassignedReservation(WebDriver driver, String RoomClass) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		int temp = 1;
		Wait.wait2Second();
		String UnassignedRoom = "(//div[@class='ir-tc-parking-lot-card clearfix Confirmed']//following-sibling::div[@class='panel panel-danger'])[last()]";
		Random rand = new Random();
		int low = 357;
		int high = 450;
		int result = rand.nextInt(high - low) + low;
		int nextRoom = (result) + 1;
		String NextRoom = Integer.toString(nextRoom);
		String ToPath = "//div[@title='" + NextRoom
				+ "']//following-sibling::div//div[@class='tapechartdatecell noResAvail'][" + (temp + 1) + "]";
		Utility.app_logs.info(ToPath);
		String ReservationName = driver.findElement(By.xpath(ToPath)).getText();
		if (RoomClass.equals("Double Bed Room"))
			RoomClass = "DBR";
		for (int i = 0; i < 10; i++) {
			if (ReservationName.contains("Available")) {
				System.out.println("if");
				break;
			} else {

				nextRoom = Integer.parseInt(NextRoom) + 1;
				NextRoom = Integer.toString(nextRoom);
			}
		}

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(UnassignedRoom)));
		System.out.println("Path here:");

		jse.executeScript("window.scrollBy(0,-300)");
		WebElement From = driver.findElement(By.xpath(UnassignedRoom));
		Point Location = From.getLocation();

		Wait.explicit_wait_xpath(ToPath, driver);
		WebElement To = driver.findElement(By.xpath(ToPath));

		// Using Action class for drag and drop.
		Actions act = new Actions(driver);
		// Dragged and dropped
		act.dragAndDrop(From, To).build().perform();

		Wait.wait2Second();
		if (TapeChart.Rules_Broken_popup.isDisplayed()) {
			Wait.explicit_wait_xpath(OR.Rules_Broken_popup, driver);
			TapeChart.Click_Book_icon_Tapechart.click();
			System.out.println("Rules are broken");
		} else {
			System.out.println("Rules are not broken");
		}
		// Utility.ElementFinderUntillNotShow(By.xpath(OR.ConfirmChangesButton),
		// driver);
		// Wait.waitUntilPresenceOfElementLocated(OR.ConfirmChangesButton);
		Wait.explicit_wait_visibilityof_webelement_120(TapeChart.ConfirmChangesButton, driver);
		TapeChart.ConfirmChangesButton.click();
		Utility.app_logs.info("Click Confirm Changes Button");
		try {
			Wait.waitForElementToBeGone(driver, TapeChart.ConfirmChangesButton, 30);
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(TapeChart.ConfirmChangesButton, driver);
			TapeChart.ConfirmChangesButton.click();
			Utility.app_logs.info("Again Click Confirm Changes Button");
			Wait.waitForElementToBeGone(driver, TapeChart.ConfirmChangesButton, 30);
		}
		// verify toaster message here
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
		assertEquals(TapeChart.Toaster_Message.getText(), "Reservation has been updated successfully",
				"Reservation does not Move");

	}

	public void MoveReserFromAssignToUnassign(WebDriver driver, String RoomClass) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		int temp = 1;
		Wait.wait2Second();
		String UnassignedRoom = "(//div[@class='panel panel-danger'])[1]";
		Random rand = new Random();
		int low = 357;
		int high = 450;
		int result = rand.nextInt(high - low) + low;
		int nextRoom = (result) + 1;
		String NextRoom = Integer.toString(nextRoom);
		String ToPath = "//div[@title='" + NextRoom
				+ "']//following-sibling::div//div[@class='tapechartdatecell noResAvail'][" + (temp + 1) + "]";

		String ReservationName = driver.findElement(By.xpath(ToPath)).getText();
		if (RoomClass.equals("Double Bed Room"))
			RoomClass = "DBR";
		for (int i = 0; i < 10; i++) {
			if (ReservationName.contains("Available")) {
				System.out.println("if");
				break;
			} else {

				nextRoom = Integer.parseInt(NextRoom) + 1;
				NextRoom = Integer.toString(nextRoom);
			}
		}

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(UnassignedRoom)));
		System.out.println("Path here:");

		jse.executeScript("window.scrollBy(0,-300)");
		WebElement From = driver.findElement(By.xpath(UnassignedRoom));
		Point Location = From.getLocation();

		Wait.explicit_wait_xpath(ToPath, driver);
		WebElement To = driver.findElement(By.xpath(ToPath));

		// Using Action class for drag and drop.
		Actions act = new Actions(driver);
		// Dragged and dropped
		act.dragAndDrop(From, To).build().perform();

		Wait.wait2Second();
		if (TapeChart.Rules_Broken_popup.isDisplayed()) {
			Wait.explicit_wait_xpath(OR.Rules_Broken_popup, driver);
			TapeChart.Click_Book_icon_Tapechart.click();
			System.out.println("Rules are broken");
		} else {
			System.out.println("Rules are not broken");
		}
		Utility.ElementFinderUntillNotShow(By.xpath(OR.ConfirmChangesButton), driver);
		Wait.waitUntilPresenceOfElementLocated(OR.ConfirmChangesButton, driver);
		TapeChart.ConfirmChangesButton.click();

		// verify toaster message here
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
		assertEquals(TapeChart.Toaster_Message.getText(), "Reservation has been updated successfully",
				"Reservation does not Move");

	}

	public void Verify_RoomClass_NegetiveCase(WebDriver driver, String RoomClass) throws InterruptedException {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String RoomName = RoomClass.split(" : ")[0];
		String RoomNumber = RoomClass.split(" : ")[1];
		if (RoomName.equals("Double Bed Room"))
			RoomName = "DBR";
		Wait.wait2Second();
		String path = "(//div[@class='row ratesrow']//div[text()='" + RoomName + "']/../../div/div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer'])";
		if (driver.findElements(By.xpath(path)).size() < 3) {
			path = "(//div[@class='row ratesrow']//div[text()='" + RoomName + "']/../../div/div[@title='" + RoomNumber
					+ "']//following-sibling::div//div[@class='DatesContainer'])[1]";
		} else {
			path = "(//div[@class='row ratesrow']//div[text()='" + RoomName + "']/../../div/div[@title='" + RoomNumber
					+ "']//following-sibling::div//div[@class='DatesContainer'])[2]";
		}

		WebElement ele = driver.findElement(By.xpath(path));
		jse.executeScript("arguments[0].scrollIntoView(true);", ele);

		WebElement parent = ele.findElement(By.xpath(".."));
		System.out.println(parent.getText());
		assertTrue(!parent.getText().equals("Out"), "Failed: RoomMain_Item Found in Current Date");
	}

	public String getNextDate(int Day) {
		SimpleDateFormat format = new SimpleDateFormat("dd");
		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, Day);
		int a = Integer.parseInt(format.format(calendar.getTime()));
		if (a < 10) {
			format = new SimpleDateFormat("d");
		}
		return format.format(calendar.getTime());
	}

	public String getNextDate(int Day, String formateStr) {
		SimpleDateFormat format = new SimpleDateFormat(formateStr);
		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, Day);
		return format.format(calendar.getTime());
	}

	public ArrayList<String> searchWithGivenEndDate(WebDriver driver, int Day) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		// driver.navigate().refresh();
		// Wait.waitUntilPresenceOfElementLocated(OR.Select_Arrival_Date,
		// driver);
		// TapeChart.Select_Arrival_Date.click();
		// TapeChart.Click_Today.click();
		// Wait.wait2Second();
		Wait.waitUntilPresenceOfElementLocated(OR.Select_DepartDate, driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.Select_DepartDate, driver);
		Utility.ScrollToElement(TapeChart.Select_DepartDate, driver);
		TapeChart.Select_DepartDate.click();
		Wait.wait2Second();
		if (Integer.parseInt(getNextDate(0)) > Integer.parseInt(getNextDate(Day))) {
			driver.findElement(By.xpath("//td[@class='new day'][text()='" + getNextDate(Day) + "']")).click();
		} else {

			driver.findElement(By.xpath("//td[@class='day'][text()='" + getNextDate(Day) + "']")).click();
		}
		tapechartLogger.info("Depart Date Changed in TapChart " + Day + " Days Added");
		test_steps.add("Depart Date Changed in TapChart " + Day + " Days Added");

		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", TapeChart.Click_Search_TapeChart);
		tapechartLogger.info("Search Button Clicked");
		test_steps.add("Search Button Clicked");
		return test_steps;
	}

	public ArrayList<String> searchWithRackRate(WebDriver driver, String TapeChartAdults, String RackRate)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Select_Arrival_Date, driver);
		TapeChart.Select_Arrival_Date.click();
		TapeChart.Click_Today.click();
		Wait.wait2Second();
		TapeChart.Enter_Adults_Tapehchart.clear();
		TapeChart.Enter_Adults_Tapehchart.sendKeys(TapeChartAdults);

		Wait.explicit_wait_xpath(OR.Click_Tapechart_RackRate, driver);
		TapeChart.Click_Tapechart_RackRate.click();
		Wait.explicit_wait_xpath("//div[@id='tapeChartSearch']//ul[@role='menu']/li/a/span[.='" + RackRate + "']",
				driver);
		driver.findElement(By.xpath("//div[@id='tapeChartSearch']//ul[@role='menu']/li/a/span[.='" + RackRate + "']"))
				.click();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", TapeChart.Click_Search_TapeChart);
		tapechartLogger.info("Search Button Clicked");
		test_steps.add("Search Button Clicked");
		return test_steps;
	}

	public ArrayList<String> searchWithGivenStartDate(WebDriver driver, int Day, String TapeChartAdults)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Select_Arrival_Date, driver);
		TapeChart.Select_Arrival_Date.click();
		driver.findElement(By.xpath("//td[@class='day'][text()='" + getNextDate(Day) + "']")).click();

		tapechartLogger.info("Start Date Changed in TapChart " + Day + " Days Added");
		test_steps.add("Start Date Changed in TapChart " + Day + " Days Added");
		TapeChart.Enter_Adults_Tapehchart.clear();
		TapeChart.Enter_Adults_Tapehchart.sendKeys(TapeChartAdults);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", TapeChart.Click_Search_TapeChart);
		tapechartLogger.info("Search Button Clicked");
		test_steps.add("Search Button Clicked");
		return test_steps;
	}

	public void VerifyEditRateValue_TapeChart(WebDriver driver, String RateValue, String RoomClassAbb)
			throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		String RoomClassRatePath = "(//div[text()='" + RoomClassAbb
				+ "' and contains(@class,'roomclassname')]//..//div//div)[2]";
		System.out.println(RoomClassRatePath);

		List<WebElement> RoomRate = driver.findElements(By.xpath(RoomClassRatePath));
		Wait.explicit_wait_visibilityof_webelement(RoomRate.get(0), driver);
		Utility.ScrollToElement(RoomRate.get(0), driver);
		String NewSetRate = RoomRate.get(0).getText();
		System.out.println("RateValuee:" + NewSetRate + "Added rate Valye:" + RateValue);
		assertTrue(NewSetRate.equals("$" + RateValue), "Edit Rate Value didn't updated");
	}

	public void VerifyEditRateValue_TapeChartForAllPersons(WebDriver driver, ArrayList<Integer> RateValue,
			String RoomClassAbb, int PersonsMaxNumber, int AdultMaxNumber, int ChildrenMaxNumber, String RatepromoCode)
			throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);

		// Adults Value Check

		String MaxAdults;
		String MaxChildren;
		String NewSetRate = "";
		int rate = 0;
		String NewRateValue;

		if (AdultMaxNumber > 0 && ChildrenMaxNumber == 0) {

			for (int i = 0; i < AdultMaxNumber; i++) {
				MaxAdults = Integer.toString(i + 1);
				MaxChildren = Integer.toString(ChildrenMaxNumber);
				TapeChartSearch_Test(driver, MaxAdults, MaxChildren, RatepromoCode);
				String RoomClassRatePath = "(//div[text()='" + RoomClassAbb
						+ "' and contains(@class,'roomclassname')]//..//div//div)[2]";
				// List<WebElement> RoomRate =
				// driver.findElements(By.xpath(RoomClassRatePath));
				WebElement RoomRate = driver.findElement(By.xpath(RoomClassRatePath));

				if (i < 4) {
					System.out.println("Index:" + i);

					Wait.explicit_wait_visibilityof_webelement(RoomRate, driver);
					Utility.ScrollToElement(RoomRate, driver);
					NewSetRate = RoomRate.getText();
					System.out.println("Excpected:" + NewSetRate + "Calculated:" + RateValue.get(i));

					assertTrue(NewSetRate.equals("$" + RateValue.get(i)), "Edit Rate Value didn't updated");
				}

				else {
					// int rate = 0;
					// String NewRateValue;
					NewSetRate = RoomRate.getText();
					rate = RateValue.get(3) + ((RateValue.get(4)) * (i - 3));
					NewRateValue = Integer.toString(rate);
					// System.out.println("Excpected:" + NewSetRate +
					// "Calculated:" + NewRateValue);

					assertTrue(NewSetRate.equals("$" + NewRateValue), "Edit Rate Value didn't updated");

				}
			}
		}

		// Children Value check
		else if (AdultMaxNumber == 0 && ChildrenMaxNumber > 0) {
			System.out.println(" children:" + ChildrenMaxNumber);

			for (int i = 5; i <= ChildrenMaxNumber; i++) {
				MaxAdults = Integer.toString(AdultMaxNumber);
				MaxChildren = Integer.toString(i);
				TapeChartSearch_Test(driver, MaxAdults, MaxChildren, RatepromoCode);
				String RoomClassRatePath = "(//div[text()='" + RoomClassAbb
						+ "' and contains(@class,'roomclassname')]//..//div//div)[2]";
				WebElement RoomRate = driver.findElement(By.xpath(RoomClassRatePath));
				NewSetRate = RoomRate.getText();

				if (i < 9) {
					rate = RateValue.get(i) + RateValue.get(3);
					NewRateValue = Integer.toString(rate);
					// System.out.println("Excpected:" + NewSetRate +
					// "Calculated:" + NewRateValue);

					assertTrue(NewSetRate.equals("$" + NewRateValue), "Edit Rat Value didn't updated");
				} else {

					rate = RateValue.get(3) + RateValue.get(RateValue.size() - 2) + ((RateValue.get(9)) * (i - 8));
					NewRateValue = Integer.toString(rate);
					// System.out.println("1st:" + RateValue.get(3) + "2nd" +
					// RateValue.get(RateValue.size() - 2) + "3rd:"
					// + ((RateValue.get(9)) * (i - 8)));
					// System.out.println("Excpected:" + NewSetRate +
					// "Calculated:" + NewRateValue);

					assertTrue(NewSetRate.equals("$" + NewRateValue), "Edit Rat Value didn't updated");

				}
			}

		}

	}

	public void TapeChartSearch_Test(WebDriver driver, String TapeChartAdults, String TapeChartChildrens,
			String PromoCode) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Select_Arrival_Date, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", TapeChart.Select_Arrival_Date);
		jse.executeScript("arguments[0].click();", TapeChart.Click_Today);
		TapeChart.Enter_Adults_Tapehchart.clear();
		TapeChart.Enter_Children_Tapechart.clear();
		TapeChart.Enter_Adults_Tapehchart.sendKeys(TapeChartAdults);
		TapeChart.Enter_Children_Tapechart.sendKeys(TapeChartChildrens);
		TapeChart.Enter_promoCode_Tapechart.clear();
		TapeChart.Enter_promoCode_Tapechart.sendKeys(PromoCode);
		TapeChart.Click_Search_TapeChart.click();
		Wait.wait2Second();

	}

	public ArrayList<String> searchWithGivenStartDate(WebDriver driver, int Day) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		// driver.navigate().refresh();
		Wait.waitUntilPresenceOfElementLocated(OR.Select_Arrival_Date, driver);
		TapeChart.Select_Arrival_Date.click();
		// TapeChart.Click_Today.click();
		Wait.wait2Second();
		// Wait.waitUntilPresenceOfElementLocated(OR.Select_DepartDate, driver);
		// Wait.explicit_wait_visibilityof_webelement(TapeChart.Select_DepartDate,
		// driver);
		// Utility.ScrollToElement(TapeChart.Select_DepartDate, driver);
		// TapeChart.Select_DepartDate.click();
		// Wait.wait2Second();
		driver.findElement(By.xpath("//td[@class='day'][text()='" + getNextDate(Day) + "']")).click();

		tapechartLogger.info("Arival Date Changed in TapChart " + Day + " Days Added");
		test_steps.add("Arival Date Changed in TapChart " + Day + " Days Added");
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", TapeChart.Click_Search_TapeChart);
		tapechartLogger.info("Search Button Clicked");
		test_steps.add("Search Button Clicked");
		return test_steps;
	}

	public void verifyRoomSlotFree(WebDriver driver, String RoomClass, String RoomNumber) {
		String path = "//div[contains(text(),'" + RoomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer']";
		WebElement ele = driver.findElement(By.xpath(path));
		WebElement parent = ele.findElement(By.xpath(".."));
		System.out.println(parent.getText());

		assertEquals(parent.getText(), "Available", "Failed: not moved");
		tapechartLogger.info("Found Available");
	}

	public String GetNumberofRoomsAvaialble(WebDriver driver) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", TapeChart.TapeChartDay1Button);
		String NumberofRoomsAvail = driver
				.findElement(By
						.xpath("//div[@class='fixedLegend']//div[contains(@data-bind,'getElement: tapeChartConsolidatedAvailabilityAndOccupancy')]//div[@class='ir-tc-absoluteCenter']"))
				.getText();
		return NumberofRoomsAvail;
	}

	public String GetOccupancy(WebDriver driver) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", TapeChart.TapeChartDay1Button);
		String Occupancy = driver
				.findElement(By
						.xpath("//div[@class='fixedLegend']//div[contains(@data-bind,'getElement: tapeChartConsolidatedAvailabilityAndOccupancy')]//div[@class='row occupercentrow']//div[@class='ir-tc-absoluteCenter']"))
				.getText();
		return Occupancy;
	}

	public ArrayList<String> VerifyRoomsAvailableOccupancy(WebDriver driver, String RoomsAvailableTapechart,
			String OccupancyTapechart, ArrayList<String> test_steps) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", TapeChart.TapeChartDay1Button);
		String Occupancy = driver
				.findElement(By
						.xpath("//div[@class='fixedLegend']//div[contains(@data-bind,'getElement: tapeChartConsolidatedAvailabilityAndOccupancy')]//div[@class='row occupercentrow']//div[@class='ir-tc-absoluteCenter']"))
				.getText();
		String NumberofRoomsAvail = driver
				.findElement(By
						.xpath("//div[@class='fixedLegend']//div[contains(@data-bind,'getElement: tapeChartConsolidatedAvailabilityAndOccupancy')]//div[@class='ir-tc-absoluteCenter']"))
				.getText();
		System.out.println("ROoms New:" + NumberofRoomsAvail + "Before:" + RoomsAvailableTapechart);

		assertTrue(!NumberofRoomsAvail.equals(RoomsAvailableTapechart), "Room Available didn't increase by 1");
		test_steps.add("Rooms Available were : " + RoomsAvailableTapechart + " Changed to : " + NumberofRoomsAvail
				+ " increased By One in TapeChart");
		System.out.println("Occ New:" + Occupancy + "before:" + OccupancyTapechart);
		int RoomsAfter = Integer.parseInt(NumberofRoomsAvail);
		int RoomsBefore = Integer.parseInt(RoomsAvailableTapechart);

		if ((RoomsAfter - RoomsBefore) >= 100) {

			assertTrue(!Occupancy.equals(OccupancyTapechart), "Occupancy in % isn't reflecting the change");
			test_steps.add("Occupancy were : " + OccupancyTapechart + " Changed to : " + Occupancy
					+ " Increased By 1% in TapeChart");
		}
		return test_steps;

	}

	public void clickOneDay(WebDriver driver) throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.WaitForElement(driver, OR.click1Day);
		TapeChart.click1Day.click();

	}

	public void getUnassignedTapechartResCount(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait3Second();
		// Wait.WaitForElement(driver, OR.getUnsassignedResCountTapechart);
		String unassignedResCountTP = TapeChart.getUnsassignedResCountTapechart.getText();
		String unassignedTapechart1 = unassignedResCountTP.replaceAll("[^a-zA-Z0-9]", "");
		// unassignedResCountTapechart=tapechartUnassigned1.replaceAll("[]",
		// "");
		unassignedResCountTapechart = unassignedTapechart1.replaceAll("UNASSIGNED", "");
		tapechartLogger.info("Unassigned Reservation from TapechartCount before creating the reservation: "
				+ unassignedResCountTapechart);
		test_steps.add("Unassigned Reservation from TapechartCount before creating the reservation: "
				+ unassignedResCountTapechart);
	}

	public void getUnassignedResCountTapechartAfterCreatingRes(WebDriver driver, ArrayList test_steps)
			throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait10Second();
		Wait.WaitForElement(driver, OR.getUnsassignedResCountTapechart);
		String unassignedResCountTP1 = TapeChart.getUnsassignedResCountTapechart.getText();
		String unassignedTapechart2 = unassignedResCountTP1.replaceAll("[^a-zA-Z0-9]", "");
		unassignedResCountTapechartAfterCreatingRes = unassignedTapechart2.replaceAll("UNASSIGNED", "");
		tapechartLogger.info("Unassigned Reservation from TapechartCount after creating the reservation: "
				+ unassignedResCountTapechartAfterCreatingRes);
		test_steps.add("Unassigned Reservation from TapechartCount after creating the reservation: "
				+ unassignedResCountTapechartAfterCreatingRes);
		Wait.wait5Second();

	}

	public void getUnassignedResGuestNameTapechart(WebDriver driver, String name) throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);

		TapeChart.getUnsassignedResCountTapechart.click();

		Wait.WaitForElement(driver, OR.getUnsassignedResTapechart);

		List<WebElement> unassignedReservations = TapeChart.getUnsassignedResTapechart;

		Iterator<WebElement> i = unassignedReservations.iterator();

		while (i.hasNext()) {

			row = i.next().getText();
			if (row.equalsIgnoreCase(name)) {
				System.out.println("***********unassigned guest name****" + row);
				unassignedGuestNameTapechart = row;
			}
		}
	}

	public void tapechartUnassignedReservationCountValidations(WebDriver driver, ArrayList test_steps) {

		if (Integer.parseInt(unassignedResCountTapechartAfterCreatingRes) > Integer
				.parseInt(unassignedResCountTapechart)) {
			tapechartLogger.info("Validated Unassigned Reservation Count in Tapechart");
			test_steps.add("Validated Unassigned Reservation Count in Tapechart");
		} else {
			Tapechart.tapechartLogger.info("Unassigened reservation count is not increased in tape chart");
			test_steps.add("Unassigened reservation count is not increased in tape chart");
		}
	}

	public void tapechartUnassignedReservationGuestNameValidation(WebDriver driver, String name, ArrayList test_steps)
			throws InterruptedException {
		Assert.assertEquals(name, unassignedGuestNameTapechart);
		tapechartLogger.info("Validated Tapechart Unassigned Reservation ");
		test_steps.add("Validated Tapechart Unassigned Reservation");
		tapechartLogger.info("System successfully validated Room class for unassigned Reservation");
	}

	public void click_UnassigenedReservationCount(WebDriver driver, ArrayList test_steps, String res)
			throws InterruptedException {

		int count = driver.findElements(By.xpath("//div[contains(text(),'Rooms')]/../following-sibling::div")).size();
		String text = "dateheadertoday";
		String temp;
		int value = 0;
		for (int i = 1; i <= count; i++) {
			temp = driver
					.findElement(By.xpath("//div[contains(text(),'Rooms')]/../following-sibling::div/div[" + i + "]"))
					.getAttribute("class");
			if (temp.contains(text)) {
				value = i;
				break;
			}
		}
		String unassigned = "//div[contains(text(),'Unassigned')]/following-sibling::div/div[" + value + "]";
		Wait.wait3Second();
		int unassignedCount = Integer.parseInt(driver.findElement(By.xpath(unassigned)).getText());
		test_steps.add("numer of unassigned for today are : " + unassignedCount);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(unassigned)));

		jse.executeScript("arguments[0].click();", driver.findElement(By.xpath(unassigned)));

		/*
		 * Wait.wait3Second(); Wait.WaitForElement(driver, unassigned);
		 * driver.findElement(By.xpath(unassigned)).click();
		 */
		test_steps.add("clicking on unassigned count");
		Wait.wait3Second();
		Wait.WaitForElement(driver, "//span[@data-bind='text: ConfirmationNumber']");
		int tempcount = driver.findElements(By.xpath("//span[@data-bind='text: ConfirmationNumber']")).size();
		if (unassignedCount == tempcount) {
			test_steps.add("number of unassigned for today are and unassigned search are equal " + unassignedCount);
		} else {
			test_steps.add("number of unassigned for today are and unassigned search are not equal " + unassignedCount);
		}

		String verifyRes = "//table/tbody/tr/td/span[contains(text(),'" + res.trim() + "')]";
		if (driver.findElements(By.xpath(verifyRes)).size() > 0) {
			test_steps.add("Reservation is found in unassigned search : " + res);
		} else {
			test_steps.add("Reservation is not found in unassigned search : " + res);
		}
	}

	public void validate_TapeChartUnassigned(ArrayList test_steps, int before, int after) {
		if (before == (after - 1)) {
			test_steps.add("before count is less one than after count");
		} else {
			test_steps.add("before count is not less one than after count");
		}
	}

	public boolean verify_Unassigned(WebDriver driver, ArrayList test_steps) throws Exception {
		String unassigned = "//div[contains(text(),' Unassigned')]";
		Wait.wait5Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(unassigned)));
		jse.executeScript("window.scrollBy(0,-400)", "");

		boolean flag = true;
		if (driver.findElements(By.xpath(unassigned)).size() > 0) {

		} else {
			flag = false;
		}
		return flag;
	}

	public ArrayList<String> TapeChartSearch_Checkin(WebDriver driver, String CheckinDate, String Adults,
			String Children, String RatePlan, ArrayList<String> steps) throws InterruptedException, ParseException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.TapeChart_CheckIn, driver);
		TapeChart.TapeChart_CheckIn.sendKeys(CheckinDate);
		assertTrue(TapeChart.TapeChart_CheckIn.getAttribute("value").contains(CheckinDate), "Failed: CheckIn");
		steps.add("Enter Checking Date  : " + CheckinDate);
		tapechartLogger.info("Enter Checking Date  : " + CheckinDate);
		TapeChart.Enter_Adults_Tapehchart.clear();
		TapeChart.Enter_Adults_Tapehchart.sendKeys(Adults);
		assertTrue(TapeChart.Enter_Adults_Tapehchart.getAttribute("value").contains(Adults), "Failed: Adult set");
		steps.add("Enter Adults  : " + Adults);
		tapechartLogger.info("Enter Adults  : " + Adults);
		TapeChart.Enter_Children_Tapechart.clear();
		TapeChart.Enter_Children_Tapechart.sendKeys(Children);
		steps.add("Enter Children : " + Children);
		tapechartLogger.info("Enter Children : " + Children);
		Wait.explicit_wait_xpath(OR.Click_Tapechart_RackRate, driver);
		TapeChart.Click_Tapechart_RackRate.click();
		String RatePlan_Path = "//div[@id='tapeChartSearch']//ul[@role='menu']//child::span[contains(text(),'"
				+ RatePlan + "')]";
		Wait.explicit_wait_xpath(RatePlan_Path, driver);
		WebElement ratePlan = driver.findElement(By.xpath(RatePlan_Path));
		ratePlan.click();
		steps.add("Selected Rate Plan : " + RatePlan);
		tapechartLogger.info("Selected Rate Plan : " + RatePlan);
		TapeChart.Click_Search_TapeChart.click();
		steps.add("Click Tape Chart search");
		tapechartLogger.info("Click Tape Chart search");
		try {
			Wait.WaitForElement(driver, OR.TapeChartAvailableSlot);
		} catch (Exception e) {
			TapeChart.Click_Search_TapeChart.click();
			tapechartLogger.info("Again Click Search");
			Wait.explicit_wait_visibilityof_webelement(TapeChart.TapeChartAvailableSlot, driver);
		}
		return steps;
	}

	public String GetUnAssignedCount_Today(WebDriver driver, String RoomClass) throws InterruptedException {

		String CellPath = "(//div[contains(text(),'" + RoomClass
				+ "')  and contains(@class,'roomclassname')]//ancestor::div[@class='roomRatesChart']//child::div[@class = 'row unassignedrow']//child::div[@class='ir-tc-absoluteCenter'])[2]";
		Wait.WaitForElement(driver, CellPath);
		WebElement Cell = driver.findElement(By.xpath(CellPath));
		Utility.ScrollToElement(Cell, driver);
		String count = Cell.getText();
		return count;
	}

	public String GetAvailableRoomsCount_Today(WebDriver driver, String RoomClass) throws InterruptedException {

		String CellPath = "(//div[contains(text(),'" + RoomClass
				+ "') and contains(@class,'roomclassname')]//ancestor::div[@class='roomRatesChart']//following-sibling::div[@class = 'row availpercentrow']//child::div[@class='ir-tc-absoluteCenter'])[2]";
		Wait.WaitForElement(driver, CellPath);
		WebElement Cell = driver.findElement(By.xpath(CellPath));
		Utility.ScrollToElement(Cell, driver);
		String count = Cell.getText();
		return count;
	}

	public String GetPercentOccupancy_Today(WebDriver driver, String RoomClass) throws InterruptedException {

		String CellPath = "(//div[contains(text(),'" + RoomClass
				+ "') and contains(@class,'roomclassname')]//ancestor::div[@class='roomRatesChart']//following-sibling::div[@class = 'row occupercentrow']//child::div[@class='ir-tc-absoluteCenter'])[2]";
		Wait.WaitForElement(driver, CellPath);
		WebElement Cell = driver.findElement(By.xpath(CellPath));
		Utility.ScrollToElement(Cell, driver);
		String count = Cell.getText();
		return count;
	}

	public String GetPropertyLevelAvailableRoomsCount_Today(WebDriver driver, String roomClassAbbreviation)
			throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Utility.ScrollToElement(TapeChart.Propertylevel_RoomsAvailable_Today, driver);
		String count = TapeChart.Propertylevel_RoomsAvailable_Today.getText();
		return count;
	}

	public String GetPropertyLevelPercentOccupancy_Today(WebDriver driver, String roomClassAbbreviation)
			throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Utility.ScrollToElement(TapeChart.Propertylevel_PercentOccupancy_Today, driver);
		String count = TapeChart.Propertylevel_PercentOccupancy_Today.getText();
		return count;
	}

	public ArrayList<String> MoveLockReservation(WebDriver driver, String RoomNumber, String RoomClass,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();
		if (RoomClass.equals("Double Bed Room")) {

			RoomClass = "DBR";
			System.out.println(RoomClass);
		}

		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		System.out.println(RoomNumber);
		Wait.wait2Second();
		String path = " ";
		try {
			path = "//div[contains(text(),'" + RoomClass

					+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
					+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer']";
		} catch (Exception e) {
			path = "(//div[contains(text(),'" + RoomClass

					+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
					+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer'])[2]";
		}
		String ResWithLockimgString = "//div[text()='" + RoomClass
				+ "']//ancestor::div[@class='roomRatesChart']//div[@class='row roomsrow']//div//span[text()='"
				+ RoomNumber
				+ "']//..//following-sibling::div//span[@class='ir-tce-mainResCell']//div//div//div//div//div[contains(@class,'reservation-lock-white')]";
		int nextRoom = Integer.parseInt(RoomNumber) + 1;
		String NextRoom = Integer.toString(nextRoom);
		String ToPath = "//div[contains(text(),'" + RoomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div/div[@title='" + NextRoom
				+ "']//following-sibling::div//div[@class='tapechartdatecell Available RackRate']";
		try {
			System.out.println(driver.findElement(By.xpath(ToPath)).isDisplayed());
		} catch (Exception e) {
			for (int i = 0; i <= 10; i++) {
				ToPath = "//div[contains(text(),'" + RoomClass
						+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div/div[@title='"
						+ NextRoom + "']//following-sibling::div//div[@class='DatesContainer']";
				String Reservationpath = "//div[contains(text(),'" + RoomClass
						+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div/div[@title='"
						+ NextRoom
						+ "']//following-sibling::div//div[@class='DatesContainer']//preceding-sibling::div//child::div[@class='guest_display_name_short']";

				String ReservationName = driver.findElement(By.xpath(Reservationpath)).getText();
				if (ReservationName.contains("Available")) {
					break;
				} else {
					nextRoom = Integer.parseInt(NextRoom) + 1;
					NextRoom = Integer.toString(nextRoom);
				}
			}
		}
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].scrollIntoView(true);",
		// driver.findElement(By.xpath(path)));
		Utility.ScrollToElement(driver.findElement(By.xpath(path)), driver);

		WebElement ResWithLockImg = driver.findElement(By.xpath(ResWithLockimgString));
		assertTrue(ResWithLockImg.isDisplayed(), "Lock Img didn't display");

		test_steps.add(" Lock Image RoomClass " + RoomClass + " RoomNumber " + RoomNumber + "is Displayed");
		WebElement From = driver.findElement(By.xpath(path));
		Point Location = From.getLocation();

		Wait.explicit_wait_xpath(ToPath, driver);
		WebElement To = driver.findElement(By.xpath(ToPath));

		// Using Action class for drag and drop.
		Actions act = new Actions(driver);
		// Dragged and dropped
		act.dragAndDrop(From, To).build().perform();

		Wait.wait2Second();
		if (TapeChart.Rules_Broken_popup.isDisplayed()) {
			Wait.explicit_wait_xpath(OR.Rules_Broken_popup, driver);
			TapeChart.Click_Book_icon_Tapechart.click();
			System.out.println("Rules are broken");
		} else {
			System.out.println("Rules are not broken");
		}
		Wait.wait5Second();
		assertTrue(!TapeChart.ConfirmChangesButton.isDisplayed(), "Reservation has been moved ");
		test_steps.add(" Couldn't move lock reservation");
		return test_steps;

	}

	public void getUnassignedResGuestNameTapechart(WebDriver driver) throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);

		TapeChart.getUnsassignedResCountTapechart.click();

		Wait.WaitForElement(driver, OR.getUnsassignedResTapechart);

		List<WebElement> unassignedReservations = TapeChart.getUnsassignedResTapechart;

		Iterator<WebElement> i = unassignedReservations.iterator();

		while (i.hasNext()) {

			row = i.next().getText();

			if (row.equalsIgnoreCase(Reservation.nameGuest)) {

				System.out.println("***********unassigned guest name****" + row);

				unassignedGuestNameTapechart = row;

			}

		}

	}

	public void VerifyNewRule_TapeChart(WebDriver driver, ArrayList<String> test_steps, String RoomClassAbb,
			String RuleName, boolean ruleApplicable) throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String CellPath = "//div[contains(text(),'" + RoomClassAbb
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::"
				+ "div//following-sibling::div[contains(@class,'DatesContainer')]//ancestor::"
				+ "div[contains(@class,'tapechartdatecell Available')]";
		// String
		// CellPath="//div[contains(text(),'\"+RoomClassAbb+\"')]//following-sibling::div/div";
		Wait.explicit_wait_xpath(CellPath, driver);
		WebElement AvailableSlot = driver.findElement(By.xpath(CellPath));
		test_steps.add("Clicked on the CellPath Related to " + RoomClassAbb + " RoomClass");
		Wait.explicit_wait_visibilityof_webelement(AvailableSlot, driver);
		jse.executeScript("arguments[0].scrollIntoView(true);", AvailableSlot);
		try {
			Wait.explicit_wait_visibilityof_webelement(AvailableSlot, driver);
			AvailableSlot.click();
			test_steps.add("AvailableSlot is clickable");
			tapechartLogger.info("AvailableSlot is clickable");

		} catch (Exception e) {
			jse.executeScript("window.scrollBy(0,-300)");
			Wait.explicit_wait_visibilityof_webelement(AvailableSlot, driver);
			AvailableSlot.click();
			test_steps.add("AvailableSlot is clickable in Scroll back 300");
			tapechartLogger.info("AvailableSlot is clickable in Scroll back 300");
		}
		if (ruleApplicable == true) {
			try {
				Wait.wait2Second();
				if (TapeChart.Rules_Broken_popup.isDisplayed()) {
					Wait.explicit_wait_xpath(OR.Rules_Broken_popup, driver);
					tapechartLogger.info("Rules Broken PopUP is  DisPlayed in TapeChart");
					test_steps.add("Rules Broken PopUP is DisPlayed in TapeChart");
					tapechartLogger.info(TapeChart.Rules_Broken_popup.getText());
					assertEquals(TapeChart.Rules_Broken_popup.getText(), "Rules Broken",
							"Rule broken popup is not showing");
					test_steps.add("Verified Rules Broken Popup in TapeChart");

					String finalString = ReservationPage.RuleName_TapeChart.getText();
					assertEquals(finalString, RuleName);
					test_steps.add("NewRule is Available in TapeChart Search With RuleName:  " + finalString);
					tapechartLogger.info("NewRule is Available in TapeChart Search With RuleName:  " + finalString);
					TapeChart.Rule_Broken_Cancel.click();
					test_steps.add("Clicked on Cancel");
				}
			} catch (Exception e) {
				tapechartLogger.info("Steps Skipped");
			}
		} else {
			test_steps.add("Rules Broken PopUP is Not DisPlayed in TapeChart");
			tapechartLogger.info("Rules Broken PopUP is Not DisPlayed in TapeChart");
			tapechartLogger.info("No Rule is Available in TapeChart Search for " + RoomClassAbb
					+ " Class With RuleName:  " + RuleName);
			test_steps.add("No Rule is Available in TapeChart Search for " + RoomClassAbb + " Class With RuleName:  "
					+ RuleName);

			try {
				// modified code
				WebDriverWait wait = new WebDriverWait(driver, 90);
				Wait.WaitForElement(driver, OR.closeReservation);
				Wait.explicit_wait_visibilityof_webelement(TapeChart.closeReservation, driver);
				Wait.WaitForElement(driver, OR.ReservationSaveButton);
				wait.until(ExpectedConditions.elementToBeClickable(TapeChart.closeReservation));
				TapeChart.closeReservation.click();
			} catch (Exception e) {

			}
		}
	}

	public void TapeChartRatePlanSearch(WebDriver driver, ArrayList<String> test_steps, String TapeChartAdults,
			String RatePlan) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Select_Arrival_Date, driver);
		TapeChart.Select_Arrival_Date.click();
		TapeChart.Click_Today.click();
		Wait.wait2Second();
		test_steps.add("Selected Today Date from the Date Picker ");
		TapeChart.Enter_Adults_Tapehchart.clear();
		TapeChart.Enter_Adults_Tapehchart.sendKeys(TapeChartAdults);

		test_steps.add("Enter Adults '" + TapeChartAdults + "'");
		Wait.explicit_wait_visibilityof_webelement(TapeChart.Select_Ratepaln_DropDown, driver);
		TapeChart.Select_Ratepaln_DropDown.click();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String RatePlanList = "//div[@id='tapeChartSearch']//ul[@role='menu']/li/a/span[.='" + RatePlan + "']";
		WebElement CurrentRatePlan = driver.findElement(By.xpath(RatePlanList));
		Wait.explicit_wait_visibilityof_webelement(CurrentRatePlan, driver);
		jse.executeScript("arguments[0].click();", CurrentRatePlan);
		test_steps.add("Selected Rateplan in TapeChart Search :" + RatePlan);
		tapechartLogger.info("Selected Rateplan in TapeChart Search :" + RatePlan);

		jse.executeScript("arguments[0].click();", TapeChart.Click_Search_TapeChart);
		test_steps.add("Clicked on Search in TapeChart");

	}

	public void VerifyNewRate_TapeChart(WebDriver driver, ArrayList<String> test_steps, String RateValue,
			String RoomClassAbb) throws InterruptedException {

		test_steps.add("Checking for the Available RoomClass Rate");
		String CellPath = "//div[contains(text(),'" + RoomClassAbb + "')]//following-sibling::div/div";
		Wait.explicit_wait_xpath(CellPath, driver);
		WebElement CurrentCellPath = driver.findElement(By.xpath(CellPath));
		Utility.ScrollToElement(CurrentCellPath, driver);
		String finalValue = Utility.convertDollarToNormalAmount(driver, CurrentCellPath.getText());
		tapechartLogger
				.info("New Rate is Available in TapeChart Search with BaseAmount of " + CurrentCellPath.getText());
		test_steps.add("New Rate is Available in TapeChart Search with BaseAmount of " + CurrentCellPath.getText());
		assertEquals(RateValue, finalValue);
	}

	public void Verify_CheckedIn_RoomsStatus(WebDriver driver, ArrayList<String> test_steps, List<String> Rooms,
			ArrayList<String> RoomAbbri, String DirtyStatus, String CleanStatus)

	{
		for (int i = 0; i < Rooms.size(); i++) {
			String[] room = Rooms.get(i).split(":");
			String FinalRoom = room[1].trim();
			System.out.println(" Room No: " + FinalRoom);

			String[] abb = RoomAbbri.get(i).split(":");
			String FinalAbb = abb[1].trim();
			System.out.println(" Room Class: " + FinalAbb);

			String path = "//div[@class='roomRatesChart']/div[contains(@class,'row ratesrow')]/div[contains(@class,'roomclassname')][contains(text(),'"
					+ FinalAbb
					+ "')]/ancestor::div[contains(@class,'ratesrow')]//following-sibling::div//div[contains(@class,'roomnumber')][@title='"
					+ FinalRoom + "']/span/span/img[contains(@src,'" + CleanStatus.toLowerCase().trim()
					+ "')]|//div[@class='roomRatesChart']/div[contains(@class,'row ratesrow')]/div[contains(@class,'roomclassname')][contains(text(),'"
					+ FinalAbb
					+ "')]/ancestor::div[contains(@class,'ratesrow')]//following-sibling::div//div[contains(@class,'roomnumber')][@title='"
					+ FinalRoom + "']/span/span/img[contains(@src,'" + DirtyStatus.toLowerCase().trim() + "')]";
			Wait.WaitForElement(driver, path);
			WebElement element = driver.findElement(By.xpath(path));
			assertTrue(element.isEnabled(), "Failed:to Verify Room Status On Tape Chart");
			if ((element.getAttribute("src").toString().toLowerCase().trim()
					.contains(DirtyStatus.toLowerCase().trim()))) {

				test_steps.add("Verified Abb  <b>" + FinalAbb + "</b>" + " Room No: <b>" + FinalRoom
						+ " </b>Status: <b>" + DirtyStatus + " </b>");
				tapechartLogger.info("Room Class " + FinalAbb + " Room No: " + FinalRoom + "Status: " + DirtyStatus);

			} else if ((element.getAttribute("src").toString().toLowerCase().trim()
					.contains(CleanStatus.toLowerCase().trim()))) {
				test_steps.add("Verified Abb  <b>" + FinalAbb + "</b>" + " Room No: <b>" + FinalRoom
						+ " </b>Status: <b>" + CleanStatus + " </b>");
				tapechartLogger.info("Room Class " + FinalAbb + " Room No: " + FinalRoom + "Status: " + CleanStatus);

			}

		}
	}

	public void TapeChart_Search_MRB(WebDriver driver, String CheckinDate, String Adults, ArrayList<String> steps)
			throws InterruptedException, ParseException {

		List<String> checkInDate = Arrays.asList(CheckinDate.split("\\|"));
		List<String> adults = Arrays.asList(Adults.split("\\|"));
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);

		Wait.waitUntilPresenceOfElementLocated(OR.TapeChart_CheckIn, driver);
		TapeChart.TapeChart_CheckIn.clear();
		TapeChart.TapeChart_CheckIn.sendKeys(checkInDate.get(0));
		steps.add("Enter Checking Date  : " + checkInDate.get(0));
		tapechartLogger.info("Enter Checking Date  : " + checkInDate.get(0));
		TapeChart.Enter_Adults_Tapehchart.clear();
		TapeChart.Enter_Adults_Tapehchart.sendKeys(adults.get(0));
		steps.add("Enter Adults  : " + Adults);
		tapechartLogger.info("Enter Adults  : " + adults.get(0));
		TapeChart.Click_Search_TapeChart.click();
		steps.add("Click Tape Chart search");
	}

	public ArrayList<String> VerifyRate_TapeChart(WebDriver driver, String RateValue, String RoomClassAbb,
			String RateName, ArrayList<String> steps) throws InterruptedException {

		String CellPath = "//div[text()='" + RoomClassAbb + "']//following-sibling::div/div";
		Wait.explicit_wait_xpath(CellPath, driver);
		WebElement CurrentCellPath = driver.findElement(By.xpath(CellPath));
		if (LastRoomClassInTapeChart(driver, RoomClassAbb)) {
			Utility.ScrollToElement(CurrentCellPath, driver);
		} else {
			Utility.ScrollToElement(CurrentCellPath, driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-300)");
		}
		String finalValue = Utility.convertDollarToNormalAmount(driver, CurrentCellPath.getText());
		tapechartLogger.info("Verified '" + RateName + "' is Available in TapeChart Search with BaseAmount of "
				+ CurrentCellPath.getText());
		test_steps.add("Verified '" + RateName + "' is Available in TapeChart Search with BaseAmount of "
				+ CurrentCellPath.getText());
		assertEquals(RateValue, finalValue);
		return steps;
	}

	public ArrayList<String> ClickOnRate(WebDriver driver, String RoomClassAbb, ArrayList<String> steps)
			throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		String CellPath = "//div[text()='" + RoomClassAbb + "']//following-sibling::div/div";
		Wait.explicit_wait_xpath(CellPath, driver);
		WebElement CurrentCell = driver.findElement(By.xpath(CellPath));
		Wait.explicit_wait_visibilityof_webelement(CurrentCell, driver);
		Wait.explicit_wait_elementToBeClickable(CurrentCell, driver);
		if (LastRoomClassInTapeChart(driver, RoomClassAbb)) {
			Utility.ScrollToElement(CurrentCell, driver);
		} else {
			Utility.ScrollToElement(CurrentCell, driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-300)");
		}
		CurrentCell.click();
		tapechartLogger.info("Click on the Rate for one of the days against the Room class '" + RoomClassAbb + "'");
		steps.add("Click on the Rate for one of the days against the Room class '" + RoomClassAbb + "'");
		Wait.explicit_wait_visibilityof_webelement(TapeChart.RateDetailPopup_Heading, driver);
		assertTrue(TapeChart.RateDetailPopup_Heading.isDisplayed(), "Failed: 'Rate Detail' popup is not Displayed");
		tapechartLogger.info("Verified 'Rate Detail' popup is Displayed");
		steps.add("Verified 'Rate Detail' popup is Displayed");
		return steps;
	}

	public ArrayList<String> VerifyRateDetailPopup_RateName(WebDriver driver, String rateName, ArrayList<String> steps)
			throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.RateDetailPopup_RateName, driver);
		Utility.ScrollToElement(TapeChart.RateDetailPopup_RateName, driver);
		String value = TapeChart.RateDetailPopup_RateName.getAttribute("value");
		assertEquals(value, rateName, "Failed: Rate Name Missmatched");
		tapechartLogger.info("Verified Rate Name '" + value + "'");
		steps.add("Verified Rate Name '" + value + "'");
		return steps;
	}

	public ArrayList<String> VerifyRateDetailPopup_RatePlan(WebDriver driver, String ratePlan, ArrayList<String> steps)
			throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.RateDetailPopup_RatePlan, driver);
		Utility.ScrollToElement(TapeChart.RateDetailPopup_RatePlan, driver);
		String value = new Select(TapeChart.RateDetailPopup_RatePlan).getFirstSelectedOption().getText();
		assertEquals(value, ratePlan, "Failed: Rate Plan Missmatched");
		tapechartLogger.info("Verified Rate Plan '" + value + "'");
		steps.add("Verified Rate Plan '" + value + "'");
		return steps;
	}

	public ArrayList<String> VerifyRateDetailPopup_RateType(WebDriver driver, String RateType, ArrayList<String> steps)
			throws InterruptedException {
		// TODO Auto-generated method stub
		String RateTypeXpath = "//div[@id='popUpForInnroad']//span[text()='" + RateType
				+ "']//preceding-sibling::input[@type='radio']";
		WebElement RateTypeElement = driver.findElement(By.xpath(RateTypeXpath));
		Wait.explicit_wait_visibilityof_webelement(RateTypeElement, driver);
		Utility.ScrollToElement(RateTypeElement, driver);
		assertEquals(RateTypeElement.isSelected(), true, "Failed: Rate Interval Missmatched");
		tapechartLogger.info("Verified Rate Type '" + RateType + "' is selected");
		steps.add("Verified Rate Type '" + RateType + "' is selected");
		return steps;
	}

	public ArrayList<String> VerifyRateDetailPopup_RateAttributes(WebDriver driver, String rateAttributes,
			ArrayList<String> steps) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.RateDetailPopup_RateAttributes, driver);
		Utility.ScrollToElement(TapeChart.RateDetailPopup_RateAttributes, driver);
		String value = TapeChart.RateDetailPopup_RateAttributes.getAttribute("placeholder");
		assertEquals(value, rateAttributes, "Failed: Rate Attributes Missmatched");
		tapechartLogger.info("Verified Rate Attributes '" + value + "'");
		steps.add("Verified Rate Attributes '" + value + "'");
		return steps;
	}

	public ArrayList<String> VerifyRateDetailPopup_Interval(WebDriver driver, String Interval, ArrayList<String> steps)
			throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.RateDetailPopup_Interval, driver);
		Utility.ScrollToElement(TapeChart.RateDetailPopup_Interval, driver);
		String value = TapeChart.RateDetailPopup_Interval.getAttribute("value");
		assertEquals(value, Interval, "Failed: Rate Interval Missmatched");
		tapechartLogger.info("Verified Interval '" + value + "'");
		steps.add("Verified Interval '" + value + "'");
		return steps;
	}

	public ArrayList<String> VerifyRateDetailPopup_MaxAdults(WebDriver driver, String maxAdults,
			ArrayList<String> steps) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.RateDetailPopup_MaxAdults, driver);
		Utility.ScrollToElement(TapeChart.RateDetailPopup_MaxAdults, driver);
		String value = TapeChart.RateDetailPopup_MaxAdults.getAttribute("value");
		assertEquals(value, maxAdults, "Failed: Rate MaxAdults Missmatched");
		tapechartLogger.info("Verified Max Adults '" + value + "'");
		steps.add("Verified Max Adults '" + value + "'");
		return steps;
	}

	public ArrayList<String> VerifyRateDetailPopup_MaxPersons(WebDriver driver, String maxPersons,
			ArrayList<String> steps) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.RateDetailPopup_MaxPersons, driver);
		Utility.ScrollToElement(TapeChart.RateDetailPopup_MaxPersons, driver);
		String value = TapeChart.RateDetailPopup_MaxPersons.getAttribute("value");
		assertEquals(value, maxPersons, "Failed: Rate MaxPersons Missmatched");
		tapechartLogger.info("Verified Max Persons '" + value + "'");
		steps.add("Verified Max Persons '" + value + "'");
		return steps;
	}

	public ArrayList<String> VerifyRateDetailPopup_BaseAmount(WebDriver driver, String baseAmount,
			ArrayList<String> steps) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.RateDetailPopup_BaseAmount, driver);
		Utility.ScrollToElement(TapeChart.RateDetailPopup_BaseAmount, driver);
		String value = TapeChart.RateDetailPopup_BaseAmount.getAttribute("value");
		assertEquals(value, baseAmount, "Failed: Rate BaseAmount Missmatched");
		tapechartLogger.info("Verified Base Amount '" + value + "'");
		steps.add("Verified Base Amount '" + value + "'");
		return steps;
	}

	public ArrayList<String> VerifyRateDetailPopup_AdditionalAdult(WebDriver driver, String additionalAdult,
			ArrayList<String> steps) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.RateDetailPopup_AdditionalAdult, driver);
		Utility.ScrollToElement(TapeChart.RateDetailPopup_AdditionalAdult, driver);
		String value = TapeChart.RateDetailPopup_AdditionalAdult.getAttribute("value");
		assertEquals(value, additionalAdult, "Failed: Rate Additional Adult Missmatched");
		tapechartLogger.info("Verified Additional Adult '" + value + "'");
		steps.add("Verified Additional Adult '" + value + "'");
		return steps;
	}

	public ArrayList<String> VerifyRateDetailPopup_AdditionalChild(WebDriver driver, String additionalChild,
			ArrayList<String> steps) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.RateDetailPopup_AdditionalChild, driver);
		Utility.ScrollToElement(TapeChart.RateDetailPopup_AdditionalChild, driver);
		String value = TapeChart.RateDetailPopup_AdditionalChild.getAttribute("value");
		assertEquals(value, additionalChild, "Failed: Rate Additional Child Missmatched");
		tapechartLogger.info("Verified Additional Child '" + value + "'");
		steps.add("Verified Additional Child '" + value + "'");
		return steps;
	}

	public ArrayList<String> VerifyRateDetailPopup_DisplayName(WebDriver driver, String displayName,
			ArrayList<String> steps) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.RateDetailPopup_DisplayName, driver);
		Utility.ScrollToElement(TapeChart.RateDetailPopup_DisplayName, driver);
		String value = TapeChart.RateDetailPopup_DisplayName.getAttribute("value");
		assertEquals(value, displayName, "Failed: Rate DisplayName Missmatched");
		tapechartLogger.info("Verified Display Name '" + value + "'");
		steps.add("Verified Display Name '" + value + "'");
		return steps;
	}

	public ArrayList<String> VerifyRateDetailPopup_Policy(WebDriver driver, String Policy, ArrayList<String> steps)
			throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.RateDetailPopup_Policy, driver);
		Utility.ScrollToElement(TapeChart.RateDetailPopup_Policy, driver);
		String value = TapeChart.RateDetailPopup_Policy.getAttribute("value");
		assertEquals(value, Policy, "Failed: Rate Policy Missmatched");
		tapechartLogger.info("Verified Policy '" + value + "'");
		steps.add("Verified Policy '" + value + "'");
		return steps;
	}

	public ArrayList<String> VerifyRateDetailPopup_Description(WebDriver driver, String Description,
			ArrayList<String> steps) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.RateDetailPopup_Description, driver);
		Utility.ScrollToElement(TapeChart.RateDetailPopup_Description, driver);
		String value = TapeChart.RateDetailPopup_Description.getAttribute("value");
		assertEquals(value, Description, "Failed: Rate Description Missmatched");
		tapechartLogger.info("Verified Description '" + value + "'");
		steps.add("Verified Description '" + value + "'");
		return steps;
	}

	public ArrayList<String> VerifyRateDetailPopup_Season(WebDriver driver, String Season, ArrayList<String> steps)
			throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.RateDetailPopup_Season, driver);
		Utility.ScrollToElement(TapeChart.RateDetailPopup_Season, driver);
		String value = TapeChart.RateDetailPopup_Season.getText();
		assertEquals(value, Season, "Failed: Rate Season Missmatched");
		tapechartLogger.info("Verified Season '" + value + "'");
		steps.add("Verified Season '" + value + "'");
		return steps;
	}

	public ArrayList<String> VerifyRateDetailPopup_RoomClass(WebDriver driver, String rateRoomClass,
			ArrayList<String> steps) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.RateDetailPopup_RoomClass, driver);
		Utility.ScrollToElement(TapeChart.RateDetailPopup_RoomClass, driver);
		String value = TapeChart.RateDetailPopup_RoomClass.getText();
		assertEquals(value, rateRoomClass, "Failed: Rate Room Class Missmatched");
		tapechartLogger.info("Verified Room Class '" + value + "'");
		steps.add("Verified Room Class '" + value + "'");
		return steps;
	}

	public ArrayList<String> VerifyRateDetailPopup_Source(WebDriver driver, String Source, ArrayList<String> steps)
			throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.RateDetailPopup_Source, driver);
		Utility.ScrollToElement(TapeChart.RateDetailPopup_Source, driver);
		String value = TapeChart.RateDetailPopup_Source.getText();
		assertEquals(value, Source, "Failed: Rate Source Missmatched");
		tapechartLogger.info("Verified Source '" + value + "'");
		steps.add("Verified Source '" + value + "'");
		return steps;
	}

	public ArrayList<String> ClickRateDetailPopupCancelButton(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.RateDetailPopup_CancelButton, driver);
		Wait.explicit_wait_elementToBeClickable(TapeChart.RateDetailPopup_CancelButton, driver);
		Utility.ScrollToElement(TapeChart.RateDetailPopup_CancelButton, driver);
		TapeChart.RateDetailPopup_CancelButton.click();
		tapechartLogger.info("Click 'Rate Detail' popup 'Cancel' button");
		steps.add("Click 'Rate Detail' popup 'Cancel' button");
		Wait.waitForElementToBeGone(driver, TapeChart.RateDetailPopup_CancelButton, 30);
		return steps;
	}

	public ArrayList<String> VerifyRuleRow(WebDriver driver, String RoomClass, String ruleType, String ruleValue,
			ArrayList<String> steps) throws InterruptedException {
		String xpath = "//div[text()='" + RoomClass
				+ "']//ancestor::div[@class='roomRatesChart']//div[contains(@class,'rulesrow')]/div[1]";
		WebElement RuleTypeElement = driver.findElement(By.xpath(xpath));
		Wait.explicit_wait_visibilityof_webelement(RuleTypeElement, driver);
		Utility.ScrollToElement(RuleTypeElement, driver);
		assertEquals(RuleTypeElement.getText(), ruleType, "Failed : Rule type Missmatched");
		steps.add("Verified Rule Type : " + ruleType);
		tapechartLogger.info("Verified Rule Type : " + ruleType);

		xpath = "//div[text()='" + RoomClass
				+ "']//ancestor::div[@class='roomRatesChart']//div[contains(@class,'ruleactive')]//div[contains(@class,'absoluteCenter')]";
		WebElement RuleValueElement = driver.findElement(By.xpath(xpath));
		Wait.explicit_wait_visibilityof_webelement(RuleValueElement, driver);
		Utility.ScrollToElement(RuleValueElement, driver);
		assertEquals(RuleValueElement.getText(), ruleValue, "Failed : Rule Value Missmatched");
		steps.add("Verified Rule Value : " + ruleValue);
		tapechartLogger.info("Verified Rule Value : " + ruleValue);

		return steps;
	}

	public ArrayList<String> ClickCell(WebDriver driver, String RoomClass, String RoomNumber, ArrayList<String> steps)
			throws InterruptedException {
		String xpath = "//div[text()='" + RoomClass
				+ "'  and contains(@class,'roomclassname')]//parent::*//following-sibling::div/div[@title='"
				+ RoomNumber + "']//following-sibling::div/span/div[contains(@class,'tapechartdatecell')][1]";
		WebElement Cell = driver.findElement(By.xpath(xpath));
		Wait.explicit_wait_visibilityof_webelement(Cell, driver);
		Wait.explicit_wait_elementToBeClickable(Cell, driver);

		if (LastRoomClassInTapeChart(driver, RoomClass)) {
			Utility.ScrollToElement(Cell, driver);
		} else {
			Utility.ScrollToElement(Cell, driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-300)");
		}
		Cell.click();
		steps.add("Click on Cell of Room '" + RoomClass + ":" + RoomNumber);
		tapechartLogger.info("Click on Cell of Room '" + RoomClass + ":" + RoomNumber);
		return steps;
	}

	public ArrayList<String> VerifyRuleBroken(WebDriver driver, String RuleName, String RuleDescription,
			ArrayList<String> steps) throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.WaitForElement(driver, OR.Rule_Broken_PopUp);
		Wait.waitForToasterToBeVisibile(By.xpath(OR.Rule_Broken_PopUp), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Rule_Broken_PopUp), driver);
		assertEquals(TapeChart.Rule_Broken_PopUp.isDisplayed(), true, "Rule broken popup is not Displayed");
		assertEquals(TapeChart.Rule_Broken_PopUp.getText(), "Rules Broken", "Rule broken popup is not showing");
		steps.add("Rule Broken Popup Appeared");
		tapechartLogger.info("Rule Broken Popup Appeared");

		String ruleNamexpath = "//*[@id='rulesBrokenConfirmation']//td[contains(@data-bind,'text: RuleName')]";
		String ruleName = driver.findElement(By.xpath(ruleNamexpath)).getText();
		assertEquals(ruleName, RuleName, "Rule Not Verified, RuleName not matched");
		steps.add("Verified Rule Name '" + RuleName + "'");
		tapechartLogger.info("Verified Rule Name '" + RuleName + "'");
		String RuleDescriptionxpath = "//*[@id='rulesBrokenConfirmation']//td[contains(@data-bind,'text: RuleDescription')]";
		String ruleDesc = driver.findElement(By.xpath(RuleDescriptionxpath)).getText();
		assertEquals(ruleDesc, RuleDescription, "Rule Not Verified, Rule Desc not matched");
		steps.add("Verified Rule Description '" + RuleDescription + "'");
		tapechartLogger.info("Verified Rule Description '" + RuleDescription + "'");

		String Messagexpath = "//span[text()='Do you want to continue?']";
		WebElement Message = driver.findElement(By.xpath(Messagexpath));
		assertEquals(Message.isDisplayed(), true,
				"Rule broken popup Message 'Do you want to continue?' is not displaying");
		steps.add("Rule broken popup Message 'Do you want to continue?' is displaying");
		tapechartLogger.info("Rule broken popup Message 'Do you want to continue?' is displaying");

		assertEquals(TapeChart.Rule_Broken_Cancel.isDisplayed(), true,
				"Rule broken popup Cancel Button is not displaying");
		steps.add("Rule Broken Popup 'Cancel Button' is displaying");
		tapechartLogger.info("Rule Broken Popup 'Cancel Button' is displaying");

		assertEquals(TapeChart.RuleBrokenBook_Btn.isDisplayed(), true,
				"Rule broken popup Book Button is not displaying");
		steps.add("Rule Broken Popup 'Book Button' is displaying");
		tapechartLogger.info("Rule Broken Popup 'Book Button' is displaying");

		return steps;
	}

	public ArrayList<String> ClickBookRuleBroken(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.RuleBrokenBook_Btn, driver);
		Wait.explicit_wait_elementToBeClickable(TapeChart.RuleBrokenBook_Btn, driver);
		Utility.ScrollToElement(TapeChart.RuleBrokenBook_Btn, driver);
		TapeChart.RuleBrokenBook_Btn.click();
		steps.add("Click Rule Broken Popup 'Book Button'");
		tapechartLogger.info("Click Rule Broken Popup 'Book Button'");
		Wait.waitForElementToBeGone(driver, TapeChart.RuleBrokenBook_Btn, 60);
		steps.add("New Reservation Page is opened");
		tapechartLogger.info("New Reservation Page is opened");
		return steps;
	}

	public ArrayList<String> ClickCancelRuleBroken(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.Rule_Broken_Cancel, driver);
		Wait.explicit_wait_elementToBeClickable(TapeChart.Rule_Broken_Cancel, driver);
		Utility.ScrollToElement(TapeChart.Rule_Broken_Cancel, driver);
		TapeChart.Rule_Broken_Cancel.click();
		steps.add("Click Rule Broken Popup 'Cancel Button'");
		tapechartLogger.info("Click Rule Broken Popup 'Cancel Button'");
		Wait.waitForElementToBeGone(driver, TapeChart.Rule_Broken_Cancel, 60);
		return steps;
	}

	// Added by Adnan
	public int ExtendReservation(WebDriver driver, String RoomNumber, String RoomClass, String FullName)
			throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();
		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		Wait.wait2Second();
		String path2 = "//div[text()='" + RoomClass
				+ "'  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer']";
		// Actions actions = new Actions(driver);
		// actions.moveToElement(driver.findElement(By.xpath(path2))).perform();
		String path = "(//div[contains(text(),'" + RoomClass
				+ "') and contains(@class,'roomclassname')]//ancestor::div[@class='roomRatesChart']//div[text()='"
				+ FullName + "']//following::span[@class='ir-tc-rightResHandle'])[1]";
		System.out.println(path);

		int size = driver.findElements(By.xpath(path)).size();
		int preWidth = 0;
		Wait.explicit_wait_xpath(path, driver);
		WebElement ele2 = driver.findElement(By.xpath(path2));
		WebElement ele = driver.findElement(By.xpath(path));
		Utility.app_logs.info(" Location " + ele.getLocation());
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if (LastRoomClassInTapeChart(driver, RoomClass)) {
			Utility.ScrollToElement(ele2, driver);
			jse.executeScript("window.scrollBy(0,200)");

		} else {
			Utility.ScrollToElement(ele2, driver);
			jse.executeScript("window.scrollBy(0,-300)");
		}
		int divWidth = ele2.getSize().getWidth();
		System.out.println("Pre Width : " + divWidth);
		preWidth = divWidth;
		// int divHeight = ele2.getSize().getHeight();
		// Get half of Height
		// int halfHeight = divHeight / 2;
		Actions builder = new Actions(driver);

		Action resizable = builder.moveToElement(ele).clickAndHold().moveByOffset(divWidth, 0).release().build();
		resizable.perform();
		return preWidth;
	}

	// Created By Adhnan
	public void VerifyUnassignedReservationColor(WebDriver driver, String ReservationName, String ArrivalDate,
			String DepartureDate, String RoomClassAbb, ArrayList<String> steps) throws InterruptedException {
		// Verify Reservation
		int index = GetUnassignedReservationIndex(driver, ReservationName, ArrivalDate, DepartureDate, RoomClassAbb);
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		String path = "(" + OR.UnassignedList + ")[" + index + "]//parent::div[contains(@class,'parking-lot-card')]";
		tapechartLogger.info("Res Path is " + path);
		Wait.explicit_wait_xpath(path, driver);
		WebElement res = driver.findElement(By.xpath(path));
		Wait.explicit_wait_visibilityof_webelement_120(res, driver);
		Utility.ScrollToElement(res, driver);
		String Color = res.getCssValue("background-color");
		tapechartLogger.info("Reservation Color : " + Color);
		steps.add("Verified Unassigned Reservation '" + ReservationName + "' Color(" + Color + ") is Green");
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyBlackOutRoomsInTapeChat> ' Description: <Verify the
	 * rooms block in TapeChart> ' Input parameters: < String RoomClassName> '
	 * Return value: ArrayList<String> ' Created By: <Reddy Ponnolu> ' Created
	 * On: <04/05/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> verifyBlackOutRoomsInTapeChat(WebDriver driver, String RoomClassName,
			ArrayList<String> test_steps) throws InterruptedException {
		String BlackOutRoomLine1 = "(//div[text()='" + RoomClassName
				+ "']//ancestor::div[@class='roomRatesChart']//div[contains(@class,'col-xs-11')])[2]";
		String aquifiedCellWithBlackOut = "(//div[text()='" + RoomClassName
				+ "']//ancestor::div[@class='roomRatesChart']"
				+ "//div[contains(@class,'col-xs-11')])[2]//div[contains(@class,'tapechartdatecell BlockedOut')]";
		String BlackOutText = "(//div[text()='" + RoomClassName
				+ "']//ancestor::div[@class='roomRatesChart']//div[contains(@class,'col-xs-11')])[2]//div[contains(@class,'guest_display_name_short')]";

		int cellCountInLine = driver.findElements(By.xpath(BlackOutRoomLine1)).size();
		String cellContentInLine = "null";
		for (int i = 1; i <= cellCountInLine; i++) {
			cellContentInLine = driver.findElement(By.xpath("((//div[text()='" + RoomClassName
					+ "']//ancestor::div[@class='roomRatesChart']//div[contains(@class,'col-xs-11')])[2]//div[contains(@class,'tapechartdatecell BlockedOut')])["
					+ i + "]")).getText();
			System.out.println("cellContentInLine:  " + cellContentInLine);
			if (cellContentInLine.contains("B")) {
				String blackOutTittle = driver.findElement(By.xpath(aquifiedCellWithBlackOut)).getText();
				System.out.println("blackOutTittle :" + blackOutTittle);
				Assert.assertEquals(blackOutTittle, "B", "BlckOutText is not matching");

			}
			if (cellContentInLine.contains("BlockOutRoom")) {
				String blackOutTittle = driver.findElement(By.xpath(aquifiedCellWithBlackOut)).getText();
				System.out.println("blackOutTittle :" + blackOutTittle);
				Assert.assertEquals(blackOutTittle, "BlockOutRoom", "BlckOutText is not matching");

			}
			Wait.WaitForElement(driver, OR.Click_Reservation);
			Wait.waitForElementToBeVisibile(By.xpath(OR.Click_Reservation), driver);
		}

		return test_steps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <MoveReservations> ' Description: <> ' Input parameters: (
	 * Room Number, Room Class Name ) ' Return value: <return type> ( void /
	 * String / ArrayList<String> ...etc ' Created By: <Full name of developer>
	 * ' Created On: <MM/DD/YYYY>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ <MM/DD/YYYY>:<Full name of
	 * developer>: 1.Step modified 2.Step modified
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public String MoveReservations(WebDriver driver, String roomNumber, String roomClass) throws InterruptedException {
		Wait.wait2Second();
		roomNumber = roomNumber.replaceAll("\\s+", "");
		System.out.println(roomNumber);
		Wait.wait2Second();
		String path = "//div[contains(text(),'" + roomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
				+ roomNumber + "']//following-sibling::div//div[@class='DatesContainer']";
		System.out.println("PTah:" + path);
		int nextRoom = Integer.parseInt(roomNumber) + 1;
		String NextRoom = Integer.toString(nextRoom);
		String ToPath = "//div[contains(text(),'" + roomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div/div[@title='" + NextRoom
				+ "']//following-sibling::div/span/div[contains(@class,'tapechartdatecell')][3]";

		System.out.println("To pTah:" + ToPath);
		Utility.ScrollToElement(driver.findElement(By.xpath(path)), driver);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if (LastRoomClassInTapeChart(driver, roomClass)) {
			jse.executeScript("window.scrollBy(0,200)");

		} else {
			jse.executeScript("window.scrollBy(0,-300)");
		}

		System.out.println("Scrolled to the reservation");

		WebElement From = driver.findElement(By.xpath(path));

		Wait.explicit_wait_xpath(ToPath, driver);
		WebElement To = driver.findElement(By.xpath(ToPath));

		// Using Action class for drag and drop.
		Actions act = new Actions(driver);
		// Dragged and dropped
		act.dragAndDrop(From, To).build().perform();

		Actions builder = new Actions(driver);
		Action resizable = builder.moveToElement(From).clickAndHold().moveToElement(To).release().build();

		return NextRoom;
	}

	@Override
	public void verifyOutOfOrder(WebDriver driver, ArrayList<String> test_steps, String roomClassName, String title)
			throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_TapeChart tapeChart = new Elements_TapeChart(driver);
		String color = tapeChart.TapeChart_outIcon.getCssValue("background-color");
		System.out.println(color);
		// Utility.ScrollToTillEndOfPage(driver);
		String TapeChart_RoomClassPath = "//div[@class='roomRatesChart']//div[contains(@class,'roomclassname')][contains(text(),'"
				+ roomClassName + "')]";
		WebElement TapeChart_RoomClassName = driver.findElement(By.xpath(TapeChart_RoomClassPath));
		Utility.ScrollToElement(TapeChart_RoomClassName, driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart_RoomClassName, driver);
		String outElement = "//div[@class='roomRatesChart']/div[contains(@class,'row ratesrow')]/div[contains(@class,'roomclassname')][contains(text(),'"
				+ roomClassName
				+ "')]/ancestor::div[contains(@class,'ratesrow')]//following-sibling::div//div[contains(@class,'roomnumber')][@title='"
				+ title + "']//following-sibling::div//div[contains(@class,'Out')][@title='Out']";
		WebElement outEle = driver.findElement(By.xpath(outElement));
		boolean tapeChatClass = outEle.isDisplayed();
		String backgroundColor = outEle.getCssValue("background-color");
		System.out.println(backgroundColor);

		if (tapeChatClass) {

			Assert.assertTrue(outEle.isDisplayed(), "Failed : to display Out for Room Class " + roomClassName);
			test_steps.add("Out is dispalyed for Room Class " + roomClassName);
			tapechartLogger.info("Out is dispalyed for Room Class " + roomClassName);
			Assert.assertTrue(color.equals(backgroundColor),
					"Failed : to verify background color of Out of Order Room");
			test_steps.add("Out of Order Room Color Matched ");
			tapechartLogger.info("Out of Order Room Color Matched ");
		} else {
			test_steps.add("Failed : to display Out for Room Class " + roomClassName);
			tapechartLogger.info("Failed : to display Out for Room Class " + roomClassName);
			test_steps.add("Failed to  Matched Out of Order Room Color");
			tapechartLogger.info("Failed to  Matched Out of Order Room Color");
		}
	}

	public ArrayList<String> verifyReservationExist(WebDriver driver, String roomNumber, String roomClass,
			String roomClassAbbreviation, String fullName)

			throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<>();
		Wait.wait2Second();
		String rervationPath = "//div[text()='" + roomClassAbbreviation
				+ "' and contains(@class,'roomclassname')]//ancestor::div[@class='roomRatesChart']//div[@title='"
				+ roomNumber + "']//following-sibling::div//div//div[@class='guest_display_name_short']";
		WebElement reservationElement = driver.findElement(By.xpath(rervationPath));
		JavascriptExecutor javaScript = (JavascriptExecutor) driver;
		if (LastRoomClassInTapeChart(driver, roomClass)) {
			Utility.ScrollToElement(reservationElement, driver);
			javaScript.executeScript("window.scrollBy(0,-200)");

		} else {
			Utility.ScrollToElement(reservationElement, driver);
			javaScript.executeScript("window.scrollBy(0,-300)");
			Utility.app_logs.info("Scrolled Back");
		}
		assertEquals(reservationElement.isDisplayed(), true, "Failed: Created reservation is not displaying");
		assertEquals(reservationElement.getText(), fullName, "Failed: Reservation name is mismatching in tape chart");
		testSteps.add("Verify group name with reeservation name");

		return testSteps;
	}

	public ArrayList<String> verifyResevationhandles(WebDriver driver, String roomNumber, String roomClassAbbreviation,
			boolean right, boolean left, ArrayList<String> steps) throws InterruptedException {

		roomNumber = roomNumber.replaceAll("\\s+", "");
		Wait.wait2Second();
		String path = "(//div[@class='row ratesrow']//div[text()='" + roomClassAbbreviation
				+ "']/../../div/div[@title='" + roomNumber
				+ "']//following-sibling::div//div[@class='DatesContainer'])[last()]";
		String leftpath = "//div[@class='row ratesrow']//div[text()='" + roomClassAbbreviation
				+ "']/../../div/div[@title='" + roomNumber
				+ "']//following-sibling::div//div[@class='DatesContainer']//ancestor::span[@class='ir-tce-mainResCell']/span[contains(@class,'left')]";
		String rightpath = "//div[@class='row ratesrow']//div[text()='" + roomClassAbbreviation
				+ "']/../../div/div[@title='" + roomNumber
				+ "']//following-sibling::div//div[@class='DatesContainer']//ancestor::span[@class='ir-tce-mainResCell']/span[contains(@class,'right')]";
		WebElement Room = driver.findElement(By.xpath(path));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if (LastRoomClassInTapeChart(driver, roomClassAbbreviation)) {
			Utility.ScrollToElement(Room, driver);
			jse.executeScript("window.scrollBy(0,-200)");

		} else {
			Utility.ScrollToElement(Room, driver);
			jse.executeScript("window.scrollBy(0,-350)");
		}
		WebElement hoverElement = driver.findElement(By.xpath(path));
		Actions builder = new Actions(driver);
		builder.moveToElement(hoverElement).perform();
		steps.add("Hovers over a Reservation (Room '" + roomClassAbbreviation + ":" + roomNumber + "' )");
		WebElement lefthandle = driver.findElement(By.xpath(leftpath));
		WebElement righthandle = driver.findElement(By.xpath(rightpath));
		if (right) {
			for (int i = 0; i < 5; i++) {
				Utility.app_logs.info(righthandle.getAttribute("style"));
				if (righthandle.getAttribute("style").contains("visible")) {
					break;
				} else {
					Wait.wait2Second();
				}
			}
			assertEquals(righthandle.getAttribute("style"), "visibility: visible;", "right handles not appear");
			steps.add("Right Handle Appeared");
		} else {
			Utility.app_logs.info(righthandle.getAttribute("style"));
			assertEquals(righthandle.getAttribute("style"), "visibility: hidden;", "right handles not appear");
			steps.add("Right Handle not Appeared");
		}
		if (left) {
			for (int i = 0; i < 5; i++) {
				Utility.app_logs.info(lefthandle.getAttribute("style"));
				if (lefthandle.getAttribute("style").contains("visible")) {
					break;
				} else {
					Wait.wait2Second();
				}
			}
			assertEquals(lefthandle.getAttribute("style"), "visibility: visible;", "left handles not appear");
			steps.add("Left Handle Appeared");
		} else {

			Utility.app_logs.info(lefthandle.getAttribute("style"));
			assertEquals(lefthandle.getAttribute("style"), "visibility: hidden;", "left handles not appear");
			steps.add("Left Handle not Appeared");
		}
		return steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <toolTipHeaderVerification> ' Description: <tool tip
	 * header verification> ' Input parameters: <first room number, room class,
	 * guest name, folio total balance ,folio balance, reservation number, room
	 * class abbreviation> ' Return value: <ArrayList> ' Created By: <Adnan
	 * Ghaffar> ' Created On: <05/09/2020>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ <MM/DD/YYYY>:<Full name of
	 * developer>: 1.Step modified 2.Step modified
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> toolTipHeaderVerification(WebDriver driver, String roomNumber, String roomClass,
			String fullName, String folioTotal, String folioBalance, String reservationNumber,
			String roomClassAbbreviation)

			throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<>();
		Wait.wait2Second();
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;

		String path2 = "//div[text()='" + roomClassAbbreviation
				+ "' and contains(@class,'roomclassname')]//ancestor::div[@class='roomRatesChart']//div[@title='"
				+ roomNumber + "']//following-sibling::div//div[text()='" + fullName + "']/../../../..";
		WebElement Room = driver.findElement(By.xpath(path2));
		if (LastRoomClassInTapeChart(driver, roomClassAbbreviation)) {
			Utility.ScrollToElement(Room, driver);
			javaScriptExecutor.executeScript("window.scrollBy(0,-200)");

		} else {
			tapechartLogger.info("In if scroll");
			Utility.ScrollToElement(Room, driver);
			javaScriptExecutor.executeScript("window.scrollBy(0,-350)");
		}
		WebElement hoverElement = driver.findElement(By.xpath(path2));
		Actions builder = new Actions(driver);
		builder.moveToElement(hoverElement).perform();
		testSteps.add("Hovers over a Reservation (Room '" + roomClassAbbreviation + ":" + roomNumber + "' )");

		String namePath = "//div[@role='tooltip']//div[text()='" + fullName + "']";
		String reservationNumberPath = "//div[@role='tooltip']//div[text()='" + fullName
				+ "']/../../following-sibling::span/b";
		String folioTotalPath = "//div[@role='tooltip']//div[text()='" + fullName
				+ "']/../../../../following-sibling::div//span[contains(@id,'FolioTotal')]";
		String folioBalancePath = "//div[@role='tooltip']//div[text()='" + fullName
				+ "']/../../../../following-sibling::div//span[contains(@id,'FolioBalance')]";

		// jse.executeScript("window.scrollBy(0,-300)");
		Wait.WaitForElement(driver, namePath);
		Wait.waitForElementToBeVisibile(By.xpath(namePath), driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(namePath)), driver);
		String foundName = driver.findElement(By.xpath(namePath)).getText();
		assertEquals(foundName, fullName, "Failed Name Missmatched");
		testSteps.add("Successfully Verified Full Name in ToolTip : " + foundName);
		tapechartLogger.info("Successfully Verified Full Name in ToolTip : " + foundName);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(reservationNumberPath)), driver);
		String foundReservationNumber = driver.findElement(By.xpath(reservationNumberPath)).getText();
		assertEquals(foundReservationNumber.trim(), "#" + reservationNumber, "Failed Reservation No Missmatched");
		testSteps.add("Successfully Verified Reservation No in ToolTip : " + foundReservationNumber);
		tapechartLogger.info("Successfully Verified Reservation No in ToolTip : " + foundReservationNumber);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(folioTotalPath)), driver);
		String foundTotal = driver.findElement(By.xpath(folioTotalPath)).getText();

		for (int i = 0; i < 5; i++) {
			if (foundTotal.equals("")) {
				tapechartLogger.info("try " + i + " Trip total null");
				Wait.wait2Second();
				foundTotal = driver.findElement(By.xpath(folioTotalPath)).getText();
			} else {
				break;
			}
		}
		foundTotal = Utility.RemoveDollarandSpaces(driver, foundTotal);
		testSteps.add("Successfully Verified Folio Total in ToolTip : " + foundTotal);
		tapechartLogger.info("Successfully Verified Folio Total in ToolTip : " + foundTotal);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(folioBalancePath)), driver);
		String foundBalance = driver.findElement(By.xpath(folioBalancePath)).getText();
		for (int i = 0; i < 5; i++) {
			if (foundBalance.equals("")) {
				tapechartLogger.info("try " + i + "Folio Balance null");
				Wait.wait2Second();
				foundBalance = driver.findElement(By.xpath(folioBalancePath)).getText();
			} else {
				break;
			}
		}
		foundBalance = Utility.RemoveDollarandSpaces(driver, foundBalance);
		assertEquals(foundBalance, folioBalance, "Failed Folio Balance Missmatched");
		testSteps.add("Successfully Verified Folio Balance in ToolTip : " + foundBalance);
		tapechartLogger.info("Successfully Verified Folio Balance in ToolTip : " + foundBalance);

		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <toolTipVerificationLine> ' Description: <verification of
	 * tool tip> ' Input parameters: <room number, room class name, guest name,
	 * arrival date, depart date , total nights, room index> ' Return value:
	 * <ArrayList> ' Created By: <Adnan Ghaffar> ' Created On: <05/09/2020>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ <MM/DD/YYYY>:<Full name of
	 * developer>: 1.Step modified 2.Step modified
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> toolTipVerificationLine(WebDriver driver, String roomNumber, String roomClass,
			String guestName, String arrivalDate, String departDate, String totalNights, int index)
			throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<>();
		String arivalDatePath = "(//div[@role='tooltip']//div[text()='" + guestName
				+ "']/../../../../../../following-sibling::div//div[contains(@class,'hover-date-month ng-arriveDate')])["
				+ index + "]";
		String departDatePath = "(//div[@role='tooltip']//div[text()='" + guestName
				+ "']/../../../../../../following-sibling::div//div[contains(@class,'hover-date-month ng-departDate')])["
				+ index + "]";
		String totalNightsPath = "(//div[@role='tooltip']//div[text()='" + guestName
				+ "']/../../../../../../following-sibling::div//div[contains(@class,'totalNights')])[" + index + "]";
		String roomClassPath = "(//div[@role='tooltip']//div[text()='" + guestName
				+ "']/../../../../../../following-sibling::div//span[contains(@class,'ir-tc-roomClass')])[" + index
				+ "]";
		String roomNoPath = "(//div[@role='tooltip']//div[text()='" + guestName
				+ "']/../../../../../../following-sibling::div//span[contains(@class,'ir-tc-roomClass')]/../following-sibling::div/span)["
				+ index + "]";

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(arivalDatePath)), driver);
		String foundArivalDate = driver.findElement(By.xpath(arivalDatePath)).getText();
		assertEquals(Utility.parseDate(foundArivalDate, "MMM dd, yyyy", "MMM dd, yyyy"), arrivalDate,
				"Failed Arival Date Missmatched");
		test_steps.add("Successfully Verified Arival Date in ToolTip : " + foundArivalDate);
		tapechartLogger.info("Successfully Verified Arival Date in ToolTip : " + foundArivalDate);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(departDatePath)), driver);
		String foundDepartDate = driver.findElement(By.xpath(departDatePath)).getText();
		assertEquals(Utility.parseDate(foundDepartDate, "MMM dd, yyyy", "MMM dd, yyyy"), departDate,
				"Failed Depart Date Missmatched");
		test_steps.add("Successfully Verified Depart Date in ToolTip : " + foundDepartDate);
		tapechartLogger.info("Successfully Verified Depart Date in ToolTip : " + foundDepartDate);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(totalNightsPath)), driver);
		String foundNights = driver.findElement(By.xpath(totalNightsPath)).getText();
		assertEquals(foundNights, totalNights, "Failed Total Nights Missmatched");
		test_steps.add("Successfully Verified Total Nights in ToolTip : " + foundNights);
		tapechartLogger.info("Successfully Verified Total Nights in ToolTip : " + foundNights);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(roomClassPath)), driver);
		String foundClass = driver.findElement(By.xpath(roomClassPath)).getText();
		foundClass = foundClass.replace(".", "");
		foundClass = foundClass.replace(".", "");
		foundClass = foundClass.replace(".", "");
		assertTrue(roomClass.contains(foundClass),
				"Failed RoomClass Name Missmatched Found[" + foundClass + "} expented[" + roomClass + "]");
		test_steps.add("Successfully Verified RoomClass Name in ToolTip : " + foundClass);
		tapechartLogger.info("Successfully Verified RoomClass Name in ToolTip : " + foundClass);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(roomNoPath)), driver);
		String foundRoomNo = driver.findElement(By.xpath(roomNoPath)).getText();
		assertEquals(foundRoomNo, roomNumber, "Failed Room Number Missmatched");
		test_steps.add("Successfully Verified Room Number in ToolTip : " + foundRoomNo);
		tapechartLogger.info("Successfully Verified Room Number in ToolTip : " + foundRoomNo);

		return test_steps;
	}

	public String getTapeChartFirstCellDate(WebDriver driver) throws InterruptedException {
		Elements_TapeChart elementsTapeChart = new Elements_TapeChart(driver);

		Utility.ScrollToElement_NoWait(elementsTapeChart.TapeChartDateChart, driver);
		String getDate = elementsTapeChart.TapeChartDateChart.getText();

		tapechartLogger.info(getDate);
		String[] splitDate = getDate.split(" ");
		System.out.println("splitDate: " + splitDate.length);
		for (int i = 0; i < splitDate.length; i++) {
			System.out.println(i + " : " + splitDate[i]);
		}
		return getDate;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <moveReservations> ' Description: <Move Reservation to
	 * index Date and Next Room in same> ' Input parameters: <first room number,
	 * room class, guest name, second room number, date index> ' Return value:
	 * <String> ' Created By: <Adnan Ghaffar> ' Created On: <05/09/2020>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ <MM/DD/YYYY>:<Full name of
	 * developer>: 1.Step modified 2.Step modified
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public String moveReservations(WebDriver driver, String roomNumber, String roomClass, String guestName, int roomAdd,
			int dateIndex) throws InterruptedException {

		roomNumber = roomNumber.replaceAll("\\s+", "");
		System.out.println(roomNumber);
		Wait.wait2Second();
		String path = "//div[text()='" + roomClass
				+ "'  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
				+ roomNumber + "']//following-sibling::div//div[@class='DatesContainer']";
		System.out.println("PTah:" + path);
		int nextRoom = Integer.parseInt(roomNumber) + roomAdd;
		String secondRoom = Integer.toString(nextRoom);
		System.out.println("DateIndex: " + dateIndex);
		String ToPath = "//div[text()='" + roomClass
				+ "'  and contains(@class,'roomclassname')]//parent::*//following-sibling::div/div[@title='"
				+ secondRoom + "']//following-sibling::div/span/div[contains(@class,'tapechartdatecell')][" + dateIndex
				+ "]";

		WebElement fromElement = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(fromElement, driver);
		Utility.app_logs.info(" Location " + fromElement.getLocation());
		JavascriptExecutor javaScript = (JavascriptExecutor) driver;
		if (LastRoomClassInTapeChart(driver, roomClass)) {
			Utility.ScrollToElement(fromElement, driver);
			javaScript.executeScript("window.scrollBy(0,-200)");

		} else {
			Utility.ScrollToElement(fromElement, driver);
			javaScript.executeScript("window.scrollBy(0,-300)");
			Utility.app_logs.info("Scrolled Back");
		}

		Wait.explicit_wait_xpath(ToPath, driver);
		WebElement toElement = driver.findElement(By.xpath(ToPath));

		Actions action = new Actions(driver);
		action.dragAndDrop(fromElement, toElement).build().perform();

		Actions builder = new Actions(driver);
		Action resizable = builder.moveToElement(fromElement).clickAndHold().moveToElement(toElement).release().build();
		// resizable.perform();

		return secondRoom;
	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <extendReservation> ' Description: <Extend reserved
	 * reservation from one room to next one> ' Input parameters: <room number,
	 * roomClassAbbreviation, guest name> ' Return value: <room number> '
	 * Created By: <Adnan Ghaffar> ' Created On: <05/09/2020>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ <MM/DD/YYYY>:<Full name of
	 * developer>: 1.Step modified 2.Step modified
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public int extendReservation(WebDriver driver, String roomNumber, String roomClassAbbreviation, String guestName)
			throws InterruptedException {

		roomNumber = roomNumber.replaceAll("\\s+", "");
		Wait.wait2Second();
		String pathFrom = "//div[contains(text(),'" + roomClassAbbreviation
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
				+ roomNumber + "']//following-sibling::div//div[@class='DatesContainer']";

		String pathTo = "(//div[text()='" + roomClassAbbreviation
				+ "' and contains(@class,'roomclassname')]//ancestor::div[@class='roomRatesChart']//div[@title='"
				+ roomNumber + "']//following-sibling::div//div[text()='" + guestName
				+ "']//following::span[@class='ir-tc-rightResHandle'])[1]";

		int preWidth = 0;
		Wait.explicit_wait_xpath(pathTo, driver);
		WebElement elementFrom = driver.findElement(By.xpath(pathFrom));
		WebElement elementTo = driver.findElement(By.xpath(pathTo));
		Utility.app_logs.info(" Location " + elementTo.getLocation());
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		if (LastRoomClassInTapeChart(driver, roomClassAbbreviation)) {
			Utility.ScrollToElement(elementFrom, driver);
			jse.executeScript("window.scrollBy(0,-200)");

		} else {
			Utility.ScrollToElement(elementFrom, driver);
			jse.executeScript("window.scrollBy(0,-350)");
		}
		int divWidth = elementFrom.getSize().getWidth();
		System.out.println("Pre Width : " + divWidth);
		preWidth = divWidth;

		Actions builder = new Actions(driver);

		Action resizable = builder.moveToElement(elementTo).clickAndHold().moveByOffset(divWidth, 0).release().build();
		resizable.perform();
		return preWidth;
	}

	// Added by adnan
	public ArrayList<String> toolTip_Verification(WebDriver driver, String RoomNumber, String RoomClass,
			String FullName, String ArivalDate, String DepartDate, String totalNights, String FolioTotal,
			String FolioBalance, String resNo, String RoomClassAbb) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<>();
		Wait.wait2Second();
		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		Wait.wait2Second();

		String path2 = "//div[contains(text(),'" + RoomClassAbb
				+ "') and contains(@class,'roomclassname')]//ancestor::div[@class='roomRatesChart']//div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[text()='" + FullName + "']/../../../..";
		WebElement Room = driver.findElement(By.xpath(path2));
		if (LastRoomClassInTapeChart(driver, RoomClassAbb)) {
			Utility.ScrollToElement(Room, driver);
		} else {
			Utility.ScrollToElement(Room, driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-300)");
		}
		WebElement hoverElement = driver.findElement(By.xpath(path2));
		Actions builder = new Actions(driver);
		builder.moveToElement(hoverElement).perform();
		test_steps.add("Hovers over a Reservation (Room '" + RoomClassAbb + ":" + RoomNumber + "' )");
		// Actions actions = new Actions(driver);
		// actions.moveToElement(driver.findElement(By.xpath(path2))).perform();
		String path = "//*[text()='" + FullName + "']/following::span[@class='ir-tc-rightResHandle']";

		String namePath = "//div[@role='tooltip']//div[text()='" + FullName + "']";
		String resNoPath = "//div[@role='tooltip']//div[text()='" + FullName + "']/../../following-sibling::span/b";
		String folioTotalPath = "//div[@role='tooltip']//div[text()='" + FullName
				+ "']/../../../../following-sibling::div//span[contains(@id,'FolioTotal')]";
		String folioBalancePath = "//div[@role='tooltip']//div[text()='" + FullName
				+ "']/../../../../following-sibling::div//span[contains(@id,'FolioBalance')]";
		String arivalDatePath = "//div[@role='tooltip']//div[text()='" + FullName
				+ "']/../../../../../../following-sibling::div//div[contains(@class,'hover-date-month ng-arriveDate')]";
		String departDatePath = "//div[@role='tooltip']//div[text()='" + FullName
				+ "']/../../../../../../following-sibling::div//div[contains(@class,'hover-date-month ng-departDate')]";
		String totalNightsPath = "//div[@role='tooltip']//div[text()='" + FullName
				+ "']/../../../../../../following-sibling::div//div[contains(@class,'totalNights')]";
		String roomClassPath = "//div[@role='tooltip']//div[text()='" + FullName
				+ "']/../../../../../../following-sibling::div//span[contains(@class,'ir-tc-roomClass')]";
		String roomNoPath = "//div[@role='tooltip']//div[text()='" + FullName
				+ "']/../../../../../../following-sibling::div//span[contains(@class,'ir-tc-roomClass')]/../following-sibling::div/span";

		// Utility.ScrollToElement(driver.findElement(By.xpath(namePath)),
		// driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(namePath)), driver);
		String foundName = driver.findElement(By.xpath(namePath)).getText();
		assertEquals(foundName, FullName, "Failed Name Missmatched");
		test_steps.add("Successfully Verified Full Name in ToolTip : " + foundName);
		tapechartLogger.info("Successfully Verified Full Name in ToolTip : " + foundName);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(resNoPath)), driver);
		String foundResNo = driver.findElement(By.xpath(resNoPath)).getText();
		assertEquals(foundResNo.trim(), "#" + resNo, "Failed Reservation No Missmatched");
		test_steps.add("Successfully Verified Reservation No in ToolTip : " + foundResNo);
		tapechartLogger.info("Successfully Verified Reservation No in ToolTip : " + foundResNo);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(folioTotalPath)), driver);
		String foundTotal = driver.findElement(By.xpath(folioTotalPath)).getText();
		System.out.println(folioTotalPath);
		System.out.println(foundTotal);
		for (int i = 0; i < 5; i++) {
			if (foundTotal.equals("")) {
				tapechartLogger.info("try " + i + " Trip total null");
				Wait.wait2Second();
				foundTotal = driver.findElement(By.xpath(folioTotalPath)).getText();
				System.out.println(foundTotal);
			} else {
				break;
			}
		}
		foundTotal = Utility.RemoveDollarandSpaces(driver, foundTotal);
		test_steps.add("Successfully Verified Folio Total in ToolTip : " + foundTotal);
		tapechartLogger.info("Successfully Verified Folio Total in ToolTip : " + foundTotal);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(folioBalancePath)), driver);
		String foundBalance = driver.findElement(By.xpath(folioBalancePath)).getText();
		for (int i = 0; i < 5; i++) {
			if (foundBalance.equals("")) {
				tapechartLogger.info("try " + i + "Folio Balance null");
				Wait.wait2Second();
				foundBalance = driver.findElement(By.xpath(folioBalancePath)).getText();
				System.out.println(foundBalance);
			} else {
				break;
			}
		}
		foundBalance = Utility.RemoveDollarandSpaces(driver, foundBalance);
		assertEquals(foundBalance, FolioBalance, "Failed Folio Balance Missmatched");
		test_steps.add("Successfully Verified Folio Balance in ToolTip : " + foundBalance);
		tapechartLogger.info("Successfully Verified Folio Balance in ToolTip : " + foundBalance);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(arivalDatePath)), driver);
		String foundArivalDate = driver.findElement(By.xpath(arivalDatePath)).getText();
		assertEquals(Utility.parseDate(foundArivalDate, "MMM dd, yyyy", "MMM dd, yyyy"), ArivalDate,
				"Failed Arival Date Missmatched");
		test_steps.add("Successfully Verified Arival Date in ToolTip : " + foundArivalDate);
		tapechartLogger.info("Successfully Verified Arival Date in ToolTip : " + foundArivalDate);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(departDatePath)), driver);
		String foundDepartDate = driver.findElement(By.xpath(departDatePath)).getText();
		assertEquals(Utility.parseDate(foundDepartDate, "MMM dd, yyyy", "MMM dd, yyyy"), DepartDate,
				"Failed Depart Date Missmatched");
		test_steps.add("Successfully Verified Depart Date in ToolTip : " + foundDepartDate);
		tapechartLogger.info("Successfully Verified Depart Date in ToolTip : " + foundDepartDate);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(totalNightsPath)), driver);
		String foundNights = driver.findElement(By.xpath(totalNightsPath)).getText();
		assertEquals(foundNights, totalNights, "Failed Total Nights Missmatched");
		test_steps.add("Successfully Verified Total Nights in ToolTip : " + foundNights);
		tapechartLogger.info("Successfully Verified Total Nights in ToolTip : " + foundNights);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(roomClassPath)), driver);
		String foundClass = driver.findElement(By.xpath(roomClassPath)).getText();
		foundClass = foundClass.replace(".", "");
		foundClass = foundClass.replace(".", "");
		foundClass = foundClass.replace(".", "");
		assertTrue(RoomClass.contains(foundClass),
				"Failed RoomClass Name Missmatched Found[" + foundClass + "} expented[" + RoomClass + "]");
		test_steps.add("Successfully Verified RoomClass Name in ToolTip : " + foundClass);
		tapechartLogger.info("Successfully Verified RoomClass Name in ToolTip : " + foundClass);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(roomNoPath)), driver);
		String foundRoomNo = driver.findElement(By.xpath(roomNoPath)).getText();
		assertEquals(foundRoomNo, RoomNumber, "Failed Room Number Missmatched");
		test_steps.add("Successfully Verified Room Number in ToolTip : " + foundRoomNo);
		tapechartLogger.info("Successfully Verified Room Number in ToolTip : " + foundRoomNo);

		return test_steps;
	}

	// Added By Adnan: Move Reservation to next Date and Next Room in same Class
	public String MoveReservations(WebDriver driver, String RoomNumber, String RoomClass, String FullName)
			throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();

		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		System.out.println(RoomNumber);
		Wait.wait2Second();
		String path = "//div[contains(text(),'" + RoomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer']";
		System.out.println("PTah:" + path);
		int nextRoom = Integer.parseInt(RoomNumber) + 1;
		String NextRoom = Integer.toString(nextRoom);
		String ToPath = "//div[contains(text(),'" + RoomClass
				+ "')  and contains(@class,'roomclassname')]//parent::*//following-sibling::div/div[@title='" + NextRoom
				+ "']//following-sibling::div/span/div[contains(@class,'tapechartdatecell')][3]";

		System.out.println("To pTah:" + ToPath);
		Utility.ScrollToElement(driver.findElement(By.xpath(path)), driver);
		System.out.println("Scrolled to the reservation");
		System.out.println(path);
		WebElement ele = driver.findElement(By.xpath(path));
		Utility.app_logs.info(" Location " + ele.getLocation());
		if (LastRoomClassInTapeChart(driver, RoomClass)) {
			Utility.ScrollToElement(ele, driver);
		} else {
			Utility.ScrollToElement(ele, driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-300)");
			Utility.app_logs.info("Scrolled Back");
		}
		WebElement From = driver.findElement(By.xpath(path));
		Point Location = From.getLocation();

		Wait.explicit_wait_xpath(ToPath, driver);
		WebElement To = driver.findElement(By.xpath(ToPath));

		// Using Action class for drag and drop.
		Actions act = new Actions(driver);
		// Dragged and dropped
		act.dragAndDrop(From, To).build().perform();

		Actions builder = new Actions(driver);
		Action resizable = builder.moveToElement(From).clickAndHold().moveToElement(To).release().build();
		// resizable.perform();

		return NextRoom;
	}

	public void CheckInCheckOutDate(WebDriver driver) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Select_Arrival_Date, driver);
		TapeChart.Select_Arrival_Date.click();
		TapeChart.Click_Today.click();
		Wait.wait2Second();

	}

	public void EnterAdult(WebDriver driver, String TapeChartAdults) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.Enter_Adults_Tapehchart, driver);
		TapeChart.Enter_Adults_Tapehchart.clear();
		TapeChart.Enter_Adults_Tapehchart.sendKeys(TapeChartAdults);

	}

	public void SelectRatePlan(WebDriver driver, String RatePlan) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.btnRatePlanSelect, driver);
		Wait.explicit_wait_elementToBeClickable(TapeChart.btnRatePlanSelect, driver);
		TapeChart.btnRatePlanSelect.click();
		Wait.wait1Second();
		String selectRatePlan = "(//span[text()='Manual Override'])[2]";
		WebElement element = driver.findElement(By.xpath(selectRatePlan));
		element.click();
	}

	public void EnterRateAmount(WebDriver driver, String Amount) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.enterAmount, driver);
		Wait.explicit_wait_elementToBeClickable(TapeChart.enterAmount, driver);
		TapeChart.enterAmount.sendKeys(Amount);

	}

	public void ClickOnSearch(WebDriver driver) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.Click_Search_TapeChart, driver);
		Wait.explicit_wait_elementToBeClickable(TapeChart.Click_Search_TapeChart, driver);
		TapeChart.Click_Search_TapeChart.click();
	}

	public void ClickOnAvailableRoom(WebDriver driver) throws InterruptedException {

		Elements_TapeChart elements_TapeChart = new Elements_TapeChart(driver);
		Wait.WaitForElement(driver, NewTapeChart.AvailableRoom);
		Wait.explicit_wait_visibilityof_webelement(elements_TapeChart.AvailableRoom, driver);
		Wait.explicit_wait_elementToBeClickable(elements_TapeChart.AvailableRoom, driver);

		Utility.ScrollToElement(elements_TapeChart.AvailableRoom, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-300)");
		Wait.wait1Second();
		elements_TapeChart.AvailableRoom.click();
		// Wait.WaitForReservationLoading(driver);
	}

	public boolean LastRoomClassInTapeChart(WebDriver driver, String RoomClassName) {
		String xpath = "(//div[contains(@class,'roomclassname')])[last()]";
		WebElement element = driver.findElement(By.xpath(xpath));
		if (element.getText().equals(RoomClassName)) {
			Utility.app_logs.info("Room class is the Last one" + RoomClassName);
			return true;
		}
		return false;

	}

	// Created By Adnan
	public ArrayList<String> TapeChartSearch(WebDriver driver, String CheckinDate, String CheckOutDate, String Adults,
			String Children, String RatePlan, ArrayList<String> steps) throws InterruptedException, ParseException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.TapeChart_CheckIn, driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.TapeChart_CheckIn, driver);
		Utility.ScrollToElement(TapeChart.TapeChart_CheckIn, driver);
		TapeChart.TapeChart_CheckIn.clear();
		TapeChart.TapeChart_CheckIn.sendKeys(CheckinDate);
		assertTrue(TapeChart.TapeChart_CheckIn.getAttribute("value").contains(CheckinDate), "Failed: CheckIn");
		steps.add("Enter Checking Date  : " + CheckinDate);
		tapechartLogger.info("Enter Checking Date  : " + CheckinDate);

		Wait.explicit_wait_visibilityof_webelement(TapeChart.TapeChart_CheckOut, driver);
		Utility.ScrollToElement(TapeChart.TapeChart_CheckOut, driver);
		TapeChart.TapeChart_CheckOut.clear();
		TapeChart.TapeChart_CheckOut.sendKeys(CheckOutDate);
		assertTrue(TapeChart.TapeChart_CheckOut.getAttribute("value").contains(CheckOutDate), "Failed: CheckOut");
		steps.add("Enter Check Out Date  : " + CheckOutDate);
		tapechartLogger.info("Enter Check Out Date  : " + CheckOutDate);

		TapeChart.Enter_Adults_Tapehchart.clear();
		TapeChart.Enter_Adults_Tapehchart.sendKeys(Adults);
		assertTrue(TapeChart.Enter_Adults_Tapehchart.getAttribute("value").contains(Adults), "Failed: Adult set");
		steps.add("Enter Adults  : " + Adults);
		tapechartLogger.info("Enter Adults  : " + Adults);
		TapeChart.Enter_Children_Tapechart.clear();
		TapeChart.Enter_Children_Tapechart.sendKeys(Children);
		steps.add("Enter Children : " + Children);
		tapechartLogger.info("Enter Children : " + Children);
		// Wait.explicit_wait_xpath(OR.Click_Tapechart_RackRate, driver);
		// TapeChart.Click_Tapechart_RackRate.click();
		// String RatePlan_Path =
		// "//div[@id='tapeChartSearch']//ul[@role='menu']//child::span[contains(text(),'"
		// + RatePlan + "')]";
		// Wait.explicit_wait_xpath(RatePlan_Path, driver);
		// WebElement ratePlan = driver.findElement(By.xpath(RatePlan_Path));
		// ratePlan.click();
		steps.add("Selected Rate Plan : " + RatePlan);
		tapechartLogger.info("Selected Rate Plan : " + RatePlan);
		TapeChart.Click_Search_TapeChart.click();
		steps.add("Click Tape Chart search");
		tapechartLogger.info("Click Tape Chart search");
		// try {
		// Wait.WaitForElement(driver, OR.TapeChartAvailableSlot);
		// } catch (Exception e) {
		// TapeChart.Click_Search_TapeChart.click();
		// tapechartLogger.info("Again Click Search");
		// Wait.explicit_wait_visibilityof_webelement(TapeChart.TapeChartAvailableSlot,
		// driver);
		// }
		return steps;
	}

	public void ClickUnassignedRoomClass(WebDriver driver, String RoomClass, ArrayList<String> steps)
			throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		String CellPath = "//div[text()='" + RoomClass
				+ "'  and contains(@class,'roomclassname')]//ancestor::div[@class='roomRatesChart']/child::div[last()]// div[contains(text(),'Unassigned')]";
		Wait.explicit_wait_xpath(CellPath, driver);
		WebElement Unassigned_Reserv_Room = driver.findElement(By.xpath(CellPath));

		if (LastRoomClassInTapeChart(driver, RoomClass)) {
			Utility.ScrollToElement(Unassigned_Reserv_Room, driver);
		} else {
			Utility.ScrollToElement(Unassigned_Reserv_Room, driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-300)");
			Utility.app_logs.info("Scrolled Back");
		}
		Unassigned_Reserv_Room.click();
		steps.add("Click Unassigned of Room Class : " + RoomClass);
		Utility.app_logs.info("Click Unassigned of Room Class : " + RoomClass);
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(res.CP_NewReservation_GuestSalutation, driver);
		steps.add("New Reservation Page is opened");
		tapechartLogger.info("New Reservation Page is opened");
	}

	public String GetUnassignedRoomClassCount(WebDriver driver, String RoomClass) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		String CellPath = "//div[text()='" + RoomClass
				+ "'  and contains(@class,'roomclassname')]//ancestor::div[@class='roomRatesChart']/child::div[last()]// div[contains(text(),'Unassigned')]";
		Wait.explicit_wait_xpath(CellPath, driver);
		WebElement Unassigned_Reserv_Room = driver.findElement(By.xpath(CellPath));
		return Unassigned_Reserv_Room.getText().split(" ")[0];
	}

	public void ClickUnassigned(WebDriver driver) throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.Unassigned_Button, driver);
		Wait.explicit_wait_elementToBeClickable(TapeChart.Unassigned_Button, driver);
		Utility.ScrollToElement(TapeChart.Unassigned_Button, driver);
		if (!TapeChart.Unassigned_Button.getAttribute("class").contains("active")) {
			TapeChart.Unassigned_Button.click();
		}
		assertTrue(TapeChart.UnassignedColumnHeader.isDisplayed(), "Failed:List not show");
	}

	public void VerifyUnassignedReservation(WebDriver driver, String ReservationName, String ArrivalDate,
			String DepartureDate, String RoomClassAbb) {
		// Verify Reservation

		tapechartLogger.info("Expected Res Name : " + ReservationName);
		tapechartLogger.info("Expected Arival Date : " + ArrivalDate);
		tapechartLogger.info("Expected Departure Date : " + DepartureDate);
		tapechartLogger.info("Expected Room Class Abbreviation : " + RoomClassAbb);
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement_350(TapeChart.UnassignedList_ResName.get(0), driver);
		int LineItem_Size = TapeChart.UnassignedList_ResName.size();
		tapechartLogger.info("Unassigned Reservations List Size : " + LineItem_Size);
		int lineNumber = LineItem_Size - 1;
		tapechartLogger.info("LineNumber : " + lineNumber);
		boolean found = false;
		for (lineNumber = LineItem_Size - 1; lineNumber >= 0; lineNumber--) {
			tapechartLogger.info("LineNumber : " + lineNumber);
			tapechartLogger.info("Res Name : " + TapeChart.UnassignedList_ResName.get(lineNumber).getText());
			tapechartLogger.info("Arival Date : " + TapeChart.UnassignedList_ArrivalDate.get(lineNumber).getText());
			tapechartLogger
					.info("Departure Date : " + TapeChart.UnassignedList_DepartureDate.get(lineNumber).getText());
			tapechartLogger.info(
					"Room Class Abbreviation : " + TapeChart.UnassignedList_RoomClassAbb.get(lineNumber).getText());
			if (TapeChart.UnassignedList_ResName.get(lineNumber).getText().equals(ReservationName)
					&& TapeChart.UnassignedList_ArrivalDate.get(lineNumber).getText().equals(ArrivalDate)
					&& TapeChart.UnassignedList_RoomClassAbb.get(lineNumber).getText().equals(RoomClassAbb)
					&& TapeChart.UnassignedList_DepartureDate.get(lineNumber).getText().equals(DepartureDate)) {

				found = true;
				break;
			}
		}
		if (!found) {
			assertTrue(false, "Failed: Reservation '" + ReservationName + "' Not found.");
		}
	}

	public int GetUnassignedReservationIndex(WebDriver driver, String ReservationName, String ArrivalDate,
			String DepartureDate, String RoomClassAbb) {
		// Verify Reservation

		tapechartLogger.info("Expected Res Name : " + ReservationName);
		tapechartLogger.info("Expected Arival Date : " + ArrivalDate);
		tapechartLogger.info("Expected Departure Date : " + DepartureDate);
		tapechartLogger.info("Expected Room Class Abbreviation : " + RoomClassAbb);
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement_350(TapeChart.UnassignedList_ResName.get(0), driver);
		int LineItem_Size = TapeChart.UnassignedList_ResName.size();
		tapechartLogger.info("Unassigned Reservations List Size : " + LineItem_Size);
		int lineNumber = LineItem_Size - 1;
		tapechartLogger.info("LineNumber : " + lineNumber);
		boolean found = false;
		for (lineNumber = LineItem_Size - 1; lineNumber >= 0; lineNumber--) {
			tapechartLogger.info("LineNumber : " + lineNumber);
			tapechartLogger.info("Res Name : " + TapeChart.UnassignedList_ResName.get(lineNumber).getText());
			tapechartLogger.info("Arival Date : " + TapeChart.UnassignedList_ArrivalDate.get(lineNumber).getText());
			tapechartLogger
					.info("Departure Date : " + TapeChart.UnassignedList_DepartureDate.get(lineNumber).getText());
			tapechartLogger.info(
					"Room Class Abbreviation : " + TapeChart.UnassignedList_RoomClassAbb.get(lineNumber).getText());
			if (TapeChart.UnassignedList_ResName.get(lineNumber).getText().equals(ReservationName)
					&& TapeChart.UnassignedList_ArrivalDate.get(lineNumber).getText().equals(ArrivalDate)
					&& TapeChart.UnassignedList_RoomClassAbb.get(lineNumber).getText().equals(RoomClassAbb)
					&& TapeChart.UnassignedList_DepartureDate.get(lineNumber).getText().equals(DepartureDate)) {

				found = true;
				break;
			}
		}
		if (!found) {
			assertTrue(false, "Failed: Reservation '" + ReservationName + "' Not found.");
		}
		return lineNumber + 1;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <GetandVerifyUnassignedReservationNumber> ' Description:
	 * <Get Unassigned Reservation Number OR Verify Unassigned Reservation
	 * Number From the Unassigned Button Unassigned Column Header and From the
	 * size of Unassigned Reservation List> ' Input parameters: <boolean need to
	 * verify or not> ' Return value: <String> ' Created By: <Adhnan Ghaffar> '
	 * Created On: <04/27/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public String GetandVerifyUnassignedReservationNumber(WebDriver driver, boolean verify)
			throws InterruptedException {
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		String number = null;
		Wait.explicit_wait_visibilityof_webelement(TapeChart.Unassigned_Button, driver);
		Utility.ScrollToElement(TapeChart.Unassigned_Button, driver);
		number = TapeChart.Unassigned_Button.getText();
		number = number.split(" ")[1];
		number = number.replaceAll("[()]", "");
		if (verify) {
			if (!number.equals("0")) {
				int size = driver.findElements(By.xpath(OR.UnassignedList)).size();
				assertEquals(Integer.toString(size), number,
						"Failed: Number of Unassigned Reservations missmatched on Unassigned Button");
			}
			assertEquals(TapeChart.UnassignedColumnHeader.getText(), "Unassigned (" + number + ")",
					"Failed: Number of Unassigned Reservations missmatched on Unassigned Column Header");
		}
		return number;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyReservationName_ReservationUpdate> ' Description:
	 * <Verification reservation number in update popup> ' Input parameters: <
	 * ',' separated parameters type> ' Return value: <ArrayList> ' Created By:
	 * <Farhan Ghaffar> ' Created On: <03/30/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> verifyReservationNameReservationUpdate(WebDriver driver, String ResName) {
		ArrayList<String> test_steps = new ArrayList<>();
		String path = "//*[@id='ReservationUpdate']//h4[contains(@data-bind,'ReservationName')]";
		WebElement element = driver.findElement(By.xpath(path));
		Wait.explicit_wait_visibilityof_webelement_120(element, driver);
		Wait.explicit_wait_elementToBeClickable(element, driver);
		String foundName = element.getText();
		assertEquals(foundName, ResName, "Failed Reservation Name Mismatched");
		tapechartLogger.info("Successfully Verified Reservation Name in Reservation Update Popup");
		test_steps.add("Successfully Verified Reservation Name in Reservation Update Popup");
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyCheckInTimeReservationUpdate> ' Description:
	 * <verify check in date> ' Input parameters: <check in date> ' Return
	 * value: <checkInDate> ' Created By: <Adnan Ghaffar> ' Created On:
	 * <05/09/2020>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ <MM/DD/YYYY>:<Full name of
	 * developer>: 1.Step modified 2.Step modified
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> verifyCheckInTimeReservationUpdate(WebDriver driver, String checkInDate) {
		ArrayList<String> testSteps = new ArrayList<>();
		String path = "//*[@id='ReservationUpdate']//span[text()='Check In']/following-sibling::div//div[contains(@class,'datetextNoChange')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		String foundDate = driver.findElement(By.xpath(path)).getText();
		assertEquals(Utility.parseDate(foundDate, "MMM dd, yyyy", "MMM dd, yyyy"), checkInDate,
				"Failed Check In Date Mismatched");
		tapechartLogger.info("Successfully Verified Check In Date in Reservation Update Popup");
		testSteps.add("Successfully Verified Check In Date in Reservation Update Popup");

		return testSteps;
	}

	public ArrayList<String> verifyCheckInTime_ReservationUpdate(WebDriver driver, String CheckInDate, String wasDate) {
		ArrayList<String> test_steps = new ArrayList<>();
		String path = "//*[@id='ReservationUpdate']//span[text()='Check In']/following-sibling::div/div[contains(@data-bind,'NewDateArrivedDisplay')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		String foundDate = driver.findElement(By.xpath(path)).getText();
		tapechartLogger.info("CheckIn Found Date " + foundDate);
		assertEquals(Utility.parseDate(foundDate, "MMM dd, yyyy", "MMM dd, yyyy"), CheckInDate,
				"Failed Check In Date Mismatched");
		tapechartLogger.info("Successfully Verified Check In Date in Reservation Update Popup");
		test_steps.add("Successfully Verified Check In Date in Reservation Update Popup");

		String path2 = "//*[@id='ReservationUpdate']//span[text()='Check In']/following-sibling::div//div[contains(@data-bind,'OriginalDateArrivedDisplay')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path2)), driver);
		String foundDate2 = driver.findElement(By.xpath(path2)).getText();
		tapechartLogger.info("Before Checkin Found Date " + foundDate2);
		foundDate2 = foundDate2.substring(4);
		assertEquals(Utility.parseDate(foundDate2, "MMM dd, yyyy", "MMM dd, yyyy"), wasDate,
				"Failed Check In Before Date Mismatched");
		tapechartLogger.info("Successfully Verified Check In Before Date in Reservation Update Popup");
		test_steps.add("Successfully Verified Check In Before Date in Reservation Update Popup");

		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyCheckOutTimeReservationUpdate> ' Description:
	 * <verify check out date> ' Input parameters: <checkOutDate, pastDate> '
	 * Return value: <Array List> ' Created By: <Adnan Ghaffar> ' Created On:
	 * <05/09/2020>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ <MM/DD/YYYY>:<Full name of
	 * developer>: 1.Step modified 2.Step modified
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> verifyCheckOutTimeReservationUpdate(WebDriver driver, String CheckOutDate,
			String pastDate) {
		ArrayList<String> testSteps = new ArrayList<>();
		String updatedCheckoutPath = "//*[@id='ReservationUpdate']//span[text()='Check Out']/following-sibling::div//div[contains(@data-bind,'NewDateDepartedDisplay')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(updatedCheckoutPath)), driver);
		String foundDate = driver.findElement(By.xpath(updatedCheckoutPath)).getText();
		assertEquals(Utility.parseDate(foundDate, "MMM dd, yyyy", "MMM dd, yyyy"), CheckOutDate,
				"Failed Check Out Date Mismatched");
		tapechartLogger.info("Successfully Verified Check Out Date in Reservation Update Popup");
		testSteps.add("Successfully Verified Check Out Date in Reservation Update Popup");

		String previouseCheckoutPath = "//*[@id='ReservationUpdate']//span[text()='Check Out']/following-sibling::div//div[contains(@data-bind,'OriginalDateDepartedDisplay')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(previouseCheckoutPath)), driver);
		String newCheckoutDate = driver.findElement(By.xpath(previouseCheckoutPath)).getText();
		newCheckoutDate = newCheckoutDate.substring(4);
		assertEquals(Utility.parseDate(newCheckoutDate, "MMM dd, yyyy", "MMM dd, yyyy"), pastDate,
				"Failed Check Out Was Date Mismatched");
		tapechartLogger.info("Successfully Verified First Check Out Date in Reservation Update Popup");
		testSteps.add("Successfully Verified First Check Out Date in Reservation Update Popup");

		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyRoomClassReservationUpdate> ' Description: <verify
	 * updated reservation in tape chart> ' Input parameters: <roomClass> '
	 * Return value: <Array List> ' Created By: <Adnan Ghaffar> ' Created On:
	 * <05/09/2020>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ <MM/DD/YYYY>:<Full name of
	 * developer>: 1.Step modified 2.Step modified
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> verifyRoomClassReservationUpdate(WebDriver driver, String roomClass) {
		ArrayList<String> test_steps = new ArrayList<>();
		String path = "//*[@id='ReservationUpdate']//span[text()='Room Class']/following-sibling::div//div[contains(@data-bind,'RoomClassChanged')][1]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		String foundName = driver.findElement(By.xpath(path)).getText();
		foundName = foundName.replace(".", "");
		foundName = foundName.replace(".", "");
		foundName = foundName.replace(".", "");
		assertTrue(roomClass.contains(foundName),
				"Failed RoomClass Mismatched expected[" + roomClass + "] but found[" + foundName + "]");
		tapechartLogger.info("Successfully Verified RoomClass Name in Reservation Update Popup");
		test_steps.add("Successfully Verified RoomClass Name in Reservation Update Popup");

		return test_steps;
	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyTripTotalReservationUpdate> ' Description: <verify
	 * tool tip> ' Input parameters: <tripTotal, wasTripTotal> ' Return value:
	 * <Array List> ' Created By: <Adnan Ghaffar> ' Created On: <05/09/2020>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ <MM/DD/YYYY>:<Full name of
	 * developer>: 1.Step modified 2.Step modified
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> verifyTripTotalReservationUpdate(WebDriver driver, String tripTotal, String wasTripTotal) {
		ArrayList<String> testSteps = new ArrayList<>();
		String path = "//*[@id='ReservationUpdate']//span[text()='Trip Total']/following-sibling::div//div[contains(@data-bind,'NewFolioTotal')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		String foundTripTotal = driver.findElement(By.xpath(path)).getText();
		foundTripTotal = Utility.convertDollarToNormalAmount(driver, foundTripTotal).trim();
		assertEquals(Float.parseFloat(foundTripTotal), Float.parseFloat(tripTotal), "Failed TripTotal Mismatched");
		tapechartLogger.info("Successfully Verified TripTotal in Reservation Update Popup");
		testSteps.add("Successfully Verified TripTotal in Reservation Update Popup");

		String pastToolTripPath = "//*[@id='ReservationUpdate']//span[text()='Trip Total']/following-sibling::div//span[contains(@data-bind,'StartingFolioTotalDisplay')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(pastToolTripPath)), driver);
		String foundWasTripTotal = driver.findElement(By.xpath(pastToolTripPath)).getText();
		foundWasTripTotal = Utility.convertDollarToNormalAmount(driver, foundWasTripTotal).trim();
		assertEquals(Float.parseFloat(foundWasTripTotal), Float.parseFloat(wasTripTotal),
				"Failed TripTotal was Mismatched");
		tapechartLogger.info("Successfully Verified Trip Total before Extend in Reservation Update Popup");
		test_steps.add("Successfully Verified Trip Total before Extend in Reservation Update Popup");
		return test_steps;
	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyBalanceDueReservationUpdate> ' Description: <verify
	 * total balance> ' Input parameters: <balanceDue, pastBalanceDue> ' Return
	 * value: <Array List> ' Created By: <Adnan Ghaffar> ' Created On:
	 * <05/09/2020>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ <MM/DD/YYYY>:<Full name of
	 * developer>: 1.Step modified 2.Step modified
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> verifyBalanceDueReservationUpdate(WebDriver driver, String balanceDue,
			String pastBalanceDue) {
		ArrayList<String> testSteps = new ArrayList<>();
		String path = "//*[@id='ReservationUpdate']//span[text()='Balance Due']/following-sibling::div//div[contains(@data-bind,'NewFolioBalance')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		String foundBalaTotal = driver.findElement(By.xpath(path)).getText();
		foundBalaTotal = Utility.convertDollarToNormalAmount(driver, foundBalaTotal).trim();
		assertEquals(Float.parseFloat(foundBalaTotal), Float.parseFloat(balanceDue), "Failed Balance Mismatched");
		tapechartLogger.info("Successfully Verified Balance in Reservation Update Popup");
		testSteps.add("Successfully Verified Balance in Reservation Update Popup");

		String beforeExtendBalancePath = "//*[@id='ReservationUpdate']//span[text()='Balance Due']/following-sibling::div//span[contains(@data-bind,'StartingFolioBalanceDisplay')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(beforeExtendBalancePath)), driver);
		String foundBalaTotalBeforeExtend = driver.findElement(By.xpath(beforeExtendBalancePath)).getText();
		foundBalaTotalBeforeExtend = Utility.convertDollarToNormalAmount(driver, foundBalaTotalBeforeExtend).trim();
		assertEquals(Float.parseFloat(foundBalaTotalBeforeExtend), Float.parseFloat(pastBalanceDue),
				"Failed Balance WAS Mismatched");
		tapechartLogger.info("Successfully Verified Balance before Extend in Reservation Update Popup");
		testSteps.add("Successfully Verified Balance before Extend in Reservation Update Popup");
		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickConfirm_ReservationUpdate> ' Description: <click on
	 * confirm change button in popup in tape chart> ' Input parameters: < room
	 * number, room class name> ' Return value: <ArrayList> ' Created By:
	 * <Farhan Ghaffar> ' Created On: <03/30/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> clickConfirmReservationUpdate(WebDriver driver) throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<>();
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement_120(TapeChart.ConfirmChangeReservation_Button, driver);
		Utility.ScrollToElement(TapeChart.ConfirmChangeReservation_Button, driver);
		TapeChart.ConfirmChangeReservation_Button.click();
		tapechartLogger.info("Confirm Change Clicked in Reservation Update Popup");
		testSteps.add("Confirm Change Clicked in Reservation Update Popup");
		// verify toaster message here
		try {
			Wait.explicit_wait_visibilityof_webelement(TapeChart.Toaster_Message, driver);
			String message = TapeChart.Toaster_Message.getText();
			assertEquals(message, "Reservation has been updated successfully", "Reservation does not expand");
			tapechartLogger.info("Reservation Update Toaster Message Appear");
			testSteps.add("Get toaster message: " + message);
		} catch (Exception e) {
			System.err.println("Toaster not Displayed");
			tapechartLogger.info("Reservation Update Toaster Message not Appear");
			testSteps.add("Reservation Update Toaster Message not Appear");
		}
		return testSteps;
	}

	public void verifyReducedReservation(WebDriver driver, String RoomNumber, String RoomClass, String FullName,
			int preWidth) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();
		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		Wait.wait2Second();
		String path2 = "//div[text()='" + RoomClass
				+ "'  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer']";
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath(path2))).perform();

		WebElement ele2 = driver.findElement(By.xpath(path2));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-300)");
		// Wait.wait2Second();
		int divWidth2 = ele2.getSize().getWidth();
		System.out.println("Post Width : " + divWidth2);
		assertTrue(divWidth2 < preWidth, "Failed : Reservation not Reduced");

	}

	public void verifyExtendedReservation(WebDriver driver, String RoomNumber, String RoomClass, String FullName,
			int preWidth) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();
		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		Wait.wait2Second();
		String path2 = "//div[text()='" + RoomClass
				+ "'  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer']";
		Actions actions = new Actions(driver);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(path2)), driver);
		actions.moveToElement(driver.findElement(By.xpath(path2))).perform();

		// int preWidth = 0;
		WebElement ele2 = driver.findElement(By.xpath(path2));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-300)");

		// Wait.wait2Second();
		int divWidth2 = ele2.getSize().getWidth();
		System.out.println("Post Width : " + divWidth2);
		assertTrue(divWidth2 > preWidth, "Failed : Reservation not Extended");

	}

	public int reduceReservation(WebDriver driver, String RoomNumber, String RoomClass, String FullName)
			throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait2Second();
		RoomNumber = RoomNumber.replaceAll("\\s+", "");
		Wait.wait2Second();
		String path2 = "//div[text()='" + RoomClass
				+ "'  and contains(@class,'roomclassname')]//parent::*//following-sibling::div//div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[@class='DatesContainer']";
		// Actions actions = new Actions(driver);
		// actions.moveToElement(driver.findElement(By.xpath(path2))).perform();
		String path = "(//div[text()='" + RoomClass
				+ "' and contains(@class,'roomclassname')]//ancestor::div[@class='roomRatesChart']//div[@title='"
				+ RoomNumber + "']//following-sibling::div//div[text()='" + FullName
				+ "']//following::span[@class='ir-tc-rightResHandle'])[1]";
		// "//div[text()='"+FullName+"']/following::span[@class='ir-tc-rightResHandle']";
		System.out.println(path);
		Wait.explicit_wait_xpath(path, driver);

		WebElement ele2 = driver.findElement(By.xpath(path2));
		WebElement ele = driver.findElement(By.xpath(path));
		Utility.app_logs.info(" Location " + ele.getLocation());
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if (LastRoomClassInTapeChart(driver, RoomClass)) {
			Utility.ScrollToElement(ele, driver);
			jse.executeScript("window.scrollBy(0,-200)");

		} else {
			Utility.ScrollToElement(ele, driver);
			jse.executeScript("window.scrollBy(0,-350)");
		}
		Utility.app_logs.info(" Location " + ele.getLocation());
		int preWidth = 0;
		Wait.wait2Second();
		int divWidth = ele2.getSize().getWidth();
		System.out.println("Pre Width : " + divWidth);
		preWidth = divWidth;
		Actions builder = new Actions(driver);

		Action resizable = builder.moveToElement(ele).clickAndHold().moveByOffset(-divWidth, 0).release().build();
		resizable.perform();

		Wait.explicit_wait_visibilityof_webelement_120(TapeChart.CheckIn, driver);
		assertEquals(TapeChart.CheckIn.getText(), "Check In", "Check in doesnot display");
		assertEquals(TapeChart.CheckOut.getText(), "Check Out", "Check out does not display");
		assertEquals(TapeChart.TripTotal.getText(), "Trip Total", "Trip total does not display");
		assertEquals(TapeChart.BalanceDue.getText(), "Balance Due", "Balance due does not display");
		return preWidth;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 *
	 * ' Method Name: <tapeChartSearch> ' Description: <The method will enter
	 * the search information of Tape chartand click search> ' Input parameters:
	 * <WebDriver , String , String , String ,String , String > ' Return value:
	 * <ArrayList> ' Created By: <Adhnan Ghaffar> ' Created On: <05/11/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> tapeChartSearch(WebDriver driver, String CheckinDate, String CheckOutDate, String Adults,
			String Children, String RatePlan, ArrayList<String> steps) throws InterruptedException, ParseException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.TapeChart_CheckIn, driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.TapeChart_CheckIn, driver);
		Utility.ScrollToElement(TapeChart.TapeChart_CheckIn, driver);
		TapeChart.TapeChart_CheckIn.clear();
		TapeChart.TapeChart_CheckIn.sendKeys(CheckinDate);
		assertTrue(TapeChart.TapeChart_CheckIn.getAttribute("value").contains(CheckinDate), "Failed: CheckIn");
		steps.add("Enter Checkin Date  : " + CheckinDate);
		tapechartLogger.info("Enter Checkin Date  : " + CheckinDate);

		Wait.explicit_wait_visibilityof_webelement(TapeChart.TAPECHART_CHECKOUT, driver);
		Utility.ScrollToElement(TapeChart.TAPECHART_CHECKOUT, driver);
		TapeChart.TAPECHART_CHECKOUT.clear();
		TapeChart.TAPECHART_CHECKOUT.sendKeys(CheckOutDate);
		assertTrue(TapeChart.TAPECHART_CHECKOUT.getAttribute("value").contains(CheckOutDate), "Failed: CheckOut");
		steps.add("Enter Check Out Date  : " + CheckOutDate);
		tapechartLogger.info("Enter Check Out Date  : " + CheckOutDate);

		TapeChart.Enter_Adults_Tapehchart.clear();
		TapeChart.Enter_Adults_Tapehchart.sendKeys(Adults);
		assertTrue(TapeChart.Enter_Adults_Tapehchart.getAttribute("value").contains(Adults), "Failed: Adult set");
		steps.add("Enter Adults  : " + Adults);
		tapechartLogger.info("Enter Adults  : " + Adults);
		TapeChart.Enter_Children_Tapechart.clear();
		TapeChart.Enter_Children_Tapechart.sendKeys(Children);
		steps.add("Enter Children : " + Children);
		tapechartLogger.info("Enter Children : " + Children);
		Wait.explicit_wait_xpath(OR.Click_Tapechart_RackRate, driver);
		TapeChart.Click_Tapechart_RackRate.click();
		String RatePlan_Path = "//div[@id='tapeChartSearch']//ul[@role='menu']//child::span[contains(text(),'"
				+ RatePlan + "')]";
		Wait.explicit_wait_xpath(RatePlan_Path, driver);
		WebElement ratePlan = driver.findElement(By.xpath(RatePlan_Path));
		ratePlan.click();
		steps.add("Selected Rate Plan : " + RatePlan);
		tapechartLogger.info("Selected Rate Plan : " + RatePlan);
		TapeChart.Click_Search_TapeChart.click();
		steps.add("Click Tape Chart search");
		tapechartLogger.info("Click Tape Chart search");
		try {
			Wait.WaitForElement(driver, OR.TapeChartAvailableSlot);
		} catch (Exception e) {
			TapeChart.Click_Search_TapeChart.click();
			tapechartLogger.info("Again Click Search");
			Wait.explicit_wait_visibilityof_webelement(TapeChart.TapeChartAvailableSlot, driver);
		}
		return steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickAvailableSlot> ' Description: <click on available
	 * slot after searching tape chart> ' Input parameters:(RoomClass name ) '
	 * Return value: void ' Created By: <Adnan Ghaffar> ' CreatedOn:
	 * <05/11/2020>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void clickAvailableSlot(WebDriver driver, String RoomClass) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Elements_CPReservation CPReservation = new Elements_CPReservation(driver);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String CellPath = "//div[text()='" + RoomClass
				+ "'  and contains(@class,'roomclassname')]//parent::*//following-sibling::"
				+ "div//following-sibling::div[contains(@class,'DatesContainer')]//ancestor::"
				+ "div[contains(@class,'tapechartdatecell Available')]";
		Wait.WaitForElement(driver, CellPath);
		WebElement AvailableSlot = driver.findElement(By.xpath(CellPath));
		Wait.waitForElementToBeClickable(By.xpath(CellPath), driver);
		try {
			Utility.ScrollToElement(AvailableSlot, driver);
			try {
				AvailableSlot.click();
			} catch (Exception e) {
				jse.executeScript("window.scrollBy(0,-300)");
				AvailableSlot.click();
				System.out.println("Scrol back 300");

			}
			Wait.WaitForElement(driver, OR.ReservationDetailPage);
		} catch (Exception e) {
			if (TapeChart.Rules_Broken_popup.isDisplayed()) {
				Wait.WaitForElement(driver, OR.Rules_Broken_popup);
				Wait.waitForElementToBeClickable(By.xpath(OR.Click_Book_icon_Tapechart), driver);
				Utility.ScrollToElement(TapeChart.Click_Book_icon_Tapechart, driver);
				TapeChart.Click_Book_icon_Tapechart.click();
			} else {
				System.out.println("Rules are not broken");
			}
		}
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_GuestSalutation);
		assertTrue(CPReservation.CP_NewReservation_GuestSalutation.isDisplayed(), "Reservation Page didn't load");

	}

	public HashMap<String, String> getTapChartRates(WebDriver driver, String roomClassAbbrivation, String checkInDate,
			String checkOutDate, String adults, String children, String ratePlan, String DateFormatType,
			int numberofDays) throws ParseException, InterruptedException {
		HashMap<String, String> ratesAgainstDate = new HashMap<String, String>();
		Elements_TapeChart tapeChart = new Elements_TapeChart(driver);

		ArrayList<String> toastMessage = new ArrayList<String>();
		String dateStartFrom = "";
		String dateEndAt = "";
		if (DateFormatType.equals("USA")) {

			dateStartFrom = checkInDate.split("/")[1];
			dateEndAt = checkOutDate.split("/")[1];

		} else if (DateFormatType.equals("International")) {

			dateStartFrom = checkInDate.split("/")[0];
			dateEndAt = checkOutDate.split("/")[0];

		}

		if (tapeChart.getToastMessage.size() > 0) {
			if (tapeChart.getToastMessage.get(1).isDisplayed()) {
				String message = tapeChart.getToastMessage.get(1).getText();
				toastMessage.add(message);
				tapechartLogger.info(message);
			}
		} else {
			String ratePath = "//div[contains(text(),'" + roomClassAbbrivation
					+ "')]//following-sibling::div//div[@class='tapechartdatecell']";
			Utility.ScrollToElement(driver.findElement(By.xpath(ratePath)), driver);
			List<WebElement> rateList = driver.findElements(By.xpath(ratePath));
			System.out.println("Size of DategetList:" + tapeChart.getDateAndDayList.size());
			for (int i = 0; i < tapeChart.getDateAndDayList.size(); i++) {

				if (tapeChart.getDateAndDayList.get(i).getText().contains(dateStartFrom)) {

					for (int j = i; j < tapeChart.getDateAndDayList.size(); j++) {
						tapechartLogger.info("Date Added:" + tapeChart.getDateAndDayList.get(j).getText());

						tapechartLogger.info("Rate Added:" + rateList.get(j).getText());
						ratesAgainstDate.put(tapeChart.getDateAndDayList.get(j).getText(),
								Utility.convertDollarToNormalAmount(driver, rateList.get(j).getText()));

						if (tapeChart.getDateAndDayList.get(j).getText().contains(dateEndAt)) {
							break;
						}

					}
				}
			}
		}

		return ratesAgainstDate;

	}

	public ArrayList<String> getDragAndDropReservationPopUpDetails(WebDriver driver) {
		ArrayList<String> detailList = new ArrayList<String>();

		Elements_TapeChart element = new Elements_TapeChart(driver);
		Wait.waitForElementToBeVisibile(By.xpath(NewTapeChart.GET_CHECK_IN_TEXT), driver);

		String getTripTotalPre = element.getTripTotalPre.getText();
		String getTripTotalPost = element.getTripTotalPost.getText();
		getTripTotalPre = Utility.convertDollarToNormalAmount(driver, getTripTotalPre);
		detailList.add(getTripTotalPre);
		tapechartLogger.info(getTripTotalPre);

		try {
			if (element.getTripTotalPost.isDisplayed()) {
				getTripTotalPost = getTripTotalPost.split(" ")[1];
				getTripTotalPost = Utility.convertDollarToNormalAmount(driver, getTripTotalPost);
				detailList.add(getTripTotalPost);
				tapechartLogger.info(getTripTotalPost);
			}
		} catch (Exception e) {
			detailList.add("");
			tapechartLogger.info("Null");
		}

		return detailList;
	}

	public ArrayList<String> selectRateOptionsToApplyRate(WebDriver driver, int index) {
		ArrayList<String> testSteps = new ArrayList<String>();

		Elements_TapeChart element = new Elements_TapeChart(driver);
		Wait.WaitForElement(driver, NewTapeChart.CHANGE_RATE_APPLIED_DROPDOWN_BUTTON);
		element.changeRateAppliedDropDownButton.click();
		tapechartLogger.info(" DropDown Button Clicked");
		testSteps.add("DropDown Button Clicked");

		element.changeRateAppliedDropDownList.get(index).click();
		Wait.WaitForElement(driver, NewTapeChart.CHANGE_RATE_APPLIED_SELECTEC_OPTION);
		tapechartLogger.info("Rate Option Selected:" + element.CHANGE_RATE_APPLIED_SELECTEC_OPTION.getText());

		testSteps.add("Rate Option Selected:" + element.CHANGE_RATE_APPLIED_SELECTEC_OPTION.getText());
		return testSteps;

	}

	public ArrayList<String> verifyRateUpdate(WebDriver driver, int numberofDays, String nightlyRate,
			String updateRatesType, String rateCurrencyType, String rateChangeValue,
			ArrayList<String> beforeUpdateRateValues, ArrayList<String> afterUpdateRateValues) {
		ArrayList<String> testSteps = new ArrayList<String>();

		int rateExpected = 0;

		for (int i = 0; i <= numberofDays; i++) {

			tapechartLogger.info("Nighlty Rate Plan Expected In RatesGrid:" + nightlyRate);
			testSteps.add("Nighlty Rate Plan Expected In RatesGrid:" + nightlyRate);
			String rateFound = afterUpdateRateValues.get(i);
			tapechartLogger.info("Nighlty Rate Plan Found In RatesGrid:" + rateFound);
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

					tapechartLogger.info("Nighlty Rate Plan Expected In Reservation Find Rooms:" + rateExpected);
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
					tapechartLogger.info("Nighlty Rate Plan Expected In Reservation Find Rooms:" + rateExpected);
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

					tapechartLogger.info("Nighlty Rate Plan Expected In Reservation Find Rooms:" + rateExpected);
					testSteps.add(
							"Nighlty Rate Plan Expected In Reservation Find Rooms:" + beforeUpdateRateValues.get(i));
					// Need to remove dollar sign here
					tapechartLogger.info("Nighlty Rate Plan Expected In Reservation Find Rooms:" + rateExpected);
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
			tapechartLogger.info("Verified");
			testSteps.add("Verified");

		}
		return testSteps;

	}

	public ArrayList<String> verifyRateChangeInMoveExtendPopUp(WebDriver driver, String changeType,
			ArrayList<String> moveFromRate, ArrayList<String> moveToRate, int taxApplied) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_TapeChart element = new Elements_TapeChart(driver);

		ArrayList<String> rateFound = new ArrayList<String>();
		String rateToVerify = "0.0";
		// Loop to select each rate change applied option 1-NoRateChange 2-
		// Recalculate Rate 3-Change Rate for new Date

		for (int i = 0; i < element.changeRateAppliedDropDownList.size(); i++) {
			selectRateOptionsToApplyRate(driver, i);
			rateFound = getDragAndDropReservationPopUpDetails(driver);
			// For 0 Index means-> No Rate Changes Applied Values will be
			// same for every scenario
			if (i == 0) {
				for (int pointerOfListFrom = 0; pointerOfListFrom < moveFromRate.size(); pointerOfListFrom++) {
					rateToVerify = (Double.toString(
							Double.parseDouble(rateToVerify) + (Double.parseDouble(moveFromRate.get(pointerOfListFrom))
									+ ((Double.parseDouble(moveFromRate.get(pointerOfListFrom)) * taxApplied) / 100))));

				}

			} else {
				if (changeType.equalsIgnoreCase("Move") || changeType.equalsIgnoreCase("Extend")) {

					// Reservation extend to x days. It will loop to each day
					// rate and apply tax to it and add it
					for (int j = 0; j < moveToRate.size(); j++) {
						rateToVerify = (Double
								.toString(Double.parseDouble(rateToVerify) + (Double.parseDouble(moveToRate.get(j))
										+ ((Double.parseDouble(moveToRate.get(j)) * taxApplied) / 100))));
					}

				}

				if (changeType.equalsIgnoreCase("Reduce")) {
					// Reservation extend to x days. It will loop to each day
					// rate and apply tax to it and add it
					for (int j = 0; j < moveToRate.size(); j++) {
						rateToVerify = (Double
								.toString(Double.parseDouble(rateToVerify) - (Double.parseDouble(moveToRate.get(j))
										+ ((Double.parseDouble(moveToRate.get(j)) * taxApplied) / 100))));

					}
					rateToVerify = Double.toString(Math.abs(Double.parseDouble(rateToVerify)));

				}

			}
			assertEquals(rateToVerify.equals(rateFound.get(0)), true, "Failed: No Rate Change Value didn't Match");
			tapechartLogger.info("Verified No Rate Change Value:" + rateFound.get(0));
			testSteps.add("Verified No Rate Change Value:" + rateFound.get(0));
		}

		return testSteps;
	}

}
