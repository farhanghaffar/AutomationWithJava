package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class Elements_Roles {

	WebDriver driver;
	public static Logger app_logs = Logger.getLogger("devpinoyLogger");
	public Elements_Roles(WebDriver driver2)
	{
		this.driver=driver2;
		PageFactory.initElements(this.driver, this);
		
		 
	}
	
	@FindBy(xpath=OR.Roles_Search_Btn)
	public WebElement Roles_Search_Btn;
	
	@FindBy(xpath=OR.First_Element_In_Roles_SearchResults)
	public WebElement First_Element_In_Roles_SearchResults;
	
	@FindBy(xpath=OR.Enter_Role_Name)
	public WebElement Enter_Role_Name;
	
	@FindBy(xpath=OR.Roles_Reset_Btn)
	public WebElement Roles_Reset_Btn;
	
	@FindBy(xpath=OR.New_Role_Btn)
	public WebElement New_Role_Btn;
	
	@FindBy(xpath=OR.Select_Role_Satus)
	public WebElement Select_Role_Satus;
	
}
