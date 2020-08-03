package com.innroad.inncenter.interfaces;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public interface IPolicies {

	ArrayList<String> NewPolicybutton(WebDriver driver, String Accounttype, ArrayList<String> test_step) throws InterruptedException;

	//void Enter_Policy_Name(WebDriver driver, String PolicyName) throws InterruptedException;

	void verify_Policy_Type(WebDriver driver, String PolicyType);

	void Enter_Policy_Desc(WebDriver driver, String PolicyText, String PolicyDesc);

	void Associate_Sources(WebDriver driver) throws InterruptedException;

	void Associate_Seasons(WebDriver driver) throws InterruptedException;

	void Associate_RoomClasses(WebDriver driver) throws InterruptedException;

	void Associate_RatePlans(WebDriver driver) throws InterruptedException;

}
