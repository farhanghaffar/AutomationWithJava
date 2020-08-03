package com.innroad.inncenter.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class Elements_Rules {
	WebDriver driver;

	public Elements_Rules(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath=OR.Rules_Search_Btn)
	public WebElement Rules_Search_Btn;
	
	@FindBy(xpath=OR.First_Element_In_Rules_SearchResults)
	public WebElement First_Element_In_Rules_SearchResults;
	
	@FindBy(xpath=OR.RulesPage_Reset_Btn)
	public WebElement RulesPage_Reset_Btn;
	
	@FindBy(xpath=OR.Select_Rule_Satus)
	public WebElement Select_Rule_Satus;
	
	@FindBy(xpath=OR.New_Rule_Btn)
	public WebElement New_Rule_Btn;
	
}
