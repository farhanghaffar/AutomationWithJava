package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

public interface IListManagement {

	void NewItemCreation(WebDriver driver, String Name, String Description) throws InterruptedException;

	void SelectFilter(WebDriver driver, String FilterName) throws InterruptedException;

}
