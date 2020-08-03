package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Inventory;
import com.innroad.inncenter.webelements.Elements_RoomMaintenance;

public class Users {

	public static Logger users = Logger.getLogger("RoomMaintenance");

	
	public void searchUser(WebDriver driver,String user){
		String name="//input[@placeholder='Login ID']";
		Wait.WaitForElement(driver, name);
		driver.findElement(By.xpath(name)).sendKeys(user);
	}
	
	public void search(WebDriver driver){
		String search="//button[contains(text(),'Search')]";
		Wait.WaitForElement(driver, search);
		driver.findElement(By.xpath(search)).click();
	}

	public String get_UserFirstName(WebDriver driver,String user){
		String Lname="//a[contains(text(),'"+user.trim()+"')]/../following-sibling::td[2]/span";
		Wait.WaitForElement(driver, Lname);
		return driver.findElement(By.xpath(Lname)).getText();
	}
	
	public String get_UserLastName(WebDriver driver,String user){
		String Fname="//a[contains(text(),'"+user.trim()+"')]/../following-sibling::td[1]/span";
		Wait.WaitForElement(driver, Fname);
		return driver.findElement(By.xpath(Fname)).getText();
	}
	
}