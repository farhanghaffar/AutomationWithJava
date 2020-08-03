package com.innroad.inncenter.pages;

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

	public static final String ACCOUNT_SELECT_PROPERTY = "//div[@data-bind='foreach: AccountDetails']//select[contains(@data-bind,'PropertyName')]";
	public static final String ACCOUNT_PAYMENT_METHOD_LIST = "//div[@data-bind='foreach: AccountDetails']//select[contains(@data-bind,'PaymentMethodsList')]";
	public static final String ACCOUNT_BILLING_ACCOUNT_NUMBER = "//div[@data-bind='foreach: AccountDetails']//input[@placeholder='Billing Account Number']";
	public static final String ACCOUNT_BILLING_ACCOUNT_EXPIRAY_DATE = "//div[@data-bind='foreach: AccountDetails']//input[contains(@data-bind,'BillingCreditCardExpDate')]";
	
	
	public static final String SEARCHEDACCOUNT_SINCEDATE = "//span[contains(@data-bind,'text: moment(AccountSince).isValid()')]";
	public static final String SEARCHEDACCOUNT_ROOMS = "//span[contains(@data-bind,'text: Rooms')]";

	public static final String SEARCHEDACCOUNT_RESERVATIONS = "//span[contains(@data-bind,'text: Reservation')]";

	public static final String folioOptionsLabel = "//a[.='Folio Options']";
	public static final String folioOptionDepositPolicy = "(//select[contains(@data-bind, 'DepositPolicyList')])[1]";
	public static final String folioOptionCheckInPolicy = "(//select[contains(@data-bind, 'CheckInPolicyList')])[1]";
	public static final String  folioOptionNoShowPolicy = "(//select[contains(@data-bind, 'NoShowPolicyList')])[1]";
	
	public static final String folioOptionCancellationPolicy = "(//input[contains(@data-bind, 'selectedCancelPolicyString')])[1]";
	public static final String folioOptionCancellationPolicyListSource = "(//td//select[contains(@data-bind, 'filteredItems')])[1]";
	public static  final String folioOptionCancellationPolicyPickBtn = "(//button[contains(@data-bind, 'click: AddNew')])[1]";
	public static final String folioOptionCancelationPolicyDeleteAllBtn = "(//button[contains(@data-bind, 'click: DiscardAll')])[1]";
	public static final String folioOptionCancellationPolicyDoneBtn = "//h4[text()='Cancel Policy Picker']//..//..//button[text()='Done']";
	
	
}
