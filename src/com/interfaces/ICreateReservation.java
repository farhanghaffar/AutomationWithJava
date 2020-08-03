package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

public interface ICreateReservation {
	
	void createReservation(WebDriver driver,String MarketSegment, String ReferralDropDown, String FirstName, String LastName, String Adults, String SelectRoomClassRoomAssign, String SelectRoomNumber ) throws InterruptedException;

}
