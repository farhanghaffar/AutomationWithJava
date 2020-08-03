package com.innroad.inncenter.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class WebElements_Reservation_Select_Dates_BE {
	
WebDriver driver;
	
	public WebElements_Reservation_Select_Dates_BE(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath=OR.BE_Page_Title)
	public WebElement BE_Page_Title;
	
	@FindBy(xpath=OR.BE_My_Dates_are_flexible)
	public WebElement BE_My_Dates_are_flexible;
	
	@FindBy(xpath=OR.GetCheckintext)
	public WebElement GetCheckintext;
	
	@FindBy(xpath=OR.GetCheckouttext)
	public WebElement GetCheckouttext;
	
	@FindBy(id=OR.Get_NumberofAdults)
	public WebElement Get_NumberofAdults;
	
	@FindBy(id=OR.Get_NumberofAdults)
	public WebElement Get_Numberofchilds;
	
	@FindBy(id=OR.AvailabilitySearch1_btnAvailabilitySearch)
	public WebElement AvailabilitySearch1_btnAvailabilitySearch;
	
	@FindBy(id=OR.AvailabilitySearch1_lbkChkInDate)
	public WebElement AvailabilitySearch1_lbkChkInDate;
	
	@FindBy(id=OR.AvailabilitySearch1_lblChkInDayOfTheWeek)
	public WebElement AvailabilitySearch1_lblChkInDayOfTheWeek;
	
	@FindBy(id=OR.AvailabilitySearch1_lblChkInMonthAndYear)
	public WebElement AvailabilitySearch1_lblChkInMonthAndYear;
	
	@FindBy(xpath=OR.CheckInText)
	public WebElement CheckInText;
	
	@FindBy(id=OR.AvailabilitySearch1_lbkChkOutDate)
	public WebElement AvailabilitySearch1_lbkChkOutDate;
	
	@FindBy(id=OR.AvailabilitySearch1_lblChkOutDayOfTheWeek)
	public WebElement AvailabilitySearch1_lblChkOutDayOfTheWeek;
	
	@FindBy(id=OR.AvailabilitySearch1_lblChkOutMonthAndYear)
	public WebElement AvailabilitySearch1_lblChkOutMonthAndYear;
	
	@FindBy(xpath=OR.CheckOutText)
	public WebElement CheckOutText;
	
	@FindBy(xpath=OR.AvailabilitySearch1_lblPropertyName)
	public WebElement AvailabilitySearch1_lblPropertyName;
	
	@FindBy(xpath=OR.AvailabilitySearch1_divGuests)
	public WebElement AvailabilitySearch1_divGuests;
	
	@FindBy(xpath=OR.GetRoomClassName_Availableroompage)
	public WebElement GetRoomClassName_Availableroompage;
	
	@FindBy(xpath=OR.GetRoomClassName_Availableroompage2)
	public WebElement GetRoomClassName_Availableroompage2;
	
	@FindBy(xpath=OR.GetRoomClassName_Availableroompage3)
	public WebElement GetRoomClassName_Availableroompage3;
	
	@FindBy(xpath=OR.Click_Select_Button)
	public WebElement Click_Select_Button;
	
	@FindBy(xpath=OR.Increment_Adult_Count)
	public WebElement Increment_Adult_Count;
	
	@FindBy(xpath=OR.Increment_Child_Count)
	public WebElement Increment_Child_Count;
	
	@FindBy(xpath=OR.Check_in_Text)
	public WebElement Check_in_Text;
	
	@FindBy(xpath=OR.Check_out_Text)
	public WebElement Check_out_Text;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
