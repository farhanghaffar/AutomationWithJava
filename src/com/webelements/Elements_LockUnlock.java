package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;


public class Elements_LockUnlock {
	

	WebDriver driver ;


	public static Logger app_logs = Logger.getLogger("devpinoyLogger");
		
		public Elements_LockUnlock(WebDriver driver2)
		{
			this.driver=driver2;
			PageFactory.initElements(this.driver, this);
			
			 
		}

		@FindBy(xpath=OR.Click_BasicSearch)
		public WebElement Click_BasicSearch;

		@FindBy(xpath=OR.advancedSearch)
		public WebElement advancedSearch;
		
		@FindBy(xpath=OR.searchButton)
		public WebElement searchButton;

		@FindBy(xpath=OR.openReservation)
		public WebElement openReservation;
		
		@FindBy(xpath=OR.getInHouseReservations)
		public WebElement getInHouseReservations;
		
		@FindBy(xpath=OR.inHouseReservation)
		public WebElement inHouseReservation;

		@FindBy(xpath=OR.getAllArrivalsReservations)
		public WebElement getAllArrivalsReservations;
		
		@FindBy(xpath=OR.allArrivalsReservation)
		public WebElement allArrivalsReservation;
		
		@FindBy(xpath=OR.inHouseReservations)
		public WebElement inHouseReservations;
		
		@FindBy(xpath=OR.Click_RoomPicker)
		public WebElement Click_RoomPicker;

		@FindBy(xpath=OR.unlock)
		public WebElement unlock;
		
		@FindBy(xpath=OR.lock)
		public WebElement lock;
		
		@FindBy(xpath=OR.getAlertMessage)
		public WebElement getAlertMessage;

		@FindBy(xpath=OR.clickCancel)
		public WebElement clickCancel;
		
		
}
