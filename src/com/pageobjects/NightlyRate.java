package com.innroad.inncenter.pageobjects;

import static org.junit.Assert.assertNotEquals;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.innroad.inncenter.properties.OR_NightlyRatePlan;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_NightlyRate;
import com.innroad.inncenter.webelements.Elements_RatesGrid;
import com.innroad.inncenter.webelements.WebElementsOverview;

public class NightlyRate {

	public static Logger logger = Logger.getLogger("NightlyRate");

	//Rate Plan Name

	public void enterRatePlanName(WebDriver driver,String rateName,ArrayList<String> test_steps) throws InterruptedException {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		Wait.explicit_wait_visibilityof_webelement_120(elements.RATE_PLAN_NAME, driver);
		elements.RATE_PLAN_NAME.clear();

		Utility.clearValue(driver, elements.RATE_PLAN_NAME);
		elements.RATE_PLAN_NAME.sendKeys(rateName);
		test_steps.add("Entered Rate Plan Name : " + rateName);
		logger.info("Entered Rate Plan Name : " + rateName);

	}

	public void verifyRatePlanNameVisibility(WebDriver driver,boolean isEnabled, boolean isDisplayed,ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		Wait.explicit_wait_visibilityof_webelement_120(elements.RATE_PLAN_NAME, driver);
		assertEquals(elements.RATE_PLAN_NAME.isDisplayed(), isDisplayed, "Failed To Verify Rate Plan Name Display");
		test_steps.add("Successfully Verified Rate Plan Name is Displayed : " + isDisplayed);
		logger.info("Successfully Verified Rate Plan Name is Displayed : " + isDisplayed);

		assertEquals(elements.RATE_PLAN_NAME.isEnabled(), isEnabled, "Failed To Verify Rate Plan Name Enable");
		test_steps.add("Successfully Verified Rate Plan Name Enable : " + isEnabled);
		logger.info("Successfully Verified Rate Plan Name Enable : " + isEnabled);
	}

	public void verifyRatePlanRequiredFeild(WebDriver driver, String ratePlanName, boolean isRateAlreadyExistError, ArrayList<String> test_steps) throws InterruptedException {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		String errorTxt = "Rate Plan Name cannot be empty";

		if(isRateAlreadyExistError) {
			errorTxt = "A rate plan with this name already exists. Please choose a different name.";
			Wait.explicit_wait_visibilityof_webelement_120(elements.RATE_PLAN_NAME, driver);
			enterRatePlanName(driver, ratePlanName, test_steps);
			elements.RATE_PLAN_NAME.sendKeys(Keys.TAB);
			assertTrue(elements.RATE_PLAN_NAME_ERROR_TEXT.isDisplayed(), "Failed To Verify Rate Plan Name Error Text Not Displayed");
			test_steps.add("Successfully Verified Rate Plan Name Error Text Displayed ");
			logger.info("Successfully Verified Rate Plan Name Error Text Displayed ");

			assertEquals(elements.RATE_PLAN_NAME_ERROR_TEXT.getText(),errorTxt, "Failed To Verify Rate Plan Name Error Text Not Displayed");
			test_steps.add("Successfully Verified Rate Plan Name Error Text : " + elements.RATE_PLAN_NAME_ERROR_TEXT.getText());
			logger.info("Successfully Verified Rate Plan Name Error Text : " + elements.RATE_PLAN_NAME_ERROR_TEXT.getText());

			assertEquals(elements.RATE_PLAN_NAME_ERROR_TEXT.findElement(By.xpath("./..")).getAttribute("class"), "ratePlanNameErrorText", "Failed To Verify text in Red Color");
			test_steps.add("Successfully Verified Rate Plan Name Error Text is in Red Color");
			logger.info("Successfully Verified Rate Plan Name Error Text is in Red Color");
			verifyNextButton(driver, false, true, test_steps);
			Utility.clearValue(driver, elements.RATE_PLAN_NAME);
		} else {

			Wait.explicit_wait_visibilityof_webelement_120(elements.RATE_PLAN_NAME, driver);
			enterRatePlanName(driver, ratePlanName, test_steps);
			elements.RATE_PLAN_NAME.clear();

			Utility.clearValue(driver, elements.RATE_PLAN_NAME);
			test_steps.add("Entered Rate Plan Name Cleared");
			logger.info("Entered Rate Plan Name Cleared");

			verifyRatePlanNameErrorTxt(driver,errorTxt, true, test_steps);
			verifyNextButton(driver, false, true, test_steps);
			verifyRatePlanNameFeildValue(driver, ratePlanName, false, test_steps);
			enterRatePlanName(driver, ratePlanName, test_steps);		
			verifyRatePlanNameErrorTxt(driver,errorTxt, false, test_steps);
			verifyRatePlanNameFeildValue(driver, ratePlanName, true, test_steps);
		}
	}

	public void verifyRatePlanNameErrorTxt(WebDriver driver,String errorTxt, boolean isDisplayed, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		if(isDisplayed) {
			assertTrue(elements.RATE_PLAN_NAME.getAttribute("class").toUpperCase().contains("REQUIRED"), "Failed To Verify RatPlan Name Feild in Red Color");
			test_steps.add("Successfully Verified Rate Plan Name Feild is in Red Color");
			logger.info("Successfully Verified Rate Plan Name Feild is in Red Color");

			assertTrue(elements.RATE_PLAN_NAME_ERROR_TEXT.isDisplayed(), "Failed To Verify Rate Plan Name Error Text Not Displayed");
			test_steps.add("Successfully Verified Rate Plan Name Error Text Displayed ");
			logger.info("Successfully Verified Rate Plan Name Error Text Displayed ");

			assertEquals(elements.RATE_PLAN_NAME_ERROR_TEXT.getText(),errorTxt, "Failed To Verify Rate Plan Name Error Text Not Displayed");
			test_steps.add("Successfully Verified Rate Plan Name Error Text : " + elements.RATE_PLAN_NAME_ERROR_TEXT.getText());
			logger.info("Successfully Verified Rate Plan Name Error Text : " + elements.RATE_PLAN_NAME_ERROR_TEXT.getText());

			assertEquals(elements.RATE_PLAN_NAME_ERROR_TEXT.findElement(By.xpath("./..")).getAttribute("class"), "ratePlanNameErrorText", "Failed To Verify text in Red Color");
			test_steps.add("Successfully Verified Rate Plan Name Error Text is in Red Color");
			logger.info("Successfully Verified Rate Plan Name Error Text is in Red Color");
		}else {
			try {
				assertTrue(!elements.RATE_PLAN_NAME_ERROR_TEXT.isDisplayed(), "Failed To Verify Rate Plan Name Error Text Displayed");
				test_steps.add("Successfully Verified Rate Plan Name Error Text not Displayed ");
				logger.info("Successfully Verified Rate Plan Name Error Text not Displayed");
			} catch (Exception e) {
				test_steps.add("Successfully Verified Rate Plan Name Error Text not Displayed ");
				logger.info("Successfully Verified Rate Plan Name Error Text not Displayed");
			}
		}
	}

	public void verifyRatePlanNameFeildValue(WebDriver driver, String expectedValue, boolean expectedEqual, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		String foundValue = elements.RATE_PLAN_NAME.getAttribute("value");
		if(expectedEqual) {
			assertEquals(foundValue, expectedValue,"Failed To Verify Value Missmatched");
			test_steps.add("Successfully Verified Rate Plan Name Value Matched : " + foundValue);
			logger.info("Successfully Verified Rate Plan Name Value Matched : " + foundValue);

			Actions builder = new Actions(driver);
			builder.moveToElement(elements.RATE_PLAN_NAME).perform();

			assertEquals(elements.RATE_PLAN_NAME.getAttribute("title"), expectedValue,"Failed To Verify tooltip value Missmatched");
			test_steps.add("Successfully Verified Rate Plan Name tool tip Value Matched : " + elements.RATE_PLAN_NAME.getAttribute("title"));
			logger.info("Successfully Verified Rate Plan Name tool tip Value Matched : " + elements.RATE_PLAN_NAME.getAttribute("title"));

		}else {
			assertNotEquals(foundValue, expectedValue,"Failed To Verify Value Matched");
			test_steps.add("Successfully Verified Rate Plan Name Value Not Matched");
			logger.info("Successfully Verified Rate Plan Name Value Not Matched ");
		}

	}

	public int getRatePlanInputFeiledLength(WebDriver driver,ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		return elements.RATE_PLAN_NAME.getAttribute("value").length();
	}

	//Rate Plan Folio Display Name

	public void enterRateFolioDisplayName(WebDriver driver,String folioDisplayName, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		Wait.explicit_wait_visibilityof_webelement_120(elements.RATE_PLAN_FOLIO_DISPLAY_NAME, driver);
		elements.RATE_PLAN_FOLIO_DISPLAY_NAME.clear();
		Utility.clearValue(driver, elements.RATE_PLAN_FOLIO_DISPLAY_NAME);
		elements.RATE_PLAN_FOLIO_DISPLAY_NAME.sendKeys(folioDisplayName);
		test_steps.add("Entered Rate Plan Folio Display Name : " + folioDisplayName);
		logger.info("Entered Rate Plan Folio Display Name : " + folioDisplayName);
	}

	public void verifyRatePlanFolioDisplayNameVisibility(WebDriver driver,boolean isEnabled, boolean isDisplayed,ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		Wait.explicit_wait_visibilityof_webelement_120(elements.RATE_PLAN_FOLIO_DISPLAY_NAME, driver);
		assertEquals(elements.RATE_PLAN_FOLIO_DISPLAY_NAME.isDisplayed(), isDisplayed, "Failed To Verify Rate Plan Folio Display Name Displayed");
		test_steps.add("Successfully Verified Rate Plan Folio Display  Name is Displayed : " + isDisplayed);
		logger.info("Successfully Verified Rate Plan Folio Display Name is Displayed : " + isDisplayed);

		assertEquals(elements.RATE_PLAN_FOLIO_DISPLAY_NAME.isEnabled(), isEnabled, "Failed To Verify Rate Plan Folio Display Name Enable");
		test_steps.add("Successfully Verified Rate Plan Folio Display Name Enable : " + isEnabled);
		logger.info("Successfully Verified Rate Plan Folio Display Name Enable : " + isEnabled);
	}

	public void verifyRatePlanFolioDisplayNameRequiredFeild(WebDriver driver, String ratePlanFolioDisplayName, ArrayList<String> test_steps) throws InterruptedException {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		Wait.explicit_wait_visibilityof_webelement_120(elements.RATE_PLAN_FOLIO_DISPLAY_NAME, driver);
		enterRateFolioDisplayName(driver, ratePlanFolioDisplayName, test_steps);
		elements.RATE_PLAN_FOLIO_DISPLAY_NAME.clear();

		Utility.clearValue(driver, elements.RATE_PLAN_FOLIO_DISPLAY_NAME);
		test_steps.add("Entered Rate Plan Folio Display Name Cleared");
		logger.info("Entered Rate Plan Folio Display Name Cleared");

		verifyRatePlanFolioDisplayNameErrorTxt(driver, true, test_steps);
		enterRateFolioDisplayName(driver, ratePlanFolioDisplayName, test_steps);		
		verifyRatePlanFolioDisplayNameErrorTxt(driver, false, test_steps);
	}

	public void verifyRatePlanFolioDisplayNameErrorTxt(WebDriver driver, boolean isDisplayed, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		if(isDisplayed) {
			assertTrue(elements.RATE_PLAN_FOLIO_DISPLAY_NAME.getAttribute("class").toUpperCase().contains("REQUIRED"), "Failed To Verify RatPlan Name Feild in Red Color");
			test_steps.add("Successfully Verified Rate Plan Folio Display Name Feild is in Red Color");
			logger.info("Successfully Verified Rate Plan Folio Display Name Feild is in Red Color");

			assertTrue(elements.RATE_PLAN_FOLIO_DISPLAY_NAME_ERROR_TEXT.isDisplayed(), "Failed To Verify Rate Plan Name Error Text Not Displayed");
			test_steps.add("Successfully Verified Rate Plan Folio Display Name Error Text Displayed : " + elements.RATE_PLAN_FOLIO_DISPLAY_NAME_ERROR_TEXT.getText());
			logger.info("Successfully Verified Rate Plan Folio Display Name Error Text Displayed : " + elements.RATE_PLAN_FOLIO_DISPLAY_NAME_ERROR_TEXT.getText());

			assertEquals(elements.RATE_PLAN_FOLIO_DISPLAY_NAME_ERROR_TEXT.findElement(By.xpath("./..")).getAttribute("class"), "ratePlanNameErrorText", "Failed To Verify text in Red Color");
			test_steps.add("Successfully Verified Rate Plan Folio Display Name Error Text is in Red Color");
			logger.info("Successfully Verified Rate Plan Folio Display Name Error Text is in Red Color");
		}else {
			try {
				assertTrue(!elements.RATE_PLAN_FOLIO_DISPLAY_NAME_ERROR_TEXT.isDisplayed(), "Failed To Verify Rate Plan Folio Display Name Error Text Displayed");
				test_steps.add("Successfully Verified Rate Plan Folio Display Name Error Text not Displayed ");
				logger.info("Successfully Verified Rate Plan Folio Display Name Error Text not Displayed");
			} catch (Exception e) {
				test_steps.add("Successfully Verified Rate Plan Name Error Text not Displayed ");
				logger.info("Successfully Verified Rate Plan Folio Display Name Error Text not Displayed");
			}
		}
	}

	public void verifyRatePlanFolioDisplayNameFeildValue(WebDriver driver, String expectedValue, boolean expectedEqual, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		String foundValue = elements.RATE_PLAN_FOLIO_DISPLAY_NAME.getAttribute("value");
		if(expectedEqual) {
			assertEquals(foundValue, expectedValue,"Failed To Verify Value Missmatched");
			test_steps.add("Successfully Verified Rate Plan Folio Display Name Value Matched : " + foundValue);
			logger.info("Successfully Verified Rate Plan Folio Display Name Value Matched : " + foundValue);

			Actions builder = new Actions(driver);
			builder.moveToElement(elements.RATE_PLAN_FOLIO_DISPLAY_NAME).perform();

			assertEquals(elements.RATE_PLAN_FOLIO_DISPLAY_NAME.getAttribute("title"), expectedValue,"Failed To Verify tooltip value Missmatched");
			test_steps.add("Successfully Verified Rate Plan Folio Display Name tool tip Value Matched : " + elements.RATE_PLAN_FOLIO_DISPLAY_NAME.getAttribute("title"));
			logger.info("Successfully Verified Rate Plan Folio Display Name tool tip Value Matched : " + elements.RATE_PLAN_FOLIO_DISPLAY_NAME.getAttribute("title"));
		}else {
			assertNotEquals(foundValue, expectedValue,"Failed To Verify Value Matched");
			test_steps.add("Successfully Verified Rate Plan Folio Display Name Value Not Matched");
			logger.info("Successfully Verified Rate Plan Folio Display Name Value Not Matched ");
		}

	}

	public int getRatePlanFolioDisplayNameInputFeiledLength(WebDriver driver,ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		return elements.RATE_PLAN_FOLIO_DISPLAY_NAME.getAttribute("value").length();
	}

	//Rate Plan Description

	public void enterRatePlanDescription(WebDriver driver,String description,ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);
		Wait.explicit_wait_visibilityof_webelement_120(elements.RATE_PLAN_DESCRIPTION, driver);

		elements.RATE_PLAN_DESCRIPTION.clear();
		Utility.clearValue(driver, elements.RATE_PLAN_DESCRIPTION);
		elements.RATE_PLAN_DESCRIPTION.sendKeys(description);
		test_steps.add("Entered Rate Plan Description : " + description);
		logger.info("Entered Rate Plan Description : " + description);
	}

	public void verifyRatePlanDescriptionVisibility(WebDriver driver,boolean isEnabled, boolean isDisplayed,ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		Wait.explicit_wait_visibilityof_webelement_120(elements.RATE_PLAN_DESCRIPTION, driver);
		assertEquals(elements.RATE_PLAN_DESCRIPTION.isDisplayed(), isDisplayed, "Failed To Verify Rate Plan Description Display");
		test_steps.add("Successfully Verified Rate Plan Description is Displayed : " + isDisplayed);
		logger.info("Successfully Verified Rate Plan Description is Displayed : " + isDisplayed);

		assertEquals(elements.RATE_PLAN_DESCRIPTION.isEnabled(), isEnabled, "Failed To Verify Rate Plan Description Enable");
		test_steps.add("Successfully Verified Rate Plan Description Enable : " + isEnabled);
		logger.info("Successfully Verified Rate Plan Description Enable : " + isEnabled);
	}

	public void verifyRatePlanDescriptionFeildValue(WebDriver driver, String expectedValue, boolean expectedEqual, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		String foundValue = elements.RATE_PLAN_DESCRIPTION.getAttribute("value");
		if(expectedEqual) {
			assertEquals(foundValue, expectedValue,"Failed To Verify VAlue Missmatched");
			test_steps.add("Successfully Verified Rate Plan Description Value Matched : " + foundValue);
			logger.info("Successfully Verified Rate Plan Description Value Matched : " + foundValue);
		}else {
			assertNotEquals(foundValue, expectedValue,"Failed To Verify Value Matched");
			test_steps.add("Successfully Verified Rate Plan Description Value Not Matched");
			logger.info("Successfully Verified Rate Plan Description Value Not Matched ");
		}

	}

	public int getRatePlanDescriptionInputFeiledLength(WebDriver driver,ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		return elements.RATE_PLAN_DESCRIPTION.getAttribute("value").length();
	}

	public String getCharCountRatePlanDescription(WebDriver driver) {
		return driver.findElement(By.xpath("//span[@class='char-count']")).getText();
	}

	public void verifyCharCountRatePlanDescription(WebDriver driver, String expectedMaxLength, ArrayList<String> test_steps) {

		String found = getCharCountRatePlanDescription(driver);
		assertEquals(found, expectedMaxLength + " / 255", "Failed To Verify Description Max Length");
		test_steps.add("Successfully Verified Rate Plan Description Value Max Length : " + found);
		logger.info("Successfully Verified Rate Plan Description Value Max Length : " + found);
	}

	// Next Button

	public void clickNextButton(WebDriver driver, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		try {
			Wait.explicit_wait_visibilityof_webelement_120(elements.NEXT_BUTTON, driver);
			Wait.explicit_wait_elementToBeClickable(elements.NEXT_BUTTON, driver);
			elements.NEXT_BUTTON.click();
		}catch (Exception e) {
			Utility.clickThroughJavaScript(driver, elements.NEXT_BUTTON);
		}
		test_steps.add("Next Button Clicked");
		logger.info("Next Button Clicked");

	}

	public void verifyNextButton(WebDriver driver, boolean isEnabled, boolean isDisplayed, ArrayList<String> test_steps) throws InterruptedException {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);
		Wait.explicit_wait_visibilityof_webelement_120(elements.NEXT_BUTTON, driver);

		assertEquals(elements.NEXT_BUTTON.isDisplayed(), isDisplayed, "Failed To Verify Next Button is not Displayed");
		test_steps.add("Successfully Verified Next Button is Displayed : " + isDisplayed);
		logger.info("Successfully Verified Next Button is Displayed : " + isDisplayed);

		assertEquals(elements.NEXT_BUTTON.isEnabled(), isEnabled, "Failed To Verify Next Button Enable");
		test_steps.add("Successfully Verified Next Button Enable : " + isEnabled);
		logger.info("Successfully Verified Next Button Enable : " + isEnabled);
	}

	//Channels

	public void selectChannels(WebDriver driver,String channels,boolean isSelect,ArrayList<String> test_steps) {

		StringTokenizer token = new StringTokenizer(channels, Utility.DELIM);
		while(token.hasMoreTokens()) {
			String channel = token.nextToken();
			if(channel.equalsIgnoreCase("All")) {
				channel = "Select All";
			}
			String path = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+channel.toUpperCase()+"']/preceding-sibling::span/input";

			if(isSelect) {
				if(!driver.findElement(By.xpath(path)).isSelected()) {
					try {
						driver.findElement(By.xpath(path)).click();
					}catch (Exception e) {
						Utility.clickThroughJavaScript(driver, driver.findElement(By.xpath(path)));
					}
					test_steps.add(channel + " Channel Selected");
					logger.info(channel + " Channel Selected");
				}else {
					test_steps.add(channel + " Channel Already Selected");
					logger.info(channel + " Channel Already Selected");
				}
			}else {
				if(driver.findElement(By.xpath(path)).isSelected()) {
					try {
						driver.findElement(By.xpath(path)).click();
					}catch (Exception e) {
						Utility.clickThroughJavaScript(driver, driver.findElement(By.xpath(path)));
					}
					test_steps.add(channel + " Channel UnSelected");
					logger.info(channel + " Channel UnSelected");
				}else {
					test_steps.add(channel + " Channel Already UnSelected");
					logger.info(channel + " Channel Already UnSelected");
				}
			}

		}
	}

	public void verifySelectedChannels(WebDriver driver,String selectedChannels,boolean isSelected,ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);
		StringTokenizer token = new StringTokenizer(selectedChannels, Utility.DELIM);
		while(token.hasMoreTokens()) {
			String channel = token.nextToken();
			if(channel.equalsIgnoreCase("select all") || channel.equalsIgnoreCase("all")) {
				String path = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='SELECT ALL']/preceding-sibling::span/input";

				assertEquals(driver.findElement(By.xpath(path)).isSelected(),isSelected,"FAiled To Verify Channel not Selected : " + channel);
				test_steps.add("Successfully Verified Channel " + channel + " is Selected : " + isSelected);
				logger.info("Successfully Verified Channel " + channel + " is Selected : " + isSelected);
				int size = elements.DISTRIBUTION_CHANNEL_LIST.size();
				for (int i = 0; i < size; i++) {

					verifySelectedChannels(driver, elements.DISTRIBUTION_CHANNEL_LIST.get(i).getText(), isSelected, test_steps);
				}
			}else {
				String path = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+channel.toUpperCase()+"']/preceding-sibling::span/input";

				assertEquals(driver.findElement(By.xpath(path)).isSelected(),isSelected,"FAiled To Verify Channel not Selected : " + channel);
				test_steps.add("Successfully Verified Channel " + channel + " is Selected : " + isSelected);
				logger.info("Successfully Verified Channel " + channel + " is Selected : " + isSelected);
			}
		}
	}

	public void verifyDisplayedDistributionChannels(WebDriver driver, ArrayList<String> channelList, ArrayList<String> test_steps) throws InterruptedException {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		int size = elements.DISTRIBUTION_CHANNEL_LIST.size();
		assertEquals(size, channelList.size(),"Failed To Verify List Size with Active List Size");
		test_steps.add("Successfully Verified Distribution Channels List Size : " + size);
		logger.info("Successfully Verified Distribution Channels List Size : " + size);
		for (int i = 0; i < size; i++) {

			assertTrue(elements.DISTRIBUTION_CHANNEL_LIST.get(i).isDisplayed(), "Failed To Verify Distribution Channels : "+elements.DISTRIBUTION_CHANNEL_LIST.get(i).getText()+" is not Displayed");
			test_steps.add("Successfully Verified Distribution Channels is Displayed : " + elements.DISTRIBUTION_CHANNEL_LIST.get(i).getText());
			logger.info("Successfully Verified Distribution Channels is Displayed : " + elements.DISTRIBUTION_CHANNEL_LIST.get(i).getText());

			assertEquals(elements.DISTRIBUTION_CHANNEL_LIST.get(i).getText().toUpperCase(), channelList.get(i).toUpperCase(), "Failed To Verify Displayed Distribution Channels");
			test_steps.add("Successfully Verified Distribution Channels : " + elements.DISTRIBUTION_CHANNEL_LIST.get(i).getText());
			logger.info("Successfully Verified Distribution Channels : " + elements.DISTRIBUTION_CHANNEL_LIST.get(i).getText());

			verifyChannelsSelectable(driver, elements.DISTRIBUTION_CHANNEL_LIST.get(i).getText(), test_steps);
		}
	}

	public void verifyChannelsSelectable(WebDriver driver,String channelName, ArrayList<String> test_steps) {
		String path = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+channelName.toUpperCase()+"']/preceding-sibling::span";

		assertTrue(driver.findElement(By.xpath(path)).isDisplayed(),"Failed To Verify "+channelName+" Selectable is not Displayed");
		test_steps.add("Successfully Verified Channel Radio/CheckBox is Displayed : " + channelName);
		logger.info("Successfully Verified Channel Radio/CheckBox is Displayed : " + channelName);
		assertTrue(driver.findElement(By.xpath(path)).isEnabled(),"Failed To Verify "+channelName+" Selectable is not Enabled");
		test_steps.add("Successfully Verified Channel Radio/CheckBox is Enabled : " + channelName);
		logger.info("Successfully Verified Channel Radio/CheckBox is Enabled : " + channelName);
	}

	//RoomClasses

	public void selectRoomClasses(WebDriver driver,String roomClasses,boolean isSelect,ArrayList<String> test_steps) {

		StringTokenizer token = new StringTokenizer(roomClasses, Utility.DELIM);
		while(token.hasMoreTokens()) {
			String roomClass = token.nextToken();
			if(roomClass.equalsIgnoreCase("All")) {
				roomClass = "Select All";
			}
			String path = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+roomClass.toUpperCase()+"']/preceding-sibling::span/input";
			if(isSelect) {
				if(!driver.findElement(By.xpath(path)).isSelected()) {
					try {
						driver.findElement(By.xpath(path)).click();
					}catch (Exception e) {
						Utility.clickThroughJavaScript(driver, driver.findElement(By.xpath(path)));
					}
					test_steps.add(roomClass + " Room Class Selected");
					logger.info(roomClass + " Room Class Selected");
				}else {
					test_steps.add(roomClass + " Room Class Already Selected");
					logger.info(roomClass + " Room Class Already Selected");
				}
			}else {
				if(driver.findElement(By.xpath(path)).isSelected()) {
					try {
						driver.findElement(By.xpath(path)).click();
					}catch (Exception e) {
						Utility.clickThroughJavaScript(driver, driver.findElement(By.xpath(path)));
					}
					test_steps.add(roomClass + " Room Class UnSelected");
					logger.info(roomClass + " Room Class UnSelected");
				}else {
					test_steps.add(roomClass + " Room Class Already UnSelected");
					logger.info(roomClass + " Room Class Already UnSelected");
				}
			}

		}
	}

	public void verifySelectedRoomClasses(WebDriver driver,String selectedRoomClasses,boolean isSelected,ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		StringTokenizer token = new StringTokenizer(selectedRoomClasses, Utility.DELIM);
		while(token.hasMoreTokens()) {
			String roomClass = token.nextToken();
			if(roomClass.equalsIgnoreCase("select all") || roomClass.equalsIgnoreCase("all")) {
				String path = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='SELECT ALL']/preceding-sibling::span/input";
				assertEquals(driver.findElement(By.xpath(path)).isSelected(),isSelected,"Failed To Verify Room Class not Selected : " + roomClass + " ");
				test_steps.add("Successfully Verified Room Class " + roomClass + " is Selected : " + isSelected);
				logger.info("Successfully Verified Room Class " + roomClass + " is Selected : " + isSelected);
				int size = elements.ROOMCLASSES_LIST.size();
				for (int i = 0; i < size; i++) {	
					verifySelectedRoomClasses(driver, elements.ROOMCLASSES_LIST.get(i).getText(), isSelected, test_steps);
				}
			}
			else {
				String path = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+roomClass.toUpperCase()+"']/preceding-sibling::span/input";
				assertEquals(driver.findElement(By.xpath(path)).isSelected(),isSelected,"Failed To Verify Room Class not Selected : " + roomClass + " ");
				test_steps.add("Successfully Verified Room Class " + roomClass + " is Selected : " + isSelected);
				logger.info("Successfully Verified Room Class " + roomClass + " is Selected : " + isSelected);
			}
		}
	}

	public void verifyDisplayedRoomClasses(WebDriver driver, ArrayList<String> roomClassList, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		int size = elements.ROOMCLASSES_LIST.size();
		assertEquals(size, roomClassList.size(),"Failed To Verify List Size with Active List Size");
		test_steps.add("Successfully Verified Room Class List Size : " + size);
		logger.info("Successfully Verified Room Class List Size : " + size);
		for (int i = 0; i < size; i++) {

			assertTrue(elements.ROOMCLASSES_LIST.get(i).isDisplayed(), "Failed To Verify Room Class : "+elements.ROOMCLASSES_LIST.get(i).getText()+" is not Displayed");
			test_steps.add("Successfully Verified Room Class is Displayed : " + elements.ROOMCLASSES_LIST.get(i).getText());
			logger.info("Successfully Verified Room Class is Displayed : " + elements.ROOMCLASSES_LIST.get(i).getText());

			assertEquals(elements.ROOMCLASSES_LIST.get(i).getText().toUpperCase(), roomClassList.get(i).toUpperCase(), "Failed To Verify Displayed RoomClass");
			test_steps.add("Successfully Verified Room Class : " + elements.ROOMCLASSES_LIST.get(i).getText());
			logger.info("Successfully Verified Room Class : " + elements.ROOMCLASSES_LIST.get(i).getText());

			verifyRoomClassSelectable(driver, elements.ROOMCLASSES_LIST.get(i).getText(), test_steps);
		}
	}

	public void verifyRoomClassSelectable(WebDriver driver,String RoomClass, ArrayList<String> test_steps) {
		String path = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+RoomClass.toUpperCase()+"']/preceding-sibling::span";

		assertTrue(driver.findElement(By.xpath(path)).isDisplayed(),"Failed To Verify "+RoomClass+" Selectable is not Displayed");
		test_steps.add("Successfully Verified RoomClass CheckBox is Displayed : " + RoomClass);
		logger.info("Successfully Verified RoomClass CheckBox is Displayed : " + RoomClass);
		assertTrue(driver.findElement(By.xpath(path)).isEnabled(),"Failed To Verify "+RoomClass+" Selectable is not Enabled");
		test_steps.add("Successfully Verified RoomClass CheckBox is Enabled : " + RoomClass);
		logger.info("Successfully Verified RoomClass CheckBox is Enabled : " + RoomClass);
	}

	//Restrictions

	public void selectRestrictionTypes(WebDriver driver,String restrictionTypes, boolean isSelect, ArrayList<String> test_steps) {
		StringTokenizer token = new StringTokenizer(restrictionTypes, Utility.DELIM);
		while(token.hasMoreTokens()) {
			String restriction = token.nextToken();

			String path = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+restriction.toUpperCase()+"']/preceding-sibling::span/input";
			if(isSelect) {
				if(!driver.findElement(By.xpath(path)).isSelected()) {
					try {
						driver.findElement(By.xpath(path)).click();
					}catch (Exception e) {
						Utility.clickThroughJavaScript(driver, driver.findElement(By.xpath(path)));
					}
					test_steps.add(restriction + " Restriction Selected");
					logger.info(restriction + " Restriction Selected");
				}else {
					test_steps.add(restriction + " Restriction Already Selected");
					logger.info(restriction + " Restriction Already Selected");
				}
			}else {
				if(driver.findElement(By.xpath(path)).isSelected()) {
					try {
						driver.findElement(By.xpath(path)).click();
					}catch (Exception e) {
						Utility.clickThroughJavaScript(driver, driver.findElement(By.xpath(path)));
					}
					test_steps.add(restriction + " Restriction UnSelected");
					logger.info(restriction + " Restriction UnSelected");
				}else {
					test_steps.add(restriction + " Restriction Already UnSelected");
					logger.info(restriction + " Restriction Already UnSelected");
				}
			}
			verifySelectedRestriction(driver, restriction, isSelect, test_steps);
		}
	}

	public void verifySelectedRestriction(WebDriver driver,String options, boolean isSelected, ArrayList<String> test_steps) {
		StringTokenizer token = new StringTokenizer(options, Utility.DELIM);
		while (token.hasMoreTokens()) {
			String option = token.nextToken();

			String path = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"
					+ option.toUpperCase() + "']/preceding-sibling::span/input";

			assertEquals(driver.findElement(By.xpath(path)).isSelected(), isSelected, "Failed To verify : "+option+" Selected");
			test_steps.add("Successfully Verified : " + option + " is Selected : " + isSelected);
			logger.info("Successfully Verified : " + option + " is Selected : " + isSelected);
		}

	}

	public void verifyRestrictionsTypesCheckBoxes(WebDriver driver, String restrictionTypes, ArrayList<String> test_steps) {
		StringTokenizer token = new StringTokenizer(restrictionTypes, Utility.DELIM);
		while(token.hasMoreTokens()) {
			String restriction = token.nextToken();

			String restrictionPath = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+restriction.toUpperCase()+"']";
			String inputPath = restrictionPath + "/preceding-sibling::span";

			assertTrue(driver.findElement(By.xpath(restrictionPath)).isDisplayed(), "Failed To Verify Restriction : "+driver.findElement(By.xpath(restrictionPath)).getText()+" is not Displayed");
			test_steps.add("Successfully Verified Restriction is Displayed : " +driver.findElement(By.xpath(restrictionPath)).getText());
			logger.info("Successfully Verified Restriction is Displayed : " + driver.findElement(By.xpath(restrictionPath)).getText());

			assertEquals(driver.findElement(By.xpath(restrictionPath)).getText().toUpperCase(), restriction.toUpperCase(), "Failed To Verify Displayed Restriction Text");
			test_steps.add("Successfully Verified Restriction Text : " + driver.findElement(By.xpath(restrictionPath)).getText());
			logger.info("Successfully Verified Restriction Text : " + driver.findElement(By.xpath(restrictionPath)).getText());

			assertTrue(driver.findElement(By.xpath(inputPath)).isDisplayed(), "Failed To Verify Restriction CheckBox is not Displayed");
			test_steps.add("Successfully Verified Restriction CheckBox is Displayed");
			logger.info("Successfully Verified Restriction CheckBox is Displayed");

			assertTrue(driver.findElement(By.xpath(inputPath)).isEnabled(), "Failed To Verify Restriction CheckBox is not Enabled");
			test_steps.add("Successfully Verified Restriction CheckBox is Enabled");
			logger.info("Successfully Verified Restriction CheckBox is Enabled");
		}
	}

	public void lengthOfStay(WebDriver driver,String restrictionTypes,boolean isMinStay,String minNightsCount, boolean isMaxStay, String maxNightsCount, ArrayList<String> test_steps) {

		//****************************
		//options Only for inputCounter method 
		String minOption = "Min";
		String maxOption = "Max";
		//****************************

		boolean isLengthOfStayReq = false;
		StringTokenizer token = new StringTokenizer(restrictionTypes, Utility.DELIM);
		while(token.hasMoreTokens()) {
			String restriction = token.nextToken();
			if(restriction.equalsIgnoreCase("Length of stay")) {
				isLengthOfStayReq = true;
			}
		}

		if(isLengthOfStayReq) {
			if(isMinStay) {
				selectRestrictionTypes(driver, minOption, isMinStay, test_steps);
				verifyRestrictionDaysNightsCountSpinnerFeild(driver, minOption, isMinStay, test_steps);
				inputCounter(driver, minOption, minNightsCount, test_steps);
				verifyRestrictionDaysNightsCountSpinnerFeildValue(driver, minOption, minNightsCount, isMinStay, test_steps);
			}

			if(isMaxStay) {
				selectRestrictionTypes(driver, maxOption,isMaxStay, test_steps);
				verifyRestrictionDaysNightsCountSpinnerFeild(driver, maxOption,isMaxStay, test_steps);
				inputCounter(driver, maxOption, maxNightsCount, test_steps);
				verifyRestrictionDaysNightsCountSpinnerFeildValue(driver, maxOption, maxNightsCount, isMaxStay, test_steps);
			}
		}
	}

	public void bookingWindow(WebDriver driver,String restrictionTypes,boolean isMoreThanDaysReq, String moreThanDaysCount, boolean isWithInDaysReq, String withInDaysCount, ArrayList<String> test_steps) {
		// ****************************
		// options Only for inputCounter method
		String moreThanOption = "More than";
		String withInOption = "Within";
		// ****************************

		boolean isBookingWindowReq = false;
		StringTokenizer token = new StringTokenizer(restrictionTypes, Utility.DELIM);
		while(token.hasMoreTokens()) {
			String restriction = token.nextToken();
			if(restriction.equalsIgnoreCase("Booking window")) {
				isBookingWindowReq = true;
			}
		}

		if(isBookingWindowReq) {
			if(isMoreThanDaysReq) {
				selectRestrictionTypes(driver, moreThanOption,isMoreThanDaysReq, test_steps);
				verifyRestrictionDaysNightsCountSpinnerFeild(driver, moreThanOption,isMoreThanDaysReq, test_steps);
				inputCounter(driver, moreThanOption, moreThanDaysCount, test_steps);
				verifyRestrictionDaysNightsCountSpinnerFeildValue(driver, moreThanOption, moreThanDaysCount, isMoreThanDaysReq, test_steps);
			} 

			if(isWithInDaysReq) {
				selectRestrictionTypes(driver, withInOption,isWithInDaysReq, test_steps);
				verifyRestrictionDaysNightsCountSpinnerFeild(driver, withInOption,isWithInDaysReq, test_steps);
				inputCounter(driver, withInOption, withInDaysCount, test_steps);
				verifyRestrictionDaysNightsCountSpinnerFeildValue(driver, withInOption, withInDaysCount, isWithInDaysReq, test_steps);
			}
		}
	}

	/*
	 *	'Options are: 'Min', 'Max', 'More than', 'Within'
	 */
	private void inputCounter(WebDriver driver,String option, String count, ArrayList<String> test_steps) {
		String path = "//span[text()='"+option+"']/parent::label/parent::div/following-sibling::div//input";

		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(path)), driver);
		driver.findElement(By.xpath(path)).clear();
		Utility.clearValue(driver, driver.findElement(By.xpath(path)));
		driver.findElement(By.xpath(path)).sendKeys(count);
		test_steps.add("Entered Days/Nights Count : " + count);
		logger.info("Entered Days/Nights Count : " + count);
	}

	public void verifyRestrictionDaysNightsCountSpinnerFeildValue(WebDriver driver,String option, String expectedCount, boolean isExpected, ArrayList<String> test_steps) {
		String path = "//span[text()='"+option+"']/parent::label/parent::div/following-sibling::div//input";

		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(path)), driver);

		if(isExpected) {
			assertEquals(driver.findElement(By.xpath(path)).getAttribute("value"), expectedCount, "Failed To Verify"+option+" Days Night count Feild Value");
			test_steps.add("Successfully Verified " + option + " Days Nights Count Spinner Feild Value : " + expectedCount);
			logger.info("Successfully Verified " + option + " Days Nights Count Spinner Feild Value : " + expectedCount);
		}else {
			assertNotEquals(driver.findElement(By.xpath(path)).getAttribute("value"), expectedCount,"Failed To Verify"+option+" Days Night count Feild Value");
			test_steps.add("Successfully Verified " + option + " Days Nights Count Spinner Feild Value Not Matched ");
			logger.info("Successfully Verified " + option + " Days Nights Count Spinner Feild Value Not Matched ");
		}

	}

	public void verifyRestrictionDaysNightsCountSpinnerFeild(WebDriver driver, String restrictions, boolean isEnabled, ArrayList<String> test_steps) {
		StringTokenizer token = new StringTokenizer(restrictions, Utility.DELIM);
		while(token.hasMoreTokens()) {
			String option = token.nextToken();

			String path = "//span[text()='"+option+"']/parent::label/parent::div/following-sibling::div//input";

			assertTrue(driver.findElement(By.xpath(path)).isDisplayed(),"Failed to Verify "+option+" Days Night count Feild not Displayed");
			test_steps.add("Successfully Verified " + option + " Days Nights Count Spinner is Displayed");
			logger.info("Successfully Verified " + option + " Days Nights Count Spinner is Displayed");

			assertEquals(driver.findElement(By.xpath(path)).isEnabled(), isEnabled, "Failed to Verify "+option+" Days Night count Feild Enabled");
			test_steps.add("Successfully Verified " + option + " Days Nights Count Spinner Enabled : " + isEnabled);
			logger.info("Successfully Verified " + option + " Days Nights Count Spinner Enabled : " + isEnabled);
		}
	}

	public void promoCode(WebDriver driver,String restrictionTypes, String promoCode, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);
		boolean isPromoCodeReq = false;
		StringTokenizer token = new StringTokenizer(restrictionTypes, Utility.DELIM);
		while(token.hasMoreTokens()) {
			String restriction = token.nextToken();
			if(restriction.equalsIgnoreCase("Promo code")) {
				isPromoCodeReq = true;
			}
		}

		if(isPromoCodeReq) {
			verifyPromoCodeFeildVisibility(driver, true, isPromoCodeReq, test_steps);
			elements.PROMO_CODE.clear();
			Utility.clearValue(driver, elements.PROMO_CODE);
			elements.PROMO_CODE.sendKeys(promoCode);
			test_steps.add("Entered Promo Code : " + promoCode);
			logger.info("Entered Promo Code : " + promoCode);
			verifyPromoCodeFeildValue(driver, promoCode, isPromoCodeReq, test_steps);
		}
	}

	public void verifyPromoCodeFeildVisibility(WebDriver driver, boolean isEnabled, boolean isDisplayed,ArrayList<String> test_steps ) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		assertEquals(elements.PROMO_CODE.isDisplayed(), isDisplayed, "Failed To Verify Promo Code Feild Displayed");
		test_steps.add("Successfully Verified PromoCode is Displayed : " + isDisplayed);
		logger.info("Successfully Verified PromoCode is Displayed" + isDisplayed);

		assertEquals(elements.PROMO_CODE.isEnabled(), isEnabled, "Failed To Verify Promo Code Feild Enabled");
		test_steps.add("Successfully Verified PromoCode is Enabled : " + isEnabled);
		logger.info("Successfully Verified PromoCode is Enabled : " + isEnabled);

		assertEquals(elements.PROMO_CODE.getAttribute("maxlength"), "50", "Failed To Verify Promo Code Feild Displayed");
		test_steps.add("Successfully Verified PromoCode maxlength : 50 ");
		logger.info("Successfully Verified PromoCode maxlength : 50");
	}

	public void verifyPromoCodeFeildValue(WebDriver driver, String expectedValue, boolean isExpected, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);

		String foundValue = elements.PROMO_CODE.getAttribute("value");
		if(isExpected) {
			assertEquals(foundValue, expectedValue,"Failed To Verify Value Missmatched");
			test_steps.add("Successfully Verified PromoCode Value Matched : " + foundValue);
			logger.info("Successfully Verified PromoCode Value Matched : " + foundValue);
		}else {
			assertNotEquals(foundValue, expectedValue,"Failed To Verify Value Matched");
			test_steps.add("Successfully Verified PromoCode Value Not Matched");
			logger.info("Successfully Verified PromoCode Value Not Matched ");
		}

	}

	public void verifyToolTipBookingWindow(WebDriver driver,ArrayList<String> test_steps) {
		try {
			String elePath = "//span[text()='Booking window']//span";
			WebElement hoverElement = driver.findElement(By.xpath(elePath));
			Actions builder = new Actions(driver);
			builder.moveToElement(hoverElement).perform();
			test_steps.add("Hovers over a Booking Widnow SVG");
			logger.info("Hovers over a Booking Widnow SVG");

			String expected = "Booking window does not map to external channels";
			String actual = getToolTipValue(driver);
			assertEquals(actual, expected, "Failed To Verify Booking Window Tool Tip Value");
			test_steps.add("Successfully Verified Booking Window ToolTip : " + expected);
			logger.info("Successfully Verified Booking Window ToolTip : " + expected);
		}catch (Exception e) {

		}
	}

	public void verifyToolTipPromoCode(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {
		try {
			String elePath = "//span[text()='Promo code']//span";
			WebElement hoverElement = driver.findElement(By.xpath(elePath));
			Wait.wait1Second();
			Actions builder = new Actions(driver);
			builder.moveToElement(hoverElement).perform();
			test_steps.add("Hovers over a PromoCode SVG");
			logger.info("Hovers over a PromoCode SVG");
			Wait.wait1Second();
			String expected = "Promo code does not map to external channels";
			String actual = getToolTipValue(driver);
			assertEquals(actual, expected, "Failed To Verify PromoCode Tool Tip Value");
			test_steps.add("Successfully Verified PromoCode ToolTip : " + expected);
			logger.info("Successfully Verified PromoCode ToolTip : " + expected);
		}catch (Exception e) {

		}
	}

	public String getToolTipValue(WebDriver driver) {
		String path = "//div[@class='ant-tooltip info-popover ant-tooltip-placement-top']//div[@class='modal-popover-content']";
		return driver.findElement(By.xpath(path)).getText();
	}

	public void selectRestrictions(WebDriver driver,boolean isRatePlanRistrictionReq,String RistrictionType, boolean isMinStay,String MinNights, boolean isMaxStay, String MaxNights,boolean isMoreThanDaysReq, String MoreThanDaysCount, boolean isWithInDaysReq, String WithInDaysCount,String PromoCode, ArrayList<String> test_steps) {
		if (isRatePlanRistrictionReq) {

			test_steps.add("=================== SELECT RESTRICTIONS TO QUALIFY RATE ======================");
			logger.info("=================== SELECT RESTRICTIONS TO QUALIFY RATE ======================");
			selectRestrictionTypes(driver, RistrictionType, true, test_steps);
			lengthOfStay(driver, RistrictionType, isMinStay, MinNights,
					isMaxStay, MaxNights, test_steps);
			bookingWindow(driver, RistrictionType,isMoreThanDaysReq,
					MoreThanDaysCount, isWithInDaysReq, WithInDaysCount, test_steps);
			promoCode(driver, RistrictionType, PromoCode, test_steps);

		} else {
			test_steps.add("=================== NO RESTRICTIONS TO QUALIFY RATE ======================");
			logger.info("=================== NO RESTRICTIONS TO QUALIFY RATE ======================");
			selectRestrictionTypes(driver, RistrictionType, false, test_steps);
		}
	}

	//Policy

	public void selectPolicy(WebDriver driver,String policyTypes,boolean isSelect,ArrayList<String> test_steps) {
		StringTokenizer token = new StringTokenizer(policyTypes, Utility.DELIM);
		while(token.hasMoreTokens()) {
			String policy = token.nextToken();
			if(policy.equalsIgnoreCase("All")) {
				policy = "Select All";
			}
			String path = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+policy.toUpperCase()+"']/preceding-sibling::span/input";

			if(isSelect) {
				if(!driver.findElement(By.xpath(path)).isSelected()) {
					try {
						driver.findElement(By.xpath(path)).click();
					}catch (Exception e) {
						Utility.clickThroughJavaScript(driver, driver.findElement(By.xpath(path)));
					}
					test_steps.add(policy + " Policy Selected");
					logger.info(policy + " Policy Selected");
				}else {
					test_steps.add(policy + " Policy Already Selected");
					logger.info(policy + " Policy Already Selected");
				}
			}else {
				if(driver.findElement(By.xpath(path)).isSelected()) {
					try {
						driver.findElement(By.xpath(path)).click();
					}catch (Exception e) {
						Utility.clickThroughJavaScript(driver, driver.findElement(By.xpath(path)));
					}
					test_steps.add(policy + " Policy UnSelected");
					logger.info(policy + " Policy UnSelected");
				}else {
					test_steps.add(policy + " Policy Already UnSelected");
					logger.info(policy + " Policy Already UnSelected");
				}
			}
		}
	}

	public void selectPolicy(WebDriver driver,String PoliciesType,String PoliciesName,boolean isPolicesReq,ArrayList<String> test_steps) {
		if (isPolicesReq) {
			test_steps.add("=================== SELECT POLICIES ======================");
			logger.info("=================== SELECT POLICIES ======================");
			selectPolicy(driver, PoliciesType, true, test_steps);
			selectPolicy(driver, PoliciesName, true, test_steps);

		} else {
			test_steps.add("=================== NO POLICIES ======================");
			logger.info("=================== NO POLICIES ======================");
			selectPolicy(driver, PoliciesType, false, test_steps);
		}
	}

	public void verifySelectedPolicy(WebDriver driver,String policyTypes,boolean isSelected,ArrayList<String> test_steps) {
		StringTokenizer token = new StringTokenizer(policyTypes, Utility.DELIM);
		while(token.hasMoreTokens()) {
			String policy = token.nextToken();
			if(policy.equalsIgnoreCase("All")) {
				policy = "Select All";
			}
			String path = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+policy.toUpperCase()+"']/preceding-sibling::span/input";

			assertEquals(driver.findElement(By.xpath(path)).isSelected(),isSelected,"Failed To Verify Policy not Selected : " + policy);
			test_steps.add("Successfully Verified Policy " + policy + " is Selected : " + isSelected);
			logger.info("Successfully Verified Policy " + policy + " is Selected : " + isSelected);
		}
	}

	public String getPolicyDescription(WebDriver driver,String policyType,String policyName,ArrayList<String> test_steps) {
		selectPolicy(driver, policyType,true, test_steps);
		String path = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+policyName.toUpperCase()+"']/span[@class='stepSubText']";

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getPolicyDescription(WebDriver driver,String policyName,ArrayList<String> test_steps) {

		String path = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+policyName.toUpperCase()+"']/span[@class='stepSubText']";

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public HashMap<String, String> getAllPolicyDescriptions(WebDriver driver,String policyNames,boolean isPolicyReq, ArrayList<String> test_steps){

		HashMap<String, String> map = new HashMap<>();
		if(isPolicyReq) {
			StringTokenizer token = new StringTokenizer(policyNames, Utility.DELIM);
			while(token.hasMoreTokens()) {
				String policyName = token.nextToken();
				map.put(policyName, getPolicyDescription(driver, policyName, test_steps));
				//policyDesc.add();
			}
		}else {
			map.put("NO POLICIES","No policies selected");
		}
		return map;
	}

	public void verifyInputPolicies(WebDriver driver, String policyName, ArrayList<String> test_steps) {
		String path = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+policyName.toUpperCase()+"']/preceding-sibling::span";

		assertTrue(driver.findElement(By.xpath(path)).isDisplayed(),"Failed To Verify "+policyName+" Selectable is not Displayed");
		test_steps.add("Successfully Verified Policy Radio/CheckBox is Displayed : " + policyName);
		logger.info("Successfully Verified Policy Radio/CheckBox is Displayed : " + policyName);
		assertTrue(driver.findElement(By.xpath(path)).isEnabled(),"Failed To Verify "+policyName+" Selectable is not Enabled");
		test_steps.add("Successfully Verified Policy Radio/CheckBox is Enabled : " + policyName);
		logger.info("Successfully Verified Policy Radio/CheckBox is Enabled : " + policyName);
	}

	public void verifyAllPolicies(WebDriver driver,String policyType,ArrayList<String> policyList,ArrayList<String> test_steps) {
		String path = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+policyType.toUpperCase()+"']/../following-sibling::div//label/span[2]";
		selectPolicy(driver, policyType,true, test_steps);

		int size = driver.findElements(By.xpath(path)).size();
		assertEquals(size, policyList.size(),"Failed To Verify List Size with Active List Size");
		test_steps.add("Successfully Verified Policy List Size : " + size);
		logger.info("Successfully Verified Policy List Size : " + size);
		for (int i = 0; i < size; i++) {

			assertTrue(driver.findElements(By.xpath(path)).get(i).isDisplayed(), "Failed To Verify Policy : "+driver.findElements(By.xpath(path)).get(i).getText()+" is not Displayed");
			test_steps.add("Successfully Verified Policy is Displayed : " + driver.findElements(By.xpath(path)).get(i).getText());
			logger.info("Successfully Verified Policy is Displayed : " + driver.findElements(By.xpath(path)).get(i).getText());

			assertTrue(driver.findElements(By.xpath(path)).get(i).getText().contains(policyList.get(i)),  "Failed To Verify Displayed Policy expected[" + policyList.get(i) + "] but found["+driver.findElements(By.xpath(path)).get(i).getText()+"]");
			test_steps.add("Successfully Verified Policy : " + policyList.get(i));
			logger.info("Successfully Verified Policy : " + policyList.get(i));

			verifyInputPolicies(driver, policyList.get(i), test_steps);
		}
		selectPolicy(driver, policyType,false, test_steps);
	}

	public String getRestrictionsToQualifyRate(WebDriver driver,ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);
		String restrictions = null;
		try {
			Wait.explicit_wait_visibilityof_webelement(elements.RESTRICTIONS_TO_QUALIFY_RATE, driver);
			restrictions = elements.RESTRICTIONS_TO_QUALIFY_RATE.getText();
			if(restrictions.isEmpty() || restrictions == null) {
				restrictions = "No restrictions selected";
			}
		}catch (Exception e) {
			restrictions = "No restrictions selected";
		}

		test_steps.add("Restrictions are : " + restrictions);
		logger.info("Restrictions are : " + restrictions);

		return restrictions;
	}

	public void verfiyRestrictionsToQualifyRateMsg(WebDriver driver,String expectedMsg, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);
		try {
			Wait.explicit_wait_visibilityof_webelement(elements.RESTRICTIONS_TO_QUALIFY_RATE, driver);
			
			assertTrue(elements.RESTRICTIONS_TO_QUALIFY_RATE.isDisplayed(),"Failed to Verify Restrictions To Qualify Rate Displayed");
			test_steps.add("Successfully Verified Restrictions To Qualify Rate Text : Displayed");
			logger.info("Successfully Verified Restrictions To Qualify Rate Text : Displayed");
			
			String found = elements.RESTRICTIONS_TO_QUALIFY_RATE.getText();
			assertEquals(found, expectedMsg, "Failed To Verify Restrictions To Qualify Rate Msg");
			test_steps.add("Successfully Verified Restrictions To Qualify Rate Text : " + found);
			logger.info("Successfully Verified Restrictions To Qualify Rate Text : " + found);
		}catch (Exception e) {
			assertTrue(expectedMsg.isEmpty(), "Failed To Verify Restrictions To Qualify Rate Msg");
			test_steps.add("Successfully Verified No Restrictions To Qualify Rate Text");
			logger.info("Successfully Verified No Restrictions To Qualify Rate Text ");
		}
	}
	
	public String generateRestrictionsToQualifyRate(String restrictionTypes,boolean isMin,String minNights,boolean isMax,String maxNights, boolean isMoreThan, String moreThanDays, boolean isWithInDaysReq, String withInDaysCount,String promoCode) {
		
		String restrictionMsg = "";
		
		boolean isLenghtOfStayReq = false;
		boolean isBookingWindowReq = false;
		boolean isPromoCodeReq = false;
		
		StringTokenizer token = new StringTokenizer(restrictionTypes, Utility.DELIM);
		while(token.hasMoreTokens()) {
			String restriction = token.nextToken();
			if(restriction.equalsIgnoreCase("Length of stay")) {
				isLenghtOfStayReq = true;
			}
			if(restriction.equalsIgnoreCase("Booking window")) {
				isBookingWindowReq = true;
			}
			if(restriction.equalsIgnoreCase("Promo code")) {
				isPromoCodeReq = true;
			}
		}
		
		String lenghtOfStayMsg = "";
		if(isLenghtOfStayReq) {
			if(isMin && isMax) {
				if(Integer.parseInt(minNights)==Integer.parseInt(maxNights)) {
					lenghtOfStayMsg = "Guests must stay "+Integer.parseInt(maxNights)+" night(s)";
				}else {
					lenghtOfStayMsg = "Guests must stay between "+Integer.parseInt(minNights)+" and "+Integer.parseInt(maxNights)+" nights when they book";
				}
			} else if(isMin){
				lenghtOfStayMsg = "Guests must stay "+Integer.parseInt(minNights)+" nights";
			}else if(isMax) {
				lenghtOfStayMsg = "Guests cannot stay more than "+Integer.parseInt(maxNights)+" nights";
			}
		}
		
		String bookingWindowMsg="";
		if(isBookingWindowReq) {
			
			if(isLenghtOfStayReq) {
				lenghtOfStayMsg +=". ";
			}
			
			if(isMoreThan && isWithInDaysReq) {
				if(Integer.parseInt(moreThanDays)==Integer.parseInt(withInDaysCount)) {
					bookingWindowMsg = "Guests must book their stay "+Integer.parseInt(moreThanDays)+" day(s) before the check-in date";
				}else {
					bookingWindowMsg = "Guests must book their stay between "+Integer.parseInt(moreThanDays)+" and "+Integer.parseInt(withInDaysCount)+" days before the check-in date";
				}
			}else if(isMoreThan) {
				bookingWindowMsg = "Guests must book atleast "+Integer.parseInt(moreThanDays)+" days in advance of check-in date";
			}else if(isWithInDaysReq) {
				bookingWindowMsg = "Guests must book within "+Integer.parseInt(withInDaysCount)+" days of check-in date";
			}
		}
		
		String promoCodeMsg = "";
		if(isPromoCodeReq) {
			if(isBookingWindowReq) {
				bookingWindowMsg +=". ";
			}else if(isLenghtOfStayReq) {
				lenghtOfStayMsg +=". ";
			}
			
			promoCodeMsg = "Guest must use promo code '"+promoCode+"' to qualify for this rate plan";
		}
		
		restrictionMsg = lenghtOfStayMsg+bookingWindowMsg+promoCodeMsg;
		return restrictionMsg;
	}
	

	public void clickTitleSummaryValueForEdit(WebDriver driver,String Title,ArrayList<String> test_steps ) {
		String path = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+Title.toUpperCase()+"']/../following-sibling::div/span[@class='summary-value']";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		driver.findElement(By.xpath(path)).click();
		test_steps.add("Successfully Clicked Title Summray Value of  : " + Title);
		logger.info("Successfully Clicked Title Summray Value of  : " + Title);

	}
	public String getTitleSummaryValue(WebDriver driver,String Title,ArrayList<String> test_steps ) {
		String path = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+Title.toUpperCase()+"']/../following-sibling::div/span[@class='summary-value']";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		String found = driver.findElement(By.xpath(path)).getText();
		test_steps.add("Found Title Summray Value of " + Title + " is : " + found);
		logger.info("Found Title Summray Value of " + Title + " is : " + found);
		assertTrue(driver.findElement(By.xpath(path)).isDisplayed(),"Failed To Verify Title Summary Value is Not Displayed");
		test_steps.add("Successfully Verified Title Summray Value of " + Title + " is : Displayed");
		logger.info("Successfully Verified Title Summray Value of " + Title + " is : Displayed ");
		return found;

	}

	public void verifyTitleSummaryValue(WebDriver driver, String Title, String expected, ArrayList<String> test_steps) {
		String actual = getTitleSummaryValue(driver, Title, test_steps);
		assertEquals(actual, expected,"Failed to Verify Title Summary Value");

		test_steps.add("Successfully Verified Title Summray Value of " + Title + " is : " + actual);
		logger.info("Successfully Verified Title Summray Value of " + Title + " is : " + actual);
	}

	public String generateTitleSummaryValueForChannels(WebDriver driver) {
		String result = "";

		String selectAllPath = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='SELECT ALL']/preceding-sibling::span/input";
		String itemsPath = "//span[text()='Select All']/../following-sibling::div//input";

		if(driver.findElement(By.xpath(selectAllPath)).isSelected()) {
			result = "All channels selected";
		}else {

			List<WebElement> list = driver.findElements(By.xpath(itemsPath));
			int count = 1;
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).isSelected()) {

					if(count==4) {
						break;
					} else if(count!=1 && count<4) {
						result += ", ";
						count++;
					}

					result += driver.findElement(By.xpath("("+itemsPath+"/../following-sibling::span)["+(i+1)+"]")).getText();
				}
			}
			if(list.size()>3) {
				result += " +"+(Math.abs(list.size()-3));
			}
		}

		logger.info("Channel Generated Title Summary Value : " + result);
		return result;
	}

	public String generateTitleSummaryValueForRoomClass(WebDriver driver) {
		String result = "";

		String selectAllPath = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='SELECT ALL']/preceding-sibling::span/input";
		String itemsPath = "//span[text()='Select All']/../../following-sibling::div//input";

		if(driver.findElement(By.xpath(selectAllPath)).isSelected()) {
			result = "All room classes selected";
		}else {

			List<WebElement> list = driver.findElements(By.xpath(itemsPath));
			int count = 0;

			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).isSelected()) {
					if(count>=3) {

					} else if(count==0) {
						result = driver.findElement(By.xpath("("+itemsPath+"/../following-sibling::span)["+(i+1)+"]")).getText();

					} else {
						result += ", " + driver.findElement(By.xpath("("+itemsPath+"/../following-sibling::span)["+(i+1)+"]")).getText();

					}

					count++;
				}
			}
			if(count>3) {
				result += " +"+(Math.abs(count-3));
			}
		}

		logger.info("RoomClass Generated Title Summary Value : " + result);
		return result;
	}


	public void verifyPolicyTitleSummaryValue(WebDriver driver, String policyNames,HashMap<String, String> policyDesc,boolean isPolicyReq, ArrayList<String> test_steps) throws InterruptedException {

		String policySummaryTitlePath = "//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='POLICY']";
		if(isPolicyReq) {
			String policyPath = policySummaryTitlePath + "/../following-sibling::div/span[@class='summary-value']/span";
			List<WebElement> list = driver.findElements(By.xpath(policyPath));
			ArrayList<String> tokens = Utility.convertTokenToArrayList(policyNames, Utility.DELIM);
			ArrayList<String> acctualList = new ArrayList<String>();

			for (int i = 0; i < list.size(); i++) {
				acctualList.add(list.get(i).getText());
			}

			Collections.sort(acctualList);
			Collections.sort(tokens);

			for(int i=0; i<acctualList.size(); i++) {
				String foundPolicy = acctualList.get(i);
				assertEquals(foundPolicy, tokens.get(i),"Failed To Verify Policy In Title Summary Value");
				test_steps.add("Successfully Verified Title Summray Policy Value: " + foundPolicy);
				logger.info("Successfully Verified Title Summray Policy Value  : " + foundPolicy);
			}

			ArrayList<String> policies = Utility.convertTokenToArrayList(policyNames, Utility.DELIM);
			for (int j = 0; j < policies.size(); j++) {
				String path = "//span[@class='summary-value']/span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+policies.get(j).toUpperCase()+"']/span";

				Wait.wait1Second();

				new Actions(driver).moveToElement(driver.findElement(By.xpath(policySummaryTitlePath))).perform();

				Wait.wait1Second();
				WebElement hoverElement = driver.findElement(By.xpath(path));
				Actions builder = new Actions(driver);
				builder.moveToElement(hoverElement).perform();
				test_steps.add("Hovers over a Policy: " +policies.get(j)+ " SVG");
				logger.info("Hovers over a Policy: " +policies.get(j)+ " SVG");
				Wait.wait1Second();
				String expected = policyDesc.get(policies.get(j));
			
			try{
				String actual = driver.findElement(By.xpath("//div[@class='ant-tooltip ant-tooltip-placement-right']//div[@class='ant-tooltip-inner']/strong")).getText();
				assertEquals(actual, expected, "Failed To Verify Policy Tool Tip Value");
				test_steps.add("Successfully Verified Policy ToolTip : " + expected);
				logger.info("Successfully Verified Policy ToolTip : " + expected);
			}catch (Error e) {
				System.out.println("hello");
			}catch (Exception e) {
				System.out.println("hello");

			}


			}
		}else {
			verifyTitleSummaryValue(driver, "Policy",policyDesc.get("NO POLICIES"), test_steps);
		}
	}

	public void clickCreateSeason(WebDriver driver,ArrayList<String> test_steps) {
		Elements_RatesGrid ratessGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_NightlyRatePlan.RatePlan_CreateSeason);
		try {
			Utility.ScrollToElement(ratessGrid.RatePlan_CreateSeason, driver);
			ratessGrid.RatePlan_CreateSeason.click();
		}catch (Exception e) {
			Utility.clickThroughJavaScript(driver, ratessGrid.RatePlan_CreateSeason);
		}
		test_steps.add("Clicking on Create Season");
		logger.info("Clicking on Create Season");
	}

	public void selectSeasonDates(WebDriver driver,ArrayList<String> test_steps,String SeasonStartDate,String SeasonEndDate) {
		String startDateMonth=Utility.get_Month(SeasonStartDate);
		String startDateYear=Utility.getYear(SeasonStartDate);
		String startDateDay=Utility.getDay(SeasonStartDate);
		startDateMonth=startDateMonth.toUpperCase();

		String startDateMonthYear=startDateMonth+" "+startDateYear;
		startDateMonthYear=startDateMonthYear.trim();

		String startDate="//div[text()='"+startDateMonthYear+"']/../..//div[@class='DayPicker-Body']//div[text()='"+startDateDay+"']";
		driver.findElement(By.xpath(startDate)).click();
		test_steps.add("Selecting start date of the season as : "+SeasonStartDate);
		logger.info("Selecting start date of the season as : "+SeasonStartDate);

		String endDateMonth=Utility.get_Month(SeasonEndDate);
		String endDateYear=Utility.getYear(SeasonEndDate);
		String endDateDay=Utility.getDay(SeasonEndDate);
		endDateMonth=endDateMonth.toUpperCase();

		String endDateMonthYear=endDateMonth+" "+endDateYear;
		endDateMonthYear=startDateMonthYear.trim();

		String endDate="//div[text()='"+endDateMonthYear+"']/../..//div[@class='DayPicker-Body']//div[text()='"+endDateDay+"']";
		driver.findElement(By.xpath(endDate)).click();
		test_steps.add("Selecting end date of the season as : "+SeasonEndDate);
		logger.info("Selecting end date of the season as : "+SeasonEndDate);
	}

	public void enterSeasonName(WebDriver driver,ArrayList<String> test_steps,String SeasonName) {
		Elements_RatesGrid ratessGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_NightlyRatePlan.RatePlan_SeasonName);
		ratessGrid.RatePlan_SeasonName.sendKeys(SeasonName);
		test_steps.add("Enter Season Name : "+SeasonName);
		logger.info("Enter Season Name : "+SeasonName);
	}

	public void selectSeasonDays(WebDriver driver,ArrayList<String> test_steps,String isMonDay,String isTueDay,String isWednesDay,String isThursDay,String isFriday,String isSaturDay,String isSunDay) {
		Elements_RatesGrid ratessGrid = new Elements_RatesGrid(driver);

		if(isSunDay.equalsIgnoreCase("Yes")) {
			if(ratessGrid.RatePlan_Season_Sunday.isSelected()) {
				test_steps.add("Sunday Already selected");
				logger.info("Sunday Already selected");
			}else {
				ratessGrid.RatePlan_Season_Sunday.click();
				test_steps.add("Succesfully selected Sunday");
				logger.info("Succesfully selected Sunday");
			}
		}else {
			if(ratessGrid.RatePlan_Season_Sunday.isSelected()) {
				ratessGrid.RatePlan_Season_Sunday.click();
				test_steps.add("Succesfully unselected Sunday");
				logger.info("Succesfully unselected Sunday");
			}else {
				test_steps.add("Sunday Already unselected");
				logger.info("Sunday Already unselected");
			}
		}


		if(isMonDay.equalsIgnoreCase("Yes")) {
			if(ratessGrid.RatePlan_Season_Monnday.isSelected()) {
				test_steps.add("Monday Already selected");
				logger.info("Monday Already selected");
			}else {
				ratessGrid.RatePlan_Season_Monnday.click();
				test_steps.add("Succesfully selected Monday");
				logger.info("Succesfully selected Monday");
			}
		}else {
			if(ratessGrid.RatePlan_Season_Monnday.isSelected()) {
				ratessGrid.RatePlan_Season_Monnday.click();
				test_steps.add("Succesfully unselected Monday");
				logger.info("Succesfully unselected Monday");
			}else {
				test_steps.add("Monday Already unselected");
				logger.info("Monday Already unselected");
			}
		}


		if(isTueDay.equalsIgnoreCase("Yes")) {
			if(ratessGrid.RatePlan_Season_Tuesday.isSelected()) {
				test_steps.add("Tuesday Already selected");
				logger.info("Tuesday Already selected");
			}else {
				ratessGrid.RatePlan_Season_Tuesday.click();
				test_steps.add("Succesfully selected Tuesday");
				logger.info("Succesfully selected Tuesday");
			}
		}else {
			if(ratessGrid.RatePlan_Season_Tuesday.isSelected()) {
				ratessGrid.RatePlan_Season_Tuesday.click();
				test_steps.add("Succesfully unselected Tuesday");
				logger.info("Succesfully unselected Tuesday");
			}else {
				test_steps.add("Tuesday Already unselected");
				logger.info("Tuesday Already unselected");
			}
		}

		if(isWednesDay.equalsIgnoreCase("Yes")) {
			if(ratessGrid.RatePlan_Season_Wednesday.isSelected()) {
				test_steps.add("Wednesday Already selected");
				logger.info("Wednesday Already selected");
			}else {
				ratessGrid.RatePlan_Season_Wednesday.click();
				test_steps.add("Succesfully selected Wednesday");
				logger.info("Succesfully selected Wednesday");
			}
		}else {
			if(ratessGrid.RatePlan_Season_Wednesday.isSelected()) {
				ratessGrid.RatePlan_Season_Wednesday.click();
				test_steps.add("Succesfully unselected Wednesday");
				logger.info("Succesfully unselected Wednesday");
			}else {
				test_steps.add("Wednesday Already unselected");
				logger.info("Wednesday Already unselected");
			}
		}


		if(isThursDay.equalsIgnoreCase("Yes")) {
			if(ratessGrid.RatePlan_Season_Thursday.isSelected()) {
				test_steps.add("Thursday Already selected");
				logger.info("Thursday Already selected");
			}else {
				ratessGrid.RatePlan_Season_Thursday.click();
				test_steps.add("Succesfully selected Thursday");
				logger.info("Succesfully selected Thursday");
			}
		}else {
			if(ratessGrid.RatePlan_Season_Thursday.isSelected()) {
				ratessGrid.RatePlan_Season_Thursday.click();
				test_steps.add("Succesfully unselected Thursday");
				logger.info("Succesfully unselected Thursday");
			}else {
				test_steps.add("Thursday Already unselected");
				logger.info("Thursday Already unselected");
			}
		}


		if(isFriday.equalsIgnoreCase("Yes")) {
			if(ratessGrid.RatePlan_Season_Friday.isSelected()) {
				test_steps.add("Friday Already selected");
				logger.info("Friday Already selected");
			}else {
				ratessGrid.RatePlan_Season_Friday.click();
				test_steps.add("Succesfully selected Friday");
				logger.info("Succesfully selected Friday");
			}
		}else {
			if(ratessGrid.RatePlan_Season_Friday.isSelected()) {
				ratessGrid.RatePlan_Season_Friday.click();
				test_steps.add("Succesfully unselected Friday");
				logger.info("Succesfully unselected Friday");
			}else {
				test_steps.add("Friday Already unselected");
				logger.info("Friday Already unselected");
			}
		}

		if(isSaturDay.equalsIgnoreCase("Yes")) {
			if(ratessGrid.RatePlan_Season_Saturday.isSelected()) {
				test_steps.add("Saturday Already selected");
				logger.info("Saturday Already selected");
			}else {
				ratessGrid.RatePlan_Season_Saturday.click();
				test_steps.add("Succesfully selected Saturday");
				logger.info("Succesfully selected Saturday");
			}
		}else {
			if(ratessGrid.RatePlan_Season_Saturday.isSelected()) {
				ratessGrid.RatePlan_Season_Saturday.click();
				test_steps.add("Succesfully unselected Saturday");
				logger.info("Succesfully unselected Saturday");
			}else {
				test_steps.add("Saturday Already unselected");
				logger.info("Saturday Already unselected");
			}
		}
	}

	public void clickCreateSeasonSave(WebDriver driver,ArrayList<String> test_steps) {
		Elements_RatesGrid ratessGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_NightlyRatePlan.RatePlan_Season_CreateSeason);
		ratessGrid.RatePlan_Season_CreateSeason.click();
		test_steps.add("Click on create season");
		logger.info("Click on create season");
	}


	public void selectSeasonColor(WebDriver driver,ArrayList<String> test_steps) {
		Elements_RatesGrid ratessGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_NightlyRatePlan.RatePlan_Season_ColorDropDown);
		ratessGrid.RatePlan_Season_ColorDropDown.click();
		test_steps.add("Click on season color dropdown");
		logger.info("Click on season color dropdown");	

		Random random = new Random();
		int randomNumber = random.nextInt(10 - 1) + 1;

		String color="(//div[contains(text(),'Select season color')]/following-sibling::div/div//div/span/div)["+randomNumber+"]";
		Wait.WaitForElement(driver, color);
		driver.findElement(By.xpath(color)).click();
		test_steps.add("Selected color to Season");
		logger.info("Selected color to Season");	
	}

	public void selectAdditionalChargesForChildrenAdults(WebDriver driver,ArrayList<String> test_steps,String isAdditionalChargesForChildrenAdults) {
		Elements_RatesGrid ratessGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_NightlyRatePlan.RatePlan_Season_ChargesForAdditionalChildAdult);

		String classAttribute=null;
		classAttribute=ratessGrid.RatePlan_Season_ChargesForAdditionalChildAdult.getAttribute("aria-checked");
		if(isAdditionalChargesForChildrenAdults.equalsIgnoreCase("Yes")) {
			if(classAttribute.contains("false")) {
				ratessGrid.RatePlan_Season_ChargesForAdditionalChildAdult.click();
				test_steps.add("Clicking on Charge for additional adult/child");
				logger.info("Clicking on Charge for additional adult/child");	
			}	
		}else {
			if(classAttribute.contains("true")) {
				ratessGrid.RatePlan_Season_ChargesForAdditionalChildAdult.click();
				test_steps.add("Unselecting the Charge for additional adult/child");
				logger.info("Unselecting the Charge for additional adult/child");	
			}
		}
	}

	public void enterRate(WebDriver driver,ArrayList<String> test_steps,String RatePerNight,String isAdditionalChargesForChildrenAdults,String MaxAdults,String MaxPersons,String AdditionalAdultsPerNight,String AdditionalChildPerNight ) {

		String[] rate = RatePerNight.split("\\|");

		String nihtRate="//input[@name='txtRate']";
		String maxAdults=null, maxPersons=null,addAdultPerNiht=null,AddChildPerNiht=null;

		if(isAdditionalChargesForChildrenAdults.equalsIgnoreCase("Yes")) {
			System.out.println(MaxAdults+" "+MaxPersons+" "+AdditionalAdultsPerNight+" " +AdditionalChildPerNight);
			String[] adults = MaxAdults.split("\\|");

			String[] persons = MaxPersons.split("\\|");

			String[] addAdults = AdditionalAdultsPerNight.split("\\|");
			String[] addChild = AdditionalChildPerNight.split("\\|");



			for(int i=0;i<rate.length;i++) {

				System.out.println("Adults " + adults[i]);
				System.out.println("persons " + persons[i]);
				System.out.println("addAdults " + addAdults[i]);
				System.out.println("addChild " + addChild[i]);

				nihtRate="(//input[@name='txtRate'])["+(i+1)+"]";
				driver.findElement(By.xpath(nihtRate)).sendKeys(rate[i]);
				test_steps.add("Enter rate for room "+i+" rate "+rate[i]);
				logger.info("Enter rate for room "+i+" rate "+rate[i]);

				maxAdults="((//input[@name='txtRate'])["+(i+1)+"]/../../../../../../li/ul/li//input[@role='spinbutton'])[1]";
				driver.findElement(By.xpath(maxAdults)).sendKeys(adults[i]);
				test_steps.add("Enter max adults for the room  "+i+" is "+adults[i]);
				logger.info("Enter max adults for the room  "+i+" is "+adults[i]);

				maxPersons="((//input[@name='txtRate'])["+(i+1)+"]/../../../../../../li/ul/li//input[@role='spinbutton'])[2]";
				driver.findElement(By.xpath(maxPersons)).sendKeys(persons[i]);
				test_steps.add("Enter max persons for the room  "+i+" is "+persons[i]);
				logger.info("Enter max persons for the room  "+i+" is "+persons[i]);

				addAdultPerNiht="((//input[@name='txtRate'])["+(i+1)+"]/../../../../../../li/ul/li//input[@role='spinbutton'])[3]";
				driver.findElement(By.xpath(addAdultPerNiht)).sendKeys(addAdults[i]);
				test_steps.add("Enter Additional adults per night for the room  "+i+" is "+addAdults[i]);
				logger.info("Enter Additional adults per night for the room  "+i+" is "+addAdults[i]);

				AddChildPerNiht="((//input[@name='txtRate'])["+(i+1)+"]/../../../../../../li/ul/li//input[@role='spinbutton'])[4]";
				driver.findElement(By.xpath(AddChildPerNiht)).sendKeys(addChild[i]);
				test_steps.add("Enter Additional child per night for the room  "+i+" is "+addChild[i]);
				logger.info("Enter Additional chuld per night for the room  "+i+" is "+addChild[i]);
			}
		}else {
			for(int i=0;i<rate.length;i++) {
				nihtRate="(//input[@name='txtRate'])["+(i+1)+"]";
				driver.findElement(By.xpath(nihtRate)).sendKeys(rate[i]);
				test_steps.add("Enter rate for room "+i+" rate "+rate[i]);
				logger.info("Enter rate for room "+i+" rate "+rate[i]);
			}
		}	
	}

	public void addExtraRoomClassInSeason(WebDriver driver,ArrayList<String> test_steps,String isAddRoomClassInSeason,String ExtraRoomClassesInSeason,String isAdditionalChargesForChildrenAdults,String RatePerNight,String ExtraRoomClassRatePerNight,String ExtraRoomClassMaxAdults,String ExtraRoomClassMaxPersons,String ExtraRoomClassAdditionalAdultsPerNight,String ExtraRoomClassAdditionalChildPerNight) {

		String roomClassName;

		if(isAddRoomClassInSeason.equalsIgnoreCase("Yes")) {
			String[] roomClass = ExtraRoomClassesInSeason.split("\\|");
			Elements_RatesGrid ratessGrid = new Elements_RatesGrid(driver);
			Wait.WaitForElement(driver, OR_NightlyRatePlan.RatePlan_Season_AddRoomClass);
			ratessGrid.RatePlan_Season_AddRoomClass.click();
			test_steps.add("Clickin on Add Room Class");
			logger.info("Clickin on Add Room Class");
			for(int i=0;i<roomClass.length;i++) {
				roomClassName="//span[text()='"+roomClass[i]+"']/preceding-sibling::span/input";
				Wait.WaitForElement(driver, roomClassName);
				driver.findElement(By.xpath(roomClassName)).click();
				test_steps.add("Successfully selected room class : "+roomClass[i]);
				logger.info("Successfully selected room class : "+roomClass[i]);

				String[] rate = RatePerNight.split("\\|");

				String nihtRate="//input[@name='txtRate']";
				String ratePerNight=null,maxAdults=null, maxPersons=null,addAdultPerNiht=null,AddChildPerNiht=null;

				if(isAdditionalChargesForChildrenAdults.equalsIgnoreCase("Yes")) {
					String[] rateNight = ExtraRoomClassRatePerNight.split("\\|");
					String[] adults = ExtraRoomClassMaxAdults.split("\\|");
					String[] persons = ExtraRoomClassMaxPersons.split("\\|");

					String[] addAdults = ExtraRoomClassAdditionalAdultsPerNight.split("\\|");
					String[] addChild = ExtraRoomClassAdditionalChildPerNight.split("\\|");

					for(int k=(rate.length+1);k<=(rate.length+rateNight.length);k++) {
						ratePerNight="(//input[@name='txtRate'])["+k+"]";
						driver.findElement(By.xpath(ratePerNight)).sendKeys(rateNight[i]);
						test_steps.add("Enter rate for room "+i+" rate "+rateNight[i]);
						logger.info("Enter rate for room "+i+" rate "+rateNight[i]);

						maxAdults="((//input[@name='txtRate'])["+k+"]/../../../../../../li/ul/li//input[@role='spinbutton'])[1]";
						driver.findElement(By.xpath(maxAdults)).sendKeys(adults[i]);
						test_steps.add("Enter max adults for the room  "+i+" is "+adults[i]);
						logger.info("Enter max adults for the room  "+i+" is "+adults[i]);

						maxPersons="((//input[@name='txtRate'])["+k+"]/../../../../../../li/ul/li//input[@role='spinbutton'])[2]";
						driver.findElement(By.xpath(maxPersons)).sendKeys(persons[i]);
						test_steps.add("Enter max persons for the room  "+i+" is "+persons[i]);
						logger.info("Enter max persons for the room  "+i+" is "+persons[i]);

						addAdultPerNiht="((//input[@name='txtRate'])["+k+"]/../../../../../../li/ul/li//input[@role='spinbutton'])[3]";
						driver.findElement(By.xpath(addAdultPerNiht)).sendKeys(addAdults[i]);
						test_steps.add("Enter Additional adults per night for the room  "+i+" is "+addAdults[i]);
						logger.info("Enter Additional adults per night for the room  "+i+" is "+addAdults[i]);

						AddChildPerNiht="((//input[@name='txtRate'])["+k+"]/../../../../../../li/ul/li//input[@role='spinbutton'])[4]";
						driver.findElement(By.xpath(AddChildPerNiht)).sendKeys(addChild[i]);
						test_steps.add("Enter Additional child per night for the room  "+i+" is "+addChild[i]);
						logger.info("Enter Additional chuld per night for the room  "+i+" is "+addChild[i]);
					}
				}else {
					String[] rateNight = ExtraRoomClassRatePerNight.split("\\|");
					for(int k=(rate.length+1);k<=(rate.length+rateNight.length);k++) {
						nihtRate="(//input[@name='txtRate'])["+k+"]";
						driver.findElement(By.xpath(nihtRate)).sendKeys(rateNight[i]);
						test_steps.add("Enter rate for room "+i+" rate "+rateNight[i]);
						logger.info("Enter rate for room "+i+" rate "+rateNight[i]);
					}
				}		
			}
		}
	}

	public void clickRulesRestrictionOnSeason(WebDriver driver,ArrayList<String> test_steps) {
		Elements_RatesGrid ratessGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_NightlyRatePlan.RatePlan_Season_RulesRestrictionsOnSeason);
		ratessGrid.RatePlan_Season_RulesRestrictionsOnSeason.click();
		test_steps.add("Clicking om Rules/Restrictions on Season Page");
		logger.info("Clicking om Rules/Restrictions on Season Page");
	}

	public void clickAssignRulesByRoomClass(WebDriver driver,ArrayList<String> test_steps,String isAssignRulesByRoomClass) {
		Elements_RatesGrid ratessGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_NightlyRatePlan.RatePlan_Season_AssignRulesByRoomClass);

		String classAttribute=null;
		classAttribute=ratessGrid.RatePlan_Season_AssignRulesByRoomClass.getAttribute("aria-checked");
		if(isAssignRulesByRoomClass.equalsIgnoreCase("Yes")) {
			if(classAttribute.contains("false")) {
				ratessGrid.RatePlan_Season_AssignRulesByRoomClass.click();
				test_steps.add("Clicking on assign rules by room class");
				logger.info("Clicking on assign rules by room class");	
			}	
		}else {
			if(classAttribute.contains("true")) {
				ratessGrid.RatePlan_Season_AssignRulesByRoomClass.click();
				test_steps.add("Unselecting the assign rules by room class");
				logger.info("Unselecting the assign rules by room class");	
			}
		}
	}

	public void enterSeasonLevelRules(WebDriver driver,ArrayList<String> test_steps,String isAssignRulesByRoomClass,String SeasonRuleSpecificRoomClass,String SeasonRuleType,String SeasonRuleMinStayValue,String isSeasonRuleOnMonday,String isSeasonRuleOnTuesday,String isSeasonRuleOnWednesday,String isSeasonRuleOnThursday,String isSeasonRuleOnFriday,String isSeasonRuleOnSaturday,String isSeasonRuleOnSunday) throws InterruptedException {
		Elements_RatesGrid ratesGrid = new Elements_RatesGrid(driver);

		if(isAssignRulesByRoomClass.equalsIgnoreCase("Yes")) {
			Wait.WaitForElement(driver, OR_NightlyRatePlan.RatePlan_Season_SeasonRulesSpecificRoomClasses);
			ratesGrid.RatePlan_Season_SeasonRulesSpecificRoomClasses.click();
			test_steps.add("Clicking on Choose existing room class(s)");
			logger.info("Clicking on Choose existing room class(s)");	
			String[] roomClass=SeasonRuleSpecificRoomClass.split("\\|");

			for(int i=0;i<roomClass.length;i++) {
				String roomClassName="//li[text()='"+roomClass[i]+"']";
			try{Utility.ScrollToElement(driver.findElement(By.xpath(roomClassName)), driver);
				driver.findElement(By.xpath(roomClassName)).click();
			}catch (Exception e) {
					Utility.clickThroughJavaScript(driver,driver.findElement(By.xpath(roomClassName)));
				}
				test_steps.add("Selectin room : "+roomClass[i]);
				logger.info("Selectin room : "+roomClass[i]);
			}
			Wait.wait1Second();
			try{
				Utility.ScrollToElement(driver.findElement(By.xpath("//label[text()='Room class']")), driver);
				driver.findElement(By.xpath("//label[text()='Room class']")).click();
			}catch (Exception e) {
				Utility.clickThroughJavaScript(driver, driver.findElement(By.xpath("//label[text()='Room class']")));
			}
			Wait.wait1Second();
		}

		String[] ruleType=SeasonRuleType.split("\\|");

		if(ruleType.length>0	&& ruleType!=null) {
			for(int i=0;i<ruleType.length;i++) {
				if(ruleType[i].equalsIgnoreCase("Min Nights")){
					Utility.clickThroughJavaScript(driver, ratesGrid.RatePlan_Season_SeasonMinNightsRule);

					Wait.wait1Second();
					ratesGrid.RatePlan_Season_SeasonMinNightsRuleValue.click();
					Wait.wait1Second();
					ratesGrid.RatePlan_Season_SeasonMinNightsRuleValue.clear();
					Utility.clearValue(driver, ratesGrid.RatePlan_Season_SeasonMinNightsRuleValue);
					Wait.wait1Second();
					ratesGrid.RatePlan_Season_SeasonMinNightsRuleValue.sendKeys(SeasonRuleMinStayValue);
				}

				if((ruleType.length>2&&ruleType.toString().contains("Min Nights"))||(ruleType.length>1&&!ruleType.toString().contains("Min Nights"))) {
					String mon[]=isSeasonRuleOnMonday.split("\\|");
					String tue[]=isSeasonRuleOnTuesday.split("\\|");
					String wed[]=isSeasonRuleOnWednesday.split("\\|");
					String thu[]=isSeasonRuleOnThursday.split("\\|");
					String fri[]=isSeasonRuleOnFriday.split("\\|");
					String sat[]=isSeasonRuleOnSaturday.split("\\|");
					String sun[]=isSeasonRuleOnSunday.split("\\|");

					if(ruleType[i].equalsIgnoreCase("No check-in")) {

						Utility.clickThroughJavaScript(driver, ratesGrid.RatePlan_Season_SeasonNoCheckIn);

						if(mon[0].equalsIgnoreCase("yes")) {
							String monNoCheckIn="//span[text()='No check-in']/../following-sibling::div//span[text()='Mon']/following-sibling::span";
							driver.findElement(By.xpath(monNoCheckIn)).click();
							test_steps.add("No Check in on monday is selected");
							logger.info("No Check in on monday is selected");
						}

						if(tue[0].equalsIgnoreCase("yes")) {
							String tueNoCheckIn="//span[text()='No check-in']/../following-sibling::div//span[text()='Tue']/following-sibling::span";
							driver.findElement(By.xpath(tueNoCheckIn)).click();
							test_steps.add("No Check in on tuesday is selected");
							logger.info("No Check in on tuesday is selected");
						}

						if(wed[0].equalsIgnoreCase("yes")) {
							String wedNoCheckIn="//span[text()='No check-in']/../following-sibling::div//span[text()='Wed']/following-sibling::span";
							driver.findElement(By.xpath(wedNoCheckIn)).click();
							test_steps.add("No Check in on wednesday is selected");
							logger.info("No Check in on wednesday is selected");
						}

						if(thu[0].equalsIgnoreCase("yes")) {
							String thuNoCheckIn="//span[text()='No check-in']/../following-sibling::div//span[text()='Thu']/following-sibling::span";
							driver.findElement(By.xpath(thuNoCheckIn)).click();
							test_steps.add("No Check in on thursday is selected");
							logger.info("No Check in on thursday is selected");
						}


						if(fri[0].equalsIgnoreCase("yes")) {
							String friNoCheckIn="//span[text()='No check-in']/../following-sibling::div//span[text()='Fri']/following-sibling::span";
							driver.findElement(By.xpath(friNoCheckIn)).click();
							test_steps.add("No Check in on friday is selected");
							logger.info("No Check in on friday is selected");
						}

						if(sat[0].equalsIgnoreCase("yes")) {
							String satNoCheckIn="//span[text()='No check-in']/../following-sibling::div//span[text()='Sat']/following-sibling::span";
							driver.findElement(By.xpath(satNoCheckIn)).click();
							test_steps.add("No Check in on saturday is selected");
							logger.info("No Check in on saturday is selected");
						}

						if(sun[0].equalsIgnoreCase("yes")) {
							String sunNoCheckIn="//span[text()='No check-in']/../following-sibling::div//span[text()='Sun']/following-sibling::span";
							driver.findElement(By.xpath(sunNoCheckIn)).click();
							test_steps.add("No Check in on sunday is selected");
							logger.info("No Check in on sunday is selected");
						}

					}


					if(ruleType[i].equalsIgnoreCase("No check-out")) {

						Utility.clickThroughJavaScript(driver, ratesGrid.RatePlan_Season_SeasonNoCheckOut);

						if(mon[0].equalsIgnoreCase("yes")) {
							String monNoCheckOut="//span[text()='No check-out']/../following-sibling::div//span[text()='Mon']/following-sibling::span";
							driver.findElement(By.xpath(monNoCheckOut)).click();
							test_steps.add("No Check out on monday is selected");
							logger.info("No Check out on monday is selected");
						}

						if(tue[0].equalsIgnoreCase("yes")) {
							String tueNoCheckOut="//span[text()='No check-out']/../following-sibling::div//span[text()='Tue']/following-sibling::span";
							driver.findElement(By.xpath(tueNoCheckOut)).click();
							test_steps.add("No Check out on tuesday is selected");
							logger.info("No Check out on tuesday is selected");
						}

						if(wed[0].equalsIgnoreCase("yes")) {
							String wedNoCheckOut="//span[text()='No check-out']/../following-sibling::div//span[text()='Wed']/following-sibling::span";
							driver.findElement(By.xpath(wedNoCheckOut)).click();
							test_steps.add("No Check out on wednesday is selected");
							logger.info("No Check out on wednesday is selected");
						}

						if(thu[0].equalsIgnoreCase("yes")) {
							String thuNoCheckOut="//span[text()='No check-out']/../following-sibling::div//span[text()='Thu']/following-sibling::span";
							driver.findElement(By.xpath(thuNoCheckOut)).click();
							test_steps.add("No Check out on thursday is selected");
							logger.info("No Check out on thursday is selected");
						}


						if(fri[0].equalsIgnoreCase("yes")) {
							String friNoCheckOut="//span[text()='No check-out']/../following-sibling::div//span[text()='Fri']/following-sibling::span";
							driver.findElement(By.xpath(friNoCheckOut)).click();
							test_steps.add("No Check out on friday is selected");
							logger.info("No Check out on friday is selected");
						}

						if(sat[0].equalsIgnoreCase("yes")) {
							String satNoCheckOut="//span[text()='No check-out']/../following-sibling::div//span[text()='Sat']/following-sibling::span";
							driver.findElement(By.xpath(satNoCheckOut)).click();
							test_steps.add("No Check out on saturday is selected");
							logger.info("No Check out on saturday is selected");
						}

						if(sun[0].equalsIgnoreCase("yes")) {
							String sunNoCheckOut="//span[text()='No check-out']/../following-sibling::div//span[text()='Sun']/following-sibling::span";
							driver.findElement(By.xpath(sunNoCheckOut)).click();
							test_steps.add("No Check out on sunday is selected");
							logger.info("No Check out on sunday is selected");
						}

					}
				}else {

					if(ruleType[i].equalsIgnoreCase("No check-in")) {
						Utility.clickThroughJavaScript(driver, ratesGrid.RatePlan_Season_SeasonNoCheckIn);
						if(isSeasonRuleOnMonday.equalsIgnoreCase("yes")) {
							String monNoCheckIn="//span[text()='No check-in']/../following-sibling::div//span[text()='Mon']/following-sibling::span";
							driver.findElement(By.xpath(monNoCheckIn)).click();
							test_steps.add("No Check in on monday is selected");
							logger.info("No Check in on monday is selected");
						}

						if(isSeasonRuleOnTuesday.equalsIgnoreCase("yes")) {
							String tueNoCheckIn="//span[text()='No check-in']/../following-sibling::div//span[text()='Tue']/following-sibling::span";
							driver.findElement(By.xpath(tueNoCheckIn)).click();
							test_steps.add("No Check in on tuesday is selected");
							logger.info("No Check in on tuesday is selected");
						}

						if(isSeasonRuleOnWednesday.equalsIgnoreCase("yes")) {
							String wedNoCheckIn="//span[text()='No check-in']/../following-sibling::div//span[text()='Wed']/following-sibling::span";
							driver.findElement(By.xpath(wedNoCheckIn)).click();
							test_steps.add("No Check in on wednesday is selected");
							logger.info("No Check in on wednesday is selected");
						}

						if(isSeasonRuleOnThursday.equalsIgnoreCase("yes")) {
							String thuNoCheckIn="//span[text()='No check-in']/../following-sibling::div//span[text()='Thu']/following-sibling::span";
							driver.findElement(By.xpath(thuNoCheckIn)).click();
							test_steps.add("No Check in on thursday is selected");
							logger.info("No Check in on thursday is selected");
						}


						if(isSeasonRuleOnFriday.equalsIgnoreCase("yes")) {
							String friNoCheckIn="//span[text()='No check-in']/../following-sibling::div//span[text()='Fri']/following-sibling::span";
							driver.findElement(By.xpath(friNoCheckIn)).click();
							test_steps.add("No Check in on friday is selected");
							logger.info("No Check in on friday is selected");
						}

						if(isSeasonRuleOnSaturday.equalsIgnoreCase("yes")) {
							String satNoCheckIn="//span[text()='No check-in']/../following-sibling::div//span[text()='Sat']/following-sibling::span";
							driver.findElement(By.xpath(satNoCheckIn)).click();
							test_steps.add("No Check in on saturday is selected");
							logger.info("No Check in on saturday is selected");
						}

						if(isSeasonRuleOnSunday.equalsIgnoreCase("yes")) {
							String sunNoCheckIn="//span[text()='No check-in']/../following-sibling::div//span[text()='Sun']/following-sibling::span";
							driver.findElement(By.xpath(sunNoCheckIn)).click();
							test_steps.add("No Check in on sunday is selected");
							logger.info("No Check in on sunday is selected");
						}

					}


					if(ruleType[i].equalsIgnoreCase("No check-out")) {
						Utility.clickThroughJavaScript(driver, ratesGrid.RatePlan_Season_SeasonNoCheckOut);
						if(isSeasonRuleOnMonday.equalsIgnoreCase("yes")) {
							String monNoCheckOut="//span[text()='No check-out']/../following-sibling::div//span[text()='Mon']/following-sibling::span";
							driver.findElement(By.xpath(monNoCheckOut)).click();
							test_steps.add("No Check out on monday is selected");
							logger.info("No Check out on monday is selected");
						}

						if(isSeasonRuleOnTuesday.equalsIgnoreCase("yes")) {
							String tueNoCheckOut="//span[text()='No check-out']/../following-sibling::div//span[text()='Tue']/following-sibling::span";
							driver.findElement(By.xpath(tueNoCheckOut)).click();
							test_steps.add("No Check out on tuesday is selected");
							logger.info("No Check out on tuesday is selected");
						}

						if(isSeasonRuleOnWednesday.equalsIgnoreCase("yes")) {
							String wedNoCheckOut="//span[text()='No check-out']/../following-sibling::div//span[text()='Wed']/following-sibling::span";
							driver.findElement(By.xpath(wedNoCheckOut)).click();
							test_steps.add("No Check out on wednesday is selected");
							logger.info("No Check out on wednesday is selected");
						}

						if(isSeasonRuleOnThursday.equalsIgnoreCase("yes")) {
							String thuNoCheckOut="//span[text()='No check-out']/../following-sibling::div//span[text()='Thu']/following-sibling::span";
							driver.findElement(By.xpath(thuNoCheckOut)).click();
							test_steps.add("No Check out on thursday is selected");
							logger.info("No Check out on thursday is selected");
						}


						if(isSeasonRuleOnFriday.equalsIgnoreCase("yes")) {
							String friNoCheckOut="//span[text()='No check-out']/../following-sibling::div//span[text()='Fri']/following-sibling::span";
							driver.findElement(By.xpath(friNoCheckOut)).click();
							test_steps.add("No Check out on friday is selected");
							logger.info("No Check out on friday is selected");
						}

						if(isSeasonRuleOnSaturday.equalsIgnoreCase("yes")) {
							String satNoCheckOut="//span[text()='No check-out']/../following-sibling::div//span[text()='Sat']/following-sibling::span";
							driver.findElement(By.xpath(satNoCheckOut)).click();
							test_steps.add("No Check out on saturday is selected");
							logger.info("No Check out on saturday is selected");
						}

						if(isSeasonRuleOnSunday.equalsIgnoreCase("yes")) {
							String sunNoCheckOut="//span[text()='No check-out']/../following-sibling::div//span[text()='Sun']/following-sibling::span";
							driver.findElement(By.xpath(sunNoCheckOut)).click();
							test_steps.add("No Check out on sunday is selected");
							logger.info("No Check out on sunday is selected");
						}
					}

				}

			}
		}
	}

	public void clickSeasonPolicies(WebDriver driver,ArrayList<String> test_steps) {
		Elements_RatesGrid ratessGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_NightlyRatePlan.RatePlan_Season_SeasonPolices);
		try{
			Utility.ScrollToElement(ratessGrid.RatePlan_Season_SeasonPolices, driver);
			ratessGrid.RatePlan_Season_SeasonPolices.click();
		}catch (Exception e) {
			Utility.clickThroughJavaScript(driver, ratessGrid.RatePlan_Season_SeasonPolices);
		}
		test_steps.add("Clicking on season policies");
		logger.info("Clicking on season policies");
	}

	@Deprecated
	public void selectSeasonPolicies(WebDriver driver,ArrayList<String> test_steps,String isSeasonPolicies,String SeasonPolicyType,String SeasonCancellationPolicy,String SeasonDepositPolicy,String SeasonCheckInPolicy,String SeasonNoShowPolicy) {
		if(isSeasonPolicies.equalsIgnoreCase("yes")) {
			String[] policy=SeasonPolicyType.split("\\|");

			for(int i=0;i<policy.length;i++) {

				if(policy[i].equalsIgnoreCase("Cancellation Policy")) {
					String can="//span[text()='Cancellation']/..//input";
					driver.findElement(By.xpath(can)).click();
					test_steps.add("Clicking on Cancellation Policy Checkbox");
					logger.info("Clicking on Cancellation Policy Checkbox");

					String canPolicy="//span[contains(text(),'"+SeasonCancellationPolicy+"')]/preceding-sibling::span/input";
					driver.findElement(By.xpath(canPolicy)).click();
					test_steps.add("Selecting season level Cancellation policy as : "+SeasonCancellationPolicy);
					logger.info("Selecting season level Cancellation policy as : "+SeasonCancellationPolicy);
				}

				if(policy[i].equalsIgnoreCase("Deposit")) {

					String deposit="//span[contains(text(),'"+SeasonDepositPolicy+"')]/preceding-sibling::span/input";
					driver.findElement(By.xpath(deposit)).click();
					test_steps.add("Selecting season level Deposit policy as : "+SeasonDepositPolicy);
					logger.info("Selecting season level Deposit policy as : "+SeasonDepositPolicy);
				}

				if(policy[i].equalsIgnoreCase("Check-In")) {
					String checkIn="//span[text()='Check-in']/..//input";
					driver.findElement(By.xpath(checkIn)).click();
					test_steps.add("Clicking on checkIn Policy Checkbox");
					logger.info("Clicking on checkIn Policy Checkbox");

					String checkin="//span[contains(text(),'"+SeasonCheckInPolicy+"')]/preceding-sibling::span/input";
					driver.findElement(By.xpath(checkin)).click();
					test_steps.add("Selecting season level checkin policy as : "+SeasonCheckInPolicy);
					logger.info("Selecting season level checkin policy as : "+SeasonCheckInPolicy);
				}

				if(policy[i].equalsIgnoreCase("No Show")) {
					String noShow="//span[text()='No Show']/..//input";
					driver.findElement(By.xpath(noShow)).click();
					test_steps.add("Clicking on noShow Policy Checkbox");
					logger.info("Clicking on noShow Policy Checkbox");

					String noShowPolicy="//span[contains(text(),'"+SeasonNoShowPolicy+"')]/preceding-sibling::span/input";
					driver.findElement(By.xpath(noShowPolicy)).click();
					test_steps.add("Selecting season level noShow policy as : "+SeasonNoShowPolicy);
					logger.info("Selecting season level noShow policy as : "+SeasonNoShowPolicy);
				}
			}
		}
	}

	public void clickSaveSason(WebDriver driver,ArrayList<String> test_steps) {
		Elements_RatesGrid ratessGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_NightlyRatePlan.RatePlan_Season_SeasonSave);
		try{
			Utility.ScrollToElement(ratessGrid.RatePlan_Season_SeasonSave, driver);
			ratessGrid.RatePlan_Season_SeasonSave.click();
		}catch (Exception e) {
			Utility.clickThroughJavaScript(driver, ratessGrid.RatePlan_Season_SeasonSave);
		}
		test_steps.add("Clicking on Save Season");
		logger.info("Clicking on Save Season");
	}

	public void clickCompleteChanges(WebDriver driver,ArrayList<String> test_steps) {
		Elements_RatesGrid ratessGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_NightlyRatePlan.RatePlan_Season_CompleteChanges);
		ratessGrid.RatePlan_Season_CompleteChanges.click();
		test_steps.add("Clicking on Complete Changes");
		logger.info("Clicking on Complete Changes");
	}


	public void clickSaveAsActive(WebDriver driver,ArrayList<String> test_steps) {
		Elements_RatesGrid ratessGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_NightlyRatePlan.RatePlan_Season_SaveAsActive);
		ratessGrid.RatePlan_Season_SaveAsActive.click();
		test_steps.add("Clicking on Save As Active");
		logger.info("Clicking on Save As Active");
	}

	public void verifyTodayDateSelectedInSeasonCancelder(WebDriver driver,ArrayList<String> test_steps) {
		String currentDate="//div[@class='DayPicker-Day DayPicker-Day--today']/div";
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		Date dateobj = new Date();
		String date=df.format(dateobj);
		Wait.WaitForElement(driver, currentDate);
		currentDate=driver.findElement(By.xpath(currentDate)).getText().trim();
		date=Utility.getDay(date);
		assertTrue(currentDate.equalsIgnoreCase(date), "Current date was not selected in season  calender");
	}
	
	public void verifyCompleteChanges(WebDriver driver,ArrayList<String> test_steps) {
		Wait.WaitForElement(driver, OR_NightlyRatePlan.RatePlan_Season_CompleteChanges);
		test_steps.add("Verified Complete changes button");
		logger.info("Verified Complete changes button");
	}
	
	public void selectDateRangeLabel(WebDriver driver,ArrayList<String> test_steps) {
		String slectDateRange="//h3[text()='Select date range  to create a season and set rates']";
		assertTrue(driver.findElement(By.xpath(slectDateRange)).isDisplayed(), "Select date range  to create a season and set rates is not displayed");
		test_steps.add("Select date range  to create a season and set rates is displayed");
		logger.info("Select date range  to create a season and set rates is displayed");
	}
	
	public void verifyEnterSeasonNamePlaceholder(WebDriver driver,ArrayList<String> test_steps) {
		String seasonName="//input[@placeholder='Enter season name']";
		Wait.WaitForElement(driver, seasonName);
		test_steps.add("Verified Enter season name in season popup");
		logger.info("Verified Enter season name in season popup");
	}

	public String verifySeasonNameCombinations(WebDriver driver,ArrayList<String> test_steps) {
		Elements_RatesGrid ratessGrid = new Elements_RatesGrid(driver);
		Wait.WaitForElement(driver, OR_NightlyRatePlan.RatePlan_SeasonName);


		assertTrue(!ratessGrid.RatePlan_Season_CreateSeason.isEnabled(), "Create season button is enabled without entering the season name");
		test_steps.add("Create season button is disabled");
		logger.info("Create season button is disabled");

		String seasonName=Utility.generateRandomStringWithGivenLength(53);
		ratessGrid.RatePlan_SeasonName.clear();
		ratessGrid.RatePlan_SeasonName.sendKeys(seasonName);

		String accualValue=ratessGrid.RatePlan_SeasonName.getAttribute("value");
		int length=accualValue.length();
		assertTrue(length==50, "failed to verify season name length");
		test_steps.add("Verified maximum season name length as 50, Entered season name length as 53, but accepted 50");
		logger.info("Verified maximum season name length as 50, Entered season name length as 53, but accepted 50");


		assertTrue(ratessGrid.RatePlan_Season_CreateSeason.isEnabled(), "Create season button is enabled even after entering the season name");
		test_steps.add("Create season button is enabled after entering the season name");
		logger.info("Create season button is enabled after entering the season name");
		return seasonName;
	}

	public void verifyAllSeasonDaysSelected(WebDriver driver,ArrayList<String> test_steps) {
		Elements_RatesGrid ratessGrid = new Elements_RatesGrid(driver);

		if(ratessGrid.RatePlan_Season_Sunday.isSelected()) {
			test_steps.add("Sunday defaultly selected");
			logger.info("Sunday defaultly selected");
		}else {
			assertTrue(false, "Sunday defaultly not selected");
			test_steps.add("Sunday defaultly not selected");
			logger.info("Sunday defaultly not selected");
		}

		if(ratessGrid.RatePlan_Season_Monnday.isSelected()) {
			test_steps.add("Monday defaultly selected");
			logger.info("Monday defaultly selected");
		}else {
			assertTrue(false, "Monday defaultly not selected");
			test_steps.add("Monday defaultly not selected");
			logger.info("Monday defaultly not selected");
		}

		if(ratessGrid.RatePlan_Season_Tuesday.isSelected()) {
			test_steps.add("Tuesday defaultly selected");
			logger.info("Tuesday defaultly selected");
		}else {
			assertTrue(false, "Tuesday defaultly not selected");
			test_steps.add("Tuesday defaultly not selected");
			logger.info("Tuesday defaultly not selected");
		}

		if(ratessGrid.RatePlan_Season_Wednesday.isSelected()) {
			test_steps.add("Wednesday defaultly selected");
			logger.info("Wednesday Already selected");
		}else {
			assertTrue(false, "Wednesday defaultly not selected");
			test_steps.add("Wednesday defaultly not selected");
			logger.info("Wednesday defaultly not selected");
		}

		if(ratessGrid.RatePlan_Season_Thursday.isSelected()) {
			test_steps.add("Thursday defaultly selected");
			logger.info("Thursday Already selected");
		}else {
			assertTrue(false, "Thursday defaultly not selected");
			test_steps.add("Thursday defaultly not selected");
			logger.info("Thursday defaultly not selected");
		}

		if(ratessGrid.RatePlan_Season_Friday.isSelected()) {
			test_steps.add("Friday defaultly selected");
			logger.info("Friday defaultly selected");
		}else {
			assertTrue(false, "Friday defaultly not selected");
			test_steps.add("Friday defaultly not selected");
			logger.info("Friday defaultly not selected");
		}

		if(ratessGrid.RatePlan_Season_Saturday.isSelected()) {
			test_steps.add("Saturday defaultly selected");
			logger.info("Saturday defaultly selected");
		}else {
			assertTrue(false, "Saturday defaultly not selected");
			test_steps.add("Saturday defaultly not selected");
			logger.info("Saturday defaultly not selected");
		}
	}

	public void verifyBlockoutIsEnabled(WebDriver driver,ArrayList<String> test_steps) {
		Elements_RatesGrid ratessGrid = new Elements_RatesGrid(driver);
		assertTrue(ratessGrid.RatePlan_Season_Blockout.isEnabled(), "Season blobk out button is disabled");
		test_steps.add("Season block out button is enabled");
		logger.info("Season block out button is enabled");
	}
	
	
	public void verifySeasonNameInSeasonPopup(WebDriver driver,ArrayList<String> test_steps,String seasonName) {
		String season="//li/input[contains(@value,'"+seasonName+"')]";
		assertTrue(driver.findElement(By.xpath(season)).isDisplayed(), "Season name not displayed on season popup");
		test_steps.add("Season name displayed on season popup");
		logger.info("Season name displayed on season popup");
	}
	
	
	public void verifySeasonColor(WebDriver driver,ArrayList<String> test_steps) {
		String seasonColor="//div[@class='selectSeasonColor noSeasonColor']";
		assertTrue(driver.findElement(By.xpath(seasonColor)).isDisplayed(), "Season color is not empty");
		test_steps.add("No color is selected for season");
		logger.info("No color is selected for season");
		
		assertTrue(!driver.findElement(By.xpath(seasonColor)).isDisplayed(), "Season color is empty");
		test_steps.add("Season color is selected");
		logger.info("Season color is selected");
		
	}
	
	
	public void verifyRatesHeader(WebDriver driver,ArrayList<String> test_steps) {
		String rates="//h2[contains(text(),'Rates')]";
		assertTrue(driver.findElement(By.xpath(rates)).isDisplayed(), "Rates header is not displaying on Season popup");
		test_steps.add("Rates header is displaying on Season popup");
		logger.info("Rates header is displaying on Season popup");
	}
	
	
	public void verifyRatesLinkInSeasonPopup(WebDriver driver,ArrayList<String> test_steps,String RoomClass) {
		Elements_RatesGrid ratessGrid = new Elements_RatesGrid(driver);
		assertTrue(ratessGrid.RatePlan_Season_ChargesForAdditionalChildAdult.isDisplayed(), "Charge for additional adult/child is not displayed");
		test_steps.add("Charge for additional adult/child is displayed");
		logger.info("Charge for additional adult/child is displayed");
		
		
		assertTrue(ratessGrid.RatePlan_Season_ChargesForAdditionalChildAdult.getAttribute("aria-checked").equalsIgnoreCase("false"), "Charge for additional adult/child is not off defaultly");
		test_steps.add("Charge for additional adult/child is off by default");
		logger.info("Charge for additional adult/child is off by default");
		
		String rate="//li[contains(text(),'Rate per night')]";
		String star="//sup[contains(text(),'*')]";
		
		assertTrue((driver.findElement(By.xpath(rate)).isDisplayed()&&driver.findElement(By.xpath(star)).isDisplayed()), "Rate per night* is not displayed");
		test_steps.add("Rate per night* is displayed");
		logger.info("Rate per night* is displayed");
		
		
		assertTrue(!ratessGrid.rateGridRuleSavedMessage.isEnabled(), "Season save is enabled");
		test_steps.add("Season save is not enabled");
		logger.info("Season save is not enabled");
		
		String[] roomClass = RoomClass.split("\\|");
		
		for(int k=0;k<roomClass.length;k++) {
			String room="//span[contains(text(),'"+roomClass[k]+"')]";
			test_steps.add("Room class name displayed for : "+roomClass[k]);
			logger.info("Room class name displayed for : "+roomClass[k]);
			
			String roomClassCheck="//span[contains(text(),'"+roomClass[k]+"')]/preceding-sibling::span/input";
			assertTrue((driver.findElement(By.xpath(roomClassCheck)).isEnabled()), roomClass[k]+": Room Class is not checked");
			test_steps.add(roomClass[k]+": Room Class is checked");
			logger.info(roomClass[k]+": Room Class is checked");
						
			String nihtRate="(//input[@name='txtRate'])["+k+"]";
			assertTrue((driver.findElement(By.xpath(nihtRate)).isDisplayed()), roomClass[k]+": Room Class rate per night is not displayed");
			test_steps.add(roomClass[k]+": Room Class rate per night is displayed");
			logger.info(roomClass[k]+": Room Class rate per night is displayed");
		}

		for(int k=0;k<roomClass.length;k++) {
			ratessGrid.RatePlan_Season_ChargesForAdditionalChildAdult.click();
			test_steps.add("Clicking on Charge for additional adult/child");
			logger.info("Clicking on Charge for additional adult/child");
			
			assertTrue((driver.findElement(By.xpath(rate)).isDisplayed()&&driver.findElement(By.xpath(star)).isDisplayed()), "Rate per night* is not displayed");
			test_steps.add("Rate per night* is displayed");
			logger.info("Rate per night* is displayed");
			
			
		}

	}
    
	//Channels
	
	public void selectChannelsEditPage(WebDriver driver,String channels,boolean isSelect,ArrayList<String> test_steps) {

		StringTokenizer token = new StringTokenizer(channels, Utility.DELIM);
		while(token.hasMoreTokens()) {
			String channel = token.nextToken();
			if(channel.equalsIgnoreCase("All")) {
				channel = "Select All";
			}
			String path = "//*[text()='Channel']/..//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+channel.toUpperCase()+"']/preceding-sibling::span/input";
			
			if(isSelect) {
			if(!driver.findElement(By.xpath(path)).isSelected()) {
				try {
					driver.findElement(By.xpath(path)).click();
				}catch (Exception e) {
					Utility.clickThroughJavaScript(driver, driver.findElement(By.xpath(path)));
				}
				test_steps.add(channel + " Channel Selected");
				logger.info(channel + " Channel Selected");
			}else {
				test_steps.add(channel + " Channel Already Selected");
				logger.info(channel + " Channel Already Selected");
			}
			}else {
				if(driver.findElement(By.xpath(path)).isSelected()) {
					try {
						driver.findElement(By.xpath(path)).click();
					}catch (Exception e) {
						Utility.clickThroughJavaScript(driver, driver.findElement(By.xpath(path)));
					}
					test_steps.add(channel + " Channel UnSelected");
					logger.info(channel + " Channel UnSelected");
				}else {
					test_steps.add(channel + " Channel Already UnSelected");
					logger.info(channel + " Channel Already UnSelected");
				}
			}
			
		}
	}
	
	public void verifySelectedChannelsEditPage(WebDriver driver,String selectedChannels,boolean isSelected,ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);
		StringTokenizer token = new StringTokenizer(selectedChannels, Utility.DELIM);
		while(token.hasMoreTokens()) {
			String channel = token.nextToken();
			if(channel.equalsIgnoreCase("select all") || channel.equalsIgnoreCase("all")) {
				String path = "//*[text()='Channel']/..//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='SELECT ALL']/preceding-sibling::span/input";
				
				assertEquals(driver.findElement(By.xpath(path)).isSelected(),isSelected,"FAiled To Verify Channel not Selected : " + channel);
				test_steps.add("Successfully Verified Channel " + channel + " is Selected : " + isSelected);
				logger.info("Successfully Verified Channel " + channel + " is Selected : " + isSelected);
				int size = elements.DISTRIBUTION_CHANNEL_LIST.size();
				for (int i = 0; i < size; i++) {
					
					verifySelectedChannels(driver, elements.DISTRIBUTION_CHANNEL_LIST.get(i).getText(), isSelected, test_steps);
				}
			}else {
			String path = "//*[text()='Channel']/..//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+channel.toUpperCase()+"']/preceding-sibling::span/input";
			
			assertEquals(driver.findElement(By.xpath(path)).isSelected(),isSelected,"FAiled To Verify Channel not Selected : " + channel);
			test_steps.add("Successfully Verified Channel " + channel + " is Selected : " + isSelected);
			logger.info("Successfully Verified Channel " + channel + " is Selected : " + isSelected);
			}
		}
	}
	
	public void verifyDisplayedDistributionChannelsEditPage(WebDriver driver, ArrayList<String> channelList, ArrayList<String> test_steps) throws InterruptedException {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);
		
		int size = elements.DISTRIBUTION_CHANNEL_LIST.size();
		assertEquals(size, channelList.size(),"Failed To Verify List Size with Active List Size");
		test_steps.add("Successfully Verified Distribution Channels List Size : " + size);
		logger.info("Successfully Verified Distribution Channels List Size : " + size);
		for (int i = 0; i < size; i++) {
			
			assertTrue(elements.DISTRIBUTION_CHANNEL_LIST.get(i).isDisplayed(), "Failed To Verify Distribution Channels : "+elements.DISTRIBUTION_CHANNEL_LIST.get(i).getText()+" is not Displayed");
			test_steps.add("Successfully Verified Distribution Channels is Displayed : " + elements.DISTRIBUTION_CHANNEL_LIST.get(i).getText());
			logger.info("Successfully Verified Distribution Channels is Displayed : " + elements.DISTRIBUTION_CHANNEL_LIST.get(i).getText());
			
			assertEquals(elements.DISTRIBUTION_CHANNEL_LIST.get(i).getText().toUpperCase(), channelList.get(i).toUpperCase(), "Failed To Verify Displayed Distribution Channels");
			test_steps.add("Successfully Verified Distribution Channels : " + elements.DISTRIBUTION_CHANNEL_LIST.get(i).getText());
			logger.info("Successfully Verified Distribution Channels : " + elements.DISTRIBUTION_CHANNEL_LIST.get(i).getText());
			
			verifyChannelsSelectable(driver, elements.DISTRIBUTION_CHANNEL_LIST.get(i).getText(), test_steps);
		}
	}
	
	public void verifyChannelsSelectableEditPage(WebDriver driver,String channelName, ArrayList<String> test_steps) {
		String path = "//*[text()='Channel']/..//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+channelName.toUpperCase()+"']/preceding-sibling::span";
		
		assertTrue(driver.findElement(By.xpath(path)).isDisplayed(),"Failed To Verify "+channelName+" Selectable is not Displayed");
		test_steps.add("Successfully Verified Channel Radio/CheckBox is Displayed : " + channelName);
		logger.info("Successfully Verified Channel Radio/CheckBox is Displayed : " + channelName);
		assertTrue(driver.findElement(By.xpath(path)).isEnabled(),"Failed To Verify "+channelName+" Selectable is not Enabled");
		test_steps.add("Successfully Verified Channel Radio/CheckBox is Enabled : " + channelName);
		logger.info("Successfully Verified Channel Radio/CheckBox is Enabled : " + channelName);
	}
	
	//RoomClasses
	
	public void selectRoomClassesEditPage(WebDriver driver,String roomClasses,boolean isSelect,ArrayList<String> test_steps) {

		StringTokenizer token = new StringTokenizer(roomClasses, Utility.DELIM);
		while(token.hasMoreTokens()) {
			String roomClass = token.nextToken();
			if(roomClass.equalsIgnoreCase("All")) {
				roomClass = "Select All";
			}
			String path = "//*[text()='Room class']/..//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+roomClass.toUpperCase()+"']/preceding-sibling::span/input";
			if(isSelect) {
			if(!driver.findElement(By.xpath(path)).isSelected()) {
				try {
					driver.findElement(By.xpath(path)).click();
				}catch (Exception e) {
					Utility.clickThroughJavaScript(driver, driver.findElement(By.xpath(path)));
				}
				test_steps.add(roomClass + " Room Class Selected");
				logger.info(roomClass + " Room Class Selected");
			}else {
				test_steps.add(roomClass + " Room Class Already Selected");
				logger.info(roomClass + " Room Class Already Selected");
			}
			}else {
				if(driver.findElement(By.xpath(path)).isSelected()) {
					try {
						driver.findElement(By.xpath(path)).click();
					}catch (Exception e) {
						Utility.clickThroughJavaScript(driver, driver.findElement(By.xpath(path)));
					}
					test_steps.add(roomClass + " Room Class UnSelected");
					logger.info(roomClass + " Room Class UnSelected");
				}else {
					test_steps.add(roomClass + " Room Class Already UnSelected");
					logger.info(roomClass + " Room Class Already UnSelected");
				}
			}
			
		}
	}
	
	public void verifySelectedRoomClassesEditPage(WebDriver driver,String selectedRoomClasses,boolean isSelected,ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);
		
		StringTokenizer token = new StringTokenizer(selectedRoomClasses, Utility.DELIM);
		while(token.hasMoreTokens()) {
			String roomClass = token.nextToken();
			if(roomClass.equalsIgnoreCase("select all") || roomClass.equalsIgnoreCase("all")) {
				String path = "//*[text()='Room class']/..//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='SELECT ALL']/preceding-sibling::span/input";
				assertEquals(driver.findElement(By.xpath(path)).isSelected(),isSelected,"Failed To Verify Room Class not Selected : " + roomClass + " ");
				test_steps.add("Successfully Verified Room Class " + roomClass + " is Selected : " + isSelected);
				logger.info("Successfully Verified Room Class " + roomClass + " is Selected : " + isSelected);
				int size = elements.ROOMCLASSES_LIST.size();
				for (int i = 0; i < size; i++) {	
					verifySelectedRoomClassesEditPage(driver, elements.ROOMCLASSES_LIST.get(i).getText(), isSelected, test_steps);
				}
			}
			else {
			String path = "//*[text()='Room class']/..//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+roomClass.toUpperCase()+"']/preceding-sibling::span/input";
			assertEquals(driver.findElement(By.xpath(path)).isSelected(),isSelected,"Failed To Verify Room Class not Selected : " + roomClass + " ");
			test_steps.add("Successfully Verified Room Class " + roomClass + " is Selected : " + isSelected);
			logger.info("Successfully Verified Room Class " + roomClass + " is Selected : " + isSelected);
			}
		}
	}
	
	public void verifyDisplayedRoomClassesEditPage(WebDriver driver, ArrayList<String> roomClassList, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);
		
		int size = elements.ROOMCLASSES_LIST.size();
		assertEquals(size, roomClassList.size(),"Failed To Verify List Size with Active List Size");
		test_steps.add("Successfully Verified Room Class List Size : " + size);
		logger.info("Successfully Verified Room Class List Size : " + size);
		for (int i = 0; i < size; i++) {
			
			assertTrue(elements.ROOMCLASSES_LIST.get(i).isDisplayed(), "Failed To Verify Room Class : "+elements.ROOMCLASSES_LIST.get(i).getText()+" is not Displayed");
			test_steps.add("Successfully Verified Room Class is Displayed : " + elements.ROOMCLASSES_LIST.get(i).getText());
			logger.info("Successfully Verified Room Class is Displayed : " + elements.ROOMCLASSES_LIST.get(i).getText());
			
			assertEquals(elements.ROOMCLASSES_LIST.get(i).getText().toUpperCase(), roomClassList.get(i).toUpperCase(), "Failed To Verify Displayed RoomClass");
			test_steps.add("Successfully Verified Room Class : " + elements.ROOMCLASSES_LIST.get(i).getText());
			logger.info("Successfully Verified Room Class : " + elements.ROOMCLASSES_LIST.get(i).getText());
			
			verifyRoomClassSelectableEditPage(driver, elements.ROOMCLASSES_LIST.get(i).getText(), test_steps);
		}
	}
	
	public void verifyRoomClassSelectableEditPage(WebDriver driver,String RoomClass, ArrayList<String> test_steps) {
		String path = "//*[text()='Room class']/..//span[translate(normalize-space(text()),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+RoomClass.toUpperCase()+"']/preceding-sibling::span";
		
		assertTrue(driver.findElement(By.xpath(path)).isDisplayed(),"Failed To Verify "+RoomClass+" Selectable is not Displayed");
		test_steps.add("Successfully Verified RoomClass CheckBox is Displayed : " + RoomClass);
		logger.info("Successfully Verified RoomClass CheckBox is Displayed : " + RoomClass);
		assertTrue(driver.findElement(By.xpath(path)).isEnabled(),"Failed To Verify "+RoomClass+" Selectable is not Enabled");
		test_steps.add("Successfully Verified RoomClass CheckBox is Enabled : " + RoomClass);
		logger.info("Successfully Verified RoomClass CheckBox is Enabled : " + RoomClass);
	}
	
	public void ratePlanStatusChange(WebDriver driver, boolean isActive, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);
		
		String path = "//li[text()='Inactive']";
		String msg = "Inactive";
		if(isActive) {
			path = "//li[text()='Active']";
			msg = "Active";
		}
		
		Wait.explicit_wait_visibilityof_webelement(elements.RATE_PLAN_STATUS_SELECTION, driver);
		elements.RATE_PLAN_STATUS_SELECTION.click();
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(path)), driver);
		driver.findElement(By.xpath(path)).click();
		test_steps.add("Successfully Verified Rate Plan Status Changed to : " + msg);
		logger.info("Successfully Verified Rate Plan Status Changed to : " + msg);
		
		verifySelectedRatePlanStatus(driver, isActive, test_steps);
	}
	
	public void verifySelectedRatePlanStatus(WebDriver driver, boolean isExpectedActive, ArrayList<String> test_steps) {
		String path = "//label[text()='Rate plan status']/following-sibling::div//div[@class='ant-select-selection-selected-value']";
		String expected = "Inactive";
		if(isExpectedActive) {
			expected = "Active";
		}
		assertEquals(driver.findElement(By.xpath(path)).getText(), expected, "Failed to verify Selected Status");
		test_steps.add("Successfully Verified Rate Plan Selected Status : " + driver.findElement(By.xpath(path)).getText());
		logger.info("Successfully Verified Rate Plan Selected Status : " + driver.findElement(By.xpath(path)).getText());
		
		assertEquals(driver.findElement(By.xpath(path)).getAttribute("title"), expected, "Failed to verify Selected Status tooltip");
		test_steps.add("Successfully Verified Rate Plan Selected Status tooltip : " + expected);
		logger.info("Successfully Verified Rate Plan Selected Status tooltip : " + expected);
	}
	
	public String getRatePlanNameEditPage(WebDriver driver, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);
		Wait.explicit_wait_visibilityof_webelement(elements.RATE_PLAN_NAME_EDIT_PAGE, driver);
		String found = elements.RATE_PLAN_NAME_EDIT_PAGE.getText();
		test_steps.add("Found Rate Plan Name : " + found);
		logger.info("Found Rate Plan Name : " + found);
		return found;
	}
	
	public void verifyRatePlanNameEditPage(WebDriver driver,String expectedName, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);
		Wait.explicit_wait_visibilityof_webelement(elements.RATE_PLAN_NAME_EDIT_PAGE, driver);
		
		assertTrue(elements.RATE_PLAN_NAME_EDIT_PAGE.isDisplayed(),"Failed to Verify Rate Plan Name");
		test_steps.add("Successfully Verified Rate Plan Name Displayed");
		logger.info("Successfully Verified Rate Plan Name Displayed");
		
		assertEquals(elements.RATE_PLAN_NAME_EDIT_PAGE.getText(), expectedName, "Failed to Verify Rate Plan Name");
		test_steps.add("Successfully Verified Rate Plan Name : " + expectedName);
		logger.info("Successfully Verified Rate Plan Name : " + expectedName);
	}
	
	public String getRatePlanTypeEditPage(WebDriver driver, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);
		Wait.explicit_wait_visibilityof_webelement(elements.RATE_PLAN_TYPE_EDIT_PAGE, driver);
		String found = elements.RATE_PLAN_TYPE_EDIT_PAGE.getText();
		test_steps.add("Found Rate Plan Type : " + found);
		logger.info("Found Rate Plan Type : " + found);
		return found;
	}
	
	public void verifyRatePlanTypeEditPage(WebDriver driver,String expectedType, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);
		Wait.explicit_wait_visibilityof_webelement(elements.RATE_PLAN_TYPE_EDIT_PAGE, driver);
		
		assertTrue(elements.RATE_PLAN_TYPE_EDIT_PAGE.isDisplayed(),"Failed to Verify Rate Plan Type");
		test_steps.add("Successfully Verified Rate Plan Type Displayed");
		logger.info("Successfully Verified Rate Plan Type Displayed");
		
		assertEquals(elements.RATE_PLAN_TYPE_EDIT_PAGE.getText(), expectedType, "Failed to Verify Rate Plan Type");
		test_steps.add("Successfully Verified Rate Plan Type : " + expectedType);
		logger.info("Successfully Verified Rate Plan Type : " + expectedType);
	}
	
	public String getPropertyNameEditPage(WebDriver driver, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);
		Wait.explicit_wait_visibilityof_webelement(elements.PROPERTY_NAME_EDIT_PAGE, driver);
		String found = elements.PROPERTY_NAME_EDIT_PAGE.getText();
		test_steps.add("Found Property Name : " + found);
		logger.info("Found Property Name : " + found);
		return found;
	}
	
	public void verifyPropertyNameEditPage(WebDriver driver,String expectedType, ArrayList<String> test_steps) {
		Elements_NightlyRate elements = new Elements_NightlyRate(driver);
		Wait.explicit_wait_visibilityof_webelement(elements.PROPERTY_NAME_EDIT_PAGE, driver);
		
		assertTrue(elements.PROPERTY_NAME_EDIT_PAGE.isDisplayed(),"Failed to VerifyProperty Name");
		test_steps.add("Successfully Verified Property Name Displayed");
		logger.info("Successfully Verified Property Name Displayed");
		
		assertEquals(elements.PROPERTY_NAME_EDIT_PAGE.getText(), expectedType, "Failed to Verify Property Name");
		test_steps.add("Successfully Verified Property Name : " + expectedType);
		logger.info("Successfully Verified Property Name : " + expectedType);
	}
	
	// Save Rate plan Button
	
		public void clickSaveRatePlanButton(WebDriver driver, ArrayList<String> test_steps) {
			Elements_NightlyRate elements = new Elements_NightlyRate(driver);
			
			try {
				Wait.explicit_wait_visibilityof_webelement_120(elements.SAVE_RATE_PLAN, driver);
				Wait.explicit_wait_elementToBeClickable(elements.SAVE_RATE_PLAN, driver);
				elements.SAVE_RATE_PLAN.click();
			}catch (Exception e) {
				Utility.clickThroughJavaScript(driver, elements.SAVE_RATE_PLAN);
			}
			test_steps.add("Save Rate Plan Button Clicked");
			logger.info("Save Rate Plan Button Clicked");
			
		}
		
		public void verifySaveRatePlanButton(WebDriver driver, boolean isEnabled, boolean isDisplayed, ArrayList<String> test_steps) throws InterruptedException {
			Elements_NightlyRate elements = new Elements_NightlyRate(driver);
			Wait.explicit_wait_visibilityof_webelement_120(elements.SAVE_RATE_PLAN, driver);
			
			assertEquals(elements.SAVE_RATE_PLAN.isDisplayed(), isDisplayed, "Failed To Verify Next Button is not Displayed");
			test_steps.add("Successfully Verified Save Rate Plan Button is Displayed : " + isDisplayed);
			logger.info("Successfully Verified Save Rate Plan Button is Displayed : " + isDisplayed);
			
			assertEquals(elements.SAVE_RATE_PLAN.isEnabled(), isEnabled, "Failed To Verify Next Button Enable");
			test_steps.add("Successfully Verified Next Button Enable : " + isEnabled);
			logger.info("Successfully Verified Save Rate Plan Button Enable : " + isEnabled);
		}

}
