package com.innroad.inncenter.interfaces;

public class NewAccount {

	public static final String Account_GetStatement_Icon = "//div[@class='AccountDetail']//i[@id='accStatementEn']";
	public static final String Account_NewPeriodPopup = "//div[contains(@data-bind,'NewPeriodPopUpHandle')]";
	public static final String AccountStatement_CalanderIcon = "//div[contains(@data-bind,'NewPeriodPopUpHandle')]//i[@class='fa fa-calendar']";
	public static final String AccountStatement_SelectToday = "//div[contains(@data-bind,'NewPeriodPopUpHandle')]//div[@class='datepicker-days']//td[@class='today day']";
	public static final String AccountStatementSelect_SelectDate = "//div[contains(@data-bind,'NewPeriodPopUpHandle')]//button[contains(@data-bind,'SelectNewPeriod')]";
	public static final String Accounts_AccountStatement = "//div[contains(@data-bind,' reportViewer')]//table";
	public static final String Accounts_AccountStatement_Confirm = "//div[contains(@data-bind,' reportViewer')]//table//span[text()='Confirm']";
	public static final String AccountStatement_ModuleLoading = "(//div[@class='AccountDetail']//div[@class ='modules_loading'])[7]";
	public static final String Account_SelectPeriod = "//select[contains(@data-bind,'SelectedPeriod')]";
	public static final String Account_SelectStatus = "(//div[@class='AccountDetail']//select[contains(@data-bind,'StatusName')])[1]";

	public static final String AccountHeader_AccountName = "(//div[@class='AccountDetail']//span[contains(@data-bind,'Account.AccountName ')])[1]";
	public static final String AccountHeader_AccountNumber = "(//div[@class='AccountDetail']//span[contains(@data-bind,'AccountNoToDisplay')])[1]";
	public static final String AccountNotes_DueDate = "//div[@class='modal-scrollable']//div[@id='NotesDetailPopup']//input[contains(@data-bind,'DueDate')]";

	public static final String Account_DirtyTab = "(//div[@class='sec_nav_in container']//img[contains(@data-bind,'isDirty')])[last()]";
	public static final String AddedNote_DueDate = "//tr[@data-bind='visible : showCancelledNotes()']//child::span[contains(@data-bind,'DueDate')]";
	public static final String AddedNote_LoginId = "//tr[@data-bind='visible : showCancelledNotes()']//child::span[contains(@data-bind,'LoginId')]";
	public static final String AccountSave_ModuleLoading = "//div[contains(@data-bind,'visible: showLoadingInsideAccountDetail')]//div[@class='modules_loading']";

	public static final String Account_SelectProperty = "//div[@data-bind='foreach: AccountDetails']//select[contains(@data-bind,'PropertyName')]";
	public static final String Account_PaymentMethodsList = "//div[@data-bind='foreach: AccountDetails']//select[contains(@data-bind,'PaymentMethodsList')]";
	public static final String Account_BillingAccountNumber = "//div[@data-bind='foreach: AccountDetails']//input[@placeholder='Billing Account Number']";
	public static final String Account_BillingAccountExpiryDate = "//div[@data-bind='foreach: AccountDetails']//input[contains(@data-bind,'BillingCreditCardExpDate')]";
	public static final String PaymentPopup_Note = "//div[@id='AccountPaymetItemDetail']//textarea[@data-bind='value: Notes']";
	public static final String PaymentDetailNotes_Account = "//textarea[contains(@data-bind,'value: Notes')]";

	// account billing info
	public static final String SelectBillType = "//select[contains(@data-bind,'options: PaymentMethodsList')]";
	public static final String inputAccNumber = "//input[@placeholder='Billing Account Number']";
	public static final String inputCardExpDate = "//input[@placeholder='Exp Date']";
	public static final String inputBillingNotes = "(//input[@placeholder='Billing Notes'])[2]";
	public static final String PaymentModuleLoading = "//div[@id='AccountPaymetItemDetail']//div[@class='modules_loading']";
	public static final String selectAutherizationType = "//div[@id='AccountPaymetItemDetail']//select[contains(@data-bind,'options: $parent.TransactionTypeArray')]";
	public static final String selectProperty = "//select[contains(@data-bind,'options: $root.PropertyList,')]";
	public static final String selectPaymentProperty = "//span[text()='Payment Details']//..//..//following-sibling::div//select[contains(@data-bind,'options: $parent.drpPropertyList')]";

	public static final String enterPaymentNotes = "//div[@id='AccountPaymetItemDetail']//textarea[@data-bind='value: Notes']";
	public static final String chkVoidLineItem = "//input[contains(@data-bind,'checked: $root.DisplayVoidItems')]";
}
