package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

public interface IDistribution {
	
	void ClickDistributionLink(WebDriver driver,ExtentTest test);
	
	void ClickBlackoutsLink(WebDriver driver);
	
	void CheckOutRoom(WebDriver driver, int index);
	
	void CheckedRoom(WebDriver driver, int index,boolean condition);

}
