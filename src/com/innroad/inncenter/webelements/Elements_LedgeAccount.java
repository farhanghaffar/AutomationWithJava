package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.pages.NewLedgerAccount;
import com.innroad.inncenter.properties.OR;

public class Elements_LedgeAccount {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_LedgeAccount(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);

	}

	@FindBy(xpath = OR.Create_New_LedgeAccount_ButtonClick)
	public WebElement Create_New_LedgeAccount_ButtonClick;

	@FindBy(xpath = OR.Ledger_Accounts_Name_Inputs)
	public List<WebElement> Ledger_Accounts_Name_Inputs;

	@FindBy(id = OR.Ledger_Accounts_Description_Input)
	public WebElement Ledger_Accounts_Description_Input;

	@FindBy(xpath = NewLedgerAccount.SelectLedgerAccountStatus)
	public WebElement SelectLedgerAccountStatus;

	@FindBy(xpath = NewLedgerAccount.SelectLedgerAccountType)
	public WebElement SelectLedgerAccountType;

	@FindBy(xpath = NewLedgerAccount.LedgerAccount_DefaultAmount)
	public WebElement LedgerAccount_DefaultAmount;

	@FindBy(id = OR.Ledger_Accounts_DefaultAmount)
	public WebElement Ledger_Accounts_DefaultAmount;

	@FindBy(xpath = OR.Ledger_Accounts_SaveButtonClick)
	public WebElement Ledger_Accounts_SaveButtonClick;

	@FindBy(id = OR.Ledger_Accounts_DeleteButtonClick)
	public WebElement Ledger_Accounts_DeleteButtonClick;

	@FindBy(id = OR.Ledger_Accounts_EditButtonClick)
	public WebElement Ledger_Accounts_EditButtonClick;

	@FindBy(id = OR.Ledger_Accounts_CancelButtonClick)
	public WebElement Ledger_Accounts_CancelButtonClick;

	@FindBy(xpath = OR.LedgerAccount_Chekbox)
	public List<WebElement> LedgerAccount_Chekbox;

	@FindBy(xpath = OR.Ledger_Accounts_Status)
	public List<WebElement> Ledger_Accounts_Status;

	@FindBy(id = NewLedgerAccount.LedgerAccount_SelectStatusType)
	public WebElement LedgerAccount_SelectStatusType;

	@FindBy(xpath = NewLedgerAccount.LedgerAccount_PropertyTab)
	public WebElement LedgerAccount_PropertyTab;

	@FindBy(xpath = NewLedgerAccount.Ledger_Accounts_EditButtonClick_PropertyTab)
	public WebElement Ledger_Accounts_EditButtonClick_PropertyTab;

	@FindBy(xpath = NewLedgerAccount.Ledger_Accounts_SelectStatus)
	public WebElement Ledger_Accounts_SelectStatus;

	@FindBy(xpath = NewLedgerAccount.Ledger_Accounts_SaveButtonClick_PropertyTab)
	public WebElement Ledger_Accounts_SaveButtonClick_PropertyTab;

	@FindBy(xpath = NewLedgerAccount.LedgerAccount_MasterTab)
	public WebElement LedgerAccount_MasterTab;
	
	@FindBy(xpath = OR.Ledger_Accounts_Name_1)
	public WebElement Ledger_Accounts_Name_1;
}