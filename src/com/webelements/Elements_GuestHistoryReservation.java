package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;



public class Elements_GuestHistoryReservation {
	
	


	WebDriver driver ;


	public static Logger app_logs = Logger.getLogger("devpinoyLogger");
		
		public Elements_GuestHistoryReservation(WebDriver driver2)
		{
			this.driver=driver2;
			PageFactory.initElements(this.driver, this);
			
			 
		}

		@FindBy(xpath=OR.newReservationButton)
		public WebElement newReservationButton;

		@FindBy(xpath=OR.closeGuestHistoryRes)
		public WebElement closeGuestHistoryRes;


	
	
}
