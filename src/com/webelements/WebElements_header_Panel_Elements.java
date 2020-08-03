package com.innroad.inncenter.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class WebElements_header_Panel_Elements {
	
	
	WebDriver driver;

	public WebElements_header_Panel_Elements(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	@FindBy(id=OR.SearchIcon)
	public WebElement SearchIcon;
	
	@FindBy(id=OR.Inventory_Icon)
	public WebElement Inventory_Icon;

}
