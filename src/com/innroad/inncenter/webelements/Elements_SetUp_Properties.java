package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Setup;

public class Elements_SetUp_Properties {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_SetUp_Properties(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);

	}

	@FindBy(xpath = OR.propertyName)
	public WebElement propertyName;

	@FindBy(xpath = OR.Property_Grid)
	public WebElement Property_Grid;

	@FindBy(xpath = OR.Property_Options)
	public WebElement Property_Options;

	@FindBy(xpath = OR.Property_CreditCard)
	public WebElement Property_CreditCard;

	@FindBy(xpath = OR.OptionGuaranteed)
	public WebElement OptionGuaranteed;

	@FindBy(xpath = OR.OptionCreditCardAlways)
	public WebElement OptionCreditCardAlways;

	@FindBy(xpath = OR_Setup.Setup_PropertiesGuestStatementOption)
	public WebElement Setup_PropertiesGuestStatementOption;

	@FindBy(xpath = OR_Setup.Setup_PropertiesGuestRegistrationOption)
	public WebElement Setup_PropertiesGuestRegistrationOption;

	@FindBy(xpath = OR_Setup.Setup_PropertiesGuaranteedOption)
	public WebElement Setup_PropertiesGuaranteedOption;

	@FindBy(xpath = OR_Setup.PROPERTY_DEPOSITREQUIRED_SAVEGAURANTEEDRESERVATION)
	public WebElement PROPERTY_DEPOSITREQUIRED_SAVEGAURANTEEDRESERVATION;

	@FindBy(xpath = OR_Setup.PROPERTY_DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION)
	public WebElement PROPERTY_DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION;
	
	@FindBy(xpath = OR_Setup.NonZeroBalanceWhileCheckOut)
	public WebElement NonZeroBalanceWhileCheckOut;

}
