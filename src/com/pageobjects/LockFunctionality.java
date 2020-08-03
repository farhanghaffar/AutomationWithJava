package com.innroad.inncenter.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.innroad.inncenter.interfaces.ILockFunctionality;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_LockUnlock;



public class LockFunctionality implements ILockFunctionality{
	
	public static Logger lockUnlockLogger = Logger.getLogger("LockFunctionality");
	public static String getAllArrivalsCount;
	public static String getinHouseReservationCount;
	public void search(WebDriver driver) throws InterruptedException {
		Elements_LockUnlock lock_unlock_rooms= new Elements_LockUnlock(driver);
		WebDriverWait wait=	new WebDriverWait(driver, 10); 
		wait.until(ExpectedConditions.elementToBeClickable(lock_unlock_rooms.inHouseReservation));
		//Wait.wait10Second();
		//lock_unlock_rooms.inHouseReservation.click();
		lock_unlock_rooms.inHouseReservation.click();
		Wait.wait10Second();
		
		getinHouseReservationCount=lock_unlock_rooms.getInHouseReservations.getText();
		
		int inHouseCount=Integer.parseInt(getinHouseReservationCount);
		Wait.wait10Second();
		lockUnlockLogger.info("Count of InHouse Reservations " +inHouseCount);
		Wait.wait10Second();
		
		
		if(inHouseCount==0){
		lock_unlock_rooms.allArrivalsReservation.click();
		Wait.wait10Second();
		}
		
		else if(inHouseCount!=0){
		Wait.wait10Second();	
		}
		Wait.explicit_wait_xpath(OR.openReservation, driver);
	}

	
	
	public void clickReservation(WebDriver driver) throws InterruptedException{
		Elements_LockUnlock lock_unlock_rooms= new Elements_LockUnlock(driver);
		Wait.explicit_wait_xpath(OR.openReservation, driver);
		lock_unlock_rooms.openReservation.click();
		Wait.wait10Second();
		lockUnlockLogger.info( " System successfully Clicked on Reservation " );
		Wait.wait10Second();
	}
	
	public void verifyLockUnlock(WebDriver driver) throws InterruptedException{
		Elements_LockUnlock lock_unlock_rooms= new Elements_LockUnlock(driver);
		
		lock_unlock_rooms.Click_RoomPicker.click();
		
		Wait.wait10Second();
	
		String roomPickerAlertMessage=lock_unlock_rooms.getAlertMessage.getText();
			
		if(roomPickerAlertMessage.equalsIgnoreCase("Please note that this guest has requested not to have their room changed."))
		{
			
			lockUnlockLogger.info( " Room Picker is Disabled " );
			lock_unlock_rooms.unlock.click();
			Wait.wait5Second();
	
			lockUnlockLogger.info( " Reserved room is unlocked " );
		}
		
		else
		{
			Wait.explicit_wait_xpath(OR.clickCancel, driver);
			//Thread.sleep(5000);
			lockUnlockLogger.info(" Click on Cancel button in pop up ");
	
			lock_unlock_rooms.clickCancel.click();
			Wait.wait10Second();
	
			lockUnlockLogger.info(" Room picker has been Enabled ");
	
			lock_unlock_rooms.lock.click();
			lockUnlockLogger.info(" Room Picker has been Disabled ");
	
			Wait.wait10Second();
			
			lock_unlock_rooms.Click_RoomPicker.click();
			
			Wait.wait3Second();
			
			String roomPickerAlertMessage2=lock_unlock_rooms.getAlertMessage.getText();
			
			lockUnlockLogger.info(" Room Picker alert message : " +roomPickerAlertMessage2);
			
			Wait.wait3Second();
			
		}
		
	}
}
