package com.innroad.inncenter.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Reservation;

public class PerformanceMethods {
	
	
	
	public void RoomAssignmentSearch(WebDriver driver, String PropertyName,String Nights, String Adults, String Children, String RatepromoCode, String CheckorUncheckAssign, String RoomClassName, String RoomNumber ) throws InterruptedException
	{
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.Click_RoomPicker.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait3Second();
		try
		{
		new Select(ReservationPage.Select_property_RoomAssign).selectByVisibleText(PropertyName);
		}
		catch(Exception e)
		{
			new Select(ReservationPage.Select_property_RoomAssign2).selectByVisibleText(PropertyName);
		}
		Wait.wait5Second();
		ReservationPage.Click_Arrive_Datepicker.click();
		ReservationPage.Click_Today.click();
		ReservationPage.Enter_Nigts.sendKeys(Nights);
		ReservationPage.Enter_Adults.sendKeys(Adults);
		ReservationPage.Enter_Children.sendKeys(Children);
		ReservationPage.Enter_Rate_Promocode.sendKeys();
		
		if(ReservationPage.Check_Assign_Room.isSelected())
		{
			ReservationPage.Check_Assign_Room.click();
			 long startTime = System.currentTimeMillis();
			ReservationPage.Click_Search.click();
			Wait.explicit_wait_xpath("//tbody[@data-bind='foreach: RoomListWithRules']//tr[5]", driver);
			 long endTime   = System.currentTimeMillis();
	     	 long totalTime = endTime - startTime;
	      	 System.out.println("Room Search :" + totalTime);
		}
		else
		{
			if(CheckorUncheckAssign.equals("Yes"))
			{
				ReservationPage.Check_Assign_Room.click();
				 long startTime = System.currentTimeMillis();
				ReservationPage.Click_Search.click();
				Wait.explicit_wait_xpath("//tbody[@data-bind='foreach: RoomListWithRules']//tr[5]", driver);
				long endTime   = System.currentTimeMillis();
		     	 long totalTime = endTime - startTime;
		      	 System.out.println("Room Search :" + totalTime);
			}
			else
			{
				 long startTime = System.currentTimeMillis();
				ReservationPage.Click_Search.click();
				Wait.explicit_wait_xpath("//tbody[@data-bind='foreach: RoomListWithRules']//tr[5]", driver);
				long endTime   = System.currentTimeMillis();
		     	 long totalTime = endTime - startTime;
		      	 System.out.println("Room Search :" + totalTime);
			}
		}
		long startTime = System.currentTimeMillis();
		try
		{
	
		new Select(ReservationPage.Select_Room_Class).selectByVisibleText(RoomClassName);
		String selectedOption = new Select(ReservationPage.Validating_UnAssgined_DDL).getFirstSelectedOption().getText();
        System.out.println("selectedOption  " +selectedOption);
		if(selectedOption.equals("--Select--"))
		{
		new Select(ReservationPage.Select_Room_Number).selectByVisibleText(RoomNumber);
		}
		else
		{
			  System.out.println("Reservation is unassigned");
		}
		}
		catch(Exception e)
		{
			Wait.explicit_wait_xpath(OR.Validation_Text_NoRooms, driver);
			System.out.println("Room Class are not Available for these dates");
			
		}
		ReservationPage.Click_Select.click();
		try
		{
			Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
		}
		catch(Exception e)
		{
			System.out.println("Verification failed");
		}
		Wait.wait5Second();
		if(ReservationPage.Verify_RulesBroken_Popup.isDisplayed())
		{
			ReservationPage.Click_Continue_RuleBroken_Popup.click();
		}
		else
		{
			System.out.println("Satisfied Rules");
		}
		
		if(ReservationPage.Verify_Toaster_Container.isDisplayed())
		{
		String getTotasterTitle=ReservationPage.Toaster_Title.getText();
		String getToastermessage=ReservationPage.Toaster_Message.getText();
		System.out.println(getTotasterTitle + " " +getToastermessage);
		Assert.assertEquals(getTotasterTitle, "Room assignment has changed.");
		Assert.assertEquals(getToastermessage, "Room assignment has changed.");
		}
		long endTime   = System.currentTimeMillis();
    	long totalTime = endTime - startTime;
     	System.out.println("Room SElection Time :" + totalTime);
		
	}
	
	public void marketingInfo(WebDriver driver, String MarketSegment,
			String Referral, String Travel_Agent, String ExtReservation) throws InterruptedException {
		
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		new Select(ReservationPage.Reservation_market_Segment).selectByVisibleText(MarketSegment);
		new Select(ReservationPage.Reservation_Referral).selectByVisibleText(Referral);
		try
		{
		ReservationPage.Enter_Travel_Agent.sendKeys(Travel_Agent);
		}
		catch(Exception e)
		{
			
		}
		ReservationPage.Enter_Ext_Reservation.sendKeys(ExtReservation);
	}
	
	public void contactInformation(WebDriver driver, String saluation,
			String FirstName, String LastName, String Address, String Line1,
			String Line2, String Line3, String City, String Country,
			String State, String Postalcode, String Phonenumber,
			String alternativenumber, String Email, String Account,String IsTaxExempt,
			String TaxEmptext) {
		
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		
		new Select(ReservationPage.Select_Saluation).selectByVisibleText(saluation);
		ReservationPage.Enter_First_Name.clear();
		ReservationPage.Enter_Last_Name.clear();
		ReservationPage.Enter_Address_Search.clear();
		ReservationPage.Enter_Line1.clear();
		ReservationPage.Enter_Line2.clear();
		ReservationPage.Enter_Line3.clear();
		ReservationPage.Enter_City.clear();
		ReservationPage.Enter_Postal_Code.clear();;
		ReservationPage.Enter_Phone_Number.clear();;
		ReservationPage.Enter_Alt_Phn_number.clear();;
		ReservationPage.Enter_email.clear();;
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
		try
		{
		ReservationPage.Enter_Account.sendKeys(Account);
		}
		catch(Exception e)
		{
			
		}
		if(IsTaxExempt.equals("Yes"))
		{
		if(ReservationPage.Check_IsTaxExempt.isSelected())
		{
		ReservationPage.Enter_TaxExemptId.sendKeys(TaxEmptext);	
		}
		else
		{
			ReservationPage.Check_IsTaxExempt.click();
			ReservationPage.Enter_TaxExemptId.sendKeys(TaxEmptext);
		}
		}
	}
	
	public void billingInformation(WebDriver driver, String PaymentMethod,
			String AccountNumber, String ExpiryDate, String BillingNotes) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		new Select(ReservationPage.Select_Payment_Method).selectByVisibleText(PaymentMethod);
		if(PaymentMethod.equalsIgnoreCase("MC")||PaymentMethod.equalsIgnoreCase("Amex")||PaymentMethod.equalsIgnoreCase("Discover")||PaymentMethod.equalsIgnoreCase("Visa"))
		{
			ReservationPage.Enter_Account_Number.sendKeys(AccountNumber);
			ReservationPage.Enter_Exp_Card.sendKeys(ExpiryDate);
			ReservationPage.Enter_Billing_Note.sendKeys(BillingNotes);
			
		}
	}
	
public void saveReservation(WebDriver driver) throws InterruptedException {
		

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		
		ReservationPage.Click_Save_ReservationDetails.click();
		
		if(ReservationPage.Verify_Toaster_Container.isDisplayed())
		{
		String getTotasterTitle_ReservationSucess=ReservationPage.Toaster_Title.getText();
		String getToastermessage_ReservationSucess=ReservationPage.Toaster_Message.getText();
		Assert.assertEquals(getTotasterTitle_ReservationSucess, "Reservation Saved");
		if(getToastermessage_ReservationSucess.endsWith("has been saved successfully"));
		}
	}

}
