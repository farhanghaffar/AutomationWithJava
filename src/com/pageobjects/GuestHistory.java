package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.innroad.inncenter.interfaces.IGuestHistory;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Accounts;
import com.innroad.inncenter.webelements.Elements_GuestHistory;
import com.innroad.inncenter.webelements.Elements_On_All_Navigation;
import com.innroad.inncenter.webelements.Elements_Reservation;
import com.innroad.inncenter.webelements.Elements_Reservation_SearchPage;
import com.relevantcodes.extentreports.ExtentTest;

public class GuestHistory implements IGuestHistory {

	public static String getAccountNo;
	public static Logger guestHistoryLogger = Logger.getLogger("GuestHistory");

	public void guestHistory(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		Wait.wait3Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", guest_his.Guest_History);
		// guest_his.Guest_History.click();
		Wait.wait3Second();
		guestHistoryLogger.info(" System successfully Navigated to Guest History ");
		Wait.wait3Second();
	}

	public void GuestHistory_CombineButtonClick(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		try {
			Wait.explicit_wait_visibilityof_webelement_120(guest_his.GuestHis_CombineCheckBox, driver);
			Utility.ScrollToElement(guest_his.GuestHis_CombineCheckBox, driver);
			guest_his.GuestHis_CombineCheckBox.click();
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement_120(guest_his.GuestHis_CombineCheckBox1, driver);
			Utility.ScrollToElement(guest_his.GuestHis_CombineCheckBox1, driver);
			guest_his.GuestHis_CombineCheckBox1.click();
		}
	}

	public void EnterFirstLastNameSeperately(WebDriver driver, String FirstName, String LastName,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		Wait.wait2Second();
		Wait.WaitForElement(driver, OR.enterAccountFName);
		Wait.waitForElementToBeVisibile(By.xpath( OR.enterAccountFName), driver);
		Wait.waitForElementToBeClickable(By.xpath( OR.enterAccountFName), driver);
		guest_his.enterAccountFName.sendKeys(FirstName);
		guestHistoryLogger.info("First Name Enterted: " + FirstName);
		test_steps.add("First Name Enterted: " + FirstName);
		guest_his.enterGAccountLastName.sendKeys(LastName);
		guestHistoryLogger.info("Last Name Enterted: " + LastName);
		test_steps.add("Last Name Enterted: " + LastName);

	}

	
	public void GuestHistory_NewAccount(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		Utility.ScrollToElement(guest_his.newAccount, driver);
		guest_his.newAccount.click();
		try {
			// Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(OR.GuestHistoryAccountPage)));
			Wait.explicit_wait_visibilityof_webelement_600(guest_his.selectAccountSalutation, driver);
		} catch (Exception e) {
			Utility.app_logs.info("Catch body");
			driver.navigate().refresh();
			// Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(OR.GuestHistoryAccountPage)));
			guest_his.newAccount.click();
			Wait.explicit_wait_visibilityof_webelement_600(guest_his.selectAccountSalutation, driver);
		}
		assertTrue(guest_his.selectAccountSalutation.isDisplayed(), "Guest history page didn't display");

	}

	public void guestHistory_NewAccount(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", guest_his.newAccount);
		// guest_his.newAccount.click();
		Wait.explicit_wait_absenceofelement(OR.Account_ModuleLoading, driver);
		try {
			Wait.waitForElementToBeVisibile(By.xpath(OR.guestHistoryAccountPage), driver);
		} catch (Exception e) {
			Wait.waitForElementToBeVisibile(By.xpath(OR.GuestHistoryAccountPage), driver);

		}

	}

	public void guestHistory_AccountDetails(WebDriver driver, String salutation, String FirstName) {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		try {
			Wait.explicit_wait_visibilityof_webelement_150(guest_his.selectAccountSalutation, driver);
			new Select(guest_his.selectAccountSalutation).selectByVisibleText(salutation);
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement_150(guest_his.selectAccountSalutation_1, driver);
			new Select(guest_his.selectAccountSalutation_1).selectByVisibleText(salutation);
		}
		guest_his.accountFirstName.sendKeys(FirstName);

	}

	public void accountAttributes(WebDriver driver, String MarketSegment, String Referral,
			ArrayList<String> test_steps) {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		// String getAccountSince= guest_his.accountSince.getText();
		// guestHistoryLogger.info(" Account Since " +getAccountSince);
		new Select(guest_his.Select_Market_Segment).selectByVisibleText(MarketSegment);
		guestHistoryLogger.info("Market Segment Selected: " + MarketSegment);
		test_steps.add("Market Segment Selected: " + MarketSegment);
		new Select(guest_his.Select_Referrals).selectByVisibleText(Referral);
		guestHistoryLogger.info("Referral Selected: " + Referral);
		test_steps.add("Referral Selected: " + Referral);

	}

	public ArrayList<String> Mailinginfo(WebDriver driver, String AccountFirstName, String AccountLastName,
			String Phonenumber, String alternativeNumber, String Address1, String Address2, String Address3,
			String Email, String city, String State, String Postalcode) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);

		CreateAccount.Account_Enter_First_Name.sendKeys(AccountFirstName);
		guestHistoryLogger.info("Successfully Entered First Name: " + AccountFirstName);
		test_steps.add("Successfully Entered First Name: " + AccountFirstName);

		CreateAccount.Account_Enter_Last_Name.sendKeys(AccountLastName);
		guestHistoryLogger.info("Successfully Entered Last Name: " + AccountLastName);
		test_steps.add("Successfully Entered Last Name: " + AccountLastName);

		CreateAccount.Account_Phone_number.sendKeys(Phonenumber);
		guestHistoryLogger.info("Successfully Entered Phone Number: " + Phonenumber);
		test_steps.add("Successfully Entered Phone Number: " + Phonenumber);

		CreateAccount.Account_Enter_AltPhoneNumber.sendKeys(alternativeNumber);
		guestHistoryLogger.info("Successfully Entered Alternative Number: " + alternativeNumber);
		test_steps.add("Successfully Entered Alternative Number: " + alternativeNumber);

		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Utility.ElementFinderUntillNotShow(By.xpath(OR.Account_Enter_Line1), driver);
		js1.executeScript("arguments[0].scrollIntoView();", CreateAccount.Account_Enter_Line1);

		CreateAccount.Account_Enter_Line1.sendKeys(Address1);
		CreateAccount.Account_Enter_Line2.sendKeys(Address2);
		CreateAccount.Account_Enter_Line3.sendKeys(Address3);
		guestHistoryLogger.info("Successfully Scroll to Address");
		test_steps.add("Successfully Scroll to Address");

		CreateAccount.Account_Enter_Email.sendKeys(Email);
		guestHistoryLogger.info("Successfully Entered Email: " + Email);
		test_steps.add("Successfully Entered Email: " + Email);

		CreateAccount.Account_Enter_City.sendKeys(city);
		guestHistoryLogger.info("Successfully Entered City: " + city);
		test_steps.add("Successfully Entered City: " + city);

		new Select(CreateAccount.Select_Account_State).selectByVisibleText(State);
		guestHistoryLogger.info("Successfully Seleted State: " + State);
		test_steps.add("Successfully Seleted State: " + State);

		CreateAccount.Account_Enter_PostalCode.sendKeys(Postalcode);
		guestHistoryLogger.info("Successfully Entered Postalcode: " + Postalcode);
		test_steps.add("Successfully Entered Postalcode: " + Postalcode);

		return test_steps;
	}

	public void Billinginfo(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.wait5Second();
		if (CreateAccount.Account_Check_Mailing_info.isSelected()) {
			guestHistoryLogger.info("Same as Mailing Info Check box already checked");
			test_steps.add("Same as Mailing Info Check box already checked");

		} else {
			jse.executeScript("arguments[0].click();", CreateAccount.Account_Check_Mailing_info);

			guestHistoryLogger.info("Same as Mailing Info Check box checked");
			test_steps.add("Same as Mailing Info Check box checked");
		}

	}

	public ArrayList<String> Save(WebDriver driver) throws InterruptedException, IOException {

		ArrayList<String> test_steps = new ArrayList<>();
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		jse.executeScript("arguments[0].click();", CreateAccount.Account_save);
		guestHistoryLogger.info("Clicked on Save button");
		test_steps.add("Clicked on Save button");
		// CreateAccount.Account_save.click();
		// Thread.sleep(5000);
		try {
			Wait.explicit_wait_visibilityof_webelement(CreateAccount.Toaster_Message, driver);

			if (CreateAccount.Toaster_Title.isDisplayed()) {
				String getTotasterTitle_ReservationSucess = CreateAccount.Toaster_Title.getText();
				// String getToastermessage_ReservationSucess =
				// CreateAccount.Toaster_Message.getText();
				Assert.assertEquals(getTotasterTitle_ReservationSucess, "Account Saved");
				// Thread.sleep(3000);
				String getAccountName = CreateAccount.AccountName.getText();
				guestHistoryLogger.info(" Account Name of created guest is  " + getAccountName);
				test_steps.add(" Account Name of created guest is  " + getAccountName);
				// Thread.sleep(3000);

				String getAccountNo = CreateAccount.AccountNo.getText();
				guestHistoryLogger.info(" Account Number of created guest is  " + getAccountNo);
				test_steps.add(" Account Number of created guest is  " + getAccountNo);
				FileOutputStream fos = new FileOutputStream(".\\guestHistoryAccountNo.txt");
				byte b[] = getAccountNo.getBytes();
				fos.write(b);
				fos.close();
				guestHistoryLogger.info(" Account Number of created guest is saved in guestHistoryAccountNo.txt ");
				test_steps.add(" Account Number of created guest is saved in guestHistoryAccountNo.txt ");
				// Wait.wait5Second();

				// if(getToastermessage_ReservationSucess.equals("The account
				// Test
				// Account has been successfully created."));
			}
		} catch (Exception e) {
			System.err.println("Toaster Message not Appear");
			test_steps.add("Toaster Message not Appear");
		}
		assertTrue(!CreateAccount.Account_Save_Button.isEnabled(), "Save button didn't disable");
		assertTrue(!CreateAccount.Account_Save_Clsoe.isEnabled(), "Save button didn't disable");
		assertTrue(!CreateAccount.AccountsPage_Reset_Btn.isEnabled(), "Save button didn't disable");
		assertTrue(CreateAccount.NewReservation_Button.isEnabled(), "Save button didn't disable");

		return test_steps;
	}

	public String GetAccountNumber(WebDriver driver) throws InterruptedException {
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Utility.ScrollToElement(CreateAccount.AccountNo_1, driver);
		String getAccountNo = CreateAccount.AccountNo_1.getAttribute("value");
		guestHistoryLogger.info(" Account Number of created guest is  " + getAccountNo);
		return getAccountNo;
	}

	public void ChangeAccountNumber(WebDriver driver, String AccountNumber) throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement_150(CreateAccount.AccountNo_1, driver);
		CreateAccount.AccountNo_1.clear();
		CreateAccount.AccountNo_1.sendKeys(AccountNumber);
		Wait.wait3Second();
		String selectedOption = CreateAccount.AccountNo_1.getAttribute("value");

		System.out.println(selectedOption);
		Assert.assertEquals(AccountNumber, selectedOption);

	}

	public void newReservation(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", guest_his.newReservationButton);
		// guest_his.newReservationButton.click();
		Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load, driver);
		Wait.wait15Second();
	}

	/*
	 * public void roomAssignment(WebDriver driver,String PropertyName, String
	 * Nights, String Adults, String Children, String RatepromoCode,String
	 * CheckorUncheckAssign, String RoomClassName, String RoomNumber) throws
	 * InterruptedException {
	 * 
	 * Elements_Reservation ReservationPage = new Elements_Reservation(driver);
	 * ReservationPage.Click_RoomPicker.click();
	 * Wait.explicit_wait_xpath(OR.Room_Assignment_PopUp); Wait.wait3Second();
	 * try { new
	 * Select(ReservationPage.Select_property_RoomAssign).selectByVisibleText(
	 * PropertyName); } catch(Exception e) { new
	 * Select(ReservationPage.Select_property_RoomAssign2).selectByVisibleText(
	 * PropertyName); } Wait.wait15Second();
	 * ReservationPage.Click_Arrive_Datepicker.click();
	 * ReservationPage.Click_Today.click();
	 * ReservationPage.Enter_Nigts.sendKeys(Nights);
	 * ReservationPage.Enter_Adults.sendKeys(Adults);
	 * ReservationPage.Enter_Children.sendKeys(Children);
	 * ReservationPage.Enter_Rate_Promocode.sendKeys(RatepromoCode);
	 * 
	 * if(ReservationPage.Check_Assign_Room.isSelected()) { //
	 * ReservationPage.Check_Assign_Room.click();
	 * ReservationPage.Click_Search.click(); } else {
	 * if(CheckorUncheckAssign.equals("Yes")) {
	 * ReservationPage.Check_Assign_Room.click();
	 * ReservationPage.Click_Search.click(); } else {
	 * ReservationPage.Click_Search.click(); } } try {
	 * 
	 * new Select(ReservationPage.Select_Room_Class).selectByVisibleText(
	 * RoomClassName); String selectedOption = new
	 * Select(ReservationPage.Validating_UnAssgined_DDL).getFirstSelectedOption(
	 * ).getText(); guestHistoryLogger.info("selectedOption  " +selectedOption);
	 * if(selectedOption.equals("--Select--")) { //new
	 * Select(ReservationPage.Select_Room_Number).selectByVisibleText(RoomNumber
	 * ); new Select(ReservationPage.Select_Room_Number).selectByIndex(1);
	 * Wait.wait5Second(); } else {
	 * guestHistoryLogger.info("Reservation is unassigned"); } } catch(Exception
	 * e) { Wait.explicit_wait_xpath(OR.Validation_Text_NoRooms);
	 * guestHistoryLogger.info("Room Class are not Available for these dates");
	 * 
	 * } ReservationPage.Click_Select.click(); try {
	 * Wait.explicit_wait_xpath(OR.Verify_RulesBroken_Popup); } catch(Exception
	 * e) { guestHistoryLogger.info("Verification failed"); }
	 * Wait.wait5Second();
	 * if(ReservationPage.Verify_RulesBroken_Popup.isDisplayed()) {
	 * ReservationPage.Click_Continue_RuleBroken_Popup.click(); } else {
	 * guestHistoryLogger.info("Satisfied Rules"); }
	 * 
	 * if(ReservationPage.Verify_Toaster_Container.isDisplayed()) { String
	 * getTotasterTitle=ReservationPage.Toaster_Title.getText(); String
	 * getToastermessage=ReservationPage.Toaster_Message.getText();
	 * guestHistoryLogger.info(getTotasterTitle + " " +getToastermessage);
	 * Assert.assertEquals(getTotasterTitle, "Room assignment has changed.");
	 * Assert.assertEquals(getToastermessage, "Room assignment has changed."); }
	 * Wait.wait2Second(); String getPropertyName =
	 * ReservationPage.Get_Property_Name.getText(); String
	 * getRoomclassName_status=ReservationPage.Get_RoomClass_Status.getText();
	 * guestHistoryLogger.info(getRoomclassName_status);
	 * Assert.assertEquals(getPropertyName,PropertyName ); String
	 * getRoomclassName[]= getRoomclassName_status.split(" ");
	 * //Assert.assertEquals(getRoomclassName[0],RoomClassName );
	 * if(CheckorUncheckAssign.equals("Yes")) {
	 * 
	 * } else { Assert.assertEquals(getRoomclassName[3],"Unassigned" ); }
	 * 
	 * 
	 * }
	 */

	public void guestHistoryAccountNo(WebDriver driver) throws InterruptedException, IOException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		getAccountNo = guest_his.guestHistoryAccountNo.getText();
		guestHistoryLogger.info("Guest History Account Number is :" + getAccountNo);
		FileOutputStream fos = new FileOutputStream(".\\guestHistoryAccountNo.txt");
		byte b[] = getAccountNo.getBytes();
		fos.write(b);
		fos.close();
		Wait.wait5Second();

	}

	public void closeGuestHistoryReservation(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his_res = new Elements_GuestHistory(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", guest_his_res.closeGuestHistoryRes);
		// guest_his_res.closeGuestHistoryRes.click();
		Wait.wait10Second();

	}

	public void closeAccount(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);

		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", guest_his.closeReservation);
			// guest_his.closeReservation.click();
			Wait.wait5Second();
		} catch (Exception e) {
			UnsavedData(driver);

		}
		assertTrue(guest_his.newAccount.isDisplayed(), "Guest history page didn't display");

	}

	public ArrayList<String> searchAccount(WebDriver driver, String AccountFname, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.enterAccountFName, driver);
		guest_his.enterAccountFName.clear();
		guest_his.enterAccountFName.sendKeys(AccountFname);
		Wait.wait10Second();
		String AccountNumber = "";
		try {
			// guestHistoryLogger.info("GuestHistoryAccountNumber
			// :"+getGuestHistoryAccountNumber);
			FileReader fr = new FileReader(".\\guestHistoryAccountNo.txt");
			BufferedReader br = new BufferedReader(fr);

			while ((getAccountNo = br.readLine()) != null) {
				guestHistoryLogger.info(" Guest History Account Number :" + getAccountNo);

				Wait.wait10Second();
				guest_his.enterGuestHistoryAccountNo.clear();
				guest_his.enterGuestHistoryAccountNo.sendKeys(getAccountNo);
				AccountNumber = AccountNumber + getAccountNo;

			}
			br.close();
		} catch (IOException e) {
			guestHistoryLogger.info("File not found");
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", guest_his.clickSearchButton);

		// guest_his.clickSearchButton.click();
		Wait.WaitForElement(driver, OR.Number_Of_Accounts);
		int count = driver.findElements(By.xpath(OR.Number_Of_Accounts)).size();

		if (count == 0) {
			test_steps.add(
					"There are No Accounts available with " + AccountFname + " and Account Number " + AccountNumber);
			Utility.app_logs.info(
					"There are No Accounts available with " + AccountFname + " and Account Number " + AccountNumber);
		} else {
			test_steps.add("Number of Accounts available with " + AccountFname + " and Account Number " + AccountNumber
					+ " in this page are " + count);
			Utility.app_logs.info("Number of Accounts available with " + AccountFname + " and Account Number "
					+ AccountNumber + " in this page are " + count);
		}
		return test_steps;
	}

	public ArrayList<String> searchAccount(WebDriver driver, String AccountFname, String AccountNumber,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		driver.navigate().refresh();

		Wait.waitUntilPresenceOfElementLocated(OR.enterAccountFName, driver);
		guest_his.enterAccountFName.clear();
		guest_his.enterAccountFName.sendKeys(AccountFname);

		guest_his.enterGuestHistoryAccountNo.clear();
		guest_his.enterGuestHistoryAccountNo.sendKeys(AccountNumber);

		guest_his.clickSearchButton.click();
		Utility.app_logs.info("Click Search");

		Wait.WaitForElement(driver, OR.Number_Of_Accounts);
		Wait.WaitForElement(driver, OR.SearcehdGuestHistoryAccountNumber);
		System.out.println(
				"AccNume expec:" + AccountNumber + "Result:" + guest_his.SearcehdGuestHistoryAccountNumber.getText());
		// Assert.assertEquals(AccountNumber,
		// guest_his.SearcehdGuestHistoryAccountNumber.getText()),
		// "Failed: Searcehd Account Missmatched");

		return test_steps;
	}

	public void deleteGuestHistoryAccount(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", guest_his.selectGuestHistoryAccount);
		jse.executeScript("arguments[0].click();", guest_his.deleteButton);
		// Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.Toaster_Message)));
		// Utility.app_logs.info(driver.findElement(By.xpath(OR.Toaster_Message)).getText());
	}

	public void SelectSearchedGuestProfile(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		guest_his.ClickSearchedGuestProfile.click();
		Wait.wait2Second();

	}

	public void UnsavedData(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		guest_his.UnsavedData_YesBtn.click();
		Wait.wait2Second();

	}

	public ArrayList<String> type_AccName(WebDriver driver, ExtentTest test1, String AccountName,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		WebDriverWait wait = new WebDriverWait(driver, 90);
		// Wait.wait2Second();
		Wait.WaitForElement(driver, OR.Account_CorpAccountName);
		wait.until(ExpectedConditions.visibilityOf(CreateAccount.Account_CorpAccountName));
		CreateAccount.Account_CorpAccountName.sendKeys(AccountName);
		test_steps.add("Enter Account Name : " + AccountName);
		return test_steps;
	}

	public void OpenSearchedGuestHistroyAccount(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		guest_his.ClickSearchedGuestProfile.click();
		assertTrue(guest_his.selectAccountSalutation_1.isDisplayed(), "Guest History Account page didn't display");
	}

	public void OpenReservtionTab_GuestHistoryAccount(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);

		guest_his.ReservationTab_GuestAccount.click();
		assertTrue(reservationSearch.advanced.isDisplayed(),
				"Guest History Account-> Reservation Search page didn't display");
	}

	public ArrayList<String> Billinginfo(WebDriver driver, String PaymentMethod, String Account, String ExpDate,
			String BillingNotes, ArrayList<String> test_steps) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.wait5Second();
		if (CreateAccount.Account_Check_Mailing_info.isSelected()) {
			guestHistoryLogger.info("Check box already checked");
		} else {
			jse.executeScript("arguments[0].click();", CreateAccount.Account_Check_Mailing_info);

		}
		new Select(guest_his.GuestHistoryAccount_BillType).selectByVisibleText(PaymentMethod);
		test_steps.add("Payement Method : " + PaymentMethod);
		guest_his.GuestHistoryAccount_BillingAccountNum.sendKeys(Account);
		test_steps.add("Account number : " + Account);

		guest_his.GuestHistoryAccount_ExpDate.sendKeys(ExpDate);
		test_steps.add("Exp Date : " + ExpDate);

		guest_his.GuestHistoryAccount_BillingNotes.sendKeys(BillingNotes);
		test_steps.add("Billing Notes : " + BillingNotes);

		return test_steps;
	}

	public void ChangeGuestHistoryAccountStatus(WebDriver driver, String Status) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		new Select(guest_his.GuestHistoryAccountStatus).selectByVisibleText(Status);
		String StatusValue = new Select(guest_his.GuestHistoryAccountStatus).getFirstSelectedOption().getText();
		assertTrue(StatusValue.equals(Status), "Status didn't change");
	}

	public void OpenSearchedGuestHistroyAccount_ReservationTab(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		Elements_Reservation_SearchPage reservationSearch = new Elements_Reservation_SearchPage(driver);
		guest_his.ClickSearchedGuestProfile.click();
		assertTrue(reservationSearch.advanced.isDisplayed(),
				"Guest History Account-> Reservation Search page didn't display");
	}

	public ArrayList<String> VerifyInActiveGuestAccount(WebDriver driver, String AccountFname, String AccountNumber,
			String Status, ArrayList<String> test_steps) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		driver.navigate().refresh();

		Wait.waitUntilPresenceOfElementLocated(OR.enterAccountFName, driver);
		guest_his.enterAccountFName.clear();
		guest_his.enterAccountFName.sendKeys(AccountFname);

		guest_his.enterGuestHistoryAccountNo.clear();
		guest_his.enterGuestHistoryAccountNo.sendKeys(AccountNumber);
		new Select(guest_his.SearchGuestHistoryAccountStatus).selectByVisibleText(Status);

		guest_his.clickSearchButton.click();
		Utility.app_logs.info("Click Search");

		Wait.WaitForElement(driver, OR.Number_Of_Accounts);
		Wait.WaitForElement(driver, OR.SearcehdGuestHistoryAccountNumber);
		System.out.println(
				"AccNume expec:" + AccountNumber + "Result:" + guest_his.SearcehdGuestHistoryAccountNumber.getText());
		test_steps.add("Account Searched : " + AccountNumber);

		WebElement AccountStatus = driver.findElement(
				By.xpath("(//table[@class='table table-striped table-bordered table-hover']//td)[12]//span"));
		assertEquals(AccountStatus.getText(), Status, " Guest History Account Status didn't change");
		test_steps.add("Account Status : " + Status + ": Verified");

		return test_steps;
	}
	public void clickOnNewHistoryAccount(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		Wait.WaitForElement(driver, OR.NewGuestHistoryButton);
		Wait.waitForElementToBeClickable(By.xpath(OR.NewGuestHistoryButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.NewGuestHistoryButton), driver);
		guest_his.NewGuestHistoryButton.click();
		Wait.explicit_wait_absenceofelement(OR.Account_ModuleLoading, driver);
		try {
			Wait.waitForElementToBeVisibile(By.xpath(OR.guestHistoryAccountPage), driver);
		} catch (Exception e) {
			Wait.waitForElementToBeVisibile(By.xpath(OR.GuestHistoryAccountPage), driver);

		}

	}
	
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <searchAccount> 
	 * ' Description: <This method will return expected deposit amount> 
	 * ' Input parameters: <WebDriver driver, String accountFirstName, String accountLastName> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Farhan Ghaffar>
	 * ' Created On:  <06/16/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String> searchAccount(WebDriver driver, String accountFirstName, String accountLastName
			) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		
		Wait.WaitForElement(driver, OR.guestAccountFirstName);
		Wait.waitForElementToBeVisibile(By.xpath(OR.guestAccountFirstName), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.guestAccountFirstName), driver);
		guest_his.guestAccountFirstName.clear();
		guest_his.guestAccountFirstName.sendKeys(accountFirstName);
		testSteps.add("Entered Guest First Name : " + accountFirstName);
		guestHistoryLogger.info("Entered Guest First Name : " + accountFirstName);

		Wait.WaitForElement(driver, OR.guestAccountLastName);
		Wait.waitForElementToBeVisibile(By.xpath(OR.guestAccountLastName), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.guestAccountLastName), driver);
		guest_his.guestAccountLastName.clear();
		guest_his.guestAccountLastName.sendKeys(accountLastName);
		testSteps.add("Entered Guest Last Name : " + accountLastName);
		guestHistoryLogger.info("Entered Guest Last Name : " + accountLastName);

		Wait.WaitForElement(driver, OR.clickSearchButton);
		Wait.waitForElementToBeVisibile(By.xpath(OR.clickSearchButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.clickSearchButton), driver);
		guest_his.clickSearchButton.click();
		guestHistoryLogger.info("Click Search");
		testSteps.add("Click Search");

		
		Wait.WaitForElement(driver, OR.Number_Of_Accounts);
		Wait.WaitForElement(driver, OR.SearcehdGuestHistoryAccountNumber);
		System.out.println(
				"AccNume expec:" + accountFirstName + "Result:" + guest_his.SearcehdGuestHistoryAccountNumber.getText());
	
		return testSteps;
	}


	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <updateCardNumber> 
	 * ' Description: <This method will update credit card number in guest profile> 
	 * ' Input parameters: <WebDriver driver, String newCCNumber> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Farhan Ghaffar>
	 * ' Created On:  <06/17/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String> updateCardNumber(WebDriver driver, String newCCNumber) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		ArrayList<String> testSteps = new ArrayList<>();

		Wait.WaitForElement(driver, OR.clickDecyptCCNumberImg);
		Wait.waitForElementToBeVisibile(By.xpath(OR.clickDecyptCCNumberImg), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.clickDecyptCCNumberImg), driver);
		Utility.ScrollToElement_NoWait(guest_his.clickDecyptCCNumberImg, driver);
		guest_his.clickDecyptCCNumberImg.click();
		guestHistoryLogger.info("Click On Decrypt Icon");
		testSteps.add("Click On Decrypt Icon");
		
		Wait.WaitForElement(driver, OR.billingAccountNumberInput);
		Wait.waitForElementToBeVisibile(By.xpath(OR.billingAccountNumberInput), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.billingAccountNumberInput), driver);
		guest_his.billingAccountNumberInput.clear();
		guest_his.billingAccountNumberInput.sendKeys(newCCNumber);
		testSteps.add("Entered CreditCard Number : " + newCCNumber);
		guestHistoryLogger.info("Entered CreditCard Number : " + newCCNumber);
	
		return testSteps;
	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <clickSave> 
	 * ' Description: <This method will click on save button in guest profile> 
	 * ' Input parameters: <WebDriver driver> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Farhan Ghaffar>
	 * ' Created On:  <06/17/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String> clickSave(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.guestInfoSaveButton);
		Wait.waitForElementToBeVisibile(By.xpath(OR.guestInfoSaveButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.guestInfoSaveButton), driver);
		guest_his.guestInfoSaveButton.click();
		guestHistoryLogger.info("Click Save");
		testSteps.add("Click Save");
		return testSteps;
	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <getAccountNumber> 
	 * ' Description: <This method will return account number off searched pofile in guest history> 
	 * ' Input parameters: <WebDriver driver> 
	 * ' Return value: <String>
	 * ' Created By: <Farhan Ghaffar>
	 * ' Created On:  <06/17/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public String getAccountNumber(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		Wait.WaitForElement(driver, OR.SearcehdGuestHistoryAccountNumber);
		String accountNumber = guest_his.SearcehdGuestHistoryAccountNumber.getText();
		return accountNumber;
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <openSearchedGuestAccount> 
	 * ' Description: <This method will click searched account to open details in guest history> 
	 * ' Input parameters: <WebDriver driver> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Farhan Ghaffar>
	 * ' Created On:  <06/17/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	
	public ArrayList<String> openSearchedGuestAccount(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.ClickSearchedGuestProfile);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ClickSearchedGuestProfile), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.ClickSearchedGuestProfile), driver);
		guest_his.ClickSearchedGuestProfile.click();
		guestHistoryLogger.info("Click on searched guest profile");
		testSteps.add("Click on searched guest profile");

		return testSteps;
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <openReservtionTabInGuestHistoryAccount> 
	 * ' Description: <This method will click reservation tab in guest profile> 
	 * ' Input parameters: <WebDriver driver> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Farhan Ghaffar>
	 * ' Created On:  <06/17/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String> openReservtionTabInGuestHistoryAccount(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.reservationTabGuestAccount);
		Wait.waitForElementToBeVisibile(By.xpath(OR.reservationTabGuestAccount), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.reservationTabGuestAccount), driver);
		guest_his.reservationTabGuestAccount.click();
		guestHistoryLogger.info("Click on reservation tab");
		testSteps.add("Click on reservation tab");
		
		return testSteps;
		
	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <verifyCardNumber> 
	 * ' Description: <This method will veirfy updated credit card number in guest profile> 
	 * ' Input parameters: <WebDriver driver, String newCCNumber> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Farhan Ghaffar>
	 * ' Created On:  <06/17/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */

	public ArrayList<String> verifyCardNumber(WebDriver driver, String newCCNumber) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		ArrayList<String> testSteps = new ArrayList<>();

		Wait.WaitForElement(driver, OR.clickDecyptCCNumberImg);
		Wait.waitForElementToBeVisibile(By.xpath(OR.clickDecyptCCNumberImg), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.clickDecyptCCNumberImg), driver);
		Utility.ScrollToElement_NoWait(guest_his.clickDecyptCCNumberImg, driver);
		guest_his.clickDecyptCCNumberImg.click();
		guestHistoryLogger.info("Click On Decrypt Icon");
		testSteps.add("Click On Decrypt Icon");
		
		Wait.WaitForElement(driver, OR.billingAccountNumberInput);
		Wait.waitForElementToBeVisibile(By.xpath(OR.billingAccountNumberInput), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.billingAccountNumberInput), driver);
		String expectedcardNumber = "XXXX" + newCCNumber.substring(newCCNumber.length() - 4);
		String guetsCardNumber  = guest_his.billingAccountNumberInput.getAttribute("value");
		
			guestHistoryLogger.info("Expected CreditCard Number : " + expectedcardNumber);
			guestHistoryLogger.info("Found : " + guetsCardNumber);
		
			if (guetsCardNumber.contains("X")) {
				assertEquals(guetsCardNumber, expectedcardNumber, "Failed : Card Number mismatches");
				testSteps.add("Expected CreditCard Number : " + expectedcardNumber);
				testSteps.add("Found : " + guetsCardNumber);

			}
			else{
				assertEquals(guetsCardNumber, newCCNumber, "Failed : Card Number mismatches");
				testSteps.add("Expected CreditCard Number : " + newCCNumber);
				testSteps.add("Found : " + guetsCardNumber);
			}
			
			
		
		testSteps.add("verified card number");
		guestHistoryLogger.info("verified card number");
	
		return testSteps;
	}

	
	//Added By Adhnan 6/22/2020
	public void clickNewReservation(WebDriver driver) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		Wait.WaitForElement(driver, OR.newReservationButton);
		Wait.waitForElementToBeVisibile(By.xpath(OR.newReservationButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.newReservationButton), driver);
		 guest_his.newReservationButton.click();
	}
	
	
	public void enterFirstLastName(WebDriver driver, String FirstName, String LastName,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_GuestHistory guest_his = new Elements_GuestHistory(driver);
		Wait.wait2Second();
		Wait.WaitForElement(driver, OR.GUEST_ACCOUNT_FIRST_NAME);
		Wait.waitForElementToBeVisibile(By.xpath( OR.GUEST_ACCOUNT_FIRST_NAME), driver);
		Wait.waitForElementToBeClickable(By.xpath( OR.GUEST_ACCOUNT_FIRST_NAME), driver);
		guest_his.GUEST_ACCOUNT_FIRST_NAME.sendKeys(FirstName);
		guestHistoryLogger.info("First Name Enterted: " + FirstName);
		test_steps.add("First Name Enterted: " + FirstName);
		guest_his.GUEST_ACCOUNT_LAST_NAME.sendKeys(LastName);
		guestHistoryLogger.info("Last Name Enterted: " + LastName);
		test_steps.add("Last Name Enterted: " + LastName);

	}
	

}
