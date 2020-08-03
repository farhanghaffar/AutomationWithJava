package com.innroad.inncenter.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public interface IGuestHistory {

	void guestHistory(WebDriver driver) throws InterruptedException;

	void guestHistory_NewAccount(WebDriver driver) throws InterruptedException;

	void guestHistory_AccountDetails(WebDriver driver, String salutation, String FirstName);

	void accountAttributes(WebDriver driver, String MarketSegment, String Referral, ArrayList<String> test_steps);

	ArrayList<String> Mailinginfo(WebDriver driver, String AccountFirstName, String AccountLastName, String Phonenumber,
			String alternativeNumber, String Address1, String Address2, String Address3, String Email, String city,
			String State, String Postalcode) throws InterruptedException;

	void Billinginfo(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException;

	// void newReservation(WebDriver driver);
	// void roomAssignment(WebDriver driver,String PropertyName, String Nights,
	// String Adults,String Children, String RatepromoCode,String
	// CheckorUncheckAssign, String RoomClassName,String RoomNumber) throws
	// InterruptedException;
	ArrayList<String> Save(WebDriver driver) throws InterruptedException, FileNotFoundException, IOException;

	void closeAccount(WebDriver driver) throws InterruptedException;

	ArrayList<String>  searchAccount(WebDriver driver, String AccountFname, ArrayList<String> test_steps) throws InterruptedException;

}
