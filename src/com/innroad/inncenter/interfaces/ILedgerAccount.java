package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

public interface ILedgerAccount {
	
	void NewAccountbutton(WebDriver driver)throws InterruptedException;
	void LedgerAccountDetails(WebDriver driver,String Name,String Description,String DefaultAmount)throws InterruptedException;
	void SaveLedgerAccount(WebDriver driver)throws InterruptedException;
	void DeleteLedgerAccount(WebDriver driver) throws InterruptedException;
}
