package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.innroad.inncenter.properties.OR;

public class WebElementsOverview {

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	WebDriver driver;

	public WebElementsOverview(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);

	}

	@FindBy(xpath = OR.GoButtonInventory)
	public WebElement GoButtonInventory;

	@FindBy(xpath = OR.Calendar)
	public WebElement Calendar;

	@FindBy(xpath = OR.Edit_Rate)
	public WebElement Edit_Rate;

	@FindBy(xpath = OR.Rates_Override_Info_Popup)
	public WebElement Rates_Override_Info_Popup;

	@FindBy(xpath = OR.First_Roomclass_P1_Rate)
	public WebElement First_Roomclass_P1_Rate;

	@FindBy(xpath = OR.First_DBR_Roomclass_P1_Rate)
	public WebElement First_DBR_Roomclass_P1_Rate;

	@FindBy(xpath = OR.Save_Btn_In_Rates_Override_Info_Popup)
	public WebElement Save_Btn_In_Rates_Override_Info_Popup;

	@FindBy(xpath = OR.Done_Btn_In_Rates_Override_Info_Popup)
	public WebElement Done_Btn_In_Rates_Override_Info_Popup;

	@FindBy(xpath = OR.Cancel_Btn_In_Rates_Override_Info_Popup)
	public WebElement Cancel_Btn_In_Rates_Override_Info_Popup;

	@FindBy(xpath = OR.Get_Rack_Rate)
	public WebElement Get_Rack_Rate;

	@FindBy(xpath = OR.RoomClassNameWithRates)
	public List<WebElement> RoomClassNameWithRates;

	@FindBy(xpath = OR.PolicySuite_EvenOutFieldToday)
	public WebElement PolicySuite_EvenOutFieldToday;

	@FindBy(xpath = OR.OverViewSaveBtn)
	public WebElement OverViewSaveBtn;

	@FindBy(xpath = OR.EditOverRatePopup)
	public WebElement EditOverRatePopup;

	@FindBy(xpath = OR.Overview_MaxPerson)
	public List<WebElement> Overview_MaxPersons;

	@FindBy(xpath = OR.Overview_GoButton)
	public WebElement Overview_GoButton;

	@FindBy(xpath = OR.SelectTodaysDate)
	public WebElement SelectTodaysDate;

	@FindBy(xpath = OR.RateOverridenInfo_GoButton)
	public WebElement RateOverridenInfo_GoButton;

	@FindBy(xpath = OR.ActivDate)
	public WebElement ActivDate;

	@FindBy(css = OR.GetTodayDate)
	public WebElement GetTodayDate;

	@FindBy(xpath = OR.DateSelection_EditRatePopup)
	public List<WebElement> DateSelection_EditRatePopup;

	@FindBy(xpath = OR.SelectDate_From)
	public WebElement SelectDate_From;

	@FindBy(xpath = OR.DateSelectionFrom_OverviewPanel)
	public List<WebElement> DateSelectionFrom_OverviewPanel;

	@FindBy(id = OR.SelectRatePlan_EditRate)
	public WebElement SelectRatePlan_EditRate;

	@FindBy(id = OR.SelectRoomClass_EditRate)
	public WebElement SelectRoomClass_EditRate;

	@FindBy(name = OR.GoButton_EditRate)
	public WebElement GoButton_EditRate;

	@FindBy(xpath = OR.OverViewTodayButton)
	public WebElement OverViewTodayButton;

	@FindBy(xpath = OR.OverView_DateStart)
	public WebElement OverView_DateStart;

	@FindBy(xpath = OR.SelectRatePlan_Overview)
	public WebElement SelectRatePlan_Overview;
	
	@FindBy(xpath = OR.RatePopup)
	public WebElement RatePopup;

	@FindBy(xpath = OR.RatePopup_RoomRate)
	public WebElement RatePopup_RoomRate;

	@FindBy(xpath = OR.RatePopup_ExtraAdult)
	public WebElement RatePopup_ExtraAdult;

	@FindBy(xpath = OR.RatePopup_ExtraChild)
	public WebElement RatePopup_ExtraChild;

	@FindBy(id = OR.rateGridAvailableButton)
	public WebElement rateGridAvailableButton;
	
	@FindBy(xpath = OR.rateGridBulkUpdateButton)
	public WebElement rateGridBulkUpdateButton;

	@FindBy(xpath = OR.rateGridBulkUpdateAvailable)
	public WebElement rateGridBulkUpdateAvailable;

	@FindBy(xpath = OR.availabilityHeading)
	public WebElement availabilityHeading;

	@FindBy(xpath = OR.bulkUpdatePopupText)
	public WebElement bulkUpdatePopupText;

	@FindBy(xpath = OR.startDate)
	public WebElement bulkUpdatePopupCheckinInput;

	@FindBy(xpath = OR.endDate)
	public WebElement bulkUpdatePopupCheckoutInput;

	@FindBy(xpath = OR.totalOccupancy)
	public WebElement totalOccupancy;

	@FindBy(xpath = OR.totalOccupancyType)
	public WebElement totalOccupancyType;

	@FindBy(xpath = OR.occupancyIcon)
	public WebElement occupancyIcon;
	
	@FindBy(xpath = OR.totalOccupanyValue)
	public WebElement totalOccupanyValue;

	@FindBy(xpath = OR.bulkUpdatePopupRoomClass)
	public WebElement bulkUpdatePopupRoomClass;

	@FindBy(xpath = OR.updateButton)
	public WebElement updateButton;

	@FindBy(xpath = OR.daysText)
	public WebElement daysText;
	
	@FindBy(xpath = OR.bulkUpdateCancel)
	public WebElement bulkUpdateCancel;
	
	@FindBy(xpath = OR.rulesHeading)
	public WebElement rulesHeading;
	
	@FindBy(xpath = OR.minimumStay)
	public WebElement minimumStay;
	
	@FindBy(xpath = OR.minimumStayValue)
	public WebElement minimumStayValue;
	
	@FindBy(xpath = OR.checkin)
	public WebElement checkin;
	
	@FindBy(xpath = OR.noCheckInInput)
	public WebElement noCheckInInput;
	
	@FindBy(xpath = OR.noCheckInCheckbox)
	public WebElement noCheckInCheckbox;
	
	@FindBy(xpath = OR.checkOut)
	public WebElement checkOut;
	
	@FindBy(xpath = OR.noCheckOutInput)
	public WebElement noCheckOutInput;
	
	@FindBy(xpath = OR.noCheckOutCheckbox)
	public WebElement noCheckOutCheckbox;
	
	@FindBy(xpath = OR.closeRulesPopup)
	public WebElement closeRulesPopup;

	@FindBy(xpath = OR.totalOccupancyText)
	public WebElement totalOccupancyText;
	
	@FindBy(xpath = OR.totalOccupancyTypeVisibility)
	public WebElement totalOccupancyTypeVisibility;
	
	@FindBy(xpath = OR.updatExistingRules)
	public WebElement updatExistingRules;
}
