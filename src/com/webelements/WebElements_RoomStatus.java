package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_GuestServices;

public class WebElements_RoomStatus {

	WebDriver driver;
	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public WebElements_RoomStatus(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);

	}

	@FindBy(xpath = OR.RoomStatus_SearchInput)
	public List<WebElement> RoomStatus_SearchInput;

	@FindBy(id = OR.RoomStatus_SearchButon)
	public WebElement RoomStatus_SearchButon;

	@FindBy(className = OR.RoomStatus_SearchedRooms)
	public WebElement RoomStatus_SearchedRooms;

	@FindBy(className = OR.VerifyRoomStats_Dirty)
	public WebElement VerifyRoomStats_Dirty;

	@FindBy(className = OR.VerifyRoomStats_Inspection)
	public WebElement VerifyRoomStats_Inspection;

	@FindBy(className = OR.VerifyRoomStats_Clean)
	public WebElement VerifyRoomStats_Clean;

	@FindBy(className = OR.VerifyRoomStats_OutofOrder)
	public WebElement VerifyRoomStats_OutofOrder;

	@FindBy(className = OR.VerifyRoomStats_All)
	public WebElement VerifyRoomStats_All;

	@FindBy(xpath = OR.VerifySortFuncButton)
	public WebElement VerifySortFuncButton;

	@FindBy(xpath = OR.VerifySortFuncButton1)
	public WebElement VerifySortFuncButton1;

	@FindBy(xpath = OR.VerifySortFunc_RoomNum)
	public WebElement VerifySortFunc_RoomNum;

	@FindBy(xpath = OR.VerifySortFunc_Zone)
	public WebElement VerifySortFunc_Zone;

	@FindBy(xpath = OR.VerifySortFunc_ArrivalDue)
	public WebElement VerifySortFunc_ArrivalDue;

//	@FindBy(xpath = OR.RoomStatus_RoomDirtyLabel)
//	public WebElement RoomStatus_RoomDirtyLabel;

	@FindBy(xpath = OR.RoomStatus_RoomTaskLabel)
	public WebElement RoomStatus_RoomTaskLabel;

	@FindBy(xpath = OR.RoomStatus_RoomDirtyLabelDropDownButton)
	public WebElement RoomStatus_RoomDirtyLabelDropDownButton;

	@FindBy(xpath = OR.RoomStatus_RoomDirtyLabelButton_DirtyCate)
	public WebElement RoomStatus_RoomDirtyLabelButton_DirtyCate;

	@FindBy(xpath = OR.RoomStatus_RoomDirtyLabelButton_CleanCate)
	public WebElement RoomStatus_RoomDirtyLabelButton_CleanCate;

	@FindBy(xpath = OR.RoomStatus_RoomDirtyLabelButton_InspectionCate)
	public WebElement RoomStatus_RoomDirtyLabelButton_InspectionCate;

	@FindBy(xpath = OR.RoomStatus_Room_RadioButton)
	public WebElement RoomStatus_Room_RadioButton;

	@FindBy(xpath = OR.UpdateStatusButton)
	public WebElement UpdateStatusButton;

	@FindBy(xpath = OR.UpdateStatusButton1)
	public WebElement UpdateStatusButton1;

	@FindBy(xpath = OR.UpdateStatus_Dirty)
	public WebElement UpdateStatus_Dirty;

	@FindBy(xpath = OR.UpdateStatus_Inspection)
	public WebElement UpdateStatus_Inspection;

	@FindBy(xpath = OR.UpdateStatus_Clean)
	public WebElement UpdateStatus_Clean;

	@FindBy(xpath = OR.RoomStatus_Element)
	public WebElement RoomStatus_Element;

	@FindBy(xpath = OR.Report)
	public WebElement Report;

	@FindBy(xpath = OR.Report_RoomStatus)
	public WebElement Report_RoomStatus;

	@FindBy(xpath = OR.Report_RoomStatusTask)
	public WebElement Report_RoomStatusTask;

	@FindBy(xpath = OR.ReportPage)
	public WebElement ReportPage;

	@FindBy(xpath = OR.DirtyTab)
	public WebElement DirtyTab;

	@FindBy(xpath = OR.RoomStatus)
	public WebElement RoomStatusTab;

	/*
	 * @FindBy(xpath = OR.RoomStatus_AllStatNumber) public WebElement
	 * RoomStatus_AllQuickStatsNumber;
	 */

	@FindBy(xpath = OR.RoomStatus_SearchField)
	public WebElement RoomStatus_SearchField;

 /*	@FindBy(xpath = OR.RoomStatus_RoomCard)
	public List<WebElement> RoomStatus_RoomCardList;  */

	@FindBy(xpath = OR.RoomStatus_RoomTileDropDownButton)
	public List<WebElement> RoomStatus_RoomTileDropDownButton;

	@FindBy(xpath = OR.GS_DateRangeLabel)
	public WebElement RoomStatus_Today;

	@FindBy(xpath = OR_GuestServices.OutOFOrderTab)
	public WebElement OOOTab;

	@FindBy(xpath = OR_GuestServices.RoomStatus_OutOfOrderStatNumber)
	public WebElement RoomStatus_OutOfOrderStatSize;

	@FindBy(xpath = OR_GuestServices.RoomStatus_OORRoomCard)
	public List<WebElement> RoomStatus_OORRoomCard;

	@FindBy(xpath = OR_GuestServices.RoomStatus_DirtyStatNumber)
	public WebElement RoomStatus_DirtyStatSize;

	@FindBy(xpath = OR_GuestServices.RoomStatus_CleanStatNumber)
	public WebElement RoomStatus_CleanStatSize;

	@FindBy(xpath = OR_GuestServices.RoomStatus_AllStatNumber)
	public WebElement RoomStatus_AllQuickStatsNumber;
	
	@FindBy(xpath = OR_GuestServices.RoomStatusTab)
	public WebElement RoomStatusTabOne;
	
	@FindBy(xpath = OR_GuestServices.GS_Loading)
	public WebElement GS_Loading;
	
	@FindBy(xpath = OR_GuestServices.RoomStatus_RoomCard)
	public List<WebElement> RoomStatus_RoomCardList;
	
	 @FindBy(xpath = OR_GuestServices.RoomStatus_ClenRoomCardSize)
	 public List< WebElement> RoomStatus_CleanRoomCardS;
	 
	 @FindBy(xpath = OR_GuestServices.RoomStatus_DirtyRoomCardSize)
	   public List< WebElement> RoomStatus_DirtyStatTotalRoomCardStatus;  
	 
	 @FindBy(xpath = OR_GuestServices.RoomStatus_InspectionStatNumber)
	 public WebElement RoomStatus_InspectionStatSize;  
	 
	 @FindBy(xpath = OR_GuestServices.RoomStatus_InspectionRoomCardSize)
	 public List< WebElement> RoomStatus_InspectionRoomCardS;
	 
	 @FindBy(xpath = OR_GuestServices.RoomStatus_OORRoomCardSize)
	 public List< WebElement> RoomStatus_OORRoomCardS;
	 
	 
	 @FindBy(xpath = OR_GuestServices.RoomStatus_Room_RadioButtons)
	 public List<WebElement> RoomStatus_RoomTile_RadioButtons;
	
	 @FindBy(xpath = OR_GuestServices.tosterMsg)
		public WebElement tosterMsg;
	 
	 @FindBy(xpath = OR_GuestServices.RoomStatus_RoomCard_Vacant)          
	 public WebElement RoomStatus_Vacant;
	 
	 @FindBy(xpath = OR_GuestServices.RoomStatus_RoomCard_ArrivalDue)
	   public WebElement RoomStatus_ArrivalDue; 
	 
	 @FindBy (xpath = OR_GuestServices.RoomStatus_AllTab)
		public WebElement RoomStatus_AllTab;
	 
	 @FindBy (xpath = OR_GuestServices.RoomStatus_AllQuickStats)
		public WebElement RoomStatus_AllQuickStats;

}