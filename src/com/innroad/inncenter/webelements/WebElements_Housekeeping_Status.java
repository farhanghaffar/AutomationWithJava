package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class WebElements_Housekeeping_Status {

	WebDriver driver;
	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public WebElements_Housekeeping_Status(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);

	}

	@FindBy(xpath = OR.Select_Items_Per_Page)
	public WebElement Select_Items_Per_Page;

	@FindBy(xpath = OR.Select_Rmcondition_In_Theader)
	public WebElement Select_Rmcondition_In_Theader;

	@FindBy(xpath = OR.Save_Housekeeping_Status)
	public WebElement Save_Housekeeping_Status;

	@FindBy(xpath = OR.HK_SelectRoomClass)
	public WebElement HK_SelectRoomClass;

	@FindBy(xpath = OR.HK_GoButton)
	public WebElement HK_GoButton;

	@FindBy(xpath = OR.Housekeeping_Room_Class)
	public WebElement Housekeeping_Room_Class;

	@FindBy(xpath = OR.Housekeeping_Date)
	public WebElement Housekeeping_Date;

	// House Keeping Status Room Status

	@FindBy(id = OR.Housekeeping_RoomStatus_Vacant)
	public WebElement Housekeeping_RoomStatus_Vacant;

	@FindBy(id = OR.Housekeeping_RoomStatus_Occupied)
	public WebElement Housekeeping_RoomStatus_Occupied;

	@FindBy(id = OR.Housekeeping_RoomStatus_OutofOrder)
	public WebElement Housekeeping_RoomStatus_OutofOrder;

	// House Keeping Status Room Condition

	@FindBy(id = OR.Housekeeping_RoomCondition_Clean)
	public WebElement Housekeeping_RoomCondition_Clean;

	@FindBy(id = OR.Housekeeping_RoomCondition_Dirty)
	public WebElement Housekeeping_RoomCondition_Dirty;

	@FindBy(id = OR.Housekeeping_RoomCondition_Inspected)
	public WebElement Housekeeping_RoomCondition_Inspected;

	@FindBy(id = OR.Housekeeping_RoomCondition_None)
	public WebElement Housekeeping_RoomCondition_None;

	// House Keeping Status Due In

	@FindBy(id = OR.Housekeeping_DueIn_Yes)
	public WebElement Housekeeping_DueIn_Yes;

	@FindBy(id = OR.Housekeeping_DueIn_No)
	public WebElement Housekeeping_DueIn_No;

	// House Keeping Status Zone

	@FindBy(id = OR.Housekeeping_Zone)
	public WebElement Housekeeping_Zone;

	// House Keeping Status Group By

	@FindBy(id = OR.Housekeeping_GroupBy)
	public WebElement Housekeeping_GroupBy;

	// Go Button
	@FindBy(id = OR.Housekeeping_GoButton)
	public WebElement Housekeeping_GoButton;
	// House Keeping Status Columns

	@FindBy(id = OR.Housekeeping_Room_Column)
	public WebElement Housekeeping_Room_Column;

	// House Keeping Status PageHyperLink

	@FindBy(xpath = OR.Housekeeping_PageHyperLink)
	public WebElement Housekeeping_PageHyperLink;

	// Go Button
	@FindBy(id = OR.Housekeeping_SaveButton)
	public WebElement Housekeeping_SaveButton;

	// Go Button
	@FindBy(id = OR.Housekeeping_CancelButton)
	public WebElement Housekeeping_CancelButton;

	@FindBy(xpath = OR.Housekeeping_TotalItems_SelectedProperty)
	public WebElement Housekeeping_TotalItems_SelectedProperty;

	@FindBy(xpath = OR.HouseKeeping_SelectItemPerPage)
	public WebElement HouseKeeping_SelectItemPerPage;

	// Print Button
	@FindBy(id = OR.Housekeeping_PrintButton)
	public WebElement Housekeeping_PrintButton;

	// PDF Report
	@FindBy(id = OR.Housekeeping_PDFReport)
	public WebElement Housekeeping_PDFReport;
}
