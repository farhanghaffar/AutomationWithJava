package com.innroad.inncenter.pages;

public class NewListManagement {
	public static final String FilterElementList = "(//table[@class='FilterTable toggleElement-Parent']//td)[2]//font[@class='TextSmall']//a";
	public static final String SystemItemTextField = "(//td[@class='ContentTitleText'])[1]";
	public static final String SaveButton = "//input[@id='MainContent_btnSaveMasterList']";
	public static final String NewItemButton = "//input[@id='MainContent_btnAddMasterList']";
	public static final String EditButton = "//input[@id='MainContent_btnEditMasterList']";
	public static final String DeleteButton = "//input[@id='MainContent_btnDeleteMasterList']";
	public static final String CancelButton = "//input[@id='MainContent_btnCancelMasterList']";
	public static final String CustomItemListSize = "//table[@id='MainContent_dgclientList']//tr";
	public static final String SelectStatus = "//select[@id='MainContent_drpActive']";
	public static final String RatePlanInActiveCustomListCheckbox = "//input[@id='MainContent_dgclientList_chkListFlg']";
	public static final String Newitem_Name = "//input[@name='ctl00$MainContent$dgclientList$ctl05$txtListItemName']";
	public static final String Newitem_Description = "//input[@name='ctl00$MainContent$dgclientList$ctl05$txtlistItemDesc']";
	public static final String RatePlan_Name = "//input[contains(@id,'MainContent_dgclientList_txtListItemName')]";
	public static final String RatePlan_Description = "//input[contains(@id,'MainContent_dgclientList_txtlistItemDesc')]";
	public static final String RatePlan_Status = "(//select[contains(@id,'MainContent_dgclientList_ddlListItemStatus')])[last()]";
	public static final String CustomRatePlan_NameList = "//span[contains(@id,'MainContent_dgclientList_lblListItemName')]";
	public static final String ClickRatePlanLink = "//a[text()='Rate Plan']";

}
