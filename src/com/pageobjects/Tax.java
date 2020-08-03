package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.NewsAddress;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.innroad.inncenter.interfaces.ITax;
import com.innroad.inncenter.pages.NewTax;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Inventory;
import com.innroad.inncenter.webelements.Elements_Tax;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class Tax implements ITax {

	public static Logger taxLogger = Logger.getLogger("Taxes");

	@Override
	public String createTax(WebDriver driver, ExtentTest test1, String taxName, String displayName, String description,
			String value, String category, String taxLedgerAccount,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Tax tax = new Elements_Tax(driver);

		Wait.WaitForElement(driver, OR.NewTaxItem_Title);
		assertEquals(tax.NewTaxItem_Title.getText(), "Tax Item Details", "New Item Tax page does find");
		Wait.WaitForElement(driver, OR.TaxItemName);
		tax.TaxItemName.clear();
		tax.TaxItemName.sendKeys(taxName);
		taxLogger.info("New Tax Name : " + taxName);
		test_steps.add("New Tax Name : " + taxName);
		tax.TaxDispalyName.clear();
		tax.TaxDispalyName.sendKeys(displayName);
		taxLogger.info("New Tax Dispaly Name : " + displayName);
		test_steps.add("New Tax Dispaly Name : " + displayName);
		tax.TaxDescription.clear();
		tax.TaxDescription.sendKeys(description);
		taxLogger.info("New Tax description : " + description);
		test_steps.add("New Tax description : " + description);
		tax.TaxValue.clear();
		tax.TaxValue.sendKeys(value);
		taxLogger.info("New Tax Value : " + value);
		test_steps.add("New Tax Value : " + value);
		tax.TaxPercent.click();
		taxLogger.info("Tax percent is clicked");
		test_steps.add("Tax percent is clicked");
		Wait.explicit_wait_visibilityof_webelement(tax.ExcludeTaxExempt, driver);
		Utility.ScrollToElement(tax.ExcludeTaxExempt, driver);
		tax.ExcludeTaxExempt.click();
		taxLogger.info("Exclude when tax exempt is clicked");
		test_steps.add("Exclude when tax exempt is clicked");
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(tax.taxCategory, driver);
		Select sel = new Select(tax.taxCategory);
		 sel.selectByVisibleText(category);
		//sel.selectByIndex(1);

		taxLogger.info("Tax category selected to : " + category);
		test_steps.add("Tax category selected to : " + category);
		Wait.explicit_wait_visibilityof_webelement(tax.TaxAssociate, driver);
		Utility.ScrollToElement(tax.TaxAssociate, driver);
		Wait.wait1Second();
		tax.TaxAssociate.click();

		Wait.wait3Second();

		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));

		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));
		// System.out.println(driver.findElement(By.xpath("//td[@id='tdTitle']/font[contains(text(),'Ledger
		// Account Picker')]")).getText());

		if (driver.findElements(By.xpath(OR.TaxLedgerAccountPopup)).size() > 0) {

//			taxLedgerAccount = new Select(driver.findElement(By.xpath("//select[@id='lstTaxes']"))).getOptions().get(0)
//					.getText();
			driver.findElement(
					By.xpath("//select[@id='lstTaxes']/option[contains(text(),'" + taxLedgerAccount.trim() + "')]"))
					.click();
			taxLogger.info("Tax ledger account selected to : " + taxLedgerAccount);
			test_steps.add("Tax ledger account selected to : " + taxLedgerAccount);
			tax.TaxPickOne.click();
		} else {

			assertTrue(false, "Failed: No  Ledger Account Available");
		}

		tax.TaxLaedgerAccountDone.click();
		taxLogger.info("Click Tax ledger account Done");
		test_steps.add("Successfully clicked Tax ledger account Done");
		Wait.wait2Second();
		driver.switchTo().defaultContent();
		Wait.WaitForElement(driver, OR.TaxSave);
		taxLogger.info("Successfully clicked Tax Save");
		test_steps.add("Successfully clicked Tax Save");
		Wait.WaitForElement(driver, OR.TaxDone);
		tax.TaxDone.click();
		taxLogger.info("Successfully clicked Tax Done");
		test_steps.add("Successfully clicked Tax Done");
		// Wait.wait5Second();

//		Wait.WaitForElement(driver,
//				"//table[@id='MainContent_dgTaxItemList']/tbody/tr/td[1]/a[contains(text(),'" + taxName.trim() + "')]");
//		if (driver.findElements(By.xpath(
//				"//table[@id='MainContent_dgTaxItemList']/tbody/tr/td[1]/a[contains(text(),'" + taxName.trim() + "')]"))
//				.size() > 0) {
			taxLogger.info("Tax Successfully created : " + taxName);
			test_steps.add("Tax Successfully created : " + taxName);
			driver.findElement(By.xpath("//li[@id='head_reservations']/a/img")).click();
			// Wait.wait5Second();

//		} else {
//			taxLogger.info("Tax not created : " + taxName);
//			test_steps.add("Tax not created : " + taxName);
//			assertEquals(false, true,"Tax not created : " + taxName);
//		}
		return taxLedgerAccount;
	}

	
	public void createTax(WebDriver driver, ExtentTest test1, String taxName, String displayName, String description,
			String value, String category) throws InterruptedException {

		Elements_Tax tax = new Elements_Tax(driver);

		Wait.WaitForElement(driver, OR.NewTaxItem_Title);
		assertEquals(tax.NewTaxItem_Title.getText(), "Tax Item Details", "New Item Tax page does find");
		Wait.WaitForElement(driver, OR.TaxItemName);
		tax.TaxItemName.clear();
		tax.TaxItemName.sendKeys(taxName);
		taxLogger.info("New Tax Name : " + taxName);

		tax.TaxDispalyName.clear();
		tax.TaxDispalyName.sendKeys(displayName);
		taxLogger.info("New Tax Dispaly Name : " + displayName);

		tax.TaxDescription.clear();
		tax.TaxDescription.sendKeys(description);
		taxLogger.info("New Tax description : " + description);

		tax.TaxValue.clear();
		tax.TaxValue.sendKeys(value);
		taxLogger.info("New Tax Value : " + value);

		tax.TaxPercent.click();
		taxLogger.info("Tax percent is clicked");

		Wait.explicit_wait_visibilityof_webelement(tax.ExcludeTaxExempt, driver);
		Utility.ScrollToElement(tax.ExcludeTaxExempt, driver);
		tax.ExcludeTaxExempt.click();
		taxLogger.info("Exclude when tax exempt is clicked");

		Wait.wait2Second();
		Select sel = new Select(tax.taxCategory);
		 sel.selectByVisibleText(category);
		//sel.selectByIndex(1);

		taxLogger.info("Tax category selected to : " + category);

		Wait.explicit_wait_visibilityof_webelement(tax.TaxAssociate, driver);
		Utility.ScrollToElement(tax.TaxAssociate, driver);
		Wait.wait1Second();
		tax.TaxAssociate.click();

		Wait.wait3Second();

		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));

		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));
		// System.out.println(driver.findElement(By.xpath("//td[@id='tdTitle']/font[contains(text(),'Ledger
		// Account Picker')]")).getText());

		if (driver.findElements(By.xpath(OR.TaxLedgerAccountPopup)).size() > 0) {
/*
			taxLedgerAccount = new Select(driver.findElement(By.xpath("//select[@id='lstTaxes']"))).getOptions().get(0)
					.getText();
			driver.findElement(
					By.xpath("//select[@id='lstTaxes']/option[contains(text(),'" + taxLedgerAccount.trim() + "')]"))
					.click();
			taxLogger.info("Tax ledger account selected to : " + taxLedgerAccount);

			tax.TaxPickOne.click();*/
			new Select(driver.findElement(By.xpath("//select[@id='lstTaxes']"))).getOptions();
			
			
			tax.TaxSelectAllLedgerAccounts.click();
			taxLogger.info("Selected All Available Ledger Accounts");
			
		} else {

			assertTrue(false, "Failed: No  Ledger Account Available");
		}

		tax.TaxLaedgerAccountDone.click();
		taxLogger.info("Click Tax ledger account Done");

		Wait.wait2Second();
		driver.switchTo().defaultContent();

		Wait.WaitForElement(driver, OR.TaxDone);
		tax.TaxDone.click();
		taxLogger.info("click Tax Done");
		// Wait.wait5Second();

		Wait.WaitForElement(driver,
				"//table[@id='MainContent_dgTaxItemList']/tbody/tr/td[1]/a[contains(text(),'" + taxName.trim() + "')]");
		if (driver.findElements(By.xpath(
				"//table[@id='MainContent_dgTaxItemList']/tbody/tr/td[1]/a[contains(text(),'" + taxName.trim() + "')]"))
				.size() > 0) {
			taxLogger.info("Tax Successfully created : " + taxName);

			driver.findElement(By.xpath("//li[@id='head_reservations']/a/img")).click();
			// Wait.wait5Second();

		} else {
			taxLogger.info("Tax Successfully not created : " + taxName);
		}
		
	}

	
	public String createTax(WebDriver driver, ExtentTest test1, String taxName, String displayName, String description,
			String value, String category, String taxLedgerAccount, boolean ExcludeTaxExempt)
			throws InterruptedException {

		Elements_Tax tax = new Elements_Tax(driver);

		Wait.WaitForElement(driver, OR.NewTaxItem_Title);
		assertEquals(tax.NewTaxItem_Title.getText(), "Tax Item Details", "New Item Tax page does find");
		Wait.WaitForElement(driver, OR.TaxItemName);
		tax.TaxItemName.clear();
		tax.TaxItemName.sendKeys(taxName);
		taxLogger.info("New Tax Name : " + taxName);
		
		Wait.explicit_wait_visibilityof_webelement(tax.TaxDispalyName, driver);
		Utility.ScrollToElement(tax.TaxDispalyName, driver);
		tax.TaxDispalyName.clear();
		tax.TaxDispalyName.sendKeys(displayName);
		taxLogger.info("New Tax Dispaly Name : " + displayName);

		Wait.explicit_wait_visibilityof_webelement(tax.TaxDescription, driver);
		Utility.ScrollToElement(tax.TaxDescription, driver);
		tax.TaxDescription.clear();
		tax.TaxDescription.sendKeys(description);
		taxLogger.info("New Tax description : " + description);
		
		
		Wait.explicit_wait_visibilityof_webelement(tax.TaxValue, driver);
		Utility.ScrollToElement(tax.TaxValue, driver);
		tax.TaxValue.clear();
		tax.TaxValue.sendKeys(value);
		taxLogger.info("New Tax Value : " + value);
		
		Wait.explicit_wait_visibilityof_webelement(tax.TaxPercent, driver);
		Utility.ScrollToElement(tax.TaxPercent, driver);
        tax.TaxPercent.click();
		taxLogger.info("Tax percent is clicked");

		Wait.explicit_wait_visibilityof_webelement(tax.ExcludeTaxExempt, driver);
		Utility.ScrollToElement(tax.ExcludeTaxExempt, driver);
		if (ExcludeTaxExempt) {
			tax.ExcludeTaxExempt.click();
			taxLogger.info("Exclude when tax exempt is clicked");
		}
		assertEquals(ExcludeTaxExempt, tax.ExcludeTaxExempt.isSelected(),
				"Failed: Exclude Tax Exempt is not in the required state");

		Wait.wait2Second();
		Select sel = new Select(tax.taxCategory);
		// sel.selectByVisibleText(category);
		sel.selectByIndex(1);

		taxLogger.info("Tax category selected to : " + category);

		Wait.explicit_wait_visibilityof_webelement(tax.TaxAssociate, driver);
		Utility.ScrollToElement(tax.TaxAssociate, driver);
		Wait.wait1Second();
		tax.TaxAssociate.click();

		Wait.wait3Second();

		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));

		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));
		// System.out.println(driver.findElement(By.xpath("//td[@id='tdTitle']/font[contains(text(),'Ledger
		// Account Picker')]")).getText());

		if (driver.findElements(By.xpath(OR.TaxLedgerAccountPopup)).size() > 0) {

			driver.findElement(By.xpath("//select[@id='lstTaxes']/option[contains(text(),'" + taxLedgerAccount + "')]"))
					.click();
			taxLogger.info("Tax ledger account selected to : " + taxLedgerAccount);

			tax.TaxPickOne.click();
		} else {

			assertTrue(false, "Failed: Ledger Account Picker not Displayed");
		}

		tax.TaxLaedgerAccountDone.click();
		taxLogger.info("Click Tax ledger account Done");

		Wait.wait2Second();
		driver.switchTo().defaultContent();

		Wait.WaitForElement(driver, OR.TaxDone);
		tax.TaxDone.click();
		taxLogger.info("click Tax Done");

		// Wait.wait5Second();
		SearchTax(driver, taxName, false);

		/*
		 * Wait.WaitForElement(driver,
		 * "//table[@id='MainContent_dgTaxItemList']/tbody/tr/td[1]/a[contains(text(),'"
		 * + taxName.trim() + "')]"); if (driver.findElements(By.xpath(
		 * "//table[@id='MainContent_dgTaxItemList']/tbody/tr/td[1]/a[contains(text(),'"
		 * + taxName.trim() + "')]")) .size() > 0) {
		 * test_steps.add("Tax Successfully crated : " + taxName);
		 * taxLogger.info("Tax Successfully crated : " + taxName);
		 * 
		 * driver.findElement(By.xpath("//li[@id='head_reservations']/a/img")). click();
		 * // Wait.wait5Second();
		 * 
		 * } else { test_steps.add("Tax Successfully not crated : " + taxName);
		 * taxLogger.info("Tax Successfully not crated : " + taxName); }
		 */
		return taxLedgerAccount;
	}
	
	public String createTaxExempt(WebDriver driver, ExtentTest test1, String taxName, String displayName, String description,
			String value, String category, String taxLedgerAccount, boolean ExcludeTaxExempt)
			throws InterruptedException {

		Elements_Tax tax = new Elements_Tax(driver);

		Wait.WaitForElement(driver, OR.NewTaxItem_Title);
		assertEquals(tax.NewTaxItem_Title.getText(), "Tax Item Details", "New Item Tax page does find");
		Wait.WaitForElement(driver, OR.TaxItemName);
		tax.TaxItemName.clear();
		tax.TaxItemName.sendKeys(taxName);
		taxLogger.info("New Tax Name : " + taxName);
		
		Wait.explicit_wait_visibilityof_webelement(tax.TaxDispalyName, driver);
		Utility.ScrollToElement(tax.TaxDispalyName, driver);
		tax.TaxDispalyName.clear();
		tax.TaxDispalyName.sendKeys(displayName);
		taxLogger.info("New Tax Dispaly Name : " + displayName);

		Wait.explicit_wait_visibilityof_webelement(tax.TaxDescription, driver);
		Utility.ScrollToElement(tax.TaxDescription, driver);
		tax.TaxDescription.clear();
		tax.TaxDescription.sendKeys(description);
		taxLogger.info("New Tax description : " + description);
		
		
		Wait.explicit_wait_visibilityof_webelement(tax.TaxValue, driver);
		Utility.ScrollToElement(tax.TaxValue, driver);
		tax.TaxValue.clear();
		tax.TaxValue.sendKeys(value);
		taxLogger.info("New Tax Value : " + value);
		
		Wait.explicit_wait_visibilityof_webelement(tax.TaxPercent, driver);
		Utility.ScrollToElement(tax.TaxPercent, driver);
        tax.TaxPercent.click();
		taxLogger.info("Tax percent is clicked");

		Wait.explicit_wait_visibilityof_webelement(tax.ExcludeTaxExempt, driver);
		Utility.ScrollToElement(tax.ExcludeTaxExempt, driver);
		if (ExcludeTaxExempt) {
			tax.ExcludeTaxExempt.click();
			taxLogger.info("Exclude when tax exempt is clicked");
		}
		assertEquals(ExcludeTaxExempt, tax.ExcludeTaxExempt.isSelected(),
				"Failed: Exclude Tax Exempt is not in the required state");

		Wait.wait2Second();
		Select sel = new Select(tax.taxCategory);
		sel.selectByVisibleText(category);
		//sel.selectByIndex(1);

		taxLogger.info("Tax category selected to : " + category);

		Wait.explicit_wait_visibilityof_webelement(tax.TaxAssociate, driver);
		Utility.ScrollToElement(tax.TaxAssociate, driver);
		Wait.wait1Second();
		tax.TaxAssociate.click();

		Wait.wait3Second();

		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));

		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));
		if (driver.findElements(By.xpath(OR.TaxLedgerAccountPopup)).size() > 0) {

			driver.findElement(By.xpath("//select[@id='lstTaxes']/option[contains(text(),'" + taxLedgerAccount + "')]"))
					.click();
			taxLogger.info("Tax ledger account selected to : " + taxLedgerAccount);

			tax.TaxPickOne.click();
		} else {

			assertTrue(false, "Failed: Ledger Account Picker not Displayed");
		}

		tax.TaxLaedgerAccountDone.click();
		taxLogger.info("Click Tax ledger account Done");

		Wait.wait2Second();
		driver.switchTo().defaultContent();
		
		Wait.WaitForElement(driver, OR.TaxSave);
		tax.TaxSave.click();
		taxLogger.info("click Tax Save");

		Wait.WaitForElement(driver, OR.TaxDone);
		tax.TaxDone.click();
		taxLogger.info("click Tax Done");

		SearchTax(driver, taxName, false);
		return taxLedgerAccount;
	}
	public ArrayList<String> createTaxExemptCheck(WebDriver driver, ExtentTest test1, String taxName, String displayName, String description,
			String value, String category, String taxLedgerAccount, boolean ExcludeTaxExempt)
			throws InterruptedException {
		 ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Tax tax = new Elements_Tax(driver);

		Wait.WaitForElement(driver, OR.NewTaxItem_Title);
		assertEquals(tax.NewTaxItem_Title.getText(), "Tax Item Details", "New Item Tax page does find");
		Wait.WaitForElement(driver, OR.TaxItemName);
		tax.TaxItemName.clear();
		tax.TaxItemName.sendKeys(taxName);
		taxLogger.info("Enter New Tax Name : " + taxName);
		test_steps.add("Enter New Tax Name : " + taxName);
		Wait.explicit_wait_visibilityof_webelement(tax.TaxDispalyName, driver);
		Utility.ScrollToElement(tax.TaxDispalyName, driver);
		tax.TaxDispalyName.clear();
		tax.TaxDispalyName.sendKeys(displayName);
		taxLogger.info("Enter New Tax Dispaly Name : " + displayName);
		test_steps.add("Enter New Tax Dispaly Name : " + displayName);

		Wait.explicit_wait_visibilityof_webelement(tax.TaxDescription, driver);
		Utility.ScrollToElement(tax.TaxDescription, driver);
		tax.TaxDescription.clear();
		tax.TaxDescription.sendKeys(description);
		taxLogger.info("Enter New Tax description : " + description);
		test_steps.add("Enter New Tax description : " + description);
		
		
		Wait.explicit_wait_visibilityof_webelement(tax.TaxValue, driver);
		Utility.ScrollToElement(tax.TaxValue, driver);
		tax.TaxValue.clear();
		tax.TaxValue.sendKeys(value);
		taxLogger.info("Enter New Tax Value : " + value);
		test_steps.add("Enter New Tax Value : " + value);
		
		Wait.explicit_wait_visibilityof_webelement(tax.TaxPercent, driver);
		Utility.ScrollToElement(tax.TaxPercent, driver);
        tax.TaxPercent.click();
		taxLogger.info("Tax percent is clicked");
		test_steps.add("Click on tax percent CheckBox");

		Wait.explicit_wait_visibilityof_webelement(tax.ExcludeTaxExempt, driver);
		Utility.ScrollToElement(tax.ExcludeTaxExempt, driver);
		if (ExcludeTaxExempt) {
			tax.ExcludeTaxExempt.click();
			taxLogger.info("Exclude when tax exempt is clicked");
			test_steps.add("Click on Exclude tax Exempt");
			test_steps.add("The 'Tax Exempt When Excluded' Is Checked");
		}
		assertEquals(ExcludeTaxExempt, tax.ExcludeTaxExempt.isSelected(),
				"Failed: Exclude Tax Exempt is not in the required state");

		Wait.wait2Second();
		Select sel = new Select(tax.taxCategory);
		sel.selectByVisibleText(category);
		test_steps.add("Tax category selected to : " + category);
		taxLogger.info("Tax category selected to : " + category);

		Wait.explicit_wait_visibilityof_webelement(tax.TaxAssociate, driver);
		Utility.ScrollToElement(tax.TaxAssociate, driver);
		Wait.wait1Second();
		tax.TaxAssociate.click();
		test_steps.add("Click on Tax Associate");

		Wait.wait3Second();

		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));

		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));
		if (driver.findElements(By.xpath(OR.TaxLedgerAccountPopup)).size() > 0) {

			driver.findElement(By.xpath("//select[@id='lstTaxes']/option[contains(text(),'" + taxLedgerAccount + "')]"))
					.click();
			taxLogger.info("Tax ledger account selected to : " + taxLedgerAccount);
			test_steps.add("Tax ledger account selected to : " + taxLedgerAccount);

			tax.TaxPickOne.click();
			test_steps.add("Click on Tax Picker");
		} else {

			assertTrue(false, "Failed: Ledger Account Picker not Displayed");
		}

		tax.TaxLaedgerAccountDone.click();
		taxLogger.info("Click On Tax Leadger Account Done Button");
		test_steps.add("Click On Tax Leadger Account Done Button");

		Wait.wait2Second();
		driver.switchTo().defaultContent();
		
		Wait.WaitForElement(driver, OR.TaxSave);
		tax.TaxSave.click();
		taxLogger.info("click Tax Save");
		test_steps.add("Click on save button");

		Wait.WaitForElement(driver, OR.TaxDone);
		tax.TaxDone.click();
		taxLogger.info("click Tax Done");
		test_steps.add("Click on done button");

		SearchTax(driver, taxName, false);
		test_steps.add("Created Tax is searched successfully : " + taxName );
		return test_steps;
	}
	public ArrayList<String> createTaxExemptUncheck(WebDriver driver, ExtentTest test1, String tax_2_Name, String display_2_Name, String description,
			String value, String category, String tax_2_LedgerAccount, boolean ExcludeTaxExempt)
			throws InterruptedException {
		 ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Tax tax = new Elements_Tax(driver);

		Wait.WaitForElement(driver, OR.NewTaxItem_Title);
		assertEquals(tax.NewTaxItem_Title.getText(), "Tax Item Details", "New Item Tax page does find");
		Wait.WaitForElement(driver, OR.TaxItemName);
		tax.TaxItemName.clear();
		tax.TaxItemName.sendKeys(tax_2_Name);
		taxLogger.info("Enter New Tax Name : " + tax_2_Name);
		test_steps.add("Enter New Tax Name : " + tax_2_Name );
		
		Wait.explicit_wait_visibilityof_webelement(tax.TaxDispalyName, driver);
		Utility.ScrollToElement(tax.TaxDispalyName, driver);
		tax.TaxDispalyName.clear();
		tax.TaxDispalyName.sendKeys(display_2_Name);
		taxLogger.info("Enter New Tax Dispaly Name : " + display_2_Name);
		test_steps.add("Enter New Tax Dispaly Name : " + display_2_Name );

		Wait.explicit_wait_visibilityof_webelement(tax.TaxDescription, driver);
		Utility.ScrollToElement(tax.TaxDescription, driver);
		tax.TaxDescription.clear();
		tax.TaxDescription.sendKeys(description);
		taxLogger.info("Enter New Tax description : " + description);
		test_steps.add("Enter New Tax description : " + description);
		
		
		Wait.explicit_wait_visibilityof_webelement(tax.TaxValue, driver);
		Utility.ScrollToElement(tax.TaxValue, driver);
		tax.TaxValue.clear();
		tax.TaxValue.sendKeys(value);
		taxLogger.info("Enter New Tax Value : " + value);
		test_steps.add("Enter New Tax Value : " + value );
		
		Wait.explicit_wait_visibilityof_webelement(tax.TaxPercent, driver);
		Utility.ScrollToElement(tax.TaxPercent, driver);
        tax.TaxPercent.click();
		taxLogger.info("Tax percent is clicked");
		test_steps.add(" Click on Tax Percent Checkbox");

		Wait.explicit_wait_visibilityof_webelement(tax.ExcludeTaxExempt, driver);
		Utility.ScrollToElement(tax.ExcludeTaxExempt, driver);
		assertEquals(!ExcludeTaxExempt, tax.ExcludeTaxExempt.isSelected(),
				"Failed: Exclude Tax Exempt is not in the required state");
		test_steps.add(" The 'Tax Exempt When Excluded' Checkbox Is Unchecked");
		Wait.wait2Second();
		Select sel = new Select(tax.taxCategory);
		sel.selectByVisibleText(category);
		taxLogger.info("Tax category selected to : " + category);
		test_steps.add("Tax category selected to : " + category );

		Wait.explicit_wait_visibilityof_webelement(tax.TaxAssociate, driver);
		Utility.ScrollToElement(tax.TaxAssociate, driver);
		Wait.wait1Second();
		tax.TaxAssociate.click();
		test_steps.add(" Click on Tax Associate button" );

		Wait.wait3Second();

		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));

		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));
		if (driver.findElements(By.xpath(OR.TaxLedgerAccountPopup)).size() > 0) {

			driver.findElement(By.xpath("//select[@id='lstTaxes']/option[contains(text(),'" + tax_2_LedgerAccount + "')]"))
					.click();
			taxLogger.info("Tax ledger account selected to : " + tax_2_LedgerAccount);
			test_steps.add("Tax ledger account selected to : " + tax_2_LedgerAccount);

			tax.TaxPickOne.click();
			test_steps.add("Tax_pick_one is selected" );
		} else {

			assertTrue(false, "Failed: Ledger Account Picker not Displayed");
		}

		tax.TaxLaedgerAccountDone.click();
		taxLogger.info("Click On Tax Ledger Account Done Button");
		test_steps.add("Click On Tax Ledger Account Done Button");

		Wait.wait2Second();
		driver.switchTo().defaultContent();
		
		Wait.WaitForElement(driver, OR.TaxSave);
		tax.TaxSave.click();
		taxLogger.info("click Tax Save Button");
		test_steps.add("click On Save Button" );

		Wait.WaitForElement(driver, OR.TaxDone);
		tax.TaxDone.click();
		taxLogger.info("click Tax Done Button");
		test_steps.add("click On Done Button" );

		SearchTax(driver, tax_2_Name, false);
		test_steps.add("Created Tax is searched successfully : " + tax_2_Name );
		return test_steps;
	}


	public String createTax(WebDriver driver, ExtentTest test1, String taxName, String displayName, String description,
			String value, String category, String taxLedgerAccount, boolean ExcludeTaxExempt, boolean Percentage,
			boolean VAT) throws InterruptedException {

		Elements_Tax tax = new Elements_Tax(driver);

		Wait.explicit_wait_visibilityof_webelement_350(tax.NewTaxItem_Title, driver);
		assertEquals(tax.NewTaxItem_Title.getText(), "Tax Item Details", "New Item Tax page does find");
		Wait.WaitForElement(driver, OR.TaxItemName);
		tax.TaxItemName.clear();
		tax.TaxItemName.sendKeys(taxName);
		taxLogger.info("New Tax Name : " + taxName);

		tax.TaxDispalyName.clear();
		tax.TaxDispalyName.sendKeys(displayName);
		taxLogger.info("New Tax Dispaly Name : " + displayName);

		tax.TaxDescription.clear();
		tax.TaxDescription.sendKeys(description);
		taxLogger.info("New Tax description : " + description);

		tax.TaxValue.clear();
		tax.TaxValue.sendKeys(value);
		taxLogger.info("New Tax Value : " + value);

		Wait.explicit_wait_visibilityof_webelement(tax.TaxPercent, driver);
		Utility.ScrollToElement(tax.TaxPercent, driver);
		if (Percentage) {
			tax.TaxPercent.click();
			taxLogger.info("Tax percent is clicked");
		}

		Wait.explicit_wait_visibilityof_webelement(tax.ExcludeTaxExempt, driver);
		Utility.ScrollToElement(tax.ExcludeTaxExempt, driver);
		if (ExcludeTaxExempt) {
			tax.ExcludeTaxExempt.click();
			taxLogger.info("Exclude when tax exempt is clicked");
		}
		assertEquals(ExcludeTaxExempt, tax.ExcludeTaxExempt.isSelected(),
				"Failed: Exclude Tax Exept is not in the required state");

		if (VAT) {
			tax.CalculateasVAT.click();
			taxLogger.info("Calculate as VATis clicked");
		}

		Wait.explicit_wait_visibilityof_webelement(tax.CalculateasVAT, driver);
		Utility.ScrollToElement(tax.CalculateasVAT, driver);

		Wait.wait2Second();
		Select sel = new Select(tax.taxCategory);
		 sel.selectByVisibleText(category);
		//sel.selectByIndex(1);

		taxLogger.info("Tax category selected to : " + category);

		Wait.explicit_wait_visibilityof_webelement(tax.TaxAssociate, driver);
		Utility.ScrollToElement(tax.TaxAssociate, driver);
		Wait.wait1Second();
		tax.TaxAssociate.click();

		Wait.wait3Second();

		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));

		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));
		// System.out.println(driver.findElement(By.xpath("//td[@id='tdTitle']/font[contains(text(),'Ledger
		// Account Picker')]")).getText());

		if (driver.findElements(By.xpath(OR.TaxLedgerAccountPopup)).size() > 0) {

			driver.findElement(By.xpath("//select[@id='lstTaxes']/option[text()='" + taxLedgerAccount + "']")).click();
			taxLogger.info("Tax ledger account selected to : " + taxLedgerAccount);

			tax.TaxPickOne.click();
		} else {

			assertTrue(false, "Failed: No  Ledger Account Available");
		}

		tax.TaxLaedgerAccountDone.click();
		taxLogger.info("Click Tax ledger account Done");

		Wait.wait2Second();
		driver.switchTo().defaultContent();

		Wait.WaitForElement(driver, OR.TaxDone);
		tax.TaxDone.click();
		taxLogger.info("Click Tax  Done");

		// Wait.wait5Second();

		SearchTax(driver, taxName, false);
		return taxLedgerAccount;
	}

	public ArrayList<String> NewTextItemButton(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Tax tax = new Elements_Tax(driver);
		Wait.wait2Second();

		tax.TaxesLink.click();
		Utility.app_logs.info("Navigate To Tax");
		test_steps.add("Navigate To Tax");

		WebElement existingTaxTable = driver.findElement(By.xpath("//*[@id='MainContent_dgTaxItemList']"));
		if (existingTaxTable.isDisplayed()) {
			List rows = driver.findElements(By.xpath("//table[@id='MainContent_dgTaxItemList']/tbody/tr"));
			int size = rows.size() - 2;
			taxLogger.info("Existing Tax Table Records#: " + size);
			test_steps.add("Existing Tax Table Records#: " + size);
			if (size > 0) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
		} else {
			assertTrue(false);
		}

		Wait.WaitForElement(driver, OR.TaxNewItem);
		test_steps.add("Click on New Tax Item Button");
		tax.TaxNewItem.click();
		Wait.WaitForElement(driver, OR.NewTaxItem_Title);
		assertEquals(tax.NewTaxItem_Title.getText(), "Tax Item Details", "New Item Tax page does find");
		return test_steps;
	}

	public ArrayList<String> ClickNewTextItemButton(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Tax tax = new Elements_Tax(driver);
		Wait.wait2Second();

		tax.TaxesLink.click();
		Utility.app_logs.info("Navigate To Tax");
		

		WebElement existingTaxTable = driver.findElement(By.xpath("//*[@id='MainContent_dgTaxItemList']"));
		if (existingTaxTable.isDisplayed()) {
			List rows = driver.findElements(By.xpath("//table[@id='MainContent_dgTaxItemList']/tbody/tr"));
			int size = rows.size() - 2;
			taxLogger.info("Existing Tax Table Records#: " + size);
			if (size > 0) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
		} else {
			assertTrue(false);
		}

		Wait.WaitForElement(driver, OR.TaxNewItem);
		test_steps.add("Click on New Tax Item Button");
		tax.TaxNewItem.click();
		Wait.WaitForElement(driver, OR.NewTaxItem_Title);
		assertEquals(tax.NewTaxItem_Title.getText(), "Tax Item Details", "New Item Tax page does find");
		return test_steps;
	}
	
	public void Click_NewItem(WebDriver driver, ArrayList<String> test_steps)
	{
		Elements_Tax tax = new Elements_Tax(driver);
		Wait.WaitForElement(driver, OR.TaxNewItem);
		test_steps.add("Click on New Tax Item Button");
		tax.TaxNewItem.click();
	}
	public void ClickTaxes(WebDriver driver) throws InterruptedException {

		Elements_Tax tax = new Elements_Tax(driver);
		Wait.explicit_wait_visibilityof_webelement(tax.TaxesLink, driver);
		tax.TaxesLink.click();
		taxLogger.info("Navigate Tax");

		Wait.WaitForElement(driver, OR.TaxNewItem);
	}

	public void DeleteTax(WebDriver driver) throws InterruptedException {

		Elements_Tax tax = new Elements_Tax(driver);
		List<WebElement> TaxDeleteCheckBox = driver
				.findElements(By.xpath("//table[@id='MainContent_dgTaxItemList']/tbody/tr"));
		System.out.println(TaxDeleteCheckBox.size());

		Wait.wait2Second();
		tax.TaxItemName.click();

	}

	public ArrayList<String> delete_tax(WebDriver driver, String taxName) throws InterruptedException
	{
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_Tax tax = new Elements_Tax(driver);
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String taxPath = "//a[text()='" + taxName + "']//ancestor::tr[contains(@class,'dgItem')]//child::input";
		System.out.println(taxPath);
		Wait.explicit_wait_xpath(taxPath, driver);
		WebElement Tax = driver.findElement(By.xpath(taxPath));
		jse.executeScript("arguments[0].scrollIntoView();", Tax);
		Tax.click();
		//Wait.wait5Second();
		tax.DeleteTax.click();
		taxLogger.info(" Sucessfully deleted the Tax : "+taxName);
		test_steps.add(" Sucessfully deleted the Tax : "+taxName);
		
		return test_steps;
	}

	public void SearchTax(WebDriver driver, String TaxName, boolean click) throws InterruptedException {

		Elements_Tax tax = new Elements_Tax(driver);
		boolean element = driver.findElements(By.cssSelector(NewTax.TaxPagesSize)).size() > 0;
		int element_size = 0;
		int index = 0;
		boolean isTaxCreated = false;
		List<WebElement> TaxElements = null;

		if (!element) {
			Utility.app_logs.info("Tax found in first page");
			// single page
			element_size = driver.findElements(By.cssSelector(NewTax.FindTaxName)).size();

			if (element_size > 1) {
				TaxElements = driver.findElements(By.cssSelector(NewTax.FindTaxName));
				for (int j = 0; j < element_size; j++) {
					if (TaxElements.get(j).getText().contains(TaxName)) {
						isTaxCreated = true;
						index = j;
						break;
					}
				}
			}
		} else {

			int size = driver.findElements(By.cssSelector(NewTax.TaxPagesSize)).size();
			System.out.println("Size:" + size);
			System.out.print("Size:" + size);
			for (int i = 0; i < size; i++) {

				element_size = driver.findElements(By.cssSelector(NewTax.FindTaxName)).size();
				System.out.println("Element Size:" + element_size);

				isTaxCreated = false;
				if (element_size > 1) {
					TaxElements = driver.findElements(By.cssSelector(NewTax.FindTaxName));
					for (int j = 0; j < element_size; j++) {
						// Utility.app_logs.info(TaxElements.get(j).getText());
						if (TaxElements.get(j).getText().equals(TaxName)) {
							Utility.app_logs.info(" Tax name found ");
							isTaxCreated = true;
							index = j;
							// assertEquals(isTaxCreated, true, "Newely creatd
							// Tax does not find in Tax list");
							break;
						}
					}

				}
				if (isTaxCreated == false) {
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("window.scrollBy(0,450)", "");
					System.out.println("Index:" + i);

					WebElement TaxElement = driver.findElement(By.cssSelector(NewTax.TaxPagesSize));
					TaxElement.click();

					Wait.wait2Second();
				}

			}
		}

		if (click) {
			String TaxPath = "//a[.='" + TaxName + "']";
			WebElement CreatedTax = driver.findElement(By.xpath(TaxPath));
			Utility.ScrollToElement(CreatedTax, driver);
			CreatedTax.click();
			Wait.explicit_wait_visibilityof_webelement(tax.NewTaxItem_Title, driver);
		}

	}
	
	public void openTax(WebDriver driver, String TaxName) throws InterruptedException {
		Elements_Tax tax = new Elements_Tax(driver);
		String TaxPath = "//a[.='" + TaxName + "']";
		Wait.waitUntilPresenceOfElementLocated(TaxPath, driver);
		WebElement CreatedTax = driver.findElement(By.xpath(TaxPath));
		
		Utility.ScrollToElement(CreatedTax, driver);
		Wait.explicit_wait_elementToBeClickable(CreatedTax, driver);
		CreatedTax.click();
		Wait.WaitForElement(driver, OR.ExcludeTaxExempt);
	}
	
	public void uncheckExcludeTaxExempt(WebDriver driver, ExtentTest test, ArrayList<String> test_steps) {
		Elements_Tax tax = new Elements_Tax(driver);
		if(tax.ExcludeTaxExempt.isSelected()==true) {
			
			tax.ExcludeTaxExempt.click();
			//taxLogger.info(" Unchecked Exclude Tax Exempt ");
			test_steps.add(" Unchecked Exclude Tax Exempt ");
			
		}
	}
	
	public void checkExcludeTaxExempt(WebDriver driver, ExtentTest test, ArrayList<String> test_steps) {
		Elements_Tax tax = new Elements_Tax(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.ExcludeTaxExempt, driver);
		if(!tax.ExcludeTaxExempt.isSelected()) {
			
			tax.ExcludeTaxExempt.click();
			//taxLogger.info(" Unchecked Exclude Tax Exempt ");
			test_steps.add(" Checked Exclude Tax Exempt ");
			
		}
	}


	public void SearchTax(WebDriver driver, String TaxName, boolean click, boolean SetStatus, String StatusType)
			throws InterruptedException {

		Elements_Tax tax = new Elements_Tax(driver);
		
		Wait.wait2Second();

		tax.TaxesLink.click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		boolean element = driver.findElements(By.cssSelector(NewTax.TaxPagesSize)).size() > 0;
		int element_size = 0;
		int index = 0;
		boolean isTaxCreated = false;
		List<WebElement> TaxElements = null;
		WebElement Status = driver.findElement(By.xpath("//select[@id='MainContent_drpActive']"));
		if (SetStatus) {
			new Select(Status).selectByVisibleText(StatusType);
			jse.executeScript("arguments[0].click();", tax.GoButton);
			Wait.wait5Second();
		}
		if (!element) {
			// single page
			element_size = driver.findElements(By.cssSelector(NewTax.FindTaxName)).size();

			if (element_size > 1) {
				TaxElements = driver.findElements(By.cssSelector(NewTax.FindTaxName));
				for (int j = 0; j < element_size; j++) {
					if (TaxElements.get(j).getText().contains(TaxName)) {
						isTaxCreated = true;
						index = j;
						break;
					}
				}
			}
		} else {

			int size = driver.findElements(By.cssSelector(NewTax.TaxPagesSize)).size();

			for (int i = 0; i <= size; i++) {

				element_size = driver.findElements(By.cssSelector(NewTax.FindTaxName)).size();
				isTaxCreated = false;
				if (element_size > 1) {
					TaxElements = driver.findElements(By.cssSelector(NewTax.FindTaxName));
					for (int j = 0; j < element_size; j++) {

						if (TaxElements.get(j).getText().equals(TaxName)) {
							isTaxCreated = true;
							index = j;
							assertEquals(isTaxCreated, true, "Newely creatd Tax does not find in Tax list");
							break;
						}
					}

				}
				if (isTaxCreated == false) {
					jse.executeScript("window.scrollBy(0,450)", "");
					driver.findElements(By.cssSelector(NewTax.TaxPagesSize)).get(i).click();
					Wait.wait2Second();
				}

			}
		}

		if (click) {
			String TaxPath = "//a[.='" + TaxName + "']";
			WebElement CreatedTax = driver.findElement(By.xpath(TaxPath));
			Utility.ScrollToElement(CreatedTax, driver);
			CreatedTax.click();
			Wait.explicit_wait_visibilityof_webelement(tax.NewTaxItem_Title, driver);
		}

	}

	public ArrayList<String> CheckTaxExempt(WebDriver driver, boolean ExcludeTaxExempt, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Tax tax = new Elements_Tax(driver);

		Wait.explicit_wait_visibilityof_webelement(tax.ExcludeTaxExempt, driver);
		Utility.ScrollToElement(tax.ExcludeTaxExempt, driver);
		if (ExcludeTaxExempt) {
			if (!tax.ExcludeTaxExempt.isSelected()) {
				tax.ExcludeTaxExempt.click();
				test_steps.add("Exclude when tax exempt is Checked");
				taxLogger.info("Exclude when tax exempt is Checked");
			}
		} else if (tax.ExcludeTaxExempt.isSelected()) {
			tax.ExcludeTaxExempt.click();
			test_steps.add("Exclude when tax exempt is UnChecked");
			taxLogger.info("Exclude when tax exempt is UnChecked");
		}

		assertEquals(ExcludeTaxExempt, tax.ExcludeTaxExempt.isSelected(),
				"Failed: Exclude Tax Exept is not in the required state");

		Wait.WaitForElement(driver, OR.TaxSave);
		tax.TaxSave.click();
		test_steps.add("click Tax Save");
		Wait.WaitForElement(driver, OR.TaxDone);
		tax.TaxDone.click();
		test_steps.add("click Tax Done");
		// Wait.wait5Second();

		Wait.WaitForElement(driver, OR.TaxNewItem);
		return test_steps;
	}

	public ArrayList<String> SaveTax(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Tax tax = new Elements_Tax(driver);
		Wait.WaitForElement(driver, OR.TaxSave);
		tax.TaxSave.click();
		test_steps.add("click Tax Save");
		Wait.WaitForElement(driver, OR.TaxDone);
		tax.TaxDone.click();
		test_steps.add("click Tax Done");
		// Wait.wait5Second();

		Wait.WaitForElement(driver, OR.TaxNewItem);
		return test_steps;
	}

	public ArrayList<String> CheckPercent(WebDriver driver, boolean checked, String value,
			ArrayList<String> test_steps) {
		Elements_Tax tax = new Elements_Tax(driver);

		if (checked) {
			if (!tax.TaxPercent.isSelected()) {
				tax.TaxValue.clear();
				tax.TaxValue.sendKeys(value);
				test_steps.add("New Tax Value : " + value);
				taxLogger.info("New Tax Value : " + value);
				tax.TaxPercent.click();
				test_steps.add("Percent is Checked");
				taxLogger.info("Percent is Checked");
			}
		} else if (tax.TaxPercent.isSelected()) {
			tax.TaxValue.clear();
			tax.TaxValue.sendKeys(value);
			test_steps.add("New Tax Value : " + value);
			taxLogger.info("New Tax Value : " + value);
			tax.TaxPercent.click();
			test_steps.add("Percent is UnChecked");
			taxLogger.info("Percent is UnChecked");
		}

		assertEquals(checked, tax.TaxPercent.isSelected(), "Failed: Percent is not in the required state");

		return test_steps;
	}

	public void RemoveLedgerAccount(WebDriver driver, String taxLedgerAccount) {
		// TODO Auto-generated method stub
		Elements_Tax tax = new Elements_Tax(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String acc = "//table[@id='MainContent_dgLedgAccAssociation']//td[text()='" + taxLedgerAccount
				+ "']//parent::tr//child::a";
		WebElement ledgerAccount = driver.findElement(By.xpath(acc));
		jse.executeScript("arguments[0].scrollIntoView();", ledgerAccount);
		ledgerAccount.click();

	}

	public void TaxStatus(WebDriver driver, String Status) {
		// TODO Auto-generated method stub
		Elements_Tax tax = new Elements_Tax(driver);
		new Select(tax.TaxStatus).selectByVisibleText(Status);
		boolean StatusCheck = false;
		if (tax.TaxStatus.getText().equals(Status)) {
			StatusCheck = true;
		}

		// assertTrue(StatusCheck, "Failed:Sttaus Not Selected Correctly");
	}

	public ArrayList<String> saveClick(WebDriver driver){
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_Tax tax = new Elements_Tax(driver);
		
		
		Wait.WaitForElement(driver, OR.TaxSave);
		tax.TaxSave.click();
		taxLogger.info("Validate : Click Tax Save");
		test_steps.add("Validate : Click Tax Save");
		
		return test_steps;
	}
	
	public ArrayList<String> validateReqMessage(WebDriver driver){
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_Tax tax = new Elements_Tax(driver);
		
		Wait.explicit_wait_visibilityof_webelement(tax.TaxReqMessage, driver);
		assertEquals(tax.TaxReqMessage.isDisplayed(), true,"Failed to Validate: * not displayed");
		assertEquals(tax.TaxReqMessage.getText(), "All fields marked with an * are required fields","Failed to Validate Message");
		assertEquals(tax.TaxReqMessage.getCssValue("color"),"rgba(255, 0, 0, 1)","Failed to Validate: Color should be Red");
		taxLogger.info("Validate : "+tax.TaxReqMessage.getText());
		test_steps.add("Validate : "+tax.TaxReqMessage.getText());
		
		return test_steps;
	}
	
	public ArrayList<String> validateTaxItemName(WebDriver driver){
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_Tax tax = new Elements_Tax(driver);
		
		Wait.explicit_wait_visibilityof_webelement(tax.ReqTaxItemName, driver);
		assertEquals(tax.ReqTaxItemName.isDisplayed(), true,"Failed to Validate: * not displayed");
		assertEquals(tax.ReqTaxItemName.getText(), "*","Failed to Validate");
		assertEquals(tax.ReqTaxItemName.getCssValue("color"),"rgba(255, 0, 0, 1)","Failed to Validate: Color should be Red");
		taxLogger.info("Validate : Tax Item Name "+tax.ReqTaxItemName.getText());
		test_steps.add("Validate : Tax Item Name "+tax.ReqTaxItemName.getText());
		return test_steps;
	}
	
	public ArrayList<String> validateTaxDisplayName(WebDriver driver){
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_Tax tax = new Elements_Tax(driver);
		
		Wait.explicit_wait_visibilityof_webelement(tax.ReqTaxItemDisplayName, driver);
		assertEquals(tax.ReqTaxItemDisplayName.isDisplayed(), true,"Failed to Validate: * not displayed");
		assertEquals(tax.ReqTaxItemDisplayName.getText(), "*","Failed to Validate");
		assertEquals(tax.ReqTaxItemDisplayName.getCssValue("color"),"rgba(255, 0, 0, 1)","Failed to Validate: Color should be Red");
		taxLogger.info("Validate : Tax Item Display Name "+tax.ReqTaxItemDisplayName.getText());
		test_steps.add("Validate : Tax Item Display Name "+tax.ReqTaxItemDisplayName.getText());
		return test_steps;
	}
	
	public ArrayList<String> validateTaxItemDesc(WebDriver driver){
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_Tax tax = new Elements_Tax(driver);
		
		Wait.explicit_wait_visibilityof_webelement(tax.ReqTaxItemDesc, driver);
		assertEquals(tax.ReqTaxItemDesc.isDisplayed(), true,"Failed to Validate: * not displayed");
		assertEquals(tax.ReqTaxItemDesc.getText(), "*","Failed to Validate");
		assertEquals(tax.ReqTaxItemDesc.getCssValue("color"),"rgba(255, 0, 0, 1)","Failed to Validate: Color should be Red");
		taxLogger.info("Validate : Tax Item Desc "+tax.ReqTaxItemDesc.getText());
		test_steps.add("Validate : Tax Item Desc "+tax.ReqTaxItemDesc.getText());
		return test_steps;
	}
	
	public ArrayList<String> validateTaxItemValue(WebDriver driver){
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_Tax tax = new Elements_Tax(driver);
		
		Wait.explicit_wait_visibilityof_webelement(tax.ReqTaxItemValue, driver);
		assertEquals(tax.ReqTaxItemValue.isDisplayed(), true,"Failed to Validate: * not displayed");
		assertEquals(tax.ReqTaxItemValue.getText(), "*","Failed to Validate");
		assertEquals(tax.ReqTaxItemValue.getCssValue("color"),"rgba(255, 0, 0, 1)","Failed to Validate: Color should be Red");
		taxLogger.info("Validate : Tax Item Value "+tax.ReqTaxItemValue.getText());
		test_steps.add("Validate : Tax Item Value "+tax.ReqTaxItemValue.getText());
		return test_steps;
	}
	
	public ArrayList<String> validateTaxItemName(WebDriver driver,String taxName){
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_Tax tax = new Elements_Tax(driver);
		Wait.explicit_wait_visibilityof_webelement(tax.TaxItemName, driver);
		
		tax.TaxItemName.clear();
		tax.TaxItemName.sendKeys(taxName);
		taxLogger.info("Validate Enter New Tax Name : " + taxName);
		test_steps.add("Validate Enter New Tax Name : " + taxName);
		
		test_steps.addAll(saveClick(driver));
		
//		if(driver.findElements(By.cssSelector(NewTax.ReqTaxItemName)).size()!=0) {
////			System.out.println("Element is Present");
//			assertEquals(tax.ReqTaxItemName.isDisplayed(), false,"Failed to Validate: * displayed");
//		}else{
////			System.out.println("Element is Absent");
//			assertTrue(true);
//		}
		
		
		return test_steps;
	}
	
	public ArrayList<String> validateTaxDisplayName(WebDriver driver,String displayName){
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_Tax tax = new Elements_Tax(driver);
		Wait.explicit_wait_visibilityof_webelement(tax.TaxDispalyName, driver);
		
		tax.TaxDispalyName.clear();
		tax.TaxDispalyName.sendKeys(displayName);
		taxLogger.info("Validate Enter New Tax Display Name : " + displayName);
		test_steps.add("Validate Enter New Tax Display Name : " + displayName);
		
		test_steps.addAll(saveClick(driver));
//		
//		if(driver.findElements(By.xpath(NewTax.ReqTaxItemDisplayName)).size()!=0) {
////			System.out.println("Element is Present");
//			assertEquals(tax.ReqTaxItemDisplayName.isDisplayed(), false,"Failed to Validate: * displayed");
//		}else{
////			System.out.println("Element is Absent");
//			assertTrue(true);
//		}
		
		return test_steps;
	}
	
	public ArrayList<String> validateTaxItemDesc(WebDriver driver,String desc){
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_Tax tax = new Elements_Tax(driver);
		Wait.explicit_wait_visibilityof_webelement(tax.TaxDescription, driver);
		
		tax.TaxDescription.clear();
		tax.TaxDescription.sendKeys(desc);
		taxLogger.info("Validate Enter New Tax Description : " + desc);
		test_steps.add("Validate Enter New Tax Description : " + desc);
		
		test_steps.addAll(saveClick(driver));
//		if(driver.findElements(By.xpath(NewTax.ReqTaxItemDesc)).size()!=0) {
////			System.out.println("Element is Present");
//			assertEquals(tax.ReqTaxItemDesc.isDisplayed(), false,"Failed to Validate: * displayed");
//		}else{
////			System.out.println("Element is Absent");
//			assertTrue(true);
//		}
		
		
		return test_steps;
	}
	
	public ArrayList<String> validateTaxItemValue(WebDriver driver,String value){
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_Tax tax = new Elements_Tax(driver);
		Wait.explicit_wait_visibilityof_webelement(tax.TaxValue, driver);
		
		tax.TaxValue.clear();
		tax.TaxValue.sendKeys(value);
		taxLogger.info("Validate Enter New Tax Value : " + value);
		test_steps.add("Validate Enter New Tax Value : " + value);
		
		test_steps.addAll(saveClick(driver));
		
//		if(driver.findElements(By.xpath(NewTax.ReqTaxItemValue)).size()!=0) {
////			System.out.println("Element is Present");
//			assertEquals(tax.ReqTaxItemValue.isDisplayed(), false,"Failed to Validate: * displayed");
//		}else{
////			System.out.println("Element is Absent");
//			assertTrue(true);
//		}
//		
//		if(driver.findElements(By.xpath(NewTax.TaxReqMessage)).size()!=0) {
////			System.out.println("Element is Present");
//			assertEquals(tax.TaxReqMessage.isDisplayed(), false,"Failed to Validate: Message is displayed");
//		}else{
////			System.out.println("Element is Absent");
//			assertTrue(true);
//		}
		tax.TaxDone.click();
		taxLogger.info("Validate : Click Tax Done");
		test_steps.add("Validate : Click Tax Done");
		
		return test_steps;
	}
	
	public ArrayList<String> createTaxItemNegitiveCase(WebDriver driver,String taxName, String displayName, String description,
			String value, String category, String taxLedgerAccount) throws InterruptedException{
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_Tax tax = new Elements_Tax(driver);
		Wait.WaitForElement(driver, OR.NewTaxItem_Title);
		assertEquals(tax.NewTaxItem_Title.getText(), "Tax Item Details", "New Item Tax page does find");
		Wait.WaitForElement(driver, OR.TaxItemName);
		tax.TaxItemName.clear();
		tax.TaxItemName.sendKeys(taxName);
		taxLogger.info("New Tax Name : " + taxName);
		test_steps.add("New Tax Name : " + taxName);
		tax.TaxDispalyName.clear();
		tax.TaxDispalyName.sendKeys(displayName);
		taxLogger.info("New Tax Dispaly Name : " + displayName);
		test_steps.add("New Tax Dispaly Name : " + displayName);
		tax.TaxDescription.clear();
		tax.TaxDescription.sendKeys(description);
		taxLogger.info("New Tax description : " + description);
		test_steps.add("New Tax description : " + description);
		tax.TaxValue.clear();
		tax.TaxValue.sendKeys(value);
		taxLogger.info("New Tax Value : " + value);
		test_steps.add("New Tax Value : " + value);
		tax.TaxPercent.click();
		taxLogger.info("Tax percent is clicked");
		test_steps.add("Tax percent is clicked");
		Wait.explicit_wait_visibilityof_webelement(tax.ExcludeTaxExempt, driver);
		Utility.ScrollToElement(tax.ExcludeTaxExempt, driver);
		tax.ExcludeTaxExempt.click();
		taxLogger.info("Exclude when tax exempt is clicked");
		test_steps.add("Exclude when tax exempt is clicked");
		Wait.wait2Second();
		Select sel = new Select(tax.taxCategory);
		// sel.selectByVisibleText(category);
		sel.selectByIndex(1);

		taxLogger.info("Tax category selected to : " + category);
		test_steps.add("Tax category selected to : " + category);
		Wait.explicit_wait_visibilityof_webelement(tax.TaxAssociate, driver);
		Utility.ScrollToElement(tax.TaxAssociate, driver);
		Wait.wait1Second();
		tax.TaxAssociate.click();

		Wait.wait3Second();

		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));

		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));
		// System.out.println(driver.findElement(By.xpath("//td[@id='tdTitle']/font[contains(text(),'Ledger
		// Account Picker')]")).getText());

		if (driver.findElements(By.xpath(OR.TaxLedgerAccountPopup)).size() > 0) {

			taxLedgerAccount = new Select(driver.findElement(By.xpath("//select[@id='lstTaxes']"))).getOptions().get(0)
					.getText();
			driver.findElement(
					By.xpath("//select[@id='lstTaxes']/option[contains(text(),'" + taxLedgerAccount.trim() + "')]"))
					.click();
			taxLogger.info("Tax ledger account selected to : " + taxLedgerAccount);
			test_steps.add("Tax ledger account selected to : " + taxLedgerAccount);
			tax.TaxPickOne.click();
		} else {

			assertTrue(false, "Failed: No  Ledger Account Available");
		}

		tax.TaxLaedgerAccountDone.click();
		taxLogger.info("Click Tax ledger account Done");
		test_steps.add("Successfully clicked Tax ledger account Done");
		Wait.wait2Second();
		driver.switchTo().defaultContent();
		Wait.WaitForElement(driver, OR.TaxSave);
		taxLogger.info("Successfully clicked Tax Save");
		test_steps.add("Successfully clicked Tax Save");
		
		

		return test_steps;	
	}
	
	public ArrayList<String> verifyTaxItemValue(WebDriver driver){
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_Tax tax = new Elements_Tax(driver);
		//Wait.explicit_wait_visibilityof_webelement(tax.TaxValue, driver);
	
		Wait.explicit_wait_visibilityof_webelement(tax.InvalidValueMessage, driver);
		assertEquals(tax.InvalidValueMessage.isDisplayed(), true,"Failed to Validate: * not displayed");
		assertEquals(tax.InvalidValueMessage.getText(), "Invalid Value.","Failed to Validate Invalid value Message");
		assertEquals(tax.InvalidValueMessage.getCssValue("color"),"rgba(255, 0, 0, 1)","Failed to Validate: Color should be Red");
		taxLogger.info("Validate : "+tax.InvalidValueMessage.getText());
		test_steps.add("Validate : "+tax.InvalidValueMessage.getText());
		
		return test_steps;
	}
	
	public String createTaxWithStatus(WebDriver driver, String taxName, String displayName, String description,
			String value, String category, String taxLedgerAccount,String Status,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Tax tax = new Elements_Tax(driver);

		Wait.WaitForElement(driver, OR.NewTaxItem_Title);
		assertEquals(tax.NewTaxItem_Title.getText(), "Tax Item Details", "New Item Tax page does find");
		Wait.WaitForElement(driver, OR.TaxItemName);
		tax.TaxItemName.clear();
		tax.TaxItemName.sendKeys(taxName);
		taxLogger.info("New Tax Name : " + taxName);
		test_steps.add("New Tax Name : " + taxName);
		new Select(tax.TaxStatus).selectByVisibleText(Status);
		taxLogger.info("Tax Status : " + Status);
		test_steps.add("Tax Status : " + Status);
		tax.TaxDispalyName.clear();
		tax.TaxDispalyName.sendKeys(displayName);
		taxLogger.info("New Tax Dispaly Name : " + displayName);
		test_steps.add("New Tax Dispaly Name : " + displayName);
		tax.TaxDescription.clear();
		tax.TaxDescription.sendKeys(description);
		taxLogger.info("New Tax description : " + description);
		test_steps.add("New Tax description : " + description);
		tax.TaxValue.clear();
		tax.TaxValue.sendKeys(value);
		taxLogger.info("New Tax Value : " + value);
		test_steps.add("New Tax Value : " + value);
		tax.TaxPercent.click();
		taxLogger.info("Tax percent is clicked");
		test_steps.add("Tax percent is clicked");
		Wait.explicit_wait_visibilityof_webelement(tax.ExcludeTaxExempt, driver);
		Utility.ScrollToElement(tax.ExcludeTaxExempt, driver);
		tax.ExcludeTaxExempt.click();
		taxLogger.info("Exclude when tax exempt is clicked");
		test_steps.add("Exclude when tax exempt is clicked");
		Wait.wait2Second();
		Select sel = new Select(tax.taxCategory);
		 sel.selectByVisibleText(category);
		//sel.selectByIndex(1);

		taxLogger.info("Tax category selected to : " + category);
		test_steps.add("Tax category selected to : " + category);
		Wait.explicit_wait_visibilityof_webelement(tax.TaxAssociate, driver);
		Utility.ScrollToElement(tax.TaxAssociate, driver);
		Wait.wait1Second();
		tax.TaxAssociate.click();

		Wait.wait3Second();

		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));

		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));
		// System.out.println(driver.findElement(By.xpath("//td[@id='tdTitle']/font[contains(text(),'Ledger
		// Account Picker')]")).getText());

		if (driver.findElements(By.xpath(OR.TaxLedgerAccountPopup)).size() > 0) {

//			taxLedgerAccount = new Select(driver.findElement(By.xpath("//select[@id='lstTaxes']"))).getOptions().get(0)
//					.getText();
			driver.findElement(
					By.xpath("//select[@id='lstTaxes']/option[contains(text(),'" + taxLedgerAccount.trim() + "')]"))
					.click();
			taxLogger.info("Tax ledger account selected to : " + taxLedgerAccount);
			test_steps.add("Tax ledger account selected to : " + taxLedgerAccount);
			tax.TaxPickOne.click();
		} else {

			assertTrue(false, "Failed: Ledger Account Picker not Displayed");
		}

		tax.TaxLaedgerAccountDone.click();
		taxLogger.info("Click Tax ledger account Done");
		test_steps.add("Successfully clicked Tax ledger account Done");
		Wait.wait2Second();
		driver.switchTo().defaultContent();
		Wait.WaitForElement(driver, OR.TaxSave);
		taxLogger.info("Successfully clicked Tax Save");
		test_steps.add("Successfully clicked Tax Save");
		Wait.WaitForElement(driver, OR.TaxDone);
		tax.TaxDone.click();
		taxLogger.info("Successfully clicked Tax Done");
		test_steps.add("Successfully clicked Tax Done");
		Wait.wait1Second();
		
		new Select(driver.findElement(By.xpath("//*[@id='MainContent_drpActive']"))).selectByVisibleText(Status);
		driver.findElement(By.xpath("//*[@id='MainContent_btnGoSearch']")).click();

		Wait.WaitForElement(driver,
				"//table[@id='MainContent_dgTaxItemList']/tbody/tr/td[1]/a[contains(text(),'" + taxName.trim() + "')]");
		if (driver.findElements(By.xpath(
				"//table[@id='MainContent_dgTaxItemList']/tbody/tr/td[1]/a[contains(text(),'" + taxName.trim() + "')]"))
				.size() > 0) {
			taxLogger.info("Tax Successfully created : " + taxName);
			test_steps.add("Tax Successfully created : " + taxName);
			driver.findElement(By.xpath("//li[@id='head_reservations']/a/img")).click();
			// Wait.wait5Second();

		} else {
			taxLogger.info("Tax not created : " + taxName);
			test_steps.add("Tax not created : " + taxName);
			assertEquals(false, true,"Tax not created : " + taxName);
		}
		return taxLedgerAccount;
	}
	
	public String createTaxWithLedAccStatus(WebDriver driver, String taxLedgerAccount,ArrayList<String> test_steps) throws InterruptedException {
		Elements_Tax tax = new Elements_Tax(driver);

		tax.TaxAssociate.click();

		Wait.wait3Second();

		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));

		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));

		//select[@id='lstTaxes']/option
		
		if (driver.findElements(By.xpath(OR.TaxLedgerAccountPopup)).size() > 0) {

			int size=driver.findElements(By.xpath("//select[@id='lstTaxes']/option")).size();
			
			for(int i=1;i<=size;i++) {
				String opt=driver.findElement(By.xpath("//select[@id='lstTaxes']/option["+i+"]")).getText();
				assertTrue(!opt.equals(taxLedgerAccount),"Ledger Account Found");
			}
			tax.TaxLaedgerAccountDone.click();
			taxLogger.info("Verified Tax ledger account not Found : " + taxLedgerAccount);
			test_steps.add("Verified Tax ledger account Not Found : " + taxLedgerAccount);

		} else {
			assertTrue(false, "Failed: Ledger Account Picker not Displayed");
		}
		
		return taxLedgerAccount;
	}
	public void NavToTaxes(WebDriver driver) {
		Elements_Tax tax = new Elements_Tax(driver);
		tax.TaxesLink.click();
		Utility.app_logs.info("Navigate Tax");
	
	}
	
	public ArrayList<String> DeleteAllTaxExcept(WebDriver driver, String TaxName, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Tax tax = new Elements_Tax(driver);
		boolean element = driver.findElements(By.cssSelector(NewTax.TaxPagesSize)).size() > 0;
		int element_size = 0;
		int index = 0;
		boolean AnyTaxFound = false;
		List<WebElement> TaxElements = null;

		if (!element) {
			Utility.app_logs.info("Tax found in first page");
			// single page
			element_size = driver.findElements(By.cssSelector(NewTax.FindTaxName)).size();

			if (element_size > 0) {
				TaxElements = driver.findElements(By.cssSelector(NewTax.FindTaxName));
				for (int j = 0; j < element_size; j++) {
					Utility.app_logs.info(TaxElements.get(j).getText());
					if (!TaxElements.get(j).getText().equals(TaxName)) {
						Utility.app_logs.info(TaxElements.get(j).getText());
						JavascriptExecutor jse = (JavascriptExecutor) driver;
							
						String taxPath = "//a[text()=\""+TaxElements.get(j).getText()
								+ "\"]//ancestor::tr[contains(@class,'dgItem')]//child::input";
						
						Wait.explicit_wait_xpath(taxPath, driver);
						WebElement Tax = driver.findElement(By.xpath(taxPath));
						jse.executeScript("arguments[0].scrollIntoView();", Tax);
						Tax.click();
						AnyTaxFound = true;
						// Wait.wait5Second();
					}
				}
				if (AnyTaxFound) {
					Utility.ScrollToElement(tax.DeleteTax, driver);
					tax.DeleteTax.click();
					taxLogger.info(" Sucessfully deleted the Tax");
					test_steps.add(" Sucessfully deleted the Tax");
				}
			}
		} else {

			int size = driver.findElements(By.cssSelector(NewTax.TaxPagesSize)).size();
			
			for (int i = 0; i < size; i++) {

				element_size = driver.findElements(By.cssSelector(NewTax.FindTaxName)).size();
				System.out.println("Element Size:" + element_size);

				AnyTaxFound = false;
				if (element_size > 1) {
					TaxElements = driver.findElements(By.cssSelector(NewTax.FindTaxName));
					for (int j = 0; j < element_size; j++) {
						Utility.app_logs.info(TaxElements.get(j).getText());
						if (!TaxElements.get(j).getText().equals(TaxName)) {
							Utility.app_logs.info(TaxElements.get(j).getText());
							JavascriptExecutor jse = (JavascriptExecutor) driver;
							String taxPath = "//a[text()='" + TaxElements.get(j).getText()
									+ "']//ancestor::tr[contains(@class,'dgItem')]//child::input";
							
							Wait.explicit_wait_xpath(taxPath, driver);
							WebElement Tax = driver.findElement(By.xpath(taxPath));
							jse.executeScript("arguments[0].scrollIntoView();", Tax);
							Tax.click();
							AnyTaxFound = true;
						}
					}
					if (AnyTaxFound) {
						Utility.ScrollToElement(tax.DeleteTax, driver);
						tax.DeleteTax.click();
						taxLogger.info(" Sucessfully deleted the Tax");
						test_steps.add(" Sucessfully deleted the Tax");
					}

				}

				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,450)", "");

				WebElement TaxElement = driver.findElement(By.cssSelector(NewTax.TaxPagesSize));
				TaxElement.click();

				Wait.wait2Second();

			}
		}
		return test_steps;

	}
	
	public void createTax1(WebDriver driver, String taxName,String taxName1, String displayName1, String description,
			String value, String category, String taxLedgerAccount1, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Tax tax = new Elements_Tax(driver);

		Wait.WaitForElement(driver, OR.NewTaxItem_Title);
		assertEquals(tax.NewTaxItem_Title.getText(), "Tax Item Details", "New Item Tax page does find");
		Wait.WaitForElement(driver, OR.TaxItemName);
		tax.TaxItemName.clear();
		tax.TaxItemName.sendKeys(taxName1);
		taxLogger.info("New Tax Name : " + taxName1);

		tax.TaxDispalyName.clear();
		tax.TaxDispalyName.sendKeys(displayName1);
		taxLogger.info("New Tax Dispaly Name : " + displayName1);

		tax.TaxDescription.clear();
		tax.TaxDescription.sendKeys(description);
		taxLogger.info("New Tax description : " + description);

		tax.TaxValue.clear();
		tax.TaxValue.sendKeys(value);
		taxLogger.info("New Tax Value : " + value);

		tax.TaxPercent.click();
		taxLogger.info("Tax percent is clicked");

		Wait.explicit_wait_visibilityof_webelement(tax.ExcludeTaxExempt, driver);
		Utility.ScrollToElement(tax.ExcludeTaxExempt, driver);
		tax.ExcludeTaxExempt.click();
		taxLogger.info("Exclude when tax exempt is clicked");

		Wait.wait2Second();
		Select sel = new Select(tax.taxCategory);
		 sel.selectByVisibleText(category);
		//sel.selectByIndex(1);

		taxLogger.info("Tax category selected to : " + category);

		Wait.explicit_wait_visibilityof_webelement(tax.TaxAssociate, driver);
		Utility.ScrollToElement(tax.TaxAssociate, driver);
		Wait.wait1Second();
		tax.TaxAssociate.click();

		Wait.wait3Second();

		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));

		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));
		// System.out.println(driver.findElement(By.xpath("//td[@id='tdTitle']/font[contains(text(),'Ledger
		// Account Picker')]")).getText());

		if (driver.findElements(By.xpath(OR.TaxLedgerAccountPopup)).size() > 0) {
			

			

				driver.findElement(By.xpath("//select[@id='lstTaxes']/option[text()='" + taxLedgerAccount1 + "']")).click();

				taxLogger.info("Tax ledger account selected to : " + taxLedgerAccount1);
		
			
			//driver.findElement(By.xpath("//select[@id='lstTaxes']/option[contains(text(),'" + taxLedgerAccount1.trim() + "')]")).click();
			Wait.WaitForElement(driver, OR.TaxPickOne);

			tax.TaxPickOne.click();
			
			Wait.WaitForElement(driver, OR.SelectedLedgerAccount);
			
			taxLedgerAccount1=	tax.SelectedLedgerAccount.getText();
			
			taxLogger.info("Selected Ledger Account : " + taxLedgerAccount1);
			test_steps.add("Selected Ledger Account : " + taxLedgerAccount1);
			/*new Select(driver.findElement(By.xpath("//select[@id='lstTaxes']"))).getOptions();
			
			
			tax.TaxSelectAllLedgerAccounts.click();
			taxLogger.info("Selected All Available Ledger Accounts");*/
			
		} else {

			assertTrue(false, "Failed: No  Ledger Account Available");
		}

		tax.TaxLaedgerAccountDone.click();
		taxLogger.info("Click Tax ledger account Done");

		Wait.wait2Second();
		driver.switchTo().defaultContent();

		Wait.WaitForElement(driver, OR.TaxDone);
		tax.TaxDone.click();
		taxLogger.info("click Tax Done");
		// Wait.wait5Second();

		Wait.WaitForElement(driver,
				"//table[@id='MainContent_dgTaxItemList']/tbody/tr/td[1]/a[contains(text(),'" + taxName1.trim() + "')]");
		if (driver.findElements(By.xpath(
				"//table[@id='MainContent_dgTaxItemList']/tbody/tr/td[1]/a[contains(text(),'" + taxName1.trim() + "')]"))
				.size() > 0) {
			taxLogger.info("Tax Successfully created : " + taxName1);

			driver.findElement(By.xpath("//li[@id='head_reservations']/a/img")).click();
			// Wait.wait5Second();

		} else {
			taxLogger.info("Tax Successfully not created : " + taxName1);
		}
		
		
	}
	
	public void SearchTax1(WebDriver driver, String TaxName1, boolean click) throws InterruptedException {

		Elements_Tax tax = new Elements_Tax(driver);
		boolean element = driver.findElements(By.cssSelector(NewTax.TaxPagesSize)).size() > 0;
		int element_size = 0;
		int index = 0;
		boolean isTaxCreated = false;
		List<WebElement> TaxElements = null;

		if (!element) {
			Utility.app_logs.info("Tax found in first page");
			// single page
			element_size = driver.findElements(By.cssSelector(NewTax.FindTaxName)).size();

			if (element_size > 1) {
				TaxElements = driver.findElements(By.cssSelector(NewTax.FindTaxName));
				for (int j = 0; j < element_size; j++) {
					if (TaxElements.get(j).getText().contains(TaxName1)) {
						isTaxCreated = true;
						index = j;
						break;
					}
				}
			}
		} else {

			int size = driver.findElements(By.cssSelector(NewTax.TaxPagesSize)).size();
			System.out.println("Size:" + size);
			System.out.print("Size:" + size);
			for (int i = 0; i < size; i++) {

				element_size = driver.findElements(By.cssSelector(NewTax.FindTaxName)).size();
				System.out.println("Element Size:" + element_size);

				isTaxCreated = false;
				if (element_size > 1) {
					TaxElements = driver.findElements(By.cssSelector(NewTax.FindTaxName));
					for (int j = 0; j < element_size; j++) {
						// Utility.app_logs.info(TaxElements.get(j).getText());
						if (TaxElements.get(j).getText().equals(TaxName1)) {
							Utility.app_logs.info(" Tax name found ");
							isTaxCreated = true;
							index = j;
							// assertEquals(isTaxCreated, true, "Newely creatd
							// Tax does not find in Tax list");
							break;
						}
					}

				}
				if (isTaxCreated == false) {
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("window.scrollBy(0,450)", "");
					System.out.println("Index:" + i);

					WebElement TaxElement = driver.findElement(By.cssSelector(NewTax.TaxPagesSize));
					TaxElement.click();

					Wait.wait2Second();
				}

			}
		}

		if (click) {
			String TaxPath = "//a[.='" + TaxName1 + "']";
			WebElement CreatedTax = driver.findElement(By.xpath(TaxPath));
			Utility.ScrollToElement(CreatedTax, driver);
			CreatedTax.click();
			Wait.explicit_wait_visibilityof_webelement(tax.NewTaxItem_Title, driver);
		}

	}
	
	public ArrayList<String> delete_tax1(WebDriver driver, String taxName1) throws InterruptedException
	{
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_Tax tax = new Elements_Tax(driver);
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String taxPath = "//a[text()='" + taxName1 + "']//ancestor::tr[contains(@class,'dgItem')]//child::input";
		System.out.println(taxPath);
		Wait.explicit_wait_xpath(taxPath, driver);
		WebElement Tax = driver.findElement(By.xpath(taxPath));
		jse.executeScript("arguments[0].scrollIntoView();", Tax);
		Tax.click();
		//Wait.wait5Second();
		tax.DeleteTax.click();
		taxLogger.info(" Sucessfully deleted the Tax : "+taxName1);
		test_steps.add(" Sucessfully deleted the Tax : "+taxName1);
		
		return test_steps;
	}
	public void SearchTax2(WebDriver driver, String TaxName2, boolean click) throws InterruptedException {

		Elements_Tax tax = new Elements_Tax(driver);
		boolean element = driver.findElements(By.cssSelector(NewTax.TaxPagesSize)).size() > 0;
		int element_size = 0;
		int index = 0;
		boolean isTaxCreated = false;
		List<WebElement> TaxElements = null;

		if (!element) {
			Utility.app_logs.info("Tax found in first page");
			// single page
			element_size = driver.findElements(By.cssSelector(NewTax.FindTaxName)).size();

			if (element_size > 1) {
				TaxElements = driver.findElements(By.cssSelector(NewTax.FindTaxName));
				for (int j = 0; j < element_size; j++) {
					if (TaxElements.get(j).getText().contains(TaxName2)) {
						isTaxCreated = true;
						index = j;
						break;
					}
				}
			}
		} else {

			int size = driver.findElements(By.cssSelector(NewTax.TaxPagesSize)).size();
			System.out.println("Size:" + size);
			System.out.print("Size:" + size);
			for (int i = 0; i < size; i++) {

				element_size = driver.findElements(By.cssSelector(NewTax.FindTaxName)).size();
				System.out.println("Element Size:" + element_size);

				isTaxCreated = false;
				if (element_size > 1) {
					TaxElements = driver.findElements(By.cssSelector(NewTax.FindTaxName));
					for (int j = 0; j < element_size; j++) {
						// Utility.app_logs.info(TaxElements.get(j).getText());
						if (TaxElements.get(j).getText().equals(TaxName2)) {
							Utility.app_logs.info(" Tax name found ");
							isTaxCreated = true;
							index = j;
							// assertEquals(isTaxCreated, true, "Newely creatd
							// Tax does not find in Tax list");
							break;
						}
					}

				}
				if (isTaxCreated == false) {
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("window.scrollBy(0,450)", "");
					System.out.println("Index:" + i);

					WebElement TaxElement = driver.findElement(By.cssSelector(NewTax.TaxPagesSize));
					TaxElement.click();

					Wait.wait2Second();
				}

			}
		}

		if (click) {
			String TaxPath = "//a[.='" + TaxName2 + "']";
			WebElement CreatedTax = driver.findElement(By.xpath(TaxPath));
			Utility.ScrollToElement(CreatedTax, driver);
			CreatedTax.click();
			Wait.explicit_wait_visibilityof_webelement(tax.NewTaxItem_Title, driver);
		}

	}
	
	public ArrayList<String> delete_tax2(WebDriver driver, String taxName2) throws InterruptedException
	{
		ArrayList<String> test_steps=new ArrayList<String>();
		Elements_Tax tax = new Elements_Tax(driver);
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String taxPath = "//a[text()='" + taxName2 + "']//ancestor::tr[contains(@class,'dgItem')]//child::input";
		System.out.println(taxPath);
		Wait.explicit_wait_xpath(taxPath, driver);
		WebElement Tax = driver.findElement(By.xpath(taxPath));
		jse.executeScript("arguments[0].scrollIntoView();", Tax);
		Tax.click();
		//Wait.wait5Second();
		tax.DeleteTax.click();
		taxLogger.info(" Sucessfully deleted the Tax : "+taxName2);
		test_steps.add(" Sucessfully deleted the Tax : "+taxName2);
		
		return test_steps;
	}
	

	public void createTax2(WebDriver driver, String taxName,String taxName2, String displayName2, String description,
			String value, String category, String taxLedgerAccount2, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Tax tax = new Elements_Tax(driver);

		Wait.WaitForElement(driver, OR.NewTaxItem_Title);
		assertEquals(tax.NewTaxItem_Title.getText(), "Tax Item Details", "New Item Tax page does find");
		Wait.WaitForElement(driver, OR.TaxItemName);
		tax.TaxItemName.clear();
		tax.TaxItemName.sendKeys(taxName2);
		taxLogger.info("New Tax Name : " + taxName2);

		tax.TaxDispalyName.clear();
		tax.TaxDispalyName.sendKeys(displayName2);
		taxLogger.info("New Tax Dispaly Name : " + displayName2);

		tax.TaxDescription.clear();
		tax.TaxDescription.sendKeys(description);
		taxLogger.info("New Tax description : " + description);

		tax.TaxValue.clear();
		tax.TaxValue.sendKeys(value);
		taxLogger.info("New Tax Value : " + value);

		
		if(tax.TaxPercent.isSelected()) {
			
			
		}
		tax.TaxPercent.click();
		taxLogger.info("Tax percent is clicked");

		Wait.explicit_wait_visibilityof_webelement(tax.ExcludeTaxExempt, driver);
		Utility.ScrollToElement(tax.ExcludeTaxExempt, driver);
		
		if(tax.ExcludeTaxExempt.isSelected()) {
			
			taxLogger.info("ExcludeTaxExempt was already selected");
			tax.ExcludeTaxExempt.click();
			taxLogger.info("Unchecked ExcludeTaxExempt ");
		}
		
		else {
			
			taxLogger.info("Exclude when tax exempt checkbox is not Checked");
			test_steps.add("Exclude when tax exempt checkbox is not Checked");
		}
		

		Wait.wait2Second();
		Select sel = new Select(tax.taxCategory);
		 sel.selectByVisibleText(category);
		//sel.selectByIndex(1);

		taxLogger.info("Tax category selected to : " + category);

		Wait.explicit_wait_visibilityof_webelement(tax.TaxAssociate, driver);
		Utility.ScrollToElement(tax.TaxAssociate, driver);
		Wait.wait1Second();
		tax.TaxAssociate.click();

		Wait.wait3Second();

		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));

		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));
		// System.out.println(driver.findElement(By.xpath("//td[@id='tdTitle']/font[contains(text(),'Ledger
		// Account Picker')]")).getText());

		if (driver.findElements(By.xpath(OR.TaxLedgerAccountPopup)).size() > 0) {


			driver.findElement(By.xpath("//select[@id='lstTaxes']/option[text()='" + taxLedgerAccount2 + "']")).click();

			taxLogger.info("Tax ledger account selected to : " + taxLedgerAccount2);
	
		
		//driver.findElement(By.xpath("//select[@id='lstTaxes']/option[contains(text(),'" + taxLedgerAccount1.trim() + "')]")).click();
		Wait.WaitForElement(driver, OR.TaxPickOne);

		tax.TaxPickOne.click();
		
		Wait.WaitForElement(driver, OR.SelectedLedgerAccount);
		
		taxLedgerAccount2=	tax.SelectedLedgerAccount.getText();
		
		taxLogger.info("Selected Ledger Account2 : " + taxLedgerAccount2);
		test_steps.add("Selected Ledger Account2 : " + taxLedgerAccount2);	
			
		/*	tax.TaxSelectAllLedgerAccounts.click();
			taxLogger.info("Selected All Available Ledger Accounts");*/
			
		} else {

			assertTrue(false, "Failed: No  Ledger Account Available");
		}

		tax.TaxLaedgerAccountDone.click();
		taxLogger.info("Click Tax ledger account Done");

		Wait.wait2Second();
		driver.switchTo().defaultContent();

		Wait.WaitForElement(driver, OR.TaxDone);
		tax.TaxDone.click();
		taxLogger.info("click Tax Done");
		// Wait.wait5Second();

		Wait.WaitForElement(driver,
				"//table[@id='MainContent_dgTaxItemList']/tbody/tr/td[1]/a[contains(text(),'" + taxName2.trim() + "')]");
		if (driver.findElements(By.xpath(
				"//table[@id='MainContent_dgTaxItemList']/tbody/tr/td[1]/a[contains(text(),'" + taxName2.trim() + "')]"))
				.size() > 0) {
			taxLogger.info("Tax Successfully created : " + taxName2);

			driver.findElement(By.xpath("//li[@id='head_reservations']/a/img")).click();
			// Wait.wait5Second();

		} else {
			taxLogger.info("Tax Successfully not created : " + taxName2);
		}
	}
	
	public boolean isTaxExist(WebDriver driver) throws InterruptedException {
		Elements_Tax tax = new Elements_Tax(driver);
		List<WebElement> isTaxitemExist = driver.findElements(By.xpath(NewTax.TaxListSize));
		return isTaxitemExist.size() > 2;

	}

	public void openTax(WebDriver driver) throws InterruptedException {
		Elements_Tax tax = new Elements_Tax(driver);
		tax.firstTaxItem.click();
	}

	public ArrayList<String> AttachLegderAccount(WebDriver driver, String LedgerAccount) throws InterruptedException {
		Elements_Tax tax = new Elements_Tax(driver);
		 ArrayList<String> steps = new ArrayList<>();
		Wait.explicit_wait_visibilityof_webelement(tax.TaxAssociate, driver);
		Utility.ScrollToElement(tax.TaxAssociate, driver);
		tax.TaxAssociate.click();
		steps.add("Click on associate button");
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));

		//Wait.WaitForElement(driver, NewTax.selectLedgerAccount);
		Wait.explicit_wait_visibilityof_webelement(tax.selectLedgerAccount, driver);
		new Select(tax.selectLedgerAccount).selectByVisibleText(LedgerAccount);
		taxLogger.info("Selected ledger account: "+LedgerAccount);
		steps.add("Selected ledger account: "+LedgerAccount);

		tax.TaxPickOne.click();
		steps.add("Click on picker button");

		tax.TaxLaedgerAccountDone.click();
		taxLogger.info("Click Tax ledger account Done button");
		steps.add("Click Tax ledger account Done button");
		driver.switchTo().defaultContent();

		Wait.WaitForElement(driver, OR.TaxDone);
		Wait.explicit_wait_visibilityof_webelement(tax.TaxDone, driver);
		Wait.explicit_wait_elementToBeClickable(tax.TaxDone, driver);
		
		String attachAccPath = "//td[text()='"+LedgerAccount+"']";
		WebElement verifyAccount = driver.findElement(By.xpath(attachAccPath));
		Utility.ScrollToElement(verifyAccount, driver);
		assertEquals(verifyAccount.getText(), LedgerAccount,"Failed: Attach ledger account is not showing");
		steps.add("Verifie attch ledger account is showing under associate ledger account list");
		tax.TaxDone.click();
		steps.add("Click on done button");
		return steps;
	}
	
	public void PercentCheckBox(WebDriver driver) throws InterruptedException {
		Elements_Tax tax = new Elements_Tax(driver);
		
		System.out.println(tax.TaxPercent.isSelected());
		if (!tax.TaxPercent.isSelected()) {
			tax.TaxPercent.click();
		}
	}
	public String getPercentvalue(WebDriver driver) throws InterruptedException {
		Elements_Tax tax = new Elements_Tax(driver);
		return tax.TaxValue.getAttribute("value");
	}
	public String getTaxItemName(WebDriver driver) throws InterruptedException {
		Elements_Tax tax = new Elements_Tax(driver);
		return tax.TaxItemName.getAttribute("value");
	}
	public String getTaxItemCategory(WebDriver driver) throws InterruptedException {
		Elements_Tax tax = new Elements_Tax(driver);
		Select select = new Select(tax.taxCategory);
		WebElement option = select.getFirstSelectedOption();
		return option.getText();
	}

	public boolean verifyTaxExist(WebDriver driver)
	{
		boolean isExist=false;
		/*isExist=Utility.isElementPresent(driver, By.xpath("//div[@id='MainContent_TaxItems_DIV']"));*/
		isExist=Utility.isElementPresent(driver, By.xpath(NewTax.firstTaxItem));
		taxLogger.info("Tax exist or not " + isExist);
		return isExist;
				
				
	}
	
	public boolean verifyLegerAccountExist(WebDriver driver,String leadgerName)
	{
		boolean isExist=false;
		String path="//table[@id='MainContent_dgLedgAccAssociation']//tr//td[contains(text(),'"+leadgerName+"')]";
		isExist=Utility.isElementPresent(driver, By.xpath(path));
		taxLogger.info("Leadger is exist or not" + isExist);
		return isExist;
	}


public ArrayList<String> deleteTaxStartsWithSameName(WebDriver driver, String taxname) throws InterruptedException {
        
	    Elements_Tax tax = new Elements_Tax(driver);
        boolean element = driver.findElements(By.xpath(OR.Tax_size)).size() > 0;
        boolean isFind = false;
        String taxCheckboxpath = "//table[@class='dgText']/tbody/tr[starts-with(@class,'dgItem')]/td/a[starts-with(.,'"+taxname+"')]/ancestor::td/following-sibling::td/span/input";
        ArrayList<String> test_steps = new ArrayList<>();
       
        if (element) {
        // single page
            System.out.println("in if");
            List<WebElement> taxNameListSize = driver.findElements(By.xpath(taxCheckboxpath));
            for (int i = 0; i < taxNameListSize .size(); i++    ) {
                Utility.ScrollToElement_NoWait(taxNameListSize .get(i), driver);
                taxNameListSize.get(i).click();
               
            }
            test_steps.add("Total number of rates select for delete: " +taxNameListSize.size());
            tax.DeleteTax.click();
            Wait.explicit_wait_visibilityof_webelement_120(tax.DeleteTax, driver);
            Wait.explicit_wait_elementToBeClickable(tax.DeleteTax, driver);
           
        }
        return test_steps;
    }

public boolean isExistingDefaultTaxAvailable(WebDriver driver, String taxLedgerAccount) throws InterruptedException {
	boolean existingFlag = false;

	ArrayList<String> taxNames = existingAvailableTaxesName(driver);
	for(String taxName : taxNames) {
		Elements_Tax tax = new Elements_Tax(driver);
		if(taxName.equalsIgnoreCase("Sales Tax @ 6%")) {
			Wait.WaitForElement(driver, OR.Tax_Items);
			openTax(driver, taxName);
			String acc = "//table[@id='MainContent_dgLedgAccAssociation']//td[text()='" + taxLedgerAccount
					+ "']//parent::tr//child::a";
			Wait.wait3Second();
			Utility.ScrollToElement(tax.TaxAssociate, driver);
		if(driver.findElements(By.xpath(acc)).size()!=0) {
			Wait.wait5Second();
			existingFlag =true;
			clickDoneOnTaxPage(driver);
			break;
		}
		clickDoneOnTaxPage(driver);
		break;
		}
		}
	return existingFlag;
	
}
public void clickDoneOnTaxPage(WebDriver driver) {
	Elements_Tax tax = new Elements_Tax(driver);

	Wait.explicit_wait_visibilityof_webelement(tax.TaxAssociate, driver);
	Wait.explicit_wait_elementToBeClickable(tax.TaxDone, driver);
	tax.TaxDone.click();
}
public ArrayList<String> existingAvailableTaxesName(WebDriver driver) {
	Elements_Tax tax = new Elements_Tax(driver);
	ArrayList<String> TaxNames = new ArrayList<String>();
	Wait.WaitForElement(driver, OR.TaxHeader);
	for (int i = 1; i <= tax.getAllTaxes.size(); i++) {
		String Tname = driver.findElement(By.xpath(
				"//table[@id='MainContent_dgTaxItemList']/tbody/tr[starts-with(@class,'dgItem')][" + i + "]/td/a"))
				.getText();
		System.out.println("in method name:" +  			driver.findElement(By.xpath(
				"//table[@id='MainContent_dgTaxItemList']/tbody/tr[starts-with(@class,'dgItem')][" + i + "]/td/a"))
				.getText());
		TaxNames.add(Tname);
	}
	return TaxNames;


}
public void removeLedgerAccInExistingTaxes(WebDriver driver, String taxLedgerAccount, ArrayList<String>test_steps ) throws InterruptedException {
	Elements_Tax tax = new Elements_Tax(driver);
	Wait.WaitForElement(driver, OR.Tax_Items);
	
	int count = existingAvailableTaxes(driver);
	ArrayList<String> taxExistingName = existingAvailableTaxesName(driver);
	test_steps.add("Existing tax names are: "+ "<b>"+taxExistingName+"</b>");
	for(int i = 0; i<count; i++) {
		Wait.WaitForElement(driver, OR.Tax_Items);
		if(!taxExistingName.get(i).equals("Sales Tax @ 6%")) {
			Wait.WaitForElement(driver, OR.Tax_Items);
			openTax(driver, taxExistingName.get(i));
			String acc = "//table[@id='MainContent_dgLedgAccAssociation']//td[text()='" + taxLedgerAccount
					+ "']//parent::tr//child::a";
			Wait.wait3Second();
		if(driver.findElements(By.xpath(acc)).size()!=0) {
			Wait.wait2Second();
			RemoveLedgerAccount(driver, taxLedgerAccount);
			test_steps.add("Remove room charge ledger account from Tax: "+ "<b>"+taxExistingName.get(i)+"</b>");
		}else {
			test_steps.add("Room Charge ledger account not associated to Tax: "+ "<b>"+taxExistingName.get(i)+"</b>");
		}
		clickDoneOnTaxPage(driver);
		test_steps.add("Click at Done button");
		}
	}
}
public int existingAvailableTaxes(WebDriver driver) {
	Elements_Tax tax = new Elements_Tax(driver);
	int taxCount = 0;
	Wait.WaitForElement(driver, OR.TaxHeader);
	
	try {
		taxCount = tax.getAllTaxes.size();
		System.out.println("in method try:"+ taxCount);
	} catch (Exception e) {
		System.out.println("in method catch:"+ taxCount);
		return taxCount;
		
		
	}

	return taxCount;

}
// check if percent checkbox selected or not
public boolean checkIfPercentSelected(WebDriver driver, ArrayList<String>test_steps) throws InterruptedException {
	Elements_Tax tax = new Elements_Tax(driver);
	boolean flag = false;
	Wait.explicit_wait_visibilityof_webelement(tax.TaxPercent, driver);
	Utility.ScrollToElement(tax.TaxPercent, driver);
	if (tax.TaxPercent.isSelected()) {
		return true;
	}
test_steps.add("Checking if percent checkbox selected:"+ flag);
	return flag;

}

public void selectPercentCheckbox(WebDriver driver, ArrayList<String> test_steps) {
	Elements_Tax tax = new Elements_Tax(driver);
	//boolean isPercentChecked = checkIfPercentSelected(driver, test_steps);
/*		if (isPercentChecked) {
        test_steps.add("percent checkbox already checked");
	} else {

	}*/
	
	Wait.isElementDisplayed(driver, tax.TaxPercent);
	tax.TaxPercent.click();
	test_steps.add("Click at percent checkbox");
}

public String getTaxVal(WebDriver driver, String taxName) {
	Elements_Tax tax = new Elements_Tax(driver);
	Wait.isElementDisplayed(driver, tax.TaxPercent);
	String TaxVal = driver.findElement(By.xpath("//input[@name='ctl00$MainContent$txtTaxItemValue']")).getAttribute("value");
	System.out.println(TaxVal);
	return TaxVal;
	
}

}


