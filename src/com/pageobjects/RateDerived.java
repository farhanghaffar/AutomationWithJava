package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertTrue;

import java.awt.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.innroad.inncenter.interfaces.IRateDerived;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_DocumentTemplates;
import com.innroad.inncenter.webelements.Elements_Inventory;

public class RateDerived implements IRateDerived {

	public static Logger derivedRateLogger = Logger.getLogger("RateDerived");

	public void inventory_Rate(WebDriver driver) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.wait3Second();
		rate.click_Inventory.click();
		rate.inventory_rate.click();
		Wait.wait10Second();
		derivedRateLogger.info(" System successfully Navigated to Inventory Rates ");
		// Wait.wait10Second();
	}

	public  ArrayList<String> new_RateDerived(WebDriver driver, String ratename, String offsetAmount, String rateDisplayName,
			String ratePolicy, String rateDescription, ArrayList<String> test_steps) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_Inventory derivedrate = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement_120(derivedrate.newRate, driver);
		derivedrate.newRate.click();
		test_steps.add("Click New Rate");
		derivedRateLogger.info("Click New Rate");
		Wait.explicit_wait_visibilityof_webelement_120(derivedrate.rateName, driver);
		derivedrate.rateName.sendKeys(ratename);
		String derivedplan = new Select(derivedrate.ratePlan).getFirstSelectedOption().getText();
		derivedRateLogger.info("Selected Rate Plan : " + derivedplan);
		test_steps.add("Selected Rate Plan : " + derivedplan);

		new Select(derivedrate.ratePlan).selectByIndex(1);
		String derivedplan2 = new Select(derivedrate.ratePlan).getFirstSelectedOption().getText();
		derivedRateLogger.info(" Select Derived Rate Plan : " + derivedplan2);
		test_steps.add("Select Derived Rate Plan : " + derivedplan2);

		// derivedRateLogger.info( "Selected Rate Plan : " +derivedplan);
		derivedrate.selectDerivedRate.click();
		String rateType = derivedrate.getRateType4.getText();
		derivedRateLogger.info(" Selected Rate Type : " + rateType);

		Select dropdown = new Select(derivedrate.selectDerivedRatePlan);
		java.util.List<WebElement> options = dropdown.getOptions();
		int itemSize = options.size() - 1;
		derivedRateLogger.info(" No of Derived rates : " + itemSize);

		for (int i = 0; i < itemSize; i++) {
			// String optionsValue = options.get(i).getText();
			// derivedRateLogger.info("Derived rate Plans : " +optionsValue);

			new Select(derivedrate.selectDerivedRatePlan).selectByIndex(itemSize);

		}

		derivedrate.offsetAmount.sendKeys(offsetAmount);
		Wait.explicit_wait_visibilityof_webelement(derivedrate.rate_displayName, driver);
		derivedrate.rate_displayName.sendKeys(rateDisplayName);
		test_steps.add("Enter Rate Name : " + rateDisplayName);
		Wait.explicit_wait_visibilityof_webelement(derivedrate.rate_policy, driver);
		derivedrate.rate_policy.sendKeys(ratePolicy);
		test_steps.add("Enter Rate Policy : " + ratePolicy);
		Wait.explicit_wait_visibilityof_webelement(derivedrate.rate_description, driver);
		derivedrate.rate_description.sendKeys(rateDescription);
		test_steps.add("Enter Rate Description : " + rateDescription);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		// String parentWindow=driver.getWindowHandle();
		derivedrate.rate_Associate_Seasons.click();
		Wait.wait2Second();

		/*
		 * Set<String> allWindowHandles = driver.getWindowHandles();
		 * derivedRateLogger.info(allWindowHandles.size());
		 * 
		 * for (String winHandle : allWindowHandles) { if
		 * (!winHandle.equals(parentWindow)) {
		 * driver.switchTo().window(winHandle);
		 * 
		 * } driver.switchTo().window(parentWindow); }
		 */

		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		derivedRateLogger.info(" Switched to Frame ");

		derivedrate.click_All_Seasons.click();
		// Wait.wait5Second();
		// Wait.explicit_wait_xpath(OR.doneButton);
		derivedrate.doneButton.click();
		Wait.wait2Second();
		driver.switchTo().defaultContent();
		test_steps.add("Associate Seasons");
		// Wait.wait25Second();
		/*
		 * JavascriptExecutor js2 = (JavascriptExecutor) driver;
		 * js2.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 * Thread.sleep(5000);
		 */

		/*
		 * WebElement availableCell=rate.rate_Associate_RoomClasses;
		 * jse.executeScript("arguments[0].scrollIntoView(true);",
		 * availableCell); jse.executeScript("arguments[0].click()",
		 * availableCell);
		 */

		jse.executeScript("window.scrollBy(0,1000)");
		Utility.ScrollToElement(derivedrate.rate_Associate_RoomClasses, driver);
		Wait.explicit_wait_xpath(OR.rate_Associate_RoomClasses, driver);
		derivedrate.rate_Associate_RoomClasses.click();
		Thread.sleep(1000);
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		derivedRateLogger.info(" Switched to Frame ");
		Wait.wait2Second();
		derivedrate.click_All_RoomClasses.click();
		Wait.explicit_wait_visibilityof_webelement(derivedrate.doneButton, driver);
		derivedrate.doneButton.click();
		Wait.wait2Second();
		driver.switchTo().defaultContent();
		Wait.wait2Second();

		test_steps.add("Associate RoomClasses");
		Wait.explicit_wait_visibilityof_webelement(derivedrate.rate_Associate_Sources, driver);
		Utility.ScrollToElement(derivedrate.rate_Associate_Sources, driver);
		Wait.explicit_wait_visibilityof_webelement(derivedrate.rate_Associate_Sources, driver);
		derivedrate.rate_Associate_Sources.click();
		Wait.wait1Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		derivedRateLogger.info(" Switched to Frame ");
		Wait.wait1Second();
		derivedrate.rate_select_Source.click();
		Wait.explicit_wait_visibilityof_webelement(derivedrate.doneButton, driver);
		derivedrate.doneButton.click();
		Wait.wait2Second();
		driver.switchTo().defaultContent();

		test_steps.add("Associate Sources");
		
		Wait.explicit_wait_visibilityof_webelement(derivedrate.rate_Save_Button, driver);
		derivedrate.rate_Save_Button.click();
		test_steps.add("Click Save");
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(derivedrate.rate_done_button, driver);
		derivedrate.rate_done_button.click();
		test_steps.add("Click done");
		return test_steps;

	}

	public void delete_rate(WebDriver driver) throws InterruptedException

	{
		Elements_Inventory rate = new Elements_Inventory(driver);
		rate.click_goButton.click();
		Wait.wait5Second();
		rate.selectDRate.click();
		Wait.wait10Second();
		rate.deleteRate.click();
		Wait.wait10Second();
		derivedRateLogger.info(" System sucessfully deleted the Rate ");
		Wait.wait10Second();
	}

	public void delete_rate(WebDriver driver, String RateName) throws InterruptedException

	{
		Elements_Inventory rate = new Elements_Inventory(driver);
		rate.click_goButton.click();

		String RatePath = " //a[contains(text(),'" + RateName + "')]/../preceding-sibling::td/span/input";
		WebElement Rate = driver.findElement(By.xpath(RatePath));
		Wait.explicit_wait_visibilityof_webelement(Rate, driver);
		Utility.ScrollToElement(Rate, driver);
		Rate.click();
		Wait.explicit_wait_visibilityof_webelement(rate.deleteRate, driver);
		Utility.ScrollToElement(rate.deleteRate, driver);		
		rate.deleteRate.click();
		derivedRateLogger.info(" System sucessfully deleted the Rate ");

	}

	public void DeleteRate(WebDriver driver, String RateType, String RateName) throws InterruptedException

	{
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.SelectRateType)), driver);
		new Select(driver.findElement(By.xpath(OR.SelectRateType))).selectByVisibleText(RateType);
		Utility.app_logs.info("Select Rate Type : " + RateType);
		rate.click_goButton.click();
		Utility.app_logs.info("Click Go");
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.CreatedRate_Pages)), driver);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", rate.CreatedRate_Pages);
		int Size = driver.findElements(By.xpath(OR.CreatedRate_Pages)).size();
		if (Size > 2) {
			// System.out.println("Pages : " + (Size - 1));
		}
		boolean found = false;
		for (int Page = 1; Page < Size; Page++) {
			Wait.WaitForElement(driver, OR.RateName_List);
			int count = driver.findElements(By.xpath(OR.RateName_List)).size();
			// System.out.println(count);
			for (int i = 0; i < count; i++) {
				Wait.wait2Second();
				int rowNumber = i + 1;
				String rateName = driver.findElement(By.xpath("(" + OR.RateName_List + ")[" + rowNumber + "]"))
						.getText();
				// System.out.println("Rate name is " + rateName);
				if (rateName.equals(RateName)) {
					found = true;
					// System.out.println("Rate Found");
					driver.findElement(By.xpath("(" + OR.DeleteDocument_CheckBox + ")[" + rowNumber + "]")).click();
					// System.out.println("click Check");
					assertTrue(driver.findElement(By.xpath("(" + OR.DeleteDocument_CheckBox + ")[" + rowNumber + "]"))
							.isSelected(), "Failed: Delete  Rate");
					break;
				}
			}
			if (found) {
				if (Page != 1) {
					String firstPagePath = "//tr[@class='TextDefault']/td//following-sibling::*[text()='" + 1 + "']";
					jse.executeScript("arguments[0].scrollIntoView(true);",
							driver.findElement(By.xpath(firstPagePath)));
					driver.findElement(By.xpath(firstPagePath)).click();
					derivedRateLogger.info("Move to Page " + 1);
					Wait.WaitForElement(driver, OR.RateName_List);
				}
				break;
			} else {
				int NextPage = Page + 1;
				String NextPagePath = "//tr[@class='TextDefault']/td//following-sibling::*[text()='" + NextPage + "']";
				jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(NextPagePath)));
				driver.findElement(By.xpath(NextPagePath)).click();
				derivedRateLogger.info("Move to Page " + NextPage);
				Wait.WaitForElement(driver, OR.RateName_List);
			}
		}
		if (found) {
			rate.deleteRate.click();
			derivedRateLogger.info(RateName + " Rate Delete");
		} else {
			assertTrue(false, "Rate '" + RateName + "' not found");
		}
	}

	public ArrayList<String> DeleteAllRate(WebDriver driver, String RateType,ArrayList<String> test_steps) throws InterruptedException

	{
		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.SelectRateType)), driver);
		new Select(driver.findElement(By.xpath(OR.SelectRateType))).selectByVisibleText(RateType);
		Utility.app_logs.info("Select Rate Type : " + RateType);
		test_steps.add("Select Rate Type : " + RateType);
		rate.click_goButton.click();
		Utility.app_logs.info("Click Go");
		boolean foundRates = true;
		try {
			Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.CreatedRate_Pages)), driver);
		} catch (Exception e) {
			WebElement Message = driver.findElement(By.xpath(OR.ErrorMessage));
			if (Message.isDisplayed()) {
				Utility.app_logs.info(Message.getText());
				foundRates = false;
			}
		}

		if (foundRates) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", rate.CreatedRate_Pages);
			int Size = driver.findElements(By.xpath(OR.CreatedRate_Pages)).size();
			if (Size > 2) {
				System.out.println("Pages : " + (Size - 1));
			}
			for (int Page = 1; Page < Size; Page++) {
				if (Page != 1) {
					int NextPage = Page + 1;
					String NextPagePath = "//tr[@class='TextDefault']/td//following-sibling::*[text()='" + NextPage
							+ "']";
					jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(NextPagePath)));
					driver.findElement(By.xpath(NextPagePath)).click();
					derivedRateLogger.info("Move to Page " + NextPage);
				}
				Wait.WaitForElement(driver, OR.RateName_List);
				int count = driver.findElements(By.xpath(OR.RateName_List)).size();
				// System.out.println(count);
				for (int i = 0; i < count; i++) {
					Wait.wait2Second();
					int rowNumber = i + 1;
					String rateName = driver.findElement(By.xpath("(" + OR.RateName_List + ")[" + rowNumber + "]"))
							.getText();
					System.out.println("Rate name is " + rateName);

					driver.findElement(By.xpath("(" + OR.DeleteDocument_CheckBox + ")[" + rowNumber + "]")).click();
					System.out.println("click Check");
					assertTrue(driver.findElement(By.xpath("(" + OR.DeleteDocument_CheckBox + ")[" + rowNumber + "]"))
							.isSelected(), "Failed: Delete  Rate");
				}
				rate.deleteRate.click();
				derivedRateLogger.info("Rates Deleted of Page " + Page);

			}
		}
		return test_steps;

	}

}
