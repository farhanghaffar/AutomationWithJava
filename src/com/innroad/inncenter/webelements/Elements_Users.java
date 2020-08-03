package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class Elements_Users {

WebDriver driver ;
	
	public static Logger app_logs = Logger.getLogger("devpinoyLogger");
	
	public Elements_Users(WebDriver driver2)
	{
		this.driver=driver2;
		PageFactory.initElements(this.driver, this);

	}
	
	@FindBy(xpath=OR.New_User_Btn)
	public WebElement New_User_Btn;
	
	@FindBy(xpath=OR.Users_Search_Btn)
	public WebElement Users_Search_Btn;
	
	@FindBy(xpath=OR.First_Element_In_Users_SearchResults)
	public WebElement First_Element_In_Users_SearchResults;
	
	@FindBy(xpath=OR.Enter_User_LoginId)
	public WebElement Enter_User_LoginId;
	
	@FindBy(xpath=OR.Users_Reset_Btn)
	public WebElement Users_Reset_Btn;
	
	
	@FindBy(xpath=OR.UserFirstName)
	public WebElement UserFirstName;
	
	@FindBy(xpath=OR.UserLastName)
	public WebElement UserLastName;
	
	@FindBy(xpath=OR.UserLogin)
	public WebElement UserLogin;
	
	@FindBy(xpath=OR.UserEmail)
	public WebElement UserEmail;
	
	@FindBy(xpath=OR.User_Reset_Btn)
	public WebElement User_Reset_Btn;
	
	@FindBy(xpath=OR.User_Save_Btn)
	public WebElement User_Save_Btn;
	
	@FindBy(xpath=OR.User_ResetPassword_Btn)
	public WebElement User_ResetPassword_Btn;
	
	@FindBy(xpath=OR.User_AssociateRole_Btn)
	public WebElement User_AssociateRole_Btn;
	
	@FindBy(xpath=OR.User_AssociateProperties_Btn)
	public WebElement User_AssociateProperties_Btn;
	
	@FindBy(xpath=OR.RolePicker_popup)
	public WebElement AssociateRole_popup;
	
	@FindBy(xpath=OR.AddAssociateRole)
	public WebElement AddAssociateRole;
	
	@FindBy(xpath=OR.SelectAssociateRole)
	public WebElement SelectAssociateRole;
	
	@FindBy(xpath=OR.SelectedAssociateRole)
	public WebElement SelectedAssociateRole;
	
	@FindBy(xpath=OR.User_AssociateRole_Done)
	public WebElement User_AssociateRole_Done;
	
	@FindBy(xpath=OR.PropertyPicker_popup)
	public WebElement PropertyPicker_popup;
	
	@FindBy(xpath=OR.AddAssociateProperty)
	public WebElement AddAssociateProperty;
	
	@FindBy(xpath=OR.SelectAssociateProperty)
	public WebElement SelectAssociateProperty;
	
	@FindBy(xpath=OR.User_AssociateProperty_Done)
	public WebElement User_AssociateProperty_Done;
	
	@FindBy(xpath=OR.CurrentUser)
	public WebElement CurrentUser;
	
	@FindBy(xpath=OR.Logout)
	public WebElement Logout;
	
	@FindBy(xpath=OR.logoutLink)
	public WebElement logoutLink;
	
	@FindBy(className = OR.Toaster_Title)
	public WebElement Toaster_Title;

	@FindBy(className = OR.Toaster_Message)
	public WebElement Toaster_Message;

	
	@FindBy(xpath = OR.CloseNewUserTab)
	public WebElement CloseNewUserTab;
	
	@FindBy(xpath = OR.Search_LastName)
	public WebElement Search_LastName;
	
	@FindBy(xpath = OR.Search_FirstName)
	public WebElement Search_FirstName;
	
	@FindBy(xpath = OR.Search_LoginId)
	public WebElement Search_LoginId;
	
	@FindBy(xpath = OR.Search_Email)
	public WebElement Search_Email;
	
	@FindBy(xpath = OR.User_SearchButton)
	public WebElement User_SearchButton;
	
	@FindBy(xpath = OR.VerifySearch)
	public WebElement VerifySearch;
	
	@FindBy(xpath = OR.Select_Status)
	public WebElement Select_Status;
	
	@FindBy(xpath = OR.Select_AllProperties_Checkbox)
	public WebElement Select_AllProperties_Checkbox;
	
	
	@FindBy(xpath = OR.SetNewPassword_Title)
	public WebElement SetNewPassword_Title;
	
	@FindBy(xpath = OR.SetNewPassword_NewPasswordInputField)
	public WebElement SetNewPassword_NewPasswordInputField;
	
	@FindBy(xpath = OR.SetNewPassword_ConfirmPasswordInputField)
	public WebElement SetNewPassword_ConfirmPasswordInputField;
	
	@FindBy(xpath = OR.SetNewPassword_SubmitButton)
	public WebElement SetNewPassword_SubmitButton;
	
	@FindBy(xpath = OR.Relogin_Message_LoginPage)
	public WebElement Relogin_Message_LoginPage;
	
		@FindBy(xpath = OR.UserPage_SelectStatus)
	public WebElement UserPage_SelectStatus;

	@FindBy(xpath = OR.UserPage_SearchedUser_Status)
	public WebElement UserPage_SearchedUser_Status;
	
}
