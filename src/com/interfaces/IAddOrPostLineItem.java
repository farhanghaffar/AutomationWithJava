package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

public interface IAddOrPostLineItem {
	
	//void guestNameReservation(WebDriver driver) throws InterruptedException;
	void clickFoliotab(WebDriver driver) throws InterruptedException;
	//void addLineItem(WebDriver driver, String Category, String Amount) throws InterruptedException;
	void adjustLineItem(WebDriver driver,String folioitemDescription, String folioLineAmount, String folioNotes) throws InterruptedException;
}
