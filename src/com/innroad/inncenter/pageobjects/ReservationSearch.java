
package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.innroad.inncenter.interfaces.IReservationSearchPage;
import com.innroad.inncenter.pages.NewReservation;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Reservation;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_MovieFolio;
import com.innroad.inncenter.webelements.Elements_CPReservation;
import com.innroad.inncenter.webelements.Elements_FolioLineItemsVoid;
import com.innroad.inncenter.webelements.Elements_Reservation;
import com.innroad.inncenter.webelements.Elements_Reservation_SearchPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReservationSearch implements IReservationSearchPage {

	ArrayList<String> test_steps = new ArrayList<>();
	public String ResNumber;
	public static String ReservationNumber;
	public static Logger resSearchLogger = Logger.getLogger("ReservationSearch");

	public void basicSearch_WithGuestName(WebDriver driver, String GuestName) throws InterruptedException {
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		resservationSearch.BasicGuestName.sendKeys(GuestName);
		resSearchLogger.info("Entered the reservation number for basic search");
		resservationSearch.Click_BasicSearch.click();
		resSearchLogger.info("Clicked on Search Button");
		Wait.explicit_wait_xpath(OR.Verify_Search_Loading_Gird, driver);
		Wait.wait10Second();
		String GetGuestName = resservationSearch.Get_Guest_Name.getAttribute("title");
		Assert.assertEquals(GuestName, GetGuestName);

	}

	public ArrayList<String> basicSearch_WithGuestName(WebDriver driver, String GuestName,
			ArrayList<String> getTest_Steps) throws InterruptedException {
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		resservationSearch.BasicGuestName.sendKeys(GuestName);
		resSearchLogger.info("Entered the reservation number for basic search");
		resservationSearch.Click_BasicSearch.click();
		resSearchLogger.info("Clicked on Search Button");
		Wait.explicit_wait_xpath(OR.Verify_Search_Loading_Gird, driver);
		Wait.wait10Second();
		String GetGuestName = resservationSearch.Get_Guest_Name.getAttribute("title");
		Assert.assertEquals(GuestName, GetGuestName);
		return getTest_Steps;

	}

	public void basicSearch_WithResNumber(WebDriver driver) throws InterruptedException {

		try {
			FileReader fr = new FileReader(".\\ConfirmationNumber.txt");
			BufferedReader br = new BufferedReader(fr);

			while ((ResNumber = br.readLine()) != null) {
				// resSearchLogger.info("ResNumber :" + ResNumber);
				Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
				resSearchLogger.info("ResNumber after loop:" + ResNumber);
				resservationSearch.Basic_Res_Number.sendKeys(ResNumber);
				resservationSearch.Click_BasicSearch.click();
				Wait.explicit_wait_xpath(OR.Verify_Search_Loading_Gird, driver);
				Wait.wait10Second();
				String GetResNumber = resservationSearch.Get_Res_Number.getText();
				Assert.assertEquals(ResNumber, GetResNumber);
			}
			br.close();
		} catch (IOException e) {
			// System.out.println("File not found");
		}

	}

	public String basicSearch_WithResNumber(WebDriver driver, String resNumber) throws InterruptedException {
		String str = null;

		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		Wait.explicit_wait_visibilityof_webelement(resservationSearch.Basic_Res_Number, driver);
		resservationSearch.Basic_Res_Number.click();
		test_steps.add("Click on Reservation Number Text Box");
		resSearchLogger.info("Click on Reservation Number Text Box");
		resservationSearch.Basic_Res_Number.clear();
		// Wait.wait2Second();

		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		resservationSearch.Basic_Res_Number.sendKeys(resNumber);
		resservationSearch.Click_BasicSearch.click();
		Wait.explicit_wait_xpath(OR.Verify_Search_Loading_Gird, driver);
		// Wait.wait10Second();

		Wait.WaitForElement(driver, OR.Get_Res_Number);
		// Wait.explicit_wait_visibilityof_webelement_350(resservationSearch.Get_Res_Number,
		// driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Get_Res_Number, driver);
		String GetResNumber = resservationSearch.Get_Res_Number.getText();
		System.out.println(GetResNumber);
		if (GetResNumber == "") {
			Wait.explicit_wait_visibilityof_webelement(resservationSearch.Get_Res_Number, driver);
			GetResNumber = resservationSearch.Get_Res_Number.getText();
			System.out.println(GetResNumber);
		}
		Assert.assertEquals(resNumber, GetResNumber);

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.NewRervations)));
		// System.out.println(resNumber);

		String resLocator = "//span[contains(text(),'" + resNumber.trim() + "')]/../../td[4]/div/a";

		Wait.WaitForElement(driver, resLocator);
		str = driver.findElement(By.xpath(resLocator)).getText();

		WebElement ReservationFound = driver.findElement(By.xpath(resLocator));
		ReservationFound.click();

		// driver.navigate().refresh();
		// Utility.ElementFinderUntillNotShow(By.xpath(OR.Reservation_market_Segment),
		// driver);

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation);

		return str;
	}

	public String basicSearch_WithReservationNumber(WebDriver driver, String resNumber) throws InterruptedException {
		String str = null;

		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(resservationSearch.Basic_Res_Number, driver);
		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		resservationSearch.Basic_Res_Number.clear();
		// Wait.wait2Second();

		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		resservationSearch.Basic_Res_Number.sendKeys(resNumber);
		resservationSearch.Click_BasicSearch.click();
		Wait.explicit_wait_xpath(OR.Verify_Search_Loading_Gird, driver);
		// Wait.wait10Second();

		Wait.WaitForElement(driver, OR.Get_Res_Number);
		// Wait.explicit_wait_visibilityof_webelement_350(resservationSearch.Get_Res_Number,
		// driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Get_Res_Number, driver);
		String GetResNumber = resservationSearch.Get_Res_Number.getText();
		System.out.println(GetResNumber);
		if (GetResNumber == "") {
			Wait.explicit_wait_visibilityof_webelement(resservationSearch.Get_Res_Number, driver);
			GetResNumber = resservationSearch.Get_Res_Number.getText();
			System.out.println(GetResNumber);
		}
		Assert.assertEquals(resNumber, GetResNumber);

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.NewRervations)));
		// System.out.println(resNumber);

		String resLocator = "//span[contains(text(),'" + resNumber.trim() + "')]/../../td[4]/div/a";

		Wait.WaitForElement(driver, resLocator);
		str = driver.findElement(By.xpath(resLocator)).getText();

		WebElement ReservationFound = driver.findElement(By.xpath(resLocator));

		return str;
	}

	public ArrayList<String> basicSearch_WithResNumber(WebDriver driver, ArrayList<String> getTest_Steps)
			throws InterruptedException {

		try {
			FileReader fr = new FileReader(".\\ConfirmationNumber.txt");
			BufferedReader br = new BufferedReader(fr);

			while ((ResNumber = br.readLine()) != null) {
				// resSearchLogger.info("ResNumber :" + ResNumber);
				Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
				resSearchLogger.info("ResNumber after loop:" + ResNumber);
				resservationSearch.Basic_Res_Number.sendKeys(ResNumber);
				resservationSearch.Click_BasicSearch.click();
				Wait.explicit_wait_xpath(OR.Verify_Search_Loading_Gird, driver);
				Wait.wait10Second();
				String GetResNumber = resservationSearch.Get_Res_Number.getText();
				Assert.assertEquals(ResNumber, GetResNumber);
			}
			br.close();
		} catch (IOException e) {
			// System.out.println("File not found");
		}
		return getTest_Steps;

	}

	public String departureDate(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.DepartureDate);

		String getDepartureDate = resservationSearch.DepartureDate.getText();
		int departdate = Integer.parseInt(getDepartureDate.substring(4, 6));

		String departure_Date = Integer.toString(departdate);

		return departure_Date;

	}

	public void BulkCheckOutReservationStatus(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		if (resservationSearch.Get_Reservation_Status.getText().equals("Departed")) {
			test_steps.add("Reservation Status: Departed");
			resSearchLogger.info("Bulk Checkout is Successful");

		} else {
			test_steps.add("Failed to Checkout");
			resSearchLogger.info("Fail to Checkout");
		}

	}

	public String pendingDepartures(WebDriver driver, String DepartureDate, ExtentTest test)
			throws InterruptedException {
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.DateIcon);
		resservationSearch.DateIcon.click();
		String depart_Date = "//div[@class='datepicker-days']//*[text()='" + DepartureDate + "']";

		driver.findElement(By.xpath(depart_Date)).click();

		System.out.println(" Clicked on Departure date " + DepartureDate);

		// Click on All departures

		Wait.WaitForElement(driver, OR.allDepartures);

		resservationSearch.allDepartures.click();
		resSearchLogger.info(" Clicked on All Departures ");

		// Get the count of Pending departures
		// Utility.ScrollToElement( resservationSearch.pendingDepartures,
		// driver);
		Wait.WaitForElement(driver, OR.pendingDepartures);
		Wait.wait2Second();
		Utility.ScrollToElement(resservationSearch.pendingDepartures, driver);
		String pendingDeparturesCount = resservationSearch.pendingDepartures.getText();
		Wait.wait10Second();
		// resSearchLogger.info(" Pending departures count before checkout "
		// +pendingDeparturesCount);
		return pendingDeparturesCount;

	}

	public void basicSearch_WithResNumber(WebDriver driver, String resNumber, boolean open)
			throws InterruptedException {
		String str = null;
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		Elements_Reservation res = new Elements_Reservation(driver);
		Reservation reserv = new Reservation();
		Wait.wait5Second();
		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		Wait.explicit_wait_visibilityof_webelement_350(resservationSearch.Basic_Res_Number, driver);
		resservationSearch.Basic_Res_Number.clear();
		resservationSearch.Basic_Res_Number.sendKeys(resNumber);
		resservationSearch.Click_BasicSearch.click();
		Wait.WaitForElement(driver, OR.Verify_Search_Loading_Gird);
		Wait.wait2Second();
		Wait.WaitForElement(driver, OR.Get_Res_Number);
		String GetResNumber = resservationSearch.Get_Res_Number.getText();
		assertEquals(GetResNumber, resNumber, "Failed: Reservatio Found");
		String resLocator = "//span[contains(text(),'" + resNumber.trim() + "')]/../../td[4]/div/a";
		WebElement ReservationFound = driver.findElement(By.xpath(resLocator));
		if (open) {
			try {
				Wait.explicit_wait_visibilityof_webelement(ReservationFound, driver);
				ReservationFound.click();
				Wait.explicit_wait_visibilityof_webelement(res.Reservation_market_Segment, driver);
				Utility.app_logs.info("Reservation Opened Successfully");
			} catch (Exception e) {
				reserv.CloseOpenedReservation(driver);
				Utility.app_logs.info("Close reservation");
				Wait.explicit_wait_visibilityof_webelement(ReservationFound, driver);
				ReservationFound.click();
				Wait.explicit_wait_visibilityof_webelement(res.Reservation_market_Segment, driver);
				Utility.app_logs.info("Reservation Open Successfully Afetr Closing");
			}
		}
	}

	public void getGuestNameInSearchResult(WebDriver driver, ExtentTest test) {
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		String guestNameInResult = "(//a[.='" + Reservation.nameGuest + "'])[1]";

		String guestnameInResult = driver.findElement(By.xpath(guestNameInResult)).getText();

		resSearchLogger.info("Displayed Guest Name after Search: " + guestnameInResult);

		if (Reservation.nameGuest.equalsIgnoreCase(guestnameInResult)) {

			resSearchLogger.info("Guest Name is Same");

		}

		else {

			resSearchLogger.info("Guest Name is not Same");
		}
	}

	public void basicSearch_WithInvalidGuestName(WebDriver driver, String Username, ExtentTest test)
			throws InterruptedException {
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		resservationSearch.BasicGuestName.clear();
		resservationSearch.BasicGuestName.sendKeys(Username);
		resSearchLogger.info("Entered the reservation number for basic search");
		resservationSearch.Click_BasicSearch.click();
		resSearchLogger.info("Clicked on Search Button");
		Wait.WaitForElement(driver, OR.noRecordAlertMessage);
		// Wait.explicit_wait_xpath(OR.Verify_Search_Loading_Gird, driver);
		Wait.wait10Second();

		String alertMessage = resservationSearch.noRecordAlertMessage.getText();
		if (alertMessage.contains("No records meet your criteria. Please change your criteria and search again.")) {

			resSearchLogger.info(
					"Searched Invalid Guest Name and displayed message: No records meet your criteria. Please change your criteria and search again.");

			test_steps.add(
					"Searched Invalid Guest Name and displayed message: No records meet your criteria. Please change your criteria and search again.");
		}

		else {

			resSearchLogger.info("Search for Invalid Guest Name failed");

		}

	}

	public void SearchAndOpenRes(WebDriver driver, String ReservationNumber) throws InterruptedException {

		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);

		// driver.navigate().refresh();
		// Wait.wait2Second();
		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		resservationSearch.Basic_Res_Number.clear();
		resservationSearch.Basic_Res_Number.sendKeys(ReservationNumber);
		resservationSearch.Click_BasicSearch.click();
		Wait.WaitForElement(driver, OR.Verify_Search_Loading_Gird);

		Wait.WaitForElement(driver, OR.Get_Res_Number);
		// Wait.wait2Second();
		Utility.ElementFinderUntillNotShow(By.xpath(OR.Get_Res_Number), driver);
		Wait.explicit_wait_visibilityof_webelement_200(resservationSearch.Get_Res_Number, driver);
		String GetResNumber = resservationSearch.Get_Res_Number.getText();

		assertEquals(GetResNumber, ReservationNumber, "Failed: Searched Reservation Number missmatched");
		String resLocator = "//span[contains(text(),'" + ReservationNumber.trim() + "')]/../../td[4]/div/a";
		Wait.WaitForElement(driver, resLocator);
		driver.findElement(By.xpath(resLocator)).click();
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation);
	}

	public void basicSearch_ResNumber(WebDriver driver, String resNumber) throws InterruptedException {

		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(resservationSearch.Basic_Res_Number, driver);
		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		resservationSearch.Basic_Res_Number.clear();

		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		resservationSearch.Basic_Res_Number.sendKeys(resNumber);
		resservationSearch.Click_BasicSearch.click();
		Wait.explicit_wait_xpath(OR.Verify_Search_Loading_Gird, driver);

		Wait.WaitForElement(driver, OR.Get_Res_Number);
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement_350(resservationSearch.Get_Res_Number, driver);
		Utility.ScrollToElement(resservationSearch.Get_Res_Number, driver);
		String GetResNumber = resservationSearch.Get_Res_Number.getText();
		Wait.wait3Second();
		System.out.println("NUMBER:" + GetResNumber + " Newres" + resNumber);
		Assert.assertEquals(resNumber, GetResNumber);

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.NewRervations)));
		System.out.println("ResNumber:" + resNumber);

	}

	public void basicSearchResNumber(WebDriver driver, String resNumber) throws InterruptedException {

		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);

		Wait.wait2Second();
		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		resservationSearch.Basic_Res_Number.clear();

		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		resservationSearch.Basic_Res_Number.sendKeys(resNumber);
		resservationSearch.Click_BasicSearch.click();
		Wait.explicit_wait_xpath(OR.Verify_Search_Loading_Gird, driver);

		Wait.WaitForElement(driver, OR.Get_Res_Number);
		Wait.explicit_wait_visibilityof_webelement_150(resservationSearch.Get_Res_Number, driver);
		String GetResNumber = resservationSearch.Get_Res_Number.getText();
		Wait.wait3Second();
		System.out.println("NUMBER:" + GetResNumber + " Newres" + resNumber);
		Assert.assertEquals(resNumber, GetResNumber);

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.NewRervations)));
		System.out.println("ResNumber:" + resNumber);

	}

	public void basicSearch_WithResNumber1(WebDriver driver, String resNumber) throws InterruptedException {
		String str = null;
		try {
			/*
			 * FileReader fr= new FileReader(".\\ConfirmationNumber.txt");
			 * BufferedReader br = new BufferedReader(fr);
			 * 
			 * while((ResNumber=br.readLine())!=null) {
			 * System.out.println("ResNumber :"+resNumber);
			 * Elements_Reservation_SearchPage resservationSearch = new
			 * Elements_Reservation_SearchPage(driver);
			 * System.out.println("ResNumber after loop:" + resNumber);
			 * resservationSearch.Basic_Res_Number.sendKeys(resNumber);
			 * resservationSearch.Click_BasicSearch.click();
			 * Wait.explicit_wait_xpath(OR.Verify_Search_Loading_Gird);
			 * Wait.wait10Second(); String
			 * GetResNumber=resservationSearch.Get_Res_Number.getText();
			 * Assert.assertEquals(resNumber, GetResNumber); } br.close();
			 */

			// Explicit wait object creation
			WebDriverWait wait = new WebDriverWait(driver, 90);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.NewRervations)));

			String resLocator = "//span[contains(text(),'" + resNumber.trim() + "')]/../../td[4]/div/a";
			// Thread.sleep(5000);

			/*
			 * Elements_MovieFolio moveFolio = new Elements_MovieFolio(driver);
			 * moveFolio.NewRervations.click();
			 */

			str = driver.findElement(By.xpath(resLocator)).getText();

			driver.findElement(By.xpath(resLocator)).click();
		} catch (Exception e) {
			// System.out.println("File not found");
		}

	}

	public ArrayList<String> Bulkcheckin(WebDriver driver, String GuestName, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		// Wait.waitUntilPresenceOfElementLocated(OR.Check_Reservation);
		Wait.explicit_wait_visibilityof_webelement(resservationSearch.Check_Reservation, driver);
		Utility.ScrollToElement(resservationSearch.Check_Reservation, driver);
		resservationSearch.Check_Reservation.click();
		test_steps.add("Selected Reservation");
		resSearchLogger.info("Reseravation is selected");
		Wait.waitUntilPresenceOfElementLocated(OR.Click_Bulk_Action, driver);
		resservationSearch.Click_Bulk_Action.click();
		test_steps.add("Click Bulk Action");
		resSearchLogger.info("Clicked on bulk action");
		resservationSearch.Select_Checkin.click();
		test_steps.add("Clicked on bulk action - CHECKIN option");
		resSearchLogger.info("Clicked on bulk action - CHECKIN option");
		Wait.explicit_wait_visibilityof_webelement_120(resservationSearch.Verify_Bulk_Checkin_popup, driver);
		test_steps.add("Bulk CheckIn Popup is displayed");
		resSearchLogger.info("Bulk CheckIn Popup is displayed");

		/*
		 * String GetGuestName =
		 * resservationSearch.Verify_Guest_Name.getAttribute("title");
		 * resSearchLogger.info("GuestName  :" + GuestName); if
		 * (GetGuestName.equals(GuestName)) {
		 * resSearchLogger.info("Verified Guest Name"); } else {
		 * resSearchLogger.info("Failed to verify Guest Name"); }
		 */
		Wait.explicit_wait_visibilityof_webelement_120(resservationSearch.Click_Process_Button, driver);
		resservationSearch.Click_Process_Button.click();
		test_steps.add("Clicked on Process button");
		resSearchLogger.info("Clicked on Process button");
		Wait.explicit_wait_xpath(OR.Verify_Bulk_Checin_Completed, driver);
		Wait.wait5Second();
		resservationSearch.click_on_Close_icon.click();
		test_steps.add("Clicked on Close button");
		resSearchLogger.info("Clicked on Close button");
		Wait.wait5Second();
		Wait.explicit_wait_visibilityof_webelement(resservationSearch.Get_Reservation_Status, driver);
		assertTrue(resservationSearch.Get_Reservation_Status.getText().equals("In-House"),
				"Reservation Status is not changed to In-House after bulk checkin");

		if (resservationSearch.Get_Reservation_Status.getText().equals("In-House")) {
			test_steps.add("Reservation Status: In House");
			resSearchLogger.info("In House Success");

		} else {
			test_steps.add("Failed to Checkin");
			resSearchLogger.info("Fail to checkin");
		}
		return test_steps;

	}

	public void bulkCheckInWithZeroBal(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		resservationSearch.Check_Reservation.click();
		resSearchLogger.info("Reseravation is selected");
		resservationSearch.Click_Bulk_Action.click();
		resSearchLogger.info("Clicked on bulk action");
		resservationSearch.Select_Checkin.click();
		resSearchLogger.info("Clicked on bulk action - CHECKIN option");
		// Wait.WaitForElement(driver, OR.Verify_Bulk_Checkin_popup);
		Wait.explicit_wait_visibilityof_webelement(resservationSearch.Verify_Bulk_Checkin_popup, driver);
		String GetGuestName = resservationSearch.Verify_Guest_Name.getAttribute("title");
		resSearchLogger.info("GuestName  :" + GetGuestName);
		/*
		 * if (GetGuestName.equals(GuestName)) {
		 * resSearchLogger.info("Verified Guest Name"); } else {
		 * resSearchLogger.info("Failed to verify Guest Name"); }
		 */
		// Wait.wait5Second();
		Wait.explicit_wait_visibilityof_webelement_120(resservationSearch.Click_Process_Button, driver);
		resservationSearch.Click_Process_Button.click();
		resSearchLogger.info("Clicked on Process button");
		Wait.WaitForElement(driver, OR.Verify_Bulk_Checin_Completed);
		resservationSearch.click_on_Close_icon.click();
		resSearchLogger.info("Clicked on Close button");
		Wait.wait5Second();
		searchReservation(driver);
		String getBulkCheckInStatus = resservationSearch.Get_Reservation_Status.getText();
		Assert.assertEquals(getBulkCheckInStatus, "In-House");
		/*
		 * if (resservationSearch.Get_Reservation_Status.getText().equals(
		 * "In-House")) { resSearchLogger.info("In House Success"); } else {
		 * resSearchLogger.info("Fail to checkin"); }
		 */

	}

	String Negative_Process_Amount;

	public ArrayList<String> Bulkcheckout(WebDriver driver, String ReservationNumber, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.explicit_wait_visibilityof_webelement(resservationSearch.Check_Reservation, driver);
		Utility.ScrollToElement(resservationSearch.Check_Reservation, driver);
		resservationSearch.Check_Reservation.click();
		test_steps.add("Selected reservation");
		resSearchLogger.info("Reseravation is selected");
		resservationSearch.Click_Bulk_Action.click();
		test_steps.add("Clicked on Bulk Action");
		resSearchLogger.info("Clicked on bulk action");
		resservationSearch.Select_Checkout.click();
		test_steps.add("Clicked on bulk action - CHECKOUT option");
		resSearchLogger.info("Clicked on bulk action - CHECKOUT option");
		Wait.explicit_wait_xpath(OR.Verify_Bulk_Checkout_popup, driver);
		String Amount_Processed = resservationSearch.Verify_Amount_Value.getAttribute("value");
		System.out.println(Amount_Processed);
		Wait.wait5Second();
		float TotalAmount = Float.parseFloat(Amount_Processed);
		System.out.println(TotalAmount);
		Negative_Process_Amount = String.format("$ %.2f", TotalAmount);
		System.out.println(Negative_Process_Amount);
		test_steps.add("The Process Amount at the time of checkout is : " + Negative_Process_Amount);
		test_steps.add("Amount Before clicking on Process button : " + Negative_Process_Amount);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", resservationSearch.Click_Process_Button);
		test_steps.add("Clicked on Process button");
		resSearchLogger.info("Clicked on Process button");
		Wait.explicit_wait_xpath(OR.Verify_Bulk_Checkout_Completed, driver);
		String Closing_Amount = resservationSearch.Verify_Closing_Amount_Value.getAttribute("value");
		System.out.println(Closing_Amount);
		test_steps.add("Amount: " + Closing_Amount);
		// assertEquals(Amount_Processed, Closing_Amount,"Failed : Amount before
		// processing is not equal to Closing amount");
		// float TotalAmount = Float.parseFloat(Closing_Amount);
		// System.out.println(TotalAmount);
		// System.out.println("The Closing balance after payment is:" +
		// Closing_Amount);
		// test_steps.add("The Closing balance after payment is:" +
		// Closing_Amount);
		// Closing_Amount = String.format("$ %.2f", TotalAmount);
		// System.out.println(Closing_Amount);
		// test_steps.add(Closing_Amount);
		jse.executeScript("arguments[0].click();", resservationSearch.click_on_Close_icon);
		test_steps.add("Clicked on Close button");
		resSearchLogger.info("Clicked on Close button");
		return test_steps;
	}

	public ArrayList<String> VerificationWithNegativeBlncRes(WebDriver driver, String PaymentType, String Amount)
			throws InterruptedException, IOException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.explicit_wait_visibilityof_webelement(resservationSearch.McLineItemFolio, driver);
		Utility.ScrollToElement(resservationSearch.McLineItemFolio, driver);
		String FolioBalnc = resservationSearch.McLineItemFolio.getText();
		System.out.println(FolioBalnc);
		assertEquals(Negative_Process_Amount, FolioBalnc,
				"Failed : Amount before processing is not equal to Closing amount");
		test_steps.add("The amount at the time of checkout is same as in the folio balance : " + FolioBalnc);
		return test_steps;
	}

	public void searchResWithAdvancedSearch(WebDriver driver) {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		// reservationSearch.unassignedReservations.click();
		reservationSearch.advanced.click();
		Wait.explicit_wait_visibilityof_webelement_120(reservationSearch.advancedSearchStatus, driver);
		reservationSearch.advancedSearchStatus.click();
		reservationSearch.reservedStatus.click();
		Wait.explicit_wait_visibilityof_webelement_120(reservationSearch.searchButton, driver);
		Wait.WaitForElement(driver, OR.searchButton);
		reservationSearch.searchButton.click();
	}

	public void openReservation(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.WaitForElement(driver, OR.clickReservation);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", reservationSearch.clickReservation);
		assertTrue(ReservationPage.Reservation_Referral.isDisplayed(), "Res Page didn't display");
	}

	public void closeReservation(WebDriver driver) {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.explicit_wait_visibilityof_webelement(reservationSearch.closeReservation, driver);
		reservationSearch.closeReservation.click();

	}

	public void advanceSearchReservation(WebDriver driver) {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.searchButton, driver);
		reservationSearch.enterAdvResNumber.sendKeys(FolioLineItems.ReservationNumber);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", reservationSearch.searchButton);
		Wait.explicit_wait_visibilityof_webelement(reservationSearch.searchButton, driver);
		reservationSearch.searchButton.click();
	}

	public void searchReservation(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.basicSearchIcon, driver);
		reservationSearch.enterResNumber.clear();
		reservationSearch.enterResNumber.sendKeys(FolioLineItems.ReservationNumber);
		Wait.wait2Second();
		reservationSearch.basicSearchIcon.click();
		Wait.wait5Second();
		Wait.WaitForElement(driver, OR.Check_Reservation);
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.Check_Reservation, driver);
	}

	public void selectBulkCancel(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		// reservationSearch.Check_Reservation.click();
		Wait.wait2Second();
		driver.findElements(By.xpath("//div[@class='checker']")).get(0).click();
		Wait.wait2Second();
		resSearchLogger.info("Reservation is selected");
		assertTrue(reservationSearch.Click_Bulk_Action.isEnabled(), "Failed: Bulk Action is not Enabled");
		reservationSearch.Click_Bulk_Action.click();
		resSearchLogger.info("Clicked on bulk action");
		reservationSearch.selectCancel.click();

		resSearchLogger.info("Clicked on cancel ");
		Wait.WaitForElement(driver, OR.bulkCancelpopup);
		reservationSearch.enterCancellationReason.sendKeys("Reservation bulk Cancellation");
		Wait.explicit_wait_visibilityof_webelement(reservationSearch.processButton, driver);
		reservationSearch.processButton.click();

		resSearchLogger.info("Click process ");
		Wait.wait5Second();
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.bulkCancellationMessage, driver);
		String bulkCancelRes = reservationSearch.bulkCancellationMessage.getText();

		resSearchLogger.info(bulkCancelRes);
		Assert.assertEquals(bulkCancelRes, "Bulk Cancel Completed");
		reservationSearch.BulkPopupClose.click();
		resSearchLogger.info("Click Close ");
		Wait.wait5Second();
	}

	public void verifyBulkCancelWithoutVoidRoomCharges(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		searchReservation(driver);
		openReservation(driver);
		reservationSearch.click_Folio_tab.click();
		List<WebElement> rows = driver.findElements(By.xpath("(//table/tbody)[4]/tr"));
		resSearchLogger.info("No of rows : " + rows.size());
		assertTrue(!rows.isEmpty(), "RoomCharge Line item is voided which is inncorrect");
		Wait.wait10Second();
	}

	public void rollbackReservation(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.clickRollBackButton, driver);
		Utility.ScrollToElement(reservationSearch.clickRollBackButton, driver);
		reservationSearch.clickRollBackButton.click();
		// Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.roomAssignmentpopUpSelectButton);
		Utility.ElementFinderUntillNotShow(By.xpath(OR.roomAssignmentpopUpSelectButton), driver);
		Wait.WaitForElement(driver, OR.roomAssignmentpopUpSelectButton);
		reservationSearch.roomAssignmentpopUpSelectButton.click();
		Wait.wait10Second();
		Wait.WaitForElement(driver, OR.folioSaveButton);

		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.folioSaveButton, driver);
		Utility.ScrollToElement(reservationSearch.folioSaveButton, driver);
		reservationSearch.folioSaveButton.click();
		Wait.wait10Second();
		closeReservation(driver);
	}

	public void verifyBulkCancelWithVoidRoomCharges(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		searchReservation(driver);
		reservationSearch.Check_Reservation.click();
		resSearchLogger.info("Reservation is selected");
		reservationSearch.Click_Bulk_Action.click();
		resSearchLogger.info("Clicked on bulk action");
		reservationSearch.selectCancel.click();
		Wait.WaitForElement(driver, OR.bulkCancelpopup);
		reservationSearch.enterCancellationReason.sendKeys("Reservation bulk Cancellation");
		if (!reservationSearch.voidRoomChargesCheckBox.isSelected()) {

			reservationSearch.voidRoomChargesCheckBox.click();
			reservationSearch.processButton.click();
			Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.bulkCancellationMessage, driver);
			String bulkCancelRes = reservationSearch.bulkCancellationMessage.getText();
			Assert.assertEquals(bulkCancelRes, "Bulk Cancel Completed");
			reservationSearch.bulkPopupClose.click();
		}
		searchReservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.basicSearchcancelledReservation, driver);
		String bulkCancelledReservationStatus = reservationSearch.basicSearchcancelledReservation.getText();
		Assert.assertEquals(bulkCancelledReservationStatus, "Cancelled");
		openReservation(driver);
		Wait.WaitForElement(driver, OR.click_Folio_tab);
		reservationSearch.click_Folio_tab.click();
		Wait.WaitForElement(driver, OR.clickVoidButton);
		List<WebElement> rows = driver.findElements(By.xpath("((//table/tbody)[4]/tr)//child::*"));
		assertTrue(rows.isEmpty(), "RoomCharge Line item is NOT voided which is inncorrect");
		resSearchLogger.info("Folio Line Items after void roomCharges: " + rows.size());
		Wait.WaitForElement(driver, OR.clickVoidButton);
		/*
		 * if(!reservationSearch.clickVoidButton.isSelected()){
		 * 
		 * reservationSearch.clickVoidButton.click(); Wait.wait5Second(); }
		 * List<WebElement> rows2 =
		 * driver.findElements(By.xpath("(//table/tbody)[4]/tr"));
		 * resSearchLogger.info("Folio Line Items after void roomCharges: "
		 * +rows2.size());
		 */
	}

	public void bulkCancellation(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", reservationSearch.Check_Reservation);
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.Check_Reservation, driver);
		reservationSearch.Check_Reservation.click();
		resSearchLogger.info("Reservation is selected");
		reservationSearch.Click_Bulk_Action.click();
		resSearchLogger.info("Clicked on bulk action");
		reservationSearch.selectCancel.click();
		Wait.WaitForElement(driver, OR.bulkCancelpopup);
		reservationSearch.enterCancellationReason.sendKeys("Reservation bulk Cancellation");
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.processButton, driver);
		reservationSearch.processButton.click();
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.bulkCancellationMessage, driver);
		String bulkCancelRes = reservationSearch.bulkCancellationMessage.getText();
		Assert.assertEquals(bulkCancelRes, "Bulk Cancel Completed");
		reservationSearch.bulkPopupClose.click();
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.advancedSearchReservedStatus, driver);
		reservationSearch.advancedSearchReservedStatus.click();

		/*
		 * Utility.ScrollToElement(reservationSearch.reservedTocancelledStatus);
		 * ((JavascriptExecutor)
		 * driver).executeScript("window.scrollBy(0,-250)", "");
		 */

		Wait.explicit_wait_visibilityof_webelement(reservationSearch.reservedTocancelledStatus, driver);
		reservationSearch.reservedTocancelledStatus.click();
		// Wait.WaitForElement(driver, OR.searchButton);
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.searchButton, driver);
		reservationSearch.searchButton.click();
		// Wait.WaitForElement(driver, OR.cancelledReservation);
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.cancelledReservation, driver);

		String bulkCancelledReservationStatus = reservationSearch.cancelledReservation.getText();
		Assert.assertEquals(bulkCancelledReservationStatus, "Cancelled");
		resSearchLogger.info("Bulk Cancellation Successful");
		Wait.wait5Second();

	}

	public ArrayList<String> selectBulkNoShow(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		reservationSearch.Check_Reservation.click();
		resSearchLogger.info("Reservation is selected");
		test_steps.add("Reservation is selected");
		reservationSearch.Click_Bulk_Action.click();
		resSearchLogger.info("Clicked on bulk action");
		test_steps.add("Click on bulk action");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", reservationSearch.selectNoShow);
		reservationSearch.selectNoShow.click();
		test_steps.add("Select No Show option");
		Wait.WaitForElement(driver, OR.bulkNoShowpopup);
		assertTrue(reservationSearch.bulkNoShowpopup.getText().equals("Bulk No-Show"),
				"Bulk No-Show PopUp didn't display");
		test_steps.add("Bulk No-Show PoPup Appears");
		Wait.WaitForElement(driver, OR.processButton);
		reservationSearch.processButton.click();
		test_steps.add("Click on Process button");
		Wait.WaitForElement(driver, OR.bulkPopupClose);
		reservationSearch.BulkPopupClose.click();
		test_steps.add("Click on close button");
		Wait.WaitForElement(driver, OR.basicSearchIcon);
		reservationSearch.basicSearchIcon.click();
		test_steps.add("Click on Search Icon");
		Wait.wait2Second();
		String bulkNoShowReservationStatus = reservationSearch.basicSearchNoShowReservation.getText();
		Assert.assertEquals(bulkNoShowReservationStatus, "No-Show");
		test_steps.add("Status verified No-Show");
		Wait.wait5Second();
		return test_steps;
	}

	public ArrayList<String> BulkNoShow_Reservation(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		reservationSearch.Check_Reservation.click();
		resSearchLogger.info("Reservation is selected");
		test_steps.add("Reservation is selected");
		reservationSearch.Click_Bulk_Action.click();
		resSearchLogger.info("Clicked on bulk action");
		test_steps.add("Click on bulk action");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", reservationSearch.selectNoShow);
		reservationSearch.selectNoShow.click();
		test_steps.add("Select No Show option");
		Wait.WaitForElement(driver, OR.bulkNoShowpopup);
		assertTrue(reservationSearch.bulkNoShowpopup.getText().equals("Bulk No-Show"),
				"Bulk No-Show PopUp didn't display");
		test_steps.add("Bulk No-Show PoPup Appears");
		Wait.WaitForElement(driver, OR.processButton);
		reservationSearch.processButton.click();
		test_steps.add("Click on Process button");
		Wait.WaitForElement(driver, OR.bulkPopupClose);
		reservationSearch.BulkPopupClose.click();
		test_steps.add("Click on close button");
		return test_steps;
	}

	public void verifyBulkNoShowWithoutVoidRoomCharges(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		searchReservation(driver);
		openReservation(driver);
		reservationSearch.click_Folio_tab.click();
		List<WebElement> rows = driver.findElements(By.xpath("(//table/tbody)[4]/tr"));
		resSearchLogger.info("No of rows : " + rows.size());
		// assertTrue(!rows.isEmpty(), "RoomCharge Line item is voided which is
		// inncorrect");
		Wait.wait10Second();
	}

	public void verifyBulkNoShowInReservationPage(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		String ReservationStatus = reservationSearch.ReservationStatus_ReservationPage.getText();
		assertTrue(ReservationStatus.equals("No-Show"), "Reservation Sttaus isn't correct");
		resSearchLogger.info("No-Show Reservation Status Verified");

	}

	public void verifyBulkNoShowWithVoidRoomCharges(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		searchReservation(driver);
		reservationSearch.Check_Reservation.click();
		resSearchLogger.info("Reservation is selected");
		reservationSearch.Click_Bulk_Action.click();
		resSearchLogger.info("Clicked on bulk action");
		reservationSearch.selectNoShow.click();
		Wait.WaitForElement(driver, OR.bulkNoShowpopup);
		if (!reservationSearch.voidRoomChargesCheckBox.isSelected()) {

			Wait.WaitForElement(driver, OR.voidRoomChargesCheckBox);
			reservationSearch.voidRoomChargesCheckBox.click();
			Wait.explicit_wait_visibilityof_webelement(reservationSearch.processButton, driver);
			reservationSearch.processButton.click();
			Wait.wait5Second();
			Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.bulkNoShowMessage, driver);
			String bulkNoShowRes = reservationSearch.bulkNoShowMessage.getText();
			Assert.assertEquals(bulkNoShowRes, "Bulk No-Show Completed");
			reservationSearch.BulkPopupClose.click();
		}
		searchReservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.basicSearchNoShowReservation, driver);
		String bulkCancelledReservationStatus = reservationSearch.basicSearchNoShowReservation.getText();
		Assert.assertEquals(bulkCancelledReservationStatus, "No-Show");
		openReservation(driver);
		Wait.WaitForElement(driver, OR.click_Folio_tab);
		reservationSearch.click_Folio_tab.click();
		Wait.WaitForElement(driver, OR.clickVoidButton);
		List<WebElement> rows = driver.findElements(By.xpath("((//table/tbody)[4]/tr)//child::*"));
		// assertTrue(rows.isEmpty(), "RoomCharge Line item is NOT voided which
		// is inncorrect");
		resSearchLogger.info("Folio Line Items after void roomCharges: " + rows.size());
		Wait.WaitForElement(driver, OR.clickVoidButton);
		/*
		 * if(!reservationSearch.clickVoidButton.isSelected()){
		 * 
		 * reservationSearch.clickVoidButton.click(); Wait.wait5Second(); }
		 * List<WebElement> rows2 =
		 * driver.findElements(By.xpath("(//table/tbody)[4]/tr"));
		 * resSearchLogger.info("Folio Line Items after void roomCharges: "
		 * +rows2.size());
		 */
	}

	public void delete_Res_WithResNumber(WebDriver driver, String Res_Confirm_Number) throws InterruptedException {

		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		resservationSearch.Check_Reservation.click();
		resservationSearch.Click_Bulk_Action.click();

		try {
			Wait.explicit_wait_visibilityof_webelement(resservationSearch.Select_Delete, driver);
			Utility.ScrollToElement(resservationSearch.Select_Delete, driver);
			Wait.explicit_wait_elementToBeClickable(resservationSearch.Select_Delete, driver);
			resservationSearch.Select_Delete.click();
		} catch (Exception e) {
			resservationSearch.Click_Bulk_Action.click();
			Wait.explicit_wait_visibilityof_webelement(resservationSearch.Select_Delete, driver);
			Utility.ScrollToElement(resservationSearch.Select_Delete, driver);
			Wait.explicit_wait_elementToBeClickable(resservationSearch.Select_Delete, driver);
			resservationSearch.Select_Delete.click();
		}

		Wait.WaitForElement(driver, OR.Verify_Bulk_Delete_popup);
		assertTrue(resservationSearch.Verify_Bulk_Delete_popup.getText().equals("Bulk Delete"),
				"Bulk No-Show PopUp didn't display");
		String GetResNumber = resservationSearch.Verify_Res_Number.getText();

		resSearchLogger.info("GetResNumber  :" + GetResNumber);
		assertTrue(GetResNumber.equals(Res_Confirm_Number), "Fail to verify GetResNumber in popup");
		Wait.WaitForElement(driver, OR.Click_Process_Button);
		resservationSearch.Click_Process_Button.click();
		Wait.WaitForElement(driver, OR.Verify_Bulk_Delete_Completed);
		resservationSearch.click_on_Close_icon.click();
		Wait.wait3Second();

		// Clear basic search and search for the deleted reservation
		resservationSearch.Basic_Res_Number.clear();
		resservationSearch.Basic_Res_Number.sendKeys(Res_Confirm_Number);
		resservationSearch.Click_BasicSearch.click();
		Wait.wait2Second();
		// Assert.assertEquals(resservationSearch.Search_Results_Alert_Msg.getText(),
		// "No records meet your criteria. Please change your criteria and
		// search again.");

	}

	public ArrayList<String> Delete_Reservation(WebDriver driver, String Res_Confirm_Number)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		resservationSearch.Check_Reservation.click();
		test_steps.add("Click on 'Check_reservation' Checkox");
		resservationSearch.Click_Bulk_Action.click();
		test_steps.add("Click on 'Bulk_Action'Checkbox");
		Utility.ScrollToElement(resservationSearch.Select_Delete, driver);
		resservationSearch.Select_Delete.click();
		test_steps.add("Select Delete Option");
		Wait.WaitForElement(driver, OR.Verify_Bulk_Delete_popup);
		assertTrue(resservationSearch.Verify_Bulk_Delete_popup.getText().equals("Bulk Delete"),
				"Bulk Delete PopUp didn't display");
		test_steps.add("Bulk Delete PopUp is displayed");
		String GetResNumber = resservationSearch.Verify_Res_Number.getText();
		test_steps.add("GetResNumber  :" + GetResNumber);
		resSearchLogger.info("GetResNumber  :" + GetResNumber);
		assertTrue(GetResNumber.equals(Res_Confirm_Number), "Fail to verify GetResNumber in popup");
		Wait.WaitForElement(driver, OR.Click_Process_Button);
		resservationSearch.Click_Process_Button.click();
		test_steps.add("Click on Process Button");
		Wait.WaitForElement(driver, OR.Verify_Bulk_Delete_Completed);
		resservationSearch.click_on_Close_icon.click();
		test_steps.add("Click on Close Button");
		Wait.wait3Second();

		// Clear basic search and search for the deleted reservation
		resservationSearch.Basic_Res_Number.clear();
		resservationSearch.Basic_Res_Number.sendKeys(Res_Confirm_Number);
		test_steps.add("Reservation is searched");
		resservationSearch.Click_BasicSearch.click();
		test_steps.add("Click on search icon");
		Wait.wait2Second();
		Assert.assertEquals(resservationSearch.Search_Results_Alert_Msg.getText(),
				"No records meet your criteria. Please change your criteria and search again.");
		test_steps.add(
				" After bulk delete reservation the message apperrs : 'No records meet your criteria. Please change your criteria and search again' .");
		return test_steps;
	}

	public ArrayList<String> uncheck_TaxExempt(WebDriver driver, ExtentTest test, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		Wait.wait2Second();
		// Java script object creation
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		if (ReservationPage.Check_IsTaxExempt.isSelected()) {

			Wait.WaitForElement(driver, OR.Check_IsTaxExempt);
			ReservationPage.Check_IsTaxExempt.click();
			test_steps.add("Tax exempt check box un selected");
			resSearchLogger.info("Tax exempt check box un selected");
		}
		return test_steps;
	}

	public void addIncidentals(WebDriver driver, ExtentTest test1, String category, String amount)
			throws InterruptedException {
		// Wait.wait5Second();
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);

		Wait.WaitForElement(driver, OR.AddIncidental);
		Utility.ScrollToElement(resservationSearch.AddIncidental, driver);
		resservationSearch.AddIncidental.click();
		resSearchLogger.info("Click Add");

		Select sel = new Select(resservationSearch.IncidentalCategory);
		sel.selectByVisibleText(category);
		resSearchLogger.info("elect the category : " + category);

		resservationSearch.IncidentalAmount.sendKeys(amount);
		resSearchLogger.info("Enter the amount : " + amount);

		resservationSearch.Commit.click();
		resSearchLogger.info("Click commit");

		Wait.wait5Second();
		String str = driver.findElement(By.xpath("//td/span[contains(text(),'" + category
				+ "')]/../following-sibling::td/following-sibling::td/following-sibling::td/span")).getText();
		str = str.replace("$", "");
		Double d = Double.parseDouble(str);
		if (d == 0) {
			resSearchLogger.info("Tax value is zero : " + d);
		} else {
			resSearchLogger.info("Tax value is not zero : " + d);
		}
	}

	public void preDefinedQueriesTab_AllArrivalClick(WebDriver driver) throws InterruptedException {

		Elements_Reservation_SearchPage res = new Elements_Reservation_SearchPage(driver);
		String ColorBfrButtonClick = res.select_AllArrivals.getCssValue("background-color");
		res.select_AllArrivals.click();
		String ColorAftrButtonClick = "";

		try {
			WebElement AllArrival_AfterClick = driver
					.findElement(By.xpath("//li[@class='all-arrivals predefinedQueryOption active']"));
			ColorAftrButtonClick = AllArrival_AfterClick.getCssValue("background-color");
		} catch (Exception e) {
			ColorAftrButtonClick = res.selectAllArrivals.getCssValue("background-color");
		}

		assertTrue(!ColorBfrButtonClick.equals(ColorAftrButtonClick), "Button is not clicked");
		resSearchLogger.info("AllArrival Button Clicked ");

	}
	/*
	 * 
	 * public void bulkCancelOfReservation(WebDriver driver) throws
	 * InterruptedException{ Elements_Reservation_SearchPage reservation=new
	 * Elements_Reservation_SearchPage(driver);
	 * reservation.selectAllRecords.click(); new
	 * Select(reservation.selectCancelFromBulkAction).selectByIndex(3);
	 * Wait.wait10Second();
	 * 
	 * }
	 */

	public void BasicSearch(WebDriver driver, String FirstName, String LastName) throws InterruptedException {

		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);

		resservationSearch.Enter_Guest_Name.sendKeys(FirstName + " " + LastName);
		resservationSearch.Click_BasicSearch.click();
		Wait.wait2Second();

		String Guest_Name = resservationSearch.Get_Guest_Name.getText();
		assertEquals(Guest_Name, FirstName + " " + LastName, "Basic search fail");

	}

	public void verifyNotes(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage notes = new Elements_Reservation_SearchPage(driver);
		// notes.clickSummaryTab.click();
		notes.clickGuestInfoTab.click();
		List<WebElement> rows = driver.findElements(By.xpath("(//table/tbody)[3]/tr"));
		resSearchLogger.info("No of rows in Notes : " + rows.size());
		Wait.wait5Second();

		if (rows.size() != 0) {
			Wait.WaitForElement(driver, OR.notesDelete);
			notes.notesDelete.click();
			Wait.WaitForElement(driver, OR.folioSaveButton);
			Wait.explicit_wait_visibilityof_webelement_120(notes.folioSaveButton, driver);
		}
	}

	/*
	 * public void preDefinedQueriesTab(WebDriver driver) throws
	 * InterruptedException{
	 * 
	 * Elements_Reservation_SearchPage reservation=new
	 * Elements_Reservation_SearchPage(driver);
	 * reservation.inHouseReservations.click();
	 * 
	 * //new Select(reservation.selectAllArrivals).selectByIndex(1);
	 * Wait.wait10Second();
	 * 
	 * }
	 * 
	 */
	public void OpenExiting_Reservation(WebDriver driver, String FirstName, String LastName)
			throws InterruptedException {

		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);

		resservationSearch.Enter_Guest_Name.sendKeys(FirstName + " " + LastName);
		resservationSearch.Click_BasicSearch.click();
		Wait.wait5Second();
		boolean web_ele_size = driver.findElements(By.xpath(OR.Get_Guest_Name)).size() > 1;
		if (web_ele_size) {
			String Guest_Name = resservationSearch.Get_Guest_Names.get(0).getText();

			assertEquals(Guest_Name, FirstName + " " + LastName, "Basic search fail");

			resservationSearch.Get_Guest_Names.get(0).click();
		} else {
			String Guest_Name = resservationSearch.Get_Guest_Name.getText();

			assertEquals(Guest_Name, FirstName + " " + LastName, "Basic search fail");

			resservationSearch.Get_Guest_Name.click();
		}

		Wait.wait2Second();
		assertTrue(resservationSearch.ReservationStatus_ReservationPage.isDisplayed(), "Reservation Page didn't open");

	}

	public void ClickExistReservation(WebDriver driver) throws InterruptedException {

		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);

		boolean guest_name = driver.findElements(By.xpath(OR.Get_Guest_Name)).size() > 1;
		if (guest_name) {
			driver.findElements(By.xpath(OR.Get_Guest_Name)).get(0).click();
		} else {
			resservationSearch.Get_Guest_Name.click();
		}
	}

	public void ReservationInfoDetails(WebDriver driver) throws InterruptedException {

		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);

		Wait.explicit_wait_visibilityof_webelement_350(reservationSearch.AdvanceReservationInfo_RoomClassButton,
				driver);
		reservationSearch.AdvanceReservationInfo_RoomClassButton.click();
		reservationSearch.AdvanceReservationInfo_DBRRoomClass.click();
		Wait.wait5Second();
		reservationSearch.AdvanceMarketingInfo_SearchButton.click();
		Wait.wait5Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", reservationSearch.AdvanceReservation_LastItemRoomClass);
		Wait.wait5Second();
		String GetRoomClass = reservationSearch.AdvanceReservation_LastItemRoomClass.getText();
		System.out.println("ROOMCLASS" + GetRoomClass);
		GetRoomClass = GetRoomClass.split(" : ")[0];
		System.out.println("ROOMCLASS" + GetRoomClass);
		assertTrue(GetRoomClass.equals("DBR") || GetRoomClass.equals("Double Bed Room"),
				"Matching Results are not displayed");
	}

	public void MarketingInfo_SourceDropDown(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);

		Wait.explicit_wait_visibilityof_webelement_120(reservationSearch.AdvanceMarketingInfo_SourceDropDownButton,
				driver);
		String GetSourceDefaultValue = reservationSearch.AdvanceMarketingInfo_SourceDefaultValue.getText();
		assertTrue(GetSourceDefaultValue.contains("Source"), "Source By Default Value isn't correct");
		reservationSearch.AdvanceMarketingInfo_SourceDropDownButton.click();
		reservationSearch.AdvanceMarketingInfo_InncenterDropDown.click();
		Wait.wait1Second();
		reservationSearch.AdvanceMarketingInfo_SearchButton.click();
		Wait.wait5Second();
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].scrollIntoView();",
		// reservationSearch.AdvanceReservation_ItemPerPage);
		// reservationSearch.AdvanceReservation_ItemPerPage.click();
		// reservationSearch.AdvanceReservation_ItemPerPage.sendKeys("100");
		// reservationSearch.AdvanceReservation_ItemPerPage.click();

	}

	public void GuestInfoDetails(WebDriver driver, String AccountName) throws InterruptedException {

		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.explicit_wait_visibilityof_webelement_350(reservationSearch.AdvanceGuestInfo_AccountName, driver);
		reservationSearch.AdvanceGuestInfo_AccountName.sendKeys(AccountName);
		Wait.wait5Second();
		Wait.explicit_wait_visibilityof_webelement_350(driver.findElement(By.className("span-nomber")), driver);
		List<WebElement> element = driver.findElements(By.className("span-nomber"));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		jse1.executeScript("arguments[0].click();", element.get(0));
		// element.get(2).click();
		System.out.println("Element Nme" + element.get(0).getAttribute("value"));
		String GetAccountName = reservationSearch.AdvanceGuestInfo_AccountName.getAttribute("value");
		assertTrue(GetAccountName.contains(AccountName));
		String GetAccountNumber = reservationSearch.AdvanceGuestInfo_AccountName.getAttribute("value");
		assertTrue(!GetAccountNumber.equals(null));
		Wait.wait1Second();
		reservationSearch.AdvanceMarketingInfo_SearchButton.click();
		Wait.wait10Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", reservationSearch.AdvanceReservation_ItemPerPage);
		reservationSearch.AdvanceReservation_ItemPerPage.click();
		reservationSearch.AdvanceReservation_ItemPerPage.sendKeys("100");
		reservationSearch.AdvanceReservation_ItemPerPage.click();
		Wait.wait3Second();
		String GetAccountNameText = reservationSearch.AdvanceReservation_LastItemAccountName.getAttribute("title");
		// System.out.println("get text:"+GetAccountNameText);
		assertTrue(GetAccountNameText.equals("Test Group"), "Reservation associated with the acc isn't displayed");

	}

	public void OpenAdvanceReservation(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		// Wait.WaitForElement(driver, OR.clickReservation);
		Wait.wait10Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", reservationSearch.clickReservation);
		// reservationSearch.clickReservation.click();
		Wait.wait5Second();
	}

	public void SelectFirstActiveReservation(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		reservationSearch.Reser_FirstActiveReservation.click();
	}

	public void searchReservationWithNumber(WebDriver driver, String ReservationNumber) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.basicSearchIcon, driver);
		reservationSearch.enterResNumber.clear();
		reservationSearch.enterResNumber.sendKeys(ReservationNumber);
		Wait.wait2Second();
		reservationSearch.basicSearchIcon.click();
		Wait.wait2Second();
		Wait.WaitForElement(driver, OR.Check_Reservation);
		// Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.Check_Reservation,
		// driver);
		assertTrue(reservationSearch.Check_Reservation.isDisplayed(), "Reservation didn't search");
	}

	public ArrayList<String> BulkCancel(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.wait2Second();
		driver.findElements(By.xpath("//div[@class='checker']")).get(0).click();
		Wait.wait2Second();
		test_steps.add("Select Reservation");
		resSearchLogger.info("Reservation is selected");
		assertTrue(reservationSearch.Click_Bulk_Action.isEnabled(), "Failed: Bulk Action is not Enabled");
		reservationSearch.Click_Bulk_Action.click();
		resSearchLogger.info("Clicked on bulk action");
		test_steps.add("Clicked on bulk action");
		reservationSearch.selectCancel.click();
		resSearchLogger.info("Clicked on cancel ");
		test_steps.add("Clicked on Cancel");
		Wait.WaitForElement(driver, OR.bulkCancelpopup);
		assertEquals(reservationSearch.bulkCancelpopup.isDisplayed(), true, "The bulkCancel PopUp is not displayed");
		String Amount_Processed = reservationSearch.Verify_Amount_Value.getAttribute("value");
		Amount_Processed = Amount_Processed.replace("", " $ 0");
		System.out.println(Amount_Processed);
		test_steps.add("The process amount during bulk cancellation is : " + Amount_Processed);
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.enterCancellationReason, driver);
		reservationSearch.enterCancellationReason.sendKeys("Reservation bulk Cancellation");
		test_steps.add("Enter the reason for bulk cancellation : 'Reservation bulk Cancellation' ");
		Wait.wait1Second();
		reservationSearch.voidRoomChargesCheckBox.click();
		test_steps.add("The Void Charges CheckBox is clicked");
		assertTrue(reservationSearch.voidRoomChargesCheckBox.isSelected(), "Failed: Void Room Charges Are not Checked");
		Wait.explicit_wait_visibilityof_webelement(reservationSearch.processButton, driver);
		reservationSearch.processButton.click();
		resSearchLogger.info("Click process Button ");
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.bulkCancellationMessage, driver);
		String bulkCancelRes = reservationSearch.bulkCancellationMessage.getText();
		resSearchLogger.info(bulkCancelRes);
		Assert.assertEquals(bulkCancelRes, "Bulk Cancel Completed");
		String Closing_Amount = reservationSearch.Verify_Closing_Amount_Value.getAttribute("value");
		System.out.println(Closing_Amount);

		float TotalAmount = Float.parseFloat(Closing_Amount);
		System.out.println(TotalAmount);
		System.out.println("The Closing balance after cancellation is:" + Closing_Amount);
		Closing_Amount = String.format("$ %.2f", TotalAmount);
		System.out.println(Closing_Amount);
		test_steps.add("The Closing balance after cancellation is : " + Closing_Amount);
		reservationSearch.BulkPopupClose.click();
		resSearchLogger.info("Click Close ");
		return test_steps;

	}

	public ArrayList<String> VerifyEndingBalance(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.FolioBalance, driver);
		Utility.ScrollToElement(reservationSearch.FolioBalance, driver);
		String EndingBlnc = reservationSearch.FolioBalance.getText();
		System.out.println(EndingBlnc);
		EndingBlnc = EndingBlnc.replace("$", "");
		float totalAmount = Float.parseFloat(EndingBlnc);
		System.out.println(totalAmount);
		assertEquals(totalAmount == 0.0, true, "The Ending Balance Is not Zero after cancellation of resrvation");
		String Closing_Amount = String.format("$ %.2f", totalAmount);
		test_steps.add("The Ending Balance In Folio Is : " + Closing_Amount);
		return test_steps;
	}

	public ArrayList<String> VerifyEndingAmount(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.FolioBalance, driver);
		Utility.ScrollToElement(reservationSearch.FolioBalance, driver);
		String EndingBlnc = reservationSearch.FolioBalance.getText();
		System.out.println(EndingBlnc);
		EndingBlnc = EndingBlnc.replace("$", "");
		float totalAmount = Float.parseFloat(EndingBlnc);
		System.out.println(totalAmount);
		assertEquals(totalAmount == 0.0, true, "The Ending Balance Is not zero after cancellation of resrvation");
		String Closing_Amount = String.format("$ %.2f", totalAmount);
		test_steps.add("The Ending Amount is : " + Closing_Amount);
		return test_steps;
	}

	public ArrayList<String> VerifyBalanceAfterCreationRes(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.FolioBalance, driver);
		Utility.ScrollToElement(reservationSearch.FolioBalance, driver);
		String EndingBlnc = reservationSearch.FolioBalance.getText();
		System.out.println(EndingBlnc);
		EndingBlnc = EndingBlnc.replace("$", "");
		float totalAmount = Float.parseFloat(EndingBlnc);
		System.out.println(totalAmount);
		assertEquals(totalAmount > 0, true, "The Ending Balance Is not zero after cancellation of resrvation");
		String Closing_Amount = String.format("$ %.2f", totalAmount);
		test_steps.add("After created reservation the folio balance is : " + Closing_Amount);
		return test_steps;
	}

	public void verifReservationStatus(WebDriver driver, String Status) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		String ReservationStatus = reservationSearch.ReservationStatus_ReservationPage.getText();
		assertTrue(ReservationStatus.equals(Status), "Reservation Status isn't correct");
		resSearchLogger.info("Reservation Status Verified");
	}

	public ArrayList<String> SelectBulkNoShow(WebDriver driver, String ResNumber, boolean Void, ArrayList<String> steps)
			throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);

		Utility.ScrollToElement(reservationSearch.Check_Reservation, driver);
		reservationSearch.Check_Reservation.click();
		resSearchLogger.info("Reservation is selected");
		steps.add("Select reservation");
		assertTrue(reservationSearch.Click_Bulk_Action.isEnabled(), "Failed: Bulk Action Drop Down is not Enabled");
		steps.add("Bulk Action Drop Down is Enabled");
		Utility.ScrollToElement(reservationSearch.Click_Bulk_Action, driver);
		reservationSearch.Click_Bulk_Action.click();
		resSearchLogger.info("Clicked on bulk action");
		steps.add("Clicked on Bulk Action");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", reservationSearch.selectNoShow);
		reservationSearch.selectNoShow.click();

		Wait.WaitForElement(driver, OR.bulkNoShowpopup);
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.bulkNoShowpopup, driver);
		assertTrue(reservationSearch.bulkNoShowpopup.getText().equals("Bulk No-Show"),
				"Bulk No-Show PopUp didn't display");
		steps.add("Bulk No-Show PopUp Appeared");
		Utility.ScrollToElement(reservationSearch.BulkNoShowPopup_Message, driver);
		String message1 = reservationSearch.BulkNoShowPopup_Message.getText();
		Utility.app_logs.info("Message 1 Text " + message1);
		message1 = message1.replaceAll("[\r\n]+", " ").trim();
		assertEquals(message1, ("1 RESERVATIONS(S) CAN BE UPDATED").trim(),
				"Failed: Reservation Can be updated Message");
		steps.add("'1 RESERVATIONS(S) CAN BE UPDATED'  Message Appeared");
		Utility.ScrollToElement(reservationSearch.BulkNoShowPopup_ResNumber, driver);
		assertEquals(reservationSearch.BulkNoShowPopup_ResNumber.getText(), ResNumber,
				"Failed: reservation Number missmatched");
		steps.add("Bulk No Show modal appears  with details to the selected reservation");

		Utility.ScrollToElement(reservationSearch.BulkNoShowPopup_VoidRoomCharges, driver);
		assertTrue(!reservationSearch.BulkNoShowPopup_VoidRoomCharges.isSelected(),
				"Failed: Void Room Charges of Bulk No Show popup not Unchecked");
		steps.add("Void Room Charges of Bulk No Show popup not Unchecked");
		reservationSearch.BulkNoShowPopup_VoidRoomCharges.click();
		steps.add("Click Void Room Charges of Bulk No Show popup");

		Utility.app_logs.info("Click Void Room Charges of Bulk No Show popup");
		assertTrue(reservationSearch.BulkNoShowPopup_VoidRoomCharges.isSelected(),
				"Failed: Void Room Charges of Bulk No Show popup not Unchecked");
		steps.add("Void Room Charges of Bulk No Show popup Checked");

		// New Steps Added

		try {
			Wait.WaitForElement(driver, NewReservation.BulkNoShowPopup_PostNoShowFee);
			assertTrue(!reservationSearch.BulkNoShowPopup_PostNoShowFee.isSelected(),
					"Failed: Post No Show fee of Bulk No Show popup Checked");
			steps.add("Post No Show fee of Bulk No Show popup Appeared");
			reservationSearch.BulkNoShowPopup_PostNoShowFee.click();
			Utility.ScrollToElement(reservationSearch.BulkNoShowPopup_PostNoShowFee, driver);
			assertTrue(reservationSearch.BulkNoShowPopup_PostNoShowFee.isSelected(),
					"Failed: Post No Show fee of Bulk No Show popup not Checked");
			steps.add("Post No Show fee of Bulk No Show popup Checked");
			Utility.app_logs.info("Post No Show fee of Bulk No Show popup Checked");
		} catch (Exception e) {
			steps.add("Post No Show fee of Bulk No Show popup CheckBox Not Appeared");
			Utility.app_logs.info("Post No Show fee of Bulk No Show popup CheckBox Not Appeared");
		}

		Wait.WaitForElement(driver, OR.processButton);
		reservationSearch.processButton.click();
		steps.add("Click Process Bulk No-Show");
		try {
			Wait.waitForElementToBeGone(driver,
					driver.findElement(By
							.xpath("//div[contains(@data-bind,'visible: showLoading() || showLoading')]//div[@class='modules_loading']")),
					300);
		} catch (Exception e) {

		}
		Wait.WaitForElement(driver, OR.bulkNoShowMessage);
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.bulkNoShowMessage, driver);
		assertTrue(reservationSearch.bulkNoShowMessage.getText().equals("Bulk No-Show Completed"),
				"Bulk No-Show Completed PopUp didn't display");
		steps.add("Bulk No-Show Completed Popup Appear");
		Utility.ScrollToElement(reservationSearch.BulkPopupClose, driver);
		reservationSearch.BulkPopupClose.click();
		steps.add("Click Close Bulk No-Show Completed");
		Wait.WaitForElement(driver, OR.basicSearchIcon);
		reservationSearch.basicSearchIcon.click();
		Wait.wait2Second();
		String bulkNoShowReservationStatus = reservationSearch.basicSearchNoShowReservation.getText();
		Assert.assertEquals(bulkNoShowReservationStatus, "No-Show");
		steps.add("Status of the reservation in search result reflects No Show");
		// Wait.wait5Second();
		return steps;
	}

	public ArrayList<String> OpenSearchedReservation_ClickFolio(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.clickReservation);
		Wait.explicit_wait_visibilityof_webelement_120(reservationSearch.clickReservation, driver);
		reservationSearch.clickReservation.click();
		steps.add("Reservation Page opened Successfully");
		resSearchLogger.info("Reservation Page opened Successfully");
		Wait.explicit_wait_visibilityof_webelement_150(reservationSearch.click_Folio_tab, driver);
		Utility.ScrollToElement(reservationSearch.click_Folio_tab, driver);
		reservationSearch.click_Folio_tab.click();
		steps.add("Folio Tab opened Successfully");
		resSearchLogger.info("Folio Tab opened Successfully");

		return steps;
	}

	public ArrayList<String> verifyReservationStatus(WebDriver driver, String Status, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		String ReservationStatus = reservationSearch.ReservationStatus_ReservationPage.getText();
		assertTrue(ReservationStatus.equals(Status), "Reservation Status isn't correct");
		resSearchLogger.info("Reservation Status Verified");
		test_steps.add("Reservation Status : " + Status + " Verified");
		return test_steps;
	}

	public void movedTo_OpenedRegistration_Or_Search_VIA_RegistrationNo(WebDriver driver, ArrayList<String> test_steps,
			String GuestName, String RegistrationNO) throws InterruptedException {
		String Path = "//div/ul[@class='sec_nav']//li/a//span[contains(text(),'" + GuestName + "')]";
		boolean isExist = driver.findElement(By.xpath(Path)).isEnabled();
		if (isExist) {
			driver.findElement(By.xpath(Path)).click();
			resSearchLogger.info("Moved to Already Opened Registration: " + RegistrationNO);
			test_steps.add("Moved to Already Opened Registration: <b>" + RegistrationNO + "</b>");
		} else {
			basicSearchWithResNumber(driver, RegistrationNO, test_steps);

		}
	}

	public String basicSearch_WithResNumberVerification(WebDriver driver, String resNumber)
			throws InterruptedException {
		String str = null;

		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Elements_Reservation res = new Elements_Reservation(driver);
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(resservationSearch.Basic_Res_Number, driver);
		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		resservationSearch.Basic_Res_Number.clear();
		// Wait.wait2Second();

		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		resservationSearch.Basic_Res_Number.sendKeys(resNumber);
		resservationSearch.Click_BasicSearch.click();
		Wait.explicit_wait_xpath(OR.Verify_Search_Loading_Gird, driver);
		// Wait.wait10Second();

		Wait.WaitForElement(driver, OR.Get_Res_Number);
		// Wait.explicit_wait_visibilityof_webelement_350(resservationSearch.Get_Res_Number,
		// driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Get_Res_Number, driver);
		String GetResNumber = resservationSearch.Get_Res_Number.getText();
		System.out.println(GetResNumber);
		if (GetResNumber == "") {
			Wait.explicit_wait_visibilityof_webelement(resservationSearch.Get_Res_Number, driver);
			GetResNumber = resservationSearch.Get_Res_Number.getText();
			System.out.println(GetResNumber);
		}
		Assert.assertEquals(resNumber, GetResNumber);

		// Explicit wait object creation
		WebDriverWait wait = new WebDriverWait(driver, 90);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.NewRervations)));
		// System.out.println(resNumber);

		String resLocator = "//span[contains(text(),'" + resNumber.trim() + "')]/../../td[4]/div/a";

		Wait.WaitForElement(driver, resLocator);
		str = driver.findElement(By.xpath(resLocator)).getText();

		WebElement ReservationFound = driver.findElement(By.xpath(resLocator));
		ReservationFound.click();
		Wait.wait15Second();
		driver.navigate().refresh();
		Wait.wait10Second();

		return str;
	}

	public ArrayList<String> multipleSearchReservationNumber(WebDriver driver, String reservationNumber)
			throws Exception {

		// We can send multiple reservation number e.g
		// xxxxxxx,xxxxxxxx,xxxxxxxxx,xxxxxxx
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Basic_Res_Number), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Basic_Res_Number), driver);

		resservationSearch.Basic_Res_Number.clear();
		resservationSearch.Basic_Res_Number.sendKeys(reservationNumber);
		testSteps.add("Enter reservation number: " + reservationNumber);

		resservationSearch.Click_BasicSearch.click();
		testSteps.add("Click on search button");
		resSearchLogger.info("Click on search button");
		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Basic_Res_Number), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Basic_Res_Number), driver);

		return testSteps;
	}

	public ArrayList<String> clickBulkOptionCheckInAndVerifyPopUp(WebDriver driver) throws Exception {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Click_Bulk_Action, driver);
		resservationSearch.Click_Bulk_Action.click();
		test_steps.add("Click Bulk Action");
		resSearchLogger.info("Clicked on bulk action");
		resservationSearch.Select_Checkin.click();
		test_steps.add("Clicked on bulk action - CHECKIN option");
		resSearchLogger.info("Clicked on bulk action - CHECKIN option");
		Wait.explicit_wait_visibilityof_webelement_120(resservationSearch.Verify_Bulk_Checkin_popup, driver);
		test_steps.add("Bulk CheckIn Popup is displayed");
		resSearchLogger.info("Bulk CheckIn Popup is displayed");

		return test_steps;

	}

	public ArrayList<String> selectBulkCheckOut(WebDriver driver) throws Exception {
		ArrayList<String> testSteps = new ArrayList<>();
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.Click_Bulk_Action);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Click_Bulk_Action), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Click_Bulk_Action), driver);

		resservationSearch.Click_Bulk_Action.click();
		testSteps.add("Click Bulk Action");
		resSearchLogger.info("Clicked on bulk action");

		resservationSearch.Select_Checkout.click();
		testSteps.add("Select bulk checkout");
		resSearchLogger.info("Select bulk checkout");
		Wait.WaitForElement(driver, OR.bulkCheckoutPopup);
		Wait.waitForElementToBeVisibile(By.xpath(OR.bulkCheckoutPopup), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.bulkCheckoutPopup), driver);

		assertEquals(resservationSearch.bulkCheckoutPopup.getText(), "Bulk Checkout",
				"Failed: Bulk checkout popup is not dispalying");
		testSteps.add("Bulk checkOut popup is displayed");
		resSearchLogger.info("Bulk CheckOut Popup is displayed");
		return testSteps;

	}

	public void verifyReservationRoom(WebDriver driver, String ResNumber, String Room, ArrayList<String> test_steps)
			throws InterruptedException {
		String xpath = "//span[@data-bind='text: ConfirmationNumber' and text() = '" + ResNumber
				+ "']/parent::td/following-sibling::td/span[@data-bind='text: RoomClassName']";
		WebElement ResRoom = driver.findElement(By.xpath(xpath));
		Wait.explicit_wait_visibilityof_webelement(ResRoom, driver);
		Utility.ScrollToElement(ResRoom, driver);
		String ReservationRoom = ResRoom.getText();
		assertEquals(ReservationRoom, Room, "Reservation Room isn't correct");
		resSearchLogger.info("Verified Reservation Room '" + Room + "'");
		test_steps.add("Verified Reservation Room '" + Room + "'");
	}

	public void clickAdvanceReservationButton(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Wait.WaitForElement(driver, OR.AdvancedSearch);
		Utility.ScrollToElement(reservationSearch.AdvancedSearch, driver);
		reservationSearch.AdvancedSearch.click();
	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickAdvancedSearch> ' Description: <Open advance
	 * search.> ' Input parameters: <WebDriver driver> ' Return value:
	 * <ArrayList<String>> ' Created By: <Jamal Nasir> ' Created On:
	 * <05/06/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> clickAdvancedSearch(WebDriver driver) {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		ArrayList<String> test_steps = new ArrayList<String>();

		reservationSearch.advanced.click();
		Wait.WaitForElement(driver, OR.advancedSearchStatus);
		resSearchLogger.info("Clicked Advance Search");
		test_steps.add("Clicked Advance Search");

		return test_steps;

	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <searchWeekWithNoReservation> ' Description: <Search for
	 * reservation based on date from coming Sunday to next Sunday till then
	 * date will return no reservation in search results and return last search
	 * date day.> ' Input parameters: <WebDriver driver, ArrayList<String>
	 * test_steps> ' Return value: <int> ' Created By: <Jamal Nasir> ' Created
	 * On: <05/06/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public static int Days = 7;

	public int searchWeekWithNoReservation(WebDriver driver) throws InterruptedException, ParseException {

		Elements_Reservation_SearchPage res = new Elements_Reservation_SearchPage(driver);
		resSearchLogger.info("Days Count : " + Days);

		String stayFromDate = null;
		stayFromDate = ESTTimeZone.getComingSunday(Days);

		res.StayFromInput.clear();
		res.StayFromInput.sendKeys(stayFromDate);

		String stayToDate = null;
		stayToDate = ESTTimeZone.getComingSunday(Days = Days + 7);

		res.StayToInput.clear();
		res.StayToInput.sendKeys(stayToDate);
		Wait.WaitForElement(driver, OR.searchButton);
		res.searchButton.click();

		Wait.WaitForElement(driver, OR.New_Reservation_Button);
		if (res.AdvanceReservationResultList.size() > 0) {
			searchWeekWithNoReservation(driver);
		}
		return Days;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickCloseSearch> ' Description: <Closes advance search.>
	 * ' Input parameters: <WebDriver driver> ' Return value:
	 * <ArrayList<String>> ' Created By: <Jamal Nasir> ' Created On:
	 * <05/06/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> clickCloseSearch(WebDriver driver) {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		ArrayList<String> test_steps = new ArrayList<String>();

		reservationSearch.AdvanceSearchClose.click();
		resSearchLogger.info("Clicked On Close Advance Search Icon");
		test_steps.add("Clicked On Close Advance Search Icons");

		Wait.WaitForElement(driver, OR.advanced);

		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyReservationStatusForMRB> ' Description: <Verify
	 * Reservation Status for MRB> ' Input parameters: <Guest Name, Reservation
	 * Number and Reservation Status value> ' Return value: <array list> '
	 * Created By: <Gangotri> ' Created On: <04/29/2020>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ <mm/dd/yyyy>:<Developer name>:
	 * 1.Step modified 2.Step modified
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> verifyReservationStatusForMRB(WebDriver driver, String GuestName,
			String ConfirmationNumber, String Status, ArrayList<String> test_steps) throws InterruptedException {
		String Path = "//td/div/a[contains(@data-bind,'GuestName')][contains(text(),'" + GuestName
				+ "')]/ancestor::td/following-sibling::td/span[contains(@data-bind,'ConfirmationNumber')][contains(text(),'"
				+ ConfirmationNumber
				+ "')]/ancestor::td/following-sibling::td//span[not(contains(@class,'ng-resStatus'))][contains(@data-bind, 'text: StatusName')][contains(text(),'"
				+ Status + "')]";
		WebElement element = driver.findElement(By.xpath(Path));
		Wait.WaitForElement(driver, Path);
		Utility.ScrollToElement(element, driver);
		assertTrue(element.isDisplayed(), "Reservation Status isn't correct");
		resSearchLogger.info("Guest Name :" + GuestName + " Confirmation Number: " + ConfirmationNumber
				+ " Reservation Status : " + Status + " Verified");
		test_steps.add("Guest Name :<b>" + GuestName + " </b>Confirmation Number: <b>" + ConfirmationNumber
				+ " </b>Reservation Status : <b>" + Status + "</b> Verified");
		return test_steps;
	}

	public void clickBasicReservation(WebDriver driver)

	{
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		Wait.explicit_wait_visibilityof_webelement(resservationSearch.Basic_Res_Number, driver);
		resservationSearch.Basic_Res_Number.click();
	}

	public String basicSearchWithResNumber(WebDriver driver, String resNumber, ArrayList<String> test_steps)
			throws InterruptedException {

		String str = null;

		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		Wait.explicit_wait_visibilityof_webelement(resservationSearch.Basic_Res_Number, driver);
		resservationSearch.Basic_Res_Number.click();
		test_steps.add("Click on Reservation Number Text Box");
		resSearchLogger.info("Click on Reservation Number Text Box");
		resservationSearch.Basic_Res_Number.clear();
		test_steps.add("Clear  Reservation Number Text Box");
		resSearchLogger.info("Clear  Reservation Number Text Box");
		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		resservationSearch.Basic_Res_Number.sendKeys(resNumber);
		test_steps.add("Input Reservation Number <b>" + resNumber + "</b>");
		resSearchLogger.info("Input Reservation Number: " + resNumber);
		resservationSearch.Click_BasicSearch.click();
		test_steps.add("Click on Search Button");
		resSearchLogger.info("Click on Search Button");
		Wait.WaitForElement(driver, OR.Get_Res_Number);
		Wait.waitUntilPresenceOfElementLocated(OR.Get_Res_Number, driver);
		String GetResNumber = resservationSearch.Get_Res_Number.getText();
		System.out.println(GetResNumber);
		if (GetResNumber == "") {
			Wait.explicit_wait_visibilityof_webelement(resservationSearch.Get_Res_Number, driver);
			GetResNumber = resservationSearch.Get_Res_Number.getText();
			resSearchLogger.info(GetResNumber);
		}
		Assert.assertEquals(resNumber, GetResNumber);
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.NewRervations)));
		String resLocator = "//span[contains(text(),'" + resNumber.trim() + "')]/../../td[4]/div/a";
		Wait.WaitForElement(driver, resLocator);
		str = driver.findElement(By.xpath(resLocator)).getText();
		WebElement ReservationFound = driver.findElement(By.xpath(resLocator));
		ReservationFound.click();
		test_steps.add("Click on Reservation: </b>" + resNumber + "</b>");
		resSearchLogger.info("Click on Reservation: " + resNumber);
		driver.navigate().refresh();
		String loading = "(//div[@class='ir-loader-in'])";
		Wait.explicit_wait_absenceofelement(loading, driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation);
		return str;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: basicSearchWithGuestName ' Description: search with guest
	 * name ' Input parameters: WebDriver,String ' Return value:
	 * ArrayList<String> ' Created By: Adnan Ghaffar ' Created On: 28-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> clickOnAdvanced(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.advancedSearch);
		Wait.waitForElementToBeVisibile(By.xpath(OR.advancedSearch), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.advancedSearch), driver);
		resservationSearch.advancedSearch.click();
		testSteps.add("Click on advaced");
		return testSteps;
	}

	public ArrayList<String> guestInfo(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.GuestInfoInAdvanced);
		Wait.waitForElementToBeVisibile(By.xpath(OR.GuestInfoInAdvanced), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.GuestInfoInAdvanced), driver);
		assertEquals(resservationSearch.GuestInfoInAdvanced.isDisplayed(), true,
				"Failed: Guest info secion is not displaying");
		testSteps.add("Verify guest info section is displaying");
		return testSteps;
	}

	public ArrayList<String> marketingInfo(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.MarketingInfoInAdvanced);
		Wait.waitForElementToBeVisibile(By.xpath(OR.MarketingInfoInAdvanced), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.MarketingInfoInAdvanced), driver);
		assertEquals(resservationSearch.marketingInfoInAdvanced.isDisplayed(), true,
				"Failed: Marketing info secion is not displaying");
		testSteps.add("Verify marketing info section is displaying");
		return testSteps;
	}

	public ArrayList<String> reservationInfo(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.ReservationInfoInAdvanced);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ReservationInfoInAdvanced), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.ReservationInfoInAdvanced), driver);
		assertEquals(resservationSearch.ReservationInfoInAdvanced.isDisplayed(), true,
				"Failed: Reservation info secion is not displaying");
		testSteps.add("Verify reservation info section is displaying");
		return testSteps;
	}

	public String getReservationDetails(WebDriver driver, String guestName, int index) throws InterruptedException {

		String reservationDetailsPath = "((//a[text()='" + guestName + "'])[1]//..//..//following-sibling::td//span)["
				+ index + "]";
		Wait.WaitForElement(driver, reservationDetailsPath);
		Wait.waitForElementToBeVisibile(By.xpath(reservationDetailsPath), driver);
		WebElement element = driver.findElement(By.xpath(reservationDetailsPath));
		Utility.ScrollToElement_NoWait(element, driver);
		return element.getText();

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: basicSearchWithGuestName ' Description: search with guest
	 * name ' Input parameters: WebDriver,String ' Return value:
	 * ArrayList<String> ' Created By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> basicSearchWithGuestName(WebDriver driver, String guestName) throws InterruptedException {

		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.BasicGuestName);
		Wait.waitForElementToBeVisibile(By.xpath(OR.BasicGuestName), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.BasicGuestName), driver);
		reservationSearch.BasicGuestName.sendKeys(guestName);
		resSearchLogger.info("Entered the guest name for basic search");
		testSteps.add("Entered the guest name for basic search");
		reservationSearch.Click_BasicSearch.click();
		resSearchLogger.info("Clicked on Search Button");
		testSteps.add("Clicked on Search Button");
		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: advanceSearchWithReservationNumber ' Description: advance
	 * search with reservation number ' Input parameters: WebDriver,String '
	 * Return value: ArrayList<String> ' Created By: Adnan Ghaffar ' Created On:
	 * 28-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> advanceSearchWithReservationNumber(WebDriver driver, String reservationNumber) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.ADVANCE_SEARCH_WITH_RESERVATION_NUMBER);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ADVANCE_SEARCH_WITH_RESERVATION_NUMBER), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.ADVANCE_SEARCH_WITH_RESERVATION_NUMBER), driver);

		reservationSearch.advanceSearchWithReservationNumber.clear();
		reservationSearch.advanceSearchWithReservationNumber.sendKeys(reservationNumber);
		testSteps.add("Enter reservation number");

		return testSteps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: AdvanceSearchWithGuestName ' Description: Advance search
	 * with guest name ' Input parameters: WebDriver,String ' Return value:
	 * ArrayList<String> ' Created By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> advanceSearchWithGuestName(WebDriver driver, String guestName)
			throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.ADVANCE_SEARCH_WITH_GUEST_NAME);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ADVANCE_SEARCH_WITH_GUEST_NAME), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.ADVANCE_SEARCH_WITH_GUEST_NAME), driver);
		reservationSearch.advanceSearchWithGuestName.sendKeys(guestName);
		testSteps.add("Entered guest name: " + guestName);

		return testSteps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: AdvanceSearchWithAccountNumber ' Description: Advance
	 * search with account number ' Input parameters: WebDriver,String ' Return
	 * value: ArrayList<String> ' Created By: Adnan Ghaffar ' Created On:
	 * 11-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> advanceSearchWithAccountNumber(WebDriver driver, String accountNumber)
			throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.ADVANCE_SEARCH_WITH_ACCOUNT_NUMBER);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ADVANCE_SEARCH_WITH_ACCOUNT_NUMBER), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.ADVANCE_SEARCH_WITH_ACCOUNT_NUMBER), driver);
		reservationSearch.advanceSearchWithAccountNumber.sendKeys(accountNumber);
		testSteps.add("Entered account number: " + accountNumber);

		return testSteps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: AdvanceSearchWithEmailAndPhoneNumber ' Description:
	 * Advance search with email and phone number ' Input parameters:
	 * WebDriver,String,string ' Return value: ArrayList<String> ' Created By:
	 * Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> advanceSearchWithEmailAndPhoneNumber(WebDriver driver, String email, String phoneNumber)
			throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.ADVANCE_SEARCH_WITH_EMAIL_ADDRESS);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ADVANCE_SEARCH_WITH_EMAIL_ADDRESS), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.ADVANCE_SEARCH_WITH_EMAIL_ADDRESS), driver);
		reservationSearch.advanceSearchWithEmailAddress.sendKeys(email);
		testSteps.add("Entered email: " + email);
		reservationSearch.advanceSearchWithPhoneNumber.sendKeys(phoneNumber);
		testSteps.add("Entered phone number: " + phoneNumber);

		return testSteps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: AdvanceSearchWithReferral ' Description: Advance search
	 * with referral ' Input parameters: WebDriver,String ' Return value:
	 * ArrayList<String> ' Created By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> advanceSearchWithReferral(WebDriver driver, String referral) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.ADVANCE_SEARCH_WITH_REFERRAL);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ADVANCE_SEARCH_WITH_REFERRAL), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.ADVANCE_SEARCH_WITH_REFERRAL), driver);

		reservationSearch.advanceSearchWithReferral.click();

		String pathReferral = "(//label[text()='Referral']/preceding-sibling::div//ul/li//span[contains(text(),'"
				+ referral.trim() + "')])[1]";
		System.out.println(pathReferral);
		Wait.WaitForElement(driver, pathReferral);
		WebElement elementReferral = driver.findElement(By.xpath(pathReferral));
		Wait.explicit_wait_visibilityof_webelement(elementReferral, driver);
		elementReferral.click();
		testSteps.add("Selected Referral as: " + referral);

		return testSteps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: advanceSearchWithCountry ' Description: Advance search
	 * with country and state ' Input parameters: WebDriver,String,String '
	 * Return value: ArrayList<String> ' Created By: Adnan Ghaffar ' Created On:
	 * 11-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> advanceSearchWithCountry(WebDriver driver, String country) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Wait.WaitForElement(driver, OR.ADVANCE_SEARCH_WITH_COUNTRY);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ADVANCE_SEARCH_WITH_COUNTRY), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.ADVANCE_SEARCH_WITH_COUNTRY), driver);
		Elements_Reservation_SearchPage searchElement = new Elements_Reservation_SearchPage(driver);
		searchElement.advanceSearchWithCountry.click();
		resSearchLogger.info("Advance Search with country dropdown clicked");

		String selectCountry = "//span[contains(text(),'Country')]//..//following-sibling::div//ul//li//span[contains(text(),'"
				+ country.trim() + "')]";
		Wait.WaitForElement(driver, selectCountry);
		Wait.waitForElementToBeVisibile(By.xpath(selectCountry), driver);
		driver.findElement(By.xpath(selectCountry)).click();
		testSteps.add("Selected Country : " + country);
		resSearchLogger.info("Selected Country : " + country);

		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: advanceSearchWithCountry ' Description: Advance search
	 * with country and state ' Input parameters: WebDriver,String,String '
	 * Return value: ArrayList<String> ' Created By: Adnan Ghaffar ' Created On:
	 * 11-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> advanceSearchWithState(WebDriver driver, String state) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Wait.WaitForElement(driver, OR.ADVANCE_SEARCH_WITH_STATE);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ADVANCE_SEARCH_WITH_STATE), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.ADVANCE_SEARCH_WITH_STATE), driver);
		Elements_Reservation_SearchPage searchElement = new Elements_Reservation_SearchPage(driver);
		searchElement.advanceSearchWithState.click();

		String selectState = "//button[@title='State']//..//following-sibling::div//ul//li//span[contains(text(),'"
				+ state + "')]";
		Wait.WaitForElement(driver, selectState);
		Wait.waitForElementToBeVisibile(By.xpath(selectState), driver);
		driver.findElement(By.xpath(selectState)).click();
		testSteps.add("Selected state : " + state);

		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: advanceSearchWithCheckinDate ' Description: Advance search
	 * with Checkin date ' Input parameters: WebDriver,String ' Return value:
	 * ArrayList<String> ' Created By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> advanceSearchWithCheckinDate(WebDriver driver, String checkin)
			throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.ADVANCE_SEARCH_WITH_CHECKIN_DATE);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ADVANCE_SEARCH_WITH_CHECKIN_DATE), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.ADVANCE_SEARCH_WITH_CHECKIN_DATE), driver);
		reservationSearch.advanceSearchWithCheckinDate.sendKeys(checkin);
		testSteps.add("checkin date entered" + checkin);
		return testSteps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: advanceSearchWithCheckoutDate ' Description: Advance
	 * search with Checkout date ' Input parameters: WebDriver,String ' Return
	 * value: ArrayList<String> ' Created By: Adnan Ghaffar ' Created On:
	 * 11-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> advanceSearchWithCheckoutDate(WebDriver driver, String checkout)
			throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.ADVANCE_SEARCH_WITH_CHECKOUT_DATE);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ADVANCE_SEARCH_WITH_CHECKOUT_DATE), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.ADVANCE_SEARCH_WITH_CHECKOUT_DATE), driver);
		reservationSearch.advanceSearchWithCheckoutDate.sendKeys(checkout);
		testSteps.add("checkout date entered" + checkout);
		return testSteps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: clickOnAdvanceSearchButton ' Description: click on advance
	 * search button ' Input parameters: WebDriver ' Return value:
	 * ArrayList<String> ' Created By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> clickOnSearchButton(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.searchButton);
		Wait.waitForElementToBeVisibile(By.xpath(OR.searchButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.searchButton), driver);
		reservationSearch.searchButton.click();
		testSteps.add("Click on search button");

		return testSteps;
	}

	/*
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 * 
	 * ' Method Name: getGuestName ' Description: get guest name from table
	 * after search ' Input parameters: WebDriver,String,int ' Return value:
	 * String ' Created By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 */
	public String getGuestNameAfterSearch(WebDriver driver, int index) throws InterruptedException {

		String path = "(//a[contains(@data-bind,'text: GuestName')])[" + index + "]";
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);
		WebElement element = driver.findElement(By.xpath(path));
		String guestName = element.getAttribute("title");
		return guestName;
	}

	/*
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 * 
	 * ' Method Name: getReservationNumberAfterSearch ' Description: get
	 * reservation number from search table ' Input parameters: Webdriver,int '
	 * Return value: String ' Created By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 */

	public String getReservationNumberAfterSearch(WebDriver driver, int index) throws InterruptedException {

		String path = "(//span[contains(@data-bind,'text: ConfirmationNumber')])[" + index + "]";
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);
		WebElement element = driver.findElement(By.xpath(path));
		String reservationNumber = element.getText();
		return reservationNumber;
	}

	/*
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 * 
	 * ' Method Name: getAdultsAfterSearch ' Description: get Adults from search
	 * table ' Input parameters: Webdriver,int ' Return value: String ' Created
	 * By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 */
	public String getAdultsAfterSearch(WebDriver driver, int index) throws InterruptedException {

		String path = "(//span[contains(@data-bind,'text: NumberOfAdults')])[" + index + "]";
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);
		WebElement element = driver.findElement(By.xpath(path));
		String adults = element.getText();
		return adults;
	}

	/*
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 * 
	 * ' Method Name: getChildrenAfterSearch ' Description: get children from
	 * search table ' Input parameters: Webdriver,int ' Return value: String '
	 * Created By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 */
	public String getChildrenAfterSearch(WebDriver driver, int index) throws InterruptedException {

		String path = "(//span[contains(@data-bind,'text: NumberOfChildren')])[" + index + "]";
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);
		WebElement element = driver.findElement(By.xpath(path));
		String children = element.getText();
		return children;
	}

	/*
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 * 
	 * ' Method Name: getRoomAfterSearch ' Description: get room from search
	 * table ' Input parameters: Webdriver,int ' Return value: String ' Created
	 * By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 */

	public String getRoomAfterSearch(WebDriver driver, int index) throws InterruptedException {

		String path = "(//span[contains(@data-bind,'text: RoomClassName')])[" + index + "]";
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);
		WebElement element = driver.findElement(By.xpath(path));
		String room = element.getText();
		return room;
	}

	/*
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 * 
	 * ' Method Name: getArrivalDateAfterSearch ' Description: get arrival date
	 * from search table ' Input parameters: Webdriver,int ' Return value:
	 * String ' Created By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 */
	public String getArrivalDateAfterSearch(WebDriver driver, int index) throws InterruptedException {

		String path = "(//span[contains(@data-bind,'text: DateStart')])[" + index + "]";
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);
		WebElement element = driver.findElement(By.xpath(path));
		String arrivalDate = element.getText();
		return arrivalDate;
	}

	/*
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 * 
	 * ' Method Name: getDepartDateAfterSearch ' Description: get departure date
	 * from search table ' Input parameters: Webdriver,int ' Return value:
	 * String ' Created By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 */
	public String getDepartDateAfterSearch(WebDriver driver, int index) throws InterruptedException {

		String path = "(//span[contains(@data-bind,'text: DateEnd')])[" + index + "]";
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);
		WebElement element = driver.findElement(By.xpath(path));
		String departDate = element.getText();
		return departDate;
	}

	/*
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 * 
	 * ' Method Name: getNightsAfterSearch ' Description: get nights from search
	 * table ' Input parameters: Webdriver,int ' Return value: String ' Created
	 * By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 */
	public String getNightsAfterSearch(WebDriver driver, int index) throws InterruptedException {

		String path = "(//span[contains(@data-bind,'text: NoOfNights')])[" + index + "]";
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);
		WebElement element = driver.findElement(By.xpath(path));
		String nights = element.getText();
		return nights;
	}

	/*
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 * 
	 * ' Method Name: enterAccountNumber ' Description: get nights from search
	 * table ' Input parameters: Webdriver,ArrayList<String>,String ' Return
	 * value: void ' Created By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 */

	public void enterAccountNumber(WebDriver driver, ArrayList<String> test_steps, String Account)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Account);
		String accountdisplay = "//label[contains(text(),'Account')]/..//i[starts-with(@class,'rightIcon demo-icon icon-pencil')]";
		WebElement ele = driver.findElement(By.xpath(accountdisplay));
		if (!ele.isDisplayed()) {
			if (Account.equalsIgnoreCase("") || Account.isEmpty()) {
				System.out.println("No Account");
			} else {
				res.CP_NewReservation_Account.sendKeys(Account);
				test_steps.add("Enter Account : " + Account);
				Wait.wait2Second();
				String account = "//b[contains(text(),'" + Account.trim() + "')]/../../..";
				Wait.WaitForElement(driver, account);
				driver.findElement(By.xpath(account)).click();
				String dataYes = "//div[contains(text(),'Do you want to replace the guest info')]/following-sibling::div//button[contains(text(),'Yes')]";
				Wait.WaitForElement(driver, dataYes);
				driver.findElement(By.xpath(dataYes)).click();
				test_steps.add(
						"Do you want to replace the guest info, payment method, marketing info and notes in this reservation with the information from the account? Clicking yes will save all the account info to the reservation. : Yes");
			}
		}
	}

	/*
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 * 
	 * ' Method Name: getAccountName ' Description: get attached account name
	 * after creation of new reservation ' Input parameters: Webdriver ' Return
	 * value: String ' Created By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 */

	public String getAccountName(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.VERIFY_ACCOUNT_NAME);
		Wait.waitForElementToBeVisibile(By.xpath(OR.VERIFY_ACCOUNT_NAME), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.VERIFY_ACCOUNT_NAME), driver);
		Utility.ScrollToElement_NoWait(reservationSearch.verifyAccountName, driver);
		String accountName = reservationSearch.verifyAccountName.getText();
		return accountName;
	}
	/*
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 * 
	 * ' Method Name: getReservationStatusAfterReservation ' Description: get
	 * reservation status from search table ' Input parameters: Webdriver,int '
	 * Return value: String ' Created By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 */

	public String getReservationStatusAfterSearch(WebDriver driver, int index) {
		String path = "(//span[contains(@data-bind,'StatusName.replace')])[" + index + "]";
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);
		WebElement element = driver.findElement(By.xpath(path));
		String status = element.getText();
		return status;

	}

	/*
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 * 
	 * ' Method Name: clickOnPrintIcon ' Description: click on report print icon
	 * ' Input parameters:WebDriver ' Return value: void ' Created By: Adnan
	 * Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 */

	public ArrayList<String> clickOnPrintIcon(WebDriver driver) throws InterruptedException, IOException {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.PRINT_ICON);
		Wait.waitForElementToBeVisibile(By.xpath(OR.PRINT_ICON), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.PRINT_ICON), driver);
		reservationSearch.printIcon.click();
		testSteps.add("Click on print icon");

		return testSteps;

	}
	/*
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 * 
	 * ' Method Name: downloadPDFReport ' Description: download PDF Report of
	 * Guest Reservation ' Input parameters: WebDriver ' Return value: String '
	 * Created By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 */

	public void downloadPDFReport(WebDriver driver) throws InterruptedException, IOException {

		Wait.wait15Second();
		Wait.waitForElementToBeGone(driver,
				driver.findElement(By.xpath("//*[@id='bpjscontainer_23']/div[2]//div[@class='modules_loading']")),
				10000);
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		Wait.wait10Second();
		resSearchLogger.info(tabs2.size());
		driver.switchTo().window(tabs2.get(1));
		Wait.wait2Second();

		new Select(driver.findElement(By.xpath("//*[@id='drpReportType']"))).selectByVisibleText("Download As Pdf");
		driver.findElement(By.xpath("//*[@id='btnImgDownload']")).click();
		Wait.wait3Second();

		driver.close();
		driver.switchTo().window(tabs2.get(0));

	}
	/*
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 * 
	 * ' Method Name: clickOnPrintButton ' Description: click on print button '
	 * Input parameters: WebDriver ' Return value: ArrayList<String> ' Created
	 * By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 */

	public ArrayList<String> clickOnPrintButton(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Reservation_SearchPage element = new Elements_Reservation_SearchPage(driver);

		Wait.WaitForElement(driver, OR.CLICK_ON_PRINT_BUTTON);
		Wait.waitForElementToBeVisibile(By.xpath(OR.CLICK_ON_PRINT_BUTTON), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.CLICK_ON_PRINT_BUTTON), driver);
		if (!element.radioButtonListReport.isSelected()) {
			element.radioButtonListReport.click();
			testSteps.add("List report is selected");
		} else {
			testSteps.add("List report is selected");
		}

		if (!element.CheckBoxIncludeTotalRevenue.isSelected()) {
			element.CheckBoxIncludeTotalRevenue.click();
			testSteps.add("Include total revenue is selected");
		} else {
			testSteps.add("Include total revenue is selected");
		}

		element.clickOnPrintButton.click();
		testSteps.add("Click print button");

		return testSteps;
	}

	/*
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 * 
	 * ' Method Name: clickOnAdvanceSearch ' Description: click on advanced '
	 * Input parameters: WebDriver ' Return value: String ' Created By: Adnan
	 * Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 */
	public ArrayList<String> clickOnAdvance(WebDriver driver) throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Reservation_SearchPage element = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.CLICK_ON_ADVANCE_SEARCH);
		Wait.waitForElementToBeVisibile(By.xpath(OR.CLICK_ON_ADVANCE_SEARCH), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.CLICK_ON_ADVANCE_SEARCH), driver);
		element.clickOnAdvanceSearch.click();
		testSteps.add("Click on advanced");

		return testSteps;

	}
	/*
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 * 
	 * ' Method Name: verifyReservationStatusWithName ' Description:
	 * verification of Reservation Status After Basic Search ' Input parameters:
	 * WebDriver, String, ArrayList<string> ' Return value: ArrayList<String> '
	 * Created By: Aqsa Manzoor ' Created On: 15-05-2020
	 * 
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 */

	public ArrayList<String> verifyReservationStatusWithName(WebDriver driver, String GuestName, String Status,
			ArrayList<String> test_steps) throws InterruptedException {
		WebElement reservationStatusElement = driver.findElement(By.xpath("//a[text()='" + GuestName
				+ "']/ancestor::td/following-sibling::td//span[contains(@data-bind, 'text: StatusName')]"));
		String ReservationStatus = reservationStatusElement.getText();
		assertTrue(ReservationStatus.equals(Status), "Reservation Status isn't correct");
		resSearchLogger.info("Reservation Status Verified");
		test_steps.add(GuestName + " Reservation Status : " + Status + " Verified");
		return test_steps;
	}

	public void basicSearchWithResNumber(WebDriver driver, String reservationNumber, boolean isClick)
			throws InterruptedException {

		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.Basic_Res_Number);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Basic_Res_Number), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Basic_Res_Number), driver);

		resservationSearch.Basic_Res_Number.clear();
		resservationSearch.Basic_Res_Number.sendKeys(reservationNumber);
		resservationSearch.Click_BasicSearch.click();
		Wait.WaitForElement(driver, OR.Verify_Search_Loading_Gird);

		Wait.WaitForElement(driver, OR.Get_Res_Number);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Get_Res_Number), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Get_Res_Number), driver);
		String getReservationNumber = resservationSearch.Get_Res_Number.getText();
		assertEquals(getReservationNumber, reservationNumber, "Failed: Reservatio Found");
		String resservationLocator = "//span[contains(text(),'" + reservationNumber.trim() + "')]/../../td[4]/div/a";
		WebElement ReservationFound = driver.findElement(By.xpath(resservationLocator));
		if (isClick) {
			ReservationFound.click();
			Utility.app_logs.info("Reservation Opened Successfully");
		}
	}

	public ArrayList<String> OpenSearchedReservation(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.WaitForElement(driver, OR.clickReservation);
		Wait.waitForElementToBeVisibile(By.xpath(OR.clickReservation), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.clickReservation), driver);
		reservationSearch.clickReservation.click();
		steps.add("Reservation Page opened Successfully");
		resSearchLogger.info("Reservation Page opened Successfully");

		return steps;
	}

	public void clickCloseAdvanceSearchButton(WebDriver driver) throws InterruptedException {
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);

		Wait.WaitForElement(driver, OR.ClickOnCloseAdvanceSearch);
		Utility.ScrollToElement(reservationSearch.ClickOnCloseAdvanceSearch, driver);
		reservationSearch.ClickOnCloseAdvanceSearch.click();
	}

	public void closeReservation(WebDriver driver, String guestName) throws InterruptedException {
		String path = "//div/ul[@class='sec_nav']//li/a//span[contains(text(),'" + guestName + "')]/parent::a/i";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		element.click();

	}

}