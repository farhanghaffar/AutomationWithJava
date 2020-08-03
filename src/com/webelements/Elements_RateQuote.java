package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class Elements_RateQuote {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_RateQuote(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);

	}

	@FindBy(xpath = OR.Click_Arrive_DatePicker)
	public WebElement Click_Arrive_DatePicker;

	@FindBy(xpath = OR.Click_Today)
	public WebElement Click_Today;
	
	@FindBy(xpath = OR.Check_RateQuoteAssignRooms)
	public WebElement Check_RateQuoteAssignRooms;

	@FindBy(xpath = OR.Enter_RateQuoteNights)
	public WebElement Enter_RateQuoteNights;

	@FindBy(xpath = OR.Enter_RateQuoteAdults)
	public WebElement Enter_RateQuoteAdults;

	@FindBy(xpath = OR.Enter_RateQuoteChildren)
	public WebElement Enter_RateQuoteChildren;

	@FindBy(xpath = OR.Select_RateQuoteRatePlanList)
	public WebElement Select_RateQuoteRatePlanList;

	@FindBy(xpath = OR.Enter_RateQuotePromoCode)
	public WebElement Enter_RateQuotePromoCode;

	@FindBy(xpath = OR.Click_searchRateQuote)
	public WebElement Click_searchRateQuote;

	@FindBy(xpath = OR.Click_clearRateQuote)
	public WebElement Click_clearRateQuote;

	@FindBy(xpath = OR.Verify_tblRateQuoteGrid)
	public WebElement Verify_tblRateQuoteGrid;

	@FindBy(xpath = OR.Verify_Number_rate_book_green)
	public WebElement Verify_Number_rate_book_green;

	@FindBy(xpath = OR.Verify_Number_rate_book_red)
	public WebElement Verify_Number_rate_book_red;

	@FindBy(xpath = OR.Get_Property_Id)
	public WebElement Get_Property_Id;

	@FindBy(xpath = OR.Click_First_Book_Icon)
	public WebElement Click_First_Book_Icon;

	@FindBy(xpath = OR.Verify_RulesBroken_Popup)
	public WebElement Verify_RulesBroken_Popup;

	@FindBy(xpath = OR.Click_Rate_Quote_Book)
	public WebElement Click_Rate_Quote_Book;

	@FindBy(xpath = OR.Verify_OverBook_popup)
	public WebElement Verify_OverBook_popup;

	@FindBy(xpath = OR.Click_Continue_OverBook_Popup)
	public WebElement Click_Continue_OverBook_Popup;

	@FindBy(xpath = OR.Click_First_Quote_Icon)
	public WebElement Click_First_Quote_Icon;

	@FindBy(xpath = OR.Click_RuleBroken_Quote)
	public WebElement Click_RuleBroken_Quote;

	@FindBy(id = OR.TotalRate_DBRValue)
	public WebElement TotalRate_DBRValue;
	
	@FindBy(xpath = OR.Rules_Broken_popupQuote)
	public WebElement Rules_Broken_popupQuote;
	
	@FindBy(xpath = OR.RuleName_NewQuote)
	public WebElement RuleName_NewQuote;	
	
	@FindBy(xpath = OR.Rules_Broken_popupQuoteOK)
	public WebElement Rules_Broken_popupQuoteOK;	
	
	@FindBy(xpath = OR.Click_RatePlan)
	public WebElement Click_RatePlan;
	
	@FindBy(xpath = OR.Click_Depart_DatePicker)
	public WebElement Click_Depart_DatePicker;

}
