package com.innroad.inncenter.pages;

public class NewFolio {

	public static final String Reservation_CardInfoPopup = "//div[@id='ReservationPaymentDetailCreditCardInfo']";
	public static final String CardInfoPopup_CardName = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[@placeholder='Name On Card']";
	public static final String CardInfoPopup_CardNum = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[@placeholder='Credit Card Number']";
	public static final String CardInfoPopup_ExpDate = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[@placeholder='Expiry Date']";
	public static final String CardInfoPopup_Card_CVVCode = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[@placeholder='CVV Code']";
	public static final String CardInfoPopup_Address = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[@placeholder='Address1']";
	public static final String CardInfoPopup_Card_City = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[@placeholder='City']";
	public static final String CardInfoPopup_Card_State = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[@placeholder='State']";
	public static final String CardInfoPopup_PostalCode = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[@placeholder='PostalCode']";
	public static final String CardInfoPopup_ApprovalCode = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[@placeholder='ApprovalCode']";
	public static final String CardInfoPopup_OKButton = "//div[@id='ReservationPaymentDetailCreditCardInfo']//button[contains(@data-bind,'click: btnOKClick')]";
	public static final String CardInfoPopup_CancelButton = "//div[@id='ReservationPaymentDetailCreditCardInfo']//button[text()='Cancel']";

	//Validation Messages
	public static final String CardInfoPopup_CardNameValidation = "//div[@id='ReservationPaymentDetailCreditCardInfo']//div[contains(@data-bind,'NameOnCard')]//span[@class='validationMessage']";
	public static final String CardInfoPopup_CardNumberValidation = "//div[@id='ReservationPaymentDetailCreditCardInfo']//div[contains(@data-bind,'AccountNumber')]//span[@class='validationMessage']";
	public static final String CardInfoPopup_ExpDateValidation = "//div[@id='ReservationPaymentDetailCreditCardInfo']//div[contains(@data-bind,'ExpDate')]//span[@class='validationMessage']";
	
	public static final String Account_CardInfoPopup = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]";
	public static final String Account_CardInfoPopup_CardName = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]//input[@placeholder='Name On Card']";
	public static final String Account_CardInfoPopup_CardNum = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]//input[@placeholder='Credit Card Number']";
	public static final String Account_CardInfoPopup_ExpDate = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]//input[@placeholder='Expiry Date']";
	public static final String Account_CardInfoPopup_Card_CVVCode = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]//input[@placeholder='CVV Code']";
	public static final String Account_CardInfoPopup_Address = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]//input[@placeholder='Address1']";
	public static final String Account_CardInfoPopup_Card_City = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]//input[@placeholder='City']";
	public static final String Account_CardInfoPopup_Card_State = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]//input[@placeholder='State']";
	public static final String Account_CardInfoPopup_PostalCode = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]//input[@placeholder='PostalCode']";
	public static final String Account_CardInfoPopup_ApprovalCode = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]//input[@placeholder='ApprovalCode']";
	public static final String Account_CardInfoPopup_OKButton = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]//button[contains(@data-bind,'click: btnOKClick')]";

	public static final String Cancellation_Policy = "//input[contains(@data-bind,'value: $root.selectedCancelPolicyString')]";
	public static final String CancelPolicyPicker_Popup = "//h4[text()='Cancel Policy Picker']";
	public static final String AvailableCancellationPolicies = "//h4[text()='Cancel Policy Picker']//parent::div//following-sibling::div//select[contains(@data-bind,'chosenObjectsToAssign')]";
	public static final String SelectedCancellationPolicies = "//h4[text()='Cancel Policy Picker']//parent::div//following-sibling::div//select[contains(@data-bind,'chosenObjectsToUnAssign')]";
	public static final String ClickAddOne_Cancelpolicy = "//h4[text()='Cancel Policy Picker']//parent::div//following-sibling::div//button[contains(@data-bind,'AddNew')]";
	public static final String ClickDone_CancelPolicy = "//h4[text()='Cancel Policy Picker']//parent::div//following-sibling::div//button[contains(@data-bind,'Done')]";
	public static final String ClickDiscardAll_CancelPolicy ="//h4[text()='Cancel Policy Picker']//parent::div//following-sibling::div//button[contains(@data-bind,'DiscardAll')]";
	public static final String AddedLineItems_Date = "//tbody[contains(@data-bind,'ComputedFolioItemsElement')]//span[contains(@data-bind,'EffectiveDate')]";
	public static final String AddedLineItems_Category = "//tbody[contains(@data-bind,'ComputedFolioItemsElement')]//span[contains(@data-bind,'data.Category')]";
	public static final String AddedLineItems_Description = "//tbody[contains(@data-bind,'ComputedFolioItemsElement')]//a[contains(@data-bind,'data.Description')]";
	public static final String AddedLineItems_Amount = "//tbody[contains(@data-bind,'ComputedFolioItemsElement')]//span[contains(@data-bind,'data.Amount_aggregate')]";
	public static final String AddedLineItems_Room = "//tbody[contains(@data-bind,'ComputedFolioItemsElement')]//span[contains(@data-bind,'data.RoomNumber')]";
	public static final String AddedLineItems_Icon = "//tbody[contains(@data-bind,'ComputedFolioItemsElement')]//img[contains(@data-bind,'ChangeStatus')]";
	
	
	public static final String Reservation_Payments = "(//div[contains(@class,'payInfoSection')]//label[contains(text(),'Payments:')]//parent::div//span[@class='pamt'])[2]";
	public static final String Reservation_Balance = "(//div[contains(@class,'payInfoSection')]//label[contains(text(),'Balance:')]//parent::div//span[@class='pamt'])[2]";
	public static final String Reservation_TotalCharges = "(//div[contains(@class,'payInfoSection')]//label[contains(text(),'Total Charges:')]//parent::div//span[@class='pamt'])[2]";
	public static final String Enter_LineItemAmount = "//td[@class='lineitem-amount']//input";
	
	public static final String FolioRows = "//tbody[contains(@data-bind,'getElement: ComputedFolioItemsElement')]//tr";
	public static final String Enter_LineItemQuentity = "//td[@class='lineitem-qty']//input";
	
	public static final String roomCharges = "//label[contains(text(),'Room Charges:')]//following-sibling::span//span";
	public static final String incidental = "//label[contains(text(),'Incidentals:')]//following-sibling::span//span";
	public static final String taxServices = "//label[contains(text(),'Taxes & Service Charges:')]//following-sibling::span//span";
	public static final String totalCharges = "//label[contains(text(),'Total Charges:')]//following-sibling::span//span";
	public static final String payment = "//label[contains(text(),'Payments:')]//following-sibling::span//span";
	public static final String totalBalance = "//label[contains(text(),'Balance:')]//following-sibling::span//span";
	
	public static final String ItemDetails_PrePath = "//div[@data-bind='getElement: popUp']";
	public static final String itemDetailsPopup = ItemDetails_PrePath+"//h4";
	public static final String ItemDetails_CancelButton = "("+ItemDetails_PrePath+"//button[text()='Cancel'])[1]";
	public static final String ItemDetails_AddButton = ItemDetails_PrePath+"//button[text()='Add']";
	public static final String ItemDetails_VoidButton = ItemDetails_PrePath+"//button[text()='Void']";
	public static final String ItemDetails_CancelPopup = "("+ItemDetails_PrePath+"//button[text()='Cancel'])[2]";
	public static final String ItemDetails_ContinuePopup = ItemDetails_PrePath+"//button[text()='Continue']";
	public static final String ItemDetails_Transcation = ItemDetails_PrePath+"//a[text()='Transactions']";
	public static final String ItemDetailsRows = "("+ItemDetails_PrePath+"//table//tbody)[1]//tr";
	public static final String ItemDetails_RoomCharges = "//span[text()='Room Charges']//..//following-sibling::td//span";
	public static final String ItemDetails_Incidental = "//span[text()='Incidentals']//..//following-sibling::td//span";
	public static final String ItemDetails_TaxServices = "//span[text()='Taxes & Service Charges']//..//following-sibling::td//span";
	public static final String ItemDetails_TotalCharges = "//span[text()='Total Charges']//..//following-sibling::td//span";
	public static final String RoomNumber = "//span[@data-bind='text: $root.RoomNumber']";
	public static final String checkbox_IncludePendingItemsinTotal = "//input[@data-bind='checked: $root.IncludePendingItemsInTotal']";
	public static final String Notes = "//textarea[@data-bind='value: $parent.NewFolioNotes']";
	public static final String checkboxDisplayVoidItem = "//input[@data-bind='checked: $root.DisplayVoidItems']";
	public static final String RollBackButton = ItemDetails_PrePath+"//button[contains(@data-bind,'click: $parent.btnRollBack')]";
	public static final String ItemDetailsSelectCategory = "//select[contains(@data-bind,'options: $parent.CategoriesToShow')]";
	public static final String EnterItemDetailsDescription = "//input[contains(@data-bind,'value: $parent.NewFolioDescription')]";
	public static final String EntertemDetailsAmount = "//input[contains(@data-bind,'value: $parent.NewFolioAmount')]";
	public static final String FolioBalance = "//label[text()='Folio Balance:']//following-sibling::div//span";
	public static final String TotalBalance = "//label[text()='Total: ']//following-sibling::div//p";
	public static final String PaymentDetailsPopupHeading = "//span[text()='Payment Details']";
	public static final String SelectAuthorizationType = "AuthorizationTypeOut";
	public static final String ProcessButton = "//button[contains(@data-bind, 'visible: $parent.isProcessButtonVisible')]";
	public static final String enterAmount = "//input[contains(@data-bind,'value: $parent.formattedAmount')]";
	public static final String CurrentBalance = "//span[contains(@data-bind,'value: CurrentBalance')]";
	public static final String CurrentPayment = "//span[contains(@data-bind,'value: CurrentPayment')]";
	public static final String EndingBalance = "//span[contains(@data-bind,'value: EndBalance')]";
	public static final String btnCancelInPaymentPopup = "//div[@id='ReservationPaymetItemDetail']//button[contains(@data-bind,'btnCancelVisible')]";
	
	public static final String Foliotab = "//li//a[text()='Folio(s)']";
	public static final String FolioDetails = "//a[text()='Folio Details']";
	public static final String FolioOptions = "//a[text()='Folio Options']";
	
	public static final String  CheckboxIncludeTaxesinLineItems = "//input[@data-bind='checked: $root.IncludeTaxesInLineItems']";
	public static final String  IncludeTaxesinLineItemsCheckbox = "//input[contains(@data-bind,'IncludeTaxesInLineItems')]";
	public static final String ChangeFolioButton = "//img[contains(@data-bind,'click: $parent.ReservationDetail.DeleteFolio')]//..//..//..//button";
	public static final String SaveFolio ="//button[.='Reset']//parent::span//following-sibling::span//button[.='Save']";
	public static final String MoveFolio_NewFolio_Save1 = "//button[.='Close']//following-sibling::button[contains(text(),'Save')]";

	public static final String SelectFolioProprtyInAccount = "//td[@class='lineitem-rooms']//select[contains(@data-bind,'options: $root.PropertyList')]"; 
	public static final String Enter_LineItemQuantity = "//td[@class='lineitem-qty']//input";

}
