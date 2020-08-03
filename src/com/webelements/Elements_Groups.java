package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.pages.NewGroups;
import com.innroad.inncenter.properties.OR;

public class Elements_Groups {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_Groups(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);

	}

	@FindBy(xpath = OR.New_Account_Btn)
	public WebElement New_Account_Btn;

	@FindBy(xpath = OR.Group_Name)
	public WebElement Group_Name;

	@FindBy(xpath = OR.Market_Segment)
	public WebElement Market_Segment;

	@FindBy(xpath = OR.Referrls)
	public WebElement Referrls;

	@FindBy(xpath = OR.FirstName)
	public WebElement FirstName;

	@FindBy(xpath = OR.LastName)
	public WebElement LastName;

	@FindBy(xpath = OR.Phone)
	public WebElement Phone;

	@FindBy(xpath = OR.Address1)
	public WebElement Address1;

	@FindBy(xpath = OR.City)
	public WebElement City;

	@FindBy(xpath = OR.Country)
	public WebElement Country;

	@FindBy(xpath = OR.State)
	public WebElement State;

	@FindBy(xpath = OR.PostalCode)
	public WebElement PostalCode;

	@FindBy(xpath = OR.Check_Mailing_Info)
	public WebElement Check_Mailing_Info;

	@FindBy(xpath = OR.Group_Save)
	public WebElement Group_Save;

	@FindBy(xpath = OR.Group_Folio_Add_LineItem)
	public WebElement Group_Folio_Add_LineItem;

	@FindBy(xpath = OR.Group_Folio_Commit_Lineitem)
	public WebElement Group_Folio_Commit_Lineitem;

	@FindBy(xpath = OR.Group_Folio_CardInfo)
	public WebElement Group_Folio_CardInfo;

	@FindBy(xpath = OR.Group_Folio_PaymentMethod)
	public WebElement Group_Folio_PaymentMethod;

	@FindBy(xpath = OR.Group_Folio_NameOnCard)
	public WebElement Group_Folio_NameOnCard;

	@FindBy(xpath = OR.Group_Folio_CardNumber)
	public WebElement Group_Folio_CardNumber;

	@FindBy(xpath = OR.Group_Folio_ExpDate)
	public WebElement Group_Folio_ExpDate;

	@FindBy(xpath = OR.Group_Folio_CVV)
	public WebElement Group_Folio_CVV;

	@FindBy(xpath = OR.Group_Folio_OK)
	public WebElement Group_Folio_OK;

	@FindBy(xpath = OR.Group_Folio_AuthType)
	public WebElement Group_Folio_AuthType;

	@FindBy(xpath = OR.Group_Folio_Process)
	public WebElement Group_Folio_Process;

	@FindBy(xpath = OR.Group_Folio_Amount)
	public WebElement Group_Folio_Amount;

	@FindBy(xpath = OR.AdvanceDepositConfirmPopup_Yes)
	public WebElement AdvanceDepositConfirmPopup_Yes;

	@FindBy(xpath = OR.AdvanceDepositConfirmPopup)
	public WebElement AdvanceDepositConfirmPopup;

	@FindBy(xpath = OR.Group_Folio_Continue)
	public WebElement Group_Folio_Continue;
	
	@FindBy(xpath = OR.Group_Folio_Close)
	public WebElement Group_Folio_Close;

	@FindBy(xpath = OR.Group_Folio_EndingBalance)
	public WebElement Group_Folio_EndingBalance;

	@FindBy(xpath = OR.Group_Folio_Add)
	public WebElement Group_Folio_Add;

	@FindBy(xpath = OR.Group_Folio)
	public WebElement Group_Folio;

	@FindBy(xpath = OR.Group_Folio_AutoApply)
	public WebElement Group_Folio_AutoApply;

	@FindBy(xpath = OR.Group_Folio_AdvanceDeposit)
	public WebElement Group_Folio_AdvanceDeposit;

	@FindBy(xpath = OR.Group_Folio_AdvanceDepositAdd)
	public WebElement Group_Folio_AdvanceDepositAdd;

	@FindBy(xpath = OR.Group_Folio_AdvanceDepositAutoApply)
	public WebElement Group_Folio_AdvanceDepositAutoApply;

	@FindBy(xpath = OR.Group_Folio_AdvanceDepositContinue)
	public WebElement Group_Folio_AdvanceDepositContinue;

	@FindBy(xpath = OR.GroupsAccounts_Link)
	public WebElement GroupsAccounts_Link;

	@FindBy(xpath = OR.GroupsAccounts_Title)
	public WebElement GroupsAccounts_Title;

	@FindBy(xpath = NewGroups.AppplyAdvanceDeposit_DialogContinue)
	public WebElement AppplyAdvanceDeposit_DialogContinue;

	@FindBy(xpath = NewGroups.AppplyAdvanceDeposit_Dialog)
	public WebElement AppplyAdvanceDeposit_Dialog;

	@FindBy(xpath = OR.GroupName)
	public WebElement GroupName;

	@FindBy(xpath = OR.GroupAccountNumber)
	public WebElement GroupAccountNumber;

	@FindBy(xpath = OR.GroupNewResButton)
	public WebElement GroupNewResButton;

	@FindBy(id = OR.Resgroups_AccountName)
	public WebElement Resgroups_AccountName;

	@FindBy(id = OR.Resgroups_AccountNumber)
	public WebElement Resgroups_AccountNumber;

	@FindBy(id = OR.Resgroups_GoButton)
	public WebElement Resgroups_GoButton;

	@FindBy(xpath = OR.Resgroups_ReservToSelect)
	public WebElement Resgroups_ReservToSelect;

	@FindBy(xpath = OR.Resgroups_AccountDetailsPage)
	public WebElement Resgroups_AccountDetailsPage;

	@FindBy(id = OR.RoomBlocksTab)
	public WebElement RoomBlocksTab;

	@FindBy(xpath = OR.GroupAccount_FirstActiveElement)
	public WebElement GroupAccount_FirstActiveElement;

	@FindBy(xpath = OR.Groups_ReservationsTab)
	public WebElement Groups_ReservationsTab;

	@FindBy(xpath = OR.Verify_New_Reservation_Enabled)
	public WebElement Verify_New_Reservation_Enabled;
	@FindBy(xpath = OR.Group_Search_GroupName)
	public WebElement Group_Search_GroupName;

	@FindBy(xpath = OR.Group_Search_Go)
	public WebElement Group_Search_Go;

	@FindBy(xpath = OR.Group_Folio_Pay)
	public WebElement Group_Folio_Pay;

	@FindBy(xpath = OR.Group_Folio_Void)
	public WebElement Group_Folio_Void;

	@FindBy(xpath = OR.Group_Folio_Void_Notes)
	public WebElement Group_Folio_Void_Notes;

	@FindBy(xpath = OR.Group_Folio_Void_Save)
	public WebElement Group_Folio_Void_Save;

	@FindBy(xpath = OR.Group_Done)
	public WebElement Group_Done;

	@FindBy(xpath = OR.DeleteButton)
	public WebElement DeleteButton;

	@FindBy(xpath = OR.Group_FolioOptions)
	public WebElement Group_FolioOptions;

	@FindBy(xpath = OR.Group_FolioOptions_ChargeRouting)
	public WebElement Group_FolioOptions_ChargeRouting;

	@FindBy(xpath = OR.Group_RoomBlock_GroupInfo_ExpectedRevenue)
	public WebElement Group_RoomBlock_GroupInfo_ExpectedRevenue;

	@FindBy(xpath = OR.GroupBillInfoCopyToPickUpReservation)
	public WebElement GroupBillInfoCopyToPickUpReservation;

	@FindBy(xpath = OR.GroupBilling_AccountNo)
	public WebElement GroupBilling_AccountNo;

	@FindBy(xpath = OR.GroupBilling_ExpiryDate)
	public WebElement GroupBilling_ExpiryDate;

	@FindBy(xpath = OR.GroupBilling_BillingNotes)
	public WebElement GroupBilling_BillingNotes;

	@FindBy(xpath = OR.RoomingListBillingInfo_FirstName)
	public WebElement RoomingListBillingInfo_FirstName;

	@FindBy(xpath = OR.RoomingListBillingInfo_LastName)
	public WebElement RoomingListBillingInfo_LastName;

	@FindBy(xpath = OR.RoomingListBillingInfo_Address1)
	public WebElement RoomingListBillingInfo_Address1;

	@FindBy(xpath = OR.RoomingListBillingInfo_PhoneNo)
	public WebElement RoomingListBillingInfo_PhoneNo;

	@FindBy(xpath = OR.RoomingListBilling_AccountNo)
	public WebElement RoomingListBilling_AccountNo;

	@FindBy(xpath = OR.RoomingListBilling_ExpiryDate)
	public WebElement RoomingListBilling_ExpiryDate;

	@FindBy(xpath = OR.RoomingListBilling_BillingNotes)
	public WebElement RoomingListBilling_BillingNotes;

	@FindBy(xpath = OR.RoomingListBillingInfo_PostalCode)
	public WebElement RoomingListBillingInfo_PostalCode;

	@FindBy(xpath = OR.CheckVaryRoomsByDate)
	public WebElement CheckVaryRoomsByDate;

	@FindBy(xpath = OR.RoomingListPickupSummary_BlockName)
	public WebElement RoomingListPickupSummary_BlockName;

	@FindBy(xpath = OR.RoomingListPickupSummary_ArivalDate)
	public WebElement RoomingListPickupSummary_ArivalDate;

	@FindBy(xpath = OR.RoomingListPickupSummary_DepartDate)
	public WebElement RoomingListPickupSummary_DepartDate;

	@FindBy(xpath = OR.RoomingListPickupSummary_Status)
	public WebElement RoomingListPickupSummary_Status;

	@FindBy(xpath = OR.RoomingListPickupSummary_PickUp)
	public WebElement RoomingListPickupSummary_PickUp;

	@FindBy(xpath = OR.GroupFolio_PaymentDetailPopup_CardInfoButton)
	public WebElement GroupFolio_PaymentDetailPopup_CardInfoButton;

	@FindBy(xpath = OR.GroupFolio_PaymentDetail_EncriptDecriptButton)
	public WebElement GroupFolio_PaymentDetail_EncriptDecriptButton;

	@FindBy(xpath = OR.GroupFolio_PaymentDetail_NameOnCard)
	public WebElement GroupFolio_PaymentDetail_NameOnCard;

	@FindBy(xpath = OR.GroupFolio_PaymentDetail_CardNO)
	public WebElement GroupFolio_PaymentDetail_CardNO;

	@FindBy(xpath = OR.GroupFolio_PaymentDetail_ExpiryDate)
	public WebElement GroupFolio_PaymentDetail_ExpiryDate;

	@FindBy(xpath = OR.GroupFolio_PaymentDetail_City)
	public WebElement GroupFolio_PaymentDetail_City;

	@FindBy(xpath = OR.GroupFolio_PaymentDetail_State)
	public WebElement GroupFolio_PaymentDetail_State;

	@FindBy(xpath = OR.GroupFolio_PaymentDetail_PostalCode)
	public WebElement GroupFolio_PaymentDetail_PostalCode;

	@FindBy(xpath = OR.GroupFolio_PaymentDetail_ButtonOK)
	public WebElement GroupFolio_PaymentDetail_ButtonOK;

	@FindBy(xpath = OR.Group_Account)
	public WebElement Group_Account;

	@FindBy(xpath = OR.GroupFolio_PaymentDetail_CVVCode)
	public WebElement GroupFolio_PaymentDetail_CVVCode;

	@FindBy(xpath = OR.GroupFolio_PaymentDetail_TransactionTab)
	public WebElement GroupFolio_PaymentDetail_TransactionTab;

	@FindBy(xpath = OR.GroupFolio_PaymentDetail_CancelButton)
	public WebElement GroupFolio_PaymentDetail_CancelButton;

	@FindBy(xpath = OR.Group_PreviewFolio_Add)
	public WebElement Group_PreviewFolio_Add;

	@FindBy(xpath = OR.Group_PreviewFolio)
	public WebElement Group_PreviewFolio;

	@FindBy(xpath = OR.RoomBlock_GroupName)
	public WebElement RoomBlock_GroupName;

	@FindBy(xpath = OR.RoomBlock_Account)
	public WebElement RoomBlock_Account;

	@FindBy(xpath = OR.RoomBlock_Revenue)
	public WebElement RoomBlock_Revenue;

	@FindBy(xpath = OR.Block_Dates)
	public WebElement Block_Dates;

	@FindBy(xpath = OR.Block_name)
	public WebElement Block_name;

	@FindBy(xpath = OR.GroupAccountDetails)
	public WebElement RoomBlock_releaseDate;

	@FindBy(xpath = OR.Block_Status)
	public WebElement Block_Status;

	@FindBy(xpath = OR.Blocked_Rooms_count)
	public WebElement Blocked_Rooms_count;

	@FindBy(xpath = OR.Total_Room_Ninghts)
	public WebElement Total_Room_Ninghts;

	@FindBy(xpath = OR.Expected_Revenue)
	public WebElement Expected_Revenue;

	@FindBy(xpath = OR.GroupAccountDetails)
	public WebElement GroupAccountDetails;

	@FindBy(xpath = OR.Edit_block_details)
	public WebElement Edit_block_details;

	@FindBy(xpath = OR.GetStartDate)
	public WebElement GetStartDate;

	@FindBy(xpath = OR.GetEndDate)
	public WebElement GetEndDate;

	@FindBy(xpath = OR.Account_Detail_Expected_Revenue)
	public WebElement Account_Detail_Expected_Revenue;

	@FindBy(xpath = OR.Account_Detail_QRG)
	public WebElement Account_Detail_QRG;

	@FindBy(xpath = OR.Account_PickedUp_Revenue)
	public WebElement Account_PickedUp_Revenue;

	@FindBy(xpath = OR.Account_PickedUp_Percentage)
	public WebElement Account_PickedUp_Percentage;

	@FindBy(xpath = OR.Room_Block_PickedUp_Revenue)
	public WebElement Room_Block_PickedUp_Revenue;

	@FindBy(xpath = OR.Room_Block_PickedUp_percentage)
	public WebElement Room_Block_PickedUp_percentage;

	@FindBy(xpath = OR.LineItem_act_Amount)
	public WebElement LineItem_act_Amount;

	@FindBy(xpath = OR.LineItem_add_Amount)
	public WebElement LineItem_add_Amount;

	@FindBy(xpath = OR.LineItem_Room_charges)
	public WebElement LineItem_Room_charges;

	@FindBy(xpath = OR.LineItem_Incidentals)
	public WebElement LineItem_Incidentals;

	@FindBy(xpath = OR.LineItem_total_charges)
	public WebElement LineItem_total_charges;

	@FindBy(xpath = OR.Block_Details_PickedUP_Revenue)
	public WebElement Block_Details_PickedUP_Revenue;

	@FindBy(xpath = OR.Block_Details_PickedUP_percentage)
	public WebElement Block_Details_PickedUP_percentage;

	@FindBy(xpath = OR.LineItem_ArriveDate)
	public WebElement LineItem_ArriveDate;

	@FindBy(id = OR.GroupsEndingBalance)
	public WebElement GroupsEndingBalance;

	@FindBy(xpath = OR.Group_AdvGroup_SelectProperty)
	public WebElement Group_AdvGroup_SelectProperty;

	@FindBy(xpath = OR.Group_AdvGroup_PaymentDetail_SelectProperty)
	public WebElement Group_AdvGroup_PaymentDetail_SelectProperty;

	@FindBy(xpath = OR.AdvGroup_Notes)
	public WebElement AdvGroup_Notes;

	@FindBy(xpath = OR.GroupSwipeIcon)
	public WebElement GroupSwipeIcon;

	@FindBy(xpath = OR.GroupSwipeTextTrackData)
	public WebElement GroupSwipeTextTrackData;

	@FindBy(xpath=OR.GroupSwipeSubmitButton)
	public WebElement GroupSwipeSubmitButton;
	
	@FindBy(xpath=OR.Select_Group_paymethod)
	public WebElement Select_Group_paymethod;
	
	@FindBy(xpath=OR.Click_Group_Card_info)
	public WebElement Click_Group_Card_info;
	
	@FindBy(xpath=OR.Enter_Group_Card_Name)
	public WebElement Enter_Group_Card_Name;
	
	@FindBy(xpath=OR.Enter_CC_Group_Number)
	public WebElement Enter_CC_Group_Number;
	
	@FindBy(xpath=OR.Enter_ExpiryDate_Group)
	public WebElement Enter_ExpiryDate_Group;
	
	@FindBy(xpath=OR.Enter_CCV_Group)
	public WebElement Enter_CCV_Group;
	
	@FindBy(xpath=OR.Click_Ok_Group)
	public WebElement Click_Ok_Group;
	
	@FindBy(xpath=OR.Click_Process_Group)
	public WebElement Click_Process_Group;

	@FindBy(xpath=OR.Enter_Group_Amount)
	public WebElement Enter_Group_Amount;

	@FindBy(xpath=OR.PaymentPopUp_Group)
	public WebElement PaymentPopUp_Group;
	
	@FindBy(xpath=OR.PaymentDetailPopUp_Group)
	public WebElement PaymentDetailPopUp_Group;
	
	@FindBy(xpath=OR.Click_Continue_Group)
	public WebElement Click_Continue_Group;
	
	@FindBy(xpath=OR.Click_Transition_Group)
	public WebElement Click_Transition_Group;
	
	@FindBy(xpath=OR.Click_Outstanding_Group)
	public WebElement Click_Outstanding_Group;

	@FindBy(xpath=OR.FolioOptionCheckInPolicy)
	public WebElement FolioOptionCheckInPolicy;
	
	@FindBy(xpath=OR.FolioOptionCancellationPolicy)
	public WebElement FolioOptionCancellationPolicy;
	
	@FindBy(xpath=OR.FolioOptionCancellationPolicyListSource)
	public WebElement FolioOptionCancellationPolicyListSource;
	
	@FindBy(xpath=OR.FolioOptionCancellationPolicyPickBtn)
	public WebElement FolioOptionCancellationPolicyPickBtn;
	
	@FindBy(xpath=OR.FolioOptionCancellationPolicyDoneBtn)
	public WebElement FolioOptionCancellationPolicyDoneBtn;
	
	@FindBy(xpath=OR.Check_TaxExempt)
	public WebElement Check_TaxExempt;
	
	@FindBy(xpath=OR.Txt_TaxExempt)
	public WebElement Txt_TaxExempt;
	
	@FindBy(xpath=OR.FolioOptionDepositPolicy)
	public WebElement FolioOptionDepositPolicy;
	
	@FindBy(xpath=OR.btnNewGroup)
	public WebElement btnNewGroup;
	
	@FindBy(xpath=OR.reservationDetailPopup)
	public WebElement reservationDetailPopup;
	
	@FindBy(xpath=OR.reservationDetailPopup_Close)
	public WebElement reservationDetailPopup_Close;
	
	@FindBy(xpath=OR.RoomBlockDetails)
	public List<WebElement> roomBlockDetails;
	
	@FindBy(id=OR.FolioOptionCancelationPolicyDeleteAllBtn)
	public WebElement FolioOptionCancelationPolicyDeleteAllBtn;
	
	@FindBy(id=OR.folioOptionNoShowPolicy)
	public WebElement folioOptionNoShowPolicy;
	
}
