package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.innroad.inncenter.interfaces.IListManagement;
import com.innroad.inncenter.pages.NewListManagement;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_ListManagement;

public class ListManagement implements IListManagement {

	public static Logger accountsLogger = Logger.getLogger("ListManagement");

	@Override
	public void NewItemCreation(WebDriver driver, String Name, String Description) throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_ListManagement listManagement = new Elements_ListManagement(driver);
		int size = listManagement.CustomItemListSize.size();
		System.out.println("size:" + size);
		listManagement.NewItemButton.click();
		String NewitemName = "//input[@id='MainContent_dgclientList_txtListItemName_" + (size - 1) + "']";
		String NewitemDesc = "//input[@id='MainContent_dgclientList_txtlistItemDesc_" + (size - 1) + "']";
		WebElement Newitem_Name = driver.findElement(By.xpath(NewitemName));
		WebElement Newitem_Description = driver.findElement(By.xpath(NewitemDesc));

		Newitem_Name.sendKeys(Name);
		assertTrue(Newitem_Name.getAttribute("value").equals(Name), " Name didn't add");
		Newitem_Description.sendKeys(Description);
		assertTrue(Newitem_Description.getAttribute("value").equals(Description), " Description didn't add");

	}

	@Override
	public void SelectFilter(WebDriver driver, String FilterName) throws InterruptedException {
		// TODO Auto-generated method stub

		Elements_ListManagement listManagement = new Elements_ListManagement(driver);

		for (int i = 0; i < listManagement.FilterElementList.size(); i++) {

			if (listManagement.FilterElementList.get(i).getText().equals(FilterName)) {
				listManagement.FilterElementList.get(i).click();
				assertTrue(listManagement.SystemItemTextField.getText().equals("System Items"),
						" Filter didn't select");
				break;
			}

		}

	}

	public void SaveButtonClick(WebDriver driver) throws InterruptedException {
		Elements_ListManagement listManagement = new Elements_ListManagement(driver);
		int CustomListSizeBefore = listManagement.CustomItemListSize.size();
		listManagement.SaveButton.click();
		Wait.wait2Second();
		int CustomListSizeAfter = listManagement.CustomItemListSize.size();
		System.out.println("Before:" + CustomListSizeBefore + " After:" + CustomListSizeAfter);
		// assertTrue(CustomListSizeAfter > CustomListSizeBefore, "New Item
		// didn't add");

	}

	public void SelectRatePlan(WebDriver driver, String RatePlanName) throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_ListManagement listManagement = new Elements_ListManagement(driver);
		int size = listManagement.CustomItemListSize.size();
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		for (int i = 0; i < size - 1; i++) {

			String itemName = driver
					.findElement(By.xpath("//span[@id='MainContent_dgclientList_lblListItemName_" + i + "']"))
					.getText();
			if (itemName.equals(RatePlanName)) {
				WebElement Rateplancheckbox = driver
						.findElement(By.xpath("//input[@id='MainContent_dgclientList_chkLstItemFlg_" + i + "']"));
				jse.executeScript("arguments[0].click();", Rateplancheckbox);

				assertTrue(Rateplancheckbox.isSelected(), " Rate Plan didn't select");
			}
		}

	}

	public void EditButtonClick(WebDriver driver) throws InterruptedException {
		Elements_ListManagement listManagement = new Elements_ListManagement(driver);
		listManagement.EditButton.click();

	}

	public void ChangeStatusofRateplan(WebDriver driver, String RatePlanName, String Status)
			throws InterruptedException {
		Elements_ListManagement listManagement = new Elements_ListManagement(driver);
		int size = listManagement.CustomItemListSize.size();

		String itemName = driver
				.findElement(By.xpath("//span[@id='MainContent_dgclientList_lblListItemName_" + (size - 2) + "']"))
				.getAttribute("value");
		if (itemName.equals(RatePlanName)) {
			WebElement SelectStatus = driver.findElement(
					By.xpath("//select[@id='MainContent_dgclientList_ddlListItemStatus_" + (size - 2) + "']"));
			new Select(SelectStatus).selectByVisibleText(Status);
			String FirstSelectedoption = new Select(SelectStatus).getFirstSelectedOption().getText();

			assertTrue(FirstSelectedoption.equals(Status), "Status didn't select");
		}
		listManagement.SaveButton.click();

	}

	public void SelectStatus(WebDriver driver, String Status) throws InterruptedException {
		Elements_ListManagement listManagement = new Elements_ListManagement(driver);
		new Select(listManagement.SelectStatus).selectByVisibleText(Status);
		Wait.WaitForElement(driver, NewListManagement.RatePlanInActiveCustomListCheckbox);
		assertTrue(listManagement.RatePlanInActiveCustomListCheckbox.isDisplayed(),
				" RatePlan InActive Custom List didn't Display");

	}

	public void DeleteCustomInActiveRatePlan(WebDriver driver, String RatePlanName) throws InterruptedException {
		Elements_ListManagement listManagement = new Elements_ListManagement(driver);
		int tablesizebefore = listManagement.CustomItemListSize.size();
		WebElement RatePlanCheckbox = driver
				.findElement(By.xpath("//table[@id='MainContent_dgclientList']//span[text()='" + RatePlanName
						+ "']//parent::td/preceding-sibling::td//input"));
		Utility.ScrollToElement(RatePlanCheckbox, driver);
		RatePlanCheckbox.click();
		listManagement.DeleteButton.click();
		Wait.wait2Second();
		int tablesizeafter = listManagement.CustomItemListSize.size();
		System.out.print("Befoee:" + tablesizebefore + " After:" + tablesizeafter);
		assertEquals(tablesizebefore > tablesizeafter, true, " Didn't delete all items");

	}

	// public void DeleteAllCustomInActiveRatePlan(WebDriver driver) {
	// Elements_ListManagement listManagement = new
	// Elements_ListManagement(driver);
	// listManagement.RatePlanInActiveCustomListCheckbox.click();
	// listManagement.DeleteButton.click();
	// int tablesize = listManagement.CustomItemListSize.size();
	// assertTrue(tablesize < 2, " Didn't delete all items");
	//
	// }

	public void CreateRatePlan(WebDriver driver, String Name, String Description, String Status)
			throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_ListManagement listManagement = new Elements_ListManagement(driver);

		listManagement.NewItemButton.click();
		Wait.WaitForElement(driver, NewListManagement.RatePlan_Name);
		Utility.ScrollToElement(listManagement.RatePlan_Name, driver);
		listManagement.RatePlan_Name.sendKeys(Name);
		assertTrue(listManagement.RatePlan_Name.getAttribute("value").equals(Name), " Name didn't add");
		listManagement.RatePlan_Description.sendKeys(Description);
		assertTrue(listManagement.RatePlan_Description.getAttribute("value").equals(Description),
				" Description didn't add");
		new Select(listManagement.RatePlan_Status).selectByVisibleText(Status);
	}

	public void SaveRatePlan(WebDriver driver) throws InterruptedException {
		Elements_ListManagement listManagement = new Elements_ListManagement(driver);
		Utility.ScrollToElement(listManagement.SaveButton, driver);
		listManagement.SaveButton.click();
	}

	public void VerifyRatePlan(WebDriver driver, String Name, boolean Exist) throws InterruptedException {
		Elements_ListManagement listManagement = new Elements_ListManagement(driver);
		Wait.explicit_wait_visibilityof_webelement(listManagement.CustomRatePlan_NameList, driver);
		List<WebElement> names = driver.findElements(By.xpath(NewListManagement.CustomRatePlan_NameList));
		boolean found = false;
		for (WebElement CustomName : names) {
			Utility.app_logs.info(CustomName.getText());
			if (CustomName.getText().equals(Name)) {
				found = true;
			}
		}
		assertEquals(found, Exist, "Verification Failed");

	}

	public boolean IsRatePlanExists(WebDriver driver, String Name) throws InterruptedException {
		Elements_ListManagement listManagement = new Elements_ListManagement(driver);
		Wait.explicit_wait_visibilityof_webelement(listManagement.CustomRatePlan_NameList, driver);
		List<WebElement> names = driver.findElements(By.xpath(NewListManagement.CustomRatePlan_NameList));
		boolean found = false;
		for (WebElement CustomName : names) {
			Utility.app_logs.info(CustomName.getText());
			if (CustomName.getText().equals(Name)) {
				found = true;
			}
		}
		return found;
	}

	public ArrayList<String> SelectRatePlan_ChangeStatus(WebDriver driver, String RatePlanName, String Status,
			ArrayList<String> test_steps) throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_ListManagement listManagement = new Elements_ListManagement(driver);
		int size = listManagement.CustomItemListSize.size();
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		for (int i = 0; i < size - 1; i++) {

			String itemName = driver
					.findElement(By.xpath("//span[@id='MainContent_dgclientList_lblListItemName_" + i + "']"))
					.getText();
			if (itemName.equals(RatePlanName)) {
				WebElement Rateplancheckbox = driver
						.findElement(By.xpath("//input[@id='MainContent_dgclientList_chkLstItemFlg_" + i + "']"));
				jse.executeScript("arguments[0].click();", Rateplancheckbox);

				assertTrue(Rateplancheckbox.isSelected(), " Rate Plan didn't select");
				test_steps.add("Rate Plan : " + RatePlanName + " Selected");
				accountsLogger.info("Rate Plan : " + RatePlanName + " Selected");

				EditButtonClick(driver);
				test_steps.add("Click on Edit Button");
				accountsLogger.info("Click on Edit Button");
				WebElement SelectStatus = driver
						.findElement(By.xpath("//input[@id='MainContent_dgclientList_chkLstItemFlg_" + i
								+ "']/parent::td/following-sibling::td[3]//select"));
				new Select(SelectStatus).selectByVisibleText(Status);
				String FirstSelectedoption = new Select(SelectStatus).getFirstSelectedOption().getText();

				assertTrue(FirstSelectedoption.equals(Status), "Status didn't select");
				test_steps.add("Rate Plan : " + RatePlanName + " Status Changed To " + Status);
				accountsLogger.info("Rate Plan : " + RatePlanName + " Status Changed To " + Status);
				break;
			}

		}
		listManagement.SaveButton.click();
		test_steps.add("Save Button Click");
		accountsLogger.info("Save Button Click");
		return test_steps;

	}
	
	public void NewItemCreation(WebDriver driver, String Name, String Description, ArrayList<String> test_steps)
			throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_ListManagement listManagement = new Elements_ListManagement(driver);
		int size = listManagement.CustomItemListSize.size();
		System.out.println("size:" + size);
		Wait.WaitForElement(driver, NewListManagement.NewItemButton);
		Wait.waitForElementToBeVisibile(By.xpath(NewListManagement.NewItemButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(NewListManagement.NewItemButton), driver);
		listManagement.NewItemButton.click();
		accountsLogger.info("New Item Button Clicked");
		test_steps.add("New Item Button Clicked");
		
		String NewitemName = "//input[@id='MainContent_dgclientList_txtListItemName_" + (size - 1) + "']";
		String NewitemDesc = "//input[@id='MainContent_dgclientList_txtlistItemDesc_" + (size - 1) + "']";
		
		Wait.WaitForElement(driver, NewitemName);
		Wait.waitForElementToBeVisibile(By.xpath(NewitemName), driver);
		Wait.waitForElementToBeClickable(By.xpath(NewitemName), driver);
		WebElement Newitem_Name = driver.findElement(By.xpath(NewitemName));
		WebElement Newitem_Description = driver.findElement(By.xpath(NewitemDesc));

		Utility.ScrollToElement_NoWait(Newitem_Name, driver);
		Newitem_Name.sendKeys(Name);
		assertTrue(Newitem_Name.getAttribute("value").equals(Name), " Name didn't add");
		accountsLogger.info("New Item Name Enterted: " + Name + " And Verfied");
		test_steps.add("New Item Name Enterted: " + Name + " And Verfied");
		Newitem_Description.sendKeys(Description);
		assertTrue(Newitem_Description.getAttribute("value").equals(Description), " Description didn't add");
		accountsLogger.info("New Item Description Enterted: " + Description + " And Verfied");
		test_steps.add("New Item Name Description: " + Description + " And Verfied");
	}

	
	public void SelectFilter(WebDriver driver, String FilterName, ArrayList<String> test_steps)
			throws InterruptedException {
		// TODO Auto-generated method stub

		Elements_ListManagement listManagement = new Elements_ListManagement(driver);

		for (int i = 0; i < listManagement.FilterElementList.size(); i++) {

			if (listManagement.FilterElementList.get(i).getText().equals(FilterName)) {
				listManagement.FilterElementList.get(i).click();
				assertTrue(listManagement.SystemItemTextField.getText().equals("System Items"),
						" Filter didn't select");
				accountsLogger.info("Filter Selected: " + FilterName);
				test_steps.add("Filter Selected: " + FilterName);

				break;
			}

		}

	}

	public void SaveButtonClick(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_ListManagement listManagement = new Elements_ListManagement(driver);
		int CustomListSizeBefore = listManagement.CustomItemListSize.size();
		listManagement.SaveButton.click();
		Wait.wait2Second();
		int CustomListSizeAfter = listManagement.CustomItemListSize.size();
		System.out.println("Before:" + CustomListSizeBefore + " After:" + CustomListSizeAfter);
		// assertTrue(CustomListSizeAfter > CustomListSizeBefore, "New Item
		// didn't add");
		accountsLogger.info("Save Button Clicked");
		test_steps.add("Save Button Clicked");

	}

	public void SelectStatus(WebDriver driver, String Status, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_ListManagement listManagement = new Elements_ListManagement(driver);
		new Select(listManagement.SelectStatus).selectByVisibleText(Status);
		Wait.WaitForElement(driver, NewListManagement.RatePlanInActiveCustomListCheckbox);
		assertTrue(listManagement.RatePlanInActiveCustomListCheckbox.isDisplayed(),
				" RatePlan InActive Custom List didn't Display");
		accountsLogger.info("ListManagement Status Selected: " + Status);
		test_steps.add("ListManagement Status Selected: " + Status);
	}

	public void DeleteCustomInActiveRatePlan(WebDriver driver, String Name, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_ListManagement listManagement = new Elements_ListManagement(driver);
		int tablesizebefore = listManagement.CustomItemListSize.size();
		WebElement RatePlanCheckbox = driver
				.findElement(By.xpath("//table[@id='MainContent_dgclientList']//span[text()='" + Name
						+ "']//parent::td/preceding-sibling::td//input"));
		Utility.ScrollToElement(RatePlanCheckbox, driver);
		RatePlanCheckbox.click();
		accountsLogger.info("In List Management " + Name + " Checkbox Selected");
		test_steps.add("In List Management " + Name + " Checkbox Selected");
		listManagement.DeleteButton.click();
		accountsLogger.info("Delete Button Clicked");
		test_steps.add("Delete Button Clicked");

		Wait.wait2Second();
		int tablesizeafter = listManagement.CustomItemListSize.size();
		System.out.print("Befoee:" + tablesizebefore + " After:" + tablesizeafter);
		assertEquals(tablesizebefore > tablesizeafter, true, " Didn't delete all items");
		accountsLogger
				.info("In List Management Table Size Before Delete: " + tablesizebefore + " After :" + tablesizeafter);
		test_steps.add("In List Management Table Size Before Delete: " + tablesizebefore + " After :" + tablesizeafter);

	}

	public void ClickRatePlan(WebDriver driver) {
		Elements_ListManagement listManagement = new Elements_ListManagement(driver);
		Wait.explicit_wait_elementToBeClickable(listManagement.clickRatePlanLink, driver);
		listManagement.clickRatePlanLink.click();
		}
	
}
