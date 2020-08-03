package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Inventory;
import com.innroad.inncenter.webelements.Elements_RoomMaintenance;

public class RoomMaintenance {

	public static Logger accountsLogger = Logger.getLogger("RoomMaintenance");

	public String CreateNewRoomOut(WebDriver driver, String Night, String Subjet) throws InterruptedException {

		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		elements_RoomMaintenance.NewRoomMaintenance_Button.click();
		Wait.wait2Second();
		elements_RoomMaintenance.SelectStart_EndDate.get(0).click();
		Wait.wait2Second();
		String getDate = elements_RoomMaintenance.GetActiveDate.getText();
		elements_RoomMaintenance.GetActiveDate.click();
		elements_RoomMaintenance.SelectStart_EndDate.get(1).click();
		Wait.wait2Second();
		String ActiveDate = elements_RoomMaintenance.GetActiveDate.getText();
		int date = Integer.parseInt(ActiveDate);
		if (date == 28 || date == 30 || date == 31) {
			date = 1;
		} else {
			date = date + 1;
		}

		Wait.wait2Second();

		elements_RoomMaintenance.EnterRoomMaintenance_Night.clear();
		elements_RoomMaintenance.EnterRoomMaintenance_Night.sendKeys(Night);
		elements_RoomMaintenance.EnterRoomMaintenance_Subject.sendKeys(Subjet);
		elements_RoomMaintenance.RoomMaintenance_AssociateRoom.click();

		driver.switchTo().frame(0);
		Wait.waitUntilPresenceOfElementLocated(OR.RoomPicker_Popup, driver);
		assertEquals(elements_RoomMaintenance.RoomPicker_Popup.getText(), "Room Picker",
				"Room picker does not display");
		elements_RoomMaintenance.SelectRoom_OutofOrder.click();
		String roomclass = elements_RoomMaintenance.GetRoomClass.getText();
		String roomnum = elements_RoomMaintenance.GetRoomNumber.getText();
		elements_RoomMaintenance.Select_RoomButton.click();

		Wait.waitUntilPresenceOfElementLocated(OR.RoomMaintenance_Save, driver);
		elements_RoomMaintenance.RoomMaintenance_Save.click();
		Wait.waitUntilPresenceOfElementLocated(OR.DoneButton, driver);
		elements_RoomMaintenance.DoneButton.click();

		return roomclass + " : " + roomnum + " : " + getDate;

	}

	public void CreateNewRoomOut(WebDriver driver, String Night, String Subjet, String RoomNumber,String RoomClass,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		elements_RoomMaintenance.NewRoomMaintenance_Button.click();
		accountsLogger.info("New Room Maintenance Clicked");
		test_steps.add("New Room Maintenance Clicked");
		Wait.explicit_wait_visibilityof_webelement(elements_RoomMaintenance.SelectStart_EndDate.get(0), driver);
		Utility.ScrollToElement(elements_RoomMaintenance.SelectStart_EndDate.get(0), driver);
		elements_RoomMaintenance.SelectStart_EndDate.get(0).click();
		String getDate = elements_RoomMaintenance.GetActiveDate.getText();
		elements_RoomMaintenance.GetActiveDate.click();
		Wait.explicit_wait_visibilityof_webelement(elements_RoomMaintenance.SelectStart_EndDate.get(1), driver);
		Utility.ScrollToElement(elements_RoomMaintenance.SelectStart_EndDate.get(1), driver);
		elements_RoomMaintenance.SelectStart_EndDate.get(1).click();
		Wait.wait2Second();
		String ActiveDate = elements_RoomMaintenance.GetActiveDate.getText();
		int date = Integer.parseInt(ActiveDate);
		if (date == 28 || date == 30 || date == 31) {
			date = 1;
		} else {
			date = date + 1;
		}

		Wait.wait2Second();

		elements_RoomMaintenance.EnterRoomMaintenance_Night.clear();
		elements_RoomMaintenance.EnterRoomMaintenance_Night.sendKeys(Night);
		elements_RoomMaintenance.EnterRoomMaintenance_Subject.sendKeys(Subjet);

		accountsLogger.info("Required Feilds Filled");
		test_steps.add("Required Feilds Filled");

		elements_RoomMaintenance.RoomMaintenance_AssociateRoom.click();

		driver.switchTo().frame(0);
		Wait.waitUntilPresenceOfElementLocated(OR.RoomPicker_Popup, driver);
		assertEquals(elements_RoomMaintenance.RoomPicker_Popup.getText(), "Room Picker",
				"Room picker does not display");
		accountsLogger.info("Room Picker Dialoge is Displayed");
		test_steps.add("Room Picker Dialoge is Displayed");

		new Select(driver.findElement(By.xpath("//*[@id='drpRoomClassList']"))).selectByVisibleText(RoomClass);
		Wait.wait2Second();
		driver.findElement(By.xpath("//*[@id='btnGo']")).click();
		Wait.wait2Second();
		new Select(driver.findElement(By.xpath("//*[@id='ddlItemsPerPage']"))).selectByIndex(3);
		Wait.wait2Second();
		String checkBoxByRoomNumber = "//tr/td[4][contains(text(),'" + RoomNumber
				+ "')]/preceding-sibling::td/span/input[contains(@type,'checkbox')]";

		System.out.println(checkBoxByRoomNumber);

		WebElement checkBoxOfRoom = driver.findElement(By.xpath(checkBoxByRoomNumber));
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("arguments[0].scrollIntoView()", checkBoxOfRoom);
		checkBoxOfRoom.click();
		Utility.ScrollToElement(elements_RoomMaintenance.Select_RoomButton, driver);
		Wait.explicit_wait_elementToBeClickable(elements_RoomMaintenance.Select_RoomButton, driver);
		elements_RoomMaintenance.Select_RoomButton.click();
		Wait.wait2Second();
		try{
			if(elements_RoomMaintenance.RoomPicker_Popup.isDisplayed()) {
				if(!checkBoxOfRoom.isSelected()) {
					jse.executeScript("arguments[0].scrollIntoView()", checkBoxOfRoom);
					checkBoxOfRoom.click();
					Utility.ScrollToElement(elements_RoomMaintenance.Select_RoomButton, driver);
					elements_RoomMaintenance.Select_RoomButton.click();
				}
			}
		}catch (Exception e) {
			System.out.println("Room Picker Gone");
		}
		accountsLogger.info("Selected Room # " + RoomNumber);
		test_steps.add("Selected Room # " + RoomNumber);

		Wait.waitUntilPresenceOfElementLocated(OR.RoomMaintenance_Save, driver);
		elements_RoomMaintenance.RoomMaintenance_Save.click();
		Wait.waitUntilPresenceOfElementLocated(OR.DoneButton, driver);
		elements_RoomMaintenance.DoneButton.click();

		accountsLogger.info("Room Item Saved and Done Clicked");
		test_steps.add("Room Item Saved and Done Clicked");
	}

	public String Delete_RoomOutOfOrder(WebDriver driver, String NameOfRoom) {
		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);

		String RoomClass = elements_RoomMaintenance.RoomMaintenance_FirstActiveElement_RoomClass.getText();
		Wait.waitUntilPresenceOfElementLocated(OR.InputRoomOutOfOrder, driver);
		elements_RoomMaintenance.InputRoomOutOfOrder.click();
		elements_RoomMaintenance.DeleteButton.click();
		return RoomClass;

	}

	public String UpdateOutOfOrderRoom(WebDriver driver, String Subject) throws InterruptedException {

		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		String Edit_OutOrderRoom = "//a[.='" + Subject + "']";
		Wait.waitUntilPresenceOfElementLocated(Edit_OutOrderRoom, driver);
		WebElement element = driver.findElement(By.xpath(Edit_OutOrderRoom));
		element.click();

		Wait.waitUntilPresenceOfElementLocated(OR.RoomMaintenance_AssociateRoom, driver);
		elements_RoomMaintenance.RoomMaintenance_AssociateRoom.click();

		driver.switchTo().frame(0);
		Wait.waitUntilPresenceOfElementLocated(OR.RoomPicker_Popup, driver);
		assertEquals(elements_RoomMaintenance.RoomPicker_Popup.getText(), "Room Picker",
				"Room picker does not display");
		elements_RoomMaintenance.SelectRoom_OutofOrder.click();
		String roomclass = elements_RoomMaintenance.GetRoomClass.getText();
		String roomnum = elements_RoomMaintenance.GetRoomNumber.getText();
		elements_RoomMaintenance.Select_RoomButton.click();

		driver.switchTo().defaultContent();
		Wait.wait2Second();
		Wait.waitUntilPresenceOfElementLocated(OR.RemoveButton, driver);
		elements_RoomMaintenance.RemoveButton.click();

		Wait.waitUntilPresenceOfElementLocated(OR.RoomMaintenance_Save, driver);
		elements_RoomMaintenance.RoomMaintenance_Save.click();

		return roomclass + "," + roomnum;

	}

	public void VerifyRecordOverview(WebDriver driver, String counts) {

		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Records, driver);
		String records = elements_RoomMaintenance.Records.getText();
		String split[] = records.split(" ");
		String count = split[0];
		assertEquals(count, counts, "record does not match");

	}

	public String GetRecords(WebDriver driver) {

		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Records, driver);
		String records = elements_RoomMaintenance.Records.getText();
		String split[] = records.split(" ");
		String count = split[0];
		return count;

	}

	public void DeleteRoomOutOfOrder(WebDriver driver, String Subject) throws InterruptedException {
		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);

		try {
		String path = "//table[@id='MainContent_dgMaintenanceList']//a[text()='" + Subject
				+ "']//parent::td//following-sibling::td[last()]//input[@type='checkbox']";
		WebElement room = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(room, driver);
		room.click();

		elements_RoomMaintenance.DeleteButton.click();
		}catch (Exception e) {
			assertTrue(false);
		}
	}

	public void SearchRooms(WebDriver driver, String RoomType, String RoomNumber) {

		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_RoomMaintenance.RoomMaintenance_Room, driver);
		elements_RoomMaintenance.SelectStart_EndDate.get(0).click();
		elements_RoomMaintenance.SelectDate_Today.click();
		elements_RoomMaintenance.RoomMaintenance_Room.clear();
		elements_RoomMaintenance.RoomMaintenance_Room.sendKeys(RoomNumber);
		new Select(elements_RoomMaintenance.RoomMaintenance_Reason).selectByVisibleText(RoomType);

		elements_RoomMaintenance.RoomMaintenance_GoButton.click();
		try {

			System.out.println(elements_RoomMaintenance.RoomMaintenance_VerifyReason.getText());
		}

		catch (Exception e) {
			System.out.println(elements_RoomMaintenance.RoomMaintenance_VerifyReason.getText());

		}
		assertTrue(elements_RoomMaintenance.RoomMaintenance_VerifyReason.getText().equals(RoomType),
				"Failed: Room not searched correctly");

	}

	public void OpenRoom(WebDriver driver, String Subject) throws InterruptedException {

		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		Wait.wait5Second();
		String path = "//table[@id='MainContent_dgMaintenanceList']//a[text()='" + Subject + "']";
		WebElement room = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(room, driver);
		room.click();
		System.out.println("CLicked");
		assertTrue(elements_RoomMaintenance.RoomMaintenance_DetailPage.isDisplayed(),
				"Failed:Detail Page not displayed");

	}

	public void AddNotes(WebDriver driver, String Subject, String Desc, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		Wait.wait2Second();
		elements_RoomMaintenance.RoomMaintenance_AddNotes.click();
		accountsLogger.info("Room Maintenance Add Notes Clicked");
		test_steps.add("Room Maintenance Add Notes Clicked");
		driver.manage().window().maximize();
		driver.switchTo().frame("dialog-body0");
		assertTrue(elements_RoomMaintenance.RoomMaintenance_NotesDetailPage.isDisplayed(),
				"Failed:Detail Page is not displayed");

		accountsLogger.info("Notes Page Displayed");
		test_steps.add("Notes Page Displayed");

		elements_RoomMaintenance.RoomMaintenance_Notes_Subject.sendKeys(Subject);
		elements_RoomMaintenance.RoomMaintenance_Notes_Detail.sendKeys(Desc);
		elements_RoomMaintenance.RoomMaintenance_Notes_SaveButton.click();

		accountsLogger.info("Notes Added Successfully");
		test_steps.add("Notes Added Successfully");
		Wait.wait2Second();
		driver.switchTo().defaultContent();
		assertTrue(elements_RoomMaintenance.RoomMaintenance_DetailPage.isDisplayed(),
				"Failed:Detail Page not displayed");
		test_steps.add("Successfully Modified RoomMain_Item");
		accountsLogger.info("Successfully Modified RoomMain_Item");

	}

	public ArrayList<String> clickNewButton(WebDriver driver){
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_RoomMaintenance.NewRoomMaintenance_Button, driver);
		elements_RoomMaintenance.NewRoomMaintenance_Button.click();
		accountsLogger.info("New button Clicked");
		test_steps.add("New button Clicked");
		return test_steps;
	}
	
	public ArrayList<String> saveClicked(WebDriver driver){
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.RoomMaintenance_Save, driver);
		elements_RoomMaintenance.RoomMaintenance_Save.click();
		accountsLogger.info("Save button Clicked");
		test_steps.add("Save button Clicked");
		return test_steps;
	}
	
	public ArrayList<String> doneClicked(WebDriver driver){
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.DoneButton, driver);
		elements_RoomMaintenance.DoneButton.click();
		accountsLogger.info("Done button Clicked");
		test_steps.add("Done button Clicked");
		return test_steps;
	}
	
	public ArrayList<String> validateReqMessage(WebDriver driver){
		ArrayList<String> test_steps=new ArrayList<String>();
		
		WebElement reqMessageAllFeilds=driver.findElement(By.xpath("//*[@id='MainContent_dispSumm']"));
		assertEquals(reqMessageAllFeilds.isDisplayed(), true,"Failed to Validate: * not displayed");
		assertEquals(reqMessageAllFeilds.getText(), "All fields marked with an * are required fields","Failed to Validate Message");
		assertEquals(reqMessageAllFeilds.getCssValue("color"),"rgba(255, 0, 0, 1)","Failed to Validate: Color should be Red");
		accountsLogger.info("Validate : "+reqMessageAllFeilds.getText());
		test_steps.add("Validate : "+reqMessageAllFeilds.getText());
		
		return test_steps;
	}
	
	public ArrayList<String> validateRoomReqMessage(WebDriver driver){
		ArrayList<String> test_steps=new ArrayList<String>();
		WebElement reqMessage=driver.findElement(By.xpath("//*[@id='MainContent_lblErrorMessage']/li"));
		assertEquals(reqMessage.isDisplayed(), true,"Failed to Validate: * not displayed");
		assertEquals(reqMessage.getText(), "Please select atleast one room.","Failed to Validate Message");
		assertEquals(reqMessage.getCssValue("color"),"rgba(255, 0, 0, 1)","Failed to Validate: Color should be Red");
		accountsLogger.info("Validate : "+reqMessage.getText());
		test_steps.add("Validate : "+reqMessage.getText());
		
		return test_steps;
	}
	
	public ArrayList<String> validateReqDateStart(WebDriver driver){
		ArrayList<String> test_steps=new ArrayList<String>();
		
		WebElement reqMessage=driver.findElement(By.xpath("//*[@id='MainContent_reqtxtDateStart']"));
		assertEquals(reqMessage.isDisplayed(), true,"Failed to Validate: * not displayed");
		assertEquals(reqMessage.getText(), "*","Failed to Validate");
		assertEquals(reqMessage.getCssValue("color"),"rgba(255, 0, 0, 1)","Failed to Validate: Color should be Red");
		accountsLogger.info("Validate : Date Start Required "+reqMessage.getText());
		test_steps.add("Validate : Date Start Required "+reqMessage.getText());
		
		return test_steps;
	}
	

	public ArrayList<String> validateReqDateEnd(WebDriver driver){
		ArrayList<String> test_steps=new ArrayList<String>();
		
		WebElement reqMessage=driver.findElement(By.xpath("//*[@id='MainContent_reqtxtDateEnd']"));
		assertEquals(reqMessage.isDisplayed(), true,"Failed to Validate: * not displayed");
		assertEquals(reqMessage.getText(), "*","Failed to Validate");
		assertEquals(reqMessage.getCssValue("color"),"rgba(255, 0, 0, 1)","Failed to Validate: Color should be Red");
		accountsLogger.info("Validate : Date End Required "+reqMessage.getText());
		test_steps.add("Validate : Date End Required "+reqMessage.getText());
		
		return test_steps;
	}
	

	public ArrayList<String> validateReqSubject(WebDriver driver){
		ArrayList<String> test_steps=new ArrayList<String>();
		
		WebElement reqMessage=driver.findElement(By.xpath("//*[@id='MainContent_reqSubject']"));
		assertEquals(reqMessage.isDisplayed(), true,"Failed to Validate: * not displayed");
		assertEquals(reqMessage.getText(), "*","Failed to Validate");
		assertEquals(reqMessage.getCssValue("color"),"rgba(255, 0, 0, 1)","Failed to Validate: Color should be Red");
		accountsLogger.info("Validate : Subject Required "+reqMessage.getText());
		test_steps.add("Validate : Subject Required "+reqMessage.getText());
		
		return test_steps;
	}
	
	public ArrayList<String> validateReqFilledStartDate(WebDriver driver) throws InterruptedException{
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		elements_RoomMaintenance.SelectStart_EndDate.get(0).click();
		Wait.wait2Second();
		String getDate = elements_RoomMaintenance.GetActiveDate.getText();
		elements_RoomMaintenance.GetActiveDate.click();
		accountsLogger.info("Validate : Enter Today Date as Start Date "+getDate);
		test_steps.add("Validate : Enter Today Date as Start Date "+getDate);
		
		return test_steps;
	}
	
	public ArrayList<String> validateReqFilledEndDate(WebDriver driver,String Night) throws InterruptedException{
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		
	
		elements_RoomMaintenance.SelectStart_EndDate.get(1).click();
		Wait.wait2Second();
		String ActiveDate = elements_RoomMaintenance.GetActiveDate.getText();
		int date = Integer.parseInt(ActiveDate);
		if (date == 28 || date == 30 || date == 31) {
			date = 1;
		} else {
			date = date + 1;
		}

		Wait.wait2Second();

		elements_RoomMaintenance.EnterRoomMaintenance_Night.clear();
		elements_RoomMaintenance.EnterRoomMaintenance_Night.sendKeys(Night);
		accountsLogger.info("Validate : Enter End Date ");
		test_steps.add("Validate : Enter End Date ");
		accountsLogger.info("Validate : Enter Night : "+Night);
		test_steps.add("Validate : Enter Night : "+Night);
		return test_steps;
	}
	
	public ArrayList<String> validateReqFilledSubject(WebDriver driver,String Subject){
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		elements_RoomMaintenance.EnterRoomMaintenance_Subject.sendKeys(Subject);
		accountsLogger.info("Validate : Enter Subject : "+Subject);
		test_steps.add("Validate : Enter Subject : "+Subject);
		return test_steps;
	}
	
	public String validateReqRoomAss(WebDriver driver,ArrayList<String> test_Steps) {
		
		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		elements_RoomMaintenance.RoomMaintenance_AssociateRoom.click();
		accountsLogger.info("Associate Room button Clicked");
		test_Steps.add("Associate Room button Clicked");
		driver.switchTo().frame(0);
		Wait.waitUntilPresenceOfElementLocated(OR.RoomPicker_Popup, driver);
		assertEquals(elements_RoomMaintenance.RoomPicker_Popup.getText(), "Room Picker",
				"Room picker does not display");
		elements_RoomMaintenance.SelectRoom_OutofOrder.click();
		String roomclass = elements_RoomMaintenance.GetRoomClass.getText();
		String roomnum = elements_RoomMaintenance.GetRoomNumber.getText();
		elements_RoomMaintenance.Select_RoomButton.click();
		accountsLogger.info("Validate : Room Selected : "+roomnum);
		test_Steps.add("Validate : Room Selected : "+roomnum);
		accountsLogger.info("Validate : Room Class : "+roomclass);
		test_Steps.add("Validate : Room Class : "+roomclass);
		return roomclass + " : " + roomnum;
	}
	
	public ArrayList<String> validateReqDatesBeforeRoomAss(WebDriver driver) {
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		elements_RoomMaintenance.RoomMaintenance_AssociateRoom.click();
		accountsLogger.info("Associate Room button Clicked");
		test_steps.add("Associate Room button Clicked");
		WebElement reqMessage=driver.findElement(By.xpath("//*[@id='MainContent_lblErrorMessage']"));
		assertEquals(reqMessage.isDisplayed(), true,"Failed to Validate: * not displayed");
		assertEquals(reqMessage.getText(), "Please select Start Date and End Date","Failed to Validate Message");
		assertEquals(reqMessage.getCssValue("color"),"rgba(255, 0, 0, 1)","Failed to Validate: Color should be Red");
		accountsLogger.info("Validate : "+reqMessage.getText());
		test_steps.add("Validate : "+reqMessage.getText());
		return test_steps;
	}
	
	public String createNewRoomOutNegetive(WebDriver driver,String Night, String Subjet, ArrayList<String> test_steps) throws InterruptedException, ParseException{
		
		
		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		elements_RoomMaintenance.NewRoomMaintenance_Button.click();
		accountsLogger.info("New Room Maintenance Button Clicked");
		test_steps.add("New Room Maintenance Button Clicked");
		Wait.wait1Second();
		elements_RoomMaintenance.SelectStart_EndDate.get(0).click();
		Wait.wait1Second();
		
		String currday=getCurrentDay();
		String nextday=getNextDay();
		if(Integer.parseInt(nextday)<Integer.parseInt(currday)) {
			driver.findElement(By.xpath("//i[contains(@class,'icon-arrow-right')]")).click();
		}
		String path="//tr/td[text()='"+nextday+"'][@class='day']";
		driver.findElement(By.xpath(path)).click();
		accountsLogger.info("Date Selected : " + nextday);
		test_steps.add("Date Selected : " + nextday);
		
		elements_RoomMaintenance.EnterRoomMaintenance_Night.clear();
		elements_RoomMaintenance.EnterRoomMaintenance_Night.sendKeys(Night);
		accountsLogger.info("Night Entered : " + Night);
		test_steps.add("Night Entered : " + Night);
		elements_RoomMaintenance.EnterRoomMaintenance_Subject.sendKeys(Subjet);
		accountsLogger.info("Subject Entered : " + Subjet);
		test_steps.add("Subject Entered : " + Subjet);
		elements_RoomMaintenance.RoomMaintenance_AssociateRoom.click();
		accountsLogger.info("Room Maintenance AssociateRoom Button Clicked");
		test_steps.add("Room Maintenance AssociateRoom Button Clicked");
		driver.switchTo().frame(0);
		Wait.waitUntilPresenceOfElementLocated(OR.RoomPicker_Popup, driver);
		assertEquals(elements_RoomMaintenance.RoomPicker_Popup.getText(), "Room Picker",
				"Room picker does not display");
		accountsLogger.info("Room Picker Displayed");
		test_steps.add("Room Picker Displayed");
		elements_RoomMaintenance.SelectRoom_OutofOrder.click();
		
		String roomclass = elements_RoomMaintenance.GetRoomClass.getText();
		String roomnum = elements_RoomMaintenance.GetRoomNumber.getText();
		accountsLogger.info("Room Selected : " + roomnum);
		test_steps.add("Room Selected : " + roomnum);
		accountsLogger.info("Room Class : " + roomclass);
		test_steps.add("Room Class : " + roomclass);
		
		elements_RoomMaintenance.Select_RoomButton.click();
		accountsLogger.info("Select Room Clicked");
		test_steps.add("Select Room Clicked");

		Wait.waitUntilPresenceOfElementLocated(OR.RoomMaintenance_Save, driver);
		elements_RoomMaintenance.RoomMaintenance_Save.click();
		accountsLogger.info("Save Clicked");
		test_steps.add("Save Clicked");
		Wait.waitUntilPresenceOfElementLocated(OR.DoneButton, driver);
		elements_RoomMaintenance.DoneButton.click();
		accountsLogger.info("Done Clicked");
		test_steps.add("Done Clicked");
		
		return roomclass + " : " + roomnum;

	}
	
	public String getNextDate(int NoOfDaysFromNow) {
		String pattern = "MMM dd, yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, NoOfDaysFromNow);
		String soutDate=simpleDateFormat.format(calendar.getTime()); 
		System.out.println(soutDate);
		return soutDate;
	}
	
	public String getCurrentDay() {
		final SimpleDateFormat format = new SimpleDateFormat("dd");
		  final Date date = new Date();
		  final Calendar calendar = Calendar.getInstance();
		  calendar.setTime(date);
		//  calendar.add(Calendar.DAY_OF_YEAR, 1);
		  return format.format(calendar.getTime()); 
	}
	
	public String getNextDay() throws ParseException {
		  final SimpleDateFormat format = new SimpleDateFormat("dd");
		  final Date date = new Date();
		  final Calendar calendar = Calendar.getInstance();
		  calendar.setTime(date);
		  calendar.add(Calendar.DAY_OF_YEAR, 1);
		  return format.format(calendar.getTime()); 
		}
	
	public ArrayList<String> validateAddNotes(WebDriver driver, String Subject) throws InterruptedException{
		ArrayList<String> test_steps=new ArrayList<String>();
		
		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		Wait.wait2Second();
		elements_RoomMaintenance.RoomMaintenance_AddNotes.click();
		accountsLogger.info("Room Maintenance Add Notes Clicked");
		test_steps.add("Room Maintenance Add Notes Clicked");
		//driver.manage().window().maximize();
		driver.switchTo().frame("dialog-body0");
		assertTrue(elements_RoomMaintenance.RoomMaintenance_NotesDetailPage.isDisplayed(),
				"Failed:Detail Page is not displayed");

		accountsLogger.info("Notes Page Displayed");
		test_steps.add("Notes Page Displayed");
		elements_RoomMaintenance.RoomMaintenance_Notes_SaveButton.click();
		accountsLogger.info("Room Maintenance Add Notes Save Clicked");
		test_steps.add("Room Maintenance Add Notes Save Clicked");
		Wait.wait2Second();
		
		WebElement reqMessageAllFeilds=driver.findElement(By.xpath("//*[@id='dispSumm']"));
		assertEquals(reqMessageAllFeilds.isDisplayed(), true,"Failed to Validate: * not displayed");
		assertEquals(reqMessageAllFeilds.getText(), "All fields marked with an * are required fields","Failed to Validate Message");
		assertEquals(reqMessageAllFeilds.getCssValue("color"),"rgba(255, 0, 0, 1)","Failed to Validate: Color should be Red");
		accountsLogger.info("Validate : "+reqMessageAllFeilds.getText());
		test_steps.add("Validate : "+reqMessageAllFeilds.getText());
		
		WebElement reqMessage=driver.findElement(By.xpath("//*[@id='reqNoteSubject']"));
		assertEquals(reqMessage.isDisplayed(), true,"Failed to Validate: * not displayed");
		assertEquals(reqMessage.getText(), "*","Failed to Validate");
		assertEquals(reqMessage.getCssValue("color"),"rgba(255, 0, 0, 1)","Failed to Validate: Color should be Red");
		accountsLogger.info("Validate : Note Subject Required "+reqMessage.getText());
		test_steps.add("Validate : Note Subject Required "+reqMessage.getText());
		
		elements_RoomMaintenance.RoomMaintenance_Notes_Subject.sendKeys(Subject);
		
		elements_RoomMaintenance.RoomMaintenance_Notes_SaveButton.click();
		accountsLogger.info("Room Maintenance Add Notes Save Clicked");
		test_steps.add("Room Maintenance Add Notes Save Clicked");
		accountsLogger.info("Notes Added Successfully");
		test_steps.add("Notes Added Successfully");
		Wait.wait2Second();
		driver.switchTo().defaultContent();
		assertTrue(elements_RoomMaintenance.RoomMaintenance_DetailPage.isDisplayed(),
				"Failed:Detail Page not displayed");
		
		return test_steps;
	}
	
	public void createNewRoomOutNegetive(WebDriver driver, String Night, String Subjet, String RoomNumber,
			ArrayList<String> test_steps) throws InterruptedException, ParseException {

		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		elements_RoomMaintenance.NewRoomMaintenance_Button.click();
		Wait.wait2Second();
		accountsLogger.info("New Room Maintenance Clicked");
		test_steps.add("New Room Maintenance Clicked");
		elements_RoomMaintenance.SelectStart_EndDate.get(0).click();
		Wait.wait1Second();
		
		String currday=getCurrentDay();
		String nextday=getNextDay();
		if(Integer.parseInt(nextday)<Integer.parseInt(currday)) {
			driver.findElement(By.xpath("//i[contains(@class,'icon-arrow-right')]")).click();
		}
		String path="//tr/td[text()='"+nextday+"'][@class='day']";
		driver.findElement(By.xpath(path)).click();
		accountsLogger.info("Date Selected : " + nextday);
		test_steps.add("Date Selected : " + nextday);
		

		elements_RoomMaintenance.EnterRoomMaintenance_Night.clear();
		elements_RoomMaintenance.EnterRoomMaintenance_Night.sendKeys(Night);
		elements_RoomMaintenance.EnterRoomMaintenance_Subject.sendKeys(Subjet);

		accountsLogger.info("Required Feilds Filled");
		test_steps.add("Required Feilds Filled");

		elements_RoomMaintenance.RoomMaintenance_AssociateRoom.click();

		driver.switchTo().frame(0);
		Wait.waitUntilPresenceOfElementLocated(OR.RoomPicker_Popup, driver);
		assertEquals(elements_RoomMaintenance.RoomPicker_Popup.getText(), "Room Picker",
				"Room picker does not display");
		accountsLogger.info("Room Picker Dialoge is Displayed");
		test_steps.add("Room Picker Dialoge is Displayed");

		new Select(driver.findElement(By.xpath("//*[@id='ddlItemsPerPage']"))).selectByIndex(3);

		String checkBoxByRoomNumber = "//tr/td[4][contains(text(),'" + RoomNumber
				+ "')]/preceding-sibling::td/span/input[contains(@type,'checkbox')]";

		System.out.println(checkBoxByRoomNumber);

		WebElement checkBoxOfRoom = driver.findElement(By.xpath(checkBoxByRoomNumber));
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("arguments[0].scrollIntoView()", checkBoxOfRoom);
		checkBoxOfRoom.click();
		elements_RoomMaintenance.Select_RoomButton.click();

		accountsLogger.info("Selected Room # " + RoomNumber);
		test_steps.add("Selected Room # " + RoomNumber);

		Wait.waitUntilPresenceOfElementLocated(OR.RoomMaintenance_Save, driver);
		elements_RoomMaintenance.RoomMaintenance_Save.click();
		Wait.waitUntilPresenceOfElementLocated(OR.DoneButton, driver);
		elements_RoomMaintenance.DoneButton.click();

		accountsLogger.info("Room Item Saved and Done Clicked");
		test_steps.add("Room Item Saved and Done Clicked");
	}

	public void delete_RoomItem(WebDriver driver, String RoomClass,String Number) {
		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		String path="//table[@id='MainContent_dgMaintenanceList']//tr//td[contains(text(),'"+RoomClass+" : "+Number+"')]//following-sibling::td/span/input";
		System.out.println(path);
		driver.findElement(By.xpath(path)).click();
		elements_RoomMaintenance.DeleteButton.click();
	}
	
	public String CreateNewRoomOut(WebDriver driver, String Night, String Subjet,String RoomClass) throws InterruptedException {

		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		elements_RoomMaintenance.NewRoomMaintenance_Button.click();
		Wait.wait2Second();
		elements_RoomMaintenance.SelectStart_EndDate.get(0).click();
		Wait.wait2Second();
		String getDate = elements_RoomMaintenance.GetActiveDate.getText();
		elements_RoomMaintenance.GetActiveDate.click();
		elements_RoomMaintenance.SelectStart_EndDate.get(1).click();
		Wait.wait2Second();
		String ActiveDate = elements_RoomMaintenance.GetActiveDate.getText();
		int date = Integer.parseInt(ActiveDate);
		if (date == 28 || date == 30 || date == 31) {
			date = 1;
		} else {
			date = date + 1;
		}

		Wait.wait2Second();

		elements_RoomMaintenance.EnterRoomMaintenance_Night.clear();
		elements_RoomMaintenance.EnterRoomMaintenance_Night.sendKeys(Night);
		elements_RoomMaintenance.EnterRoomMaintenance_Subject.sendKeys(Subjet);
		elements_RoomMaintenance.RoomMaintenance_AssociateRoom.click();

		driver.switchTo().frame(0);
		Wait.waitUntilPresenceOfElementLocated(OR.RoomPicker_Popup, driver);
		assertEquals(elements_RoomMaintenance.RoomPicker_Popup.getText(), "Room Picker",
				"Room picker does not display");
		new Select(driver.findElement(By.xpath("//*[@id='drpRoomClassList']"))).selectByVisibleText(RoomClass);
		Wait.wait2Second();
		driver.findElement(By.xpath("//*[@id='btnGo']")).click();
		Wait.wait2Second();
		elements_RoomMaintenance.SelectRoom_OutofOrder.click();
		String roomclass = elements_RoomMaintenance.GetRoomClass.getText();
		String roomnum = elements_RoomMaintenance.GetRoomNumber.getText();
		elements_RoomMaintenance.Select_RoomButton.click();

		Wait.waitUntilPresenceOfElementLocated(OR.RoomMaintenance_Save, driver);
		elements_RoomMaintenance.RoomMaintenance_Save.click();
		Wait.waitUntilPresenceOfElementLocated(OR.DoneButton, driver);
		elements_RoomMaintenance.DoneButton.click();

		return roomnum;

	}
	
	public String getRoomMaintanceNight(WebDriver driver,ArrayList<String> test_steps)
	{
		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		// Verified No Show Payment Page Card No
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		String nights = (String) executor.executeScript("return arguments[0].value",
				elements_RoomMaintenance.RoomMaintenance_Nights);
		accountsLogger.info("Room Maintence Night Is: " +nights);
		test_steps.add("Room Maintence Night Is: <b> " +nights +" </b>");
		return nights;
	}
	
	public String getRoomMaintanceStartDate(WebDriver driver,ArrayList<String> test_steps)
	{
		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		// Verified No Show Payment Page Card No
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		String startDate = (String) executor.executeScript("return arguments[0].value",
				elements_RoomMaintenance.RoomMaintenance_StartDate);
		accountsLogger.info("Room Maintence Start Date Is: " +startDate);
		test_steps.add("Room Maintence Start Date Is: <b> " +startDate +" </b>");
		return startDate;
	}
	
	public String getRoomMaintanceEndDate(WebDriver driver,ArrayList<String> test_steps)
	{
		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		// Verified No Show Payment Page Card No
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		String endDate = (String) executor.executeScript("return arguments[0].value",
				elements_RoomMaintenance.RoomMaintenance_EndDate);
		accountsLogger.info("Room Maintence End Date Is: " +endDate);
		test_steps.add("Room Maintence  End Is: <b> " +endDate +" </b>");
		return endDate;
	}
	
	public ArrayList<String> createNewRoomOutOfOrder(WebDriver driver, String Subjet, String RoomNumber,String RoomClass,
			ArrayList<String> test_steps, String Detail) throws InterruptedException {

		ArrayList<String> nights = new ArrayList<>();
		
		Elements_RoomMaintenance elements_RoomMaintenance = new Elements_RoomMaintenance(driver);
		elements_RoomMaintenance.NewRoomMaintenance_Button.click();
		accountsLogger.info("New Room Maintenance Clicked");
		test_steps.add("New Room Maintenance Clicked");
		Wait.explicit_wait_visibilityof_webelement(elements_RoomMaintenance.SelectStart_EndDate.get(0), driver);
		Utility.ScrollToElement(elements_RoomMaintenance.SelectStart_EndDate.get(0), driver);
		elements_RoomMaintenance.SelectStart_EndDate.get(0).click();
		accountsLogger.info("Click Start Date Calendar Icon ");
		test_steps.add("Click Start Date Calendar Icon " );
		String title=elements_RoomMaintenance.GetActiveDate.getAttribute("title");
		String ActiveDate = elements_RoomMaintenance.GetActiveDate.getText();	
		int dateActive = Integer.parseInt(ActiveDate);
		
		String startDate= Utility.parseDate(title, "EEEEE, MMM dd, yyyy", "dd/MM/yyyy");
		String monthYearForStartDate = Utility.get_MonthYear(startDate);
		String dayForStartDate = String.valueOf(dateActive-1);
		System.out.println(monthYearForStartDate);
		String header = null, headerText = null, next = null, date = null;
		for (int i = 1; i <= 12; i++) {
			header = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[2]";
			headerText = driver.findElement(By.xpath(header)).getText();
			if (headerText.equalsIgnoreCase(monthYearForStartDate)) {
				date = "//td[contains(@class,'day') and text()='" + dayForStartDate + "']";
				int size = driver.findElements(By.xpath(date)).size();
				
			//	driver.findElement(By.xpath(date)).click();
				for (int k = 1; k <= size; k++) {
					date = "(//td[contains(@class,'day') and text()='" + dayForStartDate + "'])[" + k + "]";
					String classText = driver.findElement(By.xpath(date)).getAttribute("title");
					if (classText.contains(dayForStartDate))
						{
						driver.findElement(By.xpath(date)).click();						
						break;
						}
					
				}
				break;
			} else {
				next = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[3]/div";
				Wait.WaitForElement(driver, next);
				driver.findElement(By.xpath(next)).click();
			}
		}
		
	/*	date = "//td[contains(@class,'old')]";
		Utility.ScrollToElement(driver.findElement(By.xpath(date)), driver);
		driver.findElement(By.xpath(date)).click();		*/
		
		String StartDate=getRoomMaintanceStartDate(driver,test_steps);

		accountsLogger.info("Select Start Date : " +StartDate);
		test_steps.add("Select Start Date: " +StartDate);
		Wait.explicit_wait_visibilityof_webelement(elements_RoomMaintenance.SelectStart_EndDate.get(1), driver);
		Utility.ScrollToElement(elements_RoomMaintenance.SelectStart_EndDate.get(1), driver);
		elements_RoomMaintenance.SelectStart_EndDate.get(1).click();		
		accountsLogger.info("Click End Date Calendar Icon ");
		test_steps.add("Click Start End Calendar Icon " );
		String monthYear=monthYearForStartDate;
		String day = String.valueOf(dateActive+2);
		System.out.println(monthYear);
		String header1 = null, headerText1 = null, next1 = null, date1 = null;
		for (int i = 1; i <= 12; i++) {
			header1 = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[2]";
			headerText1 = driver.findElement(By.xpath(header1)).getText();
			if (headerText1.equalsIgnoreCase(monthYear)) {
				date1 = "//td[contains(@class,'day') and text()='" + day + "']";
				int size = driver.findElements(By.xpath(date1)).size();
				for (int k = 1; k <= size; k++) {
					date1 = "(//td[contains(@class,'day') and text()='" + day + "'])[" + k + "]";
					String classText = driver.findElement(By.xpath(date1)).getAttribute("class");
					if (!classText.contains("old")) {
						driver.findElement(By.xpath(date1)).click();	
						
						break;
					}
				}
				break;
			} else {
				next1 = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[3]/div";
				driver.findElement(By.xpath(next1)).click();
			}
		}
        
		
		
		String EndDate=getRoomMaintanceEndDate(driver,test_steps);
		String Night=getRoomMaintanceNight(driver,test_steps);
		nights.add(StartDate);
		nights.add(EndDate);
		nights.add(Night);
		
		accountsLogger.info("Select End Date : " +EndDate);
		test_steps.add("Select End Date: " +EndDate);
		
		elements_RoomMaintenance.EnterRoomMaintenance_Subject.sendKeys(Subjet);
		accountsLogger.info("Input Subject : " +Subjet);
		test_steps.add("Input Subject : " +Subjet);

		elements_RoomMaintenance.RoomMaintenance_AddNote.click();
		accountsLogger.info("Click Add Button");
		test_steps.add("Click Add Button");

		driver.switchTo().frame(0);
		elements_RoomMaintenance.RoomMaintenance_AddNoteSubject.sendKeys(Subjet);
		elements_RoomMaintenance.RoomMaintenance_AddNoteDetail.sendKeys(Detail);
		elements_RoomMaintenance.RoomMaintenance_AddNoteSaveButton.click();
		
		accountsLogger.info("Added Notes :" +Subjet +" " +Detail);
		test_steps.add("Added Notes : Subject <b>" +Subjet +" </b> Detail  <b>" +Detail +" </b>");

		driver.switchTo().defaultContent();
		accountsLogger.info("Switch to Default Content");
		Wait.WaitForElement(driver, OR.RoomMaintenance_AssociateRoom);
		Wait.waitForElementToBeClickable(By.xpath(OR.RoomMaintenance_AssociateRoom), driver);
		elements_RoomMaintenance.RoomMaintenance_AssociateRoom.click();
		driver.switchTo().frame(0);
		Wait.waitUntilPresenceOfElementLocated(OR.RoomPicker_Popup, driver);
		assertEquals(elements_RoomMaintenance.RoomPicker_Popup.getText(), "Room Picker",
				"Room picker does not display");
		accountsLogger.info("Room Picker Dialoge is Displayed");
		test_steps.add("Room Picker Dialoge is Displayed");

		new Select(driver.findElement(By.xpath("//*[@id='drpRoomClassList']"))).selectByVisibleText(RoomClass);
		accountsLogger.info("Select Room Class: "+RoomClass);
		test_steps.add("Select Room Class: <b>"+RoomClass+"</b>");
		Wait.WaitForElement(driver, "//*[@id='btnGo']");
		driver.findElement(By.xpath("//*[@id='btnGo']")).click();
		accountsLogger.info("Click Go Button");
		test_steps.add("Click Go Button");
		Wait.waitUntilPresenceOfElementLocated("//*[@id='ddlItemsPerPage']", driver);
		new Select(driver.findElement(By.xpath("//*[@id='ddlItemsPerPage']"))).selectByIndex(3);
		String checkBoxByRoomNumber = "//tr/td[4][contains(text(),'" + RoomNumber
				+ "')]/preceding-sibling::td/span/input[contains(@type,'checkbox')]";
		Wait.WaitForElement(driver, checkBoxByRoomNumber);
		System.out.println(checkBoxByRoomNumber);

		WebElement checkBoxOfRoom = driver.findElement(By.xpath(checkBoxByRoomNumber));
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("arguments[0].scrollIntoView()", checkBoxOfRoom);
		checkBoxOfRoom.click();
		accountsLogger.info("Selected Room # " + RoomNumber);
		test_steps.add("Selected Room # " + RoomNumber);
		Utility.ScrollToElement(elements_RoomMaintenance.Select_RoomButton, driver);
		Wait.explicit_wait_elementToBeClickable(elements_RoomMaintenance.Select_RoomButton, driver);
		
		elements_RoomMaintenance.Select_RoomButton.click();
		accountsLogger.info("Click Select Button ");
		test_steps.add("Click Select Button");
	/*	Wait.WaitForElement(driver, OR.RoomPicker_Popup);
		try{
			if(elements_RoomMaintenance.RoomPicker_Popup.isDisplayed()) {
				if(!checkBoxOfRoom.isSelected()) {
					jse.executeScript("arguments[0].scrollIntoView()", checkBoxOfRoom);
					checkBoxOfRoom.click();
					Utility.ScrollToElement(elements_RoomMaintenance.Select_RoomButton, driver);
					elements_RoomMaintenance.Select_RoomButton.click();
				}
			}
		}catch (Exception e) {
			System.out.println("Room Picker Gone");
		}*/
		

	/*	Wait.waitUntilPresenceOfElementLocated(OR.RoomMaintenance_Save, driver);
		elements_RoomMaintenance.RoomMaintenance_Save.click();*/
		Wait.waitUntilPresenceOfElementLocated(OR.DoneButton, driver);
		elements_RoomMaintenance.DoneButton.click();

		accountsLogger.info("Done Clicked");
		test_steps.add("Done Clicked");
		return nights;
		
	}

	
	public  List<LocalDate> getDatesAsPerNights(WebDriver driver,ArrayList<String> test_steps,String StartDate, String EndDate, String formate)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formate);
		 LocalDate Start=LocalDate.parse(StartDate, formatter);
		 LocalDate End=LocalDate.parse(EndDate,formatter);
		 List<LocalDate> listofDates = new ArrayList<LocalDate>();
		    /*for (LocalDate date = Start.plusDays(1); !date.isAfter(End.minusDays(1)); date = date.plusDays(1)) {
			        listofDates.add(date);
			       
			    }*/
		 for (LocalDate date = Start; !date.isAfter(End.minusDays(1)); date = date.plusDays(1)) {
		        listofDates.add(date);
		       
		    }
		    accountsLogger.info("Dates as per Nights " + listofDates);
			test_steps.add("Dates as per Nights <b>" + listofDates +"</b>");
		    return listofDates;
	
	}
	
	public List<String> getDateOnlyAsperNights(WebDriver driver,ArrayList<String> test_steps, List<LocalDate> Date,String formate, String changedFormat) throws ParseException
	{
		 List<String> getDate=new ArrayList<String>();
		for(LocalDate dates: Date)
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formate);
			String formattedString = dates.format(formatter);
			getDate.add(Utility.GetDate(driver, test_steps, formattedString, formate,changedFormat));
		}
		
		 accountsLogger.info("Date as per Nights " + getDate);
			test_steps.add("Date as per Nights <b>" + getDate +"</b>");
		return getDate;
			
	}
	
	public List<String> getDayAsperNights(WebDriver driver,ArrayList<String> test_steps, List<LocalDate> Date,String formate, String changeFormat) throws ParseException
	{
		 List<String> getDay=new ArrayList<String>();
		for(LocalDate dates: Date)
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formate);
			String formattedString = dates.format(formatter);
			getDay.add(Utility.GetDayOfDate(driver, test_steps, formattedString, formate,changeFormat));
		}
		
		 accountsLogger.info("Day as per Nights " + getDay);
			test_steps.add("Day as per Nights <b>" + getDay +"</b>");
		return getDay;
			
	}
	
}