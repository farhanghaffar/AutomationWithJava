package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.innroad.inncenter.interfaces.IFolioLineItemsVoid;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_FolioLineItemsVoid;
import com.innroad.inncenter.webelements.Elements_Reservation;

public class FolioLineItems implements IFolioLineItemsVoid {

	public static double processedamount = 0;
	public static double endingbalance;
	public static double folioendingbalanceafterpayment;
	public static String ReservationNumber;

	public static Logger folioLineItemsLogger = Logger.getLogger("FolioLineItems");

	public void IPropertySelector(WebDriver driver, String PropertyName) throws InterruptedException {

		if (driver.findElement(By.xpath("//b[@role='presentation']")).isDisplayed()) {

			driver.findElement(By.id("s2id_autogen1")).click();
			// Wait.explicit_wait_xpath("select2-drop");
			driver.findElement(By.xpath("//li/div[.='" + PropertyName + "']")).click();
			Wait.wait15Second();
		} else {
			folioLineItemsLogger.info("Single Property");
		}
	}

	public void clickNewReservationButton(WebDriver driver) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		FolioLineItems.New_Reservation_Button.click();
		Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
		Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);
	}

	public void marketingInfo(WebDriver driver, String MarketSegment, String Referral, String Travel_Agent,
			String ExtReservation) throws InterruptedException {

		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		new Select(FolioLineItems.Reservation_market_Segment).selectByVisibleText(MarketSegment);
		new Select(FolioLineItems.Reservation_Referral).selectByVisibleText(Referral);
		try {
			FolioLineItems.Enter_Travel_Agent.sendKeys(Travel_Agent);
		} catch (Exception e) {

		}
		FolioLineItems.Enter_Ext_Reservation.sendKeys(ExtReservation);
	}

	public void contactInformation(WebDriver driver, String saluation, String FirstName, String LastName,
			String Address, String Line1, String Line2, String Line3, String City, String Country, String State,
			String Postalcode, String Phonenumber, String alternativenumber, String Email, String Account,
			String IsTaxExempt, String TaxEmptext) {

		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);

		new Select(FolioLineItems.Select_Saluation).selectByVisibleText(saluation);
		FolioLineItems.Enter_First_Name.clear();
		FolioLineItems.Enter_Last_Name.clear();
		FolioLineItems.Enter_Address_Search.clear();
		FolioLineItems.Enter_Line1.clear();
		FolioLineItems.Enter_Line2.clear();
		FolioLineItems.Enter_Line3.clear();
		FolioLineItems.Enter_City.clear();
		FolioLineItems.Enter_Postal_Code.clear();
		;
		FolioLineItems.Enter_Phone_Number.clear();
		;
		FolioLineItems.Enter_Alt_Phn_number.clear();
		;
		FolioLineItems.Enter_email.clear();
		;
		FolioLineItems.Enter_First_Name.sendKeys(FirstName);
		FolioLineItems.Enter_Last_Name.sendKeys(LastName);
		FolioLineItems.Enter_Address_Search.sendKeys(Address);
		FolioLineItems.Enter_Line1.sendKeys(Line1);
		FolioLineItems.Enter_Line2.sendKeys(Line2);
		FolioLineItems.Enter_Line3.sendKeys(Line3);
		FolioLineItems.Enter_City.sendKeys(City);
		new Select(FolioLineItems.Select_Country).selectByVisibleText(Country);
		new Select(FolioLineItems.Select_State).selectByVisibleText(State);
		FolioLineItems.Enter_Postal_Code.sendKeys(Postalcode);
		FolioLineItems.Enter_Phone_Number.sendKeys(Phonenumber);
		FolioLineItems.Enter_Alt_Phn_number.sendKeys(alternativenumber);
		FolioLineItems.Enter_email.sendKeys(Email);
		try {
			FolioLineItems.Enter_Account.sendKeys(Account);
		} catch (Exception e) {

		}
		if (IsTaxExempt.equals("Yes")) {
			if (FolioLineItems.Check_IsTaxExempt.isSelected()) {
				FolioLineItems.Enter_TaxExemptId.sendKeys(TaxEmptext);
			} else {
				FolioLineItems.Check_IsTaxExempt.click();
				FolioLineItems.Enter_TaxExemptId.sendKeys(TaxEmptext);
			}
		}
	}

	public void billingInformation(WebDriver driver, String PaymentMethod, String AccountNumber, String ExpiryDate,
			String BillingNotes) {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		new Select(FolioLineItems.Select_Payment_Method).selectByVisibleText(PaymentMethod);
		if (PaymentMethod.equalsIgnoreCase("MC") || PaymentMethod.equalsIgnoreCase("Amex")
				|| PaymentMethod.equalsIgnoreCase("Discover") || PaymentMethod.equalsIgnoreCase("Visa")) {
			FolioLineItems.Enter_Account_Number.sendKeys(AccountNumber);
			FolioLineItems.Enter_Exp_Card.sendKeys(ExpiryDate);
			FolioLineItems.Enter_Billing_Note.sendKeys(BillingNotes);

		}
	}

	public void roomAssignment(WebDriver driver, String PropertyName, String Nights, String Adults, String Children,
			String RatepromoCode, String CheckorUncheckAssign, String RoomClassName, String RoomNumber)
			throws InterruptedException {

		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		FolioLineItems.Click_RoomPicker.click();
		Thread.sleep(5000);
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait3Second();
		/*
		 * try { new
		 * Select(FolioLineItems.Select_property_RoomAssign).selectByVisibleText
		 * (PropertyName); } catch(Exception e) { new
		 * Select(FolioLineItems.Select_property_RoomAssign2).
		 * selectByVisibleText(PropertyName); }
		 */

		Wait.wait2Second();
		FolioLineItems.Click_Arrive_Datepicker.click();
		FolioLineItems.Click_Today.click();
		FolioLineItems.Enter_Nigts.clear();
		FolioLineItems.Enter_Nigts.sendKeys(Nights);
		FolioLineItems.Enter_Adults.sendKeys(Adults);
		FolioLineItems.Enter_Children.sendKeys(Children);
		FolioLineItems.Enter_Rate_Promocode.sendKeys(RatepromoCode);

		if (FolioLineItems.Check_Assign_Room.isSelected()) {
			// FolioLineItems.Check_Assign_Room.click();
			FolioLineItems.Click_Search.click();
		} else {
			if (CheckorUncheckAssign.equals("Yes")) {
				FolioLineItems.Check_Assign_Room.click();
				FolioLineItems.Click_Search.click();
			} else {
				FolioLineItems.Click_Search.click();
			}
		}
		try {

			new Select(FolioLineItems.Select_Room_Class).selectByVisibleText(RoomClassName);
			String selectedOption = new Select(FolioLineItems.Validating_UnAssgined_DDL).getFirstSelectedOption()
					.getText();
			folioLineItemsLogger.info("selectedOption  " + selectedOption);
			if (selectedOption.equals("--Select--")) {
				// new
				// Select(FolioLineItems.Select_Room_Number).selectByVisibleText(RoomNumber);
				new Select(FolioLineItems.Select_Room_Number).selectByIndex(1);
			} else {
				folioLineItemsLogger.info("Reservation is unassigned");
			}
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.Validation_Text_NoRooms, driver);
			folioLineItemsLogger.info("Room Class are not Available for these dates");

		}
		FolioLineItems.Click_Select.click();
		try {
			Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
		} catch (Exception e) {
			folioLineItemsLogger.info("Verification failed");
		}
		Wait.wait5Second();
		if (FolioLineItems.Verify_RulesBroken_Popup.isDisplayed()) {
			FolioLineItems.Click_Continue_RuleBroken_Popup.click();
		} else {
			folioLineItemsLogger.info("Satisfied Rules");
		}

		if (FolioLineItems.Verify_Toaster_Container.isDisplayed()) {
			String getTotasterTitle = FolioLineItems.Toaster_Title.getText();
			String getToastermessage = FolioLineItems.Toaster_Message.getText();
			folioLineItemsLogger.info(getTotasterTitle + " " + getToastermessage);
			Assert.assertEquals(getTotasterTitle, "Room assignment has changed.");
			Assert.assertEquals(getToastermessage, "Room assignment has changed.");
		}
		Wait.wait2Second();
		String getPropertyName = FolioLineItems.Get_Property_Name.getText();
		String getRoomclassName_status = FolioLineItems.Get_RoomClass_Status.getText();
		folioLineItemsLogger.info(getRoomclassName_status);
		Assert.assertEquals(getPropertyName, PropertyName);
		String getRoomclassName[] = getRoomclassName_status.split(" ");

		// Assert.assertEquals(getRoomclassName[0],RoomClassName );
		if (CheckorUncheckAssign.equals("Yes")) {

		} else {
			Assert.assertEquals(getRoomclassName[3], "Unassigned");
		}
	}

	public void roomAssignment1(WebDriver driver, String PropertyName, String Nights, String Adults, String Children,
			String RatepromoCode, String CheckorUncheckAssign, String RoomClassName, String RoomNumber)
			throws InterruptedException {

		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		FolioLineItems.Click_RoomPicker.click();
		Thread.sleep(5000);
		Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp, driver);
		Wait.wait3Second();
		/*
		 * try { new
		 * Select(FolioLineItems.Select_property_RoomAssign).selectByVisibleText
		 * (PropertyName); } catch(Exception e) { new
		 * Select(FolioLineItems.Select_property_RoomAssign2).
		 * selectByVisibleText(PropertyName); }
		 */

		Wait.wait2Second();
		FolioLineItems.Click_Arrive_Datepicker.click();
		FolioLineItems.Click_Today.click();
		FolioLineItems.Enter_Nigts.clear();
		FolioLineItems.Enter_Nigts.sendKeys(Nights);
		FolioLineItems.Enter_Adults.sendKeys(Adults);
		FolioLineItems.Enter_Children.sendKeys(Children);
		FolioLineItems.Enter_Rate_Promocode.sendKeys(RatepromoCode);

		if (FolioLineItems.Check_Assign_Room.isSelected()) {
			// FolioLineItems.Check_Assign_Room.click();
			FolioLineItems.Click_Search.click();
		} else {
			if (CheckorUncheckAssign.equals("Yes")) {
				FolioLineItems.Check_Assign_Room.click();
				FolioLineItems.Click_Search.click();
			} else {
				FolioLineItems.Click_Search.click();
			}
		}
		try {

			new Select(FolioLineItems.Select_Room_Class).selectByVisibleText(RoomClassName);
			String selectedOption = new Select(FolioLineItems.Validating_UnAssgined_DDL).getFirstSelectedOption()
					.getText();
			folioLineItemsLogger.info("selectedOption  " + selectedOption);
			if (selectedOption.equals("--Select--")) {
				// new
				// Select(FolioLineItems.Select_Room_Number).selectByVisibleText(RoomNumber);
				new Select(FolioLineItems.Select_Room_Number).selectByIndex(1);
			} else {
				folioLineItemsLogger.info("Reservation is unassigned");
			}
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.Validation_Text_NoRooms, driver);
			folioLineItemsLogger.info("Room Class are not Available for these dates");

		}
		FolioLineItems.Click_Select.click();
		try {
			Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup, driver);
		} catch (Exception e) {
			folioLineItemsLogger.info("Verification failed");
		}
		Wait.wait5Second();
		if (FolioLineItems.Verify_RulesBroken_Popup.isDisplayed()) {
			FolioLineItems.Click_Continue_RuleBroken_Popup.click();
		} else {
			folioLineItemsLogger.info("Satisfied Rules");
		}

		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.Reservation_ModuleLoading)), 180);
		if (FolioLineItems.Verify_Toaster_Container.isDisplayed()) {
			String getTotasterTitle = FolioLineItems.Toaster_Title.getText();
			String getToastermessage = FolioLineItems.Toaster_Message.getText();
			folioLineItemsLogger.info(getTotasterTitle + " " + getToastermessage);
			Assert.assertEquals(getTotasterTitle, "Room assignment has changed.");
			Assert.assertEquals(getToastermessage, "Room assignment has changed.");
		}
		Wait.wait2Second();
		String getPropertyName = FolioLineItems.Get_Property_Name.getText();
		String getRoomclassName_status = FolioLineItems.Get_RoomClass_Status.getText();
		folioLineItemsLogger.info(getRoomclassName_status);
		// Assert.assertEquals(getPropertyName, PropertyName);
		String getRoomclassName[] = getRoomclassName_status.split(":");
		System.out.println(getRoomclassName);
		// Assert.assertEquals(getRoomclassName[0],RoomClassName );
		if (CheckorUncheckAssign.equals("Yes")) {

		} else {
			Assert.assertEquals(getRoomclassName[1].trim(), "Unassigned");
		}

	}

	// Save the Reservation
	public void saveReservation(WebDriver driver) throws InterruptedException {

		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		double waittime = 0.12;
		long startTime = System.currentTimeMillis();
		FolioLineItems.Click_Save_ReservationDetails.click();
		Wait.wait3Second();
		try {
			if (FolioLineItems.Verify_Depos_policy.isDisplayed()) {
				FolioLineItems.Click_Without_Deposit.click();
			}
		} catch (Exception ne) {

		}
		try {
			if (FolioLineItems.Verify_Toaster_Container.isDisplayed()) {
				String getTotasterTitle_ReservationSucess = FolioLineItems.Toaster_Title.getText();
				String getToastermessage_ReservationSucess = FolioLineItems.Toaster_Message.getText();
				Assert.assertEquals(getTotasterTitle_ReservationSucess, "Reservation Saved");
				long endTime = System.currentTimeMillis();
				double totalTime = (endTime - startTime);
				// folioVoidLogger.info(totalTime + " in Millsecs");
				double TotalTimeinsecs = totalTime / 1000;
				double ActualTime = TotalTimeinsecs - waittime - 3;
				// folioVoidLogger.info(ActualTime + " in secs");
				if (getToastermessage_ReservationSucess.endsWith("has been saved successfully"))
					;

			}
		} catch (Exception e) {

		}
		Wait.wait10Second();
	}

	public void verifyFolioBalance(WebDriver driver) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		Wait.WaitForElement(driver, OR.click_Folio_tab);
		Wait.explicit_wait_visibilityof_webelement_120(FolioLineItems.click_Folio_tab, driver);
		FolioLineItems.click_Folio_tab.click();
		// Wait.explicit_wait_visibilityof_webelement_120(FolioLineItems.Click_Pay_Button);
		// float
		// folioBalance=Float.parseFloat(reservationSearch.getBalanceFolioLineItems.getText());

		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.Payment_Details_Folio_Balance, driver);
		String GetEndingBalance = FolioLineItems.Payment_Details_Folio_Balance.getText();
		// resSearchLogger.info(GetEndingBalance);
		String folioEndBalance = (GetEndingBalance
				.substring(GetEndingBalance.indexOf("(") + 1, GetEndingBalance.length() - 1).trim());
		String RemoveCurreny[] = folioEndBalance.split(" ");
		endingbalance = Double.parseDouble(RemoveCurreny[1]);
		// resSearchLogger.info("Ending balance before Payment " +
		// endingbalance);
		// endingbalance=Double.parseDouble(folioEndBalance);
		folioLineItemsLogger.info("Folio Ending balance  " + endingbalance);
		// Wait.wait5Second();

		getReservationNumber(driver);

		// .String
		// resLocator="//span[contains(text(),'"+resNumber.trim()+"')]/../../td[4]/div/a";
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.Click_Pay_Button, driver);
		Utility.ScrollToElement(FolioLineItems.Click_Pay_Button, driver);
		if (endingbalance != 0.0) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", FolioLineItems.Click_Pay_Button);
			// FolioLineItems.Click_Pay_Button.click();
			Wait.explicit_wait_visibilityof_webelement_150(FolioLineItems.Verify_Payment_Details_popup, driver);
			new Select(FolioLineItems.Select_Paymnet_Method).selectByVisibleText("Cash");
			Wait.WaitForElement(driver, OR.payment_AddButton);
			Wait.explicit_wait_visibilityof_webelement_150(FolioLineItems.payment_AddButton, driver);

			FolioLineItems.payment_AddButton.click();
			Thread.sleep(5000);
			Wait.explicit_wait_visibilityof_webelement_600(FolioLineItems.Continue_Pay_Button, driver);
			Wait.WaitForElement(driver, OR.Continue_Pay_Button);
			jse.executeScript("arguments[0].click();", FolioLineItems.Continue_Pay_Button);
			try {
				Wait.waitForElementToBeGone(driver, FolioLineItems.Continue_Pay_Button, 30);
			} catch (Exception e) {
				FolioLineItems.Continue_Pay_Button.click();
				Utility.app_logs.info("Click Continue again");
				Wait.waitForElementToBeGone(driver, FolioLineItems.Continue_Pay_Button, 30);
			}
			Wait.explicit_wait_visibilityof_webelement_600(FolioLineItems.Payment_Details_Folio_Balance, driver);
			Wait.WaitForElement(driver, OR.Payment_Details_Folio_Balance);
			Wait.explicit_wait_visibilityof_webelement_150(FolioLineItems.Payment_Details_Folio_Balance, driver);
			Wait.WaitForElement(driver, OR.Payment_Details_Folio_Balance);
			String balanceAfterPayment = FolioLineItems.Payment_Details_Folio_Balance.getText();
			String GetEndingBalanceafterpayment = (balanceAfterPayment
					.substring(balanceAfterPayment.indexOf("(") + 1, balanceAfterPayment.length() - 1).trim());
			String RemoveCurreny1[] = GetEndingBalanceafterpayment.split(" ");
			folioendingbalanceafterpayment = Double.parseDouble(RemoveCurreny1[1]);
			folioLineItemsLogger.info("Ending balance After Payment " + folioendingbalanceafterpayment);
			Wait.WaitForElement(driver, OR.folioSaveButton);
			Wait.explicit_wait_visibilityof_webelement(FolioLineItems.folioSaveButton, driver);
			jse.executeScript("arguments[0].click();", FolioLineItems.folioSaveButton);
			// FolioLineItems.folioSaveButton.click();
			Wait.explicit_wait_visibilityof_webelement(FolioLineItems.Toaster_Title, driver);
			Assert.assertEquals(FolioLineItems.Toaster_Title.getText(), "Reservation Saved");
			// Wait.wait5Second();

		}

	}

	// Void Folio Line Items
	public void folioLineItemsVoid(WebDriver driver, String Category, String Amount, String Notes)
			throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.click_Add_Button, driver);

		FolioLineItems.selectAllLineItems.click();

		FolioLineItems.clickVoidButton.click();
		Wait.WaitForElement(driver, OR.enterNotes);
		FolioLineItems.enterNotes.sendKeys(Notes);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.clickVoidButtonInNotes, driver);
		FolioLineItems.clickVoidButtonInNotes.click();

		Wait.WaitForElement(driver, OR.DisplayVoidItemsCheckBox);
		if (!FolioLineItems.DisplayVoidItemsCheckBox.isSelected()) {
			FolioLineItems.DisplayVoidItemsCheckBox.click();
			;
			Wait.wait2Second();

		}
		FolioLineItems.DisplayVoidItemsCheckBox.click();
		folioLineItemsLogger.info(" Checked Display Void Items CheckBox ");

		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		String getBalance1 = FolioLineItems.getBalanceFolioLineItems.getText();
		folioLineItemsLogger.info(" Balance of the Folio Line Items after void " + getBalance1);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.folioSaveButton, driver);
		FolioLineItems.folioSaveButton.click();
		Wait.wait5Second();
	}

	public void getReservationNumber(WebDriver driver) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		ReservationNumber = FolioLineItems.resNumber.getText();
		folioLineItemsLogger.info(ReservationNumber);
		Wait.wait5Second();

	}

	public String GetReservationNumber(WebDriver driver) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		ReservationNumber = FolioLineItems.resNumber.getText();
		folioLineItemsLogger.info(ReservationNumber);
		return ReservationNumber;

	}

	@Override
	public void adjustLineItem(WebDriver driver, String folioitemDescription, String folioLineAmount, String folioNotes)
			throws InterruptedException {
		// TODO Auto-generated method stub

	}

	public void FoliobillingInformation(WebDriver driver, String BillingName, String PaymentMethod,
			String AccountNumber, String ExpiryDate, String BillingNotes) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);

		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.FolioPopUp_BillingName, driver);
		// if (FolioLineItems.FolioPopUp_BillingName.equals(null)) {
		FolioLineItems.FolioPopUp_BillingName.sendKeys(BillingName);
		// }
		new Select(FolioLineItems.FolioPopUp_PaymentMethod).selectByVisibleText(PaymentMethod);
		Wait.wait5Second();
		if (FolioLineItems.FolioPopUp_AccountNumber.equals(null)) {
			if (PaymentMethod.equalsIgnoreCase("MC") || PaymentMethod.equalsIgnoreCase("Amex")
					|| PaymentMethod.equalsIgnoreCase("Discover") || PaymentMethod.equalsIgnoreCase("Visa")) {

				FolioLineItems.FolioPopUp_AccountNumber.sendKeys(AccountNumber);
				FolioLineItems.FolioPopUp_ExpiryDate.sendKeys(ExpiryDate);
			}
		}
		String GetPaymentMethod = FolioLineItems.FolioPopUp_PaymentMethod.getAttribute("value");

		assertTrue(GetPaymentMethod.contains(PaymentMethod), "particular reservation details isn't populated");
		Utility.ScrollToElement(FolioLineItems.FolioPopUp_SaveButton, driver);
		FolioLineItems.FolioPopUp_SaveButton.click();
	}

	public void FoliobillingInformation11(WebDriver driver, String BillingName, String PaymentMethod,
			String ReservationNumber) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);

		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.FolioPopUp_BillingName, driver);
		// if (FolioLineItems.FolioPopUp_BillingName.equals(null)) {
		FolioLineItems.FolioPopUp_BillingName.sendKeys(BillingName);
		// }
		try {
			new Select(FolioLineItems.FolioPopUp_PaymentMethod).selectByVisibleText(PaymentMethod);
		} catch (Exception e) {
			new Select(FolioLineItems.FolioPopUp_PaymentMethod).selectByVisibleText("Reservationn");
		}

		// Wait.explicit_wait_visibilityof_webelement(FolioLineItems.FolioPopUp_ReservationNumber);
		// FolioLineItems.FolioPopUp_ReservationNumber.sendKeys(ReservationNumber);
		// String ReservationPath = "//span[@class='span-account-type' and
		// text()='" + ReservationNumber + "']";
		// WebElement Reservation =
		// driver.findElement(By.xpath(ReservationPath));
		// Wait.explicit_wait_visibilityof_webelement_350(Reservation);
		// Reservation.click();
		// PaymentMethod = PaymentMethod + " - " + ReservationNumber;
		// Wait.wait2Second();
		// new
		// Select(FolioLineItems.FolioPopUp_PaymentMethod).selectByVisibleText(PaymentMethod);
		//
		// String GetPaymentMethod =
		// FolioLineItems.FolioPopUp_PaymentMethod.getAttribute("value");
		//
		// assertTrue(GetPaymentMethod.contains(PaymentMethod), "particular
		// reservation details isn't populated");
		Utility.ScrollToElement(FolioLineItems.FolioPopUp_SaveButton, driver);
		FolioLineItems.FolioPopUp_SaveButton.click();
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.PaymentDetail, driver);
		Utility.ScrollToElement(FolioLineItems.PaymentDetail, driver);
		String PaymentDetail = FolioLineItems.PaymentDetail.getAttribute("value");
		Utility.app_logs.info(PaymentDetail);
		assertTrue(PaymentDetail.contains(PaymentMethod),
				"Failed: Payment Detail Missmatched Expected " + PaymentMethod + "But found " + PaymentDetail);
	}

	public void AddfolioLineItems(WebDriver driver, String Category, String Amount) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.click_Add_Button, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Utility.ScrollToElement(FolioLineItems.click_Add_Button, driver);
		jse.executeScript("arguments[0].click();", FolioLineItems.click_Add_Button);

		// FolioLineItems.click_Add_Button.click();
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.selectCategory, driver);
		Utility.ScrollToElement(FolioLineItems.selectCategory, driver);
		new Select(FolioLineItems.selectCategory).selectByVisibleText(Category);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.enterAmount, driver);
		System.out.println("AMount in func:" + Amount);
		FolioLineItems.enterAmount.sendKeys("100");
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.clickCommitButton, driver);
		// jse.executeScript("arguments[0].click();",
		// FolioLineItems.clickCommitButton);
		Utility.ScrollToElement(FolioLineItems.clickCommitButton, driver);
		FolioLineItems.clickCommitButton.click();
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.getBalanceFolioLineItems, driver);
		String getBalance = FolioLineItems.getBalanceFolioLineItems.getText();
		folioLineItemsLogger.info(" Balance of the Folio Line Items " + getBalance);
	}

	public ArrayList<String> AddfolioLineItem(WebDriver driver, String Category, String Amount)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		Wait.wait5Second();
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.click_Add_Button, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Utility.ScrollToElement(FolioLineItems.click_Add_Button, driver);
		jse.executeScript("arguments[0].click();", FolioLineItems.click_Add_Button);
		test_steps.add("Click on Add button");

		// FolioLineItems.click_Add_Button.click();
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.selectCategory, driver);
		Utility.ScrollToElement(FolioLineItems.selectCategory, driver);

		// Assertions
		assertTrue(FolioLineItems.LineItemDate.isDisplayed(), "Line Item Date isn't Displayed");
		test_steps.add("Line Item Date is Displayed");
		assertTrue(FolioLineItems.selectCategory.isDisplayed(), "Category isn't Displayed");
		test_steps.add("Category is Displayed");
		assertTrue(FolioLineItems.foliolineItemDescField.isDisplayed(), "Line item description isn't Displayed");
		test_steps.add("Line item description is Displayed");
		assertTrue(FolioLineItems.enterAmount.isDisplayed(), "Amount isn't Displayed");
		test_steps.add("Amount is Displayed");
		assertTrue(FolioLineItems.Foliolineitems_QTY.isDisplayed(), "Quantity isn't Displayed");
		test_steps.add("Quantity is Displayed");
		assertTrue(FolioLineItems.Foliolineitems_QTY.getAttribute("value").equals("1"),
				"Quantity  Text isn't equal to 1");
		test_steps.add("Quantity  Text is equal to 1");
		new Select(FolioLineItems.selectCategory).selectByVisibleText(Category);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.enterAmount, driver);
		System.out.println("Amount in func:" + Amount);
		test_steps.add("Amount in func:" + Amount);
		FolioLineItems.enterAmount.sendKeys(Amount);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.clickCommitButton, driver);
		Utility.ScrollToElement(FolioLineItems.clickCommitButton, driver);
		FolioLineItems.clickCommitButton.click();
		test_steps.add("Click on Commit Button");
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.getBalanceFolioLineItems, driver);
		String getBalance = FolioLineItems.getBalanceFolioLineItems.getText();
		folioLineItemsLogger.info(" Balance of the Folio Line Items " + getBalance);
		test_steps.add(" Balance of the Folio Line Items " + getBalance);
		return test_steps;
	}

	public ArrayList<String> AddfolioLineItem_TaxExempt(WebDriver driver, String Category, String Amount)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		Wait.wait5Second();
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.click_Add_Folio_Button, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Utility.ScrollToElement(FolioLineItems.click_Add_Folio_Button, driver);
		jse.executeScript("arguments[0].click();", FolioLineItems.click_Add_Folio_Button);
		test_steps.add("Click on Add Folio Button");
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.selectCategory, driver);
		Utility.ScrollToElement(FolioLineItems.selectCategory, driver);
		test_steps.add("Select Category In Folio Line Item : " + Category);

		// Assertions
		assertTrue(FolioLineItems.LineItemDate.isDisplayed(), "Line Item Date isn't Displayed");
		test_steps.add("Line Item Date is Displayed");
		assertTrue(FolioLineItems.selectCategory.isDisplayed(), "Category isn't Displayed");
		assertTrue(FolioLineItems.foliolineItemDescField.isDisplayed(), "Line item description isn't Displayed");
		test_steps.add("Line item description is Displayed");
		assertTrue(FolioLineItems.enterAmount.isDisplayed(), "Amount isn't Displayed");
		assertTrue(FolioLineItems.Foliolineitems_QTY.isDisplayed(), "Quantity isn't Displayed");
		assertTrue(FolioLineItems.Foliolineitems_QTY.getAttribute("value").equals("1"),
				"Quantity  Text isn't equal to 1");
		new Select(FolioLineItems.selectCategory).selectByVisibleText(Category);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.enterAmount, driver);
		System.out.println("Entered Amount In Folio Line Item :" + Amount);
		FolioLineItems.enterAmount.sendKeys(Amount);
		test_steps.add("Amount Entered In Folio Line Item Is :" + Amount);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.clickFolioCommitButton, driver);
		Utility.ScrollToElement(FolioLineItems.clickFolioCommitButton, driver);
		FolioLineItems.clickFolioCommitButton.click();
		test_steps.add("Click on Commit Button");
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.getBalanceFolioLineItems, driver);
		String getBalance = FolioLineItems.getBalanceFolioLineItems.getText();
		folioLineItemsLogger.info(" Balance of the Folio Line Items " + getBalance);
		test_steps.add("Ending Balance of the Folio Line Items Is: " + getBalance);
		return test_steps;
	}

	public void VerifyTaxAdded(WebDriver driver, String Amount) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		WebElement TaxAdded = driver.findElement(By.xpath("(//td[@class='lineitem-tax'])[2]//span"));
		Wait.explicit_wait_visibilityof_webelement(TaxAdded, driver);
		assertEquals(TaxAdded.isDisplayed(), true, "tax is not added to the amount");
	}

	public ArrayList<String> folioLineItemsVoid(WebDriver driver, String Category, String Amount, String Notes,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		Wait.WaitForElement(driver, OR.click_Add_Button);
		Utility.ScrollToElement(FolioLineItems.selectAllLineItems, driver);
		Wait.wait2Second();
		js1.executeScript("arguments[0].click();", FolioLineItems.selectAllLineItems);
		js1.executeScript("arguments[0].click();", FolioLineItems.clickVoidButton);
		Wait.WaitForElement(driver, OR.enterNotes);
		Wait.wait3Second();
		assertTrue(FolioLineItems.VoidNotesPopup.isDisplayed(), " Notes Tab didn't Displayed");
		test_steps.add("Void->Notes Tab is Displayed");
		folioLineItemsLogger.info(" Void->Notes Tab is Displayed");

		FolioLineItems.enterNotes.sendKeys(Notes);
		// Wait.explicit_wait_visibilityof_webelement(FolioLineItems.clickVoidButtonInNotes,
		// driver);
		Wait.WaitForElement(driver, OR.clickVoidButtonInNotes);

		FolioLineItems.clickVoidButtonInNotes.click();
		Wait.WaitForElement(driver, OR.click_Add_Button);
		assertTrue(FolioLineItems.click_Add_Button.isDisplayed(), " Folio Page didn't Displayed");
		test_steps.add("Folio Page is Displayed");
		folioLineItemsLogger.info(" Folio Page is Displayed");

		String Lineitmessize = Integer.toString(FolioLineItems.LineItems.size());

		assertTrue(Lineitmessize.equals("0"), "Line Items Size isn't zero");
		test_steps.add("Line Item size is:" + FolioLineItems.LineItems.size());
		folioLineItemsLogger.info(" Line Item size is:" + FolioLineItems.LineItems.size());

		assertTrue(ReservationPage.Res_Reset_Button.isEnabled(), "Reset Button isn't Enabled");
		test_steps.add("Reset Button is Enabled");
		folioLineItemsLogger.info(" Reset Button is Enabled");

		assertTrue(ReservationPage.ReservationSaveButton.isEnabled(), " Save Button isn't Enabledo");
		test_steps.add("Save Button is Enabled");
		folioLineItemsLogger.info("Save Button is Enabled");

		assertTrue(ReservationPage.SaveAndCloseReservation.isEnabled(), " Save & Close Button isn't Enabled");
		test_steps.add("Save & Close Button is Enabled");
		folioLineItemsLogger.info("Save & Close Button is Enabled");

		return test_steps;
	}

	public ArrayList<String> ClickDesResLineItem_1sttax(WebDriver driver, String TaxItemName)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		int LineItemDescription_Size = Elements_FolioLineItemsVoid.LineItems_Description.size();
		Utility.ScrollToElement(Elements_FolioLineItemsVoid.LineItems_Description.get(LineItemDescription_Size - 1),
				driver);
		Elements_FolioLineItemsVoid.LineItems_Description.get(LineItemDescription_Size - 1).click();
		Wait.wait2Second();
		WebElement tax_item = driver.findElement(
				By.xpath("//table[1]/tbody/tr[last()]/td[5]/a[contains(@data-bind,'text: $data.Description')]"));
		String tax_item_text = tax_item.getText();
		System.out.println(tax_item_text + ":" + TaxItemName);
		assertEquals(tax_item_text != TaxItemName, true, "New tax item does not display");
		return test_steps;
	}

	public ArrayList<String> CancelTaxFolioItem_Res(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		try {
			String cancelPath = "//div[@class='modal modal-overflow in']//div[@class='modal-footer']//button[.='Cancel']";
			driver.findElement(By.xpath(cancelPath)).click();
		} catch (Exception e) {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
		}
		return test_steps;

		// Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new
		// Elements_FolioLineItemsVoid(driver);
		// Wait.wait5Second();
		// Wait.explicit_wait_visibilityof_webelement(Elements_FolioLineItemsVoid.ItemDatails_CancelButton,
		// driver);
		// Utility.ScrollToElement(Elements_FolioLineItemsVoid.ItemDatails_CancelButton,
		// driver);
		// Elements_FolioLineItemsVoid.ItemDatails_CancelButton.click();

	}

	public ArrayList<String> ClickDesResLineItem(WebDriver driver, String TaxItemName) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		int LineItemDescription_Size = Elements_FolioLineItemsVoid.LineItems_Description.size();
		Utility.ScrollToElement(Elements_FolioLineItemsVoid.LineItems_Description.get(LineItemDescription_Size - 1),
				driver);
		Elements_FolioLineItemsVoid.LineItems_Description.get(LineItemDescription_Size - 1).click();
		Wait.wait2Second();
		WebElement tax_item = driver.findElement(
				By.xpath("//table[1]/tbody/tr[last()]/td[5]/a[contains(@data-bind,'text: $data.Description')]"));
		String tax_item_text = tax_item.getText();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", tax_item);
		System.out.println(tax_item_text + ":" + TaxItemName);
		assertEquals(tax_item_text, TaxItemName, "New tax item does not display");
		test_steps.add("Successfully Verify Tax Item : " + TaxItemName);
		return test_steps;
	}
	public ArrayList<String> ClickDesResLineItem_Tax2(WebDriver driver, String TaxItemName) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_FolioLineItemsVoid Elements_FolioLineItemsVoid = new Elements_FolioLineItemsVoid(driver);

		int LineItemDescription_Size = Elements_FolioLineItemsVoid.LineItems_Description.size();
		Utility.ScrollToElement(Elements_FolioLineItemsVoid.LineItems_Description.get(LineItemDescription_Size - 1),
				driver);
		Elements_FolioLineItemsVoid.LineItems_Description.get(LineItemDescription_Size - 1).click();
		Wait.wait2Second();
		WebElement tax_item = driver.findElement(
				By.xpath("//table[1]/tbody/tr[last()]/td[5]/a[contains(@data-bind,'text: $data.Description')]"));
		String tax_item_text = tax_item.getText();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", tax_item);
		System.out.println(tax_item_text + ":" + TaxItemName);
		assertEquals(tax_item_text, TaxItemName, "New tax item does not display");
		return test_steps;
	}

}
