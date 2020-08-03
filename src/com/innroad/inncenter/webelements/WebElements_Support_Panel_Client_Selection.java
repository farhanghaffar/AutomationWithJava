package com.innroad.inncenter.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class WebElements_Support_Panel_Client_Selection {
	
	
WebDriver driver;
	
	public WebElements_Support_Panel_Client_Selection(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath=OR.ClickArrow)
	public WebElement ClickArrow;
	
	@FindBy(id=OR.clientTextBox)
	public WebElement clientTextBox;
	
	@FindBy(xpath=OR.Settings_Icon)
	public WebElement Settings_Icon;
	
	@FindBy(xpath=OR.Pencil_Icon)
	public WebElement Pencil_Icon;
	
	@FindBy(xpath=OR.Enter_client_TextBox)
	public WebElement Enter_client_TextBox;

}
