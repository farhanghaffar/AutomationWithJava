package com.innroad.inncenter.properties;

public class OR_Reservation {
	
	// Login Page
	public static final String clientCode = "txtclientCode";
	public static final String userID = "txtLoginID";
	public static final String password = "txtUserPassword";
	public static final String Login = "btnLogon";
	public static final String confirmCheckInPaymentRoomClassName = "//label[contains(@data-bind,'text: roomClassName + ')]";

	public static final String notesTabHeader = "//div[@class='panel-heading' and text()='Notes']";
	public static final String cancelPolicyCollapseInDetailsTab = "//span[@data-bind='text: PolicyTitle']/..//i";
	public static final String cancelPolicyNameInDetailsTab = "//span[@data-bind='text:PolicyName']";
	public static final String cancelPolicyTextInDetailsTab = "//textarea[contains(@data-bind,'text:PolicyStatement')]";

	public static final String cancelReasonNoteType = "//td[contains(text(),'Cancel')][contains(@data-bind,'text: type')]";

	public static final String cancelResPopupVoidRoomChargesCheckBox = "//span[@data-bind='text: modal.labelVoidCharges']/../..//label[@class='ir-checkbox']//input";
	public static final String cancelResPopupVoidRoomChargesCheckBoxClick = "//span[@data-bind='text: modal.labelVoidCharges']/../..//label[@class='ir-checkbox']";
	public static final String cancelResPopupPolicyName = "//span[contains(@data-bind,'modal.policyDescription')]";
	public static final String cancelResPopupPolicyToolTipName = "//div[@role='tooltip']//h3[@class='popover-title']";
	public static final String cancelResPopupPolicyToolTipText = "//div[@role='tooltip']//div[@class='popover-content']";

	// span[@data-bind='text:
	// modal.labelVoidCharges']/..//span[@class='ir-check-box']
	public static final String noShowSuccessPolicyAmount = "//label[text()='AMOUNT']/..//span[contains(@data-bind,'PaymentAmount')]";

	public static final String Cp_Reservation_PaymentDetail_CloseButton = "//div[@id='ReservationPaymetItemDetail']/..//div/button[contains(@data-bind,'CloseButtonClick')][contains(text(),'Close')]";

	// CP Reservation Page
	public static final String CP_NewReservation = "(//a[contains(text(),'New Reservation')])[1]";
	public static final String CP_NewReservation_CheckinCal = "//label[text()='Check In']/following-sibling::i";
	public static final String CP_NewReservation_CheckoutCal = "//label[text()='Check Out']/following-sibling::i";
	public static final String CP_NewReservation_Adults = "//label[text()='Adults']/preceding-sibling::input";
	public static final String CP_NewReservation_Children = "//label[text()='Children']/preceding-sibling::input";
	public static final String CP_NewReservation_Rateplan = "//label[contains(text(),'Rate Plan / Promo')]/preceding-sibling::div/button";
	public static final String CP_NewReservation_PromoCode = "//label[contains(text(),'Enter Code')]/preceding-sibling::input";
	public static final String CP_NewReservation_SplitReservation = "//label[text()='Split-Room Reservation?']";
	public static final String CP_NewReservation_FindRooms = "//a[@data-bind='click: FindRoom']";
	public static final String CP_NewReservation_DepositAmount = "//label[contains(text(),'Deposit Due')]/following-sibling::div/div";
	public static final String CP_NewReservation_OverrideDeposit = "(//span[contains(text(),'Override Deposit Due')]/../following-sibling::div/span)[1]";
	public static final String CP_NewReservation_OverrideDepositAmoount = "//label[contains(text(),'Deposit Due')]/following-sibling::div//input";
	public static final String CP_NewReservation_TripTotalAmount = "//div[contains(@class,'desktop-view trip-summary-sticky')]//div[contains(@class,'panel-body')]//div[contains(@data-bind,'tripTotal')]";
	public static final String CP_NewReservation_Next = "//a[contains(text(),'NEXT')]";
	public static final String CP_NewReservation_GuestSalutation = "//button[contains(@class,'salutationBtn')]";
	public static final String CP_NewReservation_GuestFirstName = "//label[text()='Guest ']/preceding-sibling::input";
	public static final String CP_NewReservation_GuestLastName = "//label[text()='Guest ']/../../../../following-sibling::div//input";
	public static final String CP_NewReservation_ContactSalutation = "(//button[contains(@class,'salutationBtn')])[2]";
	public static final String CP_NewReservation_ContactFirstName = "//label[text()='Contact']/preceding-sibling::input";
	public static final String CP_NewReservation_ContactLastName = "//label[text()='Contact']/../../../../following-sibling::div//input";
	public static final String CP_NewReservation_Phone = "(//label[text()='Phone']/preceding-sibling::input)[2]";
	public static final String CP_NewReservation_AltenativePhone = "(//label[text()='Alternate Phone']/preceding-sibling::input)";
	public static final String CP_NewReservation_Email = "(//label[text()='E-mail']/preceding-sibling::input)";
	public static final String CP_NewReservation_Account = "(//label[text()='Account']/preceding-sibling::input)";
	public static final String CP_NewReservation_Address1 = "(//label[text()='Address 1']/preceding-sibling::input)";
	public static final String CP_NewReservation_Address2 = "(//label[text()='Address 2']/preceding-sibling::input)";
	public static final String CP_NewReservation_Address3 = "(//label[text()='Address 3']/preceding-sibling::input)";
	public static final String CP_NewReservation_City = "(//label[text()='City']/preceding-sibling::input)";
	public static final String CP_NewReservation_PostalCode = "(//label[contains(text(),'Postal Code')])[2]/preceding-sibling::input";
	public static final String CP_NewReservation_CreateGuestProfile = "//span[contains(text(),'Create Guest Profile')]/..//input";
	public static final String CP_NewReservation_AddMoreGuestInfo = "(//button[text()='Add More Guest Info'])[2]";

	public static final String CP_NewReservation_PaymentMethod = "//label[text()='Payment Method']/preceding-sibling::div//button";
	public static final String CP_NewReservation_CardNumber = "//label[text()='Credit Card Number']/preceding-sibling::input";
	public static final String CP_NewReservation_NameOnCard = "//label[text()='Name On card']/preceding-sibling::input";
	public static final String CP_NewReservation_Swipe = "//button[text()='SWIPE']";
	public static final String CP_NewReservation_CardExpDate = "(//label[text()='Exp Date']/preceding-sibling::input)[2]";
	public static final String CP_NewReservation_TravelAgent = "//label[text()='Travel Agent']/preceding-sibling::input";
	public static final String CP_NewReservation_Market = "//label[text()='Market']/preceding-sibling::div/button";
	public static final String CP_NewReservation_Referral = "(//label[text()='Referral']/preceding-sibling::div/button)[2]";
	public static final String CP_NewReservation_Book = "(//button[text()='BOOK NOW'])[1]";
	public static final String CP_NewReservation_Quote = "//button[text()='SAVE QUOTE']";
	public static final String CP_NewReservation_DepositPayPopUp = "//h4[text()='Deposit Payment']";
	public static final String CP_NewReservation_ConfirmationNumber = "//h4[contains(text(),'Confirmation')]/../..//strong/following-sibling::span";
	public static final String CP_NewReservation_ReservationStatus = "//h4[contains(text(),'Confirmation')]/../..//strong[text()='Status:']/following-sibling::span";
	public static final String CP_NewReservation_ClosePopUp = "//h4[contains(text(),'Confirmation')]/../..//button[text()='Close']";
	public static final String CP_NewReservation_Folio = "//li[contains(@data-bind,'Folio')]//a[text()='Folio(s)']/..";
	public static final String CP_NewReservation_History = "//a[text()='History']/..";
	public static final String CP_NewReservation_AddRoom = "//button[text()='ADD A ROOM']";
	public static final String CP_NewReservation_DetailsTab = "//a[text()='Details']/..";
	public static final String CP_NewReservation_DocumentsTab = "//a[contains(text(),'Documents')]";

	public static final String CP_GuestInfo_GuestName = "//guest-info//span[contains(text(),'Gues')]/../..//label[text()='Guest Name']/following-sibling::div//span";
	public static final String CP_GuestInfo_ContactName = "//guest-info//span[contains(text(),'Gues')]/../..//label[text()='Contact Name']/following-sibling::div//span";
	public static final String CP_GuestInfo_Email = "//guest-info//span[contains(text(),'Gues')]/../..//label[text()='E-mail']/following-sibling::span";
	public static final String CP_GuestInfo_Phone = "//guest-info//span[contains(text(),'Gues')]/../..//label[text()='Phone']/following-sibling::span";
	public static final String CP_GuestInfo_AlternatePhone = "//guest-info//span[contains(text(),'Gues')]/../..//label[text()='Alternate Phone']/following-sibling::span";
	public static final String CP_GuestInfo_Account = "//guest-info//span[contains(text(),'Gues')]/../..//label[text()='Account']/following-sibling::span";
	public static final String CP_GuestInfo_Country = "//guest-info//span[contains(text(),'Gues')]/../..//label[text()='Country']/following-sibling::span";
	public static final String CP_GuestInfo_PostalCode = "//guest-info//span[contains(text(),'Gues')]/../..//label[text()='Postal Code']/following-sibling::span";
	public static final String CP_GuestInfo_State = "//guest-info//span[contains(text(),'Gues')]/../..//label[text()='State']/following-sibling::span";
	public static final String CP_GuestInfo_City = "//guest-info//span[contains(text(),'Gues')]/../..//label[text()='City']/following-sibling::span";
	public static final String CP_GuestInfo_Address1 = "//guest-info//span[contains(text(),'Gues')]/../..//label[text()='Address']/following-sibling::span/span[1]";
	public static final String CP_GuestInfo_Address2 = "//guest-info//span[contains(text(),'Gues')]/../..//label[text()='Address']/following-sibling::span/span[3]";
	public static final String CP_GuestInfo_Address3 = "//guest-info//span[contains(text(),'Gues')]/../..//label[text()='Address']/following-sibling::span/span[5]";

	public static final String Cp_AssociatedPolicies_DepositPolicy = "//div[text()='Policies And Disclaimers']/..//label[text()='Deposit Policy']/following-sibling::div//button";
	public static final String CP_AddNotes = "//a[text()='ADD NOTE ']";
	public static final String CP_AddTask = "//button[text()='ADD TASK']";

	public static final String CP_Reservation_Folio_Pay = "//button[text()='Pay']";
	public static final String CP_Reservation_Folio_TakePayment = "//h4[text()='Take Payment']";
	public static final String CP_Reservation_Folio_TakePayment_Amount = "//h4[text()='Take Payment']/../..//label[text()='AMOUNT']/following-sibling::div/input";
	public static final String CP_Reservation_Folio_TakePayment_TypeButton = "//h4[text()='Take Payment']/../..//label[text()='TYPE']/..//button";
	public static final String CP_Reservation_Folio_TakePayment_PaymentButton = "//h4[text()='Take Payment']/../..//label[text()='PAYMENT METHOD']/..//button";

	// CP Reservation Status Panel
	public static final String CP_Reservation_StatusPanel_DropDownBox = "//reservation-status//span[contains(@class,'ir-dropdown-span-status')]";
	public static final String CP_Reservation_PanelGuestProfileName = "//span[contains(@data-bind,'GuestProfileName')][@class='ir-textTrim']";
	public static final String CP_Reservation_PanelNoShowStatus = "//span[@class='ng-status']";
	public static final String CP_Reservation_PanelReservation = "//span[@class='ng-status']/following-sibling::span[2]";
	public static final String CP_Reservation_PanelTripTotal = "//reservation-status//td[contains(@data-bind,'tripTotal')]";
	public static final String CP_Reservation_PanelBalance = "//reservation-status//td/div[contains(@data-bind,'balance')]";

	// CP Reservation- Detail Tab POLICIESANDDISCLAIMERS
	public static final String CP_Reservation_POLICIESANDDISCLAIMERS_NoShowPolicyCollapse = "//div[contains(@class,'panel-default')]//a[contains(@data-bind,'click:cancelNoShow')]/i[1]";
	public static final String CP_Reservation_POLICIESANDDISCLAIMERS_NoShowPolicyExpand = "//div[contains(@class,'panel-default')]//a[contains(@data-bind,'click:cancelNoShow')]/i[2]";
	public static final String CP_Reservation_POLICIESANDDISCLAIMERS_NoShowPolicyExpandNoPolicy = "(//div[@class='panel-body']/div//div[contains(@class,'ir-frm-grp')]/div/button[@title='No Policy'])[2]/span[contains(@class,'pull-left')]";
	public static final String CP_Reservation_POLICIESANDDISCLAIMERS_NoShowPolicyLabel = "//label[contains(text(),'No show Policy')]";
	public static final String CP_Reservation_DisplayVoidItemCheckBox = "//input[contains(@data-bind,'DisplayVoidItems')]";
	public static final String CP_Reservation_DisplayVoidItemFolioImage3 = "//td/img[contains(@src,'./Folio_Images/3.gif')]";
	public static final String CP_Reservation_POLICIESANDDISCLAIMERS_CancellationPolicyLabel = "(//div[text()='Policies And Disclaimers']/..//label[text()='Cancellation Policy'])[1]";

	public static final String CP_Reservation_FolioDetailDropDownBox = "//button/span[contains(text(),'Guest Folio For')]";

	public static final String CP_Reservation_StayInfo_ThreeDotts = "(//div[@id='stayinfo'])[1]//div[contains(@class,'panel-body')]//div//button/span";
	// CP Reservation History Tab
	public static final String CP_Reservation_HistoryTab_NoShowDesc = "//span[contains(text(),'Marked this reservation as a no show')]|//span[contains(text(),'Cancelled this reservation')]";
	public static final String CP_Reservation_HistoryTab_MadeNoShowPayment = "//span[contains(text(),'Made a no show payment ')]";
	// CP Reservation Roll Back Button
	public static final String CP_Reservation_RolleBackButton = "//button[contains(text(),'Roll Back All')]|//button[contains(text(),'Roll Back')]";
	public static final String CP_Reservation_StayInfo_SecondaryGuestName = "(//div[contains(text(),'Stay')]/..//div//span[contains(@data-bind,'guestName')])[2]";
	public static final String CP_Reservation_GuestContactInforCollapse = "//span[contains(@class,'collapsed')]//span[@class='icon']";
	public static final String CP_Reservation_CheckInAllButton = "//reservation-status//button[contains(text(),'Check In All')]";
	public static final String CP_Reservation_CheckInButton = "//reservation-status//button[contains(text(),'Check In')]";
	public static final String CP_Reservation_ReservationStatusCheckOutAllButton = "//reservation-status//button[contains(text(),'Check Out All')]";
	public static final String CP_Reservation_ReservationStatusCheckOutButton = "//reservation-status//button[contains(text(),'Check Out')]";

	public static final String CP_Reservation_CheckIn_GuestRegistrationReportToggle = "//span[contains(@data-bind,'generateGuestRegistrationReport')]|//span[contains(@data-bind,'generateGuestStatement')]";
	public static final String CP_Reservation_CheckIn_GuestRegistrationReportToggleOff = "//span[contains(@data-bind,'generateGuestRegistrationReport')][contains(@class,'off')]|//span[contains(@data-bind,'generateGuestStatement')][contains(@class,'off')]";

	public static final String CP_Reservation_ConfirmCheckIn_Button = "//button[contains(text(),'CONFIRM CHECK IN')]";
	public static final String CP_Reservation_ConfirmCheckInPayment_Button = "//button[contains(text(),'PROCEED TO CHECK IN PAYMENT')]";

	public static final String CP_Reservation_ConfirmCheckOut_Button = "//div[contains(@data-bind,'checkOutDetail')]//div[contains(@data-bind,'checkOutDetail.showFooterWithCheckoutButton')]//button[contains(@data-bind,'checkOutDetail.onConfirmClick')]";
	public static final String CP_Reservation_CheckIn_ModelPopMsg = "//div[contains(text(),'One or more of the selected room(s) is Dirty. Woul')]";
	public static final String CP_Reservation_CheckIn_ModelPopCancelButton = "//button[contains(@class,'cp-confirmation-no')][contains(text(),'Cancel')]";
	public static final String CP_Reservation_CheckIn_ModelPopConfirmButton = "//button[contains(@class,'cp-confirmation-yes')][contains(text(),'Confirm')]";

	public static final String CP_Reservation_POLICIESANDDISCLAIMERS_CheckINPolicyCollapse = "//div[contains(@class,'panel-default')]//a[contains(@data-bind,'click:cancelCheckin')]/i[1]";
	public static final String CP_Reservation_POLICIESANDDISCLAIMERS_CheckInPolicyLabel = "//label[contains(text(),'Check-in Policy')]";
	public static final String CP_OverrideCheckInAmountCheckBox = "//div[contains(@data-bind,'IsPaymentOverrideAmountAllowed')]//span[@class='ir-check-box']";
	public static final String CP_CheckInTypeOptions = "//div[contains(@data-bind,'IsAuthTypeDropDownVisible')]//ul//li/a/span[@class='text']";
	public static final String CP_CheckInSuccessPrintButton = "//button[contains(@data-bind,'checkInDetail.printGuestRegistrationReport')]";
	public static final String CP_Reservation_CheckOutAllButton = "//button[contains(text(),'Check Out All')]";
	public static final String CP_Reservation_CheckOutButton = "//button[contains(text(),'Check Out')]";
	public static final String CP_Reservation_CheckInOkButton = "//div[@id='ir-cp-alert-modal']//div[@class='modal-footer']//button[contains(text(),'OK')]";

	public static final String CancelReservation_Reason = "//textarea[contains(@data-bind,'modal.cancelreason')]";
	public static final String CancelReservation_validationMsg = "//div[contains(@data-bind,'cancellationDetails')]//span[@class='validationMessage']";

	public static final String CheckIN_GuestContactInforCollapse = "//span[@class='accordion-panel collapsed']";
	public static final String CheckIN_EditGuest = "//a[@id='edit-guest']";

	public static final String NoShow_Cancel_CheckIn_CheckOut_Title = "//div[@role='document']//h4[contains(@data-bind,'noShow.modalTitle')]|//div[@role='document']//h4[contains(@data-bind,'cancel.modalTitle')]|//div[@role='document']//h4[contains(@data-bind,'checkInDetail.checkInTitle')]|//div[@role='document']//h4[contains(@data-bind,'checkOutDetail.checkOutTitle')]";
	public static final String NoShow_Cancel_CheckIn_CheckOut_GuestName = "//div[@role='document']//div/span[contains(@data-bind,'currentGuestName')]";
	public static final String NoShow_Cancel_CheckIn_CheckOut_ReservationNO = "//div[@role='document']//div/span[contains(@data-bind,'text: confirmationNumber')]|//div[@role='document']//div/span[contains(@data-bind,'text:confirmationNumber')]";
	public static final String NoShow_Cancel_CheckIn_CheckOut_Status = "//div[@role='document']//div/span[contains(@data-bind,'currentStatusName')]";
	public static final String VoidRoomChargesCheckBoxLabel = "//span[contains(text(),'Void Room Charges')]|//span[contains(text(),'Void room charges for unused nights')]";
	public static final String VoidRoomChargesCheckBox = "//span[contains(text(),'Void Room Charges')]/ancestor::div/label/span[1]|//span[contains(text(),'Void room charges for unused nights')]/ancestor::div/label/span[1]";
	public static final String NoShowAndCancelReservation_PolicyName = "//div[contains(@data-bind,'noshowCancellationDetails')]//span[@data-toggle='popover']|//div[contains(@data-bind,'cancellationDetails')]//span[contains(@data-bind,'popover')]";
	public static final String CheckInReservation_PolicyName = "//div[contains(@data-bind,'IsFromCheckOut')]//span[contains(@data-toggle,'popover')]";

	public static final String Reservation_PostCheckBoxLabel = "//span[contains(text(),'Post No-Show Fee')]|(//span[contains(text(),'Post Cancellation Fee')])[2]";
	public static final String ConfirmedNoShowButton = "//button[contains(text(),'CONFIRM NO SHOW')]|//button[contains(text(),'CONFIRM CANCELLATION')]";
	public static final String ProceedToPaymentButton = "//button[contains(text(),'PROCEED TO NO SHOW PAYMENT')]|//button[contains(text(),'PROCEED TO CANCELLATION PAYMENT')]";

	// No Show Payment page element
	public static final String NoShowPaymentPopupTitle = "//h4[contains(@data-bind,'PaymentPopupHeaderText()')]";
	public static final String NoShowPaymentPopup_Type = "//div[contains(@data-bind,'IsAuthTypeDropDownVisible')]//button/span[contains(@class,'pull-left')]";
	public static final String NoShowPaymentPopup_Amount = "//input[@id='payFormAmountV2']";
	public static final String NoShowCancelPaymentPopup_Balance = "(//div[@data-bind='visible:PaymentFormEditMode()']//span[contains(@data-bind,'PaymentFolioBalance')])[1]";
	public static final String CheckInPaymentPopup_Balance = "(//div[contains(@data-bind,'IsFromCheckIn')]//div//span[contains(@data-bind,'PaymentFolioBalance')])[1]";
	public static final String checkInPaymentPopupPolicyName = "//span[text()='Policy:']/..//span[@class='ir-popover-default ir-border-b hidden-sm hidden-xs ir-cursor-pointer']";

	public static final String NoShowPaymentPopup_CardNumber = "//input[contains(@class,'ir-frm-ctrl')][@placeholder='Credit Card Number']";
	public static final String NoShowPaymentPopup_NameOnCard = "//input[contains(@class,'ir-frm-ctrl')][@placeholder='Name On Card']";
	public static final String NoShowPaymentPopup_Expiry = "//input[contains(@class,'ir-frm-ctrl')][@placeholder='Expiry Date']";
	public static final String NoShowPaymentPopup_CVVCode = "//input[contains(@class,'ir-frm-ctrl')][@placeholder='CVV Code']";
	public static final String NoShowPaymentPopup_ExternalPaymentCheckBox = "//div[contains(@data-bind,'IsPaymentLogAsExternalVisibl')]//span[@class='ir-check-box']";
	public static final String NoShowPaymentPopup_Date = "//div[contains(@data-bind,'IsFromNoShow')]//input[contains(@class,'ir-padding-Calendar')]";
	public static final String NoShowPaymentPopup_Date_CalanderIcon = "//div[contains(@data-bind,'IsFromNoShow')]//i[@class='demo-icon icon-calendar leftIcon']";
	public static final String CheckInPaymentPopup_Date_CalanderIcon = "//div[contains(@data-bind,'IsFromCheckIn')]//i[@class='demo-icon icon-calendar leftIcon']";
	public static final String CheckOutPaymentPopup_Date_CalanderIcon = "//div[contains(@data-bind,'IsFromCheckOut')]//i[@class='demo-icon icon-calendar leftIcon']";

	public static final String NoShowPaymentPopup_SetMainPaymentCheckBox = "//div[contains(@data-bind,'IsPaymentSetAsMainMethodVisible')]//span[@class='ir-check-box']";
	public static final String NoShowPaymentPopup_SetMainPaymentLabel = "//div[contains(@data-bind,'IsPaymentSetAsMainMethodVisible')]//label[@class='ir-checkbox']";
	public static final String NoShowPaymentPopup_LogANDPayButton = "//button[contains(@data-bind,'IsPaymentFormValid')][contains(text(),'Log')]|//button[contains(@data-bind,'PaymentProcessButtonText')][contains(text(),'Pay')]|//button[contains(text(),'Authorize')]|//button[contains(text(),'Refund')]";
	public static final String NoShowPaymentPopup_CloseButton = "//div[@class='modal-dialog modal-lg']//button[contains(@class,'close-modal hidden')]";
	public static final String NoShowPaymentPopup_Msg = "//div[@class='ir-custom-modal-body modal-body']";
	public static final String NoShowPaymentPopup_YesButton = "//div[@class='ir-custom-modal']//button[contains(text(),'YES')]";
	public static final String NoShowPaymentPopup_NoButton = "//div[@class='ir-custom-modal']//button[contains(text(),'NO')]";
	public static final String NoShowPaymentAddNoteLink = "//button[@type='button'][contains(text(),'ADD NOTES')]";
	public static final String NoShowPaymentAddNoteTextBox = "//input[@placeholder='Add Notes']";

	// No Show Successful page element
	public static final String NoShowSuccess_ModelHeader = "//div[contains(@class,'modal-content')]//h4[contains(@data-bind,'noShow.modelSuccessTitle')]|//div[contains(@class,'modal-content')]//h4[contains(@data-bind,'cancel.cancelStatusTitle')]|//div[contains(@class,'modal-content')]//h4[contains(@data-bind,'checkInDetail')]|//div[contains(@class,'modal-content')]//h4[contains(@data-bind,'checkOutDetail.checkOutStatusTitle')]";
	public static final String NoShowSuccess_Date = "//span[contains(@data-bind,'noShow.PaymentDate')]|//span[contains(@data-bind,'cancel.PaymentDate')]|//span[contains(@data-bind,'checkInDetail.PaymentDate')]|//span[contains(@data-bind,'checkOutDetail.PaymentDate')]";
	public static final String NoShowSuccess_Balance = "(//span[contains(@data-bind,'noShow.PaymentFolioBalance')])[2]|(//span[contains(@data-bind,'cancel.PaymentFolioBalance')])[2]|(//span[contains(@data-bind,'checkInDetail.PaymentFolioBalance')])[2]|//span[contains(@data-bind,'checkOutDetail.PaymentFolioBalance')]";
	public static final String NoShowSuccess_Type = "//span[contains(@data-bind,'noShow.PaymentAuthTypeText')]|//span[contains(@data-bind,'cancel.PaymentType')]|//span[contains(@data-bind,'checkInDetail.PaymentAuthTypeText')]|//span[contains(@data-bind,'text : checkOutDetail.PaymentType')]";
	public static final String NoShowSuccess_PaymentMethod = "//h4[contains(@data-bind,'noShow.modelSuccessTitle')]/ancestor::div/following-sibling::div//span[contains(@data-bind,'noShow.PaymentInformation')]|//span[contains(@data-bind,'cancel.PaymentInformation')]|//span[contains(@data-bind,'checkInDetail.PaymentInformation')]|//span[contains(@data-bind,'checkOutDetail.PaymentInformation')]";
	public static final String NoShowSuccess_PaymentToolTip = "//h4[contains(@data-bind,'noShow.modelSuccessTitle')]/ancestor::div/following-sibling::div//label[contains(@class,'ir-textTrim')]";
	public static final String NoShowSuccess_Status = "//h4[contains(@data-bind,'noShow.modelSuccessTitle')]/ancestor::div/following-sibling::div//label[contains(@data-bind,'noShow.PaymentStatus')]|//label[contains(@data-bind,'cancel.PaymentStatus')]|//label[contains(@data-bind,'checkInDetail.PaymentStatus')]|//label[contains(@data-bind,'checkOutDetail.PaymentStatus')]";
	public static final String NoShowSuccess_Notes = "//h4[contains(@data-bind,'noShow.modelSuccessTitle')]/ancestor::div/following-sibling::div//span[contains(@data-bind,'noShow.PaymentNotes')]|//span[contains(@data-bind,'cancel.PaymentNotes')]|//span[contains(@data-bind,'checkInDetail.PaymentNotes')]|//span[contains(@data-bind,'checkOutDetail.PaymentNotes')]";
	public static final String NoShowSuccess_CloseButton = "//h4[contains(@data-bind,'noShow.modelSuccessTitle')]/ancestor::div/following-sibling::div/div//button|//button[contains(@data-bind,'cancel.closeModal')]|//button[contains(@data-bind,'checkInDetail.closeModal')]|//button[contains(@data-bind,'checkOutDetail.closeModal')]|//button[contains(@data-bind,'noShow.isFinalStep')][contains(text(),'Close')]";
	public static final String noShowSuccessPolicyName = "//span[text()='Policy: ']/..//span[@class='ir-popover-default hidden-sm hidden-xs ir-cursor-pointer ir-border-b']";

	// Perform Confirm Now Show -
	public static final String ConfirmDialog_Message = "(//div[contains(@class,'cp-confirmation-dialog')]//div[@class='modal-content']//div[@class='modal-body'])[2]";
	public static final String ConfirmDialog_NoButton = "//button[contains(text(),'No, Cancel the no show process')]";
	public static final String ConfirmDialog_YesButton = "//button[contains(text(),'Yes, Confirm no show & refund later')]";
	public static final String NoShowSuccessFul_ModelHeader = "//h4[contains(@class,'ir-mp-headTitle')][contains(text(),'No Show Successful')]";
	public static final String NoShowSuccessFul_Msg = "//span[contains(text(),'No Show successful')]";
	public static final String NoShowSuccessFul_Reg = "//h4[contains(@class,'ir-mp-headTitle')][contains(text(),'Successful')]/ancestor::div/following-sibling::div//span[contains(@data-bind,'text:confirmationNumber')]";
	public static final String NoShowSuccessFul_CloseButton = "//button[contains(@data-bind,'noShow.isFinalStep')][contains(text(),'Close')]";

	public static final String CancelRoom_ToolTip_PolicyName = "//div[@role='tooltip']/h3[@class='popover-title']";
	public static final String CancelRoom_ToolTip_PolicyDescription = "//div[@role='tooltip']//div[@class='popover-content']";

	public static final String CP_Reservation_StayInfo_CheckInButton = "//div[contains(@class,'ir-stayInfo-details')]//button[contains(text(),'Check In')]";
	public static final String CP_Reservation_StayInfo_CheckOutButton = "//div[contains(@class,'ir-stayInfo-details')]//button[contains(text(),'Check Out')]";
	public static final String CP_Reservation_StayInfo_TotalButton = "//div[contains(@class,'ir-stayInfo-details')]//button[contains(@data-bind,'onStatusActionBtnClick')]";

	public static final String Cp_Reservation_PaymentDetail_CanceButton = "//div[@id='ReservationPaymetItemDetail']/..//div/button[contains(@data-bind,'CloseButtonClick')][contains(text(),'Cancel')]";

	public static final String Add_LineItem = "//button[contains(@data-bind,'fnAddFolioItem')]";
	public static final String Select_LineItem_Category = "//select[contains(@data-bind,'LedgerAccountItems')]";
	public static final String Enter_LineItem_Amount = "//td[@class='lineitem-amount']/input";
	public static final String Commit_LineItem = "//button[.='Commit']";

	public static final String CP_Reservation_CheckOutAllButton_ConfirmationMsg = "//div[@class='modal-scrollable']//div[@class='modal-body']";
	public static final String CP_Reservation_CheckOutAllButton_ConfirmationMsgYesButton = "//div[@class='modal-scrollable']//button[contains(text(),'Yes')]";
	public static final String CP_Reservation_CheckOutAllButton_ConfirmationMsgNoButton = "//div[@class='modal-scrollable']//button[contains(text(),'No')]";
	public static final String CP_Reservation_CheckOut_Message = "//div[@class='modal-scrollable']//div[@class='modal-body']//div[contains(@class,'ng-padTopBottom')]/div/div[contains(@class,'text-center')]";
	public static final String CP_Reservation_CheckINUassigned = "//ir-stay-info-checkin//div[contains(@class,'ir-roomInfo')]//span[contains(@class,'pull-left')][contains(text(),'Unassigned')]";
	public static final String CP_Reservation_CheckINUassignedTosterMsg = "//div[@class='toast-message'][contains(text(),'Please select a room for the reservation')]";
	public static final String CP_Reservation_CheckDropDownOpenRooms = "//ir-stay-info-checkin//div[contains(@class,'ir-roomInfo')]//div[contains(@class,'open')]/div[contains(@class,'dropdown-menu open')]//ul/li[@class='selected']/following-sibling::li/a/span[@class='text']";
	public static final String CP_Reservation_CheckInFirstDropDownPath = "(//ir-stay-info-checkin//div[@class='ir-flex-grow ir-roomInfo']//button//span[@class='filter-option pull-left'])[1]";
	public static final String CP_Reservation_CheckInDropDownBox = "//ir-stay-info-checkin//div[contains(@class,'ir-roomInfo')]//button";
	public static final String EnterCheckinDate = "//label[text()='Check In']//..//..//div//input";
	public static final String EnterCheckout = "//label[text()='Check Out']//..//..//div//input";
	public static final String CheckboxGuestProfile = "(//span[text()='Create Guest Profile']//..//span)[1]";
	public static final String CheckInPopupHeading = "//h4[text()='Check In']";
	public static final String CheckinConfirmButton = "//button[contains(@data-bind,'click: checkInDetail.confirmCheckInClick')]";
	public static final String VerifyDirtyRoomPopup = "//div[text()='The room status is dirty. Do you wish to proceed with check in?']";
	public static final String ButtonDirtyConfirm = "//div[text()='The room status is dirty. Do you wish to proceed with check in?']//..//button[text()='Confirm']";

	public static final String ReservationStatus = "//div[contains(@class,'reservation-details-container')]//span[@class='ng-status']";
	public static final String ButtonAddIncidental = "//a[@data-target='#incidentals']";
	public static final String EnterAddOn_IncidenalDate = "//div[text()='ADD-ONS & INCIDENTALS']//..//input[contains(@data-bind,'datePickerCalendar')]";
	public static final String ButtonSelectIncidental = "//div[text()='ADD-ONS & INCIDENTALS']//..//button[@title='--Select--']";
	public static final String EnterPerUnit = "//div[text()='ADD-ONS & INCIDENTALS']//..//input[contains(@data-bind,'value: newRow.perUnit')]";
	public static final String EnterQuentity = "//div[text()='ADD-ONS & INCIDENTALS']//..//input[contains(@data-bind,'value: newRow.quantity')]";
	public static final String GetIncidentalDate = "//div[text()='ADD-ONS & INCIDENTALS']//..//td[@data-bind='text:effectiveOn']";
	public static final String GetIncidentalCategory = "//div[text()='ADD-ONS & INCIDENTALS']//..//td[@data-bind='text:category']";
	public static final String GetIncidentalDescription = "//div[text()='ADD-ONS & INCIDENTALS']//..//td[@data-bind='text:description']";
	public static final String GetIncidentalPerUnit = "//div[text()='ADD-ONS & INCIDENTALS']//..//td[contains(@data-bind,'value: perUnitPrice')]";
	public static final String GetIncidentalQuentity = "//div[text()='ADD-ONS & INCIDENTALS']//..//td[contains(@data-bind,'text: quantity')]";
	public static final String GetIncidentalTotalAmount = "//div[text()='ADD-ONS & INCIDENTALS']//..//td[contains(@data-bind,'value: chargeSummary.totalAmount')]";
	public static final String GetIncidentalTax = "//div[text()='ADD-ONS & INCIDENTALS']//..//td[contains(@data-bind,'value: chargeSummary.totalTaxAmount')]";
	public static final String IncidentalSave = "//div[text()='ADD-ONS & INCIDENTALS']//..//button[contains(@data-bind,'enable: saveEnabled')]";
	public static final String HistoryTab = "//a[text()='History']";
	public static final String History_Category = "//span[contains(@data-bind,'text:$data.category')]";
	public static final String History_Date = "//span[contains(@data-bind,'text:$parent.formatDate')]";
	public static final String History_Time = "//span[contains(@data-bind,'text:$parent.formatTime')]";
	public static final String History_User = "//span[contains(@data-bind,'text:$data.user')]";
	public static final String History_Description = "//span[contains(@data-bind,'text:$data.description')]";
	public static final String DetailsTab = "//a[text()='Details']";
	public static final String TotalNumberofIncidentalRows = "//div[text()='ADD-ONS & INCIDENTALS']//..//tr";
	public static final String DeleteIncidentalInSatyInfoButton = "//div[text()='ADD-ONS & INCIDENTALS']//..//a[contains(@data-bind,'click: $parent.bindVoidFolioItem')]";
	public static final String VoidPopupHeading = "(//*[contains(text(),'Void Items')])[2]";
	public static final String EnterNotes = "//textarea[contains(@data-bind,'value: voidItemNote')]";

	public static final String RollBackPopupMsg = "//div[@class='modal-scrollable']//div[@class='modal-body']";
	public static final String RollBacktPopupYesButton = "//div[@class='modal-scrollable']//button[contains(text(),'Yes')]";
	public static final String RollBackPopupNoButton = "//div[@class='modal-scrollable']//button[contains(text(),'No')]";
	public static final String RollBackPopupCloseButton = "//div[@class='modal-scrollable']//button[contains(@class,'close')]";
	public static final String Spinner = "//div[contains(@data-bind,'showLoading')]/div[@class='ir-loader-in']";

	public static final String Folio_RoomCharges = "//label[.='Room Charges: ']//following-sibling::span[@class='pamt']";
	public static final String Folio_TaxServices = "//label[.='Taxes & Service Charges:']//following-sibling::span/span[@class='pamt']";

	public static final String VoidButton = "//button[contains(@data-bind,'click: $parent.voidAddOn')]";

	public static final String StayInfo_Editbutton = "//button[contains(@data-bind,'onStayInfoEdit:function')]";
	public static final String StayInfo_ChangeDetails = "//li[contains(text(),'Change Stay Details')]";

	public static final String Checkout_CompleteCheckOutMsg = "//div[@class='modal-dialog modal-md']//div[@class='modal-body'][contains(text(),'completing check-out')]";
	public static final String Checkout_CompleteCheckOutMsgYesButton = "//button[contains(text(),'Yes, take me to the Folio')]";
	public static final String Checkout_CompleteCheckOutMsgNoButton = "//button[contains(text(),'No, cancel the check-out process')]";

	public static final String EnterManualRate = "//label[.='Rate Amount']//preceding-sibling::input";

	public static final String ClickGoBackToStayInfo = "//span[contains(@data-bind,'clickGoBackToStayInfo')]/i";
	public static final String NewPolicesApplicableYesBtn = "//p[contains(text(),'new policies are applicable')]/../..//button[text()='Yes']";
	public static final String PrimaryGuestFirstName = "//span[text()='Primary Room']/../../../../../..//label[text()='Guest ']/preceding-sibling::input[contains(@data-bind,'firstName')]";
	public static final String PrimaryGuestLastName = "//span[text()='Primary Room']/../../../../../..//label[text()='Guest ']/../../../../following-sibling::div//input[contains(@data-bind,'lastName')]";
	public static final String AdditonalGuest1FirstName = "//span[text()='Additional Room']/../../../../../..//span[text()='Guest  1']/../../..//input[contains(@data-bind,'FirstName')]";
	public static final String AdditonalGuest1LastName = "//span[text()='Additional Room']/../../../../../..//span[text()='Guest  1']/../../..//input[contains(@data-bind,'LastName')]";

	public static final String HeaderAccountName_AfterReservation = "//div[@data-bind='css:ReservationStatusDisplay']//a[contains(@data-bind,'AccountName')]";
	public static final String HeaderAccountName_BeforeReservation = "//a[contains(@data-bind,'click: GetAccountDetail, value:accountName, text:accountName')]";
	// Reseration Detail Info for Created CP Reservation
	public static final String GuestName_GuestInfo = "//div[contains(@data-bind,'with:GuestInfo')]//span[contains(@data-bind,'GuestProfileName')]";
	public static final String ContactName_GuestInfo = "//div[contains(@data-bind,'with:GuestInfo')]//span[contains(@data-bind,'ContactProfileName')]";
	public static final String AddressLine1_GuestInfo = "//div[contains(@data-bind,'with:mailingAddress')]//span[contains(@data-bind,'text:address1')]";
	public static final String AddressLine2_GuestInfo = "//div[contains(@data-bind,'with:mailingAddress')]//span[contains(@data-bind,'text:address2')]";
	public static final String AddressLine3_GuestInfo = "//div[contains(@data-bind,'with:mailingAddress')]//span[contains(@data-bind,'text:address3')]";
	public static final String City_GuestInfo = "//div[contains(@data-bind,'with:mailingAddress')]//span[contains(@data-bind,'city')]";
	public static final String Contry_GuestInfo = "//div[contains(@data-bind,'with:mailingAddress')]//span[contains(@data-bind,'countryId')]";
	public static final String State_GuestInfo = "//div[contains(@data-bind,'with:mailingAddress')]//span[contains(@data-bind,'stateName')]";
	public static final String PostalCode_GuestInfo = "//div[contains(@data-bind,'with:mailingAddress')]//span[contains(@data-bind,'postalCode')]";
	public static final String Email_GuestInfo = "//div[contains(@data-bind,'with:GuestInfo')]//span[contains(@data-bind,'emailAddress')]";
	public static final String PhoneNo_GuestInfo = "//div[contains(@data-bind,'with:GuestInfo')]//span[contains(@data-bind,'mailing_formattedPhoneNumber')]";

	public static final String AlternativePhoneNo_GuestInfo = "//div[contains(@data-bind,'with:GuestInfo')]//span[contains(@data-bind,'mailing_formattedAlternativePhoneNumber')]";
	public static final String MarketSegment_GuestInfo = "//span[contains(@data-bind,'text:marketSegment')]";
	public static final String Referral_GuestInfo = "//span[contains(@data-bind,'text:referral')]";
	public static final String PaymentMethod_PayInfo = "//div[contains(@data-bind,'PaymentDetail')]//span[contains(@data-bind,'methodName')]";
	public static final String CardNo_PayInfo = "//div[contains(@data-bind,'PaymentDetail')]//span[contains(@data-bind,'text: PaymentDetail().creditCard.displayNumber')]";
	public static final String NameOnCard_PayInfo = "//div[contains(@data-bind,'PaymentDetail')]//span[contains(@data-bind,'cardHolderName')]";
	public static final String CardExpiry_PayInfo = "//div[contains(@data-bind,'PaymentDetail')]//span[contains(@data-bind,'expiresOn')]";

	public static final String tripTotalAmountAtTop = "//td[contains(@data-bind,'tripTotal')]";
	public static final String tripBalanceAmountAtTop = "//td/div[contains(@data-bind,'balance')]";

	public static final String checkInPaymntPopupCheckInPolicy = "//span[@class='ir-popover-default ir-border-b hidden-sm hidden-xs ir-cursor-pointer']";
	public static final String checkInPaymntPopupAmount = "//input[@id='payFormAmountV2']";
	public static final String checkInPaymntPopupType = "//label[text()='TYPE']/../..//button";
	public static final String checkInPaymntPopupBalance = "//div[contains(text(),'Balance')]//span[contains(@data-bind,'showInnroadCurrency')]";

	public static final String folioHistoryAuthPaymentDesc = "//span[contains(text(),'Authorized payment for')]";
	public static final String folioHistoryTabSearchBox = "//input[@id='txtHistorySearch']";
	public static final String folioHistoryCapturePaymentDesc = "//span[contains(text(),'Captured payment for')]";
	public static final String folioHistoryAuthPaymentCategory = "//span[contains(text(),'Authorized payment for')]/../..//td//span[@class='label label-default ir-bg-payment']";
	public static final String folioHistoryCapturePaymentCategory = "//span[contains(text(),'Captured payment for')]/../..//td//span[@class='label label-default ir-bg-payment']";
	public static final String roomNoDDAtCheckInPopup = "//span[contains(text(),'ROOM:')]/..//span[@class='filter-option pull-left']";

	public static final String checkInPolicyName = "//div[text()='Policies And Disclaimers']/..//label[text()='Check-in Policy']/following-sibling::div//button/span";
	public static final String checkInPolicyToolTip = "//div[text()='Policies And Disclaimers']/..//label[text()='Check-in Policy']/following-sibling::div//button";
	public static final String checkInPolicyText = "//textarea[@data-bind='text:policyStatementCheckin, disable:true']";
	public static final String paymentCheckInPopupAuthTypeReadOnly = "//button[@class='btn dropdown-toggle disabled btn-default'][@title='Authorization Only']";
	public static final String paymentCheckInPopupAuthTypeEditable = "//button[@class='btn dropdown-toggle btn-default'][@title='Authorization Only']";

	public static final String CP_Reservation_Open_Reservation = "//a[contains(@data-bind,'click: $parent.SelectedReservation')][1]";
	public static final String CP_ClickStayInfoEdit = "//button[@class='ir-iconCircle btn ir-btn ir-no-space pull-right']";
	public static final String CP_ClickAssignRoomNumber = "//ul//li[contains(text(),'Assign  room Number')]";
	public static final String CP_SelectResRoom = "//div[contains(@class,'ir-flex-grow ir-flex-grow-3 ir-flex-mb-grow-3')]//div//div//ul//li//a";
	public static final String CP_SaveRoomNo = "//button[contains(@class,'btn btn-success text-uppercase')]";
	public static final String CP_VerifyChangeCostPopup = "//div[contains(text(),'Are you sure you wish to change the total cost of the stay from')]";
	public static final String CP_VerifyFolioDropDownItem = "//a/span[contains(text(),'Guest Folio For')]";
	public static final String CP_VerifyPolicyChangePopup = "//div//p[contains(text(),'Would you like to update the reservation to the new policies?')]";

	public static final String CP_Reservation_PaymentMethod_Section = "((//span[contains(text(), 'PAYMENT') ]//following-sibling::span[contains(text(), 'Method') ])[1]//..//..)[1]";
	public static final String CP_Reservation_PaymentMethod = "//label[.='PAYMENT METHOD']//following-sibling::span";
	public static final String CP_RESERVATION_CC_Number = "//span[contains(text(), 'CARD')]//parent::label//following::span[2]//following-sibling::span";
	public static final String CP_Reservation_CardHolder_Name = "//label[.='NAME ']//following-sibling::span";
	public static final String CP_Reservation_Card_ExpiryDate = "//label[.='EXP.DATE']//following-sibling::span";

	public static final String CP_NewReservation_SameAsMailingAddressCheckBox = "//label//following-sibling::input[@data-bind='checked: PaymentDetail().useMailingInfo']";
	public static final String CP_NewReservation_BillingSalutation = "//label[text()='First Name']/preceding-sibling::input//parent::div//preceding-sibling::div//button";
	public static final String CP_NewReservation_BillingFirstName = "//label[text()='First Name']/preceding-sibling::input";
	public static final String CP_NewReservation_BillingLastName = "(//input[contains(@data-bind,'value: lastName')])[2]";
	public static final String CP_NewReservation_BillingAddress1 = "(//label[text()='Address 1']/preceding-sibling::input)[2]";
	public static final String CP_NewReservation_BillingAddress2 = "(//label[text()='Address 2']/preceding-sibling::input)[2]";
	public static final String CP_NewReservation_BillingAddress3 = "(//label[text()='Address 3']/preceding-sibling::input)[2]";
	public static final String CP_NewReservation_BillingCity = "(//label[text()='City']/preceding-sibling::input)[2]";
	public static final String CP_NewReservation_BillingPostalCode = "(//label[contains(text(),'Postal Code')])[4]/preceding-sibling::input";
	public static final String CP_NewReservation_BillingCountry = "(//label[text()='Country'])[4]//preceding::button[1]//span[2]";
	public static final String CP_NewReservation_BillingState = "(//label[text()='state'])[1]//preceding::button[1]//span[2]";

	public static final String COPY_BUTTON = "//div[contains(@data-bind,'visible: isStayInfoLoaded()')]//button[contains(text(),'COPY')]";
	public static final String COPY_RESERVATION_TRIMNAME = "//span[contains(@data-bind,'trimText: ValueText') and contains(text(),'copy')]";

	public static final String COPY_CP_GuestInfo_GuestProfile = "(//input[contains(@data-bind,'value: $parent.GuestProfileName')])[2]";
	public static final String COPY_CP_GuestInfo_Guest_Salutation = "(//button[@type='button']//span[contains(@data-bind,'text: salutation')])[2]";
	public static final String COPY_CP_GuestInfo_FirstName = "(//div[contains(@data-bind,'validationOptions: guestValidationRules')]//input[contains(@data-bind,'value: firstName')])[3]";
	public static final String COPY_CP_GuestInfo_LastName = "(//div[contains(@data-bind,'validationOptions: guestValidationRules')]//input[contains(@data-bind,'value: lastName')])[3]";
	public static final String COPY_CP_ContactInfo_Salutation = "(//div[contains(@data-bind,'validationOptions: guestValidationRules')]//span[contains(@data-bind,'text: $parent.mailingAddress.contact.salutation')])[2]";
	public static final String COPY_CP_ContactInfo_FirstName = "(//div[contains(@data-bind,'validationOptions: guestValidationRules')]//input[contains(@data-bind,'value: $parent.mailingAddress.contact.firstName')])[2]";
	public static final String COPY_CP_ContactInfo_LastName = "(//div[contains(@data-bind,'validationOptions: guestValidationRules')]//input[contains(@data-bind,'value: $parent.mailingAddress.contact.lastName')])[2]";
	public static final String COPY_CP_ContactInfo_Email = "(//div[contains(@data-bind,'validationOptions: guestValidationRules')]//input[contains(@data-bind,'textInput: $parent.mailingAddress.email')])[2]";
	public static final String COPY_CP_ContactInfo_Phone = "(//div[contains(@data-bind,'validationOptions: guestValidationRules')]//input[contains(@data-bind,'value:$parent.mailingAddress.phone.number')])[2]";
	public static final String COPY_CP_ContactInfo_AlternativePhone = "(//div[contains(@data-bind,'validationOptions: guestValidationRules')]//input[contains(@data-bind,'value: $parent.mailingAddress.alternativePhone.number')])[2]";
	public static final String COPY_CP_MailingInfo_Address1 = "(//div[contains(@data-bind,'validationOptions: guestValidationRules')]//input[contains(@data-bind,'value: address1')])[3]";
	public static final String COPY_CP_MailingInfo_Address2 = "(//input[contains(@data-bind,'value:address2')])[2]";
	public static final String COPY_CP_MailingInfo_Address3 = "(//input[contains(@data-bind,'value:address3')])[2]";
	public static final String COPY_CP_MailingInfo_City = "(//input[contains(@data-bind,'value: city')])[3]";
	public static final String COPY_CP_MailingInfo_Country = "(//select[@name='country'])[3]/following-sibling::div//button//span[1]";
	public static final String COPY_CP_MailingInfo_State = "(//select[@name='state'])[3]/following-sibling::div//button//span[1]";
	public static final String COPY_CP_MailingInfo_PostalCode = "(//div[contains(@data-bind,'validationOptions: guestValidationRules')]//input[contains(@data-bind,'value: postalCode')])[4]";

	public static final String COPY_CP_BillingInfo_PaymentMethod = "((//select[contains(@data-bind,'selectPicker: methodId')])[2]/following-sibling::div)//i[@class='glyphicon ir-mastercard-icon']/parent::span[1]";
	public static final String COPY_CP_BillingInfo_CreditCardNumber = "(//input[contains(@data-bind,'value: creditCardNumber')])[2]";
	public static final String COPY_CP_BillingInfo_NameOnCard = "(//div[contains(@data-bind,'validationOptions: guestValidationRules')]//input[contains(@data-bind,'value:nameOnCreditCard')])[2]";
	public static final String COPY_CP_BillingInfo_ExpDate = "(//div[contains(@data-bind,'validationOptions: guestValidationRules')]//input[contains(@data-bind,'value:creditCardExpDate')])[4]";
	public static final String COPY_CP_NewReservation_TaskNotCopy = "//div[text()='There are no Tasks created on this reservation']";
	public static final String COPY_CP_RoomClassName = "//div[contains(@data-bind,'text: roomClass.name')]";
	public static final String COPY_CP_NewReservation_Folio = "(//li[contains(@data-bind,'Folio')]//a[text()='Folio(s)']/..)[2]";
	public static final String CancelReservation_CofirmCancellation = "//button[contains(@data-bind,'click: cancel.onConfirmCancelClick')]";

	public static final String HeaderGuestName_AfterReservation = "//div[@data-bind='css:ReservationStatusDisplay']//span[contains(@data-bind,'GuestProfileName')]";
	public static final String HeaderStatus_AfterReservation = "//div[@data-bind='css:ReservationStatusDisplay']//span[contains(@data-bind,'text:reservationViewStatuses.titleLineStatus')]";
	public static final String HeaderConfirmationNo = "//div[@data-bind='css:ReservationStatusDisplay']//span[contains(@data-bind,'text:confirmationNumber')]";
	public static final String HeaderStayDate = "//div[@data-bind='css:ReservationStatusDisplay']//span[contains(@data-bind,'text: stayInfo.desktopCheckInOut()')]";
	public static final String TripSummary_RoomCharge = "//span[contains(text(),'Trip')]/../..//div[contains(@data-bind,'roomChargeTotal')]";
	public static final String TripSummary_Incidentals = "//span[contains(text(),'Trip')]/../..//div[contains(@data-bind,'addOnTotal')]";
	public static final String TripSummary_TaxesAndServiceCharges = "//span[contains(text(),'Trip')]/../..//div[contains(@data-bind,'taxesTotal')]";
	public static final String TripSummary_TripTotal = "//span[contains(text(),'Trip')]/../..//div[contains(@data-bind,'tripTotal')]";
	public static final String TripSummary_Payment = "//span[contains(text(),'Trip')]/../..//div[contains(@data-bind,'paymentsTotal')]";
	public static final String TripSummary_Balance = "//span[contains(text(),'Trip')]/../..//div[contains(@data-bind,'balance')]";
	public static final String SaveQuote = "(//button[text()='SAVE QUOTE'])[1]";
	public static final String getRoomChargesInTripSummary = "(//div[text()='Room Charges']//following-sibling::div)[1]";
	public static final String getIncidentalsInTripSummary = "//span[text()='Summary']/../../..//div[text()='Incidentals']/following-sibling::div";
	public static final String getTaxandServicesInTripSummary = "(//div[text()='Taxes & Service Charges']//following-sibling::div)[1]";
	public static final String getTripTotalInTripSummary = "(//div[text()='Trip Total']//following-sibling::div)[1]";
	public static final String getTotalBalanceInTripSummary = "(//div[text()='Balance']//following-sibling::div)[1]";
	public static final String getPaidInTripSummary = "(//div[text()='Paid']//following-sibling::div)[1]";

	public static final String ClickOnCheckBoxProperty = "(//input[contains(@data-bind,'enable: EnableSelectAll')])[1]/..";
	public static final String ResBulkCanUpdatedTab = "(//span[contains(@data-bind,'text: ProcessedList().length')]/..)[1]";
	public static final String ResBulkCanNotUpdatedTab = "(//span[contains(@data-bind,'text: UnProcessedList().length')]/..)[1]";
	public static final String ClickProcessButtonBulkCheckIN = "//h4[contains(text(),'Bulk Check In')]/../..//button[text()='Process']";
	public static final String Verify_resNumber1BulkCheckInCanNotUpdate = "(//div[@id='tab2']//span[contains(@data-bind,'text: ConfirmationNumber')])[1]";
	public static final String VerifyBulkCheckInCompletePopUp = "//h4[.='Bulk Check In Completed']";
	public static final String ClickResCanNotUpdateAfterProcess = "(//div[@class='panel-group']//..//following-sibling::a)[2]";
	public static final String CLOSE_BULK_ACTION_POPUP = "(//div[@class='ng-modal-content']//following-sibling::div//button[contains(text(),'Close')])[2]";
	public static final String ClickProcessButtonBulkCheckOut = "//h4[contains(text(),'Bulk Check')]/../..//button[text()='Process']";
	public static final String VerifyBulkCheckOutCompletePopUp = "//h4[.='Bulk Checkout Completed']";
	public static final String ClickResCanUpdateAfterProcess = "(//div[@class='panel-group']//..//following-sibling::a)[1]";

	public static final String selectRoomNumberInMRB = "//div[contains(@data-bind,'validationOptions')]//button[@title='--Select Room--']";
	public static final String checkboxCopyGuest = "(//span[text()='Copy Guest 1 Info from Primary Room']//..//span)[1]";
	public static final String historyRoom = "//span[contains(@data-bind,'text:$data.room')]";
	public static final String incidentalInTS = "(//div[contains(@data-bind,'value: addOnTotal')])[1]";
	public static final String ADDONSRoomButton = "//h4[text()='ADD-ONS']//..//..//span[@class='bs-caret']";
	public static final String ADDONSSelectedRoom = "(//h4[text()='ADD-ONS']//..//..//button//span)[1]";
	public static final String GetIncidentalRoomNumber = "//div[text()='ADD-ONS & INCIDENTALS']//..//td[contains(@data-bind,'text: roomNumber')]";
	public static final String ClickOnAddRooms = "//button[contains(@data-bind,'click: onAddRoomClick')]";

	public static final String addOnsButton = "(//a[contains(text(),'ADD-ONS')])[1]";
	public static final String ADDONSHeading = "//h4[.='ADD-ONS']";
	public static final String AddONSSearchInput = "//h4[text()='ADD-ONS']//..//..//input[contains(@data-bind,'searchingFlag: stoppedSearching')]";
	public static final String AddONSSearchButtton = "//h4[text()='ADD-ONS']//..//..//button[text()='Save']";
	public static final String ADDONSSaveButton = "//h4[text()='ADD-ONS']//..//..//button[text()='Save']";
	public static final String AddONSAmount = "//span[contains(@class,'addOn-rate')]";
	public static final String ADDONSQuentityInput = "//input[contains(@data-bind,'value: Quantity')]";
	public static final String ADDONSPlus = "//h4[text()='ADD-ONS']//..//..//span[text()='+']";
	public static final String detailsTab = "//a[text()='Details']";

	public static final String deleteIncidentalInSatyInfoButton = "//div[text()='ADD-ONS & INCIDENTALS']//..//a[contains(@data-bind,'click: $parent.bindVoidFolioItem')]";
	public static final String voidPopupHeading = "(//*[contains(text(),'Void Items')])[2]";
	public static final String enterNotes = "//textarea[contains(@data-bind,'value: voidItemNote')]";
	public static final String voidButton = "//button[contains(@data-bind,'click: $parent.voidAddOn')]";
	public static final String LoadingModule = "(//div[@class='ir-loader-in'])[2]";
	public static final String addARoom = "//button[contains(@data-bind,'click: onAddRoomClick')]";

	public static final String GetTotalTaxBeforeSaveRes = "(//div[@class='panel-body'])[1]/div/div/span[text()='Taxes & Service Charges']/ancestor::div/following-sibling::div[starts-with(.,'$')]";
	public static final String GetTotalTripBeforeSaveRes = "(//div[@class='panel-body'])[1]/div/div/span[text()='Trip Total']/ancestor::div/following-sibling::div[starts-with(.,'$')]";
	public static final String GetRoomChrgesBeforeSaveRes = "(//div[@class='panel-body'])[1]/div/div/div/div/span/ancestor::div/span[text()='Room Charges']/ancestor::div/following-sibling::div[starts-with(.,'$')]";
	public static final String GetRoomChargeFolioCount = "(//tbody[starts-with(@data-bind,'getElement: ComputedFolioItemsElement')]/tr/td/span[text()='Room Charge']/../following-sibling::td/a)";
	public static final String GetTaxTotalinChildFolioItem = "//table[@border='0']/tbody/tr/td/span[text()='Taxes & Service Charges']/ancestor::td/following-sibling::td/span";
	public static final String ClickFolioPopupCancel = "(//div[@class='modal-footer']/div/div[@class='col-md-5 text-left user_updated_panel_popup']/following-sibling::div/button[text()='Cancel'])[2]";
	public static final String ItemDetailOnFolioPopup = "//div[@class='modal-header']/h4[@class='modal-title' and text()='Item Detail']";
	public static final String RateFloorYes = "(//input[@name='ctl00$MainContent$grpLowestRate'])[1]";
	public static final String GetTaxFolioItems = "//table[@class='table table-striped table-bordered table-hover popGrdFx']/tbody/tr/td[contains(.,'Tax')]/ancestor::tr/td[contains(.,'$')]";
	public static final String MARKETSEGMENT_DROPDOWNLIST = "//label[text()='Market']/preceding-sibling::div//ul/li";
	public static final String QUOTECREATED_ALERTMESSAGE = "//div[contains(@data-bind,'text: onBookSuccessModalObj().message')]";
	public static final String MARKETSEGMENT_ADVANCESEARCH_DROPDOWNLIST = "//span[text()='Marketing Segment']/parent::button/following-sibling::div//li";
	public static final String ADVANCESEARCH_MARKETSEGMENT_BUTTON = "//button[@title='Marketing Segment']";

	public static final String GUESTINFO_EDITBUTTON = "//span[contains(@data-bind,'EditGuestInfo : function()')]";
	public static final String GUESTINFO_SAVEBUTTON = "(//button[contains(@data-bind,'click: GuestInfoSave')])[1]";
	public static final String GUESTINFO_GUESTPROFILE_INPUTFIELD = "//input[contains(@data-bind,'value: $parent.GuestProfileName')]";
	public static final String GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP = "//div[contains(text(),'Do you want to replace the guest info')]";
	public static final String GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP_YESBUTTON = "//div[contains(text(),'Do you want to replace the guest info')]/parent::div//button[text()='Yes']";
	public static final String GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP_NOBUTTON = "//div[contains(text(),'Do you want to replace the guest info')]/parent::div//button[text()='No']";
	public static final String GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP_CLOSEBUTTON = "//div[contains(text(),'Do you want to replace the guest info')]/parent::div//button[contains(@class,'close')]";

	public static final String GUESTINFO_SENDEMAIL_BUTTON = "//mail-popup//button[contains(@data-bind,'click: function(){ showEmailPopup() }')]";

	public static final String GUESTINFO_SENDEMAIL_HEADERTITLE = "(//h4[text()='Send Mail'])[2]";
	public static final String GUESTINFO_SENDEMAIL_EMAILID = "//input[contains(@data-bind,'value:to')]";
	public static final String SENDMAIL_POPUP_SEND_BUTTON = "//div[@class='modal-footer']//button[contains(@class,'desktop-view')][text()='Send']";
	public static final String GUESTINFO_SENDEMAIL_SUBJECT = "(//button[contains(@data-bind,'click:function(){send();}')])[2]";
	public static final String GUESTINFO_SENDEMAIL_DESCRIPTION = "//body[contains(@class,'cke_editable')]//span//span";
	public static final String POST_CANCELLATIONFEE_CHECKBOX = "//input[contains(@data-bind,'modal.isPolicyOverrideAllowed')]";
	public static final String SelectMarketButton = "(//label[text()='Market']/preceding-sibling::div/button)[1]";

	public static final String CP_Reservation_HistoryTab_RollBackDesc = "//span[contains(text(),'Rolled back this reservation to Reserved')]";
	public static final String cancelPaymentPopupBalance = "//div[contains(@data-bind,'IsFromCancel')]//div//span[contains(@data-bind,'PaymentFolioBalance')]";

	public static final String OVERBOOKING_TAB_MESSAGE = "//div[contains(text(),'There are 0')]";
	public static final String OVERBOOKING_TAB_YESBUTTON = "//div[contains(text(),'There are 0')]/parent::div//div[@class='modal-footer']//button[text()='Yes']";
	public static final String OVERBOOKING_TAB_NOBUTTON = "//div[contains(text(),'There are 0')]/parent::div//div[@class='modal-footer']//button[text()='No']";
	public static final String OVERBOOKING_TAB_CLOSEBUTTON = "//div[contains(text(),'There are 0')]/parent::div//button[contains(@class,'close')]";
	public static final String AssociatedAccountName = "//a/span[@data-bind='text:$parent.account']";
	public static final String GetRoomNumber = "//div[contains(@data-bind,'selectedRoomNumber')]";
	public static final String GroupSign = "//*[contains(text(),'Group')]//span";
	public static final String CheckInButton = "(//button[contains(@data-bind,'click: $parents[2].ReservationDetail.ShowCheckIn')])[1]";
	public static final String CheckInConfirmButton = "//button[contains(@data-bind,'click: checkInDetail.confirmCheckInClick')]";
	public static final String SpinerLoading = "(//div[@id='ReservationDetail_CP']//div[contains(@data-bind,'visible: showLoading')])[1]";
	public static final String GetReservationStatus = "//span[@data-bind='text:reservationViewStatuses.titleLineStatus']";

	public static final String GetRoomChargesInTripSummaryBeforeCreateReservation = "//span[text()='Room Charges']//..//..//div[contains(@data-bind,'value: roomChargeTotal')]";
	public static final String GetTaxandServicesInTripSummaryBeforeCreateReservation = "//span[text()='Taxes & Service Charges']//..//..//div[contains(@data-bind,'value: taxesTotal')]";
	public static final String noShowNotesType = "//td[text()='No Show']/..//td[contains(@data-bind,'type')]";

	public static final String noShowPolicyDisclaimersTab = "(//a[contains(@data-bind,'cancelNoShow')]/i)[1]";
	public static final String noShowPolicyNameInDetailsTab = "//button[@title='NoshowPolicy470']";
	public static final String noShowPolicyTextDetailsTab = "//textarea[contains(text(),'NoshowPolicy470_Text')]";
	public static final String closeNoShowPolicyExpand = "(//a[contains(@data-bind,'cancelNoShow')])";

	public static final String roomNumberInItemDetails = "//div/span[contains(@data-bind,'RoomNumber')]";
	public static final String folioBalance = "(//div[contains(text(),'Balance')]//span[contains(@data-bind,'PaymentFolioBalance')])[2]";
	public static final String EditMarketingInfoIcon = "//span[contains(@data-bind,'visible:!marketingInfo.IsMarketingInfoEditMode')]//i";
	public static final String SaveMarketingInfo = "(//button[contains(@data-bind,'enable: saveMarketingEnabled')])[1]";
	public static final String CP_NewReservation_SplitCheckBox = "//label[text()='Split-Room Reservation?']";
	public static final String spinerLoadingatRoomSelection = "//div[@id='ReservationCreate']//div[@data-bind='visible: showLoading']";

	public static final String guestStatement = "//div/a[contains(text(),'Reports ')]/..//ul//span[text()='Guest Statement']";
	public static final String guestStatementWithTaxBreakdown = "//div/a[contains(text(),'Reports ')]/..//ul//span[text()='Guest Statement with Tax breakdown']";
	public static final String guestRegistration = "//div/a[contains(text(),'Reports ')]/..//ul//span[text()='Guest Registration']";
	public static final String guestRegistartiontaxbreakdown = "//div/a[contains(text(),'Reports ')]/..//ul//span[text()='Guest Registration with Tax breakdown']";
	public static final String reservationReports = "//div/a[contains(text(),'Reports ')]";

	public static final String SpanTaskCategoryImage = "//span[contains(text(),'Tasks')]//..//..//div[@class='ir-task-type']//span[@data-bind='css: category.icon.image']";
	public static final String SpanTaskName = "//span[contains(text(),'Tasks')]//..//..//div[@class='ir-task-type']//span[@data-bind='text: category.type.name']";
	public static final String SpanTaskDetails = "//span[contains(text(),'Tasks')]//..//..//td[@data-bind='text: description']";
	public static final String SpanTaskDueOn = "//span[contains(text(),'Tasks')]//..//..//td[@data-bind='text: $parent.taskModule().formatDueOn(dueOn)']";
	public static final String SpanTaskStatus = "//span[contains(text(),'Tasks')]//..//..//td[@data-bind='text: $parent.taskModule().formatStatus(status)']";

	public static final String unsavedDataPopupHeader = "//h4[contains(text(),'You have Unsaved Data!')]";
	public static final String unsavedDataPopupYesButton = "//h4[contains(text(),'You have Unsaved Data!')]/../..//button[text()='Yes']";
	public static final String unsavedDataPopupNoButton = "//h4[contains(text(),'You have Unsaved Data!')]/../..//button[text()='No']";
	public static final String unsavedDataDisplayed = "//div[@id='alertForTab' and @aria-hidden='false']";

	public static final String depositPolicyCollapseIcon = "//a[contains(@data-bind,'collapseOne')]";
	public static final String depositPolicyNameAtDisclaimers = "//a[contains(@data-bind,'collapseOne')]/../../..//span[@class='filter-option pull-left']";
	public static final String depositPolicyTextAtDisclaimers = "//textarea[contains(@data-bind,'policyStatementDeposit')]";
	public static final String depositPolicyToolTipTextAtDisclaimers = "//a[contains(@data-bind,'collapseOne')]/../../..//button";

	public static final String paymentPopupDate = "(//input[contains(@data-bind,'value:PaymentDate')])[2]";
	public static final String guestHistoryCategories = "//span[contains(@data-bind,'data.category')]";
	public static final String guestHistoryDescriptions = "//span[contains(@data-bind,'data.description')]";

	public static final String fristRoomChageInReservation = "(//span[contains(@data-bind,'roomTotal')])[1]";
	public static final String SecondRoomChageInReservation = "(//span[contains(@data-bind,'roomTotal')])[2]";
	public static final String fristRoomNumberInReservation = "(//span[contains(@data-bind,'roomNumber')])[1]";
	public static final String secondRoomNumberInReservation = "(//span[contains(@data-bind,'roomNumber')])[2]";

	// PropetyDetails
	public static final String seclectProperty = "(//table[@id='MainContent_dgPropertyList']//td/a)[1]";
	public static final String propertyDetailsPhoneNumber = "//input[@id='MainContent_txtMailing_phoneNumber']";
	public static final String propertyDetailsAddress1 = "//input[@id='MainContent_txtMailing_address1']";
	public static final String propertyDetailsCity = "//input[@id='MainContent_txtMailing_city']";
	public static final String propertyDetailsCountry = "//select[@id='MainContent_drpMailing_countryID']/option[contains(text(),'United States')]";
	public static final String propertyDetailsState = "//select[@id='MainContent_drpMailing_territoryID']";
	public static final String propertyDetailsPostalCode = "//input[@id='MainContent_txtMailing_postalCode']";
	public static final String propertyDetailscontactName = "//input[@id='MainContent_txtMailing_contactName']";
	public static final String propertyDetailsinputEmail = "//input[@id='MainContent_txtMailing_email']";
	public static final String ADDITIONAL_EMAIL_ADDRESS = "//input[contains(@data-bind,'value: EmailAddress')]";
	public static final String ADDITIONAL_PHONE_NUMBER = "(//input[contains(@data-bind,'value: PhoneNumber')])[1]";
	public static final String CLICK_ON_PRINT_BUTTON = "//*[@id='optionsForInnroad']//div[text()='Print']";
	public static final String CHECK_BOX_INCLUDE_TOTAL_REVENUE = "//span[text()='Include Total Revenue']//..//input[@type='checkbox']";
	public static final String RADIO_BUTTON_MAILING_DETAILS = "//span[text()='Mailing Details']//..//input[@type='radio']";
	public static final String PRINT_ICON = "//a[contains(@data-bind,'click: Print')]";
	public final static String CLICK_ON_ADVANCE_SEARCH = "//button[.='Advanced']";
	public final static String GET_GUEST_INFO_TEXT = "(//span[contains(text(),'Guest')])[5]/..";
	public final static String GET_RESERVATION_INFO_TEXT = "(//span[contains(text(),'Reservation')])[5]/..";
	public final static String GET_MARKETING_INFO_TEXT = "//span[contains(text(),'Marketing')]/..";
	public static final String ClickTaxExcemptCheckBox = "(//div[@data-bind='with: PaymentDetail']/div/div/div/label[@class='ir-checkbox']//span[@class='ir-check-box'])[3]";
	public static final String EnterTaxExcemptId = "//label[text()='Tax Exempt ID']/../input[@class='form-control ir-frm-ctrl']";
	// public static final String
	// ClickResCanUpdateAfterProcess="(//div[@class='panel-group']//..//following-sibling::a)[1]";
	public static final String SelectRoomClassInSearch = "//select[@id='MainContent_drpRoomClassList']";
	public static final String ClickSearchGO = "//input[@id='MainContent_btnGoSearch']";
	public static final String GetTotalGuestFolioItemInRes = "//ul[@class='dropdown-menu inner']/li/a/span[starts-with(.,'Guest Folio For')]";
	public static final String GetDepositDueCharge = "(//label[text()='Deposit Due']/following-sibling::div/div[contains(.,'$')])[1]";

	public static final String NotesTypes = "//tbody[@data-bind='foreach: notes']//td[contains(@data-bind,'type')]";
	public static final String NotesRoom = "//tbody[@data-bind='foreach: notes']//td[contains(@data-bind,'roomClassAbbrv ')]";
	public static final String NotesSubject = "//tbody[@data-bind='foreach: notes']//td[contains(@data-bind,'subject')]";
	public static final String NotesDescription = "//tbody[@data-bind='foreach: notes']//td[contains(@data-bind,'details')]";
	public static final String NotesUpdatedOn = "//tbody[@data-bind='foreach: notes']//td[contains(@data-bind,'updatedOn')]";

	// verify checkout Payment
	public static final String checkOutPaymentDate = "//span[contains(@data-bind,'text:checkOutDetail.PaymentDate')]|//span[contains(@data-bind,'checkInDetail.PaymentDate')]";
	public static final String checkOutPaymentType = "//span[contains(@data-bind,'checkOutDetail.PaymentType')]";
	public static final String checkOutFolioBalence = "//div[contains(text(),'Balance')]//span[contains(@data-bind,'checkOutDetail.PaymentFolioBalance')]";
	public static final String checkoutPaymentStatus = "//label[contains(@data-bind,'checkOutDetail.PaymentStatus')]";
	public static final String checkOutButtonClose = "(//button[contains(text(),'CLOSE')])[2]";
	public static final String checkOutPaymentMethod = "//span[contains(@data-bind,'PaymentInformation')]";
	public static final String checkOutPaymentAmount = "//span[contains(@data-bind,'PaymentAmount')]";

	public static final String checkOutPopUp = "//div[contains(text(),'Are you sure you want to check-out all rooms at once')]//following-sibling::div//button[contains(text(),'Yes')]";
	public static final String dirtyRoomPopUp = "//div[contains(text(),'One or more of the selected room(s) is Dirty')]//following-sibling::div//button[contains(text(),'Confirm')]";
	public static final String checkOutPaymentButton = "//button[contains(@data-bind,'PaymentFormattedAmountMask')]";

	public static final String PAYMENTMETHOD_EDITBUTTON = "//span[contains(@data-bind,'editPaymentInfo : function')]";
	public static final String PAYMENTMETHOD_SAVEBUTTON = "(//button[contains(@data-bind,'PaymentDetail().savePaymentInfo')])[1]";
	public static final String CP_SECONDARYGUEST_CHECKOUTBUTTON = "(//button[text()='Check Out'])[1]";
	public static final String CP_SECONDARYGUEST_CHECKOUT_CONFIRMATIONPOPUP = "//div[contains(text(),'Do you wish to settle all charges')]";
	public static final String CP_CHECKOUT_CONFIRMATIONTAB_NOBUTTON = "//div[contains(text(),'Do you wish to settle')]/parent::div//div[@class='modal-footer']//button[text()='No']";
	public static final String CP_CHECKOUT_CONFIRMCHECKOUT_BUTTON = "//button[contains(@data-bind,'click: checkOutDetail.onConfirmClick')]";
	public static final String CP_CHECKOUT_GENERATEGUESTSTATEMENT_TOGGLE = "//span[contains(@data-bind,'!checkOutDetail.isGenerateGuestStatement()')]";
	public static final String CP_CHECKIN_GENERATEGUESTSTATEMENT_TOGGLE = "//span[contains(@data-bind,'!checkInDetail.isGuestRegistrationReport()')]";

	public static final String CP_SECONDARYGUEST_ROOMNUMBER = "(//span[contains(text(),'ROOM:')])[2]/following-sibling::span[contains(@data-bind,'text: roomNumber')]";
	public static final String CP_SECONDARYGUEST_SALUTATION = "(//button[contains(@title,'Select')]//span[text()='Select'])[1]";
	public static final String CP_SECONDARYGUEST_FIRSTNAME = "(//label[text()='Guest ']/preceding-sibling::input)[2]";
	public static final String CP_SECONDARYGUEST_LASTNAME = "//label[text()='Guest ']/../../../following-sibling::div//input";
	public static final String CP_SECONDARYGUEST_CHECKINBUTTON = "(//button[text()='Check In'])[2]";
	public static final String CP_SECONDARYGUEST_CHECKIN_CHECKOUTTAB_TITLENAME = "//span[contains(@data-bind,'text: currentGuestName')]";
	public static final String cancelResPopupPostCancelFeeCheckBox = "//input[contains(@data-bind,'modal.isPolicyOverrideAllowed')]";
	public static final String cancelResPopupPostCancelFeeCheckBoxClick = "//input[contains(@data-bind,'modal.isPolicyOverrideAllowed')]/..//span[@class='ir-check-box']";
	public static final String resStatusAtBasicSearch = "//tbody[@data-bind='foreach: ReservationList']//span[contains(@data-bind,'text: StatusName')]";

	public static final String CLOSE_BULK_CHECKOUT_POPUP = "(//div[@class='ng-modal-content']//following-sibling::div//button[contains(text(),'Close')])[1]";
	public static final String ROLL_BACK_POPUP_TEXT = "(//div[contains(@data-bind,'css: appendResdIdString')])[2]//..//following-sibling::div[@class='modal-body']";
	public static final String ACCEPT_ROLL_BACK_CHANGE = "//button[contains(@data-bind,'click: popupAcceptChanges')]";

	public static final String reservationStatusInDetailSection = "(//span[contains(@data-bind, 'reservationViewStatuses.titleLineStatus')])[1]";
	public static final String bulkCheckOutSuccessHeading = "//h4[.='Bulk Checkout Completed']";
	public static final String bulkCheckOutSuccessReservationCount = "(//span[@data-bind='text: ProcessedList().length'])[2]";
	public static final String rateInterval = "//input[@id='MainContent_txtInterval']";
	// Reservation Reports
	public static final String reportsIcon = "//a[contains(@data-bind,'reportsAccordionInitialize')]/i";
	public static final String guestStatements = "(//span[contains(text(),'Guest Registration')])[1]";
	public static final String allRooms = "(//a//span[contains(text(),'All rooms')])[3]";

	public static final String folioLineItemTableTrSize = "//tbody[contains(@data-bind,'ComputedFolioItems')]//tr";
	public static final String checkoutWithoutPayment = "//button[contains(text(),'CHECK OUT WITHOUT PAYMENT')]";
	public static final String cP_ReservationDirtyMsg = "//div[contains(text(),'The room status is dirty')]";
	public static final String Reservation_Backward_Admin = "(//a/span[contains(text(),'Reservations')])[3]";

	public static final String CPRESERVATION_CHECKIN_DIRTYROOMMODALPOPUP = "//div[contains(text(),'The room status is dirty')]";
	public static final String FOLIO_OPTIONS_TAB = "//a[contains(@data-bind,'ShowFolioOption')]";
	public static final String FOLIO_DETAILS_TAB = "//a[contains(@data-bind,'ShowFolioGrid')]";
	public static final String SUPPRESS_ACCOUNT_FOLIO_PRINTING_CHECKBOX = "//input[contains(@data-bind,'SuppressPrinting')]";

	public static final String depositPaymentPopupHeading = "//h4[contains(text(), 'Deposit Payment')]";
	public static final String depositPaymentPopupPolicy = "//span[contains(@data-content, 'VerifyPaymentPolicy_Text')]";
	public static final String depositPaymentPopupBalance = "(//div[contains(@data-bind, 'IsFromDeposit')]//span[contains(@data-bind, 'PaymentFolioBalance')])[2]";
	public static final String depositPaymentPopupAmount = "payFormAmountV2";
	public static final String depositPaymentPopupTransactionDecline = "//span[contains(@data-bind, 'TransactionDeclinedMessage')]";
	public static final String depositPaymentPopupPaymentButton = "//button[contains(@data-bind, 'PaymentFormattedAmountMask(), click: PaymentProcessButtonClick')]";
	public static final String cancelDepositPaymentPopupButton = "//h4[contains(text(), 'Deposit Payment')]/../button";
	public static final String saveAsQuoteButton = "//button[contains(text(), 'Save as Quote')]";
	public static final String reservationStatusOnDetailSection = "//span[contains(@data-bind,'text:reservationViewStatuses.titleLineStatus')]";
	public static final String reservationNumberOnDetailSection = "//span[contains(@data-bind, 'text:confirmationNumber')]";
	public static final String editPaymentInfoButton = "//span[contains(@data-bind, 'IsPaymentInfoEditMode')]";
	public static final String decryptLink = "//a[contains(@data-bind, 'btnIsInGuestEncryptMode')]";
	public static final String ccNumberInput = "(//input[contains(@data-bind, 'creditCardNumber')])[1]";
	public static final String paymentInfoSaveButton = "//a[contains(@data-bind, 'click:PaymentDetail().closePaymentInfo')]//following-sibling::button[contains(text(), 'Save')]";
	public static final String updateGuestProfileInput = "(//input[contains(@data-bind, 'chkUpdateGuestProfileChecked')]//following-sibling::span)[5]";
	public static final String depositDue = "(//div[contains(@data-bind, 'depositDueAmount')])[1]";
	
	public static final String CP_NewReservation_COUNTRY = "(//select[@name='country'])[1]/following-sibling::div//button//span[1]";
	public static final String CP_NewReservation_STATE = "(//select[@name='state'])[1]/following-sibling::div//button//span[1]";
	public static final String CP_NewReservation_POSTALCODE = "(//input[contains(@data-bind,'value: postalCode')])[1]";
	public static final String EnterReservationNumber = "//div[@class='modal-body']//input[@class='form-control ir-frm-ctrl ir-td-textTrim']";
    public static final String ClickPayButton = "//div[@class='modal-body']//button[@class='btn ir-btn btn-success btn-block']";
    public static final String ClosePaymentSuccessfulModal = "//div[@class='col-md-12 text-right']//button[@class='btn ir-btn btn-default md ir-mb-btn-block']";
    public static final String CreateReservationPaymentMethod = "//*[@id=\"bpjscontainer_29\"]/div[2]/div/div[2]/form/div[6]/fieldset/paymentmethod/div/div/div[1]/div/div[2]/div[1]/div/div";
    public static final String CashPaymentDescription = "#bpjscontainer_67 > div.hidden-sm.hidden-xs > div:nth-child(2) > div:nth-child(1) > div.portlet-body.flip-scroll.portlet-body_2 > div > div:nth-child(2) > div > table > tbody > tr:nth-child(2) > td.lineitem-description > a";//"#bpjscontainer_62 > div.hidden-sm.hidden-xs > div:nth-child(2) > div:nth-child(1) > div.portlet-body.flip-scroll.portlet-body_2 > div > div:nth-child(2) > div > table > tbody > tr:nth-child(2) > td.lineitem-description > a";
    public static final String UpdatedByUser = "//div[@data-bind='if: $root.ShowUpateUserInfo() ']//span[contains(@data-bind,'UpdatedBy')]";
    public static final String ClickSavePaymentButton = "/html/body/div[4]/div[2]/div/section/span/div/div[4]/span/section/div[2]/div[1]/div/div/div[9]/div/div[1]/div[2]/span[3]/span[4]/button[1]";
    public static final String EnterAmount = "//*[@id=\"payFormAmountV2\"]";
    public static final String ReservationNumber= "//span[contains(@data-bind,'text:confirmationNumber')]";//"//div[@class='ir-textTrim']//span[contains(@data-bind,'GuestProfileName')]";

	public static final String authorizationSuccess_Date="//h4[text()='Authorization Successful']/../..//span[contains(@data-bind,'SelectedPaymentDateString()')]";
	public static final String authorizationSuccess_Balance="//h4[text()='Authorization Successful']/../..//span[contains(@data-bind,'PaymentFolioBalanceAfterProcess')]";	
	public static final String authorizationSuccess_Folio="//h4[text()='Authorization Successful']/../..//span[contains(@data-bind,'text:PaymentFolioName()')]";
	public static final String authorizationSuccess_Amount="//h4[text()='Authorization Successful']/../..//span[contains(@data-bind,'PaymentFormattedAmount()')]";
	public static final String authorizationSuccess_Type="//h4[text()='Authorization Successful']/../..//span[contains(@data-bind,'PaymentAuthTypeText')]";
	public static final String authorizationSuccess_PaymentMethod="//h4[text()='Authorization Successful']/../..//span[contains(@data-bind,'ProcessedPaymentInformation()')]";
	public static final String authorizationSuccess_Status="//h4[text()='Authorization Successful']/../..//label[contains(@data-bind,'ProcessedPaymentStatus')]";
	public static final String authorizationSuccess_Note="//h4[text()='Authorization Successful']/../..//span[(@class='short-meta')]";
	public static final String authorizationSuccess_CloseButton="//h4[text()='Authorization Successful']/../..//button[contains(@data-bind,'click: CloseTakePaymentButtonClick')]";
	public static final String reservation_Save="//button[@class='btn btn-success'][text()='Save']";
	public static final String loading="(//div[@class='ir-loader-in'])";
	public static final String checkout_Close="//button[contains(@data-bind,'PaymentCloseButtonClick')]";
	public static final String checkout_ProceedButton="//h4[text()='Check Out']/../..//button[contains(@data-bind,'click: checkOutDetail.onConfirmClick')]";
	public static final String checkout_ProceedMessage="(//div[@class='modal-scrollable']//div[@class='modal-body'])[2]";
	public static final String checkoutWithoutRefund="//button[contains(text(),'No, confirm check-out without a refund')]|//button[contains(text(),'No, cancel the check-out process')]";
	public static final String checkoutTakemeToFolio="//button[contains(text(),'Yes, take me to the Folio')]";
	public static final String folioDetailTab="//a[@class='current']";
	public static final String detailTab="//div[@class='panel-heading pr'][contains(text(),'Stay')]";
	public static final String checkoutPayment="//h4[text()='Check Out Payment']";

	public static final String LongStayText = "//span[@data-bind='text: PaymentDetail().exemptText()']"; 
	public static final String LongStayCheckbox = "//span[@data-bind='text: PaymentDetail().exemptText()']//following-sibling::i"; 
	public static final String depositPolicyLabel = "//label[contains(text(),'Deposit Policy')]";
	public static final String depositPolicyCollapse = "(//div[contains(@class,'panel-default')]//h4//following-sibling::a[contains(@data-bind,'href')]/i[1])[1]";
	public static final String reservationPolicy  = "//div[@aria-expanded='true']//span[@class='filter-option pull-left']";
	public static final String newGroupReservationInput = "//input[contains(@value,'New Reservation')]";
	public static final String checkInPolicy = "//span[.='Policy:']/following-sibling::span[1]";
	public static final String tripSummaryRoomCharges = "(//div[contains(@data-bind,'roomChargeTotal')])[1]";
	public static final String tripSummaryTaxAndServices = "(//div[contains(@data-bind,'taxesTotal')])[1]";
	public static final String tripSummaryTotal = "(//div[contains(@data-bind,'tripTotal')])[1]";
	public static final String tripSummaryDepositPaid = "(//div[contains(@data-bind,'depositDueAmount')])[1]";
	public static final String SelectPaymentMethodButton = "//h4[text()='Check In Payment']//..//..//div[contains(@class,'payment-method')]//button";
	public static final String CheckInGuestRegirstrationReportToggle = "(//div[contains(@data-bind,'if: checkInDetail.showFooterWithCheckInButton')]//span)[1]";
	public static final String CheckInDirtyRoomPopup = "//div[@role='document']//following-sibling::div//button[text()='Confirm']";
	public static final String CLOSEBULKACTIONPOPUP = "//button[contains(@data-bind,'visible: !BulkOperationInProcess()')]";

	public static final String GET_ALL_ROOM_CLASS_NAME="//span[contains(@data-bind,'text: roomClass.name')]";
	public static final String GET_ALL_ROOM_RATES="//span[contains(@data-bind,'text: $root.GetCurrencySymbol() + total')]";
	public static final String GET_ALL_ROOM_CLASS_DESCRIPTION="//span[contains(@data-bind,'$root.GetCurrencySymbol() + average')]";
	public static final String GET_ERROR_MESSAGE="//div[contains(@data-bind,'text: roomSearch.editStayInfoError')]";

}
