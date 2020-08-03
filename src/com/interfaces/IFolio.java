package com.innroad.inncenter.interfaces;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

public interface IFolio {

	public ArrayList<String> MoveFolioInsideReservation(WebDriver driver, ExtentTest test, String resNumber1,
			String newFolioName, String newFolioDescriptio, ArrayList<String> test_steps) throws InterruptedException;

	public ArrayList<String> MoveFolioInsideReservation(WebDriver driver, ExtentTest test, String resNumber1,
			String resNumber2, Double d, Double d2, ArrayList<String> test_steps) throws Exception;

}
