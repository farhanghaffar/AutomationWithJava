package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class WebElements_Create_Reservation {
	
	
	WebDriver driver ;


	public static Logger app_logs = Logger.getLogger("devpinoyLogger");
		
		public WebElements_Create_Reservation(WebDriver driver2)
		{
			this.driver=driver2;
			PageFactory.initElements(this.driver, this);
			
			 
		}
		
		
		@FindBy(xpath=OR.ReservationIcon)
		public WebElement ReservationIcon;
		
		@FindBy(id=OR.Reservationlink)
		public WebElement Reservationlink;
		
		@FindBy(id=OR.NewReservationButton)
		public WebElement NewReservationButton;
		
		@FindBy(id=OR.ClickonAssignRooms)
		public WebElement ClickonAssignRooms;
		
		
		@FindBy(id=OR.UnCheckGuestProfile)
		public WebElement UnCheckGuestProfile;
		
		@FindBy(id=OR.GuestFirstName)
		public WebElement GuestFirstName;
		
		@FindBy(id=OR.GuestLastName)
		public WebElement GuestLastName;
		
		
		@FindBy(id=OR.Switchtoframe)
		public WebElement Switchtoframe;
		
		@FindBy(xpath=OR.StartDate)
		public WebElement StartDate;
		
		@FindBy(xpath=OR.ClickonToday)
		public WebElement ClickonToday;
		
		
		@FindBy(id=OR.NumberofAdults)
		public WebElement NumberofAdults;
		
		
		@FindBy(id=OR.SearchButton)
		public WebElement SearchButton;
		
		
		@FindBy(id=OR.SelectRoomClass)
		public WebElement SelectRoomClass;
		
		
		@FindBy(id=OR.Select)
		public WebElement Select;
		
		@FindBy(id=OR.Save)
		public WebElement Save;
}
