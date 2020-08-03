package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.innroad.inncenter.interfaces.IAccount;
import com.innroad.inncenter.pages.NewAccount;
import com.innroad.inncenter.pages.NewFolio;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Reservation;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Accounts;
import com.innroad.inncenter.webelements.Elements_DocumentTemplates;
import com.innroad.inncenter.webelements.Elements_FolioLineItemsVoid;
import com.innroad.inncenter.webelements.Elements_Groups;
import com.innroad.inncenter.webelements.Elements_GuestHistory;
import com.innroad.inncenter.webelements.Elements_Reservation;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Account implements IAccount {

	public static Logger accountsLogger = Logger.getLogger("Account");

	public void NewAccountbutton(WebDriver driver, String Accounttype) {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Select_Account_Type);
		Wait.explicit_wait_visibilityof_webelement_150(CreateAccount.Select_Account_Type, driver);
		new Select(CreateAccount.Select_Account_Type).selectByVisibleText(Accounttype);
		CreateAccount.Click_New_Account.click();
		Wait.waitForElementToBeGone(driver, CreateAccount.Account_ModuleLoading, 180);
		// Wait.explicit_wait_xpath(OR.Verify_New_Account_tab);
		// assertTrue(CreateAccount.AccountPage.isDisplayed(), "Account Page
		// didn't display");
	}

	public void ClickNewAccountbutton(WebDriver driver, String Accounttype) {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Select_Account_Type);
		Wait.explicit_wait_visibilityof_webelement_200(CreateAccount.Select_Account_Type, driver);
		new Select(CreateAccount.Select_Account_Type).selectByVisibleText(Accounttype);
		CreateAccount.Click_New_Account.click();
		try {
			Wait.explicit_wait_visibilityof_webelement_600(CreateAccount.Verify_New_Account_Page_Load, driver);
		} catch (Exception e) {
			Utility.app_logs.info("Catch body");
			driver.navigate().refresh();
			new Select(CreateAccount.Select_Account_Type).selectByVisibleText(Accounttype);
			CreateAccount.Click_New_Account.click();
			Wait.explicit_wait_visibilityof_webelement_600(CreateAccount.Verify_New_Account_Page_Load, driver);
		}
	}

	public void AccountDetails(WebDriver driver, String AccountType, String AccountName) throws InterruptedException {
		Wait.wait2Second();
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Verify_Account_Type);
		Wait.explicit_wait_visibilityof_webelement_150(CreateAccount.Verify_Account_Type, driver);
		String selectedOption = new Select(CreateAccount.Verify_Account_Type).getFirstSelectedOption().getText();
		Assert.assertEquals(AccountType, selectedOption);
		CreateAccount.Enter_Account_Name.sendKeys(AccountName);
	}

	public void AccountDetails_RandomNumber(WebDriver driver, String AccountType, String AccountName) {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement_350(CreateAccount.Verify_Account_Type, driver);
		String selectedOption = new Select(CreateAccount.Verify_Account_Type).getFirstSelectedOption().getText();
		System.out.println("A:" + AccountType + "B:" + selectedOption);
		Assert.assertEquals(AccountType, selectedOption);
		CreateAccount.Enter_Account_Name.sendKeys(AccountName);
	}

	public void CGAccountDetails(WebDriver driver, String AccountType, String AccountName) throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement_350(CreateAccount.Select_Account_Type, driver);
		new Select(CreateAccount.Select_Account_Type).selectByVisibleText(AccountType);

		String selectedOption = new Select(CreateAccount.Select_Account_Type).getFirstSelectedOption().getText();

		if (selectedOption != AccountType) {
			Wait.wait2Second();
			new Select(CreateAccount.Select_Account_Type).selectByVisibleText(AccountType);
		}

		assertTrue(
				new Select(CreateAccount.Select_Account_Type).getFirstSelectedOption().getText().equals(AccountType));

		CreateAccount.Click_New_Account.click();

		Wait.explicit_wait_visibilityof_webelement_600(CreateAccount.Verify_New_Account_Page_Load, driver);
		Wait.explicit_wait_visibilityof_webelement_350(CreateAccount.Verify_Account_Type, driver);

		Wait.wait5Second();

		selectedOption = new Select(CreateAccount.Verify_Account_Type).getFirstSelectedOption().getText();

		if (selectedOption != AccountType) {
			Wait.wait2Second();
			new Select(CreateAccount.Verify_Account_Type).selectByVisibleText(AccountType);
		}

		Assert.assertEquals(AccountType, selectedOption);

		CreateAccount.Enter_Account_Name.sendKeys(AccountName);
	}

	public void CGAccountDetails1(WebDriver driver, String AccountType, String AccountName)
			throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Select_Account_Type);
		new Select(CreateAccount.Select_Account_Type).selectByVisibleText(AccountType);
		CreateAccount.Click_New_Account.click();
		try {
			// Wait.explicit_wait_visibilityof_webelement_350(CreateAccount.Verify_New_Account_tab);
			Wait.explicit_wait_visibilityof_webelement_350(CreateAccount.Enter_Account_Name, driver);
			CreateAccount.Enter_Account_Name.sendKeys(AccountName);
		} catch (Exception e) {
			Utility.app_logs.info("Catch body");
			driver.navigate().refresh();
			Wait.explicit_wait_visibilityof_webelement(CreateAccount.Click_New_Account, driver);
			CreateAccount.Click_New_Account.click();
			// Wait.explicit_wait_visibilityof_webelement_350(CreateAccount.Verify_New_Account_tab);
			Wait.explicit_wait_visibilityof_webelement_350(CreateAccount.Enter_Account_Name, driver);
			CreateAccount.Enter_Account_Name.sendKeys(AccountName);
		}
	}

	public void AccountAttributes(WebDriver driver, String MargetSegment, String Referral) {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		new Select(CreateAccount.Select_Market_Segment).selectByVisibleText(MargetSegment);
		new Select(CreateAccount.Select_Referrals).selectByVisibleText(Referral);

	}

	public void Mailinginfo(WebDriver driver, String AccountFirstName, String AccountLastName, String Phonenumber,
			String alternativeNumber, String Address1, String Address2, String Address3, String Email, String city,
			String State, String Postalcode) {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		CreateAccount.Account_Enter_First_Name.sendKeys(AccountFirstName);
		CreateAccount.Account_Enter_Last_Name.sendKeys(AccountLastName);
		CreateAccount.Account_Phone_number.sendKeys(Phonenumber);
		CreateAccount.Account_Enter_AltPhoneNumber.sendKeys(alternativeNumber);
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
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Wait.wait5Second();
		WebElement checkBoxMailingInfo = CreateAccount.Account_Check_Mailing_info;
		jse.executeScript("arguments[0].scrollIntoView(true);", checkBoxMailingInfo);
		Wait.wait2Second();
		if (checkBoxMailingInfo.isSelected()) {
			accountsLogger.info("Check box already checked");
		} else {

			checkBoxMailingInfo.click();
		}

	}

	public void Save(WebDriver driver) throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Utility.ScrollToElement(CreateAccount.Account_save, driver);
		if (CreateAccount.Account_save.isEnabled()) {
			CreateAccount.Account_save.click();
			try {
				Wait.explicit_wait_visibilityof_webelement_120(CreateAccount.Verify_Toaster_Container, driver);
				if (CreateAccount.Verify_Toaster_Container.isDisplayed()) {
					String getTotasterTitle_ReservationSucess = CreateAccount.Toaster_Title.getText();
					String getToastermessage_ReservationSucess = CreateAccount.Toaster_Message.getText();
					Assert.assertEquals(getTotasterTitle_ReservationSucess, "Account Saved");
					if (getToastermessage_ReservationSucess
							.equals("The account Test Account has been successfully created."))
						;
				}
			} catch (Exception e) {
				System.out.println("Toaster not Available");
			}
		}
	}

	public void SaveandClose(WebDriver driver) throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		CreateAccount.Account_Save_Clsoe.click();
		try {
			String loadingPath = "//*[@id='bpjscontainer_21']/div/div[1]/div[2]/div[9]/div[2]/div/div[@class='modules_loading']";
			WebElement loading = driver.findElement(By.xpath(loadingPath));
			if (loading.isDisplayed()) {

				Wait.explicit_wait_visibilityof_webelement_600(CreateAccount.Account_Search, driver);
			}
		} catch (Exception e) {
			System.out.println("No Loading...");
		}
		try {
			Wait.explicit_wait_visibilityof_webelement(CreateAccount.Verify_Toaster_Container, driver);
			if (CreateAccount.Verify_Toaster_Container.isDisplayed()) {
				String getTotasterTitle_ReservationSucess = CreateAccount.Toaster_Title.getText();
				String getToastermessage_ReservationSucess = CreateAccount.Toaster_Message.getText();
				Assert.assertEquals(getTotasterTitle_ReservationSucess, "Account Saved");
				if (getToastermessage_ReservationSucess
						.equals("The account Test Account has been successfully created."))
					;
			}
		} catch (Exception e) {
			System.out.println("Toaster Not Displayed");
			driver.navigate().refresh();
		}

		try {
			Wait.wait1Second();
			if (ReservationPage.AlertForTab.isDisplayed()) {

				ReservationPage.AlertForTab_Yes_Btn.click();
			}
			Wait.wait1Second();
		} catch (Exception e) {
			System.out.println("Tab Alert Not Displayed");
		}

	}

	public void addLineitem1(WebDriver driver, String propertyName, String SelectCategory, String Amount,
			ArrayList test_steps) throws InterruptedException {

		Elements_Accounts CreateAccountlineitem = new Elements_Accounts(driver);
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(CreateAccountlineitem.Account_Add, driver);
		Utility.ScrollToElement(CreateAccountlineitem.Account_Add, driver);
		Wait.WaitForElement(driver, OR.Account_Add);
		CreateAccountlineitem.Account_Add.click();
		Wait.explicit_wait_xpath(driver, OR.Verify_Account_line_item);
		new Select(CreateAccountlineitem.Select_Property_lineitem).selectByVisibleText(propertyName);
		new Select(CreateAccountlineitem.Select_Line_item_Category).selectByVisibleText(SelectCategory);
		try {
			String GetID = CreateAccountlineitem.Get_Gift_ID.getAttribute("value");
			accountsLogger.info("GetID :" + GetID);
			String GetSplitid[] = GetID.split("#");
			accountsLogger.info("Gift Certificate Number:" + GetSplitid[1]);
			test_steps.add(GetID);
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(".\\GiftCertificate.txt"));
				writer.write(GetSplitid[1].trim());
				writer.close();
			} catch (Exception e) {

			}
		} catch (Exception e) {

		}
		try {

			CreateAccountlineitem.Enter_Gift_Line_Item_Amount.clear();
			Wait.wait2Second();
			CreateAccountlineitem.Enter_Gift_Line_Item_Amount.sendKeys(Amount);
		} catch (Exception e) {
			CreateAccountlineitem.Enter_Line_item_Amount.clear();
			Wait.wait2Second();
			CreateAccountlineitem.Enter_Line_item_Amount.sendKeys(Amount);
		}

	}

	public void addLineitem(WebDriver driver, String propertyName, String SelectCategory, String Amount)
			throws InterruptedException {

		Elements_Accounts CreateAccountlineitem = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement(CreateAccountlineitem.Account_Add, driver);
		Utility.ScrollToElement(CreateAccountlineitem.Account_Add, driver);
		CreateAccountlineitem.Account_Add.click();
		Wait.explicit_wait_xpath(OR.Verify_Account_line_item, driver);
		// new
		// Select(CreateAccountlineitem.Select_Property_lineitem).selectByVisibleText(propertyName);
		Wait.wait2Second();
		new Select(CreateAccountlineitem.Select_Line_item_Category).selectByVisibleText(SelectCategory);

		try {

			CreateAccountlineitem.Enter_Gift_Line_Item_Amount.clear();
			Wait.wait2Second();
			CreateAccountlineitem.Enter_Gift_Line_Item_Amount.sendKeys(Amount);
		} catch (Exception e) {
			CreateAccountlineitem.Enter_Line_item_Amount.clear();
			Wait.wait2Second();
			CreateAccountlineitem.Enter_Line_item_Amount.sendKeys(Amount);

		}

	}

	public void addLineitem(WebDriver driver, String propertyName, String Amount) throws InterruptedException {

		Elements_Accounts CreateAccountlineitem = new Elements_Accounts(driver);

		CreateAccountlineitem.Account_Add.click();
		Wait.explicit_wait_xpath(OR.Verify_Account_line_item, driver);
		// new
		new Select(CreateAccountlineitem.Select_Property_lineitem).selectByIndex(1);

		new Select(CreateAccountlineitem.Select_Line_item_Category).selectByVisibleText(propertyName);

		try {

			CreateAccountlineitem.Enter_Gift_Line_Item_Amount.clear();
			Wait.wait2Second();
			CreateAccountlineitem.Enter_Gift_Line_Item_Amount.sendKeys(Amount);
		} catch (Exception e) {
			CreateAccountlineitem.Enter_Line_item_Amount.clear();
			Wait.wait2Second();
			CreateAccountlineitem.Enter_Line_item_Amount.sendKeys(Amount);

		}

	}

	public void Commit(WebDriver driver) {
		Elements_Accounts CreateAccountlineitem = new Elements_Accounts(driver);
		CreateAccountlineitem.Click_Commit.click();
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 *
	 * ' Method Name: <navigateFolio> ' Description: <This method will navigate
	 * to folio tab of account> ' Input parameters:(WebDriver) ' Return value:
	 * void ' Updated By: <Adhnan Ghaffar> ' Updated On: <06/02/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void navigateFolio(WebDriver driver) throws InterruptedException {
		Elements_Accounts ClickFolio = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Account_Folio);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Account_Folio), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Account_Folio), driver);
		Utility.ScrollToElement_NoWait(ClickFolio.Account_Folio, driver);
		ClickFolio.Account_Folio.click();
		try {
			Wait.WaitForElement(driver, OR.Account_Folio_FolioOptions);
			// Wait.explicit_wait_visibilityof_webelement(ClickFolio.Account_Folio_FolioOptions,
			// driver);
		} catch (Exception e) {
			ClickFolio.Account_Folio.click();
			Wait.WaitForElement(driver, OR.Account_Folio_FolioOptions);
			// Wait.explicit_wait_visibilityof_webelement(ClickFolio.Account_Folio_FolioOptions,
			// driver);

		}
	}

	public void navigateFolioOptions(WebDriver driver) throws InterruptedException {
		Elements_Accounts ClickFolio = new Elements_Accounts(driver);
		// Wait.explicit_wait_visibilityof_webelement_120(ClickFolio.Account_Folio);
		Wait.wait2Second();
		// Wait.WaitForElement(driver, OR.Account_Folio_FolioOptions);
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", ClickFolio.Account_Folio_FolioOptions);

		} catch (Exception e) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", ClickFolio.Account_Folio_FolioOptions1);

		}
	}

	public void select_ChargeRouting(WebDriver driver, String routing) {
		Elements_Accounts ClickFolio = new Elements_Accounts(driver);
		// Wait.explicit_wait_visibilityof_webelement_120(ClickFolio.Account_Folio);
		Wait.WaitForElement(driver, OR.Account_Folio_FolioOptions_MoveToAccountFolio);
		new Select(ClickFolio.Account_Folio_FolioOptions_MoveToAccountFolio).selectByVisibleText(routing);

	}

	public void Payment(WebDriver driver, String PaymentType, String CardName, String CCNumber, String CCExpiry,
			String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue)
			throws InterruptedException {
		Elements_Accounts AccountPayment = new Elements_Accounts(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		Utility.ScrollToElement(AccountPayment.Account_pay, driver);
		AccountPayment.Account_pay.click();
		Wait.wait2Second();
		new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
		// Wait.wait3Second();
		AccountPayment.Click_Account_Card_info.click();
		Wait.explicit_wait_xpath(OR.Verify_Account_Paymnet_info_popup, driver);
		AccountPayment.Enter_Account_Card_Name.sendKeys(CardName);
		AccountPayment.Enter_CC_Account_Number.sendKeys(CCNumber);
		AccountPayment.Enter_ExpiryDate_Account.sendKeys(CCExpiry);
		AccountPayment.Enter_CCV_Account.sendKeys(CCVCode);
		AccountPayment.Enter_Acc_ApptovalCode.sendKeys(Authorizationtype);
		AccountPayment.Click_Ok_Account.click();
		Wait.waitForElementToBeGone(driver, AccountPayment.AccountPaymentModule, 30);
		Wait.explicit_wait_visibilityof_webelement(AccountPayment.Select_Authorization_Type_Account, driver);

		new Select(AccountPayment.Select_Authorization_Type_Account).selectByVisibleText(Authorizationtype);
		if (ChangeAmount.equalsIgnoreCase("Yes")) {
			AccountPayment.Enter_Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
		} else {
			accountsLogger.info("Processed complete amount");
		}
		Wait.wait3Second();
		if (Authorizationtype.equalsIgnoreCase("Capture")) {
			Utility.app_logs.info("Authorization Type is Capture");
			// Thread.sleep(1000*20);
			// Wait.waitForElementToBeGone(driver, element, timeout);
			Utility.ScrollToElement(AccountPayment.Click_Process_Account, driver);
			try {

				AccountPayment.Click_Process_Account.click();
			} catch (Exception e) {
				jse.executeScript("arguments[0].click();", AccountPayment.Click_Process_Account);

			}
			Thread.sleep(1000 * 10);
			Wait.explicit_wait_visibilityof_webelement(AccountPayment.Apply_Advance_Deposite, driver);
			// Wait.explicit_wait_xpath(OR.Apply_Advance_Deposite);
			AccountPayment.Click_Continue_Adv_Deposite.click();
		}
		Wait.wait3Second();
		Wait.explicit_wait_xpath(OR.Verify_MC_Grid, driver);
		String GetPaymentMethod = AccountPayment.Account_Payment_Method.getText();
		accountsLogger.info(GetPaymentMethod + " " + GetPaymentMethod);
		if (GetPaymentMethod.equals(PaymentType)) {
			accountsLogger.info("Paymnet Success");
		} else {
			accountsLogger.info("Paymnet Failed");
		}
		AccountPayment.Click_Account_Pay_Continue.click();
		Wait.explicit_wait_visibilityof_webelement(AccountPayment.Get_Line_item_Account, driver);

		Wait.explicit_wait_xpath(OR.Get_Line_item_Account, driver);
		String GetMCCard = AccountPayment.Get_Line_item_Account.getText();
		accountsLogger.info(GetMCCard + " " + GetMCCard);
		if (GetMCCard.equals("Name: MC Account #: XXXX5454 Exp. Date: 08/19")) {
			accountsLogger.info("Paymnet Success");
		} else {
			accountsLogger.info("Paymnet Failed");
		}
		String GetBalance = AccountPayment.Verify_Ending_Balance.getText();
		accountsLogger.info(GetBalance + " " + GetBalance);
		String RemoveCurreny[] = GetBalance.split(" ");
		accountsLogger.info(RemoveCurreny[1]);
		if (ChangeAmount.equalsIgnoreCase("No")) {
			if (RemoveCurreny[1].equals("0.00"))
				;
		} else {
			accountsLogger.info("Selected Changed Value");
		}

	}

	public void CreateNewReservation(WebDriver driver) {
		Elements_Accounts AccountPayment = new Elements_Accounts(driver);
		AccountPayment.Click_New_Reservation_Account.click();
		Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
	}

	public void CreateHouseAccount(WebDriver driver, String HouseAccountName) {
		Elements_Accounts AccountPayment = new Elements_Accounts(driver);
		AccountPayment.Enter_House_Account_Name.sendKeys(HouseAccountName);

	}

	public void GiftCertificate(WebDriver driver, String GiftCertificateName) {
		Elements_Accounts AccountPayment = new Elements_Accounts(driver);
		AccountPayment.Enter_House_Account_Name.sendKeys(GiftCertificateName);

	}

	public void AddNoteButtonClick(WebDriver driver) throws InterruptedException {

		Elements_Accounts AddNote = new Elements_Accounts(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", AddNote.Acc_Add_Button);
		AddNote.Acc_Add_Button.click();
		Wait.WaitForElement(driver, OR.Verify_Notes_Popup);
		assertTrue(AddNote.Verify_Notes_Popup.isDisplayed(), "Notes Popup didn't display");
	}

	public void AccAddNotes(WebDriver driver, String Subject, String NoteDetails, String NoteType, String Notestatus)
			throws InterruptedException {

		Elements_Accounts AddNote = new Elements_Accounts(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", AddNote.Acc_Add_Button);

		AddNote.Acc_Add_Button.click();
		Wait.WaitForElement(driver, OR.Verify_Notes_Popup);
		Wait.WaitForElement(driver, OR.Acc_Note_Enter_sub);
		// Wait.explicit_wait_xpath(OR.Verify_Notes_Popup, driver);
		AddNote.Acc_Note_Enter_sub.sendKeys(Subject);
		AddNote.Acc_Note_Enter_Details.sendKeys(NoteDetails);
		new Select(AddNote.Acc_Note_Select_Note_Type).selectByVisibleText(NoteType);

		if (AddNote.Acc_Note_Action_Req.isSelected()) {
			accountsLogger.info("Already selected");
		} else {
			AddNote.Acc_Note_Action_Req.click();
			Wait.wait10Second();
		}

		new Select(AddNote.Acc_Note_Select_Note_Status).selectByVisibleText(Notestatus);

		AddNote.Acc_Note_Save.click();
		String NotesSubject = AddNote.AddNotes_NotesSubject.getText();
		String Notestype = AddNote.AddNotes_NotesType.getText();
		System.out.println("Subject:" + Subject + "Type:" + NoteType);

		System.out.println("Subject:" + NotesSubject + "Type:" + Notestype);
		assertTrue(AddNote.Verify_Add_line_Notes.isDisplayed(), "AccountNotes didn't added successfull");
		assertTrue(NotesSubject.equals(Subject), "AccountNotes Subject didn't match");
		assertTrue(Notestype.equals(NoteType), "AccountNotes Type didn't match");

	}

	public void Cp_AccAddNotes(WebDriver driver, String Subject, String NoteDetails, String NoteType)
			throws InterruptedException {

		Elements_Accounts AddNote = new Elements_Accounts(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", AddNote.Acc_Add_Button);

		AddNote.Acc_Add_Button.click();
		Wait.WaitForElement(driver, OR.Verify_Notes_Popup);
		Wait.WaitForElement(driver, OR.Acc_Note_Enter_sub);
		// Wait.explicit_wait_xpath(OR.Verify_Notes_Popup, driver);
		AddNote.Acc_Note_Enter_sub.sendKeys(Subject);
		AddNote.Acc_Note_Enter_Details.sendKeys(NoteDetails);
		new Select(AddNote.Acc_Note_Select_Note_Type).selectByVisibleText(NoteType);

		/*
		 * if (AddNote.Acc_Note_Action_Req.isSelected()) {
		 * accountsLogger.info("Already selected"); } else {
		 * AddNote.Acc_Note_Action_Req.click(); Wait.wait10Second(); }
		 */

		AddNote.Acc_Note_Save.click();
		Wait.wait5Second();
		String NotesSubject = AddNote.AddNotes_NotesSubject.getText();
		String Notestype = AddNote.AddNotes_NotesType.getText();
		System.out.println("Subject:" + Subject + "Type:" + NoteType);

		System.out.println("Subject:" + NotesSubject + "Type:" + Notestype);
		// assertTrue(AddNote.Verify_Add_line_Notes.isDisplayed(), "AccountNotes
		// didn't added successfull");
		assertTrue(NotesSubject.equals(Subject), "AccountNotes Subject didn't match");
		assertTrue(Notestype.equals(NoteType), "AccountNotes Type didn't match");

	}

	public void AccAddNotes1(WebDriver driver, String Subject, String NoteDetails, String NoteType, String Notestatus)
			throws InterruptedException {

		Elements_Accounts AddNote = new Elements_Accounts(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", AddNote.Acc_Add_Button);

		AddNote.Acc_Add_Button.click();
		Wait.explicit_wait_xpath(OR.Verify_Notes_Popup, driver);
		AddNote.Acc_Note_Enter_sub.sendKeys(Subject);
		AddNote.Acc_Note_Enter_Details.sendKeys(NoteDetails);
		new Select(AddNote.Acc_Note_Select_Note_Type).selectByVisibleText(NoteType);

		AddNote.Acc_Note_Save.click();
		assertTrue(AddNote.Verify_Add_line_Notes.isDisplayed(), " Notes didn't add");

	}

	public void get_AccountDetails(WebDriver driver, ExtentTest test, String AccountType, String AccountName)
			throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);
		Wait.explicit_wait_xpath(OR.Account_Type, driver);

		Select sel = new Select(Account.Account_Type);
		sel.selectByVisibleText(AccountType);
		test.log(LogStatus.PASS, "Account type : " + AccountType);
		accountsLogger.info("Account type : " + AccountType);

		Account.Account_Name.sendKeys(AccountName);
		test.log(LogStatus.PASS, "Account Name : " + AccountName);
		accountsLogger.info("Account Name : " + AccountName);

		Account.Account_Search.click();
		test.log(LogStatus.PASS, "Click Account Search");
		accountsLogger.info("Click Account Search");

		Wait.wait5Second();

		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(OR.Number_Of_Accounts))));

		int count = driver.findElements(By.xpath(OR.Number_Of_Accounts)).size();

		if (count == 0) {
			test.log(LogStatus.PASS, "There are No Accounts available with " + AccountName);
			accountsLogger.info("There are No Accounts available with " + AccountName);
		} else {
			test.log(LogStatus.PASS, "Number of Accounts available with " + AccountName + " in this page are " + count);
			accountsLogger.info("Number of Accounts available with " + AccountName + "  in this page are " + count);
		}
	}

	public ArrayList<String> type_CorpAccName(WebDriver driver, ExtentTest test1, String AccountName,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		WebDriverWait wait = new WebDriverWait(driver, 90);
		// Wait.wait2Second();
		Wait.WaitForElement(driver, OR.Account_CorpAccountName);
		wait.until(ExpectedConditions.visibilityOf(CreateAccount.Account_CorpAccountName));
		CreateAccount.Account_CorpAccountName.sendKeys(AccountName);
		test_steps.add("Enter Account Name : " + AccountName);
		accountsLogger.info("Enter Account Name : " + AccountName);
		return test_steps;
	}

	public ArrayList<String> AccountAttributes(WebDriver driver, ExtentTest test1, String MargetSegment,
			String Referral, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		WebDriverWait wait = new WebDriverWait(driver, 90);
		Wait.WaitForElement(driver, OR.Select_Market_Segment);
		wait.until(ExpectedConditions.visibilityOf(CreateAccount.Select_Market_Segment));
		new Select(CreateAccount.Select_Market_Segment).selectByVisibleText(MargetSegment);
		test_steps.add("Select Market Segment : " + MargetSegment);
		accountsLogger.info("Select Market Segment : " + MargetSegment);
		new Select(CreateAccount.Select_Referrals).selectByVisibleText(Referral);
		test_steps.add("Select Referral : " + Referral);
		accountsLogger.info("Select Referral : " + Referral);
		return test_steps;
	}

	public String getAccountNumber(WebDriver driver, ExtentTest test1, String AccountName) throws InterruptedException {

		String acc = driver.findElement(By.xpath("(//input[@placeholder='Account Number'])[2]")).getAttribute("value");
		accountsLogger.info("Account Number : " + acc);
		return acc;

	}

	public ArrayList<String> Mailinginfo(WebDriver driver, ExtentTest test1, String AccountFirstName,
			String AccountLastName, String Phonenumber, String alternativeNumber, String Address1, String Address2,
			String Address3, String Email, String city, String State, String Postalcode, ArrayList<String> test_steps) {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement_120(CreateAccount.Account_Enter_First_Name, driver);
		CreateAccount.Account_Enter_First_Name.sendKeys(AccountFirstName);
		test_steps.add("Enter Account First Name : " + AccountFirstName);
		accountsLogger.info("Enter Account First Name : " + AccountFirstName);

		CreateAccount.Account_Enter_Last_Name.sendKeys(AccountLastName);
		test_steps.add("Enter Account Last Name : " + AccountLastName);
		accountsLogger.info("Enter Account Last Name : " + AccountLastName);

		CreateAccount.Account_Phone_number.sendKeys(Phonenumber);
		test_steps.add("Enter Account Phone Number : " + Phonenumber);
		accountsLogger.info("Enter Account Phone Number : " + Phonenumber);

		CreateAccount.Account_Enter_AltPhoneNumber.sendKeys(alternativeNumber);
		test_steps.add("Enter Account Alternate Number : " + alternativeNumber);
		accountsLogger.info("Enter Account Alternate Number : " + alternativeNumber);

		CreateAccount.Account_Enter_Line1.sendKeys(Address1);
		test_steps.add("Enter Account Address1 : " + Address1);
		accountsLogger.info("Enter Account Address1 : " + Address1);

		CreateAccount.Account_Enter_Line2.sendKeys(Address2);
		test_steps.add("Enter Account Address2 : " + Address2);
		accountsLogger.info("Enter Account Address2 : " + Address2);

		CreateAccount.Account_Enter_Line3.sendKeys(Address3);
		test_steps.add("Enter Account Address3 : " + Address3);
		accountsLogger.info("Enter Account Address3 : " + Address3);

		CreateAccount.Account_Enter_Email.sendKeys(Email);
		test_steps.add("Enter Account Email : " + Email);
		accountsLogger.info("Enter Account Email : " + Email);

		CreateAccount.Account_Enter_City.sendKeys(city);
		test_steps.add("Enter Account City : " + city);
		accountsLogger.info("Enter Account City : " + city);

		new Select(CreateAccount.Select_Account_State).selectByVisibleText(State);
		test_steps.add("Select Account state : " + State);
		accountsLogger.info("Select Account state : " + State);

		CreateAccount.Account_Enter_PostalCode.sendKeys(Postalcode);
		test_steps.add("Enter Account Postal code : " + Postalcode);
		accountsLogger.info("Enter Account Postal code : " + Postalcode);
		return test_steps;
	}

	public ArrayList<String> Billinginfo(WebDriver driver, ExtentTest test1, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		WebDriverWait wait = new WebDriverWait(driver, 90);
		Wait.WaitForElement(driver, OR.Account_Check_Mailing_info);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", CreateAccount.Account_Check_Mailing_info);
		wait.until(ExpectedConditions.visibilityOf(CreateAccount.Account_Check_Mailing_info));
		if (CreateAccount.Account_Check_Mailing_info.isSelected()) {
			// System.out.println("Check box already checked");
		} else {

			CreateAccount.Account_Check_Mailing_info.click();
			test_steps.add("Click same as mailing address");
			accountsLogger.info("Click same as mailing address");
		}
		return test_steps;
	}

	public ArrayList<String> get_AccountDetails(WebDriver driver, ExtentTest test1, String AccountType,
			String AccountName, String AccountNumber, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);
		Wait.explicit_wait_xpath(OR.Account_Type, driver);

		Wait.WaitForElement(driver, OR.Account_Type);

		Select sel = new Select(Account.Account_Type);
		sel.selectByVisibleText(AccountType);
		test_steps.add("Account type : " + AccountType);
		accountsLogger.info("Account type : " + AccountType);

		try {
			Account.Account_Name.sendKeys(AccountName);
		} catch (Exception e) {

			Account.Account_Name_1.sendKeys(AccountName);
		}
		test_steps.add("Account Name : " + AccountName);
		accountsLogger.info("Account Name : " + AccountName);

		try {
			driver.findElement(By.xpath(OR.Search_AccountNumber + "[2]")).sendKeys(AccountNumber);
		} catch (Exception e) {
			Account.Search_AccountNumber.sendKeys(AccountNumber);
		}
		test_steps.add("Account Number : " + AccountNumber);
		accountsLogger.info("Account Number : " + AccountNumber);

		Account.Account_Search.click();
		test_steps.add("Click Account Search");
		accountsLogger.info("Click Account Search");

		Wait.WaitForElement(driver, OR.Number_Of_Accounts);
		int count = driver.findElements(By.xpath(OR.Number_Of_Accounts)).size();

		if (count == 0) {
			test_steps.add(
					"There are No Accounts available with " + AccountName + " and Account Number " + AccountNumber);
			accountsLogger.info(
					"There are No Accounts available with " + AccountName + " and Account Number " + AccountNumber);
		} else {
			test_steps.add("Number of Accounts available with " + AccountName + " and Account Number " + AccountNumber
					+ " in this page are " + count);
			accountsLogger.info("Number of Accounts available with " + AccountName + " and Account Number "
					+ AccountNumber + " in this page are " + count);
		}
		return test_steps;
	}

	public ArrayList<String> VerifyDeleteAccount(WebDriver driver, String AccountType, String AccountName,
			String AccountNumber, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);
		try {
			Wait.explicit_wait_xpath(OR.AccountType, driver);
			Wait.WaitForElement(driver, OR.AccountType);
			Wait.explicit_wait_visibilityof_webelement_150(Account.AccountType, driver);
			Select sel = new Select(Account.AccountType);
			sel.selectByVisibleText(AccountType);
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.Account_Type, driver);
			Wait.WaitForElement(driver, OR.Account_Type);
			Wait.explicit_wait_visibilityof_webelement_150(Account.Account_Type, driver);
			Select sel = new Select(Account.Account_Type);
			sel.selectByVisibleText(AccountType);
		}

		test_steps.add("Account type : " + AccountType);
		accountsLogger.info("Account type : " + AccountType);

		Account.SearchAccountName.clear();
		Account.SearchAccountName.sendKeys(AccountName);

		test_steps.add("Account Name : " + AccountName);
		accountsLogger.info("Account Name : " + AccountName);
		Account.SearchAccountNumber.clear();
		Account.SearchAccountNumber.sendKeys(AccountNumber);

		test_steps.add("Account Number : " + AccountNumber);
		accountsLogger.info("Account Number : " + AccountNumber);

		Account.Account_Search.click();
		test_steps.add("Click Account Search");
		accountsLogger.info("Click Account Search");
		Wait.waitUntilPresenceOfElementLocatedByClassName(OR.Toaster_Title, driver);
		String Toast_Message = Account.Toaster_Title.getText();
		assertEquals(Toast_Message, "No Accounts Exist");
		try {
			if (Account.Toaster_Title.isDisplayed()) {
				Wait.waitForElementToBeGone(driver, Account.Toaster_Title, 10);
			} else {
				Utility.app_logs.info("Toaster disappears");
			}
		} catch (Exception e) {

		}
		test_steps.add(Toast_Message);
		accountsLogger.info(Toast_Message);

		return test_steps;

	}

	public ArrayList<String> SearchAccount(WebDriver driver, ExtentTest test1, String AccountType, String AccountName,
			String AccountNumber, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);
		try {
			Wait.explicit_wait_visibilityof_webelement_120(Account.Account_Type, driver);
			Select sel = new Select(Account.Account_Type);
			sel.selectByVisibleText(AccountType);
		} catch (Exception e) {
			Utility.app_logs.info("In CAtch");
			Wait.explicit_wait_visibilityof_webelement_120(Account.AccountType, driver);
			Select sel = new Select(Account.AccountType);
			sel.selectByVisibleText(AccountType);
		}
		test_steps.add("Account type : " + AccountType);
		accountsLogger.info("Account type : " + AccountType);

		Account.SearchAccountName.sendKeys(AccountName);

		test_steps.add("Account Name : " + AccountName);
		accountsLogger.info("Account Name : " + AccountName);

		Account.SearchAccountNumber.sendKeys(AccountNumber);

		test_steps.add("Account Number : " + AccountNumber);
		accountsLogger.info("Account Number : " + AccountNumber);

		Account.Account_Search.click();
		test_steps.add("Click Account Search");
		accountsLogger.info("Click Account Search");

		Wait.WaitForElement(driver, OR.Number_Of_Accounts);
		int count = driver.findElements(By.xpath(OR.Number_Of_Accounts)).size();

		if (count == 0) {
			test_steps.add(
					"There are No Accounts available with " + AccountName + " and Account Number " + AccountNumber);
			accountsLogger.info(
					"There are No Accounts available with " + AccountName + " and Account Number " + AccountNumber);
		} else {
			test_steps.add("Number of Accounts available with " + AccountName + " and Account Number " + AccountNumber
					+ " in this page are " + count);
			accountsLogger.info("Number of Accounts available with " + AccountName + " and Account Number "
					+ AccountNumber + " in this page are " + count);
		}
		// assertTrue(Account.Number_Of_Accounts.isDisplayed(), "Account didn't
		// search");
		return test_steps;

	}

	public ArrayList<String> addLineItems(WebDriver driver, ExtentTest test1, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts CreateAccountlineitem = new Elements_Accounts(driver);

		Wait.WaitForElement(driver, OR.Account_Add);
		Utility.ScrollToElement(CreateAccountlineitem.Account_Add, driver);
		CreateAccountlineitem.Account_Add.click();

		Wait.WaitForElement(driver, OR.Select_Property_lineitem);
		new Select(CreateAccountlineitem.Select_Property_lineitem).selectByIndex(1);
		// Wait.wait5Second();
		Wait.WaitForElement(driver, OR.Select_Line_item_Category);
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("arguments[0].scrollIntoView();", CreateAccountlineitem.Select_Line_item_Category);

		new Select(CreateAccountlineitem.Select_Line_item_Category).selectByIndex(1);

		String Cat1 = new Select(CreateAccountlineitem.Select_Line_item_Category).getFirstSelectedOption().getText();
		test_steps.add("Selected category : " + Cat1);
		accountsLogger.info("Selected category : " + Cat1);

		CreateAccountlineitem.Enter_Line_item_Amount.clear();
		// Wait.wait2Second();
		Wait.WaitForElement(driver, OR.Enter_Line_item_Amount);
		CreateAccountlineitem.Enter_Line_item_Amount.sendKeys("100");
		test_steps.add("Enter amount 100");
		accountsLogger.info("Enter amount 100");
		Commit(driver, test1);
		CreateAccountlineitem.Account_Add.click();

		Wait.WaitForElement(driver, OR.Select_Property_lineitem);
		new Select(CreateAccountlineitem.Select_Property_lineitem).selectByIndex(1);
		// Wait.wait2Second();
		Wait.WaitForElement(driver, OR.Select_Line_item_Category);
		new Select(CreateAccountlineitem.Select_Line_item_Category).selectByIndex(2);

		String Cat2 = new Select(CreateAccountlineitem.Select_Line_item_Category).getFirstSelectedOption().getText();
		test_steps.add("Selected category : " + Cat2);
		accountsLogger.info("Selected category : " + Cat2);

		Wait.WaitForElement(driver, OR.Enter_Line_item_Amount);
		CreateAccountlineitem.Enter_Line_item_Amount.clear();
		// Wait.wait2Second();
		Wait.WaitForElement(driver, OR.Enter_Line_item_Amount);
		CreateAccountlineitem.Enter_Line_item_Amount.sendKeys("150");
		test_steps.add("Enter amount 150");
		accountsLogger.info("Enter amount 150");
		Commit(driver, test1);
		Save(driver, test1, test_steps);
		return test_steps;

	}

	public String addLineItems_VerifyIt(WebDriver driver, ExtentTest test, String SelectCategory, String Amount,
			int CategoryNumber) throws InterruptedException {

		Elements_Accounts CreateAccountlineitem = new Elements_Accounts(driver);

		Reservation res = new Reservation();
		float EndingBalance_Before = GetEndingBalance(driver);

		try {
			Wait.WaitForElement(driver, OR.Account_Add);
			Utility.ScrollToElement(CreateAccountlineitem.Account_Add, driver);
			CreateAccountlineitem.Account_Add.click();
		} catch (Exception e) {
			// reservation.analyzeLog(driver, test_steps);
		}

		Wait.WaitForElement(driver, OR.Select_Property_lineitem);
		new Select(CreateAccountlineitem.Select_Property_lineitem).selectByIndex(1);
		// Wait.wait5Second();
		Wait.WaitForElement(driver, OR.Select_Line_item_Category);
		try {
			new Select(CreateAccountlineitem.Select_Line_item_Category).selectByVisibleText(SelectCategory);
			String Cat1 = new Select(CreateAccountlineitem.Select_Line_item_Category).getFirstSelectedOption()
					.getText();
			assertEquals(SelectCategory, Cat1, "Failed Category is not Selected");
		} catch (Exception e) {
			Utility.app_logs.info("Category: " + SelectCategory + " not Found");
			new Select(CreateAccountlineitem.Select_Line_item_Category).selectByIndex(CategoryNumber);
		}
		String Cat1 = new Select(CreateAccountlineitem.Select_Line_item_Category).getFirstSelectedOption().getText();

		accountsLogger.info("Selected category : " + Cat1);

		CreateAccountlineitem.Enter_Line_item_Amount.clear();
		// Wait.wait2Second();
		Wait.WaitForElement(driver, OR.Enter_Line_item_Amount);
		CreateAccountlineitem.Enter_Line_item_Amount.sendKeys(Amount);
		accountsLogger.info("Enter amount " + Amount);
		Commit(driver, test);
		accountsLogger.info("Click Commit");
		ArrayList<String> test_steps = new ArrayList<>();
		Save(driver, test, test_steps);

		String LineItem_AmountPath = "//td[contains(@data-bind,'lineitem')]/span[text()='" + Cat1
				+ "']//ancestor::tr//td[@class='lineitem-amount']/span";

		WebElement LineItem_Amount = driver.findElement(By.xpath(LineItem_AmountPath));
		float LineItemAmount = Float.parseFloat(LineItem_Amount.getText().split(" ")[1]);

		float EndingBalance_After = GetEndingBalance(driver);

		assertEquals(EndingBalance_After, (EndingBalance_Before + LineItemAmount),
				"Failed : Line ItemAmount is not Added to Ending Balance");
		return Cat1;
	}

	public void Commit(WebDriver driver, ExtentTest test) throws InterruptedException {
		Elements_Accounts CreateAccountlineitem = new Elements_Accounts(driver);
		CreateAccountlineitem.Click_Commit.click();
		Wait.wait2Second();
		Utility.app_logs.info("Click Commit ");
		if (CreateAccountlineitem.Click_Commit.isEnabled()) {
			CreateAccountlineitem.Click_Commit.click();
			Utility.app_logs.info("Click Commit Again");
		}
		assertTrue(!CreateAccountlineitem.Click_Commit.isEnabled(), "Failed: Added Line Item is not commited");
	}

	public ArrayList<String> click_Pay(WebDriver driver, ExtentTest test1, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Accounts AccountPayment = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Account_pay);
		Utility.ScrollToElement(AccountPayment.Account_pay, driver);
		AccountPayment.Account_pay.click();
		test_steps.add("Click on Pay Button");
		accountsLogger.info("Click on Pay Button");
		return test_steps;
	}

	public ArrayList<String> select_AccountFolio(WebDriver driver, ExtentTest test1, String AccountName,
			ArrayList<String> test_steps) {

		Elements_Accounts Account = new Elements_Accounts(driver);
		String account = "//table[@class='table table-striped table-bordered table-hover']/tbody/tr/td/a";

		Wait.WaitForElement(driver, account);
		Wait.WaitForElement(driver, account);

		WebDriverWait wait = new WebDriverWait(driver, 90);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(account))));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(account))));

		driver.findElement(By.xpath(account)).click();
		test_steps.add("Clicking on Account name : " + AccountName);
		accountsLogger.info("Clicking on Account name : " + AccountName);

		Wait.WaitForElement(driver, OR.Account_Folio);
		wait.until(ExpectedConditions.visibilityOf(Account.Account_Folio));
		wait.until(ExpectedConditions.elementToBeClickable(Account.Account_Folio));
		Account.Account_Folio.click();
		test_steps.add("Clicking on Account Folio");
		accountsLogger.info("Clicking on Account Folio");
		return test_steps;
	}

	public ArrayList<String> OpenSearchedAccount(WebDriver driver, String AccountName, ArrayList<String> test_steps) {

		Elements_Accounts Account = new Elements_Accounts(driver);
		String account = "//table[@class='table table-striped table-bordered table-hover']/tbody/tr/td/a";

		Wait.WaitForElement(driver, account);
		Wait.WaitForElement(driver, account);
		WebDriverWait wait = new WebDriverWait(driver, 90);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(account))));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(account))));

		driver.findElement(By.xpath(account)).click();
		test_steps.add("Clicking on Account name : " + AccountName);
		accountsLogger.info("Clicking on Account name : " + AccountName);

		Wait.WaitForElement(driver, OR.Account_Folio);
		wait.until(ExpectedConditions.visibilityOf(Account.Account_Folio));
		wait.until(ExpectedConditions.elementToBeClickable(Account.Account_Folio));
		return test_steps;
	}

	public ArrayList<String> ClickSearchedAccount(WebDriver driver, String AccountName, ArrayList<String> test_steps) {

		Elements_Accounts Account = new Elements_Accounts(driver);
		String account = "//table[@class='table table-striped table-bordered table-hover']/tbody/tr/td/a";

		Wait.WaitForElement(driver, account);
		Wait.WaitForElement(driver, account);
		WebDriverWait wait = new WebDriverWait(driver, 90);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(account))));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(account))));

		driver.findElement(By.xpath(account)).click();
		test_steps.add("Clicking on Account name : " + AccountName);
		accountsLogger.info("Clicking on Account name : " + AccountName);

		Wait.WaitForElement(driver, OR.Account_Folio);
		wait.until(ExpectedConditions.visibilityOf(Account.Account_Folio));
		return test_steps;

	}

	public ArrayList<String> cashPayment(WebDriver driver, ExtentTest test, String Amount, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);
		float EndingBalance_Before = GetEndingBalance(driver);
		Utility.app_logs.info("Ending banalce Befor = " + EndingBalance_Before);
		click_Pay(driver, test, test_steps);
		Wait.WaitForElement(driver, OR.Select_Account_paymethod);
		Account.Enter_Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), Amount);
		test_steps.add("Enter Amount to pay " + Amount);
		accountsLogger.info("Enter Amount to pay " + Amount);
		String PaymentType = "Cash";
		Wait.explicit_wait_visibilityof_webelement(Account.Select_Account_paymethod, driver);
		new Select(Account.Select_Account_paymethod).selectByVisibleText("Cash");
		test_steps.add("Select Payment type " + PaymentType);
		accountsLogger.info("Select Payment type " + PaymentType);

		Account.Payment_Add.click();
		test_steps.add("Clicking on Add");
		accountsLogger.info("Clicking on Add");

		// Wait.wait3Second();
		Wait.WaitForElement(driver, OR.Apply_Advance_Deposite);

		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.Apply_Advance_Deposite)), driver);
		Account.Click_Continue_Adv_Deposite.click();
		test_steps.add("Click Advance Deposit link");
		accountsLogger.info("Click Advance Deposit link");
		Wait.explicit_wait_visibilityof_webelement(Account.Folio_Cash_Continue_Btn, driver);
		Utility.ScrollToElement(Account.Folio_Cash_Continue_Btn, driver);
		Account.Folio_Cash_Continue_Btn.click();
		test_steps.add("Clicking on Continue");
		accountsLogger.info("Clicking on Continue");
		try {
			Wait.waitForElementToBeGone(driver, Account.Folio_Cash_Continue_Btn, 30);
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(Account.Folio_Cash_Continue_Btn, driver);
			Utility.ScrollToElement(Account.Folio_Cash_Continue_Btn, driver);
			Account.Folio_Cash_Continue_Btn.click();
			test_steps.add("Again Clicking on Continue");
			accountsLogger.info("Again Clicking on Continue");
			Wait.waitForElementToBeGone(driver, Account.Folio_Cash_Continue_Btn, 30);
		}
		Save(driver, test, test_steps);
		test_steps.add("Clicking on Save Account");
		accountsLogger.info("Clicking on Save Account");

		float EndingBalance_After = GetEndingBalance(driver);
		Utility.app_logs.info("Ending banalce After = " + EndingBalance_After);
		Utility.app_logs.info("Amount Payed = " + Float.parseFloat(Amount));

		float value = EndingBalance_Before - EndingBalance_After;

		Utility.app_logs.info("Ending Balance should be " + value);
		float result = Math.round(value * 100) / 100;
		Utility.app_logs.info("Ending Balance should be (round off)" + result);
		assertEquals(Float.parseFloat(Amount), result, "Failed : Cash Payment Failed");
		return test_steps;
	}

	public ArrayList<String> cashPayment(WebDriver driver, ExtentTest test1, String AccountType, String AccountName,
			String AmountToPay, String PaymentType, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);

		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOf(Account.Folio_Balance));

		Wait.WaitForElement(driver, OR.Folio_Balance);

		String balance = Account.Folio_Balance.getText();
		balance = balance.replace("$", "");

		float bal = Float.parseFloat(balance);

		if (bal > 0) {

			test_steps.add("Borefore pay Balance " + bal);
			accountsLogger.info("Borefore pay Balance " + bal);

			Account.Account_pay.click();
			test_steps.add("Clicking on Pay");
			accountsLogger.info("Clicking on Pay");
			/*
			 * new Select
			 * (Account.Select_Account_paymethod).selectByVisibleText(
			 * PaymentType); test.log(LogStatus.PASS,
			 * "Select Payment type "+PaymentType);
			 * accountsLogger.info("Select Payment type "+PaymentType);
			 * 
			 * Account.Enter_Change_Amount.sendKeys(Keys.chord(Keys.CONTROL,
			 * "a"),AmountToPay); test.log(LogStatus.PASS,
			 * "Enter Amount to pay "+AmountToPay);
			 * accountsLogger.info("Enter Amount to pay "+AmountToPay);
			 */

			Wait.wait2Second();
			Wait.WaitForElement(driver, OR.Select_Account_paymethod);

			new Select(Account.Select_Account_paymethod).selectByVisibleText(PaymentType);
			test_steps.add("Select Payment type " + PaymentType);
			accountsLogger.info("Select Payment type " + PaymentType);

			Account.Enter_Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), AmountToPay);
			test_steps.add("Enter Amount to pay " + AmountToPay);
			accountsLogger.info("Enter Amount to pay " + AmountToPay);
			// Wait.wait3Second();

			/*
			 * String text; String text=driver.findElement(By.
			 * xpath("//table[@class='table table-condensed table-striped table-borderd table-hover table-bordered']/tbody[1]/tr[1]/td[2]/span"
			 * )).getText(); driver.findElement(By.
			 * xpath("//table[@class='table table-condensed table-striped table-borderd table-hover table-bordered']/tbody[1]/tr[1]/td[1]/input"
			 * )).click();
			 * 
			 * AccountPayment.Account_AutoApply.click(); Wait.wait2Second();
			 * 
			 * if(driver.findElement(By.
			 * xpath("//table[@class='table table-condensed table-striped table-borderd table-hover table-bordered']/tbody[1]/tr[1]/td[1]/input"
			 * )).isSelected()){ text=driver.findElement(By.
			 * xpath("//table[@class='table table-condensed table-striped table-borderd table-hover table-bordered']/tbody[1]/tr[1]/td[2]/span"
			 * )).getText(); }
			 */

			if (AccountType.contains("Corporate")) {

				Wait.WaitForElement(driver,
						"//span[contains(text(),'Payment')]/../../../../following-sibling::div/div/div/div/div/div/ul/li/div/button[contains(text(),'Add')]");
				driver.findElement(By
						.xpath("//span[contains(text(),'Payment')]/../../../../following-sibling::div/div/div/div/div/div/ul/li/div/button[contains(text(),'Add')]"))
						.click();

				test_steps.add("Clicking on Add");
				accountsLogger.info("Clicking on Add");
				// Wait.wait3Second();
				Wait.WaitForElement(driver, OR.Apply_Advance_Deposite);

				Wait.explicit_wait_xpath(OR.Apply_Advance_Deposite, driver);
				Account.Click_Continue_Adv_Deposite.click();
				test_steps.add("Click Advance Deposit link");
				accountsLogger.info("Click Advance Deposit link");

			}
			test_steps.add("Clicking on Add");
			accountsLogger.info("Clicking on Add");

			Wait.wait3Second();

			Wait.WaitForElement(driver, OR.Folio_Cash_Continue_Btn);
			Utility.ScrollToElement(Account.Folio_Cash_Continue_Btn, driver);
			Account.Folio_Cash_Continue_Btn.click();
			test_steps.add("Clicking on Continue");
			accountsLogger.info("Clicking on Continue");

			try {
				Wait.waitForElementToBeGone(driver, Account.Folio_Cash_Continue_Btn, 30);
			} catch (Exception e) {
				Account.Folio_Cash_Continue_Btn.click();
				test_steps.add("Again Clicking on Continue");
				accountsLogger.info("Again Clicking on Continue");
			}
			Save(driver, test1, test_steps);

			test_steps.add("Clicking on Save Account");
			accountsLogger.info("Clicking on Save Account");

			String balance1 = Account.Folio_Balance.getText();
			balance1 = balance1.replace("$", "");

			float bal1 = Float.parseFloat(balance1);

			test_steps.add("After pay Folio balance " + bal1);
			accountsLogger.info("After pay Folio balance " + bal1);

			if (bal1 + Float.parseFloat(AmountToPay) == bal) {
				test_steps.add("Cash Payment " + AmountToPay + " is successful");
				accountsLogger.info("Cash Payment " + AmountToPay + " is successful");
			} else {
				test_steps.add("Cash Payment " + AmountToPay + " is Fail");
				accountsLogger.info("Cash Payment " + AmountToPay + " is Fail");
			}

		} else {
			test_steps.add("Account : " + AccountName + " balance is Zero");
			accountsLogger.info("Account : " + AccountName + " balance is Zero");
		}
		return test_steps;

	}

	public ArrayList<String> Save(WebDriver driver, ExtentTest test1, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Account_save);
		Wait.explicit_wait_visibilityof_webelement(CreateAccount.Account_save, driver);
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.elementToBeClickable(CreateAccount.Account_save));

		Utility.ScrollToElement(CreateAccount.Account_save, driver);
		CreateAccount.Account_save.click();
		test_steps.add("Click on Save");
		accountsLogger.info("Click on Save");

		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		Wait.explicit_wait_visibilityof_webelement_350(CreateAccount.Toaster_Message, driver);

		try {
			test_steps.add(CreateAccount.Toaster_Message.getText());
			accountsLogger.info(CreateAccount.Toaster_Message.getText());
			CreateAccount.Toaster_Message.click();
			// String getTotasterTitle_ReservationSucess =
			// CreateAccount.Toaster_Title.getText();
			// Assert.assertEquals(getTotasterTitle_ReservationSucess, "Account
			// Saved");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return test_steps;

	}

	public ArrayList<String> SaveAccount(WebDriver driver, ExtentTest test1, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Account_save);
		Utility.ScrollToElement(CreateAccount.Account_save, driver);
		CreateAccount.Account_save.click();
		test_steps.add("Click on Save");
		accountsLogger.info("Click on Save");
		Wait.explicit_wait_visibilityof_webelement(CreateAccount.Toaster_Title, driver);
		String getTotasterTitle_ReservationSucess = CreateAccount.Toaster_Title.getText();
		Assert.assertEquals(getTotasterTitle_ReservationSucess, "Account Saved");
		test_steps.add(CreateAccount.Toaster_Message.getText());
		accountsLogger.info(CreateAccount.Toaster_Message.getText());

		/*
		 * if (CreateAccount.Verify_Toaster_Container.isDisplayed()) {
		 * Wait.explicit_wait_visibilityof_webelement_600(CreateAccount.
		 * Toaster_Title, driver); String getTotasterTitle_ReservationSucess =
		 * CreateAccount.Toaster_Title.getText(); String
		 * getToastermessage_ReservationSucess =
		 * CreateAccount.Toaster_Message.getText();
		 * Assert.assertEquals(getTotasterTitle_ReservationSucess,
		 * "Account Saved"); if (getToastermessage_ReservationSucess.
		 * equals("The account Test Account has been successfully created.")) ;
		 * test_steps.
		 * add("The account Test Account has been successfully Saved.");
		 * accountsLogger.
		 * info("The account Test Account has been successfully Saved."); }
		 */
		return test_steps;

	}

	public ArrayList<String> SaveAccount(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Account_save);
		Utility.ScrollToElement(CreateAccount.Account_save, driver);
		CreateAccount.Account_save.click();
		test_steps.add("Click on Save");
		accountsLogger.info("Click on Save");
		try {
			Wait.explicit_wait_visibilityof_webelement_350(CreateAccount.Toaster_Title, driver);
			String getTotasterTitle_ReservationSucess = CreateAccount.Toaster_Title.getText();
			String getToastermessage_ReservationSucess = CreateAccount.Toaster_Message.getText();
			Assert.assertEquals(getTotasterTitle_ReservationSucess, "Account Saved");
			if (getToastermessage_ReservationSucess.equals("The account Test Account has been successfully created."))
				;
			test_steps.add("The account Test Account has been successfully Saved.");
			accountsLogger.info("The account Test Account has been successfully Saved.");
		} catch (Exception e) {
			test_steps.add("No toaster message Appear");
			accountsLogger.info("No toaster message Appear");
		}
		return test_steps;

	}

	public ArrayList<String> SaveNewAccount(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Account_save);
		Utility.ScrollToElement(CreateAccount.Account_save, driver);
		CreateAccount.Account_save.click();
		test_steps.add("Click on Save");
		accountsLogger.info("Click on Save");
		try {
			Wait.explicit_wait_visibilityof_webelement_350(CreateAccount.Toaster_Title, driver);
			String getTotasterTitle_ReservationSucess = CreateAccount.Toaster_Title.getText();
			String getToastermessage_ReservationSucess = CreateAccount.Toaster_Message.getText();
			Assert.assertEquals(getTotasterTitle_ReservationSucess, "Account Saved");
			if (getToastermessage_ReservationSucess.equals("The account Test Account has been successfully created."))
				;
			accountsLogger.info("The Created Account Has Been Successfully Saved..");
		} catch (Exception e) {
			test_steps.add("No toaster message Appear");
			accountsLogger.info("No toaster message Appear");
		}
		return test_steps;

	}

	public ArrayList<String> ageingPayment(WebDriver driver, ExtentTest test1, String AccountType, String PaymentType,
			String CardName, String CCNumber, String CCExpiry, String CCVCode, String Authorizationtype,
			String ChangeAmount, String ChangeAmountValue, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Accounts AccountPayment = new Elements_Accounts(driver);
		Wait.wait2Second();

		String LineItemname = "";
		if (PaymentType.equalsIgnoreCase("MC")) {

			// AccountPayment.Account_pay.click();

			Wait.WaitForElement(driver, OR.Select_Account_paymethod);
			new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
			accountsLogger.info("Payment Type : " + PaymentType);
			// Wait.wait3Second();

			Wait.WaitForElement(driver, OR.Click_Account_Card_info);
			AccountPayment.Click_Account_Card_info.click();
			Wait.explicit_wait_xpath(OR.Verify_Account_Paymnet_info_popup, driver);
			Wait.wait3Second();
			AccountPayment.Enter_Account_Card_Name.sendKeys(CardName);
			accountsLogger.info("Card Name : " + CardName);

			AccountPayment.Enter_CC_Account_Number.sendKeys(CCNumber);
			accountsLogger.info("Card Number : " + CCNumber);

			AccountPayment.Enter_ExpiryDate_Account.sendKeys(CCExpiry);
			accountsLogger.info("Card Expiry : " + CCExpiry);

			AccountPayment.Enter_CCV_Account.sendKeys(CCVCode);
			accountsLogger.info("Card CVV : " + CCVCode);

			AccountPayment.Click_Ok_Account.click();
			// Wait.wait10Second();

			Wait.WaitForElement(driver, OR.Select_Authorization_Type_Account);
			new Select(AccountPayment.Select_Authorization_Type_Account).selectByVisibleText(Authorizationtype);
			if (ChangeAmount.equalsIgnoreCase("Yes")) {
				/*
				 * ReservationFolio.Change_Amount.clear(); Wait.wait3Second();
				 * ReservationFolio.Change_Amount.sendKeys(ChangeAmountValue);
				 */
				AccountPayment.Enter_Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
			} else {
				// System.out.println("Processed complete amount");
				accountsLogger.info("Processed complete amount");
			}
			// Wait.wait10Second();
			if (Authorizationtype.equalsIgnoreCase("Capture")) {

				Wait.WaitForElement(driver,
						"//table[@class='table table-condensed table-striped table-borderd table-hover table-bordered']/tbody[1]/tr[1]/td[2]/span");
				String text = driver
						.findElement(By
								.xpath("//table[@class='table table-condensed table-striped table-borderd table-hover table-bordered']/tbody[1]/tr[1]/td[2]/span"))
						.getText();
				driver.findElement(By
						.xpath("//table[@class='table table-condensed table-striped table-borderd table-hover table-bordered']/tbody[1]/tr[1]/td[1]/input"))
						.click();
				AccountPayment.Click_Process_Account.click();
				accountsLogger.info("Click Process");
				/*
				 * Wait.explicit_wait_xpath(OR.Apply_Advance_Deposite);
				 * AccountPayment.Click_Continue_Adv_Deposite.click();
				 */
			}
			// Wait.wait3Second();

			Wait.WaitForElement(driver, OR.Verify_MC_Grid);
			Wait.explicit_wait_xpath(OR.Verify_MC_Grid, driver);
			String GetPaymentMethod = AccountPayment.Account_Payment_Method.getText();
			System.out.println(GetPaymentMethod + " " + GetPaymentMethod);
			if (GetPaymentMethod.equals(PaymentType)) {
				// System.out.println("Paymnet Success");

				accountsLogger.info("Payment Success");
			} else {
				// System.out.println("Paymnet Failed");
				accountsLogger.info("Payment Failed");
			}
			AccountPayment.Click_Account_Pay_Continue.click();
			// Wait.wait3Second();

			Wait.WaitForElement(driver, OR.Get_Line_item_Account);
			Wait.explicit_wait_xpath(OR.Get_Line_item_Account, driver);
			String GetMCCard = AccountPayment.Get_Line_item_Account.getText();
			// System.out.println(GetMCCard + " "+GetMCCard);
			if (GetMCCard.equals("Name: MC Account #: XXXX5454 Exp. Date: 08/19")) {
				// System.out.println("Paymnet Success");
				accountsLogger.info("Payment Success");
			} else {
				// System.out.println("Paymnet Failed");
				accountsLogger.info("Payment Failed");
			}
			String GetBalance = AccountPayment.Verify_Ending_Balance.getText();
			// System.out.println(GetBalance + " "+GetBalance);
			String RemoveCurreny[] = GetBalance.split(" ");
			// System.out.println(RemoveCurreny[1]);
			if (ChangeAmount.equalsIgnoreCase("No")) {
				if (RemoveCurreny[1].equals("0.00"))
					;
			} else {
				// System.out.println("Selected Changed Value");

				accountsLogger.info("Selected Changed Value");
			}

		} else if (PaymentType.equalsIgnoreCase("Cash")) {

			new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);

			test_steps.add("Select Payment type " + PaymentType);
			accountsLogger.info("Select Payment type " + PaymentType);

			AccountPayment.Enter_Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
			test_steps.add("Enter Amount to pay " + ChangeAmountValue);
			accountsLogger.info("Enter Amount to pay " + ChangeAmountValue);

			// Wait.wait3Second();

			Wait.WaitForElement(driver,
					"//table[@class='table table-condensed table-striped table-borderd table-hover table-bordered']/tbody[1]/tr[1]/td[2]/span");
			String text = driver
					.findElement(By
							.xpath("//table[@class='table table-condensed table-striped table-borderd table-hover table-bordered']/tbody[1]/tr[1]/td[2]/span"))
					.getText();
			driver.findElement(By
					.xpath("//table[@class='table table-condensed table-striped table-borderd table-hover table-bordered']/tbody[1]/tr[1]/td[1]/input"))
					.click();
			if (AccountType.contains("Corporate")) {

				driver.findElement(By
						.xpath("//span[contains(text(),'Payment')]/../../../../following-sibling::div/div/div/div/div/div/ul/li/div/button[contains(text(),'Add')]"))
						.click();

			} else {
				driver.findElement(By
						.xpath("//span[contains(text(),'Payment')]/../../../../following-sibling::div[1]/div/div/div/div[1]/div/div[2]/div[2]/div/div/div[2]/div[6]/div/button[contains(text(),'Add')]"))
						.click();
			}

			String Path = "(//*[@id='AccountPaymetItemDetail']//input[@type='checkbox'])";
			List<WebElement> checkBoxes = driver.findElements(By.xpath(Path));
			int i = 1;
			for (WebElement checkBox : checkBoxes) {

				if (checkBox.isSelected()) {
					LineItemname = driver
							.findElement(By.xpath(Path + "[" + i
									+ "]//parent::td//following::span[contains(@data-bind,'DisplayCaption')][1]"))
							.getText();
					Utility.app_logs.info(LineItemname);
					break;
				}
				i++;
			}
			test_steps.add("Clicking on Add");
			accountsLogger.info("Clicking on Add");
			// Wait.wait3Second();
			JavascriptExecutor jse = (JavascriptExecutor) driver;

			Wait.explicit_wait_visibilityof_webelement_350(AccountPayment.Folio_Cash_Continue_Btn, driver);
			jse.executeScript("arguments[0].click();", AccountPayment.Folio_Cash_Continue_Btn);
			test_steps.add("Clicking on Continue");
			accountsLogger.info("Clicking on Continue");

			try {
				Wait.waitForElementToBeGone(driver, AccountPayment.Folio_Cash_Continue_Btn, 30);
			} catch (Exception e) {
				AccountPayment.Folio_Cash_Continue_Btn.click();
				test_steps.add("Again Clicking on Continue");
				accountsLogger.info("Again Clicking on Continue");
			}
			Save(driver, test1, test_steps);
			test_steps.add("Clicking on Save Account");
			accountsLogger.info("Clicking on Save Account");
		}

		// Save(driver, test1);
		String loc = "(//table[@class='table table-striped table-bordered table-hover trHeight25']//following-sibling::a[contains(@data-bind,'data.Description') and text()='"
				+ LineItemname + "']/../following-sibling::td/img)[last()]";

		Wait.WaitForElement(driver, loc);
		String str = driver.findElement(By.xpath(loc)).getAttribute("title");
		// System.out.println(str);
		test_steps.add("Payment : " + str);
		accountsLogger.info("Payment : " + str);
		return test_steps;

	}

	public ArrayList<String> ageingPaymentAutoApply(WebDriver driver, ExtentTest test1, String AccountType,
			String PaymentType, String CardName, String CCNumber, String CCExpiry, String CCVCode,
			String Authorizationtype, String ChangeAmount, String ChangeAmountValue, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts AccountPayment = new Elements_Accounts(driver);

		Wait.WaitForElement(driver, OR.Folio_Balance);
		String balance = AccountPayment.Folio_Balance.getText();
		balance = balance.replace("$", "");

		float bal = Float.parseFloat(balance);

		test_steps.add("Before Pay Folio balance : " + balance);
		accountsLogger.info("Before Pay Folio balance : " + balance);

		click_Pay(driver, test1, test_steps);

		Wait.wait2Second();
		if (PaymentType.equalsIgnoreCase("MC")) {

			Wait.WaitForElement(driver, OR.Select_Account_paymethod);
			// AccountPayment.Account_pay.click();
			new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
			// Wait.wait3Second();

			Wait.WaitForElement(driver, OR.Click_Account_Card_info);

			AccountPayment.Click_Account_Card_info.click();
			Wait.explicit_wait_xpath(OR.Verify_Account_Paymnet_info_popup, driver);
			// Wait.wait3Second();

			Wait.WaitForElement(driver, OR.Enter_Account_Card_Name);
			AccountPayment.Enter_Account_Card_Name.sendKeys(CardName);
			accountsLogger.info("Card Name : " + CardName);

			AccountPayment.Enter_CC_Account_Number.sendKeys(CCNumber);
			accountsLogger.info("Card Number : " + CCNumber);

			AccountPayment.Enter_ExpiryDate_Account.sendKeys(CCExpiry);
			accountsLogger.info("Card Expiry : " + CCExpiry);

			AccountPayment.Enter_CCV_Account.sendKeys(CCVCode);
			accountsLogger.info("Card CVV : " + CCVCode);

			AccountPayment.Click_Ok_Account.click();

			// Wait.wait10Second();

			Wait.WaitForElement(driver, OR.Select_Authorization_Type_Account);
			new Select(AccountPayment.Select_Authorization_Type_Account).selectByVisibleText(Authorizationtype);
			if (ChangeAmount.equalsIgnoreCase("Yes")) {
				/*
				 * ReservationFolio.Change_Amount.clear(); Wait.wait3Second();
				 * ReservationFolio.Change_Amount.sendKeys(ChangeAmountValue);
				 */
				AccountPayment.Enter_Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
			} else {
				// System.out.println("Processed complete amount");
				accountsLogger.info("Processed complete amount");

			}
			// Wait.wait5Second();
			if (Authorizationtype.equalsIgnoreCase("Capture")) {
				String text;
				Wait.WaitForElement(driver, OR.Account_AutoApply);
				AccountPayment.Account_AutoApply.click();

				// Wait.wait2Second();
				Wait.WaitForElement(driver,
						"//table[@class='table table-condensed table-striped table-borderd table-hover table-bordered']/tbody[1]/tr[1]/td[1]/input");
				if (driver
						.findElement(By
								.xpath("//table[@class='table table-condensed table-striped table-borderd table-hover table-bordered']/tbody[1]/tr[1]/td[1]/input"))
						.isSelected()) {
					text = driver
							.findElement(By
									.xpath("//table[@class='table table-condensed table-striped table-borderd table-hover table-bordered']/tbody[1]/tr[1]/td[2]/span"))
							.getText();
				}
			}

			Wait.WaitForElement(driver, OR.Click_Process_Account);

			AccountPayment.Click_Process_Account.click();
			accountsLogger.info("Click Process");

			// Wait.wait3Second();

			Wait.WaitForElement(driver, OR.Verify_MC_Grid);
			Wait.explicit_wait_xpath(OR.Verify_MC_Grid, driver);
			String GetPaymentMethod = AccountPayment.Account_Payment_Method.getText();
			// System.out.println(GetPaymentMethod + " "+GetPaymentMethod);
			if (GetPaymentMethod.equals(PaymentType)) {
				// System.out.println("Paymnet Success");
				accountsLogger.info("Payment Success");
			} else {
				// System.out.println("Paymnet Failed");
				accountsLogger.info("Payment Failed");
			}

			Wait.WaitForElement(driver, OR.Click_Account_Pay_Continue);
			AccountPayment.Click_Account_Pay_Continue.click();
			// Wait.wait3Second();

			Wait.WaitForElement(driver, OR.Get_Line_item_Account);
			Wait.explicit_wait_xpath(OR.Get_Line_item_Account, driver);
			String GetMCCard = AccountPayment.Get_Line_item_Account.getText();
			// System.out.println(GetMCCard + " "+GetMCCard);
			if (GetMCCard.equals("Name: MC Account #: XXXX5454 Exp. Date: 08/19")) {
				// System.out.println("Paymnet Success");

				accountsLogger.info("Payment Success");
			} else {
				// System.out.println("Paymnet Failed");
				accountsLogger.info("Payment Failed");
			}

			Wait.WaitForElement(driver, OR.Verify_Ending_Balance);
			String GetBalance = AccountPayment.Verify_Ending_Balance.getText();
			// System.out.println(GetBalance + " "+GetBalance);
			String RemoveCurreny[] = GetBalance.split(" ");
			// System.out.println(RemoveCurreny[1]);
			if (ChangeAmount.equalsIgnoreCase("No")) {
				if (RemoveCurreny[1].equals("0.00"))
					;
			} else {
				accountsLogger.info("Selected Changed Value");
			}

		} else if (PaymentType.equalsIgnoreCase("Cash")) {

			Wait.WaitForElement(driver, OR.Select_Account_paymethod);
			new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);

			test_steps.add("Select Payment type " + PaymentType);
			accountsLogger.info("Select Payment type " + PaymentType);

			new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);

			test_steps.add("Select Payment type " + PaymentType);
			accountsLogger.info("Select Payment type " + PaymentType);

			AccountPayment.Enter_Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
			test_steps.add("Enter Amount to pay " + ChangeAmountValue);
			// Wait.wait3Second();

			String text;
			Wait.WaitForElement(driver, OR.Account_AutoApply);
			AccountPayment.Account_AutoApply.click();
			accountsLogger.info("Click Auto Apply");

			// Wait.wait2Second();

			Wait.WaitForElement(driver,
					"//table[@class='table table-condensed table-striped table-borderd table-hover table-bordered']/tbody[1]/tr[1]/td[1]/input");
			if (driver
					.findElement(By
							.xpath("//table[@class='table table-condensed table-striped table-borderd table-hover table-bordered']/tbody[1]/tr[1]/td[1]/input"))
					.isSelected()) {
				text = driver
						.findElement(By
								.xpath("//table[@class='table table-condensed table-striped table-borderd table-hover table-bordered']/tbody[1]/tr[1]/td[2]/span"))
						.getText();
			}

			if (AccountType.contains("Corporate")) {

				driver.findElement(By
						.xpath("//span[contains(text(),'Payment Details')]/../../../../following-sibling::div/div/div/div/div/div/ul/li/div/button[contains(text(),'Add')]"))
						.click();

				int i = 0;

			} else {
				driver.findElement(By
						.xpath("//span[contains(text(),'Payment Details')]/../../../../following-sibling::div[1]/div/div/div/div[1]/div/div[2]/div[2]/div/div/div[2]/div[6]/div/button[contains(text(),'Add')]"))
						.click();
			}

			test_steps.add("Clicking on Add");
			accountsLogger.info("Clicking on Add");

			Wait.WaitForElement(driver, OR.Folio_Cash_Continue_Btn);
			AccountPayment.Folio_Cash_Continue_Btn.click();
			test_steps.add("Clicking on Continue");
			accountsLogger.info("Clicking on Continue");

		}

		Save(driver, test1, test_steps);

		String balance1 = AccountPayment.Folio_Balance.getText();
		balance1 = balance1.replace("$", "");

		float bal1 = Float.parseFloat(balance1);

		test_steps.add("After pay Folio balance " + bal1);
		accountsLogger.info("After pay Folio balance " + bal1);

		if (bal1 + Float.parseFloat(ChangeAmountValue) == bal) {
			test_steps.add("Cash Payment " + ChangeAmountValue + " is successful");
			accountsLogger.info("Cash Payment " + ChangeAmountValue + " is successful");
		} else {
			test_steps.add("Cash Payment " + ChangeAmountValue + " is Fail");
			accountsLogger.info("Cash Payment " + ChangeAmountValue + " is Fail");
		}

		String loc = "//table[@class='table table-striped table-bordered table-hover trHeight25']/tbody/tr/td/span[contains(text(),'Parking')]/../following-sibling::td/img";

		String str = driver.findElement(By.xpath(loc)).getAttribute("title");
		// System.out.println(str);
		test_steps.add("Payment : " + str);
		accountsLogger.info("Payment : " + str);
		return test_steps;
	}

	public ArrayList<String> ageingPaymentAdvanceDeposit(WebDriver driver, ExtentTest test1, String AccountType,
			String PaymentType, String CardName, String CCNumber, String CCExpiry, String CCVCode,
			String Authorizationtype, String ChangeAmount, String ChangeAmountValue, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts AccountPayment = new Elements_Accounts(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOf(AccountPayment.Folio_Balance));

		Wait.WaitForElement(driver, OR.Folio_Balance);
		String balance = AccountPayment.Folio_Balance.getText();
		balance = balance.replace("$", "");

		float bal = Float.parseFloat(balance);

		test_steps.add("Before Pay Folio balance : " + balance);
		accountsLogger.info("Before Pay Folio balance : " + balance);

		click_Pay(driver, test1, test_steps);

		// Wait.wait2Second();
		System.out.println("Cardtype:" + PaymentType);
		if (PaymentType.equalsIgnoreCase("MC")) {

			Wait.explicit_wait_visibilityof_webelement_150(AccountPayment.Select_Account_paymethod, driver);
			// jse.executeScript("arguments[0].click();",
			// AccountPayment.Select_Account_paymethod);
			Wait.wait2Second();

			new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
			test_steps.add("Select payment Method : " + PaymentType);
			accountsLogger.info("Select payment Method : " + PaymentType);

			try {
				String Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();
				test_steps.add("Selected payment Method : " + Type);
				accountsLogger.info("Selected payment Method : " + Type);
				if (Type.contains("Select")) {
					new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
					test_steps.add("Again Select payment Method : " + PaymentType);
					accountsLogger.info("Again Select payment Method : " + PaymentType);
					Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();
					test_steps.add("Selected payment Method : " + Type);
					accountsLogger.info("Selected payment Method : " + Type);
				}
				assertTrue(Type.contains(PaymentType), "Failed : Payment Type is not selected");
			} catch (Exception e) {
				Wait.explicit_wait_visibilityof_webelement(AccountPayment.Select_Account_paymethod, driver);
				new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
				test_steps.add("Select payment Method : " + PaymentType);
				accountsLogger.info("Select payment Method : " + PaymentType);
				String Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();
				test_steps.add("Selected payment Method : " + Type);
				accountsLogger.info("Selected payment Method : " + Type);
				assertTrue(Type.contains(PaymentType), "Failed : Payment Type is not selected");
			}
			// Wait.wait3Second();
			try {
				Wait.explicit_wait_visibilityof_webelement(AccountPayment.Click_Account_Card_info, driver);
				AccountPayment.Click_Account_Card_info.click();
				Wait.explicit_wait_visibilityof_webelement(AccountPayment.Enter_Account_Card_Name, driver);
			} catch (Exception e) {
				Wait.WaitForElement(driver, OR.Click_Account_Card_info);
				jse.executeScript("arguments[0].click();", AccountPayment.Click_Account_Card_info);
				Wait.explicit_wait_visibilityof_webelement(AccountPayment.Enter_Account_Card_Name, driver);
			}

			test_steps.add("Click Card Info");
			accountsLogger.info("Click Card Info");

			Wait.explicit_wait_xpath(OR.Verify_Account_Paymnet_info_popup, driver);
			// Wait.wait2Second();

			Wait.WaitForElement(driver, OR.Enter_Account_Card_Name);

			AccountPayment.Enter_Account_Card_Name.sendKeys(CardName);
			test_steps.add("Enter Card Name :" + CardName);
			accountsLogger.info("Enter Card Name :" + CardName);

			AccountPayment.Enter_CC_Account_Number.sendKeys(CCNumber);
			test_steps.add("Enter Card Number : " + CCNumber);
			accountsLogger.info("Enter Card Number : " + CCNumber);

			AccountPayment.Enter_ExpiryDate_Account.sendKeys(CCExpiry);
			test_steps.add("Enter Expiry date : " + CCExpiry);
			accountsLogger.info("Enter Expiry date : " + CCExpiry);

			AccountPayment.Enter_CCV_Account.sendKeys(CCVCode);
			test_steps.add("Enter CVV : " + CCVCode);
			accountsLogger.info("Enter CVV : " + CCVCode);

			AccountPayment.Click_Ok_Account.click();
			test_steps.add("Click OK");
			accountsLogger.info("Click OK");

			Wait.waitForElementToBeGone(driver, AccountPayment.Click_Ok_Account, 60);
			// Wait.wait5Second();
			String Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();
			test_steps.add("Selected payment Method : " + Type);
			accountsLogger.info("Selected payment Method : " + Type);
			if (Type.contains("Select")) {
				new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
				test_steps.add("Again Select payment Method : " + PaymentType);
				accountsLogger.info("Again Select payment Method : " + PaymentType);
				Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();
				test_steps.add("Selected payment Method : " + Type);
				accountsLogger.info("Selected payment Method : " + Type);
			}
			assertTrue(Type.contains(PaymentType), "Failed : Payment Type is not selected");
			Wait.WaitForElement(driver, OR.Select_Authorization_Type_Account);
			Wait.explicit_wait_visibilityof_webelement(AccountPayment.Select_Authorization_Type_Account, driver);
			new Select(AccountPayment.Select_Authorization_Type_Account).selectByVisibleText(Authorizationtype);
			if (ChangeAmount.equalsIgnoreCase("Yes")) {
				/*
				 * ReservationFolio.Change_Amount.clear(); Wait.wait3Second();
				 * ReservationFolio.Change_Amount.sendKeys(ChangeAmountValue);
				 */
				AccountPayment.Enter_Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
				test_steps.add("Enter amount to pay : " + ChangeAmountValue);
				accountsLogger.info("Enter amount to pay : " + ChangeAmountValue);
			} else {
				// System.out.println("Processed complete amount");
			}
			// Wait.wait3Second();
			if (Authorizationtype.equalsIgnoreCase("Capture")) {
				// Wait.WaitForElement(driver, OR.Click_Process_Account);

				String text;
				Wait.explicit_wait_visibilityof_webelement_350(AccountPayment.Click_Process_Account, driver);
				Utility.ScrollToElement(AccountPayment.Click_Process_Account, driver);
				AccountPayment.Click_Process_Account.click();

				test_steps.add("Click Process");
				accountsLogger.info("Click Process");

				// Wait.wait3Second();

				Wait.WaitForElement(driver, OR.Apply_Advance_Deposite);
				Wait.explicit_wait_xpath(OR.Apply_Advance_Deposite, driver);
				AccountPayment.Click_Continue_Adv_Deposite.click();
				test_steps.add("Click Advance Deposit link");
				accountsLogger.info("Click Advance Deposit link");
			}

			// Wait.wait3Second();
			Wait.explicit_wait_xpath(OR.Verify_MC_Grid, driver);
			Wait.WaitForElement(driver, OR.Verify_MC_Grid);
			String GetPaymentMethod = AccountPayment.Account_Payment_Method.getText();
			// System.out.println(GetPaymentMethod + " "+GetPaymentMethod);
			if (GetPaymentMethod.equals(PaymentType)) {
				// System.out.println("Paymnet Success");
			} else {

			}
			Wait.WaitForElement(driver, OR.Click_Account_Pay_Continue);
			AccountPayment.Click_Account_Pay_Continue.click();
			test_steps.add("Click Continue");
			accountsLogger.info("Click Continue");
			String GetMCCard = "";
			int Size = AccountPayment.Get_Line_item_Account_1.size();
			System.out.println("Size:" + Size);
			try {
				jse.executeScript("arguments[0].scrollIntoView();", AccountPayment.Get_Line_item_Account);

				Wait.WaitForElement(driver, OR.Get_Line_item_Account);
				Wait.explicit_wait_xpath(OR.Get_Line_item_Account, driver);
				GetMCCard = AccountPayment.Get_Line_item_Account.getText();

			} catch (Exception e) {
				jse.executeScript("arguments[0].scrollIntoView();",
						AccountPayment.Get_Line_item_Account_1.get(Size - 1));
				GetMCCard = AccountPayment.Get_Line_item_Account_1.get(Size - 1).getText();
			}
			// System.out.println(GetMCCard + " "+GetMCCard);
			if (GetMCCard.equals("Name: MC Account #: XXXX5454 Exp. Date: 08/19")) {
				// System.out.println("Paymnet Success");
			} else {
				// System.out.println("Paymnet Failed");
			}
			String GetBalance = AccountPayment.Verify_Ending_Balance.getText();
			// System.out.println(GetBalance + " "+GetBalance);
			String RemoveCurreny[] = GetBalance.split(" ");
			// System.out.println(RemoveCurreny[1]);
			if (ChangeAmount.equalsIgnoreCase("No")) {
				if (RemoveCurreny[1].equals("0.00"))
					;
			} else {
				// System.out.println("Selected Changed Value");
			}
		} else if (PaymentType.equalsIgnoreCase("Cash")) {
			Wait.WaitForElement(driver, OR.Select_Account_paymethod);
			new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
			test_steps.add("Select Payment type " + PaymentType);
			accountsLogger.info("Select Payment type " + PaymentType);

			// new
			// Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
			// test_steps.add("Select Payment type " + PaymentType);
			// accountsLogger.info("Select Payment type " + PaymentType);

			AccountPayment.Enter_Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
			test_steps.add("Enter Amount to pay " + ChangeAmountValue);
			accountsLogger.info("Enter Amount to pay " + ChangeAmountValue);

			if (AccountType.contains("Corporate")) {

				Wait.WaitForElement(driver,
						"//span[contains(text(),'Payment')]/../../../../following-sibling::div/div/div/div/div/div/ul/li/div/button[contains(text(),'Add')]");
				driver.findElement(By
						.xpath("//span[contains(text(),'Payment')]/../../../../following-sibling::div/div/div/div/div/div/ul/li/div/button[contains(text(),'Add')]"))
						.click();

				test_steps.add("Clicking on Add");
				accountsLogger.info("Clicking on Add");
				// Wait.wait3Second();

				Wait.WaitForElement(driver, OR.Apply_Advance_Deposite);
				Wait.explicit_wait_xpath(OR.Apply_Advance_Deposite, driver);
				AccountPayment.Click_Continue_Adv_Deposite.click();
				test_steps.add("Click Advance Deposit link");
				accountsLogger.info("Click Advance Deposit link");

			} else {
				driver.findElement(By
						.xpath("//span[contains(text(),'Payment')]/../../../../following-sibling::div[1]/div/div/div/div[1]/div/div[2]/div[2]/div/div/div[2]/div[6]/div/button[contains(text(),'Add')]"))
						.click();
			}

			// Wait.wait3Second();
			Wait.WaitForElement(driver, OR.Folio_Cash_Continue_Btn);

			AccountPayment.Folio_Cash_Continue_Btn.click();
			test_steps.add("Clicking on Continue");
			accountsLogger.info("Clicking on Continue");

		}

		String str = driver
				.findElement(By.xpath("//span[contains(text(),'Advance Deposit')]/../following-sibling::span/span"))
				.getText();
		System.out.println("Amount is :" + ChangeAmountValue);

		System.out.println("string is :" + str);

		str = str.replace("$", "");

		str = str.trim();

		// float a = Float.parseFloat(str);

		test_steps.add("Advanced deposit balance " + str);
		accountsLogger.info("Advanced deposit balance " + str);
		//
		// if (a == Float.parseFloat(ChangeAmountValue)) {
		// test_steps.add("Advanced Deposit Successfull for : " + str);
		// accountsLogger.info("Advanced Deposit Successfull for : " + str);
		//
		// } else {
		// test_steps.add("Advanced Deposit not for : " + str);
		// accountsLogger.info("Advanced Deposit not for : " + str);
		// }

		Save(driver, test1, test_steps);

		String balance1 = AccountPayment.Folio_Balance.getText();
		balance1 = balance1.replace("$", "");

		float bal1 = Float.parseFloat(balance1);

		test_steps.add("After pay Folio balance " + bal1);
		accountsLogger.info("After pay Folio balance " + bal1);

		if (bal1 + Float.parseFloat(ChangeAmountValue) == bal) {
			test_steps.add("Cash Payment " + ChangeAmountValue + " is successful");
			accountsLogger.info("Cash Payment " + ChangeAmountValue + " is successful");
		} else {
			test_steps.add("Cash Payment " + ChangeAmountValue + " is Fail");
			accountsLogger.info("Cash Payment " + ChangeAmountValue + " is Fail");
		}

		return test_steps;

	}

	public void close_Account(WebDriver driver) throws InterruptedException {
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.wait2Second();
		String CloseAccButton = "//ul[@class='sec_nav']/li[7]/a/i";
		WebElement CloseAccountButton = driver.findElement(By.xpath(CloseAccButton));
		Wait.wait5Second();
		Wait.WaitForElement(driver, CloseAccButton);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(CloseAccButton)), driver);
		new WebDriverWait(driver, 100).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(By.xpath(CloseAccButton)));
		CloseAccountButton.click();
		accountsLogger.info("Close Account");
		Wait.WaitForElement(driver, OR.CloseUnSavedDataTab);
		if (CreateAccount.CloseUnSavedDataTab.isDisplayed()) {
			CreateAccount.CloseUnSavedDataTab.click();
		}
		assertTrue(CreateAccount.Click_New_Account.isDisplayed(), "Account Button didn't display");
	}

	public void closeAccount(WebDriver driver) throws InterruptedException {
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.wait2Second();
		String CloseAccButton = "//ul[@class='sec_nav']/li[7]/a/i";
		WebElement CloseAccountButton = driver.findElement(By.xpath(CloseAccButton));
		Wait.wait2Second();
		Wait.WaitForElement(driver, CloseAccButton);
		Utility.ScrollToElement(CloseAccountButton, driver);
		CloseAccountButton.click();
		accountsLogger.info("Close Account");
		assertTrue(CreateAccount.Click_New_Account.isDisplayed(), "Account Button didn't display");
	}

	public void WaitAccountLoading(WebDriver driver) {
		Wait.explicit_wait_absenceofelement(OR.Account_ModuleLoading, driver);
	}

	public void NewReservationButton(WebDriver driver, ExtentTest test) {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement(CreateAccount.NewReservation_Button, driver);
		CreateAccount.NewReservation_Button.click();
		// Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
		Wait.explicit_wait_xpath(OR_Reservation.CP_NewReservation_CheckinCal, driver);

	}

	public void CP_NewReservationButton(WebDriver driver, ExtentTest test) {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement(CreateAccount.NewReservation_Button, driver);
		CreateAccount.NewReservation_Button.click();
		Wait.explicit_wait_xpath(OR.New_Reservation_Tab, driver);
		Wait.explicit_wait_xpath(OR_Reservation.CP_NewReservation_CheckinCal, driver);

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 *
	 * ' Method Name: <AccountSave> ' Description: <This method will Save
	 * account when created/Modification: remove unnecessary wait> ' Input
	 * parameters:(WebDriver) ' Return value: void ' Updated By: <Adhnan
	 * Ghaffar> ' Updated On: <06/02/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> AccountSave(WebDriver driver, ExtentTest test, String AccountName,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Account_Save_Button), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Account_Save_Button), driver);
		CreateAccount.Account_Save_Button.click();
		test_steps.add("Click Save ");
		accountsLogger.info("Click Save");
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		Wait.waitForElementToBeVisibile(By.className(OR.Toaster_Message), driver);
		String Toast_Message = CreateAccount.Toaster_Message.getText();

		String dirty = "(//img[@src='scripts/innCenter/V4/server/dirty.png'])[7]";
		if (driver.findElement(By.xpath(dirty)).isDisplayed()
				|| Toast_Message.contains("Please fill in all the mandatory fields")) {
			accountsLogger.info("************* Please fill in all the mandatory fields ************");
			test_steps.add("Please fill in all the mandatory fields");
		} else {
			test_steps.add(Toast_Message);
			accountsLogger.info(Toast_Message);
			assertEquals(Toast_Message, "The account " + AccountName + " has been successfully created.",
					"New account does not create");
		}

		return test_steps;
	}

	public ArrayList<String> SaveCloseAccount(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();",
		// CreateAccount.Account_Save_Clsoe);
		Utility.ScrollToElement(CreateAccount.Account_Save_Clsoe, driver);
		try {
			CreateAccount.Account_Save_Clsoe.click();
		} catch (Exception e) {
			jse.executeScript("arguments[0].click();", CreateAccount.Account_Save_Clsoe);
		}
		test_steps.add("Click Save and Close");
		accountsLogger.info("Click Save and Close");
		Wait.explicit_wait_visibilityof_webelement_150(CreateAccount.Toaster_Message, driver);
		String Toast_Message = CreateAccount.Toaster_Message.getText();
		try {
			CreateAccount.Toaster_Message.click();
		} catch (Exception e) {

		}
		test_steps.add(Toast_Message);
		accountsLogger.info(Toast_Message);
		return test_steps;
	}

	public ArrayList<String> SaveCloseAccount1(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", CreateAccount.Account_Save_Clsoe);
		test_steps.add("Click Save and Close");
		accountsLogger.info("Click Save and Close");
		jse.executeScript("arguments[0].click();", CreateAccount.CloseUnSavedDataTab);
		Wait.explicit_wait_visibilityof_webelement_150(CreateAccount.Toaster_Message, driver);
		String Toast_Message = CreateAccount.Toaster_Message.getText();
		test_steps.add(Toast_Message);
		accountsLogger.info(Toast_Message);
		return test_steps;
	}

	public String getAccountNum(WebDriver driver) throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		String Account_Number = CreateAccount.GetAccount_Number.getAttribute("value");
		return Account_Number;

	}

	public ArrayList<String> DeleteAccount(WebDriver driver, ExtentTest test, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		CreateAccount.Enter_AccountNumber_On_SearchPages.sendKeys(Utility.Account_Number);
		CreateAccount.Search_Accounts_Button.click();
		Wait.WaitForElement(driver, OR.Search_Account_Result);
		String Account_Number = CreateAccount.Search_Account_Result.getText();
		assertEquals(Account_Number, Utility.Account_Number, "Search account number does not exist");
		CreateAccount.Search_Account_Checkbox.click();
		CreateAccount.Account_Delete_Button.click();
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
		String Toast_Message = CreateAccount.Toaster_Message.getText();
		assertEquals(Toast_Message, "Accounts Deleted Successfully", "New account does not delete");

		test_steps.add(Toast_Message);
		accountsLogger.info(Toast_Message);
		return test_steps;

	}

	public ArrayList<String> DeleteAccount(WebDriver driver, String AccountNumber, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Search_Account_Result);
		String Account_Number = CreateAccount.Search_Account_Result.getText();
		assertEquals(Account_Number, AccountNumber, "Search account number does not exist");
		CreateAccount.Search_Account_Checkbox.click();
		Utility.ScrollToElement(CreateAccount.Account_Delete_Button, driver);
		CreateAccount.Account_Delete_Button.click();

		String Toast_Message = "";
		try {
			Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
			Wait.waitUntilPresenceOfElementLocatedByClassName(OR.Toaster_Message, driver);
			Toast_Message = CreateAccount.Toaster_Message.getText();
		} catch (Exception e) {
			Wait.waitUntilPresenceOfElementLocatedByClassName(OR.Toaster_Message, driver);
			Toast_Message = CreateAccount.Toaster_Message.getText();
		}
		assertEquals(Toast_Message, "Accounts Deleted Successfully", "New account does not delete");
		try {
			if (CreateAccount.Toaster_Message.isDisplayed()) {
				Wait.waitForElementToBeGone(driver, CreateAccount.Toaster_Message, 10);
			} else {
				Utility.app_logs.info("Toaster disappears");
			}
		} catch (Exception e) {

		}
		test_steps.add(Toast_Message);
		accountsLogger.info(Toast_Message);
		return test_steps;

	}

	public void OpenExitingAccount(WebDriver driver) throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);
		Account.Searched_AccountName.click();
		try {
			Wait.explicit_wait_visibilityof_webelement(Account.Select_Market_Segment, driver);
			assertTrue(Account.Select_Market_Segment.isDisplayed(), "Existing Account didn't open");
		} catch (Exception e) {
			driver.navigate().refresh();
			Wait.explicit_wait_visibilityof_webelement(Account.Select_Market_Segment, driver);
			assertTrue(Account.Select_Market_Segment.isDisplayed(), "Existing Account didn't open");
		}
	}

	public void ClickUnitownerItem(WebDriver driver) throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement(Account.UnitOwner_Item_link, driver);
		Account.UnitOwner_Item_link.click();
		accountsLogger.info("Navigate to new unitowner page");
	}

	public void ClickTravelAgentItem(WebDriver driver) throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);

		try {
			Wait.WaitForElement(driver, OR.TravelAgenItem);
			Account.TravelAgenItem.click();
		} catch (Exception e) {
			Wait.WaitForElement(driver, OR.TravelAgen_Item_link);
			Account.TravelAgen_Item_link.click();
		}
		accountsLogger.info("Navigate to new travel agent page");
	}

	public String CreateNewUnitItem(WebDriver driver, String ItemName, String DisplayName, String Description,
			String ItemValue, String Category, String SelectTax) throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);

		Wait.explicit_wait_visibilityof_webelement_150(Account.NewItem, driver);
		Account.NewItem.click();
		Random rand = new Random();
		int value = rand.nextInt(100);
		ItemName = ItemName + Integer.toString(value);
		DisplayName = ItemName;
		Description = ItemName;
		Wait.wait2Second();
		Account.Itme_Name.sendKeys(ItemName);
		Account.Item_DisplayName.sendKeys(DisplayName);
		Account.Item_Description.sendKeys(Description);
		Account.Item_Value.sendKeys(ItemValue);
		Account.PercentCheckbox.click();

		new Select(Account.SelectItem_Catagory).selectByVisibleText(Category);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", Account.Item_Associate_Button);

		Account.Item_Associate_Button.click();
		Wait.wait2Second();
		driver.switchTo().frame(0);
		driver.switchTo().frame(0);
		new Select(Account.SelectTax).selectByVisibleText(SelectTax);
		Account.AddTax_Button.click();
		Wait.wait2Second();
		Account.Tax_Done_Button.click();
		driver.switchTo().defaultContent();
		Wait.wait2Second();
		Account.SaveItem_Button.click();

		return ItemName;

	}

	public String CreateNewTravelAgentItem(WebDriver driver, String ItemName, String DisplayName, String Description,
			String ItemValue, String Category, String SelectTax) throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);
		Wait.wait2Second();
		Account.NewItem.click();
		ItemName = ItemName + Utility.getTimeStamp();
		DisplayName = ItemName;
		Description = ItemName;
		Wait.wait2Second();
		Account.TravelAgentItme_Name.sendKeys(ItemName);
		Account.TravelAgentItem_DisplayName.sendKeys(DisplayName);
		Account.TravelAgentItem_Description.sendKeys(Description);
		Account.TravelAgentItem_Value.sendKeys(ItemValue);
		Account.TravelAgentItem_Percent.click();

		// new
		// Select(Account.SelectItem_Catagory).selectByVisibleText(Category);
		Account.Item_Associate_Button.click();
		Wait.wait2Second();
		driver.switchTo().frame(0);
		driver.switchTo().frame(0);
		new Select(Account.SelectTax).selectByVisibleText(SelectTax);
		Account.AddTax_Button.click();
		Wait.wait2Second();
		Account.Tax_Done_Button.click();
		driver.switchTo().defaultContent();
		Wait.wait2Second();
		Account.SaveItem_Button.click();
		Wait.wait2Second();
		Account.DoneItem_Button.click();

		return ItemName;

	}

	public boolean VerifyItem(WebDriver driver, String ItemName) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(OR.CreatedItem_Pages)));
		int Size = driver.findElements(By.xpath(OR.CreatedItem_Pages)).size();
		if (Size > 1) {
			System.out.println("Pages : " + (Size - 1));
		}
		boolean found = false;
		for (int Page = 1; Page < Size; Page++) {
			Wait.WaitForElement(driver, OR.TravelAgentItemList);
			int count = driver.findElements(By.xpath(OR.TravelAgentItemList)).size();
			// System.out.println(count);

			for (int i = 0; i < count; i++) {
				Wait.wait2Second();
				int rowNumber = i + 1;
				String item_Name = driver
						.findElement(By.xpath("(" + OR.TravelAgentItemList + ")[" + rowNumber + "]/td[1]/a")).getText();
				// System.out.println("item name is " + item_Name);
				if (item_Name.contains(ItemName)) {
					found = true;
					break;
				}
			}
			if (found) {
				if (Page != 1) {
					String firstPagePath = "//tr[@align='right']/td//following-sibling::*[text()='1']";
					jse.executeScript("arguments[0].scrollIntoView(true);",
							driver.findElement(By.xpath(firstPagePath)));
					driver.findElement(By.xpath(firstPagePath)).click();
					accountsLogger.info("Move to Page " + 1);
					Wait.WaitForElement(driver, OR.TravelAgentItemList);
				}
				break;
			} else {

				int NextPage = Page + 1;
				String NextPagePath = "//tr[@align='right']/td//following-sibling::*[text()='" + NextPage + "']";
				jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(NextPagePath)));
				driver.findElement(By.xpath(NextPagePath)).click();
				accountsLogger.info("Move to Page " + NextPage);
				Wait.WaitForElement(driver, OR.TravelAgentItemList);
			}
		}
		if (found) {
			accountsLogger.info(ItemName + " Item Exist");
		} else {
			assertTrue(false, "Item not found");
		}
		return found;
	}

	public ArrayList<String> DeleteItem(WebDriver driver, String ItemName, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Accounts Account = new Elements_Accounts(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(OR.CreatedItem_Pages)));
		int Size = driver.findElements(By.xpath(OR.CreatedItem_Pages)).size();
		if (Size > 1) {
			System.out.println("Pages : " + (Size - 1));
		}
		boolean found = false;
		for (int Page = 1; Page < Size; Page++) {
			Wait.WaitForElement(driver, OR.TravelAgentItemList);
			int count = driver.findElements(By.xpath(OR.TravelAgentItemList)).size();
			System.out.println(count);

			for (int i = 0; i < count; i++) {
				Wait.wait2Second();
				int rowNumber = i + 1;
				String item_Name = driver
						.findElement(By.xpath("(" + OR.TravelAgentItemList + ")[" + rowNumber + "]/td[1]/a")).getText();
				System.out.println("item name is " + item_Name);
				if (item_Name.contains(ItemName)) {
					found = true;
					driver.findElement(By.xpath("(" + OR.TravelAgentItemList + ")[" + rowNumber + "]/td[5]//input"))
							.click();
					Wait.wait2Second();
					Account.Delete_TravelAgentItem.click();
					break;
				}
			}
			if (found) {
				if (Page != 1) {
					String firstPagePath = "//tr[@align='right']/td//following-sibling::*[text()='1']";
					jse.executeScript("arguments[0].scrollIntoView(true);",
							driver.findElement(By.xpath(firstPagePath)));
					driver.findElement(By.xpath(firstPagePath)).click();
					accountsLogger.info("Move to Page " + 1);
					Wait.WaitForElement(driver, OR.TravelAgentItemList);
				}
				break;
			} else {

				int NextPage = Page + 1;
				String NextPagePath = "//tr[@align='right']/td//following-sibling::*[text()='" + NextPage + "']";
				jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(NextPagePath)));
				driver.findElement(By.xpath(NextPagePath)).click();
				accountsLogger.info("Move to Page " + NextPage);
				Wait.WaitForElement(driver, OR.TravelAgentItemList);
			}
		}
		if (found) {
			test_steps.add(ItemName + " Item Delete");
			accountsLogger.info(ItemName + " Item Delete");
		} else {
			assertTrue(false, "Item not found");
		}
		return test_steps;

	}

	public ArrayList<String> AccountStatement(WebDriver driver, String AccountType, String PeriodDate,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);

		Account.Account_Statement_Tab.click();
		test_steps.add("Click Account Statement Tab");
		accountsLogger.info("Click Account Statement Tab");
		Wait.explicit_wait_visibilityof_webelement(Account.Account_Statement_Page, driver);
		String AccountTypePath = "//label[text()='" + AccountType + "']//preceding-sibling::input[@type='radio']";
		driver.findElement(By.xpath(AccountTypePath)).click();
		assertTrue(driver.findElement(By.xpath(AccountTypePath)).isSelected(),
				"Failed : Account Type " + AccountType + " is not Selected");
		test_steps.add("Select Account Type : " + AccountType);
		accountsLogger.info("Select Account Type : " + AccountType);
		Select SelectPeriod = new Select(Account.Select_PeroidDate);
		List<WebElement> list = SelectPeriod.getOptions();
		boolean InProgress_period = false;
		for (WebElement period : list) {
			Utility.app_logs.info(period.getText());
			if (period.getText().contains("In Progress")) {
				SelectPeriod.selectByVisibleText(period.getText());
				String SelectedPeriod = new Select(Account.Select_PeroidDate).getFirstSelectedOption().getText();
				test_steps.add("Select Period Date : " + SelectedPeriod);
				accountsLogger.info("Select Period Date : " + SelectedPeriod);
				Account.ViewPeriod_Button.click();
				test_steps.add("Click View Period");
				accountsLogger.info("Click View Period");
				try {
					driver.switchTo().frame("dialog-body0");
					accountsLogger.info("Switch Frame dialog-body0");
					driver.switchTo().frame("frmWaitHost");
					accountsLogger.info("Switch Frame frmWaitHost");
					Wait.explicit_wait_visibilityof_webelement(Account.AccountStatement_Popup, driver);
					test_steps.add("Account Statement Popup displayed");
					accountsLogger.info("Account Statement Popup displayed");
					driver.switchTo().frame("ifrmAccountStatement");
					accountsLogger.info("Switch Frame ifrmAccountStatement");
					Wait.explicit_wait_visibilityof_webelement(Account.AccountStatement, driver);
					test_steps.add("Account Statement displayed");
					accountsLogger.info("Account Statement displayed");
					Wait.wait2Second();
					driver.switchTo().defaultContent();
					accountsLogger.info("Switch default content");
					driver.switchTo().frame("dialog-body0");
					accountsLogger.info("Switch Frame dialog-body0");
					driver.switchTo().frame("frmWaitHost");
					accountsLogger.info("Switch Frame frmWaitHost");
					Wait.explicit_wait_visibilityof_webelement(Account.AccountStatement_Confirm, driver);
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("arguments[0].click();", Account.AccountStatement_Confirm);
					test_steps.add("Click Account Statement Confirm Button");
					// AccountStatement_Confirm.click();
					Wait.wait2Second();
					driver.switchTo().alert().accept();
					test_steps.add("Accept Alert");
					test_steps.add("Close the In Progress Period");
					accountsLogger.info("Close the In Progress Period");
					driver.switchTo().defaultContent();
					accountsLogger.info("Switch defautContent");
					/*
					 * try{ Utility.ScrollToElement(Account.
					 * AccountStatement_PopupClose);
					 * Account.AccountStatement_PopupClose.click();
					 * accountsLogger.info("Account Statement Popup Closed");
					 * }catch(Exception e){
					 * 
					 * }
					 */ InProgress_period = true;
				} catch (Exception e) {
					if (Account.AccountStatement_ErrorMessage.isDisplayed()) {
						String message = Account.AccountStatement_ErrorMessage.getText();
						test_steps.add(message);
						accountsLogger.info(message);
						assertTrue(false, "Error : " + message);
					}
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("arguments[0].click();", Account.AccountStatement_Cancel);
					System.out.println("Catch");
					// Account.AccountStatement_Cancel.click();
					assertTrue(false, "Failed : Account Statement not displaying Error : ");
				}
				if (InProgress_period) {
					Utility.app_logs.info("Break loop");
					break;

				}
			}
		}
		Wait.explicit_wait_visibilityof_webelement_120(Account.RunPeriod_Button, driver);
		// driver.navigate().refresh();
		// accountsLogger.info("Refresh Page");
		Wait.explicit_wait_visibilityof_webelement(Account.RunPeriod_Button, driver);
		String SelectedPeriod = new Select(Account.Select_PeroidDate).getFirstSelectedOption().getText();
		System.out.println("SelectedPeriod" + SelectedPeriod);
		test_steps.add("SelectedPeriod" + SelectedPeriod);
		if (!SelectedPeriod.contains("New")) {
			SelectPeriod.selectByVisibleText(PeriodDate);
			Wait.explicit_wait_visibilityof_webelement(Account.Select_Date, driver);
			Utility.ScrollToElement(Account.Select_Date, driver);
			Account.Select_Date.click();
			Wait.wait2Second();
			SelectPeriod = new Select(Account.Select_PeroidDate);
			list = SelectPeriod.getOptions();
			for (WebElement period : list) {
				Utility.app_logs.info(period.getText());
				if (period.getText().contains("New")) {
					SelectPeriod.selectByVisibleText(period.getText());
				}
			}
			SelectedPeriod = new Select(Account.Select_PeroidDate).getFirstSelectedOption().getText();
		}

		test_steps.add("Select Period Date : " + SelectedPeriod);
		accountsLogger.info("Select Period Date : " + SelectedPeriod);
		assertTrue(SelectedPeriod.contains("New)"), "Failed: New Period is not Selected");
		Account.RunPeriod_Button.click();
		test_steps.add("Run Period");
		accountsLogger.info("Run Period");
		try {
			driver.switchTo().frame("dialog-body0");
			accountsLogger.info("Switch Frame dialog-body0");
			driver.switchTo().frame("frmWaitHost");
			accountsLogger.info("Switch Frame frmWaitHost");
			Wait.explicit_wait_visibilityof_webelement(Account.AccountStatement_Popup, driver);
			test_steps.add("Account Statement Popup displayed");
			accountsLogger.info("Account Statement Popup displayed");
			driver.switchTo().frame("ifrmAccountStatement");
			accountsLogger.info("Switch Frame ifrmAccountStatement");
			Wait.explicit_wait_visibilityof_webelement(Account.AccountStatement, driver);
			test_steps.add("Account Statement displayed");
			accountsLogger.info("Account Statement displayed");
			Wait.wait2Second();
			driver.switchTo().defaultContent();
			accountsLogger.info("Switch default content");
			driver.switchTo().frame("dialog-body0");
			accountsLogger.info("Switch Frame dialog-body0");
			driver.switchTo().frame("frmWaitHost");
			accountsLogger.info("Switch Frame frmWaitHost");
			Wait.explicit_wait_visibilityof_webelement(Account.AccountStatement_Confirm, driver);
			Account.AccountStatement_Confirm.click();
			Wait.wait2Second();
			driver.switchTo().alert().accept();
			test_steps.add("Click Cancel Account Statement");
			accountsLogger.info("Click Cancel");
			driver.switchTo().defaultContent();
			accountsLogger.info("Switch defautContent");
			/*
			 * try{
			 * Utility.ScrollToElement(Account.AccountStatement_PopupClose);
			 * Account.AccountStatement_PopupClose.click();
			 * accountsLogger.info("Account Statement Popup Closed");
			 * }catch(Exception e){
			 * 
			 * }
			 */ } catch (Exception e) {
			if (Account.AccountStatement_ErrorMessage.isDisplayed()) {
				String message = Account.AccountStatement_ErrorMessage.getText();
				test_steps.add(message);
				accountsLogger.info(message);
				assertTrue(false, "Error : " + message);
			}
			assertTrue(false, "Failed : Account Statement not displaying Error : ");
		}
		return test_steps;

	}

	public void AccountStatement(WebDriver driver) throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);

		Account.Account_Statement_Tab.click();
		for (int i = 0; i <= 1; i++) {

			if (i > 1) {
				driver.findElement(By.id("MainContent_rbtnAccountType_" + i));
			}
			new Select(Account.Select_PeroidDate).selectByVisibleText("New Period");
			Wait.wait2Second();
			Account.Select_Date.click();
			Wait.wait2Second();
			Account.RunPeriod_Button.click();
			Wait.wait5Second();
		}
	}

	public void CloseStatement(WebDriver driver)

	{
		Elements_Accounts elements_Accounts = new Elements_Accounts(driver);

		String path = "//a[@id='dialog-close0']";
		WebElement closestatement = driver.findElement(By.xpath(path));
		closestatement.click();
	}

	public void SearchAccount(WebDriver driver) {

		Elements_Accounts elements_Accounts = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement_150(elements_Accounts.Search_Accounts_Button, driver);
		elements_Accounts.Search_Accounts_Button.click();
		Wait.WaitForElement(driver, OR.Search_Account_Name);
		boolean SearchAccounts = driver.findElements(By.xpath(OR.Search_Account_Name)).size() > 1;
		if (SearchAccounts) {
			driver.findElements(By.xpath(OR.Search_Account_Name)).get(0).click();

		} else {
			elements_Accounts.Search_Account_Name.click();
		}
		try {

		} catch (Exception e) {

		}
	}

	public void SearchAccount_Wait(WebDriver driver) {
		Elements_Accounts Account = new Elements_Accounts(driver);
		try {
			SearchAccount(driver);
			Wait.explicit_wait_visibilityof_webelement_350(Account.Enter_Account_Name, driver);
		} catch (Exception e) {
			Utility.app_logs.info("Search Account Catch Body");
			driver.navigate().refresh();
			SearchAccount(driver);
			Wait.explicit_wait_visibilityof_webelement_350(Account.Enter_Account_Name, driver);
		}
	}

	public String AddTaxItem_AccountFolio(WebDriver driver, String Category, String Amount, String LedgerAccount,
			ExtentTest test1) throws InterruptedException {

		Elements_Accounts CreateAccountlineitem = new Elements_Accounts(driver);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", CreateAccountlineitem.Account_Add);

		Wait.WaitForElement(driver, OR.Account_Add);
		CreateAccountlineitem.Account_Add.click();

		Wait.WaitForElement(driver, OR.Select_Property_lineitem);
		new Select(CreateAccountlineitem.Select_Property_lineitem).selectByIndex(1);

		Wait.WaitForElement(driver, OR.Select_Line_item_Category);
		try {
			new Select(CreateAccountlineitem.Select_Line_item_Category).selectByVisibleText(Category);
		} catch (Exception e) {
			new Select(CreateAccountlineitem.Select_Line_item_Category).selectByIndex(1);
		}
		String cat = new Select(CreateAccountlineitem.Select_Line_item_Category).getFirstSelectedOption().getText();
		// Utility.app_logs.info(message);
		CreateAccountlineitem.Enter_Line_item_Amount.clear();
		Wait.WaitForElement(driver, OR.Enter_Line_item_Amount);
		CreateAccountlineitem.Enter_Line_item_Amount.sendKeys(Amount);
		Commit(driver, test1);

		return cat;
	}

	public void TravelAgent_CheckBox(WebDriver driver, String ItemName) throws InterruptedException {

		String Path = "//td[.='" + ItemName + "']//following-sibling::td/span/input";
		Wait.waitUntilPresenceOfElementLocated(Path, driver);
		boolean IsMoreInput = driver.findElements(By.xpath(Path)).size() > 1;
		if (IsMoreInput) {

			List<WebElement> moreItem = driver.findElements(By.xpath(Path));

			for (int i = 0; i < moreItem.size(); i++) {
				moreItem.get(i).click();
				Wait.wait1Second();
			}

		} else {
			driver.findElement(By.xpath(Path)).click();
		}
	}

	public void DeleteButton(WebDriver driver) throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);

		Wait.waitUntilPresenceOfElementLocated(OR.DeleteItem, driver);
		Account.DeleteItem.click();
		Wait.wait5Second();

	}

	public ArrayList<String> search_Account(WebDriver driver, ExtentTest test1, String AccountType, String AccountName,
			String AccountNumber, String AccountStatus, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);
		driver.navigate().refresh();
		try {
			Wait.explicit_wait_xpath(OR.AccountType, driver);
			Wait.WaitForElement(driver, OR.AccountType);
			Wait.explicit_wait_visibilityof_webelement_150(Account.AccountType, driver);
			Select sel = new Select(Account.AccountType);
			sel.selectByVisibleText(AccountType);
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.Account_Type, driver);
			Wait.WaitForElement(driver, OR.Account_Type);
			Wait.explicit_wait_visibilityof_webelement_150(Account.Account_Type, driver);
			Select sel = new Select(Account.Account_Type);
			sel.selectByVisibleText(AccountType);
		}
		test_steps.add("Account type : " + AccountType);
		accountsLogger.info("Account type : " + AccountType);
		try {
			Account.Account_Name_1.sendKeys(AccountName);
		} catch (Exception e) {
			Account.Account_Name.sendKeys(AccountName);

		}
		test_steps.add("Account Name : " + AccountName);
		accountsLogger.info("Account Name : " + AccountName);
		try {
			Wait.explicit_wait_xpath(OR.Account_Status_1, driver);
			Wait.WaitForElement(driver, OR.Account_Status_1);
			Select sel = new Select(Account.Account_Status_1);
			sel.selectByVisibleText(AccountStatus);
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.Account_Status, driver);
			Wait.WaitForElement(driver, OR.Account_Status);
			Select sel = new Select(Account.Account_Status);
			sel.selectByVisibleText(AccountStatus);
		}
		test_steps.add("Account Status : " + AccountStatus);
		accountsLogger.info("Acount Status: " + AccountStatus);
		try {
			Account.Search_AccountNumber.sendKeys(AccountNumber);
		} catch (Exception e) {
			Account.Account_Number.sendKeys(AccountNumber);
		}
		test_steps.add("Account Number : " + AccountNumber);
		accountsLogger.info("Account Number : " + AccountNumber);

		Account.Account_Search.click();
		test_steps.add("Click Account Search");
		accountsLogger.info("Click Account Search");

		Wait.wait2Second();
		try {
			Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.AccountSearch_LoadingModule)), 60);
		} catch (Exception e) {

		}

		Wait.WaitForElement(driver, OR.Number_Of_Accounts);
		int count = driver.findElements(By.xpath(OR.Number_Of_Accounts)).size();

		if (count == 0) {
			test_steps.add("There are No Accounts available with Account Name'" + AccountName + "' , Account Number '"
					+ AccountNumber + "' and Account Status '" + AccountStatus + "'");
			accountsLogger.info("There are No Accounts available with Account Name'" + AccountName
					+ "' , Account Number '" + AccountNumber + "' and Account Status '" + AccountStatus + "'");
		} else {
			test_steps.add("Number of Accounts available with Account Name'" + AccountName + "' , Account Number '"
					+ AccountNumber + "' and Account Status '" + AccountStatus + "' in this page are '" + count + "'");
			accountsLogger.info("Number of Accounts available with Account Name'" + AccountName + "' , Account Number '"
					+ AccountNumber + "' and Account Status '" + AccountStatus + "' in this page are '" + count + "'");
			// Verify the account

			test_steps.add("Verifying Searched Account...");
			accountsLogger.info("Verifying Searched Account...");
			test_steps.add("Account Name...");
			accountsLogger.info("Account Name...");
			Wait.WaitForElement(driver, OR.Searched_AccountName);
			String name = Account.Searched_AccountName.getText();

			Assert.assertEquals(name, AccountName, "  Account Name missmatched  ");

			test_steps.add("Account Number...");
			accountsLogger.info("Account Number...");
			Wait.WaitForElement(driver, OR.Searched_AccountNumber);
			String number = Account.Searched_AccountNumber.getText();
			// AccountNumber="012";
			Assert.assertEquals(number, AccountNumber, "  Account Number mismatched  ");
			test_steps.add("Account Status...");
			accountsLogger.info("Account Status..");
			// AccountStatus="Inactive";
			if (!AccountStatus.equals("ALL")) {
				Wait.WaitForElement(driver, OR.Searched_AccountStatus);
				String status = Account.Searched_AccountStatus.getText();
				Assert.assertEquals(status, AccountStatus, "  Account Status mismatched  ");
			}
			test_steps.add("Account Verified");
			accountsLogger.info("Account Verified");

		}
		return test_steps;

	}

	public void EnterAccountName(WebDriver driver, ExtentTest test, String AccountName) {

		Elements_Accounts Account = new Elements_Accounts(driver);
		Account.Folio_AccountName_Input.sendKeys(AccountName);
		accountsLogger.info("Entering  Account Name Folio");
	}

	public ArrayList<String> TaxExempt(WebDriver driver, String TaxExmpt) {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Accounts Account = new Elements_Accounts(driver);
		Account.TaxExemptCheckBox.click();
		test_steps.add("The Tax Exempt Checkbox is checked");
		Account.TaxExemptID.sendKeys(TaxExmpt);
		test_steps.add("The Tax Exempt Id is : " + TaxExmpt);
		return test_steps;

	}

	public void PrintButtonClick(WebDriver driver) {

		Elements_Accounts Account = new Elements_Accounts(driver);
		Account.PrintButton.click();
		assertTrue(Account.PDF_File.isDisplayed(), "Failed:PDF isn't displayed");

	}

	public void ExportButtonClick(WebDriver driver) {

		Elements_Accounts Account = new Elements_Accounts(driver);
		Account.ExportButton.click();
		// assertTrue(Account.PDF_File.isDisplayed(), "Failed:PDF isn't
		// displayed");

	}

	public void SelectFileCategory(WebDriver driver, String FileCategory) {

		Elements_Accounts Account = new Elements_Accounts(driver);
		// driver.switchTo().frame("popiframe");
		System.out.println("Change Category");
		Select Category = new Select(Account.SelectPDFCategory);
		Category.selectByVisibleText(FileCategory);
		assertTrue(Category.getFirstSelectedOption().getText().equals(FileCategory), "Failed : Category not matched");

	}

	public float GetEndingBalance(WebDriver driver) throws InterruptedException {
		Elements_Accounts Account = new Elements_Accounts(driver);
		Utility.ScrollToElement(Account.Account_EndingBalance, driver);
		String Balance = Account.Account_EndingBalance.getText();
		System.out.print(Balance);
		float EndingBalance = Float.parseFloat(Balance.split(" ")[1]);
		System.out.print(EndingBalance);
		return EndingBalance;
	}

	public void VoidLineItem(WebDriver driver, String Category) throws InterruptedException {
		Elements_Accounts Account = new Elements_Accounts(driver);
		float EndingBalance_Before = GetEndingBalance(driver);
		System.out.print(EndingBalance_Before);
		String LineItem_AmountPath = "//td[contains(@data-bind,'lineitem')]/span[text()='" + Category
				+ "']//ancestor::tr//td[@class='lineitem-amount']/span";

		WebElement LineItem_Amount = driver.findElement(By.xpath(LineItem_AmountPath));
		float LineItemAmount = Float.parseFloat(LineItem_Amount.getText().split(" ")[1]);
		String CheckBox_path = "//td[contains(@data-bind,'lineitem')]/span[text()='" + Category
				+ "']//ancestor::tr//input[@type='checkbox']";
		WebElement CheckBox = driver.findElement(By.xpath(CheckBox_path));
		Utility.ScrollToElement(CheckBox, driver);
		CheckBox.click();
		Wait.explicit_wait_elementToBeClickable(Account.Void, driver);
		Account.Void.click();
		Wait.explicit_wait_visibilityof_webelement(Account.NotesPopup, driver);
		Account.NotesPopup_Note.sendKeys("Void It");
		Utility.ScrollToElement(Account.NotesPopup_Void, driver);
		Account.NotesPopup_Void.click();
		Wait.explicit_wait_visibilityof_webelement(Account.Account_EndingBalance, driver);
		float EndingBalance_After = GetEndingBalance(driver);
		System.out.print(EndingBalance_After);
		assertEquals(EndingBalance_After, (EndingBalance_Before - LineItemAmount),
				"Failed : Ending Balance is not updated");
	}

	public ArrayList<String> ageingPaymentAdvanceDeposit1(WebDriver driver, ExtentTest test1, String AccountType,
			String PaymentType, String CardName, String CCNumber, String CCExpiry, String CCVCode,
			String Authorizationtype, String ChangeAmount, String ChangeAmountValue, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts AccountPayment = new Elements_Accounts(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOf(AccountPayment.Folio_Balance));

		Wait.WaitForElement(driver, OR.Folio_Balance);
		String balance = AccountPayment.Folio_Balance.getText();
		balance = balance.replace("$", "");

		float bal = Float.parseFloat(balance);

		test_steps.add("Before Pay Folio balance : " + balance);
		accountsLogger.info("Before Pay Folio balance : " + balance);

		click_Pay(driver, test1, test_steps);

		// Wait.wait2Second();
		System.out.println("Cardtype:" + PaymentType);
		if (PaymentType.equalsIgnoreCase("MC")) {

			// Wait.explicit_wait_visibilityof_webelement(AccountPayment.Select_Account_paymethod);
			jse.executeScript("arguments[0].click();", AccountPayment.Select_Account_paymethod);

			new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
			test_steps.add("Select payment Method : " + PaymentType);
			accountsLogger.info("Select payment Method : " + PaymentType);

			try {
				String Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();
				test_steps.add("Selected payment Method : " + Type);
				accountsLogger.info("Selected payment Method : " + Type);
				if (Type.contains("Select")) {
					new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
					test_steps.add("Again Select payment Method : " + PaymentType);
					accountsLogger.info("Again Select payment Method : " + PaymentType);
					Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();
					test_steps.add("Selected payment Method : " + Type);
					accountsLogger.info("Selected payment Method : " + Type);
				}
				assertTrue(Type.contains(PaymentType), "Failed : Payment Type is not selected");
			} catch (Exception e) {
				Wait.explicit_wait_visibilityof_webelement(AccountPayment.Select_Account_paymethod, driver);
				new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
				test_steps.add("Select payment Method : " + PaymentType);
				accountsLogger.info("Select payment Method : " + PaymentType);
				String Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();
				test_steps.add("Selected payment Method : " + Type);
				accountsLogger.info("Selected payment Method : " + Type);
				assertTrue(Type.contains(PaymentType), "Failed : Payment Type is not selected");
			}

			// Wait.wait3Second();

			Wait.WaitForElement(driver, OR.Click_Account_Card_info);
			jse.executeScript("arguments[0].click();", AccountPayment.Click_Account_Card_info);

			test_steps.add("Click Card Info");
			accountsLogger.info("Click Card Info");

			Wait.explicit_wait_xpath(OR.Verify_Account_Paymnet_info_popup, driver);
			// Wait.wait2Second();

			Wait.WaitForElement(driver, OR.Enter_Account_Card_Name);
			AccountPayment.Enter_Account_Card_Name.sendKeys(CardName);
			test_steps.add("Enter Card Name :" + CardName);
			accountsLogger.info("Enter Card Name :" + CardName);

			AccountPayment.Enter_CC_Account_Number.sendKeys(CCNumber);
			test_steps.add("Enter Card Number : " + CCNumber);
			accountsLogger.info("Enter Card Number : " + CCNumber);

			AccountPayment.Enter_ExpiryDate_Account.sendKeys(CCExpiry);
			test_steps.add("Enter Expiry date : " + CCExpiry);
			accountsLogger.info("Enter Expiry date : " + CCExpiry);

			AccountPayment.Enter_CCV_Account.sendKeys(CCVCode);
			test_steps.add("Enter CVV : " + CCVCode);
			accountsLogger.info("Enter CVV : " + CCVCode);

			AccountPayment.Click_Ok_Account.click();
			test_steps.add("Click OK");
			accountsLogger.info("Click OK");

			// Wait.wait5Second();
			Wait.WaitForElement(driver, OR.Select_Authorization_Type_Account);

			new Select(AccountPayment.Select_Authorization_Type_Account).selectByVisibleText(Authorizationtype);
			if (ChangeAmount.equalsIgnoreCase("Yes")) {
				/*
				 * ReservationFolio.Change_Amount.clear(); Wait.wait3Second();
				 * ReservationFolio.Change_Amount.sendKeys(ChangeAmountValue);
				 */
				AccountPayment.Enter_Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
				test_steps.add("Enter amount to pay : " + ChangeAmountValue);
				accountsLogger.info("Enter amount to pay : " + ChangeAmountValue);
			} else {
				// System.out.println("Processed complete amount");
			}
			// // Wait.wait3Second();
			if (Authorizationtype.equalsIgnoreCase("Capture")) {
				// Wait.WaitForElement(driver, OR.Click_Process_Account);

				String text;
				Wait.explicit_wait_visibilityof_webelement_350(AccountPayment.Click_Process_Account, driver);
				Utility.ScrollToElement(AccountPayment.Click_Process_Account, driver);
				AccountPayment.Click_Process_Account.click();
				//
				test_steps.add("Click Process");
				accountsLogger.info("Click Process");

				Wait.wait3Second();

				Wait.WaitForElement(driver, OR.Apply_Advance_Deposite);
				Wait.explicit_wait_xpath(OR.Apply_Advance_Deposite, driver);
				AccountPayment.Click_Continue_Adv_Deposite.click();
				test_steps.add("Click Advance Deposit link");
				accountsLogger.info("Click Advance Deposit link");
			}

			// Wait.wait3Second();
			Wait.explicit_wait_xpath(OR.Verify_MC_Grid, driver);
			Wait.WaitForElement(driver, OR.Verify_MC_Grid);
			String GetPaymentMethod = AccountPayment.Account_Payment_Method.getText();
			// System.out.println(GetPaymentMethod + " "+GetPaymentMethod);
			if (GetPaymentMethod.equals(PaymentType)) {
				// System.out.println("Paymnet Success");
			} else {

			}
			Wait.WaitForElement(driver, OR.Click_Account_Pay_Continue);
			AccountPayment.Click_Account_Pay_Continue.click();
			test_steps.add("Click Continue");
			accountsLogger.info("Click Continue");
			String GetMCCard = "";
			int Size = AccountPayment.Get_Line_item_Account_1.size();
			System.out.println("Size:" + Size);
			try {
				jse.executeScript("arguments[0].scrollIntoView();", AccountPayment.Get_Line_item_Account);

				Wait.WaitForElement(driver, OR.Get_Line_item_Account);
				Wait.explicit_wait_xpath(OR.Get_Line_item_Account, driver);
				GetMCCard = AccountPayment.Get_Line_item_Account.getText();

			} catch (Exception e) {
				jse.executeScript("arguments[0].scrollIntoView();",
						AccountPayment.Get_Line_item_Account_1.get(Size - 1));
				GetMCCard = AccountPayment.Get_Line_item_Account_1.get(Size - 1).getText();
			}
			// System.out.println(GetMCCard + " "+GetMCCard);
			if (GetMCCard.equals("Name: MC Account #: XXXX5454 Exp. Date: 08/19")) {
				// System.out.println("Paymnet Success");
			} else {
				// System.out.println("Paymnet Failed");
			}
			String GetBalance = AccountPayment.Verify_Ending_Balance.getText();
			// System.out.println(GetBalance + " "+GetBalance);
			String RemoveCurreny[] = GetBalance.split(" ");
			// System.out.println(RemoveCurreny[1]);
			// if (ChangeAmount.equalsIgnoreCase("No")) {
			// if (RemoveCurreny[1].equals("0.00"))
			// ;
			// } else {
			// // System.out.println("Selected Changed Value");
			// }
			// } else if (PaymentType.equalsIgnoreCase("Cash")) {
			// Wait.WaitForElement(driver, OR.Select_Account_paymethod);
			// new
			// Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
			// test_steps.add("Select Payment type " + PaymentType);
			// accountsLogger.info("Select Payment type " + PaymentType);
			//
			// // new
			// //
			// Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
			// // test_steps.add("Select Payment type " + PaymentType);
			// // accountsLogger.info("Select Payment type " + PaymentType);
			//
			// AccountPayment.Enter_Change_Amount.sendKeys(Keys.chord(Keys.CONTROL,
			// "a"), ChangeAmountValue);
			// test_steps.add("Enter Amount to pay " +
			// ChangeAmountValue);
			// accountsLogger.info("Enter Amount to pay " + ChangeAmountValue);
			//
			// if (AccountType.contains("Corporate")) {
			//
			// Wait.WaitForElement(driver,
			// "//span[contains(text(),'Payment')]/../../../../following-sibling::div/div/div/div/div/div/ul/li/div/button[contains(text(),'Add')]");
			// driver.findElement(By
			// .xpath("//span[contains(text(),'Payment')]/../../../../following-sibling::div/div/div/div/div/div/ul/li/div/button[contains(text(),'Add')]"))
			// .click();
			//
			// test_steps.add("Clicking on Add");
			// accountsLogger.info("Clicking on Add");
			// // Wait.wait3Second();
			//
			// Wait.WaitForElement(driver, OR.Apply_Advance_Deposite);
			// Wait.explicit_wait_xpath(OR.Apply_Advance_Deposite);
			// AccountPayment.Click_Continue_Adv_Deposite.click();
			// test_steps.add("Click Advance Deposit link");
			// accountsLogger.info("Click Advance Deposit link");
			//
			// } else {
			// driver.findElement(By
			// .xpath("//span[contains(text(),'Payment')]/../../../../following-sibling::div[1]/div/div/div/div[1]/div/div[2]/div[2]/div/div/div[2]/div[6]/div/button[contains(text(),'Add')]"))
			// .click();
			// }
			//
			// // Wait.wait3Second();
			// Wait.WaitForElement(driver, OR.Folio_Cash_Continue_Btn);
			//
			// AccountPayment.Folio_Cash_Continue_Btn.click();
			// test_steps.add("Clicking on Continue");
			// accountsLogger.info("Clicking on Continue");
			//
			// }
			//
			// String str = driver
			// .findElement(By.xpath("//span[contains(text(),'Advance
			// Deposit')]/../following-sibling::span/span"))
			// .getText();
			// System.out.println("string is :" + str);
			//
			// str = str.replace("$", "");
			//
			// str = str.trim();
			//
			// float a = Float.parseFloat(str);
			//
			// test_steps.add("Advanced deposit balance " + str);
			// accountsLogger.info("Advanced deposit balance " + str);
			//
			// if (a == Float.parseFloat(ChangeAmountValue)) {
			// test_steps.add("Advanced Deposit Successfull for : " +
			// str);
			// accountsLogger.info("Advanced Deposit Successfull for : " + str);
			//
			// } else {
			// test_steps.add("Advanced Deposit not for : " + str);
			// accountsLogger.info("Advanced Deposit not for : " + str);
			// }
			//
			// Save(driver, test1);
			//
			// String balance1 = AccountPayment.Folio_Balance.getText();
			// balance1 = balance1.replace("$", "");
			//
			// float bal1 = Float.parseFloat(balance1);
			//
			// test_steps.add("After pay Folio balance " + bal1);
			// accountsLogger.info("After pay Folio balance " + bal1);
			//
			// if (bal1 + Float.parseFloat(ChangeAmountValue) == bal) {
			// test_steps.add("Cash Payment " + ChangeAmountValue + "
			// is successful");
			// accountsLogger.info("Cash Payment " + ChangeAmountValue + " is
			// successful");
			// } else {
			// test_steps.add("Cash Payment " + ChangeAmountValue + "
			// is Fail");
			// accountsLogger.info("Cash Payment " + ChangeAmountValue + " is
			// Fail");
			// }

		}
		return test_steps;
	}

	public void ChangeAccountNumber(WebDriver driver, String AccountNumber) throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		String selectedOption = null;
		Wait.WaitForElement(driver, OR.Verify_Account_Type);
		Wait.wait5Second();
		try {
			CreateAccount.New_Account_Number.clear();
			CreateAccount.New_Account_Number.sendKeys(AccountNumber);
			Wait.wait3Second();
			selectedOption = CreateAccount.New_Account_Number.getAttribute("value");
		} catch (Exception e) {

			CreateAccount.New_Account_Number_1.clear();
			CreateAccount.New_Account_Number_1.sendKeys(AccountNumber);
			Wait.wait3Second();
			selectedOption = CreateAccount.New_Account_Number_1.getAttribute("value");
		}

		System.out.println(selectedOption);
		Assert.assertEquals(AccountNumber, selectedOption);

	}

	public void AccountModuleLoad(WebDriver driver, String Accounttype) {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Select_Account_Type);
		Wait.explicit_wait_visibilityof_webelement_150(CreateAccount.Select_Account_Type, driver);
		new Select(CreateAccount.Select_Account_Type).selectByVisibleText(Accounttype);
		CreateAccount.Click_New_Account.click();

		Wait.waitForElementToBeGone(driver, CreateAccount.Account_ModuleLoading, 120);
	}

	public void click_CloseAccount(WebDriver driver, ArrayList<String> test_steps, String account)
			throws InterruptedException {

		String subStr = account.substring(0, 9);
		String close = "//span[contains(text(),'" + subStr + "')]/following-sibling::i";
		Wait.WaitForElement(driver, close);
		Wait.wait1Second();
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(close)), driver);
		driver.findElement(By.xpath(close)).click();
		test_steps.add("Closeing the account");
	}

	public void account_Search(WebDriver driver, ArrayList<String> test_steps, String account)
			throws InterruptedException {
		String accountName = "//input[@data-bind='value: AccountName']";
		String search = "//div[@id='accountSearchFilter']//button[contains(text(),'Search')]";

		Wait.WaitForElement(driver, accountName);
		driver.findElement(By.xpath(accountName)).clear();
		driver.findElement(By.xpath(accountName)).sendKeys(account);
		Wait.WaitForElement(driver, search);
		driver.findElement(By.xpath(search)).click();
		String acc = "//td[3]/a[contains(text(),'" + account + "')]";
		Wait.WaitForElement(driver, acc);
		int count = 0;
		while (true) {
			if (driver.findElement(By.xpath("//div[@id='AccountList']//div[contains(text(),'Account List')]"))
					.isDisplayed()) {
				break;
			} else {
				Wait.wait2Second();
				count++;
			}
			if (count == 15) {
				break;
			}
		}

		Wait.wait5Second();
		Wait.explicit_wait_xpath(acc, driver);
		driver.findElement(By.xpath(acc)).click();
		test_steps.add("Opening the account : " + account);
		String verifyopened = "//div[@data-bind='foreach: AccountDetails']//span[contains(text(),'" + account + "')]";
		Wait.WaitForElement(driver, verifyopened);
	}

	public void verify_GiftAccountFolio(WebDriver driver, ArrayList<String> test_steps, String reservation) {
		String folio = "//div[@class='portlet-body ng_ac_tabs']//a[contains(text(),'Folio')]";
		Wait.WaitForElement(driver, folio);
		driver.findElement(By.xpath(folio)).click();
		test_steps.add("Opening the account Folio");

		String res = "//a[contains(text(),'Gift Certificate')]";
		Wait.WaitForElement(driver, res);
		test_steps.add("Reservation Payment line item found in account folio");
		String bal = "(//a[contains(text(),'" + reservation + "')]/../following-sibling::td[3]/span)[2]";
		String balance;

		if (driver
				.findElements(
						By.xpath("(//a[contains(text(),'Gift Certificate')]/../following-sibling::td[3]/span)[2]"))
				.size() > 0) {
			Wait.WaitForElement(driver, bal);
			balance = driver.findElement(By.xpath(bal)).getText();
			test_steps.add("Reservation payment found in gift certificate folio");
		} else {
			bal = "(//a[contains(text(),'Gift Certificate')]/../following-sibling::td[3]/span)";
			Wait.WaitForElement(driver, bal);
			balance = driver.findElement(By.xpath(bal)).getText();
			test_steps.add("Reservation payment found in gift certificate folio");
		}

	}

	public void click_reservationTab(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		String res = "//a[contains(text(),'Reservations')]";
		Wait.WaitForElement(driver, res);
		driver.findElement(By.xpath(res)).click();
		test_steps.add("Click on reservation tab in account");
	}

	public void verify_reservationinAccpountResewrvation(WebDriver driver, ArrayList<String> test_steps,
			String reservation) {
		String search = "(//button[@id='Button2'])[3]";
		Wait.WaitForElement(driver, search);
		driver.findElement(By.xpath(search)).click();
		test_steps.add("Click on search in reservation tab in account");

		String res = "(//span[contains(text(),'" + reservation + "')])[1]";
		Wait.WaitForElement(driver, res);
		Wait.explicit_wait_xpath(res, driver);
		test_steps.add("Reservation found in account reservation tab");
	}

	public void verify_reservationinAccpountResewrvationTab(WebDriver driver, ArrayList<String> test_steps,
			String reservation) {
		String search = "(//button[@id='Button2'])[3]";
		Wait.WaitForElement(driver, search);
		driver.findElement(By.xpath(search)).click();
		test_steps.add("Click on search in reservation tab in account");

		String res = "(//span[contains(text(),'" + reservation + "')])[2]";
		Wait.WaitForElement(driver, res);
		Wait.explicit_wait_xpath(res, driver);
		test_steps.add("Reservation found in account reservation tab");
	}

	public void associateRooms(WebDriver driver, ArrayList<String> test_steps, String roomclass)
			throws InterruptedException {
		String associate = "//button[contains(text(),'Associate Rooms')]";
		Wait.WaitForElement(driver, associate);
		driver.findElement(By.xpath(associate)).click();
		test_steps.add("Click on associate rooms");

		String roompicker = "//h4[contains(text(),'Room Picker')]";
		Wait.WaitForElement(driver, roompicker);

		String property = "(//label[contains(text(),'Property:')]/following-sibling::div/select)[4]";
		Wait.WaitForElement(driver, property);
		new Select(driver.findElement(By.xpath(property))).selectByIndex(1);

		String roomclassname = "//label[contains(text(),'Room Class:')]/following-sibling::div/select";
		Wait.WaitForElement(driver, roomclassname);
		new Select(driver.findElement(By.xpath(roomclassname))).selectByVisibleText(roomclass.trim());
		test_steps.add("selected room class : " + roomclass);

		String go = "(//button[contains(text(),'Go')])[2]";
		Wait.WaitForElement(driver, go);
		driver.findElement(By.xpath(go)).click();
		test_steps.add("Click on go");

		String input = "//input[@data-bind='checked: select, disable: Associated']";
		Wait.WaitForElement(driver, input);
		driver.findElement(By.xpath(input)).click();
		test_steps.add("selecting room");

		int count = driver.findElements(By.xpath("//button[contains(text(),'Select')]")).size();

		String select = "(//button[contains(text(),'Select')])[" + count + "]";
		Wait.WaitForElement(driver, select);
		driver.findElement(By.xpath(select)).click();
		test_steps.add("clicking on select");
		Wait.wait5Second();
	}

	public String get_FilioBalance(WebDriver driver, ExtentTest test) {

		String bal = "//label[.='Ending Balance: ']//following-sibling::span[@class='pamt']";
		Wait.WaitForElement(driver, bal);
		return driver.findElement(By.xpath(bal)).getText();

	}

	public void verify_PaymentWithLineItem(WebDriver driver, ArrayList test_steps, String before, String after,
			String resbalance) {

		before = before.replace("$", "");
		before = before.trim();
		Double bf = Double.parseDouble(before);

		after = after.replace("$", "");
		after = after.trim();
		Double af = Double.parseDouble(after);

		resbalance = resbalance.replace("$", "");
		resbalance = resbalance.trim();
		Double rf = Double.parseDouble(resbalance);

		if (Math.round((bf + rf) * 100D) / 100D == af) {
			accountsLogger.info("Paymnet amont is successful for reservation from account folio");
			test_steps.add("Paymnet amont is successful for reservation from account folio");
		} else {
			accountsLogger.info("Paymnet amont is not successful for reservation from account folio");
			test_steps.add("Paymnet amont is not successful for reservation from account folio");
		}
	}

	public void AccountSatusInActive(WebDriver driver) throws InterruptedException {
		Elements_Accounts acc = new Elements_Accounts(driver);
		try {
			Wait.explicit_wait_visibilityof_webelement(acc.Account_Status_Active, driver);
			new Select(acc.Account_Status_Active).selectByVisibleText("Inactive");
		} catch (Exception e) {
			Wait.wait3Second();
			Wait.explicit_wait_visibilityof_webelement(acc.Account_Status_Active, driver);
			new Select(acc.Account_Status_Active).selectByVisibleText("Inactive");
		}
	}

	public void verify_AccountResservationPayment(WebDriver driver, ArrayList<String> test_steps, String res) {
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		String payment = "//a[contains(text(),'Res #" + res + "')]";
		Wait.WaitForElement(driver, payment);
		test_steps.add("Reservation payment found in account folio");
	}

	public void verifyAccountAndResBalance(WebDriver driver, ArrayList test_steps, String before, String after) {
		before = before.replace("$", "");
		before = before.trim();

		after = after.replace("$", "");
		after = after.replace("(", "");
		after = after.replace(")", "");
		after = after.trim();
		System.out.println(after);
		System.out.println(before);

		if (before.equalsIgnoreCase(after)) {
			test_steps.add("Account folio balance and reservation balance are same : " + after);
		} else {
			test_steps.add("Account folio balance and reservation balance are not same");
		}
	}

	public ArrayList<String> ageingPayment_Advance(WebDriver driver, ExtentTest test1, String AccountType,
			String PaymentType, String CardName, String CCNumber, String CCExpiry, String CCVCode,
			String Authorizationtype, String ChangeAmount, String ChangeAmountValue, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Accounts AccountPayment = new Elements_Accounts(driver);
		Wait.wait2Second();

		Wait.explicit_wait_visibilityof_webelement_120(AccountPayment.AccountPyamentPopup, driver);
		assertEquals(AccountPayment.AccountPyamentPopup.getText(), "Payment Details",
				"payment popup is not displaying");

		Wait.explicit_wait_visibilityof_webelement_120(AccountPayment.Enter_Change_Amount, driver);
		Utility.ScrollToElement(AccountPayment.Enter_Change_Amount, driver);
		AccountPayment.Enter_Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
		test_steps.add("Enter Amount to pay " + ChangeAmountValue);
		accountsLogger.info("Enter Amount to pay " + ChangeAmountValue);

		Wait.explicit_wait_visibilityof_webelement_120(AccountPayment.Select_Account_paymethod, driver);
		Utility.ScrollToElement(AccountPayment.Select_Account_paymethod, driver);
		new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
		test_steps.add("Select Payment type " + PaymentType);
		accountsLogger.info("Select Payment type " + PaymentType);

		Wait.explicit_wait_visibilityof_webelement_120(AccountPayment.Payment_Add, driver);
		Utility.ScrollToElement(AccountPayment.Payment_Add, driver);
		AccountPayment.Payment_Add.click();
		test_steps.add("Click on Add Button");
		accountsLogger.info("Clicking on Add Button");

		Wait.wait3Second();
		Wait.explicit_wait_visibilityof_webelement_120(AccountPayment.Click_Continue_Adv_Deposite, driver);
		Utility.ScrollToElement(AccountPayment.Click_Continue_Adv_Deposite, driver);
		AccountPayment.Click_Continue_Adv_Deposite.click();
		test_steps.add("Click on Continue Button of Advance Leadger Account");
		accountsLogger.info("Click on Continue Button of Advance Leadger Account");

		Wait.explicit_wait_visibilityof_webelement_150(AccountPayment.Click_Account_Pay_Continue, driver);
		Utility.ScrollToElement(AccountPayment.Click_Account_Pay_Continue, driver);
		AccountPayment.Click_Account_Pay_Continue.click();
		test_steps.add("Click on Account_Pay_Continue Button");
		accountsLogger.info("Click on Account_Pay_Continue Button");

		Wait.WaitForElement(driver, OR.Account_save);
		Wait.explicit_wait_visibilityof_webelement_120(AccountPayment.Account_save, driver);

		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.elementToBeClickable(AccountPayment.Account_save));
		AccountPayment.Account_save.click();

		test_steps.add("Click on Save Button");
		accountsLogger.info("Click on Save Button");

		try {
			Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
			Wait.explicit_wait_visibilityof_webelement_120(AccountPayment.Toaster_Message, driver);
			AccountPayment.Toaster_Message.click();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return test_steps;
	}

	public void VerfiyLineItem(WebDriver driver, String Category, String Amount) throws InterruptedException {

		String amount = "//span[text()='" + Category + "']//..//following-sibling::td[@class='lineitem-amount']//span";
		String category = "//span[text()='" + Category + "']";
		String tax = "//span[text()='" + Category + "']//..//following-sibling::td[@class='lineitem-tax']//span";

		WebElement element_category = driver.findElement(By.xpath(category));
		WebElement element_amount = driver.findElement(By.xpath(amount));
		WebElement element_tax = driver.findElement(By.xpath(tax));
		// WebElement element_Balance = driver.findElement(By.xpath(balance));

		Wait.explicit_wait_visibilityof_webelement(element_category, driver);
		Utility.ScrollToElement(element_category, driver);

		System.out.println(element_category.getText());
		assertEquals(element_category.getText(), Category, "Category does not add");

		double enter_amount = Double.parseDouble(Amount);
		String split_tax[] = element_tax.getText().split(" ");
		double get_tax = Double.parseDouble(split_tax[1]);
		System.out.println("get : " + get_tax);
		System.out.println("enter : " + enter_amount);

		float total_amount = (float) (enter_amount + get_tax);
		System.out.println("$ " + total_amount);
		System.out.println("amount: " + element_amount.getText());
		Amount = String.format("%.2f", total_amount);
		System.out.println(Amount);
		assertEquals(element_amount.getText(), "$ " + Amount, "Amount does not add");

		// String balance = " //(//label[text()='Balance:
		// ']//..//span[contains(@data-bind,'showInnroadCurrency:')])[1]";

	}

	public String VerifyPayments(WebDriver driver, String Amount) throws InterruptedException {

		Elements_Accounts elements_Accounts = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_Accounts.FolioPayments, driver);
		Utility.ScrollToElement(elements_Accounts.FolioPayments, driver);
		assertEquals(elements_Accounts.FolioPayments.getText(), "$ " + Amount + ".00");
		return elements_Accounts.FolioPayments.getText();

	}

	public String AdvanceDepositBalance(WebDriver driver) throws InterruptedException {

		Elements_Accounts elements_Accounts = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_Accounts.FolioPayments, driver);
		Wait.explicit_wait_visibilityof_webelement(elements_Accounts.AdvanceFolioBalance, driver);
		Utility.ScrollToElement(elements_Accounts.AdvanceFolioBalance, driver);

		System.out.println(elements_Accounts.AdvanceFolioBalance.getText());
		String str[] = elements_Accounts.AdvanceFolioBalance.getText().split(" ");
		System.out.println(str[1]);
		return str[1];
	}

	public void VerifyAdDepositBalance(WebDriver driver, String BeforeValue, String AfetrValue, String PayAmount)
			throws InterruptedException {

		float afterPayment = Float.parseFloat(AfetrValue);
		float payamount = Float.parseFloat(PayAmount);
		float before_value = Float.parseFloat(BeforeValue);
		float remainingAmount = before_value - payamount;
		assertEquals(afterPayment, remainingAmount, "payment is not matching after individual pay");

	}

	public ArrayList<String> addLineItems(WebDriver driver, String Category, String Amount,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Accounts CreateAccountlineitem = new Elements_Accounts(driver);

		Wait.WaitForElement(driver, OR.Account_Add);
		Utility.ScrollToElement(CreateAccountlineitem.Account_Add, driver);
		CreateAccountlineitem.Account_Add.click();
		test_steps.add("Click Add Line Items");
		Wait.WaitForElement(driver, OR.Select_Property_lineitem);
		new Select(CreateAccountlineitem.Select_Property_lineitem).selectByIndex(1);

		Wait.explicit_wait_visibilityof_webelement(CreateAccountlineitem.Select_Line_item_Category, driver);
		// new
		// Select(CreateAccountlineitem.Select_Line_item_Category).selectByIndex(1);

		// select category here
		new Select(CreateAccountlineitem.Select_Line_item_Category).selectByVisibleText(Category);
		String Cat1 = new Select(CreateAccountlineitem.Select_Line_item_Category).getFirstSelectedOption().getText();
		test_steps.add("Selected category : " + Cat1);
		accountsLogger.info("Selected category : " + Cat1);

		CreateAccountlineitem.Enter_Line_item_Amount.clear();
		CreateAccountlineitem.Enter_Line_item_Amount.sendKeys(Amount);
		test_steps.add("Enter amount : " + Amount);
		accountsLogger.info("Enter amount : " + Amount);

		return test_steps;

	}

	public ArrayList<String> Advance_Deposit_Balance(WebDriver driver, ExtentTest test1, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Accounts AccountPayment = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Advance_Deposit_Balance);
		Utility.ScrollToElement(AccountPayment.Advance_Deposit_Balance, driver);
		assertEquals(AccountPayment.Advance_Deposit_Balance.isDisplayed(), true);
		AccountPayment.Advance_Deposit_Balance.click();
		test_steps.add("Click Advance Deposit Balance");
		accountsLogger.info("Click Advance Deposit Balance");
		return test_steps;
	}

	public ArrayList<String> PaymentAgainst_IndividualLineItem(WebDriver driver, String Category, String Amount,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Accounts AccountPayment = new Elements_Accounts(driver);

		Wait.explicit_wait_visibilityof_webelement(AccountPayment.AccountPyamentPopup, driver);
		assertEquals(AccountPayment.AccountPyamentPopup.getText(), "Payment Details",
				"payment popup is not displaying");

		String path_VerifyLineItem = "//div[@id='divOutstandingItems']//input[contains(@data-bind,'click: $parent.checkItem')]";
		List<WebElement> verifyLineItem = driver.findElements(By.xpath(path_VerifyLineItem));
		assertEquals(verifyLineItem.size(), 2, "Line item size not qual to 2");
		test_steps.add("Verify line item size and size equal :" + verifyLineItem.size());

		String path = "//div[@id='divOutstandingItems']//span[text()='" + Category
				+ "']//..//..//td//input[contains(@data-bind,'click: $parent.checkItem')]";
		WebElement outside_LineItem = driver.findElement(By.xpath(path));
		Wait.explicit_wait_visibilityof_webelement(outside_LineItem, driver);
		Utility.ScrollToElement(outside_LineItem, driver);
		outside_LineItem.click();
		test_steps.add("Clikc on outside line item");
		String path_PayAmount = "//div[@id='divOutstandingItems']//span[text()='" + Category
				+ "']//..//..//td//input[contains(@data-bind,'value: PayAmount')]";
		WebElement enter_PayAmount = driver.findElement(By.xpath(path_PayAmount));
		enter_PayAmount.clear();
		enter_PayAmount.sendKeys(Amount);
		test_steps.add("Enter pay ammount for out siding line item : " + Amount);
		accountsLogger.info("Enter pay ammount for out siding line item : " + Amount);

		Wait.wait1Second();
		AccountPayment.Payment_Add.click();
		test_steps.add("Clicking Payment_Add Button");
		accountsLogger.info("Clicking on Payment_Add Button");

		Wait.wait3Second();
		AccountPayment.Click_Continue_Adv_Deposite.click();
		test_steps.add("Clicking on Continue_Adv_Deposite Button");
		accountsLogger.info("Clicking on Continue");

		Wait.wait3Second();
		Wait.WaitForElement(driver, OR.Click_Account_Pay_Continue);
		Wait.explicit_wait_visibilityof_webelement(AccountPayment.Click_Account_Pay_Continue, driver);
		AccountPayment.Click_Account_Pay_Continue.click();
		test_steps.add("Clicking on Account_Pay_Continue");
		accountsLogger.info("Clicking on Account_Pay_Continue");

		Wait.wait3Second();
		Wait.WaitForElement(driver, OR.Account_save);
		Wait.explicit_wait_visibilityof_webelement(AccountPayment.Account_save, driver);
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.elementToBeClickable(AccountPayment.Account_save));
		AccountPayment.Account_save.click();
		test_steps.add("Click on Save");
		accountsLogger.info("Click on Save");
		try {
			Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
			Wait.explicit_wait_visibilityof_webelement(AccountPayment.Toaster_Message, driver);
			AccountPayment.Toaster_Message.click();
		} catch (Exception e) {

		}
		return test_steps;

	}

	public void Verify_IndividualLineItem(WebDriver driver, String Category) throws InterruptedException {

		String path = "//span[text()='" + Category
				+ "']//..//following-sibling::td[@class='text-center lineitem-aging']//img[@title='Partially Paid']";
		List<WebElement> element = driver.findElements(By.xpath(path));
		System.out.println("Partially Paid" + element.size());
		assertEquals(element.get(1).isDisplayed(), true, "element is not display");
	}

	public void Verify_PaymentIndividualLineItem(WebDriver driver, String Category) throws InterruptedException {
		String path = "//span[text()='" + Category
				+ "']//..//following-sibling::td[@class='text-center lineitem-aging']//img[@title='Fully Paid']";
		WebElement element = driver.findElement(By.xpath(path));
		Wait.explicit_wait_visibilityof_webelement(element, driver);
		Utility.ScrollToElement(element, driver);
		System.out.print("Fully Paid Successfully \n");
		assertEquals(element.findElement(By.xpath(path)).isDisplayed(), true, "element is not display");
	}

	public ArrayList<String> AutoPaymentAgainst_IndividualLineItem(WebDriver driver, String Category, String Amount,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Accounts AccountPayment = new Elements_Accounts(driver);

		Wait.explicit_wait_visibilityof_webelement(AccountPayment.AccountPyamentPopup, driver);
		assertEquals(AccountPayment.AccountPyamentPopup.getText(), "Payment Details",
				"payment popup is not displaying");

		Wait.wait3Second();
		assertTrue(AccountPayment.Account_AutoApply.isDisplayed(), "AutoPlay element is not displayed");
		AccountPayment.Account_AutoApply.click();
		test_steps.add("Clicking on AutoApply");
		accountsLogger.info("Clicking on AutoApply");

		Wait.wait1Second();
		AccountPayment.Payment_Add.click();
		test_steps.add("Clicking on Add");
		accountsLogger.info("Clicking on Add");

		Wait.wait3Second();
		AccountPayment.Click_Continue_Adv_Deposite.click();
		test_steps.add("Clicking on Continue");
		accountsLogger.info("Clicking on Continue");

		Wait.wait3Second();
		Wait.explicit_wait_visibilityof_webelement(AccountPayment.Click_Account_Pay_Continue, driver);
		AccountPayment.Click_Account_Pay_Continue.click();
		test_steps.add("Clicking on Continue");
		accountsLogger.info("Clicking on Continue");

		Wait.wait3Second();
		Wait.explicit_wait_visibilityof_webelement(AccountPayment.Account_save, driver);
		AccountPayment.Account_save.click();
		Wait.explicit_wait_visibilityof_webelement(AccountPayment.Toaster_Message, driver);
		AccountPayment.Toaster_Message.click();
		test_steps.add("Clicking on Save");
		accountsLogger.info("Clicking on Save");

		return test_steps;

	}

	public String AdvanceDepositBalnce(WebDriver driver) throws InterruptedException {
		Elements_Accounts elements_Accounts = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_Accounts.FolioPayments, driver);
		Utility.ScrollToElement(elements_Accounts.AdvanceFolioBalance, driver);

		System.out.println(elements_Accounts.AdvanceFolioBalance.getText());
		String str[] = elements_Accounts.AdvanceFolioBalance.getText().split(" ");
		System.out.println(str[1]);
		return str[1];
	}

	public ArrayList<String> ageingPaymentAdvanceDeposit_Pay(WebDriver driver, ExtentTest test1, String AccountType,
			String PaymentType, String CardName, String CCNumber, String CCExpiry, String CCVCode,
			String Authorizationtype, String ChangeAmount, String ChangeAmountValue, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts AccountPayment = new Elements_Accounts(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOf(AccountPayment.Folio_Balance));

		click_Pay(driver, test1, test_steps);

		// Wait.wait2Second();
		System.out.println("Cardtype:" + PaymentType);
		if (PaymentType.equalsIgnoreCase("MC")) {

			Wait.explicit_wait_visibilityof_webelement_150(AccountPayment.Select_Account_paymethod, driver);
			Wait.wait2Second();

			new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
			test_steps.add("Select payment Method : " + PaymentType);
			accountsLogger.info("Select payment Method : " + PaymentType);

			try {
				String Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();
				test_steps.add("Selected payment Method : " + Type);
				accountsLogger.info("Selected payment Method : " + Type);
				if (Type.contains("Select")) {
					new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
					test_steps.add("Again Select payment Method : " + PaymentType);
					accountsLogger.info("Again Select payment Method : " + PaymentType);
					Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();
					test_steps.add("Selected payment Method : " + Type);
					accountsLogger.info("Selected payment Method : " + Type);
				}
			} catch (Exception e) {
				Wait.explicit_wait_visibilityof_webelement(AccountPayment.Select_Account_paymethod, driver);
				new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
				test_steps.add("Select payment Method : " + PaymentType);
				accountsLogger.info("Select payment Method : " + PaymentType);
				String Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();
				test_steps.add("Selected payment Method : " + Type);
				accountsLogger.info("Selected payment Method : " + Type);
				assertTrue(Type.contains(PaymentType), "Failed : Payment Type is not selected");
			}
			// Wait.wait3Second();
			try {
				Wait.explicit_wait_visibilityof_webelement(AccountPayment.Click_Account_Card_info, driver);
				AccountPayment.Click_Account_Card_info.click();
				Wait.explicit_wait_visibilityof_webelement(AccountPayment.Enter_Account_Card_Name, driver);
			} catch (Exception e) {
				Wait.WaitForElement(driver, OR.Click_Account_Card_info);
				jse.executeScript("arguments[0].click();", AccountPayment.Click_Account_Card_info);
				Wait.explicit_wait_visibilityof_webelement(AccountPayment.Enter_Account_Card_Name, driver);
			}

			test_steps.add("Click Card Info");
			accountsLogger.info("Click Card Info");

			Wait.explicit_wait_xpath(OR.Verify_Account_Paymnet_info_popup, driver);
			Wait.WaitForElement(driver, OR.Enter_Account_Card_Name);

			AccountPayment.Enter_Account_Card_Name.sendKeys(CardName);
			test_steps.add("Enter Card Name :" + CardName);
			accountsLogger.info("Enter Card Name :" + CardName);

			AccountPayment.Enter_CC_Account_Number.sendKeys(CCNumber);
			test_steps.add("Enter Card Number : " + CCNumber);
			accountsLogger.info("Enter Card Number : " + CCNumber);

			AccountPayment.Enter_ExpiryDate_Account.sendKeys(CCExpiry);
			test_steps.add("Enter Expiry date : " + CCExpiry);
			accountsLogger.info("Enter Expiry date : " + CCExpiry);

			AccountPayment.Enter_CCV_Account.sendKeys(CCVCode);
			test_steps.add("Enter CVV : " + CCVCode);
			accountsLogger.info("Enter CVV : " + CCVCode);

			AccountPayment.Click_Ok_Account.click();
			test_steps.add("Click OK");
			accountsLogger.info("Click OK");

			Wait.waitForElementToBeGone(driver, AccountPayment.Click_Ok_Account, 60);

			String Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();
			test_steps.add("Selected payment Method : " + Type);
			accountsLogger.info("Selected payment Method : " + Type);
			if (Type.contains("Select")) {

				new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
				test_steps.add("Again Select payment Method : " + PaymentType);

				accountsLogger.info("Again Select payment Method : " + PaymentType);
				Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();

				test_steps.add("Selected payment Method : " + Type);
				accountsLogger.info("Selected payment Method : " + Type);
			}
			assertTrue(Type.contains(PaymentType), "Failed : Payment Type is not selected");
			Wait.WaitForElement(driver, OR.Select_Authorization_Type_Account);
			Wait.explicit_wait_visibilityof_webelement(AccountPayment.Select_Authorization_Type_Account, driver);
			new Select(AccountPayment.Select_Authorization_Type_Account).selectByVisibleText(Authorizationtype);
			if (ChangeAmount.equalsIgnoreCase("Yes")) {
				AccountPayment.Enter_Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
				test_steps.add("Enter amount to pay : " + ChangeAmountValue);
				accountsLogger.info("Enter amount to pay : " + ChangeAmountValue);
			}
			Wait.WaitForElement(driver, OR.Click_Process_Account);

			Wait.explicit_wait_visibilityof_webelement_350(AccountPayment.Click_Process_Account, driver);
			Utility.ScrollToElement(AccountPayment.Click_Process_Account, driver);
			AccountPayment.Click_Process_Account.click();

			test_steps.add("Click Process");
			accountsLogger.info("Click Process");

			Wait.WaitForElement(driver, OR.Apply_Advance_Deposite);
			Wait.explicit_wait_xpath(OR.Apply_Advance_Deposite, driver);
			AccountPayment.Click_Continue_Adv_Deposite.click();
			test_steps.add("Click Advance Deposit link");
			accountsLogger.info("Click Advance Deposit link");

			Wait.explicit_wait_xpath(OR.Verify_MC_Grid, driver);
			Wait.WaitForElement(driver, OR.Verify_MC_Grid);
			Wait.WaitForElement(driver, OR.Click_Account_Pay_Continue);
			AccountPayment.Click_Account_Pay_Continue.click();

			test_steps.add("Click Continue");
			accountsLogger.info("Click Continue");
		}

		Save1(driver, test1, test_steps);

		test_steps = addLineItems1(driver, test1, test_steps);
		// test_steps.addAll(test_steps);

		Wait.explicit_wait_visibilityof_webelement_350(AccountPayment.Advance_Deposit_Click, driver);
		Utility.ScrollToElement(AccountPayment.Advance_Deposit_Click, driver);
		AccountPayment.Advance_Deposit_Click.click();
		test_steps.add("Apply Advance Deposit");
		accountsLogger.info("Apply Advance Deposit");

		Wait.wait3Second();
		AccountPayment.Deposit_Auto_apply.get(0).click();
		test_steps.add("Clicking on Auto Apply");
		accountsLogger.info("Clicking on Auto Apply");

		Wait.wait3Second();
		AccountPayment.Deposit_Auto_applyAdd.get(0).click();
		test_steps.add("Clicking on Add");
		accountsLogger.info("Clicking on Add");

		Wait.wait3Second();
		AccountPayment.Deposit_Auto_applyContinue.get(0).click();
		test_steps.add("Clicking on Continue");
		accountsLogger.info("Clicking on Continue");

		Save1(driver, test1, test_steps);
		Wait.wait5Second();
		Wait.WaitForElement(driver, OR.Deposit_paid);
		// accountsLogger.info("Deposit_paid.getTagName()::"+AccountPayment.Deposit_paid.getTagName());
		if (AccountPayment.Deposit_paid.isDisplayed() || AccountPayment.Deposit_paid.getTagName().equals("img")) {
			test_steps.add("Advance Deposit Paid Sucessfull");
			accountsLogger.info("Advance Deposit Paid Sucessfull ");
		} else {
			test_steps.add("Advance Deposit Unsucessfull");
			accountsLogger.info("Advance Deposit Unsucessfull ");

		}

		Wait.wait3Second();
		return test_steps;
	}

	public ArrayList<String> DeleteAccount_AccNo(WebDriver driver, ExtentTest test, ArrayList<String> test_steps,
			String AccountNumber, Elements_Accounts CreateAccount) throws InterruptedException {

		accountsLogger.info("AccountNumberDeleted : " + AccountNumber);
		CreateAccount.Search_Accounts_Button.click();
		Wait.WaitForElement(driver, OR.Search_Account_Result);
		Wait.wait2Second();
		CreateAccount.Search_Account_Checkbox.click();
		Wait.wait2Second();
		CreateAccount.Account_Delete_Button.click();

		return test_steps;

	}

	public ArrayList<String> Save1(WebDriver driver, ExtentTest test1, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		// Wait.WaitForElement(driver, OR.Account_save);
		Wait.explicit_wait_visibilityof_webelement_150(CreateAccount.Account_save, driver);

		Utility.ScrollToElement(CreateAccount.Account_save, driver);
		CreateAccount.Account_save.click();
		test_steps.add("Click on Save");
		accountsLogger.info("Click on Save");

		return test_steps;

	}

	public ArrayList<String> addLineItems1(WebDriver driver, ExtentTest test1, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts CreateAccountlineitem = new Elements_Accounts(driver);

		Wait.WaitForElement(driver, OR.Account_Add);
		Utility.ScrollToElement(CreateAccountlineitem.Account_Add, driver);
		CreateAccountlineitem.Account_Add.click();

		Wait.WaitForElement(driver, OR.Select_Property_lineitem);
		new Select(CreateAccountlineitem.Select_Property_lineitem).selectByIndex(1);
		// Wait.wait5Second();
		Wait.WaitForElement(driver, OR.Select_Line_item_Category);
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("arguments[0].scrollIntoView();", CreateAccountlineitem.Select_Line_item_Category);

		new Select(CreateAccountlineitem.Select_Line_item_Category).selectByIndex(1);

		String Cat1 = new Select(CreateAccountlineitem.Select_Line_item_Category).getFirstSelectedOption().getText();
		test_steps.add("Selected category : " + Cat1);
		accountsLogger.info("Selected category : " + Cat1);

		CreateAccountlineitem.Enter_Line_item_Amount.clear();
		// Wait.wait2Second();
		Wait.WaitForElement(driver, OR.Enter_Line_item_Amount);
		CreateAccountlineitem.Enter_Line_item_Amount.sendKeys("100");
		test_steps.add("Enter amount 100");
		accountsLogger.info("Enter amount 100");
		Commit(driver, test1);

		Save1(driver, test1, test_steps);
		return test_steps;

	}

	public ArrayList<String> AccountPage_Validation(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Accounts account = new Elements_Accounts(driver);
		assertTrue(!account.Account_save.isEnabled(), "Failed: Account Page Save Button is Enabled At Start");
		assertTrue(!account.Account_Save_Clsoe.isEnabled(),
				"Failed: Account Page Save & Close Button is Enabled At Start");
		assertTrue(!account.AccountsPage_Reset_Btn.isEnabled(),
				"Failed: Account Page Reset Button is Enabled At Start");
		assertTrue(!account.NewReservation_Button.isEnabled(),
				"Failed: Account Page New Reservation Button is Enabled At Start");
		test_steps.add("Validate : Account Page");
		return test_steps;

	}

	public ArrayList<String> AccountPage_AccountName_Validation(WebDriver driver, String AccountName)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Accounts account = new Elements_Accounts(driver);
		account.Account_CorpAccountName.sendKeys(AccountName);
		account.Account_save.click();
		assertTrue(account.Account_save.isEnabled(), "Failed: Account Page Save Button isn't Enabled");
		assertTrue(account.Account_Save_Clsoe.isEnabled(), "Failed: Account Page Save & Close Button isn't Enabled");
		assertTrue(account.AccountsPage_Reset_Btn.isEnabled(), "Failed: Account Page Reset Button isn't Enabled");
		assertTrue(!account.NewReservation_Button.isEnabled(),
				"Failed: Account Page New Reservation Button is Enabled At Start");
		// MailingInformation Validation asserts
		Wait.WaitForElement(driver, OR.AccountPage_MailingInfo_FirstName_ValidationMessage);
		assertTrue(account.AccountPage_MailingInfo_FirstName_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info FirstName ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PhoneNumber_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PhoneNumber ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_AddressLine_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info AddressLine ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_City_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info City ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_State_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info State ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PostalCode_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PostalCode ValidationMessage didn't display");

		// Billing Information Validation asserts

		assertTrue(account.AccountPage_BillingInfo_FirstName_ValidationMessage.isDisplayed(),
				"Failed: Account Page Billing Info FirstName ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PhoneNumber_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PhoneNumber ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_AddressLine_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info AddressLine ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_City_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info City ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_State_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info State ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PostalCode_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PostalCode ValidationMessage didn't display");
		test_steps.add("Validate : All Input fields Validated after adding AccountName");
		return test_steps;

	}

	public ArrayList<String> AccountPage_FirstName_Validation(WebDriver driver, String FirstName)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Accounts account = new Elements_Accounts(driver);
		account.Account_Enter_First_Name.sendKeys(FirstName);
		account.Account_save.click();

		assertTrue(account.Account_save.isEnabled(), "Failed: Account Page Save Button isn't Enabled");
		assertTrue(account.Account_Save_Clsoe.isEnabled(), "Failed: Account Page Save & Close Button isn't Enabled");
		assertTrue(account.AccountsPage_Reset_Btn.isEnabled(), "Failed: Account Page Reset Button isn't Enabled");
		assertTrue(!account.NewReservation_Button.isEnabled(),
				"Failed: Account Page New Reservation Button is Enabled At Start");
		// MailingInformation Validation asserts
		Wait.WaitForElement(driver, OR.AccountPage_AccountName_ValidationMessage);

		assertTrue(account.AccountPage_AccountName_ValidationMessage.isDisplayed(),
				"Failed: Account Page AccountName ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PhoneNumber_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PhoneNumber ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_AddressLine_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info AddressLine ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_City_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info City ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_State_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info State ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PostalCode_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PostalCode ValidationMessage didn't display");

		// Billing Information Validation asserts

		assertTrue(account.AccountPage_MailingInfo_PhoneNumber_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PhoneNumber ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_AddressLine_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info AddressLine ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_City_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info City ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_State_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info State ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PostalCode_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PostalCode ValidationMessage didn't display");
		test_steps.add("Validate : All Input fields Validated after adding FirstName");
		return test_steps;

	}

	public ArrayList<String> AccountPage_PhoneNumber_Validation(WebDriver driver, String PhoneNumber)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Accounts account = new Elements_Accounts(driver);
		account.Account_Phone_number.clear();
		account.Account_Phone_number.sendKeys(PhoneNumber);
		new Select(account.Select_Market_Segment).selectByIndex(1);
		account.Account_save.click();

		assertTrue(account.Account_save.isEnabled(), "Failed: Account Page Save Button isn't Enabled");
		assertTrue(account.Account_Save_Clsoe.isEnabled(), "Failed: Account Page Save & Close Button isn't Enabled");
		assertTrue(account.AccountsPage_Reset_Btn.isEnabled(), "Failed: Account Page Reset Button isn't Enabled");
		assertTrue(!account.NewReservation_Button.isEnabled(),
				"Failed: Account Page New Reservation Button is Enabled At Start");
		// MailingInformation Validation asserts
		//
		Wait.WaitForElement(driver, OR.AccountPage_AccountName_ValidationMessage);

		assertTrue(account.AccountPage_AccountName_ValidationMessage.isDisplayed(),
				"Failed: Account Page AccountName ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_FirstName_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info FirstName ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_AddressLine_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info AddressLine ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_City_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info City ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_State_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info State ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PostalCode_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PostalCode ValidationMessage didn't display");

		// Billing Information Validation asserts
		assertTrue(account.AccountPage_BillingInfo_FirstName_ValidationMessage.isDisplayed(),
				"Failed: Account Page Billing Info FirstName ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_AddressLine_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info AddressLine ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_City_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info City ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_State_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info State ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PostalCode_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PostalCode ValidationMessage didn't display");
		test_steps.add("Validate : All Input fields Validated after adding Phonenumber");
		return test_steps;

	}

	public ArrayList<String> AccountPage_AddressLine1_Validation(WebDriver driver, String Line1)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Accounts account = new Elements_Accounts(driver);
		new Select(account.Select_Market_Segment).selectByIndex(1);
		account.Account_Enter_Line1.sendKeys(Line1);
		account.Account_save.click();

		assertTrue(account.Account_save.isEnabled(), "Failed: Account Page Save Button isn't Enabled");
		assertTrue(account.Account_Save_Clsoe.isEnabled(), "Failed: Account Page Save & Close Button isn't Enabled");
		assertTrue(account.AccountsPage_Reset_Btn.isEnabled(), "Failed: Account Page Reset Button isn't Enabled");
		assertTrue(!account.NewReservation_Button.isEnabled(),
				"Failed: Account Page New Reservation Button is Enabled At Start");
		// MailingInformation Validation asserts
		Wait.WaitForElement(driver, OR.AccountPage_AccountName_ValidationMessage);

		assertTrue(account.AccountPage_AccountName_ValidationMessage.isDisplayed(),
				"Failed: Account Page AccountName ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_FirstName_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info FirstName ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PhoneNumber_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PhoneNumber ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_City_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info City ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_State_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info State ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PostalCode_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PostalCode ValidationMessage didn't display");

		// Billing Information Validation asserts
		assertTrue(account.AccountPage_BillingInfo_FirstName_ValidationMessage.isDisplayed(),
				"Failed: Account Page Billing Info FirstName ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PhoneNumber_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PhoneNumber ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_City_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info City ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_State_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info State ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PostalCode_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PostalCode ValidationMessage didn't display");
		test_steps.add("Validate : All Input fields Validated after adding Address Line1");
		return test_steps;

	}

	public ArrayList<String> AccountPage_State_Validation(WebDriver driver, String State) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Accounts account = new Elements_Accounts(driver);
		new Select(account.Select_Account_State).selectByVisibleText(State);
		account.Account_save.click();

		assertTrue(account.Account_save.isEnabled(), "Failed: Account Page Save Button isn't Enabled");
		assertTrue(account.Account_Save_Clsoe.isEnabled(), "Failed: Account Page Save & Close Button isn't Enabled");
		assertTrue(account.AccountsPage_Reset_Btn.isEnabled(), "Failed: Account Page Reset Button isn't Enabled");
		assertTrue(!account.NewReservation_Button.isEnabled(),
				"Failed: Account Page New Reservation Button is Enabled At Start");
		// MailingInformation Validation asserts
		Wait.WaitForElement(driver, OR.AccountPage_AccountName_ValidationMessage);

		assertTrue(account.AccountPage_AccountName_ValidationMessage.isDisplayed(),
				"Failed: Account Page AccountName ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_FirstName_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info FirstName ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_AddressLine_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info AddressLine ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PhoneNumber_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PhoneNumber ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_City_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info City ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PostalCode_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PostalCode ValidationMessage didn't display");

		// Billing Information Validation asserts

		assertTrue(account.AccountPage_BillingInfo_FirstName_ValidationMessage.isDisplayed(),
				"Failed: Account Page Billing Info FirstName ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PhoneNumber_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PhoneNumber ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_AddressLine_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info AddressLine ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_City_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info City ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PostalCode_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PostalCode ValidationMessage didn't display");
		test_steps.add("Validate : All Input fields Validated after adding State");
		return test_steps;

	}

	public ArrayList<String> AccountPage_City_Validation(WebDriver driver, String City) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Accounts account = new Elements_Accounts(driver);
		account.Account_Enter_City.sendKeys(City);
		account.Account_save.click();
		assertTrue(account.Account_save.isEnabled(), "Failed: Account Page Save Button isn't Enabled");
		assertTrue(account.Account_Save_Clsoe.isEnabled(), "Failed: Account Page Save & Close Button isn't Enabled");
		assertTrue(account.AccountsPage_Reset_Btn.isEnabled(), "Failed: Account Page Reset Button isn't Enabled");
		assertTrue(!account.NewReservation_Button.isEnabled(),
				"Failed: Account Page New Reservation Button is Enabled At Start");
		// MailingInformation Validation asserts
		Wait.WaitForElement(driver, OR.AccountPage_AccountName_ValidationMessage);
		assertTrue(account.AccountPage_AccountName_ValidationMessage.isDisplayed(),
				"Failed: Account Page AccountName ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_FirstName_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info FirstName ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PhoneNumber_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PhoneNumber ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_AddressLine_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info AddressLine ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PostalCode_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PostalCode ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_State_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info State ValidationMessage didn't display");

		// Billing Information Validation asserts
		assertTrue(account.AccountPage_BillingInfo_FirstName_ValidationMessage.isDisplayed(),
				"Failed: Account Page Billing Info FirstName ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PhoneNumber_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PhoneNumber ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_AddressLine_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info AddressLine ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_State_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info State ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PostalCode_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PostalCode ValidationMessage didn't display");
		test_steps.add("Validate : All Input fields Validated after adding City");
		return test_steps;

	}

	public ArrayList<String> AccountPage_PostalCode_Validation(WebDriver driver, String PostalCode)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Accounts account = new Elements_Accounts(driver);
		account.Account_Enter_PostalCode.sendKeys(PostalCode);
		account.Account_save.click();
		assertTrue(account.Account_save.isEnabled(), "Failed: Account Page Save Button isn't Enabled");
		assertTrue(account.Account_Save_Clsoe.isEnabled(), "Failed: Account Page Save & Close Button isn't Enabled");
		assertTrue(account.AccountsPage_Reset_Btn.isEnabled(), "Failed: Account Page Reset Button isn't Enabled");
		assertTrue(!account.NewReservation_Button.isEnabled(),
				"Failed: Account Page New Reservation Button is Enabled At Start");

		// Information Validation asserts
		Wait.WaitForElement(driver, OR.AccountPage_AccountName_ValidationMessage);
		assertTrue(account.AccountPage_AccountName_ValidationMessage.isDisplayed(),
				"Failed: Account Page AccountName ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_FirstName_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info FirstName ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_AddressLine_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info AddressLine ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_City_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info City ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_State_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info State ValidationMessage didn't display");
		// Billing Information

		assertTrue(account.AccountPage_BillingInfo_FirstName_ValidationMessage.isDisplayed(),
				"Failed: Account Page Billing Info FirstName ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PhoneNumber_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PhoneNumber ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_AddressLine_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info AddressLine ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_City_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info City ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_State_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info State ValidationMessage didn't display");

		test_steps.add("Validate : All Input fields Validated after adding PostalCode");
		return test_steps;

	}

	public ArrayList<String> AddNoteFullPageValidation(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Accounts AddNote = new Elements_Accounts(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		assertTrue(AddNote.Acc_Note_Save.isEnabled(), "Failed: Cancel Button is not enabled at start");
		jse.executeScript("arguments[0].scrollIntoView();", AddNote.Acc_Note_Save);
		assertTrue(AddNote.AccountPage_NotesPage_Cancelbutton.isEnabled(),
				"Failed: Cancel Button is not enabled at start");
		jse.executeScript("arguments[0].scrollIntoView();", AddNote.Acc_Note_Save);
		AddNote.Acc_Note_Save.click();
		assertTrue(AddNote.AccountPage_NotesDetails_Subject_ValidationMessage.isDisplayed(),
				"Failed: Notes Page Subject ValidationMessage didn't display");
		Wait.WaitForElement(driver, OR.AccountPage_NotesPage_Cancelbutton);
		jse.executeScript("arguments[0].click();", AddNote.AccountPage_NotesPage_Cancelbutton);
		test_steps.add("Validate : Notes Page  Validated");
		return test_steps;
	}

	public ArrayList<String> AccountPage_PhoneNumber_NegativeCase(WebDriver driver, String PhoneNumber)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Accounts account = new Elements_Accounts(driver);
		account.Account_Phone_number.sendKeys(PhoneNumber);
		account.Account_save.click();
		String PhoneNumberInvalidMsg = account.AccountPage_PhoneNumber_InvalidMessage.getText();
		System.out.println("Msg:" + PhoneNumberInvalidMsg);
		assertTrue(
				PhoneNumberInvalidMsg
						.equals("Invalid phone number. Valid phone numbers for this country must contain a minimum of 10 and maximum of 10 numerical digits and can only contain the following special characters – ( ) . space"),
				"Failed:Invalid Phone Number didn't show");

		assertTrue(account.Account_save.isEnabled(), "Failed: Account Page Save Button isn't Enabled");
		assertTrue(account.Account_Save_Clsoe.isEnabled(), "Failed: Account Page Save & Close Button isn't Enabled");
		assertTrue(account.AccountsPage_Reset_Btn.isEnabled(), "Failed: Account Page Reset Button isn't Enabled");
		assertTrue(!account.NewReservation_Button.isEnabled(),
				"Failed: Account Page New Reservation Button is Enabled At Start");
		// MailingInformation Validation asserts
		Wait.WaitForElement(driver, OR.AccountPage_AccountName_ValidationMessage);

		assertTrue(account.AccountPage_MailingInfo_AddressLine_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info AddressLine ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_City_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info City ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_State_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info State ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PostalCode_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PostalCode ValidationMessage didn't display");

		// Billing Information Validation asserts

		assertTrue(account.AccountPage_MailingInfo_AddressLine_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info AddressLine ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_City_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info City ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_State_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info State ValidationMessage didn't display");

		assertTrue(account.AccountPage_MailingInfo_PostalCode_ValidationMessage.isDisplayed(),
				"Failed: Account Page Mailing Info PostalCode ValidationMessage didn't display");
		test_steps.add("Validate : All Input fields Validated after adding Phonenumber");
		return test_steps;

	}

	public ArrayList<String> AgeingPaymentAdvanceDeposit_Pay(WebDriver driver, ExtentTest test1, String AccountType,
			String PaymentType, String CardName, String CCNumber, String CCExpiry, String CCVCode,
			String Authorizationtype, String ChangeAmount, String ChangeAmountValue, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts AccountPayment = new Elements_Accounts(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOf(AccountPayment.Folio_Balance));

		ArrayList<String> steps = new ArrayList<>();
		steps = click_Pay(driver, test1, steps);
		test_steps.addAll(steps);
		if (ChangeAmount.equalsIgnoreCase("Yes")) {
			AccountPayment.Enter_Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
			test_steps.add("Enter amount to pay : " + ChangeAmountValue);
			accountsLogger.info("Enter amount to pay : " + ChangeAmountValue);
		}
		Wait.explicit_wait_visibilityof_webelement_150(AccountPayment.Select_Account_paymethod, driver);
		Wait.wait2Second();

		new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
		// test_steps.add("Select payment Method : " + PaymentType);
		accountsLogger.info("Select payment Method : " + PaymentType);

		try {
			String Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();
			test_steps.add("Selected payment Method : " + Type);
			accountsLogger.info("Selected payment Method : " + Type);
			if (Type.contains("Select")) {
				new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
				test_steps.add("Again Select payment Method : " + PaymentType);
				accountsLogger.info("Again Select payment Method : " + PaymentType);
				Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();
				test_steps.add("Selected payment Method : " + Type);
				accountsLogger.info("Selected payment Method : " + Type);
			}
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(AccountPayment.Select_Account_paymethod, driver);
			new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
			test_steps.add("Select payment Method : " + PaymentType);
			accountsLogger.info("Select payment Method : " + PaymentType);
			String Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();
			test_steps.add("Selected payment Method : " + Type);
			accountsLogger.info("Selected payment Method : " + Type);
			assertTrue(Type.contains(PaymentType), "Failed : Payment Type is not selected");
		}
		Wait.explicit_wait_visibilityof_webelement_350(AccountPayment.Click_AddPayment_Account, driver);
		Utility.ScrollToElement(AccountPayment.Click_AddPayment_Account, driver);
		AccountPayment.Click_AddPayment_Account.click();

		test_steps.add("Click Add payment");
		accountsLogger.info("Click Add Payment");

		Wait.WaitForElement(driver, OR.Apply_Advance_Deposite);
		Wait.explicit_wait_xpath(OR.Apply_Advance_Deposite, driver);
		AccountPayment.Click_Continue_Adv_Deposite.click();
		test_steps.add("Click Apply Advance Deposit Continue Button");
		accountsLogger.info("Click Apply Advance Deposit Continue Button");

		Wait.explicit_wait_xpath(OR.Verify_MC_Grid, driver);
		Wait.WaitForElement(driver, OR.Verify_MC_Grid);
		Wait.WaitForElement(driver, OR.Click_Account_Pay_Continue);
		AccountPayment.Click_Account_Pay_Continue.click();

		test_steps.add("Click Continue Payment");
		accountsLogger.info("Click Continue Payment");

		steps.clear();
		steps = Save1(driver, test1, steps);
		test_steps.addAll(steps);
		try {
			Wait.waitForElementToBeGone(driver, AccountPayment.Account_ModuleLoading, 120);
		} catch (Exception e) {

		}
		return test_steps;
	}

	public ArrayList<String> AdvanceDeposit(WebDriver driver, ExtentTest test, String Category1, String Category2,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Accounts AccountPayment = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement_350(AccountPayment.Advance_Deposit_Click, driver);
		Utility.ScrollToElement(AccountPayment.Advance_Deposit_Click, driver);
		AccountPayment.Advance_Deposit_Click.click();
		test_steps.add("Click Advance Deposit Balance");
		accountsLogger.info("Click Advance Deposit Balance");

		Wait.explicit_wait_visibilityof_webelement(AccountPayment.AccountPyamentPopup, driver);
		assertEquals(AccountPayment.AccountPyamentPopup.getText(), "Payment Details",
				"payment popup is not displaying");

		Wait.explicit_wait_visibilityof_webelement(AccountPayment.Deposit_Auto_apply.get(0), driver);
		Utility.ScrollToElement(AccountPayment.Deposit_Auto_apply.get(0), driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", AccountPayment.Deposit_Auto_apply.get(0));
		// AccountPayment.Deposit_Auto_apply.get(0).click();
		test_steps.add("Clicking on Auto Apply");
		accountsLogger.info("Clicking on Auto Apply");

		String path_VerifyLineItem = "//div[@id='divOutstandingItems']//input[contains(@data-bind,'click: $parent.checkItem')]";
		List<WebElement> verifyLineItem = driver.findElements(By.xpath(path_VerifyLineItem));
		assertEquals(verifyLineItem.size(), 2, "Line item size not qual to 2");
		test_steps.add("Verify line item size and size equal :" + verifyLineItem.size());

		String path = "//div[@id='divOutstandingItems']//span[text()='" + Category1
				+ "']//..//..//td//input[contains(@data-bind,'click: $parent.checkItem')]";
		WebElement outside_LineItem = driver.findElement(By.xpath(path));
		Wait.explicit_wait_visibilityof_webelement(outside_LineItem, driver);
		Utility.ScrollToElement(outside_LineItem, driver);
		try {
			assertTrue(outside_LineItem.isSelected(), "Failed : Line Item : " + Category1 + " is not Selected");
		} catch (Exception e) {
			jse.executeScript("arguments[0].click();", AccountPayment.Deposit_Auto_apply.get(0));
			test_steps.add("Click Auto Apply Again");
			assertTrue(outside_LineItem.isSelected(), "Failed : Line Item : " + Category1 + " is not Selected");
		}
		test_steps.add("Line Item : " + Category1 + " is  Selected");
		path = "//div[@id='divOutstandingItems']//span[text()='" + Category2
				+ "']//..//..//td//input[contains(@data-bind,'click: $parent.checkItem')]";
		outside_LineItem = driver.findElement(By.xpath(path));
		Wait.explicit_wait_visibilityof_webelement(outside_LineItem, driver);
		Utility.ScrollToElement(outside_LineItem, driver);
		assertTrue(outside_LineItem.isSelected(), "Failed : Line Item : " + Category2 + " is not Selected");
		test_steps.add("Line Item : " + Category2 + " is  Selected");

		Wait.explicit_wait_visibilityof_webelement(AccountPayment.Payment_Add, driver);
		AccountPayment.Payment_Add.click();
		test_steps.add("Clicking on Add");
		accountsLogger.info("Clicking on Add");

		Wait.wait3Second();
		AccountPayment.Click_Continue_Adv_Deposite.click();
		test_steps.add("Click Continue Apply Advance Deposit");
		accountsLogger.info("Click Continue Apply Advance Deposit");

		Wait.wait3Second();
		Wait.explicit_wait_visibilityof_webelement(AccountPayment.Click_Account_Pay_Continue, driver);
		AccountPayment.Click_Account_Pay_Continue.click();
		test_steps.add("Clicking on Continue Payment details");
		accountsLogger.info("Clicking on Continue Payment Details");

		Wait.wait3Second();
		Wait.explicit_wait_visibilityof_webelement(AccountPayment.Account_save, driver);
		AccountPayment.Account_save.click();
		Wait.explicit_wait_visibilityof_webelement(AccountPayment.Toaster_Message, driver);
		AccountPayment.Toaster_Message.click();
		test_steps.add("Clicking on Save");
		accountsLogger.info("Clicking on Save");

		return test_steps;
	}

	public void Verify_LineItemFullyPaid(WebDriver driver, String Category) throws InterruptedException {

		String path = "//span[text()='" + Category
				+ "']//..//following-sibling::td[@class='text-center lineitem-aging']//img[@title='Fully Paid']";
		WebElement element = driver.findElement(By.xpath(path));
		assertEquals(element.isDisplayed(), true, "element is not display");
	}

	public ArrayList<String> AutoPaymentAgainst_IndividualLineItems(WebDriver driver, ExtentTest test1,
			String AccountType, String PaymentType, String CardName, String CCNumber, String CCExpiry, String CCVCode,
			String Authorizationtype, String ChangeAmount, String ChangeAmountValue, String Category1, String Category2,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Accounts AccountPayment = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement(AccountPayment.AccountPyamentPopup, driver);
		assertEquals(AccountPayment.AccountPyamentPopup.getText(), "Payment Details",
				"payment popup is not displaying");
		String path_VerifyLineItem = "//div[@id='divOutstandingItems']//input[contains(@data-bind,'click: $parent.checkItem')]";
		List<WebElement> verifyLineItem = driver.findElements(By.xpath(path_VerifyLineItem));
		assertEquals(verifyLineItem.size(), 2, "Line item size not qual to 2");
		test_steps.add("Verify line item size and size equal :" + verifyLineItem.size());
		accountsLogger.info("Verify line item size and size equal :" + verifyLineItem.size());

		Wait.explicit_wait_visibilityof_webelement_150(AccountPayment.Account_AutoApply, driver);
		Utility.ScrollToElement(AccountPayment.Account_AutoApply, driver);
		assertTrue(AccountPayment.Account_AutoApply.isDisplayed(), "AutoPlay element is not displayed");
		AccountPayment.Account_AutoApply.click();
		test_steps.add("Click on AutoApply");
		accountsLogger.info("Click on AutoApply");
		String Category1Amount = "//div[@id='divOutstandingItems']//span[text()='" + Category1
				+ "']//..//..//td//input[contains(@data-bind,'value: PayAmount')]";
		String Category2Amount = "//div[@id='divOutstandingItems']//span[text()='" + "Nightly " + Category2
				+ "']//..//..//td//input[contains(@data-bind,'value: PayAmount')]";
		String totalAmount = "(//*[@id='divOutstandingItems']/table/tfoot/tr/td[6]/span)[2]";
		WebElement element_Category1Amount = driver.findElement(By.xpath(Category1Amount));
		WebElement element_Category2Amount = driver.findElement(By.xpath(Category2Amount));
		WebElement element_totalAmount = driver.findElement(By.xpath(totalAmount));
		Wait.explicit_wait_visibilityof_webelement(element_Category1Amount, driver);
		Utility.ScrollToElement(element_Category1Amount, driver);

		if (element_Category1Amount.isDisplayed() == true) {
			System.out.println(element_Category1Amount.getAttribute("value"));
		} else {
			System.out.println("Not displayed");
		}
		Wait.explicit_wait_visibilityof_webelement(element_Category2Amount, driver);
		Utility.ScrollToElement(element_Category2Amount, driver);

		if (element_Category2Amount.isDisplayed() == true) {
			System.out.println(element_Category2Amount.getAttribute("value"));
		} else {
			System.out.println("Not displayed");

		}
		double firstCategoryAmount = Double.parseDouble(element_Category1Amount.getAttribute("value"));
		double secondCategoryAmount = Double.parseDouble(element_Category2Amount.getAttribute("value"));

		Wait.explicit_wait_visibilityof_webelement(element_totalAmount, driver);
		Utility.ScrollToElement(element_totalAmount, driver);
		if (element_totalAmount.isDisplayed() == true) {
			System.out.println(element_totalAmount.getText());
		} else {
			System.out.println("Not displayed");

		}
		double TotalCharges = (double) (firstCategoryAmount + secondCategoryAmount);
		System.out.println("Total amount after applying auto apply");
		System.out.println(TotalCharges);
		test_steps.add("Total amount after applying auto apply is checked: " + TotalCharges);
		double TotalofBothCategory = Double.parseDouble(element_totalAmount.getText());
		if (TotalCharges == TotalofBothCategory) {
			System.out.println("The calulated amount and total given amount is same");
			test_steps.add("The calulated amount and total given amount is same : " + TotalofBothCategory);
			accountsLogger.info("The calulated amount and total given amount is same");
		} else {
			System.out.println("The calulated amount and total given amount is not same");
		}

		String textchangeAmount = AccountPayment.Enter_Change_Amount.getAttribute("value");
		System.out.println(textchangeAmount);
		double changeAmount = Double.parseDouble(textchangeAmount);
		double remainingAmount = (double) (changeAmount - TotalofBothCategory);
		System.out.println("Remaining Amount");
		System.out.println(remainingAmount);
		test_steps.add("The calulated remaining amount : " + remainingAmount);

		Wait.explicit_wait_visibilityof_webelement_150(AccountPayment.Account_AutoApply, driver);
		Utility.ScrollToElement(AccountPayment.Account_AutoApply, driver);
		AccountPayment.Payment_Add.click();
		test_steps.add("Click on Add Button");
		accountsLogger.info("Click on Add Button");

		Wait.explicit_wait_visibilityof_webelement_150(AccountPayment.Click_Continue_Adv_Deposite, driver);
		Utility.ScrollToElement(AccountPayment.Click_Continue_Adv_Deposite, driver);
		AccountPayment.Click_Continue_Adv_Deposite.click();
		test_steps.add("Click on Continue_Adv_Deposite Button");
		accountsLogger.info("Clicking on Continue_Adv_Deposite Button");

		Wait.explicit_wait_visibilityof_webelement_150(AccountPayment.Click_Account_Pay_Continue, driver);
		Utility.ScrollToElement(AccountPayment.Click_Account_Pay_Continue, driver);
		AccountPayment.Click_Account_Pay_Continue.click();
		test_steps.add("Clicking on Account_Pay_Continue Button");
		accountsLogger.info("Clicking on Account_Pay_Continue Button");

		Wait.explicit_wait_visibilityof_webelement_150(AccountPayment.Account_save, driver);
		Utility.ScrollToElement(AccountPayment.Account_save, driver);
		AccountPayment.Account_save.click();

		Wait.explicit_wait_visibilityof_webelement_150(AccountPayment.Toaster_Message, driver);
		Utility.ScrollToElement(AccountPayment.Toaster_Message, driver);
		AccountPayment.Toaster_Message.click();
		test_steps.add("Click on Save");
		accountsLogger.info("Click on Save");
		return test_steps;

	}

	public void VerifyAdDepositRemainingBalnce(WebDriver driver, String AfetrValue, String PaidAmount)
			throws InterruptedException {
		float afterPayment = Float.parseFloat(AfetrValue);
		float payamount = Float.parseFloat(PaidAmount);
		float remainingAmount = payamount;
		assertEquals(afterPayment, remainingAmount, "payment is not matching after individual pay");

	}

	public void Save_OpenNewReservation(WebDriver driver) throws InterruptedException {
		Elements_Accounts eleAccounts = new Elements_Accounts(driver);

		Wait.explicit_wait_elementToBeClickable(eleAccounts.Account_Save_Button, driver);
		eleAccounts.Account_Save_Button.click();
		Wait.explicit_wait_elementToBeClickable(eleAccounts.NewReservation_Button, driver);
		eleAccounts.NewReservation_Button.click();
		Wait.wait3Second();
	}

	public ArrayList<String> MakeAuthorizate_Payment(WebDriver driver, ExtentTest test1, String AccountType,
			String PaymentType, String CardName, String CCNumber, String CCExpiry, String CCVCode,
			String Authorizationtype, String ChangeAmount, String ChangeAmountValue, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts AccountPayment = new Elements_Accounts(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOf(AccountPayment.Folio_Balance));

		ArrayList<String> steps = new ArrayList<>();
		steps = click_Pay(driver, test1, steps);
		test_steps.addAll(steps);

		Wait.explicit_wait_visibilityof_webelement_150(AccountPayment.Select_Account_paymethod, driver);
		Wait.wait2Second();

		new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
		// test_steps.add("Select payment Method : " + PaymentType);
		accountsLogger.info("Select payment Method : " + PaymentType);

		try {
			String Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();
			test_steps.add("Selected payment Method : " + Type);
			accountsLogger.info("Selected payment Method : " + Type);
			if (Type.contains("Select")) {
				new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
				test_steps.add("Again Select payment Method : " + PaymentType);
				accountsLogger.info("Again Select payment Method : " + PaymentType);
				Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();
				test_steps.add("Selected payment Method : " + Type);
				accountsLogger.info("Selected payment Method : " + Type);
			}
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(AccountPayment.Select_Account_paymethod, driver);
			new Select(AccountPayment.Select_Account_paymethod).selectByVisibleText(PaymentType);
			test_steps.add("Select payment Method : " + PaymentType);
			accountsLogger.info("Select payment Method : " + PaymentType);
			String Type = new Select(AccountPayment.Select_Account_paymethod).getFirstSelectedOption().getText();
			test_steps.add("Selected payment Method : " + Type);
			accountsLogger.info("Selected payment Method : " + Type);
			assertTrue(Type.contains(PaymentType), "Failed : Payment Type is not selected");
		}
		System.out.println("Cardtype:" + PaymentType);
		try {
			Wait.explicit_wait_visibilityof_webelement(AccountPayment.Click_Account_Card_info, driver);
			AccountPayment.Click_Account_Card_info.click();
			Wait.explicit_wait_visibilityof_webelement(AccountPayment.Enter_Account_Card_Name, driver);
		} catch (Exception e) {
			Wait.WaitForElement(driver, OR.Click_Account_Card_info);
			jse.executeScript("arguments[0].click();", AccountPayment.Click_Account_Card_info);
			Wait.explicit_wait_visibilityof_webelement(AccountPayment.Enter_Account_Card_Name, driver);
		}

		test_steps.add("Click Card Info");
		accountsLogger.info("Click Card Info");

		Wait.explicit_wait_xpath(OR.Verify_Account_Paymnet_info_popup, driver);
		Wait.WaitForElement(driver, OR.Enter_Account_Card_Name);

		AccountPayment.Enter_Account_Card_Name.sendKeys(CardName);
		test_steps.add("Enter Card Name :" + CardName);
		accountsLogger.info("Enter Card Name :" + CardName);

		AccountPayment.Enter_CC_Account_Number.sendKeys(CCNumber);
		test_steps.add("Enter Card Number : " + CCNumber);
		accountsLogger.info("Enter Card Number : " + CCNumber);

		AccountPayment.Enter_ExpiryDate_Account.sendKeys(CCExpiry);
		test_steps.add("Enter Expiry date : " + CCExpiry);
		accountsLogger.info("Enter Expiry date : " + CCExpiry);

		AccountPayment.Enter_CCV_Account.sendKeys(CCVCode);
		test_steps.add("Enter CVV : " + CCVCode);
		accountsLogger.info("Enter CVV : " + CCVCode);

		AccountPayment.Click_Ok_Account.click();
		test_steps.add("Click OK");
		accountsLogger.info("Click OK");

		Wait.waitForElementToBeGone(driver, AccountPayment.Click_Ok_Account, 60);

		Wait.WaitForElement(driver, OR.Select_Authorization_Type_Account);
		Wait.explicit_wait_visibilityof_webelement(AccountPayment.Select_Authorization_Type_Account, driver);
		new Select(AccountPayment.Select_Authorization_Type_Account).selectByVisibleText(Authorizationtype);
		Utility.ScrollToElement(AccountPayment.Enter_Change_Amount, driver);
		AccountPayment.Enter_Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
		test_steps.add("Enter amount to pay : " + ChangeAmountValue);
		accountsLogger.info("Enter amount to pay : " + ChangeAmountValue);
		Wait.WaitForElement(driver, OR.Click_Process_Account);

		Wait.explicit_wait_visibilityof_webelement_350(AccountPayment.Click_Process_Account, driver);
		Utility.ScrollToElement(AccountPayment.Click_Process_Account, driver);
		AccountPayment.Click_Process_Account.click();

		test_steps.add("Click Process");
		accountsLogger.info("Click Process");

		String Auth_Path = "//div[@id='AccountPaymetItemDetail']//span[contains(@data-bind,'LedgerAccountName') and text()='"
				+ PaymentType
				+ "']//ancestor::div[@id='reservationList']//following-sibling::img[contains(@data-bind,'TransactionStatus')]";
		// Wait.explicit_wait_xpath(Auth_Path, driver);
		Wait.WaitForElement(driver, Auth_Path);
		assertTrue(driver.findElement(By.xpath(Auth_Path)).isDisplayed(),
				"Failed : Transaction Status: Authorized not Appeared");

		test_steps.add("Transaction Status: Authorized Appeared");
		Wait.WaitForElement(driver, OR.Click_Account_Pay_Continue);
		AccountPayment.Click_Account_Pay_Continue.click();

		test_steps.add("Click Continue Payment");
		accountsLogger.info("Click Continue Payment");

		try {
			Wait.waitForElementToBeGone(driver, AccountPayment.Click_Account_Pay_Continue, 20);
		} catch (Exception e) {
			AccountPayment.Click_Account_Pay_Continue.click();
			test_steps.add("Again Click Continue Payment");
			accountsLogger.info("Again Click Continue Payment");
		}
		String LineItems_Auth_Path = "//tbody[contains(@data-bind,'ComputedFolioItemsElement')]//span[contains(@data-bind,'data.Category') and text()='"
				+ PaymentType
				+ "']//ancestor::tbody[contains(@data-bind,'ComputedFolioItemsElement')]//following-sibling::img[contains(@data-bind,'TransactionStatus')]";
		// Wait.explicit_wait_xpath(Auth_Path, driver);
		Wait.WaitForElement(driver, LineItems_Auth_Path);
		assertTrue(driver.findElement(By.xpath(LineItems_Auth_Path)).isDisplayed(),
				"Failed :Card Payment is Added in Line Items with Transaction Status: Authorized");
		test_steps.add("Card Payment is Added in Line Items with Transaction Status: Authorized");
		steps.clear();
		steps = Save1(driver, test1, steps);
		test_steps.addAll(steps);
		try {
			Wait.waitForElementToBeGone(driver, AccountPayment.Account_ModuleLoading, 120);
		} catch (Exception e) {

		}
		return test_steps;
	}

	public ArrayList<String> Verify_AuthorizePayment(WebDriver driver, String PaymentType, String Period,
			ArrayList<String> test_steps) {
		String LineItems_Auth_Path = "//tbody[contains(@data-bind,'ComputedFolioItemsElement')]//span[contains(@data-bind,'data.Category') and text()='"
				+ PaymentType
				+ "']//ancestor::tbody[contains(@data-bind,'ComputedFolioItemsElement')]//following-sibling::img[contains(@data-bind,'TransactionStatus')]";
		// Wait.explicit_wait_xpath(Auth_Path, driver);
		Wait.WaitForElement(driver, LineItems_Auth_Path);
		assertTrue(driver.findElement(By.xpath(LineItems_Auth_Path)).isDisplayed(),
				"Failed :Card Payment is Added in Line Items with Transaction Status: Authorized");
		test_steps.add(PaymentType + " Card Authorization exist in " + Period + " Period");
		accountsLogger.info(PaymentType + " Card Authorization exist in " + Period + " Period");
		return test_steps;
	}

	public void ClickFolio(WebDriver driver) throws InterruptedException {
		Elements_Accounts ClickFolio = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ClickFolio.Account_Folio, driver);
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();", ClickFolio.Account_Folio);
		Utility.ScrollToElement(ClickFolio.Account_Folio, driver);
		ClickFolio.Account_Folio.click();

	}

	public void navigateAccount(WebDriver driver) {
		Elements_Accounts ClickFolio = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement_120(ClickFolio.Account_AccountTab, driver);
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();",
		// ClickFolio.Account_AccountTab);
		ClickFolio.Account_AccountTab.click();

	}

	public ArrayList<String> Accounts_AccountStatement(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException {
		Elements_Accounts account = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement(account.Account_GetStatement_Icon, driver);
		Utility.ScrollToElement(account.Account_GetStatement_Icon, driver);
		account.Account_GetStatement_Icon.click();
		steps.add("Click Account Statement Icon");
		accountsLogger.info("Click Account Statement Icon");
		try {
			Wait.waitUntilPresenceOfElementLocated(NewAccount.Account_NewPeriodPopup, driver);
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(account.Account_GetStatement_Icon, driver);
			Utility.ScrollToElement(account.Account_GetStatement_Icon, driver);
			account.Account_GetStatement_Icon.click();
			steps.add("Click Account Statement Icon");
			accountsLogger.info("Click Account Statement Icon");
			Wait.waitUntilPresenceOfElementLocated(NewAccount.Account_NewPeriodPopup, driver);
		}
		steps.add("Account Statement Select date popup Appeared");
		accountsLogger.info("Account Statement Select date popup Appeared");
		Wait.explicit_wait_visibilityof_webelement(account.AccountStatement_CalanderIcon, driver);
		Utility.ScrollToElement(account.AccountStatement_CalanderIcon, driver);
		account.AccountStatement_CalanderIcon.click();
		steps.add("Click Calender Icon of Account Statement 'Select date' popup");
		accountsLogger.info("Click Calender Icon of 'Account Statement Select date' popup");
		Wait.explicit_wait_visibilityof_webelement(account.AccountStatement_SelectToday, driver);
		Utility.ScrollToElement(account.AccountStatement_SelectToday, driver);
		account.AccountStatement_SelectToday.click();
		steps.add("Select Date 'Today' for Account Statement");
		accountsLogger.info("Select Date 'Today' for Account Statement");
		Wait.explicit_wait_visibilityof_webelement(account.AccountStatementSelect_SelectDate, driver);
		Utility.ScrollToElement(account.AccountStatementSelect_SelectDate, driver);
		account.AccountStatementSelect_SelectDate.click();
		steps.add("Click Select Button of Account Statement 'Select date' popup");
		accountsLogger.info("Click Select Button of Account Statement 'Select date' popup");
		try {
			Wait.waitForElementToBeGone(driver, account.AccountStatement_ModuleLoading, 180);
		} catch (Exception e) {

		}
		Wait.explicit_wait_visibilityof_webelement(account.Accounts_AccountStatement, driver);
		assertTrue(account.Accounts_AccountStatement.isDisplayed(), "Failed: Account Statement not Displaying");
		steps.add("Account Statement Appeared");
		accountsLogger.info("Account Statement Appeared");
		Wait.explicit_wait_visibilityof_webelement(account.Accounts_AccountStatement_Confirm, driver);
		Utility.ScrollToElement(account.Accounts_AccountStatement_Confirm, driver);
		account.Accounts_AccountStatement_Confirm.click();
		steps.add("Click Account Statement  Confirm Button");
		accountsLogger.info("Click Account Statement Confirm Button");
		Thread.sleep(3000);
		Wait.wait2Second();
		driver.switchTo().alert().accept();
		steps.add("Accept Alert");
		accountsLogger.info("Accept Alert");
		return steps;

	}

	public String SelectPeriod(WebDriver driver, String period) throws InterruptedException {
		Elements_Accounts account = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement(account.Account_SelectPeriod, driver);
		Utility.ScrollToElement(account.Account_SelectPeriod, driver);
		List<WebElement> periods = new Select(account.Account_SelectPeriod).getOptions();
		for (WebElement periodname : periods) {
			if (periodname.getText().contains(period)) {
				period = periodname.getText();
				Utility.app_logs.info("Period : " + period);
				break;
			}
		}
		Select SelectPeriod = new Select(account.Account_SelectPeriod);
		SelectPeriod.selectByVisibleText(period);
		Utility.app_logs.info("Selected Period : " + period);
		assertTrue(SelectPeriod.getFirstSelectedOption().getText().contains(period), "Failed: Period is not Selected");
		Wait.WaitForElement(driver, NewFolio.AddedLineItems_Category);
		return period;
	}

	public void SelectAccountStatus(WebDriver driver, String status) throws InterruptedException {
		Elements_Accounts account = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement(account.Account_SelectStatus, driver);
		Utility.ScrollToElement(account.Account_SelectStatus, driver);
		Select SelectPeriod = new Select(account.Account_SelectStatus);
		SelectPeriod.selectByVisibleText(status);
		Utility.app_logs.info("Selected Status : " + status);
		assertTrue(SelectPeriod.getFirstSelectedOption().getText().contains(status), "Failed: Period is not Selected");
	}

	public void selectAccountType(WebDriver driver, String AccountType) {
		Elements_Accounts account = new Elements_Accounts(driver);
		Wait.explicit_wait_visibilityof_webelement(account.Select_Account_Type, driver);
		Select SelectPeriod = new Select(account.Select_Account_Type);
		SelectPeriod.selectByVisibleText(AccountType);
	}

	public void VerifySearched_List(WebDriver driver, String AccountName) throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Number_Of_Accounts);
		int count = driver.findElements(By.xpath(OR.Number_Of_Accounts)).size();
		if (count != 0) {// Verify the account
			Wait.WaitForElement(driver, OR.Searched_AccountName);
			List<WebElement> names = driver.findElements(By.xpath(OR.Searched_AccountName));
			for (WebElement name : names) {
				Assert.assertTrue(name.getText().contains(AccountName), "Failed: Account Name missmatched");
			}
		}
	}

	public void VerifyOpenAccountNameAndNumber(WebDriver driver, String AccountName, String AccountNumber)
			throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, NewAccount.AccountHeader_AccountName);
		Utility.ScrollToElement(Account.AccountHeader_AccountName, driver);
		assertEquals(Account.AccountHeader_AccountName.getText(), AccountName, "Failed: Account Name missmatched");
		Utility.ScrollToElement(Account.AccountHeader_AccountNumber, driver);
		assertEquals(Account.AccountHeader_AccountNumber.getText(), AccountNumber, "Failed: Account Name missmatched");
	}

	public ArrayList<String> AccAddNotes(WebDriver driver, String Subject, String NoteDetails, String NoteType,
			String Notestatus, String LoginId, ArrayList<String> steps) throws InterruptedException, ParseException {

		Elements_Accounts AddNote = new Elements_Accounts(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", AddNote.Acc_Add_Button);
		steps.add("Scroll down to Notes section.");
		AddNote.Acc_Add_Button.click();
		steps.add("Click on Add above table header.");
		Wait.WaitForElement(driver, OR.Verify_Notes_Popup);
		steps.add("Note Details modal appears");
		Wait.WaitForElement(driver, OR.Acc_Note_Enter_sub);
		// Verify Elements

		assertTrue(AddNote.Acc_Note_Enter_sub.isDisplayed(), "Failed: Note Details modal not contains Subject.");
		steps.add("Note Details modal contains Subject.");
		assertTrue(AddNote.Acc_Note_Enter_Details.isDisplayed(), "Failed: Note Details modal not contains Details.");
		steps.add("Note Details modal contains Details.");
		assertTrue(AddNote.Acc_Note_Select_Note_Type.isDisplayed(),
				"Failed: Note Details modal not contains Note Type.");
		steps.add("Note Details modal contains Note Type.");
		assertTrue(AddNote.Acc_Note_Action_Req.isDisplayed(),
				"Failed: Note Details modal not contains Action Required CheckBox.");
		steps.add("Note Details modal contains Action Required CheckBox.");
		assertTrue(AddNote.AccountNotes_DueDate.isDisplayed(),
				"Failed: Note Details modal not contains Due Date/Time.");
		steps.add("Note Details modal contains Due Date/Time.");
		assertTrue(AddNote.Acc_Note_Select_Note_Status.isDisplayed(),
				"Failed: Note Details modal not contains Action.");
		steps.add("Note Details modal contains Action.");
		// Wait.explicit_wait_xpath(OR.Verify_Notes_Popup, driver);
		AddNote.Acc_Note_Enter_sub.sendKeys(Subject);
		AddNote.Acc_Note_Enter_Details.sendKeys(NoteDetails);
		new Select(AddNote.Acc_Note_Select_Note_Type).selectByVisibleText(NoteType);

		if (AddNote.Acc_Note_Action_Req.isSelected()) {
			accountsLogger.info("Already selected");
		} else {
			AddNote.Acc_Note_Action_Req.click();
			Wait.wait10Second();
		}

		new Select(AddNote.Acc_Note_Select_Note_Status).selectByVisibleText(Notestatus);
		steps.add("Entered All required details");
		String DueDate = AddNote.AccountNotes_DueDate.getAttribute("value");
		SimpleDateFormat Note_DateFormat = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat AddedDate_DateFormat = new SimpleDateFormat("dd-MMM-yy");
		Date Due_Date = Note_DateFormat.parse(DueDate);
		String AddedDueDate = AddedDate_DateFormat.format(Due_Date);
		Utility.app_logs.info("Note_DueDate : " + DueDate + "  Added Note : " + AddedDueDate);
		AddNote.Acc_Note_Save.click();
		steps.add("Click Note Save");
		try {
			Wait.waitForElementToBeGone(driver, AddNote.Acc_Note_Save, 10);
		} catch (Exception e) {

		}
		Wait.explicit_wait_visibilityof_webelement(AddNote.AddNotes_NotesSubject, driver);
		String NotesSubject = AddNote.AddNotes_NotesSubject.getText();
		String Notestype = AddNote.AddNotes_NotesType.getText();
		System.out.println("Subject:" + Subject + "Type:" + NoteType);
		System.out.println("Subject:" + NotesSubject + "Type:" + Notestype);
		assertTrue(AddNote.Verify_Add_line_Notes.isDisplayed(), "AccountNotes didn't added successfull");
		steps.add("Notes section is populated with a new row containing details filled in the modal");
		assertTrue(NotesSubject.equals(Subject), "AccountNotes Subject didn't match");
		assertTrue(Notestype.equals(NoteType), "AccountNotes Type didn't match");
		assertEquals(AddNote.AddedNote_DueDate.getText(), AddedDueDate, "Failed: Due Date missmatched");
		assertEquals(AddNote.AddedNote_LoginId.getText().toLowerCase(), LoginId.toLowerCase(),
				"Failed: Login ID missmatched");
		steps.add("The loginId and property Due Date is also recorded");
		return steps;

	}

	public ArrayList<String> Verify_AddedNote(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException, ParseException {

		Elements_Accounts AddNote = new Elements_Accounts(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", AddNote.Acc_Add_Button);
		steps.add("Scroll down to Notes section.");
		assertTrue(AddNote.Verify_Add_line_Notes.isDisplayed(), "AccountNotes didn't added successfull");
		steps.add("AccountNotes  added successfull");
		return steps;

	}

	public void SaveButton_Status(WebDriver driver, boolean status) throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Utility.ScrollToElement(CreateAccount.Account_save, driver);
		if (status) {
			assertTrue(CreateAccount.Account_save.isEnabled(), "Failed: Account Save Button is not Enabled");
		} else {
			assertTrue(!CreateAccount.Account_save.isEnabled(), "Failed: Account Save Button is Enabled");
		}
	}

	public void ResetButton_Status(WebDriver driver, boolean status) throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Utility.ScrollToElement(CreateAccount.AccountsPage_Reset_Btn, driver);
		if (status) {
			assertTrue(CreateAccount.AccountsPage_Reset_Btn.isEnabled(), "Failed: Account Reset Button is not Enabled");
		} else {
			assertTrue(!CreateAccount.AccountsPage_Reset_Btn.isEnabled(), "Failed: Account Reset Button is Enabled");
		}
	}

	public void SaveAndCloseButton_Status(WebDriver driver, boolean status) throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Utility.ScrollToElement(CreateAccount.Account_Save_Clsoe, driver);
		if (status) {
			assertTrue(CreateAccount.Account_Save_Clsoe.isEnabled(),
					"Failed: Account SaveAndClose Button is not Enabled");
		} else {
			assertTrue(!CreateAccount.Account_Save_Clsoe.isEnabled(), "Failed: Account SaveAndClose Button is Enabled");
		}
	}

	public void Account_DirtyTab(WebDriver driver, boolean Dirty) throws InterruptedException {

		Elements_Accounts account = new Elements_Accounts(driver);
		if (Dirty) {
			Utility.ScrollToElement(account.Account_DirtyTab, driver);
			assertTrue(account.Account_DirtyTab.isDisplayed(), "Failed: Account Tab is not Dirty");
		} else {
			assertEquals(account.Account_DirtyTab.getAttribute("style"), "display: none;",
					"Failed: Account Tab in not Clean");
		}

	}

	public ArrayList<String> SaveAccount_verifyLoadingModule(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Account_save);
		Utility.ScrollToElement(CreateAccount.Account_save, driver);
		CreateAccount.Account_save.click();
		test_steps.add("Click on Save");
		accountsLogger.info("Click on Save");

		Utility.ScrollToElement(CreateAccount.AccountSave_ModuleLoading, driver);
		try {
			if (CreateAccount.AccountSave_ModuleLoading.isDisplayed()) {
				test_steps.add("After Click Save Button Module Loading appear");
			} else {
				test_steps.add("After Click Save Button Module Loading not appear");
			}
		} catch (Exception e) {

		}
		try {
			Wait.explicit_wait_visibilityof_webelement_350(CreateAccount.Toaster_Title, driver);
			String getTotasterTitle_ReservationSucess = CreateAccount.Toaster_Title.getText();
			String getToastermessage_ReservationSucess = CreateAccount.Toaster_Message.getText();
			Assert.assertEquals(getTotasterTitle_ReservationSucess, "Account Saved");
			if (getToastermessage_ReservationSucess.equals("The account Test Account has been successfully created."))
				;
			test_steps.add("The account Test Account has been successfully Saved.");
			accountsLogger.info("The account Test Account has been successfully Saved.");
		} catch (Exception e) {
			test_steps.add("No toaster message Appear");
			accountsLogger.info("No toaster message Appear");
		}
		return test_steps;

	}

	public String getLineItemDesc(WebDriver driver) {
		Elements_FolioLineItemsVoid line = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement_600(line.LineItemDescription, driver);
		return line.LineItemDescription.getText();
	}

	public void OpenSearchedAccount(WebDriver driver) throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);
		Account.Searched_AccountName.click();
	}

	public ArrayList<String> verifyAccountDetails(WebDriver driver, String accountType, String accountName,
			String accountNo, String status) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		// Wait.WaitForElement(driver, OR.Verify_Account_Type);

		Wait.explicit_wait_visibilityof_webelement_150(CreateAccount.Verify_Account_Type, driver);
		String selectedOption = new Select(CreateAccount.Verify_Account_Type).getFirstSelectedOption().getText();
		assertEquals(selectedOption, accountType, "Failed To Verify Account Type : " + selectedOption);
		test_steps.add("Successfully Verifed Account Type : " + accountType);
		accountsLogger.info("Successfully Verifed Account Type : " + accountType);

		String foundName = CreateAccount.Enter_Account_Name.getAttribute("value");
		assertEquals(foundName, accountName, "Failed To Verify Account Name : " + foundName);
		test_steps.add("Successfully Verifed Account Name : " + foundName);
		accountsLogger.info("Successfully Verifed Account Name : " + foundName);

		String foundAccNo = CreateAccount.GetAccount_Number.getAttribute("value");
		assertEquals(foundAccNo, accountNo, "Failed To Verify Account Account No : " + foundAccNo);
		test_steps.add("Successfully Verifed Account No : " + foundAccNo);
		accountsLogger.info("Successfully Verifed Account No : " + foundAccNo);

		String foundStatus = new Select(CreateAccount.Account_Status_Active).getFirstSelectedOption().getText();
		assertEquals(foundStatus, status, "Failed To Verify Account Status : " + foundStatus);
		test_steps.add("Successfully Verifed Account Status : " + foundStatus);
		accountsLogger.info("Successfully Verifed Account Status : " + foundStatus);

		return test_steps;
	}

	public ArrayList<String> verifyMarketSegmentDropDownAccount(WebDriver driver, ArrayList<String> test_steps,
			String MarketSegment, boolean isContains) throws InterruptedException {
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Select_Market_Segment);
		Utility.ScrollToElement(CreateAccount.Select_Market_Segment, driver);
		test_steps.add("Market Segment Dropdown Opened");
		accountsLogger.info("Market Segment Dropdown Opened");
		Select dropdown = new Select(CreateAccount.Select_Market_Segment);
		boolean isMarketSegmentFound = false;
		// Get all options
		List<WebElement> options = dropdown.getOptions();

		for (int i = 0; i < options.size(); i++) {

			if (isContains) {
				isMarketSegmentFound = options.get(i).getText().equals(MarketSegment);
				if (isMarketSegmentFound) {
					assertEquals(isMarketSegmentFound, true);
					test_steps.add("Market Segment Dropdown Contains Active: " + MarketSegment);
					accountsLogger.info("Market Segment Dropdown Contains Active: " + MarketSegment);
					break;
				}
			} else {
				if (!isMarketSegmentFound) {
					isMarketSegmentFound = options.get(i).getText().contains(MarketSegment);
					assertEquals(isMarketSegmentFound, false);
					test_steps.add("Market Segment Dropdown Doesn't Contain InActive/Obsolete: " + MarketSegment);
					accountsLogger.info("Market Segment Dropdown Doesn't Contain InActive/Obsolete: " + MarketSegment);
					break;
				}

			}
		}
		return test_steps;
	}

	public void verifyChargeRouting(WebDriver driver, String chargeRoutingType, ArrayList<String> test_steps) {
		Elements_Accounts ClickFolio = new Elements_Accounts(driver);
		// Wait.explicit_wait_visibilityof_webelement_120(ClickFolio.Account_Folio);
		Wait.WaitForElement(driver, OR.AccountFolioFolioOptionsChargeType);
		Wait.waitForElementToBeVisibile(By.xpath(OR.AccountFolioFolioOptionsChargeType), driver);
		String chargeType = "(//label[contains(text(),'Move To Account Folio: ')]/../div/select/option)[3]";
		chargeType = driver.findElement(By.xpath(chargeType)).getText();
		test_steps.add("Charge Routing Option : " + chargeType);
		accountsLogger.info("Charge Routing Option : : " + chargeType);
		// assertTrue(chargeType.contentEquals(chargeRoutingType));
		// assertEquals(chargeType, chargeRoutingType);

	}

	public void clickOnFolioDetails(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Accounts ClickFolio = new Elements_Accounts(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.accountFolioDetailOptions), driver);
		Wait.wait2Second();
		// Wait.WaitForElement(driver, OR.Account_Folio_FolioOptions);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ClickFolio.accountFolioDetailOptions);
		// ClickFolio.Account_Folio.click();
		test_steps.add("clcick on  Folio Details");
		accountsLogger.info("clcick on  Folio Details");

	}

	public void closeAccountTab(WebDriver driver) throws InterruptedException {
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		String CloseAccButton = "//ul[@class='sec_nav']/li[7]/a/i";
		Wait.WaitForElement(driver, CloseAccButton);
		Wait.waitForElementToBeVisibile(By.xpath(CloseAccButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(CloseAccButton), driver);
		WebElement CloseAccountButton = driver.findElement(By.xpath(CloseAccButton));
		Utility.ScrollToElement_NoWait(CloseAccountButton, driver);
		CloseAccountButton.click();
		accountsLogger.info("Close Account");
		Wait.WaitForElement(driver, OR.CloseUnSavedDataTab);
		if (CreateAccount.CloseUnSavedDataTab.isDisplayed()) {
			CreateAccount.CloseUnSavedDataTab.click();
		}
		assertTrue(CreateAccount.Click_New_Account.isDisplayed(), "Account Button didn't display");
	}

	public ArrayList<String> searchAccount(WebDriver driver, ExtentTest test1, String AccountType, String AccountName,
			String AccountNumber, String AccountStatus, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);
		driver.navigate().refresh();
		try {
			Wait.explicit_wait_xpath(OR.AccountType, driver);
			Wait.WaitForElement(driver, OR.AccountType);
			Wait.explicit_wait_visibilityof_webelement_150(Account.AccountType, driver);
			Select sel = new Select(Account.AccountType);
			sel.selectByVisibleText(AccountType);
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.Account_Type, driver);
			Wait.WaitForElement(driver, OR.Account_Type);
			Wait.explicit_wait_visibilityof_webelement_150(Account.Account_Type, driver);
			Select sel = new Select(Account.Account_Type);
			sel.selectByVisibleText(AccountType);
		}
		test_steps.add("Account type : " + AccountType);
		accountsLogger.info("Account type : " + AccountType);
		try {
			Account.Account_Name_1.sendKeys(AccountName);
		} catch (Exception e) {
			Account.Account_Name.sendKeys(AccountName);

		}
		test_steps.add("Account Name : " + AccountName);
		accountsLogger.info("Account Name : " + AccountName);
		try {
			Wait.explicit_wait_xpath(OR.Account_Status_1, driver);
			Wait.WaitForElement(driver, OR.Account_Status_1);
			Select sel = new Select(Account.Account_Status_1);
			sel.selectByVisibleText(AccountStatus);
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.Account_Status, driver);
			Wait.WaitForElement(driver, OR.Account_Status);
			Select sel = new Select(Account.Account_Status);
			sel.selectByVisibleText(AccountStatus);
		}
		test_steps.add("Account Status : " + AccountStatus);
		accountsLogger.info("Acount Status: " + AccountStatus);
		try {
			Account.Search_AccountNumber.sendKeys(AccountNumber);
		} catch (Exception e) {
			Account.Account_Number.sendKeys(AccountNumber);
		}
		test_steps.add("Account Number : " + AccountNumber);
		accountsLogger.info("Account Number : " + AccountNumber);

		Account.Account_Search.click();
		test_steps.add("Click Account Search");
		accountsLogger.info("Click Account Search");

		Wait.wait2Second();
		try {
			Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.AccountSearch_LoadingModule)), 60);
		} catch (Exception e) {

		}

		Wait.WaitForElement(driver, OR.Number_Of_Accounts);
		int count = driver.findElements(By.xpath(OR.Number_Of_Accounts)).size();

		if (count == 0) {
			test_steps.add("There are No Accounts available with Account Name'" + AccountName + "' , Account Number '"
					+ AccountNumber + "' and Account Status '" + AccountStatus + "'");
			accountsLogger.info("There are No Accounts available with Account Name'" + AccountName
					+ "' , Account Number '" + AccountNumber + "' and Account Status '" + AccountStatus + "'");
		} else {
			test_steps.add("Number of Accounts available with Account Name'" + AccountName + "' , Account Number '"
					+ AccountNumber + "' and Account Status '" + AccountStatus + "' in this page are '" + count + "'");
			accountsLogger.info("Number of Accounts available with Account Name'" + AccountName + "' , Account Number '"
					+ AccountNumber + "' and Account Status '" + AccountStatus + "' in this page are '" + count + "'");
			// Verify the account

			test_steps.add("Verifying Searched Account...");
			accountsLogger.info("Verifying Searched Account...");
			test_steps.add("Account Name...");
			accountsLogger.info("Account Name...");
			Wait.WaitForElement(driver, OR.Searched_AccountName);
			String name = Account.Searched_AccountName.getText();

			Assert.assertEquals(name, AccountName, "  Account Name missmatched  ");

			test_steps.add("Account Number...");
			accountsLogger.info("Account Number...");
			Wait.WaitForElement(driver, OR.Searched_AccountNumber);
			String number = Account.Searched_AccountNumber.getText();
			// AccountNumber="012";
			Assert.assertEquals(number, AccountNumber, "  Account Number mismatched  ");
			test_steps.add("Account Status...");
			accountsLogger.info("Account Status..");
			// AccountStatus="Inactive";
			if (!AccountStatus.equals("ALL")) {
				Wait.WaitForElement(driver, OR.Searched_AccountStatus);
				String status = Account.Searched_AccountStatus.getText();
				Assert.assertEquals(status, AccountStatus, "  Account Status mismatched  ");
			}
			test_steps.add("Account Verified");
			accountsLogger.info("Account Verified");

		}
		return test_steps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 *
	 * ' Method Name: <enterAccountNumber> ' Description: <This method will
	 * enter account number> ' Input parameters:(WebDriver, String ) ' Return
	 * value: <void> ' Created By: <Adhnan Ghaffar> ' Created On: <06/02/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void enterAccountNumber(WebDriver driver, String accountNumber) throws InterruptedException {

		Elements_Accounts createAccount = new Elements_Accounts(driver);
		createAccount.enterAccountNumber.clear();
		createAccount.enterAccountNumber.sendKeys(accountNumber);
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 *
	 * ' Method Name: <selectPropertyFromAccount> ' Description: <This method
	 * will select property from the account> ' Input parameters:(WebDriver,
	 * String ) ' Return value: ArrayList<String> ' Created By: <Adhnan Ghaffar>
	 * ' Created On: <06/02/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> selectPropertyFromAccount(WebDriver driver, String selectProperty)
			throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Accounts accountElement = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.SELECT_PROPERTY_FROM_ACCOUNT);
		Wait.waitForElementToBeClickable(By.xpath(OR.SELECT_PROPERTY_FROM_ACCOUNT), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.SELECT_PROPERTY_FROM_ACCOUNT), driver);

		new Select(accountElement.selectPropertyFromAccount).selectByVisibleText(selectProperty);
		testSteps.add("Selected property: " + selectProperty);
		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 *
	 * ' Method Name: <saveAfterAccount> ' Description: <This method will save
	 * account after making some changes after creation of the account> ' Input
	 * parameters:(WebDriver, String ) ' Return value: ArrayList<String> '
	 * Created By: <Adhnan Ghaffar> ' Created On: <06/02/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> saveAfterAccount(WebDriver driver, String accountName, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Account_save);
		Wait.waitForElementToBeClickable(By.xpath(OR.Account_save), driver);
		Utility.ScrollToElement(CreateAccount.Account_save, driver);
		CreateAccount.Account_save.click();
		test_steps.add("Click on Save");
		accountsLogger.info("Click on Save");
		String Message = "Successfully Updated Account : " + accountName;
		Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
		Wait.waitForElementToBeVisibile(By.className(OR.Toaster_Message), driver);
		//Wait.explicit_wait_visibilityof_webelement_120(CreateAccount.Toaster_Message, driver);
		String Toast_Message = CreateAccount.Toaster_Message.getText();
		test_steps.add(Message);
		/*String dirty = "(//img[@src='scripts/innCenter/V4/server/dirty.png'])[7]";
		if (driver.findElement(By.xpath(dirty)).isDisplayed()
				|| Toast_Message.contains("Please fill in all the mandatory fields")) {
			accountsLogger.info("************* Please fill in all the mandatory fields ************");
			test_steps.add("Please fill in all the mandatory fields");
		} else {
			test_steps.add(Toast_Message);
			accountsLogger.info(Toast_Message);
			accountsLogger.info(Message);
			assertEquals(Toast_Message, Message, "Account Updation Failed");
		}*/
		return test_steps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 *
	 * ' Method Name: <getBalance> ' Description: <This method will get the
	 * balance of the label> ' Input parameters:(WebDriver, String ) ' Return
	 * value: String ' Created By: <Adhnan Ghaffar> ' Created On: <06/02/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String getBalance(WebDriver driver, String label) throws InterruptedException {
		Elements_Accounts Account = new Elements_Accounts(driver);
		String xpath = "//label[text()='" + label + "']//parent::div//span[@class='pamt']";
		Wait.WaitForElement(driver, xpath);
		WebElement amount = driver.findElement(By.xpath(xpath));
		Wait.explicit_wait_visibilityof_webelement(amount, driver);
		Utility.ScrollToElement(amount, driver);
		return amount.getText().trim();
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 *
	 * ' Method Name: <selectPropertyInPaymentDetailPopup> ' Description: <This
	 * method will select property in Payment Details Popup> ' Input
	 * parameters:(WebDriver, String ) ' Return value: ArrayList<String> '
	 * Created By: <Adhnan Ghaffar> ' Created On: <06/02/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> selectPropertyInPaymentDetailPopup(WebDriver driver, String PropertyName)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		String PropertySelectDropDown = "(//select[contains(@data-bind,'options: $parent.drpPropertyList')])[2]";
		WebElement PropertySelect = null;
		try {
			accountsLogger.info("In try");
			PropertySelect = driver.findElement(By.xpath(PropertySelectDropDown));
			Wait.explicit_wait_visibilityof_webelement_120(PropertySelect, driver);

		} catch (Exception e) {
			accountsLogger.info("In catch");
			PropertySelectDropDown = "//select[contains(@data-bind,'options: $parent.drpPropertyList')]";
			PropertySelect = driver.findElement(By.xpath(PropertySelectDropDown));
			Wait.explicit_wait_visibilityof_webelement_120(PropertySelect, driver);
		}
		Wait.explicit_wait_elementToBeClickable(PropertySelect, driver);
		Utility.ScrollToElement(PropertySelect, driver);
		new Select(PropertySelect).selectByVisibleText(PropertyName);
		test_steps.add(PropertyName + " selected in Payment Details");
		String selectedOption = new Select(PropertySelect).getFirstSelectedOption().getText();
		Assert.assertEquals(PropertyName, selectedOption);
		test_steps.add("Selected Property Verified in Payment Details");

		return test_steps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: mailingInfo ' Description: Enter all mailing info first
	 * name, last name, phone number, address, email, city, state, postal code '
	 * Input parameters:
	 * Webdriver,String,String,String,String,String,String,String,String '
	 * Return value: ArrayList<String> ' Created By: Adnan Ghaffar ' Created On:
	 * 11-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> mailingInfo(WebDriver driver, String accountFirstName, String accountLastName,
			String phoneNumber, String address, String email, String city, String state, String postalCode) {

		ArrayList<String> testSteps = new ArrayList<String>();
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);

		CreateAccount.Account_Enter_First_Name.sendKeys(accountFirstName);
		testSteps.add("Entered first name: " + accountFirstName);

		CreateAccount.Account_Enter_Last_Name.sendKeys(accountLastName);
		testSteps.add("Entered last name: " + accountLastName);

		CreateAccount.Account_Phone_number.sendKeys(phoneNumber);
		testSteps.add("Entered phone number: " + phoneNumber);

		CreateAccount.Account_Enter_Line1.sendKeys(address);
		testSteps.add("Entered address: " + address);

		CreateAccount.Account_Enter_Email.sendKeys(email);
		testSteps.add("Entered email: " + email);

		CreateAccount.Account_Enter_City.sendKeys(city);
		testSteps.add("Entered city: " + city);

		new Select(CreateAccount.Select_Account_State).selectByVisibleText(state);
		testSteps.add("Entered state: " + state);

		CreateAccount.Account_Enter_PostalCode.sendKeys(postalCode);
		testSteps.add("Entered postal code: " + postalCode);

		return testSteps;
	}

	public String getDefaultAccountNumber(WebDriver driver) throws InterruptedException {

		String acc = driver.findElement(By.xpath("(//input[@placeholder='Account Number'])[1]")).getAttribute("value");
		accountsLogger.info("Account Number : " + acc);
		return acc;

	}

	public ArrayList<String> clickOnFolioOptions(WebDriver driver) {
		ArrayList<String> testSteps = new ArrayList<>();
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, NewAccount.folioOptionsLabel);
		Wait.waitForElementToBeVisibile(By.xpath(NewAccount.folioOptionsLabel), driver);
		Wait.waitForElementToBeClickable(By.xpath(NewAccount.folioOptionsLabel), driver);
		CreateAccount.folioOptionsLabel.click();
		testSteps.add("Click on folio options");
		accountsLogger.info("Click on folio options");
		return testSteps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectNoShowPolicy> ' Description: <This method will
	 * select Deposit policy inside group> ' Input parameters: <WebDriver
	 * driver, String policyName> ' Return value: <ArrayList<String>> ' Created
	 * By: <Farhan Ghaffar> ' Created On: <06/15/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> selectDepositPolicy(WebDriver driver, String policyName) {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, NewAccount.folioOptionDepositPolicy);
		Wait.waitForElementToBeVisibile(By.xpath(NewAccount.folioOptionDepositPolicy), driver);
		Wait.waitForElementToBeClickable(By.xpath(NewAccount.folioOptionDepositPolicy), driver);
		new Select(CreateAccount.folioOptionDepositPolicy).selectByVisibleText(policyName);
		test_steps.add("Selected Deposit Policy : " + policyName);
		accountsLogger.info("Selected Deposit Policy : " + policyName);
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyDepositPolicy> ' Description: <This method will
	 * verify selected deposit policy inside group> ' Input parameters:
	 * <WebDriver driver, String policyName> ' Return value: <ArrayList<String>>
	 * ' Created By: <Farhan Ghaffar> ' Created On: <06/15/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> verifyDepositPolicy(WebDriver driver, String policyName) {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, NewAccount.folioOptionDepositPolicy);
		Wait.waitForElementToBeVisibile(By.xpath(NewAccount.folioOptionDepositPolicy), driver);
		Wait.waitForElementToBeClickable(By.xpath(NewAccount.folioOptionDepositPolicy), driver);
		String foundOption = new Select(CreateAccount.folioOptionDepositPolicy).getFirstSelectedOption().getText();
		assertEquals(foundOption, policyName, "Failed to verify seleted NoShow policy");
		test_steps.add("Successfully Verified Deposit Policy : " + policyName);
		accountsLogger.info("Successfully Verified Deposit Policy : " + policyName);
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectNoShowPolicy> ' Description: <This method will
	 * select noshow policy inside group> ' Input parameters: <WebDriver driver,
	 * String policyName> ' Return value: <ArrayList<String>> ' Created By:
	 * <Farhan Ghaffar> ' Created On: <06/15/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> selectNoShowPolicy(WebDriver driver, String policyName) {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, NewAccount.folioOptionNoShowPolicy);
		Wait.waitForElementToBeVisibile(By.xpath(NewAccount.folioOptionNoShowPolicy), driver);
		Wait.waitForElementToBeClickable(By.xpath(NewAccount.folioOptionNoShowPolicy), driver);
		new Select(CreateAccount.folioOptionNoShowPolicy).selectByVisibleText(policyName);
		test_steps.add("Selected No Show Policy : " + policyName);
		accountsLogger.info("Selected No Show Policy : " + policyName);
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyNoShowPolicy> ' Description: <This method will
	 * verify selected noshow policy inside group> ' Input parameters:
	 * <WebDriver driver, String policyName> ' Return value: <ArrayList<String>>
	 * ' Created By: <Farhan Ghaffar> ' Created On: <06/15/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> verifyNoShowPolicy(WebDriver driver, String policyName) {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups groups = new Elements_Groups(driver);

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, NewAccount.folioOptionNoShowPolicy);
		Wait.waitForElementToBeVisibile(By.xpath(NewAccount.folioOptionNoShowPolicy), driver);
		Wait.waitForElementToBeClickable(By.xpath(NewAccount.folioOptionNoShowPolicy), driver);
		String foundOption = new Select(CreateAccount.folioOptionNoShowPolicy).getFirstSelectedOption().getText();
		assertEquals(foundOption, policyName, "Failed to verify seleted NoShow policy");
		test_steps.add("Successfully Verified NoShow Policy : " + policyName);
		accountsLogger.info("Successfully Verified NoShow Policy : " + policyName);
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectCheckInPolicy> ' Description: <This method will
	 * select CheckIn policy inside group> ' Input parameters: <WebDriver
	 * driver, String policyName> ' Return value: <ArrayList<String>> ' Created
	 * By: <Farhan Ghaffar> ' Created On: <06/15/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> selectCheckInPolicy(WebDriver driver, String policyName) {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, NewAccount.folioOptionCheckInPolicy);
		Wait.waitForElementToBeVisibile(By.xpath(NewAccount.folioOptionCheckInPolicy), driver);
		Wait.waitForElementToBeClickable(By.xpath(NewAccount.folioOptionCheckInPolicy), driver);
		new Select(CreateAccount.folioOptionCheckInPolicy).selectByVisibleText(policyName);
		test_steps.add("Selected CheckIn Policy : " + policyName);
		accountsLogger.info("Selected CheckIn Policy : " + policyName);
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyCheckInPolicy> ' Description: <This method will
	 * verify selected CheckIn policy inside group> ' Input parameters:
	 * <WebDriver driver, String policyName> ' Return value: <ArrayList<String>>
	 * ' Created By: <Farhan Ghaffar> ' Created On: <06/15/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> verifyCheckInPolicy(WebDriver driver, String policyName) {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, NewAccount.folioOptionCheckInPolicy);
		Wait.waitForElementToBeVisibile(By.xpath(NewAccount.folioOptionCheckInPolicy), driver);
		Wait.waitForElementToBeClickable(By.xpath(NewAccount.folioOptionCheckInPolicy), driver);
		String foundOption = new Select(CreateAccount.folioOptionCheckInPolicy).getFirstSelectedOption().getText();
		assertEquals(foundOption, policyName, "Failed to verify seleted NoShow policy");
		test_steps.add("Successfully Verified CheckIn Policy : " + policyName);
		accountsLogger.info("Successfully Verified CheckIn Policy : " + policyName);
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectCancelationPolicy> ' Description: <This method will
	 * first delete all pre-selected policies then select cancelation policy
	 * inside group> ' Input parameters: <WebDriver driver, String policyName> '
	 * Return value: <ArrayList<String>> ' Created By: <Farhan Ghaffar> '
	 * Created On: <06/11/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> selectCancellationPolicy(WebDriver driver, String policyName) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);

		Wait.WaitForElement(driver, NewAccount.folioOptionCancellationPolicy);
		Wait.waitForElementToBeVisibile(By.xpath(NewAccount.folioOptionCancellationPolicy), driver);
		Wait.waitForElementToBeClickable(By.xpath(NewAccount.folioOptionCancellationPolicy), driver);
		Utility.ScrollToElement_NoWait(CreateAccount.folioOptionCancellationPolicy, driver);
		CreateAccount.folioOptionCancellationPolicy.click();
		accountsLogger.info("FolioOptionCancellationPolicy clicked");

		Wait.WaitForElement(driver, NewAccount.folioOptionCancellationPolicyListSource);
		Wait.waitForElementToBeVisibile(By.xpath(NewAccount.folioOptionCancellationPolicyListSource), driver);
		Wait.waitForElementToBeClickable(By.xpath(NewAccount.folioOptionCancellationPolicyListSource), driver);
		new Select(CreateAccount.folioOptionCancellationPolicyListSource).selectByVisibleText(policyName);

		Wait.waitForElementToBeClickable(By.xpath(NewAccount.folioOptionCancellationPolicyPickBtn), driver);
		CreateAccount.folioOptionCancellationPolicyPickBtn.click();

		Wait.waitForElementToBeClickable(By.xpath(NewAccount.folioOptionCancellationPolicyDoneBtn), driver);
		Utility.ScrollToElement_NoWait(CreateAccount.folioOptionCancellationPolicyDoneBtn, driver);
		CreateAccount.folioOptionCancellationPolicyDoneBtn.click();
		test_steps.add("Selected Cancellation Policy : " + policyName);
		accountsLogger.info("Selected Cancellation Policy : " + policyName);
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyCancellationPolicy> ' Description: <This method
	 * will verify selected Cancellation policy inside group> ' Input
	 * parameters: <WebDriver driver, String policyName> ' Return value:
	 * <ArrayList<String>> ' Created By: <Farhan Ghaffar> ' Created On:
	 * <06/15/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> verifyCancellationPolicy(WebDriver driver, String policyName) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups groups = new Elements_Groups(driver);

		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, NewAccount.folioOptionCancellationPolicy);
		Wait.waitForElementToBeVisibile(By.xpath(NewAccount.folioOptionCancellationPolicy), driver);
		Wait.waitForElementToBeClickable(By.xpath(NewAccount.folioOptionCancellationPolicy), driver);
		Utility.ScrollToElement_NoWait(CreateAccount.folioOptionCancellationPolicy, driver);

		String foundOption = CreateAccount.folioOptionCancellationPolicy.getAttribute("value");
		assertEquals(foundOption, policyName, "Failed to verify seleted Cancellation policy");
		test_steps.add("Successfully Verified Cancellation Policy : " + policyName);
		accountsLogger.info("Successfully Verified Cancellation Policy : " + policyName);
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 *
	 * ' Method Name: <AccountSave> ' Description: <This method will Save
	 * account when created/Modification: remove unnecessary wait> ' Input
	 * parameters:(WebDriver) ' Return value: void ' Updated By: <Farhan
	 * Ghaffar> ' Updated On: <06/02/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> accountSave(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Account_Save_Button);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Account_Save_Button), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Account_Save_Button), driver);
		CreateAccount.Account_Save_Button.click();
		test_steps.add("Click Save ");
		accountsLogger.info("Click Save");

		return test_steps;
	}

	public void accountSearch(WebDriver driver, ArrayList<String> test_steps, String account)
			throws InterruptedException {
		Elements_Accounts CreateAccount = new Elements_Accounts(driver);

		String accountName = "//input[@data-bind='value: AccountName']";
		String search = "//div[@id='accountSearchFilter']//button[contains(text(),'Search')]";

		System.out.println("in search");
		Wait.WaitForElement(driver, accountName);
		Wait.waitForElementToBeVisibile(By.xpath(accountName), driver);
		Wait.waitForElementToBeClickable(By.xpath(accountName), driver);
		driver.findElement(By.xpath(accountName)).clear();
		driver.findElement(By.xpath(accountName)).sendKeys(account);
		Wait.WaitForElement(driver, search);
		driver.findElement(By.xpath(search)).click();
		String acc = "//td[3]/a[contains(text(),'" + account + "')]";

		Wait.WaitForElement(driver, acc);
		Wait.waitForElementToBeVisibile(By.xpath(acc), driver);
		Wait.waitForElementToBeClickable(By.xpath(acc), driver);
		driver.findElement(By.xpath(acc)).click();

		test_steps.add("Opening the account : " + account);
		Wait.wait1Second();
		driver.navigate().refresh();
		String verifyopened = "//div[@data-bind='foreach: AccountDetails']//span[contains(text(),'" + account + "')]";
		Wait.WaitForElement(driver, verifyopened);
		Wait.waitForElementToBeVisibile(By.xpath(verifyopened), driver);
		Wait.waitForElementToBeClickable(By.xpath(verifyopened), driver);

	}

	public String getAccountStatus(WebDriver driver) throws InterruptedException {
		Elements_Accounts acc = new Elements_Accounts(driver);
		String accountStatus = "";

		Wait.explicit_wait_visibilityof_webelement(acc.Account_Status_Active, driver);
		Wait.wait5Second();
		List<WebElement> accountStatusList = new Select(acc.Account_Status_Active).getOptions();
		accountStatus = accountStatusList.get(0).getText();
		System.out.print("Account Status Is:" + accountStatus);
		return accountStatus;

	}

	public ArrayList<String> searchAccount(WebDriver driver, ExtentTest test1, String AccountType, String AccountName,
			String AccountNumber, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);
		driver.navigate().refresh();
		try {
			Wait.explicit_wait_xpath(OR.AccountType, driver);
			Wait.WaitForElement(driver, OR.AccountType);
			Wait.explicit_wait_visibilityof_webelement_150(Account.AccountType, driver);
			Select sel = new Select(Account.AccountType);
			sel.selectByVisibleText(AccountType);
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.Account_Type, driver);
			Wait.WaitForElement(driver, OR.Account_Type);
			Wait.explicit_wait_visibilityof_webelement_150(Account.Account_Type, driver);
			Select sel = new Select(Account.Account_Type);
			sel.selectByVisibleText(AccountType);
		}
		test_steps.add("Account type : " + AccountType);
		accountsLogger.info("Account type : " + AccountType);
		try {
			Account.Account_Name_1.sendKeys(AccountName);
		} catch (Exception e) {
			Account.Account_Name.sendKeys(AccountName);

		}
		test_steps.add("Account Name : " + AccountName);
		accountsLogger.info("Account Name : " + AccountName);

		try {
			Account.Search_AccountNumber.sendKeys(AccountNumber);
		} catch (Exception e) {
			Account.Account_Number.sendKeys(AccountNumber);
		}
		test_steps.add("Account Number : " + AccountNumber);
		accountsLogger.info("Account Number : " + AccountNumber);

		Account.Account_Search.click();
		test_steps.add("Click Account Search");
		accountsLogger.info("Click Account Search");

		Wait.wait2Second();
		try {
			Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.AccountSearch_LoadingModule)), 60);
		} catch (Exception e) {

		}
		return test_steps;
	}

	public ArrayList<String> verifySearchedAccountDetails(WebDriver driver, String AccountName, String AccountNumber,
			String AccountSince, String Reservation, String Rooms, String AccountStatus, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Accounts Account = new Elements_Accounts(driver);
		Wait.WaitForElement(driver, OR.Number_Of_Accounts);
		int count = driver.findElements(By.xpath(OR.Number_Of_Accounts)).size();

		if (count == 0) {
			test_steps.add("There are No Accounts available with Account Name'" + AccountName + "' , Account Number '"
					+ AccountNumber + "' and Account Status '" + AccountStatus + "'");
			accountsLogger.info("There are No Accounts available with Account Name'" + AccountName
					+ "' , Account Number '" + AccountNumber + "' and Account Status '" + AccountStatus + "'");
		} else {
			test_steps.add("Number of Accounts available with Account Name'" + AccountName + "' , Account Number '"
					+ AccountNumber + "' and Account Status '" + AccountStatus + "' in this page are '" + count + "'");
			accountsLogger.info("Number of Accounts available with Account Name'" + AccountName + "' , Account Number '"
					+ AccountNumber + "' and Account Status '" + AccountStatus + "' in this page are '" + count + "'");
			// Verify the account

			test_steps.add("Verifying Searched Account...");
			accountsLogger.info("Verifying Searched Account...");

			Wait.WaitForElement(driver, OR.Searched_AccountName);
			String accountNameFound = Account.Searched_AccountName.getText();
			Assert.assertEquals(accountNameFound, AccountName, "  Account Name missmatched  ");
			test_steps.add("Account Name: " + AccountName);
			accountsLogger.info("Account Name: " + AccountName);

			Wait.WaitForElement(driver, OR.Searched_AccountNumber);
			String accountNumberFound = Account.Searched_AccountNumber.getText();
			Assert.assertEquals(accountNumberFound, AccountNumber, "  Account Number mismatched  ");
			test_steps.add("Account Number: " + accountNumberFound);
			accountsLogger.info("Account Number: " + accountNumberFound);

			Wait.WaitForElement(driver, NewAccount.SEARCHEDACCOUNT_SINCEDATE);
			String accountSinceFound = Account.SEARCHEDACCOUNT_SINCEDATE.getText();
			Assert.assertEquals(accountSinceFound, AccountSince, "  Account Since Date mismatched  ");
			test_steps.add("Account Since: " + accountSinceFound);
			accountsLogger.info("Account Since: " + accountSinceFound);

			Wait.WaitForElement(driver, NewAccount.SEARCHEDACCOUNT_ROOMS);
			String roomsFound = Account.SEARCHEDACCOUNT_ROOMS.getText();
			Assert.assertEquals(roomsFound, Rooms, "  Account Rooms mismatched  ");
			test_steps.add("Account Rooms: " + roomsFound);
			accountsLogger.info("Account Rooms: " + roomsFound);

			Wait.WaitForElement(driver, NewAccount.SEARCHEDACCOUNT_RESERVATIONS);
			String reservationsFound = Account.SEARCHEDACCOUNT_RESERVATIONS.getText();
			Assert.assertEquals(reservationsFound, Reservation, "  Account Reservations  mismatched  ");
			test_steps.add("Account Reservation: " + reservationsFound);
			accountsLogger.info("Account Reservation: " + reservationsFound);

			if (!AccountStatus.equals("ALL")) {
				Wait.WaitForElement(driver, OR.Searched_AccountStatus);
				String statusFound = Account.Searched_AccountStatus.getText();
				Assert.assertEquals(statusFound, AccountStatus, "  Account Status mismatched  ");
				test_steps.add("Account Status: " + statusFound);
				accountsLogger.info("Account Status: " + statusFound);
			}
		}
		return test_steps;
	}

}
