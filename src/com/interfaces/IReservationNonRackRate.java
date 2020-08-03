package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

public interface IReservationNonRackRate {
	
	
	
	void roomAssignment(WebDriver driver,String PropertyName1, String Nights, String Adults,
			String Children, String RatepromoCode,String CheckorUncheckAssign, String RoomClassName,
			String RoomNumber, String RatePlan) throws InterruptedException;

}
