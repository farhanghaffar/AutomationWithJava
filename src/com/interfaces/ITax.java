package com.innroad.inncenter.interfaces;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

public interface ITax {
	
	String createTax(WebDriver driver, ExtentTest test, String taxName, String displayName, String description, String value, String category,String taxLedgerAccount,ArrayList<String> test_steps) throws InterruptedException;

}
