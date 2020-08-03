package com.innroad.inncenter.pages;

public class RoomStatusPage {
	public static final String InputSearch = "(//input[@placeholder='Search Room, Zone, Occupancy and more'])[2]";
	public static final String Loading = "//div[text()='Loading...']";
	public static final String ButtonSearch = "searchsubmit";
	public static final String GetCategoryImage = "//div[@class='gs-displayTable']//span//span//span";
	public static final String GetCategoryName = "//div[@class='gs-displayTable']//span[contains(@class,'firstLetterCaps')]";
	public static final String GetTaskDueOnAssignedAndStatus = "//tr[@class='text-uppercase']//following-sibling::tr//td";
	public static final String ButtonClosPopup = "//button[contains(@class,'closeBtn')]";
	public static final String RoomStatusInSecondWindow = "//span[@class='gs-statsValue']";
	public static final String RoomStatus = "//div[contains(@class,'change-status')]//span";
	public static final String RoomStatusFor = "//i[text()='Room Status for']//following-sibling::span";
	public static final String GetTaskImage = "//span[contains(@class,'gs-displayTable')]//span//span";
	public static final String GetTaskName = "//span[contains(@class,'firstLetterCaps')]//span";
	public static final String TaskDueOnAssignedAndStatusInReport = "//tr[@class='text-uppercase']//following-sibling::tr//td";
	





}