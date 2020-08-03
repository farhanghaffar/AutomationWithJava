package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.pages.NewAccount;
import com.innroad.inncenter.properties.OR;

public class Elements_Accounts {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_Accounts(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);

	}

	@FindBy(xpath = OR.Select_Account_Type)
	public WebElement Select_Account_Type;

	@FindBy(xpath = OR.Click_New_Account)
	public WebElement Click_New_Account;

	@FindBy(xpath = OR.GetAccount_Number)
	public WebElement GetAccount_Number;

	@FindBy(xpath = OR.Verify_New_Account_Page_Load)
	public WebElement Verify_New_Account_Page_Load;

	@FindBy(xpath = OR.Verify_New_Account_tab)
	public WebElement Verify_New_Account_tab;

	@FindBy(xpath = OR.Enter_Account_Name)
	public WebElement Enter_Account_Name;

	@FindBy(xpath = OR.Acc_Enter_Account_Name)
	public WebElement Acc_Enter_Account_Name;

	@FindBy(xpath = OR.Verify_Account_Type)
	public WebElement Verify_Account_Type;

	@FindBy(xpath = OR.Select_Market_Segment)
	public WebElement Select_Market_Segment;

	@FindBy(xpath = OR.Account_Enter_First_Name)
	public WebElement Account_Enter_First_Name;

	@FindBy(xpath = OR.Account_Phone_number)
	public WebElement Account_Phone_number;

	@FindBy(xpath = OR.Account_Enter_AltPhoneNumber)
	public WebElement Account_Enter_AltPhoneNumber;

	@FindBy(xpath = OR.Account_Enter_Line1)
	public WebElement Account_Enter_Line1;

	@FindBy(xpath = OR.Account_Enter_Line2)
	public WebElement Account_Enter_Line2;

	@FindBy(xpath = OR.Account_Enter_Line3)
	public WebElement Account_Enter_Line3;

	@FindBy(xpath = OR.Account_Enter_Email)
	public WebElement Account_Enter_Email;

	@FindBy(xpath = OR.Account_Enter_City)
	public WebElement Account_Enter_City;

	@FindBy(xpath = OR.Select_Account_State)
	public WebElement Select_Account_State;

	@FindBy(xpath = OR.Account_Enter_PostalCode)
	public WebElement Account_Enter_PostalCode;

	@FindBy(xpath = OR.Account_Check_Mailing_info)
	public WebElement Account_Check_Mailing_info;

	@FindBy(xpath = OR.Account_save)
	public WebElement Account_save;

	@FindBy(xpath = OR.Account_Save_Clsoe)
	public WebElement Account_Save_Clsoe;

	@FindBy(xpath = OR.CloseUnSavedDataTab)
	public WebElement CloseUnSavedDataTab;

	@FindBy(xpath = OR.Select_Referrals)
	public WebElement Select_Referrals;

	@FindBy(xpath = OR.Account_Enter_Last_Name)
	public WebElement Account_Enter_Last_Name;

	@FindBy(xpath = OR.Verify_Toaster_Container)
	public WebElement Verify_Toaster_Container;

	@FindBy(className = OR.Toaster_Title)
	public WebElement Toaster_Title;

	@FindBy(className = OR.Toaster_Message)
	public WebElement Toaster_Message;

	@FindBy(xpath = OR.AccountName)
	public WebElement AccountName;

	@FindBy(xpath = OR.AccountNo)
	public WebElement AccountNo;

	@FindBy(xpath = OR.AccountNo_1)
	public WebElement AccountNo_1;

	@FindBy(xpath = OR.Account_Folio)
	public WebElement Account_Folio;

	@FindBy(xpath = OR.Account_AccountTab)
	public WebElement Account_AccountTab;

	@FindBy(xpath = OR.FolioOptions)
	public WebElement FolioOptions;

	@FindBy(xpath = OR.Account_pay)
	public WebElement Account_pay;

	@FindBy(xpath = OR.Account_Add)
	public WebElement Account_Add;

	@FindBy(xpath = OR.Verify_Account_line_item)
	public WebElement Verify_Account_line_item;

	@FindBy(xpath = OR.Select_Line_item_Category)
	public WebElement Select_Line_item_Category;

	@FindBy(xpath = OR.Enter_Line_item_Amount)
	public WebElement Enter_Line_item_Amount;

	@FindBy(xpath = OR.Click_Commit)
	public WebElement Click_Commit;

	@FindBy(xpath = OR.Select_Account_paymethod)
	public WebElement Select_Account_paymethod;

	@FindBy(xpath = OR.Click_Account_Card_info)
	public WebElement Click_Account_Card_info;

	@FindBy(xpath = OR.Verify_Account_Paymnet_info_popup)
	public WebElement Verify_Account_Paymnet_info_popup;

	@FindBy(xpath = OR.Enter_Account_Card_Name)
	public WebElement Enter_Account_Card_Name;

	@FindBy(xpath = OR.Enter_CC_Account_Number)
	public WebElement Enter_CC_Account_Number;

	@FindBy(xpath = OR.Enter_ExpiryDate_Account)
	public WebElement Enter_ExpiryDate_Account;

	@FindBy(xpath = OR.Enter_CCV_Account)
	public WebElement Enter_CCV_Account;

	@FindBy(xpath = OR.Click_Ok_Account)
	public WebElement Click_Ok_Account;

	@FindBy(xpath = OR.Select_Authorization_Type_Account)
	public WebElement Select_Authorization_Type_Account;

	@FindBy(xpath = OR.Select_Authorization_Type_Account)
	public List<WebElement> Select_Authorization_Type_Accounts;

	@FindBy(xpath = OR.Enter_Change_Amount)
	public WebElement Enter_Change_Amount;

	@FindBy(xpath = OR.Click_Account_Pay_Continue)
	public WebElement Click_Account_Pay_Continue;

	@FindBy(xpath = OR.Click_Process_Account)
	public WebElement Click_Process_Account;

	@FindBy(xpath = OR.Click_AddPayment_Account)
	public WebElement Click_AddPayment_Account;

	@FindBy(xpath = OR.Account_Payment_Method)
	public WebElement Account_Payment_Method;

	@FindBy(xpath = OR.Get_Line_item_Account)
	public WebElement Get_Line_item_Account;

	@FindBy(xpath = OR.Get_Line_item_Account_1)
	public List<WebElement> Get_Line_item_Account_1;

	@FindBy(xpath = OR.Verify_Ending_Balance)
	public WebElement Verify_Ending_Balance;

	@FindBy(xpath = OR.Select_Property_lineitem)
	public WebElement Select_Property_lineitem;

	@FindBy(xpath = OR.Apply_Advance_Deposite)
	public WebElement Apply_Advance_Deposite;

	@FindBy(xpath = OR.Click_Continue_Adv_Deposite)
	public WebElement Click_Continue_Adv_Deposite;

	@FindBy(xpath = OR.Click_New_Reservation_Account)
	public WebElement Click_New_Reservation_Account;

	@FindBy(xpath = OR.Enter_House_Account_Name)
	public WebElement Enter_House_Account_Name;

	@FindBy(xpath = OR.Get_Gift_ID)
	public WebElement Get_Gift_ID;

	@FindBy(xpath = OR.Enter_Gift_Line_Item_Amount)
	public WebElement Enter_Gift_Line_Item_Amount;

	@FindBy(xpath = OR.Acc_Add_Button)
	public WebElement Acc_Add_Button;

	@FindBy(xpath = OR.Acc_Note_Enter_sub)
	public WebElement Acc_Note_Enter_sub;

	@FindBy(xpath = OR.Acc_Note_Enter_Details)
	public WebElement Acc_Note_Enter_Details;

	@FindBy(xpath = OR.Acc_Note_Select_Note_Type)
	public WebElement Acc_Note_Select_Note_Type;

	@FindBy(xpath = OR.Acc_Note_Select_Note_Status)
	public WebElement Acc_Note_Select_Note_Status;

	@FindBy(xpath = OR.Acc_Note_Action_Req)
	public WebElement Acc_Note_Action_Req;

	@FindBy(xpath = OR.Acc_Note_Save)
	public WebElement Acc_Note_Save;

	@FindBy(xpath = OR.Verify_Add_line_Notes)
	public WebElement Verify_Add_line_Notes;

	@FindBy(xpath = OR.Click_Folio_Options)
	public WebElement Click_Folio_Options;

	@FindBy(xpath = OR.Select_Account_Checkin_Policy)
	public WebElement Select_Account_Checkin_Policy;

	@FindBy(xpath = OR.AccountsPage_Reset_Btn)
	public WebElement AccountsPage_Reset_Btn;

	@FindBy(xpath = OR.AccountsPage_Click_All)
	public WebElement AccountsPage_Click_All;

	@FindBy(xpath = OR.First_Element_In_Account_SearchResults)
	public WebElement First_Element_In_Account_SearchResults;

	@FindBy(xpath = OR.Enter_AccountName_On_SearchPage)
	public WebElement Enter_AccountName_On_SearchPage;

	@FindBy(xpath = OR.Click_SearchBtn_On_AccountsPage)
	public WebElement Click_SearchBtn_On_AccountsPage;

	@FindBy(xpath = OR.Folio_Cash_Continue_Btn)
	public WebElement Folio_Cash_Continue_Btn;

	@FindBy(xpath = OR.Folio_Cash_Continue_Btn1)
	public WebElement Folio_Cash_Continue_Btn1;

	@FindBy(xpath = OR.Folio_Cash_Continue_Btn2)
	public WebElement Folio_Cash_Continue_Btn2;

	@FindBy(xpath = OR.Account_CorpAccountName)
	public WebElement Account_CorpAccountName;

	@FindBy(xpath = OR.Account_AutoApply)
	public WebElement Account_AutoApply;

	@FindBy(xpath = OR.Folio_Balance)
	public WebElement Folio_Balance;

	@FindBy(xpath = OR.Account_Name)
	public WebElement Account_Name;

	@FindBy(xpath = OR.Account_Name_1)
	public WebElement Account_Name_1;

	@FindBy(xpath = OR.SearchAccountNumber)
	public WebElement SearchAccountNumber;

	@FindBy(xpath = OR.SearchAccountName)
	public WebElement SearchAccountName;

	@FindBy(xpath = OR.Account_Number)
	public WebElement Account_Number;

	@FindBy(xpath = OR.Search_AccountNumber)
	public WebElement Search_AccountNumber;

	@FindBy(xpath = OR.Account_Search)
	public WebElement Account_Search;

	@FindBy(xpath = OR.Number_Of_Accounts)
	public WebElement Number_Of_Accounts;

	@FindBy(xpath = OR.Account_Type)
	public WebElement Account_Type;

	@FindBy(xpath = OR.AccountType)
	public WebElement AccountType;

	@FindBy(xpath = OR.Account_Status)
	public WebElement Account_Status;

	@FindBy(xpath = OR.Account_Status_Active)
	public WebElement Account_Status_Active;

	@FindBy(xpath = OR.Account_Status_1)
	public WebElement Account_Status_1;

	@FindBy(xpath = OR.NewReservation_Button)
	public WebElement NewReservation_Button;

	@FindBy(xpath = OR.Account_Save_Button)
	public WebElement Account_Save_Button;

	@FindBy(xpath = OR.Enter_AccountNumber_On_SearchPages)
	public WebElement Enter_AccountNumber_On_SearchPages;

	@FindBy(xpath = OR.Search_Accounts_Button)
	public WebElement Search_Accounts_Button;

	@FindBy(xpath = OR.Search_Account_Result)
	public WebElement Search_Account_Result;

	@FindBy(xpath = OR.Search_Account_Checkbox)
	public WebElement Search_Account_Checkbox;

	@FindBy(xpath = OR.Account_Delete_Button)
	public WebElement Account_Delete_Button;

	@FindBy(xpath = OR.Searched_AccountName)
	public WebElement Searched_AccountName;

	@FindBy(xpath = OR.Searched_AccountNumber)
	public WebElement Searched_AccountNumber;

	@FindBy(xpath = OR.Searched_AccountStatus)
	public WebElement Searched_AccountStatus;

	@FindBy(xpath = OR.NoteModal_Title)
	public WebElement NoteModal_Title;

	@FindBy(xpath = OR.UnitOwner_Item_link)
	public WebElement UnitOwner_Item_link;

	@FindBy(id = OR.NewItem)
	public WebElement NewItem;

	@FindBy(id = OR.Itme_Name)
	public WebElement Itme_Name;

	@FindBy(id = OR.Item_DisplayName)
	public WebElement Item_DisplayName;

	@FindBy(id = OR.Item_Description)
	public WebElement Item_Description;

	@FindBy(id = OR.Item_Value)
	public WebElement Item_Value;

	@FindBy(xpath = OR.PercentCheckbox)
	public WebElement PercentCheckbox;

	@FindBy(id = OR.Item_Catagory)
	public WebElement SelectItem_Catagory;

	@FindBy(xpath = OR.Item_Associate_Button)
	public WebElement Item_Associate_Button;

	@FindBy(xpath = OR.LdgerAccount_Popup)
	public WebElement LdgerAccount_Popup;

	@FindBy(id = OR.SelectTax)
	public WebElement SelectTax;

	@FindBy(id = OR.AdTax_Button)
	public WebElement AddTax_Button;

	@FindBy(id = OR.Tax_Done_Button)
	public WebElement Tax_Done_Button;

	@FindBy(id = OR.SaveItem_Button)
	public WebElement SaveItem_Button;

	@FindBy(id = OR.DoneItem_Button)
	public WebElement DoneItem_Button;

	@FindBy(xpath = OR.TaxExemptCheckBox)
	public WebElement TaxExemptCheckBox;

	@FindBy(xpath = OR.TaxExemptID)
	public WebElement TaxExemptID;

	@FindBy(xpath = OR.Account_Statement_Tab)
	public WebElement Account_Statement_Tab;

	@FindBy(xpath = OR.Account_Statement_Page)
	public WebElement Account_Statement_Page;

	@FindBy(xpath = OR.AccountStatement)
	public WebElement AccountStatement;

	@FindBy(xpath = OR.AccountStatement_Popup)
	public WebElement AccountStatement_Popup;

	@FindBy(id = OR.AccountStatement_PopupClose)
	public WebElement AccountStatement_PopupClose;

	@FindBy(xpath = OR.AccountStatement_Cancel)
	public WebElement AccountStatement_Cancel;

	@FindBy(xpath = OR.AccountStatement_Confirm)
	public WebElement AccountStatement_Confirm;

	@FindBy(xpath = OR.AccountStatment_Cancel_1)
	public WebElement AccountStatment_Cancel_1;

	@FindBy(id = OR.Select_PeroidDate)
	public WebElement Select_PeroidDate;

	@FindBy(id = OR.AccountStatement_ErrorMessage)
	public WebElement AccountStatement_ErrorMessage;

	@FindBy(id = OR.RunPeriod_Button)
	public WebElement RunPeriod_Button;

	@FindBy(id = OR.ViewPeriod_Button)
	public WebElement ViewPeriod_Button;

	@FindBy(xpath = OR.Select_Date)
	public WebElement Select_Date;

	@FindBy(xpath = OR.Search_Account_Name)
	public WebElement Search_Account_Name;

	@FindBy(xpath = OR.AccountPage)
	public WebElement AccountPage;

	@FindBy(xpath = OR.TravelAgen_Item_link)
	public WebElement TravelAgen_Item_link;

	@FindBy(xpath = OR.TravelAgenItem)
	public WebElement TravelAgenItem;

	@FindBy(id = OR.TravelAgentItme_Name)
	public WebElement TravelAgentItme_Name;

	@FindBy(id = OR.TravelAgentItem_DisplayName)
	public WebElement TravelAgentItem_DisplayName;

	@FindBy(id = OR.TravelAgentItem_Description)
	public WebElement TravelAgentItem_Description;

	@FindBy(id = OR.TravelAgentItem_Value)
	public WebElement TravelAgentItem_Value;

	@FindBy(id = OR.TravelAgentItem_Percent)
	public WebElement TravelAgentItem_Percent;

	@FindBy(xpath = OR.DeleteItem)
	public WebElement DeleteItem;

	@FindBy(xpath = OR.Select_DepositPolicy)
	public WebElement Select_DepositPolicy;

	@FindBy(xpath = OR.Folio_AccountName_Input)
	public WebElement Folio_AccountName_Input;

	@FindBy(xpath = OR.Delete_TravelAgentItem)
	public WebElement Delete_TravelAgentItem;

	@FindBy(xpath = OR.PrintButton)
	public WebElement PrintButton;

	@FindBy(xpath = OR.PDF_File)
	public WebElement PDF_File;

	@FindBy(xpath = OR.ExportButton)
	public WebElement ExportButton;

	@FindBy(xpath = OR.SelectPDFCategory)
	public WebElement SelectPDFCategory;

	@FindBy(xpath = OR.Account_EndingBalance)
	public WebElement Account_EndingBalance;

	@FindBy(xpath = OR.Void)
	public WebElement Void;

	@FindBy(xpath = OR.NotesPopup)
	public WebElement NotesPopup;

	@FindBy(xpath = OR.NotesPopup_Note)
	public WebElement NotesPopup_Note;

	@FindBy(xpath = OR.NotesPopup_Void)
	public WebElement NotesPopup_Void;

	@FindBy(xpath = OR.Payment_Add)
	public WebElement Payment_Add;

	@FindBy(xpath = OR.New_Account_Number)
	public WebElement New_Account_Number;

	@FindBy(xpath = OR.New_Account_Number_1)
	public WebElement New_Account_Number_1;

	@FindBy(xpath = OR.Account_ModuleLoading)
	public WebElement Account_ModuleLoading;

	@FindBy(xpath = OR.Enter_Acc_ApptovalCode)
	public WebElement Enter_Acc_ApptovalCode;

	@FindBy(xpath = OR.AccountPaymentModule)
	public WebElement AccountPaymentModule;

	@FindBy(xpath = OR.AccountPyamentPopup)
	public WebElement AccountPyamentPopup;

	@FindBy(xpath = OR.FolioPayments)
	public WebElement FolioPayments;

	@FindBy(xpath = OR.AdvanceFolioBalance)
	public WebElement AdvanceFolioBalance;

	@FindBy(xpath = OR.Advance_Deposit_Balance)
	public WebElement Advance_Deposit_Balance;

	@FindBy(xpath = OR.Deposit_Auto_applyContinue)
	public List<WebElement> Deposit_Auto_applyContinue;

	@FindBy(xpath = OR.Deposit_Auto_apply)
	public List<WebElement> Deposit_Auto_apply;

	@FindBy(xpath = OR.Deposit_Auto_applyAdd)
	public List<WebElement> Deposit_Auto_applyAdd;

	@FindBy(xpath = OR.Advance_Deposit_Click)
	public WebElement Advance_Deposit_Click;

	@FindBy(xpath = OR.Deposit_paid)
	public WebElement Deposit_paid;

	@FindBy(xpath = OR.Verify_Notes_Popup)
	public WebElement Verify_Notes_Popup;

	@FindBy(xpath = OR.AccountPage_AccountName_ValidationMessage)
	public WebElement AccountPage_AccountName_ValidationMessage;

	@FindBy(xpath = OR.AccountPage_MailingInfo_FirstName_ValidationMessage)
	public WebElement AccountPage_MailingInfo_FirstName_ValidationMessage;

	@FindBy(xpath = OR.AccountPage_MailingInfo_PhoneNumber_ValidationMessage)
	public WebElement AccountPage_MailingInfo_PhoneNumber_ValidationMessage;

	@FindBy(xpath = OR.AccountPage_MailingInfo_AddressLine_ValidationMessage)
	public WebElement AccountPage_MailingInfo_AddressLine_ValidationMessage;

	@FindBy(xpath = OR.AccountPage_MailingInfo_City_ValidationMessage)
	public WebElement AccountPage_MailingInfo_City_ValidationMessage;

	@FindBy(xpath = OR.AccountPage_MailingInfo_State_ValidationMessage)
	public WebElement AccountPage_MailingInfo_State_ValidationMessage;

	@FindBy(xpath = OR.AccountPage_MailingInfo_PostalCode_ValidationMessage)
	public WebElement AccountPage_MailingInfo_PostalCode_ValidationMessage;

	@FindBy(xpath = OR.AccountPage_BillingInfo_FirstName_ValidationMessage)
	public WebElement AccountPage_BillingInfo_FirstName_ValidationMessage;

	@FindBy(xpath = OR.AccountPage_BillingInfo_PhoneNumber_ValidationMessage)
	public WebElement AccountPage_BillingInfo_PhoneNumber_ValidationMessage;

	@FindBy(xpath = OR.AccountPage_BillingInfo_AddressLine_ValidationMessage)
	public WebElement AccountPage_BillingInfo_AddressLine_ValidationMessage;

	@FindBy(xpath = OR.AccountPage_BillingInfo_City_ValidationMessage)
	public WebElement AccountPage_BillingInfo_City_ValidationMessage;

	@FindBy(xpath = OR.AccountPage_BillingInfo_State_ValidationMessage)
	public WebElement AccountPage_BillingInfo_State_ValidationMessage;

	@FindBy(xpath = OR.AccountPage_BillingInfo_PostalCode_ValidationMessage)
	public WebElement AccountPage_BillingInfo_PostalCode_ValidationMessage;

	@FindBy(xpath = OR.AccountPage_NotesDetails_Subject_ValidationMessage)
	public WebElement AccountPage_NotesDetails_Subject_ValidationMessage;

	@FindBy(xpath = OR.AccountPage_NotesPage_Cancelbutton)
	public WebElement AccountPage_NotesPage_Cancelbutton;

	@FindBy(xpath = OR.AccountPage_NotesButton)
	public WebElement AccountPage_NotesButton;

	@FindBy(xpath = OR.AccountPage_PhoneNumber_InvalidMessage)
	public WebElement AccountPage_PhoneNumber_InvalidMessage;

	@FindBy(xpath = OR.AddNotes_NotesType)
	public WebElement AddNotes_NotesType;

	@FindBy(xpath = OR.AddNotes_NotesSubject)
	public WebElement AddNotes_NotesSubject;

	@FindBy(xpath = NewAccount.Account_GetStatement_Icon)
	public WebElement Account_GetStatement_Icon;

	@FindBy(xpath = NewAccount.Account_NewPeriodPopup)
	public WebElement Account_NewPeriodPopup;

	@FindBy(xpath = NewAccount.AccountStatement_CalanderIcon)
	public WebElement AccountStatement_CalanderIcon;

	@FindBy(xpath = NewAccount.AccountStatement_SelectToday)
	public WebElement AccountStatement_SelectToday;

	@FindBy(xpath = NewAccount.AccountStatementSelect_SelectDate)
	public WebElement AccountStatementSelect_SelectDate;

	@FindBy(xpath = NewAccount.Accounts_AccountStatement)
	public WebElement Accounts_AccountStatement;

	@FindBy(xpath = NewAccount.Accounts_AccountStatement_Confirm)
	public WebElement Accounts_AccountStatement_Confirm;

	@FindBy(xpath = NewAccount.AccountStatement_ModuleLoading)
	public WebElement AccountStatement_ModuleLoading;

	@FindBy(xpath = NewAccount.Account_SelectPeriod)
	public WebElement Account_SelectPeriod;

	@FindBy(xpath = NewAccount.Account_SelectStatus)
	public WebElement Account_SelectStatus;

	@FindBy(xpath = OR.Account_Folio_FolioOptions)
	public WebElement Account_Folio_FolioOptions;

	@FindBy(xpath = OR.Account_Folio_FolioOptions1)
	public WebElement Account_Folio_FolioOptions1;

	@FindBy(xpath = OR.Account_Folio_FolioOptions_MoveToAccountFolio)
	public WebElement Account_Folio_FolioOptions_MoveToAccountFolio;

	@FindBy(xpath = NewAccount.AccountHeader_AccountName)
	public WebElement AccountHeader_AccountName;

	@FindBy(xpath = NewAccount.AccountHeader_AccountNumber)
	public WebElement AccountHeader_AccountNumber;

	@FindBy(xpath = NewAccount.AccountNotes_DueDate)
	public WebElement AccountNotes_DueDate;

	@FindBy(xpath = NewAccount.Account_DirtyTab)
	public WebElement Account_DirtyTab;

	@FindBy(xpath = NewAccount.AddedNote_DueDate)
	public WebElement AddedNote_DueDate;

	@FindBy(xpath = NewAccount.AddedNote_LoginId)
	public WebElement AddedNote_LoginId;

	@FindBy(xpath = NewAccount.AccountSave_ModuleLoading)
	public WebElement AccountSave_ModuleLoading;

	@FindBy(xpath = OR.Account_Folio_AccountStatus)
	public WebElement Account_Folio_AccountStatus;

	@FindBy(xpath = OR.voidButton)
	public WebElement voidButton;

	@FindBy(xpath = OR.accountFolioDetailOptions)
	public WebElement accountFolioDetailOptions;

	@FindBy(xpath = OR.EnterAccountNumber)
	public WebElement enterAccountNumber;

	@FindBy(xpath = NewAccount.ACCOUNT_SELECT_PROPERTY)
	public WebElement ACCOUNT_SELECT_PROPERTY;

	@FindBy(xpath = NewAccount.ACCOUNT_PAYMENT_METHOD_LIST)
	public WebElement ACCOUNT_PAYMENT_METHOD_LIST;

	@FindBy(xpath = NewAccount.ACCOUNT_BILLING_ACCOUNT_NUMBER)
	public WebElement ACCOUNT_BILLING_ACCOUNT_NUMBER;

	@FindBy(xpath = NewAccount.ACCOUNT_BILLING_ACCOUNT_EXPIRAY_DATE)
	public WebElement ACCOUNT_BILLING_ACCOUNT_EXPIRAY_DATE;

	@FindBy(xpath = OR.CLICK_PROCESS_BUTTONS)
	public List<WebElement> CLICK_PROCESS_BUTTONS;

	@FindBy(xpath = OR.SELECT_PROPERTY_FROM_ACCOUNT)
	public WebElement selectPropertyFromAccount;

	@FindBy(xpath = NewAccount.SEARCHEDACCOUNT_SINCEDATE)
	public WebElement SEARCHEDACCOUNT_SINCEDATE;

	@FindBy(xpath = NewAccount.SEARCHEDACCOUNT_ROOMS)
	public WebElement SEARCHEDACCOUNT_ROOMS;

	@FindBy(xpath = NewAccount.SEARCHEDACCOUNT_RESERVATIONS)
	public WebElement SEARCHEDACCOUNT_RESERVATIONS;

	@FindBy(xpath = NewAccount.folioOptionsLabel)
	public WebElement folioOptionsLabel;

	@FindBy(xpath = NewAccount.folioOptionDepositPolicy)
	public WebElement folioOptionDepositPolicy;

	@FindBy(xpath = NewAccount.folioOptionNoShowPolicy)
	public WebElement folioOptionNoShowPolicy;

	@FindBy(xpath = NewAccount.folioOptionCheckInPolicy)
	public WebElement folioOptionCheckInPolicy;

	@FindBy(xpath = NewAccount.folioOptionCancellationPolicy)
	public WebElement folioOptionCancellationPolicy;

	@FindBy(xpath = NewAccount.folioOptionCancellationPolicyListSource)
	public WebElement folioOptionCancellationPolicyListSource;

	@FindBy(xpath = NewAccount.folioOptionCancellationPolicyPickBtn)
	public WebElement folioOptionCancellationPolicyPickBtn;

	@FindBy(xpath = NewAccount.folioOptionCancelationPolicyDeleteAllBtn)
	public WebElement folioOptionCancelationPolicyDeleteAllBtn;

	@FindBy(xpath = NewAccount.folioOptionCancellationPolicyDoneBtn)
	public WebElement folioOptionCancellationPolicyDoneBtn;

}
