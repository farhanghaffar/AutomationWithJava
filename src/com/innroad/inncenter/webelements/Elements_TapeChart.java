package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.pages.NewTapeChart;
import com.innroad.inncenter.properties.OR;

public class Elements_TapeChart {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_TapeChart(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);

	}

	@FindBy(xpath = OR.Select_Arrival_Date)
	public WebElement Select_Arrival_Date;

	@FindBy(xpath = OR.Enter_Adults_Tapehchart)
	public WebElement Enter_Adults_Tapehchart;

	@FindBy(xpath = OR.Enter_Children_Tapechart)
	public WebElement Enter_Children_Tapechart;

	@FindBy(xpath = OR.Click_Tapechart_Rateplan)
	public WebElement Click_Tapechart_Rateplan;

	@FindBy(xpath = OR.Enter_promoCode_Tapechart)
	public WebElement Enter_promoCode_Tapechart;

	@FindBy(xpath = OR.Click_First_Available)
	public WebElement Click_First_Available;

	@FindBy(xpath = OR.Click_Today)
	public WebElement Click_Today;

	@FindBy(xpath = OR.Click_Tomorrow)
	public WebElement Click_Tomorrow;

	@FindBy(xpath = OR.Get_Tapechart_Rateplan)
	public WebElement Get_Tapechart_Rateplan;

	@FindBy(xpath = OR.Select_Rack_Rate)
	public WebElement Select_Rack_Rate;

	@FindBy(xpath = OR.Click_Tapechart_RackRate)
	public WebElement Click_Tapechart_RackRate;

	@FindBy(xpath = OR.Click_Search_TapeChart)
	public WebElement Click_Search_TapeChart;

	@FindBy(xpath = OR.Rules_Broken_popup)
	public WebElement Rules_Broken_popup;

	@FindBy(xpath = OR.Click_Book_icon_Tapechart)
	public WebElement Click_Book_icon_Tapechart;

	@FindBy(xpath = OR.Click_Unassigned_Tapechart)
	public WebElement Click_Unassigned_Tapechart;

	@FindBy(xpath = OR.FirstRoomClass_Rate_In_Tapechart)
	public WebElement FirstRoomClass_Rate_In_Tapechart;

	@FindBy(xpath = OR.Click_First_Available_In_First_Roomclass)
	public WebElement Click_First_Available_In_First_Roomclass;

	@FindBy(className = OR.ReservationsLink)
	public WebElement ReservationsLink;

	@FindBy(xpath = OR.Tape_Chart)
	public WebElement Tape_Chart;

	@FindBy(xpath = OR.TapeChart)
	public WebElement TapeChart;

	@FindBy(xpath = NewTapeChart.OverBookingPopup_Cancel)
	public WebElement OverBookingPopup_Cancel;

	@FindBy(xpath = NewTapeChart.OverBookingPopup)
	public WebElement OverBookingPopup;

	@FindBy(css = OR.BlackOutCell)
	public List<WebElement> BlackOutCell;

	@FindBy(xpath = OR.New_QuoteIcon)
	public WebElement New_QuoteIcon;

	@FindBy(xpath = OR.NewQuote)
	public WebElement NewQuote;

	@FindBy(css = OR.DatePickerIcon)
	public List<WebElement> DatePickerIcon;

	@FindBy(xpath = OR.Quote_SearchButton)
	public WebElement Quote_SearchButton;

	@FindBy(xpath = OR.Rooms_Table)
	public WebElement Rooms_Table;

	@FindBy(xpath = OR.BlackOutAlert)
	public WebElement BlackOutAlert;

	@FindBy(xpath = OR.NoRatePopUp)
	public WebElement NoRatePopUp;

	@FindBy(xpath = OR.Blackout_OkButton)
	public WebElement Blackout_OkButton;

	@FindBy(css = OR.SelectDate)
	public WebElement SelectDate;

	@FindBy(className = OR.BookButton)
	public List<WebElement> BookButton;

	@FindBy(css = OR.Rule_Broken)
	public List<WebElement> Rule_Broken;

	@FindBy(xpath = OR.Rule_Broken_PopUp)
	public WebElement Rule_Broken_PopUp;

	@FindBy(xpath = OR.Rule_Broken_Cancel)
	public WebElement Rule_Broken_Cancel;

	@FindBy(className = OR.Reservation_Book_Button)
	public List<WebElement> Reservation_Book_Button;

	@FindBy(css = OR.QuoteBookButton)
	public List<WebElement> QuoteBookButton;

	@FindBy(xpath = OR.RoomClass)
	public WebElement RoomClass;

	@FindBy(xpath = OR.SyndicateCell)
	public WebElement SyndicateCell;

	@FindBy(xpath = OR.OutOfOrderCell)
	public WebElement OutOfOrderCell;

	@FindBy(xpath = OR.Click_Today_Arrival_Groups)
	public WebElement Click_Today_Arrival_Groups;

	@FindBy(xpath = OR.UnsignedList_Date)
	public List<WebElement> UnsignedList_Date;

	@FindBy(xpath = OR.Unassigned_Button)
	public WebElement Unassigned_Button;

	@FindBy(xpath = OR.GetTodayDate)
	public WebElement GetTodayDate;

	@FindBy(xpath = OR.Res_View_Limit_Element)
	public WebElement Res_View_Limit_Element;

	@FindBy(xpath = OR.DBR_Unassigned_FooterRow)
	public WebElement DBR_Unassigned_FooterRow;

	@FindBy(xpath = OR.Unassigned_Footer_Row)
	public WebElement Unassigned_Footer_Row;

	@FindBy(xpath = OR.JR_Unssigned_FooterRow)
	public WebElement JR_Unssigned_FooterRow;

	@FindBy(xpath = OR.PR_Unssigned_FooterRow)
	public WebElement PR_Unssigned_FooterRow;

	@FindBy(xpath = OR.Unassigned_Reserv_Room)
	public WebElement Unassigned_Reserv_Room;

	@FindBy(xpath = OR.ReservationToDrag)
	public WebElement ReservationToDrag;

	@FindBy(xpath = OR.ConfirmChangesButton)
	public WebElement ConfirmChangesButton;

	@FindBy(xpath = OR.Toaster_Message)
	public WebElement Toaster_Message;

	@FindBy(xpath = OR.ConfirmChangeReservation_Button)
	public WebElement ConfirmChangeReservation_Button;

	@FindBy(xpath = OR.CheckIn)
	public WebElement CheckIn;

	@FindBy(xpath = OR.BalanceDue)
	public WebElement BalanceDue;

	@FindBy(xpath = OR.CheckOut)
	public WebElement CheckOut;

	@FindBy(xpath = OR.TripTotal)
	public WebElement TripTotal;

	@FindBy(xpath = OR.CancelChangeReservation_Button)
	public WebElement CancelChangeReservation_Button;

	@FindBy(xpath = OR.TapeChart_1DayButton)
	public WebElement TapeChart_1DayButton;

	@FindBy(xpath = OR.ReservationTapeChart_ReservationTitle)
	public WebElement ReservationTapeChart_ReservationTitle;

	@FindBy(xpath = OR.TapeChartAvailableSlot)
	public WebElement TapeChartAvailableSlot;

	@FindBy(xpath = OR.ClickTapeChartAvailableSlot)
	public WebElement ClickTapeChartAvailableSlot;

	@FindBy(xpath = OR.ClickReservation)
	public WebElement ClickReservation;

	@FindBy(xpath = OR.NewDate_CheckOut)
	public WebElement NewDate_CheckOut;

	@FindBy(xpath = OR.PreviousDate_CheckOut)
	public WebElement PreviousDate_CheckOut;

	@FindBy(xpath = OR.CancelChangesButton)
	public WebElement CancelChangesButton;

	@FindBy(xpath = OR.ReservationUpdate_Popup)
	public WebElement ReservationUpdate_Popup;

	@FindBy(css = OR.OldDate)
	public WebElement OldDate;

	@FindBy(css = OR.GetActiveDate)
	public WebElement GetActiveDate;

	@FindBy(xpath = OR.TapeChart_CheckIn)
	public WebElement TapeChart_CheckIn;

	@FindBy(xpath = OR.AssignRoomCheckBox)
	public WebElement AssignRoomCheckBox;

	@FindBy(xpath = OR.ReservationDetailPage)
	public WebElement ReservationDetailPage;

	@FindBy(xpath = OR.UnassignedList)
	public WebElement UnassignedList;

	@FindBy(xpath = OR.OverBookingPopup)
	public WebElement Over_BookingPopup;

	@FindBy(xpath = OR.OverBookingCancelBtn)
	public WebElement OverBookingCancelBtn;

	@FindBy(xpath = OR.OverBookingContinueBtn)
	public WebElement OverBookingContinueBtn;

	@FindBy(xpath = OR.Rule_Broken_PopUp)
	public List<WebElement> ListRuleBrokenPopUp;

	@FindBy(xpath = OR.RuleBrokenBook_Btn)
	public WebElement RuleBrokenBook_Btn;

	@FindBy(xpath = OR.Select_DepartDate)
	public WebElement Select_DepartDate;

	@FindBy(xpath = OR.TapeChartDay1Button)
	public WebElement TapeChartDay1Button;

	@FindBy(xpath = OR.click1Day)
	public WebElement click1Day;

	@FindBy(xpath = OR.getUnsassignedResCountTapechart)
	public WebElement getUnsassignedResCountTapechart;

	@FindBy(xpath = OR.getUnsassignedResTapechart)
	public List<WebElement> getUnsassignedResTapechart;

	@FindBy(xpath = NewTapeChart.Propertylevel_RoomsAvailable_Today)
	public WebElement Propertylevel_RoomsAvailable_Today;

	@FindBy(xpath = NewTapeChart.Propertylevel_PercentOccupancy_Today)
	public WebElement Propertylevel_PercentOccupancy_Today;

	@FindBy(xpath = OR.Select_Ratepaln_DropDown)
	public WebElement Select_Ratepaln_DropDown;

	@FindBy(xpath = NewTapeChart.OverBookingPopup_Continue)
	public WebElement OverBookingPopup_Continue;

	@FindBy(xpath = OR.ConfirmChanges_Button)
	public WebElement ConfirmChanges_Button;

	@FindBy(xpath = OR.closeReservation)
	public WebElement closeReservation;

	@FindBy(xpath = NewTapeChart.RateDetailPopup_Heading)
	public WebElement RateDetailPopup_Heading;

	@FindBy(xpath = NewTapeChart.RateDetailPopup_RateName)
	public WebElement RateDetailPopup_RateName;

	@FindBy(xpath = NewTapeChart.RateDetailPopup_RatePlan)
	public WebElement RateDetailPopup_RatePlan;

	@FindBy(xpath = NewTapeChart.RateDetailPopup_RateAttributes)
	public WebElement RateDetailPopup_RateAttributes;

	@FindBy(xpath = NewTapeChart.RateDetailPopup_Interval)
	public WebElement RateDetailPopup_Interval;

	@FindBy(xpath = NewTapeChart.RateDetailPopup_MaxAdults)
	public WebElement RateDetailPopup_MaxAdults;

	@FindBy(xpath = NewTapeChart.RateDetailPopup_MaxPersons)
	public WebElement RateDetailPopup_MaxPersons;

	@FindBy(xpath = NewTapeChart.RateDetailPopup_BaseAmount)
	public WebElement RateDetailPopup_BaseAmount;

	@FindBy(xpath = NewTapeChart.RateDetailPopup_AdditionalAdult)
	public WebElement RateDetailPopup_AdditionalAdult;

	@FindBy(xpath = NewTapeChart.RateDetailPopup_AdditionalChild)
	public WebElement RateDetailPopup_AdditionalChild;

	@FindBy(xpath = NewTapeChart.RateDetailPopup_DisplayName)
	public WebElement RateDetailPopup_DisplayName;

	@FindBy(xpath = NewTapeChart.RateDetailPopup_Policy)
	public WebElement RateDetailPopup_Policy;

	@FindBy(xpath = NewTapeChart.RateDetailPopup_Description)
	public WebElement RateDetailPopup_Description;

	@FindBy(xpath = NewTapeChart.RateDetailPopup_Season)
	public WebElement RateDetailPopup_Season;

	@FindBy(xpath = NewTapeChart.RateDetailPopup_RoomClass)
	public WebElement RateDetailPopup_RoomClass;

	@FindBy(xpath = NewTapeChart.RateDetailPopup_Source)
	public WebElement RateDetailPopup_Source;

	@FindBy(xpath = NewTapeChart.RateDetailPopup_CancelButton)
	public WebElement RateDetailPopup_CancelButton;

	@FindBy(xpath = NewTapeChart.btnRatePlanSelect)
	public WebElement btnRatePlanSelect;

	@FindBy(xpath = NewTapeChart.enterAmount)
	public WebElement enterAmount;

	@FindBy(xpath = NewTapeChart.AvailableRoom)
	public WebElement AvailableRoom;

	@FindBy(xpath = NewTapeChart.TapeChart_Out_Icon)
	public WebElement TapeChart_outIcon;

	@FindBy(xpath = NewTapeChart.TapeChart_CheckOut)
	public WebElement TapeChart_CheckOut;

	@FindBy(xpath = NewTapeChart.UnassignedColumnHeader)
	public WebElement UnassignedColumnHeader;

	@FindBy(xpath = NewTapeChart.UnassignedList_ResName)
	public List<WebElement> UnassignedList_ResName;

	@FindBy(xpath = NewTapeChart.UnassignedList_RoomClassAbb)
	public List<WebElement> UnassignedList_RoomClassAbb;

	@FindBy(xpath = NewTapeChart.UnassignedList_ArrivalDate)
	public List<WebElement> UnassignedList_ArrivalDate;

	@FindBy(xpath = NewTapeChart.UnassignedList_DepartureDate)
	public List<WebElement> UnassignedList_DepartureDate;

	@FindBy(xpath = NewTapeChart.TapeChartDateChart)
	public WebElement TapeChartDateChart;

	@FindBy(xpath = NewTapeChart.TAPECHART_CHECKOUT)
	public WebElement TAPECHART_CHECKOUT;

	@FindBy(xpath = NewTapeChart.GET_DATE_AND_DAY)
	public WebElement getDateAndDay;

	@FindBy(xpath = NewTapeChart.GET_DATE_AND_DAY)
	public List<WebElement> getDateAndDayList;

	@FindBy(xpath = NewTapeChart.GET_TOAST_MESSAGE)
	public List<WebElement> getToastMessage;

	@FindBy(xpath = NewTapeChart.GET_CHECK_IN_PRE_DATE)
	public WebElement getCheckInPreDate;

	@FindBy(xpath = NewTapeChart.GET_CHECK_IN_POST_DATE)
	public WebElement getCheckInPostDate;

	@FindBy(xpath = NewTapeChart.GET_CHECK_OUT_PRE_DATE)
	public WebElement getCheckOutPreDate;

	@FindBy(xpath = NewTapeChart.GET_CHECK_OUT_POST_DATE)
	public WebElement getCheckOutPostDate;

	@FindBy(xpath = NewTapeChart.GET_ROOM_CLASS_PRE)
	public WebElement getRoomClassPre;

	@FindBy(xpath = NewTapeChart.GET_ROOM_CLASS_POST)
	public WebElement getRoomClassPost;

	@FindBy(xpath = NewTapeChart.GET_TRIP_TOTAL_PRE)
	public WebElement getTripTotalPre;

	@FindBy(xpath = NewTapeChart.GET_TRIP_TOTAL_POST)
	public WebElement getTripTotalPost;

	@FindBy(xpath = NewTapeChart.GET_BALANCE_DUE_PRE)
	public WebElement getBalanceDuePre;

	@FindBy(xpath = NewTapeChart.GET_BALANCE_POST)
	public WebElement getBalancePost;

	@FindBy(xpath = NewTapeChart.GET_CHECK_IN_TEXT)
	public WebElement GetCheckInText;

	@FindBy(xpath = NewTapeChart.GET_CHECK_OUT_TEXT)
	public WebElement getCheckOutText;

	@FindBy(xpath = NewTapeChart.GET_ROOM_CLASS_TEXT)
	public WebElement getRoomClassText;

	@FindBy(xpath = NewTapeChart.GET_TRIP_TOTAL_TEXT)
	public WebElement getTripTotalText;

	@FindBy(xpath = NewTapeChart.GET_BALANCE_DUE_TEXT)
	public WebElement getBalanceDueText;

	@FindBy(xpath = NewTapeChart.CHANGE_RATE_APPLIED_DROPDOWN_BUTTON)
	public WebElement changeRateAppliedDropDownButton;

	@FindBy(xpath = NewTapeChart.CHANGE_RATE_APPLIED_DROPDOWN_LIST)
	public List<WebElement> changeRateAppliedDropDownList;

	@FindBy(xpath = NewTapeChart.CHANGE_RATE_APPLIED_SELECTEC_OPTION)
	public WebElement CHANGE_RATE_APPLIED_SELECTEC_OPTION;

}
