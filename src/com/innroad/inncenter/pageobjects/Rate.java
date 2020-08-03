package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.innroad.inncenter.interfaces.IRate;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Inventory;
import com.innroad.inncenter.webelements.Elements_NewRoomClass;
import com.innroad.inncenter.webelements.Elements_Reservation;

public class Rate implements IRate {

	public static Logger rateLogger = Logger.getLogger("Rate");

	public void inventory_Rate(WebDriver driver) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.wait3Second();
		rate.click_Inventory.click();
		rate.inventory_rate.click();
		Wait.waitUntilPresenceOfElementLocated(OR.Rates_Title, driver);
		// test_steps.add("System successfully Navigated to Inventory Rates ");
		rateLogger.info("System successfully Navigated to Inventory Rates ");
	}

	public void NavigateInventoryRate(WebDriver driver) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.wait3Second();
		rate.NavIconInventory.click();
		Wait.explicit_wait_xpath(OR.Inventory_Grid, driver);
		rate.inventory_rate.click();
		Wait.waitUntilPresenceOfElementLocated(OR.Rates_Title, driver);
		// test_steps.add("System successfully Navigated to Inventory Rates ");
		rateLogger.info("System successfully Navigated to Inventory Rates ");
	}

	public void new_Rate(WebDriver driver, String ratename, String maxAdults, String maxPersons, String baseAmount,
			String additionalAdult, String additionalChild, String rateDisplayName, String ratePolicy,
			String rateDescription, String RoomClass, String RoomClass1) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_Inventory rate = new Elements_Inventory(driver);
		clickOnNewRateButton(driver, test_steps);
		rate.rateName.sendKeys(ratename);
		String ratepan = new Select(rate.ratePlan).getFirstSelectedOption().getText();
		rateLogger.info("Selected Rate Plan : " + ratepan);
		String rateType = rate.getRateType.getText();
		rateLogger.info(" Selected Rate Type : " + rateType);
		rate.maxAdults.sendKeys(maxAdults);
		rate.maxPersons.sendKeys(maxPersons);
		rate.baseAmount.sendKeys(baseAmount);
		Wait.explicit_wait_xpath(OR.additionalAdult, driver);
		rate.additionalAdult.sendKeys(additionalAdult);
		Wait.explicit_wait_xpath(OR.additionalChild, driver);
		rate.additionalChild.sendKeys(additionalChild);
		Wait.explicit_wait_xpath(OR.rate_displayName, driver);
		rate.rate_displayName.sendKeys(rateDisplayName);
		Wait.explicit_wait_xpath(OR.rate_policy, driver);
		rate.rate_policy.sendKeys(ratePolicy);
		Wait.explicit_wait_xpath(OR.rate_description, driver);
		rate.rate_description.sendKeys(rateDescription);

		// Attach session
		Utility.ScrollToElement(rate.rate_Associate_Seasons, driver);
		rate.rate_Associate_Seasons.click();
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		rate.click_All_Seasons.click();
		rate.doneButton.click();
		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_RoomClasses, driver);
		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		rate.rate_Associate_RoomClasses.click();
		Thread.sleep(5000);
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		Wait.wait5Second();

		// rate.click_All_RoomClasses.click();
		new Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass);
		rate.PickerButton.click();
		new Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass1);
		rate.PickerButton.click();
		Wait.explicit_wait_xpath(OR.doneButton, driver);
		rate.doneButton.click();
		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_Sources, driver);
		Utility.ScrollToElement(rate.rate_Associate_Sources, driver);
		rate.rate_Associate_Sources.click();
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		rate.rate_select_Source.click();
		Wait.explicit_wait_xpath(OR.doneButton, driver);
		rate.doneButton.click();
		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Wait.explicit_wait_xpath(OR.rate_Save_Button, driver);
		rate.rate_Save_Button.click();
		Wait.wait3Second();
		rateLogger.info("Click on Save Button ");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			Wait.wait5Second();
			rateLogger.info(" Clicked on Done Button ");
		} catch (Exception e) {
			driver.navigate().back();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_Save_Button, driver);
			rate.rate_Save_Button.click();
			Wait.wait3Second();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_done_button, driver);
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			Wait.wait5Second();
			rateLogger.info("Again Click on Done Button ");
		}
	}

	public ArrayList<String> new_Rate(WebDriver driver, String ratename, String maxAdults, String maxPersons,
			String baseAmount, String additionalAdult, String additionalChild, String rateDisplayName,
			String ratePolicy, String rateDescription, String RoomClass) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_Inventory rate = new Elements_Inventory(driver);
		clickOnNewRateButton(driver, test_steps);
		rate.rateName.sendKeys(ratename);
		test_steps.add("Rate Name : " + ratename);
		String ratepan = new Select(rate.ratePlan).getFirstSelectedOption().getText();
		rateLogger.info("Selected Rate Plan : " + ratepan);
		test_steps.add("Selected Rate Plan : " + ratepan);
		String rateType = rate.getRateType.getText();
		rateLogger.info(" Selected Rate Type : " + rateType);
		test_steps.add(" Selected Rate Type : " + rateType);
		rate.maxAdults.sendKeys(maxAdults);
		test_steps.add("Max Adults : " + maxAdults);
		rate.maxPersons.sendKeys(maxPersons);
		test_steps.add("Max Persons : " + maxPersons);
		rate.baseAmount.sendKeys(baseAmount);
		test_steps.add("Base Amount : " + baseAmount);
		Wait.explicit_wait_xpath(OR.additionalAdult, driver);
		rate.additionalAdult.sendKeys(additionalAdult);
		test_steps.add("Additional Adults : " + additionalAdult);
		Wait.explicit_wait_xpath(OR.additionalChild, driver);
		rate.additionalChild.sendKeys(additionalChild);
		test_steps.add("Additional Child : " + additionalChild);
		Wait.explicit_wait_xpath(OR.rate_displayName, driver);
		rate.rate_displayName.sendKeys(rateDisplayName);
		test_steps.add("Rate Display Name : " + rateDisplayName);
		Wait.explicit_wait_xpath(OR.rate_policy, driver);
		rate.rate_policy.sendKeys(ratePolicy);
		test_steps.add("Rate Policy : " + ratePolicy);
		Wait.explicit_wait_xpath(OR.rate_description, driver);
		rate.rate_description.sendKeys(rateDescription);
		test_steps.add("Rate Description : " + rateDescription);

		// Attach session
		Utility.ScrollToElement(rate.rate_Associate_Seasons, driver);
		rate.rate_Associate_Seasons.click();
		test_steps.add("Associalte Seasons Button Clicked");
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		rate.click_All_Seasons.click();
		test_steps.add("All Seasons Button Clicked");
		rate.doneButton.click();
		test_steps.add("Associalte Seasons Done Button Clicked");
		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_RoomClasses, driver);
		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		rate.rate_Associate_RoomClasses.click();
		test_steps.add("Associalte RoomClasses Button Clicked");
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		Wait.wait2Second();

		// rate.click_All_RoomClasses.click();
		new Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass);
		rate.PickerButton.click();
		test_steps.add("Associalte RoomClass Selected : " + RoomClass);
		Wait.explicit_wait_xpath(OR.doneButton, driver);
		rate.doneButton.click();
		test_steps.add("Associalte RoomClasses Done Button Clicked");
		Wait.wait2Second();
		driver.switchTo().defaultContent();

		Utility.ScrollToElement(rate.rate_Associate_Sources, driver);
		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_Sources, driver);
		rate.rate_Associate_Sources.click();
		test_steps.add("Associalte Sources Button Clicked");
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		rate.rate_select_Source.click();
		test_steps.add("Select Source");
		Wait.explicit_wait_visibilityof_webelement(rate.doneButton, driver);
		rate.doneButton.click();
		test_steps.add("Associalte Sources Done Button Clicked");
		Wait.wait2Second();
		driver.switchTo().defaultContent();

		Wait.explicit_wait_xpath(OR.rate_Save_Button, driver);
		rate.rate_Save_Button.click();
		Wait.wait3Second();
		rateLogger.info("Click on Save Button ");
		test_steps.add("Click on Save Button ");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			Wait.wait5Second();
			rateLogger.info(" Clicked on Done Button ");
			test_steps.add(" Clicked on Done Button ");
		} catch (Exception e) {
			driver.navigate().back();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_Save_Button, driver);
			rate.rate_Save_Button.click();
			Wait.wait3Second();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_done_button, driver);
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			Wait.wait5Second();
			rateLogger.info("Again Click on Done Button ");
			test_steps.add("Again Click on Done Button ");
		}
		return test_steps;
	}

	public ArrayList<String> CreateRate(WebDriver driver, String ratename, String maxAdults, String maxPersons,
			String baseAmount, String additionalAdult, String additionalChild, String rateDisplayName,
			String ratePolicy, String rateDescription, String RoomClass, ArrayList<String> test_steps)
			throws InterruptedException {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_Inventory rate = new Elements_Inventory(driver);
		clickOnNewRateButton(driver, test_steps);

		rate.rateName.sendKeys(ratename);

		test_steps.add("Rate Name : " + ratename);
		rateLogger.info("Rate Name : " + ratename);
		String ratepan = new Select(rate.ratePlan).getFirstSelectedOption().getText();
		test_steps.add("Selected Rate Plan : " + ratepan);
		rateLogger.info("Selected Rate Plan : " + ratepan);
		String rateType = rate.getRateType.getText();
		test_steps.add("Selected Rate Type : " + rateType);
		rateLogger.info("Selected Rate Type : " + rateType);
		rate.maxAdults.sendKeys(maxAdults);
		test_steps.add("Rate Max Adults : " + maxAdults);
		rateLogger.info("Rate Max Adults : " + maxAdults);
		rate.maxPersons.sendKeys(maxPersons);
		test_steps.add("Rate Max Persons : " + maxPersons);
		rateLogger.info("Rate Max Persons : " + maxPersons);
		rate.baseAmount.sendKeys(baseAmount);
		test_steps.add("Rate Base Amount : " + baseAmount);
		rateLogger.info("Rate Base Amount : " + baseAmount);
	//	Wait.explicit_wait_xpath(OR.additionalAdult, driver);
		rate.additionalAdult.sendKeys(additionalAdult);
		test_steps.add("Rate Additional Adult : " + additionalAdult);
		rateLogger.info("Rate Additional Adult : " + additionalAdult);
	//	Wait.explicit_wait_xpath(OR.additionalChild, driver);
		rate.additionalChild.sendKeys(additionalChild);
		test_steps.add("Rate Additional Child : " + additionalChild);
		rateLogger.info("Rate Additional Child : " + additionalChild);
	//	Wait.explicit_wait_xpath(OR.rate_displayName, driver);
		rate.rate_displayName.sendKeys(rateDisplayName);
		test_steps.add("Rate Display Name : " + rateDisplayName);
		rateLogger.info("Rate Display Name : " + rateDisplayName);
	//	Wait.explicit_wait_xpath(OR.rate_policy, driver);
		rate.rate_policy.sendKeys(ratePolicy);
		test_steps.add("Rate Policy : " + ratePolicy);
		rateLogger.info("Rate Policy : " + ratePolicy);
	//	Wait.explicit_wait_xpath(OR.rate_description, driver);
		rate.rate_description.sendKeys(rateDescription);
		test_steps.add("Rate Description : " + rateDescription);
		rateLogger.info("Rate Description : " + rateDescription);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Associate_Seasons),8, driver);
		// Attach session
		Utility.ScrollToElement(rate.rate_Associate_Seasons, driver);
		rate.rate_Associate_Seasons.click();
		test_steps.add("Click Associate Seasons");
		rateLogger.info("Click Associate Seasons");
//		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		rate.click_All_Seasons.click();
		rate.doneButton.click();
		test_steps.add("Select All Seasons and Click Done");
		rateLogger.info("Select All Seasons and Click Done");
//		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_RoomClasses, driver);
		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		jse.executeScript("window.scrollBy(0,300)");
		rate.rate_Associate_RoomClasses.click();
		test_steps.add("Click Associate RoomClasses");
		rateLogger.info("Click Associate RoomClasses");
//		Thread.sleep(5000);
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		Wait.wait5Second();
		// rate.click_All_RoomClasses.click();
		new Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass);
		rate.PickerButton.click();
//		Wait.explicit_wait_xpath(OR.doneButton, driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.doneButton), 5,driver);

		rate.doneButton.click();
		test_steps.add("Select  Room Class : " + RoomClass + " and Click Done");
		rateLogger.info("Select  Room Class : " + RoomClass + " and Click Done");
		//Wait.wait3Second();
		driver.switchTo().defaultContent();

		Utility.ScrollToElement(rate.rate_Associate_Sources, driver);
		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_Sources, driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Associate_Sources), 5,driver);
		rate.rate_Associate_Sources.click();
		test_steps.add("Click Associate Sources");
		rateLogger.info("Click Associate Sources");
//		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		rate.rate_select_Source.click();
//		Wait.explicit_wait_xpath(OR.doneButton, driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.doneButton),5, driver);
		rate.doneButton.click();
		test_steps.add("Select first Source and Click Done");
		rateLogger.info("Select first Source and Click Done");
//		Wait.wait3Second();
		driver.switchTo().defaultContent();
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Save_Button),5, driver);
//		Wait.explicit_wait_xpath(OR.rate_Save_Button, driver);
		rate.rate_Save_Button.click();
//		Wait.wait3Second();
		test_steps.add("Click Save Rate");
		rateLogger.info("Click Save Rate");
		try {
			rate.rate_done_button.click();
//			Wait.wait5Second();
			test_steps.add("Click Done Rate");
			rateLogger.info("Click Done Rate");
		} catch (Exception e) {
			driver.navigate().back();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_Save_Button, driver);
			rate.rate_Save_Button.click();
		//	Wait.wait3Second();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_done_button, driver);
			rate.rate_done_button.click();
//			Wait.wait5Second();
			rateLogger.info("Again Click on Done Button ");
		}
		return test_steps;
	}

	public void delete_rate(WebDriver driver) throws InterruptedException

	{
		Elements_Inventory rate = new Elements_Inventory(driver);
		// Wait.wait5Second();
		// rate.click_Inventory.click();
		// rate.inventory_rate.click();
		// Wait.wait10Second();
		rate.click_goButton.click();
		Wait.wait5Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", rate.selectBaseRate);
		rate.selectBaseRate.click();
		Wait.wait10Second();
		rate.deleteRate.click();
		Wait.wait10Second();
		rateLogger.info(" System sucessfully deleted the Rate ");
		Wait.wait10Second();

	}

	public void delete_rate(WebDriver driver, String RateName) throws InterruptedException

	{
		Elements_Inventory rate = new Elements_Inventory(driver);
		rate.click_goButton.click();
		Wait.wait5Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String ratePath = "(//a[text()='" + RateName + "']/../preceding-sibling::td/span/input)[1]";
		WebElement Rate = driver.findElement(By.xpath(ratePath));
		Wait.wait2Second();

		// jse.executeScript("arguments[0].scrollIntoView();", Rate);
		jse.executeScript("arguments[0].click();", Rate);
		// Rate.click();
		Wait.wait10Second();
		rate.deleteRate.click();
		rateLogger.info(" System sucessfully deleted the Rate ");

	}

	public void delete_rate_1(WebDriver driver, String RateName) throws InterruptedException

	{
		Elements_Inventory rate = new Elements_Inventory(driver);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String ratePath = "(//a[text()='" + RateName + "']/../preceding-sibling::td/span/input)[1]";
		WebElement Rate = driver.findElement(By.xpath(ratePath));
		Wait.wait2Second();

		// jse.executeScript("arguments[0].scrollIntoView();", Rate);
		jse.executeScript("arguments[0].click();", Rate);
		// Rate.click();
		Wait.wait10Second();
		rate.deleteRate.click();
		rateLogger.info(" System sucessfully deleted the Rate ");

	}

	public void deleteAllRates(WebDriver driver, ArrayList<String> test_steps, String rateName) throws InterruptedException{
			Elements_Inventory rate = new Elements_Inventory(driver);
			try {
				List<WebElement> ratesCheckBoxes = driver.findElements(By.
						xpath("//a[contains(text(),'"+rateName+"')]/../preceding-sibling::td/span/input"));
				if (ratesCheckBoxes.size() >0 ) {
					for (WebElement checkBox : ratesCheckBoxes) {
						checkBox.click();
					}
					rate.deleteRate.click();
					test_steps.add("Deleted all rate plans named with "+rateName);
				}else {
					test_steps.add("No rate plans are existing to delete for "+rateName);					
				}
			} catch (Exception e) {
				test_steps.add("No rate plans are existing to delete for "+rateName);
			}			
	}

	public void Delete_rate_1(WebDriver driver, String RateName) throws InterruptedException

	{
		Elements_Inventory rate = new Elements_Inventory(driver);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String ratePath = "(//a[text()='" + RateName + "']/../preceding-sibling::td/span/input)[1]";
		WebElement Rate = driver.findElement(By.xpath(ratePath));
		Wait.wait2Second();

		// jse.executeScript("arguments[0].scrollIntoView();", Rate);
		jse.executeScript("arguments[0].click();", Rate);
		try {
			assertTrue(Rate.isSelected(), "Failed : Rate CheckBox is not Selected");
		} catch (Exception e) {
			Utility.ScrollToElement(Rate, driver);
			Rate.click();
			assertTrue(Rate.isSelected(), "Failed : Rate CheckBox is not Selected");
		}
		// Rate.click();
		rate.deleteRate.click();
		rateLogger.info(" System sucessfully deleted the Rate ");

	}

	public ArrayList<String> verifyDeleteRate(WebDriver driver, String RateName) throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<>();
		
		try {
			Elements_Inventory rate = new Elements_Inventory(driver);
			Wait.explicit_wait_visibilityof_webelement(rate.click_goButton, driver);
			Utility.ScrollToElement(rate.click_goButton, driver);
			rate.click_goButton.click();
			Utility.app_logs.info("Click Rate's Go Button");
			testSteps.add("Click Rate's Go Button");
			Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.cssSelector(OR.FindRateName)), driver);
			boolean element = driver.findElements(By.cssSelector(OR.RatesPagesSize)).size() > 0;
			int element_size = 0;
			int index = 0;
			boolean isRateCreated = false;
			List<WebElement> RatesElements = null;
			if (!element) {
				// single page
				Utility.app_logs.info("Single Page of rates");
				element_size = driver.findElements(By.cssSelector(OR.FindRateName)).size();

				if (element_size > 1) {
					RatesElements = driver.findElements(By.cssSelector(OR.FindRateName));
					for (int j = 0; j < element_size; j++) {
						Utility.app_logs.info(RatesElements.get(j).getText());
						if (RatesElements.get(j).getText().contains(RateName)) {
							isRateCreated = true;
							index = j;
							Utility.app_logs.info("Rate : " + RateName + " Found");
							testSteps.add("Rate : " + RateName + " Found");
							break;
						}
					}
				}
			} else {

				int size = driver.findElements(By.cssSelector(OR.RatesPagesSize)).size();

				for (int i = 0; i <= size; i++) {
					Utility.app_logs.info("Page : " + i + " of rates");
					element_size = driver.findElements(By.cssSelector(OR.FindRateName)).size();
					isRateCreated = false;
					if (element_size > 1) {
						RatesElements = driver.findElements(By.cssSelector(OR.FindRateName));
						for (int j = 0; j < element_size; j++) {
							Utility.app_logs.info(RatesElements.get(j).getText());
							if (RatesElements.get(j).getText().equals(RateName)) {
								isRateCreated = true;
								index = j;
								Utility.app_logs.info("Rate : " + RateName + " Found");
								testSteps.add("Rate : " + RateName + " Found");
								break;
							}
						}

					}
					if (isRateCreated == false && i < size) {
						JavascriptExecutor jse = (JavascriptExecutor) driver;
						jse.executeScript("window.scrollBy(0,450)", "");
						driver.findElements(By.cssSelector(OR.RatesPagesSize)).get(i).click();
						Wait.wait2Second();
					}

				}
			}

			//assertTrue(!isRateCreated, "Failed : Found Deleted rate");
		} catch (Exception e) {
			
			
		}
		return testSteps;

	}

	public void SearchRate(WebDriver driver, String RateName, boolean click) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		boolean element = driver.findElements(By.cssSelector(OR.RatesPagesSize)).size() > 0;
		int element_size = 0;
		int index = 0;
		boolean isRateCreated = false;
		List<WebElement> RatesElements = null;
		if (!element) {
			// single page

			element_size = driver.findElements(By.cssSelector(OR.FindRateName)).size();

			if (element_size > 1) {
				RatesElements = driver.findElements(By.cssSelector(OR.FindRateName));
				for (int j = 0; j < element_size; j++) {
					if (RatesElements.get(j).getText().contains(RateName)) {
						isRateCreated = true;
						index = j;
						break;
					}
				}
			}
		} else {

			int size = driver.findElements(By.cssSelector(OR.RatesPagesSize)).size();

			for (int i = 0; i <= size; i++) {

				element_size = driver.findElements(By.cssSelector(OR.FindRateName)).size();
				isRateCreated = false;
				if (element_size > 1) {
					RatesElements = driver.findElements(By.cssSelector(OR.FindRateName));
					for (int j = 0; j < element_size; j++) {

						if (RatesElements.get(j).getText().equals(RateName)) {
							isRateCreated = true;
							index = j;
							assertEquals(isRateCreated, true, "Newely creatd rate does not find in rate list");
							break;
						}
					}

				}
				if (isRateCreated == false && i < size) {
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					// jse.executeScript("window.scrollBy(0,450)", "");
					Utility.ScrollToElement(driver.findElement(By.cssSelector(OR.RatesPagesSize)), driver);
					driver.findElements(By.cssSelector(OR.RatesPagesSize)).get(i).click();
					Wait.wait2Second();
				}

			}
		}

		if (click) {
			// driver.navigate().refresh();
			// Thread.sleep(10000);
			// JavascriptExecutor jse = (JavascriptExecutor) driver;
			// jse.executeScript("window.scrollBy(0,450)", "");
			String RatePath = "//a[.='" + RateName + "']";
			WebElement CreatedRate = driver.findElement(By.xpath(RatePath));
			Utility.ScrollToElement(CreatedRate, driver);
			CreatedRate.click();
			Wait.explicit_wait_visibilityof_webelement(rate.rateName, driver);
		}

	}

	public void UpdateRate(WebDriver driver, String RateName) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_xpath(OR.rateName, driver);
		rate.rateName.clear();
		rate.rateName.sendKeys(RateName);
		Wait.explicit_wait_xpath(OR.rate_Save_Button, driver);
		rate.rate_Save_Button.click();
		Wait.wait5Second();
		rate.rate_done_button.click();
		Wait.wait5Second();
	}

	public void Attach_RoomClass(WebDriver driver, String RoomClass) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Utility.app_logs.info(RoomClass);
		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_RoomClasses, driver);
		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		rate.rate_Associate_RoomClasses.click();
		Thread.sleep(5000);
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		try {
			new Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass);
		} catch (Exception e) {
			new Select(rate.SelectAssociateRoomClass_1).selectByVisibleText(RoomClass);

		}
		rate.PickerButton.click();
		Wait.explicit_wait_xpath(OR.doneButton, driver);
		rate.doneButton.click();
		Wait.wait2Second();
		rate.rate_Save_Button.click();
		Wait.wait3Second();
		rate.rate_done_button.click();
		Wait.wait3Second();
	}

	public void UnAttach_RoomClass(WebDriver driver, String RoomClass) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);

		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_RoomClasses, driver);

		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		// String Path = "//td[.='Test RC']//following-sibling::td/a";
		String Path = "(//td[.='" + RoomClass + "']//following-sibling::td/a)[1]";
		WebElement element = driver.findElement(By.xpath(Path));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		// element.click();
		Wait.wait2Second();
		rate.rate_Save_Button.click();
		Wait.wait3Second();
		rate.rate_done_button.click();
		Wait.wait2Second();

	}

	public void SearchRateAsscioatedSeason(WebDriver driver, String SeasonType) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		new Select(rate.Rate_SeasonFilter).selectByVisibleText(SeasonType);
		rate.click_goButton.click();
		Wait.wait2Second();
		rate.Rate_FirstItemRateList.click();
	}

	public void VerifySeason(WebDriver driver, String SeasonType, String UpdatedDate) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", rate.Rate_ApplyToSeasonsName);
		Utility.app_logs.info("Season UpateDate: " + UpdatedDate);
		String NewSeasonDate = rate.Rate_ApplyToSeasonsStartDate.getText();
		Utility.app_logs.info("Season Rate Date: " + NewSeasonDate);
		if (rate.Rate_ApplyToSeasonsName.getText().equals(SeasonType)) {

			assertEquals(rate.Rate_ApplyToSeasonsStartDate.getText(), UpdatedDate,
					"Failed:Date not same as updated date");

		}

	}

	public void SelectRoomClassesFromTable(WebDriver driver) throws Exception {

		Elements_Inventory rate = new Elements_Inventory(driver);
		rate.Rate_FirstItemRateList.click();
		Wait.wait3Second();
		assertTrue(rate.Rate_RateDetailPage.isDisplayed(), "Failed: Rate Details Page not displayed");

	}

	public void EditRoomInfo(WebDriver driver, String SortOrder) throws Exception {

		Elements_NewRoomClass roomClass = new Elements_NewRoomClass(driver);

		Random rand = new Random();

		int n = rand.nextInt(50) + 1;
		int SortOrderInt = Integer.parseInt(SortOrder);
		SortOrderInt = (SortOrderInt + n);
		SortOrder = String.valueOf(SortOrderInt);
		roomClass.CreateVirtualRoom_VirtualSortOrder.clear();
		roomClass.CreateVirtualRoom_VirtualSortOrder.sendKeys(SortOrder);
		roomClass.NewRoomClassPublish.click();
		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
		Wait.wait3Second();
		roomClass.RoomClasses_ChangesSaved_OKButton.click();

	}

	public void New_Rate_With_PromoCode(WebDriver driver, String ratename, String RatePlan, String maxAdults,
			String maxPersons, String baseAmount, String additionalAdult, String additionalChild,
			String rateDisplayName, String ratePolicy, String rateDescription, String RoomClass, String ConditionalRate,
			String PromoCode, String Season) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<>();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_Inventory rate = new Elements_Inventory(driver);
		clickOnNewRateButton(driver, test_steps);
		rate.rateName.sendKeys(ratename);
		new Select(rate.ratePlan).selectByVisibleText(RatePlan);
		rateLogger.info("Selected Rate Plan : " + RatePlan);
		String rateType = rate.getRateType.getText();
		rateLogger.info(" Selected Rate Type : " + rateType);

		if (ConditionalRate.equals("Yes")) {
			rate.ConditionalRate.click();
			rate.Rate_PromoCode.sendKeys(PromoCode);
		}
		rate.maxAdults.sendKeys(maxAdults);
		rate.maxPersons.sendKeys(maxPersons);
		rate.baseAmount.sendKeys(baseAmount);
		Wait.explicit_wait_xpath(OR.additionalAdult, driver);
		rate.additionalAdult.sendKeys(additionalAdult);
		Wait.explicit_wait_xpath(OR.additionalChild, driver);
		rate.additionalChild.sendKeys(additionalChild);
		Wait.explicit_wait_xpath(OR.rate_displayName, driver);
		rate.rate_displayName.sendKeys(rateDisplayName);
		Wait.explicit_wait_xpath(OR.rate_policy, driver);
		rate.rate_policy.sendKeys(ratePolicy);
		Wait.explicit_wait_xpath(OR.rate_description, driver);
		rate.rate_description.sendKeys(rateDescription);
		// Attach session
		Utility.ScrollToElement(rate.rate_Associate_Seasons, driver);
		rate.rate_Associate_Seasons.click();
		rateLogger.info("Click Associate Seasons");

		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");

		String path = "//*[@id='lstSeasons']//option[contains(text(),'" + Season + "')]";
		WebElement season = driver.findElement(By.xpath(path));
		Wait.explicit_wait_visibilityof_webelement(season, driver);
		Utility.ScrollToElement(season, driver);

		season.click();
		rate.PickerButton.click();
		rate.doneButton.click();
		rateLogger.info("Select " + Season + " Season and Click Done");

		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_RoomClasses, driver);
		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		rate.rate_Associate_RoomClasses.click();
		Thread.sleep(2000);
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		Wait.wait2Second();

		// rate.click_All_RoomClasses.click();
		new Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass);
		rate.PickerButton.click();
		Wait.explicit_wait_xpath(OR.doneButton, driver);
		rate.doneButton.click();
		Wait.wait2Second();
		driver.switchTo().defaultContent();

		Utility.ScrollToElement(rate.rate_Associate_Sources, driver);
		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_Sources, driver);
		rate.rate_Associate_Sources.click();
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		rate.rate_select_Source.click();
		Wait.explicit_wait_visibilityof_webelement(rate.doneButton, driver);
		rate.doneButton.click();
		Wait.wait2Second();
		driver.switchTo().defaultContent();

		Wait.explicit_wait_xpath(OR.rate_Save_Button, driver);
		rate.rate_Save_Button.click();
		Wait.wait3Second();
		rateLogger.info("Click on Save Button ");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			Wait.wait5Second();
			rateLogger.info(" Clicked on Done Button ");
		} catch (Exception e) {
			driver.navigate().back();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_Save_Button, driver);
			rate.rate_Save_Button.click();
			Wait.wait3Second();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_done_button, driver);
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			Wait.wait5Second();
			rateLogger.info("Again Click on Done Button ");
		}
	}

	public ArrayList<String> rate_AssociateToSeason(WebDriver driver, String ratename, String maxAdults,
			String maxPersons, String baseAmount, String additionalAdult, String additionalChild,
			String rateDisplayName, String ratePolicy, String rateDescription, String RoomClass, String SeasonName,
			ArrayList<String> test_steps) throws InterruptedException {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_Inventory rate = new Elements_Inventory(driver);
		clickOnNewRateButton(driver, test_steps);
		rate.rateName.sendKeys(ratename);

		test_steps.add("Enter Rate Name : " + ratename);
		rateLogger.info("Enter Rate Name : " + ratename);
		String ratepan = new Select(rate.ratePlan).getFirstSelectedOption().getText();
		test_steps.add("Selected Rate Plan : " + ratepan);
		rateLogger.info("Selected Rate Plan : " + ratepan);
		String rateType = rate.getRateType.getText();
		test_steps.add("Selected Rate Type : " + rateType);
		rateLogger.info("Selected Rate Type : " + rateType);

		maxAdults = maxAdults.substring(0, maxAdults.indexOf("."));
		rate.maxAdults.sendKeys(maxAdults);
		test_steps.add("Enter Rate Max Adults : " + maxAdults);
		rateLogger.info("Enter Rate Max Adults : " + maxAdults);

		maxPersons = maxPersons.substring(0, maxPersons.indexOf("."));
		rate.maxPersons.sendKeys(maxPersons);
		test_steps.add("Enter Rate Max Persons : " + maxPersons);
		rateLogger.info("Enter Rate Max Persons : " + maxPersons);

		rate.baseAmount.sendKeys(baseAmount);
		test_steps.add("Enter Rate Base Amount : " + baseAmount);
		rateLogger.info("Enter Rate Base Amount : " + baseAmount);
		Wait.explicit_wait_xpath(OR.additionalAdult, driver);
		rate.additionalAdult.sendKeys(additionalAdult);
		test_steps.add("Enter Rate Additional Adult : " + additionalAdult);
		rateLogger.info("Enter Rate Additional Adult : " + additionalAdult);
		Wait.explicit_wait_xpath(OR.additionalChild, driver);
		rate.additionalChild.sendKeys(additionalChild);
		test_steps.add("Enter Rate Additional Child : " + additionalChild);
		rateLogger.info("Enter Rate Additional Child : " + additionalChild);
		Wait.explicit_wait_xpath(OR.rate_displayName, driver);
		rate.rate_displayName.sendKeys(rateDisplayName);
		test_steps.add("Enter Rate Display Name : " + rateDisplayName);
		rateLogger.info("Enter Rate Display Name : " + rateDisplayName);
		Wait.explicit_wait_xpath(OR.rate_policy, driver);
		rate.rate_policy.sendKeys(ratePolicy);
		test_steps.add("Enter Rate Policy : " + ratePolicy);
		rateLogger.info("Enter Rate Policy : " + ratePolicy);
		Wait.explicit_wait_xpath(OR.rate_description, driver);
		rate.rate_description.sendKeys(rateDescription);
		test_steps.add("Enter Rate Description : " + rateDescription);
		rateLogger.info("Enter Rate Description : " + rateDescription);

		// Attach session
		Utility.ScrollToElement(rate.rate_Associate_Seasons, driver);
		rate.rate_Associate_Seasons.click();
		test_steps.add("Click Associate Seasons");
		rateLogger.info("Click Associate Seasons");

		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		String path = "//*[@id='lstSeasons']//option[contains(text(),'" + SeasonName + "')]";
		WebElement season = driver.findElement(By.xpath(path));
		Wait.explicit_wait_visibilityof_webelement(season, driver);
		Utility.ScrollToElement(season, driver);

		season.click();
		rate.PickerButton.click();
		rate.doneButton.click();
		test_steps.add("Select " + SeasonName + " Season and Click Done");
		rateLogger.info("Select " + SeasonName + " Season and Click Done");

		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_RoomClasses, driver);
		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		jse.executeScript("window.scrollBy(0,300)");
		rate.rate_Associate_RoomClasses.click();
		test_steps.add("Click Associate RoomClasses");
		rateLogger.info("Click Associate RoomClasses");
		Thread.sleep(5000);

		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		Wait.wait5Second();

		String pathRoom = "//*[@id='lstRooms']//option[contains(text(),'" + RoomClass + "')]";
		WebElement seasonRoom = driver.findElement(By.xpath(pathRoom));
		Wait.explicit_wait_visibilityof_webelement(seasonRoom, driver);
		Utility.ScrollToElement(seasonRoom, driver);
		seasonRoom.click();

		new Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass);
		rate.PickerButton.click();
		Wait.explicit_wait_xpath(OR.doneButton, driver);
		rate.doneButton.click();
		test_steps.add("Select  Room Class : " + RoomClass + " and Click Done");
		rateLogger.info("Select  Room Class : " + RoomClass + " and Click Done");

		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Utility.ScrollToElement(rate.rate_Associate_Sources, driver);
		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_Sources, driver);
		rate.rate_Associate_Sources.click();
		test_steps.add("Click Associate Sources");
		rateLogger.info("Click Associate Sources");

		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		rate.rate_select_Source.click();
		Wait.explicit_wait_xpath(OR.doneButton, driver);
		rate.doneButton.click();

		test_steps.add("Select first Source and Click Done");
		rateLogger.info("Select first Source and Click Done");
		Wait.wait5Second();
		driver.switchTo().defaultContent();

		Wait.wait5Second();
		Wait.explicit_wait_xpath(OR.rate_Save_Button, driver);
		rate.rate_Save_Button.click();
		test_steps.add("Click on Save Rate Button");
		rateLogger.info("Click on Save Button ");
		try {
			Wait.wait5Second();
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			test_steps.add("Click Done Rate");
			rateLogger.info(" Clicked on Done Button ");

		} catch (Exception e) {
			driver.navigate().back();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_Save_Button, driver);
			rate.rate_Save_Button.click();
			Wait.wait3Second();
			// Wait.explicit_wait_visibilityof_webelement(rate.rate_done_button,
			// driver);
			// rate.rate_done_button.click();
			// Wait.wait5Second();
			// rateLogger.info("Again Click on Done Button ");
		}
		return test_steps;

	}

	public ArrayList<String> CreateRate_Season(WebDriver driver, String ratename, String maxAdults, String maxPersons,
			String baseAmount, String additionalAdult, String additionalChild, String rateDisplayName,
			String ratePolicy, String rateDescription, String RoomClass, String Season, String RatePlan,
			ArrayList<String> test_steps) throws InterruptedException {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_Inventory rate = new Elements_Inventory(driver);
		clickOnNewRateButton(driver, test_steps);
		rate.rateName.sendKeys(ratename);

		test_steps.add("Enter Rate Name : " + ratename);
		rateLogger.info("Enter Rate Name : " + ratename);
		new Select(rate.ratePlan).selectByVisibleText(RatePlan);
		String ratepan = new Select(rate.ratePlan).getFirstSelectedOption().getText();
		assertEquals(ratepan, RatePlan, "Failed: Rate Plan is not Selected");
		test_steps.add("Selected Rate Plan : " + ratepan);
		rateLogger.info("Selected Rate Plan : " + ratepan);
		String rateType = rate.getRateType.getText();
		test_steps.add("Selected Rate Type : " + rateType);
		rateLogger.info("Selected Rate Type : " + rateType);
		rate.maxAdults.sendKeys(maxAdults);
		test_steps.add("Enter Rate Max Adults : " + maxAdults);
		rateLogger.info("Enter Rate Max Adults : " + maxAdults);
		rate.maxPersons.sendKeys(maxPersons);
		test_steps.add("Enter Rate Max Persons : " + maxPersons);
		rateLogger.info("Enter Rate Max Persons : " + maxPersons);
		rate.baseAmount.sendKeys(baseAmount);
		test_steps.add("Enter Rate Base Amount : " + baseAmount);
		rateLogger.info("Enter Rate Base Amount : " + baseAmount);
		Wait.explicit_wait_xpath(OR.additionalAdult, driver);
		rate.additionalAdult.sendKeys(additionalAdult);
		test_steps.add("Enter Rate Additional Adult : " + additionalAdult);
		rateLogger.info("Enter Rate Additional Adult : " + additionalAdult);
		Wait.explicit_wait_xpath(OR.additionalChild, driver);
		rate.additionalChild.sendKeys(additionalChild);
		test_steps.add("Enter Rate Additional Child : " + additionalChild);
		rateLogger.info("Enter Rate Additional Child : " + additionalChild);
		Wait.explicit_wait_xpath(OR.rate_displayName, driver);
		rate.rate_displayName.sendKeys(rateDisplayName);
		test_steps.add("Rate Display Name : " + rateDisplayName);
		rateLogger.info("Rate Display Name : " + rateDisplayName);
		Wait.explicit_wait_xpath(OR.rate_policy, driver);
		rate.rate_policy.sendKeys(ratePolicy);
		test_steps.add("Enter Rate Policy : " + ratePolicy);
		rateLogger.info("Enter Rate Policy : " + ratePolicy);
		Wait.explicit_wait_xpath(OR.rate_description, driver);
		rate.rate_description.sendKeys(rateDescription);
		test_steps.add("Enter Rate Description : " + rateDescription);
		rateLogger.info("Enter Rate Description : " + rateDescription);

		// Attach session
		Utility.ScrollToElement(rate.rate_Associate_Seasons, driver);
		rate.rate_Associate_Seasons.click();
		test_steps.add("Click Associate Seasons");
		rateLogger.info("Click Associate Seasons");

		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		String path = "//*[@id='lstSeasons']//option[contains(text(),'" + Season + "')]";
		WebElement season = driver.findElement(By.xpath(path));
		Wait.explicit_wait_visibilityof_webelement(season, driver);
		Utility.ScrollToElement(season, driver);

		season.click();
		rate.PickerButton.click();
		rate.doneButton.click();
		test_steps.add("Select " + Season + " Season and Click Done");
		rateLogger.info("Select " + Season + " Season and Click Done");

		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_RoomClasses, driver);
		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		jse.executeScript("window.scrollBy(0,300)");
		rate.rate_Associate_RoomClasses.click();
		test_steps.add("Click Associate RoomClasses");
		rateLogger.info("Click Associate RoomClasses");
		Thread.sleep(5000);
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		Wait.wait5Second();

		// rate.click_All_RoomClasses.click();
		new Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass);
		rate.PickerButton.click();
		Wait.explicit_wait_xpath(OR.doneButton, driver);
		rate.doneButton.click();
		test_steps.add("Select  Room Class : " + RoomClass + " and Click Done");
		rateLogger.info("Select  Room Class : " + RoomClass + " and Click Done");
		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Utility.ScrollToElement(rate.rate_Associate_Sources, driver);
		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_Sources, driver);
		rate.rate_Associate_Sources.click();
		test_steps.add("Click Associate Sources");
		rateLogger.info("Click Associate Sources");
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		rate.rate_select_Source.click();
		Wait.explicit_wait_xpath(OR.doneButton, driver);
		rate.doneButton.click();
		test_steps.add("Select first Source and Click Done");
		rateLogger.info("Select first Source and Click Done");
		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Wait.explicit_wait_xpath(OR.rate_Save_Button, driver);
		rate.rate_Save_Button.click();
		Wait.wait3Second();
		test_steps.add("Click Save Rate");
		rateLogger.info("Click Save Rate");
		try {
			rate.rate_done_button.click();
			Wait.wait5Second();
			test_steps.add("Click Done Rate");
			rateLogger.info("Click Done Rate");
		} catch (Exception e) {
			driver.navigate().back();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_Save_Button, driver);
			rate.rate_Save_Button.click();
			Wait.wait3Second();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_done_button, driver);
			rate.rate_done_button.click();
			Wait.wait5Second();
			rateLogger.info("Again Click on Done Button ");
		}
		return test_steps;
	}

	public boolean IsRateExist(WebDriver driver, String RateName) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		boolean element = driver.findElements(By.cssSelector(OR.RatesPagesSize)).size() > 0;
		int element_size = 0;
		int index = 0;
		boolean isRateCreated = false;
		List<WebElement> RatesElements = null;
		if (!element) {
			// single page

			element_size = driver.findElements(By.cssSelector(OR.FindRateName)).size();

			if (element_size > 1) {
				RatesElements = driver.findElements(By.cssSelector(OR.FindRateName));
				for (int j = 0; j < element_size; j++) {
					if (RatesElements.get(j).getText().contains(RateName)) {
						isRateCreated = true;
						index = j;
						break;
					}
				}
			}
			return isRateCreated;
		} else {

			int size = driver.findElements(By.cssSelector(OR.RatesPagesSize)).size();

			for (int i = 0; i <= size; i++) {

				element_size = driver.findElements(By.cssSelector(OR.FindRateName)).size();
				isRateCreated = false;
				if (element_size > 1) {
					RatesElements = driver.findElements(By.cssSelector(OR.FindRateName));
					for (int j = 0; j < element_size; j++) {

						if (RatesElements.get(j).getText().equals(RateName)) {
							isRateCreated = true;
							index = j;
							assertEquals(isRateCreated, true, "Newely creatd rate does not find in rate list");
							break;
						}
					}

				}
				if (isRateCreated == false) {
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("window.scrollBy(0,450)", "");
					driver.findElements(By.cssSelector(OR.RatesPagesSize)).get(i).click();
					Wait.wait2Second();
				}

			}

			return isRateCreated;
		}
	}
	

	/*public void new_Rate(WebDriver driver, String ratename, String ratePlan, String maxAdults, String maxPersons,
			String baseAmount, String additionalAdult, String additionalChild, String rateDisplayName,
			String associateSeason, String ratePolicy, String rateDescription, String RoomClass,
			ArrayList<String> test_steps) throws InterruptedException {

//		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_Inventory rate = new Elements_Inventory(driver);
		clickOnNewRateButton(driver, test_steps);
		provideRateName(driver, test_steps, ratename);
		selectRatePlan(driver, test_steps, ratePlan);
		String rateType = rate.getRateType.getText();
		rateLogger.info("Selected Rate Type : <b>" + rateType+"</b>");
		test_steps.add("Selected Rate Type : <b>" + rateType+"</b>");
		rate.maxAdults.sendKeys(maxAdults);
		test_steps.add("Provided max adults as : <b>" + maxAdults+"</b>");
		rate.maxPersons.sendKeys(maxPersons);
		test_steps.add("Provided max persons as : <b>" + maxPersons+"</b>");
		rate.baseAmount.sendKeys(baseAmount);
		test_steps.add("Provided base amount as : <b>" + baseAmount+"</b>");
		Wait.waitForElementToBeVisibile(By.xpath(OR.additionalAdult), driver);
		rate.additionalAdult.sendKeys(additionalAdult);
		test_steps.add("Provided additional adult as : <b>" + additionalAdult+"</b>");
		Wait.waitForElementToBeVisibile(By.xpath(OR.additionalChild), driver);
		rate.additionalChild.sendKeys(additionalChild);
		test_steps.add("Provided additional children as : <b>" + additionalChild+"</b>");
		Wait.waitForElementToBeVisibile(By.xpath(OR.rate_displayName), driver);
		rate.rate_displayName.sendKeys(rateDisplayName);
		test_steps.add("Provided rate display name as : <b>" + rateDisplayName+"</b>");
		Wait.waitForElementToBeVisibile(By.xpath(OR.rate_policy), driver);
		rate.rate_policy.sendKeys(ratePolicy);
		test_steps.add("Provided rate policy as : <b>" + ratePolicy+"</b>");
		Wait.waitForElementToBeVisibile(By.xpath(OR.rate_description), driver);
		rate.rate_description.sendKeys(rateDescription);
		test_steps.add("Provided rate description as : <b>" + rateDescription+"</b>");

		AssociateSeason(driver, associateSeason, test_steps);
		AssociateRoomClass(driver, RoomClass, test_steps);
		AssociateSource(driver, "innCenter", test_steps);

		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Save_Button), driver);
		rate.rate_Save_Button.click();
		rateLogger.info("Click on Save Button ");
		test_steps.add("Saved the Details");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			rateLogger.info("Clicked on Done Button ");
			test_steps.add("Clicked on Done");
		} catch (Exception e) {
			driver.navigate().back();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_Save_Button, driver);
			rate.rate_Save_Button.click();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_done_button, driver);
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			rateLogger.info("Again Click on Done Button ");
		}
		driver.navigate().refresh();
		rateLogger.info("Successfully Created <b>" + ratename + "</b> Rate for <b>" + ratePlan + "</b> Rate Plan");
		test_steps.add("Successfully Created <b>" + ratename + "</b>' Rate for <b>" + ratePlan + "</b> Rate Plan");
	}
*/
	public void delete_rate(WebDriver driver, ArrayList<String> test_steps, String RateName) throws InterruptedException

	{
		Elements_Inventory rate = new Elements_Inventory(driver);
		rate.click_goButton.click();
		Wait.wait5Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		test_steps.add("Fiinding the " + RateName + " in the Rates ");
		String ratePath = "//a[contains(text(),'" + RateName + "')]/../preceding-sibling::td/span/input[1]";
		WebElement Rate = driver.findElement(By.xpath(ratePath));
		Wait.explicit_wait_visibilityof_webelement(Rate, driver);
		jse.executeScript("arguments[0].click();", Rate);
		Wait.explicit_wait_visibilityof_webelement(rate.deleteRate, driver);
		rate.deleteRate.click();
		test_steps.add("Clicked on Delete Button");
		rateLogger.info("System sucessfully deleted the " + RateName + " Rate");
		test_steps.add("System sucessfully deleted the " + RateName + " Rate");
	}
	public void new_Rate(WebDriver driver, String ratename, String ratePlan, String maxAdults, String maxPersons,
			String baseAmount, String additionalAdult, String additionalChild, String rateDisplayName,
			String associateSeason, String ratePolicy, String rateDescription, String RoomClass,
			ArrayList<String> test_steps) throws InterruptedException {

//		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.newRate), driver);
		rate.newRate.click();
		Wait.waitForElementToBeVisibile(By.xpath(OR.rateName), driver);
		rate.rateName.sendKeys(ratename);
		test_steps.add("Provided Rate Name as <b> "+ratename+"</b> ");
		String ratePlanSelected = new Select(rate.ratePlan).getFirstSelectedOption().getText();
		if ( !(ratePlanSelected.equalsIgnoreCase(ratePlan)) ) {
			new Select(rate.ratePlan).selectByVisibleText(ratePlan);
			rateLogger.info("Selected Rate Plan as : <b>"+ratePlan+"</b>");
			test_steps.add("Selected Rate Plan  : <b>"+ratePlan+"</b>");			
		}else {
			test_steps.add("Rate Plan is already selected as : <b>"+ratePlan+"</b>");
		}
		String rateType = rate.getRateType.getText();
		rateLogger.info("Selected Rate Type : <b>" + rateType+"</b>");
		test_steps.add("Selected Rate Type : <b>" + rateType+"</b>");
		rate.maxAdults.sendKeys(maxAdults);
		test_steps.add("Provided max adults as : <b>" + maxAdults+"</b>");
		rate.maxPersons.sendKeys(maxPersons);
		test_steps.add("Provided max persons as : <b>" + maxPersons+"</b>");
		rate.baseAmount.sendKeys(baseAmount);
		test_steps.add("Provided base amount as : <b>" + baseAmount+"</b>");
		Wait.waitForElementToBeVisibile(By.xpath(OR.additionalAdult), driver);
		rate.additionalAdult.sendKeys(additionalAdult);
		test_steps.add("Provided additional adult as : <b>" + additionalAdult+"</b>");
		Wait.waitForElementToBeVisibile(By.xpath(OR.additionalChild), driver);
		rate.additionalChild.sendKeys(additionalChild);
		test_steps.add("Provided additional children as : <b>" + additionalChild+"</b>");
		Wait.waitForElementToBeVisibile(By.xpath(OR.rate_displayName), driver);
		rate.rate_displayName.sendKeys(rateDisplayName);
		test_steps.add("Provided rate display name as : <b>" + rateDisplayName+"</b>");
		Wait.waitForElementToBeVisibile(By.xpath(OR.rate_policy), driver);
		rate.rate_policy.sendKeys(ratePolicy);
		test_steps.add("Provided rate policy as : <b>" + ratePolicy+"</b>");
		Wait.waitForElementToBeVisibile(By.xpath(OR.rate_description), driver);
		rate.rate_description.sendKeys(rateDescription);
		test_steps.add("Provided rate description as : <b>" + rateDescription+"</b>");

		AssociateSeason(driver, associateSeason, test_steps);
		AssociateRoomClass(driver, RoomClass, test_steps);
		AssociateSource(driver, "innCenter", test_steps);

		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Save_Button), driver);
		rate.rate_Save_Button.click();
		rateLogger.info("Click on Save Button ");
		test_steps.add("Saved the Details");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			rateLogger.info("Clicked on Done Button ");
			test_steps.add("Clicked on Done");
		} catch (Exception e) {
			driver.navigate().back();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_Save_Button, driver);
			rate.rate_Save_Button.click();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_done_button, driver);
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			rateLogger.info("Again Click on Done Button ");
		}
		driver.navigate().refresh();
		rateLogger.info("Successfully Created <b>" + ratename + "</b> Rate for <b>" + ratePlan + "</b> Rate Plan");
		test_steps.add("Successfully Created <b>" + ratename + "</b>' Rate for <b>" + ratePlan + "</b> Rate Plan");
	}
	public void SearchRate(WebDriver driver, ArrayList<String> test_steps, String RateName, boolean click)
			throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		boolean element = driver.findElements(By.cssSelector(OR.RatesPagesSize)).size() > 0;
		int element_size = 0;
		int index = 0;
		boolean isRateCreated = false;
		List<WebElement> RatesElements = null;
		if (!element) {
			// single page

			element_size = driver.findElements(By.cssSelector(OR.FindRateName)).size();

			if (element_size > 1) {
				RatesElements = driver.findElements(By.cssSelector(OR.FindRateName));

				for (int j = 0; j < element_size; j++) {
					if (RatesElements.get(j).getText().contains(RateName)) {
						isRateCreated = true;
						index = j;
						if (click) {
							String RatePath = "//a[.='" + RateName + "']";
							WebElement CreatedRate = driver.findElement(By.xpath(RatePath));
							Utility.ScrollToElement(CreatedRate, driver);
							CreatedRate.click();
							Wait.explicit_wait_visibilityof_webelement(rate.rateName, driver);
						}
						test_steps.add("New Rate is Found in Rates : " + RateName);
						rateLogger.info("Rate Found Successfully ");
						break;
					}
				}
			}
			if (index == 0) {
				test_steps.add("No Rate is Available in Rates with the Name of " + RateName);
				rateLogger.info("Rate did not found");
			}
		} else {

			int size = driver.findElements(By.cssSelector(OR.RatesPagesSize)).size();

			for (int i = 0; i <= size; i++) {

				element_size = driver.findElements(By.cssSelector(OR.FindRateName)).size();
				isRateCreated = false;
				if (element_size > 1) {
					RatesElements = driver.findElements(By.cssSelector(OR.FindRateName));
					for (int j = 0; j < element_size; j++) {

						if (RatesElements.get(j).getText().equals(RateName)) {
							isRateCreated = true;
							index = j;
							assertEquals(isRateCreated, true, "Newely creatd rate does not find in rate list");
							rateLogger.info("Rate did not Found  ");
							break;
						}
					}

				}
				if (isRateCreated == false) {
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("window.scrollBy(0,450)", "");
					driver.findElements(By.cssSelector(OR.RatesPagesSize)).get(i).click();
					Wait.wait2Second();
				}

			}
		}

		if (click) {
			String RatePath = "//a[.='" + RateName + "']";
			WebElement CreatedRate = driver.findElement(By.xpath(RatePath));
			Utility.ScrollToElement(CreatedRate, driver);
			CreatedRate.click();
			Wait.explicit_wait_visibilityof_webelement(rate.rateName, driver);
		}

	}

	public ArrayList<String> CreateRate(WebDriver driver, String ratename, String maxAdults, String maxPersons,
			String baseAmount, String additionalAdult, String additionalChild, String rateDisplayName,
			String ratePolicy, String rateDescription, String RoomClass1, String RoomClass2,
			ArrayList<String> test_steps) throws InterruptedException {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_Inventory rate = new Elements_Inventory(driver);
		clickOnNewRateButton(driver, test_steps);
		provideRateName(driver, test_steps, ratename);		
		String ratepan = new Select(rate.ratePlan).getFirstSelectedOption().getText();
		test_steps.add("Selected Rate Plan : " + ratepan);
		rateLogger.info("Selected Rate Plan : " + ratepan);
		String rateType = rate.getRateType.getText();
		test_steps.add("Selected Rate Type : " + rateType);
		rateLogger.info("Selected Rate Type : " + rateType);
		rate.maxAdults.sendKeys(maxAdults);
		test_steps.add("Rate Max Adults : " + maxAdults);
		rateLogger.info("Rate Max Adults : " + maxAdults);
		rate.maxPersons.sendKeys(maxPersons);
		test_steps.add("Rate Max Persons : " + maxPersons);
		rateLogger.info("Rate Max Persons : " + maxPersons);
		rate.baseAmount.sendKeys(baseAmount);
		test_steps.add("Rate Base Amount : " + baseAmount);
		rateLogger.info("Rate Base Amount : " + baseAmount);
		Wait.explicit_wait_xpath(OR.additionalAdult, driver);
		rate.additionalAdult.sendKeys(additionalAdult);
		test_steps.add("Rate Additional Adult : " + additionalAdult);
		rateLogger.info("Rate Additional Adult : " + additionalAdult);
		Wait.explicit_wait_xpath(OR.additionalChild, driver);
		rate.additionalChild.sendKeys(additionalChild);
		test_steps.add("Rate Additional Child : " + additionalChild);
		rateLogger.info("Rate Additional Child : " + additionalChild);
		Wait.explicit_wait_xpath(OR.rate_displayName, driver);
		rate.rate_displayName.sendKeys(rateDisplayName);
		test_steps.add("Rate Display Name : " + rateDisplayName);
		rateLogger.info("Rate Display Name : " + rateDisplayName);
		Wait.explicit_wait_xpath(OR.rate_policy, driver);
		rate.rate_policy.sendKeys(ratePolicy);
		test_steps.add("Rate Policy : " + ratePolicy);
		rateLogger.info("Rate Policy : " + ratePolicy);
		Wait.explicit_wait_xpath(OR.rate_description, driver);
		rate.rate_description.sendKeys(rateDescription);
		test_steps.add("Rate Description : " + rateDescription);
		rateLogger.info("Rate Description : " + rateDescription);

		// Attach session
		Utility.ScrollToElement(rate.rate_Associate_Seasons, driver);
		rate.rate_Associate_Seasons.click();
		test_steps.add("Click Associate Seasons");
		rateLogger.info("Click Associate Seasons");
		//Wait.wait2Second();
		String path="//iframe[@id='dialog-body0']";
		Wait.WaitForElement(driver, path);
		driver.switchTo().frame("dialog-body0");
		rateLogger.info(" Switched to Main Frame ");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Child Frame ");
		rateLogger.info(" Switched to Frame ");
		rate.click_All_Seasons.click();
		rate.doneButton.click();
		test_steps.add("Select All Seasons and Click Done");
		rateLogger.info("Select All Seasons and Click Done");
		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_RoomClasses, driver);
		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		jse.executeScript("window.scrollBy(0,300)");
		rate.rate_Associate_RoomClasses.click();
		test_steps.add("Click Associate RoomClasses");
		rateLogger.info("Click Associate RoomClasses");
	//	Thread.sleep(5000);
		Wait.WaitForElement(driver, path);
		driver.switchTo().frame("dialog-body0");
		rateLogger.info(" Switched to Main Frame ");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Child Frame ");
	//	Wait.wait5Second();
		
		// rate.click_All_RoomClasses.click();
		new Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass1);
		rate.PickerButton.click();
	//	Wait.explicit_wait_xpath(OR.doneButton, driver);
		Wait.presenceOfElementLocated(driver, OR.doneButton, 10);
		new Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass2);
		rate.PickerButton.click();
	//	Wait.explicit_wait_xpath(OR.doneButton, driver);
		Wait.presenceOfElementLocated(driver, OR.doneButton, 10);
		rate.doneButton.click();
		test_steps.add("Select Room Classes: '" + RoomClass1 + "' , '" + RoomClass2 + "' and  Click Done");
		rateLogger.info("Select Room Classes: '" + RoomClass1 + "' , '" + RoomClass2 + "' and  Click Done");
		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Utility.ScrollToElement(rate.rate_Associate_Sources, driver);
		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_Sources, driver);
		rate.rate_Associate_Sources.click();
		test_steps.add("Click Associate Sources");
		rateLogger.info("Click Associate Sources");
	//	Wait.wait2Second();
		Wait.WaitForElement(driver, path);
		driver.switchTo().frame("dialog-body0");
		rateLogger.info(" Switched to Main Frame ");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Child Frame ");
		rate.rate_select_Source.click();
	//	Wait.explicit_wait_xpath(OR.doneButton, driver);
		Wait.presenceOfElementLocated(driver, OR.doneButton, 10);
		rate.doneButton.click();
		test_steps.add("Select first Source and Click Done");
		rateLogger.info("Select first Source and Click Done");
		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Wait.explicit_wait_xpath(OR.rate_Save_Button, driver);
	//	Wait.presenceOfElementLocated(driver, OR.rate_Save_Button, 20);
		rate.rate_Save_Button.click();
		Wait.wait3Second();
		test_steps.add("Click Save Rate");
		rateLogger.info("Click Save Rate");
		try {
			rate.rate_done_button.click();
			Wait.wait5Second();
			test_steps.add("Click Done Rate");
			rateLogger.info("Click Done Rate");
		} catch (Exception e) {
			driver.navigate().back();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_Save_Button, driver);
			rate.rate_Save_Button.click();
			Wait.wait3Second();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_done_button, driver);
			rate.rate_done_button.click();
			Wait.wait5Second();
			rateLogger.info("Again Click on Done Button ");
		}
		return test_steps;
	}
	public boolean SearchRate_RoomClasses(WebDriver driver, String RateName, boolean click)
			throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		boolean element = driver.findElements(By.cssSelector(OR.RatesPagesSize)).size() > 0;
		int element_size = 0;
		int index = 0;
		boolean isRateCreated = false;
		List<WebElement> RatesElements = null;
		if (!element) {
			// single page

			element_size = driver.findElements(By.cssSelector(OR.FindRateName)).size();

			if (element_size > 1) {
				RatesElements = driver.findElements(By.cssSelector(OR.FindRateName));
				for (int j = 0; j < element_size; j++) {
					if (RatesElements.get(j).getText().contains(RateName)) {
						isRateCreated = true;
						index = j;
						break;
					}
				}
			}
		} else {

			int size = driver.findElements(By.cssSelector(OR.RatesPagesSize)).size();

			for (int i = 0; i <= size; i++) {

				element_size = driver.findElements(By.cssSelector(OR.FindRateName)).size();
				isRateCreated = false;
				if (element_size > 1) {
					RatesElements = driver.findElements(By.cssSelector(OR.FindRateName));
					for (int j = 0; j < element_size; j++) {

						if (RatesElements.get(j).getText().equals(RateName)) {
							isRateCreated = true;
							index = j;
							assertEquals(isRateCreated, true, "Newely creatd rate does not find in rate list");
							break;
						}
					}

				}
				if (isRateCreated == false) {
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					// jse.executeScript("window.scrollBy(0,450)", "");
					Utility.ScrollToElement(driver.findElement(By.cssSelector(OR.RatesPagesSize)), driver);
					driver.findElements(By.cssSelector(OR.RatesPagesSize)).get(i).click();
					Wait.wait2Second();
				}

			}
		}

		if (click) {
			String RatePath = "//a[.='" + RateName + "']";
			WebElement CreatedRate = driver.findElement(By.xpath(RatePath));
			Utility.ScrollToElement(CreatedRate, driver);
			CreatedRate.click();
			Wait.WaitForElement(driver, OR.rateName);
			Wait.explicit_wait_visibilityof_webelement(rate.rateName, driver);
		}
		return isRateCreated;

	}

	/*public void Attach_RoomClasses(WebDriver driver) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		
		Wait.WaitForElement(driver, OR.rate_Associate_RoomClasses);
		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_RoomClasses, driver);
		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		rate.rate_Associate_RoomClasses.click();
		Thread.sleep(5000);
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		rate.click_All_RoomClasses.click();

		Wait.explicit_wait_xpath(OR.doneButton, driver);
		rate.doneButton.click();

	}*/
	
	public boolean Attach_RoomClasses(WebDriver driver) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);

		// driver.navigate().refresh();
		// Thread.sleep(5000);
		Wait.WaitForElement(driver, OR.rate_Associate_RoomClasses);
		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_RoomClasses, driver);
		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		rate.rate_Associate_RoomClasses.click();
		Thread.sleep(5000);
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		Wait.explicit_wait_visibilityof_webelement(rate.Select_RoomClass, driver);
		Select select = new Select(rate.Select_RoomClass);
		List<WebElement> options = select.getOptions();
		boolean isRoomClassExist = false;
		if (options.size() > 0) {
			isRoomClassExist = true;
			rate.click_All_RoomClasses.click();

			Wait.explicit_wait_xpath(OR.doneButton, driver);
			rate.doneButton.click();
		} else {
			driver.switchTo().defaultContent();
			rate.rateDialogCloseLink.click();
		}
		driver.switchTo().defaultContent();
		return isRoomClassExist;

	}

	public void Done_SaveButton(WebDriver driver) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);

		Wait.wait2Second();
		rate.rate_Save_Button.click();
		Wait.wait5Second();
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
		rate.rate_done_button.click();
		Wait.wait5Second();
	}


	public ArrayList<String> newRate_RoomClasses(WebDriver driver, String ratename, String maxAdults, String maxPersons,
			String baseAmount, String additionalAdult, String additionalChild, String rateDisplayName,
			String ratePolicy, String rateDescription, String RoomClass, String Season, int size) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_Inventory rate = new Elements_Inventory(driver);
		clickOnNewRateButton(driver, test_steps);
		provideRateName(driver, test_steps, ratename);
		String ratepan = new Select(rate.ratePlan).getFirstSelectedOption().getText();
		rateLogger.info("Selected Rate Plan : " + ratepan);
		test_steps.add("Selected Rate Plan : " + ratepan);
		String rateType = rate.getRateType.getText();
		rateLogger.info(" Selected Rate Type : " + rateType);
		test_steps.add(" Selected Rate Type : " + rateType);
		rate.maxAdults.sendKeys(maxAdults);
		test_steps.add("Max Adults : " + maxAdults);
		rate.maxPersons.sendKeys(maxPersons);
		test_steps.add("Max Persons : " + maxPersons);
		rate.baseAmount.sendKeys(baseAmount);
		test_steps.add("Base Amount : " + baseAmount);
		Wait.explicit_wait_xpath(OR.additionalAdult, driver);
		rate.additionalAdult.sendKeys(additionalAdult);
		test_steps.add("Additional Adults : " + additionalAdult);
		Wait.explicit_wait_xpath(OR.additionalChild, driver);
		rate.additionalChild.sendKeys(additionalChild);
		test_steps.add("Additional Child : " + additionalChild);
		Wait.explicit_wait_xpath(OR.rate_displayName, driver);
		rate.rate_displayName.sendKeys(rateDisplayName);
		test_steps.add("Rate Display Name : " + rateDisplayName);
		Wait.explicit_wait_xpath(OR.rate_policy, driver);
		rate.rate_policy.sendKeys(ratePolicy);
		test_steps.add("Rate Policy : " + ratePolicy);
		Wait.explicit_wait_xpath(OR.rate_description, driver);
		rate.rate_description.sendKeys(rateDescription);
		test_steps.add("Rate Description : " + rateDescription);

		// Attach session
		Utility.ScrollToElement(rate.rate_Associate_Seasons, driver);
		rate.rate_Associate_Seasons.click();
		test_steps.add("Associalte Seasons Button Clicked");
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		// rate.click_All_Seasons.click();
		new Select(rate.SelectSeason_Rate).selectByVisibleText(Season);
		test_steps.add("Select Season");
		rate.PickerButton.click();
		rate.doneButton.click();
		test_steps.add("Associalte Seasons Done Button Clicked");
		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_RoomClasses, driver);
		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		rate.rate_Associate_RoomClasses.click();
		test_steps.add("Associalte RoomClasses Button Clicked");
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		Wait.wait2Second();

		 rate.click_All_RoomClasses.click();
	//	new Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass);
		//rate.PickerButton.click();
		test_steps.add("Associalte All RoomClass ");
		Wait.explicit_wait_xpath(OR.doneButton, driver);
		rate.doneButton.click();
		test_steps.add("Associalte RoomClasses Done Button Clicked");
		Wait.wait2Second();
		driver.switchTo().defaultContent();

		Utility.ScrollToElement(rate.rate_Associate_Sources, driver);
		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_Sources, driver);
		rate.rate_Associate_Sources.click();
		test_steps.add("Associalte Sources Button Clicked");
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		rate.rate_select_Source.click();
		test_steps.add("Select Source");
		Wait.explicit_wait_visibilityof_webelement(rate.doneButton, driver);
		rate.doneButton.click();
		test_steps.add("Associalte Sources Done Button Clicked");
		Wait.wait2Second();
		driver.switchTo().defaultContent();

		Wait.explicit_wait_xpath(OR.rate_Save_Button, driver);
		rate.rate_Save_Button.click();
		Wait.wait3Second();
		rateLogger.info("Click on Save Button ");
		test_steps.add("Click on Save Button ");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			Wait.wait5Second();
			rateLogger.info(" Clicked on Done Button ");
			test_steps.add(" Clicked on Done Button ");
		} catch (Exception e) {
			driver.navigate().back();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_Save_Button, driver);
			rate.rate_Save_Button.click();
			Wait.wait3Second();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_done_button, driver);
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			Wait.wait5Second();
			rateLogger.info("Again Click on Done Button ");
			test_steps.add("Again Click on Done Button ");
		}
		return test_steps;
	}

	public boolean BeforeSearch(WebDriver driver) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.WaitForElement(driver, OR.click_goButton);
		Wait.explicit_wait_visibilityof_webelement(rate.click_goButton, driver);
		Wait.explicit_wait_elementToBeClickable(rate.click_goButton, driver);
		return rate.NoRecordMeet.size() > 0;
	}
	
	public ArrayList<String> newRate_RoomClasses(WebDriver driver, String ratename, String maxAdults, String maxPersons,
			String baseAmount, String additionalAdult, String additionalChild, String rateDisplayName,
			String ratePolicy, String rateDescription, String RoomClass, String Season) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_Inventory rate = new Elements_Inventory(driver);
		clickOnNewRateButton(driver, test_steps);
		rate.rateName.sendKeys(ratename);
		test_steps.add("Rate Name : " + ratename);
		String ratepan = new Select(rate.ratePlan).getFirstSelectedOption().getText();
		rateLogger.info("Selected Rate Plan : " + ratepan);
		test_steps.add("Selected Rate Plan : " + ratepan);
		String rateType = rate.getRateType.getText();
		rateLogger.info(" Selected Rate Type : " + rateType);
		test_steps.add(" Selected Rate Type : " + rateType);
		rate.maxAdults.sendKeys(maxAdults);
		test_steps.add("Max Adults : " + maxAdults);
		rate.maxPersons.sendKeys(maxPersons);
		test_steps.add("Max Persons : " + maxPersons);
		rate.baseAmount.sendKeys(baseAmount);
		test_steps.add("Base Amount : " + baseAmount);
		Wait.explicit_wait_xpath(OR.additionalAdult, driver);
		rate.additionalAdult.sendKeys(additionalAdult);
		test_steps.add("Additional Adults : " + additionalAdult);
		Wait.explicit_wait_xpath(OR.additionalChild, driver);
		rate.additionalChild.sendKeys(additionalChild);
		test_steps.add("Additional Child : " + additionalChild);
		Wait.explicit_wait_xpath(OR.rate_displayName, driver);
		rate.rate_displayName.sendKeys(rateDisplayName);
		test_steps.add("Rate Display Name : " + rateDisplayName);
		Wait.explicit_wait_xpath(OR.rate_policy, driver);
		rate.rate_policy.sendKeys(ratePolicy);
		test_steps.add("Rate Policy : " + ratePolicy);
		Wait.explicit_wait_xpath(OR.rate_description, driver);
		rate.rate_description.sendKeys(rateDescription);
		test_steps.add("Rate Description : " + rateDescription);

		// Attach session
		Utility.ScrollToElement(rate.rate_Associate_Seasons, driver);
		rate.rate_Associate_Seasons.click();
		test_steps.add("Associalte Seasons Button Clicked");
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		// rate.click_All_Seasons.click();
		Wait.explicit_wait_visibilityof_webelement_120(rate.SelectSeason_Rate, driver);
		Wait.explicit_wait_elementToBeClickable(rate.SelectSeason_Rate, driver);
		Utility.ScrollToElement(rate.SelectSeason_Rate, driver);
		new Select(rate.SelectSeason_Rate).selectByVisibleText(Season);
		test_steps.add("Select Season");
		Wait.explicit_wait_visibilityof_webelement_120(rate.PickerButton, driver);
		Wait.explicit_wait_elementToBeClickable(rate.PickerButton, driver);
		Utility.ScrollToElement(rate.PickerButton, driver);
		rate.PickerButton.click();
		Wait.explicit_wait_visibilityof_webelement_120(rate.doneButton, driver);
		Wait.explicit_wait_elementToBeClickable(rate.doneButton, driver);
		Utility.ScrollToElement(rate.doneButton, driver);
		rate.doneButton.click();
		test_steps.add("Associalte Seasons Done Button Clicked");
		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_RoomClasses, driver);
		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		rate.rate_Associate_RoomClasses.click();
		test_steps.add("Associalte RoomClasses Button Clicked");
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		Wait.wait2Second();
		Select select = new Select(rate.Select_RoomClass);
		List<WebElement> options = select.getOptions();
		boolean isRoomClassExist = false;
		if (options.size() > 0) {
			isRoomClassExist = true;
			Wait.explicit_wait_visibilityof_webelement_120(rate.click_All_RoomClasses, driver);
			Wait.explicit_wait_elementToBeClickable(rate.click_All_RoomClasses, driver);
			Utility.ScrollToElement(rate.click_All_RoomClasses, driver);
			rate.click_All_RoomClasses.click();
			// new
			// Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass);
			// rate.PickerButton.click();
			test_steps.add("Associalte All RoomClass ");
			// Wait.explicit_wait_xpath(OR.doneButton, driver);
			Wait.explicit_wait_visibilityof_webelement_120(rate.doneButton, driver);
			Wait.explicit_wait_elementToBeClickable(rate.doneButton, driver);
			Utility.ScrollToElement(rate.doneButton, driver);
			rate.doneButton.click();
			test_steps.add("Associalte RoomClasses Done Button Clicked");
			Wait.wait2Second();
		} else {
			Wait.explicit_wait_visibilityof_webelement_120(rate.rateDialogCloseLink, driver);
			Wait.explicit_wait_elementToBeClickable(rate.rateDialogCloseLink, driver);
			Utility.ScrollToElement(rate.rateDialogCloseLink, driver);
			rate.rateDialogCloseLink.click();
			test_steps.add("No room class exist for attch with new rate");
		}

		driver.switchTo().defaultContent();

		Utility.ScrollToElement(rate.rate_Associate_Sources, driver);
		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_Sources, driver);
		rate.rate_Associate_Sources.click();
		test_steps.add("Associalte Sources Button Clicked");
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		rate.rate_select_Source.click();
		test_steps.add("Select Source");
		Wait.explicit_wait_visibilityof_webelement(rate.doneButton, driver);
		rate.doneButton.click();
		test_steps.add("Associalte Sources Done Button Clicked");
		Wait.wait2Second();
		driver.switchTo().defaultContent();

		Wait.explicit_wait_xpath(OR.rate_Save_Button, driver);
		rate.rate_Save_Button.click();
		Wait.wait3Second();
		rateLogger.info("Click on Save Button ");
		test_steps.add("Click on Save Button ");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			Wait.wait5Second();
			rateLogger.info(" Clicked on Done Button ");
			test_steps.add(" Clicked on Done Button ");
		} catch (Exception e) {
			driver.navigate().back();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_Save_Button, driver);
			rate.rate_Save_Button.click();
			Wait.wait3Second();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_done_button, driver);
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			Wait.wait5Second();
			rateLogger.info("Again Click on Done Button ");
			test_steps.add("Again Click on Done Button ");
		}
		return test_steps;
	}
	
	public ArrayList<String> CreateRate(WebDriver driver, String ratename, String maxAdults, String maxPersons,
			String baseAmount, String additionalAdult, String additionalChild, String rateDisplayName,
			String ratePolicy, String rateDescription, String RoomClass,String ratePlan) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_Inventory rate = new Elements_Inventory(driver);
		clickOnNewRateButton(driver, test_steps);
		rate.rateName.sendKeys(ratename);
		test_steps.add("Rate Name : " + ratename);
		new Select(rate.ratePlan).selectByVisibleText(ratePlan);
		rateLogger.info("Selected Rate Plan : " + ratePlan);
		test_steps.add("Selected Rate Plan : " + ratePlan);
		assertEquals(new Select(rate.ratePlan).getFirstSelectedOption().getText(),ratePlan,"Failed Rate Plan not selected");
		String rateType = rate.getRateType.getText();
		rateLogger.info(" Selected Rate Type : " + rateType);
		test_steps.add(" Selected Rate Type : " + rateType);
		rate.maxAdults.sendKeys(maxAdults);
		test_steps.add("Max Adults : " + maxAdults);
		rate.maxPersons.sendKeys(maxPersons);
		test_steps.add("Max Persons : " + maxPersons);
		rate.baseAmount.sendKeys(baseAmount);
		test_steps.add("Base Amount : " + baseAmount);
		Wait.explicit_wait_xpath(OR.additionalAdult, driver);
		rate.additionalAdult.sendKeys(additionalAdult);
		test_steps.add("Additional Adults : " + additionalAdult);
		Wait.explicit_wait_xpath(OR.additionalChild, driver);
		rate.additionalChild.sendKeys(additionalChild);
		test_steps.add("Additional Child : " + additionalChild);
		Wait.explicit_wait_xpath(OR.rate_displayName, driver);
		rate.rate_displayName.sendKeys(rateDisplayName);
		test_steps.add("Rate Display Name : " + rateDisplayName);
		Wait.explicit_wait_xpath(OR.rate_policy, driver);
		rate.rate_policy.sendKeys(ratePolicy);
		test_steps.add("Rate Policy : " + ratePolicy);
		Wait.explicit_wait_xpath(OR.rate_description, driver);
		rate.rate_description.sendKeys(rateDescription);
		test_steps.add("Rate Description : " + rateDescription);

		// Attach session
	//.ScrollToElement(rate.rate_Associate_Seasons, driver);
	//	rate.rate_Associate_Seasons.click();
	//	test_steps.add("Associalte Seasons Button Clicked");
		//Wait.wait2Second();
		//driver.switchTo().frame("dialog-body0");
		//driver.switchTo().frame("frmWaitHost");
		
		Wait.WaitForElement(driver, OR.rate_Associate_Seasons);
		Wait.waitForElementToBeVisibile(By.xpath(OR.rate_Associate_Seasons), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Associate_Seasons), driver);

		Utility.ScrollToElement_NoWait(rate.rate_Associate_Seasons, driver);
		rate.rate_Associate_Seasons.click();
		test_steps.add("Associalte Seasons Button Clicked");

		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));
		
		rateLogger.info(" Switched to Frame ");
		Wait.WaitForElement(driver, OR.click_All_Seasons);
		Wait.waitForElementToBeClickable(By.xpath(OR.click_All_Seasons), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.click_All_Seasons), driver);
		rate.click_All_Seasons.click();
		test_steps.add("All Seasons Button Clicked");
		rate.doneButton.click();
		test_steps.add("Associalte Seasons Done Button Clicked");
		//Wait.wait3Second();
		driver.switchTo().defaultContent();
				

		//Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_RoomClasses, driver);
		Wait.WaitForElement(driver, OR.rate_Associate_RoomClasses);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Associate_RoomClasses), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Associate_RoomClasses), driver);
		Utility.ScrollToElement_NoWait(rate.rate_Associate_RoomClasses, driver);
		rate.rate_Associate_RoomClasses.click();
		test_steps.add("Associalte RoomClasses Button Clicked");
		
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));
		rateLogger.info(" Switched to Frame ");
		
		Wait.WaitForElement_ID(driver, OR.SelectAssociateRoomClass);
		Wait.waitForElementToBeClickable(By.id(OR.SelectAssociateRoomClass), driver);
		Wait.waitForElementToBeClickable(By.id(OR.SelectAssociateRoomClass), driver);
		
		new Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass);
		rate.PickerButton.click();
		test_steps.add("Associalte RoomClass Selected : " + RoomClass);
		Wait.explicit_wait_xpath(OR.doneButton, driver);
		rate.doneButton.click();
		test_steps.add("Associalte RoomClasses Done Button Clicked");
		driver.switchTo().defaultContent();

		
		Wait.WaitForElement(driver, OR.rate_Associate_Sources);
		Wait.waitForElementToBeVisibile(By.xpath(OR.rate_Associate_Sources), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Associate_Sources), driver);
		Utility.ScrollToElement_NoWait(rate.rate_Associate_Sources, driver);
		rate.rate_Associate_Sources.click();
		test_steps.add("Associalte Sources Button Clicked");
		
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));
		rateLogger.info(" Switched to Frame ");
		rateLogger.info(" Switched to Frame ");
		
		Wait.WaitForElement(driver, OR.rate_select_Source);
		Wait.waitForElementToBeVisibile(By.xpath(OR.rate_select_Source), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_select_Source), driver);
		rate.rate_select_Source.click();
		test_steps.add("Select Source");
		rate.doneButton.click();
		test_steps.add("Associalte Sources Done Button Clicked");
		driver.switchTo().defaultContent();

		Wait.WaitForElement(driver, OR.rate_Save_Button);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Save_Button), driver);
		rate.rate_Save_Button.click();
		rateLogger.info("Click on Save Button ");
		test_steps.add("Click on Save Button ");
		
		Wait.WaitForElement(driver, OR.rate_done_button);
		Wait.waitForElementToBeVisibile(By.xpath(OR.rate_done_button), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_done_button), driver);

		rate.rate_done_button.click();
		rateLogger.info(" Clicked on Done Button ");
		test_steps.add(" Clicked on Done Button ");

		Wait.WaitForElement(driver, OR.newRate);
		Wait.waitForElementToBeVisibile(By.xpath(OR.newRate), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.newRate), driver);
	
		return test_steps;
	}
	
//	Updated by Naveen Kumar
	public void clickOnNewRateButton(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.newRate), driver);
		Utility.ScrollToElement(rate.newRate, driver);
		rate.newRate.click();
		test_steps.add("Clicking on <b>New Rate</b> button");
		Wait.waitForElementToBeVisibile(By.xpath(OR.rateName), driver);		
	}
	
	public void EnterRateName(WebDriver driver, String ratename, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(rate.rateName, driver);
		Utility.ScrollToElement(rate.rateName, driver);
		rate.rateName.sendKeys(ratename);
		test_steps.add("Enter Rate Name : " + ratename);
	}
	
	public void SelectRatePlan(WebDriver driver,String ratePlan, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(rate.ratePlan, driver);
		Utility.ScrollToElement(rate.ratePlan, driver);
		new Select(rate.ratePlan).selectByVisibleText(ratePlan);
		rateLogger.info("SelectRate Plan : " + ratePlan);
		test_steps.add("Select Rate Plan : " + ratePlan);
		assertEquals(new Select(rate.ratePlan).getFirstSelectedOption().getText(),ratePlan,"Failed Rate Plan not selected");
		
	}
	public String GetRateType(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(rate.getRateType, driver);
		Utility.ScrollToElement(rate.getRateType, driver);
		String rateType = rate.getRateType.getText();
		rateLogger.info("Selected Rate Type : " + rateType);
		test_steps.add("Selected Rate Type : " + rateType);
		return rateType;
	}
	public void EnterMaxAdults(WebDriver driver, String maxAdults, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(rate.maxAdults, driver);
		Utility.ScrollToElement(rate.maxAdults, driver);
		rate.maxAdults.sendKeys(maxAdults);
		test_steps.add("Enter Max Adults : " + maxAdults);
	}
	public void EnterMaxPersons(WebDriver driver,String maxPersons, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(rate.maxPersons, driver);
		Utility.ScrollToElement(rate.maxPersons, driver);
		rate.maxPersons.sendKeys(maxPersons);
		test_steps.add("Enter Max Persons : " + maxPersons);
	}
	
	public void EnterBaseAmount(WebDriver driver,String baseAmount, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(rate.baseAmount, driver);
		Utility.ScrollToElement(rate.baseAmount, driver);
		rate.baseAmount.sendKeys(baseAmount);
		test_steps.add("Enter Base Amount : " + baseAmount);
	}
	public void EnterAdditionalAdult(WebDriver driver, String additionalAdult, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(rate.additionalAdult, driver);
		Utility.ScrollToElement(rate.additionalAdult, driver);
		rate.additionalAdult.sendKeys(additionalAdult);
		test_steps.add("Enter Additional Adults : " + additionalAdult);
	}
	
	public void EnterAdditionalChild(WebDriver driver, String additionalChild,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(rate.additionalChild, driver);
		Utility.ScrollToElement(rate.additionalChild, driver);
		rate.additionalChild.sendKeys(additionalChild);
		test_steps.add("Enter Additional Child : " + additionalChild);
	}
	public void EnterRateDisplayName(WebDriver driver, String rateDisplayName, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(rate.rate_displayName, driver);
		Utility.ScrollToElement(rate.rate_displayName, driver);
		rate.rate_displayName.sendKeys(rateDisplayName);
		test_steps.add("Enter Rate Display Name : " + rateDisplayName);
	}
	public void EnterRatePolicy(WebDriver driver,String ratePolicy, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(rate.rate_policy, driver);
		Utility.ScrollToElement(rate.rate_policy, driver);
		rate.rate_policy.sendKeys(ratePolicy);
		test_steps.add("Enter Rate Policy : " + ratePolicy);
	}
	public void EnterRateDescription(WebDriver driver,String rateDescription,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(rate.rate_description, driver);
		Utility.ScrollToElement(rate.rate_description, driver);
		rate.rate_description.sendKeys(rateDescription);
		test_steps.add("Enter Rate Description : " + rateDescription);
	}


	 public void AssociateSeason(WebDriver driver,String Season, ArrayList<String> test_steps) throws InterruptedException {
	        Elements_Inventory rate = new Elements_Inventory(driver);
	        Wait.waitForElementToBeClickable(By.xpath(OR.rate_Associate_Seasons), driver);
	        Utility.ScrollToElement(rate.rate_Associate_Seasons, driver);
	        rate.rate_Associate_Seasons.click();
	        test_steps.add("Associalte Seasons Button Clicked");
	        Wait.waitForFrame(By.xpath(OR.ratesSeasonPopupFrame1), driver);
	        Wait.waitForFrame(By.xpath(OR.ratesSeasonPopupFrame2), driver);
	        String path = "//*[@id='lstSeasons']//option[contains(text(),'" + Season + "')]";
	        Wait.waitForElementToBeVisibile(By.xpath(path), driver);       
	        WebElement season = driver.findElement(By.xpath(path));
	        Utility.ScrollToElement(season, driver);
	        season.click();
	        rate.PickerButton.click();
	        rate.doneButton.click();
	        rateLogger.info("Select " + Season + " Season and Click Done");
	        test_steps.add("Select <b>" + Season + "</b> Season and Click Done");       
	        driver.switchTo().defaultContent();
	    }
	 public void AssociateRoomClass(WebDriver driver,String RoomClass,ArrayList<String> test_steps) throws InterruptedException {

		 

	        Elements_Inventory rate = new Elements_Inventory(driver);
	        Wait.waitForElementToBeClickable(By.xpath(OR.rate_Associate_RoomClasses), driver);
	        Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
	        rate.rate_Associate_RoomClasses.click();
	        test_steps.add("Associalte RoomClasses Button Clicked");
	        Wait.waitForFrame(By.xpath(OR.ratesSeasonPopupFrame1), driver);
	        Wait.waitForFrame(By.xpath(OR.ratesSeasonPopupFrame2), driver);
	        new Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass);
	        rate.PickerButton.click();
	        test_steps.add("Selected RoomClass : <b>" + RoomClass+"</b>");
	        Wait.waitForElementToBeClickable(By.xpath(OR.doneButton), driver);
	        rate.doneButton.click();
	        test_steps.add("Click Associalte RoomClasses Done Button");
	        driver.switchTo().defaultContent();
	    }
	 public void AssociateSource(WebDriver driver,String Source,ArrayList<String> test_steps) throws InterruptedException {

		 

	        Elements_Inventory rate = new Elements_Inventory(driver);
	        Wait.waitForElementToBeClickable(By.xpath(OR.rate_Associate_RoomClasses), driver);
	        Wait.waitForElementToBeClickable(By.xpath(OR.rate_Associate_Sources), driver);
	        Utility.ScrollToElement(rate.rate_Associate_Sources, driver);
	        rate.rate_Associate_Sources.click();
	        test_steps.add("Associalte Sources Button Clicked");
	        Wait.waitForFrame(By.xpath(OR.ratesSeasonPopupFrame1), driver);
	        Wait.waitForFrame(By.xpath(OR.ratesSeasonPopupFrame2), driver);
	        String path ="//td[text()='" + Source + "']//parent::tr/td/input";
	        Wait.waitForElementToBeVisibile(By.xpath(path), driver);
	        WebElement element = driver.findElement(By.xpath(path));
	        Utility.ScrollToElement(element, driver);
	        element.click();
	        test_steps.add("Select Source '" + Source + "'");
	        test_steps.add("Select Source");
	        Wait.waitForElementToBeVisibile(By.xpath(OR.doneButton), driver);
	        rate.doneButton.click();
	        test_steps.add("Associalte Sources Done Button Clicked");
	        driver.switchTo().defaultContent();
	    }

	/*public void AssociateSeason(WebDriver driver,String Season, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Associate_Seasons), driver);
		Utility.ScrollToElement(rate.rate_Associate_Seasons, driver);
		rate.rate_Associate_Seasons.click();
		test_steps.add("Associalte Seasons Button Clicked");
		Wait.waitForFrame(By.xpath(OR.ratesSeasonPopupFrame1), driver);
		Wait.waitForFrame(By.xpath(OR.ratesSeasonPopupFrame2), driver);
		String path = "//*[@id='lstSeasons']//option[contains(text(),'" + Season + "')]";
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);		
		WebElement season = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(season, driver);
		season.click();
		rate.PickerButton.click();
		rate.doneButton.click();
		rateLogger.info("Select " + Season + " Season and Click Done");
		test_steps.add("Select <b>" + Season + "</b> Season and Click Done");		
		driver.switchTo().defaultContent();
	}
=======
	public void AssociateSeason(WebDriver driver,String Season, ArrayList<String> test_steps) throws InterruptedException {

        Elements_Inventory rate = new Elements_Inventory(driver);
        Wait.waitForElementToBeClickable(By.xpath(OR.rate_Associate_Seasons), driver);
        Utility.ScrollToElement(rate.rate_Associate_Seasons, driver);
        rate.rate_Associate_Seasons.click();
        test_steps.add("Associalte Seasons Button Clicked");
        Wait.waitForFrame(By.xpath(OR.ratesSeasonPopupFrame1), driver);
        Wait.waitForFrame(By.xpath(OR.ratesSeasonPopupFrame2), driver);
        String path = "//*[@id='lstSeasons']//option[contains(text(),'" + Season + "')]";
        Wait.waitForElementToBeVisibile(By.xpath(path), driver);       
        WebElement season = driver.findElement(By.xpath(path));
        Utility.ScrollToElement(season, driver);
        season.click();
        rate.PickerButton.click();
        rate.doneButton.click();
        rateLogger.info("Select " + Season + " Season and Click Done");
        test_steps.add("Select <b>" + Season + "</b> Season and Click Done");       
        driver.switchTo().defaultContent();
    }
 
	
	
>>>>>>> 4d0b92c56342050ab90f2408a75a64799debf3f9
	public void AssociateRoomClass(WebDriver driver,String RoomClass,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Associate_RoomClasses), driver);
		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		rate.rate_Associate_RoomClasses.click();
		test_steps.add("Associalte RoomClasses Button Clicked");
		Wait.waitForFrame(By.xpath(OR.ratesSeasonPopupFrame1), driver);
		Wait.waitForFrame(By.xpath(OR.ratesSeasonPopupFrame2), driver);
		new Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass);
		rate.PickerButton.click();
		test_steps.add("Selected RoomClass : <b>" + RoomClass+"</b>");
		Wait.waitForElementToBeClickable(By.xpath(OR.doneButton), driver);
		rate.doneButton.click();
		test_steps.add("Click Associalte RoomClasses Done Button");
		driver.switchTo().defaultContent();
	}
	public void AssociateSource(WebDriver driver,String Source,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Associate_RoomClasses), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Associate_Sources), driver);
		Utility.ScrollToElement(rate.rate_Associate_Sources, driver);
		rate.rate_Associate_Sources.click();
		test_steps.add("Associalte Sources Button Clicked");
		Wait.waitForFrame(By.xpath(OR.ratesSeasonPopupFrame1), driver);
		Wait.waitForFrame(By.xpath(OR.ratesSeasonPopupFrame2), driver);
		String path ="//td[text()='" + Source + "']//parent::tr/td/input";
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		element.click();
		test_steps.add("Select Source '" + Source + "'");
		test_steps.add("Select Source");
		Wait.waitForElementToBeVisibile(By.xpath(OR.doneButton), driver);
		rate.doneButton.click();
		test_steps.add("Associalte Sources Done Button Clicked");
		driver.switchTo().defaultContent();
	}
<<<<<<< HEAD
*/

	public void Save_DoneRate(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(rate.rate_Save_Button, driver);
		Wait.waitUntilPresenceOfElementLocated(OR.rate_Save_Button, driver);
		rate.rate_Save_Button.click();
		Wait.wait3Second();
		rateLogger.info("Click on Save Button ");
		test_steps.add("Click on Save Button ");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			Wait.wait5Second();
			rateLogger.info(" Clicked on Done Button ");
			test_steps.add(" Clicked on Done Button ");
		} catch (Exception e) {
			driver.navigate().back();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_Save_Button, driver);
			rate.rate_Save_Button.click();
			Wait.wait3Second();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_done_button, driver);
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			Wait.wait5Second();
			rateLogger.info("Again Click on Done Button ");
			test_steps.add("Again Click on Done Button ");
		}
	}
	public ArrayList<String> DeleteRate(WebDriver driver, String RateName) throws InterruptedException {
		  
		Elements_Inventory rate = new Elements_Inventory(driver);
  		boolean element = driver.findElements(By.cssSelector(OR.RatesPagesSize)).size() > 0;
  		boolean isFind = false;
  		String rateCheckboxpath = "//a[starts-with(text(),'"+RateName+"')]//..//..//td//span//input";
  		ArrayList<String> test_steps = new ArrayList<>();
  		
  		if (!element) {
  		// single page
  			System.out.println("in if");
  			List<WebElement> rateNameListSize = driver.findElements(By.xpath(rateCheckboxpath));
  			for (int i = 0; i < rateNameListSize.size(); i++    ) {
  				Utility.ScrollToElement_NoWait(rateNameListSize.get(i), driver);
  				rateNameListSize.get(i).click();
  				
  			}
  			test_steps.add("Total number of rates select for delete: " +rateNameListSize.size());
  			rate.deleteRate.click();
  			Wait.explicit_wait_visibilityof_webelement_120(rate.deleteRate, driver);
  			Wait.explicit_wait_elementToBeClickable(rate.deleteRate, driver);
  			
  		} else {
  
  			// for more than one page
  			System.out.println("In multiple page"+rate.RatesPagesSize.size());
  			Wait.explicit_wait_visibilityof_webelement_120(rate.deleteRate, driver);
  			Wait.explicit_wait_elementToBeClickable(rate.deleteRate, driver);
  			Utility.ScrollToElement(rate.RatesPagesSize.get(rate.RatesPagesSize.size()-1), driver);
  			rate.RatesPagesSize.get(rate.RatesPagesSize.size()-1).click();
  			
  			for (int i = rate.RatesPagesSize.size(); i>=0; i--) {
  				System.out.println("In for" + i);
  				
  				Wait.explicit_wait_visibilityof_webelement_120(rate.deleteRate, driver);
  				Wait.explicit_wait_elementToBeClickable(rate.deleteRate, driver);
  
  				List<WebElement> rateNameListSize = driver.findElements(By.xpath(rateCheckboxpath));
  				isFind = false;
  				for (int j = 0; j < rateNameListSize.size(); j++   ) {
  					Utility.ScrollToElement_NoWait(rateNameListSize.get(j), driver);
  					rateNameListSize.get(j).click();
  					isFind = true;
  				}
  				if (isFind) {
  					rate.deleteRate.click();
  					Wait.explicit_wait_visibilityof_webelement_120(rate.deleteRate, driver);
  					Wait.explicit_wait_elementToBeClickable(rate.deleteRate, driver);
  				}
  				try {
  					if (i>0) {
  						Utility.ScrollToElement(rate.RatesPagesSize.get(rate.RatesPagesSize.size()-1), driver);
  						rate.RatesPagesSize.get(i-1).click();
  						Wait.explicit_wait_visibilityof_webelement_120(rate.deleteRate, driver);
  						Wait.explicit_wait_elementToBeClickable(rate.deleteRate, driver);
  					}
  				} catch (Exception e) {
  					// TODO: handle exception
  				}
  				
  			}
  		}
  		return test_steps;
  	}
	
	public ArrayList<String> enterRateInfo(WebDriver driver, String ratename, String maxAdults, String maxPersons,
			String baseAmount, String additionalAdult, String additionalChild, String rateDisplayName,
			String ratePolicy, String rateDescription, String RoomClass,String Season) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_Inventory rate = new Elements_Inventory(driver);
		clickOnNewRateButton(driver, test_steps);
		rate.rateName.sendKeys(ratename);
		test_steps.add("Rate Name : " + ratename);
		String ratepan = new Select(rate.ratePlan).getFirstSelectedOption().getText();
		rateLogger.info("Selected Rate Plan : " + ratepan);
		test_steps.add("Selected Rate Plan : " + ratepan);
		String rateType = rate.getRateType.getText();
		rateLogger.info(" Selected Rate Type : " + rateType);
		test_steps.add(" Selected Rate Type : " + rateType);
		rate.maxAdults.sendKeys(maxAdults);
		test_steps.add("Max Adults : " + maxAdults);
		rate.maxPersons.sendKeys(maxPersons);
		test_steps.add("Max Persons : " + maxPersons);
		rate.baseAmount.sendKeys(baseAmount);
		test_steps.add("Base Amount : " + baseAmount);
		Wait.explicit_wait_xpath(OR.additionalAdult, driver);
		rate.additionalAdult.sendKeys(additionalAdult);
		test_steps.add("Additional Adults : " + additionalAdult);
		Wait.explicit_wait_xpath(OR.additionalChild, driver);
		rate.additionalChild.sendKeys(additionalChild);
		test_steps.add("Additional Child : " + additionalChild);
		Wait.explicit_wait_xpath(OR.rate_displayName, driver);
		rate.rate_displayName.sendKeys(rateDisplayName);
		test_steps.add("Rate Display Name : " + rateDisplayName);
		Wait.explicit_wait_xpath(OR.rate_policy, driver);
		rate.rate_policy.sendKeys(ratePolicy);
		test_steps.add("Rate Policy : " + ratePolicy);
		Wait.explicit_wait_xpath(OR.rate_description, driver);
		rate.rate_description.sendKeys(rateDescription);
		test_steps.add("Rate Description : " + rateDescription);
		// Attach session
		Utility.ScrollToElement(rate.rate_Associate_Seasons, driver);
		rate.rate_Associate_Seasons.click();
		test_steps.add("Associate Seasons Button Clicked");
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		new Select(rate.Select_All_Seasons).selectByVisibleText(Season);
		rate.click_One_Season.click();
		test_steps.add("Selected Season : " + Season);
		rate.doneButton.click();
		test_steps.add("Associate Seasons Done Button Clicked");
		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_RoomClasses, driver);
		Wait.explicit_wait_visibilityof_webelement_120(rate.rate_Associate_RoomClasses, driver);
		Wait.explicit_wait_elementToBeClickable(rate.rate_Associate_RoomClasses, driver);
		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		rate.rate_Associate_RoomClasses.click();
		test_steps.add("Associate RoomClasses Button Clicked");
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		Wait.wait2Second();

		// rate.click_All_RoomClasses.click();
		Wait.explicit_wait_visibilityof_webelement_120(rate.SelectAssociateRoomClass, driver);
		Wait.explicit_wait_elementToBeClickable(rate.SelectAssociateRoomClass, driver);
		new Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass);
		rate.PickerButton.click();
		test_steps.add("Associate RoomClass Selected : " + RoomClass);
		Wait.explicit_wait_xpath(OR.doneButton, driver);
		rate.doneButton.click();
		test_steps.add("Associate RoomClasses Done Button Clicked");
		Wait.wait2Second();
		driver.switchTo().defaultContent();

		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_Sources, driver);
		Wait.explicit_wait_visibilityof_webelement_120(rate.rate_Associate_Sources, driver);
		Wait.explicit_wait_elementToBeClickable(rate.rate_Associate_Sources, driver);
		Utility.ScrollToElement(rate.rate_Associate_Sources, driver);
		rate.rate_Associate_Sources.click();
		test_steps.add("Associate Sources Button Clicked");
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		Wait.explicit_wait_visibilityof_webelement_120(rate.rate_select_Source, driver);
		Wait.explicit_wait_elementToBeClickable(rate.rate_select_Source, driver);
		Utility.ScrollToElement(rate.rate_select_Source, driver);
		rate.rate_select_Source.click();
		test_steps.add("Select Source");
		Wait.explicit_wait_visibilityof_webelement_120(rate.doneButton, driver);
		rate.doneButton.click();
		test_steps.add("Associate Sources Done Button Clicked");
		Wait.wait2Second();
		driver.switchTo().defaultContent();

		Wait.explicit_wait_xpath(OR.rate_Save_Button, driver);
		Wait.explicit_wait_visibilityof_webelement_120(rate.rate_Save_Button, driver);
		Wait.explicit_wait_elementToBeClickable(rate.rate_Save_Button, driver);
		Utility.ScrollToElement(rate.rate_Save_Button, driver);
		rate.rate_Save_Button.click();
		Wait.wait3Second();
		rateLogger.info("Click on Save Button ");
		test_steps.add("Click on Save Button ");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			Wait.wait5Second();
			rateLogger.info(" Clicked on Done Button ");
			test_steps.add(" Clicked on Done Button ");
		} catch (Exception e) {
			driver.navigate().back();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_Save_Button, driver);
			rate.rate_Save_Button.click();
			Wait.wait3Second();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_done_button, driver);
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.elementToBeClickable(rate.rate_done_button));
			rate.rate_done_button.click();
			Wait.wait5Second();
			rateLogger.info("Again Click on Done Button ");
			test_steps.add("Again Click on Done Button ");
		}
		return test_steps;
	}
	
	public ArrayList<String> deleteRateIfExist(WebDriver driver,String RateName,ArrayList<String> test_steps) throws InterruptedException {
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.wait2Second();
		String ratePath ="(//a[contains(text(),'"+RateName+"')]/../preceding-sibling::td/span/input)";
		List<WebElement> listSiz = driver.findElements(By.xpath(ratePath));
		boolean isRateCreated = false;
		System.out.println("Total listSiz.size(): "+listSiz.size() );
		
		if(listSiz.size() > 0){
			test_steps.add(listSiz.size() +" Rate already exist with a name: "+RateName);
			isRateCreated = true;
			int count = 0;
			while(count < listSiz.size()){
				System.out.println("Count Now: "+count);
			
				ratePath ="(//a[contains(text(),'"+RateName+"')]/../preceding-sibling::td/span/input)";
				WebElement rateElement = driver.findElement(By.xpath(ratePath));
				System.out.println("listSiz.get(i): "+rateElement);
				
				Utility.ScrollToElement(rateElement, driver);
				driver.findElement(By.xpath(ratePath)).click();
				Wait.wait2Second();
				
				Wait.explicit_wait_elementToBeClickable(rate.deleteRate, driver);
				rate.deleteRate.click();
				rateLogger.info("Sucessfully deleted the Rate: "+RateName);
				test_steps.add("Sucessfully deleted the Rate");			
				count++;
			}
		}	
		else{
			test_steps.add("Rate with a name "+RateName+" does not exist");
			rateLogger.info("Rate with a name "+RateName+" does not exist");		
		}	
		return test_steps;
		
	}	

	public ArrayList<String> EnterBasicInfo(WebDriver driver, String ratename, String maxAdults, String maxPersons,
			String baseAmount, String additionalAdult, String additionalChild, String rateDisplayName,
			String ratePolicy, String rateDescription) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Inventory rate = new Elements_Inventory(driver);
		clickOnNewRateButton(driver, test_steps);
		rate.rateName.sendKeys(ratename);
		test_steps.add("Rate Name : " + ratename);
		String ratepan = new Select(rate.ratePlan).getFirstSelectedOption().getText();
		rateLogger.info("Selected Rate Plan : " + ratepan);
		test_steps.add("Selected Rate Plan : " + ratepan);
		String rateType = rate.getRateType.getText();
		rateLogger.info(" Selected Rate Type : " + rateType);
		test_steps.add(" Selected Rate Type : " + rateType);
		rate.maxAdults.sendKeys(maxAdults);
		test_steps.add("Max Adults : " + maxAdults);
		rate.maxPersons.sendKeys(maxPersons);
		test_steps.add("Max Persons : " + maxPersons);
		rate.baseAmount.sendKeys(baseAmount);
		test_steps.add("Base Amount : " + baseAmount);
		Wait.explicit_wait_xpath(OR.additionalAdult, driver);
		rate.additionalAdult.sendKeys(additionalAdult);
		test_steps.add("Additional Adults : " + additionalAdult);
		Wait.explicit_wait_xpath(OR.additionalChild, driver);
		rate.additionalChild.sendKeys(additionalChild);
		test_steps.add("Additional Child : " + additionalChild);
		Wait.explicit_wait_xpath(OR.rate_displayName, driver);
		rate.rate_displayName.sendKeys(rateDisplayName);
		test_steps.add("Rate Display Name : " + rateDisplayName);
		Wait.explicit_wait_xpath(OR.rate_policy, driver);
		rate.rate_policy.sendKeys(ratePolicy);
		test_steps.add("Rate Policy : " + ratePolicy);
		Wait.explicit_wait_xpath(OR.rate_description, driver);
		rate.rate_description.sendKeys(rateDescription);
		test_steps.add("Rate Description : " + rateDescription);

		return test_steps;
	}

	public ArrayList<String> SelectSeason(WebDriver driver) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_elementToBeClickable(rate.rate_Associate_Seasons, driver);
		Utility.ScrollToElement_NoWait(rate.rate_Associate_Seasons, driver);
		rate.rate_Associate_Seasons.click();
		test_steps.add("Associate Seasons Button Clicked");
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		
		Wait.explicit_wait_visibilityof_webelement(rate.click_All_Seasons, driver);
		Wait.explicit_wait_elementToBeClickable(rate.click_All_Seasons, driver);
		rate.click_All_Seasons.click();
		test_steps.add("All Seasons Button Clicked");
		rate.doneButton.click();
		test_steps.add("Associate Seasons Done Button Clicked");
		driver.switchTo().defaultContent();
		test_steps.add("Attached season with rate successfully");
		Wait.wait1Second();
		return test_steps;
	}

	public ArrayList<String> SelectRoomClass(WebDriver driver, String RoomClass) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.wait2Second();
		Wait.WaitForElement(driver, OR.rate_Associate_RoomClasses);
		Wait.explicit_wait_visibilityof_webelement(rate.rate_Associate_RoomClasses, driver);
		Wait.explicit_wait_elementToBeClickable(rate.rate_Associate_RoomClasses, driver);
		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		rate.rate_Associate_RoomClasses.click();
		test_steps.add("Associate RoomClasses Button Clicked");
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		
		Wait.explicit_wait_elementToBeClickable(rate.SelectAssociateRoomClass, driver);
		new Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass);
		rate.PickerButton.click();
		test_steps.add("Associate RoomClass Selected : " + RoomClass);
		Wait.explicit_wait_xpath(OR.doneButton, driver);
		rate.doneButton.click();
		test_steps.add("Associate RoomClasses Done Button Clicked");
		driver.switchTo().defaultContent();
		test_steps.add("Attached room claess "+RoomClass+" with rate successfully");
		Wait.wait1Second();
		return test_steps;
	}
	
	public ArrayList<String> AttachSource(WebDriver driver) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.wait2Second();
		Wait.WaitForElement(driver, OR.rate_Associate_Sources);
		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_Sources, driver);
		Wait.explicit_wait_elementToBeClickable(rate.rate_Associate_Sources, driver);
		Utility.ScrollToElement_NoWait(rate.rate_Associate_Sources, driver);

		rate.rate_Associate_Sources.click();
		test_steps.add("Associate Sources Button Clicked");
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		Wait.explicit_wait_visibilityof_webelement(rate.rate_select_Source, driver);
		Wait.explicit_wait_elementToBeClickable(rate.rate_select_Source, driver);
		rate.rate_select_Source.click();
		test_steps.add("Select Source");
		Wait.explicit_wait_visibilityof_webelement(rate.doneButton, driver);
		rate.doneButton.click();
		test_steps.add("Associate Sources Done Button Clicked");
		driver.switchTo().defaultContent();	
		Wait.wait1Second();
		return test_steps;
	}
	
	public void SaveButton(WebDriver driver) throws InterruptedException {
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.WaitForElement(driver,OR.rate_Save_Button);
		Wait.explicit_wait_visibilityof_webelement(rate.rate_Save_Button, driver);
		Wait.explicit_wait_xpath(OR.rate_Save_Button, driver);
		rate.rate_Save_Button.click();
	
		Wait.explicit_wait_visibilityof_webelement(rate.rate_done_button, driver);
		Wait.explicit_wait_elementToBeClickable(rate.rate_done_button, driver);
	}
	public ArrayList<String> deleteRates(WebDriver driver, String RateName) throws InterruptedException {
		  
  		Elements_Inventory rate = new Elements_Inventory(driver);
  		boolean element = driver.findElements(By.cssSelector(OR.RatesPagesSize)).size() > 0;
  		boolean isFind = false;
  		String rateCheckboxpath = "//a[starts-with(text(),'"+RateName+"')]//..//..//td//span//input";
  		ArrayList<String> test_steps = new ArrayList<>();
  		
  		if (!element) {
  		// single page
  			System.out.println("in if");
  			List<WebElement> rateNameListSize = driver.findElements(By.xpath(rateCheckboxpath));
  			for (int i = 0; i < rateNameListSize.size(); i++    ) {
  				Utility.ScrollToElement_NoWait(rateNameListSize.get(i), driver);
  				rateNameListSize.get(i).click();
  				
  			}
  			test_steps.add("Total number of rates select for delete: " +rateNameListSize.size());
  			rate.deleteRate.click();
  			Wait.explicit_wait_visibilityof_webelement_120(rate.deleteRate, driver);
  			Wait.explicit_wait_elementToBeClickable(rate.deleteRate, driver);
  			
  		} else {
  
  			// for more than one page
  			System.out.println("In multiple page"+rate.RatesPagesSize.size());
  			Wait.explicit_wait_visibilityof_webelement_120(rate.deleteRate, driver);
  			Wait.explicit_wait_elementToBeClickable(rate.deleteRate, driver);
  			Utility.ScrollToElement(rate.RatesPagesSize.get(rate.RatesPagesSize.size()-1), driver);
  			rate.RatesPagesSize.get(rate.RatesPagesSize.size()-1).click();
  			
  			for (int i = rate.RatesPagesSize.size(); i>=0; i--) {
  				System.out.println("In for" + i);
  				
  				Wait.explicit_wait_visibilityof_webelement_120(rate.deleteRate, driver);
  				Wait.explicit_wait_elementToBeClickable(rate.deleteRate, driver);
  
  				List<WebElement> rateNameListSize = driver.findElements(By.xpath(rateCheckboxpath));
  				isFind = false;
  				for (int j = 0; j < rateNameListSize.size(); j++   ) {
  					Utility.ScrollToElement_NoWait(rateNameListSize.get(j), driver);
  					rateNameListSize.get(j).click();
  					isFind = true;
  				}
  				if (isFind) {
  					rate.deleteRate.click();
  					Wait.explicit_wait_visibilityof_webelement_120(rate.deleteRate, driver);
  					Wait.explicit_wait_elementToBeClickable(rate.deleteRate, driver);
  				}
  				try {
  					if (i>0) {
  						Utility.ScrollToElement(rate.RatesPagesSize.get(rate.RatesPagesSize.size()-1), driver);
  						rate.RatesPagesSize.get(i-1).click();
  						Wait.explicit_wait_visibilityof_webelement_120(rate.deleteRate, driver);
  						Wait.explicit_wait_elementToBeClickable(rate.deleteRate, driver);
  					}
  				} catch (Exception e) {
  					// TODO: handle exception
  				}
  				
  			}
  		}
  		return test_steps;
  	}
	
	public ArrayList<String> createRate(WebDriver driver, String ratename, String maxAdults, String maxPersons,
			String baseAmount, String additionalAdult, String additionalChild, String rateDisplayName,
			String ratePolicy, String rateDescription, String RoomClass, ArrayList<String> test_steps)
			throws InterruptedException {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_Inventory rate = new Elements_Inventory(driver);
		clickOnNewRateButton(driver, test_steps);
		rate.rateName.sendKeys(ratename);
		test_steps.add("Rate Name : " + ratename);
		rateLogger.info("Rate Name : " + ratename);
		String ratepan = new Select(rate.ratePlan).getFirstSelectedOption().getText();
		test_steps.add("Selected Rate Plan : " + ratepan);
		rateLogger.info("Selected Rate Plan : " + ratepan);
		String rateType = rate.getRateType.getText();
		test_steps.add("Selected Rate Type : " + rateType);
		rateLogger.info("Selected Rate Type : " + rateType);
		rate.maxAdults.sendKeys(maxAdults);
		test_steps.add("Rate Max Adults : " + maxAdults);
		rateLogger.info("Rate Max Adults : " + maxAdults);
		rate.maxPersons.sendKeys(maxPersons);
		test_steps.add("Rate Max Persons : " + maxPersons);
		rateLogger.info("Rate Max Persons : " + maxPersons);
		rate.baseAmount.sendKeys(baseAmount);
		test_steps.add("Rate Base Amount : " + baseAmount);
		rateLogger.info("Rate Base Amount : " + baseAmount);
		rate.additionalAdult.sendKeys(additionalAdult);
		test_steps.add("Rate Additional Adult : " + additionalAdult);
		rateLogger.info("Rate Additional Adult : " + additionalAdult);
		rate.additionalChild.sendKeys(additionalChild);
		test_steps.add("Rate Additional Child : " + additionalChild);
		rateLogger.info("Rate Additional Child : " + additionalChild);
		rate.rate_displayName.sendKeys(rateDisplayName);
		test_steps.add("Rate Display Name : " + rateDisplayName);
		rateLogger.info("Rate Display Name : " + rateDisplayName);
		rate.rate_policy.sendKeys(ratePolicy);
		test_steps.add("Rate Policy : " + ratePolicy);
		rateLogger.info("Rate Policy : " + ratePolicy);
		rate.rate_description.sendKeys(rateDescription);
		test_steps.add("Rate Description : " + rateDescription);
		rateLogger.info("Rate Description : " + rateDescription);

		// Attach session
		Utility.ScrollToElement(rate.rate_Associate_Seasons, driver);
		rate.rate_Associate_Seasons.click();
		test_steps.add("Click Associate Seasons");
		rateLogger.info("Click Associate Seasons");
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		String Season="//option[text()='All Year Season']";
		Utility.ScrollToElement(driver.findElement(By.xpath(Season)), driver);
		
		driver.findElement(By.xpath(Season)).click();
		
		String pickOne="//input[@id='btnPickOne']";
		driver.findElement(By.xpath(pickOne)).click();
		//rate.click_All_Seasons.click();
		rate.doneButton.click();
		test_steps.add("Select All Seasons and Click Done");
		rateLogger.info("Select All Seasons and Click Done");
		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_RoomClasses, driver);
		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		jse.executeScript("window.scrollBy(0,300)");
		Wait.wait1Second();
		rate.rate_Associate_RoomClasses.click();
		test_steps.add("Click Associate RoomClasses");
		rateLogger.info("Click Associate RoomClasses");
		Thread.sleep(5000);
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		Wait.wait5Second();
		String room="//option[text()='"+RoomClass+"']";
		Utility.ScrollToElement(driver.findElement(By.xpath(room)), driver);
		
		driver.findElement(By.xpath(room)).click();
		
		String selectRoom="//input[@id='btnPickOne']";
		driver.findElement(By.xpath(selectRoom)).click();

		 //rate.click_All_RoomClasses
		
		Wait.explicit_wait_xpath(OR.doneButton, driver);
		rate.doneButton.click();
		test_steps.add("Select  Room Class : " + RoomClass + " and Click Done");
		rateLogger.info("Select  Room Class : " + RoomClass + " and Click Done");
		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Utility.ScrollToElement(rate.rate_Associate_Sources, driver);
		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_Sources, driver);
		rate.rate_Associate_Sources.click();
		test_steps.add("Click Associate Sources");
		rateLogger.info("Click Associate Sources");
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Frame ");
		rate.rate_select_Source.click();
		Wait.explicit_wait_xpath(OR.doneButton, driver);
		rate.doneButton.click();
		test_steps.add("Select first Source and Click Done");
		rateLogger.info("Select first Source and Click Done");
		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Wait.explicit_wait_xpath(OR.rate_Save_Button, driver);
		rate.rate_Save_Button.click();
		Wait.wait3Second();
		test_steps.add("Click Save Rate");
		rateLogger.info("Click Save Rate");
		try {
			rate.rate_done_button.click();
			Wait.wait5Second();
			test_steps.add("Click Done Rate");
			rateLogger.info("Click Done Rate");
		} catch (Exception e) {
			driver.navigate().back();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_Save_Button, driver);
			rate.rate_Save_Button.click();
			Wait.wait3Second();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_done_button, driver);
			rate.rate_done_button.click();
			Wait.wait5Second();
			rateLogger.info("Again Click on Done Button ");
		}
		return test_steps;
	}

	public void rateGrid(WebDriver driver) throws InterruptedException {
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_xpath(OR.linkRateGrid, driver);
		rate.linkRateGrid.click();
		Wait.waitForElementToBeVisibile(By.xpath(OR.rateGridAvilability), driver);
		
		
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <clickOnRateGridAvilability> 
	 * ' Description: <Select rateGridAvilability> 
	 * ' Input parameters: < >   
	 * ' Return value: ArrayList<String> 
	 * ' Created By: <Reddy Ponnolu>
	 * ' Created On:  <04/05/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void clickOnRateGridAvilability(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.rateGridAvilability), driver);
		rate.RateGridAvilability.click();
		Wait.wait5Second();
		test_steps.add("Click on RateGridAvilability");
		rateLogger.info("Click on RateGridAvilability");
		Wait.waitforloadpage(1, 5, 5);
		
		
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <searchAndExpandRoomClassInGrid> 
	 * ' Description: <Select Room class in rate grid> 
	 * ' Input parameters: < String RoomClassName>   
	 * ' Return value: ArrayList<String> 
	 * ' Created By: <Reddy Ponnolu>
	 * ' Created On:  <04/05/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String> searchAndExpandRoomClassInGrid(WebDriver driver,String RoomClassName,ArrayList<String> test_steps) throws Exception{
		Elements_Inventory rate = new Elements_Inventory(driver);
		Utility.ScrollToEnd(driver);
		//Wait.explicit_wait_visibilityof_webelement_120(rate.RateGridAvilability, driver);
	/*	driver.switchTo().defaultContent();
		rate.RateGridAvilability.click();
		Wait.wait10Second();*/
		Wait.wait5Second();
		String room="//div[@title='"+RoomClassName+"']//following-sibling::span";
		Wait.waitForElementToBeVisibile(By.xpath("//div[@title='"+RoomClassName+"']//following-sibling::span"), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(room)), driver);
		Wait.wait2Second();
		Wait.waitForElementToBeClickable(By.xpath(room), driver);
		driver.findElement(By.xpath(room)).click();
		test_steps.add("Click on CreatedRoom class on rategrid");
		rateLogger.info("Click on CreatedRoom class on rategrid");
		Wait.waitForElementToBeVisibile(By.xpath((OR.clickONInncenter)), driver);
		//Wait.explicit_wait_visibilityof_webelement_120(rate.clickONInncenter, driver);
		return test_steps;
		
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <selectInncenterTab> 
	 * ' Description: <Select Room class InncenterTab> 
	 * ' Input parameters: < String RoomClassName>   
	 * ' Return value: ArrayList<String> 
	 * ' Created By: <Reddy Ponnolu>
	 * ' Created On:  <04/05/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String> selectInncenterTab(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException{
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.waitForElementToBeVisibile(By.xpath((OR.clickONInncenter)), driver);
		//Wait.explicit_wait_visibilityof_webelement_120(rate.clickONInncenter, driver);
		Utility.ScrollToElement(rate.clickONInncenter, driver);
		rate.clickONInncenter.click();
		Wait.wait3Second();
		test_steps.add("Click on Inncenter In selectedRoomClass");
		rateLogger.info("Click on Inncenter In selectedRoomClass");
		try {
			//Wait.explicit_wait_visibilityof_webelement_120(rate.BlackOutTosterMessage, driver);
			String tosterMassage=rate.BlackOutTosterMessage.getText();
			test_steps.add("tosterMassage:"+tosterMassage);
			rateLogger.info("tosterMassage:"+tosterMassage);
			//assertEquals(tosterMassage, "Bulk Update of Avilability Saved Successfully");
			assertEquals(tosterMassage, "Distribution to inncenter wassuccessfully enablede");
		} catch (Exception e) {
			rateLogger.info("No Working Toaster Appear");
		}
		Wait.waitForElementToBeVisibile(By.xpath(OR.bulkUpdateButton), driver);
		return test_steps;
		
	}
	
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <clickOnBulkUpdateTab> 
	 * ' Description: <Slect the BulkUpdateTab> 
	 * ' Input parameters: < String RoomClassName>   
	 * ' Return value: ArrayList<String> 
	 * ' Created By: <Reddy Ponnolu>
	 * ' Created On:  <04/05/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void clickOnBulkUpdateTab(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {
		Elements_Inventory rate = new Elements_Inventory(driver);
		//Wait.explicit_wait_visibilityof_webelement_120(rate.BulkUpdateButton, driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.bulkUpdateButton), driver);
		Utility.ScrollToElement(rate.BulkUpdateButton, driver);
		Wait.wait5Second();
		rate.BulkUpdateButton.click();
		test_steps.add("Click on BulkUpdate button");
		rateLogger.info("Click on BulkUpdate button");
		//Wait.explicit_wait_visibilityof_webelement_120(rate.BulkUpdateAvilability, driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.bulkUpdateAvilability), driver);
		
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <clickOnYesUpdateButton> 
	 * ' Description: <Slect the availability of rooms> 
	 * ' Input parameters: < String RoomClassName>   
	 * ' Return value: ArrayList<String> 
	 * ' Created By: <Reddy Ponnolu>
	 * ' Created On:  <04/05/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void clickOnYesUpdateButton(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.waitForElementToBeVisibile(By.xpath("(//div[@role='document']//following-sibling::p/../p)[1]"), driver);
		String UpdateMessage=driver.findElement(By.xpath("(//div[@role='document']//following-sibling::p/../p)[1]")).getText();
		test_steps.add("Avilability date range  :"+UpdateMessage);
		rateLogger.info("Avilability date range  :"+UpdateMessage);
		Assert.assertEquals(UpdateMessage,"Availability will be updated for 3 day(s) within this date range.","Both message are not equal");
		
		String AcceptMessage=driver.findElement(By.xpath("(//div[@role='document']//following-sibling::p/../p)[2]")).getText();
		test_steps.add("AcceptMessage  :"+AcceptMessage);
		rateLogger.info("AcceptMessage  :"+AcceptMessage);
		Assert.assertEquals(AcceptMessage,"Would you like to proceed?","Both message are not equal");
		
		driver.findElement(By.xpath("//button[contains(@class,'btn btn btn-success')]")).click();;
		test_steps.add("Click on BulkUpdate button");
		rateLogger.info("Click on BulkUpdate button");
		Wait.wait3Second();
		
		try {
			//Wait.explicit_wait_visibilityof_webelement_120(rate.BlackOutTosterMessage, driver);
			String tosterMassage=rate.BlackOutTosterMessage.getText();
			test_steps.add("tosterMassage:"+tosterMassage);
			rateLogger.info("tosterMassage:"+tosterMassage);
			assertEquals(tosterMassage, "Bulk Update of Avilability Saved Successfully");
			//assertEquals(tosterMassage, "Distribution to inncenter wassuccessfully enablede");
		} catch (Exception e) {
			rateLogger.info("No Working Toaster Appear");
		}
		Wait.waitForElementToBeVisibile(By.xpath(OR.CPReservationBackward), driver);
	}
	
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <selectBulkAvilability> 
	 * ' Description: <Select BulkAvilability option> 
	 * ' Input parameters: < >   
	 * ' Return value: ArrayList<String> 
	 * ' Created By: <Reddy Ponnolu>
	 * ' Created On:  <04/05/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void selectBulkAvilability(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.bulkUpdateAvilability), driver);
		//Wait.explicit_wait_visibilityof_webelement_120(rate.BulkUpdateAvilability, driver);
		
		//driver.switchTo().defaultContent();
		Wait.wait3Second();
		Utility.ScrollToElement(rate.BulkUpdateAvilability, driver);
		rate.BulkUpdateAvilability.click();
		test_steps.add("select bulk update avilability");
		rateLogger.info("select bulk update avilability");
		Wait.waitForElementToBeVisibile(By.xpath("//div/label[contains(text(),'Start')]//following-sibling::div"), driver);
		Wait.wait3Second();
		
		
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <verifyBulkUpdateAvilabilityHeadTex> 
	 * ' Description: <Select room class for BlackOut> 
	 * ' Input parameters: < >   
	 * ' Return value: ArrayList<String> 
	 * ' Created By: <Reddy Ponnolu>
	 * ' Created On:  <04/05/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String> verifyBulkUpdateAvilabilityHeadTex(WebDriver driver,ArrayList<String> test_steps){
		Elements_Inventory rate = new Elements_Inventory(driver);
		//Wait.explicit_wait_visibilityof_webelement_120(rate.BulkUpdateText, driver);
		Wait.waitForElementToBeVisibile(By.xpath("//div[contains(text(),'Bulk update - Availability')]"), driver);
		String popUpBulkUpdateheading ="//div[contains(text(),'Bulk update - Availability')]";
		popUpBulkUpdateheading =driver.findElement(By.xpath(popUpBulkUpdateheading)).getText();
		test_steps.add("popUp BulkUpdate heading   :"+ popUpBulkUpdateheading);
		rateLogger.info("select bulk update avilability  :"+popUpBulkUpdateheading);
		Wait.waitForElementToBeVisibile(By.xpath("//div[contains(text(),'Select room class(es)')]"), driver);
		return test_steps;
		
	}
	public void bulkUpdate(WebDriver driver,ArrayList test_steps,String startDate,String endDate) throws Exception {
		
		/*Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_RoomClasses, driver);
		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		jse.executeScript("window.scrollBy(0,300)");
		rate.rate_Associate_RoomClasses.click();*/
		
		
		Wait.waitForElementToBeVisibile(By.xpath("//div/label[contains(text(),'Start')]//following-sibling::div"), driver);
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,300)", driver.findElement(By.xpath("//div/label[contains(text(),'Start')]//following-sibling::div")));
		driver.findElement(By.xpath("//div/label[contains(text(),'Start')]//following-sibling::div/input")).click();
		driver.findElement(By.xpath("//div/label[contains(text(),'Start')]//following-sibling::div/input")).sendKeys(startDate);
		Wait.wait3Second();
		jse.executeScript("window.scrollBy(0,300);",driver.findElement(By.xpath("//div/label[contains(text(),'End')]//following-sibling::div")));
		driver.findElement(By.xpath("//div/label[contains(text(),'End')]//following-sibling::div/input")).click();
		driver.findElement(By.xpath("//div/label[contains(text(),'End')]//following-sibling::div/input")).sendKeys(endDate);
		//driver.findElement(By.xpath("//div/label[contains(text(),'End')]//following-sibling::div/input/span")).click();
		driver.findElement(By.xpath("//div[@class='col-md-6 datepicker calendar-right']/label")).click();
		
		Wait.waitForElementToBeVisibile(By.xpath("//div[contains(text(),'Bulk update - Availability')]"), driver);
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <selectRoomClassInBulkUpadte> 
	 * ' Description: <Select room class for BlackOut> 
	 * ' Input parameters: < >   
	 * ' Return value: ArrayList<String> 
	 * ' Created By: <Reddy Ponnolu>
	 * ' Created On:  <04/05/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String> selectRoomClassInBulkUpadte(WebDriver driver,String RoomClassName,ArrayList<String> test_steps) throws InterruptedException{
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.waitForElementToBeVisibile(By.xpath("//div[contains(text(),'Select room class(es)')]"), driver);
		 WebElement AvilableRoomsMenu =driver.findElement(By.xpath("//div[contains(text(),'Select room class(es)')]"));
		 AvilableRoomsMenu.click();
		 driver.findElement(By.xpath("(//div[@class='Select-input'])[2]/input")).sendKeys(RoomClassName);
		driver.findElement(By.xpath("//div[@class='Select-menu-outer']")).click();
		//Wait.explicit_wait_visibilityof_webelement_120(rate.BlackOutTab, driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.blackOutTab), driver);
		return test_steps;
		
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <clickOnBlackOutButton> 
	 * ' Description: <click on bulk out button> 
	 * ' Input parameters: < >   
	 * ' Return value: ArrayList<String> 
	 * ' Created By: <Reddy Ponnolu>
	 * ' Created On:  <04/05/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void clickOnBlackOutButton(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.blackOutTab), driver);
		//Wait.explicit_wait_visibilityof_webelement_120(rate.BlackOutTab, driver);
		Utility.ScrollToElement(rate.BlackOutTab, driver);
		rate.BlackOutTab.click();
		test_steps.add("Click on BulkOutbutton");
		rateLogger.info("Click on BulkOut button");
		Wait.explicit_wait_visibilityof_webelement_120(rate.UpadteButton, driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.upadteButton), driver);
		
	}
	public void clickOnAvilableTab(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement_120(rate.AvilableTab, driver);
		Utility.ScrollToElement(rate.AvilableTab, driver);
		rate.AvilableTab.click();
		test_steps.add("Click on AvilableTab");
		rateLogger.info("Click on AvilableTab");
		
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <clickOnBulkUpdateButton> 
	 * ' Description: <Find and select update button in page> 
	 * ' Input parameters: < >   
	 * ' Return value: ArrayList<String> 
	 * ' Created By: <Reddy Ponnolu>
	 * ' Created On:  <04/05/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void clickOnBulkUpdateButton(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.upadteButton), driver);
		//Wait.explicit_wait_visibilityof_webelement_120(rate.UpadteButton, driver);
		Utility.ScrollToElement(rate.UpadteButton, driver);
		rate.UpadteButton.click();
		test_steps.add("Click on UpadteButton button");
		rateLogger.info("Click on UpadteButton button");
		Wait.waitForElementToBeVisibile(By.xpath("(//div[@role='document']//following-sibling::p/../p)[1]"), driver);
		
	}

	public ArrayList<String> ClickNewRate(WebDriver driver) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		clickOnNewRateButton(driver, testSteps);
		return testSteps;

	}

	public ArrayList<String>  EnterRateName(WebDriver driver,String ratename)
			throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		rate.rateName.sendKeys(ratename);
		testSteps.add("Enter Rate Name : " + ratename);
		return testSteps;
	}

	public ArrayList<String> SelectRatePlan(WebDriver driver, String ratePlan)
			throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		new Select(rate.ratePlan).selectByVisibleText(ratePlan);
		rateLogger.info("SelectRate Plan : " + ratePlan);
		testSteps.add("Select Rate Plan : " + ratePlan);
		assertEquals(new Select(rate.ratePlan).getFirstSelectedOption().getText(), ratePlan,
				"Failed Rate Plan not selected");
		return testSteps;

	}

	public String GetRateType(WebDriver driver) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		String rateType = rate.getRateType.getText();
		rateLogger.info("Selected Rate Type : " + rateType);
		return rateType;
	}

	public ArrayList<String> EnterMaxAdults(WebDriver driver, String maxAdults)
			throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Utility.ScrollToElement_NoWait(rate.maxAdults, driver);
		rate.maxAdults.sendKeys(maxAdults);
		testSteps.add("Enter Max Adults : " + maxAdults);
		return testSteps;
	}

	public ArrayList<String> EnterMaxPersons(WebDriver driver, String maxPersons)
			throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Utility.ScrollToElement_NoWait(rate.maxPersons, driver);
		rate.maxPersons.sendKeys(maxPersons);
		testSteps.add("Enter Max Persons : " + maxPersons);
		return testSteps;
	}

	public  ArrayList<String> EnterBaseAmount(WebDriver driver, String baseAmount)
			throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Utility.ScrollToElement_NoWait(rate.baseAmount, driver);
		rate.baseAmount.sendKeys(baseAmount);
		testSteps.add("Enter Base Amount : " + baseAmount);
		return testSteps;
	}

	public ArrayList<String> EnterAdditionalAdult(WebDriver driver, String additionalAdult)
			throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Utility.ScrollToElement_NoWait(rate.additionalAdult, driver);
		rate.additionalAdult.sendKeys(additionalAdult);
		testSteps.add("Enter Additional Adults : " + additionalAdult);
		return testSteps;
	}

	public ArrayList<String> EnterAdditionalChild(WebDriver driver, String additionalChild)
			throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Utility.ScrollToElement_NoWait(rate.additionalChild, driver);
		rate.additionalChild.sendKeys(additionalChild);
		testSteps.add("Enter Additional Child : " + additionalChild);
		return testSteps ;
	}

	public ArrayList<String> EnterRateDisplayName(WebDriver driver, String rateDisplayName)
			throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Utility.ScrollToElement_NoWait(rate.rate_displayName, driver);
		rate.rate_displayName.sendKeys(rateDisplayName);
		testSteps.add("Enter Rate Display Name : " + rateDisplayName);
		return testSteps;
	}

	public ArrayList<String> EnterRatePolicy(WebDriver driver, String ratePolicy)
			throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Utility.ScrollToElement_NoWait(rate.rate_policy, driver);
		rate.rate_policy.sendKeys(ratePolicy);
		testSteps.add("Enter Rate Policy : " + ratePolicy);
		return testSteps;
	}

	public ArrayList<String> EnterRateDescription(WebDriver driver, String rateDescription)
			throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Utility.ScrollToElement_NoWait(rate.rate_description, driver);
		rate.rate_description.sendKeys(rateDescription);
		testSteps.add("Enter Rate Description : " + rateDescription);
		return testSteps;
	}

	public ArrayList<String> AssociateSeason(WebDriver driver, String season)
			throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.rate_Associate_Seasons);
		Wait.waitForElementToBeVisibile(By.xpath(OR.rate_Associate_Seasons), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Associate_Seasons), driver);

		Utility.ScrollToElement_NoWait(rate.rate_Associate_Seasons, driver);
		rate.rate_Associate_Seasons.click();
		testSteps.add("Associalte Seasons Button Clicked");

		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));
		rateLogger.info(" Switched to Frame ");
		String path = "//*[@id='lstSeasons']//option[contains(text(),'" + season + "')]";
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);
		WebElement elmentSeason = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(elmentSeason, driver);
		elmentSeason.click();
		rate.PickerButton.click();
		rate.doneButton.click();
		rateLogger.info("Select " + season + " Season and Click Done");
		testSteps.add("Select " + season + " Season and Click Done");
		driver.switchTo().defaultContent();
		return testSteps;
	}

	public ArrayList<String> AssociateRoomClass(WebDriver driver, String roomClass)
			throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.rate_Associate_RoomClasses);
		Wait.waitForElementToBeVisibile(By.xpath(OR.rate_Associate_RoomClasses), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Associate_RoomClasses), driver);
		Utility.ScrollToElement_NoWait(rate.rate_Associate_RoomClasses, driver);
		Wait.wait1Second();
		rate.rate_Associate_RoomClasses.click();
		testSteps.add("Associalte RoomClasses Button Clicked");
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));

		rateLogger.info(" Switched to Frame ");
		Wait.WaitForElement_ID(driver, OR.SelectAssociateRoomClass);
		Wait.waitForElementToBeVisibile(By.id(OR.SelectAssociateRoomClass), driver);

		new Select(rate.SelectAssociateRoomClass).selectByVisibleText(roomClass);
		rate.PickerButton.click();
		testSteps.add("Selected RoomClass : " + roomClass);
		rate.doneButton.click();
		testSteps.add("Click Associalte RoomClasses Done Button");
		driver.switchTo().defaultContent();
		return testSteps;
	}

	public ArrayList<String> AssociateSource(WebDriver driver, String source)
			throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.rate_Associate_Sources);
		Wait.waitForElementToBeVisibile(By.xpath(OR.rate_Associate_Sources), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Associate_Sources), driver);

		Utility.ScrollToElement_NoWait(rate.rate_Associate_Sources, driver);
		Wait.wait1Second();
		rate.rate_Associate_Sources.click();
		testSteps.add("Associalte Sources Button Clicked");
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		driver.switchTo().frame(driver.findElement(By.id("frmWaitHost")));

		rateLogger.info(" Switched to Frame ");
		String path = "//td[text()='" + source + "']//parent::tr/td/input";
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);

		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement_NoWait(element, driver);
		element.click();
		testSteps.add("Select Source '" + source + "'");
		rate.doneButton.click();
		testSteps.add("Associalte Sources Done Button Clicked");
		driver.switchTo().defaultContent();
		return testSteps;

	}

	public ArrayList<String> Save_DoneRate(WebDriver driver) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.rate_Save_Button);
		Wait.waitForElementToBeVisibile(By.xpath(OR.rate_Save_Button), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Save_Button), driver);
		rate.rate_Save_Button.click();

		Wait.WaitForElement(driver, OR.rate_done_button);
		Wait.waitForElementToBeVisibile(By.xpath(OR.rate_done_button), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_done_button), driver);

		rate.rate_done_button.click();
		rateLogger.info(" Clicked on Done Button ");
		testSteps.add(" Clicked on Done Button ");

		Wait.WaitForElement(driver, OR.newRate);
		Wait.waitForElementToBeVisibile(By.xpath(OR.newRate), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.newRate), driver);
		return testSteps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <create_Rate> ' Description: <create_Rate> ' 
	 * ' Input parameters: <string>
	 * ' Created By: <Gangotri Sikheria> '
	 * Created On: <11 June 2020>

	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> create_Rate(WebDriver driver, String ratename, String maxAdults, String maxPersons,
			String baseAmount, String additionalAdult, String additionalChild, String rateDisplayName,
			String ratePolicy, String rateDescription, String RoomClass1, String RoomClass2,
			ArrayList<String> test_steps) throws InterruptedException {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_xpath(OR.newRate, driver);
		rate.newRate.click();
		test_steps.add("Click New Rate");
		rateLogger.info("Click New Rate");
		Wait.explicit_wait_xpath(OR.rateName, driver);
		rate.rateName.sendKeys(ratename);

		test_steps.add("Rate Name : " + ratename);
		rateLogger.info("Rate Name : " + ratename);
		String ratepan = new Select(rate.ratePlan).getFirstSelectedOption().getText();
		test_steps.add("Selected Rate Plan : " + ratepan);
		rateLogger.info("Selected Rate Plan : " + ratepan);
		String rateType = rate.getRateType.getText();
		test_steps.add("Selected Rate Type : " + rateType);
		rateLogger.info("Selected Rate Type : " + rateType);
		rate.maxAdults.sendKeys(maxAdults);
		test_steps.add("Rate Max Adults : " + maxAdults);
		rateLogger.info("Rate Max Adults : " + maxAdults);
		rate.maxPersons.sendKeys(maxPersons);
		test_steps.add("Rate Max Persons : " + maxPersons);
		rateLogger.info("Rate Max Persons : " + maxPersons);
		rate.baseAmount.sendKeys(baseAmount);
		test_steps.add("Rate Base Amount : " + baseAmount);
		rateLogger.info("Rate Base Amount : " + baseAmount);
		Wait.explicit_wait_xpath(OR.additionalAdult, driver);
		rate.additionalAdult.sendKeys(additionalAdult);
		test_steps.add("Rate Additional Adult : " + additionalAdult);
		rateLogger.info("Rate Additional Adult : " + additionalAdult);
		Wait.explicit_wait_xpath(OR.additionalChild, driver);
		rate.additionalChild.sendKeys(additionalChild);
		test_steps.add("Rate Additional Child : " + additionalChild);
		rateLogger.info("Rate Additional Child : " + additionalChild);
		Wait.explicit_wait_xpath(OR.rate_displayName, driver);
		rate.rate_displayName.sendKeys(rateDisplayName);
		test_steps.add("Rate Display Name : " + rateDisplayName);
		rateLogger.info("Rate Display Name : " + rateDisplayName);
		Wait.explicit_wait_xpath(OR.rate_policy, driver);
		rate.rate_policy.sendKeys(ratePolicy);
		test_steps.add("Rate Policy : " + ratePolicy);
		rateLogger.info("Rate Policy : " + ratePolicy);
		Wait.explicit_wait_xpath(OR.rate_description, driver);
		rate.rate_description.sendKeys(rateDescription);
		test_steps.add("Rate Description : " + rateDescription);
		rateLogger.info("Rate Description : " + rateDescription);

		// Attach session
		Utility.ScrollToElement(rate.rate_Associate_Seasons, driver);
		rate.rate_Associate_Seasons.click();
		test_steps.add("Click Associate Seasons");
		rateLogger.info("Click Associate Seasons");
		//Wait.wait2Second();
		String path="//iframe[@id='dialog-body0']";
		Wait.WaitForElement(driver, path);
		driver.switchTo().frame("dialog-body0");
		rateLogger.info(" Switched to Main Frame ");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Child Frame ");
		rateLogger.info(" Switched to Frame ");
		rate.click_All_Seasons.click();
		rate.doneButton.click();
		test_steps.add("Select All Seasons and Click Done");
		rateLogger.info("Select All Seasons and Click Done");
		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_RoomClasses, driver);
		Utility.ScrollToElement(rate.rate_Associate_RoomClasses, driver);
		jse.executeScript("window.scrollBy(0,300)");
		rate.rate_Associate_RoomClasses.click();
		test_steps.add("Click Associate RoomClasses");
		rateLogger.info("Click Associate RoomClasses");
	//	Thread.sleep(5000);
		Wait.WaitForElement(driver, path);
		driver.switchTo().frame("dialog-body0");
		rateLogger.info(" Switched to Main Frame ");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Child Frame ");
	//	Wait.wait5Second();
		
		// rate.click_All_RoomClasses.click();
		new Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass1);
		rate.PickerButton.click();
	//	Wait.explicit_wait_xpath(OR.doneButton, driver);
		Wait.presenceOfElementLocated(driver, OR.doneButton, 10);
		new Select(rate.SelectAssociateRoomClass).selectByVisibleText(RoomClass2);
		rate.PickerButton.click();
	//	Wait.explicit_wait_xpath(OR.doneButton, driver);
		Wait.presenceOfElementLocated(driver, OR.doneButton, 10);
		rate.doneButton.click();
		test_steps.add("Select Room Classes: '" + RoomClass1 + "' , '" + RoomClass2 + "' and  Click Done");
		rateLogger.info("Select Room Classes: '" + RoomClass1 + "' , '" + RoomClass2 + "' and  Click Done");
		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Utility.ScrollToElement(rate.rate_Associate_Sources, driver);
		Wait.waitUntilPresenceOfElementLocated(OR.rate_Associate_Sources, driver);
		rate.rate_Associate_Sources.click();
		test_steps.add("Click Associate Sources");
		rateLogger.info("Click Associate Sources");
	//	Wait.wait2Second();
		Wait.WaitForElement(driver, path);
		driver.switchTo().frame("dialog-body0");
		rateLogger.info(" Switched to Main Frame ");
		driver.switchTo().frame("frmWaitHost");
		rateLogger.info(" Switched to Child Frame ");
		rate.rate_select_Source.click();
	//	Wait.explicit_wait_xpath(OR.doneButton, driver);
		Wait.presenceOfElementLocated(driver, OR.doneButton, 10);
		rate.doneButton.click();
		test_steps.add("Select first Source and Click Done");
		rateLogger.info("Select first Source and Click Done");
		Wait.wait3Second();
		driver.switchTo().defaultContent();

		Wait.explicit_wait_xpath(OR.rate_Save_Button, driver);
	//	Wait.presenceOfElementLocated(driver, OR.rate_Save_Button, 20);
		rate.rate_Save_Button.click();
		Wait.wait3Second();
		test_steps.add("Click Save Rate");
		rateLogger.info("Click Save Rate");
		try {
			rate.rate_done_button.click();
			Wait.wait5Second();
			test_steps.add("Click Done Rate");
			rateLogger.info("Click Done Rate");
		} catch (Exception e) {
			driver.navigate().back();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_Save_Button, driver);
			rate.rate_Save_Button.click();
			Wait.wait3Second();
			Wait.explicit_wait_visibilityof_webelement(rate.rate_done_button, driver);
			rate.rate_done_button.click();
			Wait.wait5Second();
			rateLogger.info("Again Click on Done Button ");
		}
		return test_steps;
	}

	public ArrayList<String> EnterRateInterval(WebDriver driver, String ratePolicy)
			throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Utility.ScrollToElement_NoWait(rate.rateInterval, driver);
		rate.rateInterval.sendKeys(ratePolicy);
		testSteps.add("Enter Rate Interval : " + ratePolicy);
		return testSteps;
	}
	public void createRate(WebDriver driver, ArrayList<String> test_steps, String ratename, String ratePlan, String rateInterval, 
			String maxAdults, String maxPersons, String baseAmount, String additionalAdult, String additionalChild, 
			String rateDisplayName, String associateSeason, String ratePolicy, String rateDescription, String RoomClass) 
					throws Exception {

		clickOnNewRateButton(driver, test_steps);
		provideRateName(driver, test_steps, ratename);			
		selectRatePlan(driver, test_steps, ratePlan);			
		if (Utility.validateString(rateInterval)) {
			provideRateInterval(driver, test_steps, rateInterval);
		}
		provideMaxAdultsAndPersons(driver, test_steps, maxAdults, maxPersons);
		provideBaseAmount(driver, test_steps, baseAmount);
		provideAdditionalAdultsAndChildren(driver, test_steps, additionalAdult, additionalChild);
		provideDisplayName(driver, test_steps, rateDisplayName);	
		providePolicyAndDescription(driver, test_steps, ratePolicy, rateDescription);	
		AssociateSeason(driver, associateSeason, test_steps);
		AssociateRoomClass(driver, RoomClass, test_steps);
		AssociateSource(driver, "innCenter", test_steps);
		clickOnSaveRateButton(driver, test_steps);
		try {
			clickOnDoneButtonInNewRatePage(driver, test_steps);
		} catch (Exception e) {
			driver.navigate().back();
			clickOnSaveRateButton(driver, test_steps);
			clickOnDoneButtonInNewRatePage(driver, test_steps);
		}
		rateLogger.info("Successfully Created <b>" + ratename + "</b> Rate for <b>" + ratePlan + "</b> Rate Plan");
		test_steps.add("Successfully Created <b>" + ratename + "</b>' Rate for <b>" + ratePlan + "</b> Rate Plan");	
	}

	private void selectRatePlan(WebDriver driver, ArrayList<String> test_steps, String ratePlan) {
		Elements_Inventory rate = new Elements_Inventory(driver);
		String ratePlanSelected = new Select(rate.ratePlan).getFirstSelectedOption().getText();
		if ( !(ratePlanSelected.equalsIgnoreCase(ratePlan)) ) {
			new Select(rate.ratePlan).selectByVisibleText(ratePlan);
			rateLogger.info("Selected Rate Plan as : <b>"+ratePlan+"</b>");
			test_steps.add("Selected Rate Plan  : <b>"+ratePlan+"</b>");			
		}else {
			test_steps.add("Rate Plan is already selected as : <b>"+ratePlan+"</b>");
		}
	}

	private void provideRateName(WebDriver driver, ArrayList<String> test_steps, String ratename) {
		Elements_Inventory rate = new Elements_Inventory(driver);
		rate.rateName.clear();
		rate.rateName.sendKeys(ratename);
		rateLogger.info("Provided Rate Name as <b> "+ratename+"</b> ");
		test_steps.add("Provided Rate Name as <b> "+ratename+"</b> ");
	}

	private void provideRateInterval(WebDriver driver, ArrayList<String> test_steps, String rateInterval) {
		Elements_Inventory rate = new Elements_Inventory(driver);
		rate.ConditionalRate.click();
		test_steps.add("Enabling <b>Conditional rate </b>checkbox ");
		rate.rateInterval.clear();
		rate.rateInterval.sendKeys(rateInterval);
		rateLogger.info("Provided Rate interval as <b> "+rateInterval+"</b> ");
		test_steps.add("Provided Rate interval as <b> "+rateInterval+"</b> ");
	}

	private void provideMaxAdultsAndPersons(WebDriver driver, ArrayList<String> test_steps, 
			String maxAdults, String maxPersons) {
		Elements_Inventory rate = new Elements_Inventory(driver);
		rate.maxAdults.clear();
		rate.maxAdults.sendKeys(maxAdults);
		rateLogger.info("Provided max adults as : <b>" + maxAdults+"</b>");
		test_steps.add("Provided max adults as : <b>" + maxAdults+"</b>");
		rate.maxPersons.clear();
		rate.maxPersons.sendKeys(maxPersons);
		test_steps.add("Provided max persons as : <b>" + maxPersons+"</b>");
		rateLogger.info("Provided max persons as : <b>" + maxPersons+"</b>");
	}

	private void provideBaseAmount(WebDriver driver, ArrayList<String> test_steps, 
			String baseAmount) {
		Elements_Inventory rate = new Elements_Inventory(driver);
		rate.baseAmount.clear();
		rate.baseAmount.sendKeys(baseAmount);
		test_steps.add("Provided base amount as : <b>" + baseAmount+"</b>");
	}

	private void provideAdditionalAdultsAndChildren(WebDriver driver, ArrayList<String> test_steps, 
			String additionalAdult, String additionalChild) {
		Elements_Inventory rate = new Elements_Inventory(driver);
		rate.additionalAdult.clear();
		rate.additionalAdult.sendKeys(additionalAdult);
		test_steps.add("Provided additional adult as : <b>" + additionalAdult+"</b>");
		rateLogger.info("Provided additional adult as : <b>" + additionalAdult+"</b>");
		rate.additionalChild.clear();
		rate.additionalChild.sendKeys(additionalChild);
		test_steps.add("Provided additional children as : <b>" + additionalChild+"</b>");
		rateLogger.info("Provided additional children as : <b>" + additionalChild+"</b>");
	}

	private void provideDisplayName(WebDriver driver, ArrayList<String> test_steps, 
			String displayName) {
		Elements_Inventory rate = new Elements_Inventory(driver);
		rate.rate_displayName.clear();
		rate.rate_displayName.sendKeys(displayName);
		test_steps.add("Provided rate display name as : <b>" + displayName+"</b>");
		rateLogger.info("Provided rate display name as : <b>" + displayName+"</b>");
	}

	private void providePolicyAndDescription(WebDriver driver, ArrayList<String> test_steps, 
			String ratePolicy, String rateDescription) {
		Elements_Inventory rate = new Elements_Inventory(driver);
		rate.rate_policy.clear();
		rate.rate_policy.sendKeys(ratePolicy);
		test_steps.add("Provided rate policy as : <b>" + ratePolicy+"</b>");
		rateLogger.info("Provided rate policy as : <b>" + ratePolicy+"</b>");
		rate.rate_description.clear();
		rate.rate_description.sendKeys(rateDescription);
		test_steps.add("Provided rate description as : <b>" + rateDescription+"</b>");
		rateLogger.info("Provided rate description as : <b>" + rateDescription+"</b>");
	}

	private void clickOnSaveRateButton(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_Save_Button), driver);
		rate.rate_Save_Button.click();
		rateLogger.info("Clicked on Save Rate Button ");
		test_steps.add("Clicked on Save Rate Button ");
	}

	private void clickOnDoneButtonInNewRatePage(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rate_done_button), driver);
		rate.rate_done_button.click();
		rateLogger.info("Clicked on Done Button in New Rate page");
		test_steps.add("Clicked on Done Button in New Rate page");
	}
	
}

