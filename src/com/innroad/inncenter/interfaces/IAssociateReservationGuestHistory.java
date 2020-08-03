package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

public interface IAssociateReservationGuestHistory {
	
	void guestHistory(WebDriver driver) throws InterruptedException ;
	
	void guestHistory_NewAccount(WebDriver driver)throws InterruptedException; 
	
	void guestHistory_AccountDetails(WebDriver driver, String salutation, String FirstName );
	
	void accountAttributes(WebDriver driver, String MarketSegment, String Referral);
	
	void Mailinginfo(WebDriver driver, String AccountFirstName,
			String AccountLastName, String Phonenumber,
			String alternativeNumber, String Address1, String Address2,
			String Address3, String Email, String city, String State,
			String Postalcode);
	
	void Billinginfo(WebDriver driver) throws InterruptedException ;
	
	void Save(WebDriver driver) throws InterruptedException ;
	
	void closeAccount(WebDriver driver) throws InterruptedException;
	
	void associateGuestToReservation(WebDriver driver, String GuestProfile) throws InterruptedException;	

	
}
