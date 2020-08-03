package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

public interface IFolioLineItemsVoid {
	
	void marketingInfo(WebDriver driver,String MarketSegment,String Referral, String Travel_Agent,String ExtReservation)throws InterruptedException;
	   
	void clickNewReservationButton(WebDriver driver )throws InterruptedException;
	
	void contactInformation(WebDriver driver,String saluation, String FirstName, String LastName,String Address,String Line1, String Line2, String Line3, String City, String Country, String State, String Postalcode,String Phonenumber, String alternativenumber, String Email, String Account,String IsTaxExempt, String TaxEmptext);
	
	void billingInformation(WebDriver driver,String PaymentMethod, String AccountNumber, String ExpiryDate, String BillingNotes);
	
	void roomAssignment(WebDriver driver,String PropertyName,String Nights, String Adults, String Children, String RatepromoCode,String CheckorUncheckAssign, String RoomClassName, String RoomNumber)throws InterruptedException;
	
	void IPropertySelector(WebDriver driver,String PropertyName)throws InterruptedException;
	
	void saveReservation(WebDriver driver)throws InterruptedException;
	
	void adjustLineItem(WebDriver driver,String folioitemDescription, String folioLineAmount, String folioNotes) throws InterruptedException;

	void folioLineItemsVoid(WebDriver driver, String Category, String Amount, String Notes)throws InterruptedException; 
}
