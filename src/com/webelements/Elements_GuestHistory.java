package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class Elements_GuestHistory {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_GuestHistory(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = OR.reservation_Menu)
	public WebElement reservation_Menu;

	@FindBy(xpath = OR.Guest_History)
	public WebElement Guest_History;

	@FindBy(xpath = OR.newAccount)
	public WebElement newAccount;

	@FindBy(xpath = OR.selectAccountSalutation)
	public WebElement selectAccountSalutation;

	@FindBy(xpath = OR.selectAccountSalutation_1)
	public WebElement selectAccountSalutation_1;

	@FindBy(xpath = OR.accountFirstName)
	public WebElement accountFirstName;

	@FindBy(xpath = OR.accountNumber)
	public WebElement accountNumber;

	@FindBy(xpath = OR.accountSince)
	public WebElement accountSince;

	@FindBy(xpath = OR.Select_Market_Segment)
	public WebElement Select_Market_Segment;

	@FindBy(xpath = OR.Select_Referrals)
	public WebElement Select_Referrals;

	@FindBy(xpath = OR.closeReservation)
	public WebElement closeReservation;

	@FindBy(xpath = OR.guestHistoryAccountNo)
	public WebElement guestHistoryAccountNo;

	@FindBy(xpath = OR.enterAccountFName)
	public WebElement enterAccountFName;

	@FindBy(xpath = OR.enterGAccountLastName)
	public WebElement enterGAccountLastName;

	@FindBy(xpath = OR.enterGuestHistoryAccountNo)
	public WebElement enterGuestHistoryAccountNo;

	@FindBy(xpath = OR.GuestHistory_AccountNumber)
	public WebElement GuestHistory_AccountNumber;

	@FindBy(xpath = OR.clickSearchButton)
	public WebElement clickSearchButton;

	@FindBy(xpath = OR.selectGuestHistoryAccount)
	public WebElement selectGuestHistoryAccount;

	@FindBy(xpath = OR.SearcehdGuestHistoryAccountNumber)
	public WebElement SearcehdGuestHistoryAccountNumber;

	@FindBy(xpath = OR.deleteButton)
	public WebElement deleteButton;

	@FindBy(xpath = OR.newReservationButton)
	public WebElement newReservationButton;

	@FindBy(xpath = OR.New_Reservation_Page_Load)
	public WebElement New_Reservation_Page_Load;

	@FindBy(xpath = OR.closeGuestHistoryRes)
	public WebElement closeGuestHistoryRes;

	@FindBy(xpath = OR.ClickSearchedGuestProfile)
	public WebElement ClickSearchedGuestProfile;

	@FindBy(xpath = OR.UnsavedData_YesBtn)
	public WebElement UnsavedData_YesBtn;

	@FindBy(xpath = OR.GuestHis_CombineCheckBox)
	public WebElement GuestHis_CombineCheckBox;

	@FindBy(xpath = OR.GuestHis_CombineCheckBox1)
	public WebElement GuestHis_CombineCheckBox1;

	@FindBy(xpath = OR.ReservationTab_GuestAccount)
	public WebElement ReservationTab_GuestAccount;

	@FindBy(xpath = OR.GuestHistoryAccountStatus)
	public WebElement GuestHistoryAccountStatus;

	@FindBy(xpath = OR.GuestHistoryAccount_BillType)
	public WebElement GuestHistoryAccount_BillType;

	@FindBy(xpath = OR.GuestHistoryAccount_BillingAccountNum)
	public WebElement GuestHistoryAccount_BillingAccountNum;

	@FindBy(xpath = OR.GuestHistoryAccount_ExpDate)
	public WebElement GuestHistoryAccount_ExpDate;

	@FindBy(xpath = OR.GuestHistoryAccount_BillingNotes)
	public WebElement GuestHistoryAccount_BillingNotes;

	@FindBy(xpath = OR.SearchGuestHistoryAccountStatus)
	public WebElement SearchGuestHistoryAccountStatus;
	
	@FindBy(xpath = OR.NewGuestHistoryButton)
	public WebElement NewGuestHistoryButton;
	
	@FindBy(xpath = OR.guestAccountFirstName)
	public WebElement guestAccountFirstName;

	@FindBy(xpath = OR.guestAccountLastName)
	public WebElement guestAccountLastName;

	@FindBy(xpath = OR.clickDecyptCCNumberImg)
	public WebElement clickDecyptCCNumberImg;

	@FindBy(xpath = OR.billingAccountNumberInput)
	public WebElement billingAccountNumberInput;

	@FindBy(xpath = OR.guestInfoSaveButton)
	public WebElement guestInfoSaveButton;

	@FindBy(xpath = OR.reservationTabGuestAccount)
	public WebElement reservationTabGuestAccount;
	
	@FindBy(xpath = OR.GUEST_ACCOUNT_FIRST_NAME)
	public WebElement GUEST_ACCOUNT_FIRST_NAME;
	
	@FindBy(xpath = OR.GUEST_ACCOUNT_LAST_NAME)
	public WebElement GUEST_ACCOUNT_LAST_NAME;

}
