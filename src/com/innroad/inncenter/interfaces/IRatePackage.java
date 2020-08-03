package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

public interface IRatePackage {
	
	void inventory_Rate(WebDriver driver) throws InterruptedException;
	
	void package_details(WebDriver driver, String packageName) throws InterruptedException;
	
	void package_components(WebDriver driver, String PackageCompDescription, String PackageAmount) throws InterruptedException;
	
	void package_descriptiveInformation(WebDriver driver, String rateDisplayName, String ratePolicy, String rateDescription) throws InterruptedException;
	
	void associateRate(WebDriver driver) throws InterruptedException;
	
	void delete_rate(WebDriver driver) throws InterruptedException;
	
}
