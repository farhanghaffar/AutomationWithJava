package com.innroad.inncenter.interfaces;


import java.io.IOException;

import org.openqa.selenium.WebDriver;

public interface ISourceStatus {
	
	void selectProperty(WebDriver driver, String propertyName1) throws InterruptedException ;
	
	void inventory_Distribution(WebDriver driver) throws InterruptedException;
	
	void channels(WebDriver driver) throws InterruptedException, IOException;
	
	void navigateReservation(WebDriver driver) throws InterruptedException;
	
	void marketingInfo(WebDriver driver, String MarketSegment,
			String Referral, String Travel_Agent, String ExtReservation) throws InterruptedException;
	
	void contactInformation(WebDriver driver, String saluation,
			String FirstName, String LastName, String Address, String Line1,
			String Line2, String Line3, String City, String Country,
			String State, String Postalcode, String Phonenumber,
			String alternativenumber, String Email, String Account,String IsTaxExempt,
			String TaxEmptext) ;
	
	void billingInformation(WebDriver driver, String PaymentMethod,
			String AccountNumber, String ExpiryDate, String BillingNotes);
			
	
	
	void roomAssignment(WebDriver driver,String PropertyName, String Nights, String Adults,
			String Children, String RatepromoCode,String CheckorUncheckAssign, String RoomClassName,
			String RoomNumber) throws InterruptedException;
	
	void verify_reservaionStatus(WebDriver driver) throws InterruptedException;

	}
