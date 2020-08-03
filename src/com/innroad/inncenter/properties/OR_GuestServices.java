package com.innroad.inncenter.properties;

public class OR_GuestServices {
	
	public static final String OutOFOrderTab = "//span[(text()='Out of Order')]";
	public static final String RoomStatus_OutOfOrderStatNumber = "//span[@class='gs-statsValue color-gray']";
	public static final String RoomStatus_OORRoomCard = "//div[contains(@class,'OutOfOrder')]";
	public static final String RoomStatus_DirtyStatNumber = "//span[@class='gs-statsValue color-red']";
	public static final String RoomStatus_CleanStatNumber = "//span[@class='gs-statsValue color-green']";
	public static final String RoomStatus_AllStatNumber = "//span[@class='gs-statsValue color-blue']";
	public static final String RoomStatusTab = "//a[.='Room Status']";
	 public static final String GS_Loading="(//div[contains(@class,'loading')]/div[@class='title'])";
	 public static final String RoomStatus_RoomCard = "//room-card/div";
	 
	 public static final String RoomMaintenance_Nights = "//input[@id='MainContent_txtNights']";
	 public static final String RoomMaintenance_StartDate = "//input[@id='MainContent_txtDateStart']";
	 public static final String RoomMaintenance_EndDate = "//input[@id='MainContent_txtDateEnd']";
	 public static final String RoomMaintenance_WeekDayCell = "//div[@class='col flex-column']//div[contains(@class,'MonthDateCell')]//div[@class='weekDay']";
	 public static final String RoomMaintenance_WeekDateCell = "//div[@class='col flex-column']//div[contains(@class,'MonthDateCell')]//div[@class='dayNum']";
	 public static final String RoomStatus_ClenRoomCardSize = "//div[contains(@class,'Clean')]/div";
	 public static final String RoomStatus_DirtyRoomCardSize = "//div[contains(@class,'Dirty')]/div";
	 public static final String RoomStatus_InspectionStatNumber = "//span[@class='gs-statsValue color-orange']";
	 public static final String RoomStatus_InspectionRoomCardSize = "//div[contains(@class,'Inspection')]/div";
	 public static final String RoomStatus_OORRoomCardSize = "//div[contains(@class,'OutOfOrder')]/div";
	 public static final String RoomStatus_Room_RadioButtons = "//div[@class='grid-content-inner']//div//span//span";
	 public static final String tosterMsg="//div[@class='toast-message']";
	 public static final String RoomStatus_RoomCard_Vacant = "//room-card/div//span";
	 public static final String RoomStatus_RoomCard_ArrivalDue = "//room-card/div//span[@class='arrivalDue']";
	 public static final String RoomStatus_AllTab = "//li[@class='blue']";
	 public static final String RoomStatus_AllQuickStats = "//li[@class='blue active']";



		

}
