package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.pages.NewPolicies;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Inventory;
import com.innroad.inncenter.properties.OR_Setup;

public class WebElements_Policies {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public WebElements_Policies(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);

	}

	@FindBy(xpath = OR.Policy_Name_On_Policiespage)
	public WebElement Policy_Name_On_Policiespage;

	@FindBy(xpath = OR.Policy_TableShow)
	public WebElement Policy_TableShow;

	@FindBy(xpath = OR.Search_On_On_Policiespage)
	public WebElement Search_On_On_Policiespage;

	@FindBy(xpath = OR.First_Element_In_Search_Result)
	public WebElement First_Element_In_Search_Result;

	@FindBy(xpath = OR.Select_Policy_Type)
	public WebElement Select_Policy_Type;

	@FindBy(xpath = OR.SelectPolicyType)
	public WebElement SelectPolicyType;

	@FindBy(xpath = OR.New_Policy_Btn)
	public WebElement New_Policy_Btn;

	@FindBy(xpath = OR.Enter_Policy_Name)
	public WebElement Enter_Policy_Name;

	@FindBy(xpath = OR.Select_Deposit_Roomcharges_Radiobtn)
	public WebElement Select_Deposit_Roomcharges_Radiobtn;

	@FindBy(xpath = OR.Select_Deposit_Fixedamount_Radiobtn)
	public WebElement Select_Deposit_Fixedamount_Radiobtn;

	@FindBy(xpath = OR.Select_Deposit_Firstnightrc_Radiobtn)
	public WebElement Select_Deposit_Firstnightrc_Radiobtn;

	@FindBy(xpath = OR.Select_Noshow_Roomcharges_Radiobtn)
	public WebElement Select_Noshow_Roomcharges_Radiobtn;

	@FindBy(xpath = OR.Select_Noshow_Fixedamount_Radiobtn)
	public WebElement Select_Noshow_Fixedamount_Radiobtn;

	@FindBy(xpath = OR.Select_Noshow_Firstnightrc_Radiobtn)
	public WebElement Select_Noshow_Firstnightrc_Radiobtn;
	
	@FindBy(xpath = NewPolicies.Select_Cancellation_Roomcharges_Radiobtn)
	public WebElement Select_Cancellation_Roomcharges_Radiobtn;
	
	@FindBy(xpath = NewPolicies.Select_Cancellation_Roomcharges_Percentage)
	public WebElement Select_Cancellation_Roomcharges_Percentage;

	@FindBy(xpath = NewPolicies.Select_Cancellation_Fixedamount_Radiobtn)
	public WebElement Select_Cancellation_Fixedamount_Radiobtn;

	@FindBy(xpath = NewPolicies.Select_Cancellation_Firstnightrc_Radiobtn)
	public WebElement Select_Cancellation_Firstnightrc_Radiobtn;
	
	@FindBy(xpath = NewPolicies.Select_Cancellation_Beyond_Radiobtn)
	public WebElement Select_Cancellation_Beyond_Radiobtn;

	@FindBy(xpath = NewPolicies.Select_Cancellation_Beyond_NumberOfDays)
	public WebElement Select_Cancellation_Beyond_NumberOfDays;

	@FindBy(xpath = NewPolicies.Select_Cancellation_Within_Radiobtn)
	public WebElement Select_Cancellation_Within_Radiobtn;
	
	@FindBy(xpath = NewPolicies.Select_Cancellation_Within_NumberOfDays)
	public WebElement Select_Cancellation_Within_NumberOfDays;

	@FindBy(xpath = NewPolicies.Select_Cancellation_Fixedamount_Amount)
	public WebElement Select_Cancellation_Fixedamount_Amount;

	@FindBy(xpath = NewPolicies.Select_Cancellation_Firstnightrc_NumberOfNights)
	public WebElement Select_Cancellation_Firstnightrc_NumberOfNights;

	@FindBy(xpath = OR.Select_Checkin_Roomcharges_Radiobtn)
	public WebElement Select_Checkin_Roomcharges_Radiobtn;

	@FindBy(xpath = OR.Select_Checkin_Fixedamount_Radiobtn)
	public WebElement Select_Checkin_Fixedamount_Radiobtn;

	@FindBy(xpath = OR.Select_Roomcharges_Type_for_Deposit)
	public WebElement Select_Checkin_Firstnightrc_Radiobtn;

	@FindBy(xpath = OR.Select_Roomcharges_Type_for_Deposit)
	public WebElement Select_Roomcharges_Type_for_Deposit;

	@FindBy(xpath = OR.Select_Roomcharges_Type_for_Cancellation)
	public WebElement Select_Roomcharges_Type_for_Cancellation;

	@FindBy(xpath = OR.Select_Roomcharges_Type_for_Noshow)
	public WebElement Select_Roomcharges_Type_for_Noshow;

	@FindBy(xpath = OR.Enter_Deposit_Percentage_Charges)
	public WebElement Enter_Deposit_Percentage_Charges;

	@FindBy(xpath = OR.Enter_Deposit_Fixed_Amount)
	public WebElement Enter_Deposit_Fixed_Amount;

	@FindBy(xpath = OR.Enter_Deposit_First_Nights_RC)
	public WebElement Enter_Deposit_First_Nights_RC;

	@FindBy(xpath = OR.Enter_Noshow_Percentage_Charges)
	public WebElement Enter_Noshow_Percentage_Charges;

	@FindBy(xpath = OR.Enter_Noshow_Fixed_Amount)
	public WebElement Enter_Noshow_Fixed_Amount;

	@FindBy(xpath = OR.Enter_Noshow_First_Nights_RC)
	public WebElement Enter_Noshow_First_Nights_RC;

	@FindBy(xpath = OR.Select_Capture_Payment_for_Checkin)
	public WebElement Select_Capture_Payment_for_Checkin;

	@FindBy(xpath = OR.Select_Authorize_Payment_for_Checkin)
	public WebElement Select_Authorize_Payment_for_Checkin;

	@FindBy(xpath = OR.Enter_Checkin_Percentage_On_balance)
	public WebElement Enter_Checkin_Percentage_On_balance;

	@FindBy(xpath = OR.Select_Capture_Payment_for_Checkin_1)
	public WebElement Select_Capture_Payment_for_Checkin_1;

	@FindBy(xpath = OR.Select_Authorize_Payment_for_Checkin_1)
	public WebElement Select_Authorize_Payment_for_Checkin_1;

	@FindBy(xpath = OR.Enter_Checkin_Percentage_On_balance_1)
	public WebElement Enter_Checkin_Percentage_On_balance_1;

	@FindBy(xpath = OR.Enter_Checkin_Percentage_On_balance_Authorize_1)
	public WebElement Enter_Checkin_Percentage_On_balance_Authorize_1;

	@FindBy(xpath = OR.Enter_Policy_Text)
	public WebElement Enter_Policy_Text;

	@FindBy(xpath = OR.Enter_Policy_Description)
	public WebElement Enter_Policy_Description;

	@FindBy(xpath = OR.Associate_Sources_Btn)
	public WebElement Associate_Sources_Btn;

	@FindBy(xpath = OR.Associate_Seasons_Btn)
	public WebElement Associate_Seasons_Btn;

	@FindBy(xpath = OR.Associate_Room_Classes_Btn)
	public WebElement Associate_Room_Classes_Btn;

	@FindBy(xpath = OR.Associate_Rate_Plans_Btn)
	public WebElement Associate_Rate_Plans_Btn;

	@FindBy(xpath = OR.Associate_Assign_All_Btn)
	public WebElement Assoociate_Assign_All_Btn;
	
	@FindBy(xpath = OR.Associate_Assign_One_Btn)
	public WebElement Assoociate_Assign_One_Btn;
	
	@FindBy(xpath = OR.Select_Available_Options_In_Popup)
	public WebElement Select_Available_Options_In_Popup;

	@FindBy(xpath = OR.Done_In_Popup)
	public WebElement Done_In_Popup;

	@FindBy(xpath = OR.Policy_Save)
	public WebElement Policy_Save;
	
	@FindBy (xpath = OR.CloseOpenedTab)
	public WebElement CloseOpenedTab;

	@FindBy(xpath = OR.Verify_Toaster_Container)
	public WebElement Verify_Toaster_Container;

	@FindBy(className = OR.Toaster_Title)
	public WebElement Toaster_Title;

	@FindBy(className = OR.Toaster_Message)
	public WebElement Toaster_Message;

	@FindBy(xpath = OR.Policy_Close_Btn)
	public WebElement Policy_Close_Btn;

	@FindBy(xpath = OR.Delete_Policy_Checkbox)
	public WebElement Delete_Policy_Checkbox;

	@FindBy(xpath = OR.Delete_Policy_Btn)
	public WebElement Delete_Policy_Btn;

	@FindBy(xpath = OR.Policy_Reset_Btn)
	public WebElement Policy_Reset_Btn;

	@FindBy(xpath = OR.Policy_FirstActiveClass)
	public WebElement Policy_FirstActiveClass;

	@FindBy(xpath = OR.Policy_PolicyDetailsPage)
	public WebElement Policy_PolicyDetailsPage;
	
	
	@FindBy(xpath = OR.ClosePolicyTab)
	public WebElement ClosePolicyTab;
	
	@FindBy(xpath = OR.PolicyAttributePercentage_Checkbox)
	public WebElement PolicyAttributePercentage_Checkbox;
	
	@FindBy(xpath = NewPolicies.PolicyName_Validation)
	public WebElement PolicyName_Validation;
	
	@FindBy(xpath = NewPolicies.PolicyAttributes_Validation)
	public WebElement PolicyAttributes_Validation;
	
	@FindBy(xpath = NewPolicies.PolicyText_Validation)
	public WebElement PolicyText_Validation;
	
	@FindBy(xpath = NewPolicies.PolicyAssociateSource_Validation)
	public WebElement PolicyAssociateSource_Validation;
	
	@FindBy(xpath = NewPolicies.PolicyAssociateRoomClass_Validation)
	public WebElement PolicyAssociateRoomClass_Validation;
	
	@FindBy(xpath = NewPolicies.PolicyAssociateSeasons_Validation)
	public WebElement PolicyAssociateSeasons_Validation;
	
	@FindBy(xpath = NewPolicies.PolicyAssociateRatePlan_Validation)
	public WebElement PolicyAssociateRatePlan_Validation;

	@FindBy(xpath = NewPolicies.Selec_Attribute)
	public WebElement Selec_Attribute;
	
	@FindBy(xpath = NewPolicies.AddButton)
	public WebElement AddButton;
	
	
	@FindBy(xpath = NewPolicies.PolicyCheckbox)
	public WebElement PolicyCheckbox;
	
	@FindBy(xpath = NewPolicies.PolicyCheckbox)
	public List<WebElement> List_PolicyCheckbox;
	
	@FindBy(xpath = OR.PolicySearch_ModuleLoading)
	public WebElement PolicySearch_ModuleLoading;
	
	@FindBy(xpath = NewPolicies.NumberofRecordPerPage)
	public WebElement NumberofRecordPerPage;
	
	@FindBy(xpath = NewPolicies.Select_PolicyType)
	public WebElement Select_PolicyType;
	
	@FindBy(xpath = OR.LoginModuleLoding)
	public WebElement LoginModuleLoding;
	
	@FindBy(xpath = OR_Inventory.Select_PolicyType)
	public WebElement Select_PolicyTypeDropDownBox;
	
	@FindBy(xpath = OR_Inventory.Table_PolicyType_Column)
	public List<WebElement> Table_PolicyTypeColumn;

	@FindBy(xpath = OR.selectPoliciesPerPage)
	public WebElement selectPoliciesPerPage;
	
	@FindBy(xpath = NewPolicies.Select_NoShow_Roomcharges_Percentage)
	public WebElement Select_NoShow_Roomcharges_Percentage;
	
	@FindBy(xpath=OR.CANCELATION_POLICIES)
	public List<WebElement> cancelationPolices;
	
	@FindBy(xpath=OR.DEPOSITE_POLICES)
	public List<WebElement> depositePolices;
	
	@FindBy(xpath=OR.CHECK_IN_POLICIES)
	public List<WebElement> checkInPolicies;
	
	@FindBy(xpath=OR.NO_SHOW_POLICIES)
	public List<WebElement> noShowPolicies;
		
}

