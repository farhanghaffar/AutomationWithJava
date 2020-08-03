package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class WebElementsLogin {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("WebElementsLogin");

	public WebElementsLogin(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(id = OR.clientCode)
	public WebElement clientCode;

	@FindBy(id = OR.userID)
	public WebElement userID;

	@FindBy(id = OR.password)
	public WebElement password;

	@FindBy(id = "btnLogon")
	public WebElement Login;
	
	@FindBy(xpath = OR.LoginModuleLoding)
	public WebElement LoginModuleLoding;

}
