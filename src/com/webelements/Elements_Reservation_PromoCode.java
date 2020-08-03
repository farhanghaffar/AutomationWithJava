package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.innroad.inncenter.properties.OR;

public class Elements_Reservation_PromoCode {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_Reservation_PromoCode(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);

	}

	@FindBy(xpath = OR.Tape_Chart)
	public WebElement Tape_Chart;

	@FindBy(xpath = OR.Select_Arrival_Date)
	public WebElement Select_Arrival_Date;

	@FindBy(xpath = OR.Click_Today)
	public WebElement Click_Today;

	@FindBy(xpath = OR.Enter_Adults_Tapehchart)
	public WebElement Enter_Adults_Tapehchart;

	@FindBy(xpath = OR.Enter_Children_Tapechart)
	public WebElement Enter_Children_Tapechart;

	@FindBy(xpath = OR.Enter_promoCode_Tapechart)
	public WebElement Enter_promoCode_Tapechart;

	@FindBy(xpath = OR.Click_Search_TapeChart)
	public WebElement Click_Search_TapeChart;

	@FindBy(xpath = OR.clickAvailableRoom)
	public WebElement clickAvailableRoom;

	@FindBy(xpath = OR.Rules_Broken_popup)
	public WebElement Rules_Broken_popup;

	@FindBy(xpath = OR.Click_Book_icon_Tapechart)
	public WebElement Click_Book_icon_Tapechart;

	@FindBy(xpath = OR.get_Reservation_Status)
	public WebElement get_Reservation_Status;

	@FindBy(xpath = OR.clickCancelButton)
	public WebElement clickCancelButton;

	@FindBy(xpath = OR.closeReservation)
	public WebElement closeReservation;

	@FindBy(xpath = OR.cancelReason)
	public WebElement cancelReason;

	@FindBy(xpath = OR.OkCancelReason)
	public WebElement OkCancelReason;
	
	@FindBy(xpath = OR.Reservation_PromoCode)
	public WebElement Reservation_PromoCode;

}
