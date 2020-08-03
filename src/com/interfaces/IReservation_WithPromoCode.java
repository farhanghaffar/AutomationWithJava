package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

public interface IReservation_WithPromoCode {

	void clickTapechart(WebDriver driver)throws InterruptedException;
	void checkAvailabilityWithPromoCode(WebDriver driver, String TapeChartAdults, String TapeChartChildrens, String PromoCode) throws InterruptedException;
	void click_availableRoomClass(WebDriver driver)  throws InterruptedException;
	void marketingInfo(WebDriver driver, String MarketSegment, String Referral, String Travel_Agent, String ExtReservation) throws InterruptedException;
	void contactInformation(WebDriver driver, String saluation, String FirstName, String LastName, String Address, String Line1, String Line2, String Line3, String City, String Country, String State, String Postalcode,String Phonenumber, String alternativenumber, String Email, String Account,String IsTaxExempt, String TaxEmptext) throws InterruptedException;
	void billingInformation(WebDriver driver, String PaymentMethod, String AccountNumber, String ExpiryDate, String BillingNotes) throws InterruptedException;
	void saveReservation(WebDriver driver) throws InterruptedException;
	void cancelReservation(WebDriver driver, String Reason) throws InterruptedException;
	
}

