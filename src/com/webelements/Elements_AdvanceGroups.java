package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class Elements_AdvanceGroups {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_AdvanceGroups(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);

	}
	
	@FindBy(xpath=OR.Group_Search_Go)
	public WebElement Group_Search_Go;
	
	@FindBy(xpath=OR.Group_Reservation_Search_Go)
	public WebElement Group_Reservation_Search_Go;
	
	@FindBy(xpath=OR.Group_Reservation_Search_Reservation)
	public WebElement Group_Reservation_Search_Reservation;
	
	@FindBy(xpath=OR.Group_Search_GroupName)
	public WebElement Group_Search_GroupName;

	@FindBy(xpath = OR.GroupsNewAccount)
	public WebElement GroupsNewAccount;
	
	@FindBy(xpath = OR.GroupAccountNumber)
	public WebElement GroupAccountNumber;
	
	@FindBy(xpath = OR.GroupAccountNumber_1)
	public WebElement GroupAccountNumber_1;

	@FindBy(xpath = OR.AccountFirstName)
	public WebElement AccountFirstName;

	@FindBy(xpath = OR.MarketingSegment)
	public WebElement MarketingSegment;

	@FindBy(xpath = OR.GroupReferral)
	public WebElement GroupReferral;

	@FindBy(xpath = OR.SalutationMailing)
	public WebElement SalutationMailing;

	@FindBy(xpath = OR.Select_Account_Type)
	public WebElement Select_Account_Type;

	@FindBy(xpath = OR.GroupFirstName)
	public WebElement GroupFirstName;

	@FindBy(xpath = OR.GroupLastName)
	public WebElement GroupLastName;

	@FindBy(xpath = OR.GroupPhn)
	public WebElement GroupPhn;

	@FindBy(xpath = OR.GroupAddress)
	public WebElement GroupAddress;

	@FindBy(xpath = OR.GroupCity)
	public WebElement GroupCity;

	@FindBy(xpath = OR.Groupstate)
	public WebElement Groupstate;

	@FindBy(xpath = OR.GroupPostale)
	public WebElement GroupPostale;

	@FindBy(xpath = OR.Groupscountry)
	public WebElement Groupscountry;

	@FindBy(xpath = OR.Mailinginfo)
	public WebElement Mailinginfo;

	@FindBy(xpath = OR.GroupSave)
	public WebElement GroupSave;

	@FindBy(xpath = OR.Click_New_Block_button)
	public WebElement Click_New_Block_button;

	@FindBy(xpath = OR.Enter_Block_Name)
	public WebElement Enter_Block_Name;

	@FindBy(xpath = OR.Click_Ok)
	public WebElement Click_Ok;

	@FindBy(xpath = OR.Select_Arrival_Date_Groups)
	public WebElement Select_Arrival_Date_Groups;

	@FindBy(xpath = OR.Click_Today_Arrival_Groups)
	public WebElement Click_Today_Arrival_Groups;

	@FindBy(xpath = OR.Enter_No_of_Nigts)
	public WebElement Enter_No_of_Nigts;

	@FindBy(xpath = OR.Click_Search_Group)
	public WebElement Click_Search_Group;

	/*
	 * @FindBy(xpath=OR.GetBlockedRowsize) public WebElement GetBlockedRowsize;
	 */

	@FindBy(id = OR.txtTotalRooms)
	public WebElement txtTotalRooms;

	@FindBy(xpath = OR.GetBlockedRowsize)
	public List<WebElement> GetBlockedRowsize;

	@FindBy(xpath = OR.GetRoomclasses)
	public List<WebElement> GetRoomclasses;

	@FindBy(xpath = OR.Click_Create_Block)
	public WebElement Click_Create_Block;

	@FindBy(xpath = OR.Click_Create_Block1)
	public WebElement Click_Create_Block1;

	@FindBy(xpath = OR.Click_Ok_On_Rel_Popup)
	public WebElement Click_Ok_On_Rel_Popup;

	@FindBy(xpath = OR.Verify_Block_Nights)
	public WebElement Verify_Block_Nights;

	@FindBy(xpath = OR.Click_Continue_Block_Night)
	public WebElement Click_Continue_Block_Night;

	@FindBy(xpath = OR.CountofRooms)
	public List<WebElement> CountofRooms;

	@FindBy(xpath = OR.Navigate_Room_Block)
	public WebElement Navigate_Room_Block;

	@FindBy(xpath = OR.Rooming_List)
	public WebElement Rooming_List;

	@FindBy(xpath = OR.Rooming_List_Popup)
	public WebElement Rooming_List_Popup;

	@FindBy(xpath = OR.RoomingList_FirstName)
	public WebElement RoomingList_FirstName;

	@FindBy(xpath = OR.RoomingList_LastName)
	public WebElement RoomingList_LastName;

	@FindBy(xpath = OR.RoomingList_RoomClass)
	public WebElement RoomingList_RoomClass;

	@FindBy(xpath = OR.RoomingList_RoomNumber)
	public WebElement RoomingList_RoomNumber;

	@FindBy(xpath = OR.RoomingList_Amount)
	public WebElement RoomingList_Amount;

	@FindBy(xpath = OR.RoomingList_BillingInfo)
	public WebElement RoomingList_BillingInfo;

	@FindBy(xpath = OR.BillingInfo_Popup)
	public WebElement BillingInfo_Popup;

	@FindBy(xpath = OR.BillingInfo_PaymentMethod)
	public WebElement BillingInfo_PaymentMethod;

	@FindBy(xpath = OR.BillingInfo_Save)
	public WebElement BillingInfo_Save;

	@FindBy(xpath = OR.BillingInfo_Tick)
	public WebElement BillingInfo_Tick;

	@FindBy(xpath = OR.RoomingList_Pickup)
	public WebElement RoomingList_Pickup;

	@FindBy(xpath = OR.RoomingListSummary_Close)
	public WebElement RoomingListSummary_Close;

	@FindBy(xpath = OR.RoomingListSummary)
	public WebElement RoomingListSummary;

	@FindBy(xpath = OR.GeneratedReservationNumber)
	public WebElement GeneratedReservationNumber;

	@FindBy(xpath = OR.Groups_ReservationsTab)
	public WebElement Groups_ReservationsTab;

	@FindBy(xpath = OR.ReservationNumbers)
	public WebElement ReservationNumbers;

	@FindBy(xpath = OR.ReservationGuest)
	public WebElement ReservationGuest;

	@FindBy(xpath = OR.ReservationDetailPage_GuestName)
	public WebElement ReservationDetailPage_GuestName;

	@FindBy(xpath = OR.ReservationDetailPage_Account)
	public WebElement ReservationDetailPage_Account;

	@FindBy(xpath = OR.ReservationDetailPage_Close)
	public WebElement ReservationDetailPage_Close;

	@FindBy(xpath = OR.RoomDetailsPage_EditButton)
	public WebElement RoomDetailsPage_EditButton;

	@FindBy(xpath = OR.RoomDetailsPage_BlockDetails)
	public WebElement RoomDetailsPage_BlockDetails;

	@FindBy(xpath = OR.BlockDetailsPage_RoomBlockAttiribute)
	public WebElement BlockDetailsPage_RoomBlockAttiribute;

	@FindBy(xpath = OR.BlockDetailsPage_BlockOptionsButton)
	public WebElement BlockDetailsPage_BlockOptionsButton;

	@FindBy(xpath = OR.BlockDetailsPage_PreviewFolioButton)
	public WebElement BlockDetailsPage_PreviewFolioButton;

	@FindBy(xpath = OR.BlockDetailsPage_ChargeRouting)
	public WebElement BlockDetailsPage_ChargeRouting;

	@FindBy(xpath = OR.BlockDetailsPage_LineItems)
	public WebElement BlockDetailsPage_LineItems;

	@FindBy(xpath = OR.YellowBookIcon)
	public List<WebElement> YellowBookIcon;
	
	@FindBy(xpath = OR.RedBookIcon)
	public List<WebElement> RedBookIcon;
	
	@FindBy(xpath = OR.BlueBookIcon)
	public List<WebElement> BlueBookIcon;

	@FindBy(xpath=OR.Group_Click_Roominglistbutton)
	public WebElement Group_Click_Roominglistbutton;
	
	@FindBy(xpath=OR.Groups_Select_Pickupbutton_Roominglistpopup)
	public WebElement Groups_Select_Pickupbutton_Roominglistpopup;
	
	@FindBy(xpath=OR.GetStartDate)
	public WebElement GetStartDate;
	
	@FindBy(xpath=OR.GetEndDate)
	public WebElement GetEndDate;
	
	@FindBy(xpath=OR.GroupAdults)
	public WebElement GroupAdults;
	
	@FindBy(xpath=OR.AddGroupAdults)
	public WebElement AddGroupAdults;

	@FindBy(xpath=OR.SubtractGroupAdults)
	public WebElement SubtractGroupAdults;
	
	@FindBy(xpath=OR.GroupChilds)
	public WebElement GroupChilds;
	
	@FindBy(xpath=OR.AddGroupChilds)
	public WebElement AddGroupChilds;

	@FindBy(xpath=OR.SubtractGroupChilds)
	public WebElement SubtractGroupChilds;
	
	@FindBy(xpath=OR.GroupRatePlan)
	public WebElement GroupRatePlan;
	
	@FindBy(xpath=OR.AmountItemDetail)
	public WebElement AmountItemDetail;
	
	@FindBy(xpath=OR.Group_Folio_PickBillingInfo_Button)
	public WebElement Group_Folio_PickBillingInfo_Button;
	
	@FindBy(xpath=OR.Group_Folio_BillingInfo_FirstName)
	public WebElement Group_Folio_BillingInfo_FirstName;
	
	@FindBy(xpath=OR.Group_Folio_BillingInfo_LastName)
	public WebElement Group_Folio_BillingInfo_LastName;
	
	@FindBy(xpath=OR.Group_Folio_BillingInfo_City)
	public WebElement Group_Folio_BillingInfo_City;
	
	@FindBy(xpath=OR.Group_Folio_BillingInfo_State)
	public WebElement Group_Folio_BillingInfo_State;
	
	@FindBy(xpath=OR.Group_Folio_BillingInfo_Country)
	public WebElement Group_Folio_BillingInfo_Country;
	
	@FindBy(xpath=OR.Group_Folio_BillingInfo_PaymentMethod)
	public WebElement Group_Folio_BillingInfo_PaymentMethod;
	
	@FindBy(xpath=OR.Group_Folio_BillingInfo_SaveButton)
	public WebElement Group_Folio_BillingInfo_SaveButton;
	
	@FindBy(xpath=OR.GroupPreviewFolio_AddButton)
	public WebElement GroupPreviewFolio_AddButton;
	
	@FindBy(xpath=OR.GroupPreviewFolio_TaxAndServiceCharges)
	public WebElement GroupPreviewFolio_TaxAndServiceCharges;
	
	@FindBy(xpath=OR.GroupPreviewFolio_TotalCharges)
	public WebElement GroupPreviewFolio_TotalCharges;
	
	@FindBy(xpath=OR.GroupPreviewFolio_CommitButton)
	public WebElement GroupPreviewFolio_CommitButton;
	
	@FindBy(xpath=OR.Document_Save)
	public WebElement Document_Save;
	
	@FindBy(xpath=OR.Document_Done)
	public WebElement Document_Done;
	
	@FindBy(xpath=OR.Group_Folio_BillingInfo_PostalCode)
	public WebElement Group_Folio_BillingInfo_PostalCode;
	
	@FindBy(xpath=OR.Group_Folio_BillingInfo_AccountNumber)
	public WebElement Group_Folio_BillingInfo_AccountNumber;
	
	@FindBy(xpath=OR.Group_Folio_BillingInfo_ExpiryDate)
	public WebElement Group_Folio_BillingInfo_ExpiryDate;
	
	@FindBy(xpath=OR.GroupFolio_BillingInfo_SetMainPaymentMethod_Check)
	public WebElement GroupFolio_BillingInfo_SetMainPaymentMethod_Check;
	
	@FindBy(xpath = OR.ratePlan)
	public WebElement ratePlan;
	
	@FindBy(xpath = OR.GroupBlockRoomQGR)
	public WebElement GroupBlockRoomQGR;
	
	@FindBy(xpath = OR.GroupBlockRoomTotal)
	public WebElement GroupBlockRoomTotal;
	
	@FindBy(xpath = OR.GroupPreShoulder_Add)
	public WebElement GroupPreShoulder_Add;
	
	@FindBy(xpath = OR.GroupPreShoulder_Remove)
	public WebElement GroupPreShoulder_Remove;
	
	@FindBy(xpath = OR.GroupPreShoulder_Text)
	public WebElement GroupPreShoulder_Text;
	
	@FindBy(xpath = OR.GroupPostShoulder_Add)
	public WebElement GroupPostShoulder_Add;
	
	@FindBy(xpath = OR.GroupPostShoulder_Remove)
	public WebElement GroupPostShoulder_Remove;
	
	@FindBy(xpath = OR.GroupPostShoulder_Text)
	public WebElement GroupPostShoulder_Text;
	
	@FindBy(xpath=OR.block_Details)
	public WebElement block_Details;
	
	@FindBy(xpath=OR.Rate_Quote_Tittle)
	public WebElement Rate_Quote_Tittle;
	
	@FindBy(xpath=OR.Edit_block_details)
	public WebElement Edit_block_details;
	
	@FindBy(xpath=OR.Edit_block_Name)
	public WebElement Edit_block_Name;
	
	@FindBy(xpath=OR.Edit_block_Arrive_date)
	public WebElement Edit_block_Arrive_date;
	
	@FindBy(xpath=OR.Edit_block_Depart)
	public WebElement Edit_block_Depart;
	
	@FindBy(xpath=OR.Edit_block_QGR_Value)
	public WebElement Edit_block_QGR_Value;
	
	@FindBy(xpath=OR.Edit_block_Expected_Revenue)
	public WebElement Edit_block_Expected_Revenue;
	
	@FindBy(xpath=OR.Edit_block_RoomNinght)
	public WebElement Edit_block_RoomNinght;
	
	@FindBy(xpath=OR.Edit_block_RoomBlocks)
	public WebElement Edit_block_RoomBlocks;
	
	@FindBy(xpath=OR.Edit_block_ActuvalQTG_Value)
	public WebElement Edit_block_ActuvalQTG_Value;
	
	@FindBy(xpath=OR.Edit_block_total_Amount)
	public WebElement Edit_block_total_Amount;
	
	@FindBy(xpath=OR.GroupAccountDetails)
	public WebElement GroupAccountDetails;
	
	@FindBy(xpath=OR.SelectRoom_context)
	public WebElement SelectRoom_context;
	
	@FindBy(xpath=OR.oldGroups_Amount_Roominglistpopup)
	public WebElement oldGroups_Amount_Roominglistpopup;
	
	@FindBy(xpath=OR.RelseDate_PopUp)
	public WebElement RelseDate_PopUp;
	
	@FindBy(xpath=OR.Rate_Quote_Room_Ninghts)
	public WebElement Rate_Quote_Room_Ninghts;
	
	@FindBy(xpath=OR.Summary_Room_Ninght)
	public WebElement Summary_Room_Ninght;
	
	@FindBy(xpath=OR.Summary_Room_Blocks)
	public WebElement Summary_Room_Blocks;
	
	@FindBy(xpath=OR.Arrive_Date)
	public WebElement Arrive_Date;
	
	@FindBy(xpath=OR.Departure_Date)
	public WebElement Departure_Date;
	
	@FindBy(xpath=OR.Room_QRg_Amount)
	public WebElement Room_QRg_Amount;
	
	@FindBy(xpath=OR.Total_Amount)
	public WebElement Total_Amount;
	
	@FindBy(xpath=OR.Account_Detail_Expected_Revenue)
	public WebElement Account_Detail_Expected_Revenue;
	
	@FindBy(xpath=OR.Room_Block_ArriveDate)
	public WebElement Room_Block_ArriveDate;
	
	@FindBy(xpath=OR.Room_Block_Depart)
	public WebElement Room_Block_Depart;
	
	@FindBy(xpath=OR.Room_Block_PromoCod)
	public WebElement Room_Block_PromoCod;
	
	@FindBy(xpath=OR.Room_Block_Popup)
	public WebElement Room_Block_Popup;
	
	@FindBy(xpath=OR.ReservationPage_Promocode)
	public WebElement ReservationPage_Promocode;
	
	@FindBy(xpath=OR.Reservation_ACCNO)
	public WebElement Reservation_ACCNO;
	
	@FindBy(xpath=OR.Reservation_start_date)
	public WebElement Reservation_start_date;
	
	@FindBy(xpath=OR.Reservation_end_date)
	public WebElement Reservation_end_date;
	
	@FindBy(xpath=OR.Reservation_Folio_Balence)
	public WebElement Reservation_Folio_Balence;
	
	@FindBy(xpath=OR.Reservation_Total)
	public WebElement Reservation_Total;
	
	@FindBy(xpath=OR.Reservation_LineItem_effectivedate)
	public WebElement Reservation_LineItem_effectivedate;
	
	@FindBy(xpath=OR.Reservation_Room_Charges)
	public WebElement Reservation_Room_Charges;
	
	@FindBy(xpath=OR.Reservation_incidentals)
	public WebElement Reservation_incidentals;
	
	@FindBy(xpath=OR.Reservation_balanc)
	public WebElement Reservation_balanc;
	
	@FindBy(xpath=OR.Reservation_folio_Tab)
	public WebElement Reservation_folio_Tab;
	
	@FindBy(xpath=OR.Reservation_folio_ACC)
	public WebElement Reservation_folio_ACC;
	
	@FindBy(xpath=OR.Rooming_list_Block_Name)
	public WebElement Rooming_list_Block_Name;
	
	@FindBy(xpath=OR.Rooming_list_Arrive_Date)
	public WebElement Rooming_list_Arrive_Date;
	
	@FindBy(xpath=OR.Rooming_list_Statu)
	public WebElement Rooming_list_Statu;
	
	@FindBy(xpath=OR.Rooming_list_Depart_Date)
	public WebElement Rooming_list_Depart_Date;
	
	@FindBy(xpath=OR.Rooming_list_QRG_value)
	public WebElement Rooming_list_QRG_value;
	
	@FindBy(xpath=OR.Rooming_list_Expected_Revenue)
	public WebElement Rooming_list_Expected_Revenue;
	
	@FindBy(xpath=OR.Rooming_list_PickedUp_Revenue)
	public WebElement Rooming_list_PickedUp_Revenue;
	
	@FindBy(xpath=OR.Rooming_list_PickedUp_percentage)
	public WebElement Rooming_list_PickedUp_percentage;
	
	@FindBy(xpath=OR.Rooming_List_grid_chkHideDates)
	public WebElement Rooming_List_grid_chkHideDates;
	
	@FindBy(xpath=OR.Rooming_List_grid_Arrive_date)
	public WebElement Rooming_List_grid_Arrive_date;
	
	@FindBy(xpath=OR.Rooming_List_grid_deprt_date)
	public WebElement Rooming_List_grid_deprt_date;
	
	

	
	@FindBy(xpath=OR.PickUp_Summary_Block_Name)
	public WebElement PickUp_Summary_Block_Name;
	
	@FindBy(xpath=OR.PickUp_Summary_Arrive_Date)
	public WebElement PickUp_Summary_Arrive_Date;
	
	@FindBy(xpath=OR.PickUp_Summary_QGR_Value)
	public WebElement PickUp_Summary_QGR_Value;
	
	@FindBy(xpath=OR.PickUp_Summary_Depart_Date)
	public WebElement PickUp_Summary_Depart_Date;
	
	@FindBy(xpath=OR.PickUp_Summary_Status)
	public WebElement PickUp_Summary_Status;
	
	@FindBy(xpath=OR.PickUp_Summary_Expected_Revenue)
	public WebElement PickUp_Summary_Expected_Revenue;
	
	@FindBy(xpath=OR.PickUp_Summary_PickedUp_Revenue)
	public WebElement PickUp_Summary_PickedUp_Revenue;
	
	@FindBy(xpath=OR.PickUp_Summary_PickedUp_percentage)
	public WebElement PickUp_Summary_PickedUp_percentage;
	
	@FindBy(xpath=OR.PickUp_Summary_Reservation_Number)
	public WebElement PickUp_Summary_Reservation_Number;
	
	@FindBy(xpath=OR.PickUp_Summary_Grid_Arrive_date)
	public WebElement PickUp_Summary_Grid_Arrive_date;
	
	@FindBy(xpath=OR.PickUp_Summary_Grid_Depart_date)
	public WebElement PickUp_Summary_Grid_Depart_date;
	
	@FindBy(xpath=OR.Rooming_List_tittle)
	public WebElement Rooming_List_tittle;
	
	@FindBy(xpath=OR.Rooming_list_Billing_FristName)
	public WebElement Rooming_list_Billing_FristName;
	
	@FindBy(xpath=OR.Rooming_list_Billing_lastName)
	public WebElement Rooming_list_Billing_lastName;
	
	@FindBy(xpath=OR.Rooming_list_Billing_Phone_Number)
	public WebElement Rooming_list_Billing_Phone_Number;
	
	@FindBy(xpath=OR.Rooming_list_Billing_Address)
	public WebElement Rooming_list_Billing_Address;
	
	@FindBy(xpath=OR.Rooming_list_Billing_city)
	public WebElement Rooming_list_Billing_city;
	
	@FindBy(xpath=OR.Rooming_list_Billing_state)
	public WebElement Rooming_list_Billing_state;
	
	@FindBy(xpath=OR.Rooming_list_Billing_postal_code)
	public WebElement Rooming_list_Billing_postal_code;
	
	@FindBy(xpath=OR.Rooming_list_Billing_country)
	public WebElement Rooming_list_Billing_country;
	
	@FindBy(xpath=OR.Group_PreScheduler)
	public WebElement Group_PreScheduler;
	
	@FindBy(xpath=OR.Group_PostScheduler)
	public WebElement Group_PostScheduler;
	
	@FindBy(xpath=OR.Group_Block_Edit)
	public WebElement Group_Block_Edit;
	
	@FindBy(xpath=OR.Group_PreSchedulerMinus)
	public WebElement Group_PreSchedulerMinus;
	
	@FindBy(xpath=OR.Group_PostSchedulerMinus)
	public WebElement Group_PostSchedulerMinus;
	
	@FindBy(xpath=OR.Group_Edit_Block_Save)
	public WebElement Group_Edit_Block_Save;
	
	@FindBy(xpath=OR.Group_template_Save)
	public WebElement Group_template_Save;
	
	@FindBy(xpath=OR.Group_Block_Save_Message)
	public WebElement Group_Block_Save_Message;
	
	@FindBy(xpath=OR.Group_Edit_Block_Done)
	public WebElement Group_Edit_Block_Done;
	
	//Select Rooms
	@FindBy(xpath=OR.Select_Rooms_Number)
	public WebElement Select_Rooms_Number;
	
	@FindBy(xpath=OR.Select_Rooms_Save_Button)
	public WebElement Select_Rooms_Save_Button;
	
	@FindBy(xpath=OR.Select_Rooms_done_Button)
	public WebElement Select_Rooms_done_Button;
	
	@FindBy(xpath=OR.Select_Rooms_Book_icon)
	public WebElement Select_Rooms_Book_icon;
	
	@FindBy(xpath=OR.Group_select_Rooms)
	public WebElement Group_select_Rooms;
	
	@FindBy(xpath=OR.Select_Rooms_Go)
	public WebElement Select_Rooms_Go;
	
	@FindBy(xpath=OR.Verify_Rooms_Number)
	public WebElement Verify_Rooms_Number;
	
	@FindBy(xpath=OR.Remove_Selected_Room)
	public WebElement Remove_Selected_Room;
	
	@FindBy(xpath=OR.Select_Depart_Date_Groups)
	public WebElement Select_Depart_Date_Groups;
	
	@FindBy(xpath=OR.Select_CategoryItemDetail)
	public WebElement Select_CategoryItemDetail;
	
	@FindBy(xpath=OR.DescriptionItemDetail)
	public WebElement DescriptionItemDetail;
	
	@FindBy(xpath=OR.AddItemDetail)
	public WebElement AddItemDetail;
	

	@FindBy(xpath=OR.RoomBlockInBlockDetails)
	public WebElement RoomBlockInBlockDetails;
	
	@FindBy(xpath=OR.PickedupInBlockInfo)
	public WebElement PickedupInBlockInfo;
	
	@FindBy(xpath=OR.RoomPerNightInBlockDetails)
	public WebElement RoomPerNightInBlockDetails;
	
	@FindBy(xpath=OR.ReleaseDateInBlockDetails)
	public WebElement ReleaseDateInBlockDetails;
	
	@FindBy(xpath=OR.PickedupInBlockDetails)
	public WebElement PickedupInBlockDetails;
	
	@FindBy(id=OR.SelectRoomsButton)
	public WebElement SelectRoomsButton;
	
	@FindBy(xpath=OR.SelectRoomHeading)
	public WebElement SelectRoomHeading;
	
	@FindBy(id=OR.SelectRoomClassInSelectRooms)
	public WebElement SelectRoomClassInSelectRooms;
	
	@FindBy(id=OR.ClickOnDoneInSelectRooms)
	public WebElement ClickOnDoneInSelectRooms;

	//RoomingList Part Start from here
	
	@FindBy(id=OR.RoomingListButton)
	public WebElement RoomingListButton;
	
	@FindBy(xpath=OR.blockName)
	public WebElement blockName;
	
	@FindBy(xpath=OR.arrive)
	public WebElement arrive;
	
	@FindBy(xpath=OR.depart)
	public WebElement depart;
	
	@FindBy(xpath=OR.qgr)
	public WebElement qgr;
	
	@FindBy(xpath=OR.reservationStatus)
	public WebElement reservationStatus;
	
	@FindBy(xpath=OR.expectedRevenue)
	public WebElement expectedRevenue;
	
	@FindBy(xpath=OR.pickupRevenue)
	public WebElement pickupRevenue;
	
	@FindBy(xpath=OR.pickupPercent)
	public WebElement pickupPercent;
	
	@FindBy(xpath=OR.firstNameInput)
	public WebElement firstNameInput;
	
	@FindBy(xpath=OR.lastNameInput)
	public WebElement lastNameInput;
	
	@FindBy(xpath=OR.selectRoomClass)
	public WebElement selectRoomClass;
	
	@FindBy(xpath=OR.selectRoomNumber)
	public WebElement selectRoomNumber;
	
	@FindBy(xpath=OR.amountInput)
	public WebElement amountInput;
	
	@FindBy(xpath=OR.billingInfoIcon)
	public WebElement billingInfoIcon;
	
	@FindBy(xpath=OR.pickupButton)
	public WebElement pickupButton;
	
	@FindBy(xpath=OR.imgTick)
	public WebElement imgTick;
	
	@FindBy(xpath=OR.headingBillingINfo)
	public WebElement headingBillingINfo;
	
	@FindBy(xpath=OR.selectSalulation)
	public WebElement selectSalulation;
	
	@FindBy(xpath=OR.selectPaymentMethod)
	public WebElement selectPaymentMethod;
	
	@FindBy(xpath=OR.accountInput)
	public WebElement accountInput;
	
	@FindBy(xpath=OR.expiryDateInput)
	public WebElement expiryDateInput;
	
	@FindBy(xpath=OR.saveBillingInfoInput)
	public WebElement saveBillingInfoInput;
	
	@FindBy(id=OR.RoomingListSummary_BlockName)
	public WebElement RoomingListSummaryBlockName;
		
	@FindBy(id=OR.RoomingListSummary_Status)
	public WebElement RoomingListSummaryStatus;
	
	@FindBy(id=OR.RoomingListSummary_ArriveDate)
	public WebElement RoomingListSummaryArriveDate;
	
	@FindBy(id=OR.RoomingListSummary_DepartDate)
	public WebElement RoomingListSummaryDepartDate;
	
	@FindBy(id=OR.RoomingListSummary_Qgr)
	public WebElement RoomingListSummaryQgr;
	
	@FindBy(id=OR.RoomingListSummary_ExpectedRevenue)
	public WebElement RoomingListSummaryExpectedRevenue;
	
	@FindBy(id=OR.RoomingListSummary_PickedupRdevenue)
	public WebElement RoomingListSummaryPickedupRdevenue;
	
	@FindBy(id=OR.RoomingListSummary_PikedupPercentage)
	public WebElement RoomingListSummaryPickedupPercentage;
	
	@FindBy(xpath=OR.ClosePickedupSummary)
	public WebElement ClosePickedupSummary;
	
	@FindBy(id=OR.GetReservationNumberfromRoomingListSummary)
	public WebElement GetReservationNumberfromRoomingListSummary;
	
	@FindBy(xpath=OR.GuestNameInReservationDetails)
	public WebElement GuestNameInReservationDetails;
	
	@FindBy(xpath=OR.GroupNameInReservationDetails)
	public WebElement GroupNameInReservationDetails;
	
	@FindBy(xpath=OR.ConfirmationNumberInReservationDetails)
	public WebElement ConfirmationNumberInReservationDetails;
	
	@FindBy(xpath=OR.TripTotalInReservationDetails)
	public WebElement TripTotalInReservationDetails;
	
	@FindBy(xpath=OR.BalanceInReservationDetails)
	public WebElement BalanceInReservationDetails;
	
	@FindBy(xpath=OR.StartCheckInButtonInReservationDetails)
	public WebElement StartCheckInButtonInReservationDetails;
	
	@FindBy(xpath=OR.CheckinDateInReservtionDetails)
	public WebElement CheckinDateInReservtionDetails;
	
	@FindBy(xpath=OR.CheckoutDateInResrvationDetails)
	public WebElement CheckoutDateInResrvationDetails;
	
	@FindBy(xpath=OR.RoomClassInResrvationDetails)
	public WebElement RoomClassInResrvationDetails;
	
	@FindBy(xpath=OR.RoomNumberInResrvationDetails)
	public WebElement RoomNumberInResrvationDetails;
	
	@FindBy(xpath=OR.RatePlanInReservationDetails)
	public WebElement RatePlanInReservationDetails;
	
	@FindBy(xpath=OR.PromoInReservationDetails)
	public WebElement PromoInReservationDetails;
	
	@FindBy(xpath=OR.CloseReservationDetailsPopup)
	public WebElement CloseReservationDetailsPopup;
	
	@FindBy(xpath=OR.CloseRoomingListPopup)
	public WebElement CloseRoomingListPopup;
	
	@FindBy(id = OR.groupFolioTab)
	public WebElement groupFolioTab;
	
	@FindBy(id=OR.folioOptionNoShowPolicy)
	public WebElement folioOptionNoShowPolicy;
	
	@FindBy(id = OR.GroupFolioOptionButton)
	public WebElement GroupFolioOptionButton;

	
	
	
	
}
