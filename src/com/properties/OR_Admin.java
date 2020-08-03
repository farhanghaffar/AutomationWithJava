package com.innroad.inncenter.properties;

public class OR_Admin {

	public static final String UserIcon = "//div[@class='user_des']";
	// public static final String LogoutBtn = "//a[@id='logoutLinkMobile']";
	public static final String LogoutBtn = "//a[@id='logoutlink']|//a[@id='logoutLinkMobile']";
	public static final String PropertyName = "//span[@class='select2-chosen']";

	public static final String CLICK_CLIENT = "//div[@id='MainContent_Clients_DIV']//following-sibling::tr//a";
	public static final String CLICK_CLIENT_OPTION = "//input[@id='MainContent_btnClientOption']";

	public static final String GET_DEFAUL_CURRENCY = "//select[@id='MainContent_drpDefaultCurrency']//following-sibling::option[@selected='selected']";
	public static final String GET_DATE_FORMAT = "//select[@id='MainContent_drpDateFormat']//following-sibling::option[@selected='selected']";
	public static final String SELECT_DATE_FORMAT = "//select[@id='MainContent_drpDateFormat']";
	public static final String ADMIN_SAVE_BUTTON = "//input[@id='MainContent_btnSave']";
	public static final String ADMIN_DONE_BUTTON = "//input[@id='MainContent_btnDone']";

}
