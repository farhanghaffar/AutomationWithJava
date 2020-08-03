package com.innroad.inncenter.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.innroad.inncenter.interfaces.ILedgerAccount;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_LedgeAccount;

public class LedgerAccount implements ILedgerAccount {

	public static Logger accountsLogger = Logger.getLogger("LedgeAccount");

	public void NewAccountbutton(WebDriver driver) throws InterruptedException {

		Elements_LedgeAccount CreateNewLedgeAccount = new Elements_LedgeAccount(driver);
		CreateNewLedgeAccount.Create_New_LedgeAccount_ButtonClick.click();

	}

	public void LedgerAccountDetails(WebDriver driver, String AccountName, String AccountDescription,
			String DefaultAmount) throws InterruptedException {
		System.out.println(AccountName);
		Elements_LedgeAccount CreateNewLedgeAccount = new Elements_LedgeAccount(driver);
		try {
			Wait.waitUntilPresenceOfElementLocated(OR.Ledger_Accounts_Name_Inputs, driver);
			Utility.ScrollToElement(CreateNewLedgeAccount.Ledger_Accounts_Name_Inputs.get(0), driver);
			CreateNewLedgeAccount.Ledger_Accounts_Name_Inputs.get(0).sendKeys(AccountName);
			CreateNewLedgeAccount.Ledger_Accounts_Name_Inputs.get(1).sendKeys(AccountDescription);
		} catch (Exception e) {
			CreateNewLedgeAccount.Ledger_Accounts_Name_1.sendKeys(AccountName);
		}

	}

	public void LedgerAccountDetails(WebDriver driver, String AccountName, String AccountDescription,
			String DefaultAmount, String AccountType, String Status) throws InterruptedException {
		System.out.println(AccountName);
		Elements_LedgeAccount CreateNewLedgeAccount = new Elements_LedgeAccount(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Ledger_Accounts_Name_Inputs, driver);
		Utility.ScrollToElement(CreateNewLedgeAccount.Ledger_Accounts_Name_Inputs.get(0), driver);
		CreateNewLedgeAccount.Ledger_Accounts_Name_Inputs.get(0).sendKeys(AccountName);
		CreateNewLedgeAccount.Ledger_Accounts_Name_Inputs.get(1).sendKeys(AccountDescription);
		new Select(CreateNewLedgeAccount.SelectLedgerAccountType).selectByVisibleText(AccountType);
		Wait.explicit_wait_visibilityof_webelement(CreateNewLedgeAccount.LedgerAccount_DefaultAmount, driver);
		CreateNewLedgeAccount.LedgerAccount_DefaultAmount.sendKeys(DefaultAmount);
		new Select(CreateNewLedgeAccount.SelectLedgerAccountStatus).selectByVisibleText(Status);

	}

	public void VerifyAccountDetails(WebDriver driver, String AccountName, String AccountDescription,
			String AccountType, String AccountDefaultAmount, String AccountStatus) throws InterruptedException {
		// System.out.println(AccountName);
		String AccountPath = "//table[@id='MainContent_dgclientLegdAccounts']//span[text()='" + AccountName + "']";
		WebElement Account = driver.findElement(By.xpath(AccountPath));
		try {
			Wait.explicit_wait_visibilityof_webelement(Account, driver);
			Utility.ScrollToElement(Account, driver);
		} catch (Exception e) {
			Utility.app_logs.info(" No Account " + AccountName + " found ");
			Assert.assertTrue(false, " No Account " + AccountName + " found ");
		}

		String CheckBoxPath = AccountPath + "//parent::td//preceding-sibling::td/input";
		WebElement CheckBox = driver.findElement(By.xpath(CheckBoxPath));
		Assert.assertEquals(CheckBox.isSelected(), false, "Failed : Check Box Is Selected");
		String DescriptionPath = AccountPath + "//parent::td//following::td[1]/span";
		WebElement Description = driver.findElement(By.xpath(DescriptionPath));
		Assert.assertEquals(Description.getText(), AccountDescription, "Failed : Account Description Missmatched");
		String TypePath = AccountPath + "//parent::td//following::td[3]/select";
		Select Type = new Select(driver.findElement(By.xpath(TypePath)));
		Assert.assertEquals(Type.getFirstSelectedOption().getText(), AccountType, "Failed : Account Type Missmatched");
		String StatusPath = AccountPath + "//parent::td//following::td[6]/select";
		Select Status = new Select(driver.findElement(By.xpath(StatusPath)));
		Assert.assertEquals(Status.getFirstSelectedOption().getText(), AccountStatus,
				"Failed : Account Status Missmatched");
		String DefaultAmountPath = AccountPath + "//parent::td//following::td[5]/span";
		WebElement DefaultAmount = driver.findElement(By.xpath(DefaultAmountPath));
		Assert.assertEquals(DefaultAmount.getText(), AccountDefaultAmount + ".00",
				"Failed : Account DefaultAmount Missmatched");
	}

	public void SaveLedgerAccount(WebDriver driver) throws InterruptedException {
		Elements_LedgeAccount CreateNewLedgeAccount = new Elements_LedgeAccount(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Ledger_Accounts_SaveButtonClick, driver);
		CreateNewLedgeAccount.Ledger_Accounts_SaveButtonClick.click();

	}

	public void DeleteLedgerAccount(WebDriver driver) throws InterruptedException {
		Elements_LedgeAccount CreateNewLedgeAccount = new Elements_LedgeAccount(driver);
		Wait.wait2Second();
		int size = driver.findElements(By.xpath(OR.LedgerAccount_Chekbox)).size();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", CreateNewLedgeAccount.LedgerAccount_Chekbox.get(size - 2));

		if (CreateNewLedgeAccount.LedgerAccount_Chekbox.get(size - 1).isSelected()) {
			accountsLogger.info("Check box already checked");
		} else {

			CreateNewLedgeAccount.LedgerAccount_Chekbox.get(size - 2).click();
		}

		CreateNewLedgeAccount.Ledger_Accounts_DeleteButtonClick.click();
	}

	public void DeleteLedgerAccount(WebDriver driver, String AccountName) throws InterruptedException {
		Elements_LedgeAccount CreateNewLedgeAccount = new Elements_LedgeAccount(driver);
		String AccountPath = "//table[@id='MainContent_dgclientLegdAccounts']//span[text()='" + AccountName + "']";
		WebElement Account = driver.findElement(By.xpath(AccountPath));
		try {
			Wait.explicit_wait_visibilityof_webelement(Account, driver);
			Utility.ScrollToElement(Account, driver);
		} catch (Exception e) {
			Utility.app_logs.info(" No Account " + AccountName + " found ");
			Assert.assertTrue(false, " No Account " + AccountName + " found ");
		}

		String CheckBoxPath = AccountPath + "//parent::td//preceding-sibling::td/input";
		WebElement CheckBox = driver.findElement(By.xpath(CheckBoxPath));
		if (CheckBox.isSelected()) {
			accountsLogger.info("Check box already checked");
		} else {
			CheckBox.click();
		}
		Assert.assertEquals(CheckBox.isSelected(), true, "Failed : Check Box Is not Selected");
		Utility.ScrollToElement(CreateNewLedgeAccount.Ledger_Accounts_DeleteButtonClick, driver);
		CreateNewLedgeAccount.Ledger_Accounts_DeleteButtonClick.click();
	}

	public void ChangeStatus(WebDriver driver, String AccountName, String Status) throws InterruptedException {
		Elements_LedgeAccount CreateNewLedgeAccount = new Elements_LedgeAccount(driver);
		String AccountPath = "//table[@id='MainContent_dgclientLegdAccounts']//span[text()='" + AccountName + "']";
		WebElement Account = driver.findElement(By.xpath(AccountPath));
		try {
			Wait.explicit_wait_visibilityof_webelement(Account, driver);
			Utility.ScrollToElement(Account, driver);
		} catch (Exception e) {
			Utility.app_logs.info(" No Account " + AccountName + " found ");
			Assert.assertTrue(false, " No Account " + AccountName + " found ");
		}

		String CheckBoxPath = AccountPath + "//parent::td//preceding-sibling::td/input";
		WebElement CheckBox = driver.findElement(By.xpath(CheckBoxPath));
		if (CheckBox.isSelected()) {
			accountsLogger.info("Check box already checked");
		} else {
			CheckBox.click();
		}
		Assert.assertEquals(CheckBox.isSelected(), true, "Failed : Check Box Is not Selected");
		CreateNewLedgeAccount.Ledger_Accounts_EditButtonClick.click();
		Wait.explicit_wait_visibilityof_webelement(CreateNewLedgeAccount.SelectLedgerAccountStatus, driver);
		Utility.ScrollToElement(CreateNewLedgeAccount.SelectLedgerAccountStatus, driver);
		new Select(CreateNewLedgeAccount.SelectLedgerAccountStatus).selectByVisibleText(Status);
		SaveLedgerAccount(driver);
	}

	public void SelectStatus(WebDriver driver, String Status) throws InterruptedException {
		Elements_LedgeAccount CreateNewLedgeAccount = new Elements_LedgeAccount(driver);

		Wait.explicit_wait_visibilityof_webelement(CreateNewLedgeAccount.LedgerAccount_SelectStatusType, driver);
		Utility.ScrollToElement(CreateNewLedgeAccount.LedgerAccount_SelectStatusType, driver);
		new Select(CreateNewLedgeAccount.LedgerAccount_SelectStatusType).selectByVisibleText(Status);
		Wait.wait1Second();
		driver.navigate().refresh();
	}

	public void EditLedgerAccount(WebDriver driver, String Status) throws InterruptedException {
		Elements_LedgeAccount CreateNewLedgeAccount = new Elements_LedgeAccount(driver);
		CreateNewLedgeAccount.Ledger_Accounts_EditButtonClick.click();

		Wait.waitUntilPresenceOfElementLocated(OR.Ledger_Accounts_Status, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", CreateNewLedgeAccount.Ledger_Accounts_Status
				.get(CreateNewLedgeAccount.Ledger_Accounts_Status.size() - 1));

		new Select(CreateNewLedgeAccount.Ledger_Accounts_Status
				.get(CreateNewLedgeAccount.Ledger_Accounts_Status.size() - 1)).selectByVisibleText(Status);

	}

	public void PropertyTab(WebDriver driver) throws InterruptedException {
		Elements_LedgeAccount CreateNewLedgeAccount = new Elements_LedgeAccount(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", CreateNewLedgeAccount.LedgerAccount_PropertyTab);
		driver.navigate().refresh();
	}

	public void EditLedgerAccount_PropertyTab(WebDriver driver, String Status) throws InterruptedException {
		Elements_LedgeAccount CreateNewLedgeAccount = new Elements_LedgeAccount(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement Account = driver
				.findElement(By.xpath("//input[@id='MainContent_dgPropLedgAccounts_chkClientAccFlagItem_1']"));
		jse.executeScript("arguments[0].scrollIntoView();", Account);
		Account.click();
		// Select Account
		jse.executeScript("arguments[0].click();", CreateNewLedgeAccount.Ledger_Accounts_EditButtonClick_PropertyTab);
		//

	}

	public void SelectStatus_PropertyTab(WebDriver driver, String Status) throws InterruptedException {
		Elements_LedgeAccount CreateNewLedgeAccount = new Elements_LedgeAccount(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", CreateNewLedgeAccount.Ledger_Accounts_SelectStatus);
		Wait.explicit_wait_visibilityof_webelement(CreateNewLedgeAccount.Ledger_Accounts_SelectStatus, driver);
		Utility.ScrollToElement(CreateNewLedgeAccount.Ledger_Accounts_SelectStatus, driver);
		new Select(CreateNewLedgeAccount.Ledger_Accounts_SelectStatus).selectByVisibleText(Status);
		Wait.wait1Second();
		driver.navigate().refresh();
	}

	public void SaveButtoClick_PropertyTab(WebDriver driver) throws InterruptedException {
		Elements_LedgeAccount CreateNewLedgeAccount = new Elements_LedgeAccount(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", CreateNewLedgeAccount.Ledger_Accounts_SaveButtonClick_PropertyTab);
		driver.navigate().refresh();
	}

	public void DeleteLedgerAccount_PropertyTab(WebDriver driver, String AccountName) throws InterruptedException {
		Elements_LedgeAccount CreateNewLedgeAccount = new Elements_LedgeAccount(driver);
		String AccountPath = "//table[@id='MainContent_dgclientLegdAccounts']//span[text()='" + AccountName + "']";
		WebElement Account = driver.findElement(By.xpath(AccountPath));
		try {
			Wait.explicit_wait_visibilityof_webelement(Account, driver);
			Utility.ScrollToElement(Account, driver);
		} catch (Exception e) {
			Utility.app_logs.info(" No Account " + AccountName + " found ");
			Assert.assertTrue(false, " No Account " + AccountName + " found ");
		}

		String CheckBoxPath = AccountPath + "//parent::td//preceding-sibling::td/input";
		WebElement CheckBox = driver.findElement(By.xpath(CheckBoxPath));
		if (CheckBox.isSelected()) {
			accountsLogger.info("Check box already checked");
		} else {
			CheckBox.click();
		}
		Assert.assertEquals(CheckBox.isSelected(), true, "Failed : Check Box Is not Selected");
		Utility.ScrollToElement(CreateNewLedgeAccount.Ledger_Accounts_DeleteButtonClick, driver);
		CreateNewLedgeAccount.Ledger_Accounts_DeleteButtonClick.click();
	}

	public void MasterTab(WebDriver driver) throws InterruptedException {
		Elements_LedgeAccount CreateNewLedgeAccount = new Elements_LedgeAccount(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", CreateNewLedgeAccount.LedgerAccount_MasterTab);
		driver.navigate().refresh();
	}
	
	public boolean isAccountExist(WebDriver driver, String AccountName) throws InterruptedException {
		String AccountPath = "//table[@id='MainContent_dgclientLegdAccounts']//span[starts-with(text(),'" + AccountName
				+ "')]";
		return driver.findElements(By.xpath(AccountPath)).size() > 0;

	}

	public void clickOnDelete(WebDriver driver) throws InterruptedException {
		Elements_LedgeAccount CreateNewLedgeAccount = new Elements_LedgeAccount(driver);
		CreateNewLedgeAccount.Ledger_Accounts_DeleteButtonClick.click();
	}
	public ArrayList<String> checkedLedgerAccounts(WebDriver driver, String AccountName) throws InterruptedException {

		ArrayList<String> listOfId = new ArrayList<>();
		String AccountPath = "//table[@id='MainContent_dgclientLegdAccounts']//span[starts-with(text(),'" + AccountName
				+ "')]//..//..//td//input";
		String getIdPath = "//table[@id='MainContent_dgclientLegdAccounts']//span[starts-with(text(),'"+AccountName+"')]//..//..//td//input[@type='checkbox']";
		
		System.out.println("AccountPath: "+AccountPath);
		System.out.println("getIdPath: "+getIdPath);

		List<WebElement> Accounts = driver.findElements(By.xpath(AccountPath));
		List<WebElement> getId = driver.findElements(By.xpath(getIdPath));
		System.out.println("Accounts: "+Accounts.size());
		System.out.println("getId: "+getId.size());

		Utility.ScrollToElement(Accounts.get(0), driver);
		for (int i = 0; i < Accounts.size();) {
			Accounts.get(i).click();
			listOfId.add(getId.get(i).getAttribute("id"));

			i = i + 2;
		}
		for (int i = 0; i < listOfId.size(); i++) {
			System.out.println(listOfId.get(i));
		}
		System.out.println(listOfId.size());
		return listOfId;
	}

	public void changAccStatus(WebDriver driver, ArrayList<String> InputId, String Status) throws InterruptedException {

		System.out.println(InputId.size());
		
		for (int i = 0; i < InputId.size(); i++) {
			String AccountPath = "(//input[@id='"+InputId.get(i)+"']//..//following-sibling::td//select)[2]";
			System.out.println(AccountPath);
			WebElement select = driver.findElement(By.xpath(AccountPath));
			Utility.ScrollToElement(select, driver);
			new Select(select).selectByVisibleText(Status);
		}
	}
	public void clickonEditButton(WebDriver driver) throws InterruptedException {
		Elements_LedgeAccount CreateNewLedgeAccount = new Elements_LedgeAccount(driver);
		CreateNewLedgeAccount.Ledger_Accounts_EditButtonClick.click();
	}



}