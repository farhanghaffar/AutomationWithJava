package com.innroad.inncenter.pages;

public class NightlyRatePage {

	public static final String RATE_PLAN_NAME = "//label[text()='Rate Plan Name']/following-sibling::input";
	public static final String RATE_PLAN_FOLIO_DISPLAY_NAME = "//label[text()='Folio Display Name']/following-sibling::input";
	public static final String RATE_PLAN_DESCRIPTION = "//label[text()='Enter rate plan description (optional)']/following-sibling::textarea";
	
	public static final String NEXT_BUTTON = "//span[text()='Next']/parent::button";
	
	public static final String RATE_PLAN_NAME_ERROR_TEXT = "//label[text()='Rate Plan Name']/../following-sibling::div[@class='ratePlanNameErrorText']/div";
	public static final String RATE_PLAN_FOLIO_DISPLAY_NAME_ERROR_TEXT = "//label[text()='Folio Display Name']/../following-sibling::div[@class='ratePlanNameErrorText']/div";
	
	public static final String DISTRIBUTION_CHANNEL_LIST = "//span[text()='Select All']/../following-sibling::div/label/span[2]";
	public static final String ROOMCLASSES_LIST = "//span[text()='Select All']/../../following-sibling::div/label/span[2]";
	
	public static final String PROMO_CODE = "//*[@id='promoCode']";
	
	public static final String RESTRICTIONS_TO_QUALIFY_RATE = "(//span[contains(@class,'stepSubText')])[2]";
	
	public static final String RATE_PLAN_STATUS_SELECTION = "//label[text()='Rate plan status']/following-sibling::div";
	public static final String SAVE_RATE_PLAN = "//span[text()='Save rate plan']/parent::button";
	public static final String RATE_PLAN_NAME_EDIT_PAGE = "//h3[@class='seasonTitle title']";
	public static final String RATE_PLAN_TYPE_EDIT_PAGE = "//label[text()='Rate plan type']//following-sibling::label";
	public static final String PROPERTY_NAME_EDIT_PAGE = "//label[text()='Property name']//following-sibling::label";
	public static final String RATE_PLAN_OVERVIEW_TAB = "//div[text()='Rate plan overview']";
	public static final String RESTRICTION_AND_POLICIES_TAB = "//div[text()='Restrictions & Policies']";
	public static final String CALENDAR_TAB = "//div[text()='Calendar']";
}
