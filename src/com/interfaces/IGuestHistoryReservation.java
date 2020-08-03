package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

public interface IGuestHistoryReservation {
	
	void guestHistory(WebDriver driver) throws InterruptedException;
	void guestHistory_NewAccount(WebDriver driver)throws InterruptedException;
	void guestHistory_AccountDetails(WebDriver driver, String salutation, String FirstName );
	void accountAttributes(WebDriver driver, String MarketSegment, String Referral);
	void Mailinginfo(WebDriver driver, String AccountFirstName,
			String AccountLastName, String Phonenumber,
			String alternativeNumber, String Address1, String Address2,
			String Address3, String Email, String city, String State,
			String Postalcode);
	void Billinginfo(WebDriver driver) throws InterruptedException;
	void Save(WebDriver driver) throws InterruptedException;
	void newReservation(WebDriver driver) throws InterruptedException;
	void roomAssignment(WebDriver driver,String PropertyName2, String Nights, String Adults,
			String Children, String RatepromoCode,String CheckorUncheckAssign, String RoomClassName,
			String RoomNumber) throws InterruptedException;
	void saveReservation(WebDriver driver) throws InterruptedException;
	void closeGuestHistoryReservation(WebDriver driver) throws InterruptedException;
	
}
