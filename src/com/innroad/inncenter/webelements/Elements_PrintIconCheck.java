package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class Elements_PrintIconCheck {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_PrintIconCheck(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);

	}
	
	@FindBy(xpath = OR.ClosePrintIconPopup)
	public WebElement ClosePrintIconPopup;
	
	@FindBy(xpath = OR.PrintAllRadioButtons)
	public WebElement PrintAllRadioButtons;
	
	@FindBy(xpath=OR.clickPrintIcon)
	public WebElement clickPrintIcon;
	
	@FindBy(xpath=OR.PrintPopUp)
	public WebElement PrintPopUp;

	@FindBy(xpath=OR.AllRadioButtonsList)
	public WebElement AllRadioButtonsList;
	
	@FindBy(xpath = OR.ResPrintButton)
	public WebElement ResPrintButton;
	
	@FindBy(xpath = OR.ResEmailButton)
	public WebElement ResEmailButton;
	
	@FindBy(xpath = OR.ResExportButton)
	public WebElement ResExportButton;
	
	@FindBy(xpath = OR.ExportlistButton)
	public WebElement ExportlistButton;
	
	@FindBy(xpath = OR.AllExportsButtons)
	public WebElement AllExportsButtons;
}
