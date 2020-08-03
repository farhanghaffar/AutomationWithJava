package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

public interface IReservationCopy_Assigned {

	void roomAssignment(WebDriver driver,String PropertyName, String Nights, String Adults,
			String Children, String RatepromoCode,String CheckorUncheckAssign, String RoomClassName, String RoomNumber) throws InterruptedException;
	void saveReservation(WebDriver driver) throws InterruptedException;

}
