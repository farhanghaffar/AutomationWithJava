package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

public interface IHousekepping_Status {
	
	int Select_Room_Condition_In_Header(WebDriver driver,String roomcondition) throws InterruptedException;
	void Save(WebDriver driver) throws InterruptedException;
	void Select_Items_Per_Page(WebDriver driver,String itemsperpage) throws InterruptedException;

}
