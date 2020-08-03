package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class Elements_OldGroups {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_OldGroups(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);

	}

	@FindBy(xpath = OR.GroupsNewAccount)
	public WebElement GroupsNewAccount;

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

	@FindBy(xpath = OR.oldGroup_startdate)
	public WebElement oldGroup_startdate;

	@FindBy(xpath = OR.oldGroup_numberofNights)
	public WebElement oldGroup_numberofNights;

	@FindBy(xpath = OR.oldGroup_AccountNumber)
	public WebElement oldGroup_AccountNumber;

	@FindBy(xpath = OR.oldGroup_Today)
	public WebElement oldGroup_Today;

	@FindBy(xpath = OR.oldGroup_Adults)
	public WebElement oldGroup_Adults;

	@FindBy(xpath = OR.old_Groups_Click_Search)
	public WebElement old_Groups_Click_Search;

	@FindBy(xpath = OR.GetNumberofclasses)
	public List<WebElement> GetNumberofclasses;

	@FindBy(xpath = OR.oldGroups_Click_Groups)
	public WebElement oldGroups_Click_Groups;

	@FindBy(xpath = OR.oldGroups_Account_Search)
	public WebElement oldGroups_Account_Search;

	@FindBy(xpath = OR.oldGroups_Account_ClickGo)
	public WebElement oldGroups_Account_ClickGo;

	@FindBy(xpath = OR.oldGroups_Account_VerifyAccountNumber)
	public WebElement oldGroups_Account_VerifyAccountNumber;

	@FindBy(xpath = OR.oldGroup_SearchBookicon)
	public WebElement oldGroup_SearchBookicon;

	@FindBy(xpath = OR.oldGroup_Click_Roominglistbutton)
	public WebElement oldGroup_Click_Roominglistbutton;

	@FindBy(xpath = OR.oldGroups_Enter_FirstName_Roominglistpopup)
	public WebElement oldGroups_Enter_FirstName_Roominglistpopup;

	@FindBy(xpath = OR.oldGroups_Enter_LastName_Roominglistpoup)
	public WebElement oldGroups_Enter_LastName_Roominglistpoup;

	@FindBy(xpath = OR.oldGroups_Select_Roomclass_Roominglistpoup)
	public WebElement oldGroups_Select_Roomclass_Roominglistpoup;

	@FindBy(xpath = OR.oldGroups_Select_RoomNo_Roominglistpopup)
	public WebElement oldGroups_Select_RoomNo_Roominglistpopup;

	@FindBy(xpath = OR.oldGroups_Select_Pickupbutton_Roominglistpopup)
	public WebElement oldGroups_Select_Pickupbutton_Roominglistpopup;

	@FindBy(xpath = OR.oldGroup_Click_Close_Roominglistsummary)
	public WebElement oldGroup_Click_Close_Roominglistsummary;

	@FindBy(xpath = OR.oldGroup_AccountSearch_Accountnum)
	public WebElement oldGroup_AccountSearch_Accountnum;

	@FindBy(xpath = OR.Verify_Block_Details_Popup)
	public WebElement Verify_Block_Details_Popup;

	@FindBy(xpath = OR.NewBlock_Click_Ok)
	public WebElement NewBlock_Click_Ok;

	@FindBy(id = OR.NewBlock_Click_Cancel)
	public WebElement NewBlock_Click_Cancel;

	@FindBy(xpath = OR.RateQuotePage)
	public WebElement RateQuotePage;

	@FindBy(xpath = OR.RoomClassNameSearchResult)
	public WebElement RoomClassNameSearchResult;

	@FindBy(id = OR.ReleaseNote_OkBtn)
	public WebElement ReleaseNote_OkBtn;

	@FindBy(xpath = OR.BlockToSelectFromDropDown)
	public WebElement BlockToSelectFromDropDown;

	@FindBy(id = OR.EditBtn)
	public WebElement EditBtn;

	@FindBy(id = OR.SelectRoomsBtn)
	public WebElement SelectRoomsBtn;

	@FindBy(id = OR.RoomListingBtn)
	public WebElement RoomListingBtn;

	@FindBy(xpath = OR.RoomingList_PickUpRooms)
	public WebElement RoomingList_PickUpRooms;

	@FindBy(id = OR.DeleteBtn)
	public WebElement DeleteBtn;

	@FindBy(id = OR.BlockDetails_AdultAddBtn)
	public WebElement BlockDetails_AdultAddBtn;

	@FindBy(id = OR.BlockDetails_SaveBtn)
	public WebElement BlockDetails_SaveBtn;

	@FindBy(id = OR.BlockDetails_SaveBtnList)
	public List<WebElement> BlockDetails_SaveBtnList;

	@FindBy(id = OR.BlockDetails_DoneBtn)
	public WebElement BlockDetails_DoneBtn;

	@FindBy(id = OR.BlockDetails_CancelBtn)
	public WebElement BlockDetails_CancelBtn;

	@FindBy(xpath = OR.RoomblockDetailedPage)
	public WebElement RoomblockDetailedPage;

	@FindBy(id = OR.oldGroup_RoomListingBillingInfo_SaveButton)
	public WebElement oldGroup_RoomListingBillingInfo_SaveButton;

	@FindBy(id = OR.oldGroup_RoomListingBillingInfo_CancelButton)
	public WebElement oldGroup_RoomListingBillingInfo_CancelButton;

	@FindBy(xpath = OR.ReservationgroupAccount_List_GuestName)
	public WebElement ReservationgroupAccount_List_GuestName;

	@FindBy(id = OR.oldGroups_Amount_Roominglistpopup)
	public WebElement oldGroups_Amount_Roominglistpopup;

	@FindBy(id = OR.oldGroups_ClickBillingInfo_Roominglistpopup)
	public WebElement oldGroups_ClickBillingInfo_Roominglistpopup;

	@FindBy(id = OR.oldGroup_RoominglistsummaryPage)
	public WebElement oldGroup_RoominglistsummaryPage;

	@FindBy(id = OR.Navigate_ReservationGroupAccount)
	public WebElement Navigate_ReservationGroupAccount;

	@FindBy(id = OR.old_Groups_ReservationGroupAccountPage)
	public WebElement old_Groups_ReservationGroupAccountPage;
	
	@FindBy(xpath = OR.oldGroup_NewBlock)
	public WebElement oldGroup_NewBlock;
	
	@FindBy(xpath = OR.oldGroup_NewBlock_RoomBlobkDetailsPOpUp)
	public WebElement oldGroup_NewBlock_RoomBlobkDetailsPOpUp;
	
	@FindBy(xpath = OR.oldGroup_NewBlock_BlockName)
	public WebElement oldGroup_NewBlock_BlockName;
	
	@FindBy(xpath = OR.oldGroup_NewBlock_Save)
	public WebElement oldGroup_NewBlock_Save;
	
	@FindBy(xpath = OR.oldGroup_NewBlock_Cancel)
	public WebElement oldGroup_NewBlock_Cancel;
	
	@FindBy(xpath = OR.oldGroup_ArrivalDate)
	public WebElement oldGroup_ArrivalDate;
	
	@FindBy(xpath = OR.oldGroup_DepartDate)
	public WebElement oldGroup_DepartDate;
	
	@FindBy(xpath = OR.oldGroup_NewBlock_Adults)
	public WebElement oldGroup_NewBlock_Adults;
	
	@FindBy(xpath = OR.oldGroup_NewBlock_RatePlan)
	public WebElement oldGroup_NewBlock_RatePlan;
	
	@FindBy(xpath = OR.oldGroup_NewBlock_Children)
	public WebElement oldGroup_NewBlock_Children;
	
	@FindBy(xpath = OR.oldGroup_NewBlock_Search)
	public WebElement oldGroup_NewBlock_Search;
	
	@FindBy(xpath = OR.oldGroup_Pagination)
	public WebElement oldGroup_Pagination;
	
	@FindBy(xpath = OR.oldGroup_RoomingList_Amount)
	public WebElement oldGroup_RoomingList_Amount;
	
	@FindBy(xpath = OR.oldGroup_RoomingList_PaymentInfo)
	public WebElement oldGroup_RoomingList_PaymentInfo;
	
	@FindBy(xpath = OR.oldGroup_RoomingList_PaymentInfo_Save)
	public WebElement oldGroup_RoomingList_PaymentInfo_Save;
	
	@FindBy(xpath = OR.oldGroup_PaymentMethod)
	public WebElement oldGroup_PaymentMethod;
	
	@FindBy(xpath = OR.oldGroup_RoomingList_Pickup)
	public WebElement oldGroup_RoomingList_Pickup;
	
	@FindBy(xpath = OR.oldGroup_RoomingList_Close)
	public WebElement oldGroup_RoomingList_Close;
	
	@FindBy(xpath = OR.DisplayVoidItemButton)
	public WebElement DisplayVoidItemButton;

	@FindBy(xpath = OR.BtnEditBlockName)
	public WebElement BtnEditBlockName;

	@FindBy(xpath = OR.TxtEditBlockName)
	public WebElement TxtEditBlockName;

	@FindBy(xpath = OR.LblEditBlockName)
	public WebElement LblEditBlockName;

	@FindBy(xpath = OR.Grp_GCPickerSelectButton)
	public WebElement Grp_GCPickerSelectButton;

	@FindBy(xpath = OR.Grp_GCPickerSearchField)
	public WebElement Grp_GCPickerSearchField;

	@FindBy(xpath = OR.Grp_GCPickerGoButton)
	public WebElement Grp_GCPickerGoButton;

	@FindBy(xpath = OR.Grp_GCPickerCancelButton)
	public WebElement Grp_GCPickerCancelButton;

	@FindBy(xpath = OR.Grp_GCPickerNoRecordMeet)
	public WebElement Grp_GCPickerNoRecordMeet;

	@FindBy(xpath = OR.Grp_AmountSold)
	public WebElement Grp_AmountSold;

	@FindBy(xpath = OR.Grp_RedeemedAmount)
	public WebElement Grp_RedeemedAmount;

	@FindBy(xpath = OR.Grp_Balance)
	public WebElement Grp_Balance;

	@FindBy(xpath = OR.Grp_GiftCertificatPicker)
	public WebElement Grp_GiftCertificatPicker;

	@FindBy(xpath = OR.GroupPayment_Info_Textarea)
	public WebElement GroupPayment_Info_Textarea;

	@FindBy(xpath = OR.Grp_FolioBillingInformationPopUp)
	public WebElement Grp_FolioBillingInformationPopUp;

	@FindBy(xpath = OR.Grp_FolioBillingInfoFirstName)
	public WebElement Grp_FolioBillingInfoFirstName;

	@FindBy(xpath = OR.Grp_FolioBillingInfoLastName)
	public WebElement Grp_FolioBillingInfoLastName;

	@FindBy(xpath = OR.Grp_FolioBillingInfoPaymentMethod)
	public WebElement Grp_FolioBillingInfoPaymentMethod;

	@FindBy(xpath = OR.Grp_FolioBillingInfoSaveButton)
	public WebElement Grp_FolioBillingInfoSaveButton;
	
	@FindBy(xpath = OR.Grp_FolioBillingInfoCancelButton)
	public WebElement Grp_FolioBillingInfoCancelButton;
}
