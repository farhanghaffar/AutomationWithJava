//Interface for Login

package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

public interface INightAudit {	

	
	void EnterAuditDate(WebDriver driver,String Date) throws InterruptedException;
	void GoButtonClick(WebDriver driver) throws InterruptedException;
	void VerifyHouseKeepingSection(WebDriver driver) throws InterruptedException;
	void EnterAuditDate(WebDriver driver) throws InterruptedException;
	void DailyTransactionButtonClick(WebDriver driver) throws InterruptedException;
	void SelectAllItemStatus(WebDriver driver) throws InterruptedException;
	void DailyTransaction_PostButtonClick(WebDriver driver) throws InterruptedException;
	void PeriodIsOpenButtonClick(WebDriver driver) throws InterruptedException;
	String GetHouseKeepingCount(WebDriver driver) throws InterruptedException;



	
}
