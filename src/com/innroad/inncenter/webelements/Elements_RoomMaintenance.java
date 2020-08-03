package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_GuestServices;

public class Elements_RoomMaintenance {
	

	WebDriver driver ;


	public static Logger app_logs = Logger.getLogger("devpinoyLogger");
		
		public Elements_RoomMaintenance(WebDriver driver2)
		{
			this.driver=driver2;
			PageFactory.initElements(this.driver, this);
			
			 
		}

		@FindBy(xpath=OR.NewRoomMaintenance_Button)
		public WebElement NewRoomMaintenance_Button;
		
		@FindBy(name=OR.SelectStart_EndDate)
		public List<WebElement> SelectStart_EndDate;
		
		@FindBy(xpath=OR.GetStartDate)
		public WebElement GetStartDate;
		
		@FindBy(xpath=OR.GetEndDate)
		public WebElement GetEndDate;
		
		@FindBy(xpath=OR.EnterRoomMaintenance_Night)
		public WebElement EnterRoomMaintenance_Night;
		
		@FindBy(xpath=OR.EnterRoomMaintenance_Subject)
		public WebElement EnterRoomMaintenance_Subject;
		
		@FindBy(xpath=OR.RoomMaintenance_AssociateRoom)
		public WebElement RoomMaintenance_AssociateRoom;
		
		@FindBy(xpath=OR.RoomMaintenance_AddNote)
		public WebElement RoomMaintenance_AddNote;
		
		@FindBy(xpath=OR.RoomMaintenance_AddNoteSubject)
		public WebElement RoomMaintenance_AddNoteSubject;
		
		@FindBy(xpath=OR.RoomMaintenance_AddNoteDetail)
		public WebElement RoomMaintenance_AddNoteDetail;
		
		@FindBy(xpath=OR.RoomMaintenance_AddNoteSaveButton)
		public WebElement RoomMaintenance_AddNoteSaveButton;
		
		@FindBy(css=OR.GetActiveDate)
		public WebElement GetActiveDate;
		
		@FindBy(xpath=OR.SelectDate_Today)
		public WebElement SelectDate_Today;
		
		@FindBy(xpath=OR.RoomPicker_Popup)
		public WebElement RoomPicker_Popup;
		
		@FindBy(xpath=OR.SelectRoom_OutofOrder)
		public WebElement SelectRoom_OutofOrder;
		
		@FindBy(xpath=OR.GetRoomClass)
		public WebElement GetRoomClass;
		
		@FindBy(xpath=OR.GetRoomNumber)
		public WebElement GetRoomNumber;
		
		@FindBy(xpath=OR.Select_RoomButton)
		public WebElement Select_RoomButton;
		
		@FindBy(xpath=OR.RoomMaintenance_Save)
		public WebElement RoomMaintenance_Save;
		
		@FindBy(xpath=OR.InputRoomOutOfOrder)
		public WebElement InputRoomOutOfOrder;
		
		@FindBy(xpath=OR.DeleteButton)
		public WebElement DeleteButton;
		
		@FindBy(xpath=OR.RemoveButton)
		public WebElement RemoveButton;
		
		@FindBy(xpath=OR.Records)
		public WebElement Records;
		
		@FindBy(xpath=OR.DoneButton)
		public WebElement DoneButton;
		
		@FindBy(id=OR.RoomMaintenance_Reason)
		public WebElement RoomMaintenance_Reason;
		
		@FindBy(id=OR.RoomMaintenance_Room)
		public WebElement RoomMaintenance_Room;
		
		@FindBy(id=OR.RoomMaintenance_GoButton)
		public WebElement RoomMaintenance_GoButton;
		
		@FindBy(xpath=OR.RoomMaintenance_VerifyReason)
		public WebElement RoomMaintenance_VerifyReason;
		
		@FindBy(xpath=OR.RoomMaintenance_DetailPage)
		public WebElement RoomMaintenance_DetailPage;
		
		@FindBy(xpath=OR.RoomMaintenance_FirstActiveElement)
		public WebElement RoomMaintenance_FirstActiveElement;
		
		@FindBy(xpath=OR.RoomMaintenance_FirstActiveElement_RoomClass)
		public WebElement RoomMaintenance_FirstActiveElement_RoomClass;
		
		@FindBy(xpath=OR.RoomMaintenance_AddNotes)
		public WebElement RoomMaintenance_AddNotes;
		
		@FindBy(xpath=OR.RoomMaintenance_NotesDetailPage)
		public WebElement RoomMaintenance_NotesDetailPage;
		
		@FindBy(xpath=OR.RoomMaintenance_Notes_Subject)
		public WebElement RoomMaintenance_Notes_Subject;
		
		@FindBy(xpath=OR.RoomMaintenance_Notes_Detail)
		public WebElement RoomMaintenance_Notes_Detail;
		
		@FindBy(xpath=OR.RoomMaintenance_Notes_SaveButton)
		public WebElement RoomMaintenance_Notes_SaveButton;
		

		@FindBy(xpath=OR_GuestServices.RoomMaintenance_Nights)
		public WebElement RoomMaintenance_Nights;
		
		@FindBy(xpath=OR_GuestServices.RoomMaintenance_StartDate)
		public WebElement RoomMaintenance_StartDate;
		
		@FindBy(xpath=OR_GuestServices.RoomMaintenance_EndDate)
		public WebElement RoomMaintenance_EndDate;
		
		@FindBy(xpath=OR_GuestServices.RoomMaintenance_WeekDayCell)
		public List<WebElement> RoomMaintenance_WeekDayCell;
		
		@FindBy(xpath=OR_GuestServices.RoomMaintenance_WeekDateCell)
		public List<WebElement> RoomMaintenance_WeekDateCell;
		
		
}
