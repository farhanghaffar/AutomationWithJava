package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.pages.NewLedgerAccount;
import com.innroad.inncenter.pages.NewListManagement;
import com.innroad.inncenter.properties.OR;

public class Elements_ListManagement {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_ListManagement(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);

	}

	@FindBy(xpath = NewListManagement.FilterElementList)
	public List<WebElement> FilterElementList;

	@FindBy(xpath = NewListManagement.SystemItemTextField)
	public WebElement SystemItemTextField;

	@FindBy(xpath = NewListManagement.SaveButton)
	public WebElement SaveButton;

	@FindBy(xpath = NewListManagement.NewItemButton)
	public WebElement NewItemButton;

	@FindBy(xpath = NewListManagement.EditButton)
	public WebElement EditButton;

	@FindBy(xpath = NewListManagement.DeleteButton)
	public WebElement DeleteButton;

	@FindBy(xpath = NewListManagement.CancelButton)
	public WebElement CancelButton;

	@FindBy(xpath = NewListManagement.CustomItemListSize)
	public List<WebElement> CustomItemListSize;

	@FindBy(xpath = NewListManagement.SelectStatus)
	public WebElement SelectStatus;

	@FindBy(xpath = NewListManagement.RatePlanInActiveCustomListCheckbox)
	public WebElement RatePlanInActiveCustomListCheckbox;

	@FindBy(xpath = NewListManagement.Newitem_Name)
	public WebElement Newitem_Name;

	@FindBy(xpath = NewListManagement.Newitem_Description)
	public WebElement Newitem_Description;
	
	@FindBy(xpath = NewListManagement.RatePlan_Name)
	public WebElement RatePlan_Name;

	@FindBy(xpath = NewListManagement.RatePlan_Description)
	public WebElement RatePlan_Description;
	
	@FindBy(xpath = NewListManagement.RatePlan_Status)
	public WebElement RatePlan_Status;
	
	@FindBy(xpath = NewListManagement.CustomRatePlan_NameList)
	public WebElement CustomRatePlan_NameList;
	
	@FindBy(xpath = NewListManagement.ClickRatePlanLink)
	public WebElement clickRatePlanLink;
}