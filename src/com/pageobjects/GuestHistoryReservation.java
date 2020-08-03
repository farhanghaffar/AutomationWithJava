package com.innroad.inncenter.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.innroad.inncenter.interfaces.ICreateReservation;
import com.innroad.inncenter.interfaces.IGuestHistoryReservation;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Accounts;
import com.innroad.inncenter.webelements.Elements_GuestHistory;
import com.innroad.inncenter.webelements.Elements_Reservation;

public class GuestHistoryReservation implements IGuestHistoryReservation {

	public void guestHistory(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		Wait.wait3Second();
		guest_his.Guest_History.click();
		Wait.wait3Second();
		System.out.println(" System successfully Navigated to Guest History ");
		Wait.wait3Second();
	}

	public void guestHistory_NewAccount(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		guest_his.newAccount.click();
		try {
			Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(OR.GuestHistoryAccountPage)), driver);
		} catch (Exception e) {
			Utility.app_logs.info("Catch body");
			driver.navigate().refresh();
			Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(OR.GuestHistoryAccountPage)), driver);
		}
		// Utility.ElementFinderUntillNotShow(By.xpath(OR.GuestHistoryAccountPage),
		// driver);

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
			System.out.println("Check box already checked");
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
			System.out.println(" Account Name of created guest is  " + getAccountName);
			Thread.sleep(3000);

			String getAccountNo = CreateAccount.AccountNo.getText();
			System.out.println(" Account Number of created guest is  " + getAccountNo);

			// if(getToastermessage_ReservationSucess.equals("The account Test
			// Account has been successfully created."));
		}
	}

	public void newReservation(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		guest_his.newReservationButton.click();
		Wait.wait10Second();
		Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);
	}

	public void roomAssignment(WebDriver driver, String PropertyName2, String Nights, String Adults, String Children,
			String RatepromoCode, String CheckorUncheckAssign, String RoomClassName, String RoomNumber)
			throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		ReservationPage.Click_RoomPicker.click();
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait3Second();
		/*
		 * try { new Select(ReservationPage.Select_property_RoomAssign).
		 * selectByVisibleText(PropertyName); } catch(Exception e) { new
		 * Select(ReservationPage.Select_property_RoomAssign2).
		 * selectByVisibleText(PropertyName); }
		 */
		Wait.wait15Second();
		ReservationPage.Click_Arrive_Datepicker.click();
		ReservationPage.Click_Today.click();
		ReservationPage.Enter_Nigts.sendKeys(Nights);
		ReservationPage.Enter_Adults.sendKeys(Adults);
		ReservationPage.Enter_Children.sendKeys(Children);
		ReservationPage.Enter_Rate_Promocode.sendKeys(RatepromoCode);

		if (ReservationPage.Check_Assign_Room.isSelected()) {
			// ReservationPage.Check_Assign_Room.click();
			ReservationPage.Click_Search.click();
		} else {
			if (CheckorUncheckAssign.equals("Yes")) {
				ReservationPage.Check_Assign_Room.click();
				ReservationPage.Click_Search.click();
			} else {
				ReservationPage.Click_Search.click();
			}
		}
		try {

			new Select(ReservationPage.Select_Room_Class).selectByVisibleText(RoomClassName);
			String selectedOption = new Select(ReservationPage.Validating_UnAssgined_DDL).getFirstSelectedOption()
					.getText();
			System.out.println("selectedOption  " + selectedOption);
			if (selectedOption.equals("--Select--")) {
				// new
				// Select(ReservationPage.Select_Room_Number).selectByVisibleText(RoomNumber);
				new Select(ReservationPage.Select_Room_Number).selectByIndex(1);
				Wait.wait5Second();
			} else {
				System.out.println("Reservation is unassigned");
			}
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.Validation_Text_NoRooms, driver);
			System.out.println("Room Class are not Available for these dates");

		}
		ReservationPage.Click_Select.click();
		try {
			Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
		} catch (Exception e) {
			System.out.println("Verification failed");
		}
		Wait.wait5Second();
		if (ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
			ReservationPage.Click_Continue_RuleBroken_Popup.click();
		} else {
			System.out.println("Satisfied Rules");
		}

		if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
			String getTotasterTitle = ReservationPage.Toaster_Title.getText();
			String getToastermessage = ReservationPage.Toaster_Message.getText();
			System.out.println(getTotasterTitle + " " + getToastermessage);
			Assert.assertEquals(getTotasterTitle, "Room assignment has changed.");
			Assert.assertEquals(getToastermessage, "Room assignment has changed.");
		}
		Wait.wait2Second();
		String getPropertyName = ReservationPage.Get_Property_Name.getText();
		String getRoomclassName_status = ReservationPage.Get_RoomClass_Status.getText();
		System.out.println(getRoomclassName_status);
		Assert.assertEquals(getPropertyName, PropertyName2);
		String getRoomclassName[] = getRoomclassName_status.split(" ");
		// Assert.assertEquals(getRoomclassName[0],RoomClassName );
		if (CheckorUncheckAssign.equals("Yes")) {

		} else {
			Assert.assertEquals(getRoomclassName[3], "Unassigned");
		}
	}

	public void saveReservation(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		double waittime = 0.12;
		long startTime = System.currentTimeMillis();
		ReservationPage.Click_Save_ReservationDetails.click();
		Wait.wait3Second();
		try {
			if (ReservationPage.Verify_Depos_policy.isDisplayed()) {
				ReservationPage.Click_Without_Deposit.click();
			}
		} catch (Exception ne) {

		}
		try {
			if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
				String getTotasterTitle_ReservationSucess = ReservationPage.Toaster_Title.getText();
				String getToastermessage_ReservationSucess = ReservationPage.Toaster_Message.getText();
				Assert.assertEquals(getTotasterTitle_ReservationSucess, "Reservation Saved");
				long endTime = System.currentTimeMillis();
				double totalTime = (endTime - startTime);
				System.out.println(totalTime + " in Millsecs");
				double TotalTimeinsecs = totalTime / 1000;
				double ActualTime = TotalTimeinsecs - waittime - 3;
				System.out.println(ActualTime + " in secs");
				if (getToastermessage_ReservationSucess.endsWith("has been saved successfully"))
					;

			}
		} catch (Exception e) {

		}
		Wait.wait10Second();

	}

	public void closeGuestHistoryReservation(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his_res = new Elements_GuestHistory(driver);
		guest_his_res.closeGuestHistoryRes.click();
		Wait.wait10Second();

	}

}
