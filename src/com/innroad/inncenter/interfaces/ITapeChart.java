package com.innroad.inncenter.interfaces;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public interface ITapeChart {
	
	void tapeChartSearch(WebDriver driver,String TapeChartAdults, String TapeChartChildrens, String PromoCode)throws InterruptedException;
	void clickAvailableRoomClass(WebDriver driver)throws InterruptedException;
	void verifyOutOfOrder(WebDriver driver, ArrayList<String> test_steps, String roomClassName, String title)
			throws InterruptedException;

}
