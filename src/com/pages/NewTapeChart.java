package com.innroad.inncenter.pages;

public class NewTapeChart {

	public static final String OverBookingPopup = "//div[@id='RoomOverBookingPopUp']";
	public static final String OverBookingPopup_Cancel = "//div[@id='RoomOverBookingPopUp']//button[text()='Cancel']";
	public static final String OverBookingPopup_Continue = "//div[@id='RoomOverBookingPopUp']//button[text()='Continue']";
	public static final String Propertylevel_RoomsAvailable_Today = "(//div[@class='legendFix']//child::div[contains(@class,'availpercentrow')]//child::div[@class='ir-tc-absoluteCenter'])[2]";
	public static final String Propertylevel_PercentOccupancy_Today = "(//div[@class='legendFix']//child::div[contains(@class,'occupercentrow')]//child::div[@class='ir-tc-absoluteCenter'])[2]";

	// TapeChart RateDetails Popup
	public static final String RateDetailPopup_Heading = "//div[@id='popUpForInnroad']//h4[text()='Rate Details']";
	public static final String RateDetailPopup_RateName = "//div[@id='popUpForInnroad']//input[@placeholder='Rate Name']";
	public static final String RateDetailPopup_RatePlan = "//div[@id='popUpForInnroad']//select[contains(@data-bind,'RatePlanId')]";
	public static final String RateDetailPopup_RateAttributes = "//div[@id='popUpForInnroad']//input[@placeholder='Based On Rate']";
	public static final String RateDetailPopup_Interval = "//div[@id='popUpForInnroad']//input[@placeholder='Interval']";
	public static final String RateDetailPopup_MaxAdults = "//div[@id='popUpForInnroad']//input[@placeholder='Max Adults']";
	public static final String RateDetailPopup_MaxPersons = "//div[@id='popUpForInnroad']//input[@placeholder='Max Persons']";
	public static final String RateDetailPopup_BaseAmount = "//div[@id='popUpForInnroad']//input[@placeholder='Base Amount']";
	public static final String RateDetailPopup_AdditionalAdult = "//div[@id='popUpForInnroad']//input[@placeholder='Additional Adult']";
	public static final String RateDetailPopup_AdditionalChild = "//div[@id='popUpForInnroad']//input[@placeholder='Additional Child']";
	public static final String RateDetailPopup_DisplayName = "//div[@id='popUpForInnroad']//input[@placeholder='Display Name']";
	public static final String RateDetailPopup_Policy = "//div[@id='popUpForInnroad']//textarea[@placeholder='Policy']";
	public static final String RateDetailPopup_Description = "//div[@id='popUpForInnroad']//textarea[@placeholder='Description']";
	public static final String RateDetailPopup_Season = "//div[@id='popUpForInnroad']//tbody[contains(@data-bind,'Seasons')]//span[@data-bind='text: SeasonName']";
	public static final String RateDetailPopup_RoomClass = "//div[@id='popUpForInnroad']//tbody[contains(@data-bind,'RoomClasses')]//span[@data-bind='text: RoomClassName']";
	public static final String RateDetailPopup_Source = "//div[@id='popUpForInnroad']//tbody[contains(@data-bind,'Sources')]//span[@data-bind='text: SourceName']";
	public static final String RateDetailPopup_CancelButton = "//div[@id='popUpForInnroad']//button[text()='Cancel']";
	public static final String btnRatePlanSelect = "//div[contains(@class,'rate-plan')]//button[@data-toggle='dropdown']";
	public static final String enterAmount = "//input[contains(@data-bind,'value: manualOverride')]";
	public static final String AvailableRoom = "(//div[text()='Available']/../../../..)[1]";

	public static final String TapeChart_Out_Icon = "//div[@class='legend cellFix']//div//div[@class='icon nohover out']";

	public static final String TapeChart_CheckOut = "//input[contains(@data-bind,'value: DepartDate')]";

	public static final String UnassignedColumnHeader = "//div[contains(@data-bind,'text:UnassignedResString')]";
	public static final String UnassignedList_ResName = "//div[contains(@data-bind,'css: isRoomAssigned')]//span[contains(@data-bind,'GuestName')]";
	public static final String UnassignedList_RoomClassAbb = "//div[contains(@data-bind,'css: isRoomAssigned')]//span[contains(@data-bind,'RoomClassName')]";
	public static final String UnassignedList_ArrivalDate = "//div[contains(@data-bind,'css: isRoomAssigned')]//span[contains(@data-bind,'StartDate')]";
	public static final String UnassignedList_DepartureDate = "//div[contains(@data-bind,'css: isRoomAssigned')]//span[contains(@data-bind,'EndDate')]";
	public static final String TapeChartDateChart = "(//div[contains(@class,'dateheadercell')])[1]";
	public static final String TAPECHART_CHECKOUT = "//input[contains(@data-bind,'value: DepartDate')]";

	public static final String GET_DATE_AND_DAY = "//div[@class=' roomheadercontainer']//..//following-sibling::div//div";
	public static final String GET_TOAST_MESSAGE = "//div[@class='toast-message']";

	public static final String GET_CHECK_IN_PRE_DATE = "((//span[@class='label-text col-xs-6'])[1]//following-sibling::div//div)[1]";
	public static final String GET_CHECK_IN_POST_DATE = "((//span[@class='label-text col-xs-6'])[1]//following-sibling::div//div)[2]";
	public static final String GET_CHECK_OUT_PRE_DATE = "((//span[@class='label-text col-xs-6'])[2]//following-sibling::div//div)[1]";
	public static final String GET_CHECK_OUT_POST_DATE = "((//span[@class='label-text col-xs-6'])[2]//following-sibling::div//div)[2]";
	public static final String GET_ROOM_CLASS_PRE = "((//span[@class='label-text col-xs-6'])[3]//following-sibling::div//div)[1]";
	public static final String GET_ROOM_CLASS_POST = "((//span[@class='label-text col-xs-6'])[3]//following-sibling::div//div)[2]";
	public static final String GET_TRIP_TOTAL_PRE = "((//span[@class='label-text col-xs-6'])[4]//following-sibling::div//div)[1]";
	public static final String GET_TRIP_TOTAL_POST = "((//span[@class='label-text col-xs-6'])[4]//following-sibling::div//div)[2]";
	public static final String GET_BALANCE_DUE_PRE = "((//span[@class='label-text col-xs-6'])[5]//following-sibling::div//div)[1]";
	public static final String GET_BALANCE_POST = "((//span[@class='label-text col-xs-6'])[5]//following-sibling::div//div)[2]";
	public static final String GET_CHECK_IN_TEXT = "(//span[@class='label-text col-xs-6'])[1]";
	public static final String GET_CHECK_OUT_TEXT = "(//span[@class='label-text col-xs-6'])[2]";
	public static final String GET_ROOM_CLASS_TEXT = "(//span[@class='label-text col-xs-6'])[3]";
	public static final String GET_TRIP_TOTAL_TEXT = "(//span[@class='label-text col-xs-6'])[4]";
	public static final String GET_BALANCE_DUE_TEXT = "(//span[@class='label-text col-xs-6'])[5]";

	public static final String CHANGE_RATE_APPLIED_DROPDOWN_BUTTON = "(//div[@class='container'])[2]//button//span/i";
	public static final String CHANGE_RATE_APPLIED_DROPDOWN_LIST = "(//div[@class='container'])[2]//ul//li";
	public static final String CHANGE_RATE_APPLIED_SELECTEC_OPTION = "(//div[@class='container'])[2]//button//span[@class='filter-option pull-left']";

}
