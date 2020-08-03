package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.pages.NewTax;
import com.innroad.inncenter.properties.OR;

public class Elements_Tax {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_Tax(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = OR.TaxNewItem)
	public WebElement TaxNewItem;

	@FindBy(xpath = OR.TaxItemName)
	public WebElement TaxItemName;

	@FindBy(xpath = OR.TaxDispalyName)
	public WebElement TaxDispalyName;

	@FindBy(xpath = OR.TaxDescription)
	public WebElement TaxDescription;

	@FindBy(xpath = OR.TaxValue)
	public WebElement TaxValue;

	@FindBy(xpath = OR.CalculateasVAT)
	public WebElement CalculateasVAT;

	@FindBy(xpath = OR.ExcludeTaxExempt)
	public WebElement ExcludeTaxExempt;

	@FindBy(xpath = OR.TaxPercent)
	public WebElement TaxPercent;

	@FindBy(xpath = OR.taxCategory)
	public WebElement taxCategory;

	@FindBy(xpath = OR.TaxAssociate)
	public WebElement TaxAssociate;
	
	@FindBy(xpath = OR.TaxSelectAllLedgerAccounts)
	public WebElement TaxSelectAllLedgerAccounts;

	@FindBy(xpath = OR.TaxLedgerAccountPopup)
	public WebElement TaxLedgerAccountPopup;

	@FindBy(xpath = OR.TaxPickOne)
	public WebElement TaxPickOne;

	@FindBy(xpath = OR.TaxLaedgerAccountDone)
	public WebElement TaxLaedgerAccountDone;

	@FindBy(xpath = OR.TaxDone)
	public WebElement TaxDone;

	@FindBy(xpath = OR.TaxSave)
	public WebElement TaxSave;

	@FindBy(xpath = OR.NewTaxItem_Title)
	public WebElement NewTaxItem_Title;

	@FindBy(xpath = OR.TaxStatus)
	public WebElement TaxStatus;

	@FindBy(xpath = OR.TaxesLink)
	public WebElement TaxesLink;

	@FindBy(xpath = NewTax.DeleteTax)
	public WebElement DeleteTax;

	@FindBy(xpath = NewTax.GoButton)
	public WebElement GoButton;

	@FindBy(xpath = NewTax.TaxReqMessage)
	public WebElement TaxReqMessage;
	
	@FindBy(xpath = NewTax.ReqTaxItemName)
	public WebElement ReqTaxItemName;
	
	@FindBy(xpath = NewTax.ReqTaxItemDisplayName)
	public WebElement ReqTaxItemDisplayName;
	
	@FindBy(xpath = NewTax.ReqTaxItemDesc)
	public WebElement ReqTaxItemDesc;
	
	@FindBy(xpath = NewTax.ReqTaxItemValue)
	public WebElement ReqTaxItemValue;
	
	@FindBy(xpath = NewTax.InvalidValueMessage)
	public WebElement InvalidValueMessage;
	
	@FindBy(xpath = OR.SelectedLedgerAccount)
	public WebElement SelectedLedgerAccount;
	
	@FindBy(xpath = NewTax.firstTaxItem)
	public WebElement firstTaxItem;
	
	@FindBy(id = NewTax.selectLedgerAccount)
	public WebElement selectLedgerAccount;

	@FindBy(xpath = OR.GetAlltaxes)
	public List<WebElement> getAllTaxes;
	
}
