
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

import com.innroad.inncenter.interfaces.IPolicies;
import com.innroad.inncenter.pages.NewPolicies;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Inventory;
import com.innroad.inncenter.properties.OR_Setup;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_On_All_Navigation;
import com.innroad.inncenter.webelements.WebElements_Create_Seasons;
import com.innroad.inncenter.webelements.WebElements_Policies;

public class Policies implements IPolicies {

	public static Logger policiesLogger = Logger.getLogger("Policies");

	// selects the given policy type and clicks new policy button
	public ArrayList<String> NewPolicybutton(WebDriver driver, String PolicyType, ArrayList<String> test_steps)
			throws InterruptedException {

		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Wait.WaitForElement(driver, OR.New_Policy_Btn);
		Wait.waitForElementToBeVisibile(By.xpath(OR.New_Policy_Btn), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.New_Policy_Btn), driver);
		CreatePolicy.New_Policy_Btn.click();
		policiesLogger.info("Click New Policy Button");
		test_steps.add("Click New policy button");
		try {
			Wait.waitForElementToBeVisibile(By.xpath(NewPolicies.Select_PolicyType), driver);
			new Select(CreatePolicy.Select_PolicyType).selectByVisibleText(PolicyType);
		} catch (Exception e) {
			Wait.waitForElementToBeClickable(By.xpath(OR.New_Policy_Btn), driver);
			Utility.ScrollToElement(CreatePolicy.New_Policy_Btn, driver);
			CreatePolicy.New_Policy_Btn.click();
			policiesLogger.info("Again Click New Policy Button");
			test_steps.add("Again Click New policy button");
			Wait.waitForElementToBeVisibile(By.xpath(NewPolicies.Select_PolicyType), driver);
			new Select(CreatePolicy.Select_PolicyType).selectByVisibleText(PolicyType);
		}
		policiesLogger.info("Select policy type : " + PolicyType);
		test_steps.add("Select policy type : " + PolicyType);

		return test_steps;

	}

	public void ClickNewPolicybutton(WebDriver driver) {

		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Wait.WaitForElement(driver, OR.New_Policy_Btn);
		CreatePolicy.New_Policy_Btn.click();
		policiesLogger.info("Click New Policy Button");
		Wait.explicit_wait_xpath(OR.Enter_Policy_Name, driver);

	}

	// enters given policy name
	public void Enter_Policy_Name(WebDriver driver, String PolicyName, ArrayList<String> test_steps)
			throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Wait.WaitForElement(driver, OR.Enter_Policy_Name);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Enter_Policy_Name), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Enter_Policy_Name), driver);
		// Utility.ScrollToElement(CreatePolicy.Enter_Policy_Name, driver);
		CreatePolicy.Enter_Policy_Name.clear();
		policiesLogger.info("Clear Policy Name");
		CreatePolicy.Enter_Policy_Name.sendKeys(PolicyName);
		policiesLogger.info("Enter Policy Name: " + PolicyName);
		test_steps.add("Enter Policy Name : <b> " + PolicyName + "</b>");

	}

	public void Clear_Policy_Name(WebDriver driver) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Utility.ScrollToElement(CreatePolicy.Enter_Policy_Name, driver);
		CreatePolicy.Enter_Policy_Name.clear();
	}

	public ArrayList<String> Verify_PolicyName_Validation(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		WebElement ele = CreatePolicy.PolicyName_Validation;
		Wait.explicit_wait_visibilityof_webelement(ele, driver);
		Utility.ScrollToElement(ele, driver);
		assertTrue(ele.isDisplayed(), "Failed: Policy Name Validation is not visible");
		// steps.add("Policy Name Validation : " + ele.getText());
		return steps;
	}

	public ArrayList<String> Verify_PolicyAttributes_Validation(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		WebElement ele = CreatePolicy.PolicyAttributes_Validation;
		Wait.explicit_wait_visibilityof_webelement(ele, driver);
		Utility.ScrollToElement(ele, driver);
		assertTrue(ele.isDisplayed(), "Failed: Policy Name Validation is not visible");
		// steps.add("Policy Attribute Validation : " + ele.getText());
		return steps;
	}

	public ArrayList<String> Verify_PolicyText_Validation(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		WebElement ele = CreatePolicy.PolicyText_Validation;
		Wait.explicit_wait_visibilityof_webelement(ele, driver);
		Utility.ScrollToElement(ele, driver);
		assertTrue(ele.isDisplayed(), "Failed: Policy Name Validation is not visible");
		// steps.add("Policy Text Validation : " + ele.getText());
		return steps;
	}

	public ArrayList<String> Verify_PolicyAssociateSource_Validation(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		WebElement ele = CreatePolicy.PolicyAssociateSource_Validation;
		Wait.explicit_wait_visibilityof_webelement(ele, driver);
		Utility.ScrollToElement(ele, driver);
		assertTrue(ele.isDisplayed(), "Failed: Policy Name Validation is not visible");
		// steps.add("Policy Associate Sources Validation : " + ele.getText());
		return steps;
	}

	public ArrayList<String> Verify_PolicyAssociateRatePlan_Validation(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		WebElement ele = CreatePolicy.PolicyAssociateRatePlan_Validation;
		Wait.explicit_wait_visibilityof_webelement(ele, driver);
		Utility.ScrollToElement(ele, driver);
		assertTrue(ele.isDisplayed(), "Failed: Policy Name Validation is not visible");
		// steps.add("Policy Associate RatePlan Validation : " + ele.getText());
		return steps;
	}

	public ArrayList<String> Verify_PolicyAssociateRoomClass_Validation(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		WebElement ele = CreatePolicy.PolicyAssociateRoomClass_Validation;
		Wait.explicit_wait_visibilityof_webelement(ele, driver);
		Utility.ScrollToElement(ele, driver);
		assertTrue(ele.isDisplayed(), "Failed: Policy Name Validation is not visible");
		// steps.add("Policy Associate RoomClass Validation : " +
		// ele.getText());
		return steps;
	}

	public ArrayList<String> Verify_PolicyAssociateSeasons_Validation(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		WebElement ele = CreatePolicy.PolicyAssociateSeasons_Validation;
		Wait.explicit_wait_visibilityof_webelement(ele, driver);
		Utility.ScrollToElement(ele, driver);
		assertTrue(ele.isDisplayed(), "Failed: Policy Name Validation is not visible");
		// steps.add("Associate Seasons Validation : " + ele.getText());
		return steps;
	}

	// checks whether selected policy type is selected or not.
	public void verify_Policy_Type(WebDriver driver, String PolicyType) {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		String selectedOption = new Select(CreatePolicy.Select_Policy_Type).getFirstSelectedOption().getText();
		Assert.assertEquals(PolicyType, selectedOption);
	}

	// checks whether selected policy type is selected or not.
	public void Enter_Policy_Type(WebDriver driver, String PolicyType) {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);

		driver.findElement(By.xpath(OR.SelectPolicyType)).click();
		new Select(CreatePolicy.SelectPolicyType).selectByVisibleText(PolicyType);
		String selectedOption = new Select(CreatePolicy.SelectPolicyType).getFirstSelectedOption().getText();
		Assert.assertEquals(PolicyType, selectedOption);
	}

	public void Enter_Deposit_policy_Attributes_RC_Percentage(WebDriver driver, String Number) {

		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		CreatePolicy.Enter_Deposit_Percentage_Charges.sendKeys(Number);
		policiesLogger.info("Enter Room Charges Percentage");
	}

	public void Enter_NoShow_policy_Attributes_RC_Percentage(WebDriver driver, String Number) {

		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		CreatePolicy.Enter_Noshow_Percentage_Charges.sendKeys(Number);
		policiesLogger.info("Enter Room Charges Percentage:" + Number);
	}

	public void Enter_Deposit_Policy_Attributes(WebDriver driver, String Chargestype, String Number)
			throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		CreatePolicy.Enter_Deposit_Percentage_Charges.clear();
		switch (Chargestype.toUpperCase()) {
		case "PRC":
			CreatePolicy.PolicyAttributePercentage_Checkbox.click();
			CreatePolicy.Enter_Deposit_Percentage_Charges.sendKeys(Number);
			break;

		case "TRC":
			new Select(CreatePolicy.Select_Roomcharges_Type_for_Deposit).selectByVisibleText("Total Charges");
			Wait.wait2Second();
			CreatePolicy.Enter_Deposit_Percentage_Charges.sendKeys(Number);
			break;

		case "FA":
			driver.findElement(By.xpath(OR.Select_Deposit_Fixedamount_Radiobtn)).click();
			CreatePolicy.Enter_Deposit_Fixed_Amount.sendKeys(Number);
			break;

		case "FNRC":

			driver.findElement(By.xpath(OR.Select_Deposit_Firstnightrc_Radiobtn)).click();
			CreatePolicy.Enter_Deposit_First_Nights_RC.sendKeys(Number);
			break;
		default:
			System.err.println("Error! Invalid Input Supplied for deposit policy expected PRC/TRC/FA/FNRC");

		}

	}

	public ArrayList<String> Deposit_Policy_Attributes(WebDriver driver, String Chargestype, String Value,
			ArrayList<String> test_steps) throws InterruptedException {

		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		if (Chargestype.equals("Room Charges") || Chargestype.equals("Total Charges")) {
			WebElement RadioButton = driver.findElement(By.xpath(OR.Select_Deposit_Roomcharges_Radiobtn));
			Wait.explicit_wait_visibilityof_webelement(RadioButton, driver);
			if (!RadioButton.isSelected()) {
				RadioButton.click();
				policiesLogger.info("Click Room Charges Radio Button");
				test_steps.add("Click Room Charged Radio Button");
			}
			assertTrue(RadioButton.isSelected(), "Failed : Radio Button is not Selected");
			new Select(CreatePolicy.Select_Roomcharges_Type_for_Deposit).selectByVisibleText(Chargestype);
			Wait.wait2Second();
			assertEquals(
					new Select(CreatePolicy.Select_Roomcharges_Type_for_Deposit).getFirstSelectedOption().getText(),
					Chargestype, "Failed: Charges Type not Successfully Selected");
			policiesLogger.info("Select Charges Type : " + Chargestype);
			test_steps.add("Select Charges Type : " + Chargestype);
			CreatePolicy.Enter_Deposit_Percentage_Charges.sendKeys(Value);
			assertEquals(CreatePolicy.Enter_Deposit_Percentage_Charges.getAttribute("value"), Value,
					"Failed: Night Not entered Successfully");
			policiesLogger.info("Enter Percentage : " + Value);
			test_steps.add("Enter Percentage : " + Value);
		} else if (Chargestype.equals("FNsRC") || Chargestype.equals("First Night Room Charges")
				|| Chargestype.equals("First Night")) {
			WebElement RadioButton = driver.findElement(By.xpath(OR.Select_Deposit_Firstnightrc_Radiobtn));
			Wait.explicit_wait_visibilityof_webelement(RadioButton, driver);
			if (!RadioButton.isSelected()) {
				RadioButton.click();
				policiesLogger.info("Click First Nights Room Charges Radio Button");
				test_steps.add("Click First Nights Room Charges Button");
			}
			// assertTrue(RadioButton.isSelected(), "Failed : Radio Button is
			// not Selected");
			// Updated by Naveen
			CreatePolicy.Enter_Deposit_First_Nights_RC.clear();
			CreatePolicy.Enter_Deposit_First_Nights_RC.sendKeys(Value);
			// assertEquals(CreatePolicy.Enter_Deposit_First_Nights_RC.getAttribute("value"),
			// Value,
			// "Failed: Night Not entered Successfully");
			policiesLogger.info("Enter Nights : " + Value);
			test_steps.add("Entered Number of Night room chares : " + Value);
		} else if (Chargestype.equalsIgnoreCase("Fixed Amount")) {
			Wait.waitForElementToBeClickable(By.xpath(OR.Select_Deposit_Fixedamount_Radiobtn), driver);
			if (!(driver.findElement(By.xpath(OR.Select_Deposit_Fixedamount_Radiobtn)).isSelected())) {
				driver.findElement(By.xpath(OR.Select_Deposit_Fixedamount_Radiobtn)).click();
				CreatePolicy.Enter_Deposit_Fixed_Amount.clear();
				CreatePolicy.Enter_Deposit_Fixed_Amount.sendKeys(Value);
				test_steps.add("Entering deposit policy fixed amount as <b>" + Value + "</b>");
			} else {
				CreatePolicy.Enter_Deposit_Fixed_Amount.clear();
				CreatePolicy.Enter_Deposit_Fixed_Amount.sendKeys(Value);
				test_steps.add("Entering deposit policy fixed amount as <b>" + Value + "</b>");
			}
		}
		return test_steps;

	}

	public void Enter_Checkin_Policy_Attributes(WebDriver driver, String paymenttype, String number)
			throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);

		switch (paymenttype.toLowerCase()) {
		case "capture":
			CreatePolicy.Select_Capture_Payment_for_Checkin.click();
			CreatePolicy.Enter_Checkin_Percentage_On_balance.sendKeys(number);

			break;

		case "authorize":
			CreatePolicy.Select_Authorize_Payment_for_Checkin.click();
			CreatePolicy.Enter_Checkin_Percentage_On_balance.sendKeys(number);
			break;
		default:
			System.err.println("Error! Invalid Input Supplied for checkin policy expecting capture/authorize ");

		}

	}

	public void Enter_policy_Attributes_TC_Percentage(WebDriver driver, String Number) {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		CreatePolicy.Enter_Deposit_Percentage_Charges.sendKeys(Number);

	}

	public void Enter_Policy_Desc(WebDriver driver, String PolicyText, String PolicyDesc) {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		try {
			Utility.ScrollToElement(CreatePolicy.Enter_Policy_Text, driver);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CreatePolicy.Enter_Policy_Text.clear();
		policiesLogger.info("Clear Policy Text");
		CreatePolicy.Enter_Policy_Text.sendKeys(PolicyText);
		policiesLogger.info("Enter Policy Text:" + PolicyText);
		CreatePolicy.Enter_Policy_Description.clear();
		policiesLogger.info("Clear Policy Description");
		CreatePolicy.Enter_Policy_Description.sendKeys(PolicyDesc);
		policiesLogger.info("Enter Policy Description: " + PolicyDesc);

	}

	public void Associate_Sources(WebDriver driver) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Wait.WaitForElement(driver, OR.Associate_Sources_Btn);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Associate_Sources_Btn), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Associate_Sources_Btn), driver);
		Utility.ScrollToElement_NoWait(CreatePolicy.Associate_Sources_Btn, driver);
		CreatePolicy.Associate_Sources_Btn.click();
		policiesLogger.info("Click Associate Sources Button");
		Wait.WaitForElement(driver, OR.Associate_Assign_All_Btn);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Associate_Assign_All_Btn), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Associate_Assign_All_Btn), driver);
		// Wait.explicit_wait_visibilityof_webelement_350(CreatePolicy.Assoociate_Assign_All_Btn,
		// driver);
		int Available_Options_Count = driver.findElements(By.xpath(OR.Available_Options_In_Popup)).size();
		policiesLogger.info(Available_Options_Count);
		Wait.wait1Second();
		CreatePolicy.Assoociate_Assign_All_Btn.click();
		Wait.wait1Second();
		policiesLogger.info("Click Associate All Button");
		int Added_Options_Count = driver.findElements(By.xpath(OR.Added_Options_In_Popup)).size();
		Assert.assertEquals(Added_Options_Count, Available_Options_Count,
				"All Available options are not added in Sources Picker popup after clicking Assign all");
		CreatePolicy.Done_In_Popup.click();
		// Wait.wait3Second();

		policiesLogger.info("Click Done Button");

	}

	public void Associate_Seasons(WebDriver driver) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Associate_Seasons_Btn), driver);
		Utility.ScrollToElement(CreatePolicy.Associate_Seasons_Btn, driver);
		CreatePolicy.Associate_Seasons_Btn.click();
		policiesLogger.info("Click Associate Seasons Button");
		Wait.waitForElementToBeClickable(By.xpath(OR.Associate_Assign_All_Btn), driver);
		CreatePolicy.Assoociate_Assign_All_Btn.click();
		policiesLogger.info("Click Associate Seasons All Button");
		Wait.waitForElementToBeClickable(By.xpath(OR.Done_In_Popup), driver);
		CreatePolicy.Done_In_Popup.click();
		policiesLogger.info("Click Done Button");

	}

	public void Associate_Seasons(WebDriver driver, String Season) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Wait.WaitForElement(driver, OR.Associate_Seasons_Btn);
		Wait.waitForElementToBeClickable(By.xpath(OR.Associate_Seasons_Btn), driver);
		Utility.ScrollToElement_NoWait(CreatePolicy.Associate_Seasons_Btn, driver);
		CreatePolicy.Associate_Seasons_Btn.click();
		Wait.WaitForElement(driver, OR.Associate_Assign_One_Btn);
		Wait.waitForElementToBeClickable(By.xpath(OR.Associate_Assign_One_Btn), driver);
		new Select(CreatePolicy.Select_Available_Options_In_Popup).selectByVisibleText(Season);
		CreatePolicy.Assoociate_Assign_One_Btn.click();
		Wait.waitForElementToBeClickable(By.xpath(OR.Done_In_Popup), driver);
		CreatePolicy.Done_In_Popup.click();
	}

	public void Associate_RoomClasses(WebDriver driver) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Utility.ScrollToElement(CreatePolicy.Associate_Room_Classes_Btn, driver);
		CreatePolicy.Associate_Room_Classes_Btn.click();
		policiesLogger.info("Click Associate Room Classes Button");
		Wait.explicit_wait_visibilityof_webelement_120(CreatePolicy.Assoociate_Assign_All_Btn, driver);
		int Available_Options_Count = driver.findElements(By.xpath(OR.Available_Options_In_Popup)).size();
		System.out.println("Avaliable:" + Available_Options_Count);
		Wait.wait1Second();
		CreatePolicy.Assoociate_Assign_All_Btn.click();
		policiesLogger.info("Click Associate Room Classes All Button");
		int Added_Options_Count;
		try {
			Wait.wait5Second();
			Added_Options_Count = driver.findElements(By.xpath(OR.Added_Options_In_Popup)).size();
			System.out.println("Added:" + Added_Options_Count);

			// System.out.println("first:" + Added_Options_Count + "second:" +
			// Available_Options_Count);
			Assert.assertEquals(Added_Options_Count, Available_Options_Count,
					"All Available options are not added in Roomclasses Picker popup after clicking Assign all");
		} catch (Exception e) {
			Wait.wait5Second();
			Added_Options_Count = driver.findElements(By.xpath(OR.Added_Options_In_Popup)).size();
			System.out.println("Added:" + Added_Options_Count);
			Assert.assertEquals(Added_Options_Count, Available_Options_Count,
					"All Available options are not added in Roomclasses Picker popup after clicking Assign all");
		}
		CreatePolicy.Done_In_Popup.click();
		Wait.wait3Second();
		policiesLogger.info("Click Done Button");

	}

	public void Associate_RoomClasses(WebDriver driver, String RoomClassName) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Wait.WaitForElement(driver, OR.Associate_Room_Classes_Btn);
		Wait.waitForElementToBeClickable(By.xpath(OR.Associate_Room_Classes_Btn), driver);
		Utility.ScrollToElement(CreatePolicy.Associate_Room_Classes_Btn, driver);
		CreatePolicy.Associate_Room_Classes_Btn.click();
		Wait.WaitForElement(driver, OR.Associate_Assign_One_Btn);
		Wait.waitForElementToBeClickable(By.xpath(OR.Associate_Assign_One_Btn), driver);
		new Select(CreatePolicy.Select_Available_Options_In_Popup).selectByVisibleText(RoomClassName);
		CreatePolicy.Assoociate_Assign_One_Btn.click();
		Wait.waitForElementToBeClickable(By.xpath(OR.Done_In_Popup), driver);
		CreatePolicy.Done_In_Popup.click();
	}

	public void Associate_RatePlans(WebDriver driver) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Utility.ScrollToElement(CreatePolicy.Associate_Rate_Plans_Btn, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();",
		// CreatePolicy.Associate_Rate_Plans_Btn);
		CreatePolicy.Associate_Rate_Plans_Btn.click();
		policiesLogger.info("Click Rate Plans Button");
		Wait.explicit_wait_visibilityof_webelement_350(CreatePolicy.Assoociate_Assign_All_Btn, driver);
		int Available_Options_Count = driver.findElements(By.xpath(OR.Available_Options_In_Popup)).size();
		Wait.wait1Second();
		CreatePolicy.Assoociate_Assign_All_Btn.click();
		policiesLogger.info("Click Rate Plans All Button");
		Wait.wait3Second();
		int Added_Options_Count = driver.findElements(By.xpath(OR.Added_Options_In_Popup)).size();
		Assert.assertEquals(Added_Options_Count, Available_Options_Count,
				"All Available options are not added in Roomclasses Picker popup after clicking Assign all");
		CreatePolicy.Done_In_Popup.click();
		Wait.wait3Second();
		policiesLogger.info("Click Done Button");

	}

	public void Associate_RatePlan(WebDriver driver, String RatePlan) throws InterruptedException {

		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Wait.WaitForElement(driver, OR.Associate_Rate_Plans_Btn);
		Wait.waitForElementToBeClickable(By.xpath(OR.Associate_Rate_Plans_Btn), driver);
		Utility.ScrollToElement(CreatePolicy.Associate_Rate_Plans_Btn, driver);
		CreatePolicy.Associate_Rate_Plans_Btn.click();
		String rate = "//option[text()='" + RatePlan + "']";
		Wait.WaitForElement(driver, rate);
		Wait.waitForElementToBeClickable(By.xpath(rate), driver);
		driver.findElement(By.xpath(rate)).click();
		String assign = "//button[@data-bind='click: AddNew']";
		Wait.waitForElementToBeClickable(By.xpath(assign), driver);
		driver.findElement(By.xpath(assign)).click();
		Wait.waitForElementToBeClickable(By.xpath(OR.Done_In_Popup), driver);
		CreatePolicy.Done_In_Popup.click();
	}

	public void Save_Policy_ClickThreeTimes(WebDriver driver) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();", CreatePolicy.Policy_Save);
		Wait.explicit_wait_visibilityof_webelement(CreatePolicy.Policy_Save, driver);
		Utility.ScrollToElement(CreatePolicy.Policy_Save, driver);
		CreatePolicy.Policy_Save.click();
		policiesLogger.info("Click Save once");
		try {
			if (CreatePolicy.Toaster_Title.isDisplayed()) {
				String getTotasterTitle_ReservationSucess = CreatePolicy.Toaster_Title.getText();
				Assert.assertEquals(getTotasterTitle_ReservationSucess, "Success");

			} else {
				System.err.println("Toaster_Message is not displaying ");
			}
		} catch (Exception e) {
			CreatePolicy.Policy_Save.click();
			policiesLogger.info("Click Save once more");
			try {
				if (CreatePolicy.Toaster_Title.isDisplayed()) {
					String getTotasterTitle_ReservationSucess = CreatePolicy.Toaster_Title.getText();
					Assert.assertEquals(getTotasterTitle_ReservationSucess, "Success");

				} else {
					System.err.println("Toaster_Message is not displaying ");
				}
			} catch (Exception f) {
				CreatePolicy.Policy_Save.click();
				policiesLogger.info("Click Save once more");
				if (CreatePolicy.Toaster_Title.isDisplayed()) {
					String getTotasterTitle_ReservationSucess = CreatePolicy.Toaster_Title.getText();
					Assert.assertEquals(getTotasterTitle_ReservationSucess, "Success");

				} else {
					System.err.println("Toaster_Message is not displaying ");
				}

			}
		}
	}

	public ArrayList<String> SavePolicy_MissingField(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Wait.explicit_wait_visibilityof_webelement(CreatePolicy.Policy_Save, driver);
		Utility.ScrollToElement(CreatePolicy.Policy_Save, driver);
		CreatePolicy.Policy_Save.click();
		test_steps.add("Click Save Policy");
		// jse.executeScript("arguments[0].click();", CreatePolicy.Policy_Save);
		Wait.explicit_wait_visibilityof_webelement(CreatePolicy.Toaster_Message, driver);
		String Error_Message = CreatePolicy.Toaster_Message.getText();
		Assert.assertEquals(Error_Message, "Please fill in all the mandatory fields");
		test_steps.add("Toaster Message Appear : " + Error_Message);
		return test_steps;
	}

	public boolean SaveButton_IsEnabled(WebDriver driver, boolean Enabled) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Wait.explicit_wait_visibilityof_webelement(CreatePolicy.Policy_Save, driver);
		Utility.ScrollToElement(CreatePolicy.Policy_Save, driver);
		boolean status = CreatePolicy.Policy_Save.isEnabled();
		if (Enabled) {
			assertTrue(status, "Failed : Save Button is not Enabled");
		} else {
			assertTrue(!status, "Failed : Save Button is Enabled");
		}
		return status;
	}

	public String Save_Policy(WebDriver driver) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Wait.WaitForElement(driver, OR.Policy_Save);
		Wait.waitForElementToBeClickable(By.xpath(OR.Policy_Save), driver);
		CreatePolicy.Policy_Save.click();
		policiesLogger.info("Click Save Button");
		String getTotasterTitle_ReservationSucess = "";
		try {
			getTotasterTitle_ReservationSucess = CreatePolicy.Toaster_Title.getText();
			Assert.assertEquals(getTotasterTitle_ReservationSucess, "Success");
			CreatePolicy.Toaster_Title.click();
			policiesLogger.info("Close Toaster Title");
		} catch (Exception e) {
			System.err.println("Toaster_Message is not displaying ");
		}
		return getTotasterTitle_ReservationSucess;
	}

	public void clickSavePolicyButton(WebDriver driver, ArrayList<String> test_steps, String policyName)
			throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Policy_Save), driver);
		Utility.ScrollToElement(CreatePolicy.Policy_Save, driver);
		CreatePolicy.Policy_Save.click();
		try {
			String getToasterMessage = CreatePolicy.Toaster_Message.getText();
			assertEquals(getToasterMessage, "Successfully Created Policy : " + policyName,
					"Failed - Verify policy creation success toaster message");
			test_steps.add("Successfully verified policy creation success toaster message as " + "<b>"
					+ getToasterMessage + "</b>");
			CreatePolicy.Toaster_Title.click();
		} catch (Exception e) {
			System.err.println("Toaster_Message is not displaying ");
			test_steps.add("Toaster_Message is not displaying ");
		}
	}

	public void Close_Policy_Tab(WebDriver driver) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		Utility.ScrollToElement(CreatePolicy.Policy_Close_Btn, driver);
		jse.executeScript("arguments[0].click();", CreatePolicy.Policy_Close_Btn);

		// CreatePolicy.Policy_Close_Btn.click();
		Wait.explicit_wait_xpath(OR.New_Policy_Btn, driver);
	}

	public ArrayList<String> closeOpenedPolicyTab(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Close_Policy_Tab(driver);
		test_steps.add("Closing already opened policy tab");
		return test_steps;
	}

	// Searches for the policy with the created policy name.
	public void Verify_Policy(WebDriver driver, String PolicyName) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Wait.explicit_wait_visibilityof_webelement_350(CreatePolicy.Policy_Name_On_Policiespage, driver);
		CreatePolicy.Policy_Name_On_Policiespage.clear();
		CreatePolicy.Policy_Name_On_Policiespage.sendKeys(PolicyName);
		CreatePolicy.Search_On_On_Policiespage.click();
		try {
			Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.PolicySearch_ModuleLoading)), 60);
			Wait.waitUntilPresenceOfElementLocated(OR.First_Element_In_Search_Result, driver);
			Wait.explicit_wait_visibilityof_webelement(CreatePolicy.First_Element_In_Search_Result, driver);
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(CreatePolicy.First_Element_In_Search_Result, driver);
		}
		Assert.assertEquals(CreatePolicy.First_Element_In_Search_Result.getText(), PolicyName);

	}

	public void Open_Existing_Policy(WebDriver driver, String PolicyName) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		WebElement Policy_Name_On_SearchPage = driver.findElement(By.xpath("//a[text()='" + PolicyName + "']"));
		Policy_Name_On_SearchPage.click();
	}

	public void Search_Open_Existing_Policy(WebDriver driver, String PolicyName) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Wait.explicit_wait_visibilityof_webelement(CreatePolicy.Policy_Name_On_Policiespage, driver);
		CreatePolicy.Policy_Name_On_Policiespage.clear();
		CreatePolicy.Policy_Name_On_Policiespage.sendKeys(PolicyName);
		Wait.explicit_wait_visibilityof_webelement(CreatePolicy.Search_On_On_Policiespage, driver);
		Utility.ScrollToElement(CreatePolicy.Search_On_On_Policiespage, driver);
		CreatePolicy.Search_On_On_Policiespage.click();
		WebElement Policy_Name_On_SearchPage = driver.findElement(By.xpath("//a[text()='" + PolicyName + "']"));
		Policy_Name_On_SearchPage.click();
	}

	public void VerifyPolicyNotExist(WebDriver driver, String PolicyName) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		// Wait.waitForElementToBeGone(driver, CreatePolicy.Toaster_Title, 60);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Wait.explicit_wait_visibilityof_webelement_350(CreatePolicy.Policy_Name_On_Policiespage, driver);
		CreatePolicy.Policy_Name_On_Policiespage.clear();
		CreatePolicy.Policy_Name_On_Policiespage.sendKeys(PolicyName);
		CreatePolicy.Search_On_On_Policiespage.click();
		wait.until(ExpectedConditions.textToBePresentInElement(CreatePolicy.Toaster_Title, "No Policies Exist"));
		Utility.app_logs.info(CreatePolicy.Toaster_Title.getText());

	}

	public void Delete_Policy(WebDriver driver, String PolicyName) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Wait.explicit_wait_visibilityof_webelement(CreatePolicy.Policy_Name_On_Policiespage, driver);
		CreatePolicy.Policy_Name_On_Policiespage.clear();
		CreatePolicy.Policy_Name_On_Policiespage.sendKeys(PolicyName);

		Wait.explicit_wait_visibilityof_webelement(CreatePolicy.Search_On_On_Policiespage, driver);
		Utility.ScrollToElement(CreatePolicy.Search_On_On_Policiespage, driver);
		CreatePolicy.Search_On_On_Policiespage.click();
		Wait.wait2Second();

		if (driver.findElements(By.xpath(OR.First_Element_In_Search_Result)).size() != 0) {
			Wait.explicit_wait_xpath(OR.First_Element_In_Search_Result, driver);
			Assert.assertEquals(CreatePolicy.First_Element_In_Search_Result.getText(), PolicyName);
			Wait.explicit_wait_visibilityof_webelement(CreatePolicy.Delete_Policy_Checkbox, driver);

			WebElement Policy = driver.findElement(
					By.xpath("//a[text()='" + PolicyName + "']/../following-sibling::td//input[@type='checkbox']"));
			jse.executeScript("arguments[0].click();", Policy);
			// Policy.click();
			// CreatePolicy.Delete_Policy_Checkbox.click();
			Wait.wait1Second();
			CreatePolicy.Delete_Policy_Btn.click();
			try {
				wait.until(ExpectedConditions.textToBePresentInElement(CreatePolicy.Toaster_Title,
						"Policies Deleted Successfully"));
				CreatePolicy.Toaster_Title.click();

			} catch (Exception e) {
				policiesLogger.error("Toast Message is not displaying");
			}
		} else {
			policiesLogger.error("Given " + PolicyName + " Policy is not found");
		}
		Wait.explicit_wait_visibilityof_webelement(CreatePolicy.Policy_Name_On_Policiespage, driver);

	}

	public void SearchPolicy(WebDriver driver) throws InterruptedException {
		WebElements_Policies Policy = new WebElements_Policies(driver);
		Policy.Search_On_On_Policiespage.click();
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement_150(Policy.Policy_TableShow, driver);
		assertTrue(Policy.Policy_TableShow.isDisplayed(), "Failed:Table not displayed");
	}

	public void ClickSearchPolicy(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		WebElements_Policies Policy = new WebElements_Policies(driver);
		Policy.Search_On_On_Policiespage.click();
		Wait.wait2Second();
		test_steps.add("Click on Search Button");
		policiesLogger.info("Click on Search Button");
	}

	public void SelectPolicyFromTable(WebDriver driver) throws Exception {

		WebElements_Policies Policy = new WebElements_Policies(driver);
		Policy.Policy_FirstActiveClass.click();
		Wait.wait3Second();
		assertTrue(Policy.Policy_PolicyDetailsPage.isDisplayed(), "Failed: Policy Details Page not displayed");

	}

	public void UpdatePolicyInfo(WebDriver driver, String Desc) throws Exception {

		WebElements_Policies Policy = new WebElements_Policies(driver);
		Random rand = new Random();
		int n = rand.nextInt(50) + 1;
		String Number = String.valueOf(n);
		Desc = Desc + Number;
		Policy.Enter_Policy_Description.clear();
		Policy.Enter_Policy_Description.sendKeys(Desc);

	}

	public ArrayList<String> NoShow_policy_Attributes(WebDriver driver, String noShowAttribute,
			String noShowAttribute_Value, ArrayList<String> test_steps) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if (noShowAttribute.equals("Room Charges") || noShowAttribute.equals("Total Charges")) {
			WebElement RadioButton = driver.findElement(By.xpath(OR.Select_Noshow_Roomcharges_Radiobtn));
			Wait.explicit_wait_xpath(OR.Select_Noshow_Roomcharges_Radiobtn, driver);
			if (!RadioButton.isSelected()) {
				RadioButton.click();
				policiesLogger.info("Click Room Charges Radio Button");
				test_steps.add("Click Room Charged Radio Button");
			}
			assertTrue(RadioButton.isSelected(), "Failed : Radio Button is not Selected");
			new Select(CreatePolicy.Select_Roomcharges_Type_for_Noshow).selectByVisibleText(noShowAttribute);
			Wait.wait2Second();
			assertEquals(new Select(CreatePolicy.Select_Roomcharges_Type_for_Noshow).getFirstSelectedOption().getText(),
					noShowAttribute, "Failed: Charges Type not Successfully Selected");
			policiesLogger.info("Select Charges Type : " + noShowAttribute);
			test_steps.add("Selected Charges Type : " + noShowAttribute);
			CreatePolicy.Enter_Noshow_Percentage_Charges.clear();
			CreatePolicy.Enter_Noshow_Percentage_Charges.sendKeys(noShowAttribute_Value);
			assertEquals(CreatePolicy.Enter_Noshow_Percentage_Charges.getAttribute("value"), noShowAttribute_Value,
					"Failed: Percentage Not entered Successfully");
			policiesLogger.info("Enter Percentage : " + noShowAttribute_Value);
			test_steps.add("Entered Percentage : " + noShowAttribute_Value);
		} else if (noShowAttribute.equals("First Night")) {
			WebElement RadioButton = driver.findElement(By.xpath(OR.Select_Noshow_Firstnightrc_Radiobtn));
			Wait.explicit_wait_xpath(OR.Select_Noshow_Firstnightrc_Radiobtn, driver);
			// Wait.explicit_wait_visibilityof_webelement(RadioButton);
			if (!RadioButton.isSelected()) {
				RadioButton.click();
				policiesLogger.info("Click First Nights Room Charges Radio Button");
				test_steps.add("Click First Nights Room Charges Button");
			}
			assertTrue(RadioButton.isSelected(), "Failed : Radio Button is not Selected");
			CreatePolicy.Enter_Noshow_First_Nights_RC.sendKeys(noShowAttribute_Value);
			assertEquals(CreatePolicy.Enter_Noshow_First_Nights_RC.getAttribute("value"), noShowAttribute_Value,
					"Failed: Night Not entered Successfully");
			policiesLogger.info("Enter Nights : " + noShowAttribute_Value);
			test_steps.add("Enter Nights : " + noShowAttribute_Value);
		} else if (noShowAttribute.equals("Fixed Amount")) {
			WebElement RadioButton = driver.findElement(By.xpath(OR.Select_Noshow_Fixedamount_Radiobtn));
			Wait.waitForElementToBeClickable(By.xpath(OR.Select_Noshow_Fixedamount_Radiobtn), driver);
			if (!RadioButton.isSelected()) {
				RadioButton.click();
				policiesLogger.info("Click Fixedamount Room Charges Radio Button");
				test_steps.add("Click Fixedamount Room Charges Button");
			}
			// assertTrue(RadioButton.isSelected(), "Failed : Radio Button is
			// not Selected");
			CreatePolicy.Enter_Noshow_Fixed_Amount.clear();
			CreatePolicy.Enter_Noshow_Fixed_Amount.sendKeys(noShowAttribute_Value);
			// assertEquals(CreatePolicy.Enter_Noshow_Fixed_Amount.getAttribute("value"),
			// noShowAttribute_Value,
			// "Failed: Fixed Amount Not entered Successfully");
			policiesLogger.info("Enter Fixed Amount : " + noShowAttribute_Value);
			test_steps.add("Entering Fixed Amount as : <b>" + noShowAttribute_Value + "</b>");
		}
		return test_steps;
	}

	public ArrayList<String> Cancellation_policy_Attributes(WebDriver driver, String cancellationAttribute1,
			String cancellationAttribute1_Value, String cancellationAttribute2, String cancellationAttribute2_Value,
			ArrayList<String> test_steps) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		if (cancellationAttribute1.equals("Room Charges") || cancellationAttribute1.equals("Total Charges")) {
			WebElement RadioButton = CreatePolicy.Select_Cancellation_Roomcharges_Radiobtn;
			Wait.explicit_wait_visibilityof_webelement(RadioButton, driver);
			if (!RadioButton.isSelected()) {
				RadioButton.click();
				policiesLogger.info("Click Room Charges Radio Button");
				test_steps.add("Click Room Charged Radio Button");
			}
			assertTrue(RadioButton.isSelected(), "Failed : Radio Button is not Selected");
			new Select(CreatePolicy.Select_Roomcharges_Type_for_Cancellation)
					.selectByVisibleText(cancellationAttribute1);
			Wait.wait2Second();
			assertEquals(new Select(CreatePolicy.Select_Roomcharges_Type_for_Cancellation).getFirstSelectedOption()
					.getText(), cancellationAttribute1, "Failed: Charges Type not Successfully Selected");
			policiesLogger.info("Select Charges Type : " + cancellationAttribute1);
			test_steps.add("Select Charges Type : " + cancellationAttribute1);
			CreatePolicy.Select_Cancellation_Roomcharges_Percentage.sendKeys(cancellationAttribute1_Value);
			assertEquals(CreatePolicy.Select_Cancellation_Roomcharges_Percentage.getAttribute("value"),
					cancellationAttribute1_Value, "Failed: PercentAge Not entered Successfully");
			policiesLogger.info("Enter Percentage : " + cancellationAttribute1_Value);
			test_steps.add("Enter Percentage : " + cancellationAttribute1_Value);
		} else if (cancellationAttribute1.equals("First Night")) {
			WebElement RadioButton = CreatePolicy.Select_Cancellation_Firstnightrc_Radiobtn;
			Wait.explicit_wait_visibilityof_webelement(RadioButton, driver);
			if (!RadioButton.isSelected()) {
				RadioButton.click();
				policiesLogger.info("Click First Nights Room Charges Radio Button");
				test_steps.add("Click First Nights Room Charges Button");
			}
			assertTrue(RadioButton.isSelected(), "Failed : Radio Button is not Selected");
			CreatePolicy.Select_Cancellation_Firstnightrc_NumberOfNights.sendKeys(cancellationAttribute1_Value);
			assertEquals(CreatePolicy.Select_Cancellation_Firstnightrc_NumberOfNights.getAttribute("value"),
					cancellationAttribute1_Value, "Failed: Night Not entered Successfully");
			policiesLogger.info("Enter Nights : " + cancellationAttribute1_Value);
			test_steps.add("Enter Nights : " + cancellationAttribute1_Value);
		} else if (cancellationAttribute1.equals("Fixed Amount")) {
			WebElement RadioButton = CreatePolicy.Select_Cancellation_Fixedamount_Radiobtn;
			Wait.explicit_wait_visibilityof_webelement(RadioButton, driver);
			if (!RadioButton.isSelected()) {
				RadioButton.click();
				policiesLogger.info("Click Fixedamount Room Charges Radio Button");
				test_steps.add("Click Fixedamount Room Charges Button");
			}
			assertTrue(RadioButton.isSelected(), "Failed : Radio Button is not Selected");
			CreatePolicy.Select_Cancellation_Fixedamount_Amount.sendKeys(cancellationAttribute1_Value);
			assertEquals(CreatePolicy.Select_Cancellation_Fixedamount_Amount.getAttribute("value"),
					cancellationAttribute1_Value, "Failed: Fixed Amount Not entered Successfully");
			policiesLogger.info("Enter Fixed Amount : " + cancellationAttribute1_Value);
			test_steps.add("Enter Fixed Amount : " + cancellationAttribute1_Value);
		}
		if (cancellationAttribute2.equals("Beyond")) {
			WebElement RadioButton = CreatePolicy.Select_Cancellation_Beyond_Radiobtn;
			Wait.explicit_wait_visibilityof_webelement(RadioButton, driver);
			if (!RadioButton.isSelected()) {
				RadioButton.click();
				policiesLogger.info("Click  Beyond Radio Button");
				test_steps.add("Click Beyond radio Button");
			}
			assertTrue(RadioButton.isSelected(), "Failed : Radio Button is not Selected");
			CreatePolicy.Select_Cancellation_Beyond_NumberOfDays.sendKeys(cancellationAttribute2_Value);
			assertEquals(CreatePolicy.Select_Cancellation_Beyond_NumberOfDays.getAttribute("value"),
					cancellationAttribute2_Value, "Failed: Days Not entered Successfully");
			policiesLogger.info("Enter Days : " + cancellationAttribute2_Value);
			test_steps.add("Enter Days : " + cancellationAttribute2_Value);
		} else if (cancellationAttribute2.equals("Within")) {
			WebElement RadioButton = CreatePolicy.Select_Cancellation_Within_Radiobtn;
			Wait.explicit_wait_visibilityof_webelement(RadioButton, driver);
			if (!RadioButton.isSelected()) {
				RadioButton.click();
				policiesLogger.info("Click Within Radio Button");
				test_steps.add("Click Within Radio Button");
			}
			assertTrue(RadioButton.isSelected(), "Failed : Radio Button is not Selected");
			CreatePolicy.Select_Cancellation_Within_NumberOfDays.sendKeys(cancellationAttribute2_Value);
			assertEquals(CreatePolicy.Select_Cancellation_Within_NumberOfDays.getAttribute("value"),
					cancellationAttribute2_Value, "Failed: Days Not entered Successfully");
			policiesLogger.info("Enter Days : " + cancellationAttribute2_Value);
			test_steps.add("Enter Days : " + cancellationAttribute2_Value);
		}
		return test_steps;

	}

	public void CloseOpenPolicy(WebDriver driver) throws InterruptedException {
		WebElements_Policies Policy = new WebElements_Policies(driver);
		Wait.explicit_wait_visibilityof_webelement(Policy.CloseOpenedTab, driver);
		Utility.ScrollToElement(Policy.CloseOpenedTab, driver);
		Policy.CloseOpenedTab.click();
		Wait.explicit_wait_xpath(OR.New_Policy_Btn, driver);
	}

	public void VerifyDeletePolicy(WebDriver driver, String PolicyName) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Wait.explicit_wait_visibilityof_webelement_350(CreatePolicy.Policy_Name_On_Policiespage, driver);
		CreatePolicy.Policy_Name_On_Policiespage.clear();
		CreatePolicy.Policy_Name_On_Policiespage.sendKeys(PolicyName);
		Utility.ScrollToElement(CreatePolicy.Search_On_On_Policiespage, driver);
		CreatePolicy.Search_On_On_Policiespage.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.textToBePresentInElement(CreatePolicy.Toaster_Title, "No Policies Exist"));
		try {
			CreatePolicy.Toaster_Title.click();
			// Wait.waitForElementToBeGone(driver, CreatePolicy.Toaster_Title,
			// 5);
		} catch (Exception e) {
			Utility.app_logs.info("Toaster is not present");
		}

	}

	public void CloseOpenPolicyTab(WebDriver driver) throws InterruptedException {
		WebElements_Policies Policy = new WebElements_Policies(driver);
		Wait.explicit_wait_visibilityof_webelement(Policy.ClosePolicyTab, driver);
		Utility.ScrollToElement(Policy.ClosePolicyTab, driver);
		Policy.ClosePolicyTab.click();
	}

	public boolean SearchPolicies(WebDriver driver) throws InterruptedException {
		WebElements_Policies policy = new WebElements_Policies(driver);

		Wait.explicit_wait_visibilityof_webelement(policy.Search_On_On_Policiespage, driver);
		policy.Search_On_On_Policiespage.click();
		Wait.waitForElementToBeGone(driver, policy.PolicySearch_ModuleLoading, 60);
		boolean isPolicyExist = false;
		try {
			String toaster_message = policy.Toaster_Title.getText();
			System.out.println("policy.Toaster_Title: " + toaster_message);
			if (toaster_message.equals("No Policies Exists")) {
				isPolicyExist = true;
			}
			System.out.println("in try of toaster");

		} catch (Exception e) {
			System.out.println("in catch");
		}
		return isPolicyExist;
	}

	public boolean AnyPolicyExist(WebDriver driver) throws InterruptedException {
		WebElements_Policies policy = new WebElements_Policies(driver);
		Wait.explicit_wait_visibilityof_webelement(policy.Search_On_On_Policiespage, driver);
		policy.Search_On_On_Policiespage.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		boolean exist;
		try {
			wait.until(ExpectedConditions.textToBePresentInElement(policy.Toaster_Title, "No Policies Exist"));
			exist = false;
		} catch (Exception e) {
			Utility.app_logs.info("Toaster not Appeaar");
			exist = true;
		}
		return exist;
	}

	public void DeleteAllPolicies(WebDriver driver) throws InterruptedException {
		WebElements_Policies policy = new WebElements_Policies(driver);

		if (policy.List_PolicyCheckbox.size() > 1) {
			Utility.ScrollToElement(policy.NumberofRecordPerPage, driver);
			new Select(policy.NumberofRecordPerPage).selectByVisibleText("100");
			for (int i = 0; i < policy.List_PolicyCheckbox.size(); i++) {
				Utility.ScrollToElement(policy.List_PolicyCheckbox.get(i), driver);
				policy.List_PolicyCheckbox.get(i).click();
			}
			Utility.ScrollToElement(policy.Delete_Policy_Btn, driver);
			policy.Delete_Policy_Btn.click();
			Wait.waitUntilPresenceOfElementLocatedByClassName(OR.Toaster_Title, driver);
			Wait.explicit_wait_visibilityof_webelement(policy.Toaster_Title, driver);
			try {
				policy.Toaster_Title.click();
			} catch (Exception e) {

			}
		} else {
			policy.PolicyCheckbox.click();
			policy.Delete_Policy_Btn.click();
			Wait.waitUntilPresenceOfElementLocatedByClassName(OR.Toaster_Title, driver);
			Wait.explicit_wait_visibilityof_webelement(policy.Toaster_Title, driver);
			System.out.println(policy.Toaster_Title.getText());
			policy.Toaster_Title.click();

		}
	}

	public void DeleteAllPolicies(WebDriver driver, boolean isPolicyExist) throws InterruptedException {
		WebElements_Policies policy = new WebElements_Policies(driver);

		try {
			if (!isPolicyExist) {
				if (policy.List_PolicyCheckbox.size() > 1) {
					System.out.println("after if");
					Utility.ScrollToElement(policy.NumberofRecordPerPage, driver);
					new Select(policy.NumberofRecordPerPage).selectByVisibleText("100");
					int size = policy.List_PolicyCheckbox.size();
					for (int i = 0; i < policy.List_PolicyCheckbox.size(); i++) {
						policy.List_PolicyCheckbox.get(i).click();

					}
					Utility.ScrollToElement(policy.Delete_Policy_Btn, driver);
					policy.Delete_Policy_Btn.click();
					Wait.explicit_wait_visibilityof_webelement(policy.Toaster_Title, driver);
					policy.Toaster_Title.click();
				} else {
					policy.PolicyCheckbox.click();
					policy.Delete_Policy_Btn.click();
					Wait.explicit_wait_visibilityof_webelement(policy.Toaster_Title, driver);
					System.out.println(policy.Toaster_Title.getText());
					policy.Toaster_Title.click();

				}
			}

		} catch (Exception e) {
			System.out.println("there are no policy exist");
		}

	}

	public void AssociateSingle_Seasons(WebDriver driver, String Season) throws InterruptedException {
		WebElements_Policies policy = new WebElements_Policies(driver);

		Wait.explicit_wait_visibilityof_webelement(policy.Associate_Seasons_Btn, driver);
		Utility.ScrollToElement(policy.Associate_Seasons_Btn, driver);
		policy.Associate_Seasons_Btn.click();

		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(policy.Selec_Attribute, driver);
		int Available_Options_Count = driver.findElements(By.xpath(OR.Available_Options_In_Popup)).size();

		new Select(policy.Selec_Attribute).selectByVisibleText(Season);
		policy.AddButton.click();
		int Added_Options_Count = driver.findElements(By.xpath(OR.Added_Options_In_Popup)).size();
		Assert.assertEquals(Added_Options_Count, 1, "Season does not add after clikc on add button");
		policy.Done_In_Popup.click();
		Wait.wait1Second();

	}

	public void AssociateSingle_RoomClass(WebDriver driver, String RoomClass) throws InterruptedException {
		WebElements_Policies policy = new WebElements_Policies(driver);

		Wait.explicit_wait_visibilityof_webelement(policy.Associate_Room_Classes_Btn, driver);
		Utility.ScrollToElement(policy.Associate_Room_Classes_Btn, driver);
		policy.Associate_Room_Classes_Btn.click();

		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(policy.Selec_Attribute, driver);
		int Available_Options_Count = driver.findElements(By.xpath(OR.Available_Options_In_Popup)).size();

		new Select(policy.Selec_Attribute).selectByVisibleText(RoomClass);
		policy.AddButton.click();
		int Added_Options_Count = driver.findElements(By.xpath(OR.Added_Options_In_Popup)).size();
		Assert.assertEquals(Added_Options_Count, 1, "RoomClass does not add after clikc on add button");
		policy.Done_In_Popup.click();
		Wait.wait1Second();

	}

	public void AssociateSingle_RatePlan(WebDriver driver, String RatePlan) throws InterruptedException {
		WebElements_Policies policy = new WebElements_Policies(driver);

		Wait.explicit_wait_visibilityof_webelement(policy.Associate_Rate_Plans_Btn, driver);
		Utility.ScrollToElement(policy.Associate_Rate_Plans_Btn, driver);
		policy.Associate_Rate_Plans_Btn.click();

		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(policy.Selec_Attribute, driver);
		int Available_Options_Count = driver.findElements(By.xpath(OR.Available_Options_In_Popup)).size();

		new Select(policy.Selec_Attribute).selectByVisibleText(RatePlan);
		policy.AddButton.click();
		int Added_Options_Count = driver.findElements(By.xpath(OR.Added_Options_In_Popup)).size();
		Assert.assertEquals(Added_Options_Count, 1, "Rate Plan does not add after clikc on add button");
		policy.Done_In_Popup.click();
		Wait.wait1Second();

	}

	public void ClickOnPolicies(WebDriver driver) throws InterruptedException {
		WebElements_Policies policy = new WebElements_Policies(driver);

		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.Menuitem_Policy_Text.click();
		Wait.waitForElementToBeGone(driver, policy.LoginModuleLoding, 300);
		Wait.explicit_wait_xpath(OR.Policy_Button, driver);
	}

	public void delete_Policies(WebDriver driver, String PolicyType) throws InterruptedException {
		String type = "//label[text()='Policy Type:']/following-sibling::div/select";
		Wait.WaitForElement(driver, type);
		new Select(driver.findElement(By.xpath(type))).selectByVisibleText(PolicyType.trim());
		String search = "//button[text()='Search']";
		Wait.WaitForElement(driver, search);
		driver.findElement(By.xpath(search)).click();
		Wait.wait10Second();
		String policytext = "//div[text()='Policies List']/../..//tbody/tr/td[5]/input";

		if (driver.findElement(By.xpath("//div[text()='Policies List']")).isDisplayed()) {
			Wait.WaitForElement(driver, policytext);
			int count = driver.findElements(By.xpath(policytext)).size();

			for (int i = 1; i <= count; i++) {
				policytext = "(//div[text()='Policies List']/../..//tbody/tr/td[5]/input)[" + i + "]";
				Wait.WaitForElement(driver, policytext);
				driver.findElement(By.xpath(policytext)).click();
				Wait.wait1Second();
			}
			String del = "//button[text()='Delete']";
			Wait.WaitForElement(driver, del);
			driver.findElement(By.xpath(del)).click();
			Wait.wait5Second();
			int cnt = 1;
			while (true) {
				if (driver.findElement(By.xpath("//div[text()='Policies List']")).isDisplayed()) {
					Wait.wait5Second();
					cnt++;
				} else if (cnt == 5) {
					break;
				} else {
					break;
				}
			}
		}
	}

	public void Select_PolicyType(WebDriver driver, String policyType, ArrayList<String> test_steps)
			throws InterruptedException {
		WebElements_Policies policy = new WebElements_Policies(driver);
		Wait.WaitForElement(driver, OR_Inventory.Select_PolicyType);
		Utility.ScrollToElement(policy.Select_PolicyTypeDropDownBox, driver);
		Wait.explicit_wait_visibilityof_webelement(policy.Select_PolicyTypeDropDownBox, driver);
		new Select(policy.Select_PolicyTypeDropDownBox).selectByVisibleText(policyType);
		test_steps.add("Select Policy Type : <b>" + policyType + "</b>");
		policiesLogger.info("Select Policy Type : " + policyType);
	}

	public ArrayList<String> createPolicy(WebDriver driver, ArrayList<String> test_steps, String policyName,
			String policyType, String attr1, String attrValue1, String attr2, String attrValue2, String source,
			String policySeason, String roomClass, String ratePlan, String policyText, String policyDesc)
			throws Exception {
		WebElements_Policies policy = new WebElements_Policies(driver);

		try {
			NewPolicybutton(driver, policyType, test_steps);
			Enter_Policy_Name(driver, policyName, test_steps);
			String policyTypeSelected = new Select(policy.Select_PolicyType).getFirstSelectedOption().getText();
			if (!(policyTypeSelected.equalsIgnoreCase(policyType))) {
				Select_PolicyType(driver, policyType, test_steps);
			} else {
				test_steps.add("Policy type is already selected as <b>" + policyType + "</b>");
			}
			if (policyType.equalsIgnoreCase("Check In")) {
				Enter_Checkin_Policy_Attributes(driver, attr1, attrValue1);
				test_steps.add("Selecting policy attribute <b>" + attr1 + "</b>");
				test_steps.add("Providing balance during Check-in  is <b>" + attrValue1 + " </b> percentage");
			} else if (policyType.equalsIgnoreCase("Cancellation")) {
				Cancellation_policy_Attributes(driver, attr1, attrValue1, attr2, attrValue2, test_steps);
			} else if (policyType.equalsIgnoreCase("Deposit")) {
				Deposit_Policy_Attributes(driver, attr1, attrValue1, test_steps);
			} else if (policyType.equalsIgnoreCase("No Show")) {
				NoShow_policy_Attributes(driver, attr1, attrValue1, test_steps);
			}
			Enter_Policy_Desc(driver, policyText, policyDesc);
			test_steps.add("Entering policy texts as <b>" + policyDesc + "</b>");
			Utility.ScrollToElement(policy.Associate_Sources_Btn, driver);
			associateSource(driver, test_steps, source);
			if (Utility.validateString(policySeason)) {
				Associate_Seasons(driver, policySeason);
				test_steps.add("Selecting <b>" + policySeason + "</b> season");
			} else {
				Associate_Seasons(driver);
				test_steps.add("Selecting all the seasons");
			}
			Associate_RatePlan(driver, ratePlan);
			test_steps.add("Selecting rate plan as <b>" + ratePlan + "</b>");
			Associate_RoomClasses(driver, roomClass);
			test_steps.add("Selecting room class as <b>" + roomClass + "</b>");
			clickSavePolicyButton(driver, test_steps, policyName);
			test_steps.add("Saving room class by clicking on save button");
		} catch (Exception e) {

		}
		return test_steps;
	}

	public ArrayList<String> deleteAllPolicies(WebDriver driver, ArrayList<String> test_steps, String policyType,
			String policyName) throws Exception {
		try {
			WebElements_Policies policy = new WebElements_Policies(driver);
			String policyTypeSelected = new Select(policy.Select_PolicyTypeDropDownBox).getFirstSelectedOption()
					.getText();
			if (!(policyTypeSelected.equalsIgnoreCase(policyType))) {
				new Select(policy.Select_PolicyTypeDropDownBox).selectByVisibleText(policyType);
			}
			ClickSearchPolicy(driver, test_steps);
			List<WebElement> policiesCheckBoxes = driver.findElements(By.xpath(
					"//a[contains(text(), '" + policyName + "')]/../..//input[@data-bind='checked: deletePolicy']"));
			if (policiesCheckBoxes.size() > 0) {
				for (WebElement checkBox : policiesCheckBoxes) {
					checkBox.click();
				}
				policy.Delete_Policy_Btn.click();
				test_steps.add("Deleted all policies named with <b>" + policyName + "</b>");
				policiesLogger.info("Deleted all policies named with " + policyName);

			} else {
				test_steps.add("No previous policies to delete for <b>" + policyName + "</b>");
				policiesLogger.info("No previous policies to delete for " + policyName);
			}
		} catch (Exception e) {

		}
		return test_steps;
	}

	public boolean SearchPolicyWithName(WebDriver driver, String PolicyName) throws InterruptedException {

		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Wait.explicit_wait_visibilityof_webelement(CreatePolicy.Policy_Name_On_Policiespage, driver);
		CreatePolicy.Policy_Name_On_Policiespage.clear();
		System.out.println("Policy name: " + PolicyName);
		CreatePolicy.Policy_Name_On_Policiespage.sendKeys(PolicyName);

		Wait.explicit_wait_visibilityof_webelement(CreatePolicy.Search_On_On_Policiespage, driver);
		Utility.ScrollToElement(CreatePolicy.Search_On_On_Policiespage, driver);
		CreatePolicy.Search_On_On_Policiespage.click();
		boolean isPolicyExist = false;
		try {
			Wait.explicit_wait_visibilityof_webelement(CreatePolicy.Toaster_Message, driver);

		} catch (Exception e) {
			isPolicyExist = true;
			new Select(CreatePolicy.selectPoliciesPerPage).selectByVisibleText("100");
			Wait.explicit_wait_visibilityof_webelement(CreatePolicy.Search_On_On_Policiespage, driver);
			Wait.explicit_wait_elementToBeClickable(CreatePolicy.Search_On_On_Policiespage, driver);

		}
		return isPolicyExist;
	}

	public ArrayList<String> DeletePolicy_1(WebDriver driver, String PolicyName) throws InterruptedException {
		WebElements_Policies policy = new WebElements_Policies(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		try {
			String policyPath = "//a[starts-with(text(),'" + PolicyName + "')]//..//following-sibling::td//input";
			List<WebElement> listOfSize = driver.findElements(By.xpath(policyPath));
			if (listOfSize.size() > 1) {
				Utility.ScrollToElement(policy.NumberofRecordPerPage, driver);
				new Select(policy.NumberofRecordPerPage).selectByVisibleText("100");

				for (int i = 0; i < listOfSize.size(); i++) {
					Utility.ScrollToElement_NoWait(listOfSize.get(i), driver);
					listOfSize.get(i).click();
				}
			} else {
				Utility.ScrollToElement_NoWait(listOfSize.get(0), driver);
				listOfSize.get(0).click();
			}
			test_steps.add("Total number of policies select for delete: " + listOfSize.size());
			policy.Delete_Policy_Btn.click();
			test_steps.add("Click on delete button");
			Wait.WaitForElementUsingClassName(driver, OR.Toaster_Title);
			Wait.explicit_wait_visibilityof_webelement(policy.Toaster_Title, driver);
			test_steps.add(policy.Toaster_Message.getText());
			policy.Toaster_Title.click();

		} catch (Exception e) {
			System.out.println("There are no policy exist");
		}
		return test_steps;
	}

	public void Enter_Checkin_Policy_Attributes_1(WebDriver driver, String paymenttype, String number, String type)
			throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);

		if (type.toLowerCase().trim().equals("cancellation")) {
			CreatePolicy.Select_Cancellation_Roomcharges_Percentage.sendKeys(number);
			CreatePolicy.Select_Cancellation_Beyond_NumberOfDays.sendKeys("0");

		} else if (type.toLowerCase().trim().equals("no show")) {
			CreatePolicy.Select_NoShow_Roomcharges_Percentage.sendKeys(number);
		} else {
			CreatePolicy.Enter_Checkin_Percentage_On_balance.sendKeys(number);
		}

		switch (paymenttype.toLowerCase().trim()) {
		case "capture":
			if (type.toLowerCase().trim().equals("capture")) {
				CreatePolicy.Select_Capture_Payment_for_Checkin.click();
			}
			break;

		case "authorize":
			if (type.toLowerCase().trim().equals("authorize")) {
				CreatePolicy.Select_Authorize_Payment_for_Checkin.click();
			}
			break;
		default:
			System.err.println("Error! Invalid Input Supplied for checkin policy expecting capture/authorize ");
		}
	}

	// Searches for the policy with the created policy name.
	public ArrayList<String> verifySearchToaster(WebDriver driver, ArrayList<String> test_steps, String PolicyName,
			boolean isExist) throws InterruptedException {

		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Wait.explicit_wait_visibilityof_webelement_350(CreatePolicy.Policy_Name_On_Policiespage, driver);
		CreatePolicy.Policy_Name_On_Policiespage.clear();
		CreatePolicy.Policy_Name_On_Policiespage.sendKeys(PolicyName);
		test_steps.add("Entering Policy Name : " + PolicyName);
		policiesLogger.info("Enter Policy Name : " + PolicyName);
		Utility.ScrollToElement(CreatePolicy.Search_On_On_Policiespage, driver);
		CreatePolicy.Search_On_On_Policiespage.click();
		test_steps.add("Clicked search button");
		policiesLogger.info("Clicked search button");
		if (!isExist) {
			try {
				if (CreatePolicy.Toaster_Title.isDisplayed()) {
					String getTotasterTitle_ReservationSucess = CreatePolicy.Toaster_Title.getText();
					Assert.assertEquals(getTotasterTitle_ReservationSucess, "No Policies Exist",
							"Failed to verify Toster");
					test_steps.add("Successfully Verified Toaster : " + getTotasterTitle_ReservationSucess);
					policiesLogger.info("Successfully Verified Toaster : " + getTotasterTitle_ReservationSucess);
				} else {
					System.err.println("Toaster_Message is not displaying ");
				}
			} catch (Exception e) {
				System.err.println("Toaster_Message is not displaying ");
			}
		} else {
			try {
				Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(OR.PolicySearch_ModuleLoading)), 60);
				Wait.waitUntilPresenceOfElementLocated(OR.First_Element_In_Search_Result, driver);
				Wait.explicit_wait_visibilityof_webelement(CreatePolicy.First_Element_In_Search_Result, driver);
			} catch (Exception e) {
				Wait.explicit_wait_visibilityof_webelement(CreatePolicy.First_Element_In_Search_Result, driver);
			}
			Assert.assertEquals(CreatePolicy.First_Element_In_Search_Result.getText(), PolicyName);
			test_steps.add("Successfully Verified Policy : " + PolicyName);
			policiesLogger.info("Successfully Verified Policy : " + PolicyName);
		}
		return test_steps;
	}

	public void associateSource(WebDriver driver, ArrayList<String> test_steps, String source)
			throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Utility.ScrollToElement(CreatePolicy.Associate_Sources_Btn, driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Associate_Sources_Btn), driver);
		CreatePolicy.Associate_Sources_Btn.click();
		policiesLogger.info("Click Associate Sources Button");
		Wait.waitForElementToBeClickable(By.xpath(OR.Associate_Assign_One_Btn), driver);
		new Select(CreatePolicy.Select_Available_Options_In_Popup).selectByVisibleText(source);
		// String path = "//select[contains(@data-bind,'options:
		// filteredItems')]/..//option[contains(text(),'"+source+"')]";
		// driver.findElement(By.xpath(path)).click();
		// new
		// Select(driver.findElement(By.xpath(path))).selectByVisibleText(arg0);
		CreatePolicy.Assoociate_Assign_One_Btn.click();
		policiesLogger.info("Click Associate One Button");
		Wait.waitForElementToBeClickable(By.xpath(OR.Done_In_Popup), driver);
		CreatePolicy.Done_In_Popup.click();
		policiesLogger.info("Click Done Button");
		test_steps.add("Selecting source as <b>" + source + "</b>");
	}

	public void createMultiplePolicies(WebDriver driver, ArrayList<String> test_steps, String numberOfPolicies[],
			ArrayList<String> policyNames, String policyType, ArrayList<String> policiesFor,
			ArrayList<String> policyAmounts, String source, String policySeason, String roomClassName, String ratePlan,
			ArrayList<String> policyTexts, ArrayList<String> policyDescs) throws Exception {

		// Navigation navigation = new Navigation();
		for (int j = 0; j < numberOfPolicies.length; j++) {
			int i = j + 1;
			try {
				test_steps
						.add("========== Creating policy " + i + " and Associating with created room class ==========");
				createPolicy(driver, test_steps, policyNames.get(j), policyType, policiesFor.get(j),
						policyAmounts.get(j), null, null, source, policySeason, roomClassName, ratePlan,
						policyTexts.get(j), policyDescs.get(j));
			} catch (Exception e) {
				// navigation.Inventory(driver, test_steps);
				// navigation.policies(driver, test_steps);
				// test_steps.add("========== Creating policy "+i+" and
				// Associating with created room class ==========");
				// createPolicy(driver, test_steps, policyNames.get(j),
				// policyType, policiesFor.get(j),
				// policyAmounts.get(j), null, null, source, policySeason,
				// roomClassName, ratePlan,
				// policyTexts.get(j), policyDescs.get(j));
			}
			closeOpenedPolicyTab(driver, test_steps);
		}

	}

	// enters given policy name
	public void Enter_Policy_Name(WebDriver driver, String PolicyName) throws InterruptedException {
		WebElements_Policies CreatePolicy = new WebElements_Policies(driver);
		Utility.ScrollToElement(CreatePolicy.Enter_Policy_Name, driver);
		CreatePolicy.Enter_Policy_Name.clear();
		CreatePolicy.Enter_Policy_Name.sendKeys(PolicyName);
	}

	public void Click_All(WebDriver driver) throws InterruptedException {
		String all = "(//a[contains(text(),'All')])[1]";
		Wait.WaitForElement(driver, all);
		Wait.wait2Second();
		driver.findElement(By.xpath(all)).click();
	}

	public void delete_Policies(WebDriver driver) throws InterruptedException {

		String pol = "//tbody[@data-bind='foreach: policiesList']/tr/td[5]/input";

		while (true) {
			int count = driver.findElements(By.xpath(pol)).size();
			if (count > 0) {
				String all = "//small[contains(text(),'Items Per Page')]/following-sibling::select";
				Wait.WaitForElement(driver, all);
				new Select(driver.findElement(By.xpath(all))).selectByValue("100");
				for (int i = 1; i <= count; i++) {
					pol = "//tbody[@data-bind='foreach: policiesList']/tr[" + i + "]/td[5]/input";
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					WebElement element = driver.findElement(By.xpath(pol));
					jse.executeScript("window.scrollTo(0, 50)");
					jse.executeScript("arguments[0].scrollIntoView();", element);
					Wait.wait2Second();
					driver.findElement(By.xpath(pol)).click();
					if (i == count) {
						driver.findElement(By.xpath("//button[contains(text(),'Delete')]")).click();
						Wait.wait10Second();
					}
				}
				count = driver.findElements(By.xpath(pol)).size();
				if (count == 0) {
					break;
				}
			} else {
				break;
			}
		}

	}
	
	public ArrayList<String> getCancelationPolicies(WebDriver driver){
		ArrayList<String> cancelationPolicie=new ArrayList<String>();
		Wait.WaitForElement(driver, OR.CANCELATION_POLICIES);
		Wait.waitForElementToBeVisibile(By.xpath(OR.CANCELATION_POLICIES), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.CANCELATION_POLICIES), driver);
		WebElements_Policies element = new WebElements_Policies(driver);
		int size=element.cancelationPolices.size();
		for(int i=0;i<size;i++) {
			
			String foundText=element.cancelationPolices.get(i).getText();
					cancelationPolicie.add(foundText);
			
		}
		return cancelationPolicie;
	}

	public ArrayList<String> getDepositPolicies(WebDriver driver){
		ArrayList<String> depositePolicie=new ArrayList<String>();
		WebElements_Policies element = new WebElements_Policies(driver);
		int size=element.depositePolices.size();
		for(int i=0;i<size;i++) {
			
			String foundText=element.depositePolices.get(i).getText();
					depositePolicie.add(foundText);
			
		}
		return depositePolicie;
	}
	public ArrayList<String> getCheckInPolicies(WebDriver driver){
		ArrayList<String> checkInPolicie=new ArrayList<String>();
		WebElements_Policies element = new WebElements_Policies(driver);
		int size=element.checkInPolicies.size();
		for(int i=0;i<size;i++) {
			
			String foundText=element.checkInPolicies.get(i).getText();
			checkInPolicie.add(foundText);
			
		}
		return checkInPolicie;
	}

	public ArrayList<String> getNoShowPolicies(WebDriver driver){
		ArrayList<String> noShowPolicie=new ArrayList<String>();
		WebElements_Policies element = new WebElements_Policies(driver);
		int size=element.noShowPolicies.size();
		for(int i=0;i<size;i++) {
			
			String foundText=element.noShowPolicies.get(i).getText();
			noShowPolicie.add(foundText);
			
		}
		return noShowPolicie;
	}


}