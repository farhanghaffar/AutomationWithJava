package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.innroad.inncenter.interfaces.IDistribution;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Distribution;
import com.innroad.inncenter.webelements.Elements_TapeChart;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Distribution {

	public static Logger distributionLogger = Logger.getLogger("Distribution");

	public void ClickDistributionLink(WebDriver driver, ExtentTest test, String Client1) throws InterruptedException {

		Elements_Distribution elements_Distribution = new Elements_Distribution(driver);
		elements_Distribution.DistributionLink.click();
		test.log(LogStatus.PASS, "Navigate to Distribution");
		distributionLogger.info("Navigate to Distribution");
		Wait.wait2Second();
		new Select(elements_Distribution.Select_Client).selectByVisibleText(Client1);
		Wait.wait2Second();
	}

	public ArrayList<String> BlackOutRoom(WebDriver driver, ExtentTest test, String BlackoutRoom, String Source,
			boolean condition, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Distribution elements_Distribution = new Elements_Distribution(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_Distribution.DistributionLink, driver);
		assertEquals(elements_Distribution.DistributionLink.getText(), "Distribution",
				"Distribution link did not find");
		elements_Distribution.DistributionLink.click();
		distributionLogger.info("Navigate to Distribution");
		// if (condition) {
		test_steps.add("Click on Distribution");
		// }
		Wait.explicit_wait_visibilityof_webelement(elements_Distribution.BlackoutsLink, driver);
		assertEquals(elements_Distribution.BlackoutsLink.getText(), "Blackouts", "Black link is not find");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Utility.ScrollToElement(elements_Distribution.BlackoutsLink, driver);
		elements_Distribution.BlackoutsLink.click();
		test_steps.add("Click on Blackouts");

		Wait.WaitForElement(driver, OR.LoginModuleLoding);
		Wait.waitForElementToBeGone(driver, elements_Distribution.LoginModuleLoding, 300);
		Wait.explicit_wait_visibilityof_webelement(elements_Distribution.SelectSource, driver);

		new Select(elements_Distribution.SelectSource).selectByVisibleText(Source);
		if (condition) {
			test_steps.add("Select source : " + Source);
		}
		String BlackoutRoomPath = "(//span[text()='" + BlackoutRoom
				+ "']/parent::td//following-sibling::td[@class='textCenter'])[2]/input";
		Utility.ElementFinderUntillNotShow(By.xpath(BlackoutRoomPath), driver);
		WebElement current_state = driver.findElement(By.xpath(BlackoutRoomPath));
		Utility.ScrollToElement(current_state, driver);
		if (condition) {
			if (!current_state.isSelected()) {
				Utility.isBlackFirst = true;
				current_state.click();
				Utility.app_logs.info("Click CheckBox");
				elements_Distribution.BlackOutRoom_Save_Button.click();

				Utility.app_logs.info("Click Save");
				Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
				test_steps.add("" + elements_Distribution.Toaster_Message.getText());

				elements_Distribution.Toaster_Message.click();
				current_state = driver.findElement(By.xpath(BlackoutRoomPath));
				Utility.ScrollToElement(current_state, driver);
				assertEquals(current_state.isSelected(), condition, "Black room checkbox does not checked");
			} else {
				Utility.isBlackFirst = false;
				String BlackoutRoom_Path = "(//span[text()='" + BlackoutRoom
						+ "']/parent::td//following-sibling::td[@class='textCenter'])[3]/input";
				Utility.ElementFinderUntillNotShow(By.xpath(BlackoutRoom_Path), driver);
				current_state = driver.findElement(By.xpath(BlackoutRoom_Path));
				Utility.ScrollToElement(current_state, driver);

				current_state.click();
				Utility.app_logs.info("Click CheckBox");
				elements_Distribution.BlackOutRoom_Save_Button.click();

				Utility.app_logs.info("Click Save");
				Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
				String ToasterMessage = elements_Distribution.Toaster_Message.getText();
				assertEquals(ToasterMessage, "Blackout Dates Saved Successfully",
						"Success message is not appear after clikc on save button");
				try {
					elements_Distribution.Toaster_Message.click();
				} catch (Exception e) {
					// TODO: handle exception
				}

				test_steps.add(ToasterMessage);
				current_state = driver.findElement(By.xpath(BlackoutRoom_Path));
				Utility.ScrollToElement(current_state, driver);
				assertEquals(current_state.isSelected(), condition, "Black room checkbox does not checked");

			}
		} else if (current_state.isSelected()) {
			String BlackoutRoom_Path = null;
			if (Utility.isBlackFirst == false) {
				BlackoutRoom_Path = "(//span[text()='" + BlackoutRoom
						+ "']/parent::td//following-sibling::td[@class='textCenter'])[3]/input";
				Utility.ElementFinderUntillNotShow(By.xpath(BlackoutRoom_Path), driver);
				current_state = driver.findElement(By.xpath(BlackoutRoom_Path));
			}
			current_state.click();
			Utility.app_logs.info("Click CheckBox");
			elements_Distribution.BlackOutRoom_Save_Button.click();

			Utility.app_logs.info("Click Save");
			Wait.WaitForElementUsingClassName(driver, OR.Toaster_Message);
			try {
				elements_Distribution.Toaster_Message.click();
			} catch (Exception e) {
				// TODO: handle exception
			}

			if (Utility.isBlackFirst == false) {
				current_state = driver.findElement(By.xpath(BlackoutRoom_Path));
			} else {
				current_state = driver.findElement(By.xpath(BlackoutRoomPath));
			}

			Utility.ScrollToElement(current_state, driver);
			assertEquals(current_state.isSelected(), condition, "Black room checkbox does not checked");
		}
		return test_steps;
	}

	public void SyndicateRoom(WebDriver driver, boolean condition) throws InterruptedException {
		Elements_Distribution elements_Distribution = new Elements_Distribution(driver);
		elements_Distribution.DistributionLink.click();
		distributionLogger.info("Navigate to Distribution");
		Wait.wait2Second();
		elements_Distribution.SyndicateLink.click();
		Wait.waitUntilPresenceOfElementLocated(OR.SyndicateRoomCheckBox, driver);
		elements_Distribution.SyndicateRoomCheckBox.click();
		elements_Distribution.SyndicateRoomSave.click();
		Wait.explicit_wait_visibilityof_webelement(elements_Distribution.Toaster_Message, driver);
		Wait.explicit_wait_visibilityof_webelement(elements_Distribution.SyndicateRoomCheckBox, driver);
		WebElement current_state = elements_Distribution.SyndicateRoomCheckBox;

		boolean isRoomChecked = false;
		if (current_state.isSelected()) {

			isRoomChecked = true;
		}
		assertEquals(isRoomChecked, condition, "Syndicate room checkbox does not checked");
	}

	public void SyndicateRoom1(WebDriver driver, boolean condition) throws InterruptedException {
		Elements_Distribution elements_Distribution = new Elements_Distribution(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.SyndicateRoomCheckBox, driver);
		elements_Distribution.SyndicateRoomCheckBox.click();
		elements_Distribution.SyndicateRoomSave.click();
		Wait.explicit_wait_visibilityof_webelement(elements_Distribution.Toaster_Message, driver);
		Wait.explicit_wait_visibilityof_webelement(elements_Distribution.SyndicateRoomCheckBox, driver);
		for (int i = 2; i < 350; i++) {
			String path = "//table/tbody/tr[" + i + "]/td[4]/input";
			System.out.println("Path:" + path);
			WebElement current_state = driver.findElement(By.xpath(path));

			boolean isRoomChecked = false;
			if (current_state.isSelected()) {

				isRoomChecked = true;

				assertEquals(isRoomChecked, condition, "Syndicate room checkbox does not checked");
				System.out.println("I:" + i);

				break;

			} else

			{
				System.out.println("already selected");
				break;
			}

		}
	}

	public void SyndicateRoom(WebDriver driver, String RoomClassName, String RoomNumber, boolean condition)
			throws InterruptedException {
		Elements_Distribution elements_Distribution = new Elements_Distribution(driver);
		String CheckBoxPath = "(//span[text()='" + RoomClassName
				+ "']//..//..//following-sibling::tr//td//span[text()='" + RoomNumber + "']//..//..//td//input)[2]";

		Utility.app_logs.info(CheckBoxPath);
		Wait.waitUntilPresenceOfElementLocated(CheckBoxPath, driver);
		WebElement SyndicateRoom = driver.findElement(By.xpath(CheckBoxPath));
		Utility.ScrollToElement(SyndicateRoom, driver);
		if (condition) {
			if (!SyndicateRoom.isSelected()) {
				SyndicateRoom.click();
				elements_Distribution.SyndicateRoomSave.click();
				// Wait.explicit_wait_visibilityof_webelement(elements_Distribution.Toaster_Message);
				WebElement room = driver.findElement(By.xpath(CheckBoxPath));

				if (elements_Distribution.Toaster_Message.isDisplayed()) {
					String getTotasterTitle_ReservationSucess = elements_Distribution.Toaster_Message.getText();
					Wait.explicit_wait_visibilityof_webelement_120(elements_Distribution.Toaster_Message, driver);
					Assert.assertEquals(getTotasterTitle_ReservationSucess, "Syndicated Rooms Saved Successfully");

				} else {
					System.err.println("Toaster_Message is not displaying ");
				}
				// assertEquals(room.isSelected(), condition, "Syndicate room
				// checkbox does not checked");
			}
		} else {
			if (SyndicateRoom.isSelected()) {
				SyndicateRoom.click();
				elements_Distribution.SyndicateRoomSave.click();
				Wait.waitForElementToBeGone(driver, elements_Distribution.Syndicate_ModuleLoading, 120);
				Wait.explicit_wait_visibilityof_webelement(elements_Distribution.Toaster_Message, driver);
				WebElement room = driver.findElement(By.xpath(CheckBoxPath));
				Utility.ScrollToElement(room, driver);
				assertEquals(room.isSelected(), condition, "Syndicate room checkbox does not checked");
			}
		}

	}

	/*
	 * public String getDefaultStatusOfChannel(WebDriver
	 * driver,ArrayList<String> test_steps, String ChannelName) throws
	 * InterruptedException { String Status=null; String
	 * waitPath="//span[.='"+ChannelName+"']"; Wait.WaitForElement(driver,
	 * waitPath);
	 * Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(
	 * waitPath)), driver); String
	 * path="//span[.='"+ChannelName+"']/..//following-sibling::td/select";
	 * 
	 * distributionLogger.info(path); WebElement element=
	 * driver.findElement(By.xpath(path)); Utility.ScrollToElement(element,
	 * driver); JavascriptExecutor executor = (JavascriptExecutor) driver;
	 * String optionValue = (String)
	 * executor.executeScript("return arguments[0].value", element);
	 * distributionLogger.info("Selected Values Is:"+optionValue); Select sel =
	 * new Select(element); WebElement option = sel.getFirstSelectedOption();
	 * Status = option.getText(); distributionLogger.info("Selected Status Is: "
	 * +Status); test_steps.add("Distribution- Channel Name: <b>"
	 * +ChannelName+"</b> <b> Status is :"+Status+"</b>\"");
	 * distributionLogger.info("Distribution Channel Name: " + ChannelName+
	 * " Status is " +Status);
	 * 
	 * return Status;
	 * 
	 * }
	 */

	public String getDefaultStatusOfChannel(WebDriver driver, ArrayList<String> test_steps, String ChannelName)
			throws InterruptedException {
		String Status = null;
		String waitPath = "//span[.='" + ChannelName + "']";
		Wait.WaitForElement(driver, waitPath);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(waitPath)), driver);
		String path = "//span[.='" + ChannelName + "']/..//following-sibling::td/select";

		distributionLogger.info(path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		String optionValue = (String) executor.executeScript("return arguments[0].value", element);
		distributionLogger.info("Selected Values Is:" + optionValue);
		Select sel = new Select(element);
		WebElement option = sel.getFirstSelectedOption();
		Status = option.getText();
		distributionLogger.info("Selected Status Is: " + Status);

		/*
		 * List<WebElement> options = sel.getOptions(); for (WebElement option :
		 * options) { System.out.println(option.getAttribute("value"));
		 * if(option.getAttribute("value").equals(optionValue)) {
		 * Status=option.getText();
		 * distributionLogger.info("Selected Status Is: " +Status); break; } }
		 */
		test_steps.add("Distribution- Channel Name: <b>" + ChannelName + "</b> <b> Status is :" + Status + "</b>\"");
		distributionLogger.info("Distribution Channel Name: " + ChannelName + " Status is " + Status);

		return Status;

	}

	public ArrayList<String> getAllActiveChannelDetails(WebDriver driver) throws InterruptedException {
		ArrayList<String> activeChannels = new ArrayList<String>();

		Wait.waitForElementToBeClickable(xpath, driver);
		Elements_Distribution elements_Distribution = new Elements_Distribution(driver);
		for (int i = 0; i < elements_Distribution.DISTRIBUTIONCHANNEL_DISTRIBUTE.size(); i++) {
			if (elements_Distribution.DISTRIBUTIONCHANNEL_DISTRIBUTE.get(i).isSelected()) {
				activeChannels.add(elements_Distribution.DISTRIBUTIONCHANNEL_DISTRIBUTE_SOURCENAME.get(i).getText());

			}
		}
		return activeChannels;
	}

	
}
