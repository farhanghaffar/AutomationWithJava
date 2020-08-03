package com.innroad.inncenter.pages;

public class RateGridPage {

	
	public static final String RATE_GRID_CALENDAR_ICON = "//*[@id='daterange-calendar']";
	public static final String DATE_FROM_GRID_HEADER = "//*[@id='daterange-calendar']/../../../following-sibling::div/div[1]/div/div";
	public static final String TOTAL_OCCUPANCY_TABLE_DATE_COLUMNS = "//div[contains(@class,'MonthDateCell')]";
	public static final String ROOMCLASSES_TABLE_DATE_COLUMNS = "//div[@class='DatesTable'][1]//div[contains(@class,'RateCellStyle')]";
	
	public static final String ADD_RATE_PLAN = "//button[text()='Add Rate Plan']";
}
