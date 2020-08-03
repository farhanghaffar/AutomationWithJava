package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Reports;

public class Elements_Reports {
	

	WebDriver driver ;


	public static Logger app_logs = Logger.getLogger("devpinoyLogger");
		
		public Elements_Reports(WebDriver driver2)
		{
			this.driver=driver2;
			PageFactory.initElements(this.driver, this);
			
			 
		}

		@FindBy(xpath=OR.AccountsBalance)
		public WebElement AccountsBalance;
		
		@FindBy(xpath=OR.Select_LedgerType)
		public WebElement Select_LedgerType;
		
		@FindBy(xpath=OR.AccountStatus)
		public WebElement AccountStatus;
		
		@FindBy (xpath = OR.AccountBalance_EffectiveDate)
		public WebElement AccountBalance_EffectiveDate;
		
		@FindBy(xpath=OR.AccountBalanceGo_Button)
		public WebElement AccountBalanceGo_Button;
		
		@FindBy(xpath=OR.AccountBalance_Header)
		public WebElement AccountBalance_Header;
		
		@FindBy(xpath=OR.ReservationType_Current)
		public WebElement ReservationType_Current;
		
		@FindBy(xpath=OR.ReservationType_Past)
		public WebElement ReservationType_Past;
		
		@FindBy(xpath=OR.ReservationType_Future)
		public WebElement ReservationType_Future;
		
		@FindBy(xpath=OR.Reservation_Pending)
		public WebElement Reservation_Pending;
		
		@FindBy(xpath=OR.LedgerBalance_Tab)
		public WebElement LedgerBalance_Tab;
		
		@FindBy(xpath=OR.LedgerBalance_CheckIn)
		public WebElement LedgerBalance_CheckIn;
		
		@FindBy(xpath=OR.LedgerBalance_FromDate)
		public WebElement LedgerBalance_FromDate;
		
		@FindBy(xpath=OR.LedgerBalance_ToDate)
		public WebElement LedgerBalance_ToDate;
		
		@FindBy(xpath=OR.GoButton)
		public WebElement GoButton;
		
		@FindBy(xpath=OR.ActivDate)
		public WebElement ActivDate;
		
		@FindBy(xpath=OR.FirstOfTheMonth)
		public WebElement FirstOfTheMonth;
		
		@FindBy(xpath=OR.Incidentials_Checkbox)
		public WebElement Incidentials_Checkbox;
		
		@FindBy(xpath=OR.ShiftReport_RadioButton)
		public WebElement ShiftReport_RadioButton;
		
		@FindBy(xpath=OR.LagerBalacePage)
		public WebElement LagerBalacePage;
		
		@FindBy(xpath=OR.Detail_RadioButton)
		public WebElement Detail_RadioButton;
		
		@FindBy(xpath=OR.DepartmentReport_RadioButton)
		public WebElement DepartmentReport_RadioButton;
		
		@FindBy(xpath=OR.Summary_RadioButton)
		public WebElement Summary_RadioButton;
		
		@FindBy(xpath=OR.LedgerBalance_ItemStatus)
		public WebElement LedgerBalance_ItemStatus;
		
		@FindBy(xpath=OR.LedgerBalance_AccountType)
		public WebElement LedgerBalance_AccountType;
		
		@FindBy(xpath=OR.LedgerBalance_ReportType)
		public WebElement LedgerBalance_ReportType;
		
		@FindBy(xpath=OR.Select_PreMonth)
		public WebElement Select_PreMonth;
		
		@FindBy(css=OR.OldDate)
		public WebElement OldDate;
		
		@FindBy(css=OR.ActiveDate)
		public WebElement ActiveDate;
		
		@FindBy(xpath=OR.MerchantTrans_Tab)
		public WebElement MarchantTrans_Tab;
		
		@FindBy(xpath=OR.MerchantPage)
		public WebElement MarchantPage;
		
		@FindBy(xpath=OR.SelectMerchant_Date)
		public WebElement SelectMerchant_Date;
		
		@FindBy(xpath=OR.TranstionStatus)
		public WebElement TranstionStatus;
		
		@FindBy(xpath=OR.MerchantCapture_Checkbox)
		public WebElement MerchantCapture_Checkbox;
		@FindBy(xpath=OR.MerchantCancel_Checkbox)
		public WebElement MerchantCancel_Checkbox;
		
		@FindBy(xpath=OR.MerchantCredit_Checkbox)
		public WebElement MerchantCredit_Checkbox;
		
		@FindBy(xpath=OR.MerchantAuthorized_Checkbox)
		public WebElement MerchantAuthorized_Checkbox;
		
		@FindBy(xpath=OR.DailyFlash_Tab)
		public WebElement DailyFlash_Tab;
		
		@FindBy(xpath=OR.DailyFlashPage)
		public WebElement DailyFlashPage;
		
		@FindBy(xpath=OR.SelectDailyFlash_Date)
		public WebElement SelectDailyFlash_Date;
		
		@FindBy(xpath=OR.DailyFlash_SelectedDate)
		public WebElement DailyFlash_SelectedDate;
		
		@FindBy(xpath=OR.DailyFlash_RadioButton)
		public WebElement DailyFlash_RadioButton;
		
		@FindBy(xpath=OR.DailyFlachManagementTransfers_RadioButton)
		public WebElement DailyFlachManagementTransfers_RadioButton;
		
		@FindBy(xpath=OR.DailyFlashBreakoutTaxExemptRev_CheckBox)
		public WebElement DailyFlashBreakoutTaxExemptRev_CheckBox;
		
		@FindBy (xpath = OR.DailyFlash_ReportGenerated)
		public WebElement DailyFlash_GeneratedReport;
		
		@FindBy (xpath = OR.DailyFlash_ReportGenerated)
		public WebElement GeneratedReport;
		
		@FindBy (xpath = OR.NoDataAvailable_Message)
		public WebElement NoDataAvailable_Message;
		
		@FindBy(xpath=OR.RoomForecast_Tab)
		public WebElement RoomForecast_Tab;
		
		@FindBy(xpath=OR.RoomForecastPage)
		public WebElement RoomForecastPage;
		
		@FindBy(xpath=OR.SelectRoomForecast_FromDate)
		public WebElement SelectRoomForecast_FromDate;

		@FindBy(xpath=OR.SelectRoomForecast_ToDate)
		public WebElement SelectRoomForecast_ToDate;
		
		@FindBy(xpath=OR.RoomForecast_FromDate)
		public WebElement RoomForecast_FromDate;

		@FindBy(xpath=OR.RoomForecast_ToDate)
		public WebElement RoomForecast_ToDate;
		
		@FindBy (xpath = OR.RoomForecast_GeneratedReport)
		public WebElement RoomForecast_GeneratedReport;
		
		@FindBy(id=OR.NetSales_GroupBy)
		public WebElement NetSales_GroupBy;
		
		@FindBy(id=OR.NetSales_ClientType)
		public WebElement NetSales_ClientType;
		
		@FindBy(xpath=OR.NetSales_FromDate)
		public WebElement NetSales_FromDate;
		
		@FindBy(xpath=OR.NetSales_ToDate)
		public WebElement NetSales_ToDate;
		
		@FindBy (xpath = OR.Today_Day)
		public WebElement Today_Day;
		
		@FindBy(id=OR.NetSales_GoButton)
		public WebElement NetSales_GoButton;
		
		@FindBy(id=OR.NetSales_GenratedReport_GroupByRoom)
		public WebElement NetSales_GenratedReport_GroupByRoom;
		
		@FindBy (xpath = OR.Select_FromDate)
		public WebElement Select_FromDate;
		
		@FindBy (xpath = OR.Select_ToDate)
		public WebElement Select_ToDate;
		
		@FindBy(xpath=OR.ADSummary_RadioButton)
		public WebElement ADSummary_RadioButton;
		
		@FindBy(xpath=OR.ADDetail_RadioButton)
		public WebElement ADDetail_RadioButton;
		
		@FindBy(xpath=OR.ReportType_Inbound)
		public WebElement ReportType_Inbound;

		@FindBy(xpath=OR.ReportType_Outbound)
		public WebElement ReportType_Outbound;
		
		@FindBy(xpath=OR.ReportType_Net)
		public WebElement ReportType_Net;
		
		@FindBy(xpath=OR.GoButton)
		public WebElement AdvanceDeposite_GoButton;
				
		
		@FindBy(xpath=OR.Reports_DailyFlash)
		public WebElement Reports_DailyFlash;
		
		@FindBy(xpath=OR.Repoprts_DailyFlash_Calender)
		public WebElement Repoprts_DailyFlash_Calender;
		
		@FindBy(xpath=OR.Repoprts_DailyFlash_Calender_Right)
		public WebElement Repoprts_DailyFlash_Calender_Right;
		
		@FindBy(xpath=OR.Repoprts_DailyFlash_Go)
		public WebElement Repoprts_DailyFlash_Go;
		
		
		@FindBy(xpath=OR.NetSales_Stayon_FromDate)
		public WebElement NetSales_Stayon_FromDate;
		
		@FindBy(xpath=OR.NetSales_Stayon_ToDate)
		public WebElement NetSales_Stayon_ToDate;
		
		@FindBy(xpath=OR.LEDGERBALANCE_MARKETSEGMENT)
		public WebElement LEDGERBALANCE_MARKETSEGMENT;
		
		@FindBy(xpath=OR.ReportsDailyFlash)
		public WebElement ReportsDailyFlash;

		@FindBy(xpath=OR.DailyFlashRadioButton)
		public WebElement DailyFlashRadioButton;
		
		@FindBy(xpath=OR.DailyFlashBreakoutTaxExemptRevCheckBox)
		public WebElement DailyFlashBreakoutTaxExemptRevCheckBox;
		

		@FindBy(xpath=OR_Reports.ReportFrame)
		public WebElement ReportFrame;
		
		@FindBy(xpath=OR_Reports.ReportType)
		public WebElement ReportType;
		
		@FindBy(id=OR_Reports.ReportDisplay)
		public WebElement ReportDisplay;

		@FindBy(xpath=OR_Reports.ViewPDF)
		public WebElement ViewPDF;
		
		@FindBy(xpath=OR.NetSalesFromDate)
		public WebElement NetSalesFromDate;
		
		@FindBy(xpath=OR.NetSalesToDate)
		public WebElement NetSalesToDate;
		
		@FindBy(xpath = OR_Reports.NetSalesFromCalenderHeading)
		public WebElement NetSalesFromCalenderHeading;
		
		@FindBy(xpath= OR_Reports.NetSalesStayonFromDate)
		public WebElement NetSalesStayonFromDate;
		
		@FindBy(xpath = OR_Reports.NetSalesFromCalenderNext)
		public WebElement NetSalesFromCalenderNext;
		
		@FindBy(xpath=OR_Reports.NetSalesStayonToDate)
		public WebElement NetSalesStayonToDate;
		
		@FindBy(id=OR.NetSalesGroupBy)
		public WebElement NetSalesGroupBy;
		

		
		
}
