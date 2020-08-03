package com.innroad.inncenter.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.innroad.inncenter.interfaces.ICreateReservation;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.webelements.WebElements_Create_Reservation;

public class Create_Reservation implements ICreateReservation {

	@Override
	public void createReservation(WebDriver driver,String MarketSegment,
			String ReferralDropDown, String FirstName, String LastName,
			String Adults, String SelectRoomClassRoomAssign,
			String SelectRoomNumber) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		WebElements_Create_Reservation CREATE_RESERVATION = new WebElements_Create_Reservation(driver);
		
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		driver.switchTo().frame(0);
		Thread.sleep(5000);
		CREATE_RESERVATION.ReservationIcon.click();
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		driver.switchTo().frame("main");
		Thread.sleep(5000);
		CREATE_RESERVATION.Reservationlink.click();
		Thread.sleep(6000);
		CREATE_RESERVATION.NewReservationButton.click();
		Thread.sleep(5000);
		new Select (driver.findElement(By.id(OR.MarketSegment))).selectByVisibleText(MarketSegment);
		Thread.sleep(3000);
		new Select (driver.findElement(By.id(OR.ReferralDropDown))).selectByVisibleText(ReferralDropDown);
		Thread.sleep(3000);
		CREATE_RESERVATION.UnCheckGuestProfile.click();
		CREATE_RESERVATION.GuestFirstName.sendKeys(FirstName);
		CREATE_RESERVATION.GuestLastName.sendKeys(LastName);
		CREATE_RESERVATION.ClickonAssignRooms.click();
		Thread.sleep(5000);
		//d.switchTo().defaultContent();
		Thread.sleep(5000);
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		Thread.sleep(3000);
		CREATE_RESERVATION.StartDate.click();
		Thread.sleep(3000);
		CREATE_RESERVATION.ClickonToday.click();
		Thread.sleep(3000);
		CREATE_RESERVATION.NumberofAdults.clear();
		CREATE_RESERVATION.NumberofAdults.sendKeys(Adults);
		CREATE_RESERVATION.SearchButton.click();
		Thread.sleep(5000);
	    new Select (driver.findElement(By.id(OR.SelectRoomClassRoomAssign))).selectByVisibleText(SelectRoomClassRoomAssign);
	    Thread.sleep(3000);
	    new Select (driver.findElement(By.id(OR.SelectRoomNumber))).selectByVisibleText(SelectRoomNumber);
	    Thread.sleep(3000);
	    CREATE_RESERVATION.Select.click();
	    Thread.sleep(3000);
	    driver.switchTo().defaultContent();
		Thread.sleep(3000);
		driver.switchTo().frame("main");
		Thread.sleep(3000);
		CREATE_RESERVATION.Save.click();
		Thread.sleep(5000);
		
	}
	
	
	

}
