package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Admin;

public class Elements_Admin {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("Admin");

	public Elements_Admin(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);

	}

	@FindBy(xpath = OR.Admin)
	public WebElement Admin;

	@FindBy(xpath = OR.User_link)
	public WebElement User_link;

	@FindBy(xpath = OR.Users_grid)
	public WebElement Users_grid;

	@FindBy(xpath = OR.User_LastName)
	public WebElement User_LastName;

	@FindBy(xpath = OR.User_FirstName)
	public WebElement User_FirstName;

	@FindBy(xpath = OR.User_LoginID)
	public WebElement User_LoginID;

	@FindBy(xpath = OR.User_Email)
	public WebElement User_Email;

	@FindBy(xpath = OR.User_AssociateRoles)
	public WebElement User_AssociateRoles;

	@FindBy(xpath = OR.User_AssociateProperties)
	public WebElement User_AssociateProperties;

	@FindBy(xpath = OR.User_Save)
	public WebElement User_Save;

	@FindBy(xpath = OR_Admin.UserIcon)
	public WebElement UserIcon;

	@FindBy(xpath = OR_Admin.LogoutBtn)
	public WebElement LogoutBtn;

	@FindBy(xpath = OR_Admin.PropertyName)
	public WebElement PropertyName;

	@FindBy(xpath = OR.clientDetailsInventorySubSystemCheckBox)
	public WebElement clientDetailsInventorySubSystemCheckBox;

	@FindBy(xpath = OR.clientDetailsBothCheckBox)
	public WebElement clientDetailsBothCheckBox;

	@FindBy(xpath = OR_Admin.CLICK_CLIENT)
	public WebElement clickClient;

	@FindBy(xpath = OR_Admin.GET_DEFAUL_CURRENCY)
	public WebElement getDefaultCurrency;

	@FindBy(xpath = OR_Admin.GET_DATE_FORMAT)
	public WebElement getDateFormat;

	@FindBy(xpath = OR_Admin.CLICK_CLIENT_OPTION)
	public WebElement clickClientOption;

	@FindBy(xpath = OR_Admin.SELECT_DATE_FORMAT)
	public WebElement selectDateFormat;
	
	@FindBy(xpath = OR_Admin.ADMIN_SAVE_BUTTON)
	public WebElement adminSaveButton;
	
	@FindBy(xpath = OR_Admin.ADMIN_DONE_BUTTON)
	public WebElement adminDoneButton;

}