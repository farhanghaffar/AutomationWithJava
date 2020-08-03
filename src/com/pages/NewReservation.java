package com.innroad.inncenter.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewReservation {

	public static final String DepositPolicy_Button = ".modal-scrollable div button";
	public static final String CloseResTab = "//i[contains(@data-bind,'click: $parent.closeTab')]";
	public static final String OverbookingPopup = "//div[@id='reservationOverBookingPopUp']";
	public static final String OverbookingPopup_Continue = "//div[@id='reservationOverBookingPopUp']//button[text()='Continue']";
	public static final String RoomAssignmentStatus = "//span[contains(@data-bind,'    text: StatusId')]";
	public static final String RoomCharger_SelectButton = "//button[contains(@data-bind,'click: SelectClickOnReCalculateFolios')]";

	public static final String AccountLoadingToaster = "(//div[contains(@data-bind,'foreach: ReservationDetails')]//div[@class='modules_loading'])[1]";
	public static final String RoomSelectPopup = "//div[contains(@data-bind,'getElement: popUpHandle')]//h4";
	public static final String RoomDirtyButton = "//div[contains(@data-bind,'getElement: popUpHandle')]//button[text()='Confirm']";

	public static final String Balance = "//label[text()='Balance: ']//parent::div/span[@class='pamt']/span";
	public static final String PaymentDetail = "//textarea[contains(@data-bind,'PayDetails')]";
	public static final String Item_Details_Popup_Tax = "//span[contains(@data-bind,'TaxSummary')]";
	public static final String Item_Details_Popup_TotalCharges = "//span[contains(@data-bind,'TotalSummary')]";
	public static final String ReservationArrivalDate = "(//p[contains(@data-bind,'DateStart')])[1]";
	public static final String RecalculateRoomCharges = "//div[@id='divRoomPickerReCalculateFolios']//input[@name='roomChargesChangedGroup' and contains(@data-bind,'ReCalculateFoliosEnabled')]";
	public static final String Reservation_FolioBalance = "//label[contains(text(),'Folio Balance:')]//following-sibling::div//span[contains(@data-bind,'ComputedSummary')]";

	// NoShow Reservation
	public static final String NOShow_VoidRoomCharges = "//h4[text()='NoShow']//ancestor::div[contains(@data-bind,'NowShowCancellationPopupContainer')]//input[contains(@data-bind, 'VoidCharges')]";
	public static final String NOShow_NoShowFee = "//h4[text()='NoShow']//ancestor::div[contains(@data-bind,'NowShowCancellationPopupContainer')]//input[contains(@data-bind, 'PostFees')]";
	public static final String NOShow_OkButton = "//h4[text()='NoShow']//ancestor::div[contains(@data-bind,'NowShowCancellationPopupContainer')]//button[contains(@data-bind, 'OK')]";
	public static final String NOShow_CancelButton = "//h4[text()='NoShow']//ancestor::div[contains(@data-bind,'NowShowCancellationPopupContainer')]//button[contains(@data-bind, 'Cancel')]";
	public static final String Cancellation_CancellationFee = "//h4[text()='Cancellation']//ancestor::div[contains(@data-bind,'NowShowCancellationPopupContainer')]//input[contains(@data-bind, 'PostFees')]";
	public static final String Reservation_CreateGuestProfile_CheckBox = "//div[@id='ReservationDetailMain']//following::input[contains(@data-bind,'CreateGuestProfile')]";
	public static final String BulkNoShowPopup_PostNoShowFee = "//div[contains(@data-bind,'BulkReservationsActionContainer')]//child::input[contains(@data-bind,' checked: BulkPostFees')]";
	// New added
	public static final String Reservation_SameAsContactInfo_CheckBox = "//div[@id='ReservationDetailMain']//following::input[@type='checkbox' and contains(@data-bind,'checked: UseMailingInfo')]";
	public static final String ContactInfo_Country = "//div[contains(@data-bind,'ContactInfo')]//following-sibling::select[contains(@data-bind,'CountryStates')]";
	public static final String ContactInfo_PhoneCountryCode = "//div[contains(@data-bind,'ContactInfo')]//following-sibling::input[contains(@placeholder,'Country Code') and contains(@data-bind,'value: PhoneCountryCode')]";
	public static final String ContactInfo_AltPhoneCountryCode = "//div[contains(@data-bind,'ContactInfo')]//following-sibling::input[contains(@placeholder,'Country Code') and contains(@data-bind,'value: AltPhoneCountryCode')]";
	public static final String ContactInfo_FaxCountryCode = "//div[contains(@data-bind,'ContactInfo')]//following-sibling::input[contains(@placeholder,'Country Code') and contains(@data-bind,'value: FaxCountryCode')]";
	public static final String RoomAssignment_DateArrived = "//input[contains(@data-bind,'value: DateArrived')]";
	public static final String RoomAssignment_DateDepart = "//input[contains(@data-bind,'value: DateDeparted')]";
	public static final String RoomAssignment_SearchedRow = "//tbody[contains(@data-bind,'foreach: RoomListWithRules')]/tr";

	public static final String Reservation_SelectedAdults = "//p[contains(@data-bind,'NumberOfAdults')]";
	public static final String Reservation_SelectedChildren = "//p[contains(@data-bind,'NumberOfChildren')]";
	public static final String Reservation_SelectedNights = "//div[@id='StayInfo']//p[contains(@data-bind,'NoOfNights')]";
	public static final String Reservation_SelectedGuestName = "(//div[@id='StayInfo']//span[contains(@data-bind,'text: GuestNameUI')])[1]";
	public static final String Reservation_SelectedArrival = "//div[@id='StayInfo']//p[contains(@data-bind,'DateStart()')]";
	public static final String Reservation_TaxAndServiceCharges = "//label[contains(text(),'Taxes & Service Charges: ')]//parent::div//following-sibling::span[@class='pamt tsc']/span";
	public static final String BulkNoShowPopup_Message = "(//div[contains(@data-bind,'BulkReservationsActionContainer')]//child::li[@role='presentation'])[1]/a";
	public static final String BulkNoShowPopup_ResNumber = "//div[contains(@data-bind,'BulkOperationInProcess') and contains(@id,'tab')]//child::span[contains(@data-bind,'text: ConfirmationNumber')]";
	public static final String BulkNoShowPopup_VoidRoomCharges = "//div[contains(@data-bind,'BulkReservationsActionContainer')]//child::input[contains(@data-bind,'enable: BulkVoidChargesEnable() === 1, checked: BulkVoidCharges')]";

	public static final String Reservation_RatePlan = "(//label[contains(text(),'Rate Plan:')]//parent::div//select[contains(@data-bind,'RatePlanId')])[1]";
	public static final String Reservation_RoomAssign_RatePlan = "(//select[contains(@data-bind,'RatePlanId')])[5]";

	public static final String AddedNote_NotesType = "//div[@data-bind='visible: showNotesList']//tbody[@data-bind='foreach: NoteList']//span[contains(@data-bind,'text: NoteTypeName')]";
	public static final String AddedNote_Subject = "//div[@data-bind='visible: showNotesList']//tbody[@data-bind='foreach: NoteList']//a[contains(@data-bind,'text: Subject')]";

}
