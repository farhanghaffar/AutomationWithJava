package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

public interface ILockFunctionality {
	void search(WebDriver driver) throws InterruptedException;
	void clickReservation(WebDriver driver) throws InterruptedException;
	void verifyLockUnlock(WebDriver driver) throws InterruptedException;
	
}
