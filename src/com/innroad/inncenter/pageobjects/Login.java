package com.innroad.inncenter.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.innroad.inncenter.interfaces.ILogin;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.*;

public class Login implements ILogin {

	private static Logger loginLogger = Logger.getLogger("Login");

	@Override
	public void login(WebDriver driver, String url, String Clientcode, String UserID, String Password)
			throws InterruptedException {

		WebElementsLogin wl = new WebElementsLogin(driver);
		driver.get(TestCore.envURL); // Overriding test-data url value with jenkins build parameter
		loginLogger.info("Entered application URL");
		Wait.WaitForElement_ID(driver, OR.clientCode);
		wl.clientCode.sendKeys("autocp");
		loginLogger.info("Entered client code");
		wl.userID.sendKeys(UserID);
		Utility.userId=UserID;
		loginLogger.info("Entered used id : "+Utility.userId);
		loginLogger.info("Entered used id");
		wl.password.sendKeys(Password);
		loginLogger.info("Entered password");
		Wait.wait3Second();
		//Assert.assertEquals("x", "y");
		wl.Login.click();
		loginLogger.info("Clicked on login button");
		
		Wait.WaitForElement(driver, OR.LoginModuleLoding);
		//Wait.waitForElementToBeGone(driver, wl.LoginModuleLoding, 300);
		Wait.WaitForElement(driver, OR.New_Reservation_Button);;		
//		Wait.waitUntilPresenceOfElementLocated("//small[.='Items Per Page']");
		Wait.wait2Second();

		//	if(driver.getCurrentUrl().equ)
		try{
			Wait.explicit_wait_absenceofelement("//div[@class='dvLoading']", driver);
			Wait.WaitForElement(driver, OR.New_Reservation_Button);
			Wait.explicit_wait_visibilityof_webelement_350(driver.findElement(By.xpath(OR.New_Reservation_Button)),driver);
			Wait.waitUntilPresenceOfElementLocated(OR.New_Reservation_Button, driver);		
		}catch(Exception e){
			driver.navigate().to(TestCore.envURL);// Overriding test-data url value with jenkins build parameter
			loginLogger.info("Entered application URL");

			wl.clientCode.sendKeys(Clientcode);
			loginLogger.info("Entered client code");
			Wait.wait1Second();
			wl.userID.sendKeys(UserID);
			Wait.wait1Second();
			loginLogger.info("Entered used id");
			wl.password.sendKeys(Password);
			loginLogger.info("Entered password");
			Wait.wait2Second();
			//				Assert.assertEquals("x", "y");
			wl.Login.click();
			loginLogger.info("Clicked on login button");
/*
			Wait.explicit_wait_absenceofelement("//div[@class='dvLoading']", driver);
			Wait.WaitForElement(driver, OR.New_Reservation_Button);
			Wait.explicit_wait_visibilityof_webelement_350(driver.findElement(By.xpath(OR.New_Reservation_Button)),driver);
			Wait.waitUntilPresenceOfElementLocated(OR.New_Reservation_Button, driver);		
	*/		
			Wait.WaitForElement(driver, OR.LoginModuleLoding);
			Wait.waitForElementToBeGone(driver, wl.LoginModuleLoding, 300);
			Wait.WaitForElement(driver, OR.New_Reservation_Button);;		
//			Wait.waitUntilPresenceOfElementLocated("//small[.='Items Per Page']");
			Wait.wait2Second();
		}
	}
	
	@Override
	public void BE_login(WebDriver driver, String URL) throws InterruptedException {

		WebElementsLogin wl = new WebElementsLogin(driver);
		driver.get(URL);
		Wait.wait5Second();

	}

	public void logout(WebDriver driver) throws InterruptedException {
		String name="//div[@class='user_des']";
		Wait.WaitForElement(driver, name);
		Wait.waitForElementToBeVisibile(By.xpath(name), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(name)), driver);
		driver.findElement(By.xpath(name)).click();
		String logout="//a[@id='logoutlink']/..";
		Wait.waitForElementToBeVisibile(By.xpath(logout), driver);
		Wait.WaitForElement(driver, logout);
		Utility.ScrollToElement(driver.findElement(By.xpath(logout)), driver);
		driver.findElement(By.xpath(logout)).click();
//		
//		JavascriptExecutor jse = (JavascriptExecutor) driver;
//		jse.executeScript("arguments[0].click();", driver.findElement(By.xpath(logout)));
//		driver.findElement(By.xpath(logout)).click();
	}
	
	public void login_URL(WebDriver driver,String Clientcode, String UserID, String Password)
			throws InterruptedException {

		WebElementsLogin wl = new WebElementsLogin(driver);
		//driver.get(url); // Overriding test-data url value with
										// jenkins build parameter
		//loginLogger.info("Entered application URL : " + url);
		Wait.WaitForElement_ID(driver, OR.clientCode);
		wl.clientCode.sendKeys(Clientcode);
		loginLogger.info("Entered client code");
		wl.userID.sendKeys(UserID);
		loginLogger.info("Entered used id");
		wl.password.sendKeys(Password);
		loginLogger.info("Entered password");
		Wait.wait3Second();
		// Assert.assertEquals("x", "y");
		wl.Login.click();
		loginLogger.info("Clicked on login button");

		Wait.WaitForElement(driver, OR.LoginModuleLoding);
		Wait.waitForElementToBeGone(driver, wl.LoginModuleLoding, 300);
		Wait.WaitForElement(driver, OR.New_Reservation_Button);
		Wait.explicit_wait_visibilityof_webelement_600(driver.findElement(By.xpath(OR.New_Reservation_Button)), driver);
	}
	
	
}
