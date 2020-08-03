package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.common.XPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.pages.NewReservation;
import com.innroad.inncenter.properties.OR;



public class Elements_Reservation_CopyPage {



WebDriver driver ;
	
	public static Logger app_logs = Logger.getLogger("devpinoyLogger");
	
	public Elements_Reservation_CopyPage(WebDriver driver2)
	{
		this.driver=driver2;
		PageFactory.initElements(this.driver, this);
		
	}
	
	@FindBy(xpath=OR.click_SearchReservation_Button)
	public WebElement click_SearchReservation_Button;
	
	@FindBy(xpath=OR.Get_Guest_Name)
	public WebElement Get_Guest_Name;
	
	@FindBy(xpath=OR.BasicGuestName)
	public WebElement BasicGuestName;

	@FindBy(xpath=OR.Click_BasicSearch)
	public WebElement Click_BasicSearch;
	
	@FindBy(xpath=OR.Verify_Search_Loading_Gird)
	public WebElement Verify_Search_Loading_Gird;
	
	@FindBy(xpath=OR.Basic_Res_Number)
	public WebElement Basic_Res_Number;
	
	@FindBy(xpath=OR.Get_Res_Number)
	public WebElement Get_Res_Number;
	
	@FindBy(xpath=OR.Check_Reservation)
	public WebElement Check_Reservation;
	
	@FindBy(xpath=OR.click_Reservation_GuestName)
	public WebElement click_Reservation_GuestName;

	@FindBy(xpath=OR.click_Copy_Button)
	public WebElement click_Copy_Button; 
	
	@FindBy(xpath=OR.close_Copied_Reservation)
	public WebElement close_Copied_Reservation;
	
	@FindBy(xpath=OR.get_Copied_Guest_Name)
	public WebElement get_Copied_Guest_Name;
	
	@FindBy(xpath=OR.get_Copied_ReservationTab)
	public WebElement get_Copied_ReservationTab;

	@FindBy(xpath=OR.get_Searched_GuestName)
	public WebElement get_Searched_GuestName;

	@FindBy(xpath=OR.click_RoomPicker)
	public WebElement click_RoomPicker;

	@FindBy(xpath=OR.check_AssignRoom_ChecBox)
	public WebElement check_AssignRoom_ChecBox;
	
	@FindBy(xpath=OR.click_Search_Button)
	public WebElement click_Search_Button;
	
	@FindBy(xpath=OR.get_Room_Number)
	public WebElement get_Room_Number;

	@FindBy(xpath=OR.click_Select_Button_RoomAssignment)
	public WebElement click_Select_Button_RoomAssignment;

	@FindBy(xpath=OR.click_Select_Button_RoomChargesChanged)
	public WebElement click_Select_Button_RoomChargesChanged;	
	
	@FindBy(xpath=OR.click_Save_button_ReservationCopy)
	public WebElement click_Save_button_ReservationCopy;

	@FindBy(xpath=OR.getUnassignedRoomNumber)
	public WebElement getUnassignedRoomNumber;
	
	@FindBy(xpath=OR.click_cancel_button)
	public WebElement click_cancel_button;

	@FindBy(xpath=OR.click_copiedRoomPicker)
	public WebElement click_copiedRoomPicker;
	
	@FindBy(xpath=OR.Get_RoomClass_Status_CopyUnassigned)
	public WebElement Get_RoomClass_Status_CopyUnassigned;
	
	@FindBy(xpath=OR.Select_Room_Number)
	public WebElement Select_Room_Number;
	
	@FindBy(xpath=OR.Validating_UnAssgined_DDL)
	public WebElement Validating_UnAssgined_DDL;

	@FindBy(xpath=OR.Select_Room_Number2)
	public WebElement Select_Room_Number2;

	@FindBy(xpath=OR.get_copiedRoom_Number)
	public WebElement get_copiedRoom_Number;

	@FindBy(xpath=OR.click_room_picker_afterCopy)
	public WebElement click_room_picker_afterCopy;

	@FindBy(xpath=OR.get_copied_unassignedRoomNo)
	public WebElement get_copied_unassignedRoomNo;

	@FindBy(xpath=OR.click_Select_Button_UnassignedRoomAssignment)
	public WebElement click_Select_Button_UnassignedRoomAssignment;

	@FindBy(xpath=OR.click_room_picker_afterCopyUnassignedRoom)
	public WebElement click_room_picker_afterCopyUnassignedRoom;
	
	
	@FindBy(xpath=OR.click_cancel_unassigned_room)
	public WebElement click_cancel_unassigned_room;
	
	@FindBy(xpath=OR.getunassignedRoomNumberCopy)
	public WebElement getunassignedRoomNumberCopy;

	@FindBy(xpath = NewReservation.OverbookingPopup)
	public WebElement OverbookingPopup;
	
	@FindBy(xpath = NewReservation.OverbookingPopup_Continue)
	public WebElement OverbookingPopup_Continue;
	
	@FindBy(xpath=OR.switchToFolio2)
	public WebElement switchToFolio2;
	
	@FindBy(xpath=OR.Copied_First_Name)
	public WebElement Copied_First_Name;
	
	@FindBy(xpath = OR.Copied_Line1)
	public WebElement Copied_Line1;
	
	@FindBy(xpath = OR.Copied_Last_Name)
	public WebElement Copied_Last_Name;
	
	@FindBy(xpath = OR.Copied_City)
	public WebElement Copied_City;
	
	@FindBy(xpath = OR.CopiedSelect_State)
	public WebElement CopiedSelect_State;
	
	@FindBy(xpath = OR.CopiedSelect_Country)
	public WebElement CopiedSelect_Country;
	
	@FindBy(xpath = OR.Copied_Postal_Code)
	public WebElement Copied_Postal_Code;
	
	@FindBy(xpath = OR.Copied_Phone_Number)
	public WebElement Copied_Phone_Number;
	
	@FindBy(xpath = OR.Copied_email)
	public WebElement Copied_email;
}
