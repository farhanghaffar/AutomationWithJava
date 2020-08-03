package com.innroad.inncenter.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.innroad.inncenter.interfaces.IReservationCopy_Assigned;
import com.innroad.inncenter.pages.NewReservation;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Reservation;
import com.innroad.inncenter.webelements.Elements_Reservation_CopyPage;

public class ReservationCopy_Assigned implements IReservationCopy_Assigned {

	public static String nextRoom;

	public static String copiedNextRoom;

	public static Logger resCopyAssignedLogger = Logger.getLogger("ReservationCopy_Assigned");
	
	public  ArrayList<String> contactInformation(WebDriver driver, String saluation, String FirstName, String LastName,
			String Address, String Line1, String Line2, String Line3, String City, String Country, String State,
			String Postalcode, String Phonenumber, String alternativenumber, String Email, String Account,
			String IsTaxExempt, String TaxEmptext, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Select_Saluation, driver);
		Utility.ScrollToElement(ReservationPage.Select_Saluation, driver);
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
		resCopyAssignedLogger.info("FirstName: " + FirstName);
		
		ReservationPage.Enter_Last_Name.sendKeys(LastName);
		resCopyAssignedLogger.info("LastName: " + LastName);
		
		ReservationPage.Enter_Address_Search.sendKeys(Address);
		resCopyAssignedLogger.info("Address: " + Address);
		
		
		
		ReservationPage.Enter_Line1.sendKeys(Line1);
		ReservationPage.Enter_Line2.sendKeys(Line2);
		ReservationPage.Enter_Line3.sendKeys(Line3);
		ReservationPage.Enter_City.sendKeys(City);
		resCopyAssignedLogger.info("City: " + City);
		
		new Select(ReservationPage.Select_Country).selectByVisibleText(Country);
		resCopyAssignedLogger.info("Country: " + new Select(ReservationPage.Select_Country).getFirstSelectedOption().getText());
		
		new Select(ReservationPage.Select_State).selectByVisibleText(State);
		resCopyAssignedLogger.info("State: " + new Select(ReservationPage.Select_State).getFirstSelectedOption().getText());
		//String guestState=new Select(ReservationPage.Select_State).getFirstSelectedOption().getText();
		ReservationPage.Enter_Postal_Code.clear();
		ReservationPage.Enter_Postal_Code.sendKeys(Postalcode);
		resCopyAssignedLogger.info("Postal_Code: " + Postalcode);
		
		ReservationPage.Enter_Phone_Number.clear();
		ReservationPage.Enter_Phone_Number.sendKeys(Phonenumber);
		resCopyAssignedLogger.info("Pone Number: " + Phonenumber);
	
		ReservationPage.Enter_Alt_Phn_number.clear();
		ReservationPage.Enter_Alt_Phn_number.sendKeys(alternativenumber);
		resCopyAssignedLogger.info("Alternative Pone Number: " + alternativenumber);
		
		
		ReservationPage.Enter_email.clear();
		ReservationPage.Enter_email.sendKeys(Email);
		resCopyAssignedLogger.info("Email: " + Email);
		
		resCopyAssignedLogger.info("Entered required contact information");
		
		
		
		try {
			ReservationPage.Enter_Account.sendKeys(Account);
		} catch (Exception e) {

		}

		if (IsTaxExempt.equals("Yes")) {
			if (ReservationPage.Check_IsTaxExempt.isSelected()) {
				ReservationPage.Enter_TaxExemptId.sendKeys(TaxEmptext);
				resCopyAssignedLogger.info("Entered TaxExcemptID");
			} else {
				ReservationPage.Check_IsTaxExempt.click();
				resCopyAssignedLogger.info("Clicked on TaxExcempt checkbox");
				ReservationPage.Enter_TaxExemptId.sendKeys(TaxEmptext);
				resCopyAssignedLogger.info("Entered TaxExcemptID");
			}
			
			
				
		}
		
		
		return test_steps;
		
		
	}

	public ArrayList<String> copyReservation(WebDriver driver, ArrayList<String> getTest_Steps) throws InterruptedException {

		Elements_Reservation_CopyPage res_copy = new Elements_Reservation_CopyPage(driver);

		// Wait.explicit_wait_visibilityof_webelement(res_copy.click_Copy_Button);
		Wait.WaitForElement(driver, OR.click_Copy_Button);
		res_copy.click_Copy_Button.click();
		resCopyAssignedLogger.info(" Clicked on Copy button ");
		getTest_Steps.add(" Clicked on Copy button ");
		Wait.WaitForElement(driver, OR.click_copiedRoomPicker);
		//Wait.explicit_wait_visibilityof_webelement_120(res_copy.click_copiedRoomPicker, driver);

		Utility.ScrollToElement(res_copy.click_copiedRoomPicker, driver);
		res_copy.click_copiedRoomPicker.click();

		Thread.sleep(5000);
		Wait.WaitForElement(driver, OR.get_Room_Number);
		Select getRoomNumber = new Select(res_copy.get_Room_Number);
		copiedNextRoom = getRoomNumber.getFirstSelectedOption().getText();
		// String copiedNextRoom=res_copy.get_copiedRoom_Number.getText();

		// Assert.assertEquals( nextRoom, copiedNextRoom);
		resCopyAssignedLogger.info("Picked next available room of assigned reservation: " + copiedNextRoom);
		getTest_Steps.add("Picked next available room of assigned reservation: " + copiedNextRoom);
		// Assert.assertEquals( nextRoom,copiedNextRoom);
		// Assert.assertEquals( nextRoom, nextRoomCopied);
		// Assert.assertEquals( nextRoom, copiedNextRoom);

		res_copy.click_cancel_button.click();
		Thread.sleep(8000);

		resCopyAssignedLogger.info(" Reservation Copied ");
		//Thread.sleep(8000);
		Wait.WaitForElement(driver, OR.get_Copied_Guest_Name);
		Wait.WaitForElement(driver, OR.get_Copied_Guest_Name);
		String copiedGuestName = res_copy.get_Copied_Guest_Name.getText();
		resCopyAssignedLogger.info(" Name of the Copied Guest Name is " +copiedGuestName);
		//getTest_Steps.add(" Name of the Copied Guest Name is " +copiedGuestName);
		Thread.sleep(5000);
		return getTest_Steps;

	}
	
	public ArrayList<String> getCopiedGuestProfile(WebDriver driver,String FirstName, String LastName,String Line1, String City,String State, String Country,String Postalcode,String Phonenumber,String Email, ArrayList<String> getTest_Steps ) {
		Elements_Reservation_CopyPage res_copy = new Elements_Reservation_CopyPage(driver);
		//Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.Copied_First_Name);
		

		if(!FirstName.equals(res_copy.Copied_First_Name.getAttribute("value"))&&
			!LastName.equals(res_copy.Copied_Last_Name.getAttribute("value"))&&
			Line1.equals(res_copy.Copied_Line1.getAttribute("value"))&&
			City.equals(res_copy.Copied_City.getAttribute("value"))&&
			State.equals(new Select(res_copy.CopiedSelect_State).getFirstSelectedOption().getText()) &&
			Country.equals(new Select(res_copy.CopiedSelect_Country).getFirstSelectedOption().getText()) &&
			Postalcode.equals(res_copy.Copied_Postal_Code.getAttribute("value")) &&
			Phonenumber.equals(res_copy.Copied_Phone_Number.getAttribute("value")) &&
			Email.equals(res_copy.Copied_email.getAttribute("value"))){

			
			resCopyAssignedLogger.info("****guest profile is copied*****");
			getTest_Steps.add("****guest profile is copied*****");
			
		}
		return getTest_Steps;
	}
	
	
	public ArrayList<String> navigateFolioRes2(WebDriver driver, ArrayList<String> getTest_Steps) throws InterruptedException {
		Elements_Reservation_CopyPage res_copy = new Elements_Reservation_CopyPage(driver);
		Wait.WaitForElement(driver, OR.switchToFolio2);
		res_copy.switchToFolio2.click();
		resCopyAssignedLogger.info("Switched to Folio");
		Wait.wait5Second();
		return getTest_Steps;
		
	}
	
	
	public void roomAssignment(WebDriver driver, String PropertyName, String Nights, String Adults, String Children,
			String RatepromoCode, String CheckorUncheckAssign, String RoomClassName, String RoomNumber)
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
		ReservationPage.Enter_Rate_Promocode.sendKeys(RatepromoCode);

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

			new Select(ReservationPage.Select_Room_Class).selectByVisibleText(RoomClassName);
			String selectedOption = new Select(ReservationPage.Validating_UnAssgined_DDL).getFirstSelectedOption()
					.getText();
			resCopyAssignedLogger.info("selectedOption  " + selectedOption);
			Wait.wait5Second();
			if (selectedOption.equals("--Select--")) {
				// new
				// Select(ReservationPage.Select_Room_Number).selectByVisibleText(RoomNumber);
				new Select(ReservationPage.Select_Room_Number).selectByIndex(5);
				String selectedRoom = new Select(ReservationPage.Select_Room_Number).getFirstSelectedOption().getText();

				resCopyAssignedLogger.info(" Selected Room number is " + selectedRoom);

				Thread.sleep(5000);
				Select getRoomNumber = new Select(ReservationPage.Select_Room_Number);

				List<WebElement> getAllRoomNumbers = getRoomNumber.getOptions();

				for (WebElement getEachRoomNumber : getAllRoomNumbers)
					// resCopyAssignedLogger.info(getEachRoomNumber.getText());
					// resCopyAssignedLogger.info("Total no of rooms "
					// +getAllRoomNumbers.size());
					nextRoom = getAllRoomNumbers.get(1).getText();
				resCopyAssignedLogger.info(" Next  availble Room is " + nextRoom);
				WebElement EachRoom = getRoomNumber.getFirstSelectedOption();
				resCopyAssignedLogger.info("The Selected Rooom Number is " + EachRoom.getText());
				Wait.wait5Second();
			} else {
				resCopyAssignedLogger.info("Reservation is unassigned");
			}
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.Validation_Text_NoRooms, driver);
			resCopyAssignedLogger.info("Room Class are not Available for these dates");

		}
		ReservationPage.Click_Select.click();
		try {
			Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
		} catch (Exception e) {
			resCopyAssignedLogger.info("Verification failed");
		}
		Wait.wait5Second();
		if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
			ReservationPage.Click_Continue_RuleBroken_Popup.click();
		} else {
			resCopyAssignedLogger.info("Satisfied Rules");
		}

		if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
			String getTotasterTitle = ReservationPage.Toaster_Title.getText();
			String getToastermessage = ReservationPage.Toaster_Message.getText();
			resCopyAssignedLogger.info(getTotasterTitle + " " + getToastermessage);
			Assert.assertEquals(getTotasterTitle, "Room assignment has changed.");
			Assert.assertEquals(getToastermessage, "Room assignment has changed.");
		}
		Wait.wait2Second();
		String getPropertyName = ReservationPage.Get_Property_Name.getText();
		String getRoomclassName_status = ReservationPage.Get_RoomClass_Status.getText();
		resCopyAssignedLogger.info(getRoomclassName_status);
		Assert.assertEquals(getPropertyName, PropertyName);
		String getRoomclassName[] = getRoomclassName_status.split(" ");
		// Assert.assertEquals(getRoomclassName[0],RoomClassName );
		if (CheckorUncheckAssign.equals("Yes")) {

		}

	}

	public void saveReservation(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		double waittime = 0.12;
		long startTime = System.currentTimeMillis();
		ReservationPage.Click_Save_ReservationDetails.click();
		Wait.wait3Second();
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
				long endTime = System.currentTimeMillis();
				double totalTime = (endTime - startTime);
				// resCopyAssignedLogger.info(totalTime + " in Millsecs");
				double TotalTimeinsecs = totalTime / 1000;
				double ActualTime = TotalTimeinsecs - waittime - 3;
				// resCopyAssignedLogger.info(ActualTime + " in secs");
				if (getToastermessage_ReservationSucess.endsWith("has been saved successfully"))
					;

			}
		} catch (Exception e) {

		}
		Wait.wait10Second();

	}

	public void copyReservation(WebDriver driver) throws InterruptedException {

		Elements_Reservation_CopyPage res_copy = new Elements_Reservation_CopyPage(driver);

		// Wait.explicit_wait_visibilityof_webelement(res_copy.click_Copy_Button);
		Thread.sleep(5000);
		res_copy.click_Copy_Button.click();

		Wait.explicit_wait_visibilityof_webelement_120(res_copy.click_copiedRoomPicker, driver);

		Utility.ScrollToElement(res_copy.click_copiedRoomPicker, driver);
		res_copy.click_copiedRoomPicker.click();

		Thread.sleep(5000);
		Select getRoomNumber = new Select(res_copy.get_Room_Number);
		copiedNextRoom = getRoomNumber.getFirstSelectedOption().getText();
		// String copiedNextRoom=res_copy.get_copiedRoom_Number.getText();

		// Assert.assertEquals( nextRoom, copiedNextRoom);
		resCopyAssignedLogger.info(" Copied Next room Number is " + copiedNextRoom);

		// Assert.assertEquals( nextRoom,copiedNextRoom);
		// Assert.assertEquals( nextRoom, nextRoomCopied);
		// Assert.assertEquals( nextRoom, copiedNextRoom);

		res_copy.click_cancel_button.click();
		Thread.sleep(8000);

		resCopyAssignedLogger.info(" Reservation Copied ");
		Thread.sleep(8000);
		String copiedGuestName = res_copy.get_Copied_Guest_Name.getText();
		resCopyAssignedLogger.info(" Name of the Copied Guest Name is " + copiedGuestName);
		Thread.sleep(5000);

	}
	
	public ArrayList<String> CopyReservation_Overbooking(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation_CopyPage res_copy = new Elements_Reservation_CopyPage(driver);

		Wait.explicit_wait_visibilityof_webelement(res_copy.click_Copy_Button, driver);
		//Thread.sleep(5000);
		Utility.ScrollToElement(res_copy.click_Copy_Button, driver);
		res_copy.click_Copy_Button.click();
		test_steps.add("Click Copy Reservation");
		resCopyAssignedLogger.info("Click Copy");
		Wait.WaitForElement(driver, NewReservation.OverbookingPopup);
		Wait.explicit_wait_visibilityof_webelement(res_copy.OverbookingPopup, driver);
		Assert.assertTrue(res_copy.OverbookingPopup.isDisplayed(),"Failed: Overbooking popup not appeared");
		resCopyAssignedLogger.info("Overbooking Popup Appears");
		test_steps.add("Overbooking Popup Appears");
		Utility.ScrollToElement(res_copy.OverbookingPopup_Continue, driver);
		res_copy.OverbookingPopup_Continue.click();
		test_steps.add("Click Continue Button of OverBooking Popup");
		resCopyAssignedLogger.info("Click Continue");
		//Thread.sleep(5000);
		resCopyAssignedLogger.info("Reservation Copied ");
		//Thread.sleep(8000);

		Wait.explicit_wait_visibilityof_webelement(res_copy.get_Copied_ReservationTab, driver);
		test_steps.add("Copied Reservation Successfully");
		String copiedGuestName = res_copy.get_Copied_ReservationTab.getText();
		resCopyAssignedLogger.info(" Name of the Copied Guest Name is " + copiedGuestName);
		test_steps.add(" Name of the Copied Guest Name is " + copiedGuestName);
		//Thread.sleep(5000);
		return test_steps;

	}
	
	public ArrayList<String> CopyReservation_OverbookingNotAppeared(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Reservation_CopyPage res_copy = new Elements_Reservation_CopyPage(driver);

		Wait.explicit_wait_visibilityof_webelement(res_copy.click_Copy_Button, driver);
		//Thread.sleep(5000);
		Utility.ScrollToElement(res_copy.click_Copy_Button, driver);
		res_copy.click_Copy_Button.click();
		test_steps.add("Click Copy Reservation");
		resCopyAssignedLogger.info("Click Copy");
		try{
			Wait.explicit_wait_visibilityof_webelement(res_copy.OverbookingPopup, driver);
		}catch(Exception e) {
			test_steps.add("Overbooking popup not appeared");
		}
		Wait.explicit_wait_visibilityof_webelement(res_copy.get_Copied_ReservationTab, driver);
		test_steps.add("Copied Reservation Successfully");
		String copiedGuestName = res_copy.get_Copied_ReservationTab.getText();
		resCopyAssignedLogger.info(" Name of the Copied Guest Name is " + copiedGuestName);
		test_steps.add(" Name of the Copied Guest Name is " + copiedGuestName);
		//Thread.sleep(5000);
		return test_steps;

	}

}
