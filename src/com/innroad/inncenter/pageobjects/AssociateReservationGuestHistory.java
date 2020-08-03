package com.innroad.inncenter.pageobjects;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.innroad.inncenter.interfaces.IAssociateReservationGuestHistory;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Accounts;
import com.innroad.inncenter.webelements.Elements_GuestHistory;
import com.innroad.inncenter.webelements.Elements_Reservation;

public class AssociateReservationGuestHistory implements IAssociateReservationGuestHistory {
	public static Logger associateResGuestLogger = Logger.getLogger("AssociateReservationGuestHistory");

	public void guestHistory(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		Wait.wait3Second();
		guest_his.Guest_History.click();
		Wait.wait3Second();
		associateResGuestLogger.info(" System successfully Navigated to Guest History ");
		Wait.wait3Second();
	}

	public void guestHistory_NewAccount(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		guest_his.newAccount.click();
		associateResGuestLogger.info(" Clicked on New Account ");
		Wait.wait3Second();

	}

	public void guestHistory_AccountDetails(WebDriver driver, String salutation, String FirstName) {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		new Select(guest_his.selectAccountSalutation).selectByVisibleText(salutation);
		guest_his.accountFirstName.sendKeys(FirstName);
		// String getAccountNumber= guest_his.accountNumber.getText();
		// System.out.println(" Account Number is " +getAccountNumber);

	}

	public void accountAttributes(WebDriver driver, String MarketSegment, String Referral) {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		// String getAccountSince= guest_his.accountSince.getText();
		// System.out.println(" Account Since " +getAccountSince);
		new Select(guest_his.Select_Market_Segment).selectByVisibleText(MarketSegment);
		new Select(guest_his.Select_Referrals).selectByVisibleText(Referral);

	}

	public void Mailinginfo(WebDriver driver, String AccountFirstName, String AccountLastName, String Phonenumber,
			String alternativeNumber, String Address1, String Address2, String Address3, String Email, String city,
			String State, String Postalcode) {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		CreateAccount.Account_Enter_First_Name.sendKeys(AccountFirstName);
		CreateAccount.Account_Enter_Last_Name.sendKeys(AccountLastName);
		CreateAccount.Account_Phone_number.sendKeys(Phonenumber);
		CreateAccount.Account_Enter_AltPhoneNumber.sendKeys(alternativeNumber);

		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		CreateAccount.Account_Enter_Line1.sendKeys(Address1);
		CreateAccount.Account_Enter_Line2.sendKeys(Address2);
		CreateAccount.Account_Enter_Line3.sendKeys(Address3);
		CreateAccount.Account_Enter_Email.sendKeys(Email);
		CreateAccount.Account_Enter_City.sendKeys(city);
		new Select(CreateAccount.Select_Account_State).selectByVisibleText(State);
		CreateAccount.Account_Enter_PostalCode.sendKeys(Postalcode);

	}

	public void Billinginfo(WebDriver driver) throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.wait5Second();
		if (CreateAccount.Account_Check_Mailing_info.isSelected()) {
			associateResGuestLogger.info(" Check box already checked ");
		} else {

			CreateAccount.Account_Check_Mailing_info.click();
		}

	}

	public void Save(WebDriver driver) throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		CreateAccount.Account_save.click();
		Thread.sleep(5000);

		if (CreateAccount.Verify_Toaster_Container.isDisplayed()) {
			String getTotasterTitle_ReservationSucess = CreateAccount.Toaster_Title.getText();
			String getToastermessage_ReservationSucess = CreateAccount.Toaster_Message.getText();
			Assert.assertEquals(getTotasterTitle_ReservationSucess, "Account Saved");
			Thread.sleep(3000);
			String getAccountName = CreateAccount.AccountName.getText();
			associateResGuestLogger.info(" Account Name of created guest is  " + getAccountName);
			Thread.sleep(3000);

			String getAccountNo = CreateAccount.AccountNo.getText();

			associateResGuestLogger.info(" Account Number of created guest is  " + getAccountNo);
		}

	}

	public void closeAccount(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		guest_his.closeReservation.click();
		Wait.wait10Second();

	}

	ArrayList<String> test_steps = new ArrayList<>();

	public void associateGuestToReservation(WebDriver driver, String GuestProfile) throws InterruptedException {

		Elements_Reservation res = new Elements_Reservation(driver);
		Reservation Reservation = new Reservation();
		res.reservation_Menu.click();
		Wait.wait10Second();
		Wait.explicit_wait_xpath(OR.Verify_Search_Loading_Gird, driver);
		res.Click_BasicSearch.click();
		Wait.wait10Second();
		Wait.explicit_wait_xpath(OR.Verify_Search_Loading_Gird, driver);
		res.clickReservation.click();
		Wait.wait10Second();
		Wait.explicit_wait_xpath(OR.findGuestProfile, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].scrollIntoView();", res.clickEditButton);
			res.clickEditButton.click();
			res.findGuestProfile.clear();
			res.findGuestProfile.sendKeys(GuestProfile);
			Wait.wait10Second();
		} catch (Exception e) {
			Reservation.GuestProfile(driver, GuestProfile, test_steps);
		}

		// Actions action = new Actions(driver);
		// action.moveToElement(reservation.selectGuest).build().perform();
		res.selectGuest.click();
		Wait.wait15Second();
		// Wait.explicit_wait_xpath(OR.continueButton);

		WebDriverWait wait = new WebDriverWait(driver, 150);
		// WebElement element =
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.continueButton)));
		Wait.wait10Second();
		res.continueButton.click();
		Wait.wait10Second();
		// WebDriverWait wait1 = (WebDriverWait) (new WebDriverWait(driver,
		// 150)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR.reservation_Save)));
		res.reservation_Save.click();
		associateResGuestLogger.info(" Success");
		Wait.wait10Second();

	}

}
