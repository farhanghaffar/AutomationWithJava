package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class Elements_Syndication {

	WebDriver driver;
	public static Logger app_logs =Logger.getLogger("devpinoyLogger");
	
	public Elements_Syndication(WebDriver driver2)
	{
		this.driver=driver2;
		PageFactory.initElements(this.driver, this);
	
		
	}
	
	
	@FindBy(xpath=OR.Syndication_FirstAvailable_Checkbox)
	public WebElement Syndication_FirstAvailable_Checkbox;
	
	@FindBy(xpath=OR.Syndication_Reset_Button)
	public WebElement Syndication_Reset_Button;
	
	@FindBy(xpath=OR.Syndication_Month_Label)
	public WebElement Syndication_Month_Label;
	
}
