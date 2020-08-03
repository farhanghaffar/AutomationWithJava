package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.innroad.inncenter.interfaces.IReports;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Reports;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_On_All_Navigation;
import com.innroad.inncenter.webelements.Elements_Reports;
import com.snowtide.PDF;
import com.snowtide.pdf.Document;
import com.snowtide.pdf.Page;
import com.snowtide.pdf.layout.Image;


public class Reports implements IReports {

	public static Logger reportLogger = Logger.getLogger("Reports");

	public ArrayList<String> VerifyCityLedger(WebDriver driver, String LedgerType,ArrayList<String>  test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);

		elements_Reports.AccountsBalance.click();

		assertEquals(elements_Reports.AccountBalance_Header.getText(), "Account Balance Summary",
				"Account balance header does not display");
		new Select(elements_Reports.Select_LedgerType).selectByVisibleText(LedgerType);
		return AccountBalanceGoButton(driver,test_steps);

	}

	@Override
	public void VerifyCurrentReservationReport(WebDriver driver, String LedgerType) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);

		elements_Reports.AccountsBalance.click();

		assertEquals(elements_Reports.AccountBalance_Header.getText(), "Account Balance Summary",
				"Account balance header does not display");
		new Select(elements_Reports.Select_LedgerType).selectByVisibleText(LedgerType);
		if (!elements_Reports.ReservationType_Current.isSelected()) {
			elements_Reports.ReservationType_Current.click();

		}
		if (elements_Reports.ReservationType_Past.isSelected() && elements_Reports.ReservationType_Future.isSelected()
				&& elements_Reports.Reservation_Pending.isSelected()) {
			elements_Reports.ReservationType_Past.click();
			elements_Reports.ReservationType_Future.click();
			elements_Reports.Reservation_Pending.click();

		}

	}

	@Override
	public void VerifyPastReservationReport(WebDriver driver, String LedgerType) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.AccountsBalance.click();
		assertEquals(elements_Reports.AccountBalance_Header.getText(), "Account Balance Summary",
				"Account balance header does not display");

		new Select(elements_Reports.Select_LedgerType).selectByVisibleText(LedgerType);

		if (!elements_Reports.ReservationType_Past.isSelected()) {

			elements_Reports.ReservationType_Past.click();

		}
		if (elements_Reports.ReservationType_Current.isSelected()
				&& elements_Reports.ReservationType_Future.isSelected()
				&& elements_Reports.Reservation_Pending.isSelected()) {

			elements_Reports.ReservationType_Current.click();
			elements_Reports.ReservationType_Future.click();
			elements_Reports.Reservation_Pending.click();

		}

	}

	@Override
	public void VerifyFutureReservationReport(WebDriver driver, String LedgerType) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.AccountsBalance.click();
		assertEquals(elements_Reports.AccountBalance_Header.getText(), "Account Balance Summary",
				"Account balance header does not display");

		new Select(elements_Reports.Select_LedgerType).selectByVisibleText(LedgerType);

		if (!elements_Reports.ReservationType_Future.isSelected()) {

			elements_Reports.ReservationType_Future.click();

		}
		if (elements_Reports.ReservationType_Current.isSelected() && elements_Reports.ReservationType_Past.isSelected()
				&& elements_Reports.Reservation_Pending.isSelected()) {

			elements_Reports.ReservationType_Current.click();
			elements_Reports.ReservationType_Past.click();
			elements_Reports.Reservation_Pending.click();

		}

	}

	public ArrayList<String> BalanceLedgerReport(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.LedgerBalance_CheckIn.click();
		Wait.wait2Second();
		elements_Reports.ActivDate.click();
		Wait.wait2Second();
		test_steps.add("Select date for report");
		Wait.wait2Second();
		elements_Reports.Incidentials_Checkbox.click();
		return test_steps;

	}

	@Override
	public ArrayList<String> BalanceLedgerTab(WebDriver driver,ArrayList<String> test_steps) {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.LedgerBalance_Tab.click();
		test_steps.add("Navigate to ledger balance");
		reportLogger.info("Navigate to ledger balance");
		Wait.waitUntilPresenceOfElementLocated(OR.LagerBalacePage, driver);
		assertEquals(elements_Reports.LagerBalacePage.getText(), "Ledger Balances",
				"Ledger balance page does not find");
		return test_steps;

	}

	public ArrayList<String> BalanceLedgerGoButton(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", elements_Reports.GoButton);
		elements_Reports.GoButton.click();
		Wait.wait10Second();
		test_steps.add("Click on Go button");
		reportLogger.info("Click on Go button");
		try {
			driver.switchTo().frame("ifrmAccountStatement");
			assertTrue(elements_Reports.GeneratedReport.isDisplayed(), "Legder Balance Report isn't generated");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			if (elements_Reports.NoDataAvailable_Message.isDisplayed()) {
				assertTrue(false, "Legder Balance Report isn't generated  Error: "
						+ elements_Reports.NoDataAvailable_Message.getText());
			} else {
				assertTrue(elements_Reports.NoDataAvailable_Message.isDisplayed(),
						"Legder Balance Report isn't generated");
			}

		}
		return test_steps;
	}

	public ArrayList<String>  BalanceLedgeWithShiftReport(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.LedgerBalance_CheckIn.click();
		Wait.wait2Second();
		elements_Reports.ActivDate.click();
		Wait.wait2Second();
		test_steps.add("Select date for report");
		Wait.wait2Second();
		elements_Reports.ShiftReport_RadioButton.click();
		elements_Reports.Incidentials_Checkbox.click();
		return test_steps;

	}


	public ArrayList<String> BalanceLedgeWithDepartmentReport(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.LedgerBalance_CheckIn.click();
		Wait.wait2Second();
		elements_Reports.ActivDate.click();
		Wait.wait2Second();
		test_steps.add("Select date for report");
		Wait.wait2Second();
		elements_Reports.Incidentials_Checkbox.click();
		return test_steps;

	}

	public ArrayList<String> LedgerBalance_DepartmentReportUnderSummary(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.LedgerBalance_CheckIn.click();
		Wait.wait2Second();
		elements_Reports.ActivDate.click();
		Wait.wait2Second();
		test_steps.add("Select date for report");
		Wait.wait2Second();
		elements_Reports.Summary_RadioButton.click();
		assertTrue(elements_Reports.Summary_RadioButton.isSelected(), "Failed : Summary is not Enabled");
		Wait.wait2Second();
		elements_Reports.DepartmentReport_RadioButton.click();
		assertTrue(elements_Reports.DepartmentReport_RadioButton.isSelected(),
				"Failed : Department Report is not Enabled");
		elements_Reports.Incidentials_Checkbox.click();
		return test_steps;

	}

	public ArrayList<String> LedgerBalance_Dates(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.LedgerBalance_FromDate.click();
		Wait.wait2Second();
		elements_Reports.FirstOfTheMonth.click();
		Wait.wait2Second();
		test_steps.add("Select From date for report");
		elements_Reports.LedgerBalance_ToDate.click();
		Wait.wait2Second();
		elements_Reports.ActiveDate.click();
		Wait.wait2Second();
		test_steps.add("Select To date for report");
		Wait.wait2Second();
		return test_steps;

	}

	public void LedgerBalance_Incidental(WebDriver driver) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		if (!elements_Reports.Incidentials_Checkbox.isSelected()) {
			elements_Reports.Incidentials_Checkbox.click();
		}
		assertTrue(elements_Reports.Incidentials_Checkbox.isSelected(), "Failed : Incidental CheckBox is not Selected");

	}

	public void LedgerBalanceReport_ItemStatus(WebDriver driver, String ItemStatus) throws InterruptedException {

		List<WebElement> ItemStatusList = driver.findElements(By.xpath(OR.LedgerBalance_ItemStatus));
		for (WebElement item : ItemStatusList) {
			if (item.isSelected()) {
				item.click();
			}
		}
		Wait.wait2Second();
		String ItemStatusPath = "(//label[contains(text(),'" + ItemStatus
				+ "')]//preceding-sibling::input[@type='checkbox'])[last()]";
		driver.findElement(By.xpath(ItemStatusPath)).click();
		assertTrue(driver.findElement(By.xpath(ItemStatusPath)).isSelected(),
				"Failed : Item Status : " + ItemStatus + " is not Selected");

	}

	public void LedgerBalanceReport_AccountType(WebDriver driver, String AccountType) throws InterruptedException {

		List<WebElement> AccountTypeList = driver.findElements(By.xpath(OR.LedgerBalance_AccountType));
		for (WebElement type : AccountTypeList) {
			if (type.isSelected()) {
				type.click();
			}
		}
		Wait.wait2Second();
		String AccountTypePath = "(//label[text()='" + AccountType
				+ "']//preceding-sibling::input[@type='checkbox'])[last()]";
		driver.findElement(By.xpath(AccountTypePath)).click();
		System.out.println(AccountTypePath);
		assertTrue(driver.findElement(By.xpath(AccountTypePath)).isSelected(),
				"Failed : Account Type : " + AccountType + " is not Selected");

	}

	public void LedgerBalanceReport_ReportType(WebDriver driver, String ReportType) throws InterruptedException {

		String ReportTypePath = "(//label[text()='" + ReportType
				+ "']//preceding-sibling::input[@type='radio'])[last()]";
		if (!driver.findElement(By.xpath(ReportTypePath)).isSelected()) {
			driver.findElement(By.xpath(ReportTypePath)).click();
		}
		assertTrue(driver.findElement(By.xpath(ReportTypePath)).isSelected(),
				"Failed : Report Type : " + ReportType + " is not Selected");

	}

	public ArrayList<String> LedgerBalance_DepartmentReportUnderDetail(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.LedgerBalance_CheckIn.click();
		Wait.wait2Second();
		elements_Reports.FirstOfTheMonth.click();
		Wait.wait2Second();
		test_steps.add("Select date for report");
		Wait.wait2Second();
		elements_Reports.Detail_RadioButton.click();
		assertTrue(elements_Reports.Detail_RadioButton.isSelected(), "Failed : Detail is not Enabled");
		Wait.wait2Second();
		elements_Reports.DepartmentReport_RadioButton.click();
		assertTrue(elements_Reports.DepartmentReport_RadioButton.isSelected(),
				"Failed : Department Report is not Enabled");
		elements_Reports.Incidentials_Checkbox.click();
		return test_steps;

	}

	public ArrayList<String> SelectDate(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.LedgerBalance_CheckIn.click();
		Wait.wait2Second();
		String ActiveDate = elements_Reports.ActiveDate.getText();
		if (ActiveDate.equals("1")) {
			Wait.wait1Second();
			elements_Reports.OldDate.click();
		} else {
			int currentDate = Integer.parseInt(ActiveDate);
			currentDate = currentDate - 1;
			String SelectDate = String.valueOf(currentDate);
			driver.findElement(By.xpath("(//td[.='" + SelectDate + "'])[1]")).click();
		}
		Wait.wait2Second();
		test_steps.add("Select date for report");
		reportLogger.info("Select date for report");
		Wait.wait2Second();
		return test_steps;

	}

	public ArrayList<String> BalanceLedgeWithShift_DetailsReport(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.Detail_RadioButton.click();
		elements_Reports.ShiftReport_RadioButton.click();
		elements_Reports.Incidentials_Checkbox.click();

		test_steps.add("Click on shift and details  radio button");
		reportLogger.info("Click on shift and details  radio button");
		return test_steps;

	}

	public ArrayList<String> BalanceLedgeWithDepartment_DetailsReport(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.Detail_RadioButton.click();
		elements_Reports.ShiftReport_RadioButton.click();
		elements_Reports.Incidentials_Checkbox.click();

		test_steps.add("Click on shift and details  radio button");
		reportLogger.info("Click on shift and details  radio button");
		return test_steps;

	}

	@Override
	public void MarchantTrans(WebDriver driver) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.MerchantTrans_Tab, driver);
		elements_Reports.MarchantTrans_Tab.click();

		Wait.waitUntilPresenceOfElementLocated(OR.MerchantPage, driver);
		assertTrue(elements_Reports.MarchantPage.getText().contains("Merchant"), "Merchant page does not display");

	}

	public ArrayList<String> SelectMarchantDate(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.SelectMerchant_Date.click();
		Wait.wait2Second();
		String ActiveDate = elements_Reports.ActiveDate.getText();
		if (ActiveDate.equals("1")) {
			Wait.wait1Second();
			elements_Reports.OldDate.click();
		} else {
			/*
			 * int currentDate = Integer.parseInt(ActiveDate); currentDate =
			 * currentDate - 1; String SelectDate = String.valueOf(currentDate);
			 * driver.findElement(By.xpath("(//td[.='" + SelectDate +
			 * "'])[1]")).click();
			 */
			elements_Reports.FirstOfTheMonth.click();
		}
		Wait.wait2Second();
		test_steps.add("Select date for report");
		reportLogger.info("Select date for report");
		Wait.wait2Second();
		return test_steps;

	}

	@Override
	public void MarchantReports_Auth(WebDriver driver) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.TranstionStatus.click();

	}

	public ArrayList<String> MerchantGoButton(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.GoButton.click();
		Wait.wait10Second();
		test_steps.add("Click on Go button");
		reportLogger.info("Click on Go button");
		// Thread.sleep(6*10000);

		try {
			driver.switchTo().frame("ifrmAccountStatement");
			assertTrue(elements_Reports.GeneratedReport.isDisplayed(), "Merchant Transaction Report isn't generated");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			if (elements_Reports.NoDataAvailable_Message.isDisplayed()) {
				assertTrue(false, "Merchant Transaction Report isn't generated  Error: "
						+ elements_Reports.NoDataAvailable_Message.getText());
			} else {
				assertTrue(elements_Reports.NoDataAvailable_Message.isDisplayed(),
						"Merchant Transaction Report isn't generated");
			}

		}
		return test_steps;

	}

	public void MarchantReports_AuthorizationOnly(WebDriver driver) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.MerchantAuthorized_Checkbox.click();
		assertTrue(elements_Reports.MerchantAuthorized_Checkbox.isSelected(), "Credit is not Selected");
	}

	@Override
	public void MarchantReports_Capture(WebDriver driver) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.MerchantCapture_Checkbox.click();
		assertTrue(elements_Reports.MerchantCapture_Checkbox.isSelected(), "Capture is not Selected");

	}

	@Override
	public void MarchantReports_Credit(WebDriver driver) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.MerchantCredit_Checkbox.click();
		assertTrue(elements_Reports.MerchantCredit_Checkbox.isSelected(), "Credit is not Selected");
	}

	@Override
	public void MarchantReports_Cancel(WebDriver driver) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.MerchantCancel_Checkbox.click();
		assertTrue(elements_Reports.MerchantCancel_Checkbox.isSelected(), "Cancel is not Selected");
	}

	public ArrayList<String> VerifyTransactionTypesCheckboxes(WebDriver driver,ArrayList<String> test_steps) {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		boolean is_selected = elements_Reports.MerchantAuthorized_Checkbox.isSelected();
		if (!is_selected) {
			test_steps.add("Authorized transaction type is not seleted ");
			reportLogger.info("Authorized transaction type is not seleted ");
			assertTrue(is_selected, "Authorized transaction type is not seleted");
		} else {
			test_steps.add("Authorized Selected");
			reportLogger.info("Authorized Selected");
		}
		is_selected = elements_Reports.MerchantCredit_Checkbox.isSelected();
		if (!is_selected) {
			test_steps.add("Credit transaction type is not seleted ");
			reportLogger.info("Credit transaction type is not seleted ");
			assertTrue(is_selected, "Credit transaction type is not seleted");
		} else {
			test_steps.add("Credit Selected");
			reportLogger.info("Credit Selected");
		}
		is_selected = elements_Reports.MerchantCancel_Checkbox.isSelected();
		if (!is_selected) {
			test_steps.add("Cancel transaction type is not seleted ");
			reportLogger.info("Cancel transaction type is not seleted ");
			assertTrue(is_selected, "Cancel transaction type is not seleted");
		} else {
			test_steps.add("Cancel Selected");
			reportLogger.info("Cancel Selected");
		}
		is_selected = elements_Reports.MerchantCapture_Checkbox.isSelected();
		if (!is_selected) {
			test_steps.add("Capture transaction type is not seleted ");
			reportLogger.info("Capture transaction type is not seleted ");
			assertTrue(is_selected, "Capture transaction type is not seleted");
		} else {
			test_steps.add("Capture Selected");
			reportLogger.info("Capture Selected");
		}
		test_steps.add("All types are Selected");
		reportLogger.info("All types are Selected");
		return test_steps;

	}

	// Daily Flash

	@Override
	public void DailyFlash(WebDriver driver) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.DailyFlash_Tab, driver);
		elements_Reports.DailyFlash_Tab.click();

		Wait.waitUntilPresenceOfElementLocated(OR.DailyFlashPage, driver);
		assertTrue(elements_Reports.DailyFlashPage.getText().contains("Daily Flash"),
				"Daily Flash page does not display");

	}

	@Override
	public void SelectDailyFlashDate(WebDriver driver) throws InterruptedException {
		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.SelectDailyFlash_Date.click();
		Wait.wait2Second();
		String ActiveDate = elements_Reports.ActiveDate.getText();
		if (ActiveDate.equals("1")) {
			Wait.wait1Second();
			elements_Reports.OldDate.click();
		} else {
			int currentDate = Integer.parseInt(ActiveDate);
			currentDate = currentDate - 1;
			String SelectDate = String.valueOf(currentDate);
			driver.findElement(By.xpath("(//td[.='" + SelectDate + "'])[1]")).click();
		}
		Wait.wait2Second();
		// System.out.println(elements_Reports.DailyFlash_SelectedDate.getAttribute("value"));
		assertTrue(!elements_Reports.DailyFlash_SelectedDate.getAttribute("value").isEmpty(), "Date is not selected");
		// test_steps.add("Select date ");
		reportLogger.info("Select date ");
		Wait.wait2Second();

	}

	public ArrayList<String> DailyFlashReport(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.DailyFlash_RadioButton.click();
		test_steps.add("Select Report Type: Daily Flash");
		reportLogger.info("Select Report Type: Daily Flash");
		assertTrue(elements_Reports.DailyFlash_RadioButton.isSelected(), "Daily Flash Report Type is not selected");
		return test_steps;

	}

	public ArrayList<String> ManagementTransferReport(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.DailyFlachManagementTransfers_RadioButton.click();
		test_steps.add("Select Report Type: Management Transfers");
		reportLogger.info("Select Report Type: Management Transfers");
		assertTrue(elements_Reports.DailyFlachManagementTransfers_RadioButton.isSelected(),
				"Management Transfers Report Type is not selected");
		return test_steps;

	}

	public ArrayList<String> BreakoutTaxExempt_Revenue(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.DailyFlashBreakoutTaxExemptRev_CheckBox.click();
		test_steps.add("Click: Breakout Tax Exempt Revenue CheckBox");
		reportLogger.info("Click: Breakout Tax Exempt Revenue CheckBox");
		assertTrue(elements_Reports.DailyFlashBreakoutTaxExemptRev_CheckBox.isSelected(),
				"Breakout Tax Exempt Revenue is not selected");
		return test_steps;

	}

	public ArrayList<String> DailyFlashGoButton(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.GoButton.click();
		Wait.wait2Second();
		test_steps.add("Click: Go");
		reportLogger.info("Click: GO");
		Thread.sleep(6 * 10000);
		// driver.manage().window().maximize();
		driver.switchTo().frame("ifrmAccountStatement");
		assertTrue(elements_Reports.DailyFlash_GeneratedReport.isDisplayed(), "Daily Flash Report isn't generated");
		driver.switchTo().defaultContent();
		test_steps.add("Daily Flash Report generated");
		reportLogger.info("Daily Flash Report generated");
		return test_steps;
	}

	public ArrayList<String> VerifyBreakoutTaxExemptRevenueCheckbox(WebDriver driver,ArrayList<String> test_steps) {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		boolean is_selected = elements_Reports.DailyFlashBreakoutTaxExemptRev_CheckBox.isSelected();
		if (!is_selected) {
			test_steps.add("Breakout Tax Exempt Revenue is not seleted ");
			reportLogger.info("Breakout Tax Exempt Revenue is not seleted ");
			assertEquals(is_selected, true, "Breakout Tax Exempt Revenue is not seleted");
		} else {
			test_steps.add("Breakout Tax Exempt Revenue is Selected");
			reportLogger.info("Breakout Tax Exempt Revenue is Selected");
		}
		return test_steps;
	}

	// RoomForecast

	@Override
	public void RoomForecast(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.RoomForecast_Tab, driver);
		elements_Reports.RoomForecast_Tab.click();

		Wait.waitUntilPresenceOfElementLocated(OR.RoomForecastPage, driver);
		assertTrue(elements_Reports.RoomForecastPage.getText().contains("Room Forecast"),
				"Room Forecast page does not display");
		test_steps.add("Click on roomfore case");
	}

	public ArrayList<String> SelectRoomForecast_FromDate(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.SelectRoomForecast_FromDate.click();
		Wait.wait2Second();
		String ActiveDate = elements_Reports.ActiveDate.getText();

		if (ActiveDate.equals("1")) {
			Wait.wait1Second();
			elements_Reports.OldDate.click();
		} else {
			int currentDate = Integer.parseInt(ActiveDate);
			currentDate = currentDate - 1;
			String SelectDate = String.valueOf(currentDate);
			driver.findElement(By.xpath("(//td[.='" + SelectDate + "'])[1]")).click();
		}
		Wait.wait2Second();
		assertTrue(!elements_Reports.RoomForecast_FromDate.getAttribute("value").isEmpty(),
				"From Date is not Selected");
		test_steps.add("Select From date ");
		reportLogger.info("Select From  date");
		Wait.wait2Second();
		return test_steps;
	}

	public ArrayList<String> SelectRoomForecast_ToDate(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.SelectRoomForecast_ToDate.click();
		Wait.wait2Second();
		Date currentdate = new Date();
		int SelectDate = Integer.valueOf(currentdate.toString().substring(8, 10));
		driver.findElement(By.xpath("(//td[.='" + SelectDate + "'])[1]")).click();
		Wait.wait2Second();
		assertTrue(!elements_Reports.RoomForecast_ToDate.getAttribute("value").isEmpty(), "From Date is not Selected");
		test_steps.add("Select TO date ");
		reportLogger.info("Select To date");
		Wait.wait2Second();
		return test_steps;
	}

	public ArrayList<String> RoomForecastGoButton(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.GoButton.click();
		test_steps.add("Click on Go button");
		reportLogger.info("Click on Go button");
		Wait.wait2Second();
		driver.switchTo().frame("ifrmAccountStatement");
		assertTrue(elements_Reports.RoomForecast_GeneratedReport.isDisplayed(), "Report isn't generated");
		driver.switchTo().defaultContent();
		test_steps.add("RoomForecast Report generated");
		reportLogger.info("RoomForecast Report generated");
		return test_steps;
	}

	@Override
	public void NetSales_GroupBy(WebDriver driver, String GroupBy) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		new Select(elements_Reports.NetSales_GroupBy).selectByVisibleText(GroupBy);

	}
	
public ArrayList<String> SelectNetSales_FromDate(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		
		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.NetSales_FromDate.click();
		Wait.wait2Second();
		String ActiveDate = elements_Reports.ActiveDate.getText();

		if (ActiveDate.equals("1")) {
			Wait.wait1Second();
			elements_Reports.OldDate.click();
		} else {
			int currentDate = Integer.parseInt(ActiveDate);
			currentDate = currentDate - 1;
			String SelectDate = String.valueOf(currentDate);
			driver.findElement(By.xpath("(//td[.='" + SelectDate + "'])[1]")).click();
		}
		Wait.wait2Second();
		assertTrue(!elements_Reports.NetSales_Stayon_FromDate.getAttribute("value").isEmpty(),
				"From Date is not Selected");
		test_steps.add("Selected From date ");
		reportLogger.info("Selected From  date");
		Wait.wait2Second();
		return test_steps;
	}

	public ArrayList<String> SelectNetSales_ToDate(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.NetSales_ToDate.click();
		Wait.wait2Second();
		Date currentdate = new Date();
		int SelectDate = Integer.valueOf(currentdate.toString().substring(8, 10));
		driver.findElement(By.xpath("(//td[.='" + SelectDate + "'])[1]")).click();
		Wait.wait2Second();
		assertTrue(!elements_Reports.NetSales_Stayon_ToDate.getAttribute("value").isEmpty(), "To Date is not Selected");
		test_steps.add("Selected To date ");
		reportLogger.info("Selected To date");
		Wait.wait2Second();
		return test_steps;
	}


	

	public void NetSales_ClientType(WebDriver driver, String ClientType) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		new Select(elements_Reports.NetSales_ClientType).selectByVisibleText(ClientType);

	}

	@Override
	public void NetSales_GoButton(WebDriver driver) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.NetSales_GoButton.click();
		Wait.wait2Second();
		driver.manage().window().maximize();
		driver.switchTo().frame("ifrmAccountStatement");
		assertTrue(elements_Reports.NetSales_GenratedReport_GroupByRoom.isDisplayed(), "Report isn't generated");
		driver.switchTo().defaultContent();

	}

	public ArrayList<String> GenerateAdvanceDepositeReport(WebDriver driver,ArrayList<String> test_steps, String reportType, String DetailLevel)
			throws InterruptedException {
		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.Select_FromDate.click();
		Wait.wait2Second();
		elements_Reports.FirstOfTheMonth.click();
		Wait.wait2Second();
		elements_Reports.Select_ToDate.click();
		Wait.wait2Second();
		elements_Reports.ActivDate.click();
		Wait.wait2Second();
		test_steps.add("Select date for report");

		// Select Detail Level
		if (DetailLevel.contains("Summary")) {
			elements_Reports.ADSummary_RadioButton.click();
			assertTrue(elements_Reports.ADSummary_RadioButton.isSelected(), "Failed : Summary is not Enabled");
		} else if (DetailLevel.contains("Detail")) {
			elements_Reports.ADDetail_RadioButton.click();
			assertTrue(elements_Reports.ADDetail_RadioButton.isSelected(), "Failed : Detail is not Enabled");
		}
		Wait.wait2Second();

		// Select Report Type
		if (reportType.contains("Inbound")) {
			elements_Reports.ReportType_Inbound.click();
			assertTrue(elements_Reports.ReportType_Inbound.isSelected(), "Failed : Inbound is not Enabled");
		} else if (reportType.contains("Outbound")) {
			elements_Reports.ReportType_Outbound.click();
			assertTrue(elements_Reports.ReportType_Outbound.isSelected(), "Failed : Outbound is not Enabled");
		} else if (reportType.contains("Net")) {
			elements_Reports.ReportType_Net.click();
			assertTrue(elements_Reports.ReportType_Net.isSelected(), "Failed : Net is not Enabled");
		}
		return test_steps;

	}

	public ArrayList<String> AdvanceDepositGoButton(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.AdvanceDeposite_GoButton.click();
		Wait.wait5Second();
		test_steps.add("Click on Go button");
		reportLogger.info("Click on Go button");
		try {
			driver.switchTo().frame("ifrmAccountStatement");
			assertTrue(elements_Reports.GeneratedReport.isDisplayed(), "Advance Deposite Report isn't generated");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			if (elements_Reports.NoDataAvailable_Message.isDisplayed()) {
				assertTrue(false, "Merchant Transaction Report isn't generated  Error: "
						+ elements_Reports.NoDataAvailable_Message.getText());
			} else {
				assertTrue(elements_Reports.NoDataAvailable_Message.isDisplayed(),
						"Merchant Transaction Report isn't generated");
			}

		}
		return test_steps;

	}

	public void AdvanceDepositeReport(WebDriver driver, String LedgerType, String AccountStatus)
			throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.AccountBalance_EffectiveDate.click();
		Wait.wait2Second();
		elements_Reports.FirstOfTheMonth.click();
		Wait.wait2Second();
		new Select(elements_Reports.Select_LedgerType).selectByVisibleText(LedgerType);
		if (!elements_Reports.ReservationType_Current.isSelected()) {
			elements_Reports.ReservationType_Current.click();

		}
		if (elements_Reports.ReservationType_Past.isSelected() && elements_Reports.ReservationType_Future.isSelected()
				&& elements_Reports.Reservation_Pending.isSelected()) {
			elements_Reports.ReservationType_Past.click();
			elements_Reports.ReservationType_Future.click();
			elements_Reports.Reservation_Pending.click();

		}
		new Select(elements_Reports.AccountStatus).selectByVisibleText(AccountStatus);
		Wait.wait10Second();

	}

	public ArrayList<String> AccountBalanceGoButton(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", elements_Reports.AccountBalanceGo_Button);
		elements_Reports.AccountBalanceGo_Button.click();
		Wait.wait10Second();
		test_steps.add("Click on Go button");
		reportLogger.info("Click on Go button");
		try {
			driver.switchTo().frame("ifrmAccountStatement");
			assertTrue(elements_Reports.GeneratedReport.isDisplayed(), "Account Balance Report isn't generated");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			if (elements_Reports.NoDataAvailable_Message.isDisplayed()) {
				assertTrue(false, "Account Balance Report isn't generated  Error: "
						+ elements_Reports.NoDataAvailable_Message.getText());
			} else {
				assertTrue(elements_Reports.NoDataAvailable_Message.isDisplayed(),
						"Account Balance Report isn't generated");
			}

		}
		return test_steps;
	}

	public void NetSales_Dates(WebDriver driver) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.NetSales_FromDate.click();
		Wait.wait2Second();
		elements_Reports.FirstOfTheMonth.click();
		Wait.wait2Second();
		Utility.app_logs.info("Select From date for report");
		elements_Reports.NetSales_ToDate.click();
		Wait.wait2Second();
		elements_Reports.Today_Day.click();
		Wait.wait2Second();
		Utility.app_logs.info("Select To date for report");

	}

	public void click_DailyFlash(WebDriver driver,ArrayList test_steps){
		Elements_Reports reports = new Elements_Reports(driver);
		Wait.WaitForElement(driver, OR.Reports_DailyFlash);
		reports.Reports_DailyFlash.click();
		test_steps.add("Click on daily flash");
	}

	

	public String verifyGuestStatementReport(WebDriver driver,ArrayList<String>test_steps) throws InterruptedException
	{
		 String pdfFileInText=null;
	List<String> ReportContant= new ArrayList<String>();
	 
		File theNewestFile = null;
		File getFile = null;
		String dataPath=null;
		try
		{
			boolean isExist=Utility.isElementPresent(driver, By.id("embdreportViewer"));
			if(isExist)
			{
				test_steps.add("PDF Report is Displayed");
				Utility.app_logs.info("PDF Report is Displayed");
			}
				else
			{
				test_steps.add("PDF Report is Not Displayed");
				Utility.app_logs.info("PDF Report is Not Displayed");
			}
			if(isExist)
			{
				dataPath=driver.findElement(By.id("embdreportViewer")).getAttribute("src");
			driver.get(dataPath);
			String fileName = Utility.download_status(driver);
			Wait.wait2Second();
			getFile = new File(System.getProperty("user.dir") + File.separator + "externalFiles"
					+ File.separator + "downloadFiles" + File.separator + fileName);
			Utility.fileName=getFile;
			test_steps.add("PDF report file downloaded : "+getFile.getAbsolutePath());
			File dir = new File(System.getProperty("user.dir")+"/externalFiles/downloadFiles");
			FileFilter fileFilter = new WildcardFileFilter("*." + "pdf");
			File[] files = dir.listFiles(fileFilter);
			if (files.length > 0) {
				Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
				theNewestFile = files[0];
			}
			Wait.wait3Second();
			try
			{
				PDDocument document = PDDocument.load(theNewestFile);
				document.getClass();
				if (!document.isEncrypted()) {
					PDFTextStripperByArea stripper = new PDFTextStripperByArea();
					stripper.setSortByPosition(true);
					PDFTextStripper tStripper = new PDFTextStripper();
					pdfFileInText = tStripper.getText(document);

					String lines[] = pdfFileInText.split("\\n");
						for (String line : lines) {
						if(line.contains("$")){
							line=line.replace("$", "");
							line=line.trim();
							line=line.replace(",", "");
							line=line.trim();
						}
						ReportContant.add(line);
				
								
					}
				
				}  
				
			}
			catch(Exception e){
				e.printStackTrace();
				
		}
		}
		}catch(Exception e){
			e.printStackTrace();
			
	}
		return pdfFileInText;
			
			
		
	}
	public File selectDate(WebDriver driver,ArrayList test_steps,String date,ArrayList Revenue,ArrayList Revenuetype,ArrayList amount,ArrayList MTD,ArrayList YTD,ArrayList daypay,ArrayList monthpay,
			ArrayList yearpay,ArrayList type,ArrayList total) throws InterruptedException{
		File theNewestFile = null;
		File getFile = null;
		try{
			Elements_Reports reports = new Elements_Reports(driver);
			Wait.WaitForElement(driver, OR.Repoprts_DailyFlash_Calender);
			reports.Repoprts_DailyFlash_Calender.click();
			test_steps.add("Click on Calender");
			String month=Utility.get_MonthYear(date);
			String day=Utility.getDay(date);
			String monthYear="//table[@class='datepicker-table-condensed table-condensed']/thead/tr[2]/th[2]";
			int i=0;
			String label;
			String dateTest="//td[text()='"+day+"']";
			/*outer : while(i<20){
				label=driver.findElement(By.xpath(monthYear)).getText();
				if(label.equalsIgnoreCase(month)){
					Wait.WaitForElement(driver, dateTest);
					driver.findElement(By.xpath(dateTest)).click();*/
					Wait.wait3Second();
					Wait.WaitForElement(driver, OR.Repoprts_DailyFlash_Go);
					reports.Repoprts_DailyFlash_Go.click();
					test_steps.add("Click on Go");
					//Thread.sleep(20000);
					String frame="//iframe[@id='MainContent_ucRptViewer_ifrmAccountStatement']";
					Wait.WaitForElement(driver, frame);
					driver.switchTo().frame(driver.findElement(By.xpath(frame)));
					String sample="//select[@id='drpReportType']";
					Wait.wait3Second();
					new Actions(driver).moveToElement(driver.findElement(By.xpath(sample))).build().perform();
					WebElement ele=driver.findElement(By.xpath(sample));
					Wait.wait3Second();
					//driver.manage().window().maximize();
					assertTrue(driver.findElement(By.id("tdViewPDF")).isDisplayed(), "Report isn't Displayed");
					Utility.app_logs.info("PDF Report is Displayed");
					//test_steps.add("PDF Report is Displayed");
					String dataPath=driver.findElement(By.xpath("//*[@id='viewPDF']")).getAttribute("data");
					driver.get(dataPath);
					String fileName = Utility.download_status(driver);
					Wait.wait2Second();
					getFile = new File(System.getProperty("user.dir") + File.separator + "externalFiles"
							+ File.separator + "downloadFiles" + File.separator + fileName);

					test_steps.add("PDF report file downloaded : "+getFile.getAbsolutePath());
					File dir = new File(System.getProperty("user.dir")+"/externalFiles/downloadFiles");
					FileFilter fileFilter = new WildcardFileFilter("*." + "pdf");
					File[] files = dir.listFiles(fileFilter);
					if (files.length > 0) {
						Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
						theNewestFile = files[0];
					}
					Wait.wait3Second();
					try 
					{
						PDDocument document = PDDocument.load(theNewestFile);
						document.getClass();
						if (!document.isEncrypted()) {
							PDFTextStripperByArea stripper = new PDFTextStripperByArea();
							stripper.setSortByPosition(true);
							PDFTextStripper tStripper = new PDFTextStripper();
							String pdfFileInText = tStripper.getText(document);
							String lines[] = pdfFileInText.split("\\n");
							boolean flag=true;
							String testflag="test";
							for (String line : lines) {
								if(line.contains("$")){
									line=line.replace("$", "");
									line=line.trim();
									line=line.replace(",", "");
									line=line.trim();
								}
								String sub1 = null;
								String sub2 = null;
								String sub3 = null;
								String sub4 = null;
								if(line.contains("Revenue By Type")){
									sub1=line.substring(0, 15);
									sub2=line.substring(16, 20);
									sub3=line.substring(20, 23);
									sub4=line.substring(23, 35);
									Revenue.add(sub1);
									Revenue.add(sub4);
									Revenue.add(sub2);
									Revenue.add(sub3);
									flag=false;
									testflag="Revenue By Type";
								}else if(line.contains("Payments by Method")){
									//System.out.println("Payments by Method");
									flag=false;
									testflag="Payments by Method";
								}else if(line.contains("Total Receipts")){
									//System.out.println("Total Receipts");
									String str1=line.substring(line.length()-14,line.length());
									line=line.replace(str1, "");
									String lines1[] = line.split(" ");
									total.add(str1);
									for (int j=lines1.length-1;j>=0;j--) {
										total.add(lines1[j]);
									}
									flag=false;
									testflag="Total Receipts";
								}
								if(flag){
									if(testflag.equalsIgnoreCase("Revenue By Type")){
										String lines1[] = line.split("  ");
										for (int j=0;j<lines1.length;j++) {
											if(j==0){
												Revenuetype.add(lines1[j]);
											}else  if(j==1){
												amount.add(lines1[j]);
											}else if(j==2){
												MTD.add(lines1[j]);
											}else{
												YTD.add(lines1[j]);
											}
										}
									}else if(testflag.equalsIgnoreCase("Payments by Method")){
										String lines1[] = line.split("  ");
										for (int j=0;j<lines1.length;j++) {
											if(j==0){
												type.add(lines1[j]);
											}else  if(j==1){
												daypay.add(lines1[j]);
											}else if(j==2){
												monthpay.add(lines1[j]);
											}else{
												yearpay.add(lines1[j]);
											}
										}
									}
								}else{
									flag=true;
								}
							}
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					//break outer;
				/*}else{
					Wait.wait2Second();
					Wait.WaitForElement(driver, OR.Repoprts_DailyFlash_Calender_Right);
					reports.Repoprts_DailyFlash_Calender_Right.click();
				}*/
			//}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return getFile;
	}

	public void verifyInnroadLogo(File file,ArrayList test_steps) throws Exception{
		System.out.println("File : " +file);
		//String pdfFilePath = "D:\\Auto\\inncenter.nextgen.sanitysuite\\externalFiles\\downloadFiles\\AutomationQATestClient-DailyFlashReport218312475.pdf";
		File outputDir = new File(System.getProperty("user.dir")+"/externalFiles/downloadFiles");
		if (!outputDir.exists()) outputDir.mkdirs();
		while(Utility.isPdfReading){
			Wait.wait2Second();
			reportLogger.info("Waiting.....Report Already Opened");
		}
		Utility.isPdfReading = true;
		Document pdf = PDF.open(file);

		for (Page p : pdf.getPages()) {
			int i = 0;
			for (Image img : p.getImages()) {
				FileOutputStream out = new FileOutputStream(new File(outputDir, String.format("%s-%s.%s",p.getPageNumber(), i, img.dataFormat().name().toLowerCase())));
				out.write(img.data());
				out.close();
				i++;
			}
			System.out.printf("Found %s images on page %s", p.getImages().size(), p.getPageNumber());
			System.out.println();
		}
		pdf.close();
		Utility.isPdfReading = false;
		test_steps.add("Images are downloaded");
		BufferedImage biA = ImageIO.read(new File(System.getProperty("user.dir")+"\\Images\\InnroadLogo.png"));
		DataBuffer dbA = biA.getData().getDataBuffer();
		int sizeA = dbA.getSize();                      
		BufferedImage biB = ImageIO.read(new File(System.getProperty("user.dir")+"/externalFiles/downloadFiles/0-0.png"));
		DataBuffer dbB = biB.getData().getDataBuffer();
		int sizeB = dbB.getSize();
		// compare data-buffer objects //
		if(sizeA == sizeB) {
			for(int i=0; i<sizeA; i++) { 
				if(dbA.getElem(i) != dbB.getElem(i)) {
					reportLogger.info("Innroad logo not found");
					test_steps.add("Innroad logo not found");
				}
			}
			reportLogger.info("Innroad logo validated sucessfully");
			test_steps.add("Innroad logo validated sucessfully "+ "<img src='"+System.getProperty("user.dir")+"\\externalFiles\\downloadFiles\\0-0.png' hight="+50+" width ="+50+"/>");
		}
		else {
			reportLogger.info("Innroad logo not found");
			test_steps.add("Innroad logo not found");
		}
		
		
		
		
		 File file1 = new File(file.getAbsolutePath()); 
        try{
	        if(file1.delete()) 
	        { 
	        	reportLogger.info("File deleted successfully");
	        } 
	        else
	        { 
	        	reportLogger.info("Failed to delete the file");
	        } 
        }catch(Exception e){
       	 e.printStackTrace();
        }
		
		
	}
//
//	public void verifyInnroadLogo(WebDriver driver,ArrayList test_steps) throws Exception{
//		String frame="//iframe[@id='MainContent_ucRptViewer_ifrmAccountStatement']";
//		Wait.WaitForElement(driver, frame);
//		driver.switchTo().frame(driver.findElement(By.xpath(frame)));
//		Wait.wait3Second();
//		//driver.manage().window().maximize();
//		assertTrue(driver.findElement(By.id("tdViewPDF")).isDisplayed(), "Report isn't Displayed");
//		Utility.app_logs.info("PDF Report is Displayed");
//		//test_steps.add("PDF Report is Displayed");
//		String dataPath=driver.findElement(By.xpath("//*[@id='viewPDF']")).getAttribute("data");
//		driver.get(dataPath);
//		Wait.wait2Second();
//		File file = Utility.getLatestFilefromDir(System.getProperty("user.dir") + File.separator + "externalFiles"
//				+ File.separator + "downloadFiles" + File.separator);
//		System.out.println(file);
//		reportLogger.info("PDF report file downloaded");
//		test_steps.add("PDF report file downloaded");
//		//String pdfFilePath = "D:\\Auto\\inncenter.nextgen.sanitysuite\\externalFiles\\downloadFiles\\AutomationQATestClient-DailyFlashReport218312475.pdf";
//		File outputDir = new File(System.getProperty("user.dir")+"/externalFiles/downloadFiles");
//		if (!outputDir.exists()) outputDir.mkdirs();
//		Document pdf = PDF.open(file);
//		int count = 0;
//		for (Page p : pdf.getPages()) {
//			int i = 0;
//			for (Image img : p.getImages()) {
//				FileOutputStream out = new FileOutputStream(new File(outputDir, String.format("%s-%s.%s",p.getPageNumber(), i, img.dataFormat().name().toLowerCase())));
//				out.write(img.data());
//				out.close();
//				i++;
//			
//			}
//			count=p.getImages().size();
//			System.out.printf("Found %s images on page %s", p.getImages().size(), p.getPageNumber());
//			System.out.println();
//		}
//		for(int k=0;k<count;k++){
//		BufferedImage biA = ImageIO.read(new File(System.getProperty("user.dir")+"\\Images\\InnroadLogo.png"));
//		DataBuffer dbA = biA.getData().getDataBuffer();
//		int sizeA = dbA.getSize();                      
//		BufferedImage biB = ImageIO.read(new File(System.getProperty("user.dir")+"/externalFiles/downloadFiles/"+k+"-0.png"));
//		DataBuffer dbB = biB.getData().getDataBuffer();
//		int sizeB = dbB.getSize();
//		// compare data-buffer objects //
//		if(sizeA == sizeB) {
//			for(int i=0; i<sizeA; i++) { 
//				if(dbA.getElem(i) != dbB.getElem(i)) {
//					reportLogger.info("Innroad logo not found");
//					test_steps.add("Innroad logo not found");
//				}
//			}
//			reportLogger.info("Innroad logo validated sucessfully");
//			test_steps.add("Innroad logo validated sucessfully "+ "<img src='"+System.getProperty("user.dir")+"\\externalFiles\\downloadFiles\\0-0.png' hight="+50+" width ="+50+"/>");
//		}
//		else {
//			reportLogger.info("Innroad logo not found");
//			test_steps.add("Innroad logo not found");
//		}
//	}
//		
//		pdf.close();
//		 File file1 = new File(file.getAbsolutePath()); 
//         try{
//        	 System.out.println(file1);
//	        if(file1.delete()) 
//	        { 
//	        	reportLogger.info("File deleted successfully");
//	        } 
//	        else
//	        { 
//	        	reportLogger.info("Failed to delete the file");
//	        } 
//         }catch(Exception e){
//        	 e.printStackTrace();
//         }
//		
//	}
//
//	public ArrayList<String> VerifyInnroadLogoInReport(WebDriver driver, ArrayList<String> test_steps,String Report)
//			throws Exception {
//		String frame = "//iframe[@id='MainContent_ucRptViewer_ifrmAccountStatement']";
//		Wait.WaitForElement(driver, frame);
//		driver.switchTo().frame(driver.findElement(By.xpath(frame)));
//		Wait.wait3Second();
//		// driver.manage().window().maximize();
//		assertTrue(driver.findElement(By.id("tdViewPDF")).isDisplayed(), "Report isn't Displayed");
//		Utility.app_logs.info("PDF Report is Displayed");
//		// test_steps.add("PDF Report is Displayed");
//		String dataPath = driver.findElement(By.xpath("//*[@id='viewPDF']")).getAttribute("data");
//		driver.get(dataPath);
//		Wait.wait2Second();
//		File file = Utility.getLatestFilefromDir(System.getProperty("user.dir") + File.separator + "externalFiles"
//				+ File.separator + "downloadFiles" + File.separator);
//		System.out.println(file);
//		reportLogger.info("PDF report file downloaded");
//		test_steps.add("PDF report file downloaded");
//		// String pdfFilePath =
//		// "D:\\Auto\\inncenter.nextgen.sanitysuite\\externalFiles\\downloadFiles\\AutomationQATestClient-DailyFlashReport218312475.pdf";
//		File outputDir = new File(System.getProperty("user.dir") + "/externalFiles/downloadFiles");
//		if (!outputDir.exists())
//			outputDir.mkdirs();
//		Document pdf = PDF.open(file);
//		ArrayList<String> ImgList = new ArrayList<>();
//		for (Page p : pdf.getPages()) {
//			int i = 0;
//			for (Image img : p.getImages()) {
//				FileOutputStream out = new FileOutputStream(new File(outputDir,
//						String.format(Report+"%s-%s.%s", p.getPageNumber(), i, img.dataFormat().name().toLowerCase())));
//				out.write(img.data());
//				out.close();
//				ImgList.add(Report+p.getPageNumber() + "-" + i + "." + img.dataFormat().name().toLowerCase());
//				i++;
//			}
//			System.out.printf("Found %s images on page %s", p.getImages().size(), p.getPageNumber());
//			System.out.println();
//		}
//		BufferedImage biA = ImageIO
//				.read(new File(System.getProperty("user.dir") + "\\Images\\InnroadLogo.png"));
//		DataBuffer dbA = biA.getData().getDataBuffer();
//		int sizeA = dbA.getSize();
//		int Page = 1;
//		for (String img : ImgList) {
//			System.out.print(img);
//			BufferedImage biB = ImageIO
//					.read(new File(System.getProperty("user.dir") + "/externalFiles/downloadFiles/"+img));
//			DataBuffer dbB = biB.getData().getDataBuffer();
//			int sizeB = dbB.getSize();
//			// compare data-buffer objects //
//			if (sizeA == sizeB) {
//				for (int i = 0; i < sizeA; i++) {
//					if (dbA.getElem(i) != dbB.getElem(i)) {
//						assertTrue(false,"Innroad Logo not found");
//					}
//				}
//				reportLogger.info("Image "+img+" Innroad logo validated sucessfully");
//				test_steps.add("Page: "+Page+" Innroad logo validated sucessfully " + "<img src='" + System.getProperty("user.dir")
//				+ "\\externalFiles\\downloadFiles\\0-0.png' hight=" + 50 + " width =" + 50 + "/>");
//				Page++;
//			} else {
//				assertTrue(false,"Innroad Logo not found");
//			}
//			
//			
//		}
//		pdf.close();
//		 File file1 = new File(file.getAbsolutePath()); 
//        try{
//       	 System.out.println(file1);
//	        if(file1.delete()) 
//	        { 
//	        	reportLogger.info("File deleted successfully");
//	        } 
//	        else
//	        { 
//	        	reportLogger.info("Failed to delete the file");
//	        } 
//        }catch(Exception e){
//       	 e.printStackTrace();
//        }
//		
//		
//		return test_steps;
//	}
//	
	public ArrayList<String> verfiyInnroadLogo() throws IOException{
		
		ArrayList<String> test_steps = new ArrayList<String>();
		
		File file = Utility.getLatestFilefromDir(System.getProperty("user.dir") + File.separator + "externalFiles"
				+ File.separator + "downloadFiles" + File.separator);
		
		File outputDir = new File(System.getProperty("user.dir") + "/externalFiles/downloadFiles");
		
		Document pdf = PDF.open(file);
		ArrayList<String> ImgList = new ArrayList<>();
		for (Page p : pdf.getPages()) {
			int i = 0;
			for (Image img : p.getImages()) {
				FileOutputStream out = new FileOutputStream(new File(outputDir, String.format("%s-%s.%s",p.getPageNumber(), i, img.dataFormat().name().toLowerCase())));
				out.write(img.data());
				out.close();
				ImgList.add(p.getPageNumber() + "-" + i + "." + img.dataFormat().name().toLowerCase());
				i++;
			}
			
			System.out.printf("Found %s images on page %s", p.getImages().size(), p.getPageNumber());
			System.out.println();
			//test_steps.add(String.format("Found %s images on page %s", p.getImages().size(), p.getPageNumber()));
			
		}
		pdf.close();
		 File file1 = new File(file.getAbsolutePath()); 
       try{
      	 System.out.println(file1);
	        if(file1.delete()) 
	        { 
	        	reportLogger.info("File deleted successfully");
	        } 
	        else
	        { 
	        	reportLogger.info("Failed to delete the file");
	        } 
       }catch(Exception e){
      	 e.printStackTrace();
       }
		

		BufferedImage biA = ImageIO.read(new File(System.getProperty("user.dir")+"\\Images\\InnroadLogo.png"));
		DataBuffer dbA = biA.getData().getDataBuffer();
		int sizeA = dbA.getSize();
		
		for (String img : ImgList) {
			File loadImage = new File(System.getProperty("user.dir") + "/externalFiles/downloadFiles/"+img);
			
			BufferedImage biB = ImageIO.read(loadImage);
			
			DataBuffer dbB = biB.getData().getDataBuffer();
			int sizeB = dbB.getSize();
			// compare data-buffer objects //
			if (sizeA == sizeB) {
				for (int i = 0; i < sizeA; i++) {
					if (dbA.getElem(i) != dbB.getElem(i)) {
						reportLogger.info("Innroad logo not matched");
						test_steps.add("Innroad logo not matched");
						assertTrue(false,"Failed: Logo Not Matched");
					}
				}
				reportLogger.info("Image "+img+" Innroad logo validated sucessfully");
				test_steps.add("Image "+img+" Innroad logo validated sucessfully");
//				test_steps.add("Page: "+Page+" Innroad logo validated sucessfully " + "<img src='" + System.getProperty("user.dir")
//				+ "\\externalFiles\\downloadFiles\\0-0.png' hight=" + 50 + " width =" + 50 + "/>");
		
			} else {
				reportLogger.info("Innroad logo not found");
				//test_steps.add("Innroad logo not found");
			}
			loadImage.delete();
		}
		
		return test_steps;
		
	}
	
	public ArrayList<String> verfiyInnroadLogo(String fileName) throws IOException, InterruptedException{
		
		ArrayList<String> test_steps = new ArrayList<String>();
		System.out.println(fileName);
		File file = new File(System.getProperty("user.dir") + File.separator + "externalFiles"
				+ File.separator + "downloadFiles" + File.separator + fileName);
		StringTokenizer token = new StringTokenizer(fileName,".pdf");
		String folderName = token.nextToken();
		File outputDir = new File(System.getProperty("user.dir") + "/externalFiles/downloadFiles/"+folderName);
		if (!outputDir.exists())
			outputDir.mkdirs();
		
		while(Utility.isPdfReading){
			Wait.wait2Second();
			reportLogger.info("Waiting.....Report Already Opened");
		}
		Utility.isPdfReading = true;
		
		Document pdf = PDF.open(file);
		ArrayList<String> ImgList = new ArrayList<>();
		for (Page p : pdf.getPages()) {
			int i = 0;
			for (Image img : p.getImages()) {
				FileOutputStream out = new FileOutputStream(new File(outputDir, String.format("%s-%s.%s",p.getPageNumber(), i, img.dataFormat().name().toLowerCase())));
				out.write(img.data());
				out.close();
				ImgList.add(p.getPageNumber() + "-" + i + "." + img.dataFormat().name().toLowerCase());
				i++;
			}
			
			System.out.printf("Found %s images on page %s", p.getImages().size(), p.getPageNumber());
			System.out.println();
			//test_steps.add(String.format("Found %s images on page %s", p.getImages().size(), p.getPageNumber()));
			
		}
		pdf.close();
		 File file1 = new File(file.getAbsolutePath()); 
		 Utility.isPdfReading = false;
		 
       try{
      	 System.out.println(file1);
	        if(file1.delete()) 
	        { 
	        	reportLogger.info("File deleted successfully");
	        } 
	        else
	        { 
	        	reportLogger.info("Failed to delete the file");
	        } 
       }catch(Exception e){
      	 e.printStackTrace();
       }
		

		BufferedImage biA = ImageIO.read(new File(System.getProperty("user.dir")+"\\Images\\InnroadLogo.png"));
		DataBuffer dbA = biA.getData().getDataBuffer();
		int sizeA = dbA.getSize();
		
		for (String img : ImgList) {
			File loadImage = new File(System.getProperty("user.dir") + "/externalFiles/downloadFiles/"+folderName+"/"+img);
			
			BufferedImage biB = ImageIO.read(loadImage);
			
			DataBuffer dbB = biB.getData().getDataBuffer();
			int sizeB = dbB.getSize();
			// compare data-buffer objects //
			if (sizeA == sizeB) {
				for (int i = 0; i < sizeA; i++) {
					if (dbA.getElem(i) != dbB.getElem(i)) {
						reportLogger.info("Innroad logo not matched");
						test_steps.add("Innroad logo not matched");
						assertTrue(false,"Failed: Logo Not Matched");
					}
				}
				reportLogger.info("Image "+img+" Innroad logo validated sucessfully");
				test_steps.add("Image "+img+" Innroad logo validated sucessfully");
//				test_steps.add("Page: "+Page+" Innroad logo validated sucessfully " + "<img src='" + System.getProperty("user.dir")
//				+ "\\externalFiles\\downloadFiles\\0-0.png' hight=" + 50 + " width =" + 50 + "/>");
		
			} else {
				reportLogger.info("Innroad logo not found");
				//test_steps.add("Innroad logo not found");
			}
			loadImage.delete();
		}
		outputDir.delete();
		
		return test_steps;
		
	}

	public void downloadReportFile(WebDriver driver) throws InterruptedException{
		
		String frame = "//iframe[@id='MainContent_ucRptViewer_ifrmAccountStatement']";
		Wait.WaitForElement(driver, frame);
		driver.switchTo().frame(driver.findElement(By.xpath(frame)));
		Wait.wait3Second();
		// driver.manage().window().maximize();
		assertTrue(driver.findElement(By.id("tdViewPDF")).isDisplayed(), "Report isn't Displayed");
		Utility.app_logs.info("PDF Report is Displayed");
		// test_steps.add("PDF Report is Displayed");
		String dataPath = driver.findElement(By.xpath("//*[@id='viewPDF']")).getAttribute("data");
		driver.get(dataPath);
		
	}
	
	public void verify_DailyFlashDuplicate(File f, ArrayList test_steps) throws IOException {
		int count = 0;
		PDDocument document = PDDocument.load(f);
		document.getClass();
		if (!document.isEncrypted()) {
			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			stripper.setSortByPosition(true);
			PDFTextStripper tStripper = new PDFTextStripper();
			String pdfFileInText = tStripper.getText(document);
			String lines[] = pdfFileInText.split("\\n");
			boolean flag = true;
			String testflag = "test";

			for (String line : lines) {

				if (line.contains("Stay Throughs")) {
					count++;
				}
			}
		}
		if (count > 1) {
			reportLogger.info("Report printed multiple times");
			test_steps.add("Report printed multiple times");
		}
	}

	public void Report_Verify_ReportContent(WebDriver driver,String reportContent, String compareData,ArrayList <String>test_steps)
	{
		assertTrue(reportContent.contains(compareData));
		
	}


	public ArrayList<String> verifyMarketSegmentDropDownLedgerBalanceReport(WebDriver driver,
			ArrayList<String> test_steps, String MarketSegment, boolean isContains) throws InterruptedException {
		Elements_Reports elements_Reports = new Elements_Reports(driver);
		Wait.WaitForElement(driver, OR.LEDGERBALANCE_MARKETSEGMENT);
		Utility.ScrollToElement(elements_Reports.LEDGERBALANCE_MARKETSEGMENT, driver);
		test_steps.add("Market Segment Dropdown Opened");
		reportLogger.info("Market Segment Dropdown Opened");

		boolean isMarketSegmentFound = false;
		Select dropdown = new Select(elements_Reports.LEDGERBALANCE_MARKETSEGMENT);

		// Get all options
		List<WebElement> options = dropdown.getOptions();

		for (int i = 0; i < options.size(); i++) {

			if (isContains) {
				isMarketSegmentFound = options.get(i).getText().equals(MarketSegment);

				if (isMarketSegmentFound) {
					assertEquals(isMarketSegmentFound, true);
					test_steps.add("Market Segment Dropdown Contains Active: " + MarketSegment);
					reportLogger.info("Market Segment Dropdown Contains Active: " + MarketSegment);
					break;
				}
			} else {
				if (!isMarketSegmentFound) {
					isMarketSegmentFound = options.get(i).getText().contains(MarketSegment);
					assertEquals(isMarketSegmentFound, false);
					test_steps.add("Market Segment Dropdown Doesn't Contain InActive/Obsolete: " + MarketSegment);
					reportLogger.info("Market Segment Dropdown Doesn't Contain InActive/Obsolete: " + MarketSegment);
					break;
				}

			}
		}
		return test_steps;
	}
	public void clickDailyFlash(WebDriver driver,ArrayList<String> test_steps){
		Elements_Reports reports = new Elements_Reports(driver);
		Wait.WaitForElement(driver, OR.ReportsDailyFlash);
		reports.ReportsDailyFlash.click();
		test_steps.add("Click on daily flash");
		reportLogger.info("Click on daily flash");
	}
	public ArrayList<String> dailyFlashReport(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.DailyFlashRadioButton.click();
		test_steps.add("Select Report Type: Daily Flash");
		reportLogger.info("Select Report Type: Daily Flash");
		assertTrue(elements_Reports.DailyFlashRadioButton.isSelected(), "Daily Flash Report Type is not selected");
		return test_steps;

	}

	public ArrayList<String> breakoutTaxExemptRevenue(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.DailyFlashBreakoutTaxExemptRevCheckBox.click();
		test_steps.add("Click: Breakout Tax Exempt Revenue CheckBox");
		reportLogger.info("Click: Breakout Tax Exempt Revenue CheckBox");
		assertTrue(elements_Reports.DailyFlashBreakoutTaxExemptRevCheckBox.isSelected(),
				"Breakout Tax Exempt Revenue is not selected");
		return test_steps;

	}
	public ArrayList<String> verifyBreakoutTaxExemptRevenueCheckbox(WebDriver driver,ArrayList<String> test_steps) {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		boolean is_selected = elements_Reports.DailyFlashBreakoutTaxExemptRevCheckBox.isSelected();
		if (!is_selected) {
			test_steps.add("Breakout Tax Exempt Revenue is not seleted ");
			reportLogger.info("Breakout Tax Exempt Revenue is not seleted ");
			assertEquals(is_selected, true, "Breakout Tax Exempt Revenue is not seleted");
		} else {
			test_steps.add("Breakout Tax Exempt Revenue is Selected");
			reportLogger.info("Breakout Tax Exempt Revenue is Selected");
		}
		return test_steps;
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <verifyFlashReportLayout> 
	 * ' Description: <Verify the content of flash report> 
	 * ' Input parameters: <WebDriver driver, String qaProperty, String dailyFlashReport, String yTD, String mTD> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Jamal Nasir>
	 * ' Created On:  <05/06/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String> verifyFlashReportLayout(WebDriver driver, String qaProperty, String dailyFlashReport, String yTD, String mTD) throws InterruptedException{
		Elements_Reports reports = new Elements_Reports(driver);		
		ArrayList<String> test_steps = new ArrayList<>();
		File theNewestFile = null;
		File getFile = null;
		try{
					Wait.WaitForElement(driver, OR.ReportsDailyFlashCalender);
					String date = Utility.getCurrentDate("MM/dd/yyyy");
					reportLogger.info("date :" + date);
					
					String reportDate = Utility.getCurrentDate("dd-MMM-yyyy");
					reportLogger.info("reportDate :" + reportDate);
					
					Wait.WaitForElement(driver, OR_Reports.ReportFrame);
					driver.switchTo().frame(reports.ReportFrame);					

					Wait.WaitForElement(driver, OR_Reports.ReportType);					
					new Actions(driver).moveToElement(reports.ReportType).build().perform();
					
					assertTrue(reports.ReportDisplay.isDisplayed(), "Report isn't Displayed");
					Utility.app_logs.info("PDF Report is Displayed");
					test_steps.add("PDF Report is Displayed");
			
					String dataPath=reports.ViewPDF.getAttribute("data");
					driver.get(dataPath);
					String fileName = Utility.download_status(driver);	
					getFile = new File(System.getProperty("user.dir") + File.separator + "externalFiles"
							+ File.separator + "downloadFiles" + File.separator + fileName);

					test_steps.add("PDF report file downloaded : "+getFile.getAbsolutePath());
					reportLogger.info("PDF report file downloaded : "+getFile.getAbsolutePath());

					test_steps.add("PDF report file Name : "+getFile.getName());
					reportLogger.info("PDF report file Name : "+getFile.getName());
					
					File dir = new File(System.getProperty("user.dir")+"/externalFiles/downloadFiles");					
					FileFilter fileFilter = new WildcardFileFilter("*." + "pdf");
					File[] files = dir.listFiles(fileFilter);
					if (files.length > 0) {
						Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
						theNewestFile = files[0];
					}
					try 
					{
						PDDocument document = PDDocument.load(theNewestFile);
						document.getClass();
						if (!document.isEncrypted()) {
							PDFTextStripperByArea stripper = new PDFTextStripperByArea();
							stripper.setSortByPosition(true);
							PDFTextStripper tStripper = new PDFTextStripper();
							String pdfFileInText = tStripper.getText(document);
							String lines[] = pdfFileInText.split("\\n");
							HashMap<String, Integer> map = new HashMap<>();							
							for (String line : lines) {

								if(line.contains(qaProperty)){
									if(!map.containsKey(qaProperty)){
										test_steps.add("Verified " + qaProperty + " in Flash Report");
										reportLogger.info("Verified " + qaProperty + " in Flash Report");
										map.put(qaProperty, 1);
									}
								}
							
								if(line.contains(dailyFlashReport)){
									if(!map.containsKey(dailyFlashReport)){
										test_steps.add("Verified " + dailyFlashReport + "  heading in Flash Report");
										reportLogger.info("Verified " + dailyFlashReport + " heading in Flash Report");
										map.put(dailyFlashReport, 1);		
									}
								}
								if(line.contains(date)){
									if(!map.containsKey(date)){
										test_steps.add("Verified Document Creation Date(" +  date + ") in Flash Report");
										reportLogger.info("Verified Document Creation Date(" +  date + ") in Flash Report");
										map.put(date, 1);
										
									}
								}
								if(line.contains(reportDate)){
									if(!map.containsKey(reportDate)){
										test_steps.add("Verified Date(" +  reportDate + ") in Flash Report");
										reportLogger.info("Verified Date(" +  reportDate + ") in Flash Report");
										map.put(reportDate, 1);
									}
									
								}

								if(line.contains(mTD)){
									if(!map.containsKey(mTD)){
										test_steps.add("Verified (" + mTD + ") in Flash Report");
										reportLogger.info("Verified (" + mTD + ") in Flash Report");
										map.put(mTD, 1);
									}
								}
								if(line.contains(yTD)){
									if(!map.containsKey(yTD)){
										test_steps.add("Verified (" + yTD + ") in Flash Report");
										reportLogger.info("Verified (" + yTD + ") in Flash Report");
										map.put(yTD, 1);
									}
								}
							}
							document.close();
						}
					}catch(Exception e){
						e.printStackTrace();
					}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		Utility.deleteFile(getFile);
		test_steps.add("Deleted PDF File");
		reportLogger.info("Deleted PDF File");
	
		return test_steps;
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <selectDate> 
	 * ' Description: <Select today date in daily flash report> 
	 * ' Input parameters: < 'WebDriver' separated parameters type> 
	 * ' Return value: <ArrayList>
	 * ' Created By: <Jamal Nasir>
	 * ' Created On:  <04/28/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	 
	public ArrayList<String> selectDate(WebDriver driver) throws InterruptedException {
		
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();
		
		Wait.WaitForElement(driver, OR.DailyFlashReportDate);	
		Navigate.DailyFlashReportDate.click();
		test_steps.add("Click on Calender");
		reportLogger.info("Click on Calender");
		
		Wait.WaitForElement(driver, OR.DailyFlashReportTodayDate);
		Navigate.DailyFlashReportTodayDate.click();
		test_steps.add("Selected Today's Date");
		reportLogger.info("Selected Today's Date");

		Wait.WaitForElement(driver, OR.DailyFlashReportGoButton);
		Navigate.DailyFlashReportGoButton.click();
		test_steps.add("Click on Go");
		reportLogger.info("Click on Go");

		return test_steps;
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <selectNetSalesFromDate> 
	 * ' Description: <Select From date> 
	 * ' Input parameters: <WebDriver driver, String checkInDate> 
	 * ' Return value: <ArrayList>
	 * ' Created By: <Jamal Nasir>
	 * ' Created On:  <05/06/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */	
	public ArrayList<String> selectNetSalesFromDate(WebDriver driver, String checkInDate) throws InterruptedException {
		 ArrayList<String> test_steps = new ArrayList<>();
			Elements_Reports elementsReports = new Elements_Reports(driver);
			Wait.WaitForElement(driver, OR.NetSalesFromDate);
			elementsReports.NetSalesFromDate.click();
			String monthYear = Utility.get_MonthYear(checkInDate);
			String day = Utility.getDay(checkInDate);
			reportLogger.info(monthYear);
			String headerText = null, date;
			for (int i = 1; i <= 12; i++) {
				headerText = elementsReports.NetSalesFromCalenderHeading.getText();
				if (headerText.equalsIgnoreCase(monthYear)) {
					date = "//td[contains(@class,'day') and text()='" + day + "']";
					Wait.WaitForElement(driver, date);
					int size = driver.findElements(By.xpath(date)).size();
					for (int k = 1; k <= size; k++) {
						date = "(//td[contains(@class,'day') and text()='" + day + "'])[" + k + "]";
						String classText = driver.findElement(By.xpath(date)).getAttribute("class");
						if (!classText.contains("old")) {
							driver.findElement(By.xpath(date)).click();
							Wait.wait2Second();
							assertTrue(!elementsReports.NetSalesStayonFromDate.getAttribute("value").isEmpty(),
									"From Date is not Selected");
	
							test_steps.add("Selected From date : " + checkInDate);
							reportLogger.info("Selected From  date : " + checkInDate);
							break;
						}
					}
					break;
				} else {
					Wait.WaitForElement(driver, OR_Reports.NetSalesFromCalenderNext);
					elementsReports.NetSalesFromCalenderNext.click();
					Wait.wait2Second();
				}
			}
	
			return test_steps;
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <selectNetSalesToDate> 
	 * ' Description: <Select To date> 
	 * ' Input parameters: <WebDriver driver, String checkOutDate> 
	 * ' Return value: <ArrayList>
	 * ' Created By: <Jamal Nasir>
	 * ' Created On:  <05/06/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */	
	public ArrayList<String> selectNetSalesToDate(WebDriver driver, String checkOutDate)
			throws InterruptedException {
		Elements_Reports elementsReports = new Elements_Reports(driver);
		 ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.NetSalesToDate);
		elementsReports.NetSalesToDate.click();
		String monthYear = Utility.get_MonthYear(checkOutDate);
		String day = Utility.getDay(checkOutDate);
		reportLogger.info(monthYear);
		String headerText = null, date;
		for (int i = 1; i <= 12; i++) {
			headerText = elementsReports.NetSalesFromCalenderHeading.getText();
			if (headerText.equalsIgnoreCase(monthYear)) {
				date = "//td[contains(@class,'day') and text()='" + day + "']";
				Wait.WaitForElement(driver, date);
				int size = driver.findElements(By.xpath(date)).size();
				for (int k = 1; k <= size; k++) {
					date = "(//td[contains(@class,'day') and text()='" + day + "'])[" + k + "]";
					String classText = driver.findElement(By.xpath(date)).getAttribute("class");
					if (!classText.contains("old")) {
						driver.findElement(By.xpath(date)).click();
						Wait.wait2Second();
						assertTrue(!elementsReports.NetSalesStayonToDate.getAttribute("value").isEmpty(), "To Date is not Selected");
						
						test_steps.add("Selected To date : " + checkOutDate);
						reportLogger.info("Selected To date : " + checkOutDate);
						break;
					}
				}
	
				break;
			} else {
				Wait.WaitForElement(driver, OR_Reports.NetSalesFromCalenderNext);
				elementsReports.NetSalesFromCalenderNext.click();
				Wait.wait2Second();
			}
		}
		return test_steps;
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <VerifyNetSalesReport> 
	 * ' Description: <Verify Content Of NetSales Report> 
	 * ' Input parameters: <WebDriver driver, String NetSalesReport,  String GroupBy, String qaProperty, String StayFromDate, String StayToDate, String JSRoomClass, String RoomClass, int numberOfReservation> 
	 * ' Return value: <ArrayList>
	 * ' Created By: <Jamal Nasir>
	 * ' Created On:  <05/01/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String>  verifyNetSalesReport(WebDriver driver, String netSalesReport, String groupBy, String qaProperty, String stayFromDate, String stayToDate, String jsRoomClass, String roomClass, int numberOfReservation) throws InterruptedException{
			Elements_Reports reports = new Elements_Reports(driver);
			ArrayList<String> test_steps = new ArrayList<>();
			File theNewestFile = null;
			File getFile = null;
			try{
						stayFromDate = ESTTimeZone.reformatDate(stayFromDate, "dd/MM/yyyy" , "MMM d, yyyy");
						stayToDate = ESTTimeZone.reformatDate(stayToDate, "dd/MM/yyyy" , "MMM d, yyyy");
						reportLogger.info("stayFromDate : "  + stayFromDate);
						reportLogger.info("stayToDate : "  +  stayToDate);
						
						String weekStartDate = ESTTimeZone.reformatDate(stayFromDate, "MMM d, yyyy" , "M/d");
						String weekEndDate = ESTTimeZone.reformatDate(stayToDate, "MMM d, yyyy" , "M/d");
						String weekConsumedDesc = weekStartDate + " to " + weekEndDate;
						reportLogger.info("weekConsumedDesc : " + weekConsumedDesc);

						Wait.WaitForElement(driver, OR_Reports.ReportFrame);
						driver.switchTo().frame(reports.ReportFrame);					
						
						Wait.WaitForElement(driver, OR_Reports.ReportType);			
						new Actions(driver).moveToElement(reports.ReportType).build().perform();
		
						assertTrue(reports.ReportDisplay.isDisplayed(), "Report isn't Displayed");
						Utility.app_logs.info("PDF Report is Displayed");
						test_steps.add("PDF Report is Displayed");
				
						String dataPath=reports.ViewPDF.getAttribute("data");
						driver.get(dataPath);
		
						String fileName = Utility.download_status(driver);			
						getFile = new File(System.getProperty("user.dir") + File.separator + "externalFiles"
								+ File.separator + "downloadFiles" + File.separator + fileName);
		
						test_steps.add("PDF report file downloaded : "+getFile.getAbsolutePath());
						reportLogger.info("PDF report file downloaded : "+getFile.getAbsolutePath());
		
						test_steps.add("PDF report file Name : "+getFile.getName());
						reportLogger.info("PDF report file Name : "+getFile.getName());
						
						File dir = new File(System.getProperty("user.dir")+"/externalFiles/downloadFiles");					
						FileFilter fileFilter = new WildcardFileFilter("*." + "pdf");
						File[] files = dir.listFiles(fileFilter);
						if (files.length > 0) {
							Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
							theNewestFile = files[0];
						}
		
						try 
						{
							PDDocument document = PDDocument.load(theNewestFile);
							document.getClass();
							if (!document.isEncrypted()) {
								PDFTextStripperByArea stripper = new PDFTextStripperByArea();
								stripper.setSortByPosition(true);
								PDFTextStripper tStripper = new PDFTextStripper();
								String pdfFileInText = tStripper.getText(document);
								String lines[] = pdfFileInText.split("\\n");
								HashMap<String, Integer> map = new HashMap<>();							
								for (String line : lines) {
									if(line.contains(netSalesReport)){
										if(!map.containsKey(netSalesReport)){
											test_steps.add("Verified " + netSalesReport + "  heading in NetSales Report");
											reportLogger.info("Verified " + netSalesReport + " heading in NetSales Report");
											map.put(netSalesReport, 1);		
										}
									}
		
									if(line.contains(qaProperty)){
										if(!map.containsKey(qaProperty)){
											test_steps.add("Verified " + qaProperty );
											reportLogger.info("Verified " + qaProperty);
											map.put(qaProperty, 1);
										}
									}
									
									if(line.contains(groupBy)){
										if(!map.containsKey(groupBy)){
											test_steps.add("Verified selected grouped by is " + groupBy );
											reportLogger.info("Verified selected grouped by is " + groupBy);
											map.put(groupBy, 1);
											
										}
									}
								
									if(line.contains(stayFromDate)){
										if(!map.containsKey(stayFromDate)){
											test_steps.add("Verified Stay On date(" +  stayFromDate + ")");
											reportLogger.info("Verified Stay On date(" +  stayFromDate + ")");
											map.put(stayFromDate, 1);									
										}
									}									
									
									if(line.contains(stayToDate)){
										if(!map.containsKey(stayToDate)){
											test_steps.add("Verified Stay To date(" +  stayToDate + ")");
											reportLogger.info("Verified Stay To date(" +  stayToDate + ")");
											map.put(stayToDate, 1);
										}								
									}

									if(line.contains(weekConsumedDesc)){
										if(!map.containsKey(weekConsumedDesc)){
											System.out.print(line.toString());
											test_steps.add("Verified week consumed description (" + weekConsumedDesc + ")");
											reportLogger.info("Verified week consumed description (" + weekConsumedDesc + ")");
											map.put(weekConsumedDesc, 1);
											
											if(line.contains(String.valueOf(numberOfReservation))){
												if(!map.containsKey(String.valueOf(numberOfReservation))){
													System.out.print(line.toString());
													test_steps.add("Verified NetSales report grouped by week consumed has total three reservations");
													reportLogger.info("Verified NetSales report grouped by week consumed has total three reservations");
													map.put(String.valueOf(numberOfReservation), 1);
												}
											}
										}
									}
									String jsRoomClassRes = "2";
									if(line.contains(jsRoomClass)){
										if(!map.containsKey(jsRoomClass)){
												test_steps.add("Verified room class (" + jsRoomClass + ")");
												reportLogger.info("Verified room class (" + jsRoomClass + ")");
												map.put(jsRoomClass, 1);
												if(line.contains(jsRoomClassRes)){
													test_steps.add("Verified room class (" + jsRoomClass + ") have two reservations");
													reportLogger.info("Verified room class (" + jsRoomClass + ") have two reservations");
													map.put(jsRoomClassRes, 1);
													
												}
										}
									}
									String roomClassRes = "1";
									if(line.contains(roomClass)){
										if(!map.containsKey(roomClass)){
											test_steps.add("Verified room class (" + roomClass + ")");
											reportLogger.info("Verified room class (" + roomClass + ")");
											map.put(roomClass, 1);

											if(line.contains(roomClassRes)){
												test_steps.add("Verified room class (" + roomClass + ") have one reservations");
												reportLogger.info("Verified room class (" + roomClass + ") have one reservations");	
												map.put(roomClassRes, 1);
											}											
										}
									}
									if(!map.containsKey("Total")){
											if(map.containsKey(jsRoomClassRes) && map.containsKey(roomClassRes)){
												test_steps.add("Verified that week has total three reservations");
												reportLogger.info("Verified that week has total three reservations");
												map.put("Total", 1);
											}
									}
								}
								
								document.close();
							}
						}catch(Exception e){
							e.printStackTrace();
						}
					
			}catch(Exception e){
				e.printStackTrace();
			}
			Utility.deleteFile(getFile);
			test_steps.add("Deleted PDF File");
			reportLogger.info("Deleted PDF File");
		
			return test_steps;
		}
	
	public void NetSalesGoButton(WebDriver driver) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		elements_Reports.NetSales_GoButton.click();
		Wait.wait2Second();
		driver.manage().window().maximize();
		driver.switchTo().frame("ifrmAccountStatement");
		assertTrue(elements_Reports.NetSales_GenratedReport_GroupByRoom.isDisplayed(), "Report isn't generated");
		driver.switchTo().defaultContent();

	}
	
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <netSalesGroupBy> 
	 * ' Description: <Select groupBy option> 
	 * ' Input parameters: <WebDriver driver, String groupBy> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Jamal Nasir>
	 * ' Created On:  <05/06/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	 
	public ArrayList<String> netSalesGroupBy(WebDriver driver, String groupBy) throws InterruptedException {
		Elements_Reports elements_Reports = new Elements_Reports(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Select selectGroupBy = new Select(elements_Reports.NetSalesGroupBy);
		selectGroupBy.selectByVisibleText(groupBy);
		String selectedOption = selectGroupBy.getFirstSelectedOption().getText();
		test_steps.add("Selected GroupBy Option : " + selectedOption);
		reportLogger.info("Selected GroupBy Option : " + selectedOption);
		
		return test_steps;
	}
	 
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <verifyGuestsReport> 
	 * ' Description: <This method will verify content of GuestReports WithTaxBreakdown> 
	 * ' Input parameters: <WebDriver driver, String reportHeading, String incidentalName, String description, String amount, String reservationNumber,String roomClassName, String roomClassDescription,String roomNumber,String folioTaxes, String folioRoomCharges, String paymentMethod> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Jamal Nasir>
	 * ' Created On:  <05/22/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
		 
	public ArrayList<String> verifyGuestsReport(WebDriver driver, String reportHeading, String incidentalName, String description, String amount, String reservationNumber,String roomClassName, String roomClassDescription,String roomNumber,String folioTaxes, String folioRoomCharges, String paymentMethod) throws InterruptedException{
		File theNewestFile = null;
		File getFile = null;
		ArrayList<String> test_steps = new ArrayList<>();
		String verifyIncidentalAmount = incidentalName + " " + description + " " + roomClassName + " : " + roomNumber + " $ -" + amount;
		Double taxesInDouble = Double.parseDouble(folioTaxes);
		Double roomChargesinDouble = Double.parseDouble(folioRoomCharges);
		Double totalRoomCharges = roomChargesinDouble + taxesInDouble;
		reportLogger.info("totalRoomCharges : " + totalRoomCharges);
		String verifyRoomClass = "Room Charge " + roomClassDescription + " " + roomClassName + " : " + roomNumber + " $ " + folioTaxes + " $ " + totalRoomCharges;
		reportLogger.info("verifyIncidentalAmount : " + verifyIncidentalAmount);
		reportLogger.info("verifyRoomClass : " + verifyRoomClass);
		String dataPath=null;
		
		try{
			
			ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
			System.out.println(tabs2);
			if (tabs2.size() == 2) {
				driver.switchTo().window(tabs2.get(1));
				test_steps.add("Switch to Tab two");
				reportLogger.info("Switch to Tab two");
				Wait.wait2Second();
			}
			boolean isExist=Utility.isElementPresent(driver, By.id("embdreportViewer"));
			if(isExist)
			{
				test_steps.add("PDF Report is Displayed");
				Utility.app_logs.info("PDF Report is Displayed");
			}
			else
			{
				test_steps.add("PDF Report is Not Displayed");
				Utility.app_logs.info("PDF Report is Not Displayed");
			}
			if(isExist)
			{
					dataPath=driver.findElement(By.id("embdreportViewer")).getAttribute("src");
					driver.get(dataPath);
					String fileName = Utility.download_status(driver);
					Wait.wait2Second();
					driver.close();
					driver.switchTo().window(tabs2.get(0));
					test_steps.add("Closed tab two and switch to first tab");
					reportLogger.info("Closed tab two and switch to first tab");

					getFile = new File(System.getProperty("user.dir") + File.separator + "externalFiles"
							+ File.separator + "downloadFiles" + File.separator + fileName);
					test_steps.add("PDF report file downloaded : "+getFile.getAbsolutePath());
					reportLogger.info("PDF report file downloaded : "+getFile.getAbsolutePath());

					File dir = new File(System.getProperty("user.dir")+"/externalFiles/downloadFiles");
					FileFilter fileFilter = new WildcardFileFilter("*." + "pdf");
					File[] files = dir.listFiles(fileFilter);
					if (files.length > 0) {
						Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
						theNewestFile = files[0];
					}
					Wait.wait3Second();
					
					try 
					{
						PDDocument document = PDDocument.load(theNewestFile);
						document.getClass();
						if (!document.isEncrypted()) {
							PDFTextStripperByArea stripper = new PDFTextStripperByArea();
							stripper.setSortByPosition(true);
							PDFTextStripper tStripper = new PDFTextStripper();
							String pdfFileInText = tStripper.getText(document);
							HashMap<String, Integer> map = new HashMap<>();							
							String lines[] = pdfFileInText.split("\\n");
							for (String line : lines) {
								//Common Fields Verifications
								if(line.contains(reportHeading)){
									if(!map.containsKey(reportHeading)){
											if(!map.containsKey(reportHeading)){
												test_steps.add("Verified (" + reportHeading + ") heading in report");
												reportLogger.info("Verified (" + reportHeading + ") heading in report");
												map.put(reportHeading, 1);
											}
									}
								}
								
								if(line.contains(reservationNumber)){
									if(!map.containsKey(reservationNumber)){
												test_steps.add("Verified reservation number(" + reservationNumber + ") in report");
												reportLogger.info("Verified reservation number(" + reservationNumber + ") in report");
												map.put(reservationNumber, 1);
									}
								}								

								String incidentalAmount = "Incidentals $0.00";
								if(line.contains(incidentalAmount)){
									if(!map.containsKey(incidentalAmount)){
												test_steps.add("Verified that incidental amount is zero in report");
												reportLogger.info("Verified that incidental amount is zero in report");
												map.put(incidentalAmount, 1);
									}
								}
	
								//Verification when all checkBoxes are unchecked
								String roomCharges = "Room Charges $0.00";
								if(line.contains(roomCharges)){
									if(!map.containsKey(roomCharges)){
												test_steps.add("Verified that Room Charges amount is zero in report");
												reportLogger.info("Verified that Room Charges amount is zero in report");
												map.put(roomCharges, 1);
									}
								}								

								String totalCharges = "Total Charges $0.00";
								if(line.contains(totalCharges)){
									if(!map.containsKey(totalCharges)){
												test_steps.add("Verified that Total Charges amount is zero in report");
												reportLogger.info("Verified that Total Charges amount is zero in report");
												map.put(totalCharges, 1);
									}
								}								
								
								if(line.contains(paymentMethod)){
									if(!map.containsKey(paymentMethod)){
												test_steps.add("Verified payment method (" + paymentMethod + ") in report");
												reportLogger.info("Verified payment method (" + paymentMethod + ") in report");
												map.put(paymentMethod, 1);
									}
								}

								//Verification when all checkBoxes are uchecked
								if(line.contains(verifyIncidentalAmount)){
									if(!map.containsKey(verifyIncidentalAmount)){
											if(!map.containsKey(verifyIncidentalAmount)){
												test_steps.add("Verified that void line item(" + incidentalName + ") amount(" + amount + ") has negative sign(-) in report");
												reportLogger.info("Verified that void line item(" + incidentalName + ") amount(" + amount + ") has negative sign(-) in report");
												map.put(verifyIncidentalAmount, 1);
											}
									}
								}
								if(line.contains(verifyRoomClass)){
									if(!map.containsKey(verifyRoomClass)){
												test_steps.add("Verified RoomClass(" + roomClassName + "), room charges amount(" + folioRoomCharges + ") and  tax(" + folioTaxes + ")  in report");
												reportLogger.info("Verified RoomClass(" + roomClassName + "), room charges amount(" + folioRoomCharges + ") and  tax(" + folioTaxes + ")  in report");
												map.put(verifyRoomClass, 1);
									}
								}								
																
							}
						}
						document.close();
					}catch(Exception e){
						e.printStackTrace();
					}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
			Utility.deleteFile(getFile);
			test_steps.add("Deleted report file");
			reportLogger.info("Deleted report file");
		return test_steps;
	}

	public File verifyGuestStement(WebDriver driver, String fileName, String propertyDetailsAddress,
			String proprtyAddress, String propertyDetailsPhoneNumber, String propertyFax,
			String propertyDetailsinputEmail, String primaryUser, String address, String cityStatePostalCode,
			String phoneNumber, String arrivalDateToValidate, String depatureDateToValidate,
			String validateRoomwithRoomNumber1, String validateRoomwithRoomNumber2,
			String validereservationNumberInReport, String roomNumber1, String roomNumber2, String category, String qty,
			String tax, String amount, String description, String checkInDate, String checkOutDate,
			ArrayList<String> test_steps) throws InterruptedException {

		File theNewestFile = null;
		File getFile = null;
		String line1 = checkInDate + " " + category + " " + description + " " + validateRoomwithRoomNumber1 + " "
				+ " $ " + tax + " $" + " " + amount;
		String line2 = checkOutDate + " " + category + " " + description + " " + validateRoomwithRoomNumber1 + " "
				+ " $ " + " " + tax + " " + " $" + " " + amount;
				getFile = new File(System.getProperty("user.dir") + File.separator + "externalFiles" + File.separator
				+ "downloadFiles" + File.separator + fileName);

		test_steps.add("PDF report file downloaded : " + getFile.getAbsolutePath());
		File dir = new File(System.getProperty("user.dir") + "/externalFiles/downloadFiles");
		FileFilter fileFilter = new WildcardFileFilter("*." + "pdf");
		File[] files = dir.listFiles(fileFilter);
		if (files.length > 0) {
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
		}
		Wait.wait3Second();
		try {
			PDDocument document = PDDocument.load(theNewestFile);
			document.getClass();
			if (!document.isEncrypted()) {
				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);
				PDFTextStripper tStripper = new PDFTextStripper();
				String pdfFileInText = tStripper.getText(document);
				String lines[] = pdfFileInText.split("\\n");
				boolean flag = true;
				ArrayList<String> propertyDetails = new ArrayList<>();
				propertyDetails.add(propertyDetailsAddress);
				propertyDetails.add(propertyDetailsPhoneNumber);
				propertyDetails.add(propertyFax);
				propertyDetails.add(proprtyAddress);
				propertyDetails.add(propertyDetailsinputEmail);
				propertyDetails.add(primaryUser);
				propertyDetails.add(address);
				propertyDetails.add(cityStatePostalCode);
				propertyDetails.add(phoneNumber);
				propertyDetails.add(arrivalDateToValidate);
				propertyDetails.add(depatureDateToValidate);
				propertyDetails.add(validateRoomwithRoomNumber1);
				propertyDetails.add(validateRoomwithRoomNumber2);
				propertyDetails.add(validereservationNumberInReport);
				propertyDetails.add(roomNumber1);
				propertyDetails.add(description);
				propertyDetails.add(amount);
				propertyDetails.add(tax);
				propertyDetails.add(validateRoomwithRoomNumber1);
				propertyDetails.add(roomNumber2);
				propertyDetails.add(category);
				propertyDetails.add(line1);
				propertyDetails.add(line2);

				// 06/17/2020 Room Charge Nightly Room Rate Double Bed Room :
				// 105 $ 15.00 $ 250.00
				// DATE CATEGORY DESCRIPTION ROOM TAX AMOUNT

				for (String string : propertyDetails) {
					boolean propertyFound = false;
					for (String line : lines) {
						if (string.equalsIgnoreCase(line.trim())) {
							test_steps.add("Verified property details (" + string + ") in Guest Statement Report");
							reportLogger.info("Verified propertyDetails  (" + string + ") in Guest Statement Report");
							propertyFound = true;
							break;
						}
					}
					if (!propertyFound) {
						test_steps.add("Property details for (" + string + ") not found in Guest Statement Report");
						reportLogger.info("Property details for (" + string + ") not in Guest Statement Report");
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return getFile;
	}
		 
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <SelectLedgerBalanceFromDate> 
	 * ' Description: <This method will select from date in ledger balance report> 
	 * ' Input parameters: <WebDriver driver, String fromDate> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Jamal Nasir>
	 * ' Created On:  <05/18/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String>  SelectLedgerBalanceFromDate(WebDriver driver, String fromDate)
			throws InterruptedException {
		Elements_Reports elementsReports = new Elements_Reports(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.LedgerBalance_FromDate);
		elementsReports.LedgerBalance_FromDate.click();
		String monthYear = Utility.get_MonthYear(fromDate);
		String day = Utility.getDay(fromDate);
		System.out.println(monthYear);
		String header = null, headerText = null, next = null, date;
		for (int i = 1; i <= 12; i++) {
			header = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[2]";
			headerText = driver.findElement(By.xpath(header)).getText();
			if (headerText.equalsIgnoreCase(monthYear)) {
				date = "//td[contains(@class,'day') and text()='" + day + "']";
				Wait.WaitForElement(driver, date);
				int size = driver.findElements(By.xpath(date)).size();
				for (int k = 1; k <= size; k++) {
					date = "(//td[contains(@class,'day') and text()='" + day + "'])[" + k + "]";
					String classText = driver.findElement(By.xpath(date)).getAttribute("class");
					if (!classText.contains("old")) {
						driver.findElement(By.xpath(date)).click();
						test_steps.add("Selecting from date : " + fromDate);
						reportLogger.info("Selecting from date : " + fromDate);
						break;
					}
				}

				break;
			} else {
				next = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[3]/i";
				Wait.WaitForElement(driver, next);
				driver.findElement(By.xpath(next)).click();
				Wait.wait2Second();
			}
		}
		return test_steps;
	}
	

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <SelectLedgerBalanceToDate> 
	 * ' Description: <This method will select from date in ledger balance report> 
	 * ' Input parameters: <WebDriver driver, String toDate> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Jamal Nasir>
	 * ' Created On:  <05/18/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String>  SelectLedgerBalanceToDate(WebDriver driver, String toDate)
			throws InterruptedException {
		Elements_Reports elementsReports = new Elements_Reports(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		elementsReports.Incidentials_Checkbox.click();
		
		Wait.WaitForElement(driver, OR.LedgerBalance_ToDate);
		elementsReports.LedgerBalance_ToDate.click();
		String monthYear = Utility.get_MonthYear(toDate);
		String day = Utility.getDay(toDate);
		System.out.println(monthYear);
		String header = null, headerText = null, next = null, date;
		for (int i = 1; i <= 12; i++) {
			header = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[2]";
			headerText = driver.findElement(By.xpath(header)).getText();
			if (headerText.equalsIgnoreCase(monthYear)) {
				date = "//td[contains(@class,'day') and text()='" + day + "']";
				Wait.WaitForElement(driver, date);
				int size = driver.findElements(By.xpath(date)).size();
				for (int k = 1; k <= size; k++) {
					date = "(//td[contains(@class,'day') and text()='" + day + "'])[" + k + "]";
					String classText = driver.findElement(By.xpath(date)).getAttribute("class");
					if (!classText.contains("old")) {
						driver.findElement(By.xpath(date)).click();
						test_steps.add("Selecting to date : " + toDate);
						reportLogger.info("Selecting to date : " + toDate);
						break;
					}
				}

				break;
			} else {
				next = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[3]/i";
				Wait.WaitForElement(driver, next);
				driver.findElement(By.xpath(next)).click();
				Wait.wait2Second();
			}
		}
		return test_steps;
	}


	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <clickCheckBoxIncidentals> 
	 * ' Description: <This method will click Incidentals in ledger balance report> 
	 * ' Input parameters: <WebDriver driver> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Jamal Nasir>
	 * ' Created On:  <05/11/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String>  clickAllIncidentalsSelectCheckBox(WebDriver driver)
			throws InterruptedException {
		Elements_Reports elementsReports = new Elements_Reports(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.Incidentials_Checkbox);
		Wait.wait2Second();
		if(elementsReports.Incidentials_Checkbox.isSelected()){
			test_steps.add("Incidentials checkBox already clicked");
			reportLogger.info("Incidentials checkBox already clicked");
		}
		else{
			elementsReports.Incidentials_Checkbox.click();
			test_steps.add("Clicked Incidentials CheckBox");
			reportLogger.info("Clicked Incidentials CheckBox");
		}return test_steps;
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <selectGroupBy> 
	 * ' Description: <This method will select group by options in ledger balance report> 
	 * ' Input parameters: <WebDriver driver> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Jamal Nasir>
	 * ' Created On:  <05/18/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String>  selectGroupBy(WebDriver driver, String Category)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Select groupByDropDown = new Select(driver.findElement(By.id("MainContent_drpGroupByOption")));
		groupByDropDown.selectByVisibleText(Category);
		test_steps.add("Select Category : " + Category);
		reportLogger.info("Select Category : " + Category);
		return test_steps;
	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <clickIncidentalsCheckBoxes> 
	 * ' Description: <This method will select specific check box in ledger balance report> 
	 * ' Input parameters: <WebDriver driver, String incidentalName> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Jamal Nasir>
	 * ' Created On:  <05/18/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String>  clickIncidentalsCheckBoxes(WebDriver driver, String incidentalName, boolean isChecked)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String path = "(//label[contains(text(),'" + incidentalName + "')]//preceding-sibling::input)[1]";
		System.out.println(path);
		WebElement selectCheckBox = driver.findElement(By.xpath(path));
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Utility.ScrollToElement(selectCheckBox, driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);
		if(isChecked){
			if(selectCheckBox.isSelected()){
				test_steps.add(incidentalName + " already selected");
				reportLogger.info(incidentalName + " already selected");				
			}else{
				selectCheckBox.click();
				test_steps.add(incidentalName + " clicked");
				reportLogger.info(incidentalName + " clicked");
				}
		}else{
			if(selectCheckBox.isSelected()){
				selectCheckBox.click();
				test_steps.add(incidentalName + " unSelected");
				reportLogger.info(incidentalName + " unSelected");
				}else{
					test_steps.add(incidentalName + " already unSelected");
					reportLogger.info(incidentalName + " already unSelected");				
				}
		}
		
		return test_steps;
	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <clickItemStatus> 
	 * ' Description: <This method will select specific check box for ItemStatus in ledger balance report> 
	 * ' Input parameters: <WebDriver driver, String status> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Jamal Nasir>
	 * ' Created On:  <05/18/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */

	public ArrayList<String>  clickItemStatus(WebDriver driver, String status)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String path = "(//label[contains(text(),'" + status + "')]//preceding-sibling::input)[1]";
		System.out.println(path);
		WebElement selectCheckBox = driver.findElement(By.xpath(path));
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Utility.ScrollToElement(selectCheckBox, driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);
		selectCheckBox.click();
		test_steps.add("Item status " + status + " clicked");
		reportLogger.info("Item status " + status + " clicked");
		return test_steps;
	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <verifyLegderBalanceReport> 
	 * ' Description: <This method will verify ledger balance report content> 
	 * ' Input parameters: <WebDriver driver, String incidentalName, String incidentalName2, String firstRoomNumber, String secondRoomNumber> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Jamal Nasir>
	 * ' Created On:  <05/18/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	
	public ArrayList<String> verifyLegderBalanceReport(WebDriver driver, String incidentalName, String incidentalName2, String incidentalName3, String firstRoomNumber, String secondRoomNumber) throws InterruptedException{
		File theNewestFile = null;
		File getFile = null;
		Elements_Reports reports = new Elements_Reports(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		try{
					Wait.WaitForElement(driver, OR_Reports.ReportFrame);
					driver.switchTo().frame(reports.ReportFrame);					
					
					Wait.WaitForElement(driver, OR_Reports.ReportType);			
					new Actions(driver).moveToElement(reports.ReportType).build().perform();
		
					assertTrue(reports.ReportDisplay.isDisplayed(), "Report isn't Displayed");
					Utility.app_logs.info("PDF Report is Displayed");
					test_steps.add("PDF Report is Displayed");
			
					String dataPath=reports.ViewPDF.getAttribute("data");
					driver.get(dataPath);
	
					String fileName = Utility.download_status(driver);
					Wait.wait2Second();
					
					getFile = new File(System.getProperty("user.dir") + File.separator + "externalFiles"
							+ File.separator + "downloadFiles" + File.separator + fileName);
					test_steps.add("PDF report file downloaded : "+getFile.getAbsolutePath());
					reportLogger.info("PDF report file downloaded : "+getFile.getAbsolutePath());

					File dir = new File(System.getProperty("user.dir")+"/externalFiles/downloadFiles");
					FileFilter fileFilter = new WildcardFileFilter("*." + "pdf");
					File[] files = dir.listFiles(fileFilter);
					if (files.length > 0) {
						Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
						theNewestFile = files[0];
					}
					Wait.wait3Second();
					
					try 
					{
						PDDocument document = PDDocument.load(theNewestFile);
						document.getClass();
						if (!document.isEncrypted()) {
							PDFTextStripperByArea stripper = new PDFTextStripperByArea();
							stripper.setSortByPosition(true);
							PDFTextStripper tStripper = new PDFTextStripper();
							String pdfFileInText = tStripper.getText(document);
							HashMap<String, Integer> map = new HashMap<>();							
							String lines[] = pdfFileInText.split("\\n");
							for (String line : lines) {
								if(line.contains(incidentalName)){
									if(!map.containsKey(incidentalName)){
										if(line.contains(firstRoomNumber)){
											if(!map.containsKey(firstRoomNumber)){
												test_steps.add("Verified that item description (" + incidentalName + ") exist in report");
												reportLogger.info("Verified that item description (" + incidentalName + ") exist in report");
												map.put(incidentalName, 1);
												test_steps.add("Verified that item description (" + incidentalName + ") is displayed along with Room number (" + firstRoomNumber + ") ");
												reportLogger.info("Verified that item description (" + incidentalName + ") is displayed along with Room number (" + firstRoomNumber + ") ");
												map.put(firstRoomNumber, 1);
											}
										}
									}
								}
								if(line.contains(incidentalName2)){
									if(!map.containsKey(incidentalName2)){
										if(line.contains(firstRoomNumber)){
												test_steps.add("Verified that item description (" + incidentalName2 + ") exist in report");
												reportLogger.info("Verified that item description (" + incidentalName2 + ") exist in report");
												map.put(incidentalName2, 1);
												test_steps.add("Verified that item description (" + incidentalName2 + ") is displayed along with Room number (" + firstRoomNumber + ")");
												reportLogger.info("Verified that item description (" + incidentalName2 + ") is displayed along with Room number (" + firstRoomNumber + ")");												
												map.put(firstRoomNumber, 2);
										}
									}
								}
								if(line.contains(incidentalName3)){
									if(!map.containsKey(incidentalName3)){
										if(line.contains(secondRoomNumber)){
												test_steps.add("Verified that item description (" + incidentalName3 + ") exist in report");
												reportLogger.info("Verified that item description (" + incidentalName3 + ") exist in report");
												map.put(incidentalName3, 1);
												test_steps.add("Verified that item description (" + incidentalName3 + ") is displayed along with Room number (" + secondRoomNumber + ")");
												reportLogger.info("Verified that item description (" + incidentalName3 + ") is displayed along with Room number (" + secondRoomNumber + ")");
												map.put(secondRoomNumber, 1);
										}
									}
								}
							}
						}
						document.close();
					}catch(Exception e){
						e.printStackTrace();
					}
		}catch(Exception e){
			e.printStackTrace();
		}
			Utility.deleteFile(getFile);
			test_steps.add("Deleted report file");
			reportLogger.info("Deleted report file");
		return test_steps;
	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <navigateAccountBalance> 
	 * ' Description: <This method will navigate to Account Balance tab> 
	 * ' Input parameters: <WebDriver> 
	 * ' Return value: <void>
	 * ' Created By: <Adhnan Ghaffar>
	 * ' Created On:  <06/02/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void navigateAccountBalance(WebDriver driver) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		Wait.WaitForElement(driver, OR.AccountsBalance);
		Wait.waitForElementToBeClickable(By.xpath(OR.AccountsBalance), driver);
		Utility.ScrollToElement(elements_Reports.AccountsBalance, driver);
		elements_Reports.AccountsBalance.click();
		Wait.explicit_wait_visibilityof_webelement(elements_Reports.AccountBalance_Header, driver);
		assertEquals(elements_Reports.AccountBalance_Header.getText(), "Account Balance Summary",
				"Account balance header does not display");

	}
	
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <selectLedgerType> 
	 * ' Description: <This method will select ledger type> 
	 * ' Input parameters: <WebDriver , String> 
	 * ' Return value: <void>
	 * ' Created By: <Adhnan Ghaffar>
	 * ' Created On:  <06/02/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void selectLedgerType(WebDriver driver, String LedgerType) throws InterruptedException {

		Elements_Reports elements_Reports = new Elements_Reports(driver);
		Wait.WaitForElement(driver, OR.Select_LedgerType);
		new Select(elements_Reports.Select_LedgerType).selectByVisibleText(LedgerType);
		

	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <selectAccountTypeCheckBox> 
	 * ' Description: <This method will select the label's checkBox based on the 'checked' parameter> 
	 * ' Input parameters: <WebDriver, String, boolean> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Adhnan Ghaffar>
	 * ' Created On:  <06/02/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String> selectAccountTypeCheckBox(WebDriver driver, String label, boolean checked,
			ArrayList<String> steps) throws InterruptedException {
		String xpath = "(//label[text()='" + label + "']//preceding-sibling::input)[last()]";
		Wait.WaitForElement(driver, xpath);
		WebElement checkBox = driver.findElement(By.xpath(xpath));
		if (checked) {
			if (!checkBox.isSelected()) {
				Utility.ScrollToElement(checkBox, driver);
				checkBox.click();
				steps.add("Click " + label + " checkBox");
				reportLogger.info("Click " + label + " checkBox");
			}
			steps.add(label + " checkBox : Checked");
			reportLogger.info(label + " checkBox : Checked");
		} else {
			if (checkBox.isSelected()) {
				Utility.ScrollToElement(checkBox, driver);
				checkBox.click();
				steps.add("Click " + label + " checkBox");
				reportLogger.info("Click " + label + " checkBox");
			}
			steps.add(label + " checkBox : UnChecked");
			reportLogger.info(label + " checkBox : UnChecked");
		}
		return steps;
	}
	
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <printReportData> 
	 * ' Description: <This method will download the report and print it on console> 
	 * ' Input parameters: <WebDriver> 
	 * ' Return value: <void>
	 * ' Created By: <Adhnan Ghaffar>
	 * ' Created On:  <02/06/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void printReportData(WebDriver driver) throws InterruptedException, IOException {
		
		
		String fileName = Utility.download_status(driver);
		String lines[] = null;
		// String content = null;
		if (fileName.isEmpty()) {
			// if filename is empty than we've to go with last modified
			// file
			// content = Utility.checkPDF(fileName);
			lines = Utility.checkPDFArray(fileName);// taskList.readFile();
		} else {
			lines = Utility.checkPDFArray(fileName);// taskList.readFile();
		}
		System.out.println("line size : " + lines.length);
		int i = 0;
		for (String line : lines) {
			Utility.app_logs.info(i + " : " + line);
		}
	}
	public File verifyGuestStement(WebDriver driver,String fileName,ArrayList<String> documentfields,String ContentFields,ArrayList<String> test_steps) throws InterruptedException{
   	 
		File theNewestFile = null;
 		File getFile = null;
 		/*String line1 =checkInDate+" "+category+" "+description+" "+validateRoomwithRoomNumber1+" "+" $ "+" "+tax+" "+" $"+" "+amount;
		String line2=checkOutDate+" "+category+" "+description+" "+validateRoomwithRoomNumber1+" "+" $ "+" "+tax+" "+" $"+" "+amount;*/
 	 getFile = new File(System.getProperty("user.dir") + File.separator + "externalFiles"
					+ File.separator + "downloadFiles" + File.separator + fileName);


			test_steps.add("PDF report file downloaded : " + getFile.getAbsolutePath());
			File dir = new File(System.getProperty("user.dir") + "/externalFiles/downloadFiles");
			FileFilter fileFilter = new WildcardFileFilter("*." + "pdf");
			File[] files = dir.listFiles(fileFilter);
			if (files.length > 0) {
				Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
				theNewestFile = files[0];
			}
			Wait.wait3Second();
			try {
				PDDocument document = PDDocument.load(theNewestFile);
				document.getClass();
				if (!document.isEncrypted()) {
					PDFTextStripperByArea stripper = new PDFTextStripperByArea();
					stripper.setSortByPosition(true);
					PDFTextStripper tStripper = new PDFTextStripper();
					String pdfFileInText = tStripper.getText(document);
					String lines[] = pdfFileInText.split("\\n");
					boolean flag = true;
					for (String string : documentfields) {
						boolean propertyFound = false;
						for (String line : lines) {
							if (string.equalsIgnoreCase(line.trim())) {
								test_steps.add("Verified property details (" + string + ") in Guest Statement Report");
								reportLogger.info("Verified propertyDetails  (" + string + ") in Guest Statement Report");
								propertyFound=true;
								break;
							}
						}
						if (!propertyFound) {
							test_steps.add("Property details for (" + string + ") not found in Guest Statement Report");
							reportLogger.info("Property details for (" + string + ") not in Guest Statement Report");
						}
				}
					
				}
}catch (Exception e){
	
	
}
			return getFile;
	}
		 
	public void verifyGuestStatementReportGenerated(WebDriver driver,ArrayList<String>test_steps) throws InterruptedException
	{
		 
		try
		{
			boolean isExist=Utility.isElementPresent(driver, By.id("embdreportViewer"));
			if(isExist)
			{
				test_steps.add("PDF Report is Displayed and Gnerate Report in New Tab");
				Utility.app_logs.info("PDF Report is Displayed and Gnerate Report in New Tab");
			}
				else
			{
				test_steps.add("PDF Report is Not Displayed, but Gnerate Report in New Tab");
				Utility.app_logs.info("PDF Report is Not Displayed but Gnerate Report in New Tab");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}


}