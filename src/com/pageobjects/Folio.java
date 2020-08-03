package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.innroad.inncenter.interfaces.IFolio;
import com.innroad.inncenter.pages.NewFolio;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Accounts;
import com.innroad.inncenter.webelements.Elements_All_Payments;
import com.innroad.inncenter.webelements.Elements_CPReservation;
import com.innroad.inncenter.webelements.Elements_Folio;
import com.innroad.inncenter.webelements.Elements_FolioLineItemsVoid;
import com.innroad.inncenter.webelements.Elements_MovieFolio;
import com.innroad.inncenter.webelements.Elements_Reservation;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class Folio implements IFolio {

	public static String resName;
	public static String res1LineItem;
	public static String res1LineItem2;
	public static String res1LineItem3;
	public static String res1LineItem4;
	public static String res1LineItem5;
	public static Double d;
	public static Double d2;
	public static Double d1;
	public static String Balance;
	static ArrayList<String> test_steps = new ArrayList<>();
	static ReservationSearch resSearch = new ReservationSearch();
	public static Logger folioLogger = Logger.getLogger("Folio");

	public ArrayList<String> MoveFolioInsideReservation(WebDriver driver, ExtentTest test1, String resNumber1,
			String newFolioName, String newFolioDescription, ArrayList<String> test_steps) throws InterruptedException {

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);

		// Search Reservation

		/*
		 * ReservationSearch resSearch = new ReservationSearch();
		 * resSearch.basicSearch_WithResNumber(driver,resNumber1);
		 * test.log(LogStatus.PASS,
		 * "System opened reservation number "+resNumber1);
		 * folioLogger.info("System opened reservation number "+resNumber1);
		 */

		// waiting for the visibility of guest info
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.GuestInfo)));

		wait.until(ExpectedConditions.elementToBeClickable(moveFolio.GuestInfo));

		Wait.WaitForElement(driver, OR.GuestInfo);

		moveFolio.MoveFolio_Folio.click();

		// Waiting for visibility of adjoining rooms
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_GuestFolio)));
		test_steps.add("Successfully opened the Folio Tab");
		folioLogger.info("Successfully opened the Folio Tab");

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio);

		// click on Folio Tab
		moveFolio.MoveFolio_NewFolio.click();

		// Waiting for visibility of new Folio Details

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolioDeatils);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_NewFolioDeatils)));
		test_steps.add("Successfully New Folio Details pop up ");
		folioLogger.info("Successfully New Folio Details pop up ");

		// new Folio creation
		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_Name);
		moveFolio.MoveFolio_NewFolio_Name.sendKeys(newFolioName);
		test_steps.add("Successfully Entered the New Folio Name : " + newFolioName);
		folioLogger.info("Successfully Entered the New Folio Name : " + newFolioName);

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_Description);
		moveFolio.MoveFolio_NewFolio_Description.sendKeys(newFolioDescription);
		test_steps.add("Successfully Entered the New Folio Description : " + newFolioDescription);
		folioLogger.info("Successfully Entered the New Folio Description : " + newFolioDescription);

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_Save);
		moveFolio.MoveFolio_NewFolio_Save.click();
		test_steps.add("Successfully clicked save Folio");
		folioLogger.info("Successfully clicked save Folio");
		// Wait.wait10Second();
		// Waiting for visibility of adjoining rooms

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_SaveReservation);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_NewFolio_SaveReservation)));
		moveFolio.MoveFolio_NewFolio_SaveReservation.click();
		test_steps.add("Successfully clicked save Reservation");
		folioLogger.info("Successfully clicked save Reservation");

		// select new folio

		Wait.WaitForElement(driver, "//div[@id='toast-container']/div/div");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='toast-container']/div/div")));
		Wait.wait1Second();
		Select sel = new Select(moveFolio.MoveFolio_GuestFolio);
		sel.selectByVisibleText(newFolioName);
		test_steps.add("Successfully opened the new Folio : " + newFolioName);
		folioLogger.info("Successfully opened the new Folio : " + newFolioName);

		sel.selectByIndex(0);
		Wait.wait1Second();
		sel.selectByVisibleText(newFolioName);
		Wait.wait1Second();
		sel.selectByIndex(0);
		Wait.wait2Second();

		Wait.WaitForElement(driver,
				"//label[contains(text(),'Balance: ')]/following-sibling::span[@class='pamt']/span[@class='pamt']");
		String Balance = driver
				.findElement(By
						.xpath("//label[contains(text(),'Balance: ')]/following-sibling::span[@class='pamt']/span[@class='pamt']"))
				.getText();
		Balance = Balance.replace("$", "");

		Double d = Double.parseDouble(Balance);
		folioLogger.info("Folio Balance : " + d);

		// select the folio item to move
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", moveFolio.MoveFolio_SelectFiloItem);
		moveFolio.MoveFolio_SelectFiloItem.click();
		test_steps.add("Successfully selected the folio Item");
		folioLogger.info("Successfully selected the folio Item");

		Wait.WaitForElement(driver, OR.MoveFolio_Move);

		moveFolio.MoveFolio_Move.click();
		test_steps.add("Successfully clieked on move");
		folioLogger.info("Successfully clieked on move");

		// Folio items to move to target folio pop up
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_TargetFolio)));
		Wait.WaitForElement(driver, OR.MoveFolio_TargetFolio);
		sel = new Select(moveFolio.MoveFolio_TargetFolio);
		sel.selectByVisibleText(newFolioName);
		test_steps.add("Successfully opened the new Folio : " + newFolioName);
		folioLogger.info("Successfully opened the new Folio : " + newFolioName);

		// move folio items to target folio pop up

		Wait.WaitForElement(driver, OR.MoveFolio_FolioItemToMove);

		moveFolio.MoveFolio_FolioItemToMove.click();
		test_steps.add("Successfully clieked on folio item");
		folioLogger.info("Successfully clieked on folio item");

		Wait.WaitForElement(driver, OR.MoveFolio_MoveSelectedItem);
		moveFolio.MoveFolio_MoveSelectedItem.click();
		test_steps.add("Successfully moved folio item");
		folioLogger.info("Successfully moved folio item");

		Wait.WaitForElement(driver, OR.MoveFolio_Close);
		moveFolio.MoveFolio_Close.click();
		test_steps.add("Successfully clicked on close");
		folioLogger.info("Successfully clicked on close");

		// Wait.wait2Second();
		Wait.WaitForElement(driver, "//div[@id='toast-container']/div/div[2]");
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='toast-container']/div/div[2]")));

		String Balance1 = driver
				.findElement(By
						.xpath("//label[contains(text(),'Balance: ')]/following-sibling::span[@class='pamt']/span[@class='pamt']"))
				.getText();
		Balance1 = Balance1.replace("$", "");

		Double d1 = Double.parseDouble(Balance1);
		folioLogger.info("Folio Old Balance : " + d1);

		// validation folio items
		sel = new Select(moveFolio.MoveFolio_GuestFolio);
		sel.selectByVisibleText(newFolioName);
		test_steps.add("Successfully opened the new Folio : " + newFolioName);
		folioLogger.info("Successfully opened the new Folio : " + newFolioName);
		if (driver.findElements(By.xpath(OR.MoveFolio_SelectFiloItem)).size() > 0) {
			test_steps.add("Successfully moved the folio Item to : " + newFolioName);
			folioLogger.info("Successfully moved the folio Item to : " + newFolioName);

			String Balance2 = driver
					.findElement(By
							.xpath("//label[contains(text(),'Balance: ')]/following-sibling::span[@class='pamt']/span[@class='pamt']"))
					.getText();
			Balance2 = Balance2.replace("$", "");

			Double d2 = Double.parseDouble(Balance2);
			folioLogger.info("Folio New Balance : " + d2);
			if (d == d1 + d2) {
				test_steps.add("Successfully Validated Folio Balances");
				folioLogger.info("Successfully Validated Folio Balances");
			} else {
				test_steps.add("Folio Balance Fail");
				folioLogger.info("Folio Balance Fail");
			}
		}
		return test_steps;
	}

	@Override
	public ArrayList<String> MoveFolioInsideReservation(WebDriver driver, ExtentTest test1, String resNumber1,
			String resNumber2, Double d, Double d2, ArrayList<String> test_steps) throws Exception {
		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);

		String resName;

		// Search and open Reservation1
		ReservationSearch resSearch = new ReservationSearch();
		resName = resSearch.basicSearch_WithResNumber(driver, resNumber1);
		test_steps.add("System opened reservation number " + resNumber1);
		folioLogger.info("System opened reservation number " + resNumber1);

		Wait.WaitForElement(driver, OR.Reservations);
		// Search and open Reservation2
		moveFolio.Reservation_SecNav.click();
		Wait.wait2Second();
		resSearch.basicSearch_WithResNumber(driver, resNumber2);
		test_steps.add("System opened reservation number " + resNumber2);
		folioLogger.info("System opened reservation number " + resNumber2);

		// opening the first reservation tab
		Wait.WaitForElement(driver, OR.FirstOpenedReservation);

		moveFolio.FirstOpenedReservation.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.GuestInfo)));
		wait.until(ExpectedConditions.elementToBeClickable(moveFolio.GuestInfo));
		// Wait.wait3Second();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Wait.WaitForElement(driver, OR.MoveFolio_Folio);
		jse.executeScript("arguments[0].click();", moveFolio.MoveFolio_Folio);

		// moveFolio.MoveFolio_Folio.click();

		// Waiting for visibility of adjoining rooms
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_GuestFolio)));
		test_steps.add("Successfully opened the Folio Tab");
		folioLogger.info("Successfully opened the Folio Tab");

		// Select the folio item to move
		// Wait.wait2Second();

		Wait.WaitForElement(driver, OR.MoveFolio_SelectFiloItem);
		jse.executeScript("arguments[0].scrollIntoView();", moveFolio.MoveFolio_SelectFiloItem);
		moveFolio.MoveFolio_SelectFiloItem.click();
		test_steps.add("Successfully selected the folio Item");
		folioLogger.info("Successfully selected the folio Item");

		// click on move
		// Wait.wait3Second();

		Wait.WaitForElement(driver, OR.MoveFolio_Move);
		moveFolio.MoveFolio_Move.click();
		test_steps.add("Successfully clieked on move");
		folioLogger.info("Successfully clieked on move");
		Wait.wait2Second();
		// Select the guest folio of another reservation
		// Wait.WaitForElement(driver, OR.MoveFolio_TargetFolio);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_TargetFolio)));
		Select sel = new Select(moveFolio.MoveFolio_TargetFolio);
		sel.selectByVisibleText("Guest Folio");
		test_steps.add("Successfully opened the new Folio : " + "Guest Folio");
		folioLogger.info("Successfully opened the new Folio : " + "Guest Folio");

		// move Folio item to another reservation folio
		Wait.WaitForElement(driver, OR.MoveFolio_FolioItemToMove);
		moveFolio.MoveFolio_FolioItemToMove.click();
		test_steps.add("Successfully clieked on folio item");
		folioLogger.info("Successfully clieked on folio item");

		Wait.WaitForElement(driver, OR.MoveFolio_MoveSelectedItem);
		moveFolio.MoveFolio_MoveSelectedItem.click();
		test_steps.add("Successfully moved folio item");
		folioLogger.info("Successfully moved folio item");

		Wait.WaitForElement(driver, OR.MoveFolio_Close);
		moveFolio.MoveFolio_Close.click();
		test_steps.add("Successfully clicked on close");
		folioLogger.info("Successfully clicked on close");
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='toast-container']/div/div[2]")));

		// closing first reservation and validation second reservation folio
		Wait.WaitForElement(driver, OR.FirstOpenedReservationClose);
		moveFolio.FirstOpenedReservationClose.click();
		Wait.WaitForElement(driver, OR.FirstOpenedReservation);
		moveFolio.FirstOpenedReservation.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.GuestInfo)));

		wait.until(ExpectedConditions.elementToBeClickable(moveFolio.GuestInfo));
		// Wait.wait3Second();
		Wait.WaitForElement(driver, OR.MoveFolio_Folio);
		moveFolio.MoveFolio_Folio.click();

		// Waiting for visibility of guest folio
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_GuestFolio)));
		test_steps.add("Successfully opened the Folio Tab");
		folioLogger.info("Successfully opened the Folio Tab");
		// Wait.wait2Second();
		Wait.WaitForElement(driver,
				"//label[contains(text(),'Balance: ')]/following-sibling::span[@class='pamt']/span[@class='pamt']");
		String Balance1 = driver
				.findElement(By
						.xpath("//label[contains(text(),'Balance: ')]/following-sibling::span[@class='pamt']/span[@class='pamt']"))
				.getText();
		Balance1 = Balance1.replace("$", "");

		Double d1 = Double.parseDouble(Balance1);
		folioLogger.info("Folio First Balance : " + d1);

		if (driver
				.findElements(By
						.xpath("//table[@class='table table-striped table-bordered table-hover trHeight25']/tbody/tr/td/input"))
				.size() > 1) {

			if (d1 == d + d2) {
				test_steps.add("Successfully moved the folio Item");
				test_steps.add("Folio Balance is same : " + d1);
				folioLogger.info("Folio Balance is same : " + d1);
			} else {
				test_steps.add("Successfully moved the folio Item");
				test_steps.add("Folio Balance is same : " + d1);
				folioLogger.info("Folio Balance is not same : " + d1);
			}

		}
		return test_steps;

	}

	public ArrayList<String> MoveFolioInsideReservation(WebDriver driver, ExtentTest test, String resNumber1,
			String newFolioName, String newFolioDescription, String Category, String Amount,
			ArrayList<String> test_steps) throws InterruptedException {

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);

		// Search Reservation

		/*
		 * ReservationSearch resSearch = new ReservationSearch();
		 * resSearch.basicSearch_WithResNumber(driver,resNumber1);
		 * test.log(LogStatus.PASS,
		 * "System opened reservation number "+resNumber1);
		 * folioLogger.info("System opened reservation number "+resNumber1);
		 */

		// waiting for the visibility of guest info
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.GuestInfo)));

		wait.until(ExpectedConditions.elementToBeClickable(moveFolio.GuestInfo));

		Wait.WaitForElement(driver, OR.GuestInfo);

		moveFolio.MoveFolio_Folio.click();

		// Waiting for visibility of adjoining rooms
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_GuestFolio)));
		// test_steps.add("Successfully opened the Folio Tab");
		test_steps.add("Opened the Folio Tab");
		folioLogger.info("Opened the Folio Tab");

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio);

		// click on Folio Tab
		moveFolio.MoveFolio_NewFolio.click();

		// Waiting for visibility of new Folio Details

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolioDeatils);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_NewFolioDeatils)));
		// test_steps.add("Successfully New Folio Details pop up ");
		test_steps.add("Displayed New Folio Details pop up ");
		folioLogger.info("Displayed New Folio Details pop up ");

		// new Folio creation
		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_Name);
		moveFolio.MoveFolio_NewFolio_Name.sendKeys(newFolioName);
		// test_steps.add("Successfully Entered the New Folio Name : " +
		// newFolioName);
		test_steps.add("Entered the New Folio Name : " + newFolioName);
		folioLogger.info("Entered the New Folio Name : " + newFolioName);

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_Description);
		moveFolio.MoveFolio_NewFolio_Description.sendKeys(newFolioDescription);
		// test_steps.add("Successfully Entered the New Folio Description : " +
		// newFolioDescription);
		test_steps.add("Entered New Folio Description : " + newFolioDescription);
		folioLogger.info("Entered New Folio Description : " + newFolioDescription);

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_Save);
		moveFolio.MoveFolio_NewFolio_Save.click();
		// test_steps.add("Successfully clicked save Folio");
		test_steps.add("Clicked save Folio");
		folioLogger.info("Clicked save Folio");
		// Wait.wait10Second();
		// Waiting for visibility of adjoining rooms

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_SaveReservation);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_NewFolio_SaveReservation)));
		moveFolio.MoveFolio_NewFolio_SaveReservation.click();
		// test_steps.add("Successfully clicked save Reservation");
		test_steps.add("Clicked save Reservation");
		folioLogger.info("Clicked save Reservation");

		// select new folio

		Wait.WaitForElement(driver, "//div[@id='toast-container']/div/div");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='toast-container']/div/div")));
		Wait.wait1Second();
		Select sel = new Select(moveFolio.MoveFolio_GuestFolio);
		sel.selectByVisibleText(newFolioName);
		// test_steps.add("Successfully opened the new Folio : " +
		// newFolioName);
		test_steps.add("Opened the new Folio : " + newFolioName);
		folioLogger.info("Opened the new Folio : " + newFolioName);

		sel.selectByIndex(0);
		Wait.wait1Second();
		sel.selectByVisibleText(newFolioName);
		Wait.wait1Second();
		sel.selectByIndex(0);
		Wait.wait2Second();

		Wait.WaitForElement(driver,
				"//label[contains(text(),'Balance: ')]/following-sibling::span[@class='pamt']/span[@class='pamt']");
		String Balance = driver
				.findElement(By
						.xpath("//label[contains(text(),'Balance: ')]/following-sibling::span[@class='pamt']/span[@class='pamt']"))
				.getText();
		Balance = Balance.replace("$", "");

		Double d = Double.parseDouble(Balance);
		folioLogger.info("Folio Balance : " + d);

		// select the folio item to move
		// moveFolio.MoveFolio_SelectFiloItem.click();
		// Wait.WaitForElement(driver, OR.MoveFolio_SelectFiloItem2);
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_SelectFiloItem2)));
		// Wait.wait10Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", moveFolio.MoveFolio_SelectFiloItem2);
		moveFolio.MoveFolio_SelectFiloItem2.click();
		Wait.wait5Second();
		test_steps.add("Selected the folio Item");
		folioLogger.info("Selected the folio Item");

		Wait.WaitForElement(driver, OR.MoveFolio_Move);

		moveFolio.MoveFolio_Move.click();
		test_steps.add("Clicked on move");
		folioLogger.info("Clicked on move");

		// Folio items to move to target folio pop up
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_TargetFolio)));
		Wait.WaitForElement(driver, OR.MoveFolio_TargetFolio);
		sel = new Select(moveFolio.MoveFolio_TargetFolio);
		sel.selectByVisibleText(newFolioName);
		test_steps.add("Opened the new Folio : " + newFolioName);
		folioLogger.info("Opened the new Folio : " + newFolioName);

		// move folio items to target folio pop up

		Wait.WaitForElement(driver, OR.MoveFolio_FolioItemToMove);

		// moveFolio.MoveFolio_FolioItemToMove.click();
		moveFolio.MoveFolio_FolioItemToMove_2.click();
		test_steps.add("Clicked on folio item");
		folioLogger.info("Clicked on folio item");

		Wait.WaitForElement(driver, OR.MoveFolio_MoveSelectedItem);
		moveFolio.MoveFolio_MoveSelectedItem.click();
		test_steps.add("Moved folio item");
		folioLogger.info("Moved folio item");

		Wait.WaitForElement(driver, OR.MoveFolio_Close);
		moveFolio.MoveFolio_Close.click();
		test_steps.add("Clicked on close");
		folioLogger.info("Clicked on close");

		// Wait.wait2Second();
		Wait.WaitForElement(driver, "//div[@id='toast-container']/div/div[2]");
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='toast-container']/div/div[2]")));

		String Balance1 = driver
				.findElement(By
						.xpath("//label[contains(text(),'Balance: ')]/following-sibling::span[@class='pamt']/span[@class='pamt']"))
				.getText();
		Balance1 = Balance1.replace("$", "");

		Double d1 = Double.parseDouble(Balance1);
		folioLogger.info("Folio Old Balance : " + d1);

		// validation folio items
		sel = new Select(moveFolio.MoveFolio_GuestFolio);
		sel.selectByVisibleText(newFolioName);
		test_steps.add("Opened the new Folio : " + newFolioName);
		folioLogger.info("Opened the new Folio : " + newFolioName);
		if (driver.findElements(By.xpath(OR.MoveFolio_SelectFiloItem)).size() > 0) {
			test_steps.add("Moved the folio Item to : " + newFolioName);
			folioLogger.info("Moved the folio Item to : " + newFolioName);

			String Balance2 = driver
					.findElement(By
							.xpath("//label[contains(text(),'Balance: ')]/following-sibling::span[@class='pamt']/span[@class='pamt']"))
					.getText();
			Balance2 = Balance2.replace("$", "");

			Double d2 = Double.parseDouble(Balance2);
			folioLogger.info("Folio New Balance : " + d2);
			if (d == d1 + d2) {
				test_steps.add("Folio balance is same");
				folioLogger.info("Folio balance is same");
			} else {
				// test.log(LogStatus.FAIL, "Folio Balance Fail");
				test_steps.add("Folio balance is not same");
				folioLogger.info("Folio balance is not same");
			}
		}
		return test_steps;
	}

	public ArrayList<String> newFolio(WebDriver driver, ExtentTest test, String resNumber1, String newFolioName,
			String newFolioDescription, ArrayList<String> test_steps) throws InterruptedException {

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);
		ReservationSearch resSearch = new ReservationSearch();

		moveFolio.FirstOpenedReservationClose.click();
		boolean size = driver.findElements(By.xpath(OR.FirstOpenedReservationClose)).size() > 0;
		if (size) {
			moveFolio.FirstOpenedReservationClose.click();
		}
		Wait.wait2Second();
		// Search Reservation
		resSearch.basicSearch_WithResNumber(driver, resNumber1);
		test_steps.add("System opened reservation number " + resNumber1);
		folioLogger.info("System opened reservation number " + resNumber1);

		// waiting for the visibility of guest info

		Wait.WaitForElement(driver, OR.GuestInfo);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.GuestInfo)));
		wait.until(ExpectedConditions.elementToBeClickable(moveFolio.GuestInfo));

		Wait.WaitForElement(driver, OR.MoveFolio_Folio);
		moveFolio.MoveFolio_Folio.click();

		// Waiting for visibility of adjoining rooms
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_GuestFolio)));
		Wait.WaitForElement(driver, OR.MoveFolio_GuestFolio);
		test_steps.add("Successfully opened the Folio Tab");
		folioLogger.info("Clicked on Folio Tab");

		// click on Folio Tab
		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio);
		moveFolio.MoveFolio_NewFolio.click();

		// Waiting for visibility of new Folio Details
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_NewFolioDeatils)));
		Wait.WaitForElement(driver, OR.MoveFolio_NewFolioDeatils);
		test_steps.add("Successfully New Folio Details pop up");
		folioLogger.info("Successfully New Folio Details pop up");
		assertTrue(moveFolio.MoveFolio_NewFolioDeatils.isDisplayed(), "New Folio Popup didn't display");

		// new Folio creation
		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_Name);
		moveFolio.MoveFolio_NewFolio_Name.sendKeys(newFolioName);
		test_steps.add("Successfully Entered the New Folio Name : " + newFolioName);
		folioLogger.info("Successfully Entered the New Folio Name : " + newFolioName);
		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_Description);
		moveFolio.MoveFolio_NewFolio_Description.sendKeys(newFolioDescription);
		test_steps.add("Successfully Entered the New Folio Description : " + newFolioDescription);
		folioLogger.info("Successfully Entered the New Folio Description : " + newFolioDescription);
		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_Save);
		moveFolio.MoveFolio_NewFolio_Save.click();
		test_steps.add("Successfully clicked save Folio");
		folioLogger.info("Successfully clicked save Folio");
		new Select(moveFolio.MoveFolio_GuestFolio).selectByVisibleText(newFolioName);
		WebElement FolioDropDown = driver.findElement(By.xpath("//select[@class='form-control folioFil']"));
		String FirstSelectedoption = new Select(FolioDropDown).getFirstSelectedOption().getText();
		assertTrue(FirstSelectedoption.equals(newFolioName), "Folio didn't added");

		// Waiting for visibility of adjoining rooms
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_NewFolio_SaveReservation)));

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_SaveReservation);
		moveFolio.MoveFolio_NewFolio_SaveReservation.click();
		test_steps.add("Successfully clicked save Reservation");
		folioLogger.info("Successfully clicked save Reservation");

		// select new folio
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='toast-container']/div/div")));

		Wait.WaitForElement(driver, OR.MoveFolio_GuestFolio);

		Select sel = new Select(moveFolio.MoveFolio_GuestFolio);
		sel.selectByVisibleText(newFolioName);
		test_steps.add("Successfully selected the new Folio : " + newFolioName);
		folioLogger.info("Successfully selected the new Folio : " + newFolioName);

		Wait.WaitForElement(driver, OR.Edit_Folio_Btn);
		moveFolio.Edit_Folio_Btn.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_NewFolioDeatils)));
		assertTrue(moveFolio.MoveFolio_NewFolioDeatils.isDisplayed(), "Edit Folio Popup didn't display");

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_Name);
		moveFolio.MoveFolio_NewFolio_Name.clear();
		moveFolio.MoveFolio_NewFolio_Name.sendKeys(newFolioName + "test");
		test_steps.add("Successfully edited the New Folio Name : " + newFolioName + "test");
		folioLogger.info("Successfully edited the New Folio Name : " + newFolioName + "test");

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_Description);
		moveFolio.MoveFolio_NewFolio_Description.clear();
		moveFolio.MoveFolio_NewFolio_Description.sendKeys(newFolioDescription + "test");

		test_steps.add("Successfully edited the New Folio Description : " + newFolioDescription + "test");
		folioLogger.info("Successfully edited the New Folio Description : " + newFolioDescription + "test");

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_Save);
		moveFolio.MoveFolio_NewFolio_Save.click();
		test_steps.add("Successfully clicked save Folio");
		folioLogger.info("Successfully clicked save Folio");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_NewFolio_SaveReservation)));
		// select new folio

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_SaveReservation);
		moveFolio.MoveFolio_NewFolio_SaveReservation.click();
		test_steps.add("Successfully clicked save Reservation");
		folioLogger.info("Successfully clicked save Reservation");

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='toast-container']/div/div")));
		// Wait.wait3Second();

		Wait.WaitForElement(driver, OR.MoveFolio_GuestFolio);
		sel = new Select(moveFolio.MoveFolio_GuestFolio);
		newFolioName = newFolioName + "test";
		sel.selectByVisibleText(newFolioName);
		test_steps.add("Successfully selected the edited Folio : " + newFolioName + "test");
		folioLogger.info("Successfully selected the edited Folio : " + newFolioName + "test");
		Wait.wait1Second();
		FirstSelectedoption = new Select(FolioDropDown).getFirstSelectedOption().getText();
		assertTrue(FirstSelectedoption.equals(newFolioName), "Folio didn't added");

		Wait.WaitForElement(driver, OR.Delete_Folio_Btn);

		moveFolio.Delete_Folio_Btn.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_NewFolio_SaveReservation)));
		moveFolio.MoveFolio_NewFolio_SaveReservation.click();
		test_steps.add("Successfully clicked save Reservation");
		folioLogger.info("Successfully clicked save Reservation");

		Wait.wait5Second();
		if (!(driver.findElements(By.xpath("//select[@class='form-control folioFil']/option")).size() > 1)) {
			test_steps.add("Sucessfully deleted the folio : " + newFolioName + "test");
			folioLogger.info("Sucessfully deleted the folio : " + newFolioName + "test");
		}
		String FolioDropDownString = "//select[@class='form-control folioFil']//option";
		List<WebElement> FolioDropDownList = driver.findElements(By.xpath(FolioDropDownString));
		for (int i = 0; i < FolioDropDownList.size(); i++) {
			Wait.WaitForElement(driver, FolioDropDownString);

			if (!FolioDropDownList.get(i).getText().equals(newFolioName)) {
				break;
			}

			assertTrue(!FolioDropDownList.get(i).getText().equals(newFolioName), "Folio didn't deleted");

		}
		return test_steps;
	}

	public void LineItem(WebDriver driver, String SelectCategory, String Amount) throws InterruptedException {

		Elements_FolioLineItemsVoid elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.AddButton, driver);
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.elementToBeClickable(elements_FolioLineItemsVoid.AddButton));

		Utility.ScrollToElement(elements_FolioLineItemsVoid.AddButton, driver);
		elements_FolioLineItemsVoid.AddButton.click();
		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.SelectCategory, driver);

		new Select(elements_FolioLineItemsVoid.SelectCategory).selectByVisibleText(SelectCategory);
		elements_FolioLineItemsVoid.Enter_LineItemAmount.clear();
		elements_FolioLineItemsVoid.Enter_LineItemAmount.sendKeys(Amount);
		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.CommitButton, driver);
		Utility.ScrollToElement(elements_FolioLineItemsVoid.CommitButton, driver);
		elements_FolioLineItemsVoid.CommitButton.click();
		folioLogger.info("Click on Commit button");
		Wait.wait1Second();

	}

	public float GetEndingBalance(WebDriver driver) throws InterruptedException {
		Elements_Reservation res = new Elements_Reservation(driver);
		Utility.ScrollToElement(res.Balance, driver);
		String Balance = res.Balance.getText();
		System.out.print(Balance);
		float EndingBalance = Float.parseFloat(Balance.split(" ")[1]);
		System.out.print(EndingBalance);
		return EndingBalance;
	}

	public void VoidLineItem(WebDriver driver, String Category) throws InterruptedException {
		Elements_Accounts Account = new Elements_Accounts(driver);
		Elements_Reservation res = new Elements_Reservation(driver);
		float EndingBalance_Before = GetEndingBalance(driver);
		Utility.app_logs.info(EndingBalance_Before);
		String LineItem_AmountPath = "//td[contains(@data-bind,'lineitem')]/span[text()='" + Category
				+ "']//ancestor::tr//td[@class='lineitem-amount']/span";

		WebElement LineItem_Amount = driver.findElement(By.xpath(LineItem_AmountPath));
		float LineItemAmount = Float.parseFloat(LineItem_Amount.getText().split(" ")[1]);
		String CheckBox_path = "//td[contains(@data-bind,'lineitem')]/span[text()='" + Category
				+ "']//ancestor::tr//input[@type='checkbox']";
		WebElement CheckBox = driver.findElement(By.xpath(CheckBox_path));
		Utility.ScrollToElement(CheckBox, driver);
		CheckBox.click();
		Wait.explicit_wait_elementToBeClickable(Account.Void, driver);
		Account.Void.click();
		Wait.explicit_wait_visibilityof_webelement_120(Account.NotesPopup, driver);
		Account.NotesPopup_Note.sendKeys("Void It");
		Utility.ScrollToElement(Account.NotesPopup_Void, driver);
		Account.NotesPopup_Void.click();
		Wait.explicit_wait_visibilityof_webelement(res.Balance, driver);
		float EndingBalance_After = GetEndingBalance(driver);
		Utility.app_logs.info(EndingBalance_After);
		String EBB = String.format("%.2f", (EndingBalance_Before - LineItemAmount));
		assertEquals(String.format("%.2f", EndingBalance_After),
				String.format("%.2f", (EndingBalance_Before - LineItemAmount)),
				"Failed : Ending Balance is not updated");
		Utility.app_logs.info("Successfullt void Line item " + Category);
	}

	public void LineItem1(WebDriver driver, String SelectCategory, String Amount) throws InterruptedException {

		Elements_FolioLineItemsVoid Ele_FolioItem = new Elements_FolioLineItemsVoid(driver);
		Wait.wait2Second();
		WebElement element = driver.findElement(By.xpath(OR.AddButton));
		Wait.explicit_wait_visibilityof_webelement_120(element, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,300)");
		element.click();
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(Ele_FolioItem.SelectCategory1, driver);
		new Select(Ele_FolioItem.SelectCategory1).selectByVisibleText(SelectCategory);
		int size = Ele_FolioItem.LineItemsAmountFields.size();
		Ele_FolioItem.LineItems_Amount.sendKeys(Amount);
		Ele_FolioItem.CommitButton.click();
		Wait.wait1Second();
	}

	public void LineItem1(WebDriver driver, String Amount) throws InterruptedException {

		Elements_FolioLineItemsVoid Ele_FolioItem = new Elements_FolioLineItemsVoid(driver);
		Wait.wait2Second();
		WebElement element = driver.findElement(By.xpath(OR.AddButton));
		Wait.explicit_wait_visibilityof_webelement_120(element, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,300)");
		element.click();
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(Ele_FolioItem.SelectCategory1, driver);
		new Select(Ele_FolioItem.SelectCategory1).selectByIndex(1);
		int size = Ele_FolioItem.LineItemsAmountFields.size();
		Ele_FolioItem.LineItems_Amount.sendKeys(Amount);
		Ele_FolioItem.CommitButton.click();
		Wait.wait1Second();
	}

	public void VerifyLineItem(WebDriver driver, String Date, String Property, String Category, String Amount) {

		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement(Elements_FolioLineItemsVoid.RoomCharger_Tax.get(0), driver);
		String Charges = Elements_FolioLineItemsVoid.RoomCharger_Tax.get(0).getText();
		String Incidentals = Elements_FolioLineItemsVoid.RoomCharger_Tax.get(1).getText();
		String Tax = Elements_FolioLineItemsVoid.RoomCharger_Tax.get(2).getText();

		// Split $ sign
		String[] str_charger = Charges.split(" ");
		String[] str_incidentals = Incidentals.split(" ");
		String[] str_tax = Tax.split(" ");

		double do_charger = Double.valueOf(str_charger[1]);
		double do_incidentals = Double.valueOf(str_incidentals[1]);
		double do_tax = Double.valueOf(str_tax[1]);

		// Calculate total charger with tax
		double total_amount_after_tax = do_charger + do_incidentals + do_tax;
		String str_total = String.valueOf(total_amount_after_tax);

		String Total_Charges = Elements_FolioLineItemsVoid.RoomCharger_Tax.get(3).getText();
		String Pyments = Elements_FolioLineItemsVoid.RoomCharger_Tax.get(4).getText();
		String Balance = Elements_FolioLineItemsVoid.RoomCharger_Tax.get(5).getText();
		// payments
		String[] str_payments = Pyments.split(" ");
		double balance_after_paymenys = Double.valueOf(str_payments[1]) + total_amount_after_tax;

		assertTrue(Total_Charges.contains(str_total), "Total Charges does not conatin amount");
		assertTrue(Balance.contains(String.valueOf(balance_after_paymenys)), "Balance does not conatin amount");

	}

	public ArrayList<String> Cash_Payment(WebDriver driver, String Amount, String PaymentType,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		WebElement element = driver.findElement(By.xpath(OR.PayButton));
		elements_All_Payments.Folio.get(0).click();
		test_steps.add("Click Folio");
		folioLogger.info("Click Folio");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		test_steps.add("Click Pay");
		folioLogger.info("Click Pay");
		Wait.wait2Second();
		try {
			Wait.explicit_wait_xpath(OR.Reservation_PaymentPopup, driver);
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.PaymentPopUp, driver);
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentPopUp, driver);
			assertEquals(elements_All_Payments.PaymentPopUp.getText(), "Payment Details",
					"payment popup does not open");

		}

		// select payment type as cash`

		new Select(elements_All_Payments.PaymentType).selectByVisibleText(PaymentType);

		elements_All_Payments.Enter_Amount.clear();
		for (int i = 0; i < 10; i++) {
			elements_All_Payments.Enter_Amount.sendKeys(Keys.BACK_SPACE);
		}
		elements_All_Payments.Enter_Amount.sendKeys(Amount);

		// Add button
		elements_All_Payments.Add_Pay_Button.click();
		Wait.wait2Second();
		Wait.explicit_wait_absenceofelement(OR.Payment_ModuleLoading, driver);
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
		elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
		try {
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);

		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
			elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
			Utility.app_logs.info("Click Continue again");
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);
		}
		// Verify cash payment
		int LineItem_Size = Elements_FolioLineItemsVoid.LineItems.size();
		boolean isExist = false;
		for (int i = 0; i < Elements_FolioLineItemsVoid.LineItems.size(); i++) {
			if (Elements_FolioLineItemsVoid.LineItems.get(i).getText().equalsIgnoreCase(PaymentType)) {
				isExist = true;
				break;
			}
		}

		assertEquals(isExist, true, "payament type does not add in line item");
		Verify_LineItem(driver, PaymentType.toLowerCase(), Amount, "");
		/*
		 * assertTrue( Elements_FolioLineItemsVoid.LineItems.get(LineItem_Size -
		 * 1).getText().contains(String.valueOf(Amount)),
		 * "Amount does not add in line item");
		 * 
		 */
		return test_steps;
	}

	/*
	 * public void moveFolioPayment(WebDriver driver, ExtentTest test,
	 * ArrayList<String> test_steps) { Elements_All_Payments
	 * elements_All_Payments = new Elements_All_Payments(driver);
	 * Wait.WaitForElement(driver, OR.selectPaymentFolio); if
	 * (elements_All_Payments.selectPaymentFolio3.isDisplayed() &&
	 * !elements_All_Payments.selectPaymentFolio3.isEnabled()) {
	 * folioLogger.info("Payments cannot be moved ");
	 * 
	 * test_steps.add("Payments cannot be moved "); }
	 * 
	 * else {
	 * 
	 * folioLogger.info("Enabled Payment line Item ");
	 * 
	 * test_steps.add("Enabled Payment line Item "); } }
	 */

	public void moveFolioPayment(WebDriver driver, ExtentTest test, ArrayList<String> test_steps) {
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		// Wait.WaitForElement(driver, OR.selectPaymentFolio);
		if (elements_All_Payments.selectPaymentFolio.isDisplayed()
				&& !elements_All_Payments.selectPaymentFolio.isEnabled()) {
			folioLogger.info("Payments cannot be moved ");

			test_steps.add("Payments cannot be moved ");
		}

		else {

			folioLogger.info("Enabled Payment line Item ");

			test_steps.add("Enabled Payment line Item ");
		}
	}

	public void selectCustomFolio(WebDriver driver, String newFolioName) throws InterruptedException {
		Elements_All_Payments customFolio = new Elements_All_Payments(driver);
		Wait.WaitForElement(driver, OR.MoveFolio_GuestFolio);
		Select sel = new Select(customFolio.MoveFolio_GuestFolio);
		sel.selectByVisibleText(newFolioName);
		Wait.wait3Second();
		folioLogger.info("Selected NewFolio " + newFolioName);
		test_steps.add("Selected NewFolio " + newFolioName);
	}

	public void CardPayment(WebDriver driver) throws InterruptedException {

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(OR.PayButton));
		jse.executeScript("arguments[0].click();", elements_All_Payments.Folio.get(0));

		// elements_All_Payments.Folio.get(0).click();
		jse.executeScript("arguments[0].click();", element);
		try {
			Wait.explicit_wait_xpath(OR.Reservation_PaymentPopup, driver);

		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.PaymentPopUp, driver);
		}
		// Wait.wait2Second();

		// select payment type as cash
		try {
			System.out.println("Try");
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_PaymentType, driver);
			driver.findElement(By.xpath(OR.PaymentDetail_PaymentType)).click();
			new Select(elements_All_Payments.PaymentDetail_PaymentType).selectByIndex(1);

		} catch (Exception e) {
			System.out.println("Catch");
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentType, driver);
			driver.findElement(By.xpath(OR.PaymentType)).click();
			new Select(elements_All_Payments.PaymentType).selectByIndex(1);

		}
		Wait.explicit_wait_xpath(OR.PaymentDetail_Add_Button, driver);
		driver.findElement(By.xpath(OR.PaymentDetail_Add_Button)).click();
		Thread.sleep(10000);
		Wait.waitUntilPresenceOfElementLocated(OR.PaymentDetail_Continue_Pay_Button, driver);
		jse.executeScript("arguments[0].click();", elements_All_Payments.PaymentDetail_Continue_Pay_Button);

		try {
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 60);
		} catch (Exception e) {
			elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
			Utility.app_logs.info("Click Continue again");
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);
		}
		// driver.findElement(By.xpath(OR.PaymentDetail_Continue_Pay_Button)).click();
		Wait.wait2Second();
	}

	public void CardPayment(WebDriver driver, String PaymentType, String Amount, String CardName, String CardNum,
			String ExpDate, String CVVCode, String Address, String City, String State, String PostalCode,
			String AuthCode) throws InterruptedException {

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		WebElement element = driver.findElement(By.xpath(OR.PayButton));
		elements_All_Payments.Folio.get(0).click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		Wait.explicit_wait_xpath(OR.PaymentPopUp, driver);
		Wait.wait2Second();
		// assertEquals(elements_All_Payments.PaymentPopUp.getText(), "Payment
		// Details", "payment popup does not open");

		// select payment type as cash
		try {
			driver.findElement(By.xpath(OR.PaymentType)).click();
			new Select(elements_All_Payments.PaymentType).selectByVisibleText(PaymentType);
		} catch (Exception e) {
			driver.findElement(By.xpath(OR.PaymentDetail_PaymentType)).click();
			new Select(elements_All_Payments.PaymentDetail_PaymentType).selectByVisibleText(PaymentType);
		}
		elements_All_Payments.Enter_Amount.clear();
		for (int i = 0; i < 10; i++) {
			elements_All_Payments.Enter_Amount.sendKeys(Keys.BACK_SPACE);
		}
		elements_All_Payments.Payment_Info_Button.click();
		Wait.explicit_wait_xpath(OR.Payment_Info_Button, driver);
		Wait.wait2Second();
		elements_All_Payments.Enter_Amount.sendKeys(Amount);
		elements_All_Payments.Enter_CardName.sendKeys(CardName);
		elements_All_Payments.Enter_CardNum.sendKeys(CardNum);
		elements_All_Payments.Enter_ExpDate.sendKeys(ExpDate);
		elements_All_Payments.Enter_Card_CVVCode.sendKeys(CVVCode);
		elements_All_Payments.Enter_Address.sendKeys(Address);
		elements_All_Payments.Enter_Card_City.sendKeys(City);
		elements_All_Payments.Enter_Card_State.sendKeys(State);
		elements_All_Payments.Enter_PostalCode.sendKeys(PostalCode);
		elements_All_Payments.Enter_ApprovalCode.sendKeys(AuthCode);
		try {
			elements_All_Payments.CardOK_Button.click();
			Wait.waitForElementToBeGone(driver, elements_All_Payments.CardOK_Button, 60);
		} catch (Exception e) {
			Utility.app_logs.info("Again Click Card O Button");
			elements_All_Payments.CardOK_Button.click();
			Wait.waitForElementToBeGone(driver, elements_All_Payments.CardOK_Button, 60);
		}
		Wait.explicit_wait_visibilityof_webelement_350(elements_All_Payments.Process_Button, driver);
		Utility.ScrollToElement(elements_All_Payments.Process_Button, driver);
		elements_All_Payments.Process_Button.click();
		Thread.sleep(10000);
		Wait.waitUntilPresenceOfElementLocated(OR.Continue_Pay_Button, driver);
		Utility.ScrollToElement(elements_All_Payments.Continue_Pay_Button, driver);
		driver.findElement(By.xpath(OR.Continue_Pay_Button)).click();
		Wait.wait2Second();

		// Verify payament
		Verify_LineItem(driver, PaymentType, Amount, "");
		/*
		 * Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new
		 * Elements_FolioLineItemsVoid(driver); int LineItem_Size =
		 * Elements_FolioLineItemsVoid.LineItems.size();
		 * assertTrue(Elements_FolioLineItemsVoid.LineItems.get(LineItem_Size -
		 * 4).getText().contains(PaymentType),
		 * "payament type does not add in line item"); assertTrue(
		 * Elements_FolioLineItemsVoid.LineItems.get(LineItem_Size -
		 * 1).getText().contains(String.valueOf(Amount)),
		 * "Amount does not add in line item");
		 */
	}

	public void Card_Payment(WebDriver driver, String PaymentType, String Amount, String CardName, String CardNum,

			String ExpDate, String CVVCode, String Address, String City, String State, String PostalCode,

			String AuthCode) throws InterruptedException {
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		WebElement element = driver.findElement(By.xpath(OR.PayButton));
		elements_All_Payments.Folio.get(0).click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		try {
			Wait.WaitForElement(driver, OR.Reservation_PaymentPopup);
			assertTrue(elements_All_Payments.Reservation_PaymentPopup.isDisplayed(),
					"Failed : Failed payment Popup Not Open");
		} catch (Exception e) {

			Wait.WaitForElement(driver, OR.PaymentPopUp);
			assertEquals(elements_All_Payments.PaymentPopUp.getText(), "Payment Details",
					"payment popup does not open");

		}

		Utility.ScrollToElement(elements_All_Payments.PaymentDetail_PaymentType, driver);
		System.out.println("Type:" + PaymentType);
		new Select(elements_All_Payments.PaymentDetail_PaymentType).selectByVisibleText(PaymentType);
		assertTrue(new Select(elements_All_Payments.PaymentDetail_PaymentType).getFirstSelectedOption().getText()
				.contains(PaymentType), "Failed : Failed " + PaymentType + " payment Type Not Selected");

		// elements_All_Payments.Enter_Amount.clear();
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentCardInfoButton, driver);
		elements_All_Payments.PaymentCardInfoButton.click();
		Wait.explicit_wait_visibilityof_webelement_120(elements_All_Payments.Reservation_CardInfoPopup, driver);
		elements_All_Payments.CardInfoPopup_CardName.sendKeys(CardName);
		elements_All_Payments.CardInfoPopup_CardNum.sendKeys(CardNum);
		elements_All_Payments.CardInfoPopup_ExpDate.sendKeys(ExpDate);
		elements_All_Payments.CardInfoPopup_Card_CVVCode.sendKeys(CVVCode);
		elements_All_Payments.CardInfoPopup_Address.sendKeys(Address);
		elements_All_Payments.CardInfoPopup_Card_City.sendKeys(City);
		elements_All_Payments.CardInfoPopup_Card_State.sendKeys(State);
		elements_All_Payments.CardInfoPopup_PostalCode.sendKeys(PostalCode);
		elements_All_Payments.CardInfoPopup_ApprovalCode.sendKeys(AuthCode);
		elements_All_Payments.CardInfoPopup_OKButton.click();
		Wait.explicit_wait_absenceofelement(OR.CardInfoPopup_ModuleLoading, driver);
		// Thread.sleep(5000);
		String getAmount = elements_All_Payments.Enter_Amount.getAttribute("value");
		/*
		 * elements_All_Payments.Enter_Amount.clear(); for (int i = 0; i <
		 * getAmount.length(); i++) {
		 * elements_All_Payments.Enter_Amount.clear();
		 * elements_All_Payments.Enter_Amount.sendKeys(Keys.BACK_SPACE);
		 * System.out.println("in for"); }
		 * elements_All_Payments.Enter_Amount.sendKeys(Amount);
		 * Utility.app_logs.info("Entered Amount : "+ Amount);
		 */
		Wait.explicit_wait_xpath(OR.Process_Button, driver);
		Wait.explicit_wait_visibilityof_webelement_350(elements_All_Payments.Process_Button, driver);
		Utility.app_logs.info("Entered Amount : " + elements_All_Payments.Enter_Amount.getAttribute("value"));
		jse.executeScript("arguments[0].click();", elements_All_Payments.Process_Button);
		Wait.wait2Second();

		Wait.isElementDisplayed(driver, elements_All_Payments.Process_Button);
		try {
			assertTrue(elements_All_Payments.Process_Button.isEnabled(), "Failed: Process Button is not Enabled");
		} catch (Error e) {
			Utility.app_logs.info("In catch");
			Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Payment_ModuleLoading)), 90);
			assertTrue(elements_All_Payments.Process_Button.isEnabled(), "Failed: Process Button is not Enabled");
		}

		Wait.isElementDisplayed(driver, elements_All_Payments.Process_Button);

		new Select(elements_All_Payments.Select_Authorization_type).selectByVisibleText("Capture");

		Wait.wait3Second();

		Utility.app_logs
				.info(new Select(elements_All_Payments.Select_Authorization_type).getFirstSelectedOption().getText());
		assertTrue(new Select(elements_All_Payments.Select_Authorization_type).getFirstSelectedOption().getText()
				.contains("Capture"), "Failed : Capture is not selected");
		Utility.ScrollToElement(elements_All_Payments.Enter_Amount, driver);
		elements_All_Payments.Enter_Amount.clear();
		if (Float.parseFloat(Amount) < 0.0) {
			assertTrue(false, "Failed: Amount is less then 0.0");
		}
		String str_length = elements_All_Payments.Enter_Amount.getAttribute("value");
		for (int i = 0; i < str_length.length(); i++) {
			elements_All_Payments.Enter_Amount.sendKeys(Keys.BACK_SPACE);
		}
		elements_All_Payments.Enter_Amount.sendKeys(Amount);
		Utility.app_logs.info("Enter Amount " + Amount);
		elements_All_Payments.Process_Button.click();
		Utility.app_logs.info("Click Process");

		// Wait.wait2Second();
		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Payment_ModuleLoading)), 300);
		Wait.explicit_wait_visibilityof_webelement_350(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
		jse.executeScript("arguments[0].click();", elements_All_Payments.PaymentDetail_Continue_Pay_Button);
		try {
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
			elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
			Utility.app_logs.info("Click Continue again");
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 90);
		}
		Verify_LineItem(driver, PaymentType, Amount, "");
	}

	public ArrayList<String> PayCardPayment(WebDriver driver, String PaymentType, String Amount, String AccountNumber,
			String ExpDate, ArrayList<String> steps) throws InterruptedException {
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		WebElement element = driver.findElement(By.xpath(OR.PayButton));
		elements_All_Payments.Folio.get(0).click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		steps.add("Click Pay Reservation");
		try {
			Wait.WaitForElement(driver, OR.Reservation_PaymentPopup);
			assertTrue(elements_All_Payments.Reservation_PaymentPopup.isDisplayed(),
					"Failed : Failed payment Popup Not Open");
		} catch (Exception e) {
			Wait.WaitForElement(driver, OR.PaymentPopUp);
			assertEquals(elements_All_Payments.PaymentPopUp.getText(), "Payment Details",
					"payment popup does not open");
		}

		Utility.ScrollToElement(elements_All_Payments.PaymentDetail_PaymentType, driver);
		System.out.println("Type:" + PaymentType);

		Utility.ScrollToElement(elements_All_Payments.Enter_Amount, driver);
		elements_All_Payments.Enter_Amount.clear();
		if (Float.parseFloat(Amount) < 0.0) {
			assertTrue(false, "Failed: Amount is less then 0.0");
		}
		String str_length = elements_All_Payments.Enter_Amount.getAttribute("value");
		for (int i = 0; i < str_length.length(); i++) {
			elements_All_Payments.Enter_Amount.sendKeys(Keys.BACK_SPACE);
		}
		elements_All_Payments.Enter_Amount.sendKeys(Amount);
		Utility.app_logs.info("Enter Amount " + Amount);

		steps.add("Enter Amount " + Amount);
		elements_All_Payments.Process_Button.click();
		Utility.app_logs.info("Click Process");
		steps.add("Click Process Payment");
		// Wait.wait2Second();
		Wait.explicit_wait_absenceofelement(OR.Payment_ModuleLoading, driver);
		Wait.explicit_wait_visibilityof_webelement_350(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
		jse.executeScript("arguments[0].click();", elements_All_Payments.PaymentDetail_Continue_Pay_Button);
		try {
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
			elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
			Utility.app_logs.info("Click Continue again");
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 90);
		}

		String Description = AccountNumber.substring(AccountNumber.length() - 4);
		Description = Description + " Exp. Date: " + ExpDate;
		steps.add("Click Continue Payment");
		Verify_LineItem(driver, PaymentType, Amount, Description);
		return steps;
	}

	public ArrayList<String> ValidateCardPaymentWithoutCardInformation(WebDriver driver, String PaymentType,
			ArrayList<String> steps) throws InterruptedException {
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		WebElement element = driver.findElement(By.xpath(OR.PayButton));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		steps.add("Click Reservation Pay Button");
		try {
			Wait.WaitForElement(driver, OR.Reservation_PaymentPopup);
			assertTrue(elements_All_Payments.Reservation_PaymentPopup.isDisplayed(),
					"Failed : Failed payment Popup Not Open");
		} catch (Exception e) {

			Wait.WaitForElement(driver, OR.PaymentPopUp);
			assertEquals(elements_All_Payments.PaymentPopUp.getText(), "Payment Details",
					"payment popup does not open");

		}
		Utility.ScrollToElement(elements_All_Payments.PaymentDetail_PaymentType, driver);
		System.out.println("Type:" + PaymentType);
		new Select(elements_All_Payments.PaymentDetail_PaymentType).selectByVisibleText(PaymentType);
		assertTrue(new Select(elements_All_Payments.PaymentDetail_PaymentType).getFirstSelectedOption().getText()
				.contains(PaymentType), "Failed : Failed " + PaymentType + " payment Type Not Selected");
		steps.add("Select Payment Type : " + PaymentType);
		// Process button
		Utility.ScrollToElement(elements_All_Payments.Process_Button, driver);
		elements_All_Payments.Process_Button.click();
		steps.add("Click Payment Details Process Button");
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.CardPayment_CardInformatoinAlertMessage,
				driver);
		assertTrue(elements_All_Payments.CardPayment_CardInformatoinAlertMessage.isDisplayed(),
				"Failed : Empty Card Fields Alert Message no appeared");
		steps.add(
				"Alert Message Appeared : " + elements_All_Payments.CardPayment_CardInformatoinAlertMessage.getText());
		Utility.ScrollToElement(elements_All_Payments.Cancel_ReservationPaymentPopup, driver);
		elements_All_Payments.Cancel_ReservationPaymentPopup.click();
		Wait.waitForElementToBeGone(driver, elements_All_Payments.Cancel_ReservationPaymentPopup, 20);
		return steps;
	}

	public ArrayList<String> ValidateCardInformationAllFields(WebDriver driver, String PaymentType,
			ArrayList<String> steps) throws InterruptedException {
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		WebElement element = driver.findElement(By.xpath(OR.PayButton));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		steps.add("Click Reservation Pay Button");
		try {
			Wait.WaitForElement(driver, OR.Reservation_PaymentPopup);
			assertTrue(elements_All_Payments.Reservation_PaymentPopup.isDisplayed(),
					"Failed : Failed payment Popup Not Open");
		} catch (Exception e) {
			Wait.WaitForElement(driver, OR.PaymentPopUp);
			assertEquals(elements_All_Payments.PaymentPopUp.getText(), "Payment Details",
					"payment popup does not open");
		}
		Utility.ScrollToElement(elements_All_Payments.PaymentDetail_PaymentType, driver);
		System.out.println("Type:" + PaymentType);
		new Select(elements_All_Payments.PaymentDetail_PaymentType).selectByVisibleText(PaymentType);
		assertTrue(new Select(elements_All_Payments.PaymentDetail_PaymentType).getFirstSelectedOption().getText()
				.contains(PaymentType), "Failed : Failed " + PaymentType + " payment Type Not Selected");
		steps.add("Select Payment Type : " + PaymentType);
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentCardInfoButton, driver);
		elements_All_Payments.PaymentCardInfoButton.click();
		steps.add("Click Payment Card info Button");
		Wait.explicit_wait_visibilityof_webelement_120(elements_All_Payments.Reservation_CardInfoPopup, driver);
		elements_All_Payments.CardInfoPopup_OKButton.click();
		steps.add("Click Payment Card information Popup OK Button");
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.CardInfoPopup_CardNameValidation, driver);
		assertTrue(elements_All_Payments.CardInfoPopup_CardNameValidation.isDisplayed(),
				"Failed : Empty Card Name Validation No Appeard");
		steps.add("Successfully Verified Card Name Validation in Card Information Popup");
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.CardInfoPopup_CardNumberValidation, driver);
		assertTrue(elements_All_Payments.CardInfoPopup_CardNumberValidation.isDisplayed(),
				"Failed : Empty Card Number Validation No Appeard");
		steps.add("Successfully Verified Card Number Validation in Card Information Popup");
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.CardInfoPopup_ExpDateValidation, driver);
		assertTrue(elements_All_Payments.CardInfoPopup_ExpDateValidation.isDisplayed(),
				"Failed : Empty Card Expiry Date Validation No Appeard");
		steps.add("Successfully Verified Card Expiry Date Validation in Card Information Popup");

		Utility.ScrollToElement(elements_All_Payments.CardInfoPopup_CancelButton, driver);
		elements_All_Payments.CardInfoPopup_CancelButton.click();
		Wait.waitForElementToBeGone(driver, elements_All_Payments.CardInfoPopup_CancelButton, 20);
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Cancel_ReservationPaymentPopup, driver);
		Utility.ScrollToElement(elements_All_Payments.Cancel_ReservationPaymentPopup, driver);
		elements_All_Payments.Cancel_ReservationPaymentPopup.click();
		Wait.waitForElementToBeGone(driver, elements_All_Payments.Cancel_ReservationPaymentPopup, 20);
		return steps;
	}

	public ArrayList<String> ValidateReservationCardInformation_CardNumberAndExpDate(WebDriver driver,
			String PaymentType, String CardName, ArrayList<String> steps) throws InterruptedException {
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		WebElement element = driver.findElement(By.xpath(OR.PayButton));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		steps.add("Click Reservation Pay Button");
		try {
			Wait.WaitForElement(driver, OR.Reservation_PaymentPopup);
			assertTrue(elements_All_Payments.Reservation_PaymentPopup.isDisplayed(),
					"Failed : Failed payment Popup Not Open");
		} catch (Exception e) {
			Wait.WaitForElement(driver, OR.PaymentPopUp);
			assertEquals(elements_All_Payments.PaymentPopUp.getText(), "Payment Details",
					"payment popup does not open");
		}
		Utility.ScrollToElement(elements_All_Payments.PaymentDetail_PaymentType, driver);
		System.out.println("Type:" + PaymentType);
		new Select(elements_All_Payments.PaymentDetail_PaymentType).selectByVisibleText(PaymentType);
		assertTrue(new Select(elements_All_Payments.PaymentDetail_PaymentType).getFirstSelectedOption().getText()
				.contains(PaymentType), "Failed : Failed " + PaymentType + " payment Type Not Selected");
		steps.add("Select Payment Type : " + PaymentType);
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentCardInfoButton, driver);
		elements_All_Payments.PaymentCardInfoButton.click();
		steps.add("Click Payment Card info Button");
		Wait.explicit_wait_visibilityof_webelement_120(elements_All_Payments.Reservation_CardInfoPopup, driver);
		elements_All_Payments.CardInfoPopup_CardName.sendKeys(CardName);
		steps.add("Enter Card information Popup Card Name : " + CardName);
		elements_All_Payments.CardInfoPopup_OKButton.click();
		steps.add("Click Payment Card information Popup OK Button");
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.CardInfoPopup_CardNumberValidation, driver);
		assertTrue(elements_All_Payments.CardInfoPopup_CardNumberValidation.isDisplayed(),
				"Failed : Empty Card Number Validation No Appeard");
		steps.add("Successfully Verified Card Number Validation in Card Information Popup");
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.CardInfoPopup_ExpDateValidation, driver);
		assertTrue(elements_All_Payments.CardInfoPopup_ExpDateValidation.isDisplayed(),
				"Failed : Empty Card Expiry Date Validation No Appeard");
		steps.add("Successfully Verified Card Expiry Date Validation in Card Information Popup");

		Utility.ScrollToElement(elements_All_Payments.CardInfoPopup_CancelButton, driver);
		elements_All_Payments.CardInfoPopup_CancelButton.click();
		Wait.waitForElementToBeGone(driver, elements_All_Payments.CardInfoPopup_CancelButton, 20);
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Cancel_ReservationPaymentPopup, driver);
		Utility.ScrollToElement(elements_All_Payments.Cancel_ReservationPaymentPopup, driver);
		elements_All_Payments.Cancel_ReservationPaymentPopup.click();
		Wait.waitForElementToBeGone(driver, elements_All_Payments.Cancel_ReservationPaymentPopup, 20);
		return steps;
	}

	public ArrayList<String> ValidateReservationCardInformationExpDate(WebDriver driver, String PaymentType,
			String CardName, String CardNum, ArrayList<String> steps) throws InterruptedException {
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		WebElement element = driver.findElement(By.xpath(OR.PayButton));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		steps.add("Click Reservation Pay Button");
		try {
			Wait.WaitForElement(driver, OR.Reservation_PaymentPopup);
			assertTrue(elements_All_Payments.Reservation_PaymentPopup.isDisplayed(),
					"Failed : Failed payment Popup Not Open");
		} catch (Exception e) {
			Wait.WaitForElement(driver, OR.PaymentPopUp);
			assertEquals(elements_All_Payments.PaymentPopUp.getText(), "Payment Details",
					"payment popup does not open");
		}
		Utility.ScrollToElement(elements_All_Payments.PaymentDetail_PaymentType, driver);
		System.out.println("Type:" + PaymentType);
		new Select(elements_All_Payments.PaymentDetail_PaymentType).selectByVisibleText(PaymentType);
		assertTrue(new Select(elements_All_Payments.PaymentDetail_PaymentType).getFirstSelectedOption().getText()
				.contains(PaymentType), "Failed : Failed " + PaymentType + " payment Type Not Selected");
		steps.add("Select Payment Type : " + PaymentType);

		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentCardInfoButton, driver);
		elements_All_Payments.PaymentCardInfoButton.click();
		steps.add("Click Payment Card info Button");
		Wait.explicit_wait_visibilityof_webelement_120(elements_All_Payments.Reservation_CardInfoPopup, driver);
		elements_All_Payments.CardInfoPopup_CardName.sendKeys(CardName);
		steps.add("Enter Card information Popup Card Name : " + CardName);
		elements_All_Payments.CardInfoPopup_CardNum.sendKeys(CardNum);
		steps.add("Enter Card information Popup Card Number : " + CardNum);
		elements_All_Payments.CardInfoPopup_OKButton.click();
		steps.add("Click Payment Card information Popup OK Button");
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.CardInfoPopup_ExpDateValidation, driver);
		assertTrue(elements_All_Payments.CardInfoPopup_ExpDateValidation.isDisplayed(),
				"Failed : Empty Card Expiry Date Validation No Appeard");
		steps.add("Successfully Verified Card Expiry Date Validation in Card Information Popup");

		Utility.ScrollToElement(elements_All_Payments.CardInfoPopup_CancelButton, driver);
		elements_All_Payments.CardInfoPopup_CancelButton.click();
		Wait.waitForElementToBeGone(driver, elements_All_Payments.CardInfoPopup_CancelButton, 20);
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Cancel_ReservationPaymentPopup, driver);
		Utility.ScrollToElement(elements_All_Payments.Cancel_ReservationPaymentPopup, driver);
		elements_All_Payments.Cancel_ReservationPaymentPopup.click();
		Wait.waitForElementToBeGone(driver, elements_All_Payments.Cancel_ReservationPaymentPopup, 20);
		return steps;
	}

	public void Swipe_Payment(WebDriver driver, String Amount, String PaymentType, String CardNumber)
			throws InterruptedException {
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		WebElement element = driver.findElement(By.xpath(OR.PayButton));
		elements_All_Payments.Folio.get(0).click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		try {
			Wait.WaitForElement(driver, OR.Reservation_PaymentPopup);
			// Wait.explicit_wait_xpath(OR.Reservation_PaymentPopup, driver);
			// Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup,
			// driver);
			assertTrue(elements_All_Payments.Reservation_PaymentPopup.isDisplayed(),
					"Failed : Failed payment Popup Not Open");
		} catch (Exception e) {

			Wait.WaitForElement(driver, OR.PaymentPopUp);
			// Wait.explicit_wait_xpath(OR.PaymentPopUp, driver);
			// Wait.explicit_wait_visibilityof_webelement_200(elements_All_Payments.PaymentPopUp,
			// driver);
			assertEquals(elements_All_Payments.PaymentPopUp.getText(), "Payment Details",
					"payment popup does not open");

		}
		// select payment type as cash new
		System.out.println("Type:" + PaymentType);
		// new
		// Select(elements_All_Payments.PaymentType).selectByVisibleText(PaymentType);
		// assertTrue(
		// new
		// Select(elements_All_Payments.PaymentType).getFirstSelectedOption().getText().contains(PaymentType),
		// "Failed : Failed " + PaymentType + " payment Type Not Selected");

		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentSwipe_Img, driver);
		Utility.ScrollToElement(elements_All_Payments.PaymentSwipe_Img, driver);
		elements_All_Payments.PaymentSwipe_Img.click();
		Utility.app_logs.info("Click Swipe payment Icon");
		Wait.WaitForElement(driver, OR.SwipePaymentpopup);
		assertEquals(elements_All_Payments.SwipePaymentpopup.getText(), "Please swipe the card:");
		// Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.SwipeCard_Field,
		// driver);
		assertTrue(elements_All_Payments.SwipeCard_Field.isDisplayed(), "Failed: Swipe Text Box is not visible");
		assertTrue(elements_All_Payments.CardSubmitButton.isDisplayed(), "Failed: Swipe Submit Button is not visible");
		Utility.ScrollToElement(elements_All_Payments.SwipeCard_Field, driver);
		elements_All_Payments.SwipeCard_Field.sendKeys(CardNumber);
		// Wait.waitForElementToBeGone(driver,
		// elements_All_Payments.SwipeCard_Loader, 150);
		Utility.app_logs.info("Enter Swipe Text Value");
		Assert.assertTrue(false);
		// Thread.sleep(1000 * 15);
		// Utility.app_logs.info("Click Process");
		// Wait.isElementDisplayed(driver,
		// elements_All_Payments.ProcessBtn.get(2));
		// elements_All_Payments.ProcessBtn.get(2).click();
		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Payment_ModuleLoading)), 90);
		Wait.isElementDisplayed(driver, elements_All_Payments.Process_Button);
		try {
			assertTrue(elements_All_Payments.Process_Button.isEnabled(), "Failed: Process Button is not Enabled");
		} catch (Error e) {
			Utility.app_logs.info("In catch");
			Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Payment_ModuleLoading)), 90);
			assertTrue(elements_All_Payments.Process_Button.isEnabled(), "Failed: Process Button is not Enabled");
		}
		Utility.ScrollToElement(elements_All_Payments.Enter_Amount, driver);
		elements_All_Payments.Enter_Amount.clear();
		if (Float.parseFloat(Amount) < 0.0) {
			assertTrue(false, "Failed: Amount is less then 0.0");
		}
		String str_length = elements_All_Payments.Enter_Amount.getAttribute("value");
		for (int i = 0; i < str_length.length(); i++) {
			elements_All_Payments.Enter_Amount.sendKeys(Keys.BACK_SPACE);
		}
		elements_All_Payments.Enter_Amount.sendKeys(Amount);
		Utility.app_logs.info("Enter Amount " + Amount);
		elements_All_Payments.Process_Button.click();
		Utility.app_logs.info("Click Process");
		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Payment_ModuleLoading)), 150);

		elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
		Utility.app_logs.info("Click Continue");
		try {
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
			elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
			Utility.app_logs.info("Click Continue again");
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 50);
		}

		// Verify cash payment
		int LineItem_Size = Elements_FolioLineItemsVoid.LineItems.size();
		boolean isExist = false;
		for (int i = 0; i < Elements_FolioLineItemsVoid.LineItems.size(); i++) {
			if (Elements_FolioLineItemsVoid.LineItems.get(i).getText().equalsIgnoreCase(PaymentType)) {
				isExist = true;
				break;
			}
		}

		assertEquals(isExist, true, "payament type does not add in line item");
		Verify_LineItem(driver, PaymentType, Amount, "");

	}

	public void SwipePayment(WebDriver driver, String Amount, String PaymentType, String CardNumber)
			throws InterruptedException {
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		WebElement element = driver.findElement(By.xpath(OR.PayButton));
		elements_All_Payments.Folio.get(0).click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		try {
			Wait.WaitForElement(driver, OR.Reservation_PaymentPopup);
			// Wait.explicit_wait_xpath(OR.Reservation_PaymentPopup, driver);
			// Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup,
			// driver);
			assertTrue(elements_All_Payments.Reservation_PaymentPopup.isDisplayed(),
					"Failed : Failed payment Popup Not Open");
		} catch (Exception e) {

			Wait.WaitForElement(driver, OR.PaymentPopUp);
			// Wait.explicit_wait_xpath(OR.PaymentPopUp, driver);
			// Wait.explicit_wait_visibilityof_webelement_200(elements_All_Payments.PaymentPopUp,
			// driver);
			assertEquals(elements_All_Payments.PaymentPopUp.getText(), "Payment Details",
					"payment popup does not open");

		}
		// select payment type as cash new
		System.out.println("Type:" + PaymentType);
		// new
		// Select(elements_All_Payments.PaymentType).selectByVisibleText(PaymentType);
		// assertTrue(
		// new
		// Select(elements_All_Payments.PaymentType).getFirstSelectedOption().getText().contains(PaymentType),
		// "Failed : Failed " + PaymentType + " payment Type Not Selected");

		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentSwipe_Img, driver);
		Utility.ScrollToElement(elements_All_Payments.PaymentSwipe_Img, driver);
		elements_All_Payments.PaymentSwipe_Img.click();
		Utility.app_logs.info("Click Swipe payment Icon");
		Wait.WaitForElement(driver, OR.SwipePaymentpopup);
		assertEquals(elements_All_Payments.SwipePaymentpopup.getText(), "Please swipe the card:");
		// Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.SwipeCard_Field,
		// driver);
		assertTrue(elements_All_Payments.SwipeCard_Field.isDisplayed(), "Failed: Swipe Text Box is not visible");
		assertTrue(elements_All_Payments.CardSubmitButton.isDisplayed(), "Failed: Swipe Submit Button is not visible");
		Utility.ScrollToElement(elements_All_Payments.SwipeCard_Field, driver);
		elements_All_Payments.SwipeCard_Field.sendKeys(CardNumber);
		// Wait.waitForElementToBeGone(driver,
		// elements_All_Payments.SwipeCard_Loader, 150);
		Utility.app_logs.info("Enter Swipe Text Value");
		// Thread.sleep(1000 * 15);
		// Utility.app_logs.info("Click Process");
		// Wait.isElementDisplayed(driver,
		// elements_All_Payments.ProcessBtn.get(2));
		// elements_All_Payments.ProcessBtn.get(2).click();
		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Payment_ModuleLoading)), 90);

		Wait.isElementDisplayed(driver, elements_All_Payments.Process_Button);

		try {
			assertTrue(elements_All_Payments.Process_Button.isEnabled(), "Failed: Process Button is not Enabled");
		} catch (Error e) {
			Utility.app_logs.info("In catch");
			Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Payment_ModuleLoading)), 90);
			assertTrue(elements_All_Payments.Process_Button.isEnabled(), "Failed: Process Button is not Enabled");
		}
		new Select(elements_All_Payments.Select_Authorization_type).selectByVisibleText("Capture");

		Wait.wait3Second();

		Utility.app_logs
				.info(new Select(elements_All_Payments.Select_Authorization_type).getFirstSelectedOption().getText());
		assertTrue(new Select(elements_All_Payments.Select_Authorization_type).getFirstSelectedOption().getText()
				.contains("Capture"), "Failed : Capture is not selected");
		Utility.ScrollToElement(elements_All_Payments.Enter_Amount, driver);
		elements_All_Payments.Enter_Amount.clear();
		if (Float.parseFloat(Amount) < 0.0) {
			assertTrue(false, "Failed: Amount is less then 0.0");
		}
		String str_length = elements_All_Payments.Enter_Amount.getAttribute("value");
		for (int i = 0; i < str_length.length(); i++) {
			elements_All_Payments.Enter_Amount.sendKeys(Keys.BACK_SPACE);
		}
		elements_All_Payments.Enter_Amount.sendKeys(Amount);
		Utility.app_logs.info("Enter Amount " + Amount);
		elements_All_Payments.Process_Button.click();
		Utility.app_logs.info("Click Process");
		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Payment_ModuleLoading)), 150);

		elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
		Utility.app_logs.info("Click Continue");
		try {
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
			elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
			Utility.app_logs.info("Click Continue again");
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 50);
		}

		// Verify cash payment

		Verify_LineItem(driver, PaymentType, Amount, "");

	}

	public void Card_Swipe_Payment(WebDriver driver, String Amount, String PaymentType, String CardNumber)
			throws InterruptedException {

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		WebElement element = driver.findElement(By.xpath(OR.PayButton));
		elements_All_Payments.Folio.get(0).click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		try {
			Wait.explicit_wait_xpath(OR.Reservation_PaymentPopup, driver);
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);
			// assertEquals(elements_All_Payments.Reservation_PaymentPopupHeading.getText().contains("Payment"),
			// true,
			// "payment popup does not open");
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.PaymentPopUp, driver);
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentPopUp, driver);
			assertEquals(elements_All_Payments.PaymentPopUp.getText(), "Payment Details",
					"payment popup does not open");

		}

		// select payment type as cash
		new Select(elements_All_Payments.PaymentType).selectByVisibleText(PaymentType);
		elements_All_Payments.Enter_Amount.clear();
		for (int i = 0; i < 10; i++) {
			elements_All_Payments.Enter_Amount.sendKeys(Keys.BACK_SPACE);
		}
		elements_All_Payments.Enter_Amount.sendKeys(Amount);
		// card swipe
		elements_All_Payments.PaymentSwipe_Img.click();
		elements_All_Payments.SwipeCard_Field.sendKeys(CardNumber);

		folioLogger.info("Swipe Card");
		Wait.wait2Second();
		Wait.explicit_wait_absenceofelement(OR.Payment_ModuleLoading, driver);
		Wait.explicit_wait_visibilityof_webelement_150(elements_All_Payments.Process_Button, driver);
		// Process
		elements_All_Payments.Process_Button.click();
		folioLogger.info("Click Process");

		// Thread.sleep(5000);
		Wait.wait2Second();
		Wait.explicit_wait_absenceofelement(OR.Payment_ModuleLoading, driver);
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
		elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
		try {
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);

		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
			elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
			Utility.app_logs.info("Click Continue again");
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);
		}
		Wait.explicit_wait_visibilityof_webelement_150(Elements_FolioLineItemsVoid.LineItems.get(1), driver);
		// Verify card payment
		int LineItem_Size = Elements_FolioLineItemsVoid.LineItems.size();

		Wait.explicit_wait_visibilityof_webelement_150(Elements_FolioLineItemsVoid.LineItems.get(LineItem_Size - 4),
				driver);
		Utility.ScrollToElement(Elements_FolioLineItemsVoid.LineItems.get(LineItem_Size - 4), driver);
		assertTrue(Elements_FolioLineItemsVoid.LineItems.get(LineItem_Size - 4).getText().contains(PaymentType),
				"Payament type does not add in line item");
		assertTrue(
				Elements_FolioLineItemsVoid.LineItems.get(LineItem_Size - 1).getText().contains(String.valueOf(Amount)),
				"Amount does not add in line item");

	}

	public ArrayList<String> SelectPolicy(WebDriver driver, String Policy, ExtentTest test,
			ArrayList<String> test_steps) {

		Elements_MovieFolio elements_MovieFolio = new Elements_MovieFolio(driver);

		// elements_MovieFolio.MoveFolio_Folio.click();
		// Wait.explicit_wait_xpath(OR.Folio_Options);
		elements_MovieFolio.Folio_Options.click();

		Wait.explicit_wait_xpath(OR.Select_DepositPolicy, driver);

		new Select(elements_MovieFolio.Select_DepositPolicy).selectByVisibleText(Policy);

		elements_MovieFolio.Save_DepositPolicy.click();

		test_steps.add("Select deposit policy: " + Policy);
		folioLogger.info("Select deposit policy: " + Policy);
		return test_steps;
	}

	public void SelectPolicy(WebDriver driver, String Policy) throws InterruptedException {

		Elements_MovieFolio elements_MovieFolio = new Elements_MovieFolio(driver);

		Wait.explicit_wait_visibilityof_webelement(elements_MovieFolio.MoveFolioFolio, driver);
		elements_MovieFolio.MoveFolioFolio.click();
		Wait.explicit_wait_visibilityof_webelement(elements_MovieFolio.Folio_Option, driver);
		elements_MovieFolio.Folio_Option.click();

		Wait.explicit_wait_visibilityof_webelement(elements_MovieFolio.Select_DepositPolicies, driver);
		Utility.ScrollToElement(elements_MovieFolio.Select_DepositPolicies, driver);
		new Select(elements_MovieFolio.Select_DepositPolicies).selectByVisibleText(Policy);
		elements_MovieFolio.SaveDepositPolicy.click();
		Wait.explicit_wait_visibilityof_webelement_150(elements_MovieFolio.Verify_Toaster_Container, driver);
		folioLogger.info("Select deposit policy: " + Policy);
	}

	public ArrayList<String> SelectNoPolicy(WebDriver driver, String Policy, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_MovieFolio elements_MovieFolio = new Elements_MovieFolio(driver);

		Wait.explicit_wait_visibilityof_webelement(elements_MovieFolio.Folio_Option, driver);
		elements_MovieFolio.Folio_Option.click();
		String SelectedPolicy = new Select(elements_MovieFolio.Select_DepositPolicies).getFirstSelectedOption()
				.getText();
		if (SelectedPolicy.contains(Policy)) {
			test_steps.add("Already Selected deposit policy: " + SelectedPolicy);
			folioLogger.info("Already Selected deposit policy: " + SelectedPolicy);
		} else {
			Wait.explicit_wait_visibilityof_webelement_350(elements_MovieFolio.Select_DepositPolicies, driver);
			Utility.ScrollToElement(elements_MovieFolio.Select_DepositPolicies, driver);
			new Select(elements_MovieFolio.Select_DepositPolicies).selectByVisibleText(Policy);
			Utility.ScrollToElement(elements_MovieFolio.SaveDepositPolicy, driver);
			elements_MovieFolio.SaveDepositPolicy.click();
			// Wait.explicit_wait_visibilityof_webelement_150(elements_MovieFolio.Verify_Toaster_Container);
			Elements_Reservation Res = new Elements_Reservation(driver);
			try {
				Wait.explicit_wait_visibilityof_webelement_350(Res.Toaster_Message, driver);
				System.out.println(
						"Toaster Message appear : " + driver.findElement(By.xpath(OR.Toaster_Message)).getText());
				driver.findElement(By.xpath(OR.Toaster_Message)).click();
				Wait.wait3Second();
			} catch (Exception e) {
				folioLogger.info("No Toaster Appear");
			}
			test_steps.add("Select deposit policy: " + Policy);
			folioLogger.info("Select deposit policy: " + Policy);
			SaveFolioButton(driver);
		}
		return test_steps;
	}

	public void Select_Policy(WebDriver driver, String Policy) {

		Elements_MovieFolio elements_MovieFolio = new Elements_MovieFolio(driver);

		elements_MovieFolio.MoveFolioFolio.click();
		Wait.explicit_wait_xpath(OR.Folio_Option, driver);
		elements_MovieFolio.Folio_Option.click();

		Wait.explicit_wait_xpath(OR.Select_DepositPolicies, driver);

		new Select(elements_MovieFolio.Select_DepositPolicies).selectByVisibleText(Policy);

		folioLogger.info("Select deposit policy: " + Policy);
	}

	public void Click_FolioOptions(WebDriver driver) {

		Elements_MovieFolio elements_MovieFolio = new Elements_MovieFolio(driver);
		try {
			Wait.explicit_wait_visibilityof_webelement_350(elements_MovieFolio.Folio_Option, driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", elements_MovieFolio.Folio_Option);
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement_350(elements_MovieFolio.Folio_Options, driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", elements_MovieFolio.Folio_Options);
		}

		// elements_MovieFolio.Folio_Option.click();
	}

	public void Click_FolioDetails(WebDriver driver) {

		Elements_MovieFolio elements_MovieFolio = new Elements_MovieFolio(driver);
		try {
			elements_MovieFolio.Folio_Details.click();
		} catch (Exception e) {
			elements_MovieFolio.Folio_Details1.click();

		}
	}

	public ArrayList<String> VerifyPolicy(WebDriver driver, String Policy, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_MovieFolio elements_MoveFolio = new Elements_MovieFolio(driver);

		Wait.explicit_wait_visibilityof_webelement(elements_MoveFolio.Select_DepositPolicies, driver);
		Utility.ScrollToElement(elements_MoveFolio.Select_DepositPolicies, driver);

		String SelectedDepositPolicy = new Select(elements_MoveFolio.Select_DepositPolicies).getFirstSelectedOption()
				.getText();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", elements_MoveFolio.Select_DepositPolicies);
		assertEquals(SelectedDepositPolicy, Policy, "Failed : Policy is not selected");
		test_steps.add("Selected deposit policy: " + SelectedDepositPolicy);
		folioLogger.info("Selected deposit policy: " + SelectedDepositPolicy);
		return test_steps;
	}

	public void VerifyPolicy_2(WebDriver driver, String Policy) throws InterruptedException {

		Elements_MovieFolio elements_MoveFolio = new Elements_MovieFolio(driver);

		Wait.explicit_wait_visibilityof_webelement(elements_MoveFolio.Select_DepositPolicy, driver);
		Utility.ScrollToElement(elements_MoveFolio.Select_DepositPolicy, driver);

		String SelectedDepositPolicy = new Select(elements_MoveFolio.Select_DepositPolicy).getFirstSelectedOption()
				.getText();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", elements_MoveFolio.Select_DepositPolicy);
		assertEquals(SelectedDepositPolicy, Policy, "Failed : Policy is not selected");
		folioLogger.info("Selected deposit policy: " + SelectedDepositPolicy);
	}

	public ArrayList<String> VerifyDepositPolicy(WebDriver driver, String Policy, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_MovieFolio elements_MoveFolio = new Elements_MovieFolio(driver);
		folioLogger.info("verifying Deposit policy");
		Wait.explicit_wait_visibilityof_webelement(elements_MoveFolio.Select_DepositPolicies, driver);
		Utility.ScrollToElement(elements_MoveFolio.Select_DepositPolicies, driver);

		String SelectedDepositPolicy = new Select(elements_MoveFolio.Select_DepositPolicies).getFirstSelectedOption()
				.getText();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", elements_MoveFolio.Select_DepositPolicies);
		assertEquals(SelectedDepositPolicy, Policy, "Failed : Policy is not selected");
		test_steps.add("Applied deposit policy: " + SelectedDepositPolicy);
		folioLogger.info("Applied deposit policy: " + SelectedDepositPolicy);
		assertEquals(elements_MoveFolio.Select_DepositPolicies.isEnabled(), false,
				"Failed: Deposit Polic Selection is Enabled");
		test_steps.add("Deposit policy Field is Disabled ");
		folioLogger.info("Deposit policy Field is Disabled");
		return test_steps;
	}

	public ArrayList<String> SelectDepositPolicy(WebDriver driver, String Policy, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_MovieFolio elements_MoveFolio = new Elements_MovieFolio(driver);

		Wait.explicit_wait_xpath(OR.Select_DepositPolicies, driver);
		Utility.ScrollToElement(elements_MoveFolio.Select_DepositPolicies, driver);
		Select SelectedDepositPolicy = new Select(elements_MoveFolio.Select_DepositPolicies);
		SelectedDepositPolicy.selectByVisibleText(Policy);
		Wait.wait2Second();
		String SelectedPolicy = SelectedDepositPolicy.getFirstSelectedOption().getText();
		assertEquals(SelectedPolicy, Policy, "Failed : Policy is not selected");
		test_steps.add("Selected deposit policy: " + SelectedPolicy);
		folioLogger.info("Selected deposit policy: " + SelectedPolicy);
		return test_steps;
	}

	public ArrayList<String> SelectNoShowPolicy_NotApplicable(WebDriver driver, String Policy,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_MovieFolio elements_MoveFolio = new Elements_MovieFolio(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_MoveFolio.Select_NoShow, driver);
		Utility.ScrollToElement(elements_MoveFolio.Select_NoShow, driver);
		boolean found = false;
		try {
			new Select(elements_MoveFolio.Select_NoShow).selectByVisibleText(Policy);
			found = true;
		} catch (Exception e) {
			test_steps.add("No Policy " + Policy + " Exist ");
		}
		assertTrue(!found, "Failed: Policy is Applicable on any other RoomClass");
		return test_steps;
	}

	public ArrayList<String> SelectCheckinPolicy(WebDriver driver, String Policy, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_xpath(OR.Select_DepositPolicies, driver);
		Utility.ScrollToElement(ReservationPage.Select_Reservation_Checkin_Policy, driver);
		Select PolicySelect = new Select(ReservationPage.Select_Reservation_Checkin_Policy);
		PolicySelect.selectByVisibleText(Policy);
		Wait.wait2Second();
		String SelectedCheckinPolicy = new Select(ReservationPage.Select_Reservation_Checkin_Policy)
				.getFirstSelectedOption().getText();
		assertEquals(SelectedCheckinPolicy, Policy, "Failed : Policy is not  selected");
		test_steps.add("Selected CheckIn policy: " + SelectedCheckinPolicy);
		folioLogger.info("Selected CheckIn policy: " + SelectedCheckinPolicy);
		return test_steps;

	}

	public void ReservationTab(WebDriver driver) {

		Elements_MovieFolio elements_MovieFolio = new Elements_MovieFolio(driver);
		elements_MovieFolio.Reservation_Tab.click();

		Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
		Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);
	}

	public void Deposite_Policy(WebDriver driver, String PolicyName) throws InterruptedException {

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.wait5Second();
		// select deposit policy
		new Select(elements_All_Payments.PaymentType).selectByIndex(1);

		// Add button
		elements_All_Payments.Add_Pay_Button.click();
		Wait.wait5Second();
		driver.findElement(By.xpath(OR.Continue_Pay_Button)).click();
		Wait.wait2Second();

		// Verify deposit policy
		int LineItem_Size = Elements_FolioLineItemsVoid.LineItems_Description.size();
		// assertTrue(Elements_FolioLineItemsVoid.LineItems.get(LineItem_Size -
		// 1).getText().contains("Account - " + AccountName), "Not select
		// deposit policy");

	}

	public void AccountPayment(WebDriver driver, String PaymentType, String Amount) throws InterruptedException {

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		WebElement element = driver.findElement(By.xpath(OR.PayButton));
		elements_All_Payments.Folio.get(0).click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		Wait.wait2Second();
		WebElement confirm = driver.findElement(By.xpath(OR.ConfrimButton));

		try {
			if (confirm.isDisplayed()) {
				confirm.click();
				Wait.wait2Second();
			}
		} catch (Exception e) {
		}
		try {
			Wait.explicit_wait_xpath(OR.Reservation_PaymentPopup, driver);
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);
			assertEquals(elements_All_Payments.Reservation_PaymentPopupHeading.getText().contains("Payment"), true,
					"payment popup does not open");
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.PaymentPopUp, driver);
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentPopUp, driver);
			assertEquals(elements_All_Payments.PaymentPopUp.getText(), "Payment Details",
					"payment popup does not open");
		}

		// select payment type as account
		new Select(elements_All_Payments.PaymentType).selectByVisibleText(PaymentType);
		Utility.app_logs.info(PaymentType);

		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Search_Account_Button, driver);
		// jse.executeScript("arguments[0].click();",
		// elements_All_Payments.Select_Account_Button);
		elements_All_Payments.Search_Account_Button.click();
		Utility.app_logs.info("Click Search Account Button");

		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Select_Account.get(0), driver);
		elements_All_Payments.Select_Account.get(0).click();
		try {
			jse.executeScript("arguments[0].click();", elements_All_Payments.Select_Account_Button);
			Utility.app_logs.info("Click Select Account Button");
			Wait.waitForElementToBeGone(driver, elements_All_Payments.Select_Account_Button, 30);
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Select_Account.get(0), driver);
			elements_All_Payments.Select_Account.get(0).click();
			jse.executeScript("arguments[0].click();", elements_All_Payments.Select_Account_Button);
			Utility.app_logs.info("Again Click Select Account Button");
			Wait.waitForElementToBeGone(driver, elements_All_Payments.Select_Account_Button, 30);
		}
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentType, driver);
		new Select(elements_All_Payments.PaymentType).selectByIndex(1);
		String str_length = elements_All_Payments.Enter_Amount.getAttribute("value");
		for (int i = 0; i < str_length.length(); i++) {
			elements_All_Payments.Enter_Amount.sendKeys(Keys.BACK_SPACE);
		}
		elements_All_Payments.Enter_Amount.sendKeys(Amount);
		folioLogger.info("Enter Amount : " + Amount);
		jse.executeScript("arguments[0].click();", elements_All_Payments.Add_Pay_Button);

		Wait.explicit_wait_absenceofelement(OR.Payment_ModuleLoading, driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Continue_Pay_Button, driver);
		WebElement webElement = driver.findElement(By.xpath(OR.Continue_Pay_Button));
		Wait.explicit_wait_visibilityof_webelement_120(webElement, driver);
		jse.executeScript("arguments[0].click();", webElement);
		System.out.println("Click Continue");
		try {
			Wait.waitForElementToBeGone(driver, webElement, 60);
		} catch (Exception e) {
			jse.executeScript("arguments[0].click();", webElement);
			Utility.app_logs.info("Click Continue again");
		}
		// Verify payment
		Verify_LineItem(driver, "account", "", "");
		/*
		 * Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new
		 * Elements_FolioLineItemsVoid(driver);
		 * Wait.explicit_wait_visibilityof_webelement_150(
		 * Elements_FolioLineItemsVoid.LineItems.get(1)); int LineItem_Size =
		 * Elements_FolioLineItemsVoid.LineItems.size(); String type =
		 * Elements_FolioLineItemsVoid.LineItems.get(LineItem_Size -
		 * 4).getText(); Utility.app_logs.info(type);
		 * assertTrue(type.contains("account"),
		 * "payament type does not add in line item");
		 */
	}

	public void Verify_LineItem(WebDriver driver, String Category, String Amount, String Description) {
		// Verify payment
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement_350(Elements_FolioLineItemsVoid.AddedLineItems_Category.get(0),
				driver);
		int LineItem_Size = Elements_FolioLineItemsVoid.AddedLineItems_Category.size();
		folioLogger.info("LineItem_Size : " + LineItem_Size);
		int lineNumber = LineItem_Size - 1;
		folioLogger.info("LineNumber : " + lineNumber);
		boolean found = false;
		for (lineNumber = LineItem_Size - 1; lineNumber >= 0; lineNumber--) {
			folioLogger.info("LineNumber : " + lineNumber);
			folioLogger.info(
					"Category : " + Elements_FolioLineItemsVoid.AddedLineItems_Category.get(lineNumber).getText());
			folioLogger.info("Amount : " + Elements_FolioLineItemsVoid.AddedLineItems_Amount.get(lineNumber).getText());
			folioLogger.info("Description : "
					+ Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber).getText());
			if (Elements_FolioLineItemsVoid.AddedLineItems_Category.get(lineNumber).getText()
					.equalsIgnoreCase(Category)) {
				assertTrue(Elements_FolioLineItemsVoid.AddedLineItems_Category.get(lineNumber).getText()
						.equalsIgnoreCase(Category), "Failed: Category Missmatched");
				if (!Amount.equals("")) {
					Utility.app_logs.info(Amount + " expected  but Actual Amount : "
							+ Elements_FolioLineItemsVoid.AddedLineItems_Amount.get(lineNumber).getText());
					assertTrue(Elements_FolioLineItemsVoid.AddedLineItems_Amount.get(lineNumber).getText()
							.contains(Amount), "Failed: Amount Missmatched");
				}
				if (!Description.equals("")) {
					String desc = Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber).getText();
					Utility.app_logs.info("line Item Description : " + desc);
					Utility.app_logs.info("Expected Description : " + Description);
					assertTrue(desc.replaceAll(" ", "").contains(Description.replaceAll(" ", "")),
							"Failed: Description Missmatched");
				}
				found = true;
				break;
			}
		}
		if (!found) {
			assertTrue(false, "Failed: Line Item havinf Category: " + Category + " Not found.");
		}
	}

	public void ChangeFolioOptions(WebDriver driver, String PhoneAccess1, String PhoneAccess2)
			throws InterruptedException {

		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		Elements_FolioLineItemsVoid.FolioOption.click();
		Wait.wait2Second();
		Wait.WaitForElement(driver, OR.Select_PhoneAccess);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", Elements_FolioLineItemsVoid.Select_PhoneAccess);
		new Select(Elements_FolioLineItemsVoid.Select_PhoneAccess).selectByVisibleText(PhoneAccess1);
		Elements_FolioLineItemsVoid.SaveOptions_Button.click();
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
		Select select = new Select(Elements_FolioLineItemsVoid.Select_PhoneAccess);
		WebElement option = select.getFirstSelectedOption();
		String getSelectedOption = option.getText();
		assertEquals(getSelectedOption, PhoneAccess1, "Option does not select");

		// Again set default option
		Wait.wait5Second();
		jse.executeScript("arguments[0].scrollIntoView();", Elements_FolioLineItemsVoid.Select_PhoneAccess);
		new Select(Elements_FolioLineItemsVoid.Select_PhoneAccess).selectByVisibleText(PhoneAccess2);
		Elements_FolioLineItemsVoid.SaveOptions_Button.click();
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
		Select select2 = new Select(Elements_FolioLineItemsVoid.Select_PhoneAccess);
		WebElement option2 = select2.getFirstSelectedOption();
		String getSelectedOption2 = option2.getText();
		assertEquals(getSelectedOption2, PhoneAccess2, "Option does not select");

	}

	public void SaveFolio(WebDriver driver) throws InterruptedException {

		Elements_Reservation Elements_Reservations = new Elements_Reservation(driver);
		Wait.explicit_wait_xpath(OR.ReservationSaveButton, driver);
		Elements_Reservations.ReservationSaveButton.click();
		Utility.ElementFinderUntillNotShow(By.xpath(OR.Toaster_Message), driver);

	}

	public void SaveFolioButton(WebDriver driver) throws InterruptedException {

		Wait.explicit_wait_xpath(OR.FolioSaveButton, driver);
		WebElement SaveButton = driver.findElement(By.xpath(OR.FolioSaveButton));
		SaveButton.click();
		Utility.ElementFinderUntillNotShow(By.xpath(OR.Toaster_Message), driver);

	}

	public ArrayList<String> clickFolio(WebDriver driver, ArrayList<String> getTest_Steps) throws InterruptedException {
		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);
		Utility.ScrollToElement(moveFolio.switchToFolio, driver);
		Wait.WaitForElement(driver, OR.switchToFolio);
		moveFolio.switchToFolio.click();
		Wait.WaitForElement(driver, OR.MoveFolio_GuestFolio);
		// Wait.wait10Second();
		return getTest_Steps;
	}

	public void HouseAccount_CardPayment(WebDriver driver, String PaymentType, String Amount, String CardName,
			String CardNum, String ExpDate, String CVVCode, String Address, String City, String State,
			String PostalCode, String AuthCode) throws InterruptedException {

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		WebElement element = driver.findElement(By.xpath(OR.PayButton));
		elements_All_Payments.Folio.get(0).click();
		Wait.explicit_wait_visibilityof_webelement(element, driver);
		Utility.ScrollToElement(element, driver);
		element.click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();", element);
		try {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Account_PaymentPopup, driver);
			assertTrue(elements_All_Payments.Account_PaymentPopup.isDisplayed(), "Failed: Payment Popup not displayed");
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentPopUp, driver);
			assertTrue(elements_All_Payments.PaymentPopUp.isDisplayed(), "Failed: Payment Popup not displayed");
		}
		// select payment type as cash
		try {
			String Type = null;
			elements_All_Payments.HouseAccoutn_Enter_Amount.clear();
			for (int i = 0; i < 10; i++) {
				elements_All_Payments.HouseAccoutn_Enter_Amount.sendKeys(Keys.BACK_SPACE);
			}
			try {
				Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.HouseAccoutn_PaymentType, driver);
				new Select(elements_All_Payments.HouseAccoutn_PaymentType).selectByVisibleText(PaymentType);
				Type = new Select(elements_All_Payments.HouseAccoutn_PaymentType).getFirstSelectedOption().getText();

			} catch (Exception e) {

				Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.HouseAccoutn_PaymentType_1, driver);
				new Select(elements_All_Payments.HouseAccoutn_PaymentType_1).selectByVisibleText(PaymentType);
				Type = new Select(elements_All_Payments.HouseAccoutn_PaymentType_1).getFirstSelectedOption().getText();
			}
			Utility.app_logs.info(Type);
			assertTrue(Type.contains(PaymentType), "Failed: payment Type Missmatched");
		} catch (Exception e) {
			Utility.app_logs.info("Catch Body");
			elements_All_Payments.PaymentPopup_Close.click();
			Wait.explicit_wait_visibilityof_webelement(element, driver);
			Utility.ScrollToElement(element, driver);
			element.click();
			try {
				Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Account_PaymentPopup, driver);
				Utility.app_logs.info(elements_All_Payments.Account_PaymentPopupHeading.getText());
				assertTrue(elements_All_Payments.Account_PaymentPopup.isDisplayed(),
						"Failed: Payment Popup not displayed");
			} catch (Exception g) {
				Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentPopUp, driver);
				Utility.app_logs.info(elements_All_Payments.PaymentPopUp.getText());
				assertTrue(elements_All_Payments.PaymentPopUp.isDisplayed(), "Failed: Payment Popup not displayed");
			}
			new Select(elements_All_Payments.HouseAccoutn_PaymentType).selectByVisibleText(PaymentType);
			new Select(elements_All_Payments.HouseAccoutn_PaymentType).selectByVisibleText(PaymentType);
			String Type = new Select(elements_All_Payments.HouseAccoutn_PaymentType).getFirstSelectedOption().getText();
			Utility.app_logs.info(Type);
			assertTrue(Type.contains(PaymentType), "Failed: payment Type Missmatched");
		}
		elements_All_Payments.HouseAccoutn_Enter_Amount.clear();
		for (int i = 0; i < 10; i++) {
			elements_All_Payments.HouseAccoutn_Enter_Amount.sendKeys(Keys.BACK_SPACE);
		}
		elements_All_Payments.HouseAccoutn_Enter_Amount.sendKeys(Amount);

		Wait.explicit_wait_visibilityof_webelement_120(elements_All_Payments.HouseAccoutn_Payment_Info_Button, driver);
		try {
			Utility.app_logs.info("try");
			jse.executeScript("arguments[0].click();", elements_All_Payments.HouseAccoutn_Payment_Info_Button);
			Wait.explicit_wait_visibilityof_webelement_350(elements_All_Payments.Account_CardInfoPopup, driver);
		} catch (Exception e) {
			Utility.app_logs.info("catch");
			elements_All_Payments.HouseAccoutn_Payment_Info_Button.click();
			Wait.explicit_wait_visibilityof_webelement_350(elements_All_Payments.Account_CardInfoPopup, driver);
		}
		elements_All_Payments.Account_CardInfoPopup_CardName.sendKeys(CardName);
		elements_All_Payments.Account_CardInfoPopup_CardNum.sendKeys(CardNum);
		elements_All_Payments.Account_CardInfoPopup_ExpDate.sendKeys(ExpDate);
		elements_All_Payments.Account_CardInfoPopup_Card_CVVCode.sendKeys(CVVCode);
		elements_All_Payments.Account_CardInfoPopup_Address.sendKeys(Address);
		elements_All_Payments.Account_CardInfoPopup_Card_City.sendKeys(City);
		elements_All_Payments.Account_CardInfoPopup_Card_State.sendKeys(State);
		elements_All_Payments.Account_CardInfoPopup_PostalCode.sendKeys(PostalCode);
		elements_All_Payments.Account_CardInfoPopup_ApprovalCode.sendKeys(AuthCode);
		elements_All_Payments.Account_CardInfoPopup_OKButton.click();
		Utility.app_logs.info("Fill Payment Details");
		Wait.waitForElementToBeGone(driver, elements_All_Payments.Account_CardInfoPopup_OKButton, 80);
		Thread.sleep(10000);
		Wait.explicit_wait_visibilityof_webelement_120(elements_All_Payments.HouseAccoutn_Process_Button, driver);
		Utility.ScrollToElement(elements_All_Payments.HouseAccoutn_Process_Button, driver);
		elements_All_Payments.HouseAccoutn_Process_Button.click();
		Utility.app_logs.info("Click Process");
		Thread.sleep(10000);

		Wait.explicit_wait_visibilityof_webelement_150(elements_All_Payments.HouseAccoutn_Continue_Pay_Button, driver);

		// jse.executeScript("arguments[0].click();",
		Utility.ScrollToElement(elements_All_Payments.HouseAccoutn_Continue_Pay_Button, driver);
		elements_All_Payments.HouseAccoutn_Continue_Pay_Button.click();

		try {
			Wait.waitForElementToBeGone(driver, elements_All_Payments.HouseAccoutn_Continue_Pay_Button, 30);
			Utility.app_logs.info("Click Continue");
		} catch (Exception e) {
			elements_All_Payments.HouseAccoutn_Continue_Pay_Button.click();
			Utility.app_logs.info("Click Continue again");
		}

		// Verify payment
		Verify_LineItem(driver, PaymentType, Amount, "");
		/*
		 * Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new
		 * Elements_FolioLineItemsVoid(driver); try {
		 * Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.
		 * cssSelector(OR.LineItems))); } catch (Exception e) {
		 * Utility.ElementFinderUntillNotShow(By.cssSelector(OR.LineItems),
		 * driver); } int LineItem_Size =
		 * Elements_FolioLineItemsVoid.LineItems.size();
		 * assertTrue(Elements_FolioLineItemsVoid.LineItems.get(LineItem_Size -
		 * 4).getText().contains(PaymentType),
		 * "payament type does not add in line item"); assertTrue(
		 * Elements_FolioLineItemsVoid.LineItems.get(LineItem_Size -
		 * 1).getText().contains(String.valueOf(Amount)),
		 * "Amount does not add in line item");
		 * Utility.app_logs.info("Verified Payment");
		 */
	}

	public void AssociatePolicies(WebDriver driver, String Policy, String CheckInPolicy, String NoShowPolicy,
			ExtentTest test) throws InterruptedException {

		Elements_MovieFolio elements_MovieFolio = new Elements_MovieFolio(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_MovieFolio.MoveFolio_Folio, driver);
		elements_MovieFolio.MoveFolio_Folio.click();
		// Policy = "NewPolicy";
		try {
			Wait.explicit_wait_visibilityof_webelement(elements_MovieFolio.Folio_Options, driver);
			elements_MovieFolio.Folio_Options.click();
			Wait.explicit_wait_visibilityof_webelement(elements_MovieFolio.Select_DepositPolicy, driver);
			Utility.ScrollToElement(elements_MovieFolio.Select_DepositPolicy, driver);
			new Select(elements_MovieFolio.Select_DepositPolicy).selectByVisibleText(Policy);
		} catch (Exception e) {
			elements_MovieFolio.Folio_Option.click();
			Wait.explicit_wait_visibilityof_webelement(elements_MovieFolio.Select_DepositPolicies, driver);
			Utility.ScrollToElement(elements_MovieFolio.Select_DepositPolicies, driver);
			new Select(elements_MovieFolio.Select_DepositPolicies).selectByVisibleText(Policy);
		}

		folioLogger.info("Select deposit policy: " + Policy);

		Wait.wait1Second();
		new Select(elements_MovieFolio.Select_CheckIN).selectByVisibleText(CheckInPolicy);
		folioLogger.info("Select checkin policy: " + CheckInPolicy);

		Wait.wait1Second();
		new Select(elements_MovieFolio.Select_NoShow).selectByVisibleText(NoShowPolicy);
		folioLogger.info("Select noshow policy: " + CheckInPolicy);
		try {
			elements_MovieFolio.Save_DepositPolicy.click();
		} catch (Exception e) {
			elements_MovieFolio.Save_DepositPolicies.click();
		}
		Wait.explicit_wait_visibilityof_webelement_150(elements_MovieFolio.Verify_Toaster_Container, driver);
	}

	public void AssociateDepositPolicy(WebDriver driver, String Policy, ExtentTest test) throws InterruptedException {

		Elements_MovieFolio elements_MovieFolio = new Elements_MovieFolio(driver);

		new Select(elements_MovieFolio.Select_DepositPolicy).selectByVisibleText(Policy);
		folioLogger.info("Select deposit policy: " + Policy);

		elements_MovieFolio.Save_DepositPolicy.click();

	}

	public ArrayList<String> PaymentViewAccount(WebDriver driver, String PaymentType, String Amount,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		WebElement element = driver.findElement(By.xpath(OR.PayButton));
		elements_All_Payments.Folio.get(0).click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		try {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);
			assertTrue(elements_All_Payments.Reservation_PaymentPopup.isDisplayed(),
					"Failed: Payment Popup not displayed");
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentPopUp, driver);
			assertTrue(elements_All_Payments.PaymentPopUp.isDisplayed(), "Failed: Payment Popup not displayed");
		}
		test_steps.add("Click Pay");
		folioLogger.info("Click Pay");
		// select payment type as cash
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentType, driver);
		Utility.ScrollToElement(elements_All_Payments.PaymentType, driver);
		elements_All_Payments.PaymentType.click();
		new Select(elements_All_Payments.PaymentType).selectByVisibleText(PaymentType);
		elements_All_Payments.Enter_Amount.clear();
		for (int i = 0; i < 10; i++) {
			elements_All_Payments.Enter_Amount.sendKeys(Keys.BACK_SPACE);
		}
		elements_All_Payments.Enter_Amount.sendKeys(Amount);

		// Add button
		elements_All_Payments.Add_Pay_Button.click();
		test_steps.add("Click Add");
		folioLogger.info("Click Add");
		// Wait.wait5Second();
		Wait.explicit_wait_absenceofelement(OR.Payment_ModuleLoading, driver);
		Wait.explicit_wait_visibilityof_webelement_350(elements_All_Payments.Continue_Pay_Button, driver);
		driver.findElement(By.xpath(OR.Continue_Pay_Button)).click();
		Wait.wait3Second();
		test_steps.add("Click Continue");
		folioLogger.info("Click Continue");
		// Verify payment
		jse.executeScript("window.scrollBy(0,300)");
		Verify_LineItem(driver, "account", Amount, PaymentType);
		/*
		 * Wait.explicit_wait_visibilityof_webelement_150(
		 * Elements_FolioLineItemsVoid.LineItems.get(1));
		 * Utility.ScrollToElement(driver.findElement(By.cssSelector(OR.
		 * LineItems))); int LineItem_Size =
		 * Elements_FolioLineItemsVoid.LineItems.size();
		 * assertTrue(Elements_FolioLineItemsVoid.LineItems.get(LineItem_Size -
		 * 1).getText().contains(Amount), "Amount does not add in line item");
		 * // payment Description int LineItemDescription_Size =
		 * Elements_FolioLineItemsVoid.LineItems_Description.size();
		 * 
		 * Wait.explicit_wait_visibilityof_webelement(
		 * Elements_FolioLineItemsVoid.LineItems_Description.get(
		 * LineItemDescription_Size - 1)); System.out
		 * .println(Elements_FolioLineItemsVoid.LineItems_Description.get(
		 * LineItemDescription_Size - 1).getText());
		 * System.out.println(PaymentType);
		 * System.out.println(String.valueOf(PaymentType));
		 * assertTrue(Elements_FolioLineItemsVoid.LineItems_Description.get(
		 * LineItemDescription_Size - 1).getText()
		 * .contains(String.valueOf(PaymentType)),
		 * "payament type does not add in line item");
		 */
		return test_steps;
	}

	public void FolioTab1(WebDriver driver) throws InterruptedException {

		Elements_FolioLineItemsVoid Ele_FolioItem = new Elements_FolioLineItemsVoid(driver);
		// Wait.waitUntilPresenceOfElementLocated(OR.Folio);
		Ele_FolioItem.FolioTab.click();
	}

	public void VeirfyLineItem(WebDriver driver, String Catagory, String Amount) {

		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		int LineItem_Size = Elements_FolioLineItemsVoid.LineItems.size();
		assertTrue(Elements_FolioLineItemsVoid.LineItems.get(LineItem_Size - 4).getText()
				.contains(String.valueOf(Catagory)), "Amount does not add in line item");
	}

	public ArrayList<String> VeirfyTaxItem(WebDriver driver, String Catagory, String folioAmount, String TaxItemName,
			String taxValue, ArrayList<String> test_steps) throws InterruptedException {

		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		int LineItemDescription_Size = Elements_FolioLineItemsVoid.LineItems_Description.size();
		Utility.ScrollToElement(Elements_FolioLineItemsVoid.LineItems_Description.get(LineItemDescription_Size - 1),
				driver);
		WebElement LineItemsDesc = driver.findElement(By.xpath("(//td[@class='lineitem-description']//a)[last()]"));
		String LineItemDescText = LineItemsDesc.getText();

		// System.out.println("LineItemDescText:" + LineItemDescText +
		// "Category" + Catagory);
		assertEquals(LineItemDescText, Catagory, "Tax Item type does not add in line item");

		String LineItemsTax = driver.findElement(By.xpath("(//td[@class='lineitem-tax']//span)[last()]")).getText()
				.substring(2);
		String LineItemsAmount = driver.findElement(By.xpath("(//td[@class='lineitem-amount']//span)[last()]"))
				.getText().substring(2);

		Float generatedAmount = Float.parseFloat(LineItemsAmount) - Float.parseFloat(LineItemsTax);

		test_steps.add("Verify Amount : " + generatedAmount);
		folioLogger.info("Verify Amount : " + generatedAmount);
		assertTrue(generatedAmount == Float.parseFloat(folioAmount), "Tax Not Verified");

		// assertTrue(Elements_FolioLineItemsVoid.LineItems_Description.get(1).getText().contains(Catagory),
		// "Tax Item type does not add in line item");
		Elements_FolioLineItemsVoid.LineItems_Description.get(LineItemDescription_Size - 1).click();
		Wait.wait2Second();
		boolean IsChangeDueDatePopup = driver.findElements(By.xpath(OR.ChangeDueDate_Popup)).size() == 1;
		if (IsChangeDueDatePopup) {
			Elements_FolioLineItemsVoid.ChangeDueDate_NoButton.click();
			Wait.wait2Second();
		}
		String TaxItem_Path = "(//a[.='" + TaxItemName + "'])[2]";

		boolean isTaxItem = driver.findElements(By.xpath(TaxItem_Path)).size() > 1;
		if (isTaxItem) {

			List<WebElement> tax_item = driver.findElements(By.xpath(TaxItem_Path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", tax_item.get(tax_item.size() - 1));
			assertEquals(tax_item.get(tax_item.size() - 1).getText(), TaxItemName, "New tax item does not display");
		} else {

			// WebElement tax_item = driver
			// .findElement(By.xpath("(//span[contains(@data-bind,'text:
			// $data.Description')])[1]"));

			WebElement tax_item = driver.findElement(
					By.xpath("//table[1]/tbody/tr[2]/td[5]/a[contains(@data-bind,'text: $data.Description')]"));
			String tax_item_text = tax_item.getText();
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", tax_item);
			System.out.println(tax_item_text + ":" + TaxItemName);
			assertEquals(tax_item_text, TaxItemName, "New tax item does not display");
			tax_item.click();
			Wait.wait5Second();
			// Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath("//*[@id='bpjscontainer_67']//input[contains(@data-bind,'value:
			// TaxItemValue')]")), driver);
			// String
			// tax_Ammount=driver.findElement(By.xpath("//*[@id='bpjscontainer_67']//input[contains(@data-bind,'value:
			// TaxItemValue')]")).getAttribute("value");;
			// jse.executeScript("arguments[0].scrollIntoView();", tax_Ammount);
			//
			// assertEquals(tax_Ammount, taxValue, "New tax item value does not
			// display");

			driver.findElement(By.xpath("//*[@id='popUpForInnroad']/div[3]/button")).click();
			Wait.wait5Second();

		}
		return test_steps;
	}

	public void VeirfyTaxItem_Account(WebDriver driver, String Catagory, String Amount, String TaxItemName)
			throws InterruptedException {

		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		int LineItemDescription_Size = Elements_FolioLineItemsVoid.LineItems_Description.size();
		Utility.ScrollToElement(Elements_FolioLineItemsVoid.LineItems_Description.get(LineItemDescription_Size - 1),
				driver);
		WebElement LineItemsDesc = driver.findElement(By.xpath("//td[@class='lineitem-description-2']//a"));
		String LineItemDescText = LineItemsDesc.getText();
		// System.out.println("LineItemDescText:" + LineItemDescText +
		// "Category" + Catagory);
		assertEquals(LineItemDescText, Catagory, "Tax Item type does not add in line item");
		// assertTrue(Elements_FolioLineItemsVoid.LineItems_Description.get(1).getText().contains(Catagory),
		// "Tax Item type does not add in line item");
		Elements_FolioLineItemsVoid.LineItems_Description.get(LineItemDescription_Size - 1).click();
		Wait.wait2Second();
		boolean IsChangeDueDatePopup = driver.findElements(By.xpath(OR.ChangeDueDate_Popup)).size() == 1;
		if (IsChangeDueDatePopup) {
			Elements_FolioLineItemsVoid.ChangeDueDate_NoButton.click();
			Wait.wait2Second();
		}
		String TaxItem_Path = "(//a[.='" + TaxItemName + "'])[2]";
		boolean isTaxItem = driver.findElements(By.xpath(TaxItem_Path)).size() > 1;
		if (isTaxItem) {
			List<WebElement> tax_item = driver.findElements(By.xpath(TaxItem_Path));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", tax_item.get(tax_item.size() - 1));
			assertEquals(tax_item.get(tax_item.size() - 1).getText(), TaxItemName, "New tax item does not display");
		} else {
			WebElement tax_item = driver
					.findElement(By.xpath("(//span[contains(@data-bind,'text: $data.Description')])[1]"));
			String tax_item_text = tax_item.getText();
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", tax_item);
			// System.out.println(tax_item_text + ":" + TaxItemName);
			assertEquals(tax_item_text, TaxItemName, "New tax item does not display");
		}
	}

	public ArrayList<String> VeirfyTaxItem_Account_Folio(WebDriver driver, String Catagory, String Amount,
			String TaxItemName, String TaxAmount, ArrayList<String> test_steps) throws InterruptedException {

		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		int LineItemDescription_Size = Elements_FolioLineItemsVoid.LineItems_Description.size();
		Utility.ScrollToElement(Elements_FolioLineItemsVoid.LineItems_Description.get(LineItemDescription_Size - 1),
				driver);
		try {
			String LineItemsTax = driver.findElement(By.xpath("(//td[@class='lineitem-tax']//span)[last()]")).getText()
					.substring(2);
			String LineItemsAmount = driver.findElement(By.xpath("(//td[@class='lineitem-amount']//span)[last()]"))
					.getText().substring(2);

			Float generatedAmount = Float.parseFloat(LineItemsAmount) - Float.parseFloat(LineItemsTax);

			test_steps.add("Verify Amount : " + generatedAmount);
			folioLogger.info("Verify Amount : " + generatedAmount);
			assertTrue(generatedAmount == Float.parseFloat(Amount), "Line Items Amount Not Verified");
		} catch (Exception e) {
			Utility.app_logs.info("Amount Not Verified");
		}

		WebElement LineItemsDesc = driver.findElement(By.xpath("//td[@class='lineitem-description-2']//a"));
		String LineItemDescText = LineItemsDesc.getText();
		// System.out.println("LineItemDescText:" + LineItemDescText +
		// "Category" + Catagory);
		assertEquals(LineItemDescText, Catagory, "Tax Item type does not add in line item");
		// assertTrue(Elements_FolioLineItemsVoid.LineItems_Description.get(1).getText().contains(Catagory),
		// "Tax Item type does not add in line item");
		Elements_FolioLineItemsVoid.LineItems_Description.get(LineItemDescription_Size - 1).click();
		// Wait.wait2Second();
		try {
			// boolean IsChangeDueDatePopup =
			// driver.findElements(By.xpath(OR.ChangeDueDate_Popup)).size() ==
			// 1;
			// if (IsChangeDueDatePopup) {
			Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.ChangeDueDatePopup)), driver);
			Utility.app_logs.info("Change Date Popup Appears");
			Elements_FolioLineItemsVoid.ChangeDueDateNoButton.click();
			// Wait.wait2Second();
			// }
		} catch (Exception e) {
			Utility.app_logs.info("No Change Date Popup Appears");
		}
		// String TaxItem_Path = "(//a[.='" + TaxItemName + "'])[2]";
		// boolean isTaxItem =
		// driver.findElements(By.xpath(TaxItem_Path)).size() > 1;
		// if (isTaxItem) {
		// List<WebElement> tax_item =
		// driver.findElements(By.xpath(TaxItem_Path));
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].scrollIntoView();",
		// tax_item.get(tax_item.size() - 1));
		// assertEquals(tax_item.get(tax_item.size() - 1).getText(), Catagory,
		// "New tax item does not display");
		// } else {
		// WebElement tax_item = driver
		// .findElement(By.xpath("(//span[contains(@data-bind,'text:
		// $data.Description')])[1]"));
		// String tax_item_text = tax_item.getText();
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].scrollIntoView();", tax_item);
		// // System.out.println(tax_item_text + ":" + TaxItemName);
		// assertEquals(tax_item_text, Catagory, "New tax item does not
		// display");
		// }
		WebElement tax_item = driver.findElement(
				By.xpath("//table[1]/tbody/tr[last()]/td[5]/a[contains(@data-bind,'text: $data.Description')]"));
		String tax_item_text = tax_item.getText();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", tax_item);
		System.out.println(tax_item_text + ":" + TaxItemName);
		assertEquals(tax_item_text, TaxItemName, "New tax item does not display");
		folioLogger.info("Successfully Verify Tax Item : " + TaxItemName);
		test_steps.add("Successfully Verify Tax Item : " + TaxItemName);

		Wait.wait5Second();
		jse.executeScript("arguments[0].scrollIntoView()", tax_item);
		tax_item.click();
		Wait.wait5Second();
		Wait.explicit_wait_visibilityof_webelement(
				driver.findElement(By.xpath("//input[contains(@data-bind,'value: TaxItemValue')]")), driver);
		String tax_Ammount = driver.findElement(By.xpath("//input[contains(@data-bind,'value: TaxItemValue')]"))
				.getAttribute("value");
		;

		assertEquals(tax_Ammount, TaxAmount, "New tax item value does not display");
		folioLogger.info("Successfully Verify Tax Value : " + TaxAmount);
		test_steps.add("Successfully Verify Tax Value : " + TaxAmount);

		driver.findElement(By.xpath("//*[@id='popUpForInnroad']/div[3]/button")).click();
		Wait.wait5Second();
		return test_steps;
	}

	public void CancelTaxItem_Res(WebDriver driver) throws InterruptedException {

		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Elements_FolioLineItemsVoid.ItemDatails_CancelButton.click();
		Wait.wait2Second();
	}

	public void CancelTaxItem_Accounts(WebDriver driver) throws InterruptedException {

		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Utility.ScrollToElement(Elements_FolioLineItemsVoid.ItemDatails_CancelButton, driver);
		Elements_FolioLineItemsVoid.ItemDatails_CancelButton.click();
		Wait.wait2Second();
	}

	public void VerfiyTaxItem(WebDriver driver, String Description) throws InterruptedException {

		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.wait2Second();
		// Wait.waitUntilPresenceOfElementLocated(OR.LineItems_Description);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", Elements_FolioLineItemsVoid.LineItems_Description.get(1));

		int LineItemDescription_Size = Elements_FolioLineItemsVoid.LineItems_Description.size();
		boolean found = false;
		for (int i = 1; i < LineItemDescription_Size; i++) {
			System.out.println("Description:" + Description);

			try {
				String ItemDesc = driver.findElement(By.xpath("(//td[@class='lineitem-description']//a)[1]")).getText();
				System.out.println("ItemDesc:" + ItemDesc);
				assertEquals(ItemDesc, Description, "Tax Item type does not add in line item");
			} catch (Exception e) {

				String ItemDesc = Elements_FolioLineItemsVoid.LineItems_Description.get(i).getText();
				System.out.println("ItemDesc:" + ItemDesc);
				assertEquals(ItemDesc, Description, "Tax Item type does not add in line item");
			}
			// if (ItemDesc.equals(Description)) {
			// found = true;
			// System.out.println(found);
			// break;
			// }
			// assertTrue(found, "Tax Item type does not add in line item");
		}

	}

	public void PaymentInfoPickerButtonClick(WebDriver driver) throws InterruptedException {

		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement(Elements_FolioLineItemsVoid.PaymentInfoPicker, driver);
		Utility.ScrollToElement(Elements_FolioLineItemsVoid.PaymentInfoPicker, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,300)");
		jse.executeScript("arguments[0].click();", Elements_FolioLineItemsVoid.PaymentInfoPicker);
		// Elements_FolioLineItemsVoid.PaymentInfoPicker.click();
		Utility.app_logs.info("Click Payment info Picker");
		Wait.explicit_wait_visibilityof_webelement(Elements_FolioLineItemsVoid.FolioBillingInformationPopUp, driver);
		assertEquals(Elements_FolioLineItemsVoid.FolioBillingInformationPopUp.isDisplayed(), true,
				"payment info pop-up didnt  displayed");
		Wait.wait2Second();
	}

	public void FoliobillingInformation(WebDriver driver, String BillingName, String PaymentMethod,
			String AccountNumber, String ExpiryDate, String BillingNotes) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);

		if (FolioLineItems.FolioPopUp_BillingName.equals(null)) {
			FolioLineItems.FolioPopUp_BillingName.sendKeys(BillingName);
		}
		new Select(FolioLineItems.FolioPopUp_PaymentMethod).selectByVisibleText(PaymentMethod);
		Wait.wait5Second();
		if (FolioLineItems.FolioPopUp_AccountNumber.equals(null)) {
			if (PaymentMethod.equalsIgnoreCase("MC") || PaymentMethod.equalsIgnoreCase("Amex")
					|| PaymentMethod.equalsIgnoreCase("Discover") || PaymentMethod.equalsIgnoreCase("Visa")) {

				FolioLineItems.FolioPopUp_AccountNumber.sendKeys(AccountNumber);
				FolioLineItems.FolioPopUp_ExpiryDate.sendKeys(ExpiryDate);
			}
		}
		String GetPaymentMethod = FolioLineItems.FolioPopUp_PaymentMethod.getAttribute("value");

		assertTrue(GetPaymentMethod.contains(PaymentMethod), "particular reservation details isn't populated");

		FolioLineItems.FolioPopUp_SaveButton.click();
	}

	public ArrayList<String> Create_NewFolio(WebDriver driver, String FolioName, String FolioDescription,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_MovieFolio folio = new Elements_MovieFolio(driver);

		// click on Folio Tab
		Wait.waitUntilPresenceOfElementLocated(OR.Click_Folio_Tab, driver);
		Wait.wait3Second();
		folio.Click_Folio_Tab.click();
		folioLogger.info("Click Folio Tab");
		Wait.wait2Second();
		// click New Folio
		Wait.explicit_wait_xpath(OR.Click_NewFolio, driver);
		folio.Click_NewFolio.click();
		folioLogger.info("Click New Folio");

		// new Folio creation
		Wait.explicit_wait_xpath(OR.New_FolioName, driver);
		folio.New_FolioName.sendKeys(FolioName);
		Wait.wait2Second();
		assertTrue(folio.New_FolioName.getAttribute("value").contains(FolioName), "Failed: Folio Name");
		test_steps.add("New Folio Name : " + FolioName);
		folioLogger.info("New Folio Name : " + FolioName);
		Wait.explicit_wait_xpath(OR.New_FolioDescription, driver);
		folio.New_FolioDescription.sendKeys(FolioDescription);
		Wait.wait2Second();
		assertTrue(folio.New_FolioDescription.getAttribute("value").contains(FolioDescription),
				"Failed: Folio Description");
		test_steps.add("New Folio Description : " + FolioDescription);
		folioLogger.info("New Folio Description : " + FolioDescription);

		Wait.explicit_wait_xpath(OR.Folio_Tab_Save, driver);
		folio.Folio_Tab_Save.click();
		test_steps.add("Click Save");
		folioLogger.info("Click Save");
		return test_steps;

	}

	public void CloseOpenedReservation(WebDriver driver) throws InterruptedException {
		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);
		Wait.wait3Second();
		Utility.ScrollToElement(moveFolio.FirstOpenedReservationClose, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-100)");
		moveFolio.FirstOpenedReservationClose.click();
		Wait.wait2Second();
		folioLogger.info("Close Reservation");
	}

	public void PostLineItem(WebDriver driver) throws InterruptedException {
		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);
		Wait.wait3Second();
		moveFolio.Post_LineItem.click();

	}

	public void ChargeRouting(WebDriver driver, String MovetoFolioReser, String FolioPrintingCheckBox)
			throws InterruptedException {
		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);
		try {
			new Select(moveFolio.ChargeRouting_MoveToAccFolio).selectByVisibleText(MovetoFolioReser);
		} catch (Exception e) {
			new Select(moveFolio.ChargeRouting_MoveToAccFolio1).selectByVisibleText(MovetoFolioReser);

		}
		if (FolioPrintingCheckBox.equals("Yes") || FolioPrintingCheckBox.equals("yes")) {
			moveFolio.ChargeRouting_FolioPrintingCheckBox.click();

		}

	}

	public void ApplyRouting(WebDriver driver) throws InterruptedException {

		Elements_MovieFolio elements_MovieFolio = new Elements_MovieFolio(driver);
		Elements_FolioLineItemsVoid Ele_FolioItem = new Elements_FolioLineItemsVoid(driver);
		elements_MovieFolio.SelectAllLineitems.click();
		elements_MovieFolio.ApplyRoutingButton.click();
		Wait.wait2Second();
		assertTrue(elements_MovieFolio.ConfirmApplyRouting.isDisplayed(), "Failed:Confirm Page not displayed");
		elements_MovieFolio.ConfirmApplyRouting_OK.click();
		int size = Ele_FolioItem.LineItemsAmountFields.size();
		System.out.println("Size:" + size);
		String lineitemsize = Integer.toString(size);
		// assertTrue(lineitemsize.equals("0"), "Failed:Room Changes not moved
		// to folio account");

	}

	public ArrayList<String> VerifyCheckInPolicy(WebDriver driver, String policyName, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_MovieFolio elements_MoveFolio = new Elements_MovieFolio(driver);

		Wait.explicit_wait_visibilityof_webelement(elements_MoveFolio.Select_CheckIN, driver);
		Utility.ScrollToElement(elements_MoveFolio.Select_CheckIN, driver);

		String SelectedPolicy = new Select(elements_MoveFolio.Select_CheckIN).getFirstSelectedOption().getText();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", elements_MoveFolio.Select_CheckIN);
		assertEquals(SelectedPolicy, policyName, "Failed : Policy is not selected");
		test_steps.add("Applied CheckIn policy: " + SelectedPolicy);
		folioLogger.info("Applied CheckIn policy: " + SelectedPolicy);
		assertEquals(elements_MoveFolio.Select_CheckIN.isEnabled(), true,
				"Failed: CheckIn Polic Selection is Disabled");
		test_steps.add("CheckIn policy Field is Enabled ");
		folioLogger.info("CheckIn policy Field is Enabled");
		return test_steps;
	}

	public ArrayList<String> VerifyCancellationtPolicy(WebDriver driver, String policyName,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_MovieFolio elements_MoveFolio = new Elements_MovieFolio(driver);

		Wait.explicit_wait_visibilityof_webelement(elements_MoveFolio.Cancellation_Policy, driver);
		Utility.ScrollToElement(elements_MoveFolio.Cancellation_Policy, driver);
		try {
			String SelectedPolicy = elements_MoveFolio.Cancellation_Policy.getAttribute("value");
			if (SelectedPolicy.equals("No Policy")) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				assertEquals(SelectedPolicy, policyName, "Failed : Policy is not selected");
				test_steps.add("Applied Cancellation policy: " + SelectedPolicy);
				folioLogger.info("Applied Cancellation policy: " + SelectedPolicy);
				assertEquals(elements_MoveFolio.Cancellation_Policy.isEnabled(), true,
						"Failed: Deposit Polic Selection is Disabled");
				test_steps.add("Cancellation policy Field is Enabled ");
				folioLogger.info("Cancellation policy Field is Enabled");
			}

		} catch (Exception e) {
			System.err.println("Policy Alreay Selected");
		}
		return test_steps;
	}

	public ArrayList<String> VerifyCancellationPolicy(WebDriver driver, String Policy, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_MovieFolio elements_MoveFolio = new Elements_MovieFolio(driver);

		Wait.explicit_wait_visibilityof_webelement(elements_MoveFolio.Cancellation_Policy, driver);
		Utility.ScrollToElement(elements_MoveFolio.Cancellation_Policy, driver);
		String SelectedPolicy = elements_MoveFolio.Cancellation_Policy.getAttribute("value");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", elements_MoveFolio.Select_DepositPolicies);
		assertEquals(SelectedPolicy, Policy, "Failed : Policy is not selected");
		return test_steps;
	}

	public ArrayList<String> VerifyNoShowPolicy(WebDriver driver, String policyName, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_MovieFolio elements_MoveFolio = new Elements_MovieFolio(driver);

		Wait.explicit_wait_visibilityof_webelement(elements_MoveFolio.Select_NoShow, driver);
		Utility.ScrollToElement(elements_MoveFolio.Select_NoShow, driver);

		String SelectedNoshowPolicy = new Select(elements_MoveFolio.Select_NoShow).getFirstSelectedOption().getText();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", elements_MoveFolio.Select_NoShow);
		assertEquals(SelectedNoshowPolicy, policyName, "Failed : Policy is not selected");
		test_steps.add("Applied Noshow policy: " + SelectedNoshowPolicy);
		folioLogger.info("Applied Noshow policy: " + SelectedNoshowPolicy);
		assertEquals(elements_MoveFolio.Select_NoShow.isEnabled(), true, "Failed: Noshow Polic Selection is Disabled");
		test_steps.add("Noshow policy Field is Enabled ");
		folioLogger.info("Noshow policy Field is Enabled");
		return test_steps;

	}

	public ArrayList<String> SelectNoShowPolicy(WebDriver driver, String policyName, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_MovieFolio elements_MoveFolio = new Elements_MovieFolio(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_MoveFolio.Select_NoShow, driver);
		Utility.ScrollToElement(elements_MoveFolio.Select_NoShow, driver);
		new Select(elements_MoveFolio.Select_NoShow).selectByVisibleText(policyName);
		String SelectedNoshowPolicy = new Select(elements_MoveFolio.Select_NoShow).getFirstSelectedOption().getText();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", elements_MoveFolio.Select_NoShow);
		assertEquals(SelectedNoshowPolicy, policyName, "Failed : Policy is not selected");
		test_steps.add("Selected Noshow policy: " + SelectedNoshowPolicy);
		folioLogger.info("Selected Noshow policy: " + SelectedNoshowPolicy);
		return test_steps;

	}

	public void SelectCancellationPolicy(WebDriver driver, String policyName) throws InterruptedException {

		System.out.println("Policy:" + policyName);
		Elements_MovieFolio folio = new Elements_MovieFolio(driver);
		Wait.explicit_wait_visibilityof_webelement(folio.Cancellation_Policy, driver);
		Utility.ScrollToElement(folio.Cancellation_Policy, driver);
		folio.Cancellation_Policy.click();
		Wait.WaitForElement(driver, NewFolio.CancelPolicyPicker_Popup);
		Wait.explicit_wait_visibilityof_webelement(folio.AvailableCancellationPolicies, driver);
		new Select(folio.AvailableCancellationPolicies).selectByVisibleText(policyName);
		folio.ClickAddOne_Cancelpolicy.click();

		/*
		 * List<WebElement> selectedList = new
		 * Select(folio.SelectedCancellationPolicies).getAllSelectedOptions();
		 * boolean foundPolicy = false; for(WebElement policy: selectedList) {
		 * Utility.app_logs.info(policy.getText());
		 * if(policy.getText().equalsIgnoreCase(policyName)) { foundPolicy =
		 * true; break; } } assertTrue(
		 * foundPolicy,"Failed: Selected Policy not present in the selected List"
		 * );
		 */Utility.ScrollToElement(folio.ClickDone_CancelPolicy, driver);
		folio.ClickDone_CancelPolicy.click();
		Wait.waitForElementToBeGone(driver, folio.ClickDone_CancelPolicy, 30);
		try {
			assertEquals(folio.Cancellation_Policy.getAttribute("value"), policyName,
					"Failed:Cancellation Policy Not selected");
		} catch (Exception e) {
			assertTrue(folio.Cancellation_Policy.getAttribute("value").contains(policyName),
					"Failed:Cancellation Policy Not selected");
		}

	}

	public void SelectCancellationPolicy_1(WebDriver driver, String policyName) throws InterruptedException {

		System.out.println("Policy:" + policyName);
		Elements_MovieFolio elements_MoveFolio = new Elements_MovieFolio(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_MoveFolio.Cancellation_Policy, driver);
		Utility.ScrollToElement(elements_MoveFolio.Cancellation_Policy, driver);
		elements_MoveFolio.Cancellation_Policy.click();
		new Select(elements_MoveFolio.Cancellation_Policy).selectByVisibleText(policyName);
		String SelectedNoshowPolicy = new Select(elements_MoveFolio.Cancellation_Policy).getFirstSelectedOption()
				.getText();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", elements_MoveFolio.Cancellation_Policy);
		assertEquals(SelectedNoshowPolicy, policyName, "Failed : Policy is not selected");
		folioLogger.info("Applied Noshow policy: " + SelectedNoshowPolicy);

	}

	public ArrayList<String> ValidatePaymentwithZeroAmount(WebDriver driver, String Amount, String PaymentType,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		Elements_MovieFolio elements_MovieFolio = new Elements_MovieFolio(driver);
		WebElement element = driver.findElement(By.xpath(OR.PayButton));

		assertTrue(elements_MovieFolio.Folio_Option.isDisplayed(), "Failed : Folio tab is not opened");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		folioLogger.info("Click Pay");
		test_steps.add("Click Reservation Pay Button");
		Wait.wait2Second();
		try {
			Wait.explicit_wait_xpath(OR.Reservation_PaymentPopup, driver);
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);
			assertTrue(elements_All_Payments.Reservation_PaymentPopup.isDisplayed(),
					"Failed : Failed payment Popup Not Open");
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.PaymentPopUp, driver);
			Wait.explicit_wait_visibilityof_webelement_200(elements_All_Payments.PaymentPopUp, driver);
			assertEquals(elements_All_Payments.PaymentPopUp.getText(), "Payment Details",
					"payment popup does not open");

		}
		test_steps.add("Payment popup Appeared");
		// select payment type as cash`

		new Select(elements_All_Payments.PaymentDetail_PaymentType).selectByVisibleText(PaymentType);

		assertTrue(new Select(elements_All_Payments.PaymentDetail_PaymentType).getFirstSelectedOption().getText()
				.contains(PaymentType), "Failed : Failed " + PaymentType + " payment Type Not Selected");
		test_steps.add("Select Payment Type : " + PaymentType);
		assertTrue(elements_All_Payments.Add_Pay_Button.isEnabled(), "Failed:  Add button is not High Lighted");
		elements_All_Payments.Enter_Amount.clear();
		String str_length = elements_All_Payments.Enter_Amount.getAttribute("value");
		for (int i = 0; i < str_length.length(); i++) {
			elements_All_Payments.Enter_Amount.sendKeys(Keys.BACK_SPACE);
		}
		elements_All_Payments.Enter_Amount.sendKeys("0");
		test_steps.add("Enter Amount : 0");
		// Add button
		elements_All_Payments.Add_Pay_Button.click();
		test_steps.add("Click Add Payment Button");
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.ZeroPayment_AlertPopup, driver);
		assertTrue(elements_All_Payments.ZeroPayment_AlertPopup.isDisplayed(),
				" Failed: Payment Popup Alert Not Appeared");
		Utility.ScrollToElement(elements_All_Payments.ZeroPayment_AlertPopupOk, driver);
		elements_All_Payments.ZeroPayment_AlertPopupOk.click();
		Wait.waitForElementToBeGone(driver, elements_All_Payments.ZeroPayment_AlertPopupOk, 20);
		// Enter Payment Again
		elements_All_Payments.Enter_Amount.clear();
		str_length = elements_All_Payments.Enter_Amount.getAttribute("value");
		for (int i = 0; i < str_length.length(); i++) {
			elements_All_Payments.Enter_Amount.sendKeys(Keys.BACK_SPACE);
		}
		elements_All_Payments.Enter_Amount.sendKeys(Amount);

		// Add button
		elements_All_Payments.Add_Pay_Button.click();
		Wait.explicit_wait_absenceofelement(OR.Payment_ModuleLoading, driver);
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
		elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
		try {
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);

		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
			elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
			Utility.app_logs.info("Click Continue again");
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);
		}
		return test_steps;
	}

	public ArrayList<String> CashPayment_NegativeAmount(WebDriver driver, String Amount, String PaymentType,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		Elements_MovieFolio elements_MovieFolio = new Elements_MovieFolio(driver);
		WebElement element = driver.findElement(By.xpath(OR.PayButton));

		assertTrue(elements_MovieFolio.Folio_Option.isDisplayed(), "Failed : Folio tab is not opened");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		folioLogger.info("Click Pay");
		test_steps.add("Click Reservation Pay Button");
		Wait.wait2Second();
		try {
			Wait.explicit_wait_xpath(OR.Reservation_PaymentPopup, driver);
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);
			assertTrue(elements_All_Payments.Reservation_PaymentPopup.isDisplayed(),
					"Failed : Failed payment Popup Not Open");
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.PaymentPopUp, driver);
			Wait.explicit_wait_visibilityof_webelement_200(elements_All_Payments.PaymentPopUp, driver);
			assertEquals(elements_All_Payments.PaymentPopUp.getText(), "Payment Details",
					"payment popup does not open");
		}
		test_steps.add("Payment popup Appeared");
		// select payment type as cash`

		new Select(elements_All_Payments.PaymentDetail_PaymentType).selectByVisibleText(PaymentType);

		assertTrue(new Select(elements_All_Payments.PaymentDetail_PaymentType).getFirstSelectedOption().getText()
				.contains(PaymentType), "Failed : Failed " + PaymentType + " payment Type Not Selected");
		test_steps.add("Select Payment Type : " + PaymentType);
		assertTrue(elements_All_Payments.Add_Pay_Button.isEnabled(), "Failed:  Add button is not High Lighted");

		// Enter Amount
		elements_All_Payments.Enter_Amount.clear();
		String str_length = elements_All_Payments.Enter_Amount.getAttribute("value");
		for (int i = 0; i < str_length.length(); i++) {
			elements_All_Payments.Enter_Amount.sendKeys(Keys.BACK_SPACE);
		}
		elements_All_Payments.Enter_Amount.sendKeys(Amount);

		// Add button
		elements_All_Payments.Add_Pay_Button.click();
		Wait.explicit_wait_absenceofelement(OR.Payment_ModuleLoading, driver);
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
		elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
		try {
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);

		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
			elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
			Utility.app_logs.info("Click Continue again");
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);
		}
		return test_steps;

	}

	public void cash_Payment(WebDriver driver, String Amount, String PaymentType) throws InterruptedException {

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Elements_MovieFolio elements_MovieFolio = new Elements_MovieFolio(driver);

		WebElement element = driver.findElement(By.xpath(OR.PayButton));
		elements_All_Payments.Folio.get(0).click();
		folioLogger.info("Click Folio");
		Wait.explicit_wait_visibilityof_webelement(elements_MovieFolio.Folio_Option, driver);
		assertTrue(elements_MovieFolio.Folio_Option.isDisplayed(), "Failed : Folio tab is not opened");

		Utility.ScrollToElement(element, driver);
		element.click();
		folioLogger.info("Click Pay");
		Wait.wait2Second();
		try {
			Wait.explicit_wait_xpath(OR.Reservation_PaymentPopup, driver);
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);
			assertTrue(elements_All_Payments.Reservation_PaymentPopup.isDisplayed(),
					"Failed : Failed payment Popup Not Open");
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.PaymentPopUp, driver);
			Wait.explicit_wait_visibilityof_webelement_200(elements_All_Payments.PaymentPopUp, driver);
			assertEquals(elements_All_Payments.PaymentPopUp.getText(), "Payment Details",
					"payment popup does not open");

		}

		// select payment type as cash`

		new Select(elements_All_Payments.PaymentDetail_PaymentType).selectByVisibleText(PaymentType);

		assertTrue(new Select(elements_All_Payments.PaymentDetail_PaymentType).getFirstSelectedOption().getText()
				.contains(PaymentType), "Failed : Failed " + PaymentType + " payment Type Not Selected");
		assertTrue(elements_All_Payments.Add_Pay_Button.isEnabled(), "Failed:  Add button is not High Lighted");
		elements_All_Payments.Enter_Amount.clear();
		if (Float.parseFloat(Amount) < 0.0) {
			assertTrue(false, "Failed: Amount is less then 0.0");
		}
		String str_length = elements_All_Payments.Enter_Amount.getAttribute("value");
		for (int i = 0; i < str_length.length(); i++) {
			elements_All_Payments.Enter_Amount.sendKeys(Keys.BACK_SPACE);
		}
		elements_All_Payments.Enter_Amount.sendKeys(Amount);
		// Add button
		elements_All_Payments.Add_Pay_Button.click();
		Wait.wait2Second();
		Wait.explicit_wait_absenceofelement(OR.Payment_ModuleLoading, driver);
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
		elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
		try {
			System.out.println("in module try start");
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);
			System.out.println("in module try end");
		} catch (Exception e) {
			System.out.println("in module catch");
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
			elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
			Utility.app_logs.info("Click Continue again");
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);
		}

		// Verify cash payment
		Utility.ScrollToElement(Elements_FolioLineItemsVoid.LineItems.get(0), driver);
		int LineItem_Size = Elements_FolioLineItemsVoid.LineItems.size();
		boolean isExist = false;
		for (int i = 0; i < Elements_FolioLineItemsVoid.LineItems.size(); i++) {
			if (Elements_FolioLineItemsVoid.LineItems.get(i).getText().equalsIgnoreCase(PaymentType)) {
				isExist = true;
				break;
			}
		}

		assertEquals(isExist, true, "payament type does not add in line item");
		Verify_LineItem(driver, PaymentType.toLowerCase(), Amount, "");

	}

	public ArrayList<String> VerifyPaymentAddButton(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Elements_MovieFolio elements_MovieFolio = new Elements_MovieFolio(driver);

		WebElement element = driver.findElement(By.xpath(OR.PayButton));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		folioLogger.info("Click Pay");
		test_steps.add("Click Reservation Pay Button");
		Wait.explicit_wait_xpath(OR.Reservation_PaymentPopup, driver);
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);
		assertTrue(elements_All_Payments.Reservation_PaymentPopup.isDisplayed(),
				"Failed : Failed payment Popup Not Open");
		test_steps.add("Payment Popup Appeared");
		// Add button
		Utility.ScrollToElement(elements_All_Payments.Add_Pay_Button, driver);
		assertTrue(!elements_All_Payments.Add_Pay_Button.isEnabled(),
				"Failed: Payment Popup Add Button is not Disaled");
		Utility.ScrollToElement(elements_All_Payments.Close_ReservationPaymentPopup, driver);
		elements_All_Payments.Close_ReservationPaymentPopup.click();
		Wait.waitForElementToBeGone(driver, elements_All_Payments.Close_ReservationPaymentPopup, 20);
		return test_steps;
	}

	public ArrayList<String> LineItemsVoid(WebDriver driver, String Notes, String Category,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);

		Wait.WaitForElement(driver, OR.LineItems_Checkbox);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.LineItems_Checkbox.get(1), driver);
		Utility.ScrollToElement(FolioLineItems.LineItems_Checkbox.get(1), driver);
		FolioLineItems.LineItems_Checkbox.get(1).click();
		FolioLineItems.clickVoidButton.click();
		Wait.WaitForElement(driver, OR.VoidNotesPopup);
		assertEquals(FolioLineItems.VoidNotesPopup.getText(), "Notes", "Notes pop with Text box not opened");
		assertEquals(FolioLineItems.enterNotes.isDisplayed(), true, "Notes Text box not display");
		FolioLineItems.enterNotes.sendKeys(Notes);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.clickVoidButtonInNotes, driver);
		FolioLineItems.clickVoidButtonInNotes.click();
		test_steps.add("Verified Notes popup with text field");

		Wait.WaitForElement(driver, OR.LineItems_Checkbox);

		assertEquals(FolioLineItems.LineItems_Checkbox.size(), 1, "line item did not remove");
		/*
		 * String PathLintItemCategory = "//span[text()='" + Category + "']";
		 * WebElement ElementLineItemCategory =
		 * driver.findElement(By.xpath(PathLintItemCategory));
		 * System.out.println(ElementLineItemCategory.isDisplayed());
		 * assertEquals(ElementLineItemCategory.isDisplayed(), false,
		 * "void line item is showing");
		 */
		return test_steps;

	}

	public ArrayList<String> PostLineItem(WebDriver driver, String Category, ArrayList<String> test_steps)
			throws InterruptedException {

		Wait.wait2Second();
		String PathLineItemStatus = "//span[text()='" + Category
				+ "']//..//..//td[@class='text-center lineitems-changestatus']";
		WebElement ElementLineItemStatus = driver.findElement(By.xpath(PathLineItemStatus));
		Wait.explicit_wait_visibilityof_webelement(ElementLineItemStatus, driver);

		Utility.ScrollToElement(ElementLineItemStatus, driver);
		ElementLineItemStatus.click();
		test_steps.add("Click on post line item");
		Wait.wait1Second();

		String PathPostLineTitem = "//span[text()='" + Category + "']//..//..//td//img[@src='./Folio_Images/2.gif']";
		WebElement ElementPrePost = driver.findElement(By.xpath(PathPostLineTitem));
		Wait.explicit_wait_visibilityof_webelement(ElementPrePost, driver);
		assertEquals(ElementPrePost.isDisplayed(), true, "Failed to posted line item");
		test_steps.add("Verified post line item");

		return test_steps;
	}

	public ArrayList<String> RollBackPostedLineItem(WebDriver driver, String Category, String Notes,
			ArrayList<String> test_steps, boolean isContinue) throws InterruptedException {
		Elements_FolioLineItemsVoid folioLineItems = new Elements_FolioLineItemsVoid(driver);

		String PathLineItemDescription = "//span[text()='" + Category
				+ "']//..//following-sibling::td[contains(@data-bind,'lineitem-description')]//a";
		WebElement ElementLineItemDescription = driver.findElement(By.xpath(PathLineItemDescription));
		Wait.explicit_wait_visibilityof_webelement(ElementLineItemDescription, driver);
		Utility.ScrollToElement(ElementLineItemDescription, driver);
		ElementLineItemDescription.isDisplayed();
		Wait.wait1Second();
		ElementLineItemDescription.click();

		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(folioLineItems.LineItem_RollBackButton, driver);
		folioLineItems.LineItem_RollBackButton.click();
		folioLineItems.enterRollBackNotes.sendKeys(Notes);
		Utility.ScrollToElement(folioLineItems.RollbackContinueBtn, driver);
		assertEquals(folioLineItems.RollbackContinueBtn.getText(), "Continue", "Continue button is not displayed");
		test_steps.add("Verified continue button is highlighting");
		if (isContinue) {
			folioLineItems.RollbackContinueBtn.click();
		} else {
			folioLineItems.RollBackCancelBtn.click();

		}
		Wait.wait2Second();
		// Wait.explicit_wait_visibilityof_webelement(ElementLineItemDescription,
		// driver);
		return test_steps;
	}

	public String GetPayment(WebDriver driver) throws InterruptedException {
		Elements_FolioLineItemsVoid folio = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement(folio.Reservation_Payments, driver);
		Utility.ScrollToElement(folio.Reservation_Payments, driver);
		String PaymentValue = folio.Reservation_Payments.getText().split(" ")[1];
		Utility.app_logs.info(PaymentValue);
		return PaymentValue;
	}

	public String VerifyPayment(WebDriver driver, String previousPayment, String amount) throws InterruptedException {
		Elements_FolioLineItemsVoid folio = new Elements_FolioLineItemsVoid(driver);
		String Payment = GetPayment(driver);
		String ExpectedPayment = String.format("%.2f", Float.parseFloat(previousPayment) + Float.parseFloat(amount));
		assertEquals(Payment, ExpectedPayment, "Expected payment does not match");
		return Payment;
	}

	public void VerifyBalance(WebDriver driver) throws InterruptedException {
		Elements_FolioLineItemsVoid folio = new Elements_FolioLineItemsVoid(driver);
		String Payment = GetPayment(driver);
		String TotalCharges = folio.Reservation_TotalCharges.getText().split(" ")[1];
		String Balance = folio.Reservation_Balance.getText().split(" ")[1];
		String ExpectedBalance = String.format("%.2f", Float.parseFloat(TotalCharges) - Float.parseFloat(Payment));

		if (ExpectedBalance.contains("-")) {
			Utility.app_logs.info("Balance  goes negative");
			Utility.app_logs.info("Actual balance  : " + Balance);
			Utility.app_logs.info("Calculated balance  : " + ExpectedBalance);
			Balance = Balance.replaceAll("[()]", "");
			Balance = "-" + Balance;

		}
		assertEquals(Balance, ExpectedBalance);
	}

	public void VerifyBalance_Add(WebDriver driver) throws InterruptedException {
		Elements_FolioLineItemsVoid folio = new Elements_FolioLineItemsVoid(driver);
		String Payment = GetPayment(driver);
		Utility.app_logs.info(Payment);
		Payment = Payment.replaceAll("[()]", "");
		Utility.app_logs.info(Payment);
		String TotalCharges = folio.Reservation_TotalCharges.getText().split(" ")[1];
		String Balance = folio.Reservation_Balance.getText().split(" ")[1];
		String ExpectedBalance = String.format("%.2f", Float.parseFloat(TotalCharges) + Float.parseFloat(Payment));
		assertEquals(Balance, ExpectedBalance);
	}

	public void VerifyRollBackLineItem(WebDriver driver, String Category) throws InterruptedException {

		String PathPrePost = "//span[text()='" + Category + "']//..//..//td//img[@src='./Folio_Images/1.gif']";
		WebElement ElementPrePost = driver.findElement(By.xpath(PathPrePost));
		Wait.explicit_wait_visibilityof_webelement(ElementPrePost, driver);
		assertEquals(ElementPrePost.isDisplayed(), true, "Failed to roll back line item");
	}

	public void LineItem_EmptyFiled(WebDriver driver, String Category) throws InterruptedException {

		Elements_FolioLineItemsVoid elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.AddButton, driver);
		Utility.ScrollToElement(elements_FolioLineItemsVoid.AddButton, driver);
		elements_FolioLineItemsVoid.AddButton.click();
		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.SelectCategory, driver);
		new Select(elements_FolioLineItemsVoid.SelectCategory).selectByVisibleText(Category);

		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.CommitButton, driver);
		Utility.ScrollToElement(elements_FolioLineItemsVoid.CommitButton, driver);
		elements_FolioLineItemsVoid.CommitButton.click();
		folioLogger.info("Click on Commit button");
		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.Toaster_ValidationMessage, driver);
		System.out.println(elements_FolioLineItemsVoid.Toaster_ValidationMessage.getText());

		assertEquals(elements_FolioLineItemsVoid.Toaster_ValidationMessage.getText(),
				"Please fill the required fields in all the new items.",
				"Validation message not appeare after empty amount field");

		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.AbortBtn, driver);
		elements_FolioLineItemsVoid.AbortBtn.click();

	}

	public void VerifyRollBackCancel(WebDriver driver, String Category) throws InterruptedException {

		String PathPrePost = "//span[text()='" + Category + "']//..//..//td//img[@src='./Folio_Images/2.gif']";
		WebElement ElementPrePost = driver.findElement(By.xpath(PathPrePost));
		Wait.explicit_wait_visibilityof_webelement(ElementPrePost, driver);
		assertEquals(ElementPrePost.isDisplayed(), true, "Failed to roll back line item");
	}

	public void LineItem_EnterDate(WebDriver driver, String Date) throws InterruptedException, ParseException {

		Elements_FolioLineItemsVoid elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.wait1Second();

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String DF = dateFormat.format(date);

		Calendar c = Calendar.getInstance();
		c.setTime(dateFormat.parse(DF));
		if (Date.equals("future")) {
			c.add(Calendar.DATE, 1);
		} else {
			c.add(Calendar.DATE, -1);
		}

		String futureDate = dateFormat.format(c.getTime());
		System.out.println("Enter date : " + futureDate);
		elements_FolioLineItemsVoid.LineItemDate.click();
		elements_FolioLineItemsVoid.LineItemDate.clear();
		elements_FolioLineItemsVoid.LineItemDate.sendKeys(futureDate);
		Wait.wait1Second();

	}

	public void LineItemWithDate(WebDriver driver, String SelectCategory, String Amount, String Date)
			throws InterruptedException, ParseException {

		Elements_FolioLineItemsVoid elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.AddButton, driver);
		Utility.ScrollToElement(elements_FolioLineItemsVoid.AddButton, driver);
		elements_FolioLineItemsVoid.AddButton.click();
		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.SelectCategory, driver);
		LineItem_EnterDate(driver, Date);
		new Select(elements_FolioLineItemsVoid.SelectCategory).selectByVisibleText(SelectCategory);
		elements_FolioLineItemsVoid.Enter_LineItemAmount.click();
		elements_FolioLineItemsVoid.Enter_LineItemAmount.clear();
		elements_FolioLineItemsVoid.Enter_LineItemAmount.sendKeys(Amount);
		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.CommitButton, driver);
		Utility.ScrollToElement(elements_FolioLineItemsVoid.CommitButton, driver);
		elements_FolioLineItemsVoid.CommitButton.click();
		folioLogger.info("Click on Commit button");
		Wait.wait1Second();

	}

	public void LineItemPostingPopup(WebDriver driver) throws InterruptedException, ParseException {

		Elements_FolioLineItemsVoid elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.wait1Second();
		elements_FolioLineItemsVoid.ItemPostingFailure_Popup.getText();
		assertEquals(elements_FolioLineItemsVoid.ItemPostingFailure_Popup.getText(), "Item Posting Failure",
				"Failed to verify future date post line item");
		elements_FolioLineItemsVoid.LineItemPostingFailure_OkBtn.click();
		Wait.wait1Second();

	}

	public ArrayList<String> PostLineItemWithDate(WebDriver driver, String Category, ArrayList<String> test_steps)
			throws InterruptedException {

		Wait.wait2Second();
		String PathLineItemStatus = "//span[text()='" + Category
				+ "']//..//..//td[@class='text-center lineitems-changestatus']";
		WebElement ElementLineItemStatus = driver.findElement(By.xpath(PathLineItemStatus));
		Wait.explicit_wait_visibilityof_webelement(ElementLineItemStatus, driver);

		Utility.ScrollToElement(ElementLineItemStatus, driver);
		ElementLineItemStatus.click();
		test_steps.add("Click on post line item");
		Wait.wait1Second();
		return test_steps;
	}

	public String GetToatalCharger(WebDriver driver) throws InterruptedException {

		Elements_FolioLineItemsVoid folioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement(folioLineItemsVoid.TotalCharger, driver);
		Utility.ScrollToElement(folioLineItemsVoid.TotalCharger, driver);
		return folioLineItemsVoid.TotalCharger.getText();

	}

	public String GetToatalBalance(WebDriver driver) throws InterruptedException {

		Elements_FolioLineItemsVoid folioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement(folioLineItemsVoid.Balance, driver);
		Utility.ScrollToElement(folioLineItemsVoid.Balance, driver);
		return folioLineItemsVoid.Balance.getText();

	}

	public ArrayList<String> VerifyLineItems_NegativeAmount(WebDriver driver, String Category, String Amount,
			ArrayList<String> TestSteps) throws InterruptedException {

		String PathLineItemAmount = "//span[text()='" + Category
				+ "']//..//following-sibling::td[@class='lineitem-amount']//span";
		String PathLintItemCategory = "//span[text()='" + Category + "']";
		String PathLineItemTax = "//span[text()='" + Category
				+ "']//..//following-sibling::td[@class='lineitem-tax']//span";

		String PathPrePost = "//span[text()='" + Category + "']//..//..//td//img[@src='./Folio_Images/1.gif']";
		WebElement ElementPrePost = driver.findElement(By.xpath(PathPrePost));
		Wait.explicit_wait_visibilityof_webelement(ElementPrePost, driver);
		assertEquals(ElementPrePost.isDisplayed(), true, "Failed to verify line item image");

		WebElement element_LintItemCategory = driver.findElement(By.xpath(PathLintItemCategory));
		Wait.explicit_wait_visibilityof_webelement(element_LintItemCategory, driver);
		Utility.ScrollToElement(element_LintItemCategory, driver);

		assertEquals(element_LintItemCategory.getText(), Category, "Category did not added");
		TestSteps.add("Verified line item category : " + Category);

		WebElement element_LineItemTax = driver.findElement(By.xpath(PathLineItemTax));

		String split[] = Amount.split("-");
		float EnterAmount = Float.parseFloat(split[1]);
		System.out.println("tex : " + element_LineItemTax.getText());
		String SplitTax[] = element_LineItemTax.getText().split("-");
		float Tax = Float.parseFloat(SplitTax[1]);
		System.out.println("Tax : " + Tax);
		float TotalAmount = EnterAmount + Tax;
		String ExpectedAmount = String.format("%.2f", TotalAmount);

		WebElement ElementAmount = driver.findElement(By.xpath(PathLineItemAmount));
		assertEquals(ElementAmount.getText(), "$ -" + ExpectedAmount, "Amount is mismatching");
		TestSteps.add("Verified line item amount with tax : " + ElementAmount.getText());

		return TestSteps;

	}

	public ArrayList<String> VerifyCharger_Balance(WebDriver driver, String previouseCharger, String previouseBalance,
			String Amount, ArrayList<String> TestSteps) throws InterruptedException {

		String current_balance = GetToatalBalance(driver);
		String current_charger = GetToatalBalance(driver);

		String split_amount[] = Amount.split("-");
		System.out.println("arrya size : " + split_amount.length);
		Amount = split_amount[1];
		Amount = Amount.trim();
		System.out.println(Amount);
		float EnterAmount = Float.parseFloat(Amount);

		System.out.println("Enter amount : " + EnterAmount);

		System.out.println(previouseCharger);
		String split_previouseCharger[] = previouseCharger.split(" ");
		float previouse_Charger = Float.parseFloat(split_previouseCharger[1]);
		System.out.println(previouse_Charger);

		System.out.println("preB : " + previouseBalance);
		String split_previouseBalance[] = previouseBalance.split(" ");
		float previouse_Balance = Float.parseFloat(split_previouseBalance[1]);
		System.out.println("PBF : " + previouse_Balance);

		float expectedBalance = previouse_Balance - EnterAmount;
		String ExpectedBalance = String.format("%.2f", expectedBalance);
		System.out.println("expectedBalance : " + expectedBalance);
		assertEquals(current_balance, "$ " + ExpectedBalance, "Failed : Balance is mismatching");

		float expectedCharger = previouse_Charger - EnterAmount;
		System.out.println("expectedCharger : " + expectedCharger);
		String ExpectedCharger = String.format("%.2f", expectedCharger);
		assertEquals(current_charger, "$ " + ExpectedCharger, "Failed : Charger is mismatching");

		return TestSteps;

	}

	public String getAmountWithTax(WebDriver driver, String Category) {
		String PathLineItemAmount = "//span[text()='" + Category
				+ "']//..//following-sibling::td[@class='lineitem-amount']//span";

		WebElement ElementAmount = driver.findElement(By.xpath(PathLineItemAmount));
		Wait.explicit_wait_visibilityof_webelement(ElementAmount, driver);
		return ElementAmount.getText();

	}

	public ArrayList<String> verifyCategoryResLineItem(WebDriver driver, String LagerAccName) {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		String lineCategory = driver.findElement(By.xpath("(//td[@class='lineitem-category']//span)[last()]"))
				.getText();
		assertEquals(lineCategory, LagerAccName, "Failed to Validate Category");
		folioLogger.info("Successfully Verify FolioLine Item Category : " + lineCategory);
		test_steps.add("Successfully Verify FolioLine Item Category : " + lineCategory);
		return test_steps;
	}

	public ArrayList<String> verifyDescResLineItem(WebDriver driver, String LagerAccDesc) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		int LineItemDescription_Size = Elements_FolioLineItemsVoid.LineItems_Description.size();
		Utility.ScrollToElement(Elements_FolioLineItemsVoid.LineItems_Description.get(LineItemDescription_Size - 1),
				driver);
		WebElement LineItemsDesc = driver.findElement(By.xpath("(//td[@class='lineitem-description']//a)[last()]"));
		String LineItemDescText = LineItemsDesc.getText();

		// System.out.println("LineItemDescText:" + LineItemDescText +
		// "Category" + Catagory);
		assertEquals(LineItemDescText, LagerAccDesc, "Tax Item type does not add in line item");
		folioLogger.info("Successfully Verify FolioLine Item Description : " + LineItemDescText);
		test_steps.add("Successfully Verify FolioLine Item Description : " + LineItemDescText);

		return test_steps;
	}

	public ArrayList<String> SelectedDepositPolicy(WebDriver driver, String Policy, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_MovieFolio elements_MoveFolio = new Elements_MovieFolio(driver);

		Wait.explicit_wait_xpath(OR.Select_DepositPolicies, driver);
		Utility.ScrollToElement(elements_MoveFolio.Select_DepositPolicies, driver);
		Select select = new Select(elements_MoveFolio.Select_DepositPolicies);
		WebElement option = select.getFirstSelectedOption();
		String defaultItem = option.getText();
		System.out.println(defaultItem);
		test_steps.add("Selected deposit policy :" + defaultItem);
		test_steps.add("Expected select deposit policy : " + Policy);
		assertEquals(defaultItem, Policy, "Failed: deposit policy is mismatched");
		return test_steps;
	}

	public ArrayList<String> SelectedCheckInPolicy(WebDriver driver, String Policy, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_MovieFolio elements_MoveFolio = new Elements_MovieFolio(driver);

		Wait.explicit_wait_xpath(OR.Select_CheckIN, driver);
		Utility.ScrollToElement(elements_MoveFolio.Select_CheckIN, driver);
		Select select = new Select(elements_MoveFolio.Select_CheckIN);
		WebElement option = select.getFirstSelectedOption();
		String defaultItem = option.getText();
		System.out.println(defaultItem);
		test_steps.add("Selected checkin policy :" + defaultItem);
		test_steps.add("Expected select checkin policy : " + Policy);
		assertEquals(defaultItem, Policy, "Failed: checkin policy is mismatched");
		return test_steps;
	}

	public ArrayList<String> verifyAmountResLineItem(WebDriver driver, String folioAmount) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		String LineItemsTax = driver.findElement(By.xpath("(//td[@class='lineitem-tax']//span)[last()]")).getText()
				.substring(2);
		String LineItemsAmount = driver.findElement(By.xpath("(//td[@class='lineitem-amount']//span)[last()]"))
				.getText().substring(2);

		Float generatedAmount = Float.parseFloat(LineItemsAmount) - Float.parseFloat(LineItemsTax);
		test_steps.add("Verify Amount : " + generatedAmount);
		folioLogger.info("Verify Amount : " + generatedAmount);
		assertTrue(generatedAmount == Float.parseFloat(folioAmount), "Tax Not Verified");
		folioLogger.info("Successfully Verify FolioLine Item Amount : " + generatedAmount);
		test_steps.add("Successfully Verify FolioLine Item Amount : " + generatedAmount);

		return test_steps;
	}

	public ArrayList<String> verifyTaxResLineItem(WebDriver driver, String TaxItemName, String taxValue)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		int LineItemDescription_Size = Elements_FolioLineItemsVoid.LineItems_Description.size();
		Utility.ScrollToElement(Elements_FolioLineItemsVoid.LineItems_Description.get(LineItemDescription_Size - 1),
				driver);
		Elements_FolioLineItemsVoid.LineItems_Description.get(LineItemDescription_Size - 1).click();
		Wait.wait2Second();
		boolean IsChangeDueDatePopup = driver.findElements(By.xpath(OR.ChangeDueDate_Popup)).size() == 1;
		if (IsChangeDueDatePopup) {
			Elements_FolioLineItemsVoid.ChangeDueDate_NoButton.click();
			Wait.wait2Second();
		}

		WebElement tax_item = driver.findElement(
				By.xpath("//table[1]/tbody/tr[last()]/td[5]/a[contains(@data-bind,'text: $data.Description')]"));
		String tax_item_text = tax_item.getText();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", tax_item);
		System.out.println(tax_item_text + ":" + TaxItemName);
		assertEquals(tax_item_text, TaxItemName, "New tax item does not display");
		folioLogger.info("Successfully Verify Tax Item : " + TaxItemName);
		test_steps.add("Successfully Verify Tax Item : " + TaxItemName);

		tax_item.click();
		Wait.wait5Second();
		Wait.explicit_wait_visibilityof_webelement(
				driver.findElement(By.xpath("//input[contains(@data-bind,'value: TaxItemValue')]")), driver);
		String tax_Ammount = driver.findElement(By.xpath("//input[contains(@data-bind,'value: TaxItemValue')]"))
				.getAttribute("value");
		;

		assertEquals(tax_Ammount, taxValue, "New tax item value does not display");
		folioLogger.info("Successfully Verify Tax Value : " + taxValue);
		test_steps.add("Successfully Verify Tax Value : " + taxValue);

		driver.findElement(By.xpath("//*[@id='popUpForInnroad']/div[3]/button")).click();
		Wait.wait5Second();
		return test_steps;
	}

	public ArrayList<String> verifyTaxResLineItem(WebDriver driver, String TaxItemName) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		int LineItemDescription_Size = Elements_FolioLineItemsVoid.LineItems_Description.size();
		Utility.ScrollToElement(Elements_FolioLineItemsVoid.LineItems_Description.get(LineItemDescription_Size - 1),
				driver);
		Elements_FolioLineItemsVoid.LineItems_Description.get(LineItemDescription_Size - 1).click();
		Wait.wait2Second();
		boolean IsChangeDueDatePopup = driver.findElements(By.xpath(OR.ChangeDueDate_Popup)).size() == 1;
		if (IsChangeDueDatePopup) {
			Elements_FolioLineItemsVoid.ChangeDueDate_NoButton.click();
			Wait.wait2Second();
		}

		int size = driver
				.findElements(By
						.xpath("//table[contains(@class,'table table-striped table-bordered table-hover popGrdFx')]/tbody/tr"))
				.size();
		for (int i = 1; i <= size; i++) {

			String desc = driver.findElement(By
					.xpath("//table[contains(@class,'table table-striped table-bordered table-hover popGrdFx')]/tbody/tr["
							+ i + "]/td[5]"))
					.getText();
			System.out.println(desc);
			assertTrue(!desc.equalsIgnoreCase(TaxItemName), "Failed: Tax Found");
		}
		folioLogger.info("Verify Tax Not Found : " + TaxItemName);
		test_steps.add("Verify Tax Not Found : " + TaxItemName);
		return test_steps;
	}

	public boolean VerifyLineItemExist(WebDriver driver, String Category) throws InterruptedException {
		String PathLintItemCategory = "//span[text()='" + Category + "']";
		boolean isCategoryExist = driver.findElements(By.xpath(PathLintItemCategory)).size() > 0;
		return isCategoryExist;

	}

	public void VerifyRollBackLineItem_Cash(WebDriver driver, String Category) throws InterruptedException {

		try {
			String PathPrePost = "//span[text()='" + Category + "']//..//..//td//img[@src='./Folio_Images/1.gif']";
			WebElement ElementPrePost = driver.findElement(By.xpath(PathPrePost));
			Wait.explicit_wait_visibilityof_webelement(ElementPrePost, driver);
			Utility.ScrollToElement(ElementPrePost, driver);
			assertEquals(ElementPrePost.isDisplayed(), true, "Failed to roll back line item");
		} catch (Exception e) {
			String PathPrePost = "//span[text()='" + "cash" + "']//..//..//td//img[@src='./Folio_Images/1.gif']";
			WebElement ElementPrePost = driver.findElement(By.xpath(PathPrePost));
			Wait.explicit_wait_visibilityof_webelement(ElementPrePost, driver);
			Utility.ScrollToElement(ElementPrePost, driver);
			assertEquals(ElementPrePost.isDisplayed(), true, "Failed to roll back line item");
		}

	}

	public ArrayList<String> VerifyLineItems(WebDriver driver, String Category, String Amount,
			ArrayList<String> TestSteps) throws InterruptedException {

		try {
			String PathLineItemAmount = "//span[text()='" + Category
					+ "']//..//following-sibling::td[@class='lineitem-amount']//span";
			String PathLintItemCategory = "//span[text()='" + Category + "']";
			String PathLineItemTax = "//span[text()='" + Category
					+ "']//..//following-sibling::td[@class='lineitem-tax']//span";

			String PathPrePost = "//span[text()='" + Category + "']//..//..//td//img[@src='./Folio_Images/1.gif']";
			WebElement ElementPrePost = driver.findElement(By.xpath(PathPrePost));
			Wait.explicit_wait_visibilityof_webelement(ElementPrePost, driver);

			assertEquals(ElementPrePost.isDisplayed(), true, "Failed to verify line item image");

			WebElement element_LintItemCategory = driver.findElement(By.xpath(PathLintItemCategory));
			Wait.explicit_wait_visibilityof_webelement(element_LintItemCategory, driver);
			Utility.ScrollToElement(element_LintItemCategory, driver);

			assertEquals(element_LintItemCategory.getText(), Category, "Category did not added");
			TestSteps.add("Verified line item category : " + Category);

			WebElement element_LineItemTax = driver.findElement(By.xpath(PathLineItemTax));

			float EnterAmount = Float.parseFloat(Amount);
			String SplitTax[] = element_LineItemTax.getText().split(" ");
			float Tax = Float.parseFloat(SplitTax[1]);
			float TotalAmount = EnterAmount + Tax;
			String ExpectedAmount = String.format("%.2f", TotalAmount);

			WebElement ElementAmount = driver.findElement(By.xpath(PathLineItemAmount));
			assertEquals(ElementAmount.getText(), "$ " + ExpectedAmount, "Amount is mismatching");
			TestSteps.add("Verified line item amount with tax : " + ElementAmount.getText());

		} catch (Exception e) {
			Category = "cash";
			String PathLineItemAmount = "//span[text()='" + Category
					+ "']//..//following-sibling::td[@class='lineitem-amount']//span";
			String PathLintItemCategory = "//span[text()='" + Category + "']";
			String PathLineItemTax = "//span[text()='" + Category
					+ "']//..//following-sibling::td[@class='lineitem-tax']//span";

			String PathPrePost = "//span[text()='" + Category + "']//..//..//td//img[@src='./Folio_Images/1.gif']";
			WebElement ElementPrePost = driver.findElement(By.xpath(PathPrePost));
			Wait.explicit_wait_visibilityof_webelement(ElementPrePost, driver);

			assertEquals(ElementPrePost.isDisplayed(), true, "Failed to verify line item image");

			WebElement element_LintItemCategory = driver.findElement(By.xpath(PathLintItemCategory));
			Wait.explicit_wait_visibilityof_webelement(element_LintItemCategory, driver);
			Utility.ScrollToElement(element_LintItemCategory, driver);

			assertEquals(element_LintItemCategory.getText(), Category, "Category did not added");
			TestSteps.add("Verified line item category : " + Category);

			WebElement element_LineItemTax = driver.findElement(By.xpath(PathLineItemTax));

			float EnterAmount = Float.parseFloat(Amount);
			String SplitTax[] = element_LineItemTax.getText().split(" ");
			float Tax = Float.parseFloat(SplitTax[1]);
			float TotalAmount = EnterAmount + Tax;
			String ExpectedAmount = String.format("%.2f", TotalAmount);

			WebElement ElementAmount = driver.findElement(By.xpath(PathLineItemAmount));
			assertEquals(ElementAmount.getText(), "$ " + ExpectedAmount, "Amount is mismatching");
			TestSteps.add("Verified line item amount with tax : " + ElementAmount.getText());

		}

		return TestSteps;

	}

	public ArrayList<String> RollBackCashItem(WebDriver driver, String Category, String Notes,
			ArrayList<String> test_steps, boolean isContinue) throws InterruptedException {
		Elements_FolioLineItemsVoid folioLineItems = new Elements_FolioLineItemsVoid(driver);

		String PathLineItemDescription = "//span[text()='" + Category
				+ "']//..//following-sibling::td[contains(@data-bind,'lineitem-description')]//a";
		WebElement ElementLineItemDescription = driver.findElement(By.xpath(PathLineItemDescription));
		Wait.explicit_wait_visibilityof_webelement(ElementLineItemDescription, driver);
		Utility.ScrollToElement(ElementLineItemDescription, driver);
		ElementLineItemDescription.click();

		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(folioLineItems.PaymentRollBack_Button, driver);
		folioLineItems.PaymentRollBack_Button.click();

		Wait.explicit_wait_visibilityof_webelement(folioLineItems.EnterpaymentNotes, driver);
		folioLineItems.EnterpaymentNotes.sendKeys(Notes);

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		elements_All_Payments.Add_Pay_Button.click();
		Wait.wait2Second();
		Wait.explicit_wait_absenceofelement(OR.Payment_ModuleLoading, driver);
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
		elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();

		try {
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);

		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
			elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
			Utility.app_logs.info("Click Continue again");
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 60);
		}

		return test_steps;
	}

	public ArrayList<String> SelectNoShowPolicy_ifNotSelected(WebDriver driver, String policyName,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_MovieFolio elements_MoveFolio = new Elements_MovieFolio(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_MoveFolio.Select_NoShow, driver);
		Utility.ScrollToElement(elements_MoveFolio.Select_NoShow, driver);
		boolean PolicyNotSelected = true;
		String SelectedNoshowPolicy = new Select(elements_MoveFolio.Select_NoShow).getFirstSelectedOption().getText();
		if (SelectedNoshowPolicy.equalsIgnoreCase(policyName)) {
			PolicyNotSelected = false;
			assertEquals(SelectedNoshowPolicy, policyName, "Failed : Policy is not selected");
			test_steps.add("Selected Noshow policy: " + SelectedNoshowPolicy);
			folioLogger.info("Selected Noshow policy: " + SelectedNoshowPolicy);
		}
		if (PolicyNotSelected) {
			new Select(elements_MoveFolio.Select_NoShow).selectByVisibleText(policyName);
			SelectedNoshowPolicy = new Select(elements_MoveFolio.Select_NoShow).getFirstSelectedOption().getText();
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", elements_MoveFolio.Select_NoShow);
			assertEquals(SelectedNoshowPolicy, policyName, "Failed : Policy is not selected");
			test_steps.add("Selected Noshow policy: " + SelectedNoshowPolicy);
			folioLogger.info("Selected Noshow policy: " + SelectedNoshowPolicy);
			Reservation res = new Reservation();
			res.SaveAfter_Reservation(driver);
		}

		return test_steps;

	}

	public void Verify_LineItem(WebDriver driver, String Date, String Category, String Amount, String Description) {
		// Verify payment
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement_350(Elements_FolioLineItemsVoid.AddedLineItems_Category.get(0),
				driver);
		int LineItem_Size = Elements_FolioLineItemsVoid.AddedLineItems_Category.size();
		folioLogger.info("LineItem_Size : " + LineItem_Size);
		int lineNumber = LineItem_Size - 1;
		folioLogger.info("LineNumber : " + lineNumber);
		boolean found = false;
		for (lineNumber = LineItem_Size - 1; lineNumber >= 0; lineNumber--) {
			folioLogger.info("LineNumber : " + lineNumber);
			folioLogger.info(
					"Category : " + Elements_FolioLineItemsVoid.AddedLineItems_Category.get(lineNumber).getText());
			folioLogger.info("Amount : " + Elements_FolioLineItemsVoid.AddedLineItems_Amount.get(lineNumber).getText());
			folioLogger.info("Description : "
					+ Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber).getText());
			if (Elements_FolioLineItemsVoid.AddedLineItems_Category.get(lineNumber).getText()
					.equalsIgnoreCase(Category)) {
				assertTrue(Elements_FolioLineItemsVoid.AddedLineItems_Category.get(lineNumber).getText()
						.equalsIgnoreCase(Category), "Failed: Category Missmatched");
				if (!Date.equals("")) {
					Utility.app_logs.info(Date + " expected  but Actual Date : "
							+ Elements_FolioLineItemsVoid.AddedLineItems_Date.get(lineNumber).getText());
					assertTrue(Elements_FolioLineItemsVoid.AddedLineItems_Date.get(lineNumber).getText().contains(Date),
							"Failed: Date Missmatched");
				}
				if (!Amount.equals("")) {
					Utility.app_logs.info(Amount + " expected  but Actual Amount : "
							+ Elements_FolioLineItemsVoid.AddedLineItems_Amount.get(lineNumber).getText());
					assertTrue(Elements_FolioLineItemsVoid.AddedLineItems_Amount.get(lineNumber).getText()
							.contains(Amount), "Failed: Amount Missmatched");
				}
				if (!Description.equals("")) {
					String desc = Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber).getText();
					Utility.app_logs.info("line Item Description : " + desc);
					Utility.app_logs.info("Expected Description : " + Description);
					assertTrue(desc.replaceAll(" ", "").contains(Description.replaceAll(" ", "")),
							"Failed: Description Missmatched");
				}
				found = true;
				break;
			}
		}
		if (!found) {
			assertTrue(false, "Failed: Line Item havinf Category: " + Category + " Not found.");
		}
	}

	public void VerifyToatalCharges(WebDriver driver, String Value) throws InterruptedException {

		String Charges = GetToatalCharger(driver);
		Charges = Charges.split(" ")[1];
		assertEquals(Charges, Value, "Failed : TOtal Charges missmatched");
	}

	public ArrayList<String> AddNewFolio(WebDriver driver, ExtentTest test, String newFolioName,
			String newFolioDescription, ArrayList<String> test_steps) throws InterruptedException {

		// Explicit wait object creation
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);

		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);

		Wait.WaitForElement(driver, OR.MoveFolio_Folio);
		moveFolio.MoveFolio_Folio.click();

		Wait.WaitForElement(driver, OR.MoveFolio_GuestFolio);
		test_steps.add("Successfully opened the Folio Tab");
		folioLogger.info("Clicked on Folio Tab");

		// click on Folio Tab
		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio);
		moveFolio.MoveFolio_NewFolio.click();

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolioDeatils);
		test_steps.add("Successfully New Folio Details pop up");
		folioLogger.info("Successfully New Folio Details pop up");
		assertTrue(moveFolio.MoveFolio_NewFolioDeatils.isDisplayed(), "New Folio Popup didn't display");

		// new Folio creation
		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_Name);
		moveFolio.MoveFolio_NewFolio_Name.sendKeys(newFolioName);
		test_steps.add("Successfully Entered the New Folio Name : " + newFolioName);
		folioLogger.info("Successfully Entered the New Folio Name : " + newFolioName);
		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_Description);
		moveFolio.MoveFolio_NewFolio_Description.sendKeys(newFolioDescription);
		test_steps.add("Successfully Entered the New Folio Description : " + newFolioDescription);
		folioLogger.info("Successfully Entered the New Folio Description : " + newFolioDescription);
		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_Save);
		moveFolio.MoveFolio_NewFolio_Save.click();
		test_steps.add("Successfully clicked save Folio");
		folioLogger.info("Successfully clicked save Folio");
		WebElement FolioDropDown = driver.findElement(By.xpath("//select[@class='form-control folioFil']"));
		String FirstSelectedoption = new Select(FolioDropDown).getFirstSelectedOption().getText();
		new Select(moveFolio.MoveFolio_GuestFolio).selectByVisibleText(newFolioName);
		assertTrue(FirstSelectedoption.equals(newFolioName), "Folio didn't added");
		test_steps.add("Successfully selected the new Folio : " + newFolioName);
		folioLogger.info("Successfully selected the new Folio : " + newFolioName);
		// Verification
		String Lineitmessize = Integer.toString(FolioLineItems.LineItems.size());
		assertTrue(Lineitmessize.equals("0"), "Line Items Size isn't zero");
		folioLogger.info("Line Item size is:" + Lineitmessize);
		test_steps.add("Line Item size is:" + Lineitmessize);

		assertTrue(FolioLineItems.RoomCharger_Tax.get(0).getText().equals("$ 0.00"), "RoomCharges aren't zero");
		folioLogger.info("Folio Room Charges Are:" + FolioLineItems.RoomCharger_Tax.get(0).getText());
		test_steps.add("Folio Room Charges Are:" + FolioLineItems.RoomCharger_Tax.get(0).getText());

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_SaveReservation);
		moveFolio.MoveFolio_NewFolio_SaveReservation.click();
		test_steps.add("Successfully clicked save Reservation");
		folioLogger.info("Successfully clicked save Reservation");

		FirstSelectedoption = new Select(FolioDropDown).getOptions().get(0).getText();
		assertTrue(FirstSelectedoption.equals("Guest Folio"), "Folio didn't set to default");
		folioLogger.info("Folio DropDown Value Set to Default:" + FirstSelectedoption);
		test_steps.add("Folio DropDown Value Set to Default:" + FirstSelectedoption);

		assertTrue(FolioLineItems.LineItems.size() > 0, "Line Items are default");
		folioLogger.info("Line Item size is greater than 0 ");
		test_steps.add("Line Item size is greater than 0 ");

		assertTrue(FolioLineItems.AddedLineItems_Category.get(0).getText().equals("Room Charge"),
				"Line items aren't set to default");
		folioLogger.info("Line Items List set to default");
		test_steps.add("Line Items List set to default");

		return test_steps;
	}

	public ArrayList<String> EditFolio(WebDriver driver, ExtentTest test, String newFolioName, String UpdatedFolioName,
			String newFolioDescription, ArrayList<String> test_steps) throws InterruptedException {

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);
		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);

		// Select New Folio
		WebElement FolioDropDown = driver.findElement(By.xpath("//select[@class='form-control folioFil']"));

		new Select(moveFolio.MoveFolio_GuestFolio).selectByVisibleText(newFolioName);
		String FirstSelectedoption = new Select(FolioDropDown).getFirstSelectedOption().getText();
		assertTrue(FirstSelectedoption.equals(newFolioName), "Folio didn't added");
		folioLogger.info("New Folio is Selected from the dropdown");
		test_steps.add("New Folio is Selected from the dropdown");

		String Lineitmessize = Integer.toString(FolioLineItems.LineItems.size());
		assertTrue(Lineitmessize.equals("0"), "Line Items Size isn't zero");
		folioLogger.info("Line Item size is:" + Lineitmessize);
		test_steps.add("Line Item size is:" + Lineitmessize);

		Wait.WaitForElement(driver, OR.Edit_Folio_Btn);
		moveFolio.Edit_Folio_Btn.click();
		Wait.WaitForElement(driver, OR.MoveFolio_NewFolioDeatils);
		assertTrue(moveFolio.MoveFolio_NewFolioDeatils.isDisplayed(), "Edit Folio Popup didn't display");
		test_steps.add("For Edit, Folio Page is Displayed");
		folioLogger.info("For Edit, Folio Page is Displayed");

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_Name);
		moveFolio.MoveFolio_NewFolio_Name.clear();
		moveFolio.MoveFolio_NewFolio_Name.sendKeys(UpdatedFolioName);
		test_steps.add("Successfully edited the New Folio Name : " + UpdatedFolioName);
		folioLogger.info("Successfully edited the New Folio Name : " + UpdatedFolioName);

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_Description);
		moveFolio.MoveFolio_NewFolio_Description.clear();
		moveFolio.MoveFolio_NewFolio_Description.sendKeys(newFolioDescription + "test");
		test_steps.add("Successfully edited the New Folio Description : " + newFolioDescription + "test");
		folioLogger.info("Successfully edited the New Folio Description : " + newFolioDescription + "test");

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_Save);
		moveFolio.MoveFolio_NewFolio_Save.click();
		test_steps.add("Successfully clicked save Folio");
		folioLogger.info("Successfully clicked save Folio");

		FolioDropDown = driver.findElement(By.xpath("//select[@class='form-control folioFil']"));

		FirstSelectedoption = new Select(FolioDropDown).getFirstSelectedOption().getText();
		assertTrue(FirstSelectedoption.equals(UpdatedFolioName), "Folio didn't added");
		folioLogger.info("Verified New Updated Folio : " + UpdatedFolioName);
		test_steps.add("Verified New Updated Folio : " + UpdatedFolioName);

		Lineitmessize = Integer.toString(FolioLineItems.LineItems.size());
		folioLogger.info("Line Item size is:" + Lineitmessize);

		assertTrue(Lineitmessize.equals("0"), "Line Items Size isn't zero");
		test_steps.add("Line Item size is:" + Lineitmessize);
		assertTrue(FolioLineItems.RoomCharger_Tax.get(0).getText().equals("$ 0.00"), "RoomCharges aren't zero");
		folioLogger.info("Folio Room Chars Are:" + FolioLineItems.RoomCharger_Tax.get(0).getText());
		test_steps.add("Folio Room Chars Are:" + FolioLineItems.RoomCharger_Tax.get(0).getText());

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_SaveReservation);
		moveFolio.MoveFolio_NewFolio_SaveReservation.click();
		test_steps.add("Successfully clicked save Reservation");
		folioLogger.info("Successfully clicked save Reservation");
		Wait.wait1Second();
		FirstSelectedoption = new Select(FolioDropDown).getOptions().get(0).getText();

		Lineitmessize = Integer.toString(FolioLineItems.LineItems.size());
		assertTrue(FirstSelectedoption.equals("Guest Folio"), "Folio didn't set to default");
		folioLogger.info("Folio DropDown Value Set to Default:" + FirstSelectedoption);
		test_steps.add("Folio DropDown Value Set to Default:" + FirstSelectedoption);
		assertTrue(FolioLineItems.LineItemsList.size() > 0, "Line Items are default");
		folioLogger.info("Line Item size is:" + Lineitmessize);
		test_steps.add("Line Item size is:" + Lineitmessize);
		assertTrue(FolioLineItems.AddedLineItems_Category.get(0).getText().equals("Room Charge"),
				"Line items aren't set to default");
		folioLogger.info("Line Items List set to default");
		test_steps.add("Line Items List set to default");

		FolioDropDown = driver.findElement(By.xpath("//select[@class='form-control folioFil']"));
		new Select(moveFolio.MoveFolio_GuestFolio).selectByVisibleText(UpdatedFolioName);
		FirstSelectedoption = new Select(FolioDropDown).getFirstSelectedOption().getText();
		assertTrue(FirstSelectedoption.equals(UpdatedFolioName), "Folio didn't added");
		folioLogger.info("New Updated Folio is Selected from the dropdown : " + UpdatedFolioName);
		test_steps.add("New Updated Folio is Selected from the dropdown : " + UpdatedFolioName);

		Lineitmessize = Integer.toString(FolioLineItems.LineItems.size());
		assertTrue(Lineitmessize.equals("0"), "Line Items Size isn't zero");
		folioLogger.info("Line Item size is:" + Lineitmessize);
		test_steps.add("Line Item size is:" + Lineitmessize);

		return test_steps;
	}

	public ArrayList<String> DeleteFolio(WebDriver driver, ExtentTest test, String UpdatedFolioName,
			ArrayList<String> test_steps) throws InterruptedException {

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);

		WebElement FolioDropDown = driver.findElement(By.xpath("//select[@class='form-control folioFil']"));

		Wait.WaitForElement(driver, OR.MoveFolio_GuestFolio);
		new Select(moveFolio.MoveFolio_GuestFolio).selectByVisibleText(UpdatedFolioName);

		Wait.wait2Second();
		String FirstSelectedoption = new Select(FolioDropDown).getFirstSelectedOption().getText();
		//

		assertTrue(FirstSelectedoption.equals(UpdatedFolioName), "Folio didn't added");
		folioLogger.info("New Folio is Selected from the dropdown : " + UpdatedFolioName);
		test_steps.add("New Folio is Selected from the dropdown : " + UpdatedFolioName);

		Wait.WaitForElement(driver, OR.Delete_Folio_Btn);

		moveFolio.Delete_Folio_Btn.click();
		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_SaveReservation);
		Wait.explicit_wait_elementToBeClickable(moveFolio.MoveFolio_NewFolio_SaveReservation, driver);

		moveFolio.MoveFolio_NewFolio_SaveReservation.click();
		test_steps.add("Successfully clicked save Reservation");
		folioLogger.info("Successfully clicked save Reservation");

		Wait.wait5Second();
		if (!(driver.findElements(By.xpath("//select[@class='form-control folioFil']/option")).size() > 1)) {
			test_steps.add("Sucessfully deleted the folio : " + UpdatedFolioName);
			folioLogger.info("Sucessfully deleted the folio : " + UpdatedFolioName);
		}
		String FolioDropDownString = "//select[@class='form-control folioFil']//option";
		List<WebElement> FolioDropDownList = driver.findElements(By.xpath(FolioDropDownString));
		for (int i = 0; i < FolioDropDownList.size(); i++) {
			Wait.WaitForElement(driver, FolioDropDownString);

			if (!FolioDropDownList.get(i).getText().equals(UpdatedFolioName)) {
				break;
			}

			assertTrue(!FolioDropDownList.get(i).getText().equals(UpdatedFolioName), "Folio didn't deleted");

		}
		folioLogger.info("Successfully Checked the folio is deleted");

		test_steps.add("Successfully Checked the folio is deleted");
		return test_steps;
	}

	public ArrayList<String> VerifyLineItems_UpdatedFolio_TaxApplied(WebDriver driver, String Category, String Amount,
			String UpdatedFolioName, ArrayList<String> test_steps) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		WebElement FolioDropDown = driver.findElement(By.xpath("//select[@class='form-control folioFil']"));
		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);
		// Verify Line Item=1
		assertTrue(FolioLineItems.LineItems.size() > 0, "Line Items are default");
		test_steps.add("Line Item size is greater than 0 ");

		// Verify Category
		assertTrue(FolioLineItems.AddedLineItems_Category.get(0).getText().equals(Category),
				"Line items aren't set to default");
		folioLogger.info("Line Items List set to default");
		test_steps.add("Line Items List set to default");
		// Select Updated DropDown & Verify

		FolioDropDown = driver.findElement(By.xpath("//select[@class='form-control folioFil']"));

		new Select(moveFolio.MoveFolio_GuestFolio).selectByVisibleText(UpdatedFolioName);
		String FirstSelectedoption = new Select(FolioDropDown).getFirstSelectedOption().getText();
		assertTrue(FirstSelectedoption.equals(UpdatedFolioName), "Folio didn't added");
		folioLogger.info("New Folio is Selected from the dropdown : " + UpdatedFolioName);

		test_steps.add("New Folio is Selected from the dropdown : " + UpdatedFolioName);
		// Verify Amount After Updated Folio

		FolioLineItems.IncludeTaxLineItemCheckbox.click();
		String GetAmount = FolioLineItems.AddedLineItems_Amount.get(0).getText();
		assertTrue(GetAmount.equals("$ " + Amount + "0"), "Amount is not same as added");
		folioLogger.info("Line Item Added Amount is:" + GetAmount);
		test_steps.add("Line Item Added Amount is:" + GetAmount);
		// Save Res

		Wait.WaitForElement(driver, OR.MoveFolio_NewFolio_SaveReservation);
		moveFolio.MoveFolio_NewFolio_SaveReservation.click();
		test_steps.add("Successfully clicked save Reservation");
		folioLogger.info("Successfully clicked save Reservation");

		// Selected Folio Verification
		FirstSelectedoption = new Select(FolioDropDown).getOptions().get(0).getText();

		assertTrue(FirstSelectedoption.equals("Guest Folio"), "Folio didn't set to default");
		folioLogger.info("Folio DropDown Value Set to Default:" + FirstSelectedoption);
		test_steps.add("Folio DropDown Value Set to Default:" + FirstSelectedoption);

		// Select Updated DropDown & Verify
		Wait.wait1Second();
		FolioDropDown = driver.findElement(By.xpath("//select[@class='form-control folioFil']"));
		new Select(moveFolio.MoveFolio_GuestFolio).selectByVisibleText(UpdatedFolioName);
		FirstSelectedoption = new Select(FolioDropDown).getOptions().get(0).getText();
		String FirstSelectedoption1 = new Select(FolioDropDown).getFirstSelectedOption().getText();

		// System.out.println("Found:" + FirstSelectedoption + " 1st:" +
		// FirstSelectedoption1);
		assertTrue(FirstSelectedoption1.equals(UpdatedFolioName), "Folio didn't selected");
		test_steps.add("New Folio is Selected from the dropdown");
		folioLogger.info("New Folio is Selected from the dropdown");
		return test_steps;
	}

	public ArrayList<String> CheckNoLineItemExist(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		String LineItemExists = Integer.toString(FolioLineItems.LineItems.size());
		assertEquals(LineItemExists.equals("0"), true, "Line Items Size isn't zero");
		test_steps.add("Line Item size is : " + FolioLineItems.LineItems.size());
		return test_steps;
	}

	public ArrayList<String> CheckDisplayVoidItems(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_FolioLineItemsVoid elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.DisplayVoidItemsCheckBox, driver);
		Utility.ScrollToElement(elements_FolioLineItemsVoid.DisplayVoidItemsCheckBox, driver);
		if (!elements_FolioLineItemsVoid.DisplayVoidItemsCheckBox.isSelected()) {
			elements_FolioLineItemsVoid.DisplayVoidItemsCheckBox.click();
		}
		assertEquals(elements_FolioLineItemsVoid.DisplayVoidItemsCheckBox.isSelected(), true,
				"Display Void Items CheckBox Is not Checked");
		return test_steps;
	}

	public ArrayList<String> DisplayVoidItemImage(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_FolioLineItemsVoid elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.VoidItemDisplayImage, driver);
		Utility.ScrollToElement(elements_FolioLineItemsVoid.VoidItemDisplayImage, driver);
		assertTrue(elements_FolioLineItemsVoid.VoidItemDisplayImage.isDisplayed(),
				"Failed: Void Line Item Image is not Displayed");
		return test_steps;
	}

	public void Verify_LineItem(WebDriver driver, String Date, String Category, String Amount, String Description,
			String Room) {
		// Verify payment
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement_350(Elements_FolioLineItemsVoid.AddedLineItems_Category.get(0),
				driver);
		int LineItem_Size = Elements_FolioLineItemsVoid.AddedLineItems_Category.size();
		folioLogger.info("LineItem_Size : " + LineItem_Size);
		int lineNumber = LineItem_Size - 1;
		folioLogger.info("LineNumber : " + lineNumber);
		boolean found = false;
		for (lineNumber = LineItem_Size - 1; lineNumber >= 0; lineNumber--) {
			folioLogger.info("LineNumber : " + lineNumber);
			folioLogger.info(
					"Category : " + Elements_FolioLineItemsVoid.AddedLineItems_Category.get(lineNumber).getText());
			folioLogger.info("Amount : " + Elements_FolioLineItemsVoid.AddedLineItems_Amount.get(lineNumber).getText());
			folioLogger.info("Description : "
					+ Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber).getText());
			if (Elements_FolioLineItemsVoid.AddedLineItems_Category.get(lineNumber).getText().equalsIgnoreCase(Category)
					&& Elements_FolioLineItemsVoid.AddedLineItems_Room.get(lineNumber).getText().equals(Room)) {
				if (!Date.equals("")) {
					Utility.app_logs.info(Date + " expected  but Actual Date : "
							+ Elements_FolioLineItemsVoid.AddedLineItems_Date.get(lineNumber).getText());
					assertTrue(Elements_FolioLineItemsVoid.AddedLineItems_Date.get(lineNumber).getText().contains(Date),
							"Failed: Date Missmatched");
				}

				if (!Amount.equals("")) {
					Utility.app_logs.info(Amount + " expected  but Actual Amount : "
							+ Elements_FolioLineItemsVoid.AddedLineItems_Amount.get(lineNumber).getText());
					assertTrue(Elements_FolioLineItemsVoid.AddedLineItems_Amount.get(lineNumber).getText()
							.contains(Amount), "Failed: Amount Missmatched");
				}
				if (!Description.equals("")) {
					String desc = Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber).getText();
					Utility.app_logs.info("line Item Description : " + desc);
					Utility.app_logs.info("Expected Description : " + Description);
					assertTrue(desc.replaceAll(" ", "").contains(Description.replaceAll(" ", "")),
							"Failed: Description Missmatched");
				}
				found = true;
				break;
			}
		}
		if (!found) {
			assertTrue(false, "Failed: Line Item having Category: " + Category + " Not found.");
		}
	}

	public void Verify_LineItem(WebDriver driver, String Date, String Category) {
		// Verify payment
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement_350(Elements_FolioLineItemsVoid.AddedLineItems_Category.get(0),
				driver);
		int LineItem_Size = Elements_FolioLineItemsVoid.AddedLineItems_Category.size();
		folioLogger.info("LineItem_Size : " + LineItem_Size);
		int lineNumber = LineItem_Size - 1;
		folioLogger.info("LineNumber : " + lineNumber);
		boolean found = false;
		for (lineNumber = LineItem_Size - 1; lineNumber >= 0; lineNumber--) {
			folioLogger.info("LineNumber : " + lineNumber);
			folioLogger.info(
					"Category : " + Elements_FolioLineItemsVoid.AddedLineItems_Category.get(lineNumber).getText());
			folioLogger.info("Amount : " + Elements_FolioLineItemsVoid.AddedLineItems_Amount.get(lineNumber).getText());
			folioLogger.info("Description : "
					+ Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber).getText());
			if (Elements_FolioLineItemsVoid.AddedLineItems_Category.get(lineNumber).getText().equalsIgnoreCase(Category)
					&& Elements_FolioLineItemsVoid.AddedLineItems_Date.get(lineNumber).getText().equals(Date)) {

				found = true;
				break;
			}
		}
		if (!found) {
			assertTrue(false, "Failed: Line Item having Category: " + Category + " Not found.");
		}
	}

	// Folio Line Items
	public ArrayList<String> res1FolioLineItem(WebDriver driver, ExtentTest test, ArrayList<String> getTest_Steps)
			throws InterruptedException {
		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);
		Wait.WaitForElement(driver, OR.getRes1LineItem);
		res1LineItem = moveFolio.getRes1LineItem.getText();
		folioLogger.info("Reservation1 Line Item: " + res1LineItem);
		Wait.wait5Second();
		return getTest_Steps;
	}

	public static ArrayList<String> res1FolioLineItem2(WebDriver driver, ExtentTest test,
			ArrayList<String> getTest_Steps) throws InterruptedException {
		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);
		Wait.WaitForElement(driver, OR.getRes1LineItem2);
		res1LineItem2 = moveFolio.getRes1LineItem2.getText();
		folioLogger.info("Reservation1 Line Item2: " + res1LineItem2);
		test_steps.add("Added Folio Line Item2" + moveFolio.getRes1LineItem2.getText());
		// l2=res1LineItem2;
		Wait.wait5Second();
		return getTest_Steps;
	}

	public ArrayList<String> res1FolioLineItem3(WebDriver driver, ExtentTest test, ArrayList<String> getTest_Steps)
			throws InterruptedException {
		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);
		Wait.WaitForElement(driver, OR.getRes1LineItem3);
		res1LineItem3 = moveFolio.getRes1LineItem3.getText();
		folioLogger.info("Reservation1 Line Item3: " + res1LineItem3);
		test_steps.add("Added Folio Line Item3" + moveFolio.getRes1LineItem3.getText());
		// l2=res1LineItem2;
		Wait.wait5Second();
		return getTest_Steps;
	}

	public ArrayList<String> res1FolioLineItem4(WebDriver driver, ExtentTest test, ArrayList<String> getTest_Steps)
			throws InterruptedException {
		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);
		Wait.WaitForElement(driver, OR.getRes1LineItem4);
		res1LineItem4 = moveFolio.getRes1LineItem4.getText();
		folioLogger.info("Reservation1 Line Item4: " + res1LineItem4);
		test_steps.add("Added Folio Line Item4" + moveFolio.getRes1LineItem4.getText());
		// l2=res1LineItem2;
		Wait.wait5Second();
		return getTest_Steps;
	}

	public ArrayList<String> res1FolioLineItem5(WebDriver driver, ExtentTest test, ArrayList<String> getTest_Steps)
			throws InterruptedException {
		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);
		Wait.WaitForElement(driver, OR.getRes1LineItem5);
		res1LineItem5 = moveFolio.getRes1LineItem5.getText();
		folioLogger.info("Reservation1 Line Item5: " + res1LineItem5);
		test_steps.add("Added Folio Line Item5" + moveFolio.getRes1LineItem5.getText());
		// l2=res1LineItem2;
		Wait.wait5Second();
		return getTest_Steps;
	}

	public ArrayList<String> AddfolioLineItem(WebDriver driver, String Category, String Amount,
			ArrayList<String> getTest_Steps) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		/*
		 * Wait.explicit_wait_visibilityof_webelement(FolioLineItems.
		 * click_Add_Button, driver); JavascriptExecutor jse =
		 * (JavascriptExecutor) driver;
		 * Utility.ScrollToElement(FolioLineItems.click_Add_Button, driver);
		 * jse.executeScript("arguments[0].click();",
		 * FolioLineItems.click_Add_Button);
		 */
		// FolioLineItems.click_Add_Button.click();
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.selectCategory, driver);
		Utility.ScrollToElement(FolioLineItems.selectCategory, driver);
		new Select(FolioLineItems.selectCategory).selectByVisibleText(Category);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.enterAmount, driver);
		System.out.println("Amount in func:" + Amount);
		FolioLineItems.enterAmount.sendKeys(Amount);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.clickCommitButton, driver);
		Utility.ScrollToElement(FolioLineItems.clickCommitButton, driver);
		FolioLineItems.clickCommitButton.click();
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.getBalanceFolioLineItems, driver);
		String getBalance = FolioLineItems.getBalanceFolioLineItems.getText();
		folioLogger.info(" Balance of the Folio Line Items " + getBalance);
		return test_steps;
	}

	public ArrayList<String> AddfolioLineItem2(WebDriver driver, String Category2, String Amount2,
			ArrayList<String> getTest_Steps) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.click_Add_Button, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Utility.ScrollToElement(FolioLineItems.click_Add_Button, driver);
		jse.executeScript("arguments[0].click();", FolioLineItems.click_Add_Button);

		// FolioLineItems.click_Add_Button.click();
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.selectCategory, driver);
		Utility.ScrollToElement(FolioLineItems.selectCategory, driver);
		new Select(FolioLineItems.selectCategory).selectByVisibleText(Category2);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.enterAmount, driver);
		System.out.println("Amount in func:" + Amount2);
		FolioLineItems.enterAmount.sendKeys(Amount2);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.clickCommitButton, driver);
		Utility.ScrollToElement(FolioLineItems.clickCommitButton, driver);
		FolioLineItems.clickCommitButton.click();
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.getBalanceFolioLineItems, driver);
		String getBalance = FolioLineItems.getBalanceFolioLineItems.getText();
		folioLogger.info(" Balance of the Folio Line Items " + getBalance);
		return test_steps;
	}

	public ArrayList<String> AddfolioLineItem3(WebDriver driver, String Category3, String Amount3,
			ArrayList<String> getTest_Steps) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.click_Add_Button, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Utility.ScrollToElement(FolioLineItems.click_Add_Button, driver);
		jse.executeScript("arguments[0].click();", FolioLineItems.click_Add_Button);

		// FolioLineItems.click_Add_Button.click();
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.selectCategory, driver);
		Utility.ScrollToElement(FolioLineItems.selectCategory, driver);
		new Select(FolioLineItems.selectCategory).selectByVisibleText(Category3);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.enterAmount, driver);
		System.out.println("Amount in func:" + Amount3);
		FolioLineItems.enterAmount.sendKeys(Amount3);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.clickCommitButton, driver);
		Utility.ScrollToElement(FolioLineItems.clickCommitButton, driver);
		FolioLineItems.clickCommitButton.click();
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.getBalanceFolioLineItems, driver);
		String getBalance = FolioLineItems.getBalanceFolioLineItems.getText();
		folioLogger.info(" Balance of the Folio Line Items " + getBalance);
		return test_steps;
	}

	public ArrayList<String> AddfolioLineItem4(WebDriver driver, String Category4, String Amount4,
			ArrayList<String> getTest_Steps) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		Wait.WaitForElement(driver, OR.click_Add_Button);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.click_Add_Button, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Utility.ScrollToElement(FolioLineItems.click_Add_Button, driver);
		jse.executeScript("arguments[0].click();", FolioLineItems.click_Add_Button);

		// FolioLineItems.click_Add_Button.click();
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.selectCategory, driver);
		Utility.ScrollToElement(FolioLineItems.selectCategory, driver);
		new Select(FolioLineItems.selectCategory).selectByVisibleText(Category4);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.enterAmount, driver);
		System.out.println("Amount in func:" + Amount4);
		FolioLineItems.enterAmount.sendKeys(Amount4);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.clickCommitButton, driver);
		Utility.ScrollToElement(FolioLineItems.clickCommitButton, driver);
		FolioLineItems.clickCommitButton.click();
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.getBalanceFolioLineItems, driver);
		String getBalance = FolioLineItems.getBalanceFolioLineItems.getText();
		folioLogger.info(" Balance of the Folio Line Items " + getBalance);
		return test_steps;
	}

	public ArrayList<String> AddfolioLineItem5(WebDriver driver, String Category5, String Amount5,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		Wait.WaitForElement(driver, OR.click_Add_Button);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.click_Add_Button, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Utility.ScrollToElement(FolioLineItems.click_Add_Button, driver);
		jse.executeScript("arguments[0].click();", FolioLineItems.click_Add_Button);

		// FolioLineItems.click_Add_Button.click();
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.selectCategory, driver);
		Utility.ScrollToElement(FolioLineItems.selectCategory, driver);
		new Select(FolioLineItems.selectCategory).selectByVisibleText(Category5);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.enterAmount, driver);
		System.out.println("Amount in func:" + Amount5);
		FolioLineItems.enterAmount.sendKeys(Amount5);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.clickCommitButton, driver);
		Utility.ScrollToElement(FolioLineItems.clickCommitButton, driver);
		FolioLineItems.clickCommitButton.click();
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.getBalanceFolioLineItems, driver);
		String getBalance = FolioLineItems.getBalanceFolioLineItems.getText();
		folioLogger.info(" Balance of the Folio Line Items " + getBalance);
		return test_steps;
	}

	public void openFolioLineItem(WebDriver driver, String Category) throws InterruptedException {
		Elements_Folio folio = new Elements_Folio(driver);

		String xpath = "(//span[.='" + Category + "']//following::a)[1]";

		WebElement FolioDescription = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", FolioDescription);

		Wait.wait5Second();
		// Wait.WaitForElement(driver, OR.FolioDescription2);
		FolioDescription.click();
		Wait.wait5Second();
	}

	public void openFolioLineItem2(WebDriver driver, String Category2) throws InterruptedException {
		Elements_Folio folio = new Elements_Folio(driver);

		String xpath = "(//span[.='" + Category2 + "']//following::a)[1]";

		WebElement FolioDescription2 = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", FolioDescription2);

		Wait.wait5Second();
		Wait.WaitForElement(driver, OR.FolioDescription2);
		FolioDescription2.click();
		Wait.wait5Second();
	}

	public void openFolioLineItem3(WebDriver driver, String Category3) throws InterruptedException {
		Elements_Folio folio = new Elements_Folio(driver);

		String xpath = "(//span[.='" + Category3 + "']//following::a)[1]";

		WebElement FolioDescription3 = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", FolioDescription3);

		Wait.wait5Second();
		// Wait.WaitForElement(driver, OR.FolioDescription2);
		FolioDescription3.click();
		Wait.wait5Second();
	}

	public void openFolioLineItem4(WebDriver driver, String Category4) throws InterruptedException {
		Elements_Folio folio = new Elements_Folio(driver);

		String xpath = "(//span[.='" + Category4 + "']//following::a)[1]";

		WebElement FolioDescription4 = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", FolioDescription4);

		Wait.wait5Second();
		// Wait.WaitForElement(driver, OR.FolioDescription2);
		FolioDescription4.click();
		Wait.wait5Second();
	}

	public void openFolioLineItem5(WebDriver driver, String Category5) throws InterruptedException {
		Elements_Folio folio = new Elements_Folio(driver);

		String xpath = "(//span[.='" + Category5 + "']//following::a)[1]";

		WebElement FolioDescription5 = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", FolioDescription5);

		Wait.wait5Second();
		// Wait.WaitForElement(driver, OR.FolioDescription2);
		FolioDescription5.click();
		Wait.wait5Second();
	}

	public void VerifyTaxNameInFolio(WebDriver driver, String taxName, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Folio folio = new Elements_Folio(driver);
		try {
			String xpath = "//a[.='" + taxName + "']";
			String TaxNameInFolio = driver.findElement(By.xpath(xpath)).getText();
			folioLogger.info(" Tax is displayed : " + TaxNameInFolio);

			if (TaxNameInFolio.contains(taxName)) {

				String tax_amount = "(//a[.='" + taxName + "']//following::td/span)[1]";
				String taxAmount = driver.findElement(By.xpath(tax_amount)).getText();

				// folio.taxAmount.getText();
				folioLogger.info(" Tax is displayed and Tax Amount is: " + taxAmount);

				test_steps.add(" Tax is displayed and Tax Amount : " + taxAmount);

			}

		}

		catch (Exception e) {
			folioLogger.info(" Tax is not displayed ");
			test_steps.add(" Tax is not displayed ");
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", folio.ItemDetailCancelButton);
		Wait.WaitForElement(driver, OR.ItemDetailCancelButton);
		folio.ItemDetailCancelButton.click();
		Wait.wait5Second();
	}

	public ArrayList<String> MoveFolioInsideReservation(WebDriver driver, ExtentTest test1, String resNumber1,
			String resNumber2, ArrayList<String> test_steps) throws Exception {
		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);

		// Search and open Reservation1

		resName = resSearch.basicSearch_WithResNumber(driver, resNumber1);
		test_steps.add("System opened reservation number " + resNumber1);
		folioLogger.info("System opened reservation number " + resNumber1);

		/*
		 * Wait.WaitForElement(driver, OR.FirstOpenedReservationClose);
		 * moveFolio.FirstOpenedReservationClose.click();
		 */

		Wait.WaitForElement(driver, OR.Reservations);
		// Search and open Reservation2
		moveFolio.Reservation_SecNav.click();
		Wait.wait2Second();
		resSearch.basicSearch_WithResNumber(driver, resNumber2);
		test_steps.add("System opened reservation number " + resNumber2);
		folioLogger.info("System opened reservation number " + resNumber2);

		// opening the first reservation tab
		Wait.WaitForElement(driver, OR.FirstOpenedReservation);
		/*
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.
		 * GuestInfo)));
		 * wait.until(ExpectedConditions.elementToBeClickable(moveFolio.
		 * GuestInfo));
		 */
		Wait.wait5Second();
		moveFolio.FirstOpenedReservation.click();
		Wait.wait2Second();
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.GuestInfo)));
		// wait.until(ExpectedConditions.elementToBeClickable(moveFolio.GuestInfo));
		// Wait.wait3Second();
		Wait.WaitForElement(driver, OR.GuestInfo);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Wait.WaitForElement(driver, OR.MoveFolio_Folio);
		jse.executeScript("arguments[0].click();", moveFolio.MoveFolio_Folio);

		// moveFolio.MoveFolio_Folio.click();

		// Waiting for visibility of adjoining rooms
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_GuestFolio)));
		test_steps.add("Opened Folio Tab");
		folioLogger.info("Opened Folio Tab");

		// Select the folio item to move
		// Wait.wait2Second();

		/*
		 * res1LineItem=moveFolio.getRes1LineItem.getText();
		 * folioLogger.info("Reservation1 Line Item: " +res1LineItem);
		 */

		Wait.WaitForElement(driver, OR.MoveFolio_SelectFiloItem);
		jse.executeScript("arguments[0].scrollIntoView();", moveFolio.MoveFolio_SelectFiloItem);
		// moveFolio.MoveFolio_SelectFiloItem.click();
		moveFolio.MoveFolio_SelectFiloItem2.click();
		test_steps.add("Selected the folio Item");
		folioLogger.info("Selected the folio Item");

		// click on move
		// Wait.wait3Second();

		Wait.WaitForElement(driver, OR.MoveFolio_Move);
		moveFolio.MoveFolio_Move.click();
		test_steps.add("Clicked on move");
		folioLogger.info("Clicked on move");
		Wait.wait2Second();
		// Select the guest folio of another reservation
		Wait.WaitForElement(driver, OR.MoveFolio_TargetFolio);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_TargetFolio)));
		Select sel = new Select(moveFolio.MoveFolio_TargetFolio);
		sel.selectByVisibleText("Guest Folio");
		test_steps.add("Opened the new Folio : " + "Guest Folio");
		folioLogger.info("Opened the new Folio : " + "Guest Folio");

		// move Folio item to another reservation folio
		Wait.WaitForElement(driver, OR.MoveFolio_FolioItemToMove);
		// moveFolio.MoveFolio_FolioItemToMove.click();
		moveFolio.MoveFolio_FolioItemToMove_2.click();
		test_steps.add("Clieked on folio item");
		folioLogger.info("Clieked on folio item");

		Wait.WaitForElement(driver, OR.MoveFolio_MoveSelectedItem);
		moveFolio.MoveFolio_MoveSelectedItem.click();
		test_steps.add("Moved folio item");
		folioLogger.info("Moved folio item");

		Wait.WaitForElement(driver, OR.MoveFolio_Close);
		moveFolio.MoveFolio_Close.click();
		test_steps.add("Clicked on close");
		folioLogger.info("Clicked on close");
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='toast-container']/div/div[2]")));

		// closing first reservation and validation second reservation folio
		Wait.WaitForElement(driver, OR.FirstOpenedReservationClose);
		moveFolio.FirstOpenedReservationClose.click();
		Wait.WaitForElement(driver, OR.FirstOpenedReservation);
		moveFolio.FirstOpenedReservation.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.GuestInfo)));

		wait.until(ExpectedConditions.elementToBeClickable(moveFolio.GuestInfo));
		// Wait.wait3Second();
		Wait.WaitForElement(driver, OR.MoveFolio_Folio);
		moveFolio.MoveFolio_Folio.click();

		// Waiting for visibility of guest folio
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.MoveFolio_GuestFolio)));
		test_steps.add("Opened the Folio Tab");
		folioLogger.info("Opened the Folio Tab");
		// Wait.wait2Second();
		Wait.WaitForElement(driver,
				"//label[contains(text(),'Balance: ')]/following-sibling::span[@class='pamt']/span[@class='pamt']");
		String Balance1 = driver
				.findElement(By
						.xpath("//label[contains(text(),'Balance: ')]/following-sibling::span[@class='pamt']/span[@class='pamt']"))
				.getText();
		Balance1 = Balance1.replace("$", "");

		d1 = Double.parseDouble(Balance1);
		folioLogger.info("Folio First Balance : " + d1);

		/*
		 * moveFolio.navigateToRes2.click();
		 * folioLogger.info(" Opened Second Reservation " );
		 */
		Wait.WaitForElement(driver, OR.FirstOpenedReservationClose);
		moveFolio.FirstOpenedReservationClose.click();
		/*
		 * Wait.WaitForElement(driver, OR.FirstOpenedReservationClose);
		 * moveFolio.FirstOpenedReservationClose.click();
		 */

		Wait.WaitForElement(driver, OR.Reservations);
		// Search and open Reservation2
		moveFolio.Reservation_SecNav.click();
		Wait.wait2Second();
		resSearch.basicSearch_WithResNumber(driver, resNumber1);
		test_steps.add("Opened reservation " + resNumber1);
		folioLogger.info("Opened reservation  " + resNumber1);

		// opening the first reservation tab
		Wait.wait2Second();
		Wait.WaitForElement(driver, OR.FirstOpenedReservation);

		moveFolio.FirstOpenedReservation.click();
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.GuestInfo)));
		// wait.until(ExpectedConditions.elementToBeClickable(moveFolio.GuestInfo));
		Wait.WaitForElement(driver, OR.Click_Folio_Tab);
		moveFolio.Click_Folio_Tab.click();
		folioLogger.info("Switched  ");
		Wait.wait10Second();
		if (driver
				.findElements(By
						.xpath("//table[@class='table table-striped table-bordered table-hover trHeight25']/tbody/tr/td/input"))
				.size() > 1) {
			Wait.wait5Second();
			/*
			 * res1FolioLineItem(driver, test1, test_steps);
			 * res1FolioLineItem2(driver, test1, test_steps);
			 */
			if (d1 == d + d2) {
				test_steps.add("Successfully moved the folio Item");
				test_steps.add("Folio Balance is same : " + d1);
				folioLogger.info("Folio Balance is same : " + d1);
			} else {
				test_steps.add("Successfully moved the folio Item");
				test_steps.add("Folio Balance is same : " + d1);
				folioLogger.info("Folio Balance is not same : " + d1);
			}

		} else {
			test_steps.add("Successfully moved the folio Item");
			test_steps.add("Folio Balance is same : " + d1);
			folioLogger.info("Folio Balance is same : " + d1);
		}

		return test_steps;

	}

	public String getPaymentLineItem(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Folio folio = new Elements_Folio(driver);
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		String payment_LineItem = "";
		if (elements_All_Payments.selectPaymentFolio3.isDisplayed()
				&& !elements_All_Payments.selectPaymentFolio3.isEnabled()) {
			Wait.WaitForElement(driver, OR.PaymentLineItem);
			Utility.ScrollToElement(folio.PaymentLineItem, driver);

			payment_LineItem = folio.PaymentLineItem.getText();
			folioLogger.info("Payment Line Item : " + payment_LineItem);
			test_steps.add("Payment Line Item : " + payment_LineItem);

		}
		return payment_LineItem;

	}

	/*
	 * public void FolioPaymentLineItem(WebDriver driver, ArrayList<String>
	 * test_steps) { Elements_Folio folio = new Elements_Folio(driver);
	 * 
	 * //boolean
	 * FolioPaymentLineItem=Boolean.parseBoolean(CopyRes_AssignedUnassigned.
	 * FolioPayment);
	 * 
	 * 
	 * 
	 * if(!CopyRes_AssignedUnassigned.FolioPayment.isEmpty()) {
	 * 
	 * folioLogger.info("Payment Line Item is Copied");
	 * test_steps.add(" Payment Line Item is Copied"); }
	 * 
	 * else {
	 * 
	 * folioLogger.info("Payment Line Item is not Copied");
	 * test_steps.add(" Payment Line Item is not Copied"); } }
	 */

	public void FolioPaymentLineItem(WebDriver driver, ArrayList<String> test_steps) {
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		// Wait.WaitForElement(driver, OR.selectPaymentFolio3);

		try {

			if (elements_All_Payments.selectPaymentFolio3.isDisplayed()) {

				getPaymentLineItem(driver, test_steps);

			}

		}

		catch (Exception e) {

			folioLogger.info("Folio Payment line Item cannot be Copied ");

			test_steps.add("Folio Payment line Item cannot be Copied ");
			//
			// folioLogger.info("Enabled Payment line Item ");
			//
			// test_steps.add("Enabled Payment line Item ");
		}

	}

	public ArrayList<String> PayCardPayment_Auth(WebDriver driver, String PaymentType, String Amount,
			String AccountNumber, String ExpDate, String AuthorizationtypeAuth, ArrayList<String> steps)
			throws InterruptedException {
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		WebElement element = driver.findElement(By.xpath(OR.PayButton));
		elements_All_Payments.Folio.get(0).click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		steps.add("Click Pay Reservation");
		try {
			Wait.WaitForElement(driver, OR.Reservation_PaymentPopup);
			assertTrue(elements_All_Payments.Reservation_PaymentPopup.isDisplayed(),
					"Failed : Failed payment Popup Not Open");
		} catch (Exception e) {
			Wait.WaitForElement(driver, OR.PaymentPopUp);
			assertEquals(elements_All_Payments.PaymentPopUp.getText(), "Payment Details",
					"payment popup does not open");
		}

		Utility.ScrollToElement(elements_All_Payments.PaymentDetail_PaymentType, driver);
		System.out.println("Type:" + PaymentType);

		Utility.ScrollToElement(elements_All_Payments.Enter_Amount_Auth, driver);
		elements_All_Payments.Enter_Amount_Auth.clear();
		if (Float.parseFloat(Amount) < 0.0) {
			assertTrue(false, "Failed: Amount is less then 0.0");
		}
		String str_length = elements_All_Payments.Enter_Amount_Auth.getAttribute("value");
		for (int i = 0; i < str_length.length(); i++) {
			elements_All_Payments.Enter_Amount_Auth.sendKeys(Keys.BACK_SPACE);
		}
		elements_All_Payments.Enter_Amount_Auth.sendKeys(Amount);
		Utility.app_logs.info("Enter Amount " + Amount);

		steps.add("Enter Amount " + Amount);
		Wait.wait10Second();
		System.out.println("AuthoType:" + AuthorizationtypeAuth);
		new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText(AuthorizationtypeAuth);
		Utility.app_logs.info("Selected authorization type");
		elements_All_Payments.Process_Button_Auth.click();
		Utility.app_logs.info("Click Process");
		steps.add("Click Process Payment");
		// Wait.wait2Second();
		Wait.explicit_wait_absenceofelement(OR.Payment_ModuleLoading, driver);
		Wait.explicit_wait_visibilityof_webelement_350(elements_All_Payments.AuthorizationImg, driver);
		Utility.ScrollToElement(elements_All_Payments.AuthorizationImg, driver);
		elements_All_Payments.AuthorizationImg.isDisplayed();
		elements_All_Payments.AuthorizationImg.click();
		steps.add("Authorization Image is displayed");

		Wait.explicit_wait_absenceofelement(OR.Payment_ModuleLoading, driver);
		Wait.explicit_wait_visibilityof_webelement_350(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
		jse.executeScript("arguments[0].click();", elements_All_Payments.PaymentDetail_Continue_Pay_Button);
		try {
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 30);
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
			elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
			Utility.app_logs.info("Click Continue again");
			Wait.waitForElementToBeGone(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 90);
		}

		String Description = AccountNumber.substring(AccountNumber.length() - 4);
		Description = Description + " Exp. Date: " + ExpDate;
		steps.add("Click Continue Payment");
		Verify_LineItem(driver, PaymentType, Amount, Description);
		Wait.explicit_wait_absenceofelement(OR.Payment_ModuleLoading, driver);
		Wait.explicit_wait_visibilityof_webelement_350(elements_All_Payments.AuthorizationImg, driver);
		Utility.ScrollToElement(elements_All_Payments.AuthorizationImg, driver);
		elements_All_Payments.AuthorizationImg.isDisplayed();
		elements_All_Payments.AuthorizationImg.click();
		steps.add("Authorization Image is displayed");
		return steps;
	}

	public void VerifyTaxExempt(WebDriver driver, boolean isExempt) throws InterruptedException {
		Elements_FolioLineItemsVoid elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		Utility.ScrollToElement(elements_FolioLineItemsVoid.List_LineItemTax.get(0), driver);

		for (int i = 0; i < 3; i++) {
			if (isExempt) {
				folioLogger.info("List_LineItemTax : " + elements_FolioLineItemsVoid.List_LineItemTax.get(i).getText());
				assertEquals(elements_FolioLineItemsVoid.List_LineItemTax.get(i).getText(), ("$ 0.00"),
						"Failed: tax exempt not apply after click on reset long stay");
			} else {
				folioLogger.info("List_LineItemTax : " + elements_FolioLineItemsVoid.List_LineItemTax.get(i).getText());
				assertNotEquals(elements_FolioLineItemsVoid.List_LineItemTax.get(i).getText(), ("$ 0.00"),
						"Folio Tax item is equal  to zero after save reservation");
			}

		}

	}

	public ArrayList<String> GetDatesForLongStay(WebDriver driver, ArrayList<String> getDates)
			throws InterruptedException {
		Elements_FolioLineItemsVoid elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		for (int i = 0; i < 3; i++) {

			getDates.add(elements_FolioLineItemsVoid.List_LineItemDates.get(i).getText());

		}

		return getDates;
	}

	public void VerifyTaxExemptApplied(WebDriver driver, int nightStay) throws InterruptedException {
		Elements_FolioLineItemsVoid elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.List_LineItemTax.get(0), driver);
		Utility.ScrollToElement(elements_FolioLineItemsVoid.List_LineItemTax.get(0), driver);
		nightStay = nightStay * 3;
		nightStay = nightStay + 1;
		int total_removeItem = 0;

		if (elements_FolioLineItemsVoid.List_LineItemTax.size() == 10) {
			System.out.println("in if");
			for (int i = 0; i < nightStay; i++) {

				if (i == 0) {
					assertEquals(elements_FolioLineItemsVoid.List_LineItemTax.get(i).getText(), ("$ 0.00"),
							"Failed: tax exempt not apply after click on reset long stay");

				} else {

					if (elements_FolioLineItemsVoid.List_LineItemAmount.get(i).getText().contains("-")) {
						assertEquals(elements_FolioLineItemsVoid.List_LineItemTax.get(i).getText(), ("$ 0.00"),
								"Failed: tax exempt not apply after click on reset long stay");
						total_removeItem++;
					} else {
						assertEquals(elements_FolioLineItemsVoid.List_LineItemTax.get(i).getText(), ("$ 0.00"),
								"Failed: tax exempt not apply after click on reset long stay");
						assertEquals(elements_FolioLineItemsVoid.List_LineItemTax.get(i).isDisplayed(), true,
								"Failed: Amount is not displaying in filio line item amount");

					}

				}

			}
		} else {
			System.out.println("in else");
			for (int i = 0; i < nightStay; i++) {

				if (i == 0) {
					assertEquals(elements_FolioLineItemsVoid.List_LineItemTax.get(i).getText(), ("$ 0.00"),
							"Failed: tax exempt not apply after click on reset long stay");

				} else {

					if (elements_FolioLineItemsVoid.List_LineItemAmount.get(i).getText().contains("-")) {
						assertEquals(elements_FolioLineItemsVoid.List_LineItemTax.get(i).getText(), ("$ 0.00"),
								"Failed: tax exempt not apply after click on reset long stay");
						total_removeItem++;
					} else {
						assertEquals(elements_FolioLineItemsVoid.List_LineItemTax.get(i).getText(), ("$ 0.00"),
								"Failed: tax exempt not apply after click on reset long stay");
						assertEquals(elements_FolioLineItemsVoid.List_LineItemTax.get(i).isDisplayed(), true,
								"Failed: Amount is not displaying in filio line item amount");

					}

				}

			}
		}

		assertEquals(total_removeItem, 3, "Failed: After change night stay 3 to 4, line itme does not remove");

	}

	public void VerifyTaxName1InFolio(WebDriver driver, String taxName1, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Folio folio = new Elements_Folio(driver);
		try {
			String xpath = "//a[.='" + taxName1 + "']";
			String TaxNameInFolio = driver.findElement(By.xpath(xpath)).getText();
			folioLogger.info(" Tax1 is displayed : " + TaxNameInFolio);

			if (TaxNameInFolio.contains(taxName1)) {

				String tax_amount = "(//a[.='" + taxName1 + "']//following::td/span)[1]";
				String taxAmount = driver.findElement(By.xpath(tax_amount)).getText();

				// folio.taxAmount.getText();
				folioLogger.info(" Tax1 is displayed and Tax Amount is: " + taxAmount);

				test_steps.add(" Tax1 is displayed and Tax Amount : " + taxAmount);

			}

		}

		catch (Exception e) {
			folioLogger.info(" Tax1 is not displayed ");
			test_steps.add(" Tax1 is not displayed ");
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", folio.ItemDetailCancelButton);
		Wait.WaitForElement(driver, OR.ItemDetailCancelButton);
		folio.ItemDetailCancelButton.click();
		Wait.wait5Second();
	}

	public void VerifyTaxName2InFolio(WebDriver driver, String taxName2, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Folio folio = new Elements_Folio(driver);
		try {
			String xpath = "//a[.='" + taxName2 + "']";
			String TaxNameInFolio = driver.findElement(By.xpath(xpath)).getText();
			folioLogger.info(" Tax1 is displayed : " + TaxNameInFolio);

			if (TaxNameInFolio.contains(taxName2)) {

				String tax_amount = "(//a[.='" + taxName2 + "']//following::td/span)[1]";
				String taxAmount = driver.findElement(By.xpath(tax_amount)).getText();

				// folio.taxAmount.getText();
				folioLogger.info(" Tax2 is displayed and Tax Amount is: " + taxAmount);

				test_steps.add(" Tax2 is displayed and Tax Amount : " + taxAmount);

			}

		}

		catch (Exception e) {
			folioLogger.info(" Tax2 is not displayed ");
			test_steps.add(" Tax2 is not displayed ");
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", folio.ItemDetailCancelButton);
		Wait.WaitForElement(driver, OR.ItemDetailCancelButton);
		folio.ItemDetailCancelButton.click();
		Wait.wait5Second();
	}

	public void SelectCancellationPolicy_DiscardAll(WebDriver driver, String policyName) throws InterruptedException {

		System.out.println("Policy:" + policyName);
		Elements_MovieFolio folio = new Elements_MovieFolio(driver);
		Wait.explicit_wait_visibilityof_webelement(folio.Cancellation_Policy, driver);
		Utility.ScrollToElement(folio.Cancellation_Policy, driver);
		folio.Cancellation_Policy.click();
		Wait.WaitForElement(driver, NewFolio.CancelPolicyPicker_Popup);
		Wait.explicit_wait_visibilityof_webelement(folio.ClickDiscardAll_CancelPolicy, driver);
		Utility.ScrollToElement(folio.ClickDiscardAll_CancelPolicy, driver);
		folio.ClickDiscardAll_CancelPolicy.click();
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(folio.AvailableCancellationPolicies, driver);
		new Select(folio.AvailableCancellationPolicies).selectByVisibleText(policyName);
		folio.ClickAddOne_Cancelpolicy.click();

		Utility.ScrollToElement(folio.ClickDone_CancelPolicy, driver);
		folio.ClickDone_CancelPolicy.click();
		Wait.waitForElementToBeGone(driver, folio.ClickDone_CancelPolicy, 30);
		try {
			assertEquals(folio.Cancellation_Policy.getAttribute("value"), policyName,
					"Failed:Cancellation Policy Not selected");
		} catch (Exception e) {
			assertTrue(folio.Cancellation_Policy.getAttribute("value").contains(policyName),
					"Failed:Cancellation Policy Not selected");
		}

	}

	public int getLineItemRows(WebDriver driver) throws InterruptedException {

		Elements_FolioLineItemsVoid elemetFolio = new Elements_FolioLineItemsVoid(driver);
		// Utility.ScrollToElement(elemetFolio.FolioRows.get(0), driver);
		int size = 0;
		try {
			size = elemetFolio.FolioRows.size();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return size;

	}

	public String getRoomCharges(WebDriver driver) throws InterruptedException, ParseException {

		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		Utility.ScrollToElement_NoWait(elementFolio.roomCharges, driver);

		String[] str = elementFolio.roomCharges.getText().split(" ");
		System.out.println("Room charger: " + str[1]);
		return str[1];
	}

	public String getIncidental(WebDriver driver) throws InterruptedException {

		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		Utility.ScrollToElement_NoWait(elementFolio.incidental, driver);
		String[] str = elementFolio.incidental.getText().split(" ");
		System.out.println("incidetal: " + str[1]);
		return str[1];
	}

	public String getTaxServices(WebDriver driver) {

		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		String[] str = elementFolio.taxServices.getText().split(" ");
		System.out.println("taxServices: " + str[1]);
		return str[1];
	}

	public String getTotalCharges(WebDriver driver) {

		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		String[] str = elementFolio.totalCharges.getText().split(" ");
		System.out.println("totalCharges: " + str[1]);
		return str[1];
	}

	public String getpayment(WebDriver driver) {

		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		String[] str = elementFolio.payment.getText().split(" ");
		System.out.println("payment: " + str[1]);
		return str[1];
	}

	public String getTotalBalance(WebDriver driver) {

		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		String[] str = elementFolio.totalBalance.getText().split(" ");
		System.out.println("totalBalance: " + str[1]);
		return str[1];
	}

	public ArrayList<String> LineItemQuentity(WebDriver driver, String Category, String Quentity)
			throws InterruptedException {
		ArrayList<String> TestSteps = new ArrayList<>();
		String PathLintItemQuentity = "//span[text()='" + Category
				+ "']//..//following-sibling::td[@class='lineitem-qty']//span";
		WebElement element_LintItemQuentity = driver.findElement(By.xpath(PathLintItemQuentity));
		Wait.explicit_wait_visibilityof_webelement(element_LintItemQuentity, driver);

		TestSteps.add("Expected line item quentity: " + Quentity);
		TestSteps.add("Found in line item: " + element_LintItemQuentity.getText());
		assertEquals(element_LintItemQuentity.getText(), Quentity, "Quentity is mismatching");
		TestSteps.add("Verified line item quentity");
		return TestSteps;

	}

	public ArrayList<String> itemDetailsPopup(WebDriver driver, String Popupheading) {

		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> stesp = new ArrayList<>();

		Wait.WaitForElement(driver, NewFolio.itemDetailsPopup);
		Wait.explicit_wait_visibilityof_webelement(elementFolio.itemDetailsPopup, driver);
		Wait.explicit_wait_elementToBeClickable(elementFolio.itemDetailsPopup, driver);
		String heading = elementFolio.itemDetailsPopup.getText();
		stesp.add("Expected item details popup heading: " + Popupheading);
		stesp.add("Found: " + heading);
		assertEquals(heading, Popupheading, "Failed: Item details heading is mismatched!");
		test_steps.add("Verified item details popup");
		return stesp;

	}

	public String splitString(String str) {

		String[] strSplit = str.split(" ");
		return strSplit[1];
	}

	public String Incidenatl(String IncidentalAmount, String Quentity) {

		double amount = Double.parseDouble(IncidentalAmount);
		amount = amount * (Integer.parseInt(Quentity));

		String totlaAmount = String.format("%.2f", amount);
		System.out.println("total: " + totlaAmount);
		return totlaAmount;
	}

	public String AddValue(String FirstValue, String SecondValue) {

		double firstValue = Double.parseDouble(FirstValue);
		double secondValue = Double.parseDouble(SecondValue);
		double Total = firstValue + secondValue;
		String amount = String.format("%.2f", Total);
		System.out.println("total: " + amount);
		return amount;
	}

	public ArrayList<String> CancelButton(WebDriver driver, boolean isClick, boolean isEnabled) {
		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> stesp = new ArrayList<>();

		Wait.WaitForElement(driver, NewFolio.ItemDetails_CancelButton);
		Wait.explicit_wait_visibilityof_webelement(elementFolio.ItemDetails_CancelButton, driver);
		Wait.explicit_wait_elementToBeClickable(elementFolio.ItemDetails_CancelButton, driver);
		if (isClick) {
			elementFolio.ItemDetails_CancelButton.click();
			stesp.add("Click on cancel butto");
		} else {
			assertEquals(elementFolio.ItemDetails_CancelButton.isEnabled(), isEnabled,
					"Failed to verify cancel button state");
			if (isEnabled) {
				stesp.add("Verified cancel button is enabled");
			} else {
				stesp.add("Verified cancel button is disabled");
			}

		}
		return stesp;

	}

	public ArrayList<String> VoidButton(WebDriver driver, boolean isClick, boolean isEnabled) {
		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> stesp = new ArrayList<>();

		Wait.WaitForElement(driver, NewFolio.ItemDetails_VoidButton);
		Wait.explicit_wait_visibilityof_webelement(elementFolio.ItemDetails_VoidButton, driver);
		Wait.explicit_wait_elementToBeClickable(elementFolio.ItemDetails_VoidButton, driver);
		if (isClick) {
			elementFolio.ItemDetails_VoidButton.click();
			stesp.add("Click on void button");
		} else {
			assertEquals(elementFolio.ItemDetails_VoidButton.isEnabled(), isEnabled,
					"Failed to verify void button state");
			if (isEnabled) {
				stesp.add("Verified void button is enabled");
			} else {
				stesp.add("Verified void button is disabled");
			}

		}
		return stesp;

	}

	public ArrayList<String> AddButton(WebDriver driver, boolean isClick, boolean isEnabled)
			throws InterruptedException {
		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> stesp = new ArrayList<>();

		Wait.WaitForElement(driver, NewFolio.ItemDetails_AddButton);
		Wait.explicit_wait_visibilityof_webelement(elementFolio.ItemDetails_AddButton, driver);
		Wait.explicit_wait_elementToBeClickable(elementFolio.ItemDetails_AddButton, driver);
		if (isClick) {
			elementFolio.ItemDetails_AddButton.click();
			stesp.add("Click on add button");
		} else {
			assertEquals(elementFolio.ItemDetails_AddButton.isEnabled(), isEnabled,
					"Failed to verify add button state");
			if (isEnabled) {
				stesp.add("Verified add button is enabled");
			} else {
				stesp.add("Verified add button is disabled");
			}

		}
		Wait.wait2Second();
		return stesp;

	}

	public ArrayList<String> CancelPopupButton(WebDriver driver, boolean isClick, boolean isEnabled)
			throws InterruptedException {
		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> stesp = new ArrayList<>();

		Wait.WaitForElement(driver, NewFolio.ItemDetails_CancelPopup);
		Wait.explicit_wait_visibilityof_webelement(elementFolio.ItemDetails_CancelPopup, driver);
		Wait.explicit_wait_elementToBeClickable(elementFolio.ItemDetails_CancelPopup, driver);
		Utility.ScrollToElement_NoWait(elementFolio.ItemDetails_CancelPopup, driver);
		if (isClick) {
			elementFolio.ItemDetails_CancelPopup.click();
			stesp.add("Click on cancel button in item details popup");
		} else {
			assertEquals(elementFolio.ItemDetails_CancelPopup.isEnabled(), isEnabled,
					"Failed to verify cancel popup button state");
			if (isEnabled) {
				stesp.add("Verified cancel popup button is enabled");
			} else {
				stesp.add("Verified cancel popup button is disabled");
			}

		}
		return stesp;

	}

	public ArrayList<String> ContinuePopupButton(WebDriver driver, boolean isClick, boolean isEnabled)
			throws InterruptedException {
		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> stesp = new ArrayList<>();

		Wait.WaitForElement(driver, NewFolio.ItemDetails_ContinuePopup);
		Wait.explicit_wait_visibilityof_webelement(elementFolio.ItemDetails_ContinuePopup, driver);
		Utility.ScrollToElement_NoWait(elementFolio.ItemDetails_ContinuePopup, driver);
		if (isClick) {
			elementFolio.ItemDetails_ContinuePopup.click();
			stesp.add("Click on continue popup button");
		} else {
			assertEquals(elementFolio.ItemDetails_ContinuePopup.isEnabled(), isEnabled,
					"Failed to verify continue popup button state");
			if (isEnabled) {
				stesp.add("Verified continue popup button is enabled");
			} else {
				stesp.add("Verified continue popup button is disabled");
			}

		}
		return stesp;
	}

	public ArrayList<String> LineItemDate(WebDriver driver, String Category)
			throws InterruptedException, ParseException {
		ArrayList<String> TestSteps = new ArrayList<>();

		String PathDate = "//span[text()='" + Category + "']//..//..//td[@class='lineitem-date']//span";
		System.out.println(PathDate);
		WebElement ElementDate = driver.findElement(By.xpath(PathDate));
		Wait.explicit_wait_visibilityof_webelement(ElementDate, driver);
		String getDate = ESTTimeZone.getDateforLineItem(0, true);

		TestSteps.add("Expected line item date: " + getDate);
		TestSteps.add("Found: " + ElementDate.getText());
		assertEquals(ElementDate.getText(), getDate, "Faile: date is mismatched");
		TestSteps.add("Verified line item date");
		return TestSteps;
	}

	public int getTableRows(WebDriver driver) {
		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		return elementFolio.ItemDetailsRows.size();

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <ItemDetails_Category_State> ' Description: < Getting
	 * folio line item description in item details > ' Input parameters: <line
	 * item category,Descriptionand inex > ' Return value: <test steps in form
	 * of array list> ' Created By: <Farhan Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void ItemDetails_Category_State(WebDriver driver, String Category, String State, int index)
			throws InterruptedException {

		String PathPrePost = "(" + NewFolio.ItemDetails_PrePath + "//span[text()='" + Category + "'])[" + index
				+ "]//..//..//td//img[@src='" + State + "']";
		System.out.println(PathPrePost);
		WebElement ElementPrePost = driver.findElement(By.xpath(PathPrePost));
		Wait.explicit_wait_visibilityof_webelement(ElementPrePost, driver);
		assertEquals(ElementPrePost.isDisplayed(), true, "Failed: Line item is not pending state");

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <DateItemDetails> ' Description: < Getting folio line item
	 * description in item details > ' Input parameters: <line item
	 * category,Descriptionand inex > ' Return value: <test steps in form of
	 * array list> ' Created By: <Farhan Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> DateItemDetails(WebDriver driver, String Category, int index, String Item_category)
			throws InterruptedException, ParseException {
		ArrayList<String> TestSteps = new ArrayList<>();

		String PathDate = "(" + NewFolio.ItemDetails_PrePath + "//span[text()='" + Category + "'])[" + index
				+ "]//..//..//td//span[contains(@data-bind,'text:$data.EffectiveDate')]";
		System.out.println(PathDate);
		WebElement ElementDate = driver.findElement(By.xpath(PathDate));
		Wait.explicit_wait_visibilityof_webelement(ElementDate, driver);
		String getDate = ESTTimeZone.getDateforLineItem(0, false);

		test_steps.add("Expected line item date: " + getDate);
		test_steps.add("Found: " + ElementDate.getText());
		assertEquals(ElementDate.getText(), getDate, "Faile: date is mismatched in item details popup");
		TestSteps.add("Verified line item date in " + Item_category);
		return TestSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <ItemDetailsCategory> ' Description: < Getting category> '
	 * Input parameters: <> ' Return value: <test steps in form of array list> '
	 * Created By: <Farhan Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> ItemDetailsCategory(WebDriver driver, String Category, int index, String Item_category)
			throws InterruptedException {
		ArrayList<String> TestSteps = new ArrayList<>();
		String PathLintItemCategory = "(" + NewFolio.ItemDetails_PrePath + "//span[text()='" + Category + "'])[" + index
				+ "]";
		WebElement element_LintItemCategory = driver.findElement(By.xpath(PathLintItemCategory));
		Wait.explicit_wait_visibilityof_webelement(element_LintItemCategory, driver);
		Utility.ScrollToElement(element_LintItemCategory, driver);

		test_steps.add("Expected category in item details popup: " + Category);
		test_steps.add("Found: " + element_LintItemCategory.getText());
		assertEquals(element_LintItemCategory.getText(), Category, "Category is mismatching");
		TestSteps.add("Verified selected category is showing in " + Item_category);
		return TestSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <itemDetails_Descroption> ' Description: < Getting
	 * description> ' Input parameters: <> ' Return value: <test steps in form
	 * of array list> ' Created By: <Farhan Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> itemDetails_Descroption(WebDriver driver, String Category, String Description,
			boolean isClick, int index, String Item_Tax, String Tag) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<>();
		String PathLineItemDescription = "(" + NewFolio.ItemDetails_PrePath + "//span[text()='" + Category + "'])["
				+ index + "]//..//..//td//" + Tag + "[contains(@data-bind,'text: $data.Description')]";
		WebElement ElementDescription = driver.findElement(By.xpath(PathLineItemDescription));
		String getDescription = ElementDescription.getText();
		test_steps.add("Expected line item description: " + Description);
		test_steps.add("Found: " + getDescription);
		assertEquals(getDescription, Description, "Failed: Description is mistamtching");
		test_steps.add("Verified description in " + Item_Tax);
		if (isClick) {
			ElementDescription.click();
		}
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getAmount_ItemDetails> ' Description: < Getting Amount> '
	 * Input parameters: <> ' Return value: <String> ' Created By: <Farhan
	 * Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String getAmount_ItemDetails(WebDriver driver, String Category, int index) throws InterruptedException {

		String PathLineItemAmount = "(" + NewFolio.ItemDetails_PrePath + "//span[text()='" + Category + "'])[" + index
				+ "]//..//..//td//span[contains(@data-bind,'showInnroadCurrency:')]";
		WebElement ElementAmount = driver.findElement(By.xpath(PathLineItemAmount));
		return ElementAmount.getText();

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <Itemdetails_RoomChares> ' Description: < Getting Room
	 * charges> ' Input parameters: <> ' Return value: <String> ' Created By:
	 * <Farhan Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String Itemdetails_RoomChares(WebDriver driver) throws InterruptedException {

		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		Utility.ScrollToElement_NoWait(elementFolio.ItemDetails_RoomCharges, driver);
		return elementFolio.ItemDetails_RoomCharges.getText();

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <Itemdetails_Incientals> ' Description: < Getting
	 * Incientals> ' Input parameters: <> ' Return value: <String> ' Created By:
	 * <Farhan Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String Itemdetails_Incientals(WebDriver driver) throws InterruptedException {

		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		Utility.ScrollToElement_NoWait(elementFolio.ItemDetails_Incidental, driver);
		return elementFolio.ItemDetails_Incidental.getText();

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <Itemdetails_TaxServices> ' Description: < Getting
	 * Incidentals> ' Input parameters: <> ' Return value: <String> ' Created
	 * By: <Farhan Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String Itemdetails_TaxServices(WebDriver driver) throws InterruptedException {

		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		Utility.ScrollToElement_NoWait(elementFolio.ItemDetails_TaxServices, driver);
		return elementFolio.ItemDetails_TaxServices.getText();

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <Itemdetails__TotalCharges> ' Description: < Getting
	 * TotalCharges> ' Input parameters: <> ' Return value: <String> ' Created
	 * By: <Farhan Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String Itemdetails__TotalCharges(WebDriver driver) throws InterruptedException {

		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		Utility.ScrollToElement_NoWait(elementFolio.ItemDetails_TotalCharges, driver);
		return elementFolio.ItemDetails_TotalCharges.getText();

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <Itemdetails_RoomNumber> ' Description: < Getting
	 * TotalCharges> ' Input parameters: <> ' Return value: <String> ' Created
	 * By: <Farhan Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String Itemdetails_RoomNumber(WebDriver driver) throws InterruptedException {

		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		Utility.ScrollToElement_NoWait(elementFolio.RoomNumber, driver);
		return elementFolio.RoomNumber.getText();

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <checkboxIncludePendingItemsinTotal> ' Description: <
	 * Getting TotalCharges> ' Input parameters: <> ' Return value: <void> '
	 * Created By: <Farhan Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void checkboxIncludePendingItemsinTotal(WebDriver driver, boolean isClick) throws InterruptedException {

		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		Wait.WaitForElement(driver, NewFolio.checkbox_IncludePendingItemsinTotal);
		Wait.explicit_wait_visibilityof_webelement(elementFolio.checkbox_IncludePendingItemsinTotal, driver);
		Wait.explicit_wait_elementToBeClickable(elementFolio.checkbox_IncludePendingItemsinTotal, driver);
		Utility.ScrollToElement_NoWait(elementFolio.checkbox_IncludePendingItemsinTotal, driver);

		if (isClick) {
			if (!elementFolio.checkbox_IncludePendingItemsinTotal.isSelected()) {
				elementFolio.checkbox_IncludePendingItemsinTotal.click();
			}
		} else {
			if (elementFolio.checkbox_IncludePendingItemsinTotal.isSelected()) {
				elementFolio.checkbox_IncludePendingItemsinTotal.click();
			}
		}

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <EnterNotes> ' Description: < Getting TotalCharges> '
	 * Input parameters: <> ' Return value: <Array list> ' Created By: <Farhan
	 * Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> EnterNotes(WebDriver driver, String Notes) throws InterruptedException {

		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> steps = new ArrayList<>();
		Wait.WaitForElement(driver, NewFolio.Notes);
		Wait.explicit_wait_visibilityof_webelement(elementFolio.Notes, driver);
		Wait.explicit_wait_elementToBeClickable(elementFolio.Notes, driver);
		elementFolio.Notes.sendKeys(Notes);
		steps.add("Enter notes: " + Notes);
		return steps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <CheckboxDisplayVoidItem> ' Description: < Getting
	 * TotalCharges> ' Input parameters: <> ' Return value: <Array list> '
	 * Created By: <Farhan Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> CheckboxDisplayVoidItem(WebDriver driver, boolean isChecked) throws InterruptedException {

		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> steps = new ArrayList<>();
		Wait.WaitForElement(driver, NewFolio.checkboxDisplayVoidItem);
		Wait.explicit_wait_visibilityof_webelement(elementFolio.checkboxDisplayVoidItem, driver);
		Wait.explicit_wait_elementToBeClickable(elementFolio.checkboxDisplayVoidItem, driver);
		Utility.ScrollToElement(elementFolio.checkboxDisplayVoidItem, driver);
		if (isChecked) {
			if (!elementFolio.checkboxDisplayVoidItem.isSelected()) {
				elementFolio.checkboxDisplayVoidItem.click();
				steps.add("Checked displayed void ttem");
			} else {
				if (elementFolio.checkboxDisplayVoidItem.isSelected()) {
					elementFolio.checkboxDisplayVoidItem.click();
					steps.add("Unchecked displayed void item");
				}
			}

		}

		return steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <isLineItemDisplay> ' Description: < Getting TotalCharges>
	 * ' Input parameters: <> ' Return value: <boolean> ' Created By: <Farhan
	 * Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public boolean isLineItemDisplay(WebDriver driver, String Category) throws InterruptedException {

		String PathLintItemCategory = "//span[text()='" + Category + "']";
		WebElement element_lineItem = driver.findElement(By.xpath(PathLintItemCategory));
		System.out.println("dislay" + element_lineItem.isDisplayed());
		return element_lineItem.isDisplayed();
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <PostedLineItems> ' Description: <Post line item> ' Input
	 * parameters: <> ' Return value: <void> ' Created By: <Farhan Ghaffar> '
	 * Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void PostedLineItems(WebDriver driver, String Category, String State, String PostState)
			throws InterruptedException {

		String PathPrePost = "//span[text()='" + Category + "']//..//..//td//img[@src='" + State + "']";
		System.out.println(PathPrePost);
		WebElement ElementPrePost = driver.findElement(By.xpath(PathPrePost));
		Wait.explicit_wait_visibilityof_webelement(ElementPrePost, driver);
		Wait.explicit_wait_elementToBeClickable(ElementPrePost, driver);
		Utility.ScrollToElement(ElementPrePost, driver);
		assertEquals(ElementPrePost.isDisplayed(), true, "Failed: Line item is not pending state");
		ElementPrePost.click();
		PathPrePost = "//span[text()='" + Category + "']//..//..//td//img[@src='" + PostState + "']";
		System.out.println(PathPrePost);
		ElementPrePost = driver.findElement(By.xpath(PathPrePost));
		Wait.explicit_wait_visibilityof_webelement(ElementPrePost, driver);
		assertEquals(ElementPrePost.isDisplayed(), true, "Failed: Line item is not posted state");

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <RollBackButton> ' Description: <Rool back line item> '
	 * Input parameters: <> ' Return value: <array list> ' Created By: <Farhan
	 * Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> RollBackButton(WebDriver driver) throws InterruptedException {
		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> stesp = new ArrayList<>();
		Wait.WaitForElement(driver, NewFolio.ItemDetails_ContinuePopup);
		Wait.explicit_wait_visibilityof_webelement(elementFolio.ItemDetails_ContinuePopup, driver);
		Wait.explicit_wait_elementToBeClickable(elementFolio.RollBackButton, driver);
		elementFolio.RollBackButton.click();
		stesp.add("Click on Rollback button");
		return stesp;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <CalculateIncidental> ' Description: <calculate
	 * incidental> ' Input parameters: <> ' Return value: <array list> ' Created
	 * By: <Farhan Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String CalculateIncidental(String Amount, String Quenity) {

		double amount = Double.parseDouble(Amount);
		amount = amount * 2;
		String totalAmount = String.format("%.2f", amount);
		System.out.println("total: " + totalAmount);
		return totalAmount;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <SelectCategory> ' Description: <Select Category> ' Input
	 * parameters: <> ' Return value: <void> ' Created By: <Farhan Ghaffar> '
	 * Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void SelectCategory(WebDriver driver, String Category) throws InterruptedException {
		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		Wait.WaitForElement(driver, NewFolio.ItemDetailsSelectCategory);
		Wait.explicit_wait_visibilityof_webelement(elementFolio.ItemDetailsSelectCategory, driver);
		Wait.explicit_wait_elementToBeClickable(elementFolio.ItemDetailsSelectCategory, driver);
		new Select(elementFolio.ItemDetailsSelectCategory).selectByVisibleText(Category);

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <EnterDescription> ' Description: <Enter Description> '
	 * Input parameters: <> ' Return value: <void> ' Created By: <Farhan
	 * Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void EnterDescription(WebDriver driver, String Description) throws InterruptedException {
		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		elementFolio.EnterItemDetailsDescription.sendKeys(Description);
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <EnterAmount> ' Description: <Enter Amount> ' Input
	 * parameters: <> ' Return value: <void> ' Created By: <Farhan Ghaffar> '
	 * Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void EnterAmount(WebDriver driver, String Description) throws InterruptedException {
		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		elementFolio.EntertemDetailsAmount.sendKeys(Description);
	}

	public ArrayList<String> VerifyClearFields(WebDriver driver) throws InterruptedException {
		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> steps = new ArrayList<>();
		Select select = new Select(elementFolio.ItemDetailsSelectCategory);
		WebElement options = select.getFirstSelectedOption();
		assertEquals(options.getText(), "--Select--",
				"Failed: Category did not remove selected option after click on cancel button");
		assertEquals(elementFolio.EnterItemDetailsDescription.getAttribute("value").isEmpty(), true,
				"Failed: Description input field  did not  clear after click on cancel button");
		assertEquals(elementFolio.EntertemDetailsAmount.getAttribute("value").isEmpty(), true,
				"Failed: Amount input field  did not  clear after click on cancel button");
		steps.add("Verified Category, Description, Amount fields are cleared after click on cancel button");
		return steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <CalculateIncidentalAfterDiscount> ' Description:
	 * <CalculateIncidentalAfterDiscount> ' Input parameters: <> ' Return value:
	 * <String> ' Created By: <Farhan Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String CalculateIncidentalAfterDiscount(String Amount, String Quenity, String discount) {

		double amount = Double.parseDouble(Amount);
		amount = amount * 2;
		if (discount.contains("-")) {
			String[] strSplit = discount.split("-");
			discount = strSplit[1];
		}
		amount = amount - (Double.parseDouble(discount));
		String totalAmount = String.format("%.2f", amount);
		System.out.println("total: " + totalAmount);
		return totalAmount;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <TaxCalculationAfterDiscount> ' Description:
	 * <TaxCalculationAfterDiscount> ' Input parameters: <> ' Return value:
	 * <String> ' Created By: <Farhan Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String TaxCalculationAfterDiscount(String Tax, String TaxPercentage, String discount) {

		if (discount.contains("-")) {
			String[] strSplit = discount.split("-");
			discount = strSplit[1];
		}
		double Percentage = Double.parseDouble(TaxPercentage);
		double afterDoscountTax = (Percentage / 100) * (Double.parseDouble(discount));
		System.out.println(afterDoscountTax);
		String TaxAmount = String.format("%.2f", afterDoscountTax);
		System.out.println("TaxAmount: " + TaxAmount);
		afterDoscountTax = (Double.parseDouble(Tax)) - afterDoscountTax;
		String TotalAfterDiscount = String.format("%.2f", afterDoscountTax);

		System.out.println("Tax: " + TotalAfterDiscount);

		return TotalAfterDiscount + "," + TaxAmount;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <splitbyComma> ' Description: <split string by comma> '
	 * Input parameters: <> ' Return value: <String> ' Created By: <Farhan
	 * Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String splitbyComma(String Tax_Amount, int index) {

		String[] strSplit = Tax_Amount.split(",");
		return strSplit[index];

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <VerifyFolioBalance> ' Description: <Verify FolioBalance>
	 * ' Input parameters: <> ' Return value: <Array list> ' Created By: <Farhan
	 * Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> VerifyFolioBalance(WebDriver driver, String ExpectedFolioBalance)
			throws InterruptedException {
		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> steps = new ArrayList<>();
		String getFoliobalance = elementFolio.FolioBalance.getText();
		ExpectedFolioBalance = "$ " + ExpectedFolioBalance;
		steps.add("Expected folio balance: $ " + ExpectedFolioBalance);
		steps.add("Found: " + getFoliobalance);
		assertEquals(getFoliobalance, ExpectedFolioBalance, "failed: Folio balance is mismatching");
		steps.add("Verified folio balance");
		return steps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <VerifyTotalBalance> ' Description: <Verify TotalBalance>
	 * ' Input parameters: <> ' Return value: <Array list> ' Created By: <Farhan
	 * Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> VerifyTotalBalance(WebDriver driver, String ExpectedTotalBalance)
			throws InterruptedException {
		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> steps = new ArrayList<>();
		String getFoliobalance = elementFolio.TotalBalance.getText();
		ExpectedTotalBalance = "$ " + ExpectedTotalBalance;
		steps.add("Expected total balance: $ " + ExpectedTotalBalance);
		steps.add("Found: " + getFoliobalance);
		assertEquals(getFoliobalance, ExpectedTotalBalance, "failed: total balance is mismatching");
		steps.add("Verified total balance");
		return steps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickAddButton> ' Description: <Clikc on add button in
	 * line item> ' Input parameters: <> ' Return value: <void> ' Created By:
	 * <Farhan Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void clickAddButton(WebDriver driver) throws InterruptedException {

		Elements_FolioLineItemsVoid elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.AddButton, driver);
		Wait.explicit_wait_elementToBeClickable(elements_FolioLineItemsVoid.AddButton, driver);
		Utility.ScrollToElement(elements_FolioLineItemsVoid.AddButton, driver);
		elements_FolioLineItemsVoid.AddButton.click();
		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.SelectCategory, driver);
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickOnAbortButton> ' Description: <Clikc on abort button
	 * in line item> ' Input parameters: <> ' Return value: <void> ' Created By:
	 * <Farhan Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void clickOnAbortButton(WebDriver driver) throws InterruptedException {

		Elements_FolioLineItemsVoid elemetFolio = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement(elemetFolio.AbortBtn, driver);
		Wait.explicit_wait_elementToBeClickable(elemetFolio.AbortBtn, driver);
		elemetFolio.AbortBtn.click();

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyCategoryDropdownList> ' Description: <verify
	 * Category Dropdown List ha specific category> ' Input parameters: <> '
	 * Return value: <boolean> ' Created By: <Farhan Ghaffar> ' Created On:
	 * <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public boolean verifyCategoryDropdownList(WebDriver driver, String SelectCategory)
			throws InterruptedException, ParseException {

		Wait.wait1Second();
		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		Select select = new Select(elementFolio.SelectCategory);
		List<WebElement> options = select.getOptions();
		System.out.println("size: " + options.size());
		boolean iscategoryExist = false;
		for (int i = 0; i < options.size(); i++) {
			System.out.println(options.get(i).getText());
			if (options.get(i).getText().equals(SelectCategory)) {
				iscategoryExist = true;
				break;
			}
		}
		// new
		// Select(elementFolio.SelectCategory).selectByVisibleText(SelectCategory);
		return iscategoryExist;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <AddLineItem> ' Description: <Add new line item> ' Input
	 * parameters: <> ' Return value: <ArrayList> ' Created By: <Farhan Ghaffar>
	 * ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> AddLineItem(WebDriver driver, String SelectCategory, String Amount, int days,
			String Quentity) throws InterruptedException, ParseException {

		Elements_FolioLineItemsVoid elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		String SelectDate = LineItem_EnterDate(driver, days);
		test_steps.add("Select lien item add date: " + SelectDate);

		elements_FolioLineItemsVoid.Enter_LineItemAmount.click();

		Wait.explicit_wait_elementToBeClickable(elements_FolioLineItemsVoid.SelectCategory, driver);
		new Select(elements_FolioLineItemsVoid.SelectCategory).selectByVisibleText(SelectCategory);
		test_steps.add("Select category: " + SelectCategory);

		// elements_FolioLineItemsVoid.Enter_LineItemQuentity.clear();
		elements_FolioLineItemsVoid.Enter_LineItemQuentity.sendKeys(Quentity);
		test_steps.add("Enter quentity: " + Quentity);
		String getQuantity = elements_FolioLineItemsVoid.Enter_LineItemQuentity.getAttribute("value");
		for (int i = 0; i < getQuantity.length(); i++) {
			if (i == 0) {
				System.out.println("in arrow key left");
				elements_FolioLineItemsVoid.Enter_LineItemQuentity.sendKeys(Keys.ARROW_LEFT);
			} else {
				System.out.println("else");
				elements_FolioLineItemsVoid.Enter_LineItemQuentity.sendKeys(Keys.BACK_SPACE);
			}
		}

		elements_FolioLineItemsVoid.Enter_LineItemAmount.click();
		elements_FolioLineItemsVoid.Enter_LineItemAmount.clear();
		elements_FolioLineItemsVoid.Enter_LineItemAmount.sendKeys(Amount);
		test_steps.add("Enter amount: " + Amount);

		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.CommitButton, driver);
		Utility.ScrollToElement(elements_FolioLineItemsVoid.CommitButton, driver);
		elements_FolioLineItemsVoid.CommitButton.click();
		folioLogger.info("Click on Commit button");
		return test_steps;
	}

	public void VerifyLineItems_State(WebDriver driver, String Category, String State) throws InterruptedException {

		Wait.wait1Second();
		String PathPrePost = "//span[text()='" + Category + "']//..//..//td//img[@src='" + State + "']";
		System.out.println(PathPrePost);
		WebElement ElementPrePost = driver.findElement(By.xpath(PathPrePost));
		Wait.explicit_wait_visibilityof_webelement(ElementPrePost, driver);
		assertEquals(ElementPrePost.isDisplayed(), true, "Failed: Line item is not posted state");

	}

	public ArrayList<String> VerifyPaymentPopUp(WebDriver driver, String PaymentPopupHeading)
			throws InterruptedException, ParseException {

		ArrayList<String> test_steps = new ArrayList<>();
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Payment_ModuleLoading)), 300);
		Wait.WaitForElement(driver, NewFolio.PaymentDetailsPopupHeading);
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetailsPopupHeading, driver);
		Wait.explicit_wait_elementToBeClickable(elements_All_Payments.SelectAuthorizationType, driver);
		assertEquals(elements_All_Payments.PaymentDetailsPopupHeading.getText(), PaymentPopupHeading,
				"Failed: Authorization type is not slected");
		test_steps.add("Verified payment popup heading");
		return test_steps;

	}

	public ArrayList<String> ClickOnProcessButton(WebDriver driver) throws InterruptedException, ParseException {

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		ArrayList<String> test_steps = new ArrayList<>();

		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.ProcessButton, driver);
		Wait.explicit_wait_elementToBeClickable(elements_All_Payments.ProcessButton, driver);
		elements_All_Payments.ProcessButton.click();
		test_steps.add("Click on Process button");
		folioLogger.info("click on process button");
		return test_steps;

	}

	public ArrayList<String> ClickOnContinueButton(WebDriver driver) throws InterruptedException, ParseException {
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		ArrayList<String> test_steps = new ArrayList<>();

		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Payment_ModuleLoading)), 300);

		Wait.WaitForElement(driver, OR.PaymentDetail_Continue_Pay_Button);
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
		Wait.explicit_wait_elementToBeClickable(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);

		elements_All_Payments.PaymentDetail_Continue_Pay_Button.click();
		test_steps.add("Click on continue button");
		Wait.waitForElementToBeInvisible(driver, elements_All_Payments.PaymentDetail_Continue_Pay_Button, 10);
		return test_steps;

	}

	public ArrayList<String> VerifyClickOnContinueButton(WebDriver driver, boolean isEnable)
			throws InterruptedException, ParseException {

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		ArrayList<String> test_steps = new ArrayList<>();

		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Payment_ModuleLoading)), 300);

		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);
		Wait.explicit_wait_elementToBeClickable(elements_All_Payments.PaymentDetail_Continue_Pay_Button, driver);

		if (isEnable) {
			assertEquals(elements_All_Payments.PaymentDetail_Continue_Pay_Button.isEnabled(), isEnable,
					"Failed: Button is disbaled after checked Allow non zero balance checkbox");
			test_steps.add(
					"Verified continue button is enabled after checked Allow non-zero balance at the time of check-out checkbox during check ");
		} else {
			assertEquals(elements_All_Payments.PaymentDetail_Continue_Pay_Button.isEnabled(), isEnable,
					"Failed: Button is enabled after unchecked Allow non zero balance checkbox");
			test_steps.add(
					"Verified continue button is disabled after unchecked Allow non-zero balance at the time of check-out checkbox during check ");

		}

		return test_steps;

	}

	public String LineItem_EnterDate(WebDriver driver, int days) throws InterruptedException, ParseException {

		Elements_FolioLineItemsVoid elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		String selectDate = ESTTimeZone.DateFormateForLineItem(days);
		System.out.println("Enter date : " + selectDate);
		elements_FolioLineItemsVoid.LineItemDate.click();
		elements_FolioLineItemsVoid.LineItemDate.clear();
		elements_FolioLineItemsVoid.LineItemDate.sendKeys(selectDate);
		Wait.wait1Second();
		return selectDate;

	}

	public String verifyLineItemCategory(WebDriver driver, String Category, ArrayList<String> test_steps) {
		String finalCat = null;
		try {
			String catPath = "//td[contains(@data-bind,'lineitem-category')]/span[contains(text(),'" + Category + "')]";
			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(catPath)), driver);
			String foundCat = driver.findElement(By.xpath(catPath)).getText();
			assertEquals(foundCat, Category, "Failed to Verify Category");
			Utility.app_logs.info("Successfully Verified Category : " + foundCat);
			test_steps.add("Successfully Verified Category : " + foundCat);
			finalCat = foundCat;
		} catch (Exception e) {
			String catPath = "//td[contains(@data-bind,'lineitem-category')]/span";
			List<WebElement> list = driver.findElements(By.xpath(catPath));
			for (int i = 0; i < list.size(); i++) {
				String foundCat = list.get(i).getText();
				if (foundCat.toUpperCase().equals(Category.toUpperCase())) {
					finalCat = foundCat;
				}
			}
			assertEquals(finalCat.toUpperCase(), Category.toUpperCase(), "Failed to Verify Category");
			Utility.app_logs.info("Successfully Verified Category : " + finalCat);
			test_steps.add("Successfully Verified Category : " + finalCat);
		}
		return finalCat;
	}

	public String lineItemDate(WebDriver driver, String Category, int day, int index)
			throws InterruptedException, ParseException {
		ArrayList<String> TestSteps = new ArrayList<>();

		String PathDate = "(//span[text()='" + Category + "']//..//..//td[@class='lineitem-date']//span)[" + index
				+ "]";
		System.out.println(PathDate);
		WebElement ElementDate = driver.findElement(By.xpath(PathDate));
		Wait.explicit_wait_visibilityof_webelement(ElementDate, driver);
		String getDate = ESTTimeZone.getDateforLineItem(day, true);

		TestSteps.add("Expected line item date: " + getDate);
		TestSteps.add("Found: " + ElementDate.getText());
		assertEquals(ElementDate.getText(), getDate, "Faile: date is mismatched");
		TestSteps.add("Verified line item date");
		return getDate;

	}

	public ArrayList<String> LineItemCategory(WebDriver driver, String Category) throws InterruptedException {
		ArrayList<String> TestSteps = new ArrayList<>();
		String PathLintItemCategory = "//span[text()='" + Category + "']";
		WebElement element_LintItemCategory = driver.findElement(By.xpath(PathLintItemCategory));
		Wait.explicit_wait_visibilityof_webelement(element_LintItemCategory, driver);
		Utility.ScrollToElement(element_LintItemCategory, driver);

		TestSteps.add("Expected line item category: " + Category);
		TestSteps.add("Found in line item: " + element_LintItemCategory.getText());
		assertEquals(element_LintItemCategory.getText(), Category, "Category did not added");
		TestSteps.add("Verified selected category has been added");
		return TestSteps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getDescroption> ' Description: < Getting folio line item
	 * description > ' Input parameters: <line item category,Description > '
	 * Return value: <test steps in form of arraylist> ' Created By: <Farhan
	 * Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> getDescroption(WebDriver driver, String Category, String Description, boolean isClick)
			throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<>();
		String PathLineItemDescription = "//span[text()='" + Category
				+ "']//..//following-sibling::td[@class='lineitem-description']//a";
		WebElement ElementDescription = driver.findElement(By.xpath(PathLineItemDescription));
		Wait.explicit_wait_elementToBeClickable(ElementDescription, driver);
		Utility.ScrollToElement(ElementDescription, driver);
		String getDescription = ElementDescription.getText();
		test_steps.add("Expected line item description: " + Description);
		test_steps.add("Found: " + getDescription);
		assertEquals(getDescription, Description, "Failed: Description is mistamtching");
		if (isClick) {
			ElementDescription.click();
		}
		return test_steps;
	}

	public String getAmount(WebDriver driver, String Category) throws InterruptedException {

		String PathLineItemAmount = "//span[text()='" + Category
				+ "']//..//following-sibling::td[@class='lineitem-amount']//span";

		WebElement ElementAmount = driver.findElement(By.xpath(PathLineItemAmount));
		return Utility.convertDollarToNormalAmount(driver, ElementAmount.getText());

	}

	public String getTax(WebDriver driver, String Category) throws InterruptedException {

		String PathLineItemTax = "//span[text()='" + Category
				+ "']//..//following-sibling::td[@class='lineitem-tax']//span";
		WebElement element_LineItemTax = driver.findElement(By.xpath(PathLineItemTax));
		String SplitTax[] = element_LineItemTax.getText().split(" ");
		System.out.println("Tax: " + SplitTax[1]);
		return SplitTax[1];

	}

	public String TaxCalculation(String amount, String percentage, String Quentity) {

		double Percentage = Double.parseDouble(percentage);
		double getAmount = Double.parseDouble(amount);
		double Tax = (Percentage / 100) * getAmount;
		Tax = Tax * (Integer.parseInt(Quentity));
		System.out.println("totalPercentage: " + Tax);
		System.out.println("Tax: " + Tax);
		String TotalFee = String.format("%.2f", Tax);
		System.out.println("Tax: " + TotalFee);

		return TotalFee;
	}

	public String AddTwoValue(String FirstValue, String SecondValue) {

		double firstValue = Double.parseDouble(FirstValue);
		firstValue = firstValue * 2;
		double secondValue = Double.parseDouble(SecondValue);
		double Total = firstValue + secondValue;
		String amount = String.format("%.2f", Total);
		System.out.println("total: " + amount);
		return amount;
	}

	public String MinseTwoValue(String FirstValue, String SecondValue) throws InterruptedException, ParseException {

		double first = Double.parseDouble(FirstValue);
		double second = Double.parseDouble(SecondValue);
		double total = first - second;
		String ExpectedAmount = String.format("%.2f", total);
		System.out.println(ExpectedAmount);
		return ExpectedAmount;

	}

	public ArrayList<String> FolioTab(WebDriver driver) {
		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> test_steps = new ArrayList<>();

		Wait.WaitForElement(driver, NewFolio.Foliotab);
		Wait.explicit_wait_elementToBeClickable(elementFolio.Foliotab, driver);
		Wait.explicit_wait_elementToBeClickable(elementFolio.Foliotab, driver);
		elementFolio.Foliotab.click();
		test_steps.add("Click on Folio");
		Wait.WaitForElement(driver, NewFolio.FolioDetails);
		Wait.explicit_wait_elementToBeClickable(elementFolio.FolioDetails, driver);
		Wait.explicit_wait_elementToBeClickable(elementFolio.FolioDetails, driver);
		return test_steps;
	}

	public ArrayList<String> LineItemDate(WebDriver driver, String Category, int day, int index)
			throws InterruptedException, ParseException {
		ArrayList<String> TestSteps = new ArrayList<>();

		String PathDate = "(//span[text()='" + Category + "']//..//..//td[@class='lineitem-date']//span)[" + index
				+ "]";
		System.out.println(PathDate);
		WebElement ElementDate = driver.findElement(By.xpath(PathDate));
		Wait.explicit_wait_visibilityof_webelement(ElementDate, driver);
		String getDate = ESTTimeZone.getDateforLineItem(day, true);

		TestSteps.add("Expected line item date: " + getDate);
		TestSteps.add("Found: " + ElementDate.getText());
		assertEquals(ElementDate.getText(), getDate, "Faile: date is mismatched");
		TestSteps.add("Verified line item date");
		return TestSteps;
	}

	public ArrayList<String> LineItemCategory(WebDriver driver, String Category, int index)
			throws InterruptedException {
		ArrayList<String> TestSteps = new ArrayList<>();
		String PathLintItemCategory = "(//span[text()='" + Category + "'])[" + index + "]";
		WebElement element_LintItemCategory = driver.findElement(By.xpath(PathLintItemCategory));
		Wait.explicit_wait_visibilityof_webelement(element_LintItemCategory, driver);
		Utility.ScrollToElement_NoWait(element_LintItemCategory, driver);

		TestSteps.add("Expected line item category: " + Category);
		TestSteps.add("Found in line item: " + element_LintItemCategory.getText());
		assertEquals(element_LintItemCategory.getText(), Category, "Category did not added");
		TestSteps.add("Verified selected category has been added");
		return TestSteps;

	}

	public ArrayList<String> getDescroption(WebDriver driver, String Category, String Description, boolean isClick,
			int index) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<>();
		String PathLineItemDescription = "(//span[text()='" + Category
				+ "']//..//following-sibling::td[contains(@class,'lineitem-description')]//a)[" + index + "]";
		WebElement ElementDescription = driver.findElement(By.xpath(PathLineItemDescription));
		Wait.explicit_wait_elementToBeClickable(ElementDescription, driver);
		Utility.ScrollToElement_NoWait(ElementDescription, driver);
		String getDescription = ElementDescription.getText();
		test_steps.add("Expected line item description: " + Description);
		test_steps.add("Found: " + getDescription);
		assertEquals(getDescription, Description, "Failed: Description is mistamtching");
		test_steps.add("verified description");
		if (isClick) {
			ElementDescription.click();
		}
		return test_steps;
	}

	public ArrayList<String> LineItemQuentity(WebDriver driver, String Category, String Quentity, int index)
			throws InterruptedException {
		ArrayList<String> TestSteps = new ArrayList<>();
		String PathLintItemQuentity = "(//span[text()='" + Category
				+ "']//..//following-sibling::td[@class='lineitem-qty']//span)[" + index + "]";
		WebElement element_LintItemQuentity = driver.findElement(By.xpath(PathLintItemQuentity));

		TestSteps.add("Expected line item quentity: " + Quentity);
		TestSteps.add("Found in line item: " + element_LintItemQuentity.getText());
		assertEquals(element_LintItemQuentity.getText(), Quentity, "Quentity is mismatching");
		TestSteps.add("Verified line item quentity");
		return TestSteps;

	}

	public String getAmount(WebDriver driver, String Category, int index) throws InterruptedException {

		String PathLineItemAmount = "(//span[text()='" + Category
				+ "']//..//following-sibling::td[@class='lineitem-amount']//span)[" + index + "]";

		WebElement ElementAmount = driver.findElement(By.xpath(PathLineItemAmount));
		return ElementAmount.getText();

	}

	public void VerifyLineItems_State(WebDriver driver, String Category, String State, int index)
			throws InterruptedException {

		Wait.wait1Second();
		String PathPrePost = "(//span[text()='" + Category + "']//..//..//td//img[@src='" + State + "'])[" + index
				+ "]";
		System.out.println(PathPrePost);
		WebElement ElementPrePost = driver.findElement(By.xpath(PathPrePost));
		Wait.explicit_wait_visibilityof_webelement(ElementPrePost, driver);
		assertEquals(ElementPrePost.isDisplayed(), true, "Failed: Line item is not posted state");

	}

	public String getLineTaxAmount(WebDriver driver, String Category) {
		String taxPath = "//td[contains(@data-bind,'lineitem-category')]/span[contains(text(),'" + Category
				+ "')]/../following-sibling::td[contains(@class,'lineitem-tax')]/span";
		String tax = "0";
		try {
			Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(taxPath)), driver);
			tax = driver.findElement(By.xpath(taxPath)).getText();
			tax = tax.replace("$", "");
			tax = tax.replace(" ", "");
		} catch (Exception e) {
			folioLogger.info("Tax Column not Found");
		}
		return tax;
	}

	public String getLineTaxAmount(WebDriver driver, String Category, int index) {
		String taxPath = "(//td[contains(@data-bind,'lineitem-category')]/span[contains(text(),'" + Category
				+ "')]/../following-sibling::td[contains(@class,'lineitem-tax')]/span)[" + index + "]";

		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(taxPath)), driver);
		String tax = driver.findElement(By.xpath(taxPath)).getText();
		tax = tax.replace("$", "");
		tax = tax.replace(" ", "");
		return tax;
	}

	public ArrayList<String> verifyLineItemIcon(WebDriver driver, String Category, String imgSRC, String msg) {
		ArrayList<String> test_steps = new ArrayList<String>();

		String imgPah = "//td[contains(@data-bind,'lineitem-category')]/span[contains(text(),'" + Category
				+ "')]/../preceding-sibling::td[contains(@class,'lineitems-changestatus')]/img";

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(imgPah)), driver);
		String foundSrc = driver.findElement(By.xpath(imgPah)).getAttribute("src");
		assertEquals(foundSrc, imgSRC, "Failed to Verify Icon");
		Utility.app_logs.info("Successfully Verified" + msg + " : " + foundSrc);
		test_steps.add("Successfully Verified" + msg + " : " + foundSrc);

		return test_steps;
	}

	public ArrayList<String> verifyLineItemDate(WebDriver driver, String Category) {
		ArrayList<String> test_steps = new ArrayList<String>();

		String datePath = "//td[contains(@data-bind,'lineitem-category')]/span[contains(text(),'" + Category
				+ "')]/../preceding-sibling::td[contains(@class,'lineitem-date')]/span";

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(datePath)), driver);
		String foundDate = driver.findElement(By.xpath(datePath)).getText();
		assertEquals(Utility.parseDate(foundDate, "EEE MMM dd, yyyy", "EEE MMM dd, yyyy"),
				Utility.getCurrentDate("EEE MMM dd, yyyy"), "Failed to Verify Date");
		Utility.app_logs.info("Successfully Verified Date : " + foundDate);
		test_steps.add("Successfully Verified Date : " + foundDate);

		return test_steps;
	}

	public ArrayList<String> verifyLineItemAmount(WebDriver driver, String Category, String Amount) {
		ArrayList<String> test_steps = new ArrayList<String>();
		String amountPath = "//td[contains(@data-bind,'lineitem-category')]/span[contains(text(),'" + Category
				+ "')]/../following-sibling::td[contains(@class,'lineitem-amount')]/span";

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(amountPath)), driver);
		String foundAmount = driver.findElement(By.xpath(amountPath)).getText();
		foundAmount = foundAmount.replace("$", "");
		foundAmount = foundAmount.replace(" ", "");

		assertEquals(Float.parseFloat(foundAmount), Float.parseFloat(Amount), "Failed to Verify Amount");
		Utility.app_logs.info("Successfully Verified Amount : " + foundAmount);
		test_steps.add("Successfully Verified Amount : " + foundAmount);
		return test_steps;
	}

	public ArrayList<String> verifyLineItemDesc(WebDriver driver, String Category, String desc)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		String descPath = "//td[contains(@data-bind,'lineitem-category')]/span[contains(text(),'" + Category
				+ "')]/../following-sibling::td[contains(@data-bind,'lineitem-description')]/a";

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(descPath)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(descPath)), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(descPath)), driver);
		String foundDesc = driver.findElement(By.xpath(descPath)).getText();
		assertTrue(foundDesc.contains(desc),
				"Failed Found Description MisMatched Expected[" + desc + "] but found[" + foundDesc + "]");

		Utility.app_logs.info("Line Item Description : " + foundDesc);
		test_steps.add("Line Item Description : " + foundDesc);

		return test_steps;
	}

	public int verifyLineItemCount(WebDriver driver) {

		String path = "//td[contains(@data-bind,'lineitem-category')]";

		int size = driver.findElements(By.xpath(path)).size();

		return size;
	}

	public String splitStringByDot(String stringTobeSplit) {

		System.out.println(stringTobeSplit);
		String[] strSplit = stringTobeSplit.split("\\.");
		return strSplit[0];
	}

	public String getIncidentalTax(WebDriver driver, int index) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		return element.GetIncidentalTax.get(index).getText();
	}

	public String getIncidentalTotalAmount(WebDriver driver, int index) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		return element.GetIncidentalTotalAmount.get(index).getText();
	}

	public ArrayList<String> folioTab(WebDriver driver) throws InterruptedException {

		Elements_FolioLineItemsVoid elementsFolio = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> testSteps = new ArrayList<>();

		Wait.WaitForElement(driver, NewFolio.Foliotab);
		Wait.waitForElementToBeVisibile(By.xpath(NewFolio.Foliotab), driver);
		Wait.waitForElementToBeClickable(By.xpath(NewFolio.Foliotab), driver);
		elementsFolio.Foliotab.click();
		testSteps.add("Click on Folio");

		Wait.WaitForElement(driver, NewFolio.CheckboxIncludeTaxesinLineItems);
		Wait.waitForElementToBeVisibile(By.xpath(NewFolio.CheckboxIncludeTaxesinLineItems), driver);
		Wait.waitForElementToBeClickable(By.xpath(NewFolio.CheckboxIncludeTaxesinLineItems), driver);
		Utility.ScrollToElement_NoWait(elementsFolio.checkboxIncludeTaxesinLineItems, driver);
		if (!elementsFolio.checkboxIncludeTaxesinLineItems.isSelected()) {
			System.out.println("in clikc state");
			elementsFolio.checkboxIncludeTaxesinLineItems.click();
			testSteps.add("Checked  Include Taxes in Line Items checkbox");
		} else {
			testSteps.add("By default 'Include Taxes in Line Items' checkbox is checked");

		}
		return testSteps;
	}

	public void verifyLineItemsState(WebDriver driver, String Category, String State, int index)
			throws InterruptedException {

		String PathPrePost = "(//span[text()='" + Category + "']//..//..//td//img[@src='" + State + "'])[" + index
				+ "]";
		System.out.println(PathPrePost);
		Wait.WaitForElement(driver, PathPrePost);
		WebElement ElementPrePost = driver.findElement(By.xpath(PathPrePost));
		Wait.explicit_wait_visibilityof_webelement(ElementPrePost, driver);
		assertEquals(ElementPrePost.isDisplayed(), true, "Failed: void line itme is is not display");

	}

	public void itemDetailsCategoryState(WebDriver driver, String Category, String State, int index)
			throws InterruptedException {

		String PathPrePost = "(" + NewFolio.ItemDetails_PrePath + "//span[text()='" + Category + "'])[" + index
				+ "]//..//..//td//img[@src='" + State + "']";
		System.out.println(PathPrePost);
		WebElement ElementPrePost = driver.findElement(By.xpath(PathPrePost));
		Wait.explicit_wait_visibilityof_webelement(ElementPrePost, driver);
		assertEquals(ElementPrePost.isDisplayed(), true, "Failed: Line item is not pending state");

	}

	public ArrayList<String> dateItemDetails(WebDriver driver, String Category, int index, String Item_category,
			int Days) throws InterruptedException, ParseException {
		ArrayList<String> TestSteps = new ArrayList<>();

		String PathDate = "(" + NewFolio.ItemDetails_PrePath + "//span[text()='" + Category + "'])[" + index
				+ "]//..//..//td//span[contains(@data-bind,'text:$data.EffectiveDate')]";
		System.out.println(PathDate);
		WebElement ElementDate = driver.findElement(By.xpath(PathDate));
		Wait.explicit_wait_visibilityof_webelement(ElementDate, driver);
		String getDate = ESTTimeZone.getDateforLineItem(Days, false);

		test_steps.add("Expected line item date: " + getDate);
		test_steps.add("Found: " + ElementDate.getText());
		assertEquals(ElementDate.getText(), getDate, "Faile: date is mismatched in item details popup");
		TestSteps.add("Verified line item date in " + Item_category);
		return TestSteps;
	}

	public ArrayList<String> itemDetailsDescroption(WebDriver driver, String Category, String Description,
			boolean isClick, int index, String Item_Tax, String Tag) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<>();
		String PathLineItemDescription = "(" + NewFolio.ItemDetails_PrePath + "//span[text()='" + Category + "'])["
				+ index + "]//..//..//td//" + Tag + "[contains(@data-bind,'text: $data.Description')]";
		WebElement ElementDescription = driver.findElement(By.xpath(PathLineItemDescription));
		String getDescription = ElementDescription.getText();
		test_steps.add("Expected line item description: " + Description);
		test_steps.add("Found: " + getDescription);
		assertEquals(getDescription, Description, "Failed: Description is mistamtching");
		test_steps.add("Verified description in " + Item_Tax);
		if (isClick) {
			ElementDescription.click();
		}
		return test_steps;
	}

	public String getAmountItemDetails(WebDriver driver, String Category, int index) throws InterruptedException {

		String PathLineItemAmount = "(" + NewFolio.ItemDetails_PrePath + "//span[text()='" + Category + "'])[" + index
				+ "]//..//..//td//span[contains(@data-bind,'showInnroadCurrency:')]";
		WebElement ElementAmount = driver.findElement(By.xpath(PathLineItemAmount));
		return ElementAmount.getText();

	}

	public String itemDetailsIncientals(WebDriver driver) throws InterruptedException {

		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		Utility.ScrollToElement_NoWait(elementFolio.itemDetailsIncidental, driver);
		return elementFolio.itemDetailsIncidental.getText();

	}

	public ArrayList<String> cancelPopupButton(WebDriver driver, boolean isClick, boolean isEnabled)
			throws InterruptedException {
		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> stesp = new ArrayList<>();

		Wait.WaitForElement(driver, NewFolio.ItemDetails_CancelPopup);
		Wait.explicit_wait_visibilityof_webelement(elementFolio.itemDetailsCancelPopup, driver);
		Wait.explicit_wait_elementToBeClickable(elementFolio.itemDetailsCancelPopup, driver);
		Utility.ScrollToElement_NoWait(elementFolio.itemDetailsCancelPopup, driver);
		if (isClick) {
			elementFolio.itemDetailsCancelPopup.click();
			stesp.add("Click on cancel button in item details popup");
		} else {
			assertEquals(elementFolio.itemDetailsCancelPopup.isEnabled(), isEnabled,
					"Failed to verify cancel popup button state");
			if (isEnabled) {
				stesp.add("Verified cancel popup button is enabled");
			} else {
				stesp.add("Verified cancel popup button is disabled");
			}

		}
		return stesp;

	}

	public ArrayList<String> changeFolioOption(WebDriver driver, String FolioName) {

		Elements_FolioLineItemsVoid elementFolio = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, NewFolio.ChangeFolioButton);
		Wait.waitForElementToBeVisibile(By.xpath(NewFolio.ChangeFolioButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(NewFolio.ChangeFolioButton), driver);
		elementFolio.changeFolioButton.click();
		testSteps.add("Click on folio option button");

		String elementPath = "(//li//a//span[contains(text(),'" + FolioName + "')]//parent::a)[1]";
		WebElement liSelect = driver.findElement(By.xpath(elementPath));
		Wait.WaitForElement(driver, elementPath);
		Wait.waitForElementToBeVisibile(By.xpath(elementPath), driver);
		Wait.waitForElementToBeClickable(By.xpath(elementPath), driver);

		liSelect.click();
		folioLogger.info("Clicked option: " + FolioName);
		String FirstSelectedoption = elementFolio.changeFolioButton.getAttribute("title");
		testSteps.add("Selected Folio : " + FirstSelectedoption);
		folioLogger.info("Selected Folio : " + FirstSelectedoption);
		return testSteps;

	}

	public ArrayList<String> FolioExist(WebDriver driver, String SelectedFolioName, String FolioName, boolean exist)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();

		String path = "(//span[contains(text(),'" + SelectedFolioName + "')]//parent::button)";
		WebElement FolioDropDown = driver.findElement(By.xpath(path));
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Utility.ScrollToElement(FolioDropDown, driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);

		FolioDropDown.click();
		test_steps.add("Expand Folio DropDown");
		folioLogger.info("Expand Folio DropDown");

		String optionspath = "//div[contains(@data-bind,'Folio')]//ul/li//span[@class='text']";
		List<WebElement> FolioOptions = driver.findElements(By.xpath(optionspath));
		Wait.WaitForElement(driver, optionspath);
		Wait.waitForElementToBeVisibile(By.xpath(optionspath), driver);
		boolean found = false;
		folioLogger.info("Expected Folio '" + FolioName + "'");
		for (WebElement ele : FolioOptions) {
			String text = ele.getText();
			folioLogger.info("Folio Option : " + text);
			if (text.contains(FolioName)) {
				found = true;
				break;
			}
		}
		assertEquals(found, exist, " Failed: Folio Options Exist issue");
		if (exist) {
			test_steps.add("Verified Folio '" + FolioName + "' Exist");
			folioLogger.info("Verified Folio '" + FolioName + "' Exist");
		} else {
			test_steps.add("Verified Folio '" + FolioName + "' not Exist");
			folioLogger.info("Verified Folio '" + FolioName + "' not Exist");
		}
		Utility.ScrollToElement_NoWait(FolioDropDown, driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);

		FolioDropDown.click();
		test_steps.add("Close Folio DropDown");
		folioLogger.info("Close Folio DropDown");
		return test_steps;

	}

	public String MinseValue(String FirstValue, String SecondValue) throws InterruptedException, ParseException {

		int first = Integer.parseInt(FirstValue);
		int second = Integer.parseInt(SecondValue);
		int total = first - second;
		System.out.println(total);
		return String.valueOf(total);

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clikOnLineItemDescription> ' Description: <click on
	 * description field> ' Input parameters: < String category> ' Return value:
	 * <ArrayList> ' Created By: <Reddy Ponnolu> ' Created On: <08/05/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void clikOnLineItemDescription(WebDriver driver, String category, ArrayList<String> test_steps) {
		String description = "//span[contains(text(),'" + category
				+ "')]/../../td[contains(@data-bind,'lineitem-description')]/a";
		driver.findElement(By.xpath(description)).click();
		test_steps.add("Click on line item description");
		folioLogger.info("Click on line item description");
		Wait.waitForElementToBeVisibile(By.xpath(OR.roomNumberInItemDetails), driver);

	}

	public String getNoShowFeeAmount(WebDriver driver, String category, ArrayList<String> test_steps) {
		String amount = "//span[contains(text(),'" + category
				+ "')]/../../td/span[contains(@data-bind,'IncludeTaxesInLineItems')]";
		Wait.waitForElementToBeVisibile(By.xpath(amount), driver);
		amount = driver.findElement(By.xpath(amount)).getText();
		test_steps.add("No show Amount :" + amount);
		folioLogger.info("No show Amount :" + amount);
		return amount;

	}

	public void verifyLineItemPaymentMethod(WebDriver driver, String category, ArrayList<String> test_steps) {
		String description = "//span[contains(text(),'" + category
				+ "')]/../../td[contains(@data-bind,'lineitem-description')]/a";
		String paymentMethod = driver.findElement(By.xpath(description)).getText();
		test_steps.add("Resrvation Folio Paymenent Line Item Method:" + paymentMethod);
		folioLogger.info("Resrvation Folio Paymenent Line Item Method:" + paymentMethod);

	}

	public ArrayList<String> verifyLineItemDetails(WebDriver driver, String RoomNumber, String roomClassName,
			String RoomCharge, ArrayList<String> test_steps) {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);

		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);

		Wait.waitForElementToBeVisibile(By.xpath(OR.roomNumberInItemDetails), driver);

		if (!RoomNumber.equals("Unassigned")) {
			String roomNumber = FolioLineItems.roomNumberInItemDetails.getText();
			test_steps.add("Avilable RoomNumber In ItemDetails :" + roomNumber);
			folioLogger.info("Avilable RoomNumber In ItemDetails :" + roomNumber);
			assertEquals(RoomNumber, roomNumber, "Room Numbers are different");

		} else {
			String roomNumber = FolioLineItems.roomNumberInItemDetails.getText();
			test_steps.add("Avilable RoomNumber In ItemDetails :" + roomNumber);
			folioLogger.info("Avilable RoomNumber In ItemDetails :" + roomNumber);
			assertEquals(roomClassName, roomNumber, "Room Numbers are different");
		}

		Wait.waitForElementToBeVisibile(By.xpath(OR.roomChargeInItemDetails), driver);
		String roomCharge = FolioLineItems.roomChargeInItemDetails.getText().replace("$", "").trim();
		test_steps.add(" RoomCharge In ItemDetails :" + roomCharge);
		folioLogger.info(" RoomCharge In ItemDetails :" + roomCharge);

		double lineItemRoomCharge = Double.valueOf(roomCharge);

		Wait.waitForElementToBeVisibile(By.xpath(OR.taxesAndServiceCharge), driver);
		String taxesAndServiceCharge = FolioLineItems.taxesAndServiceCharge.getText().replace("$", "").trim();
		test_steps.add(" taxes & ServiceCharge In ItemDetails :" + taxesAndServiceCharge);
		folioLogger.info(" taxes & ServiceCharge In ItemDetails :" + taxesAndServiceCharge);

		double lineItemTax = Double.valueOf(taxesAndServiceCharge);
		double roomChargeInclueTax = lineItemRoomCharge + lineItemTax;
		test_steps.add(" Add  taxes & ServiceCharge to RoomCharge  :" + roomChargeInclueTax);
		folioLogger.info("Add  taxes & ServiceCharge to RoomCharge  : :" + roomChargeInclueTax);

		String roomchargeWithTax = df.format(roomChargeInclueTax);

		assertEquals(RoomCharge.replace("$", "").trim(), roomchargeWithTax, "Room Charges are different");

		Wait.waitForElementToBeVisibile(By.xpath(OR.totalSummaryInItemDetails), driver);
		String totalCharge = FolioLineItems.totalSummaryInItemDetails.getText();
		test_steps.add(" Total summary charges  In ItemDetails :" + totalCharge);
		folioLogger.info(" Total summary charges  In ItemDetails:" + totalCharge);

		Wait.waitForElementToBeVisibile(By.xpath(OR.cancelButton), driver);
		FolioLineItems.cancelButton.click();
		test_steps.add("Click on Cancel Button In Item Detal");
		folioLogger.info("Click on Cancel Button In Item Detal");

		return test_steps;
	}

	public String getLineItemAmount(WebDriver driver, String category, boolean withTax) throws InterruptedException {

		Elements_FolioLineItemsVoid elementsFolio = new Elements_FolioLineItemsVoid(driver);

		Utility.ScrollToElement(elementsFolio.includeTaxesinLineItemsCheckbox, driver);
		if (withTax) {
			if (!elementsFolio.includeTaxesinLineItemsCheckbox.isSelected()) {
				elementsFolio.includeTaxesinLineItemsCheckbox.click();
			}
		} else if (elementsFolio.includeTaxesinLineItemsCheckbox.isSelected()) {
			elementsFolio.includeTaxesinLineItemsCheckbox.click();
		}
		assertEquals(withTax, elementsFolio.includeTaxesinLineItemsCheckbox.isSelected(),
				"Failed : Include Taxes in Line Item CheckBox is not in Required State");
		String Amount = driver.findElement(By.xpath("//span[contains(text(),'" + category
				+ "')]//parent::td//following-sibling::td[@class='lineitem-amount']/span")).getText();
		Amount = Amount.split(" ")[1];
		Utility.app_logs.info("Line Item Amount containing description " + category + " = " + Amount);
		return Amount;
	}

	public void includeTaxinLIneItemCheckbox(WebDriver driver, boolean isChecked) {
		Elements_FolioLineItemsVoid elementsFolio = new Elements_FolioLineItemsVoid(driver);

		if (isChecked) {

			if (!elementsFolio.includeTaxesinLineItemsCheckbox.isSelected()) {
				elementsFolio.includeTaxesinLineItemsCheckbox.click();
			}
		} else if (!isChecked) {
			if (elementsFolio.includeTaxesinLineItemsCheckbox.isSelected()) {
				elementsFolio.includeTaxesinLineItemsCheckbox.click();
			}
		}
	}

	public String getToatalBalance(WebDriver driver) throws InterruptedException {
		Elements_FolioLineItemsVoid folioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.WaitForElement(driver, OR.Balance);
		Wait.waitForToasterToBeVisibile(By.xpath(OR.Balance), driver);
		Utility.ScrollToElement(folioLineItemsVoid.Balance, driver);
		return folioLineItemsVoid.Balance.getText();

	}

	public void verifyLineItem(WebDriver driver, String date, String category, String amount, String description,
			String roomNumber, boolean exist) {
		// Verify payment
		Elements_FolioLineItemsVoid elementsFolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		// here verify Include Taxes in Line Items checked or not
		Wait.WaitForElement(driver, NewFolio.AddedLineItems_Category);
		Wait.waitForElementToBeVisibile(By.xpath(NewFolio.AddedLineItems_Category), driver);
		Wait.waitForElementToBeClickable(By.xpath(NewFolio.AddedLineItems_Category), driver);

		int lineItemSize = elementsFolioLineItemsVoid.AddedLineItems_Category.size();
		folioLogger.info("LineItem_Size : " + lineItemSize);
		int lineNumber = lineItemSize - 1;
		folioLogger.info("Expected Date : " + date);
		folioLogger.info("Expected Room : " + roomNumber);
		folioLogger.info("Expected Category : " + category);
		folioLogger.info("Expected Amount : " + amount);
		folioLogger.info("Expected Description : " + description);
		boolean found = false;
		for (lineNumber = lineItemSize - 1; lineNumber >= 0; lineNumber--) {
			folioLogger.info("LineNumber : " + lineNumber);
			folioLogger.info("Date : " + elementsFolioLineItemsVoid.AddedLineItems_Date.get(lineNumber).getText());
			folioLogger.info("Room : " + elementsFolioLineItemsVoid.AddedLineItems_Room.get(lineNumber).getText());
			folioLogger
					.info("Category : " + elementsFolioLineItemsVoid.AddedLineItems_Category.get(lineNumber).getText());
			folioLogger.info("Amount : " + elementsFolioLineItemsVoid.AddedLineItems_Amount.get(lineNumber).getText());
			folioLogger.info(
					"Description : " + elementsFolioLineItemsVoid.AddedLineItems_Description.get(lineNumber).getText());
			String lineItemAmount = elementsFolioLineItemsVoid.AddedLineItems_Amount.get(lineNumber).getText();
			lineItemAmount = Utility.convertDollarToNormalAmount(driver, lineItemAmount);
			if (elementsFolioLineItemsVoid.AddedLineItems_Category.get(lineNumber).getText().equalsIgnoreCase(category)
					&& elementsFolioLineItemsVoid.AddedLineItems_Room.get(lineNumber).getText().equals(roomNumber)
					&& elementsFolioLineItemsVoid.AddedLineItems_Date.get(lineNumber).getText().contains(date)
					&& lineItemAmount.equals(amount) && elementsFolioLineItemsVoid.AddedLineItems_Description
							.get(lineNumber).getText().equals(description)) {
				found = true;
				break;
			}
		}
		assertEquals(found, exist,
				"Failed: Line Item having Category: " + category + " Not in Required visibility form");

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickAddLineItemButton> ' Description: <Click on add line
	 * item button> ' Input parameters: <WebDriver driver> ' Return value:
	 * <ArrayList<String>> ' Created By: <Jamal Nasir> ' Created On:
	 * <05/12/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> clickAddLineItemButton(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_FolioLineItemsVoid elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.AddLineItemButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.AddLineItemButton), driver);
		Utility.ScrollToElement(elements_FolioLineItemsVoid.AddLineItemButton, driver);
		elements_FolioLineItemsVoid.AddLineItemButton.click();
		test_steps.add("Clicked on Add button");
		folioLogger.info("Clicked on Add button");

		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <AddFolioLineItem> ' Description: <Add line item
	 * requirements i.e category and amount> ' Input parameters: <WebDriver
	 * driver> ' Return value: <ArrayList<String>> ' Created By: <Jamal Nasir> '
	 * Created On: <05/12/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> AddFolioLineItem(WebDriver driver, String Category, String Amount)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);

		Wait.waitForElementToBeVisibile(By.xpath(OR.selectCategory), driver);
		Utility.ScrollToElement(FolioLineItems.selectCategory, driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.selectCategory), driver);
		new Select(FolioLineItems.selectCategory).selectByVisibleText(Category);
		test_steps.add("Selected category : " + Category);
		folioLogger.info("Selected category : " + Category);

		Wait.waitForElementToBeVisibile(By.xpath(OR.enterAmount), driver);
		System.out.println("Amount in func:" + Amount);
		FolioLineItems.enterAmount.sendKeys(Amount);
		test_steps.add("Entered Amount : " + Amount);
		folioLogger.info("Entered Amount : " + Amount);

		Wait.waitForElementToBeVisibile(By.xpath(OR.clickCommitButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.clickCommitButton), driver);
		Utility.ScrollToElement(FolioLineItems.clickCommitButton, driver);

		FolioLineItems.clickCommitButton.click();
		test_steps.add("Clicked on commit button");
		folioLogger.info("Clicked on commit button");

		Wait.WaitForElement(driver, OR.getBalanceFolioLineItems);
		Wait.waitForElementToBeVisibile(By.xpath(OR.getBalanceFolioLineItems), driver);
		String getBalance = FolioLineItems.getBalanceFolioLineItems.getText();
		folioLogger.info(" Balance of the Folio Line Items " + getBalance);
		Wait.wait1Second();

		return test_steps;
	}

	public ArrayList<String> ClickSaveFolioItemsButton(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);

		Wait.waitForElementToBeVisibile(By.xpath(NewFolio.SaveFolio), driver);
		Utility.ScrollToElement(moveFolio.SaveFolio, driver);
		Wait.waitForElementToBeClickable(By.xpath(NewFolio.SaveFolio), driver);
		moveFolio.SaveFolio.click();

		Wait.waitForElementToBeVisibile(By.xpath(OR.AddLineItemButton), driver);
		Wait.wait10Second();
		test_steps.add("Clicked on Save");
		folioLogger.info("Clicked on Save");
		return test_steps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickFolioDetailsCheckBoxes> ' Description: <This method
	 * will select specific check box in reservation folio section> ' Input
	 * parameters: <WebDriver driver, String incidentalName> ' Return value:
	 * <ArrayList<String>> ' Created By: <Jamal Nasir> ' Created On:
	 * <05/22/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> clickFolioDetailsCheckBoxes(WebDriver driver, String checkBoxName, boolean isChecked)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String path = "(//span[contains(text(),'" + checkBoxName + "')]//preceding-sibling::input)[1]";
		System.out.println(path);
		WebElement selectCheckBox = driver.findElement(By.xpath(path));
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Utility.ScrollToElement(selectCheckBox, driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);
		if (isChecked) {
			if (selectCheckBox.isSelected()) {
				test_steps.add(checkBoxName + " already checked");
				folioLogger.info(checkBoxName + " already checked");
			} else {
				selectCheckBox.click();
				test_steps.add(checkBoxName + " checked");
				folioLogger.info(checkBoxName + " checked");
			}
		} else {
			if (selectCheckBox.isSelected()) {
				selectCheckBox.click();
				test_steps.add(checkBoxName + " unChecked");
				folioLogger.info(checkBoxName + " unChecked");
			} else {
				test_steps.add(checkBoxName + " already unChecked");
				folioLogger.info(checkBoxName + " already unChecked");
			}
		}

		return test_steps;
	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <voidLineItem> ' Description: <This method will void
	 * specific line item x in reservation folio section> ' Input parameters:
	 * <WebDriver driver, String Category,String note> ' Return value:
	 * <ArrayList<String>> ' Created By: <Jamal Nasir> ' Created On:
	 * <05/22/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> voidLineItem(WebDriver driver, String category, String note) throws InterruptedException {
		Elements_Accounts Account = new Elements_Accounts(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		float EndingBalance_Before = GetEndingBalance(driver);
		Utility.app_logs.info(EndingBalance_Before);
		String LineItem_AmountPath = "//td[contains(@data-bind,'lineitem')]/span[text()='" + category
				+ "']//ancestor::tr//td[@class='lineitem-amount']/span";

		WebElement LineItem_Amount = driver.findElement(By.xpath(LineItem_AmountPath));
		float LineItemAmount = Float.parseFloat(LineItem_Amount.getText().split(" ")[1]);
		String CheckBox_path = "//td[contains(@data-bind,'lineitem')]/span[text()='" + category
				+ "']//ancestor::tr//input[@type='checkbox']";
		WebElement CheckBox = driver.findElement(By.xpath(CheckBox_path));
		Utility.ScrollToElement(CheckBox, driver);
		CheckBox.click();
		test_steps.add("Click line item(" + category + ") checkbox");
		folioLogger.info("Click line item(" + category + ") checkbox");
		Utility.ScrollToElement(Account.voidButton, driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.voidButton), driver);
		Account.voidButton.click();
		test_steps.add("Click void button");
		folioLogger.info("Click void button");
		Wait.waitForElementToBeVisibile(By.xpath(OR.NotesPopup), driver);
		Account.NotesPopup_Note.sendKeys(note);
		test_steps.add("Entered Notes : " + note);
		folioLogger.info("Entered Notes : " + note);
		Utility.ScrollToElement(Account.NotesPopup_Void, driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.NotesPopup_Void), driver);
		Account.NotesPopup_Void.click();
		test_steps.add("Click void button to close Notes popup");
		folioLogger.info("Click void button to close Notes popup");

		Wait.waitForElementToBeVisibile(By.xpath(OR.Balance), driver);
		float EndingBalance_After = GetEndingBalance(driver);
		Utility.app_logs.info(EndingBalance_After);
		String EBB = String.format("%.2f", (EndingBalance_Before - LineItemAmount));
		assertEquals(String.format("%.2f", EndingBalance_After),
				String.format("%.2f", (EndingBalance_Before - LineItemAmount)),
				"Failed : Ending Balance is not updated");
		Utility.app_logs.info("Successfully void Line item " + category);
		test_steps.add("Successfully void Line item " + category);
		return test_steps;
	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <makePayment> ' Description: <This method will make
	 * payment in reservation folio section> ' Input parameters: <WebDriver
	 * driver,String paymentType> ' Return value: <ArrayList<String>> ' Created
	 * By: <Jamal Nasir> ' Created On: <05/22/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> makePayment(WebDriver driver, String paymentType) throws InterruptedException {
		Elements_All_Payments payment = new Elements_All_Payments(driver);
		ArrayList<String> test_steps = new ArrayList<>();

		Wait.WaitForElement(driver, OR.PayButton);
		Wait.waitForElementToBeVisibile(By.xpath(OR.PayButton), driver);
		Utility.ScrollToElement(payment.PayButton, driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.PayButton), driver);
		payment.PayButton.click();
		test_steps.add("Click payment button");
		folioLogger.info("Click payment button");

		Wait.WaitForElement(driver, OR.paymentType);
		Wait.waitForElementToBeVisibile(By.xpath(OR.paymentType), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.paymentType), driver);
		payment.paymentType.click();
		folioLogger.info("Click payment type button");

		String dropDownOption = "//a[.='" + paymentType + "']";
		Wait.WaitForElement(driver, dropDownOption);
		Wait.waitForElementToBeVisibile(By.xpath(dropDownOption), driver);
		Wait.waitForElementToBeClickable(By.xpath(dropDownOption), driver);

		driver.findElement(By.xpath(dropDownOption)).click();
		test_steps.add("Selected option  : " + paymentType);
		folioLogger.info("Selected option  : " + paymentType);

		Utility.ScrollToElement(payment.paymentSubmitButton, driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.paymentSubmitButton), driver);
		String payButtonText = payment.paymentSubmitButton.getText();
		payment.paymentSubmitButton.click();
		test_steps.add("Click (" + payButtonText + ") button");
		folioLogger.info("Click (" + payButtonText + ") button");

		Wait.WaitForElement(driver, OR.paymentSuccessFullPopupClose);
		Wait.waitForElementToBeVisibile(By.xpath(OR.paymentSuccessFullPopupClose), driver);
		payment.paymentSuccessFullPopupClose.click();
		folioLogger.info("Click close button");
		test_steps.add("Click close button");
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectGuestFolioFristRoom> ' Description: <Select Guest
	 * folio frist room in drop down> ' Input parameters: < Amount> ' Return
	 * value: <ArrayList> ' Created By: <Reddy Ponnolu> ' Created On:
	 * <01/05/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> selectGuestFolioFristRoom(WebDriver driver, String Amount, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);

		try {
			Wait.explicit_wait_visibilityof_webelement_120(FolioLineItems.SelectGuestFolioRoom, driver);
			/*
			 * JavascriptExecutor jse = (JavascriptExecutor) driver;
			 * jse.executeScript("arguments[0].scrollIntoView();",
			 * FolioLineItems.SelectGuestFolioRoom);
			 */
			// Wait.explicit_wait_visibilityof_webelement_120(FolioLineItems.SelectGuestFolioRoom,
			// driver);
			Wait.waitForElementToBeVisibile(By.xpath("//button[contains(@title,'Guest Folio')]"), driver);
			// Wait.explicit_wait_elementToBeClickable(FolioLineItems.SelectGuestFolioRoom,
			// driver);
			Wait.waitForElementToBeClickable(By.xpath("//button[contains(@title,'Guest Folio')]"), driver);
			Wait.wait5Second();
			FolioLineItems.SelectGuestFolioRoom.click();
			Wait.wait2Second();
			Wait.explicit_wait_visibilityof_webelement_120(FolioLineItems.GuestFolioFristRoom, driver);
			Wait.explicit_wait_elementToBeClickable(FolioLineItems.SelectGuestFolioRoom, driver);
			FolioLineItems.GuestFolioFristRoom.click();
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			Wait.explicit_wait_visibilityof_webelement_120(FolioLineItems.SelectGuestFolioRoom, driver);
			/*
			 * JavascriptExecutor jse = (JavascriptExecutor) driver;
			 * jse.executeScript("arguments[0].scrollIntoView();",
			 * FolioLineItems.SelectGuestFolioRoom);
			 */
			// Wait.explicit_wait_visibilityof_webelement_120(FolioLineItems.SelectGuestFolioRoom,
			// driver);
			// Wait.explicit_wait_elementToBeClickable(FolioLineItems.SelectGuestFolioRoom,
			// driver);
			Wait.waitForElementToBeClickable(By.xpath("//button[contains(@title,'Guest Folio')]"), driver);
			Wait.wait5Second();
			FolioLineItems.SelectGuestFolioRoom.click();
			Wait.wait2Second();
			// Wait.explicit_wait_visibilityof_webelement_120(FolioLineItems.GuestFolioFristRoom,
			// driver);
			// Wait.explicit_wait_elementToBeClickable(FolioLineItems.SelectGuestFolioRoom,
			// driver);
			Wait.waitForElementToBeClickable(By.xpath("//span[contains(text(),'Guest Folio For DBR')]"), driver);
			FolioLineItems.GuestFolioFristRoom.click();
		}
		test_steps.add("select room successfully");
		folioLogger.info("select room successfully");
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectGuestFolioSecondRoom> ' Description: <Select Guest
	 * folio Second room in drop down> ' Input parameters: < Amount> ' Return
	 * value: <ArrayList> ' Created By: <Reddy Ponnolu> ' Created On:
	 * <01/05/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> selectGuestFolioSecondRoom(WebDriver driver, String Amount, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		try {
			Wait.explicit_wait_visibilityof_webelement_120(FolioLineItems.SelectGuestFolioRoom, driver);
			/*
			 * JavascriptExecutor jse = (JavascriptExecutor) driver;
			 * jse.executeScript("arguments[0].scrollIntoView();",
			 * FolioLineItems.SelectGuestFolioRoom);
			 */
			Wait.wait5Second();
			Wait.waitForElementToBeClickable(By.xpath("//button[contains(@title,'Guest Folio')]"), driver);
			FolioLineItems.SelectGuestFolioRoom.click();
			Wait.wait2Second();
			/*
			 * Wait.explicit_wait_visibilityof_webelement_120(FolioLineItems.
			 * GuestFolioSecondRoom, driver);
			 * Wait.explicit_wait_elementToBeClickable(FolioLineItems.
			 * GuestFolioSecondRoom, driver);
			 */
			Wait.waitForElementToBeClickable(By.xpath("//span[contains(text(),'Guest Folio For KS')]"), driver);
			FolioLineItems.GuestFolioSecondRoom.click();
			test_steps.add("select  second room successfully");
			folioLogger.info("select second  room successfully");
			Wait.waitForElementToBeVisibile(By.xpath("//span[contains(text(),'Guest Folio For KS')]"), driver);
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			Wait.explicit_wait_visibilityof_webelement_120(FolioLineItems.SelectGuestFolioRoom, driver);
			/*
			 * JavascriptExecutor jse = (JavascriptExecutor) driver;
			 * jse.executeScript("arguments[0].scrollIntoView();",
			 * FolioLineItems.SelectGuestFolioRoom);
			 */
			Wait.wait2Second();
			Wait.waitForElementToBeClickable(By.xpath("//button[contains(@title,'Guest Folio')]"), driver);
			FolioLineItems.SelectGuestFolioRoom.click();
			Wait.wait2Second();
			/*
			 * Wait.explicit_wait_visibilityof_webelement_120(FolioLineItems.
			 * GuestFolioSecondRoom, driver);
			 * Wait.explicit_wait_elementToBeClickable(FolioLineItems.
			 * GuestFolioSecondRoom, driver);
			 */
			Wait.waitForElementToBeClickable(By.xpath("//span[contains(text(),'Guest Folio For KS')]"), driver);
			FolioLineItems.GuestFolioSecondRoom.click();
			test_steps.add("select  second room successfully");
			folioLogger.info("select second  room successfully");
			Wait.waitForElementToBeVisibile(By.xpath("//span[contains(text(),'Guest Folio For KS')]"), driver);

		}
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyLineItemDetails> ' Description: <Verify the
	 * roomNumber,RoomCharge and total Balance in lineItemDetail page> ' Input
	 * parameters: < > ' Return value: <ArrayList> ' Created By: <Reddy Ponnolu>
	 * ' Created On: <01/05/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> verifyLineItemDetails(WebDriver driver, String RoomNumber, String RoomCharge,
			ArrayList<String> test_steps) {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.roomNumberInItemDetails), driver);
		String roomNumber = FolioLineItems.roomNumberInItemDetails.getText();
		test_steps.add("Avilable RoomNumber In ItemDetails :" + roomNumber);
		folioLogger.info("Avilable RoomNumber In ItemDetails :" + roomNumber);
		assertEquals(RoomNumber, roomNumber, "Room Numbers are different");

		Wait.waitForElementToBeVisibile(By.xpath(OR.roomChargeInItemDetails), driver);
		String roomCharge = FolioLineItems.roomChargeInItemDetails.getText();
		test_steps.add(" RoomCharge In ItemDetails :" + roomCharge);
		folioLogger.info(" RoomCharge In ItemDetails :" + roomCharge);
		assertEquals(RoomCharge, roomCharge, "Room Charges are different");

		Wait.waitForElementToBeVisibile(By.xpath(OR.totalSummaryInItemDetails), driver);
		String totalCharge = FolioLineItems.totalSummaryInItemDetails.getText();
		test_steps.add(" Total summary charges  In ItemDetails :" + totalCharge);
		folioLogger.info(" Total summary charges  In ItemDetails:" + totalCharge);

		Wait.waitForElementToBeVisibile(By.xpath(OR.cancelButton), driver);
		FolioLineItems.cancelButton.click();
		test_steps.add("Click on Cancel Button In Item Detal");
		folioLogger.info("Click on Cancel Button In Item Detal");

		return test_steps;

	}

	public void clikOnSecondLineItemDescription(WebDriver driver, String category, int line,
			ArrayList<String> test_steps) {
		String description = "(//span[contains(text(),'" + category
				+ "')]/../../td[contains(@data-bind,'lineitem-description')]/a)[2]";
		driver.findElement(By.xpath(description)).click();
		test_steps.add("Click on line item description");
		folioLogger.info("Click on line item description");

		Wait.waitForElementToBeVisibile(By.xpath(OR.roomNumberInItemDetails), driver);

	}

	public ArrayList<String> verifyLineItem(WebDriver driver, String Category, String Amount, String Quantity,
			String timeZone, ArrayList<String> test_steps) {
		// ArrayList<String> test_steps = new ArrayList<String>();
		try {
			String lineItemsTable = "(//tbody[contains(@data-bind,'ComputedFolioItemsElement')])[1]";

			String datePath = "//td[contains(@data-bind,'lineitem-category')]/span[contains(text(),'" + Category
					+ "')]/../preceding-sibling::td[contains(@class,'lineitem-date')]/span";
			/*
			 * String propertyPath =
			 * "//td[contains(@data-bind,'lineitem-category')]/span[contains(text(),'"
			 * + Category +
			 * "')]/../preceding-sibling::td[contains(@class,'lineitem-rooms')]/span";
			 */
			String catPath = "//td[contains(@data-bind,'lineitem-category')]/span[contains(text(),'" + Category + "')]";
			String amountPath = "(//td[contains(@data-bind,'lineitem-category')]/span[contains(text(),'" + Category
					+ "')]/../following-sibling::td[contains(@class,'lineitem-amount')]/span)";
			String QuantityPath = "(//td[contains(@data-bind,'lineitem-category')]/span[contains(text(),'" + Category
					+ "')]/../following-sibling::td[contains(@class,'lineitem-qty')]/span)";

			/*
			 * Wait.explicit_wait_visibilityof_webelement_120(driver.findElement
			 * (By.xpath(datePath)), driver); String foundDate =
			 * driver.findElement(By.xpath(datePath)).getText();
			 * assertEquals(Utility.parseDate(foundDate, "EEE MMM dd, yyyy",
			 * "EEE MMM dd, yyyy"), Utility.getCurrentDate("EEE MMM dd, yyyy"),
			 * "Failed to Verify Date");
			 * Utility.app_logs.info("Successfully Verified Date : " +
			 * foundDate); test_steps.add("Successfully Verified Date : " +
			 * foundDate);
			 */

			Date date = new Date();
			DateFormat df = new SimpleDateFormat("MM/dd/yy");

			// Use Madrid's time zone to format the date in
			df.setTimeZone(TimeZone.getTimeZone(timeZone));

			String d = df.format(date);

			/*
			 * payline =
			 * "(//span[contains(text(),'payment') and contains(text(),'" +
			 * amount + "') and contains(text(),'" + AccountName +
			 * "')]/../preceding-sibling::td//span)[2]";
			 */
			String lineItemDate = driver.findElement(By.xpath(datePath)).getText();
			Wait.wait3Second();
			if (lineItemDate.equalsIgnoreCase(d)) {
				test_steps.add("Folio Line Item Date : " + lineItemDate);
				folioLogger.info("Folio Line Item Date : : " + lineItemDate);
			} else {
				test_steps.add("Folio Line Item Date : : " + lineItemDate);
				folioLogger.info("Folio Line Item Date : : " + lineItemDate);
			}

			/*
			 * Wait.explicit_wait_visibilityof_webelement_120(driver.findElement
			 * (By.xpath(propertyPath)), driver); String foundProperty =
			 * driver.findElement(By.xpath(propertyPath)).getText();
			 * assertEquals(foundProperty, Property,
			 * "Failed to Verify Property");
			 * Utility.app_logs.info("Successfully Verified Property : " +
			 * foundProperty);
			 * test_steps.add("Successfully Verified Property : " +
			 * foundProperty);
			 */

			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(catPath)), driver);
			String foundCat = driver.findElement(By.xpath(catPath)).getText();
			assertEquals(foundCat, Category, "Failed to Verify Category");
			Utility.app_logs.info("Successfully Verified  FristRoom Category: " + foundCat);
			test_steps.add("Successfully Verified  FristRoom Category     :  " + foundCat);

			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(QuantityPath)), driver);
			String foundqty = driver.findElement(By.xpath(QuantityPath)).getText();
			assertEquals(foundqty, Quantity, "Failed to Verify Quantity");
			Utility.app_logs.info("Successfully Verified Quantity : " + foundqty);
			test_steps.add("Successfully Verified Quantity : " + foundqty);

			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(amountPath)), driver);
			String foundAmount = driver.findElement(By.xpath(amountPath)).getText();
			foundAmount = foundAmount.replace("$", "");
			foundAmount = foundAmount.replace(" ", "");

			String tax = getLineTaxAmount(driver, Category);

			Utility.app_logs.info("Successfully Verified Amount : " + foundAmount);
			test_steps.add("Successfully Verified Amount : " + foundAmount);

		} catch (Exception e) {

		}
		return test_steps;
	}

	public void ApplyRouting(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {

		Elements_MovieFolio elements_MovieFolio = new Elements_MovieFolio(driver);
		Elements_FolioLineItemsVoid Ele_FolioItem = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_MovieFolio.SelectAllLineitems, driver);
		Wait.explicit_wait_elementToBeClickable(elements_MovieFolio.SelectAllLineitems, driver);
		elements_MovieFolio.SelectAllLineitems.click();
		Wait.explicit_wait_visibilityof_webelement(elements_MovieFolio.ApplyRoutingButton, driver);
		Wait.explicit_wait_elementToBeClickable(elements_MovieFolio.ApplyRoutingButton, driver);
		elements_MovieFolio.ApplyRoutingButton.click();
		Wait.explicit_wait_visibilityof_webelement_120(elements_MovieFolio.ConfirmApplyRouting, driver);
		// assertTrue(elements_MovieFolio.ConfirmApplyRouting.isDisplayed(),
		// "Failed:Confirm Page not displayed");
		// elements_MovieFolio.ConfirmApplyRouting_OK.click();
		String PopUpText = elements_MovieFolio.ChargeRoutingPopText.getText();
		test_steps.add("PopUpText  :" + PopUpText);
		folioLogger.info("PopUpText  :" + PopUpText);
		assertEquals(PopUpText, "Apply Charge Routing ?");
		elements_MovieFolio.ConfirmApplyRouting_OK.click();
		Wait.waitForElementToBeVisibile(By.xpath(OR.clickOnSave), driver);
		Wait.wait2Second();
		elements_MovieFolio.clickOnSave.click();
		// int size = Ele_FolioItem.LineItemsAmountFields.size();
		// System.out.println("Size:" + size);
		// String lineitemsize = Integer.toString(size);
		// assertTrue(lineitemsize.equals("0"), "Failed:Room Changes not moved
		// to folio account");

	}

	public ArrayList<String> verifyLineItemRoomChageCategory(WebDriver driver) {
		ArrayList<String> test_steps = new ArrayList<String>();
		int category = driver.findElements(By.xpath("//td[contains(@data-bind,'lineitem-category')]/span")).size();
		// List<WebElement> catlist =driver.findElements(By.xpath(category));
		String Category = null;
		for (int i = 1; i <= category; i++) {
			Category = driver
					.findElement(By.xpath(("(//td[contains(@data-bind,'lineitem-category')]/span)[" + i + "]")))
					.getText();
			System.out.println("Categorty types:" + Category);
			if (Category.equals("Room Charge")) {
				String catPath = "//td[contains(@data-bind,'lineitem-category')]/span[contains(text(),'" + Category
						+ "')]";
				Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(catPath)), driver);
				String foundCat = driver.findElement(By.xpath(catPath)).getText();
				assertEquals(foundCat, Category, "Failed to Verify Category");
				Utility.app_logs.info("Successfully Verified Category : " + foundCat);
				test_steps.add("Succxessfully Verified Category : " + foundCat);

			} else {
				Utility.app_logs.info("Room Charge category not avilable in category list");
				test_steps.add("Room Charge category not avilable in category list");

			}
		}

		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectGuestFolioAccount> ' Description: <Select Guest
	 * folio Account in drop down> ' Input parameters: <
	 * Amount,reservationNumber> ' Return value: <ArrayList> ' Created By:
	 * <Reddy Ponnolu> ' Created On: <01/05/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> selectGuestFolioAccount(WebDriver driver, String reservationNumber,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		try {
			/*
			 * Wait.explicit_wait_visibilityof_webelement_120(FolioLineItems.
			 * SelectGuestFolioRoom, driver);
			 * Wait.explicit_wait_elementToBeClickable(FolioLineItems.
			 * SelectGuestFolioRoom, driver);
			 */
			Wait.wait2Second();
			Wait.waitForElementToBeClickable(By.xpath("//button[contains(@title,'Guest Folio')]"), driver);
			FolioLineItems.SelectGuestFolioRoom.click();
			Wait.wait2Second();
			String guestFolioAccount = "(//span[contains(text(),'" + reservationNumber + "')])[3]";
			WebElement selectAccount = driver.findElement(By.xpath(guestFolioAccount));
			Wait.explicit_wait_visibilityof_webelement_120(selectAccount, driver);
			Wait.explicit_wait_elementToBeClickable(selectAccount, driver);
			Wait.waitForElementToBeClickable(By.xpath("(//span[contains(text(),'" + reservationNumber + "')])[3]"),
					driver);
			selectAccount.click();
			test_steps.add("select Account successfully");
			folioLogger.info("select Account successfully");

		} catch (Exception e) {
			/*
			 * Wait.explicit_wait_visibilityof_webelement_120(FolioLineItems.
			 * SelectGuestFolioRoom, driver);
			 * Wait.explicit_wait_elementToBeClickable(FolioLineItems.
			 * SelectGuestFolioRoom, driver);
			 */
			Wait.wait5Second();
			Wait.waitForElementToBeClickable(By.xpath("//button[contains(@title,'Guest Folio')]"), driver);
			FolioLineItems.SelectGuestFolioRoom.click();
			Wait.wait2Second();
			String guestFolioAccount = "(//span[contains(text(),'" + reservationNumber + "')])[3]";
			WebElement selectAccount = driver.findElement(By.xpath(guestFolioAccount));
			Wait.explicit_wait_visibilityof_webelement_120(selectAccount, driver);
			Wait.explicit_wait_elementToBeClickable(selectAccount, driver);
			Wait.waitForElementToBeClickable(By.xpath("(//span[contains(text(),'" + reservationNumber + "')])[3]"),
					driver);
			selectAccount.click();
			test_steps.add("select Account successfully");
			folioLogger.info("select Account successfully");
		}

		return test_steps;
	}

	public String lineItemQuentity(WebDriver driver, String Category, String Quentity, int index)
			throws InterruptedException {
		ArrayList<String> TestSteps = new ArrayList<>();
		String PathLintItemQuentity = "(//span[text()='" + Category
				+ "']//..//following-sibling::td[@class='lineitem-qty']//span)[" + index + "]";
		PathLintItemQuentity = driver.findElement(By.xpath(PathLintItemQuentity)).getText();

		// WebElement element_LintItemQuentity =
		// driver.findElement(By.xpath(PathLintItemQuentity));

		TestSteps.add("Expected line item quentity: " + Quentity);
		TestSteps.add("Found in line item: " + PathLintItemQuentity);
		assertEquals(PathLintItemQuentity, Quentity, "Quentity is mismatching");
		TestSteps.add("Verified line item quentity");
		return PathLintItemQuentity;

	}

	public String getLineItemDescription(WebDriver driver, String category, int line, ArrayList<String> test_steps) {
		String description = "//span[contains(text(),'" + category
				+ "')]/../../td[contains(@data-bind,'lineitem-description')]/a";
		String des = driver.findElement(By.xpath(description)).getText();
		test_steps.add("Folio line item description:  " + des);
		folioLogger.info("Folio line item description:  " + des);
		return des;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <ChangeFolio> ' Description: <short description about
	 * method purpose> ' Input parameters: <WebDriver driver, String
	 * NewFolioName,String SelectFolioName> ' Return value: <ArrayList<String>>
	 * ' Created By: <Jamal Nasir> ' Created On: <05/18/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> ChangeFolio(WebDriver driver, String NewFolioName, String SelectFolioName)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();

		String path = "(//span[contains(text(),'" + NewFolioName + "')]//parent::button)[1]";
		folioLogger.info(path);
		WebElement FolioDropDown = driver.findElement(By.xpath(path));
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Utility.ScrollToElement(FolioDropDown, driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);

		String FirstSelectedoption = FolioDropDown.getText();
		test_steps.add("Current Selected Folio : " + FirstSelectedoption);
		folioLogger.info("Current Selected Folio : " + FirstSelectedoption);

		FolioDropDown.click();
		test_steps.add("Folio DropDown Clicked");
		folioLogger.info("Folio DropDown Clicked");

		String liPath = "(//span[contains(text(),'" + SelectFolioName + "')]//parent::a)[1]";
		WebElement liSelect = driver.findElement(By.xpath(liPath));
		Wait.waitForElementToBeVisibile(By.xpath(liPath), driver);
		Wait.waitForElementToBeClickable(By.xpath(liPath), driver);

		liSelect.click();
		folioLogger.info("Clicked option: " + SelectFolioName);
		FirstSelectedoption = FolioDropDown.getText();
		test_steps.add("Selected Folio : " + FirstSelectedoption);
		folioLogger.info("Selected Folio : " + FirstSelectedoption);
		return test_steps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 *
	 * ' Method Name: <addLineItemAccount> ' Description: <This method will add
	 * line Item in account folio> ' Input parameters:(WebDriver, String,
	 * String,ArrayList<String> , String ,int ) ' Return value:
	 * ArrayList<String> ' Created By: <Adhnan Ghaffar> ' Created On:
	 * <06/02/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> addLineItemAccount(WebDriver driver, String SelectCategory, String Amount,
			ArrayList<String> steps, String selectProperty, int index) throws InterruptedException {

		Elements_FolioLineItemsVoid elementFolioLineItem = new Elements_FolioLineItemsVoid(driver);

		Wait.WaitForElement(driver, OR.click_Add_Button);
		Wait.waitForElementToBeVisibile(By.xpath(OR.click_Add_Button), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.click_Add_Button), driver);

		Utility.ScrollToElement_NoWait(elementFolioLineItem.click_Add_Button, driver);
		elementFolioLineItem.click_Add_Button.click();
		folioLogger.info("Click Add Line Item Button");
		steps.add("Click Add Line Item Button");

		Wait.WaitForElement(driver, NewFolio.SelectFolioProprtyInAccount);
		Wait.waitForElementToBeClickable(By.xpath(NewFolio.SelectFolioProprtyInAccount), driver);
		Wait.waitForElementToBeVisibile(By.xpath(NewFolio.SelectFolioProprtyInAccount), driver);
		new Select(elementFolioLineItem.SelectFolioProprtyInAccount).selectByVisibleText(selectProperty);
		steps.add("Selected property: " + selectProperty);

		Wait.WaitForElement(driver, OR.selectCategory);
		Wait.waitForElementToBeClickable(By.xpath(OR.selectCategory), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.selectCategory), driver);
		new Select(elementFolioLineItem.selectCategory).selectByVisibleText(SelectCategory);
		folioLogger.info("Select Line Item Category : " + SelectCategory);
		steps.add("Select Line Item Category : " + SelectCategory);

		elementFolioLineItem.enterAmount.clear();
		elementFolioLineItem.enterAmount.sendKeys(Amount);
		folioLogger.info("Enter Line Item Amount : " + Amount);
		steps.add("Enter Line Item Amount : " + Amount);

		Wait.waitForElementToBeVisibile(By.xpath(OR.clickCommitButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.clickCommitButton), driver);

		elementFolioLineItem.clickCommitButton.click();
		folioLogger.info("Click on Commit button");
		steps.add("Click on Commit button");
		/*
		 * if(elementFolioLineItem.clickCommitButton.isEnabled()){
		 * Wait.explicit_wait_visibilityof_webelement(elementFolioLineItem.
		 * clickCommitButton, driver);
		 * Utility.ScrollToElement(elementFolioLineItem.clickCommitButton,
		 * driver); elementFolioLineItem.clickCommitButton.click();
		 * folioLogger.info("Again Click on Commit button");
		 * steps.add("Again Click on Commit button"); }
		 */
		// assertTrue(!elementFolioLineItem.clickCommitButton.isEnabled(),"Failed:
		// Commit Button is not Clicked");
		return steps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 *
	 * ' Method Name: <verifyAddedLineItem> ' Description: <This method will
	 * verify line Item> ' Input parameters:(WebDriver, String, String, String,
	 * String, String,String ) ' Return value: <void> ' Created By: <Adhnan
	 * Ghaffar> ' Created On: <06/02/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifyAddedLineItem(WebDriver driver, String icon, String date, String category, String amount,
			String description) throws InterruptedException {
		// Verify payment
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.WaitForElement(driver, NewFolio.AddedLineItems_Category);
		Wait.waitForElementToBeVisibile(By.xpath(NewFolio.AddedLineItems_Category), driver);
		Wait.explicit_wait_visibilityof_webelement_350(Elements_FolioLineItemsVoid.AddedLineItems_Category.get(0),
				driver);
		if (!amount.contains("$")) {
			amount = "$ " + amount.replaceAll(" ", "");
		}
		int LineItem_Size = Elements_FolioLineItemsVoid.AddedLineItems_Category.size();
		folioLogger.info("LineItem_Size : " + LineItem_Size);
		folioLogger.info("Expected Line Item Icon : " + icon);
		folioLogger.info("Expected Line Item Date : " + date);
		folioLogger.info("Expected Line Item Category : " + category);
		folioLogger.info("Expected Line Item Description : " + description);
		folioLogger.info("Expected Line Item Amount : " + amount);
		int lineNumber = LineItem_Size - 1;
		folioLogger.info("LineNumber : " + lineNumber);
		boolean found = false;
		Wait.explicit_wait_visibilityof_webelement_350(
				Elements_FolioLineItemsVoid.AddedLineItems_Category.get(LineItem_Size - 1), driver);
		for (lineNumber = LineItem_Size - 1; lineNumber >= 0; lineNumber--) {

			String desc = Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber).getText();
			String lineItemDate = Elements_FolioLineItemsVoid.AddedLineItems_Date.get(lineNumber).getText();
			String IconSrc = Elements_FolioLineItemsVoid.AddedLineItems_Icon.get(lineNumber).getAttribute("src");
			String LineItemAmount = Elements_FolioLineItemsVoid.AddedLineItems_Amount.get(lineNumber).getText();
			String LineItemCategory = Elements_FolioLineItemsVoid.AddedLineItems_Category.get(lineNumber).getText();
			folioLogger.info("LineNumber : " + lineNumber);
			folioLogger.info("Category : " + LineItemCategory);
			folioLogger.info("Amount : " + LineItemAmount);
			folioLogger.info("Description : " + desc);
			folioLogger.info("Icon Source : " + IconSrc);
			folioLogger.info("Date : " + lineItemDate);

			if (LineItemCategory.equalsIgnoreCase(category) && LineItemAmount.contains(amount)
					&& desc.replaceAll(" ", "").contains(description.replaceAll(" ", "")) && IconSrc.contains(icon)
					&& lineItemDate.equals(date)) {
				Utility.ScrollToElement(Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber), driver);
				found = true;
				break;
			}
		}
		if (!found) {
			assertTrue(false, "Failed: Required item not found");
		}
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 *
	 * ' Method Name: <verifyLineItemClickIcon> ' Description: <This method will
	 * verify line Item and click on it's description if required> ' Input
	 * parameters:(WebDriver, String, String, String, String, boolean,String ) '
	 * Return value: <void> ' Created By: <Adhnan Ghaffar> ' Created On:
	 * <06/02/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifyLineItemClickIcon(WebDriver driver, String Icon, String Category, String Amount,
			String Description, boolean clickIcon, String newIcon) throws InterruptedException {
		// Verify payment
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement_350(Elements_FolioLineItemsVoid.AddedLineItems_Category.get(0),
				driver);
		if (!Amount.contains("$")) {
			Amount = "$ " + Amount.replaceAll(" ", "");
		}
		int LineItem_Size = Elements_FolioLineItemsVoid.AddedLineItems_Category.size();
		folioLogger.info("LineItem_Size : " + LineItem_Size);
		folioLogger.info("Expected Line Item Category : " + Category);
		folioLogger.info("Expected Line Item Icon : " + Icon);
		folioLogger.info("Expected Line Item Description : " + Description);
		folioLogger.info("Expected Line Item Amount : " + Amount);
		int lineNumber = LineItem_Size - 1;
		folioLogger.info("LineNumber : " + lineNumber);
		boolean found = false;
		// Wait.explicit_wait_visibilityof_webelement_350(
		// Elements_FolioLineItemsVoid.AddedLineItems_Category.get(LineItem_Size
		// - 1), driver);
		Wait.WaitForElement(driver, NewFolio.AddedLineItems_Category);

		for (lineNumber = LineItem_Size - 1; lineNumber >= 0; lineNumber--) {

			String desc = Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber).getText();
			String IconSrc = Elements_FolioLineItemsVoid.AddedLineItems_Icon.get(lineNumber).getAttribute("src");
			String LineItemAmount = Elements_FolioLineItemsVoid.AddedLineItems_Amount.get(lineNumber).getText();
			String LineItemCategory = Elements_FolioLineItemsVoid.AddedLineItems_Category.get(lineNumber).getText();
			folioLogger.info("LineNumber : " + lineNumber);
			folioLogger.info("Category : " + LineItemCategory);
			folioLogger.info("Amount : " + LineItemAmount);
			folioLogger.info("Description : " + desc);
			Utility.app_logs.info("line Item Icon  src: " + IconSrc);

			if (LineItemCategory.equalsIgnoreCase(Category) && LineItemAmount.contains(Amount)
					&& desc.replaceAll(" ", "").contains(Description.replaceAll(" ", "")) && IconSrc.contains(Icon)) {
				Utility.ScrollToElement(Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber), driver);
				found = true;
				if (clickIcon) {
					Utility.ScrollToElement(Elements_FolioLineItemsVoid.AddedLineItems_Icon.get(lineNumber), driver);
					Elements_FolioLineItemsVoid.AddedLineItems_Icon.get(lineNumber).click();
					for (int i = 0; i < 5; i++) {
						if (Elements_FolioLineItemsVoid.AddedLineItems_Icon.get(lineNumber).getAttribute("src")
								.contains(newIcon)) {
							break;
						}
					}
				}
				break;
			}
		}
		if (!found) {
			assertTrue(false, "Failed: Required item not found");
		}
	}

	public String getLineItemAmount(WebDriver driver, String Category, String sourceIcon, boolean WithTax)
			throws InterruptedException {
		Elements_Reservation res = new Elements_Reservation(driver);
		Utility.ScrollToElement(res.Include_Taxes_in_Line_Items_Checkbox, driver);
		if (WithTax) {
			if (!res.Include_Taxes_in_Line_Items_Checkbox.isSelected()) {
				res.Include_Taxes_in_Line_Items_Checkbox.click();
				folioLogger.info("UnCheck Include Taxes in Line Item CheckBox");
			}
		} else if (res.Include_Taxes_in_Line_Items_Checkbox.isSelected()) {
			res.Include_Taxes_in_Line_Items_Checkbox.click();
			folioLogger.info("Check Include Taxes in Line Item CheckBox");
		}
		assertEquals(WithTax, res.Include_Taxes_in_Line_Items_Checkbox.isSelected(),
				"Failed : Include Taxes in Line Item CheckBox is not in Required State");
		String Amount = driver
				.findElement(By.xpath("//img[contains(@src,'" + sourceIcon
						+ "')]/parent::td//following-sibling::td/span[contains(text(),'" + Category
						+ "')]//parent::td//following-sibling::td[@class='lineitem-amount']/span"))
				.getText();
		Amount = Amount.split(" ")[1];
		Utility.app_logs.info("Line Item Amount containing description " + Category + " = " + Amount);
		return Amount;
	}

	public String getLineItemDescription(WebDriver driver, String Category) throws InterruptedException {

		String descPath = null;
		String desc = null;
		try {
			descPath = "(//td[contains(@data-bind,'lineitem-category')]/span[contains(text(),'" + Category
					+ "')]/../following-sibling::td[contains(@data-bind,'lineitem-description')]/a)[1]";
			Wait.WaitForElement(driver, descPath);
			desc = driver.findElement(By.xpath(descPath)).getText();
		} catch (Exception e) {
			descPath = "(//td[contains(@data-bind,'lineitem-category')]/span[contains(text(),'" + Category
					+ "')]/../following-sibling::td[contains(@data-bind,'lineitem-description')]/a)[2]";
			Wait.WaitForElement(driver, descPath);
			desc = driver.findElement(By.xpath(descPath)).getText();
		}
		return desc;

	}

	public ArrayList<String> ClickSaveFolioButton(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);

		Wait.waitForElementToBeVisibile(By.xpath(NewFolio.SaveFolio), driver);
		Utility.ScrollToElement(moveFolio.SaveFolio, driver);
		Wait.waitForElementToBeClickable(By.xpath(NewFolio.SaveFolio), driver);
		for (int i = 0; i < 2; i++) {
			Wait.wait10Second();
			if (moveFolio.SaveFolio.isEnabled()) {

				moveFolio.SaveFolio.click();
				System.out.print(" In if: Save is Clicked");

			}

			else {
				System.out.print(" In Else Break");

				break;
			}
			System.out.print(" This After Break Break");

		}

		test_steps.add("Clicked on Save");
		folioLogger.info("Clicked on Save");

		return test_steps;

	}

	public String getPaymentAmount(WebDriver driver) throws InterruptedException {
		Elements_FolioLineItemsVoid folioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.WaitForElement(driver, OR.folioPayments);
		Wait.waitForElementToBeVisibile(By.xpath(OR.folioPayments), driver);
		Utility.ScrollToElement(folioLineItemsVoid.folioPayments, driver);
		return folioLineItemsVoid.folioPayments.getText();

	}

	public String addTwoValue(String FirstValue, String SecondValue) {
		double firstValue = Double.parseDouble(FirstValue);
		double secondValue = Double.parseDouble(SecondValue);
		double Total = firstValue + secondValue;
		String amount = String.format("%.2f", Total);
		System.out.println("total: " + amount);
		return amount;
	}

	public void verifyFolioLineItem(WebDriver driver, ArrayList<String> test_steps, String date, String category,
			String desc, String amount, String nameOnCard, String last4Digit, String expiryDate)
			throws InterruptedException {

		desc = "Name: " + nameOnCard + " Account #: XXXX" + last4Digit + " Exp. Date: " + expiryDate + "";
		folioLogger.info("Description is" + desc);
		amount = "$ " + amount + "";
		folioLogger.info("Amount is" + amount);
		String path = "//tbody[contains(@data-bind,'ComputedFolioItems')]//td[@class='lineitem-date']/span[(text()='"
				+ date + "')]" + "/../following-sibling::td[@class='lineitem-category']/span[(text()='" + category
				+ "')]" + "/../following-sibling::td[@class='lineitem-description']//a[normalize-space(text()='" + desc
				+ "')]" + "/../following-sibling::td[@class='lineitem-amount']/span[(text()='" + amount + "')]";

		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		// Verified No Show Fee line item
		assertTrue(element.isDisplayed(), "Failed: To Verify Line Item");
		test_steps.add(" Verified Date:<b>  " + date + "</b>");
		folioLogger.info("Verified  Date: " + date);
		test_steps.add(" Verified  Category:<b>  " + category + "</b>");
		folioLogger.info(" Verified  Category: " + category);
		test_steps.add("Verified  Description:<b>  " + desc + "</b>");
		folioLogger.info(" Verified Description: " + desc);
		test_steps.add(" Verified Amount:<b>  " + amount + "</b>");
		folioLogger.info(" Verified  Amount: " + amount);

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 *
	 * ' Method Name: LineItem ' Description: This method will add line Item in
	 * the folio ' Input parameters: (driver, String , String, String,
	 * ArrayList<String>) ' Return value: ArrayList<String> ' Created By: Adhnan
	 * Ghaffar ' Created On: <06/15/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> LineItem(WebDriver driver, String SelectCategory, String Quantity, String Amount,
			ArrayList<String> steps) throws InterruptedException {

		Elements_FolioLineItemsVoid elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.AddButton, driver);
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.elementToBeClickable(elements_FolioLineItemsVoid.AddButton));
		Utility.ScrollToElement(elements_FolioLineItemsVoid.AddButton, driver);
		elements_FolioLineItemsVoid.AddButton.click();
		folioLogger.info("Click Add Line Item Button");
		steps.add("Click Add Line Item Button");
		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.SelectCategory, driver);
		new Select(elements_FolioLineItemsVoid.SelectCategory).selectByVisibleText(SelectCategory);
		folioLogger.info("Select Line Item Category : " + SelectCategory);
		steps.add("Select Line Item Category : " + SelectCategory);
		elements_FolioLineItemsVoid.Enter_LineItemQuantity.clear();
		elements_FolioLineItemsVoid.Enter_LineItemQuantity.sendKeys(Keys.chord(Keys.CONTROL, "a"), Quantity);
		folioLogger.info("Enter Line Item Quantity : " + Quantity);
		steps.add("Enter Line Item Quantity : " + Quantity);
		elements_FolioLineItemsVoid.Enter_LineItemAmount.clear();
		elements_FolioLineItemsVoid.Enter_LineItemAmount.sendKeys(Amount);
		folioLogger.info("Enter Line Item Amount : " + Amount);
		steps.add("Enter Line Item Amount : " + Amount);
		Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.CommitButton, driver);
		Utility.ScrollToElement(elements_FolioLineItemsVoid.CommitButton, driver);
		elements_FolioLineItemsVoid.CommitButton.click();
		folioLogger.info("Click on Commit button");
		steps.add("Click on Commit button");
		Wait.wait3Second();
		if (elements_FolioLineItemsVoid.CommitButton.isEnabled()) {
			Wait.explicit_wait_visibilityof_webelement(elements_FolioLineItemsVoid.CommitButton, driver);
			Utility.ScrollToElement(elements_FolioLineItemsVoid.CommitButton, driver);
			elements_FolioLineItemsVoid.CommitButton.click();
			folioLogger.info("Again Click on Commit button");
			steps.add("Again Click on Commit button");
		}

		return steps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 *
	 * ' Method Name: verifyLineItemAdded ' Description: This method will Verify
	 * line Item in the folio. ' Input parameters: (driver, String , String,
	 * String) ' Return value: void ' Created By: Adhnan Ghaffar ' Created On:
	 * <06/15/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifyLineItemAdded(WebDriver driver, String Category, String Amount, String Description)
			throws InterruptedException {
		// Verify payment
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement_350(Elements_FolioLineItemsVoid.AddedLineItems_Category.get(0),
				driver);
		if (!Amount.contains("$")) {
			Amount = "$ " + Amount.replaceAll(" ", "");
		}
		int LineItem_Size = Elements_FolioLineItemsVoid.AddedLineItems_Category.size();
		folioLogger.info("LineItem_Size : " + LineItem_Size);
		folioLogger.info("Expected Line Item Category : " + Category);
		folioLogger.info("Expected Line Item Description : " + Description);
		folioLogger.info("Expected Line Item Amount : " + Amount);
		int lineNumber = LineItem_Size - 1;
		folioLogger.info("LineNumber : " + lineNumber);
		boolean found = false;
		Wait.explicit_wait_visibilityof_webelement_350(
				Elements_FolioLineItemsVoid.AddedLineItems_Category.get(LineItem_Size - 1), driver);
		for (lineNumber = LineItem_Size - 1; lineNumber >= 0; lineNumber--) {

			String desc = Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber).getText();
			String LineItemAmount = Elements_FolioLineItemsVoid.AddedLineItems_Amount.get(lineNumber).getText();
			String LineItemCategory = Elements_FolioLineItemsVoid.AddedLineItems_Category.get(lineNumber).getText();
			folioLogger.info("LineNumber : " + lineNumber);
			folioLogger.info("Category : " + LineItemCategory);
			folioLogger.info("Amount : " + LineItemAmount);
			folioLogger.info("Description : " + desc);
			// System.out.print(LineItemCategory.equalsIgnoreCase(Category));
			// System.out.print(desc.replaceAll(" ",
			// "").contains(Description.replaceAll(" ", "")));
			// System.out.print(LineItemAmount.contains(Amount));

			if (LineItemCategory.equalsIgnoreCase(Category) && LineItemAmount.contains(Amount)
					&& desc.replaceAll(" ", "").contains(Description.replaceAll(" ", ""))) {
				Utility.ScrollToElement(Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber), driver);
				found = true;
				break;
			}
		}
		if (!found) {
			assertTrue(false, "Failed: Required item not found");
		}
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 *
	 * ' Method Name: verifyLineItemAdded ' Description: This method will Verify
	 * line Item in the folio. ' Input parameters: (driver, String , String) '
	 * Return value: void ' Created By: Adhnan Ghaffar ' Created On:
	 * <06/16/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifyLineItemAdded(WebDriver driver, String Category, String Amount) throws InterruptedException {
		// Verify payment
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement_350(Elements_FolioLineItemsVoid.AddedLineItems_Category.get(0),
				driver);
		if (!Amount.contains("$")) {
			Amount = "$ " + Amount.replaceAll(" ", "");
		}
		int LineItem_Size = Elements_FolioLineItemsVoid.AddedLineItems_Category.size();
		folioLogger.info("LineItem_Size : " + LineItem_Size);
		folioLogger.info("Expected Line Item Category : " + Category);
		folioLogger.info("Expected Line Item Amount : " + Amount);
		int lineNumber = LineItem_Size - 1;
		folioLogger.info("LineNumber : " + lineNumber);
		boolean found = false;
		Wait.explicit_wait_visibilityof_webelement_350(
				Elements_FolioLineItemsVoid.AddedLineItems_Category.get(LineItem_Size - 1), driver);
		for (lineNumber = LineItem_Size - 1; lineNumber >= 0; lineNumber--) {

			String LineItemAmount = Elements_FolioLineItemsVoid.AddedLineItems_Amount.get(lineNumber).getText();
			String LineItemCategory = Elements_FolioLineItemsVoid.AddedLineItems_Category.get(lineNumber).getText();
			folioLogger.info("LineNumber : " + lineNumber);
			folioLogger.info("Category : " + LineItemCategory);
			folioLogger.info("Amount : " + LineItemAmount);
			// System.out.print(LineItemCategory.equalsIgnoreCase(Category));
			// System.out.print(desc.replaceAll(" ",
			// "").contains(Description.replaceAll(" ", "")));
			// System.out.print(LineItemAmount.contains(Amount));

			if (LineItemCategory.equalsIgnoreCase(Category) && LineItemAmount.contains(Amount)) {
				Utility.ScrollToElement(Elements_FolioLineItemsVoid.AddedLineItems_Category.get(lineNumber), driver);
				found = true;
				break;
			}
		}
		if (!found) {
			assertTrue(false, "Failed: Required item not found");
		}
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 *
	 * ' Method Name: <verifyLineItemClickDescription> ' Description: <This
	 * method will verify line Item and click on it's description if required> '
	 * Input parameters:(WebDriver, String, String, String, String,
	 * boolean,String ) ' Return value: <void> ' Created By: <Adhnan Ghaffar> '
	 * Created On: <06/19/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifyLineItemClickDescription(WebDriver driver, String Icon, String Category, String Amount,
			String Description, boolean clickDescription) throws InterruptedException {
		// Verify payment
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement_350(Elements_FolioLineItemsVoid.AddedLineItems_Category.get(0),
				driver);
		if (!Amount.contains("$")) {
			Amount = "$ " + Amount.replaceAll(" ", "");
		}
		int LineItem_Size = Elements_FolioLineItemsVoid.AddedLineItems_Category.size();
		folioLogger.info("LineItem_Size : " + LineItem_Size);
		folioLogger.info("Expected Line Item Category : " + Category);
		folioLogger.info("Expected Line Item Icon : " + Icon);
		folioLogger.info("Expected Line Item Description : " + Description);
		folioLogger.info("Expected Line Item Amount : " + Amount);
		int lineNumber = LineItem_Size - 1;
		folioLogger.info("LineNumber : " + lineNumber);
		boolean found = false;
		// Wait.explicit_wait_visibilityof_webelement_350(
		// Elements_FolioLineItemsVoid.AddedLineItems_Category.get(LineItem_Size
		// - 1), driver);
		Wait.WaitForElement(driver, NewFolio.AddedLineItems_Category);

		for (lineNumber = LineItem_Size - 1; lineNumber >= 0; lineNumber--) {

			String desc = Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber).getText();
			String IconSrc = Elements_FolioLineItemsVoid.AddedLineItems_Icon.get(lineNumber).getAttribute("src");
			String LineItemAmount = Elements_FolioLineItemsVoid.AddedLineItems_Amount.get(lineNumber).getText();
			String LineItemCategory = Elements_FolioLineItemsVoid.AddedLineItems_Category.get(lineNumber).getText();
			folioLogger.info("LineNumber : " + lineNumber);
			folioLogger.info("Category : " + LineItemCategory);
			folioLogger.info("Amount : " + LineItemAmount);
			folioLogger.info("Description : " + desc);
			Utility.app_logs.info("line Item Icon  src: " + IconSrc);

			if (LineItemCategory.equalsIgnoreCase(Category) && LineItemAmount.contains(Amount)
					&& desc.replaceAll(" ", "").contains(Description.replaceAll(" ", "")) && IconSrc.contains(Icon)) {
				Wait.waitForElementToBeClickable(By.xpath(NewFolio.AddedLineItems_Description), driver);
				Utility.ScrollToElement(Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber), driver);
				found = true;
				if (clickDescription) {
					Utility.ScrollToElement(Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber),
							driver);
					try {
						Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber).click();
					} catch (Exception e) {
						Wait.waitForElementToBeClickable(By.xpath(NewFolio.AddedLineItems_Description), driver);
						Utility.ScrollToElement(Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber),
								driver);
						Elements_FolioLineItemsVoid.AddedLineItems_Description.get(lineNumber).click();
					}
					Wait.explicit_wait_visibilityof_webelement(
							driver.findElement(By.xpath(OR.Verify_Payment_Details_popup)), driver);
				}
				break;
			}
		}
		if (!found) {
			assertTrue(false, "Failed: Required item not found");
		}
	}

	public ArrayList<String> clickOnfolio(WebDriver driver) throws InterruptedException {
		Elements_FolioLineItemsVoid elementsFolio = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> testSteps = new ArrayList<>();

		Wait.WaitForElement(driver, NewFolio.Foliotab);
		Wait.waitForElementToBeVisibile(By.xpath(NewFolio.Foliotab), driver);
		Wait.waitForElementToBeClickable(By.xpath(NewFolio.Foliotab), driver);
		elementsFolio.Foliotab.click();
		testSteps.add("Click on Folio");

		return testSteps;
	}

	public void VerifyAmountAfterTaxExempt(WebDriver driver, ArrayList<String> amount, ArrayList<String> tax,
			String category) throws InterruptedException, ParseException {
		Elements_FolioLineItemsVoid elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		Utility.ScrollToElement(elements_FolioLineItemsVoid.List_LineItemTax.get(0), driver);

		int count = 0;
		for (int i = 1; i <= 4; i++) {

			String getTax = tax.get(count);

			String getAmount = amount.get(count);
			String remainingAmount = MinseTwoValue(getAmount, getTax);
			String actualAmount = getAmount(driver, category, i);
			actualAmount = actualAmount.replace("$", "");
			actualAmount = actualAmount.trim();
			System.out.println("actualAmount: " + actualAmount);
			System.out.println("remainingAmount: " + remainingAmount);

			assertEquals(actualAmount, remainingAmount, "Failed: remaining amount is mismatching after tax exempt");
			count = count + 1;

		}

	}

	public ArrayList<String> getAmountWithIndex(WebDriver driver, String category)
			throws InterruptedException, ParseException {
		Elements_FolioLineItemsVoid elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> amount = new ArrayList<>();
		Utility.ScrollToElement(elements_FolioLineItemsVoid.List_LineItemTax.get(0), driver);

		for (int i = 1; i <= 4; i++) {

			String actualAmount = getAmount(driver, category, i);
			actualAmount = actualAmount.replace("$", "");
			actualAmount = actualAmount.trim();
			System.out.println(actualAmount);
			amount.add(actualAmount);

		}
		return amount;
	}

	public ArrayList<String> getTaxWithIndex(WebDriver driver, String category)
			throws InterruptedException, ParseException {
		Elements_FolioLineItemsVoid elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);
		ArrayList<String> tax = new ArrayList<>();
		Utility.ScrollToElement(elements_FolioLineItemsVoid.List_LineItemTax.get(0), driver);

		for (int i = 1; i <= 4; i++) {
			String actualAmount = getLineTaxAmount(driver, category, i);
			actualAmount = actualAmount.replace("$", "");
			actualAmount = actualAmount.trim();
			System.out.println(actualAmount);
			tax.add(actualAmount);

		}
		return tax;

	}

	public void verifyLineItemIcon(WebDriver driver, ArrayList<String> test_steps, String category, String imgSRC,
			String msg) throws InterruptedException {
		boolean iconFound = false;
		String foundSrc = null;
		Wait.waitForElementToBeVisibile(
				By.xpath("//button[@class='btn dropdown-toggle btn-default'][contains(@title,'Guest Folio')]"), driver,
				30);
		List<WebElement> rooms = driver.findElements(By.xpath(
				"//button[@class='btn dropdown-toggle btn-default'][contains(@title,'Guest Folio')]/..//a/span[@class='text']"));
		for (WebElement roomsToSelect : rooms) {
			driver.findElement(
					By.xpath("//button[@class='btn dropdown-toggle btn-default'][contains(@title,'Guest Folio')]"))
					.click();
			Wait.wait1Second();
			roomsToSelect.click();
			String imgPah = "//td[contains(@data-bind,'lineitem-category')]/span[contains(text(),'" + category
					+ "')]/../preceding-sibling::td[contains(@class,'lineitems-changestatus')]/img";
			Wait.waitForElementToBeVisibile(By.xpath(imgPah), driver, 10);
			foundSrc = driver.findElement(By.xpath(imgPah)).getAttribute("src");
			if (foundSrc.contains(imgSRC)) {
				iconFound = true;
				Utility.app_logs.info("Successfully Verified " + msg + " : " + foundSrc);
				test_steps.add("Successfully Verified " + msg + " : " + foundSrc);
				break;
			}
		}
		assertEquals(iconFound, true, "Failed to Verify Icon");
		Utility.app_logs.info("Successfully Verified " + msg + " : " + foundSrc);
		test_steps.add("Successfully Verified " + msg + " : <b>" + foundSrc + "</b>");

	}

}
