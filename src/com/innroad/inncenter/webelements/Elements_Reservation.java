package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.innroad.inncenter.pages.NewReservation;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Setup;

public class Elements_Reservation {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_Reservation(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);

	}

	@FindBy(xpath = OR.reservation_mainMenu)
	public WebElement reservation_mainMenu;

	@FindBy(xpath = OR.reservation_Menu)
	public WebElement reservation_Menu;

	@FindBy(xpath = OR.clickNewReservationButton)
	public WebElement clickNewReservationButton;

	@FindBy(xpath = OR.New_Reservation_Button)
	public WebElement New_Reservation_Button;

	@FindBy(xpath = OR.New_Reservation_Button1)
	public WebElement New_Reservation_Button1;

	@FindBy(xpath = OR.Reservation_market_Segment)
	public WebElement Reservation_market_Segment;

	@FindBy(xpath = OR.Reservation_Referral)
	public WebElement Reservation_Referral;

	@FindBy(xpath = OR.Enter_Travel_Agent)
	public WebElement Enter_Travel_Agent;

	@FindBy(xpath = OR.Enter_Ext_Reservation)
	public WebElement Enter_Ext_Reservation;

	@FindBy(xpath = OR.Enter_Guest_Profile)
	public WebElement Enter_Guest_Profile;

	@FindBy(xpath = OR.Select_Saluation)
	public WebElement Select_Saluation;

	@FindBy(xpath = OR.Enter_First_Name)
	public WebElement Enter_First_Name;

	@FindBy(xpath = OR.Enter_Contact_First_Name)
	public WebElement Enter_Contact_First_Name;

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

	@FindBy(xpath = OR.Enter_City)
	public WebElement Enter_City;

	@FindBy(xpath = OR.Select_Country)
	public WebElement Select_Country;

	@FindBy(xpath = OR.Select_State)
	public WebElement Select_State;

	@FindBy(xpath = OR.Enter_Postal_Code)
	public WebElement Enter_Postal_Code;

	@FindBy(xpath = OR.Enter_Phone_Number)
	public WebElement Enter_Phone_Number;

	@FindBy(xpath = OR.Enter_Alt_Phn_number)
	public WebElement Enter_Alt_Phn_number;

	@FindBy(xpath = OR.Enter_email)
	public WebElement Enter_email;

	@FindBy(xpath = OR.Enter_Account)
	public WebElement Enter_Account;

	@FindBy(xpath = OR.Check_IsTaxExempt)
	public WebElement Check_IsTaxExempt;
	
	@FindBy(xpath = OR.AccountTaxExempt)
	public WebElement AccountTaxExempt;
	
	
	@FindBy(xpath = OR.Enter_TaxExemptId)
	public WebElement Enter_TaxExemptId;
	
	@FindBy(xpath = OR.taxExemptMessage)
	public WebElement taxExemptMessage;

	@FindBy(xpath = OR.Check_isLongstay)
	public WebElement Check_isLongstay;

	@FindBy(xpath = OR.Select_Payment_Method)
	public WebElement Select_Payment_Method;
	
	@FindBy(xpath = OR.Select_Payment_Method_CopyRes)
	public WebElement Select_Payment_Method_CopyRes;
	

	@FindBy(xpath = OR.Enter_Account_Number)
	public WebElement Enter_Account_Number;

	@FindBy(xpath = OR.Enter_Exp_Card)
	public WebElement Enter_Exp_Card;

	@FindBy(xpath = OR.Enter_Billing_Note)
	public WebElement Enter_Billing_Note;

	@FindBy(xpath = OR.Add_Note_Reservation)
	public WebElement Add_Note_Reservation;

	@FindBy(xpath = OR.Click_RoomPicker)
	public WebElement Click_RoomPicker;
	
	@FindBy(xpath = OR.Get_Account_Name)
	public WebElement Get_Account_Name;

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

	@FindBy(xpath = OR.selectRackRate)
	public WebElement selectRackRate;

	@FindBy(xpath = OR.AdhocRate)
	public WebElement AdhocRate;

	@FindBy(xpath = OR.Enter_Rate_Promocode)
	public WebElement Enter_Rate_Promocode;

	@FindBy(xpath = OR.Check_Assign_Room)
	public WebElement Check_Assign_Room;

	@FindBy(xpath = OR.RoomPicker_ModuleLoading)
	public WebElement RoomPicker_ModuleLoading;

	@FindBy(xpath = OR.Click_Search)
	public WebElement Click_Search;

	@FindBy(xpath = OR.Select_property_RoomAssign)
	public WebElement Select_property_RoomAssign;

	@FindBy(xpath = OR.Select_Room_Class)
	public WebElement Select_Room_Class;

	@FindBy(xpath = OR.Select_Room_Number)
	public WebElement Select_Room_Number;

	@FindBy(xpath = OR.Validating_UnAssgined_DDL)
	public WebElement Validating_UnAssgined_DDL;

	@FindBy(xpath = OR.Click_Select)
	public WebElement Click_Select;

	@FindBy(xpath = OR.RoomCharges_Select)
	public WebElement RoomCharges_Select;

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
	
	@FindBy(xpath = OR.ToasterMessageGuaranteedRes)
	public WebElement ToasterMessageGuaranteedRes;
	
	@FindBy(css = OR.CancelDepositePolicy_Button)
	public List<WebElement> CancelDepositePolicy_Button;

	@FindBy(xpath = OR.Get_Property_Name)
	public WebElement Get_Property_Name;

	@FindBy(xpath = OR.Get_RoomClass_Status)
	public WebElement Get_RoomClass_Status;

	@FindBy(xpath = OR.Click_Save_ReservationDetails)
	public WebElement Click_Save_ReservationDetails;
	

	@FindBy(xpath = OR.Save_SourceReservationDetails)
	public WebElement Save_SourceReservationDetails;

	@FindBy(xpath = OR.Room_Assignment_PopUp)
	public WebElement Room_Assignment_PopUp;

	@FindBy(xpath = OR.Click_Save_Close)
	public WebElement Click_Save_Close;

	@FindBy(xpath = OR.Verify_Depos_policy)
	public WebElement Verify_Depos_policy;

	@FindBy(xpath = OR.Click_Without_Deposit)
	public WebElement Click_Without_Deposit;

	@FindBy(xpath = OR.Click_Email_icon)
	public WebElement Click_Email_icon;

	@FindBy(xpath = OR.Select_Attachment)
	public WebElement Select_Attachment;

	@FindBy(xpath = OR.Get_email_Id)
	public WebElement Get_email_Id;

	@FindBy(xpath = OR.Click_Send_Email)
	public WebElement Click_Send_Email;

	@FindBy(xpath = OR.getReservationStatus)
	public WebElement getReservationStatus;
	
	@FindBy(xpath = OR.GuestProfileCheckbox)
	public WebElement GuestProfileCheckbox;
	// Folio

	@FindBy(xpath = OR.ReservationStatus)
	public WebElement ReservationStatus;

	@FindBy(xpath = OR.Click_Pay_Button)
	public WebElement Click_Pay_Button;

	@FindBy(xpath = OR.Verify_Payment_Details_poup)
	public WebElement Verify_Payment_Details_poup;

	@FindBy(xpath = OR.Select_Paymnet_Method)
	public WebElement Select_Paymnet_Method;

	@FindBy(xpath = OR.Click_ADD_Button)
	public WebElement Click_ADD_Button;

	@FindBy(xpath = OR.Click_ADD)
	public WebElement Click_ADD;

	@FindBy(xpath = OR.Click_ADD_Btn)
	public WebElement Click_ADD_Btn;

	@FindBy(xpath = OR.Verify_Guest_Ledger)
	public WebElement Verify_Guest_Ledger;

	@FindBy(xpath = OR.Get_Payment_Method)
	public WebElement Get_Payment_Method;

	@FindBy(xpath = OR.Click_Continue)
	public WebElement Click_Continue;

	@FindBy(xpath = OR.Verify_Line_item)
	public WebElement Verify_Line_item;

	@FindBy(xpath = OR.Click_Card_info)
	public WebElement Click_Card_info;

	@FindBy(xpath = OR.Verify_payment_info_popup)
	public WebElement Verify_payment_info_popup;

	@FindBy(xpath = OR.Enter_Card_Name)
	public WebElement Enter_Card_Name;

	@FindBy(xpath = OR.Enter_Account_Number_Folio)
	public WebElement Enter_Account_Number_Folio;

	@FindBy(xpath = OR.Enter_CC_Expiry)
	public WebElement Enter_CC_Expiry;

	@FindBy(xpath = OR.Enter_CVVCode)
	public WebElement Enter_CVVCode;

	@FindBy(xpath = OR.Click_OK)
	public WebElement Click_OK;

	@FindBy(xpath = OR.Click_Process)
	public WebElement Click_Process;

	@FindBy(xpath = OR.GetMC_Payment_Method)
	public WebElement GetMC_Payment_Method;

	@FindBy(xpath = OR.GetAddedLine_MC)
	public WebElement GetAddedLine_MC;

	@FindBy(xpath = OR.GetAddedLineMC)
	public WebElement GetAddedLineMC;

	@FindBy(xpath = OR.Verify_MC_Grid)
	public WebElement Verify_MC_Grid;

	@FindBy(xpath = OR.Verify_Balance_Zero)
	public WebElement Verify_Balance_Zero;

	@FindBy(xpath = OR.click_Folio_tab)
	public WebElement click_Folio_tab;

	@FindBy(xpath = OR.Select_Authorization_type)
	public WebElement Select_Authorization_type;

	@FindBy(xpath = OR.Change_Amount)
	public WebElement Change_Amount;

	@FindBy(xpath = OR.Payment_Details_Folio_Balance)
	public WebElement Payment_Details_Folio_Balance;

	// checkin

	@FindBy(xpath = OR.Click_Checkin)
	public WebElement Click_Checkin;

	@FindBy(xpath = OR.roomAssignmentpopUpSelectButton)
	public WebElement roomAssignmentpopUpSelectButton;

	@FindBy(xpath = OR.Verify_Dirty_Room_popup)
	public WebElement Verify_Dirty_Room_popup;

	@FindBy(xpath = OR.Confirm_button)
	public WebElement Confirm_button;

	@FindBy(xpath = OR.Click_on_confirm)
	public WebElement Click_on_confirm;

	@FindBy(xpath = OR.Payment_Popup)
	public WebElement Payment_Popup;

	@FindBy(xpath = OR.Key_Generation_Popup)
	public WebElement Key_Generation_Popup;

	@FindBy(xpath = OR.Key_Generation_Close)
	public WebElement Key_Generation_Close;

	@FindBy(xpath = OR.Click_Checkout)
	public WebElement Click_Checkout;

	@FindBy(xpath = OR.Click_Close)
	public WebElement Click_Close;

	@FindBy(xpath = OR.Click_Swipe_Icon)
	public WebElement Click_Swipe_Icon;

	@FindBy(xpath = OR.Enter_Track_Data)
	public WebElement Enter_Track_Data;

	@FindBy(xpath = OR.Room_Assignment_PopUp_Error)
	public WebElement Room_Assignment_PopUp_Error;

	@FindBy(xpath = OR.Room_Selector_In_Room_Assignment_PopUp)
	public WebElement Room_Selector_In_Room_Assignment_PopUp;

	@FindBy(xpath = OR.ReCalculate_Folio_Options_PopUp)
	public WebElement ReCalculate_Folio_Options_PopUp;

	@FindBy(xpath = OR.ReCal_Folio_Options_PopUp_No_Charge_Changed)
	public WebElement ReCal_Folio_Options_PopUp_No_Charge_Changed;

	@FindBy(xpath = OR.ReCal_Folio_Options_PopUp_Select_Btn)
	public WebElement ReCal_Folio_Options_PopUp_Select_Btn;

	@FindBy(xpath = OR.ReCal_Folio_Options_PopUp_Cancel_Btn)
	public WebElement ReCal_Folio_Options_PopUp_Cancel_Btn;

	@FindBy(xpath = OR.Policy_Comparision_PopUp_Continue_Btn)
	public WebElement Policy_Comparision_PopUp_Continue_Btn;

	@FindBy(xpath = OR.Select_Continue_with_OriginalPolicy)
	public WebElement Select_Continue_with_OriginalPolicy;

	@FindBy(xpath = OR.Policy_Comparision_PopUp)
	public WebElement Policy_Comparision_PopUp;

	@FindBy(xpath = OR.ReCal_Folio_Options_PopUp_Recalculate)
	public WebElement ReCal_Folio_Options_PopUp_Recalculate;

	// Notes

	@FindBy(xpath = OR.Click_Add_Res_Note)
	public WebElement Click_Add_Res_Note;

	@FindBy(xpath = OR.verify_Add_Notes_popup)
	public WebElement verify_Add_Notes_popup;

	@FindBy(xpath = OR.Enter_Subject_Notes)
	public WebElement Enter_Subject_Notes;

	@FindBy(xpath = OR.Select_Note_Type)
	public WebElement Select_Note_Type;

	@FindBy(xpath = OR.Select_Note_Type1)
	public WebElement Select_Note_Type1;

	@FindBy(xpath = OR.Select_TaskCategory)
	public WebElement Select_TaskCategory;

	@FindBy(xpath = OR.Check_Action_Required)
	public WebElement Check_Action_Required;

	@FindBy(xpath = OR.Select_Notes_Status)
	public WebElement Select_Notes_Status;

	@FindBy(xpath = OR.Enter_Note_Details)
	public WebElement Enter_Note_Details;

	@FindBy(xpath = OR.Enter_Note_Details1)
	public WebElement Enter_Note_Details1;

	@FindBy(xpath = OR.Click_Save_Note)
	public WebElement Click_Save_Note;

	@FindBy(xpath = OR.Click_Save_Note1)
	public WebElement Click_Save_Note1;

	@FindBy(xpath = OR.Verify_Added_Notes)
	public WebElement Verify_Added_Notes;
	
	@FindBy(xpath = OR.addedNote)
	public WebElement addedNote;
	
	
	@FindBy(xpath = OR.NotesSubject)
	public WebElement NotesSubject;
	
	@FindBy(xpath = OR.getCopiedNotesSubject)
	public WebElement getCopiedNotesSubject;

	@FindBy(xpath = OR.Get_Confirmation_Number)
	public WebElement Get_Confirmation_Number;

	@FindBy(xpath = OR.Get_Reser_Number)
	public WebElement Get_Reser_Number;

	@FindBy(xpath = OR.Select_property_RoomAssign2)
	public WebElement Select_property_RoomAssign2;

	@FindBy(xpath = OR.Enter_Account_Res_Name)
	public WebElement Enter_Account_Res_Name;

	@FindBy(xpath = OR.Click_Search_House_Acc)
	public WebElement Click_Search_House_Acc;

	@FindBy(xpath = OR.Select_House_Acc)
	public WebElement Select_House_Acc;

	@FindBy(xpath = OR.Click_Select_House_acc)
	public WebElement Click_Select_House_acc;

	@FindBy(xpath = OR.Enter_Gift_ID)
	public WebElement Enter_Gift_ID;

	@FindBy(xpath = OR.Click_Go_Gift)
	public WebElement Click_Go_Gift;

	@FindBy(xpath = OR.Select_Gift_Certificate)
	public WebElement Select_Gift_Certificate;

	@FindBy(xpath = OR.Click_Select_Gift)
	public WebElement Click_Select_Gift;

	@FindBy(xpath = OR.Resell_Rooms_Popup)
	public WebElement Resell_Rooms_Popup;

	@FindBy(xpath = OR.Resell_Rooms_Popup_Continue_Btn)
	public WebElement Resell_Rooms_Popup_Continue_Btn;

	@FindBy(xpath = OR.Resell_Rooms_Popup_Cancel_Btn)
	public WebElement Resell_Rooms_Popup_Cancel_Btn;

	@FindBy(xpath = OR.Guestinfo)
	public WebElement Guestinfo;

	@FindBy(xpath = OR.Cancel_Reservation_Icon)
	public WebElement Cancel_Reservation_Icon;

	@FindBy(xpath = OR.Cancel_Res_Popup)
	public WebElement Cancel_Res_Popup;

	@FindBy(xpath = OR.Cancel_Reason_Txtarea)
	public WebElement Cancel_Reason_Txtarea;

	@FindBy(xpath = OR.VoidRC_Chkbox_In_Popup)
	public WebElement VoidRC_Chkbox_In_Popup;

	@FindBy(xpath = OR.Cancel_Res_Popup_Ok_Btn)
	public WebElement Cancel_Res_Popup_Ok_Btn;

	@FindBy(xpath = OR.ReservationCanellationID)
	public WebElement ReservationCanellationID;

	@FindBy(xpath = OR.Cancel_Res_Popup_Cancel_Btn)
	public WebElement Cancel_Res_Popup_Cancel_Btn;

	@FindBy(xpath = OR.Reservation_Status)
	public WebElement Reservation_Status;

	@FindBy(xpath = OR.Close_Tab_Btn)
	public WebElement Close_Tab_Btn;

	@FindBy(xpath = OR.AlertForTab)
	public WebElement AlertForTab;

	@FindBy(xpath = OR.AlertForTab_Yes_Btn)
	public WebElement AlertForTab_Yes_Btn;

	@FindBy(xpath = OR.Click_Continue_Deposit)
	public WebElement Click_Continue_Deposit;

	@FindBy(xpath = OR.Acc_Picker_Confirm_Continue_Btn)
	public WebElement Acc_Picker_Confirm_Continue_Btn;

	@FindBy(xpath = OR.Associated_AccountName)
	public WebElement Associated_AccountName;

	@FindBy(xpath = OR.Acc_Picker_Confirm)
	public WebElement Acc_Picker_Confirm;

	@FindBy(xpath = OR.Click_Show_PaymentInfo)
	public WebElement Click_Show_PaymentInfo;

	@FindBy(xpath = OR.Folio_Billing_Info_Popup)
	public WebElement Folio_Billing_Info_Popup;

	@FindBy(xpath = OR.Select_Salutation_PaymentInfo_Popup)
	public WebElement Select_Salutation_PaymentInfo_Popup;

	@FindBy(xpath = OR.Enter_First_Name_PaymentInfo_Popup)
	public WebElement Enter_First_Name_PaymentInfo_Popup;

	@FindBy(xpath = OR.Enter_Last_Name_PaymentInfo_Popup)
	public WebElement Enter_Last_Name_PaymentInfo_Popup;

	@FindBy(xpath = OR.Enter_Phone_PaymentInfo_Popup)
	public WebElement Enter_Phone_PaymentInfo_Popup;

	@FindBy(xpath = OR.Enter_AltPhone_PaymentInfo_Popup)
	public WebElement Enter_AltPhone_PaymentInfo_Popup;

	@FindBy(xpath = OR.Enter_Email_PaymentInfo_Popup)
	public WebElement Enter_Email_PaymentInfo_Popup;

	@FindBy(xpath = OR.Enter_Line1_PaymentInfo_Popup)
	public WebElement Enter_Line1_PaymentInfo_Popup;

	@FindBy(xpath = OR.Enter_Line2_PaymentInfo_Popup)
	public WebElement Enter_Line2_PaymentInfo_Popup;

	@FindBy(xpath = OR.Enter_Line3_PaymentInfo_Popup)
	public WebElement Enter_Line3_PaymentInfo_Popup;

	@FindBy(xpath = OR.Enter_City_PaymentInfo_Popup)
	public WebElement Enter_City_PaymentInfo_Popup;

	@FindBy(xpath = OR.Select_Country_PaymentInfo_Popup)
	public WebElement Select_Country_PaymentInfo_Popup;

	@FindBy(xpath = OR.Select_State_PaymentInfo_Popup)
	public WebElement Select_State_PaymentInfo_Popup;

	@FindBy(xpath = OR.Enter_Postal_Code_PaymentInfo_Popup)
	public WebElement Enter_Postal_Code_PaymentInfo_Popup;

	@FindBy(xpath = OR.Select_Payment_Method_PaymentInfo_Popup)
	public WebElement Select_Payment_Method_PaymentInfo_Popup;

	@FindBy(xpath = OR.Enter_Account_Number_PaymentInfo_Popup)
	public WebElement Enter_Account_Number_PaymentInfo_Popup;

	@FindBy(xpath = OR.Enter_CardExpiryDate_PaymentInfo_Popup)
	public WebElement Enter_CardExpiryDate_PaymentInfo_Popup;

	@FindBy(xpath = OR.Enter_BillingNotes_PaymentInfo_Popup)
	public WebElement Enter_BillingNotes_PaymentInfo_Popup;

	@FindBy(xpath = OR.Save_Btn_PaymentInfo_Popup)
	public WebElement Save_Btn_PaymentInfo_Popup;

	@FindBy(xpath = OR.Cancel_Btn_PaymentInfo_Popup)
	public WebElement Cancel_Btn_PaymentInfo_Popup;

	@FindBy(xpath = OR.Payment_Info_Textarea)
	public WebElement Payment_Info_Textarea;

	@FindBy(xpath = OR.First_RC_Line_Item_Desc)
	public WebElement First_RC_Line_Item_Desc;

	@FindBy(xpath = OR.First_RC_Line_Item_Amount)
	public WebElement First_RC_Line_Item_Amount;

	@FindBy(xpath = OR.Item_Details_Popup)
	public WebElement Item_Details_Popup;

	@FindBy(xpath = NewReservation.Item_Details_Popup_Tax)
	public WebElement Item_Details_Popup_Tax;

	@FindBy(xpath = NewReservation.Item_Details_Popup_TotalCharges)
	public WebElement Item_Details_Popup_TotalCharges;

	@FindBy(xpath = OR.Item_Details_Popup_Descriptions)
	public List<WebElement> Item_Details_Popup_Descriptions;

	@FindBy(xpath = OR.Item_Details_Popup_Cancel_Btn)
	public WebElement Item_Details_Popup_Cancel_Btn;

	@FindBy(xpath = OR.RC_Link_In_Item_Details_Popup)
	public WebElement RC_Link_In_Item_Details_Popup;

	@FindBy(xpath = OR.Tax_Item_Details_Popup)
	public WebElement Tax_Item_Details_Popup;

	@FindBy(xpath = OR.Sales_Tax_Link_In_Item_Details_Popup)
	public WebElement Sales_Tax_Link_In_Item_Details_Popup;

	@FindBy(xpath = OR.Total_Charges_In_Item_Details_Popup)
	public WebElement Total_Charges_In_Item_Details_Popup;

	@FindBy(xpath = OR.Include_Taxes_in_Line_Items_Checkbox)
	public WebElement Include_Taxes_in_Line_Items_Checkbox;

	@FindBy(xpath = OR.Rate_Details_Popup)
	public WebElement Rate_Details_Popup;

	@FindBy(xpath = OR.Rate_Details_Popup_Cancel_Btn)
	public WebElement Rate_Details_Popup_Cancel_Btn;

	@FindBy(xpath = OR.Display_Name_In_Tax_Item_Details_Popup)
	public WebElement Display_Name_In_Tax_Item_Details_Popup;

	@FindBy(xpath = OR.Rate_Name_In_Rate_Details_Popup)
	public WebElement Rate_Name_In_Rate_Details_Popup;

	@FindBy(xpath = OR.Click_Book_Reservation)
	public WebElement Click_Book_Reservation;

	@FindBy(xpath = OR.Check_Split_Rooms)
	public WebElement Check_Split_Rooms;

	@FindBy(xpath = OR.GuestInfo)
	public WebElement GuestInfo;
	
	@FindBy(xpath = OR.getGuestName1)
	public WebElement getGuestName1;

	@FindBy(xpath = OR.GuestInfo_1)
	public WebElement GuestInfo_1;

	// Payment amount

	@FindBy(xpath = OR.Room_Charges)
	public WebElement Room_Charges;

	@FindBy(xpath = OR.Incidentals)
	public WebElement Incidentals;

	@FindBy(xpath = OR.TaxesAndServiceCharges)
	public WebElement TaxesAndServiceCharges;

	@FindBy(xpath = OR.TotalCharges)
	public WebElement TotalCharges;

	@FindBy(xpath = OR.TaxExemptThisFieldIsdRequired)
	public WebElement TaxExemptThisFieldIsdRequired;

	@FindBy(xpath = OR.MoveFolio_Folio)
	public WebElement MoveFolio_Folio;

	@FindBy(xpath = OR.FirstOpenedReservationClose)
	public WebElement FirstOpenedReservationClose;

	@FindBy(xpath = OR.Processed_Amount_In_Paymentdetails_Popup)
	public WebElement Processed_Amount_In_Paymentdetails_Popup;

	// Guest History
	@FindBy(xpath = OR.Click_BasicSearch)
	public WebElement Click_BasicSearch;

	@FindBy(xpath = OR.clickReservation)
	public WebElement clickReservation;

	@FindBy(xpath = OR.findGuestProfile)
	public WebElement findGuestProfile;

	@FindBy(xpath = OR.clickEditButton)
	public WebElement clickEditButton;

	@FindBy(xpath = OR.selectGuest)
	public WebElement selectGuest;

	@FindBy(xpath = OR.continueButton)
	public WebElement continueButton;

	@FindBy(xpath = OR.reservation_Save)
	public WebElement reservation_Save;

	@FindBy(xpath = OR.ReservationPage_Reset_Btn)
	public WebElement ReservationPage_Reset_Btn;

	@FindBy(xpath = OR.Add_LineItem)
	public WebElement Add_LineItem;

	@FindBy(xpath = OR.Select_LineItem_Category)
	public WebElement Select_LineItem_Category;

	@FindBy(xpath = OR.Enter_LineItem_Amount)
	public WebElement Enter_LineItem_Amount;

	@FindBy(xpath = OR.Commit_LineItem)
	public WebElement Commit_LineItem;

	@FindBy(xpath = OR.Reservation_Folio_Options)
	public WebElement Reservation_Folio_Options;

	@FindBy(xpath = OR.Reservation_Folio_Details)
	public WebElement Reservation_Folio_Details;

	@FindBy(xpath = OR.Select_Reservation_Checkin_Policy)
	public WebElement Select_Reservation_Checkin_Policy;

	@FindBy(xpath = OR.Select_Reservation_NoShow_Policy)
	public WebElement Select_Reservation_NoShow_Policy;

	@FindBy(xpath = OR.Click_Reservation_Cancellation_Policy)
	public WebElement Click_Reservation_Cancellation_Policy;

	@FindBy(xpath = OR.Select_Reservation_Deposit_Policy)
	public WebElement Select_Reservation_Deposit_Policy;

	@FindBy(xpath = OR.Reservation_Folio)
	public WebElement Reservation_Folio;

	@FindBy(xpath = OR.Already_Checked_In_Confirm_Popup)
	public WebElement Already_Checked_In_Confirm_Popup;

	@FindBy(xpath = OR.Already_Checked_In_Confirm_Popup_Confirm_Btn)
	public WebElement Already_Checked_In_Confirm_Popup_Confirm_Btn;

	// room assignment
	@FindBy(xpath = OR.RoomAssignmentButton)
	public WebElement RoomAssignmentButton;

	@FindBy(xpath = OR.RoomCharges)
	public WebElement RoomCharges;

	@FindBy(css = OR.RoomAssignment_DatePicker_Button)
	public WebElement RoomAssignment_DateIcon;

	@FindBy(xpath = OR.RoomAssignment_DatePicker_Button_2)
	public WebElement RoomAssignment_DatePicker_Button_2;

	@FindBy(xpath = OR.RoomAssignment_DatePicker_Button_1)
	public List<WebElement> RoomAssignment_DatePicker_Button_1;

	@FindBy(css = OR.SelectDate)
	public WebElement SelectDate;

	@FindBy(xpath = OR.RoomAssign_Check)
	public WebElement RoomAssign_Check;

	@FindBy(css = OR.SearchRoomButton)
	public WebElement SearchRoomButton;

	@FindBy(xpath = OR.ClickSearchRoomButton)
	public WebElement ClickSearchRoomButton;

	@FindBy(xpath = OR.SelectRoomClasses)
	public WebElement SelectRoomClasses;

	@FindBy(xpath = OR.SelectRoomClasses)
	public List<WebElement> SplitRooms;

	@FindBy(xpath = OR.SelectRoomNumbers)
	public WebElement SelectRoomNumbers;

	@FindBy(xpath = OR.SelectRoomNumbers)
	public List<WebElement> SplitRoomNumbers;

	@FindBy(xpath = OR.FirstRoomNumberInList)
	public WebElement FirstRoomNumberInList;

	@FindBy(css = OR.CheckRule)
	public WebElement CheckRule;

	@FindBy(xpath = OR.AvailableRoom)
	public WebElement AvailableRoom;

	@FindBy(xpath = OR.SelectButton)
	public WebElement SelectButton;

	@FindBy(css = OR.ContinueButton)
	public WebElement ContinueButton;

	@FindBy(xpath = OR.Continue)
	public List<WebElement> Continue;

	@FindBy(xpath = OR.RoleBroken_Continue)
	public WebElement RoleBroken_Continue;

	@FindBy(xpath = OR.ReservationSaveButton)
	public WebElement ReservationSaveButton;

	@FindBy(xpath = OR.ReservationSaveButton_2)
	public WebElement ReservationSaveButton_2;

	@FindBy(xpath = OR.SaveAndCloseReservation)
	public WebElement SaveAndCloseReservation;

	@FindBy(xpath = OR.CopyButton)
	public WebElement CopyButton;

	@FindBy(xpath = OR.CopyReservationTab)
	public WebElement CopyReservationTab;

	@FindBy(className = OR.ReservationTab)
	public List<WebElement> ReservationTab;

	@FindBy(xpath = OR.RoomAssign_Number)
	public List<WebElement> RoomAssign_Number;

	@FindBy(xpath = OR.ExtendReservation_Button)
	public WebElement ExtendReservation_Button;

	@FindBy(xpath = OR.RoomChargerPopup)
	public WebElement RoomChargerPopup;

	@FindBy(xpath = OR.RecalculateFolio_RadioButton)
	public WebElement RecalculateFolio_RadioButton;

	@FindBy(xpath = OR.SelectClickOnReCalculateFolios)
	public WebElement Button_RoomChargerSelect;

	@FindBy(xpath = OR.ApplyDeltaEnabled_RadioButton)
	public WebElement ApplyDeltaEnabled_RadioButton;

	@FindBy(xpath = OR.NoRoomCharger_RadioButton)
	public WebElement NoRoomCharger_RadioButton;

	@FindBy(xpath = OR.Depart_Value)
	public List<WebElement> Depart_Value;

	@FindBy(xpath = OR.NightField)
	public WebElement NightField;

	// Reservation Status
	
	@FindBy(xpath = OR.Reservation_BookButton)
	public WebElement Reservation_BookButton;

	@FindBy(css = OR.RollBackButton)
	public WebElement RollBackButton;

	@FindBy(xpath = OR.NoShowIcon)
	public WebElement NoShowIcon;

	@FindBy(xpath = OR.VoidRoomChargerCheckbox)
	public List<WebElement> VoidRoomChargerCheckbox;

	@FindBy(xpath = OR.OkbuttonInNowShow)
	public List<WebElement> OkbuttonInNowShow;

	@FindBy(xpath = OR.RollBackStatus_Select)
	public WebElement RollBackStatus;
	@FindBy(xpath = OR.TaxExmpt_Popup)
	public WebElement TaxExmpt_Popup;

	@FindBy(xpath = OR.Set_TaxExe_Button)
	public WebElement Set_TaxExe_Button;

	@FindBy(xpath = OR.Cancel_TaxExe_Button)
	public WebElement Cancel_TaxExe_Button;

	@FindBy(xpath = OR.BulkAction)
	public WebElement BulkAction;

	@FindBy(xpath = OR.BulkActionCheckOut)
	public WebElement BulkActionCheckOut;

	@FindBy(xpath = OR.BulkActionCheckOutGrid)
	public WebElement BulkActionCheckOutGrid;

	@FindBy(xpath = OR.Rules_Popup)
	public WebElement Rules_Popup;

	@FindBy(xpath = OR.OK_Button)
	public WebElement OK_Button;

	@FindBy(xpath = OR.More_Rules_button)
	public List<WebElement> More_Rules_button;

	@FindBy(xpath = OR.RoomAssign_Cancel)
	public WebElement RoomAssign_Cancel;

	@FindBy(xpath = OR.Enter_TravelAgen_Account)
	public WebElement Enter_TravelAgen_Account;

	@FindBy(xpath = OR.AccountPicker_Continue_Button)
	public WebElement AccountPicker_Continue_Button;

	@FindBy(xpath = OR.Enter_GuestProfile_Name)
	public WebElement Enter_GuestProfile_Name;

	@FindBy(css = OR.Select_GuestProfile)
	public List<WebElement> Select_GuestProfile;

	@FindBy(xpath = OR.GuestProfile_Popup)
	public WebElement GuestProfile_Popup;

	@FindBy(xpath = OR.GuestProfileAttached)
	public WebElement GuestProfileAttached;

	@FindBy(xpath = OR.GuestProfile_Popup_Continue)
	public WebElement GuestProfile_Popup_Continue;

	@FindBy(xpath = OR.NotesType)
	public List<WebElement> NotesType;

	@FindBy(xpath = OR.SelectRoomClasses)
	public List<WebElement> SelectRoomClass;

	@FindBy(xpath = OR.SelectRoomNumbers)
	public List<WebElement> SelectRoomNumber;

	@FindBy(xpath = OR.Res_Reset_Button)
	public WebElement Res_Reset_Button;

	@FindBy(xpath = OR.GestInfo_Tab)
	public WebElement GestInfo_Tab;

	@FindBy(xpath = OR.ClickOnPrintIcon)
	public WebElement PrintIcon;

	@FindBy(xpath = OR.Report_Popup)
	public WebElement Report_Popup;

	@FindBy(className = OR.ReportButtonList)
	public List<WebElement> ReportButtonList;

	@FindBy(xpath = OR.DownloadasPDF)
	public WebElement DownloadasPDF;

	@FindBy(xpath = OR.ReservationPage)
	public WebElement ReservationPage;

	@FindBy(className = OR.Select_ReportType)
	public WebElement Select_ReportType;

	@FindBy(xpath = OR.CheckIn_Button)
	public WebElement CheckIn_Button;

	@FindBy(xpath = OR.CheckIN_Confirm)
	public WebElement CheckIN_Confirm;

	@FindBy(xpath = OR.CancelRoomAssignment_Button)
	public WebElement CancelRoomAssignment_Button;

	@FindBy(xpath = OR.CloseReservationTab)
	public WebElement CloseReservationTab;

	@FindBy(xpath = OR.ConfirmationPopup)
	public WebElement ConfirmationPopup;

	@FindBy(xpath = OR.New_Reservation_Button_2)
	public WebElement New_Reservation_Button_2;

	@FindBy(xpath = OR.Reservation_market_Segment_2)
	public WebElement Reservation_market_Segment_2;

	@FindBy(xpath = OR.Reservation_Referral_2)
	public WebElement Reservation_Referral_2;

	@FindBy(xpath = OR.Enter_Travel_Agent_2)
	public WebElement Enter_Travel_Agent_2;

	@FindBy(xpath = OR.Enter_Ext_Reservation_2)
	public WebElement Enter_Ext_Reservation_2;

	@FindBy(xpath = OR.Enter_Guest_Profile_2)
	public WebElement Enter_Guest_Profile_2;

	@FindBy(xpath = OR.Select_Saluation_2)
	public WebElement Select_Saluation_2;

	@FindBy(xpath = OR.Enter_First_Name_2)
	public WebElement Enter_First_Name_2;

	@FindBy(xpath = OR.Enter_Last_Name_2)
	public WebElement Enter_Last_Name_2;

	@FindBy(xpath = OR.Enter_Address_Search_2)
	public WebElement Enter_Address_Search_2;

	@FindBy(xpath = OR.Enter_Line1_2)
	public WebElement Enter_Line1_2;

	@FindBy(xpath = OR.Enter_Line2_2)
	public WebElement Enter_Line2_2;

	@FindBy(xpath = OR.Enter_Line3_2)
	public WebElement Enter_Line3_2;

	@FindBy(xpath = OR.Enter_Address_Search_3)
	public WebElement Enter_Address_Search_3;

	@FindBy(xpath = OR.Enter_Line1_3)
	public WebElement Enter_Line1_3;

	@FindBy(xpath = OR.Enter_Line2_3)
	public WebElement Enter_Line2_3;

	@FindBy(xpath = OR.Enter_Line3_3)
	public WebElement Enter_Line3_3;

	@FindBy(xpath = OR.Enter_City_3)
	public WebElement Enter_City_3;

	@FindBy(xpath = OR.Enter_City_2)
	public WebElement Enter_City_2;

	@FindBy(xpath = OR.Select_Country_2)
	public WebElement Select_Country_2;

	@FindBy(xpath = OR.Select_State_2)
	public WebElement Select_State_2;

	@FindBy(xpath = OR.Select_State_3)
	public WebElement Select_State_3;

	@FindBy(xpath = OR.Enter_Postal_Code_3)
	public WebElement Enter_Postal_Code_3;

	@FindBy(xpath = OR.Enter_Phone_Number_3)
	public WebElement Enter_Phone_Number_3;

	@FindBy(xpath = OR.Enter_Postal_Code_2)
	public WebElement Enter_Postal_Code_2;

	@FindBy(xpath = OR.Enter_Phone_Number_2)
	public WebElement Enter_Phone_Number_2;

	@FindBy(xpath = OR.Enter_Alt_Phn_number_2)
	public WebElement Enter_Alt_Phn_number_2;

	@FindBy(xpath = OR.Enter_email_2)
	public WebElement Enter_email_2;

	@FindBy(xpath = OR.Enter_Account_2)
	public WebElement Enter_Account_2;

	@FindBy(xpath = OR.Check_IsTaxExempt_2)
	public WebElement Check_IsTaxExempt_2;

	@FindBy(xpath = OR.Enter_TaxExemptId_2)
	public WebElement Enter_TaxExemptId_2;

	@FindBy(xpath = OR.ProcessButton)
	public WebElement ProcessButton;

	@FindBy(xpath = OR.BulkCheckOut_Popup)
	public WebElement BulkCheckOut_Popup;

	@FindBy(xpath = OR.CloseButton)
	public WebElement CloseButton;

	@FindBy(xpath = OR.ClickTravelAgentAccount)
	public WebElement ClickTravelAgentAccount;

	@FindBy(xpath = OR.ConfirmPopup)
	public WebElement ConfirmPopup;

	@FindBy(xpath = OR.ConfirmButton)
	public WebElement ConfirmButton;

	@FindBy(xpath = OR.CheckinReport_CancelButton)
	public WebElement CheckinReport_CancelButton;

	@FindBy(id = OR.AddressAutoSuggestionDropDown)
	public WebElement AddressAutoSuggestionDropDown;

	@FindBy(id = OR.GuestProfileAutoSuggestionDropDown)
	public WebElement GuestProfileAutoSuggestionDropDown;

	@FindBy(id = OR.TravelAgentAutoSuggestionDropDown)
	public WebElement TravelAgentAutoSuggestionDropDown;

	@FindBy(id = OR.AccountNameAutoSuggestionDropDown)
	public WebElement AccountNameAutoSuggestionDropDown;

	@FindBy(xpath = OR.Copy_Picked_Account_Data_To_Reservation_ContinueButton)
	public WebElement Copy_Picked_Account_Data_To_Reservation_ContinueButton;

	@FindBy(xpath = OR.Account_name)
	public WebElement Account_name;

	@FindBy(xpath = OR.GetSelectedRoomNumber)
	public WebElement GetSelectedRoomNumber;

	@FindBy(xpath = OR.AdvancedSearched_Button)
	public WebElement AdvancedSearched_Button;

	@FindBy(xpath = OR.AdvancedSearchPage_load)
	public WebElement AdvancedSearchPage_load;

	@FindBy(xpath = OR.AdvancedSearch_StayFromDate)
	public WebElement AdvancedSearch_StayFromDate;

	@FindBy(xpath = OR.AdvancedSearch_StayToDate)
	public WebElement AdvancedSearch_StayToDate;

	@FindBy(xpath = OR.AdvancedSearched_RoomClassDropDown)
	public WebElement AdvancedSearched_RoomClassDropDown;

	@FindBy(xpath = OR.AdvancedSearched_RoomClass)
	public WebElement AdvancedSearched_RoomClass;

	@FindBy(xpath = OR.AdvancedSearched_SelectedRoomClass)
	public WebElement AdvancedSearched_SelectedRoomClass;

	@FindBy(xpath = OR.AdvancedSearched_Source)
	public WebElement AdvancedSearched_Source;

	@FindBy(xpath = OR.AdvancedSearch_SearchButton)
	public WebElement AdvancedSearch_SearchButton;

	@FindBy(xpath = OR.ArrivalDate)
	public WebElement ArrivalDate;

	@FindBy(xpath = OR.DepartureDate)
	public WebElement DepartureDate;

	@FindBy(xpath = OR.Searched_RoomClass)
	public WebElement Searched_RoomClass;

	@FindBy(xpath = OR.Searched_ResNumber)
	public WebElement Searched_ResNumber;

	@FindBy(xpath = OR.Searched_GuestName)
	public WebElement Searched_GuestName;

	@FindBy(xpath = OR.AdvancedSearch_ResultNextPage)
	public WebElement AdvancedSearch_ResultNextPage;

	@FindBy(xpath = OR.TravelAgent_AutoSuggestionDropDown)
	public WebElement TravelAgent_AutoSuggestionDropDown;

	@FindBy(xpath = OR.ReservationUpdate_ConfirmChange)
	public WebElement ReservationUpdate_ConfirmChange;

	@FindBy(xpath = OR.PaymentDetails_CancelButton)
	public WebElement PaymentDetails_CancelButton;

	@FindBy(xpath = OR.ReservationRoomStatus)
	public WebElement ReservationRoomStatus;

	@FindBy(xpath = OR.DeleteTaskButton)
	public WebElement DeleteTaskButton;

	@FindBy(xpath = OR.DeleteTaskButton1)
	public WebElement DeleteTaskButton1;

	@FindBy(xpath = OR.DeleteTaskYesButton)
	public WebElement DeleteTaskYesButton;

	@FindBy(xpath = OR.DeleteTaskNoButton)
	public WebElement DeleteTaskNoButton;

	@FindBy(xpath = OR.Popup_LineItemDescription)
	public WebElement Popup_LineItemDescription;

	@FindBy(xpath = OR.RateDetailsPopup)
	public WebElement RateDetailsPopup;

	@FindBy(xpath = OR.RateNameLabel)
	public WebElement RateNameLabel;

	@FindBy(xpath = OR.AddTask)
	public WebElement AddTask;

	@FindBy(xpath = OR.TaskDetailPopup)
	public WebElement TaskDetailPopup;

	@FindBy(xpath = OR.SelectTaskCategory)
	public WebElement SelectTaskCategory;

	@FindBy(xpath = OR.SelectTaskType)
	public WebElement SelectTaskType;

	@FindBy(xpath = OR.CancelTask)
	public WebElement CancelTask;

	@FindBy(xpath = OR.SaveTaskButton)
	public WebElement SaveTaskButton;

	@FindBy(xpath = OR.TaskListTable)
	public WebElement TaskListTable;

	@FindBy(xpath = OR.click_Select_Button_RoomChargesChanged)
	public WebElement click_Select_Button_RoomChargesChanged;

	@FindBy(xpath = NewReservation.RecalculateRoomCharges)
	public WebElement RecalculateRoomCharges;

	@FindBy(xpath = NewReservation.NOShow_VoidRoomCharges)
	public WebElement NOShow_VoidRoomCharges;

	@FindBy(xpath = NewReservation.NOShow_NoShowFee)
	public WebElement NOShow_NoShowFee;

	@FindBy(xpath = NewReservation.Cancellation_CancellationFee)
	public WebElement Cancellation_CancellationFee;

	@FindBy(xpath = NewReservation.NOShow_OkButton)
	public WebElement NOShow_OkButton;

	@FindBy(xpath = NewReservation.NOShow_CancelButton)
	public WebElement NOShow_CancelButton;

	@FindBy(xpath = NewReservation.Reservation_FolioBalance)
	public WebElement Reservation_FolioBalance;

	@FindBy(xpath = OR.Click_LockButton)
	public WebElement Click_LockButton;

	@FindBy(xpath = OR.TaskValue)
	public List<WebElement> TaskValue;

	@FindBy(xpath = OR.AddTaskButton)
	public WebElement AddTaskButton;

	@FindBy(xpath = OR.TaskPopup)
	public WebElement TaskPopup;

	@FindBy(css = NewReservation.DepositPolicy_Button)
	public List<WebElement> DepositPolicy_Button;

	@FindBy(xpath = NewReservation.CloseResTab)
	public List<WebElement> CloseResTab;

	@FindBy(xpath = NewReservation.RoomAssignmentStatus)
	public WebElement RoomAssignmentStatus;

	@FindBy(xpath = NewReservation.RoomCharger_SelectButton)
	public List<WebElement> RoomCharger_SelectButton;

	@FindBy(xpath = NewReservation.AccountLoadingToaster)
	public WebElement AccountLoadingToaster;

	@FindBy(xpath = NewReservation.RoomSelectPopup)
	public WebElement RoomSelectPopup;

	@FindBy(xpath = NewReservation.RoomDirtyButton)
	public WebElement RoomDirtyButton;

	@FindBy(xpath = NewReservation.Balance)
	public WebElement Balance;

	@FindBy(xpath = OR.Select_Payment_Method_2)
	public WebElement Select_Payment_Method_2;

	@FindBy(xpath = OR.Enter_Account_Number_2)
	public WebElement Enter_Account_Number_2;

	@FindBy(xpath = OR.Enter_Exp_Card_2)
	public WebElement Enter_Exp_Card_2;

	@FindBy(xpath = OR.Enter_Billing_Note)
	public WebElement Enter_Billing_Note_2;

	@FindBy(xpath = OR.Add_Note_Reservation_2)
	public WebElement Add_Note_Reservation_2;

	@FindBy(xpath = OR.Click_RoomPicker_2)
	public WebElement Click_RoomPicker_2;

	@FindBy(xpath = OR.Select_Payment_Method_3)
	public WebElement Select_Payment_Method_3;

	@FindBy(xpath = OR.Select_Payment_Method_4)
	public WebElement Select_Payment_Method_4;

	@FindBy(xpath = OR.Enter_Account_Number_3)
	public WebElement Enter_Account_Number_3;

	@FindBy(xpath = OR.Enter_Exp_Card_3)
	public WebElement Enter_Exp_Card_3;

	@FindBy(xpath = OR.Enter_Billing_Note_3)
	public WebElement Enter_Billing_Note_3;

	@FindBy(xpath = OR.SelectDate_2)
	public WebElement SelectDate_2;

	@FindBy(xpath = OR.RoomAssign_Check_2)
	public WebElement RoomAssign_Check_2;

	@FindBy(xpath = OR.SearchRoomButton_2)
	public WebElement SearchRoomButton_2;

	@FindBy(xpath = OR.SelectRoomNumbers_2)
	public WebElement SelectRoomNumbers_2;

	@FindBy(xpath = OR.SelectRoomClasses_2)
	public WebElement SelectRoomClasses_2;

	@FindBy(xpath = OR.Select_AllFolio)
	public WebElement Select_AllFolio;

	@FindBy(xpath = OR.BtnVoid)
	public WebElement BtnVoid;

	@FindBy(xpath = OR.TextArea_Notest)
	public WebElement TextArea_Notest;

	@FindBy(xpath = OR.Notes_Void)
	public WebElement Notes_Void;

	@FindBy(xpath = NewReservation.ReservationArrivalDate)
	public WebElement ReservationArrivalDate;

	@FindBy(xpath = OR.ReservationFolioBalance)
	public WebElement ReservationFolioBalance;

	@FindBy(xpath = OR.FolioTab_Reservation)
	public WebElement FolioTab_Reservation;

	@FindBy(xpath = OR.GuestInfo_Tab)
	public WebElement GuestInfo_Tab;

	@FindBy(xpath = OR.GetRoomClassNumber)
	public WebElement GetRoomClassNumber;

	@FindBy(xpath = OR.Close_ReservationTab)
	public WebElement Close_ReservationTab;

	@FindBy(xpath = OR.AlertForTab_Yes_Btn)
	public List<WebElement> ListAlertForTab;

	@FindBy(xpath = OR.CheckIn_Out_Field)
	public List<WebElement> CheckIn_Out_Field;

	@FindBy(xpath = OR.EnterPromocode)
	public WebElement EnterPromocode;

	@FindBy(xpath = OR.RoomDirtyConfirmBtn)
	public List<WebElement> ListRoomDirtyConfirmBtn;

	@FindBy(xpath = OR.RoomDirtyConfirmBtn)
	public WebElement RoomDirtyConfirmBtn;

	@FindBy(xpath = OR.FolioTaxItem)
	public List<WebElement> FolioTaxItem;

	@FindBy(xpath = OR.ReservationsStatus)
	public WebElement ReservationsStatus;

	@FindBy(xpath = OR.LongStayMessage)
	public WebElement LongStayMessage;

	@FindBy(xpath = OR.RoomClass_Number)
	public WebElement RoomClass_Number;
	
	@FindBy(xpath = OR.RoomClassNumber)
	public WebElement RoomClassNumber;

	@FindBy(xpath = OR.Referral_MarketInfo_ValidationMessage)
	public WebElement Referral_MarketInfo_ValidationMessage;

	@FindBy(xpath = OR.FirstName_ContactInfo_ValidationMessage)
	public WebElement FirstName_ContactInfo_ValidationMessage;

	@FindBy(xpath = OR.LastName_ContactInfo_ValidationMessage)
	public WebElement LastName_ContactInfo_ValidationMessage;

	@FindBy(xpath = OR.PhoneNumber_ContactInfo_ValidationMessage)
	public WebElement PhoneNumber_ContactInfo_ValidationMessage;

	@FindBy(xpath = OR.Address_ContactInfo_Line1_ValidationMessage)
	public WebElement Address_ContactInfo_Line1_ValidationMessage;

	@FindBy(xpath = OR.City_ContactInfo_ValidationMessage)
	public WebElement City_ContactInfo_ValidationMessage;

	@FindBy(xpath = OR.PostalCode_ContactInfo_ValidationMessage)
	public WebElement PostalCode_ContactInfo_ValidationMessage;

	@FindBy(xpath = OR.State_ContactInfo_ValidationMessage)
	public WebElement State_ContactInfo_ValidationMessage;

	@FindBy(xpath = OR.Address_Line1_BillingInfo_ValidationMessage)
	public WebElement Address_Line1_BillingInfo_ValidationMessage;

	@FindBy(xpath = OR.FirstName_BillingInfo_ValidationMessage)
	public WebElement FirstName_BillingInfo_ValidationMessage;

	@FindBy(xpath = OR.PhoneNumber_BillingInfo_ValidationMessage)
	public WebElement PhoneNumber_BillingInfo_ValidationMessage;

	@FindBy(xpath = OR.City_BillingInfo_ValidationMessage)
	public WebElement City_BillingInfo_ValidationMessage;

	@FindBy(xpath = OR.State_BillingInfo_ValidationMessage)
	public WebElement State_BillingInfo_ValidationMessage;

	@FindBy(xpath = OR.PostalCode_BillingInfo_ValidationMessage)
	public WebElement PostalCode_BillingInfo_ValidationMessage;

	@FindBy(xpath = OR.SelectRoom_ValidationMessage)
	public WebElement SelectRoom_ValidationMessage;

	@FindBy(xpath = OR.RoomSelection_Message_RoomAssignPage)
	public WebElement RoomSelection_Message_RoomAssignPage;

	@FindBy(xpath = OR.StartDate_ValidationMessage_RoomAssignPage)
	public WebElement StartDate_ValidationMessage_RoomAssignPage;

	@FindBy(xpath = OR.EndDate_ValidationMessage_RoomAssignPage)
	public WebElement EndDate_ValidationMessage_RoomAssignPage;

	@FindBy(xpath = OR.NightsCount_ValidationMessage_RoomAssignPage)
	public WebElement NightsCount_ValidationMessage_RoomAssignPage;

	@FindBy(xpath = OR.AdultsCount_ValidationMessage_RoomAssignPage)
	public WebElement AdultsCount_ValidationMessage_RoomAssignPage;

	@FindBy(xpath = OR.CurrentBalance)
	public WebElement CurrentBalance;

	@FindBy(xpath = OR.CurrentPayment)
	public WebElement CurrentPayment;

	@FindBy(xpath = OR.EndingBalance)
	public WebElement EndingBalance;

	@FindBy(xpath = OR.AccountPicker_ContinueBtn)
	public WebElement AccountPicker_ContinueBtn;

	@FindBy(xpath = OR.VerifyAccountName)
	public WebElement VerifyAccountName;

	@FindBy(xpath = OR.PickAccountFromDropDwon)
	public WebElement PickAccountFromDropDwon;

	@FindBy(xpath = NewReservation.Reservation_CreateGuestProfile_CheckBox)
	public WebElement Reservation_CreateGuestProfile_CheckBox;

	@FindBy(xpath = OR.getUnsassignedResCount)
	public WebElement getUnsassignedResCount;

	@FindBy(xpath = OR.getGuestName)
	public WebElement getGuestName;

	@FindBy(xpath = OR.clickUnassigned)
	public WebElement clickUnassigned;

	@FindBy(xpath = OR.CreateGuestCheckBox)
	public WebElement CreateGuestCheckBox;

	@FindBy(xpath = OR.RoomChargerPopup_RAP)
	public WebElement RoomChargerPopup_RAP;

	@FindBy(xpath = OR.RecalculateFolio_RadioButton__RAP)
	public WebElement RecalculateFolio_RadioButton__RAP;

	@FindBy(xpath = OR.Select__RAP)
	public WebElement Select__RAP;

	@FindBy(xpath = OR.ApplyDeltaEnabled_RadioButton__RAP)
	public WebElement ApplyDeltaEnabled_RadioButton__RAP;

	@FindBy(xpath = OR.NoRoomCharger_RadioButton_RAP)
	public WebElement NoRoomCharger_RadioButton_RAP;

	@FindBy(xpath = OR.GuestProfile_Checbox)
	public WebElement GuestProfile_Checbox;

	@FindBy(xpath = OR.List_LineItemAmount)
	public List<WebElement> List_LineItemAmount;

	@FindBy(xpath = OR.Reservations_Status)
	public WebElement Reservations_Status;

	@FindBy(xpath = OR.List_LineItemTax)
	public List<WebElement> List_LineItemTax;

	@FindBy(xpath = OR.TexExempt_CheckBox)
	public WebElement TexExempt_CheckBox;

	@FindBy(xpath = OR.SetExemptionAllItems_Button)
	public WebElement SetExemptionAllItems_Button;

	@FindBy(xpath = OR.SetExemptionFutureItems_Button)
	public WebElement SetExemptionFutureItems_Button;

	@FindBy(xpath = OR.LongStayCheckbox)
	public WebElement LongStayCheckbox;

	@FindBy(xpath = OR.closeReservation)
	public WebElement closeReservation;

	@FindBy(xpath = OR.getRoomClassName)
	public WebElement getRoomClassName;

	@FindBy(xpath = OR.Reservation_Get_UnassignedCount)
	public WebElement Reservation_Get_UnassignedCount;

	@FindBy(xpath = OR.Reservation_Unassigned)
	public WebElement Reservation_Unassigned;

	@FindBy(xpath = OR.RoomMoveTask)
	public WebElement RoomMoveTask;

	@FindBy(xpath = OR.Reservation_Folio_Pay)
	public WebElement Reservation_Folio_Pay;

	@FindBy(xpath = OR.Reservation_CreateGuestProfile)
	public WebElement Reservation_CreateGuestProfile;

	@FindBy(xpath = OR.Reservation_RoomChargeLineItem)
	public WebElement Reservation_RoomChargeLineItem;

	@FindBy(xpath = OR.Reservation_Folio_DropDown)
	public WebElement Reservation_Folio_DropDown;

	@FindBy(xpath = OR.Reservation_Folio_Select_AllLineItems)
	public WebElement Reservation_Folio_Select_AllLineItems;

	@FindBy(xpath = OR.Reservation_Folio_ApplyRouting)
	public WebElement Reservation_Folio_ApplyRouting;

	@FindBy(xpath = OR.OpenedReservation_CloseButton)
	public WebElement OpenedReservation_CloseButton;
	
	@FindBy(xpath=OR.OpenedReservation1_CloseButton)
	public WebElement OpenedReservation1_CloseButton;
	
	@FindBy(xpath=OR.Reservation_Folio_DisplayVoidItems)
	public WebElement Reservation_Folio_DisplayVoidItems;

	@FindBy(xpath = NewReservation.Reservation_SelectedArrival)
	public WebElement Reservation_SelectedArrival;

	@FindBy(xpath = NewReservation.Reservation_SelectedNights)
	public WebElement Reservation_SelectedNights;

	@FindBy(xpath = NewReservation.Reservation_RatePlan)
	public WebElement Reservation_RatePlan;

	// New Added
	@FindBy(xpath = NewReservation.Reservation_SameAsContactInfo_CheckBox)
	public WebElement Reservation_SameAsContactInfo_CheckBox;

	@FindBy(xpath = NewReservation.ContactInfo_Country)
	public WebElement ContactInfo_Country;

	@FindBy(xpath = NewReservation.ContactInfo_PhoneCountryCode)
	public WebElement ContactInfo_PhoneCountryCode;

	@FindBy(xpath = NewReservation.ContactInfo_AltPhoneCountryCode)
	public WebElement ContactInfo_AltPhoneCountryCode;

	@FindBy(xpath = NewReservation.ContactInfo_FaxCountryCode)
	public WebElement ContactInfo_FaxCountryCode;

	@FindBy(xpath = NewReservation.RoomAssignment_DateArrived)
	public WebElement RoomAssignment_DateArrived;

	@FindBy(xpath = NewReservation.RoomAssignment_DateDepart)
	public WebElement RoomAssignment_DateDepart;

	@FindBy(xpath = NewReservation.RoomAssignment_SearchedRow)
	public List<WebElement> RoomAssignment_SearchedRow;

	@FindBy(xpath = NewReservation.Reservation_SelectedAdults)
	public WebElement Reservation_SelectedAdults;

	@FindBy(xpath = NewReservation.Reservation_SelectedChildren)
	public WebElement Reservation_SelectedChildren;

	@FindBy(xpath = NewReservation.Reservation_SelectedGuestName)
	public WebElement Reservation_SelectedGuestName;

	@FindBy(xpath = NewReservation.Reservation_TaxAndServiceCharges)
	public WebElement Reservation_TaxAndServiceCharges;
	
	@FindBy(xpath = OR.InHouseRes)
	public WebElement InHouseRes;
	
	@FindBy(xpath = OR.InHouse_Box)
	public WebElement InHouse_Box;
	
	@FindBy(xpath = OR.Records_Found)
	public WebElement Records_Found;
	
	@FindBy(xpath = OR.Coloumn_Headers)
	public WebElement Coloumn_Headers;
	
	@FindBy(xpath = OR.RecordsInHoues)
	public WebElement RecordsInHoues;
	
	@FindBy(xpath = OR.Pagenation)
	public WebElement Pagenation;
	
	@FindBy(xpath = OR.ItemesPerpage)
	public WebElement ItemesPerpage;
	
	@FindBy(xpath = OR.InHouse_Reservation)
	public WebElement InHouse_Reservation;
	
	@FindBy(xpath = OR.Report_Print_PopUp)
	public WebElement Report_Print_PopUp;
	
	@FindBy(xpath = OR.InHouse_BulkAction)
	public WebElement InHouse_BulkAction;
	
	@FindBy(xpath = OR.Check_Box_Option)
	public WebElement Check_Box_Option;
	
	@FindBy(xpath = OR.Select_check_box)
	public WebElement Select_check_box;
	
	@FindBy(xpath = OR.AllArrivals_Box)
	public WebElement AllArrivals_Box;
	
	@FindBy(xpath = OR.Records_AllArrivals_Box)
	public WebElement Records_AllArrivals_Box;
	
	@FindBy(xpath = OR.countsof_records)
	public WebElement countsof_records;
	
	@FindBy(xpath = OR.AllArrivals_DropDown_Option)
	public WebElement AllArrivals_DropDown_Option;
	
	@FindBy(xpath = OR.pending_Records_count)
	public WebElement pending_Records_count;
	
	@FindBy(xpath = OR.pending_Arrivals)
	public WebElement pending_Arrivals;
	
	@FindBy(xpath = OR.Actual_Pending_Records)
	public WebElement Actual_Pending_Records;
	
	@FindBy(xpath = OR.Arrivals_and_Departure)
	public WebElement Arrivals_and_Departure;
	
	@FindBy(xpath = OR.Actuval_Records_Arrivals_and_Departure)
	public WebElement Actuval_Records_Arrivals_and_Departure;
	
	@FindBy(xpath = OR.Grid_Arrivals_and_Departure_Records)
	public WebElement Grid_Arrivals_and_Departure_Records;
	
	@FindBy(xpath = OR.pending_Dropdown)
	public WebElement pending_Dropdown;
	
	@FindBy(xpath = OR.All_Departures)
	public WebElement All_Departures;
	
	@FindBy(xpath = OR.All_Departures_Dropdown)
	public WebElement All_Departures_Dropdown;
	
	@FindBy(xpath = OR.All_Arrival_Departures)
	public WebElement All_Arrival_Departures;
	
	@FindBy(xpath = OR.Pending_Departures)
	public WebElement Pending_Departures;
	
	@FindBy(xpath = OR.Unassingned)
	public WebElement Unassingned;
	
	@FindBy(xpath = OR.newreservation)
	public WebElement newreservation;
	
	@FindBy(xpath = OR.Pending_departure_Dropdown)
	public WebElement Pending_departure_Dropdown;
	
	@FindBy(xpath = OR.Actuval_Pending_Departures_Records)
	public WebElement Actuval_Pending_Departures_Records;
	
	@FindBy(xpath = OR.Actuval_All_Departures_Records)
	public WebElement Actuval_All_Departures_Records;
	
	@FindBy(xpath = OR.Actuval_AllArrivals_departures)
	public WebElement Actuval_AllArrivals_departures;
	
	
	@FindBy(xpath = OR.All_departures_dropdown)
	public WebElement All_departures_dropdown;
		
	@FindBy(xpath = OR.calender_dropdown)
	public WebElement calender_dropdown;
	
	@FindBy(xpath = OR.Pendending_Departures_Records)
	public WebElement Pendending_Departures_Records;
	
	@FindBy(xpath = OR.All_Arrivals_And_departures_Records)
	public WebElement All_Arrivals_And_departures_Records;
	
	@FindBy(xpath = OR.All_Departures_Records)
	public WebElement All_Departures_Records;
	
	@FindBy(xpath = OR.visible_Records_All_Arrivals_And_departures)
	public WebElement visible_Records_All_Arrivals_And_departures;
	
	@FindBy(xpath = OR.All_Arrivals_And_departures)
	public WebElement All_Arrivals_And_departures;
	
	@FindBy(xpath = OR.Pending_Departures_Dropdown)
	public WebElement Pending_Departures_Dropdown;
	
	@FindBy(xpath = OR.visible_Records_Pending_departures)
	public WebElement visible_Records_Pending_departures;
	
	@FindBy(xpath = OR.Pendending_Departures)
	public WebElement Pendending_Departures;
	
	@FindBy(xpath = OR.visible_Records_All_Departures)
	public WebElement visible_Records_All_Departures;

	@FindBy(xpath = NewReservation.Reservation_RoomAssign_RatePlan)
	public WebElement Reservation_RoomAssign_RatePlan;
	
	@FindBy(xpath = OR.Dirty_Room_YesButton)
	public WebElement Dirty_Room_YesButton;
	
	@FindBy(xpath = OR.RollBackCancelBtn)
	public WebElement RollBackCancelBtn;
	
	@FindBy(xpath= OR.AccountNumber_HouseAccountPopup)
	public WebElement AccountNumber_HouseAccountPopup;
	
	@FindBy(xpath = NewReservation.AddedNote_NotesType)
	public List<WebElement> AddedNote_NotesType;
	
	@FindBy(xpath = NewReservation.AddedNote_Subject)
	public List<WebElement> AddedNote_Subject;
	
	@FindBy(xpath = OR.Enter_Contact_Last_Name)
	public WebElement Enter_Contact_Last_Name;
	
	@FindBy(xpath = OR.NotesTitle)
	public WebElement NotesTitle;
	
	@FindBy(xpath = OR.TaskListNotesType)
	public WebElement TaskListNotesType;
	
	@FindBy(xpath = OR.TaskList_NotesType)
	public WebElement TaskList_NotesType;
	
	@FindBy(xpath = OR.TaskListActionRequired)
	public WebElement TaskListActionRequired;
	
	@FindBy(xpath = OR.TaskListAction)
	public WebElement TaskListAction;
	
	@FindBy(xpath = OR.NotesPopUpCancelButton)
	public WebElement NotesPopUpCancelButton;

	@FindBy(xpath=OR.CountOfAdvancedSearchResults)
	public WebElement CountOfAdvancedSearchResults;
	
	@FindBy(xpath=OR.CreateGuestProfile)
	public WebElement CreateGuestProfile;
	
	@FindBy(xpath=OR.Click_Save)
	public WebElement Click_Save;
	

	@FindBy(xpath =OR.CloseUnSavedDataTab)
	public WebElement CloseUnSavedDataTab;
	
	@FindBy(xpath = OR.BasicRes_Status_Check)
	public WebElement BasicRes_Status_Check;
	
	@FindBy(xpath =OR.Ok_Button_Popup)
	public WebElement Ok_Button_Popup;
	
	@FindBy(xpath =OR.RuleName_TapeChart)
	public WebElement RuleName_TapeChart;
	
	@FindBy(xpath =OR.NewRes_RoomAssign_Cancel)
	public WebElement NewRes_RoomAssign_Cancel;
	
	@FindBy(xpath =OR.Reservation_Rule_Button)
	public WebElement Reservation_Rule_Button;
	
	@FindBy(xpath = OR.Select_RatePlan)
	public WebElement Select_RatePlan;
	
	@FindBy(xpath = OR.SearchPopUp)
	public WebElement SearchPopUp;
	
	@FindBy(xpath = OR.isDirtyRoomPopup_Confirm)
	public WebElement isDirtyRoomPopup_Confirm;
	
	@FindBy(xpath = OR.VerifyChangePolicyPopup)
	public WebElement VerifyChangePolicyPopup;
	
	@FindBy(xpath = OR.policyComparisionPopUp_Btn)
	public WebElement policyComparisionPopUp_Btn;
	
	@FindBy(xpath = OR.SelectRoomNumbers)
	public List<WebElement> List_SelectRoomNumbers;
	
	@FindBy(xpath = OR.NewReservationTab)
	public WebElement NewReservationTab;
	
	@FindBy(xpath = OR.CloseuUsavedReservationAlert)
	public WebElement unsavedReservationAlert;
	
	@FindBy(xpath=OR.clickAttachmentDropDown)
	public WebElement clickAttachmentDropDown;
	
	@FindBy(xpath=OR.selectGuestRegistration)
	public WebElement selectGuestRegistration;
	
	@FindBy(xpath=OR.customFieldButton)
	public WebElement customFieldButton;
	
	@FindBy(xpath=OR.bodyText)
	public WebElement bodyText;
	
	@FindBy(xpath = OR.Click_Email_icon)
	public List<WebElement> emailIconsInGuestDetailsTab;


	
	
	
	
	
	
}
