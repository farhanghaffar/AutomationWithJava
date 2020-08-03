package com.innroad.inncenter.webelements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Setup;

public class WebElements_Create_Seasons {
	
	WebDriver driver;

	public WebElements_Create_Seasons(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id=OR.New_Seasons)
	public WebElement New_Seasons_link;
	
	@FindBy(xpath=OR.Select_Property_in_Seasons)
	public WebElement Selecting_Property_in_Season;
	
	@FindBy(xpath=OR.New_Season_Button)
	public WebElement New_Season_Button;
	
	@FindBy(id=OR.EG_Select_Property_in_Seasons)
	public WebElement EG_Select_Property_in_Seasons;

	@FindBy(id=OR.EG_New_Seasons_Button)
	public WebElement EG_New_Seasons_Button;

	@FindBy(xpath=OR.NG_Season_Name)
	public WebElement NG_Season_Name;
	
	@FindBy(xpath=OR.NG_Season_start_Date)
	public WebElement NG_Season_start_Date;
	
	@FindBy(xpath=OR.NG_Season_End_Date)
	public WebElement NG_Season_End_Date;
	
	@FindBy(xpath=OR.NG_Season_Save)
	public WebElement NG_Season_Save;
	
	@FindBy(xpath=OR.NG_Season_Reset)
	public WebElement NG_Season_Reset;
	
	
	@FindBy(xpath=OR.NG_Season_Search)
	public WebElement NG_Season_Search;
	
	@FindBy(xpath=OR.First_Element_In_Season_SearchResults)
	public WebElement First_Element_In_Season_SearchResults;
	
	@FindBy(xpath=OR.SeasonsPage_Reset_Btn)
	public WebElement SeasonsPage_Reset_Btn;
	
	@FindBy(id=OR.EG_Season_Name)
	public WebElement EG_Season_Name;
	
	@FindBy(xpath=OR.EG_start_Picker)
	public WebElement EG_start_Picker;
	
	@FindBy(xpath=OR.EG_Season_start_Date)
	public WebElement EG_Season_start_Date;
	
	@FindBy(xpath=OR.EG_End_picker)
	public WebElement EG_End_picker;
	
	@FindBy(id=OR.EG_Season_End_Date)
	public WebElement EG_Season_End_Date;
	
	@FindBy(id=OR.EG_Season_Save)
	public WebElement EG_Season_Save;
	
	@FindBy(xpath=OR.NG_New_Season_Btn)
	public WebElement NG_New_Season_Btn;
	
	@FindBy(xpath = OR.Season_Grid)
	public WebElement Season_Grid;

	
	@FindBy(xpath = OR.Season_Status)
	public WebElement Season_Status;
	
	@FindBy(xpath = OR.SeasonDate_Today)
	public WebElement SeasonDate_Today;
	
	@FindBy(xpath = OR.SeasonEndDate_Day)
	public WebElement SeasonEndDate_Day;
	
	@FindBy(xpath = OR.First_Date_RequiredField)
	public WebElement First_Date_RequiredField;
	
	@FindBy(xpath = OR.End_Date_RequiredField)
	public WebElement End_Date_RequiredField;
	
	@FindBy(xpath = OR.EffectiveDays_ValidationMsg)
	public WebElement EffectiveDays_ValidationMsg;
	
	@FindBy(xpath = OR.CheckboxMonday)
	public WebElement CheckboxMonday;
	
	@FindBy(xpath = OR.CheckboxTuesday)
	public WebElement CheckboxTuesday;
	
	@FindBy(xpath = OR.CheckboxWednesday)
	public WebElement CheckboxWednesday;
	
	@FindBy(xpath = OR.CheckboxThursday)
	public WebElement CheckboxThursday;
	
	
	@FindBy(xpath = OR.CheckboxFriday)
	public WebElement CheckboxFriday;
	
	@FindBy(xpath = OR.CheckboxSaturday)
	public WebElement CheckboxSaturday;
	
	@FindBy(xpath = OR.CheckboxSunday)
	public WebElement CheckboxSunday;
	
	@FindBy(xpath = OR.FillMendatoryField)
	public WebElement FillMendatoryField;
	
	@FindBy(xpath = OR.Close_Season)
	public WebElement Close_Season;
	
	@FindBy(xpath = OR.Unsaved_Data_PopUp)
	public WebElement Unsaved_Data_PopUp;
	
	@FindBy(xpath = OR.Unsaved_No_Button)
	public WebElement Unsaved_No_Button;
	
	@FindBy(xpath = OR.Unsaved_Yes_Button)
	public WebElement Unsaved_Yes_Button;

	@FindBy(xpath=OR.Seasons_SearchButton)
	public WebElement Seasons_SearchButton;
	
	@FindBy(xpath=OR.Seasons_TableShow)
	public WebElement Seasons_TableShow;
	
	@FindBy(xpath=OR.Seasons_FirstActiveClass)
	public WebElement Seasons_FirstActiveClass;
	
	@FindBy(xpath=OR.Seasons_SeasonDetailsPage)
	public WebElement Seasons_SeasonDetailsPage;
	
	@FindBy(xpath=OR.Seasons_FirstActiveClass_Date)
	public WebElement Seasons_FirstActiveClass_Date;
	
	@FindBy (xpath = OR.SelectItemsPerPage_Seasons)
	public WebElement SelectItemsPerPage_Seasons;
	
	@FindBy (xpath = OR.DeleteSeason)
	public WebElement DeleteSeason;
	
	@FindBy(className=OR.Toaster_Message)
	public WebElement Toaster_Message;
	
	@FindBy(xpath = OR.	First_Name_RequiredField)
	public WebElement 	First_Name_RequiredField;
	
	
	@FindBy(xpath=OR.ClickOnNext)
	public WebElement ClickOnNext;
	
	@FindBy(xpath=OR.NewSeason_Button)
	public WebElement NewSeason_Button;
	
	@FindBy(xpath = OR.SelectSeasonTodayDate)
	public WebElement SelectSeasonTodayDate;
	
	@FindBy(xpath=OR_Setup.EnterSeasonStartDate)
	public WebElement EnterSeasonStartDate;
	
	@FindBy(xpath=OR_Setup.EnterSeasonEndDate)
	public WebElement EnterSeasonEndDate;
	
	@FindBy(xpath=OR_Setup.SeasonPagination)
	public List<WebElement> SeasonPagination;
	
	@FindBy(xpath=OR_Setup.SearchSeason_Btn)
	public WebElement SearchSeason_Btn;
	
	@FindBy(xpath=OR_Setup.CloseSeasonTab)
	public WebElement CloseSeasonTab;
	
}

