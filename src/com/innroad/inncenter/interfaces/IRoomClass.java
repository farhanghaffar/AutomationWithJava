package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

public interface IRoomClass {

	boolean roomClassInfo(WebDriver driver,String roomClassName,String roomClassAbbrivation,String bedsCount,String maxAdults,String maxPersopns,String roomQuantity,ExtentTest test)throws InterruptedException, Exception;;
}
