package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

public interface IRoomStatus {
	
	void SearchRoomZoonOccupancy(WebDriver driver,String Value) throws InterruptedException;

	void VerifyQuickStats(WebDriver driver) throws InterruptedException;
	
	void VerifySortFuntionality(WebDriver driver) throws InterruptedException;
	void SortByZone(WebDriver driver) throws InterruptedException;

	void SortByArrivalDue(WebDriver driver) throws InterruptedException;

	void SortByRoomNum(WebDriver driver) throws InterruptedException;
	
	void MouserHoverOverElemet(WebDriver driver)throws InterruptedException;
	
	 void ClickFirstElementRadioButton(WebDriver driver) throws InterruptedException;
	 
	 void SelectOneRoomStatusCategory(WebDriver driver, String ColorName) throws InterruptedException;
	 
	 void ClickCleanDropDownButton(WebDriver driver) throws InterruptedException ;
	 
	 void ClickUpdateStatusButton(WebDriver driver) throws InterruptedException;
	 
	 void ClickUpdateStatus_CleanButton(WebDriver driver) throws InterruptedException;


}
