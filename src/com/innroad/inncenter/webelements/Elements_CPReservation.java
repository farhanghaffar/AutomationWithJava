
package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Reservation;

public class Elements_CPReservation {
	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_CPReservation(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = OR_Reservation.CP_NewReservation)
	public WebElement CP_NewReservation;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_CheckinCal)
	public WebElement CP_NewReservation_CheckinCal;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_CheckoutCal)
	public WebElement CP_NewReservation_CheckoutCal;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_Adults)
	public WebElement CP_NewReservation_Adults;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_Children)
	public WebElement CP_NewReservation_Children;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_Rateplan)
	public WebElement CP_NewReservation_Rateplan;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_PromoCode)
	public WebElement CP_NewReservation_PromoCode;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_FindRooms)
	public WebElement CP_NewReservation_FindRooms;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_DepositAmount)
	public WebElement CP_NewReservation_DepositAmount;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_OverrideDeposit)
	public WebElement CP_NewReservation_OverrideDeposit;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_OverrideDepositAmoount)
	public WebElement CP_NewReservation_OverrideDepositAmoount;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_TripTotalAmount)
	public WebElement CP_NewReservation_TripTotalAmount;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_Next)
	public WebElement CP_NewReservation_Next;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_GuestSalutation)
	public WebElement CP_NewReservation_GuestSalutation;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_GuestFirstName)
	public WebElement CP_NewReservation_GuestFirstName;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_GuestLastName)
	public WebElement CP_NewReservation_GuestLastName;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_ContactSalutation)
	public WebElement CP_NewReservation_ContactSalutation;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_ContactFirstName)
	public WebElement CP_NewReservation_ContactFirstName;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_ContactLastName)
	public WebElement CP_NewReservation_ContactLastName;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_Phone)
	public WebElement CP_NewReservation_Phone;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_Email)
	public WebElement CP_NewReservation_Email;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_AltenativePhone)
	public WebElement CP_NewReservation_AltenativePhone;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_Account)
	public WebElement CP_NewReservation_Account;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_Address1)
	public WebElement CP_NewReservation_Address1;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_Address2)
	public WebElement CP_NewReservation_Address2;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_Address3)
	public WebElement CP_NewReservation_Address3;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_City)
	public WebElement CP_NewReservation_City;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_PostalCode)
	public WebElement CP_NewReservation_PostalCode;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_CreateGuestProfile)
	public WebElement CP_NewReservation_CreateGuestProfile;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_PaymentMethod)
	public WebElement CP_NewReservation_PaymentMethod;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_CardNumber)
	public WebElement CP_NewReservation_CardNumber;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_NameOnCard)
	public WebElement CP_NewReservation_NameOnCard;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_CardExpDate)
	public WebElement CP_NewReservation_CardExpDate;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_Swipe)
	public WebElement CP_NewReservation_Swipe;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_TravelAgent)
	public WebElement CP_NewReservation_TravelAgent;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_Market)
	public WebElement CP_NewReservation_Market;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_Referral)
	public WebElement CP_NewReservation_Referral;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_Book)
	public WebElement CP_NewReservation_Book;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_Quote)
	public WebElement CP_NewReservation_Quote;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_DepositPayPopUp)
	public WebElement CP_NewReservation_DepositPayPopUp;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_ConfirmationNumber)
	public WebElement CP_NewReservation_ConfirmationNumber;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_ReservationStatus)
	public WebElement CP_NewReservation_ReservationStatus;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_ClosePopUp)
	public WebElement CP_NewReservation_ClosePopUp;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_Folio)
	public WebElement CP_NewReservation_Folio;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_History)
	public WebElement CP_NewReservation_History;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_DetailsTab)
	public WebElement CP_NewReservation_DetailsTab;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_DocumentsTab)
	public WebElement CP_NewReservation_DocumentsTab;

	@FindBy(xpath = OR_Reservation.CP_GuestInfo_GuestName)
	public WebElement CP_GuestInfo_GuestName;

	@FindBy(xpath = OR_Reservation.CP_GuestInfo_ContactName)
	public WebElement CP_GuestInfo_ContactName;

	@FindBy(xpath = OR_Reservation.CP_GuestInfo_Email)
	public WebElement CP_GuestInfo_Email;

	@FindBy(xpath = OR_Reservation.CP_GuestInfo_Phone)
	public WebElement CP_GuestInfo_Phone;

	@FindBy(xpath = OR_Reservation.CP_GuestInfo_AlternatePhone)
	public WebElement CP_GuestInfo_AlternatePhone;

	@FindBy(xpath = OR_Reservation.CP_GuestInfo_Account)
	public WebElement CP_GuestInfo_Account;

	@FindBy(xpath = OR_Reservation.CP_GuestInfo_Country)
	public WebElement CP_GuestInfo_Country;

	@FindBy(xpath = OR_Reservation.CP_GuestInfo_PostalCode)
	public WebElement CP_GuestInfo_PostalCode;

	@FindBy(xpath = OR_Reservation.CP_GuestInfo_State)
	public WebElement CP_GuestInfo_State;

	@FindBy(xpath = OR_Reservation.CP_GuestInfo_City)
	public WebElement CP_GuestInfo_City;

	@FindBy(xpath = OR_Reservation.CP_GuestInfo_Address1)
	public WebElement CP_GuestInfo_Address1;

	@FindBy(xpath = OR_Reservation.CP_GuestInfo_Address2)
	public WebElement CP_GuestInfo_Address2;

	@FindBy(xpath = OR_Reservation.CP_GuestInfo_Address3)
	public WebElement CP_GuestInfo_Address3;

	@FindBy(xpath = OR_Reservation.CP_AddNotes)
	public WebElement CP_AddNotes;

	@FindBy(xpath = OR_Reservation.CP_AddTask)
	public WebElement CP_AddTask;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_AddRoom)
	public WebElement CP_NewReservation_AddRoom;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_SplitReservation)
	public WebElement CP_NewReservation_SplitReservation;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_AddMoreGuestInfo)
	public WebElement CP_NewReservation_AddMoreGuestInfo;

	@FindBy(xpath = OR_Reservation.CP_Reservation_Folio_Pay)
	public WebElement CP_Reservation_Folio_Pay;

	@FindBy(xpath = OR_Reservation.CP_Reservation_Folio_TakePayment)
	public WebElement CP_Reservation_Folio_TakePayment;

	@FindBy(xpath = OR_Reservation.CP_Reservation_Folio_TakePayment_Amount)
	public WebElement CP_Reservation_Folio_TakePayment_Amount;

	@FindBy(xpath = OR_Reservation.CP_Reservation_Folio_TakePayment_TypeButton)
	public WebElement CP_Reservation_Folio_TakePayment_TypeButton;

	@FindBy(xpath = OR_Reservation.CP_Reservation_Folio_TakePayment_PaymentButton)
	public WebElement CP_Reservation_Folio_TakePayment_PaymentButton;

	@FindBy(xpath = OR_Reservation.CP_Reservation_StatusPanel_DropDownBox)
	public WebElement CP_Reservation_StatusPanel_DropDownBox;

	@FindBy(xpath = OR_Reservation.CP_Reservation_DisplayVoidItemCheckBox)
	public WebElement CP_Reservation_DisplayVoidItemCheckBox;

	@FindBy(xpath = OR_Reservation.CP_Reservation_DisplayVoidItemFolioImage3)
	public WebElement CP_Reservation_DisplayVoidItemLineItemImage3;

	@FindBy(xpath = OR_Reservation.CP_Reservation_PanelGuestProfileName)
	public WebElement CP_Reservation_StatusPanel_GuestName;

	@FindBy(xpath = OR_Reservation.CP_Reservation_PanelNoShowStatus)
	public WebElement CP_Reservation_StatusPanel_Status;

	@FindBy(xpath = OR_Reservation.CP_Reservation_PanelTripTotal)
	public WebElement CP_Reservation_StatusPanel_TripTotal;

	@FindBy(xpath = OR_Reservation.CP_Reservation_PanelBalance)
	public WebElement CP_Reservation_StatusPanel_Balance;

	@FindBy(xpath = OR_Reservation.CP_Reservation_PanelReservation)
	public WebElement CP_Reservation_PanelReservation;

	@FindBy(xpath = OR_Reservation.CP_Reservation_POLICIESANDDISCLAIMERS_NoShowPolicyCollapse)
	public WebElement CP_Reservation_POLICIESANDDISCLAIMERS_NoShowPolicyCollapse;

	@FindBy(xpath = OR_Reservation.CP_Reservation_POLICIESANDDISCLAIMERS_NoShowPolicyExpand)
	public WebElement CP_Reservation_POLICIESANDDISCLAIMERS_NoShowPolicyExpand;

	@FindBy(xpath = OR_Reservation.CP_Reservation_POLICIESANDDISCLAIMERS_NoShowPolicyExpandNoPolicy)
	public WebElement CP_Reservation_POLICIESANDDISCLAIMERS_NoShowPolicyExpandNoPolicy;

	@FindBy(xpath = OR_Reservation.CP_Reservation_POLICIESANDDISCLAIMERS_NoShowPolicyLabel)
	public WebElement CP_Reservation_POLICIESANDDISCLAIMERS_NoShowPolicyLabel;

	@FindBy(xpath = OR_Reservation.CP_Reservation_HistoryTab_NoShowDesc)
	public WebElement CP_Reservation_HistoryTab_NoShow;

	@FindBy(xpath = OR_Reservation.CP_Reservation_HistoryTab_RollBackDesc)
	public WebElement CP_Reservation_HistoryTab_RollBackDesc;

	@FindBy(xpath = OR_Reservation.CP_Reservation_HistoryTab_MadeNoShowPayment)
	public List<WebElement> CP_Reservation_HistoryTab_MadeNoShow;

	@FindBy(xpath = OR_Reservation.CP_Reservation_RolleBackButton)
	public WebElement CP_Reservation_RollBackAllButton;

	@FindBy(xpath = OR_Reservation.CP_Reservation_POLICIESANDDISCLAIMERS_CancellationPolicyLabel)
	public WebElement CP_Reservation_POLICIESANDDISCLAIMERS_CancellationPolicyLabel;

	@FindBy(xpath = OR_Reservation.CP_Reservation_StayInfo_SecondaryGuestName)
	public WebElement CP_Reservation_StayInfo_SecondaryGuestName;

	@FindBy(xpath = OR_Reservation.CP_Reservation_FolioDetailDropDownBox)
	public WebElement CP_Reservation_FolioDetailDropDownBox;

	@FindBy(xpath = OR_Reservation.CP_Reservation_StayInfo_ThreeDotts)
	public List<WebElement> CP_Reservation_StayInfo_ThreeDotts;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckInAllButton)
	public WebElement CP_Reservation_CheckInAllButton;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckInButton)
	public WebElement CP_Reservation_CheckInButton;

	@FindBy(xpath = OR_Reservation.CP_Reservation_ReservationStatusCheckOutAllButton)
	public WebElement CP_Reservation_ReservationStatusCheckOutAllButton;

	@FindBy(xpath = OR_Reservation.CP_Reservation_ReservationStatusCheckOutButton)
	public WebElement CP_Reservation_ReservationStatusCheckOutButton;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckIn_GuestRegistrationReportToggle)
	public WebElement CP_Reservation_CheckIn_GuestRegistrationReportToggle;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckIn_GuestRegistrationReportToggleOff)
	public WebElement CP_Reservation_CheckIn_GuestRegistrationReportToggleOff;

	@FindBy(xpath = OR_Reservation.CP_Reservation_ConfirmCheckIn_Button)
	public WebElement CP_Reservation_ConfirmCheckIn_Button;

	@FindBy(xpath = OR_Reservation.confirmCheckInPaymentRoomClassName)
	public List<WebElement> confirmCheckInPaymentRoomClassName;

	@FindBy(xpath = OR_Reservation.CP_Reservation_ConfirmCheckOut_Button)
	public WebElement CP_Reservation_ConfirmCheckOut_Button;

	@FindBy(xpath = OR_Reservation.CP_Reservation_ConfirmCheckInPayment_Button)
	public WebElement CP_Reservation_ConfirmCheckInPayment_Button;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckIn_ModelPopMsg)
	public WebElement CP_Reservation_CheckIn_ModelPopMsg;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckIn_ModelPopCancelButton)
	public WebElement CP_Reservation_CheckIn_ModelPopCancelButton;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckIn_ModelPopConfirmButton)
	public WebElement CP_Reservation_CheckIn_ModelPopConfirmButton;

	@FindBy(xpath = OR_Reservation.CP_Reservation_GuestContactInforCollapse)
	public WebElement CP_Reservation_GuestContactInforCollapse;

	@FindBy(xpath = OR_Reservation.CP_Reservation_POLICIESANDDISCLAIMERS_CheckINPolicyCollapse)
	public WebElement CP_Reservation_POLICIESANDDISCLAIMERS_CheckInPolicyCollapse;

	@FindBy(xpath = OR_Reservation.CP_Reservation_POLICIESANDDISCLAIMERS_CheckInPolicyLabel)
	public WebElement CP_Reservation_POLICIESANDDISCLAIMERS_CheckInPolicyLabel;

	@FindBy(xpath = OR_Reservation.CP_OverrideCheckInAmountCheckBox)
	public WebElement CP_OverrideCheckInAmountCheckBox;

	@FindBy(xpath = OR_Reservation.CP_CheckInTypeOptions)
	public List<WebElement> CP_CheckInTypeOptions;

	@FindBy(xpath = OR_Reservation.CP_CheckInSuccessPrintButton)
	public WebElement CP_CheckInSuccessPrintButton;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckOutAllButton)
	public WebElement CP_Reservation_CheckOutAllButton;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckOutButton)
	public WebElement CP_Reservation_CheckOutButton;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckInOkButton)
	public WebElement CP_Reservation_CheckInOkButton;

	@FindBy(xpath = OR_Reservation.CancelReservation_Reason)
	public WebElement CancelReservation_Reason;

	@FindBy(xpath = OR_Reservation.CancelReservation_validationMsg)
	public WebElement CancelReservation_ValidationMsg;

	@FindBy(xpath = OR_Reservation.NoShow_Cancel_CheckIn_CheckOut_Title)
	public WebElement CP_Reservation_NoShow_Cancel_CheckIn_CheckOut_Title;

	@FindBy(xpath = OR_Reservation.NoShow_Cancel_CheckIn_CheckOut_GuestName)
	public WebElement CP_Reservation_NoShow_Cancel_CheckIn_CheckOut_GuestName;

	@FindBy(xpath = OR_Reservation.NoShow_Cancel_CheckIn_CheckOut_ReservationNO)
	public WebElement CP_Reservation_NoShow_Cancel_CheckIn_CheckOut_ConfirmationNo;

	@FindBy(xpath = OR_Reservation.NoShow_Cancel_CheckIn_CheckOut_Status)
	public WebElement CP_Reservation_NoShow_Cancel_CheckIn_CheckOut_Status;

	@FindBy(xpath = OR_Reservation.VoidRoomChargesCheckBoxLabel)
	public WebElement VoidRoomChargeLabel;

	@FindBy(xpath = OR_Reservation.VoidRoomChargesCheckBox)
	public WebElement CP_Reservation_NoShow_VoidRoomChargeCheckBox;

	@FindBy(xpath = OR_Reservation.ConfirmedNoShowButton)
	public WebElement CP_Reservation_ConfirmButton;

	@FindBy(xpath = OR_Reservation.ConfirmedNoShowButton)
	public WebElement CP_Reservation_NoShow_ConfirmNoShowButton;

	@FindBy(xpath = OR_Reservation.ProceedToPaymentButton)
	public WebElement ProceedToPaymentButton;

	@FindBy(xpath = OR_Reservation.ConfirmDialog_Message)
	public WebElement CP_Reservation_NoShow_ProceedToNoShowWithoutRefund;

	@FindBy(xpath = OR_Reservation.ConfirmDialog_NoButton)
	public WebElement CP_Reservation_NoShow_CancelledNoShowProcessButton;

	@FindBy(xpath = OR_Reservation.ConfirmDialog_YesButton)
	public WebElement CP_Reservation_NoShow_YesConfirmedNoShowProcessButton;

	@FindBy(xpath = OR_Reservation.NoShowPaymentPopupTitle)
	public WebElement CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupTitle;

	@FindBy(xpath = OR_Reservation.NoShowPaymentPopup_Type)
	public WebElement CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType;

	@FindBy(xpath = OR_Reservation.NoShowPaymentPopup_Amount)
	public WebElement CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount;

	@FindBy(xpath = OR_Reservation.NoShowCancelPaymentPopup_Balance)
	public WebElement NoShowCancelPaymentPopup_Balance;

	@FindBy(xpath = OR_Reservation.CheckInPaymentPopup_Balance)
	public WebElement CheckInPaymentPopup_Balance;

	@FindBy(xpath = OR_Reservation.cancelPaymentPopupBalance)
	public WebElement cancelPaymentPopupBalance;

	@FindBy(xpath = OR_Reservation.NoShowPaymentPopup_CardNumber)
	public WebElement CP_Reservation_NoShowPaymentPopup_CardNumber;

	@FindBy(xpath = OR_Reservation.NoShowPaymentPopup_NameOnCard)
	public WebElement CP_Reservation_NoShowPaymentPopup_NameOnCard;

	@FindBy(xpath = OR_Reservation.NoShowPaymentPopup_Expiry)
	public WebElement CP_Reservation_NoShowPaymentPopup_Expiry;

	@FindBy(xpath = OR_Reservation.NoShowPaymentPopup_CVVCode)
	public WebElement CP_Reservation_NoShowPaymentPopup_CVVCode;

	@FindBy(xpath = OR_Reservation.NoShowPaymentPopup_ExternalPaymentCheckBox)
	public WebElement CP_Reservation_NoShowPaymentPopup_ExternalPaymentCheckBox;

	@FindBy(xpath = OR_Reservation.NoShowPaymentPopup_SetMainPaymentCheckBox)
	public WebElement CP_Reservation_NoShowPaymentPopup_SetMainPaymentCheckBox;

	@FindBy(xpath = OR_Reservation.NoShowPaymentPopup_Date)
	public WebElement NoShowPaymentPopup_Date;

	@FindBy(xpath = OR_Reservation.NoShowPaymentPopup_Date_CalanderIcon)
	public WebElement NoShow_Cancel_Checkout_Date_CalanderIcon;

	@FindBy(xpath = OR_Reservation.CheckOutPaymentPopup_Date_CalanderIcon)
	public WebElement CheckOutPaymentPopup_Date_CalanderIcon;

	@FindBy(xpath = OR_Reservation.NoShowPaymentPopup_SetMainPaymentLabel)
	public WebElement CP_Reservation_NoShowPaymentPopup_MainPaymentMethod;

	@FindBy(xpath = OR_Reservation.NoShowPaymentPopup_LogANDPayButton)
	public WebElement CP_Reservation_NoShowPaymentPopup_LogAndPayButton;

	@FindBy(xpath = OR_Reservation.NoShowPaymentPopup_CloseButton)
	public WebElement CP_Reservation_NoShowPaymentPopup_CloseButton;

	@FindBy(xpath = OR_Reservation.NoShowPaymentPopup_Msg)
	public WebElement CP_Reservation_NoShowPaymentPopup_CloseButtonMsg;

	@FindBy(xpath = OR_Reservation.NoShowPaymentPopup_YesButton)
	public WebElement CP_Reservation_NoShowPaymentPopup_CloseButtonMsgYesButton;

	@FindBy(xpath = OR_Reservation.NoShowPaymentPopup_NoButton)
	public WebElement CP_Reservation_NoShowPaymentPopup_CloseButtonMsgNoButton;

	@FindBy(xpath = OR_Reservation.ConfirmDialog_Message)
	public WebElement CP_Reservation_ConfirmNoShowMsg;

	@FindBy(xpath = OR_Reservation.ConfirmDialog_YesButton)
	public WebElement CP_Reservation_ConfirmNoShowYesButton;

	@FindBy(xpath = OR_Reservation.ConfirmDialog_NoButton)
	public WebElement CP_Reservation_ConfirmNoShowNoButton;

	@FindBy(xpath = OR_Reservation.NoShowSuccess_ModelHeader)
	public WebElement CP_Reservation_NoShowSuccess;

	@FindBy(xpath = OR_Reservation.NoShowSuccess_Date)
	public WebElement NoShowSuccess_Date;

	@FindBy(xpath = OR_Reservation.NoShowSuccess_Balance)
	public WebElement NoShowSuccess_Balance;

	@FindBy(xpath = OR_Reservation.noShowSuccessPolicyAmount)
	public WebElement noShowSuccessPolicyAmount;

	@FindBy(xpath = OR_Reservation.NoShowSuccess_Type)
	public WebElement NoShowSuccess_Type;

	@FindBy(xpath = OR_Reservation.NoShowSuccess_PaymentMethod)
	public WebElement CP_Reservation_NoShowSuccessPaymentMethod;

	@FindBy(xpath = OR_Reservation.NoShowSuccess_PaymentToolTip)
	public WebElement CP_Reservation_NoShowSuccessPaymentToolTip;

	@FindBy(xpath = OR_Reservation.NoShowSuccess_Status)
	public WebElement CP_Reservation_NoShowSuccessStatus;

	@FindBy(xpath = OR_Reservation.NoShowSuccess_Notes)
	public WebElement CP_Reservation_NoShowSuccessNotes;

	@FindBy(xpath = OR_Reservation.NoShowSuccess_CloseButton)
	public WebElement CP_Reservation_NoShowSuccessCloseButton;

	@FindBy(xpath = OR_Reservation.checkInPaymentPopupPolicyName)
	public WebElement checkInPaymentPopupPolicyName;

	@FindBy(xpath = OR_Reservation.NoShowPaymentAddNoteLink)
	public WebElement CP_Reservation_NoShowPayment_AddNoteLink;

	@FindBy(xpath = OR_Reservation.NoShowPaymentAddNoteTextBox)
	public WebElement CP_Reservation_NoShowPayment_AddNoteTextBox;

	@FindBy(xpath = OR_Reservation.noShowSuccessPolicyName)
	public WebElement noShowSuccessPolicyName;

	@FindBy(xpath = OR_Reservation.NoShowSuccessFul_ModelHeader)
	public WebElement NoShowSuccessfull_Header;

	@FindBy(xpath = OR_Reservation.NoShowSuccessFul_Msg)
	public WebElement NoShowSuccessfull_Msg;

	@FindBy(xpath = OR_Reservation.NoShowSuccessFul_Reg)
	public WebElement NoShowSuccessfull_Reg;

	@FindBy(xpath = OR_Reservation.NoShowSuccessFul_CloseButton)
	public WebElement NoShowSuccessfull_Button;

	@FindBy(xpath = OR_Reservation.NoShowAndCancelReservation_PolicyName)
	public WebElement NoShowAndCancelReservation_PolicyName;

	@FindBy(xpath = OR_Reservation.CheckInReservation_PolicyName)
	public WebElement CheckInReservation_PolicyName;

	@FindBy(xpath = OR_Reservation.Reservation_PostCheckBoxLabel)
	public WebElement ReservationPopUp_PostLabel;

	@FindBy(xpath = OR_Reservation.CancelRoom_ToolTip_PolicyName)
	public WebElement CancelRoom_ToolTip_PolicyName;

	@FindBy(xpath = OR_Reservation.CancelRoom_ToolTip_PolicyDescription)
	public WebElement CancelRoom_ToolTip_PolicyDescription;

	@FindBy(xpath = OR_Reservation.CheckInPaymentPopup_Date_CalanderIcon)
	public WebElement CheckInPaymentPopup_Date_CalanderIcon;

	@FindBy(xpath = OR_Reservation.CP_Reservation_StayInfo_CheckInButton)
	public List<WebElement> CP_Reservation_StayInfo_CheckInButton;

	@FindBy(xpath = OR_Reservation.CP_Reservation_StayInfo_CheckOutButton)
	public List<WebElement> CP_Reservation_StayInfo_CheckOutButtonList;

	@FindBy(xpath = OR_Reservation.CP_Reservation_StayInfo_CheckOutButton)
	public WebElement CP_Reservation_StayInfo_CheckOutButton;

	@FindBy(xpath = OR_Reservation.CP_Reservation_StayInfo_TotalButton)
	public List<WebElement> CP_Reservation_StayInfo_TotalButton;

	@FindBy(xpath = OR_Reservation.Cp_Reservation_PaymentDetail_CanceButton)
	public WebElement Cp_Reservation_PaymentDetail_CanceButton;

	@FindBy(xpath = OR_Reservation.Cp_Reservation_PaymentDetail_CloseButton)
	public WebElement Cp_Reservation_PaymentDetail_CloseButton;

	@FindBy(xpath = OR_Reservation.Add_LineItem)
	public WebElement Add_LineItem;

	@FindBy(xpath = OR_Reservation.Select_LineItem_Category)
	public WebElement Select_LineItem_Category;

	@FindBy(xpath = OR_Reservation.Enter_LineItem_Amount)
	public WebElement Enter_LineItem_Amount;

	@FindBy(xpath = OR_Reservation.Commit_LineItem)
	public WebElement Commit_LineItem;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckOutAllButton_ConfirmationMsg)
	public WebElement CP_Reservation_CheckOutAllButton_ConfirmationMsg;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckOutAllButton_ConfirmationMsgYesButton)
	public WebElement CP_Reservation_CheckOutAllButton_ConfirmationMsgYesButton;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckOutAllButton_ConfirmationMsgNoButton)
	public WebElement CP_Reservation_CheckOutAllButton_ConfirmationMsgNoButton;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckOut_Message)
	public WebElement CP_Reservation_CheckOut_Message;

	@FindBy(xpath = OR_Reservation.checkInPolicyName)
	public WebElement checkInPolicyName;

	@FindBy(xpath = OR_Reservation.checkInPolicyToolTip)
	public WebElement checkInPolicyToolTip;

	@FindBy(xpath = OR_Reservation.tripTotalAmountAtTop)
	public WebElement tripTotalAmountAtTop;

	@FindBy(xpath = OR_Reservation.tripBalanceAmountAtTop)
	public WebElement tripBalanceAmountAtTop;

	@FindBy(xpath = OR_Reservation.checkInPaymntPopupAmount)
	public WebElement checkInPaymntPopupAmount;

	@FindBy(xpath = OR_Reservation.checkInPaymntPopupType)
	public WebElement checkInPaymntPopupType;

	@FindBy(xpath = OR_Reservation.checkInPaymntPopupBalance)
	public WebElement checkInPaymntPopupBalance;

	@FindBy(xpath = OR_Reservation.folioHistoryAuthPaymentDesc)
	public WebElement folioHistoryAuthPaymentDesc;

	@FindBy(xpath = OR_Reservation.folioHistoryCapturePaymentDesc)
	public WebElement folioHistoryCapturePaymentDesc;

	@FindBy(xpath = OR_Reservation.roomNoDDAtCheckInPopup)
	public WebElement roomNoDDAtCheckInPopup;

	@FindBy(xpath = OR_Reservation.roomNoDDAtCheckInPopup)
	public List<WebElement> roomsDDAtCheckInPopup;

	@FindBy(xpath = OR_Reservation.folioHistoryAuthPaymentCategory)
	public WebElement folioHistoryAuthPaymentCategory;

	@FindBy(xpath = OR_Reservation.folioHistoryCapturePaymentCategory)
	public WebElement folioHistoryCapturePaymentCategory;

	@FindBy(xpath = OR_Reservation.checkInPolicyText)
	public WebElement checkInPolicyText;

	@FindBy(xpath = OR_Reservation.paymentCheckInPopupAuthTypeReadOnly)
	public WebElement paymentCheckInPopupAuthTypeReadOnly;

	@FindBy(xpath = OR_Reservation.paymentCheckInPopupAuthTypeEditable)
	public WebElement paymentCheckInPopupAuthTypeEditable;

	@FindBy(xpath = OR_Reservation.folioHistoryTabSearchBox)
	public WebElement folioHistoryTabSearchBox;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckINUassigned)
	public List<WebElement> CP_Reservation_CheckINUassigned;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckINUassignedTosterMsg)
	public WebElement CP_Reservation_CheckINUassignedTosterMsg;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckDropDownOpenRooms)
	public List<WebElement> CP_Reservation_CheckDropDownOpenRooms;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckInDropDownBox)
	public List<WebElement> CP_Reservation_CheckInDropDownBox;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckInFirstDropDownPath)
	public WebElement CP_Reservation_CheckInFirstDropDownPath;

	@FindBy(xpath = OR_Reservation.EnterCheckinDate)
	public WebElement EnterCheckinDate;

	@FindBy(xpath = OR_Reservation.EnterCheckout)
	public WebElement EnterCheckout;

	@FindBy(xpath = OR_Reservation.CheckboxGuestProfile)
	public WebElement CheckboxGuestProfile;

	@FindBy(xpath = OR_Reservation.CheckInPopupHeading)
	public WebElement CheckInPopupHeading;

	@FindBy(xpath = OR_Reservation.CheckinConfirmButton)
	public WebElement CheckinConfirmButton;

	@FindBy(xpath = OR_Reservation.VerifyDirtyRoomPopup)
	public WebElement VerifyDirtyRoomPopup;

	@FindBy(xpath = OR_Reservation.ButtonDirtyConfirm)
	public WebElement ButtonDirtyConfirm;

	@FindBy(xpath = OR_Reservation.ReservationStatus)
	public WebElement ReservationStatus;

	@FindBy(xpath = OR_Reservation.ButtonAddIncidental)
	public WebElement ButtonAddIncidental;

	@FindBy(xpath = OR_Reservation.EnterAddOn_IncidenalDate)
	public WebElement EnterAddOn_IncidenalDate;

	@FindBy(xpath = OR_Reservation.ButtonSelectIncidental)
	public WebElement ButtonSelectIncidental;

	@FindBy(xpath = OR_Reservation.EnterPerUnit)
	public WebElement EnterPerUnit;

	@FindBy(xpath = OR_Reservation.EnterQuentity)
	public WebElement EnterQuentity;

	@FindBy(xpath = OR_Reservation.GetIncidentalDate)
	public List<WebElement> GetIncidentalDate;

	@FindBy(xpath = OR_Reservation.GetIncidentalCategory)
	public List<WebElement> GetIncidentalCategory;

	@FindBy(xpath = OR_Reservation.GetIncidentalDescription)
	public List<WebElement> GetIncidentalDescription;

	@FindBy(xpath = OR_Reservation.GetIncidentalPerUnit)
	public List<WebElement> GetIncidentalPerUnit;

	@FindBy(xpath = OR_Reservation.GetIncidentalQuentity)
	public List<WebElement> GetIncidentalQuentity;

	@FindBy(xpath = OR_Reservation.GetIncidentalTax)
	public List<WebElement> GetIncidentalTax;

	@FindBy(xpath = OR_Reservation.GetIncidentalTotalAmount)
	public List<WebElement> GetIncidentalTotalAmount;

	@FindBy(xpath = OR_Reservation.IncidentalSave)
	public WebElement IncidentalSave;

	@FindBy(className = OR.Toaster_Title)
	public WebElement Toaster_Title;

	@FindBy(className = OR.Toaster_Message)
	public WebElement Toaster_Message;

	@FindBy(xpath = OR_Reservation.HistoryTab)
	public WebElement HistoryTab;

	@FindBy(xpath = OR_Reservation.History_Category)
	public List<WebElement> History_Category;

	@FindBy(xpath = OR_Reservation.History_Date)
	public List<WebElement> History_Date;

	@FindBy(xpath = OR_Reservation.History_Time)
	public List<WebElement> History_Time;

	@FindBy(xpath = OR_Reservation.History_User)
	public List<WebElement> History_User;

	@FindBy(xpath = OR_Reservation.History_Description)
	public List<WebElement> History_Description;

	@FindBy(xpath = OR_Reservation.DetailsTab)
	public WebElement DetailsTab;

	@FindBy(xpath = OR_Reservation.TotalNumberofIncidentalRows)
	public List<WebElement> TotalNumberofIncidentalRows;

	@FindBy(xpath = OR_Reservation.DeleteIncidentalInSatyInfoButton)
	public List<WebElement> DeleteIncidentalInSatyInfoButton;

	@FindBy(xpath = OR_Reservation.VoidPopupHeading)
	public WebElement VoidPopupHeading;

	@FindBy(xpath = OR_Reservation.EnterNotes)
	public WebElement EnterNotes;

	@FindBy(xpath = OR_Reservation.VoidButton)
	public List<WebElement> VoidButton;

	@FindBy(xpath = OR_Reservation.StayInfo_Editbutton)
	public WebElement StayInfo_Editbutton;

	@FindBy(xpath = OR_Reservation.StayInfo_ChangeDetails)
	public WebElement StayInfo_ChangeDetails;

	@FindBy(xpath = OR_Reservation.Checkout_CompleteCheckOutMsg)
	public WebElement Checkout_CompleteCheckOutMsg;

	@FindBy(xpath = OR_Reservation.Checkout_CompleteCheckOutMsgYesButton)
	public WebElement Checkout_CompleteCheckOutMsgYesButton;

	@FindBy(xpath = OR_Reservation.Checkout_CompleteCheckOutMsgNoButton)
	public WebElement Checkout_CompleteCheckOutMsgNoButton;

	@FindBy(xpath = OR_Reservation.EnterManualRate)
	public WebElement EnterManualRate;

	@FindBy(xpath = OR_Reservation.ClickGoBackToStayInfo)
	public WebElement ClickGoBackToStayInfo;

	@FindBy(xpath = OR_Reservation.NewPolicesApplicableYesBtn)
	public WebElement NewPolicesApplicableYesBtn;

	@FindBy(xpath = OR_Reservation.PrimaryGuestFirstName)
	public WebElement PrimaryGuestFirstName;

	@FindBy(xpath = OR_Reservation.PrimaryGuestLastName)
	public WebElement PrimaryGuestLastName;

	@FindBy(xpath = OR_Reservation.AdditonalGuest1FirstName)
	public WebElement AdditonalGuest1FirstName;

	@FindBy(xpath = OR_Reservation.AdditonalGuest1LastName)
	public WebElement AdditonalGuest1LastName;

	@FindBy(xpath = OR_Reservation.HeaderAccountName_AfterReservation)
	public WebElement HeaderAccountName_AfterReservation;

	@FindBy(xpath = OR_Reservation.HeaderAccountName_BeforeReservation)
	public WebElement HeaderAccountName_BeforeReservation;

	@FindBy(xpath = OR_Reservation.GuestName_GuestInfo)
	public WebElement GuestName_GuestInfo;

	@FindBy(xpath = OR_Reservation.ContactName_GuestInfo)
	public WebElement ContactName_GuestInfo;

	@FindBy(xpath = OR_Reservation.AddressLine1_GuestInfo)
	public WebElement AddressLine1_GuestInfo;

	@FindBy(xpath = OR_Reservation.AddressLine2_GuestInfo)
	public WebElement AddressLine2_GuestInfo;

	@FindBy(xpath = OR_Reservation.AddressLine3_GuestInfo)
	public WebElement AddressLine3_GuestInfo;

	@FindBy(xpath = OR_Reservation.City_GuestInfo)
	public WebElement City_GuestInfo;

	@FindBy(xpath = OR_Reservation.Contry_GuestInfo)
	public WebElement Contry_GuestInfo;

	@FindBy(xpath = OR_Reservation.State_GuestInfo)
	public WebElement State_GuestInfo;

	@FindBy(xpath = OR_Reservation.PostalCode_GuestInfo)
	public WebElement PostalCode_GuestInfo;

	@FindBy(xpath = OR_Reservation.Email_GuestInfo)
	public WebElement Email_GuestInfo;

	@FindBy(xpath = OR_Reservation.PhoneNo_GuestInfo)
	public WebElement PhoneNo_GuestInfo;

	@FindBy(xpath = OR_Reservation.PaymentMethod_PayInfo)
	public WebElement PaymentMethod_PayInfo;

	@FindBy(xpath = OR_Reservation.AlternativePhoneNo_GuestInfo)
	public WebElement AlternativePhoneNo_GuestInfo;

	@FindBy(xpath = OR_Reservation.MarketSegment_GuestInfo)
	public WebElement MarketSegment_GuestInfo;

	@FindBy(xpath = OR_Reservation.Referral_GuestInfo)
	public WebElement Referral_GuestInfo;

	@FindBy(xpath = OR_Reservation.CardNo_PayInfo)
	public WebElement CardNo_PayInfo;

	@FindBy(xpath = OR_Reservation.NameOnCard_PayInfo)
	public WebElement NameOnCard_PayInfo;

	@FindBy(xpath = OR_Reservation.CardExpiry_PayInfo)
	public WebElement CardExpiry_PayInfo;

	@FindBy(xpath = OR_Reservation.CP_Reservation_Open_Reservation)
	public WebElement CP_Reservation_Open_Reservation;

	@FindBy(xpath = OR_Reservation.CP_ClickStayInfoEdit)
	public List<WebElement> CP_ClickStayInfoEdit;

	@FindBy(xpath = OR_Reservation.CP_ClickAssignRoomNumber)
	public List<WebElement> CP_ClickAssignRoomNumber;

	@FindBy(xpath = OR_Reservation.CP_SelectResRoom)
	public List<WebElement> CP_SelectResRoom;

	@FindBy(xpath = OR_Reservation.CP_SaveRoomNo)
	public WebElement CP_SaveRoomNo;

	@FindBy(xpath = OR_Reservation.CP_VerifyChangeCostPopup)
	public WebElement CP_VerifyChangeCostPopup;

	@FindBy(xpath = OR_Reservation.CP_VerifyFolioDropDownItem)
	public List<WebElement> CP_VerifyFolioDropDownItem;

	@FindBy(xpath = OR_Reservation.CP_VerifyPolicyChangePopup)
	public WebElement CP_VerifyPolicyChangePopup;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_BillingPostalCode)
	public WebElement CP_NewReservation_BillingPostalCode;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_BillingCity)
	public WebElement CP_NewReservation_BillingCity;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_BillingCountry)
	public WebElement CP_NewReservation_BillingCountry;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_BillingState)
	public WebElement CP_NewReservation_BillingState;

	@FindBy(xpath = OR_Reservation.CP_Reservation_PaymentMethod_Section)
	public WebElement CP_Reservation_PaymentMethod_Section;

	@FindBy(xpath = OR_Reservation.CP_Reservation_PaymentMethod)
	public WebElement CP_Reservation_PaymentMethod;

	@FindBy(xpath = OR_Reservation.CP_RESERVATION_CC_Number)
	public WebElement CP_RESERVATION_CC_Number;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CardHolder_Name)
	public WebElement CP_Reservation_CardHolder_Name;

	@FindBy(xpath = OR_Reservation.CP_Reservation_Card_ExpiryDate)
	public WebElement CP_Reservation_Card_ExpiryDate;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_BillingAddress1)
	public WebElement CP_NewReservation_BillingAddress1;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_BillingAddress2)
	public WebElement CP_NewReservation_BillingAddress2;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_BillingAddress3)
	public WebElement CP_NewReservation_BillingAddress3;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_BillingSalutation)
	public WebElement CP_NewReservation_BillingSalutation;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_BillingFirstName)
	public WebElement CP_NewReservation_BillingFirstName;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_BillingLastName)
	public WebElement CP_NewReservation_BillingLastName;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_SameAsMailingAddressCheckBox)
	public WebElement CP_NewReservation_SameAsMailingAddressCheckBox;

	@FindBy(xpath = OR_Reservation.COPY_BUTTON)
	public WebElement COPY_BUTTON;

	@FindBy(xpath = OR_Reservation.COPY_RESERVATION_TRIMNAME)
	public WebElement COPY_RESERVATION_TRIMNAME;

	@FindBy(xpath = OR_Reservation.COPY_CP_GuestInfo_GuestProfile)
	public WebElement COPY_CP_GuestInfo_GuestProfile;

	@FindBy(xpath = OR_Reservation.COPY_CP_GuestInfo_Guest_Salutation)
	public WebElement COPY_CP_GuestInfo_Guest_Salutation;

	@FindBy(xpath = OR_Reservation.COPY_CP_GuestInfo_FirstName)
	public WebElement COPY_CP_GuestInfo_FirstName;

	@FindBy(xpath = OR_Reservation.COPY_CP_GuestInfo_LastName)
	public WebElement COPY_CP_GuestInfo_LastName;

	@FindBy(xpath = OR_Reservation.COPY_CP_ContactInfo_Salutation)
	public WebElement COPY_CP_ContactInfo_Salutation;

	@FindBy(xpath = OR_Reservation.COPY_CP_ContactInfo_FirstName)
	public WebElement COPY_CP_ContactInfo_FirstName;

	@FindBy(xpath = OR_Reservation.COPY_CP_ContactInfo_LastName)
	public WebElement COPY_CP_ContactInfo_LastName;

	@FindBy(xpath = OR_Reservation.COPY_CP_ContactInfo_Email)
	public WebElement COPY_CP_ContactInfo_Email;

	@FindBy(xpath = OR_Reservation.COPY_CP_ContactInfo_Phone)
	public WebElement COPY_CP_ContactInfo_Phone;

	@FindBy(xpath = OR_Reservation.COPY_CP_ContactInfo_AlternativePhone)
	public WebElement COPY_CP_ContactInfo_AlternativePhone;

	@FindBy(xpath = OR_Reservation.COPY_CP_MailingInfo_Address1)
	public WebElement COPY_CP_MailingInfo_Address1;

	@FindBy(xpath = OR_Reservation.COPY_CP_MailingInfo_Address2)
	public WebElement COPY_CP_MailingInfo_Address2;

	@FindBy(xpath = OR_Reservation.COPY_CP_MailingInfo_Address3)
	public WebElement COPY_CP_MailingInfo_Address3;

	@FindBy(xpath = OR_Reservation.COPY_CP_MailingInfo_City)
	public WebElement COPY_CP_MailingInfo_City;

	@FindBy(xpath = OR_Reservation.COPY_CP_MailingInfo_Country)
	public WebElement COPY_CP_MailingInfo_Country;

	@FindBy(xpath = OR_Reservation.COPY_CP_MailingInfo_State)
	public WebElement COPY_CP_MailingInfo_State;

	@FindBy(xpath = OR_Reservation.COPY_CP_MailingInfo_PostalCode)
	public WebElement COPY_CP_MailingInfo_PostalCode;

	@FindBy(xpath = OR_Reservation.COPY_CP_BillingInfo_PaymentMethod)
	public WebElement COPY_CP_BillingInfo_PaymentMethod;

	@FindBy(xpath = OR_Reservation.COPY_CP_BillingInfo_CreditCardNumber)
	public WebElement COPY_CP_BillingInfo_CreditCardNumber;

	@FindBy(xpath = OR_Reservation.COPY_CP_BillingInfo_NameOnCard)
	public WebElement COPY_CP_BillingInfo_NameOnCard;

	@FindBy(xpath = OR_Reservation.COPY_CP_BillingInfo_ExpDate)
	public WebElement COPY_CP_BillingInfo_ExpDate;

	@FindBy(xpath = OR_Reservation.COPY_CP_RoomClassName)
	public WebElement COPY_CP_RoomClassName;

	@FindBy(xpath = OR_Reservation.CancelReservation_CofirmCancellation)
	public WebElement CancelReservation_CofirmCancellation;

	@FindBy(xpath = OR_Reservation.COPY_CP_NewReservation_Folio)
	public WebElement COPY_CP_NewReservation_Folio;

	@FindBy(xpath = OR_Reservation.COPY_CP_NewReservation_TaskNotCopy)
	public WebElement COPY_CP_NewReservation_TaskNotCopy;

	@FindBy(xpath = OR_Reservation.HeaderGuestName_AfterReservation)
	public WebElement HeaderGuestName_AfterReservation;

	@FindBy(xpath = OR_Reservation.HeaderStatus_AfterReservation)
	public WebElement HeaderStatus_AfterReservation;

	@FindBy(xpath = OR_Reservation.HeaderConfirmationNo)
	public WebElement HeaderConfirmationNo;

	@FindBy(xpath = OR_Reservation.HeaderStayDate)
	public WebElement HeaderStayDate;

	@FindBy(xpath = OR_Reservation.TripSummary_RoomCharge)
	public WebElement TripSummary_RoomCharge;

	@FindBy(xpath = OR_Reservation.RollBackPopupMsg)
	public WebElement RollBackMsg;

	@FindBy(xpath = OR_Reservation.RollBacktPopupYesButton)
	public WebElement RollBacktPopupYesButton;

	@FindBy(xpath = OR_Reservation.RollBackPopupNoButton)
	public WebElement RollBackPopupNoButton;

	@FindBy(xpath = OR_Reservation.RollBackPopupCloseButton)
	public WebElement RollBackPopupCloseButton;

	@FindBy(xpath = OR_Reservation.Spinner)
	public WebElement Spinner;

	@FindBy(xpath = OR_Reservation.Folio_RoomCharges)
	public WebElement Folio_RoomCharges;

	@FindBy(xpath = OR_Reservation.Folio_TaxServices)
	public WebElement Folio_TaxServices;

	@FindBy(xpath = OR_Reservation.TripSummary_Incidentals)
	public WebElement TripSummary_Incidentals;

	@FindBy(xpath = OR_Reservation.TripSummary_TaxesAndServiceCharges)
	public WebElement TripSummary_TaxesAndServiceCharges;

	@FindBy(xpath = OR_Reservation.TripSummary_TripTotal)
	public WebElement TripSummary_TripTotal;

	@FindBy(xpath = OR_Reservation.TripSummary_Payment)
	public WebElement TripSummary_Payment;

	@FindBy(xpath = OR_Reservation.TripSummary_Balance)
	public WebElement TripSummary_Balance;

	@FindBy(xpath = OR_Reservation.cancelPolicyCollapseInDetailsTab)
	public WebElement cancelPolicyCollapseInDetailsTab;

	@FindBy(xpath = OR_Reservation.cancelPolicyNameInDetailsTab)
	public WebElement cancelPolicyNameInDetailsTab;

	@FindBy(xpath = OR_Reservation.cancelPolicyTextInDetailsTab)
	public WebElement cancelPolicyTextInDetailsTab;

	@FindBy(xpath = OR_Reservation.cancelResPopupVoidRoomChargesCheckBox)
	public WebElement cancelResPopupVoidRoomChargesCheckBox;

	@FindBy(xpath = OR_Reservation.cancelResPopupVoidRoomChargesCheckBoxClick)
	public WebElement cancelResPopupVoidRoomChargesCheckBoxClick;

	@FindBy(xpath = OR_Reservation.cancelReasonNoteType)
	public WebElement cancelReasonNoteType;

	@FindBy(xpath = OR_Reservation.noShowNotesType)
	public WebElement noShowNotesType;

	@FindBy(xpath = OR_Reservation.notesTabHeader)
	public WebElement notesTabHeader;

	@FindBy(xpath = OR_Reservation.cancelResPopupPolicyName)
	public WebElement cancelResPopupPolicyName;

	@FindBy(xpath = OR_Reservation.cancelResPopupPolicyToolTipName)
	public WebElement cancelResPopupPolicyToolTipName;

	@FindBy(xpath = OR_Reservation.cancelResPopupPolicyToolTipText)
	public WebElement cancelResPopupPolicyToolTipText;

	@FindBy(xpath = OR_Reservation.SaveQuote)
	public WebElement saveQuote;

	@FindBy(xpath = OR_Reservation.getTripTotalInTripSummary)
	public WebElement getTripTotalInTripSummary;

	@FindBy(xpath = OR_Reservation.getRoomChargesInTripSummary)
	public WebElement getRoomChargesInTripSummary;

	@FindBy(xpath = OR_Reservation.getIncidentalsInTripSummary)
	public WebElement getIncidentalsInTripSummary;

	@FindBy(xpath = OR_Reservation.getTaxandServicesInTripSummary)
	public WebElement getTaxandServicesInTripSummary;

	@FindBy(xpath = OR_Reservation.getTotalBalanceInTripSummary)
	public WebElement getTotalBalanceInTripSummary;

	@FindBy(xpath = OR_Reservation.getPaidInTripSummary)
	public WebElement getPaidInTripSummary;

	@FindBy(xpath = OR_Reservation.ClickOnCheckBoxProperty)
	public WebElement clickOnCheckBoxProperty;

	@FindBy(xpath = OR_Reservation.ResBulkCanUpdatedTab)
	public WebElement ResBulkCanUpdatedTab;

	@FindBy(xpath = OR_Reservation.ResBulkCanNotUpdatedTab)
	public WebElement ResBulkCanNotUpdatedTab;

	@FindBy(xpath = OR_Reservation.ClickProcessButtonBulkCheckIN)
	public WebElement ClickProcessButtonBulkCheckIN;

	@FindBy(xpath = OR_Reservation.Verify_resNumber1BulkCheckInCanNotUpdate)
	public WebElement Verify_resNumber1BulkCheckInCanNotUpdate;

	@FindBy(xpath = OR_Reservation.ClickResCanUpdateAfterProcess)
	public WebElement clickResCanUpdateAfterProcess;

	@FindBy(xpath = OR_Reservation.ClickResCanNotUpdateAfterProcess)
	public WebElement clickResCanNotUpdateAfterProcess;

	@FindBy(xpath = OR_Reservation.VerifyBulkCheckInCompletePopUp)
	public WebElement verifyBulkCheckInCompletePopUp;

	@FindBy(xpath = OR_Reservation.CLOSE_BULK_ACTION_POPUP)
	public WebElement CloseBulkActionPopUp;

	@FindBy(xpath = OR_Reservation.ClickProcessButtonBulkCheckOut)
	public WebElement clickProcessButtonBulkCheckOut;

	@FindBy(xpath = OR_Reservation.VerifyBulkCheckOutCompletePopUp)
	public WebElement verifyBulkCheckOutCompletePopUp;

	@FindBy(xpath = OR_Reservation.EnterCheckinDate)
	public List<WebElement> listEnterCheckinDate;

	@FindBy(xpath = OR_Reservation.EnterCheckout)
	public List<WebElement> listEnterCheckout;

	@FindBy(xpath = OR_Reservation.selectRoomNumberInMRB)
	public List<WebElement> SelectRoomNumberInMRB;

	@FindBy(xpath = OR_Reservation.checkboxCopyGuest)
	public WebElement CheckboxCopyGuest;

	@FindBy(xpath = OR_Reservation.ADDONSRoomButton)
	public WebElement ADDONSRoomButton;

	@FindBy(xpath = OR_Reservation.ADDONSSelectedRoom)
	public WebElement ADDONSSelectedRoom;

	@FindBy(xpath = OR_Reservation.GetIncidentalRoomNumber)
	public List<WebElement> GetIncidentalRoomNumber;

	@FindBy(xpath = OR_Reservation.ClickOnAddRooms)
	public WebElement clickOnAddRooms;

	@FindBy(xpath = OR_Reservation.addOnsButton)
	public WebElement CP_ADDON_Button;

	@FindBy(xpath = OR_Reservation.ADDONSHeading)
	public WebElement ADDONSHeading;

	@FindBy(xpath = OR_Reservation.AddONSSearchInput)
	public WebElement AddONSSearchInput;

	@FindBy(xpath = OR_Reservation.AddONSSearchButtton)
	public WebElement AddONSSearchButtton;

	@FindBy(xpath = OR_Reservation.ADDONSSaveButton)
	public WebElement ADDONSSaveButton;

	@FindBy(xpath = OR_Reservation.AddONSAmount)
	public WebElement AddONSAmount;

	@FindBy(xpath = OR_Reservation.ADDONSQuentityInput)
	public WebElement ADDONSQuentityInput;

	@FindBy(xpath = OR_Reservation.ADDONSPlus)
	public WebElement ADDONSPlus;

	@FindBy(xpath = OR_Reservation.incidentalInTS)
	public WebElement incidentalInTS;

	@FindBy(xpath = OR_Reservation.historyRoom)
	public List<WebElement> HistoryRoom;

	@FindBy(xpath = OR_Reservation.detailsTab)
	public WebElement detailsTab;

	@FindBy(xpath = OR_Reservation.deleteIncidentalInSatyInfoButton)
	public List<WebElement> deleteIncidentalInSatyInfoButton;

	@FindBy(xpath = OR_Reservation.voidPopupHeading)
	public WebElement voidPopupHeading;

	@FindBy(xpath = OR_Reservation.enterNotes)
	public WebElement enterNotes;

	@FindBy(xpath = OR_Reservation.voidButton)
	public List<WebElement> voidButton;

	@FindBy(xpath = OR_Reservation.GetTotalTaxBeforeSaveRes)
	public WebElement getTotalTaxBeforeSaveRes;

	@FindBy(xpath = OR_Reservation.GetTotalTripBeforeSaveRes)
	public WebElement getTotalTripBeforeSaveRes;

	@FindBy(xpath = OR_Reservation.GetRoomChrgesBeforeSaveRes)
	public WebElement getRoomChrgesBeforeSaveRes;

	@FindBy(xpath = OR_Reservation.GetRoomChargeFolioCount)
	public List<WebElement> getRoomChargeFolioCount;

	@FindBy(xpath = OR_Reservation.GetTaxTotalinChildFolioItem)
	public WebElement getTaxTotalinChildFolioItem;

	@FindBy(xpath = OR_Reservation.ClickFolioPopupCancel)
	public WebElement clickFolioPopupCancel;

	@FindBy(xpath = OR_Reservation.ItemDetailOnFolioPopup)
	public WebElement itemDetailOnFolioPopup;

	@FindBy(xpath = OR_Reservation.GetTaxFolioItems)
	public List<WebElement> getTaxFolioItems;

	@FindBy(xpath = OR_Reservation.MARKETSEGMENT_ADVANCESEARCH_DROPDOWNLIST)
	public List<WebElement> MARKETSEGMENT_ADVANCESEARCH_DROPDOWNLIST;

	@FindBy(xpath = OR_Reservation.ADVANCESEARCH_MARKETSEGMENT_BUTTON)
	public WebElement ADVANCESEARCH_MARKETSEGMENT_BUTTON;

	@FindBy(xpath = OR_Reservation.MARKETSEGMENT_DROPDOWNLIST)
	public List<WebElement> MARKETSEGMENT_DROPDOWNLIST;

	@FindBy(xpath = OR_Reservation.QUOTECREATED_ALERTMESSAGE)
	public WebElement QUOTECREATED_ALERTMESSAGE;

	@FindBy(xpath = OR_Reservation.GUESTINFO_EDITBUTTON)
	public WebElement GUESTINFO_EDITBUTTON;

	@FindBy(xpath = OR_Reservation.GUESTINFO_SAVEBUTTON)
	public WebElement GUESTINFO_SAVEBUTTON;

	@FindBy(xpath = OR_Reservation.GUESTINFO_GUESTPROFILE_INPUTFIELD)
	public WebElement GUESTINFO_GUESTPROFILE_INPUTFIELD;

	@FindBy(xpath = OR_Reservation.GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP)
	public WebElement GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP;

	@FindBy(xpath = OR_Reservation.GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP_YESBUTTON)
	public WebElement GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP_YESBUTTON;

	@FindBy(xpath = OR_Reservation.GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP_NOBUTTON)
	public WebElement GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP_NOBUTTON;

	@FindBy(xpath = OR_Reservation.GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP_CLOSEBUTTON)
	public WebElement GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP_CLOSEBUTTON;

	@FindBy(xpath = OR_Reservation.GUESTINFO_SENDEMAIL_BUTTON)
	public WebElement GUESTINFO_SENDEMAIL_BUTTON;

	@FindBy(xpath = OR_Reservation.GUESTINFO_SENDEMAIL_HEADERTITLE)
	public WebElement GUESTINFO_SENDEMAIL_HEADERTITLE;

	@FindBy(xpath = OR_Reservation.GUESTINFO_SENDEMAIL_EMAILID)
	public WebElement GUESTINFO_SENDEMAIL_EMAILID;

	@FindBy(xpath = OR_Reservation.SENDMAIL_POPUP_SEND_BUTTON)
	public WebElement SENDMAIL_POPUP_SEND_BUTTON;

	@FindBy(xpath = OR_Reservation.GUESTINFO_SENDEMAIL_SUBJECT)
	public WebElement GUESTINFO_SENDEMAIL_SUBJECT;

	@FindBy(xpath = OR_Reservation.GUESTINFO_SENDEMAIL_DESCRIPTION)
	public WebElement GUESTINFO_SENDEMAIL_DESCRIPTION;

	@FindBy(xpath = OR_Reservation.POST_CANCELLATIONFEE_CHECKBOX)
	public WebElement POST_CANCELLATIONFEE_CHECKBOX;

	@FindBy(xpath = OR.GetTotalIncidentalsAmount)
	public WebElement getTotalIncidentalsAmount;

	@FindBy(xpath = OR.TaxItemPlaceholderInFolioDetail)
	public WebElement taxItemPlaceholderInFolioDetail;

	@FindBy(xpath = OR.TaxItemDetails)
	public WebElement taxItemDetailsPopup;

	@FindBy(xpath = OR.CancelTaxItemDetails)
	public WebElement cancelTaxItemDetailsPopup;

	@FindBy(xpath = OR_Reservation.SelectMarketButton)
	public WebElement SelectMarketButton;

	@FindBy(xpath = OR_Reservation.OVERBOOKING_TAB_MESSAGE)
	public WebElement OVERBOOKING_TAB_MESSAGE;

	@FindBy(xpath = OR_Reservation.OVERBOOKING_TAB_YESBUTTON)
	public WebElement OVERBOOKING_TAB_YESBUTTON;

	@FindBy(xpath = OR_Reservation.OVERBOOKING_TAB_NOBUTTON)
	public WebElement OVERBOOKING_TAB_NOBUTTON;

	@FindBy(xpath = OR_Reservation.OVERBOOKING_TAB_CLOSEBUTTON)
	public WebElement OVERBOOKING_TAB_CLOSEBUTTON;

	@FindBy(xpath = OR_Reservation.AssociatedAccountName)
	public WebElement AssociatedAccountName;

	@FindBy(xpath = OR_Reservation.GetRoomNumber)
	public WebElement GetRoomNumber;

	@FindBy(xpath = OR_Reservation.GroupSign)
	public WebElement GroupSign;

	@FindBy(xpath = OR_Reservation.CheckInButton)
	public WebElement CheckInButton;

	@FindBy(xpath = OR_Reservation.CheckInConfirmButton)
	public WebElement CheckInConfirmButton;

	@FindBy(xpath = OR_Reservation.SpinerLoading)
	public WebElement SpinerLoading;

	@FindBy(xpath = OR_Reservation.GetReservationStatus)
	public WebElement GetReservationStatus;

	@FindBy(xpath = OR_Reservation.GetRoomChargesInTripSummaryBeforeCreateReservation)
	public WebElement GetRoomChargesInTripSummaryBeforeCreateReservation;

	@FindBy(xpath = OR_Reservation.GetTaxandServicesInTripSummaryBeforeCreateReservation)
	public WebElement GetTaxandServicesInTripSummaryBeforeCreateReservation;

	@FindBy(xpath = OR_Reservation.EditMarketingInfoIcon)
	public WebElement EditMarketingInfoIcon;

	@FindBy(xpath = OR_Reservation.SaveMarketingInfo)
	public WebElement SaveMarketingInfo;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_SplitCheckBox)
	public WebElement CP_NewReservation_SplitCheckBox;

	@FindBy(xpath = OR_Reservation.spinerLoadingatRoomSelection)
	public WebElement spinerLoadingatRoomSelection;

	@FindBy(xpath = OR_Reservation.guestStatement)
	public WebElement guestStatement;

	@FindBy(xpath = OR_Reservation.guestStatementWithTaxBreakdown)
	public WebElement guestStatementWithTaxBreakdown;

	@FindBy(xpath = OR_Reservation.guestRegistration)
	public WebElement guestRegistration;

	@FindBy(xpath = OR_Reservation.guestRegistartiontaxbreakdown)
	public WebElement guestRegistartiontaxbreakdown;

	@FindBy(xpath = OR_Reservation.reservationReports)
	public WebElement reservationReports;

	@FindBy(xpath = OR_Reservation.SpanTaskCategoryImage)
	public List<WebElement> SpanTaskCategoryImage;

	@FindBy(xpath = OR_Reservation.SpanTaskName)
	public List<WebElement> SpanTaskName;

	@FindBy(xpath = OR_Reservation.SpanTaskDueOn)
	public List<WebElement> SpanTaskDueOn;

	@FindBy(xpath = OR_Reservation.SpanTaskDetails)
	public List<WebElement> SpanTaskDetails;

	@FindBy(xpath = OR_Reservation.SpanTaskStatus)
	public List<WebElement> SpanTaskStatus;

	@FindBy(xpath = OR.TypeSearch)
	public WebElement TypeSearch;

	@FindBy(xpath = OR_Reservation.ADDITIONAL_EMAIL_ADDRESS)
	public WebElement additionalEmailAddress;

	@FindBy(xpath = OR_Reservation.ADDITIONAL_PHONE_NUMBER)
	public WebElement additionalPhoneNumber;

	@FindBy(xpath = OR_Reservation.CHECK_BOX_INCLUDE_TOTAL_REVENUE)
	public WebElement CheckBoxIncludeTotalRevenue;

	@FindBy(xpath = OR_Reservation.CLICK_ON_PRINT_BUTTON)
	public WebElement clickOnPrintButton;

	@FindBy(xpath = OR_Reservation.RADIO_BUTTON_MAILING_DETAILS)
	public WebElement radioButtonMailingDetails;

	@FindBy(xpath = OR_Reservation.PRINT_ICON)
	public WebElement printIcon;

	@FindBy(xpath = OR_Reservation.CLICK_ON_ADVANCE_SEARCH)
	public WebElement clickOnAdvanceSearch;

	@FindBy(xpath = OR_Reservation.GET_GUEST_INFO_TEXT)
	public WebElement getGuestInfoText;

	@FindBy(xpath = OR_Reservation.GET_RESERVATION_INFO_TEXT)
	public WebElement getReservationInfoText;

	@FindBy(xpath = OR_Reservation.GET_MARKETING_INFO_TEXT)
	public WebElement getMarketingInfoText;

	@FindBy(xpath = OR_Reservation.depositPolicyCollapseIcon)
	public WebElement depositPolicyCollapseIcon;

	@FindBy(xpath = OR_Reservation.depositPolicyNameAtDisclaimers)
	public WebElement depositPolicyNameAtDisclaimers;

	@FindBy(xpath = OR_Reservation.depositPolicyTextAtDisclaimers)
	public WebElement depositPolicyTextAtDisclaimers;

	@FindBy(xpath = OR_Reservation.depositPolicyToolTipTextAtDisclaimers)
	public WebElement depositPolicyToolTipTextAtDisclaimers;

	@FindBy(xpath = OR_Reservation.fristRoomChageInReservation)
	public WebElement fristRoomChageInReservation;

	@FindBy(xpath = OR_Reservation.SecondRoomChageInReservation)
	public WebElement SecondRoomChageInReservation;

	@FindBy(xpath = OR_Reservation.fristRoomNumberInReservation)
	public WebElement fristRoomNumberInReservation;

	@FindBy(xpath = OR_Reservation.secondRoomNumberInReservation)
	public WebElement secondRoomNumberInReservation;

	@FindBy(xpath = OR_Reservation.seclectProperty)
	public WebElement seclectProperty;

	@FindBy(xpath = OR_Reservation.propertyDetailsPhoneNumber)
	public WebElement propertyDetailsPhoneNumber;

	@FindBy(xpath = OR_Reservation.propertyDetailsAddress1)
	public WebElement propertyDetailsAddress1;

	@FindBy(xpath = OR_Reservation.propertyDetailsCity)
	public WebElement propertyDetailsCity;

	@FindBy(xpath = OR_Reservation.propertyDetailsCountry)
	public WebElement propertyDetailsCountry;

	@FindBy(xpath = OR_Reservation.propertyDetailsState)
	public WebElement propertyDetailsState;

	@FindBy(xpath = OR_Reservation.propertyDetailsPostalCode)
	public WebElement propertyDetailsPostalCode;

	@FindBy(xpath = OR_Reservation.propertyDetailscontactName)
	public WebElement propertyDetailscontactName;

	@FindBy(xpath = OR_Reservation.propertyDetailsinputEmail)
	public WebElement propertyDetailsinputEmail;

	@FindBy(xpath = OR_Reservation.noShowPolicyDisclaimersTab)
	public WebElement noShowPolicyDisclaimersTab;

	@FindBy(xpath = OR_Reservation.closeNoShowPolicyExpand)
	public WebElement closeNoShowPolicyExpand;

	/*
	 * @FindBy(xpath = OR_Reservation.folioBalance) public WebElement
	 * folioBalance;
	 */

	@FindBy(xpath = OR_Reservation.unsavedDataPopupHeader)
	public WebElement unsavedDataPopupHeader;

	@FindBy(xpath = OR_Reservation.unsavedDataPopupNoButton)
	public WebElement unsavedDataPopupNoButton;

	@FindBy(xpath = OR_Reservation.ClickTaxExcemptCheckBox)
	public WebElement clickTaxExcemptCheckBox;

	@FindBy(xpath = OR_Reservation.EnterTaxExcemptId)
	public WebElement enterTaxExcemptId;

	@FindBy(xpath = OR_Reservation.SelectRoomClassInSearch)
	public WebElement selectRoomClassInSearch;

	@FindBy(xpath = OR_Reservation.GetTotalGuestFolioItemInRes)
	public List<WebElement> getTotalGuestFolioItemInRes;

	@FindBy(xpath = OR_Reservation.GetDepositDueCharge)
	public WebElement getDepositDueCharge;

	@FindBy(xpath = OR.CheckboxDetailedReservationList)
	public WebElement checkboxDetailedReservationList;

	@FindBy(xpath = OR.ClickOnReport)
	public WebElement clickOnReport;

	@FindBy(xpath = OR.ClickOnReportAsPDF)
	public WebElement clickOnReportAsPDF;

	@FindBy(xpath = OR.ClickOnCloseReportsPopup)
	public WebElement ClickOnCloseReportsPopup;

	@FindBy(xpath = OR.ClickOnPrint)
	public WebElement ClickOnPrint;

	@FindBy(xpath = OR.ModuleLoading)
	public WebElement ModuleLoading;

	@FindBy(xpath = OR.DownloadButton)
	public WebElement DownloadButton;

	@FindBy(xpath = OR_Reservation.NotesTypes)
	public List<WebElement> NotesTypes;

	@FindBy(xpath = OR_Reservation.NotesRoom)
	public List<WebElement> NotesRoom;

	@FindBy(xpath = OR_Reservation.NotesSubject)
	public List<WebElement> NotesSubject;

	@FindBy(xpath = OR_Reservation.NotesDescription)
	public List<WebElement> NotesDescription;

	@FindBy(xpath = OR_Reservation.NotesUpdatedOn)
	public List<WebElement> NotesUpdatedOn;

	@FindBy(xpath = OR_Reservation.checkOutPaymentDate)
	public WebElement checkOutPaymentDate;

	@FindBy(xpath = OR_Reservation.checkOutPaymentType)
	public WebElement checkOutPaymentType;

	@FindBy(xpath = OR_Reservation.checkOutFolioBalence)
	public WebElement checkOutFolioBalence;

	@FindBy(xpath = OR_Reservation.checkoutPaymentStatus)
	public WebElement checkoutPaymentStatus;

	@FindBy(xpath = OR_Reservation.checkOutButtonClose)
	public WebElement checkOutButtonClose;

	@FindBy(xpath = OR_Reservation.unsavedDataPopupYesButton)
	public WebElement unsavedDataPopupYesButton;

	@FindBy(xpath = OR_Reservation.checkOutPaymentMethod)
	public WebElement checkOutPaymentMethod;

	@FindBy(xpath = OR_Reservation.checkOutPaymentAmount)
	public WebElement checkOutPaymentAmount;

	@FindBy(xpath = OR_Reservation.folioBalance)
	public WebElement folioBalance;

	@FindBy(xpath = OR_Reservation.checkOutPopUp)
	public WebElement checkOutPopUp;

	@FindBy(xpath = OR_Reservation.dirtyRoomPopUp)
	public WebElement dirtyRoomPopUp;

	@FindBy(xpath = OR_Reservation.checkOutPaymentButton)
	public WebElement checkOutPaymentButton;

	@FindBy(xpath = OR.VERIFY_ACCOUNT_NAME)
	public WebElement verifyAccountName;

	@FindBy(xpath = OR.GET_CONFIRMATION_NUMBER)
	public WebElement getConfirmationNumber;

	@FindBy(xpath = OR_Reservation.PAYMENTMETHOD_EDITBUTTON)
	public WebElement PAYMENTMETHOD_EDITBUTTON;

	@FindBy(xpath = OR_Reservation.PAYMENTMETHOD_SAVEBUTTON)
	public WebElement PAYMENTMETHOD_SAVEBUTTON;

	@FindBy(xpath = OR_Reservation.CP_SECONDARYGUEST_CHECKOUTBUTTON)
	public WebElement CP_SECONDARYGUEST_CHECKOUTBUTTON;

	@FindBy(xpath = OR_Reservation.CP_SECONDARYGUEST_CHECKOUT_CONFIRMATIONPOPUP)
	public WebElement CP_SECONDARYGUEST_CHECKOUT_CONFIRMATIONPOPUP;

	@FindBy(xpath = OR_Reservation.CP_CHECKOUT_CONFIRMATIONTAB_NOBUTTON)
	public WebElement CP_CHECKOUT_CONFIRMATIONTAB_NOBUTTON;

	@FindBy(xpath = OR_Reservation.CP_CHECKOUT_CONFIRMCHECKOUT_BUTTON)
	public WebElement CP_CHECKOUT_CONFIRMCHECKOUT_BUTTON;

	@FindBy(xpath = OR_Reservation.CP_CHECKOUT_GENERATEGUESTSTATEMENT_TOGGLE)
	public WebElement CP_CHECKOUT_GENERATEGUESTSTATEMENT_TOGGLE;

	@FindBy(xpath = OR_Reservation.CP_CHECKIN_GENERATEGUESTSTATEMENT_TOGGLE)
	public WebElement CP_CHECKIN_GENERATEGUESTSTATEMENT_TOGGLE;

	@FindBy(xpath = OR_Reservation.CP_SECONDARYGUEST_ROOMNUMBER)
	public WebElement CP_SECONDARYGUEST_ROOMNUMBER;

	@FindBy(xpath = OR_Reservation.CP_SECONDARYGUEST_SALUTATION)
	public WebElement CP_SECONDARYGUEST_SALUTATION;

	@FindBy(xpath = OR_Reservation.CP_SECONDARYGUEST_FIRSTNAME)
	public WebElement CP_SECONDARYGUEST_FIRSTNAME;

	@FindBy(xpath = OR_Reservation.CP_SECONDARYGUEST_SALUTATION)
	public WebElement CP_SECONDARYGUEST_LASTNAME;

	@FindBy(xpath = OR_Reservation.CP_SECONDARYGUEST_CHECKINBUTTON)
	public WebElement CP_SECONDARYGUEST_CHECKINBUTTON;

	@FindBy(xpath = OR_Reservation.CP_SECONDARYGUEST_CHECKIN_CHECKOUTTAB_TITLENAME)
	public WebElement CP_SECONDARYGUEST_CHECKIN_CHECKOUTTAB_TITLENAME;

	@FindBy(xpath = OR_Reservation.cancelResPopupPostCancelFeeCheckBox)
	public WebElement cancelResPopupPostCancelFeeCheckBox;

	@FindBy(xpath = OR_Reservation.cancelResPopupPostCancelFeeCheckBoxClick)
	public WebElement cancelResPopupPostCancelFeeCheckBoxClick;

	@FindBy(xpath = OR_Reservation.resStatusAtBasicSearch)
	public WebElement resStatusAtBasicSearch;

	@FindBy(xpath = OR_Reservation.paymentPopupDate)
	public WebElement paymentPopupDate;

	@FindBy(xpath = OR_Reservation.guestHistoryCategories)
	public List<WebElement> guestHistoryCategories;

	@FindBy(xpath = OR_Reservation.guestHistoryDescriptions)
	public List<WebElement> guestHistoryDescriptions;

	@FindBy(xpath = OR_Reservation.reportsIcon)
	public WebElement reportsIcon;

	@FindBy(xpath = OR_Reservation.guestStatements)
	public WebElement guestStatements;

	@FindBy(xpath = OR_Reservation.allRooms)
	public WebElement allRooms;

	@FindBy(xpath = OR_Reservation.folioLineItemTableTrSize)
	public List<WebElement> folioLineItemTableTrSize;

	@FindBy(xpath = OR_Reservation.FOLIO_OPTIONS_TAB)
	public WebElement FOLIO_OPTIONS_TAB;

	@FindBy(xpath = OR_Reservation.FOLIO_DETAILS_TAB)
	public WebElement FOLIO_DETAILS_TAB;

	@FindBy(xpath = OR_Reservation.SUPPRESS_ACCOUNT_FOLIO_PRINTING_CHECKBOX)
	public WebElement SUPPRESS_ACCOUNT_FOLIO_PRINTING_CHECKBOX;

	@FindBy(xpath = OR_Reservation.checkoutWithoutPayment)
	public WebElement checkoutWithoutPayment;

	@FindBy(xpath = OR_Reservation.depositPaymentPopupHeading)
	public WebElement depositPaymentPopupHeading;

	@FindBy(xpath = OR_Reservation.depositPaymentPopupPolicy)
	public WebElement depositPaymentPopupPolicy;

	@FindBy(xpath = OR_Reservation.depositPaymentPopupBalance)
	public WebElement depositPaymentPopupBalance;

	@FindBy(id = OR_Reservation.depositPaymentPopupAmount)
	public WebElement depositPaymentPopupAmount;

	@FindBy(xpath = OR_Reservation.depositPaymentPopupTransactionDecline)
	public WebElement depositPaymentPopupTransactionDecline;

	@FindBy(xpath = OR_Reservation.depositPaymentPopupPaymentButton)
	public WebElement depositPaymentPopupPaymentButton;

	@FindBy(xpath = OR_Reservation.cancelDepositPaymentPopupButton)
	public WebElement cancelDepositPaymentPopupButton;

	@FindBy(xpath = OR_Reservation.saveAsQuoteButton)
	public WebElement saveAsQuoteButton;

	@FindBy(xpath = OR_Reservation.reservationStatusOnDetailSection)
	public WebElement reservationStatusOnDetailSection;

	@FindBy(xpath = OR_Reservation.editPaymentInfoButton)
	public WebElement editPaymentInfoButton;

	@FindBy(xpath = OR_Reservation.reservationNumberOnDetailSection)
	public WebElement reservationNumberOnDetailSection;

	@FindBy(xpath = OR_Reservation.decryptLink)
	public WebElement decryptLink;

	@FindBy(xpath = OR_Reservation.ccNumberInput)
	public WebElement ccNumberInput;

	@FindBy(xpath = OR_Reservation.paymentInfoSaveButton)
	public WebElement paymentInfoSaveButton;

	@FindBy(xpath = OR_Reservation.updateGuestProfileInput)
	public WebElement updateGuestProfileInput;

	@FindBy(xpath = OR_Reservation.depositDue)
	public WebElement depositDue;

	@FindBy(xpath = OR_Reservation.CP_Reservation_CheckINUassigned)
	public WebElement cP_Reservation_CheckINUassigned;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_COUNTRY)
	public WebElement CP_NewReservation_COUNTRY;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_STATE)
	public WebElement CP_NewReservation_STATE;

	@FindBy(xpath = OR_Reservation.CP_NewReservation_POSTALCODE)
	public WebElement CP_NewReservation_POSTALCODE;

	@FindBy(xpath = OR_Reservation.EnterReservationNumber)
	public WebElement enterReservationNumber;

	@FindBy(xpath = OR_Reservation.ClickPayButton)
	public WebElement clickPayButton;

	@FindBy(xpath = OR_Reservation.ClosePaymentSuccessfulModal)
	public WebElement closePaymentSuccessfulModal;

	@FindBy(xpath = OR_Reservation.CreateReservationPaymentMethod)
	public WebElement createReservationPaymentMethod;

	@FindBy(css = OR_Reservation.CashPaymentDescription)
	public WebElement cashPaymentDescription;

	@FindBy(xpath = OR_Reservation.UpdatedByUser)
	public WebElement updatedByUser;

	@FindBy(xpath = OR_Reservation.ClickSavePaymentButton)
	public WebElement clickSavePaymentButton;

	@FindBy(xpath = OR_Reservation.EnterAmount)
	public WebElement enterAmount;

	@FindBy(xpath = OR_Reservation.ReservationNumber)
	public WebElement reservationNumber;

	@FindBy(xpath = OR_Reservation.authorizationSuccess_Date)
	public WebElement authorizationSuccess_Date;

	@FindBy(xpath = OR_Reservation.authorizationSuccess_Balance)
	public WebElement authorizationSuccess_Balance;

	@FindBy(xpath = OR_Reservation.authorizationSuccess_Folio)
	public WebElement authorizationSuccess_Folio;

	@FindBy(xpath = OR_Reservation.authorizationSuccess_Amount)
	public WebElement authorizationSuccess_Amount;

	@FindBy(xpath = OR_Reservation.authorizationSuccess_Type)
	public WebElement authorizationSuccess_Type;

	@FindBy(xpath = OR_Reservation.authorizationSuccess_PaymentMethod)
	public WebElement authorizationSuccess_PaymentMethod;

	@FindBy(xpath = OR_Reservation.authorizationSuccess_Status)
	public WebElement authorizationSuccess_Status;

	@FindBy(xpath = OR_Reservation.authorizationSuccess_Note)
	public WebElement authorizationSuccess_Note;

	@FindBy(xpath = OR_Reservation.authorizationSuccess_CloseButton)
	public WebElement authorizationSuccess_CloseButton;

	@FindBy(xpath = OR_Reservation.reservation_Save)
	public WebElement reservation_Save;

	@FindBy(xpath = OR_Reservation.loading)
	public WebElement loading;

	@FindBy(xpath = OR_Reservation.checkout_Close)
	public WebElement checkout_Close;

	@FindBy(xpath = OR_Reservation.CLOSE_BULK_CHECKOUT_POPUP)
	public WebElement closeBulkCheckoutPopUp;

	@FindBy(xpath = OR_Reservation.ROLL_BACK_POPUP_TEXT)
	public WebElement rollBackPopUpText;

	@FindBy(xpath = OR_Reservation.ACCEPT_ROLL_BACK_CHANGE)
	public WebElement acceptRollBackChange;

	@FindBy(xpath = OR_Reservation.reservationStatusInDetailSection)
	public WebElement reservationStatusInDetailSection;

	@FindBy(xpath = OR_Reservation.bulkCheckOutSuccessHeading)
	public WebElement bulkCheckOutSuccessHeading;

	@FindBy(xpath = OR_Reservation.bulkCheckOutSuccessReservationCount)
	public WebElement bulkCheckOutSuccessReservationCount;

	@FindBy(xpath = OR.PRINT_RESERVATION_REPORTS)
	public WebElement PRINT_RESERVATION_REPORTS;

	@FindBy(xpath = OR_Reservation.checkout_ProceedButton)
	public WebElement checkout_ProceedButton;

	@FindBy(xpath = OR_Reservation.LongStayText)
	public WebElement LongStayText;

	@FindBy(xpath = OR_Reservation.LongStayCheckbox)
	public WebElement LongStayCheckbox;

	@FindBy(xpath = OR_Reservation.CheckInGuestRegirstrationReportToggle)
	public WebElement CheckInGuestRegirstrationReportToggle;

	@FindBy(xpath = OR_Reservation.CheckInDirtyRoomPopup)
	public WebElement checkInDirtyRoomPopup;

	@FindBy(xpath = OR_Reservation.reservationPolicy)
	public WebElement reservationPolicy;

	@FindBy(xpath = OR_Reservation.newGroupReservationInput)
	public WebElement newGroupReservationInput;

	@FindBy(xpath = OR_Reservation.checkInPolicy)
	public WebElement checkInPolicy;

	@FindBy(xpath = OR_Reservation.tripSummaryRoomCharges)
	public WebElement tripSummaryRoomCharges;

	@FindBy(xpath = OR_Reservation.tripSummaryTaxAndServices)
	public WebElement tripSummaryTaxAndServices;

	@FindBy(xpath = OR_Reservation.tripSummaryTotal)
	public WebElement tripSummaryTotal;

	@FindBy(xpath = OR_Reservation.tripSummaryDepositPaid)
	public WebElement tripSummaryDepositPaid;

	@FindBy(xpath = OR_Reservation.depositPolicyLabel)
	public WebElement depositPolicyLabel;

	@FindBy(xpath = OR_Reservation.depositPolicyCollapse)
	public WebElement depositPolicyCollapse;

	@FindBy(xpath = OR_Reservation.checkout_ProceedMessage)
	public WebElement checkout_ProceedMessage;

	@FindBy(xpath = OR_Reservation.checkoutWithoutRefund)
	public WebElement checkoutWithoutRefund;

	@FindBy(xpath = OR_Reservation.checkoutTakemeToFolio)
	public WebElement checkoutTakemeToFolio;

	@FindBy(xpath = OR_Reservation.CLOSEBULKACTIONPOPUP)
	public WebElement CLOSEBULKACTIONPOPUP;

	@FindBy(xpath = OR_Reservation.CLOSE_BULK_ACTION_POPUP)
	public WebElement CLOSE_BULK_ACTION_POPUP;

	@FindBy(xpath = OR_Reservation.GET_ALL_ROOM_CLASS_NAME)
	public List<WebElement> getAllRoomClassName;

	@FindBy(xpath = OR_Reservation.GET_ALL_ROOM_RATES)
	public List<WebElement> getAllRoomrates;

	@FindBy(xpath = OR_Reservation.GET_ALL_ROOM_CLASS_DESCRIPTION)
	public List<WebElement> getAllRoomClassDescription;

	@FindBy(xpath = OR_Reservation.GET_ERROR_MESSAGE)
	public List<WebElement> getErrorMessage;
}
