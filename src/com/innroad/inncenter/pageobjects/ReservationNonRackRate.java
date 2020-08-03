package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.innroad.inncenter.interfaces.IReservationNonRackRate;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Reservation;

public class ReservationNonRackRate implements IReservationNonRackRate {

	public static Logger resNonRackRateLogger = Logger.getLogger("ReservationNonRackRate");

	public void roomAssignment(WebDriver driver, String PropertyName1, String Nights, String Adults, String Children,
			String RatepromoCode, String CheckorUncheckAssign, String RoomClassName, String RoomNumber, String RatePlan)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.Click_RoomPicker.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait3Second();
		/*
		 * try { new Select(ReservationPage.Select_property_RoomAssign).
		 * selectByVisibleText(PropertyName); } catch(Exception e) { new
		 * Select(ReservationPage.Select_property_RoomAssign2).
		 * selectByVisibleText(PropertyName); }
		 */
		Wait.wait15Second();
		ReservationPage.Click_Arrive_Datepicker.click();
		ReservationPage.Click_Today.click();
		ReservationPage.Enter_Nigts.clear();
		ReservationPage.Enter_Nigts.sendKeys(Nights);
		ReservationPage.Enter_Adults.sendKeys(Adults);
		ReservationPage.Enter_Children.sendKeys(Children);
		// ReservationPage.Enter_Rate_Promocode.sendKeys(RatepromoCode);
		ReservationPage.selectRackRate.click();
		new Select(ReservationPage.selectRackRate).selectByVisibleText(RatePlan);
		;
		if (ReservationPage.Check_Assign_Room.isSelected()) {
			// ReservationPage.Check_Assign_Room.click();
			ReservationPage.Click_Search.click();
		} else {
			if (CheckorUncheckAssign.equals("Yes")) {
				ReservationPage.Check_Assign_Room.click();
				ReservationPage.Click_Search.click();
			} else {
				ReservationPage.Click_Search.click();
			}
		}
		try {

			new Select(ReservationPage.Select_Room_Class).selectByIndex(1);
			String selectedOption = new Select(ReservationPage.Validating_UnAssgined_DDL).getFirstSelectedOption()
					.getText();
			resNonRackRateLogger.info("selectedOption  " + selectedOption);
			if (selectedOption.equals("--Select--")) {
				// new
				// Select(ReservationPage.Select_Room_Number).selectByVisibleText(RoomNumber);
				new Select(ReservationPage.Select_Room_Number).selectByIndex(1);
				Wait.wait5Second();
			} else {
				resNonRackRateLogger.info("Reservation is unassigned");
			}
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.Validation_Text_NoRooms, driver);
			resNonRackRateLogger.info("Room Class are not Available for these dates");

		}
		ReservationPage.Click_Select.click();
		try {
			Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
		} catch (Exception e) {
			resNonRackRateLogger.info("Verification failed");
		}
		Wait.wait5Second();
		if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
			ReservationPage.Click_Continue_RuleBroken_Popup.click();
		} else {
			resNonRackRateLogger.info("Satisfied Rules");
		}

		if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
			String getTotasterTitle = ReservationPage.Toaster_Title.getText();
			String getToastermessage = ReservationPage.Toaster_Message.getText();
			resNonRackRateLogger.info(getTotasterTitle + " " + getToastermessage);
			Assert.assertEquals(getTotasterTitle, "Room assignment has changed.");
			Assert.assertEquals(getToastermessage, "Room assignment has changed.");
		}
		Wait.wait2Second();
		String getPropertyName = ReservationPage.Get_Property_Name.getText();
		String getRoomclassName_status = ReservationPage.Get_RoomClass_Status.getText();
		resNonRackRateLogger.info(getRoomclassName_status);
		// Assert.assertEquals(getPropertyName,PropertyName );
		String getRoomclassName[] = getRoomclassName_status.split(" ");
		// Assert.assertEquals(getRoomclassName[0],RoomClassName );
		if (CheckorUncheckAssign.equals("Yes")) {

		} else {
			Assert.assertEquals(getRoomclassName[3], "Unassigned");
		}

	}

	public void roomAssignment(WebDriver driver, String PropertyName1, String Nights, String Adults, String Children,
			String RatepromoCode, String CheckorUncheckAssign, String RoomClassName, String RoomNumber, String RatePlan,
			String AdhocRate) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.Click_RoomPicker.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Arrive_Datepicker, driver);
		ReservationPage.Click_Arrive_Datepicker.click();
		ReservationPage.Click_Today.click();
		ReservationPage.Enter_Nigts.clear();
		ReservationPage.Enter_Nigts.sendKeys(Nights);
		ReservationPage.Enter_Adults.sendKeys(Adults);
		ReservationPage.Enter_Children.sendKeys(Children);
		ReservationPage.selectRackRate.click();
		Select SelectedOption = new Select(ReservationPage.selectRackRate);
		new Select(ReservationPage.selectRackRate).selectByVisibleText(RatePlan);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.AdhocRate, driver);
		ReservationPage.AdhocRate.sendKeys(AdhocRate);
		WebElement SelectedRate = SelectedOption.getFirstSelectedOption();

		assertTrue(SelectedRate.getText().equals(RatePlan), " Rate didn't select correctly");

		if (ReservationPage.Check_Assign_Room.isSelected() & CheckorUncheckAssign.equals("Yes")) {
			ReservationPage.Click_Search.click();
		} else {
			if (CheckorUncheckAssign.equals("Yes")) {
				ReservationPage.Check_Assign_Room.click();
				ReservationPage.Click_Search.click();
			} else {
				ReservationPage.Click_Search.click();
			}
		}
		try {

			new Select(ReservationPage.Select_Room_Class).selectByVisibleText(RoomClassName);
			String selectedOption = new Select(ReservationPage.Validating_UnAssgined_DDL).getFirstSelectedOption()
					.getText();
			resNonRackRateLogger.info("selectedOption  " + selectedOption);
			if (selectedOption.equals("--Select--")) {
				// new
				// Select(ReservationPage.Select_Room_Number).selectByVisibleText(RoomNumber);
				new Select(ReservationPage.Select_Room_Number).selectByIndex(1);
				Wait.wait5Second();
			} else {
				resNonRackRateLogger.info("Reservation is unassigned");
			}
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.Validation_Text_NoRooms, driver);
			resNonRackRateLogger.info("Room Class are not Available for these dates");

		}
		ReservationPage.Click_Select.click();
		try {
			Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
		} catch (Exception e) {
			resNonRackRateLogger.info("Verification failed");
		}
		Wait.wait5Second();
		if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
			ReservationPage.Click_Continue_RuleBroken_Popup.click();
		} else {
			resNonRackRateLogger.info("Satisfied Rules");
		}

//		if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
//			String getTotasterTitle = ReservationPage.Toaster_Title.getText();
//			String getToastermessage = ReservationPage.Toaster_Message.getText();
//			resNonRackRateLogger.info(getTotasterTitle + " " + getToastermessage);
//			Assert.assertEquals(getTotasterTitle, "Room assignment has changed.");
//			Assert.assertEquals(getToastermessage, "Room assignment has changed.");
//		}
		Wait.wait2Second();
		String getPropertyName = ReservationPage.Get_Property_Name.getText();
		String getRoomclassName_status = ReservationPage.Get_RoomClass_Status.getText();
		resNonRackRateLogger.info(getRoomclassName_status);
		// Assert.assertEquals(getPropertyName,PropertyName );
		String getRoomclassName[] = getRoomclassName_status.split(" ");
		// Assert.assertEquals(getRoomclassName[0],RoomClassName );
		if (CheckorUncheckAssign.equals("Yes")) {

		} else {
			Assert.assertEquals(getRoomclassName[3], "Unassigned");
		}

	}
	
	public void VerifyRateAssigned(WebDriver driver, String RatePlan) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		WebElement RateAttached = driver
				.findElement(By.xpath("(//a[contains(@data-bind,'parent.fnPayLineItemDetail')])[1]"));
		assertTrue(RateAttached.getText().equals(RatePlan), "Rate didnt attached correctly");

	}


	public ArrayList<String> roomAssignment(WebDriver driver, String CheckorUncheckAssign, String RoomClassName,
			String RatePlan, String AdhocRate, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.Click_RoomPicker.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_Arrive_Datepicker, driver);
		ReservationPage.Click_Arrive_Datepicker.click();
		ReservationPage.Click_Today.click();
		ReservationPage.selectRackRate.click();
		new Select(ReservationPage.selectRackRate).selectByVisibleText(RatePlan);
		test_steps.add("Select Rate Plan : " + RatePlan);
		Utility.app_logs.info("Select Rate Plan : " + RatePlan);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.AdhocRate, driver);
		test_steps.add("Enter Adhoc rate : " + AdhocRate);
		Utility.app_logs.info("Enter Adhoc rate : " + AdhocRate);
		ReservationPage.AdhocRate.sendKeys(AdhocRate);

		if (ReservationPage.Check_Assign_Room.isSelected() & CheckorUncheckAssign.equals("Yes")) {
			ReservationPage.Click_Search.click();
		} else {
			if (CheckorUncheckAssign.equals("Yes")) {
				ReservationPage.Check_Assign_Room.click();
				ReservationPage.Click_Search.click();
			} else {
				ReservationPage.Click_Search.click();
			}
		}
		try {

			new Select(ReservationPage.Select_Room_Class).selectByVisibleText(RoomClassName);
			String selectedOption = new Select(ReservationPage.Validating_UnAssgined_DDL).getFirstSelectedOption()
					.getText();
			resNonRackRateLogger.info("selectedOption  " + selectedOption);
			if (selectedOption.equals("--Select--")) {
				// new
				// Select(ReservationPage.Select_Room_Number).selectByVisibleText(RoomNumber);
				new Select(ReservationPage.Select_Room_Number).selectByIndex(1);
				Wait.wait5Second();
			} else {
				resNonRackRateLogger.info("Reservation is unassigned");
			}
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.Validation_Text_NoRooms, driver);
			resNonRackRateLogger.info("Room Class are not Available for these dates");

		}
		ReservationPage.Click_Select.click();
		try {
			Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
		} catch (Exception e) {
			resNonRackRateLogger.info("Verification failed");
		}
		Wait.wait5Second();
		if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
			ReservationPage.Click_Continue_RuleBroken_Popup.click();
		} else {
			resNonRackRateLogger.info("Satisfied Rules");
		}

		if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
			String getTotasterTitle = ReservationPage.Toaster_Title.getText();
			String getToastermessage = ReservationPage.Toaster_Message.getText();
			resNonRackRateLogger.info(getTotasterTitle + " " + getToastermessage);
			Assert.assertEquals(getTotasterTitle, "Room assignment has changed.");
			Assert.assertEquals(getToastermessage, "Room assignment has changed.");
		}
		Wait.wait2Second();
		String getPropertyName = ReservationPage.Get_Property_Name.getText();
		String getRoomclassName_status = ReservationPage.Get_RoomClass_Status.getText();
		resNonRackRateLogger.info(getRoomclassName_status);
		// Assert.assertEquals(getPropertyName,PropertyName );
		String getRoomclassName[] = getRoomclassName_status.split(" ");
		// Assert.assertEquals(getRoomclassName[0],RoomClassName );
		if (CheckorUncheckAssign.equals("Yes")) {

		} else {
			Assert.assertEquals(getRoomclassName[3], "Unassigned");
		}
		return test_steps;

	}

	public void VerifyOverRideInRoomAssignmentPopup(WebDriver driver, String AdhocRate) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_RoomPicker, driver);
		Utility.ScrollToElement(ReservationPage.Click_RoomPicker, driver);
		ReservationPage.Click_RoomPicker.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.AdhocRate, driver);
		Assert.assertEquals(ReservationPage.AdhocRate.getAttribute("value"), AdhocRate,
				"Failed: OverRideRate missmatched");
	}

}
