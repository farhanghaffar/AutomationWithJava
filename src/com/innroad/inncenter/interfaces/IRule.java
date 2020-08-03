package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

public interface IRule {
	
	//void click_Inventory(WebDriver driver) throws InterruptedException;
	//void click_Rules(WebDriver driver)  throws InterruptedException;
	void create_Rule(WebDriver driver, String RuleName, String RuleType, String RuleDescription) throws InterruptedException;
	
}
