package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class Elements_Blackouts {
	WebDriver driver ;
	public static Logger app_logs = Logger.getLogger("devpinoyLogger");
	
	public Elements_Blackouts(WebDriver driver2)
	{
		this.driver=driver2;
		PageFactory.initElements(this.driver, this);
		
		 
	}
	
	@FindBy(xpath=OR.BlackOuts_Source_Label)
	public WebElement BlackOuts_Source_Label;
	
	@FindBy(xpath=OR.BlackOuts_FirstAvailable_Checkbox)
	public WebElement BlackOuts_FirstAvailable_Checkbox;
	
	@FindBy(xpath=OR.BlackOuts_Reset_Button)
	public WebElement BlackOuts_Reset_Button;
}
