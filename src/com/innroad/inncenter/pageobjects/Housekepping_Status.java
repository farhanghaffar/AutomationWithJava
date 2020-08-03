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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.TimeZone;

import javax.imageio.ImageIO;

import com.innroad.inncenter.interfaces.IHousekepping_Status;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.WebElements_Housekeeping_Status;
import com.innroad.inncenter.webelements.WebElements_TaskList;
import com.relevantcodes.extentreports.ExtentTest;

import org.apache.pdfbox.rendering.PDFRenderer;

public class Housekepping_Status implements IHousekepping_Status {

	// select room condition clean/dirty/inspected in table header
	public int Select_Room_Condition_In_Header(WebDriver driver, String roomcondition) throws InterruptedException {
		WebElements_Housekeeping_Status Housekeeping_Status = new WebElements_Housekeeping_Status(driver);
		Wait.explicit_wait_visibilityof_webelement(Housekeeping_Status.Select_Items_Per_Page, driver);
		new Select(Housekeeping_Status.Select_Items_Per_Page).selectByVisibleText("All");
		int size = driver.findElements(By.xpath("//*[@id='MainContent_dgHousekeeping']/tbody/tr")).size();
		if (size > 2) {
			try {
				driver.findElement(By.xpath("//tr[@class='dgHeader']//label[text()='" + roomcondition + "']/../input"))
						.click();
				Wait.wait2Second();
			} catch (Exception e) {
				assertTrue(false);
			}
		}
		return size;
	}

	// select list items per page
	public void Select_Items_Per_Page(WebDriver driver, String itemsperpage) throws InterruptedException {
		WebElements_Housekeeping_Status Housekeeping_Status = new WebElements_Housekeeping_Status(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", Housekeeping_Status.Select_Items_Per_Page);
		new Select(Housekeeping_Status.Select_Items_Per_Page).selectByVisibleText(itemsperpage);
		assertTrue(new Select(Housekeeping_Status.Select_Items_Per_Page).getFirstSelectedOption().getText()
				.contains(itemsperpage), "Failed : " + itemsperpage + " is not selected ");
		Wait.wait2Second();

	}

	// save changes
	public void Save(WebDriver driver) throws InterruptedException {

		WebElements_Housekeeping_Status Housekeeping_Status = new WebElements_Housekeeping_Status(driver);
		Housekeeping_Status.Save_Housekeeping_Status.click();
		Wait.wait2Second();
	}


	public void printAndDownloadPDF(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		WebElements_Housekeeping_Status housekeeping_Status = new WebElements_Housekeeping_Status(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_imgPrintHouseKeepingList']")))
				.click();
		// housekeeping_Status.Housekeeping_PrintButton.click();
		Wait.wait3Second();
		test_steps.add("Succussfully Clicked PrintButton ");
		Utility.app_logs.info("Succussfully Clicked PrintButton");
		// driver.manage().window().maximize();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("ifrmAccountStatement");

		assertTrue(housekeeping_Status.Housekeeping_PDFReport.isDisplayed(), "Report isn't Displayed");
		test_steps.add("PDF Loaded Succussfully");
		Utility.app_logs.info("PDF Loaded Succussfully");
		String dataPath=driver.findElement(By.xpath("//*[@id='viewPDF']")).getAttribute("data");
		driver.get(dataPath);
		Wait.wait5Second();
//		try {
//			downloadpdf(driver);
//		} catch (AWTException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String path="//*[@id='btnImgDownload']";
//		
//		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(path)), driver);
//		
//		new Select(
//				driver.findElement(By.xpath("//*[@id='drpReportType']"))
//				).selectByVisibleText("Export Data To Excel");
////		
//		if(driver.findElement(By.xpath(path)).isDisplayed()) {
//			Utility.app_logs.info("Download btn displayed");
//			test_steps.add("Download btn displayed");
//			Utility.ScrollToElement(driver.findElement(By.xpath(path)), driver);
//			driver.findElement(By.xpath(path)).click();
//			Utility.app_logs.info("File Downloaded");
//			test_steps.add("File Downloaded");
//			Wait.wait10Second();
//
//		}else {
//			assertTrue(false,"Download Btn Not displayed");
//		}
		

		Utility.app_logs.info("File Downloaded");
		test_steps.add("File Downloaded");
	}

	public void VerifyOutOfOrerRoom(WebDriver driver, String RoomClass, String RoomNumber, boolean isExist,
			ArrayList<String> test_steps) throws InterruptedException {

		WebElements_Housekeeping_Status Housekeeping_Status = new WebElements_Housekeeping_Status(driver);
		Wait.explicit_wait_visibilityof_webelement(Housekeeping_Status.HK_SelectRoomClass, driver);
		new Select(Housekeeping_Status.HK_SelectRoomClass).selectByVisibleText(RoomClass);
		test_steps.add("Room Class Selected : " + RoomClass);
		Utility.app_logs.info("Room Class Selected : " + RoomClass);

		if (Housekeeping_Status.Housekeeping_RoomStatus_Vacant.isSelected()) {
			Housekeeping_Status.Housekeeping_RoomStatus_Vacant.click();
		}
		Utility.app_logs.info("Verify Vacant : Unchecked");
		test_steps.add("Verify Vacant : Unchecked");

		if (Housekeeping_Status.Housekeeping_RoomStatus_Occupied.isSelected()) {
			Housekeeping_Status.Housekeeping_RoomStatus_Occupied.click();
		}
		Utility.app_logs.info("Verify Occupied : Unchecked");
		test_steps.add("Verify Occupied : Unchecked");
		if (!Housekeeping_Status.Housekeeping_RoomStatus_OutofOrder.isSelected()) {
			Housekeeping_Status.Housekeeping_RoomStatus_OutofOrder.click();
		}
		Utility.app_logs.info("Verify Out Of Order : Checked");
		test_steps.add("Verify Out Of Order : Checked");

		Housekeeping_Status.HK_GoButton.click();
		test_steps.add("Go Button Clicked");
		Utility.app_logs.info("Go Button Clicked");

		Wait.explicit_wait_visibilityof_webelement(Housekeeping_Status.Select_Items_Per_Page, driver);
		new Select(Housekeeping_Status.Select_Items_Per_Page).selectByVisibleText("All");

		Wait.wait3Second();
		test_steps.add("Successfully Seleted ALL Items Per Page");
		Utility.app_logs.info("Successfully Seleted ALL Items Per Page");

		if (isExist) {
			String OutOfOrder_Path = "//table/tbody/tr//td[.='" + RoomNumber + "']//following-sibling::td//span";
			WebElement element = driver.findElement(By.xpath(OutOfOrder_Path));
			Wait.explicit_wait_visibilityof_webelement(element, driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", element);
			System.out.println(element.getText());
			assertEquals(element.getText(), "Out Of Order", "Out of order room does not find in HKS");
			test_steps.add("Successfully Verify Out Of Order Room In HouseKeeping Tab");
			Utility.app_logs.info("Successfully Verify Out Of Order Room In HouseKeeping Tab");
		} else {

			String OutOfOrder_Path = "//table/tbody/tr//td[.='" + RoomNumber + "']//following-sibling::td//span";
			if (driver.findElements(By.xpath(OutOfOrder_Path)).size() > 0) {
				WebElement element = driver.findElement(By.xpath(OutOfOrder_Path));
				Wait.explicit_wait_visibilityof_webelement(element, driver);
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].scrollIntoView();", element);
				System.out.println(element.getText());
				if (element.getText().equalsIgnoreCase("Out Of Order")) {
					assertEquals(element.getText(), "Out Of Order",
							"Out of order room still exist in HKS after deletion of item");
				} else {
					assertTrue(true);
					test_steps.add("Successfully Verify Deleted Out Of Order Room In HouseKeeping Tab");
					Utility.app_logs.info("Successfully Verify Deleted Out Of Order Room In HouseKeeping Tab");
				}
			} else {
				assertTrue(true);
				test_steps.add("Successfully Verify Deleted Out Of Order Room In HouseKeeping Tab");
				Utility.app_logs.info("Successfully Verify Deleted Out Of Order Room In HouseKeeping Tab");
			}
		}

	}

	public ArrayList<String> VerifyHouseKeepingStatus(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {

		WebElements_Housekeeping_Status Housekeeping_Status = new WebElements_Housekeeping_Status(driver);

		// Verify RoomClass
		Select selectRoomClass = new Select(Housekeeping_Status.Housekeeping_Room_Class);
		WebElement RoomClassoptions = selectRoomClass.getFirstSelectedOption();
		String RoomClass = RoomClassoptions.getText();
		assertEquals(RoomClass, "--ALL--", "Room Class is not correct");
		Utility.app_logs.info("Verify RoomClass : " + RoomClass);
		test_steps.add("Verify RoomClass : " + RoomClass);

		// Verify Date
		assertEquals(Housekeeping_Status.Housekeeping_Date.getAttribute("value"), GetSystemDate(),
				"Date is not same as system date");
		Utility.app_logs.info("Date Verified");
		test_steps.add("Date Verified");

		// Verify Room Status
		boolean RoomStatusVacant = Housekeeping_Status.Housekeeping_RoomStatus_Vacant.isSelected();
		assertEquals(RoomStatusVacant, true, "Room Status Vacant is not checked");
		Utility.app_logs.info("Verify Vacant : " + RoomStatusVacant);
		test_steps.add("Verify Vacant : " + RoomStatusVacant);

		boolean RoomStatusOccupied = Housekeeping_Status.Housekeeping_RoomStatus_Occupied.isSelected();
		assertEquals(RoomStatusOccupied, true, "Room Status Occupied is not checked");
		Utility.app_logs.info("Verify Occupied : " + RoomStatusOccupied);
		test_steps.add("Verify Occupied : " + RoomStatusOccupied);

		boolean RoomStatusOutofOrder = Housekeeping_Status.Housekeeping_RoomStatus_OutofOrder.isSelected();
		assertEquals(RoomStatusOutofOrder, true, "Room Status OutofOrder is not checked");
		Utility.app_logs.info("Verify OutofOrder : " + RoomStatusOutofOrder);
		test_steps.add("Verify OutofOrder : " + RoomStatusOutofOrder);

		// Verify Room Conditions
		boolean RoomConditionClean = Housekeeping_Status.Housekeeping_RoomCondition_Clean.isSelected();
		assertEquals(RoomConditionClean, true, "Room Condition Clean is not checked");
		Utility.app_logs.info("Verify RoomConditionClean : " + RoomConditionClean);
		test_steps.add("Verify RoomConditionClean : " + RoomConditionClean);

		boolean RoomConditionDirty = Housekeeping_Status.Housekeeping_RoomCondition_Dirty.isSelected();
		assertEquals(RoomConditionDirty, true, "Room Condition Dirty is not checked");
		Utility.app_logs.info("Verify RoomConditionDirty : " + RoomConditionDirty);
		test_steps.add("Verify RoomConditionDirty : " + RoomConditionDirty);

		boolean RoomConditionInspected = Housekeeping_Status.Housekeeping_RoomCondition_Inspected.isSelected();
		assertEquals(RoomConditionInspected, true, "Room Condition Inspected is not checked");
		Utility.app_logs.info("Verify RoomConditionInspected : " + RoomConditionInspected);
		test_steps.add("Verify RoomConditionInspected : " + RoomConditionInspected);

		boolean RoomConditionNone = Housekeeping_Status.Housekeeping_RoomCondition_None.isSelected();
		assertEquals(RoomConditionNone, true, "Room Condition None is not checked");
		Utility.app_logs.info("Verify RoomConditionNone : " + RoomConditionNone);
		test_steps.add("Verify RoomConditionNone : " + RoomConditionNone);

		// Due In

		boolean DueInYes = Housekeeping_Status.Housekeeping_DueIn_Yes.isSelected();
		assertEquals(DueInYes, true, "Due In Yes is not checked");
		Utility.app_logs.info("Verify DueInYes : " + DueInYes);
		test_steps.add("Verify DueInYes : " + DueInYes);

		boolean RoomConditionNo = Housekeeping_Status.Housekeeping_DueIn_No.isSelected();
		assertEquals(RoomConditionNo, true, "Due In No is not checked");
		Utility.app_logs.info("Verify RoomConditionNo : " + RoomConditionNo);
		test_steps.add("Verify RoomConditionNo : " + RoomConditionNo);

		// Verify Zone
		Select selectZone = new Select(Housekeeping_Status.Housekeeping_Zone);
		WebElement Zoneoptions = selectZone.getFirstSelectedOption();
		String Zone = Zoneoptions.getText();
		assertEquals(Zone, "--ALL--", "Zone is not correct");
		Utility.app_logs.info("Verify Zone : " + Zone);
		test_steps.add("Verify Zone : " + Zone);

		// Verify GroupBy
		Select selectGroupBy = new Select(Housekeeping_Status.Housekeeping_GroupBy);
		WebElement GroupByoptions = selectGroupBy.getFirstSelectedOption();
		String GroupBy = GroupByoptions.getText();
		assertEquals(GroupBy, "No Grouping", "GroupBy is not correct");
		Utility.app_logs.info("Verify GroupBy : " + GroupBy);
		test_steps.add("Verify GroupBy : " + GroupBy);

		// Verify Go Button
		boolean GoButton = Housekeeping_Status.Housekeeping_GoButton.isDisplayed();
		assertEquals(GoButton, true, "Go Button is not displayed");
		Utility.app_logs.info("Verify GoButton : " + GoButton);
		test_steps.add("Verify GoButton : " + GoButton);

		// Verify Columns

		WebElement Room = driver.findElement(By.xpath("//*[@id=\"MainContent_dgHousekeeping\"]/tbody/tr[1]/td[1]"));
		String RoomColumn = Room.getText();
		assertEquals(RoomColumn, "Room", "ColumnName is not Room");
		Utility.app_logs.info("Verify RoomColumn : " + RoomColumn);
		test_steps.add("Verify RoomColumn : " + RoomColumn);

		WebElement Class = driver.findElement(By.xpath("//*[@id=\"MainContent_dgHousekeeping\"]/tbody/tr[1]/td[2]"));
		String ClassColumn = Class.getText();
		assertEquals(ClassColumn, "Class", "ColumnName is not Class");
		Utility.app_logs.info("Verify ClassColumn : " + ClassColumn);
		test_steps.add("Verify ClassColumn : " + ClassColumn);

		WebElement HouseKeepingZone = driver
				.findElement(By.xpath("//*[@id=\"MainContent_dgHousekeeping\"]/tbody/tr[1]/td[3]"));
		String HouseKeepingZoneColumn = HouseKeepingZone.getText();
		assertEquals(HouseKeepingZoneColumn, "Housekeeping Zone", "ColumnName is not HouseKeepingZone");
		Utility.app_logs.info("Verify HouseKeepingZoneColumn : " + HouseKeepingZoneColumn);
		test_steps.add("Verify HouseKeepingZoneColumn : " + HouseKeepingZoneColumn);

		WebElement Condition = driver
				.findElement(By.xpath("//*[@id=\"MainContent_dgHousekeeping\"]/tbody/tr[1]/td[4]"));
		String ConditionColumn = Condition.getText();
		// System.out.println(ConditionColumn);
		assertEquals(ConditionColumn, "Condition\n" + "Clean Dirty Inspected", "Condition Clean Dirty not Inspected\"");

		WebElement GuestName = driver
				.findElement(By.xpath("//*[@id=\"MainContent_dgHousekeeping\"]/tbody/tr[1]/td[5]"));
		String GuestNameColumn = GuestName.getText();
		assertEquals(GuestNameColumn, "Guest Name", "ColumnName is not GuestName");
		Utility.app_logs.info("Verify GuestNameColumn : " + GuestNameColumn);
		test_steps.add("Verify GuestNameColumn : " + GuestNameColumn);

		WebElement RatePlanName = driver
				.findElement(By.xpath("//*[@id=\"MainContent_dgHousekeeping\"]/tbody/tr[1]/td[6]"));
		String RatePlanName_Column = RatePlanName.getText();
		assertEquals(RatePlanName_Column, "Rate Plan Name", "ColumnName is not RatePlanName");
		Utility.app_logs.info("Verify RatePlanName_Column : " + RatePlanName_Column);
		test_steps.add("Verify RatePlanName_Column : " + RatePlanName_Column);

		WebElement Ad = driver.findElement(By.xpath("//*[@id=\"MainContent_dgHousekeeping\"]/tbody/tr[1]/td[7]"));
		String AdColumn = Ad.getText();
		assertEquals(AdColumn, "Ad", "ColumnName is not Ad");
		Utility.app_logs.info("Verify AdColumn : " + AdColumn);
		test_steps.add("Verify AdColumn : " + AdColumn);

		WebElement Ch = driver.findElement(By.xpath("//*[@id=\"MainContent_dgHousekeeping\"]/tbody/tr[1]/td[8]"));
		String ChColumn = Ch.getText();
		assertEquals(ChColumn, "Ch", "ColumnName is not Ch");
		Utility.app_logs.info("Verify ChColumn : " + ChColumn);
		test_steps.add("Verify ChColumn : " + ChColumn);

		WebElement Departure = driver
				.findElement(By.xpath("//*[@id=\"MainContent_dgHousekeeping\"]/tbody/tr[1]/td[9]"));
		String DepartureColumn = Departure.getText();
		assertEquals(DepartureColumn, "Departure", "ColumnName is not Departure");
		Utility.app_logs.info("Verify DepartureColumn : " + DepartureColumn);
		test_steps.add("Verify DepartureColumn : " + DepartureColumn);

		WebElement DueIn = driver.findElement(By.xpath("//*[@id=\"MainContent_dgHousekeeping\"]/tbody/tr[1]/td[10]"));
		String DueInColumn = DueIn.getText();
		assertEquals(DueInColumn, "Due In", "ColumnName is not DueIn");
		Utility.app_logs.info("Verify DueInColumn : " + DueInColumn);
		test_steps.add("Verify DueInColumn : " + DueInColumn);

		// Page HyperLink
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", Housekeeping_Status.Housekeeping_PageHyperLink);
		boolean PageNumberHyperLink = Housekeeping_Status.Housekeeping_PageHyperLink.isDisplayed();
		if (PageNumberHyperLink) {
			assertEquals(PageNumberHyperLink, true, "PageNumber is Not Hyperlink");
			Utility.app_logs.info("Verify PageNumberHyperLink : " + PageNumberHyperLink);
			test_steps.add("Verify PageNumberHyperLink : " + PageNumberHyperLink);
		} else {
			WebElement pageNumberHL = driver
					.findElement(By.xpath("//tr[12]/td/span[contains(@style,'font-weight:bold')]"));
			if (pageNumberHL.getText().equalsIgnoreCase("1")) {
				Utility.app_logs.info("Pagination have Only One Page");
				test_steps.add("Pagination have Only One Page");
			}
		}

		// Save Button

		boolean SaveButton = Housekeeping_Status.Housekeeping_SaveButton.isDisplayed();
		assertEquals(SaveButton, true, "Save Button is not displayed");
		Utility.app_logs.info("Verify SaveButton : " + SaveButton);
		test_steps.add("Verify SaveButton : " + SaveButton);

		// Cancel Button

		boolean CancelButton = Housekeeping_Status.Housekeeping_CancelButton.isDisplayed();
		assertEquals(CancelButton, true, "Cancel Button is not displayed");
		Utility.app_logs.info("Verify CancelButton : " + CancelButton);
		test_steps.add("Verify CancelButton : " + CancelButton);

		return test_steps;
	}

	public String GetSystemDate() {

		DateFormat dateInstance = SimpleDateFormat.getDateInstance();
		dateInstance.setTimeZone(TimeZone.getTimeZone("EST"));
		String date = dateInstance.format(Calendar.getInstance().getTime());
		String SplitDate[] = date.split(" ");
		String month = SplitDate[0];
		String day = SplitDate[1];
		String d[] = day.split(",");
		String year = SplitDate[2];
		if (Integer.parseInt(d[0]) < 10) {
			day = "0" + d[0];
		} else {
			day = d[0];
		}
		String format = month + " " + day + ", " + year;

		return format;

	}

	public void Select_RoomClass(WebDriver driver, String RoomClass) throws InterruptedException {

		WebElements_Housekeeping_Status Housekeeping_Status = new WebElements_Housekeeping_Status(driver);
		Housekeeping_Status.Housekeeping_Room_Class.sendKeys(RoomClass);
		Housekeeping_Status.Housekeeping_GoButton.click();

	}

	public void Select_RoomCondition(WebDriver driver, String RoomCondition) throws InterruptedException {

		WebElements_Housekeeping_Status Housekeeping_Status = new WebElements_Housekeeping_Status(driver);
		UnCheckAllRoomConditions(driver);
		String RoomConditionPath = "//table[@id='MainContent_chkRoomCondition']//label[text()='" + RoomCondition
				+ "']//preceding-sibling::input[@type='checkbox']";
		WebElement RoomConditionCheckBox = driver.findElement(By.xpath(RoomConditionPath));
		if (!RoomConditionCheckBox.isSelected()) {
			RoomConditionCheckBox.click();
			assertTrue(RoomConditionCheckBox.isSelected(),
					"Failed: Room Condition '" + RoomCondition + "' CheckBox is not Selected.");
			Utility.app_logs.info("Room Condition '" + RoomCondition + "' is Selected");
		} else {
			Utility.app_logs.info("Room Condition '" + RoomCondition + "' is already Selected");
		}
		Housekeeping_Status.Housekeeping_GoButton.click();

	}

	public void VerifyResult_RoomCondition(WebDriver driver, String RoomCondition, ArrayList<String> test_steps)
			throws InterruptedException {

		Wait.explicit_wait_xpath(OR.Housekeeping_Result, driver);
		String[] RoomConditions = { "Clean", "Dirty", "Inspected" };
		String RoomConditionPath = "//div[@id='MainContent_dgHousekeepingStatus_DIV']//label[text()='" + RoomCondition
				+ "']//preceding-sibling::input[@type='radio']";

		List<WebElement> SearchedRooms = driver.findElements(By.xpath(RoomConditionPath));
		List<WebElement> HouseKeeping_ResultTable = driver.findElements(By.xpath(OR.Housekeeping_Result));
		if (HouseKeeping_ResultTable.size() > 2) {

			Utility.app_logs.info((HouseKeeping_ResultTable.size() - 2) + " Room found having Room Condition = ' "
					+ RoomCondition + " '");
			test_steps.add((HouseKeeping_ResultTable.size() - 2) + " Room found having Room Condition = ' "
					+ RoomCondition + " '");
			if (RoomCondition.contains("None")) {
				for (int j = 0; j < RoomConditions.length; j++) {
					RoomConditionPath = "//div[@id='MainContent_dgHousekeepingStatus_DIV']//label[text()='"
							+ RoomConditions[j] + "']//preceding-sibling::input[@type='radio']";
					System.out.println(RoomConditionPath);
					SearchedRooms = driver.findElements(By.xpath(RoomConditionPath));
					for (int i = 1; i < SearchedRooms.size(); i++) {
						System.out.println("Checking Room Condition " + RoomConditions[j]);
						assertEquals(SearchedRooms.get(i).isSelected(), false,
								"Failed : Room Condition " + RoomConditions[j] + "is Selecet");
					}
				}
			} else {
				for (int i = 1; i < SearchedRooms.size(); i++) {
					System.out.println("Checking Room Condition " + RoomCondition);
					assertEquals(SearchedRooms.get(i).isSelected(), true, "Failed : Result missmatched");
				}
			}
		} else {
			Utility.app_logs.info("No Room found having Room Condition = ' " + RoomCondition + " '");
			test_steps.add("No Room found having Room Condition = ' " + RoomCondition + " '");
		}

	}

	public void UnCheckAllRoomConditions(WebDriver driver) {

		WebElements_Housekeeping_Status Housekeeping_Status = new WebElements_Housekeeping_Status(driver);
		if (Housekeeping_Status.Housekeeping_RoomCondition_Clean.isSelected()) {
			Housekeeping_Status.Housekeeping_RoomCondition_Clean.click();
			boolean RoomConditionClean = Housekeeping_Status.Housekeeping_RoomCondition_Clean.isSelected();
			assertEquals(RoomConditionClean, false, "Room Condition Clean is not unchecked");
		}
		if (Housekeeping_Status.Housekeeping_RoomCondition_Dirty.isSelected()) {
			Housekeeping_Status.Housekeeping_RoomCondition_Dirty.click();
			boolean RoomConditionDirty = Housekeeping_Status.Housekeeping_RoomCondition_Dirty.isSelected();
			assertEquals(RoomConditionDirty, false, "Room Condition Dirty is not unchecked");
		}
		if (Housekeeping_Status.Housekeeping_RoomCondition_Inspected.isSelected()) {
			Housekeeping_Status.Housekeeping_RoomCondition_Inspected.click();
			boolean RoomConditionInspected = Housekeeping_Status.Housekeeping_RoomCondition_Inspected.isSelected();
			assertEquals(RoomConditionInspected, false, "Room Condition Inspected is not unchecked");
		}
		if (Housekeeping_Status.Housekeeping_RoomCondition_None.isSelected()) {
			Housekeeping_Status.Housekeeping_RoomCondition_None.click();
			boolean RoomConditionNone = Housekeeping_Status.Housekeeping_RoomCondition_None.isSelected();
			assertEquals(RoomConditionNone, false, "Room Condition None is not unchecked");
		}

	}

	public void Verify_TotalItem_SelectedProperty(WebDriver driver, String TotalItem) throws InterruptedException {

		WebElements_Housekeeping_Status Housekeeping_Status = new WebElements_Housekeeping_Status(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();",
				Housekeeping_Status.Housekeeping_TotalItems_SelectedProperty);
		String GetHouseKeepingStatusValue = Housekeeping_Status.Housekeeping_TotalItems_SelectedProperty.getText();
		assertTrue(GetHouseKeepingStatusValue.equals(TotalItem), "All rooms Associated with property not displayed");

	}

	public String ChangeRoomConditionToClean(WebDriver driver) throws InterruptedException {

		Random rand = new Random();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElements_Housekeeping_Status Housekeeping_Status = new WebElements_Housekeeping_Status(driver);
		String GetHouseKeepingStatusTotalValue = Housekeeping_Status.Housekeeping_TotalItems_SelectedProperty.getText();
		int n = Integer.parseInt(GetHouseKeepingStatusTotalValue);
		int RoomNumberToSet = rand.nextInt(n);
		System.out.println("RandomNumberToSeacrh:" + RoomNumberToSet);

		for (int i = 1; i < n; i++) {
			int temp = i / 2;
			System.out.println("TEMP:" + temp);
			String TableIndex = null;
			String RoomNum = null;
			int TableIndexInt = 0;
			if (temp == 0) {
				System.out.println("EVEN");

				WebElement TableIndexEle = driver.findElement(
						By.xpath("//div[@id='MainContent_dgHousekeepingStatus_DIV']//tr[@class='dgItemAlt']["
								+ RoomNumberToSet / 2 + "]//td"));
				jse.executeScript("arguments[0].scrollIntoView(true);", TableIndexEle);
				TableIndex = TableIndexEle.getText();
				TableIndexInt = Integer.parseInt(TableIndex);
				if (TableIndexInt == RoomNumberToSet) {

					WebElement RadioBtn = driver.findElement(
							By.xpath("(//div[@id='MainContent_dgHousekeepingStatus_DIV']//tr[@class='dgItemAlt']["
									+ RoomNumberToSet / 2 + "]//td[4]//table//input)[1]"));
					RadioBtn.click();

				}
				RoomNum = String.valueOf(RoomNumberToSet / 2);
				return RoomNum;
			} else {
				System.out.println("ODD");
				WebElement TableIndexEle = driver.findElement(By.xpath(
						"//div[@id='MainContent_dgHousekeepingStatus_DIV']//tr[@class='dgItem'][" + i / 2 + "]//td"));
				jse.executeScript("arguments[0].scrollIntoView(true);", TableIndexEle);
				TableIndex = TableIndexEle.getText();
				TableIndexInt = Integer.parseInt(TableIndex);
				if (TableIndexInt == RoomNumberToSet) {

					WebElement RadioBtn = driver.findElement(
							By.xpath("(//div[@id='MainContent_dgHousekeepingStatus_DIV']//tr[@class='dgItem']["
									+ TableIndexInt / 2 + "]//td[4]//table//input)[1]"));
					RadioBtn.click();
				}
				RoomNum = String.valueOf(RoomNumberToSet / 2);
				return RoomNum;
			}

			// assertTrue(GetHouseKeepingStatusValue.equals(TotalItem), "All
			// rooms Associated with property not displayed");

		}
		return null;
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
		// File image1= new File(System.getProperty("user.dir") + File.separator +
		// "pict.jpg");
		// File image2= new File(System.getProperty("user.dir") + File.separator +
		// "innroadLogo.jpg");
		//
		// assertTrue(Utility.compareImage(image1, image2),"Failed: Inncenter Logo is
		// not Displayed");
		// test_steps.add("Inncenter Logo is Displayed");
		// Utility.app_logs.info("Inncenter Logo is Displayed");
		// image1.delete();
		return finalContent;
	}

	public ArrayList<String> verifyReport(String content, String propertyName) {
		ArrayList<String> test_steps = new ArrayList<>();

		assertTrue(content.contains("Housekeeping Status"), "Failed: Report Name Not Matched");
		test_steps.add("Report Name is Displayed");
		Utility.app_logs.info("Report Name is Displayed");

		assertTrue(content.contains("Property : " + propertyName), "Failed: Property Name Not Matched");
		test_steps.add("Property Name is displayed");
		Utility.app_logs.info("Property Name is displayed");

		assertTrue(content.contains("ClassRoom No Condition Guest Name Ad/Ch Depature Due InZone"),
				"Failed: ClassRoom No Condition Guest Name Ad/Ch Depature Due InZone not Displayed");
		test_steps.add("Room No, Class, Zone, Condition, Guest Name, Ad/Ch Departure, ,Duein columns are displayed");
		Utility.app_logs
				.info("Room No, Class, Zone, Condition, Guest Name, Ad/Ch Departure, ,Duein columns are displayed");

		assertTrue(content.contains("Printed On :" + getDate()), "Failed: Printed Date Not Displayed");
		test_steps.add("Printed Date is displayed : " + getDate());
		Utility.app_logs.info("Printed Date is displayed : " + getDate());

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
		// String frame="//iframe[@id='MainContent_ucRptViewer_ifrmAccountStatement']";
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

	public String checkPDF() throws IOException {
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

	public ArrayList<String> searchVacantDirty(WebDriver driver, String RoomClass) {
		ArrayList<String> test_steps = new ArrayList<String>();
		WebElements_Housekeeping_Status Housekeeping_Status = new WebElements_Housekeeping_Status(driver);

		if (!Housekeeping_Status.Housekeeping_RoomStatus_Vacant.isSelected()) {
			Housekeeping_Status.Housekeeping_RoomStatus_Vacant.click();
			boolean flag = Housekeeping_Status.Housekeeping_RoomStatus_Vacant.isSelected();
			assertEquals(flag, true, "Room Status Vaccant is not checked");

		}
		Utility.app_logs.info("Verify Vacant : Checked");
		test_steps.add("Verify Vacant : Checked");

		if (Housekeeping_Status.Housekeeping_RoomStatus_Occupied.isSelected()) {
			Housekeeping_Status.Housekeeping_RoomStatus_Occupied.click();
			boolean flag = Housekeeping_Status.Housekeeping_RoomStatus_Occupied.isSelected();
			assertEquals(flag, false, "Room Status Occupied is not unchecked");

		}
		Utility.app_logs.info("Verify Occupied : Unchecked");
		test_steps.add("Verify Occupied : Unchecked");
		if (Housekeeping_Status.Housekeeping_RoomStatus_OutofOrder.isSelected()) {
			Housekeeping_Status.Housekeeping_RoomStatus_OutofOrder.click();
			boolean flag = Housekeeping_Status.Housekeeping_RoomStatus_OutofOrder.isSelected();
			assertEquals(flag, false, "Room Status OutOfOrder is not unchecked");

		}
		Utility.app_logs.info("Verify Out Of Order : UnChecked");
		test_steps.add("Verify Out Of Order : UnChecked");

		if (Housekeeping_Status.Housekeeping_RoomCondition_Clean.isSelected()) {
			Housekeeping_Status.Housekeeping_RoomCondition_Clean.click();
			boolean RoomConditionClean = Housekeeping_Status.Housekeeping_RoomCondition_Clean.isSelected();
			assertEquals(RoomConditionClean, false, "Room Condition Clean is not unchecked");
		}
		if (!Housekeeping_Status.Housekeeping_RoomCondition_Dirty.isSelected()) {
			Housekeeping_Status.Housekeeping_RoomCondition_Dirty.click();
			boolean RoomConditionDirty = Housekeeping_Status.Housekeeping_RoomCondition_Dirty.isSelected();
			assertEquals(RoomConditionDirty, true, "Room Condition Dirty is not checked");
		}
		if (Housekeeping_Status.Housekeeping_RoomCondition_Inspected.isSelected()) {
			Housekeeping_Status.Housekeeping_RoomCondition_Inspected.click();
			boolean RoomConditionInspected = Housekeeping_Status.Housekeeping_RoomCondition_Inspected.isSelected();
			assertEquals(RoomConditionInspected, false, "Room Condition Inspected is not unchecked");
		}
		if (Housekeeping_Status.Housekeeping_RoomCondition_None.isSelected()) {
			Housekeeping_Status.Housekeeping_RoomCondition_None.click();
			boolean RoomConditionNone = Housekeeping_Status.Housekeeping_RoomCondition_None.isSelected();
			assertEquals(RoomConditionNone, false, "Room Condition None is not unchecked");
		}

		Wait.explicit_wait_visibilityof_webelement(Housekeeping_Status.HK_SelectRoomClass, driver);
		new Select(Housekeeping_Status.HK_SelectRoomClass).selectByVisibleText(RoomClass);
		test_steps.add("Room Class Selected : " + RoomClass);
		Utility.app_logs.info("Room Class Selected : " + RoomClass);

		Housekeeping_Status.HK_GoButton.click();
		test_steps.add("Go Button Clicked");
		Utility.app_logs.info("Go Button Clicked");

		return test_steps;
	}

	public void searchRoomClass(WebDriver driver, String RoomClass) {

		WebElements_Housekeeping_Status Housekeeping_Status = new WebElements_Housekeeping_Status(driver);
		Wait.explicit_wait_visibilityof_webelement(Housekeeping_Status.HK_SelectRoomClass, driver);
		new Select(Housekeeping_Status.HK_SelectRoomClass).selectByVisibleText(RoomClass);

		Utility.app_logs.info("Room Class Selected : " + RoomClass);

		Housekeeping_Status.HK_GoButton.click();

		Utility.app_logs.info("Go Button Clicked");
	}

	// save changes
	public void Save(WebDriver driver, ExtentTest test) throws InterruptedException {

		WebElements_Housekeeping_Status Housekeeping_Status = new WebElements_Housekeeping_Status(driver);
		Wait.WaitForElement(driver, OR.Save_Housekeeping_Status);
		Housekeeping_Status.Save_Housekeeping_Status.click();
		Wait.wait5Second();
	}
}
