package com.innroad.inncenter.pageobjects;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.innroad.inncenter.interfaces.IAddOrPostLineItem;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_FolioLineItemsVoid;
import com.innroad.inncenter.webelements.Elements_Reservation_CopyPage;
import com.relevantcodes.extentreports.ExtentTest;

public class AddOrPostLineItem implements IAddOrPostLineItem {

	public static Logger addOrPostLineItemLogger = Logger.getLogger("AddOrPostLineItem");
	public static String folioLineItem;
	public static String CopiedGuestFolioLineItem;

	public void clickFoliotab(WebDriver driver) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		try {
			Wait.explicit_wait_visibilityof_webelement(FolioLineItems.click_Folio_tab, driver);
			Utility.ScrollToElement(FolioLineItems.click_Folio_tab, driver);
			FolioLineItems.click_Folio_tab.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.click_Add_Button, driver);

	}
	
	
	public ArrayList<String>addLineItem(WebDriver driver, ExtentTest test,String Category ,String Amount,ArrayList<String> test_steps) throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.click_Add_Button, driver);
		Utility.ScrollToElement(FolioLineItems.click_Add_Button, driver);
		FolioLineItems.click_Add_Button.click();
		test_steps.add("Clicked on add button");
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.selectCategory, driver);
		new Select(FolioLineItems.selectCategory).selectByVisibleText(Category);
		test_steps.add("Selected category : "+Category);
		
		FolioLineItems.enterAmount.sendKeys(Amount);
		test_steps.add("Entered amount : "+Amount);
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.clickCommitButton, driver);
		Wait.WaitForElement(driver, OR.clickCommitButton);
		FolioLineItems.clickCommitButton.click();
		test_steps.add("Clicked on commit button");
		Wait.explicit_wait_visibilityof_webelement(FolioLineItems.clickSaveButton, driver);
		
		return test_steps;
		
	}
	
	
	
	public ArrayList<String> getLineItem(WebDriver driver, ArrayList<String> getTest_Steps) {
		// TODO Auto-generated method stub
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		folioLineItem=FolioLineItems.getFolioLineItem.getText();
		addOrPostLineItemLogger.info(" FolioLineItem: " +folioLineItem);
		getTest_Steps.add("Reservation FolioLineItem: " + folioLineItem);
		return getTest_Steps;
	}

	public ArrayList<String> getCopiedGuestFolioLineItem(WebDriver driver, ArrayList<String> getTest_Steps) {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", FolioLineItems.copiedFolioLineItem);

		Wait.WaitForElement(driver, OR.copiedFolioLineItem);
		
		CopiedGuestFolioLineItem=FolioLineItems.copiedFolioLineItem.getText();
		addOrPostLineItemLogger.info(" Copied Reservation FolioLineItem: " +CopiedGuestFolioLineItem);
		getTest_Steps.add("Copied Reservation FolioLineItem: " + CopiedGuestFolioLineItem);
		Assert.assertEquals(folioLineItem, CopiedGuestFolioLineItem);
		addOrPostLineItemLogger.info(" Folio Line Item is copied ");
		getTest_Steps.add(" Folio Line Item is copied ");
		return getTest_Steps;
	}

	public ArrayList<String> getCopiedGuestProfile(WebDriver driver,String FirstName, String LastName,String Line1, String City,String State, String Country,String Postalcode,String Phonenumber,String Email, ArrayList<String> getTest_Steps ) {
		Elements_Reservation_CopyPage res_copy = new Elements_Reservation_CopyPage(driver);
		//Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.Copied_First_Name);
		

		if(!FirstName.equals(res_copy.Copied_First_Name.getAttribute("value"))&&
			!LastName.equals(res_copy.Copied_Last_Name.getAttribute("value"))&&
			Line1.equals(res_copy.Copied_Line1.getAttribute("value"))&&
			City.equals(res_copy.Copied_City.getAttribute("value"))&&
			State.equals(new Select(res_copy.CopiedSelect_State).getFirstSelectedOption().getText()) &&
			Country.equals(new Select(res_copy.CopiedSelect_Country).getFirstSelectedOption().getText()) &&
			Postalcode.equals(res_copy.Copied_Postal_Code.getAttribute("value")) &&
			Phonenumber.equals(res_copy.Copied_Phone_Number.getAttribute("value")) &&
			Email.equals(res_copy.Copied_email.getAttribute("value"))){

			
			addOrPostLineItemLogger.info("****guest profile is copied*****");
			getTest_Steps.add("****guest profile is copied*****");
			
		}
		return getTest_Steps;
	}
	
	public ArrayList<String> folioLineItemValidation(WebDriver driver, ArrayList<String> getTest_Steps) {
		//Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		Assert.assertEquals(AddOrPostLineItem.folioLineItem, AddOrPostLineItem.CopiedGuestFolioLineItem, "Validated Folio Line Item for Copy Reservation");
		return getTest_Steps;
	}

	public void adjustLineItem(WebDriver driver, String folioitemDescription, String folioLineAmount, String folioNotes)
			throws InterruptedException {
		Elements_FolioLineItemsVoid FolioLineItems = new Elements_FolioLineItemsVoid(driver);
		// boolean lineItemStatusPending=FolioLineItems.lineItem1.isDisplayed();
		// boolean checkbox=FolioLineItems.verifyCheckbox.isEnabled();
		// boolean
		// lineItemStatusPostFutureDate=FolioLineItems.clickOkForPopup.isDisplayed();
		Wait.wait10Second();
		if (FolioLineItems.lineItemPendingStatus.isDisplayed() == true) {
			if (FolioLineItems.verifyCheckboxPendingStatus.isEnabled()) {
				Wait.explicit_wait_visibilityof_webelement(FolioLineItems.getBalanceFolioLineItems, driver);
				String getBalance = FolioLineItems.getBalanceFolioLineItems.getText();
				addOrPostLineItemLogger.info(" Balance of the Folio Line Items before adjusting " + getBalance);
				Wait.wait2Second();
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].scrollIntoView();", FolioLineItems.pendingItemDescription);

				FolioLineItems.pendingItemDescription.click();
				Wait.explicit_wait_visibilityof_webelement(FolioLineItems.selectCategoryItem, driver);
				new Select(FolioLineItems.selectCategoryItem).selectByIndex(1);
				Wait.explicit_wait_visibilityof_webelement(FolioLineItems.itemDescription, driver);
				FolioLineItems.itemDescription.sendKeys(folioitemDescription);
				FolioLineItems.foliolineItemAmount.sendKeys(folioLineAmount);
				FolioLineItems.foliolineItemNotes.sendKeys(folioNotes);
				FolioLineItems.foliolineItemAddButton.click();
				Wait.explicit_wait_visibilityof_webelement(FolioLineItems.foliolineItemContinueButton, driver);
				FolioLineItems.foliolineItemContinueButton.click();
				Wait.explicit_wait_visibilityof_webelement(FolioLineItems.lineItemPendingStatus, driver);
				String getBalance1 = FolioLineItems.getBalanceFolioLineItems.getText();
				addOrPostLineItemLogger.info(" Balance of the Folio Line Items after adjusting " + getBalance1);
				FolioLineItems.lineItemPendingStatus.click();
				Wait.explicit_wait_visibilityof_webelement(FolioLineItems.clickSaveButton, driver);
				FolioLineItems.clickSaveButton.click();
				Wait.wait10Second();
				addOrPostLineItemLogger.info(" Posted the Line Item successfully ");
				Wait.wait15Second();
			}
			/*
			 * FolioLineItems.clickSaveButton.click(); Wait.wait10Second();
			 * FolioLineItems.clickOnDescription.click(); Wait.wait10Second();
			 * FolioLineItems.clickRollBackButtonInPopUp.click();
			 * Wait.wait10Second(); FolioLineItems.clickContinueButton.click();
			 * Wait.wait10Second(); FolioLineItems.clickSaveButton.click();
			 * Wait.wait10Second();
			 */

		}
		// boolean lineItemStatusPosted=FolioLineItems.lineItem2.isDisplayed();

		// try{

		if (FolioLineItems.lineItemPostStatus.isDisplayed() == true) {
			if (FolioLineItems.verifyCheckboxPostStatus.isEnabled() != true) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].scrollIntoView();", FolioLineItems.clickOnDescription);

				FolioLineItems.clickOnDescription.click();
				Wait.explicit_wait_visibilityof_webelement(FolioLineItems.clickRollBackButtonInPopUp, driver);
				FolioLineItems.clickRollBackButtonInPopUp.click();
				Wait.explicit_wait_visibilityof_webelement(FolioLineItems.clickContinueButton, driver);
				FolioLineItems.clickContinueButton.click();
				addOrPostLineItemLogger.info(" Posted Line Item Rolled back successfully ");
				Wait.explicit_wait_visibilityof_webelement(FolioLineItems.clickSaveButton, driver);
				FolioLineItems.clickSaveButton.click();
				Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
				// Wait.wait10Second();
			} else {
				Assert.fail("Failed to Disable the checkbox after posting the line Item");

			}
		}

		/*
		 * catch(Exception e){
		 * 
		 * addOrPostLineItemLogger.info(e.getMessage());
		 */

	}

}
