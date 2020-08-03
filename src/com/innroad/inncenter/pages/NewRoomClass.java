package com.innroad.inncenter.pages;

public class NewRoomClass {

	public static final String RoomClassItemsPerpage = "//div[@class='RoomClasses_paginationSelect_1TP4Q']//div[@role='combobox']";
	public static final String RoomClassItemsPerpage_Select100 = "//ul[@role='listbox']//li[text()='100']";
	public static final String SelectedItemsPerPage = "//div[@class='RoomClasses_paginationSelect_1TP4Q']//div[@class='ant-select-selection-selected-value']";
	public static final String SelectedItems_PerPage = "//select[@data-bind='options: recordsPerPage, value: NoOfRecordsPerPage']";
	//old layout
		public static final String RoomClassList_Names = "//div[contains(@data-bind,'roomClassList')]//a[contains(@data-bind,'RoomClassName')]";
}
