package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.pages.NewFolio;
import com.innroad.inncenter.pages.NewReservation;
import com.innroad.inncenter.properties.OR;

public class Elements_FolioLineItemsVoid {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_FolioLineItemsVoid(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = OR.ReservationIcon)
	public WebElement ReservationIcon;

	@FindBy(xpath = OR.Reservationlink)
	public WebElement Reservationlink;

	@FindBy(xpath = OR.NewReservationButton)
	public WebElement NewReservationButton;

	@FindBy(xpath = OR.New_Reservation_Button)
	public WebElement New_Reservation_Button;

	@FindBy(xpath = OR.Reservation_market_Segment)
	public WebElement Reservation_market_Segment;

	@FindBy(xpath = OR.Enter_Travel_Agent)
	public WebElement Enter_Travel_Agent;

	@FindBy(xpath = OR.Enter_Ext_Reservation)
	public WebElement Enter_Ext_Reservation;

	@FindBy(xpath = OR.Reservation_Referral)
	public WebElement Reservation_Referral;

	@FindBy(xpath = OR.Select_Saluation)
	public WebElement Select_Saluation;

	@FindBy(id = OR.UnCheckGuestProfile)
	public WebElement UnCheckGuestProfile;

	@FindBy(id = OR.GuestFirstName)
	public WebElement GuestFirstName;

	@FindBy(id = OR.GuestLastName)
	public WebElement GuestLastName;

	@FindBy(xpath = OR.Enter_City)
	public WebElement Enter_City;

	@FindBy(xpath = OR.Select_Payment_Method)
	public WebElement Select_Payment_Method;

	@FindBy(xpath = OR.Select_Country)
	public WebElement Select_Country;

	@FindBy(xpath = OR.Select_State)
	public WebElement Select_State;

	@FindBy(xpath = OR.Enter_Postal_Code)
	public WebElement Enter_Postal_Code;

	@FindBy(xpath = OR.Enter_Phone_Number)
	public WebElement Enter_Phone_Number;

	@FindBy(xpath = OR.Enter_First_Name)
	public WebElement Enter_First_Name;

	@FindBy(xpath = OR.Enter_Last_Name)
	public WebElement Enter_Last_Name;

	@FindBy(xpath = OR.Enter_Address_Search)
	public WebElement Enter_Address_Search;

	@FindBy(xpath = OR.Enter_Line1)
	public WebElement Enter_Line1;

	@FindBy(xpath = OR.Enter_Line2)
	public WebElement Enter_Line2;

	@FindBy(xpath = OR.Enter_Line3)
	public WebElement Enter_Line3;

	@FindBy(xpath = OR.Enter_Alt_Phn_number)
	public WebElement Enter_Alt_Phn_number;

	@FindBy(xpath = OR.Enter_email)
	public WebElement Enter_email;

	@FindBy(xpath = OR.Enter_Account)
	public WebElement Enter_Account;

	@FindBy(xpath = OR.Check_IsTaxExempt)
	public WebElement Check_IsTaxExempt;

	@FindBy(xpath = OR.Enter_TaxExemptId)
	public WebElement Enter_TaxExemptId;

	@FindBy(xpath = OR.Enter_Account_Number)
	public WebElement Enter_Account_Number;

	@FindBy(xpath = OR.Enter_Exp_Card)
	public WebElement Enter_Exp_Card;

	@FindBy(xpath = OR.Enter_Billing_Note)
	public WebElement Enter_Billing_Note;

	@FindBy(xpath = OR.Click_RoomPicker)
	public WebElement Click_RoomPicker;

	@FindBy(xpath = OR.Click_Arrive_Datepicker)
	public WebElement Click_Arrive_Datepicker;

	@FindBy(xpath = OR.Click_Today)
	public WebElement Click_Today;

	@FindBy(xpath = OR.Enter_Nigts)
	public WebElement Enter_Nigts;

	@FindBy(xpath = OR.Enter_Adults)
	public WebElement Enter_Adults;

	@FindBy(xpath = OR.Enter_Children)
	public WebElement Enter_Children;

	@FindBy(xpath = OR.Enter_Rate_Promocode)
	public WebElement Enter_Rate_Promocode;

	@FindBy(xpath = OR.Check_Assign_Room)
	public WebElement Check_Assign_Room;

	@FindBy(xpath = OR.Click_Search)
	public WebElement Click_Search;

	@FindBy(xpath = OR.Select_Room_Class)
	public WebElement Select_Room_Class;

	@FindBy(xpath = OR.Validating_UnAssgined_DDL)
	public WebElement Validating_UnAssgined_DDL;

	@FindBy(xpath = OR.Select_Room_Number)
	public WebElement Select_Room_Number;

	@FindBy(xpath = OR.Click_Select)
	public WebElement Click_Select;

	@FindBy(xpath = OR.Verify_RulesBroken_Popup)
	public WebElement Verify_RulesBroken_Popup;

	@FindBy(xpath = OR.Click_Continue_RuleBroken_Popup)
	public WebElement Click_Continue_RuleBroken_Popup;

	@FindBy(xpath = OR.Verify_Toaster_Container)
	public WebElement Verify_Toaster_Container;

	@FindBy(className = OR.Toaster_Title)
	public WebElement Toaster_Title;

	@FindBy(className = OR.Toaster_Message)
	public WebElement Toaster_Message;

	@FindBy(xpath = OR.Get_Property_Name)
	public WebElement Get_Property_Name;

	@FindBy(xpath = OR.Get_RoomClass_Status)
	public WebElement Get_RoomClass_Status;

	@FindBy(xpath = OR.Click_Save_ReservationDetails)
	public WebElement Click_Save_ReservationDetails;

	@FindBy(xpath = OR.Verify_Depos_policy)
	public WebElement Verify_Depos_policy;

	@FindBy(xpath = OR.Click_Without_Deposit)
	public WebElement Click_Without_Deposit;

	@FindBy(xpath = OR.click_Folio_tab)
	public WebElement click_Folio_tab;

	@FindBy(xpath = OR.click_Add_Button)
	public WebElement click_Add_Button;
	
	@FindBy(xpath = OR.click_Add_Folio_Button)
	public WebElement click_Add_Folio_Button;
	

	@FindBy(xpath = OR.selectCategory)
	public WebElement selectCategory;

	@FindBy(xpath = OR.enterAmount)
	public WebElement enterAmount;

	@FindBy(xpath = OR.clickSaveButton)
	public WebElement clickSaveButton;

	@FindBy(xpath = OR.clickOkForPopup)
	public WebElement clickOkForPopup;

	@FindBy(xpath = OR.closeReservation)
	public WebElement closeReservation;

	@FindBy(xpath = OR.clickCommitButton)
	public WebElement clickCommitButton;
	
	@FindBy(xpath = OR.getFolioLineItem)
	public WebElement getFolioLineItem;
	
	@FindBy(xpath = OR.copiedFolioLineItem)
	public WebElement copiedFolioLineItem;	

	@FindBy(xpath = OR.clickFolioCommitButton)
	public WebElement clickFolioCommitButton;
	
	@FindBy(xpath = OR.selectAllLineItems)
	public WebElement selectAllLineItems;

	@FindBy(xpath = OR.clickVoidButton)
	public WebElement clickVoidButton;

	@FindBy(xpath = OR.enterNotes)
	public WebElement enterNotes;

	@FindBy(xpath = OR.clickVoidButtonInNotes)
	public WebElement clickVoidButtonInNotes;

	@FindBy(xpath = OR.DisplayVoidItemsCheckBox)
	public WebElement DisplayVoidItemsCheckBox;
	
	@FindBy(xpath = OR.VoidItemDisplayImage)
	public WebElement VoidItemDisplayImage;

	@FindBy(xpath = OR.getBalanceFolioLineItems)
	public WebElement getBalanceFolioLineItems;
	
	@FindBy(xpath = OR.ItemDetailCancelButton)
	public WebElement ItemDetailCancelButton;
	
	

	// ************************Add/Post Folio Line
	// Items*************************//

	@FindBy(xpath = OR.reservationGuestName)
	public WebElement reservationGuestName;

	@FindBy(xpath = OR.reservationGuestName2)
	public WebElement reservationGuestName2;

	@FindBy(xpath = OR.verifyCheckboxPendingStatus)
	public WebElement verifyCheckboxPendingStatus;

	@FindBy(xpath = OR.lineItemPendingStatus)
	public WebElement lineItemPendingStatus;

	@FindBy(xpath = OR.verifyCheckboxPostStatus)
	public WebElement verifyCheckboxPostStatus;

	@FindBy(xpath = OR.lineItemPostStatus)
	public WebElement lineItemPostStatus;

	@FindBy(xpath = OR.pendingItemDescription)
	public WebElement pendingItemDescription;

	@FindBy(xpath = OR.selectCategoryItem)
	public WebElement selectCategoryItem;

	@FindBy(xpath = OR.itemDescription)
	public WebElement itemDescription;

	@FindBy(xpath = OR.foliolineItemAmount)
	public WebElement foliolineItemAmount;

	@FindBy(xpath = OR.foliolineItemNotes)
	public WebElement foliolineItemNotes;

	@FindBy(xpath = OR.foliolineItemAddButton)
	public WebElement foliolineItemAddButton;

	@FindBy(xpath = OR.foliolineItemContinueButton)
	public WebElement foliolineItemContinueButton;

	@FindBy(xpath = OR.clickOnDescription)
	public WebElement clickOnDescription;

	@FindBy(xpath = OR.clickRollBackButtonInPopUp)
	public WebElement clickRollBackButtonInPopUp;

	@FindBy(xpath = OR.clickContinueButton)
	public WebElement clickContinueButton;

	@FindBy(xpath = OR.folioSaveButton)
	public WebElement folioSaveButton;

	@FindBy(css = OR.SelectCategory)
	public WebElement SelectCategory;
	
	@FindBy(xpath = OR.SelectCategory1)
	public WebElement SelectCategory1;

//	@FindBy(css = OR.LineItemsFields)
	@FindBy(xpath = OR.LineItemsFields)
	public List<WebElement> LineItemsAmountFields;

	@FindBy(xpath = OR.CommitButton)
	public WebElement CommitButton;

	@FindBy(css = OR.LineItems)
	public List<WebElement> LineItems;
	
	@FindBy(xpath = OR.selectPaymentFolio)
	public WebElement selectPaymentFolio;

	@FindBy(xpath = OR.RoomCharger_Tax)
	public List<WebElement> RoomCharger_Tax;

	@FindBy(css = OR.LineItems_Description)
	public List<WebElement> LineItems_Description;

	@FindBy(xpath = NewFolio.AddedLineItems_Category)
	public List<WebElement> AddedLineItems_Category;
	
	@FindBy(xpath = NewFolio.AddedLineItems_Room)
	public List<WebElement> AddedLineItems_Room;
	
	@FindBy(xpath = NewFolio.AddedLineItems_Date)
	public List<WebElement> AddedLineItems_Date;
	
	@FindBy(xpath = NewFolio.AddedLineItems_Description)
	public List<WebElement> AddedLineItems_Description;
	
	@FindBy(xpath = NewFolio.AddedLineItems_Icon)
	public List<WebElement> AddedLineItems_Icon;
	
	@FindBy(xpath = NewFolio.AddedLineItems_Amount)
	public List<WebElement> AddedLineItems_Amount;
	// Folio Payments Bulk Cancellation

	@FindBy(xpath = OR.resNumber)
	public WebElement resNumber;

	@FindBy(xpath = OR.Click_Pay_Button)
	public WebElement Click_Pay_Button;

	@FindBy(xpath = OR.Verify_Payment_Details_popup)
	public WebElement Verify_Payment_Details_popup;

	@FindBy(xpath = OR.Verify_Payment_Details_poup)
	public WebElement Verify_Payment_Details_poup;

	@FindBy(xpath = OR.Select_Paymnet_Method)
	public WebElement Select_Paymnet_Method;

	@FindBy(xpath = OR.payment_AddButton)
	public WebElement payment_AddButton;
	
	@FindBy(xpath = OR.AddButton)
	public WebElement AddButton;

	@FindBy(xpath = OR.payment_ContinueButton)
	public WebElement payment_ContinueButton;

	@FindBy(xpath = OR.Payment_Details_Folio_Balance)
	public WebElement Payment_Details_Folio_Balance;

	@FindBy(xpath = OR.Payment_Details_Total_Balance)
	public WebElement Payment_Details_Total_Balance;
	
	@FindBy(xpath = OR.PaymentInfo_Payments)
	public WebElement PaymentInfo_Payments;
	
	@FindBy(xpath = OR.PaymentInfo_Balance)
	public WebElement PaymentInfo_Balance;
	
	@FindBy(xpath = OR.FolioOption)
	public WebElement FolioOption;

	@FindBy(xpath = OR.Select_PhoneAccess)
	public WebElement Select_PhoneAccess;

	@FindBy(xpath = OR.SaveOptions_Button)
	public WebElement SaveOptions_Button;

	@FindBy(xpath = OR.Folio)
	public WebElement Folio;
	
	@FindBy(xpath = OR.FolioTab)
	public WebElement FolioTab;

	@FindBy(xpath = OR.ItemDatails_CancelButton)
	public WebElement ItemDatails_CancelButton;

	@FindBy(xpath = OR.TaxItem_Cancel_Account)
	public WebElement TaxItem_Cancel_Account;

	@FindBy(xpath = OR.ChangeDueDate_NoButton)
	public WebElement ChangeDueDate_NoButton;
	
	@FindBy(xpath = OR.ChangeDueDateNoButton)
	public WebElement ChangeDueDateNoButton;

	@FindBy(xpath = OR.FolioBillingInformationPopUp)
	public WebElement FolioBillingInformationPopUp;

	@FindBy(xpath = OR.FolioPopUp_BillingName)
	public WebElement FolioPopUp_BillingName;

	@FindBy(xpath = OR.FolioPopUp_PaymentMethod)
	public WebElement FolioPopUp_PaymentMethod;

	@FindBy(xpath = OR.FolioPopUp_SaveButton)
	public WebElement FolioPopUp_SaveButton;

	@FindBy(xpath = OR.FolioPopUp_AccountNumber)
	public WebElement FolioPopUp_AccountNumber;

	@FindBy(xpath = OR.FolioPopUp_ExpiryDate)
	public WebElement FolioPopUp_ExpiryDate;

	@FindBy(xpath = OR.FolioPopUp_ReservationNumber)
	public WebElement FolioPopUp_ReservationNumber;
	
	@FindBy(xpath = OR.PaymentInfoPicker)
	public WebElement PaymentInfoPicker;
	
	@FindBy(xpath = OR.Continue_Pay_Button)
	public WebElement Continue_Pay_Button;
	
	@FindBy(xpath = OR.LineItems_Amount)
	public WebElement LineItems_Amount;
	
	@FindBy(xpath = NewReservation.PaymentDetail)
	public WebElement PaymentDetail;
	
	@FindBy(xpath = OR.LineItems_Checkbox)
	public List<WebElement> LineItems_Checkbox;
	
	@FindBy(xpath = OR.VoidNotesPopup)
	public WebElement VoidNotesPopup;
	
	@FindBy(xpath = OR.LineItemStatus)
	public WebElement LineItemStatus;
	
	@FindBy(xpath = OR.BeforePostLineItem)
	public WebElement BeforePostLineItem;
	
	@FindBy(xpath = OR.LineItemDescription)
	public WebElement LineItemDescription;
	
	@FindBy(xpath = OR.LineItem_RollBackButton)
	public WebElement LineItem_RollBackButton;
	
	@FindBy(xpath = OR.enterRollBackNotes)
	public WebElement enterRollBackNotes;
	
	@FindBy(xpath = OR.RollbackContinueBtn)
	public WebElement RollbackContinueBtn;
	
	
	@FindBy(xpath = NewFolio.Reservation_Payments)
	public WebElement Reservation_Payments;
	
	@FindBy(xpath = NewFolio.Reservation_TotalCharges)
	public WebElement Reservation_TotalCharges;
	
	@FindBy(xpath = NewFolio.Reservation_Balance)
	public WebElement Reservation_Balance;
	
	@FindBy(xpath = NewFolio.Enter_LineItemAmount)
	public WebElement Enter_LineItemAmount;
	
	@FindBy(xpath = OR.LineItemPostedStatus)
	public WebElement LineItemPostedStatus;
	
	@FindBy(xpath = OR.Toaster_ValidationMessage)
	public WebElement Toaster_ValidationMessage;
	
	@FindBy(xpath = OR.AbortBtn)
	public WebElement AbortBtn;
	
	@FindBy(xpath = OR.RollBackCancelBtn)
	public WebElement RollBackCancelBtn;
	
	@FindBy(xpath = OR.LineItemDate)
	public WebElement LineItemDate;
	
	@FindBy(xpath = OR.ItemPostingFailure_Popup)
	public WebElement ItemPostingFailure_Popup;
	
	@FindBy(xpath = OR.LineItemPostingFailure_OkBtn)
	public WebElement LineItemPostingFailure_OkBtn;
	
	@FindBy(xpath = OR.TotalCharger)
	public WebElement TotalCharger;
	
	@FindBy(xpath = OR.Balance)
	public WebElement Balance;
	
	@FindBy(xpath = OR.RollBackCashItem)
	public WebElement RollBackCashItem;

	@FindBy(xpath = OR.RollBackCashItemNotes)
	public WebElement RollBackCashItemNotes;
	
	@FindBy(xpath = OR.RollBackCashItemContinue)
	public WebElement RollBackCashItemContinue;
	
	
	@FindBy(xpath = OR.PaymentRollBack_Button)
	public WebElement PaymentRollBack_Button;
	
	@FindBy(xpath = OR.EnterpaymentNotes)
	public WebElement EnterpaymentNotes;
	
	@FindBy(xpath = OR.IncludeTaxLineItemCheckbox)
	public WebElement IncludeTaxLineItemCheckbox;
	
	@FindBy(xpath = OR.Foliolineitems_QTY)
	public WebElement Foliolineitems_QTY;
	
	@FindBy(xpath = OR.foliolineItemDescField)
	public WebElement foliolineItemDescField;
	

	@FindBy(xpath = OR.LineItemsList)
	public List<WebElement> LineItemsList;
	
	@FindBy(xpath = OR.List_LineItemTax)
	public List<WebElement> List_LineItemTax;
	
	@FindBy(xpath = OR.List_LineItemDates)
	public List<WebElement> List_LineItemDates;
	
	@FindBy(xpath = OR.List_LineItemAmount)
	public List<WebElement> List_LineItemAmount;
	
	@FindBy(xpath=OR.AuthorizationImg)
	public WebElement AuthorizationImg; 
	
	@FindBy(xpath = NewFolio.FolioRows)
	public List<WebElement> FolioRows;

	@FindBy(xpath = NewFolio.Enter_LineItemQuentity)
	public WebElement Enter_LineItemQuentity;

	@FindBy(xpath = NewFolio.roomCharges)
	public WebElement roomCharges;

	@FindBy(xpath = NewFolio.incidental)
	public WebElement incidental;

	@FindBy(xpath = NewFolio.taxServices)
	public WebElement taxServices;

	@FindBy(xpath = NewFolio.totalCharges)
	public WebElement totalCharges;

	@FindBy(xpath = NewFolio.payment)
	public WebElement payment;

	@FindBy(xpath = NewFolio.totalBalance)
	public WebElement totalBalance;

	@FindBy(xpath = NewFolio.itemDetailsPopup)
	public WebElement itemDetailsPopup;

	@FindBy(xpath = NewFolio.ItemDetails_CancelButton)
	public WebElement ItemDetails_CancelButton;

	@FindBy(xpath = NewFolio.ItemDetails_AddButton)
	public WebElement ItemDetails_AddButton;

	@FindBy(xpath = NewFolio.ItemDetails_VoidButton)
	public WebElement ItemDetails_VoidButton;

	@FindBy(xpath = NewFolio.ItemDetails_CancelPopup)
	public WebElement ItemDetails_CancelPopup;

	@FindBy(xpath = NewFolio.ItemDetails_ContinuePopup)
	public WebElement ItemDetails_ContinuePopup;

	@FindBy(xpath = NewFolio.ItemDetails_Transcation)
	public WebElement ItemDetails_Transcation;

	@FindBy(xpath = NewFolio.ItemDetailsRows)
	public List<WebElement> ItemDetailsRows;

	@FindBy(xpath = NewFolio.ItemDetails_RoomCharges)
	public WebElement ItemDetails_RoomCharges;

	@FindBy(xpath = NewFolio.ItemDetails_Incidental)
	public WebElement ItemDetails_Incidental;

	@FindBy(xpath = NewFolio.ItemDetails_TaxServices)
	public WebElement ItemDetails_TaxServices;

	@FindBy(xpath = NewFolio.ItemDetails_TotalCharges)
	public WebElement ItemDetails_TotalCharges;

	@FindBy(xpath = NewFolio.RoomNumber)
	public WebElement RoomNumber;

	@FindBy(xpath = NewFolio.checkbox_IncludePendingItemsinTotal)
	public WebElement checkbox_IncludePendingItemsinTotal;

	@FindBy(xpath = NewFolio.Notes)
	public WebElement Notes;

	@FindBy(xpath = NewFolio.checkboxDisplayVoidItem)
	public WebElement checkboxDisplayVoidItem;

	@FindBy(xpath = NewFolio.RollBackButton)
	public WebElement RollBackButton;

	@FindBy(xpath = NewFolio.ItemDetailsSelectCategory)
	public WebElement ItemDetailsSelectCategory;

	@FindBy(xpath = NewFolio.EnterItemDetailsDescription)
	public WebElement EnterItemDetailsDescription;

	@FindBy(xpath = NewFolio.EntertemDetailsAmount)
	public WebElement EntertemDetailsAmount;

	@FindBy(xpath = NewFolio.FolioBalance)
	public WebElement FolioBalance;

	@FindBy(xpath = NewFolio.TotalBalance)
	public WebElement TotalBalance;
	
	@FindBy(xpath = NewFolio.Foliotab)
	public WebElement Foliotab;
	
	@FindBy(xpath = NewFolio.FolioDetails)
	public WebElement FolioDetails;
	
	@FindBy(xpath = NewFolio.FolioOptions)
	public WebElement FolioOptions;
	
	@FindBy(xpath = NewFolio.CheckboxIncludeTaxesinLineItems)
	public WebElement checkboxIncludeTaxesinLineItems;
	
	@FindBy(xpath = NewFolio.IncludeTaxesinLineItemsCheckbox)
	public WebElement includeTaxesinLineItemsCheckbox;
	
	@FindBy(xpath = NewFolio.ItemDetails_Incidental)
	public WebElement itemDetailsIncidental;
	
	@FindBy(xpath = NewFolio.ItemDetails_TaxServices)
	public WebElement itemDetailsTaxServices;

	@FindBy(xpath = NewFolio.ItemDetails_TotalCharges)
	public WebElement itemDetailsTotalCharges;
	
	@FindBy(xpath = NewFolio.ItemDetails_CancelPopup)
	public WebElement itemDetailsCancelPopup;
	
	@FindBy(xpath = NewFolio.ChangeFolioButton)
	public WebElement changeFolioButton;
	

	@FindBy(xpath = OR.AddLineItemButton)
	public WebElement AddLineItemButton;
	
	@FindBy(xpath = OR.SelectGuestFolioRoom)
	public WebElement SelectGuestFolioRoom;
	
	@FindBy(xpath = OR.FolioOptionSave)
	public WebElement FolioOptionSave;
	
	@FindBy(xpath = OR.GuestFolioFristRoom)
	public WebElement GuestFolioFristRoom;
	
	@FindBy(xpath = OR.GuestFolioSecondRoom)
	public WebElement GuestFolioSecondRoom;
	

	@FindBy(xpath = OR.roomNumberInItemDetails)
	public WebElement roomNumberInItemDetails;
	
	@FindBy(xpath = OR.roomChargeInItemDetails)
	public WebElement roomChargeInItemDetails;
	
	@FindBy(xpath = OR.totalSummaryInItemDetails)
	public WebElement totalSummaryInItemDetails;
	
	@FindBy(xpath = OR.cancelButton)
	public WebElement cancelButton;

	@FindBy(xpath = OR.taxesAndServiceCharge)
	public WebElement taxesAndServiceCharge;
	
	@FindBy(xpath = NewFolio.SelectFolioProprtyInAccount)
	public WebElement SelectFolioProprtyInAccount;

	@FindBy(xpath = OR.folioPayments)
	public WebElement folioPayments;	
	
	@FindBy(xpath = NewFolio.Enter_LineItemQuantity)
	public WebElement Enter_LineItemQuantity;
		
}
