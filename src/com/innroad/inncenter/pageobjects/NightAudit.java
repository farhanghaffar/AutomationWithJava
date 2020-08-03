package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.innroad.inncenter.interfaces.INightAudit;
import com.innroad.inncenter.interfaces.IPolicies;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Accounts;
import com.innroad.inncenter.webelements.Elements_NightAudit;
import com.innroad.inncenter.webelements.WebElements_Policies;

public class NightAudit implements INightAudit {

	public static Logger NigthAuditLogger = Logger.getLogger("NigthAuditLogger");

	@Override
	public void EnterAuditDate(WebDriver driver, String Date) throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_NightAudit NightAudit = new Elements_NightAudit(driver);
		Wait.WaitForElement(driver, OR.AuditDatePicker);
		Utility.ScrollToElement(NightAudit.AuditDatePicker, driver);
		NightAudit.AuditDatePicker.click();
		Wait.explicit_wait_visibilityof_webelement(NightAudit.ClickToday, driver);
		NightAudit.ClickToday.click();

	}

	@Override
	public void GoButtonClick(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_NightAudit NightAudit = new Elements_NightAudit(driver);
		NightAudit.Go_ButtonClick.click();

	}

	@Override
	public String GetHouseKeepingCount(WebDriver driver) throws InterruptedException {

		Elements_NightAudit NightAudit = new Elements_NightAudit(driver);
		String Count;
		Wait.explicit_wait_visibilityof_webelement(NightAudit.HouseKeepingCount, driver);
		Count = NightAudit.HouseKeepingCount.getText();
		Utility.app_logs.info(Count);
		return Count;
	}

	@Override
	public void VerifyHouseKeepingSection(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_NightAudit NightAudit = new Elements_NightAudit(driver);

		boolean SetNowButton = NightAudit.SetNowButton.isEnabled();
		assertEquals(SetNowButton, true, "HouseKeepingText isnot Enabled ");

		boolean HouseKeepingText = NightAudit.HouseKeepingText.isDisplayed();
		assertEquals(HouseKeepingText, true, "HouseKeepingText is Clickable");
	}

	public void ClickSetNowButton(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_NightAudit NightAudit = new Elements_NightAudit(driver);

		boolean SetNowButton = NightAudit.SetNowButton.isEnabled();
		assertEquals(SetNowButton, true, "HouseKeepingText isnot Enabled ");
		NightAudit.SetNowButton.click();
		Wait.explicit_wait_visibilityof_webelement(NightAudit.UpdateRoomCondition_Popup, driver);
		assertTrue(NightAudit.UpdateRoomCondition_Popup.isDisplayed(),
				"Failed : Update Room Condition Popup is not displayed ");
		NightAudit.UpdateRoomCondition_Yes.click();
	}

	@Override
	public void EnterAuditDate(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_NightAudit NightAudit = new Elements_NightAudit(driver);
		NightAudit.AuditDatePicker.click();
		NightAudit.ClickToday.click();
		String GetDate = NightAudit.AuditDate.getAttribute("value");
		NightAudit.AuditDatePicker.click();
		String CurrrentMonthDateAndDay = driver.findElement(By.cssSelector(".active.day")).getAttribute("title");
		String[] CurrentDateAndYear = CurrrentMonthDateAndDay.split(", ");
		String CurrentMonthAndDate = CurrentDateAndYear[1];
		String CurrentDate = CurrentMonthAndDate.split(" ")[1];
		String CurrentMonthFullName = CurrentMonthAndDate.split(" ")[0];
		String CurrentMonthAbbrevation = CurrentMonthFullName.substring(0, Math.min(CurrentMonthFullName.length(), 3));
		String CurrentYear = CurrentDateAndYear[2];
		int CurrentDateInt = Integer.valueOf(CurrentDate);
		if (CurrentDateInt < 10) {
			CurrentDate = Integer.toString(CurrentDateInt);
			CurrentDate = "0" + CurrentDate;
		}
		assertTrue(GetDate.equals(CurrentMonthAbbrevation + " " + CurrentDate + ", " + CurrentYear),
				"Selcted audit date isn't displayed");

	}

	public String GetSystemDate() {

		DateFormat dateInstance = SimpleDateFormat.getDateInstance();
		String date = dateInstance.format(Calendar.getInstance().getTime());
		String SplitDate[] = date.split(" ");
		String month = SplitDate[0];
		String day = SplitDate[1];
		String d[] = day.split(",");
		String year = SplitDate[2];
		if (Integer.parseInt(d[0]) < 10) {
			day = "0" + d[0];
		}
		String formate = month + " " + day + " " + year;
		return formate;

	}

	@Override
	public void DailyTransactionButtonClick(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_NightAudit NightAudit = new Elements_NightAudit(driver);
		NightAudit.DailyTransactionButton.click();
		assertTrue(NightAudit.DailyTransactionPage.isDisplayed(), "Daily Transaction Page isn't displayed");
		TableRowCountBefore = GetTableRowCount(driver);
	}

	public static int TableRowCountBefore = 0;
	public static int TableRowCountAfter = 0;

	public int GetTableRowCount(WebDriver driver) {

		int i = 0;
		WebElement table = driver.findElement(By.xpath("//*[@id='MainContent_dgCurrentNightAuditList']"));
		List<WebElement> listOfRows1 = table.findElements(By.className("dgItem"));
		List<WebElement> listOfRows2 = table.findElements(By.className("dgItemAlt"));
		i = listOfRows2.size() + listOfRows1.size();
		return i;
	}

	@Override
	public void SelectAllItemStatus(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_NightAudit NightAudit = new Elements_NightAudit(driver);

		for (int i = 0; i < 4; i++) {
			WebElement ItemStatus = driver.findElement(By.id("MainContent_chkItemStatus_" + i));

			if (!ItemStatus.isSelected()) {
				ItemStatus.click();
				boolean itemStatusState = ItemStatus.isSelected();
				assertEquals(itemStatusState, true, "itemStatusState isn't true");

			}
		}
		Wait.wait2Second();
		NightAudit.DailyTransactionPage_GoBtn.click();

	}

	@Override
	public void DailyTransaction_PostButtonClick(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_NightAudit NightAudit = new Elements_NightAudit(driver);
		NightAudit.ItemToPost.click();
		Wait.wait2Second();
		NightAudit.DailyTransactionPage_PostBtn.click();
		Wait.wait15Second();
		assertTrue(TableRowCountBefore > TableRowCountAfter, "Post Button isn't Clicked");

	}

	// Period Is Open
	@Override
	public void PeriodIsOpenButtonClick(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_NightAudit NightAudit = new Elements_NightAudit(driver);
		NightAudit.PeriodIsOpenButton.click();
		assertTrue(NightAudit.DailyTransactionPage.isDisplayed(), "Daily Transaction Page isn't displayed");

	}

	public void PeriodIsOpen_LockButtonClick(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_NightAudit NightAudit = new Elements_NightAudit(driver);
		driver.switchTo().frame("dialog-body0");
		NightAudit.PeriodIsOpen_LockButton.click();
		Wait.wait2Second();
		driver.switchTo().defaultContent();
		assertTrue(NightAudit.PeriodStatusPage.isDisplayed(), "Period Statud Page isn't displayed");

	}

	public void PostAllItemCheckBox(WebDriver driver) throws InterruptedException {
		TableRowCountBefore = GetTableRowCount(driver);
		// TODO Auto-generated method stub
		Elements_NightAudit NightAudit = new Elements_NightAudit(driver);
		NightAudit.DialyTrans_CheckBoxAll.click();
		DailyTransaction_PostButtonClick(driver);

	}

	public String LongStay(WebDriver driver) throws InterruptedException {

		Elements_NightAudit NightAudit = new Elements_NightAudit(driver);
		Wait.WaitForElement_ID(driver, OR.VerifyLongStay);
		Wait.explicit_wait_visibilityof_webelement(NightAudit.VerifyLongStay, driver);
		return NightAudit.VerifyLongStay.getText();
	}

	public ArrayList<String> LongStay_SetNow(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_NightAudit nightAudit = new Elements_NightAudit(driver);
		Wait.explicit_wait_visibilityof_webelement(nightAudit.LongStay_SetNowBtn, driver);
		nightAudit.LongStay_SetNowBtn.click();
		test_steps.add("Click on Set Now button");
		
		assertEquals(nightAudit.LongStay_Popup.getText(), "Long Stay", "Long stay popup is not appearing");
		test_steps.add("verified Long Stay popup appear");
		assertTrue(
				nightAudit.LongStayMessage.getText().contains(
						"  Reservations meet the tax exemption criteria under Long Stay. Do you wish to set Long Stay?"),
				"Long stay popup is not appearing");
		
		assertEquals(nightAudit.LongStay_YesButton.getText(), "Yes", "Yes button is not appear on long stay popup");
		test_steps.add("verified Long Stay popup had Yes button");
		
		assertEquals(nightAudit.LongStay_NoButtons.getText(), "No", "No button is not appear on long stay popup");
		test_steps.add("verified Long Stay popup had No button");
		
		nightAudit.LongStay_YesButton.click();
		test_steps.add("Click on Yes button");
		Wait.wait2Second();
		
		Wait.explicit_wait_visibilityof_webelement(nightAudit.VerifyLongStay, driver);
		assertEquals(nightAudit.VerifyLongStay.getText(), "0", "Long stay value did not set 0");
		test_steps.add("Reset Long Stay value again 0");
		
		return test_steps;

	}
	
	public void Get_NightAuditDate(WebDriver driver,ArrayList<String> test_steps)
	{
		Elements_NightAudit NightAudit = new Elements_NightAudit(driver);
		String GetDate = NightAudit.AuditDate.getAttribute("value");
		test_steps.add("Night Audit Date: <b>"+GetDate+" </b>");
		NigthAuditLogger.info("Night Audit Date: "+GetDate+"");
	}
	
	public void Verified_HouseKeeping_No_Increase(WebDriver driver,ArrayList<String> test_steps, int BeforeCheckIn, int AfterCheckInNO, String GetHouseKeepintNO)
	{
		int HouseKeepingNOIncrease = BeforeCheckIn+AfterCheckInNO;
		String HouseKeepingIncreaseNO=String.valueOf(HouseKeepingNOIncrease);
		System.out.println(GetHouseKeepintNO.trim());
		System.out.println((String.valueOf(HouseKeepingNOIncrease)).trim());
		assertEquals(GetHouseKeepintNO.trim(),(HouseKeepingIncreaseNO.trim()) ,"Failed: to Verify HouseKeeping Number");
		test_steps.add("Verified House Keeping No Increase To: <b>"+String.valueOf(HouseKeepingNOIncrease)+" </b>");
		NigthAuditLogger.info("Verified House Keeping No Increase To:"+String.valueOf(HouseKeepingNOIncrease));
	}
	
	public void Verified_HouseKeeping_No_Decrease(WebDriver driver,ArrayList<String> test_steps,  int AfterCheckInNO, int AfterCheckOut,String GetHouseKeepintNO)
	{
		int HouseKeepingNODecrease = AfterCheckInNO-AfterCheckOut;
		String HouseKeepingDecreaseNO=String.valueOf(HouseKeepingNODecrease);
		System.out.println(GetHouseKeepintNO.trim());
		System.out.println((String.valueOf(HouseKeepingNODecrease)).trim());
		assertTrue(GetHouseKeepintNO.trim().equals(HouseKeepingDecreaseNO.trim()) ,"Failed: to Verify HouseKeeping Number");
		test_steps.add("Verified House Keeping No Decrease To: <b>"+String.valueOf(HouseKeepingNODecrease)+" </b>");
		NigthAuditLogger.info("Verified House Keeping No Decrease To:"+String.valueOf(HouseKeepingNODecrease));
	}
}
