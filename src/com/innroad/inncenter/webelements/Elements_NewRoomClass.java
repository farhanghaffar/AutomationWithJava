package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.pages.NewRoomClass;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Setup;

public class Elements_NewRoomClass {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_NewRoomClass(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = OR.NewRoomClassAbb)
	public WebElement NewRoomClassAbb;

	@FindBy(xpath = OR.NewRoomClassName)
	public WebElement NewRoomClass_Name;

	@FindBy(xpath = OR.SearchRoomClass)
	public WebElement SearchRoomClass;

	@FindBy(xpath = OR.NewRoomClassMaxAdults)
	public WebElement NewRoomClass_MaxAdults;

	@FindBy(xpath = OR.NewRoomClassMaxPersons)
	public WebElement NewRoomClass_MaxPersons;

	@FindBy(xpath = OR.NewRoomClassPublish)
	public WebElement NewRoomClassPublishButton;

	@FindBy(xpath = OR.NewRoomClass_TripAdvisorTab)
	public WebElement NewRoomClass_TripAdvisorTab;

	@FindBy(xpath = OR.Amenities)
	public WebElement Amenities;

	@FindBy(xpath = OR.Amenities1)
	public WebElement Amenities1;

	@FindBy(xpath = OR.Amenities2)
	public WebElement Amenities2;

	@FindBy(xpath = OR.New_RoomClass_Name)
	public WebElement NewRoomClassName;

	@FindBy(xpath = OR.New_RoomClass_Abbrivation)
	public WebElement NewRoomClassAbbrivation;

	@FindBy(xpath = OR.New_RoomClass_KingBeds)
	public WebElement NewRoomClassKingBeds;

	@FindBy(xpath = OR.New_RoomClass_RoomType_AdjoiningRooms)
	public WebElement New_RoomClassRoomTypeAdjoiningRooms;

	@FindBy(xpath = OR.New_RoomClass_Rooms)
	public WebElement NewRoomClassRooms;

	@FindBy(xpath = OR.New_RoomClass_Max_Adults)
	public WebElement NewRoomClassMaxAdults;

	@FindBy(xpath = OR.New_RoomCLass_Max_Persons)
	public WebElement NewRoomClassMaxPersons;

	@FindBy(xpath = OR.New_RoomClass_Room_Quantity)
	public WebElement NewRoomClassRoomQuantity;

	@FindBy(xpath = OR.New_RoomClass_Create_Rooms)
	public WebElement NewRoomClassCreateRooms;

	@FindBy(xpath = OR.New_RoomClass_RoomNumber)
	public WebElement NewRoomClassRoomNumber;

	@FindBy(xpath = OR.New_RoomClass_AssignRoomNumber)
	public WebElement NewRoomClassAssignRoomNumbers;

	@FindBy(xpath = OR.New_RoomClass_Save)
	public WebElement NewRoomClassSave;

	@FindBy(xpath = OR.Toaster_Message_Close)
	public WebElement Toaster_Message_Close;

	@FindBy(xpath = OR.New_RoomClass_Publish)
	public WebElement NewRoomClassPublish;

	@FindBy(xpath = OR.New_RoomClass_OK)
	public WebElement NewRoomClassOk;

	@FindBy(xpath = OR.New_RoomClasses)
	public WebElement NewRoomClasses;

	@FindBy(xpath = OR.New_RoomClasses_1)
	public WebElement NewRoomClasses_1;

	@FindBy(xpath = OR.New_RoomClass_Msg_Publish)
	public WebElement NewRoomClassesMsgAfterPublish;

	@FindBy(xpath = OR.AddVirtualRoom)
	public WebElement AddVirtualRoom;

	@FindBy(xpath = OR.CreateVirtualRoom_Text)
	public WebElement CreateVirtualRoom_Text;

	@FindBy(xpath = OR.CreateVirtualRoom_RoomNumber)
	public WebElement CreateVirtualRoom_RoomNumber;

	@FindBy(xpath = OR.CreateVirtualRoom_Adults)
	public WebElement CreateVirtualRoom_Adults;

	@FindBy(xpath = OR.CreateVirtualRoom_Persons)
	public WebElement CreateVirtualRoom_Persons;

	@FindBy(xpath = OR.CreateVirtualRoom_SearchButton)
	public WebElement CreateVirtualRoom_SearchButton;

	@FindBy(className = OR.CreateVirtualRoom_PhysicalRoomBox)
	public WebElement CreateVirtualRoom_PhysicalRoomBox;

	@FindBy(xpath = OR.CreateVirtualRoom_PhysicalRoom_RoomList)
	public List<WebElement> CreateVirtualRoom_PhysicalRoom_RoomList;

	@FindBy(xpath = OR.PhysicalRoom_RoomList)
	public WebElement PhysicalRoom_RoomList;

	@FindBy(xpath = OR.CreateVirtualRoom_VirtualRoomSpace)
	public WebElement CreateVirtualRoom_VirtualRoomSpace;

	@FindBy(xpath = OR.CreateVirtualRoom_VirtualRoomName)
	public WebElement CreateVirtualRoom_VirtualRoomName;

	@FindBy(xpath = OR.CreateVirtualRoom_VirtualSortOrder)
	public WebElement CreateVirtualRoom_VirtualSortOrder;

	@FindBy(xpath = OR.RoomClasses_SearchButton)
	public WebElement RoomClasses_SearchButton;

	@FindBy(xpath = OR.RoomClasses_TableShow)
	public WebElement RoomClasses_TableShow;

	@FindBy(xpath = OR.RoomClasses_FirstActiveClass)
	public WebElement RoomClasses_FirstActiveClass;

	@FindBy(xpath = OR.RoomClasses_RoomDetailsPage)
	public WebElement RoomClasses_RoomDetailsPage;

	@FindBy(xpath = OR.RoomClasses_ChangesSaved_OKButton)
	public WebElement RoomClasses_ChangesSaved_OKButton;

	// New layout
	@FindBy(xpath = OR.ListingName)
	public WebElement ListingName;

	@FindBy(xpath = OR.PropertyTypeGroup)
	public WebElement PropertyTypeGroup;

	@FindBy(xpath = OR.PropertyType)
	public WebElement PropertyType;

	@FindBy(xpath = OR.RoomType)
	public WebElement RoomType;

	@FindBy(xpath = OR.ListingSummary)
	public WebElement ListingSummary;

	@FindBy(xpath = OR.New_RoomClass_Name1)
	public WebElement NewRoomClassName1;

	@FindBy(xpath = OR.New_RoomClass_Abbrivation1)
	public WebElement NewRoomClassAbbrivation1;

	@FindBy(xpath = OR.New_RoomClass_KingBeds1)
	public WebElement NewRoomClassKingBeds1;

	// @FindBy(xpath = OR.New_RoomClass_RoomType_AdjoiningRooms)
	// public WebElement New_RoomClassRoomTypeAdjoiningRooms;

	@FindBy(xpath = OR.New_RoomClass_Rooms1)
	public WebElement NewRoomClassRooms1;

	@FindBy(xpath = OR.New_RoomClass_Max_Adults1)
	public WebElement NewRoomClassMaxAdults1;

	@FindBy(xpath = OR.New_RoomCLass_Max_Persons1)
	public WebElement NewRoomClassMaxPersons1;

	@FindBy(xpath = OR.New_RoomClass_Room_Quantity1)
	public WebElement NewRoomClassRoomQuantity1;

	@FindBy(xpath = OR.New_RoomClass_Create_Rooms1)
	public WebElement NewRoomClassCreateRooms1;

	@FindBy(xpath = OR.New_RoomClass_RoomNumber1)
	public WebElement NewRoomClassRoomNumber1;

	@FindBy(xpath = OR.New_RoomClass_AssignRoomNumber1)
	public WebElement NewRoomClassAssignRoomNumbers1;

	@FindBy(xpath = OR.New_RoomClass_Save1)
	public WebElement NewRoomClassSave1;

	@FindBy(xpath = OR.New_RoomClass_Publish1)
	public WebElement NewRoomClassPublishButton1;

	@FindBy(xpath = OR.New_RoomClass_OK1)
	public WebElement NewRoomClassOk1;

	@FindBy(xpath = OR.CloseRoomClass)
	public WebElement CloseRoomClass;

	@FindBy(xpath = OR.Close_RoomClass)
	public WebElement Close_RoomClass;

	@FindBy(xpath = OR.Close_RoomClass_1)
	public WebElement Close_RoomClass_1;

	@FindBy(xpath = OR.SelectAllRecord)
	public WebElement SelectAllRecord;

	@FindBy(xpath = OR.SelectAllRecord_1)
	public WebElement SelectAllRecord_1;

	@FindBy(xpath = OR.SelectAllRecord_2)
	public WebElement SelectAllRecord_2;

	@FindBy(xpath = OR.Select100TopRecord_1)
	public WebElement Select100TopRecord_1;

	@FindBy(xpath = OR.SelectRoomClassCheckBox)
	public WebElement SelectRoomClassCheckBox;

	@FindBy(xpath = OR.RoomclassDeleteButton)
	public WebElement RoomclassDeleteButton;

	@FindBy(xpath = OR.RoomclassDelete_OKButton)
	public WebElement RoomclassDelete_OKButton;

	@FindBy(xpath = OR.RoomClass_Checkbox)
	public List<WebElement> RoomClass_Checkbox;

	@FindBy(xpath = OR.DeleteRoomClass_Button)
	public WebElement DeleteRoomClass_Button;

	@FindBy(xpath = OR.DeleteRoomClassesButton)
	public WebElement DeleteRoomClassesButton;

	@FindBy(xpath = OR.RoomClassPages)
	public List<WebElement> RoomClassPages;

	@FindBy(xpath = OR.RoomClass_Pages)
	public List<WebElement> RoomClass_Pages;

	@FindBy(xpath = NewRoomClass.RoomClassItemsPerpage)
	public WebElement RoomClassItemsPerpage;

	@FindBy(xpath = NewRoomClass.RoomClassList_Names)
	public WebElement RoomClassList_Names;

	@FindBy(xpath = NewRoomClass.RoomClassItemsPerpage_Select100)
	public WebElement RoomClassItemsPerpage_Select100;

	@FindBy(xpath = NewRoomClass.SelectedItemsPerPage)
	public WebElement SelectedItemsPerPage;

	@FindBy(xpath = NewRoomClass.SelectedItems_PerPage)
	public WebElement SelectedItems_PerPage;

	@FindBy(className = OR.Toaster_Message)
	public WebElement Toaster_Message;

	@FindBy(className = OR.Toaster_Title)
	public WebElement Toaster_Title;

	@FindBy(xpath = OR.New_RoomClass_RoomNumber)
	public List<WebElement> LIst_NewRoomClassRoomNumber;

	@FindBy(xpath = OR_Setup.Roomclass_Pagenation)
	public WebElement Roomclass_Pagenation;

	@FindBy(xpath = OR_Setup.New_RoomClass_RoomNumbers)
	public List<WebElement> List_NewRoomClassRoomNumbers;

	@FindBy(xpath = OR.roomClassHelpIcon)
	public WebElement roomClassHelpIcon;

	@FindBy(xpath = OR.roomClassCollapseIcon)
	public WebElement roomClassCollapseIcon;
	
	@FindBy(xpath = NewRoomClass.RoomClassList_Names)
	public List<WebElement> RoomClassListNames;

	@FindBy(xpath = OR.ROOM_CLASS_STATUS_SELECTED_VALUE)
	public WebElement ROOM_CLASS_STATUS_SELECTED_VALUE;
	
	@FindBy(xpath = OR.roomClassStatusDropDown)
	public WebElement roomClassStatusDropDown;
	
	@FindBy(xpath = OR.roomClassSearchButton)
	public WebElement roomClassSearchButton;

	@FindBy(xpath = OR.roomClassList)
	public List<WebElement> roomClassList;

}
