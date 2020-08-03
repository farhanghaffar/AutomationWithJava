package com.innroad.inncenter.pageobjects;

import org.testng.Assert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.innroad.inncenter.interfaces.IAdmin;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Admin;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Accounts;
import com.innroad.inncenter.webelements.Elements_Admin;
import com.innroad.inncenter.webelements.Elements_Users;

public class Admin implements IAdmin {

	public static Logger AdminLogger = Logger.getLogger("Admin");

	private boolean VerifySelectedItem(WebDriver driver, String SelectedListPath, String SelectedItem) {

		Select SelectedList = new Select(driver.findElement(By.xpath(SelectedListPath)));
		boolean found = false;
		List<WebElement> Items = SelectedList.getOptions();
		for (WebElement Item : Items) {
			String ItemName = Item.getText();
			if (ItemName.contains(SelectedItem)) {
				found = true;
				break;
			}
		}
		return found;
	}

	public void logout(WebDriver driver) {
		Elements_Users user = new Elements_Users(driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.CurrentUser), driver);
		user.CurrentUser.click();
		try {
			Wait.waitForElementToBeClickable(By.xpath(OR.Logout), driver, 10);
			user.Logout.click();
		} catch (Exception e) {
			Wait.waitForElementToBeClickable(By.xpath(OR.logoutLink), driver, 10);
			user.logoutLink.click();
		}
		Wait.waitForElementToBeVisibile(By.xpath(OR.LoginPage), driver);
	}

	public void logout(WebDriver driver, ArrayList<String> test_steps) {
		logout(driver);
		test_steps.add("Logging out to the inncenter applicaction");
	}

	public ArrayList<String> CreateNewUser(WebDriver driver, String FirstName, String LastName, String Login,
			String Email, String AssociateRole, String AssociateProperty, boolean isAllProperties,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_Users user = new Elements_Users(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Wait.explicit_wait_visibilityof_webelement(user.New_User_Btn, driver);
		user.New_User_Btn.click();
		test_steps.add("Clikc on New User button");

		Wait.explicit_wait_visibilityof_webelement(user.UserLogin, driver);
		user.UserFirstName.clear();
		user.UserFirstName.sendKeys(FirstName);
		test_steps.add("Enter first name : " + FirstName);

		user.UserLastName.clear();
		user.UserLastName.sendKeys(LastName);
		test_steps.add("Enter last name : " + LastName);

		user.UserLogin.clear();
		user.UserLogin.sendKeys(Login);
		AdminLogger.info("Login ID : " + Login);
		test_steps.add("Enter login id : " + Login);

		user.UserEmail.clear();
		user.UserEmail.sendKeys(Email);
		AdminLogger.info("Email : " + Email);
		test_steps.add("Enter email id : " + Email);

		user.User_AssociateRole_Btn.click();
		test_steps.add("Click on associate role button ");

		Wait.waitUntilPresenceOfElementLocated(OR.RolePicker_popup, driver);
		new Select(user.SelectAssociateRole).selectByVisibleText(AssociateRole);
		test_steps.add("Select associate role");

		user.AddAssociateRole.click();
		Assert.assertTrue(VerifySelectedItem(driver, OR.SelectedAssociateRole, AssociateRole),
				"Failed: Associate Role Selection");
		user.User_AssociateRole_Done.click();
		AdminLogger.info("Associate Role : " + AssociateRole);
		test_steps.add("Attached associate role : " + AssociateRole);
		Wait.wait1Second();

		Wait.WaitForElement(driver, OR.User_AssociateProperties_Btn);
		Wait.explicit_wait_visibilityof_webelement(user.User_AssociateProperties_Btn, driver);
		Utility.ScrollToElement(user.User_AssociateProperties_Btn, driver);
		user.User_AssociateProperties_Btn.click();
		test_steps.add("Click on associate property button");

		Wait.wait1Second();
		Wait.waitUntilPresenceOfElementLocated(OR.PropertyPicker_popup, driver);

		AssociateProperty = AssociateProperty.trim();
		new Select(user.SelectAssociateProperty).selectByVisibleText(AssociateProperty);
		test_steps.add("Select  associate property : " + AssociateProperty);
		user.AddAssociateProperty.click();
		Wait.wait1Second();
		user.User_AssociateProperty_Done.click();
		AdminLogger.info("Associate Property : " + AssociateProperty);
		test_steps.add("Attached associate property : " + AssociateProperty);
		Wait.wait2Second();

		return test_steps;

	}

	public void CloseTab(WebDriver driver) {
		Elements_Users user = new Elements_Users(driver);
		user.CloseNewUserTab.click();
	}

	public void SearchUser(WebDriver driver, String LastName, String FirstName, String LoginId, String Email)
			throws InterruptedException {
		Elements_Users user = new Elements_Users(driver);
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(user.Search_LastName, driver);
		user.Search_LastName.sendKeys(LastName);
		user.Search_FirstName.sendKeys(FirstName);
		user.Search_LoginId.sendKeys(LoginId);
		user.Search_Email.sendKeys(Email);
		user.User_SearchButton.click();
		Wait.explicit_wait_visibilityof_webelement(user.VerifySearch, driver);
		assertEquals(user.VerifySearch.getText(), LoginId, "Faild to search created new user");
		user.VerifySearch.click();

	}

	public void ChangeUserStatus(WebDriver driver, String Status) throws InterruptedException {
		Elements_Users user = new Elements_Users(driver);
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(user.Select_Status, driver);
		new Select(user.Select_Status).selectByVisibleText(Status);

	}

	public ArrayList<String> SetNewPassword(WebDriver driver, String NewPassword, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Users user = new Elements_Users(driver);
		Wait.WaitForElement(driver, OR.SetNewPassword_Title);
		// System.out.println("title:" + user.SetNewPassword_Title.getText());
		assertEquals(user.SetNewPassword_Title.getText(), "Setup new password", "Reset Password page didn't show");
		test_steps.add("Set Password Page Verified Title :" + user.SetNewPassword_Title.getText());
		user.SetNewPassword_NewPasswordInputField.sendKeys(NewPassword);
		test_steps.add("Entered New Password : " + NewPassword);
		System.out.println("Pass:" + NewPassword);
		user.SetNewPassword_ConfirmPasswordInputField.sendKeys(NewPassword);
		test_steps.add("Confirmed New Password : " + NewPassword);
		user.SetNewPassword_SubmitButton.click();
		test_steps.add("Submit Button is Click");

		// Verify
		Wait.WaitForElement(driver, OR.Relogin_Message_LoginPage);
		System.out.println("title:" + user.Relogin_Message_LoginPage.getText());
		WebElement Relogin = driver.findElement(By.xpath("//div[text()='Relogin with your new password.']"));
		assertEquals(Relogin.isDisplayed(), true, "Relogin page didn't show");

		return test_steps;
	}

	public void SaveButton(WebDriver driver) {
		Elements_Users user = new Elements_Users(driver);
		Wait.explicit_wait_visibilityof_webelement(user.User_Save_Btn, driver);
		user.User_Save_Btn.click();

		Wait.explicit_wait_visibilityof_webelement_350(user.Toaster_Title, driver);
		System.out.println(user.Toaster_Title.getText());
		assertEquals(user.Toaster_Title.getText(), "Success", "Failed to verify new user after save button");

	}

	public void ResetPasswordButtonClick(WebDriver driver) throws InterruptedException {
		Elements_Users user = new Elements_Users(driver);
		Wait.wait1Second();
		user.User_ResetPassword_Btn.click();
		Wait.explicit_wait_visibilityof_webelement_350(user.Toaster_Title, driver);
		System.out.println(user.Toaster_Title.getText());
		assertEquals(user.Toaster_Title.getText(), "Success", "Failed to verify new user after save button");
		user.User_Save_Btn.click();
		Wait.explicit_wait_visibilityof_webelement_350(user.Toaster_Title, driver);
		assertEquals(user.Toaster_Title.getText(), "Success", "Failed to verify new user after save button");
	}

	public boolean VerifyUser(WebDriver driver, String LastName, String FirstName, String LoginId, String Email)
			throws InterruptedException {
		Elements_Users user = new Elements_Users(driver);
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(user.Search_LastName, driver);
		user.Search_LastName.sendKeys(Keys.chord(Keys.CONTROL, "a"), LastName);
		user.Search_FirstName.sendKeys(Keys.chord(Keys.CONTROL, "a"), FirstName);
		user.Search_LoginId.sendKeys(Keys.chord(Keys.CONTROL, "a"), LoginId);
		user.Search_Email.sendKeys(Keys.chord(Keys.CONTROL, "a"), Email);

		user.User_SearchButton.click();
		boolean isUserExist = false;
		System.out.println("Search click");
		try {
			Wait.wait5Second();

			WebElement toaster = driver.findElement(By.xpath("//div[@class='toast-title']"));
			if (toaster.isDisplayed()) {
				System.out.println("User not found click");
				assertEquals(toaster.getText(), "No Users Exist", "User Exists with ID");

				isUserExist = false;
			}

		} catch (Exception e) {
			Wait.WaitForElement(driver, OR.VerifySearch);
			assertEquals(user.VerifySearch.getText(), LoginId, "Faild to search created new user");
			Utility.ScrollToElement(user.VerifySearch, driver);
			user.VerifySearch.click();
			Wait.WaitForElement(driver, OR.UserLogin);
			System.out.println("User found click");

			assertTrue(user.UserLogin.isDisplayed(), "User Page didn't display");
			isUserExist = true;

		}

		return isUserExist;

	}

	public ArrayList<String> ChangeUserStatus(WebDriver driver, String Login, String Email, String Status,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Users user = new Elements_Users(driver);
		Wait.wait1Second();
		user.UserLogin.clear();
		user.UserLogin.sendKeys(Login);
		AdminLogger.info("Login ID Changed To : " + Login);
		test_steps.add("Login ID Changed To  : " + Login);
		user.UserEmail.clear();
		user.UserEmail.sendKeys(Email);
		AdminLogger.info("Email Changed To : " + Email);
		test_steps.add("Email Changed To : " + Email);
		Wait.explicit_wait_visibilityof_webelement(user.Select_Status, driver);
		new Select(user.Select_Status).selectByVisibleText(Status);
		test_steps.add("Status Changed to : " + Status);
		String SelectedOption = new Select(user.Select_Status).getFirstSelectedOption().getText();
		assertTrue(SelectedOption.equals(Status), "Status didn't change");
		return test_steps;
	}

	public void SearchUser(WebDriver driver, String LastName, String FirstName, String LoginId, String Email,
			String Status) throws InterruptedException {
		Elements_Users user = new Elements_Users(driver);
		Wait.wait1Second();
		Wait.explicit_wait_visibilityof_webelement(user.Search_LastName, driver);
		user.Search_LastName.clear();
		user.Search_LastName.sendKeys(LastName);
		user.Search_FirstName.clear();
		user.Search_FirstName.sendKeys(FirstName);
		user.Search_LoginId.clear();
		user.Search_LoginId.sendKeys(LoginId);
		user.Search_Email.clear();
		user.Search_Email.sendKeys(Email);
		new Select(user.UserPage_SelectStatus).selectByVisibleText(Status);
		user.User_SearchButton.click();
		Wait.WaitForElement(driver, OR.VerifySearch);
		assertEquals(user.VerifySearch.getText(), LoginId, "Faild to search created new user");
		assertEquals(user.UserPage_SearchedUser_Status.getText(), Status, "Status didn't change");

	}

	public boolean selectInventorySubSystemCheckBox(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		boolean checkBoxSelected = true;
		Elements_Admin admin = new Elements_Admin(driver);
		Elements_Accounts accounts = new Elements_Accounts(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.clientDetailsInventorySubSystemCheckBox), driver);
		Utility.ScrollToElement(admin.clientDetailsInventorySubSystemCheckBox, driver);
		if (admin.clientDetailsInventorySubSystemCheckBox.isSelected()) {
			test_steps.add("Inventory Sub System check box is already selected");
		} else {
			admin.clientDetailsInventorySubSystemCheckBox.click();
			Wait.waitForElementToBeClickable(By.id(OR.SaveItem_Button), driver, 10);
			accounts.SaveItem_Button.click();
			Wait.waitForElementToBeClickable(By.id(OR.DoneItem_Button), driver, 10);
			accounts.DoneItem_Button.click();
			test_steps.add("Selecting Inventory Sub System check box");
			checkBoxSelected = false;
		}
		return checkBoxSelected;
	}

	public void selectBothInventorySubSystemAndRateCheckBox(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_Admin admin = new Elements_Admin(driver);
		Elements_Accounts accounts = new Elements_Accounts(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.clientDetailsBothCheckBox), driver);
		Utility.ScrollToElement(admin.clientDetailsBothCheckBox, driver);
		if (admin.clientDetailsBothCheckBox.isSelected()) {
			test_steps.add("<b>Both Inventory Sub System and Rate Override</b> check box is already selected");
		} else {
			admin.clientDetailsBothCheckBox.click();
			Wait.waitForElementToBeClickable(By.id(OR.SaveItem_Button), driver, 10);
			accounts.SaveItem_Button.click();
			Wait.waitForElementToBeClickable(By.id(OR.DoneItem_Button), driver, 10);
			accounts.DoneItem_Button.click();
			test_steps.add("Selecting <b>Both Inventory Sub System and Rate Override</b> check box");
		}
	}

	public boolean verifyEntitlementEnable(WebDriver driver, String entityType, ArrayList<String> test_steps,
			boolean disable) throws Exception {
		String selectAlphabet = "//a[contains(@data-bind,'AssignAlphabet')][text()='A']";
		String clickAdministrator = "//a[contains(@data-bind,'RoleName')][text()='Administrator']";
		String report = "//span[contains(@data-bind,'EntityName')][text()='" + entityType + "']/../../td[6]/input";
		String saveButton = "(//button[contains(text(),'Save')])[2]";
		boolean ifSelected = false;
		// Wait.waitForElementToBeClickable(By.xpath(selectAlphabet), driver);
		WebElement selectpage = driver.findElement(By.xpath(selectAlphabet));
		// Wait.waitForElementToBeClickable(By.xpath(report), driver);
		selectpage.click();
		Wait.waitForElementToBeVisibile(By.xpath(clickAdministrator), driver);
		driver.findElement(By.xpath(clickAdministrator)).click();
		WebElement reservationreport = driver.findElement(By.xpath(report));
		Utility.ScrollToElement(reservationreport, driver);
		if (disable) {
			if (reservationreport.isSelected()) {
				System.out.println("reservationreport not  selected");
				test_steps.add("reservationreport not  selected");
				Wait.wait2Second();
				reservationreport.click();
				test_steps.add("click on reservationreport ");
				AdminLogger.info("click on reservationreport ");
			} else {
				test_steps.add("Reservation Report Already selected");
				AdminLogger.info("Reservation Report Already selected");
				ifSelected = true;
			}
		} else {
			if (!reservationreport.isSelected()) {
				test_steps.add("reservation report not is already selected");
				Wait.wait2Second();
				reservationreport.click();
				test_steps.add("click on reservationreport ");
				AdminLogger.info("click on reservationreport ");
				ifSelected = true;
			} else {
				test_steps.add("Reservation Report Already unselected");
				AdminLogger.info("Reservation Report Already selected");
			}
		}
		Wait.waitForElementToBeVisibile(By.xpath(saveButton), driver);
		driver.findElement(By.xpath(saveButton)).click();
		return ifSelected;
	}

	public void verifyEntitlemenReportDesiabled(WebDriver driver, String entityType, ArrayList<String> test_steps)
			throws Exception {
		String selectAlphabet = "//a[contains(@data-bind,'AssignAlphabet')][text()='A']";
		String clickAdministrator = "//a[contains(@data-bind,'RoleName')][text()='Administrator']";
		String report = "//span[contains(@data-bind,'EntityName')][text()='" + entityType + "']/../../td[6]/input";
		String saveButton = "(//button[contains(text(),'Save')])[2]";
		WebElement selectpage = driver.findElement(By.xpath(selectAlphabet));

		selectpage.click();
		Wait.waitForElementToBeVisibile(By.xpath(clickAdministrator), driver);
		driver.findElement(By.xpath(clickAdministrator)).click();
		WebElement reservationreport = driver.findElement(By.xpath(report));
		Utility.ScrollToElement(reservationreport, driver);
		if (reservationreport.isSelected()) {
			Wait.wait2Second();
			reservationreport.click();
			System.out.println("reservation reports desable successfully ");

		} else {
			System.out.println("reservationreport Already selected");

		}
		Wait.waitForElementToBeVisibile(By.xpath(saveButton), driver);
		driver.findElement(By.xpath(saveButton)).click();

	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickClientOption> ' Description: <Clicks The Client Name
	 * In Client Info Tab > ' ' Input parameters: <WebDriver> ' Return: <void>
	 * Created By: <Aqsa Manzoor> ' Created On: <14 July 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public void clickClientName(WebDriver driver) {
		Elements_Admin admin = new Elements_Admin(driver);
		Wait.WaitForElement(driver, OR_Admin.CLICK_CLIENT);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Admin.CLICK_CLIENT), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Admin.CLICK_CLIENT), driver);
		admin.clickClient.click();
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickClientOption> ' Description: <Clicks The Client
	 * Options Tab Button > ' ' Input parameters: <WebDriver> ' Return: <void>
	 * Created By: <Aqsa Manzoor> ' Created On: <14 July 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public void clickClientOption(WebDriver driver) {
		Elements_Admin admin = new Elements_Admin(driver);
		Wait.WaitForElement(driver, OR_Admin.CLICK_CLIENT_OPTION);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Admin.CLICK_CLIENT_OPTION), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Admin.CLICK_CLIENT_OPTION), driver);
		admin.clickClientOption.click();
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getDefaultClinetCurrency> ' Description: <Get the
	 * selected Currency Type in the Client and returns it > ' ' Input
	 * parameters: <WebDriver> ' Return: <String> Created By: <Aqsa Manzoor> '
	 * Created On: <14 July 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public String getDefaultClientCurrency(WebDriver driver) {
		Elements_Admin admin = new Elements_Admin(driver);
		Wait.WaitForElement(driver, OR_Admin.GET_DEFAUL_CURRENCY);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Admin.GET_DEFAUL_CURRENCY), driver);
		String currency = admin.getDefaultCurrency.getText();
		return currency;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getClientDateFormat> ' Description: <Get the selected
	 * Date Format Type in the Client and returns it > ' ' Input parameters:
	 * <WebDriver> ' Return: <String> Created By: <Aqsa Manzoor> ' Created On:
	 * <14 July 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public String getClientDateFormat(WebDriver driver) {
		Elements_Admin admin = new Elements_Admin(driver);
		Wait.WaitForElement(driver, OR_Admin.GET_DATE_FORMAT);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Admin.GET_DATE_FORMAT), driver);
		String date = admin.getDateFormat.getText();
		return date;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getClientDateFormat> ' Description: <Get the selected
	 * Date Format Type in the Client and returns it > ' ' Input parameters:
	 * <WebDriver> ' Return: <String> Created By: <Aqsa Manzoor> ' Created On:
	 * <14 July 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> selectClientDateFormat(WebDriver driver, String dateFormatType) {
		ArrayList<String> testSteps = new ArrayList<>();

		Elements_Admin admin = new Elements_Admin(driver);
		Wait.WaitForElement(driver, OR_Admin.SELECT_DATE_FORMAT);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Admin.SELECT_DATE_FORMAT), driver);
		new Select(admin.selectDateFormat).selectByVisibleText(dateFormatType);
		testSteps.add("The client Date Format Type changed to: " + dateFormatType);
		AdminLogger.info("The client Date Format Type changed to:" + dateFormatType);

		return testSteps;

	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <adminSaveButton> ' Description: <Click the save button in
	 * client info > ' ' Input parameters: <WebDriver> ' Return: <void> Created
	 * By: <Aqsa Manzoor> ' Created On: <14 July 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public void adminSaveButton(WebDriver driver) {
		Elements_Admin admin = new Elements_Admin(driver);
		Wait.WaitForElement(driver, OR_Admin.ADMIN_SAVE_BUTTON);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Admin.ADMIN_SAVE_BUTTON), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Admin.ADMIN_SAVE_BUTTON), driver);
		admin.adminSaveButton.click();

	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * 'Method Name: <adminDoneButton> ' Description: <Click the done button in
	 * client info > ' ' Input parameters: <WebDriver> ' Return: <void> Created
	 * By: <Aqsa Manzoor> ' Created On: <14 July 2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public void adminDoneButton(WebDriver driver) {
		Elements_Admin admin = new Elements_Admin(driver);
		Wait.WaitForElement(driver, OR_Admin.ADMIN_DONE_BUTTON);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Admin.ADMIN_DONE_BUTTON), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Admin.ADMIN_DONE_BUTTON), driver);
		admin.adminDoneButton.click();

	}

}
