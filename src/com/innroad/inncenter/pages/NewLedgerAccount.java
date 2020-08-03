package com.innroad.inncenter.pages;

public class NewLedgerAccount {

	public static final String SelectLedgerAccountType = "//tr//td//input[@class='TextDefault']//parent::td//following::select[contains(@id,'drpSysClientLedgerType')]";
	public static final String SelectLedgerAccountStatus = "//tr//td//input[@class='TextDefault']//parent::td//following::select[contains(@id,'drpSysClientLedgerStatus')]";
	public static final String LedgerAccount_DefaultAmount = "//tr//td//input[@class='TextDefault']//parent::td//following::input[contains(@id,'txtSysClientDefaultAmount')]";
	public static final String LedgerAccount_SelectStatusType = "MainContent_drpActive";
	public static final String LedgerAccount_PropertyTab = "//input[@id='MainContent_btnProperty']";
	public static final String Ledger_Accounts_EditButtonClick_PropertyTab = "//input[@id='MainContent_btnEditProperty']";
	public static final String Ledger_Accounts_SelectStatus = "//select[@id='MainContent_dgPropLedgAccounts_drpClientLedgerStatus_1']";
	public static final String Ledger_Accounts_SaveButtonClick_PropertyTab = "//input[@id='MainContent_btnSaveProperty']";
	public static final String LedgerAccount_MasterTab = "//input[@id='MainContent_btnMaster']";
}
