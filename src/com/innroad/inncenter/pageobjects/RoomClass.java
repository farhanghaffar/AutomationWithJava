
package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.innroad.inncenter.interfaces.IRoomClass;
import com.innroad.inncenter.pages.NewRoomClass;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Setup;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_NewRoomClass;
import com.innroad.inncenter.webelements.Elements_On_All_Navigation;
import com.relevantcodes.extentreports.ExtentTest;

import junit.framework.Assert;

public class RoomClass implements IRoomClass {

	public static Logger roomclassLogger = Logger.getLogger("RoomClasses");

	public void RoomClass_Search(WebDriver driver) {
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		Wait.WaitForElement(driver, OR.SearchRoomClass);
		roomClass.SearchRoomClass.click();
	}

	public String getRoomClassName(WebDriver driver, String RoomClassName, String RoomClassAbbreviation, String test1)
			throws InterruptedException {
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);

		String xpath = "//a[.='" + RoomClassName + "']";
		String roomclass = driver.findElement(By.xpath(xpath)).getText();

		if (roomclass.contains(RoomClassName)) {

			driver.findElement(By.xpath(xpath)).click();

			Wait.wait5Second();

			// RoomClassAbbreviation=
			// driver.findElement(By.xpath("//label[.='Room Class
			// Abbreviation:']//following::input[1]")).getText();

			// RoomClassAbbreviation=e.getText();

			WebElement e = driver.findElement(By.xpath("//label[.='Room Class Abbreviation:']//following::input[1]"));

			// System.out.println("Room Class Abbreviation is: "
			// +RoomClassAbbreviation);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			test1 = (String) js.executeScript("return arguments[0].value", e);

			Wait.wait5Second();
		}

		return test1;

	}

	public boolean roomClassInfoNewPage(WebDriver driver, String roomClassName, String roomClassAbbrivation,
			String bedsCount, String maxAdults, String maxPersopns, String roomQuantity, ExtentTest test)
			throws Exception {

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		// Creating object for Elements_NewRoomClass
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);

		// Enter Room Class Name
		roomClass.NewRoomClassName1.sendKeys(roomClassName);
		// System.out.println("Entered the room class name : " + roomClassName);
		Utility.app_logs.info("Entered the room class name : " + roomClassName);

		// Enter the new class room abbreviation
		roomClass.NewRoomClassAbbrivation1.sendKeys(roomClassAbbrivation);
		// System.out.println("Entered the room class abbreviation : " +
		// roomClassAbbrivation);
		Utility.app_logs.info("Entered the room class abbreviation : " + roomClassAbbrivation);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		// Enter the number of king bed count
		js.executeScript("arguments[0].scrollIntoView();", roomClass.NewRoomClassKingBeds1);
		roomClass.NewRoomClassKingBeds1.sendKeys(bedsCount);
		// System.out.println("Entered the king beds count : " + bedsCount);
		Utility.app_logs.info("Entered the king beds count : " + bedsCount);

		// Enter the Max Adults
		roomClass.NewRoomClassMaxAdults1.sendKeys(maxAdults);
		Utility.app_logs.info("Entered the max audlts : " + maxAdults);

		// Enter the Max Persons
		roomClass.NewRoomClassMaxPersons1.sendKeys(maxPersopns);
		Utility.app_logs.info("Entered the max persons : " + maxPersopns);

		// checking
		js.executeScript("arguments[0].click(true);", roomClass.NewRoomClass_TripAdvisorTab);
		js.executeScript("arguments[0].click(true);", roomClass.Amenities1);
		js.executeScript("arguments[0].click(true);", roomClass.Amenities2);

		js.executeScript("arguments[0].click(true);", roomClass.NewRoomClassRooms1);
		Utility.app_logs.info("clicked on create rooms");
		// Enter the Rooms Quantity
		roomClass.NewRoomClassRoomQuantity1.sendKeys(roomQuantity);
		Utility.app_logs.info("Enterd the rooms quantity : " + roomQuantity);

		// Click create Rooms
		roomClass.NewRoomClassCreateRooms1.click();
		System.out.println("clicked on create rooms");

		// Waiting for visibility of Room Number text box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_RoomNumber1)));

		// Clear the Room Number text box
		roomClass.NewRoomClassRoomNumber1.clear();

		// Enter for Room Numbers
		String RoomNumber = Utility.GenerateRandomNumber();
		roomClass.NewRoomClassRoomNumber1.sendKeys(RoomNumber);

		Utility.app_logs.info("Entered the room number " + RoomNumber);
		// click on Assign room number
		roomClass.NewRoomClassAssignRoomNumbers1.click();
		Utility.app_logs.info("clicked on assign room numbers");

		// Clicking on publish
		roomClass.NewRoomClassPublishButton1.click();
		Utility.app_logs.info("clicked on Publish");

		// Clicking on OK
		Wait.explicit_wait_visibilityof_webelement_120(roomClass.NewRoomClassOk1, driver);
		Utility.ScrollToElement(roomClass.NewRoomClassOk1, driver);
		roomClass.NewRoomClassOk1.click();
		Utility.app_logs.info("Clicked on OK");

		// Waiting for visibility of publish
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Publish1)));
		// wait.until(ExpectedConditions.elementToBeClickable(roomClass.NewRoomClassPublishButton1));
		Utility.app_logs.info("Room Classes Clicked");

		roomClass.CloseRoomClass.click();

		Utility.app_logs.info("Close Room Classes Clicked");
		// Parsing the Room class name to get First character to upper case
		char ch = roomClassName.charAt(0);
		String str = "" + ch;
		str = str.toUpperCase();
		System.out.println("String :" + str);

		SelectItemsPerPage(driver);

		// Waiting for visibility of Search Bar
		WebElement Search = wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//label[@class='RoomClasses_alphabetFilterButton_1tyHW ant-radio-button-wrapper']//span[text()='"
						+ str + "']")));
		// driver.findElement(By.xpath("//ul[@class='pagination']/li/a[contains(text(),'"
		// + str + "')]")).click();
		js.executeScript("arguments[0].click(true);", Search);

		// Search.click();
		// Validating the newly created room class
		if (driver.findElements(By.xpath("//a[contains(text(),'" + roomClassName + "')]")).size() > 0) {
			Utility.app_logs.info("newly created class room validated successfully");
			return true;
		} else {
			Utility.app_logs.info("new class room not successfully created");
			return false;
		}
	}

	public ArrayList<String> UpdateRoomClassInfo(WebDriver driver, String newroomClassAbbrivation, String bedsCount,
			ExtentTest test, ArrayList<String> test_steps) throws Exception {

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		// Creating object for Elements_NewRoomClass
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);

		// Enter the new class room abbreviation
		roomClass.NewRoomClassAbbrivation1.sendKeys(newroomClassAbbrivation);
		// System.out.println("Entered the room class abbreviation : " +
		// roomClassAbbrivation);
		test_steps.add("Entered the room class abbreviation : " + newroomClassAbbrivation);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		// Enter the number of king bed count
		js.executeScript("arguments[0].scrollIntoView();", roomClass.NewRoomClassKingBeds1);
		roomClass.NewRoomClassKingBeds1.sendKeys(bedsCount);
		// System.out.println("Entered the king beds count : " + bedsCount);
		test_steps.add("Entered the king beds count : " + bedsCount);

		// Clicking on publish
		Utility.ScrollToElement(roomClass.NewRoomClassPublishButton1, driver);
		roomClass.NewRoomClassPublishButton1.click();
		Utility.app_logs.info("clicked on Publish");
		test_steps.add("clicked on Publish");

		// Clicking on OK
		Wait.explicit_wait_visibilityof_webelement_120(roomClass.NewRoomClassOk1, driver);
		Utility.ScrollToElement(roomClass.NewRoomClassOk1, driver);
		roomClass.NewRoomClassOk1.click();
		Utility.app_logs.info("Clicked on OK");
		test_steps.add("clicked on OK");

		// Waiting for visibility of publish
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Publish1)));
		// wait.until(ExpectedConditions.elementToBeClickable(roomClass.NewRoomClassPublishButton1));
		Utility.app_logs.info("Room Classes Clicked");
		test_steps.add("Room Classes Clicked");

		roomClass.CloseRoomClass.click();

		Utility.app_logs.info("Close Room Classes Clicked");
		test_steps.add("Close Room Classes Clicked");
		return test_steps;

	}

	public ArrayList<String> SearchRoom(WebDriver driver, String roomClassName, ArrayList<String> test_steps)
			throws Exception {
		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);
		char ch = roomClassName.charAt(0);
		String str = "" + ch;
		str = str.toUpperCase();
		System.out.println("String :" + str);

		// Waiting for visibility of Search Bar
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Search;
		try {
			Search = driver.findElement(By.xpath("//ul[@class='pagination']/li/a[contains(text(),'" + str + "')]"));

		} catch (Exception e) {
			Search = wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//label[@class='RoomClasses_alphabetFilterButton_1tyHW ant-radio-button-wrapper']//span[text()='"
							+ str + "']")));

		}
		js.executeScript("arguments[0].click(true);", Search);

		// Search.click();
		// Validating the newly created room class
		if (driver.findElements(By.xpath("//a[contains(text(),'" + roomClassName + "')]")).size() > 0) {
			Utility.app_logs.info("newly created class room validated successfully");
			test_steps.add("newly created class room validated successfully");

		} else {
			Utility.app_logs.info("new class room not successfully created");
			test_steps.add("new class room not successfully created");

		}
		return test_steps;

	}

	public ArrayList<String> SearchRoomClass(WebDriver driver, String roomClassName, ArrayList<String> test_steps)
			throws Exception {
		// Explicit wait object creation
		char ch = roomClassName.charAt(0);
		String str = "" + ch;
		str = str.toUpperCase();
		System.out.println("String :" + str);

		// Waiting for visibility of Search Bar
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Search;

		Search = driver.findElement(By.xpath("//ul[@class='pagination']/li/a[contains(text(),'" + str + "')]"));
		Wait.waitForElementToBeClickable(By.xpath("//ul[@class='pagination']/li/a[contains(text(),'" + str + "')]"),
				driver);
		js.executeScript("arguments[0].click(true);", Search);
		Utility.app_logs.info("Click: " + str);
		// Search.click();
		// Validating the newly created room class
		if (driver.findElements(By.xpath("//a[contains(text(),'" + roomClassName + "')]")).size() > 0) {
			Utility.app_logs.info("newly created class room validated successfully");
			test_steps.add("newly created class room validated successfully");

		} else {
			Utility.app_logs.info("Existing room class not avilable");
			test_steps.add("Existing room class not avilable");

		}
		return test_steps;

	}

	public void deleteAllRoomClasses(WebDriver driver, String roomClassName, ArrayList<String> test_steps)
			throws Exception {
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		boolean policiesExist = false;
		String str = ("" + roomClassName.charAt(0)).toUpperCase();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Search = driver
				.findElement(By.xpath("//ul[@class='pagination']/li/a[contains(text(),'" + str + "')]"));
		js.executeScript("arguments[0].click(true);", Search);
		try {
			Wait.waitForElementToBeVisibile(By.xpath(OR.roomClassHelpIcon), driver);
		} catch (Exception e) {
			Wait.waitForElementToBeVisibile(By.xpath(OR.roomClassCollapseIcon), driver);
		}
		try {
			List<WebElement> existingPoliciesCheckBoxes = driver
					.findElements(By.xpath("//a[contains(text(),'" + roomClassName + "')]"
							+ "//parent::td//following-sibling::td[contains(@data-bind,'DeleteRoomclass')]/input"));
			for (WebElement checkBox : existingPoliciesCheckBoxes) {
				checkBox.click();
				policiesExist = true;
			}
			if (policiesExist) {
				roomClass.DeleteRoomClass_Button.click();
				Wait.waitForElementToBeClickable(By.xpath(OR.Toaster_Message_Close), driver);
				roomClass.Toaster_Message_Close.click();
				test_steps.add("Deleted all room classes named with " + roomClassName);
			} else {
				test_steps.add("No existing room classes to delete for " + roomClassName);
			}
		} catch (Exception e) {
			test_steps.add("No existing room classes to delete for " + roomClassName);
		}
	}

	public void DeleteRoomClass(WebDriver driver) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		try {

			js.executeScript("arguments[0].scrollIntoView(true);", roomClass.SelectAllRecord);
			roomClass.SelectAllRecord_1.click();
			Wait.waitUntilPresenceOfElementLocated(OR.SelectAllRecord, driver);
			Utility.ScrollToElement(roomClass.SelectAllRecord, driver);
			roomClass.Select100TopRecord_1.click();
			Wait.wait2Second();
			Utility.ScrollToElement(roomClass.SelectRoomClassCheckBox, driver);
			roomClass.SelectRoomClassCheckBox.click();
			js.executeScript("arguments[0].click(true);", roomClass.RoomclassDeleteButton);
			js.executeScript("arguments[0].click(true);", roomClass.RoomclassDelete_OKButton);
		} catch (Exception e) {
			// js.executeScript("arguments[0].scrollIntoView(true);",
			// roomClass.SelectAllRecord_1);
			// roomClass.SelectAllRecord_1.click();
			// Wait.waitUntilPresenceOfElementLocated(OR.SelectAllRecord_1);
			// Utility.ScrollToElement(roomClass.SelectAllRecord_1);
			// new
			// Select(roomClass.SelectAllRecord_1).selectByVisibleText("100");
			roomClass.SelectRoomClassCheckBox.click();
			roomClass.DeleteRoomClassesButton.click();
			Utility.ScrollToElement(roomClass.RoomclassDelete_OKButton, driver);
			roomClass.RoomclassDelete_OKButton.click();

		}

		// Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message);
	}

	public void DeleteRoomClass(WebDriver driver, String RoomClass) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		js.executeScript("arguments[0].scrollIntoView(true);", roomClass.SelectAllRecord);
		roomClass.SelectAllRecord.click();
		Wait.waitUntilPresenceOfElementLocated(OR.SelectAllRecord, driver);
		Utility.ScrollToElement(roomClass.SelectAllRecord, driver);
		new Select(roomClass.SelectAllRecord).selectByVisibleText("100");
		// roomClass.RoomClass_Checkbox.get(roomClass.RoomClass_Checkbox.size()
		// - 1).click();

		String RoomClassPath = "//span[text()='" + RoomClass
				+ "']//parent::td//following-sibling::td[contains(@data-bind,'DeleteRoomclass')]/input";
		Wait.explicit_wait_xpath(RoomClassPath, driver);
		Utility.app_logs.info(RoomClassPath);
		WebElement room = driver.findElement(By.xpath(RoomClassPath));

		js.executeScript("arguments[0].scrollIntoView(true);", room);
		Thread.sleep(10000);
		// Wait.explicit_wait_visibilityof_webelement(room);
		// Utility.ScrollToElement(room);

		room.click();
		roomClass.DeleteRoomClass_Button.click();

		// Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message);
	}

	public void Delete_RoomClass_NOToast(WebDriver driver, String RoomClass) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		String RoomClassPath = "//span[text()='" + RoomClass
				+ "']//parent::td//following-sibling::td[contains(@data-bind,'DeleteRoomclass')]/input";
		Wait.explicit_wait_xpath(RoomClassPath, driver);

		WebElement room = driver.findElement(By.xpath(RoomClassPath));

		js.executeScript("arguments[0].scrollIntoView(true);", room);
		Thread.sleep(10000);
		// Wait.explicit_wait_visibilityof_webelement(room);
		// Utility.ScrollToElement(room);

		room.click();
		roomClass.DeleteRoomClass_Button.click();

		try {
			Wait.explicit_wait_visibilityof_webelement_120(roomClass.Toaster_Message, driver);
			roomclassLogger.info(roomClass.Toaster_Message.getText());
			Wait.waitForElementToBeGone(driver, roomClass.Toaster_Message, 20);
		} catch (Exception e) {

		}
	}

	public ArrayList<String> roomClassInfo(WebDriver driver, String roomClassName, String roomClassAbbrivation,
			String bedsCount, String maxAdults, String maxPersopns, String roomQuantity, ExtentTest test,
			ArrayList<String> test_steps) throws Exception {

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		// Creating object for Elements_NewRoomClass
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);

		// Enter Room Class Name
		roomClass.NewRoomClassName.sendKeys(roomClassName);
		// System.out.println("Entered the room class name : " + roomClassName);
		test_steps.add("Entered the room class name : " + roomClassName);

		// Enter the new class room abbreviation
		roomClass.NewRoomClassAbbrivation.sendKeys(roomClassAbbrivation);
		// System.out.println("Entered the room class abbreviation : " +
		// roomClassAbbrivation);
		test_steps.add("Entered the room class abbreviation : " + roomClassAbbrivation);

		// Enter the number of king bed count
		roomClass.NewRoomClassKingBeds.sendKeys(bedsCount);
		// System.out.println("Entered the king beds count : " + bedsCount);
		test_steps.add("Entered the king beds count : " + bedsCount);

		// Java script object creation
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");

		// Waiting for visibility of adjoining rooms
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_RoomType_AdjoiningRooms)));

		// Clicking on adjoining rooms check box
		roomClass.New_RoomClassRoomTypeAdjoiningRooms.click();
		// System.out.println("clicked on room type adjoining rooms check box");
		test_steps.add("clicked on room type adjoining rooms check box");

		// Click on Rooms
		js.executeScript("arguments[0].click(true);", roomClass.NewRoomClassRooms);

		// roomClass.NewRoomClassRooms.click();
		Utility.app_logs.info("Clicked on Rooms Tab");
		test_steps.add("Clicked on Rooms Tab");

		// Enter the Max Adults
		roomClass.NewRoomClassMaxAdults.sendKeys(maxAdults);
		Utility.app_logs.info("Entered the max audlts : " + maxAdults);
		test_steps.add("Entered the max audlts : " + maxAdults);

		// Enter the Max Persons
		roomClass.NewRoomClassMaxPersons.sendKeys(maxPersopns);
		Utility.app_logs.info("Entered the max persopns : " + maxPersopns);
		test_steps.add("Entered the max persopns : " + maxPersopns);

		// Enter the Rooms Quantity
		roomClass.NewRoomClassRoomQuantity.sendKeys(roomQuantity);
		Utility.app_logs.info("Enterd the rooms quantity : " + roomQuantity);
		test_steps.add("Enterd the rooms quantity : " + roomQuantity);

		// Click create Rooms
		roomClass.NewRoomClassCreateRooms.click();
		// System.out.println("clicked on create rooms");
		test_steps.add("clicked on create rooms");

		// Waiting for visibility of Room Number text box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_RoomNumber)));

		// Clear the Room Number text box
		roomClass.NewRoomClassRoomNumber.clear();

		// Enter for Room Numbers
		roomClass.NewRoomClassRoomNumber.sendKeys("501");
		Utility.app_logs.info("Entered the room number 501");
		test_steps.add("Entered the room number 501");

		// Waiting for visibility of Assign Room Number button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_AssignRoomNumber)));

		// click on Assign room number
		roomClass.NewRoomClassAssignRoomNumbers.click();
		Utility.app_logs.info("clicked on assign room numbers");
		test_steps.add("clicked on assign room numbers");

		// Waiting for visibility of Save
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Save)));

		// click on Save
		roomClass.NewRoomClassSave.click();
		Utility.app_logs.info("clicked on Save");
		test_steps.add("clicked on Save");

		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.Toaster_Message)), driver);
		String msg = driver.findElement(By.xpath(OR.Toaster_Message)).getText();
		System.out.println("Toaster Message appear : " + msg);
		driver.findElement(By.xpath(OR.Toaster_Message)).click();
		Wait.wait3Second();
		// Asserting the messag after publish
		assertTrue(msg.contains("Successfully Created Room Class : "));
		test_steps.add("Room Classed Asserted");

		// Waiting for visibility of publish
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Publish)));
		wait.until(ExpectedConditions.elementToBeClickable(roomClass.NewRoomClassPublish));

		// Clicking on publish
		roomClass.NewRoomClassPublish.click();
		Utility.app_logs.info("clicked on Publish");
		test_steps.add("clicked on Publish");
		// Waiting for the After publish Toaster messa
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.Toaster_Message)), driver);
		msg = driver.findElement(By.xpath(OR.Toaster_Message)).getText();
		System.out.println("Toaster Message appear : " + msg);
		driver.findElement(By.xpath(OR.Toaster_Message)).click();

		// Clicking on OK
		Wait.explicit_wait_visibilityof_webelement_120(roomClass.NewRoomClassOk, driver);
		Utility.ScrollToElement(roomClass.NewRoomClassOk, driver);
		roomClass.NewRoomClassOk.click();
		Utility.app_logs.info("Clicked on OK");
		test_steps.add("clicked on OK");

		// Click on Room Classes
		try {
			js.executeScript("arguments[0].click(true);", roomClass.NewRoomClasses_1);
		} catch (Exception e) {
			js.executeScript("arguments[0].click(true);", roomClass.NewRoomClasses);
		}

		// roomClass.NewRoomClasses.click();
		Utility.app_logs.info("Room Classes Clicked");
		test_steps.add("Room Classes Clicked");

		// Parsing the Room class name to get First character to upper case
		char ch = roomClassName.charAt(0);
		String str = "" + ch;
		str = str.toUpperCase();
		// System.out.println("String :" + str);

		// Waiting for visibility of Search Bar
		WebElement Search = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//ul[@class='pagination']/li/a[contains(text(),'" + str + "')]")));
		// driver.findElement(By.xpath("//ul[@class='pagination']/li/a[contains(text(),'"
		// + str + "')]")).click();
		js.executeScript("arguments[0].click(true);", Search);

		// Search.click();
		// Validating the newly created room class
		if (driver.findElements(By.xpath("//a[contains(text(),'" + roomClassName + "')]")).size() > 0) {
			Utility.app_logs.info("newly created class room validated successfully");
			test_steps.add("newly created class room validated successfully");

		} else {
			Utility.app_logs.info("new class room not successfully created");
			test_steps.add("new class room not successfully created");

		}
		return test_steps;
	}

	public ArrayList<String> UpdateroomClassInfo(WebDriver driver, String UpdateRoomClassName,
			ArrayList<String> test_steps) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 90);
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		// Enter Room Class Name
		roomClass.NewRoomClassName.clear();
		roomClass.NewRoomClassName.sendKeys(UpdateRoomClassName);
		Utility.app_logs.info("Entered the room class name : " + UpdateRoomClassName);
		test_steps.add("Update the room class name : " + UpdateRoomClassName);

		// Waiting for visibility of Save
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Save)));

		// click on Save
		roomClass.NewRoomClassSave.click();
		// System.out.println("clicked on Save");
		test_steps.add("Clicked on Save");
		Wait.explicit_wait_visibilityof_webelement_120(roomClass.Toaster_Message, driver);
		// Waiting for visibility of publish
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Publish)));
		wait.until(ExpectedConditions.elementToBeClickable(roomClass.NewRoomClassPublish));

		// Clicking on publish
		roomClass.NewRoomClassPublish.click();
		// System.out.println("clicked on Publish");
		test_steps.add("clicked on Publish");
		Wait.explicit_wait_visibilityof_webelement_120(roomClass.Toaster_Message, driver);
		roomclassLogger.info(roomClass.Toaster_Message.getText());

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(OR.New_RoomClass_Msg_Publish)));

		return test_steps;

	}

	public ArrayList<String> RoomClass_Info(WebDriver driver, String roomClassName, String roomClassAbbrivation,
			String bedsCount, String maxAdults, String maxPersopns, String roomQuantity, ExtentTest test,
			String RoomNumber, ArrayList<String> test_steps) throws Exception {

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		// Creating object for Elements_NewRoomClass
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);

		// Enter Room Class Name
		roomClass.NewRoomClassName.sendKeys(roomClassName);
		System.out.println("Entered the room class name : " + roomClassName);
		test_steps.add("Entered the room class name : " + roomClassName);

		// Enter the new class room abbreviation
		roomClass.NewRoomClassAbbrivation.sendKeys(roomClassAbbrivation);
		System.out.println("Entered the room class abbreviation : " + roomClassAbbrivation);
		test_steps.add("Entered the room class abbreviation : " + roomClassAbbrivation);

		// Enter the number of king bed count
		roomClass.NewRoomClassKingBeds.sendKeys(bedsCount);
		System.out.println("Entered the king beds count : " + bedsCount);
		test_steps.add("Entered the king beds count : " + bedsCount);

		// Java script object creation
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");

		// Waiting for visibility of adjoining rooms
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_RoomType_AdjoiningRooms)));

		// Clicking on adjoining rooms check box
		roomClass.New_RoomClassRoomTypeAdjoiningRooms.click();
		System.out.println("clicked on room type adjoining rooms check box");
		test_steps.add("clicked on room type adjoining rooms check box");

		// Click on Rooms
		roomClass.NewRoomClassRooms.click();
		System.out.println("Clicked on Rooms Tab");
		test_steps.add("Clicked on Rooms Tab");

		// Enter the Max Adults
		roomClass.NewRoomClassMaxAdults.sendKeys(maxAdults);
		System.out.println("Entered the max audlts : " + maxAdults);
		test_steps.add("Entered the max audlts : " + maxAdults);

		// Enter the Max Persons
		roomClass.NewRoomClassMaxPersons.sendKeys(maxPersopns);
		System.out.println("Entered the max persopns : " + maxPersopns);
		test_steps.add("Entered the max persopns : " + maxPersopns);

		// Enter the Rooms Quantity
		roomClass.NewRoomClassRoomQuantity.sendKeys(roomQuantity);
		System.out.println("Enterd the rooms quantity : " + roomQuantity);
		test_steps.add("Enterd the rooms quantity : " + roomQuantity);

		// Click create Rooms
		roomClass.NewRoomClassCreateRooms.click();
		System.out.println("clicked on create rooms");
		test_steps.add("clicked on create rooms");

		// Waiting for visibility of Room Number text box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_RoomNumber)));

		// Clear the Room Number text box
		roomClass.NewRoomClassRoomNumber.clear();

		// Enter for Room Numbers
		roomClass.NewRoomClassRoomNumber.sendKeys(RoomNumber);
		System.out.println("Entered the room number " + RoomNumber);
		test_steps.add("Entered the room number " + RoomNumber);
		// Validating the newly created room class
		if (driver.findElements(By.xpath("//a[contains(text(),'" + roomClassName + "')]")).size() > 0) {
			System.out.println("newly created class room validated successfully");
			test_steps.add("newly created class room validated successfully");

		} else {
			System.out.println("new class room not successfully created");
			test_steps.add("new class room not successfully created");

		}
		return test_steps;
	}

	public void AddVirtualRoom(WebDriver driver) throws Exception {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		roomClass.AddVirtualRoom.click();
		assertTrue(roomClass.CreateVirtualRoom_Text.getText().equals("Create Virtual Room"),
				"Failed:Virtual Room Class is not opened");

	}

	public void PhysicalRoomSearch(WebDriver driver, String RoomNumber, String Adults, String Persons)
			throws Exception {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		roomClass.CreateVirtualRoom_RoomNumber.sendKeys(RoomNumber);
		roomClass.CreateVirtualRoom_Adults.sendKeys(Adults);
		roomClass.CreateVirtualRoom_Persons.sendKeys(Persons);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", roomClass.CreateVirtualRoom_SearchButton);
		roomClass.CreateVirtualRoom_SearchButton.click();
		Thread.sleep(10000);
		assertTrue(roomClass.CreateVirtualRoom_PhysicalRoomBox.isDisplayed(),
				"Failed:Physical Rooms didn't search successfully");

	}

	public void DragPhyRoomToVirtualRoomSpace(WebDriver driver) throws Exception {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", roomClass.CreateVirtualRoom_PhysicalRoom_RoomList
				.get(roomClass.CreateVirtualRoom_PhysicalRoom_RoomList.size() - 1));
		Thread.sleep(10000);
		Actions action = new Actions(driver);
		WebElement From = driver.findElement(By.xpath(OR.CreateVirtualRoom_PhysicalRoom_RoomList));
		WebElement To = driver.findElement(By.xpath(OR.CreateVirtualRoom_VirtualRoomSpace));
		Thread.sleep(2000);
		// action.dragAndDrop(From, To).build().perform();
		Action drag = action.clickAndHold(From).moveToElement(To).click().release(To).build();
		Wait.wait2Second();

		drag.perform();
		Thread.sleep(2000);

	}

	public void DragPhyRoomTo_VirtualRoomSpace(WebDriver driver) throws Exception {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Wait.explicit_wait_visibilityof_webelement(roomClass.PhysicalRoom_RoomList, driver);
		jse.executeScript("arguments[0].scrollIntoView(true);", roomClass.PhysicalRoom_RoomList);
		Wait.wait2Second();
		int roomCount = driver.findElements(By.xpath(OR.PhysicalRoom_RoomList)).size();
		// System.out.println(roomCount);
		if (roomCount > 2) {
			for (int i = 1; i < 3; i++) {
				WebElement From = driver.findElement(By.xpath(OR.PhysicalRoom_RoomList));
				WebElement To = driver.findElement(By.xpath(OR.CreateVirtualRoom_VirtualRoomSpace));

				Actions act = new Actions(driver); // Dragged and dropped Action
				// act.dragAndDrop(From, To).perform();
				// act.dragAndDropBy(From, To.getLocation().getX(),
				// To.getLocation().getY()).build().perform();
				Actions builder = new Actions(driver);
				builder.moveToElement(From);
				System.out.print("Move to from");
				Wait.wait2Second();
				builder.clickAndHold(From).keyDown(Keys.CONTROL).moveToElement(To).keyUp(Keys.CONTROL);

				Action selectMultiple = builder.build();
				selectMultiple.perform();
				Wait.wait5Second();
				System.out.println("Dragged room");
			}
		}
	}

	public void VirtualRoomInfo(WebDriver driver, String VirtualRoomNumber, String SortOrder) throws Exception {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		roomClass.CreateVirtualRoom_VirtualRoomName.sendKeys(VirtualRoomNumber);
		roomClass.CreateVirtualRoom_VirtualSortOrder.sendKeys(SortOrder);
		roomClass.NewRoomClassSave.click();
		roomClass.NewRoomClassPublish.click();

	}

	public void SearchButtonClick(WebDriver driver) throws Exception {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.RoomClasses_SearchButton), driver);
		Utility.ScrollToElement(roomClass.RoomClasses_SearchButton, driver);
		roomClass.RoomClasses_SearchButton.click();
		Wait.waitForElementToBeClickable(By.xpath(OR.RoomClasses_TableShow), driver);
		assertTrue(roomClass.RoomClasses_TableShow.isDisplayed(), "Failed: RoomClasses Table not displayed");

	}

	public void SelectRoomClassesFromTable(WebDriver driver) throws Exception {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		roomClass.RoomClasses_FirstActiveClass.click();
		Wait.wait3Second();
		assertTrue(roomClass.RoomClasses_RoomDetailsPage.isDisplayed(), "Failed: Room Details Page not displayed");

	}

	public void EditRoomInfo(WebDriver driver, String SortOrder) throws Exception {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);

		Random rand = new Random();

		int n = rand.nextInt(50) + 1;
		int SortOrderInt = Integer.parseInt(SortOrder);
		SortOrderInt = (SortOrderInt + n);
		SortOrder = String.valueOf(SortOrderInt);
		roomClass.CreateVirtualRoom_VirtualSortOrder.clear();
		roomClass.CreateVirtualRoom_VirtualSortOrder.sendKeys(SortOrder);
		roomClass.NewRoomClassPublish.click();
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
		Wait.wait3Second();
		roomClass.RoomClasses_ChangesSaved_OKButton.click();

	}

	public void SavePublishRoomClass(WebDriver driver) throws Exception {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		roomClass.NewRoomClassSave.click();
		roomClass.NewRoomClassPublish.click();

	}

	public ArrayList<String> roomClassInfo1(WebDriver driver, String roomClassName, String roomClassAbbrivation,
			String bedsCount, String maxAdults, String maxPersopns, String roomQuantity, ExtentTest test,
			ArrayList<String> test_steps) throws Exception {

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		// Creating object for Elements_NewRoomClass
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);

		// Enter Room Class Name
		roomClass.NewRoomClassName.sendKeys(roomClassName);
		// System.out.println("Entered the room class name : " + roomClassName);
		test_steps.add("Entered the room class name : " + roomClassName);

		// Enter the new class room abbreviation
		roomClass.NewRoomClassAbbrivation.sendKeys(roomClassAbbrivation);
		// System.out.println("Entered the room class abbreviation : " +
		// roomClassAbbrivation);
		test_steps.add("Entered the room class abbreviation : " + roomClassAbbrivation);

		// Click on Rooms
		roomClass.NewRoomClassRooms.click();
		// System.out.println("Clicked on Rooms Tab");
		test_steps.add("Clicked on Rooms Tab");

		// Enter the Max Adults
		roomClass.NewRoomClassMaxAdults.sendKeys(maxAdults);

		test_steps.add("Entered the max audlts : " + maxAdults);

		// Enter the Max Persons
		roomClass.NewRoomClassMaxPersons.sendKeys(maxPersopns);

		// System.out.println("Entered the max persopns : " + maxPersopns);
		test_steps.add("Entered the max persons : " + maxPersopns);

		// Enter the Rooms Quantity
		roomClass.NewRoomClassRoomQuantity.sendKeys(roomQuantity);

		test_steps.add("Enterd the rooms quantity : " + roomQuantity);

		// Click create Rooms
		roomClass.NewRoomClassCreateRooms.click();
		// System.out.println("clicked on create rooms");
		test_steps.add("clicked on create rooms");

		// Waiting for visibility of Room Number text box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_RoomNumber)));

		// Clear the Room Number text box
		roomClass.NewRoomClassRoomNumber.clear();

		// Enter for Room Numbers
		roomClass.NewRoomClassRoomNumber.sendKeys("501");
		// System.out.println("Entered the room number 501");
		test_steps.add("Entered the room number 501");

		// Waiting for visibility of Assign Room Number button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_AssignRoomNumber)));

		// click on Assign room number
		roomClass.NewRoomClassAssignRoomNumbers.click();

		// System.out.println("clicked on assign room numbers");
		test_steps.add("Click Assign Room Number");

		// Waiting for visibility of Save
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Save)));

		// click on Save
		roomClass.NewRoomClassSave.click();
		test_steps.add("Click Save Room Class");
		// System.out.println("clicked on Save");
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		Wait.explicit_wait_visibilityof_webelement_120(roomClass.Toaster_Message, driver);
		test_steps.add(roomClass.Toaster_Message.getText());
		// Waiting for visibility of publish

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Publish)));
		wait.until(ExpectedConditions.elementToBeClickable(roomClass.NewRoomClassPublish));

		// Clicking on publish
		roomClass.NewRoomClassPublish.click();

		// System.out.println("clicked on Publish");
		test_steps.add("Click Publish Room Class");
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		Wait.explicit_wait_visibilityof_webelement_120(roomClass.Toaster_Message, driver);
		roomclassLogger.info(roomClass.Toaster_Message.getText());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Msg_Publish)));

		return test_steps;
	}

	public void AddChannelSpecificInformation(WebDriver driver, String ListingName, String PropertyTypeGrp) {
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		roomClass.ListingName.clear();
		roomClass.ListingName.sendKeys(ListingName);
		assertTrue(false);
		new Select(roomClass.PropertyTypeGroup).selectByVisibleText(PropertyTypeGrp);

	}

	public void Search_Delete_RoomClass(WebDriver driver, String RoomClassName) throws InterruptedException {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);

		int size = roomClass.RoomClassPages.size();
		String RoomPath = "//*[text()='" + RoomClassName + "']";
		boolean isGet = false;
		if (size < 3) {
			// single page
			WebElement RoomClass = driver.findElement(By.xpath(RoomPath));
			Utility.ScrollToElement(RoomClass, driver);
			isGet = true;
		} else {
			for (int i = 1; i <= size; i++) {
				try {
					WebElement RoomClass = driver.findElement(By.xpath(RoomPath));
					Utility.ScrollToElement(RoomClass, driver);
					// RoomClass.click();
					isGet = true;
					break;

				} catch (Exception e) {
					if (i < size) {
						Utility.ScrollToElement(roomClass.RoomClassPages.get(i), driver);
						roomClass.RoomClassPages.get(i).click();
						Wait.explicit_wait_visibilityof_webelement(roomClass.RoomClassPages.get(i), driver);
					}
				}
			}
		}

		if (isGet) {
			String RoomClsPath = "//*[text()='" + RoomClassName + "']//ancestor::tr//input";
			WebElement RoomClass = driver.findElement(By.xpath(RoomClsPath));
			Utility.ScrollToElement(RoomClass, driver);
			RoomClass.click();
			Wait.wait2Second();
			Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
			try {
				Utility.ScrollToElement(Navigate.DeleteRoomClass_Button, driver);
				Navigate.DeleteRoomClass_Button.click();
			} catch (Exception e) {
				Navigate.DeleteRoomClassesButton.get(1).click();
			}
			Wait.wait1Second();
			if (Navigate.RC_OK_Button.get(1).isDisplayed()) {
				Wait.explicit_wait_visibilityof_webelement_120(Navigate.RC_OK_Button.get(1), driver);
				Navigate.RC_OK_Button.get(1).click();
			}

			Wait.explicit_wait_visibilityof_webelement(Navigate.RoomClassesToaster, driver);
			System.out.println(Navigate.RoomClassesToaster.getText());

		}

	}

	// Old Layout
	public void SearchRoomClass(WebDriver driver, String roomClassName, boolean Open) throws InterruptedException {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		// Parsing the Room class name to get First character to upper case
		char ch = roomClassName.charAt(0);
		String str = "" + ch;
		str = str.toUpperCase();
		System.out.println("String :" + str);

		// Waiting for visibility of Search Bar
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Search;

		Search = driver.findElement(By.xpath("//ul[@class='pagination']/li/a[contains(text(),'" + str + "')]"));

		js.executeScript("arguments[0].click(true);", Search);

		SelectItems_PerPage(driver, "100");
		Utility.app_logs.info("Select 100 items per pages");
		Wait.explicit_wait_visibilityof_webelement(roomClass.RoomClass_Pages.get(0), driver);
		int size = roomClass.RoomClass_Pages.size();
		String RoomPath = "//*[text()='" + roomClassName + "']";
		boolean isGet = false;
		if (size < 3) {
			// single page
			WebElement RoomClass = driver.findElement(By.xpath(RoomPath));
			Utility.ScrollToElement(RoomClass, driver);
			isGet = true;
		} else {
			for (int i = 1; i <= size; i++) {
				try {
					WebElement RoomClass = driver.findElement(By.xpath(RoomPath));
					Utility.ScrollToElement(RoomClass, driver);
					if (Open) {
						RoomClass.click();
						Wait.explicit_wait_visibilityof_webelement(roomClass.NewRoomClassAbbrivation, driver);
					}
					isGet = true;
					break;
				} catch (Exception e) {
					if (i < size) {
						Utility.ScrollToElement(roomClass.RoomClassPages.get(i), driver);
						roomClass.RoomClass_Pages.get(i).click();
						Wait.explicit_wait_visibilityof_webelement(roomClass.RoomClassPages.get(i), driver);
					}
				}
			}
		}
		assertTrue(isGet, "failed: Room Class Not found");
	}

	public void VerifyDeleteRoomClass(WebDriver driver, String roomClassName) throws InterruptedException {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		// Parsing the Room class name to get First character to upper case
		char ch = roomClassName.charAt(0);
		String str = "" + ch;
		str = str.toUpperCase();
		System.out.println("String :" + str);

		// Waiting for visibility of Search Bar
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Search;

		Search = driver.findElement(By.xpath("//ul[@class='pagination']/li/a[contains(text(),'" + str + "')]"));

		// js.executeScript("arguments[0].click(true);", Search);
		Search.click();
		Utility.app_logs.info("Click Search ");
		boolean AnyRoomClass = true;
		try {
			Wait.explicit_wait_visibilityof_webelement(roomClass.Toaster_Title, driver);
			String Message = roomClass.Toaster_Title.getText();
			Utility.app_logs.info(Message);
			if (!Message.contains("No Room Classes Exist")) {
				try {
					roomClass.Toaster_Title.click();
					Utility.app_logs.info("Click Toaster Message");
				} catch (Exception j) {

				}
				Search.click();
				Wait.explicit_wait_visibilityof_webelement(roomClass.Toaster_Title, driver);
				Message = roomClass.Toaster_Title.getText();
				Utility.app_logs.info(Message);
			}
			if (Message.contains("No Room Classes Exist")) {
				Utility.app_logs.info(Message);
				AnyRoomClass = false;
			}
		} catch (Exception e) {

		}
		if (AnyRoomClass) {
			SelectItems_PerPage(driver, "100");
			Utility.app_logs.info("Select 100 items per pages");
			Wait.explicit_wait_visibilityof_webelement(roomClass.RoomClass_Pages.get(0), driver);
			int size = roomClass.RoomClass_Pages.size();
			boolean found = false;
			int element_size = 0;
			Utility.app_logs.info("Size : " + size);
			List<WebElement> ClassNames = null;
			if (size <= 3) {
				// single page
				element_size = driver.findElements(By.xpath(NewRoomClass.RoomClassList_Names)).size();
				if (element_size > 1) {
					ClassNames = driver.findElements(By.xpath(NewRoomClass.RoomClassList_Names));
					for (int j = 0; j < element_size; j++) {

						Utility.app_logs.info(ClassNames.get(j).getText());
						if (ClassNames.get(j).getText().equals(roomClassName)) {
							found = true;
							Utility.app_logs.info("RoomClass : " + roomClassName + " Found");
							break;
						}
					}

				}
			} else {
				for (int i = 1; i <= size; i++) {
					// code to
					element_size = driver.findElements(By.xpath(NewRoomClass.RoomClassList_Names)).size();
					if (element_size > 1) {
						ClassNames = driver.findElements(By.xpath(NewRoomClass.RoomClassList_Names));
						for (int j = 0; j < element_size; j++) {

							Utility.app_logs.info(ClassNames.get(j).getText());
							if (ClassNames.get(j).getText().equals(roomClassName)) {
								found = true;
								Utility.app_logs.info("RoomClass : " + roomClassName + " Found");
								break;
							}
						}

					}
					// code here
					if (found == false) {
						Utility.ScrollToElement(roomClass.RoomClassPages.get(i), driver);
						roomClass.RoomClass_Pages.get(i).click();
						Wait.explicit_wait_visibilityof_webelement(roomClass.RoomClassPages.get(i), driver);

					}
				}
			}

			assertTrue(!found, "failed: Room Class  found");
		}
	}

	public void Search_Open_RoomClass(WebDriver driver, String RoomClassName) throws InterruptedException {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);

		Wait.explicit_wait_visibilityof_webelement(roomClass.RoomClassPages.get(0), driver);
		int size = roomClass.RoomClassPages.size();
		String RoomPath = "//*[text()='" + RoomClassName + "']";
		boolean isGet = false;
		if (size < 3) {
			// single page
			WebElement RoomClass = driver.findElement(By.xpath(RoomPath));
			Utility.ScrollToElement(RoomClass, driver);
			isGet = true;
		} else {
			for (int i = 1; i <= size; i++) {
				try {
					WebElement RoomClass = driver.findElement(By.xpath(RoomPath));
					Utility.ScrollToElement(RoomClass, driver);
					RoomClass.click();
					Utility.app_logs.info("Click Room Class");
					Wait.explicit_wait_visibilityof_webelement(roomClass.NewRoomClassAbbrivation1, driver);
					isGet = true;
					break;

				} catch (Exception e) {
					if (i < size) {
						Utility.ScrollToElement(roomClass.RoomClassPages.get(i), driver);
						roomClass.RoomClassPages.get(i).click();
						Wait.explicit_wait_visibilityof_webelement(roomClass.RoomClassPages.get(i), driver);
					}
				}
			}
		}
		assertTrue(isGet, "failed: Room Class Not found");
	}

	public void SelectItemsPerPage(WebDriver driver) throws InterruptedException {
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		Wait.explicit_wait_visibilityof_webelement(roomClass.RoomClassItemsPerpage, driver);
		Utility.ScrollToElement(roomClass.RoomClassItemsPerpage, driver);
		roomClass.RoomClassItemsPerpage.click();
		Wait.explicit_wait_visibilityof_webelement_150(roomClass.RoomClassItemsPerpage_Select100, driver);
		Utility.ScrollToElement(roomClass.RoomClassItemsPerpage_Select100, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click(true);", roomClass.RoomClassItemsPerpage_Select100);
		String FirstSelectedOption = new Select(roomClass.SelectedItems_PerPage).getFirstSelectedOption().getText();
		System.out.println("Value :" + FirstSelectedOption);
		// roomClass.RoomClassItemsPerpage_Select100.click();
		Wait.wait2Second();
		try {
			assertEquals(roomClass.SelectedItemsPerPage.getText(), "100",
					"Failed: Room Class 100Items per Page Not selected");
		} catch (Exception e) {
			assertEquals(FirstSelectedOption, "100", "Failed: Room Class 100Items per Page Not selected");
		}
	}

	public void SelectItems_PerPage(WebDriver driver, String Items) throws InterruptedException {
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		Wait.explicit_wait_visibilityof_webelement(roomClass.SelectedItems_PerPage, driver);
		Utility.ScrollToElement(roomClass.SelectedItems_PerPage, driver);
		Select select = new Select(roomClass.SelectedItems_PerPage);
		select.selectByVisibleText(Items);
		Wait.wait2Second();
		System.out.println("Text:" + select.getFirstSelectedOption().getText());
		System.out.println("Value:" + select.getFirstSelectedOption().getAttribute("value"));

		assertEquals(select.getFirstSelectedOption().getText(), Items,
				"Failed: Room Class 100Items per Page Not selected");
	}

	@Override
	public boolean roomClassInfo(WebDriver driver, String roomClassName, String roomClassAbbrivation, String bedsCount,
			String maxAdults, String maxPersopns, String roomQuantity, ExtentTest test)
			throws InterruptedException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<String> CloseOpenedRoomClass(WebDriver driver, String roomClassName, ArrayList<String> test_steps)
			throws Exception {
		// TODO Auto-generated method stub
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		// Wait.explicit_wait_visibilityof_webelement(roomClass.Close_RoomClass,
		// driver);
		try {
			Wait.waitForElementToBeClickable(By.xpath(OR.Close_RoomClass), driver);
			Utility.ScrollToElement(roomClass.Close_RoomClass, driver);
			roomClass.Close_RoomClass.click();
		} catch (Exception e) {
			Wait.waitForElementToBeClickable(By.xpath(OR.Close_RoomClass_1), driver);
			Utility.ScrollToElement(roomClass.Close_RoomClass_1, driver);
			roomClass.Close_RoomClass_1.click();
		}
		test_steps.add("Close Opened Room Class");
		Utility.app_logs.info("Close Opened Room Class");
		return test_steps;

	}

	public ArrayList<String> CreateRoomClass(WebDriver driver, String roomClassName, String roomClassAbbrivation,
			String bedsCount, String maxAdults, String maxPersopns, String roomQuantity, ExtentTest test,
			ArrayList<String> test_steps) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 90);
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.New_RoomClass_Name), driver);
		roomClass.NewRoomClassName.sendKeys(roomClassName);
		roomclassLogger.info("Entered the room class name : " + roomClassName);
		test_steps.add("Enter room class name : <b>" + roomClassName + "</b>");
		roomClass.NewRoomClassAbbrivation.sendKeys(roomClassAbbrivation);
		roomclassLogger.info("Entered the room class abbreviation : " + roomClassAbbrivation);
		test_steps.add("Enter room class abbreviation : <b>" + roomClassAbbrivation + "</b>");
		roomClass.NewRoomClassRooms.click();
		roomclassLogger.info("Clicked on Rooms Tab");
		test_steps.add("Click Rooms Tab");
		roomClass.NewRoomClassMaxAdults.sendKeys(maxAdults);
		roomclassLogger.info("Entered the max audlts : " + maxAdults);
		test_steps.add("Enter max audlts : <b>" + maxAdults + "</b>");
		roomClass.NewRoomClassMaxPersons.sendKeys(maxPersopns);
		roomclassLogger.info("Entered the max persons : " + maxPersopns);
		test_steps.add("Enter max persons : <b>" + maxPersopns + "</b>");
		roomClass.NewRoomClassRoomQuantity.sendKeys(roomQuantity);
		roomclassLogger.info("Enterd the rooms quantity : " + roomQuantity);
		test_steps.add("Enter rooms quantity : <b>" + roomQuantity + "</b>");
		Wait.waitForElementToBeClickable(By.xpath(OR.New_RoomClass_Create_Rooms), driver);
		Utility.ScrollToElement(roomClass.NewRoomClassCreateRooms, driver);
		roomClass.NewRoomClassCreateRooms.click();
		roomclassLogger.info("clicked on create rooms");
		test_steps.add("Click create rooms");
		Wait.waitForElementToBeVisibile(By.xpath(OR.New_RoomClass_RoomNumber), driver);
		roomClass.NewRoomClassRoomNumber.clear();
		String RN = "1";
		// Utility.RoomNo=RN;
		// System.out.println("Room NO Is: "+Utility.RoomNo);
		roomClass.NewRoomClassRoomNumber.sendKeys("1");
		Utility.RoomNo = "1";
		roomclassLogger.info("Entered the room number " + RN);
		test_steps.add("Enter  room number <b>" + RN + "</b>");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_AssignRoomNumber)));
		roomClass.NewRoomClassAssignRoomNumbers.click();
		test_steps.add("Click Assign room numbers");
		Wait.waitForElementToBeVisibile(By.xpath(OR.New_RoomClass_Save), driver);
		roomClass.NewRoomClassSave.click();
		roomclassLogger.info("clicked on Save Room");
		test_steps.add("Click Save Room");
		Wait.waitForElementToBeVisibile(By.className(OR.Toaster_Message), driver);
		roomClass.Toaster_Message_Close.click();
		roomclassLogger.info("Closed save toaster");
		Wait.waitForElementToBeClickable(By.xpath(OR.New_RoomClass_Publish), driver);
		roomClass.NewRoomClassPublish.click();
		roomclassLogger.info("Clicked on Publish");
		test_steps.add("Click Publish  Room");
		// Wait.waitForElementToBeVisibile(By.className(OR.Toaster_Message),
		// driver);
		roomclassLogger.info(roomClass.NewRoomClassesMsgAfterPublish.getText());
		Wait.waitForElementToBeVisibile(By.xpath(OR.New_RoomClass_Msg_Publish), driver);
		return test_steps;
	}

	public ArrayList<String> Create_RoomClass(WebDriver driver, String roomClassName, String roomClassAbbrivation,
			String bedsCount, String maxAdults, String maxPersopns, String roomQuantity, ExtentTest test,
			ArrayList<String> test_steps) throws Exception {

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		roomClass.NewRoomClassName.sendKeys(roomClassName);
		roomclassLogger.info("Entered the room class name : " + roomClassName);
		test_steps.add("Enter room class name : " + roomClassName);
		roomClass.NewRoomClassAbbrivation.sendKeys(roomClassAbbrivation);
		roomclassLogger.info("Entered the room class abbreviation : " + roomClassAbbrivation);
		test_steps.add("Enter room class abbreviation : " + roomClassAbbrivation);
		// roomClass.NewRoomClassKingBeds.sendKeys(bedsCount);
		// System.out.println("Entered the king beds count : " + bedsCount);
		// test_steps.add("Entered the king beds count : " + bedsCount);
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0,1000)");
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_RoomType_AdjoiningRooms)));
		// roomClass.New_RoomClassRoomTypeAdjoiningRooms.click();
		// System.out.println("clicked on room type adjoining rooms check box");
		// test_steps.add("clicked on room type adjoining rooms check box");
		roomClass.NewRoomClassRooms.click();
		roomclassLogger.info("Clicked on Rooms Tab");
		test_steps.add("Click Rooms Tab");
		roomClass.NewRoomClassMaxAdults.sendKeys(maxAdults);
		roomclassLogger.info("Entered the max audlts : " + maxAdults);
		test_steps.add("Enter max audlts : " + maxAdults);
		roomClass.NewRoomClassMaxPersons.sendKeys(maxPersopns);
		roomclassLogger.info("Entered the max persons : " + maxPersopns);
		test_steps.add("Enter max persons : " + maxPersopns);
		roomClass.NewRoomClassRoomQuantity.sendKeys(roomQuantity);
		roomclassLogger.info("Enterd the rooms quantity : " + roomQuantity);
		test_steps.add("Enter rooms quantity : " + roomQuantity);
		Wait.explicit_wait_visibilityof_webelement(roomClass.NewRoomClassCreateRooms, driver);
		Wait.explicit_wait_elementToBeClickable(roomClass.NewRoomClassCreateRooms, driver);
		Utility.ScrollToElement(roomClass.NewRoomClassCreateRooms, driver);
		roomClass.NewRoomClassCreateRooms.click();
		roomclassLogger.info("clicked on create rooms");
		test_steps.add("Click create rooms");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_RoomNumber)));
		roomClass.NewRoomClassRoomNumber.clear();
		String RN = Utility.GenerateRandomNumber();
		roomClass.NewRoomClassRoomNumber.sendKeys(RN);
		roomclassLogger.info("Entered the room number " + RN);
		test_steps.add("Enter  room number " + RN);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_AssignRoomNumber)));
		roomClass.NewRoomClassAssignRoomNumbers.click();
		test_steps.add("Click Assign room numbers");

		// Waiting for visibility of Save
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Save)));
		// roomClass.NewRoomClassSave.click();
		// roomclassLogger.info("clicked on Save Room");
		// test_steps.add("Click Save Room");
		// Wait.WaitForElement(driver, OR.Toaster_Message);
		try {
			Wait.explicit_wait_visibilityof_webelement(roomClass.Toaster_Message, driver);
		} catch (Exception e) {

			roomclassLogger.info("NO Toaster Found");
		}
		// roomclassLogger.info(roomClass.Toaster_Message.getText());
		// Waiting for visibility of publish
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Publish)));
		wait.until(ExpectedConditions.elementToBeClickable(roomClass.NewRoomClassPublish));
		roomClass.NewRoomClassPublish.click();
		roomclassLogger.info("Clicked on Publish");
		test_steps.add("Click Publish  Room");

		try {
			Wait.explicit_wait_visibilityof_webelement(roomClass.Toaster_Message, driver);
			roomclassLogger.info(roomClass.Toaster_Message.getText());
		} catch (Exception e) {

			roomclassLogger.info("NO Toaster Found");
		}

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_OK)));
		/*
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.
		 * New_RoomClass_Msg_Publish)));
		 * roomclassLogger.info(driver.findElement(By.xpath(OR.
		 * New_RoomClass_Msg_Publish)).getText());
		 */ // roomClass.NewRoomClassOk.click();
			// System.out.println("Clicked on OK");
			// test_steps.add("clicked on OK");
			// //String msg =
			// driver.findElement(By.xpath(OR.New_RoomClass_Msg_Publish)).getText();

		// Waiting for invisibility of publish msg
		// try{
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(OR.New_RoomClass_Msg_Publish)));
		// }catch(Exception e){
		// if(roomClass.NewRoomClassOk.isDisplayed()){
		// roomClass.NewRoomClassOk.click();
		// System.out.println("Clicked on OK");
		// test_steps.add("clicked on OK");
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(OR.New_RoomClass_Msg_Publish)));
		// }else{
		// System.out.println("Assertion occur");
		// assertTrue(false);
		// }
		// }
		// CloseOpenedRoomClass(driver, roomClassName, test_steps);
		// SearchRoomClass(driver, roomClassName, false);
		return test_steps;
	}

	public boolean VerifyRoomClassExist(WebDriver driver, String roomClassName) throws InterruptedException {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		// Parsing the Room class name to get First character to upper case
		char ch = roomClassName.charAt(0);
		String str = "" + ch;
		str = str.toUpperCase();
		System.out.println("String :" + str);

		// Waiting for visibility of Search Bar
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Search;

		Search = driver.findElement(By.xpath("//ul[@class='pagination']/li/a[contains(text(),'" + str + "')]"));

		js.executeScript("arguments[0].click(true);", Search);

		SelectItems_PerPage(driver, "100");
		Utility.app_logs.info("Select 100 items per pages");
		Wait.explicit_wait_visibilityof_webelement(roomClass.RoomClass_Pages.get(0), driver);

		String RoomPath = "//*[text()='" + roomClassName + "']";
		boolean isGet = false;
		List<WebElement> RoomClass = driver.findElements(By.xpath(RoomPath));
		if (RoomClass.size() > 1) {
			Utility.app_logs.info("in if");
			String path_Status = "//*[text()='" + roomClassName
					+ "']//..//..//td//span[contains(@data-bind,'text: Status')]";
			String path_Publish = "//*[text()='" + roomClassName
					+ "']//..//..//td//span[contains(@data-bind,'text: Published')]";
			WebElement element_Status = driver.findElement(By.xpath(path_Status));
			WebElement element_Publishe = driver.findElement(By.xpath(path_Publish));
			Utility.app_logs.info("Publish: " + element_Publishe.getText());
			Utility.app_logs.info("Status: " + element_Status.getText());
			if (!element_Status.getText().contains("Active") || !element_Publishe.getText().contains("Yes")) {

				String path_checkbox = "//*[text()='" + roomClassName + "']//..//..//td//input";
				WebElement element_Checkbox = driver.findElement(By.xpath(path_checkbox));
				element_Checkbox.click();
				Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
				try {
					Utility.ScrollToElement(Navigate.DeleteRoomClass_Button, driver);
					Navigate.DeleteRoomClass_Button.click();
				} catch (Exception e) {
					Navigate.DeleteRoomClassesButton.get(1).click();
				}
				Wait.wait1Second();
				if (Navigate.RC_OK_Button.get(1).isDisplayed()) {
					Wait.explicit_wait_visibilityof_webelement_120(Navigate.RC_OK_Button.get(1), driver);
					Navigate.RC_OK_Button.get(1).click();
				}
				try {
					Wait.explicit_wait_visibilityof_webelement(Navigate.RoomClassesToaster, driver);
					System.out.println(Navigate.RoomClassesToaster.getText());
				} catch (Exception e) {
					// TODO: handle exception
				}

			} else {
				System.out.println("in else");
				isGet = true;
			}
		}

		return isGet;
	}

	public ArrayList<String> RoomClass_150Rooms(WebDriver driver, String roomClassName, String roomClassAbbrivation,
			String bedsCount, String maxAdults, String maxPersopns, String roomQuantity, ExtentTest test,
			ArrayList<String> test_steps) throws Exception {

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		// Creating object for Elements_NewRoomClass
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);

		// Enter Room Class Name
		roomClass.NewRoomClassName.sendKeys(roomClassName);
		// System.out.println("Entered the room class name : " + roomClassName);
		test_steps.add("Entered the room class name : " + roomClassName);

		// Enter the new class room abbreviation
		roomClass.NewRoomClassAbbrivation.sendKeys(roomClassAbbrivation);
		// System.out.println("Entered the room class abbreviation : " +
		// roomClassAbbrivation);
		test_steps.add("Entered the room class abbreviation : " + roomClassAbbrivation);

		// Click on Rooms
		roomClass.NewRoomClassRooms.click();
		// System.out.println("Clicked on Rooms Tab");
		test_steps.add("Clicked on Rooms Tab");

		// Enter the Max Adults
		roomClass.NewRoomClassMaxAdults.sendKeys(maxAdults);

		test_steps.add("Entered the max audlts : " + maxAdults);

		// Enter the Max Persons
		roomClass.NewRoomClassMaxPersons.sendKeys(maxPersopns);

		// System.out.println("Entered the max persopns : " + maxPersopns);
		test_steps.add("Entered the max persons : " + maxPersopns);

		// Enter the Rooms Quantity
		roomClass.NewRoomClassRoomQuantity.sendKeys(roomQuantity);

		test_steps.add("Enterd the rooms quantity : " + roomQuantity);

		// Click create Rooms
		roomClass.NewRoomClassCreateRooms.click();
		// System.out.println("clicked on create rooms");
		test_steps.add("clicked on create rooms");

		// Waiting for visibility of Room Number text box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_RoomNumber)));

		// Clear the Room Number text box
		// roomClass.NewRoomClassRoomNumber.clear();

		// Enter for Room Numbers
		int roomNumber = 1;
		for (int i = 0; i < roomClass.LIst_NewRoomClassRoomNumber.size(); i++) {
			if (i < 75) {
				roomClass.LIst_NewRoomClassRoomNumber.get(i).sendKeys("" + roomNumber);
				Utility.ScrollToElement_roomClass(roomClass.LIst_NewRoomClassRoomNumber.get(i), driver);
				roomNumber++;
			}
			if (i > 224) {

				roomClass.LIst_NewRoomClassRoomNumber.get(i).sendKeys("" + roomNumber);
				Utility.ScrollToElement_roomClass(roomClass.LIst_NewRoomClassRoomNumber.get(i), driver);
				roomNumber++;
			}

		}

		// System.out.println("Entered the room number 501");
		test_steps.add("Entered the room number");

		// Waiting for visibility of Assign Room Number button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_AssignRoomNumber)));

		// click on Assign room number
		Utility.ScrollToElement(roomClass.NewRoomClassAssignRoomNumbers, driver);
		roomClass.NewRoomClassAssignRoomNumbers.click();

		// System.out.println("clicked on assign room numbers");
		test_steps.add("Click Assign Room Number");

		// Waiting for visibility of Save
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Save)));

		/*
		 * // click on Save roomClass.NewRoomClassSave.click();
		 * test_steps.add("Click Save Room Class"); //
		 * System.out.println("clicked on Save");
		 * Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		 * Wait.explicit_wait_visibilityof_webelement_120(roomClass.
		 * Toaster_Message, driver);
		 * test_steps.add(roomClass.Toaster_Message.getText()); // Waiting for
		 * visibility of publish
		 * 
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.
		 * New_RoomClass_Publish)));
		 * wait.until(ExpectedConditions.elementToBeClickable(roomClass.
		 * NewRoomClassPublish));
		 */
		// Clicking on publish
		roomClass.NewRoomClassPublish.click();

		// System.out.println("clicked on Publish");
		test_steps.add("Click Publish Room Class");
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		Wait.explicit_wait_visibilityof_webelement_120(roomClass.Toaster_Message, driver);
		roomclassLogger.info(roomClass.Toaster_Message.getText());
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Msg_Publish)));

		return test_steps;
	}

	public List<String> Abbreviation = new ArrayList<String>();
	public String abb = null;

	public ArrayList getRoomClassAbbrivations(WebDriver driver, ArrayList<String> test_steps, ArrayList<String> al,
			String Roomclass) throws InterruptedException {
		Elements_NewRoomClass ele = new Elements_NewRoomClass(driver);

		String all = "//a[text()='All']";
		Wait.WaitForElement(driver, all);
		Wait.wait2Second();
		driver.findElement(By.xpath(all)).click();
		Wait.wait5Second();
		Wait.WaitForElement(driver, OR_Setup.Roomclass_Pagenation);
		new Select(ele.Roomclass_Pagenation).selectByVisibleText("100");
		String[] rc = Roomclass.split("\\|");
		int count = rc.length;
		String room = null;
		String abbri = null;

		for (int i = 1; i <= count; i++) {
			room = "//a[text()='" + rc[i - 1] + "']";
			Wait.WaitForElement(driver, room);
			Wait.wait2Second();
			driver.findElement(By.xpath(room)).click();
			abbri = "//input[contains(@data-bind,'value: RoomclassAbrv,')]";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Wait.WaitForElement(driver, abbri);
			abb = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(abbri)));
			Abbreviation.add(abb);
			test_steps.add("getting abbrivation for room class : " + rc[i - 1] + " is : " + abb);
			roomclassLogger.info("getting abbrivation for room class : " + rc[i - 1] + " is : " + abb);
			al.add(rc[i - 1] + ":" + abb);
			String close = "//ul[@class='sec_nav']/li";
			int size = driver.findElements(By.xpath(close)).size();
			close = "//ul[@class='sec_nav']/li[" + size + "]//i";
			Wait.WaitForElement(driver, close);
			driver.findElement(By.xpath(close)).click();
		}
		return al;
	}

	public ArrayList<String> CreateRoomClass(WebDriver driver, String roomClassName, String roomClassAbbrivation,
			String bedsCount, String maxAdults, String maxPersopns, String roomQuantity, String RoomNumber,
			ArrayList<String> test_steps) throws Exception {

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		roomClass.NewRoomClassName.sendKeys(roomClassName);
		roomclassLogger.info("Entered the room class name : " + roomClassName);
		test_steps.add("Enter room class name : " + roomClassName);
		roomClass.NewRoomClassAbbrivation.sendKeys(roomClassAbbrivation);
		roomclassLogger.info("Entered the room class abbreviation : " + roomClassAbbrivation);
		test_steps.add("Enter room class abbreviation : " + roomClassAbbrivation);
		// roomClass.NewRoomClassKingBeds.sendKeys(bedsCount);
		// System.out.println("Entered the king beds count : " + bedsCount);
		// test_steps.add("Entered the king beds count : " + bedsCount);
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0,1000)");
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_RoomType_AdjoiningRooms)));
		// roomClass.New_RoomClassRoomTypeAdjoiningRooms.click();
		// System.out.println("clicked on room type adjoining rooms check box");
		// test_steps.add("clicked on room type adjoining rooms check box");
		roomClass.NewRoomClassRooms.click();
		roomclassLogger.info("Clicked on Rooms Tab");
		test_steps.add("Click Rooms Tab");
		roomClass.NewRoomClassMaxAdults.sendKeys(maxAdults);
		roomclassLogger.info("Entered the max audlts : " + maxAdults);
		test_steps.add("Enter max audlts : " + maxAdults);
		roomClass.NewRoomClassMaxPersons.sendKeys(maxPersopns);
		roomclassLogger.info("Entered the max persons : " + maxPersopns);
		test_steps.add("Enter max persons : " + maxPersopns);
		roomClass.NewRoomClassRoomQuantity.sendKeys(roomQuantity);
		roomclassLogger.info("Enterd the rooms quantity : " + roomQuantity);
		test_steps.add("Enter rooms quantity : " + roomQuantity);
		Wait.explicit_wait_visibilityof_webelement(roomClass.NewRoomClassCreateRooms, driver);
		Wait.explicit_wait_elementToBeClickable(roomClass.NewRoomClassCreateRooms, driver);
		Utility.ScrollToElement(roomClass.NewRoomClassCreateRooms, driver);
		roomClass.NewRoomClassCreateRooms.click();
		roomclassLogger.info("clicked on create rooms");
		test_steps.add("Click create rooms");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_RoomNumber)));
		roomClass.NewRoomClassRoomNumber.clear();
		Utility.RoomNo = RoomNumber;
		System.out.println("Room NO Is: " + Utility.RoomNo);
		roomClass.NewRoomClassRoomNumber.sendKeys(RoomNumber);
		roomclassLogger.info("Entered the room number " + RoomNumber);
		test_steps.add("Enter  room number " + RoomNumber);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_AssignRoomNumber)));
		roomClass.NewRoomClassAssignRoomNumbers.click();
		test_steps.add("Click Assign room numbers");

		// Waiting for visibility of Save
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Save)));
		// roomClass.NewRoomClassSave.click();
		// roomclassLogger.info("clicked on Save Room");
		// test_steps.add("Click Save Room");
		// Wait.WaitForElement(driver, OR.Toaster_Message);
		// Wait.explicit_wait_visibilityof_webelement_120(roomClass.Toaster_Message,
		// driver);
		// roomclassLogger.info(roomClass.Toaster_Message.getText());
		// Waiting for visibility of publish
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Publish)));
		// wait.until(ExpectedConditions.elementToBeClickable(roomClass.NewRoomClassPublish));
		Wait.waitForElementToBeVisibile(By.xpath(OR.New_RoomClass_Publish), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.New_RoomClass_Publish), driver);
		roomClass.NewRoomClassPublish.click();
		roomclassLogger.info("Clicked on Publish");
		test_steps.add("Click Publish  Room");

		Wait.waitForElementToBeVisibile(By.className(OR.Toaster_Message), driver);
		Wait.waitForElementToBeClickable(By.className(OR.Toaster_Message), driver);
		// Wait.explicit_wait_visibilityof_webelement_120(roomClass.Toaster_Message,
		// driver);
		roomclassLogger.info(roomClass.Toaster_Message.getText());
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Msg_Publish)));
		roomclassLogger.info(driver.findElement(By.xpath(OR.New_RoomClass_Msg_Publish)).getText());
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(OR.New_RoomClass_Msg_Publish)));
		return test_steps;
	}

	public boolean SearchClasses(WebDriver driver, String roomClassName) throws InterruptedException {
		boolean isRoomClassShowing = false;
		try {
			Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
			char ch = roomClassName.charAt(0);
			String str = "" + ch;
			WebElement Search;

			Search = driver.findElement(By.xpath("//ul[@class='pagination']/li/a[contains(text(),'" + str + "')]"));
			Search.click();
			try {
				Wait.explicit_wait_visibilityof_webelement(roomClass.Toaster_Message, driver);
				System.out.println("message: " + roomClass.Toaster_Message.getText());
				System.out.println("title: " + roomClass.Toaster_Title.getText());

			} catch (Exception e) {
				isRoomClassShowing = true;
				SelectItems_PerPage(driver, "100");
				Utility.app_logs.info("Select 100 items per pages");
				Wait.explicit_wait_visibilityof_webelement(roomClass.RoomClass_Pages.get(0), driver);
			}

		} catch (Exception e) {

		}

		return isRoomClassShowing;
	}

	public ArrayList<String> Delete_RoomClass(WebDriver driver, String RoomClass) throws InterruptedException {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		String RoomClassPath = "//a[starts-with(text(),'" + RoomClass + "')]//..//following-sibling::td//input";
		List<WebElement> elementRoomClasses_Checkbox = driver.findElements(By.xpath(RoomClassPath));
		int size = roomClass.RoomClassPages.size();

		if (size < 3) {
			Wait.explicit_wait_visibilityof_webelement(elementRoomClasses_Checkbox.get(0), driver);
			Wait.explicit_wait_elementToBeClickable(elementRoomClasses_Checkbox.get(0), driver);
			for (int i = 0; i < elementRoomClasses_Checkbox.size(); i++) {
				Utility.ScrollToElement_NoWait(elementRoomClasses_Checkbox.get(i), driver);
				elementRoomClasses_Checkbox.get(i).click();
			}
		} else {
			for (int j = 0; j < roomClass.RoomClassPages.size(); j++) {
				Utility.ScrollToElement(roomClass.RoomClassPages.get(j), driver);
				roomClass.RoomClassPages.get(j).click();
				for (int i = 0; i < elementRoomClasses_Checkbox.size(); i++) {
					Wait.explicit_wait_visibilityof_webelement(elementRoomClasses_Checkbox.get(i), driver);
					Wait.explicit_wait_elementToBeClickable(elementRoomClasses_Checkbox.get(i), driver);
					Utility.ScrollToElement_NoWait(elementRoomClasses_Checkbox.get(i), driver);
					elementRoomClasses_Checkbox.get(i).click();
				}
			}

		}
		roomClass.DeleteRoomClass_Button.click();
		Wait.explicit_wait_visibilityof_webelement_120(roomClass.Toaster_Message, driver);
		roomclassLogger.info(roomClass.Toaster_Message.getText());
		test_steps.add(roomClass.Toaster_Message.getText());
		return test_steps;

	}

	public ArrayList<String> deleteRoomClassIfExist(WebDriver driver, String roomClass, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_NewRoomClass room = new Elements_NewRoomClass(driver);
		Wait.wait2Second();
		String roomClassPath = "//*[contains(text(),'" + roomClass + "')]//ancestor::tr//input";
		List<WebElement> listSiz = driver.findElements(By.xpath(roomClassPath));
		boolean isRoomCreated = false;
		System.out.println("Total listSiz.size(): " + listSiz.size());

		if (listSiz.size() > 0) {
			test_steps.add(listSiz.size() + " RoomClass already exist with a name: " + roomClass);
			isRoomCreated = true;
			int count = 0;
			while (count < listSiz.size()) {
				System.out.println("Count Now: " + count);

				roomClassPath = "//*[contains(text(),'" + roomClass + "')]//ancestor::tr//input";
				WebElement roomClassElement = driver.findElement(By.xpath(roomClassPath));
				System.out.println("listSiz.get(i): " + roomClassElement);

				Utility.ScrollToElement(roomClassElement, driver);
				driver.findElement(By.xpath(roomClassPath)).click();
				Wait.wait3Second();

				Wait.explicit_wait_elementToBeClickable(room.DeleteRoomClass_Button, driver);
				room.DeleteRoomClass_Button.click();
				roomclassLogger.info(" Sucessfully deleted the Roomclass: " + roomClass);
				test_steps.add(" Sucessfully deleted the Roomclass " + roomClass);
				count++;
			}
		} else {
			test_steps.add("Room Class with a name " + roomClass + " does not exist");
		}

		return test_steps;
	}

	public void closeRoomClassTab(WebDriver driver) throws InterruptedException {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		Utility.ScrollToElement_NoWait(roomClass.Close_RoomClass, driver);
		roomClass.Close_RoomClass.click();

	}

	public boolean searchClass(WebDriver driver, String roomClassName) throws InterruptedException {

		char ch = roomClassName.charAt(0);
		String str = "" + ch;
		String searchPath = "//ul[@class='pagination']/li/a[contains(text(),'" + str + "')]";
		WebElement elementSearch = driver.findElement(By.xpath(searchPath));
		elementSearch.click();

		Wait.WaitForElement(driver, searchPath);
		Wait.waitForElementToBeVisibile(By.xpath(searchPath), driver);
		Wait.waitForElementToBeClickable(By.xpath(searchPath), driver);

		String path = "//div[@class='table-responsive']//table[contains(@data-bind,'visible: roomClassList')]";
		WebElement element = driver.findElement(By.xpath(path));
		String style = element.getAttribute("style");
		boolean isRoomClassShowing = false;
		if (!style.equals("display: none;")) {
			System.out.println("showing");
			isRoomClassShowing = true;
			// SelectItems_PerPage(driver, "100");
		} else {
			System.out.println("Not showing");
		}

		return isRoomClassShowing;
	}

	public ArrayList<String> createRoomClass(WebDriver driver, String roomClassName, String roomClassAbbrivation,
			String bedsCount, String maxAdults, String maxPersopns, String roomQuantity, ExtentTest test,
			ArrayList<String> test_steps) throws Exception {

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		Wait.WaitForElement(driver, OR.New_RoomClass_Name);
		Wait.waitForElementToBeVisibile(By.xpath(OR.New_RoomClass_Name), driver);
		roomClass.NewRoomClassName.sendKeys(roomClassName);
		roomclassLogger.info("Entered the room class name : " + roomClassName);
		test_steps.add("Enter room class name : " + roomClassName);
		roomClass.NewRoomClassAbbrivation.sendKeys(roomClassAbbrivation);
		roomclassLogger.info("Entered the room class abbreviation : " + roomClassAbbrivation);
		test_steps.add("Enter room class abbreviation : " + roomClassAbbrivation);

		roomClass.NewRoomClassRooms.click();
		roomclassLogger.info("Clicked on Rooms Tab");
		test_steps.add("Click Rooms Tab");
		roomClass.NewRoomClassMaxAdults.sendKeys(maxAdults);
		roomclassLogger.info("Entered the max audlts : " + maxAdults);
		test_steps.add("Enter max audlts : " + maxAdults);
		roomClass.NewRoomClassMaxPersons.sendKeys(maxPersopns);
		roomclassLogger.info("Entered the max persons : " + maxPersopns);
		test_steps.add("Enter max persons : " + maxPersopns);
		roomClass.NewRoomClassRoomQuantity.sendKeys(roomQuantity);
		roomclassLogger.info("Enterd the rooms quantity : " + roomQuantity);
		test_steps.add("Enter rooms quantity : " + roomQuantity);

		Wait.explicit_wait_visibilityof_webelement(roomClass.NewRoomClassCreateRooms, driver);
		Wait.explicit_wait_elementToBeClickable(roomClass.NewRoomClassCreateRooms, driver);
		Utility.ScrollToElement(roomClass.NewRoomClassCreateRooms, driver);
		roomClass.NewRoomClassCreateRooms.click();
		roomclassLogger.info("clicked on create rooms");
		test_steps.add("Click create rooms");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_RoomNumber)));
		roomClass.NewRoomClassRoomNumber.clear();
		String RN = Utility.GenerateRandomNumber();
		Utility.RoomNo = RN;
		System.out.println("Room NO Is: " + Utility.RoomNo);
		roomClass.NewRoomClassRoomNumber.sendKeys(RN);
		roomclassLogger.info("Entered the room number " + RN);
		test_steps.add("Enter  room number " + RN);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_AssignRoomNumber)));

		roomClass.NewRoomClassAssignRoomNumbers.click();
		test_steps.add("Click Assign room numbers");

		// Waiting for visibility of Save
		// roomClass.NewRoomClassSave.click();
		// roomclassLogger.info("clicked on Save Room");
		// test_steps.add("Click Save Room");
		// Wait.WaitForElement(driver, OR.Toaster_Message);
		// Wait.waitForElementToBeVisibile(By.className(OR.Toaster_Message),
		// driver);
		// roomClass.Toaster_Message.click();
		// Wait.wait1Second();

		Wait.WaitForElement(driver, OR.New_RoomClass_Publish);
		Wait.waitForElementToBeClickable(By.xpath(OR.New_RoomClass_Publish), driver);
		roomClass.NewRoomClassPublish.click();
		roomclassLogger.info("Clicked on Publish");
		test_steps.add("Click Publish  Room");
		Wait.waitForElementToBeVisibile(By.className(OR.Toaster_Message), driver);
		return test_steps;
	}

	public ArrayList<String> deleteRoomClass(WebDriver driver, String roomClassName) throws InterruptedException {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		boolean isRoomClassExist = false;

		try {
			String roomClassPath = "//a[starts-with(text(),'" + roomClassName + "')]//..//following-sibling::td//input";
			Wait.WaitForElement(driver, roomClassPath);
			List<WebElement> elementRoomClasses_Checkbox = driver.findElements(By.xpath(roomClassPath));
			int pagesSize = roomClass.RoomClassPages.size();
			if (pagesSize < 4) {
				for (int i = 0; i < elementRoomClasses_Checkbox.size(); i++) {
					Utility.ScrollToElement_NoWait(elementRoomClasses_Checkbox.get(i), driver);
					elementRoomClasses_Checkbox.get(i).click();
					isRoomClassExist = true;
				}
			} else {
				for (int j = 0; j < roomClass.RoomClassPages.size(); j++) {
					Utility.ScrollToElement(roomClass.RoomClassPages.get(j), driver);
					roomClass.RoomClassPages.get(j).click();
					for (int i = 0; i < elementRoomClasses_Checkbox.size(); i++) {
						Wait.explicit_wait_visibilityof_webelement(elementRoomClasses_Checkbox.get(i), driver);
						Wait.explicit_wait_elementToBeClickable(elementRoomClasses_Checkbox.get(i), driver);
						Utility.ScrollToElement_NoWait(elementRoomClasses_Checkbox.get(i), driver);
						elementRoomClasses_Checkbox.get(i).click();
						isRoomClassExist = true;

					}
				}
			}
			if (isRoomClassExist) {
				Wait.waitForElementToBeClickable(By.xpath(OR.DeleteRoomClass_Button), driver);
				roomClass.DeleteRoomClass_Button.click();
				Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
				String toasterMessage = roomClass.Toaster_Message.getText();
				roomClass.Toaster_Message.click();
				Wait.wait1Second();
				roomclassLogger.info(toasterMessage);
				testSteps.add(toasterMessage);
			} else {
				testSteps.add("No room class exist that start with name of " + roomClassName);
			}
		} catch (Exception e) {

		}
		return testSteps;

	}

	public ArrayList<String> roomClassInformation(WebDriver driver, String roomClassName, String roomClassAbbrivation,
			String bedsCount, String maxAdults, String maxPersopns, String roomQuantity, ExtentTest test,
			ArrayList<String> test_steps) throws Exception {

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		// Creating object for Elements_NewRoomClass
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);

		// Enter Room Class Name
		roomClass.NewRoomClassName.sendKeys(roomClassName);
		// System.out.println("Entered the room class name : " + roomClassName);
		test_steps.add("Entered the room class name : " + roomClassName);

		// Enter the new class room abbreviation
		roomClass.NewRoomClassAbbrivation.sendKeys(roomClassAbbrivation);
		// System.out.println("Entered the room class abbreviation : " +
		// roomClassAbbrivation);
		test_steps.add("Entered the room class abbreviation : " + roomClassAbbrivation);

		// Click on Rooms
		roomClass.NewRoomClassRooms.click();
		// System.out.println("Clicked on Rooms Tab");
		test_steps.add("Clicked on Rooms Tab");

		// Enter the Max Adults
		roomClass.NewRoomClassMaxAdults.sendKeys(maxAdults);

		test_steps.add("Entered the max audlts : " + maxAdults);

		// Enter the Max Persons
		roomClass.NewRoomClassMaxPersons.sendKeys(maxPersopns);

		// System.out.println("Entered the max persopns : " + maxPersopns);
		test_steps.add("Entered the max persons : " + maxPersopns);

		// Enter the Rooms Quantity
		roomClass.NewRoomClassRoomQuantity.sendKeys(roomQuantity);

		test_steps.add("Enterd the rooms quantity : " + roomQuantity);

		// Click create Rooms
		roomClass.NewRoomClassCreateRooms.click();
		// System.out.println("clicked on create rooms");
		test_steps.add("clicked on create rooms");

		// Waiting for visibility of Room Number text box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_RoomNumber)));

		// Clear the Room Number text box
		roomClass.NewRoomClassRoomNumber.clear();

		// Enter for Room Numbers
		roomClass.NewRoomClassRoomNumber.sendKeys("501");
		// System.out.println("Entered the room number 501");
		test_steps.add("Entered the room number 501");

		// Waiting for visibility of Assign Room Number button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_AssignRoomNumber)));

		// click on Assign room number
		roomClass.NewRoomClassAssignRoomNumbers.click();

		// System.out.println("clicked on assign room numbers");
		test_steps.add("Click Assign Room Number");

		Wait.WaitForElement(driver, OR.New_RoomClass_Save);
		Wait.waitForElementToBeVisibile(By.xpath(OR.New_RoomClass_Save), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.New_RoomClass_Save), driver);
		roomClass.NewRoomClassSave.click();
		test_steps.add("Click Save Room Class");
		// System.out.println("clicked on Save");
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		Wait.waitForElementToBeVisibile(By.className(OR.Toaster_Message), driver);
		test_steps.add(roomClass.Toaster_Message.getText());

		// Clicking on publish
		Wait.WaitForElement(driver, OR.NewRoomClassPublish);
		Wait.waitForElementToBeVisibile(By.xpath(OR.NewRoomClassPublish), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.NewRoomClassPublish), driver);
		roomClass.NewRoomClassPublish.click();
		test_steps.add("Click Publish Room Class");

		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		Wait.waitForElementToBeVisibile(By.className(OR.Toaster_Message), driver);
		roomclassLogger.info(roomClass.Toaster_Message.getText());
		return test_steps;
	}

	public void selectNewRoomClass(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);

		try {
			Utility.app_logs.info("try");
			Utility.ScrollToElement(Navigate.NewRoomClass, driver);
			Navigate.NewRoomClass.click();
		} catch (Exception e) {
			Utility.app_logs.info("catch");
			Utility.ScrollToElement(Navigate.New_RoomClass, driver);
			Navigate.New_RoomClass.click();
		}
		// Wait.explicit_wait_xpath(OR.New_RoomClass_Deails, driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.New_RoomClass_Deails), driver);
	}

	public ArrayList<String> roomClassRooms(WebDriver driver, String roomClassName, String roomClassAbbrivation,
			String bedsCount, String maxAdults, String maxPersopns, String roomQuantity, ExtentTest test,
			ArrayList<String> test_steps) throws Exception {

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		// Creating object for Elements_NewRoomClass
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);

		// Enter Room Class Name
		roomClass.NewRoomClassName.sendKeys(roomClassName);
		// System.out.println("Entered the room class name : " + roomClassName);
		test_steps.add("Entered the room class name : " + roomClassName);

		// Enter the new class room abbreviation
		roomClass.NewRoomClassAbbrivation.sendKeys(roomClassAbbrivation);
		// System.out.println("Entered the room class abbreviation : " +
		// roomClassAbbrivation);
		test_steps.add("Entered the room class abbreviation : " + roomClassAbbrivation);

		// Click on Rooms
		roomClass.NewRoomClassRooms.click();
		// System.out.println("Clicked on Rooms Tab");
		test_steps.add("Clicked on Rooms Tab");

		// Enter the Max Adults
		roomClass.NewRoomClassMaxAdults.sendKeys(maxAdults);

		test_steps.add("Entered the max audlts : " + maxAdults);

		// Enter the Max Persons
		roomClass.NewRoomClassMaxPersons.sendKeys(maxPersopns);

		// System.out.println("Entered the max persopns : " + maxPersopns);
		test_steps.add("Entered the max persons : " + maxPersopns);

		// Enter the Rooms Quantity
		roomClass.NewRoomClassRoomQuantity.sendKeys(roomQuantity);

		test_steps.add("Enterd the rooms quantity : " + roomQuantity);

		// Click create Rooms
		roomClass.NewRoomClassCreateRooms.click();
		// System.out.println("clicked on create rooms");
		test_steps.add("clicked on create rooms");

		// Waiting for visibility of Room Number text box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_RoomNumber)));

		// Clear the Room Number text box
		// roomClass.NewRoomClassRoomNumber.clear();

		// Enter for Room Numbers
		int roomNumber = 1;
		roomClass.LIst_NewRoomClassRoomNumber.get(0).sendKeys("" + roomNumber);
		/*
		 * for (int i = 0; i < roomClass.LIst_NewRoomClassRoomNumber.size();
		 * i++) { if (i < 75) {
		 * roomClass.LIst_NewRoomClassRoomNumber.get(i).sendKeys("" +
		 * roomNumber);
		 * Utility.ScrollToElement_NoWait(roomClass.LIst_NewRoomClassRoomNumber.
		 * get(i), driver); roomNumber++; } if (i > 224) {
		 * 
		 * roomClass.LIst_NewRoomClassRoomNumber.get(i).sendKeys("" +
		 * roomNumber);
		 * Utility.ScrollToElement_NoWait(roomClass.LIst_NewRoomClassRoomNumber.
		 * get(i), driver); roomNumber++; }
		 * 
		 * }
		 */

		// System.out.println("Entered the room number 501");
		test_steps.add("Entered the room number");

		// Waiting for visibility of Assign Room Number button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_AssignRoomNumber)));

		// click on Assign room number
		Utility.ScrollToElement(roomClass.NewRoomClassAssignRoomNumbers, driver);
		roomClass.NewRoomClassAssignRoomNumbers.click();

		// System.out.println("clicked on assign room numbers");
		test_steps.add("Click Assign Room Number");

		// Waiting for visibility of Save
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Save)));

		/*
		 * // click on Save roomClass.NewRoomClassSave.click();
		 * test_steps.add("Click Save Room Class"); //
		 * System.out.println("clicked on Save");
		 * Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		 * Wait.explicit_wait_visibilityof_webelement_120(roomClass.
		 * Toaster_Message, driver);
		 * test_steps.add(roomClass.Toaster_Message.getText()); // Waiting for
		 * visibility of publish
		 * 
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.
		 * New_RoomClass_Publish)));
		 * wait.until(ExpectedConditions.elementToBeClickable(roomClass.
		 * NewRoomClassPublish));
		 */
		// Clicking on publish
		roomClass.NewRoomClassPublish.click();

		// System.out.println("clicked on Publish");
		test_steps.add("Click Publish Room Class");
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		Wait.explicit_wait_visibilityof_webelement_120(roomClass.Toaster_Message, driver);
		// Wait.waitForElementToBeVisibile(By.xpath(OR.Toaster_Message),
		// driver);
		roomclassLogger.info(roomClass.Toaster_Message.getText());
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Msg_Publish)));

		return test_steps;
	}

	public ArrayList<String> createRoomClass_MoreThanOneRooms(WebDriver driver, String roomClassName,
			String roomClassAbbrivation, String bedsCount, String maxAdults, String maxPersopns, String roomQuantity,
			ArrayList<String> test_steps) throws Exception {

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		// Creating object for Elements_NewRoomClass
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);

		// Enter Room Class Name
		Utility.RoomClassName = roomClassName + Utility.getTimeStamp();
		roomClass.NewRoomClassName.sendKeys(Utility.RoomClassName);
		// System.out.println("Entered the room class name : " + roomClassName);
		test_steps.add("Entered the room class name : " + roomClassName);

		// Enter the new class room abbreviation
		Utility.RoomClassabv = roomClassAbbrivation + Utility.getTimeStamp();
		roomClass.NewRoomClassAbbrivation.sendKeys(Utility.RoomClassabv);
		// System.out.println("Entered the room class abbreviation : " +
		// roomClassAbbrivation);
		test_steps.add("Entered the room class abbreviation : " + roomClassAbbrivation);

		// Click on Rooms
		roomClass.NewRoomClassRooms.click();
		// System.out.println("Clicked on Rooms Tab");
		test_steps.add("Clicked on Rooms Tab");

		// Enter the Max Adults
		roomClass.NewRoomClassMaxAdults.sendKeys(maxAdults);

		test_steps.add("Entered the max audlts : " + maxAdults);

		// Enter the Max Persons
		roomClass.NewRoomClassMaxPersons.sendKeys(maxPersopns);

		// System.out.println("Entered the max persopns : " + maxPersopns);
		test_steps.add("Entered the max persons : " + maxPersopns);

		// Enter the Rooms Quantity
		roomClass.NewRoomClassRoomQuantity.sendKeys(roomQuantity);

		test_steps.add("Enterd the rooms quantity : " + roomQuantity);

		// Click create Rooms
		roomClass.NewRoomClassCreateRooms.click();
		// System.out.println("clicked on create rooms");
		test_steps.add("clicked on create rooms");

		// Waiting for visibility of Room Number text box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_RoomNumber)));
		// Enter for Room Numbers
		Random random = new Random();
		int x = random.nextInt(900);

		// int roomNumber = 1;
		int roomNumber = x;
		Utility.RoomNo = String.valueOf(roomNumber);
		for (int i = 0; i < roomClass.List_NewRoomClassRoomNumbers.size(); i++) {
			boolean isExist = roomClass.List_NewRoomClassRoomNumbers.get(i).isDisplayed();
			if (isExist) {
				roomClass.List_NewRoomClassRoomNumbers.get(i).sendKeys("" + roomNumber);
				Utility.ScrollToElement_NoWait(roomClass.List_NewRoomClassRoomNumbers.get(i), driver);
				roomNumber++;
			}
		}

		test_steps.add("Entered the room number");

		// Waiting for visibility of Assign Room Number button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_AssignRoomNumber)));

		// click on Assign room number
		Utility.ScrollToElement(roomClass.NewRoomClassAssignRoomNumbers, driver);
		roomClass.NewRoomClassAssignRoomNumbers.click();

		// System.out.println("clicked on assign room numbers");
		test_steps.add("Click Assign Room Number");

		// Waiting for visibility of Save
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_Save)));
		// Clicking on publish
		roomClass.NewRoomClassPublish.click();

		test_steps.add("Click Publish Room Class");
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		Wait.explicit_wait_visibilityof_webelement_120(roomClass.Toaster_Message, driver);
		roomclassLogger.info(roomClass.Toaster_Message.getText());
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getRoomClassAbbrivation> ' Description: <Return
	 * abbreviation for roomclasses> ' Input parameters: <WebDriver
	 * driver,String Roomclass> ' Return value: <String> ' Created By: <Jamal
	 * Nasir> ' Created On: <05/18/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String getRoomClassAbbrivation(WebDriver driver, String Roomclass) throws InterruptedException {
		Elements_NewRoomClass ele = new Elements_NewRoomClass(driver);
		String abb1 = null;
		String all = "//a[text()='All']";
		Wait.WaitForElement(driver, all);
		Wait.wait2Second();
		driver.findElement(By.xpath(all)).click();
		Wait.wait5Second();
		Wait.WaitForElement(driver, OR_Setup.Roomclass_Pagenation);
		new Select(ele.Roomclass_Pagenation).selectByVisibleText("100");
		String[] rc = Roomclass.split("\\|");
		int count = rc.length;
		String room = null;
		String abbri = null;
		for (int i = 1; i <= count; i++) {
			room = "//a[text()='" + rc[i - 1] + "']";
			Wait.WaitForElement(driver, room);
			Wait.wait2Second();
			driver.findElement(By.xpath(room)).click();
			abbri = "//input[contains(@data-bind,'value: RoomclassAbrv,')]";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Wait.WaitForElement(driver, abbri);
			abb1 = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(abbri)));
			roomclassLogger.info("getting abbrivation for room class : " + rc[i - 1] + " is : " + abb1);
			String close = "//ul[@class='sec_nav']/li";
			int size = driver.findElements(By.xpath(close)).size();
			close = "//ul[@class='sec_nav']/li[" + size + "]//i";
			Wait.WaitForElement(driver, close);
			driver.findElement(By.xpath(close)).click();
		}
		return abb1;
	}

	public String getSingleRoomClassZone(WebDriver driver, ArrayList<String> test_steps, String roomClassStartWith,
			String roomClassName) throws InterruptedException {
		String defaultZone = null, room = null, zone = null;
		boolean isRoomClassExist = false;
		Elements_NewRoomClass roomClasss = new Elements_NewRoomClass(driver);
		Wait.WaitForElement(driver, OR_Setup.Roomclass_Pagenation);
		String roomClassPath = "//a[starts-with(text(),'" + roomClassStartWith + "')]";
		Wait.WaitForElement(driver, roomClassPath);
		room = "//a[text()='" + roomClassName + "']";
		try {
			int pagesSize = roomClasss.RoomClassPages.size();
			if (pagesSize < 4) {
				isRoomClassExist = Utility.isElementDisplayed(driver, By.xpath(room));
			} else {
				for (int j = 0; j < roomClasss.RoomClassPages.size(); j++) {
					Utility.ScrollToElement(roomClasss.RoomClassPages.get(j), driver);
					roomClasss.RoomClassPages.get(j).click();
					isRoomClassExist = Utility.isElementDisplayed(driver, By.xpath(room));
					if (isRoomClassExist) {
						break;
					} else {
						continue;
					}
				}
			}
			if (isRoomClassExist) {
				Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(room)), driver);
				Utility.ScrollToElement(driver.findElement(By.xpath(room)), driver);
				driver.findElement(By.xpath(room)).click();
				roomclassLogger.info("Click on Room Class: " + roomClassName);
			} else {
				test_steps.add("No room class exist  with name of " + roomClassName);
			}
			String roomsTab = "//a[text()='Rooms']";
			Wait.WaitForElement(driver, roomsTab);
			WebElement roomtabs = driver.findElement(By.xpath(roomsTab));
			Utility.ScrollToElement(roomtabs, driver);
			roomtabs.click();
			roomclassLogger.info("Click on Rooms Tab ");
			zone = "//td[contains(@class,'CellWidthCenter ')]/select";
			WebElement element = driver.findElement(By.xpath(zone));
			Utility.ScrollToElement(element, driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			String optionValue = (String) executor.executeScript("return arguments[0].value", element);
			roomclassLogger.info("Default Values Is:" + optionValue);
			Select sel = new Select(element);
			WebElement option = sel.getFirstSelectedOption();
			defaultZone = option.getText();
			roomclassLogger.info("Default Zone Is: " + defaultZone);
			String close = "//ul[@class='sec_nav']/li";
			int size = driver.findElements(By.xpath(close)).size();
			close = "//ul[@class='sec_nav']/li[" + size + "]//i";
			Wait.WaitForElement(driver, close);
			WebElement closeEle = driver.findElement(By.xpath(close));
			Utility.ScrollToElement(closeEle, driver);
			closeEle.click();
			roomclassLogger.info("Click Close Icon");
		} catch (Exception e) {

		}
		return defaultZone;
	}

	public void searchButtonClick(WebDriver driver) throws Exception {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.RoomClasses_SearchButton), driver);
		Utility.ScrollToElement(roomClass.RoomClasses_SearchButton, driver);
		roomClass.RoomClasses_SearchButton.click();
		Wait.waitForElementToBeClickable(By.xpath(OR.RoomClasses_TableShow), driver);
		assertTrue(roomClass.RoomClasses_TableShow.isDisplayed(), "Failed: RoomClasses Table not displayed");

	}

	public ArrayList<String> searchRoomClass(WebDriver driver, String roomClassName, ArrayList<String> test_steps)
			throws Exception {
		// Explicit wait object creation
		char ch = roomClassName.charAt(0);
		String str = "" + ch;
		str = str.toUpperCase();
		System.out.println("String :" + str);

		// Waiting for visibility of Search Bar
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Search;

		Search = driver.findElement(By.xpath("//ul[@class='pagination']/li/a[contains(text(),'" + str + "')]"));
		Wait.waitForElementToBeClickable(By.xpath("//ul[@class='pagination']/li/a[contains(text(),'" + str + "')]"),
				driver);
		js.executeScript("arguments[0].click(true);", Search);
		Utility.app_logs.info("Click: " + str);
		// Search.click();
		// Validating the newly created room class
		if (driver.findElements(By.xpath("//a[contains(text(),'" + roomClassName + "')]")).size() > 0) {
			Utility.app_logs.info("newly created class room validated successfully");
			test_steps.add("newly created class room validated successfully");

		} else {
			Utility.app_logs.info("new class room not successfully created");
			test_steps.add("new class room not successfully created");

		}
		return test_steps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <create_RoomClass> ' Description: <create_RoomClass with
	 * Random Number> ' ' Input parameters: <string> ' Created By: <Gangotri
	 * Sikheria> ' Created On: <9 June 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> create_RoomClass(WebDriver driver, String roomClassName, String roomClassAbbrivation,
			String bedsCount, String maxAdults, String maxPersopns, String roomQuantity, ExtentTest test,
			ArrayList<String> test_steps) throws Exception {

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		Wait.WaitForElement(driver, OR.New_RoomClass_Name);
		Wait.waitForElementToBeVisibile(By.xpath(OR.New_RoomClass_Name), driver);
		roomClass.NewRoomClassName.sendKeys(roomClassName);
		roomclassLogger.info("Entered the room class name : " + roomClassName);
		test_steps.add("Enter room class name : " + roomClassName);
		roomClass.NewRoomClassAbbrivation.sendKeys(roomClassAbbrivation);
		roomclassLogger.info("Entered the room class abbreviation : " + roomClassAbbrivation);
		test_steps.add("Enter room class abbreviation : " + roomClassAbbrivation);

		roomClass.NewRoomClassRooms.click();
		roomclassLogger.info("Clicked on Rooms Tab");
		test_steps.add("Click Rooms Tab");
		roomClass.NewRoomClassMaxAdults.sendKeys(maxAdults);
		roomclassLogger.info("Entered the max audlts : " + maxAdults);
		test_steps.add("Enter max audlts : " + maxAdults);
		roomClass.NewRoomClassMaxPersons.sendKeys(maxPersopns);
		roomclassLogger.info("Entered the max persons : " + maxPersopns);
		test_steps.add("Enter max persons : " + maxPersopns);
		roomClass.NewRoomClassRoomQuantity.sendKeys(roomQuantity);
		roomclassLogger.info("Enterd the rooms quantity : " + roomQuantity);
		test_steps.add("Enter rooms quantity : " + roomQuantity);
		Wait.explicit_wait_visibilityof_webelement(roomClass.NewRoomClassCreateRooms, driver);
		Wait.explicit_wait_elementToBeClickable(roomClass.NewRoomClassCreateRooms, driver);
		Utility.ScrollToElement(roomClass.NewRoomClassCreateRooms, driver);
		roomClass.NewRoomClassCreateRooms.click();
		roomclassLogger.info("clicked on create rooms");
		test_steps.add("Click create rooms");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_RoomNumber)));
		roomClass.NewRoomClassRoomNumber.clear();
		String RN = Utility.GenerateRandomNumber();
		Utility.RoomNo = RN;
		System.out.println("Room NO Is: " + Utility.RoomNo);
		roomClass.NewRoomClassRoomNumber.sendKeys(RN);
		roomclassLogger.info("Entered the room number " + RN);
		test_steps.add("Enter  room number " + RN);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.New_RoomClass_AssignRoomNumber)));

		roomClass.NewRoomClassAssignRoomNumbers.click();
		test_steps.add("Click Assign room numbers");

		// Waiting for visibility of Save
		roomClass.NewRoomClassSave.click();
		roomclassLogger.info("clicked on Save Room");
		test_steps.add("Click Save Room");
		// Wait.WaitForElement(driver, OR.Toaster_Message);
		Wait.waitForElementToBeVisibile(By.className(OR.Toaster_Message), driver);
		roomClass.Toaster_Message.click();
		Wait.wait1Second();

		Wait.WaitForElement(driver, OR.New_RoomClass_Publish);
		Wait.waitForElementToBeClickable(By.xpath(OR.New_RoomClass_Publish), driver);
		roomClass.NewRoomClassPublish.click();
		roomclassLogger.info("Clicked on Publish");
		test_steps.add("Click Publish  Room");
		Wait.waitForElementToBeVisibile(By.className(OR.Toaster_Message), driver);
		return test_steps;
	}

	public ArrayList<String> closeOpenedRoomClass(WebDriver driver, String roomClassName, ArrayList<String> test_steps)
			throws Exception {
		// TODO Auto-generated method stub
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		// Wait.explicit_wait_visibilityof_webelement(roomClass.Close_RoomClass,
		// driver);
		try {
			Wait.waitForElementToBeClickable(By.xpath(OR.Close_RoomClass), driver);
			Utility.ScrollToElement(roomClass.Close_RoomClass, driver);
			roomClass.Close_RoomClass.click();
		} catch (Exception e) {
			Wait.waitForElementToBeClickable(By.xpath(OR.Close_RoomClass_1), driver);
			Utility.ScrollToElement(roomClass.Close_RoomClass_1, driver);
			roomClass.Close_RoomClass_1.click();
		}
		test_steps.add("Close Opened Room Class");
		Utility.app_logs.info("Close Opened Room Class");
		return test_steps;

	}


	// Added By Adhnan Ghaffar7/2/2020
	public ArrayList<String> selectRoomClassStatus(WebDriver driver, String status) throws Exception {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		ArrayList<String> testSteps = new ArrayList<>();

		Wait.WaitForElement(driver, OR.ROOM_CLASS_STATUS_SELECTED_VALUE);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ROOM_CLASS_STATUS_SELECTED_VALUE), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.ROOM_CLASS_STATUS_SELECTED_VALUE), driver);
		Utility.ScrollToElement(roomClass.ROOM_CLASS_STATUS_SELECTED_VALUE, driver);
		if (roomClass.ROOM_CLASS_STATUS_SELECTED_VALUE.getText().equals(status)) {
			testSteps.add("Status : " + status + " is already Selected");
			Utility.app_logs.info("Status : " + status + " is already Selected");
		} else {
			roomClass.ROOM_CLASS_STATUS_SELECTED_VALUE.click();
			testSteps.add("Click Status to expand drop down");
			Utility.app_logs.info("Click Status to expand drop down");
			String xpath = "//ul[@role='listbox']/li[text()='" + status + "']";
			Wait.WaitForElement(driver, xpath);
			Wait.waitForElementToBeVisibile(By.xpath(xpath), driver);
			Wait.waitForElementToBeClickable(By.xpath(xpath), driver);
			WebElement roomClassStatus = driver.findElement(By.xpath(xpath));
			Utility.ScrollToElement(roomClassStatus, driver);
			roomClassStatus.click();
			Wait.waitForElementToBeGone(driver, roomClassStatus, 30);
		}
		assertEquals(roomClass.ROOM_CLASS_STATUS_SELECTED_VALUE.getText(),status,"Failed: Room Class Status not selected");
		return testSteps;
	}

	// Written by Farhan Ghaffar
	public ArrayList<String> selectRoomClassSearchStatus(WebDriver driver, String status) throws Exception {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		ArrayList<String> testSteps = new ArrayList<>();

		Wait.WaitForElement(driver, OR.roomClassStatusDropDown);
		Wait.waitForElementToBeVisibile(By.xpath(OR.roomClassStatusDropDown), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.roomClassStatusDropDown), driver);
		Utility.ScrollToElement(roomClass.roomClassStatusDropDown, driver);
		new Select(driver.findElement(By.xpath(OR.roomClassStatusDropDown))).selectByVisibleText(status);
		testSteps.add("Selected status : " + status);
		Utility.app_logs.info("Selected status : " + status);
		Wait.WaitForElement(driver, OR.roomClassSearchButton);
		Wait.waitForElementToBeVisibile(By.xpath(OR.roomClassSearchButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.roomClassSearchButton), driver);
		Utility.ScrollToElement(roomClass.roomClassSearchButton, driver);
		roomClass.roomClassSearchButton.click();
		testSteps.add("Click on search button");
		Utility.app_logs.info("Click on search button");

		return testSteps;

	}

	// get all active room class

	public List<String>[] getAllActiveRoomClasses(WebDriver driver) throws InterruptedException {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		ArrayList<String> roomClasses = new ArrayList<>();
		ArrayList<String> roomClassesAbbreviation = new ArrayList<>();
		ArrayList<String> roomsNumber = new ArrayList<>();
		String pathOfTotalRecords = "//tbody[@class='ant-table-tbody']//tr";
		String pathOfStatus = "";
		String getStats = "";
		String pathOfRoomClassName = "";
		String getRoomClassName = "";
		String pathOfRoomClassAbbreviation = "";
		String getRoomClassAbbreviation = "";
		String pathOfRoomClassNumber = "";
		String getRoomClassNumber = "";
		boolean isRoomClassExist = false;

		int pagesSize = roomClass.RoomClassPages.size();
		if (pagesSize < 4) {
			List<WebElement> getTotalRecord = driver.findElements(By.xpath(pathOfTotalRecords));

			roomclassLogger.info("size: " + getTotalRecord.size());
			for (int i = 1; i <= getTotalRecord.size(); i++) {
				pathOfStatus = "(//tbody[@class='ant-table-tbody']//tr)[" + i + "]//td[5]";
				getStats = driver.findElement(By.xpath(pathOfStatus)).getText();
				getStats = getStats.trim();
				if (getStats.equalsIgnoreCase("Active") || getStats.contains("Active")) {
					pathOfRoomClassName = "(//tbody[@class='ant-table-tbody']//tr)[" + i + "]//td[2]";
					getRoomClassName = driver.findElement(By.xpath(pathOfRoomClassName)).getText();
					getRoomClassName = getRoomClassName.trim();
					roomClasses.add(getRoomClassName);

					pathOfRoomClassAbbreviation = "(//tbody[@class='ant-table-tbody']//tr)[" + i + "]//td[3]";
					getRoomClassAbbreviation = driver.findElement(By.xpath(pathOfRoomClassAbbreviation)).getText();
					getRoomClassAbbreviation = getRoomClassAbbreviation.trim();
					roomClassesAbbreviation.add(getRoomClassAbbreviation);

					pathOfRoomClassNumber = "(//tbody[@class='ant-table-tbody']//tr)[" + i + "]//td[4]";
					getRoomClassNumber = driver.findElement(By.xpath(pathOfRoomClassNumber)).getText();
					getRoomClassNumber = getRoomClassNumber.trim();
					roomsNumber.add(getRoomClassNumber);
					isRoomClassExist = true;
				}
			}
		} else {
			for (int j = 0; j < roomClass.RoomClassPages.size(); j++) {
				Utility.ScrollToElement(roomClass.RoomClassPages.get(j), driver);
				roomClass.RoomClassPages.get(j).click();
				roomclassLogger.info("roomClass Page Size : " + roomClass.RoomClassPages.size());

				List<WebElement> getTotalRecord = driver.findElements(By.xpath(pathOfTotalRecords));

				for (int i = 1; i <= getTotalRecord.size(); i++) {
					pathOfStatus = "(//tbody[@class='ant-table-tbody']//tr)[" + i + "]//td[5]";
					getStats = driver.findElement(By.xpath(pathOfStatus)).getText();
					getStats = getStats.trim();
					if (getStats.equalsIgnoreCase("Active") || getStats.contains("Active")) {
						pathOfRoomClassName = "(//tbody[@class='ant-table-tbody']//tr)[" + i + "]//td[2]";
						getRoomClassName = driver.findElement(By.xpath(pathOfRoomClassName)).getText();
						getRoomClassName = getRoomClassName.trim();
						roomClasses.add(getRoomClassName);

						pathOfRoomClassAbbreviation = "(//tbody[@class='ant-table-tbody']//tr)[" + i + "]//td[3]";
						getRoomClassAbbreviation = driver.findElement(By.xpath(pathOfRoomClassAbbreviation)).getText();
						getRoomClassAbbreviation = getRoomClassAbbreviation.trim();
						roomClassesAbbreviation.add(getRoomClassAbbreviation);

						pathOfRoomClassNumber = "(//tbody[@class='ant-table-tbody']//tr)[" + i + "]//td[4]";
						getRoomClassNumber = driver.findElement(By.xpath(pathOfRoomClassNumber)).getText();
						getRoomClassNumber = getRoomClassNumber.trim();
						roomsNumber.add(getRoomClassNumber);

						isRoomClassExist = true;
					}
				}

			}
		}
		if (!isRoomClassExist) {
			roomclassLogger.info("No active room class exist");
		}

		List<String>[] arrayOfList = new List[3];
		arrayOfList[0] = roomClasses;
		arrayOfList[1] = roomClassesAbbreviation;
		arrayOfList[2] = roomsNumber;

		return arrayOfList;
	}
	
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getAllRoomClasses> ' Description: <Return the List of All
	 * RoomClasses Available > ' ' Input parameters: <WebDriver> ' Return:
	 * <ArrayList<String>> Created By: <Aqsa Manzoor> ' Created On: <30 June
	 * 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> getAllRoomClasses(WebDriver driver) throws InterruptedException {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		ArrayList<String> roomClassesExists = new ArrayList<>();

		int totalRoomClassOnCurrentPage = 0;
		int pagesCount = roomClass.RoomClass_Pages.size();

		for (int i = 3; i <= pagesCount; i++) {
			totalRoomClassOnCurrentPage = roomClass.RoomClassListNames.size();
			for (int j = 0; j < totalRoomClassOnCurrentPage; j++) {
				roomClassesExists.add(roomClass.RoomClassListNames.get(j).getText());

				if (j == (totalRoomClassOnCurrentPage - 1) && i < pagesCount) {
					Utility.ScrollToElement(roomClass.RoomClass_Pages.get(i), driver);
					roomClass.RoomClass_Pages.get(i).click();
					Wait.explicit_wait_visibilityof_webelement(roomClass.RoomClassPages.get(i), driver);
				}
			}
		}

		return roomClassesExists;
	}

}

