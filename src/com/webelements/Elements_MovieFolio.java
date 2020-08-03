package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.pages.NewFolio;
import com.innroad.inncenter.properties.OR;

public class Elements_MovieFolio {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_MovieFolio(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = OR.MoveFolio_Folio)
	public WebElement MoveFolio_Folio;

	@FindBy(xpath = OR.MoveFolioFolio)
	public WebElement MoveFolioFolio;

	@FindBy(xpath = OR.MoveFolio_GuestFolio)
	public WebElement MoveFolio_GuestFolio;
	
	@FindBy(xpath = OR.MoveFolio_SelectFiloItem2)
	public WebElement MoveFolio_SelectFiloItem2;

	@FindBy(xpath = OR.selectPaymentFolio)
	public WebElement selectPaymentFolio;
	
	@FindBy(xpath = OR.MoveFolio_NewFolio)
	public WebElement MoveFolio_NewFolio;

	@FindBy(xpath = OR.MoveFolio_NewFolio_Name)
	public WebElement MoveFolio_NewFolio_Name;

	@FindBy(xpath = OR.MoveFolio_NewFolio_Description)
	public WebElement MoveFolio_NewFolio_Description;

	@FindBy(xpath = OR.MoveFolio_NewFolio_Close)
	public WebElement MoveFolio_NewFolio_Close;

	@FindBy(xpath = OR.MoveFolio_NewFolio_Save)
	public WebElement MoveFolio_NewFolio_Save;

	@FindBy(xpath = OR.MoveFolio_NewFolio_SaveReservation)
	public WebElement MoveFolio_NewFolio_SaveReservation;

	@FindBy(xpath = OR.MoveFolio_SelectFiloItem)
	public WebElement MoveFolio_SelectFiloItem;

	@FindBy(xpath = OR.MoveFolio_Move)
	public WebElement MoveFolio_Move;

	@FindBy(xpath = OR.MoveFolio_TargetFolio)
	public WebElement MoveFolio_TargetFolio;
	
	@FindBy(xpath = OR.MoveFolio_FolioItemToMove_2)
	public WebElement MoveFolio_FolioItemToMove_2;

	@FindBy(xpath = OR.MoveFolio_FolioItemToMove)
	public WebElement MoveFolio_FolioItemToMove;

	@FindBy(xpath = OR.MoveFolio_MoveSelectedItem)
	public WebElement MoveFolio_MoveSelectedItem;

	@FindBy(xpath = OR.MoveFolio_Close)
	public WebElement MoveFolio_Close;

	@FindBy(xpath = OR.NewRervations)
	public WebElement NewRervations;

	@FindBy(xpath = OR.GuestInfo)
	public WebElement GuestInfo;

	@FindBy(xpath = OR.Reservations)
	public WebElement Reservations;

	@FindBy(xpath = OR.Reservation_SecNav)
	public WebElement Reservation_SecNav;

	@FindBy(xpath = OR.FirstOpenedReservation)
	public WebElement FirstOpenedReservation;

	@FindBy(xpath = OR.FirstOpenedReservationClose)
	public WebElement FirstOpenedReservationClose;

	@FindBy(xpath = OR.Edit_Folio_Btn)
	public WebElement Edit_Folio_Btn;

	@FindBy(xpath = OR.Delete_Folio_Btn)
	public WebElement Delete_Folio_Btn;

	@FindBy(xpath = OR.Folio_Options)
	public WebElement Folio_Options;

	@FindBy(xpath = OR.Folio_Option)
	public WebElement Folio_Option;
	
	@FindBy(xpath = OR.switchToFolio)
	public WebElement switchToFolio;

	@FindBy(xpath = OR.Select_DepositPolicy)
	public WebElement Select_DepositPolicy;

	@FindBy(xpath = OR.Save_DepositPolicy)
	public WebElement Save_DepositPolicy;

	@FindBy(xpath = OR.SaveDepositPolicy)
	public WebElement SaveDepositPolicy;

	@FindBy(xpath = OR.Save_DepositPolicies)
	public WebElement Save_DepositPolicies;

	@FindBy(xpath = OR.Reservation_Tab)
	public WebElement Reservation_Tab;

	@FindBy(xpath = OR.Select_CheckIN)
	public WebElement Select_CheckIN;

	@FindBy(xpath = OR.Select_NoShow)
	public WebElement Select_NoShow;

	@FindBy(xpath = OR.Folio_Tab)
	public WebElement Folio_Tab;

	@FindBy(xpath = OR.Click_Folio_Tab)
	public WebElement Click_Folio_Tab;

	@FindBy(xpath = OR.Click_NewFolio)
	public WebElement Click_NewFolio;

	@FindBy(xpath = OR.New_FolioName)
	public WebElement New_FolioName;

	@FindBy(xpath = OR.New_FolioDescription)
	public WebElement New_FolioDescription;

	@FindBy(xpath = OR.Folio_Tab_Save)
	public WebElement Folio_Tab_Save;

	@FindBy(xpath = OR.Folio_Tab_Close)
	public WebElement Folio_Tab_Close;

	@FindBy(xpath = OR.Select_DepositPolicies)
	public WebElement Select_DepositPolicies;

	@FindBy(xpath = NewFolio.Cancellation_Policy)
	public WebElement Cancellation_Policy;

	@FindBy(xpath = NewFolio.CancelPolicyPicker_Popup)
	public WebElement CancelPolicyPicker_Popup;

	@FindBy(xpath = NewFolio.AvailableCancellationPolicies)
	public WebElement AvailableCancellationPolicies;

	@FindBy(xpath = NewFolio.SelectedCancellationPolicies)
	public WebElement SelectedCancellationPolicies;

	@FindBy(xpath = NewFolio.ClickAddOne_Cancelpolicy)
	public WebElement ClickAddOne_Cancelpolicy;

	@FindBy(xpath = NewFolio.ClickDiscardAll_CancelPolicy)
	public WebElement ClickDiscardAll_CancelPolicy;
	
	@FindBy(xpath = NewFolio.ClickDone_CancelPolicy)
	public WebElement ClickDone_CancelPolicy;

	@FindBy(xpath = OR.Post_LineItem)
	public WebElement Post_LineItem;

	@FindBy(xpath = OR.ChargeRouting_MoveToAccFolio)
	public WebElement ChargeRouting_MoveToAccFolio;

	@FindBy(xpath = OR.ChargeRouting_MoveToAccFolio1)
	public WebElement ChargeRouting_MoveToAccFolio1;

	@FindBy(xpath = OR.ChargeRouting_FolioPrintingCheckBox)
	public WebElement ChargeRouting_FolioPrintingCheckBox;

	@FindBy(xpath = OR.Folio_Details)
	public WebElement Folio_Details;

	@FindBy(xpath = OR.Folio_Details1)
	public WebElement Folio_Details1;

	@FindBy(xpath = OR.SelectAllLineitems)
	public WebElement SelectAllLineitems;

	@FindBy(xpath = OR.ApplyRoutingButton)
	public WebElement ApplyRoutingButton;

	@FindBy(xpath = OR.ConfirmApplyRouting)
	public WebElement ConfirmApplyRouting;

	@FindBy(xpath = OR.ConfirmApplyRouting_OK)
	public WebElement ConfirmApplyRouting_OK;

	@FindBy(xpath = OR.Verify_Toaster_Container)
	public WebElement Verify_Toaster_Container;

	@FindBy(xpath = OR.MoveFolio_NewFolioDeatils)
	public WebElement MoveFolio_NewFolioDeatils;

	@FindBy(xpath = OR.getRes1LineItem)
	public WebElement getRes1LineItem;
	
	@FindBy(xpath = OR.getRes1LineItem2)
	public WebElement getRes1LineItem2;
	
	@FindBy(xpath = OR.getRes1LineItem3)
	public WebElement getRes1LineItem3;
	
	@FindBy(xpath = OR.getRes1LineItem4)
	public WebElement getRes1LineItem4;
	
	@FindBy(xpath = OR.getRes1LineItem5)
	public WebElement getRes1LineItem5;
	
	@FindBy(xpath = NewFolio.SaveFolio)
	public WebElement SaveFolio;

	@FindBy(xpath = NewFolio.MoveFolio_NewFolio_Save1)
	public WebElement MoveFolio_NewFolio_Save1;
	
	@FindBy(xpath = OR.ChargeRoutingPopText)
	public WebElement ChargeRoutingPopText;
	
	@FindBy(xpath = OR.clickOnSave)
	public WebElement clickOnSave;
}
