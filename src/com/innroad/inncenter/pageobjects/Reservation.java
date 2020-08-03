package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_All_Payments;
import com.innroad.inncenter.webelements.Elements_FolioLineItemsVoid;
import com.innroad.inncenter.webelements.Elements_MovieFolio;
import com.innroad.inncenter.webelements.Elements_RateQuote;
import com.innroad.inncenter.webelements.Elements_Reservation;
import com.innroad.inncenter.webelements.Elements_TapeChart;
import com.innroad.inncenter.webelements.Elements_Reservation_SearchPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.IExtentTestClass;
import com.relevantcodes.extentreports.LogStatus;

public class Reservation {

	public static String ReservationConfirmation;
	public static Logger reservationLogger = Logger.getLogger("Reservation");
	public static boolean checkinpolicy = false;
	public static boolean flag = false;
	private String RoomNumber = "";
	public static String nameGuest;
	public static String unassignedResCount;
	public static String unassignedResCountAfterCreatingRes;
	public static String LastName;
	public static String getRoomclassName_status;
	public static String roomClassNom;
	public static String roomClass;
	public static String nameGuest1;
	public static String lastName;
	ArrayList<String> test_steps = new ArrayList<>();

	private ArrayList<String> searchOpeartionList = new ArrayList<String>();
	private ArrayList<String> cDateList = new ArrayList<String>();
	private ArrayList<String> nextDateList = new ArrayList<String>();
	private ArrayList<String> previousDateList = new ArrayList<String>();
	private ArrayList<String> allDeparturesRecordsList = new ArrayList<String>();
	public static ArrayList<String> beforeReservation_AllDepartures_Records = new ArrayList<String>();
	public static ArrayList<String> AfterReservation_AllDepartures_Records = new ArrayList<String>();
	public static ArrayList<String> Before_Newreservation_Records = new ArrayList<>();
	public static ArrayList<String> After_Newreservation_Records = new ArrayList<String>();
	public static ArrayList<String> ReservationList = new ArrayList<String>();

	public void IPropertySelector(WebDriver driver, String PropertyName) throws InterruptedException {

		try {

			if (driver.findElement(By.xpath("//b[@role='presentation']")).isDisplayed()) {

				driver.findElement(By.id("s2id_autogen1")).click();
				// Wait.explicit_wait_xpath("select2-drop");
				Wait.wait2Second();
				driver.findElements(By.xpath("//div[@id='select2-drop']//ul//li//div")).get(1).click();
				Wait.wait2Second();
			} else {
				// reservationLogger.info("Single Property");
				reservationLogger.info("Single Property Client");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Double get_FolioBalance(WebDriver driver) throws InterruptedException {

		Wait.wait5Second();
		String Balance = driver
				.findElement(By
						.xpath("//label[contains(text(),'Balance: ')]/following-sibling::span[@class='pamt']/span[@class='pamt']"))
				.getText();
		Balance = Balance.replace("$", "");

		Double d = Double.parseDouble(Balance);
		reservationLogger.info("Folio First Balance : " + d);
		Wait.wait5Second();
		return d;

	}

	public void marketingInfo(WebDriver driver, ArrayList test_steps, String MarketSegment, String Referral,
			String Travel_Agent, String ExtReservation) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Reservation_market_Segment, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", ReservationPage.Reservation_market_Segment);
		new Select(ReservationPage.Reservation_market_Segment).selectByVisibleText(MarketSegment);
		reservationLogger.info("Selected Market Segment");
		test_steps.add("Select Reservation market segment : " + MarketSegment);
		new Select(ReservationPage.Reservation_Referral).selectByVisibleText(Referral);
		reservationLogger.info("Selected Referral");
		test_steps.add("Select Reservation referral : " + Referral);
		try {
			ReservationPage.Enter_Travel_Agent.sendKeys(Travel_Agent);
			reservationLogger.info("Entered Travel_Agent Info");
			test_steps.add("Entered Travel_Agent : " + Travel_Agent);
		} catch (Exception e) {

		}

		// ReservationPage.Enter_Ext_Reservation.sendKeys(ExtReservation);
		// reservationLogger.info("Entered ExtReservation");
	}

	public ArrayList<String> clickNewReservationButton(WebDriver driver, ArrayList<String> TestSteps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.New_Reservation_Button);
		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.New_Reservation_Button, driver);

		ReservationPage.New_Reservation_Button.click();
		reservationLogger.info("Click on New Reservation button");
		TestSteps.add("Click on New Reservation button");
		//try {
		//	Wait.waitForElementToBeGone(driver, ReservationPage.New_Reservation_Button, 50);
			Wait.WaitForElement(driver, OR.NewReservationTab);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.NewReservationTab, driver);
			Wait.explicit_wait_elementToBeClickable(ReservationPage.NewReservationTab, driver);
		/*} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.New_Reservation_Button, driver);
			ReservationPage.New_Reservation_Button.click();
			reservationLogger.info("Again Click on New Reservation button");
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Reservation_market_Segment, driver);
		}
*/
		return TestSteps;

	}

	public void clickNewReservationButtons(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		/*
		 * Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.
		 * New_Reservation_Button,driver); JavascriptExecutor jse =
		 * (JavascriptExecutor) driver;
		 * jse.executeScript("arguments[0].click();",
		 * ReservationPage.New_Reservation_Button);
		 * reservationLogger.info("Clicked on NewReservationButton");
		 * 
		 * try { Wait.waitForElementToBeGone(driver,
		 * ReservationPage.New_Reservation_Button, 30);
		 * Wait.explicit_wait_visibilityof_webelement(ReservationPage.
		 * Reservation_market_Segment,driver); } catch (Exception e) {
		 * Wait.explicit_wait_visibilityof_webelement(ReservationPage.
		 * New_Reservation_Button,driver);
		 * ReservationPage.New_Reservation_Button.click();
		 * reservationLogger.info("Again Clicked on New Reservation Button");
		 * Wait.explicit_wait_visibilityof_webelement(ReservationPage.
		 * Reservation_market_Segment,driver); }
		 */

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Reservation_market_Segment, driver);
	}

	public ArrayList<String> contactInformation(WebDriver driver, String saluation, String FirstName, String LastName,
			String Address, String Line1, String Line2, String Line3, String City, String Country, String State,
			String Postalcode, String Phonenumber, String alternativenumber, String Email, String Account,
			String IsTaxExempt, String TaxEmptext, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.GuestProfile_Checbox, driver);
		Utility.ScrollToElement(ReservationPage.GuestProfile_Checbox, driver);

		Wait.WaitForElement(driver, OR.Reservation_CreateGuestProfile);

		/*
		 * if (ReservationPage.GuestProfile_Checbox.isSelected()) {
		 * ReservationPage.GuestProfile_Checbox.click(); }
		 */

		if (ReservationPage.Reservation_CreateGuestProfile.isSelected()) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ReservationPage.Reservation_CreateGuestProfile);
			Wait.wait2Second();
			ReservationPage.Reservation_CreateGuestProfile.click();
		}

		new Select(ReservationPage.Select_Saluation).selectByVisibleText(saluation);
		ReservationPage.Enter_First_Name.clear();
		ReservationPage.Enter_Last_Name.clear();
		ReservationPage.Enter_Address_Search.clear();
		ReservationPage.Enter_Line1.clear();
		ReservationPage.Enter_Line2.clear();
		ReservationPage.Enter_Line3.clear();
		ReservationPage.Enter_City.clear();
		ReservationPage.Enter_Postal_Code.clear();
		ReservationPage.Enter_Phone_Number.clear();
		ReservationPage.Enter_Alt_Phn_number.clear();
		ReservationPage.Enter_email.clear();
		ReservationPage.Enter_First_Name.sendKeys(FirstName);
		ReservationPage.Enter_Last_Name.sendKeys(LastName);
		ReservationPage.Enter_Address_Search.sendKeys(Address);
		ReservationPage.Enter_Line1.sendKeys(Line1);
		ReservationPage.Enter_Line2.sendKeys(Line2);
		ReservationPage.Enter_Line3.sendKeys(Line3);
		ReservationPage.Enter_City.sendKeys(City);
		new Select(ReservationPage.Select_Country).selectByVisibleText(Country);
		new Select(ReservationPage.Select_State).selectByVisibleText(State);

		ReservationPage.Enter_Postal_Code.clear();
		ReservationPage.Enter_Postal_Code.sendKeys(Postalcode);
		reservationLogger.info("Phone Number " + Phonenumber);
		ReservationPage.Enter_Phone_Number.clear();
		ReservationPage.Enter_Phone_Number.sendKeys(Phonenumber);

		reservationLogger.info("Alternative Pone Number " + alternativenumber);
		ReservationPage.Enter_Alt_Phn_number.clear();
		ReservationPage.Enter_Alt_Phn_number.sendKeys(alternativenumber);

		reservationLogger.info("Email " + Email);
		ReservationPage.Enter_email.clear();
		ReservationPage.Enter_email.sendKeys(Email);
		reservationLogger.info("Entered required contact information");

		try {
			String acc = "//label[text()='Account:']/following-sibling::div/div/a/span";
			String acctext = driver.findElement(By.xpath(acc)).getText();
			System.out.println("acctext " + acctext);
			if (acctext.equalsIgnoreCase("") || acctext.equalsIgnoreCase(null)) {
				ReservationPage.Enter_Account.sendKeys(Account);
			}

		} catch (Exception e) {
			System.out.println("Account already Exist");
		}

		if (IsTaxExempt.equals("Yes")) {
			if (ReservationPage.Check_IsTaxExempt.isSelected()) {
				ReservationPage.Enter_TaxExemptId.sendKeys(TaxEmptext);
				reservationLogger.info("Entered TaxExcemptID");
			} else {
				ReservationPage.Check_IsTaxExempt.click();
				reservationLogger.info("Clicked on TaxExcempt checkbox");
				ReservationPage.Enter_TaxExemptId.sendKeys(TaxEmptext);
				reservationLogger.info("Entered TaxExcemptID");
			}
		}
		test_steps.add("Contact Information Added");
		return test_steps;
	}

	public void billingInformation(WebDriver driver, String PaymentMethod, String AccountNumber, String ExpiryDate,
			String BillingNotes) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Select_Payment_Method, driver);
		Utility.ScrollToElement(ReservationPage.Select_Payment_Method, driver);
		new Select(ReservationPage.Select_Payment_Method).selectByVisibleText(PaymentMethod);
		if (PaymentMethod.equalsIgnoreCase("MC") || PaymentMethod.equalsIgnoreCase("Amex")
				|| PaymentMethod.equalsIgnoreCase("Discover") || PaymentMethod.equalsIgnoreCase("Visa")) {
			ReservationPage.Enter_Account_Number.clear();
			ReservationPage.Enter_Account_Number.sendKeys(AccountNumber);
			ReservationPage.Enter_Exp_Card.clear();
			ReservationPage.Enter_Exp_Card.sendKeys(ExpiryDate);
			ReservationPage.Enter_Billing_Note.sendKeys(BillingNotes);
			reservationLogger.info("Entered Billing Information");
		}
	}

	public String getBillingInformation(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		String Payment_Method = new Select(ReservationPage.Select_Payment_Method).getFirstSelectedOption().getText();
		reservationLogger.info("Selected Payment Method for Billing Information: " + Payment_Method);
		return Payment_Method;
	}

	public String getCopiedBillingInformation(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		String Payment_Method = new Select(ReservationPage.Select_Payment_Method_CopyRes).getFirstSelectedOption()
				.getText();
		reservationLogger.info("Payment Method of Copied Reservation: " + Payment_Method);
		return Payment_Method;
	}

	public void billingInformation_2(WebDriver driver, String PaymentMethod, String AccountNumber, String ExpiryDate,
			String BillingNotes) {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		new Select(ReservationPage.Select_Payment_Method_3).selectByVisibleText(PaymentMethod);
		if (PaymentMethod.equalsIgnoreCase("MC") || PaymentMethod.equalsIgnoreCase("Amex")
				|| PaymentMethod.equalsIgnoreCase("Discover") || PaymentMethod.equalsIgnoreCase("Visa")) {
			ReservationPage.Enter_Account_Number_3.sendKeys(AccountNumber);
			ReservationPage.Enter_Exp_Card_3.clear();
			ReservationPage.Enter_Exp_Card_3.sendKeys(ExpiryDate);
			ReservationPage.Enter_Billing_Note_3.sendKeys(BillingNotes);
			reservationLogger.info("Entered Billing Information");
		}
	}

	public void AddNotes(WebDriver driver, String subject, String details, String NoteType, String Notestatus,
			ArrayList<String> test_steps) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		jse.executeScript("arguments[0].click();", ReservationPage.Add_Note_Reservation);
		// ReservationPage.Add_Note_Reservation.click();
		Wait.WaitForElement(driver, OR.verify_Add_Notes_popup);
		// Wait.explicit_wait_xpath(OR.verify_Add_Notes_popup, driver);
		ReservationPage.Enter_Subject_Notes.sendKeys(subject);
		ReservationPage.Enter_Note_Details.sendKeys(details);
		new Select(ReservationPage.Select_Note_Type).selectByVisibleText(NoteType);
		if (ReservationPage.Check_Action_Required.isSelected()) {
			reservationLogger.info("Action Requited checkbox is already selected");
		} else {
			ReservationPage.Check_Action_Required.click();
		}
		new Select(ReservationPage.Select_Notes_Status).selectByVisibleText(Notestatus);

		ReservationPage.Click_Save_Note.click();
		if (ReservationPage.Verify_Added_Notes.isDisplayed()) {
			reservationLogger.info("Added Notes");
			test_steps.add("Added Notes");
		} else {
			reservationLogger.info("Failed to add notes");
			test_steps.add("Failed to add notes");
		}

	}

	public String getNotes(WebDriver driver) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Wait.WaitForElement(driver, OR.NotesSubject);
		String notesSubject = ReservationPage.NotesSubject.getText();
		reservationLogger.info("Notes: " + notesSubject);
		return notesSubject;
	}

	public String getCopiedNotes(WebDriver driver, String subject) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait3Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String xpath = "(//a[@title='" + subject + "'])[2]";
		WebElement element = driver.findElement(By.xpath(xpath));
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		jse.executeScript("arguments[0].scrollIntoView();", element);

		Wait.WaitForElement(driver, OR.getCopiedNotesSubject);
		// String
		// CopiedNotesSubject=ReservationPage.getCopiedNotesSubject.getText();
		String CopiedNotesSubject = element.getText();
		reservationLogger.info("Copied Notes" + CopiedNotesSubject);
		// Wait.wait3Second();

		/*
		 * if(CopiedNotesSubject.equals(Notes)) {
		 * 
		 * reservationLogger.info("Copied the Notes"); } else {
		 * reservationLogger.info("Note is not copied"); }
		 */
		return CopiedNotesSubject;
	}

	public void copiedNotesValidations(WebDriver driver, String Notes, String CopiedNotes,
			ArrayList<String> test_steps) {

		if (Notes.equals(CopiedNotes)) {

			reservationLogger.info("Reservation Notes is copied");
			test_steps.add("Reservation Notes is copied");
		} else {
			reservationLogger.info("Reservation Notes is not copied");
			test_steps.add("Reservation Notes is not copied");
		}
	}

	public void CopiedBillingInformationValidations(WebDriver driver, String BillingInfo_PaymentMethod,
			String CopiedBillingInfo_PaymentMethod, ArrayList<String> test_steps) {

		if (BillingInfo_PaymentMethod.equals(CopiedBillingInfo_PaymentMethod)) {

			reservationLogger.info("Copied the Billing Information");
			test_steps.add("Copied the Billing Information");
		} else {
			reservationLogger.info("Billing Information is not Copied");
			test_steps.add("Billing Information is not Copied");
		}
	}

	public ArrayList<String> roomAssignment_RoomClass(WebDriver driver, String PropertyName, String Nights,
			String Adults, String Children, String RatepromoCode, String CheckorUncheckAssign, String RoomClassName,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		ReservationPage.RoomAssignment_DateIcon.click();
		Wait.wait2Second();
		ReservationPage.SelectDate.click();
		ReservationPage.SearchRoomButton.click();
		Wait.wait2Second();
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClassName);
		test_steps.add("Select Room Class: " + RoomClassName);
		reservationLogger.info("Select Room Class: " + RoomClassName);
		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		Wait.wait2Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}
		return test_steps;

	}

	public ArrayList<String> roomAssignment_RoomClass(WebDriver driver, String RoomClassName,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.RoomAssignment_DateIcon, driver);
		ReservationPage.RoomAssignment_DateIcon.click();
		Wait.wait2Second();
		ReservationPage.SelectDate.click();
		ReservationPage.SearchRoomButton.click();
		Wait.wait2Second();
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClassName);
		test_steps.add("Select Room Class: " + RoomClassName);
		reservationLogger.info("Select Room Class: " + RoomClassName);
		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		Wait.wait2Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.Toaster_Message, driver);
		System.out.println(
				"Toaster Message appear : " + driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).getText());
		driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).click();
		Wait.wait2Second();
		return test_steps;

	}

	public void roomAssignment(WebDriver driver, String PropertyName, String Nights, String Adults, String Children,
			String RatepromoCode, String CheckorUncheckAssign, String RoomClassName, String RoomNumber)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-300)");
		Utility.ElementFinderUntillNotShow(By.xpath(OR.RoomAssignmentButton), driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		ReservationPage.RoomAssignment_DateIcon.click();
		Wait.wait2Second();
		ReservationPage.SelectDate.click();

		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}

		if (ReservationPage.Check_Assign_Room.isSelected()) {
			ReservationPage.Check_Assign_Room.click();
			ReservationPage.Click_Search.click();
		} else {
			if (CheckorUncheckAssign.equals("Yes")) {
				ReservationPage.Check_Assign_Room.click();
				ReservationPage.Click_Search.click();
			} else {
				ReservationPage.Click_Search.click();
			}
		}

		// ReservationPage.SearchRoomButton.click();
		Wait.wait2Second();
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClassName);

		reservationLogger.info("Select Room Class: " + RoomClassName);

		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}

		Wait.wait1Second();
		String roomnum = "(//tbody//select[contains(@data-bind,'RoomId')])/option";
		System.out.println(roomnum);
		int count = driver.findElements(By.xpath(roomnum)).size();
		Random random = new Random();
		int randomNumber = random.nextInt(count - 1) + 1;
		System.out.println("count : " + count);
		System.out.println("randomNumber : " + randomNumber);
		new Select(driver.findElement(By.xpath("(//tbody//select[contains(@data-bind,'RoomId')])")))
				.selectByIndex(randomNumber);

		// new Select(ReservationPage.SelectRoomNumbers).selectByIndex(1);
		Utility.roomIndex++;
		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		Wait.wait2Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}

		/*
		 * Elements_Reservation ReservationPage = new
		 * Elements_Reservation(driver);
		 * 
		 * try {
		 * 
		 * ReservationPage.Click_RoomPicker.click();
		 * reservationLogger.info("Clicked on RoomPicker button");
		 * Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp);
		 * Wait.wait3Second(); } catch (Exception e) {
		 * 
		 * }
		 * 
		 * 
		 * try { new Select(ReservationPage.Select_property_RoomAssign).
		 * selectByVisibleText(PropertyName); } catch(Exception e) { new
		 * Select(ReservationPage.Select_property_RoomAssign2).
		 * selectByVisibleText(PropertyName); }
		 * 
		 * 
		 * Wait.wait5Second(); ReservationPage.Click_Arrive_Datepicker.click();
		 * reservationLogger.info("Clicked on Arrival Datapicker");
		 * ReservationPage.Click_Today.click();
		 * reservationLogger.info("Selected today's date");
		 * 
		 * if (Nights.equals("")) { ReservationPage.Enter_Nigts.clear();
		 * ReservationPage.Enter_Nigts.sendKeys(Nights);
		 * reservationLogger.info("Entered No of Nights"); } else {
		 * 
		 * ReservationPage.Enter_Nigts.clear();
		 * ReservationPage.Enter_Nigts.sendKeys(Nights);
		 * reservationLogger.info("Entered No of Nights"); }
		 * ReservationPage.Enter_Adults.clear();
		 * ReservationPage.Enter_Adults.sendKeys("1");
		 * reservationLogger.info("Entered No of Adults");
		 * ReservationPage.Enter_Children.clear();
		 * 
		 * ReservationPage.Enter_Children.sendKeys("1");
		 * reservationLogger.info("Entered No of Children");
		 * 
		 * ReservationPage.Enter_Rate_Promocode.sendKeys(RatepromoCode);
		 * reservationLogger.info("Entered Promocode"); reservationLogger.
		 * info("Verifying AssignRooms checkbox is selected or not?"); if
		 * (ReservationPage.Check_Assign_Room.isSelected()) { //
		 * ReservationPage.Check_Assign_Room.click();
		 * reservationLogger.info("AssignRooms checkbox is selected");
		 * ReservationPage.Click_Search.click();
		 * reservationLogger.info("Clicked on Search button"); } else {
		 * reservationLogger.info(
		 * "AssignRooms checkbox is not selected. Hence, verifying test data value to select the AssignRooms checkbox"
		 * ); if (CheckorUncheckAssign.equals("Yes")) {
		 * ReservationPage.Check_Assign_Room.click();
		 * reservationLogger.info("AssignRooms checkbox is selected");
		 * ReservationPage.Click_Search.click();
		 * reservationLogger.info("Clicked on Search button"); } else {
		 * ReservationPage.Click_Search.click();
		 * reservationLogger.info("Clicked on Search button"); } } try {
		 * 
		 * new Select(ReservationPage.Select_Room_Class).selectByVisibleText(
		 * RoomClassName); reservationLogger.info("RoomClass is selected");
		 * String selectedOption = new
		 * Select(ReservationPage.Validating_UnAssgined_DDL).
		 * getFirstSelectedOption() .getText(); //
		 * reservationLogger.info("selectedOption " + selectedOption);
		 * reservationLogger.info("RoomNumber selectedOption is:  " +
		 * selectedOption); if (selectedOption.equals("--Select--")) { // new //
		 * Select(ReservationPage.Select_Room_Number).selectByVisibleText(
		 * RoomNumber); new
		 * Select(ReservationPage.Select_Room_Number).selectByIndex(1);
		 * reservationLogger.info("Selected first available room from the list"
		 * ); } else { // reservationLogger.info("Reservation is unassigned");
		 * reservationLogger.info("Reservation is unassigned"); } } catch
		 * (Exception e) {
		 * 
		 * }
		 * 
		 * try {
		 * 
		 * ReservationPage.Click_Select.click(); reservationLogger.
		 * info("Clicked on select button from Room Picker Popup"); } catch
		 * (Exception e) {
		 * 
		 * }
		 * 
		 * try { Wait.wait2Second();
		 * Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup);
		 * reservationLogger.
		 * info("Waiting for RulesBroken popup to be displayed"); //
		 * Wait.wait5Second(); if
		 * (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
		 * reservationLogger.info("RulesBroken popup is displayed");
		 * ReservationPage.Click_Continue_RuleBroken_Popup.click();
		 * reservationLogger.info("Clicked on RulesBroken popup continue button"
		 * ); } else { reservationLogger.
		 * info("Satisfied Rules hence, RulesBroken popup is not displayed"); }
		 * } catch (Exception e) {
		 * 
		 * }
		 * 
		 * try {
		 * 
		 * if (ReservationPage.Verify_Toaster_Container.isDisplayed()) { String
		 * getTotasterTitle = ReservationPage.Toaster_Title.getText(); String
		 * getToastermessage = ReservationPage.Toaster_Message.getText();
		 * reservationLogger.info("RoomAssignment Toaster Title: " +
		 * getTotasterTitle);
		 * reservationLogger.info("RoomAssignment Toaster Message: " +
		 * getToastermessage); Assert.assertEquals(getTotasterTitle,
		 * "Room assignment has changed.");
		 * Assert.assertEquals(getToastermessage,
		 * "Room assignment has changed."); reservationLogger.
		 * info("Verified Room Assignment toaster title and message"); }
		 * 
		 * Wait.wait2Second(); String getRoomclassName_status =
		 * ReservationPage.Get_RoomClass_Status.getText();
		 * reservationLogger.info("Room Class Status:" +
		 * getRoomclassName_status); Wait.wait15Second(); //
		 * Assert.assertEquals(getPropertyName,PropertyName); String
		 * getRoomclassName[] = getRoomclassName_status.split(" "); //
		 * Assert.assertEquals(getRoomclassName[0],RoomClassName );
		 * 
		 * if (CheckorUncheckAssign.equalsIgnoreCase("Yes")) {
		 * 
		 * } else { Assert.assertEquals(getRoomclassName[getRoomclassName.length
		 * - 1], "Unassigned"); } } catch (Exception e) {
		 * 
		 * }
		 */
		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);
		Wait.waitUntilPresenceOfElementLocatedByClassName(OR.Toaster_Message, driver);
		System.out.println(
				"Toaster Message appear : " + driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).getText());
		driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).click();
		Wait.wait2Second();
	}

	public void roomAssignment_AssignedReservation(WebDriver driver, String PropertyName, String Nights, String Adults,
			String Children, String RatepromoCode, String CheckorUncheckAssign, String RoomClassName, String RoomNumber)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-300)");
		Utility.ElementFinderUntillNotShow(By.xpath(OR.RoomAssignmentButton), driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		ReservationPage.RoomAssignment_DateIcon.click();
		Wait.wait2Second();
		ReservationPage.SelectDate.click();
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}

		ReservationPage.SearchRoomButton.click();
		Wait.wait2Second();
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClassName);

		reservationLogger.info("Select Room Class: " + RoomClassName);

		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}

		new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
		Utility.roomIndex++;
		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		Wait.wait2Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}

		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
		System.out.println("Toaster Message appear : " + driver.findElement(By.xpath(OR.Toaster_Message)).getText());
		driver.findElement(By.xpath(OR.Toaster_Message)).click();
		Wait.wait2Second();
	}

	public ArrayList<String> roomAssignment_1(WebDriver driver, String PropertyName, String Nights, String Adults,
			String Children, String RatepromoCode, String CheckorUncheckAssign, String RoomClassName, String RoomNumber,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		ReservationPage.RoomAssignment_DateIcon.click();
		ReservationPage.SelectDate.click();
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}

		ReservationPage.SearchRoomButton.click();
		Wait.wait2Second();
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClassName);
		test_steps.add("Select Room Class: " + RoomClassName);
		reservationLogger.info("Select Room Class: " + RoomClassName);

		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}
		new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
		Utility.roomIndex++;

		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		Wait.wait2Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}
		return test_steps;
	}

	public ArrayList<String> roomAssignment_1(WebDriver driver, String RoomClassName, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignmentButton);
		/*
		 * Wait.wait2Second(); jse.executeScript("window.scrollBy(0,-150)");
		 * ReservationPage.RoomAssignmentButton.click();
		 */ Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		ReservationPage.RoomAssignment_DateIcon.click();
		ReservationPage.SelectDate.click();

		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}

		ReservationPage.SearchRoomButton.click();
		Wait.wait2Second();
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClassName);
		test_steps.add("Select Room Class: " + RoomClassName);
		reservationLogger.info("Select Room Class: " + RoomClassName);

		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}
		new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
		Utility.roomIndex++;
		System.out.println("Select Room Number");
		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		Wait.wait2Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Toaster_Message, driver);
		Utility.app_logs.info("Toaster message appears :" + ReservationPage.Toaster_Message.getText());
		return test_steps;
	}

	public ArrayList<String> roomAssignment_2(WebDriver driver, String RoomClassName, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignment_DatePicker_Button_2);
		/*
		 * Wait.wait2Second(); jse.executeScript("window.scrollBy(0,-150)");
		 * ReservationPage.RoomAssignmentButton.click();
		 */ Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		ReservationPage.RoomAssignment_DateIcon.click();
		ReservationPage.SelectDate_2.click();
		// ReservationPage.RoomAssign_Check.click();
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}

		ReservationPage.SearchRoomButton.click();
		Wait.wait2Second();
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClassName);
		test_steps.add("Select Room Class: " + RoomClassName);
		reservationLogger.info("Select Room Class: " + RoomClassName);

		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}

		Wait.wait1Second();
		String roomnum = "(//tbody//select[contains(@data-bind,'RoomId')])/option";
		System.out.println(roomnum);
		int count = driver.findElements(By.xpath(roomnum)).size();
		Random random = new Random();
		int randomNumber = random.nextInt(count - 1) + 1;
		System.out.println("count : " + count);
		System.out.println("randomNumber : " + randomNumber);
		new Select(driver.findElement(By.xpath("(//tbody//select[contains(@data-bind,'RoomId')])")))
				.selectByIndex(randomNumber);

		// new
		// Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
		Utility.roomIndex++;
		System.out.println("Select Room Number");
		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		Wait.wait2Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Toaster_Message, driver);
		Utility.app_logs.info("Toaster message appears :" + ReservationPage.Toaster_Message.getText());
		return test_steps;
	}

	public ArrayList<String> SplitToAssignReser(WebDriver driver, String RoomClassName, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_RoomPicker, driver);
		Utility.ScrollToElement(ReservationPage.Click_RoomPicker, driver);
		ReservationPage.Click_RoomPicker.click();
		test_steps.add("Click on Rooms Picker");
		reservationLogger.info("Successfully clicked on Rooms Picker");
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Check_Split_Rooms, driver);
		test_steps.add("Room assignment popup appears");
		Utility.ScrollToElement(ReservationPage.Check_Split_Rooms, driver);

		if (ReservationPage.Check_Split_Rooms.isSelected()) {
			ReservationPage.Check_Split_Rooms.click();
			test_steps.add("Click Split Room");
			reservationLogger.info("Click Split Room");
		}
		assertTrue(!ReservationPage.Check_Split_Rooms.isSelected());
		test_steps.add("Split Room: UnChecked");

		Utility.ScrollToElement(ReservationPage.RoomAssign_Check, driver);
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
			test_steps.add("Click Assign Room");
			reservationLogger.info("Click Assign Room");
		}
		assertTrue(ReservationPage.RoomAssign_Check.isSelected());
		test_steps.add("Assign Room: Checked");
		ReservationPage.SearchRoomButton.click();
		test_steps.add("Click Search: Room Assignment");
		reservationLogger.info("Click Search Room");

		Wait.waitUntilPresenceOfElementLocated(OR.SelectRoomClasses, driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.SelectRoomClasses, driver);
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClassName);

		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}
		new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
		Utility.roomIndex++;
		String RoomName = new Select(ReservationPage.SelectRoomClasses).getFirstSelectedOption().getText() + " : "
				+ new Select(ReservationPage.SelectRoomNumbers).getFirstSelectedOption().getText();
		test_steps.add("Select Room  " + RoomName);
		reservationLogger.info("Select Room " + RoomName);
		assertTrue(RoomName.contains(RoomClassName), "Failed: Room Class is not the selected one");
		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		test_steps.add("Click Select: Room Assignment");
		reservationLogger.info("Click Select Room");
		Wait.wait2Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.ReCalculate_Folio_Options_PopUp, driver);
			if (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
				jse.executeScript("arguments[0].click();", ReservationPage.ReCal_Folio_Options_PopUp_Recalculate);
				jse.executeScript("arguments[0].click();", ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn);
				// ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();
				// ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn.click();
			}
		} catch (Exception e) {
			reservationLogger.info("No ReCalculate Folio Options PopUp");
		}
		Wait.wait2Second();
		try {
			// Wait.explicit_wait_visibilityof_webelement(ReservationPage.Policy_Comparision_PopUp,
			// driver);
			if (ReservationPage.Policy_Comparision_PopUp.isDisplayed()) {
				ReservationPage.Policy_Comparision_PopUp_Continue_Btn.click();
				test_steps.add("Click Continue Button of Policy Comparison Popup");
			}
		} catch (Exception e) {
			System.out.println("Policy Comparision Popup not displayed ");
		}
		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);
		Wait.waitUntilPresenceOfElementLocatedByClassName(OR.Toaster_Message, driver);
		reservationLogger.info(driver.findElement(By.className(OR.Toaster_Message)).getText());
		driver.findElement(By.className(OR.Toaster_Message)).click();

		String Room = ReservationPage.ReservationRoomStatus.getText();
		System.out.println("RC:" + Room);
		assertTrue(!Room.contains("Multiple Rooms"), "Failed: RoomClass is Multiple Rooms");

		assertEquals(Room, RoomName, "Failed: Room missmatched");
		String Room_Class = Room.split("\\:")[0].replaceAll("\\s+", "");
		String RoomNumber = Room.split("\\:")[1].replaceAll("\\s+", "");
		System.out.println("RC:" + Room_Class + " RN:" + RoomNumber);
		Wait.wait2Second();
		assertTrue(!Room_Class.contains("Unassigned"), "Failed: RoomClass is  Unassigned");
		System.out.println("Room Class : " + Room_Class);
		assertTrue(!RoomNumber.contains("Unassigned"), "Failed: RoomNumber is not Unassigned");
		System.out.println("Room Number : " + RoomNumber);
		test_steps.add("Verified Room Status: " + Room);
		return test_steps;
	}

	public void saveReservation(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// Thread.sleep(5000);
		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Click_Save_ReservationDetails, driver);
		Utility.ScrollToElement(ReservationPage.Click_Save_ReservationDetails, driver);
		Wait.wait2Second();
		ReservationPage.Click_Save_ReservationDetails.click();
		Wait.wait2Second();
		try {
			if (ReservationPage.Verify_Depos_policy.isDisplayed()) {
				ReservationPage.Click_Without_Deposit.click();
				Wait.wait2Second();
			}
		} catch (Exception e) {

		}
		try {

			if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
				String getTotasterTitle_ReservationSucess = ReservationPage.Toaster_Title.getText();
				String getToastermessage_ReservationSucess = ReservationPage.Toaster_Message.getText();
				if (getToastermessage_ReservationSucess.equals("Room assignment has changed")) {
					Assert.assertEquals(getTotasterTitle_ReservationSucess, "Room assignment has changed");
				} else if (getToastermessage_ReservationSucess.equals("Reservation Saved")) {
					Assert.assertEquals(getTotasterTitle_ReservationSucess, "Reservation Saved");
				}

				if (getToastermessage_ReservationSucess.endsWith("has been saved successfully"))
					;
			}
		} catch (Exception e) {

		}

		// Wait.wait5Second();
	}

	public String GetReservationnumber(WebDriver driver, ExtentTest test1) throws IOException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", ReservationPage.Get_Confirmation_Number);
		ReservationConfirmation = ReservationPage.Get_Confirmation_Number.getText();
		if (ReservationConfirmation.contains("Pending")) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Get_Confirmation_Number, driver);
			ReservationConfirmation = ReservationPage.Get_Confirmation_Number.getText();
		}
		reservationLogger.info("ReservationConfirmation :" + ReservationConfirmation);
		// test_steps.add("Reservation Number is : " +ReservationConfirmation);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(".\\ConfirmationNumber.txt"));
			writer.write(ReservationConfirmation);
			writer.flush();
			writer.close();
		} catch (Exception e) {

		}
		return ReservationConfirmation;
	}

	public void manualEmail(WebDriver driver, String Email, String Attachment) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Email_icon, driver);
		Utility.ScrollToElement(ReservationPage.Click_Email_icon, driver);

		ReservationPage.Click_Email_icon.click();
		Utility.app_logs.info("Click Mail Icon");
		Wait.explicit_wait_xpath(OR.Verify_Send_Email_Popup, driver);
		Wait.explicit_wait_absenceofelement(OR.Verify_loading_popup_mailContentPopUp, driver);
		Wait.wait5Second();
		String GetEmailid = ReservationPage.Get_email_Id.getText();
		reservationLogger.info(GetEmailid + "" + GetEmailid);
		if (GetEmailid.equals("dinesh.ganganaboina@gmail.com")) {

		} else {
			ReservationPage.Get_email_Id.clear();
			Wait.wait3Second();
			ReservationPage.Get_email_Id.sendKeys(Email);
		}
		new Select(ReservationPage.Select_Attachment).selectByVisibleText(Attachment);
		Wait.wait5Second();
		ReservationPage.Click_Send_Email.click();
		Utility.app_logs.info("Click Send Email ");
		Wait.wait10Second();
	}

	public double Checkin(WebDriver driver, String PropertyName, String RoomClassName, String CheckorUncheckAssign,
			String PaymentType, String CardName, String CCNumber, String CCExpiry, String CCVCode,
			String Authorizationtype, String ChangeAmount, String ChangeAmountValue, String traceData,
			ArrayList test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Actions action = new Actions(driver);
		double processedamount = 0;
		double endingbalance;

		String GetEndingBalance = ReservationPage.Payment_Details_Folio_Balance.getText();
		reservationLogger.info(GetEndingBalance);
		String RemoveCurreny[] = GetEndingBalance.split(" ");
		endingbalance = Double.parseDouble(RemoveCurreny[1]);
		reservationLogger.info("Ending balance before checkin " + endingbalance);

		Wait.explicit_wait_elementToBeClickable(ReservationPage.Click_Checkin, driver);
		// action.moveToElement(ReservationPage.Click_Checkin).doubleClick().build().perform();
		Wait.wait1Second();
		ReservationPage.Click_Checkin.click();
		reservationLogger.info("Clicked on CheckIn button");

		try {
			Wait.explicit_wait_10sec(ReservationPage.Already_Checked_In_Confirm_Popup, driver);
			ReservationPage.Already_Checked_In_Confirm_Popup_Confirm_Btn.click();
			flag = true;
		} catch (Exception e) {

			reservationLogger.info("No conflicts with room assignment");
		}
		if (flag == true) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Room_Assignment_PopUp, driver);
			Select selectRoomNumber = new Select(ReservationPage.Select_Room_Number);
			String selectedOption = selectRoomNumber.getFirstSelectedOption().getText();
			if (selectedOption.contains("(O and")) {

				String RoomwithVandC = driver.findElement(By.xpath("//option[contains(text(),'(V and C)')] "))
						.getAttribute("value");
				selectRoomNumber.selectByValue(RoomwithVandC);
				reservationLogger.info("Selected first available room with V and C status from the list");
				Wait.wait2Second();
				ReservationPage.Click_Select.click();
				reservationLogger.info("Clicked on select button of room assignment popup");
				try {

					Wait.explicit_wait_visibilityof_webelement(ReservationPage.ReCalculate_Folio_Options_PopUp, driver);
					if (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
						ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();
						action.moveToElement(ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn).click().build()
								.perform();

					}
				} catch (Exception e) {
					reservationLogger.info("No ReCalculate Folio Options PopUp");
				}

			} else {

				reservationLogger.info("No Issues");
			}
		}
		if (flag == false) {
			Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
			Wait.wait5Second();
			// long Loadguestregform_StartTime = System.currentTimeMillis();
			try {
				ReservationPage.Click_Select.click();
				reservationLogger.info("Clicked on select button of room assignment popup");
				Wait.wait3Second();
				Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
			} catch (Exception e) {

			}
		}
		Wait.wait10Second();

		try {

			if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
				ReservationPage.Click_Continue_RuleBroken_Popup.click();
				reservationLogger.info("Clicked on continue button of RulesBroken Popup");
			} else {
				reservationLogger.info("Satisfied Rules");
			}
			if (ReservationPage.Verify_Dirty_Room_popup.isDisplayed()) {
				ReservationPage.Confirm_button.click();
				reservationLogger.info("Clicked on confirm button of DirtyRoom Popup");
			} else {
				reservationLogger.info("No Dirty Rooms");
			}
		} catch (Exception e) {

		}
		Wait.wait10Second();
		try {
			if (ReservationPage.Payment_Popup.isDisplayed()) {
				reservationLogger.info("This Reservation has checkin ploicy, Payment popup is displayed");
				ReservationFolio Payment = new ReservationFolio();
				checkinpolicy = true;
				processedamount = Payment.TestPaymentPopup(driver, PaymentType, CardName, CCNumber, CCExpiry, CCVCode,
						Authorizationtype, ChangeAmount, ChangeAmountValue, traceData, test_steps);
				reservationLogger.info("Payment process is completed");
			}
		} catch (Exception e) {
			reservationLogger.info("Checkin Policy doesn't exist");
		}

		if (checkinpolicy == false) {
			reservationLogger
					.info("Trying to Clicking on Confirm button of Guest Registration Form in Reservation.java");
			// Thread.sleep(10000);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_on_confirm, driver);
			ReservationPage.Click_on_confirm.click();
			Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
			Wait.wait3Second();
		}

		try {
			if (ReservationPage.Key_Generation_Popup.isDisplayed()) {
				ReservationPage.Key_Generation_Close.click();
				Wait.wait15Second();
			}
		} catch (Exception e) {
			reservationLogger.info("Key Geneartion doesnt exist");
		}
		checkinpolicy = false;
		return processedamount;
	}

	public ArrayList<String> Checkin_CashPayment(WebDriver driver, String PaymentType, String Amount,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.explicit_wait_elementToBeClickable(ReservationPage.Click_Checkin, driver);
		Utility.ScrollToElement(ReservationPage.Click_Checkin, driver);
		ReservationPage.Click_Checkin.click();
		reservationLogger.info("Clicked on CheckIn button");
		/*
		 * try { Wait.explicit_wait_10sec(ReservationPage.
		 * Already_Checked_In_Confirm_Popup);
		 * ReservationPage.Already_Checked_In_Confirm_Popup_Confirm_Btn.click();
		 * flag = true; } catch (Exception e) {
		 * 
		 * reservationLogger.info("No conflicts with room assignment"); } if
		 * (flag == true) {
		 * Wait.explicit_wait_visibilityof_webelement(ReservationPage.
		 * Room_Assignment_PopUp); Select selectRoomNumber = new
		 * Select(ReservationPage.Select_Room_Number); String selectedOption =
		 * selectRoomNumber.getFirstSelectedOption().getText(); if
		 * (selectedOption.contains("(O and")) {
		 * 
		 * String RoomwithVandC =
		 * driver.findElement(By.xpath("//option[contains(text(),'(V and C)')] "
		 * )) .getAttribute("value");
		 * selectRoomNumber.selectByValue(RoomwithVandC); reservationLogger.
		 * info("Selected first available room with V and C status from the list"
		 * ); Wait.wait2Second(); ReservationPage.Click_Select.click();
		 * reservationLogger.
		 * info("Clicked on select button of room assignment popup"); try {
		 * 
		 * Wait.explicit_wait_visibilityof_webelement(ReservationPage.
		 * ReCalculate_Folio_Options_PopUp); if
		 * (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
		 * ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();
		 * action.moveToElement(ReservationPage.
		 * ReCal_Folio_Options_PopUp_Select_Btn).click().build() .perform(); } }
		 * catch (Exception e) {
		 * reservationLogger.info("No ReCalculate Folio Options PopUp"); }
		 * 
		 * } else {
		 * 
		 * reservationLogger.info("No Issues"); } }
		 */// if (flag == false) {
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Select, driver);
			ReservationPage.Click_Select.click();
			reservationLogger.info("Clicked on select button of room assignment popup");
			// Wait.wait3Second();
			// Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup);
		} catch (Exception e) {

		}
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Room_Assignment_PopUp, driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Select, driver);
		ReservationPage.Click_Select.click();
		reservationLogger.info("Clicked on select button of room assignment popup");

		// payment
		/*
		 * try { Wait.explicit_wait_visibilityof_webelement(ReservationPage.
		 * Verify_RulesBroken_Popup); if
		 * (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
		 * ReservationPage.Click_Continue_RuleBroken_Popup.click();
		 * reservationLogger.
		 * info("Clicked on continue button of RulesBroken Popup"); } else {
		 * reservationLogger.info("Satisfied Rules"); } } catch (Exception e) {
		 * reservationLogger.info("Satisfied Rules"); }
		 */
		/*
		 * try { Wait.explicit_wait_visibilityof_webelement(ReservationPage.
		 * Verify_Dirty_Room_popup); if
		 * (ReservationPage.Verify_Dirty_Room_popup.isDisplayed()) {
		 * ReservationPage.Confirm_button.click();
		 * reservationLogger.info("Clicked on confirm button of DirtyRoom Popup"
		 * ); } else { reservationLogger.info("No Dirty Rooms"); } } catch
		 * (Exception e) { reservationLogger.info("No dirty Room"); }
		 */ // Wait.wait10Second();

		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Payment_Popup, driver);
			if (ReservationPage.Payment_Popup.isDisplayed()) {
				reservationLogger.info("Payment popup is displayed");
				CashPayment(driver, PaymentType, Amount);
				reservationLogger.info("Payment process is completed");
				test_steps.add("Payment process is completed");
			}

		} catch (Exception e) {
			reservationLogger.info("Payment Popup not Appear");
			test_steps.add("Payment popup not appear");
		}

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_on_confirm, driver);
		ReservationPage.Click_on_confirm.click();
		test_steps.add("Click on confrim button");

		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.Toaster_Message, driver);
		return test_steps;
	}

	public double Checkin_CardPayment(WebDriver driver, String PaymentType, String CardName, String CCNumber,
			String CCExpiry, String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue,
			String TraceData) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Actions action = new Actions(driver);
		double processedamount = 0;
		double endingbalance;

		String GetEndingBalance = ReservationPage.Payment_Details_Folio_Balance.getText();
		reservationLogger.info(GetEndingBalance);
		String RemoveCurreny[] = GetEndingBalance.split(" ");
		endingbalance = Double.parseDouble(RemoveCurreny[1]);
		reservationLogger.info("Ending balance before checkin " + endingbalance);

		Wait.explicit_wait_elementToBeClickable(ReservationPage.Click_Checkin, driver);
		// action.moveToElement(ReservationPage.Click_Checkin).doubleClick().build().perform();
		Wait.wait1Second();
		ReservationPage.Click_Checkin.click();
		reservationLogger.info("Clicked on CheckIn button");

		boolean flag = false;
		try {
			Wait.explicit_wait_10sec(ReservationPage.Already_Checked_In_Confirm_Popup, driver);
			ReservationPage.Already_Checked_In_Confirm_Popup_Confirm_Btn.click();
			flag = true;
		} catch (Exception e) {

			reservationLogger.info("No conflicts with room assignment");
		}
		if (flag == true) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Room_Assignment_PopUp, driver);
			Select selectRoomNumber = new Select(ReservationPage.Select_Room_Number);
			String selectedOption = selectRoomNumber.getFirstSelectedOption().getText();
			if (selectedOption.contains("(O and")) {

				String RoomwithVandC = driver.findElement(By.xpath("//option[contains(text(),'(V and C)')] "))
						.getAttribute("value");
				selectRoomNumber.selectByValue(RoomwithVandC);
				reservationLogger.info("Selected first available room with V and C status from the list");
				Wait.wait2Second();
				ReservationPage.Click_Select.click();
				reservationLogger.info("Clicked on select button of room assignment popup");
				try {

					Wait.explicit_wait_visibilityof_webelement(ReservationPage.ReCalculate_Folio_Options_PopUp, driver);
					if (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
						ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();
						action.moveToElement(ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn).click().build()
								.perform();

					}
				} catch (Exception e) {
					reservationLogger.info("No ReCalculate Folio Options PopUp");
				}

			} else {

				reservationLogger.info("No Issues");
			}
		}
		if (flag == false) {
			Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
			Wait.wait5Second();
			// long Loadguestregform_StartTime = System.currentTimeMillis();
			try {
				ReservationPage.Click_Select.click();
				reservationLogger.info("Clicked on select button of room assignment popup");
				// Wait.wait3Second();
				// Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup);
			} catch (Exception e) {

			}
		}
		try {

			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_RulesBroken_Popup, driver);
			if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
				ReservationPage.Click_Continue_RuleBroken_Popup.click();
				reservationLogger.info("Clicked on continue button of RulesBroken Popup");
			}
		} catch (Exception e) {
			reservationLogger.info("Satisfied Rules");
		}
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_Dirty_Room_popup, driver);
			if (ReservationPage.Verify_Dirty_Room_popup.isDisplayed()) {
				ReservationPage.Confirm_button.click();
				reservationLogger.info("Clicked on confirm button of DirtyRoom Popup");
			}
		} catch (Exception e) {
			reservationLogger.info("No Dirty Rooms");
		}
		// Wait.wait10Second();
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Payment_Popup, driver);
			if (ReservationPage.Payment_Popup.isDisplayed()) {
				reservationLogger.info("Payment Popup is displayed");
				ReservationFolio Payment = new ReservationFolio();
				if (PaymentType.equalsIgnoreCase("MC")) {
					System.out.println(PaymentType);
					Payment.CardPayment_MC(driver, PaymentType, CardName, CCNumber, CCExpiry, CCVCode,
							Authorizationtype, ChangeAmount, ChangeAmountValue, TraceData);

					reservationLogger.info("Payment process is completed");
				}
			}
		} catch (Exception e) {
			reservationLogger.info("Checkin Policy doesn't exist");
		}

		reservationLogger.info("Trying to Clicking on Confirm button of Guest Registration Form in Reservation.java");
		// Thread.sleep(10000);
		Wait.explicit_wait_visibilityof_webelement_200(ReservationPage.Click_on_confirm, driver);
		ReservationPage.Click_on_confirm.click();
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		Wait.wait3Second();

		try {
			if (ReservationPage.Key_Generation_Popup.isDisplayed()) {
				ReservationPage.Key_Generation_Close.click();
				Wait.wait15Second();
			}
		} catch (Exception e) {
			reservationLogger.info("Key Geneartion doesnt exist");
		}
		return processedamount;
	}

	// In this Checkin after the guest report will display it will confirm it
	public double CheckinReservation(WebDriver driver, String RoomClassName, String CheckorUncheckAssign,
			String PaymentType, String CardName, String CCNumber, String CCExpiry, String CCVCode,
			String Authorizationtype, String ChangeAmount, String ChangeAmountValue, String traceData)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Actions action = new Actions(driver);
		double processedamount = 0;
		double endingbalance;

		String GetEndingBalance = ReservationPage.Payment_Details_Folio_Balance.getText();
		reservationLogger.info(GetEndingBalance);
		String RemoveCurreny[] = GetEndingBalance.split(" ");
		endingbalance = Double.parseDouble(RemoveCurreny[1]);
		reservationLogger.info("Ending balance before checkin " + endingbalance);

		Wait.explicit_wait_elementToBeClickable(ReservationPage.Click_Checkin, driver);
		// action.moveToElement(ReservationPage.Click_Checkin).doubleClick().build().perform();
		Wait.wait1Second();
		ReservationPage.Click_Checkin.click();
		reservationLogger.info("Clicked on CheckIn button");

		try {
			Wait.explicit_wait_10sec(ReservationPage.Already_Checked_In_Confirm_Popup, driver);
			ReservationPage.Already_Checked_In_Confirm_Popup_Confirm_Btn.click();
			flag = true;
		} catch (Exception e) {
			reservationLogger.info("No conflicts with room assignment");
		}
		if (flag == true) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Room_Assignment_PopUp, driver);
			Select selectRoomNumber = new Select(ReservationPage.Select_Room_Number);
			String selectedOption = selectRoomNumber.getFirstSelectedOption().getText();
			if (selectedOption.contains("(O and")) {

				String RoomwithVandC = driver.findElement(By.xpath("//option[contains(text(),'(V and C)')] "))
						.getAttribute("value");
				selectRoomNumber.selectByValue(RoomwithVandC);
				reservationLogger.info("Selected first available room with V and C status from the list");
				Wait.wait2Second();
				ReservationPage.Click_Select.click();
				reservationLogger.info("Clicked on select button of room assignment popup");
				try {

					Wait.explicit_wait_visibilityof_webelement(ReservationPage.ReCalculate_Folio_Options_PopUp, driver);
					if (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
						ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();
						action.moveToElement(ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn).click().build()
								.perform();

					}
				} catch (Exception e) {
					reservationLogger.info("No ReCalculate Folio Options PopUp");
				}

			} else {

				reservationLogger.info("No Issues");
			}
		}
		if (flag == false) {
			Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
			Wait.wait5Second();
			// long Loadguestregform_StartTime = System.currentTimeMillis();
			try {
				ReservationPage.Click_Select.click();
				reservationLogger.info("Clicked on select button of room assignment popup");
				Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
			} catch (Exception e) {

			}
		}
		Wait.wait10Second();

		try {

			if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
				System.out.println("Rule Break is displayed");
				ReservationPage.Click_Continue_RuleBroken_Popup.click();
				reservationLogger.info("Clicked on continue button of RulesBroken Popup");
			} else {
				reservationLogger.info("Satisfied Rules");
			}
			if (ReservationPage.Verify_Dirty_Room_popup.isDisplayed()) {
				System.out.println("Dirty Room popup displayed");
				ReservationPage.Confirm_button.click();
				reservationLogger.info("Clicked on confirm button of DirtyRoom Popup");
				// Wait.wait3Second();
			} else {
				reservationLogger.info("No Dirty Rooms");
			}
		} catch (Exception e) {

		}

		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Payment_Popup, driver);
			if (ReservationPage.Payment_Popup.isDisplayed()) {
				reservationLogger.info("This Reservation has checkin ploicy, Payment popup is displayed");
				ReservationFolio Payment = new ReservationFolio();
				checkinpolicy = true;
				Payment.Checkin_TestPaymentPopup(driver, PaymentType, CardName, CCNumber, CCExpiry, CCVCode,
						Authorizationtype, ChangeAmount, ChangeAmountValue, traceData);
				reservationLogger.info("Payment process is completed");
			}
		} catch (Exception e) {
			reservationLogger.info("Checkin Policy doesn't exist");
		}

		if (checkinpolicy == false) {
			reservationLogger
					.info("Trying to Clicking on Confirm button of Guest Registration Form in Reservation.java");
			// Thread.sleep(10000);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_on_confirm, driver);
			ReservationPage.Click_on_confirm.click();
			Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
			Wait.wait3Second();
		}

		try {
			if (ReservationPage.Key_Generation_Popup.isDisplayed()) {
				ReservationPage.Key_Generation_Close.click();
				Wait.wait15Second();
			}
		} catch (Exception e) {
			reservationLogger.info("Key Generation doesnt exist");
		}
		checkinpolicy = false;
		return processedamount;
	}

	public void checkout(WebDriver driver, String PaymentType, String CardName, String CCNumber, String CCExpiry,
			String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue, String traceData,
			ArrayList test_steps)

			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		try {
			Wait.wait5Second();
			ReservationPage.Click_Checkout.click();
			reservationLogger.info("Clicked on CHECKOUT button");
			Wait.wait5Second();

			if (driver.findElement(By.xpath(OR.Resell_Rooms_Popup_Header)).isDisplayed()) {
				driver.findElement(By.xpath(OR.Resell_Rooms_Popup_Continue_Button)).click();
			}

			Wait.wait5Second();

			if (ReservationPage.Payment_Popup.isDisplayed()) {
				reservationLogger.info("Payment Popup is displayed");
				ReservationFolio Payment = new ReservationFolio();
				Payment.TestPaymentPopup(driver, PaymentType, CardName, CCNumber, CCExpiry, CCVCode, Authorizationtype,
						ChangeAmount, ChangeAmountValue, traceData, test_steps);
				reservationLogger.info("Payment is processed");
			} else {
				reservationLogger.info("Payment Popup is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Wait.wait5Second();
		Wait.waitUntilPresenceOfElementLocated(OR.Click_Close, driver);
		ReservationPage.Click_Close.click();
		reservationLogger.info("Clicked on CLOSE button of Guest Statement Report");

		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		Wait.wait5Second();

	}

	public ArrayList<String> checkout_Close(WebDriver driver, String PaymentType, String CardName, String CCNumber,
			String CCExpiry, String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue,
			String traceData, ExtentTest test, ArrayList test_steps)

			throws InterruptedException, IOException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		String ResNumber = GetReservationnumber(driver);
		try {
			Wait.wait5Second();
			ReservationPage.Click_Checkout.click();
			reservationLogger.info("Clicked on CHECKOUT button");
			Wait.wait5Second();

			if (driver.findElement(By.xpath(OR.Resell_Rooms_Popup_Header)).isDisplayed()) {
				driver.findElement(By.xpath(OR.Resell_Rooms_Popup_Continue_Button)).click();
			}

			Wait.wait5Second();

			if (ReservationPage.Payment_Popup.isDisplayed()) {
				reservationLogger.info("Payment Popup is displayed");
				ReservationFolio Payment = new ReservationFolio();
				Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
				float processed_amount = 0;
				// start here
				if (PaymentType.equalsIgnoreCase("Swipe")) {
					try {

						ReservationFolio.Click_Swipe_Icon.click();
						reservationLogger.info("Clicked on swipe icon");
						Wait.explicit_wait_xpath(OR.Verify_Swipe_Popup, driver);
						ReservationFolio.Enter_Track_Data.sendKeys(traceData);

						if (ChangeAmount.equalsIgnoreCase("Yes")) {
							ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);

						} else {
							reservationLogger.info("Processed complete amount");
						}
						Wait.wait3Second();
						new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText(Authorizationtype);
						Wait.wait3Second();
						ReservationFolio.Click_Process.click();
						reservationLogger.info("Clicked on Process Button");
						Wait.wait3Second();
						Wait.explicit_wait_xpath(OR.Verify_MC_Grid, driver);
						String GetPaymentMethod = ReservationFolio.GetMC_Payment_Method.getText();
						reservationLogger.info("PaymentMethod: " + " " + GetPaymentMethod);
						if (GetPaymentMethod.equals("MC")) {
							reservationLogger.info("Paymnet is successful");
						} else {
							reservationLogger.info("Paymnet is failed");
						}

						Wait.wait1Second();
						Utility.ScrollToElement(ReservationFolio.Processed_Amount_In_Paymentdetails_Popup, driver);
						Wait.wait2Second();
						String Processed_Amount = ReservationFolio.Processed_Amount_In_Paymentdetails_Popup.getText();
						reservationLogger.info(Processed_Amount + " -- " + Processed_Amount);
						String RemoveCurreny[] = Processed_Amount.split(" ");
						processed_amount = Float.parseFloat(RemoveCurreny[1]);
						reservationLogger.info(processed_amount);

						ReservationFolio.Click_Continue.click();
						reservationLogger.info("Clicked on continue button");
						Thread.sleep(10000);
						TestCore.login_NONGS(driver);
						ReservationSearch resSearch = new ReservationSearch();
						resSearch.SearchAndOpenRes(driver, ResNumber);
						reservationLogger.info("Open Reservation " + ResNumber);
						test_steps.add("Open Reservation " + ResNumber);
						VerifyStatus(driver, "Departed");
						reservationLogger.info("Verified Reservation Status Departed");
						test_steps.add("Verified Reservation Status Departed");
						/*
						 * Wait.wait3Second();
						 * Wait.explicit_wait_xpath(OR.GetAddedLine_MC); String
						 * GetMCCard =
						 * ReservationFolio.GetAddedLine_MC.getText();
						 * reservationLogger.info("CardType: " + " " +
						 * GetMCCard); if (GetMCCard.
						 * equals("Name: TEST CARD/MC Account #: XXXX5454 Exp. Date: 12/23"
						 * )) { reservationLogger.info("Paymnet is successful");
						 * } else { reservationLogger.info("Paymnet is failed");
						 * } Wait.wait3Second(); String GetBalance =
						 * ReservationFolio.Verify_Balance_Zero.getText(); //
						 * reservationLogger.info("Balance: " + " " + //
						 * GetBalance); RemoveCurreny = GetBalance.split(" ");
						 * reservationLogger.info("Balance: " +
						 * RemoveCurreny[1]); if
						 * (ChangeAmount.equalsIgnoreCase("NO")) { if
						 * (RemoveCurreny[1].equals("0.00")) ; } else {
						 * reservationLogger.info("Selected Changed Value"); }
						 */
					} catch (Exception e) {
						test.log(LogStatus.FAIL,
								"Exception occured while paying using swipe \n" + e.getMessage()
										+ "\n\n <br> Attaching screenshot below : \n"
										+ test.addScreenCapture(Utility.captureScreenShot(
												test.getTest().getName() + "_Payment_BySwipe" + Utility.getTimeStamp(),
												driver)));
						reservationLogger.error("Exception occured while paying using swipe \n");
						e.printStackTrace();
					}
				}
				reservationLogger.info("Payment is processed");
			} else {
				reservationLogger.info("Payment Popup is NOT displayed");
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Checkout is Failed \n" + e.getMessage() + "\n\n <br> Attaching screenshot below : \n"
							+ test.addScreenCapture(Utility.captureScreenShot(
									test.getTest().getName() + "_CheckOut" + Utility.getTimeStamp(), driver)));
			reservationLogger.info("Checkout is Failed \n");
			e.printStackTrace();
		}
		return test_steps;

	}

	public ArrayList<String> Checkout_CardPayment(WebDriver driver, String PaymentType, String CardName,
			String CCNumber, String CCExpiry, String CCVCode, String Authorizationtype, String ChangeAmount,
			String ChangeAmountValue, String TraceData, ArrayList<String> test_steps)

			throws InterruptedException, IOException {
		String ResNumber = GetReservationnumber(driver);
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Checkout, driver);
		ReservationPage.Click_Checkout.click();
		reservationLogger.info("Clicked on CHECKOUT button");

		try {
			Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.Resell_Rooms_Popup_Header)),
					driver);
			if (driver.findElement(By.xpath(OR.Resell_Rooms_Popup_Header)).isDisplayed()) {
				driver.findElement(By.xpath(OR.Resell_Rooms_Popup_Continue_Button)).click();
			}
		} catch (Exception e) {
			reservationLogger.info("Resell Room Popup not displayed");
		}
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Payment_Popup, driver);
			if (ReservationPage.Payment_Popup.isDisplayed()) {
				reservationLogger.info("Payment Popup is displayed");
				ReservationFolio Payment = new ReservationFolio();
				if (PaymentType.equalsIgnoreCase("MC")) {
					System.out.println(PaymentType);
					Payment.CardPayment_MC(driver, PaymentType, CardName, CCNumber, CCExpiry, CCVCode,
							Authorizationtype, ChangeAmount, ChangeAmountValue, TraceData);
				}
			}
			reservationLogger.info("Payment is processed");

		} catch (Exception e) {
			reservationLogger.info("Payment Popup not displayed");
		}

		reservationLogger.info("Waiting....");
		Thread.sleep(5000);
		reservationLogger.info("Waiting over");
		driver.navigate().refresh();
		reservationLogger.info("Reload Page");
		TestCore.login_NONGS(driver);
		ReservationSearch resSearch = new ReservationSearch();
		resSearch.SearchAndOpenRes(driver, ResNumber);
		reservationLogger.info("Open Reservation " + ResNumber);
		test_steps.add("Open Reservation " + ResNumber);
		VerifyStatus(driver, "Departed");
		reservationLogger.info("Verified Reservation Status Departed");
		test_steps.add("Verified Reservation Status Departed");
		return test_steps;

	}

	public ArrayList<String> checkout(WebDriver driver, String ResNumber, ExtentTest test, ArrayList<String> test_steps)
			throws InterruptedException, IOException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		try {
			Wait.wait5Second();
			ReservationPage.Click_Checkout.click();
			reservationLogger.info("Clicked on CHECKOUT button");
			// Wait.wait5Second();
			try {
				// Utility.ElementFinderUntillNotShow(By.xpath(OR.Resell_Rooms_Popup_Header),
				// driver);
				Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.Resell_Rooms_Popup_Header)),
						driver);
				if (driver.findElement(By.xpath(OR.Resell_Rooms_Popup_Header)).isDisplayed()) {
					driver.findElement(By.xpath(OR.Resell_Rooms_Popup_Continue_Button)).click();
					Utility.app_logs.info("Resell room Popup displayed");
				}
			} catch (Exception e) {
				Utility.app_logs.info("NOresell room Popup displayed");
			}
			Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Verify_Payment_Details_poup, driver);

			String amount = "(//label[.='Amount:'])[2]/following-sibling::div/input";
			Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(amount)), driver);

			String str = driver.findElement(By.xpath(amount)).getText();

			str = str.replace("-", "");

			driver.findElement(By.xpath(amount)).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			Wait.wait2Second();
			driver.findElement(By.xpath(amount)).sendKeys("5");

			new Select(driver.findElement(By.xpath("//select[@name='AuthorizationTypeOut']")))
					.selectByVisibleText("Credit");

			Wait.WaitForElement(driver, OR.Click_Process);
			ReservationPage.Click_Process.click();
			reservationLogger.info("Clicking on Process");
			Thread.sleep(5000);

			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Continue, driver);
			// Wait.WaitForElement(driver, OR.Click_Continue);
			ReservationPage.Click_Continue.click();
			reservationLogger.info("Clicking on Continue");

			// Wait.wait5Second();

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Checkout is Failed \n" + e.getMessage() + "\n\n <br> Attaching screenshot below : \n"
							+ test.addScreenCapture(Utility.captureScreenShot(
									test.getTest().getName() + "_CheckOut" + Utility.getTimeStamp(), driver)));
			reservationLogger.info("Checkout is Failed \n");
			e.printStackTrace();
		}

		Thread.sleep(5000);
		reservationLogger.info("After Sleep");
		TestCore.login_NONGS(driver);
		ReservationSearch resSearch = new ReservationSearch();
		resSearch.SearchAndOpenRes(driver, ResNumber);
		reservationLogger.info("Open Reservation " + ResNumber);
		test_steps.add("Open Reservation " + ResNumber);
		VerifyStatus(driver, "Departed");
		reservationLogger.info("Verified Reservation Status Departed");
		test_steps.add("Verified Reservation Status Departed");
		return test_steps;

	}

	public ArrayList<String> Checkout(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException, IOException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		String ResNumber = GetReservationnumber(driver);

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Checkout, driver);
		ReservationPage.Click_Checkout.click();
		reservationLogger.info("Clicked on CHECKOUT button");
		// Wait.wait5Second();
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Resell_Rooms_Popup, driver);
			if (ReservationPage.Resell_Rooms_Popup.isDisplayed()) {
				ReservationPage.Resell_Rooms_Popup_Continue_Btn.click();
				Utility.app_logs.info("Resell_Rooms_Popup");
			}
		} catch (Exception e) {
			Utility.app_logs.info("No resell_Rooms_Popup");
		}
		try {
			Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.Verify_Payment_Details_poup)),
					driver);
			if (ReservationPage.Verify_Payment_Details_poup.isDisplayed()) {
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Continue, driver);
				Utility.ScrollToElement(ReservationPage.Click_Continue, driver);
				ReservationPage.Click_Continue.click();
				reservationLogger.info("Clicking on Continue");
			}
		} catch (Exception e) {

			Utility.app_logs.info("No Payment");
		}
		Thread.sleep(5000);
		driver.navigate().refresh();
		TestCore.login_NONGS(driver);
		ReservationSearch resSearch = new ReservationSearch();
		resSearch.SearchAndOpenRes(driver, ResNumber);
		reservationLogger.info("Open Reservation " + ResNumber);
		test_steps.add("Open Reservation " + ResNumber);
		VerifyStatus(driver, "Departed");
		reservationLogger.info("Verified Reservation Status Departed");
		test_steps.add("Verified Reservation Status Departed");
		return test_steps;

		// Wait.wait5Second();

	}

	// This method is used to get the console errors of web page
	public ArrayList<String> analyzeLog(WebDriver driver, ArrayList<String> test_steps) {
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.BROWSER, Level.SEVERE);
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
		String message = "";
		boolean error = false;
		for (LogEntry entry : logEntries) {
			if (entry.getLevel().getName() == "SEVERE") {
				message = message + new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage()
						+ "\n";
				error = true;
			}
		}
		if (error) {
			reservationLogger.info(message);
			test_steps.add(message);
			// driver.navigate().refresh();
			assertTrue(false, message);
		}
		return test_steps;
	}

	public boolean WebBUG(WebDriver driver) {
		reservationLogger.info("Checking Web BUG");
		DesiredCapabilities caps = DesiredCapabilities.chrome();

		reservationLogger.info("Checking Web BUG");
		LoggingPreferences logPrefs = new LoggingPreferences();

		reservationLogger.info("Checking Web BUG");
		logPrefs.enable(LogType.BROWSER, Level.SEVERE);

		reservationLogger.info("Checking Web BUG");
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

		reservationLogger.info("Checking Web BUG");
		Set<String> logs = driver.manage().logs().getAvailableLogTypes();

		System.out.println(logs.size());

		for (String log : logs) {
			System.out.println(log);
		}
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);

		reservationLogger.info("Checking Web BUG");
		String message = "";
		boolean error = false;
		for (LogEntry entry : logEntries) {

			// reservationLogger.info("Checking Web BUG" +
			// entry.getLevel().getName());
			if (entry.getLevel().getName() == "SEVERE") {
				message = message + new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage()
						+ "\n";
				// reservationLogger.info("Message : " + message);
				error = true;
			}
		}
		reservationLogger.info(message);
		return error;
	}

	public ArrayList<String> get_ReservationStatus(WebDriver driver, ExtentTest test1, ArrayList<String> test_steps) {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.WaitForElement(driver, OR.Reservation_Status);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Reservation_Status, driver);

		String str = new Select(ReservationPage.Reservation_Status).getFirstSelectedOption().getText();
		if (str.contains("Departed")) {
			reservationLogger.info("Reservation is successfully checked out");
			test_steps.add("Reservation is successfully checked out : " + str);
		} else {
			reservationLogger.info("Reservation is not successfully checked out");
			test_steps.add("Reservation is not successfully checked out : " + str);
		}
		String bal = ReservationPage.Verify_Balance_Zero.getText();
		bal = bal.replace("$", " ");
		bal = bal.trim();

		Double d = Double.parseDouble(bal);

		if (d == 0) {
			reservationLogger.info("Amount is successfully credited");
			test_steps.add("Amount is successfully credited");
		} else {
			reservationLogger.info("Amount is not successfully credited");
			test_steps.add("Amount is not successfully credited");
		}
		return test_steps;

	}

	public String GetReservationStatus(WebDriver driver) {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.WaitForElement(driver, OR.Reservation_Status);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Reservation_Status, driver);

		String str = new Select(ReservationPage.Reservation_Status).getFirstSelectedOption().getText();
		return str;

	}

	public void AddNotes(WebDriver driver, String subject, String details, String NoteType, String Notestatus) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		jse.executeScript("arguments[0].click();", ReservationPage.Add_Note_Reservation);
		// ReservationPage.Add_Note_Reservation.click();
		Wait.explicit_wait_xpath(OR.verify_Add_Notes_popup, driver);
		ReservationPage.Enter_Subject_Notes.sendKeys(subject);
		ReservationPage.Enter_Note_Details.sendKeys(details);
		new Select(ReservationPage.Select_Note_Type).selectByVisibleText(NoteType);
		if (ReservationPage.Check_Action_Required.isSelected()) {
			reservationLogger.info("Action Requited checkbox is already selected");
		} else {
			ReservationPage.Check_Action_Required.click();
		}
		new Select(ReservationPage.Select_Notes_Status).selectByVisibleText(Notestatus);

		ReservationPage.Click_Save_Note.click();
		if (ReservationPage.Verify_Added_Notes.isDisplayed()) {
			reservationLogger.info("Created note Successfully");
		} else {
			reservationLogger.info("Failed to add notes");
		}

	}

	public void AddNote(WebDriver driver, String subject, String details, String NoteType, String Notestatus)
			throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		jse.executeScript("arguments[0].click();", ReservationPage.Add_Note_Reservation);
		// ReservationPage.Add_Note_Reservation.click();
		Wait.explicit_wait_xpath(OR.verify_Add_Notes_popup, driver);
		Wait.WaitForElement(driver, OR.Enter_Subject_Notes);
		ReservationPage.Enter_Subject_Notes.sendKeys(subject);
		Wait.WaitForElement(driver, OR.Enter_Subject_Notes);
		ReservationPage.Enter_Note_Details.sendKeys(details);
		new Select(ReservationPage.Select_Note_Type).selectByVisibleText(NoteType);
		/*
		 * if (ReservationPage.Check_Action_Required.isSelected()) {
		 * reservationLogger.info("Action Requited checkbox is already selected"
		 * ); } else { ReservationPage.Check_Action_Required.click(); }
		 */
		// new
		// Select(ReservationPage.Select_Notes_Status).selectByVisibleText(Notestatus);

		ReservationPage.Click_Save_Note.click();
		Wait.wait2Second();
		if (ReservationPage.Verify_Added_Notes.isDisplayed()) {
			reservationLogger.info("Created note Successfully");
		} else {
			reservationLogger.info("Failed to add notes");
		}

	}

	public void verifyNotes(WebDriver driver) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		WebElement Element = driver
				.findElement(By.xpath("//table[@data-bind='visible: NoteList().length >= 0']/tbody/tr[1]"));
		Wait.explicit_wait_visibilityof_webelement(Element, driver);
		try {
			if (driver.findElement(By.xpath("//table[@data-bind='visible: NoteList().length >= 0']/tbody/tr[1]"))
					.isDisplayed()) {

				reservationLogger.info(" Notes not Deleted ");
				Wait.wait5Second();
			}
		} catch (Exception e) {

			reservationLogger.info(" Notes not displayed in Reservation ");
			test_steps.add("Notes not displayed in Reservation");
		}

	}

	public void deleteNotesInReservation(WebDriver driver, String subject, ArrayList<String> test_steps) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		WebElement element = driver
				.findElement(By.xpath("(//a[.='" + subject + "']//following::td[@class='noteGrFx']/span[3])[1]"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", element);

		element.click();

		Wait.explicit_wait_elementToBeClickable(ReservationPage.reservation_Save, driver);
		reservationLogger.info(" Deleted Notes in Reservation");
		test_steps.add(" Deleted Notes in Reservation");
	}

	public String getAddedNotes(WebDriver driver, ExtentTest test) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.WaitForElement(driver, OR.addedNote);
		String addednote = ReservationPage.addedNote.getText();
		reservationLogger.info(" Added Note: " + addednote);
		test_steps.add("Added Note: " + addednote);
		Wait.wait5Second();
		// driver.findElement(By.xpath("//table[@data-bind='visible:
		// NoteList().length
		// >= 0']/tbody/tr[1]"));
		return addednote;

	}

	public void clickGuestInfo_1(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();",
		// ReservationPage.GuestInfo_1);
		// System.out.println("After");
		ReservationPage.GuestInfo_1.click();

		test_steps.add("Successfully clicked on Guest Info");
		reservationLogger.info("Successfully clicked on Guest Info");

	}

	public void AddNotes1(WebDriver driver, String subject, String details, String NoteType, String Notestatus)
			throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		jse.executeScript("arguments[0].click();", ReservationPage.Add_Note_Reservation);
		// ReservationPage.Add_Note_Reservation.click();
		Wait.wait2Second();
		Wait.explicit_wait_xpath(OR.verify_Add_Notes_popup, driver);
		ReservationPage.Enter_Subject_Notes.clear();
		ReservationPage.Enter_Subject_Notes.sendKeys(subject);
		ReservationPage.Enter_Note_Details.sendKeys(details);
		new Select(ReservationPage.Select_Note_Type).selectByVisibleText(NoteType);
		// new
		// Select(ReservationPage.Select_Notes_Status).selectByVisibleText(Notestatus);
		try {
			ReservationPage.Click_Save_Note.click();
			System.out.println("try");

		} catch (Exception e) {
			System.out.println("Catch");
			jse.executeScript("arguments[0].click();", ReservationPage.Click_Save_Note1);

		}
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_Added_Notes, driver);
		Utility.ScrollToElement(ReservationPage.Verify_Added_Notes, driver);
		if (ReservationPage.Verify_Added_Notes.isDisplayed()) {
			reservationLogger.info("Created note Successfully");
		} else {
			reservationLogger.info("Failed to add notes");
		}

	}

	/*
	 * public void GetReservationnumber(WebDriver driver) throws IOException {
	 * Elements_Reservation ReservationPage = new Elements_Reservation(driver);
	 * ReservationConfirmation =
	 * ReservationPage.Get_Confirmation_Number.getText();
	 * reservationLogger.info("ReservationConfirmation :" +
	 * ReservationConfirmation); try { BufferedWriter writer = new
	 * BufferedWriter(new FileWriter(".\\ConfirmationNumber.txt"));
	 * writer.write(ReservationConfirmation); writer.close(); } catch (Exception
	 * e) {
	 * 
	 * }
	 * 
	 * }
	 */

	/*
	 * public void clickCheckIn(WebDriver driver) throws InterruptedException{
	 * Elements_Reservation ReservationPage = new Elements_Reservation(driver);
	 * Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Checkin)
	 * ; //
	 * action.moveToElement(ReservationPage.Click_Checkin).doubleClick().build()
	 * .perform(); //Wait.wait1Second(); ReservationPage.Click_Checkin.click();
	 * reservationLogger.info("Clicked on CheckIn button");
	 * 
	 * Wait.explicit_wait_visibilityof_webelement(ReservationPage.
	 * roomAssignmentpopUpSelectButton);
	 * ReservationPage.roomAssignmentpopUpSelectButton.click();
	 * 
	 * 
	 * Wait.explicit_wait_visibilityof_webelement(ReservationPage.
	 * Click_Save_ReservationDetails);
	 * ReservationPage.Click_Save_ReservationDetails.click();
	 * 
	 * }
	 */

	public void AddNotes1(WebDriver driver, String subject, String details, String NoteType, String Notestatus,
			ArrayList<String> test_steps) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("window.scrollBy(0,250)", "");
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		jse.executeScript("arguments[0].click();", ReservationPage.Add_Note_Reservation);
		// ReservationPage.Add_Note_Reservation.click();
		Wait.wait2Second();
		Wait.explicit_wait_xpath(OR.verify_Add_Notes_popup, driver);
		ReservationPage.Enter_Subject_Notes.clear();
		ReservationPage.Enter_Subject_Notes.sendKeys(subject);
		ReservationPage.Enter_Note_Details.sendKeys(details);
		new Select(ReservationPage.Select_Note_Type).selectByVisibleText(NoteType);
		// new
		// Select(ReservationPage.Select_Notes_Status).selectByVisibleText(Notestatus);
		try {
			ReservationPage.Click_Save_Note.click();
			// System.out.println("try");

		} catch (Exception e) {
			System.out.println("Catch");
			jse.executeScript("arguments[0].click();", ReservationPage.Click_Save_Note1);

		}
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_Added_Notes, driver);
		Utility.ScrollToElement(ReservationPage.Verify_Added_Notes, driver);
		if (ReservationPage.Verify_Added_Notes.isDisplayed()) {
			reservationLogger.info("Created Note");
			test_steps.add("Created Note");
		} else {
			reservationLogger.info("Failed to add notes");
		}

	}

	public void Cancel_Reservation(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.Cancel_Reservation_Icon, driver);
		ReservationPage.Cancel_Reservation_Icon.click();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Cancel_Res_Popup, driver);
		if (ReservationPage.Cancel_Res_Popup.isDisplayed()) {
			ReservationPage.Cancel_Reason_Txtarea.sendKeys("Deletion Checking");
			Wait.wait1Second();
			ReservationPage.VoidRC_Chkbox_In_Popup.click();
			ReservationPage.Cancel_Res_Popup_Ok_Btn.click();

		}
		Wait.wait2Second();
		String res_status = new Select(ReservationPage.Reservation_Status).getFirstSelectedOption().getText();
		Assert.assertEquals(res_status, "Cancelled", "Failed to Cancel Reservation");

	}

	public ArrayList<String> CancelReservation(WebDriver driver, boolean checked, boolean CancellationFee,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.Cancel_Reservation_Icon, driver);
		ReservationPage.Cancel_Reservation_Icon.click();
		test_steps.add("Click Cancel Reservation Icon");
		Utility.app_logs.info("Click Cancel Reservation Icon");
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Cancel_Res_Popup, driver);
		assertTrue(ReservationPage.Cancel_Res_Popup.isDisplayed(), "Failed: Cancel Reservation Popup not Appeared");
		test_steps.add("Cancel Reservation Popup Appeared");

		Utility.app_logs.info("Cancel Reservation Popup Appeared");
		assertTrue(ReservationPage.Cancel_Reason_Txtarea.isDisplayed(), "Failed: Cancel Reason text box not Appeared");
		assertTrue(ReservationPage.VoidRC_Chkbox_In_Popup.isDisplayed(),
				"Failed: Void Room Charges CheckBox  not Appeared");
		assertTrue(ReservationPage.Cancel_Res_Popup_Cancel_Btn.isDisplayed(), "Failed: Cancel Button not Appeared");
		assertTrue(ReservationPage.Cancel_Res_Popup_Ok_Btn.isDisplayed(), "Failed: OK Button not Appeared");
		ReservationPage.Cancel_Reason_Txtarea.sendKeys("Deletion Checking");

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.VoidRC_Chkbox_In_Popup, driver);
		Utility.ScrollToElement(ReservationPage.VoidRC_Chkbox_In_Popup, driver);
		ReservationPage.VoidRC_Chkbox_In_Popup.click();
		Utility.app_logs.info("Click void room charges");
		Utility.ScrollToElement(ReservationPage.VoidRC_Chkbox_In_Popup, driver);
		if (checked) {
			if (!ReservationPage.VoidRC_Chkbox_In_Popup.isSelected()) {
				ReservationPage.VoidRC_Chkbox_In_Popup.click();
				test_steps.add("Click Void Room Charges CheckBox of Cancellation Popup");
				Utility.app_logs.info("Click Void Room Charges CheckBox of Cancellation Popup");
			}
			assertTrue(ReservationPage.VoidRC_Chkbox_In_Popup.isSelected(),
					"Failed: Void Room Charges CheckBox of Cancellation Popup is Not Selected");
			test_steps.add("Void Room Charges : Checked");
			reservationLogger.info("Void Room Charges : Checked");
			if (CancellationFee) {
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.Cancellation_CancellationFee, driver);
				if (!ReservationPage.Cancellation_CancellationFee.isSelected()) {
					ReservationPage.Cancellation_CancellationFee.click();
					test_steps.add("Click CancellationFee CheckBox of Cancellation Popup");
				}
				assertTrue(ReservationPage.Cancellation_CancellationFee.isSelected(),
						"Failed: Cancellation Fee CheckBox of Cancellation Popup is Not Selected");
				test_steps.add("Cancellation Fee : Checked");

				reservationLogger.info("Cancellation Fee : Checked");
			} else {
				if (ReservationPage.Cancellation_CancellationFee.isSelected()) {
					ReservationPage.Cancellation_CancellationFee.click();
					test_steps.add("Click Cancellation Fee CheckBox of Cancellation Popup");
				}
				assertTrue(!ReservationPage.Cancellation_CancellationFee.isSelected(),
						"Failed: Cancellation Fee CheckBox of Cancellation Popup is Selected");
				test_steps.add("Cancellation Fee : UnChecked");
				reservationLogger.info("Cancellation Fee : UnChecked");
			}
		} else {
			if (ReservationPage.VoidRC_Chkbox_In_Popup.isSelected()) {
				ReservationPage.VoidRC_Chkbox_In_Popup.click();
				test_steps.add("Click Void Room Charges CheckBox of Cancellation Popup");
			}
			assertTrue(!ReservationPage.VoidRC_Chkbox_In_Popup.isSelected(),
					"Failed: Void Room Charges CheckBox of NoShow Cancellation is Selected");
			test_steps.add("Void Room Charges : UnChecked");
			reservationLogger.info("Void Room Charges : UnChecked");
		}
		ReservationPage.Cancel_Res_Popup_Ok_Btn.click();
		test_steps.add("Click Ok Cancel Reservation Popup");
		return test_steps;
	}

	public void Close_Tab(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.closeReservation, driver);
		Wait.wait1Second();
		ReservationPage.closeReservation.click();
		Wait.wait2Second();
		if (ReservationPage.AlertForTab.isDisplayed()) {

			ReservationPage.AlertForTab_Yes_Btn.click();
		}
		Wait.wait1Second();
		assertTrue(ReservationPage.New_Reservation_Button.isDisplayed(), "Tab diidn't close");
	}

	public void Close_Tab_ResOpenFromTapeChart(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.closeReservation, driver);
		Wait.wait1Second();
		ReservationPage.closeReservation.click();
		Wait.wait2Second();
		if (ReservationPage.AlertForTab.isDisplayed()) {

			ReservationPage.AlertForTab_Yes_Btn.click();
		}
		Wait.wait1Second();
		WebElement tapechartpage = driver.findElement(By.xpath(OR.Tape_Chart_Grid1));
		assertTrue(tapechartpage.isDisplayed(), "Tab didn't close");
	}

	public void Save_Reservation_Pay_Deposit_Policy(WebDriver driver, String RoomClassName, String PaymentType,
			String CardName, String CCNumber, String CCExpiry, String CCVCode, String Authorizationtype,
			String ChangeAmount, String ChangeAmountValue, String traceData, String Deposit_percentage,
			ArrayList test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		float processedamount;
		float endingbalance;
		String GetEndingBalance = ReservationPage.Payment_Details_Folio_Balance.getText();
		String RemoveCurreny[] = GetEndingBalance.split(" ");
		endingbalance = Float.parseFloat(RemoveCurreny[1]);
		reservationLogger.info(endingbalance);
		Wait.explicit_wait_elementToBeClickable(ReservationPage.Click_Save_ReservationDetails, driver);
		Actions action = new Actions(driver);
		action.moveToElement(ReservationPage.Click_Save_ReservationDetails).click().build().perform();
		// ReservationPage.Click_Save_ReservationDetails.click();
		WebDriverWait wait = new WebDriverWait(driver, 120);

		wait.until(ExpectedConditions.visibilityOf((ReservationPage.Click_Continue_Deposit)));
		if (ReservationPage.Verify_Depos_policy.isDisplayed()) {
			ReservationPage.Click_Continue_Deposit.click();

			wait.until(ExpectedConditions.visibilityOf((ReservationPage.Payment_Popup)));
			if (ReservationPage.Payment_Popup.isDisplayed()) {
				reservationLogger.info("This Reservation has deposit ploicy");
				ReservationFolio Payment = new ReservationFolio();
				processedamount = Payment.TestPaymentPopup(driver, PaymentType, CardName, CCNumber, CCExpiry, CCVCode,
						Authorizationtype, ChangeAmount, ChangeAmountValue, traceData, test_steps);

				float expected_amount = ((Float.parseFloat(Deposit_percentage) * endingbalance) / 100);

				Assert.assertEquals(Math.round(processedamount), Math.round(expected_amount),
						"Calculating " + Deposit_percentage + "% of ending balance " + endingbalance
								+ ",expected amount is " + expected_amount + ", processed amount is "
								+ processedamount);

			} else {
				reservationLogger.error("Payment popup for Deposit Policy is not showing up");
			}
		} else {
			reservationLogger.error("Deposit Policy doesnt exist");
		}

	}

	public void Associate_CCAccount(WebDriver driver, String accountname) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.Enter_Account, driver);
		ReservationPage.Enter_Account.clear();
		ReservationPage.Enter_Account.sendKeys(accountname);
		Wait.explicit_wait_visibilityof_webelement(
				driver.findElement(By.xpath("//a[contains(@title,'" + accountname + "')]")), driver);
		driver.findElement(By.xpath("//a[contains(@title,'" + accountname + "')]")).click();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Acc_Picker_Confirm, driver);
		ReservationPage.Acc_Picker_Confirm_Continue_Btn.click();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Associated_AccountName, driver);
		if (ReservationPage.Associated_AccountName.getText().equalsIgnoreCase(accountname)) {
			reservationLogger.info("Account associated sucessfully to Reservation");
		} else {

			reservationLogger.error("Failed! to associate Account to Reservation");
		}
	}

	public void Add_Payment_Info(WebDriver driver, String saluation, String FirstName, String LastName, String Line1,
			String Line2, String Line3, String City, String Country, String State, String Postalcode,
			String Phonenumber, String alternativenumber, String Email, String PaymentMethod, String AccountNumber,
			String ExpiryDate, String BillingNotes) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.Click_Show_PaymentInfo, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.Click_Show_PaymentInfo);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Folio_Billing_Info_Popup, driver);
		// Wait.wait2Second();
		new Select(ReservationPage.Select_Salutation_PaymentInfo_Popup).selectByVisibleText(saluation);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Enter_First_Name_PaymentInfo_Popup, driver);
		ReservationPage.Enter_First_Name_PaymentInfo_Popup.click();
		ReservationPage.Enter_First_Name_PaymentInfo_Popup.sendKeys(FirstName);
		ReservationPage.Enter_Last_Name_PaymentInfo_Popup.sendKeys(LastName);
		ReservationPage.Enter_Line1_PaymentInfo_Popup.sendKeys(Line1);
		ReservationPage.Enter_Line2_PaymentInfo_Popup.sendKeys(Line2);
		ReservationPage.Enter_Line3_PaymentInfo_Popup.sendKeys(Line3);
		ReservationPage.Enter_City_PaymentInfo_Popup.sendKeys(City);
		new Select(ReservationPage.Select_Country_PaymentInfo_Popup).selectByVisibleText(Country);
		new Select(ReservationPage.Select_State_PaymentInfo_Popup).selectByVisibleText(State);
		ReservationPage.Enter_Postal_Code_PaymentInfo_Popup.sendKeys(Postalcode);
		ReservationPage.Enter_Phone_PaymentInfo_Popup.sendKeys(Phonenumber);
		ReservationPage.Enter_AltPhone_PaymentInfo_Popup.sendKeys(alternativenumber);
		ReservationPage.Enter_Email_PaymentInfo_Popup.sendKeys(Email);
		new Select(ReservationPage.Select_Payment_Method_PaymentInfo_Popup).selectByVisibleText(PaymentMethod);
		ReservationPage.Enter_Account_Number_PaymentInfo_Popup.sendKeys(AccountNumber);
		ReservationPage.Enter_CardExpiryDate_PaymentInfo_Popup.sendKeys(ExpiryDate);
		ReservationPage.Enter_BillingNotes_PaymentInfo_Popup.sendKeys(BillingNotes);
		ReservationPage.Save_Btn_PaymentInfo_Popup.click();
		Wait.waitForElementToBeGone(driver, ReservationPage.Save_Btn_PaymentInfo_Popup, 60);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Payment_Info_Textarea, driver);
		reservationLogger.info(ReservationPage.Payment_Info_Textarea.getAttribute("value"));
	}

	public void Verify_Line_Item_Details(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.Include_Taxes_in_Line_Items_Checkbox, driver);
		if (ReservationPage.Include_Taxes_in_Line_Items_Checkbox.isSelected()) {
			reservationLogger.info("Taxes are included in line Items by defult ");
		} else {
			ReservationPage.Include_Taxes_in_Line_Items_Checkbox.click();
			reservationLogger.info("Taxes are not included in line Items by defult so checking check box");

		}
		String Roomcharge = ReservationPage.First_RC_Line_Item_Amount.getText();
		reservationLogger.info("First RC Line Item Amount -- " + Roomcharge);
		String RemoveCurreny1[] = Roomcharge.split(" ");
		Float roomcharge_amount = Float.parseFloat(RemoveCurreny1[1]);
		reservationLogger.info(roomcharge_amount);
		Utility.ScrollToElement(ReservationPage.First_RC_Line_Item_Desc, driver);
		ReservationPage.First_RC_Line_Item_Desc.click();

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Item_Details_Popup, driver);
		String Total_Roomcharge = ReservationPage.Total_Charges_In_Item_Details_Popup.getText();
		reservationLogger.info("Total_Roomcharges in line item details popup -- " + Total_Roomcharge);
		String RemoveCurreny2[] = Roomcharge.split(" ");
		Float total_roomcharge_amount = Float.parseFloat(RemoveCurreny2[1]);
		reservationLogger.info(total_roomcharge_amount);
		Assert.assertEquals(roomcharge_amount, total_roomcharge_amount);

		try {
			Wait.wait2Second();
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Popup_LineItemDescription, driver);
			Utility.ScrollToElement(ReservationPage.Popup_LineItemDescription, driver);
			ReservationPage.Popup_LineItemDescription.click();
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.RateDetailsPopup, driver);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.RateNameLabel, driver);
			reservationLogger.info("Tax Details Popup is displaying, when click on link in line item details Popup");
			ReservationPage.Rate_Details_Popup_Cancel_Btn.click();

		} catch (Exception e) {
			Assert.fail("Tax Details Popup is not displaying, when click on link in line item details Popup");
			reservationLogger
					.error("Tax Details Popup is not displaying, when click on link in line item details Popup");

		}

	}

	public ArrayList<String> marketingInfo(WebDriver driver, ExtentTest test, String MarketSegment, String Referral,
			String Travel_Agent, String ExtReservation, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", ReservationPage.Reservation_market_Segment);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Reservation_market_Segment, driver);
		new Select(ReservationPage.Reservation_market_Segment).selectByVisibleText(MarketSegment);
		test_steps.add("Select market segment : " + MarketSegment);
		test.log(LogStatus.PASS, "Select Reservation market segment : " + MarketSegment);
		new Select(ReservationPage.Reservation_Referral).selectByVisibleText(Referral);
		test.log(LogStatus.PASS, "SelectReservation referral : " + Referral);
		test_steps.add("Select Referral : " + Referral);
		try {
			ReservationPage.Enter_Travel_Agent.sendKeys(Travel_Agent);
			// test_steps.add("Tavel Agent : " + Travel_Agent);
			// test_steps.add("Select Travel Agent : " + Travel_Agent);
		} catch (Exception e) {

		}
		// ReservationPage.Enter_Ext_Reservation.sendKeys(ExtReservation);
		// test_steps.add("Ext Reservation : " + ExtReservation);
		return test_steps;
	}

	// @Override
	public ArrayList<String> contactInformation(WebDriver driver, ArrayList test_steps, String saluation,
			String FirstName, String LastName, String Address, String Line1, String Line2, String Line3, String City,
			String Country, String State, String Postalcode, String Phonenumber, String alternativenumber, String Email,
			String Account, String IsTaxExempt, String TaxEmptext) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.Reservation_CreateGuestProfile);
		if (ReservationPage.Reservation_CreateGuestProfile.isSelected()) {
			Wait.wait1Second();
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ReservationPage.Reservation_CreateGuestProfile);
			ReservationPage.Reservation_CreateGuestProfile.click();
		}

		new Select(ReservationPage.Select_Saluation).selectByVisibleText(saluation);
		ReservationPage.Enter_First_Name.clear();
		ReservationPage.Enter_Last_Name.clear();
		ReservationPage.Enter_Address_Search.clear();
		ReservationPage.Enter_Line1.clear();
		ReservationPage.Enter_Line2.clear();
		ReservationPage.Enter_Line3.clear();
		ReservationPage.Enter_City.clear();
		ReservationPage.Enter_Postal_Code.clear();
		ReservationPage.Enter_Phone_Number.clear();
		ReservationPage.Enter_Alt_Phn_number.clear();
		ReservationPage.Enter_email.clear();

		ReservationPage.Enter_First_Name.sendKeys(FirstName);
		test_steps.add("Enter First name : " + FirstName);
		ReservationPage.Enter_Last_Name.sendKeys(LastName);
		test_steps.add("Enter Last name : " + LastName);
		ReservationPage.Enter_Address_Search.sendKeys(Address);
		test_steps.add("Enter Address : " + Address);
		ReservationPage.Enter_Line1.sendKeys(Line1);
		test_steps.add("Enter Line1 : " + Line1);
		ReservationPage.Enter_Line2.sendKeys(Line2);
		test_steps.add("Enter Line2 : " + Line2);
		ReservationPage.Enter_Line3.sendKeys(Line3);
		test_steps.add("Enter Line3 : " + Line3);
		ReservationPage.Enter_City.sendKeys(City);
		test_steps.add("Enter City : " + City);
		new Select(ReservationPage.Select_Country).selectByVisibleText(Country);
		test_steps.add("Select Country : " + Country);
		new Select(ReservationPage.Select_State).selectByVisibleText(State);
		test_steps.add("Select State : " + State);
		ReservationPage.Enter_Postal_Code.sendKeys(Postalcode);
		test_steps.add("Enter PostalCode : " + Postalcode);
		ReservationPage.Enter_Phone_Number.sendKeys(Phonenumber);
		test_steps.add("Enter Phone number : " + Phonenumber);
		ReservationPage.Enter_Alt_Phn_number.sendKeys(alternativenumber);
		test_steps.add("Enter Alternate Number : " + alternativenumber);
		ReservationPage.Enter_email.sendKeys(Email);
		test_steps.add("Enter Email : " + Email);
		if (Account.equals("")) {
			Utility.app_logs.info("Account is empty : " + Account);
		} else {
			try {
				VerifyAccount_AutoSuggestion(driver, Account);
				test_steps.add("Enter Account : " + Account);
			} catch (Exception e) {

				reservationLogger.info("Account");
				e.printStackTrace();
				assertTrue(false);
			}
		}
		if (IsTaxExempt.equals("Yes")) {
			if (ReservationPage.Check_IsTaxExempt.isSelected()) {

				Wait.WaitForElement(driver, OR.Enter_TaxExemptId);
				ReservationPage.Enter_TaxExemptId.sendKeys(TaxEmptext);
				test_steps.add("Enter Tax Exempt : " + TaxEmptext);
			} else {
				Wait.WaitForElement(driver, OR.Check_IsTaxExempt);
				ReservationPage.Check_IsTaxExempt.click();
				test_steps.add("click Tax Exempt");
				reservationLogger.info("click Tax Exempt");
				ReservationPage.Enter_TaxExemptId.sendKeys(TaxEmptext);
				test_steps.add("Enter Tax Exempt : " + TaxEmptext);
			}
		}
		return test_steps;
	}

	public void billingInformation(WebDriver driver, ExtentTest test1, String PaymentMethod, String AccountNumber,
			String ExpiryDate, String BillingNotes) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait2Second();
		Wait.WaitForElement(driver, OR.Select_Payment_Method);
		new Select(ReservationPage.Select_Payment_Method).selectByVisibleText(PaymentMethod);
		ReservationPage.Select_Payment_Method.sendKeys(Keys.TAB);

		/*
		 * if (PaymentMethod.equalsIgnoreCase("MC") ||
		 * PaymentMethod.equalsIgnoreCase("Amex") ||
		 * PaymentMethod.equalsIgnoreCase("Discover") ||
		 * PaymentMethod.equalsIgnoreCase("Visa")) {
		 */
		ReservationPage.Enter_Account_Number.sendKeys(AccountNumber);
		Wait.WaitForElement(driver, OR.Enter_Exp_Card);
		ReservationPage.Enter_Exp_Card.sendKeys(ExpiryDate);
		ReservationPage.Enter_Billing_Note.sendKeys(BillingNotes);
		// }
		Thread.sleep(15000);
	}

	public ArrayList<String> saveReservation(WebDriver driver, ExtentTest test1, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Thread.sleep(5000);
		double waittime = 0.12;
		long startTime = System.currentTimeMillis();
		Wait.waitUntilPresenceOfElementLocated(OR.Click_Save_ReservationDetails, driver);
		ReservationPage.Click_Save_ReservationDetails.click();
		test_steps.add("Successfully clicked on save reservation");
		reservationLogger.info("Successfully clicked on save reservation");

		Wait.wait3Second();
		try {
			if (ReservationPage.Verify_Depos_policy.isDisplayed()) {
				ReservationPage.Click_Without_Deposit.click();
				test_steps.add("Successfully clicked with out deposit");
				reservationLogger.info("Successfully clicked with out deposit");
			}
		} catch (Exception ne) {

		}
		try {
			if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
				Wait.wait3Second();
				String getTotasterTitle_ReservationSucess = ReservationPage.Toaster_Title.getText();
				String getToastermessage_ReservationSucess = ReservationPage.Toaster_Message.getText();
				Wait.explicit_wait_10sec(ReservationPage.CheckIn_Button, driver);
				Assert.assertEquals(getTotasterTitle_ReservationSucess, "Reservation Saved");

				long endTime = System.currentTimeMillis();
				double totalTime = (endTime - startTime);
				double TotalTimeinsecs = totalTime / 1000;
				double ActualTime = TotalTimeinsecs - waittime - 3;
				if (getToastermessage_ReservationSucess.endsWith("has been saved successfully"))
					;
			}
		} catch (Exception e) {

		}
		return test_steps;
		// Wait.wait5Second();
	}

	public void clickBook(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.Click_Book_Reservation.click();
		Wait.wait3Second();
	}

	public void saveReservationQuote(WebDriver driver, ExtentTest test) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		double waittime = 0.12;
		long startTime = System.currentTimeMillis();
		ReservationPage.Click_Save_ReservationDetails.click();
		reservationLogger.info("Successfully clicked on save reservation");

		Wait.wait2Second();
		try {
			if (ReservationPage.Verify_Depos_policy.isDisplayed()) {
				ReservationPage.Click_Without_Deposit.click();
				reservationLogger.info("Successfully clicked out deposit");

			}
		} catch (Exception ne) {

		}
		try {
			if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
				String getTotasterTitle_ReservationSucess = ReservationPage.Toaster_Title.getText();
				String getToastermessage_ReservationSucess = ReservationPage.Toaster_Message.getText();
				Assert.assertEquals(getTotasterTitle_ReservationSucess, "Reservation Saved");

				long endTime = System.currentTimeMillis();
				double totalTime = (endTime - startTime);
				// reservationLogger.info(totalTime + " in Millsecs");
				double TotalTimeinsecs = totalTime / 1000;
				double ActualTime = TotalTimeinsecs - waittime - 3;
				// reservationLogger.info(ActualTime + " in secs");

				if (getToastermessage_ReservationSucess.endsWith("has been saved successfully"))
					;
			}
		} catch (Exception e) {

		}
		Wait.wait2Second();

		try {

			Select sel = new Select(driver.findElement(By.xpath(OR.Get_QuoteReservation_Status)));

			WebElement ele = sel.getFirstSelectedOption();

			String str = ele.getText();
			reservationLogger.info(str);
			assertTrue(str.equalsIgnoreCase("Reserved") || str.equalsIgnoreCase("Confirmed")
					|| str.equalsIgnoreCase("On Hold"));
			reservationLogger.info("Reservation status : " + str);

		} catch (Exception e) {
			e.printStackTrace();
			reservationLogger.info("Reservation status is not Reserved/Confirmed/On Hold");
		}
	}

	public void manualEmail(WebDriver driver, ExtentTest test, String Email, String Attachment)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.Click_Email_icon.click();
		test.log(LogStatus.PASS, "Click on Email icon");
		Wait.explicit_wait_xpath(OR.Verify_Send_Email_Popup, driver);
		Wait.explicit_wait_absenceofelement(OR.Verify_loading_popup_mailContentPopUp, driver);
		Wait.wait3Second();
		String GetEmailid = ReservationPage.Get_email_Id.getText();
		// reservationLogger.info(GetEmailid + "" + GetEmailid);
		if (GetEmailid.equals("dinesh.ganganaboina@gmail.com")) {

		} else {
			ReservationPage.Get_email_Id.clear();
			Wait.wait3Second();
			ReservationPage.Get_email_Id.sendKeys(Email);
			test.log(LogStatus.PASS, "Enter  Email : " + Email);
		}
		new Select(ReservationPage.Select_Attachment).selectByVisibleText(Attachment);
		test.log(LogStatus.PASS, "Select Attachment : " + Attachment);
		Wait.wait3Second();
		ReservationPage.Click_Send_Email.click();
		test.log(LogStatus.PASS, "Click on send Email");
		Wait.wait3Second();
	}

	public ArrayList<String> marketingInfo(WebDriver driver, ExtentTest test, String MarketSegment, String Referral,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.Reservation_market_Segment, driver);
		jse.executeScript("arguments[0].scrollIntoView();", ReservationPage.Reservation_market_Segment);
		new Select(ReservationPage.Reservation_market_Segment).selectByVisibleText(MarketSegment);
		test_steps.add("Successfully selected the market segment : " + MarketSegment);

		new Select(ReservationPage.Reservation_Referral).selectByVisibleText(Referral);
		test_steps.add("Successfully selected the referral : " + Referral);
		return test_steps;
	}

	public void marketingInfo_2(WebDriver driver, ExtentTest test1, String MarketSegment, String Referral)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Reservation_market_Segment_2, driver);
		jse.executeScript("arguments[0].scrollIntoView();", ReservationPage.Reservation_market_Segment_2);
		new Select(ReservationPage.Reservation_market_Segment_2).selectByVisibleText(MarketSegment);
		Utility.app_logs.info("Successfully selected the maraket segment : " + MarketSegment);

		new Select(ReservationPage.Reservation_Referral_2).selectByVisibleText(Referral);
		Utility.app_logs.info("Successfully selected the referral : " + Referral);
	}

	public void pickTravelAgentAccount(WebDriver driver, String AccountName) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.Enter_TravelAgen_Account.sendKeys(AccountName);
		Wait.wait5Second();
		List<WebElement> element = driver.findElements(By.className("span-nomber"));
		element.get(0).click();
		Wait.wait2Second();
		ReservationPage.AccountPicker_Continue_Button.click();
		Wait.wait2Second();

	}

	public void pickTravelAgentAccount1(WebDriver driver, String AccountName) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.Enter_TravelAgen_Account.sendKeys(AccountName);
		Wait.wait5Second();
		driver.findElement(By.xpath("(//span[@class='span-nomber'])[1]")).click();
		;
		Wait.wait2Second();
		ReservationPage.AccountPicker_Continue_Button.click();
		Wait.wait2Second();

	}

	public void pickGuestHistory(WebDriver driver, String GuestProfile) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.Enter_GuestProfile_Name.sendKeys(GuestProfile);
		Wait.wait5Second();
		ReservationPage.Select_GuestProfile.get(2).click();
		Wait.waitUntilPresenceOfElementLocated(OR.GuestProfile_Popup, driver);
		assertEquals(ReservationPage.GuestProfile_Popup.getText(), "Please select the content for the Reservation",
				"Guest profile popup does not display");
		ReservationPage.GuestProfile_Popup_Continue.click();
		Wait.wait2Second();
	}

	public ArrayList<String> contactInformation(WebDriver driver, ExtentTest test1, String FirstName, String LastName,
			String Line1, String City, String Country, String State, String Postalcode, String Phonenumber,
			ArrayList<String> getTest_Steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.Enter_First_Name, driver);
		Utility.ScrollToElement(ReservationPage.Enter_First_Name, driver);
		ReservationPage.Enter_First_Name.clear();
		ReservationPage.Enter_Last_Name.clear();
		ReservationPage.Enter_Address_Search.clear();
		ReservationPage.Enter_Line1.clear();
		ReservationPage.Enter_Line2.clear();
		ReservationPage.Enter_Line3.clear();
		ReservationPage.Enter_City.clear();
		ReservationPage.Enter_Postal_Code.clear();
		ReservationPage.Enter_Phone_Number.clear();
		ReservationPage.Enter_Alt_Phn_number.clear();
		ReservationPage.Enter_email.clear();
		ReservationPage.Enter_First_Name.sendKeys(FirstName);
		reservationLogger.info("Successfully entered the first name : " + FirstName);
		ReservationPage.Enter_Last_Name.sendKeys(LastName);
		reservationLogger.info("Successfully entered the last name : " + LastName);
		ReservationPage.Enter_Line1.sendKeys(Line1);
		reservationLogger.info("Successfully entered the Address Line1 : " + Line1);
		ReservationPage.Enter_City.sendKeys(City);
		reservationLogger.info("Successfully entered the City : " + City);
		new Select(ReservationPage.Select_Country).selectByVisibleText(Country);
		reservationLogger.info("Successfully selected the Country : " + Country);
		new Select(ReservationPage.Select_State).selectByVisibleText(State);
		reservationLogger.info("Successfully selected the state : " + State);
		ReservationPage.Enter_Postal_Code.sendKeys(Postalcode);
		reservationLogger.info("Successfully entered the postal code : " + Postalcode);
		ReservationPage.Enter_Phone_Number.sendKeys(Phonenumber);
		reservationLogger.info("Successfully entered the phone number : " + Phonenumber);
		ReservationPage.Enter_email.sendKeys("qa-DG@innroad.com");
		return getTest_Steps;

	}

	public ArrayList<String> billingInformation(WebDriver driver, ExtentTest test1, String PaymentMethod,
			String AccountNumber, String ExpiryDate, String BillingNotes, ArrayList<String> getTest_Steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait2Second();
		Wait.WaitForElement(driver, OR.Select_Payment_Method);
		new Select(ReservationPage.Select_Payment_Method).selectByVisibleText(PaymentMethod);
		ReservationPage.Select_Payment_Method.sendKeys(Keys.TAB);

		/*
		 * if (PaymentMethod.equalsIgnoreCase("MC") ||
		 * PaymentMethod.equalsIgnoreCase("Amex") ||
		 * PaymentMethod.equalsIgnoreCase("Discover") ||
		 * PaymentMethod.equalsIgnoreCase("Visa")) {
		 */
		ReservationPage.Enter_Account_Number.sendKeys(AccountNumber);
		Wait.WaitForElement(driver, OR.Enter_Exp_Card);
		ReservationPage.Enter_Exp_Card.sendKeys(ExpiryDate);
		ReservationPage.Enter_Billing_Note.sendKeys(BillingNotes);
		// }
		// Thread.sleep(15000);
		return getTest_Steps;

	}

	public ArrayList<String> contactInformation(WebDriver driver, ExtentTest test1, String FirstName, String LastName,
			String Line1, String City, String Country, String State, String Postalcode, String Phonenumber)
			throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.Enter_First_Name, driver);
		Utility.ScrollToElement(ReservationPage.Enter_First_Name, driver);
		Wait.WaitForElement(driver, OR.Reservation_CreateGuestProfile);
		if (ReservationPage.Reservation_CreateGuestProfile.isSelected()) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ReservationPage.Reservation_CreateGuestProfile);
			ReservationPage.Reservation_CreateGuestProfile.click();
		}

		ReservationPage.Enter_First_Name.clear();
		ReservationPage.Enter_Last_Name.clear();
		ReservationPage.Enter_Address_Search.clear();
		ReservationPage.Enter_Line1.clear();
		ReservationPage.Enter_Line2.clear();
		ReservationPage.Enter_Line3.clear();
		ReservationPage.Enter_City.clear();
		ReservationPage.Enter_Postal_Code.clear();
		ReservationPage.Enter_Phone_Number.clear();
		ReservationPage.Enter_Alt_Phn_number.clear();
		ReservationPage.Enter_email.clear();
		ReservationPage.Enter_First_Name.sendKeys(FirstName);
		reservationLogger.info("Successfully entered the first name : " + FirstName);
		test_steps.add("Successfully entered the first name : " + FirstName);
		ReservationPage.Enter_Last_Name.sendKeys(LastName);
		reservationLogger.info("Successfully entered the last name : " + LastName);
		test_steps.add("Successfully entered the last name : " + LastName);
		ReservationPage.Enter_Line1.sendKeys(Line1);
		reservationLogger.info("Successfully entered the Address Line1 : " + Line1);
		test_steps.add("Successfully entered the Address Line1 : " + Line1);
		ReservationPage.Enter_City.sendKeys(City);
		reservationLogger.info("Successfully entered the City : " + City);
		test_steps.add("Successfully entered the City : " + City);
		new Select(ReservationPage.Select_Country).selectByVisibleText(Country);
		reservationLogger.info("Successfully selected the Country : " + Country);
		test_steps.add("Successfully selected the Country : " + Country);
		new Select(ReservationPage.Select_State).selectByVisibleText(State);
		reservationLogger.info("Successfully selected the state : " + State);
		test_steps.add("Successfully selected the state : " + State);
		ReservationPage.Enter_Postal_Code.sendKeys(Postalcode);
		reservationLogger.info("Successfully entered the postal code : " + Postalcode);
		test_steps.add("Successfully entered the postal code : " + Postalcode);
		ReservationPage.Enter_Phone_Number.sendKeys(Phonenumber);
		reservationLogger.info("Successfully entered the phone number : " + Phonenumber);
		test_steps.add("Successfully entered the phone number : " + Phonenumber);
		ReservationPage.Enter_email.sendKeys("email@gmail.com");
		reservationLogger.info("Successfully entered email");
		test_steps.add("Successfully entered email");
		return test_steps;
	}

	public ArrayList<String> contactInformation_taxExempt(WebDriver driver, ExtentTest test1, String FirstName,
			String LastName, String Line1, String City, String Country, String State, String Postalcode,
			String Phonenumber) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.Enter_First_Name, driver);
		Utility.ScrollToElement(ReservationPage.Enter_First_Name, driver);
		Wait.WaitForElement(driver, OR.Reservation_CreateGuestProfile);
		if (ReservationPage.Reservation_CreateGuestProfile.isSelected()) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ReservationPage.Reservation_CreateGuestProfile);
			ReservationPage.Reservation_CreateGuestProfile.click();
		}

		ReservationPage.Enter_First_Name.clear();
		ReservationPage.Enter_First_Name.sendKeys(FirstName);
		reservationLogger.info("Successfully entered the first name : " + FirstName);
		test_steps.add("Successfully entered the first name : " + FirstName);
		return test_steps;
	}

	public ArrayList<String> roomAssignment(WebDriver driver, ExtentTest test1, String Nights, String Adults,
			String Children, String CheckorUncheckAssign, String RoomClassName, String RoomClassName2,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.WaitForElement(driver, OR.Click_RoomPicker);
		ReservationPage.Click_RoomPicker.click();
		test_steps.add("Successfully clicked on Rooms Picker");
		reservationLogger.info("Successfully clicked on Rooms Picker");

		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		// Wait.wait3Second();
		/*
		 * try { new Select(ReservationPage.Select_property_RoomAssign).
		 * selectByVisibleText(PropertyName); } catch(Exception e) { new
		 * Select(ReservationPage.Select_property_RoomAssign2).
		 * selectByVisibleText(PropertyName); }
		 */
		// Wait.wait5Second();

		Wait.WaitForElement(driver, OR.Click_Arrive_Datepicker);
		ReservationPage.Click_Arrive_Datepicker.click();
		test_steps.add("Successfully clicked on arrival date");
		reservationLogger.info("Successfully clicked on arrival date");

		Wait.WaitForElement(driver, OR.Click_Today);
		ReservationPage.Click_Today.click();
		test_steps.add("Successfully clicked on Today");
		reservationLogger.info("Successfully clicked on Today");

		Wait.WaitForElement(driver, OR.Enter_Nigts);
		ReservationPage.Enter_Nigts.clear();
		ReservationPage.Enter_Nigts.sendKeys(Nights);
		test_steps.add("Successfully entered the nights : " + Nights);
		ReservationPage.Enter_Adults.clear();
		ReservationPage.Enter_Adults.sendKeys(Adults);
		test_steps.add("Successfully entered the audlts : " + Adults);
		ReservationPage.Enter_Children.clear();
		ReservationPage.Enter_Children.sendKeys(Children);
		test_steps.add("Successfully entered the childrens : " + Children);
		// ReservationPage.Enter_Rate_Promocode.sendKeys(RatepromoCode);

		// if (!ReservationPage.Check_Split_Rooms.isSelected()) {
		// ReservationPage.Check_Split_Rooms.click();
		// test_steps.add("Successfully clicked on split rooms");
		// reservationLogger.info("Successfully clicked on split rooms");
		// }

		if (ReservationPage.Check_Assign_Room.isSelected()) {
			// ReservationPage.Check_Assign_Room.click();
			ReservationPage.Click_Search.click();
			test_steps.add("Successfully clicked on assign rooms");
		} else {
			if (CheckorUncheckAssign.equals("Yes")) {
				ReservationPage.Check_Assign_Room.click();
				test_steps.add("Successfully clicked on assign rooms");
				reservationLogger.info("Successfully clicked on assign rooms");

				ReservationPage.Click_Search.click();
				test_steps.add("Successfully clicked on search");
			} else {

				ReservationPage.Click_Search.click();
				test_steps.add("Successfully clicked on search");
				reservationLogger.info("Successfully clicked on search");
			}
		}

		Thread.sleep(4000);

		int count = 1;
		while (Integer.parseInt(Nights) >= count) {
			Wait.WaitForElement(driver,
					"//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr[" + count
							+ "]/td[2]/select");
			WebElement ele = driver.findElement(By
					.xpath("//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr["
							+ count + "]/td[2]/select"));

			Wait.WaitForElement(driver,
					"//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr[" + count
							+ "]/td[3]/select");
			WebElement ele1 = driver.findElement(By
					.xpath("//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr["
							+ count + "]/td[3]/select"));
			Select sel = new Select(ele);

			if (count == 1) {
				// System.out.println(RoomClassName);
				sel.selectByVisibleText(RoomClassName);
				test_steps.add("Successfully selected the room class : " + RoomClassName);
				reservationLogger.info("Successfully selected the room class : " + RoomClassName);

				sel = new Select(ele1);
				java.util.List<WebElement> options = sel.getOptions();
				int roomCount = 0;
				for (WebElement item : options) {
					// System.out.println(item.getText());
					if (roomCount == 1) {
						sel.selectByIndex(1);
						test_steps.add("Successfully selected the room number : " + item.getText());
						reservationLogger.info("Successfully selected the room number : " + item.getText());

						break;
					}
					roomCount++;
				}

			} else {
				sel.selectByVisibleText(RoomClassName2);
				test_steps.add("Successfully selected the room class : " + RoomClassName2);
				reservationLogger.info("Successfully selected the room class : " + RoomClassName2);

				sel = new Select(ele1);

				java.util.List<WebElement> options = sel.getOptions();
				int roomCount = 0;
				for (WebElement item : options) {
					// System.out.println(item.getText());
					if (roomCount == 1) {
						sel.selectByIndex(1);
						test_steps.add("Successfully selected the room number : " + item.getText());
						reservationLogger.info("Successfully selected the room number : " + item.getText());
						break;
					}
					roomCount++;
				}
			}
			count++;
		}
		ReservationPage.Click_Select.click();
		test_steps.add("Successfully clicked on select");
		try {
			Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
		} catch (Exception e) {
		}
		if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
			ReservationPage.Click_Continue_RuleBroken_Popup.click();
			test_steps.add("Successfully clicked on rule brocken pop up");
		} else {
			reservationLogger.info("Satisfied Rules");
		}
		try {
			if (ReservationPage.click_Select_Button_RoomChargesChanged.isDisplayed()) {
				ReservationPage.click_Select_Button_RoomChargesChanged.click();
			}
		} catch (Exception e) {
			System.out.println("RoomCharged changed not displayed");
		}
		try {
			if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
				System.out.println("Toaster displayed");
				String getTotasterTitle = ReservationPage.Toaster_Title.getText();
				String getToastermessage = ReservationPage.Toaster_Message.getText();
				reservationLogger.info(getTotasterTitle + " " + getToastermessage);
				Assert.assertEquals(getTotasterTitle, "Room assignment has changed.");
				Assert.assertEquals(getToastermessage, "Room assignment has changed.");
			}
		} catch (Exception e) {
			System.out.println("Room assignment changed not displayed");
		}
		Wait.wait2Second();
		Wait.WaitForElement(driver, OR.Get_RoomClass_Status);
		// String getPropertyName = ReservationPage.Get_Property_Name.getText();
		String getRoomclassName_status = ReservationPage.Get_RoomClass_Status.getText();
		reservationLogger.info(getRoomclassName_status);
		// Assert.assertEquals(getPropertyName,PropertyName );
		String getRoomclassName[] = getRoomclassName_status.split(" ");
		// Assert.assertEquals(getRoomclassName[0],RoomClassName );
		if (CheckorUncheckAssign.equals("Yes")) {

		} else {
			// Assert.assertEquals(getRoomclassName[3], "Unassigned");
			Assert.assertEquals(getRoomclassName.length - 1, "Unassigned");
		}
		return test_steps;

	}

	public void clickGuestInfo(WebDriver driver, ExtentTest test1) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.GuestInfo, driver);
		Utility.ScrollToElement(ReservationPage.GuestInfo, driver);
		try {
			ReservationPage.GuestInfo.click();
		} catch (Exception e) {
			Thread.sleep(1000);
			Utility.ScrollToElement(ReservationPage.GuestInfo, driver);
			try {
				ReservationPage.GuestInfo.click();
			} catch (Exception ee) {
				ReservationPage.GuestInfo_1.click();

			}
		}
		// test_steps.add("Successfully clicked on Guest Info");
		reservationLogger.info("Successfully clicked on Guest Info");
	}

	public ArrayList<String> clickGuestInfo_1(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();",
		// ReservationPage.GuestInfo_1);
		// System.out.println("After");
		ReservationPage.GuestInfo_1.click();

		test_steps.add("Successfully clicked on Guest Info");
		reservationLogger.info("Successfully clicked on Guest Info");
		return test_steps;
	}

	public ArrayList<String> getNotesCreated(WebDriver driver, ExtentTest test1, ArrayList<String> test_steps) {

		int count = driver.findElements(By.xpath(OR.Get_Notes)).size();
		String str = null;

		boolean isNotesCreated = false;
		for (int i = 3; i <= count; i++) {

			// str = "//table[@class='table table-striped table-bordered
			// table-hover resGrid1']/tbody/tr[" + i
			// + "]/td[3]/a";
			str = "(//table[@class='table table-striped table-bordered table-hover resGrid1'])[2]/tbody/tr[" + i
					+ "]/td[3]/a";
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement notes = driver.findElement(By.xpath(str));
			jse.executeScript("arguments[0].scrollIntoView();", notes);
			assertTrue(driver.findElement(By.xpath(str)).isDisplayed(), "Failed : Notes are not displaying");
			reservationLogger.info(driver.findElement(By.xpath(str)).getText());
			test_steps.add("Successfully get the note : " + driver.findElement(By.xpath(str)).getText());
			isNotesCreated = true;

		}
		assertEquals(isNotesCreated, true, "Notes does not created");
		return test_steps;
	}

	public ArrayList<String> roomAssignment(WebDriver driver, ExtentTest test1, String Nights, String Adults,
			String Children, String CheckorUncheckAssign, String RoomClassName, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.Click_RoomPicker.click();
		test_steps.add("Successfully clicked on Rooms Picker");
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.WaitForElement(driver, OR.Click_Arrive_Datepicker);
		/*
		 * try { new Select(ReservationPage.Select_property_RoomAssign).
		 * selectByVisibleText(PropertyName); } catch(Exception e) { new
		 * Select(ReservationPage.Select_property_RoomAssign2).
		 * selectByVisibleText(PropertyName); }
		 */
		// Wait.wait5Second();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Arrive_Datepicker, driver);
		ReservationPage.Click_Arrive_Datepicker.click();
		test_steps.add("Successfully clicked on arrival date");
		ReservationPage.Click_Today.click();
		test_steps.add("Successfully clicked on Today");
		ReservationPage.Enter_Nigts.clear();
		ReservationPage.Enter_Nigts.sendKeys(Nights);
		test_steps.add("Successfully entered the nights : " + Nights);
		ReservationPage.Enter_Adults.clear();
		ReservationPage.Enter_Adults.sendKeys(Adults);
		test_steps.add("Successfully entered the audlts : " + Adults);
		ReservationPage.Enter_Children.clear();
		ReservationPage.Enter_Children.sendKeys(Children);
		test_steps.add("Successfully entered the childrens : " + Children);
		// ReservationPage.Enter_Rate_Promocode.sendKeys(RatepromoCode);

		/*
		 * if(!ReservationPage.Check_Split_Rooms.isSelected()){
		 * ReservationPage.Check_Split_Rooms.click(); test.log(LogStatus.PASS,
		 * "Successfully clicked on split rooms"); }
		 */

		if (ReservationPage.Check_Assign_Room.isSelected()) {
			// ReservationPage.Check_Assign_Room.click();
			ReservationPage.Click_Search.click();
			test_steps.add("Successfully clicked on assign rooms");
		} else {
			if (CheckorUncheckAssign.equals("Yes")) {
				ReservationPage.Check_Assign_Room.click();
				test_steps.add("Successfully clicked on assign rooms");
				ReservationPage.Click_Search.click();
				test_steps.add("Successfully clicked on search");
			} else {
				ReservationPage.Click_Search.click();
				test_steps.add("Successfully clicked on search");
			}
		}

		/*
		 * try {
		 * 
		 * new Select(ReservationPage.Select_Room_Class).selectByVisibleText(
		 * RoomClassName); String selectedOption = new
		 * Select(ReservationPage.Validating_UnAssgined_DDL).
		 * getFirstSelectedOption().getText();
		 * reservationLogger.info("selectedOption  " +selectedOption);
		 * if(selectedOption.equals("--Select--")) { //new
		 * Select(ReservationPage.Select_Room_Number).selectByVisibleText(
		 * RoomNumber); new
		 * Select(ReservationPage.Select_Room_Number).selectByIndex(1); } else {
		 * reservationLogger.info("Reservation is unassigned"); } }
		 * catch(Exception e) {
		 * Wait.explicit_wait_xpath(OR.Validation_Text_NoRooms);
		 * reservationLogger.info("Room Class are not Available for these dates"
		 * );
		 * 
		 * }
		 */

		// Wait.wait5Second();
		int count = 1;
		while (Integer.parseInt(Nights) >= count) {

			Wait.WaitForElement(driver,
					"//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr[" + count
							+ "]/td[2]/select");
			WebElement ele = driver.findElement(By
					.xpath("//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr["
							+ count + "]/td[2]/select"));

			Wait.WaitForElement(driver,
					"//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr[" + count
							+ "]/td[3]/select");
			WebElement ele1 = driver.findElement(By
					.xpath("//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr["
							+ count + "]/td[3]/select"));
			Select sel = new Select(ele);
			if (count == 1) {
				reservationLogger.info(RoomClassName);
				sel.selectByVisibleText(RoomClassName);
				test_steps.add("Successfully selected the room class : " + RoomClassName);
				sel = new Select(ele1);
				java.util.List<WebElement> options = sel.getOptions();
				int roomCount = 0;
				for (WebElement item : options) {
					reservationLogger.info(item.getText());
					if (roomCount == 1) {
						// sel.selectByIndex(1);

						if (Utility.roomIndex == Utility.roomIndexEnd) {
							Utility.roomIndex = 1;
						}
						new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
						Utility.roomIndex++;
						test_steps.add("Successfully selected the room number : " + item.getText());

						break;
					}
					roomCount++;
				}
			}
			count++;
		}

		ReservationPage.Click_Select.click();
		test_steps.add("Successfully clicked on select");
		try {
			Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
		} catch (Exception e) {
			reservationLogger.info("Verification failed");
		}
		Wait.wait2Second();
		if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
			ReservationPage.Click_Continue_RuleBroken_Popup.click();
			test_steps.add("Successfully clicked on rule brocken pop up");
		} else {
			reservationLogger.info("Satisfied Rules");
		}

		if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
			String getTotasterTitle = ReservationPage.Toaster_Title.getText();
			String getToastermessage = ReservationPage.Toaster_Message.getText();
			reservationLogger.info(getTotasterTitle + " " + getToastermessage);
			Assert.assertEquals(getTotasterTitle, "Room assignment has changed.");
			Assert.assertEquals(getToastermessage, "Room assignment has changed.");
		}

		// Wait.wait10Second();

		Wait.WaitForElement(driver, OR.Get_RoomClass_Status);
		// String getPropertyName = ReservationPage.Get_Property_Name.getText();
		String getRoomclassName_status = ReservationPage.Get_RoomClass_Status.getText();
		reservationLogger.info(getRoomclassName_status);
		// Assert.assertEquals(getPropertyName,PropertyName );
		String getRoomclassName[] = getRoomclassName_status.split(" ");
		// Assert.assertEquals(getRoomclassName[0],RoomClassName );
		if (CheckorUncheckAssign.equals("Yes")) {

		} else {

			// Assert.assertEquals(getRoomclassName[3], "Unassigned");
			Assert.assertEquals(getRoomclassName.length - 1, "Unassigned");
		}
		return test_steps;
	}

	public ArrayList<String> vaidatePaymentDetails(WebDriver driver, ExtentTest test, ArrayList<String> test_steps) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		String roomChargers = ReservationPage.Room_Charges.getText();
		String incidentals = ReservationPage.Incidentals.getText();
		String taxAndServiceCharges = ReservationPage.TaxesAndServiceCharges.getText();
		String Total = ReservationPage.TotalCharges.getText();

		// test.log(LogStatus.PASS, "Tax is : " + taxAndServiceCharges);

		roomChargers = roomChargers.replace("$", "").trim();
		incidentals = incidentals.replace("$", "").trim();
		taxAndServiceCharges = taxAndServiceCharges.replace("$", "").trim();
		Total = Total.replace("$", "").trim();

		if (Double.parseDouble(roomChargers) + Double.parseDouble(incidentals)
				+ Double.parseDouble(taxAndServiceCharges) == Double.parseDouble(Total)) {
			test_steps.add("Totals are equal");
		} else {

			test_steps.add("Totals are not equal");
			reservationLogger.info("Totals are not equal");
		}
		return test_steps;
	}

	public ArrayList<String> validateTaxExempt(WebDriver driver, ExtentTest test, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.wait2Second();
		// Java script object creation
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0,1000)");
		if (!ReservationPage.Check_IsTaxExempt.isSelected()) {
			Wait.WaitForElement(driver, OR.AccountTaxExempt);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.AccountTaxExempt, driver);
			Utility.ScrollToElement(ReservationPage.AccountTaxExempt, driver);
			String AccountNameTaxExempt = ReservationPage.AccountTaxExempt.getText();
			test_steps.add("The Account Name Created for taxExempt is : " + AccountNameTaxExempt);
			Wait.WaitForElement(driver, OR.Check_IsTaxExempt);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Check_IsTaxExempt, driver);
			Utility.ScrollToElement(ReservationPage.Check_IsTaxExempt, driver);
			ReservationPage.Check_IsTaxExempt.click();
			test_steps.add("Tax exempt check box selected");
			reservationLogger.info("Tax exempt check box selected");
			String str = ReservationPage.TaxExemptThisFieldIsdRequired.getText();
			test_steps.add("Tax Exempt : This Field Is Required" + str);
			ReservationPage.Enter_TaxExemptId.sendKeys("123");
			Wait.wait2Second();
			assertTrue(ReservationPage.Enter_TaxExemptId.getAttribute("value").contains("123"),
					"Failed: Tax Empt Id missmatched");
			test_steps.add("Entered Tax exempt id : " + "123");
			Wait.wait3Second();
		} else {
			Wait.WaitForElement(driver, OR.Check_IsTaxExempt);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Check_IsTaxExempt, driver);
			Utility.ScrollToElement(ReservationPage.Check_IsTaxExempt, driver);
			test_steps.add("Tax exempt check box is already selected");
			reservationLogger.info("Tax exempt check box selected");
			String str = ReservationPage.Enter_TaxExemptId.getAttribute("value");
			test_steps.add("The Tax Exmpt Id Is : " + str);
			Wait.WaitForElement(driver, OR.AccountTaxExempt);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.AccountTaxExempt, driver);
			Utility.ScrollToElement(ReservationPage.AccountTaxExempt, driver);
			String AccountNameTaxExempt = ReservationPage.AccountTaxExempt.getText();
			test_steps.add("The Account Name Created for taxExempt is : " + AccountNameTaxExempt);
		}

		return test_steps;
	}

	public ArrayList<String> validateTaxExempt_Res(WebDriver driver, ExtentTest test, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.wait2Second();
		// Java script object creation
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0,1000)");
		if (!ReservationPage.Check_IsTaxExempt.isSelected()) {

			Wait.WaitForElement(driver, OR.Check_IsTaxExempt);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Check_IsTaxExempt, driver);
			Utility.ScrollToElement(ReservationPage.Check_IsTaxExempt, driver);
			ReservationPage.Check_IsTaxExempt.click();
			test_steps.add("Selected Tax exempt check box");
			reservationLogger.info("Selected Tax exempt check box");

			String str = ReservationPage.TaxExemptThisFieldIsdRequired.getText();

			// test_steps.add(str);

			ReservationPage.Enter_TaxExemptId.sendKeys("123");
			Wait.wait2Second();
			assertTrue(ReservationPage.Enter_TaxExemptId.getAttribute("value").contains("123"),
					"Failed: Tax Empt Id missmatched");
			test_steps.add("Entered Tax exempt id : " + "123");
			Wait.wait3Second();
		}
		return test_steps;
	}

	public void LineItemTax2(WebDriver driver, String Category2, ArrayList<String> test_steps) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		String xpath = "//span[.='" + Category2 + "']//following::td[3]/span";

		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", xpath);

		String LineItem2Tax = driver.findElement(By.xpath(xpath)).getText();

		reservationLogger.info("Line Item2 Tax: " + LineItem2Tax);
		test_steps.add(" Line Item Tax: " + LineItem2Tax);
	}

	public void LineItemTax3(WebDriver driver, String Category3, ArrayList<String> test_steps) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		String xpath = "//span[.='" + Category3 + "']//following::td[3]/span";

		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", xpath);

		String LineItem3Tax = driver.findElement(By.xpath(xpath)).getText();

		reservationLogger.info("Line Item3 Tax: " + LineItem3Tax);
		test_steps.add(" Line Item Tax: " + LineItem3Tax);
	}

	public void LineItemTax4(WebDriver driver, String Category4, ArrayList<String> test_steps) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		String xpath = "//span[.='" + Category4 + "']//following::td[3]/span";

		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", xpath);

		String LineItem4Tax = driver.findElement(By.xpath(xpath)).getText();

		reservationLogger.info("Line Item4 Tax: " + LineItem4Tax);
		test_steps.add(" Line Item4 Tax: " + LineItem4Tax);
	}

	public void LineItemTax5(WebDriver driver, String Category5, ArrayList<String> test_steps) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		String xpath = "//span[.='" + Category5 + "']//following::td[3]/span";

		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", xpath);

		String LineItem5Tax = driver.findElement(By.xpath(xpath)).getText();

		reservationLogger.info("Line Item5 Tax: " + LineItem5Tax);
		test_steps.add(" Line Item5 Tax: " + LineItem5Tax);
	}

	public ArrayList<String> TaxExemptionMessage(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				ReservationPage.taxExemptMessage);
		ReservationPage.taxExemptMessage.getText();
		reservationLogger.info("Tax Exempt Save Message: " + ReservationPage.taxExemptMessage.getText());
		test_steps.add("Tax Exempt Save Message: " + ReservationPage.taxExemptMessage.getText());
		return test_steps;
	}

	public ArrayList<String> validateResTaxExempt(WebDriver driver, ExtentTest test, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.wait2Second();
		// Java script object creation
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0,1000)");
		if (ReservationPage.Check_IsTaxExempt.isSelected()) {
			Wait.WaitForElement(driver, OR.Check_IsTaxExempt);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Check_IsTaxExempt, driver);
			Utility.ScrollToElement(ReservationPage.Check_IsTaxExempt, driver);
			test_steps.add("Verified : Tax Exempt Checkbox Is Checked");
			reservationLogger.info("Tax exempt check box selected");
			String str = ReservationPage.Enter_TaxExemptId.getAttribute("value");
			test_steps.add("Verified : The Tax Exmpt Id Is : " + str);
			Wait.WaitForElement(driver, OR.AccountTaxExempt);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.AccountTaxExempt, driver);
			Utility.ScrollToElement(ReservationPage.AccountTaxExempt, driver);
			String AccountNameTaxExempt = ReservationPage.AccountTaxExempt.getText();
			test_steps.add("Verified : The Account Has Been Attached With The Created New Reservation  : "
					+ AccountNameTaxExempt);
		} else {

			assertEquals(ReservationPage.Check_IsTaxExempt.isSelected(), true,
					"The Exempt Checkbox is not checked and Exmpt id is empty");
		}

		return test_steps;
	}

	public ArrayList<String> CheckTaxExempt(WebDriver driver, String TaxExemptText, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait2Second();
		// Java script object creation
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0,1000)");
		if (!ReservationPage.Check_IsTaxExempt.isSelected()) {
			Wait.WaitForElement(driver, OR.Check_IsTaxExempt);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Check_IsTaxExempt, driver);
			Utility.ScrollToElement(ReservationPage.Check_IsTaxExempt, driver);
			ReservationPage.Check_IsTaxExempt.click();
			test_steps.add("Tax exempt check box selected");
			reservationLogger.info("Tax exempt check box selected");

			String str = ReservationPage.TaxExemptThisFieldIsdRequired.getText();

			test_steps.add(str);

			ReservationPage.Enter_TaxExemptId.sendKeys(TaxExemptText);
			Wait.wait2Second();
			assertTrue(ReservationPage.Enter_TaxExemptId.getAttribute("value").contains(TaxExemptText),
					"Failed: Tax Empt Id missmatched");
			test_steps.add("Entered Tax exempt id : " + TaxExemptText);
			Wait.wait3Second();
		}
		return test_steps;
	}

	public void click_Folio(WebDriver driver, ExtentTest test) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// Wait.wait5Second();
		Wait.WaitForElement(driver, OR.MoveFolio_Folio);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				ReservationPage.MoveFolio_Folio);

		Utility.ScrollToElement(ReservationPage.MoveFolio_Folio, driver);
		Wait.wait5Second();
		ReservationPage.MoveFolio_Folio.click();
		Wait.explicit_wait_xpath(OR.Click_NewFolio, driver);
		reservationLogger.info("Clicked on Folio");
		Wait.wait10Second();
	}

	public ArrayList<String> UnCheckTaxExemptInReservation(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait2Second();
		// Java script object creation
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0,1000)");
		if (ReservationPage.Check_IsTaxExempt.isSelected()) {

			Utility.ScrollToElement(ReservationPage.Check_IsTaxExempt, driver);
			ReservationPage.Check_IsTaxExempt.click();
			test_steps.add("Tax checkbox in Reservation is unchecked");
			reservationLogger.info("Tax checkbox in Reservation unchecked");

			Wait.wait3Second();
		}
		return test_steps;
	}

	public void click_Folio(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// Wait.wait5Second();

		Wait.WaitForElement(driver, OR.MoveFolio_Folio);
		Utility.ScrollToElement(ReservationPage.MoveFolio_Folio, driver);
		Wait.wait5Second();
		Wait.WaitForElement(driver, OR.MoveFolio_Folio);
		ReservationPage.MoveFolio_Folio.click();
		Wait.explicit_wait_xpath(OR.Click_NewFolio, driver);
		reservationLogger.info("Click on Folio");
		test_steps.add("Click on Folio");
	}

	public ArrayList<String> taxDetails(WebDriver driver, ExtentTest test, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// Wait.wait3Second();

		Wait.WaitForElement(driver, OR.Room_Charges);
		String roomChargers = ReservationPage.Room_Charges.getText();
		String incidentals = ReservationPage.Incidentals.getText();
		String taxAndServiceCharges = ReservationPage.TaxesAndServiceCharges.getText();
		String Total = ReservationPage.TotalCharges.getText();

		test_steps.add("Tax is : " + taxAndServiceCharges);

		roomChargers = roomChargers.replace("$", "").trim();
		incidentals = incidentals.replace("$", "").trim();
		taxAndServiceCharges = taxAndServiceCharges.replace("$", "").trim();
		Total = Total.replace("$", "").trim();

		if (Double.parseDouble(taxAndServiceCharges) == 0) {
			test_steps.add("Tax is Zero");
		} else {
			test_steps.add("Tax are not Zero");
			reservationLogger.info("Tax are not Zero");
		}

		ReservationPage.FirstOpenedReservationClose.click();
		// Wait.wait3Second();
		return test_steps;
	}

	public double CheckinNew(WebDriver driver, String PropertyName, String RoomClassName, String CheckorUncheckAssign,
			String PaymentType, String CardName, String CCNumber, String CCExpiry, String CCVCode,
			String Authorizationtype, String ChangeAmount, String ChangeAmountValue, String traceData,
			ArrayList test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		double waittime = 0.12;
		long startTime = System.currentTimeMillis();

		double processedamount = 0;
		double endingbalance;
		String GetEndingBalance = ReservationPage.Payment_Details_Folio_Balance.getText();

		// GetEndingBalance=GetEndingBalance.replace("$", " ");
		GetEndingBalance = GetEndingBalance.replace("(", "");
		GetEndingBalance = GetEndingBalance.replace(")", "");
		GetEndingBalance.trim();
		reservationLogger.info(GetEndingBalance);
		String RemoveCurreny[] = GetEndingBalance.split(" ");
		endingbalance = Double.parseDouble(RemoveCurreny[1]);
		reservationLogger.info("Ending balance before checkin " + endingbalance);
		Wait.wait3Second();

		// ReservationPage.Click_on_confirm.click();
		driver.navigate().refresh();
		Actions action = new Actions(driver);
		Utility.ScrollToElement(ReservationPage.Click_Checkin, driver);
		action.moveToElement(ReservationPage.Click_Checkin).doubleClick().build().perform();
		// ReservationPage.Click_Checkin.click();
		/*
		 * boolean flag = false; try {
		 * Wait.explicit_wait_visibilityof_webelement(ReservationPage.
		 * Already_Checked_In_Confirm_Popup); // *
		 * ReservationPage.Already_Checked_In_Confirm_Popup_Confirm_Btn.click();
		 * flag = true; } catch (Exception e) {
		 * reservationLogger.info("No conflicts with room assignment"); } if
		 * (flag == true) {
		 * Wait.explicit_wait_visibilityof_webelement(ReservationPage.
		 * Room_Assignment_PopUp);
		 * Wait.explicit_wait_visibilityof_webelement(ReservationPage.
		 * Select_Room_Number); Select selectRoomNumber = new
		 * Select(ReservationPage.Select_Room_Number); String selectedOption =
		 * selectRoomNumber.getFirstSelectedOption().getText(); if
		 * (selectedOption.contains("(O and")) {
		 * 
		 * String RoomwithVandC =
		 * driver.findElement(By.xpath("//option[contains(text(),'(V and C)')] "
		 * )) .getAttribute("value");
		 * selectRoomNumber.selectByValue(RoomwithVandC); reservationLogger.
		 * info("Selected first available room with V and C status from the list"
		 * ); Wait.wait2Second(); ReservationPage.Click_Select.click();
		 * reservationLogger.
		 * info("Clicked on select button of room assignment popup"); try {
		 * 
		 * Wait.explicit_wait_visibilityof_webelement(ReservationPage.
		 * ReCalculate_Folio_Options_PopUp); if
		 * (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
		 * ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();
		 * 
		 * action.moveToElement(ReservationPage.
		 * ReCal_Folio_Options_PopUp_Select_Btn).click().build() .perform(); } }
		 * catch (Exception e) {
		 * reservationLogger.info("No ReCalculate Folio Options PopUp"); } }
		 * else { reservationLogger.info("No Issues"); } }
		 */
		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.Room_Assignment_PopUp, driver);
		long endTime = System.currentTimeMillis();
		Wait.waitforloadpage(startTime, endTime, waittime);
		Wait.wait3Second();

		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(ReservationPage.Click_Select));

		ReservationPage.Click_Select.click();

		try {
			// Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_RulesBroken_Popup);

			Wait.wait2Second();
			if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
				ReservationPage.Click_Continue_RuleBroken_Popup.click();
			} else {
				reservationLogger.info("Satisfied Rules");
			}
		} catch (Exception e) {
			reservationLogger.info("Verification failed");
		}

		if (ReservationPage.Room_Assignment_PopUp_Error.isDisplayed()) {
			if (ReservationPage.Room_Assignment_PopUp_Error.getText()
					.equalsIgnoreCase("Select valid rooms on all the listed dates")) {
				new Select(ReservationPage.Room_Selector_In_Room_Assignment_PopUp).selectByIndex(2);

				ReservationPage.Click_Select.click();
				Wait.wait2Second();
				if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {

					ReservationPage.Click_Continue_RuleBroken_Popup.click();
				} else {
					reservationLogger.info("Satisfied Rules");
				}

				// ReservationPage.RoomCharges_Select.click();
			}
		}

		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_Dirty_Room_popup, driver);
			Wait.wait2Second();
			ReservationPage.Confirm_button.click();
		} catch (Exception e) {
			reservationLogger.info("No Dirty Rooms");
		}

		try {

			Wait.wait2Second();
			if (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
				ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();
				Wait.wait2Second();
				action.moveToElement(ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn).click().build().perform();

			}
		} catch (Exception e) {
			reservationLogger.info("No ReCalculate Folio Options PopUp");
		}

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		try {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);
			if (ReservationPage.Payment_Popup.isDisplayed()) {
				ReservationFolio Payment = new ReservationFolio();
				checkinpolicy = true;
				processedamount = Payment.TestPaymentPopup(driver, PaymentType, CardName, CCNumber, CCExpiry, CCVCode,
						Authorizationtype, ChangeAmount, ChangeAmountValue, traceData, test_steps);

				reservationLogger.info("Processed amount " + processedamount);
			}
		} catch (Exception e) {
			reservationLogger.info("Checkin Policy doesn't exist");
		}

		if (checkinpolicy == false) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_on_confirm, driver);
			ReservationPage.Click_on_confirm.click();
			Wait.wait3Second();
		}

		try {
			if (ReservationPage.Key_Generation_Popup.isDisplayed()) {
				ReservationPage.Key_Generation_Close.click();
				Wait.wait15Second();
			}
		} catch (Exception e) {
			reservationLogger.info("Key Geneartion doesnt exist");
		}
		checkinpolicy = false;
		return processedamount;
	}

	public ArrayList<String> CheckinWithBlnc(WebDriver driver, String PropertyName, String RoomClassName,
			String CheckorUncheckAssign, String PaymentType, String CardName, String CCNumber, String CCExpiry,
			String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue, String traceData)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);

		double waittime = 0.12;
		long startTime = System.currentTimeMillis();

		double endingbalance;
		String GetEndingBalance = ReservationPage.Payment_Details_Folio_Balance.getText();
		GetEndingBalance = GetEndingBalance.replace("(", "");
		GetEndingBalance = GetEndingBalance.replace(")", "");
		GetEndingBalance.trim();
		reservationLogger.info(GetEndingBalance);
		String RemoveCurreny[] = GetEndingBalance.split(" ");
		endingbalance = Double.parseDouble(RemoveCurreny[1]);
		test_steps.add("Ending balance before checkin : $ " + endingbalance);
		reservationLogger.info("Ending balance before checkin : $ " + endingbalance);
		Wait.wait3Second();
		driver.navigate().refresh();
		Actions action = new Actions(driver);
		Utility.ScrollToElement(ReservationPage.Click_Checkin, driver);
		action.moveToElement(ReservationPage.Click_Checkin).doubleClick().build().perform();
		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.Room_Assignment_PopUp, driver);
		long endTime = System.currentTimeMillis();
		Wait.waitforloadpage(startTime, endTime, waittime);
		Wait.wait3Second();

		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(ReservationPage.Click_Select));

		ReservationPage.Click_Select.click();

		try {

			Wait.wait2Second();
			if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
				ReservationPage.Click_Continue_RuleBroken_Popup.click();
			} else {
				reservationLogger.info("Satisfied Rules");
				test_steps.add("Satisfied Rules");
			}
		} catch (Exception e) {
			reservationLogger.info("Verification failed");
			test_steps.add("Verification failed");
		}

		if (ReservationPage.Room_Assignment_PopUp_Error.isDisplayed()) {
			if (ReservationPage.Room_Assignment_PopUp_Error.getText()
					.equalsIgnoreCase("Select valid rooms on all the listed dates")) {
				new Select(ReservationPage.Room_Selector_In_Room_Assignment_PopUp).selectByIndex(2);

				ReservationPage.Click_Select.click();
				Wait.wait2Second();
				if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {

					ReservationPage.Click_Continue_RuleBroken_Popup.click();
				} else {
					reservationLogger.info("Satisfied Rules");
					test_steps.add("Satisfied Rules");
				}

			}
		}

		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_Dirty_Room_popup, driver);
			Wait.wait2Second();
			ReservationPage.Confirm_button.click();
		} catch (Exception e) {
			reservationLogger.info("No Dirty Rooms");
			test_steps.add("No Dirty Rooms");
		}

		try {

			Wait.wait2Second();
			if (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
				ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();
				Wait.wait2Second();
				action.moveToElement(ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn).click().build().perform();

			}
		} catch (Exception e) {
			reservationLogger.info("No ReCalculate Folio Options PopUp");
			test_steps.add("No ReCalculate Folio Options PopUp");
		}

		try {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);
			if (ReservationPage.Payment_Popup.isDisplayed()) {
				assertTrue(ReservationPage.Payment_Popup.isDisplayed(), "Payment_PopUp didn't Display");
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.Change_Amount, driver);
				Utility.ScrollToElement(ReservationPage.Change_Amount, driver);
				String Endingbal = ReservationPage.Change_Amount.getAttribute("value");
				Endingbal = Endingbal.replace("$", " ");
				Endingbal = Endingbal.trim();
				System.out.println("Double:" + Endingbal);
				test_steps.add("Double:" + Endingbal);
				Double d = Double.parseDouble(Endingbal);
				d = d - 1;
				System.out.println(PaymentType);
				test_steps.add(PaymentType);
				new Select(ReservationPage.Select_Paymnet_Method).selectByVisibleText(PaymentType);
				try {
					ReservationPage.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"));
					ReservationPage.Change_Amount.sendKeys(d.toString());

				} catch (Exception e) {
					reservationLogger.info("Catch Body");
					String amount = "(//label[.='Amount:'])[2]/following-sibling::div/input";
					driver.findElement(By.xpath(amount)).sendKeys(Keys.chord(Keys.CONTROL, "a"));
					driver.findElement(By.xpath(amount)).sendKeys(d.toString());
					test_steps.add("The Amount Entered during check In is Less than policy Amount : " + (d.toString()));
				}
				if (ReservationPage.Click_ADD_Btn.isDisplayed()) {
					Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_ADD_Btn, driver);
					Utility.ScrollToElement(ReservationPage.Click_ADD_Btn, driver);
					ReservationPage.Click_ADD_Btn.click();
					reservationLogger.info("Clicking on add button");
					test_steps.add("Clicking on add button");
				} else {
					Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Process, driver);
					Utility.ScrollToElement(ReservationPage.Click_Process, driver);
					ReservationPage.Click_Process.click();
					Utility.app_logs.info("Clicked on Process Button");
					reservationLogger.info("Click Process Button");
					test_steps.add("Clicking on Process button");
				}
				WebDriverWait wait1 = new WebDriverWait(driver, 120);
				wait1.until(ExpectedConditions.elementToBeClickable(ReservationPage.Click_Continue));
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Continue, driver);
				Wait.WaitForElement(driver, OR.Click_Continue);
				ReservationPage.Click_Continue.click();
				reservationLogger.info("Clicking on Continue");
				test_steps.add("Clicking on Continue button");
			}
		} catch (Exception e) {
			reservationLogger.info("Checkin Policy doesn't exist");
			test_steps.add("Checkin Policy doesn't exist");
		}

		if (checkinpolicy == false) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_on_confirm, driver);
			ReservationPage.Click_on_confirm.click();
			Wait.wait3Second();
		}

		try {
			if (ReservationPage.Key_Generation_Popup.isDisplayed()) {
				ReservationPage.Key_Generation_Close.click();
				Wait.wait15Second();
			}
		} catch (Exception e) {
			reservationLogger.info("Key Geneartion doesnt exist");
			test_steps.add("Key Geneartion doesnt exist");
		}
		checkinpolicy = false;
		return test_steps;
	}

	public void Pay_Balance_Amount(WebDriver driver, String PaymentType, String CardName, String CCNumber,
			String CCExpiry, String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue,
			String traceData, ArrayList test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		float endingbalance;
		float processedamount;
		String GetEndingBalance = ReservationPage.Payment_Details_Folio_Balance.getText();
		reservationLogger.info("Balance Amount :" + GetEndingBalance);
		String RemoveCurreny[] = GetEndingBalance.split(" ");
		endingbalance = Float.parseFloat(RemoveCurreny[1]);
		Utility.ScrollToElement(ReservationPage.Click_Pay_Button, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.Click_Pay_Button);
		// ReservationPage.Click_Pay_Button.click();

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);

		if (elements_All_Payments.Reservation_PaymentPopup.isDisplayed()) {
			ReservationFolio Payment = new ReservationFolio();
			processedamount = Payment.TestPaymentPopup(driver, PaymentType, CardName, CCNumber, CCExpiry, CCVCode,
					Authorizationtype, ChangeAmount, ChangeAmountValue, traceData, test_steps);
			Assert.assertEquals(processedamount, endingbalance,
					" ending balance " + endingbalance + " processed amount " + processedamount);
		}
		Wait.wait3Second();

	}

	public ArrayList<String> Payment_Authorized(WebDriver driver, String PaymentType, String CardName, String CCNumber,
			String CCExpiry, String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue,
			String traceData, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Click_Pay_Button, driver);
		Utility.ScrollToElement(ReservationPage.Click_Pay_Button, driver);
		// ReservationPage.Click_Pay_Button.click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.Click_Pay_Button);

		Utility.app_logs.info("Click Pay");
		test_steps.add("Click Pay");

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		try {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);
			assertTrue(elements_All_Payments.Reservation_PaymentPopup.isDisplayed(),
					"Failed: Payment Popup not displayed");
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentPopUp, driver);
			assertTrue(elements_All_Payments.PaymentPopUp.isDisplayed(), "Failed: Payment Popup not displayed");
		}
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		try {
			new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(PaymentType);
			new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText("Authorization Only");

			Wait.wait3Second();
			assertTrue(new Select(ReservationFolio.Select_Authorization_type).getFirstSelectedOption().getText()
					.contains("Authorization"), "Failed : Authorization is not selected");
			ReservationFolio.Click_Card_info.click();
			Utility.app_logs.info("Clicked on card info");
			Wait.explicit_wait_xpath(OR.Verify_payment_info_popup, driver);
			// Wait.wait3Second();
			ReservationFolio.Enter_Card_Name.sendKeys(CardName);
			ReservationFolio.Enter_Account_Number_Folio.sendKeys(CCNumber);
			System.out.println("Entered number CardName");
			ReservationFolio.Enter_CC_Expiry.sendKeys(CCExpiry);
			ReservationFolio.Enter_CVVCode.sendKeys(CCVCode);
			Utility.app_logs.info("Entered Card Details");
			ReservationFolio.Click_OK.click();
			Utility.app_logs.info("Clicked on OK button");
			Wait.wait10Second();
			new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText(Authorizationtype);
			Utility.app_logs.info("Selected authorization type : " + Authorizationtype);
			test_steps.add("Selected authorization type : " + Authorizationtype);
			Wait.wait3Second();
			assertTrue(new Select(ReservationFolio.Select_Authorization_type).getFirstSelectedOption().getText()
					.contains(Authorizationtype), "Failed : Authorization is not selected");
			if (ChangeAmount.equalsIgnoreCase("Yes")) {
				ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
			} else {
				Utility.app_logs.info("Processing the amount displayed");
			}
			Wait.wait2Second();
			ReservationFolio.Click_Process.click();
			Utility.app_logs.info("Clicked on Process Button");
			test_steps.add("Click Process Button");
			Thread.sleep(1000);
			Wait.wait3Second();
			Wait.explicit_wait_xpath(OR.Verify_MC_Grid, driver);
			String GetPaymentMethod = ReservationFolio.GetMC_Payment_Method.getText();
			Utility.app_logs.info("PaymentMethod: " + " " + GetPaymentMethod);
			if (GetPaymentMethod.equals(PaymentType)) {
				Utility.app_logs.info("Paymnet is successful");
			} else {
				Utility.app_logs.info("Paymnet is Failed");
			}

			Wait.wait1Second();
			Utility.ScrollToElement(ReservationFolio.Processed_Amount_In_Paymentdetails_Popup, driver);
			Wait.wait2Second();
			String Processed_Amount = ReservationFolio.Processed_Amount_In_Paymentdetails_Popup.getText();
			Utility.app_logs.info("Processed_Amount " + Processed_Amount + " -- " + Processed_Amount);

			ReservationFolio.Click_Continue.click();
			Utility.app_logs.info("Clicked on continue button");
			test_steps.add("Click Continue");
			Wait.wait3Second();
			Wait.explicit_wait_xpath(OR.GetAddedLine_MC, driver);
			String GetMCCard = ReservationFolio.GetAddedLine_MC.getText();
			Utility.app_logs.info("Transaction Line Item: " + GetMCCard);
			if (GetMCCard.equalsIgnoreCase("Name: MC Account #: XXXX5454 Exp. Date: 08/21")) {
				Utility.app_logs.info("Payment is successful");
				test_steps.add("Payment is successful");
			} else {
				Utility.app_logs.info("Payment is Failed");
				test_steps.add("Payment is Failed");
			}
		} catch (Exception e) {
			/*
			 * ((IExtentTestClass) test_steps).log(LogStatus.FAIL,
			 * "Exception occured while paying using MC \n" + e.getMessage() +
			 * "\n\n <br> Attaching screenshot below : \n" + ((IExtentTestClass)
			 * test_steps).addScreenCapture(
			 * Utility.captureScreenShot(((IExtentTestClass)
			 * test_steps).getTest().getName() + "_Payment_ByMC" +
			 * Utility.getTimeStamp(), driver)));
			 */Utility.app_logs.info("Exception occured while paying using MC \n");
			e.printStackTrace();
		}
		return test_steps;
	}

	public ArrayList<String> CardPayment(WebDriver driver, String PaymentType, String CardName, String CCNumber,
			String CCExpiry, String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue,
			String traceData, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		try {
			new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(PaymentType);
			new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText("Authorization Only");

			Wait.wait3Second();
			assertTrue(new Select(ReservationFolio.Select_Authorization_type).getFirstSelectedOption().getText()
					.contains("Authorization"), "Failed : Authorization is not selected");
			ReservationFolio.Click_Card_info.click();
			Utility.app_logs.info("Clicked on card info");
			Wait.explicit_wait_xpath(OR.Verify_payment_info_popup, driver);
			// Wait.wait3Second();
			ReservationFolio.Enter_Card_Name.sendKeys(CardName);
			ReservationFolio.Enter_Account_Number_Folio.sendKeys(CCNumber);
			System.out.println("Entered number CardName");
			ReservationFolio.Enter_CC_Expiry.sendKeys(CCExpiry);
			ReservationFolio.Enter_CVVCode.sendKeys(CCVCode);
			Utility.app_logs.info("Entered Card Details");
			ReservationFolio.Click_OK.click();
			Utility.app_logs.info("Clicked on OK button");
			Wait.wait10Second();
			new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText(Authorizationtype);
			Utility.app_logs.info("Selected authorization type : " + Authorizationtype);
			test_steps.add("Selected authorization type : " + Authorizationtype);
			Wait.wait3Second();
			assertTrue(new Select(ReservationFolio.Select_Authorization_type).getFirstSelectedOption().getText()
					.contains(Authorizationtype), "Failed : Authorization is not selected");
			if (ChangeAmount.equalsIgnoreCase("Yes")) {
				ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
			} else {
				Utility.app_logs.info("Processing the amount displayed");
			}
			ReservationFolio.Click_Process.click();
			Utility.app_logs.info("Clicked on Process Button");
			test_steps.add("Click Process Button");
			Thread.sleep(1000);
			Wait.wait3Second();
			Wait.explicit_wait_xpath(OR.Select_Payment_Method_4, driver);
			// String GetPaymentMethod =
			// ReservationFolio.GetMC_Payment_Method.getText();
			// String GetPaymentMethod =
			// ReservationFolio.Get_Payment_Method.getText();
			String GetPaymentMethod = ReservationFolio.Select_Payment_Method_4.getText();
			Utility.app_logs.info("PaymentMethod: " + " " + GetPaymentMethod);
			if (GetPaymentMethod.equals(PaymentType)) {
				Utility.app_logs.info("Paymnet is successful");
			} else {
				Utility.app_logs.info("Paymnet is Failed");
			}

			Wait.wait1Second();
			Utility.ScrollToElement(ReservationFolio.Processed_Amount_In_Paymentdetails_Popup, driver);
			Wait.wait2Second();
			String Processed_Amount = ReservationFolio.Processed_Amount_In_Paymentdetails_Popup.getText();
			Utility.app_logs.info("Processed_Amount " + Processed_Amount + " -- " + Processed_Amount);

			ReservationFolio.Click_Continue.click();
			Utility.app_logs.info("Clicked on continue button");
			test_steps.add("Click Continue");
			Wait.wait3Second();

		} catch (Exception e) {
			// ((IExtentTestClass) test_steps).log(LogStatus.FAIL,
			// "Exception occured while paying using MC \n" + e.getMessage()
			// + "\n\n <br> Attaching screenshot below : \n"
			// + ((IExtentTestClass) test_steps).addScreenCapture(
			// Utility.captureScreenShot(((IExtentTestClass)
			// test_steps).getTest().getName()
			// + "_Payment_ByMC" + Utility.getTimeStamp(), driver)));
			Utility.app_logs.info("Exception occured while paying using MC \n");
			e.printStackTrace();
		}
		return test_steps;
	}

	public void WaitModuleLoading(WebDriver driver, String Element) {

		if (Element.contains("Res")) {
			Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 240);

		} else if (Element.contains("Acc")) {
			Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.AccountLoading)), 60);
		}
	}

	public void roomAssignment(WebDriver driver, ExtentTest test1, String Nights, String Adults, String Children,
			String CheckorUncheckAssign) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.Click_RoomPicker, driver);
		Utility.ScrollToElement(ReservationPage.Click_RoomPicker, driver);
		Thread.sleep(5000);
		ReservationPage.Click_RoomPicker.click();
		// test_steps.add("Successfully clicked on Rooms Picker");
		reservationLogger.info("Successfully clicked on Rooms Picker");

		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		// Wait.wait5Second();

		// Wait.WaitForElement(driver, OR.Click_Arrive_Datepicker);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Arrive_Datepicker, driver);
		ReservationPage.Click_Arrive_Datepicker.click();
		// test_steps.add("Successfully clicked on arrival date");
		reservationLogger.info("Successfully clicked on arrival date");

		ReservationPage.Click_Today.click();
		// test_steps.add("Successfully clicked on Today");
		reservationLogger.info("Successfully clicked on Today");

		ReservationPage.Enter_Nigts.clear();
		ReservationPage.Enter_Nigts.sendKeys(Nights);

		// test_steps.add("Successfully entered the nights : " +
		// Nights);
		reservationLogger.info("Successfully entered the nights : " + Nights);

		ReservationPage.Enter_Adults.clear();
		ReservationPage.Enter_Adults.sendKeys(Adults);
		// test_steps.add("Successfully entered the audlts : " +
		// Adults);
		reservationLogger.info("Successfully entered the audlts : " + Adults);

		ReservationPage.Enter_Children.clear();
		ReservationPage.Enter_Children.sendKeys(Children);
		// test_steps.add("Successfully entered the childrens : " +
		// Children);
		reservationLogger.info("Successfully entered the childrens : " + Children);

		if (ReservationPage.Check_Assign_Room.isSelected()) {

			if (CheckorUncheckAssign.equals("Yes")) {
				// ReservationPage.Check_Assign_Room.click();
				reservationLogger.info("Successfully clicked on assign rooms");

				ReservationPage.Click_Search.click();
				// test_steps.add("Successfully clicked on search");
				reservationLogger.info("Successfully clicked on search");
			} else {
				ReservationPage.Check_Assign_Room.click();
				ReservationPage.Click_Search.click();
				// test_steps.add("Successfully clicked on search");
				reservationLogger.info("Successfully clicked on search");
			}
		}

		int count = 1;

		WebElement ele = driver.findElement(
				By.xpath("//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr["
						+ count + "]/td[2]/select"));
		WebElement ele1 = driver.findElement(
				By.xpath("//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr["
						+ count + "]/td[3]/select"));
		Select sel = new Select(ele);
		if (count == 1) {

			sel.selectByIndex(1);
			// test_steps.add("Successfully selected the room class : "
			// + sel.getFirstSelectedOption().getText());
			reservationLogger.info("Successfully selected the room class : " + sel.getFirstSelectedOption().getText());
			sel = new Select(ele1);
			java.util.List<WebElement> options = sel.getOptions();
			int roomCount = 0;
			for (WebElement item : options) {
				// System.out.println(item.getText());
				if (roomCount == 1) {
					sel.selectByIndex(1);
					// test_steps.add("Successfully selected the room
					// number : " + item.getText());
					reservationLogger.info("Successfully selected the room number : " + item.getText());

					break;
				}
				roomCount++;
			}
		}

		ReservationPage.Click_Select.click();
		// test_steps.add("Successfully clicked on select");
		reservationLogger.info("Successfully clicked on select");
		try {
			Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
		} catch (Exception e) {
			// System.out.println("Verification failed");
		}
		Wait.wait2Second();
		if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
			ReservationPage.Click_Continue_RuleBroken_Popup.click();
			// test_steps.add("Successfully clicked on rule brocken pop
			// up");
			reservationLogger.info("Successfully clicked on rule brocken pop up");
		} else {
			// System.out.println("Satisfied Rules");
		}

		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Title);
		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.Verify_Toaster_Container, driver);
		if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
			Wait.wait2Second();
			String getTotasterTitle = ReservationPage.Toaster_Title.getText();
			String getToastermessage = ReservationPage.Toaster_Message.getText();
			reservationLogger.info(getTotasterTitle + " " + getToastermessage);
			Assert.assertEquals(getTotasterTitle, "Room assignment has changed.");
			Assert.assertEquals(getToastermessage, "Room assignment has changed.");
		}

		String getPropertyName = ReservationPage.Get_Property_Name.getText();
		String getRoomclassName_status = ReservationPage.Get_RoomClass_Status.getText();
		reservationLogger.info(getRoomclassName_status);
		// Assert.assertEquals(getPropertyName,PropertyName );
		String getRoomclassName[] = getRoomclassName_status.split(" : ");
		if (CheckorUncheckAssign.equals("Yes")) {

		} else {
			Assert.assertEquals(getRoomclassName[1], "Unassigned");
		}
		ReservationPage.Toaster_Title.click();

		reservationLogger.info("toaster message has been gone");

	}

	public void roomAssignmentWithChange(WebDriver driver, ExtentTest test1, String Nights, String Adults,
			String Children, String CheckorUncheckAssign) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.Click_RoomPicker, driver);
		Utility.ScrollToElement(ReservationPage.Click_RoomPicker, driver);
		Thread.sleep(5000);
		Wait.WaitForElement(driver, OR.Click_RoomPicker);
		Wait.explicit_wait_xpath(OR.Click_RoomPicker, driver);
		ReservationPage.Click_RoomPicker.click();
		// test_steps.add("Successfully clicked on Rooms Picker");
		reservationLogger.info("Successfully clicked on Rooms Picker");

		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait5Second();

		Wait.WaitForElement(driver, OR.Click_Arrive_Datepicker);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Arrive_Datepicker, driver);
		Wait.explicit_wait_elementToBeClickable(ReservationPage.Click_Arrive_Datepicker, driver);
		Wait.wait5Second();
		ReservationPage.Click_Arrive_Datepicker.click();
		// test_steps.add("Successfully clicked on arrival date");
		reservationLogger.info("Successfully clicked on arrival date");

		ReservationPage.Click_Today.click();
		// test_steps.add("Successfully clicked on Today");
		reservationLogger.info("Successfully clicked on Today");

		ReservationPage.Enter_Nigts.clear();
		ReservationPage.Enter_Nigts.sendKeys(Nights);

		// test_steps.add("Successfully entered the nights : " +
		// Nights);
		reservationLogger.info("Successfully entered the nights : " + Nights);

		ReservationPage.Enter_Adults.clear();
		ReservationPage.Enter_Adults.sendKeys(Adults);
		// test_steps.add("Successfully entered the audlts : " +
		// Adults);
		reservationLogger.info("Successfully entered the audlts : " + Adults);

		ReservationPage.Enter_Children.clear();
		ReservationPage.Enter_Children.sendKeys(Children);
		// test_steps.add("Successfully entered the childrens : " +
		// Children);
		reservationLogger.info("Successfully entered the childrens : " + Children);
		Wait.wait2Second();
		if (ReservationPage.Check_Assign_Room.isSelected()) {

			if (CheckorUncheckAssign.equals("Yes")) {
				// ReservationPage.Check_Assign_Room.click();
				reservationLogger.info("Successfully clicked on assign rooms");

				ReservationPage.Click_Search.click();
				// test_steps.add("Successfully clicked on search");
				reservationLogger.info("Successfully clicked on search");
			} else {
				ReservationPage.Check_Assign_Room.click();
				ReservationPage.Click_Search.click();
				// test_steps.add("Successfully clicked on search");
				reservationLogger.info("Successfully clicked on search");
			}
		}

		int count = 1;

		WebElement ele = driver.findElement(
				By.xpath("//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr["
						+ count + "]/td[2]/select"));
		WebElement ele1 = driver.findElement(
				By.xpath("//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr["
						+ count + "]/td[3]/select"));
		Select sel = new Select(ele);
		if (count == 1) {

			sel.selectByIndex(1);
			// test_steps.add("Successfully selected the room class : "
			// + sel.getFirstSelectedOption().getText());
			reservationLogger.info("Successfully selected the room class : " + sel.getFirstSelectedOption().getText());
			sel = new Select(ele1);
			java.util.List<WebElement> options = sel.getOptions();
			int roomCount = 0;
			for (WebElement item : options) {
				// System.out.println(item.getText());
				if (roomCount == 1) {
					sel.selectByIndex(1);
					// test_steps.add("Successfully selected the room
					// number : " + item.getText());
					reservationLogger.info("Successfully selected the room number : " + item.getText());

					break;
				}
				roomCount++;
			}
		}

		ReservationPage.Click_Select.click();
		// test_steps.add("Successfully clicked on select");
		reservationLogger.info("Successfully clicked on select");
		Wait.wait3Second();
		String roomchargesChange = "//div[@id='divRoomPickerReCalculateFolios']//button[contains(text(),'Select')]";
		Wait.WaitForElement(driver, roomchargesChange);
		driver.findElement(By.xpath(roomchargesChange)).click();

		try {
			Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
		} catch (Exception e) {
			// System.out.println("Verification failed");
		}
		Wait.wait2Second();
		if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
			ReservationPage.Click_Continue_RuleBroken_Popup.click();
			// test_steps.add("Successfully clicked on rule brocken pop
			// up");
			reservationLogger.info("Successfully clicked on rule brocken pop up");
		} else {
			// System.out.println("Satisfied Rules");
		}

		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.Verify_Toaster_Container, driver);
		if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
			Wait.wait2Second();
			String getTotasterTitle = ReservationPage.Toaster_Title.getText();
			String getToastermessage = ReservationPage.Toaster_Message.getText();
			// System.out.println(getTotasterTitle + " " +getToastermessage);
			reservationLogger.info(getTotasterTitle + " " + getToastermessage);
			Assert.assertEquals(getTotasterTitle, "Room assignment has changed.");
			Assert.assertEquals(getToastermessage, "Room assignment has changed.");
		}

		String getPropertyName = ReservationPage.Get_Property_Name.getText();
		String getRoomclassName_status = ReservationPage.Get_RoomClass_Status.getText();
		// System.out.println(getRoomclassName_status);
		reservationLogger.info(getRoomclassName_status);
		// Assert.assertEquals(getPropertyName,PropertyName );
		String getRoomclassName[] = getRoomclassName_status.split(" : ");
		// System.out.println(getRoomclassName[0]+" "+getRoomclassName[1]);
		if (CheckorUncheckAssign.equals("Yes")) {

		} else {
			Assert.assertEquals(getRoomclassName[1], "Unassigned");
		}
		ReservationPage.Toaster_Title.click();
		// Wait.waitForElementToBeGone(driver, ReservationPage.Toaster_Title,
		// 30);
		reservationLogger.info("toaster message has been gone");

	}

	public void roomAssignment_1(WebDriver driver, ExtentTest test1, String Nights, String Adults, String Children,
			String CheckorUncheckAssign) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.Click_RoomPicker, driver);
		Utility.ScrollToElement(ReservationPage.Click_RoomPicker, driver);
		Thread.sleep(3000);
		ReservationPage.Click_RoomPicker.click();
		// test_steps.add("Successfully clicked on Rooms Picker");
		reservationLogger.info("Successfully clicked on Rooms Picker");

		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		// Wait.wait5Second();

		// Wait.WaitForElement(driver, OR.Click_Arrive_Datepicker);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Arrive_Datepicker, driver);
		ReservationPage.Click_Arrive_Datepicker.click();
		// test_steps.add("Successfully clicked on arrival date");
		reservationLogger.info("Successfully clicked on arrival date");

		ReservationPage.Click_Today.click();
		// test_steps.add("Successfully clicked on Today");
		reservationLogger.info("Successfully clicked on Today");

		ReservationPage.Enter_Nigts.clear();
		ReservationPage.Enter_Nigts.sendKeys(Nights);

		// test_steps.add("Successfully entered the nights : " +
		// Nights);
		reservationLogger.info("Successfully entered the nights : " + Nights);

		ReservationPage.Enter_Adults.clear();
		ReservationPage.Enter_Adults.sendKeys(Adults);
		// test_steps.add("Successfully entered the audlts : " +
		// Adults);
		reservationLogger.info("Successfully entered the audlts : " + Adults);

		ReservationPage.Enter_Children.clear();
		ReservationPage.Enter_Children.sendKeys(Children);
		// test_steps.add("Successfully entered the childrens : " +
		// Children);
		reservationLogger.info("Successfully entered the childrens : " + Children);
		if (CheckorUncheckAssign.equals("Yes")) {
			if (ReservationPage.Check_Assign_Room.isSelected()) {
				// ReservationPage.Check_Assign_Room.click();
				ReservationPage.Click_Search.click();
				// test_steps.add("Successfully clicked on assign rooms");
				reservationLogger.info("Successfully clicked Search");
			} else {
				ReservationPage.Check_Assign_Room.click();
				// test_steps.add("Successfully clicked on assign
				// rooms");
				reservationLogger.info("Successfully clicked on assign rooms");

				ReservationPage.Click_Search.click();
				// test_steps.add("Successfully clicked on search");
				reservationLogger.info("Successfully clicked on search");
			}
		} else {
			if (ReservationPage.Check_Assign_Room.isSelected()) {
				ReservationPage.Check_Assign_Room.click();

				reservationLogger.info("Successfully clicked on assign rooms");

				ReservationPage.Click_Search.click();
				// test_steps.add("Successfully clicked on assign rooms");
				reservationLogger.info("Successfully clicked Search");
			} else {

				ReservationPage.Click_Search.click();
				// test_steps.add("Successfully clicked on search");
				reservationLogger.info("Successfully clicked on search");
			}
		}

		int count = 1;

		WebElement ele = driver.findElement(
				By.xpath("//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr["
						+ count + "]/td[2]/select"));
		WebElement ele1 = driver.findElement(
				By.xpath("//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr["
						+ count + "]/td[3]/select"));
		Select sel = new Select(ele);
		if (count == 1) {

			sel.selectByIndex(1);
			// test_steps.add("Successfully selected the room class : "
			// + sel.getFirstSelectedOption().getText());
			reservationLogger.info("Successfully selected the room class : " + sel.getFirstSelectedOption().getText());
			sel = new Select(ele1);
			java.util.List<WebElement> options = sel.getOptions();
			int roomCount = 0;
			for (WebElement item : options) {
				// System.out.println(item.getText());
				if (roomCount == 1) {
					sel.selectByIndex(1);
					// test_steps.add("Successfully selected the room
					// number : " + item.getText());
					reservationLogger.info("Successfully selected the room number : " + item.getText());

					break;
				}
				roomCount++;
			}
		}

		ReservationPage.Click_Select.click();
		// test_steps.add("Successfully clicked on select");
		reservationLogger.info("Successfully clicked on select");
		try {
			Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
		} catch (Exception e) {
			// System.out.println("Verification failed");
		}
		Wait.wait2Second();
		if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
			ReservationPage.Click_Continue_RuleBroken_Popup.click();
			// test_steps.add("Successfully clicked on rule brocken pop
			// up");
			reservationLogger.info("Successfully clicked on rule brocken pop up");
		} else {
			// System.out.println("Satisfied Rules");
		}
		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.Verify_Toaster_Container, driver);
		if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
			Wait.wait2Second();
			String getTotasterTitle = ReservationPage.Toaster_Title.getText();
			String getToastermessage = ReservationPage.Toaster_Message.getText();
			// System.out.println(getTotasterTitle + " " +getToastermessage);
			reservationLogger.info(getTotasterTitle + " " + getToastermessage);
			Assert.assertEquals(getTotasterTitle, "Room assignment has changed.");
			Assert.assertEquals(getToastermessage, "Room assignment has changed.");
		}

		String getPropertyName = ReservationPage.Get_Property_Name.getText();
		String getRoomclassName_status = ReservationPage.Get_RoomClass_Status.getText();
		// System.out.println(getRoomclassName_status);
		reservationLogger.info(getRoomclassName_status);
		// Assert.assertEquals(getPropertyName,PropertyName );
		String getRoomclassName[] = getRoomclassName_status.split(" : ");
		// System.out.println(getRoomclassName[0]+" "+getRoomclassName[1]);
		if (CheckorUncheckAssign.equals("Yes")) {

		} else {
			Assert.assertEquals(getRoomclassName[1], "Unassigned");
		}
		ReservationPage.Toaster_Title.click();
		// Wait.waitForElementToBeGone(driver, ReservationPage.Toaster_Title,
		// 30);
		reservationLogger.info("toaster message has been gone");

	}

	public ArrayList<String> validateAccount(WebDriver driver, ExtentTest test1, String Account,
			ArrayList<String> test_steps) {

		driver.findElement(By.xpath("//a[contains(text(),'" + Account.trim() + "')]")).getText();
		test_steps.add(Account + " added successfully to Reservation");
		reservationLogger.info(Account + " added successfully to Reservation");

		return test_steps;
	}

	public void verifyPolicyAssociation(WebDriver driver, String PolicyName, String PolicyType)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		String Beforeediting = "";
		Select PolicySelect;
		Utility.ScrollToElement(ReservationPage.Reservation_Folio, driver);
		Wait.wait1Second();
		ReservationPage.Reservation_Folio.click();
		ReservationPage.Reservation_Folio_Options.click();
		switch (PolicyType.toLowerCase()) {

		case "deposit":

			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Select_Reservation_Deposit_Policy, driver);
			Utility.ScrollToElement(ReservationPage.Select_Reservation_Deposit_Policy, driver);
			PolicySelect = new Select(ReservationPage.Select_Reservation_Deposit_Policy);
			Beforeediting = PolicySelect.getFirstSelectedOption().getText();
			if (Beforeediting.equalsIgnoreCase(PolicyName)) {
				reservationLogger.info("Given Policy " + PolicyName + " is Associated Bydeafult");

			} else {
				reservationLogger.error("Given Policy is not Associated Bydeafult");
				reservationLogger.error(
						"Assosiating " + PolicyName + " Manully, Check there may be multiple policies of same type");
				PolicySelect.selectByVisibleText(PolicyName);
				Wait.wait3Second();
				Assert.assertNotEquals(PolicySelect.getFirstSelectedOption().getText(), Beforeediting,
						"Failed to Associate policy to Reservation.");
			}

			break;

		case "check in":
			Utility.ScrollToElement(ReservationPage.Select_Reservation_Checkin_Policy, driver);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Select_Reservation_Checkin_Policy, driver);
			Utility.ScrollToElement(ReservationPage.Select_Reservation_Checkin_Policy, driver);
			PolicySelect = new Select(ReservationPage.Select_Reservation_Checkin_Policy);
			Beforeediting = PolicySelect.getFirstSelectedOption().getText();
			if (Beforeediting.equalsIgnoreCase(PolicyName)) {
				reservationLogger.info("Given Policy " + PolicyName + " is Associated Bydeafult");

			} else {
				reservationLogger.error("Given Policy is not Associated Bydeafult");
				reservationLogger.error(
						"Assosiating " + PolicyName + " Manully, Check there may be multiple policies of same type");
				driver.findElement(By.xpath(OR.Select_Reservation_Checkin_Policy)).click();
				PolicySelect.selectByVisibleText(PolicyName);
				Wait.wait3Second();
			}
			break;
		case "cancellation":

			break;
		case "no show":

			break;
		}

		Utility.ScrollToElement(ReservationPage.Reservation_Folio_Details, driver);
		Wait.wait1Second();
		ReservationPage.Reservation_Folio_Details.click();// navigating back to
		// folio detials for
		// payments
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Pay_Button, driver);
	}

	public void RoomAssign(WebDriver driver, ExtentTest test, String RoomClass) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		ReservationPage.RoomAssignment_DateIcon.click();
		System.out.println("Click Date icon");
		ReservationPage.SelectDate.click();
		System.out.println("Select Date");
		Wait.wait2Second();
		ReservationPage.SearchRoomButton.click();
		System.out.println("Click Serach Button");
		Wait.wait3Second();
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
		System.out.println("Select Room Class");
		// test.log(LogStatus.PASS, "Select Room Class: " + RoomClass);
		reservationLogger.info("Select Room Class: " + RoomClass);
		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}
		new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
		Utility.roomIndex++;

		// new Select(ReservationPage.SelectRoomNumbers).selectByIndex(1);
		System.out.println("Select Room Number");
		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();

		Wait.wait2Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}

		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).click();
		Wait.wait2Second();
	}

	public void ScrollToRoomAssignButton(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-300)");
		Wait.wait10Second();
		System.out.println("Scrolled to RoomAssign Button");
	}

	public ArrayList<String> RoomAssignedReservation(WebDriver driver, ExtentTest test, String RoomClass,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignmentButton);
		Utility.ElementFinderUntillNotShow(By.xpath(OR.ClickSearchRoomButton), driver);

		Wait.wait3Second();
		Wait.WaitForElement(driver, OR.NightField);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.NightField, driver);
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignment_DateIcon);
		jse.executeScript("arguments[0].click();", ReservationPage.SelectDate);

		Wait.wait2Second();
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}
		jse.executeScript("arguments[0].click();", ReservationPage.SearchRoomButton);

		reservationLogger.info("RoomClass:" + RoomClass);
		Wait.WaitForElement(driver, OR.SelectRoomClasses);
		Wait.explicit_wait_xpath(OR.SelectRoomClasses, driver);
		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.SelectRoomClasses, driver);
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
		test_steps.add("Select room class: " + RoomClass);
		reservationLogger.info("Select room class: " + RoomClass);
		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}

		Wait.wait1Second();
		String roomnum = "(//tbody//select[contains(@data-bind,'RoomId')])/option";
		System.out.println(roomnum);
		int count = driver.findElements(By.xpath(roomnum)).size();
		Random random = new Random();
		int randomNumber = random.nextInt(count - 1) + 1;
		System.out.println("count : " + count);
		System.out.println("randomNumber : " + randomNumber);
		new Select(driver.findElement(By.xpath("(//tbody//select[contains(@data-bind,'RoomId')])")))
				.selectByIndex(randomNumber);

		// new
		// Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);

		// Utility.roomIndex++;
		// Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();

		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			Wait.WaitForElement(driver, OR.Continue);
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			ReservationPage.RoleBroken_Continue.click();
		}

		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);

		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Toaster_Message, driver);
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		ReservationPage.Toaster_Message.click();
		return test_steps;
	}

	public ArrayList<String> RoomAssignedReservation(WebDriver driver, ExtentTest test, String RoomClass,
			boolean NeedWait, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// Wait.explicit_wait_xpath(OR.RoomAssignmentButton);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignmentButton);
		// ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		// test_steps.add("Click Room Assignment");
		reservationLogger.info("Click Room Assignment");
		// Wait.explicit_wait_visibilityof_webelement(ReservationPage.RoomAssignment_DateIcon);
		Utility.ElementFinderUntillNotShow(By.xpath(OR.ClickSearchRoomButton), driver);
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignment_DateIcon);
		jse.executeScript("arguments[0].click();", ReservationPage.SelectDate);

		Wait.wait2Second();
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}

		jse.executeScript("arguments[0].click();", ReservationPage.SearchRoomButton);
		// test_steps.add("Click Search");
		reservationLogger.info("Click Search");
		// ReservationPage.RoomAssignment_DateIcon.click();
		// ReservationPage.SelectDate.click();
		// ReservationPage.RoomAssign_Check.click();
		// ReservationPage.SearchRoomButton.click();
		// Wait.wait2Second();
		if (NeedWait) {
			Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.SelectRoomClasses, driver);
		} else {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.SelectRoomClasses, driver);
		}
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
		test_steps.add("Select Room Class: " + RoomClass);
		reservationLogger.info("Select Room Class: " + RoomClass);
		// new Select(ReservationPage.SelectRoomNumbers).selectByIndex(1);

		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}
		new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
		Utility.roomIndex++;
		// Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		test_steps.add("Click Select");
		reservationLogger.info("Click Select");
		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).click();
		return test_steps;
	}

	public ArrayList<String> GSRoomAssignedReservation(WebDriver driver, ExtentTest test, String RoomClass,
			String Nights, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		ReservationPage.RoomAssignment_DateIcon.click();
		ReservationPage.SelectDate.click();
		ReservationPage.Enter_Nigts.clear();
		ReservationPage.Enter_Nigts.sendKeys(Nights);
		test_steps.add("Successfully entered the nights : " + Nights);
		reservationLogger.info("Successfully entered the nights : " + Nights);
		ReservationPage.SearchRoomButton.click();
		Wait.wait2Second();
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
		test_steps.add("Select Room Class: " + RoomClass);
		reservationLogger.info("Select Room Class: " + RoomClass);
		// new Select(ReservationPage.SelectRoomNumbers).selectByIndex(1);

		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}
		new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
		Utility.roomIndex++;

		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		Wait.wait2Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		return test_steps;
	}

	public ArrayList<String> GSRoomAssignedReservation(WebDriver driver, String RoomClass, String RoomNumber,
			boolean RoomAssign, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		ReservationPage.RoomAssignment_DateIcon.click();
		ReservationPage.SelectDate.click();
		Wait.wait2Second();
		if (RoomAssign) {
			if (!ReservationPage.RoomAssign_Check.isSelected()) {
				ReservationPage.RoomAssign_Check.click();
			}
		} else if (ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}
		ReservationPage.SearchRoomButton.click();
		Wait.wait2Second();
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
		if (RoomAssign) {
			new Select(ReservationPage.SelectRoomNumbers).selectByVisibleText(RoomNumber);
			test_steps.add("Select Room : " + RoomClass + " " + RoomNumber);
			reservationLogger.info("Select Room : " + RoomClass + " " + RoomNumber);
		}
		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		Wait.wait2Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		System.out.println(
				"Toaster Message appear : " + driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).getText());
		driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).click();
		Wait.wait3Second();
		return test_steps;
	}

	public void GSRoomAssignedReservation(WebDriver driver, String RoomClass, boolean RoomAssign)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.RoomAssignment_DateIcon, driver);
		ReservationPage.RoomAssignment_DateIcon.click();
		ReservationPage.SelectDate.click();
		Wait.wait2Second();
		if (RoomAssign) {
			if (!ReservationPage.RoomAssign_Check.isSelected()) {
				ReservationPage.RoomAssign_Check.click();
			}
		} else if (ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}
		ReservationPage.SearchRoomButton.click();
		Wait.wait2Second();
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
		if (RoomAssign) {

			if (Utility.roomIndex == Utility.roomIndexEnd) {
				Utility.roomIndex = 1;
			}
			new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
			Utility.roomIndex++;

			// new Select(ReservationPage.SelectRoomNumbers).selectByIndex(1);
			reservationLogger.info("Select Room : " + RoomClass + " " + RoomNumber);
		}
		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		Wait.wait2Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		System.out.println(
				"Toaster Message appear : " + driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).getText());
		driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).click();
		Wait.wait3Second();
	}

	public ArrayList<String> AssignRoom_NewRoomClass(WebDriver driver, String RoomClass, boolean RoomAssign,
			boolean SplitRoom, ArrayList<String> test_steps, String Nights) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.RoomAssignmentButton.click();
		test_steps.add("Clicked Room Assignment Button ");
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.RoomAssignment_DateIcon, driver);

		test_steps.add("Room Assignment Popup Appeared");
		ReservationPage.RoomAssignment_DateIcon.click();
		ReservationPage.SelectDate.click();

		test_steps.add("Select Room Assignment Date");
		if (Nights != "1") {
			ReservationPage.NightField.clear();
			ReservationPage.NightField.sendKeys(Nights);
			Utility.app_logs.info("Enter Nights: " + Nights);
			test_steps.add("Enter Nights: " + Nights);
		}
		Wait.wait2Second();
		if (SplitRoom) {
			if (!ReservationPage.Check_Split_Rooms.isSelected()) {
				ReservationPage.Check_Split_Rooms.click();
			}
		} else if (ReservationPage.Check_Split_Rooms.isSelected()) {
			ReservationPage.Check_Split_Rooms.click();
		}
		if (RoomAssign) {
			if (!ReservationPage.RoomAssign_Check.isSelected()) {
				ReservationPage.RoomAssign_Check.click();
			}
		} else if (ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}

		ReservationPage.SearchRoomButton.click();
		if (SplitRoom) {
			Utility.app_logs.info("Split Room Assignment");
			test_steps.add("Split Room Assignment");
			for (int i = 0; i < Integer.valueOf(Nights); i++) {
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRooms.get(i), driver);
				new Select(ReservationPage.SplitRooms.get(i)).selectByVisibleText(RoomClass);
				test_steps.add("Select Room Class: " + RoomClass);
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRoomNumbers.get(i), driver);
				new Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(1);

				if (Utility.roomIndex == Utility.roomIndexEnd) {
					Utility.roomIndex = 1;
				}
				new Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(Utility.roomIndex);
				Utility.roomIndex++;
				String Room = new Select(ReservationPage.SplitRooms.get(i)).getFirstSelectedOption().getText() + " : "
						+ new Select(ReservationPage.SplitRoomNumbers.get(i)).getFirstSelectedOption().getText();
				test_steps.add("Select Room: " + Room);
				reservationLogger.info("Select Room: " + Room);
				assertTrue(Room.contains(RoomClass), "Failed: Room Class is not " + RoomClass);

			}
		} else {
			Utility.app_logs.info("Room Assignment");
			// test_steps.add("Room Assignment");
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.SelectRoomClasses, driver);
			new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
			String Room = new Select(ReservationPage.SelectRoomClasses).getFirstSelectedOption().getText();
			if (RoomAssign) {
				new Select(ReservationPage.SelectRoomNumbers).selectByIndex(1);
				Room = Room + " : " + new Select(ReservationPage.SelectRoomNumbers).getFirstSelectedOption().getText();
			}

			test_steps.add("Select Room: " + Room);
			reservationLogger.info("Select Room: " + Room);
			assertTrue(Room.contains(RoomClass), "Failed: Room Class is not " + RoomClass);

		}

		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();

		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.Toaster_Message, driver);
		Wait.waitUntilPresenceOfElementLocatedByClassName(OR.Toaster_Message, driver);
		System.out
				.println("Toaster Message appear : " + driver.findElement(By.className(OR.Toaster_Message)).getText());
		driver.findElement(By.className(OR.Toaster_Message)).click();
		return test_steps;
	}

	public ArrayList<String> RoomAssignment(WebDriver driver, String RoomClass, boolean RoomAssign,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Utility.ElementFinderUntillNotShow(By.xpath(OR.ClickSearchRoomButton), driver);
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignment_DateIcon);
		jse.executeScript("arguments[0].click();", ReservationPage.SelectDate);
		Wait.wait2Second();
		if (RoomAssign) {
			if (!ReservationPage.RoomAssign_Check.isSelected()) {
				jse.executeScript("arguments[0].click();", ReservationPage.RoomAssign_Check);
			}
		} else if (ReservationPage.RoomAssign_Check.isSelected()) {
			jse.executeScript("arguments[0].click();", ReservationPage.RoomAssign_Check);
		}
		jse.executeScript("arguments[0].click();", ReservationPage.SearchRoomButton);
		Wait.wait2Second();
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
		if (RoomAssign) {

			if (Utility.roomIndex == Utility.roomIndexEnd) {
				Utility.roomIndex = 1;
			}
			new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
			Utility.roomIndex++;

			test_steps.add("Select Room : " + RoomClass + " " + RoomNumber);
			reservationLogger.info("Select Room : " + RoomClass + " " + RoomNumber);
		}
		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		Wait.wait2Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.Toaster_Message, driver);
		System.out.println(
				"Toaster Message appear : " + driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).getText());
		driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).click();
		Wait.wait3Second();
		return test_steps;
	}

	public ArrayList<String> RoomAssignedReservation(WebDriver driver, ExtentTest test, String RoomClass, String Nights,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Utility.ElementFinderUntillNotShow(By.xpath(OR.ClickSearchRoomButton), driver);
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignment_DateIcon);
		jse.executeScript("arguments[0].click();", ReservationPage.SelectDate);
		Wait.wait1Second();
		ReservationPage.Enter_Nigts.clear();
		ReservationPage.Enter_Nigts.sendKeys(Nights);
		test_steps.add("Successfully entered the nights : " + Nights);
		reservationLogger.info("Successfully entered the nights : " + Nights);
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssign_Check);

		try {
			ReservationPage.SearchRoomButton.click();
			Wait.wait3Second();
			test_steps.add("Click Search");
			reservationLogger.info("Click Search");
			// Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.SelectRoomClasses);
			// Thread.sleep(10000);
			reservationLogger.info("After Wait");
			ReservationPage.SelectRoomClasses.click();
			reservationLogger.info("Click RoomClass Dropdown");
			new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
		} catch (Exception e) {
			reservationLogger.info("Catch Body");
			try {
				jse.executeScript("arguments[0].click();", ReservationPage.SearchRoomButton);
				Wait.wait2Second();
				test_steps.add("Click Search");
				reservationLogger.info("Click Search");
				// Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.SelectRoomClasses);
				Thread.sleep(10000);
				reservationLogger.info("After Wait");
				jse.executeScript("arguments[0].click();", ReservationPage.SelectRoomClasses);
				reservationLogger.info("Click RoomClass Dropdown");
				new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
			} catch (Exception e1) {
				reservationLogger.info("Catch body of Catch");
				analyzeLog(driver, test_steps);
			}
		}
		test_steps.add("Select Room Class: " + RoomClass);
		reservationLogger.info("Select Room Class: " + RoomClass);

		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}
		new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
		Utility.roomIndex++;

		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		Wait.wait2Second();

		if (ReservationPage.RoomSelectPopup.isDisplayed()) {
			ReservationPage.RoomDirtyButton.click();
			Wait.wait2Second();
		}

		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Toaster_Message, driver);
		// Wait.explicit_wait_absenceofelement(OR.Toaster_Message);
		return test_steps;
	}

	public ArrayList<String> ChangeRoom(WebDriver driver, String RoomClass, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
		test_steps.add("Select Room Class: " + RoomClass);
		reservationLogger.info("Select Room Class: " + RoomClass);
		// new Select(ReservationPage.SelectRoomNumbers).selectByIndex(1);

		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}
		new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
		Utility.roomIndex++;

		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		Wait.wait2Second();
		try {
			if (ReservationPage.click_Select_Button_RoomChargesChanged.isDisplayed()) {
				ReservationPage.click_Select_Button_RoomChargesChanged.click();
			}
		} catch (Exception e) {
			System.out.println("RoomCharged changed not displayed");
		}
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.wait2Second();
		try {
			if (ReservationPage.Policy_Comparision_PopUp.isDisplayed()) {
				ReservationPage.Policy_Comparision_PopUp_Continue_Btn.click();
			}
		} catch (Exception e) {
			System.out.println("Policy Comparision Popup not displayed ");
		}
		Wait.waitUntilPresenceOfElementLocatedByClassName(OR.Toaster_Message, driver);
		return test_steps;

	}

	public String ChangeNumberOfNights(WebDriver driver, String NumberOfNights) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.RoomAssignmentButton, driver);
		ReservationPage.RoomAssignmentButton.click();
		reservationLogger.info("Click Room Assignment Button");
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);

		Wait.explicit_wait_absenceofelement(OR.RoomAssignment_ModuleLoading, driver);
		System.out.println(ReservationPage.NightField.getAttribute("value"));
		System.out.println(ReservationPage.NightField.getText());

		if (ReservationPage.NightField.getAttribute("value").contains("1")) {
			NumberOfNights = Integer.toString(Integer.parseInt(NumberOfNights) + 1);
		}
		ReservationPage.NightField.clear();
		ReservationPage.NightField.sendKeys(NumberOfNights);
		Wait.wait5Second();
		reservationLogger.info("Change Number of Nights");
		ReservationPage.ClickSearchRoomButton.click();
		reservationLogger.info("Click Search ");
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.SelectButton, driver);

		try {
			ReservationPage.SelectButton.click();

			reservationLogger.info("Click Select");
			Wait.wait3Second();

			if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
				ReservationPage.Click_Continue_RuleBroken_Popup.click();
				reservationLogger.info("Clicked on continue button of RulesBroken Popup");
			} else {
				reservationLogger.info("Satisfied Rules");
			}
			Wait.wait3Second();
			if (ReservationPage.click_Select_Button_RoomChargesChanged.isDisplayed()) {
				ReservationPage.click_Select_Button_RoomChargesChanged.click();
			} else {
				System.out.println("RoomCharged changed not displayed");
			}
		} catch (Exception e) {
			// analyzeLog(driver, test_steps);
			System.out.println("AnalyzeLog");

		}
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		if (ReservationPage.Policy_Comparision_PopUp.isDisplayed()) {
			ReservationPage.Policy_Comparision_PopUp_Continue_Btn.click();
		} else {
			System.out.println("Policy Comparision Popup not displayed ");
		}
		return NumberOfNights;
	}

	public void SaveReservation(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		// Wait.explicit_wait_visibilityof_webelement_600(Elements_Reservations.ReservationSaveButton);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationSaveButton, driver);

		Elements_Reservations.ReservationSaveButton.click();
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Toaster_Message, driver);
		Elements_Reservations.Toaster_Message.click();
		Wait.wait2Second();
		boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;
		if (size) {
			Elements_Reservations.CancelDepositePolicy_Button.get(3).click();
			Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Toaster_Message, driver);
			Elements_Reservations.Toaster_Message.click();
			Wait.wait2Second();
		}

	}

	public void SaveRes(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.ReservationSaveButton, driver);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", Elements_Reservations.ReservationSaveButton);
		Wait.waitUntilPresenceOfElementLocated(OR.ReservationSaveButton, driver);
		Elements_Reservations.ReservationSaveButton.click();

		Utility.app_logs.info("Click Save");
		Wait.wait2Second();
		boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;
		if (size) {
			Elements_Reservations.CancelDepositePolicy_Button.get(3).click();

		}
		try {
			// Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message,
			// driver);
			System.out.println(
					"Toaster Message appear : " + driver.findElement(By.cssSelector(OR.Toaster_Message)).getText());
			driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).click();
			Wait.wait2Second();
		} catch (Exception e) {
			System.err.println("Toaster not displayed");
		}
	}

	public void SaveRes_2(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.ReservationSaveButton_2, driver);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", Elements_Reservations.ReservationSaveButton_2);
		Wait.wait2Second();
		Wait.waitUntilPresenceOfElementLocated(OR.ReservationSaveButton_2, driver);
		jse.executeScript("arguments[0].click();", Elements_Reservations.ReservationSaveButton_2);
		Utility.app_logs.info("Click Save");
		Wait.wait2Second();
		boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;
		if (size) {
			Elements_Reservations.CancelDepositePolicy_Button.get(3).click();

		}
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		System.out.println(
				"Toaster Message appear : " + driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).getText());
		Wait.wait3Second();
	}

	public void SaveReservation_DepositPolicy(WebDriver driver) throws InterruptedException {
		Wait.wait2Second();
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationSaveButton, driver);
		Utility.ScrollToElement(Elements_Reservations.ReservationSaveButton, driver);
		/*
		 * JavascriptExecutor jse = (JavascriptExecutor) driver;
		 * jse.executeScript("arguments[0].click();",
		 * Elements_Reservations.ReservationSaveButton);
		 */
		Wait.isElementDisplayed(driver, Elements_Reservations.ReservationSaveButton);
		Elements_Reservations.ReservationSaveButton.click();
		reservationLogger.info("Click on save button");
		Wait.wait2Second();

		try {
			Wait.WaitForElement(driver, OR.Toaster_Message);
			// Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(OR.Toaster_Message)),
			// driver);
			WebElement Message = driver.findElement(By.xpath(OR.Toaster_Message));
			String Text = Message.getText();
			System.out.println("Toaster Message appear : " + Text);
			Message.click();
			if (Text.contains(
					"The existing room is not available for the criteria you provided. Please either change the criteria, or check the �Split Room� check box and try again.")) {
				reAssignRoom(driver);

			}
		} catch (Exception e) {

			boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;
			System.out.println("Size:" + size);
			if (size) {
				Wait.wait1Second();
				System.out.println("Deposite Policy");
				Elements_Reservations.CancelDepositePolicy_Button.get(3).click();

			}
			// Wait.explicit_wait_visibilityof_webelement_350(Elements_Reservations.Toaster_Title,
			// driver);
			Wait.WaitForElement(driver, OR.Toaster_Title);

			if (Elements_Reservations.Toaster_Title.isDisplayed()) {
				String getTotasterTitle_ReservationSucess = Elements_Reservations.Toaster_Title.getText();
				if (getTotasterTitle_ReservationSucess.equals(
						"Elements_Reservations.CancelDepositePolicy_Button.get(3)Elements_Reservations.CancelDepositePolicy_Button.get(3)Elements_Reservations.CancelDepositePolicy_Button.get(3)Elements_Reservations.CancelDepositePolicy_Button.get(3)Elements_Reservations.CancelDepositePolicy_Button.get(3)Elements_Reservations.CancelDepositePolicy_Button.get(3)Elements_Reservations.CancelDepositePolicy_Button.get(3) Saved")) {
					Assert.assertEquals(getTotasterTitle_ReservationSucess, "Reservation Saved");
				} else if (getTotasterTitle_ReservationSucess.equals("Error while saving.")) {
					reAssignRoom(driver);
					PolicyPopup(driver);
				}
			} else {
				System.err.println("Toaster_Message is not displaying ");
			}
			Wait.wait3Second();

		}

	}

	public void SaveRes_CheckDepositPolicy(WebDriver driver) throws InterruptedException, IOException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationSaveButton, driver);
		Utility.ScrollToElement(Elements_Reservations.ReservationSaveButton, driver);

		Wait.isElementDisplayed(driver, Elements_Reservations.ReservationSaveButton);
		Elements_Reservations.ReservationSaveButton.click();
		reservationLogger.info("Click on save button");

		try {
			Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.cssSelector(OR.Toaster_Message)), driver);
			WebElement Message = driver.findElement(By.cssSelector(OR.Toaster_Message));
			String Text = Message.getText();
			reservationLogger.info("Toaster Message appear : " + Text);
			Message.click();

			if (Text.contains(
					"The existing room is not available for the criteria you provided. Please either change the criteria, or check the ‘Split Room’ check box and try again.")) {
				reAssignRoom(driver);
				Elements_Reservations.Toaster_Message.click();

			}

		} catch (Exception e) {

			boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;

			if (size) {
				Wait.wait1Second();
				Elements_Reservations.CancelDepositePolicy_Button.get(3).click();
				Wait.wait1Second();

			}
			try {
				Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Toaster_Message, driver);

				if (Elements_Reservations.Toaster_Title.isDisplayed()) {
					String getTotasterTitle_ReservationSucess = Elements_Reservations.Toaster_Title.getText();
					Elements_Reservations.Toaster_Title.click();

					if (getTotasterTitle_ReservationSucess.equals("Saved")) {
						Assert.assertEquals(getTotasterTitle_ReservationSucess, "Reservation Saved");

					} else if (getTotasterTitle_ReservationSucess.equals("Error while saving.")) {
						reAssignRoom(driver);
						PolicyPopup(driver);
					}
				}
			} catch (Exception e2) {
				reservationLogger.info("in finally");
				String reservationNumber = GetReservationnumber(driver);
				CloseOpenedReservation(driver);
				Wait.wait2Second();
				driver.navigate().refresh();
				Wait.wait2Second();

				ReservationSearch reservationSearch = new ReservationSearch();
				reservationSearch.basicSearch_WithResNumber(driver, reservationNumber, true);
				Wait.wait2Second();
				Wait.WaitForElement(driver, OR.FolioTab_Reservation);
				Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.FolioTab_Reservation, driver);
				Wait.wait2Second();
				Elements_Reservations.FolioTab_Reservation.click();
				reservationLogger.info("after folio");

			}

		}
		Wait.wait3Second();

	}

	public void PolicyPopup(WebDriver driver) throws InterruptedException {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationSaveButton, driver);
		Utility.ScrollToElement(Elements_Reservations.ReservationSaveButton, driver);
		Elements_Reservations.ReservationSaveButton.click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;

		if (size) {
			jse.executeScript("arguments[0].click();", Elements_Reservations.CancelDepositePolicy_Button.get(3));
			// Elements_Reservations.CancelDepositePolicy_Button.get(3).click();

		}
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.className(OR.Toaster_Message)), driver);
		WebElement Message = driver.findElement(By.className(OR.Toaster_Message));
		System.out.println("Toaster Message appear : " + Message.getText());
		Message.click();

	}

	public void reAssignRoom(WebDriver driver) throws InterruptedException {
		// Wait.wait5Second();
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.RoomAssignmentButton, driver);
		Utility.app_logs.info("Re assignment of room");

		Utility.ScrollToElement(Elements_Reservations.RoomAssignmentButton, driver);
		Elements_Reservations.RoomAssignmentButton.click();
		Wait.wait1Second();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);

		new Select(Elements_Reservations.SelectRoomClasses).selectByIndex(1);
		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}
		new Select(Elements_Reservations.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
		Utility.roomIndex++;
		String CheckRule = Elements_Reservations.CheckRule.getText();
		String AvailableRoom = Elements_Reservations.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		Elements_Reservations.SelectButton.click();

		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			Elements_Reservations.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			Elements_Reservations.RoleBroken_Continue.click();
		}
		Wait.waitUntilPresenceOfElementLocatedByClassName(OR.Toaster_Message, driver);
		driver.findElement(By.className(OR.Toaster_Message)).click();
	}

	public void reAssignRoom(WebDriver driver, String RoomClass) throws InterruptedException {
		// Wait.wait5Second();
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.RoomAssignmentButton, driver);
		Utility.app_logs.info("Re assignment of room");

		Utility.ScrollToElement(Elements_Reservations.RoomAssignmentButton, driver);
		Elements_Reservations.RoomAssignmentButton.click();
		Wait.wait1Second();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);

		new Select(Elements_Reservations.SelectRoomClasses).selectByVisibleText(RoomClass);
		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}
		new Select(Elements_Reservations.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
		Utility.roomIndex++;
		String CheckRule = Elements_Reservations.CheckRule.getText();
		String AvailableRoom = Elements_Reservations.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		Elements_Reservations.SelectButton.click();

		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			Elements_Reservations.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			Elements_Reservations.RoleBroken_Continue.click();
		}
		Wait.waitUntilPresenceOfElementLocatedByClassName(OR.Toaster_Message, driver);
		driver.findElement(By.className(OR.Toaster_Message)).click();
	}

	public void SaveResCheckDepositPolicy(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationSaveButton, driver);
		Utility.ScrollToElement(Elements_Reservations.ReservationSaveButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Elements_Reservations.ReservationSaveButton);

		// Elements_Reservations.ReservationSaveButton.click();
		reservationLogger.info("Click on save button");

		try {
			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(OR.Toaster_Message_Xpath)),
					driver);
			WebElement Message = driver.findElement(By.xpath(OR.Toaster_Message_Xpath));
			System.out.println("Toaster Message appear : " + Message.getText());
			if (Message.getText().contains("Please fill")) {
				Thread.sleep(10000);
				jse.executeScript("arguments[0].click();", Elements_Reservations.ReservationSaveButton);
				Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(OR.Toaster_Message_Xpath)),
						driver);
				Message = driver.findElement(By.xpath(OR.Toaster_Message_Xpath));
				System.out.println("Toaster Message appear : " + Message.getText());
				assertTrue(Message.getText().contains("Please fill"), "Failed Save Reservation");
				Message.click();
			} else {
				Message.click();
			}
		} catch (Exception e) {
			boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;
			if (size) {
				Elements_Reservations.CancelDepositePolicy_Button.get(3).click();

			}
			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(OR.Toaster_Message_Xpath)),
					driver);
			WebElement Message = driver.findElement(By.xpath(OR.Toaster_Message_Xpath));
			System.out.println("Toaster Message appear : " + Message.getText());
			Message.click();
			Wait.wait1Second();
		}
	}
	// When Create New Reservation Button

	public void SaveRes_WithoutDepositPolicy(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationSaveButton, driver);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", Elements_Reservations.ReservationSaveButton);
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.ReservationSaveButton, driver);
		Elements_Reservations.ReservationSaveButton.click();
		reservationLogger.info("Click on save button");
		Wait.wait2Second();
		Elements_Reservations.DepositPolicy_Button.get(3).click();
		// Wait.wait2Second();
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.Toaster_Message_Xpath)), driver);
		WebElement Message = driver.findElement(By.xpath(OR.Toaster_Message_Xpath));
		System.out.println("Toaster Message appear : " + Message.getText());
		Message.click();
		/*
		 * Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message,driver);
		 * driver.findElement(By.xpath(OR.Toaster_Message)).click();
		 */
		Wait.wait2Second();

	}

	public ArrayList<String> UncheckTaxExempt(WebDriver driver, ExtentTest test, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.wait2Second();
		// Java script object creation
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0,1000)");
		if (ReservationPage.Check_IsTaxExempt.isSelected()) {

			Wait.WaitForElement(driver, OR.Check_IsTaxExempt);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Check_IsTaxExempt, driver);
			Utility.ScrollToElement(ReservationPage.Check_IsTaxExempt, driver);
			ReservationPage.Check_IsTaxExempt.click();
			test_steps.add("UnChecked: Tax exempt check box");
			reservationLogger.info("UnChecked: Tax exempt check box");

			Wait.wait3Second();
		}
		return test_steps;
	}

	public void RemoveBalanceFromReservation(WebDriver driver, String Reason) throws InterruptedException {
		Wait.wait3Second();

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_350(Elements_Reservations.Select_AllFolio, driver);
		// int SizeBeforeRemoveBalance = TableSize.size();
		// System.out.print("Size:" + SizeBeforeRemoveBalance);

		Utility.ScrollToElement(Elements_Reservations.Select_AllFolio, driver);
		Elements_Reservations.Select_AllFolio.click();
		Utility.ScrollToElement(Elements_Reservations.BtnVoid, driver);
		Elements_Reservations.BtnVoid.click();
		Wait.WaitForElement(driver, OR.TextArea_Notest);
		Wait.explicit_wait_visibilityof_webelement_600(Elements_Reservations.TextArea_Notest, driver);
		Elements_Reservations.TextArea_Notest.sendKeys(Reason);
		Utility.ScrollToElement(Elements_Reservations.Notes_Void, driver);
		Elements_Reservations.Notes_Void.click();
		Wait.wait5Second();
		List<WebElement> TableSize = driver
				.findElements(By.xpath("//table[@class='table table-striped table-bordered table-hover trHeight25']"));
		int SizeAfterRemoveBalance = TableSize.size();
		System.out.print("Size:" + SizeAfterRemoveBalance);
		// assertTrue(SizeBeforeRemoveBalance > SizeAfterRemoveBalance, "Balance
		// didn't remove from reservation");

	}

	public void SaveAfterReservation(WebDriver driver) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 90);
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.ReservationSaveButton);
		wait.until(ExpectedConditions.elementToBeClickable(Elements_Reservations.ReservationSaveButton));

		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.ReservationSaveButton, driver);
		Utility.ScrollToElement(Elements_Reservations.ReservationSaveButton, driver);
		Elements_Reservations.ReservationSaveButton.isDisplayed();
		Elements_Reservations.ReservationSaveButton.click();
		reservationLogger.info("Click on save button");

		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		Wait.explicit_wait_visibilityof_webelement_350(Elements_Reservations.Toaster_Message, driver);

		try {
			System.out.println(Elements_Reservations.Toaster_Message.getText());
			Elements_Reservations.Toaster_Message.click();
		} catch (Exception e) {
			// TODO: handle exception
		}

		Wait.wait2Second();

	}

	public void SaveAfter_Reservation(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.ReservationSaveButton);
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.ReservationSaveButton, driver);
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.elementToBeClickable(Elements_Reservations.ReservationSaveButton));

		Elements_Reservations.ReservationSaveButton.click();
		reservationLogger.info("Click on save button");

		try {
			Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
			Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Toaster_Message, driver);
			Elements_Reservations.Toaster_Message.click();
			Wait.wait2Second();
		} catch (Exception e) {
			System.err.println("Toaster_Message is not displaying ");
		}

	}

	public void SaveCloseRes(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.SaveAndCloseReservation, driver);
		Elements_Reservations.SaveAndCloseReservation.click();
		Wait.wait2Second();
		boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;
		if (size) {
			Elements_Reservations.CancelDepositePolicy_Button.get(3).click();
		}

		// Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message);

	}

	public void CopyButton(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", Elements_Reservations.CopyButton);
		Wait.wait2Second();
		Elements_Reservations.CopyButton.click();
		System.out.println("Click Copy");
		Wait.wait5Second();
		int size = Elements_Reservations.ReservationTab.size();
		assertTrue(Elements_Reservations.ReservationTab.get(size - 1).getText().contains("copy"),
				"Copy of reservation does not create");

	}

	public void ResetReservation(WebDriver driver, String FirstName) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.GestInfo_Tab.click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", ReservationPage.Enter_First_Name);
		String getFirstName = ReservationPage.Enter_First_Name.getAttribute("value");

		ReservationPage.Enter_First_Name.sendKeys(" " + FirstName);
		ReservationPage.Enter_Last_Name.sendKeys("");
		Wait.wait5Second();
		ReservationPage.Res_Reset_Button.click();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Enter_First_Name, driver);
		assertEquals(ReservationPage.Enter_First_Name.getAttribute("value"), getFirstName, "Reset failed");

	}

	public ArrayList<String> StayDates(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation elements_Reservations = new Elements_Reservation(driver);

		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.GuestInfo_Tab, driver);
		elements_Reservations.GuestInfo_Tab.click();

		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.ReservationFolioBalance, driver);
		String beforeBalance = elements_Reservations.ReservationFolioBalance.getText();
		test_steps.add(
				"Verified balance before click on radio button of No Room Charge Changed/Added  : " + beforeBalance);

		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.Depart_Value.get(1), driver);
		String beforeStayDates = elements_Reservations.Depart_Value.get(1).getText();
		test_steps.add("Before click on radio button of No Room Charge Changed/Added : " + beforeStayDates);
		elements_Reservations.ExtendReservation_Button.click();

		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.RoomChargerPopup, driver);
		assertEquals(elements_Reservations.RoomChargerPopup.getText(), "Room Charges Changed",
				"Room charger popup deos not display");

		elements_Reservations.NoRoomCharger_RadioButton.click();
		elements_Reservations.Button_RoomChargerSelect.click();

		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.Toaster_Message, driver);
		elements_Reservations.Toaster_Message.click();
		Wait.wait2Second();

		String AfterStayDates = elements_Reservations.Depart_Value.get(1).getText();
		test_steps.add("After click on radio button of No Room Charge Changed/Added : " + AfterStayDates);
		assertNotEquals(AfterStayDates, beforeStayDates, "Stay dates does not perform");

		test_steps.add("Date has been changed after click on radio button of No Room Charge Changed/Added");
		test_steps
				.add("Verified balance after click on radio button of No Room Charge Changed/Added: " + beforeBalance);

		return test_steps;

	}

	public ArrayList<String> Recalcualte_Folio(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation elements_Reservations = new Elements_Reservation(driver);

		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.GuestInfo_Tab, driver);
		Wait.wait1Second();
		// elements_Reservations.GuestInfo_Tab.click();

		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.ReservationFolioBalance, driver);
		String beforeBalance = elements_Reservations.ReservationFolioBalance.getText();
		test_steps.add("Before recalculate folio balance : " + beforeBalance);

		String before_recalculate = elements_Reservations.Depart_Value.get(1).getText();
		elements_Reservations.ExtendReservation_Button.click();
		Wait.wait1Second();

		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.RoomChargerPopup, driver);
		assertEquals(elements_Reservations.RoomChargerPopup.getText(), "Room Charges Changed",
				"Room charger popup deos not display");

		elements_Reservations.RecalculateFolio_RadioButton.click();
		elements_Reservations.Button_RoomChargerSelect.click();

		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		elements_Reservations.Toaster_Message.click();
		String after_recalculate = elements_Reservations.Depart_Value.get(1).getText();

		assertNotEquals(after_recalculate, before_recalculate, "Recalculate folio does not perform");
		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.ReservationFolioBalance, driver);
		String afterBalance = elements_Reservations.ReservationFolioBalance.getText();

		String[] str1 = beforeBalance.split(" ");
		double beforeRec_Balance = Double.parseDouble(str1[1]);
		reservationLogger.info("before balance : " + beforeRec_Balance);

		// here verify folio amount
		int size = elements_Reservations.List_LineItemAmount.size();
		assertEquals(size, 2, "Line item did not increase after Recalculate in guest info");
		Utility.ScrollToElement(elements_Reservations.List_LineItemAmount.get(size - 1), driver);
		String lineItem_Amount = elements_Reservations.List_LineItemAmount.get(size - 1).getText();
		System.out.println("Folio balance : " + lineItem_Amount);

		String[] str_FolioAmount = lineItem_Amount.split(" ");
		double folio_Balance = Double.parseDouble(str_FolioAmount[1]);
		double totla_balance = beforeRec_Balance + folio_Balance;
		test_steps.add("Expected Folio balance : " + totla_balance);

		String[] str2 = afterBalance.split(" ");
		double afterRec_Balance = Double.parseDouble(str2[1]);
		test_steps.add("After recalculate folio balance : " + afterBalance);

		assertEquals(afterRec_Balance, totla_balance, "Folio balance is not changed");
		test_steps.add("Verified folio balance before and after recalculate folio");
		return test_steps;

	}

	public void Ext_Dec_Stay_Date(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		String before_ext_date = Elements_Reservations.Depart_Value.get(1).getText();
		Elements_Reservations.ExtendReservation_Button.click();
		Wait.wait2Second();
		assertEquals(Elements_Reservations.RoomChargerPopup.getText(), "Room Charges Changed",
				"Room charger popup deos not display");
		Elements_Reservations.ApplyDeltaEnabled_RadioButton.click();
		Elements_Reservations.Button_RoomChargerSelect.click();

		Utility.ElementFinderUntillNotShow(By.xpath(OR.Toaster_Message_Xpath), driver);
		Thread.sleep(5000);
		String after_ext_date = Elements_Reservations.Depart_Value.get(1).getText();
		assertNotEquals(after_ext_date, before_ext_date, "Date does not extend or decend");
	}

	public void NoShowReservation(WebDriver driver, ExtentTest test1) throws InterruptedException {

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		Wait.wait3Second();
		Wait.explicit_wait_visibilityof_webelement(elements_Reservation.NoShowIcon, driver);
		Utility.ScrollToElement(elements_Reservation.NoShowIcon, driver);
		elements_Reservation.NoShowIcon.click();
		Wait.wait5Second();
		// Wait.waitUntilPresenceOfElementLocated(OR.VoidRoomChargerCheckbox);
		elements_Reservation.VoidRoomChargerCheckbox.get(1).click();

		assertEquals(elements_Reservation.OkbuttonInNowShow.get(1).getText(), "OK", "couldn't  find ok button");
		elements_Reservation.OkbuttonInNowShow.get(1).click();
		Thread.sleep(10000);
		Wait.waitUntilPresenceOfElementLocated(OR.RollBackStatus_Select, driver);
		// Check reservation status
		Select select = new Select(elements_Reservation.RollBackStatus);
		WebElement option = select.getFirstSelectedOption();
		String defaultItem = option.getText();
		assertTrue(defaultItem.contains("Show"), "Rollback status does not change Reserved to NO Show");
		reservationLogger.info("Status has been changed Reserved to NO Show");

	}

	// adding validations
	public ArrayList<String> NoShow_Reservation(WebDriver driver, boolean VoidRoomCharges, boolean NoShowFee,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation elements = new Elements_Reservation(driver);
		Wait.wait3Second();
		Wait.WaitForElement(driver, OR.NoShowIcon);
		Wait.explicit_wait_visibilityof_webelement(elements.NoShowIcon, driver);
		Utility.ScrollToElement(elements.NoShowIcon, driver);
		elements.NoShowIcon.click();
		test_steps.add("Click Reservation's NoShow Icon");
		reservationLogger.info("Click Reservation's NoShow Icon");
		Wait.explicit_wait_visibilityof_webelement(elements.NOShow_VoidRoomCharges, driver);
		assertTrue(elements.NOShow_VoidRoomCharges.isDisplayed(),
				"Failed: NO Show Void Room Charges CheckBox not visible");
		assertTrue(elements.NOShow_OkButton.isDisplayed(), "Failed: NO Show Ok Button not visible");
		assertTrue(elements.NOShow_CancelButton.isDisplayed(), "Failed: NO Show Cancel Button not visible");
		test_steps.add("Reservation's NoShow Popup Appeared");
		// Wait.waitUntilPresenceOfElementLocated(OR.VoidRoomChargerCheckbox);
		if (VoidRoomCharges) {
			if (!elements.NOShow_VoidRoomCharges.isSelected()) {
				elements.NOShow_VoidRoomCharges.click();
				test_steps.add("Click Void Room Charges CheckBox of NoShow Popup");
			}
			assertTrue(elements.NOShow_VoidRoomCharges.isSelected(),
					"Failed: Void Room Charges CheckBox of NoShow Popup is Not Selected");
			test_steps.add("Void Room Charges : Checked");

			reservationLogger.info("Void Room Charges : Checked");
			Wait.explicit_wait_visibilityof_webelement(elements.NOShow_NoShowFee, driver);
			if (NoShowFee) {
				if (!elements.NOShow_NoShowFee.isSelected()) {
					elements.NOShow_NoShowFee.click();
					test_steps.add("Click NoShowFee CheckBox of NoShow Popup");
				}
				assertTrue(elements.NOShow_NoShowFee.isSelected(),
						"Failed: NoShow Fee CheckBox of NoShow Popup is Not Selected");
				test_steps.add("NoShow Fee : Checked");

				reservationLogger.info("NoShow Fee : Checked");
			} else {
				if (elements.NOShow_NoShowFee.isSelected()) {
					elements.NOShow_NoShowFee.click();
					test_steps.add("Click NoShowFee CheckBox of NoShow Popup");
				}
				assertTrue(!elements.NOShow_NoShowFee.isSelected(),
						"Failed: NoShow Fee CheckBox of NoShow Popup is Selected");
				test_steps.add("NoShow Fee : UnChecked");
				reservationLogger.info("NoShow Fee : UnChecked");
			}
		} else {
			if (elements.NOShow_VoidRoomCharges.isSelected()) {
				elements.NOShow_VoidRoomCharges.click();
				test_steps.add("Click Void Room Charges CheckBox of NoShow Popup");
			}
			assertTrue(!elements.NOShow_VoidRoomCharges.isSelected(),
					"Failed: Void Room Charges CheckBox of NoShow Popup is Selected");
			test_steps.add("Void Room Charges : UnChecked");
			reservationLogger.info("Void Room Charges : UnChecked");
		}

		Utility.ScrollToElement(elements.NOShow_OkButton, driver);
		elements.NOShow_OkButton.click();
		test_steps.add("Click OK Button NoShow Popup");
		reservationLogger.info("Click OK Button NoShow Popup");
		Wait.explicit_wait_visibilityof_webelement(elements.Toaster_Message, driver);
		String Text = elements.Toaster_Message.getText();
		try {
			elements.Toaster_Message.click();
		} catch (Exception e) {
			System.out.println("Toaster Message disappeared");
		}
		System.out.println("Toaster Message appear : " + Text);
		test_steps.add("Reservation" + Text);

		return test_steps;

	}

	public void ReservedStatus(WebDriver driver, ExtentTest test1) throws InterruptedException {

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_Reservation.RollBackButton, driver);
		Utility.ScrollToElement(elements_Reservation.RollBackButton, driver);
		elements_Reservation.RollBackButton.click();
		Thread.sleep(10000);

		String CheckRule = elements_Reservation.CheckRule.getText();
		String AvailableRoom = elements_Reservation.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", elements_Reservation.SelectButton);
		Wait.wait5Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			elements_Reservation.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			elements_Reservation.RoleBroken_Continue.click();
			Wait.wait2Second();
		}

		Thread.sleep(5000);
		Select select = new Select(elements_Reservation.RollBackStatus);
		WebElement option = select.getFirstSelectedOption();
		String defaultItem = option.getText();
		assertEquals(defaultItem, "Reserved", "Status does not change");
		reservationLogger.info("Set Reserved as RESERVATION STATUS");

	}

	public ArrayList<String> RollBack_StatusReserved(WebDriver driver, String RoomClass, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_Reservation.RollBackButton, driver);
		Utility.ScrollToElement(elements_Reservation.RollBackButton, driver);
		elements_Reservation.RollBackButton.click();
		test_steps.add("Click Roll Back Reservation");
		reservationLogger.info("Click Roll Back Icon of Reservation");
		Wait.explicit_wait_visibilityof_webelement(elements_Reservation.Room_Assignment_PopUp, driver);
		assertTrue(elements_Reservation.Room_Assignment_PopUp.isDisplayed(),
				"Failed: Room Assignment Popupnot Appeared");
		test_steps.add("Room Assignment Popup Appeared");

		Utility.ScrollToElement(elements_Reservation.Click_Search, driver);
		elements_Reservation.Click_Search.click();
		test_steps.add("Click Search Rooms");
		reservationLogger.info("Click Search");
		Wait.explicit_wait_visibilityof_webelement_350(elements_Reservation.SelectRoomClasses, driver);
		new Select(elements_Reservation.SelectRoomClasses).selectByVisibleText(RoomClass);
		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}
		new Select(elements_Reservation.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
		Utility.roomIndex++;
		String RoomName = new Select(elements_Reservation.SelectRoomClasses).getFirstSelectedOption().getText() + " : "
				+ new Select(elements_Reservation.SelectRoomNumbers).getFirstSelectedOption().getText();
		test_steps.add("Selected Room  " + RoomName);
		reservationLogger.info("Selected Room " + RoomName);
		assertTrue(RoomName.contains(RoomClass), "Failed: Room Class is not " + RoomClass);
		String CheckRule = elements_Reservation.CheckRule.getText();
		String AvailableRoom = elements_Reservation.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		elements_Reservation.SelectButton.click();
		test_steps.add("Click Select Room");
		reservationLogger.info("Click Select");
		Wait.wait5Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			elements_Reservation.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			elements_Reservation.RoleBroken_Continue.click();
			Wait.wait2Second();
		}
		try {
			Wait.explicit_wait_visibilityof_webelement(elements_Reservation.RecalculateRoomCharges, driver);
			elements_Reservation.RecalculateRoomCharges.click();
			test_steps.add("Click Recalculate Room Charges");
			reservationLogger.info("Click Recalculate Room Charges");
			Wait.explicit_wait_visibilityof_webelement(elements_Reservation.click_Select_Button_RoomChargesChanged,
					driver);
			elements_Reservation.click_Select_Button_RoomChargesChanged.click();
			test_steps.add("Click Select Button of Room Charges Changed");
			reservationLogger.info("Click Select Button of Room Charges Changed");
			Wait.waitForElementToBeGone(driver, elements_Reservation.click_Select_Button_RoomChargesChanged, 30);
		} catch (Exception e) {
			Utility.app_logs.info("No Recalculate Popup Appear");
		}
		try {
			Wait.explicit_wait_visibilityof_webelement(elements_Reservation.Policy_Comparision_PopUp, driver);
			if (elements_Reservation.Policy_Comparision_PopUp.isDisplayed()) {
				elements_Reservation.Policy_Comparision_PopUp_Continue_Btn.click();
				test_steps.add("Click Continue Button of Policy Comparison Popup");
			}
		} catch (Exception e) {
			System.out.println("Policy Comparision Popup not displayed ");
		}

		return test_steps;

	}

	public ArrayList<String> UnassignedToSplitAssigned(WebDriver driver, ExtentTest test, String RoomClass,
			String NightStay, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignment_DateIcon);
		jse.executeScript("arguments[0].click();", ReservationPage.Click_Today);

		ReservationPage.NightField.clear();
		ReservationPage.NightField.sendKeys(NightStay);
		Wait.wait1Second();
		jse.executeScript("arguments[0].click();", ReservationPage.Check_Split_Rooms);
		reservationLogger.info("Checked Room Split checkbox");
		Wait.wait1Second();
		// jse.executeScript("arguments[0].click();",
		// ReservationPage.ClickSearchRoomButton);
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}
		ReservationPage.SearchRoomButton.click();

		for (int i = 0; i < Integer.valueOf(NightStay); i++) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRooms.get(i), driver);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRoomNumbers.get(i), driver);
			new Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(1);
		}
		//
		test_steps.add("Select Room Class: " + RoomClass);
		reservationLogger.info("Select Room Class: " + RoomClass);
		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		jse.executeScript("arguments[0].click();", ReservationPage.click_Select_Button_RoomChargesChanged);

		// ReservationPage.click_Select_Button_RoomChargesChanged.click();
		return test_steps;
	}

	public String[] RoomAssign_WithSplit(WebDriver driver, ExtentTest test, String RoomClass, String NightStay)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		ReservationPage.RoomAssignment_DateIcon.click();
		ReservationPage.SelectDate.click();
		// String Date = ReservationPage.SelectDate.getText();

		ReservationPage.NightField.clear();
		ReservationPage.NightField.sendKeys(NightStay);
		Wait.wait1Second();
		if (!ReservationPage.Check_Split_Rooms.isSelected()) {
			ReservationPage.Check_Split_Rooms.click();
		}
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}
		reservationLogger.info("Checked Room Split checkbox");
		ReservationPage.SearchRoomButton.click();

		String[] roomNos = new String[Integer.parseInt(NightStay)];
		for (int i = 0; i < Integer.valueOf(NightStay); i++) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRooms.get(i), driver);
			new Select(ReservationPage.SplitRooms.get(i)).selectByVisibleText(RoomClass);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRoomNumbers.get(i), driver);

			if (Utility.roomIndex == Utility.roomIndexEnd) {
				Utility.roomIndex = 1;
			}
			// new
			// Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(Utility.roomIndex);
			Wait.wait1Second();
			String roomnum = "(//tbody//select[contains(@data-bind,'RoomId')])[" + (i + 1) + "]/option";
			System.out.println(roomnum);
			int count = driver.findElements(By.xpath(roomnum)).size();
			Random random = new Random();
			int randomNumber = random.nextInt(count - 1) + 1;
			System.out.println("count : " + count);
			System.out.println("randomNumber : " + randomNumber);
			new Select(
					driver.findElement(By.xpath("(//tbody//select[contains(@data-bind,'RoomId')])[" + (i + 1) + "]")))
							.selectByIndex(randomNumber);

			roomNos[i] = new Select(ReservationPage.SplitRoomNumbers.get(i)).getFirstSelectedOption().getText();
			Utility.roomIndex++;
		}

		// test_steps.add("Select Room Class: " + RoomClass);
		reservationLogger.info("Select Room Class: " + RoomClass);
		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		Wait.wait2Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Toaster_Message, driver);
		ReservationPage.Toaster_Message.click();
		Wait.wait2Second();

		return roomNos;
	}

	public void Res_Book_Quote(WebDriver driver) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.DatePickerIcon.get(5), driver);
		TapeChart.DatePickerIcon.get(5).click();
		TapeChart.SelectDate.click();
		TapeChart.Quote_SearchButton.click();
		Wait.wait2Second();
		TapeChart.Reservation_Book_Button.get(0).click();

		Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
		Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);

	}

	public void Res_Book_Quote(WebDriver driver, String RoomClassName) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(TapeChart.DatePickerIcon.get(5), driver);
		TapeChart.DatePickerIcon.get(5).click();
		TapeChart.SelectDate.click();
		TapeChart.Quote_SearchButton.click();
		Wait.wait2Second();
		String BookButtonPath = "//a[text()='" + RoomClassName
				+ "']//ancestor::tr//following-sibling::button[contains(@class,'rate-book')]";
		Utility.app_logs.info(BookButtonPath);
		WebElement BookButton = driver.findElement(By.xpath(BookButtonPath));
		Utility.ScrollToElement(BookButton, driver);
		BookButton.click();
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_RulesBroken_Popup, driver);
			if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
				ReservationPage.Click_Continue_RuleBroken_Popup.click();
				reservationLogger.info("Clicked on continue button of RulesBroken Popup");
			} else {
				reservationLogger.info("Satisfied Rules");
			}
		} catch (Exception e) {
			reservationLogger.info("Satisfied Rules");
		}

		Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
		Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);

	}

	public ArrayList<String> UnassignedRes_Book_Quote(WebDriver driver, String RoomClassName,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		TapeChart.DatePickerIcon.get(5).click();
		TapeChart.SelectDate.click();
		if (TapeChart.AssignRoomCheckBox.isSelected()) {
			TapeChart.AssignRoomCheckBox.click();
		}
		assertTrue(!TapeChart.AssignRoomCheckBox.isSelected(), "Failed: Assign Room is Checked");
		test_steps.add("Assign Room: Unchecked");

		TapeChart.Quote_SearchButton.click();
		Wait.explicit_wait_xpath(OR.Rooms_Table, driver);
		test_steps.add("Click Search");
		Wait.wait2Second();

		for (int i = 1; i <= 7; i++) {
			System.out.println(driver.findElement(By.xpath("(" + OR.Rooms_Table + "//th)[" + i + "]")).getText());
			assertTrue(driver.findElement(By.xpath("(" + OR.Rooms_Table + "//th)[" + i + "]")).isDisplayed(),
					"Failed: Room Class table");
		}
		String BookButtonPath = "//a[text()='" + RoomClassName
				+ "']//ancestor::tr//following-sibling::button[contains(@class,'rate-book')]";
		WebElement BookButton = driver.findElement(By.xpath(BookButtonPath));
		Utility.ScrollToElement(BookButton, driver);
		BookButton.click();
		try {
			if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
				ReservationPage.Click_Continue_RuleBroken_Popup.click();
				reservationLogger.info("Clicked on continue button of RulesBroken Popup");
			} else {
				reservationLogger.info("Satisfied Rules");
			}
		} catch (Exception e) {

		}

		Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
		Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);
		Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
		Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);
		String Room = ReservationPage.ReservationRoomStatus.getText();
		String RoomClass = Room.split("\\:")[0].replaceAll("\\s+", "");
		String RoomNumber = Room.split("\\:")[1].replaceAll("\\s+", "");
		System.out.println("RC:" + RoomClass + " RN:" + RoomNumber);
		Wait.wait2Second();
		assertTrue(!RoomClass.contains("Unassigned"), "Failed: RoomClass is  Unassigned");
		System.out.println("Room Class : " + RoomClass);
		assertTrue(RoomNumber.contains("Unassigned"), "Failed: RoomNumber is not Unassigned");
		System.out.println("Room Number : " + RoomNumber);
		return test_steps;
	}

	public ArrayList<String> VerifyLongStay(WebDriver driver, ExtentTest test, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);

		Elements_Reservations.Check_isLongstay.click();

		Wait.wait2Second();

		assertEquals(Elements_Reservations.TaxExmpt_Popup.getText(), "Tax Exempt", "Tax exempt popup does not display");

		assertEquals(Elements_Reservations.Set_TaxExe_Button.getText(), "Set Exemption Anyway",
				"Set Exemption Anyway button does not display");

		assertEquals(Elements_Reservations.Cancel_TaxExe_Button.getText(), "Cancel Exemption",
				"Cancel Exemption button does not display");

		test_steps.add("Verify Tax Exempt functionlity");
		reservationLogger.info("Verify Tax Exempt functionlity");

		Elements_Reservations.Cancel_TaxExe_Button.click();
		Wait.wait2Second();
		return test_steps;

	}

	public void SaveReservation_WithPolicy(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_xpath(OR.ReservationSaveButton, driver);
		Elements_Reservations.ReservationSaveButton.click();

		// Utility.ElementFinderUntillNotShow(By.xpath(OR.Toaster_Message),
		// driver);
		Wait.wait2Second();

	}

	public void SaveReservation_WithoutPolicy(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.ReservationSaveButton);
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.ReservationSaveButton, driver);
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.elementToBeClickable(Elements_Reservations.ReservationSaveButton));

		Elements_Reservations.ReservationSaveButton.click();
		Wait.wait1Second();

	}

	public void click_PayFolio(WebDriver driver, String PropertyName, String RoomClassName, String CheckorUncheckAssign,
			String PaymentType, String CardName, String CCNumber, String CCExpiry, String CCVCode,
			String Authorizationtype, String ChangeAmount, String ChangeAmountValue, String traceData,
			ArrayList test_steps) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// Wait.wait5Second();
		Wait.explicit_wait_visibilityof_webelement(moveFolio.MoveFolio_Folio, driver);
		// Wait.WaitForElement(driver, OR.MoveFolio_Folio);
		Utility.ScrollToElement(moveFolio.MoveFolio_Folio, driver);
		// moveFolio.MoveFolio_Folio.click();
		jse.executeScript("arguments[0].click();", moveFolio.MoveFolio_Folio);

		String bal = Elements_Reservations.Verify_Balance_Zero.getText();

		bal = bal.replace("$", " ");
		bal = bal.trim();
		System.out.println("Double:" + bal);
		Double d = Double.parseDouble(bal);
		d = d + 5;

		Wait.WaitForElement(driver, OR.Click_Pay_Button);
		Utility.ScrollToElement(Elements_Reservations.Click_Pay_Button, driver);
		Elements_Reservations.Click_Pay_Button.click();
		reservationLogger.info("Clicking on Pay");
		try {
			Wait.explicit_wait_xpath(OR.TakePaymentPopUp, driver);

		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.Verify_Payment_Details_poup, driver);
		}

		System.out.println(PaymentType);
		new Select(Elements_Reservations.Select_Paymnet_Method).selectByVisibleText(PaymentType);
		try {
			Elements_Reservations.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			Elements_Reservations.Change_Amount.sendKeys(d.toString());

		} catch (Exception e) {
			reservationLogger.info("Catch Body");
			String amount = "(//label[.='Amount:'])[2]/following-sibling::div/input";
			driver.findElement(By.xpath(amount)).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			driver.findElement(By.xpath(amount)).sendKeys(d.toString());
		}

		Wait.wait3Second();
		System.out.println(d.toString());
		if (PaymentType.contains("Cash")) {
			Wait.WaitForElement(driver, OR.Click_ADD);
			// jse.executeScript("arguments[0].click();",
			// Elements_Reservations.Click_ADD);
			Elements_Reservations.Click_ADD.click();
			reservationLogger.info("Clicking on ADD");
		} else {
			Wait.WaitForElement(driver, OR.Click_Process);
			// jse.executeScript("arguments[0].click();",
			// Elements_Reservations.Click_Process);
			Elements_Reservations.Click_Process.click();
			reservationLogger.info("Clicking on Process");
		}

		Wait.wait3Second();
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(Elements_Reservations.Click_Continue));
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Click_Continue, driver);
		Wait.WaitForElement(driver, OR.Click_Continue);
		Elements_Reservations.Click_Continue.click();
		reservationLogger.info("Clicking on Continue");
		Thread.sleep(5000);
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Verify_Balance_Zero, driver);

		Wait.WaitForElement(driver, OR.Verify_Balance_Zero);
		bal = Elements_Reservations.Verify_Balance_Zero.getText();

		bal = bal.replace("$", " ");
		bal = bal.replace("(", " ");
		bal = bal.replace(")", " ");
		bal = bal.trim();

		d = Double.parseDouble(bal);
		SaveAfterReservation(driver);

		/*
		 * Wait.WaitForElement(driver, OR.Click_Checkin);
		 * Elements_Reservations.Click_Checkin.click();
		 * reservationLogger.info("Clicking on Check In");
		 */

		CheckinNew(driver, PropertyName, RoomClassName, CheckorUncheckAssign, PaymentType, CardName, CCNumber, CCExpiry,
				CCVCode, Authorizationtype, ChangeAmount, ChangeAmountValue, traceData, test_steps);

	}

	public void CheckInWithNonZeroBlnc(WebDriver driver, String PropertyName, String RoomClassName,
			String CheckorUncheckAssign, String PaymentType, String CardName, String CCNumber, String CCExpiry,
			String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue, String traceData)
			throws InterruptedException {

		CheckinWithBlnc(driver, PropertyName, RoomClassName, CheckorUncheckAssign, PaymentType, CardName, CCNumber,
				CCExpiry, CCVCode, Authorizationtype, ChangeAmount, ChangeAmountValue, traceData);

	}

	public void click_PayFolioAccount(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// Wait.wait5Second();
		Wait.explicit_wait_visibilityof_webelement(moveFolio.MoveFolio_Folio, driver);
		// Wait.WaitForElement(driver, OR.MoveFolio_Folio);
		Utility.ScrollToElement(moveFolio.MoveFolio_Folio, driver);
		// moveFolio.MoveFolio_Folio.click();
		jse.executeScript("arguments[0].click();", moveFolio.MoveFolio_Folio);

		String bal = Elements_Reservations.Verify_Balance_Zero.getText();

		bal = bal.replace("$", " ");
		bal = bal.trim();
		Double d = Double.parseDouble(bal);
		d = d + 5;

		Wait.WaitForElement(driver, OR.Click_Pay_Button);
		Utility.ScrollToElement(Elements_Reservations.Click_Pay_Button, driver);
		Elements_Reservations.Click_Pay_Button.click();
		reservationLogger.info("Clicking on Pay");
		/*
		 * try { Wait.explicit_wait_xpath(OR.TakePaymentPopUp, driver);
		 * 
		 * } catch (Exception e) {
		 */
		Wait.explicit_wait_xpath(OR.Verify_Payment_Details_poup, driver);
		// }

		new Select(Elements_Reservations.Select_Paymnet_Method).selectByIndex(1);

		Wait.wait3Second();

		Wait.WaitForElement(driver, OR.Click_ADD);
		Wait.explicit_wait_xpath(OR.Click_ADD, driver);
		Wait.explicit_wait_elementToBeClickable(Elements_Reservations.Click_ADD, driver);
		Wait.explicit_wait_xpath(OR.Click_ADD, driver);
		Elements_Reservations.Click_ADD.click();
		reservationLogger.info("Clicking on ADD");

		Wait.wait3Second();
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(Elements_Reservations.Click_Continue));
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Click_Continue, driver);
		Wait.WaitForElement(driver, OR.Click_Continue);
		Elements_Reservations.Click_Continue.click();
		reservationLogger.info("Clicking on Continue");
		Thread.sleep(5000);
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Verify_Balance_Zero, driver);

		Wait.WaitForElement(driver, OR.Verify_Balance_Zero);
		bal = Elements_Reservations.Verify_Balance_Zero.getText();

		bal = bal.replace("$", " ");
		bal = bal.replace("(", " ");
		bal = bal.replace(")", " ");
		bal = bal.trim();

		d = Double.parseDouble(bal);
		SaveAfterReservation(driver);

	}

	public ArrayList<String> bulkCheckout(WebDriver driver, ExtentTest test1, String reservation,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);

		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);

		Wait.wait2Second();
		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		resservationSearch.Basic_Res_Number.clear();
		// Wait.wait2Second();
		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		resservationSearch.Basic_Res_Number.sendKeys(reservation);
		resservationSearch.Click_BasicSearch.click();
		Wait.explicit_wait_xpath(OR.Verify_Search_Loading_Gird, driver);
		// Wait.wait10Second();

		Wait.WaitForElement(driver, OR.Get_Res_Number);
		// String GetResNumber=resservationSearch.Get_Res_Number.getText();
		Wait.wait3Second();

		// String locator = "//span[contains(text(),'" + reservation +
		// "')]/../../td[1]/div/label/div/input";

		// Wait.WaitForElement(driver, locator);

		String checkbox_selector = "checker";

		driver.findElements(By.className(checkbox_selector)).get(1).click();
		reservationLogger.info("Selectring the reservation : " + reservation);

		Wait.WaitForElement(driver, OR.BulkAction);
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.BulkAction, driver);
		Elements_Reservations.BulkAction.click();
		reservationLogger.info("Clicking on Bulk Action");

		Wait.WaitForElement(driver, OR.BulkActionCheckOut);
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.BulkActionCheckOut, driver);
		Elements_Reservations.BulkActionCheckOut.click();
		reservationLogger.info("Clicking on Bulk Action Checkout");

		Wait.WaitForElement(driver, OR.BulkActionCheckOutGrid);
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.BulkActionCheckOutGrid, driver);

		if (Integer.parseInt(
				driver.findElement(By.xpath("//span[@data-bind='text: ProcessedList().length']")).getText()) == 1
				&& Integer.parseInt(
						driver.findElement(By.xpath("//a[contains(text(),'Reservations(S) can be updated')]/span"))
								.getText()) == 1) {

			Wait.WaitForElement(driver, "(//button[contains(text(),'Process')])[3]");
			driver.findElement(By.xpath("(//button[contains(text(),'Process')])[3]")).click();
			reservationLogger.info("Clicking on Process");

			Wait.WaitForElement(driver, "(//a[@class='panel-heading'])[1]/span");
			Wait.explicit_wait_visibilityof_webelement(
					driver.findElement(By.xpath("(//a[@class='panel-heading'])[1]/span")), driver);
			if (Integer
					.parseInt(driver.findElement(By.xpath("(//a[@class='panel-heading'])[1]/span")).getText()) == 1) {

				Wait.WaitForElement(driver, "(//button[contains(text(),'Close')])[7]");
				Wait.explicit_wait_visibilityof_webelement(
						driver.findElement(By.xpath("(//button[contains(text(),'Close')])[7]")), driver);
				driver.findElement(By.xpath("(//button[contains(text(),'Close')])[7]")).click();
				reservationLogger.info("Clicking on Close");
				Wait.wait5Second();
				WebDriverWait wait = new WebDriverWait(driver, 120);
				wait.until(ExpectedConditions.elementToBeClickable(driver
						.findElement(By.xpath("//span[contains(text(),'" + reservation + "')]/../../td[10]/span"))));
				Wait.WaitForElement(driver, "//span[contains(text(),'" + reservation + "')]/../../td[10]/span");
				Wait.explicit_wait_visibilityof_webelement(driver.findElement(
						By.xpath("//span[contains(text(),'" + reservation + "')]/../../td[10]/span")), driver);
				String str = driver
						.findElement(By.xpath("//span[contains(text(),'" + reservation + "')]/../../td[10]/span"))
						.getText();
				if (str.contains("Departed")) {
					reservationLogger.info("Reservation sucessfully checkout");
					test_steps.add("Reservation sucessfully checkout");
				} else {
					reservationLogger.info("Reservation not sucessfully checkout");
					test_steps.add("Reservation not sucessfully checkout");
				}
			}
		}
		return test_steps;
	}

	public ArrayList<String> VerifyRule(WebDriver driver, String Night, String Adult, String Childern, String RoomClass,
			String RuleName, String RuleDescription, ExtentTest test, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		Wait.explicit_wait_elementToBeClickable(ReservationPage.RoomAssignmentButton, driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		test_steps.add("Room Assignment Clicked");
		reservationLogger.info("Room Assignment Clicked");
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.RoomAssignment_DateIcon, driver);
		Wait.explicit_wait_elementToBeClickable(ReservationPage.RoomAssignment_DateIcon, driver);
		ReservationPage.RoomAssignment_DateIcon.click();
		ReservationPage.SelectDate.click();
		Wait.wait2Second();
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}

		ReservationPage.SearchRoomButton.click();
		Wait.wait2Second();
		test_steps.add("Search Clicked");
		reservationLogger.info("Search Clicked");
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);

		new Select(ReservationPage.SelectRoomNumbers).selectByIndex(1);
		assertTrue(ReservationPage.CheckRule.isDisplayed(), "Failed: Rule Button Not Displayed");
		assertEquals(ReservationPage.CheckRule.getCssValue("color"), "rgba(224, 34, 34, 1)",
				"Color not Matched, should be red");
		reservationLogger.info("Check rule Color Red");
		test_steps.add("Check rule Color Red");

		ReservationPage.CheckRule.click();

		Wait.wait2Second();
		assertEquals(ReservationPage.Rules_Popup.getText(), "Rules Broken", "Rule does not broken");
		String ruleName = driver
				.findElement(By
						.xpath("//*[@id='ruleMessageForInnroad']//table/tbody/tr/td[contains(@data-bind,'text: RuleName')]"))
				.getText();
		assertEquals(ruleName, RuleName, "Rule Not Verified, RuleName not matched");
		String ruleDesc = driver
				.findElement(By
						.xpath("//*[@id='ruleMessageForInnroad']//table/tbody/tr/td[contains(@data-bind,'text: RuleDescription')]"))
				.getText();
		assertEquals(ruleDesc, RuleDescription, "Rule Not Verified, Rule Desc not matched");

		test_steps.add("Rules Broken");
		reservationLogger.info("Rules Broken");
		ReservationPage.OK_Button.click();
		Wait.wait2Second();
		test_steps.add("OK Clicked");
		reservationLogger.info("OK Clicked");

		ReservationPage.Enter_Nigts.clear();
		ReservationPage.Enter_Adults.clear();
		ReservationPage.Enter_Children.clear();

		ReservationPage.Enter_Nigts.sendKeys(Night);
		test_steps.add("Enter Nights : " + Night);
		reservationLogger.info("Enter Nights : " + Night);
		ReservationPage.Enter_Adults.sendKeys(Adult);
		test_steps.add("Enter Adults : " + Adult);
		reservationLogger.info("Enter Adults : " + Adult);
		ReservationPage.Enter_Children.sendKeys(Childern);
		test_steps.add("Enter Childern : " + Childern);
		reservationLogger.info("Enter Childern : " + Childern);
		ReservationPage.SearchRoomButton.click();
		test_steps.add("Search Clicked");
		reservationLogger.info("Search Clicked");
		Wait.wait2Second();
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);

		new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);

		assertTrue(ReservationPage.More_Rules_button.get(0).isDisplayed(), "Failed: Rule Button Not Displayed");
		assertEquals(ReservationPage.More_Rules_button.get(0).getCssValue("color"), "rgba(37, 177, 59, 1)",
				"Color not Matched, should be green");
		reservationLogger.info("Check rule Color Green");
		test_steps.add("Check rule Color Green");
		ReservationPage.More_Rules_button.get(0).click();
		Wait.wait2Second();
		assertEquals(ReservationPage.Rules_Popup.getText(), "Rules Applicable", "Rule does not applicable");
		ruleName = driver
				.findElement(By
						.xpath("//*[@id='ruleMessageForInnroad']//table/tbody/tr/td[contains(@data-bind,'text: RuleName')]"))
				.getText();
		assertEquals(ruleName, RuleName, "Rule Not Verified, RuleName not matched");
		ruleDesc = driver
				.findElement(By
						.xpath("//*[@id='ruleMessageForInnroad']//table/tbody/tr/td[contains(@data-bind,'text: RuleDescription')]"))
				.getText();
		assertEquals(ruleDesc, RuleDescription, "Rule Not Verified, Rule Desc not matched");
		test_steps.add("Rules Applicable");
		reservationLogger.info("Rules Applicable");
		ReservationPage.OK_Button.click();
		test_steps.add("OK Clicked");
		reservationLogger.info("OK Clicked");
		Wait.wait2Second();
		ReservationPage.RoomAssign_Cancel.click();

		Wait.wait2Second();
		return test_steps;
	}

	public void VerifyRoomTask(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.GestInfo_Tab.click();
		Utility.ScrollToElement(ReservationPage.NotesType.get(0), driver);
		assertEquals(ReservationPage.NotesType.get(0).getText(), "Room Move", "fail to verify room task");
		assertEquals(ReservationPage.NotesType.get(1).getText(), "Room Move", "fail to verify room task");

	}

	public void SelectRoomWithMultipeNight(WebDriver driver, ExtentTest test, String RoomClass, String NightStay,
			String Child, String Adult) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		ReservationPage.RoomAssignment_DateIcon.click();
		ReservationPage.SelectDate.click();

		ReservationPage.NightField.clear();
		ReservationPage.Enter_Children.clear();
		ReservationPage.Enter_Adults.clear();

		ReservationPage.NightField.sendKeys(NightStay);
		ReservationPage.Enter_Adults.sendKeys(Adult);
		ReservationPage.Enter_Children.sendKeys(Child);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssign_Check);

		// ReservationPage.Check_Split_Rooms.click();
		// reservationLogger.info("Checked Room Split and assign room
		// checkbox");
		ReservationPage.SearchRoomButton.click();
		Wait.wait2Second();

		// Select room classes and room
		new Select(ReservationPage.SelectRoomClass.get(0)).selectByVisibleText(RoomClass);
		new Select(ReservationPage.SelectRoomClass.get(1)).selectByVisibleText(RoomClass);
		// new
		// Select(ReservationPage.SelectRoomClass.get(2)).selectByVisibleText(RoomClass);
		new Select(ReservationPage.SelectRoomNumber.get(0)).selectByIndex(2);
		new Select(ReservationPage.SelectRoomNumber.get(1)).selectByIndex(3);
		// new Select(ReservationPage.SelectRoomNumber.get(2)).selectByIndex(4);

		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		Wait.wait2Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).click();
	}

	public void ExportReport(WebDriver driver, String ReportType) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		ReservationPage.PrintIcon.click();
		Wait.WaitForElement(driver, OR.Report_Popup);
		assertEquals(ReservationPage.Report_Popup.getText(), "Choose a Report", "Report popup does not display");
		ReservationPage.Select_ReportType.click();
		Wait.wait2Second();
		String path = "//div[.='" + ReportType + "']";
		driver.findElement(By.xpath(path)).click();
		Wait.wait2Second();
	}

	public void CheckIN_RA_Confirm(WebDriver driver, String PaymentType, String Amount) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Actions action = new Actions(driver);

		Wait.wait1Second();
		ReservationPage.Click_Checkin.click();
		reservationLogger.info("Clicked on CheckIn button");

		try {
			Wait.explicit_wait_10sec(ReservationPage.Already_Checked_In_Confirm_Popup, driver);
			ReservationPage.Already_Checked_In_Confirm_Popup_Confirm_Btn.click();
			flag = true;
		} catch (Exception e) {
			reservationLogger.info("No conflicts with room assignment");
		}
		if (flag == true) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Room_Assignment_PopUp, driver);
			Select selectRoomNumber = new Select(ReservationPage.Select_Room_Number);
			String selectedOption = selectRoomNumber.getFirstSelectedOption().getText();
			if (selectedOption.contains("(O and")) {

				String RoomwithVandC = driver.findElement(By.xpath("//option[contains(text(),'(V and C)')] "))
						.getAttribute("value");
				selectRoomNumber.selectByValue(RoomwithVandC);
				reservationLogger.info("Selected first available room with V and C status from the list");
				Wait.wait2Second();
				ReservationPage.Click_Select.click();
				reservationLogger.info("Clicked on select button of room assignment popup");
				try {

					Wait.explicit_wait_visibilityof_webelement(ReservationPage.ReCalculate_Folio_Options_PopUp, driver);
					if (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
						ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();
						action.moveToElement(ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn).click().build()
								.perform();

					}
				} catch (Exception e) {
					reservationLogger.info("No ReCalculate Folio Options PopUp");
				}

			} else {

				reservationLogger.info("No Issues");
			}
		}
		if (flag == false) {
			Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
			Wait.wait5Second();
			// long Loadguestregform_StartTime = System.currentTimeMillis();
			try {
				ReservationPage.Click_Select.click();
				reservationLogger.info("Clicked on select button of room assignment popup");
				Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
			} catch (Exception e) {

			}
		}
		Wait.wait10Second();

		try {

			if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
				System.out.println("Rule Break is displayed");
				ReservationPage.Click_Continue_RuleBroken_Popup.click();
				reservationLogger.info("Clicked on continue button of RulesBroken Popup");
			} else {
				reservationLogger.info("Satisfied Rules");
			}
			if (ReservationPage.Verify_Dirty_Room_popup.isDisplayed()) {
				System.out.println("Dirty Room popup displayed");
				ReservationPage.Confirm_button.click();
				reservationLogger.info("Clicked on confirm button of DirtyRoom Popup");
				// Wait.wait3Second();
			} else {
				reservationLogger.info("No Dirty Rooms");
			}
		} catch (Exception e) {

		}
		Wait.wait10Second();
		try {
			System.out.println("Try ");

			// if (ReservationPage.Payment_Popup.isDisplayed()) {
			reservationLogger.info("This Reservation has checkin ploicy, Payment popup is displayed");
			ReservationFolio Payment = new ReservationFolio();
			checkinpolicy = true;
			System.out.println("Try If");
			CashPayment(driver, PaymentType, Amount);
			reservationLogger.info("Payment process is completed");
			// }
		} catch (Exception e) {

			reservationLogger.info("Checkin Policy doesn't exist");
		}

		// if (checkinpolicy == false) {
		// reservationLogger.info("Trying to Clicking on Confirm button of Guest
		// Registration Form in Reservation.java");
		// Thread.sleep(10000);
		// Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_on_confirm);
		// ReservationPage.Click_on_confirm.click();
		// Wait.wait1Second();
		// Wait.explicit_wait_visibilityof_webelement(ReservationPage.Toaster_Message);
		// Wait.wait3Second();
		// }

		/*
		 * try { if (ReservationPage.Key_Generation_Popup.isDisplayed()) {
		 * ReservationPage.Key_Generation_Close.click(); //Wait.wait15Second();
		 * } } catch (Exception e) {
		 * reservationLogger.info("Key Generation doesnt exist"); }
		 */

		/*
		 * ReservationPage.CheckIn_Button.click(); //Thread.sleep(5000);
		 * Wait.explicit_wait_visibilityof_webelement(ReservationPage.
		 * SelectButton); //
		 * Wait.waitUntilPresenceOfElementLocated(OR.SelectButton);
		 * ReservationPage.SelectButton.click();
		 * 
		 * boolean flag = false; try {
		 * Wait.explicit_wait_visibilityof_webelement(ReservationPage.
		 * Already_Checked_In_Confirm_Popup);
		 * 
		 * assertTrue(false);
		 * ReservationPage.Already_Checked_In_Confirm_Popup_Confirm_Btn.click();
		 * flag = true; } catch (Exception e) {
		 * reservationLogger.info("No conflicts with room assignment"); } if
		 * (flag == true) {
		 * Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.
		 * Select_Room_Number); Select selectRoomNumber = new
		 * Select(ReservationPage.Select_Room_Number); String selectedOption =
		 * selectRoomNumber.getFirstSelectedOption().getText(); if
		 * (selectedOption.contains("(O and")) {
		 * 
		 * String RoomwithVandC =
		 * driver.findElement(By.xpath("//option[contains(text(),'(V and C)')] "
		 * )) .getAttribute("value");
		 * selectRoomNumber.selectByValue(RoomwithVandC); reservationLogger.
		 * info("Selected first available room with V and C status from the list"
		 * ); Wait.wait2Second(); ReservationPage.Click_Select.click();
		 * reservationLogger.
		 * info("Clicked on select button of room assignment popup"); try {
		 * 
		 * Wait.explicit_wait_visibilityof_webelement(ReservationPage.
		 * ReCalculate_Folio_Options_PopUp); if
		 * (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
		 * ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();
		 * Actions action = new Actions(driver);
		 * action.moveToElement(ReservationPage.
		 * ReCal_Folio_Options_PopUp_Select_Btn).click().build() .perform(); } }
		 * catch (Exception e) {
		 * reservationLogger.info("No ReCalculate Folio Options PopUp"); } }
		 * else { reservationLogger.info("No Issues"); } }
		 * 
		 * try { Wait.wait2Second(); if
		 * (ReservationPage.Verify_Dirty_Room_popup.isDisplayed()) {
		 * ReservationPage.Confirm_button.click();
		 * reservationLogger.info("Clicked on confirm button of DirtyRoom Popup"
		 * ); } else { reservationLogger.info("No Dirty Rooms"); } } catch
		 * (Exception e) {
		 * 
		 * }
		 * 
		 * 
		 * Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message); String
		 * message = ReservationPage.Toaster_Title.getText(); if
		 * (message.contains("Working...")) {
		 * ReservationPage.Toaster_Message.click();
		 * System.out.println("Click on toster message"); Wait.wait3Second();
		 * Wait.explicit_wait_absenceofelement(OR.Toaster_Message); }
		 * Wait.explicit_wait_xpath(OR.CheckIN_Confirm);
		 * ReservationPage.CheckIN_Confirm.click();
		 * Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message); message =
		 * ReservationPage.Toaster_Title.getText(); System.out.println(message);
		 * assertEquals(message, "Reservation Check In",
		 * "Fail to perform checkin"); Thread.sleep(5000);
		 * 
		 * WebElement element =
		 * driver.findElement(By.xpath(OR.Continue_Pay_Button)); if
		 * (element.isDisplayed()) { element.click(); }
		 */
	}

	public void CheckIN_WithoutPayment(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.waitUntilPresenceOfElementLocated(OR.CheckIn_Button, driver);
		ReservationPage.CheckIn_Button.click();
		Actions action = new Actions(driver);

		boolean flag = false;
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Already_Checked_In_Confirm_Popup, driver);
			ReservationPage.Already_Checked_In_Confirm_Popup_Confirm_Btn.click();
			flag = true;
		} catch (Exception e) {
			reservationLogger.info("No conflicts with room assignment");
		}
		if (flag == true) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Room_Assignment_PopUp, driver);
			Select selectRoomNumber = new Select(ReservationPage.Select_Room_Number);
			String selectedOption = selectRoomNumber.getFirstSelectedOption().getText();
			if (selectedOption.contains("(O and")) {

				String RoomwithVandC = driver.findElement(By.xpath("//option[contains(text(),'(V and C)')] "))
						.getAttribute("value");
				selectRoomNumber.selectByValue(RoomwithVandC);
				reservationLogger.info("Selected first available room with V and C status from the list");
				Wait.wait2Second();
				ReservationPage.Click_Select.click();
				reservationLogger.info("Clicked on select button of room assignment popup");

				try {
					Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_RulesBroken_Popup, driver);
					if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
						ReservationPage.Click_Continue_RuleBroken_Popup.click();
						reservationLogger.info("Clicked on continue button of RulesBroken Popup");
					}
				} catch (Exception e) {
					reservationLogger.info("Satisfied Rules");
				}
				try {
					Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_Dirty_Room_popup, driver);
					if (ReservationPage.Verify_Dirty_Room_popup.isDisplayed()) {
						ReservationPage.Confirm_button.click();
						reservationLogger.info("Clicked on confirm button of DirtyRoom Popup");
					}
				} catch (Exception e) {
					reservationLogger.info("No Dirty Rooms");
				}
				try {

					Wait.explicit_wait_visibilityof_webelement(ReservationPage.ReCalculate_Folio_Options_PopUp, driver);
					if (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
						ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();

						action.moveToElement(ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn).click().build()
								.perform();
					}
				} catch (Exception e) {
					reservationLogger.info("No ReCalculate Folio Options PopUp");
				}
				try {
					Thread.sleep(3000);
					if (ReservationPage.Policy_Comparision_PopUp.isDisplayed()) {
						ReservationPage.Policy_Comparision_PopUp_Continue_Btn.click();
					}
				} catch (Exception e) {
					System.out.println("Policy Comparision Popup not displayed ");
				}
			} else {
				reservationLogger.info("No Issues");
			}
		}
		if (flag == false) {
			try {
				Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.SelectButton, driver);
				ReservationPage.Click_Select.click();
				reservationLogger.info("Clicked on select button of room assignment popup");
				try {
					Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_RulesBroken_Popup, driver);
					if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
						ReservationPage.Click_Continue_RuleBroken_Popup.click();
						reservationLogger.info("Clicked on continue button of RulesBroken Popup");
					}
				} catch (Exception e) {
					reservationLogger.info("Satisfied Rules");
				}
				try {
					Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_Dirty_Room_popup, driver);
					if (ReservationPage.Verify_Dirty_Room_popup.isDisplayed()) {
						ReservationPage.Confirm_button.click();
						reservationLogger.info("Clicked on confirm button of DirtyRoom Popup");
					}
				} catch (Exception e) {
					reservationLogger.info("No Dirty Rooms");
				}
			} catch (Exception e) {

			}
		}
		try {
			Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
			String message = ReservationPage.Toaster_Title.getText();
			if (message.contains("Working...")) {
				ReservationPage.Toaster_Message.click();
				System.out.println("Click on toster message");
				Wait.explicit_wait_absenceofelement(OR.Toaster_Message_Xpath, driver);
			}
		} catch (Exception e) {
			reservationLogger.info("No Toaster message of working..");
		}
		Wait.explicit_wait_xpath(OR.CheckIN_Confirm, driver);
		ReservationPage.CheckIN_Confirm.click();
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		String message = ReservationPage.Toaster_Title.getText();
		System.out.println(message);
		Utility.ElementFinderUntillNotShow(By.xpath(OR.Toaster_Title), driver);
		assertEquals(message, "Reservation Check In", "Fail to perform checkin");
		Thread.sleep(5000);
	}

	public ArrayList<String> CheckIN_CardPayment_VerifyPolicy(WebDriver driver, String Percentage, String PaymentType,
			String CardName, String CCNumber, String CCExpiry, String CCVCode, String Authorizationtype,
			String ChangeAmount, String ChangeAmountValue, String TraceData, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		// String FolioBalance =
		// FolioLineItems.Payment_Details_Folio_Balance.getText().split(" ")[1];
		String TotalBalance = FolioLineItems.Payment_Details_Total_Balance.getText().split(" ")[1];
		String PercentAmount = Float
				.toString((Float.parseFloat(Percentage) * Float.parseFloat(TotalBalance)) / Float.parseFloat("100"));
		System.out.println(PercentAmount);
		PercentAmount = String.format("%.2f",
				((Float.parseFloat(Percentage) * Float.parseFloat(TotalBalance)) / Float.parseFloat("100")));
		System.out.println(PercentAmount);
		if (ReservationPage.ConfirmPopup.isDisplayed()) {
			ReservationPage.ConfirmButton.click();
		}
		Wait.waitUntilPresenceOfElementLocated(OR.CheckIn_Button, driver);
		ReservationPage.CheckIn_Button.click();
		if (ReservationPage.ConfirmPopup.isDisplayed()) {
			ReservationPage.ConfirmButton.click();
		}
		Wait.waitUntilPresenceOfElementLocated(OR.SelectButton, driver);
		Thread.sleep(5000);
		ReservationPage.SelectButton.click();
		try {
			Wait.waitForElementToBeGone(driver, ReservationPage.SelectButton, 30);

		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.SelectButton, driver);
			ReservationPage.SelectButton.click();
			Wait.waitForElementToBeGone(driver, ReservationPage.SelectButton, 30);
			reservationLogger.info("Again Clicked on select button of room assignment popup");
		}
		try {
			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(OR.Reservation_PaymentPopup)),
					driver);
			reservationLogger.info("Payment popup is displayed");
			String Amount = ReservationPage.Change_Amount.getAttribute("value");
			assertEquals(PercentAmount, Amount, "Failed: Amount is not the " + Percentage + " % of the total Amount");

			reservationLogger.info("Amount is the " + Percentage + " % of the total Amount which  is " + Amount);
			CardPayment(driver, PaymentType, CardName, CCNumber, CCExpiry, CCVCode, Authorizationtype, "No", Amount,
					TraceData, test_steps);
			reservationLogger.info("Payment process is completed");

		} catch (Exception e) {
			reservationLogger.info("Checkin Policy doesn't exist");
		}

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.CheckIN_Confirm, driver);
		try {
			ReservationPage.Toaster_Message.click();
		} catch (Exception e) {
			Utility.app_logs.info("No Working Toaster Appear");
		}
		Wait.wait2Second();
		ReservationPage.CheckIN_Confirm.click();
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		assertEquals(ReservationPage.Toaster_Title.getText(), "Reservation Check In", "Fail to perform checkin");
		return test_steps;
	}

	public ArrayList<String> CheckOut_CashPayment_Close(WebDriver driver, String PaymentType, String Amount,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Checkout, driver);
		ReservationPage.Click_Checkout.click();
		reservationLogger.info("Click Checkout");
		// Thread.sleep(5000);
		CashPayment(driver, PaymentType, Amount);

		reservationLogger.info("Cach Payment Done");
		// Thread.sleep(8000);
		try {
			System.out.println("Waiting For close button...");
			analyzeLog(driver, test_steps);
			Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Click_Close, driver);
			System.out.println("before click on close button");
			test_steps.add("before click on close button");
			ReservationPage.Click_Close.click();
			Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.Toaster_Message, driver);
			assertEquals(ReservationPage.Toaster_Title.getText(), "Reservation Check Out", "Fail to perform checkin");
		} catch (Exception e) {
			/*
			 * //
			 * Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.
			 * Click_Close); System.out.println("in catch");
			 * System.out.println("before click on close button");
			 * test_steps.add("before click on close button");
			 * ReservationPage.Click_Close.click(); Wait.wait2Second();
			 * Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.
			 * Toaster_Message);
			 * assertEquals(ReservationPage.Toaster_Title.getText(),
			 * "Reservation Check Out", "Fail to perform checkout");
			 */
			analyzeLog(driver, test_steps);
		}
		return test_steps;

	}

	public void CheckOut_CashPayment_Close_CheckBUG(WebDriver driver, String PaymentType, String Amount)
			throws InterruptedException, IOException {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Checkout, driver);
		String ResNumber = GetReservationnumber(driver);
		ReservationPage.Click_Checkout.click();
		reservationLogger.info("Click Checkout");
		Wait.WaitForElement(driver, OR.Payment_Popup);
		assertTrue(ReservationPage.Payment_Popup.isDisplayed(), "Payment_PopUp didn't Display");
		Wait.wait5Second();
		// Wait.WaitForElement(driver, OR.Click_Process);
		// assertTrue(Elements_Reservations.Click_Process.isDisplayed(),
		// "Process didn't Display");
		// Elements_Reservations.Click_Process.click();
		// reservationLogger.info("Clicking on Process");
		// Wait.wait3Second();
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(Elements_Reservations.Click_Continue));
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Click_Continue, driver);
		Wait.WaitForElement(driver, OR.Click_Continue);
		Elements_Reservations.Click_Continue.click();
		reservationLogger.info("Clicking on Continue");
		Thread.sleep(5000);
		Wait.wait5Second();
		Wait.WaitForElement(driver, OR.Click_Close);
		assertTrue(ReservationPage.Click_Close.isDisplayed(), "Click_Close didn't Display");
		ReservationPage.Click_Close.click();
		reservationLogger.info("Clicking on Click_Close");
		Wait.wait5Second();
		SaveAndCloseReservation(driver);

		ReservationSearch resSearch = new ReservationSearch();
		resSearch.SearchAndOpenRes(driver, ResNumber);
		reservationLogger.info("Open Reservation " + ResNumber);
		VerifyStatus(driver, "Departed");
		reservationLogger.info("Verified Reservation Status Departed");

		// Thread.sleep(5000);
		// CashPayment(driver, PaymentType, Amount);
		// reservationLogger.info("Cash Payment Done");
		// Thread.sleep(5000);
		// System.out.println("After payment");
		// TestCore.login_NONGS(driver);
		// ReservationSearch resSearch = new ReservationSearch();
		// resSearch.SearchAndOpenRes(driver, ResNumber);
		// reservationLogger.info("Open Reservation " + ResNumber);
		// test_steps.add("Open Reservation " + ResNumber);
		// VerifyStatus(driver, "Departed");
		// reservationLogger.info("Verified Reservation Status Departed");
		// test_steps.add("Verified Reservation Status Departed");
		//
		// reservationLogger.info("before click on close button");
		// test_steps.add("before click on close button");
		// if (!WebBUG(driver)) {
		// reservationLogger.info("No WebBug");
		// ReservationPage.Click_Close.click();
		// reservationLogger.info("Click Close");
		// Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.Toaster_Message);
		// assertEquals(ReservationPage.Toaster_Title.getText(), "Reservation
		// Check Out", "Fail to perform checkin");
		// SaveAfterReservation(driver);
		// test_steps.add("Checkout perform succussfully");
		// Utility.app_logs.info("Checkout perform succussfully");
		// } else {
		// reservationLogger.info("Web BUG Appears");
		// test_steps.add("Web BUG Appears");
		// login_NONGS();
		// test_steps.add("Logged into the application");
		// Utility.app_logs.info("Logged into the application");
		// ReservationSearch resSearch = new ReservationSearch();
		// resSearch.SearchAndOpenRes(driver, ResNumber);
		// reservationLogger.info("Open Reservation " + ResNumber);
		// test_steps.add("Open Reservation " + ResNumber);
		// }
		// VerifyStatus(driver, "Departed");
		// reservationLogger.info("Verified Reservation Status Departed");
		// test_steps.add("Verified Reservation Status Departed");
	}

	public ArrayList<String> CheckOut_WithNonZeroBlncRes(WebDriver driver, String PaymentType, String Amount)
			throws InterruptedException, IOException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		String ResNumber = GetReservationnumber(driver);
		String Endingbal = Elements_Reservations.Verify_Balance_Zero.getText();
		Endingbal = Endingbal.replace("$", " ");
		Endingbal = Endingbal.trim();
		System.out.println("Double:" + Endingbal);
		test_steps.add("The Ending Balance before checkout is : $ " + Endingbal);
		Double d = Double.parseDouble(Endingbal);
		d = d - 1;
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Click_Checkout, driver);
		Elements_Reservations.Click_Checkout.click();
		test_steps.add("Click Checkout");
		reservationLogger.info("Click Checkout");

		try {
			Wait.explicit_wait_xpath(OR.TakePaymentPopUp, driver);

		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.Verify_Payment_Details_poup, driver);
		}
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.Click_Continue, driver);
		Utility.ScrollToElement(Elements_Reservations.Click_Continue, driver);
		assertEquals(Elements_Reservations.Click_Continue.isEnabled(), true, "Continue Button didn't Display");
		test_steps.add("Continue Button is enabled");
		System.out.println(PaymentType);
		test_steps.add("The Payment Method is : " + PaymentType);
		new Select(Elements_Reservations.Select_Paymnet_Method).selectByVisibleText(PaymentType);
		try {
			Elements_Reservations.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			Elements_Reservations.Change_Amount.sendKeys(d.toString());
		} catch (Exception e) {
			reservationLogger.info("Catch Body");
			String amount = "(//label[.='Amount:'])[2]/following-sibling::div/input";
			driver.findElement(By.xpath(amount)).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			driver.findElement(By.xpath(amount)).sendKeys(d.toString());
		}
		Wait.wait3Second();
		System.out.println(d.toString());
		test_steps.add("Entering Less Amount during Checkout : $ " + (d.toString()));
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Click_Process, driver);
		Utility.ScrollToElement(Elements_Reservations.Click_Process, driver);
		Elements_Reservations.Click_Process.click();
		reservationLogger.info("Click on Process");
		test_steps.add("Click on Process");
		WebDriverWait wait1 = new WebDriverWait(driver, 120);
		wait1.until(ExpectedConditions.elementToBeClickable(Elements_Reservations.Click_Continue));
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Click_Continue, driver);
		Wait.WaitForElement(driver, OR.Click_Continue);
		Elements_Reservations.Click_Continue.click();
		reservationLogger.info("Click on Continue");
		test_steps.add("Click on Continue button");
		Wait.WaitForElement(driver, OR.Click_Close);
		assertTrue(Elements_Reservations.Click_Close.isDisplayed(), "Click_Close didn't Display");
		Elements_Reservations.Click_Close.click();
		reservationLogger.info("Click on Close Button");
		test_steps.add("Click on Close Button");
		return test_steps;
	}

	public ArrayList<String> VerificationWithNonZeroBlncRes(WebDriver driver, String PaymentType, String Amount)
			throws InterruptedException, IOException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Verify_Balance_Zero, driver);
		Utility.ScrollToElement(Elements_Reservations.Verify_Balance_Zero, driver);
		String Endingbal = Elements_Reservations.Verify_Balance_Zero.getText();
		Endingbal = Endingbal.replace("$", " ");
		Endingbal = Endingbal.trim();
		System.out.println("The Ending Balance after checkout:" + Endingbal);
		int ClosingBalnce = (int) Float.parseFloat(Endingbal);
		System.out.println(ClosingBalnce);
		assertEquals(ClosingBalnce > 0, true, "The ending balance during checkout is zero");
		test_steps.add("The ending balance after checkout is non zero : " + ClosingBalnce);
		return test_steps;
	}

	public void CashPayment(WebDriver driver, String PaymentType, String Amount) throws InterruptedException {

		Wait.explicit_wait_xpath(OR.PaymentPopUp1, driver);
		// Wait.wait5Second();
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);

		// select payment type as cash
		try {
			new Select(elements_All_Payments.PaymentType).selectByVisibleText(PaymentType);
			// elements_All_Payments.Enter_Amount.clear();

			elements_All_Payments.Enter_Amount.sendKeys(Amount);
			// Add button
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", elements_All_Payments.Add_Pay_Button);
			reservationLogger.info("Click Add Pay");

			try {
				System.out.println("try");
				Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Continue_Pay_Button, driver);
				elements_All_Payments.Continue_Pay_Button.click();
				Wait.waitForElementToBeGone(driver, elements_All_Payments.Continue_Pay_Button, 30);
			} catch (Exception e) {
				System.out.println("catch");
				Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Continue_Pay_Button, driver);
				elements_All_Payments.Continue_Pay_Button.click();
				Wait.waitForElementToBeGone(driver, elements_All_Payments.Continue_Pay_Button, 30);
			}
			reservationLogger.info("Click Continue Pay");
		} catch (Exception ce) {

			// Add button
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", elements_All_Payments.Add_Pay_Button);
			reservationLogger.info("Click Add Pay");
			try {
				System.out.println("try");
				Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Continue_Pay_Button, driver);
				elements_All_Payments.Continue_Pay_Button.click();
				Wait.waitForElementToBeGone(driver, elements_All_Payments.Continue_Pay_Button, 30);
			} catch (Exception e) {
				System.out.println("catch");
				Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Continue_Pay_Button, driver);
				elements_All_Payments.Continue_Pay_Button.click();
				Wait.waitForElementToBeGone(driver, elements_All_Payments.Continue_Pay_Button, 30);
			}
			reservationLogger.info("Click Continue Pay");
		}
	}

	public void CashPayment(WebDriver driver, String PaymentType) throws InterruptedException {
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		// Wait.explicit_wait_visibilityof_webelement_150(elements_All_Payments.PaymentType,
		// driver);
		// select payment type as cash
		new Select(elements_All_Payments.PaymentType).selectByVisibleText(PaymentType);

		// Add button
		elements_All_Payments.Add_Pay_Button.click();
		Utility.app_logs.info("Click Add");

		try {
			Wait.explicit_wait_visibilityof_webelement_150(elements_All_Payments.Continue_Pay_Button, driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", elements_All_Payments.Continue_Pay_Button);
			Utility.app_logs.info("Click Continue pay");
			Wait.waitForElementToBeGone(driver, elements_All_Payments.Continue_Pay_Button, 30);
		} catch (Exception e) {
			System.out.println("catch");
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Continue_Pay_Button, driver);
			elements_All_Payments.Continue_Pay_Button.click();
			Wait.waitForElementToBeGone(driver, elements_All_Payments.Continue_Pay_Button, 30);
		}
	}

	public ArrayList<String> PaymentViaReservation(WebDriver driver, String ResNumber, String Amount,
			String PaymentType, ArrayList<String> test_steps) throws InterruptedException {

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentType, driver);
		// select payment type as Reservation
		new Select(elements_All_Payments.PaymentType).selectByVisibleText(PaymentType);
		Utility.app_logs.info("Select Payment Type : " + PaymentType);
		test_steps.add("Select Payment Type : " + PaymentType);
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Enter_Reservation, driver);
		assertTrue(elements_All_Payments.Enter_Reservation.isDisplayed(), "Failed: Reservation Text Box not Appeared");
		elements_All_Payments.Enter_Reservation.sendKeys(ResNumber);
		Utility.app_logs.info("Enter Reservation Number : " + ResNumber);
		test_steps.add("Enter Reservation Number : " + ResNumber);
		try {
			Wait.explicit_wait_xpath("//span[text()='" + ResNumber + "']", driver);
		} catch (Exception e) {
			elements_All_Payments.Enter_Reservation.clear();
			elements_All_Payments.Enter_Reservation.sendKeys(ResNumber);
			Utility.app_logs.info("Again Enter Reservation Number : " + ResNumber);
			test_steps.add("Again Enter Reservation Number : " + ResNumber);
			Wait.explicit_wait_xpath("//span[text()='" + ResNumber + "']", driver);
		}
		WebElement reservation = driver.findElement(By.xpath("//span[text()='" + ResNumber + "']"));
		Wait.explicit_wait_visibilityof_webelement_150(reservation, driver);
		reservation.click();
		Utility.app_logs.info("Select Reservation");
		test_steps.add("Select Reservation");
		Wait.wait2Second();
		elements_All_Payments.PaymentType.click();
		try {
			new Select(elements_All_Payments.PaymentType).selectByVisibleText("Reservation - " + ResNumber);
		} catch (Exception e) {
			assertTrue(false, "Failed: Reservation number is not displayed in the Payment Method drop down");
		}
		Wait.wait2Second();
		String pay_type = new Select(elements_All_Payments.PaymentType).getFirstSelectedOption().getText();
		assertTrue(pay_type.contains(ResNumber), "Failed : Payment Type is not selected");
		Utility.app_logs.info("Selected Payment Type : " + pay_type);
		test_steps.add("Selected Payment Type : " + pay_type);
		elements_All_Payments.Enter_Amount.clear();
		if (Float.parseFloat(Amount) < 0.0) {
			assertTrue(false, "Failed: Amount is less then 0.0");
		}
		String str_length = elements_All_Payments.Enter_Amount.getAttribute("value");
		for (int i = 0; i < str_length.length(); i++) {
			elements_All_Payments.Enter_Amount.sendKeys(Keys.BACK_SPACE);
		}
		elements_All_Payments.Enter_Amount.sendKeys(Amount);
		Utility.app_logs.info("Enter Amount : " + Amount);
		test_steps.add("Enter Amount : " + Amount);

		// Add button
		elements_All_Payments.Add_Pay_Button.click();
		Utility.app_logs.info("Click Add Payments");
		test_steps.add("Click Add Payments");
		try {
			Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Payment_ModuleLoading)), 60);
		} catch (Exception e) {
			Utility.app_logs.info("Loading Element Not gone");
		}
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Continue_Pay_Button, driver);
		driver.findElement(By.xpath(OR.Continue_Pay_Button)).click();
		Utility.app_logs.info("Click Continue Payments");
		test_steps.add("Click Continue Payments");
		try {
			Wait.waitForElementToBeGone(driver, elements_All_Payments.Continue_Pay_Button, 60);
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Continue_Pay_Button, driver);
			driver.findElement(By.xpath(OR.Continue_Pay_Button)).click();
			Utility.app_logs.info("Again Click Continue Payments");
			test_steps.add("Again Click Continue Payments");
		}
		Folio folio = new Folio();
		folio.Verify_LineItem(driver, PaymentType.toLowerCase(), Amount, pay_type);
		test_steps.add("Successfully Verified Payment line item is added to Folio");
		return test_steps;
	}

	public void EnterFirst_LastName(WebDriver driver, String FirstName, String LastName) {

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_350(elements_Reservation.Enter_First_Name, driver);
		elements_Reservation.Enter_First_Name.clear();
		elements_Reservation.Enter_Last_Name.clear();

		elements_Reservation.Enter_First_Name.sendKeys(FirstName);
		elements_Reservation.Enter_Last_Name.sendKeys(LastName);

	}

	public ArrayList<String> VerifyRoomOutOfOrder(WebDriver driver, String RoomClass, String RoomNumber,
			boolean IsExist, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait2Second();
		Wait.waitUntilPresenceOfElementLocated(OR.RoomAssignmentButton, driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		ReservationPage.RoomAssignment_DateIcon.click();
		ReservationPage.SelectDate.click();
		// ReservationPage.RoomAssign_Check.click();
		ReservationPage.SearchRoomButton.click();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.SelectRoomClasses, driver);
		// Double Bed Room
		// Double Bed Room
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
		test_steps.add("Select Room Class: " + RoomClass);
		reservationLogger.info("Select Room Class: " + RoomClass);

		Wait.wait2Second();
		if (IsExist) {
			try {
				new Select(ReservationPage.SelectRoomNumbers).selectByVisibleText(RoomNumber);
				// String CheckRule = ReservationPage.CheckRule.getText();
				// ReservationPage.SelectButton.click();
				// Wait.wait2Second();
				// if (CheckRule.equals("1") || CheckRule.equals("2")) {
				// ReservationPage.RoleBroken_Continue.click();
				// }

				ReservationPage.CancelRoomAssignment_Button.click();
				assertTrue(true);
			} catch (Exception e) {
				// assertEquals(false, true, "deleted room not showing in room
				// list");
			}
		} else {
			Select SelectRooms = new Select(ReservationPage.SelectRoomNumbers);
			List<WebElement> options = SelectRooms.getOptions();
			boolean RoomOutOfOrder = true;

			for (int i = 0; i < options.size(); i++) {
				if (options.get(i).getText().equals(RoomNumber)) {
					RoomOutOfOrder = false;
					new Select(ReservationPage.SelectRoomNumbers).selectByVisibleText(RoomNumber);
					reservationLogger.info("Failed: Out of order room show in room list");
					test_steps.add("Failed: Out of order room show in room list");
					break;
				}
			}
			assertEquals(RoomOutOfOrder, true, "Out of order room show in room list");
			ReservationPage.CancelRoomAssignment_Button.click();

		}

		Wait.wait2Second();
		return test_steps;
	}

	public void CloseReservation(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.CloseReservationTab.click();
		Wait.wait2Second();
		try {
			ReservationPage.ConfirmationPopup.click();
		} catch (Exception e) {
			reservationLogger.info("Confirmation Popup not Available");
		}
		Wait.wait2Second();

	}

	public void contactInformation_2(WebDriver driver, String saluation, String FirstName, String LastName,
			String Address, String Line1, String Line2, String Line3, String City, String Country, String State,
			String Postalcode, String Phonenumber, String alternativenumber, String Email, String Account,
			String IsTaxExempt, String TaxEmptext) {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		new Select(ReservationPage.Select_Saluation_2).selectByVisibleText(saluation);

		Wait.WaitForElement(driver, OR.Reservation_CreateGuestProfile);
		if (ReservationPage.Reservation_CreateGuestProfile.isSelected()) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ReservationPage.Reservation_CreateGuestProfile);
			ReservationPage.Reservation_CreateGuestProfile.click();
		}

		ReservationPage.Enter_First_Name_2.clear();
		ReservationPage.Enter_Last_Name_2.clear();
		ReservationPage.Enter_First_Name_2.sendKeys(FirstName);
		ReservationPage.Enter_Last_Name_2.sendKeys(LastName);
		ReservationPage.Enter_Line1_3.sendKeys(Line1);
		ReservationPage.Enter_Line2_3.sendKeys(Line2);
		ReservationPage.Enter_Line3_3.sendKeys(Line3);
		ReservationPage.Enter_Postal_Code_3.clear();
		ReservationPage.Enter_Postal_Code_3.sendKeys(Postalcode);
		ReservationPage.Enter_City_3.sendKeys(City);

		ReservationPage.Enter_Phone_Number_3.clear();
		ReservationPage.Enter_Phone_Number_3.sendKeys(Phonenumber);
		reservationLogger.info("Pone Number " + Phonenumber);
		new Select(ReservationPage.Select_Country_2).selectByVisibleText(Country);

		new Select(ReservationPage.Select_State_3).selectByVisibleText(State);

		reservationLogger.info("Entered required contact information");

		try {
			ReservationPage.Enter_Account_2.sendKeys(Account);
		} catch (Exception e) {

		}

		if (IsTaxExempt.equals("Yes")) {
			if (ReservationPage.Check_IsTaxExempt_2.isSelected()) {
				ReservationPage.Enter_TaxExemptId_2.sendKeys(TaxEmptext);
				reservationLogger.info("Entered TaxExcemptID");
			} else {
				ReservationPage.Check_IsTaxExempt_2.click();
				reservationLogger.info("Clicked on TaxExcempt checkbox");
				ReservationPage.Enter_TaxExemptId_2.sendKeys(TaxEmptext);
				reservationLogger.info("Entered TaxExcemptID");
			}
		}
	}

	public void clickNewReservationButton_2(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.New_Reservation_Button, driver);
		ReservationPage.New_Reservation_Button_2.click();
		reservationLogger.info("Clicked on NewReservationButton");
		Wait.explicit_wait_xpath(OR.New_Reservation_Tab_2, driver);
		Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load_2, driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Enter_First_Name_2, driver);
	}

	public void BulkCheckOut(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.Click_Checkout.click();
		System.out.println("Click Checkout");
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Close, driver);
			ReservationPage.Click_Close.click();
			reservationLogger.info("Clicked on CLOSE button of Guest Statement Report");
		} catch (Exception e) {
			Wait.waitUntilPresenceOfElementLocated(OR.BulkCheckOut_Popup, driver);
			assertEquals(ReservationPage.BulkCheckOut_Popup.getText(), "Bulk Action Summary",
					"Bulk checkout popup does not display");
			ReservationPage.ProcessButton.click();
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Close, driver);
			Wait.WaitForElement(driver, OR.Click_Close);
			Wait.waitUntilPresenceOfElementLocated(OR.Click_Close, driver);
			ReservationPage.Click_Close.click();
			reservationLogger.info("Clicked on CLOSE button of Guest Statement Report");
		}

	}

	public void ClickTravelAgentAccount(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_RoomPicker, driver);
		Utility.ScrollToElement(ReservationPage.Click_RoomPicker, driver);
		ReservationPage.ClickTravelAgentAccount.click();
	}

	public ArrayList<String> contactInformationAutoSuggestion(WebDriver driver, ExtentTest test1, String FirstName,
			String LastName, String Address, String Line1, String City, String Country, String State, String Postalcode,
			String Phonenumber, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.WaitForElement(driver, OR.Reservation_CreateGuestProfile);
		if (ReservationPage.Reservation_CreateGuestProfile.isSelected()) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ReservationPage.Reservation_CreateGuestProfile);
			ReservationPage.Reservation_CreateGuestProfile.click();
		}
		// new
		// Select(ReservationPage.Select_Saluation).selectByVisibleText(saluation);
		ReservationPage.Enter_First_Name.clear();
		ReservationPage.Enter_Last_Name.clear();
		ReservationPage.Enter_Address_Search.clear();
		ReservationPage.Enter_Line1.clear();
		ReservationPage.Enter_Line2.clear();
		ReservationPage.Enter_Line3.clear();
		ReservationPage.Enter_City.clear();
		ReservationPage.Enter_Postal_Code.clear();
		ReservationPage.Enter_Phone_Number.clear();
		ReservationPage.Enter_Alt_Phn_number.clear();
		ReservationPage.Enter_email.clear();
		ReservationPage.Enter_First_Name.sendKeys(FirstName);
		test_steps.add("Successfully entered the first name : " + FirstName);
		ReservationPage.Enter_Last_Name.sendKeys(LastName);
		test_steps.add("Successfully entered the last name : " + LastName);
		ReservationPage.Enter_Address_Search.sendKeys(Address);
		Wait.wait5Second();
		assertTrue(ReservationPage.AddressAutoSuggestionDropDown.isDisplayed(),
				"Address details aren't populated according to the search criteria");
		Wait.wait2Second();
		List<WebElement> element = driver.findElements(By.id("ui-id-6"));
		Wait.wait2Second();
		element.get(0).click();
		String GetAddress = ReservationPage.Enter_Address_Search.getAttribute("value");
		assertTrue(!GetAddress.equals(null), "address isn't associated to the resrvation");
		ReservationPage.Enter_Line1.sendKeys(Line1);
		test_steps.add("Successfully entered the Address Line1 : " + Line1);

		ReservationPage.Enter_City.sendKeys(City);
		test_steps.add("Successfully entered the City : " + City);
		new Select(ReservationPage.Select_Country).selectByVisibleText(Country);
		test_steps.add("Successfully selected the Country : " + Country);
		new Select(ReservationPage.Select_State).selectByVisibleText(State);
		test_steps.add("Successfully selected the state : " + State);
		ReservationPage.Enter_Postal_Code.sendKeys(Postalcode);
		test_steps.add("Successfully entered the postal code : " + Postalcode);
		ReservationPage.Enter_Phone_Number.sendKeys(Phonenumber);
		test_steps.add("Successfully entered the phone number : " + Phonenumber);
		// ReservationPage.Enter_Alt_Phn_number.sendKeys(alternativenumber);
		// ReservationPage.Enter_email.sendKeys(Email);
		return test_steps;
	}

	public void marketingInfoAutoSugegstion(WebDriver driver, String MarketSegment, String Referral,
			String Travel_Agent, String ExtReservation) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		new Select(ReservationPage.Reservation_market_Segment).selectByVisibleText(MarketSegment);
		reservationLogger.info("Selected Market Segment");
		new Select(ReservationPage.Reservation_Referral).selectByVisibleText(Referral);
		reservationLogger.info("Selected Referral");

		try {
			ReservationPage.Enter_Travel_Agent.sendKeys(Travel_Agent);
			Wait.wait5Second();
			assertTrue(ReservationPage.TravelAgentAutoSuggestionDropDown.isDisplayed(),
					"TravelAgent Account Name isn't populated according to the search criteria");
			List<WebElement> element = driver.findElements(By.className("span-nomber"));
			element.get(0).click();
			Wait.wait1Second();
			ReservationPage.Copy_Picked_Account_Data_To_Reservation_ContinueButton.click();
			String GetTravelAgent = ReservationPage.Enter_Travel_Agent.getAttribute("value");
			assertTrue(GetTravelAgent.equals("Travell"), "account isn't associated to the reservation");

		} catch (Exception e) {

		}
		// reservationLogger.info("Entered Travel_Agent Info");
		// ReservationPage.Enter_Ext_Reservation.sendKeys(ExtReservation);
		// reservationLogger.info("Entered ExtReservation");
	}

	public void contactInformationGuestProfileAutoSugg(WebDriver driver, ExtentTest test1, String GuestProfile)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		ReservationPage.Enter_GuestProfile_Name.sendKeys(GuestProfile);
		Wait.wait10Second();
		assertTrue(ReservationPage.GuestProfileAutoSuggestionDropDown.isDisplayed(),
				"GuestProfile aren't populated according to the search criteria");
		List<WebElement> element = driver.findElements(By.className("span-guest"));
		element.get(0).click();
		Wait.wait2Second();
		ReservationPage.Copy_Picked_Account_Data_To_Reservation_ContinueButton.click();
		String GetGuestProfileName = ReservationPage.Enter_GuestProfile_Name.getAttribute("value");
		assertTrue(GetGuestProfileName.equals("Adnan Ghaffar"), "profile isn't associated to the reservation");

	}

	public void contactInformationAccountAutoSugg(WebDriver driver, ExtentTest test1, String AccountName)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		ReservationPage.Account_name.sendKeys(AccountName);
		Wait.wait10Second();
		// assertTrue(ReservationPage.AccountNameAutoSuggestionDropDown.isDisplayed(),
		// "AccountName aren't populated according to the search criteria");
		// Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", ReservationPage.AccountNameAutoSuggestionDropDown);
		List<WebElement> element = driver.findElements(By.id("ui-id-7"));
		Wait.wait5Second();
		jse.executeScript("arguments[0].click();", element.get(0));

		Wait.wait2Second();
		// ReservationPage.Copy_Picked_Account_Data_To_Reservation_ContinueButton.click();
		String GetAccountName = ReservationPage.Account_name.getAttribute("value");
		assertTrue(!GetAccountName.equals(null), "AccountName isn't associated to the reservation");

	}

	public String GetSelectedRoomNumber(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		String RoomClass_Number = ReservationPage.GetSelectedRoomNumber.getText();
		String[] Class_Number = RoomClass_Number.split(":");

		return Class_Number[1];

	}

	public void clickNewReserButton(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.New_Reservation_Button, driver);
		ReservationPage.New_Reservation_Button.click();
		reservationLogger.info("Clicked on NewReservationButton");
		Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Enter_First_Name, driver);
	}

	public void clickNewReserButton1(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.New_Reservation_Button1);
		System.out.println("New Rser clicked");
		// reservationLogger.info("Clicked on NewReservationButton");
		// Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load);
		// Wait.explicit_wait_visibilityof_webelement(ReservationPage.Enter_First_Name);
	}

	public void VerifyGuestProfile(WebDriver driver, String Guest_Profile) throws InterruptedException {
		Elements_Reservation reserv = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", reserv.Enter_Guest_Profile);
		assertTrue(reserv.Enter_Guest_Profile.getAttribute("value").contains(Guest_Profile),
				"Guest Profile is not Selected as requires");
	}

	public void VerifyTravelAgentAccount(WebDriver driver, String TravelAgentAccount) throws InterruptedException {
		Elements_Reservation reserv = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", reserv.Enter_Travel_Agent);
		assertTrue(reserv.Enter_Travel_Agent.getAttribute("value").contains(TravelAgentAccount),
				"Travel Agent Account is not Selected as required");
	}

	public void VerifyCheckInDocument(WebDriver driver) throws InterruptedException {
		// Elements_Reservation reserv = new Elements_Reservation(driver);
		Wait.wait2Second();
		driver.switchTo().frame("popiframe");
		WebElement CheckInDoc = driver.findElement(By.id("plugin"));
		assertTrue(CheckInDoc.isDisplayed(), "Failed: CheckInDoc not displayed");
	}

	public void VerifyCheckIn_OutDocument(WebDriver driver) throws InterruptedException {
		// Elements_Reservation reserv = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.id("plugin")), driver);
		driver.switchTo().frame("popiframe");
		WebElement CheckInDoc = driver.findElement(By.id("plugin"));
		assertTrue(CheckInDoc.isDisplayed(), "Failed: CheckInDoc not displayed");
	}

	public void CheckIn_IsEnabled(WebDriver driver, boolean isEnable) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.CheckIn_Button, driver);
		assertEquals(ReservationPage.CheckIn_Button.isEnabled(), isEnable,
				"Failed : CheckIN Button enable : " + !isEnable);
	}

	public void CheckIn(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Utility.ScrollToElement(ReservationPage.CheckIn_Button, driver);
		ReservationPage.CheckIn_Button.click();
		Thread.sleep(5000);
		if (ReservationPage.ConfirmPopup.isDisplayed()) {
			ReservationPage.ConfirmButton.click();
		}
		// Wait.wait10Second();
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		jse1.executeScript("arguments[0].click();", ReservationPage.SelectButton);
		// Thread.sleep(5000);
		// ReservationPage.SelectButton.click();
		Wait.wait5Second();
		// System.out.print("CancelButtonB");
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		jse2.executeScript("arguments[0].click();", ReservationPage.PaymentDetails_CancelButton);
		// System.out.print("CancelButtonA");
		// // ReservationPage.PaymentDetails_CancelButton.
		Wait.wait2Second();
		if (ReservationPage.ConfirmPopup.isDisplayed()) {
			ReservationPage.ConfirmButton.click();
			ReservationPage.CheckinReport_CancelButton.click();
		}

	}

	public void checkin(WebDriver driver, String Payment_Type, String Amount) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Utility.ScrollToElement(ReservationPage.CheckIn_Button, driver);
		ReservationPage.CheckIn_Button.click();

		Wait.wait5Second();
		if (ReservationPage.ConfirmPopup.isDisplayed()) {
			ReservationPage.ConfirmButton.click();
		}
		// Utility.ElementFinderUntillNotShow(By.xpath(OR.SelectButton),
		// driver);
		// Wait.explicit_wait_xpath(OR.SelectButton);
		try {
			System.out.println("Select 1");
			// Wait.explicit_wait_xpath(OR.SelectButton);
			ReservationPage.SelectButton.click();
			Wait.wait1Second();
			if (ReservationPage.ConfirmPopup.isDisplayed()) {
				ReservationPage.ConfirmButton.click();
			}
			Wait.wait1Second();

			CashPayment(driver, Payment_Type, Amount);
			Wait.wait5Second();

			if (ReservationPage.ConfirmPopup.isDisplayed()) {
				ReservationPage.ConfirmButton.click();
			}

		} catch (Exception e) {
			System.out.println("Select 2");
			jse.executeScript("arguments[0].click();", ReservationPage.SelectButton);
			jse.executeScript("arguments[0].click();", ReservationPage.SelectButton);
			Wait.wait1Second();
			if (ReservationPage.ConfirmPopup.isDisplayed()) {
				ReservationPage.ConfirmButton.click();
			}
			Wait.wait1Second();
			CashPayment(driver, Payment_Type, Amount);
			Wait.wait5Second();

			if (ReservationPage.ConfirmPopup.isDisplayed()) {
				ReservationPage.ConfirmButton.click();
			}

		}
		if (ReservationPage.ConfirmPopup.isDisplayed()) {
			ReservationPage.ConfirmButton.click();
		}
		Wait.wait1Second();
		CashPayment(driver, Payment_Type, Amount);
		Wait.wait5Second();

		if (ReservationPage.ConfirmPopup.isDisplayed()) {
			ReservationPage.ConfirmButton.click();
		}

		Wait.wait2Second();
	}

	public ArrayList<String> GuestProfile(WebDriver driver, String Guest_Profile, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation reserv = new Elements_Reservation(driver);
		reserv.Enter_Guest_Profile.sendKeys(Guest_Profile);
		Wait.wait2Second();
		Wait.explicit_wait_xpath(OR.GuestProfile_AutoSuggestionDropDown, driver);
		int numberOfAutoSuggestions = driver.findElements(By.xpath(OR.GuestProfile_AutoSuggestionDropDown)).size();
		System.out.println("Find Auto Suggestions: " + numberOfAutoSuggestions);
		if (numberOfAutoSuggestions == 1) {
			if (driver.findElement(By.xpath(OR.GuestProfile_AutoSuggestionDropDown + "/a/i")).isEnabled()) {
				String message = driver.findElement(By.xpath(OR.GuestProfile_AutoSuggestionDropDown + "/a/i"))
						.getText();
				// System.out.println(message);
				if (message.contains("No data to display")) {
					assertTrue(false, "NO suggestion found");
				}
			}
		}
		driver.findElement(By.xpath("(" + OR.GuestProfile_AutoSuggestionsGuestName + ")[1]")).click();
		Wait.wait1Second();
		reserv.Copy_Picked_Account_Data_To_Reservation_ContinueButton.click();
		Wait.wait1Second();

		test_steps.add("Guest Profile : " + reserv.Enter_Guest_Profile.getAttribute("value"));
		assertTrue(reserv.Enter_Guest_Profile.getAttribute("value").contains(Guest_Profile),
				"Guest Profile is not Selected");
		return test_steps;

	}

	public String GetAssigedRoomNumber(WebDriver driver) {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		String getRoomclassName_status = ReservationPage.Get_RoomClass_Status.getText();
		String[] getRoomclassName_statusList = getRoomclassName_status.split(" : ");
		System.out.println(getRoomclassName_status + " .....  " + getRoomclassName_statusList);
		return getRoomclassName_statusList[1];
	}

	public void AssociateTravelAgentAccount(WebDriver driver, String TravelAgent) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", ReservationPage.Enter_Travel_Agent);
		ReservationPage.Enter_Travel_Agent.clear();
		ReservationPage.Enter_Travel_Agent.sendKeys(TravelAgent);
	}

	public void PrintButtonClick(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.PrintIcon, driver);
		ReservationPage.PrintIcon.click();
		Wait.wait2Second();
		assertTrue(ReservationPage.Report_Popup.isDisplayed(), "Report Popup isn't displayed");

	}

	public void PrintReport(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait2Second();
		ReservationPage.ReportButtonList.get(0).click();
		assertTrue(ReservationPage.ReservationPage.isDisplayed(), "Report Popup isn't displayed");
		// Actions action = new Actions(driver);
		// action.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys(Keys.TAB).build().perform();
		Wait.wait5Second();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Utility.app_logs.info("Switch to report tab");
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.GuestRegistrationForm)), driver);
		assertTrue(driver.findElement(By.xpath(OR.GuestRegistrationForm)).isDisplayed(),
				"Failed: GuestRegistrationForm");
		driver.close();
		Utility.app_logs.info("Close Guest Registration Form Tab");
		driver.switchTo().window(tabs2.get(0));
		Wait.explicit_wait_xpath(OR.RoomAssignmentButton, driver);

	}

	public void Export_Report(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.wait2Second();
		ReservationPage.Select_ReportType.click();
		Wait.wait2Second();
		ReservationPage.DownloadasPDF.click();
	}

	public void VerifyQAPropertysTapeChart(WebDriver driver) throws InterruptedException {
		String ProptyName = driver.findElement(By.xpath("(//div[text()='QA Property'])[1]")).getText();
		assertTrue(!ProptyName.equals("--All--"), "Property name isn't displayed which is assigned to the resrvation");

	}

	public void clickAdvancedSearchButton(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.AdvancedSearched_Button, driver);
		ReservationPage.AdvancedSearched_Button.click();
		Wait.explicit_wait_xpath(OR.AdvancedSearchPage_load, driver);
		assertTrue(ReservationPage.AdvancedSearchPage_load.isDisplayed(), "Advanced Search Page is not Loaded");
	}

	public ArrayList<String> VerifyResult_RoomClass(WebDriver driver, String roomClass, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.AdvancedSearched_RoomClass, driver);
		Wait.explicit_wait_xpath(OR.AdvancedSearched_RoomClass, driver);
		ReservationPage.AdvancedSearched_RoomClass.click();
		String source_xpath = "//span[text()='" + roomClass + "']";
		Wait.explicit_wait_xpath(source_xpath, driver);
		driver.findElement(By.xpath(source_xpath)).click();
		Wait.wait2Second();
		String selectedRoomClassFromDropdown = ReservationPage.AdvancedSearched_SelectedRoomClass.getText();
		// System.out.println("Selected
		// RoomClass:"+selectedRoomClassFromDropdown);
		assertTrue(selectedRoomClassFromDropdown.contains(roomClass), "Room Class is not selected ");
		test_steps.add("Room Class : " + roomClass);
		reservationLogger.info("Room Class : " + roomClass);

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.AdvancedSearch_SearchButton, driver);
		ReservationPage.AdvancedSearch_SearchButton.click();
		test_steps.add("Click Search");
		reservationLogger.info("Click Search");
		Wait.WaitForElement(driver, OR.Number_Of_Reservations);
		Wait.wait10Second();
		int count1 = driver.findElements(By.xpath(OR.Number_Of_Reservations)).size();
		int Total = count1;
		// System.out.println(count1);
		if (count1 == 0) {
			test_steps.add("No Reservations available for Room Class ");
			reservationLogger.info("No Reservations available for Room Class");
		} else {
			Wait.WaitForElement(driver, OR.Searched_RoomClass);
			VerifyRoomClass(driver, count1, roomClass);
			if (ReservationPage.AdvancedSearch_ResultNextPage.isEnabled()) {
				ReservationPage.AdvancedSearch_ResultNextPage.click();
				Wait.WaitForElement(driver, OR.Number_Of_Reservations);
				Wait.wait10Second();
				int count2 = driver.findElements(By.xpath(OR.Number_Of_Reservations)).size();
				VerifyRoomClass(driver, count2, roomClass);
				Total = count1 + count2;
				test_steps.add("Verified Number of Reservations available during these Stay Dates " + Total);
				reservationLogger.info("Verified Number of Reservations available during these Stay Dates " + Total);

			} else {
				test_steps.add("Verified Number of Reservations available during these Stay Dates " + Total);
				reservationLogger.info("Verified Number of Reservations available during these Stay Dates " + Total);
			}
			test_steps.add("Room Class Verified");
			reservationLogger.info("Room Class Verified");
		}
		return test_steps;
	}

	private int GetRoomClassTypeIndex(String roomClass) {
		if (roomClass.equals("Double Bed Room")) {
			return 1;
		} else if (roomClass.equals("Junior Suites")) {
			return 2;

		} else if (roomClass.equals("PolicySuite")) {
			return 3;
		} else {
			return 0;
		}
	}

	private void VerifyRoomClass(WebDriver driver, int count, String roomClass) throws InterruptedException {

		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.Searched_RoomClass)), driver);
		for (int i = 0; i < count; i++) {
			int rowNumber = i + 1;
			Wait.explicit_wait_xpath("(" + OR.Searched_RoomClass + ")[" + rowNumber + "]", driver);
			String selectedRoomClass = driver
					.findElement(By.xpath("(" + OR.Searched_RoomClass + ")[" + rowNumber + "]")).getText();
			if (roomClass.equals("Double Bed Room")) {
				boolean result = selectedRoomClass.contains(roomClass) || selectedRoomClass.contains("DBR")
						|| selectedRoomClass.contains("Multiple");
				assertTrue(result, "Room Class Missmatched");
			} else if (roomClass.equals("Junior Suites")) {
				boolean result = selectedRoomClass.contains(roomClass) || selectedRoomClass.contains("JR")
						|| selectedRoomClass.contains("Multiple");
				assertTrue(result, "Room Class Missmatched:" + selectedRoomClass);

			} else if (roomClass.equals("PolicySuite")) {
				boolean result = selectedRoomClass.contains(roomClass) || selectedRoomClass.contains("PS")
						|| selectedRoomClass.contains("Multiple");
				assertTrue(result, "Room Class Missmatched");
			} else if (selectedRoomClass == null) {
				assertTrue(false, "RoomClass is null");
			} else {
				assertEquals(selectedRoomClass, roomClass, "Room Class Missmatched");
			}
		}

	}

	public ArrayList<String> VerifyResult_StayDate(WebDriver driver, String staydatefrom, String staydateto,
			ArrayList<String> test_steps) throws InterruptedException, ParseException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.AdvancedSearch_StayFromDate, driver);
		ReservationPage.AdvancedSearch_StayFromDate.sendKeys(staydatefrom);
		Wait.wait2Second();
		assertTrue(ReservationPage.AdvancedSearch_StayFromDate.getAttribute("value").contains(staydatefrom),
				"Stay From date is not Cleared");
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.AdvancedSearch_StayToDate, driver);
		ReservationPage.AdvancedSearch_StayToDate.sendKeys(staydateto);
		Wait.wait2Second();
		assertTrue(ReservationPage.AdvancedSearch_StayToDate.getAttribute("value").contains(staydateto),
				"Stay To date is not Cleared");
		test_steps.add("Stay date selected");
		reservationLogger.info("Stay date selected");

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.AdvancedSearch_SearchButton, driver);
		ReservationPage.AdvancedSearch_SearchButton.click();
		test_steps.add("Click Search");
		reservationLogger.info("Click Search");
		Wait.WaitForElement(driver, OR.Number_Of_Reservations);

		Wait.wait15Second();
		int count = driver.findElements(By.xpath(OR.Number_Of_Reservations)).size();

		if (count == 0) {
			test_steps.add("No Reservations available during these Stay Dates");
			reservationLogger.info("No Reservations available during these Stay Dates");
		} else {
			// VerifyStayDate(driver, count, staydatefrom, staydateto);
			if (ReservationPage.AdvancedSearch_ResultNextPage.isEnabled()) {
				ReservationPage.AdvancedSearch_ResultNextPage.click();
				Wait.wait5Second();
				count = driver.findElements(By.xpath(OR.Number_Of_Reservations)).size();
				Wait.wait5Second();
				// VerifyStayDate(driver, count, staydatefrom, staydateto);
				count = count + 20;
				test_steps.add("Verified Number of Reservations available during these Stay Dates " + count);
				reservationLogger.info("Verified Number of Reservations available during these Stay Dates " + count);

			} else {
				test_steps.add("Verified Number of Reservations available during these Stay Dates " + count);
				reservationLogger.info("Verified Number of Reservations available during these Stay Dates " + count);
			}
			test_steps.add("Stay Date Verified");
			reservationLogger.info("Stay Date Verified");
		}
		return test_steps;
	}

	private void VerifyStayDate(WebDriver driver, int count, String staydatefrom, String staydateto)
			throws ParseException {

		SimpleDateFormat resultDateFormat = new SimpleDateFormat("MMM dd, yyyy");
		SimpleDateFormat inputDateFormat = new SimpleDateFormat("MM/dd/yy");
		Wait.WaitForElement(driver, OR.ArrivalDate);
		for (int i = 0; i < count; i++) {
			int rowNumber = i + 1;
			String Arrivaldate = driver.findElement(By.xpath("(" + OR.ArrivalDate + ")[" + rowNumber + "]")).getText();
			Assert.assertEquals(resultDateFormat.parse(Arrivaldate), inputDateFormat.parse(staydatefrom),
					" Arrival date missmatched  ");
			String depdate = driver.findElement(By.xpath("(" + OR.DepartureDate + ")[" + rowNumber + "]")).getText();
			Assert.assertEquals(resultDateFormat.parse(depdate), inputDateFormat.parse(staydateto),
					" Departure date missmatched  ");
		}

	}

	public ArrayList<String> VerifyTravelAgent_AutoSuggestion(WebDriver driver, String Travel_Agent,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation reserv = new Elements_Reservation(driver);
		reserv.Enter_Travel_Agent.sendKeys(Travel_Agent);
		Wait.wait2Second();
		Wait.explicit_wait_xpath(OR.TravelAgent_AutoSuggestionDropDown, driver);

		int numberOfAutoSuggestions = driver.findElements(By.xpath(OR.TravelAgent_AutoSuggestionDropDown)).size();
		// System.out.println("Find Auto Suggestions: " +
		// numberOfAutoSuggestions);
		if (numberOfAutoSuggestions == 1) {
			if (driver.findElement(By.xpath(OR.TravelAgent_AutoSuggestionDropDown + "/a/i")).isEnabled()) {
				String message = driver.findElement(By.xpath(OR.TravelAgent_AutoSuggestionDropDown + "/a/i")).getText();
				// System.out.println(message);
				if (message.contains("No data to display")) {
					assertTrue(false, "NO suggestion found");
				}
			}
		}
		for (int i = 1; i <= numberOfAutoSuggestions; i++) {
			Wait.explicit_wait_xpath("(" + OR.TravelAgent_AutoSuggestionsAccountName + ")[" + i + "]", driver);
			String travelAgent = driver
					.findElement(By.xpath("(" + OR.TravelAgent_AutoSuggestionsAccountName + ")[" + i + "]")).getText();
			// System.out.println(travelAgent);
			assertTrue(travelAgent.contains(Travel_Agent), "Travel Agent Auto Suggestion Account Name missmatched");
		}
		driver.findElement(By.xpath("(" + OR.TravelAgent_AutoSuggestionsAccountName + ")[1]")).click();
		Wait.wait1Second();
		reserv.Copy_Picked_Account_Data_To_Reservation_ContinueButton.click();
		Wait.wait1Second();
		test_steps.add("Tavel Agent : " + Travel_Agent);
		assertTrue(reserv.Enter_Travel_Agent.getAttribute("value").contains(Travel_Agent),
				"Travel Agent account is not Selected");
		return test_steps;
	}

	public ArrayList<String> VerifyAccount_AutoSuggestion(WebDriver driver, String Account, String AccountNumber,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation reserv = new Elements_Reservation(driver);
		Utility.app_logs.info(Account);
		reserv.Enter_Account.sendKeys(Account);
		Wait.wait2Second();
		// Utility.ElementFinderUntillNotShow(By.xpath(OR.AccountName_AutoSuggestionDropDown),
		// driver);

		try {
			Wait.explicit_wait_visibilityof_webelement_150(
					driver.findElement(By.xpath(OR.AccountName_AutoSuggestionDropDown)), driver);
		} catch (Exception e) {
			Utility.app_logs.info("Catch body");
			reserv.Enter_Account.clear();
			reserv.Enter_Account.sendKeys(Account);
			Wait.explicit_wait_visibilityof_webelement_600(
					driver.findElement(By.xpath(OR.AccountName_AutoSuggestionDropDown)), driver);
		}

		Wait.explicit_wait_xpath(OR.AccountName_AutoSuggestionDropDown, driver);
		Thread.sleep(5000);
		int numberOfAutoSuggestions = driver.findElements(By.xpath(OR.AccountName_AutoSuggestionDropDown)).size();
		Utility.app_logs.info("Early Auto Suggestions: " + numberOfAutoSuggestions);
		reserv.Enter_Account.clear();
		reserv.Enter_Account.sendKeys(Account);
		Thread.sleep(5000);
		Wait.explicit_wait_xpath(OR.AccountName_AutoSuggestionDropDown, driver);
		numberOfAutoSuggestions = driver.findElements(By.xpath(OR.AccountName_AutoSuggestionDropDown)).size();
		Utility.app_logs.info("Find Auto Suggestions: " + numberOfAutoSuggestions);
		try {

			if (numberOfAutoSuggestions == 1) {
				if (driver.findElement(By.xpath(OR.AccountName_AutoSuggestionDropDown + "/a/i")).isEnabled()) {
					String message = driver.findElement(By.xpath(OR.AccountName_AutoSuggestionDropDown + "/a/i"))
							.getText();
					// System.out.println(message);
					if (message.contains("No data to display")) {
						assertTrue(false, "NO suggestion found");
					}
				}
			}
		} catch (Exception e) {

		}
		Wait.explicit_wait_xpath(OR.Account_AutoSuggestionsAccountName, driver);
		for (int i = 1; i <= numberOfAutoSuggestions; i++) {

			String accountName = driver
					.findElement(By.xpath("(" + OR.Account_AutoSuggestionsAccountName + ")[" + i + "]")).getText();
			Utility.app_logs.info(accountName + " at Number : " + i);
			if (!accountName.isEmpty()) {
				assertTrue(accountName.contains(Account), "Account Auto Suggestion Account Name missmatched");
			}
		}
		reserv.Enter_Account.clear();
		reserv.Enter_Account.sendKeys(Account);
		Wait.explicit_wait_visibilityof_webelement_120(
				driver.findElement(By.xpath(OR.AccountName_AutoSuggestionDropDown)), driver);
		WebElement acc = driver
				.findElement(By.xpath("(" + OR.Account_AutoSuggestionsAccountName + ")[" + AccountNumber + "]"));
		Wait.explicit_wait_visibilityof_webelement(acc, driver);
		Utility.ScrollToElement(acc, driver);
		acc.click();
		Wait.wait1Second();
		reserv.Copy_Picked_Account_Data_To_Reservation_ContinueButton.click();
		Wait.wait1Second();
		test_steps.add("Account : " + Account);
		assertTrue(reserv.Enter_Account.getAttribute("value").contains(Account), "Account is not Selected");
		Thread.sleep(10000);
		return test_steps;
	}

	public ArrayList<String> VerifyGuestProfile_AutoSuggestion(WebDriver driver, String Guest_Profile,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation reserv = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", reserv.Enter_Guest_Profile);
		reserv.Enter_Guest_Profile.sendKeys(Guest_Profile);
		Wait.wait10Second();
		Wait.explicit_wait_xpath(OR.GuestProfile_AutoSuggestionDropDown, driver);
		int numberOfAutoSuggestions = driver.findElements(By.xpath(OR.GuestProfile_AutoSuggestionDropDown)).size();
		// System.out.println("Find Auto Suggestions: " +
		// numberOfAutoSuggestions);
		if (numberOfAutoSuggestions == 1) {
			if (driver.findElement(By.xpath(OR.GuestProfile_AutoSuggestionDropDown + "/a/i")).isEnabled()) {
				String message = driver.findElement(By.xpath(OR.GuestProfile_AutoSuggestionDropDown + "/a/i"))
						.getText();
				// System.out.println(message);
				if (message.contains("No data to display")) {
					assertTrue(false, "NO suggestion found");
				}
			}
		}
		for (int i = 1; i <= numberOfAutoSuggestions; i++) {
			String guestname = driver
					.findElement(By.xpath("(" + OR.GuestProfile_AutoSuggestionsGuestName + ")[" + i + "]")).getText();
			// System.out.println(guestname + " At " + i);
			// Wait.wait1Second();
			assertTrue(guestname.toLowerCase().contains(Guest_Profile.toLowerCase()),
					"Guest Profile Auto Suggestion Guest Name missmatched " + guestname);
		}
		driver.findElement(By.xpath("(" + OR.GuestProfile_AutoSuggestionsGuestName + ")[1]")).click();
		Wait.wait1Second();
		reserv.Copy_Picked_Account_Data_To_Reservation_ContinueButton.click();
		Wait.wait1Second();

		test_steps.add("Guest Profile : " + Guest_Profile);
		assertTrue(reserv.Enter_Guest_Profile.getAttribute("value").contains(Guest_Profile),
				"Guest Profile is not Selected");
		return test_steps;
	}

	public ArrayList<String> SelectGuestProfile(WebDriver driver, String Guest_Profile, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation reserv = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", reserv.Enter_Guest_Profile);
		reserv.Enter_Guest_Profile.sendKeys(Guest_Profile);
		Wait.wait2Second();
		driver.findElement(By.xpath("//*[contains(text(),'" + Guest_Profile + "')]")).click();
		Wait.wait1Second();
		reserv.Copy_Picked_Account_Data_To_Reservation_ContinueButton.click();
		Wait.wait1Second();

		test_steps.add("Guest Profile : " + Guest_Profile);
		assertTrue(reserv.Enter_Guest_Profile.getAttribute("value").contains(Guest_Profile),
				"Guest Profile is not Selected");
		return test_steps;
	}

	public ArrayList<String> SelectAccount(WebDriver driver, String Account, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation reserv = new Elements_Reservation(driver);
		reserv.Enter_Account.sendKeys(Account);
		Wait.wait2Second();
		Wait.explicit_wait_xpath(OR.AccountName_AutoSuggestionDropDown, driver);
		int numberOfAutoSuggestions = driver.findElements(By.xpath(OR.AccountName_AutoSuggestionDropDown)).size();
		System.out.println("Find Auto Suggestions: " + numberOfAutoSuggestions);

		for (int i = 1; i <= numberOfAutoSuggestions; i++) {

			String accountName = driver
					.findElement(By.xpath("(" + OR.Account_AutoSuggestionsAccountName + ")[" + i + "]")).getText();
			System.out.println(accountName);
			assertTrue(accountName.contains(Account), "Account Auto Suggestion Account Name missmatched");
		}
		driver.findElement(By.xpath("(" + OR.Account_AutoSuggestionsAccountName + ")[1]")).click();
		Wait.wait1Second();
		test_steps.add("Account : " + Account);
		assertTrue(reserv.Enter_Account.getAttribute("value").contains(Account), "Account is not Selected");

		reserv.Copy_Picked_Account_Data_To_Reservation_ContinueButton.click();
		Wait.waitForElementToBeGone(driver, reserv.AccountLoadingToaster, 150);
		return test_steps;
	}

	public void VerifyAccount_AutoSuggestion(WebDriver driver, String Account) throws InterruptedException {
		Elements_Reservation reserv = new Elements_Reservation(driver);
		reserv.Enter_Account.sendKeys(Account);

		try {

			Wait.WaitForElement(driver, OR.AccountName_AutoSuggestionDropDown);
		} catch (Exception e) {
			Utility.app_logs.info("Catch body");
			reserv.Enter_Account.clear();
			reserv.Enter_Account.sendKeys(Account);

			Wait.WaitForElement(driver, OR.AccountName_AutoSuggestionDropDown);

		}
		int numberOfAutoSuggestions = driver.findElements(By.xpath(OR.AccountName_AutoSuggestionDropDown)).size();
		Utility.app_logs.info("Find Auto Suggestions: " + numberOfAutoSuggestions);
		try {
			if (numberOfAutoSuggestions == 1) {
				if (driver.findElement(By.xpath(OR.AccountName_AutoSuggestionDropDown + "/a/i")).isEnabled()) {
					String message = driver.findElement(By.xpath(OR.AccountName_AutoSuggestionDropDown + "/a/i"))
							.getText();
					if (message.contains("No data to display")) {
						assertTrue(false, "NO suggestion found");
					}
				}
			}
		} catch (Exception e) {

		}
		int start_index = 1;
		for (int i = start_index; i <= numberOfAutoSuggestions; i++) {

			String accountName = driver
					.findElement(By.xpath("(" + OR.Account_AutoSuggestionsAccountName + ")[" + i + "]")).getText();
			if (accountName.contentEquals("")) {
				Utility.app_logs.info("Empty account Name found at : " + i);
				accountName = driver.findElement(By.xpath("(" + OR.Account_AutoSuggestionsAccountName + ")[" + i + "]"))
						.getText();
				Utility.app_logs.info(accountName);
			}
			assertTrue(accountName.contains(Account), "Account Auto Suggestion Account Name missmatched");
		}
		driver.findElement(By.xpath("(" + OR.Account_AutoSuggestionsAccountName + ")[" + start_index + "]")).click();
		Wait.wait1Second();
		reserv.Copy_Picked_Account_Data_To_Reservation_ContinueButton.click();
		Wait.wait1Second();
		assertTrue(reserv.Enter_Account.getAttribute("value").contains(Account), "Account is not Selected");
		Thread.sleep(10000);
	}

	public ArrayList<String> VerifyAddress_AutoSuggestion(WebDriver driver, String Address,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation reserv = new Elements_Reservation(driver);
		reserv.Enter_Address_Search.sendKeys(Address);
		Wait.wait2Second();
		Wait.explicit_wait_xpath(OR.Address_AutoSuggestionDropDown, driver);
		int numberOfAutoSuggestions = driver.findElements(By.xpath(OR.Address_AutoSuggestionDropDown)).size();
		System.out.println("Find Auto Suggestions: " + numberOfAutoSuggestions);
		for (int i = 1; i <= numberOfAutoSuggestions; i++) {
			String address = driver.findElement(By.xpath("(" + OR.Address_AutoSuggestionsAddress + ")[" + i + "]"))
					.getText();
			// System.out.println(address);
			if (address.isEmpty()) {
				assertTrue(false, "No Sugesstion found");
			} else {
				assertTrue(address.toLowerCase().contains(Address.toLowerCase()),
						"Address Auto Suggestion Address missmatched");
			}
		}
		Wait.explicit_wait_xpath(OR.Address_AutoSuggestionsAddress, driver);
		driver.findElement(By.xpath("(" + OR.Address_AutoSuggestionsAddress + ")[1]")).click();
		Wait.wait2Second();
		test_steps.add("Address : " + reserv.Enter_Address_Search.getAttribute("value"));
		// assertTrue(reserv.Enter_Address_Search.getAttribute("value").contains(Address),
		// "Address is not Selected");
		return test_steps;

	}

	public ArrayList<String> ClearStayDates(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.AdvancedSearch_StayFromDate.clear();
		assertTrue(ReservationPage.AdvancedSearch_StayFromDate.getAttribute("value").isEmpty(),
				"Stay From date is not Cleared");
		ReservationPage.AdvancedSearch_StayToDate.clear();
		assertTrue(ReservationPage.AdvancedSearch_StayToDate.getAttribute("value").isEmpty(),
				"Stay To date is not Cleared");
		Wait.wait2Second();

		test_steps.add("Stay Date Cleared");
		reservationLogger.info("Stay Date Cleared");
		return test_steps;
	}

	public void SaveAndCloseReservation(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.SaveAndCloseReservation, driver);
		Elements_Reservations.SaveAndCloseReservation.click();
		Wait.wait2Second();
		try {
			String path = "//*[@id='bpjscontainer_23']/div[2]/div[2]/div//div[@class='modules_loading']";
			if (driver.findElement(By.xpath(path)).isDisplayed()) {
				WebElement ele = driver.findElement(By.xpath(path));
				Wait.waitForElementToBeGone(driver, ele, 10000);
			}
		} catch (Exception e) {
			System.out.println("Gone");
		}

	}

	public void IsSaveEnabled(WebDriver driver) throws InterruptedException {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		if (Elements_Reservations.ReservationSaveButton.isEnabled()) {
			reservationLogger.info("Reservation Button Enabled");
			Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationSaveButton, driver);
			Utility.ScrollToElement(Elements_Reservations.ReservationSaveButton, driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", Elements_Reservations.ReservationSaveButton);
			reservationLogger.info("Click on save button");
			try {
				Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(OR.Toaster_Message_Xpath)),
						driver);
				WebElement Message = driver.findElement(By.xpath(OR.Toaster_Message_Xpath));
				System.out.println("Toaster Message appear : " + Message.getText());
				Message.click();
			} catch (Exception e) {
				boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;

				if (size) {
					jse.executeScript("arguments[0].click();",
							Elements_Reservations.CancelDepositePolicy_Button.get(3));
					// Elements_Reservations.CancelDepositePolicy_Button.get(3).click();

				}
				Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(OR.Toaster_Message_Xpath)),
						driver);
				WebElement Message = driver.findElement(By.xpath(OR.Toaster_Message_Xpath));
				System.out.println("Toaster Message appear : " + Message.getText());
				Message.click();
				Wait.wait1Second();
			}
		}
		if (Elements_Reservations.ReservationSaveButton.isEnabled()) {
			reservationLogger.info("Reservation Button Enabled");
		} else {
			reservationLogger.info("Reservation Button Not Enabled");
		}

	}

	public void CloseOpenedReservation(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.OpenedReservation_CloseButton)),
				driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(OR.OpenedReservation_CloseButton)), driver);
		driver.findElement(By.xpath(OR.OpenedReservation_CloseButton)).click();
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.New_Reservation_Button, driver);
		} catch (Exception e) {

		}
		driver.navigate().refresh();

	}

	public void SavaCloseReservation_DepositPolicy(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.SaveAndCloseReservation, driver);
		Elements_Reservations.SaveAndCloseReservation.click();
		Wait.wait2Second();

		Wait.wait2Second();
		boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;
		if (size) {
			Elements_Reservations.CancelDepositePolicy_Button.get(3).click();
			Wait.wait2Second();
		}
	}

	public void ReservationStatus(WebDriver driver, String Status) {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_xpath(OR.ReservationStatus, driver);
		Wait.WaitForElement(driver, OR.ReservationStatus);

		Select sel = new Select(ReservationPage.ReservationStatus);
		sel.selectByVisibleText(Status);

		assertTrue(ReservationPage.ReservationStatus.getText().contains(Status), "Reservation Status changed Failed");
	}

	public String VerifyStatus(WebDriver driver, String Status) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.ReservationStatus);
		Wait.waitUntilPresenceOfElementLocated(OR.ReservationStatus, driver);
		Wait.wait2Second();
		String ResStatus = new Select(ReservationPage.ReservationStatus).getFirstSelectedOption().getText();
		System.out.println("ResStatus:" + ResStatus);
		assertTrue(ResStatus.contains(Status), "Reservation Status changed Failed");
		return ResStatus;
	}

	public String GetCancellationID(WebDriver driver) {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.ReservationCanellationID, driver);
		assertTrue(ReservationPage.ReservationCanellationID.isDisplayed(),
				"Reservation Cancellation ID is not Displaying");
		String ID = ReservationPage.ReservationCanellationID.getText();
		System.out.println("Cancellation ID" + ID);
		return ID;
	}

	public void VerifyReservationStatus(WebDriver driver) {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_xpath(OR.ReservationStatus, driver);
		Wait.WaitForElement(driver, OR.ReservationStatus);

		Select sel = new Select(ReservationPage.ReservationStatus);
		ArrayList<String> reservationStatus = new ArrayList<String>();

		reservationStatus.add("Reserved");
		reservationStatus.add("Confirmed");
		reservationStatus.add("Guaranteed");
		System.out.println(reservationStatus);
		List<WebElement> allOptions = sel.getOptions();
		for (int j = 0; j < 3; j++) {
			boolean found = false;
			for (int i = 0; i < allOptions.size(); i++) {
				if (allOptions.get(i).getText().contains(reservationStatus.get(j))) {
					found = true;
					break;
				}
			}
			if (found) {
				System.out.println("Value exists " + reservationStatus.get(j));
			} else {
				assertTrue(false, "Failed: Reservation Status" + reservationStatus.get(j));
			}
		}
	}

	public ArrayList<String> SelectSource_Search(WebDriver driver, String source, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.AdvancedSearched_Source, driver);
		ReservationPage.AdvancedSearched_Source.click();
		System.out.println("Source clicked: " + source);
		String source_xpath = "//span[text()='" + source + "']";
		Wait.explicit_wait_xpath(source_xpath, driver);
		driver.findElement(By.xpath(source_xpath)).click();
		Wait.wait2Second();
		String selectedRoomClassFromDropdown = ReservationPage.AdvancedSearched_Source.getText();
		assertTrue(selectedRoomClassFromDropdown.contains(source), "Source is not selected ");
		test_steps.add("Source : " + source);
		reservationLogger.info("Source : " + source);
		Wait.wait5Second();
		// Wait.explicit_wait_visibilityof_webelement(ReservationPage.AdvancedSearch_SearchButton);
		ReservationPage.AdvancedSearch_SearchButton.click();
		test_steps.add("Click Search");
		reservationLogger.info("Click Search");
		Wait.WaitForElement(driver, OR.Number_Of_Reservations);
		Wait.wait10Second();
		int count1 = driver.findElements(By.xpath(OR.Number_Of_Reservations)).size();
		System.out.println(count1);
		if (count1 == 0) {
			test_steps.add("No Reservations available for Room Class ");
			reservationLogger.info("No Reservations available for Room Class");
		}
		return test_steps;
	}

	public String AdvancedSearch_GetRoom(WebDriver driver) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Searched_RoomClass, driver);
		String RoomClass = driver.findElements(By.xpath(OR.Searched_RoomClass)).get(0).getText();
		return RoomClass;
	}

	public String AdvancedSearch_GetArrivalDate(WebDriver driver) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", ReservationPage.ArrivalDate);

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.ArrivalDate, driver);
		String ArrivalDate = driver.findElements(By.xpath(OR.ArrivalDate)).get(0).getText();
		return ArrivalDate;
	}

	public String AdvancedSearch_GetResNumber(WebDriver driver) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Searched_ResNumber, driver);
		String ResNumber = driver.findElements(By.xpath(OR.Searched_ResNumber)).get(0).getText();
		return ResNumber;
	}

	public void AdvancedSearch_OpenFirstSearchedReservation(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(OR.Searched_GuestName)), driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Searched_GuestName, driver);
		driver.findElements(By.xpath(OR.Searched_GuestName)).get(0).click();

	}

	public void DeleteTask(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.DeleteTaskButton1);
		// ReservationPage.DeleteTaskButton.click();
		// Wait.wait2Second();
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();",
		// ReservationPage.DeleteTaskYesButton);

	}

	public ArrayList<String> VerifyCategoryTask(WebDriver driver, String categoryName1, String categoryName2,
			String taskName, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.AddTask);
		Wait.waitUntilPresenceOfElementLocated(OR.TaskDetailPopup, driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.SelectTaskCategory, driver);
		Select SelectCategory = new Select(ReservationPage.SelectTaskCategory);
		List<WebElement> CategoriesName = SelectCategory.getOptions();
		boolean ctg1found, ctg2found;
		ctg1found = ctg2found = false;
		for (WebElement category : CategoriesName) {
			String name = category.getText();
			System.out.println(name);
			if (name.contains(categoryName1)) {
				ctg1found = true;
				test_steps.add("Category : " + categoryName1 + " Exist.");
				reservationLogger.info("Category : " + categoryName1 + " Exist.");
			} else if (name.contains(categoryName2)) {
				ctg2found = true;
				test_steps.add("Category : " + categoryName2 + " Exist.");
				reservationLogger.info("Category : " + categoryName2 + " Exist.");
			}
			if (ctg1found == true && ctg2found == true) {
				break;
			}
		}
		if (ctg2found == true) {

			ReservationPage.SelectTaskCategory.click();
			SelectCategory.selectByVisibleText(categoryName2);

			test_steps.add("Select category : " + categoryName2);
			reservationLogger.info("Select category : " + categoryName2);

		} else {
			assertTrue(false, "Failed: Created category : " + categoryName2 + " not found.");
		}

		Select SelectTask = new Select(ReservationPage.SelectTaskType);
		List<WebElement> TaskTypes = SelectTask.getOptions();
		boolean taskfound;
		taskfound = false;
		for (WebElement task : TaskTypes) {
			String name = task.getText();
			System.out.println(name);
			if (name.contains(taskName)) {
				taskfound = true;
				test_steps.add("Task : " + taskName + " Exist.");
				reservationLogger.info("Task : " + taskName + " Exist.");
				break;
			}
		}
		if (taskfound == true) {
			ReservationPage.SelectTaskType.click();
			SelectTask.selectByVisibleText(taskName);

		} else {
			assertTrue(false, "Failed: Created Task : " + taskName + " not found.");
		}

		ReservationPage.CancelTask.click();
		Wait.wait2Second();
		return test_steps;
	}

	public ArrayList<String> VerifyCategoryTask(WebDriver driver, String categoryName1, String taskName,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.AddTask);
		Wait.waitUntilPresenceOfElementLocated(OR.TaskDetailPopup, driver);
		Select SelectCategory = new Select(ReservationPage.SelectTaskCategory);
		List<WebElement> CategoriesName = SelectCategory.getOptions();
		boolean ctg1found;
		ctg1found = false;
		for (WebElement category : CategoriesName) {
			String name = category.getText();
			// System.out.println(name);
			if (name.replaceAll("\\s", "").equals(categoryName1.replaceAll("\\s", ""))) {
				ctg1found = true;
				test_steps.add("Category : " + categoryName1 + " Exist.");
				reservationLogger.info("Category : " + categoryName1 + " Exist.");
				break;
			}
		}
		if (ctg1found == true) {

			ReservationPage.SelectTaskCategory.click();
			SelectCategory.selectByVisibleText(categoryName1);

			test_steps.add("Select category : " + categoryName1);
			reservationLogger.info("Select category : " + categoryName1);

		} else {
			assertTrue(false, "Failed: Created category : " + categoryName1 + " not found.");
		}

		Wait.wait3Second();
		Select SelectTask = new Select(ReservationPage.SelectTaskType);
		List<WebElement> TaskTypes = SelectTask.getOptions();
		boolean taskfound;
		taskfound = false;
		for (WebElement task : TaskTypes) {
			String name = task.getText();
			System.out.println(name);
			if (name.replaceAll("\\s", "").equals(taskName.replaceAll("\\s", ""))) {
				taskfound = true;
				test_steps.add("Task : " + taskName + " Exist.");
				reservationLogger.info("Task : " + taskName + " Exist.");
				break;
			}
		}
		if (taskfound == true) {
			ReservationPage.SelectTaskType.click();
			SelectTask.selectByVisibleText(taskName);

		} else {
			assertTrue(false, "Failed: Created Task : " + taskName + " not found.");
		}

		ReservationPage.CancelTask.click();
		Wait.wait2Second();
		return test_steps;
	}

	public ArrayList<String> VerifyTaskInTaskList(WebDriver driver, String taskName, String categoryName1,
			ArrayList<String> test_steps) {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		driver.navigate().refresh();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", ReservationPage.TaskListTable);
		int count = driver.findElements(By.xpath(OR.TaskListTable)).size();
		System.out.println(count);
		boolean found = false;
		for (int i = 1; i <= count; i++) {
			String TaskType = "(//tbody[contains(@data-bind,'taskList')]/tr)[" + i + "]/td[2]/a";
			String name = driver.findElement(By.xpath(TaskType)).getText();
			System.out.println(name);
			if (name.replaceAll("\\s", "").equals(taskName.replaceAll("\\s", ""))) {
				String Category = "(//tbody[contains(@data-bind,'taskList')]/tr)[" + i + "]/td[1]/span";
				if (driver.findElement(By.xpath(Category)).getText().contains(categoryName1)) {
					found = true;
					test_steps.add("Task : " + taskName + " Exist.");
					reservationLogger.info("Task : " + taskName + " Exist.");
					break;
				}
			}
		}
		if (!found) {
			assertTrue(false, "Auto Created Task Not found");
		}
		return test_steps;

	}

	public ArrayList<String> ChangeroomAssignment(WebDriver driver, String Nights, String Adults, String Children,
			String CheckorUncheckAssign, String RoomClassName, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.Click_RoomPicker);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", ReservationPage.Click_RoomPicker);

		ReservationPage.RoomAssignmentButton.click();
		test_steps.add("Successfully clicked on Rooms Picker");
		reservationLogger.info("Successfully clicked on Rooms Picker");

		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Check_Assign_Room, driver);
		Wait.wait5Second();
		if (ReservationPage.Check_Assign_Room.isSelected()) {
			// ReservationPage.Check_Assign_Room.click();
			Wait.WaitForElement(driver, OR.Click_Search);
			Wait.explicit_wait_xpath(OR.Click_Search, driver);
			ReservationPage.Click_Search.click();
			test_steps.add("Successfully clicked Searche");
			reservationLogger.info("Successfully clicked Search");
		} else {
			if (CheckorUncheckAssign.equals("Yes")) {
				// jse.executeScript("arguments[0].click();",
				// ReservationPage.Check_Assign_Room);
				Wait.wait2Second();
				Utility.ScrollToElement(ReservationPage.Check_Assign_Room, driver);
				ReservationPage.Check_Assign_Room.click();
				test_steps.add("Successfully clicked on assign rooms");
				reservationLogger.info("Successfully clicked on assign rooms");
				try {
					ReservationPage.Click_Search.click();
					Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.SelectRoomNumbers, driver);
				} catch (Exception e) {

					jse.executeScript("arguments[0].click();", ReservationPage.Click_Search);
				}

				test_steps.add("Successfully clicked on search");
				reservationLogger.info("Successfully clicked on search");
			} else {
				jse.executeScript("arguments[0].click();", ReservationPage.Click_Search);
				Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.SelectRoomNumbers, driver);
				// ReservationPage.Click_Search.click();
				test_steps.add("Successfully clicked on search");
				reservationLogger.info("Successfully clicked on search");
			}
		}

		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}
		new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);

		Utility.roomIndex++;
		Wait.wait1Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		// jse.executeScript("arguments[0].click();",
		// ReservationPage.SelectButton);
		ReservationPage.SelectButton.click();
		Wait.wait1Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			jse.executeScript("arguments[0].click();", ReservationPage.Continue.get(9));
			// ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			jse.executeScript("arguments[0].click();", ReservationPage.RoleBroken_Continue);
			// ReservationPage.RoleBroken_Continue.click();
		}
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.ReCalculate_Folio_Options_PopUp, driver);
			if (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
				jse.executeScript("arguments[0].click();", ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed);
				jse.executeScript("arguments[0].click();", ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn);
				// ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();
				// ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn.click();
			}
		} catch (Exception e) {
			reservationLogger.info("No ReCalculate Folio Options PopUp");
			test_steps.add("No ReCalculate Folio Options PopUp");
		}
		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		reservationLogger.info(driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).getText());
		driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).click();
		return test_steps;
	}

	public ArrayList<String> LockButtonClick(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Utility.ScrollToElement(ReservationPage.Click_LockButton, driver);
		WebElement LockButtonState_BeforeLock = driver.findElement(By.cssSelector("i.fa.fa-unlock.lockW13"));
		assertTrue(LockButtonState_BeforeLock.isDisplayed(), "Button State is locked already");
		test_steps.add("Reservation Status dropdown is unlocked");

		ReservationPage.Click_LockButton.click();
		reservationLogger.info("Click Lock Button");

		WebElement LockButtonState = driver
				.findElement(By.xpath("(//div[@class='col-md-8']//button)[2][contains(@style,'display: none')]"));
		assertTrue(!LockButtonState.isDisplayed(), "Lock Button didn't click");
		test_steps.add("Reservation Status dropdown is Locked ");

		return test_steps;

	}

	public ArrayList<String> RoomPickerAfterLockFunctionality(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.RoomAssignmentButton.click();
		WebElement ErrorMsg = driver.findElement(By.xpath("//div[contains(@data-bind,'visible: ShowError')]"));

		assertTrue(
				ErrorMsg.getText().equals("Please note that this guest has requested not to have their room changed."),
				"Failed: Error idn'tnot showed");
		test_steps.add("Message Displayed:" + ErrorMsg.getText());
		return test_steps;
	}

	public void ClickPayButton(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.Click_Pay_Button);
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		try {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);
			assertTrue(elements_All_Payments.Reservation_PaymentPopup.isDisplayed(),
					"Failed: Payment Popup not displayed");
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentPopUp, driver);
			assertTrue(elements_All_Payments.PaymentPopUp.isDisplayed(), "Failed: Payment Popup not displayed");
		}
		Utility.app_logs.info("Click Pay");
	}

	public ArrayList<String> AuthorizedPayment(WebDriver driver, String PaymentType, String CardName, String CCNumber,
			String CCExpiry, String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue,
			String traceData, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Utility.ScrollToElement(ReservationPage.Click_Pay_Button, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.Click_Pay_Button);
		// ReservationPage.Click_Pay_Button.click();
		Utility.app_logs.info("Click Pay");
		test_steps.add("Click Pay");

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		try {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);
			assertTrue(elements_All_Payments.Reservation_PaymentPopup.isDisplayed(),
					"Failed: Payment Popup not displayed");
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentPopUp, driver);
			assertTrue(elements_All_Payments.PaymentPopUp.isDisplayed(), "Failed: Payment Popup not displayed");
		}
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		try {
			new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(PaymentType);
			new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText(Authorizationtype);

			Wait.wait3Second();

			Utility.app_logs
					.info(new Select(ReservationFolio.Select_Authorization_type).getFirstSelectedOption().getText());
			assertTrue(new Select(ReservationFolio.Select_Authorization_type).getFirstSelectedOption().getText()
					.contains("Authorization"), "Failed : Authorization is not selected");
			ReservationFolio.Click_Card_info.click();
			Utility.app_logs.info("Clicked on card info");
			Wait.explicit_wait_xpath(OR.Verify_payment_info_popup, driver);
			assertTrue(ReservationFolio.Verify_payment_info_popup.isDisplayed(), "Payment Info Popup didn't display");
			Wait.wait3Second();
			ReservationFolio.Enter_Card_Name.sendKeys(CardName);
			ReservationFolio.Enter_Account_Number_Folio.clear();
			ReservationFolio.Enter_Account_Number_Folio.sendKeys(CCNumber);
			assertTrue(ReservationFolio.Enter_Account_Number_Folio.getAttribute("value").equals(CCNumber),
					"AccountNumber didn't match");
			ReservationFolio.Enter_CC_Expiry.clear();
			ReservationFolio.Enter_CC_Expiry.sendKeys(CCExpiry);
			ReservationFolio.Enter_CVVCode.sendKeys(CCVCode);
			ReservationFolio.Click_OK.click();
			Utility.app_logs.info("Clicked on OK button");
			Wait.wait10Second();
			if (ChangeAmount.equalsIgnoreCase("Yes")) {
				ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
			} else {
				Utility.app_logs.info("Processing the amount displayed");
			}
			Utility.ScrollToElement(ReservationFolio.Click_Process, driver);
			ReservationFolio.Click_Process.click();
			Utility.app_logs.info("Clicked on Process Button");
			test_steps.add("Click Process Button");
			Thread.sleep(1000);
			Wait.wait3Second();
			Wait.WaitForElement(driver, OR.Verify_MC_Grid);
			assertTrue(ReservationFolio.Verify_MC_Grid.isDisplayed(), "MC line item didn't verify in payment page");
			String GetPaymentMethod = ReservationFolio.GetMC_Payment_Method.getText();
			Utility.app_logs.info("PaymentMethod: " + " " + GetPaymentMethod);
			Utility.app_logs.info("PaymentTyepe: " + " " + PaymentType);

			if (GetPaymentMethod.equals(PaymentType)) {
				Utility.app_logs.info("Paymnet is successful");
			} else {
				Utility.app_logs.info("Paymnet is Failed");
			}

			Wait.wait1Second();
			Utility.ScrollToElement(ReservationFolio.Processed_Amount_In_Paymentdetails_Popup, driver);
			Wait.wait2Second();
			String Processed_Amount = ReservationFolio.Processed_Amount_In_Paymentdetails_Popup.getText();
			Utility.app_logs.info("Processed_Amount " + Processed_Amount + " -- " + Processed_Amount);
			Utility.ScrollToElement(ReservationFolio.Click_Continue, driver);
			ReservationFolio.Click_Continue.click();
			Utility.app_logs.info("Clicked on continue button");
			test_steps.add("Click Continue");
			Wait.wait3Second();
			Wait.WaitForElement(driver, OR.GetAddedLine_MC);
			assertTrue(ReservationFolio.GetAddedLine_MC.isDisplayed(), "MC line didn't added in folio page");
			String GetMCCard = ReservationFolio.GetAddedLine_MC.getText();
			Utility.app_logs.info("Transaction Line Item: " + GetMCCard);
			if (GetMCCard.equalsIgnoreCase("Name: MC Account #: XXXX5454 Exp. Date: 08/19")) {
				Utility.app_logs.info("Payment is successful");
				test_steps.add("Payment is successful");
			} else {
				Utility.app_logs.info("Payment is Failed");
				test_steps.add("Payment is Failed");
			}
		} catch (Exception e) {
			((IExtentTestClass) test_steps).log(LogStatus.FAIL,
					"Exception occured while paying using MC \n" + e.getMessage()
							+ "\n\n <br> Attaching screenshot below : \n"
							+ ((IExtentTestClass) test_steps).addScreenCapture(
									Utility.captureScreenShot(((IExtentTestClass) test_steps).getTest().getName()
											+ "_Payment_ByMC" + Utility.getTimeStamp(), driver)));
			Utility.app_logs.info("Exception occured while paying using MC \n");
			e.printStackTrace();
		}
		return test_steps;
	}

	public ArrayList<String> VerifyRoom_RoomMainCreatedDeleted(WebDriver driver, ExtentTest test, String RoomClass,
			String DisplayStatus, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		ReservationPage.RoomAssignment_DateIcon.click();
		ReservationPage.SelectDate.click();
		ReservationPage.RoomAssign_Check.click();
		ReservationPage.SearchRoomButton.click();
		String RoomName = RoomClass.split(" : ")[0];
		String RoomNumber = RoomClass.split(" : ")[1];
		Wait.wait2Second();
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomName);
		test_steps.add("Select Room Class: " + RoomName);
		reservationLogger.info("Select Room Class: " + RoomName);
		ReservationPage.SelectRoomNumbers.click();
		if (DisplayStatus.equals("No") || DisplayStatus.equals("no")) {
			assertTrue(!RoomNumber.equals(ReservationPage.FirstRoomNumberInList.getText()),
					"Failed: Room Number Displayed for which room maintenance item is displayed");
		} else {
			assertTrue(RoomNumber.equals(ReservationPage.FirstRoomNumberInList.getText()),
					"Failed: Room Number Displayed for which room maintenance item is not displayed");
		}
		return test_steps;
	}

	public void AuthorizedPaymentWthCC(WebDriver driver, String PaymentType, String CardName, String CCNumber,
			String CCExpiry, String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue,
			String traceData) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Actions action = new Actions(driver);
		double processedamount = 0;
		ReservationPage.Click_Checkin.click();
		reservationLogger.info("Clicked on CheckIn button");

		try {
			Wait.explicit_wait_10sec(ReservationPage.Already_Checked_In_Confirm_Popup, driver);
			ReservationPage.Already_Checked_In_Confirm_Popup_Confirm_Btn.click();
			flag = true;
		} catch (Exception e) {

			reservationLogger.info("No conflicts with room assignment");
		}
		if (flag == true) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Room_Assignment_PopUp, driver);
			Select selectRoomNumber = new Select(ReservationPage.Select_Room_Number);
			String selectedOption = selectRoomNumber.getFirstSelectedOption().getText();
			if (selectedOption.contains("(O and")) {

				String RoomwithVandC = driver.findElement(By.xpath("//option[contains(text(),'(V and C)')] "))
						.getAttribute("value");
				selectRoomNumber.selectByValue(RoomwithVandC);
				reservationLogger.info("Selected first available room with V and C status from the list");
				Wait.wait2Second();
				ReservationPage.Click_Select.click();
				reservationLogger.info("Clicked on select button of room assignment popup");
				try {

					Wait.explicit_wait_visibilityof_webelement(ReservationPage.ReCalculate_Folio_Options_PopUp, driver);
					if (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
						ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();
						action.moveToElement(ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn).click().build()
								.perform();

					}
				} catch (Exception e) {
					reservationLogger.info("No ReCalculate Folio Options PopUp");
				}

			} else {

				reservationLogger.info("No Issues");
			}
		}
		if (flag == false) {
			Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
			Wait.wait5Second();
			// long Loadguestregform_StartTime = System.currentTimeMillis();
			try {
				ReservationPage.Click_Select.click();
				reservationLogger.info("Clicked on select button of room assignment popup");
				Wait.wait3Second();
				Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
			} catch (Exception e) {

			}
		}
		Wait.wait10Second();

		try {

			if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
				ReservationPage.Click_Continue_RuleBroken_Popup.click();
				reservationLogger.info("Clicked on continue button of RulesBroken Popup");
			} else {
				reservationLogger.info("Satisfied Rules");
			}
			if (ReservationPage.Verify_Dirty_Room_popup.isDisplayed()) {
				ReservationPage.Confirm_button.click();
				reservationLogger.info("Clicked on confirm button of DirtyRoom Popup");
			} else {
				reservationLogger.info("No Dirty Rooms");
			}
		} catch (Exception e) {

		}
		Wait.wait10Second();
		try {
			if (ReservationPage.Payment_Popup.isDisplayed()) {
				reservationLogger.info("This Reservation has checkin ploicy, Payment popup is displayed");
				ReservationFolio Payment = new ReservationFolio();
				checkinpolicy = true;

			}
		} catch (Exception e) {
			reservationLogger.info("Checkin Policy doesn't exist");
		}

		if (checkinpolicy == false) {
			reservationLogger
					.info("Trying to Clicking on Confirm button of Guest Registration Form in Reservation.java");
			// Thread.sleep(10000);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_on_confirm, driver);
			ReservationPage.Click_on_confirm.click();
			Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
			Wait.wait3Second();
		}

		try {
			if (ReservationPage.Key_Generation_Popup.isDisplayed()) {
				ReservationPage.Key_Generation_Close.click();
				Wait.wait15Second();
			}
		} catch (Exception e) {
			reservationLogger.info("Key Geneartion doesnt exist");
		}
		checkinpolicy = false;
		assertTrue(ReservationPage.Payment_Popup.isDisplayed(), "Failed: Payment Popup not displayed");
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		try {
			new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(PaymentType);
			new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText(Authorizationtype);

			Wait.wait3Second();
			assertTrue(new Select(ReservationFolio.Select_Authorization_type).getFirstSelectedOption().getText()
					.contains("Authorization"), "Failed : Authorization is not selected");
			ReservationFolio.Click_Card_info.click();
			Utility.app_logs.info("Clicked on card info");
			Wait.explicit_wait_xpath(OR.Verify_payment_info_popup, driver);
			Wait.wait3Second();
			ReservationFolio.Enter_Card_Name.sendKeys(CardName);
			ReservationFolio.Enter_Account_Number_Folio.clear();
			ReservationFolio.Enter_Account_Number_Folio.sendKeys(CCNumber);
			ReservationFolio.Enter_CC_Expiry.clear();
			ReservationFolio.Enter_CC_Expiry.sendKeys(CCExpiry);
			ReservationFolio.Enter_CVVCode.sendKeys(CCVCode);
			ReservationFolio.Click_OK.click();
			Utility.app_logs.info("Clicked on OK button");
			Wait.wait10Second();
			if (ChangeAmount.equalsIgnoreCase("Yes")) {
				ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
			} else {
				Utility.app_logs.info("Processing the amount displayed");
			}
			ReservationFolio.Click_Process.click();
			Utility.app_logs.info("Clicked on Process Button");
			reservationLogger.info("Click Process Button");

			Thread.sleep(1000);
			Wait.wait3Second();
			Wait.explicit_wait_xpath(OR.Verify_MC_Grid, driver);
			String GetPaymentMethod = ReservationFolio.GetMC_Payment_Method.getText();
			Utility.app_logs.info("PaymentMethod: " + " " + GetPaymentMethod);
			if (GetPaymentMethod.equals(PaymentType)) {
				Utility.app_logs.info("Paymnet is successful");
			} else {
				Utility.app_logs.info("Paymnet is Failed");
			}

			Wait.wait1Second();
			Utility.ScrollToElement(ReservationFolio.Processed_Amount_In_Paymentdetails_Popup, driver);
			Wait.wait2Second();
			String Processed_Amount = ReservationFolio.Processed_Amount_In_Paymentdetails_Popup.getText();
			Utility.app_logs.info("Processed_Amount " + Processed_Amount + " -- " + Processed_Amount);

			ReservationFolio.Click_Continue.click();
			Utility.app_logs.info("Clicked on continue button");
			Wait.wait3Second();
			Wait.explicit_wait_xpath(OR.GetAddedLine_MC, driver);
			String GetMCCard = ReservationFolio.GetAddedLine_MC.getText();
			Utility.app_logs.info("Transaction Line Item: " + GetMCCard);
			if (GetMCCard.equalsIgnoreCase("Name: MC Account #: XXXX5454 Exp. Date: 08/21")) {
				Utility.app_logs.info("Payment is successful");
			} else {
				Utility.app_logs.info("Payment is Failed");
			}
		} catch (Exception e) {
			TestCore.test.log(LogStatus.FAIL,
					"Exception occured while paying using MC \n" + e.getMessage()
							+ "\n\n <br> Attaching screenshot below : \n"
							+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
									TestCore.test.getTest().getName() + "_Payment_ByMC" + Utility.getTimeStamp(),
									driver)));
			Utility.app_logs.info("Exception occured while paying using MC \n");
			e.printStackTrace();
		}
	}

	public void verifyRoomCharges(WebDriver driver, String newRateValue, boolean isfloat) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.RoomCharges, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-300)");
		Wait.wait2Second();
		if (!isfloat) {

			newRateValue = "$ " + newRateValue + ".00";
			System.out.println("expected:" + ReservationPage.RoomCharges.getText() + "Calculated:" + newRateValue);
			assertTrue(ReservationPage.RoomCharges.getText().equals(newRateValue),
					"Failed : Room Charges are not the updted one");
		} else {
			newRateValue = "$ " + newRateValue;
			System.out.println("expected:" + ReservationPage.RoomCharges.getText() + "Calculated:" + newRateValue);

			assertTrue(ReservationPage.RoomCharges.getText().equals(newRateValue),
					"Failed : Room Charges are not the updted one");
		}

	}

	public String GetAndVerifyRoomCharges(WebDriver driver, boolean verify, boolean Voided)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.RoomCharges, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-300)");
		Wait.wait2Second();
		String RoomCharges = ReservationPage.RoomCharges.getText().split(" ")[1];

		if (verify) {
			if (Voided) {
				assertEquals(RoomCharges, "0.00", "Failed : Room Charges are not Voided");
			} else {
				assertNotEquals(RoomCharges, "0.00", "Failed : Room Charges are Voided");
			}
		}
		return RoomCharges;

	}

	public ArrayList<String> Checkout_CaptureAmount(WebDriver driver, String Authorizationtype,
			ArrayList<String> test_steps) throws InterruptedException, IOException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		String ResNumber = GetReservationnumber(driver);
		Wait.wait5Second();
		ReservationPage.Click_Checkout.click();
		reservationLogger.info("Clicked on CHECKOUT button");
		// Wait.wait5Second();
		try {
			Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Resell_Rooms_Popup, driver);
			if (ReservationPage.Resell_Rooms_Popup.isDisplayed()) {
				ReservationPage.Resell_Rooms_Popup_Continue_Btn.click();
				Utility.app_logs.info("Resell_Rooms_Popup");
			}
		} catch (Exception e) {
			Utility.app_logs.info("No resell_Rooms_Popup");
		}
		Wait.WaitForElement(driver, OR.Verify_Payment_Details_poup);

		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(ReservationFolio.Select_Authorization_type, driver);
		new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText(Authorizationtype);

		Thread.sleep(3000);
		Utility.app_logs
				.info(new Select(ReservationFolio.Select_Authorization_type).getFirstSelectedOption().getText());
		assertTrue(new Select(ReservationFolio.Select_Authorization_type).getFirstSelectedOption().getText()
				.contains(Authorizationtype), "Failed : " + Authorizationtype + " is not selected");

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Process, driver);
		ReservationFolio.Click_Process.click();
		Utility.app_logs.info("Clicked on Process Button");
		test_steps.add("Click Process Button");

		Wait.explicit_wait_absenceofelement(OR.Payment_ModuleLoading, driver);

		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Click_Continue, driver);
		ReservationPage.Click_Continue.click();
		reservationLogger.info("Clicking on Continue");
		Wait.wait2Second();
		driver.navigate().refresh();
		reservationLogger.info("Reload Page");
		// login_NONGS(driver, test_steps);
		ReservationSearch resSearch = new ReservationSearch();
		resSearch.SearchAndOpenRes(driver, ResNumber);
		reservationLogger.info("Open Reservation " + ResNumber);
		test_steps.add("Open Reservation " + ResNumber);
		VerifyStatus(driver, "Departed");
		reservationLogger.info("Verified Reservation Status Departed");
		test_steps.add("Verified Reservation Status Departed");
		return test_steps;

		// Wait.wait5Second();
	}

	public void AddCategoryTask(WebDriver driver, String categoryName1, String taskName) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.AddTask);
		Wait.waitUntilPresenceOfElementLocated(OR.TaskDetailPopup, driver);
		Select SelectCategory = new Select(ReservationPage.SelectTaskCategory);
		List<WebElement> CategoriesName = SelectCategory.getOptions();
		boolean ctg1found;
		ctg1found = false;
		for (WebElement category : CategoriesName) {
			String name = category.getText();
			// System.out.println(name);
			if (name.replaceAll("\\s", "").equals(categoryName1.replaceAll("\\s", ""))) {
				ctg1found = true;
				reservationLogger.info("Category : " + categoryName1 + " Exist.");
				break;
			}
		}
		if (ctg1found == true) {

			ReservationPage.SelectTaskCategory.click();
			SelectCategory.selectByVisibleText(categoryName1);

			reservationLogger.info("Select category : " + categoryName1);

		} else {
			assertTrue(false, "Failed: Created category : " + categoryName1 + " not found.");
		}

		Select SelectTask = new Select(ReservationPage.SelectTaskType);
		List<WebElement> TaskTypes = SelectTask.getOptions();
		boolean taskfound;
		taskfound = false;
		for (WebElement task : TaskTypes) {
			String name = task.getText();
			// System.out.println(name);
			if (name.replaceAll("\\s", "").equals(taskName.replaceAll("\\s", ""))) {
				taskfound = true;
				reservationLogger.info("Task : " + taskName + " Exist.");
				break;
			}
		}
		if (taskfound == true) {
			ReservationPage.SelectTaskType.click();
			SelectTask.selectByVisibleText(taskName);

		} else {
			assertTrue(false, "Failed: Created Task : " + taskName + " not found.");
		}

		ReservationPage.SaveTaskButton.click();
		Wait.wait2Second();
	}

	public ArrayList<String> VerifyPaymentViaReservation(WebDriver driver, String ResNumber, String LineItemAmount,
			String PreviousPayment, ArrayList<String> test_steps) throws InterruptedException {

		AddOrPostLineItem folio = new AddOrPostLineItem();
		folio.clickFoliotab(driver);
		String Amount = getLineItemAmount(driver, ResNumber);
		assertEquals(Amount, ("-" + LineItemAmount), "Failed :Amount missmatched");

		Utility.app_logs.info("Successfully Verified Line item with negative amount paid for Reservation " + ResNumber
				+ " is added to Folio");
		test_steps.add("Successfully Verified Line item with negative amount paid for Reservation " + ResNumber
				+ " is added to Folio");

		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		String FolioBalance = FolioLineItems.Payment_Details_Folio_Balance.getText().split(" ")[1];
		String TotalBalance = FolioLineItems.Payment_Details_Total_Balance.getText().split(" ")[1];
		float fbalance = (Float.parseFloat(LineItemAmount) + Float.parseFloat(TotalBalance));
		String balance = String.format("%.2f", fbalance);
		System.out.println(balance);

		assertEquals(FolioBalance, balance, "Failed: Line Item Amount is not added in folio Balance");

		String Payments = GetPayment(driver);
		String Balance = FolioLineItems.PaymentInfo_Balance.getText().split(" ")[1];
		String addPayments_Total = String.format("%.2f", (Float.parseFloat(Payments) + Float.parseFloat(TotalBalance)));
		assertEquals(Payments,
				String.format("%.2f", (Float.parseFloat(PreviousPayment) + Float.parseFloat(LineItemAmount))),
				"Failed: Amount is not added to payments");
		Utility.app_logs.info("Successfully Verified that Amount Paid is added to Payments ");

		test_steps.add("Successfully Verified that Amount Paid is added to Payments ");
		assertEquals(Balance, addPayments_Total, "Failed : Balance != Total Charges + Payments");
		Utility.app_logs.info("Successfully Verified that Balance = Total Charges+Payments");
		test_steps.add("Successfully Verified that Balance = Total Charges+Payments");
		return test_steps;

	}

	public String GetPayment(WebDriver driver) {

		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.PaymentInfo_Payments, driver);
		String Payments = FolioLineItems.PaymentInfo_Payments.getText();
		if (Payments.contains("(")) {
			Payments = Payments.substring((Payments.indexOf("(") + 1), Payments.indexOf(")"));
		} else {
			Payments = Payments.split(" ")[1];
		}
		System.out.println("Payments  = " + Payments);
		return Payments;
	}

	public String getLineItemAmount(WebDriver driver, String LineItemDescription) {
		String Amount = driver.findElement(By.xpath("//a[contains(text(),'" + LineItemDescription
				+ "')]//parent::td//following-sibling::td[@class='lineitem-amount']/span")).getText();
		Amount = Amount.split(" ")[1];
		Utility.app_logs.info("Line Item Amount containing description " + LineItemDescription + " = " + Amount);
		return Amount;

	}

	public ArrayList<String> VerifyLineItemAmount(WebDriver driver, String LineItemDescription, String OverrideRate,
			ArrayList<String> test_steps) throws InterruptedException {
		WebElement LineItemAmount = driver.findElement(By.xpath("//a[contains(text(),'" + LineItemDescription
				+ "')]//parent::td//following-sibling::td[@class='lineitem-amount']/span"));
		Wait.explicit_wait_visibilityof_webelement(LineItemAmount, driver);
		Utility.ScrollToElement(LineItemAmount, driver);
		String Amount = LineItemAmount.getText();
		Amount = Amount.split(" ")[1];
		Utility.app_logs.info("Line Item Amount containing description " + LineItemDescription + " = " + Amount);
		String Tax = driver.findElement(By.xpath("//a[contains(text(),'" + LineItemDescription
				+ "')]//parent::td//following-sibling::td[@class='lineitem-tax']/span")).getText();
		Tax = Tax.split(" ")[1];
		Utility.app_logs.info("Line Item Tax containing description " + LineItemDescription + " = " + Tax);
		float FTax = Float.parseFloat(Tax);
		float FAmount = Float.parseFloat(Amount);
		float difference = FAmount - FTax;
		String Sdifference = String.format("%.2f", difference);
		Utility.app_logs.info(FAmount + " - " + FTax + " = " + difference);
		test_steps.add(FAmount + " - " + FTax + " = " + Sdifference);
		Assert.assertEquals(Sdifference, OverrideRate, "Failed: OverRide Rate Missmatched");
		return test_steps;

	}

	public ArrayList<String> OverrideDepositPolicyClick(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.ReservationSaveButton, driver);
		Utility.ScrollToElement(ReservationPage.ReservationSaveButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		jse.executeScript("arguments[0].click();", ReservationPage.ReservationSaveButton);

		reservationLogger.info("Click on save button");
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.DepositPolicy_Button.get(2), driver);
			ReservationPage.DepositPolicy_Button.get(2).click();
			Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(OR.Toaster_Message_Xpath)),
					driver);
			WebElement Message = driver.findElement(By.xpath(OR.Toaster_Message_Xpath));
			// Thread.sleep(5000);
			Wait.explicit_wait_xpath(OR.PaymentPopUp1, driver);
			// Wait.wait5Second();

			String Amount = "10";
			String PaymentType = "Cash";
			// select payment type as cash
			new Select(elements_All_Payments.PaymentDetail_PaymentType).selectByVisibleText(PaymentType);

			elements_All_Payments.PaymentDetail_Enter_Amount.clear();
			elements_All_Payments.PaymentDetail_Enter_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), Amount);

			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Add_Pay_Button, driver);
			elements_All_Payments.PaymentDetail_Add_Pay_Button.click();

			// Add button
			// jse.executeScript("arguments[0].click();",
			// elements_All_Payments.PaymentDetail_Add_Pay_Button);
			reservationLogger.info("Click Add Pay");
			try {
				System.out.println("try");
				Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button,
						driver);
				elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
				Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);
			} catch (Exception e) {
				System.out.println("catch");
				Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button,
						driver);
				elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
				Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);
			}
			reservationLogger.info("Click Continue Pay");
			Folio folio = new Folio();
			folio.Verify_LineItem(driver, PaymentType.toLowerCase(), Amount, "");
			reservationLogger.info("Cach Payment Done");
		} catch (Exception e) {
			System.out.println("Not show");
		}
		return test_steps;

	}

	public void CheckIN_WithoutPaymentVerifyRoomNumber(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.CheckIn_Button.click();
		Wait.waitUntilPresenceOfElementLocated(OR.SelectButton, driver);
		Thread.sleep(5000);
		if (ReservationPage.ConfirmPopup.isDisplayed()) {
			ReservationPage.ConfirmButton.click();
		}

		String RoomNumber = ReservationPage.FirstRoomNumberInList.getText();
		RoomNumber = RoomNumber.split("  ")[0];
		System.out.println("Number is:" + RoomNumber);
		assertEquals(ReservationPage.FirstRoomNumberInList.getText(), RoomNumber + " (V and I)",
				"RoomNum doesn't match");
		ReservationPage.SelectButton.click();
		Wait.wait2Second();
		if (ReservationPage.ConfirmPopup.isDisplayed()) {
			ReservationPage.ConfirmButton.click();
		}

		Wait.explicit_wait_xpath(OR.CheckIN_Confirm, driver);
		ReservationPage.Toaster_Message.click();
		Wait.wait2Second();
		ReservationPage.CheckIN_Confirm.click();
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		assertEquals(ReservationPage.Toaster_Title.getText(), "Reservation Check In", "Fail to perform checkin");
	}

	public void AddTask(WebDriver driver, String TaskSubject, String TaskDetails, String TaskNoteType,
			String Notestatus) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		jse.executeScript("arguments[0].click();", ReservationPage.Add_Note_Reservation);
		Wait.explicit_wait_xpath(OR.verify_Add_Notes_popup, driver);
		Wait.WaitForElement(driver, OR.Enter_Subject_Notes);
		ReservationPage.Enter_Subject_Notes.sendKeys(TaskSubject);
		ReservationPage.Enter_Note_Details.sendKeys(TaskDetails);
		new Select(ReservationPage.Select_Note_Type).selectByVisibleText("Early Arrival");

		if (ReservationPage.Check_Action_Required.isSelected()) {
			reservationLogger.info("Action Required checkbox was already selected");
		} else {
			ReservationPage.Check_Action_Required.click();
		}

		// new
		// Select(ReservationPage.Select_Notes_Status).selectByVisibleText(Notestatus);

		ReservationPage.Click_Save_Note.click();
		Wait.wait2Second();
		if (ReservationPage.Verify_Added_Notes.isDisplayed()) {
			reservationLogger.info("Created Task: " + TaskSubject);
			test_steps.add("Created Task: " + TaskSubject);
		} else {
			reservationLogger.info("Failed to Add Task");
			test_steps.add("Failed to Add Task");
		}
	}

	public void SaveButton(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Click_Save_ReservationDetails, driver);
		ReservationPage.Click_Save_ReservationDetails.click();
		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Verify_Toaster_Container, driver);
		reservationLogger.info("Successfully clicked on save reservation");

	}

	public String GetReservationNum(WebDriver driver, ArrayList<String> test_steps)
			throws IOException, InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Get_Confirmation_Number, driver);
		String getResNum = ReservationPage.Get_Confirmation_Number.getText();
		reservationLogger.info("ReservationConfirmation :" + getResNum);
		if (getResNum.contains("Pending")) {
			test_steps.add("failed to create Reservatio");
		}
		return getResNum;

	}

	public String GetReservationnumber(WebDriver driver) throws IOException, InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.Get_Confirmation_Number);
		String reservationNumber = ReservationPage.Get_Confirmation_Number.getText();
		for (int i = 0; i < 2; i++) {

			if (reservationNumber.equals("Pending")) {
				Thread.sleep(1000 * 5);
				reservationNumber = ReservationPage.Get_Confirmation_Number.getText();
				continue;
			} else {
				break;
			}
		}
		assertTrue(!reservationNumber.equals("Pending"), "Failed: Reservation Status is Pending");
		reservationLogger.info("ReservationConfirmation : " + reservationNumber);
		ReservationConfirmation = reservationNumber;
		return reservationNumber;

	}

	public String GetReservationnumber(WebDriver driver, ArrayList<String> test_steps) throws IOException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", ReservationPage.Get_Confirmation_Number);
		ReservationConfirmation = ReservationPage.Get_Confirmation_Number.getText();
		if (ReservationConfirmation.contains("Pending")) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Get_Confirmation_Number, driver);
			ReservationConfirmation = ReservationPage.Get_Confirmation_Number.getText();
		}
		reservationLogger.info("ReservationConfirmation :" + ReservationConfirmation);
		// test_steps.add("Reservation Number is : " +
		// ReservationConfirmation);

		if (ReservationConfirmation.contains("Pending")) {
			test_steps.add("Reservation not saved");
		} else {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(".\\ConfirmationNumber.txt"));
				writer.write(ReservationConfirmation);
				writer.flush();
				writer.close();
			} catch (Exception e) {

			}
		}
		return ReservationConfirmation;
	}

	public void CloseRes(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(
				Elements_Reservations.CloseResTab.get(Elements_Reservations.CloseResTab.size() - 1), driver);
		Elements_Reservations.CloseResTab.get(Elements_Reservations.CloseResTab.size() - 1).click();
		Elements_Reservations.AlertForTab_Yes_Btn.click();

	}

	public void CloseRes1(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Elements_Reservations.AlertForTab_Yes_Btn);

		// Elements_Reservations.AlertForTab_Yes_Btn.click();

	}

	public void SplitReservation(WebDriver driver, ExtentTest test, String RoomClass, String NightStay,
			String SplitRoomClass) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignment_DateIcon);
		ReservationPage.NightField.clear();
		ReservationPage.NightField.sendKeys(NightStay);
		Wait.wait1Second();
		jse.executeScript("arguments[0].click();", ReservationPage.Check_Split_Rooms);
		// jse.executeScript("arguments[0].click();",
		// ReservationPage.RoomAssign_Check);
		reservationLogger.info("Checked Room Split checkbox");
		Wait.explicit_wait_elementToBeClickable(ReservationPage.SearchRoomButton, driver);
		ReservationPage.SearchRoomButton.click();

		for (int i = 0; i < Integer.valueOf(NightStay); i++) {

			if (i > 0) {

				Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRooms.get(i), driver);
				new Select(ReservationPage.SplitRooms.get(i)).selectByVisibleText(SplitRoomClass);
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRoomNumbers.get(i), driver);

				if (Utility.roomIndex == Utility.roomIndexEnd) {
					Utility.roomIndex = 1;
				}
				new Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(Utility.roomIndex);
				Utility.roomIndex++;
				// new
				// Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(1);
			} else {

				Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRooms.get(i), driver);
				new Select(ReservationPage.SplitRooms.get(i)).selectByVisibleText(RoomClass);
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRoomNumbers.get(i), driver);
				if (Utility.roomIndex == Utility.roomIndexEnd) {
					Utility.roomIndex = 1;
				}
				new Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(Utility.roomIndex);
				Utility.roomIndex++;
				// new
				// Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(1);
			}

		}

		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();

		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.wait1Second();
		ReservationPage.RoomCharger_SelectButton.get(1).click();
		Wait.wait2Second();
		try {
			if (ReservationPage.Policy_Comparision_PopUp.isDisplayed()) {
				ReservationPage.Policy_Comparision_PopUp_Continue_Btn.click();
			}
		} catch (Exception e) {
			System.out.println("Policy Comparision Popup not displayed ");
		}
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).click();
	}

	public void VerifyRoomAssignmentStatus(WebDriver driver, String RoomAssignment_Status) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.RoomAssignmentStatus, driver);
		assertEquals(ReservationPage.RoomAssignmentStatus.getText(), RoomAssignment_Status,
				"Room asssignment status never change");
	}

	public boolean SaveButton_IsEnabled(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.ReservationSaveButton, driver);
		if (ReservationPage.ReservationSaveButton.isEnabled()) {
			return true;
		} else {
			Thread.sleep(15000);
			Wait.explicit_wait_elementToBeClickable(ReservationPage.ReservationSaveButton, driver);
			if (ReservationPage.ReservationSaveButton.isEnabled()) {
				return true;
			}
		}
		return false;
	}

	public boolean SaveButtonEnabled(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.ReservationSaveButton, driver);
		return ReservationPage.ReservationSaveButton.isEnabled();
	}
	// Testing

	public ArrayList<String> AuthorizedPaymentWthCC_1(WebDriver driver, String PaymentType, String CardName,
			String CCNumber, String CCExpiry, String CCVCode, String Authorizationtype, String ChangeAmount,
			String ChangeAmountValue, String traceData, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationFolio Payment = new ReservationFolio();
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		Actions action = new Actions(driver);
		double processedamount = 0;
		ReservationPage.Click_Checkin.click();
		reservationLogger.info("Clicked on CheckIn button");

		try {
			Wait.explicit_wait_10sec(ReservationPage.Already_Checked_In_Confirm_Popup, driver);
			ReservationPage.Already_Checked_In_Confirm_Popup_Confirm_Btn.click();
			flag = true;
		} catch (Exception e) {

			reservationLogger.info("No conflicts with room assignment");
		}
		if (flag == true) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Room_Assignment_PopUp, driver);
			Select selectRoomNumber = new Select(ReservationPage.Select_Room_Number);
			String selectedOption = selectRoomNumber.getFirstSelectedOption().getText();
			if (selectedOption.contains("(O and")) {

				String RoomwithVandC = driver.findElement(By.xpath("//option[contains(text(),'(V and C)')] "))
						.getAttribute("value");
				selectRoomNumber.selectByValue(RoomwithVandC);
				reservationLogger.info("Selected first available room with V and C status from the list");
				Wait.wait2Second();
				ReservationPage.Click_Select.click();
				reservationLogger.info("Clicked on select button of room assignment popup");
				try {

					Wait.explicit_wait_visibilityof_webelement(ReservationPage.ReCalculate_Folio_Options_PopUp, driver);
					if (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
						ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();
						action.moveToElement(ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn).click().build()
								.perform();

					}
				} catch (Exception e) {
					reservationLogger.info("No ReCalculate Folio Options PopUp");
				}

			} else {

				reservationLogger.info("No Issues");
			}
		}
		if (flag == false) {
			Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
			Wait.wait5Second();
			// long Loadguestregform_StartTime = System.currentTimeMillis();
			try {
				ReservationPage.Click_Select.click();
				reservationLogger.info("Clicked on select button of room assignment popup");
				Wait.wait3Second();
				Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
			} catch (Exception e) {

			}
		}
		Wait.wait10Second();

		try {

			if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
				ReservationPage.Click_Continue_RuleBroken_Popup.click();
				reservationLogger.info("Clicked on continue button of RulesBroken Popup");
			} else {
				reservationLogger.info("Satisfied Rules");
			}
			if (ReservationPage.Verify_Dirty_Room_popup.isDisplayed()) {
				ReservationPage.Confirm_button.click();
				reservationLogger.info("Clicked on confirm button of DirtyRoom Popup");
			} else {
				reservationLogger.info("No Dirty Rooms");
			}
		} catch (Exception e) {

		}
		Wait.wait10Second();
		try {
			if (ReservationPage.Payment_Popup.isDisplayed()) {
				reservationLogger.info("This Reservation has checkin ploicy, Payment popup is displayed");
				checkinpolicy = true;

			}
		} catch (Exception e) {
			reservationLogger.info("Checkin Policy doesn't exist");
		}

		// if (checkinpolicy == false) {
		// reservationLogger
		// .info("Trying to Clicking on Confirm button of Guest Registration
		// Form in Reservation.java");
		// // Thread.sleep(10000);
		// Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_on_confirm);
		// ReservationPage.Click_on_confirm.click();
		// Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message);
		// Wait.wait3Second();
		// }

		// try {
		// if (ReservationPage.Key_Generation_Popup.isDisplayed()) {
		// ReservationPage.Key_Generation_Close.click();
		// Wait.wait15Second();
		// }
		// } catch (Exception e) {
		// reservationLogger.info("Key Geneartion doesnt exist");
		// }
		// checkinpolicy = false;
		// assertTrue(ReservationPage.Payment_Popup.isDisplayed(), "Failed:
		// Payment Popup not displayed");
		// Elements_Reservation ReservationFolio = new
		// Elements_Reservation(driver);
		// try {
		// new
		// Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(PaymentType);
		// new
		// Select(ReservationFolio.Select_Authorization_type).selectByVisibleText(Authorizationtype);
		//
		// Wait.wait3Second();
		// assertTrue(new
		// Select(ReservationFolio.Select_Authorization_type).getFirstSelectedOption().getText()
		// .contains("Authorization"), "Failed : Authorization is not
		// selected");
		// ReservationFolio.Click_Card_info.click();
		// Utility.app_logs.info("Clicked on card info");
		// Wait.explicit_wait_xpath(OR.Verify_payment_info_popup);
		// Wait.wait3Second();
		// ReservationFolio.Enter_Card_Name.sendKeys(CardName);
		// ReservationFolio.Enter_Account_Number_Folio.clear();
		// ReservationFolio.Enter_Account_Number_Folio.sendKeys(CCNumber);
		// ReservationFolio.Enter_CC_Expiry.clear();
		// ReservationFolio.Enter_CC_Expiry.sendKeys(CCExpiry);
		// ReservationFolio.Enter_CVVCode.sendKeys(CCVCode);
		// ReservationFolio.Click_OK.click();
		// Utility.app_logs.info("Clicked on OK button");
		// Wait.wait10Second();
		// if (ChangeAmount.equalsIgnoreCase("Yes")) {
		// ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL,
		// "a"), ChangeAmountValue);
		// } else {
		// Utility.app_logs.info("Processing the amount displayed");
		// }
		Wait.explicit_wait_xpath(OR.Click_Process, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {

			ReservationFolio.Click_Process.click();
		} catch (Exception e) {

			jse.executeScript("arguments[0].click();", ReservationPage.Click_Process);
		}

		Utility.app_logs.info("Clicked on Process Button");
		test_steps.add("Click Process Button");
		Thread.sleep(1000);
		Wait.wait3Second();
		Wait.explicit_wait_xpath(OR.Verify_MC_Grid, driver);
		String GetPaymentMethod = ReservationFolio.GetMC_Payment_Method.getText();
		Utility.app_logs.info("PaymentMethod: " + " " + GetPaymentMethod);
		if (GetPaymentMethod.equals(PaymentType)) {
			Utility.app_logs.info("Paymnet is successful");
		} else {
			Utility.app_logs.info("Paymnet is Failed");
		}

		Wait.wait1Second();
		Utility.ScrollToElement(ReservationFolio.Processed_Amount_In_Paymentdetails_Popup, driver);
		Wait.wait2Second();
		String Processed_Amount = ReservationFolio.Processed_Amount_In_Paymentdetails_Popup.getText();
		Utility.app_logs.info("Processed_Amount " + Processed_Amount + " -- " + Processed_Amount);

		ReservationFolio.Click_Continue.click();
		Utility.app_logs.info("Clicked on continue button");
		test_steps.add("Click Continue");
		Wait.wait3Second();
		Wait.explicit_wait_xpath(OR.GetAddedLine_MC, driver);
		String GetMCCard = ReservationFolio.GetAddedLine_MC.getText();
		Utility.app_logs.info("Transaction Line Item: " + GetMCCard);
		if (GetMCCard.equalsIgnoreCase("Name: MC Account #: XXXX5454 Exp. Date: 08/21")) {
			Utility.app_logs.info("Payment is successful");
			test_steps.add("Payment is successful");
		} else {
			Utility.app_logs.info("Payment is Failed");
			test_steps.add("Payment is Failed");
		}

		return test_steps;
	}

	public void SaveRes_CheckDepositPolicyWithContinueButton(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationSaveButton, driver);
		Utility.ScrollToElement(Elements_Reservations.ReservationSaveButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Elements_Reservations.ReservationSaveButton);

		reservationLogger.info("Click on save button");

		try {
			Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(OR.Toaster_Message_Xpath)),
					driver);
			WebElement Message = driver.findElement(By.xpath(OR.Toaster_Message_Xpath));
			System.out.println("Toaster Message appear : " + Message.getText());
			Message.click();
		} catch (Exception e) {
			boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;

			if (size) {
				// Continue is on index 1
				jse.executeScript("arguments[0].click();", Elements_Reservations.CancelDepositePolicy_Button.get(1));
				// Elements_Reservations.CancelDepositePolicy_Button.get(3).click();

			}
			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(OR.Toaster_Message_Xpath)),
					driver);
			WebElement Message = driver.findElement(By.xpath(OR.Toaster_Message_Xpath));
			System.out.println("Toaster Message appear : " + Message.getText());
			Message.click();
			Wait.wait1Second();
		}
	}

	public void SaveRes_CheckDepositPolicyWithCancelButton(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationSaveButton, driver);
		Utility.ScrollToElement(Elements_Reservations.ReservationSaveButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Elements_Reservations.ReservationSaveButton);

		reservationLogger.info("Click on save button");

		try {
			Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(OR.Toaster_Message_Xpath)),
					driver);
			WebElement Message = driver.findElement(By.xpath(OR.Toaster_Message_Xpath));
			System.out.println("Toaster Message appear : " + Message.getText());
			Message.click();
		} catch (Exception e) {
			boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;

			if (size) {
				// Cancel is on index 0
				jse.executeScript("arguments[0].click();", Elements_Reservations.CancelDepositePolicy_Button.get(0));

			}
			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(OR.CheckIn_Button)), driver);

		}
	}

	// Func That got deleted or removed . adding from backup-data
	public String AssignRoom_IfNotAvailable_ChangeRoom(WebDriver driver, ExtentTest test, String RoomClass)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// Wait.explicit_wait_xpath(OR.RoomAssignmentButton);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignmentButton);
		// ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		// Wait.explicit_wait_visibilityof_webelement(ReservationPage.RoomAssignment_DateIcon);
		Utility.ElementFinderUntillNotShow(By.xpath(OR.ClickSearchRoomButton), driver);
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignment_DateIcon);
		jse.executeScript("arguments[0].click();", ReservationPage.SelectDate);
		if (!ReservationPage.RoomAssign_Check.isEnabled())
			jse.executeScript("arguments[0].click();", ReservationPage.RoomAssign_Check);
		jse.executeScript("arguments[0].click();", ReservationPage.SearchRoomButton);

		// ReservationPage.RoomAssignment_DateIcon.click();
		// ReservationPage.SelectDate.click();
		// ReservationPage.RoomAssign_Check.click();
		// ReservationPage.SearchRoomButton.click();
		// Wait.wait2Second();
		System.out.println("ROomClass:" + RoomClass);
		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.SelectRoomClasses, driver);
		try {
			new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
		} catch (Exception e) {
			reservationLogger.info("Room Class: " + RoomClass + " Not Available");
			new Select(ReservationPage.SelectRoomClasses).selectByIndex(1);
			RoomClass = new Select(ReservationPage.SelectRoomClasses).getFirstSelectedOption().getText();
		}
		reservationLogger.info("Select Room Class: " + RoomClass);
		// Testing
		// new Select(ReservationPage.SelectRoomClasses).selectByIndex(1);

		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}
		new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);

		Utility.roomIndex++;

		// Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();

		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			try {
				ReservationPage.Continue.get(9).click();
				Wait.wait2Second();
			} catch (Exception e) {
				ReservationPage.Continue.get(13).click();
				Wait.wait2Second();
			}
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		reservationLogger.info(driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).getText());
		driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).click();
		return RoomClass;
	}

	public void VerifyTaxApplied(WebDriver driver, ExtentTest test) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// Wait.wait3Second();

		Wait.WaitForElement(driver, OR.Room_Charges);

		assertTrue(ReservationPage.TaxesAndServiceCharges.isDisplayed(), "Failed:Tax is not applied");
	}

	public void VerifyCreatedTax(WebDriver driver, String taxName, String taxLedgerAccount, String tax, String Total)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		String LineItemDescriptionPath = "(//tbody[contains(@data-bind,'FolioItems')]//span[text()='" + taxLedgerAccount
				+ "' and contains(@data-bind,'data.Category')]//parent::td//following-sibling::td)[1]/a[contains(@data-bind,'data.Description')]";
		Utility.app_logs.info(LineItemDescriptionPath);
		WebElement Description = driver.findElement(By.xpath(LineItemDescriptionPath));
		Wait.explicit_wait_visibilityof_webelement(Description, driver);
		Utility.ScrollToElement(Description, driver);
		try {
			Description.click();
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(Description, driver);
			Utility.ScrollToElement(Description, driver);
			Description.click();
		}
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Item_Details_Popup, driver);
		// Wait.explicit_wait_visibilityof_webelement(ReservationPage.Item_Details_Popup_Descriptions.get(0));
		Utility.ScrollToElement(ReservationPage.Item_Details_Popup_Descriptions.get(0), driver);
		boolean found = false;
		for (WebElement description : ReservationPage.Item_Details_Popup_Descriptions) {
			Utility.app_logs.info(description.getText());
			if (description.getText().equals(taxName)) {
				found = true;
				break;
			}
		}
		if (found) {
			Utility.app_logs.info("Created Tax : " + taxName + " is Applied On Line  Item");
			Utility.ScrollToElement(ReservationPage.Item_Details_Popup, driver);
			assertEquals(ReservationPage.Item_Details_Popup_Tax.getText().split(" ")[1], tax,
					"Failed : Tax missmatched");

			assertEquals(ReservationPage.Item_Details_Popup_TotalCharges.getText().split(" ")[1], Total,
					"Failed : Total Charges missmatched");

		} else {
			assertTrue(false, "Created Tax : " + taxName + " is not Applied On Line  Item");
		}
		Utility.ScrollToElement(ReservationPage.Item_Details_Popup_Cancel_Btn, driver);
		ReservationPage.Item_Details_Popup_Cancel_Btn.click();
		System.out.println("Cancel Item Detail Clicked");
	}

	public String GetResArrivalDate(WebDriver driver) throws InterruptedException {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationArrivalDate, driver);
		Utility.ScrollToElement(Elements_Reservations.ReservationArrivalDate, driver);
		return Elements_Reservations.ReservationArrivalDate.getText();
	}

	public boolean ShouldPopupAppear(WebDriver driver, List<String> Days) throws InterruptedException {
		String day = GetResArrivalDate(driver);
		boolean found = false;
		for (String d : Days) {
			if (day.contains(d)) {
				found = true;
			}
		}
		return found;
	}

	public void SaveRes_VerifyDepositPolicyWithSeason(WebDriver driver, boolean popup) throws InterruptedException {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationSaveButton, driver);
		Utility.ScrollToElement(Elements_Reservations.ReservationSaveButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Elements_Reservations.ReservationSaveButton);
		reservationLogger.info("Click on save button");

		if (popup) {
			Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.CancelDepositePolicy_Button.get(3),
					driver);
			jse.executeScript("arguments[0].click();", Elements_Reservations.CancelDepositePolicy_Button.get(3));

			try {
				Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(OR.Toaster_Message_Xpath)),
						driver);
				WebElement Message = driver.findElement(By.xpath(OR.Toaster_Message_Xpath));
				System.out.println("Toaster Message appear : " + Message.getText());
				Message.click();
				Wait.wait1Second();
			} catch (Exception e) {
				System.err.println("Toaster Message not Appear");
			}
		} else {
			try {
				Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(OR.Toaster_Message_Xpath)),
						driver);
				WebElement Message = driver.findElement(By.xpath(OR.Toaster_Message_Xpath));
				System.out.println("Toaster Message appear : " + Message.getText());
				Message.click();
				Wait.wait1Second();
			} catch (Exception e) {
				System.err.println("Toaster Message not Appear");
			}
		}
	}

	public void VerifyCreatedTax(WebDriver driver, String taxName, String taxLedgerAccount, boolean exist)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		String LineItemDescriptionPath = "(//tbody[contains(@data-bind,'FolioItems')]//span[text()='" + taxLedgerAccount
				+ "' and contains(@data-bind,'data.Category')]//parent::td//following-sibling::td)[1]/a[contains(@data-bind,'data.Description')]";
		WebElement Description = driver.findElement(By.xpath(LineItemDescriptionPath));
		Utility.ScrollToElement(Description, driver);
		Description.click();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Item_Details_Popup, driver);
		// Wait.explicit_wait_visibilityof_webelement(ReservationPage.Item_Details_Popup_Descriptions.get(0));
		Utility.ScrollToElement(ReservationPage.Item_Details_Popup_Descriptions.get(0), driver);
		boolean found = false;
		for (WebElement description : ReservationPage.Item_Details_Popup_Descriptions) {
			Utility.app_logs.info(description.getText());
			if (description.getText().equals(taxName)) {
				found = true;
				break;
			}
		}
		assertEquals(found, exist, "Failed: Tax verification");
		if (found) {
			Utility.app_logs.info("Created Tax : " + taxName + " is Applied On Line  Item");
		} else {
			Utility.app_logs.info("Created Tax : " + taxName + " is not Applied On Line  Item");
		}
		Utility.ScrollToElement(ReservationPage.Item_Details_Popup_Cancel_Btn, driver);
		ReservationPage.Item_Details_Popup_Cancel_Btn.click();
		System.out.println("Cancel Item Detail Clicked");

	}

	public ArrayList<String> VerifyLineitemTax(WebDriver driver, String Category, Double Amount,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// Wait.wait3Second();

		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		String taxPath = "(//table[contains(@class, 'trHeight25')]//span[contains(@data-bind,data.Category) and text()='"
				+ Category + "']//parent::td//following-sibling::td[@class='lineitem-tax']/span)[last()]";
		WebElement TaxAdded = driver.findElement(By.xpath(taxPath));
		// Wait.explicit_wait_visibilityof_webelement(TaxAdded, driver);

		String taxAndServiceCharges = TaxAdded.getText();
		String taxAndServiceCharges1 = TaxAdded.getAttribute("value");

		System.out.println("taxAndServiceCharges:" + taxAndServiceCharges1);

		test_steps.add("Tax is : " + taxAndServiceCharges);
		taxAndServiceCharges = taxAndServiceCharges.replace("$", "").trim();
		boolean taxAmount = false;
		double taxAndService = Double.parseDouble(taxAndServiceCharges);
		System.out.println("Amount:" + taxAndService);
		if (Double.parseDouble(taxAndServiceCharges) == Amount) {
			test_steps.add("Tax Verified");
			taxAmount = true;
		} else {
			test_steps.add("Failed: Tax Verified");
			reservationLogger.info("Failed: Tax Verified");
		}

		assertEquals(taxAmount, true, "Failed: Taxes arr not correct");
		return test_steps;
	}

	public ArrayList<String> VerifyLineitemTax_1(WebDriver driver, String Category, Double Amount,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// Wait.wait3Second();

		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		String taxPath = "(//table[contains(@class, 'trHeight25')]//span[contains(@data-bind,data.Category) and text()='"
				+ Category + "']//parent::td//following-sibling::td[@class='lineitem-tax']/span)[last()]";
		WebElement TaxAdded = driver.findElement(By.xpath(taxPath));
		// Wait.explicit_wait_visibilityof_webelement(TaxAdded, driver);

		String taxAndServiceCharges = TaxAdded.getText();
		String taxAndServiceCharges1 = TaxAdded.getAttribute("value");

		System.out.println("taxAndServiceCharges:" + taxAndServiceCharges1);

		test_steps.add("Tax is : " + taxAndServiceCharges);
		taxAndServiceCharges = taxAndServiceCharges.replace("$", "").trim();
		boolean taxAmount = false;
		double taxAndService = Double.parseDouble(taxAndServiceCharges);
		System.out.println("Amount:" + taxAndService);
		if (Double.parseDouble(taxAndServiceCharges) == Amount) {
			test_steps.add("Tax Verified");
			taxAmount = true;
		} else {
			test_steps.add("Failed: Tax Verified");
			reservationLogger.info("Failed: Tax Verified");
		}

		assertEquals(taxAmount, true, "Failed: Taxes arr not correct");
		return test_steps;
	}

	public void AddLineItem(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Add_LineItem, driver);
		Utility.ScrollToElement(ReservationPage.Add_LineItem, driver);
		ReservationPage.Add_LineItem.click();
		Wait.wait2Second();
		Utility.ScrollToElement(ReservationPage.Select_LineItem_Category, driver);
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Select_LineItem_Category, driver);
		new Select(ReservationPage.Select_LineItem_Category).selectByIndex(3);
		ReservationPage.Enter_LineItem_Amount.sendKeys("10");
		Wait.wait2Second();
		ReservationPage.Commit_LineItem.click();

	}

	public void EnterFirst_LastName_ContentForReserTab(WebDriver driver, String FirstName, String LastName) {

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		WebElement PopUp = driver.findElement(By.xpath("//h4[text()='Please select the content for the Reservation']"));
		WebElement ContinueButton = driver
				.findElement(By.xpath("(//button[contains(@data-bind,'click: continueClicked')])[2]"));
		if (PopUp.isDisplayed()) {
			ContinueButton.click();

		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", elements_Reservation.Enter_First_Name);

		elements_Reservation.Enter_First_Name.clear();
		elements_Reservation.Enter_Last_Name.clear();

		elements_Reservation.Enter_First_Name.sendKeys(FirstName);
		elements_Reservation.Enter_Last_Name.sendKeys(LastName);

	}

	public Double GetFolioBalance(WebDriver driver) throws InterruptedException {
		Elements_Reservation res = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.Reservation_FolioBalance, driver);
		Utility.ScrollToElement(res.Reservation_FolioBalance, driver);
		String Balance = res.Reservation_FolioBalance.getText();
		Balance = Balance.split(" ")[1];
		System.out.println(Balance);
		Double d = Double.parseDouble(Balance);
		reservationLogger.info("Folio Balance : " + d);
		return d;

	}

	public String IncreaseNumberOfNights(WebDriver driver, String NumberOfNights) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.RoomAssignmentButton, driver);
		ReservationPage.RoomAssignmentButton.click();
		reservationLogger.info("Click Room Assignment Button");
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);

		Wait.explicit_wait_absenceofelement(OR.RoomAssignment_ModuleLoading, driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.NightField, driver);
		System.out.println(ReservationPage.NightField.getAttribute("value"));
		System.out.println(ReservationPage.NightField.getText());

		String Nights = ReservationPage.NightField.getAttribute("value");
		NumberOfNights = Integer.toString(Integer.parseInt(NumberOfNights) + Integer.parseInt(Nights));
		ReservationPage.NightField.clear();
		ReservationPage.NightField.sendKeys(NumberOfNights);
		Wait.wait5Second();
		reservationLogger.info("Change Number of Nights");
		ReservationPage.ClickSearchRoomButton.click();
		reservationLogger.info("Click Search ");
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.SelectButton, driver);

		try {
			ReservationPage.SelectButton.click();

			reservationLogger.info("Click Select");
			Wait.wait3Second();

			if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
				ReservationPage.Click_Continue_RuleBroken_Popup.click();
				reservationLogger.info("Clicked on continue button of RulesBroken Popup");
			} else {
				reservationLogger.info("Satisfied Rules");
			}
			try {
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.RecalculateRoomCharges, driver);
				ReservationPage.RecalculateRoomCharges.click();
				ReservationPage.click_Select_Button_RoomChargesChanged.click();
			} catch (Exception e1) {
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.SelectButton, driver);
				Utility.ScrollToElement(ReservationPage.SelectButton, driver);
				ReservationPage.SelectButton.click();
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.RecalculateRoomCharges, driver);
				ReservationPage.RecalculateRoomCharges.click();
				ReservationPage.click_Select_Button_RoomChargesChanged.click();

			}
		} catch (Exception e) {
			// analyzeLog(driver);
		}
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		if (ReservationPage.Policy_Comparision_PopUp.isDisplayed()) {
			ReservationPage.Policy_Comparision_PopUp_Continue_Btn.click();
		} else {
			System.out.println("Policy Comparision Popup not displayed ");
		}
		return NumberOfNights;
	}

	public void Select_Account(WebDriver driver, String AccountName) throws InterruptedException {
		Elements_Reservation reserv = new Elements_Reservation(driver);
		reserv.Enter_Account.sendKeys(AccountName);
		String AccountPath = "//li[@class='ui-menu-item']//b[text()='" + AccountName + "']";
		Wait.explicit_wait_xpath(AccountPath, driver);
		WebElement Account = driver.findElement(By.xpath(AccountPath));
		Wait.explicit_wait_visibilityof_webelement_600(Account, driver);
		Account.click();
		Wait.wait1Second();
		reservationLogger.info("Account : " + AccountName);
		assertTrue(reserv.Enter_Account.getAttribute("value").contains(AccountName), "Account is not Selected");

		reserv.Copy_Picked_Account_Data_To_Reservation_ContinueButton.click();
		Wait.waitForElementToBeGone(driver, reserv.AccountLoadingToaster, 150);
	}

	public void SaveRes_CheckDepositPolicy_1(WebDriver driver) throws InterruptedException {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationSaveButton, driver);
		Utility.ScrollToElement(Elements_Reservations.ReservationSaveButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Elements_Reservations.ReservationSaveButton);

		reservationLogger.info("Click on save button");

		try {
			Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(OR.Toaster_Message_Xpath)),
					driver);
			WebElement Message = driver.findElement(By.xpath(OR.Toaster_Message_Xpath));
			String Text = Message.getText();
			System.out.println("Toaster Message appear : " + Text);
			Message.click();

			if (Text.contains(
					"The existing room is not available for the criteria you provided. Please either change the criteria, or check the ‘Split Room’ check box and try again.")) {
				reAssignRoom(driver);

			}
		} catch (Exception e) {
			boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;

			if (size) {
				jse.executeScript("arguments[0].click();", Elements_Reservations.CancelDepositePolicy_Button.get(3));
				// Elements_Reservations.CancelDepositePolicy_Button.get(3).click();

			}
		}
	}

	public void SplitReservation_1(WebDriver driver, ExtentTest test, String RoomClass, String NightStay,
			String SplitRoomClass) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.Check_Split_Rooms);
		reservationLogger.info("Checked Room Split checkbox");
		Wait.explicit_wait_elementToBeClickable(ReservationPage.SearchRoomButton_2, driver);
		jse.executeScript("arguments[0].click();", ReservationPage.SearchRoomButton_2);
		//
		for (int i = 0; i < Integer.valueOf(NightStay); i++) {

			if (i > 0) {
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRooms.get(i), driver);
				new Select(ReservationPage.SplitRooms.get(i)).selectByVisibleText(SplitRoomClass);
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRoomNumbers.get(i), driver);

				if (Utility.roomIndex == Utility.roomIndexEnd) {
					Utility.roomIndex = 1;
				}
				new Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(Utility.roomIndex);
				Utility.roomIndex++;
				// new
				// Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(1);
			} else {
				System.out.println("Index:" + i);
				ReservationPage.SelectRoomClasses.click();

				Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRooms.get(i), driver);
				new Select(ReservationPage.SplitRooms.get(i)).selectByVisibleText(RoomClass);
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRoomNumbers.get(i), driver);
				if (Utility.roomIndex == Utility.roomIndexEnd) {
					Utility.roomIndex = 1;
				}
				ReservationPage.SelectRoomNumbers.click();
				new Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(Utility.roomIndex);
				Utility.roomIndex++;
				// new
				// Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(1);
			}

		}

		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();

		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.wait1Second();
		ReservationPage.RoomCharger_SelectButton.get(1).click();
		Wait.wait2Second();
		try {
			if (ReservationPage.Policy_Comparision_PopUp.isDisplayed()) {
				ReservationPage.Policy_Comparision_PopUp_Continue_Btn.click();
			}
		} catch (Exception e) {
			System.out.println("Policy Comparision Popup not displayed ");
		}
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message_Xpath, driver);
		driver.findElement(By.xpath(OR.Toaster_Message_Xpath)).click();
	}

	public void Save_Rreservation(WebDriver driver) throws InterruptedException {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationSaveButton, driver);
		Utility.ScrollToElement(Elements_Reservations.ReservationSaveButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Elements_Reservations.ReservationSaveButton);

		reservationLogger.info("Click on save button");
		boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;

		if (size) {
			jse.executeScript("arguments[0].click();", Elements_Reservations.CancelDepositePolicy_Button.get(3));
			// Elements_Reservations.CancelDepositePolicy_Button.get(3).click();
			Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Toaster_Message, driver);
			Elements_Reservations.Toaster_Message.click();
			Wait.wait3Second();
		} else {

		}

	}

	public ArrayList<String> Apply_Delta(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation elements_Reservations = new Elements_Reservation(driver);

		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.GuestInfo_Tab, driver);
		elements_Reservations.GuestInfo_Tab.click();

		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.ReservationFolioBalance, driver);
		String beforeBalance = elements_Reservations.ReservationFolioBalance.getText();
		test_steps.add("Before Apply Delta folio balance : " + beforeBalance);

		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.Depart_Value.get(1), driver);
		String before_apply_delta = elements_Reservations.Depart_Value.get(1).getText();
		elements_Reservations.ExtendReservation_Button.click();

		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.RoomChargerPopup, driver);
		assertEquals(elements_Reservations.RoomChargerPopup.getText(), "Room Charges Changed",
				"Room charger popup deos not display");

		elements_Reservations.ApplyDeltaEnabled_RadioButton.click();
		elements_Reservations.Button_RoomChargerSelect.click();

		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		elements_Reservations.Toaster_Message.click();
		Wait.wait2Second();

		String after_apply_delta = elements_Reservations.Depart_Value.get(1).getText();
		assertNotEquals(after_apply_delta, before_apply_delta, "Date does not change after aplly delta");
		test_steps.add("Date has been changed after Apply Delta");

		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.ReservationFolioBalance, driver);
		String afterBalance = elements_Reservations.ReservationFolioBalance.getText();

		int size = elements_Reservations.List_LineItemAmount.size();
		assertEquals(size, 2, "Line item did not increase after Recalculate in guest info");
		Utility.ScrollToElement(elements_Reservations.List_LineItemAmount.get(size - 1), driver);
		String lineItem_Amount = elements_Reservations.List_LineItemAmount.get(size - 1).getText();
		System.out.println("Folio balance : " + lineItem_Amount);

		String[] str_FolioAmount = lineItem_Amount.split(" ");
		double folio_Balance = Double.parseDouble(str_FolioAmount[1]);

		String[] str1 = beforeBalance.split(" ");
		double beforeRec_Balance = Double.parseDouble(str1[1]);
		double ExpectedBalance = beforeRec_Balance + folio_Balance;

		String[] str2 = afterBalance.split(" ");
		double afterRec_Balance = Double.parseDouble(str2[1]);

		test_steps.add("After Apply Delta expected balance : " + ExpectedBalance);
		test_steps.add("After Apply Delta folio balance : " + afterBalance);

		assertEquals(afterRec_Balance, ExpectedBalance, "Folio balance is not changed after applly delta");
		test_steps.add("Verified folio balance before and after Apply Delta");
		return test_steps;

	}

	public String getRoomNumber(WebDriver driver) throws InterruptedException {
		Elements_Reservation elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.GetRoomClassNumber, driver);
		String RoomClass_Number = elements_Reservations.GetRoomClassNumber.getText();
		String[] str = RoomClass_Number.split(":");
		System.out.println("room number : " + str[1]);
		return str[1];

	}

	public void closeNewReservationTab(WebDriver driver) throws InterruptedException {
		Elements_Reservation elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.Close_ReservationTab, driver);
		Utility.ScrollToElement(elements_Reservations.Close_ReservationTab, driver);
		elements_Reservations.Close_ReservationTab.click();
		Wait.wait1Second();
		boolean isDisplay = elements_Reservations.ListAlertForTab.size() > 0;
		if (isDisplay) {
			elements_Reservations.AlertForTab_Yes_Btn.click();
			Wait.wait1Second();
			driver.navigate().refresh();

		}

	}

	public ArrayList<String> RoomAssign_NewlyCreatedClass(WebDriver driver, ExtentTest test, String RoomClass,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignmentButton);
		Utility.ElementFinderUntillNotShow(By.xpath(OR.ClickSearchRoomButton), driver);
		Wait.wait3Second();
		Wait.WaitForElement(driver, OR.NightField);

		ReservationPage.RoomAssignment_DateIcon.click();
		Wait.wait1Second();
		ReservationPage.SelectDate.click();

		Wait.wait2Second();
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}
		ReservationPage.SearchRoomButton.click();

		reservationLogger.info("RoomClass:" + RoomClass);
		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.SelectRoomClasses, driver);
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
		test_steps.add("Select room class: " + RoomClass);
		reservationLogger.info("Select room class: " + RoomClass);

		Select select = new Select(ReservationPage.SelectRoomNumbers);
		List<WebElement> options = select.getOptions();
		int size = options.size();
		System.out.println("total size : " + size);
		size = size - 1;
		new Select(ReservationPage.SelectRoomNumbers).selectByIndex(size);

		// Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();

		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			ReservationPage.RoleBroken_Continue.click();
		}

		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);

		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Toaster_Message, driver);
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		ReservationPage.Toaster_Message.click();
		return test_steps;
	}

	public ArrayList<String> isTaxApplied(WebDriver driver) {
		ArrayList<String> test_steps = new ArrayList<String>();

		String lineItemTax = driver.findElement(By.xpath("//tr/td[contains(@class,'lineitem-tax')]/span")).getText();
		// Assert.assertEquals(lineItemTax, "$ 0.00");
		reservationLogger.info("Tax Applied : " + lineItemTax);
		test_steps.add("Tax Applied : " + lineItemTax);
		return test_steps;
	}

	public ArrayList<String> TaxApplied(WebDriver driver) {
		ArrayList<String> test_steps = new ArrayList<String>();

		String lineItemTax = driver.findElement(By.xpath("//tr/td[contains(@class,'lineitem-tax')]/span")).getText();
		reservationLogger.info("Tax Applied : " + lineItemTax);
		test_steps.add("Tax Applied AT First LineItem : " + lineItemTax);
		return test_steps;
	}

	public ArrayList<String> TaxApplied_ExculdeExemptChecked(WebDriver driver) {
		ArrayList<String> test_steps = new ArrayList<String>();

		String lineItemTax = driver.findElement(By.xpath("(//tr/td[contains(@class,'lineitem-tax')]/span)[2]"))
				.getText();
		Assert.assertEquals(lineItemTax, "$ 0.00");
		reservationLogger.info("Tax-1 : " + lineItemTax);
		test_steps.add("Tax-1 Displayed When 'Tax Exempt When Exclude' Checkbox is checked is: " + lineItemTax);
		return test_steps;
	}

	public ArrayList<String> TaxApplied_ExculdeExemptUnChecked(WebDriver driver) {
		ArrayList<String> test_steps = new ArrayList<String>();

		String lineItemTax = driver.findElement(By.xpath("(//tr/td[contains(@class,'lineitem-tax')]/span)[3]"))
				.getText();
		lineItemTax = lineItemTax.replace("$", " ");
		lineItemTax = lineItemTax.trim();
		float lineitmeTax = Float.parseFloat(lineItemTax);
		assertEquals(lineitmeTax > 0, true, "Tax is applied");
		String lineItemTaxExempt = String.valueOf(lineitmeTax);
		reservationLogger
				.info("Tax-2 Displayed When 'Tax Exempt When Exclude' Checkbox is checked is:  $ " + lineItemTaxExempt);
		test_steps
				.add("Tax-2 Displayed When 'Tax Exempt When Exclude' Checkbox is checked is:  $ " + lineItemTaxExempt);
		return test_steps;
	}

	public ArrayList<String> TaxExemptVerification(WebDriver driver) {
		ArrayList<String> test_steps = new ArrayList<String>();

		String lineItemTax = driver.findElement(By.xpath("//label[text()='Folio Balance:']//..//p//label")).getText();
		Assert.assertEquals(lineItemTax, "(TAX EXEMPT)");
		reservationLogger.info("Text displayed against folio balance is  : " + lineItemTax);
		test_steps.add("Text displayed against folio balance is : " + lineItemTax);
		return test_steps;
	}

	public void ClickAccountName(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.Get_Account_Name, driver);
		Utility.ScrollToElement(ReservationPage.Get_Account_Name, driver);
		ReservationPage.Get_Account_Name.click();
		Wait.wait2Second();
	}

	public ArrayList<String> SelectAssignedRoom(WebDriver driver, String RoomClass, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.Click_RoomPicker, driver);
		Utility.ScrollToElement(ReservationPage.Click_RoomPicker, driver);
		Thread.sleep(5000);
		ReservationPage.Click_RoomPicker.click();
		test_steps.add("Click on Rooms Picker");
		reservationLogger.info("Successfully clicked on Rooms Picker");
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.Click_Arrive_Datepicker, driver);
		test_steps.add("Room assignment popup appears");
		ReservationPage.Click_Arrive_Datepicker.click();
		reservationLogger.info("Successfully clicked on arrival date");
		ReservationPage.Click_Today.click();
		test_steps.add("Select Date: Today");
		reservationLogger.info("Successfully clicked on Today");
		Utility.ScrollToElement(ReservationPage.Check_Split_Rooms, driver);
		if (ReservationPage.Check_Split_Rooms.isSelected()) {
			ReservationPage.Check_Split_Rooms.click();
			test_steps.add("Click Split Room");
			reservationLogger.info("Click Split Room");
		}
		assertTrue(!ReservationPage.Check_Split_Rooms.isSelected());
		test_steps.add("Split Room: UnChecked");
		Utility.ScrollToElement(ReservationPage.Check_Assign_Room, driver);
		if (!ReservationPage.Check_Assign_Room.isSelected()) {
			ReservationPage.Check_Assign_Room.click();
			test_steps.add("Click Assign Room");
			reservationLogger.info("Click Assign Room");
		}
		assertTrue(ReservationPage.Check_Assign_Room.isSelected());
		test_steps.add("Assign Room: Checked");
		Utility.ScrollToElement(ReservationPage.Click_Search, driver);
		ReservationPage.Click_Search.click();
		test_steps.add("Click Search Rooms");
		reservationLogger.info("Click Search");
		reservationLogger.info("RoomClass:" + RoomClass);
		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.SelectRoomClasses, driver);
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
		Wait.wait1Second();
		String roomnum = "(//tbody//select[contains(@data-bind,'RoomId')])/option";
		System.out.println(roomnum);
		int count = driver.findElements(By.xpath(roomnum)).size();
		Random random = new Random();
		int randomNumber = random.nextInt(count - 1) + 1;
		System.out.println("count : " + count);
		System.out.println("randomNumber : " + randomNumber);
		new Select(driver.findElement(By.xpath("(//tbody//select[contains(@data-bind,'RoomId')])")))
				.selectByIndex(randomNumber);

		// if (Utility.roomIndex == Utility.roomIndexEnd) {
		// Utility.roomIndex = 1;
		// }
		// new
		// Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
		// Utility.roomIndex++;
		String RoomName = new Select(ReservationPage.SelectRoomClasses).getFirstSelectedOption().getText() + " : "
				+ new Select(ReservationPage.SelectRoomNumbers).getFirstSelectedOption().getText();
		test_steps.add("Select Room  " + RoomName);
		reservationLogger.info("Select Room " + RoomName);
		assertTrue(RoomName.contains(RoomClass), "Failed: Room Class is not " + RoomClass);
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		test_steps.add("Click Select Room");
		reservationLogger.info("Click Select");
		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			ReservationPage.RoleBroken_Continue.click();

		}
		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);

		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Toaster_Message, driver);
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		ReservationPage.Toaster_Message.click();
		return test_steps;
	}

	public void Res_OverBooking_Quote(WebDriver driver, String RoomClass) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		// TapeChart.NewQuote.click();
		Wait.explicit_wait_xpath(OR.New_Quote, driver);
		TapeChart.DatePickerIcon.get(5).click();
		Wait.explicit_wait_xpath(OR.New_Quote, driver);
		TapeChart.SelectDate.click();
		TapeChart.Quote_SearchButton.click();
		Wait.wait2Second();
		String BookButtonPath = "//a[text()='" + RoomClass + "']//..//..//following-sibling::td//button[@title='Book']";
		Utility.app_logs.info(BookButtonPath);
		WebElement BookButton = driver.findElement(By.xpath(BookButtonPath));
		Wait.explicit_wait_visibilityof_webelement(BookButton, driver);
		Utility.ScrollToElement(BookButton, driver);
		BookButton.click();
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(TapeChart.Over_BookingPopup, driver);
		assertEquals(TapeChart.Over_BookingPopup.getText(), "Room Overbooking", "Room Overbooking popup not appeared");

		String path = "There are 0 rooms available for the " + RoomClass
				+ ", Are you sure you want to overbook this room class?";
		String message = "//span[contains(text(),'There are 0 rooms available for the " + RoomClass
				+ ", Are you sure you want to overbook this room class?')]";
		WebElement element = driver.findElement(By.xpath(message));

		assertEquals(element.getText(), path, "over booking room message not appeared");

	}

	public void Res_OverBooking_CancelButton(WebDriver driver) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(TapeChart.OverBookingCancelBtn, driver);
		TapeChart.OverBookingCancelBtn.click();

	}

	public void OverBookingButton(WebDriver driver, String RoomClass) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);

		Wait.wait2Second();
		String BookButtonPath = "//a[text()='" + RoomClass + "']//..//..//following-sibling::td//button[@title='Book']";
		Utility.app_logs.info(BookButtonPath);
		WebElement BookButton = driver.findElement(By.xpath(BookButtonPath));
		Utility.ScrollToElement(BookButton, driver);
		BookButton.click();
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(TapeChart.Over_BookingPopup, driver);
		assertEquals(TapeChart.Over_BookingPopup.getText(), "Room Overbooking", "Room Overbooking popup not appeared");
		String path = "There are 0 rooms available for the " + RoomClass
				+ ", Are you sure you want to overbook this room class?";
		String message = "//span[contains(text(),'There are 0 rooms available for the " + RoomClass
				+ ", Are you sure you want to overbook this room class?')]";
		WebElement element = driver.findElement(By.xpath(message));

		assertEquals(element.getText(), path, "over booking room message not appeared");

	}

	public void Res_OverBooking_ContinueButton(WebDriver driver) throws InterruptedException {

		Elements_TapeChart TapeChart = new Elements_TapeChart(driver);
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(TapeChart.OverBookingContinueBtn, driver);
		TapeChart.OverBookingContinueBtn.click();
		Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
		Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);

	}

	public ArrayList<String> RoomAssignWithSplit(WebDriver driver, String ArriveDate, String RoomClass,
			String NightStay, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.RoomAssignmentButton.click();
		test_steps.add("Click on Rooms Picker");
		reservationLogger.info("Successfully clicked on Rooms Picker");
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Room_Assignment_PopUp, driver);
		test_steps.add("Room assignment popup appears");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// ReservationPage.RoomAssignment_DateIcon.click();
		// ReservationPage.SelectDate.click();
		// test_steps.add("Select Date");

		// Date Arrived
		Utility.ScrollToElement(ReservationPage.RoomAssignment_DateArrived, driver);
		ReservationPage.RoomAssignment_DateArrived.clear();
		ReservationPage.RoomAssignment_DateArrived.sendKeys(ArriveDate);
		test_steps.add("Enter Arrive Date " + ArriveDate);
		reservationLogger.info("Enter Arrive Date " + ArriveDate);

		ReservationPage.NightField.clear();
		ReservationPage.NightField.sendKeys(NightStay);

		test_steps.add("Enter Nights : " + NightStay);
		Utility.app_logs.info("Enter Nights : " + NightStay);
		Wait.wait1Second();
		if (!ReservationPage.Check_Split_Rooms.isSelected()) {
			ReservationPage.Check_Split_Rooms.click();
			test_steps.add("Click Split Room");
			reservationLogger.info("Click Split Room");
		}
		assertTrue(ReservationPage.Check_Split_Rooms.isSelected());
		test_steps.add("Split Room: Checked");
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
			test_steps.add("Click Assign Room");
			reservationLogger.info("Click Assign Room");
		}
		assertTrue(ReservationPage.Check_Assign_Room.isSelected());
		test_steps.add("Assign Room: Checked");
		reservationLogger.info("Assign Room: Checked");
		ReservationPage.SearchRoomButton.click();
		test_steps.add("Click Search: Room Assignment");
		reservationLogger.info("Click Search");
		String Previous_RoomNumber = "";
		ArrayList<String> RoomNumbers = new ArrayList<>();
		for (int i = 0; i < Integer.valueOf(NightStay); i++) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRooms.get(i), driver);
			new Select(ReservationPage.SplitRooms.get(i)).selectByVisibleText(RoomClass);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRoomNumbers.get(i), driver);

			if (Utility.roomIndex == Utility.roomIndexEnd) {
				Utility.roomIndex = 1;
			}
			new Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(Utility.roomIndex);
			Utility.roomIndex++;
			String RN = new Select(ReservationPage.SplitRoomNumbers.get(i)).getFirstSelectedOption().getText();
			if (Previous_RoomNumber.equals("")) {
				Previous_RoomNumber = RN;
			} else {
				if (Previous_RoomNumber.equals(RN)) {
					new Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(Utility.roomIndex);
					Utility.roomIndex++;
				}
				Previous_RoomNumber = new Select(ReservationPage.SplitRoomNumbers.get(i)).getFirstSelectedOption()
						.getText();
				reservationLogger.info("Previous Room Number : " + Previous_RoomNumber);
			}
			String RoomName = new Select(ReservationPage.SplitRooms.get(i)).getFirstSelectedOption().getText() + " : "
					+ new Select(ReservationPage.SplitRoomNumbers.get(i)).getFirstSelectedOption().getText();
			RoomNumbers.add(new Select(ReservationPage.SplitRoomNumbers.get(i)).getFirstSelectedOption().getText());
			test_steps.add("Select Room  " + RoomName);
			reservationLogger.info("Select Room " + RoomName);
			assertTrue(RoomName.contains(RoomClass), "Failed: Room Class is not the selected one");
		}

		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();

		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			ReservationPage.RoleBroken_Continue.click();
		}

		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);

		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Toaster_Message, driver);
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		ReservationPage.Toaster_Message.click();
		test_steps.addAll(RoomNumbers);
		return test_steps;
	}

	public ArrayList<String> SaveReservations(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException, IOException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.ReservationSaveButton);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationSaveButton, driver);
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.elementToBeClickable(Elements_Reservations.ReservationSaveButton));

		Utility.ScrollToElement(Elements_Reservations.ReservationSaveButton, driver);
		Wait.isElementDisplayed(driver, Elements_Reservations.ReservationSaveButton);
		Elements_Reservations.ReservationSaveButton.click();
		reservationLogger.info("Click on save button");
		test_steps.add("Click on save button");

		try {

			Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Toaster_Message, driver);
			String Text = Elements_Reservations.Toaster_Message.getText();
			System.out.println("Toaster Message appear : " + Text);

			Elements_Reservations.Toaster_Message.click();
			if (Text.contains(
					"The existing room is not available for the criteria you provided. Please either change the criteria, or check the ‘Split Room’ check box and try again.")) {
				reAssignRoom(driver);
				Elements_Reservations.Toaster_Message.click();

			}
			test_steps.add("Reservation" + Text);
		} catch (Exception e) {

			boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;
			System.out.println("Size:" + size);
			if (size) {
				Wait.wait1Second();
				System.out.println("Deposite Policy");
				Elements_Reservations.CancelDepositePolicy_Button.get(3).click();
				Wait.wait1Second();

			}
			try {
				Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Toaster_Message, driver);

				if (Elements_Reservations.Toaster_Title.isDisplayed()) {
					String getTotasterTitle_ReservationSucess = Elements_Reservations.Toaster_Title.getText();
					test_steps.add("Reservation" + Elements_Reservations.Toaster_Message.getText());
					Elements_Reservations.Toaster_Title.click();
					System.out.println(getTotasterTitle_ReservationSucess);
					if (getTotasterTitle_ReservationSucess.contains("Saved")) {
						Assert.assertEquals(getTotasterTitle_ReservationSucess, "Reservation Saved");

					} else if (getTotasterTitle_ReservationSucess.equals("Error while saving.")) {
						reAssignRoom(driver);
						PolicyPopup(driver);
					}

				}
			} catch (Exception e2) {
				reservationLogger.info("in finally");
				String reservationNumber = GetReservationnumber(driver);
				CloseOpenedReservation(driver);
				driver.navigate().refresh();
				ReservationSearch reservationSearch = new ReservationSearch();
				reservationSearch.basicSearch_WithResNumber(driver, reservationNumber, true);
				Wait.wait2Second();
				Wait.WaitForElement(driver, OR.FolioTab_Reservation);
				Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.FolioTab_Reservation, driver);
				Elements_Reservations.FolioTab_Reservation.click();
				reservationLogger.info("after folio");
				test_steps.add(reservationNumber);
			}

		}
		Wait.wait2Second();
		return test_steps;

	}

	public ArrayList<String> SaveReservations_CreatedFromTapeChart(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException, IOException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationSaveButton, driver);
		Utility.ScrollToElement(Elements_Reservations.ReservationSaveButton, driver);

		Wait.isElementDisplayed(driver, Elements_Reservations.ReservationSaveButton);
		Elements_Reservations.ReservationSaveButton.click();
		reservationLogger.info("Click on save button");
		test_steps.add("Click on save button");

		try {

			Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Toaster_Message, driver);
			String Text = Elements_Reservations.Toaster_Message.getText();
			System.out.println("Toaster Message appear : " + Text);

			Elements_Reservations.Toaster_Message.click();
			if (Text.contains(
					"The existing room is not available for the criteria you provided. Please either change the criteria, or check the ‘Split Room’ check box and try again.")) {
				reAssignRoom(driver);
				Elements_Reservations.Toaster_Message.click();

			}
			test_steps.add("Reservation" + Text);
		} catch (Exception e) {

			boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;
			System.out.println("Size:" + size);
			if (size) {
				Wait.wait1Second();
				System.out.println("Deposite Policy");
				Elements_Reservations.CancelDepositePolicy_Button.get(3).click();
				Wait.wait1Second();

			}
			try {
				Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Toaster_Message, driver);

				if (Elements_Reservations.Toaster_Title.isDisplayed()) {
					String getTotasterTitle_ReservationSucess = Elements_Reservations.Toaster_Title.getText();
					test_steps.add("Reservation" + Elements_Reservations.Toaster_Message.getText());
					Elements_Reservations.Toaster_Title.click();
					System.out.println(getTotasterTitle_ReservationSucess);
					if (getTotasterTitle_ReservationSucess.contains("Saved")) {
						Assert.assertEquals(getTotasterTitle_ReservationSucess, "Reservation Saved");

					} else if (getTotasterTitle_ReservationSucess.equals("Error while saving.")) {
						reAssignRoom(driver);
						PolicyPopup(driver);
					}

				}
			} catch (Exception e2) {
				reservationLogger.info("in finally");
				String reservationNumber = GetReservationnumber(driver);
				CloseOpenedReservation(driver);
				Navigation nav = new Navigation();
				nav.navigateToReservations(driver);
				reservationLogger.info("Navigate Reservation");
				// driver.navigate().refresh();
				ReservationSearch reservationSearch = new ReservationSearch();
				reservationSearch.basicSearch_WithResNumber(driver, reservationNumber, true);
				Wait.wait2Second();
				Wait.WaitForElement(driver, OR.FolioTab_Reservation);
				Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.FolioTab_Reservation, driver);
				Elements_Reservations.FolioTab_Reservation.click();
				reservationLogger.info("after folio");
				test_steps.add(reservationNumber);
			}

		}
		Wait.wait3Second();
		return test_steps;

	}

	public ArrayList<String> SaveReservations(WebDriver driver, String RoomClass, ArrayList<String> test_steps)
			throws InterruptedException, IOException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationSaveButton, driver);
		Utility.ScrollToElement(Elements_Reservations.ReservationSaveButton, driver);

		Wait.isElementDisplayed(driver, Elements_Reservations.ReservationSaveButton);
		Elements_Reservations.ReservationSaveButton.click();
		reservationLogger.info("Click on save button");
		test_steps.add("Click on save button");

		try {

			Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Toaster_Message, driver);
			String Text = Elements_Reservations.Toaster_Message.getText();
			System.out.println("Toaster Message appear : " + Text);

			Elements_Reservations.Toaster_Message.click();
			if (Text.contains(
					"The existing room is not available for the criteria you provided. Please either change the criteria, or check the ‘Split Room’ check box and try again.")) {
				reAssignRoom(driver);
				Elements_Reservations.Toaster_Message.click();

			}
			test_steps.add("Reservation" + Text);
		} catch (Exception e) {

			boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;
			System.out.println("Size:" + size);
			if (size) {
				Wait.wait1Second();
				System.out.println("Deposite Policy");
				Elements_Reservations.CancelDepositePolicy_Button.get(3).click();
				Wait.wait1Second();

			}
			try {
				Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Toaster_Message, driver);

				if (Elements_Reservations.Toaster_Title.isDisplayed()) {
					String getTotasterTitle_ReservationSucess = Elements_Reservations.Toaster_Title.getText();
					test_steps.add("Reservation" + Elements_Reservations.Toaster_Message.getText());
					Elements_Reservations.Toaster_Title.click();
					System.out.println(getTotasterTitle_ReservationSucess);
					if (getTotasterTitle_ReservationSucess.contains("Saved")) {
						Assert.assertEquals(getTotasterTitle_ReservationSucess, "Reservation Saved");

					} else if (getTotasterTitle_ReservationSucess.equals("Error while saving.")) {
						reAssignRoom(driver, RoomClass);
						PolicyPopup(driver);
					}

				}
			} catch (Exception e2) {
				reservationLogger.info("in finally");
				String reservationNumber = GetReservationnumber(driver);
				CloseOpenedReservation(driver);
				driver.navigate().refresh();
				ReservationSearch reservationSearch = new ReservationSearch();
				reservationSearch.basicSearch_WithResNumber(driver, reservationNumber, true);
				Wait.wait2Second();
				Wait.WaitForElement(driver, OR.FolioTab_Reservation);
				Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.FolioTab_Reservation, driver);
				Elements_Reservations.FolioTab_Reservation.click();
				reservationLogger.info("after folio");
				test_steps.add(reservationNumber);
			}

		}
		Wait.wait3Second();
		return test_steps;

	}

	public ArrayList<String> RoomAssign_MoreThen1Night(WebDriver driver, String RoomClass, String NightStay,
			ArrayList<String> test_steps) throws InterruptedException, ParseException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		ReservationPage.RoomAssignmentButton.click();
		test_steps.add("Click on room assignmnet picker button");

		Utility.ElementFinderUntillNotShow(By.xpath(OR.ClickSearchRoomButton), driver);

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String DF = dateFormat.format(date);

		Calendar c = Calendar.getInstance();
		c.setTime(dateFormat.parse(DF));

		int totalStay = Integer.parseInt(NightStay);
		totalStay = totalStay - 1;
		c.add(Calendar.DATE, -totalStay);
		String arrivalDate = dateFormat.format(c.getTime());

		c.add(Calendar.DATE, 0);
		String departDate = dateFormat.format(c.getTime());

		// c.add(Calendar.DATE, -3);
		// String arrivalDate = dateFormat.format(c.getTime());
		//
		// int totalStay = Integer.parseInt(NightStay);
		// totalStay = totalStay - 3;
		//
		// c.add(Calendar.DATE, +totalStay);
		// String departDate = dateFormat.format(c.getTime());

		Wait.wait2Second();
		ReservationPage.CheckIn_Out_Field.get(0).click();
		ReservationPage.CheckIn_Out_Field.get(0).sendKeys(arrivalDate);
		test_steps.add("Enter Arrvial date : " + arrivalDate);

		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.NightField, driver);
		ReservationPage.EnterPromocode.click();

		Wait.wait1Second();
		ReservationPage.CheckIn_Out_Field.get(1).click();
		ReservationPage.CheckIn_Out_Field.get(1).clear();
		ReservationPage.CheckIn_Out_Field.get(1).sendKeys(departDate);
		test_steps.add("Enter Depart date : " + departDate);

		ReservationPage.EnterPromocode.click();
		ReservationPage.NightField.click();
		ReservationPage.NightField.clear();
		ReservationPage.NightField.sendKeys(NightStay);
		Wait.wait1Second();
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}
		ReservationPage.SearchRoomButton.click();

		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}

		reservationLogger.info("RoomClass:" + RoomClass);
		for (int i = 0; i < Integer.parseInt(NightStay); i++) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.SelectRoomClasses, driver);
			new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
			test_steps.add("Select room class: " + RoomClass);
			reservationLogger.info("Select room class: " + RoomClass);

			new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
		}
		Utility.roomIndex++;
		test_steps.add("Click Select: Room Assignment");
		reservationLogger.info("Select Rooms");

		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		try {
			Wait.wait2Second();
			if (CheckRoom == 0 || CheckRoom < 0) {
				ReservationPage.Continue.get(9).click();
			}
			if (CheckRule.equals("1") || CheckRule.equals("2")) {
				ReservationPage.RoleBroken_Continue.click();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Toaster_Message, driver);
		ReservationPage.Toaster_Message.click();
		Wait.wait2Second();

		String Room = ReservationPage.ReservationRoomStatus.getText();
		System.out.println("RC:" + Room);
		// assertTrue(Room.contains("Multiple Rooms"), "Failed: RoomClass is not
		// Multiple Rooms");
		return test_steps;
	}

	public ArrayList<String> SelectUnassignedRoom(WebDriver driver, String RoomClass, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.Click_RoomPicker, driver);
		Utility.ScrollToElement(ReservationPage.Click_RoomPicker, driver);
		Thread.sleep(5000);
		ReservationPage.Click_RoomPicker.click();
		test_steps.add("Click on Rooms Picker");
		reservationLogger.info("Successfully clicked on Rooms Picker");
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Arrive_Datepicker, driver);
		test_steps.add("Room assignment popup appears");
		ReservationPage.Click_Arrive_Datepicker.click();
		reservationLogger.info("Successfully clicked on arrival date");
		ReservationPage.Click_Today.click();
		test_steps.add("Select Date: Today");
		reservationLogger.info("Successfully clicked on Today");
		Utility.ScrollToElement(ReservationPage.Check_Split_Rooms, driver);
		if (ReservationPage.Check_Split_Rooms.isSelected()) {
			ReservationPage.Check_Split_Rooms.click();
			test_steps.add("Click Split Room");
			reservationLogger.info("Click Split Room");
		}
		assertTrue(!ReservationPage.Check_Split_Rooms.isSelected());
		test_steps.add("Split Room: UnChecked");
		Utility.ScrollToElement(ReservationPage.Check_Assign_Room, driver);
		if (ReservationPage.Check_Assign_Room.isSelected()) {
			ReservationPage.Check_Assign_Room.click();
			test_steps.add("Click Assign Room");
			reservationLogger.info("Click Assign Room");
		}
		assertTrue(!ReservationPage.Check_Assign_Room.isSelected());
		test_steps.add("Assign Room: UnChecked");
		Utility.ScrollToElement(ReservationPage.Click_Search, driver);
		ReservationPage.Click_Search.click();
		test_steps.add("Click Search Rooms");
		reservationLogger.info("Click Search");
		reservationLogger.info("RoomClass:" + RoomClass);
		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.SelectRoomClasses, driver);
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
		test_steps.add("Select Room Class: " + RoomClass);
		reservationLogger.info("Select Room Class: " + RoomClass);
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		test_steps.add("Click Select Room");
		reservationLogger.info("Click Select");
		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			ReservationPage.RoleBroken_Continue.click();
		}

		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);

		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Toaster_Message, driver);
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		ReservationPage.Toaster_Message.click();
		return test_steps;
	}

	public void ClickFolioTabs(WebDriver driver) throws InterruptedException, ParseException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.FolioTab_Reservation, driver);
		ReservationPage.FolioTab_Reservation.click();
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.Reservation_Folio_Options, driver);

	}

	public void VerifyFolioTaxItem(WebDriver driver, String NightStay) throws InterruptedException, ParseException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.FolioTaxItem.get(0), driver);
		Utility.ScrollToElement(ReservationPage.FolioTaxItem.get(0), driver);
		int TotalNight = Integer.parseInt(NightStay);

		for (int i = 0; i < TotalNight; i++) {
			Utility.ScrollToElement(ReservationPage.FolioTaxItem.get(i), driver);
			assertEquals(ReservationPage.FolioTaxItem.get(i).getText(), "$ 0.00", "Tax exempt is failed");
		}
	}

	public String get_RoomClassAndRoomNumber(WebDriver driver, ArrayList test_steps) {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.Get_RoomClass_Status);
		String roomClassNameAndfNumber = Elements_Reservations.Get_RoomClass_Status.getText();

		String[] roomclassDetails = roomClassNameAndfNumber.split(":");
		return roomclassDetails[1];
	}

	public void verify_unassigend(WebDriver driver, ArrayList test_steps, String room) {
		room = room.trim();
		if (room.equalsIgnoreCase("Unassigned")) {
			test_steps.add("Room Number is assigned : " + room);
		} else {
			test_steps.add("Room Number is unassigned");
		}
	}

	public void verify_assigned(WebDriver driver, ArrayList test_steps, String room) {
		room = room.trim();
		if (room.equalsIgnoreCase("Unassigned")) {
			test_steps.add("Room Number is assigned : " + room);
		} else {
			test_steps.add("Room Number is unassigned");
		}
	}

	public String get_FilioBalance(WebDriver driver, ExtentTest test) {

		String bal = "//label[.='Balance: ']//following-sibling::span[@class='pamt']";
		Wait.WaitForElement(driver, bal);
		return driver.findElement(By.xpath(bal)).getText();

	}

	public void verify_Payment(WebDriver driver, ArrayList test_steps, String before, String after) {

		before = before.replace("$", "");
		before = before.trim();

		before = before.replace("(", "");
		before = before.trim();

		before = before.replace(")", "");
		before = before.trim();

		Double bf = Double.parseDouble(before);

		after = after.replace("$", "");
		after = after.trim();

		after = after.replace("(", "");
		after = after.trim();

		after = after.replace(")", "");
		after = after.trim();
		Double af = Double.parseDouble(after);

		if (bf == af) {
			reservationLogger.info("Paymnet is unsuccessful");
			test_steps.add("Paymnet is failed");
		} else {
			reservationLogger.info("Paymnet is successful");
			test_steps.add("Paymnet is successful");
		}

	}

	public void verify_PaymentStatus(WebDriver driver, ArrayList test_steps, String before, String after) {

		before = before.replace("$", "");
		before = before.trim();
		Double bf = Double.parseDouble(before);

		after = after.replace("$", "");
		after = after.trim();
		Double af = Double.parseDouble(after);

		if (bf == af) {
			reservationLogger.info("Paymnet is unsuccessful");
			test_steps.add("Paymnet is failed");
		} else {
			reservationLogger.info("Paymnet is successful");
			test_steps.add("Paymnet is successful");
		}

	}

	public void get_PaymentOrderNumber(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		Wait.wait5Second();
		Wait.WaitForElement(driver, OR.GetAddedLine_MC);
		String GetMCCard = ReservationFolio.GetAddedLine_MC.getText();
		if (GetMCCard.contains("Name: MC Account #: XXXX5454 Exp. Date:")) {
			reservationLogger.info("Paymnet is successful");
		} else {
			reservationLogger.info("Paymnet is Failed");
			test_steps.add("Paymnet is failed");
		}
		Wait.wait10Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", ReservationFolio.GetAddedLine_MC);
		ReservationFolio.GetAddedLine_MC.click();
		Wait.wait5Second();
		String payment = "(//a[contains(text(),'Name: MC')])[2]";
		Wait.WaitForElement(driver, payment);
		driver.findElement(By.xpath(payment)).click();

		String orderNumber = "//label[contains(text(),'Order Number:')]/following-sibling::div/label";
		Wait.WaitForElement(driver, orderNumber);
		String order = driver.findElement(By.xpath(orderNumber)).getText();
		test_steps.add("Order Number: " + order);
	}

	public String guestName(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.getGuestName);
		nameGuest = ReservationPage.getGuestName.getText();
		reservationLogger.info("Guest name: " + nameGuest);
		return nameGuest;

	}

	public void getUnassignedRoomInBasicSearch(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.clickUnassigned);
		ReservationPage.clickUnassigned.click();
		Wait.WaitForElement(driver, OR.getUnsassignedResCount);
		unassignedResCountAfterCreatingRes = ReservationPage.getUnsassignedResCount.getText();
		reservationLogger.info(
				"Unassigned Reservation Count after creating the reservation: " + unassignedResCountAfterCreatingRes);
		test_steps.add(
				"Unassigned Reservation Count after creating the reservation: " + unassignedResCountAfterCreatingRes);
	}

	public void getRoomClassAfterSearch(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// Wait.WaitForElement(driver, OR.getRoomClassName);
		getRoomclassName_status = driver
				.findElement(By
						.xpath("//*[@id='StayInfo']//following-sibling::label[.='Room(s):']/following-sibling::div/p/span"))
				.getText();
		roomClass = getRoomclassName_status.replaceAll(" : Unassigned", "");
		reservationLogger.info("Unassigned Room Class: " + roomClass);
		test_steps.add("Unassigned Room Class: " + roomClass);
	}

	public void roomClassValidations(WebDriver driver, ArrayList test_steps) {
		Assert.assertEquals(roomClassNom, roomClass, "Validated Room Class for unassigned Reservation");
		test_steps.add("Validated Room Class for unassigned Reservation");
	}

	public void getUnassignedRoomClassName(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait5Second();
		getRoomclassName_status = driver
				.findElement(By
						.xpath("//*[@id='StayInfo']//following-sibling::label[.='Room(s):']/following-sibling::div/p/span"))
				.getText();
		roomClassNom = getRoomclassName_status.replaceAll(" : Unassigned", "");
		reservationLogger.info("Room Class Name: " + roomClassNom);
		test_steps.add("Room Class Name: " + roomClassNom);
	}

	public void getUnassignedReservationCount(WebDriver driver, ArrayList test_steps) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// ReservationPage.getUnsassignedResCount.click();
		unassignedResCount = ReservationPage.getUnsassignedResCount.getText();
		reservationLogger.info("Unassigned Reservation Count before creating the reservation: " + unassignedResCount);
		test_steps.add("Unassigned Reservation Count before creating the reservation: " + unassignedResCount);

	}

	public void clickNewReservationButton(WebDriver driver, ExtentTest test) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.New_Reservation_Button);
		ReservationPage.New_Reservation_Button.click();
		Wait.explicit_wait_xpath(driver, OR.New_Reservation_Tab);
		Wait.explicit_wait_xpath(driver, OR.New_Reservation_Page_Load);
		test.log(LogStatus.PASS, "Clicked on New Reservation button");
	}

	public void marketingInfo(WebDriver driver, String MarketSegment, String Referral, String Travel_Agent,
			String ExtReservation) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		new Select(ReservationPage.Reservation_market_Segment).selectByVisibleText(MarketSegment);
		new Select(ReservationPage.Reservation_Referral).selectByVisibleText(Referral);
		try {
			ReservationPage.Enter_Travel_Agent.sendKeys(Travel_Agent);
		} catch (Exception e) {

		}
		ReservationPage.Enter_Ext_Reservation.sendKeys(ExtReservation);
	}

	public void uncheckGuestProfile(WebDriver driver) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", ReservationPage.GuestProfileCheckbox);
		Wait.WaitForElement(driver, OR.GuestProfileCheckbox);
		if (ReservationPage.GuestProfileCheckbox.isSelected()) {
			ReservationPage.GuestProfileCheckbox.click();
		}
	}

	public void contactInformation1(WebDriver driver, String saluation, String FirstName, String Address, String Line1,
			String Line2, String Line3, String City, String Country, String State, String Postalcode,
			String Phonenumber, String alternativenumber, String Email, String Account, String IsTaxExempt,
			String TaxEmptext) {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.WaitForElement(driver, OR.Reservation_CreateGuestProfile);
		if (ReservationPage.Reservation_CreateGuestProfile.isSelected()) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ReservationPage.Reservation_CreateGuestProfile);
			ReservationPage.Reservation_CreateGuestProfile.click();
		}

		new Select(ReservationPage.Select_Saluation).selectByVisibleText(saluation);
		ReservationPage.Enter_First_Name.clear();
		ReservationPage.Enter_Last_Name.clear();
		ReservationPage.Enter_Address_Search.clear();
		ReservationPage.Enter_Line1.clear();
		ReservationPage.Enter_Line2.clear();
		ReservationPage.Enter_Line3.clear();
		ReservationPage.Enter_City.clear();
		ReservationPage.Enter_Postal_Code.clear();
		;
		ReservationPage.Enter_Phone_Number.clear();
		;
		ReservationPage.Enter_Alt_Phn_number.clear();
		;
		ReservationPage.Enter_email.clear();
		;
		ReservationPage.Enter_First_Name.sendKeys(FirstName);

		LastName = new SimpleDateFormat("hh:mm:ss").format(new Date()).replaceAll("[-: ]", "");

		ReservationPage.Enter_Last_Name.sendKeys(LastName);
		ReservationPage.Enter_Address_Search.sendKeys(Address);
		ReservationPage.Enter_Line1.sendKeys(Line1);
		ReservationPage.Enter_Line2.sendKeys(Line2);
		ReservationPage.Enter_Line3.sendKeys(Line3);
		ReservationPage.Enter_City.sendKeys(City);
		new Select(ReservationPage.Select_Country).selectByVisibleText(Country);
		new Select(ReservationPage.Select_State).selectByVisibleText(State);
		ReservationPage.Enter_Postal_Code.sendKeys(Postalcode);
		ReservationPage.Enter_Phone_Number.sendKeys(Phonenumber);
		ReservationPage.Enter_Alt_Phn_number.sendKeys(alternativenumber);
		ReservationPage.Enter_email.sendKeys(Email);
		try {
			ReservationPage.Enter_Account.sendKeys(Account);
		} catch (Exception e) {

		}
		if (IsTaxExempt.equals("Yes")) {
			if (ReservationPage.Check_IsTaxExempt.isSelected()) {
				ReservationPage.Enter_TaxExemptId.sendKeys(TaxEmptext);
			} else {
				ReservationPage.Check_IsTaxExempt.click();
				ReservationPage.Enter_TaxExemptId.sendKeys(TaxEmptext);
			}
		}
	}

	public void contactInformation1(WebDriver driver, String saluation, String FirstName, String LastName,
			String Address, String Line1, String Line2, String Line3, String City, String Country, String State,
			String Postalcode, String Phonenumber, String alternativenumber, String Email, String Account,
			String IsTaxExempt, String TaxEmptext) {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		new Select(ReservationPage.Select_Saluation).selectByVisibleText(saluation);
		ReservationPage.Enter_First_Name.clear();
		ReservationPage.Enter_Last_Name.clear();
		ReservationPage.Enter_Address_Search.clear();
		ReservationPage.Enter_Line1.clear();
		ReservationPage.Enter_Line2.clear();
		ReservationPage.Enter_Line3.clear();
		ReservationPage.Enter_City.clear();
		ReservationPage.Enter_Postal_Code.clear();
		;
		ReservationPage.Enter_Phone_Number.clear();
		;
		ReservationPage.Enter_Alt_Phn_number.clear();
		;
		ReservationPage.Enter_email.clear();
		;
		ReservationPage.Enter_First_Name.sendKeys(FirstName);

		lastName = new SimpleDateFormat("hh:mm:ss").format(new Date()).replaceAll("[-: ]", "");

		ReservationPage.Enter_Last_Name.sendKeys(lastName);
		ReservationPage.Enter_Address_Search.sendKeys(Address);
		ReservationPage.Enter_Line1.sendKeys(Line1);
		ReservationPage.Enter_Line2.sendKeys(Line2);
		ReservationPage.Enter_Line3.sendKeys(Line3);
		ReservationPage.Enter_City.sendKeys(City);
		new Select(ReservationPage.Select_Country).selectByVisibleText(Country);
		new Select(ReservationPage.Select_State).selectByVisibleText(State);
		ReservationPage.Enter_Postal_Code.sendKeys(Postalcode);
		ReservationPage.Enter_Phone_Number.sendKeys(Phonenumber);
		ReservationPage.Enter_Alt_Phn_number.sendKeys(alternativenumber);
		ReservationPage.Enter_email.sendKeys(Email);
		try {
			ReservationPage.Enter_Account.sendKeys(Account);
		} catch (Exception e) {

		}
		if (IsTaxExempt.equals("Yes")) {
			if (ReservationPage.Check_IsTaxExempt.isSelected()) {
				ReservationPage.Enter_TaxExemptId.sendKeys(TaxEmptext);
			} else {
				ReservationPage.Check_IsTaxExempt.click();
				ReservationPage.Enter_TaxExemptId.sendKeys(TaxEmptext);
			}
		}
	}

	public String guestName(WebDriver driver, ExtentTest test) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.getGuestName);
		nameGuest = ReservationPage.getGuestName.getText();
		reservationLogger.info("Guest name: " + nameGuest);
		Wait.wait5Second();
		return nameGuest;
	}

	public void getGuestNameInSearchResult(WebDriver driver, ExtentTest test) {
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		String guestNameInResult = "(//a[.='" + Reservation.nameGuest + "'])[1]";

		String guestnameInResult = driver.findElement(By.xpath(guestNameInResult)).getText();

		reservationLogger.info("Displayed Guest Name after Search: " + guestnameInResult);

		if (Reservation.nameGuest.equalsIgnoreCase(guestnameInResult)) {

			reservationLogger.info("Guest Name is Same");

		}

		else {

			reservationLogger.info("Guest Name is not Same");
		}
	}

	public void verify_LineItemPosted(WebDriver driver, ArrayList<String> test_steps) {
		String lineitems = "//td[@class='text-center lineitems-changestatus']/img";
		Wait.WaitForElement(driver, lineitems);
		int count = driver.findElements(By.xpath(lineitems)).size();
		String item, itemCategory, attribute;
		for (int i = 1; i <= count; i++) {
			item = "(//td[@class='text-center lineitems-changestatus'])[" + i + "]/following-sibling::td[5]";
			Wait.WaitForElement(driver, item);
			itemCategory = driver.findElement(By.xpath(item)).getText();
			lineitems = "(//td[@class='text-center lineitems-changestatus']/img)[" + i + "]";
			attribute = driver.findElement(By.xpath(lineitems)).getAttribute("src");
			if (attribute.contains("/1.gif")) {
				test_steps.add("item " + itemCategory + " not posted");
			} else if (attribute.contains("/2.gif") || attribute.contains("/8.gif")) {
				test_steps.add("item " + itemCategory + " posted");
			}
		}
	}

	public void Checkin(WebDriver driver, ArrayList test_steps, String PropertyName, String RoomClassName,
			String CheckorUncheckAssign, String PaymentType, String CardName, String CCNumber, String CCExpiry,
			String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue, String traceData)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		try {
			Wait.WaitForElement(driver, OR.Click_Checkin);
			ReservationPage.Click_Checkin.click();
			Wait.wait5Second();
			reservationLogger.info("Clicked on CheckIn button");
			test_steps.add("Clicked on CheckIn button");
		} catch (Exception e) {
			test_steps.add("Failed to click on check-in button \n" + e.getMessage()
					+ "\n\n <br> Attaching screenshot below : \n"
					+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
							TestCore.test.getTest().getName() + "_Click_CheckinButton" + Utility.getTimeStamp(),
							driver)));
			reservationLogger.info("Failed to click on check-in button \n");
			e.printStackTrace();
		}
		Wait.WaitForElement(driver, OR.Room_Assignment_PopUp);
		Wait.explicit_wait_xpath(driver, OR.Room_Assignment_PopUp);
		// long Loadguestregform_StartTime = System.currentTimeMillis();
		try {
			Wait.wait5Second();
			Wait.WaitForElement(driver, OR.Click_Select);
			Wait.explicit_wait_elementToBeClickable(ReservationPage.Click_Select, driver);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Select, driver);
			Wait.explicit_wait_xpath(OR.Click_Select, driver);
			ReservationPage.Click_Select.click();
			// Wait.wait5Second();
			reservationLogger.info("Clicked on select button of room assignment popup");
			test_steps.add("Clicked on select button of room assignment popup");
			// Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup);
		} catch (Exception e) {
			e.printStackTrace();
			test_steps.add("Failed to click on check-in button \n" + e.getMessage()
					+ "\n\n <br> Attaching screenshot below : \n"
					+ TestCore.test.addScreenCapture(Utility.captureScreenShot(TestCore.test.getTest().getName()
							+ "_CheckIn_SelectButton_RoomAssignment" + Utility.getTimeStamp(), driver)));
			reservationLogger.info("Verification failed");
			reservationLogger.info("Failed to clicked on select button of room assignment popup while check-in \n");
			e.printStackTrace();
		}

		Wait.wait5Second();

		try {
			if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
				ReservationPage.Click_Continue_RuleBroken_Popup.click();
				reservationLogger.info("Clicked on continue button of RulesBroken Popup");
				test_steps.add("Clicked on continue button of RulesBroken Popup");
			} else {
				reservationLogger.info("Satisfied Rules");
				test_steps.add("Satisfied Rules");
			}
			if (ReservationPage.Verify_Dirty_Room_popup.isDisplayed()) {
				ReservationPage.Confirm_button.click();
				reservationLogger.info("Clicked on confirm button of DirtyRoom Popup");
				test_steps.add("Clicked on confirm button of DirtyRoom Popup");
				// Wait.wait5Second();
			} else {
				reservationLogger.info("No Dirty Rooms");
				test_steps.add("No Dirty Rooms");
			}
		} catch (Exception e) {

		}

		try {
			Wait.wait10Second();
			if (driver.findElements(By.xpath(OR.Payment_Popup)).size() > 0) {
				reservationLogger.info("Payment popup is displayed");
				ReservationFolio Payment = new ReservationFolio();
				Payment.TestPaymentPopup(driver, PaymentType, CardName, CCNumber, CCExpiry, CCVCode, Authorizationtype,
						ChangeAmount, ChangeAmountValue, traceData, test_steps);
				reservationLogger.info("Payment process is completed");
				test_steps.add("Payment process is completed");
			} else {
				Wait.waitUntilPresenceOfElementLocated(driver, OR.Click_Close_popUP);

				if (driver.findElements(By.xpath(OR.Click_Close_popUP)).size() > 1) {
					driver.findElement(By.xpath(OR.Click_Confirm)).click();
					reservationLogger.info("Clicked on Confirm button of Guest Statement Report");
					Wait.wait10Second();
				} else {
					driver.findElement(By.xpath(OR.Click_Close_popUP)).click();
					reservationLogger.info("Clicked on CLOSE button of Guest Statement Report");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			reservationLogger.info("Checkin Policy doesn't exist");
			test_steps.add("Checkin Policy doesn't exist");
		}

		// Wait.wait10Second();
		/*
		 * Wait.WaitForElement(driver, OR.Click_on_confirm);
		 * ReservationPage.Click_on_confirm.click(); reservationLogger.
		 * info("Clicked on Confirm button of Guest Registration Form");
		 * test.log(LogStatus.PASS,
		 * "Clicked on Confirm button of Guest Registration Form");
		 */
		try {
			if (ReservationPage.Key_Generation_Popup.isDisplayed()) {
				ReservationPage.Key_Generation_Close.click();
				Wait.wait15Second();
			}
		} catch (Exception e) {
			reservationLogger.info("Key Geneartion doesnt exist");
			test_steps.add("Key Geneartion doesnt exist");
		}
		Wait.wait5Second();
		String resStatus = "//label[contains(text(),'Reservation Status')]/following-sibling::div/select";
		Wait.WaitForElement(driver, resStatus);
		String status = new Select(driver.findElement(By.xpath(resStatus))).getFirstSelectedOption().getText();

		while (true) {
			if (!driver.findElement(By.xpath("//div[@id='ReservationDetail']//div[@class='modules_loading']"))
					.isDisplayed()) {
				break;
			} else {
				Wait.wait2Second();
			}
		}
		if (status.equalsIgnoreCase("In-House")) {
			reservationLogger.info("reservation checked in");
			test_steps.add("reservation checked in");
		} else {
			reservationLogger.info("reservation not checked in");
			test_steps.add("reservation not checked in");
		}

		assertEquals(status, "In-House");

	}

	public void checkout(WebDriver driver, ArrayList test_steps, String PaymentType, String CardName, String CCNumber,
			String CCExpiry, String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue,
			String traceData)

			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		try {
			Wait.wait5Second();
			Wait.WaitForElement(driver, OR.Click_Checkout);
			Wait.explicit_wait_xpath(OR.Click_Checkout, driver);
			ReservationPage.Click_Checkout.click();
			reservationLogger.info("Clicked on CHECKOUT button");
			test_steps.add("Click on CHECKOUT button");
			Wait.wait10Second();

			if (driver.findElement(By.xpath(OR.Resell_Rooms_Popup_Header)).isDisplayed()) {
				test_steps.add("Resell rooms pop up displayed");
				driver.findElement(By.xpath(OR.Resell_CancellAssociateRoomCharges)).click();
				test_steps.add("Click on Cancel Associated RoomCharges");
				driver.findElement(By.xpath(OR.Resell_Rooms_Popup_Continue_Button)).click();
				Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
				Wait.wait10Second();
				if (ReservationPage.Payment_Popup.isDisplayed()) {

					new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText("MC");
					Wait.wait3Second();
					ReservationFolio.Click_Card_info.click();
					Wait.explicit_wait_xpath(driver, OR.Verify_payment_info_popup);
					Wait.wait3Second();
					ReservationFolio.Enter_Card_Name.sendKeys(CardName);
					ReservationFolio.Enter_Account_Number_Folio.sendKeys(CCNumber);
					ReservationFolio.Enter_CC_Expiry.sendKeys(CCExpiry);
					ReservationFolio.Enter_CVVCode.sendKeys(CCVCode);
					ReservationFolio.Click_OK.click();
					Wait.wait10Second();
					Wait.WaitForElement(driver, OR.Select_Authorization_type);
					new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText(Authorizationtype);

					WebElement element = driver.findElement(
							By.xpath("//label[contains(text(),'Amount:')]/following-sibling::div/div/input"));

					element.sendKeys(Keys.chord(Keys.CONTROL, "a"));

					JavascriptExecutor js = (JavascriptExecutor) driver;
					String str = (String) js.executeScript("return arguments[0].value", element);
					str = str.replace("-", "");

					str = str.replace("$", "");
					element.sendKeys(str);
					Wait.wait15Second();
					reservationLogger.info("Before payment Process");
					Wait.WaitForElement(driver, OR.Click_Process);
					Wait.explicit_wait_xpath(OR.Click_Process, driver);
					Wait.explicit_wait_elementToBeClickable(ReservationFolio.Click_Process, driver);
					ReservationFolio.Click_Process.click();
					reservationLogger.info("After payment Process");
					Wait.wait10Second();
					Wait.WaitForElement(driver, OR.Verify_MC_Grid);
					Wait.explicit_wait_xpath(driver, OR.Verify_MC_Grid);
					String GetPaymentMethod = ReservationFolio.GetMC_Payment_Method.getText();

					ReservationFolio.Click_Continue.click();
					reservationLogger.info("After Continue");

					test_steps.add("Payment done successfully");
				}
			}

			Wait.wait10Second();

			if (ReservationPage.Payment_Popup.isDisplayed()) {
				reservationLogger.info("Payment Popup is displayed");
				ReservationFolio Payment = new ReservationFolio();
				Payment.TestPaymentPopup(driver, PaymentType, CardName, CCNumber, CCExpiry, CCVCode, Authorizationtype,
						ChangeAmount, ChangeAmountValue, traceData, test_steps);
				reservationLogger.info("Payment is processed");
			} else {
				if (driver.findElements(By.xpath(OR.Click_Close)).size() > 1) {
					driver.findElement(By.xpath(OR.Click_Confirm)).click();
					reservationLogger.info("Clicked on Confirm button of Guest Statement Report");
					test_steps.add("Clicked on Confirm button of Guest Statement Report");
				} else {
					driver.findElement(By.xpath(OR.Click_Close)).click();
					reservationLogger.info("Clicked on CLOSE button of Guest Statement Report");
					test_steps.add("Clicked on CLOSE button of Guest Statement Report");
				}
				reservationLogger.info("Payment Popup is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			test_steps.add("Checkout is Failed \n" + e.getMessage() + "\n\n <br> Attaching screenshot below : \n"
					+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
							TestCore.test.getTest().getName() + "_CheckOut" + Utility.getTimeStamp(), driver)));
			reservationLogger.info("Checkout is Failed \n");
			e.printStackTrace();
		}
		/*
		 * Wait.wait5Second();
		 * Wait.waitUntilPresenceOfElementLocated(OR.Click_Close);
		 * ReservationPage.Click_Close.click(); reservationLogger.
		 * info("Clicked on CLOSE button of Guest Statement Report");
		 */
		Wait.wait2Second();

		String resStatus = "//label[contains(text(),'Reservation Status')]/following-sibling::div/select";
		Wait.WaitForElement(driver, resStatus);
		String status = new Select(driver.findElement(By.xpath(resStatus))).getFirstSelectedOption().getText();
		if (status.equalsIgnoreCase("Departed")) {
			reservationLogger.info("reservation Departed ");
			test_steps.add("reservation Departed ");
		} else {
			reservationLogger.info("reservation not Departed");
			test_steps.add("reservation not Departed");
		}

		String checkout = "//p[contains(text(),'(Checked out on ')]";

		if (driver.findElements(By.xpath(checkout)).size() > 0) {
			test_steps.add("reservation early checked out");
		}
	}

	public int get_UnassignedCount(WebDriver driver, ExtentTest test) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.Reservation_Get_UnassignedCount);
		String text = ReservationPage.Reservation_Get_UnassignedCount.getText();
		text = text.trim();
		text = text.replace(" Unassigned", "");

		return Integer.parseInt(text);
	}

	public void contactInformation(WebDriver driver, String saluation, String FirstName, String LastName,
			String Address, String Line1, String Line2, String Line3, String City, String Country, String State,
			String Postalcode, String Phonenumber, String alternativenumber, String Email, String Account,
			String IsTaxExempt, String TaxEmptext) {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		try {
			Wait.WaitForElement(driver, OR.Reservation_CreateGuestProfile);
			if (ReservationPage.Reservation_CreateGuestProfile.isSelected()) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].scrollIntoView(true);", ReservationPage.Reservation_CreateGuestProfile);
				ReservationPage.Reservation_CreateGuestProfile.click();
			}

			new Select(ReservationPage.Select_Saluation).selectByVisibleText(saluation);
			ReservationPage.Enter_First_Name.clear();
			ReservationPage.Enter_Last_Name.clear();
			ReservationPage.Enter_Address_Search.clear();
			ReservationPage.Enter_Line1.clear();
			ReservationPage.Enter_Line2.clear();
			ReservationPage.Enter_Line3.clear();
			ReservationPage.Enter_City.clear();
			ReservationPage.Enter_Postal_Code.clear();
			ReservationPage.Enter_Phone_Number.clear();
			ReservationPage.Enter_Alt_Phn_number.clear();
			ReservationPage.Enter_email.clear();
			ReservationPage.Enter_First_Name.sendKeys(FirstName);
			ReservationPage.Enter_Last_Name.sendKeys(LastName);
			ReservationPage.Enter_Address_Search.sendKeys(Address);
			ReservationPage.Enter_Line1.sendKeys(Line1);
			ReservationPage.Enter_Line2.sendKeys(Line2);
			ReservationPage.Enter_Line3.sendKeys(Line3);
			ReservationPage.Enter_City.sendKeys(City);
			new Select(ReservationPage.Select_Country).selectByVisibleText(Country);
			new Select(ReservationPage.Select_State).selectByVisibleText(State);
			ReservationPage.Enter_Postal_Code.sendKeys(Postalcode);
			ReservationPage.Enter_Phone_Number.sendKeys(Phonenumber);
			ReservationPage.Enter_Alt_Phn_number.sendKeys(alternativenumber);
			ReservationPage.Enter_email.sendKeys(Email);
			reservationLogger.info("Entered required contact information");

			try {
				ReservationPage.Enter_Account.sendKeys(Account);
			} catch (Exception e) {

			}

			if (IsTaxExempt.equals("Yes")) {
				if (ReservationPage.Check_IsTaxExempt.isSelected()) {
					ReservationPage.Enter_TaxExemptId.sendKeys(TaxEmptext);
					reservationLogger.info("Entered TaxExcemptID");
				} else {
					ReservationPage.Check_IsTaxExempt.click();
					reservationLogger.info("Clicked on TaxExcempt checkbox");
					ReservationPage.Enter_TaxExemptId.sendKeys(TaxEmptext);
					reservationLogger.info("Entered TaxExcemptID");
				}
			}
		} catch (Exception e) {
			TestCore.test.log(LogStatus.FAIL, "Failed in contact Information \n" + e.getMessage()
					+ "\n\n <br> Attaching screenshot below : \n"
					+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
							TestCore.test.getTest().getName() + "_Reservation_ContactInfo" + Utility.getTimeStamp(),
							driver)));
			reservationLogger.info("Failed in contact Information \n");
			e.printStackTrace();
		}
	}

	public void verify_UnassignedReservation(WebDriver driver, ArrayList test_steps) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.Reservation_Unassigned);
		String text = ReservationPage.Reservation_Unassigned.getText();
		if (text.contains(": Unassigned")) {
			test_steps.add("Reservation is Unassigned");
			reservationLogger.info("Created Unassigned Reservation from Tapechart success");
		} else {
			test_steps.add("Reservation is assigned");
			reservationLogger.info("Created assigned Reservation from Tapechart success");
		}
	}

	public void verify_RoomMove(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait3Second();
		Wait.WaitForElement(driver, OR.RoomMoveTask);
		test_steps.add("Room Move Task Created");
		String roommove = "//td/span[contains(text(),'Room Move')]/../following-sibling::td/a";
		test_steps.add("Room  " + driver.findElement(By.xpath(roommove)).getText());
	}

	public void roomAssignmentWithDetails(WebDriver driver, String PropertyName, String Nights, String Adults,
			String Children, String RatepromoCode, String CheckorUncheckAssign, String RoomClassName, String RoomNumber)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		try {

			ReservationPage.Click_RoomPicker.click();
			reservationLogger.info("Clicked on RoomPicker button");
			Wait.explicit_wait_xpath(driver, OR.Room_Assignment_PopUp);
			Wait.wait3Second();
		} catch (Exception e) {
			TestCore.test.log(LogStatus.FAIL,
					"Failed to click on RoomPicker button \n" + e.getMessage()
							+ "\n\n <br> Attaching screenshot below : \n"
							+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
									TestCore.test.getTest().getName() + "_RoomPicker_Button" + Utility.getTimeStamp(),
									driver)));
			reservationLogger.info("Failed to click on RoomPicker button \n");
			e.printStackTrace();
		}

		/*
		 * try { new Select(ReservationPage.Select_property_RoomAssign).
		 * selectByVisibleText(PropertyName); } catch(Exception e) { new
		 * Select(ReservationPage.Select_property_RoomAssign2).
		 * selectByVisibleText(PropertyName); }
		 */

		Wait.wait3Second();
		ReservationPage.Click_Arrive_Datepicker.click();
		reservationLogger.info("Clicked on Arrival Datapicker");
		ReservationPage.Click_Today.click();
		reservationLogger.info("Selected today's date");

		if (Nights.equals("")) {
			ReservationPage.Enter_Nigts.sendKeys(Nights);
			reservationLogger.info("Entered No of Nights");
		} else {
			ReservationPage.Enter_Nigts.clear();
			ReservationPage.Enter_Nigts.sendKeys(Nights);
			reservationLogger.info("Entered No of Nights");
		}
		ReservationPage.Enter_Adults.sendKeys(Adults);
		reservationLogger.info("Entered No of Adults");
		ReservationPage.Enter_Children.sendKeys(Children);
		reservationLogger.info("Entered No of Children");
		ReservationPage.Enter_Rate_Promocode.sendKeys(RatepromoCode);
		reservationLogger.info("Entered Promocode");
		reservationLogger.info("Verifying AssignRooms checkbox is selected or not?");
		if (ReservationPage.Check_Assign_Room.isSelected()) {
			// ReservationPage.Check_Assign_Room.click();
			reservationLogger.info("AssignRooms checkbox is selected");
			ReservationPage.Click_Search.click();
			reservationLogger.info("Clicked on Search button");
		} else {
			reservationLogger.info(
					"AssignRooms checkbox is not selected. Hence, verifying test data value to select the AssignRooms checkbox");
			if (CheckorUncheckAssign.equals("Yes")) {
				ReservationPage.Check_Assign_Room.click();
				reservationLogger.info("AssignRooms checkbox is selected");
				ReservationPage.Click_Search.click();
				reservationLogger.info("Clicked on Search button");
			} else {
				ReservationPage.Click_Search.click();
				reservationLogger.info("Clicked on Search button");
			}
		}
		try {

			new Select(ReservationPage.Select_Room_Class).selectByVisibleText(RoomClassName);
			reservationLogger.info("RoomClass is selected");
			String selectedOption = new Select(ReservationPage.Validating_UnAssgined_DDL).getFirstSelectedOption()
					.getText();
			// System.out.println("selectedOption " + selectedOption);
			reservationLogger.info("RoomNumber selectedOption is:  " + selectedOption);
			if (selectedOption.equals("--Select--")) {
				// new
				// Select(ReservationPage.Select_Room_Number).selectByVisibleText(RoomNumber);

				Wait.wait1Second();
				String roomnum = "(//tbody//select[contains(@data-bind,'RoomId')])[1]/option";
				System.out.println(roomnum);
				int count = driver.findElements(By.xpath(roomnum)).size();
				Random random = new Random();
				int randomNumber = random.nextInt(count - 1) + 1;
				System.out.println("count : " + count);
				System.out.println("randomNumber : " + randomNumber);
				new Select(driver.findElement(By.xpath("(//tbody//select[contains(@data-bind,'RoomId')])")))
						.selectByIndex(randomNumber);
				// new
				// Select(ReservationPage.Select_Room_Number).selectByIndex(1);
				reservationLogger.info("Selected first available room from the list");
			} else {
				// System.out.println("Reservation is unassigned");
				reservationLogger.info("Reservation is unassigned");
			}
		} catch (Exception e) {
			Wait.explicit_wait_xpath(driver, OR.Validation_Text_NoRooms);
			TestCore.test.log(LogStatus.FAIL,
					"Room classes are not available for these dates \n" + e.getMessage()
							+ "\n\n <br> Attaching screenshot below : \n"
							+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
									TestCore.test.getTest().getName() + "_RoomClass_Rates" + Utility.getTimeStamp(),
									driver)));
			reservationLogger.info("Room classes are not available for these dates \n");
			e.printStackTrace();

		}

		try {

			ReservationPage.Click_Select.click();
			reservationLogger.info("Clicked on select button from Room Picker Popup");
		} catch (Exception e) {
			TestCore.test.log(LogStatus.FAIL,
					"Failed to click on select button from Room Picker Popup \n" + e.getMessage()
							+ "\n\n <br> Attaching screenshot below : \n"
							+ TestCore.test.addScreenCapture(Utility.captureScreenShot(TestCore.test.getTest().getName()
									+ "_Select-Button_RoomPicker_Popup" + Utility.getTimeStamp(), driver)));
			reservationLogger.info("Failed to click on select button from Room Picker Popup \n");
			e.printStackTrace();
		}

		try {
			Wait.wait2Second();
			Wait.explicit_wait_xpath(driver, OR.Verify_RulesBroken_Popup);
			reservationLogger.info("Waiting for RulesBroken popup to be displayed");
			// Wait.wait5Second();
			if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
				reservationLogger.info("RulesBroken popup is displayed");
				ReservationPage.Click_Continue_RuleBroken_Popup.click();
				reservationLogger.info("Clicked on RulesBroken popup continue button");
			} else {
				reservationLogger.info("Satisfied Rules hence, RulesBroken popup is not displayed");
			}
		} catch (Exception e) {
			TestCore.test.log(LogStatus.FAIL, "Exception while waiting for rulesBroken popup \n" + e.getMessage()
					+ "\n\n <br> Attaching screenshot below : \n"
					+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
							TestCore.test.getTest().getName() + "_Waiting_RulesBroken_Popup" + Utility.getTimeStamp(),
							driver)));
			reservationLogger.info("Exception while waiting for rulesBroken popup \n");
			e.printStackTrace();

		}

		try {
			Wait.WaitForElement(driver, OR.Verify_Toaster_Container);
			if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
				String getTotasterTitle = ReservationPage.Toaster_Title.getText();
				String getToastermessage = ReservationPage.Toaster_Message.getText();
				reservationLogger.info("RoomAssignment Toaster Title: " + getTotasterTitle);
				reservationLogger.info("RoomAssignment Toaster Message: " + getToastermessage);
				Assert.assertEquals(getTotasterTitle, "Room assignment has changed.");
				Assert.assertEquals(getToastermessage, "Room assignment has changed.");
				reservationLogger.info("Verified Room Assignment toaster title and message");
			}

			Wait.wait2Second();
			String getPropertyName = ReservationPage.Get_Property_Name.getText();
			String getRoomclassName_status = ReservationPage.Get_RoomClass_Status.getText();
			reservationLogger.info("Room Class Status:" + getRoomclassName_status);
			// Assert.assertEquals(getPropertyName, PropertyName);
			String getRoomclassName[] = getRoomclassName_status.split(" ");
			// Assert.assertEquals(getRoomclassName[0],RoomClassName );
			if (CheckorUncheckAssign.equals("Yes")) {

			} else {
				Assert.assertEquals(getRoomclassName[2], "Unassigned");
			}
		} catch (Exception e) {
			TestCore.test.log(LogStatus.FAIL,
					"Exception occurred while verifying toaster message \n" + e.getMessage()
							+ "\n\n <br> Attaching screenshot below : \n"
							+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
									TestCore.test.getTest().getName() + "_Waiting_Toaster" + Utility.getTimeStamp(),
									driver)));
			reservationLogger.info("Exception occurred while verifying toaster message \n");
			e.printStackTrace();
		}
	}

	public ArrayList<String> SplitReservation(WebDriver driver, ExtentTest test, String RoomClass, String NightStay,
			ArrayList<String> getTest_Steps) throws InterruptedException {

		return ChangeRoomToSplitAssigned(driver, test, RoomClass, NightStay, getTest_Steps);
	}

	public ArrayList<String> ChangeRoomToSplitAssigned(WebDriver driver, ExtentTest test, String RoomClass,
			String NightStay, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		ReservationPage.RoomAssignmentButton.click();
		test_steps.add("Click on Rooms Picker");
		reservationLogger.info("Successfully clicked on Rooms Picker");
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.NightField, driver);
		test_steps.add("Room assignment popup appears");
		Wait.wait2Second();
		Utility.ScrollToElement(ReservationPage.NightField, driver);
		ReservationPage.NightField.clear();
		ReservationPage.NightField.sendKeys(NightStay);
		test_steps.add("Enter Nights : " + NightStay);
		reservationLogger.info("Enter Nights : " + NightStay);
		if (!ReservationPage.Check_Split_Rooms.isSelected()) {
			ReservationPage.Check_Split_Rooms.click();
			test_steps.add("Click Split Room");
			reservationLogger.info("Click Split Room");
		}
		assertTrue(ReservationPage.Check_Split_Rooms.isSelected());
		test_steps.add("Split Room: Checked");

		if (!ReservationPage.Check_Assign_Room.isSelected()) {
			ReservationPage.Check_Assign_Room.click();
			test_steps.add("Click Assign Room");
			reservationLogger.info("Click Assign Room");
		}
		assertTrue(ReservationPage.Check_Assign_Room.isSelected());
		test_steps.add("Assign Room: Checked");
		Utility.ScrollToElement(ReservationPage.Click_Search, driver);
		ReservationPage.Click_Search.click();
		test_steps.add("Click Search Rooms");
		reservationLogger.info("Click Search Rooms");
		ArrayList<String> RoomNumbers = new ArrayList<>();
		String Previous_RoomNumber = "";
		for (int i = 0; i < Integer.valueOf(NightStay); i++) {

			Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRooms.get(i), driver);
			new Select(ReservationPage.SplitRooms.get(i)).selectByVisibleText(RoomClass);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRoomNumbers.get(i), driver);
			if (Utility.roomIndex == Utility.roomIndexEnd) {
				Utility.roomIndex = 1;
			}
			new Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(Utility.roomIndex);
			Utility.roomIndex++;
			String RN = new Select(ReservationPage.SplitRoomNumbers.get(i)).getFirstSelectedOption().getText();
			if (Previous_RoomNumber.equals("")) {
				Previous_RoomNumber = RN;
			} else {
				if (Previous_RoomNumber.equals(RN)) {
					if (Utility.roomIndex == Utility.roomIndexEnd) {
						Utility.roomIndex = 1;
					}
					new Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(Utility.roomIndex);
					Utility.roomIndex++;
				}
				Previous_RoomNumber = new Select(ReservationPage.SplitRoomNumbers.get(i)).getFirstSelectedOption()
						.getText();
				reservationLogger.info("Previous Room Number : " + Previous_RoomNumber);
			}
			String RoomName = new Select(ReservationPage.SplitRooms.get(i)).getFirstSelectedOption().getText() + " : "
					+ new Select(ReservationPage.SplitRoomNumbers.get(i)).getFirstSelectedOption().getText();
			RoomNumbers.add(new Select(ReservationPage.SplitRoomNumbers.get(i)).getFirstSelectedOption().getText());
			test_steps.add("Select Room  " + RoomName);
			reservationLogger.info("Select Room " + RoomName);
			assertTrue(RoomName.contains(RoomClass), "Failed: Room Class is not the selected one");
		}
		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		Utility.ScrollToElement(ReservationPage.SelectButton, driver);
		ReservationPage.SelectButton.click();
		test_steps.add("Click Select Room");
		reservationLogger.info("Click Select Room");
		Wait.wait2Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.RecalculateRoomCharges, driver);
		Utility.ScrollToElement(ReservationPage.RecalculateRoomCharges, driver);
		ReservationPage.RecalculateRoomCharges.click();
		test_steps.add("Click Recalculate Room Charges");
		reservationLogger.info("Click Recalculate Room Charges");
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.click_Select_Button_RoomChargesChanged, driver);
		Utility.ScrollToElement(ReservationPage.click_Select_Button_RoomChargesChanged, driver);
		ReservationPage.click_Select_Button_RoomChargesChanged.click();
		test_steps.add("Click Select Button of Room Charges Changed");
		reservationLogger.info("Click Select Button of Room Charges Changed");
		Wait.wait2Second();
		try {
			if (ReservationPage.Policy_Comparision_PopUp.isDisplayed()) {
				ReservationPage.Policy_Comparision_PopUp_Continue_Btn.click();
				test_steps.add("Click Continue Button of Policy Comparison Popup");
			}
		} catch (Exception e) {
			System.out.println("Policy Comparision Popup not displayed ");
		}
		try {
			Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);
		} catch (Exception e) {
			Utility.app_logs.info("No module Loading element found");
		}
		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.Toaster_Message, driver);
		Wait.waitUntilPresenceOfElementLocatedByClassName(OR.Toaster_Message, driver);
		driver.findElement(By.className(OR.Toaster_Message)).click();

		String Room = ReservationPage.ReservationRoomStatus.getText();
		System.out.println("RC:" + Room);
		Wait.wait2Second();
		assertTrue(Room.contains("Multiple Rooms"), "Failed: Room Status is not multiple rooms");
		test_steps.add("Verified Room Status: " + Room);
		test_steps.addAll(RoomNumbers);
		return test_steps;
	}

	public String ReservationSataus(WebDriver driver) throws InterruptedException {

		Elements_Reservation reservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(reservationPage.ReservationsStatus, driver);
		Utility.ScrollToElement(reservationPage.ReservationsStatus, driver);
		String reservationStatus = new Select(reservationPage.ReservationsStatus).getFirstSelectedOption().getText();
		assertEquals(reservationStatus, "In-House", "Failed to check in reservation");
		return reservationStatus;
	}

	public void CheckValidationMessage(WebDriver driver, String message) throws InterruptedException {

		String messagePath = "//div[@id='ReservationDetail']//span[@class='validationMessage' and contains(text(),'"
				+ message + "')]";
		WebElement Message = driver.findElement(By.xpath(messagePath));
		Utility.ScrollToElement(Message, driver);
		assertTrue(Message.isDisplayed(), "Failed: 'Please enter a valid credit card number.' not Appeared");
	}

	public void VerifyRoomClass_Number(WebDriver driver, String RoomClass, String RoomNumber)
			throws InterruptedException {

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_Reservation.RoomClass_Number, driver);
		Utility.ScrollToElement(elements_Reservation.RoomClass_Number, driver);
		assertEquals(elements_Reservation.RoomClass_Number.getText(), RoomClass + " : " + RoomNumber);

	}

	// Validations

	public ArrayList<String> SaveButtons_Validation(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		assertTrue(!elements_Reservation.Click_Save_ReservationDetails.isEnabled(), " Save Button is Enabled at Start");
		assertTrue(!elements_Reservation.SaveAndCloseReservation.isEnabled(), " Save Button isn't Enalbed at Start");
		reservationLogger.info("Validate : Save Buttons States");
		test_steps.add("Validate : Save Buttons States");
		return test_steps;

	}

	public ArrayList<String> Referral_Validation(WebDriver driver, String Referral) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		assertTrue(!elements_Reservation.Click_Save_ReservationDetails.isEnabled(), " Save Button is Enabled");
		assertTrue(!elements_Reservation.SaveAndCloseReservation.isEnabled(), " Save Button is Enabled ");
		new Select(elements_Reservation.Reservation_Referral).selectByVisibleText(Referral);
		Wait.WaitForElement(driver, OR.Click_Save_ReservationDetails);

		elements_Reservation.Click_Save_ReservationDetails.click();
		// Validations for Fields
		// for Referral mandatory fields
		Wait.WaitForElement(driver, OR.FirstName_ContactInfo_ValidationMessage);
		assertTrue(elements_Reservation.FirstName_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		// for ContactInfo Mandatory Fields
		assertTrue(elements_Reservation.LastName_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PhoneNumber_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.Address_ContactInfo_Line1_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.City_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.State_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PostalCode_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		// Billing Info
		assertTrue(elements_Reservation.PhoneNumber_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.Address_Line1_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.City_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.State_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PostalCode_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		test_steps.add("Validate : Market Referral Reservation Page");
		return test_steps;

	}

	public ArrayList<String> FirstName_Validation(WebDriver driver, String FirstName) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		assertTrue(!elements_Reservation.Click_Save_ReservationDetails.isEnabled(), " Save Button isn Enabled");
		assertTrue(!elements_Reservation.SaveAndCloseReservation.isEnabled(), " Save Button isn Enabled ");
		new Select(elements_Reservation.Reservation_Referral).selectByIndex(1);
		elements_Reservation.Enter_First_Name.sendKeys(FirstName);
		elements_Reservation.Enter_Contact_First_Name.sendKeys(FirstName);

		elements_Reservation.Click_Save_ReservationDetails.click();

		// Validations for Fields

		// for ContactInfo Mandatory Fields
		Wait.WaitForElement(driver, OR.LastName_ContactInfo_ValidationMessage);

		assertTrue(elements_Reservation.LastName_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PhoneNumber_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.Address_ContactInfo_Line1_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.City_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.State_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PostalCode_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		// Billing Info

		assertTrue(elements_Reservation.PhoneNumber_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.Address_Line1_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.City_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.State_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PostalCode_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		test_steps.add("Validate : FirstName Reservation Page");
		return test_steps;

	}

	public ArrayList<String> LastName_Validation(WebDriver driver, String LastName) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		assertTrue(!elements_Reservation.Click_Save_ReservationDetails.isEnabled(), " Save Button isn Enabled");
		assertTrue(!elements_Reservation.SaveAndCloseReservation.isEnabled(), " Save Button isn Enabled ");
		new Select(elements_Reservation.Reservation_Referral).selectByIndex(1);
		elements_Reservation.Enter_Last_Name.sendKeys(LastName);
		Wait.WaitForElement(driver, OR.Click_Save_ReservationDetails);
		elements_Reservation.Click_Save_ReservationDetails.click();

		// for ContactInfo Mandatory Fields
		Wait.WaitForElement(driver, OR.FirstName_ContactInfo_ValidationMessage);
		assertTrue(elements_Reservation.FirstName_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PhoneNumber_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.Address_ContactInfo_Line1_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.City_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.State_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PostalCode_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		// Billing Info

		assertTrue(elements_Reservation.PhoneNumber_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.Address_Line1_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.City_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.State_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PostalCode_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		test_steps.add("Validate : LastName Reservation Page");
		return test_steps;
	}

	public ArrayList<String> PhoneNumber_Validation(WebDriver driver, String PhoneNumber) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		assertTrue(!elements_Reservation.Click_Save_ReservationDetails.isEnabled(), " Save Button is Enabled");
		assertTrue(!elements_Reservation.SaveAndCloseReservation.isEnabled(), " Save Button is Enabled ");
		new Select(elements_Reservation.Reservation_Referral).selectByIndex(1);
		elements_Reservation.Enter_Phone_Number.sendKeys(PhoneNumber);
		Wait.WaitForElement(driver, OR.Click_Save_ReservationDetails);
		elements_Reservation.Click_Save_ReservationDetails.click();

		// for ContactInfo Mandatory Fields
		Wait.WaitForElement(driver, OR.FirstName_ContactInfo_ValidationMessage);
		assertTrue(elements_Reservation.FirstName_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		assertTrue(elements_Reservation.LastName_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.Address_ContactInfo_Line1_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.City_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.State_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PostalCode_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		// Billing Info

		assertTrue(elements_Reservation.Address_Line1_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.City_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.State_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PostalCode_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		test_steps.add("Validate : PhoneNumber Reservation Page");
		return test_steps;

	}

	public ArrayList<String> Address_Line1_Validation(WebDriver driver, String Address) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		assertTrue(!elements_Reservation.Click_Save_ReservationDetails.isEnabled(), " Save Button is Enabled");
		assertTrue(!elements_Reservation.SaveAndCloseReservation.isEnabled(), " Save Button is Enabled ");
		new Select(elements_Reservation.Reservation_Referral).selectByIndex(1);
		elements_Reservation.Enter_Line1.sendKeys(Address);
		Wait.WaitForElement(driver, OR.Click_Save_ReservationDetails);
		elements_Reservation.Click_Save_ReservationDetails.click();

		// Validations for Fields
		// for ContactInfo Mandatory Fields
		Wait.WaitForElement(driver, OR.FirstName_ContactInfo_ValidationMessage);
		assertTrue(elements_Reservation.FirstName_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.LastName_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PhoneNumber_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		assertTrue(elements_Reservation.City_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.State_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PostalCode_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		// Billing Info

		assertTrue(elements_Reservation.PhoneNumber_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		assertTrue(elements_Reservation.City_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.State_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PostalCode_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		test_steps.add("Validate : Address_Line1 Reservation Page");
		return test_steps;

	}

	public ArrayList<String> City_Validation(WebDriver driver, String City) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		assertTrue(!elements_Reservation.Click_Save_ReservationDetails.isEnabled(), " Save Button is Enabled");
		assertTrue(!elements_Reservation.SaveAndCloseReservation.isEnabled(), " Save Button is Enabled ");
		new Select(elements_Reservation.Reservation_Referral).selectByIndex(1);
		elements_Reservation.Enter_City.sendKeys(City);
		Wait.WaitForElement(driver, OR.Click_Save_ReservationDetails);
		elements_Reservation.Click_Save_ReservationDetails.click();

		// Validations for Fields
		// for ContactInfo Mandatory Fields
		Wait.WaitForElement(driver, OR.FirstName_ContactInfo_ValidationMessage);
		assertTrue(elements_Reservation.FirstName_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		assertTrue(elements_Reservation.LastName_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PhoneNumber_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.Address_ContactInfo_Line1_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		assertTrue(elements_Reservation.State_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PostalCode_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		// Billing Info

		assertTrue(elements_Reservation.PhoneNumber_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.Address_Line1_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		assertTrue(elements_Reservation.State_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PostalCode_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		test_steps.add("Validate : City_InputField Reservation Page");
		return test_steps;

	}

	public ArrayList<String> State_Validation(WebDriver driver, String State) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		assertTrue(!elements_Reservation.Click_Save_ReservationDetails.isEnabled(), " Save Button is Enabled");
		assertTrue(!elements_Reservation.SaveAndCloseReservation.isEnabled(), " Save Button is Enabled ");
		new Select(elements_Reservation.Reservation_Referral).selectByIndex(1);
		new Select(elements_Reservation.Select_State).selectByVisibleText(State);
		Wait.WaitForElement(driver, OR.Click_Save_ReservationDetails);
		elements_Reservation.Click_Save_ReservationDetails.click();

		// Validations for Fields

		// for ContactInfo Mandatory Fields
		Wait.WaitForElement(driver, OR.FirstName_ContactInfo_ValidationMessage);
		assertTrue(elements_Reservation.FirstName_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		assertTrue(elements_Reservation.LastName_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PhoneNumber_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.Address_ContactInfo_Line1_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.City_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		assertTrue(elements_Reservation.PostalCode_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		// Billing Info

		assertTrue(elements_Reservation.PhoneNumber_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.Address_Line1_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.City_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		assertTrue(elements_Reservation.PostalCode_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		test_steps.add("Validate : State_InputField Reservation Page");
		return test_steps;

	}

	public ArrayList<String> PostalCode_Validation(WebDriver driver, String PostalCode) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		elements_Reservation.Enter_Postal_Code.sendKeys(PostalCode);
		assertTrue(!elements_Reservation.Click_Save_ReservationDetails.isEnabled(), " Save Button isn Enabled");
		assertTrue(!elements_Reservation.SaveAndCloseReservation.isEnabled(), " Save Button isn Enabled ");
		new Select(elements_Reservation.Reservation_Referral).selectByIndex(1);
		Wait.WaitForElement(driver, OR.Click_Save_ReservationDetails);
		elements_Reservation.Click_Save_ReservationDetails.click();

		// Validations for Fields
		// for ContactInfo Mandatory Fields
		Wait.WaitForElement(driver, OR.FirstName_ContactInfo_ValidationMessage);
		assertTrue(elements_Reservation.FirstName_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.LastName_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.PhoneNumber_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.Address_ContactInfo_Line1_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.City_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.State_ContactInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		// Billing Info

		assertTrue(elements_Reservation.PhoneNumber_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.Address_Line1_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.City_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.State_BillingInfo_ValidationMessage.isDisplayed(),
				"Validation Messgae didn't Display");

		test_steps.add("Validate : PostalCode_InputField Reservation Page");
		return test_steps;

	}

	public ArrayList<String> RoomClassPage_Validation(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.RoomAssignmentButton);
		Utility.ScrollToElement(elements_Reservation.RoomAssignmentButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", elements_Reservation.RoomAssignmentButton);
		Utility.ElementFinderUntillNotShow(By.xpath(OR.ClickSearchRoomButton), driver);
		assertTrue(elements_Reservation.SearchRoomButton.isEnabled(), "Failed: Search Button isn't Enabled");
		assertTrue(!elements_Reservation.SelectButton.isEnabled(), "Failed: Select Button isn Enabled ");
		jse.executeScript("arguments[0].click();", elements_Reservation.SearchRoomButton);
		Wait.WaitForElement(driver, OR.StartDate_ValidationMessage_RoomAssignPage);
		assertTrue(elements_Reservation.StartDate_ValidationMessage_RoomAssignPage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.EndDate_ValidationMessage_RoomAssignPage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.AdultsCount_ValidationMessage_RoomAssignPage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.NightsCount_ValidationMessage_RoomAssignPage.isDisplayed(),
				"Validation Messgae didn't Display");

		test_steps.add("Validate : RoomClass Page");
		return test_steps;

	}

	public ArrayList<String> RoomClassPage_NightsCount_Validation(WebDriver driver, String Night)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);

		Wait.WaitForElement(driver, OR.RoomAssignmentButton);
		Utility.ScrollToElement(elements_Reservation.RoomAssignmentButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", elements_Reservation.RoomAssignmentButton);
		Utility.ElementFinderUntillNotShow(By.xpath(OR.ClickSearchRoomButton), driver);
		assertTrue(elements_Reservation.SearchRoomButton.isEnabled(), "Failed: Search Button isn't Enabled");
		assertTrue(!elements_Reservation.SelectButton.isEnabled(), "Failed: Select Button isn Enabled ");
		elements_Reservation.NightField.sendKeys(Night);
		jse.executeScript("arguments[0].click();", elements_Reservation.SearchRoomButton);
		Wait.WaitForElement(driver, OR.StartDate_ValidationMessage_RoomAssignPage);
		assertTrue(elements_Reservation.StartDate_ValidationMessage_RoomAssignPage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.EndDate_ValidationMessage_RoomAssignPage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.AdultsCount_ValidationMessage_RoomAssignPage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.NightsCount_ValidationMessage_RoomAssignPage.isDisplayed(),
				"Validation Messgae didn't Display");

		test_steps.add("Validate : NightsCount RoomClass Page" + Night);
		return test_steps;

	}

	public ArrayList<String> RoomClassPage_AdultsCount_Validation(WebDriver driver, String AdultsCount)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.RoomAssignmentButton);
		Utility.ScrollToElement(elements_Reservation.RoomAssignmentButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", elements_Reservation.RoomAssignmentButton);
		Utility.ElementFinderUntillNotShow(By.xpath(OR.ClickSearchRoomButton), driver);
		assertTrue(elements_Reservation.SearchRoomButton.isEnabled(), "Failed: Search Button isn't Enabled");
		assertTrue(!elements_Reservation.SelectButton.isEnabled(), "Failed: Select Button isn Enabled ");
		elements_Reservation.Enter_Adults.sendKeys(AdultsCount);
		jse.executeScript("arguments[0].click();", elements_Reservation.SearchRoomButton);
		Wait.WaitForElement(driver, OR.StartDate_ValidationMessage_RoomAssignPage);
		assertTrue(elements_Reservation.StartDate_ValidationMessage_RoomAssignPage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.EndDate_ValidationMessage_RoomAssignPage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.NightsCount_ValidationMessage_RoomAssignPage.isDisplayed(),
				"Validation Messgae didn't Display");

		assertTrue(elements_Reservation.AdultsCount_ValidationMessage_RoomAssignPage.isDisplayed(),
				"Validation Messgae didn't Display");

		test_steps.add("Validate : AdultsCount RoomClass Page" + AdultsCount);
		return test_steps;

	}

	public ArrayList<String> RoomClassPage_StartDate_Validation(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.RoomAssignmentButton);
		Utility.ScrollToElement(elements_Reservation.RoomAssignmentButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", elements_Reservation.RoomAssignmentButton);
		Utility.ElementFinderUntillNotShow(By.xpath(OR.ClickSearchRoomButton), driver);

		assertTrue(elements_Reservation.SearchRoomButton.isEnabled(), "Failed: Search Button isn't Enabled");
		assertTrue(!elements_Reservation.SelectButton.isEnabled(), "Failed: Select Button isn Enabled ");
		// elements_Reservation.Enter_Adults.clear();
		elements_Reservation.Enter_Adults.sendKeys("0");
		jse.executeScript("arguments[0].click();", elements_Reservation.RoomAssignment_DateIcon);
		jse.executeScript("arguments[0].click();", elements_Reservation.SelectDate);
		jse.executeScript("arguments[0].click();", elements_Reservation.SearchRoomButton);
		Wait.WaitForElement(driver, OR.AdultsCount_ValidationMessage_RoomAssignPage);
		assertTrue(elements_Reservation.AdultsCount_ValidationMessage_RoomAssignPage.isDisplayed(),
				"Validation Messgae didn't Display");

		test_steps.add("Validate : Start Date RoomClass Page");
		return test_steps;

	}

	public ArrayList<String> RoomClass_Validation(WebDriver driver, ExtentTest test) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.RoomAssignmentButton);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignmentButton);
		Utility.ElementFinderUntillNotShow(By.xpath(OR.ClickSearchRoomButton), driver);
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignment_DateIcon);
		jse.executeScript("arguments[0].click();", ReservationPage.SelectDate);

		Wait.wait2Second();
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}
		jse.executeScript("arguments[0].click();", ReservationPage.SearchRoomButton);
		Wait.wait10Second();
		jse.executeScript("arguments[0].click();", ReservationPage.SelectButton);
		String RoomSelectionMsg = ReservationPage.RoomSelection_Message_RoomAssignPage.getText();
		assertTrue(RoomSelectionMsg.equals("Select valid rooms on all the listed dates"));

		test_steps.add("Validate : RoomClass_Field RoomClass Page");
		return test_steps;

	}

	public ArrayList<String> RoomNumber_Validation(WebDriver driver, ExtentTest test, String RoomClassName)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignmentButton);
		Utility.ElementFinderUntillNotShow(By.xpath(OR.ClickSearchRoomButton), driver);
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignment_DateIcon);
		jse.executeScript("arguments[0].click();", ReservationPage.SelectDate);

		Wait.wait2Second();
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}
		jse.executeScript("arguments[0].click();", ReservationPage.SearchRoomButton);
		Wait.WaitForElement(driver, OR.SelectButton);
		Wait.wait2Second();
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClassName);

		reservationLogger.info("Select Room Class: " + RoomClassName);

		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}
		jse.executeScript("arguments[0].click();", ReservationPage.SelectButton);
		Wait.WaitForElement(driver, OR.RoomSelection_Message_RoomAssignPage);
		String RoomSelectionMsg = ReservationPage.RoomSelection_Message_RoomAssignPage.getText();
		assertTrue(RoomSelectionMsg.equals("Select valid rooms on all the listed dates"));
		test_steps.add("Validate : RoomNumber_Field RoomClass Page");
		return test_steps;

	}

	public ArrayList<String> RoomClassPage_NightsCount_Negative(WebDriver driver, String Night)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		assertTrue(elements_Reservation.SearchRoomButton.isEnabled(), "Failed: Search Button isn't Enabled");
		assertTrue(!elements_Reservation.SelectButton.isEnabled(), "Failed: Select Button isn Enabled ");
		elements_Reservation.NightField.clear();
		elements_Reservation.NightField.sendKeys(Night);
		elements_Reservation.SearchRoomButton.click();
		assertTrue(elements_Reservation.StartDate_ValidationMessage_RoomAssignPage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.EndDate_ValidationMessage_RoomAssignPage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.AdultsCount_ValidationMessage_RoomAssignPage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.NightsCount_ValidationMessage_RoomAssignPage.isDisplayed()
				&& elements_Reservation.NightsCount_ValidationMessage_RoomAssignPage.getText()
						.equals("Nights should be greater than zero"),
				"Validation Messgae didn't Display");

		test_steps.add("NegativeCase : Coudldn't search rooms with NightsCount: " + Night);
		return test_steps;

	}

	public ArrayList<String> RoomClassPage_AdultsCount_Negative(WebDriver driver, String AdultsCount)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		assertTrue(elements_Reservation.SearchRoomButton.isEnabled(), "Failed: Search Button isn't Enabled");
		assertTrue(!elements_Reservation.SelectButton.isEnabled(), "Failed: Select Button isn Enabled ");
		elements_Reservation.Enter_Adults.clear();
		elements_Reservation.Enter_Adults.sendKeys(AdultsCount);
		elements_Reservation.SearchRoomButton.click();
		assertTrue(elements_Reservation.StartDate_ValidationMessage_RoomAssignPage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(elements_Reservation.EndDate_ValidationMessage_RoomAssignPage.isDisplayed(),
				"Validation Messgae didn't Display");
		assertTrue(
				elements_Reservation.AdultsCount_ValidationMessage_RoomAssignPage.isDisplayed()
						&& elements_Reservation.AdultsCount_ValidationMessage_RoomAssignPage.getText()
								.equals("Sum of adult and child should be greater than zero"),
				"Validation Messgae didn't Display");
		test_steps.add("NegativeCase : Couldn't search rooms with AdultsCount:" + AdultsCount);
		return test_steps;

	}

	public void FolioTab(WebDriver driver) {
		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.FolioTab_Reservation);
		Wait.explicit_wait_visibilityof_webelement(elements_Reservation.FolioTab_Reservation, driver);
		elements_Reservation.FolioTab_Reservation.click();
	}

	public String SaveClick(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);

		String GetEndingBalance = Elements_Reservations.Payment_Details_Folio_Balance.getText();
		reservationLogger.info(GetEndingBalance);
		String RemoveCurreny[] = GetEndingBalance.split(" ");
		String endingbalance = RemoveCurreny[1];
		reservationLogger.info("Ending balance before checkin " + endingbalance);

		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationSaveButton, driver);
		Utility.ScrollToElement(Elements_Reservations.ReservationSaveButton, driver);

		Wait.isElementDisplayed(driver, Elements_Reservations.ReservationSaveButton);
		Elements_Reservations.ReservationSaveButton.click();
		reservationLogger.info("Click on save button");
		test_steps.add("Click on save button");

		return endingbalance;
	}

	public ArrayList<String> DepositPolicy(WebDriver driver, String PaymentType, String Percentage,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);

		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(elements_Reservation.Verify_Depos_policy, driver);
		elements_Reservation.Click_Continue_Deposit.click();
		test_steps.add("Click on continue deposit button");

		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);
		new Select(elements_All_Payments.Select_Paymnet_Method).selectByVisibleText(PaymentType);
		elements_All_Payments.Add_Pay_Button.click();
		test_steps.add("Click on add button");

		// Wait.explicit_wait_absenceofelement(OR.Payment_ModuleLoading,
		// driver);
		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Payment_ModuleLoading)), 20);
		Wait.wait5Second();
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Click_Continue, driver);
		ArrayList<String> getTest_Steps = new ArrayList<>();
		getTest_Steps = VerifyPolicyPercentage(driver, Percentage, getTest_Steps, true);
		test_steps.add("Verify deposite policy with current balance, current payment and ending balance");
		test_steps.addAll(test_steps);

		elements_All_Payments.Click_Continue.click();
		test_steps.add("Click on payment continue button");
		try {
			Wait.waitForElementToBeGone(driver, elements_All_Payments.Click_Continue, 30);

		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
			elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
			Utility.app_logs.info("Click Continue again");
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);
		}
		return test_steps;
	}

	public ArrayList<String> CheckInWithPolicy(WebDriver driver, String PaymentType, String Percentage)
			throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		WebDriverWait wait = new WebDriverWait(driver, 30);

		Wait.explicit_wait_elementToBeClickable(ReservationPage.Click_Checkin, driver);
		// action.moveToElement(ReservationPage.Click_Checkin).doubleClick().build().perform();

		ReservationPage.Click_Checkin.click();
		Wait.wait2Second();
		reservationLogger.info("Clicked on CheckIn button");
		test_steps.add("Clicked on CheckIn button");
		/*
		 * try { ReservationPage.isDirtyRoomPopup_Confirm.click();
		 * Wait.wait2Second(); } catch (Exception e) { // TODO: handle exception
		 * }
		 */
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Room_Assignment_PopUp, driver);

		Wait.wait2Second();

		/*
		 * Select select = new Select(ReservationPage.SelectRoomNumbers);
		 * List<WebElement> options = select.getOptions(); for (int i = 0; i <
		 * options.size(); i++) { System.out.println("in res_ acc policy"); if
		 * (options.get(i).getText().contains("V and C")) { System.out.
		 * println("in acc_res_policy while check in in acc_res policy"); new
		 * Select(ReservationPage.SelectRoomNumbers).selectByVisibleText(options
		 * .get(i).getText()); System.out.println("in break");
		 * 
		 * break; } }
		 */

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Select, driver);
		ReservationPage.Click_Select.click();
		reservationLogger.info("Clicked on select button of room assignment popup");
		test_steps.add("Clicked on select button of room assignment popup");

		// Wait.wait2Second();
		// here add recalculate folio
		/*
		 * try { Wait.WaitForElement(driver,
		 * OR.RecalculateFolio_RadioButton__RAP);
		 * Wait.explicit_wait_visibilityof_webelement(ReservationPage.
		 * RecalculateFolio_RadioButton__RAP, driver);
		 * ReservationPage.RecalculateFolio_RadioButton__RAP.click();
		 * test_steps.add("Click on radio button Recalculate");
		 * ReservationPage.Select__RAP.click();
		 * test_steps.add("Click on select button in Room Charges Changed");
		 * Wait.wait2Second();
		 * 
		 * } catch (Exception e) {
		 * 
		 * }
		 */
		reservationLogger.info("Selected first available room with V and C status from the list");
		test_steps.add("Selected first available room with V and C status from the list");

		try {

			Wait.WaitForElement(driver, OR.Payment_Popup);
			wait.until(ExpectedConditions.visibilityOf((ReservationPage.Payment_Popup)));

			if (ReservationPage.Payment_Popup.isDisplayed()) {
				Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
				Wait.wait2Second();
				Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);
				new Select(elements_All_Payments.Select_Paymnet_Method).selectByVisibleText(PaymentType);
				elements_All_Payments.Add_Pay_Button.click();
				test_steps.add("Click on add button");

				Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Payment_ModuleLoading)), 20);
				Wait.wait3Second();
				Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Click_Continue, driver);
				ArrayList<String> getSteps = new ArrayList<>();
				getSteps = VerifyPolicyPercentage(driver, Percentage, getSteps, false);
				test_steps.addAll(getSteps);
				test_steps.add("Verify Check IN policy with current balance, current payment and ending balance");

				elements_All_Payments.Click_Continue.click();
				test_steps.add("Click on payment continue button");
				try {
					Wait.waitForElementToBeGone(driver, elements_All_Payments.Click_Continue, 30);

				} catch (Exception e) {
					Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button,
							driver);
					elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
					Utility.app_logs.info("Click Continue again");
					Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		Wait.wait5Second();
		Wait.WaitForElement(driver, OR.Click_on_confirm);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_on_confirm, driver);
		ReservationPage.Click_on_confirm.click();
		Wait.wait1Second();
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Toaster_Message, driver);
		try {
			ReservationPage.Toaster_Message.click();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return test_steps;
	}

	public double calculatePercentage(double percent, double total) {
		double s = 100 - percent;

		return (s * total) / 100;
		// return s * 100 / total;
	}

	public ArrayList<String> VerifyPolicyPercentage(WebDriver driver, String Percentage, ArrayList<String> steps,
			boolean isDepositPolicy) {

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);

		float percentage = Float.parseFloat(Percentage);
		float oneDollar_Percentage = percentage / 100;

		String currentBalance[] = elements_Reservation.CurrentBalance.getText().split(" ");
		float current_Balance = Float.parseFloat(currentBalance[1]);

		float calculatePercentage = oneDollar_Percentage * current_Balance;
		float expectedEndingBalance = current_Balance - calculatePercentage;
		String ExpectedPercentage = String.format("%.2f", calculatePercentage);

		String ExpectedBalance = String.format("%.2f", expectedEndingBalance);

		System.out.println("ExpectedPercentage : " + ExpectedPercentage);
		System.out.println("expectedEndingBalance : " + ExpectedBalance);

		assertEquals(elements_Reservation.EndingBalance.getText(), "$ " + ExpectedBalance);
		assertEquals(elements_Reservation.CurrentPayment.getText(), "$ " + ExpectedPercentage);

		if (isDepositPolicy) {
			steps.add("Expected deposit policy amount percentage that need to pay at creation of reservation is : "
					+ ExpectedPercentage);
			steps.add("Get actuall amount from amount field after click on continue deposit policy button : "
					+ ExpectedPercentage);

			steps.add("Expected Ending Balance after pay amount in deposit policy is  : " + ExpectedBalance);
			steps.add("Get ending from ending balance field after click on continue deposit policy button : "
					+ ExpectedBalance);
		} else {
			steps.add("Expected check-in policy amount percentage that need to pay at check-in reservation time is : "
					+ ExpectedPercentage);
			steps.add(
					"Get ending balance from payment details popoup to need pay accordong to check-in policy at  check-in of reservation is  : "
							+ ExpectedPercentage);

			steps.add("Expected Ending Balance after pay amount in check-in  policy is  : " + ExpectedBalance);
			steps.add("Get ending from ending balance field after click on check-in button is : " + ExpectedBalance);
		}

		return steps;

	}

	public void AccountPicker(WebDriver driver, String AccountName, String AccountNumber) throws InterruptedException {

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		Utility.ScrollToElement(elements_Reservation.Enter_Account, driver);
		elements_Reservation.Enter_Account.sendKeys(AccountName);
		Wait.wait10Second();

		try {
			Wait.WaitForElement(driver, OR.PickAccountFromDropDwon);
			Wait.explicit_wait_visibilityof_webelement_120(elements_Reservation.PickAccountFromDropDwon, driver);
			elements_Reservation.PickAccountFromDropDwon.click();
			Wait.wait1Second();
			Wait.WaitForElement(driver, OR.AccountPicker_ContinueBtn);
			Wait.explicit_wait_visibilityof_webelement(elements_Reservation.AccountPicker_ContinueBtn, driver);

		} catch (Exception e) {

			elements_Reservation.Enter_Account.clear();
			elements_Reservation.Enter_Account.sendKeys(AccountName);
			Wait.wait10Second();

			Wait.WaitForElement(driver, OR.PickAccountFromDropDwon);
			Wait.explicit_wait_visibilityof_webelement_120(elements_Reservation.PickAccountFromDropDwon, driver);
			elements_Reservation.PickAccountFromDropDwon.click();
			Wait.wait2Second();
			Wait.WaitForElement(driver, OR.AccountPicker_ContinueBtn);
			Wait.explicit_wait_visibilityof_webelement(elements_Reservation.AccountPicker_ContinueBtn, driver);

		}
		elements_Reservation.AccountPicker_ContinueBtn.click();
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(elements_Reservation.VerifyAccountName, driver);
		assertEquals(elements_Reservation.VerifyAccountName.getText(), AccountName, "Failed : account is not attached");
	}

	public void SaveReservation_DepositePolicy(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationSaveButton, driver);
		Utility.ScrollToElement(Elements_Reservations.ReservationSaveButton, driver);

		Wait.isElementDisplayed(driver, Elements_Reservations.ReservationSaveButton);
		Elements_Reservations.ReservationSaveButton.click();
		reservationLogger.info("Click on save button");
		Wait.wait1Second();

	}

	public ArrayList<String> ClickGuestProfileCheckBox(WebDriver driver, boolean Checked, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation elements = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(elements.Reservation_CreateGuestProfile_CheckBox, driver);
		Utility.ScrollToElement(elements.Reservation_CreateGuestProfile_CheckBox, driver);
		if (Checked) {
			if (!elements.Reservation_CreateGuestProfile_CheckBox.isSelected()) {
				elements.Reservation_CreateGuestProfile_CheckBox.click();
				// test_steps.add("Click Create Guest Profile CheckBox ");
			}
			assertTrue(elements.Reservation_CreateGuestProfile_CheckBox.isSelected(),
					"Failed: Create Guest Profile is Not Selected");
			test_steps.add("Create Guest Profile : Checked");

			reservationLogger.info("Create Guest Profile : Checked");
		} else {
			if (elements.Reservation_CreateGuestProfile_CheckBox.isSelected()) {
				elements.Reservation_CreateGuestProfile_CheckBox.click();
				// test_steps.add("Click Create Guest Profile CheckBox");
			}
			assertTrue(!elements.Reservation_CreateGuestProfile_CheckBox.isSelected(),
					"Failed: Create Guest Profile CheckBox  is Selected");
			test_steps.add("Create Guest Profile: UnChecked");
			reservationLogger.info("Create Guest Profile: UnChecked");
		}
		return test_steps;
	}

	public ArrayList<String> verifyRuleButtonIsDisplayed(WebDriver driver, String RoomClass, String RatePlan)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		test_steps.add("Room Assignment Clicked");
		reservationLogger.info("Room Assignment Clicked");
		ReservationPage.RoomAssignment_DateIcon.click();

		ReservationPage.SelectDate.click();
		Wait.wait2Second();

		String ratePath = "//select[contains(@data-bind,'value: SelectedRatePlanId')]";
		Wait.explicit_wait_xpath(ratePath, driver);
		new Select(driver.findElement(By.xpath(ratePath))).selectByVisibleText(RatePlan);

		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}

		ReservationPage.SearchRoomButton.click();
		Wait.wait2Second();
		test_steps.add("Search Clicked");
		reservationLogger.info("Search Clicked");
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);

		new Select(ReservationPage.SelectRoomNumbers).selectByIndex(1);
		assertTrue(!ReservationPage.CheckRule.isDisplayed(), "Failed: Rule Button Displayed");
		assertTrue(!ReservationPage.More_Rules_button.get(0).isDisplayed(), "Failed: Rule Button Displayed");
		test_steps.add("Verify Rules button not Displayed");
		reservationLogger.info("Verify Rules button not Displayed");

		Wait.wait2Second();
		ReservationPage.RoomAssign_Cancel.click();

		Wait.wait2Second();

		return test_steps;
	}

	public String getNextDate(int Day) {
		final SimpleDateFormat format = new SimpleDateFormat("dd");
		format.setTimeZone(TimeZone.getTimeZone("EST"));
		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, Day);
		return format.format(calendar.getTime());
	}

	public ArrayList<String> verifyRuleDisplayedWithStartDate(WebDriver driver, String RoomClass, int Days)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		ReservationPage.RoomAssignmentButton.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		test_steps.add("Room Assignment Clicked");
		reservationLogger.info("Room Assignment Clicked");
		Wait.wait2Second();
		Wait.explicit_wait_elementToBeClickable(ReservationPage.RoomAssignment_DateIcon, driver);
		ReservationPage.RoomAssignment_DateIcon.click();
		driver.findElement(By.xpath("//td[@class='day'][text()='" + getNextDate(Days) + "']")).click();
		// ReservationPage.SelectDate.click();
		Wait.wait2Second();

		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}

		ReservationPage.SearchRoomButton.click();
		Wait.wait2Second();
		test_steps.add("Search Clicked");
		reservationLogger.info("Search Clicked");
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);

		new Select(ReservationPage.SelectRoomNumbers).selectByIndex(1);
		assertTrue(!ReservationPage.CheckRule.isDisplayed(), "Failed: Rule Button Displayed");
		assertTrue(!ReservationPage.More_Rules_button.get(0).isDisplayed(), "Failed: Rule Button Displayed");
		test_steps.add("Verify Rules button not Displayed");
		reservationLogger.info("Verify Rules button not Displayed");

		Wait.wait2Second();
		ReservationPage.RoomAssign_Cancel.click();

		Wait.wait2Second();

		return test_steps;
	}

	public ArrayList<String> tryCheckIN(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.explicit_wait_elementToBeClickable(ReservationPage.Click_Checkin, driver);
		Utility.ScrollToElement(ReservationPage.Click_Checkin, driver);
		ReservationPage.Click_Checkin.click();
		reservationLogger.info("Clicked on CheckIn button");
		test_steps.add("Clicked on CheckIn button");
		Wait.wait3Second();
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_Dirty_Room_popup, driver);
			if (ReservationPage.Verify_Dirty_Room_popup.isDisplayed()) {
				ReservationPage.Confirm_button.click();
				reservationLogger.info("Clicked on confirm button of Reservation Popup");
			}
		} catch (Exception e) {
			reservationLogger.info("No Reservation Rooms Confilict");
		}

		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait1Second();
		// long Loadguestregform_StartTime = System.currentTimeMillis();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Select, driver);
		Wait.explicit_wait_elementToBeClickable(ReservationPage.Click_Select, driver);
		ReservationPage.Click_Select.click();
		reservationLogger.info("Clicked on select button of room assignment popup");
		test_steps.add("Clicked on select button of room assignment popup");
		Wait.wait3Second();
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_Dirty_Room_popup, driver);
			if (ReservationPage.Verify_Dirty_Room_popup.isDisplayed()) {
				ReservationPage.Confirm_button.click();
				reservationLogger.info("Clicked on confirm button of DirtyRoom Popup");
			}
		} catch (Exception e) {
			reservationLogger.info("No Dirty Rooms");
		}
		Wait.wait3Second();
		try {
			String alertPath = "//*[@id='modalRoomPickerReservation']//div[contains(@data-bind,'NoResultsFoundMessage')]";
			if (driver.findElement(By.xpath(alertPath)).isDisplayed()) {
				if (Utility.roomIndex == Utility.roomIndexEnd) {
					Utility.roomIndex = 1;
				}
				new Select(ReservationPage.SelectRoomNumbers).selectByIndex(3);

				Utility.roomIndex++;
				// Wait.wait2Second();
				String CheckRule = ReservationPage.CheckRule.getText();
				String AvailableRoom = ReservationPage.AvailableRoom.getText();
				int CheckRoom = Integer.parseInt(AvailableRoom);
				ReservationPage.SelectButton.click();

				if (CheckRoom == 0 || CheckRoom < 0) {
					Wait.wait2Second();
					ReservationPage.Continue.get(9).click();
					Wait.wait2Second();
				}
				if (CheckRule.equals("1") || CheckRule.equals("2")) {
					Wait.wait2Second();
					ReservationPage.RoleBroken_Continue.click();
				}
				Wait.wait2Second();
				try {
					Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_Dirty_Room_popup, driver);
					if (ReservationPage.Verify_Dirty_Room_popup.isDisplayed()) {
						ReservationPage.Confirm_button.click();
						reservationLogger.info("Clicked on confirm button of DirtyRoom Popup");
					}
				} catch (Exception e) {
					reservationLogger.info("No Dirty Rooms");
				}

				Wait.wait2Second();
				String roomChargesChangedPath = "//*[@id='divRoomPickerReCalculateFolios']";
				if (driver.findElement(By.xpath(roomChargesChangedPath)).isDisplayed()) {
					driver.findElement(By.xpath(
							roomChargesChangedPath + "//button[@data-bind='click: SelectClickOnReCalculateFolios']"))
							.click();
				}

			}

		} catch (Exception e) {
			System.out.println("Re-assign room not req");
		}
		try {
			if (ReservationPage.Payment_Popup.isDisplayed()) {
				driver.findElement(By.xpath("//*[@id='ReservationPaymetItemDetail']//button[contains(text(),'Close')]"))
						.click();
				reservationLogger.info("Payment Close button Clicked");
				test_steps.add("Payment Close button Clicked");
				Wait.waitForElementToBeGone(driver, ReservationPage.Payment_Popup, 20);
			}
			Wait.wait3Second();

			if (driver.findElement(By.xpath("//button[contains(@data-bind,'text: YesClickText')]")).isDisplayed()) {
				driver.findElement(By.xpath("//button[contains(@data-bind,'text: YesClickText')]")).click();
			}
		} catch (Exception e) {
			System.out.println("Payment Popup Not Appears");
		}
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_on_confirm, driver);
		Wait.explicit_wait_elementToBeClickable(ReservationPage.Click_on_confirm, driver);
		ReservationPage.Click_on_confirm.click();
		test_steps.add("Click on confrim button");

		try {
			String msg = driver.findElement(By.cssSelector(OR.Toaster_Message)).getText();

			reservationLogger.info(msg);
			test_steps.add(msg);
		} catch (Exception e) {
			System.err.println("Toaster not Displayed");
		}

		return test_steps;
	}

	public ArrayList<String> PayFolio_WithNegBlnc(WebDriver driver, String PropertyName, String RoomClassName,
			String CheckorUncheckAssign, String PaymentType, String CardName, String CCNumber, String CCExpiry,
			String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue, String traceData)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Wait.explicit_wait_visibilityof_webelement(moveFolio.MoveFolio_Folio, driver);
		Utility.ScrollToElement(moveFolio.MoveFolio_Folio, driver);
		jse.executeScript("arguments[0].click();", moveFolio.MoveFolio_Folio);

		String Endingbal = Elements_Reservations.Verify_Balance_Zero.getText();

		Endingbal = Endingbal.replace("$", " ");
		Endingbal = Endingbal.trim();
		System.out.println("Double:" + Endingbal);
		test_steps.add("The Ending Balance Before CheckIn is : $" + Endingbal);
		Double d = Double.parseDouble(Endingbal);
		d = d + 5;

		Wait.WaitForElement(driver, OR.Click_Pay_Button);
		Utility.ScrollToElement(Elements_Reservations.Click_Pay_Button, driver);
		Elements_Reservations.Click_Pay_Button.click();
		reservationLogger.info("Click on Pay");
		test_steps.add("Click on Pay Button");
		try {
			Wait.explicit_wait_xpath(OR.TakePaymentPopUp, driver);

		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.Verify_Payment_Details_poup, driver);
		}

		System.out.println(PaymentType);
		test_steps.add("The payment Type Is Master Card : " + PaymentType);
		new Select(Elements_Reservations.Select_Paymnet_Method).selectByVisibleText(PaymentType);
		try {
			Elements_Reservations.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			Elements_Reservations.Change_Amount.sendKeys(d.toString());

		} catch (Exception e) {
			reservationLogger.info("Catch Body");
			String amount = "(//label[.='Amount:'])[2]/following-sibling::div/input";
			driver.findElement(By.xpath(amount)).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			driver.findElement(By.xpath(amount)).sendKeys(d.toString());
		}

		Wait.wait3Second();
		test_steps.add("The Payment Amount Is OvrPayed: $" + (d.toString()));
		System.out.println(d.toString());
		if (PaymentType.contains("Cash")) {
			Wait.WaitForElement(driver, OR.Click_ADD);
			Elements_Reservations.Click_ADD.click();
			reservationLogger.info("Click on ADD");
			test_steps.add("Click on ADD");
		} else {
			Wait.WaitForElement(driver, OR.Click_Process);
			Elements_Reservations.Click_Process.click();
			reservationLogger.info("Click on Process");
			test_steps.add("Click on Process");
		}

		Wait.wait3Second();
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(Elements_Reservations.Click_Continue));
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Click_Continue, driver);
		Wait.WaitForElement(driver, OR.Click_Continue);
		Elements_Reservations.Click_Continue.click();
		reservationLogger.info("Click on Continue");
		test_steps.add("Click on Continue");
		Thread.sleep(5000);
		Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.Verify_Balance_Zero, driver);
		Wait.WaitForElement(driver, OR.Verify_Balance_Zero);
		Endingbal = Elements_Reservations.Verify_Balance_Zero.getText();
		Endingbal = Endingbal.replace("$", " ");
		Endingbal = Endingbal.replace("(", "  -");
		Endingbal = Endingbal.replace(")", " ");
		Endingbal = Endingbal.trim();
		d = Double.parseDouble(Endingbal);
		System.out.println("The ending balance after payment is : $" + Endingbal);
		test_steps.add("The ending balance after payment is  : $" + Endingbal);

		return test_steps;
	}

	public ArrayList<String> CheckinWithNegBlnc(WebDriver driver, String PropertyName, String RoomClassName,
			String CheckorUncheckAssign, String PaymentType, String CardName, String CCNumber, String CCExpiry,
			String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue, String traceData)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		double waittime = 0.12;
		long startTime = System.currentTimeMillis();
		driver.navigate().refresh();
		Actions action = new Actions(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Checkin, driver);
		Utility.ScrollToElement(ReservationPage.Click_Checkin, driver);
		action.moveToElement(ReservationPage.Click_Checkin).doubleClick().build().perform();
		test_steps.add("Click Checkin Button");
		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.Room_Assignment_PopUp, driver);
		test_steps.add("Room Assignment Popup appears");
		long endTime = System.currentTimeMillis();
		Wait.waitforloadpage(startTime, endTime, waittime);
		Wait.wait3Second();

		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(ReservationPage.Click_Select));

		ReservationPage.Click_Select.click();
		test_steps.add("Click on select button");

		try {
			Wait.wait2Second();
			if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
				ReservationPage.Click_Continue_RuleBroken_Popup.click();
			} else {
				reservationLogger.info("Satisfied Rules");
				test_steps.add("Satisfied Rules");
			}
		} catch (Exception e) {
			reservationLogger.info("Verification failed");
			test_steps.add("Verification failed");
		}

		if (ReservationPage.Room_Assignment_PopUp_Error.isDisplayed()) {
			if (ReservationPage.Room_Assignment_PopUp_Error.getText()
					.equalsIgnoreCase("Select valid rooms on all the listed dates")) {
				new Select(ReservationPage.Room_Selector_In_Room_Assignment_PopUp).selectByIndex(2);

				ReservationPage.Click_Select.click();
				Wait.wait2Second();
				if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {

					ReservationPage.Click_Continue_RuleBroken_Popup.click();
				} else {
					reservationLogger.info("Satisfied Rules");
					test_steps.add("Satisfied Rules");
				}
			}
		}

		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_Dirty_Room_popup, driver);
			Wait.wait2Second();
			ReservationPage.Confirm_button.click();
		} catch (Exception e) {
			reservationLogger.info("No Dirty Rooms");
			test_steps.add("No Dirty Rooms");
		}

		try {

			Wait.wait2Second();
			if (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
				ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();
				Wait.wait2Second();
				action.moveToElement(ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn).click().build().perform();

			}
		} catch (Exception e) {
			reservationLogger.info("No ReCalculate Folio Options PopUp");
			test_steps.add("No ReCalculate Folio Options PopUp");
		}
		try {

			Wait.WaitForElement(driver, OR.Payment_Popup);
			wait.until(ExpectedConditions.visibilityOf((ReservationPage.Payment_Popup)));

			if (ReservationPage.Payment_Popup.isDisplayed()) {
				Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
				Wait.wait2Second();
				String Endingbal = ReservationPage.EndingBalance.getText();
				Endingbal = Endingbal.replace("$", " ");
				Endingbal = Endingbal.trim();
				System.out.println("Double:" + Endingbal);
				test_steps.add("The Ending Balance Before CheckIn is : $" + Endingbal);
				Double d = Double.parseDouble(Endingbal);
				d = d + 5;

				new Select(elements_All_Payments.Select_Paymnet_Method).selectByVisibleText(PaymentType);
				try {
					ReservationPage.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"));
					ReservationPage.Change_Amount.sendKeys(d.toString());

				} catch (Exception e) {
					reservationLogger.info("Catch Body");
					String amount = "(//label[.='Amount:'])[2]/following-sibling::div/input";
					driver.findElement(By.xpath(amount)).sendKeys(Keys.chord(Keys.CONTROL, "a"));
					driver.findElement(By.xpath(amount)).sendKeys(d.toString());
				}

				Wait.wait3Second();
				test_steps.add("The Payment Amount Is OvrPayed: $" + (d.toString()));
				System.out.println(d.toString());
				if (PaymentType.contains("Cash")) {
					Wait.WaitForElement(driver, OR.Click_ADD);
					ReservationPage.Click_ADD.click();
					reservationLogger.info("Click on ADD");
					test_steps.add("Click on ADD");
				} else {
					Wait.WaitForElement(driver, OR.Click_Process);
					ReservationPage.Click_Process.click();
					reservationLogger.info("Click on Process");
					test_steps.add("Click on Process");
				}

				Wait.wait3Second();
				WebDriverWait wait2 = new WebDriverWait(driver, 120);
				wait.until(ExpectedConditions.elementToBeClickable(ReservationPage.Click_Continue));
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Continue, driver);
				Wait.WaitForElement(driver, OR.Click_Continue);
				ReservationPage.Click_Continue.click();
				reservationLogger.info("Click on Continue");
				test_steps.add("Click on Continue");
				Thread.sleep(5000);
				try {
					Wait.waitForElementToBeGone(driver, elements_All_Payments.Click_Continue, 30);

				} catch (Exception e) {
					Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button,
							driver);
					elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
					Utility.app_logs.info("Click Continue again");
					Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (checkinpolicy == false) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_on_confirm, driver);
			ReservationPage.Click_on_confirm.click();
			Wait.wait3Second();
		}

		try {
			if (ReservationPage.Key_Generation_Popup.isDisplayed()) {
				ReservationPage.Key_Generation_Close.click();
				Wait.wait15Second();
			}
		} catch (Exception e) {
			reservationLogger.info("Key Geneartion doesnt exist");
			test_steps.add("Key Geneartion doesnt exist");
		}
		checkinpolicy = false;
		return test_steps;
	}

	public ArrayList<String> Add_Payment_Info(WebDriver driver, String saluation, String FirstName, String LastName,
			String Line1, String Line2, String Line3, String City, String Country, String State, String Postalcode,
			String Phonenumber, String alternativenumber, String Email, String PaymentMethod, String AccountNumber,
			String ExpiryDate, String BillingNotes, ArrayList<String> steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.Click_Show_PaymentInfo, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.Click_Show_PaymentInfo);
		steps.add("Click Payment Info of Reservation");
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Folio_Billing_Info_Popup, driver);
		assertTrue(ReservationPage.Folio_Billing_Info_Popup.isDisplayed(),
				"Failed : Folio Billing info Popup not Appeared");
		steps.add("Folio Billing info Popup Appeared");
		// Wait.wait2Second();
		new Select(ReservationPage.Select_Salutation_PaymentInfo_Popup).selectByVisibleText(saluation);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Enter_First_Name_PaymentInfo_Popup, driver);
		ReservationPage.Enter_First_Name_PaymentInfo_Popup.click();
		ReservationPage.Enter_First_Name_PaymentInfo_Popup.sendKeys(FirstName);
		steps.add("Enter Payment Info First Name : " + FirstName);
		Utility.ScrollToElement(ReservationPage.Select_Payment_Method_PaymentInfo_Popup, driver);
		new Select(ReservationPage.Select_Payment_Method_PaymentInfo_Popup).selectByVisibleText(PaymentMethod);
		steps.add("Select Payment Info Popup , Payment Method : " + PaymentMethod);
		ReservationPage.Enter_Account_Number_PaymentInfo_Popup.sendKeys(AccountNumber);
		steps.add("Select Payment Info Popup , Account Number : " + AccountNumber);
		ReservationPage.Enter_CardExpiryDate_PaymentInfo_Popup.sendKeys(ExpiryDate);
		steps.add("Select Payment Info Popup Expiry Date : " + ExpiryDate);
		ReservationPage.Save_Btn_PaymentInfo_Popup.click();
		steps.add("Click Payment Info Popup Save Button");
		Wait.waitForElementToBeGone(driver, ReservationPage.Save_Btn_PaymentInfo_Popup, 60);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Payment_Info_Textarea, driver);
		String TextVaue = ReservationPage.Payment_Info_Textarea.getAttribute("value");
		reservationLogger.info(TextVaue);
		assertTrue(TextVaue.contains(PaymentMethod), "Failed to add Payment Info");
		return steps;
	}

	public ArrayList<String> Checkin_CardPayment(WebDriver driver, String PaymentType, String CardName, String CCNumber,
			String CCExpiry, String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue,
			String TraceData, ArrayList<String> steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Actions action = new Actions(driver);
		double endingbalance;

		String GetEndingBalance = ReservationPage.Payment_Details_Folio_Balance.getText();
		reservationLogger.info(GetEndingBalance);
		String RemoveCurreny[] = GetEndingBalance.split(" ");
		endingbalance = Double.parseDouble(RemoveCurreny[1]);
		reservationLogger.info("Ending balance before checkin " + endingbalance);

		Wait.explicit_wait_elementToBeClickable(ReservationPage.Click_Checkin, driver);
		// action.moveToElement(ReservationPage.Click_Checkin).doubleClick().build().perform();
		Wait.wait1Second();
		ReservationPage.Click_Checkin.click();
		reservationLogger.info("Clicked on CheckIn button");
		steps.add("Click CheckIn Reservation");

		boolean flag = false;
		try {
			Wait.explicit_wait_10sec(ReservationPage.Already_Checked_In_Confirm_Popup, driver);
			steps.add("Already CheckIn Confirm Popup Appeared");
			ReservationPage.Already_Checked_In_Confirm_Popup_Confirm_Btn.click();
			steps.add("Click Confirm Already CheckIn Confirm Popup");
			flag = true;
		} catch (Exception e) {

			reservationLogger.info("No conflicts with room assignment");
		}
		if (flag == true) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Room_Assignment_PopUp, driver);
			Select selectRoomNumber = new Select(ReservationPage.Select_Room_Number);
			String selectedOption = selectRoomNumber.getFirstSelectedOption().getText();
			if (selectedOption.contains("(O and")) {

				String RoomwithVandC = driver.findElement(By.xpath("//option[contains(text(),'(V and C)')] "))
						.getAttribute("value");
				selectRoomNumber.selectByValue(RoomwithVandC);
				reservationLogger.info("Selected first available room with V and C status from the list");
				steps.add("Selected first available room with V and C status from the list");
				Wait.wait2Second();
				ReservationPage.Click_Select.click();
				reservationLogger.info("Clicked on select button of room assignment popup");
				steps.add("Clicked on select button of room assignment popup");
				try {

					Wait.explicit_wait_visibilityof_webelement(ReservationPage.ReCalculate_Folio_Options_PopUp, driver);
					if (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
						steps.add("Click ReCalculate Folio PopUp Appeared");
						ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();
						steps.add("Select ReCalculate Folio PopUp 'No Charges Changed'");
						action.moveToElement(ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn).click().build()
								.perform();
						steps.add("Click ReCalculate Folio PopUp Select Button");

					}
				} catch (Exception e) {
					reservationLogger.info("No ReCalculate Folio Options PopUp");
				}

			} else {

				reservationLogger.info("No Issues");
			}
		}
		if (flag == false) {
			Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
			Wait.wait5Second();
			// long Loadguestregform_StartTime = System.currentTimeMillis();
			try {
				ReservationPage.Click_Select.click();
				reservationLogger.info("Clicked on select button of room assignment popup");
				steps.add("Clicked on select button of room assignment popup");
				// Wait.wait3Second();
				// Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup);
				try {

					Wait.explicit_wait_visibilityof_webelement(ReservationPage.ReCalculate_Folio_Options_PopUp, driver);
					if (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
						steps.add("Click ReCalculate Folio PopUp Appeared");
						ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();
						steps.add("Select ReCalculate Folio PopUp 'No Charges Changed'");
						action.moveToElement(ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn).click().build()
								.perform();
						steps.add("Click ReCalculate Folio PopUp Select Button");

					}
				} catch (Exception e) {
					reservationLogger.info("No ReCalculate Folio Options PopUp");
				}
			} catch (Exception e) {

			}
		}

		try {

			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_RulesBroken_Popup, driver);
			if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
				ReservationPage.Click_Continue_RuleBroken_Popup.click();
				reservationLogger.info("Clicked on continue button of RulesBroken Popup");
				steps.add("Clicked on continue button of RulesBroken Popup");
			}
		} catch (Exception e) {
			reservationLogger.info("Satisfied Rules");
		}
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_Dirty_Room_popup, driver);
			if (ReservationPage.Verify_Dirty_Room_popup.isDisplayed()) {
				ReservationPage.Confirm_button.click();
				reservationLogger.info("Clicked on confirm button of DirtyRoom Popup");
				steps.add("Clicked on confirm button of DirtyRoom Popup");
			}
		} catch (Exception e) {
			reservationLogger.info("No Dirty Rooms");
		}
		// Wait.wait10Second();
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Payment_Popup, driver);
			if (ReservationPage.Payment_Popup.isDisplayed()) {
				reservationLogger.info("Payment Popup is displayed");
				steps.add("Payment Popup is displayed");
				ReservationFolio Payment = new ReservationFolio();
				if (PaymentType.equalsIgnoreCase("MC")) {
					System.out.println(PaymentType);
					Payment.CardPayment_MC(driver, PaymentType, CardName, CCNumber, CCExpiry, CCVCode,
							Authorizationtype, ChangeAmount, ChangeAmountValue, TraceData);
					reservationLogger.info("Payment process is completed");
					steps.add("Select Payment Type : " + PaymentType);
					steps.add("Enter Card Number : " + CCNumber);
					steps.add("Enter Card Expiry : " + CCExpiry);
					steps.add("Enter CVV Code : " + CCVCode);
					steps.add("Click Card info Popup Ok Button");
					steps.add("Enter Amount : " + ChangeAmountValue);
					steps.add("Payment process is completed");
				}
			}
		} catch (Exception e) {
			reservationLogger.info("Checkin Policy doesn't exist");
		}

		reservationLogger.info("Trying to Clicking on Confirm button of Guest Registration Form in Reservation.java");
		// Thread.sleep(10000);
		Wait.explicit_wait_visibilityof_webelement_200(ReservationPage.Click_on_confirm, driver);
		ReservationPage.Click_on_confirm.click();
		steps.add("Click Confirm button of Guest Registration Form of Reservation");
		Wait.waitUntilPresenceOfElementLocatedByClassName(OR.Toaster_Message, driver);

		try {
			ReservationPage.Toaster_Message.click();
			Wait.waitForElementToBeGone(driver, ReservationPage.Toaster_Message, 30);
		} catch (Exception e) {
			Utility.app_logs.info("No toaster appear");
		}

		try {
			if (ReservationPage.Key_Generation_Popup.isDisplayed()) {
				ReservationPage.Key_Generation_Close.click();
				Wait.wait15Second();
			}
		} catch (Exception e) {
			reservationLogger.info("Key Geneartion doesnt exist");
		}
		return steps;
	}

	public ArrayList<String> Checkin_CardPayment_Auth(WebDriver driver, String PaymentType, String CardName,
			String CCNumber, String CCExpiry, String CCVCode, String Authorizationtype, String ChangeAmount,
			String ChangeAmountValue, String TraceData, ArrayList<String> steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Actions action = new Actions(driver);
		double endingbalance;

		String GetEndingBalance = ReservationPage.Payment_Details_Folio_Balance.getText();
		reservationLogger.info(GetEndingBalance);
		String RemoveCurreny[] = GetEndingBalance.split(" ");
		endingbalance = Double.parseDouble(RemoveCurreny[1]);
		reservationLogger.info("Ending balance before checkin " + endingbalance);

		Wait.explicit_wait_elementToBeClickable(ReservationPage.Click_Checkin, driver);
		// action.moveToElement(ReservationPage.Click_Checkin).doubleClick().build().perform();
		Wait.wait1Second();
		ReservationPage.Click_Checkin.click();
		reservationLogger.info("Clicked on CheckIn button");
		steps.add("Click CheckIn Reservation");

		boolean flag = false;
		try {
			Wait.explicit_wait_10sec(ReservationPage.Already_Checked_In_Confirm_Popup, driver);
			steps.add("Already CheckIn Confirm Popup Appeared");
			ReservationPage.Already_Checked_In_Confirm_Popup_Confirm_Btn.click();
			steps.add("Click Confirm Already CheckIn Confirm Popup");
			flag = true;
		} catch (Exception e) {

			reservationLogger.info("No conflicts with room assignment");
		}
		if (flag == true) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Room_Assignment_PopUp, driver);
			Select selectRoomNumber = new Select(ReservationPage.Select_Room_Number);
			String selectedOption = selectRoomNumber.getFirstSelectedOption().getText();
			if (selectedOption.contains("(O and")) {

				String RoomwithVandC = driver.findElement(By.xpath("//option[contains(text(),'(V and C)')] "))
						.getAttribute("value");
				selectRoomNumber.selectByValue(RoomwithVandC);
				reservationLogger.info("Selected first available room with V and C status from the list");
				steps.add("Selected first available room with V and C status from the list");
				Wait.wait2Second();
				ReservationPage.Click_Select.click();
				reservationLogger.info("Clicked on select button of room assignment popup");
				steps.add("Clicked on select button of room assignment popup");
				try {

					Wait.explicit_wait_visibilityof_webelement(ReservationPage.ReCalculate_Folio_Options_PopUp, driver);
					if (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
						steps.add("Click ReCalculate Folio PopUp Appeared");
						ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();
						steps.add("Select ReCalculate Folio PopUp 'No Charges Changed'");
						action.moveToElement(ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn).click().build()
								.perform();
						steps.add("Click ReCalculate Folio PopUp Select Button");

					}
				} catch (Exception e) {
					reservationLogger.info("No ReCalculate Folio Options PopUp");
				}

			} else {

				reservationLogger.info("No Issues");
			}
		}
		if (flag == false) {
			Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
			Wait.wait5Second();
			// long Loadguestregform_StartTime = System.currentTimeMillis();
			try {
				ReservationPage.Click_Select.click();
				reservationLogger.info("Clicked on select button of room assignment popup");
				steps.add("Clicked on select button of room assignment popup");
				// Wait.wait3Second();
				// Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup);
				try {

					Wait.explicit_wait_visibilityof_webelement(ReservationPage.ReCalculate_Folio_Options_PopUp, driver);
					if (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
						steps.add("Click ReCalculate Folio PopUp Appeared");
						ReservationPage.ReCal_Folio_Options_PopUp_No_Charge_Changed.click();
						steps.add("Select ReCalculate Folio PopUp 'No Charges Changed'");
						action.moveToElement(ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn).click().build()
								.perform();
						steps.add("Click ReCalculate Folio PopUp Select Button");

					}
				} catch (Exception e) {
					reservationLogger.info("No ReCalculate Folio Options PopUp");
				}
			} catch (Exception e) {

			}
		}

		try {

			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_RulesBroken_Popup, driver);
			if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
				ReservationPage.Click_Continue_RuleBroken_Popup.click();
				reservationLogger.info("Clicked on continue button of RulesBroken Popup");
				steps.add("Clicked on continue button of RulesBroken Popup");
			}
		} catch (Exception e) {
			reservationLogger.info("Satisfied Rules");
		}
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_Dirty_Room_popup, driver);
			if (ReservationPage.Verify_Dirty_Room_popup.isDisplayed()) {
				ReservationPage.Confirm_button.click();
				reservationLogger.info("Clicked on confirm button of DirtyRoom Popup");
				steps.add("Clicked on confirm button of DirtyRoom Popup");
			}
		} catch (Exception e) {
			reservationLogger.info("No Dirty Rooms");
		}
		// Wait.wait10Second();
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Payment_Popup, driver);
			if (ReservationPage.Payment_Popup.isDisplayed()) {
				reservationLogger.info("Payment Popup is displayed");
				steps.add("Payment Popup is displayed");
				ReservationFolio Payment = new ReservationFolio();
				if (PaymentType.equalsIgnoreCase("MC")) {
					System.out.println(PaymentType);
					Payment.CardPayment_MC_Auth(driver, PaymentType, CardName, CCNumber, CCExpiry, CCVCode,
							Authorizationtype, ChangeAmount, ChangeAmountValue, TraceData);
					reservationLogger.info("Payment process is completed");
					steps.add("Select Payment Type : " + PaymentType);
					steps.add("Enter Card Number : " + CCNumber);
					steps.add("Enter Card Expiry : " + CCExpiry);
					steps.add("Enter CVV Code : " + CCVCode);
					steps.add("Click Card info Popup Ok Button");
					steps.add("Enter Amount : " + ChangeAmountValue);
					steps.add("Payment process is completed");
				}
			}
		} catch (Exception e) {
			reservationLogger.info("Checkin Policy doesn't exist");
		}

		reservationLogger.info("Trying to Clicking on Confirm button of Guest Registration Form in Reservation.java");
		// Thread.sleep(10000);
		Wait.explicit_wait_visibilityof_webelement_200(ReservationPage.Click_on_confirm, driver);
		ReservationPage.Click_on_confirm.click();
		steps.add("Click Confirm button of Guest Registration Form of Reservation");
		Wait.waitUntilPresenceOfElementLocatedByClassName(OR.Toaster_Message, driver);

		try {
			ReservationPage.Toaster_Message.click();
			Wait.waitForElementToBeGone(driver, ReservationPage.Toaster_Message, 30);
		} catch (Exception e) {
			Utility.app_logs.info("No toaster appear");
		}

		try {
			if (ReservationPage.Key_Generation_Popup.isDisplayed()) {
				ReservationPage.Key_Generation_Close.click();
				Wait.wait15Second();
			}
		} catch (Exception e) {
			reservationLogger.info("Key Geneartion doesnt exist");
		}
		return steps;
	}

	public ArrayList<String> RoomAssign_ForChangeNight(WebDriver driver, String RoomChagerType,
			ArrayList<String> test_steps) throws InterruptedException, ParseException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		ReservationPage.RoomAssignmentButton.click();
		test_steps.add("Click on room assignmnet picker button");
		Wait.wait3Second();
		Wait.WaitForElement(driver, OR.NightField);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.NightField, driver);

		int night = Integer.parseInt(ReservationPage.NightField.getAttribute("value"));
		int afternight = night + 1;
		ReservationPage.NightField.clear();
		ReservationPage.NightField.sendKeys("" + afternight);

		test_steps.add("Change night stay from " + night + " to " + afternight);

		Wait.wait1Second();
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.SearchRoomButton, driver);
		ReservationPage.SearchRoomButton.click();

		Wait.WaitForElement(driver, OR.SelectRoomNumbers);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.List_SelectRoomNumbers.get(0), driver);
		Select select = new Select(ReservationPage.List_SelectRoomNumbers.get(0));
		List<WebElement> options = select.getOptions();
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().contains("V and C")) {
				new Select(ReservationPage.List_SelectRoomNumbers.get(0)).selectByVisibleText(options.get(i).getText());
				System.out.println("in break");

				break;
			}

		}

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.SelectButton, driver);
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.NoRoomCharger_RadioButton_RAP, driver);
		if (RoomChagerType.equals("Recalculate")) {
			ReservationPage.RecalculateFolio_RadioButton__RAP.click();
			test_steps.add("Click on radio button Recalculate");
		}
		if (RoomChagerType.equals("ApplyDelta")) {
			ReservationPage.ApplyDeltaEnabled_RadioButton__RAP.click();
			test_steps.add("Click on radio button Apply Rate Change for Changed Dates Only");
		}

		if (RoomChagerType.equals("StayDates")) {
			ReservationPage.NoRoomCharger_RadioButton_RAP.click();
			test_steps.add("Click on radio button No Room Charge Changed/Added");
		}
		ReservationPage.Select__RAP.click();
		test_steps.add("Click on select button in Room Charges Changed");

		Wait.wait1Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			ReservationPage.RoleBroken_Continue.click();
		}

		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);

		try {
			Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
			Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Toaster_Message, driver);
			ReservationPage.Toaster_Message.click();
		} catch (Exception e) {
			PolicyComparisionPopup(driver);
		}

		return test_steps;
	}

	public String getDates(WebDriver driver) throws InterruptedException {

		Elements_Reservation elements_Reservations = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.GuestInfo_Tab);
		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.GuestInfo_Tab, driver);

		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.elementToBeClickable(elements_Reservations.GuestInfo_Tab));

		elements_Reservations.GuestInfo_Tab.click();
		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.Depart_Value.get(1), driver);
		Utility.ScrollToElement(elements_Reservations.Depart_Value.get(1), driver);
		String StayDates = elements_Reservations.Depart_Value.get(1).getText();

		return StayDates;

	}

	public String getFolioBalance(WebDriver driver) throws InterruptedException {

		Elements_Reservation elements_Reservations = new Elements_Reservation(driver);

		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.GuestInfo_Tab, driver);
		// elements_Reservations.GuestInfo_Tab.click();

		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.ReservationFolioBalance, driver);
		String getBalance = elements_Reservations.ReservationFolioBalance.getText();

		return getBalance;

	}

	public ArrayList<String> RecalcualteFolioBalanceAfterRA(WebDriver driver, String beforeBalance,
			String initialAmount, String afterBalance, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.GuestInfo_Tab, driver);
		// elements_Reservations.GuestInfo_Tab.click();

		String[] str_beforeBalance = beforeBalance.split(" ");
		double beforeRec_Balance = Double.parseDouble(str_beforeBalance[1]);

		// initial amount
		String[] str_initial = initialAmount.split(" ");
		double initialBalance = Double.parseDouble(str_initial[1]);

		elements_Reservations.FolioTab_Reservation.click();
		Wait.wait1Second();
		int size = elements_Reservations.List_LineItemAmount.size();
		assertEquals(size, 3, "Line item did not increase after Recalculate in RA");
		Utility.ScrollToElement(elements_Reservations.List_LineItemAmount.get(size - 1), driver);
		String lineItem_Amount = elements_Reservations.List_LineItemAmount.get(size - 1).getText();
		String[] str_FolioAmount = lineItem_Amount.split(" ");
		double folio_Balance = Double.parseDouble(str_FolioAmount[1]);
		double ExpectedBalancebalance = beforeRec_Balance + folio_Balance;

		String[] str_afterBalance = afterBalance.split(" ");
		double afterRec_Balance = Double.parseDouble(str_afterBalance[1]);

		test_steps.add("Expected folio balance Recalculate folio in RA : " + afterBalance);
		test_steps.add("After Recalculate folio balance in RA : " + afterBalance);

		assertEquals(ExpectedBalancebalance, afterRec_Balance, "Failed : To verift recalculte folio balance after RA");
		test_steps.add("Verified folio balance before and after recalculate folio in RA");
		return test_steps;

	}

	public ArrayList<String> ApplyDaltaFolioBalanceAfterRA(WebDriver driver, String beforeBalance, String initialAmount,
			String afterBalance, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.GuestInfo_Tab, driver);
		// elements_Reservations.GuestInfo_Tab.click();

		String[] str_beforeBalance = beforeBalance.split(" ");
		double beforeRec_Balance = Double.parseDouble(str_beforeBalance[1]);

		// initial amount
		String[] str_initial = initialAmount.split(" ");
		double initialBalance = Double.parseDouble(str_initial[1]);

		elements_Reservations.FolioTab_Reservation.click();
		Wait.wait1Second();
		int size = elements_Reservations.List_LineItemAmount.size();
		assertEquals(size, 3, "Line item did not increase after Apply Delta in RA");
		Utility.ScrollToElement(elements_Reservations.List_LineItemAmount.get(size - 1), driver);
		String lineItem_Amount = elements_Reservations.List_LineItemAmount.get(size - 1).getText();
		String[] str_FolioAmount = lineItem_Amount.split(" ");
		double folio_Balance = Double.parseDouble(str_FolioAmount[1]);
		double ExpectedBalancebalance = beforeRec_Balance + folio_Balance;

		String[] str_afterBalance = afterBalance.split(" ");
		double afterRec_Balance = Double.parseDouble(str_afterBalance[1]);

		test_steps.add("Expected folio balance after Apply Delta in RA : " + afterBalance);
		test_steps.add("After Apply Delta folio balance in RA : " + afterBalance);

		assertEquals(ExpectedBalancebalance, afterRec_Balance, "Failed : To verift recalculte folio balance after RA");
		test_steps.add("Verified folio balance before and after Applly Delta in RA");
		return test_steps;

	}

	public String Reservation_Status(WebDriver driver, String ExpectedStatus) throws InterruptedException {

		Elements_Reservation elements_Reservations = new Elements_Reservation(driver);
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.Reservations_Status, driver);
		String getReservation_Status = new Select(elements_Reservations.Reservations_Status).getFirstSelectedOption()
				.getText();
		// String getReservation_Status =
		// elements_Reservations.Reservations_Status.getText();
		assertEquals(getReservation_Status.trim(), ExpectedStatus, "Failed to verify reservation status");
		return getReservation_Status;

	}

	public String RoomClass_Number(WebDriver driver, String ExpectedRoomClass, String ExpectedRoomNumber)
			throws InterruptedException {

		Elements_Reservation elements_Reservations = new Elements_Reservation(driver);
		String getRoomClass_Number = elements_Reservations.GetSelectedRoomNumber.getText();
		String[] split_str = getRoomClass_Number.split(":");
		assertEquals(split_str[0].trim(), ExpectedRoomClass, "Failed to verify room class");
		assertEquals(split_str[1].trim(), ExpectedRoomNumber, "Failed to verify room number");
		return getRoomClass_Number;

	}

	public ArrayList<String> VerifyTaxExempt(WebDriver driver, boolean FutureTaxExempt, String ExpectedTax,
			ArrayList<String> steps) throws InterruptedException {

		Elements_Reservation elements_Reservations = new Elements_Reservation(driver);
		Wait.wait2Second();
		if (FutureTaxExempt) {
			Wait.explicit_wait_visibilityof_webelement(elements_Reservations.TexExempt_CheckBox, driver);
			elements_Reservations.TexExempt_CheckBox.click();
			Wait.wait1Second();
			elements_Reservations.SetExemptionFutureItems_Button.click();
			Wait.explicit_wait_visibilityof_webelement(elements_Reservations.Toaster_Message, driver);
			elements_Reservations.Toaster_Message.click();
			Wait.wait1Second();
			Wait.explicit_wait_visibilityof_webelement(elements_Reservations.List_LineItemTax.get(0), driver);
			Utility.ScrollToElement(elements_Reservations.List_LineItemTax.get(0), driver);

			for (int i = 0; i < 5; i++) {
				if (i < 2) {
					assertNotEquals(elements_Reservations.List_LineItemTax.get(i).getText(), ExpectedTax,
							"Faild to verify Tax Exempt in future valid item");
				} else {
					assertEquals(elements_Reservations.List_LineItemTax.get(i).getText(), ExpectedTax,
							"Faild to verify Tax Exempt in future valid item");
				}
			}

		} else {
			Wait.explicit_wait_visibilityof_webelement(elements_Reservations.SetExemptionAllItems_Button, driver);
			elements_Reservations.SetExemptionAllItems_Button.click();
			Wait.explicit_wait_visibilityof_webelement(elements_Reservations.Toaster_Message, driver);
			elements_Reservations.Toaster_Message.click();
			Wait.wait1Second();
			Wait.explicit_wait_visibilityof_webelement(elements_Reservations.List_LineItemTax.get(0), driver);
			for (int i = 0; i < 5; i++) {
				assertEquals(elements_Reservations.List_LineItemTax.get(i).getText(), ExpectedTax,
						"Faild to verify Tax Exempt in all valid item");
			}
		}

		return steps;
	}

	public ArrayList<String> RoomAssign_ForLongStay(WebDriver driver, String RoomClass, String NightStay,
			ArrayList<String> test_steps, boolean Recalculate, int DaysBack)
			throws InterruptedException, ParseException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		ReservationPage.RoomAssignmentButton.click();
		test_steps.add("Click on room assignmnet picker button");

		Utility.ElementFinderUntillNotShow(By.xpath(OR.ClickSearchRoomButton), driver);

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String DF = dateFormat.format(date);

		Calendar c = Calendar.getInstance();
		c.setTime(dateFormat.parse(DF));

		int totalStay = Integer.parseInt(NightStay);
		totalStay = totalStay - DaysBack;
		c.add(Calendar.DATE, -DaysBack);
		String arrivalDate = dateFormat.format(c.getTime());

		Wait.wait1Second();

		ReservationPage.CheckIn_Out_Field.clear();
		Wait.wait1Second();
		ReservationPage.CheckIn_Out_Field.get(0).click();
		// System.out.println(ReservationPage.CheckIn_Out_Field.get(0).getAttribute("value"));
		// Wait.wait2Second();
		ReservationPage.CheckIn_Out_Field.clear();
		String size = ReservationPage.CheckIn_Out_Field.get(0).getAttribute("value");
		for (int i = 0; i < size.length(); i++) {
			ReservationPage.CheckIn_Out_Field.get(0).sendKeys(Keys.BACK_SPACE);

		}
		System.out.println("arrivalDate : " + arrivalDate);
		ReservationPage.CheckIn_Out_Field.get(0).sendKeys(arrivalDate);
		// Wait.wait2Second();
		test_steps.add("Enter Arrvial date : " + arrivalDate);
		ReservationPage.EnterPromocode.click();

		// Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.NightField, driver);
		// ReservationPage.EnterPromocode.click();
		ReservationPage.NightField.click();
		ReservationPage.NightField.clear();
		ReservationPage.NightField.sendKeys(NightStay);
		// Wait.wait1Second();
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}
		ReservationPage.SearchRoomButton.click();

		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}

		reservationLogger.info("RoomClass:" + RoomClass);
		for (int i = 0; i < Integer.parseInt(NightStay); i++) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.SelectRoomClasses, driver);
			new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
			reservationLogger.info("Select room class: " + RoomClass);

		}
		new Select(ReservationPage.List_SelectRoomNumbers.get(0)).selectByIndex(Utility.roomIndex);
		Utility.roomIndex++;
		test_steps.add("Click Select: Room Assignment");
		reservationLogger.info("Select Rooms");
		test_steps.add("Select room class: " + RoomClass);

		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();

		try {
			Wait.wait1Second();
			if (CheckRoom == 0 || CheckRoom < 0) {
				ReservationPage.Continue.get(9).click();
			}
			if (CheckRule.equals("1") || CheckRule.equals("2")) {
				ReservationPage.RoleBroken_Continue.click();
			}
		} catch (Exception e) {

		}

		if (Recalculate) {
			Wait.wait1Second();
			ReservationPage.RecalculateFolio_RadioButton__RAP.click();
			test_steps.add("Click on radio button Recalculate");
			ReservationPage.Select__RAP.click();
			test_steps.add("Click on select button in Room Charges Changed");
			Wait.wait1Second();
		}

		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Toaster_Message, driver);
		ReservationPage.Toaster_Message.click();
		Wait.wait1Second();
		return test_steps;
	}

	public void SaveReservation_LongStay(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException, IOException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.ReservationSaveButton, driver);
		Utility.ScrollToElement(Elements_Reservations.ReservationSaveButton, driver);

		Wait.isElementDisplayed(driver, Elements_Reservations.ReservationSaveButton);
		Elements_Reservations.ReservationSaveButton.click();
		Wait.wait2Second();
		reservationLogger.info("Click on save button");
		test_steps.add("Click on save button");
		try {
			Wait.explicit_wait_visibilityof_webelement(Elements_Reservations.CancelDepositePolicy_Button.get(3),
					driver);
			Elements_Reservations.CancelDepositePolicy_Button.get(3).click();
			Wait.wait1Second();

		} catch (Exception e) {
		}

	}

	public void LongStayCheckBox(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException, IOException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.LongStayCheckbox, driver);
		Utility.ScrollToElement(Elements_Reservations.LongStayCheckbox, driver);
		Elements_Reservations.LongStayCheckbox.click();

	}

	public ArrayList<String> verifyAddNotesLineItem(WebDriver driver, String subject, String details, String NoteType,
			boolean isActionChecked) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Utility.ScrollToElement(ReservationPage.Verify_Added_Notes, driver);
		List<WebElement> pretableSize = driver.findElements(By.xpath(OR.Verify_Added_Notes + "/tr"));

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		jse.executeScript("arguments[0].click();", ReservationPage.Add_Note_Reservation);
		// ReservationPage.Add_Note_Reservation.click();
		reservationLogger.info("Add Note Button Clikced");
		test_steps.add("Add Note Button Clikced");
		Wait.wait2Second();
		Wait.explicit_wait_xpath(OR.verify_Add_Notes_popup, driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Enter_Subject_Notes, driver);
		ReservationPage.Enter_Subject_Notes.clear();
		ReservationPage.Enter_Subject_Notes.sendKeys(subject);
		reservationLogger.info("Enter Subject Notes : " + subject);
		test_steps.add("Enter Subject Notes : " + subject);
		ReservationPage.Enter_Note_Details.clear();
		ReservationPage.Enter_Note_Details.sendKeys(details);
		reservationLogger.info("Enter Notes Details : " + details);
		test_steps.add("Enter Notes Details : " + details);
		new Select(ReservationPage.Select_Note_Type).selectByVisibleText(NoteType);
		reservationLogger.info("Select Notes Type : " + NoteType);
		test_steps.add("Select Notes Type : " + NoteType);
		// new
		// Select(ReservationPage.Select_Notes_Status).selectByVisibleText(Notestatus);
		reservationLogger.info("Is Action Required : " + isActionChecked);
		test_steps.add("Is Action Required : " + isActionChecked);
		if (isActionChecked) {
			if (!ReservationPage.Check_Action_Required.isSelected()) {
				ReservationPage.Check_Action_Required.click();
				reservationLogger.info("Action Required : Checked");
				test_steps.add("Action Required : Checked");
			}
		} else {
			if (ReservationPage.Check_Action_Required.isSelected()) {
				ReservationPage.Check_Action_Required.click();
				reservationLogger.info("Action Required : UnChecked");
				test_steps.add("Action Required : UnChecked");
			}
		}
		try {
			System.out.println("try");
			ReservationPage.Click_Save_Note.click();
			reservationLogger.info("Save Note Clicked");
			test_steps.add("Save Note Clicked");

		} catch (Exception e) {
			System.out.println("Catch");
			jse.executeScript("arguments[0].click();", ReservationPage.Click_Save_Note1);
			reservationLogger.info("Save Note Clicked");
			test_steps.add("Save Note Clicked");

		}
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_Added_Notes, driver);
		Utility.ScrollToElement(ReservationPage.Verify_Added_Notes, driver);
		if (ReservationPage.Verify_Added_Notes.isDisplayed()) {

			List<WebElement> postTableSize = driver.findElements(By.xpath(OR.Verify_Added_Notes + "/tr"));
			reservationLogger.info("Pre Notes Table Records : " + pretableSize.size());
			test_steps.add("Pre Notes Table Records : " + pretableSize.size());
			reservationLogger.info("Post Notes Table Records : " + postTableSize.size());
			test_steps.add("Post Notes Table Records : " + postTableSize.size());
			assertEquals(postTableSize.size() - 1, pretableSize.size(), "Failed : Notes Not Added");

			reservationLogger.info("Successfully verified # of Records");
			test_steps.add("Successfully verified # of Records");

			String iconPath = OR.Verify_Added_Notes + "/tr[last()]/td[1]//i";
			String icon = driver.findElement(By.xpath(iconPath)).getAttribute("class");
			if (NoteType.equalsIgnoreCase("Guest Note")) {
				assertEquals(icon, "fa fa-pencil-square-o", "Failed : Notes Item Icon Not Matched For Type Guest Note");
				String iconPath1 = OR.Verify_Added_Notes + "/tr[last()]/td[1]/span";
				String icon1 = driver.findElement(By.xpath(iconPath1)).getAttribute("class");
				assertEquals(icon1, "noteType GuestNote", "Failed : Notes Item Icon Not Matched For Type Guest Note");
				reservationLogger.info("Successfully verified Guest Notes Icon");
				test_steps.add("Successfully verified Guest Notes Icon");

			} else if (NoteType.equalsIgnoreCase("Internal")) {
				assertEquals(icon, "fa fa-pencil-square-o", "Failed : Notes Item Icon Not Matched For Type Internal");
				String iconPath1 = OR.Verify_Added_Notes + "/tr[last()]/td[1]//span";
				String icon1 = driver.findElement(By.xpath(iconPath1)).getAttribute("class");
				assertEquals(icon1, "noteType Internal", "Failed : Notes Item Icon Not Matched For Type Guest Note");
				reservationLogger.info("Successfully verified Internal Notes Icon");
				test_steps.add("Successfully verified Internal Notes Icon");
			}

			String NotesType = driver.findElement(By.xpath(OR.Verify_Added_Notes + "/tr[last()]/td[2]/span")).getText();
			assertEquals(NotesType, NoteType, "Failed : Notes Item NotesType Not Matched");
			reservationLogger.info("Successfully verified Notes Type");
			test_steps.add("Successfully verified Notes Type");

			String Subject = driver.findElement(By.xpath(OR.Verify_Added_Notes + "/tr[last()]/td[3]/a")).getText();
			assertEquals(Subject, subject, "Failed : Notes Item Subject Not Matched");
			reservationLogger.info("Successfully verified Notes Subject");
			test_steps.add("Successfully verified Notes Subject");

			WebElement actionReq = driver.findElement(By.xpath(OR.Verify_Added_Notes + "/tr[last()]/td[5]/input"));
			assertTrue(actionReq.isDisplayed(), "Failed : Notes Item Action Req is Enabled");
			assertEquals(actionReq.isSelected(), isActionChecked, "Failed : Notes Item Action Req");
			reservationLogger.info("Successfully verified ActionRequired Displayed: " + actionReq.isDisplayed());
			test_steps.add("Successfully verified ActionRequired Displayed: " + actionReq.isDisplayed());
			reservationLogger.info("Successfully verified ActionRequired isSelected : " + actionReq.isSelected());
			test_steps.add("Successfully verified ActionRequired isSelected : " + actionReq.isSelected());

			String dueDate = driver.findElement(By.xpath(OR.Verify_Added_Notes + "/tr[last()]/td[9]/span")).getText();
			if (!actionReq.isSelected()) {
				assertEquals(dueDate, "", "Failed : Notes Item Due Date");
				reservationLogger.info("Successfully verified Due Date");
				test_steps.add("Successfully verified Due Date");
			} else {
				assertEquals(dueDate, getCurrDate(), "Failed : Notes Item Due Date");
				reservationLogger.info("Successfully verified Due Date : " + dueDate);
				test_steps.add("Successfully verified Due Date : " + dueDate);
			}
			String updateDate = driver.findElement(By.xpath(OR.Verify_Added_Notes + "/tr[last()]/td[11]/span"))
					.getText();
			assertEquals(updateDate, getCurrDate(), "Failed : Notes Item UpdateDate");
			reservationLogger.info("Successfully verified Update Date");
			test_steps.add("Successfully verified Update Date");

			WebElement checkButton = driver.findElement(
					By.xpath(OR.Verify_Added_Notes + "/tr[last()]/td[12]/span/i[contains(@class,'fa fa-check')]"));
			assertTrue(checkButton.isDisplayed(), "Failed : Notes Item Check Button Not Displayed");
			assertTrue(checkButton.isEnabled(), "Failed : Notes Item Check Button Not Enabled");
			reservationLogger.info("Successfully verified CheckButton : displayed & enabled");
			test_steps.add("Successfully verified CheckButton : displayed & enabled");

			WebElement cancelButton = driver.findElement(
					By.xpath(OR.Verify_Added_Notes + "/tr[last()]/td[12]/span/i[contains(@class,'fa fa-times')]"));
			assertTrue(cancelButton.isDisplayed(), "Failed : Notes Item Cancel Button Not Displayed");
			assertTrue(cancelButton.isEnabled(), "Failed : Notes Item Cancel Button Not Enabled");
			reservationLogger.info("Successfully verified CancelButton : displayed & enabled");
			test_steps.add("Successfully verified CancelButton : displayed & enabled");

			WebElement deleteButton = driver.findElement(By.xpath(
					OR.Verify_Added_Notes + "/tr[last()]/td[12]/span/i[contains(@class,'fa fa-trash-o fa-lg')]"));
			assertTrue(deleteButton.isDisplayed(), "Failed : Notes Item Delete Button Not Displayed");
			assertTrue(deleteButton.isEnabled(), "Failed : Notes Item Delete Button Not Enabled");
			reservationLogger.info("Successfully verified DeleteButton : displayed & enabled");
			test_steps.add("Successfully verified DeleteButton : displayed & enabled");

			reservationLogger.info("Created note Successfully");

		} else {
			reservationLogger.info("Failed to add notes");

		}

		return test_steps;
	}

	public String getCurrDate() {
		final SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");
		format.setTimeZone(TimeZone.getTimeZone("EST"));
		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return format.format(calendar.getTime());
	}

	public void downloadPDFReport(WebDriver driver) throws InterruptedException, IOException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.PrintIcon.click();
		Wait.wait3Second();
		driver.findElement(By.xpath("//*[@id='optionsForInnroad']//div[text()='Print']")).click();
		Wait.wait5Second();
		Wait.waitForElementToBeGone(driver,
				driver.findElement(By.xpath("//*[@id='bpjscontainer_23']/div[2]//div[@class='modules_loading']")),
				10000);
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Wait.wait2Second();

		new Select(driver.findElement(By.xpath("//*[@id='drpReportType']"))).selectByVisibleText("Download As Pdf");
		driver.findElement(By.xpath("//*[@id='btnImgDownload']")).click();
		Wait.wait3Second();

		driver.close();
		driver.switchTo().window(tabs2.get(0));

	}

	public String checkPDF(String folder) throws IOException {
		File getFile = Utility.getLatestFilefromDir(System.getProperty("user.dir") + File.separator + "externalFiles"
				+ File.separator + "downloadFiles" + File.separator + folder + File.separator);
		System.out.println(getFile.getAbsolutePath());
		System.out.println(getFile.getName());
		PDDocument document = PDDocument.load(getFile);
		document.getClass();
		String pdfFileInText = null;
		if (!document.isEncrypted()) {
			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			stripper.setSortByPosition(true);
			PDFTextStripper tStripper = new PDFTextStripper();
			pdfFileInText = tStripper.getText(document);
			document.close();

			if (getFile.delete()) {
				System.out.println("File deleted successfully");
			} else {
				System.out.println("Failed to delete the file");
			}
		}
		return pdfFileInText;
	}

	public ArrayList<String> verifyReportPDF(String pdfFileText, String resNo, String notesSubject, String notesDetail,
			String taskSubject, String taskDetail) {

		ArrayList<String> test_steps = new ArrayList<>();
		if (pdfFileText.contains(resNo)) {
			assertTrue(true);
			reservationLogger.info("Successfully Verified ReservationNo in PDF Report : " + resNo);
			test_steps.add("Successfully Verified ReservationNo in PDF Report : " + resNo);
		} else {
			assertTrue(false, "Failed : Reservation NO not matched in PDF File");
		}
		if (pdfFileText.contains(notesSubject)) {
			assertTrue(true);
			reservationLogger.info("Successfully Verified Notes Subject in PDF Report : " + notesSubject);
			test_steps.add("Successfully Verified Notes Subject in PDF Report : " + notesSubject);
		} else {
			assertTrue(false, "Failed : Notes Subject not matched in PDF File");
		}
		if (pdfFileText.contains(notesDetail)) {
			assertTrue(true);
			reservationLogger.info("Successfully Verified Notes Detail in PDF Report : " + notesDetail);
			test_steps.add("Successfully Verified Notes Detail in PDF Report : " + notesDetail);
		} else {
			assertTrue(false, "Failed : Notes Detail not matched in PDF File");
		}
		if (!pdfFileText.contains(taskSubject)) {
			assertTrue(true);
			reservationLogger.info("Successfully Verified Task not Displayed in PDF Report : " + taskSubject);
			test_steps.add("Successfully Verified Notes Task not Displayed in PDF Report : " + taskSubject);
		} else {
			assertTrue(false, "Failed : Notes Subject not matched in PDF File");
		}
		if (!pdfFileText.contains(taskDetail)) {
			assertTrue(true);
			reservationLogger.info("Successfully Verified Task not Displayed in PDF Report : " + taskDetail);
			test_steps.add("Successfully Verified Notes Task not Displayed in PDF Report : " + taskDetail);
		} else {
			assertTrue(false, "Failed : Notes Detail not matched in PDF File");
		}

		reservationLogger.info("Successfully Verified PDF Report");
		test_steps.add("Successfully Verified PDF Report");

		return test_steps;
	}

	public void downloadPDF_Searched(WebDriver driver) throws InterruptedException {
		Wait.explicit_wait_visibilityof_webelement(
				driver.findElement(By.xpath("//*[@id='bpjscontainer_18']//a[contains(@data-bind,'Print')]")), driver);
		WebElement printBtn = driver
				.findElement(By.xpath("//*[@id='bpjscontainer_18']//a[contains(@data-bind,'Print')]"));
		printBtn.click();
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(
				driver.findElement(By
						.xpath("//*[@id='optionsForInnroad']//span[.='Guest Registration']/preceding-sibling::input[contains(@data-bind,'radio')]")),
				driver);
		WebElement guestRegRadio = driver.findElement(By.xpath(
				"//*[@id='optionsForInnroad']//span[.='Guest Registration']/preceding-sibling::input[contains(@data-bind,'radio')]"));
		if (!guestRegRadio.isSelected()) {
			guestRegRadio.click();
		}
		Wait.wait2Second();
		driver.findElement(By.xpath("//*[@id='optionsForInnroad']//div[.='Print']")).click();
		Wait.wait2Second();
		Wait.waitForElementToBeGone(driver,
				driver.findElement(By.xpath("//*[@id='bpjscontainer_23']/div[2]//div[@class='modules_loading']")),
				10000);
		Wait.wait10Second();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Wait.wait2Second();

		new Select(driver.findElement(By.xpath("//*[@id='drpReportType']"))).selectByVisibleText("Download As Pdf");
		driver.findElement(By.xpath("//*[@id='btnImgDownload']")).click();
		Wait.wait3Second();

		driver.close();
		driver.switchTo().window(tabs2.get(0));
	}

	public void manualEmail_Searched(WebDriver driver, String email) throws InterruptedException {
		Wait.explicit_wait_visibilityof_webelement(
				driver.findElement(By.xpath("//*[@id='bpjscontainer_18']//a[contains(@data-bind,'Print')]")), driver);
		WebElement printBtn = driver
				.findElement(By.xpath("//*[@id='bpjscontainer_18']//a[contains(@data-bind,'Print')]"));
		printBtn.click();
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(
				driver.findElement(
						By.xpath("//*[@id='optionsForInnroad']//div[contains(@data-bind,'click: emailReport')]")),
				driver);
		WebElement emailBtn = driver
				.findElement(By.xpath("//*[@id='optionsForInnroad']//div[contains(@data-bind,'click: emailReport')]"));
		emailBtn.click();

		Wait.explicit_wait_visibilityof_webelement(
				driver.findElement(By.xpath("//*[@id='optionsForInnroad']//input[@placeholder='Email Address']")),
				driver);
		driver.findElement(By.xpath("//*[@id='optionsForInnroad']//input[@placeholder='Email Address']"))
				.sendKeys(email);

		emailBtn.click();
		Wait.wait2Second();

	}

	public double Checkin_CashPayment(WebDriver driver, String PaymentType, String Amount) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Actions action = new Actions(driver);
		double processedamount = 0;
		double endingbalance;

		Wait.explicit_wait_elementToBeClickable(ReservationPage.Click_Checkin, driver);
		ReservationPage.Click_Checkin.click();
		reservationLogger.info("Clicked on CheckIn button");
		Wait.wait2Second();
		try {
			if (ReservationPage.ListRoomDirtyConfirmBtn.size() > 0) {
				ReservationPage.RoomDirtyConfirmBtn.click();
				Wait.wait2Second();
			}
		} catch (Exception e) {

		}

		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Select, driver);
			ReservationPage.Click_Select.click();
			reservationLogger.info("Clicked on select button of room assignment popup");

			Wait.wait2Second();
			if (ReservationPage.ListRoomDirtyConfirmBtn.size() > 0) {
				ReservationPage.RoomDirtyConfirmBtn.click();
				Wait.wait2Second();
			}
			// Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup);
		} catch (Exception e) {
			// Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup);
		}
		try {
			Wait.waitForElementToBeGone(driver, ReservationPage.Click_Select, 30);

		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Select, driver);
			ReservationPage.Click_Select.click();
			Wait.waitForElementToBeGone(driver, ReservationPage.Click_Select, 30);
			reservationLogger.info("Again Clicked on select button of room assignment popup");
		}
		// }
		// Wait.wait10Second();

		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Payment_Popup, driver);
			if (ReservationPage.Payment_Popup.isDisplayed()) {
				reservationLogger.info("Payment popup is displayed");
				CashPayment(driver, PaymentType, Amount);
				reservationLogger.info("Payment process is completed");
			}
		} catch (Exception e) {
			reservationLogger.info("Payment Popup not Appear");
		}

		reservationLogger.info("Trying to Clicking on Confirm button of Guest Registration Form in Reservation.java");
		// Thread.sleep(10000);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_on_confirm, driver);
		ReservationPage.Click_on_confirm.click();
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.Toaster_Message, driver);
		Wait.wait3Second();

		try {
			if (ReservationPage.Key_Generation_Popup.isDisplayed()) {
				ReservationPage.Key_Generation_Close.click();
				Wait.wait15Second();
			}
		} catch (Exception e) {
			reservationLogger.info("Key Geneartion doesnt exist");
		}
		return processedamount;
	}

	public void VerifyReservationDirectFromAccountPage(WebDriver driver, String GuestProfileName)
			throws InterruptedException, IOException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		assertTrue(ReservationPage.Reservation_Referral.isDisplayed(), "Reservation Page didn't display");
		assertTrue(ReservationPage.GuestProfileAttached.getText().equals(GuestProfileName),
				"Account didn't attached successfully");
	}

	public void saveReservation1(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.Click_Save_ReservationDetails.click();
		Wait.wait2Second();
		try {
			if (ReservationPage.Verify_Depos_policy.isDisplayed()) {
				ReservationPage.Click_Without_Deposit.click();
			}
		} catch (Exception ne) {

		}
		try {
			if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
				String getTotasterTitle_ReservationSucess = ReservationPage.Toaster_Title.getText();
				String getToastermessage_ReservationSucess = ReservationPage.Toaster_Message.getText();
				Assert.assertEquals(getTotasterTitle_ReservationSucess, "Reservation Saved");
				if (getToastermessage_ReservationSucess.endsWith("has been saved successfully"))
					;
			}
		} catch (Exception e) {

		}
		Wait.wait5Second();
	}

	public void verify_NewReservationTab(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Wait.wait5Second();
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.New_Reservation_Tab);
		Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
		if (driver.findElements(By.xpath(OR.New_Reservation_Tab)).size() > 0) {
			test_steps.add("New Reservation Tab opened successfully");
		} else {
			test_steps.add("New Reservation Tab not opened");
		}
	}

	public void verifyGroupName(WebDriver driver, ArrayList test_steps, String group) {
		String GroupName = "//a[contains(text(),'" + group + "')]";
		Wait.WaitForElement(driver, GroupName);
		test_steps.add("Successfully verified the group name in group reservation : " + group);
	}

	public void click_Pay(WebDriver driver, ArrayList test_steps) throws InterruptedException {

		Elements_Reservation res = new Elements_Reservation(driver);
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOf(res.Reservation_Folio_Pay));
		Wait.explicit_wait_visibilityof_webelement(res.Reservation_Folio_Pay, driver);
		Wait.explicit_wait_elementToBeClickable(res.Reservation_Folio_Pay, driver);
		Utility.ScrollToElement(res.Reservation_Folio_Pay, driver);
		Wait.WaitForElement(driver, OR.Reservation_Folio_Pay);
		res.Reservation_Folio_Pay.click();
		test_steps.add("Successfully clicked on Pay");
		reservationLogger.info("Successfully clicked on Pay");

	}

	public void select_GroupAccountToPay(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		int count = driver.findElements(By.xpath("//select[@class='form-control payment-method-dropdown']")).size();
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(By.xpath("(//select[@class='form-control payment-method-dropdown'])[" + count + "]"))));
		Select sel = new Select(driver
				.findElement(By.xpath("(//select[@class='form-control payment-method-dropdown'])[" + count + "]")));
		sel.selectByIndex(1);
		test_steps.add("Successfully selected to  account to pay");
		reservationLogger.info("Successfully selected to  account to pay");
		Wait.wait3Second();
		Wait.WaitForElement(driver, "(//button[contains(text(),'Add')])[2]");
		driver.findElement(By.xpath("(//button[contains(text(),'Add')])[2]")).click();
		test_steps.add("Successfully clicked on Add");
		reservationLogger.info("Successfully clicked on Add");
		Wait.wait10Second();
		Wait.WaitForElement(driver, "//button[@class='btn blue btn-payment-continue']");
		driver.findElement(By.xpath("//button[@class='btn blue btn-payment-continue']")).click();
		test_steps.add("Successfully clicked on Continue");
		reservationLogger.info("Successfully clicked on Continue");
		Wait.wait3Second();
		saveReservation(driver);
		Wait.wait3Second();
	}

	public void select_UnitAccountToPay(WebDriver driver, ArrayList test_steps) throws InterruptedException {

		int count = driver.findElements(By.xpath("//select[@class='form-control payment-method-dropdown']")).size();

		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(By.xpath("(//select[@class='form-control payment-method-dropdown'])[" + count + "]"))));
		Select sel = new Select(driver
				.findElement(By.xpath("(//select[@class='form-control payment-method-dropdown'])[" + count + "]")));
		sel.selectByIndex(2);
		test_steps.add("Successfully selected to  account to pay");
		reservationLogger.info("Successfully selected to  account to pay");
		Wait.wait5Second();
		Wait.WaitForElement(driver,
				"(//span[contains(text(),'Payment Details')]/../../../../../../../..//button[contains(text(),'Add')])");
		driver.findElement(By
				.xpath("(//span[contains(text(),'Payment Details')]/../../../../../../../..//button[contains(text(),'Add')])"))
				.click();
		test_steps.add("Successfully clicked on Add");
		reservationLogger.info("Successfully clicked on Add");

		Wait.wait3Second();
		Wait.WaitForElement(driver,
				"//span[contains(text(),'Payment Details')]/../../../../../../../..//button[contains(text(),'Continue')]");
		driver.findElement(By
				.xpath("//span[contains(text(),'Payment Details')]/../../../../../../../..//button[contains(text(),'Continue')]"))
				.click();
		test_steps.add("Successfully clicked on Continue");
		reservationLogger.info("Successfully clicked on Continue");
		SaveRes_Updated(driver);
		Wait.wait3Second();
	}

	// label[contains(text(),'Payment
	// Method:')]/../following-sibling::div/select
	public void select_HouseAccountPayment(WebDriver driver, ArrayList test_steps, String accountName)
			throws InterruptedException {

		String paymentDropdown = "(//label[contains(text(),'Payment Method:')])[2]/../following-sibling::div/select";
		Wait.WaitForElement(driver, paymentDropdown);

		new Select(driver.findElement(By.xpath(paymentDropdown))).selectByVisibleText("House Account");
		test_steps.add("select House Account");
		String houseaccountpicker = "//span[contains(text(),'House Account Picker')]";
		Wait.WaitForElement(driver, houseaccountpicker);

		String account = "(//label[contains(text(),'Name:')]/../following-sibling::div/input)[3]";
		Wait.WaitForElement(driver, account);
		driver.findElement(By.xpath(account)).sendKeys(accountName);
		test_steps.add("Enter house account anme : " + accountName);

		String search = "(//button[contains(text(),'Search')])[6]";
		Wait.WaitForElement(driver, search);
		driver.findElement(By.xpath(search)).click();
		test_steps.add("Click on search");

		String radio = "//input[@name='account']";
		Wait.WaitForElement(driver, radio);
		driver.findElement(By.xpath(radio)).click();
		test_steps.add("select house acount");

		String select = "(//button[contains(text(),'Select')])[18]";
		Wait.WaitForElement(driver, select);
		driver.findElement(By.xpath(select)).click();
		test_steps.add("click on select");
		Wait.wait3Second();
		new Select(driver.findElement(By.xpath(paymentDropdown))).selectByIndex(1);
		test_steps.add("select houese acccount for payment : " + accountName);
	}

	public void pay(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Wait.wait5Second();
		Wait.WaitForElement(driver, "(//button[contains(text(),'Add')])[5]");
		driver.findElement(By.xpath("(//button[contains(text(),'Add')])[5]")).click();
		test_steps.add("Successfully clicked on Add");
		reservationLogger.info("Successfully clicked on Add");
		Wait.wait5Second();
		Wait.WaitForElement(driver, "(//button[@class='btn blue btn-payment-continue'])[2]");
		driver.findElement(By.xpath("(//button[@class='btn blue btn-payment-continue'])[2]")).click();
		test_steps.add("Successfully clicked on Continue");
		reservationLogger.info("Successfully clicked on Continue");
		Wait.wait3Second();
		saveReservation(driver);
		Wait.wait3Second();
		test_steps.add("Successfully completed payment with house account");
	}

	public void verifyRoomChargeLineItem(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_Reservation res = new Elements_Reservation(driver);
		Wait.wait2Second();
		if (driver.findElements(By.xpath(OR.Reservation_RoomChargeLineItem)).size() > 0) {
			test_steps.add("RoomChage line item found");
		} else {
			test_steps.add("RoomChage line item not found ");
		}
	}

	public void selectAccountFolio(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Wait.wait2Second();
		Elements_Reservation res = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.Reservation_Folio_DropDown);
		new Select(res.Reservation_Folio_DropDown).selectByIndex(1);
		test_steps.add("Selected Accoutn Folio");
	}

	public void selectAllLineItems(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Wait.wait3Second();
		Elements_Reservation res = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", res.Reservation_Folio_Select_AllLineItems);
		Wait.WaitForElement(driver, OR.Reservation_Folio_Select_AllLineItems);
		res.Reservation_Folio_Select_AllLineItems.click();
		test_steps.add("Selected All Folio line items");
	}

	public void selectGuestFolio(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Wait.wait2Second();
		Elements_Reservation res = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.Reservation_Folio_DropDown);
		new Select(res.Reservation_Folio_DropDown).selectByIndex(0);
		test_steps.add("Selected Guest Folio");
	}

	public void click_ApplyRouting(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Wait.wait2Second();
		Elements_Reservation res = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.Reservation_Folio_ApplyRouting);
		if (res.Reservation_Folio_ApplyRouting.isEnabled()) {
			res.Reservation_Folio_ApplyRouting.click();
			test_steps.add("Click on Apply Routing");
			String ok = "//h4[contains(text(),'Confirm')]/../following-sibling::div/button[2]";
			Wait.WaitForElement(driver, ok);
			driver.findElement(By.xpath(ok)).click();
			test_steps.add("Click on OK");
			saveReservation(driver);

		} else {
			test_steps.add("Apply Routing is diaabled");
		}
	}

	public void closeReservation(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Wait.wait5Second();
		Elements_Reservation res = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.OpenedReservation_CloseButton);
		res.OpenedReservation_CloseButton.click();
		Wait.wait2Second();
		test_steps.add("Clicked on close button");
	}

	public void closeFirstReservation(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Wait.wait2Second();
		Elements_Reservation res = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.OpenedReservation1_CloseButton);
		res.OpenedReservation1_CloseButton.click();
		test_steps.add("Click on close reservation1");
	}

	public void verify_AddedLineItem(WebDriver driver, ArrayList test_steps) {

		String line = "//td[@class='lineitem-category']/span";

		int count = driver.findElements(By.xpath(line)).size();
		String text = null;
		for (int i = 1; i <= count; i++) {
			line = "(//td[@class='lineitem-category']/span)[" + i + "]";
			text = driver.findElement(By.xpath(line)).getText().trim();
			if (!text.equalsIgnoreCase("Room Charge")) {
				test_steps.add("Linr item : " + text + " found");
			} else {
				test_steps.add("Linr item : " + text + " not found");
			}
		}
	}

	public void display_VoidedLineItems(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_Reservation res = new Elements_Reservation(driver);
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", res.Reservation_Folio_DisplayVoidItems);
		Wait.wait2Second();
		if (!res.Reservation_Folio_DisplayVoidItems.isSelected()) {
			res.Reservation_Folio_DisplayVoidItems.click();
			test_steps.add("Select display void items");

			String day = "//img[@src='./Folio_Images/3.gif']/../../td[4]/span";
			String type = "//img[@src='./Folio_Images/3.gif']/../../td[7]/span";
			String bal = "//img[@src='./Folio_Images/3.gif']/../../td[11]/span";
			String dayText, TypeText, balText;

			int count = driver.findElements(By.xpath(day)).size();
			for (int i = 1; i <= count; i++) {
				day = "(//img[@src='./Folio_Images/3.gif']/../../td[4]/span)[" + i + "]";
				type = "(//img[@src='./Folio_Images/3.gif']/../../td[7]/span)[" + i + "]";
				bal = "(//img[@src='./Folio_Images/3.gif']/../../td[11]/span)[" + i + "]";
				dayText = driver.findElement(By.xpath(day)).getText().trim();
				TypeText = driver.findElement(By.xpath(type)).getText().trim();
				balText = driver.findElement(By.xpath(bal)).getText().trim();
				if (balText.contains("$ 0.00")) {
					test_steps.add("Line Item : " + TypeText + " voided for " + dayText);
				}
			}
		}
	}

	public void verify_Moltirooms(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		String multi = "//span[contains(text(),'Multiple Rooms')]";
		Wait.WaitForElement(driver, multi);
		Wait.wait2Second();
		Wait.WaitForElement(driver, multi);
		if (driver.findElement(By.xpath(multi)).isDisplayed()) {
			test_steps.add("Room Number is : Multiple Rooms");
		}
	}

	public void VerifyArrive(WebDriver driver, String Expectedvalue) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.Reservation_SelectedArrival, driver);
		String value = ReservationPage.Reservation_SelectedArrival.getText();
		System.out.println("Arrive : " + value);
		assertTrue(value.contains(Expectedvalue), "Failed: Arrive Date missmatched");
	}

	public void VerifyDepart(WebDriver driver, String Expectedvalue) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.Reservation_SelectedNights, driver);
		String value = ReservationPage.Reservation_SelectedNights.getText();
		System.out.println("Depart : " + value);
		value = value.split(" -")[0];
		System.out.println("Depart : " + value);
		assertTrue(value.contains(Expectedvalue), "Failed: Depart Date missmatched");
	}

	public ArrayList<String> Verify_GuestFirstName(WebDriver driver, ArrayList<String> steps, String Manadatory_Color)
			throws InterruptedException {
		Elements_Reservation res = new Elements_Reservation(driver);
		// Verify Manadatory Fields
		Utility.ScrollToElement(res.Enter_First_Name, driver);
		String Color = res.Enter_First_Name.getCssValue("border-color");
		assertTrue(Color.equals(Manadatory_Color),
				"Failed: Contact Information: Guest: First Namel is not Highlighted");
		steps.add("Contact Information: Guest: First Name is Highlighted");
		reservationLogger.info("Contact Information: Guest: First Name " + Color);
		return steps;

	}

	public ArrayList<String> SelectReferral(WebDriver driver, String Referral, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Reservation_Referral, driver);
		Utility.ScrollToElement(ReservationPage.Reservation_Referral, driver);

		new Select(ReservationPage.Reservation_Referral).selectByVisibleText(Referral);
		test_steps.add("Successfully selected the referral : " + Referral);
		return test_steps;
	}

	public void EnterFirstName(WebDriver driver, String FirstName) throws InterruptedException {

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_Reservation.Enter_First_Name, driver);
		Utility.ScrollToElement(elements_Reservation.Enter_First_Name, driver);
		elements_Reservation.Enter_First_Name.clear();

		elements_Reservation.Enter_First_Name.sendKeys(FirstName);

	}

	public void EnterEmail(WebDriver driver, String Email) throws InterruptedException {

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		Utility.ScrollToElement(elements_Reservation.Enter_email, driver);
		elements_Reservation.Enter_email.clear();
		elements_Reservation.Enter_email.sendKeys(Email);

	}

	public void VerifyRatePlan(WebDriver driver, String tCRatePlan) throws InterruptedException {

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_Reservation.Reservation_RatePlan, driver);
		Utility.ScrollToElement(elements_Reservation.Reservation_RatePlan, driver);
		String SelectedRatePlan = new Select(elements_Reservation.Reservation_RatePlan).getFirstSelectedOption()
				.getText();
		assertEquals(SelectedRatePlan.replaceAll(" ", ""), tCRatePlan.replaceAll(" ", ""),
				"Failed : Policy is not selected");

	}

	public String getLineItemAmount(WebDriver driver, String Category, boolean WithTax) throws InterruptedException {
		Elements_Reservation res = new Elements_Reservation(driver);
		Utility.ScrollToElement(res.Include_Taxes_in_Line_Items_Checkbox, driver);
		if (WithTax) {
			if (!res.Include_Taxes_in_Line_Items_Checkbox.isSelected()) {
				res.Include_Taxes_in_Line_Items_Checkbox.click();
				reservationLogger.info("UnCheck Include Taxes in Line Item CheckBox");
			}
		} else if (res.Include_Taxes_in_Line_Items_Checkbox.isSelected()) {
			res.Include_Taxes_in_Line_Items_Checkbox.click();
			reservationLogger.info("Check Include Taxes in Line Item CheckBox");
		}
		assertEquals(WithTax, res.Include_Taxes_in_Line_Items_Checkbox.isSelected(),
				"Failed : Include Taxes in Line Item CheckBox is not in Required State");
		String Amount = driver.findElement(By.xpath("//span[contains(text(),'" + Category
				+ "')]//parent::td//following-sibling::td[@class='lineitem-amount']/span")).getText();
		Amount = Amount.split(" ")[1];
		Utility.app_logs.info("Line Item Amount containing description " + Category + " = " + Amount);
		return Amount;
	}

	public ArrayList<String> VerifyNewReservationElements(WebDriver driver, ArrayList<String> steps, String Pre_Country,
			String Pre_CountryCode, String Manadatory_Color) throws InterruptedException {

		Elements_Reservation res = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.Reservation_CreateGuestProfile_CheckBox, driver);
		Utility.ScrollToElement(res.Reservation_CreateGuestProfile_CheckBox, driver);
		assertTrue(res.Reservation_CreateGuestProfile_CheckBox.isSelected(),
				"Failed: Create Guest Profile is Not Selected");
		steps.add("Contact Information: Create Guest Profile CheckBox box is checked");
		reservationLogger.info("Contact Information: Create Guest Profile CheckBox box is checked");
		Utility.ScrollToElement(res.Reservation_SameAsContactInfo_CheckBox, driver);
		assertTrue(res.Reservation_SameAsContactInfo_CheckBox.isSelected(),
				"Failed: SameAsContactInfo CheckBox is Not Selected");
		steps.add("Contact Information: SameAsContactInfo CheckBox is checked");
		reservationLogger.info("Contact Information: SameAsContactInfo CheckBox is checked");
		Utility.ScrollToElement(res.ContactInfo_Country, driver);
		assertTrue(new Select(res.ContactInfo_Country).getFirstSelectedOption().getText().contains(Pre_Country),
				"Failed: Contact Information: Country is not pre populated with United States");
		steps.add("Contact Information: Country is pre populated with United States");
		reservationLogger.info("Contact Information: Country is pre populated with United States");
		Utility.ScrollToElement(res.ContactInfo_PhoneCountryCode, driver);
		assertTrue(res.ContactInfo_PhoneCountryCode.getAttribute("value").contains(Pre_CountryCode),
				"Failed: Contact Information: Phone Country code is not pre populated with '1' ");
		steps.add("Contact Information: Phone Country code is pre populated with '1' ");
		reservationLogger.info("Contact Information: Phone Country code is pre populated with '1' ");
		Utility.ScrollToElement(res.ContactInfo_AltPhoneCountryCode, driver);
		assertTrue(res.ContactInfo_AltPhoneCountryCode.getAttribute("value").contains(Pre_CountryCode),
				"Failed: Contact Information: ALternate Phone Country code is not pre populated with '1' ");
		steps.add("Contact Information:  ALternate Phone Country code is pre populated with '1' ");
		reservationLogger.info("Contact Information:  ALternate Phone Country code is pre populated with '1' ");
		Utility.ScrollToElement(res.ContactInfo_FaxCountryCode, driver);
		assertTrue(res.ContactInfo_FaxCountryCode.getAttribute("value").contains(Pre_CountryCode),
				"Failed: Contact Information: Fax: Country code is not pre populated with '1' ");
		steps.add("Contact Information: Fax: Country code is pre populated with '1' ");
		reservationLogger.info("Contact Information: Fax: Country code is pre populated with '1' ");
		// Verify Manadatory Fields
		Utility.ScrollToElement(res.Reservation_Referral, driver);
		String Color = res.Reservation_Referral.getCssValue("border-color");
		assertTrue(Color.equals(Manadatory_Color), "Failed: Marketing Information: Referral is not Highlighted");
		steps.add("Marketing Information: Referral is Highlighted");
		reservationLogger.info("Marketing Information: Referral " + Color);

		Utility.ScrollToElement(res.Reservation_market_Segment, driver);
		Color = res.Reservation_market_Segment.getCssValue("border-color");
		// assertTrue(Color.equals(Manadatory_Color),"Failed: Marketing
		// Information: Market Segment is not Highlighted");
		// steps.add("Marketing Information: Market Segment is Highlighted");
		// reservationLogger.info("Marketing Information: Market Segment " +
		// Color);

		Utility.ScrollToElement(res.Enter_First_Name, driver);
		Color = res.Enter_First_Name.getCssValue("border-color");
		assertTrue(Color.equals(Manadatory_Color),
				"Failed: Contact Information: Guest: First Namel is not Highlighted");
		steps.add("Contact Information: Guest: First Name is Highlighted");
		reservationLogger.info("Contact Information: Guest: First Name " + Color);

		Utility.ScrollToElement(res.Enter_Last_Name, driver);
		Color = res.Enter_Last_Name.getCssValue("border-color");
		assertTrue(Color.equals(Manadatory_Color), "Failed: Contact Information: Guest: Last Name is not  Highlighted");
		steps.add("Contact Information: Guest: Last Name is Highlighted");
		reservationLogger.info("Contact Information: Guest: Last Name " + Color);

		Utility.ScrollToElement(res.Enter_Contact_First_Name, driver);
		Color = res.Enter_Contact_First_Name.getCssValue("border-color");
		assertTrue(Color.equals(Manadatory_Color),
				"Failed: Contact Information: Contact: First Name is not Highlighted");
		steps.add("Contact Information: Contact: First Name is Highlighted");
		reservationLogger.info("Contact Information: Contact: First Name " + Color);

		Utility.ScrollToElement(res.Enter_Line1, driver);
		Color = res.Enter_Line1.getCssValue("border-color");
		assertTrue(Color.equals(Manadatory_Color), "Failed: Contact Information: Address: Line 1 is not Highlighted");
		steps.add("Contact Information: Address: Line 1 is Highlighted");
		reservationLogger.info("Contact Information: Address: Line 1 " + Color);

		Utility.ScrollToElement(res.Enter_City, driver);
		Color = res.Enter_City.getCssValue("border-color");
		assertTrue(Color.equals(Manadatory_Color), "Failed: Contact Information: City: City is not Highlighted");
		steps.add("Contact Information: City: City is Highlighted");
		reservationLogger.info("Contact Information: City: City " + Color);

		Utility.ScrollToElement(res.Select_State, driver);
		Color = res.Select_State.getCssValue("border-color");
		assertTrue(Color.equals(Manadatory_Color), "Failed: Contact Information: State is not Highlighted");
		steps.add("Contact Information: State is Highlighted");
		reservationLogger.info("Contact Information: State " + Color);

		Utility.ScrollToElement(res.Enter_Postal_Code, driver);
		Color = res.Enter_Postal_Code.getCssValue("border-color");
		assertTrue(Color.equals(Manadatory_Color), "Failed: Contact Information: Postal Code is not Highlighted");
		steps.add("Contact Information: Postal Code is Highlighted");
		reservationLogger.info("Contact Information: Postal Code " + Color);

		Utility.ScrollToElement(res.Enter_Phone_Number, driver);
		Color = res.Enter_Phone_Number.getCssValue("border-color");
		assertTrue(Color.equals(Manadatory_Color), "Failed: Contact Information: Phone: Number is not Highlighted");
		steps.add("Contact Information: Phone: Number is Highlighted");
		reservationLogger.info("Contact Information: Phone: Number " + Color);

		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();",
		// ReservationPage.New_Reservation_Button);

		return steps;

	}

	public ArrayList<String> RoomAssignment_VerifyManadatoryFields(WebDriver driver, String RoomClass,
			String Manadatory_Color, String PreChild, ArrayList<String> test_steps) throws InterruptedException {

		// Room Assignment Popup initial expected Conditions
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// Property
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Select_property_RoomAssign, driver);
		Utility.ScrollToElement(ReservationPage.Select_property_RoomAssign, driver);
		Select property = new Select(ReservationPage.Select_property_RoomAssign);
		List<WebElement> PropertyOptions = property.getOptions();
		assertEquals(PropertyOptions.get(0).getText(), property.getFirstSelectedOption().getText(),
				"Failed:  Property is not pre populated with the first property in the list");
		test_steps.add("Property is pre populated with the first property in the list");
		reservationLogger.info("Property  is pre populated with the first property in the list");
		// Date Arrived
		Utility.ScrollToElement(ReservationPage.RoomAssignment_DateArrived, driver);
		String Color = ReservationPage.RoomAssignment_DateArrived.getCssValue("border-color");
		assertTrue(Color.equals(Manadatory_Color), "Failed: Mandatory Field: Arrive is not Highlighted");
		test_steps.add(" Mandatory Field: Arrive is Highlighted");
		reservationLogger.info(" Mandatory Field: Arrive " + Color);
		// Date Departed
		Utility.ScrollToElement(ReservationPage.RoomAssignment_DateDepart, driver);
		Color = ReservationPage.RoomAssignment_DateDepart.getCssValue("border-color");
		assertTrue(Color.equals(Manadatory_Color), "Failed:  Mandatory Field: Depart is not Highlighted");
		test_steps.add("Mandatory Field: Depart is Highlighted");
		reservationLogger.info("Mandatory Field: Depart " + Color);
		// Nights
		Utility.ScrollToElement(ReservationPage.Enter_Nigts, driver);
		Color = ReservationPage.Enter_Nigts.getCssValue("border-color");
		assertTrue(Color.equals(Manadatory_Color), "Failed: Mandatory Field: Nights is not Highlighted");
		test_steps.add("Mandatory Field: Nights is Highlighted");
		reservationLogger.info("Mandatory Field: Nights " + Color);
		// Adults
		Utility.ScrollToElement(ReservationPage.Enter_Adults, driver);
		Color = ReservationPage.Enter_Adults.getCssValue("border-color");
		assertTrue(Color.equals(Manadatory_Color), "Failed: Mandatory Field: Adults is not Highlighted");
		test_steps.add("Mandatory Field: Adults is Highlighted");
		reservationLogger.info("Mandatory Field: Adults " + Color);

		// Children Pre_Condition
		Utility.ScrollToElement(ReservationPage.Enter_Children, driver);
		assertEquals(ReservationPage.Enter_Children.getAttribute("value"), PreChild,
				"Failed:  Children is not  pre populated with 0");
		test_steps.add("Children is pre populated with 0");
		reservationLogger.info("Children is pre populated with 0");
		// Assign Room CheckBox
		Utility.ScrollToElement(ReservationPage.Check_Assign_Room, driver);
		assertTrue(ReservationPage.Check_Assign_Room.isSelected(), "Failed : Assign Room CheckBox is not Checked");
		test_steps.add("Assign Room: Checked");
		return test_steps;
	}

	public String GetLineitemTax(WebDriver driver, String Category) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// Wait.wait3Second();

		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		String taxPath = "(//table[contains(@class, 'trHeight25')]//span[contains(@data-bind,data.Category) and text()='"
				+ Category + "']//parent::td//following-sibling::td[@class='lineitem-tax']/span)[last()]";
		WebElement TaxAdded = driver.findElement(By.xpath(taxPath));
		String taxAndServiceCharges = TaxAdded.getText();
		String taxAndServiceCharges1 = TaxAdded.getAttribute("value");

		System.out.println("taxAndServiceCharges:" + taxAndServiceCharges1);

		taxAndServiceCharges = taxAndServiceCharges.replace("$", "").trim();
		return taxAndServiceCharges;
	}

	public ArrayList<String> ClickRoomAssignment_VerifyManadatoryFields(WebDriver driver, String ArriveDate,
			String DepartDate, String RoomClass, String Manadatory_Color, String PreChild, String Nights, String Adults,
			String Children, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.Click_RoomPicker, driver);
		Utility.ScrollToElement(ReservationPage.Click_RoomPicker, driver);
		Thread.sleep(1000);
		ReservationPage.Click_RoomPicker.click();
		test_steps.add("Click on Rooms Picker");
		reservationLogger.info("Successfully clicked on Rooms Picker");
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.Click_Arrive_Datepicker, driver);
		test_steps.add("Room assignment popup appears");

		// Room Assignment Popup initial expected Conditions
		ArrayList<String> steps = new ArrayList<>();
		RoomAssignment_VerifyManadatoryFields(driver, RoomClass, Manadatory_Color, PreChild, steps);
		test_steps.addAll(steps);

		// Date Arrived
		Utility.ScrollToElement(ReservationPage.RoomAssignment_DateArrived, driver);
		ReservationPage.RoomAssignment_DateArrived.clear();
		ReservationPage.RoomAssignment_DateArrived.sendKeys(ArriveDate);
		test_steps.add("Enter Arrive Date " + ArriveDate);
		reservationLogger.info("Enter Arrive Date " + ArriveDate);
		// Date Departed
		Utility.ScrollToElement(ReservationPage.RoomAssignment_DateDepart, driver);
		ReservationPage.RoomAssignment_DateDepart.clear();
		ReservationPage.RoomAssignment_DateDepart.sendKeys(DepartDate);
		test_steps.add("Enter Arrive Date " + DepartDate);
		reservationLogger.info("Enter Arrive Date " + DepartDate);
		// ReservationPage.Click_Arrive_Datepicker.click();
		// reservationLogger.info("Successfully clicked on arrival date");
		// ReservationPage.Click_Today.click();
		// test_steps.add("Select Date: Today");
		// reservationLogger.info("Successfully clicked on Today");
		Utility.ScrollToElement(ReservationPage.Check_Split_Rooms, driver);
		if (ReservationPage.Check_Split_Rooms.isSelected()) {
			ReservationPage.Check_Split_Rooms.click();
			test_steps.add("Click Split Room");
			reservationLogger.info("Click Split Room");
		}
		assertTrue(!ReservationPage.Check_Split_Rooms.isSelected());
		test_steps.add("Split Room: UnChecked");
		Utility.ScrollToElement(ReservationPage.Check_Assign_Room, driver);
		if (ReservationPage.Check_Assign_Room.isSelected()) {
			ReservationPage.Check_Assign_Room.click();
			test_steps.add("Click Assign Room");
			reservationLogger.info("Click Assign Room");
		}
		assertTrue(!ReservationPage.Check_Assign_Room.isSelected());
		test_steps.add("Assign Room: UnChecked");

		// Nights
		Utility.ScrollToElement(ReservationPage.Enter_Nigts, driver);
		ReservationPage.Enter_Nigts.clear();
		ReservationPage.Enter_Nigts.sendKeys(Nights);
		test_steps.add("Enter Nights : " + Nights);
		reservationLogger.info("Enter Nights : " + Nights);
		// Adults
		Utility.ScrollToElement(ReservationPage.Enter_Adults, driver);
		ReservationPage.Enter_Adults.clear();
		ReservationPage.Enter_Adults.sendKeys(Adults);
		test_steps.add("Enter Adults : " + Adults);
		reservationLogger.info("Enter Adults : " + Adults);

		// Children Pre_Condition
		Utility.ScrollToElement(ReservationPage.Enter_Children, driver);
		ReservationPage.Enter_Children.clear();
		ReservationPage.Enter_Children.sendKeys(Children);
		test_steps.add("Enter Children : " + Children);
		reservationLogger.info("Enter Children : " + Children);

		Utility.ScrollToElement(ReservationPage.Click_Search, driver);
		ReservationPage.Click_Search.click();
		test_steps.add("Click Search Rooms");
		reservationLogger.info("Click Search");

		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.SelectRoomClasses, driver);

		assertEquals(ReservationPage.RoomAssignment_SearchedRow.size(), Integer.parseInt(Nights),
				"Failed: Search does not returns Rows equals to Selected Nights ");
		test_steps.add("Search returns Number of Rows equals to Selected Nights ");
		reservationLogger.info("Search returns  Number of Rows equals to Selected Nights ");

		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
		assertTrue(!ReservationPage.SelectRoomNumbers.isEnabled(), "Failed: Room Number Drop Down not  Disabled");
		test_steps.add("Room Number Drop Down is Disabled");
		reservationLogger.info("Room Number Drop Down is Disabled");

		String RoomName = new Select(ReservationPage.SelectRoomClasses).getFirstSelectedOption().getText();
		test_steps.add("Select Room  " + RoomName);
		reservationLogger.info("Select Room " + RoomName);
		assertTrue(RoomName.contains(RoomClass), "Failed: Room Class is not " + RoomClass);

		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		test_steps.add("Available Rooms  " + CheckRoom);
		reservationLogger.info("Available Rooms  " + CheckRoom);
		Utility.ScrollToElement(ReservationPage.SelectButton, driver);
		ReservationPage.SelectButton.click();
		test_steps.add("Click Select Room");
		reservationLogger.info("Click Select");
		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			ReservationPage.RoleBroken_Continue.click();

		}
		Wait.waitForElementToBeGone(driver, ReservationPage.SelectButton, 60);
		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);

		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Toaster_Message, driver);
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		ReservationPage.Toaster_Message.click();

		Wait.explicit_wait_xpath(OR.Click_NewFolio, driver);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.Click_NewFolio)), driver);
		assertTrue(driver.findElement(By.xpath(OR.Click_NewFolio)).isDisplayed(),
				"Failed: User  did not lands on Folio tab");

		test_steps.add("User lands on Folio tab");
		reservationLogger.info("User lands on Folio tab");
		return test_steps;
	}

	public void VerifyFolioBalance(WebDriver driver, String Value) throws InterruptedException {
		Elements_Reservation res = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.Reservation_FolioBalance, driver);
		Utility.ScrollToElement(res.Reservation_FolioBalance, driver);
		String Balance = res.Reservation_FolioBalance.getText();
		Balance = Balance.split(" ")[1];
		assertEquals(Balance, Value, "Failed: Folio Balance Missmatched");
		Double d = Double.parseDouble(Balance);
		reservationLogger.info("Folio Balance : " + d);
	}

	public void VerifyChildren(WebDriver driver, String children) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.Reservation_SelectedChildren, driver);
		String value = ReservationPage.Reservation_SelectedChildren.getText();
		System.out.println("Children : " + value);
		assertTrue(value.contains(children), "Failed: Children missmatched");
	}

	public void VerifyAdults(WebDriver driver, String Expectedvalue) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.Reservation_SelectedAdults, driver);
		String value = ReservationPage.Reservation_SelectedAdults.getText();
		System.out.println("Adults : " + value);
		assertTrue(value.contains(Expectedvalue), "Failed: Adults missmatched");
	}

	public void VerifyNights(WebDriver driver, String Expectedvalue) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.Reservation_SelectedNights, driver);
		String value = ReservationPage.Reservation_SelectedNights.getText();
		System.out.println("Nights : " + value);
		value = value.split("-")[1];
		System.out.println("Nights : " + value);
		value = value.replace("Night(s)", "").replace(" ", "");
		System.out.println("Nights : " + value);
		assertTrue(value.equals(Expectedvalue), "Failed: Nights missmatched");
	}

	public void verifyTaxAndServiceCharges(WebDriver driver, String newRateValue, boolean isfloat)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.Reservation_TaxAndServiceCharges, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-300)");
		Wait.wait2Second();
		if (!isfloat) {

			newRateValue = "$ " + newRateValue + ".00";
			System.out.println("expected:" + ReservationPage.Reservation_TaxAndServiceCharges.getText() + "Calculated:"
					+ newRateValue);
			assertTrue(ReservationPage.Reservation_TaxAndServiceCharges.getText().equals(newRateValue),
					"Failed : Room Charges are not the updted one");
		} else {
			newRateValue = "$ " + newRateValue;
			System.out.println("expected:" + ReservationPage.Reservation_TaxAndServiceCharges.getText() + "Calculated:"
					+ newRateValue);

			assertTrue(ReservationPage.Reservation_TaxAndServiceCharges.getText().equals(newRateValue),
					"Failed : Room Charges are not the updted one");
		}

	}

	public String GetReservationNum(WebDriver driver) throws IOException, InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Get_Confirmation_Number, driver);
		String getResNum = ReservationPage.Get_Confirmation_Number.getText();
		reservationLogger.info("ReservationConfirmation :" + getResNum);

		return getResNum;

	}

	public ArrayList<String> VerifyLineItemInPaymentDetailPopup(WebDriver driver, String PaymentType,
			String PaymentDetail, String ChangeAmountValue, boolean isDetail_Link_Check, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);

		// *[@id="bpjscontainer_28"]//div[@class='modules_loading']

		Thread.sleep(1000);
		Wait.wait3Second();
		Wait.WaitForElement(driver, OR.Verify_MC_Grid);
		assertTrue(ReservationFolio.Verify_MC_Grid.isDisplayed(), "MC line item didn't verify in payment page");
		String GetPaymentMethod = ReservationFolio.GetMC_Payment_Method.getText();
		Utility.app_logs.info("PaymentMethod: " + " " + GetPaymentMethod);
		Utility.app_logs.info("PaymentTyepe: " + " " + PaymentType);

		assertTrue(GetPaymentMethod.equals(PaymentType), "Failed: Payment Type Not Matched");
		Utility.app_logs.info("Payment Type Successfully verified");
		test_steps.add("Payment Type Successfully verified");

		if (isDetail_Link_Check) {
			String detail = driver
					.findElement(By
							.xpath("//div[@id='ReservationPaymetItemDetail']//following-sibling::div[@id='reservationList']//a"))
					.getText();
			System.out.println("detail Link yes");

			System.out.println(detail);
			System.out.println(PaymentDetail);

			assertEquals(detail.contains(PaymentDetail), true, "Failed: Payment Detail Not Matched");
			Utility.app_logs.info("Payment Detail Successfully verified");
			test_steps.add("Payment Detail Successfully verified");
		} else {
			String detail = driver
					.findElement(By
							.xpath("//div[@id='ReservationPaymetItemDetail']//following-sibling::div[@id='reservationList']//span[contains(@data-bind,'ItemDetail')]"))
					.getText();
			System.out.println("detail Link no");

			System.out.println(detail);
			System.out.println(PaymentDetail);
			assertEquals(detail.contains(PaymentDetail), true, "Failed: Payment Detail Not Matched");
			Utility.app_logs.info("Payment Detail Successfully verified");
			test_steps.add("Payment Detail Successfully verified");
		}

		String date = driver
				.findElement(By
						.xpath("//div[@id='ReservationPaymetItemDetail']//following-sibling::div[@id='reservationList']//span[@data-bind='text: $data.EffectiveDate']"))
				.getText();
		// Fri, 09-Aug-2019
		assertTrue(date.equalsIgnoreCase(getCurrDate("EEE, dd-MMM-yyyy")), "Failed: Payment Detail Not Matched");
		Utility.app_logs.info("Payment Date Successfully verified");
		test_steps.add("Payment Date Successfully verified");

		Wait.wait1Second();
		Utility.ScrollToElement(ReservationFolio.Processed_Amount_In_Paymentdetails_Popup, driver);
		Wait.wait2Second();
		String Processed_Amount = ReservationFolio.Processed_Amount_In_Paymentdetails_Popup.getText();
		assertEquals(Processed_Amount, "$ " + ChangeAmountValue, "Failed: Amount Not Matched in Payment Detail");
		Utility.app_logs.info("Successfully Verified Processed Amount : " + Processed_Amount);
		test_steps.add("Successfully Verified Processed Amount : " + Processed_Amount);

		String imgPath = "(//tbody[contains(@data-bind,' PaymentItemsArray')]//td[1])[1]/img";
		String img_A_src = driver.findElement(By.xpath(imgPath)).getAttribute("src");

		assertTrue(img_A_src.contains("/Folio_Images/7.gif"),
				"Failed: A (Authorize symbol) in the first column not matched");
		Utility.app_logs.info("Successfully Verified A (Authorize symbol) in the first column");
		test_steps.add("Successfully Verified A (Authorize symbol) in the first column");

		return test_steps;
	}

	public ArrayList<String> clickPayButton(WebDriver driver) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Utility.ScrollToElement(ReservationPage.Click_Pay_Button, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.Click_Pay_Button);
		// ReservationPage.Click_Pay_Button.click();
		Utility.app_logs.info("Click Pay");
		test_steps.add("Click Pay");

		return test_steps;
	}

	public ArrayList<String> fillPaymetDetailWithCard(WebDriver driver, String PaymentType, String CardName,
			String CCNumber, String CCExpiry, String CCVCode, String Authorizationtype, boolean isChangeAmount,
			String ChangeAmountValue) {

		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		try {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);
			assertTrue(elements_All_Payments.Reservation_PaymentPopup.isDisplayed(),
					"Failed: Payment Popup not displayed");
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentPopUp, driver);
			assertTrue(elements_All_Payments.PaymentPopUp.isDisplayed(), "Failed: Payment Popup not displayed");
		}
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		try {
			new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(PaymentType);
			new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText(Authorizationtype);

			Wait.wait3Second();

			Utility.app_logs
					.info(new Select(ReservationFolio.Select_Authorization_type).getFirstSelectedOption().getText());
			assertTrue(new Select(ReservationFolio.Select_Authorization_type).getFirstSelectedOption().getText()
					.contains("Authorization"), "Failed : Authorization is not selected");
			ReservationFolio.Click_Card_info.click();
			Utility.app_logs.info("Clicked on card info");
			Wait.explicit_wait_xpath(OR.Verify_payment_info_popup, driver);
			assertTrue(ReservationFolio.Verify_payment_info_popup.isDisplayed(), "Payment Info Popup didn't display");
			Wait.wait3Second();
			ReservationFolio.Enter_Card_Name.sendKeys(CardName);
			ReservationFolio.Enter_Account_Number_Folio.clear();
			ReservationFolio.Enter_Account_Number_Folio.sendKeys(CCNumber);
			assertTrue(ReservationFolio.Enter_Account_Number_Folio.getAttribute("value").equals(CCNumber),
					"AccountNumber didn't match");
			ReservationFolio.Enter_CC_Expiry.clear();
			ReservationFolio.Enter_CC_Expiry.sendKeys(CCExpiry);
			ReservationFolio.Enter_CVVCode.sendKeys(CCVCode);
			ReservationFolio.Click_OK.click();
			Utility.app_logs.info("Clicked on OK button");
			Wait.wait15Second();
			Wait.waitForElementToBeGone(driver,
					driver.findElement(By.xpath("//*[@id='bpjscontainer_34']//div[@class='modules_loading']")), 30000);
			if (isChangeAmount) {
				ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
			} else {
				Utility.app_logs.info("Processing the amount displayed");
			}
			Utility.ScrollToElement(ReservationFolio.Click_Process, driver);
			ReservationFolio.Click_Process.click();
			Utility.app_logs.info("Clicked on Process Button");
			test_steps.add("Click Process Button");
			Wait.wait15Second();
			Wait.waitForElementToBeGone(driver,
					driver.findElement(By.xpath("//*[@id='bpjscontainer_28']//div[@class='modules_loading']")), 30000);
		} catch (Exception e) {

			Utility.app_logs.info("Exception occured while paying using MC \n");
			e.printStackTrace();
		}
		return test_steps;
	}

	public ArrayList<String> authorizationPicker_Popup(WebDriver driver) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();

		String popupPath = "//*[@id='ReservationAuthPickerPopup']";

		try {
			Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(popupPath)), driver);
			assertTrue(driver.findElement(By.xpath(popupPath)).isDisplayed(),
					"Failed: Authorization Picker Popup not displayed");
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(popupPath)), driver);
			assertTrue(driver.findElement(By.xpath(popupPath)).isDisplayed(),
					"Failed: Authorization Picker Popup not displayed");
		}

		return test_steps;
	}

	public ArrayList<String> verifyAuthorizationPickerItems(WebDriver driver, String row, String Authorizationtype,
			String paymentDetail, String ammount) {
		ArrayList<String> test_steps = new ArrayList<String>();

		String tablePath = "//*[@id='bpjscontainer_36']//tbody/tr[" + row + "]";

		String Date = driver.findElement(By.xpath(tablePath + "/td[2]/span[@data-bind='text: Date']")).getText();
		assertEquals(Date, getCurrDate("MM/dd/yyyy"), "Failed: Date Not Mactched in Authorization Picker");

		String payMethod = driver.findElement(By.xpath(tablePath + "/td[3]/span")).getText();
		assertEquals(payMethod, Authorizationtype, "Failed: Payment Method Not Mactched in Authorization Picker");

		String payDetail = driver.findElement(By.xpath(tablePath + "/td[4]/span[@data-bind='text: DisplayCaption']"))
				.getText();
		// System.out.println(payDetail);
		// System.out.println(paymentDetail);
		assertTrue(payDetail.equalsIgnoreCase(paymentDetail),
				"Failed: Payment Detail Not Mactched in Authorization Picker");

		String payAmmount = driver.findElement(By.xpath(tablePath + "/td[6]/span[@data-bind='text: Amount']"))
				.getText();
		assertEquals(payAmmount, ammount, "Failed: Payment Amount Not Mactched in Authorization Picker");

		return test_steps;
	}

	public String getCurrDate(String customFormate) {
		final SimpleDateFormat format = new SimpleDateFormat(customFormate);
		format.setTimeZone(TimeZone.getTimeZone("PST"));
		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return format.format(calendar.getTime());
	}

	public void authorizationPickerContinue(WebDriver driver) {
		String continueBtnString = "//*[@id='bpjscontainer_36']//button[contains(@data-bind,'click: btnContinue_Click')]";

		WebElement continueBtn = driver.findElement(By.xpath(continueBtnString));
		WebElement eraseBtn = driver.findElement(
				By.xpath("//*[@id='bpjscontainer_36']//button[contains(@data-bind,'click: btnErase_Click')]"));
		if (continueBtn.isEnabled()) {
			continueBtn.click();
		} else if (eraseBtn.isEnabled()) {
			eraseBtn.click();
			if (continueBtn.isEnabled()) {
				continueBtn.click();
			} else {
				assertTrue(false);
			}
		} else {
			assertTrue(false);
		}

	}

	public void authorizationPickerUserPrior(WebDriver driver, String SelectAmount) {

		String path = "//*[@id='bpjscontainer_36']//tbody/tr/td[6]/span[@data-bind='text: Amount'][.='" + SelectAmount
				+ "']/..//preceding-sibling::td/input[@name='SelectedTransaction']";

		WebElement radio = driver.findElement(By.xpath(path));

		radio.click();

		WebElement UserPriorBtn = driver.findElement(
				By.xpath("//*[@id='bpjscontainer_36']//button[contains(@data-bind,'click: btnUsePrior_Click')]"));
		if (UserPriorBtn.isEnabled()) {
			UserPriorBtn.click();
		} else {
			radio.click();
			if (UserPriorBtn.isEnabled()) {
				UserPriorBtn.click();
			} else {
				assertTrue(false);
			}
		}

	}

	public void authorizationPickerCancelPrior(WebDriver driver, String SelectAmount) {

		String path = "//*[@id='bpjscontainer_36']//tbody/tr/td[6]/span[@data-bind='text: Amount'][.='" + SelectAmount
				+ "']/..//preceding-sibling::td/input[@name='SelectedTransaction']";

		WebElement radio = driver.findElement(By.xpath(path));

		radio.click();

		WebElement CancelPriorBtn = driver.findElement(
				By.xpath("//*[@id='bpjscontainer_36']//button[contains(@data-bind,'click: btnCancelPrior_Click')]"));
		if (CancelPriorBtn.isEnabled()) {
			CancelPriorBtn.click();
		} else {
			radio.click();
			if (CancelPriorBtn.isEnabled()) {
				CancelPriorBtn.click();
			} else {
				assertTrue(false);
			}
		}

	}

	public void payDetailContinueBtn(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Click_Continue, driver);
		Utility.ScrollToElement(ReservationFolio.Click_Continue, driver);
		ReservationFolio.Click_Continue.click();
		Utility.app_logs.info("Clicked on continue button");
		Wait.wait3Second();
	}

	public void payDetailCancelBtn(WebDriver driver) throws InterruptedException {

		String cancelPath = "//div[@id='ReservationPaymetItemDetail']//button[contains(@data-bind,'btnCancelVisible(), click: CloseButtonClick')]";
		WebElement cancelBtn = driver.findElement(By.xpath(cancelPath));
		Wait.explicit_wait_visibilityof_webelement(cancelBtn, driver);
		Wait.explicit_wait_elementToBeClickable(cancelBtn, driver);
		Utility.ScrollToElement(cancelBtn, driver);
		cancelBtn.click();
		Utility.app_logs.info("Clicked on continue button");
		Wait.wait3Second();
	}

	public ArrayList<String> verifyFolioPaymentLine(WebDriver driver, String PayCat, String PaymentDetail,
			String amount, boolean isIconCheck) {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);

		String tablePath = "//div[@id='ReservationDetail']//tr[@data-bind='if: $parent.ShouldShowThisItem($data)'][last()]";
		String iconPath = tablePath + "/td[@class='text-center lineitems-changestatus']/img";
		String datePath = tablePath + "/td[@class='lineitem-date']/span";
		String categoryPath = tablePath + "/td[@class='lineitem-category']/span";
		String detailPath = tablePath + "/td[@class='lineitem-description']/a";
		String amountPath = tablePath + "/td[@class='lineitem-amount']/span";

		Wait.WaitForElement(driver, tablePath);
		// assertTrue(driver.findElement(By.xpath(tablePath)).isDisplayed(), "MC
		// line didn't added in folio page");

		String GetMCCard = driver.findElement(By.xpath(detailPath)).getText();
		assertTrue(GetMCCard.equalsIgnoreCase(PaymentDetail), "Failed: Payment Detail not matched");
		Utility.app_logs.info("Successfully Verified Folio Line Detail : " + GetMCCard);
		test_steps.add("Successfully Verified Folio Line Detail : " + GetMCCard);

		String cat = driver.findElement(By.xpath(categoryPath)).getText();
		assertTrue(cat.equalsIgnoreCase(PayCat), "Failed: Payment Category not matched");
		Utility.app_logs.info("Successfully Verified Folio Line Category : " + cat);
		test_steps.add("Successfully Verified Folio Line Category : " + cat);

		String payAmount = driver.findElement(By.xpath(amountPath)).getText();
		assertEquals(payAmount, "$ " + amount, "Failed: Amount Not Matched in Payment Detail");
		Utility.app_logs.info("Successfully Verified Folio Line amount : " + payAmount);
		test_steps.add("Successfully Verified Folio Line amount : " + payAmount);

		String Date = driver.findElement(By.xpath(datePath)).getText();
		assertEquals(Date, getCurrDate("EEE MMM dd, yyyy"), "Failed: Payment Date not matched");
		Utility.app_logs.info("Successfully Verified Folio Line Date : " + Date);
		test_steps.add("Successfully Verified Folio Line Date : " + Date);

		if (isIconCheck) {
			String img_A_src = driver.findElement(By.xpath(iconPath)).getAttribute("src");

			assertTrue(img_A_src.contains("/Folio_Images/7.gif"),
					"Failed: A (Authorize symbol) in the first column not matched");
			Utility.app_logs.info("Successfully Verified A (Authorize symbol) in the first column");
			test_steps.add("Successfully Verified A (Authorize symbol) in the first column");
		}

		return test_steps;
	}

	public ArrayList<String> verifyAuthorizationType_PayDetail(WebDriver driver, String AuthorizationType) {

		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		String capture = new Select(ReservationFolio.Select_Authorization_type).getFirstSelectedOption().getText();
		assertEquals(capture, AuthorizationType, "Failed: Authorization Type not matched");
		Utility.app_logs.info("Successfully Verified " + AuthorizationType + " is selected as Authorization type");
		test_steps.add("Successfully Verified " + AuthorizationType + " is selected as Authorization type");
		return test_steps;
	}

	public ArrayList<String> verifyAmount_PayDetail(WebDriver driver, String ChangeAmountValue) {

		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		String Processed_Amount = ReservationFolio.Change_Amount.getAttribute("value");
		assertEquals(Processed_Amount, ChangeAmountValue, "Failed: Amount Not Matched in Payment Detail");
		Utility.app_logs.info("Successfully Verified Processed Amount : " + Processed_Amount);
		test_steps.add("Successfully Verified Processed Amount : " + Processed_Amount);
		return test_steps;
	}

	public void clickSaveButton(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.Click_Save_ReservationDetails);
		ReservationPage.Click_Save_ReservationDetails.click();
		Wait.WaitForElement(driver, OR.ToasterMessageGuaranteedRes);
	}

	public ArrayList<String> RoomAssigned_RatePlan_PromoCode(WebDriver driver, ExtentTest test, String RoomClass,
			String Adults, String RatePlan, String promocode, boolean IsCheckedUnchecked, ArrayList<String> test_steps)
			throws InterruptedException, IOException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);

		ReservationPage.RoomAssignmentButton.click();
		Utility.ElementFinderUntillNotShow(By.xpath(OR.ClickSearchRoomButton), driver);
		Wait.wait2Second();
		List<WebElement> DatePickerIcon = driver.findElements(By.cssSelector("#modalRoomPickerReservation  i"));
		// Wait.WaitForElement(driver, OR.RoomAssignment_DatePicker_Button);

		if (!Adults.equals("2")) {
			DatePickerIcon.get(0).click();
			ReservationPage.SelectDate.click();
		} else {
			ReservationPage.Enter_Adults.sendKeys(Keys.chord(Keys.CONTROL, "a"), Adults);
		}

		// Select Rate Plan
		System.out.println("RatePlan L" + RatePlan);

		new Select(ReservationPage.Reservation_RoomAssign_RatePlan).selectByVisibleText(RatePlan);
		// assertTrue(ReservationPage.Reservation_RatePlan.getAttribute("value").equals(RatePlan),
		// "Rate Plans didn't selected");
		test_steps.add(RatePlan + " Rate Plan Selected");

		ReservationPage.Enter_Rate_Promocode.sendKeys(Keys.chord(Keys.CONTROL, "a"), promocode);
		test_steps.add(promocode + " PromoCode Added");

		Wait.wait2Second();
		if (!IsCheckedUnchecked) {
			if (ReservationPage.RoomAssign_Check.isSelected()) {
				Wait.WaitForElement(driver, OR.RoomAssign_Check);
				ReservationPage.RoomAssign_Check.click();
			}
		}
		ReservationPage.SearchRoomButton.click();

		reservationLogger.info("RoomClass:" + RoomClass);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.SelectRoomClasses, driver);
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
		test_steps.add("Select room class: " + RoomClass);
		reservationLogger.info("Select room class: " + RoomClass);
		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}
		if (IsCheckedUnchecked) {
			new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);

			Utility.roomIndex++;
		}
		// Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);

		ReservationPage.SelectButton.click();

		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			ReservationPage.RoleBroken_Continue.click();
		}
		// Actions action = new Actions(driver);
		Wait.wait2Second();
		try {
			System.out.println("in try:" + ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed());

			if (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
				ReservationPage.ReCal_Folio_Options_PopUp_Recalculate.click();
				Wait.wait2Second();
				ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn.click();
				System.out.println("here:1");

			}

		} catch (Exception e) {
			System.out.println("catch ");
			reservationLogger.info("No ReCalculate Folio Options PopUp");
		}
		try {

			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Toaster_Message, driver);
			String Text = ReservationPage.Toaster_Message.getText();
			System.out.println("Toaster Message appear : " + Text);

			ReservationPage.Toaster_Message.click();
			if (Text.contains(
					"The existing room is not available for the criteria you provided. Please either change the criteria, or check the �Split Room� check box and try again.")) {
				reAssignRoom(driver);
				ReservationPage.Toaster_Message.click();

			}
			test_steps.add("Reservation" + Text);
		} catch (Exception e) {

			boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;
			System.out.println("Size:" + size);
			if (size) {
				Wait.wait1Second();
				System.out.println("Deposite Policy");
				ReservationPage.CancelDepositePolicy_Button.get(3).click();
				Wait.wait1Second();

			}
			try {
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.Toaster_Message, driver);

				if (ReservationPage.Toaster_Title.isDisplayed()) {
					String getTotasterTitle_ReservationSucess = ReservationPage.Toaster_Title.getText();
					test_steps.add("Reservation" + ReservationPage.Toaster_Message.getText());
					ReservationPage.Toaster_Title.click();
					System.out.println(getTotasterTitle_ReservationSucess);
					if (getTotasterTitle_ReservationSucess.contains("Saved")) {
						Assert.assertEquals(getTotasterTitle_ReservationSucess, "Reservation Saved");

					} else if (getTotasterTitle_ReservationSucess.equals("Error while saving.")) {
						reAssignRoom(driver);
						PolicyPopup(driver);
					}

				}
			} catch (Exception e2) {
				reservationLogger.info("in finally");
				String reservationNumber = GetReservationnumber(driver);
				CloseOpenedReservation(driver);
				driver.navigate().refresh();
				ReservationSearch reservationSearch = new ReservationSearch();
				reservationSearch.basicSearch_WithResNumber(driver, reservationNumber, true);
				Wait.wait2Second();
				Wait.WaitForElement(driver, OR.FolioTab_Reservation);
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.FolioTab_Reservation, driver);
				ReservationPage.FolioTab_Reservation.click();
				reservationLogger.info("after folio");
				test_steps.add(reservationNumber);
			}

		}
		Wait.wait3Second();
		return test_steps;
	}

	public ArrayList<String> VerifyLineItem_Desc_Amount(WebDriver driver, String LineItemDescription, String Amount,
			ArrayList<String> test_steps) throws InterruptedException {

		WebElement LineItemDes = driver.findElement(By.xpath("//a[contains(text(),'" + LineItemDescription + "')]"));
		assertTrue(LineItemDes.getText().equals(LineItemDescription), "Line Item Description isn't added correct");
		test_steps.add(" Line item description verified");

		WebElement LineItemAmount = driver.findElement(By.xpath("//a[contains(text(),'" + LineItemDescription
				+ "')]//parent::td//following-sibling::td[@class='lineitem-amount']/span"));
		Wait.explicit_wait_visibilityof_webelement(LineItemAmount, driver);
		Utility.ScrollToElement(LineItemAmount, driver);
		String AmountWithTax = LineItemAmount.getText();
		AmountWithTax = AmountWithTax.split(" ")[1];
		Utility.app_logs.info("Line Item Amount containing description " + LineItemDescription + " = " + AmountWithTax);
		String Tax = driver.findElement(By.xpath("//a[contains(text(),'" + LineItemDescription
				+ "')]//parent::td//following-sibling::td[@class='lineitem-tax']/span")).getText();
		Tax = Tax.split(" ")[1];
		Utility.app_logs.info("Line Item Tax containing description " + LineItemDescription + " = " + Tax);
		float FTax = Float.parseFloat(Tax);
		float FAmount = Float.parseFloat(AmountWithTax);
		float difference = FAmount - FTax;

		String Sdifference = Float.toString(difference);
		Utility.app_logs.info(FAmount + " - " + FTax + " = " + Sdifference);
		assertTrue(Sdifference.equals(Amount + ".0"), "Failed: Amount Missmatched");
		test_steps.add(LineItemDescription + ": Amount : " + Amount + " :Verified");
		return test_steps;
	}

	public void VerifyNote(WebDriver driver, String subject, String details, String NoteType, String Notestatus)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.Add_Note_Reservation);
		Utility.ScrollToElement(ReservationPage.Add_Note_Reservation, driver);

		Assert.assertTrue(false);
		Wait.wait2Second();
		if (ReservationPage.Verify_Added_Notes.isDisplayed()) {
			reservationLogger.info("Created note Successfully");
		} else {
			reservationLogger.info("Failed to add notes");
		}

	}

	public void setPaymentMethod(WebDriver driver, ArrayList test_steps, String paymentMethod, String cardNum,
			String expDate) {
		String payment = "(//label[contains(text(),'Payment Method:')])[2]/../../div/select";
		Wait.WaitForElement(driver, payment);
		new Select(driver.findElement(By.xpath(payment))).selectByVisibleText(paymentMethod);

		String card = "(//label[contains(text(),'Account:')])[5]/../../div[2]/input";
		Wait.WaitForElement(driver, card);
		driver.findElement(By.xpath(card)).sendKeys(cardNum);

		String exp = "(//label[contains(text(),'Exp Date:')])[2]/../div/input";
		Wait.WaitForElement(driver, exp);
		driver.findElement(By.xpath(exp)).sendKeys(expDate);
	}

	public void change_ResStatusNoShowAndSave(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		String noShow = "//label[contains(text(),'Reservation Status:')]/following-sibling::div[3]/a";
		Wait.WaitForElement(driver, noShow);
		Wait.wait2Second();
		driver.findElement(By.xpath(noShow)).click();
		test_steps.add("Click on No Show");
		Wait.wait5Second();
		String voidRoomCharges = "//label[contains(text(),'Void Room Charges')]/preceding-sibling::input";
		Wait.WaitForElement(driver, voidRoomCharges);
		driver.findElement(By.xpath(voidRoomCharges)).click();
		test_steps.add("Click void room charges");

		String Ok = "//label[contains(text(),'Void Room Charges')]/../../../../../../../..//button[2]";
		Wait.WaitForElement(driver, Ok);
		driver.findElement(By.xpath(Ok)).click();
		test_steps.add("Click Ok");
	}

	public void change_ResStatusAndSave(WebDriver driver, ArrayList test_steps, String status)
			throws InterruptedException {
		String resStatus = "//label[contains(text(),'Reservation Status:')]/following-sibling::div/select";
		Wait.WaitForElement(driver, resStatus);
		new Select(driver.findElement(By.xpath(resStatus))).selectByVisibleText(status.trim());
		test_steps.add("Reservation Status changed to : " + status);
		SaveRes_Updated(driver);
	}

	public ArrayList<String> BookResWithSplitAssignedRoom(WebDriver driver, ExtentTest test, String RoomClass,
			String NightStay, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Reservation_BookButton, driver);
		Utility.ScrollToElement(ReservationPage.Reservation_BookButton, driver);
		ReservationPage.Reservation_BookButton.click();
		test_steps.add("Click on Reservation Book Button");
		reservationLogger.info("Click on Reservation Book Button");
		Wait.WaitForElement(driver, OR.Room_Assignment_PopUp);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.NightField, driver);
		test_steps.add("Room assignment popup appears");
		Wait.wait2Second();
		Utility.ScrollToElement(ReservationPage.NightField, driver);
		ReservationPage.NightField.clear();
		ReservationPage.NightField.sendKeys(NightStay);
		test_steps.add("Enter Nights : " + NightStay);
		reservationLogger.info("Enter Nights : " + NightStay);
		if (!ReservationPage.Check_Split_Rooms.isSelected()) {
			ReservationPage.Check_Split_Rooms.click();
			test_steps.add("Click Split Room");
			reservationLogger.info("Click Split Room");
		}
		assertTrue(ReservationPage.Check_Split_Rooms.isSelected());
		test_steps.add("Split Room: Checked");

		if (!ReservationPage.Check_Assign_Room.isSelected()) {
			ReservationPage.Check_Assign_Room.click();
			test_steps.add("Click Assign Room");
			reservationLogger.info("Click Assign Room");
		}
		assertTrue(ReservationPage.Check_Assign_Room.isSelected());
		test_steps.add("Assign Room: Checked");
		Utility.ScrollToElement(ReservationPage.Click_Search, driver);
		ReservationPage.Click_Search.click();
		test_steps.add("Click Search Rooms");
		reservationLogger.info("Click Search");
		ArrayList<String> RoomNumbers = new ArrayList<>();
		String Previous_RoomNumber = "";
		for (int i = 0; i < Integer.valueOf(NightStay); i++) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRooms.get(i), driver);
			new Select(ReservationPage.SplitRooms.get(i)).selectByVisibleText(RoomClass);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRoomNumbers.get(i), driver);
			if (Utility.roomIndex == Utility.roomIndexEnd) {
				Utility.roomIndex = 1;
			}
			new Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(Utility.roomIndex);
			Utility.roomIndex++;
			String RN = new Select(ReservationPage.SplitRoomNumbers.get(i)).getFirstSelectedOption().getText();
			if (Previous_RoomNumber.equals("")) {
				Previous_RoomNumber = RN;
			} else {
				if (Previous_RoomNumber.equals(RN)) {
					if (Utility.roomIndex == Utility.roomIndexEnd) {
						Utility.roomIndex = 1;
					}
					new Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(Utility.roomIndex);
					Utility.roomIndex++;
				}
				Previous_RoomNumber = new Select(ReservationPage.SplitRoomNumbers.get(i)).getFirstSelectedOption()
						.getText();
				reservationLogger.info("Previous Room Number : " + Previous_RoomNumber);
			}
			String RoomName = new Select(ReservationPage.SplitRooms.get(i)).getFirstSelectedOption().getText() + " : "
					+ new Select(ReservationPage.SplitRoomNumbers.get(i)).getFirstSelectedOption().getText();
			RoomNumbers.add(new Select(ReservationPage.SplitRoomNumbers.get(i)).getFirstSelectedOption().getText());
			test_steps.add("Select Room  " + RoomName);
			reservationLogger.info("Select Room " + RoomName);
			assertTrue(RoomName.contains(RoomClass), "Failed: Room Class is not the selected one");
		}
		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		Utility.ScrollToElement(ReservationPage.SelectButton, driver);
		ReservationPage.SelectButton.click();
		test_steps.add("Click Select Room");
		reservationLogger.info("Click Select Room");
		Wait.wait2Second();
		driver.switchTo().alert().accept();
		test_steps.add("Accept Reservation Save Alert");
		reservationLogger.info("Accept Reservation Save Alert");
		Wait.wait2Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.RecalculateRoomCharges, driver);
		Utility.ScrollToElement(ReservationPage.RecalculateRoomCharges, driver);
		ReservationPage.RecalculateRoomCharges.click();
		test_steps.add("Click Recalculate Room Charges");
		reservationLogger.info("Click Recalculate Room Charges");
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.click_Select_Button_RoomChargesChanged, driver);
		Utility.ScrollToElement(ReservationPage.click_Select_Button_RoomChargesChanged, driver);
		ReservationPage.click_Select_Button_RoomChargesChanged.click();
		test_steps.add("Click Select Button of Room Charges Changed");
		reservationLogger.info("Click Select Button of Room Charges Changed");
		// Wait.waitForElementToBeGone(driver,
		// driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);
		Wait.explicit_wait_visibilityof_webelement_350(ReservationPage.Toaster_Message, driver);
		Wait.waitUntilPresenceOfElementLocatedByClassName(OR.Toaster_Message, driver);
		driver.findElement(By.className(OR.Toaster_Message)).click();

		String Room = ReservationPage.ReservationRoomStatus.getText();
		System.out.println("RC:" + Room);
		Wait.wait2Second();
		assertTrue(Room.contains("Multiple Rooms"), "Failed: Room Status is not multiple rooms");
		test_steps.add("Verified Room Status: " + Room);
		test_steps.addAll(RoomNumbers);
		return test_steps;

	}

	public void ClicOnFlioTab(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait1Second();
		Wait.WaitForElement(driver, OR.FolioTab_Reservation);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.FolioTab_Reservation, driver);
		ReservationPage.FolioTab_Reservation.click();

	}

	public void ClickEditRate_Description(WebDriver driver, String RatePlan) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		WebElement element = driver.findElement(By.xpath("//a[text()='" + RatePlan + "']"));
		Wait.explicit_wait_visibilityof_webelement(element, driver);
		Utility.ScrollToElement(element, driver);
		element.click();

	}

	public void VerifyEditRate_Description(WebDriver driver, String RatePlan) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait2Second();
		WebElement element = driver
				.findElement(By.xpath("//div[@data-bind='getElement: popUp']//span[text()='" + RatePlan + "']"));
		Wait.explicit_wait_visibilityof_webelement(element, driver);
		Utility.ScrollToElement(element, driver);
		assertEquals(element.getText(), RatePlan, "edit rate plan does not find in description");

		/*
		 * List<WebElement> VerifyEditRate_Link = driver.findElements(By.
		 * xpath("//div[@data-bind='getElement: popUp']//a[text()='" + RatePlan
		 * + "']")); boolean isLink = false; veri if (VerifyEditRate_Link.size()
		 * == 0) { isLink = true; } else { isLink = false; }
		 * 
		 * assertEquals(isLink, true, "edit rate plan showing link");
		 */

	}

	public void ClikcOnCancel_ItemDetailPopup(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.RollBackCancelBtn, driver);
		ReservationPage.RollBackCancelBtn.click();
		Wait.wait2Second();

	}

	public void roomAssignmentWithSpecificDate(WebDriver driver, ArrayList<String> test_steps, String Nights,
			String Adults, String Children, String CheckorUncheckAssign, String RoomClassName, String RoomClassName2,
			String date) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.WaitForElement(driver, OR.Click_RoomPicker);
		ReservationPage.Click_RoomPicker.click();
		test_steps.add("Successfully clicked on Rooms Picker");
		reservationLogger.info("Successfully clicked on Rooms Picker");

		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);

		// Wait.wait3Second();
		/*
		 * try { new Select(ReservationPage.Select_property_RoomAssign).
		 * selectByVisibleText(PropertyName); } catch(Exception e) { new
		 * Select(ReservationPage.Select_property_RoomAssign2).
		 * selectByVisibleText(PropertyName); }
		 */
		// Wait.wait5Second();

		Wait.WaitForElement(driver, OR.Click_Arrive_Datepicker);
		ReservationPage.Click_Arrive_Datepicker.click();
		test_steps.add("Successfully clicked on arrival date");
		reservationLogger.info("Successfully clicked on arrival date");

		/*
		 * Wait.WaitForElement(driver, OR.Click_Today);
		 * ReservationPage.Click_Today.click(); test.log(LogStatus.PASS,
		 * "Successfully clicked on Today");
		 * reservationLogger.info("Successfully clicked on Today");
		 */

		String month = Utility.get_MonthYear(date);
		String day = Utility.getDay(date);

		String monthYear = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr[2]/th[2]";
		int i = 0;
		String label;
		String dateTest = "//td[text()='" + day + "']";

		while (i < 20) {
			label = driver.findElement(By.xpath(monthYear)).getText();
			if (label.equalsIgnoreCase(month)) {
				Wait.WaitForElement(driver, dateTest);

				int count = driver.findElements(By.xpath(dateTest)).size();

				dateTest = "(//td[text()='" + day + "'])[" + count + "]";

				driver.findElement(By.xpath(dateTest)).click();
				break;
			} else {
				Wait.wait2Second();
				driver.findElement(By.xpath("//th[@class='next']")).click();
				i++;
			}
		}

		Wait.WaitForElement(driver, OR.Enter_Nigts);
		ReservationPage.Enter_Nigts.clear();
		ReservationPage.Enter_Nigts.sendKeys(Nights);
		test_steps.add("Successfully entered the nights : " + Nights);
		ReservationPage.Enter_Adults.clear();
		ReservationPage.Enter_Adults.sendKeys(Adults);
		test_steps.add("Successfully entered the audlts : " + Adults);
		ReservationPage.Enter_Children.clear();
		ReservationPage.Enter_Children.sendKeys(Children);
		test_steps.add("Successfully entered the childrens : " + Children);
		// ReservationPage.Enter_Rate_Promocode.sendKeys(RatepromoCode);

		if (!ReservationPage.Check_Split_Rooms.isSelected()) {
			ReservationPage.Check_Split_Rooms.click();
			test_steps.add("Successfully clicked on split rooms");
			reservationLogger.info("Successfully clicked on split rooms");
		}

		if (ReservationPage.Check_Assign_Room.isSelected()) {
			// ReservationPage.Check_Assign_Room.click();
			ReservationPage.Click_Search.click();
			test_steps.add("Successfully clicked on assign rooms");
		} else {
			if (CheckorUncheckAssign.equals("Yes")) {
				ReservationPage.Check_Assign_Room.click();
				test_steps.add("Successfully clicked on assign rooms");
				reservationLogger.info("Successfully clicked on assign rooms");

				ReservationPage.Click_Search.click();
				test_steps.add("Successfully clicked on search");
			} else {

				ReservationPage.Click_Search.click();
				test_steps.add("Successfully clicked on search");
				reservationLogger.info("Successfully clicked on search");
			}
		}

		Thread.sleep(4000);

		int count = 1;
		while (Integer.parseInt(Nights) >= count) {
			Wait.WaitForElement(driver,
					"//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr[" + count
							+ "]/td[2]/select");
			WebElement ele = driver.findElement(By
					.xpath("//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr["
							+ count + "]/td[2]/select"));

			Wait.WaitForElement(driver,
					"//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr[" + count
							+ "]/td[3]/select");
			WebElement ele1 = driver.findElement(By
					.xpath("//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr["
							+ count + "]/td[3]/select"));
			Select sel = new Select(ele);

			if (count == 1) {
				// System.out.println(RoomClassName);
				sel.selectByVisibleText(RoomClassName);
				test_steps.add("Successfully selected the room class : " + RoomClassName);
				reservationLogger.info("Successfully selected the room class : " + RoomClassName);

				sel = new Select(ele1);
				java.util.List<WebElement> options = sel.getOptions();
				int roomCount = 0;
				for (WebElement item : options) {
					// System.out.println(item.getText());
					if (roomCount == 1) {
						sel.selectByIndex(1);
						test_steps.add("Successfully selected the room number : " + item.getText());
						reservationLogger.info("Successfully selected the room number : " + item.getText());

						break;
					}
					roomCount++;
				}

			} else {
				sel.selectByVisibleText(RoomClassName2);
				test_steps.add("Successfully selected the room class : " + RoomClassName2);
				reservationLogger.info("Successfully selected the room class : " + RoomClassName2);

				sel = new Select(ele1);

				java.util.List<WebElement> options = sel.getOptions();
				int roomCount = 0;
				for (WebElement item : options) {
					// System.out.println(item.getText());
					if (roomCount == 1) {
						sel.selectByIndex(1);
						test_steps.add("Successfully selected the room number : " + item.getText());
						reservationLogger.info("Successfully selected the room number : " + item.getText());
						break;
					}
					roomCount++;
				}
			}
			count++;
		}

		ReservationPage.Click_Select.click();
		test_steps.add("Successfully clicked on select");
		try {
			Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
		} catch (Exception e) {
		}
		Wait.wait3Second();
		if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
			ReservationPage.Click_Continue_RuleBroken_Popup.click();
			test_steps.add("Successfully clicked on rule brocken pop up");
		} else {
			reservationLogger.info("Satisfied Rules");
		}

		if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
			String getTotasterTitle = ReservationPage.Toaster_Title.getText();
			String getToastermessage = ReservationPage.Toaster_Message.getText();
			reservationLogger.info(getTotasterTitle + " " + getToastermessage);
			Assert.assertEquals(getTotasterTitle, "Room assignment has changed.");
			Assert.assertEquals(getToastermessage, "Room assignment has changed.");
		}

		Wait.WaitForElement(driver, OR.Get_RoomClass_Status);
		// String getPropertyName = ReservationPage.Get_Property_Name.getText();
		String getRoomclassName_status = ReservationPage.Get_RoomClass_Status.getText();
		reservationLogger.info(getRoomclassName_status);
		// Assert.assertEquals(getPropertyName,PropertyName );
		String getRoomclassName[] = getRoomclassName_status.split(" ");
		// Assert.assertEquals(getRoomclassName[0],RoomClassName );
		if (CheckorUncheckAssign.equals("Yes")) {

		} else {

			// Assert.assertEquals(getRoomclassName[3], "Unassigned");
			Assert.assertEquals(getRoomclassName.length - 1, "Unassigned");
		}
	}

	public String get_RoomCharge(WebDriver driver) {
		String Balance = driver
				.findElement(By
						.xpath("//label[contains(text(),'Room Charges: ')]/following-sibling::span[@class='pamt']/span[@class='pamt']"))
				.getText();
		Balance = Balance.replace("$", "");
		reservationLogger.info("Folio First Balance : " + Balance);
		return Balance;
	}

	public String get_Taxes(WebDriver driver) {
		String Balance = driver
				.findElement(By
						.xpath("//label[contains(text(),'Taxes & Service Charges: ')]/following-sibling::span/span[@class='pamt']"))
				.getText();
		Balance = Balance.replace("$", "");
		reservationLogger.info("Folio First Balance : " + Balance);
		return Balance;
	}

	public ArrayList<String> download_GuestPDFReport(WebDriver driver, String ReportName) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();
		Wait.explicit_wait_visibilityof_webelement(
				driver.findElement(By.xpath("//*[@id='bpjscontainer_18']//a[contains(@data-bind,'Print')]")), driver);
		WebElement printBtn = driver
				.findElement(By.xpath("//*[@id='bpjscontainer_18']//a[contains(@data-bind,'Print')]"));
		printBtn.click();
		reservationLogger.info("Print Button Clicked");
		test_steps.add("Print Button Clicked");
		Wait.wait2Second();

		String radioPath = "//*[@id='optionsForInnroad']//span[.='" + ReportName
				+ "']/preceding-sibling::input[contains(@data-bind,'radio')]";

		WebElement guestRadio = driver.findElement(By.xpath(radioPath));

		Wait.explicit_wait_visibilityof_webelement(guestRadio, driver);
		// Guest Registration,Guest Statement
		if (!guestRadio.isSelected()) {
			guestRadio.click();
		}
		reservationLogger.info(ReportName + " Option Selected");
		test_steps.add(ReportName + " Option Selected");
		Wait.wait2Second();

		driver.findElement(By.xpath("(//*[@id='optionsForInnroad']//div[@class='innroad-btn-submit export']//i)[2]"))
				.click();
		reservationLogger.info("Export Button Clicked");
		test_steps.add("Export Button Clicked");
		driver.findElement(By.xpath("//*[@id='optionsForInnroad']/div[4]/div[3]/div/div/ul/li[1]")).click();
		reservationLogger.info("Download Pdf Button Clicked");
		test_steps.add("Download Pdf Button Clicked");

		WebElement loading = driver.findElement(By.xpath("//*[@id='bpjscontainer_22']//div[@class='modules_loading']"));
		if (loading.isDisplayed()) {
			Wait.waitForElementToBeGone(driver, loading, 600000);
		}
		Wait.wait5Second();
		test_steps.add("PDF File Downloaded");
		reservationLogger.info("PDF File Downloaded");

		return test_steps;
	}

	public void VerifyNote(WebDriver driver, String subject, String NoteType, String Notestatus)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.Add_Note_Reservation);
		Utility.ScrollToElement(ReservationPage.Add_Note_Reservation, driver);
		int LineItem_Size = ReservationPage.AddedNote_NotesType.size();
		reservationLogger.info("LineItem_Size : " + LineItem_Size);
		int lineNumber = LineItem_Size - 1;
		reservationLogger.info("LineNumber : " + lineNumber);
		boolean found = false;
		for (lineNumber = LineItem_Size - 1; lineNumber >= 0; lineNumber--) {
			reservationLogger.info("LineNumber : " + lineNumber);
			reservationLogger.info("NoteType : " + ReservationPage.AddedNote_NotesType.get(lineNumber).getText());
			reservationLogger.info("subject : " + ReservationPage.AddedNote_Subject.get(lineNumber).getText());
			if (ReservationPage.AddedNote_NotesType.get(lineNumber).getText().equalsIgnoreCase(NoteType)
					&& ReservationPage.AddedNote_Subject.get(lineNumber).getText().equals(subject)) {
				found = true;
				break;
			}
		}
		if (!found) {
			assertTrue(false, "Failed: Line Item having NoteType: " + NoteType + " Not found.");
		}

	}

	public void VerifyNoNote(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.Add_Note_Reservation);
		Utility.ScrollToElement(ReservationPage.Add_Note_Reservation, driver);
		int LineItem_Size = ReservationPage.AddedNote_NotesType.size();
		assertEquals(LineItem_Size, 0, "Failed: Note Found");

	}

	public String GetUnAssigedRoomNumber(WebDriver driver) {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		getRoomclassName_status = ReservationPage.Get_RoomClass_Status.getText();
		String[] getRoomclassName_statusList = getRoomclassName_status.split(" : ");
		// System.out.println(getRoomclassName_status + " ..... " +
		// getRoomclassName_statusList);
		System.out.println("Room Class Status " + getRoomclassName_status);
		return getRoomclassName_statusList[1];
	}

	public String guestName1(WebDriver driver, ExtentTest test) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.WaitForElement(driver, OR.getGuestName);
		nameGuest1 = ReservationPage.getGuestName1.getText();
		reservationLogger.info("Guest name: " + nameGuest1);
		Wait.wait5Second();
		return nameGuest1;

	}

	public void getUnassignedReservationCountValidationsInReservation(WebDriver driver, ExtentTest test)
			throws InterruptedException {

		Wait.wait5Second();

		driver.navigate().refresh();
		Wait.wait5Second();
		int unassignedCountAfterCreatingReservation = Integer.parseInt(unassignedResCountAfterCreatingRes);

		int unassignedCountBeforeCreatingReservation = Integer.parseInt(unassignedResCount) + 1;

		Assert.assertEquals(unassignedCountBeforeCreatingReservation, unassignedCountAfterCreatingReservation,
				"Successfully validated Unassigned Reservation Count in Reservations");
	}

	public void changeReservationStatusToReserved(WebDriver driver, String ReservationStatusReserved,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		String Res_Status = new Select(ReservationPage.Reservation_Status).getFirstSelectedOption().getText();

		reservationLogger.info("Current Status of Reservation is " + Res_Status);

		test_steps.add("Current Status of Reservation is " + Res_Status);

		if (Res_Status.equalsIgnoreCase("Guaranteed") || Res_Status.equalsIgnoreCase("On Hold")
				|| Res_Status.equalsIgnoreCase("Confirmed")) {

			Wait.wait5Second();

			new Select(ReservationPage.Reservation_Status).selectByVisibleText("Reserved");

			Wait.wait2Second();

			String Res_Status2 = new Select(ReservationPage.Reservation_Status).getFirstSelectedOption().getText();

			reservationLogger.info("Changed Reservation Status to:  " + Res_Status2);

			test_steps.add("Changed Reservation Status to:  " + Res_Status2);

		}

	}

	public void changeReservationStatusToGuaranteed(WebDriver driver, String ReservationStatusGuaranteed,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		String Res_Status = new Select(ReservationPage.Reservation_Status).getFirstSelectedOption().getText();

		reservationLogger.info("Current Status of Reservation is " + Res_Status);

		test_steps.add("Current Status of Reservation is " + Res_Status);

		if (Res_Status.equalsIgnoreCase("Reserved") || Res_Status.equalsIgnoreCase("On Hold")
				|| Res_Status.equalsIgnoreCase("Confirmed")) {

			Wait.wait5Second();

			new Select(ReservationPage.Reservation_Status).selectByVisibleText("Guaranteed");

			Wait.wait2Second();

			String Res_Status2 = new Select(ReservationPage.Reservation_Status).getFirstSelectedOption().getText();

			reservationLogger.info("Changed Reservation Status to:  " + Res_Status2);

			test_steps.add("Changed Reservation Status to:  " + Res_Status2);

		}

	}

	public void changeReservationStatusToOnHold(WebDriver driver, String ReservationStatusOnHold,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		String Res_Status = new Select(ReservationPage.Reservation_Status).getFirstSelectedOption().getText();

		reservationLogger.info("Current Status of Reservation is " + Res_Status);

		test_steps.add("Current Status of Reservation is " + Res_Status);

		if (Res_Status.equalsIgnoreCase("Guaranteed") || Res_Status.equalsIgnoreCase("Reserved")
				|| Res_Status.equalsIgnoreCase("Confirmed")) {

			Wait.wait5Second();

			new Select(ReservationPage.Reservation_Status).selectByVisibleText("On Hold");

			Wait.wait2Second();

			String Res_Status2 = new Select(ReservationPage.Reservation_Status).getFirstSelectedOption().getText();

			reservationLogger.info("Changed Reservation Status to:  " + Res_Status2);

			test_steps.add("Changed Reservation Status to:  " + Res_Status2);

		}

	}

	public void changeReservationStatusToConfirmed(WebDriver driver, String ReservationStatusConfirmed,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		String Res_Status = new Select(ReservationPage.Reservation_Status).getFirstSelectedOption().getText();

		reservationLogger.info("Current Status of Reservation is " + Res_Status);

		test_steps.add("Current Status of Reservation is " + Res_Status);

		if (Res_Status.equalsIgnoreCase("Reserved") || Res_Status.equalsIgnoreCase("On Hold")
				|| Res_Status.equalsIgnoreCase("Guaranteed")) {

			Wait.wait5Second();

			new Select(ReservationPage.Reservation_Status).selectByVisibleText("Confirmed");

			Wait.wait2Second();

			String Res_Status2 = new Select(ReservationPage.Reservation_Status).getFirstSelectedOption().getText();

			reservationLogger.info("Changed Reservation Status to:  " + Res_Status2);

			test_steps.add("Changed Reservation Status to:  " + Res_Status2);

		}

	}

	public void changeReservationStatusToPending(WebDriver driver, String ReservationStatusPending,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		String Res_Status = new Select(ReservationPage.Reservation_Status).getFirstSelectedOption().getText();

		reservationLogger.info("Current Status of Reservation is " + Res_Status);

		test_steps.add("Current Status of Reservation is " + Res_Status);

		if (Res_Status.equalsIgnoreCase("Pending")) {

			Wait.wait5Second();

			new Select(ReservationPage.Reservation_Status).selectByVisibleText("Pending");

			Wait.wait2Second();

			String Res_Status2 = new Select(ReservationPage.Reservation_Status).getFirstSelectedOption().getText();

			reservationLogger.info("Changed Reservation Status to:  " + Res_Status2);

			test_steps.add("Changed Reservation Status to:  " + Res_Status2);

		}

	}

	public void changeReservationStatus(WebDriver driver, String ReservationStatusReserved,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		String Res_Status = new Select(ReservationPage.Reservation_Status).getFirstSelectedOption().getText();

		reservationLogger.info("Status of Reservation is " + Res_Status);

		test_steps.add("Current Status of Reservation is " + Res_Status);

		if (Res_Status.equalsIgnoreCase("Guaranteed")) {

			Wait.wait5Second();

			new Select(ReservationPage.Reservation_Status).selectByVisibleText("Reserved");

			Wait.wait2Second();

			String Res_Status2 = new Select(ReservationPage.Reservation_Status).getFirstSelectedOption().getText();

			reservationLogger.info("Changed Reservation Status to:  " + Res_Status2);

			test_steps.add("Changed Reservation Status to:  " + Res_Status2);

		}

	}

	public void RemovePaymentMethod(WebDriver driver, String NoPaymentMethod, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.Select_Payment_Method);
		// Wait.explicit_wait_visibilityof_webelement(ReservationPage.Select_Payment_Method,
		// driver);
		Utility.ScrollToElement(ReservationPage.Select_Payment_Method, driver);

		if (!ReservationPage.Select_Payment_Method.equals("-- Select --")) {

			new Select(ReservationPage.Select_Payment_Method).selectByVisibleText(NoPaymentMethod);

			reservationLogger.info("Removed Payment method >>>> " + NoPaymentMethod);

			test_steps.add("Removed Payment method " + NoPaymentMethod);

			Wait.wait5Second();

			// Click Save button
			clickSaveButton(driver, test_steps);

			reservationLogger.info("Saved the Reservation ");

			test_steps.add("Saved the Reservation ");
		}

		else {

			reservationLogger.info("Payment Method was already removed ");

			test_steps.add("Payment Method was already removed ");

		}

	}

	public void saveResForGuaranteedStatus(WebDriver driver, ArrayList<String> getTest_Steps) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.Click_Save_ReservationDetails);
		ReservationPage.Click_Save_ReservationDetails.click();
		Wait.WaitForElement(driver, OR.ToasterMessageGuaranteedRes);
	}

	public void ToasterMessageForGuaranteedReservation(WebDriver driver, ExtentTest test,
			ArrayList<String> test_steps) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		if (ReservationPage.ToasterMessageGuaranteedRes.isDisplayed()) {
			String toasterMessage = ReservationPage.ToasterMessageGuaranteedRes.getText();
			if (toasterMessage.equalsIgnoreCase("Please select only credit card as a payment method.")) {

				reservationLogger.info(" Toaster message is displayed for Guaranteed Reservation " + " ******** "
						+ toasterMessage + " ******* ");
				// test_steps.add(" Toaster message for Guaranteed Reservation "
				// +toasterMessage);
				test_steps.add(
						" Toaster message for Guaranteed Reservation " + " ******** " + toasterMessage + " ******* ");
			}

		} else {
			reservationLogger.info(" Reservation Status is not Guaranteed ");
			test_steps.add(" Reservation Status is not Guaranteed ");
		}

	}

	public void selectPaymentMethodCash(WebDriver driver, String PaymentMethodCash, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Utility.ScrollToElement(ReservationPage.Select_Payment_Method, driver);

		String PaymentMethod = new Select(ReservationPage.Select_Payment_Method).getFirstSelectedOption().getText();

		System.out.println("Payment method :" + PaymentMethod);

		new Select(ReservationPage.Select_Payment_Method).selectByVisibleText("Cash");

		String CashPayment = new Select(ReservationPage.Select_Payment_Method).getFirstSelectedOption().getText();

		reservationLogger.info(" Selected Payment Method: " + CashPayment);

		test_steps.add(" Selected Payment Method: " + CashPayment);

	}

	public void selectPaymentMethodCheck(WebDriver driver, String PaymentMethodCheck, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Utility.ScrollToElement(ReservationPage.Select_Payment_Method, driver);

		/*
		 * String PaymentMethod=new
		 * Select(ReservationPage.Select_Payment_Method).getFirstSelectedOption(
		 * ).getText();
		 * 
		 * System.out.println("Payment method :" +PaymentMethod);
		 */

		new Select(ReservationPage.Select_Payment_Method).selectByVisibleText("Check");

		String CheckPayment = new Select(ReservationPage.Select_Payment_Method).getFirstSelectedOption().getText();

		reservationLogger.info(" Selected Payment Method: " + CheckPayment);

		test_steps.add(" Selected Payment Method: " + CheckPayment);

	}

	public void selectPaymentMethodPrepaid(WebDriver driver, String PaymentMethodPrepaid, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Utility.ScrollToElement(ReservationPage.Select_Payment_Method, driver);

		new Select(ReservationPage.Select_Payment_Method).selectByVisibleText("Prepaid");

		String CheckPayment = new Select(ReservationPage.Select_Payment_Method).getFirstSelectedOption().getText();

		reservationLogger.info(" Selected Payment Method: " + CheckPayment);

		test_steps.add(" Selected Payment Method: " + CheckPayment);

	}

	public void ToasterMessageForAllReservationStatus(WebDriver driver, ArrayList<String> test_steps) {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		if (ReservationPage.Toaster_Message.isDisplayed()) {

			String toasterMessage = ReservationPage.Toaster_Message.getText();

			if (toasterMessage.equalsIgnoreCase("Please select only credit card as a payment method.")) {

				reservationLogger
						.info(" Toaster message is displayed, when the Payment method: Credit Card is mandatory for Reservation "
								+ " ******** " + toasterMessage + " ******* ");

				test_steps
						.add(" Toaster message is displayed, when the Payment method: Credit Card is mandatory for Reservation "
								+ " ******** " + toasterMessage + " ******* ");

			}
		}

		else {
			reservationLogger.info(" This Reservation Status doesn't require Credit Card as Payment method ");
			test_steps.add("This Reservation Status doesn't require Credit Card as Payment method ");
		}

	}

	public ArrayList<String> VerifyReservationDirectFromAccountPage(WebDriver driver, String GuestProfileName,
			String FirstName, String LastName, String PaymentMethod, ArrayList<String> test_steps)
			throws InterruptedException, IOException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		assertTrue(ReservationPage.Reservation_Referral.isDisplayed(), "Reservation Page didn't display");
		assertTrue(ReservationPage.GuestProfileAttached.getText().equals(GuestProfileName),
				"Account didn't attached successfully");
		test_steps.add("Verified Attached Account on Reservation Page : " + GuestProfileName);

		assertTrue(ReservationPage.Enter_Contact_First_Name.getAttribute("value").equals(FirstName),
				"Contact FirstName didn't attached successfully");
		test_steps.add("Verified Attached Account Contact First Name on Reservation Page : " + FirstName);

		assertTrue(ReservationPage.Enter_Contact_Last_Name.getAttribute("value").equals(LastName),
				"Contact LastName didn't attached successfully");

		test_steps.add("Verified Attached Account Contact Last Name on Reservation Page : " + LastName);
		String FirstSelectedoption = new Select(ReservationPage.Select_Payment_Method).getFirstSelectedOption()
				.getText();

		assertTrue(FirstSelectedoption.equals(PaymentMethod), "PaymentMethod didn't attached successfully");

		test_steps.add("Verified Attached Account Payment Method on Reservation Page : " + PaymentMethod);

		return test_steps;
	}

	public ArrayList<String> RoomAssignedReservationWithNoLongWait(WebDriver driver, ExtentTest test, String RoomClass,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.RoomAssignmentButton);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignmentButton);
		Wait.WaitForElement(driver, OR.ClickSearchRoomButton);
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignment_DateIcon);
		jse.executeScript("arguments[0].click();", ReservationPage.SelectDate);

		Wait.wait2Second();
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}
		jse.executeScript("arguments[0].click();", ReservationPage.SearchRoomButton);

		reservationLogger.info("RoomClass:" + RoomClass);
		Wait.WaitForElement(driver, OR.SelectRoomClasses);
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClass);
		test_steps.add("Select room class: " + RoomClass);
		reservationLogger.info("Select room class: " + RoomClass);
		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}
		new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);

		Utility.roomIndex++;
		// Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();

		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();

		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			ReservationPage.RoleBroken_Continue.click();
		}

		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 60);

		// Wait.WaitForElement(driver, OR.Toaster_Message);
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		ReservationPage.Toaster_Message.click();
		return test_steps;
	}

	public void VerifyRoomClassNumber(WebDriver driver, String RoomClass, String RoomNumber)
			throws InterruptedException {

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_Reservation.RoomClassNumber, driver);
		Utility.ScrollToElement(elements_Reservation.RoomClassNumber, driver);
		assertEquals(elements_Reservation.RoomClassNumber.getText(), RoomClass + " : " + RoomNumber);

	}

	public void VerifyLongStayCheckBox(WebDriver driver) throws InterruptedException, IOException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.LongStayCheckbox);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.LongStayCheckbox, driver);
		Utility.ScrollToElement(Elements_Reservations.LongStayCheckbox, driver);
		assertTrue(Elements_Reservations.LongStayCheckbox.isSelected(), "Failed: Long Stay CheckBox is not Checked");

	}

	public void ClickOnSetExempt_Button(WebDriver driver) throws InterruptedException {

		Elements_Reservation elements_Reservations = new Elements_Reservation(driver);
		Wait.wait1Second();
		Wait.WaitForElement(driver, OR.SetExemptionAllItems_Button);
		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.SetExemptionAllItems_Button, driver);
		elements_Reservations.SetExemptionAllItems_Button.click();
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		Wait.explicit_wait_visibilityof_webelement(elements_Reservations.Toaster_Message, driver);
		elements_Reservations.Toaster_Message.click();
		Wait.wait1Second();
	}

	public void SaveRes_Updated(WebDriver driver) throws InterruptedException {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.ReservationSaveButton);
		Wait.explicit_wait_xpath(OR.ReservationSaveButton, driver);
		Utility.ScrollToElement(Elements_Reservations.ReservationSaveButton, driver);
		Wait.wait3Second();
		Elements_Reservations.ReservationSaveButton.click();
		reservationLogger.info("Click on save button");

		while (true) {
			if (!driver.findElement(By.xpath("//div[@id='ReservationDetail']//div[@class='modules_loading']"))
					.isDisplayed()) {
				break;
			} else {
				Wait.wait2Second();
			}
		}

		String res;
		while (true) {
			res = Elements_Reservations.Get_Confirmation_Number.getText();
			if (!res.equalsIgnoreCase("Pending")) {
				if (!res.equalsIgnoreCase("")) {
					break;
				}
			} else {
			}
			Wait.wait1Second();
			if (driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0) {
				Elements_Reservations.CancelDepositePolicy_Button.get(3).click();
			} else {
			}
		}

		while (true) {
			if (driver.findElements(By.xpath("//div[contains(text(),'Reservation Saved')]/preceding-sibling::button"))
					.size() <= 0) {
				break;
			} else {
				Wait.wait2Second();
			}
		}

		reservationLogger.info("Reservation Saved");
	}

	public String getAddedTask(WebDriver driver, String TaskSubject, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		String xpath = "//table[@class='table table-striped table-bordered table-hover resGrid1']/tbody/tr/td/a[.='"
				+ TaskSubject + "']";

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", xpath);

		// jse.executeScript("arguments[0].click();", xpath);
		String taskSubject = driver.findElement(By.xpath(xpath)).getText();

		// Wait.WaitForElement(driver, taskSubject);
		// String addedtask = ReservationPage.addedTask.getText();
		reservationLogger.info(" Added Task: " + taskSubject);
		// test_steps.add("Added Task: " + taskSubject);
		Wait.wait5Second();
		return taskSubject;
	}

	public void OpenTaskAndVerifyActionStatus(WebDriver driver, String TaskSubject, String TaskNoteType,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		String xpath = "//table[@class='table table-striped table-bordered table-hover resGrid1']//a[.='Task_EarlyArrival']";
		WebElement OpenTask = driver.findElement(By.xpath(xpath));
		Utility.ScrollToElement(OpenTask, driver);
		OpenTask.click();
		Wait.WaitForElement(driver, OR.NotesTitle);
		if (ReservationPage.NotesTitle.isDisplayed()) {

			if (new Select(ReservationPage.TaskList_NotesType).getFirstSelectedOption().getText()
					.equalsIgnoreCase(TaskNoteType) &&

					ReservationPage.TaskListActionRequired.isSelected() &&

					new Select(ReservationPage.TaskListAction).getFirstSelectedOption().getText()
							.equalsIgnoreCase("Completed"))

			{

				reservationLogger.info(" Status of Task in Reservation: Completed ");
				test_steps.add(" Status of Task in Reservation: Completed");
			}

			else {

				reservationLogger.info(" Fail to verify TaskList Status");
				test_steps.add(" Fail to verify TaskList Status");
			}

		}

		// click on Cancel button
		Wait.WaitForElement(driver, OR.NotesPopUpCancelButton);
		ReservationPage.NotesPopUpCancelButton.click();
		Wait.wait2Second();
	}

	public String getAddedNotes(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.WaitForElement(driver, OR.addedNote);
		String addednote = ReservationPage.addedNote.getText();
		reservationLogger.info(" Added Note: " + addednote);
		// test_steps.add("Added Note: " + addednote);
		Wait.wait5Second();
		// driver.findElement(By.xpath("//table[@data-bind='visible:
		// NoteList().length
		// >= 0']/tbody/tr[1]"));
		return addednote;

	}

	public void getUnassignedReservationCount(WebDriver driver, ExtentTest test) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// ReservationPage.getUnsassignedResCount.click();
		unassignedResCount = ReservationPage.getUnsassignedResCount.getText();
		reservationLogger.info(" Unassigned Reservation Count before creating the reservation: " + unassignedResCount);

	}

	public void getUnassignedRoomInBasicSearch(WebDriver driver, ExtentTest test) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.clickUnassigned);
		// ReservationPage.clickUnassigned.click();
		Wait.WaitForElement(driver, OR.getUnsassignedResCount);
		unassignedResCountAfterCreatingRes = ReservationPage.getUnsassignedResCount.getText();
		// int
		// unassignedCountAfterCreatingReservation=Integer.parseInt(unassignedResCountReservation)+1;
		// unassignedResCountAfterCreatingRes=Integer.toString(unassignedCountAfterCreatingReservation);
		reservationLogger.info(
				" Unassigned Reservation Count after creating the reservation: " + unassignedResCountAfterCreatingRes);
		Wait.wait5Second();
	}

	public String[] RoomAssign_ExtRedSplitReserTC(WebDriver driver, String RoomClass, String NightStay)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		ReservationPage.RoomAssignmentButton.click();

		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.explicit_wait_visibilityof_webelement_600(ReservationPage.Room_Assignment_PopUp, driver);
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		ReservationPage.RoomAssignment_DateIcon.click();
		ReservationPage.SelectDate.click();
		// String Date = ReservationPage.SelectDate.getText();

		ReservationPage.NightField.clear();
		ReservationPage.NightField.sendKeys(NightStay);
		Wait.wait1Second();
		if (!ReservationPage.Check_Split_Rooms.isSelected()) {
			ReservationPage.Check_Split_Rooms.click();
		}
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}
		reservationLogger.info("Checked Room Split checkbox");
		ReservationPage.SearchRoomButton.click();

		String[] roomNos = new String[Integer.parseInt(NightStay)];
		for (int i = 0; i < Integer.valueOf(NightStay); i++) {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRooms.get(i), driver);
			new Select(ReservationPage.SplitRooms.get(i)).selectByVisibleText(RoomClass);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.SplitRoomNumbers.get(i), driver);

			if (Utility.roomIndex == Utility.roomIndexEnd) {
				Utility.roomIndex = 1;
			}
			new Select(ReservationPage.SplitRoomNumbers.get(i)).selectByIndex(Utility.roomIndex);
			Wait.wait2Second();
			// String
			// roomnum="(//tbody//select[contains(@data-bind,'RoomId')])["+(i+1)+"]/option";
			// System.out.println(roomnum);
			// int count=driver.findElements(By.xpath(roomnum)).size();
			// Random random = new Random();
			// int randomNumber = random.nextInt(count - 1) + 1;
			// System.out.println("count : "+count);
			// System.out.println("randomNumber : "+randomNumber);
			// new
			// Select(driver.findElement(By.xpath("(//tbody//select[contains(@data-bind,'RoomId')])["+(i+1)+"]"))).selectByIndex(randomNumber);

			roomNos[i] = new Select(ReservationPage.SplitRoomNumbers.get(i)).getFirstSelectedOption().getText();
			Utility.roomIndex++;
		}

		// test_steps.add("Select Room Class: " + RoomClass);
		reservationLogger.info("Select Room Class: " + RoomClass);
		Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();
		Wait.wait2Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			ReservationPage.Continue.get(9).click();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			ReservationPage.RoleBroken_Continue.click();
		}
		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Toaster_Message, driver);
		ReservationPage.Toaster_Message.click();
		Wait.wait2Second();

		return roomNos;
	}

	public void clickBasicResSearch(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation_SearchPage ReservationSearchPage = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.Click_BasicSearch);
		ReservationSearchPage.Click_BasicSearch.click();
		test_steps.add("Clicked on Basic Search in Reservation ");
		reservationLogger.info("Clicked on Basic Search in Reservation ");
	}

	public void clickAdvancedResSearch(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation_SearchPage ReservationSearchPage = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.AdvancedSearch);
		ReservationSearchPage.AdvancedSearch.click();
		test_steps.add("Clicked on Advanced Search in Reservation ");
		reservationLogger.info("Clicked on Advanced Search in Reservation ");
	}

	public void verifySearchResults(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.CountOfAdvancedSearchResults, driver);
		String TotalNoOfRecords = ReservationPage.CountOfAdvancedSearchResults.getText();
		reservationLogger.info("The Search Results Page is Loaded With " + TotalNoOfRecords + " of Records");
		test_steps.add("The  Search Results Page is Loaded With " + TotalNoOfRecords + " of Records");
		Assert.assertNotNull(TotalNoOfRecords, "Contains Results");
		test_steps.add("Verified the TotalNoOfRecords");

	}

	public void clickCreateGuestProfile(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		test_steps.add("Waiting for the CreateGuestProfile Checkbox");
		Wait.WaitForElement(driver, OR.CreateGuestProfile);

		if (ReservationPage.CreateGuestProfile.isSelected()) {
			ReservationPage.CreateGuestProfile.click();
			reservationLogger.info("Uncheck the CreateGuestProfile Checkbox in Create Guest Profile");
			test_steps.add("Uncheck the CreateGuestProfile Checkbox in Create Guest Profile");
		} else {
			reservationLogger.info("Clicked on CreateGuestProfile Checkbox in Create Guest Profile");
			test_steps.add("Check the CreateGuestProfile Checkbox in Create Guest Profile");
		}

	}

	public void contactInformation(WebDriver driver, ArrayList<String> test_steps, String FirstName)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.Enter_First_Name);
		ReservationPage.Enter_First_Name.clear();
		ReservationPage.Enter_First_Name.sendKeys(FirstName);
		test_steps.add("Entered Quote Name : " + FirstName);
		reservationLogger.info("Entered all the requried details");
		test_steps.add("Entered all the required details");
	}

	public String saveAndToasterMessageCheck(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		String QuoteNumber = "";
		Wait.WaitForElement(driver, OR.Click_Save);
		Utility.ScrollToElement(ReservationPage.Click_Save, driver);
		ReservationPage.Click_Save.click();
		reservationLogger.info("Clicked on save");
		test_steps.add("Clicked on Save Button");
		Wait.wait2Second();
		test_steps.add("Saved all the details");
		reservationLogger.info("Successfully Saved all the details");

		test_steps.add("Waiting for the Toaster Pop_Up");
		Wait.WaitForElement(driver, OR.Verify_Toaster_Container);
		if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
			test_steps.add("Toaster Pop_Up is Displayed");
			String getTotasterTitle_ReservationSucess = ReservationPage.Toaster_Title.getText();
			String getToastermessage_ReservationSucess = ReservationPage.Toaster_Message.getText();
			test_steps.add("Toaster Message : " + getToastermessage_ReservationSucess);
			reservationLogger.info("Toaster Message : " + getToastermessage_ReservationSucess);
			Assert.assertEquals(getTotasterTitle_ReservationSucess, "Reservation Saved");
			test_steps.add("Verified Toaster Message");
			if (getToastermessage_ReservationSucess.endsWith("has been saved successfully"))
				;
			{
				QuoteNumber = getToastermessage_ReservationSucess.substring(0,
						getToastermessage_ReservationSucess.indexOf("has"));
				reservationLogger.info("Created NewQuote with Quote id '" + QuoteNumber + "'");
				test_steps.add("Created NewQuote with Quote id '" + QuoteNumber + "'");
			}
		}
		return QuoteNumber;
	}

	public void BookingResStatusCheck(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.SelectButton);
		ReservationPage.SelectButton.click();
		reservationLogger.info("Clicked on Select Button in Room Assignment");
		test_steps.add("Clicked on Select Button in Room Assignment");

		test_steps.add("Checking the Reservation Status");
		Select sel = new Select(driver.findElement(By.xpath(OR.Get_QuoteReservation_Status)));
		WebElement ele = sel.getFirstSelectedOption();
		String str = ele.getText();
		reservationLogger.info("Status of the Quote reservation : " + str);
		test_steps.add("Status of the Quote reservation  : " + str);
		assertTrue(str.equalsIgnoreCase("Pending"));
		test_steps.add("Verified Reservastion Status is Pending or not");
	}

	public void CloseOpenedReservation(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.OpenedReservation_CloseButton, driver);
		Utility.ScrollToElement(ReservationPage.OpenedReservation_CloseButton, driver);
		ReservationPage.OpenedReservation_CloseButton.click();
		reservationLogger.info("Clicked on Reservation Close Tab");
		test_steps.add("Clicked on  Reservation Close Tab");

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.CloseUnSavedDataTab, driver);
		ReservationPage.CloseUnSavedDataTab.click();
		reservationLogger.info("Clicked Yes in CloseUnSavedDataTab");
		test_steps.add("Clicked Yes in CloseUnSavedDataTab");

		// driver.navigate().refresh();
	}

	public void VerifyQuoteFromResSearch(WebDriver driver, ArrayList<String> test_steps, String QuoteNumber)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Elements_Reservation_SearchPage ReservationSearchPage = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.enterResNumber);
		ReservationSearchPage.enterResNumber.sendKeys(QuoteNumber);
		reservationLogger.info("Entered Quote Reservation number in Reservation search field (" + QuoteNumber + ")");
		test_steps
				.add("Entered Quote Reservation number in Reservation search [ResNumber] field (" + QuoteNumber + ")");

		Wait.WaitForElement(driver, OR.Click_BasicSearch);
		ReservationSearchPage.Click_BasicSearch.click();
		test_steps.add("Clicked on Basic Search in Reservation ");
		reservationLogger.info("Clicked on Basic Search in Reservation ");
		Wait.wait10Second();

		ReservationPage.BasicRes_Status_Check.getText();
		reservationLogger.info("Status :" + ReservationPage.BasicRes_Status_Check.getText());
		test_steps.add("Status of the Quote Reservation is '" + ReservationPage.BasicRes_Status_Check.getText() + "'");
		assertEquals(ReservationPage.BasicRes_Status_Check.getText(), "Quote");
		test_steps.add("Verified the NewQuote Status from the Reservation");
	}

	public void RuleChangesCheck(WebDriver driver, ArrayList<String> test_steps, String RoomClassName, String RuleName,
			boolean ruleApplicable) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Elements_RateQuote RateQuote = new Elements_RateQuote(driver);

		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		ReservationPage.RoomAssignmentButton.click();
		test_steps.add("Clicked on Room assignment picker");
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait2Second();

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.RoomAssignment_DateIcon, driver);
		ReservationPage.RoomAssignment_DateIcon.click();
		test_steps.add("Clicked on Date Icon");
		ReservationPage.SelectDate.click();
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}
		test_steps.add("Selected Today Date");

		ReservationPage.SearchRoomButton.click();
		Wait.wait2Second();
		test_steps.add("Clicked on Search");
		new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClassName);
		test_steps.add("Select Room Class: " + RoomClassName);
		reservationLogger.info("Select Room Class: " + RoomClassName);

		if (Utility.roomIndex == Utility.roomIndexEnd) {
			Utility.roomIndex = 1;
		}
		new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
		Utility.roomIndex++;
		Wait.wait5Second();

		if (ReservationPage.Reservation_Rule_Button.getText().isEmpty()) {
			test_steps.add("Rules Broken PopUP is Not DisPlayed in NewReservation");
			reservationLogger.info("Rules Broken PopUP is Not DisPlayed in NewReservation");

			reservationLogger.info("No Rule is Available in NewReservation With RuleName:  " + RuleName);
			test_steps.add("No Rule is Available in NewReservation Search With RuleName:  " + RuleName);
			test_steps.add("Because for today NoCheckIn rule is not Applicable");
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.NewRes_RoomAssign_Cancel, driver);
			ReservationPage.NewRes_RoomAssign_Cancel.click();
			Wait.wait2Second();
			reservationLogger.info("Clicked on cancel");
			test_steps.add("Clicked on Cancel");
		}

		else {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Reservation_Rule_Button, driver);
			ReservationPage.Reservation_Rule_Button.click();
			test_steps.add("Clicked on Rule");
			reservationLogger.info("Clicked on New Rule");

			if (ruleApplicable == true) {
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.Verify_RulesBroken_Popup, driver);
				if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
					test_steps.add("Rules Broken PopUP is DisPlayed in NewReservation");
					assertEquals(ReservationPage.Verify_RulesBroken_Popup.getText(), "Rules Broken",
							"Rule broken popup is not showing");
					test_steps.add("Verify Rules Broken PopUp in NewReservation");
					Wait.WaitForElement(driver, OR.RuleName_NewQuote);
					String finalString = RateQuote.RuleName_NewQuote.getText();
					reservationLogger.info("NewRule is Available in New Reservation With RuleName:  " + finalString);
					assertEquals(finalString, RuleName);
					test_steps.add("NewRule is Available in New Reservation With RuleName:  " + finalString);

					Wait.explicit_wait_visibilityof_webelement(RateQuote.Rules_Broken_popupQuoteOK, driver);
					RateQuote.Rules_Broken_popupQuoteOK.click();
					test_steps.add("Clicked on Ok");
					reservationLogger.info("Clicked on OK Res");
					Wait.WaitForElement(driver, OR.NewRes_RoomAssign_Cancel);
					ReservationPage.NewRes_RoomAssign_Cancel.click();
					Wait.wait2Second();
					reservationLogger.info("Clicked on cancel");
					test_steps.add("Clicked on Cancel");
				}
			}
		}
	}

	public ArrayList<String> roomAssignment_1(WebDriver driver, String RoomClassName, String RatePlan,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		ReservationPage.RoomAssignmentButton.click();
		reservationLogger.info("Clicked on Room Assignment Picker");
		test_steps.add("Clicked on Room Assignment Picker");
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.RoomAssignment_DateIcon, driver);
		ReservationPage.RoomAssignment_DateIcon.click();
		ReservationPage.SelectDate.click();
		test_steps.add("Selected Today Date From DatePicker");
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Select_RatePlan, driver);
		ReservationPage.Select_RatePlan.click();
		new Select(ReservationPage.Select_RatePlan).selectByVisibleText(RatePlan);
		reservationLogger.info("Selectd Rateplan From Reservation :" + RatePlan);
		test_steps.add("Selectd Rateplan From Reservation :" + RatePlan);

		if (ReservationPage.RoomAssign_Check.isSelected()) {
			reservationLogger.info("Available Reservation is for Assigned Rooms");
			test_steps.add("Available Reservation is for Assigned Rooms");
		}
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			reservationLogger.info("Available Reservation is for UnAssigned Rooms");
			test_steps.add("Available Reservation is for UnAssigned Rooms");
			ReservationPage.RoomAssign_Check.click();
			reservationLogger.info("Clicked on Checkbox");
			test_steps.add("Clicked on Checkbox");
		}

		ReservationPage.SearchRoomButton.click();
		Wait.wait15Second();
		reservationLogger.info("Clicked on Search in Room Assignment");
		test_steps.add("Clicked on Search in Room Assignment");

		try {
			if (ReservationPage.SelectRoomClasses.isDisplayed()) {
				reservationLogger.info("Rooms are Available for the " + RoomClassName + " RoomClass");
				test_steps.add("Rooms are Available for the " + RoomClassName + " RoomClass");
				new Select(ReservationPage.SelectRoomClasses).selectByVisibleText(RoomClassName);
				reservationLogger.info("Selected Room Class: " + RoomClassName);
				test_steps.add("Selected Room Class: " + RoomClassName);

				if (Utility.roomIndex == Utility.roomIndexEnd) {
					Utility.roomIndex = 1;
				}
				new Select(ReservationPage.SelectRoomNumbers).selectByIndex(Utility.roomIndex);
				Utility.roomIndex++;
				Wait.wait2Second();
				String CheckRule = ReservationPage.CheckRule.getText();
				String AvailableRoom = ReservationPage.AvailableRoom.getText();
				int CheckRoom = Integer.parseInt(AvailableRoom);
				ReservationPage.SelectButton.click();
				Wait.wait2Second();
				test_steps.add("Clicked on Select in Room Assignment");
				if (CheckRoom == 0 || CheckRoom < 0) {
					ReservationPage.Continue.get(9).click();
					Wait.wait2Second();
				}
				if (CheckRule.equals("1") || CheckRule.equals("2")) {
					ReservationPage.RoleBroken_Continue.click();
				}
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.Toaster_Message, driver);
				Utility.app_logs.info("Toaster message appears :" + ReservationPage.Toaster_Message.getText());
				test_steps.add("Toaster message appears :" + ReservationPage.Toaster_Message.getText());
			}
		}

		catch (Exception e) {
			if (ReservationPage.SearchPopUp.isDisplayed()) {
				reservationLogger.info("Rooms are not Available for the " + RoomClassName + " RoomClass");
				test_steps.add("Rooms are not Available for the " + RoomClassName + " RoomClass");
				reservationLogger.info(ReservationPage.SearchPopUp.getText());
				test_steps.add(ReservationPage.SearchPopUp.getText());
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.CancelRoomAssignment_Button, driver);
				ReservationPage.CancelRoomAssignment_Button.click();
				reservationLogger.info("Clicked on Cancel in Room Assignment");
				test_steps.add("Clicked on Cancel in Room Assignment");
			}
		}
		return test_steps;
	}

	public String RoomChargesCheck(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		String NewSetRate = ReservationPage.Room_Charges.getText();
		reservationLogger.info("New RoomCharges are: " + NewSetRate);
		test_steps.add("New RoomCharges are: " + NewSetRate);
		String finalString = Utility.convertDollarToNormalAmount(driver, NewSetRate);
		test_steps.add("New Rate is Available in New Reservation with BaseAmount of " + NewSetRate);
		reservationLogger.info("New Rate is Available in New Reservation with BaseAmount of " + NewSetRate);
		return finalString;
	}

	public ArrayList<String> RoomAssign_ForChangeNight_StayDate(WebDriver driver, String RoomChagerType,
			ArrayList<String> test_steps) throws InterruptedException, ParseException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		ReservationPage.RoomAssignmentButton.click();
		test_steps.add("Click on room assignmnet picker button");

		Wait.wait3Second();
		Wait.WaitForElement(driver, OR.NightField);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.NightField, driver);

		int night = Integer.parseInt(ReservationPage.NightField.getAttribute("value"));
		int afternight = night + 1;
		ReservationPage.NightField.clear();
		ReservationPage.NightField.sendKeys("" + afternight);

		test_steps.add("Change night stay from " + night + " to " + afternight);

		Wait.wait1Second();
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.SearchRoomButton, driver);
		ReservationPage.SearchRoomButton.click();

		Select select = new Select(ReservationPage.List_SelectRoomNumbers.get(0));
		List<WebElement> options = select.getOptions();
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().contains("V and C")) {
				new Select(ReservationPage.List_SelectRoomNumbers.get(0)).selectByVisibleText(options.get(i).getText());
				System.out.println("in break");

				break;
			}

		}

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.SelectButton, driver);
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.NoRoomCharger_RadioButton_RAP, driver);
		if (RoomChagerType.equals("Recalculate")) {
			ReservationPage.RecalculateFolio_RadioButton__RAP.click();
			test_steps.add("Click on radio button Recalculate");
		}
		if (RoomChagerType.equals("ApplyDelta")) {
			ReservationPage.ApplyDeltaEnabled_RadioButton__RAP.click();
			test_steps.add("Click on radio button Apply Rate Change for Changed Dates Only");
		}

		if (RoomChagerType.equals("StayDates")) {
			ReservationPage.NoRoomCharger_RadioButton_RAP.click();
			test_steps.add("Click on radio button No Room Charge Changed/Added");
		}
		ReservationPage.Select__RAP.click();
		test_steps.add("Click on select button in Room Charges Changed");

		Wait.wait1Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			ReservationPage.RoleBroken_Continue.click();
		}

		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);

		try {
			Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
			Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Toaster_Message, driver);
			ReservationPage.Toaster_Message.click();
		} catch (Exception e) {
			PolicyComparisionPopup(driver);
		}
		return test_steps;
	}

	public ArrayList<String> RoomAssign_ForChangeNight_ApplyDelta(WebDriver driver, String RoomChagerType,
			ArrayList<String> test_steps) throws InterruptedException, ParseException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		ReservationPage.RoomAssignmentButton.click();
		test_steps.add("Click on room assignmnet picker button");
		Wait.wait3Second();
		Wait.WaitForElement(driver, OR.NightField);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.NightField, driver);

		int night = Integer.parseInt(ReservationPage.NightField.getAttribute("value"));
		int afternight = night + 1;
		ReservationPage.NightField.clear();
		ReservationPage.NightField.sendKeys("" + afternight);

		test_steps.add("Change night stay from " + night + " to " + afternight);

		Wait.wait1Second();
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.SearchRoomButton, driver);
		ReservationPage.SearchRoomButton.click();

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.SelectButton, driver);

		Select select = new Select(ReservationPage.List_SelectRoomNumbers.get(0));
		List<WebElement> options = select.getOptions();
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().contains("V and C")) {
				new Select(ReservationPage.List_SelectRoomNumbers.get(0)).selectByVisibleText(options.get(i).getText());
				System.out.println("in break");
				break;
			}

		}

		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.NoRoomCharger_RadioButton_RAP, driver);
		if (RoomChagerType.equals("Recalculate")) {
			ReservationPage.RecalculateFolio_RadioButton__RAP.click();
			test_steps.add("Click on radio button Recalculate");
		}
		if (RoomChagerType.equals("ApplyDelta")) {
			ReservationPage.ApplyDeltaEnabled_RadioButton__RAP.click();
			test_steps.add("Click on radio button Apply Rate Change for Changed Dates Only");
		}

		if (RoomChagerType.equals("StayDates")) {
			ReservationPage.NoRoomCharger_RadioButton_RAP.click();
			test_steps.add("Click on radio button No Room Charge Changed/Added");
		}
		ReservationPage.Select__RAP.click();
		test_steps.add("Click on select button in Room Charges Changed");

		Wait.wait1Second();
		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			ReservationPage.RoleBroken_Continue.click();
		}

		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);

		try {
			Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
			Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Toaster_Message, driver);
			ReservationPage.Toaster_Message.click();
		} catch (Exception e) {
			PolicyComparisionPopup(driver);
		}
		return test_steps;
	}

	public ArrayList<String> CheckIn_WithCash(WebDriver driver, String PaymentType) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		WebDriverWait wait = new WebDriverWait(driver, 30);

		Wait.explicit_wait_elementToBeClickable(ReservationPage.Click_Checkin, driver);
		// action.moveToElement(ReservationPage.Click_Checkin).doubleClick().build().perform();

		ReservationPage.Click_Checkin.click();
		Wait.wait2Second();
		reservationLogger.info("Clicked on CheckIn button");
		test_steps.add("Clicked on CheckIn button");
		try {
			ReservationPage.isDirtyRoomPopup_Confirm.click();
			Wait.wait2Second();
		} catch (Exception e) {
			// TODO: handle exception
		}

		Wait.WaitForElement(driver, OR.Room_Assignment_PopUp);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Room_Assignment_PopUp, driver);

		Select select = new Select(ReservationPage.List_SelectRoomNumbers.get(0));
		List<WebElement> options = select.getOptions();
		for (int i = 0; i < options.size(); i++) {
			System.out.println("in long");
			if (options.get(i).getText().contains("V and C")) {
				new Select(ReservationPage.List_SelectRoomNumbers.get(0)).selectByVisibleText(options.get(i).getText());
				System.out.println("in break");
				break;
			}

		}

		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Select, driver);
		ReservationPage.Click_Select.click();
		reservationLogger.info("Clicked on select button of room assignment popup");
		test_steps.add("Clicked on select button of room assignment popup");

		try {
			Wait.WaitForElement(driver, OR.RecalculateFolio_RadioButton__RAP);
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.RecalculateFolio_RadioButton__RAP, driver);
			ReservationPage.RecalculateFolio_RadioButton__RAP.click();
			test_steps.add("Click on radio button Recalculate");
			ReservationPage.Select__RAP.click();
			test_steps.add("Click on select button in Room Charges Changed");
			Wait.wait2Second();

		} catch (Exception e) {

		}

		reservationLogger.info("Selected first available room with V and C status from the list");
		test_steps.add("Selected first available room with V and C status from the list");

		try {

			if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
				ReservationPage.Click_Continue_RuleBroken_Popup.click();
				reservationLogger.info("Clicked on continue button of RulesBroken Popup");
			} else {
				reservationLogger.info("Satisfied Rules");
			}

		} catch (Exception e) {

		}

		try {

			// Wait.WaitForElement(driver, OR.Payment_Popup);
			wait.until(ExpectedConditions.visibilityOf((ReservationPage.Payment_Popup)));

			if (ReservationPage.Payment_Popup.isDisplayed()) {
				Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
				Wait.wait2Second();
				Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);
				new Select(elements_All_Payments.Select_Paymnet_Method).selectByVisibleText(PaymentType);
				elements_All_Payments.Add_Pay_Button.click();
				test_steps.add("Click on add button");

				Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Payment_ModuleLoading)), 20);
				Wait.wait3Second();
				Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Click_Continue, driver);
				ArrayList<String> getSteps = new ArrayList<>();
				// getSteps = VerifyPolicyPercentage(driver, Percentage,
				// getSteps, false);
				test_steps.addAll(getSteps);
				test_steps.add("Verify Check IN policy with current balance, current payment and ending balance");

				elements_All_Payments.Click_Continue.click();
				test_steps.add("Click on payment continue button");
				try {
					Wait.waitForElementToBeGone(driver, elements_All_Payments.Click_Continue, 30);

				} catch (Exception e) {
					Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button,
							driver);
					elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
					Utility.app_logs.info("Click Continue again");
					Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println("before 5 second");
		Wait.wait5Second();

		Wait.WaitForElement(driver, OR.Click_on_confirm);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_on_confirm, driver);
		ReservationPage.Click_on_confirm.click();
		Wait.wait1Second();
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Toaster_Message, driver);
		try {
			ReservationPage.Toaster_Message.click();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return test_steps;
	}

	public ArrayList<String> RoomAssign_ForLongStays(WebDriver driver, String RoomClass, String NightStay,
			ArrayList<String> test_steps, boolean Recalculate, int DaysBack)
			throws InterruptedException, ParseException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);
		ReservationPage.RoomAssignmentButton.click();
		test_steps.add("Click on room assignmnet picker button");

		Wait.wait2Second();
		Utility.ElementFinderUntillNotShow(By.xpath(OR.ClickSearchRoomButton), driver);
		Wait.WaitForElement(driver, OR.NightField);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String DF = dateFormat.format(date);

		Calendar c = Calendar.getInstance();
		c.setTime(dateFormat.parse(DF));

		int totalStay = Integer.parseInt(NightStay);
		totalStay = totalStay - DaysBack;
		c.add(Calendar.DATE, -DaysBack);
		String arrivalDate = dateFormat.format(c.getTime());

		Wait.wait2Second();
		ReservationPage.CheckIn_Out_Field.clear();
		Wait.wait2Second();
		ReservationPage.CheckIn_Out_Field.get(0).click();
		// System.out.println(ReservationPage.CheckIn_Out_Field.get(0).getAttribute("value"));
		Wait.wait2Second();
		ReservationPage.CheckIn_Out_Field.clear();
		String size = ReservationPage.CheckIn_Out_Field.get(0).getAttribute("value");
		for (int i = 0; i < size.length(); i++) {
			ReservationPage.CheckIn_Out_Field.get(0).sendKeys(Keys.BACK_SPACE);

		}
		System.out.println("arrivalDate : " + arrivalDate);
		ReservationPage.CheckIn_Out_Field.get(0).sendKeys(arrivalDate);
		Wait.wait2Second();
		test_steps.add("Enter Arrvial date : " + arrivalDate);
		ReservationPage.EnterPromocode.click();

		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.NightField, driver);
		// ReservationPage.EnterPromocode.click();
		ReservationPage.NightField.click();
		ReservationPage.NightField.clear();
		ReservationPage.NightField.sendKeys(NightStay);
		Wait.wait1Second();
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}
		ReservationPage.SearchRoomButton.click();

		reservationLogger.info("RoomClass:" + RoomClass);

		Select select = new Select(ReservationPage.List_SelectRoomNumbers.get(0));
		List<WebElement> options = select.getOptions();
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().contains("V and C")) {
				new Select(ReservationPage.List_SelectRoomNumbers.get(0)).selectByVisibleText(options.get(i).getText());
				System.out.println("in break");

				break;
			}

		}

		test_steps.add("Click Select: Room Assignment");
		reservationLogger.info("Select Rooms");
		test_steps.add("Select room class: " + RoomClass);

		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);
		ReservationPage.SelectButton.click();

		try {
			Wait.wait2Second();
			if (CheckRoom == 0 || CheckRoom < 0) {
				ReservationPage.Continue.get(9).click();
			}
			if (CheckRule.equals("1") || CheckRule.equals("2")) {
				ReservationPage.RoleBroken_Continue.click();
			}
		} catch (Exception e) {

		}

		if (Recalculate) {
			Wait.wait1Second();
			ReservationPage.RecalculateFolio_RadioButton__RAP.click();
			test_steps.add("Click on radio button Recalculate");
			ReservationPage.Select__RAP.click();
			test_steps.add("Click on select button in Room Charges Changed");
			Wait.wait1Second();
		}

		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);

		try {
			Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
			Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Toaster_Message, driver);
			ReservationPage.Toaster_Message.click();
		} catch (Exception e) {
			PolicyComparisionPopup(driver);
		}
		Wait.wait2Second();
		return test_steps;
	}

	public void PolicyComparisionPopup(WebDriver driver) throws InterruptedException, ParseException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// Wait.WaitForElement(driver, OR.VerifyChangePolicyPopup);
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationPage.VerifyChangePolicyPopup, driver);
			ReservationPage.policyComparisionPopUp_Btn.click();
		} catch (Exception e) {

		}

	}

	// In house Operations

	public void search_Inhouse(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.InHouse_Box, driver);
		Elements_Reservations.InHouse_Box.click();
		reservationLogger.info("Click on InHouse folder");
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.InHouse_Box, driver);
		String Actuval_InHouse_Records = Elements_Reservations.RecordsInHoues.getText();
		test_steps.add(" In-Hose Records:: " + Actuval_InHouse_Records);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Records_Found, driver);
		reservationLogger.info("Actuval records" + Actuval_InHouse_Records);
		String Expected_InHouse_Records = Elements_Reservations.Records_Found.getText();
		test_steps.add("records displayed on top the grid on In-house page::  " + Expected_InHouse_Records);
		reservationLogger.info("Excpected Records are" + Expected_InHouse_Records);
		if (Actuval_InHouse_Records.equals(Expected_InHouse_Records)) {
			reservationLogger.info("In house records and grid display records are matching");
			test_steps.add("In house records and grid display records are matching");
		} else {
			reservationLogger.info("In house records and grid display records are not matching");
			test_steps.add("In house records and grid display records are not matching");
		}

	}

	public void verify_Coloumn_Headers(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Elements_Reservations.Check_Box_Option.getText();
		reservationLogger.info("Check box is avilable ");
		List<WebElement> Colomun_names = driver.findElements(By.xpath(OR.Coloumn_Headers));
		reservationLogger.info("Headers in table are below:" + Colomun_names);
		reservationLogger.info("Total headers found: " + Colomun_names.size());
		for (WebElement header : Colomun_names) {
			reservationLogger.info(header.getText());
		}
	}

	public void verifyPageNationGrid(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Pagenation, driver);
		Utility.ScrollToElement(Elements_Reservations.Pagenation, driver);
		Wait.isElementDisplayed(driver, Elements_Reservations.Pagenation);
		Elements_Reservations.Pagenation.isDisplayed();
		reservationLogger.info("Pagenation grid is avilable in the page");

	}

	public void verifyPageperItems(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Pagenation, driver);
		Utility.ScrollToElement(Elements_Reservations.ItemesPerpage, driver);
		Wait.isElementDisplayed(driver, Elements_Reservations.ItemesPerpage);
		Elements_Reservations.ItemesPerpage.click();
		String size = Elements_Reservations.ItemesPerpage.getText();
		reservationLogger.info(size);
		if (Elements_Reservations.ItemesPerpage.getAttribute("value").contains("20")) {
			reservationLogger.info("pagenation is avilable on page ");
		} else {
			reservationLogger.info("pagenation should not be avilable onPage ");
		}

	}

	public void verifyInHouse_Reservation_Report(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.InHouse_Reservation, driver);
		Elements_Reservations.InHouse_Reservation.click();
		reservationLogger.info("Reservation report is open sucessfully");
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Report_Print_PopUp, driver);
		Wait.WaitForElement(driver, OR.Report_Print_PopUp);
		Elements_Reservations.Report_Print_PopUp.click();
		reservationLogger.info("Sucessfully click print button");
	}

	public void verify_InHose_Bulk_Action(WebDriver driver, ArrayList<String> test_steps) {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Select_check_box, driver);
		List<WebElement> check_Box = driver.findElements(By.xpath(OR.Select_check_box));
		check_Box.get(0).click();
		reservationLogger.info("By deafult BulkAction field is desabled ");
		Wait.isElementDisplayed(driver, Elements_Reservations.InHouse_BulkAction);
		Elements_Reservations.InHouse_BulkAction.isEnabled();
		reservationLogger.info("Once select check box automatically BulkAction Field sulould be enabled");
		Assert.assertTrue(Elements_Reservations.InHouse_BulkAction.isEnabled());
		Wait.isElementDisplayed(driver, Elements_Reservations.InHouse_BulkAction);
		Elements_Reservations.InHouse_BulkAction.click();

	}

	public void verify_Bulk_Action_with_Multiple_CheckBOx(WebDriver driver, ArrayList<String> test_steps) {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		// Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Multiple_CheckBox,
		// driver);
		List<WebElement> check_Box = driver.findElements(By.xpath(OR.Multiple_CheckBox));
		for (int i = 0; i <= check_Box.size(); i++) {
			if (i == 5) {
				check_Box.get(i).click();
				Elements_Reservations.InHouse_BulkAction.click();
				reservationLogger
						.info("Once select Multiple check boxes automatically BulkAction Field sulould be enabled");
			}
		}

	}

	// All Arrivals operations

	public void search_All_ArriVal_Box(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.AllArrivals_Box, driver);
		Elements_Reservations.AllArrivals_Box.click();
		reservationLogger.info("All Arrivals page is open sucessfully");
		test_steps.add("All Arrivals page is open sucessfully");
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.Records_AllArrivals_Box, driver);
		String ActualRecords = Elements_Reservations.Records_AllArrivals_Box.getText();
		test_steps.add("All Arrivals display records::" + ActualRecords);
		String expectedRecords = Elements_Reservations.countsof_records.getText();
		if (ActualRecords.equals(expectedRecords)) {
			test_steps.add("All arrivals records and dispalyed on top of grid AllArrival records  were matched");
			reservationLogger
					.info("All arrivals records and dispalyed on top of grid AllArrival records  were matched");
		} else {
			test_steps.add("All arrivals records and dispalyed on top of grid AllArrival records  were not matched");
			reservationLogger
					.info("All arrivals records and dispalyed on top of grid AllArrival records  were not matched");

		}
		// Assert.assertEquals(ActualRecords, expectedRecords);
		test_steps.add("All arrivals records and dispalyed on top of grid AllArrival records  were matched");
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.countsof_records, driver);
		test_steps.add("Display records on top of the grid on All Arrivals page::" + expectedRecords);
		reservationLogger.info(Elements_Reservations.Records_AllArrivals_Box.getText());
		reservationLogger.info("both records are  matching sucessfully");

	}

	public void verify_Pending_Arrivals(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.InHouseRes, driver);
		Elements_Reservations.InHouseRes.getText();
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.AllArrivals_DropDown_Option, driver);
		Elements_Reservations.AllArrivals_DropDown_Option.click();
		test_steps.add("Click on AllArrivals_DropDown_Option");
		reservationLogger.info("All Arrivals dropdown  is enabled ");
		Elements_Reservations.pending_Arrivals.click();
		test_steps.add("Click on Pending Arrivals on Arrivals Dropdown");
		reservationLogger.info("pending arrivals page is opened sucessfully");
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.Actual_Pending_Records, driver);
		String Actual_Pending_Records = Elements_Reservations.Actual_Pending_Records.getText();
		test_steps.add("Pending Records in allArrivals" + Actual_Pending_Records);

		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.pending_Records_count, driver);
		String expected_Pending_records = Elements_Reservations.pending_Records_count.getText();
		test_steps.add("Pending Records in allArrivals" + expected_Pending_records);
		if (Actual_Pending_Records.equals(expected_Pending_records)) {
			test_steps.add("pending records and dispalyed on top of grid pending records were matched");
			reservationLogger.info("pending records and dispalyed on top of grid pending records were matched");

		} else {
			test_steps.add("pending records and dispalyed on top of grid pending records were not matched");
			reservationLogger.info("pending records and dispalyed on top of grid pending records were not matched");

		}

	}

	public void verify_Arrivals_And_Departure(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.pending_Dropdown, driver);
		// Elements_Reservations.AllArrivals_Box.click();
		Elements_Reservations.pending_Dropdown.click();
		test_steps.add("click on pending Dropdown");
		reservationLogger.info("All Arrivals dropdown  is enabled sucessfully");
		Elements_Reservations.Arrivals_and_Departure.click();
		test_steps.add("Click on all arrivals and departures ");
		reservationLogger.info("Arrivals and depature page is opened sucessfully");
		Wait.isElementDisplayed(driver, Elements_Reservations.Actuval_Records_Arrivals_and_Departure);
		String Actuval_Arrival_And_Departure_Records = Elements_Reservations.Actuval_Records_Arrivals_and_Departure
				.getText();
		test_steps.add("Arrival and departures records on reservation page:::" + Actuval_Arrival_And_Departure_Records);
		Wait.isElementDisplayed(driver, Elements_Reservations.Grid_Arrivals_and_Departure_Records);
		String Expected_Arrival_And_Departure_Records = Elements_Reservations.Grid_Arrivals_and_Departure_Records
				.getText();
		test_steps.add("Displayed Arrival and departures records on reservation page:::"
				+ Expected_Arrival_And_Departure_Records);
		if (Actuval_Arrival_And_Departure_Records.equals(Expected_Arrival_And_Departure_Records)) {
			test_steps.add(
					"Arrival and departures records are greter than   dispay on top of grid page records were matched ");
			reservationLogger.info("Arrivals and depature records are compared sucessfully");
		} else {
			test_steps.add(
					"Arrival and departures records are greter than   dispay on top of grid pafe records  were not matched");
			reservationLogger.info(
					"Arrival and departures records are greter than   dispay on top of grid pafe records  were not matched");
		}

	}

	public ArrayList<String> verify_SearchOperation_using_newResrvation(WebDriver driver,
			ArrayList<String> ReservationList, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		// read the records from inhouse box
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.InHouse_Box, driver);
		Elements_Reservations.InHouse_Box.click();
		test_steps.add("ckick on Inhouse-box");
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.InHouseRes, driver);
		String Inhouse_records = Elements_Reservations.InHouseRes.getText();
		test_steps.add("Inhouse_records : " + Inhouse_records);
		ReservationList.add("Inhouse_records : " + Elements_Reservations.InHouseRes.getText());
		reservationLogger.info("Inhouse_records: " + Elements_Reservations.InHouseRes.getText());
		// read the records from AllArrival box
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.AllArrivals_Box, driver);
		Elements_Reservations.AllArrivals_Box.click();
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.AllArrivals_Box, driver);
		Elements_Reservations.Records_AllArrivals_Box.getText();
		String AllArrivals_records = Elements_Reservations.Records_AllArrivals_Box.getText();
		test_steps.add("AllArrival_records : " + AllArrivals_records);
		ReservationList.add("AllArrival_records : " + Elements_Reservations.Records_AllArrivals_Box.getText());
		reservationLogger.info("AllArrival_records::" + Elements_Reservations.Records_AllArrivals_Box.getText());
		// read the records form pending arrivals.
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.AllArrivals_DropDown_Option, driver);
		Elements_Reservations.AllArrivals_DropDown_Option.click();
		Elements_Reservations.pending_Arrivals.click();
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.Actual_Pending_Records, driver);
		String Pending_Arrivals_Records = Elements_Reservations.Actual_Pending_Records.getText();
		test_steps.add("AllArrival_records : " + Pending_Arrivals_Records);
		ReservationList.add("AllArrival_records : " + Elements_Reservations.Actual_Pending_Records.getText());
		reservationLogger.info("pending arrival records::" + Pending_Arrivals_Records);
		// read the records from all arrivals and departures
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.pending_Dropdown, driver);
		Elements_Reservations.pending_Dropdown.click();
		reservationLogger.info("All Arrivals dropdown  is enabled sucessfully");
		Elements_Reservations.Arrivals_and_Departure.click();
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.Actuval_Records_Arrivals_and_Departure,
				driver);
		String Arrivals_departures_Records = Elements_Reservations.Actuval_Records_Arrivals_and_Departure.getText();
		test_steps.add("AllArrivalDepertures_records : " + Arrivals_departures_Records);
		ReservationList.add("AllArrivalDepertures_records : "
				+ Elements_Reservations.Actuval_Records_Arrivals_and_Departure.getText());
		reservationLogger.info("Arrivalsdepartures_Records::" + Arrivals_departures_Records);
		// read the records from all departures
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.All_Departures, driver);
		Elements_Reservations.All_Departures.click();
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Actuval_All_Departures_Records, driver);
		String All_Departures = Elements_Reservations.Actuval_All_Departures_Records.getText();
		test_steps.add("AllDepertures_records : " + All_Departures);
		ReservationList
				.add("AllDepertures_records : " + Elements_Reservations.Actuval_All_Departures_Records.getText());
		reservationLogger.info("All_Departures_records::" + All_Departures);
		// read the records from pending departures
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.All_Departures_Dropdown, driver);
		Elements_Reservations.All_Departures_Dropdown.click();
		Elements_Reservations.Pending_Departures.click();
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.Actuval_Pending_Departures_Records,
				driver);
		String Pending_Departures_records = Elements_Reservations.Actuval_Pending_Departures_Records.getText();
		test_steps.add("Pending Depertures_records : " + Pending_Departures_records);
		ReservationList.add(
				"Pending Depertures_records : " + Elements_Reservations.Actuval_Pending_Departures_Records.getText());
		reservationLogger.info("PendingDepartures_records::" + Pending_Departures_records);
		// read the records from All arrivals and departures
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Pending_departure_Dropdown, driver);
		Elements_Reservations.Pending_departure_Dropdown.click();
		Elements_Reservations.All_Arrival_Departures.click();
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.Actuval_AllArrivals_departures, driver);
		String All_Arrival_Departures_records = Elements_Reservations.Actuval_AllArrivals_departures.getText();
		test_steps.add("All Arrival Depertures_records : " + All_Arrival_Departures_records);
		ReservationList.add(
				"All Arrival Depertures_records : " + Elements_Reservations.Actuval_AllArrivals_departures.getText());
		reservationLogger.info("All_ArrivalDepartures_records" + All_Arrival_Departures_records);
		// read the records from unassingned
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Unassingned, driver);
		Elements_Reservations.Unassingned.click();
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.Unassingned, driver);
		String Unassigned_Records = Elements_Reservations.Unassingned.getText();
		test_steps.add("Unassingned records : " + Unassigned_Records);
		ReservationList.add("Unassingned records : " + Elements_Reservations.Unassingned.getText());
		reservationLogger.info("total records::" + Unassigned_Records);
		// read the records from new reservation
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.newreservation, driver);
		Elements_Reservations.newreservation.click();
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.newreservation, driver);
		String newreservation_Records = Elements_Reservations.newreservation.getText();
		test_steps.add("Unassingned records : " + newreservation_Records);
		ReservationList.add("NewReservation records : " + Elements_Reservations.newreservation.getText());
		reservationLogger.info("newreservation records::" + newreservation_Records);
		return ReservationList;
	}

	public void compareBrforeAndAfterLists(WebDriver driver, ArrayList<String> Before_Newreservation_Records,
			ArrayList<String> After_Newreservation_Records, ArrayList<String> test_steps) throws InterruptedException {
		// Before_Newreservation_Records.removeAll(Before_Newreservation_Records);

		boolean flag = false;
		for (int i = 0; i < After_Newreservation_Records.size(); i++) {
			System.out.println(i);
			if (Before_Newreservation_Records.get(i).contains(After_Newreservation_Records.get(i))) {

				flag = false;

			} else {
				flag = true;
				break;
			}

		}
		if (flag) {
			reservationLogger.info("Before reservation records and after reservation records are  not matched");
			test_steps.add("Before reservation records and after reservation records are  not matched");

		} else {
			reservationLogger.info("Before reservation records and after reservation records are  matched");
			test_steps.add("Before reservation records and after reservation records are  matched");

		}

	}

	public void verify_Records_AfterReservation(WebDriver driver, ArrayList<String> Before_Newreservation_Records,
			ArrayList<String> After_Newreservation_Records, ArrayList<String> test_steps) {

		reservationLogger.info("Before Reservation" + Before_Newreservation_Records.get(0) + "Afterreservation  "
				+ After_Newreservation_Records.get(0));
		test_steps.add("Before Reservation" + Before_Newreservation_Records.get(0) + "Afterreservation  "
				+ After_Newreservation_Records.get(0));
		reservationLogger.info("Before Reservation" + Before_Newreservation_Records.get(1) + "Afterreservation  "
				+ After_Newreservation_Records.get(1));
		test_steps.add("Before Reservation" + Before_Newreservation_Records.get(1) + "Afterreservation  "
				+ After_Newreservation_Records.get(1));
		reservationLogger.info("Before Reservation" + Before_Newreservation_Records.get(2) + "Afterreservation  "
				+ After_Newreservation_Records.get(2));
		test_steps.add("Before Reservation" + Before_Newreservation_Records.get(2) + "Afterreservation  "
				+ After_Newreservation_Records.get(2));
		reservationLogger.info("Before Reservation" + Before_Newreservation_Records.get(3) + "Afterreservation  "
				+ After_Newreservation_Records.get(3));
		test_steps.add("Before Reservation" + Before_Newreservation_Records.get(3) + "Afterreservation  "
				+ After_Newreservation_Records.get(3));
		reservationLogger.info("Before Reservation" + Before_Newreservation_Records.get(4) + "Afterreservation  "
				+ After_Newreservation_Records.get(4));
		test_steps.add("Before Reservation" + Before_Newreservation_Records.get(4) + "Afterreservation  "
				+ After_Newreservation_Records.get(4));
		reservationLogger.info("Before Reservation" + Before_Newreservation_Records.get(5) + "Afterreservation  "
				+ After_Newreservation_Records.get(5));
		test_steps.add("Before Reservation" + Before_Newreservation_Records.get(5) + "Afterreservation  "
				+ After_Newreservation_Records.get(5));
		reservationLogger.info("Before Reservation" + Before_Newreservation_Records.get(6) + "Afterreservation  "
				+ After_Newreservation_Records.get(6));
		test_steps.add("Before Reservation" + Before_Newreservation_Records.get(6) + "Afterreservation  "
				+ After_Newreservation_Records.get(6));
		reservationLogger.info("Before Reservation" + Before_Newreservation_Records.get(7) + "Afterreservation  "
				+ After_Newreservation_Records.get(7));
		test_steps.add("Before Reservation" + Before_Newreservation_Records.get(7) + "Afterreservation  "
				+ After_Newreservation_Records.get(7));
		reservationLogger.info("Before Reservation" + Before_Newreservation_Records.get(8) + "Afterreservation  "
				+ After_Newreservation_Records.get(8));
		test_steps.add("Before Reservation" + Before_Newreservation_Records.get(8) + "Afterreservation  "
				+ After_Newreservation_Records.get(8));
		// reservationLogger.info("Before
		// Reservation"+Before_Newreservation_Records.get(9)+"Afterreservation
		// "+After_Newreservation_Records.get(18));

	}

	public void search_All_departures(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.All_Departures, driver);
		Elements_Reservations.All_Departures.click();
		test_steps.add("All departures page open successfully");
		reservationLogger.info("All Departures page is opened successfully");
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.All_Departures_Records, driver);
		String ActualRecords = Elements_Reservations.All_Departures_Records.getText();
		test_steps.add("toatal no.of records in All departure::" + ActualRecords);
		reservationLogger.info("toatal no.of records in All departure::" + ActualRecords);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.visible_Records_All_Departures, driver);
		String expectedRecords = Elements_Reservations.visible_Records_All_Departures.getText();
		test_steps.add("Dispaly records on top of the grid on AllDeparture page::" + expectedRecords);
		reservationLogger.info("toatal no.of records in All departure::" + expectedRecords);
		if (ActualRecords.equals(expectedRecords)) {
			reservationLogger.info("both records are  matching successfully");
			test_steps.add("All Departurs records and dispaly on top the grid page  records are matching");

		} else {
			reservationLogger.info("both records are not  matching successfully");
			test_steps.add("All Departurs records and dispaly on top the grid page  records are not matching");
		}

	}

	public void verify_Pending_departures(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.All_Departures, driver);
		Elements_Reservations.All_Departures.click();
		test_steps.add("All departures page open successfully");
		reservationLogger.info("All Departures page is opened successfully");
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.All_departures_dropdown, driver);
		Elements_Reservations.All_departures_dropdown.click();
		test_steps.add("enable all departure dropdown on all departurs box");
		reservationLogger.info("All Departures dropdown  is enabled successfully");
		Elements_Reservations.Pendending_Departures.click();
		test_steps.add("pending departures page is open sucessfully");
		reservationLogger.info("pending departures page  is open opened sucessfully");
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Pendending_Departures_Records, driver);
		String Actual_Pending_Records = Elements_Reservations.Pendending_Departures_Records.getText();
		test_steps.add("toatal no.of records in pending departures::" + Actual_Pending_Records);
		reservationLogger.info("toatal no.of records in pending departures::" + Actual_Pending_Records);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.visible_Records_Pending_departures,
				driver);
		String expected_Pending_records = Elements_Reservations.visible_Records_Pending_departures.getText();
		test_steps.add("toatal no.of visable records in pending departures::" + expected_Pending_records);
		reservationLogger.info("toatal no.of visable records in pending departures::" + expected_Pending_records);
		if (Actual_Pending_Records.equals(expected_Pending_records)) {
			reservationLogger.info("Pending records are compared sucessfully");
			test_steps.add("Pending departures records and dispaly on top of the grid records are matching");
		} else {
			reservationLogger.info("Pending records are not compared sucessfully");
			test_steps.add("Pending departures records and dispaly on top of the grid records are not matching");
		}

	}

	public void verify_Arrivals_And_Departure_OnAlldepartures(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Pending_Departures_Dropdown, driver);
		// Elements_Reservations.AllArrivals_Box.click();
		Elements_Reservations.Pending_Departures_Dropdown.click();
		test_steps.add("All Departures pending  dropdown enable on Pendig departurs");
		reservationLogger.info("All Departures pending  dropdown  is enabled ");
		Elements_Reservations.All_Arrivals_And_departures.click();
		test_steps.add("Arrivals and depature page is opened successfully");
		reservationLogger.info("Arrivals and depature page is opened sucessfully");
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.All_Arrivals_And_departures_Records,
				driver);
		String Actuval_Arrival_And_Departure_Records = Elements_Reservations.All_Arrivals_And_departures_Records
				.getText();
		test_steps.add("toatal no.of records in arrival and departures::" + Actuval_Arrival_And_Departure_Records);

		reservationLogger
				.info("toatal no.of records in arrival and departures::" + Actuval_Arrival_And_Departure_Records);
		Wait.explicit_wait_visibilityof_webelement_150(
				Elements_Reservations.visible_Records_All_Arrivals_And_departures, driver);
		String Expected_Arrival_And_Departure_Records = Elements_Reservations.visible_Records_All_Arrivals_And_departures
				.getText();
		test_steps.add(
				"toatal no.of visible_Records on ArrivalsAnddepartures::" + Expected_Arrival_And_Departure_Records);
		reservationLogger.info(
				"toatal no.of visible_Records on ArrivalsAnddepartures::" + Expected_Arrival_And_Departure_Records);
		if (Actuval_Arrival_And_Departure_Records.equals(Expected_Arrival_And_Departure_Records)) {
			reservationLogger.info("Arrivals and depature records are compared sucessfully");
			test_steps.add("Arrivals and depature records are  not equal to dispaly on top of the grid records");
		} else {
			reservationLogger.info("Arrivals and depature records are not equal ");
			test_steps.add("Arrivals and depature records are  not equal to dispaly on top of the grid records");
		}

	}

	public ArrayList<String> verify_All_Departures_Records(WebDriver driver, ArrayList<String> allDeparturesRecordsList,
			ArrayList<String> test_steps) {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.All_Departures, driver);
		Elements_Reservations.All_Departures.click();
		reservationLogger.info("All Departures page is opened successfully");
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.All_Departures_Records, driver);
		String ActualRecords = Elements_Reservations.All_Departures_Records.getText();
		allDeparturesRecordsList.add(ActualRecords);
		test_steps.add("toatal no.of records in All departure::" + ActualRecords);
		reservationLogger.info("toatal no.of records in All departure::" + ActualRecords);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.All_departures_dropdown, driver);
		Elements_Reservations.All_departures_dropdown.click();
		test_steps.add("All Departures dropdown  is enabled successfully");
		reservationLogger.info("All Departures dropdown  is enabled successfully");
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Pendending_Departures_Records, driver);
		String Actual_Pending_Records = Elements_Reservations.Pendending_Departures_Records.getText();
		test_steps.add("total no.of pending records on all departures" + Actual_Pending_Records);
		allDeparturesRecordsList.add(Actual_Pending_Records);
		reservationLogger.info("toatal no.of records in pending departures::" + Actual_Pending_Records);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.All_Arrivals_And_departures_Records,
				driver);
		String Actuval_Arrival_And_Departure_Records = Elements_Reservations.All_Arrivals_And_departures_Records
				.getText();
		allDeparturesRecordsList.add(Actuval_Arrival_And_Departure_Records);
		reservationLogger
				.info("toatal no.of records in arrival and departures::" + Actuval_Arrival_And_Departure_Records);
		test_steps.add("toatal no.of records in arrival and departures::" + Actuval_Arrival_And_Departure_Records);
		// beforeReservation_AllDepartures_Records.addAll(allDeparturesRecordsList);
		System.out.println(beforeReservation_AllDepartures_Records);

		return allDeparturesRecordsList;

	}

	public ArrayList<String> searchOperationWithSpecificDate(WebDriver driver, ArrayList<String> searchOpeartionList,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.InHouse_Box);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.InHouse_Box, driver);
		// Elements_Reservations.InHouse_Box.click();
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.InHouseRes, driver);
		String Inhouse_records = Elements_Reservations.InHouseRes.getText();
		test_steps.add("Inhouse_records : " + Inhouse_records);
		searchOpeartionList.add("Inhouse_records : " + Elements_Reservations.InHouseRes.getText());
		reservationLogger.info("Inhouse_records: " + Elements_Reservations.InHouseRes.getText());
		// read the records from AllArrival box
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.AllArrivals_Box, driver);
		Elements_Reservations.AllArrivals_Box.click();
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.AllArrivals_Box, driver);
		Elements_Reservations.Records_AllArrivals_Box.getText();
		String AllArrivalss_records = Elements_Reservations.Records_AllArrivals_Box.getText();
		test_steps.add("AllArrival_records : " + AllArrivalss_records);
		searchOpeartionList.add("AllArrival_records : " + Elements_Reservations.Records_AllArrivals_Box.getText());
		reservationLogger.info("AllArrival_records::" + Elements_Reservations.Records_AllArrivals_Box.getText());
		// read the records form pending arrivals.
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.AllArrivals_DropDown_Option, driver);
		Elements_Reservations.AllArrivals_DropDown_Option.click();
		Elements_Reservations.pending_Arrivals.click();
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.Actual_Pending_Records, driver);
		String Pending_Arrivals_Records = Elements_Reservations.Actual_Pending_Records.getText();
		test_steps.add("AllArrival_records : " + Pending_Arrivals_Records);
		searchOpeartionList.add("AllArrival_records : " + Elements_Reservations.Actual_Pending_Records.getText());
		reservationLogger.info("pending arrival records::" + Pending_Arrivals_Records);
		// read the records from all arrivals and departures
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.pending_Dropdown, driver);
		Elements_Reservations.pending_Dropdown.click();
		reservationLogger.info("All Arrivals dropdown  is enabled ");
		Elements_Reservations.Arrivals_and_Departure.click();
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.Actuval_Records_Arrivals_and_Departure,
				driver);
		String Arrivals_departures_Records = Elements_Reservations.Actuval_Records_Arrivals_and_Departure.getText();
		test_steps.add("AllArrivalDepertures_records : " + Arrivals_departures_Records);
		searchOpeartionList.add("AllArrivalDepertures_records : "
				+ Elements_Reservations.Actuval_Records_Arrivals_and_Departure.getText());
		reservationLogger.info("Arrivalsdepartures_Records::" + Arrivals_departures_Records);
		// read the records from all departures
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.All_Departures, driver);
		Elements_Reservations.All_Departures.click();
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Actuval_All_Departures_Records, driver);
		String All_Departures = Elements_Reservations.Actuval_All_Departures_Records.getText();
		test_steps.add("AllDepertures_records : " + All_Departures);
		searchOpeartionList
				.add("AllDepertures_records : " + Elements_Reservations.Actuval_All_Departures_Records.getText());
		reservationLogger.info("All_Departures_records::" + All_Departures);
		// read the records from pending departures
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.All_Departures_Dropdown, driver);
		Elements_Reservations.All_Departures_Dropdown.click();
		Elements_Reservations.Pending_Departures.click();
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.Actuval_Pending_Departures_Records,
				driver);
		String Pending_Departures_records = Elements_Reservations.Actuval_Pending_Departures_Records.getText();
		test_steps.add("Pending Depertures_records : " + Pending_Departures_records);
		searchOpeartionList.add(
				"Pending Depertures_records : " + Elements_Reservations.Actuval_Pending_Departures_Records.getText());
		reservationLogger.info("PendingDepartures_records::" + Pending_Departures_records);
		// read the records from All arrivals and departures
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Pending_departure_Dropdown, driver);
		Elements_Reservations.Pending_departure_Dropdown.click();
		Elements_Reservations.All_Arrival_Departures.click();
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.Actuval_AllArrivals_departures, driver);
		String All_Arrival_Departures_records = Elements_Reservations.Actuval_AllArrivals_departures.getText();
		test_steps.add("All Arrival Depertures_records : " + All_Arrival_Departures_records);
		searchOpeartionList.add(
				"All Arrival Depertures_records : " + Elements_Reservations.Actuval_AllArrivals_departures.getText());
		reservationLogger.info("All_ArrivalDepartures_records" + All_Arrival_Departures_records);
		// read the records from unassingned
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Unassingned, driver);
		Elements_Reservations.Unassingned.click();
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.Unassingned, driver);
		String Unassigned_Records = Elements_Reservations.Unassingned.getText();
		test_steps.add("Unassingned records : " + Unassigned_Records);
		searchOpeartionList.add("Unassingned records : " + Elements_Reservations.Unassingned.getText());
		reservationLogger.info("total records::" + Unassigned_Records);
		// read the records from new reservation
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.newreservation, driver);
		Elements_Reservations.newreservation.click();
		Wait.explicit_wait_visibilityof_webelement_120(Elements_Reservations.newreservation, driver);
		String newreservation_Records = Elements_Reservations.newreservation.getText();
		test_steps.add("Unassingned records : " + newreservation_Records);
		searchOpeartionList.add("NewReservation records : " + Elements_Reservations.newreservation.getText());
		reservationLogger.info("newreservation records::" + newreservation_Records);
		test_steps.add("newreservation records::" + newreservation_Records);

		return searchOpeartionList;

	}

	public void verify_Search_operation_OnreservationPage_With_Current_date(WebDriver driver,
			ArrayList<String> cDateList, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Reservation reservation = new Reservation();
		Navigation nav = new Navigation();
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.calender_dropdown, driver);
		Elements_Reservations.calender_dropdown.click();
		test_steps.add("calender dropdown is enabled on the page");
		Date d = new Date();
		int current_date = d.getDate();
		reservationLogger.info("dispaly the current date::" + current_date);
		test_steps.add("dispaly the current date::" + current_date);
		WebElement pdate = driver
				.findElement(By.xpath("(//div[@class='datepicker-days']//*[text()='" + current_date + "'])[1]"));
		Wait.explicit_wait_visibilityof_webelement_150(pdate, driver);
		pdate.click();
		reservationLogger.info("successfully click the toady date");
		test_steps.add("select the current date successfully");
		nav.navigateToReservations(driver);
		reservation.searchOperationWithSpecificDate(driver, cDateList, test_steps);
		cDateList.addAll(searchOpeartionList);
		System.out.println("==========" + cDateList);

		searchOpeartionList.removeAll(searchOpeartionList);

	}

	public void verify_Search_operation_OnreservationPage_With_Next_date(WebDriver driver,
			ArrayList<String> nextDateList, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Reservation reservation = new Reservation();
		Navigation nav = new Navigation();
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.calender_dropdown, driver);
		Elements_Reservations.calender_dropdown.click();

		Date d = new Date();
		int next_date = d.getDate() + 1;
		reservationLogger.info("dispaly the Next date::" + next_date);
		test_steps.add("Find the next date from current date::" + next_date);
		WebElement pdate = driver
				.findElement(By.xpath("(//div[@class='datepicker-days']//*[text()='" + next_date + "'])[1]"));
		Wait.explicit_wait_visibilityof_webelement_150(pdate, driver);
		pdate.click();
		Wait.wait5Second();
		reservationLogger.info("successfully click the next date");
		test_steps.add("select the next date successfully");
		searchOpeartionList.removeAll(searchOpeartionList);
		reservation.searchOperationWithSpecificDate(driver, searchOpeartionList, test_steps);
		Wait.wait30Second();
		nextDateList.addAll(searchOpeartionList);

		System.out.println("=========" + nextDateList);

		boolean flag = false;
		for (int i = 0; i < cDateList.size(); i++) {

			if (cDateList.get(i).equals(nextDateList.get(i))) {
				flag = false;
			} else {
				flag = true;
			}
		}

		if (flag) {
			reservationLogger.info("Records are equal from current date to next date");
			test_steps.add("Records are equal from current date to next date");

		} else {
			reservationLogger.info("Records are veriation from current date to next date");
			test_steps.add("Records are veried from current date to next date");

		}
		searchOpeartionList.removeAll(searchOpeartionList);

	}

	public void verify_currentdate_and_NextDate_Records(WebDriver driver, ArrayList<String> cDateList,
			ArrayList<String> nextDateList, ArrayList<String> test_steps) {

		reservationLogger.info("CurrentDateRecords " + cDateList.get(0) + "NextDateRecords  " + nextDateList.get(0));
		test_steps.add("CurrentDateRecords " + cDateList.get(0) + "NextDateRecords  " + nextDateList.get(0));
		reservationLogger.info("CurrentDateRecords " + cDateList.get(1) + "NextDateRecords  " + nextDateList.get(1));
		test_steps.add("CurrentDateRecords " + cDateList.get(1) + "NextDateRecords  " + nextDateList.get(1));
		reservationLogger.info("CurrentDateRecords " + cDateList.get(2) + "NextDateRecords  " + nextDateList.get(2));
		test_steps.add("CurrentDateRecords " + cDateList.get(2) + "NextDateRecords  " + nextDateList.get(2));
		reservationLogger.info("CurrentDateRecords " + cDateList.get(3) + "NextDateRecords  " + nextDateList.get(3));
		test_steps.add("CurrentDateRecords " + cDateList.get(3) + "NextDateRecords  " + nextDateList.get(3));
		reservationLogger.info("CurrentDateRecords " + cDateList.get(4) + "NextDateRecords  " + nextDateList.get(4));
		test_steps.add("CurrentDateRecords " + cDateList.get(4) + "NextDateRecords  " + nextDateList.get(4));
		reservationLogger.info("CurrentDateRecords " + cDateList.get(5) + "NextDateRecords  " + nextDateList.get(5));
		test_steps.add("CurrentDateRecords " + cDateList.get(5) + "NextDateRecords  " + nextDateList.get(5));
		reservationLogger.info("CurrentDateRecords " + cDateList.get(6) + "NextDateRecords  " + nextDateList.get(6));
		test_steps.add("CurrentDateRecords " + cDateList.get(6) + "NextDateRecords  " + nextDateList.get(6));
		reservationLogger.info("CurrentDateRecords " + cDateList.get(7) + "NextDateRecords  " + nextDateList.get(7));
		test_steps.add("CurrentDateRecords " + cDateList.get(7) + "NextDateRecords  " + nextDateList.get(7));
		reservationLogger.info("CurrentDateRecords " + cDateList.get(8) + "NextDateRecords  " + nextDateList.get(8));
		test_steps.add("CurrentDateRecords " + cDateList.get(8) + "NextDateRecords  " + nextDateList.get(8));

	}

	public void verify_Search_operation_OnreservationPage_With_Previous_date(WebDriver driver,
			ArrayList<String> previousDateList, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Reservation reservation = new Reservation();
		Navigation nav = new Navigation();
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.calender_dropdown, driver);
		Elements_Reservations.calender_dropdown.click();

		Date d = new Date();
		int previous_date = d.getDate() - 1;
		reservationLogger.info("dispaly the previous date::" + previous_date);
		test_steps.add("select  the previous date from current date");
		WebElement pdate = driver
				.findElement(By.xpath("(//div[@class='datepicker-days']//*[text()='" + previous_date + "'])[1]"));
		Wait.explicit_wait_visibilityof_webelement_150(pdate, driver);
		pdate.click();
		test_steps.add("clickon previous date");
		reservationLogger.info("successfully click the previous date");

		reservation.searchOperationWithSpecificDate(driver, searchOpeartionList, test_steps);
		previousDateList.addAll(searchOpeartionList);

		boolean flag = false;
		for (int i = 0; i < cDateList.size(); i++) {
			if (cDateList.get(i).contains(previousDateList.get(i))) {
				flag = false;
				System.out.println(i);
			} else {
				flag = true;
			}
		}
		if (flag) {
			reservationLogger.info("Records are same from current date to previous  date");
			test_steps.add("Records are same from current date to previous  date");

		} else {
			reservationLogger.info("Records are veriation from current date to previous date");
			test_steps.add("Records are varid from current date to previous date");

		}

	}

	public void verify_currentdate_and_PreviousDaqte_Records(WebDriver driver, ArrayList<String> cDateList,
			ArrayList<String> previousDateList, ArrayList<String> test_steps) {

		reservationLogger
				.info("CurrentDateRecords " + cDateList.get(0) + "previousDateRecords  " + previousDateList.get(0));
		test_steps.add("CurrentDateRecords " + cDateList.get(0) + "Afterreservation  " + previousDateList.get(0));
		reservationLogger
				.info("CurrentDateRecords " + cDateList.get(1) + "previousDateRecords  " + previousDateList.get(1));
		test_steps.add("CurrentDateRecords " + cDateList.get(1) + "previousDateRecords  " + previousDateList.get(1));
		reservationLogger
				.info("CurrentDateRecords " + cDateList.get(2) + "previousDateRecords  " + previousDateList.get(2));
		test_steps.add("CurrentDateRecords " + cDateList.get(2) + "previousDateRecords  " + previousDateList.get(2));
		reservationLogger
				.info("CurrentDateRecords " + cDateList.get(3) + "previousDateRecords  " + previousDateList.get(3));
		test_steps.add("CurrentDateRecords " + cDateList.get(3) + "previousDateRecords  " + previousDateList.get(3));
		reservationLogger
				.info("CurrentDateRecords " + cDateList.get(4) + "previousDateRecords  " + previousDateList.get(4));
		test_steps.add("CurrentDateRecords " + cDateList.get(4) + "previousDateRecords  " + previousDateList.get(4));
		reservationLogger
				.info("CurrentDateRecords " + cDateList.get(5) + "previousDateRecords  " + previousDateList.get(5));
		test_steps.add("CurrentDateRecords " + cDateList.get(5) + "previousDateRecords  " + previousDateList.get(5));
		reservationLogger
				.info("CurrentDateRecords " + cDateList.get(6) + "previousDateRecords  " + previousDateList.get(6));
		test_steps.add("CurrentDateRecords " + cDateList.get(6) + "previousDateRecords  " + previousDateList.get(6));
		reservationLogger
				.info("CurrentDateRecords " + cDateList.get(7) + "previousDateRecords  " + previousDateList.get(7));
		test_steps.add("CurrentDateRecords " + cDateList.get(7) + "previousDateRecords  " + previousDateList.get(7));
		reservationLogger
				.info("CurrentDateRecords " + cDateList.get(8) + "previousDateRecords  " + previousDateList.get(8));
		test_steps.add("CurrentDateRecords " + cDateList.get(8) + "previousDateRecords  " + previousDateList.get(8));

	}

	public void compare_Before_newReservation_records_and_After_NewReservation_Records(WebDriver driver,
			ArrayList<String> AfterReservation_AllDepartures_Records, ArrayList<String> test_steps) {

		Reservation reservation = new Reservation();
		reservation.verify_All_Departures_Records(driver, AfterReservation_AllDepartures_Records, test_steps);

		AfterReservation_AllDepartures_Records.addAll(allDeparturesRecordsList);
		System.out.println(AfterReservation_AllDepartures_Records);
		for (String records : AfterReservation_AllDepartures_Records) {
			test_steps.add(" new reservation after all departures records :" + records);
			reservationLogger.info("after new reservation all departures records values:" + records);
		}
		boolean flag = false;
		for (int i = 0; i < beforeReservation_AllDepartures_Records.size(); i++) {
			if (beforeReservation_AllDepartures_Records.get(i).equals(AfterReservation_AllDepartures_Records.get(i))) {
				flag = false;
			} else {
				flag = true;
			}
		}
		if (flag) {
			reservationLogger.info("Records are same  from beforeresrvation and afterReservation");
			test_steps.add("Records are same  from beforeresrvation and afterReservation");

		} else {
			reservationLogger.info("Records are veriation from beforeresrvation and afterReservation");
			test_steps.add("Records are veriation from beforeresrvation and afterReservation");

		}

	}

	public void verify_Before_and_AfterResrvation_Records_AllAepartures(WebDriver driver,
			ArrayList<String> beforeReservation_AllDepartures_Records,
			ArrayList<String> AfterReservation_AllDepartures_Records, ArrayList<String> test_steps) {

		reservationLogger.info("Before Reservation" + beforeReservation_AllDepartures_Records.get(0)
				+ "Afterreservation  " + AfterReservation_AllDepartures_Records.get(0));
		test_steps.add("Before Reservation" + beforeReservation_AllDepartures_Records.get(0) + "Afterreservation    "
				+ AfterReservation_AllDepartures_Records.get(0));
		reservationLogger.info("Before Reservation" + beforeReservation_AllDepartures_Records.get(1)
				+ "Afterreservation  " + AfterReservation_AllDepartures_Records.get(1));
		test_steps.add("Before Reservation" + beforeReservation_AllDepartures_Records.get(1) + "Afterreservation    "
				+ AfterReservation_AllDepartures_Records.get(1));
		reservationLogger.info("Before Reservation" + beforeReservation_AllDepartures_Records.get(2)
				+ "Afterreservation  " + AfterReservation_AllDepartures_Records.get(2));
		test_steps.add("Before Reservation" + beforeReservation_AllDepartures_Records.get(2) + "Afterreservation    "
				+ AfterReservation_AllDepartures_Records.get(2));

	}

	public void verify_Pending_departures_count_current_and_next_Dates(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Navigation nav = new Navigation();
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.All_Departures, driver);
		Elements_Reservations.All_Departures.click();
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.All_departures_dropdown, driver);
		Elements_Reservations.All_departures_dropdown.click();
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Pendending_Departures_Records, driver);
		String present_Pending_Departure_Records = Elements_Reservations.Pendending_Departures_Records.getText();
		reservationLogger.info("Present date pendingDeparture_records::" + present_Pending_Departure_Records);
		test_steps.add("Present date pendingDeparture_records::" + present_Pending_Departure_Records);
		String Present_Arrival_And_Departure_Records = Elements_Reservations.All_Arrivals_And_departures_Records
				.getText();
		AfterReservation_AllDepartures_Records.add(Present_Arrival_And_Departure_Records);
		reservationLogger
				.info("present Present Arrival And Departure Records::" + Present_Arrival_And_Departure_Records);
		test_steps.add("present Present Arrival And Departure Records::" + Present_Arrival_And_Departure_Records);
		nav.navigateToReservations(driver);
		Elements_Reservation Elements_Reservations1 = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations1.calender_dropdown, driver);
		Elements_Reservations.calender_dropdown.click();
		Date date = new Date();
		int next_day = date.getDate() + 1;
		test_steps.add("select the next date from current date" + next_day);
		reservationLogger.info("dispaly the next date::" + next_day);
		WebElement ndate = driver
				.findElement(By.xpath("(//div[@class='datepicker-days']//*[text()='" + next_day + "'])[1]"));
		Wait.explicit_wait_visibilityof_webelement_150(ndate, driver);
		ndate.click();
		reservationLogger.info("successfully click the next date");
		test_steps.add("click on next date on calender");
		nav.navigateToReservations(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.All_Departures, driver);
		Elements_Reservations.All_Departures.click();
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.All_departures_dropdown, driver);
		Elements_Reservations.All_departures_dropdown.click();
		Wait.explicit_wait_visibilityof_webelement_150(Elements_Reservations.Pendending_Departures_Records, driver);
		String Nextdate_Pending_Departure_Records = Elements_Reservations.Pendending_Departures_Records.getText();
		test_steps.add("Next date pendingDeparture_records::" + Nextdate_Pending_Departure_Records);
		reservationLogger.info("Next date pendingDeparture_records::" + Nextdate_Pending_Departure_Records);
		if (Nextdate_Pending_Departure_Records.equals(present_Pending_Departure_Records)) {
			reservationLogger.info("in both dates Pendeing records are matching");

		} else {
			reservationLogger.info("in both dates Pendeing records are not matching");

		}

	}

	public void VerifyRoomClass(WebDriver driver, String RoomClass) throws InterruptedException {

		Elements_Reservation elements_Reservation = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.RoomClass_Number);
		Wait.explicit_wait_visibilityof_webelement(elements_Reservation.RoomClass_Number, driver);
		Utility.ScrollToElement(elements_Reservation.RoomClass_Number, driver);
		assertEquals(elements_Reservation.RoomClass_Number.getText().split(":")[0].replace(" ", ""),
				RoomClass.replace(" ", ""));

	}

	public void VerifyNote(WebDriver driver, String Category, String NoteType, String Detail, int index)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		String path = "(//tbody[contains(@data-bind,'foreach : taskList')]//tr)[" + index + "]//td";
		System.out.println(path);
		List<WebElement> TaskValue = driver.findElements(By.xpath(path));

		Wait.WaitForElement(driver, path);
		Utility.ScrollToElement(TaskValue.get(0), driver);
		assertEquals(TaskValue.get(0).getText(), Category, "Failed : Note Category does not find");
		assertEquals(TaskValue.get(1).getText(), NoteType, "Failed : Note Type does not find");
		assertEquals(TaskValue.get(2).getText(), Detail, "Failed : Note Detail does not find");

	}

	public ArrayList<String> AddNewTask(WebDriver driver, String Category, String NoteType, String details,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		// Wait.explicit_wait_visibilityof_webelement(ReservationPage.GestInfo_Tab,
		// driver);
		// ReservationPage.GestInfo_Tab.click();
		Wait.WaitForElement(driver, OR.AddTaskButton);
		Utility.ScrollToElement(ReservationPage.AddTaskButton, driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.AddTaskButton, driver);
		Utility.ScrollToElement(ReservationPage.AddTaskButton, driver);

		ReservationPage.AddTaskButton.click();
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.TaskPopup, driver);
		new Select(ReservationPage.Select_TaskCategory).selectByVisibleText(Category);
		test_steps.add("Select notes category : " + Category);

		new Select(ReservationPage.Select_Note_Type1).selectByVisibleText(NoteType);
		test_steps.add("Select updated note type : " + NoteType);

		ReservationPage.Enter_Note_Details1.sendKeys(details);
		test_steps.add("Enter note details : " + details);
		Wait.wait1Second();

		ReservationPage.Click_Save_Note1.click();
		try {
			Wait.waitForElementToBeGone(driver, ReservationPage.Click_Save_Note1, 5);
		} catch (Exception e) {
			ReservationPage.Click_Save_Note1.click();
			Wait.waitForElementToBeGone(driver, ReservationPage.Click_Save_Note1, 5);
		}

		Wait.wait1Second();

		return test_steps;

	}

	public ArrayList<String> RoomAssigned_RatePlan_OverridenRate(WebDriver driver, ExtentTest test, String RoomClass,
			String Nights, String Adults, String RatePlan, ArrayList<String> test_steps)
			throws InterruptedException, IOException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationPage.RoomAssignmentButton, driver);
		Utility.ScrollToElement(ReservationPage.RoomAssignmentButton, driver);

		ReservationPage.RoomAssignmentButton.click();
		Utility.ElementFinderUntillNotShow(By.xpath(OR.ClickSearchRoomButton), driver);
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignmentButton);
		Utility.ElementFinderUntillNotShow(By.xpath(OR.ClickSearchRoomButton), driver);
		jse.executeScript("arguments[0].click();", ReservationPage.RoomAssignment_DateIcon);
		jse.executeScript("arguments[0].click();", ReservationPage.SelectDate);

		Wait.wait2Second();
		if (!ReservationPage.RoomAssign_Check.isSelected()) {
			ReservationPage.RoomAssign_Check.click();
		}

		ReservationPage.Enter_Nigts.sendKeys(Keys.chord(Keys.CONTROL, "a"), Nights);

		ReservationPage.Enter_Adults.sendKeys(Keys.chord(Keys.CONTROL, "a"), Adults);

		ReservationPage.Enter_Children.sendKeys(Keys.chord(Keys.CONTROL, "a"), Adults);

		// Select Rate Plan

		new Select(ReservationPage.Reservation_RoomAssign_RatePlan).selectByVisibleText(RatePlan);
		// assertTrue(ReservationPage.Reservation_RatePlan.getAttribute("value").equals(RatePlan),
		// "Rate Plans didn't selected");
		test_steps.add(RatePlan + " Rate Plan Selected");

		Wait.wait2Second();
		ReservationPage.SearchRoomButton.click();
		// Select room classes and room
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.SelectRoomClass.get(0), driver);
		new Select(ReservationPage.SelectRoomClass.get(0)).selectByVisibleText(RoomClass);

		new Select(ReservationPage.SelectRoomNumber.get(0)).selectByIndex(2);

		reservationLogger.info("RoomClass:" + RoomClass);

		// Wait.wait2Second();
		String CheckRule = ReservationPage.CheckRule.getText();
		String AvailableRoom = ReservationPage.AvailableRoom.getText();
		int CheckRoom = Integer.parseInt(AvailableRoom);

		ReservationPage.SelectButton.click();

		if (CheckRoom == 0 || CheckRoom < 0) {
			Wait.wait2Second();
			ReservationPage.Continue.get(9).click();
			Wait.wait2Second();
		}
		if (CheckRule.equals("1") || CheckRule.equals("2")) {
			Wait.wait2Second();
			ReservationPage.RoleBroken_Continue.click();
		}
		// Actions action = new Actions(driver);
		Wait.wait2Second();
		try {
			System.out.println("in try:" + ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed());

			if (ReservationPage.ReCalculate_Folio_Options_PopUp.isDisplayed()) {
				ReservationPage.ReCal_Folio_Options_PopUp_Recalculate.click();
				Wait.wait2Second();
				ReservationPage.ReCal_Folio_Options_PopUp_Select_Btn.click();
				System.out.println("here:1");

			}

		} catch (Exception e) {
			System.out.println("catch ");
			reservationLogger.info("No ReCalculate Folio Options PopUp");
		}
		try {

			Wait.explicit_wait_visibilityof_webelement(ReservationPage.Toaster_Message, driver);
			String Text = ReservationPage.Toaster_Message.getText();
			System.out.println("Toaster Message appear : " + Text);

			ReservationPage.Toaster_Message.click();
			if (Text.contains(
					"The existing room is not available for the criteria you provided. Please either change the criteria, or check the �Split Room� check box and try again.")) {
				reAssignRoom(driver);
				ReservationPage.Toaster_Message.click();

			}
			test_steps.add("Reservation" + Text);
		} catch (Exception e) {

			boolean size = driver.findElements(By.cssSelector(OR.CancelDepositePolicy_Button)).size() > 0;
			System.out.println("Size:" + size);
			if (size) {
				Wait.wait1Second();
				System.out.println("Deposite Policy");
				ReservationPage.CancelDepositePolicy_Button.get(3).click();
				Wait.wait1Second();

			}
			try {
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.Toaster_Message, driver);

				if (ReservationPage.Toaster_Title.isDisplayed()) {
					String getTotasterTitle_ReservationSucess = ReservationPage.Toaster_Title.getText();
					test_steps.add("Reservation" + ReservationPage.Toaster_Message.getText());
					ReservationPage.Toaster_Title.click();
					System.out.println(getTotasterTitle_ReservationSucess);
					if (getTotasterTitle_ReservationSucess.contains("Saved")) {
						Assert.assertEquals(getTotasterTitle_ReservationSucess, "Reservation Saved");

					} else if (getTotasterTitle_ReservationSucess.equals("Error while saving.")) {
						reAssignRoom(driver);
						PolicyPopup(driver);
					}

				}
			} catch (Exception e2) {
				reservationLogger.info("in finally");
				String reservationNumber = GetReservationnumber(driver);
				CloseOpenedReservation(driver);
				driver.navigate().refresh();
				ReservationSearch reservationSearch = new ReservationSearch();
				reservationSearch.basicSearch_WithResNumber(driver, reservationNumber, true);
				Wait.wait2Second();
				Wait.WaitForElement(driver, OR.FolioTab_Reservation);
				Wait.explicit_wait_visibilityof_webelement(ReservationPage.FolioTab_Reservation, driver);
				ReservationPage.FolioTab_Reservation.click();
				reservationLogger.info("after folio");
				test_steps.add(reservationNumber);
			}

		}
		Wait.wait3Second();
		return test_steps;
	}

	public ArrayList<String> verifyRoomCharges_OverridenRate(WebDriver driver, ArrayList<Float> V0, ArrayList<Float> V1,
			String LineItemDescription, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Utility.ScrollToElement(ReservationPage.RoomCharges, driver);
		ReservationPage.Include_Taxes_in_Line_Items_Checkbox.click();
		Wait.wait2Second();
		List<WebElement> LineItemAmount = driver.findElements(By.xpath("//a[contains(text(),'" + LineItemDescription
				+ "')]//parent::td//following-sibling::td[@class='lineitem-amount']/span"));

		System.out.println("Size :" + LineItemAmount);
		Wait.explicit_wait_visibilityof_webelement(LineItemAmount.get(1), driver);
		Utility.ScrollToElement(LineItemAmount.get(1), driver);
		String Amount = LineItemAmount.get(0).getText();
		Amount = Amount.split(" ")[1];

		// For V0 Formula
		float FinalRoomChargesV0 = (V0.get(1) + V0.get(4) + V0.get(5) + (2 * V0.get(9)));
		String FinalRoomClassChargesValueV0 = (Float.toString(FinalRoomChargesV0)) + "0";

		// assert
		assertEquals(Amount.contains(FinalRoomClassChargesValueV0), true, "V0 Amount didn't macth");
		test_steps.add("V0 Amount : " + Amount + " Verfied");
		// For V1
		float FinalRoomChargesV1 = (V1.get(0) + (2 * V1.get(4)) + (2 * V1.get(9)));
		String FinalRoomClassChargesValueV1 = (Float.toString(FinalRoomChargesV1)) + "0";
		System.out.println("F:" + V1.get(0) + " S:" + (2 * V1.get(4)) + " T:" + (2 * V1.get(9)));
		Amount = LineItemAmount.get(2).getText();
		Amount = Amount.split(" ")[1];
		// assert
		assertEquals(Amount.contains(FinalRoomClassChargesValueV1), true, "V1 Amount didn't macth");

		test_steps.add("V1 Amount : " + Amount + " Verfied");

		// For V1 & V0 Coexist
		if (FinalRoomChargesV0 > FinalRoomChargesV1) {
			System.out.print("if");
			Amount = LineItemAmount.get(1).getText();
			Amount = Amount.split(" ")[1];
			assertEquals(Amount.contains(FinalRoomClassChargesValueV1), true, "V1 & V0 didn't coexist");
			test_steps.add("V1 & V0 Coexist Amount : " + Amount + " Verfied");

		} else {
			System.out.print("else");

			Amount = LineItemAmount.get(1).getText();
			Amount = Amount.split(" ")[1];
			assertEquals(Amount, FinalRoomChargesV0, "V1 & V0 didn' coexist");

			test_steps.add("V1 & V0 Coexist Amount : " + Amount + " Verfied");

		}
		WebElement LineItemDes = driver
				.findElement(By.xpath("(//a[contains(text(),'" + LineItemDescription + "')])[1]"));
		Utility.ScrollToElement(LineItemDes, driver);
		// Click Overiden Rate
		LineItemDes.click();
		Wait.wait2Second();
		Wait.WaitForElement(driver, OR.Item_Details_Popup);
		assertEquals(ReservationPage.Item_Details_Popup.isDisplayed(), true, " Item detail popup didn't display");
		test_steps.add("Item detail Page Opened");

		// Verify
		WebElement Item_Detail_Desc = driver
				.findElement(By.xpath("(//span[contains(text(),'" + LineItemDescription + "')])[2]"));
		assertEquals(Item_Detail_Desc.isDisplayed(), true, " Item Detail Desc is a hyperlink");
		Utility.ScrollToElement(ReservationPage.Item_Details_Popup_Cancel_Btn, driver);
		ReservationPage.Item_Details_Popup_Cancel_Btn.click();

		test_steps.add("Item detail Decription isn't hyperlink verified");

		return test_steps;

	}
	public String GetBalance_Folio(WebDriver driver) throws InterruptedException {

		Wait.wait5Second();
		String Balance = driver
				.findElement(By
						.xpath("//label[contains(text(),'Balance: ')]/following-sibling::span[@class='pamt']/span[@class='pamt']"))
				.getText();
		return Utility.convertDollarToNormalAmount(driver, Balance);

	}
	
	//Added by Adhnan
	public void closePaymentDetailPopup(WebDriver driver,String buttonName) throws InterruptedException {

		String cancelPath = "//div[@id='ReservationPaymetItemDetail']//button[text()='"+buttonName+"']";
		WebElement cancelBtn = driver.findElement(By.xpath(cancelPath));
		Wait.explicit_wait_visibilityof_webelement(cancelBtn, driver);
		Wait.explicit_wait_elementToBeClickable(cancelBtn, driver);
		Utility.ScrollToElement(cancelBtn, driver);
		cancelBtn.click();
		Utility.app_logs.info("Clicked on continue button");
		Wait.wait3Second();
	}
	
	
	
}