package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

public interface IAccount {
	
	void NewAccountbutton(WebDriver driver ,String Accounttype);

	void AccountDetails(WebDriver driver,String AccountType, String AccountName) throws InterruptedException;
			
	void AccountAttributes(WebDriver driver,String MargetSegment, String Referral); 
		
	void Mailinginfo(WebDriver driver,String AccountFirstName, String AccountLastName, String Phonenumber, String alternativeNumber, String Address1, String Address2, String Address3, String Email, String city, String State, String Postalcode );
	
	void Billinginfo(WebDriver driver)throws InterruptedException;
	
	void Save(WebDriver driver) throws InterruptedException;
	
	void SaveandClose(WebDriver driver) throws InterruptedException;
	
	void addLineitem(WebDriver driver,String propertyName, String SelectCategory, String Amount)throws InterruptedException;
	
	void Commit(WebDriver driver);
	
	void navigateFolio(WebDriver driver) throws InterruptedException;
	
	void Payment(WebDriver driver, String PaymentType,String CardName, String CCNumber,String CCExpiry, String CCVCode, String Authorizationtype, String ChangeAmount,
	String ChangeAmountValue) throws InterruptedException;
}
