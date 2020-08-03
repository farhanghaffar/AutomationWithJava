package com.innroad.inncenter.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.pages.Page_TaskManagement;
import com.innroad.inncenter.properties.OR;

public class Elements_TaskManagement {

	WebDriver driver;
	public Elements_TaskManagement(WebDriver driver2)
	{
		this.driver=driver2;
		PageFactory.initElements(this.driver, this);
		
		 
	}

	@FindBy(xpath=OR.Toaster_Message_Close)
	public WebElement Toaster_Message_Close;
	
	@FindBy(xpath=OR.NewCategory)
	public WebElement NewCategory;
	
	@FindBy(xpath=OR.NewCategory_Section)
	public WebElement NewCategory_Section;
	
	@FindBy(xpath=OR.AddTaskCategory)
	public WebElement AddTaskCategory;
	
	@FindBy(xpath=OR.SaveCategory)
	public WebElement SaveCategory;
	
	@FindBy(xpath=OR.CategoryAlreadyExist)
	public WebElement CategoryAlreadyExist;
	
	@FindBy(xpath=OR.CategoriesNames)
	public WebElement CategoriesNames;
	
	@FindBy(xpath=OR.CreateTaskType)
	public WebElement CreateTaskType;
	
	@FindBy(xpath=OR.CreateTaskType_Section)
	public WebElement CreateTaskType_Section;
	
	@FindBy(xpath=OR.TaskTypeName)
	public WebElement TaskTypeName;
	
	@FindBy(xpath=OR.SaveTask)
	public WebElement SaveTask;

	@FindBy (xpath = OR.TaskAlreadyExist)
	public WebElement TaskAlreadyExist;
	
	@FindBy(xpath=OR.Dialog)
	public WebElement Dialog;
	
	@FindBy(xpath=OR.Delete)
	public WebElement Delete;
	
	@FindBy(xpath=OR.InspectionFullCleaning_Toggle)
	public WebElement InspectionFullCleaning_Toggle;
	
	@FindBy(xpath=OR.SetRoomsToDirty_Toggle)
	public WebElement SetRoomsToDirty_Toggle;

	@FindBy(id=Page_TaskManagement.TaskManagement_tab)
	public WebElement TaskManagement_tab;
	
	@FindBy(xpath=Page_TaskManagement.AddNewTask)
	public WebElement AddNewTask;
	
	@FindBy(xpath=Page_TaskManagement.Enter_NewTaskName)
	public WebElement Enter_NewTaskName;
	
	@FindBy(xpath=Page_TaskManagement.TaskSaveButton)
	public WebElement TaskSaveButton;
	
	@FindBy(className=OR.Toaster_Message)
	public WebElement Toaster_Message;
	
	@FindBy(xpath = OR.ToastCloseButton)
	public WebElement ToastCloseButton;
	
	@FindBy(xpath=Page_TaskManagement.FrontDesk_Title)
	public WebElement FrontDesk_Title;

}
