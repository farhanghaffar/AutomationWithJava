package com.innroad.inncenter.properties;

public class OR_RatesGrid {

	public static final String HEADER_DATES_ROW_WEEKDAY = "//div[contains(@class,'top-header-row')]/div[contains(@class,'MonthDateCell')]/div[@class='weekDay']";
	public static final String HEADER_DATES_ROW_DAY_NUMBER = "//div[contains(@class,'top-header-row')]/div[contains(@class,'MonthDateCell')]/div[@class='dayNum']";
	public static final String DATE_RANGE_MONTH_YEAR = "//div[@class='d-flex']/div";
	public static final String DATE_RANGE_CALENDAR = "//div[@class='daterange']/img";
	public static final String CALENDER_TODAY_BUTTON = "//button[@class='DayPicker-TodayButton']";
	public static final String TOTAL_OCCUPANCY_LABEL = "//div[@class='col DateCell OccupancyHeader undefined']";
	public static final String PACE_VS_LAST_YEAR_LABEL = "//div[@class='col DateCell OccupancyHeader pace-title']";
	public static final String ROOM_CLASSES_NAMES = "//div[contains(@class,'RoomClassHeader ')]//div[@class='roomClassName']";
	public static final String ROOM_CLASS_EXPAND_BUTTON = "//div[contains(@class,'RoomClassHeader ')]//span";

	public static final String BULK_UPDATE_BUTTON = "//button[contains(text(),'Bulk Update')]";
	public static final String ADD_RATE_PLAN_BUTTON = "//button[contains(text(),'Add Rate Plan')]";
	public static final String SETTING_BUTTON_RATE_GRID = "(//button[@id='dropdown-basic-0'])[1]";
	public static final String HEADING_AVAILABILITY_SETTING = "(//li[@class='fs-16 menu-header dropdown-header'])[1]";
	public static final String HEADING_RATES_SETTING = "(//li[@class='fs-16 menu-header dropdown-header'])[2]";
	public static final String AVAILABILITY_TOGGLE_TEXT = "(//span[@class='menu-toggle-label fs-14'])[1]";
	public static final String RATES_TOGGLE_TEXT = "(//span[@class='menu-toggle-label fs-14'])[2]";
	public static final String SETTING_AVAILABILITY_TOGGLE_BUTTON = "(//span[@class='slider round settings-item-slider'])[1]";
	public static final String SETTING_RATES_TOGGLE_BUTTON = "(//span[@class='slider round settings-item-slider'])[2]";
	public static final String HEADER_DATES_BACKGROUND_COLOR = "//div[contains(@class,'RangeTable')]//div[contains(@class,'rate-grid-cell Week')]";
	public static final String RATE_GRID_CALENDAR_ICON = "//*[@id='daterange-calendar']";

	// Bulk Update Section

	public static final String RATESGRID_SETTING_BUTTON = "//button[contains(@class,'rates-settings')]";
	public static final String RATESGRID_SETTING_CONTAINER = "//div[@class='settings-container']/parent::ul";
	public static final String RATESGRID_SETTING_CONTAINER_SHOWADDITIONALADULT_TOGGLE = "//span[contains(text(),'Show additional adult and child')]/parent::li//span[contains(@class,'settings-item-slider')]";
	public static final String RATESGRID_BULKUPDATE_BUTTON = "//button[text()='Bulk Update']";
	public static final String UPDATERATES_OPTIONS = "//input[@name='updatePlanOption']/following-sibling::span";
	public static final String UPDATERATE_UPDATERATEBYROOMCLASS_TOGGLE = "//span[contains(text(),'Update rates by room class')]/preceding-sibling::button";
	public static final String UPDATE_RATE_NEWRATE_NIGHTLYRATE = "//input[@name='newRate']";
	public static final String UPDATE_RATE_NEWRATE_ADDITIONALADULT = "//input[@name='additionalAdult']";
	public static final String UPDATE_RATE_NEWRATE_ADDITIONALCHILD = "//input[@name='additionalChild']";
	public static final String UPDATE_RATE_UPDATEBYVALUE_SELECTDROPDOWN_FIRST = "//span[text()='each rate by']/preceding-sibling::div";
	public static final String UPDATE_RATE_UPDATEBYVALUE_SELECTDROPDOWN_SECOND = "//span[text()='each rate by']/following-sibling::div";
	public static final String UPDATE_RATE_UPDATEBYVALUE_VALUEFIELD = "//input[@class='ant-input-number-input']";
	public static final String UPDATE_RATE_DATE_PICKER = "//div[@class='DayPickerInput']";

	public static final String UPDATE_RATE_UPDATE_BUTTON = "//button[text()='Update']";
	public static final String BULKUPDATE_RATES_CLOSEBUTTON = "//button[@class='close']";
	public static final String UPDATE_RATE_CONFIRMATION_YES_UPDATE_BUTTON = "//div[contains(@class,'bulkUpdateConfirmDialog')]//button[contains(@class,'btn-success')]";

	public static final String rateGridBulkUpdate = "//button[text()='Bulk Update']";
	public static final String rateGridBulkUpdateAvailable = "(//li//a[contains(text(),'Availability')])[1]";
	public static final String rateGridBulkUpdateRates = "(//li//a[contains(text(),'Rates')])[1]";

	public static final String bulkUpdatePopupHeading = "//div[text()='Bulk update - Availability']";
	public static final String bulkUpdatePopupText = "//div[contains(text(), 'This one-time update ')]";
	public static final String bulkUpdatePopupCheckinInput = "//label[text()='Start']/following-sibling::div//input";
	public static final String bulkUpdatePopupCheckoutInput = "//label[text()='End']/following-sibling::div//input";

	public static final String totalOccupancyText = "//span[text()='For days that total occupancy is']";
	public static final String totalOccupancy = "//span[text()='For days that total occupancy is']/preceding-sibling::label//span";
	public static final String totalOccupancyType = "//span[@aria-label='Clear value']//following-sibling::span[@class='Select-arrow-zone']";
	public static final String totalOccupancyTypeVisibility = "//span[text()='For days that total occupancy is']/parent::label//following-sibling::div";
	public static final String occupancyIcon = "//label[contains(@class,'input-with-icon')]//following::span//i";
	public static final String totalOccupanyValue = "(//input[@class='filterPercentageTextfield form-control inline-b'])[1]";

	public static final String bulkUpdatePopupRoomClass = "//label[text()='Room class']//following-sibling::div//div[1]";
	public static final String bulkUpdatePopupUpdateButton = "//button[text()='Update']";

	public static final String bulkUpdatePopupDays = "//p[contains(text(),'Availability')]";
	public static final String bulkUpdateCancel = "//button[text()='Cancel']";

	public static final String roomClassStatusDropDown = "//select[contains(@data-bind, 'options: availableStatuses')]";
	public static final String roomClassSearchButton = "//button[text()='Search']";
	public static final String roomClassList = "//td[@data-bind='click: $parent.selectedRoomClass']//a";

	public static final String ratesTab = "//li[@class='active']";
	public static final String dailyOccupancyAndRatesLabel = "//span[(text()='Daily Occupancy & Rates')]";
	public static final String calendarIcon = "//div[@id='daterange-calendar']//img";
	public static final String ratePlanArrow = "//span[@class='Select-arrow-zone']";
	public static final String ratePlanNamesList = "//div[@class='Select-menu-outer']/div[@class='Select-menu']/div";
	public static final String ratePlansColor = "//div[@class='Select-menu-outer']/div[@class='Select-menu']/div/div";
	public static final String ratePlanGridLoading = "//div[contains(@class,'sweet-loading')]";
	public static final String pacevsLastYearLabel = "//div[text()='Pace vs Last Year']";
	public static final String ratePlanSelectValue = "//div[@class='Select-value']";
	public static final String ratePlanNamesExpanCollapseIcon = "//span[@class='ir-acrd-bnt ir-plus pr-5']";
	public static final String bestAvailableRate = "//div[contains(text(),'Best Available Rates')]";
	public static final String editIcon = "//i[@class='icon-edit pl-15']";
	public static final String deleteIcon = "//i[@class='delete-icon-svg trash-col']";
	public static final String ratePlanSelectInput = "//div[@class='Select-input']";
	public static final String ratePlanPalaceHolder = "//div[@class='Select-placeholder']";
	public static final String ratePlanInputBox = "//div[@class='Select-input']/input";
	public static final String rateGridAllRoomClass = "//div[@class='DatesTable']//div[@class='roomClassName']";
	public static final String rateGridAvailabilityLabel = "//div[@class='DatesTable']//div[@class='row d-flex availability-row']/span";
	public static final String rateGridPopupDate = "//span[@class,'popover-rate-date']";
	public static final String rateGridPopupRuleHeader = "//div[contains(@class,'RuleHeader')]";
	public static final String rateGridPopupRuleImage = "//p[@class='rule-property']/img";
	public static final String rateGridPopupRuleIndectionImage = "//span[contains(@class,'RuleIndication')]";
	public static final String rateGridPopupRuleIndectionLabel = "//div[@class='legend-item']";
	public static final String rateGridPopupMinimumNights = "//p[@class='rule-property']/span";

	public static final String EndDateInput = "//label[text()='End']//following-sibling::div//input";

	public static final String OCCUPANCY_ICON = "//label[contains(@class,'input-with-icon')]//following::span//i";
	public static final String START_DATE_INPUT = "//label[text()='Start']//following-sibling::div//input";
	public static final String END_DATE_INPUT = "//label[text()='End']//following-sibling::div//input";
	public static final String TOTAL_OCCUPANCY_TEXT = "//span[text()='For days that total occupancy is']";
	public static final String TOTAL_OCCUPANCY_TYPE = "//span[@aria-label='Clear value']//following-sibling::span[@class='Select-arrow-zone']";
	public static final String TODAY_DATE_BUTTON = "(//input//following::div[@class='DayPicker-Footer']//button)[1]";
	public static final String START_DATE_SELECTED_DAY = "(//label[text()='Start']//following-sibling::div//input//following::div[contains(@class,'DayPicker-Day--today')])[1]";
	public static final String END_DATE_SELECTED_DAY = "(//label[text()='End']//following-sibling::div//input//following::div[contains(@class,'DayPicker-Day--today')])[1]";
	public static final String OCCUPANCY_CLAUSE = "(//div[starts-with(text(),'This clause')])[1]";
	public static final String OCCUPANCY_PERCENTAGE_SIGN = "(//input[contains(@class,'filterPercentageTextfield')]/following-sibling::span)[1]";
	public static final String NO_ROOM_CLASS_SELECTED = "//div[text()='---- No room class selected --- ']";
	public static final String NO_DAYS_MATCH = "//p[text()='No days match the parameters you set. Please adjust the parameters to include at least 1 day']";
	public static final String ratePlanNames = "//div[@class='Select-menu-outer']//div//div//div";
	public static final String NO_DATS_MATCH_OK_BUTTON = "//button[text()='Ok']";

	public static final String ratePlanOption = "//div[@role='option']";

	public static final String rateGridRoomRate = "//input[contains(@name,'rateVal')]";
	public static final String rateGridExtraAd = "//input[contains(@name,'extraAdult')]";
	public static final String rateGridExtraCh = "//input[contains(@name,'extraChild')]";
	public static final String rateGridDate = "//h3[contains(@class,'mb-5 popover-header')]";
	public static final String rateGridSuccess = "//span[contains(@class,'ir-checkMark sm success')]";
	public static final String rateGridDanger = "//span[contains(@class,'ir-crossMark sm danger')]";
	public static final String rateSavedMessage = "//div[@role='alert'and contains(text(),'Rate saved successfully')]";
	public static final String rateOverrideMessage = "//div[@role='alert'and contains(text(),'Deleted rate override successfully')]";
	public static final String rateOverrideLoading = "//div[@class='css-kwlyb4']";
	public static final String popupRuleHeader = "//span[@class='rules-header']";

	public static final String rateGridremoveOverRide = "//b[contains(text(),'Remove Override')]";
	public static final String rateGridRuleSavedMessage = "//div[@role='alert'and contains(text(),'Rule saved successfully')]";
	public static final String rateEditIcon = "//i[contains(@class,'icon-edit')]";
	public static final String rateDeleteIcon = "//span[contains(@class,'icon-delete-ratePlan')]";
	public static final String ruleIndecationIcon = "//span[contains(@class,'legend-icon')]";
	public static final String rateGridRoomClass = "//div[@class='roomClassName']";
	public static final String ratePlanDesc = "//div[@class='rate-plan-description']/span";
	public static final String ratePlanConditionDesc = "//div[@class='ir-h4 conditions active']//div[contains(text(),'Conditions')]";
	public static final String popoverRate = "//h3//span[@class='popover-rate-date']/following-sibling::span";
	public static final String rulePropertyImage = "//p[@class='rule-property']/img";
	public static final String ratePlanMinusIcon = "//span[@class='ir-acrd-bnt ir-minus pr-5']";
	public static final String ratePlanPlusIcon = "//span[@class='ir-acrd-bnt ir-plus pr-5']";
	public static final String ruleToolTip = "//div[contains(@class,'rules-tooltip')]";
	public static final String ratePlanOpenArrowIcon = "//div[@class='rateplan-arrow open']";
	public static final String ruleLabels = "//span[@class='rules-labels']";
	public static final String ratePLanCloseIcon = "//a[@data-id='EDIT_RATEPLAN_TABID']/span";
	public static final String ratePLanCalendarTab = "//div[contains(text(),'Calendar')]";
	public static final String rateDeleteButton = "//button/span[text()='Delete']";
	public static final String ratePlanDeleteMsg = "//p/span";
	public static final String ratePlanDeleteMessage = "//div[@role='alert'and contains(text(),'Rate Plan deleted successfully')]";
	public static final String rateGridDay = "//div[@class='dayNum']";
	public static final String GET_RATE_PALN_DESCRIPTION="//div[@class='rate-plan-description']";
	public static final String SELECTED_RATEPLAN_NAME="//span[@class='rate-plan-picker-label']";


}
