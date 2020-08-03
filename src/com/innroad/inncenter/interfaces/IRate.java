package com.innroad.inncenter.interfaces;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public interface IRate {
	
	void inventory_Rate(WebDriver driver) throws InterruptedException;
	
	ArrayList<String> new_Rate(WebDriver driver, String ratename,String maxAdults, String maxPersons,
			String baseAmount, String additionalAdult, String additionalChild, String rateDisplayName,
			String ratePolicy, String rateDescription,String RoomClass) throws InterruptedException;
	void delete_rate(WebDriver driver) throws InterruptedException;
	
}
