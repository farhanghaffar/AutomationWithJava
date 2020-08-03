package com.innroad.inncenter.properties;

public class OR_Setup {


	public static final String EnterSeasonStartDate = "//input[@placeholder='Start Date']";
	public static final String EnterSeasonEndDate = "//input[@placeholder='End Date']";
	public static final String SeasonPagination = "//div[contains(@data-bind,'TotalRecords: totalNumberOfRows')]//ul//li/a";
	public static final String SearchSeason_Btn = "//button[contains(@data-bind,'click: goSearchSeasons')]";
	public static final String CloseSeasonTab = "(//i[contains(@data-bind,'click: $parent.closeTab')])[7]";

	public static final String Roomclass_Pagenation="//small[text()='Items Per Page']/following-sibling::select";
	public static final String Setup_PropertiesGuestStatementOption="//input[@id='MainContent_chkDefaultGenerateGuestStatementOnCheckOut']";
	public static final String Setup_PropertiesGuestRegistrationOption="//input[@id='MainContent_chkDefaultGenerateGuestRegistrationOnCheckIn']";

	// New Room Class Room Number
	public static final String New_RoomClass_RoomNumbers = "//table[contains(@class,'flip-content')]//tbody[@data-bind='foreach: Rooms']/tr/td[2]//input";


	public static final String Setup_PropertiesGuaranteedOption="//input[@id='MainContent_chkSetReservationToGuaranteed']";
	public static final String PROPERTY_DEPOSITREQUIRED_SAVEGAURANTEEDRESERVATION = "//input[@id='MainContent_chkRequiredDepositForGuaranteedReservation']";
	public static final String PROPERTY_DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION = "//input[@id='MainContent_chkSetReservationToGuaranteed']";


	//Documents templetas
	public static final String FUNCTION_ADD_ATTACHMENTS = "//input[contains(@id,'MainContent_dgScheduleFunctions_txtSources')]";
	public static final String FUNCTION_AVAILABLE_ATTACHEMENTS_LIST= "//select[@id='MainContent_lstSources']";
	public static final String FUNCTION_ADDED_ATTACHEMENTS_LIST= "//select[@id='MainContent_lstPickedSources']";
	public static final String PICK_ONE_BUTTON = "//input[@id='btnPickOne']";
	public static final String DONE_BUTTON = "//input[@value='Done']";

	public static final String NonZeroBalanceWhileCheckOut="//input[@id='MainContent_chkAllowNonZeroBalance']";
	
	
	

}
