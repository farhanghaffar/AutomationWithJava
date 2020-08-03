package com.innroad.inncenter.properties;

public class OR {

	// Login Page
	public static final String clientCode = "txtclientCode";
	public static final String userID = "txtLoginID";
	public static final String password = "txtUserPassword";
	public static final String Login = "btnLogon";
	// Documents Templates
	public static final String Doc_NewButton = " //input[@id='MainContent_btnNew']";
	public static final String DocTempPage = "//font[@class='TitleText']";
	public static final String DocumentName = "//input[@id='MainContent_txtDocumentName']";
	public static final String DocumentDescription = "//textarea[@id='MainContent_txtDocumentnote']";
	public static final String AssociateSource = "//input[@id='MainContent_btnAssociateSource']";
	public static final String AssociateSourcePopup = "//form[@id='frmSourcePicker']//font[@class='TitleText']";
	public static final String AssociateProperty = "//input[@id='MainContent_btnAssociateProperty']";
	public static final String AssociatePropertyPopup = "//font[@class='TitleText']";
	public static final String AssociateFunction = "//input[@id='MainContent_btnAssociateFunction']";
	public static final String AssociateFunctionPopup = "//font[@class='TitleText']";
	public static final String Document_Save = "//input[@id='MainContent_btnSave']";
	public static final String Document_Done = "//input[@id='MainContent_btnDone']";
	public static final String Source_Inncenter_CheckBox = "//*[@id='dgSourceList_chkSelectSource_0']";
	public static final String SavePopup = "//input[@id='btnSave']";
	public static final String Function_Email_CheckBox = "//input[@id='dgFunctionsList_chkSelectFunction_0']";
	public static final String functionConfirmationLetterCheckBox = "//input[@id='dgFunctionsList_chkSelectFunction_1']";
	public static final String Select_AssociateFunction = "//select[@id='lstRooms']";
	public static final String Selected_AssociateFunction = "//select[@id='lstPickedRooms']";
	public static final String Property_PickOne = "//div[@id='divpropertyPicker']//input[@id='btnPickOne']";
	public static final String Property_PickAll = "//input[@id='btnPickAll']";

	public static final String Function_Trigger = "//select[@id='MainContent_dgScheduleFunctions_drpDimension_0']";
	public static final String Function_Event = "//select[@id='MainContent_dgScheduleFunctions_drpTrigger_0']";
	public static final String Function_ScheduleDays = "//input[@id='MainContent_dgScheduleFunctions_txtInterval_0']";
	public static final String DocumentName_list = "//table[@id='MainContent_dgDocumentsList']//following-sibling::a[contains(@id,'DocumentName')]";
	public static final String DefaultTempCheckBox = "MainContent_chkDefault";
	public static final String SelectSystemDoc = "MainContent_dgDocumentsList_lnkDocumentName_";
	public static final String DeleteDocument_CheckBox = "//*[@type='checkbox']";
	public static final String Document_DeleteButton = "//input[@value='Delete']";
	public static final String CreatedDocument_Pages = "//tr[@class='TextDefault']/td//following-sibling::*";
	public static final String LastPage = "(//tr[@class='TextDefault']/td//following-sibling::*)[last()]";
	public static final String Content = "//input[@id='MainContent_btnDocumentContent']";

	// public static final String
	// Support Panel Page
	public static final String Reservation_Folio_Pay = "//button[contains(text(),'Pay')]";
	public static final String SearchIcon = "ucNavShortcut_rptrMenu_btnMenuItem_0";
	public static final String ClickArrow = "//*[@id='s2id_ucPanelSupport_drpClientList']/a/span[2]/b";
	public static final String clientTextBox = "s2id_autogen1_search";

	public static final String Settings_Icon = "//div[@class='support_panel_client_selector_open']";
	public static final String Pencil_Icon = "//div[@class='supportpanel']//span[@role='presentation']//b";
	public static final String Enter_client_TextBox = "(//input[@role='combobox'])[2]";

	public static final String NG_Season_Search = "//div[@id='seasonSearch']//button[text()='Search']";
	public static final String First_Element_In_Season_SearchResults = "(//tbody[@data-bind='foreach: seasonList']//tr)[1]//a";
	public static final String SeasonsPage_Reset_Btn = "//div[@id='seasonDetail']//button[.='Reset']";
	public static final String NG_New_Season_Btn = "//div[@class='footer_buttons']/button[text()='New Season']";

	// Select Property
	public static final String clickClientSelectorIcon = "//div[@class='support_panel_client_selector_open']";
	public static final String clickPenIcon = "//span[@id='select2-chosen-3']";
	public static final String enterClientName = "//input[@id='s2id_autogen3_search']";

	// Reservation Page
	public static final String Reservation_Unassigned = "//label[contains(text(),'Room(s):')]/following-sibling::div/p/span";
	public static final String Reservation_Get_UnassignedCount = "//div[@class='row unassignedrow']/div";
	public static final String ReservationIcon = "//*[@id='rptrMenu_btnMenuItem_0']";
	public static final String Reservationlink = "rptrMenu_lbtnMenuItem_0";
	public static final String OpenedReservation_Name = " (//span[@class='sn_span3'])[last()]";
	public static final String OpenedReservation_CloseButton = "(//i[@class='sn_span4 fa fa-times'])[last()]";
	public static final String OpenedReservation1_CloseButton = "(//i[@class='sn_span4 fa fa-times'])[last()-1]";
	public static final String NewReservationButton = "ucReservationControl1_btnNew";
	public static final String ClickonAssignRooms = "StayInfo1_btnAssignRooms";
	public static final String UnCheckGuestProfile = "chkCreateGuestProfile";
	public static final String GuestFirstName = "txtGuestFirstName";
	public static final String GuestLastName = "txtGuestLastName";
	public static final String Switchtoframe = "dialog-body0";
	public static final String StartDate = "//*[@id='pnlPickRoomFilter']/table/tbody/tr[3]/td[2]/table/tbody/tr/td/img";
	public static final String NumberofAdults = "txtNumberOfAdults";
	public static final String SearchButton = "btnAssignRooms";
	public static final String SelectRoomClass = "dgRoomsList_drpRoomClass_0";
	public static final String Select = "btnSelect";
	public static final String Save = "btnSave";
	public static final String ClickonToday = "//th[@class='today']";
	public static final String MarketSegment = "drpMarketingSegment";
	public static final String ReferralDropDown = "drpReferrals";
	public static final String SelectRoomClassRoomAssign = "dgRoomsList_drpRoomClass_0";
	public static final String SelectRoomNumber = "dgRoomsList_drpRooms_0";
	public static final String Guest_Info = "//a[.='Guest Info']";
	public static final String Verify_Guest_Info = "//strong[.='Reservation#: ']";
	public static final String Early_CheckOut = "//*[@id='StayInfo']//p[contains(text(),'Checked out')]";
	public static final String Reservation_Status_Dropdown = "//*[@id='StayInfo']//select";
	public static final String getReservationStatus = "//select[contains(@data-bind,'enable: drpReservationStatusEnabled')]";
	public static final String BulkAction = "//span[contains(text(),'Bulk Action')]";
	public static final String BulkActionCheckOut = "//span[contains(text(),'Check Out')]";
	public static final String BulkActionCheckOutGrid = "//h4[contains(text(),'Bulk Checkout')]";
	public static final String RoomClass_Checkbox = "//input[contains(@data-bind,'checked: deleteRoomClass')]";
	public static final String DeleteRoomClass_Button = "//button[contains(@data-bind,'click: deleteroomClasses')]";
	public static final String DeleteRoomClassesButton = "//div[@class='ant-table-footer']//button";
	public static final String SelectAllRecord = "//select[contains(@data-bind,'options: recordsPerPage')]";
	public static final String RoomClass_SearchButton = "//button[contains(@data-bind,'click: goSearchRoomClasses')]";
	public static final String QAProperty = "property-selector";
	public static final String ReservationRoomStatus = "//span[contains(@data-bind,'StatusId')]";
	public static final String RoomClass_Pages = "//div[contains(@data-bind,'currentPage: currentPageNumber')]//ul[@class='pagination']//li//a";
	public static final String RoomClassPages = "(//ul[@class='pagination'])[2]//li//a";
	public static final String RC_OK_Button = "//div[@class='ant-modal-confirm-btns']//button";

	public static final String SelectAllRecord_1 = "(//div[@class='ant-select-selection__rendered'])[2]";
	public static final String SelectAllRecord_2 = "(//input[@class='ant-checkbox-input'])[1]";
	public static final String Select100TopRecord_1 = "(//ul[@role='listbox'])//li[3]";
	public static final String SelectRoomClassCheckBox = "(//input[@class='ant-checkbox-input'])[1]";
	public static final String RoomclassDeleteButton = "//button[@class='ant-btn ant-btn-danger']";
	public static final String RoomclassDelete_OKButton = "(//button[@class='ant-btn ant-btn-primary'])[2]";
	// Lock Unlock Reservation

	public static final String advancedSearch = "//button[contains(text(),'Advanced')]";
	public static final String basicSearchIcon = "(//button[contains(@value,'Search')])[1]";
	public static final String searchButton = "(//button[contains(@data-bind,'click: $parent.GoAdvanced')])[1]";
	public static final String getInHouseReservations = "//span[contains(text(),'In House')]/ ../span[contains(@data-bind,'text: valueText')]";
	public static final String getAllArrivalsReservations = "//span[contains(text(),'All Arrivals')]/ ../span[1]";
	public static final String inHouseReservation = "//li[@class='in-house active predefinedQueryOption']/div[1]/span[2]";
	public static final String allArrivalsReservation = "//li[@class='in-house active predefinedQueryOption']/div[1]/span[2]";

	public static final String openReservation = "(//a[contains(@data-bind,'text: GuestName')])[1]";
	public static final String lock = "//button[contains(@data-bind,' visible: !DoNotMove()')]";
	public static final String unlock = "//button[contains(@data-bind,' visible: DoNotMove()')]";
	public static final String getAlertMessage = "//div[contains(@data-bind,' visible: ShowError')]";
	public static final String clickCancel = "(//button[contains(@data-bind,'click: CancelClick')])[5]";

	// Copy Reservation
	public static final String click_SearchReservation_Button = "//div[@id='ReservationSearch']//following-sibling::button[@value='Search']";
	public static final String click_Reservation_GuestName = "(//a[@title='RateQuote Guest'])[1]";
	public static final String click_Copy_Button = "//button[@data-bind='enable: EnableCopyButton(), click: CopyReservation']";
	public static final String close_Copied_Reservation = "(//i[@data-bind='click: $parent.closeTab, clickBubble: false, visible: !IsDefaultMenuOption'])[7]";
	public static final String get_Copied_Guest_Name = "(//span[contains(@data-bind,'text: GuestNameUI')])[6]";
	public static final String get_Copied_ReservationTab = "//ul[@class='sec_nav']//following-sibling::span[contains(text(),'copy')]";
	public static final String get_Searched_GuestName = "(//a[contains(@data-bind,'text: GuestName')])[1]";
	public static final String click_RoomPicker = "//button[contains(@data-bind,'enable: EnableRoomPicker()')]";
	public static final String check_AssignRoom_ChecBox = "//input[contains(@data-bind,'enable: AssignRoomsEnabled')]";
	public static final String click_Search_Button = "//button[contains(@data-bind,'click: SearchBtnClick')]";
	public static final String get_Room_Number = "//select[contains(@data-bind,'enable: IsRoomNumberEnabled')]";
	public static final String click_Select_Button_RoomAssignment = ".//*[@id='divPickRoomGrid']//select[contains(@data-bind,'enable: IsRoomNumberEnabled')]";
	public static final String click_Select_Button_RoomChargesChanged = "(//button[contains(@data-bind,'click: SelectClick')])[3]";
	public static final String click_Save_button_ReservationCopy = "(.//*[@id='bpjscontainer_21']//button[contains(@data-bind,'click: PreSave')])[2]";
	public static final String getUnassignedRoomNumber = "//select[contains(@data-bind,'options: Rooms')]";
	public static final String click_cancel_button = ".//*[@id='modalRoomPickerReservation']//button[contains(@data-bind,'click: CancelClick')]";
	public static final String click_copiedRoomPicker = "(//button[contains(@data-bind,'click: showRoomPicker')])[2]";
	public static final String get_copiedRoom_Number = "//select[contains(@data-bind,'options: Rooms')]";
	public static final String click_room_picker_afterCopy = ".//*[@id='StayInfo']//button[contains(@data-bind,'click: showRoomPicker')]";
	public static final String get_copied_unassignedRoomNo = ".//*[@id='divPickRoomGrid']//select[contains(@data-bind,'enable: IsRoomNumberEnabled')]";
	public static final String click_Select_Button_UnassignedRoomAssignment = ".//*[@id='modalRoomPickerReservation']//button[contains(@data-bind,'click: SelectClick')]";
	public static final String click_room_picker_afterCopyUnassignedRoom = "(.//*[@id='StayInfo']//button[contains(@data-bind,'click: showRoomPicker')])[2]";
	public static final String click_cancel_unassigned_room = ".//*[@id='modalRoomPickerReservation']//button[contains(@data-bind,'click: CancelClick')]";
	public static final String getunassignedRoomNumberCopy = ".//*[@id='divPickRoomGrid']/div[2]//select[contains(@data-bind,'options: Rooms')]";
	public static final String Click_LockButton = "//button[@class='btn blue sml marL5'  and contains(@data-bind,'click: DoNotMoveTrue')]";

	// copied guest profile
	public static final String Copied_First_Name = "(//label[.='Guest:']//following-sibling::div/div/div/input[@placeholder='First Name'])[2]";
	public static final String Copied_Last_Name = "(//label[.='Guest:']//following-sibling::div/div/div/input[@placeholder='Last Name'])[2]";
	public static final String Copied_Line1 = "(//input[@placeholder='Line 1'])[3]";
	public static final String Copied_Line2 = "(//input[@placeholder='Line 2'])[3]";
	public static final String Copied_Line3 = "(//input[@placeholder='Line 3'])[3]";
	public static final String Copied_City = "(//input[@placeholder='City'])[3]";
	public static final String CopiedSelect_State = "(//label[.='State:']//following-sibling::div/div/div/select[contains(@data-bind,'TerritoryId')])[3]";
	public static final String CopiedSelect_Country = "(//label[.='Country:']//following-sibling::div/select[contains(@data-bind,'CountryId')])[3]";
	public static final String Copied_Postal_Code = "(//input[@placeholder='Postal Code'])[3]";
	public static final String Copied_Phone_Number = "(//input[contains(@data-bind,'value: PhoneNumber')])[3]";
	public static final String Copied_Alt_Phn_number = "(//label[.='Alternate Phone:']//following-sibling::div/div/div/input[contains(@data-bind,'AltPhoneNumber')])[3]";
	public static final String Copied_email = "(//label[.='Email:']//following-sibling::div/div/div/input[contains(@data-bind,'Email')])[3]";
	// Header_Panel_Elements

	public static final String Inventory_Icon = "rptrMenu_btnMenuItem_3";

	// Season
	public static final String Seasons_SearchButton = "//div[@class='col-md-9 text-right']//button[text()='Search']";
	public static final String Seasons_TableShow = "(//div[@class='table-responsive'])[1]";
	public static final String Seasons_FirstActiveClass = "((//div[@class='table-responsive'])[1]//td)[1]";
	public static final String Seasons_FirstActiveClass_Date = "((//div[@class='table-responsive'])[1]//td)[2]";
	public static final String Seasons_SeasonDetailsPage = "(//div[text()='Season Details'])[1]";
	public static final String SelectItemsPerPage_Seasons = "//select[contains(@data-bind,'NoOfRecordsPerPage')]";
	public static final String DeleteSeason = "//button[contains(@data-bind,'deleteSeasons')]";

	public static final String New_Seasons = "rptrMenu_lbtnAltMenuItem_1";
	public static final String Season_Grid = "(//div[@class='caption'])[2]";
	public static final String Season_Status = "//select[contains(@data-bind,'options: computedStatuses()')]";
	public static final String Select_Property_in_Seasons = "//select[starts-with(@data-bind,'options: PropertiesList')]";
	public static final String New_Season_Button = "//div[@class='footer_buttons']/button[1]";
	public static final String EG_Select_Property_in_Seasons = "drpPropertyList";
	public static final String EG_New_Seasons_Button = "btnNew";

	public static final String NG_Season_Name = "//input[@placeholder='Season Name']";
	public static final String NG_Season_start_Date = "//input[@placeholder='Start Date']";
	public static final String NG_Season_End_Date = "//input[@placeholder='End Date']";
	public static final String NG_Season_Save = "//button[contains(@data-bind, 'saveSeasonDetail')]";
	public static final String NG_Season_Reset = "	//button[contains(@data-bind, 'resetSeasonDetail')]";

	public static final String EG_Season_Name = "txtSeasonName";
	public static final String EG_start_Picker = "//*[@id='ContentArea']/tbody/tr/td[2]/table[2]/tbody/tr[6]/td[2]/a";
	public static final String EG_Season_start_Date = "//td[@class='day today']";
	public static final String EG_End_picker = "//*[@id='ContentArea']/tbody/tr/td[2]/table[2]/tbody/tr[7]/td[2]/a/img";
	public static final String EG_Season_End_Date = "txtDateEnd";
	public static final String EG_Season_Save = "btnSave";
	public static final String SeasonDate_Today = "(//td[@class='day'])[1]";
	public static final String SeasonEndDate_Day = "(//td[@class='day'])[5]";
	public static final String First_Date_RequiredField = "//span[contains(text(),'This field is required.')]";
	public static final String End_Date_RequiredField = "//span[contains(text(),'This field is required.')]";
	public static final String FillMendatoryField = "//*[@id='toast-container']/div/div]";
	public static final String Close_Season = "(//i[contains(@data-bind,'click: $parent.closeTab')])[7]";
	public static final String Unsaved_Data_PopUp = "//*[@id='alertForTab']";
	public static final String Unsaved_No_Button = "//*[@id='alertForTab']/div[3]/button[1]";
	public static final String Unsaved_Yes_Button = "//*[@id='alertForTab']/div[3]/button[2]";
	public static final String EffectiveDays_ValidationMsg = "//span[contains(text(),'Please select at least one day(s)')]";
	public static final String CheckboxMonday = "//input[contains(@data-bind,'checked: IsMonday')]";
	public static final String CheckboxTuesday = "//input[contains(@data-bind,'checked: IsTuesday')]";
	public static final String CheckboxWednesday = "//input[contains(@data-bind,'checked: IsWednesday')]";
	public static final String CheckboxThursday = "//input[contains(@data-bind,'checked: IsThursday')]";
	public static final String CheckboxFriday = "//input[contains(@data-bind,'checked: IsFriday')]";
	public static final String CheckboxSaturday = "//input[contains(@data-bind,'checked: IsSaturday')]";
	public static final String CheckboxSunday = "//input[contains(@data-bind,'checked: IsSunday')]";
	public static final String First_Name_RequiredField = "//span[contains(text(),'This field is required.')]";

	// Select Dates BE

	public static final String BE_Page_Title = "//div[@class='booking_pages_heading']";
	public static final String BE_My_Dates_are_flexible = "//span[@data-bind='    text: MyDatesAreFlexible, visible: smallCalendarWidget']";
	public static final String GetCheckintext = "//*[@id='divNonGroup']/div/div[6]/div[1]/div[7]/div[2]/span[2]/span";
	public static final String GetCheckouttext = "//*[@id='divNonGroup']/div/div[6]/div[1]/div[7]/div[3]/span[2]/span";
	public static final String Get_NumberofAdults = "AvailabilitySearch1_txtAdults";
	public static final String AvailabilitySearch1_btnAvailabilitySearch = "AvailabilitySearch1_btnAvailabilitySearch";
	public static final String AvailabilitySearch1_lbkChkInDate = "AvailabilitySearch1_lbkChkInDate";

	public static final String AvailabilitySearch1_lblChkInDayOfTheWeek = "AvailabilitySearch1_lblChkInDayOfTheWeek";
	public static final String AvailabilitySearch1_lblChkInMonthAndYear = "AvailabilitySearch1_lblChkInMonthAndYear";
	public static final String CheckInText = "//div[@id='AvailabilitySearch1_chkInPanel']/fieldset/legend";
	public static final String AvailabilitySearch1_lbkChkOutDate = "AvailabilitySearch1_lbkChkOutDate";
	public static final String AvailabilitySearch1_lblChkOutDayOfTheWeek = "AvailabilitySearch1_lblChkOutDayOfTheWeek";
	public static final String AvailabilitySearch1_lblChkOutMonthAndYear = "AvailabilitySearch1_lblChkOutMonthAndYear";
	public static final String CheckOutText = "//div[@id='AvailabilitySearch1_chkOutPanel']/fieldset/legend";

	public static final String Reservation_Folio_Options = "//div[@id='ReservationDetail']//a[text()='Folio Options']";
	public static final String Reservation_Folio_Details = "//div[@id='ReservationDetail']//a[text()='Folio Details']";

	public static final String Select_Reservation_Checkin_Policy = "//label[text()='Check In Policy:']//following-sibling::div//select";
	public static final String Select_Reservation_Deposit_Policy = "//label[text()='Deposit Policy:']//following-sibling::div//select";
	public static final String Select_Reservation_NoShow_Policy = "//label[text()=' No Show Policy:']//following-sibling::div//select";
	public static final String Click_Reservation_Cancellation_Policy = "//label[text()='Cancellation Policy:']//following-sibling::div//input";
	public static final String Reservation_Folio = "(//div[@id='ReservationDetail']//a[text()='Folio'])[1]";
	public static final String ReCal_Folio_Options_PopUp_Recalculate = "(//input[contains(@data-bind,'enable: ReCalculateFoliosEnabled, checked')])[2]";

	public static final String Policy_Comparision_PopUp = "//div[@id='policyComparisionPopUp']//h4";
	public static final String Select_Continue_with_OriginalPolicy = "//span[contains(text(),'Continue with Original Policy')]//preceding-sibling::input";
	public static final String Policy_Comparision_PopUp_Continue_Btn = "//div[@id='policyComparisionPopUp']//button[text()='Continue']";

	public static final String Account_Save_Clsoe = "//div[@class='footer_buttons accDetailBtns']//button[.='Save and Close']";
	public static final String CloseUnSavedDataTab = "(//button[contains(@data-bind,'click: Yes')])[3]";

	public static final String Click_SearchBtn_On_AccountsPage = "//button[@data-bind='click: GetAccountsList']";

	public static final String lineItemPendingStatus = "(//img[@src='./Folio_Images/1.gif'])[1]";
	public static final String verifyCheckboxPendingStatus = "(//img[@src='./Folio_Images/1.gif'])[1]/../../td/input";
	public static final String lineItemPostStatus = "(//img[@src='./Folio_Images/2.gif'])[1]";
	public static final String verifyCheckboxPostStatus = "(//img[@src='./Folio_Images/2.gif'])[1]/../../td/input";
	public static final String pendingItemDescription = "(//img[@src='./Folio_Images/1.gif'])[1]/../../td[8]/a";
	public static final String selectCategoryItem = "//select[contains(@data-bind,'options: $parent.CategoriesToShow')]";
	public static final String itemDescription = "//input[contains(@data-bind,'value: $parent.NewFolioDescription')]";
	public static final String foliolineItemAmount = "//input[contains(@data-bind,'value: $parent.NewFolioAmount')]";
	public static final String foliolineItemNotes = "//textarea[contains(@data-bind,'value: $parent.NewFolioNotes')]";
	public static final String foliolineItemAddButton = "(//button[.='Add'])[4]";
	public static final String foliolineItemContinueButton = "//button[contains(@data-bind,'click: ClickContinueButton, enable: isDirty')]";

	public static final String folioSaveButton = "(//button[.='Save'])[1]";

	// Checkin test

	public static final String Already_Checked_In_Confirm_Popup = "//h4[text()='Confirm']";
	public static final String Already_Checked_In_Confirm_Popup_Confirm_Btn = "//button[contains(@data-bind, 'enable: YesButtonEnable')]";

	public static final String Policy_Reset_Btn = "//div[@id='policyDetail']//button[.='Reset']";

	public static final String AvailabilitySearch1_lblPropertyName = "//*[@id='AvailabilitySearch1_lblPropertyName']";
	public static final String AvailabilitySearch1_divGuests = "//*[@id='AvailabilitySearch1_divGuests']/div[2]";
	public static final String GetRoomClassName_Availableroompage = "//*[@id='MainContent_rptrPropertyList_trContent_0']/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/div[1]";
	public static final String GetRoomClassName_Availableroompage2 = "//*[@id='MainContent_rptrPropertyList_trContent_1']/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/div[1]";
	public static final String GetRoomClassName_Availableroompage3 = "//*[@id='MainContent_rptrPropertyList_trContent_2']/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/div[1]";
	public static final String Click_Select_Button = "//input[@value='Select']";
	public static final String Increment_Adult_Count = "//a[@class='incRoomCount flL grayGradient'] [@data-bind='click: incAdults']";
	public static final String Increment_Child_Count = "//a[@class='incRoomCount flL grayGradient'] [@data-bind='click: incChildren']";
	public static final String Check_in_Text = "//*[@id='divNonGroup']/div/div[6]/div[1]/div[7]/div[2]/span[2]/span";
	public static final String Check_out_Text = "//*[@id='divNonGroup']/div/div[6]/div[1]/div[7]/div[3]/span[2]/span";
	public static final String Get_Numberofchilds = "AvailabilitySearch1_txtChildren";

	// Navigating links

	public static final String Tape_Chart = "//*[text()='Tape Chart']";
	public static final String TapeChart = "//div[@class='sec_nav_in container']//span[.='Tape Chart']";
	public static final String Tape_Chart_1 = "(//span[.='Tape Chart'])[2]";
	public static final String Tape_Chart_Grid1 = "//div[@class='datemonthcell']";
	public static final String New_Quote = "(//span[.='New Quote'])[2]";
	public static final String New_QuoteIcon = "//ul[@class='sec_nav']//span[.='New Quote']";

	public static final String New_Quote_Search = "//div[@data-bind='visible: !isSearchLoading()']";
	public static final String Guest_History = "//span[.='Guest History']";
	public static final String Guest_HistoryIcon = "//ul[@class='sec_nav']//span[.='Guest History']";
	public static final String Guest_History_Grid = "//div[@data-bind='visible: showListContent() || showAccountListContent()']";
	public static final String Groups = "(//*[text()='Groups'])[2]";
	public static final String Groups_Backward = "//a[contains(text(),'Groups')]";
	public static final String Groups_Backward_Reservation = "(//span[contains(text(),'Groups')])[2]";
	public static final String Groups1 = "//div[contains(@class,'nav_in container')]//span[text()='Groups']";
	public static final String Groups_text = "//font[@class='TitleText']";
	public static final String Accounts = "head_accounts";
	public static final String Accounts2 = "//ul[@data-bind='foreach: MainNavigation']//li[2]";
	public static final String Accounts_sec_Nav = "//li[@class='accounts here active open']";
	public static final String Accounts_NavIcon = "//img[@class='nav-accounts nav_icon']";
	public static final String Statements = "//span[.='Statements']";
	public static final String Statements_SecNav = "//div[contains(@class,'sec_nav')]//span[.='Statements']";
	public static final String Statement_Content = "//div[@class='main_content']";
	public static final String Unit_owner = "//a[.='Unit Owner Items']";
	public static final String UnitOwner = "//ul[@class='sec_nav']//*[text()='Unit Owner Items']";
	public static final String Unit_owner_Account = "//font[.='Unit Owner Items']";
	public static final String Travel_Agent = "//a[.='Travel Agent Items']";
	public static final String Travel_Agent_Grid = "//*[@id='tdTitle']/font";
	public static final String Manafement_Transfers = "//a[.='Management Transfers']";
	public static final String Manafement_Transfers_Grid = "//*[@id='tdTitle']/font";
	public static final String Account_Distribution = "//a[.='Account Distributions']";
	public static final String Account_Distribution_grid = "//font[@class='TitleText']";
	public static final String Guest_Services = "//span[.='Guest Services']";
	public static final String Guest_Services_1 = "//li [@id='head_guest_services']";
	public static final String Guest_Services_2 = "(//img[@src='assets/img/GuestServices.png'])[2]";
	public static final String Guest_Services_3 = "//img[@class='nav-guest-services nav_icon']";

	public static final String GuestServicesPage = "//section[@class='main_content_out']";
	public static final String Guest_Services_grid = "//*[@id='tdTitle']/font";
	public static final String GuestServicesLabel = "//label[contains(text(),'Room Status')]";
	public static final String House_Keeping = "//a[.='Housekeeping Status']";
	public static final String House_Kepping_status = "//*[@id='tdTitle']/font";
	public static final String Task_List = "//a[.='Task List']";
	public static final String TaskListIcon = "//ul[@class='sec_nav']//*[text()='Task List']";
	public static final String TaskList = "(//a[.='Task List'])[2]";
	public static final String Task_List_grid = "//*[@id='tdTitle']/font";
	public static final String TaskListPage = "//div[@class='container gs-containerM gs-positionR gs-padMT-50']";
	public static final String RoomStatus = "(//a[.='Room Status'])[2]";
	public static final String RoomStatusPage = "//div[@class='container gs-containerM gs-positionR gs-padMT-50']";
	public static final String Room_Maintanence = "//a[.='Room Maintenance']";
	public static final String RoomMaintanence = "//ul[@class='sec_nav']//a[.='Room Maintenance']";
	public static final String Room_Maintanence_Grid = "//*[@id='tdTitle']/font";
	public static final String Inventory = ".//*[@class='nav-inventory nav_icon']";
	public static final String Inventory2 = "(//span[.='Inventory'])[2]";
	public static final String Inventory_Backward = "//span[text()='Inventory']";
	public static final String Inventory_Backward_1 = "//a[@data-id='fncMenuInventory']";
	public static final String Inventory_Grid = "//*[@id='tdTitle']/font";
	public static final String Overview = "//a[.='Overview']";
	public static final String RatesGrid = "//a[.='Rates Grid']";
	public static final String Overview1 = "(//span[.='Overview'])[2]";
	public static final String RatesGrid1 = "(//span[.='Rates Grid'])[2]";
	public static final String Rategrid_Inventory = "//a[.='Rates Grid']";

	public static final String Overview_grid = "//*[@id='tdTitle']/font";
	public static final String Seasons = "//a[.='Seasons']";
	public static final String Seasons1 = "//span[text()='Seasons']";
	public static final String Rates = "//span[.='Rates']";
	public static final String Rates1 = "//a[@id='ucNavSecondary_rptrNavMain_lbtnNavMainItem_2']";

	public static final String Rates2 = "//ul[@id='fncMenuInventory$Menu']//li/a[@href='/rates.aspx']";

	public static final String rates2 = "//a[@id='MainContent_rptrMenu_lbtnMenuItem_2']";

	// public static final String Rates_Grid = "//*[@id='tdTitle']/font";
	public static final String Rates_Grid = "//*[@id='tdTitle']/font[text()='Rates']";

	public static final String Rules = "//a[.='Rules']";
	public static final String Rules1 = "(//span[.='Rules'])[2]";
	public static final String Rules_Grid = "//button[.='New Rule']";
	public static final String Distribution = "(//a[.='Distribution'])[1]";
	public static final String Distribution1 = "(//span[.='Distribution'])[2]";
	public static final String DistributionChannel = "//th[.='Channel Name']";
	public static final String DISTRIBUTIONCHANNEL_DISTRIBUTE = "//input[contains(@data-bind,'checked: IsSelected')]";
	public static final String DISTRIBUTIONCHANNEL_DISTRIBUTE_SOURCENAME = "//span[contains(@data-bind,'text: SourceName')]";
	public static final String DISTRIBUTIONCHANNEL_DISTRIBUTE_SOURCEURL = "//span[contains(@data-bind,'text: SourceUrl')]";
	public static final String DISTRIBUTIONCHANNEL_DISTRIBUTE_SOURCEDEFAULTSTATUS = "//select[contains(@data-bind, 'StatusName')]";

	public static final String Distribution_Gird = "//div[@id='DistributionMain']";
	public static final String policies = "(//a[.='Policies'])[1]";// span[.='Policies']";
	public static final String policies1 = "(//span[.='Policies'])[2]";
	public static final String Policy_Button = "//button[.='New Policy']";
	public static final String GS_Setup = "//a[@href='/Menu.aspx?tab=fncMenuSetup']";
	public static final String Setup = "//span[.='Setup']";
	public static final String SetupIcon = "//img[@class='nav-setup nav_icon']";

	public static final String Setup_Grid = "//*[@id='tdTitle']/font";
	public static final String Propeties = "//a[.='Properties']";
	public static final String Propety_Grid = "//*[@id='tdTitle']/font";
	public static final String Roomclass = "//a[.='Room Classes']";
	public static final String Roomclass1 = "(//a[.='Room Classes'])[2]";
	public static final String Roomclass_grid = "//button[.='New Room Class']";
	public static final String Taxes = "(//a[.='Taxes'])[1]";
	public static final String Taxes1 = "(//span[.='Taxes'])[2]";
	public static final String Taxes_Grid = "//*[@id='tdTitle']/font";
	public static final String Ledger_Accounts = "//a[.='Ledger Accounts']";
	public static final String Ledger_Accounts1 = "(//span[.='Ledger Accounts'])[2]";
	public static final String Merchant_Services = "//a[.='Merchant Services']";
	public static final String Merchant_Services1 = "(//span[.='Merchant Services'])[2]";
	public static final String Merchant_Services_Grid = "//*[@id='tdTitle']/font";
	public static final String Document_Template = "//a[.='Document Templates']";
	public static final String Document_Template1 = "(//span[.='Document Templates'])[2]";
	public static final String Documnet_Template_Grid = "//*[@id='tdTitle']/font";
	public static final String List_Management = "//a[.='List Management']";
	public static final String List_Management_Grid = "//*[@id='tdTitle']/font";
	public static final String Task_Management = "//a[.='Task Management']";
	public static final String Admin = "//span[.='Admin']";
	public static final String AdminIcon = "//img[@class='nav-admin nav_icon']";
	public static final String Client_info = "//a[.='Client Info']";
	public static final String Client_info1 = "(//span[.='Client Info'])[2]";
	public static final String Client_info_grid = "//*[@id='tdTitle']/font";
	public static final String Users = "//a[.='Users']";
	public static final String Users1 = "(//span[.='Users'])[2]";
	public static final String Users_grid = "//button[.='New User']";
	public static final String Roles = "(//span[.='Roles'])[2]";
	public static final String Roles1 = "(//a[.='Roles'])[1]";
	public static final String Roles_page = "//button[.='New Role']";
	public static final String Night_audit = "//span[.='Night Audit']";

	public static final String NightAudit = "//img[contains(@class,'nav-night-audit nav_icon')]";
	public static final String NightAuditIcon = "//ul[@class='nav_des']//*[text()='Night Audit']";
	public static final String Period_status = "//*[@id='tdTitle']/font";
	public static final String Statement_Grid = "//*[@id='tdTitle']/font";

	public static final String Seasons_button = "//button[.='New Season']";

	public static final String Ledger_Account_Grid = "//*[@id='tdTitle']/font";
	public static final String Admin_Grid = "//*[@id='tdTitle']/font";
	public static final String Distribution_syndication = "//a[.='Syndication']";
	public static final String Distribution_Blackouts = "//a[.='Blackouts']";
	public static final String Reports = "//span[text()='Reports']";
	public static final String ReportsIcon = "//img[contains(@class,'nav-reports nav_icon')]";
	public static final String Reports_Backward = "//li[@id='head_reports']//img";
	public static final String Reports_Grid = "//*[@id='tdTitle']/font";
	public static final String Account_Balance = "//a[.='Account Balances']";
	public static final String Account_Balance_Grid = "//*[@id='tdTitle']/font";
	public static final String Ledger_Balances = "//a[.='Ledger Balances']";
	public static final String Ledger_Balances_Grid = "//*[@id='tdTitle']/font";
	public static final String Merchant_Trans = "//a[.='Merchant Trans']";
	public static final String Merchant_Trans_grid = "//*[@id='tdTitle']/font";
	public static final String Daily_flash = "//a[.='Daily Flash']";
	public static final String Daily_flash_grid = "//*[@id='tdTitle']/font";
	public static final String Room_Forecast = "//a[.='Room Forecast']";
	public static final String Room_Forecast_grid = "//*[@id='tdTitle']/font";
	public static final String Net_Sales = "//a[.='Net Sales']";
	public static final String Net_Sales_1 = "(//a[.='Net Sales'])[1]";

	public static final String Net_Sales_Grid = "//*[@id='tdTitle']/font";
	public static final String Advance_Depos = "//a[.='Advance Deposit']";
	public static final String Advance_Depos_grid = "//*[@id='tdTitle']/font";
	public static final String Reservation_Backward = "//*[@id='head_reservations']/a";
	public static final String Reservation_Backward_1 = "//img[@class='nav-reservation nav_icon']";
	public static final String Reservation_Backward_2 = "(//img[@src='assets/img/Reservation.png'])[2]";
	public static final String Reservation_Backward_3 = "//a[@data-id='fncMenuReservations']";
	public static final String Reservation_Backward_4 = "(//a[@href='/index.html#reservationSearch'])[2]";
	public static final String CPReservationBackward = "//a[contains(@data-id,'fncMenuReservations')]";
	public static final String CP_Reservation_Backward = "//ul[contains(@data-bind,'foreach: MainNavigation')]//span[text()='Reservations']";


	public static final String closeGuestHistoryRes = "(//i[contains(@data-bind,'click: $parent.closeTab')])[7]";
	public static final String Rule_Broken = ".tapechartdatecell.Available.NoRate.popovers";
	public static final String Rule_Broken_PopUp = "//h4[text()='Rules Broken']";
	public static final String Rule_Broken_Cancel = "//div[@id='rulesBrokenConfirmation']//button[.='Cancel']";
	public static final String ClickSearchedGuestProfile = "//a[contains(@data-bind,'click: $parent.ShowSelectedAccount')]";
	public static final String UnsavedData_YesBtn = "(//button[contains (@data-bind,'click: Yes')])[3]]";

	// properties

	public static final String NewProperty_Button = "MainContent_btnNew";
	public static final String Property_Options = "//input[@id='MainContent_btnPropertyAdvance']";
	public static final String ChkLongerthan = "//input[@id='MainContent_chkLongerthan']";
	public static final String Enter_txttaxexempt = "MainContent_txttaxexempt";
	public static final String Properties_Save = "MainContent_btnSave";
	public static final String Enter_PropertyName = "MainContent_txtPropertyName";
	public static final String Enter_LegalyName = "MainContent_txtLegalName";
	public static final String Select_Town = "MainContent_drpTownID";
	public static final String Enter_Description = "MainContent_txtPropertyDescriptionShort";
	public static final String Enter_Contact = "MainContent_txtMailing_contactName";
	public static final String Enter_Email = "MainContent_txtMailing_email";
	public static final String Enter_Phone = "MainContent_txtMailing_phoneNumber";
	public static final String Enter_Property_Address = "MainContent_txtMailing_address1";
	public static final String Enter_Property_City = "MainContent_txtMailing_city";
	public static final String Select_Property_State = "MainContent_drpMailing_territoryID";
	public static final String Enter_Property_PostalCode = "MainContent_txtMailing_postalCode";
	public static final String Property_Publish_Button = "MainContent_btnPublish";
	public static final String Use_MailingAddress_Checkbox = "MainContent_ckUseMailingInfo";
	public static final String Delete_Property_Input = ".dgItem input";
	public static final String Property_Delete_Button = "MainContent_btnDelete";

	// Reservation page

	public static final String Guestinfo = "//a[.='Guest Info']";
	public static final String SaveAndCloseReservation = "//*[@id='ReservationDetail']//following-sibling::button[contains (@data-bind,'click: SaveAndClose')]";

	// Reservation Advanced Search

	public static final String AdvancedSearched_Button = "//button[.='Advanced']";
	public static final String AdvancedSearchPage_load = "//span[@id='bpjscontainer_18']";
	public static final String AdvancedSearched_RoomClass = "//select[contains(@data-bind,'RoomClassName')]//following-sibling::div/button";
	public static final String AdvancedSearched_SelectedRoomClass = "//select[contains(@data-bind,'RoomClassName')]//following-sibling::div/button/span";
	public static final String AdvancedSearched_RoomClassDropDown = "//select[contains(@data-bind,'RoomClassName')]//following-sibling::div/div/ul/li";
	public static final String AdvancedSearched_Source = "//select[contains(@data-bind,'Source')]//following-sibling::div/button";
	public static final String AdvancedSearched_SourceDropdown = "";

	public static final String AdvancedSearch_StayFromDate = "(//input[@name='daterangepicker_start'])[1]";
	public static final String AdvancedSearch_StayToDate = "(//input[@name='daterangepicker_end'])[1]";
	public static final String AdvancedSearch_ResultNextPage = "(//span[@id='bpjscontainer_18']  //i[@class = 'fa fa-angle-double-right'])[1]";

	public static final String AdvancedSearch_SearchButton = "(//button[contains(.,'Search')])[1]";
	public static final String Number_Of_Reservations = "//span[@id='bpjscontainer_23']//child::table/tbody/tr";
	public static final String ArrivalDate = "//span[contains(@data-bind,'text: DateStart')]";
	public static final String DepartureDate = "//span[contains(@data-bind,'text: DateEnd')]";
	public static final String pendingDepartures = "//span[.='Pending Departures']//preceding-sibling::span";
	public static final String DateIcon = "(//i[@class='fa fa-calendar'])[1]";
	public static final String allDepartures = "//span[.='All Departures']//following::span[1]";
	public static final String Searched_RoomClass = "//span[contains(@data-bind,'text: RoomClassName')]";
	public static final String Searched_ResNumber = "//span[contains(@data-bind,'text: ConfirmationNumber')]";

	public static final String Searched_GuestName = "//a[contains(@data-bind,'text: GuestName')]";

	// Reservation button

	public static final String New_Reservation_Button = "//a[.='New Reservation']";
	public static final String GuestProfileCheckbox = "//span[.='Create Guest Profile']//preceding-sibling::input";
	public static final String New_Reservation_Button1 = "(//a[contains(@data-bind,'click: NewReservation')])[1]";
	public static final String New_Reservation_Tab = "//span[.='New Reser...']";
	public static final String New_Reservation_Page_Load = "//*[@id='ReservationDetailMain']/ul";
	public static final String Reservation_market_Segment = "//select[contains(@data-bind,'MarketSegmentValue')]";
	public static final String Reservation_Referral = "//select[contains(@data-bind,'ReferralsValue')]";
	public static final String Enter_Travel_Agent = "//input[@placeholder='Find Account Profile']";
	public static final String Enter_Ext_Reservation = "//input[@placeholder='Ext Reservation']";
	public static final String Enter_Guest_Profile = "//input[@placeholder='Find Guest Profile']";
	public static final String Select_Saluation = "//label[.='Guest:']//following-sibling::div/div/div/select";
	public static final String Enter_First_Name = "//label[.='Guest:']//following-sibling::div/div/div/input[@placeholder='First Name']";
	public static final String Enter_Contact_First_Name = "//label[.='Contact:']//following-sibling::div/div/div/input[@placeholder='First Name']";
	public static final String Enter_Last_Name = "//label[.='Guest:']//following-sibling::div/div/div/input[@placeholder='Last Name']";
	public static final String Enter_Address_Search = "//input[@placeholder='Search Address']";
	public static final String Enter_Line1 = "//input[@placeholder='Line 1']";
	public static final String Enter_Line2 = "//input[@placeholder='Line 2']";
	public static final String Enter_Line3 = "//input[@placeholder='Line 3']";
	public static final String Enter_City = "//input[@placeholder='City']";
	public static final String Select_Country = "//label[.='Country:']//following-sibling::div/select[contains(@data-bind,'CountryId')]";
	public static final String Select_State = "//label[.='State:']//following-sibling::div/div/div/select[contains(@data-bind,'TerritoryId')]";
	public static final String Enter_Postal_Code = "//input[@placeholder='Postal Code']";

	// public static final String
	// Enter_Phone_Number="//label[.='Phone:']//following-sibling::div/p/span[2]";
	public static final String Enter_Phone_Number = "//input[contains(@data-bind,'value: PhoneNumber')]";
	public static final String Enter_Alt_Phn_number = "//label[.='Alternate Phone:']//following-sibling::div/div/div/input[contains(@data-bind,'AltPhoneNumber')]";
	public static final String Enter_email = "//label[.='Email:']//following-sibling::div/div/div/input[contains(@data-bind,'Email')]";
	public static final String Enter_Account = "//input[@placeholder='Find Account']";

	public static final String AccountTaxExempt = "//span[contains(@data-bind,'text: $parent.Accountname')]";
	public static final String Check_IsTaxExempt = "//input[contains(@data-bind,' IsTaxExempt')]";
	public static final String Enter_TaxExemptId = "//input[contains(@data-bind,' TaxExemptId')]";
	public static final String Check_isLongstay = "//label//input[contains(@data-bind,' IsLongStay')]";
	public static final String Select_Payment_Method = "//select[contains(@data-bind,'PaymentTypeId')]";
	public static final String Select_Payment_Method_CopyRes = "(//select[contains(@data-bind,'PaymentTypeId')])[2]";
	public static final String Enter_Account_Number = "//input[@placeholder='Account Number']";
	public static final String Enter_Exp_Card = "//input[contains(@data-bind,' BillingCreditCardExpiryDate')]";
	public static final String Enter_Billing_Note = "//input[contains(@data-bind,' BillingNotes')]";
	public static final String Add_Note_Reservation = "//a[@data-target='#ReservationNotesCreate']";
	public static final String Click_RoomPicker = "//button[contains(@data-bind,'EnableRoomPicker')]";
	// Tax exempt
	public static final String TaxExmpt_Popup = "//h4[.='Tax Exempt']";
	public static final String Set_TaxExe_Button = "//button[.='Set Exemption Anyway']";
	public static final String Cancel_TaxExe_Button = "//button[.='Cancel Exemption']";

	// Reset
	public static final String Res_Reset_Button = "//div[@id='ReservationDetail']//button[text()='Reset']";
	public static final String GestInfo_Tab = "//a[text()='Guest Info']";
	public static final String Reservation_ModuleLoading = "//div[contains(@data-bind,'showLoadingForReservation')]//div[@class='modules_loading']";
	public static final String NewReservation_ModuleLoading = "//div[@id='ReservationDetail']//div[@class='modules_loading']";
	public static final String RoomAssignment_ModuleLoading = "//*[@id='modalRoomPickerReservation']//div[@class='modules_loading']";
	public static final String Room_Assignment_PopUp = "//h4[.='Room Assignment']";
	public static final String Reservation_BookButton = "//div[@id='ReservationDetail']//button[text()='Book']";
	public static final String Click_Arrive_Datepicker = "//input[contains(@data-bind,'DateArrived')]//following-sibling::div/a/i";
	public static final String Click_Today = "//table[@class='datepicker-table-condensed table-condensed']//tfoot/tr[1]/th";
	public static final String Click_ActiveDate = "(//table[@class='datepicker-table-condensed table-condensed'])[1]//following-sibling::td[@class='active day']";
	public static final String Click_Tomorrow = "/html/body/div[18]/div[1]/table/tbody/tr[2]/td[4]";
	// table[@class='datepicker-table-condensed
	// table-condensed']//tbody//td[@class='active
	// day']//following-sibling::td[1]";
	public static final String Enter_Nigts = "//input[@data-bind='value: Nights']";
	public static final String Enter_Adults = "//input[contains(@data-bind,'value: Adults')] [@type='text']";
	public static final String Enter_Children = "//input[contains(@data-bind,'value: (Children')]";
	public static final String selectRackRate = "//select[contains(@data-bind,'options: RatePlanList')]";

	public static final String AdhocRate = "//input[@type='text' and contains(@data-bind,'AdHocRate')]";
	public static final String Enter_Rate_Promocode = "//input[contains(@data-bind,'RatePromoCode')]";
	public static final String Check_Assign_Room = "//input[contains(@data-bind,'AssignRoomsEnabled')]";
	public static final String RoomPicker_ModuleLoading = "//div[@id='modalRoomPickerReservation']//div[@class='modules_loading']";
	public static final String Click_Search = "//button[contains(@data-bind,'SearchBtnClick')]";
	public static final String Validation_Text_NoRooms = "//div[.='Room Classes are not available for the search criteria. Please change the criteria and try again']";
	public static final String Select_property_RoomAssign = "//select[contains(@data-bind,'SelectedProperty')]";
	public static final String Select_property_RoomAssign2 = "//div[@id='modalRoomPickerReservation']//select[contains(@data-bind,'SelectedProperty')]";
	public static final String Verify_Room_Grid = "//div[@id='divPickRoomGrid']";
	public static final String Select_Room_Class = "//tbody//select[contains(@data-bind,'RoomClassId')]";
	public static final String Select_Room_Number = "//tbody//select[contains(@data-bind,'RoomId')]";
	public static final String Select_Room_Number2 = "(//tbody//select[contains(@data-bind,'RoomId')])[2]";
	public static final String Validating_UnAssgined_DDL = "//tbody[@data-bind='foreach: RoomListWithRules']//td[3]/select[contains(@disabled,'')]";
	public static final String Click_Select = "//*[@id='modalRoomPickerReservation']/div[3]/div//button[.='Select']";
	public static final String RoomCharges_Select = "//span[@id='bpjscontainer_68']//following-sibling:: button[text()='Select']";
	public static final String Verify_RulesBroken_Popup = ".//*[@id='myModalLabelforRuleMessage']";
	public static final String Click_Continue_RuleBroken_Popup = "//button[contains(@data-bind,'isOverRideAllowed(), text: btnContinue')]";
	public static final String Verify_Toaster_Container = "//div[@id='toast-container']";
	public static final String Toaster_Title = "toast-title";// "//
																// div[@class='toast-title']";
	public static final String Toaster_Message = "toast-message";// "//div[@class='toast-message']";
	public static final String Toaster_Message_Xpath = "//div[@class='toast-message']";
	public static final String Toaster_Message_Close = "//div[@id='toast-container']//button[@class='toast-close-button']";
	public static final String AccountName = "//span[contains(@data-bind,'text: Account.AccountFirstName()+')]";
	public static final String AccountNo = "//span[contains(@data-bind,'text: Account.AccountFirstName()+')]//following-sibling::span";
	public static final String AccountNo_1 = "//input[contains(@data-bind,'value: AccountNoToDisplay')]";

	public static final String Get_Property_Name = "//*[@id='StayInfo']//following-sibling::label[.='Property:']//following-sibling::div/p";
	public static final String Get_Account_Name = "//*[@id='StayInfo']//following-sibling::label[.='Account:']//following-sibling::div/a";
	public static final String Get_RoomClass_Status = "//*[@id='StayInfo']//following-sibling::label[.='Room(s):']/following-sibling::div/p/span";
	public static final String Get_RoomClass_Status_CopyUnassigned = "(//*[@id='StayInfo']//following-sibling::label[.='Room(s):']/following-sibling::div/p/span)[2]";
	public static final String Click_Save_ReservationDetails = "//button[.='Save']";
	public static final String ToasterMessageGuaranteedRes = "//div[@class='toast-message']";
	public static final String Verify_Depos_policy = "//div[@aria-hidden='false']//h4[.='Deposit Policy']";
	public static final String Click_Without_Deposit = "//div[@aria-hidden='false']//div[3]/button[.='Without Deposit']";
	public static final String First_Element_In_Search_Result = ".//*[@id='bpjscontainer_21']//tbody/tr[1]/td[1]/a";
	public static final String PolicySearch_ModuleLoading = "//div[contains(@data-bind,'visible: isSearchLoading')]//div[@class='row']//div[@class='modules_loading']";
	public static final String Click_Save_Close = "//div[@class='footer_buttons']//button[.='Save and Close']";
	public static final String Reservation_Status = "//label[contains(text(),'Reservation Status: ')]/following-sibling::div//select";

	public static final String Room_Assignment_PopUp_Error = "//*[@id='modalRoomPickerReservation']//div[contains(@class,'alert')]";
	public static final String Room_Selector_In_Room_Assignment_PopUp = "(//*[@id='modalRoomPickerReservation']//select[contains(@data-bind,'options: Rooms')])[1]";
	public static final String ReCalculate_Folio_Options_PopUp = "//div[@id='divRoomPickerReCalculateFolios']";
	public static final String ReCal_Folio_Options_PopUp_No_Charge_Changed = "//div[@id='divRoomPickerReCalculateFolios']//input[@value='0']";

	public static final String ReCal_Folio_Options_PopUp_Select_Btn = "//div[@id='divRoomPickerReCalculateFolios']//button[text()='Select']";
	public static final String ReCal_Folio_Options_PopUp_Cancel_Btn = "//div[@id='divRoomPickerReCalculateFolios']//button[text()='Cancel']";

	public static final String Resell_Rooms_Popup = "//h4[text()='Resell Rooms']";
	public static final String Resell_Rooms_Popup_Continue_Btn = "//div[contains(@data-bind,'excludeRoomContainer')]//button[text()='Continue']";
	public static final String Resell_Rooms_Popup_Cancel_Btn = "//div[contains(@data-bind,'excludeRoomContainer')]//button[text()='Cancel']";
	public static final String ReservationPage_Reset_Btn = "//div[@class='footer_buttons']//button[.='Reset']";

	// Task
	public static final String NotesType = "//span[text()='Room Move']";

	// LineItems
	public static final String Add_LineItem = "//button[contains(@data-bind,'fnAddFolioItem')]";
	public static final String Select_LineItem_Category = "//select[contains(@data-bind,'LedgerAccountItems')]";
	public static final String Enter_LineItem_Amount = "//td[@class='lineitem-amount']/input";
	public static final String Commit_LineItem = "//button[.='Commit']";

	// New Quote

	public static final String Click_Arrive_DatePicker = "//input[@placeholder='Arrive']/following-sibling::div/a/i";
	public static final String Enter_RateQuoteNights = "//input[@data-bind='value: RateQuoteNights']";
	public static final String Enter_RateQuoteAdults = "//input[@data-bind='value: RateQuoteAdults']";
	public static final String Enter_RateQuoteChildren = "//input[@data-bind='value: RateQuoteChildren']";
	public static final String Select_RateQuoteRatePlanList = "//select[contains(@data-bind,'options: RateQuoteRatePlanList')]";
	public static final String Enter_RateQuotePromoCode = "//input[@data-bind='value: RateQuotePromoCode']";
	public static final String Check_RateQuoteAssignRooms = "//input[@data-bind='checked: RateQuoteAssignRooms']";
	public static final String Click_searchRateQuote = "//button[@data-bind='click: searchRateQuote']";
	public static final String RoomClassHeader = "//tr[@rowtype='RoomClassHeader']";
	public static final String Click_clearRateQuote = "//button[@data-bind='click: clearRateQuote']";
	public static final String Verify_tblRateQuoteGrid = "//*[@id='tblRateQuoteGrid']";
	public static final String Verify_Number_rate_book_green = "//button[@class='rate-book-green']";
	public static final String Verify_Number_rate_book_red = "//button[@class='rate-book-red']";
	public static final String Get_Property_Id = "//tr[@rowtype='RoomClassHeader']";
	public static final String Click_First_Book_Icon = "//tr[@rowtype='RoomClassHeader']/following-sibling::tr[1]//td[contains(@id,'td_Action')]/button[2]";
	public static final String Click_Rate_Quote_Book = "//button[contains(@data-bind,'isOverRideAllowed(), text: btnQuoteOrBook')]";
	public static final String Verify_OverBook_popup = "//h4[@id='myModalLabelforRuleMessage'] [@data-bind='text: ruleTitle']";
	public static final String Click_Continue_OverBook_Popup = "//button[contains(@data-bind,'isOverRideAllowed(), text: btnContinue')]";

	// Rate Quote
	// Accounts

	public static final String Select_Account_Type = "//div[@id='accountSearchFilter']//label/following-sibling::div/select[contains(@data-bind,'AccountTypeName')]";
	public static final String Click_New_Account = "//button[.='New Account']";
	public static final String Verify_New_Account_tab = "//span[.='New Accou...']";
	public static final String GetAccount_Number = "//input[contains(@data-bind,'value: AccountNoToDisplay')]";
	public static final String Verify_New_Account_Page_Load = "//div[@data-bind='foreach: AccountDetails']";
	public static final String Account_ModuleLoading = "//div[@class='AccountDetail']//div[@class='modules_loading']";
	public static final String Payment_ModuleLoading = "//div[@id='ReservationPaymetItemDetail']//div[@class='modules_loading']";
	public static final String Enter_Account_Name = "//input[contains(@data-bind,'value: Account.AccountName')]";
	public static final String CardInfoPopup_ModuleLoading = "//div[@id='ReservationPaymentDetailCreditCardInfo']//div[@class='modules_loading']";

	public static final String AccountPayment_ModuleLoading = "//div[@id='AccountPaymetItemDetail']//div[@class='modules_loading']";
	public static final String Acc_Enter_Account_Name = "(//div[@class='col-md-9'])[10]//input";

	public static final String Verify_Account_Type = "//div[@data-bind='foreach: AccountDetails']//label/following-sibling::div/select[contains(@data-bind,'AccountTypeName')]";
	public static final String Select_Market_Segment = "//select[contains(@data-bind,'options: Marketings')]";
	public static final String Select_Referrals = "//select[contains(@data-bind,'options: Referrals')]";
	public static final String Account_Enter_First_Name = "//input[contains(@data-bind,'value: Account.MailingAddress.FirstName')]";
	public static final String Account_Enter_Last_Name = "//input[contains(@data-bind,'value: Account.MailingAddress.LastName')]";
	public static final String Account_Phone_number = "//input[contains(@data-bind,'value: Account.MailingAddress.PhoneNumber')]";
	public static final String Account_Enter_AltPhoneNumber = "//input[contains(@data-bind,'value: Account.MailingAddress.AltPhoneNumber')]";
	public static final String Account_Enter_Line1 = "//input[contains(@data-bind,'value: Account.MailingAddress.MailingAddress1')]";
	public static final String Account_Enter_Line2 = "//input[contains(@data-bind,'value: Account.MailingAddress.MailingAddress2')]";
	public static final String Account_Enter_Line3 = "//input[contains(@data-bind,'value: Account.MailingAddress.MailingAddress3')]";
	public static final String Account_Enter_Email = "//input[contains(@data-bind,'value: Account.MailingAddress.Email')]";
	public static final String Account_Enter_City = "//input[contains(@data-bind,'value: Account.MailingAddress.City')]";
	public static final String Select_Account_State = "//div[@data-bind='foreach: AccountDetails']//label/following-sibling::div/select[contains(@data-bind,'value: $parent.Account.MailingAddress.TerritoryId')]";
	public static final String Account_Enter_PostalCode = "//input[contains(@data-bind,'value: Account.MailingAddress.PostalCode')]";
	public static final String Account_Check_Mailing_info = "//input[contains(@data-bind,'checked: Account.UseMailingInfo')]";
	public static final String Account_save = "//div[@class='footer_buttons accDetailBtns']//button[.='Save']";
	public static final String Account_Folio = "//div[@class='AccountDetail']//a[.='Folio']";
	public static final String Account_AccountTab = "//div[@class='AccountDetail']//a[.='Account']";
	public static final String FolioOptions = "(//a[contains(@data-bind,'click: ShowFolioOption')])[2]";
	public static final String Account_pay = "//div[@class='AccountDetail']//button[.='Pay']";
	public static final String Account_Add = "//div[@class='AccountDetail']//button[.='Add']";
	public static final String Verify_Account_line_item = "//div[@class='AccountDetail']//tbody[contains(@data-bind,'getElement: ComputedFolioItemsElement')]";
	public static final String Select_Line_item_Category = "//div[@class='AccountDetail']//select[contains(@data-bind,'value: SelectedLedgerAccount')]";
	public static final String Enter_Line_item_Amount = "//div[@class='AccountDetail']//input[contains(@data-bind,'value: Amount')]";
	public static final String Enter_Gift_Line_Item_Amount = "//div[@class='AccountDetail']//td[@class='lineitem-amount']//input[contains(@data-bind,'value: Amount')]";
	public static final String Click_Commit = "//div[@class='AccountDetail']//button[.='Commit']";
	public static final String Select_Account_paymethod = "//div[@id='AccountPaymetItemDetail']//select[contains(@data-bind,'options: $parent.PaymentMethodsList')]";
	public static final String Click_Account_Card_info = "//div[@id='AccountPaymetItemDetail']//button[contains(@data-bind,'click: $root.ShowCreditCardInfoPopup')]";
	public static final String Verify_Account_Paymnet_info_popup = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]//h4";
	public static final String Enter_Account_Card_Name = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]//input[contains(@data-bind,'value: NameOnCard')]";
	public static final String Enter_CC_Account_Number = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]//input[contains(@data-bind,'value: $root.txtBilling_AccountNumber')]";
	public static final String Enter_ExpiryDate_Account = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]//input[contains(@data-bind,'value: ExpDate')]";
	public static final String Enter_CCV_Account = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]//input[contains(@data-bind,'value: CvvCode')]";
	public static final String Click_Ok_Account = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]//button[.='OK']";
	public static final String Select_Authorization_Type_Account = "//div[@id='AccountPaymetItemDetail']//select[@name='AuthorizationTypeOut']";
	public static final String Enter_Change_Amount = "//div[@id='AccountPaymetItemDetail']//input[contains(@data-bind,'value: $parent.formattedAmount')]";
	public static final String Click_Account_Pay_Continue = "//div[@id='AccountPaymetItemDetail']//button[contains(@data-bind,'click: btnSaveTranPayClick')]";
	public static final String Click_Process_Account = "//div[@id='AccountPaymetItemDetail']//button[contains(@data-bind,'click: $root.btnProcess_Click')][1]";
	public static final String Click_AddPayment_Account = "//div[@id='AccountPaymetItemDetail']//button[contains(@data-bind,'click: $root.btnProcess_Click')][2]";
	public static final String Account_Payment_Method = "//div[@id='AccountPaymetItemDetail']//following-sibling::div[@id='reservationList']//span[.='MC']";
	public static final String Get_Line_item_Account = "//div[@class='AccountDetail']//tr[1]//td[@class='lineitem-description-2']//a";
	public static final String Get_Line_item_Account_1 = "//a[contains(@data-bind,'text: $data.Description')]";
	public static final String Verify_Ending_Balance = "//label[.='Ending Balance: ']//following-sibling::span[@class='pamt']";
	public static final String Select_Property_lineitem = "//div[@class='AccountDetail']//select[contains(@data-bind,'value: SelectedProperty')]";
	public static final String Apply_Advance_Deposite = "//div[@id='ruleMessageForInnroad']//h4[.='Apply Advance Deposit']";
	public static final String Click_Continue_Adv_Deposite = "//*[@id='ruleMessageForInnroad']//button[.='Continue']";
	public static final String Acc_Add_Button = "//a[.='Add']";
	public static final String Verify_Notes_Popup = "//div[@class='modal-scrollable']//div[@id='NotesDetailPopup']//h4[.='Note Details']";
	public static final String Acc_Note_Enter_sub = "//div[@class='modal-scrollable']//div[@id='NotesDetailPopup']//input[contains(@data-bind,'value: Subject')]";
	public static final String Acc_Note_Enter_Details = "//div[@class='modal-scrollable']//div[@id='NotesDetailPopup']//textarea[contains(@data-bind,'value: NoteDetails')]";
	public static final String Acc_Note_Select_Note_Type = "//div[@class='modal-scrollable']//div[@id='NotesDetailPopup']//select[contains(@data-bind,'options: $parent.NoteTypes')]";
	public static final String Acc_Note_Select_Note_Status = "//div[@class='modal-scrollable']//div[@id='NotesDetailPopup']//select[contains(@data-bind,'options: $parent.Statuses')]";
	public static final String Acc_Note_Action_Req = "//div[@class='modal-scrollable']//div[@id='NotesDetailPopup']//input[contains(@data-bind,'checked: IsActionRequired')]";
	public static final String Acc_Note_Save = "//div[@class='modal-scrollable']//div[@id='NotesDetailPopup']//button[.='Save']";
	public static final String Verify_Add_line_Notes = "//tr[@data-bind='visible : showCancelledNotes()']";
	public static final String NewReservation_Button = "//button[.='New Reservation']";
	public static final String Account_Save_Button = "//div[@class='footer_buttons accDetailBtns']//button[.='Save']";

	public static final String AccountsPage_Reset_Btn = "//div[@class='footer_buttons accDetailBtns']//button[.='Reset']";
	public static final String AccountsPage_Click_All = "//div[@id='accountSearchFilter']//a[text()='All']";
	public static final String First_Element_In_Account_SearchResults = "(//tbody[@data-bind='foreach: AccountList']//tr)[1]//a";
	public static final String Enter_AccountName_On_SearchPage = "//input[@data-bind='value: AccountName']";
	public static final String Enter_AccountName_On_SearchPages = "//input[@data-bind='value: AccountName']";
	public static final String Enter_AccountNumber_On_SearchPages = "//div[@id='accountSearchFilter']//input[@data-bind='value: AccountNumber']";
	public static final String Search_Account_Result = "//span[contains(@data-bind,'text: AccountNumber')]";
	public static final String Search_Account_Checkbox = "//input[contains(@data-bind,'checked: DeleteAccount')]";
	public static final String Account_Delete_Button = "//button[contains(@data-bind,' click: deleteAccounts')]";
	public static final String Search_Accounts_Button = "//div[@id='accountSearchFilter']//button[contains(@data-bind,'click: GetAccountsList')]";
	public static final String Search_Account_Name = "//a[contains(@data-bind,'text: AccountName')]";
	public static final String AccountPage = "//a[.='Account']";
	public static final String getUnsassignedResCount = "//span[.='Unassigned']//preceding-sibling::span";
	// Basic Search

	public static final String ReservationSearch_ModuleLoading = "(//div[contains(@data-bind,'visible: isSearchLoading')]//div[@class='modules_loading'])[1]";

	public static final String BasicGuestName = "//input[contains(@data-bind,'textInput: BasicGuestName')]";
	public static final String noRecordAlertMessage = "//div[@class='alert alert-warning']";
	public static final String Get_Guest_Name = "//a[contains(@data-bind,'text: GuestName')]";
	public static final String Click_BasicSearch = "//div[@id='ReservationSearch']//following-sibling::button[@value='Search']";
	public static final String Verify_Search_Loading_Gird = "//div[contains(@data-bind,'visible: !isSearchLoading()')]//following-sibling::section/div";
	public static final String Basic_Res_Number = "//div[@id='ReservationSearch']//input[@id='txtResNum']";
	public static final String Get_Res_Number = "//span[@data-bind='text: ConfirmationNumber']";
	public static final String Enter_Guest_Name = "//input[contains(@data-bind,'textInput: BasicGuestName')]";
	public static final String Searched_AccountName = "//a[contains(@data-bind,'click: $parent.ShowSelectedAccount')]";
	public static final String Searched_AccountStatus = "//div/table//tbody[@data-bind='foreach: AccountList']/tr/td/span[contains(@data-bind,'Status')]";;
	public static final String Searched_AccountNumber = "//span[contains(@data-bind,'text: AccountNumber')]";
	public static final String AddNoteButton = "//a [contains(@data-bind,'click: addAccountNotes')]";
	public static final String NoteModal_Title = "//h4[text()='Note Details']";
	public static final String Acc_AccountName = "(//div[@class='col-md-9 has-error']//input)[5]";
	public static final String UnitOwner_Item_link = "//span[.='Unit Owner Items']";
	public static final String NewItem = "MainContent_btnNew";
	public static final String Itme_Name = "MainContent_txtUnitOwnerItemName";
	public static final String Item_DisplayName = "MainContent_txtUnitOwnerItemDispName";
	public static final String Item_Description = "MainContent_txtUnitOwnerItemDesc";
	public static final String Item_Value = "MainContent_txtUnitOwnerItemValue";
	public static final String PercentCheckbox = "//input[@id='MainContent_chkIsPercent']";
	public static final String Item_Catagory = "MainContent_drpCategory";
	public static final String Item_Associate_Button = "//input[@id='MainContent_btnAssociate']";
	public static final String LdgerAccount_Popup = "//font[.='Ledger Account Picker']";
	public static final String SelectTax = "lstTaxes";
	public static final String AdTax_Button = "btnPickOne";
	public static final String Tax_Done_Button = "Button1";
	public static final String SaveItem_Button = "MainContent_btnSave";
	public static final String DoneItem_Button = "MainContent_btnDone";
	public static final String TaxExemptCheckBox = "//input[contains(@data-bind,'checked: Account.TaxExempt')]";
	public static final String TaxExemptID = "//input[contains(@data-bind,'enable: (Account.TaxExempt() ')]";
	public static final String ReservationStatus_ReservationPage = "(//span[contains(@data-bind, 'text: StatusName')])[1]";
	// Travel Agent item
	public static final String TravelAgen_Item_link = "//span[text()='Travel Agent Items']";
	public static final String TravelAgenItem = "//ul[@class='sec_nav']//span[text()='Travel Agent Items']";
	public static final String TravelAgentItme_Name = "MainContent_txtTravelAgentItemName";
	public static final String TravelAgentItem_DisplayName = "MainContent_txtTravelAgentItemDispName";
	public static final String TravelAgentItem_Description = "MainContent_txtTravelAgentItemDesc";
	public static final String TravelAgentItem_Value = "MainContent_txtTravelAgentItemValue";
	public static final String TravelAgentItem_Percent = "MainContent_chkIsPercent";
	public static final String DeleteItem = "//input[@id='MainContent_btnDelete']";

	public static final String TravelAgentItemList = "//table[@id='MainContent_dgTravelAgentItemsList']//following-sibling::tr[contains(@class,'dgItem')]";
	public static final String CreatedItem_Pages = "//tr[@align='right']/td//following-sibling::*";
	public static final String Delete_TravelAgentItem = "//input[@id='MainContent_btnDelete']";
	public static final String PrintButton = "(//i[@id='acctPrint'])[2]";
	public static final String PDF_File = "//div[@class='modal-scrollable']";
	public static final String ExportButton = "(//div[@class='innroad-btn-submit'])[2]";
	public static final String SelectPDFCategory = "(//select[contains(@data-bind,'visible: !isSimplePdf()')])[2]";
	public static final String Account_EndingBalance = "//label[text()='Ending Balance: ']//parent::div/span[@class='pamt']/span";
	public static final String Void = "//div[contains(@data-bind,'with: folioDetailToShow')]//button[text()='Void']";
	public static final String NotesPopup = "//div[contains(@data-bind,'getElement: NotesPopUpHandle')]";
	public static final String NotesPopup_Note = "//div[contains(@data-bind,'getElement: NotesPopUpHandle')]//textarea";
	public static final String NotesPopup_Void = "//div[contains(@data-bind,'getElement: NotesPopUpHandle')]//button[text()='Void']";
	public static final String Payment_Add = "(//div[@id='AccountPaymetItemDetail']//button[contains(text(),'Add')])[2]";
	// Account statement
	public static final String Select_PeroidDate = "MainContent_drpPeriodDate";
	public static final String RunPeriod_Button = "MainContent_btnRunPeriod";
	public static final String ViewPeriod_Button = "MainContent_btnViewPeriod";
	public static final String Select_Date = "//th[text()='Today']";

	// Manual Email

	public static final String Click_Email_icon = "(//button[contains(@data-bind,'showEmailPopup')]/i)[2]";// div[@id='ReservationDetailMain']//i[contains(@data-bind,'click:
																											// SendMail')]";
	public static final String Verify_Send_Email_Popup = "(//h4[text()='Send Mail'])[2]";
	public static final String Select_Attachment = "//div[@id='mailContentPopUp']//select[contains(@data-bind,'options: MailAttachments')]";
	public static final String Verify_loading_popup_mailContentPopUp = "//div[@id='mailContentPopUp']//div[@class='modules_loading']";
	public static final String Get_email_Id = "//input[contains(@data-bind,'value:to, valueUpdate:')]";
	public static final String Click_Send_Email = "(//h4[text()='Send Mail'])[2]/../../../..//button[contains(text(),'Send')]";
	public static final String clickAttachmentDropDown = "//label[contains(text(),'Attachments')]//following-sibling::div/button";
	public static final String selectGuestRegistration = "(//span[contains(text(),'Guest Registration')])[3]";
	public static final String customFieldButton = "//label[contains(text(),'Custom Fields')]//following-sibling::div/button";
	public static final String bodyText = "//label[contains(text(),'Body')]//following-sibling::div/button";

	// Bluk Checkin

	public static final String Check_Reservation = "//tbody[@data-bind='foreach: ReservationList']//div[@class='checker']";
	public static final String Click_Bulk_Action = "//button[@title='Bulk Action']";
	public static final String Select_Checkin = "//span[.='Check In']";
	public static final String Verify_Bulk_Checkin_popup = "//h4[.='Bulk Check In']";
	public static final String Verify_Guest_Name = "//div[@id='tab1']//span[contains(@data-bind,' text: GuestName')]";
	public static final String Click_Process_Button = "//button[contains(@data-bind,'click: ValidateAndProcess')]";
	public static final String Verify_Bulk_Checin_Completed = "//h4[.='Bulk Check In Completed']";
	public static final String Verify_Bulk_Checkin_Success = "//span[.='1']";
	public static final String click_on_Close_icon = "//button[@data-bind='click: closeBulkPopup']";
	public static final String Get_Reservation_Status = "//span[contains(@data-bind,'text: StatusName.replace')]";
	public static final String Select_Checkout = "//span[.='Check Out']";
	public static final String Verify_Bulk_Checkout_popup = "//h4[.='Bulk Checkout']";
	public static final String Verify_Bulk_Checkout_Completed = "//h4[.='Bulk Checkout Completed']";
	public static final String Click_On_First_SearchResult = "(//td//a[@title])[1]";
	public static final String Check_First_SearchResult = "(//td//input[contains(@data-bind,'DeleteReservation')])[1]";

	// Bulk Cancellation
	public static final String selectAllArrivals = "//li[@class='all-arrivals active predefinedQueryOption']";
	public static final String select_AllArrivals = "//li[@class='all-arrivals predefinedQueryOption']";

	// public static final String inHouseReservations="//li[@class='in-house
	// active predefinedQueryOption']";
	public static final String inHouseReservations = "//li[@class='in-house predefinedQueryOption active']";
	public static final String unassignedReservations = "//li[@class='unassigned active predefinedQueryOption']";
	public static final String folioCancelReservation = "//a[contains(@title,'Cancel')]";
	public static final String clickRollBackButton = "//a[@title='Rollback']";
	public static final String roomAssignmentpopUpSelectButton = "(//button[.='Select'])[11]";
	public static final String payment_AddButton = "(//button[.='Add'])[2]";
	public static final String payment_ContinueButton = "";
	public static final String enterResNumber = "//input[contains(@data-bind,'textInput: BasicConfirmationNumber')]";
	public static final String enterAdvResNumber = "//input[contains(@data-bind,'value: AdvReservationNumber')]";
	public static final String resNumber = "//span[contains(@data-bind,' text: typeof ConfirmationNumberString')]";
	public static final String selectReservation = "(//input[contains(@data-bind,'enable: CanUserAccessThisResProperty')])[1]";
	public static final String closeAdvancedSearch = "//button[contains(@data-bind,'click: $parent.GoBasicLink')]";
	public static final String advanced = "//button[.='Advanced']";
	public static final String advancedSearchStatus = "//button[contains(@title,'Status')]";
	public static final String advancedSearchReservedStatus = "//button[contains(@title,'Reserved')]";
	public static final String reservedStatus = "//button[@title='Status']//following-sibling::div//span[text()='Reserved']";
	public static final String reservedTocancelledStatus = "//button[@title='Reserved']//following-sibling::div//span[text()='Cancelled']";
	public static final String selectCancel = "//span[.='Cancel']";
	public static final String bulkCancelpopup = "//h4[.='Bulk Cancel']";
	public static final String enterCancellationReason = "(//textarea[@placeholder='Cancellation reason'])[2]";
	public static final String voidRoomChargesCheckBox = "//input[contains(@data-bind,'enable: BulkVoidChargesEnable()')]";
	public static final String processButton = "(//button[.='Process'])[3]";
	public static final String bulkCancellationMessage = "//h4[.='Bulk Cancel Completed']";

	public static final String bulkPopupClose = "(//button[.='Close'])[7]";
	public static final String BulkPopupClose = "//button[.='Close' and contains(@data-bind,'closeBulkPopup')]";
	public static final String basicSearchcancelledReservation = "";

	public static final String cancelledReservation = "(//span[.='Cancelled'])[3]";
	public static final String Reser_FirstActiveReservation = "(//tbody[contains(@data-bind,'foreach: ReservationList')])[1]//tr[1]//td[4]//a"; // Bulk
																																				// NoShow
	public static final String selectNoShow = "(//span[.='No Show'])[2]";
	public static final String bulkNoShowpopup = "//h4[.='Bulk No-Show']";
	public static final String bulkNoShowMessage = "//h4[.='Bulk No-Show Completed']";
	public static final String basicSearchNoShowReservation = "//span[.='No-Show']";
	// Folio

	public static final String Click_Pay_Button = "//button[.='Pay']";
	public static final String Verify_Payment_Details_popup = "//span[.='Payment Details']";
	public static final String Verify_Payment_Details_poup = "//span[.='Payment Details']";
	public static final String Select_Paymnet_Method = "//div[@id='ReservationPaymetItemDetail']//select[contains(@data-bind,'options: $parent.PaymentMethodsList')]";
	public static final String Click_ADD_Button = "//div[@id='ReservationPaymetItemDetail']//button[.='Add']";// div[@id='ReservationPaymetItemDetail']//button[.='Add']
																												// [contains(@data-bind,'parent.btnAddEnabled()')]";
	public static final String Click_ADD = "//div[@id='ReservationPaymetItemDetail']//button[.='Add']";
	public static final String Verify_Guest_Ledger = "//div[@id='ReservationPaymetItemDetail']//following-sibling::div[@id='reservationList']";
	public static final String Get_Payment_Method = "//div[@id='ReservationPaymetItemDetail']//div[@id='reservationList']//span[contains(@data-bind,'text: $data.LedgerAccountName')]";
	public static final String Click_Continue = "//div[@id='ReservationPaymetItemDetail']//button[contains(@data-bind,'click: btnSaveTranPayClick')]";
	public static final String Verify_Line_item = "//a[contains(@data-bind,'parent.fnPayLineItemDetail')] [.='Cash']";
	public static final String Click_Card_info = "//div[@id='ReservationPaymetItemDetail']//button[contains(@data-bind,'click: $root.ShowCreditCardInfoPopup')]";
	public static final String Verify_payment_info_popup = "//div[@id='ReservationPaymentDetailCreditCardInfo']//h4";
	public static final String Enter_Card_Name = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[contains(@data-bind,'value: NameOnCard')]";
	public static final String Enter_Account_Number_Folio = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[contains(@data-bind,'value: $root.txtBilling_AccountNumber')]";
	public static final String Enter_CC_Expiry = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[contains(@data-bind,'value: ExpDate')]";
	public static final String Enter_CVVCode = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[contains(@data-bind,'value: CvvCode')]";
	public static final String Click_OK = "//div[@id='ReservationPaymentDetailCreditCardInfo']//button[.='OK']";
	public static final String Click_Process = "(//div[@id='ReservationPaymetItemDetail']//button[text()='Process'])[1]"; // "//div[@id='ReservationPaymetItemDetail']//button[contains(@data-bind,'$parent.btnProcessVisible()')]";
	public static final String GetMC_Payment_Method = "//div[@id='ReservationPaymetItemDetail']//following-sibling::div[@id='reservationList']//span[.='MC']";
	public static final String GetAddedLine_MC = "//div[@id='ReservationDetail']//tr[@data-bind='if: $parent.ShouldShowThisItem($data)'][2]//a";
	public static final String GetAddedLineMC = "//div[contains(@data-bind,'folioDetailToShow')]//span[text()='MC']//parent::td//following-sibling::td/a";
	public static final String GetAddedLine_MC_Amount = "//div[@id='ReservationDetail']//tr[@data-bind='if: $parent.ShouldShowThisItem($data)']//span[text()='MC']/../following-sibling::td[@class='lineitem-amount']/span";
	public static final String Verify_MC_Grid = "//tbody[contains(@data-bind,'foreach: PaymentItemsArray')]//tr";
	public static final String Click_Confirm = "(//div[@class='header-confirmation']//button)[2]";

	public static final String Verify_Balance_Zero = "//label[.='Balance: ']//following-sibling::span/span";
	public static final String Select_Authorization_type = "//div[@id='ReservationPaymetItemDetail']//select[@name='AuthorizationTypeOut']";
	public static final String Change_Amount = "//div[@id='ReservationPaymetItemDetail']//input[contains(@data-bind,'value: $parent.formattedAmount')]";
	// Folio Options tab
	public static final String FolioOption = "//a[contains(@data-bind,'    click: ShowFolioOption')]";
	public static final String Select_PhoneAccess = "//select[contains(@data-bind,'value: $data.AccessTelephoneLevel')]";
	public static final String SaveOptions_Button = "//button[contains(@data-bind,' click: PreSave')]";

	public static final String Payment_Details_Folio_Balance = ".//*[@id='StayInfo']//label[text()='Folio Balance:']/..//p/span";
	public static final String Payment_Details_Total_Balance = ".//*[@id='StayInfo']//label[text()='Total: ']/..//p";
	public static final String PaymentInfo_Payments = "//div[contains(@class,' payInfoSection')]//label[text()='Payments: ']/../span/span";
	public static final String PaymentInfo_Balance = "//div[contains(@class,' payInfoSection')]//label[text()='Balance: ']/../span/span";
	// Reservation Summary Tab
	public static final String clickSummaryTab = "//a[.='Summary']";
	public static final String notesDelete = "//span[contains(@class,'ButtonDeleteNormal')]/i[2]/::before";
	public static final String clickGuestInfoTab = "//a[.='Guest Info']";

	// Folio Line items void

	public static final String click_Folio_tab = ".//*[@id='ReservationDetailMain']//a[contains(@data-bind,'click: switchToFoliosTab')]";
	public static final String click_Add_Button = "(//button[contains(@data-bind,'enable: !AccountTypeGroup()')])[1]";
	public static final String click_Add_Folio_Button = "(//button[contains(@data-bind,'enable: !AccountTypeGroup()')])[4]";
	public static final String selectCategory = "//select[contains(@data-bind,'options: LedgerAccountItems')]";
	public static final String enterAmount = "//td[@class='lineitem-amount']//input[contains(@data-bind,'value: Amount')]";
	public static final String clickSaveButton = "//button[contains(@data-bind,'click: PreSave')]";
	public static final String clickOkForPopup = "(//button[contains(text(),'Ok')])[2]";

	public static final String clickCommitButton = "//button[contains(text(),'Commit')]";
	public static final String clickFolioCommitButton = "(//button[contains(text(),'Commit')])[2]";
	public static final String selectAllLineItems = "//input[contains(@data-bind,'click: SelectAllLineItems')]";
	public static final String clickVoidButton = "//button[contains(@data-bind,'click: fnVoidFolioItems')]";
	public static final String enterNotes = "//textarea[contains(@data-bind,'value: notesPopUpNote')]";
	public static final String clickVoidButtonInNotes = "//button[contains(@data-bind,'click: NotesSaveClick')]";
	public static final String DisplayVoidItemsCheckBox = "//span[contains(text(),'Display Void Items')]/../input";
	public static final String getBalanceFolioLineItems = "//label[.='Balance: ']//following-sibling::span/span";
	public static final String VoidItemDisplayImage = "//img[contains(@data-bind,'click: $parent.fnChangeStatusFromPendingToPosted')]";
	public static final String ItemDetailCancelButton = "(//h4[.='Item Detail']//following::button[.='Cancel'])[2]";
	public static final String FolioDescription2 = "(//a[@data-bind='text: $data.Description, click: $parent.fnPayLineItemDetail'])[2]";
	public static final String PaymentLineItem = "(//table[@class='table table-striped table-bordered table-hover trHeight25']/tbody/tr/td/a)[3]";
	// ************************Add/Post Folio Line
	// Items*************************//
	public static final String getRoomClassName = "//span[@data-bind='text: RoomClassName']";
	public static final String clickUnassigned = "//span[.='Unassigned']";
	public static final String reservationGuestName = "(//a[contains(text(),'Auto Guest')])[1]";
	public static final String reservationGuestName2 = "(//a[contains(text(),'Auto Guest')])[2]";
	public static final String lineItem1 = "(//img[@src='./Folio_Images/1.gif'])[1]";
	public static final String lineItem2 = "(//img[@src='./Folio_Images/2.gif'])[1]";

	public static final String clickOnDescription = "(//img[@src='./Folio_Images/2.gif'])[1]/../../td[8]/a";
	public static final String clickRollBackButtonInPopUp = "//button[@class='btn blue']/i[@class='fa fa-reply']";
	public static final String clickContinueButton = "//div[@class='modal-header']/h4[text()='Item Detail']//parent::div//following-sibling::div//button[text()='Continue']";
	public static final String switchToFolio2 = "(//a[@data-bind='click: switchToFoliosTab'])[2]";
	public static final String getFolioLineItem = "(//table[@class='table table-striped table-bordered table-hover trHeight25']/tbody/tr[2])[1]";
	public static final String copiedFolioLineItem = "(//table[@class='table table-striped table-bordered table-hover trHeight25']/tbody/tr[2])[2]";

	// Tape Chart
	public static final String getUnsassignedResTapechart = "//span[@data-bind='text: $root.GetTruncatedText(dragContext.GuestName, 16)']";
	// public static final String getGuestName = "(//span[@data-bind=' text:
	// GuestNameUI'])[1]";
	public static final String getGuestName1 = "(//span[@data-bind='    text: GuestNameUI'])[2]";
	public static final String getGuestName = "//label[contains(text(),'Guest Name:')]/following-sibling::div/p/span[2]";// (//span[@data-bind='
																															// text:
																															// GuestNameUI'])[2]";
	public static final String secNav_Reservation = "(//span[.='Reservations'])[4]";
	public static final String getUnsassignedResCountTapechart = "//button[@data-bind='click: openOrCloseParkingLot, css: activeParkingLotButton, text:UnassignedReservationString']";
	public static final String click1Day = "(//a[@data-bind='click: $parent.updateNumberOfDays'])[1]";
	public static final String Property_ExpandButton = "//div [@class='propertyclickbutton']/input";
	public static final String tapeChartGridLayOut = "//div[@class='container ng-mobile-nospace']";
	public static final String RoomClass = "//div[.='TRC']";
	public static final String Select_Arrival_Date = "//input[contains(@data-bind,'value: ArriveDate')]//following-sibling::div//a/i";
	public static final String TapeChart_CheckIn = "//input[contains(@data-bind,'value: ArriveDate')]";
	public static final String Enter_Adults_Tapehchart = "//div[@id='tapeChartSearch']//input[contains(@data-bind,'value: Adults')]";
	public static final String Enter_Children_Tapechart = "//div[@id='tapeChartSearch']//input[contains(@data-bind,'value: Children')]";
	public static final String Click_Tapechart_Rateplan = "//div[@id='divReservationTapeChart']//button[@title='Rate Plan']";
	public static final String Enter_promoCode_Tapechart = "//div[@id='tapeChartSearch']//input[contains(@data-bind,'value: PromoCode')]";
	public static final String Click_Search_TapeChart = "//div[@id='tapeChartSearch']//button[@value='Search']";
	public static final String Click_First_Available = ".//*[@id='bpjscontainer_49']/div/div[2]/div[2]/div[3]/div[2]/div[1]/div[2]/div[2]/div[2]";
	public static final String Get_Tapechart_Rateplan = "//div[@id='tapeChartSearch']//ul[@role='menu']/li/a/span[1]";
	public static final String Select_Rack_Rate = "//div[@id='tapeChartSearch']//ul[@role='menu']/li/a/span[.='Rack Rate']";
	public static final String Rules_Broken_popup = "//div[@id='rulesBrokenConfirmation']//h4";
	public static final String Click_Book_icon_Tapechart = "//div[@id='rulesBrokenConfirmation']//button[.='Book']";
	public static final String FirstRoomClass_Rate_In_Tapechart = "(//div[@class='roomRatesChart'])[1]/div[1]//div[@class='tapechartdatecell'][1]";
	public static final String Click_First_Available_In_First_Roomclass = "((//div[@class='roomRatesChart'])[1]//div[text()='Available'])[1]/../../../..";

	public static final String clickAvailableRoom = "(//div[.='Available'])[1]";
	public static final String get_Reservation_Status = "//select[contains(@data-bind,'enable: drpReservationStatusEnabled')]";
	public static final String clickCancelButton = "//a[contains(@data-bind,'click: Cancel_Reservation')]";
	public static final String cancelReason = "(//textarea[contains(@data-bind,'value: cancelreason')])[2]";
	public static final String OkCancelReason = "(//button[contains(@data-bind,'click: OKClick')])[2]";
	public static final String Reservation_PromoCode = "(//div[@class='col-md-9']// p[contains(@data-bind,'text: PromoCode')])[2]";

	public static final String Res_View_Limit_Element = "//*[@id=\"bpjscontainer_53\"]/div/div[2]/div[2]/div[3]/div[2]/div[1]/div[4]/div[2]/span/span/div/div/div[2]/div";
	public static final String DBR_Unassigned_FooterRow = "//*[@id=\"25037\"]/div/div[2]/div[1]/div/div";
	public static final String JR_Unssigned_FooterRow = "//*[@id=\"25267\"]/div/div[2]/div[1]/div/div";
	public static final String PR_Unssigned_FooterRow = "//*[@id=\"29639\"]/div/div[2]/div[1]/div/div";
	public static final String Unassigned_Reserv_Room = "(// div[contains(text(),'Unassigned')])[1]";
	public static final String Unassigned_Footer_Row = "(//div[@class='col-xs-11'])[13]//div[1]//div//div";

	// Checkin
	public static final String Click_Checkin = "//button[.='Check In']";
	public static final String Verify_Dirty_Room_popup = "//h4[contains(@data-bind,'text: ModalTitleText')]";
	public static final String Confirm_button = "//button[contains(@data-bind, 'enable: YesButtonEnable')]";
	public static final String Click_on_confirm = "//div[@class='header-confirmation']//button[2]";
	public static final String Payment_Popup = "//span[.='Payment Details']";
	public static final String Key_Generation_Popup = "//h4[.='Key Generation']";
	public static final String Key_Generation_Close = "//*[@id='keyGenerationPopUp']/div[3]/div/button[3]";

	// Checkin-New Objects
	public static final String Enter_Checkin_Percentage_On_balance = "(.//*[@id='checkin']//input[@type='text'])[1]";
	public static final String Enter_Checkin_Percentage_On_balance_1 = "(//*[contains(@data-bind,'policyDetails')]//input[@type='text'])[2]";
	public static final String Enter_Checkin_Percentage_On_balance_Authorize_1 = "(//*[contains(@data-bind,'policyDetails')]//input[@type='text'])[4]";
	public static final String Select_Capture_Payment_for_Checkin = "(.//*[@id='checkin']//input[@type='radio'])[1]";
	public static final String Select_Authorize_Payment_for_Checkin = "(.//*[@id='checkin']//input[@type='radio'])[2]";
	public static final String Select_Capture_Payment_for_Checkin_1 = "//*[contains(@data-bind,'policyDetails')]//input[@name='ko_unique_1']";
	public static final String Select_Authorize_Payment_for_Checkin_1 = "//*[contains(@data-bind,'policyDetails')]//input[@name='ko_unique_2']";

	public static final String Enter_Policy_Text = ".//textarea[contains(@data-bind,'PolicyStatement')]";
	public static final String Enter_Policy_Description = ".//textarea[contains(@data-bind,'PolicyDescription')]";
	public static final String Associate_Sources_Btn = ".//button[text()='Associate Sources']";
	public static final String Associate_Seasons_Btn = ".//button[text()='Associate Seasons']";
	public static final String Associate_Room_Classes_Btn = ".//button[text()='Associate Room Classes']";
	public static final String Associate_Rate_Plans_Btn = ".//button[text()='Associate Rate Plans']";
	public static final String Associate_Assign_All_Btn = "//button[@data-bind='click: AssignAll']";
	public static final String Associate_Assign_One_Btn = "//button[@data-bind='click: AddNew']";
	public static final String Available_Options_In_Popup = "(.//*[@id='roleModal']//select)[1]/option";
	public static final String Select_Available_Options_In_Popup = "(.//*[@id='roleModal']//select)[1]";
	public static final String Added_Options_In_Popup = "(.//*[@id='roleModal']//select)[2]/option";
	public static final String Done_In_Popup = " .//*[@id='roleModal']//button[text()='Done']";
	public static final String Selected_Options_Under_Room_Class = "//tbody[contains(@data-bind,'PolicyRoomClasses')]/tr[@style='']";
	public static final String Selected_Options_Under_Sources = "//tbody[contains(@data-bind,'PolicySources')]/tr[@style='']";
	public static final String Selected_Options_Under_Seasons = "//tbody[contains(@data-bind,'PolicySeasons')]/tr[@style='']";
	public static final String Selected_options_Under_Rateplan = "//tbody[contains(@data-bind,'PolicyRatePlans')]/tr[@style='']";
	public static final String Policy_Save = ".//*[@id='bpjscontainer_22']//button[text()='Save']";
	public static final String Policy_Close_Btn = ".//*[@id='bpjscontainer_17']//li[contains(@class,'new-policy')]//i";
	public static final String Delete_Policy_Checkbox = "(//input[@data-bind='checked: deletePolicy'])[1]";
	public static final String Delete_Policy_Btn = "//button[text()='Delete']";
	public static final String CloseOpenedTab = "(//*[@class='sn_span4 fa fa-times'])[last()]";

	// Checkout

	public static final String Click_Checkout = "//div[@id='ReservationDetail']//button[.='Check Out']";
	public static final String Click_Close = "//div[@class='header-confirmation']//span[.='Close']/..";

	public static final String Click_Close_popUP = "(//div[@class='header-confirmation']//button)";

	public static final String Click_Cancel = "//div[@class='header-confirmation']//span[.='Cancel']/..";

	// swipe

	public static final String Click_Swipe_Icon = "//div[@id='ReservationPaymetItemDetail']//img[@title='Swipe']";
	public static final String Enter_Track_Data = "//input[contains(@data-bind,'value: txtTrackData')]";
	public static final String Verify_Swipe_Popup = "//div[@id='ReservationCreditCardSwipeInfoPopup']//span[.='Please swipe the card:']";

	// Notes

	public static final String Click_Add_Res_Note = "//div[@id='ReservationDetail']//a[contains(@data-bind,'click: addNotes')]";
	public static final String verify_Add_Notes_popup = "//div[@id='NotesDetailPopup'][@aria-hidden='false']//h4[.='Note Details']";
	public static final String Enter_Subject_Notes = "//div[@id='NotesDetailPopup'][@aria-hidden='false']//input[contains(@data-bind,'value: Subject')]";
	public static final String Select_Note_Type = "//div[@id='NotesDetailPopup'][@aria-hidden='false']//select[contains(@data-bind,'value: NoteTypeObj')]";
	public static final String Select_Note_Type1 = "//select[contains(@data-bind,'value: TaskTypeObj')]";
	public static final String Check_Action_Required = "//div[@id='NotesDetailPopup'][@aria-hidden='false']//input[@data-bind='checked: IsActionRequired']";
	public static final String Select_Notes_Status = "//div[@id='NotesDetailPopup'][@aria-hidden='false']//select[contains(@data-bind,'value: NoteStatusId')]";
	public static final String Enter_Note_Details = "//div[@id='NotesDetailPopup'][@aria-hidden='false']//textarea[contains(@data-bind,'value: NoteDetails')]";
	public static final String Enter_Note_Details1 = "//textarea[contains(@data-bind,'description')]";
	public static final String Click_Save_Note = "//div[@id='NotesDetailPopup'][@aria-hidden='false']//button[contains(@data-bind,'click: saveDetail')]";
	public static final String Click_Save_Note1 = "//button[contains(@data-bind,'click: saveDetail')]";
	public static final String Verify_Added_Notes = "//div[@data-bind='visible: showNotesList']//tbody[@data-bind='foreach: NoteList']";
	public static final String addedNote = "//table[@data-bind='visible: NoteList().length >= 0']/tbody/tr[1]";
	public static final String NotesSubject = "//a[@data-bind='text: Subject, attr: { title: Subject() }']";
	public static final String getCopiedNotesSubject = "(//a[@title='Copy Notes'])[2]";
	public static final String Select_TaskCategory = "//select[contains(@data-bind,'options: $parent.Category')]";
	public static final String TaskValue = "//tbody[contains(@data-bind,'foreach : taskList')]//td";
	// Get Confirmation Number

	public static final String Get_Confirmation_Number = "//span[contains(@data-bind,'ConfirmationNumberString')]";
	public static final String Get_Reser_Number = "(//span[contains(@data-bind,'ConfirmationNumberString')])[2]";

	// Tapechart

	public static final String Click_Unassigned_Tapechart = "(//div[contains(text(),'Unassigned')])[1]"; // "//div[@class='roomClasses']//div[@class='roomRatesChart'][1]//div[@class='row
	public static final String ReservationUpdate_ConfirmChange = "//div[@id='ReservationUpdate']//following-sibling::button[contains(text(),'Confirm Changes')]"; // unassignedrow']//div[@class='col-xs-1']";
	public static final String Click_Tapechart_RackRate = "//div[@id='divReservationTapeChart']//button[@title='Rack Rate']";
	public static final String ReservationUpdate_Popup = "//div[@id='ReservationUpdate']";

	// Account new Reservation button

	public static final String Click_New_Reservation_Account = "//div[@class='AccountDetail']//button[.='New Reservation']";

	// CP Account Pay

	public static final String Verify_CP_Lineitem = "//div[@id='ReservationDetail']//tbody//tr[contains(@data-bind,'if: $parent.ShouldShowThisItem')][2]//td[@class='lineitem-description']/a";

	// Account House

	public static final String Enter_House_Account_Name = "//div[@class='AccountDetail']//input[contains(@data-bind,'value: Account.AccountName')]";
	public static final String Verify_House_Account_Picker = "//div[@id='ReservationAccountPicker']//span[.='House Account Picker']";
	public static final String Verify_Gift_Account_popup = "//div[@id='ReservationGiftCertificatePickerPopup']//span[.='Gift Certificate Picker ']";
	public static final String Get_Gift_ID = "//div[@class='AccountDetail']//input[contains(@data-bind,'value: LedgerAccountDescription')]";
	public static final String Enter_Gift_ID = "//div[@id='ReservationGiftCertificatePickerPopup']//input[@data-bind='value: GCNo ']";
	public static final String Click_Go_Gift = "//div[@id='ReservationGiftCertificatePickerPopup']//button[contains(@data-bind,'click: Search')]";
	public static final String Enter_Account_Res_Name = "//div[@id='ReservationAccountPicker']//input[contains(@data-bind,'value: AccountName')]";
	public static final String Click_Search_House_Acc = "//div[@id='ReservationAccountPicker']//button[.='Search']";
	public static final String Verify_House_Account_Grid = "//div[@id='ReservationAccountPicker']//table";
	public static final String Verify_Gift_Certificate_Grid = "//div[@id='ReservationGiftCertificatePickerPopup']//td[@id='reservationList']//table";
	public static final String Select_Gift_Certificate = "//input[@name='GitCertificate']";
	public static final String Select_House_Acc = "//div[@id='ReservationAccountPicker']//input[contains(@data-bind,'click: $parent.SelectAccount')]";
	public static final String Click_Select_House_acc = "//div[@id='ReservationAccountPicker']//button[.='Select']";
	public static final String Click_Select_Gift = "//div[@id='ReservationGiftCertificatePickerPopup']//button[.='Select']";
	public static final String Click_Account_Add = "//div[@class='AccountDetail']//a[.='Add']";

	// Navigation to Reservationpage

	public static final String Click_Reservation = ".//img[@class='nav-reservation nav_icon']";
	public static final String Verify_Reservation_Page = "//div[@id='ReservationSearch']//div[@data-bind='visible: $parent.ShowQuickFilterFields()']";

	// Groups
	public static final String GroupsNewAccount = ".//*[@id='MainContent_btnNew']";
	public static final String AccountFirstName = "//*[@id='MainContent_txtAccountFirstName']";
	public static final String MarketingSegment = "//*[@id='MainContent_drpMarketingSegment']";
	public static final String GroupReferral = "//*[@id='MainContent_drpReferral']";
	public static final String SalutationMailing = "//select[@id='MainContent_drpSalutationMailing']";
	public static final String GroupFirstName = ".//*[@id='MainContent_txtMailing_contactFirstName']";
	public static final String GroupLastName = ".//*[@id='MainContent_txtMailing_contactLastName']";
	public static final String GroupPhn = ".//*[@id='MainContent_txtMailing_phoneNumber']";
	public static final String GroupAddress = ".//*[@id='MainContent_txtMailing_address1']";
	public static final String GroupCity = ".//*[@id='MainContent_txtMailing_city']";
	public static final String Groupstate = "//select[@id='MainContent_drpMailing_territoryID']";
	public static final String GroupPostale = ".//*[@id='MainContent_txtMailing_postalCode']";
	public static final String Groupscountry = "//select[@id='MainContent_drpMailing_countryID']";
	public static final String Mailinginfo = ".//*[@id='MainContent_ckUseMailingInfo']";
	public static final String GroupSave = "//*[@id='MainContent_btnSave']";
	public static final String Verify_New_Group_Account = "//span[@id='MainContent_lblTitle']";
	public static final String Verify_New_Reservation_Enabled = ".//*[@id='MainContent_btnNewReservation']";
	public static final String Group_Search_GroupName = "//input[@id='MainContent_txtAccountName']";
	public static final String Group_Search_Go = "//input[@id='MainContent_btnGoSearch']";
	public static final String Group_Reservation_Search_Reservation = "//input[@id='MainContent_ucReservationControl1_txtAdvReservationno']";
	public static final String Group_Reservation_Search_Go = "//input[@id='MainContent_ucReservationControl1_btnGo']";
	public static final String Group_Click_Roominglistbutton = "//input[@id='btnRoomingList']";
	public static final String Groups_Select_Pickupbutton_Roominglistpopup = "//input[@id='btnPickUp']";
	// public static final String GroupAccountNumber =
	// "//input[@id='MainContent_txtAccountNo']";

	// Adv Block Creation

	public static final String Click_New_Block_button = ".//*[@id='MainContent_btnNewBlock']";
	public static final String Verify_Block_Details_Popup = "//span[.='Block Details']";
	public static final String Enter_Block_Name = ".//*[@id='MainContent_txtBlockName']";
	public static final String Click_Ok = ".//*[@id='button-OK']";
	public static final String Verify_Rate_Quote = ".//*[@id='MainContent_pnlHeadingRateQuote']/font";
	public static final String Select_Arrival_Date_Groups = ".//*[@id='trArrive']/td[2]/img";
	public static final String Click_Today_Arrival_Groups = "//div[@class='datepicker-days']//th[@class='today']";
	public static final String Enter_No_of_Nigts = ".//*[@id='MainContent_txtTotalRooms']";
	public static final String Click_Search_Group = ".//*[@id='MainContent_btnNewSearch']";
	public static final String Verify_Room_Class_Grid_Groups = ".//*[@id='rateQuoteGridWithPlainKO']/div[1]/table";
	public static final String Room_Class_Grid = "//*[@id='rateQuoteGridWithPlainKO']/div[1]/table/tbody/tr";
	public static final String UnsignedList_Date = "//div[contains(@data-bind,'css: isRoomAssigned')]//span";
	public static final String UnassignedList = "//div[contains(@data-bind,'css: isRoomAssigned')]";
	public static final String Unassigned_Button = "//button[contains(@data-bind,'click: openOrCloseParkingLot')]";
	public static final String GetTodayDate = ".active.day";
	// Adv Group Updated automatically assigned rooms

	public static final String GetBlockedRowsize = "//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr/td[3]/input";
	public static final String txtTotalRooms = "txtTotalRooms";
	public static final String GetRoomclasses = "//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr/td[1]/span";
	public static final String Click_Create_Block = ".//*[@id='MainContent_btnBook']";
	public static final String Click_Create_Block1 = ".//*[@id='MainContent_btnNewBlock']";

	public static final String Verify_Release_Date_Popup = "//span[.='Release Date']";
	public static final String Click_Ok_On_Rel_Popup = ".//*[@id='btnRelOk']";
	public static final String Verifyroom_block_content = "//div[@class='room-block-content']";
	public static final String Verify_Block_Nights = "//span[.='Block Room Nights']";
	public static final String Click_Continue_Block_Night = ".//*[@id='btnContinue']";
	public static final String CountofRooms = "//a[contains(@onclick,'openRoomClassDetails')]";
	public static final String Navigate_Room_Block = ".//*[@id='MainContent_btnRoomBlock']";
	public static final String Rooming_List = "//input[@id='btnRoomingList']";
	public static final String Rooming_List_Popup = "//form[@name='frmRoomingList']";
	public static final String RoomingList_FirstName = "(//input[contains(@id,'RoomingList_txtFirstname')])[1]";
	public static final String RoomingList_LastName = "(//input[contains(@id,'RoomingList_txtLastName')])[1]";
	public static final String RoomingList_RoomClass = "(//select[contains(@id,'RoomingList_drpRoomClassName')])[1]";
	public static final String RoomingList_RoomNumber = "(//select[contains(@id,'RoomingList_drpRoomNo')])[1]";
	public static final String RoomingList_Amount = "(//input[contains(@id,'RoomingList_txtDepositAmount')])[1]";
	public static final String RoomingList_BillingInfo = "(//table[@id='dgRoomingList']//input[contains(@id,'BillingInfo')])[1]";
	public static final String BillingInfo_Popup = "//form[@name='frmRoomingListBillingInfo']";
	public static final String BillingInfo_PaymentMethod = "//select[@id='drpBilling_TypeID']";
	public static final String BillingInfo_Save = "//form[@name='frmRoomingListBillingInfo']//input[@name='btnSave']";
	public static final String BillingInfo_Tick = "//img[@id='dgRoomingList_imgComplete_0']";
	public static final String RoomingList_Pickup = "//input[@id='btnPickUp']";
	public static final String RoomingListSummary = "//form[@name='frmRoomingListSummary']";
	public static final String RoomingListSummary_Close = "//form[@name='frmRoomingListSummary']//input[@id='btnClose']";
	public static final String GeneratedReservationNumber = "//*[@id='dgRoomingList']/tbody/tr[2]/td[1]";
	public static final String Groups_ReservationsTab = "//input[@id='MainContent_btnReservation']";
	public static final String Groups_ReservationPage = "//div[@id='MainContent_account_Details_DIV']";
	public static final String ReservationNumbers = "//table[contains(@id,'ReservationList')]//tr[contains(@class,'dgItem')]/td[2]";
	public static final String ReservationGuest = "//table[contains(@id,'ReservationList')]//tr[contains(@class,'dgItem')]/td[3]";
	public static final String ReservationRoom = "//table[contains(@id,'ReservationList')]//tr[contains(@class,'dgItem')]/td[8]";
	public static final String ReservationDetailPage = "//div[@id='ReservationDetail']";
	public static final String ReservationDetailPage_GuestName = "(//*[@id='StayInfo']//span[contains(@data-bind,'    text: GuestNameUI')])[1]";
	public static final String ReservationDetailPage_Account = "//*[@id='StayInfo']//label[text()='Account:']//parent::div//a";
	public static final String ReservationDetailPage_Close = "//a[@id='dialog-close0']";
	public static final String RoomDetailsPage_EditButton = "//input[@id='btnEditBlock']";
	public static final String RoomDetailsPage_BlockDetails = "//div[@id='MainContent_pnlHeadingBlockDetails']";
	public static final String BlockDetailsPage_RoomBlockAttiribute = "//div[@id='MainContent_pnlTitleRoomBlockAttributes']";
	public static final String BlockDetailsPage_BlockOptionsButton = "//a[@id='MainContent_lnkBlockOptions']";
	public static final String BlockDetailsPage_PreviewFolioButton = "//a[@id='MainContent_lnkPreviewFolio']";
	public static final String BlockDetailsPage_ChargeRouting = "//div[@id='MainContent_pnlChargeRouting']";
	public static final String BlockDetailsPage_LineItems = "//table[@id='tLineItems']";
	public static final String YellowBookIcon = "//div[@class='bookyellow']";
	public static final String RedBookIcon = "//div[@class='bookred']";
	public static final String BlueBookIcon = "//div[@class='book']";

	// Old Groups

	public static final String oldGroup_startdate = "//*[@id='imgDateStartGroup']";
	public static final String oldGroup_numberofNights = "//*[@id='MainContent_txtNightGroup']";
	public static final String oldGroup_AccountNumber = "//input[@id='MainContent_txtAccountNo']";
	public static final String oldGroup_Today = "//table[@class='datepicker-table-condensed table-condensed']//tfoot/tr[1]/th";
	public static final String oldGroup_Adults = "//*[@id='MainContent_txtRoomBlockAdults']";
	public static final String old_Gropups_RoomBlockPage = "//div[@id='MainContent_pnlRoomBlockAttributes']";
	public static final String RoomBlockPage = "//div[@id='MainContent_account_Details_DIV']";
	public static final String old_Groups_Click_Search = "//input[@id='MainContent_btnRoomBlockSearch']";
	public static final String GetNumberofclasses = "//td[@class='dgText'][2]";
	public static final String oldGroups_Click_Groups = ".//*[@id='ucNavSecondary_rptrNavMain_lbtnNavMainItem_4']";
	public static final String oldgroups_Verify_GroupSearchpage = "//font[.='Accounts']";
	public static final String oldGroups_Account_Search = "//input[@id='MainContent_txtAccountNumber']";
	public static final String oldGroups_Account_ClickGo = "//input[@id='MainContent_btnGoSearch']";
	public static final String oldGroups_Account_VerifyAccountNumber = "//table[@id='MainContent_dgAccountList']//tr[@class='dgItem']//td[2]";
	public static final String oldGroup_SearchBookicon = "//input[@id='MainContent_dgAccountList_imgPickUp_0']";
	public static final String oldGroup_Verify_GroupPickPage = ".//*[@id='tdTitle']/font";
	public static final String oldGroup_Click_Roominglistbutton = "//input[@id='btnRoomingList']";
	public static final String oldGroup_Verify_Roominglistpopup = "//span[.='Rooming List - Pick Up Rooms']";
	public static final String oldGroups_Enter_FirstName_Roominglistpopup = "//input[@id='dgRoomingList_txtFirstname_0']";
	public static final String oldGroups_Enter_LastName_Roominglistpoup = "//input[@id='dgRoomingList_txtLastName_0']";
	public static final String oldGroups_Select_Roomclass_Roominglistpoup = "//select[@id='dgRoomingList_drpRoomClassName_0']";
	public static final String oldGroups_Select_RoomNo_Roominglistpopup = "//select[@id='dgRoomingList_drpRoomNo_0']";
	public static final String oldGroups_Select_Pickupbutton_Roominglistpopup = "//input[@id='btnPickUp']";
	public static final String oldGroup_Verify_Roominglistsummary = "//span[.='Rooming List - Pick Up Summary']";
	public static final String oldgroup_Verify_grid = "//table[@id='dgRoomingList']";
	public static final String oldGroup_Click_Close_Roominglistsummary = "//input[@id='btnClose']";
	public static final String oldGroup_AccountSearch_Accountnum = "//input[@id='MainContent_txtAccountNumber']";
	public static final String oldGroup_NewBlock = ".//*[@id='MainContent_btnNewRoomBlock']";
	public static final String oldGroup_NewBlock_RoomBlobkDetailsPOpUp = "//font[contains(text(),'Room Block')]";
	public static final String oldGroup_NewBlock_BlockName = ".//*[@id='txtRoomBlockName']";
	public static final String oldGroup_NewBlock_Save = ".//*[@id='btnSave']";
	public static final String oldGroup_NewBlock_Cancel = ".//*[@id='btnCancel']";
	public static final String oldGroup_ArrivalDate = "//img[@id='imgDateStartGroup']";
	public static final String oldGroup_DepartDate = "//img[@id='MainContent_imgDateEndGroup']";
	public static final String oldGroup_NewBlock_Adults = "//input[@id='MainContent_txtRoomBlockAdults']";
	public static final String oldGroup_NewBlock_RatePlan = "//select[@id='MainContent_drpRoomBlockRatePlan']";
	public static final String oldGroup_NewBlock_Children = "//input[@id='MainContent_txtRoomBlockChildrens']";
	public static final String oldGroup_NewBlock_Search = "//input[@id='MainContent_btnRoomBlockSearch']";
	public static final String oldGroup_Pagination = "//select[@id='MainContent_ddlItemsPerPage']";
	public static final String oldGroup_RoomingList_Amount = "//input[starts-with(@id,'dgRoomingList_txtDepAmount')]";
	public static final String oldGroup_RoomingList_PaymentInfo = "//input[starts-with(@id,'dgRoomingList_btnBillingInfo')]";
	public static final String oldGroup_RoomingList_PaymentInfo_Save = "//input[@id='btnSave']";
	public static final String oldGroup_PaymentMethod = "//select[@id='MainContent_drpBilling_TypeID']";
	public static final String oldGroup_RoomingList_Pickup = "//input[@id='btnPickUp']";
	public static final String oldGroup_RoomingList_Close = "//input[@id='btnClose']";

	public static final String NewBlock_Click_Ok = ".//*[@id='button-OK']";
	public static final String NewBlock_Click_Cancel = "button-Cancel";
	public static final String RateQuotePage = "//*[@id='ContentArea']";
	public static final String RoomingList_PickUpRooms = "//*[@id='lblTitle']";
	public static final String oldGroup_RoomListingBillingInfo_SaveButton = "btnSave";
	public static final String oldGroup_RoomListingBillingInfo_CancelButton = "btnCancel";
	public static final String RoomClassNameSearchResult = "//*[@id='rateQuoteGridWithPlainKO']/div[1]";
	public static final String ReservationgroupAccount_List_GuestName = "//*[@id='MainContent_ucReservationControl1_dgReservationList']/tbody/tr[3]/td[3]/a";

	public static final String ReleaseNote_OkBtn = "btnRelOk";
	public static final String BlockToSelectFromDropDown = "//ul[@id='mycarousel']//li";
	public static final String RoomListingBtn = "btnRoomingList";
	public static final String RoomblockDetailedPage = "//*[@id='MainContent_pnlRoomBlockrpt']/div[2]/div/div[2]";
	public static final String SelectRoomsBtn = "btnAssignrooms";
	public static final String EditBtn = "btnEditBlock";
	public static final String DeleteBtn = "MainContent_btnDeleteBlock";
	public static final String BlockDetails_AdultAddBtn = "MainContent_btnAdultsAdd";
	public static final String BlockDetails_SaveBtn = "MainContent_btnSave";
	public static final String BlockDetails_SaveBtnList = "MainContent_btnSave";
	public static final String BlockDetails_DoneBtn = "MainContent_btnDone";
	public static final String BlockDetails_CancelBtn = "MainContent_btnCancelEditBlock";
	public static final String oldGroups_Amount_Roominglistpopup = "dgRoomingList_txtDepositAmount_0";
	public static final String oldGroup_RoominglistsummaryPage = "ucBlockSummary1_pnlBlockInfo";
	public static final String oldGroups_ClickBillingInfo_Roominglistpopup = "dgRoomingList_btnBillingInfo_0";
	public static final String Navigate_ReservationGroupAccount = "MainContent_btnReservation";
	public static final String old_Groups_ReservationGroupAccountPage = "MainContent_ucReservationControl1_ucReservationSearchTable";

	// Resell Rooms popup
	public static final String Resell_Rooms_Popup_Header = "//h4[.='Resell Rooms']";
	public static final String Resell_Rooms_Popup_Continue_Button = "//h4[.='Resell Rooms']//following::button[.='Continue']";

	// Policies
	public static final String Policy_PolicyDetailsPage = "//div[text()='Policy Details']";
	public static final String Policy_FirstActiveClass = "((//div[@class='table-responsive'])[1]//td)[1]//a";

	public static final String Policy_TableShow = "(//div[@class='table-responsive'])[1]";
	public static final String Policy_Name_On_Policiespage = "//input[contains(@data-bind,'value: policyName')]";
	public static final String Search_On_On_Policiespage = "//div[@id='policySearch']//button[contains(@data-bind,'availableStatuses')]";
	public static final String New_Policy_Btn = ".//button[text()='New Policy']";
	public static final String Select_Policy_Type = "//select[contains(@data-bind,'policyTypes')]";
	public static final String SelectPolicyType = "//div[contains(@data-bind,'foreach: policyDetails')]//select[contains(@data-bind,'policyType')]";
	public static final String Enter_Policy_Name = "//input[contains(@data-bind,'PolicyName')]";

	public static final String Select_Deposit_Roomcharges_Radiobtn = "(.//*[@id='deposit']//input[@type='radio'])[1]";
	public static final String Select_Deposit_Fixedamount_Radiobtn = "(.//*[@id='deposit']//input[@type='radio'])[2]";
	public static final String Select_Deposit_Firstnightrc_Radiobtn = "(.//*[@id='deposit']//input[@type='radio'])[3]";

	public static final String Select_Noshow_Roomcharges_Radiobtn = "(.//*[@id='noShow']//input[@type='radio'])[1]";
	public static final String Select_Noshow_Fixedamount_Radiobtn = "(.//*[@id='noShow']//input[@type='radio'])[2]";
	public static final String Select_Noshow_Firstnightrc_Radiobtn = "(.//*[@id='noShow']//input[@type='radio'])[3]";

	public static final String Select_Checkin_Roomcharges_Radiobtn = "(.//*[@id='checkin']//input[@type='radio'])[1]";
	public static final String Select_Checkin_Fixedamount_Radiobtn = "(.//*[@id='checkin']//input[@type='radio'])[2]";
	public static final String Select_Checkin_Firstnightrc_Radiobtn = "(.//*[@id='checkin']//input[@type='radio'])[3]";

	public static final String Select_Roomcharges_Type_for_Deposit = ".//*[@id='deposit']//select";
	public static final String Select_Roomcharges_Type_for_Cancellation = ".//*[@id='cancellation']//select";
	public static final String Select_Roomcharges_Type_for_Noshow = ".//*[@id='noShow']//select";

	public static final String Enter_Deposit_Percentage_Charges = "(.//*[@id='deposit']//input[@type='text'])[1]";
	public static final String Enter_Deposit_Fixed_Amount = "((.//*[@id='deposit']//div[@class='form-group'])[2]//input[@type='text'])[1]";
	public static final String Enter_Deposit_First_Nights_RC = "((.//*[@id='deposit']//div[@class='form-group'])[3]//input[@type='text'])[1]";
	public static final String Enter_Noshow_Percentage_Charges = "(.//*[@id='noShow']//input[@type='text'])[1]";
	public static final String Enter_Noshow_Fixed_Amount = "((.//*[@id='noShow']//div[@class='form-group'])[2]//input[@type='text'])[1]";
	public static final String Enter_Noshow_First_Nights_RC = "((.//*[@id='noShow']//div[@class='form-group'])[3]//input[@type='text'])[1]";

	// Guest Services
	public static final String Guest_Services_Main_Menu = "//img[@class='nav-guest-services nav_icon']";
	public static final String Guest_Services_Menu_Title = "//font[.='Guest Services Menu']";
	public static final String Select_Items_Per_Page = "//select[@id='MainContent_ddlItemsPerPage']";
	public static final String Select_Rmcondition_In_Theader = "//tr[@class='dgHeader']//input[@type='radio']"; //// tr[@class='dgHeader']//label[text()='Clean']/../input
	public static final String Save_Housekeeping_Status = ".//*[@id='MainContent_btnSave']";
	public static final String HK_SelectRoomClass = "//select[@id='MainContent_drpRoomclasslst']";
	public static final String HK_GoButton = "//input[@id='MainContent_btnGo']";

	public static final String Housekeeping_Result = "//table[@id='MainContent_dgHousekeeping']/tbody/tr";
	public static final String Housekeeping_Status_Title = "//font[.='Housekeeping Status']";

	public static final String Housekeeping_Room_Class = "//*[@id=\"MainContent_drpRoomclasslst\"]";
	public static final String Housekeeping_Date = "//*[@id='MainContent_txtDate']";

	// HoueKeeping Columns
	public static final String Housekeeping_Room_Column = "//*[@id=\"MainContent_dgHousekeeping\"]/tbody/tr[1]/td[1]";
	public static final String Housekeeping_PageHyperLink = "//*[@id=\"MainContent_dgHousekeeping\"]/tbody/tr[12]/td/a[1]";

	// House Keeping Booleans
	public static final String Housekeeping_RoomStatus_Vacant = "MainContent_chkRoomStatus_0";
	public static final String Housekeeping_RoomStatus_Occupied = "MainContent_chkRoomStatus_1";
	public static final String Housekeeping_RoomStatus_OutofOrder = "MainContent_chkRoomStatus_2";

	public static final String Housekeeping_RoomCondition_Clean = "MainContent_chkRoomCondition_0";
	public static final String Housekeeping_RoomCondition_Dirty = "MainContent_chkRoomCondition_1";
	public static final String Housekeeping_RoomCondition_Inspected = "MainContent_chkRoomCondition_2";
	public static final String Housekeeping_RoomCondition_None = "MainContent_chkRoomCondition_3";

	public static final String Housekeeping_Zone = "MainContent_drpZonesList";
	public static final String Housekeeping_GroupBy = "MainContent_drpGrpBy";

	public static final String Housekeeping_DueIn_Yes = "MainContent_chkDueInYes";
	public static final String Housekeeping_DueIn_No = "MainContent_chkDueInNo";
	// House Keeping Buttons
	public static final String Housekeeping_GoButton = "MainContent_btnGo";
	public static final String Housekeeping_SaveButton = "MainContent_btnSave";
	public static final String Housekeeping_CancelButton = "MainContent_btnCancel";
	public static final String Housekeeping_PrintButton = "//*[@id='MainContent_imgPrintHouseKeepingList']";
	public static final String Housekeeping_TotalItems_SelectedProperty = "//*[@id='MainContent_dgHousekeeping']/tbody/tr[101]/td[1]";

	public static final String Housekeeping_PDFReport = "tdViewPDF";

	public static final String HouseKeeping_SelectItemPerPage = "//select[@id='MainContent_ddlItemsPerPage']";
	public static final String Menuitem_Policy_Text = ".//a[text()='Policies' and @class='MenuItem']";

	public static final String Cancel_Reservation_Icon = "//a[@title='Cancel']//i";
	public static final String Cancel_Res_Popup = "//h4[text()='Cancellation']";
	public static final String Cancel_Reason_Txtarea = "//h4[text()='Cancellation']/../..//*[@data-bind='value: cancelreason']";
	public static final String VoidRC_Chkbox_In_Popup = "//label[text()='Void Room Charges']/preceding-sibling::input";
	public static final String Cancel_Res_Popup_Ok_Btn = "//h4[text()='Cancellation']/../..//button[text()='OK']";
	public static final String Cancel_Res_Popup_Cancel_Btn = "//h4[text()='Cancellation']/../..//button[text()='Cancel']";
	public static final String ReservationCanellationID = "//div[contains(@data-bind,'CancellationNumber')]//label";

	public static final String Close_Tab_Btn = ".//span[text()='Auto Guest']/following-sibling::i";
	public static final String AlertForTab = "//div[@id='alertForTab']//h4";
	public static final String AlertForTab_Yes_Btn = "//div[@id='alertForTab']//button[text()='Yes']";

	public static final String Select_Delete = "//span[.='Delete']";
	public static final String Verify_Bulk_Delete_popup = "//h4[.='Bulk Delete']";
	public static final String Verify_Bulk_Delete_Completed = "//h4[.='Bulk Delete Completed']";
	public static final String Search_Results_Alert_Msg = "//div[@class='alert alert-warning']";

	public static final String Verify_Res_Number = "//div[@id='tab1']//span[contains(@data-bind,'text: ConfirmationNumber')]";

	// Room Status
	public static final String RoomStatus_SearchInput = "//input[@placeholder='Search Room']";
	public static final String RoomStatus_SearchButon = "searchsubmit";
	public static final String RoomStatus_SearchedRooms = "col-sm-12 mobPadLR-0";
	public static final String VerifyRoomStats_Dirty = "red";
	public static final String VerifyRoomStats_Inspection = "orange";
	public static final String VerifyRoomStats_Clean = "green";
	public static final String VerifyRoomStats_OutofOrder = "gray";
	public static final String VerifyRoomStats_All = "blue";
	public static final String VerifySortFuncButton = "(//div[@class='col-md-6']//div/a/i)[1]";
	public static final String VerifySortFuncButton1 = "(//div[@class='col-md-6']//div/a/i)[2]";

	public static final String VerifySortFunc_RoomNum = "//a[contains(text(),'Room #')]";
	public static final String VerifySortFunc_Zone = "//a[contains(text(),'Zone')]";
	public static final String VerifySortFunc_ArrivalDue = "//a[contains(text(),'Arrival Due')]";

	public static final String RoomStatus_Element = "//div[contains(@data-sortorder,'1')]";
	public static final String RoomStatus_RoomDirtyLabelDropDownButton = "(//div[@class='grid-content-inner']//div//a//i)[1]";
	public static final String RoomStatus_RoomCleanLabel = "(//div[@class='grid-content-inner']//div//div//a[@data-toggle='dropdown'])[1]";
	public static final String RoomStatus_RoomTaskLabel = "(//div[@class='grid-content-inner']//a[@class='btn btn-block btn-link-border '])[2]";
	public static final String RoomStatus_RoomDirtyLabelButton_CleanCate = "(//div[@class='grid-content-inner']//div//ul//li[1]//a)[1]";
	public static final String RoomStatus_RoomDirtyLabelButton_DirtyCate = "(//div[@class='grid-content-inner']//div//ul//li[2]//a)[1]";
	public static final String RoomStatus_RoomDirtyLabelButton_InspectionCate = "(//div[@class='grid-content-inner']//div//ul//li[3]//a)[1]";
	public static final String RoomStatus_Room_RadioButton = "(//div[@class='grid-content-inner']//div//span//span )[1]";
	public static final String UpdateStatusButton = "(//div[@class='col-md-6']//div/a/i)[1]";
	public static final String UpdateStatusButton1 = "(//div[@class='dropdown gs-dropdown btn-group gs-marR-5'])[1]";
	public static final String UpdateStatus_Dirty = "//a[contains(text(),'Dirty')]";
	public static final String UpdateStatus_Inspection = "//a[contains(text(),'Inspection')]";
	public static final String UpdateStatus_Clean = "//a[contains(text(),'Clean')]";
	public static final String DirtyTab = "//span[text()='Dirty']";

	public static final String Report = "//app-housekeeping//a[contains(text(),'Reports')]";
	public static final String Report_RoomStatus = "(//ul[contains(@class,'dropdown-menu')]//a[contains(text(),'Room Status')])[1]";
	public static final String Report_RoomStatusTask = "(//ul[contains(@class,'dropdown-menu')]//a[contains(text(),'Room Status')])[2]";
	public static final String ReportPage = "//div[@class='container pdf-reportview']//following-sibling::label/i[contains(text(),'Room Status for')]";
	public static final String ReportPage_Table = "//div[@class='container pdf-reportview']//following-sibling::table";
	public static final String Resell_CancellAssociateRoomCharges = "//span[contains(text(),' Cancel Associated RoomCharges')]/preceding-sibling::input";
	public static final String TaskList_Report = "//app-tasklist//a[contains(text(),'Reports')]";
	// Guest History

	public static final String Accountype_Label = ".//*[@class='form-group']/label[contains(text(),'Account Type:')]";
	public static final String reservation_Menu = "(//span[.='Reservations'])[2]";
	public static final String reservation_mainMenu = "(//ul[@class='nav_des']/li/a)[1]";
	public static final String clickNewReservationButton = "//a[contains(@data-bind,'click: NewReservation')]";
	public static final String newAccount = "//button[contains(text(),'New Account')]";
	public static final String GuestHistoryAccountPage = "//div[@class='AccountDetail']/span";
	public static final String selectAccountSalutation = "(//select[contains(@data-bind,'options: $root.SaluationArray')])[1]";
	public static final String selectAccountSalutation_1 = "(//select[contains(@data-bind,'options: $root.SaluationArray')])[2]";
	public static final String accountFirstName = "//input[contains(@data-bind,'value: Account.AccountFirstName')]";
	public static final String accountNumber = "//label[text()='Account #:']/following-sibling::div/input";
	public static final String accountSince = "//input[contains(@data-bind,'dateField')]";
	public static final String closeReservation = "(//i[contains(@data-bind,'click: $parent.closeTab')])[6]";
	public static final String guestHistoryAccountNo = "(//span[contains(@data-bind,'text: AccountNoToDisplay')])[1]";
	public static final String enterAccountFName = "(//input[contains(@data-bind,'value: AccountFirstName')])[1]";
	public static final String enterGuestHistoryAccountNo = "//div[@id='accountSearchFilter']//input[contains(@data-bind,'value: AccountNumber')]";
	public static final String GuestHistory_AccountNumber = "(//input[contains(@data-bind,'value: AccountNumber')])[1]";
	public static final String clickSearchButton = "//button[contains(@data-bind,'click: GetAccountsList')]";
	public static final String selectGuestHistoryAccount = "(//input[contains(@data-bind,'checked: DeleteAccount')])[1]";
	public static final String enterGAccountLastName = "(//input[contains(@data-bind,'value: AccountLastName')])[1]";

	public static final String SearcehdGuestHistoryAccountNumber = "//span[contains(@data-bind,'text: AccountNumber')]";
	public static final String deleteButton = "(//button[.='Delete'])[1]";
	public static final String newReservationButton = "//button[.='New Reservation']";
	public static final String clickReservation = "(//a[contains(@data-bind,'text: GuestName')])[1]";
	public static final String findGuestProfile = "//input[contains(@placeholder,'Find Guest Profile')]";
	public static final String clickEditButton = "//a[contains(@data-bind,'visible: ($parent.FrequentGuestId()')]";
	public static final String selectGuest = "(//a[contains(@class,'ui-menu-item-wrapper')])[1]";
	public static final String continueButton = "//div[@id='ReservationAccountPickerConfirmationPopup']/span/div/div/div/button[contains(text(),'Continue')]";
	public static final String reservation_Save = "(//button[contains(text(),'Save')])[2]";
	public static final String PrintIcon = "//i[contains(@data-bind,'click: Print')]";
	public static final String Report_Popup = "//h4[.='Choose a Report']";
	public static final String Select_ReportType = "dropdown-icon";
	// Statements

	public static final String Account_Statement_Tab = ".//*[@id='MainContent_btnAccountStatement']";
	public static final String Account_Statement_Page = "//div[@id='MainContent_pnlAccountStatemt']";
	public static final String AccountStatement_ErrorMessage = "MainContent_lblMessage";
	public static final String AccountStatement_Popup = "//form[@id='frmrunViewReport']";
	public static final String AccountStatement_PopupClose = "dialog-close0";
	public static final String AccountStatement = "//td[@id='tdPDF']";
	public static final String AccountStatement_Cancel = "//*[@id='btnCancel']";
	public static final String AccountStatement_Confirm = "//*[@id='btnConfirm']";
	public static final String AccountStatment_Cancel_1 = "//input[@id='btnCancel']";

	// Travel Agent Items

	public static final String Travel_Agent_Items_Title = "//font[.='Travel Agent Items']";

	// Management Items

	public static final String Management_Items_Title = "//font[.='Management Items']";

	// Account Distributions

	public static final String Account_Distributions_Title = "//font[.='Account Distributions']";

	// Task List

	public static final String Task_List_Title = "//font[.='Task List']";

	// Room Maintenance

	public static final String Room_Maintenance_Fromdate = ".//*[@id='MainContent_txtDateStart']";
	public static final String NewRoomMaintenance_Button = "//input[@id='MainContent_btnNew']";
	public static final String SelectStart_EndDate = "cal_img";
	public static final String SelectDate_Today = "(//th[@class='today'])[1]";
	public static final String GetStartDate = "//input[@id='MainContent_txtDateStart']";
	public static final String GetEndDate = "//input[@id='MainContent_txtDateEnd']";
	public static final String EnterRoomMaintenance_Night = "//input[@id='MainContent_txtNights']";
	public static final String EnterRoomMaintenance_Subject = "//input[@id='MainContent_txtSubject']";
	public static final String RoomMaintenance_AssociateRoom = "//input[@id='MainContent_btnEditRooms']";
	public static final String RoomMaintenance_AddNote = "//input[@id='MainContent_UcNotes_btnAdd']";
	public static final String RoomMaintenance_AddNoteSubject = "//input[@id='txtSubject']";
	public static final String RoomMaintenance_AddNoteDetail = "//textarea[@id='txtNoteDescription']";
	public static final String RoomMaintenance_AddNoteSaveButton = "//input[@id='btnSave']";
	public static final String GetActiveDate = ".today.day";
	public static final String RoomPicker_Popup = "//font[text()='Room Picker']";
	public static final String SelectRoom_OutofOrder = "//span[@id='dgRoomsList_lblSelectRadio_0']//input";
	public static final String GetRoomClass = "(//table[@id='dgRoomsList']/tbody/tr[2]/td)[3]";
	public static final String GetRoomNumber = "(//table[@id='dgRoomsList']/tbody/tr[2]/td)[4]";
	public static final String Select_RoomButton = "//input[@id='btnDone']";
	public static final String RoomMaintenance_Save = "//input[@id='MainContent_btnSave']";
	public static final String InputRoomOutOfOrder = "//span[@class='dgItem']//input";
	public static final String DeleteButton = "//input[@id='MainContent_btnDelete']";
	public static final String RemoveButton = "//a[@id='MainContent_dgMaintenanceRooms_lnkRemoveRoomClass_0']";
	public static final String Records = "//span[@id='MainContent_lblRecordCount']";
	public static final String DoneButton = "//input[@id='MainContent_btnDone']";
	public static final String RoomMaintenance_Reason = "MainContent_drpReason";
	public static final String RoomMaintenance_Room = "MainContent_txtRoomNo";
	public static final String RoomMaintenance_GoButton = "MainContent_btnGoSearch";
	public static final String RoomMaintenance_VerifyReason = "(//tr[@class='dgItem'])[1]//td[2]";
	public static final String RoomMaintenance_DetailPage = "//td[@id='tdTitle']//font";
	public static final String RoomMaintenance_FirstActiveElement = "//table[@id='MainContent_dgMaintenanceList']//tr[2]//td[1]//a";
	public static final String RoomMaintenance_FirstActiveElement_RoomClass = "//table[@id='MainContent_dgMaintenanceList']//tr[2]//td[3]";
	public static final String RoomMaintenance_AddNotes = "//input[@id='MainContent_UcNotes_btnAdd']";
	public static final String RoomMaintenance_NotesDetailPage = "//table[@class='TitleTable']//td[@id='tdTitle']//font";
	public static final String RoomMaintenance_Notes_Subject = "//input[@id='txtSubject']";
	public static final String RoomMaintenance_Notes_Detail = "//textarea[@id='txtNoteDescription']";
	public static final String RoomMaintenance_Notes_SaveButton = "//input[@id='btnSave']";

	// Inventory
	public static final String Inventory_Menu_Title = "//font[.='Inventory Menu']";
	public static final String NavIconInventory = "//span[@data-bind='text:FunctionTag' and text()='Inventory']";

	// Overview

	public static final String Inventory_Overview = ".//*[@id='MainContent_lnkInventoryOverview']";
	public static final String Edit_Rate = "//td[@class='EditRateTitle']/input";
	public static final String SelectTodaysDate = "(//th[.='Today'])[1]";
	public static final String GoButtonInventory = "//input[@id='MainContent_btnSearch']";
	public static final String Calendar = "//input[@id='MainContent_txtDateStart']//following::img[1]";
	public static final String Iframe_Id_Of_Rates_Override_Info_Popup = "MainContent_ifrmRateOverrideInfo";
	public static final String Rates_Override_Info_Popup = ".//*[@id='tdTitle']/font[.='Rates Override Info']";
	public static final String First_Roomclass_P1_Rate = "((.//tr[contains(@id,'UcRateOverrideInfo')])[1]//td/input)[1]";
	public static final String First_DBR_Roomclass_P1_Rate = "((.//tr[contains(@id,'UcRateOverrideInfo')])[2]//td/input)[1]";

	public static final String Save_Btn_In_Rates_Override_Info_Popup = ".//*[@id='btnUpdate']";
	public static final String Done_Btn_In_Rates_Override_Info_Popup = ".//*[@id='btnDone']";
	public static final String Cancel_Btn_In_Rates_Override_Info_Popup = ".//*[@id='btnCancel']";
	public static final String Get_Rack_Rate = "//table[@id='QA Property']/tbody/tr[3]/td[3]/nobr";
	public static final String RoomClassNameWithRates = "//div[@class='roomClassName']";

	// public static final String Rates_Override_Info_Popup =
	// ".//*[@id='tdTitle']/font[.='Rates Override Info']";

	public static final String PolicySuite_EvenOutFieldToday = "(//input[@class='even_outfield'])[156]";
	public static final String OverViewSaveBtn = ".//*[@id='MainContent_btnSave']";

	// Season
	public static final String New_Season_Btn = "//button[contains(text(),'New Season')]";

	// Rates

	public static final String First_Element_In_Rules_SearchResults = "(//tbody[@data-bind='foreach: ruleList']//tr)[1]//a";
	public static final String RulesPage_Reset_Btn = "//div[@id='ruleDetail']//button[.='Reset']";
	public static final String Rules_Search_Btn = "//div[@id='ruleSearch']//button[text()='Search']";
	public static final String Select_Rule_Satus = "//div[@id='ruleDetail']//select[contains(@data-bind,'StatusName')]";

	public static final String Syndication_FirstAvailable_Checkbox = "(//td//input[@type='checkbox' and contains(@data-bind,'IsSyndicated')])[1]";
	public static final String Syndication_Reset_Button = "//div[@id='syndicationGrid']//button[.='Reset']";

	public static final String Syndicate_ModuleLoading = "//div[@id='syndicationGrid']//div[@class='modules_loading']";
	public static final String BlackOuts_FirstAvailable_Checkbox = "(//td//input[@type='checkbox' and contains(@data-bind,'IsBlackout')])[1]";
	public static final String BlackOuts_Reset_Button = "//div[@id='blackoutGrid']//button[.='Reset']";

	public static final String Channels_FirstAvailable_Checkbox = "(//div[@id='channelGrid']//td//input[@type='checkbox'])[1]";
	public static final String Channels_Reset_Button = "//div[@id='channelGrid']//button[.='Reset']";

	// Setup
	public static final String click_Inventory = "//ul/li/a/span[contains(text(),'Inventory')]";
	public static final String Rates_Title = "//font[.='Rates']";
	public static final String inventory_rate = "//a[@id='ucNavSecondary_rptrNavMain_lbtnNavMainItem_2']";
	public static final String newRate = "//input[@id='MainContent_btnNew']";
	public static final String rateName = "//input[@id='MainContent_txRateName']";
	public static final String ratePlan = "//select[@id='MainContent_drpRatePlan']";
	public static final String selectDerivedRate = "//input[@id='MainContent_rblstRateTypes_3']";
	public static final String selectPackageRatePlan = "//input[@id='MainContent_rblstRateTypes_2']";
	public static final String getRateType = "//label[contains(text(),'Rooms Only')]";
	public static final String getRateType3 = "//label[contains(text(),'Package')]";
	public static final String getRateType4 = "//label[contains(text(),'Derived Rates')]";
	public static final String getPackageComponents = "(//label[contains(text(),'Bundle')])[1]";

	public static final String clickPackageAddButton = "//input[@id='MainContent_btnAddComponents']";
	public static final String packageComponentDescription = "//input[@id='MainContent_dgRateComponents_txtDisplaycaption_0']";
	public static final String packageCalculationMethod = "//select[@id='MainContent_dgRateComponents_drpCalculationMethod_0']";
	public static final String enterPackageAmount = "//input[@id='MainContent_dgRateComponents_txtComponentAmount_0']";

	public static final String selectPackageCategory = "//select[@id='MainContent_dgRateComponents_drpCategory_0']";
	public static final String clickPackageAssociateRate = "//input[@id='MainContent_btnAssociateRate']";
	public static final String selectRateInPackage = "//input[@id='MainContent_dgRatesList_chkSelected_0']";
	public static final String clickPackageSelectButton = "//input[@id='MainContent_btnSelect']";

	public static final String maxAdults = "//input[@id='MainContent_txtMaxAdults']";
	public static final String maxPersons = "//input[@id='MainContent_txtMaxPersons']";
	public static final String baseAmount = "//input[@id='MainContent_txtAmount']";
	public static final String selectDerivedRatePlan = "//select[@id='MainContent_drpDerivedFromRateplan']";
	public static final String offsetAmount = "//input[@id='MainContent_txtOffsetAmount']";
	public static final String additionalAdult = "//input[@id='MainContent_txtAddedAdultsAmt']";
	public static final String additionalChild = "//input[@id='MainContent_txtAddedPersonAmt']";
	public static final String rate_displayName = "//input[@id='MainContent_txtDisplayName']";
	public static final String rate_policy = "//textarea[@id='MainContent_txtRatePolicy']";
	public static final String rate_description = "//textarea[@id='MainContent_txtRateDescription']";
	public static final String rate_Associate_Seasons = "//input[@id='MainContent_btnEditSeasons']";
	public static final String click_All_Seasons = "//input[@id='btnPickAll']";
	public static final String doneButton = "//input[@id='btnSave']";
	public static final String rate_Associate_RoomClasses = "//input[@id='MainContent_btnEditRoomClasses']";
	public static final String click_All_RoomClasses = "//input[@id='btnPickAll']";
	public static final String Select_RoomClass = "//select[@id='lstRooms']";
	public static final String rate_Associate_Sources = "//input[@id='MainContent_btnAssociateSource']";
	public static final String rate_select_Source = "//input[@id='dgSourceList_chkSelectSource_0']";
	public static final String rate_Save_Button = "//input[@id='MainContent_btnSave']";
	public static final String rate_done_button = "//input[@id='MainContent_btnDone']";
	public static final String selectBaseRate = "//a[contains(text(),'newbaserate')]/../preceding-sibling::td/span/input";
	public static final String selectDRate = "//a[contains(text(),'newderivedrate')]/../preceding-sibling::td/span/input";
	public static final String selectPRate = "//a[contains(text(),'newPackage')]/ ../preceding-sibling::td/span/input";
	public static final String click_goButton = "//input[@id='MainContent_btnGoSearch']";
	public static final String deleteRate = "//input[@id='MainContent_btnDelete']";
	public static final String SelectRateType = "//select[@id='MainContent_drpRateType']";
	public static final String RatesPagesSize = ".TextDefault td a";
	public static final String FindRateName = ".dgText tr td a";
	public static final String RatesTotalPages = ".TextDefault td span";
	public static final String SelectAssociateRoomClass = "lstRooms";
	public static final String SelectAssociateRoomClass_1 = "//select[@id='lstRooms']";

	public static final String PickerButton = "btnPickOne";
	// Rules
	public static final String New_Rule_Btn = "//button[contains(text(),'New Rule')]";
	public static final String click_Rules = "(.//a[.='Rules'])[1]";
	public static final String Click_newRule_Btn = ".//*[@id='bpjscontainer_19']//button[contains(@data-bind,'click: createNewRule')]";
	public static final String Enter_ruleName = "//input[contains(@data-bind,'RuleName')]";
	public static final String Enter_MiniStay = "//input[contains(@data-bind,'value: RuleValue')]";
	public static final String Select_ruleType = "(//select[contains(@data-bind,'options')])[4]";
	public static final String Enter_ruleDescription = "//textarea[contains(@data-bind,'RuleDescription')]";
	public static final String click_effectiveOnAsMonday = "(//input[@type='checkbox'])[1]";
	public static final String click_effectiveOnAsTuesday = "(//input[@type='checkbox'])[2]";
	public static final String click_associateSeasons = "//button[contains(text(),'Associate Seasons')]";
	public static final String get_unassignedSeasons_list = "(//select[@class='form-control'])[11]";
	public static final String click_associateSeasons_assignAll = "//button[@data-bind='click: AssignAll']";
	public static final String get_assignedSeasons_list = "(//select[@class='form-control'])[12]";
	public static final String click_associateSeasons_doneButton = "(//button[@data-bind='click: Done'])[3]";
	public static final String click_associateRoomclass = "//button[contains(text(),'Associate Room Classes')]";
	public static final String click_associateRoomclass_assignAll = ".//*[@id='roleModal']//button[contains(@data-bind,'click: AssignAll')]";
	public static final String click_associateRoomclass_doneButton = ".//*[@id='roleModal']//button[contains(@data-bind,'click: Done')]";
	public static final String click_associateSources = "//button[contains(text(),'Associate Sources')]";
	public static final String click_associateSources_assignAll = ".//*[@id='roleModal']//button[contains(@data-bind,'click: AssignAll')]";
	public static final String click_associateSources_doneButton = ".//*[@id='roleModal']//button[contains(@data-bind,'click: Done')]";
	public static final String click_associateRatePlans = "//button[@data-bind='click: OpenRatePlanPopup, enable: !isSaving()']";
	public static final String click_associateRatePlans_assignAll = ".//*[@id='roleModal']//button[contains(@data-bind,'click: AssignAll')]";
	public static final String click_associateRatePlans_doneButton = ".//*[@id='roleModal']//button[contains(@data-bind,'click: Done')]";
	public static final String Click_saveButton = ".//*[@id='bpjscontainer_22']//button[contains(@data-bind,'click: saveRuleDetail')]";
	public static final String Message_newRuleCreated = "//div[contains(text(),'Successfully Created Rule')]";
	public static final String Click_closeTab = "(//i[contains(@data-bind,'click: $parent.closeTab')])[7]";
	public static final String Click_searchButton = "(.//*[@id='bpjscontainer_20']//button[@class='btn blue m-icon marT5'])[2]";
	public static final String selectRule = "//a[contains(text(),'newRule')]/../following::td[4]/input";
	public static final String rule_clickDeleteButton = "//button[contains(text(),'Delete')]";
	public static final String Select_Rule = "//td[contains(@data-bind,'visible: checkAccess')]//input";
	public static final String Select_Rules = "//td[contains(@data-bind,'visible: checkAccess')]//input";
	public static final String CloseReservationTab = "//span[text()='New Reser...']//following-sibling::i[contains(@data-bind,'click: $parent.closeTab')]";
	public static final String PickerAssociate = "#roleModal select";
	public static final String Rule_AddButton = "//button[contains(@data-bind,'click: AddNew')]";
	public static final String ConfirmationPopup = "//div[@id='alertForTab']//button[contains(@data-bind,'click: Yes')]";
	public static final String SelectSeason = "(//select[contains(@data-bind,'options: filteredItems')])[1]";
	public static final String AddSeason_Button = "//button[contains(@data-bind,'click: AddNew')]";
	public static final String Rate_SeasonFilter = "//select[@id='MainContent_drpSeasonsList']";
	public static final String Rate_FirstItemRateList = "(//table[@id='MainContent_dgRatesList']//tr[@class='dgItem'])[1]//td[2]//a";
	public static final String Rate_ApplyToSeasonsName = "//td[text()='All Year Season']";
	public static final String Rate_ApplyToSeasonsStartDate = "//table[@id='MainContent_dgSeasonAssociation']//tr[2]//td[2]";
	public static final String Rate_RateDetailPage = "//span[@id='MainContent_lblPageTitle']";
	public static final String CreatedRate_Pages = "//tr[@class='TextDefault']/td//following-sibling::*";
	public static final String ErrorMessage = "//*[@class='ErrorMessage']";
	public static final String RateName_List = "//table[@id='MainContent_dgRatesList']//child::tr[contains(@class,'dgItem')]//a[contains(@href,'dgRatesList')]";
	public static final String ConditionalRate = "//input[@id='MainContent_rbtnRackRateYes']";
	public static final String Rate_PromoCode = "//input[@id='MainContent_txtPromoCode']";
	// Distribution

	public static final String Table_Text = ".//span[text()='innCenter']";
	public static final String clickDistributionMenu = "//a[@id='ucNavSecondary_rptrNavMain_lbtnNavMainItem_4']";
	public static final String propertySelectionAlert = "(//button[.='OK'])[2]";
	public static final String selectProperty = "//div[@id='s2id_autogen1']";
	public static final String distribute = "(//input[@type='checkbox'])[1]";
	public static final String defaultStatus = "(//select[contains(@data-bind,'enable: IsSelected()')])[1]";
	public static final String clickSaveButtonDistribution = "//button[.='Save']";
	public static final String Syndication_Month_Label = "//span[@class='monthLabel']";
	public static final String BlackOuts_Source_Label = "//label[text()='Source:']";
	public static final String Save_SourceReservationDetails = "(//button[.='Save'])[4]";

	// Setup

	public static final String Setup_Menu_Title = "//font[.='Setup Menu']";

	// Properties
	public static final String Properties_Title = "//font[.='Properties']";

	// Room Classes

	public static final String New_RoomClass_Btn = "//button[contains(text(),'New Room Class')]";
	public static final String NewRoomClass = "//button[text()='New Room Class']";
	public static final String New_RoomClass_Btn1 = "//button[@class='ant-btn ant-btn-primary']";

	// Tax Items
	public static final String Tax_Items_Title = "//font[.='Tax Items']";

	// Merchant Services

	public static final String Merchant_Services_Title = "//font[.='Merchant Services']";

	// Document Templates

	public static final String Document_Templates_Title = "//font[.='Document Templates']";

	// List Management

	public static final String List_Management_Title = "//font[.='List Management']";

	// Admin

	public static final String Admin_Menu_Title = "//font[.='Admin Menu']";

	// Clients

	public static final String Clients_Title = "//font[.='Clients']";

	// Roles
	public static final String New_Role_Btn = "//button[.='New Role']";
	public static final String Roles_Search_Btn = "//div[@id='roleSearch']//button[text()='Search']";
	public static final String First_Element_In_Roles_SearchResults = "(//tbody[@data-bind='foreach: roleList']//tr)[1]//a";
	public static final String Enter_Role_Name = "//div[@id='roleDetail']//input[@placeholder='Role Name']";
	public static final String Roles_Reset_Btn = "//div[@id='roleDetail']//button[.='Reset']";
	public static final String Select_Role_Satus = "//div[@id='roleDetail']//select[contains(@data-bind,'StatusName')]";

	// Night Audit
	public static final String Period_Status_Title = "//font[.='Period Status']";

	// Reports

	public static final String Reports_Menu_Title = "//font[.='Reports Menu']";

	// Account Balance Summary
	public static final String Account_Balance_Summary_Title = "//font[.='Account Balance Summary']";

	// Ledger Balances
	public static final String Ledger_Balances_Title = "//font[.='Ledger Balances']";

	public static final String LEDGERBALANCE_MARKETSEGMENT = "//select[@id='MainContent_drpMarketingSegment']";

	// Merchant Transactions
	public static final String Merchant_Transaction_From_Date_Feild = ".//*[@id='MainContent_txtTransactionsDate']";

	// Deposit
	public static final String Deposit_Title = "//font[.='Advance Deposits']";

	// Daily Flash

	public static final String Daily_Flash_From_Date_Feild = ".//*[@id='MainContent_TxtDailyDate']";

	// Room Forecast Report
	public static final String Room_Forecast_From_Date_Feild = ".//*[@id='MainContent_txtFromDate']";

	// Net Sales
	public static final String Net_Sales_Title = "//font[.='Net Sales']";

	public static final String Click_Continue_Deposit = "//div[@aria-hidden='false']//button[.='Continue']";

	public static final String Click_Folio_Options = "//span[@class='subTab']//a[text()='Folio Options']";

	public static final String Select_Account_Checkin_Policy = "//label[text()='Check In Policy:']//following-sibling::div//select";

	public static final String Acc_Picker_Confirm_Continue_Btn = ".//*[@id='ReservationAccountPickerConfirmationPopup']//button[text()='Continue']";

	public static final String Associated_AccountName = ".//a[contains(@data-bind,'Accountname')]";
	public static final String Acc_Picker_Confirm = ".//*[@id='ReservationAccountPickerConfirmationPopup']//h4";

	// PaymentInfo_Popup or Billing_Info_Popup
	public static final String Click_Show_PaymentInfo = "//a[contains(@data-bind,'fnShowPaymentInfo')]";
	public static final String Folio_Billing_Info_Popup = "//h4/span[.='Folio Billing Information']";
	public static final String Select_Salutation_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//select[contains(@data-bind,'parent.SaluationList')]";
	public static final String Enter_First_Name_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//input[@placeholder='First Name']";
	public static final String Enter_Last_Name_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//input[@placeholder='Last Name']";
	public static final String Enter_Phone_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//label[.='Phone:']/..//input[@placeholder='Number']";
	public static final String Enter_AltPhone_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//label[.='Alternate Phone:']/..//input[@placeholder='Number']";
	public static final String Enter_Email_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//label[.='Email:']/..//input[@placeholder='example@example.com']";
	public static final String Enter_Line1_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//input[contains(@data-bind,'MailingAddress1')]";
	public static final String Enter_Line2_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//input[contains(@data-bind,'MailingAddress2')]";
	public static final String Enter_Line3_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//input[contains(@data-bind,'MailingAddress3')]";
	public static final String Enter_City_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//input[contains(@data-bind,'City')]";
	public static final String Select_Country_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//select[contains(@data-bind,'CountryList')]";
	public static final String Select_State_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//select[contains(@data-bind,'StateList')]";
	public static final String Enter_Postal_Code_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//input[contains(@data-bind,'PostalCode')]";

	public static final String Select_Payment_Method_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//select[contains(@data-bind,'BillingTypeList')]";
	public static final String Enter_Account_Number_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//input[contains(@placeholder,'Account Number')]";
	public static final String Enter_CardExpiryDate_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//input[contains(@data-bind,'CardExpiryDate')]";
	public static final String Enter_BillingNotes_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//input[contains(@data-bind,'BillingNotes')]";
	public static final String Save_Btn_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//button[text()='Save']";
	public static final String Cancel_Btn_PaymentInfo_Popup = "//div[@id='ReservationPaymetInfoDiv']//button[text()='Cancel']";
	public static final String Payment_Info_Textarea = "//textarea[@data-bind='value: PayDetails']";

	public static final String First_RC_Line_Item_Desc = "(//span[text()='Room Charge']/../following-sibling::td[contains(@data-bind,'lineitem-description')]/a)[1]";
	public static final String First_RC_Line_Item_Amount = "(//span[text()='Room Charge']/../following-sibling::td[@class='lineitem-amount']/span)[1]";
	public static final String Item_Details_Popup = "//h4[.='Item Detail']";
	public static final String Item_Details_Popup_Descriptions = "//table[contains(@class,'popGrdFx')]//a[contains(@data-bind,'data.Description')]";
	public static final String RC_Link_In_Item_Details_Popup = "//div[@data-bind='getElement: popUp']//span[text()='Room Charge']/../following-sibling::td/a";
	public static final String Sales_Tax_Link_In_Item_Details_Popup = "//div[@data-bind='getElement: popUp']//span[text()='Sales Tax']/../following-sibling::td/a";
	public static final String Tax_Item_Details_Popup = "//h4[text()='Tax Item Details']";
	public static final String Display_Name_In_Tax_Item_Details_Popup = "//div[@id='popUpForInnroad']//label[text()='Display Name:']";

	public static final String Total_Charges_In_Item_Details_Popup = "//div[@data-bind='getElement: popUp']//span[contains(@data-bind,'TotalSummary')]";
	public static final String Item_Details_Popup_Cancel_Btn = "//div[@data-bind='getElement: popUp']//button[@data-dismiss='modal' and text()='Cancel']";
	public static final String Include_Taxes_in_Line_Items_Checkbox = "//input[contains(@data-bind,'IncludeTaxesInLineItems')]";

	public static final String Rate_Details_Popup = "//h4[.='Rate Details']";
	public static final String Rate_Name_In_Rate_Details_Popup = "//div[@id='popUpForInnroad']//label[text()='Rate Name:']";
	public static final String Rate_Details_Popup_Cancel_Btn = "//div[@id='popUpForInnroad']//button[@data-dismiss='modal' and text()='Cancel']";

	// New Reservations
	public static final String NewRervations = "//span[contains(text(),'New Reservations')]";

	// Guest Info
	public static final String GuestInfo = "//a[contains(text(),'Guest Info')]";

	public static final String GuestInfo_1 = "(//ul[@class='nav nav nav-pills']//li)[2]";

	public static final String ClickTravelAgentAccount = "//span[contains(@data-bind,'    text: TravelAgentAccountName')]";
	public static final String RoomMoveTask = "//td/span[contains(text(),'Room Move')]";

	// Edit Folio
	public static final String Edit_Folio_Btn = "//img[@title='Edit Folio']";

	// Delete Folio
	public static final String Delete_Folio_Btn = "//img[@title='Delete Folio']";

	// Folio
	public static final String switchToFolio = "//a[@data-bind='click: switchToFoliosTab']";
	public static final String MoveFolio_Folio = "//a[contains(text(),'Folio')]";
	public static final String MoveFolioFolio = "//div[@class='portlet-body ng_ac_tabs']//a[contains(text(),'Folio')]";

	// Guset Folio
	public static final String MoveFolio_GuestFolio = "//select[@class='form-control folioFil']";

	// New Folio
	public static final String MoveFolio_NewFolio = "//img[@title='New Folio']";

	// Folio Details
	public static final String MoveFolio_NewFolioDeatils = "//h4[contains(text(),'Folio details')]";

	// New Folio Name
	public static final String MoveFolio_NewFolio_Name = "//label[contains(text(),'Name:  ')]/following::div/input";

	// New Folio Description
	public static final String MoveFolio_NewFolio_Description = "//label[contains(text(),'Description:')]/following::div/textarea";

	// New Folio Close
	public static final String MoveFolio_NewFolio_Close = "(//button[contains(text(),'Close')])[10]";

	// New Folio Save
	public static final String MoveFolio_NewFolio_Save = "(//button[contains(text(),'Save')])[7]";

	// New Folio Save Reservation
	public static final String MoveFolio_NewFolio_SaveReservation = "(//button[contains(text(),'Save')])[2]";

	// New Folio select folio item
	public static final String MoveFolio_SelectFiloItem = "//table[@class='table table-striped table-bordered table-hover trHeight25']/tbody/tr/td[1]/input";

	// New Folio Move
	public static final String MoveFolio_Move = "//button[contains(text(),'Move')]";

	// Target Folio
	public static final String MoveFolio_TargetFolio = "//select[@data-bind='value: $root.selectedFolioId']";
	public static final String MoveFolio_FolioItemToMove2 = "(//tr[contains(@data-bind,' click: $root.SelectSourceLineItemsToMove')])[1]";
	public static final String MoveFolio_FolioItemToMove_2 = "(//tr[contains(@data-bind,' click: $root.SelectSourceLineItemsToMove')])[2]";
	public static final String MoveFolio_SelectFiloItem2 = "//table[@class='table table-striped table-bordered table-hover trHeight25']/tbody/tr[2]/td[1]/input";
	public static final String selectPaymentFolio = "(//table[@class='table table-striped table-bordered table-hover trHeight25']/tbody/tr/td/input)[2]";
	public static final String selectPaymentFolio3 = "(//table[@class='table table-striped table-bordered table-hover trHeight25']/tbody/tr/td/input)[3]";
	// Select Folio Item to move in pop up

	public static final String MoveFolio_FolioItemToMove = "//tr[contains(@data-bind,' click: $root.SelectSourceLineItemsToMove')]";

	// Move Selected Item
	public static final String MoveFolio_MoveSelectedItem = "//button[@data-bind='click: MoveItemsToTarget, enable: SelectedFolio']";

	// close on move folio pop up
	public static final String MoveFolio_Close = "//div[@id='moveFolioItemsModal']/div[3]/button";

	// Folio option tab
	public static final String Folio_Options = "(//a[contains(@data-bind,'click: ShowFolioOption')])[2]";
	public static final String Folio_Option = "//a[contains(@data-bind,'click: ShowFolioOption')]";
	public static final String Post_LineItem = "//td[@class='text-center lineitems-changestatus']//img";
	public static final String Folio_Details = "(//a[text()='Folio Details'])[1]";
	public static final String Folio_Details1 = "(//a[text()='Folio Details'])[2]";

	public static final String SelectAllLineitems = "(//input[contains(@data-bind,'click: SelectAllLineItems')])[1]";
	public static final String ConfirmApplyRouting = "//h4[text()='Confirm']";
	public static final String ConfirmApplyRouting_OK = "//button[contains(@data-bind,'enable: YesButtonEnable')]";

	public static final String ApplyRoutingButton = "(//button[contains(@data-bind,'click: fnApplyRouting')])[1]";

	public static final String ChargeRouting_MoveToAccFolio = "(//select[contains(@data-bind,'value: $data.ChargeRoutingOption')])[1]";
	public static final String ChargeRouting_MoveToAccFolio1 = "(//select[contains(@data-bind,'value: $data.ChargeRoutingOption')])[2]";

	public static final String ChargeRouting_FolioPrintingCheckBox = "(//input[contains(@data-bind,'checked: $data.SuppressPrinting')])[1]";

	// Select Policy
	public static final String Select_DepositPolicy = "(//select[contains(@data-bind,'options: $root.DepositPolicyList')])[2]";
	public static final String Select_DepositPolicies = "//select[contains(@data-bind,'options: $root.DepositPolicyList')]";

	public static final String Select_CheckIN = "//select[contains(@data-bind,'options: $root.CheckInPolicyList')]";
	public static final String Select_NoShow = "//select[contains(@data-bind,'options: $root.NoShowPolicyList')]";

	public static final String Folio_AccountName_Input = "(//div[@class='col-md-9 has-error']//input)[5]";
	// Save Policy button
	public static final String Save_DepositPolicy = "(//button[contains(text(),'Save')])[9]";
	public static final String SaveDepositPolicy = "//div[contains(@class,' accDetailBtns')]//button[text()='Save']";
	public static final String Save_DepositPolicies = "//button[contains(@data-bind,'click: SaveOnlyAfterCheckingPickedReservationCount')]";

	// ************************* Room Class **********************************

	// Room Class Details
	public static final String New_RoomClass_Deails = "//div[contains(text(),'Room Class Details')]";
	public static final String New_RoomClass_Page = "//h1[contains(text(),'New Room')]";

	public static final String NewRoomClassAbb = "//div[contains(@class,'RoomClass_mainTabsPane')]//input[@id='abbreviation']";
	public static final String NewRoomClassName = "//div[contains(@class,'RoomClass_mainTabsPane')]//input[@id='name']";
	public static final String NewRoomClassMaxAdults = "//div[contains(@class,'RoomClass_mainTabsPane')]//input[@id='maxAdults']";
	public static final String NewRoomClassMaxPersons = "//div[contains(@class,'RoomClass_mainTabsPane')]//input[@id='maxPersons']";
	public static final String NewRoomClassPublish = "//span[text()='PUBLISH']";
	public static final String NewRoomClass_TripAdvisorTab = "//img[@alt='trip-advisor']";
	public static final String Amenities = "//label[contains(@class,'Amenities_checkbox')]//input[@type='checkbox']";
	public static final String Amenities1 = "(//label[@class='Amenities_checkbox_1m4Ye ant-checkbox-wrapper']//input)[1]";
	public static final String Amenities2 = "(//div[@class='Amenities_category_1WUMi']//span[@class='ant-checkbox']//input)[201]";
	// New Room Class Name

	public static final String New_RoomClass_Name = "//input[@placeholder='Room Class Name']";
	public static final String New_RoomClass_Name1 = "//input[@id='name']";
	public static final String SearchRoomClass = "//button[@data-bind='click: goSearchRoomClasses']";

	// New Room Class Abbrivation
	public static final String New_RoomClass_Abbrivation = "//input[@placeholder='Room Class Abbreviation']";
	public static final String New_RoomClass_Abbrivation1 = "//input[@id='abbreviation']";

	// New Room Class King Beds

	public static final String New_RoomClass_KingBeds = "//h4[contains(text(),'King')]/following::div/input";
	public static final String New_RoomClass_KingBeds1 = "(//div[@class='ant-input-number-input-wrap'])[10]//input";

	// New Room Class Room Type

	public static final String New_RoomClass_RoomType_AdjoiningRooms = "//span[contains(text(),'Adjoining rooms')]/../preceding-sibling::span/input";
	public static final String New_RoomClass_RoomType_AdjoiningRooms1 = "//span[contains(text(),'Adjoining rooms')]/../preceding-sibling::span/input";

	// New Room Class Rooms

	public static final String New_RoomClass_Rooms = "//a[contains(text(),'Rooms')]";

	public static final String New_RoomClass_Rooms1 = "//div[text()='Rooms']";

	// New Room Class Max Adutls

	public static final String New_RoomClass_Max_Adults = "//label[contains(text(),'Max Adults')]/following::div/input";
	public static final String New_RoomClass_Max_Adults1 = "//input[@id='maxAdults']";

	// New Room Class Max persons

	public static final String New_RoomCLass_Max_Persons = "//label[contains(text(),'Max Persons')]/following::div/input";
	public static final String New_RoomCLass_Max_Persons1 = "//input[@id='maxPersons']";
	// New Room Class Room Quantity

	public static final String New_RoomClass_Room_Quantity = "//label[contains(text(),'Room Quantity')]/following::div/input";
	public static final String New_RoomClass_Room_Quantity1 = "//input[@placeholder='Placeholder']";
	// New Room Class Create Rooms

	public static final String New_RoomClass_Create_Rooms = "//button[@data-bind='click: createRooms']";
	public static final String New_RoomClass_Create_Rooms1 = "//button[@class='ant-btn PhysicalRooms_playButton_1AzeD ant-btn-primary ant-btn-circle ant-btn-icon-only']";

	// New Room Class Room Number
	public static final String New_RoomClass_RoomNumber = "//table/tbody/tr/td[2]/input";
	public static final String New_RoomClass_RoomNumber1 = "//input[@id='rooms[0].roomName']";

	// New Room Class Assign Rooms

	public static final String New_RoomClass_AssignRoomNumber = "//button[@class='btn blue disableForClientUser']";
	public static final String New_RoomClass_AssignRoomNumber1 = "//button[@class='ant-btn assignNumberButton ant-btn-primary']";
	// New Room Class Save

	public static final String New_RoomClass_Save = "(//button[contains(text(),'Save')])[2]";
	public static final String New_RoomClass_Save1 = "//button[@class='ant-btn doneButton ant-btn-primary']";

	// New Room Class publish

	public static final String New_RoomClass_Publish = "//button[contains(text(),'Publish')]";
	public static final String New_RoomClass_Publish1 = "//button[@class='ant-btn RoomClass_topBarButton_3fCkJ ant-btn-primary ant-btn-lg']";
	// New Room Class OK

	public static final String New_RoomClass_OK = "(//button[contains(text(),'OK')])[2]";
	public static final String New_RoomClass_OK1 = "//button[@class='ant-btn ant-btn-primary']";

	// New Room classes
	public static final String New_RoomClasses = "//span[contains(text(),'Room Classes')]";
	public static final String New_RoomClasses_1 = "(//span[contains(text(),'Room Classes')])[2]";

	public static final String CloseRoomClass = "//a[@class='MainMenu_closeButton__os2Y7']";
	public static final String Close_RoomClass = "//li[contains(@class,'here active open')]//child::i[contains(@data-bind,'closeTab')]";
	public static final String Close_RoomClass_1 = "(//i[contains(@data-bind,'click: $parent.closeTab')])[8]";
	// New Room Classes msg After Publish

	public static final String New_RoomClass_Msg_Publish = "//div[@id='toast-container']/div/div[2]";

	public static final String Click_First_Quote_Icon = "//tr[@rowtype='RoomClassHeader']/following-sibling::tr[1]//td[contains(@id,'td_Action')]/button[1]";
	public static final String Click_RuleBroken_Quote = "//button[contains(@data-bind,'click: QuoteOrBook')]";
	public static final String Get_QuoteReservation_Status = "(//label[contains(text(),'Reservation Status:')]/following-sibling::div/select)";

	public static final String Click_Book_Reservation = "//button[contains(text(),'Book')]";

	public static final String Check_Split_Rooms = "//label[contains(text(),'Split Rooms:')]/input";

	public static final String Get_Notes = "//table[@class='table table-striped table-bordered table-hover resGrid1']/tbody/tr";

	public static final String TotalRate_DBRValue = "td_TotalRate_25037";

	public static final String AddVirtualRoom = "//button[contains(@data-bind,'addSuiteLogic')]";
	public static final String CreateVirtualRoom_Text = "//div[text()='Create Virtual Room']";
	public static final String CreateVirtualRoom_RoomNumber = "//input[@placeholder= 'Room No']";
	public static final String CreateVirtualRoom_Adults = "//input[@placeholder= 'Adults']";
	public static final String CreateVirtualRoom_Persons = "//input[@placeholder='Persons']";
	public static final String CreateVirtualRoom_SearchButton = "//button[contains(@data-bind,'goSearchAvailableRooms')]";
	public static final String CreateVirtualRoom_PhysicalRoomBox = "cvr";
	public static final String CreateVirtualRoom_PhysicalRoom_RoomList = "(//div[contains(@data-bind,'roomDraggable()')])[25]";
	public static final String PhysicalRoom_RoomList = "//div[contains(@data-bind,'roomDraggable()')]";
	public static final String CreateVirtualRoom_VirtualRoomSpace = "//div[@class='dragRoomsMessageDiv']//div[contains(@data-bind,'foreach: AdjoiningRooms')]";
	public static final String CreateVirtualRoom_VirtualRoomName = "//input[@placeholder='Virtual Room Name']";
	public static final String CreateVirtualRoom_VirtualSortOrder = "//input[@placeholder='Sort Order']";
	public static final String RoomClasses_SearchButton = "//button[contains(@data-bind,'click: goSearchRoomClasses')]";
	public static final String RoomClasses_TableShow = "(//div[@class='table-responsive'])[1]";
	public static final String RoomClasses_FirstActiveClass = "((//div[@class='table-responsive'])[1]//td)[1]";
	public static final String RoomClasses_RoomDetailsPage = "//div[text()='Room Class Details']";
	public static final String RoomClasses_ChangesSaved_OKButton = "//div[@id='alertMessageForInnroad']//button[text()='OK']";

	// RoomClass-newLayout
	public static final String ListingName = "//textarea[@id='name']";
	public static final String PropertyTypeGroup = "(//div[@class='ant-select-selection-selected-value'])[5]";
	public static final String PropertyType = "//textarea[@id='name']";
	public static final String RoomType = "//textarea[@id='name']";
	public static final String ListingSummary = "//textarea[@id='name']";

	// public static final String
	// CreateVirtualRoom_VirtualRoomName="//div[contains(@data-bind,'foreach:
	// AdjoiningRooms')]";

	// First opened reservation

	public static final String FirstOpenedReservation = "//div[@class='sec_nav_in container']/ul/li[6]/a/span[3]";

	// First opened reservation close
	public static final String FirstOpenedReservationClose = "//div[@class='sec_nav_in container']/ul/li[6]/a/i";

	// Room Charges
	public static final String Room_Charges = "//label[contains(text(),'Room Charges:')]/following-sibling::span/span";

	// Incidentals
	public static final String Incidentals = "//label[contains(text(),'Incidentals:')]/following-sibling::span/span";

	// Taxes and Service charges
	public static final String TaxesAndServiceCharges = "//label[contains(text(),'Taxes & Service Charges:')]/following-sibling::span/span";

	// Tax Exempt Message
	public static final String taxExemptMessage = "(//div[@class='alert alert-danger'])[2]";

	// Total Charges
	public static final String TotalCharges = "//label[contains(text(),'Total Charges:')]/following-sibling::span/span";

	// Tax exempt this field is required
	public static final String TaxExemptThisFieldIsdRequired = "//span[contains(text(),'This field is required.')]";

	// Add incidentals

	// Add

	public static final String AddIncidental = "//button[contains(text(),'Add')]";

	// Incidental category
	public static final String IncidentalCategory = "//td[@class='lineitem-category']/select";

	// Incidental Amount
	public static final String IncidentalAmount = "//input[@class='form-control fgInpFx']";

	// commit
	public static final String Commit = "//button[contains(text(),'Commit')]";

	// Reservations sublink

	public static final String Reservations = "(//span[contains(text(),'Reservations')])[2]";

	public static final String Reservation_SecNav = "//ul[@class='sec_nav']//span[contains(text(),'Reservations')]";

	// Tax Creation
	// New Tax Item
	public static final String TaxNewItem = "//input[@id='MainContent_btnNew']";

	public static final String NewTaxItem_Title = "//font[text()='Tax Item Details']";

	// Item Name
	public static final String TaxItemName = "//td[contains(text(),'Item Name:')]/following-sibling::td/input";

	// Display Name
	public static final String TaxDispalyName = "//td[contains(text(),'Display Name:')]/following-sibling::td/input";

	// Tax Status
	public static final String TaxStatus = "//select[@id='MainContent_drpActive']";

	// Description
	public static final String TaxDescription = "//td[contains(text(),'Description:')]/following-sibling::td/textarea";

	// Tax Value
	public static final String TaxValue = "//input[@id='MainContent_txtTaxItemValue']";

	// Tax exempt
	public static final String ExcludeTaxExempt = "//input[@id='MainContent_chkTaxExempt']";

	// VAT
	public static final String CalculateasVAT = "//input[@id='MainContent_txtTaxItemValue']";

	// Tax Percent
	public static final String TaxPercent = "//input[@id='MainContent_chkIsPercent']";

	// Tax category
	public static final String taxCategory = "//select[@id='MainContent_drpCategory']";

	// Tax associate
	public static final String TaxAssociate = "//input[@id='MainContent_btnEditTaxes']";

	// Tax Ledger Account pick po up

	public static final String TaxLedgerAccountPopup = "//td[@id='tdTitle']/font[contains(text(),'Ledger Account Picker')]";

	// Tax Pick one
	public static final String TaxPickOne = "//input[@id='btnPickOne']";

	// Tax select All
	public static final String TaxSelectAllLedgerAccounts = "//input[@id='btnPickAll']";

	// String Save/Done LergerAccount
	public static final String TaxLaedgerAccountDone = "//input[@id='Button1']";

	// Tax Done
	public static final String TaxDone = "//input[@id='MainContent_btnDone']";
	public static final String TaxSave = "//input[@id='MainContent_btnSave']";

	public static final String Processed_Amount_In_Paymentdetails_Popup = "(//tbody[contains(@data-bind,' PaymentItemsArray')]//td[@class='text-right'])[1]/span";
	public static final String PaymentDetails_CancelButton = "(//button[contains(@data-bind,'click: CloseButtonClick')])[2]";
	// Continue in Cash Pay
	public static final String Folio_Cash_Continue_Btn = "//span[contains(text(),'Payment')]/../../../../following-sibling::div/div/div/button[contains(text(),'Continue')]";// span[contains(text(),'Payment
	public static final String Folio_Cash_Continue_Btn1 = "(//button[contains(@data-bind,'enable: (EnableContinueBtn()')])[2]"; // Details')]/../../../../following-sibling::div/div/div/button[contains(text(),'Continue')]";
	public static final String Folio_Cash_Continue_Btn2 = "(//button[@class='btn blue btn-payment-continue'])[2]";
	// Enter Corp Acc Name
	public static final String Account_CorpAccountName = "//input[@placeholder='Account Name']";
	public static final String GuestHis_CombineCheckBox = "(//input[contains(@data-bind,'checked: Combine')])[2]";
	public static final String GuestHis_CombineCheckBox1 = "(//input[contains(@data-bind,'checked: Combine')])[1]";
	public static final String ReservationTab_GuestAccount = "//a[text()='Reservations']";
	public static final String GuestHistoryAccountStatus = "//select[contains(@data-bind,'options: $root.StatusList')]";
	public static final String GuestHistoryAccount_BillType = "//select[contains(@data-bind,'options: PaymentMethodsList')]";
	public static final String GuestHistoryAccount_BillingAccountNum = "//input[contains(@data-bind,'value: Account.txtBilling_AccountNumber')]";
	public static final String GuestHistoryAccount_ExpDate = "//input[contains(@data-bind,'value: Account.BillingCreditCardExpDate')]";
	public static final String GuestHistoryAccount_BillingNotes = "//input[contains(@data-bind,'value: Account.BillingCvvCode')]";
	public static final String SearchGuestHistoryAccountStatus = "//select[contains(@data-bind,'options: StatusList')]";

	public static final String Account_AutoApply = "(//button[contains(text(),'Auto Apply')])[2]";

	// Folio Balance
	public static final String Folio_Balance = "//div[@class='col-md-12 payInfoSection boldFx']/div/div/label[contains(text(),'Ending Balance:')]/following-sibling::span/span";

	// Number of Accounts
	public static final String Number_Of_Accounts = "//table[@class='table table-striped table-bordered table-hover']/tbody/tr";
	// Account Search Loading
	public static final String AccountSearch_LoadingModule = "(//div[contains(@data-bind,'visible: isSearchLoading')]//div[@class='row']//div[@class='modules_loading'])[1]";

	// Account Type
	public static final String Account_Type = "(//select[contains(@data-bind,'AccountTypeName')])[2]";
	public static final String AccountType = "//select[contains(@data-bind,'AccountTypeName')]";

	// Account Name
	public static final String Account_Name = "(//label[contains(text(),'Account Name:')]/following-sibling::div/input)[2]";

	public static final String Account_Name_1 = "//label[contains(text(),'Account Name:')]/following-sibling::div/input";

	public static final String SearchAccountName = "//div[@class='AccountSearch']//label[contains(text(),'Account Name:')]/following-sibling::div/input";

	// Accuont #
	public static final String Account_Number = "(//input[@data-bind='value: AccountNumber'])[2]";

	public static final String Search_AccountNumber = "(//input[@data-bind='value: AccountNumber'])";

	public static final String SearchAccountNumber = "//div[@class='AccountSearch']//input[@data-bind='value: AccountNumber']";

	// Account Status
	public static final String Account_Status = "//div[@id='accountSearchFilter']";
	public static final String Account_Status_1 = "//select[contains(@data-bind,'StatusName')]";
	public static final String Account_Status_Active = "//select[contains(@data-bind,' value: Account.Active')]";

	// Account Search
	public static final String Account_Search = "//button[@data-bind='click: GetAccountsList']";

	// ******************************* Groups **********************************

	// New Account
	public static final String New_Account_Btn = "//input[@id='MainContent_btnNew']";
	public static final String New_Account_Number = "(//input[@placeholder='Account Number'])[2]";
	public static final String New_Account_Number_1 = "(//input[@placeholder='Account Number'])[1]";

	// Group Name
	public static final String Group_Name = "//input[@id='MainContent_txtAccountFirstName']";

	// Market Segment
	public static final String Market_Segment = "//select[@id='MainContent_drpMarketingSegment']";

	// Referrals
	public static final String Referrls = "//select[@id='MainContent_drpReferral']";

	// FirstName
	public static final String FirstName = "//input[@id='MainContent_txtMailing_contactFirstName']";

	// LAst Name
	public static final String LastName = "//input[@id='MainContent_txtMailing_contactLastName']";

	// Phone
	public static final String Phone = "//input[@id='MainContent_txtMailing_phoneNumber']";

	// Address
	public static final String Address1 = "//input[@id='MainContent_txtMailing_address1']";

	// City
	public static final String City = "//input[@id='MainContent_txtMailing_city']";

	// Country
	public static final String Country = "//select[@id='MainContent_drpBilling_countryID']";

	// State
	public static final String State = "//select[@id='MainContent_drpMailing_territoryID']";

	// Postalcode
	public static final String PostalCode = "//input[@id='MainContent_txtMailing_postalCode']";

	// Mailing Info
	public static final String Check_Mailing_Info = "//input[@id='MainContent_ckUseMailingInfo']";

	// Save
	public static final String Group_Save = "//input[@id='MainContent_btnSave']";

	// Folio
	public static final String Group_Folio = "//input[@id='MainContent_btnFolio']";

	// Add
	public static final String Group_Folio_Add_LineItem = "//input[@id='MainContent_Folio1_btnAdd']";

	// Add Line Item
	public static final String Group_Folio_Commit_Lineitem = "//input[@id='MainContent_Folio1_btnDone']";

	// Pay Buttom
	public static final String Group_Folio_Pay = "//input[@id='MainContent_Folio1_btnPay']";

	// Card Info
	public static final String Group_Folio_CardInfo = "//input[@id='btnCardInfo']";

	// Payment Method
	public static final String Group_Folio_PaymentMethod = "//select[@id='drpPaymentMethod']";

	// Name on card
	public static final String Group_Folio_NameOnCard = "//input[@id='txtNameOnCard']";

	// Card Number
	public static final String Group_Folio_CardNumber = "//input[@id='txtCreditCardNo']";

	// Exp Date
	public static final String Group_Folio_ExpDate = "//input[@id='txtExpdate']";

	// CVV
	public static final String Group_Folio_CVV = "//input[@id='txtCVVCode']";

	// OK
	public static final String Group_Folio_OK = "//input[@id='btnOK']";

	// Auth type
	public static final String Group_Folio_AuthType = "//select[@id='drpAuthType']";

	// Amount
	public static final String Group_Folio_Amount = "//input[@id='txtAmount']";

	// Process
	public static final String Group_Folio_Process = "//input[@id='btnAgingProcess']";

	// Advance Deposit Popup
	public static final String AdvanceDepositConfirmPopup = "//div[@aria-describedby='divAdvanceDepostConfirm']";
	public static final String AdvanceDepositConfirmPopup_Yes = "//div[@aria-describedby='divAdvanceDepostConfirm']//button[text()='Yes']";

	// Continue
	public static final String Group_Folio_Continue = "//input[@id='btnSaveTranPay']";

	// Ending Balance
	public static final String Group_Folio_EndingBalance = "//span[@id='MainContent_Folio1_fSummary1_lblAccEndBalance']";

	// Add
	public static final String Group_Folio_Add = "//input[@id='btnAgingAdd']";

	// Group Auto Apply
	public static final String Group_Folio_AutoApply = "//input[@id='btnAutoApplyPayment']";

	// Advance deposit
	public static final String Group_Folio_AdvanceDeposit = "//a[@id='MainContent_Folio1_fSummary1_lbtnDisplaycaption']";

	// Advance deposit Add
	public static final String Group_Folio_AdvanceDepositAdd = "//input[@id='btnAgingAdd']";

	// Advance deposit AutoApply
	public static final String Group_Folio_AdvanceDepositAutoApply = "//input[@id='btnAutoApplyPayment']";

	// Advance deposit Continue
	public static final String Group_Folio_AdvanceDepositContinue = "//input[@id='btnSaveTranPay']";

	// Room assignment

	public static final String RoomCharges = "//label[contains(text(),'Room Charges:')]//parent::div//following-sibling::span[@class='pamt']/span";
	public static final String RoomAssignmentButton = "//button[contains(@data-bind,'enable: EnableRoomPicker()')]";
	public static final String RoomAssignment_DatePicker_Button = "#modalRoomPickerReservation  i";
	public static final String RoomAssignment_DatePicker_Button_1 = "//div[@class='calFx ng-text-hint ng-dateField focused']//div[@class='calendarDateDiv ng-calendarIcon']//i";
	public static final String RoomAssignment_DatePicker_Button_2 = "(//button[contains(@data-bind,'enable: EnableRoomPicker()')])[2]";

	public static final String SelectDate = ".today.day";
	public static final String SearchRoomButton = "#modalRoomPickerReservation div:nth-child(5) div button";

	public static final String ClickSearchRoomButton = "//div[@id='modalRoomPickerReservation']//button[text()='Search']";

	public static final String RoomAssign_Check = "//input[contains(@data-bind,'enable: AssignRoomsEnabled')]";
	public static final String SelectRoomClasses = "//select[contains(@data-bind,' value: RoomClassId')]";
	public static final String SelectRoomNumbers = "//select[contains(@data-bind,' value: RoomId')]";
	public static final String FirstRoomNumberInList = "//select[contains(@data-bind,' value: RoomId')]//option[2]";
	public static final String CancelRoomAssignment_Button = "//div[@id='modalRoomPickerReservation']//button[.='Cancel']";
	public static final String SelectButton = "//button[contains(@data-bind, 'enable: EnableSelectButton')]";
	public static final String ContinueButton = "#ruleMessageForInnroad button:nth-child(4)";
	public static final String ConfirmPopup = "//h4[contains(@data-bind,'text: ModalTitleText')]";
	public static final String ConfirmButton = "//button[contains(@data-bind,'enable: YesButtonEnable')]";
	public static final String ReservationSaveButton = "//button[contains(@data-bind,'click: PreSave')]";
	public static final String ReservationSaveButton_2 = "(//button[contains(@data-bind,'click: PreSave')])[2]";

	public static final String FolioSaveButton = "//span[@id='bpjscontainer_61']//button[.='Save']";
	public static final String NightField = "//input[contains(@data-bind,'value: Nights')]";
	public static final String CopyButton = "//button[contains(@data-bind,'enable: EnableCopyButton()')]";
	public static final String CopyReservationTab = "//div[@class='sec_nav_in container']//span[contains(text(),'copy')]";
	public static final String RoomAssign_Number = "//span[contains(@data-bind,'    text: StatusId')]";
	public static final String ReservationTab = "sn_span3";
	public static final String CancelDepositePolicy_Button = ".modal-scrollable div button";
	public static final String CheckRule = ".rules_broken_icon span";
	public static final String Rules_Popup = "//h4[contains(@data-bind,'text: ruleTitle')]";
	public static final String OK_Button = "//div[@id='ruleMessageForInnroad']//button[.='OK']";
	public static final String More_Rules_button = "//span[contains(@data-bind,'    text: UnBrokenRulesCount')]";
	public static final String RoomAssign_Cancel = "//div[@id='modalRoomPickerReservation']//button[.='Cancel']";
	public static final String AvailableRoom = "//label[contains(@data-bind, 'text: AvailRoomsCount()')]";
	public static final String Continue = "//button[.='Continue']";
	public static final String RoleBroken_Continue = "//button[contains(@data-bind,' click: Continue')]";
	public static final String Enter_TravelAgen_Account = "//input[contains(@data-bind,' value: TravelAccountProfileSearchText')]";
	public static final String AccountPicker_Continue_Button = "//div[@id='ReservationAccountPickerConfirmationPopup']//button[contains(@data-bind,'click: continueClicked')]";
	public static final String Enter_GuestProfile_Name = "//input[contains(@data-bind,' value: $parent.GuestProfileSearchText')]";
	public static final String Select_GuestProfile = ".ui-menu-item a";
	public static final String GuestProfile_Popup = "//h4[.='Please select the content for the Reservation']";
	public static final String GuestProfile_Popup_Continue = "//div[@id='ReservationAccountPickerConfirmationPopup']//button[.='Continue']";
	public static final String CheckinReport_CancelButton = "//div[@class='header-confirmation']//button";
	public static final String ExtendReservation_Button = "//button[contains(@data-bind,'   enable: EnableExtendReservationButton')]";
	public static final String RoomChargerPopup = "//h4[.='Room Charges Changed']";
	public static final String RecalculateFolio_RadioButton = "//input[contains(@data-bind,'enable: ReCalculateFoliosEnabled')]";
	public static final String SelectClickOnReCalculateFolios = "//button[contains(@data-bind,'click: SelectClickOnReCalculateFolios')]";
	public static final String ApplyDeltaEnabled_RadioButton = "//input[contains(@data-bind,'enable: ApplyDeltaEnabled')]";
	public static final String NoRoomCharger_RadioButton = "//input[contains(@data-bind,'enable: NoChangeEnabled')]";
	public static final String Depart_Value = "//div[@id='StayInfo']//p[contains(@data-bind,'text: AssignedRoomsLength')]";
	public static final String Reservation_Tab = "(//a[contains(text(),'Reservations')])[3]";

	public static final String GuestProfileAttached = "//span[contains(@data-bind,'    text: $parent.GuestNameUI')]";
	// Reservation Status
	public static final String RollBackButton = ".fa.fa-reply";
	public static final String NoShowIcon = "//a[contains(@data-bind, '    click: NoShow_Reservation')]";
	public static final String VoidRoomChargerCheckbox = "//input[contains(@data-bind, 'enable: VoidChargesEnable, checked: VoidCharges')]";
	public static final String OkbuttonInNowShow = "//button[contains(@data-bind, 'click: OKClick')]";
	public static final String RollBackStatus_Select = "//select[contains(@data-bind, '    enable: drpReservationStatusEnabled')]";

	public static final String AddTask = "//a[@data-target='#ReservationTaskCreate']";
	public static final String TaskDetailPopup = "//div[@id='TaskDetailPopup']";
	public static final String SelectTaskCategory = "(//*[@id='TaskDetailPopup']//select)[1]";
	public static final String SelectTaskType = "(//*[@id='TaskDetailPopup']//select)[2]";
	public static final String CancelTask = "//*[@id='TaskDetailPopup']//button[text()='Cancel']";
	public static final String TaskListTable = "//tbody[contains(@data-bind,'taskList')]/tr";
	public static final String DeleteTaskButton = "(//span[@class='ButtonDeleteNormal'])[10]";// button[@class='btn

	public static final String DeleteTaskButton1 = "//span[@class='ButtonDeleteNormal']";// button[@class='btn

	public static final String SaveTaskButton = "//button[contains(@data-bind,'click: saveDetail')]"; // ir-close-btn
	// ir-circle-btn']";
	public static final String DeleteTaskYesButton = "(//div[@class='col-md-12 col-lg-12 col-xs-12 col-sm-12'])[15]//button[2]";
	public static final String DeleteTaskNoButton = "//button[@class='btn btn-success md ir-btn cp-confirmation-no']";

	// Folio
	public static final String Folio = "//a[.='Folio']";
	public static final String FolioTab = "(//div[@class='col-md-12 ng-tabs']//ul//li)[2]";

	public static final String AddButton = "//button[.='Add Line Item']";
	public static final String SelectCategory = ".trHeight25 tbody tr td select";
	public static final String SelectCategory1 = "//select[contains(@data-bind,'value: SelectedLedgerAccount')]";
	public static final String LineItemsFields = "//td[@class='lineitem-amount']//input";
	public static final String LineItems_Amount = "//input[contains(@data-bind,'value: Amount')]";
	public static final String CommitButton = "//button[.='Commit']";
	public static final String LineItems = ".trHeight25 tbody tr td span";
	public static final String LineItems_Description = ".trHeight25 tbody tr td a";
	public static final String VoidButton = "//button[.='Void']";
	public static final String RoomCharger_Tax = "//div[contains(@data-bind,'foreach: ComputedSummary')]//span//span";

	// Payment

	public static final String Account_PaymentPopup = "//div[@id='AccountPaymetItemDetail']";
	public static final String Account_PaymentPopupHeading = "//div[@id='AccountPaymetItemDetail']//h4/span";
	public static final String Reservation_PaymentPopup = "//div[@id='ReservationPaymetItemDetail']";
	public static final String Reservation_PaymentPopupHeading = "//div[@id='ReservationPaymetItemDetail']//h4/span";
	public static final String PayButton = "//button[text() = 'Pay']";
	public static final String PaymentPopUp = "//span[.='Payment Details']";
	public static final String TakePaymentPopUp = "//span[.='Take Payment']";
	public static final String PaymentPopUp1 = "//div[@id='ReservationPaymetItemDetail']//span[contains(@data-bind,'text: PopupHeading')]";
	public static final String PaymentPopup_Close = "//div[@id='AccountPaymetItemDetail']//button[text()='Close']";
	public static final String ConfrimButton = "//button[.='Confirm']";
	public static final String PaymentInfoButton = "//button[.='....']";//
	public static final String PaymentInfo_Input_Fields = "#bpjscontainer_32 input";
	public static final String OkButton = "//button[.='OK']";
	public static final String PaymentType = "//span[@id='bpjscontainer_28']//select[contains(@data-bind, 'options: $parent.PaymentMethodsList')]";
	public static final String PaymentDetail_PaymentType = "//div[@id='ReservationPaymetItemDetail']//select[contains(@data-bind, 'options: $parent.PaymentMethodsList')]";
	public static final String Amount_Field = ".col-md-7 input";
	public static final String Add_Pay_Button = "//div[@id='ReservationPaymetItemDetail']//button[contains(@data-bind,'visible: $parent.isAddButtonVisible')]";
	public static final String Payament_Continue_Button = "//*[@id='bpjscontainer_63']/div/div[1]/div[3]/div/div[2]/button[4]";
	public static final String ZeroPayment_AlertPopup = "//div[@id='ReservationPaymetItemDetail_Alert']//p";
	public static final String ZeroPayment_AlertPopupOk = "//div[@id='ReservationPaymetItemDetail_Alert']//button";
	public static final String Close_ReservationPaymentPopup = "//div[@id='ReservationPaymetItemDetail']//button[text()='Close']";
	public static final String Cancel_ReservationPaymentPopup = "//div[@id='ReservationPaymetItemDetail']//button[text()='Cancel']";

	public static final String Enter_Reservation = "//span[@id='bpjscontainer_28']//input[@placeholder='Reservation']";
	public static final String HouseAccoutn_PaymentType = "//div[@id='AccountPaymetItemDetail']//select[contains(@data-bind, 'options: $parent.PaymentMethodsList')]";
	public static final String HouseAccoutn_PaymentType_1 = "(//select[contains(@data-bind, 'options: $parent.PaymentMethodsList')])[2]";
	public static final String HouseAccoutn_Enter_Amount = "//div[@id='AccountPaymetItemDetail']//input[contains(@data-bind,'value: $parent.formattedAmount')]";
	public static final String HouseAccoutn_Payment_Info_Button = "//div[@id='AccountPaymetItemDetail']//button[contains(@data-bind,'click: $root.ShowCreditCardInfoPopup')]";
	public static final String HouseAccoutn_Enter_CardName = "//span[@id='bpjscontainer_65']//input[contains(@data-bind,'value: NameOnCard')]";
	public static final String HouseAccoutn_Enter_CardNum = "//span[@id='bpjscontainer_65']//input[contains(@data-bind,'value: $root.txtBilling_AccountNumber')]";
	public static final String HouseAccoutn_Enter_ExpDate = "//span[@id='bpjscontainer_65']//input[contains(@data-bind,'value: ExpDate')]";
	public static final String HouseAccoutn_Enter_Card_CVVCode = "//span[@id='bpjscontainer_65']//input[contains(@data-bind,'value: CvvCode')]";
	public static final String HouseAccoutn_Enter_Address = "//span[@id='bpjscontainer_65']//input[contains(@data-bind,'value: Address1')]";
	public static final String HouseAccoutn_Enter_Card_City = "//span[@id='bpjscontainer_65']//input[contains(@data-bind,'value: City')]";
	public static final String HouseAccoutn_Enter_Card_State = "//span[@id='bpjscontainer_65']//input[contains(@data-bind,'value: State')]";
	public static final String HouseAccoutn_Enter_PostalCode = "//span[@id='bpjscontainer_65']//input[contains(@data-bind,'value: PostalCode')]";
	public static final String HouseAccoutn_Enter_ApprovalCode = "//span[@id='bpjscontainer_65']//input[contains(@data-bind,'value: ApprovalCode')]";
	public static final String HouseAccoutn_CardOK_Button = "//span[@id='bpjscontainer_65']//button[contains(@data-bind,'click: btnOKClick')]";
	public static final String HouseAccoutn_Process_Button = "//div[@id='AccountPaymetItemDetail']//button[contains(@data-bind, 'visible: $parent.isProcessButtonVisible')]";
	public static final String HouseAccoutn_Continue_Pay_Button = "//div[@id='AccountPaymetItemDetail']//button[contains(@data-bind,' click: btnSaveTranPayClick')]";

	// House account
	public static final String Select_Account = "//div[@id='ReservationAccountPicker']//input[contains(@data-bind,'checked: isSelected')]";
	public static final String Search_Account_Button = "//div[@id='ReservationAccountPicker']//button[.='Search']";
	public static final String Select_Account_Button = "//div[@id='ReservationAccountPicker']//button[.='Select']";
	public static final String PaymentSwipe_Img = "//img[contains(@data-bind,'enable: !$parent.ShowLoading')]";
	public static final String SwipeCard_Field = "//input[contains(@data-bind,'value: txtTrackData')]";
	public static final String SubmitButton = "//button[contains(@data-bind,'click: Submit')]";
	public static final String SwipePaymentpopup = "//div[@id='ReservationCreditCardSwipeInfoPopup']//h4/span";
	public static final String CardSubmitButton = "//div[@id='ReservationCreditCardSwipeInfoPopup']//button[text()='Submit']";
	public static final String SwipeCard_Loader = "//div[@id='ReservationPaymetItemDetail']//div[@class='modules_loading']";

	public static final String Enter_Amount_Auth = "//input[contains(@data-bind,'value: $parent.formattedAmount')]";
	public static final String Enter_Amount = "//span[@id='bpjscontainer_28']//input[contains(@data-bind,'value: $parent.formattedAmount')]";
	public static final String Folio_AddPayment_Button = "//button[contains(@data-bind,'visible: ($parent.btnAddVisible()')]";
	public static final String RoomChargerAndOtherBalance = ".pamt span";
	public static final String HouseAccountPickerPopup = "//span[.='House Account Picker']";
	public static final String AccountName_Field = "//input[contains(@data-bind, 'visible: (AccountTypeId() != 4)')]";
	public static final String AccountNumber_Field = "//input[contains(@data-bind, 'value: AccountNumber')]";
	public static final String HouseAccount_SearchButton = "#bpjscontainer_39 div.modal-body div:nth-child(5) button";
	public static final String Select_HouseAccount = "//input[contains(@data-bind, 'checked: isSelected')]";
	public static final String Folio_SelectButton = "//button[contains(@data-bind, 'click: ContinueWithSelectedAccount')]";
	public static final String AccountNumber = "//span[contains(@data-bind, 'text: $data.AccountNo')]";
	public static final String GiftCertificateRadioButton = "#header > table > tbody > tr:nth-child(1) > td:nth-child(1) > input[type='radio']";
	public static final String GiftCertificateNumber = "//span[contains(@data-bind, 'text: $data.GiftCertificateNo')]";
	public static final String GiftCertificateSelectButton = "//button[contains(@data-bind, 'click: SelectBtnClick')]";
	public static final String OKButton = "//button[.='OK']";
	public static final String Folio_Select_HouseAccount = "//input[contains(@data-bind, 'checked: isSelected')]";
	public static final String Folio_AccountNumber = "//span[contains(@data-bind, 'text: $data.AccountNo')]";
	public static final String Continue_Pay_Button = "//span[@id='bpjscontainer_28']//button[contains(@data-bind,' click: btnSaveTranPayClick')]";
	public static final String PaymentDetail_Continue_Pay_Button = "//div[@id='ReservationPaymetItemDetail']//button[contains(@data-bind,' click: btnSaveTranPayClick')]";
	public static final String PaymentDetail_Enter_Amount = "//div[@id='ReservationPaymetItemDetail']//input[contains(@data-bind,'value: $parent.formattedAmount')]";
	public static final String PaymentDetail_Add_Pay_Button = "//div[@id='ReservationPaymetItemDetail']//button[contains(@data-bind,'visible: $parent.isAddButtonVisible')]";

	// Card info
	public static final String PaymentCardInfoButton = "//div[@id='ReservationPaymetItemDetail']//button[contains(@data-bind,'click: $root.ShowCreditCardInfoPopup')]";
	public static final String Payment_Info_Button = "//span[@id='bpjscontainer_28']//button[contains(@data-bind,'click: $root.ShowCreditCardInfoPopup')]";
	public static final String Enter_CardName = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[contains(@data-bind,'value: NameOnCard')]";
	public static final String Enter_CardNum = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[contains(@data-bind,'value: $root.txtBilling_AccountNumber')]";
	public static final String Enter_ExpDate = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[contains(@data-bind,'value: ExpDate')]";
	public static final String Enter_Card_CVVCode = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[contains(@data-bind,'value: CvvCode')]";
	public static final String Enter_Address = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[contains(@data-bind,'value: Address1')]";
	public static final String Enter_Card_City = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[contains(@data-bind,'value: City')]";
	public static final String Enter_Card_State = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[contains(@data-bind,'value: State')]";
	public static final String Enter_PostalCode = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[contains(@data-bind,'value: PostalCode')]";
	public static final String Enter_ApprovalCode = "//div[@id='ReservationPaymentDetailCreditCardInfo']//input[contains(@data-bind,'value: ApprovalCode')]";
	public static final String CardOK_Button = "//div[@id='ReservationPaymentDetailCreditCardInfo']//button[contains(@data-bind,'click: btnOKClick')]";
	public static final String Process_Button_Auth = "//button[contains(@data-bind, 'visible: $parent.isProcessButtonVisible')]";
	public static final String Process_Button = "//span[@id='bpjscontainer_28']//button[contains(@data-bind, 'visible: $parent.isProcessButtonVisible')]";
	public static final String PaymentDetail_Process_Button = "//div[@id='ReservationPaymetItemDetail']//button[contains(@data-bind, 'visible: $parent.isProcessButtonVisible')]";
	public static final String PaymentDetail_Add_Button = "//div[@id='ReservationPaymetItemDetail']//button[text()='Add']";
	public static final String ProcessBtn = ".btn-add-process-payment";
	public static final String CardPayment_CardInformatoinAlertMessage = "//div[@id='ReservationPaymetItemDetail']//div[contains(@data-bind,'lblMessageText')]";

	// Distribution
	public static final String DistributionLink = "ucNavSecondary_rptrNavMain_lbtnNavMainItem_4";
	public static final String Blackouts = "//li[contains(@data-bind,'css: showBlackoutsContent() ?')]//a";
	public static final String BlackOutRoomCheckBox = "//table/tbody/tr[1]/td[3]/input";
	public static final String BlackOutRoom_Save_Button1 = ".footer_buttons button";
	public static final String BlackOutRoom_Save_Button = "//span[@id='bpjscontainer_27']//button[contains(@data-bind,'click: saveDetail')]";
	public static final String Select_Client = "MainContent_ucPanelSupport_drpClientList";
	public static final String SyndicateLink = "//li[contains(@data-bind,'css: showSyndicationContent() ?')]";
	public static final String SyndicateRoomCheckBox = "//table/tbody/tr[2]/td[4]/input";
	public static final String SyndicateRoomSave = "//span[@id='bpjscontainer_24']//button[contains(@data-bind,'click: saveDetail')]";

	// Tap chart
	public static final String ReservationsLink = "nav-reservation";
	public static final String BlackOutCell = ".BlockedOut div div div div";
	public static final String NewQuote = "//span[.='New Quote']";
	public static final String DatePickerIcon = ".fa.fa-calendar";// 10 item 5
																	// item
	public static final String Quote_SearchButton = "//button[contains(@data-bind,'click: searchRateQuote')]";
	public static final String BlackOutAlert = "//h4[.='Blackouts Alert!']";
	public static final String Blackout_OkButton = "//button[contains(@data-bind,'visible: showOkButton')]";
	public static final String BookButton = "book_disabled";
	public static final String Reservation_Book_Button = "rate-book-green";
	public static final String QuoteBookButton = ".action_btns button";
	public static final String SyndicateCell = "//div[text()='S']";
	public static final String OutOfOrderCell = "//div[@title='325']//following-sibling::div//span/div/div/div/div/div[.='Out']";

	public static final String SaveReservation_Popup = "//div[@id='alertForTab']//button[.='Yes']";

	// New Quote
	public static final String AssignRoomCheckBox = "//input[@value='AssignRooms']";
	public static final String Rooms_Table = "//tbody[@id='tblRateQuoteGrid']";
	public static final String NoRatePopUp = "//h4[@id='myModalLabelforRuleMessage']";
	// OverView
	public static final String OverViewTab = "//a[@id='MainContent_lnkInventoryOverview']";
	public static final String OverView = "//a[@data-parent-id='fncMenuInventory' and text()='Overview']";
	public static final String OverView_Rooms = "//th[.='Room']";

	// Taxes Link
	public static final String TaxesLink = "//a[@id='ucNavSecondary_rptrNavMain_lbtnNavMainItem_2']";

	// Check in
	public static final String CheckIn_Button = "//button[contains(@data-bind,'visible: VisibleCheckInBtn')]";
	public static final String CheckIN_Confirm = "//button[contains(@data-bind,'click: function')]//span[.='Confirm']";

	// Reservation Groups
	public static final String GroupsAccounts_Title = "//font[text()='Accounts']";
	public static final String GroupsAccounts_Link = "(//tr[@class='dgItem']//td//a)[1]";
	public static final String GroupName = "//input[@id='MainContent_txtAccountFirstName']";
	public static final String GroupAccountNumber = "//input[@id='MainContent_txtAccountNo']";
	public static final String GroupNewResButton = "//input[@id='MainContent_btnNewReservation']";
	public static final String GroupAccountNumber_1 = "//input[@name='ctl00$MainContent$txtAccountNo']";

	public static final String Resgroups_AccountName = "MainContent_txtAccountName";
	public static final String Resgroups_AccountNumber = "MainContent_txtAccountNumber";
	public static final String Resgroups_GoButton = "MainContent_btnGoSearch";
	public static final String Resgroups_ReservToSelect = "//*[@id='MainContent_dgAccountList']/tbody/tr[2]/td[3]/a";
	public static final String Resgroups_AccountDetailsPage = "//*[@id='form1']/section";
	public static final String RoomBlocksTab = "MainContent_btnRoomBlock";

	public static final String ItemDatails_CancelButton = "//div[contains(@data-bind,'if: $root.showUpateUserInfo() ')]//following-sibling::div//button[.='Cancel']";
	public static final String TaxItem_Cancel_Account = "(//div[contains(@data-bind,'if: $root.showUpateUserInfo() ')]//following-sibling::div//button[.='Cancel'])[2]";
	public static final String ChangeDueDate_Popup = "(//h4[.='Change Due Date'])[2]";
	public static final String ChangeDueDatePopup = "//h4[.='Change Due Date']";
	public static final String ChangeDueDate_NoButton = "(//div[@id='divItemDueDateChangeConfirm']//button[.='No'])[2]";
	public static final String ChangeDueDateNoButton = "//div[@id='divItemDueDateChangeConfirm']//button[.='No']";

	public static final String GroupAccount_FirstActiveElement = "//table[@id='MainContent_dgAccountList']//tr[2]//td[3]//a";
	// Ledger Accounts
	public static final String Ledger_Accounts_Title = "//font[.='Ledger Accounts']";
	public static final String Create_New_LedgeAccount_ButtonClick = "//*[@id=\"MainContent_btnAddClient\"]";
	public static final String Ledger_Accounts_Type = "trAccount";
	public static final String Ledger_Accounts_Name_Inputs = "//tr//td//input[@class='TextDefault']";
	public static final String Ledger_Accounts_Name_1 = "//input[contains(@data-bind,'value: Account.AccountName')]";
	public static final String Ledger_Accounts_Description_Input = "MainContent_dgclientLegdAccounts_txtSysClientLedgerAccountDesc_27";
	public static final String Ledger_Accounts_DefaultAmount = "MainContent_dgclientLegdAccounts_txtSysClientDefaultAmount_27";
	public static final String Ledger_Accounts_SaveButtonClick = "//input[@id='MainContent_btnSaveMaster']";
	public static final String Ledger_Accounts_DeleteButtonClick = "MainContent_btnDeleteMaster";
	public static final String Ledger_Accounts_EditButtonClick = "MainContent_btnEditMaster";
	public static final String Ledger_Accounts_CancelButtonClick = "MainContent_btnCancelMaster";
	public static final String LedgerAccount_Chekbox = "//tr//td//input[@type='checkbox']";
	public static final String Ledger_Accounts_Status = "//tr//td//select[@class='TextDefault']";

	// Reports
	public static final String Select_LedgerType = "//select[@id='MainContent_ddlLedgerType']";
	public static final String AccountStatus = "//select[@id='MainContent_drpAccountStatus']";
	public static final String AccountsBalance = "//a[@id='ucNavSecondary_rptrNavMain_lbtnNavMainItem_0']";
	public static final String AccountBalanceGo_Button = "//input[@id='MainContent_btnGenreport']";
	public static final String AccountBalance_Header = "//font[.='Account Balance Summary']";
	public static final String Select_ReportDoenladType = "//select[@id='drpReportType']";
	public static final String AccountBalance_EffectiveDate = "//input[@id='MainContent_txtEffectiveDate']//following-sibling::img";
	public static final String ReservationType_Current = "//input[@id='MainContent_chklstReservationType_0']";
	public static final String ReservationType_Past = "//input[@id='MainContent_chklstReservationType_1']";
	public static final String ReservationType_Future = "//input[@id='MainContent_chklstReservationType_2']";
	public static final String Reservation_Pending = "//input[@id='MainContent_chkAccIncludePendingItems']";

	public static final String TaskPageSize0 = "//tr[@align='right']/td/span";
	public static final String TaskPageSize1 = "//tr[@align='right']/td/a";
	public static final String TaskList_Date = "//*[@id='MainContent_txtTaskListDate']";
	public static final String TaskList_ActionType = "//*[@id='MainContent_drpAction']";
	public static final String TaskList_SelectALlNoteTypes = "MainContent_chkIncludePastDueItems";
	public static final String TaskList_IncludePastDueItems = "MainContent_chkSelectAllNoteTypes";

	public static final String NewTaskList_Type = "MainContent_dgLineItems_drpNotesType_0";
	public static final String NewTaskList_Subject = "MainContent_dgLineItems_txtSubject_0";
	public static final String NewTaskList_SubjectPicker = "MainContent_dgLineItems_btnNotes_0";
	public static final String NewTaskList_Acc_Res = "//*[@id=\"MainContent_dgLineItems\"]/tbody/tr[2]/td[4]";
	public static final String NewTaskList_Room_ResBtn = "MainContent_dgLineItems_btnRoom_0";
	public static final String NewTaskList_Room_AccountBtn = "MainContent_dgLineItems_btnAccount_0";

	public static final String NewTaskList_DueDate = "MainContent_dgLineItems_txtDateTimeDue_0";
	public static final String NewTaskList_ActionComplete = "MainContent_dgLineItems_btnComplete_0";
	public static final String NewTaskList_ActionCancel = "MainContent_dgLineItems_btnCancelled_0";

	public static final String NewTaskList_ActionDelete = "MainContent_dgLineItems_btnDelete_0";
	public static final String NoteTypeList = "MainContent_chkNoteType";
	public static final String Room_Res_FirstRadioButton = "rbtnResIDRadioGroup";
	public static final String Room_Res_SelectBtn = "ucReservationControl1_btnSelect";
	public static final String Room_Res_NewResBtn = "ucReservationControl1_btnNew";
	public static final String Room_Res_GoBtn = "//*[@id='ucReservationControl1_BtnQueuesSearch']";
	public static final String Account_AccPicker_FirstRadioButton = "//*[@id='dgAccountsList_lblSelectRadio_0']/input";
	public static final String Account_AccPicker_GoButton = "btnGo";
	public static final String Account_AccPicker_NameInput = "txtAccountName";
	public static final String TaskList_PrintButton = "MainContent_imgPrintTaskList";
	public static final String TaskList_PDFReport = "tdViewPDF";
	public static final String TaskList_DatePicker = "//img[@name='cal_img']";

	// Task List

	public static final String TaskList_Note_Internal = "MainContent_chkNoteType_0";
	public static final String TaskList_Note_HouseKeeping = "MainContent_chkNoteType_1";
	public static final String TaskList_Note_Request = "MainContent_chkNoteType_2";
	public static final String TaskList_Note_WakeupCall = "MainContent_chkNoteType_3";
	public static final String TaskList_Note_GuestNote = "MainContent_chkNoteType_4";
	public static final String TaskList_Note_Complaint = "MainContent_chkNoteType_5";
	public static final String TaskList_Note_Message = "MainContent_chkNoteType_6";

	public static final String TaskList_Note_DepositRequired = "MainContent_chkNoteType_7";

	public static final String TaskList_Note_Email = "MainContent_chkNoteType_8";

	public static final String TaskList_Note_RoomMove = "MainContent_chkNoteType_9";

	public static final String TaskList_Note_EarlyArrival = "MainContent_chkNoteType_10";
	public static final String TaskList_Note_LateArrival = "MainContent_chkNoteType_11";

	public static final String TaskList_Note_LateDeparture = "MainContent_chkNoteType_12";

	public static final String TaskList_Note_Cancellation = "MainContent_chkNoteType_13";

	public static final String TaskList_Note_FrontDesk = "MainContent_chkNoteType_14";
	public static final String TaskList_Note_ParkingInformation = "MainContent_chkNoteType_15";
	public static final String TaskList_Note_ADA = "MainContent_chkNoteType_16";
	public static final String TaskList_Notes_Details = "//*[@id=\"ContentArea\"]";
	public static final String TaskList_Select_Subject_Click = "MainContent_dgLineItems_lbtnSubject_0";
	public static final String selectAction = "//select[@id='MainContent_drpAction']";
	public static final String Internal = "//label[.='Internal']//preceding-sibling::input";
	public static final String HouseKeeping = "//label[.='Housekeeping']//preceding-sibling::input";
	public static final String Request = "//label[.='Request']//preceding-sibling::input";
	public static final String WakeUpCall = "//label[.='Wake-up Call']//preceding-sibling::input";
	public static final String GuestNote = "//label[.='Guest Note']//preceding-sibling::input";
	public static final String Complaint = "//label[.='Complaint']//preceding-sibling::input";
	public static final String Message = "//label[.='Message']//preceding-sibling::input";
	public static final String DepositRequired = "//label[.='Deposit required']//preceding-sibling::input";
	public static final String Email = "//label[.='Email']//preceding-sibling::input";
	public static final String RoomMove = "//label[.='Room Move']//preceding-sibling::input";
	public static final String EarlyArrival = "//label[.='Early Arrival']//preceding-sibling::input";
	public static final String LastArrival = "//label[.='Late Arrival']//preceding-sibling::input";
	public static final String LateDeparture = "//label[.='Late Departure']//preceding-sibling::input";
	public static final String Cancellation = "//label[.='Cancellation']//preceding-sibling::input";
	public static final String FrontDesk = "//label[.='Front Desk']//preceding-sibling::input";
	public static final String ParkingInformation = "//label[.='Parking Information']//preceding-sibling::input";
	public static final String ADA = "//label[.='ADA']//preceding-sibling::input";
	public static final String includePastDueItems = "//input[@id='MainContent_chkIncludePastDueItems']";
	public static final String taskListGoButton = "//input[@id='MainContent_btnGo']";
	public static final String nextPage = "//table[@class='dgText']/tbody/tr[@align='right']/td/a";
	public static final String taskListRows = "//table[@class='dgText']/tbody/tr";
	public static final String taskListCols = "//table[@class='dgText']/tbody/tr/td";
	public static final String TaskList_SaveButton = "MainContent_btnSave";
	public static final String TaskList_GoButton = "MainContent_btnGo";
	public static final String TaskList_CancelButton = "MainContent_btnCancel";
	public static final String TaskList_NewButton = "MainContent_btnAdd";
	public static final String TaskList_NewButton1 = "//a[@class='btn color-white btn-success gs-btn mediumBtn']";
	public static final String TaskList_PageHyperLink = "//*[@id='MainContent_dgLineItems']/tbody/tr[22]/td/a[1]";
	public static final String NoteActionRequired = "//input[@id='chkActionReq']";
	public static final String NoteAction = "//select[@id='drpAction']";
	public static final String NotesSave = "//input[@id='btnSave']";
	public static final String SaveTaskList = "//input[@id='MainContent_btnSave']";
	public static final String TaskList_NewButton_GS = "//a[@data-target='#addTask']";
	public static final String Taskpopup_Status = "//div[@class='modal-content gs-borderR0']//dd[text()='To Do']";
	public static final String Taskpopup_Assignee = "//div[@class='modal-content gs-borderR0']//dd[text()='Unassigned']";
	public static final String Taskpopup_RoomCtegory = "//*[@id='myModalLabel']/span";
	public static final String StatusBar_Report = "//ul[@class='gs-searchFilter']";
	public static final String StatusBar = "//ul[@class='gs-searchFilter mob-divid-into-5']";
	public static final String StatusDrodown = "//span[text()='Status']";

	// Option
	public static final String EmailCheckbox = "MainContent_chkEmailDisplayName";
	public static final String EmailDisplayNameField = "MainContent_txtEmailDisplayName";

	public static final String New_Reservation_Button_2 = "//a[.='New Reservation']";
	public static final String New_Reservation_Tab_2 = "//span[.='New Reser...']";
	public static final String New_Reservation_Page_Load_2 = "//*[@id='ReservationDetailMain']/ul";
	public static final String Reservation_market_Segment_2 = "(//select[contains(@data-bind,'MarketSegmentValue')])[2]";
	public static final String Reservation_Referral_2 = "(//select[contains(@data-bind,'ReferralsValue')])[2]";
	public static final String Enter_Travel_Agent_2 = "//input[@placeholder='Find Account Profile']";
	public static final String Enter_Ext_Reservation_2 = "//input[@placeholder='Ext Reservation']";
	public static final String Enter_Guest_Profile_2 = "//input[@placeholder='Find Guest Profile']";
	public static final String Select_Saluation_2 = "(//label[.='Guest:']//following-sibling::div/div/div/select)[2]";
	public static final String Enter_First_Name_2 = "(//label[.='Guest:']//following-sibling::div/div/div/input[@placeholder='First Name'])[2]";
	public static final String Enter_Last_Name_2 = "(//label[.='Guest:']//following-sibling::div/div/div/input[@placeholder='Last Name'])[2]";
	public static final String Enter_Address_Search_2 = "(//input[@placeholder='Search Address'])[2]";
	public static final String Enter_Line1_2 = "(//input[@placeholder='Line 1'])[2]";
	public static final String Enter_Line2_2 = "(//input[@placeholder='Line 2'])[2]";
	public static final String Enter_Line3_2 = "(//input[@placeholder='Line 3'])[2]";
	public static final String Enter_City_2 = "(//input[@placeholder='City'])[2]";
	public static final String Select_Country_2 = "(//label[.='Country:']//following-sibling::div/select[contains(@data-bind,'CountryId')])[2]";
	public static final String Select_State_2 = "(//label[.='State:']//following-sibling::div/div/div/select[contains(@data-bind,'TerritoryId')])[2]";
	public static final String Select_State_3 = "(//select[contains(@data-bind,'options: stateLi')])[3]";
	public static final String Enter_Line1_3 = "(//input[@placeholder='Line 1'])[3]";
	public static final String Enter_Line2_3 = "(//input[@placeholder='Line 2'])[3]";
	public static final String Enter_Line3_3 = "(//input[@placeholder='Line 3'])[3]";
	public static final String Enter_Postal_Code_3 = "(//input[@placeholder='Postal Code'])[3]";
	public static final String Enter_Address_Search_3 = "(//input[@placeholder='Search Address'])[3]";
	public static final String Enter_Phone_Number_3 = "(//input[contains(@data-bind,'value: PhoneNumber')])[3]";
	public static final String Enter_City_3 = "(//input[@placeholder='City'])[3]";

	public static final String Enter_Postal_Code_2 = "(//input[@placeholder='Postal Code'])[2]";

	// public static final String
	// Enter_Phone_Number="//label[.='Phone:']//following-sibling::div/p/span[2]";
	public static final String Enter_Phone_Number_2 = "//input[contains(@data-bind,'value: PhoneNumber')]";
	public static final String Enter_Alt_Phn_number_2 = "(//label[.='Alternate Phone:']//following-sibling::div/div/div/input[contains(@data-bind,'AltPhoneNumber')])[2]";
	public static final String Enter_email_2 = "(//label[.='Email:']//following-sibling::div/div/div/input[contains(@data-bind,'Email')])[2]";
	public static final String Enter_Account_2 = "//input[@placeholder='Find Account']";
	public static final String Check_IsTaxExempt_2 = "//input[contains(@data-bind,' IsTaxExempt')]";
	public static final String Enter_TaxExemptId_2 = "//input[contains(@data-bind,' TaxExemptId')]";
	public static final String Check_isLongstay_2 = "(//label//input[contains(@data-bind,' IsLongStay')])[2]";
	public static final String Select_Payment_Method_2 = "//select[contains(@data-bind,'PaymentTypeId')]";
	public static final String Enter_Account_Number_2 = "//input[@placeholder='Account Number']";
	public static final String Enter_Account_Number_3 = "(//input[@placeholder='Account Number'])[2]";
	public static final String Enter_Exp_Card_3 = "(//input[contains(@data-bind,' BillingCreditCardExpiryDate')])[2]";
	public static final String Enter_Exp_Card_2 = "//input[contains(@data-bind,' BillingCreditCardExpiryDate')]";
	public static final String Select_Payment_Method_3 = "(//select[contains(@data-bind,'PaymentTypeId')])[2]";
	public static final String Enter_Billing_Note_3 = "(//input[contains(@data-bind,' BillingNotes')])[2]";
	public static final String Select_Payment_Method_4 = "(//select[contains(@data-bind,'LedgerAccountId')])[2]";
	public static final String Enter_Contact_Last_Name = "//label[.='Contact:']//following-sibling::div/div/div/input[@placeholder='Last Name']";

	public static final String Enter_Billing_Note_2 = "//input[contains(@data-bind,' BillingNotes')]";
	public static final String Add_Note_Reservation_2 = "//a[@data-target='#ReservationNotesCreate']";
	public static final String Click_RoomPicker_2 = "//button[contains(@data-bind,'EnableRoomPicker')]";

	public static final String SelectDate_2 = "(//th[@class='today'])[5]";
	public static final String RoomAssign_Check_2 = "";
	public static final String SearchRoomButton_2 = "//button[contains(@data-bind,'event: { click: SearchBtnClick }')]";
	public static final String SelectRoomNumbers_2 = "";
	public static final String SelectRoomClasses_2 = "";
	// Tax exempt
	public static final String TaxExmpt_Popup_2 = "//h4[.='Tax Exempt']";
	public static final String Set_TaxExe_Button_2 = "//button[.='Set Exemption Anyway']";
	public static final String Cancel_TaxExe_Button_2 = "//button[.='Cancel Exemption']";
	public static final String TaxExemptLabel = "//label[text()='Folio Balance:']//..//p//label";

	// NightAudit
	public static final String Go_ButtonClick = "MainContent_btnGo";
	public static final String AuditDatePicker = "//img[@name='cal_img']";
	public static final String AuditDate = "MainContent_txtDateStartNightAudit";
	public static final String ClickToday = "/html/body/div[4]/div[1]/table/tfoot/tr[1]/th";
	public static final String HouseKeepingText = "MainContent_rptrMenuRight_lblHouseKeepingTextRight_0";
	public static final String HouseKeepingCount = "//span[contains(@id,'CountTotalRoomsDirtyRight_0')]";
	public static final String SetNowButton = "MainContent_rptrMenuRight_btnSetRoomNowDirtyRight_0";
	public static final String UpdateRoomCondition_Popup = "//div[@role='dialog']//span[text()='Update Room Condition']";
	public static final String UpdateRoomCondition_Yes = "//div[@role='dialog']//button[text()='Yes']";
	public static final String DailyTransactionButton = "MainContent_rptrMenuLeft_lbtnMenuItem_1";
	public static final String DailyTransactionPage = "ContentArea";
	public static final String AllItemStatus = "MainContent_chkItemStatus_";
	public static final String DailyTransactionPage_GoBtn = "MainContent_btnSearch";
	public static final String DailyTransactionPage_PostBtn = "MainContent_btnPost";
	public static final String ItemToPost = "MainContent_dgCurrentNightAuditList_chkCurrentAll_0";
	public static final String PeriodIsOpenButton = "MainContent_rptrMenuLeft_lbtnMenuItem_0";
	public static final String PeriodIsOpen_LockButton = "btnLock";
	public static final String PeriodIsOpen_CancelButton = "btnCancel";
	public static final String PeriodStatusPage = "//*[@id='ContentArea']/tbody/tr/td[2]/table[2]";
	public static final String DialyTrans_CheckBoxAll = "MainContent_dgCurrentNightAuditList_ActuCurrentChkBoxAll";

	// Admin
	public static final String User_link = "//*[@id='MainContent_rptrMenu_lbtnAltMenuItem_1']";
	public static final String User_LastName = "//*[contains(@data-bind,'value: LastName')]";
	public static final String User_FirstName = "//*[contains(@data-bind,'value: FirstName')]";
	public static final String User_LoginID = "//*[contains(@data-bind,'value: LoginID')]";
	public static final String User_Email = "//*[contains(@data-bind,'value: Email')]";
	public static final String User_AssociateRoles = "//*[contains(@data-bind,'click: OpenRolePopup')]";
	public static final String User_AssociateProperties = "//*[contains(@data-bind,'click: OpenPropertiesPopup')]";
	public static final String User_Save = "//*[@data-bind='click: saveDetail']";

	// Ledger balance
	public static final String LedgerBalance_Tab = "//a[@id='ucNavSecondary_rptrNavMain_lbtnNavMainItem_1']";
	public static final String LedgerBalance_CheckIn = "//input[@id='MainContent_txtFromDate']//following-sibling::img";
	public static final String LedgerBalance_FromDate = "//input[@id='MainContent_txtFromDate']//following-sibling::img";
	public static final String LedgerBalance_ToDate = "//input[@id='MainContent_txtToDate']//following-sibling::img";
	public static final String LedgerBalance_CheckOut = "";
	public static final String LedgerBalance_ItemStatus = "//span[@id='MainContent_chkItemStatus']//input";
	public static final String LedgerBalance_AccountType = "//span[@id='MainContent_chkAccountType']//input";
	public static final String LedgerBalance_ReportType = "";
	public static final String GoButton = "//input[@id='MainContent_btnGenreport']";
	public static final String ActivDate = "(//th[text()='Today'])[1]";
	public static final String FirstOfTheMonth = "(//td[.='1'])[1]";
	public static final String Incidentials_Checkbox = "//input[@id='MainContent_rptLedgerAccountTypes_chkLedgerAccountType_0']";
	public static final String ShiftReport_RadioButton = "//input[@id='MainContent_rbRptShift']";
	public static final String DepartmentReport_RadioButton = "//input[@id='MainContent_rbRptDepartment']";
	public static final String LagerBalacePage = "//font[.='Ledger Balances']";
	public static final String Detail_RadioButton = "//input[@id='MainContent_rbtnLstReportType_1']";
	public static final String Summary_RadioButton = "//input[@id='MainContent_rbtnLstReportType_0']";
	public static final String Select_PreMonth = "(//i[@class='icon-arrow-left'])[1]";
	public static final String OldDate = ".old.day";
	public static final String ActiveDate = ".active.day";

	// Advance Deposit
	public static final String Select_FromDate = "(//input[@id='MainContent_txtFromDate']//following-sibling::img)[1]";
	public static final String Select_ToDate = "(//input[@id='MainContent_txtToDate']//following-sibling::img)[1]";
	public static final String ADSummary_RadioButton = "//input[@id='MainContent_rbnSummary']";
	public static final String ADDetail_RadioButton = "//input[@id='MainContent_rbnDetail']";
	public static final String ReportType_Inbound = "//input[@id='MainContent_rbtRptType_0']";
	public static final String ReportType_Outbound = "//input[@id='MainContent_rbtRptType_1']";
	public static final String ReportType_Net = "//input[@id='MainContent_rbtRptType_2']";

	// Merchant trans
	public static final String MerchantTrans_Tab = "//a[contains(@id,'ucNavSecondary_rptrNavMain_lbtnNavMainItem') and text()='Merchant Trans']";
	public static final String MerchantPage = "//font[@class='TitleText']";
	public static final String SelectMerchant_Date = "//input[@id='MainContent_txtTransactionsDate']//following-sibling::img";
	public static final String TranstionStatus = "//input[@id='MainContent_chkTransactionStatus_0']";
	public static final String MerchantCapture_Checkbox = "//input[@id='MainContent_chkTransactionStatus_1']";
	public static final String MerchantCancel_Checkbox = "//input[@id='MainContent_chkTransactionStatus_3']";
	public static final String MerchantCredit_Checkbox = "//input[@id='MainContent_chkTransactionStatus_2']";
	public static final String MerchantAuthorized_Checkbox = "//input[@id='MainContent_chkTransactionStatus_0']";

	// Daily Flash
	public static final String DailyFlash_Tab = "//a[@id='ucNavSecondary_rptrNavMain_lbtnNavMainItem_3']";
	public static final String DailyFlashPage = "//font[@class='TitleText']";
	public static final String SelectDailyFlash_Date = "//input[@id='MainContent_TxtDailyDate']//following-sibling::img";
	public static final String DailyFlash_SelectedDate = "//input[@id='MainContent_TxtDailyDate']";
	public static final String DailyFlash_RadioButton = "//input[@id='MainContent_rbtnLstReportType_0']";
	public static final String DailyFlachManagementTransfers_RadioButton = "//input[@id='MainContent_rbtnLstReportType_1']";
	public static final String DailyFlashBreakoutTaxExemptRev_CheckBox = "//input[@id='MainContent_chkExempt']";
	public static final String DailyFlash_ReportGenerated = "//*[@id='tdViewPDF']";
	public static final String NoDataAvailable_Message = "//span[@id='MainContent_lblErrorMessage']";

	// Room Forecast
	public static final String RoomForecast_Tab = "//a[@id='ucNavSecondary_rptrNavMain_lbtnNavMainItem_4']";
	public static final String RoomForecastPage = "//font[@class='TitleText']";
	public static final String SelectRoomForecast_FromDate = "//input[@id='MainContent_txtFromDate']//following-sibling::img";
	public static final String SelectRoomForecast_ToDate = "//input[@id='MainContent_txtToDate']//following-sibling::img";
	public static final String RoomForecast_FromDate = "//input[@id='MainContent_txtFromDate']";
	public static final String RoomForecast_ToDate = "//input[@id='MainContent_txtToDate']";
	public static final String RoomForecast_GeneratedReport = "//*[@id='tdViewPDF']";

	// Netsale
	public static final String NetSales_GroupBy = "MainContent_drpRptType";
	public static final String NetSales_ClientType = "MainContent_ddlClientType";
	public static final String NetSales_GoButton = "tdledgAccntDetailopts1";
	public static final String NetSales_GenratedReport_GroupByRoom = "tdViewPDF";
	public static final String NetSales_FromDate = "(//input[@id='MainContent_txtDateStart']//following-sibling::img)[1]";
	public static final String NetSales_ToDate = "(//input[@id='MainContent_txtDateEnd']//following-sibling::img)[1]";
	public static final String Today_Day = "//*[@class='today day']";
	// reservation 2

	public static final String Reservation_market_Segment_Copy = "(//select[contains(@data-bind,'MarketSegmentValue')])[2]";
	public static final String Reservation_Referral_Copy = "(//select[contains(@data-bind,'ReferralsValue')])[2]";
	public static final String Enter_Travel_Agent_Copy = "//input[@placeholder='Find Account Profile']";
	public static final String Enter_Ext_Reservation_Copy = "//input[@placeholder='Ext Reservation']";
	public static final String Enter_Guest_Profile_Copy = "//input[@placeholder='Find Guest Profile']";
	public static final String Select_Saluation_Copy = "(//label[.='Guest:']//following-sibling::div/div/div/select)[2]";
	public static final String Enter_First_Name_Copy = "(//label[.='Guest:']//following-sibling::div/div/div/input[@placeholder='First Name'])[2]";
	public static final String Enter_Last_Name_Copy = "(//label[.='Guest:']//following-sibling::div/div/div/input[@placeholder='Last Name'])[2]";
	public static final String Enter_Address_Search_Copy = "(//input[@placeholder='Search Address'])[3]";
	public static final String Enter_Line1_Copy = "(//input[@placeholder='Line 1'])[3]";
	public static final String Enter_Line2_Copy = "(//input[@placeholder='Line 2'])[3]";
	public static final String Enter_Line3_Copy = "(//input[@placeholder='Line 3'])[3]";
	public static final String Enter_City_Copy = "(//input[@placeholder='City'])[3]";
	public static final String Select_Country_Copy = "(//label[.='Country:']//following-sibling::div/select[contains(@data-bind,'CountryId')])[3]";
	public static final String Select_State_Copy = "(//label[.='State:']//following-sibling::div/div/div/select[contains(@data-bind,'TerritoryId')])[3]";
	public static final String Enter_Postal_Code_Copy = "(//input[@placeholder='Postal Code'])[3]";

	// Room Assignment 2
	public static final String RoomAssignmentButton_Copy = "(//button[contains(@data-bind,'    enable: EnableRoomPicker()')])[2]";
	public static final String RoomAssignment_DatePicker_Button_Copy = "(//div[@id='modalRoomPickerReservation']//i)[1]";

	// Checkout
	public static final String ProcessButton = "//div[contains(@data-bind,'getElement: BulkReservationsActionContainer')]//button[contains(@data-bind,'enable: isProcessEnable')]";
	public static final String BulkCheckOut_Popup = "//h4[contains(@data-bind,'html: BulkActionTitle')]";
	public static final String CloseButton = "(//button[contains(@data-bind,'click: closeBulkPopup')])[1]";

	public static final String ReservationToDrag = "//*[@id='bpjscontainer_53']/div/div[2]/div[2]/div[3]/div[2]/div[1]/div[2]/div[2]/span/span[1]/div/div/div[2]/div";
	public static final String NewPlaceForReserv = "//*[@id='bpjscontainer_53']/div/div[2]/div[2]/div[3]/div[2]/div[1]/div[2]/div[2]/span/div[3]";
	public static final String PreviousPlaceofReservation = "//*[@id='bpjscontainer_53']/div/div[2]/div[2]/div[3]/div[2]/div[1]/div[2]/div[2]/span/div[1]";
	public static final String ReservationToDragBack = "//*[@id='bpjscontainer_53']/div/div[2]/div[2]/div[3]/div[2]/div[1]/div[2]/div[2]/span/span[2]/div/div/div[2]/div";
	public static final String ConfirmChangesButton = "//*[@id='ReservationUpdate']/div[3]/div/span[2]/button";
	public static final String CancelChangesButton = "//*[@id='ReservationUpdate']//following-sibling::button[text()='Cancel']";
	public static final String ConfirmChanges_Button = "//*[@id='ReservationUpdate']//following-sibling::button[text()='Confirm Changes']";
	public static final String ReservationUpdatPage = "//*[@id='ReservationUpdate']";
	public static final String NextLocationPath = "//div[@title='325']//following-sibling::div/span/div[@class='tapechartdatecell noResAvail'][4]";
	public static final String AddressAutoSuggestionDropDown = "ui-id-6";
	public static final String GuestProfileAutoSuggestionDropDown = "ui-id-5";
	public static final String TravelAgentAutoSuggestionDropDown = "ui-id-4";
	public static final String AccountNameAutoSuggestionDropDown = "ui-id-7";
	public static final String Copy_Picked_Account_Data_To_Reservation_ContinueButton = "//div[@id='ReservationAccountPickerConfirmationPopup']//button[text()='Continue']";
	public static final String Account_name = "//*[@id='bpjscontainer_21']/div[3]/div/div[6]/div[2]/div[2]/div/div/div/div/div/div[2]/div[8]/div/div/div[1]/input";
	public static final String ResSaveButtonCopy = "//button[contains(@data-bind,'click: PreSave')]";
	public static final String Address_AutoSuggestionDropDown = "//ul[@id='ui-id-6']/li";
	public static final String GuestProfile_AutoSuggestionDropDown = "//ul[@id='ui-id-5']/li";
	public static final String TravelAgent_AutoSuggestionDropDown = "//ul[@id='ui-id-4']/li";
	public static final String AccountName_AutoSuggestionDropDown = "//ul[contains(@class,'accountPickerAuto')]/li";
	public static final String AccountLoading = "//div[@data-bind='foreach: ReservationDetails']//div[@class='modules_loading'][1]";
	public static final String TravelAgent_AutoSuggestionsAccountName = "//ul[@id='ui-id-4']/li/a/span[@class='span-account-name']/b";
	public static final String GuestProfile_AutoSuggestionsGuestName = "//ul[@id='ui-id-5']/li/a/span[@class='span-guest']/b";
	public static final String Address_AutoSuggestionsAddress = "//ul[@id='ui-id-6']/li/a";
	public static final String Account_AutoSuggestionsAccountName = "//ul[contains(@class,'accountPickerAuto')]/li/a/span[@class='span-account-name']/b";

	public static final String PaymentInfoPicker = "//a[contains(@data-bind,'click: fnShowPaymentInfo')]";
	public static final String FolioBillingInformationPopUp = "//div[@id='ReservationPaymetInfoDiv']";
	public static final String FolioPopUp_BillingName = "//div[@id='ReservationPaymetInfoDiv']//input[@placeholder='First Name']";
	public static final String FolioPopUp_PaymentMethod = "//div[@id='ReservationPaymetInfoDiv']//select[contains(@data-bind,'BillingTypeList')]";
	public static final String FolioPopUp_SaveButton = "//div[@id='ReservationPaymetInfoDiv']//button[contains(text(),'Save')]";
	public static final String FolioPopUp_AccountNumber = "//div[@id='ReservationPaymetInfoDiv']//input[@placeholder='Account Number']";
	public static final String FolioPopUp_ExpiryDate = "//div[@id='ReservationPaymetInfoDiv']//input[@placeholder='MM/YYYY']";
	public static final String FolioPopUp_ReservationNumber = "//div[@id='ReservationPaymetInfoDiv']//input[@placeholder='Reservation']";

	// Reservation -> Advance Section->Making info DropDown
	public static final String AdvanceMarketingInfo_SourceDropDownButton = "//button[contains(@title,'Source')]";
	public static final String AdvanceMarketingInfo_SourceDefaultValue = "//*[@id='bpjscontainer_22']/div/div/div/div[2]/div[2]/div[3]/div/form/div[1]/div/div/div[1]/div/button/span[1]";
	public static final String AdvanceMarketingInfo_InncenterDropDown = "//span[text()='innCenter']";
	public static final String AdvanceMarketingInfo_SearchButton = "//*[@id='bpjscontainer_22']/div/div/div/div[2]/div[2]/div[3]/div/form/div[3]/div/div/button[1]";
	public static final String AdvanceReservation_ItemPerPage = "//select[contains(@data-bind,'NoOfRecordsPerPage')]";
	public static final String AdvanceReservation_LastItemRoomClass = "(//span[contains(@data-bind,'RoomClassName')])[20]";
	public static final String AdvanceReservationInfo_RoomClassButton = "//button[contains(@title,'Room Class')]";

	public static final String AdvanceReservationInfo_DBRRoomClass = "//span[text()='Double Bed Room']";

	public static final String AdvanceGuestInfo_AccountName = "//*[@id='bpjscontainer_22']/div/div/div/div[2]/div[2]/div[1]/div/form/div[4]/div/div/div[1]/div/input";
	public static final String AdvanceGuestInfo_AccountNumber = "//*[@id='bpjscontainer_22']/div/div/div/div[2]/div[2]/div[1]/div/form/div[4]/div/div/div[2]/input";

	public static final String AdvanceReservation_LastItemAccountName = "(//span[contains(@data-bind,'AccountName')])[9]";

	// get select room class
	public static final String GetSelectedRoomNumber = "//span[contains(@data-bind,'Room.RoomDisplayName : Room.RoomClassName')]";
	public static final String ConfirmChangeReservation_Button = "//button[text()='Confirm Changes']";
	public static final String CancelChangeReservation_Button = "//button[contains(@data-bind,'click: UpdateCancel')]";
	public static final String ReservationTapeChart_ReservationTitle = "//*[@id='bpjscontainer_53']/div/div[2]/div[2]/div[3]/div[2]/div[1]/div[2]/div[2]";
	public static final String TapeChartAvailableSlot = "//div[text()='Available']";
	public static final String ClickTapeChartAvailableSlot = "(//div[@class='DatesContainer']//ancestor::div[contains(@class,'tapechartdatecell Available')])[1]";
	public static final String TapeChartAvailableSlot_RackRate = "(//div[@class='DatesContainer']//ancestor::div[contains(@class,'tapechartdatecell Available')])[1]";
	public static final String ClickReservation = "//div//div[@class='DatesContainer']";
	public static final String ReservationStatus = "(//select[contains(@data-bind,'StatusName')])[2]";
	public static final String NewDate_CheckOut = "(//*[@id='ReservationUpdate']//following-sibling::div[@class='datetext'])[1]";
	public static final String PreviousDate_CheckOut = "(//*[@id='ReservationUpdate']//following-sibling::div[@class='datetext-2'])[2]";
	public static final String TapeChart_1DayButton = "//*[@id='bpjscontainer_51']/div[2]/div[1]/div[1]/div/div/ul/li[1]/a";

	// Extend reservation popup
	public static final String CheckIn = "//div[@id='ReservationUpdate']//span[text()='Check In']";
	public static final String CheckOut = "//div[@id='ReservationUpdate']//span[text()='Check Out']";
	public static final String TripTotal = "//div[@id='ReservationUpdate']//span[text()='Trip Total']";
	public static final String BalanceDue = "//div[@id='ReservationUpdate']//span[text()='Balance Due']";

	public static final String ReportButtonList = "submit-btn-icon";
	public static final String ReservationPage = "//*[@id='bpjscontainer_18']";
	public static final String DownloadasPDF = "//div[.='Download As Pdf']";
	public static final String GuestRegistrationForm = "//td[@id='tdPDF']";

	// Folio_tab
	public static final String Folio_Tab = " //*[@id='newFolioCreatePopupReservation']/div[1]/h4";
	public static final String Click_Folio_Tab = "//*[@id='ReservationDetailMain']//following-sibling::a[contains(@data-bind,'click: switchToFoliosTab')]";
	public static final String Click_NewFolio = "//img[contains(@data-bind,'click: CreateNewFolio')]";
	public static final String New_FolioName = "//*[@id='newFolioCreatePopupReservation']//following-sibling::input";
	public static final String New_FolioDescription = "//*[@id='newFolioCreatePopupReservation']//following-sibling::textarea";
	public static final String Folio_Tab_Save = "//*[@id='newFolioCreatePopupReservation']//following-sibling::button[contains(text(), 'Save')]";
	public static final String Folio_Tab_Close = "//*[@id='newFolioCreatePopupReservation']//following-sibling::button[contains(text(), 'Close')]";

	// guest Services Task list
	public static final String AddTask_Button = "//a[@data-target='#addTask']";
	public static final String AddTask_Popup = "(//*[@class='modal-title text-center gs-modalTitle'])[1]";
	public static final String SelectTask = "//select[@name='taskType']//following-sibling::div/button";
	public static final String TypeSearch = "//input[@name='entityId']";
	public static final String TaskCategory = "//select[@name='category']//following-sibling::div/button";
	public static final String CategoryType = "//select[@name='categoryType']//following-sibling::div/button";
	public static final String Task_Detail = "//textarea[@id='taskDetail']";
	public static final String SearchTaskButton = "//div[@class='col-md-5']//button";
	public static final String SearchTaskInput = "(//div[@class='input-group'])[2]//input";
	public static final String FirstTaskInTaskListCheckBox = "(//span[@class='checkBox childCheckbox'])[1]";
	public static final String TaskList_DeleteTaskButton = "//a[@class='btn gs-btn mediumBtn']";
	public static final String BulkAction_ProceedButton = "//button[text()='Proceed']";
	public static final String TaskList_InfoPanelCloseButton = "//button[@class='close closeBtn']";
	public static final String FirstTaskInTaskListEditButton = "(//span[@class='icon gs-icon-edit color-blue gs-fontSize-18'])[1]";

	public static final String Task_Remarks = "//textarea[@name='taskRemark']";
	public static final String Task_Assignee = "//input[@name='taskAssignee']";
	public static final String Task_Save = "//button[text()='Save']";
	public static final String Report_AllAssignees = "//ul[contains(@class,'dropdown-menu')]//a[contains(text(),'All Assignees')]";
	public static final String Report_ByAssignees = "//ul[contains(@class,'dropdown-menu')]//a[contains(text(),'By Assignees')]";
	public static final String ClickDateFilter = " //span[@class='filterDates']";
	public static final String TaskListReportPage = "//div[@class='container TLViewContainer']";
	public static final String TaskListReportPage_ByAssignee = "//div[@class='container TLViewContainer pageBreak']";

	public static final String Number_of_Tasks = "//table[contains(@class, 'table gs-table gs-tdPad10')]/tbody/tr";
	public static final String TaskList_TasksTable = "//table[contains(@class,'taskList')]/tbody/*";
	public static final String TaskList_Tasks = "//table[contains(@class,'taskList')]/tbody/tr";
	public static final String TaskList_Filter = "//div[contains(@class,'task-filter')]";

	public static final String Filter_TaskCategory = "//div[contains(@class,'task-filter')]//ul/li[2]/div/div[1]//following-sibling::a";
	public static final String SelectFirstTaskCategory = "//*[@id='tf-taskcategory']/ul/li[1]/span";
	public static final String SelectFilter_TaskCategory = "//*[@id='tf-taskcategory']/ul/li";
	public static final String FirstTaskType_Name = "//*[@id='tf-taskcategory']/ul/li[1]";
	public static final String TaskType_CheckBox = "//table[contains(@class,'taskList')]/thead//following-sibling::span[contains(@class,'checkBox')]";
	public static final String BulkAction_Button = "//div[contains(@class,'task-actions')]//following-sibling::a[contains(text(),'Bulk Action')]";
	public static final String Assign_Staff = "//a[contains(text(),'Assign Staff')]";
	public static final String Change_Status = "//a[contains(text(),'Change Status')]";
	public static final String AssignStaff_Page = "(//div[@class='modal-content gs-borderR0'])[5]";
	public static final String AssignStaff_AssignTo = "//label[contains(text(),'Assign To')]//preceding-sibling::input";
	public static final String AssignStaff_Proceed = "(//button[contains(text(),'Proceed')])[2]";
	public static final String ChangeStatus_Page = "(//div[@class='modal-content gs-borderR0'])[6]";
	public static final String ChangeStatus_SelectStatus = "//div[@class='form-group gs-floatingLabel floating-label-left focused opt-2']//select[@name='taskStatus']//following-sibling::div/button";
	public static final String ChangeStatus_Proceed = "(//button[contains(text(),'Proceed')])[3]";
	public static final String ChangeStatus_StausUpdate = "//*[@id='accordionTasksResult']";
	public static final String ChangeStatus_Close = "//button[contains(text(),'Close')]";
	public static final String TaskList_TODO = "//span[text()='To Do']";
	public static final String TaskList_Inspection = "//span[text()='Inspection']";
	public static final String TaskList_Done = "//span[text()='Done']";
	public static final String TaskList_All = "//span[text()='All']";

	public static final String SearchRoomAccAssign = " //input[@placeholder='Search Room#, Acc name, Assigned and more']";
	public static final String TaskList_SearchButton = "//button[@type='submit']";
	public static final String EditTaskPopup = "//div[ @id='editTaskModal']";
	public static final String Taskpopup_GuestName = "//div[ @id='editTaskModal']//span[@class='gs-name']";
	public static final String Taskpopup_TaskDescription = "//div[ @id='editTaskModal']//span[@class='gs-disc']";
	public static final String Taskpopup_Close = "//div[ @id='editTaskModal']//button[@class='close closeBtn']";

	// public static final String TaskType_StatusDropDownButton =
	// "//table[contains(@class,'taskList')]//tr[1]//td[9]//div//div//button";

	public static final String TaskType_StatusDropDownButton = "(//button[@class='btn dropdown-toggle btn-default'])[1]";
	public static final String TaskType_StatusDropDownButton1 = "(//div[@class='gs-selectFLabel gs-selectFLabelList editMode'])[1]//button";
	public static final String TaskType_SelectStatusDropDown = "//table[contains(@class,'taskList')]//tr[1]//td[9]//div//select";
	public static final String TaskType_StatusDropDown_SaveBtn = "(//table[contains(@class,'taskList')]//tr[1]//td[9]//div//div)[3]//a[@title='Save']";

	public static final String Popup_LineItemDescription = "(//div[@data-bind='getElement: popUp']//td/a)[2]";
	public static final String RateDetailsPopup = "//h4[text()='Tax Item Details']";
	public static final String RateNameLabel = "//div[@id='popUpForInnroad']//label[text()='Item Name:']";

	public static final String TaskListFirstElementDate = "//table[@class='table gs-table table-hover gs-veticalAM gs-tdPad10 taskList']//td[6]";

	// Task Management
	public static final String TaskManagement_Tab = "//*[contains(text(),'General Settings')]";
	public static final String NewCategory = "//button[contains(text(),'New Category')]";
	public static final String NewCategory_Section = "//label[contains(text(),'New Task Category')]";
	public static final String AddTaskCategory = "//input[@id='newTaskType']";
	public static final String SaveCategory = "//button[@id='getStarted']";
	public static final String CategoryAlreadyExist = "//span[contains(text(),'Task category name already exist')]";
	public static final String CategoriesNames = "//label[contains(@class,'categoryText')]/span";
	public static final String CreateTaskType = "(//*[contains(text(),'Create Task Type')])[1]";
	public static final String CreateTaskType_Section = "//ul[@class='listTable create-new-tt']";
	public static final String TaskTypeName = "//input[@title='Create Task Type']";
	public static final String SaveTask = "//ul[@class='listTable create-new-tt']//following-sibling::button";

	public static final String TaskAlreadyExist = "//span[contains(text(),'Task type name already exist')]";
	public static final String Dialog = "//div [contains(@class,'dialog')]/div";
	public static final String Delete = "//button[contains(text(),'Delete')]";
	public static final String InspectionFullCleaning_Toggle = "(//div[@class='col-md-3']//span)[1]";
	public static final String SetRoomsToDirty_Toggle = "//div[contains(text(),'Set rooms to dirty')]//parent::div//span[contains(@class,'switchIcon')]";
	public static final String Enter_Acc_ApptovalCode = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]//input[contains(@data-bind,'value: ApprovalCode')]";

	// Logout
	public static final String CurrentUser = "//div[@class='user_des']";
	public static final String Logout = "//a[@id='logoutlink']";
	public static final String LoginPage = "//div[@id='clientLogin']";

	// Property
	public static final String SendCC_CustomEmailAddress_CheckBox = "//input[@id='MainContent_ChkMailCC']";
	public static final String SendCC_CustomEmailAddress_Email = "//input[@id='MainContent_txtccMail']";
	public static final String PropertyName_List = "//table[@id='MainContent_dgPropertyList']/tbody//following-sibling::tr[contains(@class,'dgItem')]//a";
	public static final String CreatedProperty_Pages = "//tr[@class='TextDefault']/td//following-sibling::*";

	public static final String AddTaskButton = "//a[@data-target='#ReservationTaskCreate']";
	public static final String TaskPopup = "//h4[text()='Add Task']";
	public static final String EnterTaskSubject = "//div[@id='taskDetailPopup']//input[contains(@data-bind,'value: Subject')]";
	public static final String SelectTaskStatus = "//div[@id='NotesDetailPopup']//select[contains(@data-bind,'value: NoteStatusId')]";
	public static final String EnterTaskDetails = "//div[@id='NotesDetailPopup']//textarea[contains(@data-bind,'value: NoteDetails')]";
	public static final String ClickTaskNote = "//div[@id='NotesDetailPopup']//button[contains(@data-bind,'click: saveDetail')]";

	public static final String RoomClassesToaster = "//div[@class='ant-notification-notice-message']";

	public static final String Select_AllFolio = "//input[contains(@data-bind,'click: SelectAllLineItems')]";
	public static final String BtnVoid = "//button[contains(@data-bind,'click: fnVoidFolioItems')]";
	public static final String TextArea_Notest = "//textarea[contains(@data-bind,'value: notesPopUpNote')]";
	public static final String Notes_Void = "//button[contains(@data-bind,'click: NotesSaveClick')]";
	public static final String AccountPaymentModule = "//div[contains(@class,'AccountPaymentDetailCreditCardInfo')]//div[@class='modules_loading']";
	public static final String ReservationFolioBalance = "//label[text()='Folio Balance:']//..//p//span";
	public static final String FolioTab_Reservation = "//a[contains(@data-bind,'click: switchToFoliosTab')]";
	public static final String GuestInfo_Tab = "//a[text()='Guest Info']";

	public static final String Reservation_CreateGuestProfile = "//span[contains(text(),'Create Guest Profile')]/preceding-sibling::input";

	public static final String Account_Folio_FolioOptions = "//a[contains(text(),'Folio Options')]";
	public static final String Account_Folio_AccountStatus = "//label[contains(text(),'Account Status')]";

	public static final String Account_Folio_FolioOptions_MoveToAccountFolio = "//label[contains(text(),'Move To Account Folio: ')]/following-sibling::div/select";

	public static final String Reservation_RoomChargeLineItem = "//td/span[contains(text(),'Room Charge')]";
	public static final String Reservation_Folio_DropDown = "//strong[contains(text(),'Reservation#: ')]/preceding-sibling::span/select";
	public static final String Reservation_Folio_Select_AllLineItems = "//input[@data-bind='click: SelectAllLineItems']";
	public static final String Reservation_Folio_ApplyRouting = "//button[contains(text(),'Apply Routing')]";
	public static final String Reservation_Folio_DisplayVoidItems = "//span[contains(text(),'Display Void Items')]/preceding-sibling::input";

	// Folio Line Item
	public static final String getRes1LineItem = "//table[@class='table table-striped table-bordered table-hover trHeight25']/tbody/tr[1]";
	public static final String getRes1LineItem2 = "//table[@class='table table-striped table-bordered table-hover trHeight25']/tbody/tr[2]";
	public static final String getRes1LineItem3 = "//table[@class='table table-striped table-bordered table-hover trHeight25']/tbody/tr[3]";
	public static final String getRes1LineItem4 = "//table[@class='table table-striped table-bordered table-hover trHeight25']/tbody/tr[4]";
	public static final String getRes1LineItem5 = "//table[@class='table table-striped table-bordered table-hover trHeight25']/tbody/tr[5]";

	public static final String LineItems_Checkbox = "//input[contains(@data-bind,'click: $parent.checkItem')]";
	public static final String VoidNotesPopup = "//div[@data-bind='getElement: NotesPopUpHandle']//h4";
	public static final String LineItemStatus = "//tbody//td[@class='text-center lineitems-changestatus']";
	public static final String BeforePostLineItem = "//tbody//img[contains(@data-bind,'click: $parent.fnChangeStatusFromPendingToPosted')]";
	public static final String LineItemDescription = "(//a[contains(@data-bind,'text: $data.Description')])[1]";
	public static final String LineItem_RollBackButton = "//div[@data-bind='getElement: popUp']//button[contains(@data-bind,'click: $parent.btnRollBack')]";
	public static final String enterRollBackNotes = "//div[@data-bind='getElement: popUp']//textarea[contains(@data-bind,'value: $parent.NewFolioNotes')]";
	public static final String RollbackContinueBtn = "//div[@data-bind='getElement: popUp']//button[contains(@data-bind,'click: ClickContinueButton')]";
	public static final String GetRoomClassNumber = "//span[contains(@data-bind,'Room.RoomDisplayName')]";
	public static final String Close_ReservationTab = "//span[text()='New Reser...']//following-sibling::i";
	public static final String OverBookingPopup = "//div[@id='ruleMessageForInnroad']//h4[text()='Room Overbooking']";
	public static final String OverBookingCancelBtn = "//div[@id='ruleMessageForInnroad']//button[text()='Cancel']";
	public static final String OverBookingContinueBtn = "//div[@id='ruleMessageForInnroad']//button[text()='Continue']";
	public static final String SelectSource = "//select[contains(@data-bind,'options: availableSources')]";
	public static final String CheckIn_Out_Field = "//div[@id='modalRoomPickerReservation']//input[contains(@data-bind,'dateField')]";
	public static final String EnterPromocode = "//input[contains(@data-bind,'value: RatePromoCode')]";
	public static final String VerifyLongStay = "MainContent_rptrMenuRight_lblCountTotalLongStayRight_1";
	public static final String LongStay_SetNowBtn = "MainContent_rptrMenuRight_btnSetLongStayRight_1";
	public static final String LongStay_Popup = "//span[text()='Long Stay']";
	public static final String LongStay_YesButton = "//div[@id='divLongStay']//following-sibling::div//button[text()='Yes']";
	public static final String RoomDirtyConfirmBtn = "//button[contains(@data-bind,'enable: YesButtonEnable')]";
	public static final String FolioTaxItem = "//td[@class='lineitem-tax']//span";
	public static final String ClosePolicyTab = "(//span[text()='Policies']//..//..//..//li//a/i)[7]";
	public static final String ReservationsStatus = "//select[contains(@data-bind,'options: ReservationStatuses')]";
	public static final String LongStayMessage = "//div[@id='divLongStay']//div[@class='.ui-dialog-content-div1']";
	public static final String LongStay_NoButton = "//div[@id='divLongStay']//following-sibling::div//button[text()='No']";
	public static final String EditOverRatePopup = "//font[text()='Rates Override Info']";
	public static final String LineItemPostedStatus = "//img[@src='./Folio_Images/2.gif']";
	public static final String RoomClass_Number = "//span[contains(@data-bind,'Room.RoomDisplayName')]";

	public static final String RoomClassNumber = "(//span[contains(@data-bind,'Room.RoomDisplayName')])[last()]";
	public static final String RuleBrokenBook_Btn = "//button[text()='Book']";
	public static final String AccountPyamentPopup = "//div[@id='AccountPaymetItemDetail']//span[@data-bind='text: PopupHeading']";
	public static final String FolioPayments = "//label[text()='Payments: ']//..//span//span";
	public static final String FolioBalance = "//label[text()='Balance: ']//..//span//span";
	public static final String AdvanceFolioBalance = "	//a[@data-bind='click: fnPayFromAdvanceDeposit']//following-sibling::span//span";
	public static final String Advance_Deposit_Balance = "(//a[contains(@data-bind,'click: fnPayFromAdvanceDeposit')])";
	public static final String Referral_MarketInfo_ValidationMessage = "(//span[@class='validationMessage'])[10]";
	public static final String FirstName_ContactInfo_ValidationMessage = "(//span[@class='validationMessage'])[11]";
	public static final String LastName_ContactInfo_ValidationMessage = "(//span[@class='validationMessage'])[12]";
	public static final String PhoneNumber_ContactInfo_ValidationMessage = "(//span[@class='validationMessage'])[21]";
	public static final String Address_ContactInfo_Line1_ValidationMessage = "(//span[@class='validationMessage'])[15]";
	public static final String City_ContactInfo_ValidationMessage = "(//span[@class='validationMessage'])[16]";
	public static final String PostalCode_ContactInfo_ValidationMessage = "(//span[@class='validationMessage'])[19]";
	public static final String State_ContactInfo_ValidationMessage = "(//span[@class='validationMessage'])[18]";
	public static final String Address_Line1_BillingInfo_ValidationMessage = "(//span[@class='validationMessage'])[28]";
	public static final String FirstName_BillingInfo_ValidationMessage = "(//span[@class='validationMessage'])[26]";
	public static final String PhoneNumber_BillingInfo_ValidationMessage = "(//span[@class='validationMessage'])[21]";
	public static final String City_BillingInfo_ValidationMessage = "(//span[@class='validationMessage'])[29]";
	public static final String State_BillingInfo_ValidationMessage = "(//span[@class='validationMessage'])[31]";
	public static final String PostalCode_BillingInfo_ValidationMessage = "(//span[@class='validationMessage'])[32]";
	public static final String SelectRoom_ValidationMessage = "(//span[@class='validationMessage'])[10]";
	public static final String RoomSelection_Message_RoomAssignPage = "(//div[contains(@data-bind,'text: NoResultsFoundMessage')])[5]";
	public static final String StartDate_ValidationMessage_RoomAssignPage = "(//span[@class='validationMessage'])[90]";
	public static final String EndDate_ValidationMessage_RoomAssignPage = "(//span[@class='validationMessage'])[91]";
	public static final String NightsCount_ValidationMessage_RoomAssignPage = "(//span[@class='validationMessage'])[92]";
	public static final String AdultsCount_ValidationMessage_RoomAssignPage = "(//span[@class='validationMessage'])[93]";

	public static final String Toaster_ValidationMessage = "//div[@class='toast-message']";
	public static final String AbortBtn = "//button[contains(@data-bind,'click: fnAbort')]";
	public static final String RollBackCancelBtn = "(//div[@data-bind='getElement: popUp']//button[text()='Cancel'])[2]";
	public static final String LineItemDate = "//td[@class='lineitem-date']//input";
	public static final String ItemPostingFailure_Popup = "//div[@id='divItemPostMessage']//h4";
	public static final String LineItemPostingFailure_OkBtn = "//div[@id='divItemPostMessage']//button";
	public static final String TotalCharger = "//label[text()='Total Charges: ']//..//span[@class='pamt']//span";
	public static final String Balance = "//label[text()='Balance: ']//..//span[@class='pamt']//span";

	public static final String CurrentBalance = "(//span[contains(@class,'PaymentDetailBorderless')])[1]";
	public static final String CurrentPayment = "(//span[contains(@class,'PaymentDetailBorderless')])[2]";
	public static final String EndingBalance = "(//span[contains(@class,'PaymentDetailBorderless')])[3]";

	public static final String PolicyAttributePercentage_Checkbox = "//input[contains(@data-bind,'checkedValue: depChargesType')]";
	public static final String NewReservation_link = "//a[@data-bind='click: NewReservation']";
	public static final String AccountPicker_ContinueBtn = "//div[@id='ReservationAccountPickerConfirmationPopup']//button[text()='Continue']";
	public static final String VerifyAccountName = "//span[contains(@data-bind,'text: $parent.Accountname')]";
	public static final String PickAccountFromDropDwon = "//span[@class='span-nomber']";

	public static final String Deposit_Auto_apply = "//div[@class='padLR5 padTB3']//button[.='Auto Apply']";
	public static final String Deposit_Auto_applyAdd = "//div[@class='padLR5 padTB3']//button[.='Add']";
	public static final String Deposit_Auto_applyContinue = "//div[@class='col-md-7 text-right']//button[.='Continue']";
	public static final String Deposit_paid = "(//img[@src='./Folio_Images/partially-paid.png'])[3]";
	public static final String Advance_Deposit_Click = "//div[@class='text-right']//span[.='Advance Deposit Balance']";

	// Account Page Validations

	public static final String AccountPage_MailingInfo_FirstName_ValidationMessage = "(//span[@class='validationMessage'])[51]";
	public static final String AccountPage_MailingInfo_PhoneNumber_ValidationMessage = "(//span[@class='validationMessage'])[53]";
	public static final String AccountPage_MailingInfo_AddressLine_ValidationMessage = "(//span[@class='validationMessage'])[57]";
	public static final String AccountPage_MailingInfo_City_ValidationMessage = "(//span[@class='validationMessage'])[61]";
	public static final String AccountPage_MailingInfo_State_ValidationMessage = "(//span[@class='validationMessage'])[62]";
	public static final String AccountPage_MailingInfo_PostalCode_ValidationMessage = "(//span[@class='validationMessage'])[63]";

	public static final String AccountPage_BillingInfo_FirstName_ValidationMessage = "(//span[@class='validationMessage'])[65]";
	public static final String AccountPage_BillingInfo_PhoneNumber_ValidationMessage = "(//span[@class='validationMessage'])[67]";
	public static final String AccountPage_BillingInfo_AddressLine_ValidationMessage = "(//span[@class='validationMessage'])[71]";
	public static final String AccountPage_BillingInfo_City_ValidationMessage = "(//span[@class='validationMessage'])[75]";
	public static final String AccountPage_BillingInfo_State_ValidationMessage = "(//span[@class='validationMessage'])[76]";
	public static final String AccountPage_BillingInfo_PostalCode_ValidationMessage = "(//span[@class='validationMessage'])[77]";
	public static final String AccountPage_NotesDetails_Subject_ValidationMessage = "(//span[@class='validationMessage'])[129]";
	public static final String AccountPage_PhoneNumber_InvalidMessage = "(//span[@class='validationMessage'])[55]";
	public static final String AccountPage_AccountName_ValidationMessage = "(//span[@class='validationMessage'])[47]";
	public static final String AccountPage_NotesPage_Cancelbutton = "(//button[contains(@data-bind, 'click: Cancel')])[23]";
	public static final String AccountPage_NotesButton = "//a[contains(@data-bind,'click: addAccountNotes')]";
	public static final String AddNotes_NotesType = "//span[contains(@data-bind,'text: NoteTypeName')]";
	public static final String AddNotes_NotesSubject = "//a[contains(@data-bind,'text: Subject')]";
	public static final String Select_DepartDate = "//input[contains(@data-bind,'value: DepartDate')]//following-sibling::div//a/i";
	public static final String Overview_MaxPerson = "((//table[@class='FilterTable']//tr)[12]//td)//input";
	public static final String Overview_GoButton = "//input[@id='MainContent_btnSearch']";
	public static final String DateSelection_EditRatePopup = "//img[@class='grid-cal-img']";
	public static final String SelectDate_From = "//input[@id='txtDateFrom']";
	public static final String SelectDate_To = "//input[@id='txtDateTo']";
	public static final String RateOverridenInfo_GoButton = "//input[@id='btnGo']";
	public static final String DateSelectionFrom_OverviewPanel = "//img[@name='cal_img']";

	public static final String AllowNonZeroBalanceCheckbox = "MainContent_chkAllowNonZeroBalance";
	public static final String LogOutButton = "//*[@id='Span1']";
	public static final String LogOut = "//*[@id='logoutLinkMobile']";
	public static final String Click_ADD_Btn = "(//div[@id='ReservationPaymetItemDetail']//button[.='Add'])[1]";
	public static final String CashLineItemFolio = "(//span[text()='Cash']/../following-sibling::td[@class='lineitem-amount']/span)[2]";
	public static final String McLineItemFolio = "(//span[text()='MC']/../following-sibling::td[@class='lineitem-amount']/span)[2]";
	public static final String Verify_Amount_Value = "//div[@id='tab1']//span[contains(@data-bind,' text: Amount')]";
	public static final String Verify_Closing_Amount_Value = "(//div[@id='Div1']//span[contains(@data-bind,' text: Amount')])[2]";

	public static final String CreateGuestCheckBox = "//input[contains(@data-bind,'checked: chkCreateGuestProfileChecked')]";

	public static final String RoomChargerPopup_RAP = "//div[@id='divRoomPickerReCalculateFolios']//h4[.='Room Charges Changed']";
	public static final String RecalculateFolio_RadioButton__RAP = "//div[@id='divRoomPickerReCalculateFolios']//input[contains(@data-bind,'enable: ReCalculateFoliosEnabled')]";
	public static final String Select__RAP = "//div[@id='divRoomPickerReCalculateFolios']//button[contains(@data-bind,'click: SelectClickOnReCalculateFolios')]";
	public static final String ApplyDeltaEnabled_RadioButton__RAP = "//div[@id='divRoomPickerReCalculateFolios']//input[contains(@data-bind,'enable: ApplyDeltaEnabled')]";
	public static final String NoRoomCharger_RadioButton_RAP = "//div[@id='divRoomPickerReCalculateFolios']//input[contains(@data-bind,'enable: NoChangeEnabled')]";
	public static final String RollBackCashItem = "//div[@class= 'col-md-12']//button[.='Rollback Status']";
	public static final String RollBackCashItemNotes = "//div[@class= 'col-md-7']//textarea[contains(@data-bind,'value: Notes')]";
	public static final String RollBackCashItemContinue = "//button[@class= 'btn blue btn-payment-continue']";

	public static final String GuestProfile_Checbox = "//input[contains(@data-bind,'checked: chkCreateGuestProfileChecked')]";
	public static final String PaymentRollBack_Button = "//button[text()='Rollback Status']";
	public static final String EnterpaymentNotes = "//textarea[@data-bind='value: Notes']";

	public static final String List_LineItemAmount = "//td[@class='lineitem-amount']//span";
	public static final String Reservations_Status = "//select[contains(@data-bind,' enable: drpReservationStatusEnabled')]";

	public static final String LineItemsList = "//table[@class='table table-striped table-bordered table-hover trHeight25']//tr";

	public static final String foliolineItemDescField = "//input[contains(@data-bind,'value: LedgerAccountDescription')]";

	public static final String Foliolineitems_QTY = "//input[contains(@data-bind,'value: Quantity')]";

	public static final String IncludeTaxLineItemCheckbox = "//input[contains(@data-bind,'checked: $root.IncludeTaxesInLineItems')]";

	public static final String List_LineItemTax = "//td[@class='lineitem-tax']//span";
	public static final String TexExempt_CheckBox = "//input[@data-bind='checked: $parent.IncludeCheckbox']";
	public static final String SetExemptionAllItems_Button = "//button[text()='Set Exemption for all valid items']";
	public static final String SetExemptionFutureItems_Button = "//button[text()='Set Exemption for all future items only']";
	public static final String LongStayCheckbox = "//input[contains(@data-bind,'checked: IsLongStay')]";

	// Property
	public static final String propertyName = "//table[@id='MainContent_dgPropertyList']//td/a";
	public static final String Property_Grid = "//font[.='Property Details']";
	public static final String Property_CreditCard = "//input[@id='MainContent_chkCreditCardForGuarantee']";
	public static final String OptionGuaranteed = "//label[.='For Guaranteed Reservations']//preceding-sibling::input";
	public static final String OptionCreditCardAlways = "(//label[.='Always']//preceding-sibling::input)[1]";

	// New User

	public static final String New_User_Btn = "//button[.='New User']";
	public static final String Users_Search_Btn = "//div[@id='userSearch']//button[text()='Search']";
	public static final String First_Element_In_Users_SearchResults = "(//tbody[@data-bind='foreach: userList']//tr)[1]//a";
	public static final String Enter_User_LoginId = "//div[@id='userDetail']//input[@placeholder='Login ID']";
	public static final String Users_Reset_Btn = "//div[@id='userDetail']//button[.='Reset']";

	public static final String UserFirstName = "//div[@id='userDetail']//following-sibling::input[@placeholder='First Name']";
	public static final String UserLastName = "//div[@id='userDetail']//following-sibling::input[@placeholder='Last Name']";
	public static final String UserLogin = "//div[@id='userDetail']//following-sibling::input[@placeholder='Login ID']";
	public static final String UserEmail = "//div[@id='userDetail']//following-sibling::input[@placeholder='Email']";
	public static final String User_Reset_Btn = "//div[@id='userDetail']//following-sibling::button[text()='Reset']";
	public static final String User_Save_Btn = "//div[@id='userDetail']//following-sibling::button[text()='Save']";

	public static final String User_ResetPassword_Btn = "//div[@id='userDetail']//following-sibling::button[text()='Reset Password']";
	public static final String User_AssociateRole_Btn = "//div[@id='userDetail']//following-sibling::button[text()='Associate Roles']";
	public static final String User_AssociateProperties_Btn = "//div[@id='userDetail']//following-sibling::button[text()='Associate Properties']";
	public static final String RolePicker_popup = "//div[@id='roleModal']";
	public static final String AddAssociateRole = "//div[@id='roleModal']//following-sibling::button[contains(@data-bind,'AddNew')]";
	public static final String SelectAssociateRole = "//div[@id='roleModal']//following-sibling::select[contains(@data-bind,'chosenObjectsToAssign')]";
	public static final String SelectedAssociateRole = "//div[@id='roleModal']//following-sibling::select[contains(@data-bind,'chosenObjectsToUnAssign')]";
	public static final String User_AssociateRole_Done = "//div[@id='roleModal']//following-sibling::button[text()='Done']";
	public static final String PropertyPicker_popup = "//div[@id='roleModal']";
	public static final String AddAssociateProperty = "//button[contains(@data-bind,'AddNew')]";
	public static final String SelectAssociateProperty = "//select[contains(@data-bind,'selectedOptions: chosenObjectsToAssign')]";
	public static final String User_AssociateProperty_Done = "//div[@id='roleModal']//button[text()='Done']";
	public static final String CloseNewUserTab = "(//i[contains(@data-bind,'click: $parent.closeTab')])[4]";

	public static final String Search_LastName = "//input[contains(@data-bind,'value: searchLastName')]";
	public static final String Search_FirstName = "//input[contains(@data-bind,'value: searchFirstName')]";
	public static final String Search_LoginId = "//input[contains(@data-bind,'value: searchLoginID')]";
	public static final String Search_Email = "//input[contains(@data-bind,'value: searchEmail')]";
	public static final String User_SearchButton = "//button[contains(@data-bind,'click: goSearchUsers')]";
	public static final String VerifySearch = "//a[contains(@data-bind,'text: LoginID')]";
	public static final String Select_Status = "//select[contains(@data-bind,'options: $parent.Statuses')]";
	public static final String Select_AllProperties_Checkbox = "//input[contains(@data-bind,'checked: IsPropertyAlwaysAvailable')]";
	public static final String SetNewPassword_Title = "(//h1[@class='ng-login-title'])[2]";
	public static final String SetNewPassword_NewPasswordInputField = "//input[@id='inputPassword']";
	public static final String SetNewPassword_ConfirmPasswordInputField = "//input[@id='inputPasswordConfirm']";
	public static final String SetNewPassword_SubmitButton = "//a[@id='btnChangePassword']";

	public static final String Relogin_Message_LoginPage = "(//div[@class='col-md-12'])[2]";
	public static final String UserPage_SelectStatus = "//select[contains(@data-bind,'options: availableStatuses')]";
	public static final String UserPage_SearchedUser_Status = "(//table[@class='table table-striped table-bordered table-hover']//tbody//tr//td)[5]//span";

	public static final String TapeChartDay1Button = "(//a[contains(@data-bind,'click: $parent.updateNumberOfDays')])[1]";
	public static final String Dirty_Room_YesButton = "//button[contains(@data-bind,'enable: YesButtonEnable')]";

	public static final String SelectRatePlan_EditRate = "drpRatePlan";
	public static final String SelectRoomClass_EditRate = "drpRoomClass";
	public static final String GoButton_EditRate = "btnGo";
	public static final String OverViewTodayButton = "//input[@name='btnTodayDate']";
	public static final String OverView_DateStart = "//input[@id='MainContent_txtDateStart']";
	public static final String AccountNumber_HouseAccountPopup = "//span[@id='bpjscontainer_42']//input[@data-bind='value: AccountNumber']";
	public static final String SelectRatePlan_Overview = "//select [@id='MainContent_drpRatePlan']";

	// Reports
	public static final String Reports_DailyFlash = "//a[@data-id='dailyFlashReport.aspx']";
	public static final String Repoprts_DailyFlash_Calender = "//input[@id='MainContent_TxtDailyDate']/following-sibling::img";
	public static final String Repoprts_DailyFlash_Calender_Right = "//i[@class='icon-arrow-right']";
	public static final String Repoprts_DailyFlash_Go = "//input[@id='MainContent_btnGenreport']";
	public static final String NetSales_Stayon_FromDate = "//input[@id='MainContent_txtDateStart']";
	public static final String NetSales_Stayon_ToDate = "//input[@id='MainContent_txtDateEnd']";
	public static final String List_LineItemDates = "//td[@class='lineitem-date']//span";
	public static final String LoginModuleLoding = "(//div[@class='dvLoading'])[1]";

	// Selected Ledger Account
	public static final String SelectedLedgerAccount = "//select[@id='lstPickedTaxes']";

	public static final String TaskListNotesType = "//select[@id='drpNotesType']";
	public static final String TaskList_NotesType = "(//label[.='Notes Type:']//following::select)[1]";
	public static final String TaskList_Action = "//select[@id='drpAction']";
	public static final String TaskList_ActionRequired = "//input[@id='chkActionReq']";
	public static final String TaskListPopUp_CancelButton = "//input[@id='btnCancel']";
	public static final String NotesPopUpCancelButton = "(//div[@class='text-right']//button[.='Cancel'])[10]";
	public static final String NotesTitle = "//h4[.='Note Details']";
	public static final String TaskListActionRequired = "(//input[@name='actionReq'])[1]";
	public static final String TaskListAction = "(//label[.='Action:']//following::select)[1]";

	public static final String ClosePrintIconPopup = "//div[@class='close-modal']";
	public static final String CountOfAdvancedSearchResults = "//label[contains(text(),'Record(s) found')]/preceding-sibling::span";
	public static final String clickPrintIcon = "//a[@class='ng-exportIcon ng-marRight-10 ng-marTop-10']";
	public static final String PrintPopUp = "//h4[@id='myModalLabelforOptions']";
	public static final String ResRadioButtons = "//div[@class='optionContent']//input[@type='radio']";
	public static final String ResRadioButtonsText = "//input[@type='radio']/../span[@data-bind='text:label']";
	public static final String ResDialogBoxText = "//input[@type='checkbox']/../span[contains(text(),'Include')]";
	public static final String AllRadioButtonsList = "//input[@name='optionsGroup'][2]";
	public static final String PrintAllRadioButtons = "//div[@class='optionContent']//input[@type='radio']";
	public static final String ResPrintButton = "//div[@class='innroad-btn-submit print']";
	public static final String ResEmailButton = "//div[@class='innroad-btn-submit email']";
	public static final String ResExportButton = "//div[@class='innroad-btn-submit export']";
	public static final String ExportlistButton = "//i[@class='fa fa-chevron-down']";
	public static final String AllExportsButtons = "//div[@data-bind='text:Name']";

	public static final String CreateGuestProfile = "//input[contains(@data-bind, 'chkCreateGuestProfileChecked')]";
	public static final String Click_Save = "//button[contains(@data-bind, 'click: PreSave')]";
	public static final String BasicRes_Status_Check = "(//span[contains(@data-bind,'StatusName')])[1]";
	public static final String Select_RatePlan = "//div[@class='col-md-4 vLrmargin']//select[contains(@data-bind,'RatePlanName')]";
	public static final String SearchPopUp = "//div[contains(text(),'Room Classes are not available')]";
	public static final String AdvancedSearch = "//button[contains(text(),'Advanced')]";

	// Season
	public static final String SelectSeasonTodayDate = "//th[text()='Today']";
	public static final String ClickOnNext = "//th[@class='next']//div";
	public static final String NewSeason_Button = "//button[contains(@data-bind, 'click: createNewSeason')]";
	public static final String NextMonthSelect = "//div[@class='datepicker-days']//th[@class='next']//div";
	public static final String click_effectiveOnAsWednesday = "(//input[@type='checkbox'])[3]";
	public static final String click_effectiveOnAsThursday = "(//input[@type='checkbox'])[4]";
	public static final String click_effectiveOnAsFriday = "(//input[@type='checkbox'])[5]";
	public static final String click_effectiveOnAsSaturday = "(//input[@type='checkbox'])[6]";
	public static final String click_effectiveOnAsSunday = "(//input[@type='checkbox'])[7]";

	public static final String Rules_Broken_popupQuote = "(//h4[contains(text(),'Rules Broken')])[2]";
	public static final String RuleName_NewQuote = "//div[@class='modal-body modal-body-fx']//td[contains(@data-bind,'text: RuleName')]";
	public static final String Rules_Broken_popupQuoteOK = "//h4[contains(text(),'Rules Broken')]/../following-sibling::div[2]/button[contains(text(),'OK')]";
	public static final String Ok_Button_Popup = "//*[@id='ruleMessageForInnroad']/div[3]/button[1]";
	public static final String RuleName_TapeChart = "(//td[contains(@data-bind,'text: RuleName')])[2]";
	public static final String Click_Depart_DatePicker = "//input[@placeholder='Depart']/following-sibling::div/a/i";
	public static final String Click_RatePlan = "//select[contains(@data-bind,'RateQuoteRatePlanList')]";
	public static final String NewRes_RoomAssign_Cancel = "(//body[@class='ng-transform modal-open']//button[@data-bind='click: CancelClick'])[4]";

	public static final String Reservation_Rule_Button = "//button[@class='rules_broken_icon floatN marLRAuto popovers']";
	public static final String Res_clickCancel = "(//button[contains(@data-bind,'click: CancelClick')])[5]";

	// Rate
	public static final String Select_All_Seasons = "//select[@id='lstSeasons']";
	public static final String click_One_Season = "//input[@id='btnPickOne']";

	public static final String Select_Ratepaln_DropDown = "//div[@class='inputGroup ng-select-hint opt-2']";

	public static final String isDirtyRoomPopup_Confirm = "(//button[text()='Confirm'])[2]";
	public static final String List_LineItemCategory = "//td[@class='lineitem-category']//span";

	public static final String VerifyChangePolicyPopup = "//h4[text()='You�ve changed the reservation details.']";
	public static final String policyComparisionPopUp_Btn = "//h4[text()='You�ve changed the reservation details.']//..//..//..//div//button[text()='Continue']";

	// InHose Search operation on reservation Page
	public static final String InHouse_Box = "//ul[@data-bind='foreach: $parent.PredefinedQueries, visible: $parent.ShowStatisticsSection()']/li[@value='1'] ";
	public static final String Records_Found = "//span[@id='bpjscontainer_21']//following-sibling::span[@data-bind='text:totalNumberOfRows']";
	public static final String Coloumn_Headers = "//span[@id='bpjscontainer_21']//following-sibling::table/thead/tr";
	public static final String RecordsInHoues = "//ul/li[@value='1']/div[@class='ng-mainFilter']/span[@class='ng-statsValue']";
	public static final String Pagenation = "//div[@class='col-sm-12 text-center']/div/ul";
	public static final String ItemesPerpage = "//div[@class='col-sm-12 text-right innrForm']/select";
	public static final String Check_Box_Option = "//input[@type='checkbox' and @data-bind='enable: EnableSelectAll, checked: SelectAllReservations, visible: !IsInsideReservationPicker()']";
	public static final String InHouse_Reservation = "//a[@class='ng-exportIcon ng-marRight-10 ng-marTop-10']";
	public static final String Report_Print_PopUp = "//div[@class='btn-text' and contains(text(),'Print')]";
	public static final String InHouse_BulkAction = "//button[@data-id='bulkAction']";
	public static final String Select_check_box = "//span[@id=\"bpjscontainer_21\"]/div/div/table/thead/tr/th/div[@class='ng-leftBorder']/label";
	public static final String Multiple_CheckBox = "//div[@class='checker']/input[@type='checkbox']";

	// All arivals operation
	public static final String InHouseRes = "//span[contains(@data-bind,'text: valueText')]/../span[contains(text(),'In House')]/../span[1]";
	public static final String AllArrivals_Box = "//ul/li[@value='2']";
	public static final String Records_AllArrivals_Box = "//span[contains(@data-bind,'text: valueText')]/../span[contains(text(),'All Arrivals')]/../span[1]";
	public static final String countsof_records = "//span[@id='bpjscontainer_21']//following-sibling::span[@data-bind='text:totalNumberOfRows']";
	public static final String AllArrivals_DropDown_Option = "//ul/li[@class='all-arrivals active predefinedQueryOption']//a[@data-toggle='dropdown']";
	public static final String pending_Arrivals = "//ul[@class='dropdown-menu'and @data-bind='foreach: AdditionalOptions']/li[@value='3']";
	public static final String pending_Records_count = "//span[@id='bpjscontainer_21']//following-sibling::span[@data-bind='text:totalNumberOfRows']";
	public static final String Actual_Pending_Records = "//span[.='Pending Arrivals']//preceding-sibling::span";
	public static final String Arrivals_and_Departure = "//li['value:10' and @class='hidden-xs hidden-sm']";
	public static final String Actuval_Records_Arrivals_and_Departure = "//span[.='Arrivals & Departure']//preceding-sibling::span";
	public static final String Grid_Arrivals_and_Departure_Records = "//span[@id='bpjscontainer_21']//following-sibling::div[@class='countOFPropertyList']/span";
	public static final String pending_Dropdown = "//span[@id='bpjscontainer_22']//following-sibling::li[@class='pending-arrivals active predefinedQueryOption']/div/a/span";
	public static final String All_Departures = "//ul/li[@value='7']";
	public static final String All_Departures_Dropdown = "//ul/li[@value='7']//following-sibling::div/a[@data-toggle='dropdown']";
	public static final String All_Arrival_Departures = "//ul/li[@value='6']";
	public static final String Unassingned = "//span[.='Unassigned']//preceding-sibling::span";
	public static final String newreservation = "//span[.='New Reservations']//preceding-sibling::span";
	public static final String Pending_Departures = "//ul/li[@value='5']";
	public static final String Pending_departure_Dropdown = "//li[@value='5']//a[@data-toggle='dropdown']";
	public static final String Actuval_All_Departures_Records = "//span[.='All Departures']//preceding-sibling::span";
	public static final String Actuval_Pending_Departures_Records = "//span[.='Pending Departures']//preceding-sibling::span";
	public static final String Actuval_AllArrivals_departures = "(//span[.='Arrivals & Departure']//preceding-sibling::span)[2]";

	// All departures on Reservation page

	public static final String All_Departures_Records = "//span[.='All Departures']//preceding-sibling::span";
	public static final String visible_Records_All_Departures = "//span[@id='bpjscontainer_21']//following-sibling::span[@data-bind='text:totalNumberOfRows']";
	public static final String All_departures_dropdown = "//li[@value='7']//following-sibling::a";
	public static final String Pendending_Departures = "//li[@value='5']";
	public static final String Pendending_Departures_Records = "//span[.='Pending Departures']//preceding-sibling::span";
	public static final String visible_Records_Pending_departures = "//span[@id='bpjscontainer_21']//following-sibling::span[@data-bind='text:totalNumberOfRows']";
	public static final String All_Arrivals_And_departures = "//li[@value='6']";
	public static final String Pending_Departures_Dropdown = "//li[@value='5']//following-sibling::a";
	public static final String All_Arrivals_And_departures_Records = "(//span[.='Arrivals & Departure']//preceding-sibling::span)[2]";
	public static final String visible_Records_All_Arrivals_And_departures = "//span[@id='bpjscontainer_21']//following-sibling::span[@data-bind='text:totalNumberOfRows']";
	public static final String calender_dropdown = "//input[@class='ng-calendarIcon']//following-sibling::a/i[@class='fa fa-calendar']";
	public static final String AuthorizationImg = "(//img[@src='./Folio_Images/7.gif'])[1]";

	public static final String TaskPopup_Title = "//h1[@class='gs-title']";
	public static final String TaskPopup_Details = "(//span[@class='gs-disc'])[1]";
	public static final String TaskPopup_CloseButton = "//div[@id='editTaskModal']//button[@class='close closeBtn']";
	public static final String ToastCloseButton = "//button[@class='toast-close-button']";
	public static final String SelectAllTask = "//th[contains(text(),'Task Type')]//..//th//span";
	public static final String DeleteTask_Button = "//a[contains(text(),'Delete Task')]";
	public static final String TaskDelete_ProcessButton = "(//button[text()='Proceed'])[1]";
	public static final String NoTaskFound = "//td[text()='No tasks found for the selected criteria and property.']";

	public static final String SelectSeason_Rate = "//select[@id='lstSeasons']";

	public static final String RoomStatus_AllStatNumber = "//span[@class='gs-statsValue color-blue']";
	public static final String RoomStatus_SearchField = "//div[@class='input-group-btn gs-width50P']//input";
	public static final String RoomStatus_RoomCard = "//room-card/div";
	public static final String RoomStatus_RoomTileDropDownButton = "//div[@class='grid-content']//div[contains(@class,'dropdown')]/a/i";
	public static final String GS_DateRangeLabel = "//label[@class='dateRangeLabel']";

	// Reservation
	public static final String Reservation_Menu = "//a[contains(text(),'Reservations')]";

	public static final String roomClassHelpIcon = "//i[@data-bind='click: showHelpPage']";
	public static final String roomClassCollapseIcon = "//div[@id='roomClassSearch']//a[@class='collapse']";
	public static final String NewReservationTab = "//span[text()='New Reser...']";

	// Group Block
	public static final String GroupAdults = "//input[@id='MainContent_txtNumberOfAdults']";
	public static final String AddGroupAdults = "//input[@id='MainContent_btnAdultsAdd']";
	public static final String SubtractGroupAdults = "//input[@id='MainContent_btnAdultsRemove']";
	public static final String GroupChilds = "//input[@id='MainContent_txtNumberOfChildren']";
	public static final String AddGroupChilds = "//input[@id='MainContent_btnChildrenAdd']";
	public static final String SubtractGroupChilds = "//input[@id='MainContent_btnChildrenRemove']";
	public static final String GroupRatePlan = "//select[@id='MainContent_drpRatePlan']";

	// Rates Grid Rate Popup
	public static final String RatePopup = "//div[@class ='popover-inner']";
	public static final String RatePopup_RoomRate = "//div[@class='rulesPopoverContent']//input[@name = 'rateVal']";
	public static final String RatePopup_ExtraAdult = "//div[@class='rulesPopoverContent']//input[@name = 'extraAdult']";
	public static final String RatePopup_ExtraChild = "//div[@class='rulesPopoverContent']//input[@name = 'extraChild']";

	// Groups
	public static final String Group_FolioOptions = "//*[@id='MainContent_lnkOpenChargeRouting']";
	public static final String Group_FolioOptions_ChargeRouting = "//*[@id='MainContent_Folio1_drpChargeRoutingItem']";
	public static final String Group_RoomBlock_GroupInfo_ExpectedRevenue = "//*[@id='MainContent_ucGroupSummary_lblGroupInfoExpectedRevenue']";

	public static final String GroupPreviewFolio_AddButton = "//*[@id='MainContent_ucPreviewFolio_btnAdd']";
	public static final String GroupPreviewFolio_TaxAndServiceCharges = "//*[@id='MainContent_ucPreviewFolio_lblResTaxSurcharge']";
	public static final String GroupPreviewFolio_TotalCharges = "//*[@id='MainContent_ucPreviewFolio_lblResTotalCharges']";
	public static final String GroupPreviewFolio_CommitButton = "//*[@id='MainContent_ucPreviewFolio_btnDone']";

	public static final String GroupBillInfoCopyToPickUpReservation = "//*[@id='MainContent_ckCopyBillingInfoToReservation']";

	public static final String GroupBilling_AccountNo = "//*[@id='MainContent_txtBilling_AccountNumber']";
	public static final String GroupBilling_ExpiryDate = "//*[@id='MainContent_txtBilling_CreditCardExpirationDate']";
	public static final String GroupBilling_BillingNotes = "//*[@id='MainContent_txtCvvCode']";
	public static final String RoomingListBillingInfo_FirstName = "//*[@id='txtBilling_FirstName']";
	public static final String RoomingListBillingInfo_LastName = "//*[@id='txtBilling_LastName']";
	public static final String RoomingListBillingInfo_Address1 = "//*[@id='txtBilling_address1']";
	public static final String RoomingListBillingInfo_PhoneNo = "//*[@id='txtBilling_phoneNumber']";
	public static final String RoomingListBilling_AccountNo = "//*[@id='txtBilling_AccountNumber']";
	public static final String RoomingListBilling_ExpiryDate = "//*[@id='txtBilling_CreditCardExpirationDate']";
	public static final String RoomingListBilling_BillingNotes = "//*[@id='txtCvvCode']";
	public static final String RoomingListBillingInfo_PostalCode = "//*[@id='txtBilling_postalCode']";

	public static final String CheckVaryRoomsByDate = "//*[@id='divSummary']//input[contains(@data-bind,'VaryRoomsByDate')]";

	public static final String RoomingListPickupSummary_BlockName = "//*[@id='ucBlockSummary1_lblBlockInfoBlockName']";
	public static final String RoomingListPickupSummary_ArivalDate = "//*[@id='ucBlockSummary1_lblArrive']";
	public static final String RoomingListPickupSummary_DepartDate = "//*[@id='ucBlockSummary1_lblDepart']";
	public static final String RoomingListPickupSummary_Status = "//*[@id='ucBlockSummary1_lblReservationStatus']";
	public static final String RoomingListPickupSummary_PickUp = "//*[@id='ucBlockSummary1_lblBlockInfoPickedUpPercent']";

	public static final String Group_Folio_BillingInfo_PostalCode = "//*[@id='folioBillingInfo1_txtBilling_postalCode']";
	public static final String Group_Folio_BillingInfo_AccountNumber = "//*[@id='folioBillingInfo1_txtBilling_AccountNumber']";
	public static final String Group_Folio_BillingInfo_ExpiryDate = "//*[@id='folioBillingInfo1_txtBilling_CreditCardExpirationDate']";

	public static final String GroupFolio_PaymentDetailPopup_CardInfoButton = "//*[@id='btnCardInfo']";
	public static final String GroupFolio_PaymentDetail_EncriptDecriptButton = "//*[@id='btnEncryptDecrypt']";
	public static final String GroupFolio_PaymentDetail_NameOnCard = "//*[@id='txtNameOnCard']";
	public static final String GroupFolio_PaymentDetail_CardNO = "//*[@id='txtCreditCardNo']";
	public static final String GroupFolio_PaymentDetail_ExpiryDate = "//*[@id='txtExpdate']";
	public static final String GroupFolio_PaymentDetail_City = "//*[@id='txtCity']";
	public static final String GroupFolio_PaymentDetail_State = "//*[@id='txtState']";
	public static final String GroupFolio_PaymentDetail_PostalCode = "//*[@id='txtPostalCode']";
	public static final String GroupFolio_PaymentDetail_ButtonOK = "//*[@id='btnOK']";
	public static final String GroupFolio_BillingInfo_SetMainPaymentMethod_Check = "//*[@id='folioBillingInfo1_chkSetAsMainPayment']";
	public static final String Group_Account = "//input[@id='MainContent_btnAccount']";
	public static final String GroupFolio_PaymentDetail_CVVCode = "//*[@id='txtCVVCode']";
	public static final String GroupFolio_PaymentDetail_TransactionTab = "//*[@id='btnTransaction']";
	public static final String GroupFolio_PaymentDetail_CancelButton = "//*[@id='btnCancelTranPay']";
	public static final String Group_PreviewFolio_Add = "//input[@id='MainContent_ucPreviewFolio_btnAdd']";
	public static final String RoomBlock_GroupName = "//span[@id='MainContent_ucGroupSummary_lblGroupInfoGroupName']";
	public static final String RoomBlock_Account = "//span[@id='MainContent_ucGroupSummary_lblGroupInfoAccountNo']";
	public static final String RoomBlock_Revenue = "//span[@id='MainContent_ucGroupSummary_lblGroupInfoExpectedRevenue']";
	public static final String Block_Dates = "(//span[@class='prop_info'])[2]";
	public static final String Block_name = "//span[@class='main_title']";
	public static final String RoomBlock_releaseDate = "(//span[@class='prop_info'])[3]";
	public static final String Block_Status = "(//span[@class='prop_info'])[4]";
	public static final String Blocked_Rooms_count = "//span[contains(text(),'Rooms Blocked')]";
	public static final String Total_Room_Ninghts = "(//span[@class='prop_rl'])[1]";
	public static final String Expected_Revenue = "(//span[@class='prop_rl'])[3]";
	public static final String RelseDate_PopUp = "//span[@id='ui-id-1']";
	public static final String Account_Detail_Expected_Revenue = "//span[@id='MainContent_ucGroupSummary_lblGroupInfoExpectedRevenue']";
	public static final String Account_Detail_QRG = "//span[@id='MainContent_ucGroupSummary_lblGroupInfoAverageQGR']";

	public static final String Account_PickedUp_Revenue = "//span[@id='MainContent_ucGroupSummary_lblGroupInfoPickedUpRevenue']";
	public static final String Account_PickedUp_Percentage = "//span[@id='MainContent_ucGroupSummary_lblGroupInfoPickedUpPercent']";
	public static final String Room_Block_PickedUp_Revenue = "(//span[@class='prop_rl'])[4]";
	public static final String Room_Block_PickedUp_percentage = "(//span[@class='prop_info'])[6]";
	public static final String LineItem_act_Amount = "//span[@id='MainContent_ucPreviewFolio_dgPreviewFolioLineItems_lblAmount_0']";
	public static final String LineItem_add_Amount = "//span[@id='MainContent_ucPreviewFolio_dgPreviewFolioLineItems_lblAmount_1']";
	public static final String LineItem_Room_charges = "//span[@id='MainContent_ucPreviewFolio_lblResRoomCharges']";
	public static final String LineItem_Incidentals = "//span[@id='MainContent_ucPreviewFolio_lblResIncidentals']";
	public static final String LineItem_total_charges = "//span[@id='MainContent_ucPreviewFolio_lblResTotalCharges']";
	public static final String Block_Details_PickedUP_Revenue = "//span[@id='MainContent_ucBlockSummary_lblBlockInfoPickedUpRevenue']";
	public static final String Block_Details_PickedUP_percentage = "//span[@id='MainContent_ucBlockSummary_lblBlockInfoPickedUpPercent']";
	public static final String LineItem_ArriveDate = "//span[@id='MainContent_ucPreviewFolio_dgPreviewFolioLineItems_lbldateeffective_0']";
	public static final String Group_PreScheduler = "//input[@id='MainContent_btnPreAdd']";
	public static final String Group_PostScheduler = "//input[@id='MainContent_btnPostAdd']";
	public static final String Group_Block_Edit = "//input[@id='btnEditBlock']";
	public static final String Group_PreviewFolio = "//a[@id='MainContent_lnkPreviewFolio']";
	public static final String GroupAccountDetails = "//span[@id='MainContent_lblTitle']";
	public static final String SelectRoom_context = "//span[@id='Label1']";
	public static final String Arrive_Date = "//input[@id='MainContent_txtDateStart']";
	public static final String Departure_Date = "//input[@id='MainContent_txtDateEnd']";
	public static final String Room_QRg_Amount = "//span[@id='summaryRoomQrg']";
	public static final String Total_Amount = "//span[@id='summaryTotalAmount']";
	public static final String Edit_block_details = "//font[@class='TitleText']";
	public static final String Edit_block_Name = "//span[@id='MainContent_ucBlockSummary_lblBlockInfoBlockName']";
	public static final String Edit_block_Arrive_date = "//span[@id='MainContent_ucBlockSummary_lblArrive']";
	public static final String Edit_block_Depart = "//span[@id='MainContent_ucBlockSummary_lblDepart']";
	public static final String Edit_block_QGR_Value = "//span[@id='MainContent_ucBlockSummary_lblBlockInfoAverageQGR']";
	public static final String Edit_block_Expected_Revenue = "//span[@id='MainContent_ucBlockSummary_lblBlockInfoExpectedRevenue']";
	public static final String Edit_block_RoomNinght = "//span[@id='summaryRoomNights']";
	public static final String Edit_block_RoomBlocks = "//span[@id='summaryRoomBlocks']";
	public static final String Edit_block_ActuvalQTG_Value = "//span[@id='summaryRoomQrg']";
	public static final String Edit_block_total_Amount = "//span[@id='summaryTotalAmount']";
	public static final String Room_Block_ArriveDate = "//input[@id='MainContent_txtDateStart']";
	public static final String Room_Block_Depart = "//input[@id='MainContent_txtDateEnd']";
	public static final String Room_Block_PromoCod = "//input[@id='MainContent_txtPromocode']";
	public static final String Room_Block_Popup = "//button[@id='btnContinue']";
	public static final String GroupsEndingBalance = "MainContent_Folio1_fSummary1_lblAccEndBalance";
	public static final String Group_AdvGroup_SelectProperty = "//select[@id='MainContent_drpPropertyList']";
	public static final String Group_AdvGroup_PaymentDetail_SelectProperty = "//select[@id='drpPropertyList']";
	public static final String AdvGroup_Notes = "//textarea[@id='txtNotes']";
	public static final String GroupSwipeIcon = "//*[@id='btnSwipe']";
	public static final String GroupSwipeTextTrackData = "//*[@id='txtTrackData']";
	public static final String GroupSwipeSubmitButton = "//span[contains(text(),'Submit')]";
	public static final String Select_Depart_Date_Groups = "//*[@id='trDepart']/td[2]/img";
	public static final String Select_CategoryItemDetail = "//*[@id='drpCategory']";
	public static final String DescriptionItemDetail = "//*[@id='txtDescription']";
	public static final String AmountItemDetail = "//*[@id='txtAmount']";
	public static final String AddItemDetail = "//*[@id='btnAdd']";
	public static final String BtnEditBlockName = "//*[@id='MainContent_ucBlockSummary_btnEditBlockName']";
	public static final String LblEditBlockName = "//*[@id='MainContent_ucBlockSummary_lblBlockInfoBlockName']";
	public static final String TxtEditBlockName = "//*[@id='MainContent_txtEditBlockName']";
	public static final String Select_Group_paymethod = "//*[@id='drpPaymentMethod']";
	public static final String Click_Group_Card_info = "//*[@id='btnCardInfo']";
	public static final String Enter_Group_Card_Name = "//*[@id='txtNameOnCard']";
	public static final String Enter_CC_Group_Number = "//*[@id='txtCreditCardNo']";
	public static final String Enter_ExpiryDate_Group = "//*[@id='txtExpdate']";
	public static final String Enter_CCV_Group = "//*[@id='txtCVVCode']";
	public static final String Click_Ok_Group = "//*[@id='btnOK']";
	public static final String Click_Process_Group = "//*[@id='btnAgingProcess']";

	public static final String Enter_Group_Amount = "//*[@id='txtAmount']";

	public static final String PaymentPopUp_Group = "//*[@id=\'paymentDetails_DIV\']";
	public static final String PaymentDetailPopUp_Group = "//*[@id='paymentDetails_DIV']";
	public static final String Click_Continue_Group = "//*[@id='btnSaveTranPay']";
	public static final String Click_Transition_Group = "//*[@id='btnTransaction']";
	public static final String Click_Outstanding_Group = "//*[@id='btnPayments']";

	// Groups
	public static final String FolioOptionCheckInPolicy = "//*[@id='MainContent_Folio1_drpAccPolicyTypeCheckIn']";
	public static final String FolioOptionCancellationPolicy = "//*[@id='MainContent_Folio1_txtSources']";
	public static final String FolioOptionCancellationPolicyListSource = "//*[@id='MainContent_Folio1_lstSources']";
	public static final String FolioOptionCancellationPolicyPickBtn = "//*[@id='btnPickOne']";
	public static final String FolioOptionCancellationPolicyDoneBtn = "//*[@id='divSourcePicker']//input[@value='Done']";
	public static final String FolioOptionDepositPolicy = "//*[@id='MainContent_Folio1_drpAccPolicyTypeDeposit']";
	public static final String Check_TaxExempt = "//*[@id='MainContent_chkTaxExempt']";
	public static final String Txt_TaxExempt = "//*[@id='MainContent_txtTaxExemptID']";
	public static final String btnNewGroup = "//input[@id='MainContent_btnNew']";

	public static final String DisplayVoidItemButton = "//input[@id='MainContent_Folio1_chkResDispVoidItems']";
	public static final String Grp_GCPickerSelectButton = "//input[@id='btnSelect']";
	public static final String Grp_GCPickerSearchField = "//input[@id='txtGCNo']";
	public static final String Grp_GCPickerGoButton = "//input[@id='btnGoSearch']";
	public static final String Grp_GCPickerCancelButton = "//input[@id='btnCancel']";
	public static final String Grp_GCPickerNoRecordMeet = "//span[@id='lblErrorMessage']";
	public static final String Grp_AmountSold = "//label[.='Amount Sold: ']//following-sibling::span[@class='pamt']//span";
	public static final String Grp_RedeemedAmount = "//label[.='Redeemed: ']//following-sibling::span[@class='pamt']//span";
	public static final String Grp_Balance = "//label[.='Balance: ']//following-sibling::span[@class='pamt']//span";
	public static final String Grp_GiftCertificatPicker = "//span[text()='Gift Certificate Picker']";
	public static final String Grp_FolioBillingInformationPopUp = "//font[text()='Folio Billing Information']";
	public static final String Grp_FolioBillingInfoFirstName = "//input[@id='folioBillingInfo1_txtBilling_FirstName']";
	public static final String Grp_FolioBillingInfoLastName = "//input[@id='folioBillingInfo1_txtBilling_LastName']";
	public static final String Grp_FolioBillingInfoPaymentMethod = "//select[@id='folioBillingInfo1_drpBilling_TypeID']";
	public static final String Grp_FolioBillingInfoSaveButton = "//input[@id='btnSave']";
	public static final String GroupPayment_Info_Textarea = "//textarea[@id='txtPayDetails']";
	public static final String Grp_FolioBillingInfoCancelButton = "//input[@id='btnCancel']";
	public static final String Select_Rooms_Number = "//div[@id='contenttableAssignRoomsGrid']/div[2]/div[8]/div";
	public static final String Select_Rooms_Save_Button = "//input[@id='btnSave']";
	public static final String Select_Rooms_done_Button = "//input[@id='btnAssign']";
	public static final String Select_Rooms_Book_icon = "(//div[@class='book'])[1]";
	public static final String Group_select_Rooms = "//input[@id='btnAssignrooms']";
	public static final String Select_Rooms_Go = "//input[@id='btnAssignRoomsGo']";
	public static final String Verify_Rooms_Number = "//div[@class='AssignedRooms']";
	public static final String Remove_Selected_Room = "//div[@class='unassign']";

	public static final String Group_PreSchedulerMinus = "//input[@id='MainContent_btnPreRemove']";
	public static final String Group_PostSchedulerMinus = "//input[@id='MainContent_btnPostRemove']";

	public static final String Group_Edit_Block_Save = "(//table[@id='Table2']/tbody/tr/td)[1]";// input[@id='MainContent_btnSave']";
	public static final String Group_template_Save = "//table[@id='Table2']/tbody/tr/td[1]";// input[@id='MainContent_btnSave']"

	public static final String Group_Block_Save_Message = "//div[@id='msgRoomBlocksNotMatch']/div";

	public static final String Group_Edit_Block_Done = "//input[@id='MainContent_btnDone']";// table[@id='Table2']/tbody/tr/td[2]/input";

	public static final String VerifyRoomClass_Number = "//span[contains(@data-bind,'text: StatusId')]";

	public static final String EnterClientName = "//input[@name='ctl00$MainContent$txtClientName']";
	public static final String btnSave_ClientInfo = "//input[@name='ctl00$MainContent$btnSave']";
	public static final String btnDone_ClientInfo = "//input[@name='ctl00$MainContent$btnDone']";

	public static final String block_Details = "//span[@id='ui-id-1']";
	public static final String Rate_Quote_Tittle = "//font[@class='TitleText']";
	public static final String Rooming_list_Billing_FristName = "//input[@id='txtBilling_FirstName']";
	public static final String Rooming_list_Billing_lastName = "//input[@id='txtBilling_LastName']";
	public static final String Rooming_list_Billing_Phone_Number = "//input[@id='txtBilling_phoneNumber']";
	public static final String Rooming_list_Billing_Address = "//input[@id='txtBilling_address1']";
	public static final String Rooming_list_Billing_city = "//input[@id='txtBilling_city']";
	public static final String Rooming_list_Billing_state = "//select[@id='drpBilling_territoryID']";
	public static final String Rooming_list_Billing_postal_code = "//input[@id='txtBilling_postalCode']";
	public static final String Rooming_list_Billing_country = "//select[@id='drpBilling_countryID']";
	public static final String Rooming_list_Block_Name = "//span[@id='ucBlockSummary_lblBlockInfoBlockName']";
	public static final String Rooming_list_Arrive_Date = "//span[@id='ucBlockSummary_lblArrive']";
	public static final String Rooming_list_Depart_Date = "//span[@id='ucBlockSummary_lblDepart']";
	public static final String Rooming_list_QRG_value = "//span[@id='ucBlockSummary_lblBlockInfoAverageQGR']";
	public static final String Rooming_list_Statu = "//span[@id='ucBlockSummary_lblReservationStatus']";
	public static final String Rooming_list_Expected_Revenue = "//span[@id='ucBlockSummary_lblBlockInfoExpectedRevenue']";
	public static final String Rooming_list_PickedUp_Revenue = "//span[@id='ucBlockSummary_lblBlockInfoPickedUpRevenue']";
	public static final String Rooming_list_PickedUp_percentage = "//span[@id='ucBlockSummary_lblBlockInfoPickedUpPercent']";
	public static final String Rooming_List_tittle = "//span[@id='lblTitle']";
	public static final String Rooming_List_grid_Arrive_date = "//input[@id='dgRoomingList_txtdateArrival_0']";
	public static final String Rooming_List_grid_deprt_date = "//input[@id='dgRoomingList_txtdateDeparture_0']";
	public static final String Rooming_List_grid_chkHideDates = "//input[@id='chkHideDates']";
	public static final String PickUp_Summary_Block_Name = "//span[@id='ucBlockSummary1_lblBlockInfoBlockName']";
	public static final String PickUp_Summary_Arrive_Date = "//span[@id='ucBlockSummary1_lblArrive']";
	public static final String PickUp_Summary_Depart_Date = "//span[@id='ucBlockSummary1_lblDepart']";
	public static final String PickUp_Summary_QGR_Value = "//span[@id='ucBlockSummary1_lblBlockInfoAverageQGR']";
	public static final String PickUp_Summary_Status = "//span[@id='ucBlockSummary1_lblReservationStatus']";
	public static final String PickUp_Summary_Expected_Revenue = "//span[@id='ucBlockSummary1_lblBlockInfoExpectedRevenue']";
	public static final String PickUp_Summary_PickedUp_Revenue = "//span[@id='ucBlockSummary1_lblBlockInfoPickedUpRevenue']";
	public static final String PickUp_Summary_PickedUp_percentage = "//span[@id='ucBlockSummary1_lblBlockInfoPickedUpPercent']";
	public static final String PickUp_Summary_Reservation_Number = "//*[@id='dgRoomingList']/tbody/tr[2]/td[1]";
	public static final String PickUp_Summary_Grid_Arrive_date = "//*[@id='dgRoomingList']/tbody/tr[2]/td[3]";
	public static final String PickUp_Summary_Grid_Depart_date = "//*[@id='dgRoomingList']/tbody/tr[2]/td[4]";
	public static final String ReservationPage_Promocode = "(//p[contains(@data-bind,'PromoCode')])[3]";
	public static final String ReservationPage_FristName = "(//input[@placeholder='First Name'])[1]";
	public static final String ReservationPage_LastName = "(//input[@placeholder='Last Name'])[1]";
	public static final String ReservationPage_checkIn = "//button[contains(text(),'Check In')]";
	public static final String Reservation_ACCNO = "//a[contains(@data-bind,'Accountname')]";
	public static final String Reservation_start_date = "(//p[contains(@data-bind,'DateStart')])[1]";
	public static final String Reservation_end_date = "(//p[contains(@data-bind,'DateEnd')])[1]";
	public static final String Reservation_Folio_Balence = "//span[@data-bind='showInnroadCurrency: { value: ComputedSummary().balance, ShouldShowNegativeAsBracket: true }']";
	public static final String Reservation_Total = "//p[@data-bind='showInnroadCurrency: { value: ComputedSummary().totalCharges, ShouldShowNegativeAsBracket: false }']";
	public static final String Reservation_LineItem_effectivedate = "(//span[contains(@data-bind,'EffectiveDate')])[1]";
	public static final String Reservation_Room_Charges = "(//span[@data-bind='showInnroadCurrency: { value: (accounting.toFixed($data.amount, 2)), ShouldShowNegativeAsBracket: true }'])[1]";
	public static final String Reservation_incidentals = "(//span[@data-bind='showInnroadCurrency: { value: (accounting.toFixed($data.amount, 2)), ShouldShowNegativeAsBracket: true }'])[2]";
	public static final String Reservation_balanc = "(//span[@data-bind='showInnroadCurrency: { value: (accounting.toFixed($data.amount, 2)), ShouldShowNegativeAsBracket: true }'])[6]";
	public static final String Reservation_folio_Tab = "//a[@data-bind='click: switchToFoliosTab']";
	public static final String Reservation_folio_ACC = "//a[@data-bind=' click: OpenAccountDetail, text: Accountname, visible: CompanyId() > 0 ']";
	public static final String Rate_Quote_Room_Ninghts = "//span[@id='MainContent_lblTotalRooms']";
	public static final String Summary_Room_Ninght = "//span[@id='summaryRoomNights']";
	public static final String Summary_Room_Blocks = "//span[@id='summaryRoomBlocks']";
	public static final String GroupBlockRoomQGR = "//*[@id='summaryRoomQrg']";
	public static final String GroupBlockRoomTotal = "//*[@id='summaryTotalAmount']";

	public static final String GroupInfoAverageQGR = "//*[@id='MainContent_ucGroupSummary_lblGroupInfoAverageQGR']";

	public static final String GroupPreShoulder_Add = "//*[@id='MainContent_btnPreAdd']";
	public static final String GroupPreShoulder_Remove = "//*[@id='MainContent_btnPreRemove']";
	public static final String GroupPreShoulder_Text = "//*[@id='MainContent_PreShoulderquantity']";

	public static final String GroupPostShoulder_Add = "//*[@id='MainContent_btnPostAdd']";
	public static final String GroupPostShoulder_Remove = "//*[@id='MainContent_btnPostRemove']";
	public static final String GroupPostShoulder_Text = "//*[@id='MainContent_PostShoulderquantity']";
	public static final String Group_Folio_PickBillingInfo_Button = "//*[@id='MainContent_Folio1_btnPickBillingInfo']";
	public static final String Group_Folio_BillingInfo_FirstName = "//*[@id='folioBillingInfo1_txtBilling_FirstName']";
	public static final String Group_Folio_BillingInfo_LastName = "//*[@id='folioBillingInfo1_txtBilling_LastName']";
	public static final String Group_Folio_BillingInfo_City = "//*[@id='folioBillingInfo1_txtBilling_city']";
	public static final String Group_Folio_BillingInfo_State = "//*[@id='folioBillingInfo1_drpBilling_territoryID']";
	public static final String Group_Folio_BillingInfo_Country = "//*[@id='folioBillingInfo1_drpBilling_countryID']";
	public static final String Group_Folio_BillingInfo_PaymentMethod = "//*[@id='folioBillingInfo1_drpBilling_TypeID']";
	public static final String Group_Folio_BillingInfo_SaveButton = "//*[@id='btnSave']";

	// Continue
	public static final String Group_Folio_Close = "//*[@id='btnClose']";

	// Void Button
	public static final String Group_Folio_Void = "//input[@id='MainContent_Folio1_btnVoid']";

	// Void Notes Input
	public static final String Group_Folio_Void_Notes = "//*[@id='txtNotes']";

	// Void Save Button
	public static final String Group_Folio_Void_Save = "//*[@id='btnSave']";

	// Group Done Button
	public static final String Group_Done = "//*[@id='MainContent_btnDone']";

	public static final String selectPoliciesPerPage = "//select[contains(@data-bind,'options: recordsPerPage')]";
	public static final String secondaryRatesMenuItem = "ucNavSecondary_rptrNavMain_lbtnNavMainItem_2";
	public static final String CloseuUsavedReservationAlert = "//h4[text()='You have Unsaved Data!']//..//..//button[text()='Yes']";
	public static final String bulkCheckoutPopup = "//h4[.='Bulk Checkout']";

	// Reservation
	public static final String ReservationTotalBalance = "//label[text()='Total: ']//..//p";
	public static final String selectResFromDropDown = "//a[@id='ui-id-7']";
	public static final String btnPackage = "//input[@id='MainContent_btnPackages']";
	public static final String selectAddOn = "//input[@id='MainContent_rdblPackageAvail_1']";
	public static final String selectAddOnLabel = "(//label[contains(text(),'Add-On')])[1]";
	public static final String alwaysAvailableRate = "//input[@id='MainContent_chkAlwaysAvailable']";
	// rates name
	public static final String SecondaryRatesMenuItem = "ucNavSecondary_rptrNavMain_lbtnNavMainItem_2";
	public static final String rateDescription = "//textarea[@id='MainContent_txtRateDescription']";
	public static final String enterPolicyName = "//textarea[@id='MainContent_txtRatePolicy']";

	public static final String Reservation_Menu_FromTax = "//img[@class='nav-reservation nav_icon']";

	// Black Room
	public static final String rateGridAvilability = "//a[@id='rates-and-availability-tabs-tab-AVAILABILITY_VIEW']";
	public static final String clickONInncenter = "((//div[contains(text(),'innCenter')])/../../div[@class='col AvailabilityDateCell WeekdayAvailabilityCellStyle NoBlackedStatus']/span)[1]";
	public static final String bulkUpdateRoomClass = "//div[contains(text(),'Select room class(es)')]";
	public static final String blackOutTab = "//span[contains(text(),'Blackout')]";
	public static final String upadteButton = "(//button[contains(text(),'Update')])[2]";
	public static final String avilableTab = "//span[contains(text(),'Available')]";
	public static final String linkRateGrid = "//a[@id='MainContent_rptrMenu_lbtnMenuItem_0']";
	public static final String linkRateGRIDTwo = "//a[@id='ucNavSecondary_rptrNavMain_lbtnNavMainItem_0']";
	public static final String bulkUpdateButton = "(//button[@id='dropdown-basic-0'])[2]";
	public static final String bulkUpdateAvilability = "(//ul/li/a[contains(text(),'Availability')])[1]";
	public static final String bulkUpdateText = "//div[contains(text(),'Bulk update - Availability')]";
	public static final String blackOutTosterMessage = "//div[contains(@class,'Tostify_toast-body')]";

	public static final String GetTotalIncidentalsAmount = "(//div[text()='Incidentals']/ancestor::div/div/div[starts-with(.,'$')])[2]";
	public static final String TaxItemDetails = "//h4[text()='Tax Item Details']";
	public static final String CancelTaxItemDetails = "//h4[text()='Tax Item Details']/ancestor::div/div/button[text()='Cancel']";
	public static final String TaxItemPlaceholderInFolioDetail = "//input[@placeholder = 'Item Name']";
	public static final String ClickPackageTab = "//input[@name='ctl00$MainContent$btnPackages']";
	public static final String GetTaxFolioItems = "//table[@class='table table-striped table-bordered table-hover popGrdFx']/tbody/tr/td[contains(.,'Tax')]/ancestor::tr/td[contains(.,'$')]";

	// Inventory
	public static final String Rates_Grid_Tab = "//div[contains(@class,'sec_nav_in')]//a[contains(text(),'Rates Grid')]";
	public static final String Spinner = "//div[contains(@class,'sec_nav_in')]//a[contains(text(),'Rates Grid')]";

	public static final String ratesSeasonPopupFrame2 = "//iframe[@id='frmWaitHost']";
	public static final String ratesSeasonPopupFrame1 = "//iframe[@id='dialog-body0']";

	public static final String DailyFlashReportDate = "//input[@id='MainContent_TxtDailyDate']//following-sibling::img";
	public static final String DailyFlashReportTodayDate = "//div[@class='datepicker-days']//th[..='Today']";
	public static final String DailyFlashReportGoButton = "//input[@id='MainContent_btnGenreport']";

	// Reports
	public static final String ReportsDailyFlash = "//a[@data-id='dailyFlashReport.aspx']";
	public static final String ReportsDailyFlashCalender = "//input[@id='MainContent_TxtDailyDate']/following-sibling::img";
	public static final String DailyFlashBreakoutTaxExemptRevCheckBox = "//input[@id='MainContent_chkExempt']";
	public static final String DailyFlashRadioButton = "//input[@id='MainContent_rbtnLstReportType_0']";

	// Advance search
	public static final String StayFromInput = "(//input[@name='daterangepicker_start'])[1]";
	public static final String StayToInput = "(//input[@name='daterangepicker_end'])[1]";
	public static final String AdvanceReservationFirstItemRoomClass = "(//span[contains(@data-bind,'RoomClassName')])[1]";
	public static final String AdvanceReservationResultList = "(//tbody[@data-bind='foreach: ReservationList'])[1]//following-sibling::tr";
	public static final String AdvanceSearchClose = "//button[@data-bind='click: $parent.GoBasicLink']";
	// Netsale
	public static final String NetSalesGroupBy = "MainContent_drpRptType";
	public static final String NetSalesFromDate = "(//input[@id='MainContent_txtDateStart']//following-sibling::img)[1]";
	public static final String NetSalesToDate = "(//input[@id='MainContent_txtDateEnd']//following-sibling::img)[1]";

	public static final String SetUP = "(//*[text()='Setup'])[2]";
	public static final String SecondaryTitle = "//span[@id='MainContent_lblPageTitle']";
	public static final String RoomBlockDetails = "//span[@class='prop_info']";
	public static final String ExpecteddrevenueInRoomBlock = "//span[contains(text(),'Pickup Percentage:')]//span";

	public static final String RoomBlockInBlockDetails = "//span[contains(text(),'Rooms Blocked')]";
	public static final String PickedupInBlockInfo = "//span[contains(text(),'Picked up %:')]";
	public static final String RoomPerNightInBlockDetails = "//span[contains(text(),'Total Room Nights: ')]/span";
	public static final String ReleaseDateInBlockDetails = "//span[contains(text(),'Release Date:')]";
	public static final String PickedupInBlockDetails = "//span[contains(text(),'Pickedup Revenue: ')]/span";
	public static final String SelectRoomsButton = "btnAssignrooms";
	public static final String reservationDetailPopup = "//div[contains(@data-bind,'ReservationDetail.showDiv')]";
	public static final String reservationDetailPopup_Close = "//a[@id='dialog-close0']";
	public static final String SelectRoomHeading = "//span[text()='Select Rooms']";
	public static final String SelectRoomClassInSelectRooms = "drpAssignRoomClass";
	public static final String ClickOnDoneInSelectRooms = "btnAssign";

	// rooming list section
	public static final String RoomingListButton = "btnRoomingList";
	public static final String blockName = "//span[@id='ucBlockSummary_lblBlockInfoBlockName']";
	public static final String arrive = "//span[@id='ucBlockSummary_lblArrive']";
	public static final String depart = "//span[@id='ucBlockSummary_lblDepart']";
	public static final String qgr = "//span[@id='ucBlockSummary_lblBlockInfoAverageQGR']";
	public static final String reservationStatus = "//span[@id='ucBlockSummary_lblReservationStatus']";
	public static final String expectedRevenue = "//span[@id='ucBlockSummary_lblBlockInfoExpectedRevenue']";
	public static final String pickupRevenue = "//span[@id='ucBlockSummary_lblBlockInfoPickedUpRevenue']";
	public static final String pickupPercent = "//span[@id='ucBlockSummary_lblBlockInfoPickedUpPercent']";
	public static final String firstNameInput = "//input[@id='dgRoomingList_txtFirstname_0']";
	public static final String lastNameInput = "//input[@id='dgRoomingList_txtLastName_0']";
	public static final String selectRoomClass = "//*[@id='dgRoomingList_drpRoomClassName_0']";
	public static final String selectRoomNumber = "//*[@id='dgRoomingList_drpRoomNo_0']";
	public static final String amountInput = "//input[@id='dgRoomingList_txtDepositAmount_0']";
	public static final String billingInfoIcon = "//input[@name='dgRoomingList$ctl02$btnBillingInfo']";
	public static final String pickupButton = "//input[@id='btnPickUp']";
	public static final String imgTick = "//img[@id='dgRoomingList_imgComplete_0']";

	public static final String headingBillingINfo = "//td[@id='tdTitle']//font[contains(text(),'Rooming list Billing')]";
	public static final String selectSalulation = "//select[@id='drpSalutationBilling']";
	public static final String selectPaymentMethod = "//select[@id='drpBilling_TypeID']";
	public static final String accountInput = "//input[@id='txtBilling_AccountNumber']";
	public static final String expiryDateInput = "//input[@id='txtBilling_CreditCardExpirationDate']";
	public static final String saveBillingInfoInput = "//input[@id='btnSave']";
	public static final String RoomingListSummary_BlockName = "ucBlockSummary1_lblBlockInfoBlockName";
	public static final String RoomingListSummary_Status = "ucBlockSummary1_lblReservationStatus";
	public static final String RoomingListSummary_ArriveDate = "ucBlockSummary1_lblArrive";
	public static final String RoomingListSummary_DepartDate = "ucBlockSummary1_lblDepart";
	public static final String RoomingListSummary_Qgr = "ucBlockSummary1_lblBlockInfoAverageQGR";
	public static final String RoomingListSummary_ExpectedRevenue = "ucBlockSummary1_lblBlockInfoExpectedRevenue";
	public static final String RoomingListSummary_PickedupRdevenue = "ucBlockSummary1_lblBlockInfoPickedUpRevenue";
	public static final String RoomingListSummary_PikedupPercentage = "ucBlockSummary1_lblBlockInfoPickedUpPercent";
	public static final String ClosePickedupSummary = "//a[@id='dialog-close1']";
	public static final String GetReservationNumberfromRoomingListSummary = "//div[@id='roomingListSummary_DIV']//tr[2]//td[1]";

	public static final String PrePathforReservationDeatils = "//div[@id='ReservationDetail_CP']";
	public static final String GuestNameInReservationDetails = PrePathforReservationDeatils
			+ "//span[@data-bind='text: GuestProfileName']";
	public static final String GroupNameInReservationDetails = PrePathforReservationDeatils
			+ "//span[@data-bind='text: AccountName']";
	public static final String ConfirmationNumberInReservationDetails = PrePathforReservationDeatils
			+ "//span[@data-bind='text: confirmationNumber']";
	public static final String TripTotalInReservationDetails = PrePathforReservationDeatils
			+ "//span[contains(@data-bind,'value: tripTotal')]";
	public static final String BalanceInReservationDetails = PrePathforReservationDeatils
			+ "//span[contains(@data-bind,'value: balance')]";
	public static final String StartCheckInButtonInReservationDetails = PrePathforReservationDeatils
			+ "//button[text()='START CHECK-IN']";
	public static final String CheckinDateInReservtionDetails = "(" + PrePathforReservationDeatils
			+ "//span[contains(@data-bind,'text: $root.formatDate(dateArrive')])[1]";
	public static final String CheckoutDateInResrvationDetails = "(" + PrePathforReservationDeatils
			+ "//span[contains(@data-bind,'text: $root.formatDate(dateDepart')])[1]";
	public static final String RoomClassInResrvationDetails = "(" + PrePathforReservationDeatils
			+ "//span[contains(@data-bind,'text: roomClassName')])[1]";
	public static final String RoomNumberInResrvationDetails = "(" + PrePathforReservationDeatils
			+ "//span[contains(@data-bind,'text: roomNumber')])[1]";
	public static final String RatePlanInReservationDetails = PrePathforReservationDeatils
			+ "//span[contains(@data-bind,'text: (promoCode()')]";
	public static final String PromoInReservationDetails = PrePathforReservationDeatils
			+ "//span[contains(@data-bind,'text: promoCode')]";
	public static final String CloseReservationDetailsPopup = "//a[@id='dialog-close2']";
	public static final String CloseRoomingListPopup = "//a[@id='dialog-close0']";

	public static final String MoveToTapeChart = "(//*[text()='Tape Chart'])[2]";
	public static final String AddLineItemButton = "//button[.='Add Line Item']";
	public static final String voidButton = "(//button[.='Void'])[1]";
	public static final String paymentAmountInput = "//input[@id='payFormAmountV2']";
	public static final String paymentType = "(//h4[.='Take Payment']//following::button[contains(@class, 'dropdown-toggle')]//i)[2]";
	public static final String paymentSubmitButton = "//button[contains(@data-bind,'enable: IsPaymentFormValid()')]";
	public static final String paymentSuccessFullPopupClose = "//h4[contains(text(),'Successful')]//following::button[text()='Close']";

	public static final String Roomclass2 = "(//span[.='Room Classes'])[2]";
	public static final String TaskLIst_TODoStatusDropDownBox = "//label[contains(text(),'To Do')]";
	public static final String TaskLIst_DoneStatusDropDownBox = "//label[contains(text(),'Done')]";
	public static final String TaskLIst_InspectionStatusDropDownBox = "//label[contains(text(),'Inspection')]";
	public static final String TaskLIst_StatusDropDownBoxFilterOption = "//span[@class='filter-option pull-left']";
	public static final String TaskLIst_StatusDropDownBoxButton = "//span[@class='filter-option pull-left']";
	public static final String RoomStatusTab = "//a[.='Room Status']";
	public static final String Inventory_Backward_2 = "//a[contains(@href,'fncMenuInventory')]";
	public static final String TaskForDropdown = "//div[@class='dateRanges']//span[@class='gs-angle-down']";
	public static final String clickOnTaskReports = "//div[contains(@class,'task-actions')]//a[contains(text(),'Reports')]";
	public static final String tasListReportspage = "//span[text()='Task List']";
	public static final String taskDueOn = "addTaskDatePicker";
	public static final String enterCustomRangeStartDate = "//span[contains(@class,'startDate')]";
	public static final String enterCustomRangeEndDate = "//span[contains(@class,'endDate')]";
	public static final String customRangeTodayDate = "//td[contains(@class,'today')]";

	public static final String ADVANCE_SEARCH_WITH_RESERVATION_NUMBER = "//input[contains(@data-bind,'value: AdvReservationNumber')]";
	public static final String PRINT_RESERVATION_REPORTS = "//div[contains(@class,'submit print')]";

	public static final String Reservation_Backward_Admin = "(//a/span[contains(text(),'Reservations')])[3]";

	public static final String guestAccountFirstName = "(//input[contains(@data-bind,'value: AccountFirstName')])[1]";
	public static final String guestAccountLastName = "(//input[contains(@data-bind,'value: AccountLastName')])[1]";

	public static final String clickDecyptCCNumberImg = "//img[@alt ='Decrypt']";
	public static final String billingAccountNumberInput = "//input[@placeholder='Billing Account Number']";
	public static final String guestInfoSaveButton = "(//button[contains(@data-bind,'$data.Reset')]//following-sibling::button[contains(text(),'Save')])[1]";
	public static final String reservationTabGuestAccount = "(//a[text()='Reservations'])[2]";

	public static final String CheckboxDetailedReservationList = "(//span[text()='Detailed Reservation List']//..//input[@type='radio'])[1]";
	public static final String ClickOnReport = "//div[contains(@class,'innroad-btn-submit')]//div[text()='Export']";
	public static final String ClickOnReportAsPDF = "//div[text()='Download As Pdf']";
	public static final String ClickOnCloseReportsPopup = "//div[@id='optionsForInnroad']//div[@data-bind='click: Cancel']";
	public static final String GuestInfoInAdvanced = "//div[contains(@data-bind,'$parent.ReservationSearchType.Advanced')]//span[text()='Guest']";
	public static final String ReservationInfoInAdvanced = "//div[contains(@data-bind,'$parent.ReservationSearchType.Advanced')]//span[text()='Reservation']";
	public static final String MarketingInfoInAdvanced = "//div[contains(@data-bind,'$parent.ReservationSearchType.Advanced')]//span[text()='Marketing']";
	public static final String AdvanceSearchReservationInput = "//input[@data-bind='value: AdvReservationNumber']";
	public static final String ClickOnPrint = "//*[@id='optionsForInnroad']//div[text()='Print']";
	public static final String ModuleLoading = "//*[@id='bpjscontainer_23']/div[2]//div[@class='modules_loading']";
	public static final String DownloadButton = "//*[@id='btnImgDownload']";
	public static final String ClickOnPrintIcon = "//a[contains(@data-bind,'click: Print')]";

	// Account
	public static final String EnterAccountNumber = "//input[contains(@data-bind,'value: AccountNoToDisplay')]";
	public static final String SELECT_PROPERTY_FROM_ACCOUNT = "//select[contains(@data-bind,'value: Account.PropertyId')]";
	public static final String CLICK_PROCESS_BUTTONS = "//div[@id='AccountPaymetItemDetail']//button[contains(@data-bind,'btnProcess_Click')]";
	public static final String locading = "(//div[@class='ir-loader-in'])";

	public static final String ADVANCE_SEARCH_WITH_GUEST_NAME = "//input[contains(@data-bind,'value: AdvGuestName')]";
	public static final String GET_ACCOUNT_NUMBER = "//span[contains(@data-bind,'text: AccountNoToDisplay')]";
	public static final String ADVANCE_SEARCH_WITH_ACCOUNT_NUMBER = "(//input[contains(@data-bind,'value: AdvAccountNumber')])[1]";
	public static final String ADVANCE_SEARCH_WITH_EMAIL_ADDRESS = "(//input[@type='email'])[1]";
	public static final String ADVANCE_SEARCH_WITH_PHONE_NUMBER = "(//input[@type='tel'])[1]";
	public static final String ADVANCE_SEARCH_WITH_REFERRAL = "(//label[text()='Referral']/preceding-sibling::div/button)[1]";
	public static final String ADVANCE_SEARCH_WITH_COUNTRY = "//span[contains(text(),'Country')][1]";
	public static final String ADVANCE_SEARCH_WITH_STATE = "//button[@title='State']";
	public static final String ADVANCE_SEARCH_WITH_CHECKIN_DATE = "//input[contains(@data-bind,'value: DateStart')]";
	public static final String ADVANCE_SEARCH_WITH_CHECKOUT_DATE = "//input[contains(@data-bind,'value: DateEnd')]";
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
	public static final String PRINT_ICON = "//a[contains(@data-bind,'click: Print')]";
	public static final String CHECK_BOX_DETAILED_RESERVATION_LIST = "(//span[text()='Detailed Reservation List']//..//input[@type='radio'])[1]";
	public static final String CLICK_ON_REPORT_AS_PDF = "//div[text()='Download As Pdf']";
	public static final String CLICK_ON_EXPORT = "//div[contains(@class,'innroad-btn-submit')]//div[text()='Export']";
	public static final String HEADER_STATUS_AFTER_RESERVATION = "//div[@data-bind='css:ReservationStatusDisplay']//span[contains(@data-bind,'text:reservationViewStatuses.titleLineStatus')]";
	public final static String GET_CONFIRMATION_NUMBER = "//span[contains(@data-bind,'text:confirmationNumber')]";
	public final static String GET_RESERVATION_STATUS = "//span[contains(@data-bind,'text: ConfirmationNumber')]//..//..//following-sibling::td//span[contains(@data-bind,'StatusName.replace')]";
	public final static String CLICK_ON_PRINT_BUTTON = "//*[@id='optionsForInnroad']//div[text()='Print']";
	public final static String CLICK_ON_ADVANCE_SEARCH = "//button[.='Advanced']";
	public final static String VERIFY_ACCOUNT_NAME = "//span[contains(@data-bind,'text:$parent.account')]";
	public static final String RADIO_BUTTON_LIST_REPORT = "(//span[text()='List Report']//..//input[@type='radio'])[1]";
	public static final String CHECK_BOX_INCLUDE_TOTAL_REVENUE = "//span[text()='Include Total Revenue']//..//input[@type='checkbox']";
	public static final String PropertyList = "//div[@id='select2-drop']//ul//li//div[@role='option']";
	public static final String GetDefultProperty = "(//div[@class='property-selector']//a//span)[1]";

	public static final String clientDetailsHeader = "//font[@class='TitleText']";
	public static final String clientDetailsOptionsTab = "//input[@id='MainContent_btnClientOption']";
	public static final String clientDetailsInventorySubSystemCheckBox = "//input[@id='MainContent_rbtnInventory']";
	public static final String clientDetailsBothCheckBox = "//input[@id='MainContent_rbtnBoth']";
	public static final String rateInterval = "//input[@id='MainContent_txtInterval']";

	public static final String ClickOnAddNewTask = "//i[contains(@class,'icon-plus-1 ')]";
	public static final String NewGuestHistoryButton = "//button[contains(text(),'New Account')]";
	public static final String ClickOnCloseAdvanceSearch = "//button[@data-bind='click: $parent.GoBasicLink']";
	public static final String logoutLink = "//a[@id='logoutLinkMobile']";
	public static final String AccountFolioFolioOptionsChargeType = "//label[contains(text(),'Move To Account Folio: ')]/following-sibling::div";
	public static final String accountFolioDetailOptions = "(//a[contains(text(),'Folio Details')])[1]";

	public static final String roomNumberInItemDetails = "//div/span[contains(@data-bind,'RoomNumber')]";
	public static final String roomChargeInItemDetails = "//span[contains(@data-bind,'RoomChargeSummary')]";
	public static final String totalSummaryInItemDetails = "//span[contains(@data-bind,'TotalSummary')]";
	public static final String cancelButton = "//span[contains(@data-bind,'UpdatedDate')]/../../div/button[contains(text(),'Cancel')]";
	public static final String taxesAndServiceCharge = "//span[contains(@data-bind,'TaxSummary')]";
	public static final String clickOnSave = "(//button[contains(@data-bind,'ReservationDetail.PreSave')])";
	public static final String guestHistoryAccountPage = "(//div[@class='AccountDetail']/span)[2]";
	public static final String Tax_size = "//table[@class='dgText']/tbody/tr[starts-with(@class,'dgItem')]/td/a";
	public static final String SelectRoomClassInSearch = "//select[@id='MainContent_drpRoomClassList']";
	public static final String Tax_Items = "//font[text()='Tax Items']";
	public static final String TaxHeader = "//font[text()='Tax Items']";
	public static final String GetAlltaxes = "//table[@id='MainContent_dgTaxItemList']/tbody/tr[starts-with(@class,'dgItem')]";
	public static final String Account_Folio_FolioOptions1 = "(//a[contains(text(),'Folio Options')])[1]";
	// select MultiRoom routing
	public static final String SelectGuestFolioRoom = "//button[contains(@title,'Guest Folio')]";
	public static final String GuestFolioFristRoom = "//span[contains(text(),'Guest Folio For DBR')]";
	public static final String GuestFolioSecondRoom = "//span[contains(text(),'Guest Folio For KS')]";
	public static final String FolioOptionSave = "//button[contains(@data-bind,'parent.ReservationDetail.PreSave()')]";
	public static final String ChargeRoutingPopText = "//span[contains(text(),'Apply Charge Routing ?')]";
	public static final String folioPayments = "//label[text()='Payments: ']//..//span[@class='pamt']//span";
	public static final String FolioOptionCancelationPolicyDeleteAllBtn = "btnDelAll";
	public static final String enterRateInterval = "//input[@id='MainContent_txtInterval']";
	public static final String GroupFolioOptionButton = "MainContent_lnkOpenChargeRouting";
	public static final String groupFolioTab = "MainContent_btnFolio";
	public static final String folioOptionNoShowPolicy = "MainContent_Folio1_drpAccPolicyTypeNoShow";

	public static final String GUEST_ACCOUNT_FIRST_NAME = "//input[contains(@data-bind,'value: Account.AccountFirstName')]";
	public static final String GUEST_ACCOUNT_LAST_NAME = "//input[contains(@data-bind,'value: Account.AccountLastName')]";

	public static final String rateGridAvailableButton = "rates-and-availability-tabs-tab-AVAILABILITY_VIEW";
	public static final String rateGridBulkUpdateButton = "//button[text()='Bulk Update']";
	public static final String rateGridBulkUpdateAvailable = "(//li//a[contains(text(),'Availability')])[1]";
	public static final String availabilityHeading = "//div[text()='Bulk update - Availability']";
	public static final String bulkUpdatePopupText = "//div[contains(text(), 'This one-time update ')]";
	public static final String startDate = "//label[text()='Start']/following-sibling::div//input";
	public static final String endDate = "//label[text()='End']/following-sibling::div//input";

	public static final String totalOccupancyText = "//span[text()='For days that total occupancy is']";
	public static final String totalOccupancy = "//span[text()='For days that total occupancy is']/preceding-sibling::label//span";
	public static final String totalOccupancyType = "//span[@aria-label='Clear value']//following-sibling::span[@class='Select-arrow-zone']";
	public static final String totalOccupancyTypeVisibility = "//span[text()='For days that total occupancy is']/parent::label//following-sibling::div";
	public static final String occupancyIcon = "//label[contains(@class,'input-with-icon')]//following::span//i";
	public static final String totalOccupanyValue = "//input[@class='filterPercentageTextfield form-control inline-b']";

	public static final String CLICK_ON_AVAILABILITY = "//a[@id='rates-and-availability-tabs-tab-AVAILABILITY_VIEW']";
	public static final String DAY_TAB_TEXT = "//div[@class='col day-text']";
	public static final String TOTAL_OCCUPANCY_TAB_TEXT = "//div[@class='col DateCell OccupancyHeader undefined']";
	public static final String PACE_VS_LAST_YEAR_TAB_TEXT = "//div[@class='col DateCell OccupancyHeader pace-title']";
	public static final String SELECT_AVAILABILITY_BULK_UPDATE = "//a[contains(text(),'Availability')]";
	public static final String BULK_UPDATE_BUTTON = "//button[contains(text(),'Bulk Update')]";
	public static final String ADD_RATE_PLAN_BUTTON = "//button[contains(text(),'Add Rate Plan')]";
	public static final String NIGHTLY_RATE_PLAN = "//a[contains(text(),'Nightly rate plan')]";
	public static final String DERIVED_RATE_PLAN = "//a[contains(text(),'Derived rate plan')]";
	public static final String PACKAGE_RATE_PLAN = "//a[contains(text(),'Package rate plan')]";
	public static final String INTERVAL_RATE_PLAN = "//a[contains(text(),'Interval rate plan')]";
	public static final String SELECT_RATES_BULK_UPDATE = "//a[contains(text(),'Rates')]";
	public static final String SELECT_RULES_BULK_UPDATE = "//a[contains(text(),'Rules')]";
	public static final String MAIN_MENU_CLOSE_BUTTON = "//a[@class='MainMenu_closeButton__os2Y7']";
	public static final String NEW_RATE_PLAN_TAB_TITLE = "//span[@class='summary-value']";
	public static final String CLOSE_RATE_BULK_UPDATE_POPUP = "//button[@class='close']";
	public static final String BULK_UPDATE_HEADER = "//button[@class='close']//..";

	public static final String CANCELATION_POLICIES = "//*[contains(text(),'Cancellation')]//..//..//div[@class='title']";
	public static final String DEPOSITE_POLICES = "//*[contains(text(),'Deposit')]//..//..//div[@class='title']";
	public static final String CHECK_IN_POLICIES = "//*[contains(text(),'Check-in')]//..//..//div[@class='title']";
	public static final String NO_SHOW_POLICIES = "//*[contains(text(),'No Show')]//..//..//div[@class='title']";

	// public static final String bulkUpdatePopupRoomClass =
	// "//div[contains(text(),'Select room class(es)')]";
	public static final String bulkUpdatePopupRoomClass = "//label[text()='Room class']//following-sibling::div//div[1]";
	public static final String updateButton = "//button[text()='Update']";

	public static final String daysText = "//p[contains(text(),'Availability')]";
	public static final String bulkUpdateCancel = "//button[text()='Cancel']";

	public static final String roomClassStatusDropDown = "//select[contains(@data-bind, 'options: availableStatuses')]";
	public static final String roomClassSearchButton = "//button[text()='Search']";
	public static final String roomClassList = "//td[@data-bind='click: $parent.selectedRoomClass']//a";

	public static final String ROOM_CLASS_STATUS_SELECTED_VALUE = "//div[contains(@class,'RoomClasses_statusSelectControl')]//div[@class='ant-select-selection-selected-value']";

	// rules popup
	public static final String rulesHeading = "//div[text()='Bulk update - Rules']";
	public static final String minimumStay = "//div[text()='Minimum stay']//preceding-sibling::div//label//span";
	public static final String minimumStayValue = "//div[text()='Minimum stay']//following-sibling::div//input";
	public static final String checkin = "//div[text()='Check-in']//preceding-sibling::div//label//span";
	public static final String noCheckInInput = "//div[text()='Check-in']//following-sibling::div//label//input";
	public static final String noCheckInCheckbox = "//div[text()='Check-in']//following-sibling::div//label//span[1]";
	public static final String checkOut = "//div[text()='Check-out']//preceding-sibling::div//label//span";
	public static final String noCheckOutInput = "//div[text()='Check-out']//following-sibling::div//label//input";
	public static final String noCheckOutCheckbox = "//div[text()='Check-out']//following-sibling::div//label//span[1]";

	public static final String closeRulesPopup = "//div[text()='Bulk update - Rules']//button//div";
	public static final String updatExistingRules = "//div[text()='Update existing rules']";
}