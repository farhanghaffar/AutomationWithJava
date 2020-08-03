package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

public interface IRateQuote {
	
	void searchDetails(WebDriver driver, String RateQuoteNights, String RateQuoteAdults, String RateQuoteChildren, String RateQuoteRatePlanList, String RateQuotePromoCode) throws InterruptedException;
	void clearRateQuoteDetails(WebDriver driver);
	void clickBookicon(WebDriver driver, String RoomClassName)throws InterruptedException;

}
