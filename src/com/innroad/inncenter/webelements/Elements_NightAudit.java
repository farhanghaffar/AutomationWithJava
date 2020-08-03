package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class Elements_NightAudit {
	

	WebDriver driver ;


	public static Logger app_logs = Logger.getLogger("devpinoyLogger");
		
		public Elements_NightAudit(WebDriver driver2)
		{
			this.driver=driver2;
			PageFactory.initElements(this.driver, this);
			
			 
		}

		@FindBy(id=OR.Go_ButtonClick)
		public WebElement Go_ButtonClick;
		
		
		@FindBy(xpath=OR.AuditDatePicker)
		public WebElement AuditDatePicker;
		
		@FindBy(id=OR.AuditDate)
		public WebElement AuditDate;
		
		
		@FindBy(xpath=OR.ClickToday)
		public WebElement ClickToday;
		
		@FindBy(id=OR.SetNowButton)
		public WebElement SetNowButton;
		
		@FindBy(xpath=OR.UpdateRoomCondition_Popup)
		public WebElement UpdateRoomCondition_Popup;
		
		@FindBy(xpath=OR.UpdateRoomCondition_Yes)
		public WebElement UpdateRoomCondition_Yes;
		
		@FindBy ( xpath = OR.HouseKeepingCount)
		public WebElement HouseKeepingCount;
		
		@FindBy(id=OR.HouseKeepingText)
		public WebElement HouseKeepingText;
		
		@FindBy(id=OR.DailyTransactionButton)
		public WebElement DailyTransactionButton;
		
		@FindBy(id=OR.DailyTransactionPage)
		public WebElement DailyTransactionPage;
		
		@FindBy(id=OR.AllItemStatus)
		public WebElement AllItemStatus;
		
		@FindBy(id=OR.DailyTransactionPage_GoBtn)
		public WebElement DailyTransactionPage_GoBtn;
		
		@FindBy(id=OR.DailyTransactionPage_PostBtn)
		public WebElement DailyTransactionPage_PostBtn;
		
		@FindBy(id=OR.DialyTrans_CheckBoxAll)
		public WebElement DialyTrans_CheckBoxAll;
		
		@FindBy(id=OR.ItemToPost)
		public WebElement ItemToPost;
		
		@FindBy(id=OR.PeriodIsOpenButton)
		public WebElement PeriodIsOpenButton;
		
		@FindBy(id=OR.PeriodIsOpen_LockButton)
		public WebElement PeriodIsOpen_LockButton;
		
		@FindBy(id=OR.PeriodIsOpen_CancelButton)
		public WebElement PeriodIsOpen_CancelButton;
		
		@FindBy(xpath=OR.PeriodStatusPage)
		public WebElement PeriodStatusPage;
		
		@FindBy(id=OR.VerifyLongStay)
		public WebElement VerifyLongStay;
		
		@FindBy(id=OR.LongStay_SetNowBtn)
		public WebElement LongStay_SetNowBtn;
		
		@FindBy(xpath=OR.LongStay_Popup)
		public WebElement LongStay_Popup;
		
		@FindBy(xpath=OR.LongStay_YesButton)
		public WebElement LongStay_YesButton;
		
		@FindBy(xpath=OR.LongStayMessage)
		public WebElement LongStayMessage;
		
		@FindBy(xpath=OR.LongStay_NoButton)
		public WebElement LongStay_NoButtons;
		
		
		
		
		
		
	
}