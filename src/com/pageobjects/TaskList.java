package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import javax.imageio.ImageIO;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.innroad.inncenter.interfaces.ITaskList;
import com.innroad.inncenter.pages.NewTax;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Tax;
import com.innroad.inncenter.webelements.WebElementsRoomStatus;
import com.innroad.inncenter.webelements.WebElements_TaskList;
import com.relevantcodes.extentreports.ExtentTest;

public class TaskList implements ITaskList {

	ArrayList<String> test_steps = new ArrayList<>();
	// Added By Aqsa
	public static Logger tasklistLogger = Logger.getLogger("TaskList");

	public void VerifyTaskList(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);

		// Verify Date
		assertEquals(TaskList.TaskList_Date.getAttribute("value"), GetSystemDate(false),
				"Date is not same as system date");
		tasklistLogger.info("Date Verified");
		test_steps.add("Date Verified");

		// Verify Task List Note Type
		boolean NoteTypeInternal = TaskList.TaskList_Note_Internal.isSelected();
		assertEquals(NoteTypeInternal, true, "NoteTypeInternal is not checked");
		tasklistLogger.info("Note Type Internal is Selected");
		test_steps.add("Note Type Internal is Selected");

		boolean NoteTypeHousekeeping = TaskList.TaskList_Note_HouseKeeping.isSelected();
		assertEquals(NoteTypeHousekeeping, true, "NoteTypeHousekeeping is not checked");
		tasklistLogger.info("Note Type HouseKeeping is Selected");
		test_steps.add("Note Type HouseKeeping is Selected");
		boolean NoteTypeRequest = TaskList.TaskList_Note_Request.isSelected();
		assertEquals(NoteTypeRequest, true, "NoteTypeRequest is not checked");
		tasklistLogger.info("Note Type Request is Selected");
		test_steps.add("Note Type Request is Selected");
		boolean NoteTypeWakeUpCall = TaskList.TaskList_Note_WakeupCall.isSelected();
		assertEquals(NoteTypeWakeUpCall, true, "NoteTypeWakeUpCall is not checked");
		tasklistLogger.info("Note Type WakeUpCall is Selected");
		test_steps.add("Note Type WakeUpCall is Selected");
		boolean NoteTypeGuestNote = TaskList.TaskList_Note_GuestNote.isSelected();
		assertEquals(NoteTypeGuestNote, true, "NoteTypeGuestNote is not checked");
		tasklistLogger.info("Note Type GuestNote is Selected");
		test_steps.add("Note Type GuestNote is Selected");
		boolean NoteTypeMessage = TaskList.TaskList_Note_Message.isSelected();
		assertEquals(NoteTypeMessage, true, "NoteTypeMessage is not checked");
		tasklistLogger.info("Note Type Message is Selected");
		test_steps.add("Note Type Message is Selected");
		boolean NoteTypeDepositRequired = TaskList.TaskList_Note_DepositRequired.isSelected();
		assertEquals(NoteTypeDepositRequired, true, "NoteTypeDepositRequired is not checked");
		tasklistLogger.info("Note Type DepositRequired is Selected");
		test_steps.add("Note Type DepositRequired is Selected");
		boolean NoteTypEmail = TaskList.TaskList_Note_Email.isSelected();
		assertEquals(NoteTypEmail, true, "NoteTypEmail is not checked");
		tasklistLogger.info("Note Type Email is Selected");
		test_steps.add("Note Type Email is Selected");
		boolean NoteTypeRoomMove = TaskList.TaskList_Note_RoomMove.isSelected();
		assertEquals(NoteTypeRoomMove, true, "NoteTypeRoomMove is not checked");
		tasklistLogger.info("Note Type RoomMove is Selected");
		test_steps.add("Note Type RoomMove is Selected");
		boolean NoteTypeEarlyArrival = TaskList.TaskList_Note_EarlyArrival.isSelected();
		assertEquals(NoteTypeEarlyArrival, true, "NoteTypeRoomMove is not checked");
		tasklistLogger.info("Note Type EarlyArrival is Selected");
		test_steps.add("Note Type EarlyArrival is Selected");
		boolean NoteTypeLateArrival = TaskList.TaskList_Note_LateArrival.isSelected();
		assertEquals(NoteTypeLateArrival, true, "NoteTypeLateArrival is not checked");
		tasklistLogger.info("Note Type LateArrival is Selected");
		test_steps.add("Note Type LateArrival is Selected");
		boolean NoteTypeLateDeparture = TaskList.TaskList_Note_LateDeparture.isSelected();
		assertEquals(NoteTypeLateDeparture, true, "NoteTypeLateDeparture is not checked");
		tasklistLogger.info("Note Type LateDeparture is Selected");
		test_steps.add("Note Type LateDeparture is Selected");
		boolean NoteTypeCancellation = TaskList.TaskList_Note_Cancellation.isSelected();
		assertEquals(NoteTypeCancellation, true, "NoteTypeCancellation is not checked");
		tasklistLogger.info("Note Type Cancellation is Selected");
		test_steps.add("Note Type Cancellation is Selected");
		boolean NoteTypeFrontDesk = TaskList.TaskList_Note_FrontDesk.isSelected();
		assertEquals(NoteTypeFrontDesk, true, "NoteTypeFrontDesk is not checked");
		tasklistLogger.info("Note Type FrontDesk is Selected");
		test_steps.add("Note Type FrontDesk is Selected");
		boolean NoteTypePakringInformation = TaskList.TaskList_Note_ParkingInformation.isSelected();
		assertEquals(NoteTypePakringInformation, true, "NoteTypePakringInformation is not checked");
		tasklistLogger.info("Note Type PakringInformation is Selected");
		test_steps.add("Note Type PakringInformation is Selected");
		boolean NoteTypeADA = TaskList.TaskList_Note_ADA.isSelected();
		assertEquals(NoteTypeADA, true, "NoteTypeADA is not checked");
		tasklistLogger.info("Note Type ADA is Selected");
		test_steps.add("Note Type ADA is Selected");
		// Verify Action
		Select selectActionType = new Select(TaskList.TaskList_ActionType);
		WebElement ActionTypeoptions = selectActionType.getFirstSelectedOption();
		String ActionType = ActionTypeoptions.getText();
		assertEquals(ActionType, "Pending", "ActionType is not correct");
		tasklistLogger.info("Verify Action : " + ActionType);
		test_steps.add("Verify Action : " + ActionType);
		// Verify Task List IncludePastDueItems
		boolean IncludePastDueItems = TaskList.TaskList_IncludePastDueItems.isSelected();
		assertEquals(IncludePastDueItems, true, "NoteTypeInternal is not checked");
		tasklistLogger.info("Include Past Due Items is Selected");
		test_steps.add("Include Past Due Items is Selected");

		// Verify Task List SelectAllNoteTypes
		boolean SelectAllNoteTypes = TaskList.TaskList_SelectALlNoteTypes.isSelected();
		assertEquals(SelectAllNoteTypes, true, "NoteTypeHousekeeping is not checked");

		tasklistLogger.info("Select All Note Types is Selected");
		test_steps.add("Select All Note Types is Selected");

		// Verify Go Button
		boolean GoButton = TaskList.TaskList_GoButton.isDisplayed();
		assertEquals(GoButton, true, "Go Button is not displayed");
		tasklistLogger.info("Go Button is Displayed");
		test_steps.add("Go Button is Displayed");

		// Verify Columns

		WebElement Type = driver.findElement(By.xpath("//*[@id='MainContent_dgLineItems']/tbody/tr[1]/td[2]"));
		String TypeColumn = Type.getText();
		assertEquals(TypeColumn, "Type", "ColumnName is not Type");
		tasklistLogger.info("Verify Type Column : " + TypeColumn);
		test_steps.add("Verify Type Column : " + TypeColumn);
		WebElement Subject = driver.findElement(By.xpath("//*[@id='MainContent_dgLineItems']/tbody/tr[1]/td[3]"));
		String SubjectColumn = Subject.getText();
		assertEquals(SubjectColumn, "Subject", "ColumnName is not Subject");
		tasklistLogger.info("Verify Subject Column : " + SubjectColumn);
		test_steps.add("Verify Subject Column" + SubjectColumn);
		WebElement AccOrRes = driver.findElement(By.xpath("//*[@id='MainContent_dgLineItems']/tbody/tr[1]/td[4]"));
		String AccOrResColumn = AccOrRes.getText();
		assertEquals(AccOrResColumn, "Acc/Res", "ColumnName is not Acc/Res");
		tasklistLogger.info("Verify Acc/Res Column : " + AccOrResColumn);
		test_steps.add("Verify Acc/Res Column : " + AccOrResColumn);
		WebElement Room = driver.findElement(By.xpath("//*[@id='MainContent_dgLineItems']/tbody/tr[1]/td[5]"));
		String RoomColumn = Room.getText();
		assertEquals(RoomColumn, "Room", "ColumnName is not Room");
		tasklistLogger.info("Verify Room Column : " + RoomColumn);
		test_steps.add("Verify Room Column : " + RoomColumn);
		WebElement AccOrGuestName = driver
				.findElement(By.xpath("//*[@id='MainContent_dgLineItems']/tbody/tr[1]/td[6]"));
		String AccOrGuestNameColumn = AccOrGuestName.getText();
		assertEquals(AccOrGuestNameColumn, "Account/Guest Name", "ColumnName is not AccOrGuestName");
		tasklistLogger.info("Verify AccOrGuestName Column : " + AccOrGuestNameColumn);
		test_steps.add("Verify AccOrGuestName Column : " + AccOrGuestNameColumn);
		WebElement Due = driver.findElement(By.xpath("//*[@id='MainContent_dgLineItems']/tbody/tr[1]/td[7]"));
		String DueColumn = Due.getText();
		assertEquals(DueColumn, "Due", "ColumnName is not Due");
		tasklistLogger.info("Verify Due Column : " + DueColumn);
		test_steps.add("Verify Due Column : " + DueColumn);
		WebElement Action = driver.findElement(By.xpath("//*[@id='MainContent_dgLineItems']/tbody/tr[1]/td[8]"));
		String ActionColumn = Action.getText();
		assertEquals(ActionColumn, "Action", "ColumnName is not ActionColumn");
		tasklistLogger.info("Verify Action Column : " + ActionColumn);
		test_steps.add("Verify Action Column : " + ActionColumn);
		//

		// Save Button

		boolean SaveButton = TaskList.TaskList_SaveButton.isDisplayed();
		assertEquals(SaveButton, true, "Save Button is not displayed");
		tasklistLogger.info("Save Button is Displayed");
		test_steps.add("Save Button is Displayed");
		// New Button

		boolean NewButton = TaskList.TaskList_NewButton.isDisplayed();
		assertEquals(NewButton, true, "New Button is not displayed");
		tasklistLogger.info("New Button is Displayed");
		test_steps.add("New Button is Displayed");
		// Cancel Button

		boolean CancelButton = TaskList.TaskList_CancelButton.isDisplayed();
		assertEquals(CancelButton, true, "Cancel Button is not displayed");
		tasklistLogger.info("Cancel Button is Displayed");
		test_steps.add("Cancel Button is Displayed");
		// Page HyperLink
		// Wait.wait2Second();

		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;

			boolean PageNumberHyperLink = TaskList.TaskList_PageHyperLink.isDisplayed();
			Wait.wait1Second();
			if (PageNumberHyperLink) {
				jse.executeScript("arguments[0].scrollIntoView();", TaskList.TaskList_PageHyperLink);
				assertEquals(PageNumberHyperLink, true, "PageNumber is Not Hyperlink");
				Utility.app_logs.info("Verify PageNumberHyperLink : " + PageNumberHyperLink);
				test_steps.add("Verify PageNumberHyperLink : " + PageNumberHyperLink);
			} else {
				WebElement pageNumberHL = driver
						.findElement(By.xpath("//tr[last()]/td/span[contains(@style,'font-weight:bold')]"));
				if (pageNumberHL.getText().equalsIgnoreCase("1")) {
					Utility.app_logs.info("Pagination have Only One Page");
					test_steps.add("Pagination have Only One Page");
				} else {
					assertTrue(false, "Failed: Pagination have multiple pages");
				}
			}
		} catch (Exception e) {
			WebElement pageNumberHL = driver
					.findElement(By.xpath("//tr[last()]/td/span[contains(@style,'font-weight:bold')]"));
			if (pageNumberHL.getText().equalsIgnoreCase("1")) {
				Utility.app_logs.info("Pagination have Only One Page");
				test_steps.add("Pagination have Only One Page");
			} else {
				assertTrue(false, "Failed: Pagination have multiple pages");
			}
		}

		//

		TaskList.TaskList_Select_Subject_Click.click();

		Wait.wait25Second();
		// Verify Subject Action

		boolean NotesDetails = TaskList.TaskList_Notes_Details.get(0).isDisplayed();

		assertEquals(NotesDetails, true, "NoteTypePakringInformation is not checked");
		tasklistLogger.info("table All Notes with pending status is Displayed");
		test_steps.add("table All Notes with pending status is Displayed");

	}

	public void taskListSearchCriteria(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);

		// Select the Action as All in Task List
		Wait.WaitForElement(driver, OR.selectAction);
		new Select(TaskList.selectAction).selectByVisibleText("--ALL--");

		// Uncheck Include Past Due Items
		Wait.WaitForElement(driver, OR.includePastDueItems);
		if (TaskList.includePastDueItems.isSelected() == true) {

			TaskList.includePastDueItems.click();

		}

		// Uncheck Internal
		TaskList.Internal.click();

		// UncheckHouseKeeping
		// TaskList.HouseKeeping.click();

		// Request
		TaskList.Request.click();

		// Wake-up Call
		TaskList.WakeUpCall.click();

		// Guest Note
		TaskList.GuestNote.click();

		// Complaint
		TaskList.Complaint.click();

		// Message
		TaskList.Message.click();

		// Deposit required
		TaskList.DepositRequired.click();

		// Email
		TaskList.Email.click();

		// Room Move
		TaskList.RoomMove.click();

		// Early Arrival
		TaskList.EarlyArrival.click();

		// Late Arrival
		TaskList.LastArrival.click();

		// Late Departure
		TaskList.LateDeparture.click();

		// Cancellation
		TaskList.Cancellation.click();

		// Front Desk
		TaskList.FrontDesk.click();

		// Parking Information
		TaskList.ParkingInformation.click();

		// ADA
		TaskList.ADA.click();

		// Click on Go button
		Wait.WaitForElement(driver, OR.taskListGoButton);
		Wait.wait5Second();
		TaskList.taskListGoButton.click();
		Wait.wait10Second();
	}

	public void notesValidation(WebDriver driver, String subject, String reservation, ExtentTest test,
			ArrayList<String> test_steps) throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);

		// boolean hyperlink=
		// driver.findElement(By.xpath("//tr[@align='right']/td/a")).isDisplayed();
		try {
			// if(driver.findElement(By.xpath("//tr[@align='right']/td/span")).isDisplayed())
			// {

			Wait.WaitForElement(driver, OR.taskListRows);
			List<WebElement> taskRows = TaskList.taskListRows;
			List<WebElement> taskCols = TaskList.taskListCols;

			System.out.println("Task Rows size" + taskRows.size());
			for (int i = 2; i <= taskRows.size() - 1; i++) {

				if ((driver.findElement(By.xpath("//table[@class='dgText']/tbody/tr[" + i + "]/td[3]")).getText()
						.equalsIgnoreCase(subject) &&

						driver.findElement(By.xpath("//table[@class='dgText']/tbody/tr[" + i + "]/td[4]")).getText()
								.contains(reservation)))

				{

					Wait.wait5Second();
					tasklistLogger
							.info(" Displayed Notes " + subject + " with Reservation " + reservation + " in TaskList");
					Wait.wait5Second();
					test_steps.add(" Displayed Notes " + subject + " with Reservation " + reservation + " in TaskList");
					Wait.wait5Second();

					tasklistLogger.info(" Displayed Notes in Task List");

					test_steps.add("Displayed Notes in Task List");
					Wait.wait10Second();

				}

			}

		}

		catch (Exception e) {

			if (driver.findElement(By.xpath("//tr[@align='right']/td/a")).isDisplayed()) {

				List<WebElement> hyperlinks = driver.findElements(By.xpath("//tr[@align='right']/td/a"));

				System.out.println("Hyperlinks: " + hyperlinks.size());

				for (int j = 1; j <= hyperlinks.size(); j++) {

					WebElement element = driver.findElement(By.xpath("//tr[@align='right']/td/a[" + j + "]"));

					WebElement navigateElement = driver.findElement(By.xpath("//tr[@align='right']/td/a"));

					Wait.explicit_wait_visibilityof_webelement(navigateElement, driver);

					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("arguments[0].scrollIntoView();", navigateElement);

					driver.findElement(By.xpath("//tr[@align='right']/td/a[" + j + "]")).click();

					Wait.WaitForElement(driver, OR.taskListRows);
					List<WebElement> taskRows = TaskList.taskListRows;
					List<WebElement> taskCols = TaskList.taskListCols;

					for (int i = 2; i <= taskRows.size() - 1; i++) {

						if (driver.findElement(By.xpath("//table[@class='dgText']/tbody/tr[" + i + "]/td[3]")).getText()
								.equalsIgnoreCase(subject) &&

								driver.findElement(By.xpath("//table[@class='dgText']/tbody/tr[" + i + "]/td[4]"))
										.getText().contains(reservation)) {

							Wait.wait5Second();
							tasklistLogger.info(" Displayed Notes " + subject + " with Reservation " + reservation
									+ " in TaskList");
							Wait.wait5Second();
							test_steps.add(
									" Displayed Notes " + subject + " with Reservation " + reservation + "in TaskList");
							Wait.wait5Second();

							tasklistLogger.info(" Displayed Notes in Task List");

							test_steps.add("Displayed Notes in Task List");
							Wait.wait10Second();

							// test_steps.add("Successfully Deleted the Notes in
							// Task List");

						} // if block end**********

					} // for(int i=2; i<=taskRows.size()-1;i++) end********

				} // for(int j=1;j<=hyperlinks.size();j++) end*******

			} // else if(hyperlink==true) end****

		}
	}

	public void SearchTask(WebDriver driver, String subject, String NoteType, String reservation)
			throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);

		String xpath1 = "//tr[@align='right']/td/span";

		WebElement page1 = driver.findElement(By.xpath(xpath1));

		String xpath2 = "//tr[@align='right']/td/a";

		WebElement link = driver.findElement(By.xpath(xpath2));

		List<WebElement> hyperlink = driver.findElements(By.xpath(xpath2));

		boolean links = driver.findElement(By.xpath(xpath2)).isDisplayed();

		/*
		 * int hyperlinkno=driver.findElements(By.xpath(xpath2)).size();
		 * System.out.println(" Hyperlinks size: " +hyperlinkno);
		 */

		notesValidationsInTaskList(driver, subject, NoteType, reservation, test_steps);

		if (!links) {

			// notesValidationsInTaskList(driver, subject, NoteType,
			// reservation);

			// System.out.println("Notes Found in FirstPage");

			// test_steps.add("Notes Found in FirstPage");

			Wait.WaitForElement(driver, OR.taskListRows);
			List<WebElement> taskRows = TaskList.taskListRows;
			List<WebElement> taskCols = TaskList.taskListCols;

			for (int i = 2; i <= taskRows.size() - 1; i++) {

				if (driver.findElement(By.xpath("//table[@class='dgText']/tbody/tr[" + i + "]/td[3]")).getText()
						.equalsIgnoreCase(subject) &&

						driver.findElement(By.xpath("//table[@class='dgText']/tbody/tr[" + i + "]/td[4]")).getText()
								.contains(reservation)) {

					Wait.wait5Second();
					tasklistLogger
							.info(" Displayed Notes " + subject + " with Reservation " + reservation + " in TaskList");
					Wait.wait5Second();
					test_steps.add(" Displayed Notes " + subject + " with Reservation " + reservation + "in TaskList");
					Wait.wait5Second();

					tasklistLogger.info(" Displayed Notes in Task List First Page");

					Wait.wait10Second();

					// test_steps.add("Successfully Deleted the Notes in Task
					// List");

				}

				break;
			}

		}

		else {

			if (links == true) {

				Wait.WaitForElement(driver, OR.taskListRows);
				List<WebElement> taskRows = TaskList.taskListRows;
				List<WebElement> taskCols = TaskList.taskListCols;

				for (int i = 2; i <= taskRows.size() - 1; i++) {

					for (int j = 1; j <= hyperlink.size(); j++) {

						WebElement PageLink = driver.findElement(By.xpath("//tr[@align='right']/td/a[" + j + "]"));

						PageLink.click();

						System.out.println(" Clicked on Hyperlink ");

						if (driver.findElement(By.xpath("//table[@class='dgText']/tbody/tr[" + i + "]/td[3]")).getText()
								.equalsIgnoreCase(subject) &&

								driver.findElement(By.xpath("//table[@class='dgText']/tbody/tr[" + i + "]/td[4]"))
										.getText().contains(reservation)) {

							Wait.wait5Second();
							tasklistLogger.info(" Displayed Notes " + subject + " with Reservation " + reservation
									+ " in TaskList");
							Wait.wait5Second();
							test_steps.add(
									" Displayed Notes " + subject + " with Reservation " + reservation + "in TaskList");
							Wait.wait5Second();

							tasklistLogger.info(" Displayed Notes in Task List");

							Wait.wait10Second();

							// test_steps.add("Successfully Deleted the Notes in
							// Task List");

						}

					}

					break;
				}
			}

		}

	}

	public void SearchTask1(WebDriver driver, String subject, String NoteType, String reservation)
			throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);

		String xpath1 = "//tr[@align='right']/td/span";

		WebElement page1 = driver.findElement(By.xpath(xpath1));

		String xpath2 = "//tr[@align='right']/td/a";

		WebElement link = driver.findElement(By.xpath(xpath2));

		List<WebElement> hyperlink = driver.findElements(By.xpath(xpath2));

		boolean links = driver.findElement(By.xpath(xpath2)).isDisplayed();

		/*
		 * int hyperlinkno=driver.findElements(By.xpath(xpath2)).size();
		 * System.out.println(" Hyperlinks size: " +hyperlinkno);
		 */

		notesValidationsInTaskList(driver, subject, NoteType, reservation, test_steps);

		if (!links) {

			notesValidationsInTaskList(driver, subject, NoteType, reservation, test_steps);

			System.out.println("Notes Found in FirstPage");

			test_steps.add("Notes Found in FirstPage");

		}

		else {

			if (links) {

				int hyperlinkno = driver.findElements(By.xpath(xpath2)).size();
				System.out.println(" Hyperlinks size: " + hyperlinkno);
				Wait.wait5Second();
				JavascriptExecutor jse = (JavascriptExecutor) driver;

				jse.executeScript("arguments[0].scrollIntoView();", link);

				for (int i = 1; i <= hyperlink.size(); i++) {

					// jse.executeScript("arguments[0].scrollIntoView(true);",
					// ReservationPage.Reservation_CreateGuestProfile);
					Wait.wait5Second();

					jse.executeScript("window.scrollBy(0,250)", "");
					// jse.executeScript("arguments[0].scrollIntoView();",
					// link);
					Wait.wait5Second();

					hyperlink.get(hyperlinkno++).click();

					Wait.wait10Second();

					notesValidationsInTaskList(driver, subject, NoteType, reservation, test_steps);
					// break;

				}
				System.out.println("Notes ");

				test_steps.add("Notes not found in first page");
			}

		}

	}

	public void SearchTax(WebDriver driver, String TaxName, boolean click) throws InterruptedException {

		Elements_Tax tax = new Elements_Tax(driver);
		boolean element = driver.findElements(By.cssSelector(NewTax.TaxPagesSize)).size() > 0;
		int element_size = 0;
		int index = 0;
		boolean isTaxCreated = false;
		List<WebElement> TaxElements = null;

		if (!element) {
			Utility.app_logs.info("Tax found in first page");
			// single page
			element_size = driver.findElements(By.cssSelector(NewTax.FindTaxName)).size();

			if (element_size > 1) {
				TaxElements = driver.findElements(By.cssSelector(NewTax.FindTaxName));
				for (int j = 0; j < element_size; j++) {
					if (TaxElements.get(j).getText().contains(TaxName)) {
						isTaxCreated = true;
						index = j;
						break;
					}
				}
			}
		} else {

			int size = driver.findElements(By.cssSelector(NewTax.TaxPagesSize)).size();
			System.out.println("Size:" + size);
			System.out.print("Size:" + size);
			for (int i = 0; i < size; i++) {

				element_size = driver.findElements(By.cssSelector(NewTax.FindTaxName)).size();
				System.out.println("Element Size:" + element_size);

				isTaxCreated = false;
				if (element_size > 1) {
					TaxElements = driver.findElements(By.cssSelector(NewTax.FindTaxName));
					for (int j = 0; j < element_size; j++) {
						// Utility.app_logs.info(TaxElements.get(j).getText());
						if (TaxElements.get(j).getText().equals(TaxName)) {
							Utility.app_logs.info(" Tax name found ");
							isTaxCreated = true;
							index = j;
							// assertEquals(isTaxCreated, true, "Newely creatd
							// Tax does not find in Tax list");
							break;
						}
					}

				}
				if (isTaxCreated == false) {
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("window.scrollBy(0,450)", "");
					System.out.println("Index:" + i);

					WebElement TaxElement = driver.findElement(By.cssSelector(NewTax.TaxPagesSize));
					TaxElement.click();

					Wait.wait2Second();
				}

			}
		}

		if (click) {
			String TaxPath = "//a[.='" + TaxName + "']";
			WebElement CreatedTax = driver.findElement(By.xpath(TaxPath));
			Utility.ScrollToElement(CreatedTax, driver);
			CreatedTax.click();
			Wait.explicit_wait_visibilityof_webelement(tax.NewTaxItem_Title, driver);
		}

	}

	public void notesValidationsInTaskList(WebDriver driver, String subject, String NoteType, String reservation,
			ArrayList<String> test_steps) throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);

		// Get the Notes and Reservation number
		Wait.WaitForElement(driver, OR.taskListRows);
		List<WebElement> taskRows = TaskList.taskListRows;
		List<WebElement> taskCols = TaskList.taskListCols;

		for (int i = 2; i <= taskRows.size() - 1; i++) {

			if (driver.findElement(By.xpath("//table[@class='dgText']/tbody/tr[" + i + "]/td[3]")).getText()
					.equalsIgnoreCase(subject) &&

					driver.findElement(By.xpath("//table[@class='dgText']/tbody/tr[" + i + "]/td[4]")).getText()
							.contains(reservation)) {

				Wait.wait5Second();
				tasklistLogger
						.info(" Displayed Notes " + subject + " with Reservation " + reservation + " in TaskList");
				Wait.wait5Second();
				test_steps.add(" Displayed Notes " + subject + " with Reservation " + reservation + "in TaskList");
				Wait.wait5Second();

				tasklistLogger.info(" Displayed Notes in Task List");

				test_steps.add("Displayed Notes in Task List");
				Wait.wait10Second();

				// test_steps.add("Successfully Deleted the Notes in Task
				// List");

			}

			break;
		}
	}

	public String GetSystemDate(boolean isTwoChar) {

		DateFormat dateInstance = SimpleDateFormat.getDateInstance();
		dateInstance.setTimeZone(TimeZone.getTimeZone("EST"));
		String date = dateInstance.format(Calendar.getInstance().getTime());
		String SplitDate[] = date.split(" ");
		String month = SplitDate[0];
		String day = SplitDate[1];
		String d[] = day.split(",");
		String year = SplitDate[2];
		if (isTwoChar) {
			if (Integer.parseInt(d[0]) < 10) {
				day = "0" + d[0];
			}
		}
		String formate = month + " " + day + " " + year;
		return formate;

	}

	@Override
	public void ClickNewButton(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		try {

			TaskList.TaskList_NewButton.click();
		} catch (Exception e) {

			TaskList.TaskList_NewButton1.click();
		}

	}

	@Override
	public void VerifyNewTaskListItem(WebDriver driver) throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);

		// Verify New TaskList Action Type
		Select selectNewTaskListType = new Select(TaskList.NewTaskList_Type);
		WebElement NewTypeoptions = selectNewTaskListType.getFirstSelectedOption();
		String NewType = NewTypeoptions.getText();
		assertEquals(NewType, "Internal", "NewActionType is not correct");

		// Verify New TaskList Subject
		assertEquals(TaskList.NewTaskList_Subject.getAttribute("value"), "", "Subject Value is not empty");

		// New TaskList Subject Picker Button

		boolean SubjectPickerButton = TaskList.NewTaskList_SubjectPicker.isDisplayed();
		assertEquals(SubjectPickerButton, true, "SubjectPickerButton is not displayed");

		// Verify New TaskList Acc/Res
		assertEquals(TaskList.NewTaskList_Acc_Res.getAttribute("value"), null, "Acc_Res Value is not null");

		// Room Reservation Button

		boolean RoomResButton = TaskList.NewTaskList_Room_ResBtn.isDisplayed();
		assertEquals(RoomResButton, true, "RoomResButton is not displayed");

		// Room Account Button
		boolean ActionCompleteButton = TaskList.NewTaskList_Room_AccountBtn.isDisplayed();
		assertEquals(ActionCompleteButton, true, "RoomAccountButton is not displayed");

		// Due Date
		TaskList.TaskList_DatePicker.click();
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
		assertTrue(
				TaskList.TaskList_Date.getAttribute("value")
						.equals(CurrentMonthAbbrevation + " " + CurrentDate + ", " + CurrentYear),
				"Selcted audit date isn't displayed");

		// Action Buttons

		boolean RoomAccountButton = TaskList.NewTaskList_ActionComplete.isDisplayed();
		assertEquals(RoomAccountButton, true, "ActionCompleteButton is not displayed");

		boolean ActionCancelButton = TaskList.NewTaskList_ActionCancel.isDisplayed();
		assertEquals(ActionCancelButton, true, "ActionCancelButton is not displayed");

		boolean ActionDeleteButton = TaskList.NewTaskList_ActionDelete.isDisplayed();
		assertEquals(ActionDeleteButton, true, "ActionDeleteButton is not displayed");

	}

	public void Select_NoteType(WebDriver driver) {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		for (int i = 0; i < 17; i++) {
			WebElement CurrentNoteType = driver.findElement(By.id("MainContent_chkNoteType_" + i));

			if (CurrentNoteType.isSelected()) {

				CurrentNoteType.click();

			}
		}
		// TaskList.TaskList_Note_Email.click();
		// TaskList.TaskList_GoButton.click();
	}

	public void AddNewTask_(WebDriver driver) {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);

		TaskList.TaskList_NewButton.click();
	}

	public void AddNewTask(WebDriver driver) throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.WaitForElement(driver, OR.TaskList_NewButton);
		Wait.explicit_wait_visibilityof_webelement(TaskList.TaskList_NewButton, driver);
		TaskList.TaskList_NewButton.click();
		Wait.wait1Second();
	}

	public void AddNewTask_Res_Acc(WebDriver driver) throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.WaitForElement(driver, OR.TaskList_NewButton_GS);
		Wait.explicit_wait_visibilityof_webelement(TaskList.TaskList_NewButton_GS, driver);
		TaskList.TaskList_NewButton_GS.click();
		Wait.wait1Second();
	}

	public void EnterTaskDetails(WebDriver driver, String Type, String Subject, String AccNamToSearch)
			throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);

		Select selectNewTaskListType = new Select(TaskList.NewTaskList_Type);
		selectNewTaskListType.selectByVisibleText(Type);
		TaskList.NewTaskList_Subject.sendKeys(Subject);
		// TaskList.NewTaskList_Room_ResBtn.click();
		TaskList.NewTaskList_Room_AccountBtn.click();
		Wait.wait2Second();
		TaskList.Account_AccPicker_NameInput.sendKeys(AccNamToSearch);
		TaskList.Account_AccPicker_GoButton.click();
		// TaskList.Account_AccPicker_FirstRadioButton.click();

		// TaskList.Room_Res_GoBtn.click();
		// System.out.println(TaskList.Room_Res_FirstEntry.size());
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].scrollIntoView();",
		// TaskList.Room_Res_NewResBtn);
		// TaskList.Room_Res_NewResBtn.click();
	}

	public void printAndDownloadPDF(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		TaskList.TaskList_PrintButton.click();
		test_steps.add("Succussfully Clicked PrintButton ");
		Utility.app_logs.info("Succussfully Clicked PrintButton");
		Wait.wait3Second();

		driver.manage().window().maximize();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("ifrmAccountStatement");
		assertTrue(TaskList.TaskList_PDFReport.isDisplayed(), "Report isn't Displayed");
		Utility.app_logs.info("PDF Report is Displayed");
		test_steps.add("PDF Report is Displayed");

		String dataPath = driver.findElement(By.xpath("//*[@id='viewPDF']")).getAttribute("data");
		driver.get(dataPath);

		// try {
		// downloadpdf(driver);
		// } catch (AWTException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// String path="//*[@id='btnImgDownload']";
		//
		// Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(path)),
		// driver);
		//
		// new Select(
		// driver.findElement(By.xpath("//*[@id='drpReportType']"))
		// ).selectByVisibleText("Export Data To Excel");
		//
		// if(driver.findElement(By.xpath(path)).isDisplayed()) {
		// Utility.app_logs.info("Download btn displayed");
		// test_steps.add("Download btn displayed");
		// Utility.ScrollToElement(driver.findElement(By.xpath(path)), driver);
		// driver.findElement(By.xpath(path)).click();
		// Utility.app_logs.info("File Downloaded");
		// test_steps.add("File Downloaded");
		// Wait.wait10Second();
		//
		// }else {
		// assertTrue(false,"Download Btn Not displayed");
		// }
		Utility.app_logs.info("File Downloaded");
		test_steps.add("File Downloaded");
		Wait.wait5Second();
	}

	public ArrayList<String> Click_AddTask(WebDriver driver) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.WaitForElement(driver, OR.AddTask_Button);
		Wait.waitForElementToBeVisibile(By.xpath(OR.AddTask_Button), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.AddTask_Button), driver);
		Utility.ScrollToElement_NoWait(TaskList.AddTask_Button, driver);
		Wait.wait2Second();
		TaskList.AddTask_Button.click();
		tasklistLogger.info("Click AddTask");
		test_steps.add("Click Add Task");
		Wait.WaitForElement(driver, OR.AddTask_Popup);
		Wait.waitForElementToBeVisibile(By.xpath(OR.AddTask_Popup), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.AddTask_Popup), driver);
		assertTrue(TaskList.AddTask_Popup.isDisplayed(), "Add Task Popup is not displayed");
		tasklistLogger.info("Add Task window displayed");
		test_steps.add("Add Task window displayed");

		return test_steps;

	}

	public void CreateNewTask(WebDriver driver, String task, String category, String categoryType, String details,
			String remarks, String assignee, String ResNumber) throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.SelectTask, driver);
		TaskList.SelectTask.click();
		Wait.waitUntilPresenceOfElementLocated("//span[text()='" + task + "']", driver);
		driver.findElement(By.xpath("//span[text()='" + task + "']")).click();
		assertTrue(TaskList.SelectTask.getText().contains(task), "Failed: Select Task");
		tasklistLogger.info("Task: " + task);
		Wait.wait2Second();
		TaskList.TypeSearch.sendKeys(ResNumber);
		Wait.wait3Second();
		driver.findElement(By.xpath("//*[contains (text(), '" + ResNumber + "')]")).click();
		tasklistLogger.info("Search : " + ResNumber);
		TaskList.TaskCategory.click();
		Wait.waitUntilPresenceOfElementLocated("//span[text()='" + category + "']", driver);
		driver.findElement(By.xpath("//span[text()='" + category + "']")).click();
		assertTrue(TaskList.TaskCategory.getText().contains(category), "Failed: Task Category");
		tasklistLogger.info("Task Category: " + category);
		TaskList.CategoryType.click();
		Wait.waitUntilPresenceOfElementLocated(
				"//select[@name='categoryType']//following-sibling::div/div//span[text()='" + categoryType + "']",
				driver);
		driver.findElement(By.xpath(
				"//select[@name='categoryType']//following-sibling::div/div//span[text()='" + categoryType + "']"))
				.click();
		assertTrue(TaskList.CategoryType.getText().contains(categoryType), "Failed: Category Type ");
		tasklistLogger.info("Category Type: " + categoryType);
		TaskList.Task_Detail.sendKeys(details);
		// assertTrue(TaskList.Task_Detail.getText().contains(task),"Failed:
		// Task Detail ");
		tasklistLogger.info("Task Detail: " + details);
		TaskList.Task_Remarks.sendKeys(remarks);
		// assertTrue(TaskList.Task_Remarks.getText().contains(task),"Failed:
		// Task Remarks");
		tasklistLogger.info("Task Remarks: " + remarks);
		TaskList.Task_Assignee.sendKeys(assignee);
		// assertTrue(TaskList.Task_Assignee.getText().contains(assignee),
		// "Failed: TaskAssignee ");
		tasklistLogger.info("Task Assignee: " + assignee);
		// Wait.wait3Second();
		// driver.findElement(By.xpath("//*[contains (text(),
		// 'Unassigned')]")).click();
		TaskList.Task_Save.click();
		tasklistLogger.info("Click Save");

	}

	public void CreateNewTask(WebDriver driver, String task, String category, String categoryType, String details,
			String remarks, String assignee) throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.SelectTask, driver);
		TaskList.SelectTask.click();
		Wait.waitUntilPresenceOfElementLocated("//span[text()='" + task + "']", driver);
		driver.findElement(By.xpath("//span[text()='" + task + "']")).click();
		assertTrue(TaskList.SelectTask.getText().contains(task), "Failed: Select Task");
		tasklistLogger.info("Task: " + task);
		Wait.wait2Second();
		TaskList.TypeSearch.sendKeys("10136481");
		Wait.wait3Second();
		driver.findElement(By.xpath("//*[contains (text(), '10136481')]")).click();
		tasklistLogger.info("Search : 10136481");
		TaskList.TaskCategory.click();
		Wait.waitUntilPresenceOfElementLocated("//span[text()='" + category + "']", driver);
		driver.findElement(By.xpath("//span[text()='" + category + "']")).click();
		assertTrue(TaskList.TaskCategory.getText().contains(category), "Failed: Task Category");
		tasklistLogger.info("Task Category: " + category);
		TaskList.CategoryType.click();
		Wait.waitUntilPresenceOfElementLocated(
				"//select[@name='categoryType']//following-sibling::div/div//span[text()='" + categoryType + "']",
				driver);
		driver.findElement(By.xpath(
				"//select[@name='categoryType']//following-sibling::div/div//span[text()='" + categoryType + "']"))
				.click();
		assertTrue(TaskList.CategoryType.getText().contains(categoryType), "Failed: Category Type ");
		tasklistLogger.info("Category Type: " + categoryType);
		TaskList.Task_Detail.sendKeys(details);
		// assertTrue(TaskList.Task_Detail.getText().contains(task),"Failed:
		// Task Detail ");
		tasklistLogger.info("Task Detail: " + details);
		TaskList.Task_Remarks.sendKeys(remarks);
		// assertTrue(TaskList.Task_Remarks.getText().contains(task),"Failed:
		// Task Remarks");
		tasklistLogger.info("Task Remarks: " + remarks);
		TaskList.Task_Assignee.sendKeys(assignee);
		// assertTrue(TaskList.Task_Assignee.getText().contains(assignee),
		// "Failed: TaskAssignee ");
		tasklistLogger.info("Task Assignee: " + assignee);
		// Wait.wait3Second();
		// driver.findElement(By.xpath("//*[contains (text(),
		// 'Unassigned')]")).click();
		TaskList.Task_Save.click();
		tasklistLogger.info("Click Save");

	}

	public void Reports(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.wait2Second();
		TaskList.TaskList_Report.click();
		assertTrue(driver.findElement(By.xpath(OR.Report_AllAssignees)).isDisplayed(),
				"Failed: All Assignees does not exist");
		assertTrue(driver.findElement(By.xpath(OR.Report_ByAssignees)).isDisplayed(),
				"Failed: By Assignees does not exist");
	}

	public void SelectDateFilter(WebDriver driver, String datefilter) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.ClickDateFilter, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", TaskList.ClickDateFilter);
		// TaskList.ClickDateFilter.click();
		Wait.wait2Second();
		driver.findElement(By.xpath("//li[@data-range-key='" + datefilter + "']")).click();
		tasklistLogger.info("Date Filter: " + datefilter);

	}

	public ArrayList<String> AllAssignees(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.Report_AllAssignees, driver);
		TaskList.Report_AllAssignees.click();
		Wait.wait3Second();
		test_steps.add("Click All Assignees");
		tasklistLogger.info("Click All Assignees");
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		tasklistLogger.info("Switch to report tab");
		Wait.wait2Second();
		assertTrue(driver.findElement(By.xpath(OR.TaskListReportPage)).isDisplayed(), "Failed: Task List Report Page");
		// assertTrue(driver.findElement(By.xpath(OR.ReportPage_Table)).isDisplayed(),
		// "Failed: Room Status Report");
		test_steps.add("Report: All Assignees ");
		tasklistLogger.info("Report: All Assignees");
		Wait.WaitForElement(driver, OR.Number_of_Tasks);
		int count = driver.findElements(By.xpath(OR.Number_of_Tasks)).size();
		if (count >= 2) {
			count = count / 2;
		}
		test_steps.add("Number of Task: " + count);
		tasklistLogger.info("Number of Task: " + count);
		// verify task list date
		String CalendarDateType = "This Week";
		String WeekStartDate = Utility.GetCurrentWeekDateRange().split(";")[0].split(",")[0];
		String WeekEndDate = Utility.GetCurrentWeekDateRange().split(";")[1].split(",")[0];
		String temp = Utility.GetCurrentMonthDateRange().split(";")[0].split(",")[1];
		temp = Utility.GetCurrentMonthDateRange().split(";")[1].split(",")[1];
		WeekStartDate = WeekStartDate + "'" + temp;
		WeekEndDate = WeekEndDate + "'" + temp;
		String WeekDateFormat = WeekStartDate + " To " + WeekEndDate;
		test_steps = VerifyDateRangeInTaskListReport(driver, WeekDateFormat, test_steps);
		test_steps.addAll(test_steps);
		driver.close();
		tasklistLogger.info("Close Tab");
		driver.switchTo().window(tabs2.get(0));
		Wait.explicit_wait_xpath(OR.TaskListPage, driver);
		tasklistLogger.info("Task List Page");
		return tabs2;

	}

	private java.util.Date yesterday() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	private java.util.Date getDate(String day) {
		final Calendar cal = Calendar.getInstance();
		if (day.contains("Yesterday")) {
			cal.add(Calendar.DATE, -1);
		} else if (day.contains("Today")) {
			cal.add(Calendar.DATE, 0);
		} else if (day.contains("Tomorrow")) {
			cal.add(Calendar.DATE, +1);
		}
		return cal.getTime();
	}

	public void ClickDateFilter(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.ClickDateFilter, driver);
		TaskList.ClickDateFilter.click();
		Wait.wait2Second();
		assertTrue(driver.findElement(By.xpath("//li[contains(@data-range-key,'Today')]")).isDisplayed(),
				"Failed: Today is not displayed");
		assertTrue(driver.findElement(By.xpath("//li[contains(@data-range-key,'Yesterday')]")).isDisplayed(),
				"Failed: Yesterday is not displayed");
		assertTrue(driver.findElement(By.xpath("//li[contains(@data-range-key,'Tomorrow')]")).isDisplayed(),
				"Failed: Tomorrow is not displayed");
		assertTrue(driver.findElement(By.xpath("//li[contains(@data-range-key,'This Week')]")).isDisplayed(),
				"Failed: This Week is not displayed");
		assertTrue(driver.findElement(By.xpath("//li[contains(@data-range-key,'This Month')]")).isDisplayed(),
				"Failed: This Month is not displayed");
		assertTrue(driver.findElement(By.xpath("//li[contains(@data-range-key,'Custom Range')]")).isDisplayed(),
				"Failed: Custom Range is not displayed");

	}

	public boolean SelectFilter_Date(WebDriver driver, String datefilter) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath("//li[@data-range-key='" + datefilter + "']", driver);
		driver.findElement(By.xpath("//li[@data-range-key='" + datefilter + "']")).click();
		Wait.wait2Second();
		return VerifyTaskList_OneDayTasks(driver, datefilter);

	}

	public boolean VerifyTaskList_OneDayTasks(WebDriver driver, String dateFilter) {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		boolean TaskExist = true;
		DateFormat dateFormat = new SimpleDateFormat("ddMMMyy");
		String Date = dateFormat.format(getDate(dateFilter));
		System.out.println(Date);
		System.out.println(Integer.parseInt(Date.substring(0, 1)));
		System.out.println(Date.substring(1, 7));
		if (Integer.parseInt(Date.substring(0, 1)) == 0) {
			Date = Date.substring(1, 7);
			System.out.println(Date);
		}
		Wait.WaitForElement(driver, OR.TaskList_Tasks);
		int count = driver.findElements(By.xpath(OR.TaskList_Tasks)).size();
		if (count == 1) {
			try {

				if (driver.findElement(By.xpath(OR.TaskList_Tasks + "[contains(@class,'noTask')]")).isDisplayed()) {
					TaskExist = false;
					tasklistLogger.info("No Task Found");
				}
			} catch (Exception e) {
				tasklistLogger.info("Number of Task: " + count);
				// verify task list date
				int rowNumber = 1;
				String path = "//table[contains(@class,'taskList')]//tr[" + rowNumber + "]/td[6]";
				String TaskDate = driver.findElement(By.xpath(path)).getText();
				System.out.println(TaskDate);
				TaskDate = TaskDate.split("\\(")[0].replaceAll("\\s+", "");
				System.out.println(TaskDate);
				assertEquals(TaskDate, Date, "Date Missmatched");
				tasklistLogger.info("Verified Task Date: " + Date);
			}
		}
		if (count > 1) {

			tasklistLogger.info("Number of Task: " + count);
			// verify task list date
			for (int i = 0; i < count; i++) {
				int rowNumber = i + 1;
				String path = "//table[contains(@class,'taskList')]//tr[" + rowNumber + "]/td[6]";
				String TaskDate = driver.findElement(By.xpath(path)).getText();
				System.out.println(TaskDate);
				TaskDate = TaskDate.split("\\(")[0].replaceAll("\\s+", "");
				System.out.println(TaskDate);
				assertEquals(TaskDate, Date, "Date Missmatched");
			}
			tasklistLogger.info("Verified Task Date: " + Date);
		}

		return TaskExist;
	}

	public void ClickFilter(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.TaskList_Filter, driver);
		TaskList.TaskList_Filter.click();
		Wait.wait2Second();
		assertTrue(driver
				.findElement(
						By.xpath("//div[contains(@class,'task-filter')]//ul/li[2]/div/div[1]//following-sibling::a"))
				.isDisplayed(), "Failed: Task Category is not displayed");
		assertTrue(driver
				.findElement(
						By.xpath("//div[contains(@class,'task-filter')]//ul/li[2]/div/div[2]//following-sibling::a"))
				.isDisplayed(), "Failed: Task Type is not displayed");
		assertTrue(driver
				.findElement(
						By.xpath("//div[contains(@class,'task-filter')]//ul/li[2]/div/div[3]//following-sibling::a"))
				.isDisplayed(), "Failed: Zone is not displayed");
		assertTrue(driver
				.findElement(
						By.xpath("//div[contains(@class,'task-filter')]//ul/li[2]/div/div[4]//following-sibling::a"))
				.isDisplayed(), "Failed: Assigned is not displayed");

	}

	public ArrayList<String> SelectFilter(WebDriver driver, String TaskCategory, ArrayList<String> test_steps)
			throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.Filter_TaskCategory, driver);
		TaskList.Filter_TaskCategory.click();

		Wait.explicit_wait_xpath(OR.SelectFilter_TaskCategory, driver);

		String cat_path = OR.SelectFilter_TaskCategory + "/text()[contains(.,'" + TaskCategory
				+ "')]//following-sibling::span";
		// System.out.println(cat_path);
		try {
			Utility.ScrollToElement(driver.findElement(By.xpath(cat_path)), driver);
			Wait.wait2Second();
			assertTrue(driver.findElement(By.xpath(cat_path)).isDisplayed(), "Failed: " + TaskCategory + " not exist");
			driver.findElement(By.xpath(cat_path)).click();
			Wait.wait2Second();
			assertTrue(TaskList.SelectFirstTaskCategory.isEnabled(), "Failed: Task Type is not Selected");

		} catch (Exception e) {
			assertTrue(false, TaskCategory + " not  Exist");
		}

		return VerifyFilterTaskType(driver, TaskCategory, test_steps);
	}

	private ArrayList<String> VerifyFilterTaskType(WebDriver driver, String taskCategory,
			ArrayList<String> test_steps) {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		// System.out.println(taskCategory);
		taskCategory = taskCategory.replaceAll("\\s+", "");
		// System.out.println(taskCategory);

		// System.out.println(driver.findElements(By.xpath(OR.TaskList_TasksTable)).size());
		if (driver.findElements(By.xpath(OR.TaskList_TasksTable)).size() != 0) {
			int count = driver.findElements(By.xpath(OR.TaskList_Tasks)).size();
			// System.out.println("Tasks Found After Task Type filter applied: "
			// + count);
			if (count >= 1) {
				test_steps.add("Number of Task: " + count);
				tasklistLogger.info("Number of Task: " + count);
				// verify task list Type
				for (int i = 0; i < count; i++) {
					int rowNumber = i + 1;
					String path = "//table[contains(@class,'taskList')]//tr[" + rowNumber
							+ "]/td[2]//following-sibling::span[2]";
					String TaskType = driver.findElement(By.xpath(path)).getText();
					// System.out.println(TaskType);
					if (taskCategory.contains("FrontDesk")) {
						boolean result = TaskType.contains("Internal") || TaskType.contains("Room Move")
								|| TaskType.contains("Room Clean");
						assertTrue(result, "Task Type Missmatched");
					}
				}
				test_steps.add("Verified Task Type: " + taskCategory);
				tasklistLogger.info("Verified Task Type: " + taskCategory);
			} else {
				test_steps.add("No task Found of : " + taskCategory);
				tasklistLogger.info("No task found of : " + taskCategory);
			}
		} else {
			test_steps.add("No task Found of : " + taskCategory);
			tasklistLogger.info("No task found of : " + taskCategory);
		}
		return test_steps;

	}

	public ArrayList<String> CheckTaskType_CheckBox(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.TaskType_CheckBox, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", TaskList.TaskType_CheckBox);
		// TaskList.TaskType_CheckBox.click();
		Wait.wait2Second();
		// assertTrue(TaskList.TaskType_CheckBox.isSelected(),"Failed: Task Type
		// CheckBox is not Selected");
		test_steps.add("Click Task Type CheckBox");
		tasklistLogger.info("Click Task Type CheckBox");
		Wait.explicit_wait_xpath(OR.BulkAction_Button, driver);
		Wait.wait2Second();
		assertTrue(TaskList.BulkAction_Button.isEnabled(), "Failed: Bulk Action is Not Enabled");
		test_steps.add("Bulk Button Enabled");
		tasklistLogger.info("Bulk Button Enabled");
		return test_steps;
	}

	public void UnCheckTaskType_CheckBox(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.TaskType_CheckBox, driver);
		TaskList.TaskType_CheckBox.click();
		Wait.wait2Second();
		// assertTrue(TaskList.TaskType_CheckBox.isSelected(),"Failed: Task Type
		// CheckBox is not Selected");
		tasklistLogger.info("Click Task Type CheckBox");
	}

	public int CheckSomeTaskTypes(WebDriver driver) throws InterruptedException {
		Wait.WaitForElement(driver, OR.TaskList_Tasks);
		int count = driver.findElements(By.xpath(OR.TaskList_Tasks)).size();
		int NumberOfTasks = 0;
		if (count > 3) {
			NumberOfTasks = 3;
		} else {
			NumberOfTasks = count;
		}
		String path = null;
		if (TaskExist(driver)) {
			for (int i = 1; i <= NumberOfTasks; i++) {
				path = "//table[contains(@class,'taskList')]//tr[" + i + "]/td[1]/span";
				Wait.explicit_wait_xpath(path, driver);
				WebElement Task = driver.findElement(By.xpath(path));
				Task.click();
				Wait.wait1Second();
				// assertTrue(driver.findElement(By.xpath(path)).isSelected(),"Failed:
				// " +i+" Task Tpe CheckBox is Not Selected");

			}
			tasklistLogger.info("Click Some Task Type CheckBoxes");
		}
		return NumberOfTasks;
	}

	public boolean TaskExist(WebDriver driver) {
		boolean tasks = true;
		/*
		 * try { if (driver.findElement(By.xpath(OR.TaskList_Tasks +
		 * "[contains(@class,'noTask')]")).isDisplayed()) { tasks = false;
		 * test_steps.add("No Task Found");
		 * tasklistLogger.info("No Task Found"); } } catch (Exception e) {
		 * 
		 * }
		 */ return tasks;
	}

	public void ClickBulkAction(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.BulkAction_Button, driver);
		TaskList.BulkAction_Button.click();
		Wait.wait2Second();
		assertTrue(driver.findElement(By.xpath(OR.Assign_Staff)).isDisplayed(), "Failed: Assign Staff does not exist");
		assertTrue(driver.findElement(By.xpath(OR.Change_Status)).isDisplayed(),
				"Failed: Change Status does not exist");
		tasklistLogger.info("Click Bulk Action");

	}

	public ArrayList<String> AssignStaff(WebDriver driver, String AssignTo, ArrayList<String> test_steps)
			throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.Assign_Staff, driver);
		TaskList.Assign_Staff.click();
		Wait.waitUntilPresenceOfElementLocated(OR.AssignStaff_Page, driver);
		assertTrue(driver.findElement(By.xpath(OR.AssignStaff_Page)).isDisplayed(),
				"Failed: Assign Staff Pagenot displayed");
		test_steps.add("Click Assign Staff");
		tasklistLogger.info("Click Assign Staff");
		Wait.explicit_wait_xpath(OR.AssignStaff_AssignTo, driver);
		TaskList.AssignStaff_AssignTo.sendKeys(AssignTo);
		Wait.waitUntilPresenceOfElementLocated("//*[contains (text(), '" + AssignTo + "')]", driver);
		driver.findElement(By.xpath("//*[contains (text(), '" + AssignTo + "')]")).click();
		Wait.wait2Second();
		test_steps.add("Assign To: " + AssignTo);
		tasklistLogger.info("Assign To: " + AssignTo);
		assertTrue(TaskList.AssignStaff_Proceed.isEnabled(), "Failed: Proceed Nutton is not Enabled");
		TaskList.AssignStaff_Proceed.click();
		test_steps.add("Click Proceed");
		tasklistLogger.info("Click Proceed");
		return test_steps;

	}

	public void VerifyAssignedTasks(WebDriver driver, int NumberOfTasks, String AssignTo) {
		for (int i = 0; i < NumberOfTasks; i++) {
			int rowNumber = i + 1;
			String path = "//table[contains(@class,'taskList')]//tr[" + rowNumber + "]/td[7]";
			String Assigned = driver.findElement(By.xpath(path)).getText();
			System.out.println(Assigned);
			assertTrue(Assigned.contains(AssignTo), "Assigned: Missmatched");
		}
		tasklistLogger.info("Verified Assigned");
	}

	public ArrayList<String> ChangeStatus(WebDriver driver, String taskStatus, ArrayList<String> test_steps)
			throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.Change_Status, driver);
		TaskList.Change_Status.click();
		Wait.waitUntilPresenceOfElementLocated(OR.ChangeStatus_Page, driver);
		assertTrue(driver.findElement(By.xpath(OR.ChangeStatus_Page)).isDisplayed(),
				"Failed: Assign Staff Pagenot displayed");
		test_steps.add("Click Change Status");
		tasklistLogger.info("Click Change Status");
		System.out.println(TaskList.ChangeStatus_Proceed.isEnabled());
		Wait.explicit_wait_xpath(OR.ChangeStatus_SelectStatus, driver);
		Utility.ElementFinderUntillNotShow(By.xpath(OR.ChangeStatus_SelectStatus), driver);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {

			jse.executeScript("arguments[0].click();", TaskList.ChangeStatus_SelectStatus);
		} catch (Exception e) {

			TaskList.ChangeStatus_SelectStatus.click();
		}

		// select[@name='taskStatus']//following-sibling::div//span[text()='To
		// Do']
		assertTrue(driver
				.findElement(By.xpath("//select[@name='taskStatus']//following-sibling::div//span[text()='To Do']"))
				.isDisplayed(), "Failed: To Do is not displayed");

		assertTrue(driver
				.findElement(By.xpath("//select[@name='taskStatus']//following-sibling::div//span[text()='Done']"))
				.isDisplayed(), "Failed: Done is not displayed");
		assertTrue(driver
				.findElement(By.xpath("//select[@name='taskStatus']//following-sibling::div//span[text()='Cancelled']"))
				.isDisplayed(), "Failed: Cancelled is not displayed");
		driver.findElement(
				By.xpath("//select[@name='taskStatus']//following-sibling::div//span[text()='" + taskStatus + "']"))
				.click();
		assertTrue(driver.findElement(By.xpath(OR.ChangeStatus_SelectStatus + "/span")).getText().contains(taskStatus),
				"Failed: Task Status not Selected");
		test_steps.add("Change Status: " + taskStatus);
		tasklistLogger.info("Change Status: " + taskStatus);
		Wait.wait5Second();
		System.out.println(TaskList.ChangeStatus_Proceed.isEnabled());
		// assertTrue(TaskList.ChangeStatus_Proceed.isEnabled(), "Failed:
		// Proceed Button is not Enabled");
		TaskList.ChangeStatus_Proceed.click();
		test_steps.add("Click Proceed");
		tasklistLogger.info("Click Proceed");
		Wait.waitUntilPresenceOfElementLocated(OR.ChangeStatus_StausUpdate, driver);

		Wait.waitUntilPresenceOfElementLocated(OR.ChangeStatus_Close, driver);
		TaskList.ChangeStatus_Close.click();
		test_steps.add("Click Close");
		tasklistLogger.info("Click Close");
		return test_steps;
	}

	public void VerifyTasksStatus(WebDriver driver, int NumberOfTasks, String Status) {
		for (int i = 0; i < NumberOfTasks; i++) {
			int rowNumber = i + 1;
			String path = "//table[contains(@class,'taskList')]//tr[" + rowNumber + "]/td[9]";
			String taskStatus = driver.findElement(By.xpath(path)).getText();
			System.out.println(taskStatus);
			assertTrue(taskStatus.contains(Status), "Status: Missmatched");
		}
		tasklistLogger.info("Verified Status");
	}

	public void SearchTask(WebDriver driver, String AssigneeName) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.WaitForElement(driver, OR.SearchTaskInput);
		TaskList.SearchTaskInput.sendKeys(AssigneeName);
		TaskList.SearchTaskButton.click();
		Wait.wait5Second();
	}

	public void RemoveSearch(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		TaskList.SearchTaskInput.clear();
		TaskList.SearchTaskInput.sendKeys("");
		Wait.wait5Second();
		TaskList.SearchTaskButton.click();

	}

	public ArrayList<String> DeleteAnyTask(WebDriver driver, ArrayList<String> TaskDetailsInTaskList)
			throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		ArrayList<String> test_steps = new ArrayList<String>();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		TaskList.FirstTaskInTaskListCheckBox.click();
		Wait.wait3Second();
		jse.executeScript("arguments[0].click();", TaskList.TaskList_DeleteTaskButton);
		tasklistLogger.info("Task Delete Button Clicked");
		test_steps.add("Task Delete Button Clicked");
		Wait.WaitForElement(driver, OR.BulkAction_ProceedButton);
		Wait.wait2Second();
		System.out.print(TaskList.Verify_Bulk_Delete_popup.getText());
		// assertEquals(TaskList.Verify_Bulk_Delete_popup.getText(), "Bulk
		// Delete");
		tasklistLogger.info("Bulk Delete is Displayed");
		test_steps.add("Bulk Delete is Displayed");
		// Testing
		ArrayList<String> taskDetailsInBulkDeletePopup = getTaskDetailsInBulkDeletePopUp(driver);
		boolean isEqual = TaskDetailsInTaskList.equals(taskDetailsInBulkDeletePopup);
		assertEquals(isEqual, true);
		tasklistLogger.info("Task Details Macted");
		test_steps.add("Task Details Macted");
		jse.executeScript("arguments[0].click();", TaskList.BulkAction_ProceedButton);
		tasklistLogger.info("Proceed Button Clicked");
		test_steps.add("Proceed Button Clicked");
		Wait.explicit_wait_visibilityof_webelement_120(TaskList.Toaster_Message, driver);
		assertEquals(TaskList.Toaster_Message.getText(), "Task(s) deleted.");
		tasklistLogger.info("Toaster  Message Displayed: " + TaskList.Toaster_Message.getText());
		test_steps.add("Toaster  Message Displayed: " + TaskList.Toaster_Message.getText());
		return test_steps;
	}

	public void SelectFirstTask(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		String FirstSearchedTask = "(//div[@class='gs-displayTable']//span[@class='gs-displayTable-cell firstLetterCaps'])[1]";
		Wait.WaitForElement(driver, FirstSearchedTask);
		WebElement FirstTaskListID = driver.findElement(By.xpath(FirstSearchedTask));
		FirstTaskListID.click();
	}

	public String GetFirstTaskListID(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		// WebElement FirstTaskListID = driver.findElement(
		// By.xpath("//div[@class='gs-displayTable']//span[@class='gs-displayTable-cell
		// firstLetterCaps']"));
		// FirstTaskListID.click();
		Wait.wait3Second();
		String TaskListInfo = driver.findElement(By.xpath("//div[@class='gs-custDetails']//span[@class='gs-name']"))
				.getText();
		String TaskListIDWithHash = TaskListInfo.split("-")[1];
		String TaskListID = TaskListIDWithHash.substring(2, TaskListIDWithHash.length());
		TaskList.TaskList_InfoPanelCloseButton.click();
		return TaskListID;
	}

	public void ClickEditButton(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.WaitForElement(driver, OR.FirstTaskInTaskListEditButton);
		TaskList.FirstTaskInTaskListEditButton.click();
		String TaskListPanel = "(//div[@class='modal-dialog modal-lg'])[1]";
		Wait.WaitForElement(driver, TaskListPanel);
		WebElement TaskListPanelEle = driver.findElement(By.xpath(TaskListPanel));
		// assertTrue(TaskListPanel.isDisplayed(), "Failed: TaskList Panel is
		// not Displayed");

	}

	public void EditAnyTask(WebDriver driver, String category) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		TaskList.FirstTaskInTaskListEditButton.click();
		Wait.wait3Second();
		TaskList.TaskCategory.click();
		Wait.waitUntilPresenceOfElementLocated("//span[text()='" + category + "']", driver);
		driver.findElement(By.xpath("//span[text()='" + category + "']")).click();
		assertTrue(TaskList.TaskCategory.getText().contains(category), "Failed: Task Category");
		tasklistLogger.info("Task Category: " + category);
		TaskList.Task_Save.click();
		tasklistLogger.info("Click Save");

	}

	public void VerifyTaskListQuickStats(WebDriver driver, boolean InspectionStatus) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		if (InspectionStatus)
			assertTrue(TaskList.TaskList_Inspection.isDisplayed(), "Failed : Inspection Stat isn't displayed");
		assertTrue(TaskList.TaskList_TODO.isDisplayed(), "Failed : TODO Stat isn't displayed");
		assertTrue(TaskList.TaskList_Done.isDisplayed(), "Failed : Done Stat isn't displayed");
		assertTrue(TaskList.TaskList_All.isDisplayed(), "Failed : All Stat isn't displayed");

	}

	public void ByAssignees(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.Report_ByAssignees, driver);
		TaskList.Report_ByAssignees.click();
		Wait.wait3Second();
		tasklistLogger.info("Click By Assignees");
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		tasklistLogger.info("Switch to report tab");
		Wait.wait2Second();
		assertTrue(driver.findElement(By.xpath(OR.TaskListReportPage_ByAssignee)).isDisplayed(),
				"Failed: Task List Report Page");

		tasklistLogger.info("Report: By Assignees");
		Wait.WaitForElement(driver, OR.Number_of_Tasks);
		int count = driver.findElements(By.xpath(OR.Number_of_Tasks)).size();
		if (count >= 2) {
			count = count / 2;
		}
		tasklistLogger.info("Number of Task: " + count);
		// verify task list date
		for (int i = 0; i < count; i++) {
			int rowNumber = 1;
			String path = "//table[contains(@class, 'table gs-table gs-tdPad10')]/tbody/tr[" + rowNumber + "]/td[5]";
			String TaskDate = driver.findElement(By.xpath(path)).getText();
			// System.out.println(TaskDate);
			TaskDate = TaskDate.split("\\(")[0].replaceAll("\\s+", "");
			// System.out.println(TaskDate);
			DateFormat dateFormat = new SimpleDateFormat("ddMMMyy");
			String yesterday = dateFormat.format(yesterday());
			// System.out.println(yesterday);
			assertEquals(TaskDate, yesterday, "Date Missmatched");
			rowNumber = rowNumber + 2;
		}
		driver.close();
		tasklistLogger.info("Close Tab");
		driver.switchTo().window(tabs2.get(0));
		Wait.explicit_wait_xpath(OR.TaskListPage, driver);
		tasklistLogger.info("Task List Page");

	}

	public void ChangeFirstTaskType_Status(WebDriver driver, String Status) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.TaskList_All, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// try {
		// Utility.ElementFinderUntillNotShow(By.xpath(OR.TaskType_StatusDropDownButton1),
		// driver);
		// jse.executeScript("arguments[0].click();",
		// TaskList.TaskType_StatusDropDownButton1);
		//
		// } catch (Exception e) {
		//
		// Utility.ElementFinderUntillNotShow(By.xpath(OR.TaskType_StatusDropDownButton),
		// driver);
		// jse.executeScript("arguments[0].click();",
		// TaskList.TaskType_StatusDropDownButton);
		// }
		WebElement StatusCheck = driver.findElement(By.xpath("(//select[@name='taskStatus'])[1]"));
		new Select(StatusCheck).selectByValue(Status);
		TaskList.TaskType_StatusDropDown_SaveBtn.click();

	}

	public void VerifyNewCreatedTask(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.TaskList_All, driver);
		String DateTime = TaskList.TaskListFirstElementDate.getText();
		System.out.println("DateTime:" + DateTime);
		String Date = DateTime.split(" ")[0];
		String SystemDate = GetSystemDate(true);
		SystemDate = SystemDate.split(",")[0];
		SystemDate = SystemDate.split(" ")[1];
		assertTrue(SystemDate.equals(Date), "Failed: Task Not Added Successfully");

	}

	public void VerifyTask(WebDriver driver, String resNumber, String guestName, String details) {
		// TODO Auto-generated method stub
		WebElements_TaskList tasklist = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.SearchRoomAccAssign, driver);
		tasklist.SearchRoomAccAssign.clear();
		tasklist.SearchRoomAccAssign.sendKeys(guestName);
		tasklist.TaskList_SearchButton.click();
		String TaskPath = "//table[contains(@class,'taskList')]/tbody/tr//following::*[text()='" + guestName + "']";
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(TaskPath)), driver);
		List<WebElement> TasksList = driver.findElements(By.xpath(TaskPath));
		boolean taskfound = false;
		for (WebElement task : TasksList) {
			try {
				task.click();
				Wait.waitUntilPresenceOfElementLocated(OR.EditTaskPopup, driver);
				String task_guest = tasklist.Taskpopup_GuestName.getText();
				Utility.app_logs.info(task_guest);
				assertTrue(task_guest.contains(guestName), "Failed : Task Guest name not found");
				assertTrue(task_guest.contains(resNumber), "Failed : Task Reservation Number not found");
				assertEquals(tasklist.Taskpopup_TaskDescription, details, "Failed : Detail missmatched");
				tasklist.Taskpopup_Close.click();
				taskfound = true;
			} catch (Exception e) {
				taskfound = false;
			}
			if (taskfound) {
				break;
			}

		}
	}

	public String readFile() {
		String finalContent = "";
		try {
			File getFile = Utility.getLatestFilefromDir(System.getProperty("user.dir") + File.separator
					+ "externalFiles" + File.separator + "downloadFiles" + File.separator);
			FileInputStream file = new FileInputStream(getFile);

			// Create Workbook instance holding reference to .xlsx file
			HSSFWorkbook workbook = new HSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();

			List lst = workbook.getAllPictures();
			for (Iterator it = lst.iterator(); it.hasNext();) {
				PictureData pict = (PictureData) it.next();
				String ext = pict.suggestFileExtension();
				byte[] data = pict.getData();
				if (ext.equals("jpeg")) {
					FileOutputStream out = new FileOutputStream("pict.jpg");
					out.write(data);
					out.close();
				}
			}
			// if(lst.size()<=0) {
			// assertTrue(false,"NO Image Found");
			// }

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					// Check the cell type and format accordingly
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						finalContent = finalContent + cell.getNumericCellValue() + " ";
						break;
					case Cell.CELL_TYPE_STRING:
						finalContent = finalContent + cell.getStringCellValue() + " ";
						break;
					}

				}
				finalContent = finalContent + "\n";
			}
			file.close();
			if (getFile.delete()) {
				System.out.println("File deleted successfully");
			} else {
				System.out.println("Failed to delete the file");
			}
			System.out.println(finalContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalContent;
	}

	public ArrayList<String> verifyReport(String content, String propertyName) {
		ArrayList<String> test_steps = new ArrayList<>();

		assertTrue(content.contains("Task List"), "Failed: Report Name Not Matched");
		test_steps.add("Report Name is Displayed");
		Utility.app_logs.info("Report Name is Displayed");

		assertTrue(content.contains(propertyName), "Failed: Property Name Not Matched");
		test_steps.add("Property Name is displayed");
		Utility.app_logs.info("Property Name is displayed");

		assertTrue(content.contains("Type Subject Details Acc/Res Room Account/Guest Name Due Action"),
				"Failed: Type Subject Details Acc/Res Room Account/Guest Name Due Action not Displayed");
		test_steps.add("Type,Subject,Acc/Res,Room,Account/Guest Name,Due,Action are displayed");
		Utility.app_logs.info("Type,Subject,Acc/Res,Room,Account/Guest Name,Due,Action are displayed");

		assertTrue(content.contains("Printed On :" + getDate()), "Failed: Printed Date Not Displayed");
		test_steps.add("Printed Date is displayed : " + getDate());
		Utility.app_logs.info("Printed Date is displayed : " + getDate());

		// File image1= new File(System.getProperty("user.dir") + File.separator
		// + "pict.jpg");
		// File image2= new File(System.getProperty("user.dir") + File.separator
		// + "innroadLogo.jpg");
		//
		// assertTrue(Utility.compareImage(image1, image2),"Failed: Inncenter
		// Logo is not Displayed");
		// test_steps.add("Inncenter Logo is Displayed");
		// Utility.app_logs.info("Inncenter Logo is Displayed");
		// image1.delete();

		return test_steps;
	}

	public String getDate() {
		// Sat Aug 03, 2019
		SimpleDateFormat format = new SimpleDateFormat("E MMM dd, yyyy");
		format.setTimeZone(TimeZone.getTimeZone("EST"));
		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return format.format(calendar.getTime());
	}

	public void downloadpdf(WebDriver driver) throws InterruptedException, AWTException {
		// String
		// frame="//iframe[@id='MainContent_ucRptViewer_ifrmAccountStatement']";
		// Wait.WaitForElement(driver, frame);
		// driver.switchTo().frame(driver.findElement(By.xpath(frame)));
		String sample = "//select[@id='drpReportType']";
		Thread.sleep(3000);
		new Actions(driver).moveToElement(driver.findElement(By.xpath(sample))).build().perform();
		WebElement ele = driver.findElement(By.xpath(sample));
		ele.sendKeys(Keys.TAB);
		Wait.wait2Second();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Wait.wait10Second();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		Wait.wait2Second();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		Wait.wait2Second();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		Wait.wait2Second();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Wait.wait2Second();
	}

	public String checkPDF2() throws IOException {
		String filePath = "C:\\Users\\" + System.getProperty("user.name") + "\\Downloads";
		// System.getProperty("user.dir") + File.separator + "externalFiles"+
		// File.separator + "downloadFiles" + File.separator
		// File getFile = Utility.getLatestFilefromDir(filePath);
		String user = System.getProperty("user.name");

		File theNewestFile = null;
		File dir = new File("\\Users\\" + user + "\\Downloads\\");
		FileFilter fileFilter = new WildcardFileFilter("*." + "pdf");
		File[] files = dir.listFiles(fileFilter);
		if (files.length > 0) {
			/** The newest file comes first **/
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
		}
		System.out.println(theNewestFile);
		System.out.println(theNewestFile.getAbsolutePath());
		System.out.println(theNewestFile.getName());
		PDDocument document = PDDocument.load(theNewestFile);
		document.getClass();
		String pdfFileInText = null;
		if (!document.isEncrypted()) {
			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			stripper.setSortByPosition(true);
			PDFTextStripper tStripper = new PDFTextStripper();
			pdfFileInText = tStripper.getText(document);
			document.close();

			if (theNewestFile.delete()) {
				System.out.println("File deleted successfully");
			} else {
				System.out.println("Failed to delete the file");
			}
		}
		return pdfFileInText;
	}

	public String checkPDF() throws IOException {
		File getFile = Utility.getLatestFilefromDir(System.getProperty("user.dir") + File.separator + "externalFiles"
				+ File.separator + "downloadFiles" + File.separator);
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

	public void deleteNotes(WebDriver driver, String subject, String reservation, String Action)
			throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);

		List<WebElement> taskRows = TaskList.taskListRows;
		List<WebElement> taskCols = TaskList.taskListCols;

		for (int i = 2; i <= taskRows.size() - 1; i++) {

			// for(int j=1;j<=taskCols.size();j++) {

			if (driver.findElement(By.xpath("//table[@class='dgText']/tbody/tr[" + i + "]/td[3]")).getText()
					.equalsIgnoreCase(subject) &&

					driver.findElement(By.xpath("//table[@class='dgText']/tbody/tr[" + i + "]/td[4]")).getText()
							.contains(reservation)) {

				Wait.wait5Second();
				// Open the Notes in Task List page
				driver.findElement(By.xpath("//table[@class='dgText']/tbody/tr[" + i + "]/td[3]/table/tbody/tr/td/a"))
						.click();

				// Switch to frame
				driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
				// table[@class='dgText']/tbody/tr[2]/td[3]/table/tbody/tr/td/a
				Wait.WaitForElement(driver, OR.NoteActionRequired);

				if (TaskList.NoteActionRequired.isSelected() == true) {

					// Uncheck Action Required checkbox
					TaskList.NoteActionRequired.click();

				}

				// Select the Action from dropdown
				Wait.WaitForElement(driver, OR.NoteAction);

				new Select(TaskList.NoteAction).selectByVisibleText(Action);

				// Click Save
				Wait.WaitForElement(driver, OR.NotesSave);
				TaskList.NotesSave.click();

				driver.switchTo().defaultContent();

				// Click Save in Task List
				Wait.WaitForElement(driver, OR.SaveTaskList);
				TaskList.SaveTaskList.click();
				Wait.wait5Second();

				tasklistLogger.info(" Deleted Notes from Task List ");

			}
		}
	}

	public void taskListSearchCriteria_EarlyArrival(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);

		// Select the Action as All in Task List
		Wait.WaitForElement(driver, OR.selectAction);
		new Select(TaskList.selectAction).selectByVisibleText("--ALL--");

		// Uncheck Include Past Due Items
		Wait.WaitForElement(driver, OR.includePastDueItems);
		if (TaskList.includePastDueItems.isSelected() == true) {

			TaskList.includePastDueItems.click();

		}

		if (TaskList.EarlyArrival.isSelected()) {

			Wait.wait1Second();

		}

		else {

			Wait.WaitForElement(driver, OR.EarlyArrival);
			TaskList.EarlyArrival.click();

			System.out.println("Selected early Arrival");
		}
		/*
		 * //Uncheck Internal TaskList.Internal.click();
		 * 
		 * //UncheckHouseKeeping TaskList.HouseKeeping.click();
		 * 
		 * //Request TaskList.Request.click();
		 * 
		 * //Wake-up Call TaskList.WakeUpCall.click();
		 * 
		 * //Guest Note TaskList.GuestNote.click();
		 * 
		 * //Complaint TaskList.Complaint.click();
		 * 
		 * //Message TaskList.Message.click();
		 * 
		 * //Deposit required TaskList.DepositRequired.click();
		 * 
		 * //Email TaskList.Email.click();
		 * 
		 * //Room Move TaskList.RoomMove.click();
		 */

		// Early Arrival
		// TaskList.EarlyArrival.click();

		/*
		 * //Late Arrival TaskList.LastArrival.click();
		 * 
		 * //Late Departure TaskList.LateDeparture.click();
		 * 
		 * //Cancellation TaskList.Cancellation.click();
		 * 
		 * //Front Desk TaskList.FrontDesk.click();
		 * 
		 * //Parking Information TaskList.ParkingInformation.click();
		 * 
		 * //ADA TaskList.ADA.click();
		 */

		// Click on Go button
		TaskList.HouseKeeping.click();
		Wait.WaitForElement(driver, OR.taskListGoButton);
		Wait.wait5Second();
		TaskList.taskListGoButton.click();
		Wait.wait5Second();
	}

	public void TaskValidation(WebDriver driver, String TaskSubject, String reservation, ArrayList<String> test_steps)
			throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);

		// boolean hyperlink=
		// driver.findElement(By.xpath("//tr[@align='right']/td/a")).isDisplayed();
		try {
			// if(driver.findElement(By.xpath("//tr[@align='right']/td/span")).isDisplayed())
			// {

			Wait.WaitForElement(driver, OR.taskListRows);
			List<WebElement> taskRows = TaskList.taskListRows;
			List<WebElement> taskCols = TaskList.taskListCols;

			System.out.println("Task Rows size" + taskRows.size());
			for (int i = 2; i <= taskRows.size() - 1; i++) {

				if ((driver.findElement(By.xpath("//table[@class='dgText']/tbody/tr[" + i + "]/td[3]")).getText()
						.equalsIgnoreCase(TaskSubject) &&

						driver.findElement(By.xpath("//table[@class='dgText']/tbody/tr[" + i + "]/td[4]")).getText()
								.contains(reservation)))

				{

					Wait.wait5Second();
					tasklistLogger.info(
							"Displayed Task " + TaskSubject + " with Reservation " + reservation + " in TaskList");
					Wait.wait5Second();

					test_steps
							.add("Displayed Task " + TaskSubject + " with Reservation " + reservation + " in TaskList");
					Wait.wait5Second();

					tasklistLogger.info("Displayed Task in Task List");

					test_steps.add("Displayed Task in Task List");
					Wait.wait10Second();
					// test_steps.add("Successfully Deleted the Notes in Task
					// List");

				}

				/*
				 * tasklistLogger.info(" Task not found" );
				 * 
				 * test_steps.add("Task not found");
				 */
				// break;
			}

		}

		// }

		catch (Exception e) {

			if (driver.findElement(By.xpath("//tr[@align='right']/td/a")).isDisplayed()) {

				List<WebElement> hyperlinks = driver.findElements(By.xpath("//tr[@align='right']/td/a"));

				System.out.println("Hyperlinks: " + hyperlinks.size());

				for (int j = 1; j <= hyperlinks.size(); j++) {

					WebElement element = driver.findElement(By.xpath("//tr[@align='right']/td/a[" + j + "]"));

					WebElement navigateElement = driver.findElement(By.xpath("//tr[@align='right']/td/a"));

					Wait.explicit_wait_visibilityof_webelement(navigateElement, driver);

					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("arguments[0].scrollIntoView();", navigateElement);

					driver.findElement(By.xpath("//tr[@align='right']/td/a[" + j + "]")).click();

					Wait.WaitForElement(driver, OR.taskListRows);
					List<WebElement> taskRows = TaskList.taskListRows;
					List<WebElement> taskCols = TaskList.taskListCols;

					for (int i = 2; i <= taskRows.size() - 1; i++) {

						if (driver.findElement(By.xpath("//table[@class='dgText']/tbody/tr[" + i + "]/td[3]")).getText()
								.equalsIgnoreCase(TaskSubject) &&

								driver.findElement(By.xpath("//table[@class='dgText']/tbody/tr[" + i + "]/td[4]"))
										.getText().contains(reservation)) {

							Wait.wait5Second();
							tasklistLogger.info(" Displayed Notes " + TaskSubject + " with Reservation " + reservation
									+ " in TaskList");
							Wait.wait5Second();
							test_steps.add(" Displayed Notes " + TaskSubject + " with Reservation " + reservation
									+ "in TaskList");
							Wait.wait5Second();

							tasklistLogger.info(" Displayed Task in Task List");

							test_steps.add("Displayed Task in Task List");
							Wait.wait10Second();

							// Click on Complete
							driver.findElement(
									By.xpath("//table[@class='contentTable']/tbody/tr[" + i + "]/td/a[.='" + TaskSubject
											+ "']/following::td/table/tbody/tr/td[1]/input[@id='MainContent_dgLineItems_btnComplete_3']"))
									.click();

							tasklistLogger.info(" Clicked on Complete in TaskList");

							Wait.wait25Second();

						} // for(int i=2; i<=taskRows.size()-1;i++) end********

					} // for(int j=1;j<=hyperlinks.size();j++) end*******

				} // else if(hyperlink==true) end****

			}

		}

	}

	public void ClickTaskComplete(WebDriver driver, String TaskSubject, String reservation,
			ArrayList<String> test_steps) throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);

		// String
		// xpath="//table[@class='contentTable']/tbody/tr/td/a[.='"+TaskSubject+"']/following::td/table/tbody/tr/td[1]/input[@id='MainContent_dgLineItems_btnComplete_3']";

		String xpath = "(//table[@class='dgText']/tbody/tr/td/span[.='" + reservation
				+ "']/ ../ ../td/table/tbody/tr/td/input[1])[1]";

		// (//table[@class='dgText']/tbody/tr/td/span[.='15056097']/ ../
		// ../td/table/tbody/tr/td/input[1])[1]

		Wait.wait5Second();

		WebElement complete = driver.findElement(By.xpath(xpath));

		Wait.explicit_wait_elementToBeClickable(complete, driver);

		driver.findElement(By.xpath(xpath)).click();

		Wait.wait5Second();

		tasklistLogger.info(" Clicked on Complete in TaskList");
		test_steps.add(" Clicked on Complete in TaskList");

	}

	public void TaskList_SaveButton(WebDriver driver) {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);

		// Click Save Task
		Wait.WaitForElement(driver, OR.SaveTaskList);
		TaskList.SaveTaskList.click();
		tasklistLogger.info("Saved the Changes");
		test_steps.add("Saved the Changes");
	}

	public void TaskValidationsInTaskList(WebDriver driver, String TaskSubject, String reservation, String TaskNoteType,
			ArrayList<String> test_steps) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		// Open task

		String xpath = "//table[@class='dgText']/tbody/tr/td/span[.='" + reservation
				+ "']/following::td/preceding-sibling::td/table/tbody/tr/td/a[.='" + TaskSubject + "']";

		WebElement task = driver.findElement(By.xpath(xpath));

		Utility.ScrollToElement(task, driver);

		driver.findElement(By.xpath(xpath)).click();

		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));

		// Validations
		String TaskList_NotesType = new Select(TaskList.TaskListNotesType).getFirstSelectedOption().getText();

		tasklistLogger.info(" TaskList_NotesType " + TaskList_NotesType);

		test_steps.add(" TaskList_NotesType " + TaskList_NotesType);

		String TaskListAction = new Select(TaskList.TaskList_Action).getFirstSelectedOption().getText();

		tasklistLogger.info(" TaskListAction: " + TaskListAction);

		test_steps.add(" TaskListAction: " + TaskListAction);

		if (TaskList_NotesType.equalsIgnoreCase(TaskNoteType) && TaskList.TaskList_ActionRequired.isSelected()
				&& TaskListAction.contains("Completed")) {

			Wait.WaitForElement(driver, OR.TaskListPopUp_CancelButton);
			TaskList.TaskListPopUp_CancelButton.click();
			tasklistLogger.info("Clicked on Cancel button");
			test_steps.add(" Verified the Task and Closed Task Popup ");

		}

		driver.switchTo().defaultContent();
	}

	public void CreateTask(WebDriver driver, String task, String category, String categoryType, String details,
			String Reservation_Account_Number) throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		// Select task for like reservation, account etc
		Wait.waitUntilPresenceOfElementLocated(OR.SelectTask, driver);
		TaskList.SelectTask.click();
		Wait.wait1Second();
		Wait.waitUntilPresenceOfElementLocated("//span[text()='" + task + "']", driver);
		driver.findElement(By.xpath("//span[text()='" + task + "']")).click();
		assertTrue(TaskList.SelectTask.getText().contains(task), "Failed: Select Task");
		tasklistLogger.info("Task: " + task);
		Wait.wait2Second();

		// search reservation number, account number
		Wait.WaitForElement(driver, OR.TypeSearch);
		TaskList.TypeSearch.sendKeys(Reservation_Account_Number);
		Wait.wait3Second();
		WebElement searchResult = driver
				.findElement(By.xpath("//*[contains (text(), '" + Reservation_Account_Number + "')]"));
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(searchResult, driver);
		searchResult.click();

		TaskList.TaskCategory.click();
		Wait.wait1Second();
		Wait.waitUntilPresenceOfElementLocated("//span[text()='" + category + "']", driver);
		driver.findElement(By.xpath("//span[text()='" + category + "']")).click();
		assertTrue(TaskList.TaskCategory.getText().contains(category), "Failed: Task Category");
		tasklistLogger.info("Task Category: " + category);

		TaskList.CategoryType.click();
		Wait.wait1Second();
		Wait.waitUntilPresenceOfElementLocated(
				"//select[@name='categoryType']//following-sibling::div/div//span[text()='" + categoryType + "']",
				driver);
		driver.findElement(By.xpath(
				"//select[@name='categoryType']//following-sibling::div/div//span[text()='" + categoryType + "']"))
				.click();
		assertTrue(TaskList.CategoryType.getText().contains(categoryType), "Failed: Category Type ");
		tasklistLogger.info("Category Type: " + categoryType);

		TaskList.Task_Detail.sendKeys(details);
		tasklistLogger.info("Task Detail: " + details);
		TaskList.Task_Remarks.sendKeys();

		TaskList.Task_Save.click();
		tasklistLogger.info("Click Save");
		Wait.explicit_wait_visibilityof_webelement_120(TaskList.Toaster_Message, driver);
		System.out.println(TaskList.Toaster_Message.getText());
		TaskList.ToastCloseButton.click();

	}

	public void ClickOnCreatedTask(WebDriver driver, String First_Last_Name) throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		WebElement element = driver.findElement(By.xpath("//td[text()='" + First_Last_Name + "']"));
		Wait.explicit_wait_visibilityof_webelement(element, driver);
		element.click();
		Wait.wait1Second();

	}

	public ArrayList<String> VerifyCreatedTask(WebDriver driver, String task, String category, String TaskName,
			String details, ArrayList<String> steps) throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.WaitForElement(driver, OR.TaskPopup_Title);
		Wait.explicit_wait_visibilityof_webelement_200(TaskList.TaskPopup_Title, driver);
		assertEquals(TaskList.TaskPopup_Title.getText(), TaskName, "Failed to verify addes task name");
		steps.add("Verified task type in " + task + " task");
		assertEquals(TaskList.TaskPopup_Details.getText(), details, "Failed to verify task details");
		steps.add("Verified task details in " + task + " task");
		return steps;
	}

	public void TaskPopup_CloseButton(WebDriver driver) throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.WaitForElement(driver, OR.TaskPopup_CloseButton);
		TaskList.TaskPopup_CloseButton.click();
		Wait.wait1Second();
	}

	public void CreateTaskForRoom(WebDriver driver, String task, String category, String categoryType, String details,
			String RoomNumber) throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		// Select task for like reservation, account etc
		Wait.waitUntilPresenceOfElementLocated(OR.SelectTask, driver);
		TaskList.SelectTask.click();
		Wait.wait1Second();
		Wait.waitUntilPresenceOfElementLocated("//span[text()='" + task + "']", driver);
		driver.findElement(By.xpath("//span[text()='" + task + "']")).click();
		assertTrue(TaskList.SelectTask.getText().contains(task), "Failed: Select Task");
		tasklistLogger.info("Task: " + task);
		Wait.wait2Second();

		// search room number
		Wait.WaitForElement(driver, OR.TypeSearch);
		TaskList.TypeSearch.sendKeys(RoomNumber);
		Wait.wait3Second();

		List<WebElement> searchResult = driver.findElements(By.xpath("//span//strong"));
		Wait.explicit_wait_visibilityof_webelement(searchResult.get(0), driver);
		searchResult.get(0).click();

		TaskList.TaskCategory.click();
		Wait.wait1Second();
		Wait.waitUntilPresenceOfElementLocated("//span[text()='" + category + "']", driver);
		driver.findElement(By.xpath("//span[text()='" + category + "']")).click();
		assertTrue(TaskList.TaskCategory.getText().contains(category), "Failed: Task Category");
		tasklistLogger.info("Task Category: " + category);

		TaskList.CategoryType.click();
		Wait.wait1Second();
		Wait.waitUntilPresenceOfElementLocated(
				"//select[@name='categoryType']//following-sibling::div/div//span[text()='" + categoryType + "']",
				driver);
		driver.findElement(By.xpath(
				"//select[@name='categoryType']//following-sibling::div/div//span[text()='" + categoryType + "']"))
				.click();
		assertTrue(TaskList.CategoryType.getText().contains(categoryType), "Failed: Category Type ");
		tasklistLogger.info("Category Type: " + categoryType);

		TaskList.Task_Detail.sendKeys(details);
		tasklistLogger.info("Task Detail: " + details);
		TaskList.Task_Remarks.sendKeys();

		TaskList.Task_Save.click();
		tasklistLogger.info("Click Save");
		Wait.explicit_wait_visibilityof_webelement_120(TaskList.Toaster_Message, driver);
		System.out.println(TaskList.Toaster_Message.getText());
		TaskList.ToastCloseButton.click();

	}

	public void ClickOnCreatedRoomTask(WebDriver driver, String RoomNumber) throws InterruptedException {

		WebElement element = driver.findElement(
				By.xpath("//td[contains(text(),'" + RoomNumber + "')]//following-sibling::td[text()='Internal']"));
		Wait.explicit_wait_visibilityof_webelement(element, driver);
		element.click();
		Wait.wait1Second();

	}

	public void VerifyUpdatedTask(WebDriver driver, String UpdatedTaskName) throws InterruptedException {

		String path = "//span[text()='" + UpdatedTaskName + "']";
		Wait.WaitForElement(driver, path);
		List<WebElement> element = driver.findElements(By.xpath(path));
		Wait.explicit_wait_visibilityof_webelement(element.get(0), driver);

		for (int i = 0; i < element.size(); i++) {
			assertEquals(element.get(i).getText(), UpdatedTaskName, "Failed to find updated task name in task list");
		}

	}

	public void DeleteCreatedTask(WebDriver driver) throws InterruptedException {

		WebElements_TaskList taskList = new WebElements_TaskList(driver);

		// select all task
		Wait.WaitForElement(driver, OR.SelectAllTask);
		taskList.SelectAllTask.click();
		taskList.DeleteTask_Button.click();
		Wait.wait1Second();
		Wait.WaitForElement(driver, OR.TaskDelete_ProcessButton);
		taskList.TaskDelete_ProcessButton.click();

		Wait.explicit_wait_visibilityof_webelement_120(taskList.Toaster_Message, driver);
		System.out.println(taskList.Toaster_Message.getText());
		taskList.ToastCloseButton.click();

	}

	public void Verify_DeleteCreatedTask(WebDriver driver) throws InterruptedException {

		WebElements_TaskList taskList = new WebElements_TaskList(driver);

		// select all task
		Wait.WaitForElement(driver, OR.SelectAllTask);
		if (taskList.NoTaskFound.size() != 1) {
			taskList.SelectAllTask.click();
			taskList.DeleteTask_Button.click();
			Wait.wait1Second();
			Wait.WaitForElement(driver, OR.TaskDelete_ProcessButton);
			taskList.TaskDelete_ProcessButton.click();

			Wait.explicit_wait_visibilityof_webelement_120(taskList.Toaster_Message, driver);
			System.out.println(taskList.Toaster_Message.getText());
			taskList.ToastCloseButton.click();
		}

	}

	public ArrayList<String> CreateNewTask(WebDriver driver, String task, String category, String categoryType,
			String details, String remarks, String assignee, String ResNumber, ArrayList<String> test_steps)
			throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.SelectTask, driver);
		Wait.explicit_wait_visibilityof_webelement(TaskList.SelectTask, driver);
		TaskList.SelectTask.click();
		Wait.waitUntilPresenceOfElementLocated("//span[text()='" + task + "']", driver);
		driver.findElement(By.xpath("//span[text()='" + task + "']")).click();
		assertTrue(TaskList.SelectTask.getText().contains(task), "Failed: Select Task");
		tasklistLogger.info("Task selected for " + task);
		test_steps.add("Task selected for " + task);

		// search reservation number, account number
		Wait.WaitForElement(driver, OR.TypeSearch);
		TaskList.TypeSearch.sendKeys(ResNumber);
		Wait.wait3Second();
		WebElement searchResult = driver.findElement(By.xpath("//*[contains (text(), '" + ResNumber + "')]"));
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(searchResult, driver);
		searchResult.click();

		TaskList.TaskCategory.click();
		Wait.waitUntilPresenceOfElementLocated("//span[text()='" + category + "']", driver);
		driver.findElement(By.xpath("//span[text()='" + category + "']")).click();
		assertTrue(TaskList.TaskCategory.getText().contains(category), "Failed: Task Category");
		tasklistLogger.info("Task Category: " + category);
		test_steps.add("Task Category: " + category);
		TaskList.CategoryType.click();
		Wait.wait3Second();
		Wait.waitUntilPresenceOfElementLocated(
				"//select[@name='categoryType']//following-sibling::div/div//span[text()='" + categoryType + "']",
				driver);
		driver.findElement(By.xpath(
				"//select[@name='categoryType']//following-sibling::div/div//span[text()='" + categoryType + "']"))
				.click();
		assertTrue(TaskList.CategoryType.getText().contains(categoryType), "Failed: Category Type ");
		tasklistLogger.info("Category Type: " + categoryType);
		test_steps.add("Category Type: " + categoryType);

		TaskList.Task_Detail.sendKeys(details);
		// assertTrue(TaskList.Task_Detail.getText().contains(task),"Failed:
		// Task Detail ");
		tasklistLogger.info("Task Detail: " + details);
		TaskList.Task_Remarks.sendKeys(remarks);
		// assertTrue(TaskList.Task_Remarks.getText().contains(task),"Failed:
		// Task Remarks");
		tasklistLogger.info("Task Remarks: " + remarks);
		TaskList.Task_Assignee.sendKeys(assignee);
		// assertTrue(TaskList.Task_Assignee.getText().contains(assignee),
		// "Failed: TaskAssignee ");
		tasklistLogger.info("Task Assignee: " + assignee);
		// Wait.wait3Second();
		// driver.findElement(By.xpath("//*[contains (text(),
		// 'Unassigned')]")).click();
		WebElement GetStatus = driver.findElement(By.xpath("(//span[text()='To Do'])[3]"));
		WebElement GetDate = driver.findElement(By.xpath("//*[@id=\'addTaskDatePicker\']"));
		WebElement GetAssignee = driver.findElement(By.xpath("//input[@name='taskAssignee']"));
		// test_steps.add(GetStatus.getAttribute("value"));
		test_steps.add(GetDate.getAttribute("value"));
		test_steps.add(GetAssignee.getAttribute("value"));
		TaskList.Task_Save.click();
		tasklistLogger.info("Click Save");
		// test_steps.add("Task Saved");

		return test_steps;

	}

	public ArrayList<String> taskVerification(WebDriver driver, String guestName, String details, String Status,
			String Date, String Assignee, ArrayList<String> steps) throws InterruptedException {
		// TODO Auto-generated method stub

		WebElements_TaskList tasklist = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.SearchRoomAccAssign, driver);
		tasklist.SearchRoomAccAssign.clear();
		tasklist.SearchRoomAccAssign.sendKeys(guestName);
		Wait.wait5Second();
		tasklist.TaskList_SearchButton.click();
		String TaskPath = "//table[contains(@class,'taskList')]/tbody/tr//following::*[text()='" + guestName + "']";
		Wait.WaitForElement(driver, TaskPath);
		Wait.wait5Second();
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(TaskPath)), driver);
		List<WebElement> TasksList = driver.findElements(By.xpath(TaskPath));
		boolean taskfound = false;
		for (WebElement task : TasksList) {
			try {
				task.isDisplayed();
				WebElement GuestCheck = driver.findElement(By.xpath(
						"//table[contains(@class,'taskList')]/tbody/tr//following::*[text()='" + guestName + "']"));
				GuestCheck.getText();
				assertTrue(TaskPath.contains(guestName), "Failed : Task Guest name not found");
				test_steps.add("Verified guets Name :" + guestName);

				Wait.wait5Second();
				WebElement DetailsCheck = driver.findElement(By.xpath(
						"//table[contains(@class,'taskList')]/tbody/tr//following::*[text()='" + details + "']"));
				DetailsCheck.getText();
				assertEquals(DetailsCheck.getText(), details, "Failed : Detail missmatched");
				test_steps.add("Verified Task popup decs :" + details);

				WebElement DetailsStatus = driver.findElement(By
						.xpath("//table[contains(@class,'taskList')]/tbody/tr//following::*[text()='" + Status + "']"));
				DetailsStatus.getText();
				assertEquals(DetailsStatus.getText(), Status, "Failed : Status missmatched");
				test_steps.add("Verified Task popup Status :" + Status);

				WebElement DateCheck = driver.findElement(
						By.xpath("//table[contains(@class,'taskList')]/tbody/tr//following::*[text()='" + Date + "']"));
				DateCheck.getText();
				assertEquals(DateCheck.getText(), Date, "Failed : Date missmatched");
				test_steps.add("Verified Task popup Date :" + Date);

				WebElement DateAssigned = driver.findElement(By.xpath(
						"//table[contains(@class,'taskList')]/tbody/tr//following::*[text()='" + Assignee + "']"));
				DateAssigned.getText();
				assertEquals(DateAssigned.getText(), Assignee, "Failed : Assignee missmatched");
				test_steps.add("Verified Task popup Assignee :" + Assignee);

				taskfound = true;
			} catch (Exception e) {
				taskfound = false;
			}
			if (taskfound) {
				break;
			}

		}
		return steps;

	}

	public ArrayList<String> DetailVerification(WebDriver driver, String resNumber, String guestName, String details,
			String Room, String Status, String Date, String Assignee, ArrayList<String> steps)
			throws InterruptedException {
		// TODO Auto-generated method stub
		driver.navigate().refresh();
		WebElements_TaskList tasklist = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.SearchRoomAccAssign, driver);
		tasklist.SearchRoomAccAssign.clear();
		tasklist.SearchRoomAccAssign.sendKeys(guestName);
		tasklist.TaskList_SearchButton.click();
		Wait.wait5Second();
		String TaskPath = "//table[contains(@class,'taskList')]/tbody/tr//following::*[text()='" + guestName + "']";
		Wait.WaitForElement(driver, TaskPath);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(TaskPath)), driver);
		List<WebElement> TasksList = driver.findElements(By.xpath(TaskPath));
		boolean taskfound = false;
		for (WebElement task : TasksList) {
			try {
				task.click();
				Wait.waitUntilPresenceOfElementLocated(OR.EditTaskPopup, driver);
				String task_guest = tasklist.Taskpopup_GuestName.getText();
				Utility.app_logs.info(task_guest);
				assertTrue(task_guest.contains(guestName), "Failed : Task Guest name not found");
				test_steps.add("Verified guets Name :" + guestName);
				assertTrue(task_guest.contains(resNumber), "Failed : Task Reservation Number not found");
				test_steps.add("Verified Res Number :" + resNumber);
				assertEquals(tasklist.Taskpopup_TaskDescription.getText(), details, "Failed : Detail missmatched");
				test_steps.add("Verified Task popup decs :" + details);
				assertEquals(tasklist.Taskpopup_RoomCtegory.getText(), "(" + Room + ")",
						"Failed : RoomClassName missmatched");
				test_steps.add("Verified Task popup room Class :" + Room);

				assertEquals(tasklist.Taskpopup_Status.getText(), Status, "Failed : Status missmatched");
				test_steps.add("Verified Task popup Status :" + Status);

				WebElement DateCheck = driver
						.findElement(By.xpath("//div[@class='modal-content gs-borderR0']//dd[text()='" + Date + "']"));
				DateCheck.getText();
				assertEquals(DateCheck.getText(), Date, "Failed : Date missmatched");
				test_steps.add("Verified Task popup Date :" + Date);

				assertEquals(tasklist.Taskpopup_Assignee.getText(), Assignee, "Failed : Assignee missmatched");
				test_steps.add("Verified Task popup Assignee :" + Assignee);

				taskfound = true;
			} catch (Exception e) {
				taskfound = false;
			}
			if (taskfound) {
				break;
			}

		}
		return steps;

	}

	public void SelectDateFilter_Standard(WebDriver driver, String datefilter) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		driver.navigate().refresh();
		Wait.explicit_wait_xpath(OR.ClickDateFilter, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", TaskList.ClickDateFilter);
		// TaskList.ClickDateFilter.click();
		Wait.wait2Second();
		driver.findElement(By.xpath("//li[@data-range-key='" + datefilter + "']")).click();
		tasklistLogger.info("Date Filter: " + datefilter);

	}

	String statusCheck_Report = "";

	public ArrayList<String> StatusBar_Report(WebDriver driver, String StatusFilter, ArrayList<String> test_steps)
			throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.StatusBar_Report, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", TaskList.StatusBar_Report);
		test_steps.add("Verified Status Bar :");

		if (StatusFilter == "To Do") {
			Wait.wait2Second();
			driver.findElement(By.xpath("//ul[@class='gs-searchFilter']//li[@class='todo']")).click();
			Wait.wait2Second();
			WebElement myElement = driver.findElement(By.cssSelector("li.todo span"));
			System.out.println(((JavascriptExecutor) driver)
					.executeScript("return arguments[0].firstChild.textContent;", myElement).toString());
			statusCheck_Report = (String) ((JavascriptExecutor) driver)
					.executeScript("return arguments[0].firstChild.textContent;", myElement);
			System.out.println(statusCheck_Report);
			test_steps.add("Verified To Do Status :" + statusCheck_Report);

			WebElement TodoCheck = driver.findElement(
					By.xpath("//ul[@class='gs-searchFilter']//span[contains(text(),'" + StatusFilter + "')]"));
			TodoCheck.getText();
			assertEquals(TodoCheck.getText(), StatusFilter, "Failed : Date missmatched");
			System.out.println("Inspection text" + TodoCheck.getText());
			test_steps.add("Verified Status :" + StatusFilter);
			tasklistLogger.info("Verified Status : " + StatusFilter);
		} else if (StatusFilter == "Inspection") {
			Wait.wait2Second();
			driver.findElement(By.xpath("//ul[@class='gs-searchFilter']//li[@class='inspection']")).click();
			Wait.wait2Second();
			WebElement myElement = driver.findElement(By.cssSelector("li.inspection span"));
			System.out.println(((JavascriptExecutor) driver)
					.executeScript("return arguments[0].firstChild.textContent;", myElement).toString());
			statusCheck_Report = (String) ((JavascriptExecutor) driver)
					.executeScript("return arguments[0].firstChild.textContent;", myElement);
			System.out.println(statusCheck_Report);
			test_steps.add("Verified To Do Status :" + statusCheck_Report);

			WebElement inspectionCheck = driver.findElement(
					By.xpath("//ul[@class='gs-searchFilter']//span[contains(text(),'" + StatusFilter + "')]"));
			inspectionCheck.getText();
			assertEquals(inspectionCheck.getText(), StatusFilter, "Failed : Date missmatched");
			System.out.println("Inspection text" + inspectionCheck.getText());
			test_steps.add("Verified Status :" + StatusFilter);
			tasklistLogger.info("Verified Status : " + StatusFilter);
		} else if (StatusFilter == "Done") {
			Wait.wait2Second();
			driver.findElement(By.xpath("//ul[@class='gs-searchFilter']//li[@class='done']")).click();
			Wait.wait2Second();
			WebElement myElement = driver.findElement(By.cssSelector("li.done span"));
			System.out.println(((JavascriptExecutor) driver)
					.executeScript("return arguments[0].firstChild.textContent;", myElement).toString());
			statusCheck_Report = (String) ((JavascriptExecutor) driver)
					.executeScript("return arguments[0].firstChild.textContent;", myElement);
			System.out.println(statusCheck_Report);
			test_steps.add("Verified Done Status :" + statusCheck_Report);

			WebElement doneCheck = driver.findElement(
					By.xpath("//ul[@class='gs-searchFilter']//span[contains(text(),'" + StatusFilter + "')]"));
			doneCheck.getText();
			assertEquals(doneCheck.getText(), StatusFilter, "Failed : Date missmatched");
			System.out.println("Done text" + doneCheck.getText());
			test_steps.add("Verified Status :" + StatusFilter);
			tasklistLogger.info("Verified Status : " + StatusFilter);
		} else if (StatusFilter == "All") {
			Wait.wait2Second();
			driver.findElement(By.xpath("//ul[@class='gs-searchFilter']//li[@class='all']")).click();
			Wait.wait2Second();
			WebElement myElement = driver.findElement(By.cssSelector("li.all span"));
			System.out.println(((JavascriptExecutor) driver)
					.executeScript("return arguments[0].firstChild.textContent;", myElement).toString());
			statusCheck_Report = (String) ((JavascriptExecutor) driver)
					.executeScript("return arguments[0].firstChild.textContent;", myElement);
			System.out.println(statusCheck_Report);
			test_steps.add("Verified All Status :" + statusCheck_Report);

			WebElement allCheck = driver.findElement(
					By.xpath("//ul[@class='gs-searchFilter']//span[contains(text(),'" + StatusFilter + "')]"));
			allCheck.getText();
			assertEquals(allCheck.getText(), StatusFilter, "Failed : Date missmatched");
			System.out.println("All text" + allCheck.getText());
			test_steps.add("Verified Status :" + StatusFilter);
			tasklistLogger.info("Verified Status : " + StatusFilter);
		}

		return test_steps;

	}

	String statusCheck = "";

	public ArrayList<String> StatusBar(WebDriver driver, String StatusFilter, ArrayList<String> test_steps)
			throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.StatusBar, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", TaskList.StatusBar);
		test_steps.add("Click On Status Bar");
		if (StatusFilter == "To Do") {
			Wait.wait2Second();
			driver.findElement(By.xpath("//ul[@class='gs-searchFilter mob-divid-into-5']//li[@class='todo active']"))
					.click();
			test_steps.add("Click On Status Bar");
			Wait.wait2Second();
			WebElement myElement = driver.findElement(By.cssSelector("li.todo.active span"));
			System.out.println(((JavascriptExecutor) driver)
					.executeScript("return arguments[0].firstChild.textContent;", myElement).toString());
			statusCheck = (String) ((JavascriptExecutor) driver)
					.executeScript("return arguments[0].firstChild.textContent;", myElement);
			System.out.println(statusCheck);
			test_steps.add("Verified To Do Status :" + statusCheck);

			WebElement TodoCheck = driver.findElement(By.xpath(
					"//ul[@class='gs-searchFilter mob-divid-into-5']//span[contains(text(),'" + StatusFilter + "')]"));
			TodoCheck.getText();
			assertEquals(TodoCheck.getText(), StatusFilter, "Failed : Date missmatched");
			System.out.println("To Do text" + TodoCheck.getText());
			test_steps.add("Verified Status In Status Bar :" + StatusFilter);
			tasklistLogger.info("Verified Status In Status Bar :" + StatusFilter);
		} else if (StatusFilter == "Inspection") {
			Wait.wait2Second();
			driver.findElement(By.xpath("//ul[@class='gs-searchFilter mob-divid-into-5']//li[@class='inspection']"))
					.click();
			Wait.wait2Second();
			WebElement myElement = driver.findElement(By.cssSelector("li.inspection.active span"));
			System.out.println(((JavascriptExecutor) driver)
					.executeScript("return arguments[0].firstChild.textContent;", myElement).toString());
			statusCheck = (String) ((JavascriptExecutor) driver)
					.executeScript("return arguments[0].firstChild.textContent;", myElement);
			System.out.println(statusCheck);
			test_steps.add("Verified Status :" + statusCheck);

			WebElement inspectionCheck = driver.findElement(By.xpath(
					"//ul[@class='gs-searchFilter mob-divid-into-5']//span[contains(text(),'" + StatusFilter + "')]"));
			inspectionCheck.getText();
			assertEquals(inspectionCheck.getText(), StatusFilter, "Failed : Date missmatched");
			System.out.println("Inspection text" + inspectionCheck.getText());
			test_steps.add("Verified Status In Status Bar :" + StatusFilter);
			tasklistLogger.info("Verified Status In Status Bar: " + StatusFilter);
		} else if (StatusFilter == "Done") {
			Wait.wait2Second();
			driver.findElement(By.xpath("//ul[@class='gs-searchFilter mob-divid-into-5']//li[@class='done']")).click();
			Wait.wait2Second();
			WebElement myElement = driver.findElement(By.cssSelector("li.done.active span"));
			System.out.println(((JavascriptExecutor) driver)
					.executeScript("return arguments[0].firstChild.textContent;", myElement).toString());
			statusCheck = (String) ((JavascriptExecutor) driver)
					.executeScript("return arguments[0].firstChild.textContent;", myElement);
			System.out.println(statusCheck);
			test_steps.add("Verified Status :" + statusCheck);

			WebElement doneCheck = driver.findElement(By.xpath(
					"//ul[@class='gs-searchFilter mob-divid-into-5']//span[contains(text(),'" + StatusFilter + "')]"));
			doneCheck.getText();
			assertEquals(doneCheck.getText(), StatusFilter, "Failed : Date missmatched");
			System.out.println("Done text" + doneCheck.getText());
			test_steps.add("Verified Status In Status Bar :" + StatusFilter);
			tasklistLogger.info("Verified Status In Status Bar: " + StatusFilter);
		} else if (StatusFilter == "All") {
			Wait.wait2Second();
			driver.findElement(By.xpath("//ul[@class='gs-searchFilter mob-divid-into-5']//li[@class='all']")).click();
			Wait.wait2Second();
			WebElement myElement = driver.findElement(By.cssSelector("li.all.active span"));
			System.out.println(((JavascriptExecutor) driver)
					.executeScript("return arguments[0].firstChild.textContent;", myElement).toString());
			statusCheck = (String) ((JavascriptExecutor) driver)
					.executeScript("return arguments[0].firstChild.textContent;", myElement);
			System.out.println(statusCheck);
			test_steps.add("Verified Status :" + statusCheck);

			WebElement allCheck = driver.findElement(By.xpath(
					"//ul[@class='gs-searchFilter mob-divid-into-5']//span[contains(text(),'" + StatusFilter + "')]"));
			allCheck.getText();
			assertEquals(allCheck.getText(), StatusFilter, "Failed : Date missmatched");
			System.out.println("All text" + allCheck.getText());
			test_steps.add("Verified Status In Status Bar :" + StatusFilter);
			tasklistLogger.info("Verified Status In Status Bar: " + StatusFilter);
		}

		return test_steps;

	}

	String totalAmount = "";

	public ArrayList<String> StatusBar_All(WebDriver driver, String StatusFilter, ArrayList<String> test_steps)
			throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.StatusBar, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", TaskList.StatusBar);
		test_steps.add("Click On Status Bar");
		StatusFilter = "To Do";
		Wait.wait2Second();
		driver.findElement(By.xpath("//ul[@class='gs-searchFilter mob-divid-into-5']//li[@class='todo active']"))
				.click();
		test_steps.add("Click On Status Bar");
		Wait.wait2Second();
		WebElement myElement_Todo = driver.findElement(By.cssSelector("li.todo.active span"));
		// System.out.println(((JavascriptExecutor) driver)
		// .executeScript("return arguments[0].firstChild.textContent;",
		// myElement_Todo).toString());
		String todoCount = (String) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].firstChild.textContent;", myElement_Todo);
		// System.out.println(todoCount);
		test_steps.add("Verified To Do Status :" + todoCount);

		float amountToDo = Float.parseFloat(todoCount);

		WebElement TodoCheck = driver.findElement(By.xpath(
				"//ul[@class='gs-searchFilter mob-divid-into-5']//span[contains(text(),'" + StatusFilter + "')]"));
		TodoCheck.getText();
		assertEquals(TodoCheck.getText(), StatusFilter, "Failed : Date missmatched");
		// System.out.println("To Do text" + TodoCheck.getText());
		test_steps.add("Verified Status In Status Bar :" + StatusFilter);
		tasklistLogger.info("Verified Status In Status Bar :" + StatusFilter);
		StatusFilter = "Inspection";
		Wait.wait2Second();
		driver.findElement(By.xpath("//ul[@class='gs-searchFilter mob-divid-into-5']//li[@class='inspection']"))
				.click();
		Wait.wait2Second();
		WebElement myElement_Inspection = driver.findElement(By.cssSelector("li.inspection.active span"));
		// System.out.println(((JavascriptExecutor) driver)
		// .executeScript("return arguments[0].firstChild.textContent;",
		// myElement_Inspection).toString());
		String inspectionCount = (String) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].firstChild.textContent;", myElement_Inspection);
		// System.out.println(inspectionCount);
		test_steps.add("Verified Status :" + inspectionCount);
		float amountInspection = Float.parseFloat(inspectionCount);

		WebElement inspectionCheck = driver.findElement(By.xpath(
				"//ul[@class='gs-searchFilter mob-divid-into-5']//span[contains(text(),'" + StatusFilter + "')]"));
		inspectionCheck.getText();
		assertEquals(inspectionCheck.getText(), StatusFilter, "Failed : Date missmatched");
		// System.out.println("Inspection text" + inspectionCheck.getText());
		test_steps.add("Verified Status In Status Bar :" + StatusFilter);
		tasklistLogger.info("Verified Status In Status Bar: " + StatusFilter);
		StatusFilter = "Done";
		Wait.wait2Second();
		driver.findElement(By.xpath("//ul[@class='gs-searchFilter mob-divid-into-5']//li[@class='done']")).click();
		Wait.wait2Second();
		WebElement myElement_Done = driver.findElement(By.cssSelector("li.done.active span"));
		// System.out.println(((JavascriptExecutor) driver)
		// .executeScript("return arguments[0].firstChild.textContent;",
		// myElement_Done).toString());
		String doneCount = (String) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].firstChild.textContent;", myElement_Done);
		// System.out.println(doneCount);
		test_steps.add("Verified Status :" + doneCount);
		float amountDone = Float.parseFloat(doneCount);

		WebElement doneCheck = driver.findElement(By.xpath(
				"//ul[@class='gs-searchFilter mob-divid-into-5']//span[contains(text(),'" + StatusFilter + "')]"));
		doneCheck.getText();
		assertEquals(doneCheck.getText(), StatusFilter, "Failed : Date missmatched");
		System.out.println("Done text" + doneCheck.getText());
		test_steps.add("Verified Status In Status Bar :" + StatusFilter);
		tasklistLogger.info("Verified Status In Status Bar: " + StatusFilter);
		Float sum = amountToDo + amountInspection + amountDone;
		// System.out.println("Total Amount" + sum);
		test_steps.add("Total Amount" + sum);
		tasklistLogger.info("Total Amount" + sum);
		StatusFilter = "All";
		Wait.wait2Second();
		driver.findElement(By.xpath("//ul[@class='gs-searchFilter mob-divid-into-5']//li[@class='all']")).click();
		Wait.wait2Second();
		WebElement myElement_All = driver.findElement(By.cssSelector("li.all.active span"));
		// System.out.println(((JavascriptExecutor) driver)
		// .executeScript("return arguments[0].firstChild.textContent;",
		// myElement_All).toString());
		String allCount = (String) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].firstChild.textContent;", myElement_All);
		// System.out.println(allCount);
		test_steps.add("Verified Status :" + allCount);
		float amountAll = Float.parseFloat(allCount);
		assertEquals(sum, amountAll, "Failed : sum Mismatched");
		totalAmount = Float.toString(sum);
		WebElement allCheck = driver.findElement(By.xpath(
				"//ul[@class='gs-searchFilter mob-divid-into-5']//span[contains(text(),'" + StatusFilter + "')]"));
		allCheck.getText();
		assertEquals(allCheck.getText(), StatusFilter, "Failed : Date missmatched");
		// System.out.println("All text" + allCheck.getText());
		test_steps.add("Verified Status In Status Bar :" + StatusFilter);
		tasklistLogger.info("Verified Status In Status Bar: " + StatusFilter);

		return test_steps;

	}

	String totalAmount_Report = "";

	public ArrayList<String> StatusBar_All_Report(WebDriver driver, String StatusFilter, ArrayList<String> test_steps)
			throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.StatusBar_Report, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", TaskList.StatusBar_Report);
		test_steps.add("Click On Status Bar");
		StatusFilter = "To Do";
		Wait.wait2Second();
		driver.findElement(By.xpath("//ul[@class='gs-searchFilter']//li[@class='todo']")).click();
		test_steps.add("Click On Status Bar");
		Wait.wait2Second();
		WebElement myElement_Todo = driver.findElement(By.cssSelector("li.todo span"));
		// System.out.println(((JavascriptExecutor) driver)
		// .executeScript("return arguments[0].firstChild.textContent;",
		// myElement_Todo).toString());
		String todoCount = (String) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].firstChild.textContent;", myElement_Todo);
		// System.out.println(todoCount);
		test_steps.add("Verified To Do Status :" + todoCount);

		float amountToDo = Float.parseFloat(todoCount);

		WebElement TodoCheck = driver
				.findElement(By.xpath("//ul[@class='gs-searchFilter']//span[contains(text(),'" + StatusFilter + "')]"));
		TodoCheck.getText();
		assertEquals(TodoCheck.getText(), StatusFilter, "Failed : Date missmatched");
		// System.out.println("To Do text" + TodoCheck.getText());
		test_steps.add("Verified Status In Status Bar :" + StatusFilter);
		tasklistLogger.info("Verified Status In Status Bar :" + StatusFilter);
		StatusFilter = "Inspection";
		Wait.wait2Second();
		driver.findElement(By.xpath("//ul[@class='gs-searchFilter']//li[@class='inspection']")).click();
		Wait.wait2Second();
		WebElement myElement_Inspection = driver.findElement(By.cssSelector("li.inspection span"));
		// System.out.println(((JavascriptExecutor) driver)
		// .executeScript("return arguments[0].firstChild.textContent;",
		// myElement_Inspection).toString());
		String inspectionCount = (String) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].firstChild.textContent;", myElement_Inspection);
		// System.out.println(inspectionCount);
		test_steps.add("Verified Status :" + inspectionCount);
		float amountInspection = Float.parseFloat(inspectionCount);

		WebElement inspectionCheck = driver
				.findElement(By.xpath("//ul[@class='gs-searchFilter']//span[contains(text(),'" + StatusFilter + "')]"));
		inspectionCheck.getText();
		assertEquals(inspectionCheck.getText(), StatusFilter, "Failed : Date missmatched");
		// System.out.println("Inspection text" + inspectionCheck.getText());
		test_steps.add("Verified Status In Status Bar :" + StatusFilter);
		tasklistLogger.info("Verified Status In Status Bar: " + StatusFilter);
		StatusFilter = "Done";
		Wait.wait2Second();
		driver.findElement(By.xpath("//ul[@class='gs-searchFilter']//li[@class='done']")).click();
		Wait.wait2Second();
		WebElement myElement_Done = driver.findElement(By.cssSelector("li.done span"));
		// System.out.println(((JavascriptExecutor) driver)
		// .executeScript("return arguments[0].firstChild.textContent;",
		// myElement_Done).toString());
		String doneCount = (String) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].firstChild.textContent;", myElement_Done);
		// System.out.println(doneCount);
		test_steps.add("Verified Status :" + doneCount);
		float amountDone = Float.parseFloat(doneCount);

		WebElement doneCheck = driver
				.findElement(By.xpath("//ul[@class='gs-searchFilter']//span[contains(text(),'" + StatusFilter + "')]"));
		doneCheck.getText();
		assertEquals(doneCheck.getText(), StatusFilter, "Failed : Date missmatched");
		// System.out.println("Done text" + doneCheck.getText());
		test_steps.add("Verified Status In Status Bar :" + StatusFilter);
		tasklistLogger.info("Verified Status In Status Bar: " + StatusFilter);
		Float sum = amountToDo + amountInspection + amountDone;
		// System.out.println("Total Amount" + sum);
		test_steps.add("Total Amount" + sum);
		tasklistLogger.info("Total Amount" + sum);
		StatusFilter = "All";
		Wait.wait2Second();
		driver.findElement(By.xpath("//ul[@class='gs-searchFilter']//li[@class='all']")).click();
		Wait.wait2Second();
		WebElement myElement_All = driver.findElement(By.cssSelector("li.all span"));
		// System.out.println(((JavascriptExecutor) driver)
		// .executeScript("return arguments[0].firstChild.textContent;",
		// myElement_All).toString());
		String allCount = (String) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].firstChild.textContent;", myElement_All);
		// System.out.println(allCount);
		test_steps.add("Verified Status :" + allCount);
		float amountAll = Float.parseFloat(allCount);
		assertEquals(sum, amountAll, "Failed : sum Mismatched");
		totalAmount_Report = Float.toString(sum);
		WebElement allCheck = driver
				.findElement(By.xpath("//ul[@class='gs-searchFilter']//span[contains(text(),'" + StatusFilter + "')]"));
		allCheck.getText();
		assertEquals(allCheck.getText(), StatusFilter, "Failed : Date missmatched");
		// System.out.println("All text" + allCheck.getText());
		test_steps.add("Verified Status In Status Bar :" + StatusFilter);
		tasklistLogger.info("Verified Status In Status Bar: " + StatusFilter);

		return test_steps;

	}

	ArrayList<String> value_Report = new ArrayList<String>();
	ArrayList<String> value = new ArrayList<String>();

	public ArrayList<String> TaskStatus_BarVerification(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		if (driver.findElement(By.xpath(OR.TaskList_Tasks)).isDisplayed()) {
			assertTrue(driver.findElement(By.xpath(OR.TaskList_Tasks)).isDisplayed(),
					"Failed: TaskList table is Displayed");
			test_steps.add("TaskList Table is Displayed");
			tasklistLogger.info("TaskList table is Displayed");

			Wait.WaitForElement(driver, OR.TaskList_Tasks);
			WebElement scrollElement = driver
					.findElement(By.xpath("//table[contains(@class,'taskList')]/tbody/tr[last()]"));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", scrollElement);
			test_steps.add("TaskList Table is scrolled to the last element");
			tasklistLogger.info("TaskList Table is scrolled to the last element");

			int count = driver.findElements(By.xpath(OR.TaskList_Tasks)).size();
			test_steps.add("Number of Task: " + count + "\n" + "Number of Task: " + count);
			tasklistLogger.info("Number of Task: " + count + "\n" + "Number of Task: " + count);

			WebElement table = driver.findElement(By.xpath("//table[contains(@class,'taskList')]"));

			List<WebElement> rows = table.findElements(By.tagName("tr"));
			List<WebElement> column = table.findElements(By.tagName("td"));

			for (int j = 0; j < column.size(); j++) {
				// System.out.println(column.get(j).getText() + "\n");
				value.add(column.get(j).getText() + "\n");
				System.getProperty("line.separator");
			}
			value.remove(0);
			// System.out.println("Data in tasklist = " + value);
			// value.forEach(t -> System.out.println(t));
			test_steps.add("Data in tasklist = " + value);
			return test_steps;

		}
		return test_steps;
	}

	public ArrayList<String> TaskStatus_BarVerification_AllAssignee(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		if (driver.findElement(By.xpath(OR.Number_of_Tasks)).isDisplayed()) {
			assertTrue(driver.findElement(By.xpath(OR.Number_of_Tasks)).isDisplayed(),
					"Failed: TaskList table is Displayed");
			test_steps.add("TaskList_Report Table is Displayed");
			tasklistLogger.info("TaskList_Report table is Displayed");

			Wait.WaitForElement(driver, OR.Number_of_Tasks);
			WebElement scrollElement = driver
					.findElement(By.xpath("//table[contains(@class, 'table gs-table gs-tdPad10')]/tbody/tr[last()]"));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", scrollElement);
			test_steps.add("TaskList_Report Table is scrolled to the last element");
			tasklistLogger.info("TaskList_Report Table is scrolled to the last element");

			int count_Report = driver.findElements(By.xpath(OR.Number_of_Tasks)).size();
			if (count_Report >= 2) {
				count_Report = count_Report / 2;
			}
			test_steps.add("Number of Task: " + count_Report);
			tasklistLogger.info("Number of Task: " + count_Report);

			WebElement table = driver.findElement(By.xpath("//table[contains(@class, 'table gs-table gs-tdPad10')]"));

			List<WebElement> rows = table.findElements(By.tagName("tr"));
			List<WebElement> column = table.findElements(By.tagName("td"));

			// System.out.println(rows.size());

			for (int j = 0; j < column.size(); j++) {
				// System.out.println(column.get(j).getText() + "\n");
				value_Report.add(column.get(j).getText() + "\n");
				System.getProperty("line.separator");

			}

			value_Report.removeIf(str -> str.equals("Remarks:"));
			value_Report.removeIf(str -> str.equals("Full Cleaning Houskeeping"));
			value_Report.removeIf(str -> str.equals("Remarks: Verify All"));
			// System.out.println("Second Function Value = " + value_Report);
			test_steps.add("Data in tasklist_Report = " + value_Report);

			return test_steps;

		}

		return test_steps;
	}

	public ArrayList<String> TaskStatus_BarVerification_ByAssignee(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		if (driver.findElement(By.xpath(OR.Number_of_Tasks)).isDisplayed()) {
			assertTrue(driver.findElement(By.xpath(OR.Number_of_Tasks)).isDisplayed(),
					"Failed: TaskList table is Displayed");
			test_steps.add("TaskList_Report Table is Displayed");
			tasklistLogger.info("TaskList_Report table is Displayed");

			Wait.WaitForElement(driver, OR.Number_of_Tasks);
			WebElement scrollElement = driver
					.findElement(By.xpath("//table[contains(@class, 'table gs-table gs-tdPad10')]/tbody/tr[last()]"));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", scrollElement);
			test_steps.add("TaskList_Report Table is scrolled to the last element");
			tasklistLogger.info("TaskList_Report Table is scrolled to the last element");

			int count_Report = driver.findElements(By.xpath(OR.Number_of_Tasks)).size();
			if (count_Report >= 2) {
				count_Report = count_Report / 2;
			}
			test_steps.add("Number of Task: " + count_Report);
			tasklistLogger.info("Number of Task: " + count_Report);

			WebElement table = driver.findElement(By.xpath("//table[contains(@class, 'table gs-table gs-tdPad10')]"));

			List<WebElement> rows = table.findElements(By.tagName("tr"));
			List<WebElement> column = table.findElements(By.tagName("td"));

			// System.out.println(rows.size());

			for (int j = 0; j < column.size(); j++) {
				// System.out.println(column.get(j).getText());
				value_Report.add(column.get(j).getText());
			}

			value_Report.removeIf(str -> str.equals("Remarks:"));
			value_Report.removeIf(str -> str.equals("Full Cleaning Houskeeping"));
			value_Report.removeIf(str -> str.equals("Remarks: Verify All"));
			// System.out.println("Second Function Value = " + value_Report);
			test_steps.add("Data in tasklist_Report = " + value_Report);

			return test_steps;

		}

		return test_steps;
	}

	public boolean isBothArraysEqual(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		// System.out.println("value Count = " + value.size());
		// System.out.println("valueReport Count = " + value_Report.size());
		//
		// System.out.println("valueData = " + value);
		// System.out.println("reportData = " + value_Report);

		boolean isEqual = value.containsAll(value_Report);
		if (isEqual == true) {
			System.out.println("Data Matched");
			return true;

		} else {
			System.out.println("Data Mismatched");
			return false;
		}
	}

	public boolean isBothStringsEqual(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {

		// System.out.println("StatusBarCount = " + statusCheck);
		// System.out.println("StatusBarCount_Report = " + statusCheck_Report);

		boolean isEqual = statusCheck.contains(statusCheck_Report);
		if (isEqual == true) {
			System.out.println("Data Matched");
			return true;

		} else {
			System.out.println("Data Mismatched");
			return false;
		}
	}

	public boolean isBothStringCountIsEqual(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {

		// System.out.println("StatusBarCount = " + totalAmount);
		// System.out.println("StatusBarCount_Report = " + totalAmount_Report);

		boolean isEqual = totalAmount.contains(totalAmount_Report);
		if (isEqual == true) {
			System.out.println("Data Matched");
			return true;

		} else {
			System.out.println("Data Mismatched");
			return false;
		}
	}

	public ArrayList<String> closeWindow(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		Wait.wait2Second();
		driver.close();
		tasklistLogger.info("Close Tab");
		driver.switchTo().window(tabs2.get(0));
		Wait.explicit_wait_xpath(OR.TaskListPage, driver);
		test_steps.add("Back To Task List Page");
		tasklistLogger.info("Task List Page");
		return test_steps;

	}

	public void SelectCustomRange(WebDriver driver, String CompleteYear, String CompleteMonth, String FromDate,
			String ToDate) throws InterruptedException {

		String MonthYear = CompleteYear + " " + CompleteMonth;
		String MonthYearPlaceHolder = "//th[contains(@class,'month')]";
		List<WebElement> LeftButton = driver.findElements(By.xpath("//th[@class='next availables']"));
		List<WebElement> RightButton = driver.findElements(By.xpath("//th[@class='prev available']"));
		List<WebElement> FromDateEle = driver
				.findElements(By.xpath("//td[contains(@class,'available') and contains(text(), '" + FromDate + "')]"));
		Wait.WaitForElement(driver, MonthYearPlaceHolder);
		List<WebElement> MonthYearPlaceHolderEle = driver.findElements(By.xpath(MonthYearPlaceHolder));
		// Select Month Year From Calendar Header
		if (!MonthYearPlaceHolderEle.get(0).getText().contains(MonthYear)) {
			MonthYearPlaceHolderEle.get(0).click();
		}
		// Select Dates
		FromDateEle.get(0).click();
		Wait.wait3Second();
		List<WebElement> ToDateEle = driver
				.findElements(By.xpath("//td[contains(@class,'available') and contains(text(), '" + ToDate + "')]"));
		ToDateEle.get(0).click();

		// Java Script
		// Wait.wait2Second();
		// String path = "//span[contains(@class,'startDate')]";
		// WebElement Temp = driver.findElement(By.xpath(path));
		// Temp.click();
		// String jScript = "var myList =
		// document.getElementsByClassName(\"startDate mobStartDate empty
		// focus\");"
		// + "myList[0].innerHTML=\"Apr 16, 2020\";";
		// Wait.wait2Second();
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].innerHTML='" + "Apr 16, 2020" + "'",
		// Temp);
		// JavascriptExecutor executor = (JavascriptExecutor) driver;
		// executor.executeScript(jScript);

	}

	public ArrayList<String> AllAssigneesVerification_Std(WebDriver driver, String Assingees,
			ArrayList<String> test_steps) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.Report_AllAssignees, driver);
		TaskList.Report_AllAssignees.click();
		Wait.wait3Second();
		test_steps.add("Click All Assignees");
		tasklistLogger.info("Click All Assignees");
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		tasklistLogger.info("Switch to report tab");
		Wait.wait2Second();
		assertTrue(driver.findElement(By.xpath(OR.TaskListReportPage)).isDisplayed(), "Failed: Task List Report Page");

		test_steps.add("Report: All Assignees ");
		tasklistLogger.info("Report: All Assignees");

		WebElement AssigneeCheck = driver.findElement(By.xpath("//span[contains(text(),'All Assignee')]"));
		AssigneeCheck.getText();
		assertEquals(AssigneeCheck.getText(), Assingees, "Failed : Detail missmatched");
		test_steps.add("Verified Assignee :" + Assingees);
		return test_steps;

	}

	public ArrayList<String> ByAssigneesVerification_Std(WebDriver driver, String Assingees,
			ArrayList<String> test_steps) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.explicit_wait_xpath(OR.Report_ByAssignees, driver);
		TaskList.Report_ByAssignees.click();
		Wait.wait3Second();
		test_steps.add("Click By Assignees");
		tasklistLogger.info("Click By Assignees");
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		tasklistLogger.info("Switch to report tab");
		Wait.wait2Second();
		assertTrue(driver.findElement(By.xpath(OR.TaskListReportPage)).isDisplayed(), "Failed: Task List Report Page");

		test_steps.add("Report: By Assignees ");
		tasklistLogger.info("Report: By Assignees");

		WebElement AssigneeCheck = driver.findElement(By.xpath("//span[contains(text(),'By Assignee')]"));
		AssigneeCheck.getText();
		assertEquals(AssigneeCheck.getText(), Assingees, "Failed : Detail missmatched");
		test_steps.add("Verified Assignee :" + Assingees);
		return test_steps;

	}

	public ArrayList<String> VerifyDateRangeInTaskListReport(WebDriver driver, String dateRange,
			ArrayList<String> test_steps) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		WebElement DatePath = driver.findElement(By.xpath("//label[@class='taskTitle']//span"));
		// System.out.print("*****" + DatePath.getText());
		// System.out.print("*****this" + dateRange);

		assertEquals(DatePath.getText().contains(dateRange), true, "Failed : Date missmatched");
		test_steps.add("Verified Assignee :" + dateRange);
		return test_steps;

	}

	public ArrayList<String> EditAnyTask(WebDriver driver, String category, String Type, String Status, String details,
			ArrayList<String> test_steps) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.wait3Second();
		TaskList.TaskCategory.click();
		Wait.waitUntilPresenceOfElementLocated("//span[text()='" + category + "']", driver);
		driver.findElement(By.xpath("//span[text()='" + category + "']")).click();
		assertTrue(TaskList.TaskCategory.getText().contains(category), "Failed: Task Category");
		tasklistLogger.info("Edited Task Category: " + category);
		test_steps.add("Edited Task Category: " + category);
		TaskList.CategoryType.click();
		Wait.waitUntilPresenceOfElementLocated("//span[text()='" + Type + "']/parent::a", driver);
		driver.findElement(By.xpath("//span[text()='" + Type + "']/parent::a")).click();
		assertTrue(TaskList.CategoryType.getText().contains(Type), "Failed: Task Type");
		tasklistLogger.info(" Edited Task Type: " + Type);
		test_steps.add("Edited Task Type: " + Type);
		String StatusDropdownPath = "(//span[text()='" + Status + "'])[2]";
		TaskList.StatusDrodown.click();
		Wait.wait2Second();
		Wait.waitUntilPresenceOfElementLocated(StatusDropdownPath, driver);
		WebElement StatusDropdownEle = driver.findElement(By.xpath(StatusDropdownPath));
		StatusDropdownEle.click();
		System.out.print("Statusis" + StatusDropdownEle.getText());
		System.out.print("Statusis" + StatusDropdownEle.getAttribute("value"));
		System.out.print("Statusis" + StatusDropdownEle.getAttribute("title"));

		// assertTrue(StatusDropdownEle.getText().contains(Status), "Failed:
		// Task Status");
		tasklistLogger.info("Edited Task Status: " + Status);
		test_steps.add(" Edited Task Status: " + Status);

		String EditedTaskDetails = "//textarea[@name='des']";
		WebElement EditedTaskDetailsEle = driver.findElement(By.xpath(EditedTaskDetails));
		EditedTaskDetailsEle.clear();
		EditedTaskDetailsEle.sendKeys(details);
		tasklistLogger.info("Edited Task Detail: " + details);
		test_steps.add(" Edited Task Detail: " + details);

		String SaveEditedTaskButton = "(//button[text()='Save'])[1]";
		WebElement SaveEditedTaskButtonEle = driver.findElement(By.xpath(SaveEditedTaskButton));
		Utility.ScrollToElement(SaveEditedTaskButtonEle, driver);
		SaveEditedTaskButtonEle.click();
		tasklistLogger.info("Click Save Button");
		test_steps.add("Click Save Button");
		Wait.explicit_wait_visibilityof_webelement_120(TaskList.Toaster_Message, driver);
		assertEquals(TaskList.Toaster_Message.getText(), category + " task has been updated.");
		tasklistLogger.info("Toaster  Message Displayed: " + TaskList.Toaster_Message.getText());
		test_steps.add("Toaster  Message Displayed: " + TaskList.Toaster_Message.getText());
		return test_steps;

	}

	public void SearchTaskList(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.wait5Second();
		WebElement TaskLists = driver.findElement(
				By.xpath("(//div[@class='gs-displayTable']//span[@class='gs-displayTable-cell firstLetterCaps'])[1]"));
		Wait.wait5Second();
		TaskLists.isDisplayed();
		assertTrue(TaskLists.isDisplayed(), "Failed : TaskList isn't displayed");
		RemoveSearch(driver);

	}

	public ArrayList<String> getFirstTaskDetailsInTaskList(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		ArrayList<String> taskDetailsInTaskList = new ArrayList<String>();
		String TaskIcon = "((//table[contains(@class,'taskList')]//tr)[2]//span[contains(@class,'icon')])[1]";
		String TaskType = "((//table[contains(@class,'taskList')]//tr)[2]//span[contains(@class,'firstLetterCaps')])[1]";
		String TaskLocation = "(//table[contains(@class,'taskList')]//tr)[2]//td[3][contains(@class,'cursorP')]";
		String TaskFor = "(//table[contains(@class,'taskList')]//tr)[2]//td[4][contains(@class,'cursorP')]";
		String TaskDue = "(//table[contains(@class,'taskList')]//tr)[2]//td[6][contains(@class,'cursorP')]";
		String TaskStatus = "(//table[contains(@class,'taskList')]//tr)[2]//td[9]//label[contains(@class,'gs-labelNormal')]";
		WebElement TaskIconElement = driver.findElement(By.xpath(TaskIcon));
		WebElement TaskTypeElement = driver.findElement(By.xpath(TaskType));
		WebElement TaskLocationElement = driver.findElement(By.xpath(TaskLocation));
		WebElement TaskForElement = driver.findElement(By.xpath(TaskFor));
		WebElement TaskDueElement = driver.findElement(By.xpath(TaskDue));
		WebElement TaskStatusElement = driver.findElement(By.xpath(TaskStatus));
		String DueDate = TaskDueElement.getText();
		System.out.print(DueDate);

		String DueDatePart1 = DueDate.split("\\(")[1];
		String DueDatePart0 = DueDate.split("\\(")[0];
		DueDatePart0 = DueDatePart0.split(" ")[0] + " " + DueDatePart0.split(" ")[1];
		DueDate = DueDatePart0 + " (" + DueDatePart1;
		System.out.print(DueDate);
		// taskDetailsInTaskList.add(TaskIconElement.getAttribute("class"));
		taskDetailsInTaskList.add(TaskTypeElement.getText());
		taskDetailsInTaskList.add(TaskLocationElement.getText());
		taskDetailsInTaskList.add(TaskForElement.getText());
		taskDetailsInTaskList.add(DueDate);
		taskDetailsInTaskList.add(TaskStatusElement.getText());
		System.out.print("Details:" + taskDetailsInTaskList);
		return taskDetailsInTaskList;
	}

	public ArrayList<String> getTaskDetailsInBulkDeletePopUp(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		ArrayList<String> taskDetailsInBulkDeletePopUp = new ArrayList<String>();

		String TaskIcon = "(//div[@id='bulkDeleteModal']//table//tr)[2]//div//span[contains(@class,'icon gs-fontSize-28 gs-icon-frontdesk')]";
		String TaskType = "(//div[@id='bulkDeleteModal']//table//tr)[2]//div//span[contains(@class,'firstLetterCaps')]";
		String TaskLocation = "(//div[@id='bulkDeleteModal']//table//tr)[2]//td[2]";
		String TaskFor = "(//div[@id='bulkDeleteModal']//table//tr)[2]//td[3]";
		String TaskDue = "(//div[@id='bulkDeleteModal']//table//tr)[2]//td[4]";
		String TaskStatus = "(//div[@id='bulkDeleteModal']//table//tr)[2]//td[5]//div";
		WebElement TaskIconElement = driver.findElement(By.xpath(TaskIcon));
		WebElement TaskTypeElement = driver.findElement(By.xpath(TaskType));
		WebElement TaskLocationElement = driver.findElement(By.xpath(TaskLocation));
		WebElement TaskForElement = driver.findElement(By.xpath(TaskFor));
		WebElement TaskDueElement = driver.findElement(By.xpath(TaskDue));
		WebElement TaskStatusElement = driver.findElement(By.xpath(TaskStatus));
		// taskDetailsInBulkDeletePopUp.add(TaskIconElement.getAttribute("class"));
		taskDetailsInBulkDeletePopUp.add(TaskTypeElement.getText());
		taskDetailsInBulkDeletePopUp.add(TaskLocationElement.getText());
		taskDetailsInBulkDeletePopUp.add(TaskForElement.getText());
		taskDetailsInBulkDeletePopUp.add(TaskDueElement.getText());
		taskDetailsInBulkDeletePopUp.add(TaskStatusElement.getText());
		System.out.print("Details:" + taskDetailsInBulkDeletePopUp);
		return taskDetailsInBulkDeletePopUp;
	}

	public String GetAllCount(WebDriver driver, boolean ClickTab) {
		if (ClickTab)
			driver.findElement(By.xpath("//ul[@class='gs-searchFilter mob-divid-into-5']//li[@class='all']")).click();
		WebElement myElement_All = driver.findElement(By.cssSelector("li.all.active span"));
		String allCount = (String) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].firstChild.textContent;", myElement_All);
		System.out.println(allCount);
		test_steps.add("Verified Status :" + allCount);
		return allCount;
	}

	public String GetFirstTaskListRoomNumber(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.wait3Second();
		String TaskListRoomNumber = driver.findElement(By.xpath("//h4[@id='myModalLabel']")).getAttribute("value");
		return TaskListRoomNumber;
	}

	public String GetActivityLogCount(WebDriver driver) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.wait3Second();
		String ActivityLogCount = driver.findElement(By.xpath("//a[@data-target='#activityLog']")).getText();
		ActivityLogCount = ActivityLogCount.split("\\(")[1].split("\\)")[0];
		System.out.print(ActivityLogCount);
		TaskList.TaskList_InfoPanelCloseButton.click();
		Wait.wait2Second();
		return ActivityLogCount;
	}

	public void createNewTaskWithRoomOfThatRoomClass(WebDriver driver, String task, String category, String categoryType,
			String details, String remarks, String assignee, String ResNumber, String RoomClassName)
			throws InterruptedException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.SelectTask, driver);
		Wait.explicit_wait_visibilityof_webelement(TaskList.SelectTask, driver);
		TaskList.SelectTask.click();
		Wait.waitUntilPresenceOfElementLocated("//span[text()='" + task + "']", driver);
		driver.findElement(By.xpath("//span[text()='" + task + "']")).click();
		assertTrue(TaskList.SelectTask.getText().contains(task), "Failed: Select Task");
		tasklistLogger.info("Task: " + task);
		Wait.wait2Second();
		TaskList.TypeSearch.sendKeys(ResNumber);
		Wait.wait5Second();
		driver.findElement(By.xpath("//*[contains (text(), '" + RoomClassName + "')]")).click();
		tasklistLogger.info("Search : " + ResNumber);
		TaskList.TaskCategory.click();
		Wait.waitUntilPresenceOfElementLocated("//span[text()='" + category + "']", driver);
		driver.findElement(By.xpath("//span[text()='" + category + "']")).click();
		assertTrue(TaskList.TaskCategory.getText().contains(category), "Failed: Task Category");
		tasklistLogger.info("Task Category: " + category);
		TaskList.CategoryType.click();
		Wait.wait10Second();
		Wait.waitUntilPresenceOfElementLocated(
				"//select[@name='categoryType']//following-sibling::div/div//span[text()='" + categoryType + "']",
				driver);
		driver.findElement(By.xpath(
				"//select[@name='categoryType']//following-sibling::div/div//span[text()='" + categoryType + "']"))
				.click();
		assertTrue(TaskList.CategoryType.getText().contains(categoryType), "Failed: Category Type ");
		tasklistLogger.info("Category Type: " + categoryType);
		TaskList.Task_Detail.sendKeys(details);
		tasklistLogger.info("Task Detail: " + details);
		TaskList.Task_Remarks.sendKeys(remarks);
		tasklistLogger.info("Task Remarks: " + remarks);
		TaskList.Task_Assignee.sendKeys(assignee);
		tasklistLogger.info("Task Assignee: " + assignee);
		TaskList.Task_Save.click();
		tasklistLogger.info("Click Save");

	}

	public void searchTaskByRoomNo(WebDriver driver, String RoomNO) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.WaitForElement(driver, OR.SearchTaskInput);
		TaskList.SearchTaskInput.clear();
		TaskList.SearchTaskInput.sendKeys(RoomNO);
		TaskList.SearchTaskButton.click();
		Wait.wait5Second();

	}

	
	public ArrayList<String> changeTaskStatus(WebDriver driver, String TaskStatus, String ChangeStatus,
			ArrayList<String> test_steps) throws InterruptedException {
		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		if (TaskStatus.equals("To Do") || TaskStatus.equalsIgnoreCase("to do")) {
			TaskList.TaskList_ToDoStatusDropDownBOx.click();
		} else if (TaskStatus.equals("Done") || TaskStatus.equalsIgnoreCase("done")) {
			TaskList.TaskList_DoneStatusDropDownBOx.click();
		} else if (TaskStatus.equals("Inspection") || TaskStatus.equalsIgnoreCase("inspection")) {
			TaskList.TaskList_InspectionStatusDropDownBOx.click();
		}
		TaskList.TaskList_StatusDropDownBoxFilterOption.click();
		Wait.wait5Second();
		TaskList.TaskList_StatusDropDownBOxButton.click();
		Wait.wait1Second();
		TaskList.TaskList_StatusDropDownBOxButton.click();
		Wait.wait5Second();
		String path="//span[@class='text'][contains(text(),'" + ChangeStatus + "')]";
		Wait.waitUntilPresenceOfElementLocated(path, driver);
		driver.findElement(By.xpath(path)).click();
		//Wait.wait5Second();
		Wait.waitForElementToBeClickable(By.xpath(OR.TaskType_StatusDropDown_SaveBtn), driver, 5);
		TaskList.TaskType_StatusDropDown_SaveBtn.click();
		test_steps.add("Change Task Status  " + ChangeStatus);
		tasklistLogger.info("Change Task Status " + ChangeStatus);

		return test_steps;
	}

	public void clickQuickStatesAndUpdateTaskStatus(WebDriver driver, String TaskStatus, String ChangeStatus,
			String RoomNumber, ArrayList<String> test_steps) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_TaskList taskstatus = new WebElements_TaskList(driver);
		if (TaskStatus.equals("To Do") || TaskStatus.equalsIgnoreCase("to do")) {
			Wait.WaitForElement(driver, OR.TaskList_TODO);
			taskstatus.TaskList_TODO.click();
			Wait.wait5Second();
			test_steps.add("Click on To Do Quick Stats");
			tasklistLogger.info("Click on To Do Quick Stats");
			searchTaskByRoomNo(driver, RoomNumber);
			Wait.wait5Second();
			test_steps.add("Search task");
			tasklistLogger.info("Search task");
			changeTaskStatus(driver, TaskStatus, ChangeStatus, test_steps);

		} else if (TaskStatus.equals("Done") || TaskStatus.equalsIgnoreCase("done")) {
			Wait.WaitForElement(driver, OR.TaskList_Done);
			taskstatus.TaskList_Done.click();
			Wait.wait5Second();
			test_steps.add("Click on Done Quick Stats");
			tasklistLogger.info("Click on Done Quick Stats");
			searchTaskByRoomNo(driver, RoomNumber);
			test_steps.add("Search task");
			tasklistLogger.info("Search task");
			Wait.wait5Second();
			changeTaskStatus(driver, TaskStatus, ChangeStatus, test_steps);

		}

		else if (TaskStatus.equals("Inspection") || TaskStatus.equalsIgnoreCase("inspection")) {
			Wait.WaitForElement(driver, OR.TaskList_Inspection);
			taskstatus.TaskList_Inspection.click();
			Wait.wait5Second();
			test_steps.add("Click on Inspection Quick Stats");
			tasklistLogger.info("Click on Inspection Quick Stats");
			searchTaskByRoomNo(driver, RoomNumber);
			Wait.wait5Second();
			test_steps.add("Search task");
			tasklistLogger.info("Search task");
			changeTaskStatus(driver, TaskStatus, ChangeStatus, test_steps);

		}
	}

	public void VerifyLoading(WebDriver driver) throws InterruptedException {
		WebElementsRoomStatus elementsRoomStatus = new WebElementsRoomStatus(driver);
		try {
			int count = 0;
			while (count <= 60) {
				if (!elementsRoomStatus.Loading.isDisplayed()) {
					break;
				}
				count++;
				Wait.wait2Second();
				System.out.println("in a loop: " + count);
			}
			// Wait.waitForElementToBeGone(driver,elementsRoomStatus.Loading,
			// 10);
		} catch (Exception e) {
			System.out.println("in catch");
			// Wait.wait1Second();
		}
	}

	public ArrayList<String> ClickTaskForDropDown(WebDriver driver) {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		ArrayList<String> TestSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.TaskForDropdown);
		Wait.waitForElementToBeVisibile(By.xpath(OR.TaskForDropdown), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.TaskForDropdown), driver);
		TaskList.TaskForDropdown.click();
		TestSteps.add("Click on task for dropdown");
		return TestSteps;

	}
	public ArrayList<String> SelectTaskFor(WebDriver driver, String TaskFor) throws InterruptedException {

		ArrayList<String> TestSteps = new ArrayList<>();
		String SelectTaskForPath = "//div[contains(@class,'daterangepicker')]//ul//li[text()='" + TaskFor + "']";
		tasklistLogger.info("SelectTaskForPath: " + SelectTaskForPath);
		Wait.WaitForElement(driver, SelectTaskForPath);
		Wait.waitForElementToBeVisibile(By.xpath(SelectTaskForPath), driver);
		Wait.waitForElementToBeClickable(By.xpath(SelectTaskForPath), driver);
		WebElement elementTaskFor = driver.findElement(By.xpath(SelectTaskForPath));
		elementTaskFor.click();
		test_steps.add("Selct task for as " + TaskFor);
		return TestSteps;

	}
	public void checkedTasks(WebDriver driver, String TaskForName, boolean isAssingee, String AssigneeName)
			throws InterruptedException {
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		jse.executeScript("window.scrollBy(0,-100)");
		
		String Path = "";
		
			Path = "//td[contains(text(),'" + TaskForName + "')]//..//td//label[text()='" + AssigneeName
					+ "'  or text()='Unassigned']//..//..//..//td//span[contains(@class,'childCheckbox')]";

		tasklistLogger.info("Task Name : " + Path);
		
		try {
			Wait.WaitForElement(driver, Path);
			Wait.waitForElementToBeVisibile(By.xpath(Path), driver);
			Wait.waitForElementToBeClickable(By.xpath(Path), driver);

			List<WebElement> elementCheckbox = driver.findElements(By.xpath(Path));
			Utility.ScrollToElement(elementCheckbox.get(0), driver);

			System.out.println("elementCheckbox: "+elementCheckbox.size());
			for (int i = 0; i < elementCheckbox.size(); i++) {
				System.out.println("i: "+i);
				Utility.ScrollToElement(elementCheckbox.get(i), driver);
				elementCheckbox.get(i).click();
				Wait.wait1Second();

			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void deleteTask(WebDriver driver) throws InterruptedException {

		WebElements_TaskList taskList = new WebElements_TaskList(driver);
		taskList.DeleteTask_Button.click();
		Wait.wait1Second();
		Wait.WaitForElement(driver, OR.TaskDelete_ProcessButton);
		Wait.waitForElementToBeClickable(By.xpath(OR.TaskDelete_ProcessButton), driver);
		taskList.TaskDelete_ProcessButton.click();
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		Wait.explicit_wait_visibilityof_webelement_120(taskList.Toaster_Message, driver);
		System.out.println(taskList.Toaster_Message.getText());
		taskList.ToastCloseButton.click();

	}

	public ArrayList<String> CreateTask3(WebDriver driver, String TaskFor, String category, String categoryType,
			String details, String Reservation_Account_Number, boolean IsAssignee, String AssigneeName, int days)
			throws InterruptedException, ParseException {

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		ArrayList<String> TestSteps = new ArrayList<>();
		// Select task for like reservation, account et
		Wait.WaitForElement(driver, OR.SelectTask);
		Wait.waitForElementToBeVisibile(By.xpath(OR.SelectTask), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.SelectTask), driver);
		TaskList.SelectTask.click();

		Wait.waitUntilPresenceOfElementLocated("//span[text()='" + TaskFor + "']", driver);
		driver.findElement(By.xpath("//span[text()='" + TaskFor + "']")).click();
		assertTrue(TaskList.SelectTask.getText().contains(TaskFor), "Failed: Select To Task");
		tasklistLogger.info("Task: " + TaskFor);
		Wait.wait2Second();

		// search reservation number, account number
		Wait.WaitForElement(driver, OR.TypeSearch);
		TaskList.TypeSearch.sendKeys(Reservation_Account_Number);
		Wait.wait3Second();
		String pathSearchFor = "//*[contains (text(), '" + Reservation_Account_Number + "')]";

		// Wait.wait2Second();
		Wait.WaitForElement(driver, pathSearchFor);
		Wait.waitForElementToBeVisibile(By.xpath(pathSearchFor), driver);
		Wait.waitForElementToBeClickable(By.xpath(pathSearchFor), driver);
		WebElement searchResult = driver
				.findElement(By.xpath("//*[contains (text(), '" + Reservation_Account_Number + "')]"));
		searchResult.click();

		TaskList.TaskCategory.click();
		Wait.wait1Second();
		Wait.waitUntilPresenceOfElementLocated("//span[text()='" + category + "']", driver);
		driver.findElement(By.xpath("//span[text()='" + category + "']")).click();
		assertTrue(TaskList.TaskCategory.getText().contains(category), "Failed: Task Category");
		tasklistLogger.info("Task Category: " + category);

		TaskList.CategoryType.click();
		Wait.wait1Second();
		Wait.waitUntilPresenceOfElementLocated(
				"//select[@name='categoryType']//following-sibling::div/div//span[text()='" + categoryType + "']",
				driver);
		driver.findElement(By.xpath(
				"//select[@name='categoryType']//following-sibling::div/div//span[text()='" + categoryType + "']"))
				.click();
		assertTrue(TaskList.CategoryType.getText().contains(categoryType), "Failed: Category Type ");
		tasklistLogger.info("Category Type: " + categoryType);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].removeAttribute('readonly','readonly')", TaskList.taskDueOn);
		Wait.wait2Second();
		TaskList.taskDueOn.clear();
		String date = ESTTimeZone.DateFormateForLineItem(days);
		TaskList.taskDueOn.sendKeys(date);
		Wait.wait2Second();
		test_steps.add("Select date: " + date);

		TaskList.Task_Detail.click();
		TaskList.Task_Detail.sendKeys(details);
		tasklistLogger.info("Task Detail: " + details);
		TaskList.Task_Remarks.sendKeys(details);

		if (IsAssignee) {

			TaskList.Task_Assignee.sendKeys(AssigneeName);
			TestSteps.add("Tas assignee name: " + AssigneeName);
			String pathAssignee = "//label[text()='Assignee']/..//div/ul/li/div";

			Wait.wait2Second();
			Wait.WaitForElement(driver, pathAssignee);
			Wait.waitForElementToBeVisibile(By.xpath(pathAssignee), driver);
			Wait.waitForElementToBeClickable(By.xpath(pathAssignee), driver);
			WebElement SearchAssigne = driver.findElement(By.xpath(pathAssignee));
			SearchAssigne.click();
		} else {
			TestSteps.add("Unassigned task");
		}

		TaskList.Task_Save.click();
		tasklistLogger.info("Click Save");
		/*
		 * Wait.explicit_wait_visibilityof_webelement_120(TaskList.
		 * Toaster_Message, driver);
		 * System.out.println(TaskList.Toaster_Message.getText());
		 * TaskList.ToastCloseButton.click();
		 */
		// VerifyLoading(driver);
		return TestSteps;

	}
	public String GetTaskImage(WebDriver driver, String TaskForName, boolean isAssingee, String AssigneeName)
			throws InterruptedException {
		String Path = "";
		VerifyLoading(driver);
		if (isAssingee) {
			Path = "(//td[text()='" + TaskForName + "']//..//td//div//label[text()='" + AssigneeName
					+ "']//..//..//..//td//span//span//span)";

		} else {
			Path = "//td[text()='" + TaskForName
					+ "']//..//td//div//label[text()='Unassigned']//..//..//..//td//span//span//span";

		}

		tasklistLogger.info("ImagePagt : " + Path);
		Wait.WaitForElement(driver, Path);
		Wait.waitForElementToBeVisibile(By.xpath(Path), driver);
		Wait.waitForElementToBeClickable(By.xpath(Path), driver);
		WebElement ElementImage = driver.findElement(By.xpath(Path));
		Utility.ScrollToElement(ElementImage, driver);
		String Image = ElementImage.getAttribute("class");
		/*
		 * String[] strSplit = Image.split(" ");
		 * System.out.println(strSplit[0]); System.out.println(strSplit[1]);
		 */
		return Image;

	}
	public String GetTaskFor(WebDriver driver, String TaskForName, boolean isAssingee, String AssigneeName)
			throws InterruptedException {
		String Path = "";
		if (isAssingee) {
			Path = "(//td[text()='" + TaskForName + "']//..//td//div//label[text()='" + AssigneeName
					+ "']//..//..//..//td)[4]";

		} else {
			Path = "(//td[text()='" + TaskForName + "']//..//td//div//label[text()='Unassigned']//..//..//..//td)[4]";

		}

		tasklistLogger.info("Task Name : " + Path);
		WebElement ElementImage = driver.findElement(By.xpath(Path));
		return ElementImage.getText();

	}

	public String GetTaskOccupancy(WebDriver driver, String TaskForName, boolean isAssingee, String AssigneeName)
			throws InterruptedException {
		String Path = "";
		if (isAssingee) {
			Path = "(//td[text()='" + TaskForName + "']//..//td//div//label[text()='" + AssigneeName
					+ "']//..//..//..//td)[5]";

		} else {
			Path = "(//td[text()='" + TaskForName + "']//..//td//div//label[text()='Unassigned']//..//..//..//td)[5]";

		}

		tasklistLogger.info("Task Occupancy : " + Path);
		WebElement ElementImage = driver.findElement(By.xpath(Path));
		return ElementImage.getText();

	}

	public String GetTaskDueOn(WebDriver driver, String TaskForName, boolean isAssingee, String AssigneeName)
			throws InterruptedException {
		String Path = "";
		if (isAssingee) {
			Path = "(//td[text()='" + TaskForName + "']//..//td//div//label[text()='" + AssigneeName
					+ "']//..//..//..//td)[6]";

		} else {
			Path = "(//td[text()='" + TaskForName + "']//..//td//div//label[text()='Unassigned']//..//..//..//td)[6]";

		}

		tasklistLogger.info("Task DueOn : " + Path);
		WebElement elementDueOn = driver.findElement(By.xpath(Path));
		// String[] date = elementDueOn.getText().split("(");
		// String getDate = date[0].trim();
		return elementDueOn.getText();

	}

	public String GetTaskZone(WebDriver driver, String TaskForName, boolean isAssingee, String AssigneeName)
			throws InterruptedException {
		String Path = "";
		if (isAssingee) {
			Path = "(//td[text()='" + TaskForName + "']//..//td//div//label[text()='" + AssigneeName
					+ "']//..//..//..//td)[8]";

		} else {
			Path = "(//td[text()='" + TaskForName + "']//..//td//div//label[text()='Unassigned']//..//..//..//td)[8]";

		}

		tasklistLogger.info("Task Zone : " + Path);
		WebElement ElementImage = driver.findElement(By.xpath(Path));
		return ElementImage.getText();

	}

	public String GetTaskStatus(WebDriver driver, String TaskForName, boolean isAssingee, String AssigneeName)
			throws InterruptedException {
		//String Path = "";
		//if (isAssingee) {
		String Path = "(//td[text()='" + TaskForName + "']//..//td//div//label[text()='"+AssigneeName+"' or text()='Unassigned']//..//..//..//td)[9]//label";
/*
		} else {
			Path = "(//td[text()='" + TaskForName
					+ "']//..//td//div//label[text()='Unassigned']//..//..//..//td)[9]//label";

		}*/

		tasklistLogger.info("Task Zone : " + Path);
		Wait.WaitForElement(driver, Path);
		WebElement ElementImage = driver.findElement(By.xpath(Path));
		return ElementImage.getText();

	}
	public ArrayList<String> SelectReports(WebDriver driver, String ReportsType) throws InterruptedException {

		WebElements_TaskList taskList = new WebElements_TaskList(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.clickOnTaskReports);
		Wait.waitForElementToBeVisibile(By.xpath(OR.clickOnTaskReports), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.clickOnTaskReports), driver);
		taskList.clickOnTaskReports.click();
		tasklistLogger.info("Click on reports");
		testSteps.add("Click on reports");

		String reportTypePath = "//div[contains(@class,'task-actions')]//a[contains(text(),'" + ReportsType + "')]";
		Wait.WaitForElement(driver, reportTypePath);
		testSteps.add("After click" + ReportsType);

		Wait.waitForElementToBeVisibile(By.xpath(reportTypePath), driver);
		Wait.waitForElementToBeClickable(By.xpath(reportTypePath), driver);
		WebElement element = driver.findElement(By.xpath(reportTypePath));
		element.click();
		testSteps.add("Selet reports type as " + ReportsType);

		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		tasklistLogger.info("Switch to report tab");
		Wait.WaitForElement(driver, OR.tasListReportspage);
		Wait.waitForElementToBeVisibile(By.xpath(OR.tasListReportspage), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.tasListReportspage), driver);
		return testSteps;
	}

	public String getTaskImageInReport(WebDriver driver, String TaskForName, boolean isAssingee, String AssigneeName)
			throws InterruptedException {
		String Path = "";
		if (isAssingee) {
			Path = "//td[contains(text(),'" + TaskForName + "')]//..//td[contains(text(),'" + AssigneeName
					+ "')]//..//td//span//span//span";

		} else {
			Path = "//td[contains(text()'" + TaskForName
					+ "')]//..//td[contains(text(),'Unassigned')]//..//td//span//span//span";

		}

		tasklistLogger.info("ImagePagt : " + Path);
		Wait.WaitForElement(driver, Path);
		Wait.waitForElementToBeVisibile(By.xpath(Path), driver);
		Wait.waitForElementToBeClickable(By.xpath(Path), driver);
		WebElement ElementImage = driver.findElement(By.xpath(Path));

		String Image = ElementImage.getAttribute("class");
		return Image;

	}

	public String getTaskTypeInReport(WebDriver driver, String TaskForName, boolean isAssingee, String AssigneeName)
			throws InterruptedException {
		String Path = "";
		if (isAssingee) {
			Path = "//td[contains(text(),'" + TaskForName + "')]//..//td[contains(text(),'" + AssigneeName
					+ "')]//..//td//span//span[contains(@class,'d-block')]";

		} else {
			Path = "//td[contains(text(),'" + TaskForName
					+ "')]//..//td[contains(text(),'Unassigned')]//..//td//span//span[contains(@class,'d-block')]";

		}

		tasklistLogger.info("Task Name : " + Path);
		Wait.WaitForElement(driver, Path);
		Wait.waitForElementToBeVisibile(By.xpath(Path), driver);
		Wait.waitForElementToBeClickable(By.xpath(Path), driver);
		WebElement ElementImage = driver.findElement(By.xpath(Path));
		return ElementImage.getText();

	}

	public String getTaskForInReport(WebDriver driver, String TaskForName, boolean isAssingee, String AssigneeName)
			throws InterruptedException {
		String Path = "";
		if (isAssingee) {
			Path = "(//td[contains(text(),'" + TaskForName + "')]//..//td[contains(text(),'" + AssigneeName
					+ "')]//..//td)[3]";

		} else {
			Path = "(//td[contains(text(),'" + TaskForName + "')]//..//td[contains(text(),'Unassigned')]//..//td)[3]";

		}

		tasklistLogger.info("Task Name : " + Path);
		WebElement ElementImage = driver.findElement(By.xpath(Path));
		return ElementImage.getText();

	}

	public String getTaskOccupancyInReprts(WebDriver driver, String TaskForName, boolean isAssingee,
			String AssigneeName) throws InterruptedException {
		String Path = "";
		if (isAssingee) {
			Path = "(//td[contains(text(),'" + TaskForName + "')]//..//td[contains(text(),'" + AssigneeName
					+ "')]//..//td)[4]//span";

		} else {
			Path = "(//td[contains(text(),'" + TaskForName
					+ "')]//..//td[contains(text(),'Unassigned')]//..//td)[4]//span";

		}

		tasklistLogger.info("Task Occupancy : " + Path);
		WebElement ElementImage = driver.findElement(By.xpath(Path));
		return ElementImage.getText();

	}

	public String getTaskDueOnInReport(WebDriver driver, String TaskForName, boolean isAssingee, String AssigneeName)
			throws InterruptedException {
		String Path = "";
		if (isAssingee) {
			Path = "(//td[contains(text(),'" + TaskForName + "')]//..//td[contains(text(),'" + AssigneeName
					+ "')]//..//td)[5]";

		} else {
			Path = "(//td[contains(text(),'" + TaskForName + "')]//..//td[contains(text(),'Unassigned')]//..//td)[5]";

		}

		tasklistLogger.info("Task DueOn : " + Path);
		WebElement elementDueOn = driver.findElement(By.xpath(Path));
		// String[] date = elementDueOn.getText().split("(");
		// String getDate = date[0].trim();
		return elementDueOn.getText();

	}

	public String getTaskZoneInReports(WebDriver driver, String TaskForName, boolean isAssingee, String AssigneeName)
			throws InterruptedException {
		String Path = "";
		if (isAssingee) {
			Path = "(//td[contains(text(),'" + TaskForName + "')]//..//td[contains(text(),'" + AssigneeName
					+ "')]//..//td)[7]";

		} else {
			Path = "(//td[contains(text(),'" + TaskForName + "')]//..//td[contains(text(),'Unassigned')]//..//td)[7]";

		}

		tasklistLogger.info("Task Zone : " + Path);
		WebElement ElementImage = driver.findElement(By.xpath(Path));
		return ElementImage.getText();

	}

	public String GetTaskStatusInReports(WebDriver driver, String TaskForName, boolean isAssingee, String AssigneeName)
			throws InterruptedException {
		String Path = "";
		if (isAssingee) {
			Path = "(//td[contains(text(),'" + TaskForName + "')]//..//td[contains(text(),'" + AssigneeName
					+ "')]//..//td)[8]";

		} else {
			Path = "(//td[contains(text(),'" + TaskForName + "')]//..//td[contains(text(),'Unassigned')]//..//td)[8]";

		}

		tasklistLogger.info("Task Zone : " + Path);
		WebElement ElementImage = driver.findElement(By.xpath(Path));
		return ElementImage.getText();

	}

	public String getAssignedInReports(WebDriver driver, String TaskForName, boolean isAssingee, String AssigneeName)
			throws InterruptedException {
		String Path = "";
		if (isAssingee) {
			Path = "(//td[contains(text(),'" + TaskForName + "')]//..//td[contains(text(),'" + AssigneeName
					+ "')]//..//td)[6]";

		} else {
			Path = "(//td[contains(text(),'" + TaskForName + "')]//..//td[contains(text(),'Unassigned')]//..//td)[6]";

		}

		tasklistLogger.info("Task Zone : " + Path);
		WebElement ElementImage = driver.findElement(By.xpath(Path));
		return ElementImage.getText();

	}

	public String getUnassignedTaskImageInReport(WebDriver driver, String TaskForName, boolean isAssingee,
			String AssigneeName) throws InterruptedException {
		String Path = "";

		Path = "(//td[contains(text(),'" + TaskForName + "')]//..//td//span//span//span)[1]";

		tasklistLogger.info("ImagePagt : " + Path);
		Wait.WaitForElement(driver, Path);
		Wait.waitForElementToBeVisibile(By.xpath(Path), driver);
		Wait.waitForElementToBeClickable(By.xpath(Path), driver);
		WebElement ElementImage = driver.findElement(By.xpath(Path));

		String Image = ElementImage.getAttribute("class");
		return Image;

	}

	public String getUnassignedTaskTypeInReport(WebDriver driver, String TaskForName, boolean isAssingee,
			String AssigneeName) throws InterruptedException {
		String Path = "";

		Path = "(//td[contains(text(),'" + TaskForName + "')]//..//td//span//span[contains(@class,'d-block')])[1]";

		tasklistLogger.info("Task Name : " + Path);
		Wait.WaitForElement(driver, Path);
		Wait.waitForElementToBeVisibile(By.xpath(Path), driver);
		Wait.waitForElementToBeClickable(By.xpath(Path), driver);
		WebElement ElementImage = driver.findElement(By.xpath(Path));
		return ElementImage.getText();

	}

	public String getUnassignedTaskForInReport(WebDriver driver, String TaskForName, boolean isAssingee,
			String AssigneeName) throws InterruptedException {
		String Path = "";

		Path = "(//td[contains(text(),'" + TaskForName + "')]//..//td)[3]";

		tasklistLogger.info("Task Name : " + Path);
		WebElement ElementImage = driver.findElement(By.xpath(Path));
		return ElementImage.getText();

	}

	public String getUnassignedTaskOccupancyInReprts(WebDriver driver, String TaskForName, boolean isAssingee,
			String AssigneeName) throws InterruptedException {

		String Path = "(//td[contains(text(),'" + TaskForName + "')]//..//td)[4]";
		tasklistLogger.info("Task Name : " + Path);
		WebElement ElementImage = driver.findElement(By.xpath(Path));
		return ElementImage.getText();
	}

	public String getUnassignedTaskDueOnInReport(WebDriver driver, String TaskForName, boolean isAssingee,
			String AssigneeName) throws InterruptedException {
		String Path = "(//td[contains(text(),'" + TaskForName + "')]//..//td)[5]";
		tasklistLogger.info("Task Name : " + Path);
		WebElement ElementImage = driver.findElement(By.xpath(Path));
		return ElementImage.getText();

	}

	public String getUnassignedTaskZoneInReports(WebDriver driver, String TaskForName, boolean isAssingee,
			String AssigneeName) throws InterruptedException {

		String Path = "(//td[contains(text(),'" + TaskForName + "')]//..//td)[6]";
		tasklistLogger.info("Task Name : " + Path);
		WebElement ElementImage = driver.findElement(By.xpath(Path));
		return ElementImage.getText();

	}

	public String getUnassignedTaskStatusInReports(WebDriver driver, String TaskForName, boolean isAssingee,
			String AssigneeName) throws InterruptedException {
		String Path = "(//td[contains(text(),'" + TaskForName + "')]//..//td)[7]";
		tasklistLogger.info("Task Name : " + Path);
		WebElement ElementImage = driver.findElement(By.xpath(Path));
		return ElementImage.getText();
	}

	public void selectCustomRangeEndDate(WebDriver driver, String date) throws InterruptedException {

		WebElements_TaskList taskList = new WebElements_TaskList(driver);
		Wait.wait1Second();
		Wait.WaitForElement(driver, OR.enterCustomRangeEndDate);
		Wait.waitForElementToBeClickable(By.xpath(OR.enterCustomRangeEndDate), driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].innerHTML='" + "" + date + "" + "'", taskList.enterCustomRangeEndDate);

	}

	public void selectCustomRangeEndDateFrompopup(WebDriver driver) throws InterruptedException {

		WebElements_TaskList taskList = new WebElements_TaskList(driver);
		Wait.wait1Second();
		taskList.customRangeTodayDate.click();

	}

	public ArrayList<String> clickOnAddTask(WebDriver driver) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();

		WebElements_TaskList TaskList = new WebElements_TaskList(driver);
		Wait.WaitForElement(driver, OR.ClickOnAddNewTask);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ClickOnAddNewTask), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.ClickOnAddNewTask), driver);
		Utility.ScrollToElement_NoWait(TaskList.AddTask_Button, driver);
		TaskList.clickOnAddNewTask.click();
		tasklistLogger.info("Click AddTask");
		test_steps.add("Click Add Task");

		Wait.WaitForElement(driver, OR.AddTask_Popup);
		Wait.waitForElementToBeVisibile(By.xpath(OR.AddTask_Popup), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.AddTask_Popup), driver);
		assertTrue(TaskList.AddTask_Popup.isDisplayed(), "Add Task Popup is not displayed");
		tasklistLogger.info("Add Task window displayed");
		test_steps.add("Add Task window displayed");

		return test_steps;

	}
}
