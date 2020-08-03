package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.pages.NewProperty;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Inventory;
import com.innroad.inncenter.properties.OR_Reservation;

public class Elements_On_All_Navigation {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_On_All_Navigation(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);

	}

	@FindBy(xpath = OR.Tape_Chart)
	public WebElement Tape_Chart;

	@FindBy(xpath = OR.Tape_Chart_1)
	public WebElement Tape_Chart_1;

	@FindBy(xpath = OR.TapeChart)
	public WebElement TapeChart;

	@FindBy(xpath = OR.Tape_Chart_Grid1)
	public WebElement Tape_Chart_Grid;

	@FindBy(xpath = OR.New_Quote)
	public WebElement New_Quote;

	@FindBy(xpath = OR.New_QuoteIcon)
	public WebElement New_QuoteIcon;

	@FindBy(xpath = OR.New_Quote_Search)
	public WebElement New_Quote_Search;

	@FindBy(xpath = OR.Guest_History)
	public WebElement Guest_History;

	@FindBy(xpath = OR.Guest_HistoryIcon)
	public WebElement Guest_HistoryIcon;

	@FindBy(xpath = OR.Guest_History_Grid)
	public WebElement Guest_History_Grid;

	@FindBy(xpath = OR.Groups)
	public WebElement Groups;

	@FindBy(xpath = OR.Groups1)
	public WebElement Groups1;

	@FindBy(xpath = OR.Groups_text)
	public WebElement Groups_text;

	@FindBy(id = OR.Accounts)
	public WebElement Accounts;

	@FindBy(id = OR.Accounts_NavIcon)
	public WebElement Accounts_NavIcon;

	@FindBy(xpath = OR.Accounts2)
	public WebElement Accounts2;

	@FindBy(xpath = OR.Accounts_sec_Nav)
	public WebElement Accounts_sec_Nav;

	@FindBy(xpath = OR.Statements)
	public WebElement Statements;

	@FindBy(xpath = OR.Statements_SecNav)
	public WebElement Statements_SecNav;

	@FindBy(xpath = OR.Statement_Grid)
	public WebElement Statement_Grid;

	@FindBy(xpath = OR.Unit_owner)
	public WebElement Unit_owner;

	@FindBy(xpath = OR.UnitOwner)
	public WebElement UnitOwner;

	@FindBy(xpath = OR.Unit_owner_Account)
	public WebElement Unit_owner_Account;

	@FindBy(xpath = OR.Travel_Agent)
	public WebElement Travel_Agent;

	@FindBy(xpath = OR.Travel_Agent_Grid)
	public WebElement Travel_Agent_Grid;

	@FindBy(xpath = OR.Manafement_Transfers)
	public WebElement Manafement_Transfers;

	@FindBy(xpath = OR.Manafement_Transfers_Grid)
	public WebElement Manafement_Transfers_Grid;

	@FindBy(xpath = OR.Account_Distribution)
	public WebElement Account_Distribution;

	@FindBy(xpath = OR.Account_Distribution_grid)
	public WebElement Account_Distribution_grid;

	@FindBy(xpath = OR.Guest_Services)
	public WebElement Guest_Services;

	@FindBy(xpath = OR.Guest_Services_1)
	public WebElement Guest_Services_1;

	@FindBy(xpath = OR.Guest_Services_2)
	public WebElement Guest_Services_2;

	@FindBy(xpath = OR.Guest_Services_3)
	public WebElement Guest_Services_3;

	@FindBy(xpath = OR.GuestServicesPage)
	public WebElement GuestServicesPage;

	@FindBy(xpath = OR.Guest_Services_grid)
	public WebElement Guest_Services_grid;

	@FindBy(xpath = OR.House_Keeping)
	public WebElement House_Keeping;

	@FindBy(id = OR.House_Kepping_status)
	public WebElement House_Kepping_status;

	@FindBy(xpath = OR.Task_List)
	public WebElement Task_List;

	@FindBy(xpath = OR.TaskList)
	public WebElement TaskList;

	@FindBy(xpath = OR.TaskListIcon)
	public WebElement TaskListIcon;

	@FindBy(xpath = OR.Task_List_grid)
	public WebElement Task_List_grid;

	@FindBy(xpath = OR.RoomStatus)
	public WebElement RoomStatus;

	@FindBy(xpath = OR.RoomStatusPage)
	public WebElement RoomStatusPage;

	@FindBy(xpath = OR.Room_Maintanence)
	public WebElement Room_Maintanence;

	@FindBy(xpath = OR.RoomMaintanence)
	public WebElement RoomMaintanence;

	@FindBy(xpath = OR.Room_Maintanence_Grid)
	public WebElement Room_Maintanence_Grid;

	@FindBy(id = OR.Inventory_Grid)
	public WebElement Inventory_Grid;

	@FindBy(xpath = OR.Inventory)
	public WebElement Inventory;

	@FindBy(xpath = OR.Inventory2)
	public WebElement Inventory2;

	@FindBy(xpath = OR.Inventory_Backward)
	public WebElement Inventory_Backward;

	@FindBy(xpath = OR.Inventory_Backward_1)
	public WebElement Inventory_Backward_1;

	@FindBy(xpath = OR.Inventory_Backward_2)
	public WebElement Inventory_Backward_2;

	@FindBy(xpath = OR.Overview)
	public WebElement Overview;

	@FindBy(xpath = OR.Overview1)
	public WebElement Overview1;

	@FindBy(xpath = OR.RatesGrid)
	public WebElement RatesGrid;

	@FindBy(xpath = OR.RatesGrid1)
	public WebElement RatesGrid1;

	@FindBy(xpath = OR.Overview_grid)
	public WebElement Overview_grid;

	@FindBy(xpath = OR.Seasons)
	public WebElement Seasons;

	@FindBy(xpath = OR.Seasons1)
	public WebElement Seasons1;

	@FindBy(xpath = OR.Seasons_button)
	public WebElement Seasons_button;

	@FindBy(xpath = OR.Rates)
	public WebElement Rates;

	@FindBy(xpath = OR.rates2)
	public WebElement rates2;

	@FindBy(xpath = OR.Rates1)
	public WebElement Rates1;

	@FindBy(xpath = OR.Rates2)
	public WebElement Rates2;

	@FindBy(xpath = OR.Rates_Grid)
	public WebElement Rates_Grid;

	@FindBy(xpath = OR.Rules)
	public WebElement Rules;

	@FindBy(xpath = OR.Rules1)
	public WebElement Rules1;

	@FindBy(xpath = OR.Rules_Grid)
	public WebElement Rules_Grid;

	@FindBy(xpath = OR.Distribution)
	public WebElement Distribution;

	@FindBy(xpath = OR.Distribution1)
	public WebElement Distribution1;

	@FindBy(xpath = OR.Distribution_Gird)
	public WebElement Distribution_Gird;

	@FindBy(xpath = OR.Distribution_syndication)
	public WebElement Distribution_syndication;

	@FindBy(xpath = OR.Distribution_Blackouts)
	public WebElement Distribution_Blackouts;

	@FindBy(xpath = OR.policies)
	public WebElement policies;

	@FindBy(xpath = OR.policies1)
	public WebElement policies1;

	@FindBy(xpath = OR.Policy_Button)
	public WebElement Policy_Button;

	@FindBy(xpath = OR.GS_Setup)
	public WebElement GS_Setup;

	@FindBy(xpath = OR.Setup)
	public WebElement Setup;

	@FindBy(xpath = OR.SetupIcon)
	public WebElement SetupIcon;

	@FindBy(xpath = OR.Setup_Grid)
	public WebElement Setup_Grid;

	@FindBy(xpath = OR.Propeties)
	public WebElement Propeties;

	@FindBy(xpath = OR.Propety_Grid)
	public WebElement Propety_Grid;

	@FindBy(xpath = OR.Roomclass)
	public WebElement Roomclass;

	@FindBy(xpath = OR.Roomclass1)
	public WebElement Roomclass1;

	@FindBy(xpath = OR.Roomclass_grid)
	public WebElement Roomclass_grid;

	@FindBy(xpath = OR.Taxes)
	public WebElement Taxes;

	@FindBy(xpath = OR.Taxes1)
	public WebElement Taxes1;

	@FindBy(xpath = OR.Taxes_Grid)
	public WebElement Taxes_Grid;

	@FindBy(xpath = OR.Ledger_Accounts)
	public WebElement Ledger_Accounts;

	@FindBy(xpath = OR.Ledger_Accounts1)
	public WebElement Ledger_Accounts1;

	@FindBy(xpath = OR.Ledger_Account_Grid)
	public WebElement Ledger_Account_Grid;

	@FindBy(xpath = OR.Merchant_Services)
	public WebElement Merchant_Services;

	@FindBy(xpath = OR.Merchant_Services1)
	public WebElement Merchant_Services1;

	@FindBy(xpath = OR.Merchant_Services_Grid)
	public WebElement Merchant_Services_Grid;

	@FindBy(xpath = OR.Document_Template)
	public WebElement Document_Template;

	@FindBy(xpath = OR.Document_Template1)
	public WebElement Document_Template1;

	@FindBy(xpath = OR.Documnet_Template_Grid)
	public WebElement Documnet_Template_Grid;

	@FindBy(xpath = OR.List_Management)
	public WebElement List_Management;

	@FindBy(xpath = OR.List_Management_Grid)
	public WebElement List_Management_Grid;

	@FindBy(xpath = OR.Task_Management)
	public WebElement Task_Management;

	@FindBy(xpath = OR.Admin)
	public WebElement Admin;

	@FindBy(xpath = OR.AdminIcon)
	public WebElement AdminIcon;

	@FindBy(xpath = OR.Client_info)
	public WebElement Client_info;

	@FindBy(xpath = OR.Client_info1)
	public WebElement Client_info1;

	@FindBy(xpath = OR.Client_info_grid)
	public WebElement Client_info_grid;

	@FindBy(xpath = OR.Users)
	public WebElement Users;

	@FindBy(xpath = OR.Users1)
	public WebElement Users1;

	@FindBy(xpath = OR.Users_grid)
	public WebElement Users_grid;

	@FindBy(xpath = OR.Roles)
	public WebElement Roles;

	@FindBy(xpath = OR.Roles1)
	public WebElement Roles1;

	@FindBy(xpath = OR.Roles_page)
	public WebElement Roles_page;

	@FindBy(xpath = OR.Night_audit)
	public WebElement Night_audit;

	@FindBy(xpath = OR.NightAudit)
	public WebElement NightAudit;

	@FindBy(xpath = OR.NightAuditIcon)
	public WebElement NightAuditIcon;

	@FindBy(xpath = OR.Period_status)
	public WebElement Period_status;

	@FindBy(xpath = OR.Reports)
	public WebElement Reports;

	@FindBy(xpath = OR.ReportsIcon)
	public WebElement ReportsIcon;

	@FindBy(xpath = OR.Reports_Backward)
	public WebElement Reports_Backward;

	@FindBy(xpath = OR.Reports_Grid)
	public WebElement Reports_Grid;

	@FindBy(xpath = OR.Account_Balance)
	public WebElement Account_Balance;

	@FindBy(xpath = OR.Account_Balance_Grid)
	public WebElement Account_Balance_Grid;

	@FindBy(xpath = OR.Ledger_Balances)
	public WebElement Ledger_Balances;

	@FindBy(xpath = OR.Ledger_Balances_Grid)
	public WebElement Ledger_Balances_Grid;

	@FindBy(xpath = OR.Merchant_Trans)
	public WebElement Merchant_Trans;

	@FindBy(xpath = OR.Merchant_Trans_grid)
	public WebElement Merchant_Trans_grid;

	@FindBy(xpath = OR.Daily_flash)
	public WebElement Daily_flash;

	@FindBy(xpath = OR.Daily_flash_grid)
	public WebElement Daily_flash_grid;

	@FindBy(xpath = OR.Room_Forecast)
	public WebElement Room_Forecast;

	@FindBy(xpath = OR.Net_Sales)
	public WebElement Net_Sales;

	@FindBy(xpath = OR.Net_Sales_1)
	public WebElement Net_Sales_1;

	@FindBy(xpath = OR.Advance_Depos)
	public WebElement Advance_Depos;

	@FindBy(xpath = OR.Click_Reservation)
	public WebElement Click_Reservation;

	@FindBy(xpath = OR.Reservation_Backward)
	public WebElement Reservation_Backward;

	@FindBy(xpath = OR.Reservation_Backward_1)
	public WebElement Reservation_Backward_1;

	@FindBy(xpath = OR.Reservation_Backward_2)
	public WebElement Reservation_Backward_2;

	@FindBy(xpath = OR.Reservation_Backward_3)
	public WebElement Reservation_Backward_3;

	@FindBy(xpath = OR.Reservation_Backward_4)
	public WebElement Reservation_Backward_4;

	@FindBy(xpath = OR.Guest_Info)
	public WebElement Guest_Info;

	@FindBy(xpath = OR.Guest_Services_Main_Menu)
	public WebElement Guest_Services_Main_Menu;

	@FindBy(xpath = OR.Menuitem_Policy_Text)
	public WebElement Menuitem_Policy_Text;

	@FindBy(xpath = OR.New_RoomClass_Btn)
	public WebElement New_RoomClass;

	@FindBy(xpath = OR.NewRoomClass)
	public WebElement NewRoomClass;

	@FindBy(xpath = OR.New_RoomClass_Btn1)
	public WebElement New_RoomClass1;

	@FindBy(id = OR.NewProperty_Button)
	public WebElement NewProperty_Button;

	@FindBy(xpath = OR.Property_Options)
	public WebElement Property_Options;

	@FindBy(xpath = OR.PropertyName_List)
	public WebElement PropertyName_List;

	@FindBy(xpath = OR.ChkLongerthan)
	public WebElement ChkLongerthan;

	@FindBy(id = OR.Enter_txttaxexempt)
	public WebElement Enter_txttaxexempt;

	@FindBy(id = OR.Properties_Save)
	public WebElement Properties_Save;

	@FindBy(id = NewProperty.Select_Status)
	public WebElement Select_Status;

	@FindBy(id = NewProperty.GoButton)
	public WebElement GoButton;

	@FindBy(id = OR.Property_Publish_Button)
	public WebElement Property_Publish_Button;

	@FindBy(id = OR.Enter_PropertyName)
	public WebElement Enter_PropertyName;

	@FindBy(id = OR.Enter_LegalyName)
	public WebElement Enter_LegalyName;

	@FindBy(id = OR.Select_Town)
	public WebElement Select_Town;

	@FindBy(id = OR.Enter_Description)
	public WebElement Enter_Description;

	@FindBy(id = OR.Enter_Contact)
	public WebElement Enter_Contact;

	@FindBy(id = OR.Enter_Email)
	public WebElement Enter_Email;

	@FindBy(id = OR.Enter_Phone)
	public WebElement Enter_Phone;

	@FindBy(id = OR.Enter_Property_Address)
	public WebElement Enter_Property_Address;

	@FindBy(id = OR.Enter_Property_City)
	public WebElement Enter_Property_City;

	@FindBy(id = OR.Select_Property_State)
	public WebElement Select_Property_State;

	@FindBy(id = OR.Enter_Property_PostalCode)
	public WebElement Enter_Property_PostalCode;

	@FindBy(id = OR.Use_MailingAddress_Checkbox)
	public WebElement Use_MailingAddress_Checkbox;

	@FindBy(id = OR.Property_Delete_Button)
	public WebElement Property_Delete_Button;

	@FindBy(id = OR.EmailCheckbox)
	public WebElement EmailCheckbox;

	@FindBy(id = OR.AllowNonZeroBalanceCheckbox)
	public WebElement AllowNonZeroBalanceCheckbox;

	@FindBy(xpath = OR.LogOutButton)
	public WebElement LogOutButton;

	@FindBy(xpath = OR.LogOut)
	public WebElement LogOut;

	@FindBy(xpath = OR.propertyName)
	public WebElement propertyName;

	@FindBy(id = OR.EmailDisplayNameField)
	public WebElement EmailDisplayNameField;

	@FindBy(xpath = OR.RoomClass_Checkbox)
	public List<WebElement> RoomClass_Checkbox;

	@FindBy(xpath = OR.DeleteRoomClass_Button)
	public WebElement DeleteRoomClass_Button;

	@FindBy(xpath = OR.SelectAllRecord)
	public WebElement SelectAllRecord;

	@FindBy(xpath = OR.RoomClass_SearchButton)
	public WebElement RoomClass_SearchButton;

	@FindBy(xpath = OR.Toaster_Message)
	public WebElement Toaster_Message;

	@FindBy(className = OR.QAProperty)
	public WebElement QAProperty;

	@FindBy(xpath = OR.SendCC_CustomEmailAddress_CheckBox)
	public WebElement SendCC_CustomEmailAddress_CheckBox;

	@FindBy(xpath = OR.SendCC_CustomEmailAddress_Email)
	public WebElement SendCC_CustomEmailAddress_Email;

	@FindBy(xpath = OR.DeleteRoomClassesButton)
	public List<WebElement> DeleteRoomClassesButton;

	@FindBy(xpath = OR.RC_OK_Button)
	public List<WebElement> RC_OK_Button;

	@FindBy(xpath = OR.RoomClassesToaster)
	public WebElement RoomClassesToaster;

	@FindBy(xpath = OR.Rategrid_Inventory)
	public WebElement Rategrid_Inventory;

	@FindBy(xpath = OR.secNav_Reservation)
	public WebElement secNav_Reservation;

	@FindBy(xpath = OR.Groups_Backward)
	public WebElement Groups_Backward;

	@FindBy(xpath = OR.Groups_Backward_Reservation)
	public WebElement Groups_Backward_Reservation;

	@FindBy(xpath = OR.LoginModuleLoding)
	public WebElement LoginModuleLoding;

	@FindBy(xpath = OR.Reservation_Menu)
	public WebElement Click_Reservation_Menu;

	@FindBy(xpath = OR.Rates_Grid_Tab)
	public WebElement Rates_GridTab;

	@FindBy(xpath = OR_Inventory.AvailabilityTab)
	public WebElement AvailabilityTab;

	@FindBy(xpath = OR_Inventory.RatsTab)
	public WebElement RatsTab;

	@FindBy(id = OR.secondaryRatesMenuItem)
	public WebElement secondaryRatesMenuItem;

	@FindBy(xpath = OR.Reservation_Menu_FromTax)
	public WebElement Click_ReservationMenu_fromTax;

	@FindBy(xpath = OR.CPReservationBackward)
	public WebElement CPReservationBackward;

	@FindBy(xpath = OR.DailyFlashReportDate)
	public WebElement DailyFlashReportDate;

	@FindBy(xpath = OR.DailyFlashReportTodayDate)
	public WebElement DailyFlashReportTodayDate;

	@FindBy(xpath = OR.DailyFlashReportGoButton)
	public WebElement DailyFlashReportGoButton;

	@FindBy(xpath = OR.SetUP)
	public WebElement setUp;

	@FindBy(xpath = OR.RoomStatusTab)
	public WebElement RoomStatusTab;

	@FindBy(xpath = OR.MoveToTapeChart)
	public WebElement MoveToTapeChart;

	@FindBy(xpath = OR.Roomclass2)
	public WebElement Roomclass2;

	@FindBy(xpath = OR.PropertyList)
	public List<WebElement> PropertyList;

	@FindBy(xpath = OR.GetDefultProperty)
	public WebElement GetDefultProperty;

	@FindBy(xpath = OR.Reservation_Backward_Admin)
	public WebElement Reservation_Backward_Admin;

	@FindBy(xpath = OR.clientDetailsHeader)
	public WebElement clientDetailsHeader;

	@FindBy(xpath = OR.clientDetailsOptionsTab)
	public WebElement clientDetailsOptionsTab;

	@FindBy(xpath = OR.CP_Reservation_Backward)
	public WebElement CP_Reservation_Backward;

}
