package com.innroad.inncenter.pageobjects;

import org.openqa.selenium.WebDriver;

import com.innroad.inncenter.webelements.WebElements_header_Panel_Elements;

public class Header_Panel_Elements implements com.innroad.inncenter.interfaces.IHeader_Panel_Elements {

	public WebElements_header_Panel_Elements si;
	@Override
	public void iSearchIcon(WebDriver driver) throws InterruptedException {
		
	  si= new WebElements_header_Panel_Elements(driver);
		driver.switchTo().frame("header");
		Thread.sleep(5000);
		si.SearchIcon.click();
		Thread.sleep(5000);
		
	}
	
	@Override
	public void iInventoryIcon(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		
		 si= new WebElements_header_Panel_Elements(driver);
		    driver.switchTo().defaultContent();
		    Thread.sleep(3000);
			driver.switchTo().frame(0);
			Thread.sleep(5000);
			si.Inventory_Icon.click();
			Thread.sleep(5000);
		
	}
	
	
	
	

}
