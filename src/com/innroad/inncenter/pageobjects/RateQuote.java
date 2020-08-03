package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.innroad.inncenter.interfaces.IRateQuote;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_RateQuote;
import com.innroad.inncenter.webelements.Elements_Reservation;
import com.innroad.inncenter.webelements.Elements_TapeChart;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class RateQuote implements IRateQuote {

	public static Logger rateQuoteLogger = Logger.getLogger("RateQuote");
	ArrayList<String> test_steps=new ArrayList<>();
	
	public void searchDetails(WebDriver driver, String RateQuoteNights, String RateQuoteAdults,
			String RateQuoteChildren, String RateQuoteRatePlanList, String RateQuotePromoCode)
			throws InterruptedException {

		Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);
		Wait.explicit_wait_visibilityof_webelement_150(RateQuotePage.Click_Arrive_DatePicker, driver);
		Utility.ScrollToElement(RateQuotePage.Click_Arrive_DatePicker, driver);
		RateQuotePage.Click_Arrive_DatePicker.click();
		RateQuotePage.Click_Today.click();

		RateQuotePage.Enter_RateQuoteNights.clear();

		RateQuotePage.Enter_RateQuoteNights.sendKeys(RateQuoteNights);

		RateQuotePage.Enter_RateQuoteAdults.clear();
		RateQuotePage.Enter_RateQuoteAdults.sendKeys(RateQuoteAdults);

		RateQuotePage.Enter_RateQuoteChildren.clear();
		RateQuotePage.Enter_RateQuoteChildren.sendKeys(RateQuoteChildren);
		new Select(RateQuotePage.Select_RateQuoteRatePlanList).selectByVisibleText(RateQuoteRatePlanList);

		RateQuotePage.Enter_RateQuotePromoCode.clear();
		RateQuotePage.Enter_RateQuotePromoCode.sendKeys(RateQuotePromoCode);

		rateQuoteLogger.info("Entered inputs for RateQuote Search fields");
	}

	public void searchDetails(WebDriver driver, ExtentTest test, String RateQuoteNights, String RateQuoteAdults,
			String RateQuoteChildren, String RateQuoteRatePlanList, String RateQuotePromoCode) {
		Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);

		// Wait.WaitForElement(driver, OR.Click_Arrive_DatePicker);
		RateQuotePage.Click_Arrive_DatePicker.click();

		// Wait.WaitForElement(driver, OR.Click_Today);
		RateQuotePage.Click_Today.click();
		rateQuoteLogger.info("Clicked on Today in datepicker");

		Wait.WaitForElement(driver, OR.Enter_RateQuoteNights);
		RateQuotePage.Enter_RateQuoteNights.clear();
		RateQuotePage.Enter_RateQuoteNights.sendKeys(RateQuoteNights);
		rateQuoteLogger.info("Number of Rate Quote nights : " + RateQuoteNights);

		Wait.WaitForElement(driver, OR.Enter_RateQuoteAdults);
		RateQuotePage.Enter_RateQuoteAdults.clear();
		RateQuotePage.Enter_RateQuoteAdults.sendKeys(RateQuoteAdults);
		rateQuoteLogger.info("Number of Rate Quote adults : " + RateQuoteAdults);

		Wait.WaitForElement(driver, OR.Enter_RateQuoteChildren);
		RateQuotePage.Enter_RateQuoteChildren.clear();
		RateQuotePage.Enter_RateQuoteChildren.sendKeys(RateQuoteChildren);
		new Select(RateQuotePage.Select_RateQuoteRatePlanList).selectByVisibleText(RateQuoteRatePlanList);
		rateQuoteLogger.info("Number of Rate Quote children : " + RateQuoteChildren);

		Wait.WaitForElement(driver, OR.Enter_RateQuotePromoCode);
		RateQuotePage.Enter_RateQuotePromoCode.sendKeys(RateQuotePromoCode);
		rateQuoteLogger.info("Number of Rate Quote promocode : " + RateQuotePromoCode);

	}

	public String getAvailableRoomsCount(WebDriver driver, ExtentTest test, String RoomClassName) {
		Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);

		String availableRoomsQuoteRes = "(//a[text()='" + RoomClassName + "']//ancestor::tr//following-sibling::td)[3]";
		//WebElement xpath=driver.findElement(By.xpath(availableRoomsQuoteRes));
		Wait.WaitForElement(driver, availableRoomsQuoteRes);
		String availableRoomsNewQuoteRes = driver.findElement(By.xpath(availableRoomsQuoteRes)).getText();
		rateQuoteLogger.info("availableRoomsQuoteRes: " + availableRoomsNewQuoteRes);
		return availableRoomsNewQuoteRes;

	}


	public String getTotalRate(WebDriver driver, ExtentTest test, String RoomClassName) {
		Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);

		String roomClassTotalRate = "(//a[text()='" + RoomClassName + "']//ancestor::tr//following-sibling::td)[6]";
		String totalRate = driver.findElement(By.xpath(roomClassTotalRate)).getText();
		rateQuoteLogger.info("Total Rate: " + totalRate);
		test_steps.add("Total Rate: " + totalRate);
		return totalRate;
	}

	public void uncheckAssignRooms(WebDriver driver) {

		Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);

		if (RateQuotePage.Check_RateQuoteAssignRooms.isSelected()) {

			Wait.WaitForElement(driver, OR.Check_RateQuoteAssignRooms);

			RateQuotePage.Check_RateQuoteAssignRooms.click();

			rateQuoteLogger.info(" Unchecked Assign Rooms CheckBox ");

		}
	}

	public void clearRateQuoteDetails(WebDriver driver) {

		Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);

		RateQuotePage.Click_clearRateQuote.clear();

	}

	public void ClickOnSearch(WebDriver driver) {

		Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);

		RateQuotePage.Click_searchRateQuote.click();
		Wait.explicit_wait_xpath(OR.Verify_Room_Grid, driver);

	}

	public void SearchRateQuoteDetails(WebDriver driver, ExtentTest test) {

		Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);

		Wait.WaitForElement(driver, OR.Click_searchRateQuote);
		RateQuotePage.Click_searchRateQuote.click();
		rateQuoteLogger.info("Click Search");
		Wait.explicit_wait_xpath(OR.Verify_Room_Grid, driver);
	}

	public void clickBookicon(WebDriver driver, String RoomClassName) throws InterruptedException {

		Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);
		/*
		 * String Propertyid= RateQuotePage.Get_Property_Id.getAttribute("propertyid");
		 * System.out.println(Propertyid);
		 */
		String BookButtonPath = "//a[text()='" + RoomClassName
				+ "']//ancestor::tr//following-sibling::button[@title='Book']";
		Wait.WaitForElement(driver, BookButtonPath);
		Wait.explicit_wait_xpath(BookButtonPath, driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(BookButtonPath)), driver);
		WebElement BookButton = driver.findElement(By.xpath(BookButtonPath));
		Utility.ScrollToElement(BookButton, driver);
		BookButton.click();

		try {
			Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
		} catch (Exception e) {
			rateQuoteLogger.info("Rules Satisfied");
		}
		Wait.wait3Second();
		if (RateQuotePage.Verify_RulesBroken_Popup.isDisplayed()) {
			RateQuotePage.Click_Rate_Quote_Book.click();
			Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
			Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);
		} else {
			rateQuoteLogger.info("Satisfied Rules");
		}

		try {
			Wait.explicit_wait_xpath(OR.Verify_OverBook_popup, driver);
		} catch (Exception e) {
			rateQuoteLogger.info("Rooms are available");
		}
		Wait.wait3Second();
		if (RateQuotePage.Verify_OverBook_popup.isDisplayed()) {
			RateQuotePage.Click_Continue_OverBook_Popup.click();
			Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
			Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);
		} else {
			rateQuoteLogger.info("Satisfied Rules");
		}
	}

	public void clickQuoteIcon(WebDriver driver, ExtentTest test) throws InterruptedException {

		Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);
		/*
		 * String Propertyid= RateQuotePage.Get_Property_Id.getAttribute("propertyid");
		 * System.out.println(Propertyid);
		 */

		Wait.WaitForElement(driver, OR.Click_First_Quote_Icon);
		Utility.ScrollToElement(RateQuotePage.Click_First_Quote_Icon, driver);
		RateQuotePage.Click_First_Quote_Icon.click();
		rateQuoteLogger.info("Click on Quote");

		try {
			Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
		} catch (Exception e) {
			// System.out.println("Rules Satisfied");
		}
		Wait.wait3Second();
		if (RateQuotePage.Verify_RulesBroken_Popup.isDisplayed()) {
			RateQuotePage.Click_RuleBroken_Quote.click();
			// Wait.explicit_wait_xpath(OR.Click_First_Quote_Icon);
			// RateQuotePage.Click_First_Quote_Icon.click();
			rateQuoteLogger.info("Click on Quote");

			Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
			Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);
		} else {
			// System.out.println("Satisfied Rules");
		}

		try {
			Wait.explicit_wait_xpath(OR.Verify_OverBook_popup, driver);
		} catch (Exception e) {
			// System.out.println("Rooms are available");
		}
		Wait.wait3Second();
		if (RateQuotePage.Verify_OverBook_popup.isDisplayed()) {
			RateQuotePage.Click_Continue_OverBook_Popup.click();
			rateQuoteLogger.info("Click on Over Book");

			Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
			Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);
		} else {
			// System.out.println("Satisfied Rules");
		}
		try {

			Select sel = new Select(driver.findElement(By.xpath(OR.Get_QuoteReservation_Status)));

			WebElement ele = sel.getFirstSelectedOption();

			String str = ele.getText();
			// System.out.println(str);
			assertTrue(str.equalsIgnoreCase("Quote"));
			rateQuoteLogger.info("Reservation status : " + str);

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Reservation status is not Quote");
		}
	}

	public void clickQuoteIcon(WebDriver driver, ExtentTest test, String RoomClassName) throws InterruptedException {

		Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);
		/*
		 * String Propertyid= RateQuotePage.Get_Property_Id.getAttribute("propertyid");
		 * System.out.println(Propertyid);
		 */

		String QuoteButtonPath = "//a[text()='" + RoomClassName
				+ "']//ancestor::tr//following-sibling::td[@class='action_btns text-center']/button[@title='Quote']";
		WebElement QuoteButton = driver.findElement(By.xpath(QuoteButtonPath));
		Utility.ScrollToElement(QuoteButton, driver);
		QuoteButton.click();
		rateQuoteLogger.info("Click on Quote");

		try {
			Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
		} catch (Exception e) {
			// System.out.println("Rules Satisfied");
		}
		Wait.wait3Second();
		if (RateQuotePage.Verify_RulesBroken_Popup.isDisplayed()) {
			RateQuotePage.Click_RuleBroken_Quote.click();
			// Wait.explicit_wait_xpath(OR.Click_First_Quote_Icon);
			// RateQuotePage.Click_First_Quote_Icon.click();
			rateQuoteLogger.info("Click on Quote");

			Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
			Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);
		} else {
			// System.out.println("Satisfied Rules");
		}

		try {
			Wait.explicit_wait_xpath(OR.Verify_OverBook_popup, driver);
		} catch (Exception e) {
			// System.out.println("Rooms are available");
		}
		Wait.wait3Second();
		if (RateQuotePage.Verify_OverBook_popup.isDisplayed()) {
			RateQuotePage.Click_Continue_OverBook_Popup.click();
			rateQuoteLogger.info("Click on Over Book");

			Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
			Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);
		} else {
			// System.out.println("Satisfied Rules");
		}
		try {

			Select sel = new Select(driver.findElement(By.xpath(OR.Get_QuoteReservation_Status)));

			WebElement ele = sel.getFirstSelectedOption();

			String str = ele.getText();
			// System.out.println(str);
			assertTrue(str.equalsIgnoreCase("Quote"));
			rateQuoteLogger.info("Reservation status : " + str);

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Reservation status is not Quote");
		}
	}

	public ArrayList<String> VerifyRate_NewQuote(WebDriver driver, String RateValue, String RoomClass,
			ArrayList<String> test_steps) throws InterruptedException {

		String RoomClassRatePath = "//a[text()='" + RoomClass
				+ "']//..//..//following-sibling::td[contains(@id,'td_TotalRate')]";
		WebElement element_editRate = driver.findElement(By.xpath(RoomClassRatePath));
		Wait.explicit_wait_visibilityof_webelement(element_editRate, driver);
		Utility.ScrollToElement(element_editRate, driver);
		String NewSetRate = element_editRate.getText();
		System.out.println(NewSetRate);
		assertEquals(NewSetRate, "$ " + RateValue + ".00", "Failed:New Rate Value is not correct");
		test_steps.add("Succussfully Verified Rate Plan in New Quote");

		WebElement element_btnBook = driver.findElement(
				By.xpath("// a[text()='" + RoomClass + "']//..//..//following-sibling::td//button[@title='Book']"));
		WebElement element_btnQuote = driver.findElement(
				By.xpath("// a[text()='" + RoomClass + "']//..//..//following-sibling::td//button[@title='Quote']"));

		assertTrue(element_btnBook.isDisplayed(), "Book button is not displaying under options");
		test_steps.add("Book button is displaying under options");

		assertTrue(element_btnQuote.isDisplayed(), "Quote button is not displaying under options");
		test_steps.add("Quote button is displaying under options");
		
		element_btnBook.click();

		return test_steps;

	}


	public ArrayList<String> VerifyRule(WebDriver driver, String Night, String RuleName, String RuleDescription)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);
		Wait.wait2Second();
		RateQuotePage.Click_Arrive_DatePicker.click();
		RateQuotePage.Click_Today.click();
		rateQuoteLogger.info("Arrive Date Clicked");
		test_steps.add("Arrive Date Clicked");

		RateQuotePage.Click_searchRateQuote.click();
		rateQuoteLogger.info("Rate Quote Search Clicked");
		test_steps.add("Rate Quote Search Clicked");

		Wait.explicit_wait_xpath(OR.Verify_Room_Grid, driver);

		String btnPath = "//*[@id='tblRateQuoteGrid']//tr/td[5][.='Double Bed Room']/following-sibling::td[contains(@class,'rules_btns')]//a";

		Wait.explicit_wait_xpath(btnPath, driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(btnPath)), driver);
		
		assertEquals(driver.findElement(By.xpath(btnPath)).getCssValue("color"), "rgba(224, 34, 34, 1)",
				"Color not Matched, should be red");
		rateQuoteLogger.info("Rate Quote rule Color Red");
		test_steps.add("Rate Quote rule Color Red");
		driver.findElement(By.xpath(btnPath)).click();

		rateQuoteLogger.info("Rate Quote rule Clicked");
		test_steps.add("Rate Quote rule Clicked");
		Wait.wait2Second();
		Wait.explicit_wait_xpath("//*[@id='myModalLabelforRuleMessage']", driver);
		String ruleText = driver.findElement(By.xpath("//*[@id='myModalLabelforRuleMessage']")).getText();
		System.out.println(ruleText);
		assertEquals(ruleText, "Rules Broken", "Rule does not broken");
		String ruleName = driver
				.findElement(By.xpath(
						"//*[@id='ruleMessageForInnroad']//table/tbody/tr/td[contains(@data-bind,'text: RuleName')]"))
				.getText();
		assertEquals(ruleName, RuleName, "Rule Not Verified, RuleName not matched");
		String ruleDesc = driver.findElement(By.xpath(
				"//*[@id='ruleMessageForInnroad']//table/tbody/tr/td[contains(@data-bind,'text: RuleDescription')]"))
				.getText();
		assertEquals(ruleDesc, RuleDescription, "Rule Not Verified, Rule Desc not matched");
		rateQuoteLogger.info("Successfully Verified Rules Broken in Rate Quote");
		test_steps.add("Successfully Verified Rules Broken in Rate Quote");

		driver.findElement(By.xpath(OR.OK_Button)).click();
		rateQuoteLogger.info("Rate Quote rule ok Clicked");
		test_steps.add("Rate Quote rule ok Clicked");
		Wait.wait2Second();
		RateQuotePage.Enter_RateQuoteNights.clear();

		RateQuotePage.Enter_RateQuoteNights.sendKeys(Night);
		rateQuoteLogger.info("Rate Quote night : " + Night);
		test_steps.add("Rate Quote night : " + Night);
		Wait.wait2Second();
		RateQuotePage.Click_searchRateQuote.click();
		rateQuoteLogger.info("Rate Quote Search Clicked");
		test_steps.add("Rate Quote Search Clicked");
		Wait.explicit_wait_xpath(OR.Verify_Room_Grid, driver);
		Wait.wait2Second();
		Wait.explicit_wait_xpath(btnPath, driver);
		
		try{
			Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath("//*[@id='bpjscontainer_86']//div[@class='modules_loading']")), 10000);
		}catch (Exception e) {
			System.out.println("no loading");
		}
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(btnPath)), driver);

		assertEquals(driver.findElement(By.xpath(btnPath)).getCssValue("color"), "rgba(37, 177, 59, 1)",
				"Color not Matched, should be green");
		rateQuoteLogger.info("Rate Quote rule Color Green");
		test_steps.add("Rate Quote rule Color Green");
		driver.findElement(By.xpath(btnPath)).click();
		rateQuoteLogger.info("Rate Quote rule Clicked");
		test_steps.add("Rate Quote rule Clicked");
		Wait.wait2Second();
		ruleText = driver.findElement(By.xpath("//*[@id='myModalLabelforRuleMessage']")).getText();
		System.out.println(ruleText);
		assertEquals(ruleText, "Rules Applicable", "Rule does not applicable");
		ruleName = driver
				.findElement(By.xpath(
						"//*[@id='ruleMessageForInnroad']//table/tbody/tr/td[contains(@data-bind,'text: RuleName')]"))
				.getText();
		assertEquals(ruleName, RuleName, "Rule Not Verified, RuleName not matched");
		ruleDesc = driver.findElement(By.xpath(
				"//*[@id='ruleMessageForInnroad']//table/tbody/tr/td[contains(@data-bind,'text: RuleDescription')]"))
				.getText();
		assertEquals(ruleDesc, RuleDescription, "Rule Not Verified, Rule Desc not matched");
		driver.findElement(By.xpath(OR.OK_Button)).click();

		rateQuoteLogger.info("Rate Quote rule ok Clicked");
		test_steps.add("Rate Quote rule ok Clicked");

		rateQuoteLogger.info("Successfully Verified Rules Applicable in Rate Quote");
		test_steps.add("Successfully Verified Rules Applicable in Rate Quote");

		return test_steps;
	}

	public ArrayList<String> verifyRuleBtnIsDisplayed(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);
		Wait.wait2Second();
		RateQuotePage.Click_Arrive_DatePicker.click();
		RateQuotePage.Click_Today.click();
		rateQuoteLogger.info("Arrive Date Clicked");
		test_steps.add("Arrive Date Clicked");

		RateQuotePage.Click_searchRateQuote.click();
		rateQuoteLogger.info("Rate Quote Search Clicked");
		test_steps.add("Rate Quote Search Clicked");

		Wait.explicit_wait_xpath(OR.Verify_Room_Grid, driver);

		WebElement ele2 = driver.findElement(By.xpath(".//*[@id='td_Rules_34746']"));

		int size = ele2.findElements(By.xpath(".//*")).size();
		System.out.println(size);
		if (size > 0) {
			assertTrue(false, "Failed: Rule Button Displayed");
		}
		rateQuoteLogger.info("Verify Rate Quote Rule Button Not Displayed");
		test_steps.add("Verify Rate Quote Rule Button Not Displayed");
		return test_steps;
	}

	public ArrayList<String> verifyRuleBtnIsDisplayed(WebDriver driver, String RackRate) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);
		Wait.wait2Second();
		RateQuotePage.Click_Arrive_DatePicker.click();
		RateQuotePage.Click_Today.click();
		rateQuoteLogger.info("Arrive Date Clicked");
		test_steps.add("Arrive Date Clicked");

		new Select(driver
				.findElement(By.xpath("//*[@id=\"tab_1\"]//select[contains(@data-bind,'value: SelectedRatePlan')]")))
						.selectByVisibleText(RackRate);

		RateQuotePage.Click_searchRateQuote.click();
		rateQuoteLogger.info("Rate Quote Search Clicked");
		test_steps.add("Rate Quote Search Clicked");

		Wait.explicit_wait_xpath(OR.Verify_Room_Grid, driver);

		WebElement ele2 = driver.findElement(By.xpath(".//*[@id='td_Rules_34746']"));

		int size = ele2.findElements(By.xpath(".//*")).size();
		System.out.println(size);
		if (size > 0) {
			assertTrue(false, "Failed: Rule Button Displayed");
		}
		rateQuoteLogger.info("Verify Rate Quote Rule Button Not Displayed");
		test_steps.add("Verify Rate Quote Rule Button Not Displayed");
		return test_steps;
	}

	public String getNextDate(int Day) {
		final SimpleDateFormat format = new SimpleDateFormat("dd");
		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, Day);
		return format.format(calendar.getTime());
	}

	public ArrayList<String> verifyRuleDisplayedForOtherSeason(WebDriver driver, int Day) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);
		Wait.wait2Second();
		RateQuotePage.Click_Arrive_DatePicker.click();

		driver.findElement(By.xpath("//td[@class='day'][text()='" + getNextDate(Day) + "']")).click();
		rateQuoteLogger.info("Arrive Date Clicked");
		test_steps.add("Arrive Date Clicked");

		RateQuotePage.Click_searchRateQuote.click();
		rateQuoteLogger.info("Rate Quote Search Clicked");
		test_steps.add("Rate Quote Search Clicked");

		Wait.explicit_wait_xpath(OR.Verify_Room_Grid, driver);

		WebElement ele2 = driver.findElement(By.xpath(".//*[@id='td_Rules_34746']"));

		int size = ele2.findElements(By.xpath(".//*")).size();
		System.out.println(size);

		if (size > 0) {
			assertTrue(false, "Failed: Rule Button Displayed");
		}

		rateQuoteLogger.info("Verify Rate Quote Rule Button Not Displayed");
		test_steps.add("Verify Rate Quote Rule Button Not Displayed");
		return test_steps;
	}

	public void SearchRateQuoteDetails(WebDriver driver) throws InterruptedException {

		Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);
		Wait.explicit_wait_visibilityof_webelement(RateQuotePage.Click_searchRateQuote, driver);
		Utility.ScrollToElement(RateQuotePage.Click_searchRateQuote, driver);
		RateQuotePage.Click_searchRateQuote.click();
		Wait.explicit_wait_xpath(OR.Verify_Room_Grid, driver);

	}

	public void searchDetails(WebDriver driver) {

		Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);
		Wait.explicit_wait_visibilityof_webelement(RateQuotePage.Click_Arrive_DatePicker, driver);
		RateQuotePage.Click_Arrive_DatePicker.click();
		RateQuotePage.Click_Today.click();
		rateQuoteLogger.info("Entered required inputs for RateQuote Search fields");
		Wait.WaitForElement(driver, OR.Click_searchRateQuote);
		RateQuotePage.Click_searchRateQuote.click();
		rateQuoteLogger.info("Click Search");
	}
	
	public void clickQuoteIcon(WebDriver driver, ExtentTest test, String RoomClassName,ArrayList<String>test_steps) throws InterruptedException {

		Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);
		
		String QuoteButtonPath = "//a[text()='" + RoomClassName
				+ "']//ancestor::tr//following-sibling::td[@class='action_btns text-center']/button[@title='Quote']";
		WebElement QuoteButton = driver.findElement(By.xpath(QuoteButtonPath));
		Utility.ScrollToElement(QuoteButton, driver);
		QuoteButton.click();
		rateQuoteLogger.info("Click on Quote");
		test_steps.add("Clicked on Quote Icon");
		try {
			Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
		} catch (Exception e) {
			// System.out.println("Rules Satisfied");
		}
		Wait.wait3Second();
		if (RateQuotePage.Verify_RulesBroken_Popup.isDisplayed()) {
			test_steps.add("RulesBroken Popup is displayed");
			RateQuotePage.Click_RuleBroken_Quote.click();
			rateQuoteLogger.info("Click on Quote");
			test_steps.add("Clicked on Quote button");

			Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
			Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);
			test_steps.add("New Reservation Page is opened");
		} else {
			
		}

		try {
			Wait.explicit_wait_xpath(OR.Verify_OverBook_popup, driver);
		} catch (Exception e) {
			// System.out.println("Rooms are available");
		}
		Wait.wait3Second();
		if (RateQuotePage.Verify_OverBook_popup.isDisplayed()) {
			RateQuotePage.Click_Continue_OverBook_Popup.click();
			rateQuoteLogger.info("Click on Over Book");

			Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
			Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);
			test_steps.add("New Reservation Page is Loaded");
		} else {
			// System.out.println("Satisfied Rules");
		}
		try {

			Select sel = new Select(driver.findElement(By.xpath(OR.Get_QuoteReservation_Status)));

			WebElement ele = sel.getFirstSelectedOption();

			String str = ele.getText();
			// System.out.println(str);
			test_steps.add("Checking the Reservation Status");
			rateQuoteLogger.info("Reservation status : " + str);
			test_steps.add("Reservation Status : "+str);
			assertTrue(str.equalsIgnoreCase("Quote"));
			test_steps.add("Verified the Reservation Status is Quote or not");


		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Reservation status is not Quote");
		}
	}
	
public void VerifyNewRule_NewQuote(WebDriver driver, ArrayList<String> test_steps,String RoomClass,String RuleName,boolean ruleApplicable) throws InterruptedException {
		
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Elements_RateQuote RateQuote = new Elements_RateQuote(driver);
		
		String RateQuoteRules = "//a[contains(text(), '"+RoomClass+"')]/ancestor::td/following-sibling::td[3]";
		WebElement CurrentRule = driver.findElement(By.xpath(RateQuoteRules));
		test_steps.add("Selected Rules Record Related to the "+RoomClass+ " RoomClass");
		
		if(ruleApplicable==true) {
		try {
			rateQuoteLogger.info("CheckRule is able to Clickable");
			test_steps.add("Rule is able to Clickable");
			Wait.explicit_wait_visibilityof_webelement(CurrentRule, driver);
			CurrentRule.click();
			Wait.explicit_wait_visibilityof_webelement(RateQuote.Verify_RulesBroken_Popup, driver);
			if (RateQuote.Rules_Broken_popupQuote.isDisplayed()) {
				
				test_steps.add("Rules Broken PopUP is DisPlayed in NewQuote");
				rateQuoteLogger.info("Rules Broken PopUP is DisPlayed in NewQuote");
				assertEquals(RateQuote.Verify_RulesBroken_Popup.getText(), "Rules Broken", "Rule broken popup is not showing");
				test_steps.add("Verified Rules Broken Popup in NewQuote");
				
				Wait.explicit_wait_visibilityof_webelement(RateQuote.RuleName_NewQuote, driver);
				String finalString=RateQuote.RuleName_NewQuote.getText();
				rateQuoteLogger.info("NewQuote Popup RuleName is: "+finalString);
				assertEquals(RuleName,finalString);
				test_steps.add("NewRule is Available in NewQuote Search With RuleName:  "+finalString);
				
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.Ok_Button_Popup, driver);
				RateQuote.Rules_Broken_popupQuoteOK.click();
				rateQuoteLogger.info("Clicked on OK");
				test_steps.add("Clicked on OK");
				}
			}
			catch(Exception e) {
			
			}
		}
		else {
		
			test_steps.add("Rules Broken PopUP is Not DisPlayed in NewQuote");
			rateQuoteLogger.info("Rules Broken PopUP is Not DisPlayed in NewQuote");
			
			rateQuoteLogger.info("No Rule is available in NewQuote with this RuleName: "+RuleName);
			test_steps.add("No Rule is available in NewQuote with this RuleName: "+RuleName);
			test_steps.add("because for today the NoCheckIn rule is not applicable");
			
		}
	}

public void searchBasedOnEffiectiveDay(WebDriver driver,ArrayList<String>test_steps) throws InterruptedException {
	
	Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);
	String onlyDay=Utility.GettingWeekDay(driver);
	Wait.explicit_wait_visibilityof_webelement(RateQuotePage.Click_Arrive_DatePicker, driver);

	RateQuotePage.Click_Arrive_DatePicker.click();
	rateQuoteLogger.info("Clicked Arrival");
	test_steps.add("Clicked on Arrival Date Picker");
	test_steps.add("NoCheckIn rule is Effective on only Mon/Tue/Wed day Only");
	test_steps.add("Today is "+onlyDay+ "day");
	
	if((onlyDay.equals("Mon")) || (onlyDay.equals("Tue")) || (onlyDay.equals("Wed"))) {
		rateQuoteLogger.info("Enter if");
		RateQuotePage.Click_Today.click();
		test_steps.add("So Selected Today");
		
	}
	
	else {
		rateQuoteLogger.info("Enter else Bcz today is not mon,tue,wed");
		String NextMondayDate=Utility.GettingNextMonday(driver);
		String cellPath="//td[contains(@class,'day') and @title=contains(text(),'"+NextMondayDate+"')]";
		WebElement Next_MonDayDate=driver.findElement(By.xpath(cellPath));
		Wait.explicit_wait_visibilityof_webelement(Next_MonDayDate, driver);
		Next_MonDayDate.click();
		rateQuoteLogger.info("So Next Monday "+NextMondayDate+"th is Selected");
	}
	
	Wait.explicit_wait_xpath(OR.Click_searchRateQuote, driver);
	RateQuotePage.Click_searchRateQuote.click();
	rateQuoteLogger.info("Click Search");
}

public void searchDetailsAfterSeason(WebDriver driver,ArrayList<String>test_steps) throws InterruptedException {
	   
	Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);
	
	String onlyDate=Utility.GeetingNextWeek(driver);
	int nextDayDate=Integer.parseInt(onlyDate);
	nextDayDate++;
	
	rateQuoteLogger.info("Next Week Next Day is "+nextDayDate+"th");
	test_steps.add("Next Week Next Day is "+nextDayDate+"th");
	
	RateQuotePage.Click_Arrive_DatePicker.click();
	test_steps.add("Clicked on DatePicker");
	String cellPath="//div[@class='datepicker-days']//td[contains(text(),'"+nextDayDate+"')]";
	WebElement Next_Date=driver.findElement(By.xpath(cellPath));
	Next_Date.click();
	rateQuoteLogger.info("Next Week Next day "+nextDayDate+"th is Selected");
	test_steps.add("Next Week Next day "+nextDayDate+"th is Selected");
			
	Wait.WaitForElement(driver, OR.Click_searchRateQuote);
	RateQuotePage.Click_searchRateQuote.click();
	rateQuoteLogger.info("Click Search");
	test_steps.add("Clicked on Search");
}

public void searchDetails(WebDriver driver,String RatePlan,ArrayList<String>test_steps) {

	Elements_RateQuote RateQuotePage = new Elements_RateQuote(driver);

	RateQuotePage.Click_Arrive_DatePicker.click();
	RateQuotePage.Click_Today.click();
	test_steps.add("Selected Today From the DatePicker");
	Wait.explicit_wait_visibilityof_webelement(RateQuotePage.Click_RatePlan, driver);
	RateQuotePage.Click_RatePlan.click();
	new Select(RateQuotePage.Click_RatePlan).selectByVisibleText(RatePlan);
	rateQuoteLogger.info("Selected RatePlan : "+RatePlan);
	test_steps.add("Selected RatePlan : "+RatePlan);

//	String RatePlanList="//select[contains(@data-bind,'RateQuoteRatePlanList')]//option[contains(text(),'"+RatePlan+"')]";
//	WebElement CurrentRatePlan = driver.findElement(By.xpath(RatePlanList));
//	Wait.explicit_wait_visibilityof_webelement(CurrentRatePlan, driver);
	Wait.WaitForElement(driver, OR.Click_searchRateQuote);
	RateQuotePage.Click_searchRateQuote.click();
	rateQuoteLogger.info("Click on Search");
	test_steps.add("Clicked on Search  in NewQuote");
}

public void VerifyNewRateInNewQuote(WebDriver driver,ArrayList<String> test_steps, String baseAmount,String RoomClass) {
	
	rateQuoteLogger.info("Checking for the Available RoomClass Total Rate");
	test_steps.add("Checking for the Available RoomClass Total Rate");
	String RatePath= "//a[contains(text(), '"+RoomClass+"')]/ancestor::td/following-sibling::td[2]";
	Wait.WaitForElement(driver, RatePath);
	String TotalRatePath = driver.findElement(By.xpath(RatePath)).getText();
	String finalString=Utility.convertDollarToNormalAmount(driver, TotalRatePath);
	rateQuoteLogger.info("After Converting to Normal Amount in NewQuote: "+finalString);
	assertEquals(baseAmount,finalString);
	rateQuoteLogger.info("NewRate Value in NewQuote: "+TotalRatePath);
	test_steps.add("New Rate is Available in NewQuote Search with BaseAmount of "+ TotalRatePath);
}

}
