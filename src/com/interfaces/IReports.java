package com.innroad.inncenter.interfaces;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;


public interface IReports {
	
	void VerifyCurrentReservationReport(WebDriver driver, String LedgerType) throws InterruptedException;
	void VerifyPastReservationReport(WebDriver driver, String LedgerType) throws InterruptedException;
	void VerifyFutureReservationReport(WebDriver driver, String LedgerType) throws InterruptedException;
	ArrayList<String> BalanceLedgerTab(WebDriver driver, ArrayList<String> test_steps);
	
	void MarchantTrans(WebDriver driver)throws InterruptedException ;
	void MarchantReports_Auth(WebDriver driver)throws InterruptedException ;
	void MarchantReports_Capture(WebDriver driver)throws InterruptedException ;
	void MarchantReports_Credit(WebDriver driver) throws InterruptedException;
	void MarchantReports_Cancel(WebDriver driver) throws InterruptedException;
	void DailyFlash(WebDriver driver) throws InterruptedException;
	void SelectDailyFlashDate(WebDriver driver) throws InterruptedException;
	void NetSales_GroupBy(WebDriver driver, String GroupBy) throws InterruptedException;
	  void NetSales_GoButton(WebDriver driver) throws InterruptedException;
	void RoomForecast(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException;
	

}
