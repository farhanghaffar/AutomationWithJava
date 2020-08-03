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

import com.innroad.inncenter.interfaces.IRule;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Inventory;
import com.innroad.inncenter.webelements.Elements_NewRoomClass;

import bsh.util.Util;

public class Rules implements IRule {

	public static Logger rulesLogger = Logger.getLogger("Rules");

	public void create_Rule(WebDriver driver, String RuleName, String RuleType, String RuleDescription)
			throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_Inventory newRule = new Elements_Inventory(driver);

		newRule.click_Inventory.click();
		newRule.click_Rules.click();
		Thread.sleep(5000);
		newRule.Click_newRule_Btn.click();
		newRule.Enter_ruleName.sendKeys(RuleName);
		new Select(newRule.Select_ruleType).selectByVisibleText(RuleType);
		newRule.Enter_ruleDescription.sendKeys(RuleDescription);
		Thread.sleep(3000);
		newRule.click_effectiveOnAsMonday.click();
		Thread.sleep(3000);
		newRule.click_effectiveOnAsTuesday.click();
		Thread.sleep(3000);
		newRule.click_associateSeasons.click();
		Thread.sleep(3000);
		newRule.click_associateSeasons_assignAll.click();
		newRule.click_associateSeasons_doneButton.click();
		Thread.sleep(8000);

		// Page scroll down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		newRule.click_associateRoomclass.click();
		Thread.sleep(4000);

		/*
		 * int count_unassigned=driver.findElements(By.xpath(OR.
		 * get_unassignedSeasons_list)).size();
		 * rulesLogger.info("Unassigned Seasons" +count_unassigned);
		 */

		newRule.click_associateRoomclass_assignAll.click();

		/*
		 * int count_assigned=driver.findElements(By.xpath(OR.
		 * get_assignedSeasons_list)).size(); rulesLogger.info(count_assigned);
		 */

		newRule.click_associateRoomclass_doneButton.click();
		Thread.sleep(8000);
		newRule.click_associateSources.click();
		Thread.sleep(5000);
		newRule.click_associateSources_assignAll.click();
		newRule.click_associateSources_doneButton.click();
		Thread.sleep(8000);

		// Page scroll down
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		newRule.click_associateRatePlans.click();
		Thread.sleep(5000);
		newRule.click_associateRatePlans_assignAll.click();
		newRule.click_associateRatePlans_doneButton.click();
		Thread.sleep(8000);

		// Save the Rule
		newRule.Click_saveButton.click();
		Thread.sleep(3000);

		// Rule created success message
		String ruleCreation_message = newRule.Message_newRuleCreated.getText();
		rulesLogger.info(" New Rule created Successfully " + ruleCreation_message);

		Wait.wait15Second();
		// Scroll up the page
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newRule.Click_closeTab);

		// Close the created rule
		newRule.Click_closeTab.click();
		Wait.wait10Second();
		rulesLogger.info(" Closed the tab");

		// Search the Rule
		newRule.Click_searchButton.click();
		Wait.wait10Second();
	}

	public void search_Rule(WebDriver driver) throws InterruptedException {

		Elements_Inventory newRule = new Elements_Inventory(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.click_Rules, driver);
		newRule.click_Rules.click();
		Wait.waitUntilPresenceOfElementLocated(OR.Click_searchButton, driver);
		newRule.Click_searchButton.click();
		// Wait.wait2Second();
	}

	// Delete the Rule
	public void delete_Rule(WebDriver driver, String ruleName) throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_Inventory deleteRule = new Elements_Inventory(driver);
		deleteRule.Click_searchButton.click();
		Wait.wait3Second();
		int size = driver.findElements(By.xpath(OR.Select_Rules)).size();
		if (size > 1) {
			List<WebElement> ele = driver.findElements(By.xpath(OR.Select_Rules));
			for (int i = 0; i < ele.size(); i++) {
				Utility.ScrollToElement(ele.get(i), driver);
				ele.get(i).click();
				Wait.wait1Second();
			}
		} else {
			driver.findElement(By.xpath(OR.Select_Rules)).click();
			Wait.wait1Second();
		}
		Wait.wait2Second();
		deleteRule.rule_clickDeleteButton.click();
		try {
			Utility.ElementFinderUntillNotShow(By.className(OR.Toaster_Message), driver);
			String Message = driver.findElement(By.className(OR.Toaster_Message)).getText();
			assertEquals(Message, "Rules Deleted Successfully", "Rule does not delete");
		} catch (Exception e) {
		}

	}

	public void create_Rule(WebDriver driver, String RuleName, String RuleType, String RuleDescription, String Value,
			String RoomClass, String Source, String RatePlan, String Season) throws InterruptedException {
		Elements_Inventory newRule = new Elements_Inventory(driver);
		// Wait.explicit_wait_visibilityof_webelement(newRule.click_Rules,
		// driver);
		// newRule.click_Rules.click();
		Wait.explicit_wait_visibilityof_webelement(newRule.Click_newRule_Btn, driver);
		Utility.ScrollToElement(newRule.Click_newRule_Btn, driver);
		newRule.Click_newRule_Btn.click();

		Wait.explicit_wait_visibilityof_webelement(newRule.Enter_ruleName, driver);
		newRule.Enter_ruleName.sendKeys(RuleName);
		new Select(newRule.Select_ruleType).selectByVisibleText(RuleType);
		if (newRule.Enter_MiniStay.isEnabled()) {
			newRule.Enter_MiniStay.sendKeys(Value);
		}

		newRule.Enter_ruleDescription.sendKeys(RuleDescription);

		Wait.explicit_wait_visibilityof_webelement(newRule.click_associateSeasons, driver);
		Utility.ScrollToElement(newRule.click_associateSeasons, driver);
		newRule.click_associateSeasons.click();
		Wait.explicit_wait_visibilityof_webelement_350(newRule.SelectSeason, driver);
		new Select(newRule.SelectSeason).selectByVisibleText(Season);
		newRule.AddSeason_Button.click();
		newRule.click_associateSeasons_doneButton.click();
		Wait.wait2Second();

		// Page scroll down
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		jse.executeScript("arguments[0].scrollIntoView();", newRule.click_associateRoomclass);

		newRule.click_associateRoomclass.click();
		Wait.waitUntilPresenceOfElementLocated(OR.click_associateRoomclass_doneButton, driver);
		new Select(newRule.PickerAssociate.get(0)).selectByVisibleText(RoomClass);
		newRule.Rule_AddButton.click();

		newRule.click_associateRoomclass_doneButton.click();
		Wait.wait2Second();

		newRule.click_associateSources.click();
		Wait.waitUntilPresenceOfElementLocated(OR.click_associateSources_doneButton, driver);
		new Select(newRule.PickerAssociate.get(0)).selectByVisibleText(Source);
		newRule.Rule_AddButton.click();
		newRule.click_associateSources_doneButton.click();
		Wait.wait2Second();

		// Page scroll down
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		newRule.click_associateRatePlans.click();
		Wait.waitUntilPresenceOfElementLocated(OR.click_associateRatePlans_doneButton, driver);
		new Select(newRule.PickerAssociate.get(0)).selectByVisibleText(RatePlan);
		newRule.Rule_AddButton.click();
		newRule.click_associateRatePlans_doneButton.click();
		Wait.wait2Second();

		// Save the Rule
		newRule.Click_saveButton.click();
		// Thread.sleep(3000);

		// Rule created success message
		Wait.waitUntilPresenceOfElementLocated(OR.Message_newRuleCreated, driver);
		String ruleCreation_message = newRule.Message_newRuleCreated.getText();
		assertEquals(ruleCreation_message, "Successfully Created Rule : " + RuleName, "fail to create new rule");
		rulesLogger.info(" New Rule created Successfully " + ruleCreation_message);

		// Wait.wait5Second();
		// Scroll up the page
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newRule.Click_closeTab);

		// Close the created rule
		newRule.Click_closeTab.click();
		// Wait.wait10Second();
		rulesLogger.info(" Closed the tab");

		// Search the Rule
		// newRule.Click_searchButton.click();
		// Wait.wait10Second();
	}

	public void createRule(WebDriver driver, String RuleName, String RuleType, String RuleDescription, String Value,
			String Season, String RoomClass, String Source, String RatePlan) throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_Inventory newRule = new Elements_Inventory(driver);
		newRule.click_Inventory.click();
		Wait.explicit_wait_visibilityof_webelement(newRule.click_Rules, driver);
		newRule.click_Rules.click();
		Wait.explicit_wait_visibilityof_webelement(newRule.Click_newRule_Btn, driver);
		Utility.ScrollToElement(newRule.Click_newRule_Btn, driver);
		newRule.Click_newRule_Btn.click();

		Wait.explicit_wait_visibilityof_webelement(newRule.Enter_ruleName, driver);
		newRule.Enter_ruleName.sendKeys(RuleName);
		new Select(newRule.Select_ruleType).selectByVisibleText(RuleType);
		newRule.Enter_MiniStay.sendKeys(Value);
		newRule.Enter_ruleDescription.sendKeys(RuleDescription);

		Wait.explicit_wait_visibilityof_webelement(newRule.click_associateSeasons, driver);
		Utility.ScrollToElement(newRule.click_associateSeasons, driver);
		newRule.click_associateSeasons.click();
		Wait.explicit_wait_visibilityof_webelement(newRule.click_associateSeasons_assignAll, driver);
		newRule.click_associateSeasons_assignAll.click();
		newRule.click_associateSeasons_doneButton.click();
		Wait.wait2Second();

		// Page scroll down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		// js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		js1.executeScript("arguments[0].click();", newRule.click_associateRoomclass);

		// newRule.click_associateRoomclass.click();
		Wait.waitUntilPresenceOfElementLocated(OR.click_associateRoomclass_doneButton, driver);
		new Select(newRule.PickerAssociate.get(0)).selectByVisibleText(RoomClass);
		newRule.Rule_AddButton.click();

		newRule.click_associateRoomclass_doneButton.click();
		Wait.wait2Second();

		newRule.click_associateSources.click();
		Wait.waitUntilPresenceOfElementLocated(OR.click_associateSources_doneButton, driver);
		new Select(newRule.PickerAssociate.get(0)).selectByVisibleText(Source);
		newRule.Rule_AddButton.click();
		newRule.click_associateSources_doneButton.click();
		Wait.wait2Second();

		// Page scroll down
		js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		newRule.click_associateRatePlans.click();
		Wait.waitUntilPresenceOfElementLocated(OR.click_associateRatePlans_doneButton, driver);
		new Select(newRule.PickerAssociate.get(0)).selectByVisibleText(RatePlan);
		newRule.Rule_AddButton.click();
		newRule.click_associateRatePlans_doneButton.click();
		Wait.wait2Second();

		// Save the Rule
		newRule.Click_saveButton.click();
		// Thread.sleep(3000);

		// Rule created success message
		Wait.waitUntilPresenceOfElementLocated(OR.Message_newRuleCreated, driver);
		String ruleCreation_message = newRule.Message_newRuleCreated.getText();
		assertEquals(ruleCreation_message, "Successfully Created Rule : " + RuleName, "fail to create new rule");
		rulesLogger.info(" New Rule created Successfully " + ruleCreation_message);

		// Wait.wait5Second();
		// Scroll up the page
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newRule.Click_closeTab);

		// Close the created rule
		newRule.Click_closeTab.click();
		// Wait.wait10Second();
		rulesLogger.info(" Closed the tab");

		// Search the Rule
		// newRule.Click_searchButton.click();
		// Wait.wait10Second();
	}

	public void createRule1(WebDriver driver, String RuleName, String RuleType, String RuleDescription, String Value,
			String Season, String RoomClass, String Source, String RatePlan) throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_Inventory newRule = new Elements_Inventory(driver);

		Utility.ScrollToElement(newRule.Click_newRule_Btn, driver);
		newRule.Click_newRule_Btn.click();

		Wait.explicit_wait_visibilityof_webelement(newRule.Enter_ruleName, driver);
		newRule.Enter_ruleName.sendKeys(RuleName);
		new Select(newRule.Select_ruleType).selectByVisibleText(RuleType);
		newRule.Enter_MiniStay.sendKeys(Value);
		newRule.Enter_ruleDescription.sendKeys(RuleDescription);

		Wait.explicit_wait_visibilityof_webelement(newRule.click_associateSeasons, driver);
		Utility.ScrollToElement(newRule.click_associateSeasons, driver);
		newRule.click_associateSeasons.click();
		Wait.explicit_wait_visibilityof_webelement(newRule.click_associateSeasons_assignAll, driver);
		newRule.click_associateSeasons_assignAll.click();
		newRule.click_associateSeasons_doneButton.click();
		Wait.wait2Second();

		// Page scroll down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		// js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		js1.executeScript("arguments[0].click();", newRule.click_associateRoomclass);

		// newRule.click_associateRoomclass.click();
		Wait.waitUntilPresenceOfElementLocated(OR.click_associateRoomclass_doneButton, driver);
		new Select(newRule.PickerAssociate.get(0)).selectByVisibleText(RoomClass);
		newRule.Rule_AddButton.click();

		newRule.click_associateRoomclass_doneButton.click();
		Wait.wait2Second();

		newRule.click_associateSources.click();
		Wait.waitUntilPresenceOfElementLocated(OR.click_associateSources_doneButton, driver);
		new Select(newRule.PickerAssociate.get(0)).selectByVisibleText(Source);
		newRule.Rule_AddButton.click();
		newRule.click_associateSources_doneButton.click();
		Wait.wait2Second();

		// Page scroll down
		js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		newRule.click_associateRatePlans.click();
		Wait.waitUntilPresenceOfElementLocated(OR.click_associateRatePlans_doneButton, driver);
		new Select(newRule.PickerAssociate.get(0)).selectByVisibleText(RatePlan);
		newRule.Rule_AddButton.click();
		newRule.click_associateRatePlans_doneButton.click();
		Wait.wait2Second();

		// Save the Rule
		newRule.Click_saveButton.click();
		// Thread.sleep(3000);

		// Rule created success message
		Wait.waitUntilPresenceOfElementLocated(OR.Message_newRuleCreated, driver);
		String ruleCreation_message = newRule.Message_newRuleCreated.getText();
		assertEquals(ruleCreation_message, "Successfully Created Rule : " + RuleName, "fail to create new rule");
		rulesLogger.info(" New Rule created Successfully " + ruleCreation_message);

		// Wait.wait5Second();
		// Scroll up the page
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newRule.Click_closeTab);

		// Close the created rule
		newRule.Click_closeTab.click();
		// Wait.wait10Second();
		rulesLogger.info(" Closed the tab");

		// Search the Rule
		// newRule.Click_searchButton.click();
		// Wait.wait10Second();
	}

	public void deleteALLRule(WebDriver driver) throws InterruptedException {

		Elements_Inventory deleteRule = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(deleteRule.click_Rules, driver);
		deleteRule.click_Rules.click();
		deleteRule.Click_searchButton.click();
		Wait.wait1Second();

		if (driver.findElement(By.xpath("//*[@id='bpjscontainer_21']//div[contains(text(),'Rules List')]"))
				.isDisplayed()) {
			int size = driver.findElements(By.xpath(OR.Select_Rules)).size();
			if (size > 1) {
				List<WebElement> ele = driver.findElements(By.xpath(OR.Select_Rules));
				for (int i = 0; i < ele.size(); i++) {
					Utility.ScrollToElement(ele.get(i), driver);
					ele.get(i).click();
					Wait.wait1Second();
				}

			} else if (size == 1) {
				driver.findElement(By.xpath(OR.Select_Rules)).click();
				Wait.wait1Second();
			}
			deleteRule.rule_clickDeleteButton.click();
			try {
				Utility.ElementFinderUntillNotShow(By.className(OR.Toaster_Message), driver);
				String Message = driver.findElement(By.className(OR.Toaster_Message)).getText();
				rulesLogger.info(Message);
				assertEquals(Message, "Rules Deleted Successfully", "Rule does not delete");
			} catch (Exception ex) {
				rulesLogger.info("toaster not displayed");
			}
		}

		else {
			try {
				WebElement toaster = driver.findElement(By.className(OR.Toaster_Message));
				if (toaster.isDisplayed()) {
					if (toaster.getText().equalsIgnoreCase("Please try again changing the search criteria")) {
						rulesLogger.info("no existing rule found");
					}
				}
			} catch (Exception e) {
				rulesLogger.info("toaster not displayed");
			}

		}

	}

	public ArrayList<String> verifyRuleStatusSeason(WebDriver driver, String Season) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Inventory newRule = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(newRule.Click_newRule_Btn, driver);
		Utility.ScrollToElement(newRule.Click_newRule_Btn, driver);
		newRule.Click_newRule_Btn.click();
		rulesLogger.info("New Rule Button Clicked");
		test_steps.add("New Rule Button Clicked");
		Wait.explicit_wait_visibilityof_webelement(newRule.click_associateSeasons, driver);
		Utility.ScrollToElement(newRule.click_associateSeasons, driver);
		newRule.click_associateSeasons.click();
		rulesLogger.info("Associate Seasons Button Clicked");
		test_steps.add("Associate Seasons Button Clicked");
		List<WebElement> ele = new Select(newRule.SelectSeason).getOptions();

		System.out.println(ele.size());

		for (int i = 0; i < ele.size(); i++) {
			System.out.println(ele.get(i).getText());
			assertTrue(!ele.get(i).getText().equals(Season), "Faild: InActive Season Found");
		}

		newRule.click_associateSeasons_doneButton.click();

		return test_steps;
	}

	public void closeTab(WebDriver driver) throws InterruptedException {
		Elements_Inventory newRule = new Elements_Inventory(driver);
		// Scroll up the page
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newRule.Click_closeTab);
		// Close the created rule
		Wait.wait2Second();
		newRule.Click_closeTab.click();
		Wait.wait2Second();
	}

	public void create_Rule(WebDriver driver, String RuleName, String RuleType, String RuleDescription, String Value,
			String RoomClass, String Source, String RatePlan, String Season, String Status)
			throws InterruptedException {
		Elements_Inventory newRule = new Elements_Inventory(driver);
		// Wait.explicit_wait_visibilityof_webelement(newRule.click_Rules,
		// driver);
		// newRule.click_Rules.click();
		Wait.explicit_wait_visibilityof_webelement(newRule.Click_newRule_Btn, driver);
		Utility.ScrollToElement(newRule.Click_newRule_Btn, driver);
		newRule.Click_newRule_Btn.click();

		Wait.explicit_wait_visibilityof_webelement(newRule.Enter_ruleName, driver);
		newRule.Enter_ruleName.sendKeys(RuleName);
		new Select(newRule.Select_ruleType).selectByVisibleText(RuleType);
		if (newRule.Enter_MiniStay.isEnabled()) {
			newRule.Enter_MiniStay.sendKeys(Value);
		}
		WebElement ele = driver.findElement(By.xpath("//select[contains(@data-bind,'value: Active')]"));
		Select sel = new Select(ele);
		sel.selectByVisibleText(Status);

		newRule.Enter_ruleDescription.sendKeys(RuleDescription);

		Wait.explicit_wait_visibilityof_webelement(newRule.click_associateSeasons, driver);
		Utility.ScrollToElement(newRule.click_associateSeasons, driver);
		newRule.click_associateSeasons.click();
		Wait.explicit_wait_visibilityof_webelement_350(newRule.SelectSeason, driver);
		new Select(newRule.SelectSeason).selectByVisibleText(Season);
		newRule.AddSeason_Button.click();
		newRule.click_associateSeasons_doneButton.click();
		Wait.wait2Second();

		// Page scroll down
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		jse.executeScript("arguments[0].scrollIntoView();", newRule.click_associateRoomclass);

		newRule.click_associateRoomclass.click();
		Wait.waitUntilPresenceOfElementLocated(OR.click_associateRoomclass_doneButton, driver);
		new Select(newRule.PickerAssociate.get(0)).selectByVisibleText(RoomClass);
		newRule.Rule_AddButton.click();

		newRule.click_associateRoomclass_doneButton.click();
		Wait.wait2Second();

		newRule.click_associateSources.click();
		Wait.waitUntilPresenceOfElementLocated(OR.click_associateSources_doneButton, driver);
		new Select(newRule.PickerAssociate.get(0)).selectByVisibleText(Source);
		newRule.Rule_AddButton.click();
		newRule.click_associateSources_doneButton.click();
		Wait.wait2Second();

		// Page scroll down
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		newRule.click_associateRatePlans.click();
		Wait.waitUntilPresenceOfElementLocated(OR.click_associateRatePlans_doneButton, driver);
		new Select(newRule.PickerAssociate.get(0)).selectByVisibleText(RatePlan);
		newRule.Rule_AddButton.click();
		newRule.click_associateRatePlans_doneButton.click();
		Wait.wait2Second();

		// Save the Rule
		newRule.Click_saveButton.click();
		// Thread.sleep(3000);

		// Rule created success message
		Wait.waitUntilPresenceOfElementLocated(OR.Message_newRuleCreated, driver);
		String ruleCreation_message = newRule.Message_newRuleCreated.getText();
		assertEquals(ruleCreation_message, "Successfully Created Rule : " + RuleName, "fail to create new rule");
		rulesLogger.info(" New Rule created Successfully " + ruleCreation_message);

		// Wait.wait5Second();
		// Scroll up the page
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newRule.Click_closeTab);

		// Close the created rule
		newRule.Click_closeTab.click();
		// Wait.wait10Second();
		rulesLogger.info(" Closed the tab");

		// Search the Rule
		// newRule.Click_searchButton.click();
		// Wait.wait10Second();
	}

	public ArrayList<String> searchWithStatus(WebDriver driver, String Status) {

		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Inventory newRule = new Elements_Inventory(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.click_Rules, driver);
		newRule.click_Rules.click();
		rulesLogger.info("Successfully Navigate to Rules");
		test_steps.add("Successfully Navigate to Rules");
		WebElement ele = driver.findElement(By.xpath("//select[contains(@data-bind,'value: selectedStatus')]"));
		Wait.explicit_wait_visibilityof_webelement(ele, driver);
		Select sel = new Select(ele);
		sel.selectByVisibleText(Status);
		rulesLogger.info("Rules Search Stauts : " + Status);
		test_steps.add("Rules Search Stauts : " + Status);
		Wait.waitUntilPresenceOfElementLocated(OR.Click_searchButton, driver);
		newRule.Click_searchButton.click();
		rulesLogger.info("Search Button Clicked");
		test_steps.add("Search Button Clicked");

		return test_steps;
	}

	public ArrayList<String> create_Rule(WebDriver driver, String RuleName, String RuleType, String RuleDescription,
			String Value, String RoomClass, String Source, String RatePlan, String Season, ArrayList<String> test_steps,
			ArrayList<String> EffectiveDaysList) throws InterruptedException {

		Elements_Inventory newRule = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(newRule.click_Rules, driver);
		newRule.click_Rules.click();
		test_steps.add("Clicked on Rules");
		Wait.explicit_wait_visibilityof_webelement(newRule.Click_newRule_Btn, driver);
		Utility.ScrollToElement(newRule.Click_newRule_Btn, driver);
		newRule.Click_newRule_Btn.click();
		test_steps.add("Clicked on New Rule Button");

		Wait.explicit_wait_visibilityof_webelement(newRule.Enter_ruleName, driver);
		newRule.Enter_ruleName.sendKeys(RuleName);

		new Select(newRule.Select_ruleType).selectByVisibleText(RuleType);
		test_steps.add("Selected RuleType : " + RuleType);
		if (newRule.Enter_MiniStay.isEnabled()) {
			newRule.Enter_MiniStay.sendKeys(Value);
		}

		newRule.Enter_ruleDescription.sendKeys(RuleDescription);

		Wait.explicit_wait_visibilityof_webelement(newRule.click_associateSeasons, driver);
		Utility.ScrollToElement(newRule.click_associateSeasons, driver);
		newRule.click_associateSeasons.click();
		test_steps.add("Clicked on Associate Seasons");
		Wait.explicit_wait_visibilityof_webelement_350(newRule.SelectSeason, driver);
		new Select(newRule.SelectSeason).selectByVisibleText(Season);
		test_steps.add("Selected Associate Seasons : " + Season);
		newRule.AddSeason_Button.click();
		newRule.click_associateSeasons_doneButton.click();
		Wait.wait2Second();

		// Page scroll down
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		jse.executeScript("arguments[0].scrollIntoView();", newRule.click_associateRoomclass);
		test_steps.add("Clicked on Associate RoomClass");
		newRule.click_associateRoomclass.click();
		Wait.waitUntilPresenceOfElementLocated(OR.click_associateRoomclass_doneButton, driver);

		new Select(newRule.PickerAssociate.get(0)).selectByVisibleText(RoomClass);
		test_steps.add("Selected Associate RoomClass : " + RoomClass);
		Wait.explicit_wait_visibilityof_webelement(newRule.Rule_AddButton, driver);
		newRule.Rule_AddButton.click();
		Wait.explicit_wait_visibilityof_webelement(newRule.click_associateRoomclass_doneButton, driver);
		newRule.click_associateRoomclass_doneButton.click();
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(newRule.click_associateSources, driver);
		newRule.click_associateSources.click();
		test_steps.add("Clicked on Associate Sources");

		Wait.waitUntilPresenceOfElementLocated(OR.click_associateSources_doneButton, driver);
		new Select(newRule.PickerAssociate.get(0)).selectByVisibleText(Source);
		test_steps.add("Selected Associate Sources : " + Source);
		newRule.Rule_AddButton.click();
		newRule.click_associateSources_doneButton.click();
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(newRule.click_associateRatePlans, driver);
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		newRule.click_associateRatePlans.click();
		test_steps.add("Clicked on Associate RatePlan");
		Wait.waitUntilPresenceOfElementLocated(OR.click_associateRatePlans_doneButton, driver);
		new Select(newRule.PickerAssociate.get(0)).selectByVisibleText(RatePlan);
		test_steps.add("Selected Associate RatePlan : " + RatePlan);
		newRule.Rule_AddButton.click();
		newRule.click_associateRatePlans_doneButton.click();
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(newRule.click_effectiveOnAsThursday, driver);
		if (newRule.click_effectiveOnAsThursday.isEnabled()) {
			newRule.click_effectiveOnAsThursday.click();
			EffectiveDaysList.add("Thu");
			test_steps.add("Rule Attributes are Not EFFECTIVE ON Thursday");
			rulesLogger.info("Rule Attributes are Not EFFECTIVE ON Thursday");
		}

		Wait.explicit_wait_visibilityof_webelement(newRule.click_effectiveOnAsFriday, driver);
		if (newRule.click_effectiveOnAsFriday.isEnabled()) {
			newRule.click_effectiveOnAsFriday.click();
			test_steps.add("Rule Attributes are Not EFFECTIVE ON Friday");
			EffectiveDaysList.add("Fri");
			rulesLogger.info("Rule Attributes are Not EFFECTIVE ON Friday");
		}

		Wait.explicit_wait_visibilityof_webelement(newRule.click_effectiveOnAsSaturday, driver);
		if (newRule.click_effectiveOnAsSaturday.isEnabled()) {
			newRule.click_effectiveOnAsSaturday.click();
			test_steps.add("Rule Attributes are Not EFFECTIVE ON Saturday");
			EffectiveDaysList.add("Sat");
			rulesLogger.info("Rule Attributes are Not EFFECTIVE ON Saturday");
		}

		Wait.explicit_wait_visibilityof_webelement(newRule.click_effectiveOnAsSunday, driver);
		if (newRule.click_effectiveOnAsSunday.isEnabled()) {
			newRule.click_effectiveOnAsSunday.click();
			EffectiveDaysList.add("Sun");
			test_steps.add("Rule Attributes are Not EFFECTIVE ON Sunday");
			rulesLogger.info("Rule Attributes are Not EFFECTIVE ON Sunday");
		}

		// Save the Rule
		Wait.explicit_wait_visibilityof_webelement(newRule.Click_saveButton, driver);
		newRule.Click_saveButton.click();
		test_steps.add("Clicked on Save ");

		// Rule created success message
		Wait.waitUntilPresenceOfElementLocated(OR.Message_newRuleCreated, driver);
		String ruleCreation_message = newRule.Message_newRuleCreated.getText();
		assertEquals(ruleCreation_message, "Successfully Created Rule : " + RuleName, "fail to create new rule");
		test_steps.add(" Message : " + ruleCreation_message);
		rulesLogger.info(" Message : " + ruleCreation_message);

		rulesLogger.info(" Succesfully Created New Rule with the Name of " + RuleName);
		test_steps.add(" Succesfully Created New Rule with the Name of " + RuleName);

		// Scroll up the page
		Wait.explicit_wait_visibilityof_webelement(newRule.Click_closeTab, driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newRule.Click_closeTab);

		// Close the created rule
		newRule.Click_closeTab.click();
		test_steps.add(" Closed the Created Rule tab");
		rulesLogger.info(" Closed the tab");
		return EffectiveDaysList;
	}

	public boolean checkingTodayWeek(WebDriver driver, ArrayList<String> test_steps, String onlyTodayWeek,
			ArrayList<String> EffectiveDaysList) {

		boolean ruleAppilicable = false;
		if (EffectiveDaysList.contains(onlyTodayWeek)) {
			rulesLogger.info("Created New Rule is Not Applicable for the " + onlyTodayWeek + " Day");
			test_steps.add("Created New Rule is Not Applicable for the " + onlyTodayWeek + " Day");
			rulesLogger.info("So Rules Broken PopUp Should be Not Available in all Searches for the Today Arrival");
			test_steps.add("So Rules Broken PopUp Should be Not Available in all Searches for the Today Arrival");
		} else {
			ruleAppilicable = true;
			rulesLogger.info("Created New Rule is Applicable for the " + onlyTodayWeek + " Day");
			test_steps.add("Created New Rule is Applicable for the " + onlyTodayWeek + " Day");
			rulesLogger.info("So Rules Broken PopUp Should be Available in all Searches for the Today Arrival");
			test_steps.add("So Rules Broken PopUp Should be Available in all Searches for the Today Arrival");
		}
		return ruleAppilicable;
	}

	// Delete the Rule
	public void delete_Rule_2(WebDriver driver, String ruleName, ArrayList<String> test_steps, boolean ruleApplicable)
			throws InterruptedException {

		Elements_Inventory deleteRule = new Elements_Inventory(driver);
		Wait.explicit_wait_xpath(OR.Click_searchButton, driver);
		deleteRule.Click_searchButton.click();
		test_steps.add("Clicked On Search");
		Wait.explicit_wait_xpath(OR.Select_Rules, driver);

		int size = driver.findElements(By.xpath(OR.Select_Rules)).size();
		if (size != 0) {
			if (size > 1) {
				List<WebElement> ele = driver.findElements(By.xpath(OR.Select_Rules));
				for (int i = 0; i < ele.size(); i++) {
					Utility.ScrollToElement(ele.get(i), driver);
					ele.get(i).click();
					test_steps.add("Selected Checkbox on Related Rule ");
					rulesLogger.info("Selected Checkbox on Related Rule " + ele.get(i).getText());
				}

			} else {
				driver.findElement(By.xpath(OR.Select_Rules)).click();
			}
			Wait.explicit_wait_xpath(OR.rule_clickDeleteButton, driver);
			deleteRule.rule_clickDeleteButton.click();
			test_steps.add("Clicked on Delete Button");

			// test_steps.add("Waiting for Toaster Message");
			// rulesLogger.info("Waiting for Toaster Message");
			try {
				Utility.ElementFinderUntillNotShow(By.xpath(OR.Toaster_Message), driver);
				String Message = driver.findElement(By.xpath(OR.Toaster_Message)).getText();
				assertEquals(Message, "Rules Deleted Successfully", "Rule does not delete");
				test_steps.add("Toaster Message : " + Message);
			} catch (Exception e) {
				System.out.println("Toaster not avilable");
			}

			test_steps.add(ruleName + " rule is Deleted ");
			rulesLogger.info(ruleName + " rule is Deleted ");
			ruleApplicable = false;
		} else {
			rulesLogger.info(" No Rules are available in Rule-Search to Delete");
			test_steps.add(" No Rules are available in Rule-Search to Delete");
		}
	}

	public void ClickSearch(WebDriver driver) throws InterruptedException {

		Elements_Inventory newRule = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(newRule.Click_searchButton, driver);
		Wait.explicit_wait_elementToBeClickable(newRule.Click_searchButton, driver);
		Utility.ScrollToElement(newRule.Click_searchButton, driver);
		newRule.Click_searchButton.click();
		// Wait.wait2Second();
	}

	public void SelectItems_PerPage(WebDriver driver, String Items) throws InterruptedException {
		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);
		Wait.explicit_wait_visibilityof_webelement(roomClass.SelectedItems_PerPage, driver);
		Utility.ScrollToElement(roomClass.SelectedItems_PerPage, driver);
		Select select = new Select(roomClass.SelectedItems_PerPage);
		select.selectByVisibleText(Items);
		Wait.wait2Second();
		System.out.println("Text:" + select.getFirstSelectedOption().getText());
		System.out.println("Value:" + select.getFirstSelectedOption().getAttribute("value"));

		assertEquals(select.getFirstSelectedOption().getText(), Items,
				"Failed: Room Class 100Items per Page Not selected");
	}

	public boolean SearchRule(WebDriver driver, String Rulename) throws InterruptedException {
		boolean IsRulesShowing = false;
		try {
			Elements_Inventory newRule = new Elements_Inventory(driver);
			char ch = Rulename.charAt(0);
			String str = "" + ch;
			WebElement Search;

			Search = driver
					.findElement(By.xpath("//ul[contains(@class,'pagination')]/li/a[contains(text(),'" + str + "')]"));
			Search.click();
			try {
				Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.className(OR.Toaster_Message)),
						driver);
				System.out.println("message: " + driver.findElement(By.className(OR.Toaster_Message)).getText());
				System.out.println("title: " + driver.findElement(By.className(OR.Toaster_Title)).getText());

			} catch (Exception e) {
				SelectItems_PerPage(driver, "100");
				Utility.app_logs.info("Select 100 items per pages");
				Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.Select_Rules)), driver);
				String xpath = "//a[contains(@data-bind,'RuleName') and text()='" + Rulename + "']";
				int size = driver.findElements(By.xpath(xpath)).size();
				if (size > 0) {
					IsRulesShowing = true;
				}
			}

		} catch (Exception e) {

		}
		return IsRulesShowing;
	}

	public void SelectCheckBox(WebDriver driver, String Rulename) throws InterruptedException {

		String xpath = "//a[contains(@data-bind,'RuleName') and text()='" + Rulename
				+ "']//parent::td/following-sibling::td[contains(@data-bind,'checkAccess')]/input";
		WebElement CheckBox = driver.findElement(By.xpath(xpath));
		Wait.explicit_wait_visibilityof_webelement_120(CheckBox, driver);
		Wait.explicit_wait_elementToBeClickable(CheckBox, driver);
		Utility.ScrollToElement(CheckBox, driver);
		CheckBox.click();
		assertTrue(CheckBox.isSelected(), "Failed: CheckBox is not selected");
	}

	public void ClickDeleteButton(WebDriver driver) throws InterruptedException {

		Elements_Inventory deleteRule = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement_120(deleteRule.rule_clickDeleteButton, driver);
		Wait.explicit_wait_elementToBeClickable(deleteRule.rule_clickDeleteButton, driver);
		Utility.ScrollToElement(deleteRule.rule_clickDeleteButton, driver);
		deleteRule.rule_clickDeleteButton.click();
		try {
			Utility.ElementFinderUntillNotShow(By.className(OR.Toaster_Message), driver);
			String Message = driver.findElement(By.className(OR.Toaster_Message)).getText();
			assertEquals(Message, "Rules Deleted Successfully", "Rule does not delete");
		} catch (Exception e) {
		}
	}

	// Created By adnan
	public void ClickCreateRule(WebDriver driver, ArrayList<String> steps) throws InterruptedException {
		Elements_Inventory newRule = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(newRule.Click_newRule_Btn, driver);
		Wait.explicit_wait_elementToBeClickable(newRule.Click_newRule_Btn, driver);
		Utility.ScrollToElement(newRule.Click_newRule_Btn, driver);
		newRule.Click_newRule_Btn.click();
		steps.add("Click 'New Rule' button");
	}

	// Created By adnan
	public void EnterRuleName(WebDriver driver, String RuleName, ArrayList<String> steps) throws InterruptedException {
		Elements_Inventory newRule = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(newRule.Enter_ruleName, driver);
		Utility.ScrollToElement(newRule.Enter_ruleName, driver);
		newRule.Enter_ruleName.sendKeys(RuleName);
		steps.add("Enter Rule Name : " + RuleName);
	}

	// Created By adnan
	public void SelectRuleType(WebDriver driver, String RuleType, String Value, ArrayList<String> steps)
			throws InterruptedException {
		Elements_Inventory newRule = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(newRule.Select_ruleType, driver);
		Utility.ScrollToElement(newRule.Select_ruleType, driver);
		new Select(newRule.Select_ruleType).selectByVisibleText(RuleType);
		steps.add("Select Rule Type : " + RuleType);
		if (newRule.Enter_MiniStay.isEnabled()) {
			newRule.Enter_MiniStay.sendKeys(Value);
			steps.add("Enter Rule Value : " + Value);
		}
	}

	// Created By adnan
	public void EnterRuleDescription(WebDriver driver, String RuleDescription, ArrayList<String> steps)
			throws InterruptedException {
		Elements_Inventory newRule = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(newRule.Enter_ruleDescription, driver);
		Utility.ScrollToElement(newRule.Enter_ruleDescription, driver);
		newRule.Enter_ruleDescription.sendKeys(RuleDescription);
		steps.add("Enter Rule Description : " + RuleDescription);

	}

	
	// Created By adnan
		public void SelectSeason(WebDriver driver, String Season, ArrayList<String> steps) throws InterruptedException {
			Elements_Inventory newRule = new Elements_Inventory(driver);
			//Wait.explicit_wait_visibilityof_webelement(newRule.click_associateSeasons, driver);
			
			Wait.WaitForElement(driver, OR.click_associateSeasons);
			Wait.waitForElementToBeVisibile(By.xpath(OR.click_associateSeasons), driver);
			Wait.waitForElementToBeClickable(By.xpath( OR.click_associateSeasons), driver);
			
			Utility.ScrollToElement(newRule.click_associateSeasons, driver);
			newRule.click_associateSeasons.click();
			Wait.explicit_wait_visibilityof_webelement_350(newRule.SelectSeason, driver);
			steps.add("Click 'Associate Season' ");
			new Select(newRule.SelectSeason).selectByVisibleText(Season);
			steps.add("Select Season : " + Season);
			newRule.AddSeason_Button.click();
			steps.add("Click 'Add Season' button");
			newRule.click_associateSeasons_doneButton.click();
			steps.add("Click 'Done' Season Picker");
			Wait.wait2Second();
		}

		// Created By adnan
		public void SelectRoomClass(WebDriver driver, String RoomClass, ArrayList<String> steps)
				throws InterruptedException {
			Elements_Inventory newRule = new Elements_Inventory(driver);
			//Wait.explicit_wait_visibilityof_webelement(newRule.click_associateRoomclass, driver);
			
			Wait.WaitForElement(driver, OR.click_associateRoomclass);
			Wait.waitForElementToBeVisibile(By.xpath(OR.click_associateRoomclass), driver);
			Wait.waitForElementToBeClickable(By.xpath( OR.click_associateRoomclass), driver);
			
			Utility.ScrollToElement(newRule.click_associateRoomclass, driver);
			newRule.click_associateRoomclass.click();
			Wait.waitUntilPresenceOfElementLocated(OR.click_associateRoomclass_doneButton, driver);
			steps.add("Click 'Associate Room Class' ");
			new Select(newRule.PickerAssociate.get(0)).selectByVisibleText(RoomClass);
			steps.add("Select Room Class : " + RoomClass);
			newRule.Rule_AddButton.click();
			steps.add("Click 'Add Room Class' button");
			newRule.click_associateRoomclass_doneButton.click();
			steps.add("Click 'Done' Room Class Picker");
		}

		// Created By adnan
		public void SelectSource(WebDriver driver, String Source, ArrayList<String> steps) throws InterruptedException {
			Elements_Inventory newRule = new Elements_Inventory(driver);
			Wait.WaitForElement(driver,  OR.click_associateSources);
			Wait.waitForElementToBeVisibile(By.xpath( OR.click_associateSources), driver);
			Wait.waitForElementToBeClickable(By.xpath( OR.click_associateSources), driver);
			Wait.explicit_wait_visibilityof_webelement(newRule.click_associateSources, driver);
			Utility.ScrollToElement(newRule.click_associateSources, driver);
			newRule.click_associateSources.click();
			Wait.waitUntilPresenceOfElementLocated(OR.click_associateSources_doneButton, driver);
			steps.add("Click 'Associate Source' ");
			new Select(newRule.PickerAssociate.get(0)).selectByVisibleText(Source);
			steps.add("Select Source : " + Source);
			newRule.Rule_AddButton.click();
			steps.add("Click 'Add Source' button");
			newRule.click_associateSources_doneButton.click();
			steps.add("Click 'Done' Source Picker");

			Wait.wait2Second();
		}

		// Created By adnan
		public void SelectRatePlan(WebDriver driver, String RatePlan, ArrayList<String> steps) throws InterruptedException {
			Elements_Inventory newRule = new Elements_Inventory(driver);
			//Wait.explicit_wait_visibilityof_webelement(newRule.click_associateRatePlans, driver);
			Wait.WaitForElement(driver, OR.click_associateRatePlans);
			Wait.waitForElementToBeVisibile(By.xpath(OR.click_associateRatePlans), driver);
			Wait.waitForElementToBeClickable(By.xpath( OR.click_associateRatePlans), driver);
			Utility.ScrollToElement(newRule.click_associateRatePlans, driver);
			newRule.click_associateRatePlans.click();
			Wait.waitUntilPresenceOfElementLocated(OR.click_associateRatePlans_doneButton, driver);
			steps.add("Click 'Associate Rate Plan' ");
			new Select(newRule.PickerAssociate.get(0)).selectByVisibleText(RatePlan);
			steps.add("Select Rate Plan : " + RatePlan);
			newRule.Rule_AddButton.click();
			steps.add("Click 'Add Rate Plan' button");
			newRule.click_associateRatePlans_doneButton.click();
			steps.add("Click 'Done' Rate Plan Picker");

			Wait.wait2Second();
		}

	// Created By adnan
	public void SaveRule(WebDriver driver, String RuleName, ArrayList<String> steps) throws InterruptedException {
		Elements_Inventory newRule = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(newRule.Click_saveButton, driver);
		Wait.explicit_wait_elementToBeClickable(newRule.Click_saveButton, driver);
		Utility.ScrollToElement(newRule.Click_saveButton, driver);
		newRule.Click_saveButton.click();
		steps.add("Click 'Save' button");
		// Rule created success message
		Wait.waitUntilPresenceOfElementLocated(OR.Message_newRuleCreated, driver);
		String ruleCreation_message = newRule.Message_newRuleCreated.getText();
		assertEquals(ruleCreation_message, "Successfully Created Rule : " + RuleName, "fail to create new rule");
		rulesLogger.info(" New Rule created Successfully " + ruleCreation_message);
		try {
			newRule.Message_newRuleCreated.click();
			Wait.waitForElementToBeGone(driver, newRule.Message_newRuleCreated, 15);
		} catch (Exception e) {

		}
	}

	// created By Adnan
	public void CloseTab(WebDriver driver, ArrayList<String> steps) throws InterruptedException {
		Elements_Inventory newRule = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(newRule.Click_closeTab, driver);
		Wait.explicit_wait_elementToBeClickable(newRule.Click_closeTab, driver);
		Utility.ScrollToElement(newRule.Click_closeTab, driver);
		newRule.Click_closeTab.click();
		steps.add("Close Rule Tab");
	}

}
