package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Reservation;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Accounts;
import com.innroad.inncenter.webelements.Elements_AdvanceGroups;
import com.innroad.inncenter.webelements.Elements_All_Payments;
import com.innroad.inncenter.webelements.Elements_CPReservation;
import com.innroad.inncenter.webelements.Elements_Groups;
import com.innroad.inncenter.webelements.Elements_OldGroups;
import com.innroad.inncenter.webelements.Elements_Reservation;
import com.relevantcodes.extentreports.ExtentTest;

public class Groups {

	public static Logger groupLogger = Logger.getLogger("Group");

	public ArrayList<String> click_NewAccount(WebDriver driver, ExtentTest test, ArrayList<String> test_steps) {

		Elements_Groups group = new Elements_Groups(driver);

		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOf(group.New_Account_Btn));
		Wait.WaitForElement(driver, OR.New_Account_Btn);
		group.New_Account_Btn.click();
		test_steps.add("Click on New Account");
		return test_steps;
	}

	public ArrayList<String> type_GroupName(WebDriver driver, ExtentTest test1, String GroupName,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Groups group = new Elements_Groups(driver);
		// Wait.wait3Second();

		click_NewAccount(driver, test1, test_steps);
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOf(group.Group_Name));
		group.Group_Name.sendKeys(GroupName);
		test_steps.add("Enter Group Name : " + GroupName);
		groupLogger.info("Enter Group Name : " + GroupName);
		return test_steps;
	}

	public ArrayList<String> type_AccountAttributes(WebDriver driver, ExtentTest test1, String MarketSegment,
			String Referral, ArrayList<String> test_steps) {

		Elements_Groups group = new Elements_Groups(driver);

		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOf(group.Market_Segment));

		Wait.WaitForElement(driver, OR.Market_Segment);
		new Select(group.Market_Segment).selectByVisibleText(MarketSegment);
		test_steps.add("Select Market Segment : " + MarketSegment);
		groupLogger.info("Select Market Segment : " + MarketSegment);

		new Select(group.Referrls).selectByVisibleText(Referral);
		test_steps.add("Select Referral : " + Referral);
		groupLogger.info("Select Referral : " + Referral);
		return test_steps;
	}

	public ArrayList<String> type_MailingAddrtess(WebDriver driver, ExtentTest test1, String FirstName, String LastName,
			String Phone, String Address, String City, String State, String Country, String PostalCode,
			ArrayList<String> test_steps) {

		Elements_Groups group = new Elements_Groups(driver);

		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOf(group.FirstName));

		Wait.WaitForElement(driver, OR.FirstName);
		group.FirstName.sendKeys(FirstName);
		test_steps.add("Enter First Name : " + FirstName);
		groupLogger.info("Enter First Name : " + FirstName);

		group.LastName.sendKeys(LastName);
		test_steps.add("Enter Last Name : " + LastName);
		groupLogger.info("Enter Last Name : " + LastName);

		group.Phone.sendKeys(Phone);
		test_steps.add("Enter Phone Number : " + Phone);
		groupLogger.info("Enter Phone Number : " + Phone);

		group.Address1.sendKeys(Address);
		test_steps.add("Enter Address : " + Address);
		groupLogger.info("Enter Address : " + Address);

		group.City.sendKeys(City);
		test_steps.add("Enter City : " + City);
		groupLogger.info("Enter City : " + City);

		new Select(group.Country).selectByVisibleText(Country);
		test_steps.add("Select Country : " + Country);
		groupLogger.info("Select Country : " + Country);

		new Select(group.State).selectByVisibleText(State);
		test_steps.add("Select State : " + State);
		groupLogger.info("Select State : " + State);

		group.PostalCode.sendKeys(PostalCode);
		test_steps.add("Enter Postal code : " + PostalCode);
		groupLogger.info("Enter Postal code : " + PostalCode);

		return test_steps;
	}

	public ArrayList<String> Billinginfo(WebDriver driver, ExtentTest test1, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);

		WebDriverWait wait = new WebDriverWait(driver, 90);
		Utility.ScrollToElement(group.Check_Mailing_Info, driver);
		Wait.WaitForElement(driver, OR.Check_Mailing_Info);
		wait.until(ExpectedConditions.visibilityOf(group.Check_Mailing_Info));
		if (group.Check_Mailing_Info.isSelected()) {
			// System.out.println("Check box already checked");
		} else {

			group.Check_Mailing_Info.click();
			test_steps.add("Click same as mailing address");
			groupLogger.info("Click same as mailing address");

		}
		return test_steps;

	}

	public ArrayList<String> Save(WebDriver driver, ExtentTest test1, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);

		Wait.WaitForElement(driver, OR.Group_Save);
		Wait.wait3Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", group.Group_Save);

		// group.Group_Save.click();

		test_steps.add("Click on Save");
		return test_steps;

	}

	public ArrayList<String> navigateFolio(WebDriver driver, ExtentTest test1, ArrayList<String> test_steps) {
		Elements_Groups group = new Elements_Groups(driver);

		// WebDriverWait wait = new WebDriverWait(driver, 90);
		Wait.explicit_wait_visibilityof_webelement_350(group.Group_Folio, driver);
		// Wait.WaitForElement(driver, OR.Group_Folio);
		group.Group_Folio.click();
		test_steps.add("Click on Group Folio");
		groupLogger.info("Click on Group Folio");
		return test_steps;

	}

	public ArrayList<String> addLineItems(WebDriver driver, ExtentTest test1, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);

		Wait.WaitForElement(driver, OR.Group_Folio_Add_LineItem);
		group.Group_Folio_Add_LineItem.click();
		test_steps.add("Click on Add Line item");
		groupLogger.info("Click on Add Line item");

		Wait.WaitForElement(driver, "//select[@id='MainContent_Folio1_dgLineItems_drpFolioProperty_0']");
		new Select(driver.findElement(By.xpath("//select[@id='MainContent_Folio1_dgLineItems_drpFolioProperty_0']")))
				.selectByIndex(1);
		// Wait.wait2Second();

		Wait.WaitForElement(driver, "//select[@id='MainContent_Folio1_dgLineItems_drpLedgeraccountname_0']");
		new Select(
				driver.findElement(By.xpath("//select[@id='MainContent_Folio1_dgLineItems_drpLedgeraccountname_0']")))
						.selectByIndex(1);

		String Cat1 = new Select(
				driver.findElement(By.xpath("//select[@id='MainContent_Folio1_dgLineItems_drpLedgeraccountname_0']")))
						.getFirstSelectedOption().getText();
		// System.out.println("Cat1 : "+Cat1);
		test_steps.add("Selected category : " + Cat1);
		groupLogger.info("Selected category : " + Cat1);

		Wait.WaitForElement(driver, "//input[@id='MainContent_Folio1_dgLineItems_txtAmount_0']");
		driver.findElement(By.xpath("//input[@id='MainContent_Folio1_dgLineItems_txtAmount_0']")).clear();
		// Wait.wait2Second();
		driver.findElement(By.xpath("//input[@id='MainContent_Folio1_dgLineItems_txtAmount_0']")).sendKeys("100");
		test_steps.add("Enter amount 100");
		groupLogger.info("Enter amount 100");
		Commit(driver, test1);

		Wait.WaitForElement(driver, OR.Group_Folio_Add_LineItem);
		group.Group_Folio_Add_LineItem.click();

		Wait.WaitForElement(driver, "//select[@id='MainContent_Folio1_dgLineItems_drpFolioProperty_1']");
		new Select(driver.findElement(By.xpath("//select[@id='MainContent_Folio1_dgLineItems_drpFolioProperty_1']")))
				.selectByIndex(1);
		// Wait.wait2Second();
		Wait.WaitForElement(driver, "//select[@id='MainContent_Folio1_dgLineItems_drpLedgeraccountname_1']");
		new Select(
				driver.findElement(By.xpath("//select[@id='MainContent_Folio1_dgLineItems_drpLedgeraccountname_1']")))
						.selectByIndex(2);

		String Cat2 = new Select(
				driver.findElement(By.xpath("//select[@id='MainContent_Folio1_dgLineItems_drpLedgeraccountname_1']")))
						.getFirstSelectedOption().getText();
		// System.out.println("Cat2 : "+Cat2);
		test_steps.add("Selected category : " + Cat2);
		groupLogger.info("Selected category : " + Cat2);

		Wait.WaitForElement(driver, "//input[@id='MainContent_Folio1_dgLineItems_txtAmount_1']");
		driver.findElement(By.xpath("//input[@id='MainContent_Folio1_dgLineItems_txtAmount_1']")).clear();
		// Wait.wait2Second();
		driver.findElement(By.xpath("//input[@id='MainContent_Folio1_dgLineItems_txtAmount_1']")).sendKeys("150");
		test_steps.add("Enter amount 150");
		groupLogger.info("Enter amount 150");

		Commit(driver, test1);
		Save(driver, test1, test_steps);
		return test_steps;

	}

	public void Commit(WebDriver driver, ExtentTest test) {
		Elements_Groups group = new Elements_Groups(driver);
		group.Group_Folio_Commit_Lineitem.click();
		groupLogger.info("Click on commit");
	}

	public ArrayList<String> ageingPayment(WebDriver driver, ExtentTest test1, String AccountType, String PaymentType,
			String CardName, String CCNumber, String CCExpiry, String CCVCode, String Authorizationtype,
			String ChangeAmount, String ChangeAmountValue, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);

		navigateFolio(driver, test1, test_steps);
		// Wait.wait2Second();

		Wait.WaitForElement(driver, "//input[@id='MainContent_Folio1_btnPay']");
		driver.findElement(By.xpath("//input[@id='MainContent_Folio1_btnPay']")).click();
		Wait.wait3Second();
		if (PaymentType.equalsIgnoreCase("MC")) {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
			String text = null;
			// AccountPayment.Account_pay.click();

			Wait.WaitForElement(driver, OR.Group_Folio_PaymentMethod);
			new Select(group.Group_Folio_PaymentMethod).selectByVisibleText(PaymentType);
			groupLogger.info("Payment Method : " + PaymentType);

			// Wait.wait3Second();

			Wait.WaitForElement(driver, OR.Group_Folio_CardInfo);
			group.Group_Folio_CardInfo.click();
			// Wait.explicit_wait_xpath(OR.Group_Folio_NameOnCard);
			Wait.wait2Second();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body1")));

			Wait.WaitForElement(driver, OR.Group_Folio_NameOnCard);
			group.Group_Folio_NameOnCard.sendKeys(CardName);
			test_steps.add("Enter name on card : " + CardName);
			groupLogger.info("Enter name on card : " + CardName);

			group.Group_Folio_CardNumber.sendKeys(CCNumber);
			test_steps.add("Enter credit card number : " + CCNumber);
			groupLogger.info("Enter credit card number : " + CCNumber);

			group.Group_Folio_ExpDate.sendKeys(CCExpiry);
			test_steps.add("Enter Card expiry date : " + CCExpiry);
			groupLogger.info("Enter Card expiry date : " + CCExpiry);

			group.Group_Folio_CVV.sendKeys(CCVCode);
			test_steps.add("Enter card CVV : " + CCVCode);
			groupLogger.info("Enter card CVV : " + CCVCode);

			group.Group_Folio_OK.click();
			test_steps.add("Clicking on Folio Ok");
			groupLogger.info("Clicking on Folio Ok");

			Wait.wait2Second();

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
			new Select(group.Group_Folio_AuthType).selectByVisibleText(Authorizationtype);
			if (ChangeAmount.equalsIgnoreCase("Yes")) {
				Wait.WaitForElement(driver, OR.Group_Folio_Amount);
				group.Group_Folio_Amount.clear();
				// Wait.wait2Second();
				Wait.WaitForElement(driver, OR.Group_Folio_Amount);
				group.Group_Folio_Amount.sendKeys(ChangeAmountValue);
				test_steps.add("Enter Amount to pay " + ChangeAmountValue);
				groupLogger.info("Enter Amount to pay " + ChangeAmountValue);
			} else {
				// System.out.println("Processed complete amount");
			}
			// Wait.wait2Second();
			if (Authorizationtype.equalsIgnoreCase("Capture")) {
				Wait.WaitForElement(driver, "//a[@id='dgPaymentDetails_lbtnDisplaycaption_0']");
				text = driver.findElement(By.xpath("//a[@id='dgPaymentDetails_lbtnDisplaycaption_0']")).getText();
				driver.findElement(By.xpath("//input[@id='dgPaymentDetails_ChkItemSelect_0']")).click();
				group.Group_Folio_Process.click();
				groupLogger.info("Click Process");
			}
			// Wait.wait3Second();
			// Wait.explicit_wait_xpath("//table[@id='dgTransactionPayList']/tbody/tr[2]/td[4]");

			Wait.WaitForElement(driver, OR.Group_Folio_PaymentMethod);
			String GetPaymentMethod = new Select(group.Group_Folio_PaymentMethod).getFirstSelectedOption().getText();
			// System.out.println(GetPaymentMethod + " "+GetPaymentMethod);
			if (GetPaymentMethod.equals(PaymentType)) {
				// System.out.println("Paymnet Success");
				groupLogger.info("Payment Success");

			} else {
				// System.out.println("Paymnet Failed");
				groupLogger.info("Payment Failed");
			}
			group.Group_Folio_Continue.click();
			groupLogger.info("Click Continue");
			Wait.wait3Second();

			Alert alert = driver.switchTo().alert();
			alert.accept();
			Wait.wait3Second();
			try {
				if (driver.switchTo().alert() != null) {
					alert.accept();
				}
			} catch (Exception e) {
				// System.out.println("No Alert");
			}
			// Wait.wait2Second();

			Wait.WaitForElement(driver, "//a[contains(text(),'Name')]");
			String GetMCCard = driver.findElement(By.xpath("//a[contains(text(),'Name')]")).getText();
			// System.out.println(GetMCCard + " "+GetMCCard);
			if (GetMCCard.contains("Name: test Account #: XXXX5454 Exp.")) {
				// System.out.println("Paymnet Success");

				groupLogger.info("Payment Success");
			}

			driver.switchTo().defaultContent();
			Wait.wait2Second();
			Save(driver, test1, test_steps);
			test_steps.add("Clicking on Save Account");
			groupLogger.info("Clicking on Save Account");

			navigateFolio(driver, test1, test_steps);
			String loc = "//table[@id='MainContent_Folio1_dgLineItems']/tbody/tr/td/table/tbody/tr/td/a[contains(text(),'"
					+ text.trim() + "')]/../../../../../following-sibling::td/img";

			String str = driver.findElement(By.xpath(loc)).getAttribute("title");
			test_steps.add("Payment : " + str);
			groupLogger.info("Payment : " + str);

		} else if (PaymentType.equalsIgnoreCase("Cash")) {
			Wait.wait2Second();

			driver.switchTo().defaultContent();

			driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));

			WebDriverWait wait = new WebDriverWait(driver, 90);

			Wait.WaitForElement(driver, "//span[@id='lblPaymentMethod']/../following-sibling::td/select");
			wait.until(ExpectedConditions.visibilityOf(
					driver.findElement(By.xpath("//span[@id='lblPaymentMethod']/../following-sibling::td/select"))));
			new Select(driver.findElement(By.xpath("//span[@id='lblPaymentMethod']/../following-sibling::td/select")))
					.selectByVisibleText(PaymentType);

			test_steps.add("Select Payment type " + PaymentType);
			groupLogger.info("Select Payment type " + PaymentType);

			// Wait.wait2Second();

			Wait.WaitForElement(driver, OR.Group_Folio_Amount);
			group.Group_Folio_Amount.clear();
			// Wait.wait2Second();

			Wait.WaitForElement(driver, OR.Group_Folio_Amount);
			group.Group_Folio_Amount.sendKeys(ChangeAmountValue);
			test_steps.add("Enter Amount to pay " + ChangeAmountValue);
			groupLogger.info("Enter Amount to pay " + ChangeAmountValue);

			Wait.WaitForElement(driver, "//a[@id='dgPaymentDetails_lbtnDisplaycaption_0']");
			String text = driver.findElement(By.xpath("//a[@id='dgPaymentDetails_lbtnDisplaycaption_0']")).getText();
			driver.findElement(By.xpath("//input[@id='dgPaymentDetails_ChkItemSelect_0']")).click();
			// System.out.println(text);

			Wait.WaitForElement(driver, OR.Group_Folio_Add);
			group.Group_Folio_Add.click();

			test_steps.add("Clicking on Add");
			groupLogger.info("Clicking on Add");

			// Wait.wait3Second();

			Wait.WaitForElement(driver, OR.Group_Folio_Continue);
			group.Group_Folio_Continue.click();
			test_steps.add("Clicking on Continue");
			groupLogger.info("Clicking on Continue");
			Wait.wait2Second();
			driver.switchTo().defaultContent();
			Save(driver, test1, test_steps);
			test_steps.add("Clicking on Save Account");
			groupLogger.info("Clicking on Save Account");
			navigateFolio(driver, test1, test_steps);
			String loc = "//table[@id='MainContent_Folio1_dgLineItems']/tbody/tr/td/table/tbody/tr/td/a[contains(text(),'"
					+ text.trim() + "')]/../../../../../following-sibling::td/img";
			String str = driver.findElement(By.xpath(loc)).getAttribute("title");
			// System.out.println(str);
			test_steps.add("Payment : " + str);
			groupLogger.info("Payment : " + str);
		}
		return test_steps;

	}

	public ArrayList<String> ageingPaymentAutoApply(WebDriver driver, ExtentTest test1, String AccountType,
			String PaymentType, String CardName, String CCNumber, String CCExpiry, String CCVCode,
			String Authorizationtype, String ChangeAmount, String ChangeAmountValue, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);
		navigateFolio(driver, test1, test_steps);
		test_steps.add("Clicking on Folio");
		groupLogger.info("Clicking on Folio");

		Wait.WaitForElement(driver, OR.Group_Folio_EndingBalance);
		String balance = group.Group_Folio_EndingBalance.getText();
		balance = balance.replace("$", "");
		float bal = Float.parseFloat(balance);
		test_steps.add("Before Pay Folio balance : " + balance);
		groupLogger.info("Before Pay Folio balance : " + balance);

		navigateFolio(driver, test1, test_steps);
		// Wait.wait2Second();

		Wait.WaitForElement(driver, "//input[@id='MainContent_Folio1_btnPay']");
		driver.findElement(By.xpath("//input[@id='MainContent_Folio1_btnPay']")).click();
		test_steps.add("Clicking on Pay");
		groupLogger.info("Clicking on Pay");

		Wait.wait2Second();
		if (PaymentType.equalsIgnoreCase("MC")) {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
			String text = null;

			Wait.WaitForElement(driver, OR.Group_Folio_PaymentMethod);
			new Select(group.Group_Folio_PaymentMethod).selectByVisibleText(PaymentType);
			test_steps.add("Select Payment type : " + PaymentType);
			groupLogger.info("Select Payment type : " + PaymentType);

			// Wait.wait3Second();
			Wait.explicit_wait_elementToBeClickable(group.Group_Folio_CardInfo, driver);
			group.Group_Folio_CardInfo.click();
			test_steps.add("Clicking on Card Info");
			groupLogger.info("Clicking on Card Info");

			Wait.wait2Second();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body1")));
			group.Group_Folio_NameOnCard.sendKeys(CardName);
			test_steps.add("Enter name on card : " + CardName);
			groupLogger.info("Enter name on card : " + CardName);

			group.Group_Folio_CardNumber.sendKeys(CCNumber);
			test_steps.add("Enter credit card number : " + CCNumber);
			groupLogger.info("Enter credit card number : " + CCNumber);

			group.Group_Folio_ExpDate.sendKeys(CCExpiry);
			test_steps.add("Enter Card expiry date : " + CCExpiry);
			groupLogger.info("Enter Card expiry date : " + CCExpiry);

			group.Group_Folio_CVV.sendKeys(CCVCode);
			test_steps.add("Enter card CVV : " + CCVCode);
			groupLogger.info("Enter card CVV : " + CCVCode);

			group.Group_Folio_OK.click();
			test_steps.add("Clicking on Folio Ok");
			groupLogger.info("Clicking on Folio Ok");

			Wait.wait2Second();

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
			Thread.sleep(5000);
			Wait.wait2Second();
			new Select(group.Group_Folio_AuthType).selectByVisibleText(Authorizationtype);
			test_steps.add("Select Authorization type : " + Authorizationtype);
			groupLogger.info("Select Authorization type : " + Authorizationtype);

			if (ChangeAmount.equalsIgnoreCase("Yes")) {
				Wait.WaitForElement(driver, OR.Group_Folio_Amount);
				group.Group_Folio_Amount.clear();
				Wait.wait2Second();
				Wait.explicit_wait_elementToBeClickable(group.Group_Folio_Amount, driver);
				group.Group_Folio_Amount.sendKeys(ChangeAmountValue);
				test_steps.add("Enter Amount to pay " + ChangeAmountValue);
				groupLogger.info("Enter Amount to pay " + ChangeAmountValue);

			} else {
				// System.out.println("Processed complete amount");
				groupLogger.info("Processed complete amount");
			}
			// Wait.wait2Second();
			if (Authorizationtype.equalsIgnoreCase("Capture")) {
				Wait.WaitForElement(driver, OR.Group_Folio_AutoApply);
				group.Group_Folio_AutoApply.click();
				test_steps.add("Clicking on Auto Apply");
				groupLogger.info("Clicking on Auto Apply");

				// Wait.wait2Second();

				Wait.WaitForElement(driver, "//input[@id='dgPaymentDetails_ChkItemSelect_0']");
				if (driver.findElement(By.xpath("//input[@id='dgPaymentDetails_ChkItemSelect_0']")).isSelected()) {
					text = driver.findElement(By.xpath("//a[@id='dgPaymentDetails_lbtnDisplaycaption_0']")).getText();
					// System.out.println("Test : "+text);
				}
				// Wait.wait2Second();

				Wait.WaitForElement(driver, OR.Group_Folio_Process);
				group.Group_Folio_Process.click();
				test_steps.add("Clicking on Folio");
				groupLogger.info("Clicking on Folio");
			}
			try {
				Wait.explicit_wait_visibilityof_webelement(group.AdvanceDepositConfirmPopup, driver);
				group.AdvanceDepositConfirmPopup_Yes.click();
				test_steps.add("Clicking Advance Deposit Yes");
				groupLogger.info("Clicking Advance Deposit Yes");
			} catch (Exception e) {
				test_steps.add("NO Advance Deposit Popup");
				groupLogger.info("NO Advance Deposit Popup");
			}
			// Wait.wait3Second();
			// Wait.explicit_wait_xpath("//table[@id='dgTransactionPayList']/tbody/tr[2]/td[4]");

			Wait.WaitForElement(driver, OR.Group_Folio_PaymentMethod);
			String GetPaymentMethod = new Select(group.Group_Folio_PaymentMethod).getFirstSelectedOption().getText();
			// System.out.println(GetPaymentMethod + " "+GetPaymentMethod);
			if (GetPaymentMethod.equals(PaymentType)) {
				// System.out.println("Paymnet Success");
				groupLogger.info("Payment Success");
			} else {
				// System.out.println("Paymnet Failed");
				groupLogger.info("Payment Failed");
			}
			// Wait.wait3Second();
			Thread.sleep(5000);
			Wait.explicit_wait_visibilityof_webelement_350(group.Group_Folio_Continue, driver);
			// Wait.WaitForElement(driver, OR.Group_Folio_Continue);
			Utility.ScrollToElement(group.Group_Folio_Continue, driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", group.Group_Folio_Continue);

			// group.Group_Folio_Continue.click();
			test_steps.add("Clicking on Continue");
			groupLogger.info("Clicking on Continue");
			Wait.wait3Second();

			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
				Wait.wait3Second();
				if (driver.switchTo().alert() != null) {
					alert.accept();
				}
			} catch (Exception e) {
				System.out.println("No Alert");
			}
			// Wait.wait2Second();

			Wait.WaitForElement(driver, "//a[contains(text(),'Name')]");
			String GetMCCard = driver.findElement(By.xpath("//a[contains(text(),'Name')]")).getText();
			// System.out.println(GetMCCard + " "+GetMCCard);
			if (GetMCCard.contains("Name: test Account #: XXXX5454 Exp.")) {
				// System.out.println("Paymnet Success");
				test_steps.add("Payment Success");
				groupLogger.info("Payment Success");
			}

			driver.switchTo().defaultContent();
			Wait.wait2Second();
			Save(driver, test1, test_steps);
			test_steps.add("Clicking on Save Account");
			groupLogger.info("Clicking on Save Account");
			Wait.wait2Second();
			navigateFolio(driver, test1, test_steps);
			String loc = "//table[@id='MainContent_Folio1_dgLineItems']/tbody/tr/td/table/tbody/tr/td/a[contains(text(),'"
					+ text.trim() + "')]/../../../../../following-sibling::td/img";

			String str = driver.findElement(By.xpath(loc)).getAttribute("title");
			// System.out.println(str);
			test_steps.add("Payment : " + str);
			groupLogger.info("Payment : " + str);

			String balance1 = group.Group_Folio_EndingBalance.getText();
			balance1 = balance1.replace("$", "");

			float bal1 = Float.parseFloat(balance1);

			test_steps.add("After pay Folio balance " + bal1);
			groupLogger.info("After pay Folio balance " + bal1);

			if (bal1 + Float.parseFloat(ChangeAmountValue) == bal) {
				test_steps.add("Cash Payment " + ChangeAmountValue + " is successful");
				groupLogger.info("Cash Payment " + ChangeAmountValue + " is successful");
			} else {
				test_steps.add("Cash Payment " + ChangeAmountValue + " is Fail");
				groupLogger.info("Cash Payment " + ChangeAmountValue + " is Fail");
			}

		} else if (PaymentType.equalsIgnoreCase("Cash")) {
			Wait.wait2Second();

			driver.switchTo().defaultContent();

			driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));

			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.visibilityOf(
					driver.findElement(By.xpath("//span[@id='lblPaymentMethod']/../following-sibling::td/select"))));

			Wait.WaitForElement(driver, "//span[@id='lblPaymentMethod']/../following-sibling::td/select");
			new Select(driver.findElement(By.xpath("//span[@id='lblPaymentMethod']/../following-sibling::td/select")))
					.selectByVisibleText(PaymentType);

			test_steps.add("Select Payment type " + PaymentType);
			groupLogger.info("Select Payment type " + PaymentType);

			// Wait.wait2Second();

			Wait.WaitForElement(driver, OR.Group_Folio_Amount);
			group.Group_Folio_Amount.clear();
			// Wait.wait2Second();

			Wait.WaitForElement(driver, OR.Group_Folio_Amount);
			group.Group_Folio_Amount.sendKeys(ChangeAmountValue);
			test_steps.add("Enter Amount to pay " + ChangeAmountValue);
			groupLogger.info("Enter Amount to pay " + ChangeAmountValue);

			String text = null;
			group.Group_Folio_AutoApply.click();
			test_steps.add("Clicking on Auto Apply");
			groupLogger.info("Clicking on Auto Apply");

			// Wait.wait2Second();

			Wait.WaitForElement(driver, "//input[@id='dgPaymentDetails_ChkItemSelect_0']");
			if (driver.findElement(By.xpath("//input[@id='dgPaymentDetails_ChkItemSelect_0']")).isSelected()) {
				text = driver.findElement(By.xpath("//a[@id='dgPaymentDetails_lbtnDisplaycaption_0']")).getText();
				// System.out.println("Test : "+text);
			}
			// Wait.wait2Second();
			// System.out.println(text);

			Wait.WaitForElement(driver, OR.Group_Folio_Add);
			group.Group_Folio_Add.click();
			test_steps.add("Clicking on Add");
			groupLogger.info("Clicking on Add");

			// Wait.wait3Second();

			Wait.WaitForElement(driver, OR.Group_Folio_Continue);
			Utility.ScrollToElement(group.Group_Folio_Continue, driver);
			group.Group_Folio_Continue.click();
			test_steps.add("Clicking on Continue");
			groupLogger.info("Clicking on Continue");

			Wait.wait2Second();
			driver.switchTo().defaultContent();
			Save(driver, test1, test_steps);
			test_steps.add("Clicking on Save Account");
			groupLogger.info("Clicking on Save Account");

			navigateFolio(driver, test1, test_steps);
			String loc = "//table[@id='MainContent_Folio1_dgLineItems']/tbody/tr/td/table/tbody/tr/td/a[contains(text(),'"
					+ text.trim() + "')]/../../../../../following-sibling::td/img";

			String str = driver.findElement(By.xpath(loc)).getAttribute("title");
			// System.out.println(str);
			test_steps.add("Payment : " + str);
			groupLogger.info("Payment : " + str);

			String balance1 = group.Group_Folio_EndingBalance.getText();
			balance1 = balance1.replace("$", "");
			float bal1 = Float.parseFloat(balance1);
			test_steps.add("After pay Folio balance " + bal1);
			groupLogger.info("After pay Folio balance " + bal1);

			if (bal1 + Float.parseFloat(ChangeAmountValue) == bal) {
				test_steps.add("Cash Payment " + ChangeAmountValue + " is successful");
				groupLogger.info("Cash Payment " + ChangeAmountValue + " is successful");
			} else {
				test_steps.add("Cash Payment " + ChangeAmountValue + " is Fail");
				groupLogger.info("Cash Payment " + ChangeAmountValue + " is Fail");
			}
		}
		return test_steps;
	}

	public ArrayList<String> ageingPaymentAdvanceDeposit(WebDriver driver, ExtentTest test1, String AccountType,
			String PaymentType, String CardName, String CCNumber, String CCExpiry, String CCVCode,
			String Authorizationtype, String ChangeAmount, String ChangeAmountValue, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);

		navigateFolio(driver, test1, test_steps);

		test_steps.add("Clicking on Folio");
		groupLogger.info("Clicking on Folio");

		Wait.WaitForElement(driver, OR.Group_Folio_EndingBalance);
		String balance = group.Group_Folio_EndingBalance.getText();
		balance = balance.replace("$", "");
		float bal = Float.parseFloat(balance);
		test_steps.add("Before Pay Folio balance : " + balance);
		groupLogger.info("Before Pay Folio balance : " + balance);

		navigateFolio(driver, test1, test_steps);
		// Wait.wait2Second();
		Wait.WaitForElement(driver, "//input[@id='MainContent_Folio1_btnPay']");
		driver.findElement(By.xpath("//input[@id='MainContent_Folio1_btnPay']")).click();
		test_steps.add("Clicking on Pay");
		groupLogger.info("Clicking on Pay");

		Wait.wait3Second();
		if (PaymentType.equalsIgnoreCase("MC")) {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
			Wait.WaitForElement(driver, OR.Group_Folio_PaymentMethod);
			new Select(group.Group_Folio_PaymentMethod).selectByVisibleText(PaymentType);
			test_steps.add("Select Payment type : " + PaymentType);
			groupLogger.info("Select Payment type : " + PaymentType);

			Wait.wait2Second();
			Wait.WaitForElement(driver, OR.Group_Folio_CardInfo);
			group.Group_Folio_CardInfo.click();
			test_steps.add("Clicking on Card Info");
			groupLogger.info("Clicking on Card Info");

			Wait.wait2Second();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body1")));
			Wait.WaitForElement(driver, OR.Group_Folio_NameOnCard);
			group.Group_Folio_NameOnCard.sendKeys(CardName);
			test_steps.add("Enter name on card : " + CardName);
			groupLogger.info("Enter name on card : " + CardName);

			group.Group_Folio_CardNumber.sendKeys(CCNumber);
			test_steps.add("Enter credit card number : " + CCNumber);
			groupLogger.info("Enter credit card number : " + CCNumber);

			group.Group_Folio_ExpDate.sendKeys(CCExpiry);
			test_steps.add("Enter Card expiry date : " + CCExpiry);
			groupLogger.info("Enter Card expiry date : " + CCExpiry);

			group.Group_Folio_CVV.sendKeys(CCVCode);
			test_steps.add("Enter card CVV : " + CCVCode);
			groupLogger.info("Enter card CVV : " + CCVCode);

			group.Group_Folio_OK.click();
			test_steps.add("Clicking on Folio Ok");
			groupLogger.info("Clicking on Folio Ok");

			Wait.wait2Second();

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));

			Wait.WaitForElement(driver, OR.Group_Folio_AuthType);
			new Select(group.Group_Folio_AuthType).selectByVisibleText(Authorizationtype);
			test_steps.add("Select Authorization type : " + Authorizationtype);
			groupLogger.info("Select Authorization type : " + Authorizationtype);

			if (ChangeAmount.equalsIgnoreCase("Yes")) {
				Thread.sleep(1000);
				group.Group_Folio_Amount.clear();
				Wait.wait2Second();

				Wait.WaitForElement(driver, OR.Group_Folio_Amount);
				group.Group_Folio_Amount.sendKeys(ChangeAmountValue);
				test_steps.add("Enter Amount to pay " + ChangeAmountValue);
				groupLogger.info("Enter Amount to pay " + ChangeAmountValue);
			} else {
				// System.out.println("Processed complete amount");
				groupLogger.info("Processed complete amount");

			}
			// Wait.wait2Second();
			if (Authorizationtype.equalsIgnoreCase("Capture")) {
				Wait.wait2Second();
				Wait.WaitForElement(driver, OR.Group_Folio_Process);
				group.Group_Folio_Process.click();
				test_steps.add("Clicking on Folio");
				groupLogger.info("Clicking on Folio");
			}
			Wait.wait3Second();
			Wait.WaitForElement(driver, "(//button[@type='button'])[2]");
			driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
			// Wait.wait3Second();

			Wait.WaitForElement(driver, "//table[@id='dgTransactionPayList']/tbody/tr[2]/td[4]");
			Wait.explicit_wait_xpath("//table[@id='dgTransactionPayList']/tbody/tr[2]/td[4]", driver);
			String GetPaymentMethod = new Select(group.Group_Folio_PaymentMethod).getFirstSelectedOption().getText();
			// System.out.println(GetPaymentMethod + " "+GetPaymentMethod);
			if (GetPaymentMethod.equals(PaymentType)) {
				// System.out.println("Paymnet Success");

				groupLogger.info("Payment Success");
			} else {
				// System.out.println("Paymnet Failed");
			}

			Wait.WaitForElement(driver, OR.Group_Folio_Continue);
			Utility.ScrollToElement(group.Group_Folio_Continue, driver);
			group.Group_Folio_Continue.click();
			test_steps.add("Clicking on Continue");
			groupLogger.info("Clicking on Continue");

			Wait.wait3Second();

			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
				Wait.wait3Second();
				if (driver.switchTo().alert() != null) {
					alert.accept();
				}
			} catch (Exception e) {
				System.out.println("No Alert");
			}
			// Wait.wait2Second();
			try {

				Wait.WaitForElement(driver, "//a[contains(text(),'Name')]");
				String GetMCCard = driver.findElement(By.xpath("//a[contains(text(),'Name')]")).getText();
				// System.out.println(GetMCCard + " "+GetMCCard);
				if (GetMCCard.contains("Name: test Account #: XXXX5454 Exp.")) {
					// System.out.println("Paymnet Success");
					test_steps.add("Payment Success");
					groupLogger.info("Payment Success");
				}
			} catch (Exception e) {
				test_steps.add("Card payment failure");
				groupLogger.info("Card payment failure");
				Assert.fail();
			}

			driver.switchTo().defaultContent();
			// Wait.wait2Second();
			Save(driver, test1, test_steps);
			test_steps.add("Clicking on Save Account");
			groupLogger.info("Clicking on Save Account");

			navigateFolio(driver, test1, test_steps);
			Wait.WaitForElement(driver, "//span[@id='MainContent_Folio1_fSummary1_lblAdvanceDepositBalance']");
			String str = driver
					.findElement(By.xpath("//span[@id='MainContent_Folio1_fSummary1_lblAdvanceDepositBalance']"))
					.getText();
			str = str.replace("$", "");
			str = str.trim();
			float a = Float.parseFloat(str);
			test_steps.add("Advanced deposit balance " + str);
			groupLogger.info("Advanced deposit balance " + str);

			if (a == Float.parseFloat(ChangeAmountValue)) {
				test_steps.add("Advanced Deposit Successfull for : " + str);
				groupLogger.info("Advanced Deposit Successfull for : " + str);
			} else {
				test_steps.add("Advanced Deposit not sucessfull for : " + str);
				groupLogger.info("Advanced Deposit not sucessfull for : " + str);
			}

			Wait.WaitForElement(driver, OR.Group_Folio_EndingBalance);

			String balance1 = group.Group_Folio_EndingBalance.getText();
			balance1 = balance1.replace("$", "");
			float bal1 = Float.parseFloat(balance1);
			test_steps.add("After pay Folio balance " + bal1);
			groupLogger.info("After pay Folio balance " + bal1);

			if (bal1 + Float.parseFloat(ChangeAmountValue) == bal) {
				test_steps.add("Cash Payment " + ChangeAmountValue + " is successful");
				groupLogger.info("Cash Payment " + ChangeAmountValue + " is successful");
			} else {
				test_steps.add("Cash Payment " + ChangeAmountValue + " is Fail");
				groupLogger.info("Cash Payment " + ChangeAmountValue + " is Fail");
			}
		} else if (PaymentType.equalsIgnoreCase("Cash")) {
			Wait.wait2Second();

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));

			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.visibilityOf(
					driver.findElement(By.xpath("//span[@id='lblPaymentMethod']/../following-sibling::td/select"))));

			Wait.WaitForElement(driver, "//span[@id='lblPaymentMethod']/../following-sibling::td/select");
			new Select(driver.findElement(By.xpath("//span[@id='lblPaymentMethod']/../following-sibling::td/select")))
					.selectByVisibleText(PaymentType);

			test_steps.add("Select Payment type " + PaymentType);
			groupLogger.info("Select Payment type " + PaymentType);

			// Wait.wait2Second();
			Wait.WaitForElement(driver, OR.Group_Folio_Amount);
			group.Group_Folio_Amount.clear();
			// Wait.wait2Second();
			Wait.WaitForElement(driver, OR.Group_Folio_Amount);
			group.Group_Folio_Amount.sendKeys(ChangeAmountValue);
			test_steps.add("Enter Amount to pay " + ChangeAmountValue);
			groupLogger.info("Enter Amount to pay " + ChangeAmountValue);
			// Wait.wait2Second();

			Wait.WaitForElement(driver, OR.Group_Folio_Add);
			group.Group_Folio_Add.click();
			test_steps.add("Clicking on Add");
			groupLogger.info("Clicking on Add");

			// Wait.wait3Second();

			Wait.WaitForElement(driver, "(//button[@type='button'])[2]");
			driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
			// Wait.wait3Second();

			Wait.WaitForElement(driver, OR.Group_Folio_Continue);
			group.Group_Folio_Continue.click();
			test_steps.add("Clicking on Continue");
			groupLogger.info("Clicking on Continue");
			Wait.wait2Second();
			driver.switchTo().defaultContent();
			Save(driver, test1, test_steps);
			test_steps.add("Clicking on Save Account");
			groupLogger.info("Clicking on Save Account");
			navigateFolio(driver, test1, test_steps);

			Wait.WaitForElement(driver, "//span[@id='MainContent_Folio1_fSummary1_lblAdvanceDepositBalance']");
			String str = driver
					.findElement(By.xpath("//span[@id='MainContent_Folio1_fSummary1_lblAdvanceDepositBalance']"))
					.getText();
			str = str.replace("$", "");
			str = str.trim();
			float a = Float.parseFloat(str);
			test_steps.add("Advanced deposit balance " + str);
			groupLogger.info("Advanced deposit balance " + str);

			if (a == Float.parseFloat(ChangeAmountValue)) {
				test_steps.add("Advanced Deposit Successfull for : " + str);
				groupLogger.info("Advanced Deposit Successfull for : " + str);
			} else {
				test_steps.add("Advanced Deposit not successful for : " + str);
				groupLogger.info("Advanced Deposit not successful for : " + str);
			}

			String balance1 = group.Group_Folio_EndingBalance.getText();
			balance1 = balance1.replace("$", "");
			float bal1 = Float.parseFloat(balance1);
			test_steps.add("After pay Folio balance " + bal1);
			groupLogger.info("After pay Folio balance " + bal1);

			if (bal1 + Float.parseFloat(ChangeAmountValue) == bal) {
				test_steps.add("Cash Payment " + ChangeAmountValue + " is successful");
				groupLogger.info("Cash Payment " + ChangeAmountValue + " is successful");
			} else {
				test_steps.add("Cash Payment " + ChangeAmountValue + " is Fail");
				groupLogger.info("Cash Payment " + ChangeAmountValue + " is Fail");
			}
		}

		// Wait.wait2Second();

		Wait.WaitForElement(driver, OR.Group_Folio_EndingBalance);
		String balance1 = group.Group_Folio_EndingBalance.getText();
		balance1 = balance1.replace("$", "");
		float bal1 = Float.parseFloat(balance1);
		test_steps.add("After pay Folio balance " + bal1);
		groupLogger.info("After  Advance deposit payFolio balance " + bal1);

		String str = driver.findElement(By.xpath("//span[@id='MainContent_Folio1_fSummary1_lblAdvanceDepositBalance']"))
				.getText();
		str = str.replace("$", "");
		str = str.trim();
		float a = Float.parseFloat(str);
		test_steps.add("Advanced deposit balance " + str);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", group.Group_Folio_AdvanceDeposit);
		group.Group_Folio_AdvanceDeposit.click();

		test_steps.add("Click Group_Folio_AdvanceDeposit");
		groupLogger.info("Click Group_Folio_AdvanceDeposit");
		Wait.wait2Second();

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		// Wait.wait3Second();

		Wait.explicit_wait_visibilityof_webelement(group.Group_Folio_AdvanceDepositAutoApply, driver);
		// Wait.WaitForElement(driver, OR.Group_Folio_AdvanceDepositAutoApply);
		// Wait.explicit_wait_xpath(OR.Group_Folio_AdvanceDepositAutoApply);
		group.Group_Folio_AdvanceDepositAutoApply.click();
		test_steps.add("Click Group_Folio_AdvanceDepositAutoApply");
		groupLogger.info("Click Group_Folio_AdvanceDepositAutoApply");
		// Wait.wait2Second();

		// Wait.WaitForElement(driver, OR.Group_Folio_AdvanceDepositAdd);
		// Wait.explicit_wait_xpath(OR.Group_Folio_AdvanceDepositAdd);
		Wait.explicit_wait_visibilityof_webelement(group.Group_Folio_AdvanceDepositAdd, driver);
		group.Group_Folio_AdvanceDepositAdd.click();
		test_steps.add("Click Group_Folio_AdvanceDepositAdd");
		groupLogger.info("Click Group_Folio_AdvanceDepositAdd");
		try {
			Wait.explicit_wait_visibilityof_webelement(group.AppplyAdvanceDeposit_Dialog, driver);
			test_steps.add("Apply Advance Deposit Dialog Appear");
			groupLogger.info("Apply Advance Deposit Dialog Appear");
			Wait.explicit_wait_visibilityof_webelement(group.AppplyAdvanceDeposit_DialogContinue, driver);
			group.AppplyAdvanceDeposit_DialogContinue.click();
			test_steps.add("Click Apply Advance Deposit Dialog Continue");
			groupLogger.info("Click Apply Advance Deposit Dialog Continue");
		} catch (Exception e) {
			test_steps.add("Apply Advance Deposit Dialog not Appear");
			groupLogger.info("Apply Advance Deposit Dialog not Appear");
		}

		// Wait.explicit_wait_xpath(OR.Group_Folio_AdvanceDepositContinue);
		// Wait.wait5Second();

		// Wait.WaitForElement(driver, OR.Group_Folio_AdvanceDepositContinue);
		Wait.explicit_wait_visibilityof_webelement(group.Group_Folio_AdvanceDepositContinue, driver);
		Utility.ScrollToElement(group.Group_Folio_AdvanceDepositContinue, driver);
		jse.executeScript("arguments[0].click();", group.Group_Folio_AdvanceDepositContinue);

		test_steps.add("Click Group_Folio_AdvanceDepositContinue");
		groupLogger.info("Click Group_Folio_AdvanceDepositContinue");
		// group.Group_Folio_AdvanceDepositContinue.click();
		Wait.wait2Second();
		driver.switchTo().defaultContent();
		Save(driver, test1, test_steps);
		test_steps.add("Clicking on Save Account");
		navigateFolio(driver, test1, test_steps);
		Wait.wait3Second();

		if (driver.findElements(By.xpath("//span[@id='MainContent_Folio1_fSummary1_lblAdvanceDepositBalance']"))
				.size() <= 0) {

			test_steps.add("Payment via Advance deposit link is successful");
			groupLogger.info("Payment via Advance deposit link is successful");
		} else {
			test_steps.add("Payment via Advance deposit link is not successful");
			groupLogger.info("Payment via Advance deposit link is not successful");
		}
		return test_steps;
	}

	public String ClickAccounts(WebDriver driver) {
		Elements_Groups elements_Groups = new Elements_Groups(driver);
		Wait.WaitForElement(driver, OR.GroupsAccounts_Title);
		assertEquals(elements_Groups.GroupsAccounts_Title.getText(), "Accounts", "Accounts does not find");
		elements_Groups.GroupsAccounts_Link.click();
		groupLogger.info("Click Account");
		try {
			String GroupName = elements_Groups.GroupName.getAttribute("value");
			String GroupAccountNumber = elements_Groups.GroupAccountNumber.getAttribute("value");
			elements_Groups.GroupNewResButton.click();
			groupLogger.info("Click New Reservation");
			return GroupName + ":" + GroupAccountNumber;
		} catch (Exception e) {
			driver.navigate().back();
			Wait.explicit_wait_visibilityof_webelement(elements_Groups.GroupsAccounts_Link, driver);
			elements_Groups.GroupsAccounts_Link.click();
			groupLogger.info("Again Open First Account");
			String GroupName = elements_Groups.GroupName.getAttribute("value");
			String GroupAccountNumber = elements_Groups.GroupAccountNumber.getAttribute("value");
			elements_Groups.GroupNewResButton.click();
			groupLogger.info("Click New Reservation");
			return GroupName + ":" + GroupAccountNumber;
		}

	}

	public void Search_Account(WebDriver driver, String AccountName, String AccountNumber) throws InterruptedException {
		Elements_Groups elements_Groups = new Elements_Groups(driver);

		elements_Groups.Resgroups_AccountName.clear();
		elements_Groups.Resgroups_AccountName.sendKeys(AccountName);
		elements_Groups.Resgroups_AccountNumber.clear();
		elements_Groups.Resgroups_AccountNumber.sendKeys(AccountNumber);
		elements_Groups.Resgroups_GoButton.click();
		Wait.wait2Second();
		assertTrue(elements_Groups.Resgroups_ReservToSelect.isDisplayed(), "Searched group account isn't diplayed");
		Wait.wait2Second();
		elements_Groups.Resgroups_ReservToSelect.click();
		Wait.wait2Second();
		assertTrue(elements_Groups.Resgroups_AccountDetailsPage.isDisplayed(),
				"Account Detail Page isn't open and tabs aren't displayed");

	}

	public void SearchAccount(WebDriver driver, String AccountName, String AccountNumber) throws InterruptedException {
		Elements_Groups elements_Groups = new Elements_Groups(driver);

		// elements_Groups.Resgroups_AccountName.clear();
		// elements_Groups.Resgroups_AccountName.sendKeys(AccountName);
		// elements_Groups.Resgroups_AccountNumber.clear();
		// elements_Groups.Resgroups_AccountNumber.sendKeys(AccountNumber);
		elements_Groups.Resgroups_GoButton.click();
		Wait.wait2Second();
		assertTrue(elements_Groups.Resgroups_ReservToSelect.isDisplayed(), "Searched group account isn't diplayed");
		Wait.wait2Second();
		elements_Groups.Resgroups_ReservToSelect.click();
		Wait.wait2Second();
		assertTrue(elements_Groups.Resgroups_AccountDetailsPage.isDisplayed(),
				"Account Detail Page isn't open and tabs aren't displayed");

	}

	public void SearchAccNumber(WebDriver driver, String AccountNumber) throws InterruptedException {
		Elements_Groups elements_Groups = new Elements_Groups(driver);

		elements_Groups.Resgroups_AccountNumber.clear();
		elements_Groups.Resgroups_AccountNumber.sendKeys(AccountNumber);
		elements_Groups.Resgroups_GoButton.click();
		Wait.wait2Second();
		assertTrue(elements_Groups.Resgroups_ReservToSelect.isDisplayed(), "Searched group account isn't diplayed");
		Wait.wait2Second();
		elements_Groups.Resgroups_ReservToSelect.click();
		Wait.wait2Second();
		assertTrue(elements_Groups.Resgroups_AccountDetailsPage.isDisplayed(),
				"Account Detail Page isn't open and tabs aren't displayed");

	}

	int Lineitemsnewitemindex = 0;
	public static int TableRowCountBefore = 0;
	public static int TableRowCountAfter = 0;

	public ArrayList<String> AddLineItems(WebDriver driver, ExtentTest test1, ArrayList<String> test_steps)
			throws InterruptedException {

		Lineitemsnewitemindex = GetTableRowCount(driver);
		TableRowCountBefore = Lineitemsnewitemindex;
		Elements_Groups group = new Elements_Groups(driver);
		Wait.WaitForElement(driver, OR.Group_Folio_Add_LineItem);
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_Add_LineItem, driver);
		group.Group_Folio_Add_LineItem.click();
		test_steps.add("Click on Add Line item");
		groupLogger.info("Click on Add Line item");
		// Select Category
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver
				.findElement(By.id("MainContent_Folio1_dgLineItems_drpLedgeraccountname_" + Lineitemsnewitemindex)));
		driver.findElement(By.id("MainContent_Folio1_dgLineItems_drpLedgeraccountname_" + Lineitemsnewitemindex))
				.click();
		WebElement element = driver.findElement(By.xpath("//*[@id='MainContent_Folio1_dgLineItems_drpLedgeraccountname_"
				+ Lineitemsnewitemindex + "']/option[9]"));
		Wait.explicit_wait_visibilityof_webelement(element, driver);
		element.click();

		driver.findElement(By.id("MainContent_Folio1_dgLineItems_txtAmount_" + Lineitemsnewitemindex)).sendKeys("100");
		Commit(driver, test1);
		Save(driver, test1, test_steps);
		return test_steps;

	}

	public int GetTableRowCount(WebDriver driver) {

		int i = 0;
		WebElement table = driver.findElement(By.xpath("//*[@id='MainContent_Folio1_dgLineItems']"));
		List<WebElement> listOfRows1 = table.findElements(By.className("dgItem"));
		List<WebElement> listOfRows2 = table.findElements(By.className("dgItemAlt"));
		i = listOfRows2.size() + listOfRows1.size();
		return i;
	}

	public void navigateRoomBlock(WebDriver driver, ExtentTest test1) {
		Elements_Groups group = new Elements_Groups(driver);
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOf(group.RoomBlocksTab));
		group.RoomBlocksTab.click();
		groupLogger.info("Click on RoomBlocksTab");

	}

	public void SelectFirstAccount(WebDriver driver) throws InterruptedException {
		Elements_Groups group = new Elements_Groups(driver);
		Wait.explicit_wait_visibilityof_webelement(group.GroupAccount_FirstActiveElement, driver);
		Utility.ScrollToElement(group.GroupAccount_FirstActiveElement, driver);
		group.GroupAccount_FirstActiveElement.click();
		Wait.explicit_wait_visibilityof_webelement_120(group.Resgroups_AccountDetailsPage, driver);
		assertTrue(group.Resgroups_AccountDetailsPage.isDisplayed(), "Failed: Account Details Page not opened");

	}

	public void click_GroupsReservationTab(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Groups group = new Elements_Groups(driver);
		Wait.WaitForElement(driver, OR.Groups_ReservationsTab);
		group.Groups_ReservationsTab.click();
		test_steps.add("Click on New Reservation Tab in Group");
		groupLogger.info("Click on New Reservation Tab in Group");
	}

	public void click_GroupNewReservation(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Groups group = new Elements_Groups(driver);
		Wait.WaitForElement(driver, OR.Verify_New_Reservation_Enabled);
		group.Verify_New_Reservation_Enabled.click();
		test_steps.add("Click on New Reservation in Group");
		groupLogger.info("Click on New Reservation in Group");
	}

	public ArrayList<String> enterAccountNo(WebDriver driver, String accNo) {
		ArrayList<String> test_steps = new ArrayList<>();

		Elements_Groups group = new Elements_Groups(driver);
		Wait.explicit_wait_visibilityof_webelement_120(group.GroupAccountNumber, driver);
		group.GroupAccountNumber.clear();
		group.GroupAccountNumber.sendKeys(accNo);

		test_steps.add("Entered Account Number : " + group.GroupAccountNumber.getAttribute("value"));
		groupLogger.info("Entered Account Number : " + group.GroupAccountNumber.getAttribute("value"));

		return test_steps;
	}

	public ArrayList<String> ClickNewBlock(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_xpath(OR.Click_New_Block_button, driver);
		Wait.explicit_wait_visibilityof_webelement_150(group.Click_New_Block_button, driver);
		Utility.ScrollToElement(group.Click_New_Block_button, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_New_Block_button, driver);
		group.Click_New_Block_button.click();
		test_steps.add("Click New Block Button");
		groupLogger.info("Click New Block Button");
		return test_steps;
	}

	public ArrayList<String> EnterBlockName(WebDriver driver, String blockName) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_xpath(OR.Enter_Block_Name, driver);
		Wait.explicit_wait_visibilityof_webelement_600(group.Enter_Block_Name, driver);
		group.Enter_Block_Name.sendKeys(blockName);
		test_steps.add("Entered New Block Name : " + blockName);
		groupLogger.info("Entered New Block Name : " + blockName);
		return test_steps;
	}

	public ArrayList<String> ClickOkay_CreateNewBlock(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_xpath(OR.Click_Ok, driver);
		Wait.explicit_wait_visibilityof_webelement_150(group.Click_Ok, driver);
		Utility.ScrollToElement(group.Click_Ok, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Ok, driver);
		group.Click_Ok.click();
		test_steps.add("OK Button Clicked");
		groupLogger.info("OK Button Clicked");
		return test_steps;
	}

	public ArrayList<String> SelectArrivalDepartureDates(WebDriver driver, String ArrivalDate, String DepartDate)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);

		Wait.explicit_wait_xpath(OR.Select_Arrival_Date_Groups, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Select_Arrival_Date_Groups, driver);
		Wait.explicit_wait_elementToBeClickable(group.Select_Arrival_Date_Groups, driver);
		group.Select_Arrival_Date_Groups.click();
		test_steps.add("Select Arrival Date");
		groupLogger.info("Select Arrival Date");

		// String todayPath = "//div[1]/table/tfoot/tr[1]/th[text()='Today']";
		Wait.explicit_wait_xpath(OR.Click_Today_Arrival_Groups, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Click_Today_Arrival_Groups, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Today_Arrival_Groups, driver);
		group.Click_Today_Arrival_Groups.click();
		test_steps.add("Today Clicked as Arrival Date");
		groupLogger.info("Today Clicked as Arrival Date");
		assertEquals(group.GetStartDate.getAttribute("value"), ArrivalDate, "Failed: Arrival Date not Selected");
		test_steps.add("Selected Arrival Date '" + ArrivalDate + "'");
		groupLogger.info("Select Arrival Date '" + ArrivalDate + "'");
		assertEquals(group.GetEndDate.getAttribute("value"), DepartDate, "Failed: Depart Date not Selected");
		test_steps.add("Selected Depart Date '" + DepartDate + "'");
		groupLogger.info("Select Depart Date '" + DepartDate + "'");
		return test_steps;
	}

	public ArrayList<String> SelectRatePlan(WebDriver driver, String RatePlan) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_visibilityof_webelement(group.GroupRatePlan, driver);
		Wait.explicit_wait_elementToBeClickable(group.GroupRatePlan, driver);
		Utility.ScrollToElement(group.GroupRatePlan, driver);
		new Select(group.GroupRatePlan).selectByVisibleText(RatePlan);
		test_steps.add("Select Rate Plan '" + RatePlan + "'");
		groupLogger.info("Select Rate Plan '" + RatePlan + "'");
		assertEquals(new Select(group.GroupRatePlan).getFirstSelectedOption().getText(), RatePlan,
				"Failed: Rate Plan not Selected");
		return test_steps;
	}

	public ArrayList<String> SelectAdults(WebDriver driver, String Adults) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_visibilityof_webelement(group.GroupAdults, driver);
		Wait.explicit_wait_elementToBeClickable(group.GroupAdults, driver);
		Utility.ScrollToElement(group.GroupAdults, driver);
		String PreSelectedValue = group.GroupAdults.getAttribute("value");
		int diff = Integer.parseInt(Adults) - Integer.parseInt(PreSelectedValue);
		WebElement button = group.AddGroupAdults;
		if (diff != 0) {
			if (diff < 0) {
				button = group.SubtractGroupAdults;
			}
			Wait.explicit_wait_elementToBeClickable(button, driver);
			Utility.ScrollToElement(button, driver);
			for (int i = 0; i < Math.abs(diff); i++) {
				button.click();
				groupLogger.info("Click Button");
			}
		}
		test_steps.add("Select Adults '" + Adults + "'");
		groupLogger.info("Select Adults '" + Adults + "'");
		assertEquals(group.GroupAdults.getAttribute("value"), Adults, "Failed: Adults not Selected");
		return test_steps;
	}

	public ArrayList<String> SelectChilds(WebDriver driver, String Childs) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_visibilityof_webelement(group.GroupChilds, driver);
		Wait.explicit_wait_elementToBeClickable(group.GroupChilds, driver);
		Utility.ScrollToElement(group.GroupChilds, driver);
		String PreSelectedValue = group.GroupChilds.getAttribute("value");
		int diff = Integer.parseInt(Childs) - Integer.parseInt(PreSelectedValue);
		WebElement button = group.AddGroupChilds;
		if (diff != 0) {
			if (diff < 0) {
				button = group.SubtractGroupChilds;
			}
			Wait.explicit_wait_elementToBeClickable(button, driver);
			Utility.ScrollToElement(button, driver);
			for (int i = 0; i < Math.abs(diff); i++) {
				button.click();
				groupLogger.info("Click Button");
			}
		}
		test_steps.add("Select Childs '" + Childs + "'");
		groupLogger.info("Select Childs '" + Childs + "'");
		assertEquals(group.GroupChilds.getAttribute("value"), Childs, "Failed: Childs not Selected");
		return test_steps;
	}

	public ArrayList<String> EnterNights(WebDriver driver, String roomPerNight) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);

		Wait.explicit_wait_xpath(OR.Enter_No_of_Nigts, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Enter_No_of_Nigts, driver);
		Wait.explicit_wait_elementToBeClickable(group.Enter_No_of_Nigts, driver);
		group.Enter_No_of_Nigts.click();
		group.Enter_No_of_Nigts.sendKeys(roomPerNight);
		test_steps.add("Entered Room Per Night : " + roomPerNight);
		groupLogger.info("Entered Room Per Night : " + roomPerNight);
		return test_steps;
	}

	public ArrayList<String> ClickSearchGroup(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_xpath(OR.Click_Search_Group, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Click_Search_Group, driver);
		Utility.ScrollToElement(group.Click_Search_Group, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Search_Group, driver);
		group.Click_Search_Group.click();
		test_steps.add("Search Group Button Clicked");
		groupLogger.info("Search Group Button Clicked");
		return test_steps;
	}

	public ArrayList<String> VerifyRoomClass(WebDriver driver, String RoomClass, String BaseAmount)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_visibilityof_webelement(AdvGroup.GetRoomclasses.get(0), driver);
		List<WebElement> GetRoomclassNames = AdvGroup.GetRoomclasses;
		groupLogger.info("GetRoomclassNames" + GetRoomclassNames.size());
		boolean found = false;
		for (int i = 0; i < GetRoomclassNames.size(); i++) {
			if (GetRoomclassNames.get(i).getText().equals(RoomClass)) {
				test_steps.add("Verified Room Class '" + RoomClass + "' is Displayed");
				groupLogger.info("Verified Room Class '" + RoomClass + "' is Displayed");
				int index = i + 1;
				String Amountxpath = "//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index
						+ "]/td[4]/input";
				groupLogger.info(Amountxpath);
				WebElement AmountPerNight = driver.findElement(By.xpath(Amountxpath));
				Utility.ScrollToElement(AmountPerNight, driver);
				groupLogger.info("Text : " + AmountPerNight.getAttribute("value"));
				assertEquals(AmountPerNight.getAttribute("value"), BaseAmount, "Failed: Avg rate missmatched");
				test_steps.add("Verified Room Class  Avg Rate is equals to Base amount '$ " + BaseAmount
						+ "' set in the Rate");
				groupLogger.info("Verified Room Class  Avg Rate is equals to Base amount '$ " + BaseAmount
						+ "' set in the Rate");
				found = true;
			}
		}
		assertTrue(found, "Failed Room Class not found");
		return test_steps;
	}

	public ArrayList<String> Search_Account(WebDriver driver, String AccountName, String AccountNumber, boolean isFound,
			boolean open) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups elements_Groups = new Elements_Groups(driver);
		test_steps.add("Search Criteria");
		groupLogger.info("Search Criteria");
		elements_Groups.Resgroups_AccountName.clear();
		elements_Groups.Resgroups_AccountName.sendKeys(AccountName);
		test_steps.add("Account Name : " + AccountName);
		groupLogger.info("Account Name : " + AccountName);

		elements_Groups.Resgroups_AccountNumber.clear();
		elements_Groups.Resgroups_AccountNumber.sendKeys(AccountNumber);
		test_steps.add("Account No : " + AccountNumber);
		groupLogger.info("Account NO : " + AccountNumber);

		elements_Groups.Resgroups_GoButton.click();
		test_steps.add("GO Button Clicked");
		groupLogger.info("GO Button Clicked");

		Wait.wait2Second();
		if (isFound) {
			try {
				if (elements_Groups.Resgroups_ReservToSelect.isDisplayed()) {
					assertTrue(elements_Groups.Resgroups_ReservToSelect.isDisplayed(),
							"Searched group account isn't diplayed");
					test_steps.add(AccountName + " Account Found");
					groupLogger.info(AccountName + " Account Found");
					if (open) {
						Wait.wait2Second();
						elements_Groups.Resgroups_ReservToSelect.click();
						Wait.wait2Second();
						assertTrue(elements_Groups.Resgroups_AccountDetailsPage.isDisplayed(),
								"Account Detail Page isn't open and tabs aren't displayed");
					}
				} else {
					test_steps.add(AccountName + " Account Not Found");
					groupLogger.info(AccountName + " Account Not Found");
				}
			} catch (Exception e) {
				try {
					if (driver.findElement(By.id("MainContent_lblMessage")).isDisplayed()) {
						test_steps.add(AccountName + " Account Not Found");
						groupLogger.info(AccountName + " Account Not Found");
					}
				} catch (Exception exception) {
					System.out.println("Message not appaer");
				}
			}
		}
		return test_steps;
	}

	public ArrayList<String> deleteSearchedAccount(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		String selectPath = "//*[@id='MainContent_dgAccountList']/tbody/tr[2]/td[1]//input";
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(selectPath)), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(selectPath)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(selectPath)), driver);
		driver.findElement(By.xpath(selectPath)).click();
		test_steps.add("Successfully Selected Searched Account CheckBox");
		groupLogger.info("Successfully Selected Searched Account CheckBox");

		Wait.explicit_wait_visibilityof_webelement(group.DeleteButton, driver);
		Utility.ScrollToElement(group.DeleteButton, driver);
		Wait.explicit_wait_elementToBeClickable(group.DeleteButton, driver);
		group.DeleteButton.click();
		test_steps.add("Delete Button Clicked");
		groupLogger.info("Delete Button Clicked");

		Wait.wait2Second();
		driver.switchTo().alert().accept();
		Wait.wait5Second();
		try {
			Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.id("MainContent_lblMessage")), driver);
			test_steps.add("Successfully Deleted ");
			groupLogger.info("Successfully Deleted");
		} catch (Exception e) {
			System.out.println("Msg not appeared");
		}
		return test_steps;
	}

	public ArrayList<String> AddLineItems(WebDriver driver, String LineCategory, String LineAmount, String LineNO)
			throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<>();

		Elements_Groups group = new Elements_Groups(driver);
		// Click Add Line Item
		Wait.WaitForElement(driver, OR.Group_Folio_Add_LineItem);
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_Add_LineItem, driver);
		group.Group_Folio_Add_LineItem.click();
		test_steps.add("Click on Add Line item");
		groupLogger.info("Click on Add Line item");

		// Select Category
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();",
				driver.findElement(By.id("MainContent_Folio1_dgLineItems_drpLedgeraccountname_" + LineNO)));

		Select cat = new Select(
				driver.findElement(By.id("MainContent_Folio1_dgLineItems_drpLedgeraccountname_" + LineNO)));
		cat.selectByVisibleText(LineCategory);

		test_steps.add("Selcted Category : " + LineCategory);
		groupLogger.info("Selcted Category : " + LineCategory);

		// Enter Amount
		driver.findElement(By.id("MainContent_Folio1_dgLineItems_txtAmount_" + LineNO)).sendKeys(LineAmount);

		test_steps.add("Entered Folio Line Item Amount : " + LineAmount);
		groupLogger.info("Entered Folio Line Item Amount : " + LineAmount);
		return test_steps;

	}

	public ArrayList<String> newReservation(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		Wait.explicit_wait_visibilityof_webelement_120(group.GroupNewResButton, driver);
		Utility.ScrollToElement(group.GroupNewResButton, driver);
		Wait.explicit_wait_elementToBeClickable(group.GroupNewResButton, driver);
		group.GroupNewResButton.click();
		groupLogger.info("Click New Reservation");
		test_steps.add("Click New Reservation");
		Wait.wait10Second();
		return test_steps;
	}

	public ArrayList<String> advanceDepositBalAutoApply(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Group_Folio_AdvanceDeposit, driver);
		Utility.ScrollToElement(group.Group_Folio_AdvanceDeposit, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_AdvanceDeposit, driver);
		group.Group_Folio_AdvanceDeposit.click();
		groupLogger.info("Click Advance Deposit Balance");
		test_steps.add("Click Advance Deposit Balance");

		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		// Wait.wait3Second();

		Wait.explicit_wait_visibilityof_webelement(group.Group_Folio_AdvanceDepositAutoApply, driver);
		// Wait.WaitForElement(driver, OR.Group_Folio_AdvanceDepositAutoApply);
		// Wait.explicit_wait_xpath(OR.Group_Folio_AdvanceDepositAutoApply);

		group.Group_Folio_AdvanceDepositAutoApply.click();
		test_steps.add("Click Group_Folio_AdvanceDepositAutoApply");
		groupLogger.info("Click Group_Folio_AdvanceDepositAutoApply");
		// Wait.wait2Second();

		// Wait.WaitForElement(driver, OR.Group_Folio_AdvanceDepositAdd);
		// Wait.explicit_wait_xpath(OR.Group_Folio_AdvanceDepositAdd);
		Wait.explicit_wait_visibilityof_webelement(group.Group_Folio_AdvanceDepositAdd, driver);
		group.Group_Folio_AdvanceDepositAdd.click();
		test_steps.add("Click Group Folio AdvanceDeposit Add");
		groupLogger.info("Click Group Folio AdvanceDeposit Add");
		try {
			Wait.explicit_wait_visibilityof_webelement(
					driver.findElement(By.xpath("//div[@aria-describedby='divadvanceDepostBalance']")), driver);
			test_steps.add("Apply Advance Deposit Dialog Appear");
			groupLogger.info("Apply Advance Deposit Dialog Appear");
			Wait.explicit_wait_visibilityof_webelement(
					driver.findElement(
							By.xpath("//div[contains(@class,'ui-dialog-buttonpane')]//button[text()='Continue']")),
					driver);
			driver.findElement(By.xpath("//div[contains(@class,'ui-dialog-buttonpane')]//button[text()='Continue']"))
					.click();
			test_steps.add("Click Apply Advance Deposit Dialog Continue");
			groupLogger.info("Click Apply Advance Deposit Dialog Continue");
		} catch (Exception e) {
			test_steps.add("Apply Advance Deposit Dialog not Appear");
			groupLogger.info("Apply Advance Deposit Dialog not Appear");
		}

		// Wait.explicit_wait_xpath(OR.Group_Folio_AdvanceDepositContinue);
		// Wait.wait5Second();

		// Wait.WaitForElement(driver, OR.Group_Folio_AdvanceDepositContinue);
		Wait.explicit_wait_visibilityof_webelement(group.Group_Folio_AdvanceDepositContinue, driver);
		Utility.ScrollToElement(group.Group_Folio_AdvanceDepositContinue, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_AdvanceDepositContinue, driver);
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();",
		// group.Group_Folio_AdvanceDepositContinue);
		group.Group_Folio_AdvanceDepositContinue.click();
		test_steps.add("Click Group Folio AdvanceDeposit Continue");
		groupLogger.info("Click Group Folio AdvanceDeposit Continue");

		Wait.wait2Second();
		driver.switchTo().defaultContent();
		return test_steps;
	}

	public String getAdvanceDepositBalance(WebDriver driver) throws InterruptedException {
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(
				driver.findElement(By.id("MainContent_Folio1_fSummary1_lblAdvanceDepositBalance")), driver);

		return driver.findElement(By.id("MainContent_Folio1_fSummary1_lblAdvanceDepositBalance")).getText();
	}

	public ArrayList<String> verifyFullyPaidIcon(WebDriver driver, String lineCategory, String env) {
		ArrayList<String> test_steps = new ArrayList<>();

		String fullyPaidIconPath = "//tbody/tr/td/span[contains(text(),'" + lineCategory
				+ "')]//parent::td/../td/img[@title='Fully Paid']"; // "MainContent_Folio1_dgLineItems_imgItemStatus_"
																	// + LineNo;
		Wait.explicit_wait_xpath(fullyPaidIconPath, driver);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(fullyPaidIconPath)), driver);
		String src = driver.findElement(By.xpath(fullyPaidIconPath)).getAttribute("src");
		String title = driver.findElement(By.xpath(fullyPaidIconPath)).getAttribute("title");

		assertTrue(src.contains("Folio_Images/fully-paid.png"), "Failed Icon Source not matched");
		assertEquals(title, "Fully Paid", "Failed to Match Title");
		test_steps.add("Successfully Verified Icon Source and Title : Fully Paid");
		groupLogger.info("Successfully Verified Icon Source and Title : Fully Paid");

		return test_steps;
	}

	public ArrayList<String> changeActiveStatus(WebDriver driver, String status) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String statusPath = "//*[@id='MainContent_drpActive']";
		Wait.explicit_wait_xpath(statusPath, driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(statusPath)), driver);
		new Select(driver.findElement(By.xpath(statusPath))).selectByVisibleText(status);
		test_steps.add("Successfully Status Changed to : " + status);
		groupLogger.info("Successfully Status Changed to : " + status);
		Wait.wait2Second();
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			System.out.println("No alert found");
		}
		Wait.wait2Second();
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			System.out.println("No alert found");
		}
		Wait.wait2Second();
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			System.out.println("No alert found");
		}
		return test_steps;
	}

	public ArrayList<String> rollBack_Void_LineItem(WebDriver driver, String LineNo) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		String text = "//input[@id='MainContent_Folio1_dgLineItems_chkSelectFolioItem_" + LineNo
				+ "']/../../td[7]/table/tbody/tr/td/a";
		Wait.WaitForElement(driver, text);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(text)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(text)), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(text)), driver);
		driver.findElement(By.xpath(text)).click();
		test_steps.add("Clicking on Item link");
		groupLogger.info("Clicking on Item link");
		loadingImage(driver);
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		loadingImage(driver);

		String rollback = "//input[@id='btnRollback']";
		Wait.WaitForElement(driver, rollback);
		Utility.ScrollToElement(driver.findElement(By.xpath(rollback)), driver);
		driver.findElement(By.xpath(rollback)).click();
		test_steps.add("Clicking on rollback");
		groupLogger.info("Clicking on rollback");

		String notesPath = "//*[@id='txtNotes']";
		Wait.WaitForElement(driver, notesPath);
		try {
			loadingImage(driver);
			Wait.explicit_wait_visibilityof_webelement_200(driver.findElement(By.xpath(notesPath)), driver);
			Utility.ScrollToElement(driver.findElement(By.xpath(notesPath)), driver);
			Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(notesPath)), driver);
			driver.findElement(By.xpath(notesPath)).click();
			driver.findElement(By.xpath(notesPath)).sendKeys("Deleting");
			test_steps.add("Entered Notes");
			groupLogger.info("Entered Notes");
		} catch (Exception e) {
			loadingImage(driver);
			Wait.explicit_wait_visibilityof_webelement_200(driver.findElement(By.xpath(notesPath)), driver);
			Utility.ScrollToElement(driver.findElement(By.xpath(notesPath)), driver);
			Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(notesPath)), driver);
			driver.findElement(By.xpath(notesPath)).click();
			driver.findElement(By.xpath(notesPath)).sendKeys("Deleting");
			test_steps.add("Entered Notes");
			groupLogger.info("Entered Notes");
		}

		String voidBtnPath = "//input[@id='btnVoid']";
		Wait.WaitForElement(driver, voidBtnPath);
		Utility.ScrollToElement(driver.findElement(By.xpath(voidBtnPath)), driver);
		driver.findElement(By.xpath(voidBtnPath)).click();
		test_steps.add("Clicking on void Button");
		groupLogger.info("Clicking on void Button");

		String continuee = "//input[@value='Continue']";

		try {

			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(continuee)), driver);
			Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(continuee)), driver);
			Utility.ScrollToElement(driver.findElement(By.xpath(continuee)), driver);
			driver.findElement(By.xpath(continuee)).click();
		} catch (Exception e) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", driver.findElement(By.xpath(continuee)));
		}

		test_steps.add("Clicking on continue");
		groupLogger.info("Clicking on continue");
		loadingImage(driver);
		driver.switchTo().defaultContent();
		loadingImage(driver);
		return test_steps;
	}

	public ArrayList<String> createNewBlock(WebDriver driver, String blockName, String roomPerNight)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_xpath(OR.Click_New_Block_button, driver);
		Wait.explicit_wait_visibilityof_webelement_150(group.Click_New_Block_button, driver);
		Utility.ScrollToElement(group.Click_New_Block_button, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_New_Block_button, driver);
		group.Click_New_Block_button.click();
		test_steps.add("New Block Button Clicked");
		groupLogger.info("New Block Button Clicked");

		Wait.explicit_wait_xpath(OR.Enter_Block_Name, driver);
		Wait.explicit_wait_visibilityof_webelement_600(group.Enter_Block_Name, driver);
		group.Enter_Block_Name.sendKeys(blockName);
		test_steps.add("Entered New Block Name : " + blockName);
		groupLogger.info("Entered New Block Name : " + blockName);

		Wait.explicit_wait_xpath(OR.Click_Ok, driver);
		Wait.explicit_wait_visibilityof_webelement_150(group.Click_Ok, driver);
		Utility.ScrollToElement(group.Click_Ok, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Ok, driver);
		group.Click_Ok.click();
		test_steps.add("OK Button Clicked");
		groupLogger.info("OK Button Clicked");

		// String calPath = "//*[@id='trArrive']/td[2]/img";
		Wait.explicit_wait_xpath(OR.Select_Arrival_Date_Groups, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Select_Arrival_Date_Groups, driver);
		Wait.explicit_wait_elementToBeClickable(group.Select_Arrival_Date_Groups, driver);
		group.Select_Arrival_Date_Groups.click();
		test_steps.add("Select Arrival Date");
		groupLogger.info("Select Arrival Date");

		// String todayPath = "//div[1]/table/tfoot/tr[1]/th[text()='Today']";
		Wait.explicit_wait_xpath(OR.Click_Today_Arrival_Groups, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Click_Today_Arrival_Groups, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Today_Arrival_Groups, driver);
		group.Click_Today_Arrival_Groups.click();
		test_steps.add("Today Clicked as Arrival Date");
		groupLogger.info("Today Clicked as Arrival Date");

		Wait.explicit_wait_xpath(OR.Enter_No_of_Nigts, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Enter_No_of_Nigts, driver);
		Wait.explicit_wait_elementToBeClickable(group.Enter_No_of_Nigts, driver);
		group.Enter_No_of_Nigts.click();
		group.Enter_No_of_Nigts.sendKeys(roomPerNight);
		test_steps.add("Entered Room Per Night : " + roomPerNight);
		groupLogger.info("Entered Room Per Night : " + roomPerNight);

		Wait.explicit_wait_xpath(OR.Click_Search_Group, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Click_Search_Group, driver);
		Utility.ScrollToElement(group.Click_Search_Group, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Search_Group, driver);
		group.Click_Search_Group.click();
		test_steps.add("Search Group Button Clicked");
		groupLogger.info("Search Group Button Clicked");
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		List<WebElement> GetRoomclassNames = AdvGroup.GetRoomclasses;
		groupLogger.info("GetRoomclassNames" + GetRoomclassNames.size());
		for (int i = 0; i < GetRoomclassNames.size(); i++) {
			if (GetRoomclassNames.get(i).getText().equals("Junior Suites")) {
				int index = i + 1;
				driver.findElement(By.xpath(
						"//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index + "]/td[3]/input"))
						.sendKeys(Keys.chord(Keys.CONTROL, "a"), roomPerNight);
				Wait.wait1Second();
				test_steps.add("Select room block for : Junior Suites for : " + roomPerNight + " days");

				// break;
			} else {
				int index = i + 1;
				driver.findElement(By.xpath(
						"//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index + "]/td[3]/input"))
						.sendKeys(Keys.chord(Keys.CONTROL, "a"), "0");
				Wait.wait1Second();
			}
		}
		Wait.explicit_wait_xpath(OR.Click_Create_Block, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Click_Create_Block, driver);
		Utility.ScrollToElement(group.Click_Create_Block, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Create_Block, driver);
		group.Click_Create_Block.click();
		test_steps.add("Create Block Button Clicked");
		groupLogger.info("Create Block Button Clicked");

		try {
			Wait.explicit_wait_xpath(OR.Click_Ok_On_Rel_Popup, driver);
			if (group.Click_Ok_On_Rel_Popup.isDisplayed()) {
				Wait.explicit_wait_visibilityof_webelement_200(group.Click_Ok_On_Rel_Popup, driver);
				Utility.ScrollToElement(group.Click_Ok_On_Rel_Popup, driver);
				Wait.explicit_wait_elementToBeClickable(group.Click_Ok_On_Rel_Popup, driver);
				group.Click_Ok_On_Rel_Popup.click();
				test_steps.add("Release Date OK Button Clicked");
				groupLogger.info("Release Date OK Button Clicked");
			}
		} catch (Exception e) {
			System.out.println("Release Date Popup not Displayed");
		}

		Wait.wait2Second();
		return test_steps;
	}

	public String getPickUpValue(WebDriver driver, String RoomClass) throws InterruptedException {

		String pickupPath = "//a[text()='" + RoomClass + "']/parent::div/parent::div/preceding-sibling::div[2]/div";
		Wait.WaitForElement(driver, pickupPath);
		Wait.waitForElementToBeVisibile(By.xpath(pickupPath), driver);
		Wait.waitForElementToBeClickable(By.xpath(pickupPath), driver);
		Utility.ScrollToElement_NoWait(driver.findElement(By.xpath(pickupPath)), driver);
		return driver.findElement(By.xpath(pickupPath)).getText();
	}

	public ArrayList<String> roomingListClick(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Rooming_List, driver);
		Wait.explicit_wait_visibilityof_webelement_150(group.Rooming_List, driver);
		Utility.ScrollToElement_NoWait(group.Rooming_List, driver);
		Wait.explicit_wait_elementToBeClickable(group.Rooming_List, driver);
		Utility.ScrollToElement(group.Rooming_List, driver);
		group.Rooming_List.click();
		test_steps.add("Rooming List Button Clicked");
		groupLogger.info("Rooming List Button Clicked");

		return test_steps;
	}

	public ArrayList<String> roomingListPopup_RoomPickup(WebDriver driver, String FirstName, String LastName,
			String RoomClass, String Amount, String Country, String State, String City, String paymentMethod,
			String accountNumber, String expiryDate) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_OldGroups group = new Elements_OldGroups(driver);

		driver.switchTo().frame("dialog-body0");

		Wait.waitUntilPresenceOfElementLocated(OR.Rooming_List_Popup, driver);

		Wait.waitUntilPresenceOfElementLocated(OR.oldGroups_Enter_FirstName_Roominglistpopup, driver);
		group.oldGroups_Enter_FirstName_Roominglistpopup.sendKeys(FirstName);
		test_steps.add("Entered First Name in Rooming List Popup : " + FirstName);
		groupLogger.info("Entered First Name in Rooming List Popup : " + FirstName);

		Wait.waitUntilPresenceOfElementLocated(OR.oldGroups_Enter_LastName_Roominglistpoup, driver);
		group.oldGroups_Enter_LastName_Roominglistpoup.sendKeys(LastName);
		test_steps.add("Entered Last Name in Rooming List Popup : " + LastName);
		groupLogger.info("Entered Last Name in Rooming List Popup : " + LastName);

		Wait.explicit_wait_visibilityof_webelement_120(group.oldGroups_Select_Roomclass_Roominglistpoup, driver);
		new Select(group.oldGroups_Select_Roomclass_Roominglistpoup).selectByVisibleText(RoomClass);
		test_steps.add("Selected RoomClass in Rooming List Popup : " + RoomClass);
		groupLogger.info("Selected RoomClass in Rooming List Popup : " + RoomClass);

		String roomnum = OR.oldGroups_Select_RoomNo_Roominglistpopup + "/option";
		System.out.println(roomnum);
		int count = driver.findElements(By.xpath(roomnum)).size();
		Random random = new Random();
		int randomNumber = random.nextInt(count - 1) + 1;
		System.out.println("count : " + count);
		System.out.println("randomNumber : " + randomNumber);
		new Select(group.oldGroups_Select_RoomNo_Roominglistpopup).selectByIndex(randomNumber);

		test_steps.add("Selected RoomNo in Rooming List Popup : " + randomNumber);
		groupLogger.info("Selected RoomNo in Rooming List Popup : " + randomNumber);

		// Wait.waitUntilPresenceOfElementLocated(OR.oldGroups_Amount_Roominglistpopup,
		// driver);
		Wait.explicit_wait_visibilityof_webelement_120(group.oldGroups_Amount_Roominglistpopup, driver);
		Utility.ScrollToElement(group.oldGroups_Amount_Roominglistpopup, driver);
		Wait.explicit_wait_elementToBeClickable(group.oldGroups_Amount_Roominglistpopup, driver);
		group.oldGroups_Amount_Roominglistpopup.click();
		group.oldGroups_Amount_Roominglistpopup.clear();
		group.oldGroups_Amount_Roominglistpopup.sendKeys(Amount);
		test_steps.add("Entered Amount in Rooming List Popup : " + Amount);
		groupLogger.info("Entered Amount in Rooming List Popup : " + Amount);

		Wait.explicit_wait_visibilityof_webelement_120(group.oldGroups_ClickBillingInfo_Roominglistpopup, driver);
		Utility.ScrollToElement(group.oldGroups_ClickBillingInfo_Roominglistpopup, driver);
		Wait.explicit_wait_elementToBeClickable(group.oldGroups_ClickBillingInfo_Roominglistpopup, driver);
		group.oldGroups_ClickBillingInfo_Roominglistpopup.click();
		test_steps.add("Billing Info Button Clicked");
		groupLogger.info("Billing Info Button Clicked");
		loadingImage(driver);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("dialog-body1");
		Wait.waitUntilPresenceOfElementLocated("//*[@id='frmRoomingListBillingInfo']", driver);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.id("drpBilling_countryID")), driver);
		new Select(driver.findElement(By.id("drpBilling_countryID"))).selectByVisibleText(Country);
		test_steps.add("Selected Country in Billing Info Popup : " + Country);
		groupLogger.info("Selected Country in Billing Info Popup : " + Country);

		Assert.assertEquals(
				new Select(driver.findElement(By.id("drpBilling_countryID"))).getFirstSelectedOption().getText(),
				Country, "Failed Country Not Matched in Billing Info");
		test_steps.add("Successfully Verified Selected Country in Billing Info Popup: " + Country);
		groupLogger.info("Successfully Verified Selected Country in Billing Info Popup: " + Country);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.id("drpBilling_territoryID")), driver);
		new Select(driver.findElement(By.id("drpBilling_territoryID"))).selectByVisibleText(State);
		test_steps.add("Selected State in Billing Info Popup : " + State);
		groupLogger.info("Selected State in Billing Info Popup : " + State);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.id("txtBilling_city")), driver);
		driver.findElement(By.id("txtBilling_city")).clear();
		driver.findElement(By.id("txtBilling_city")).sendKeys(City);
		test_steps.add("Entered City in Billing Info Popup : " + City);
		groupLogger.info("Entered City in Billing Info Popup : " + City);

		Elements_AdvanceGroups group1 = new Elements_AdvanceGroups(driver);

		Wait.explicit_wait_visibilityof_webelement_120(group1.BillingInfo_PaymentMethod, driver);
		String selectedPayType = new Select(group1.BillingInfo_PaymentMethod).getFirstSelectedOption().getText();
		assertEquals(selectedPayType, paymentMethod, "Failed : Payment Method Not Matched");
		test_steps.add("Successfully Verified Payment Method : " + paymentMethod);
		groupLogger.info("Successfully Verified Payment Method : " + paymentMethod);

		Elements_Groups grp = new Elements_Groups(driver);
		Wait.explicit_wait_visibilityof_webelement_120(grp.RoomingListBilling_AccountNo, driver);
		String enteredAccNo = grp.RoomingListBilling_AccountNo.getAttribute("value");
		assertEquals(enteredAccNo, accountNumber, "Failed : Account Number Not Matched");

		test_steps.add("Successfully Verified Account Number in Billing Info Popup: " + enteredAccNo);
		groupLogger.info("Successfully Verified Account Number in Billing Info Popup: " + enteredAccNo);

		Wait.explicit_wait_visibilityof_webelement_120(grp.RoomingListBilling_ExpiryDate, driver);
		String enteredExpiryDate = grp.RoomingListBilling_ExpiryDate.getAttribute("value");
		assertEquals(enteredExpiryDate, expiryDate, "Failed : Expiry Date Not Matched");

		test_steps.add("Successfully Verified Expiry Date in Billing Info Popup: " + enteredExpiryDate);
		groupLogger.info("Successfully Verified Expiry Date in Billing Info Popup: " + enteredExpiryDate);
		Wait.explicit_wait_visibilityof_webelement_120(group1.BillingInfo_Save, driver);
		Utility.ScrollToElement(group1.BillingInfo_Save, driver);
		Wait.explicit_wait_elementToBeClickable(group1.BillingInfo_Save, driver);
		group1.BillingInfo_Save.click();
		test_steps.add("Billing Info Save Button Clicked");
		groupLogger.info("Billing Info Save Button Clicked");
		loadingImage(driver);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("dialog-body0");
		loadingImage(driver);
		driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> verifyBillingInfoFullyPaid(WebDriver driver) {
		ArrayList<String> test_steps = new ArrayList<>();
		// driver.switchTo().frame("dialog-body0");

		Wait.waitUntilPresenceOfElementLocated(OR.Rooming_List_Popup, driver);
		loadingImage(driver);
		try {
			String fullyPaidImagePath = "//*[@id='dgRoomingList_imgComplete_0']";
			Wait.waitUntilPresenceOfElementLocated(fullyPaidImagePath, driver);
			WebElement fullyPaidImg = driver.findElement(By.xpath(fullyPaidImagePath));
			String src = fullyPaidImg.getAttribute("src");
			assertTrue(src.contains("Assets/CompleteBold.GIF"), "Failed To verify Fully Paid");
			test_steps.add("Successfully Verified Fully Paid Icon");
			groupLogger.info("Successfully Verified Fully Paid Icon");

		} catch (Exception e) {
			assertTrue(false, "Failed To verify Fully Paid");
		}
		// driver.switchTo().defaultContent();

		return test_steps;
	}

	public ArrayList<String> pickUpClick(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		// driver.switchTo().frame("dialog-body0");

		Wait.waitUntilPresenceOfElementLocated(OR.Rooming_List_Popup, driver);

		Wait.waitUntilPresenceOfElementLocated(OR.Groups_Select_Pickupbutton_Roominglistpopup, driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(OR.Groups_Select_Pickupbutton_Roominglistpopup)), driver);
		Wait.explicit_wait_elementToBeClickable(
				driver.findElement(By.xpath(OR.Groups_Select_Pickupbutton_Roominglistpopup)), driver);
		driver.findElement(By.xpath(OR.Groups_Select_Pickupbutton_Roominglistpopup)).click();
		test_steps.add("Pick Up Button Clicked");
		groupLogger.info("Pick Up Button Clicked");
		loadingImage(driver);
		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> pickUpCloseClick(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		// driver.switchTo().frame("dialog-body1");
		loadingImage(driver);
		// Wait.waitUntilPresenceOfElementLocated("//*[@id='frmRoomingListSummary']",
		// driver);
		Wait.explicit_wait_visibilityof_webelement_600(
				driver.findElement(By.xpath(OR.oldGroup_Click_Close_Roominglistsummary)), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(OR.oldGroup_Click_Close_Roominglistsummary)), driver);
		Wait.explicit_wait_elementToBeClickable(
				driver.findElement(By.xpath(OR.oldGroup_Click_Close_Roominglistsummary)), driver);
		driver.findElement(By.xpath(OR.oldGroup_Click_Close_Roominglistsummary)).click();
		test_steps.add("Rooming List Summary Close Button Clicked");
		groupLogger.info("Rooming List Summary Close Button Clicked");
		loadingImage(driver);
		// driver.switchTo().defaultContent();
		// loadingImage(driver);
		return test_steps;
	}

	public ArrayList<String> paymentInfoButtonClick(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_PickBillingInfo_Button, driver);
		Utility.ScrollToElement(group.Group_Folio_PickBillingInfo_Button, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_PickBillingInfo_Button, driver);
		group.Group_Folio_PickBillingInfo_Button.click();
		test_steps.add("Payment Billing Info Button Clicked");
		groupLogger.info("Payment Billing Info Button Clicked");
		return test_steps;
	}

	public ArrayList<String> paymentInfo(WebDriver driver, String firstName, String lastName, String country,
			String state, String city, String postalCode, String paymentMethod, String cardNo, String expiryDate)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		driver.switchTo().frame("dialog-body0");
		Wait.waitUntilPresenceOfElementLocated("//*[@id='frmFolioBillingDetail']", driver);

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_FirstName, driver);
		group.Group_Folio_BillingInfo_FirstName.clear();
		group.Group_Folio_BillingInfo_FirstName.sendKeys(firstName);
		test_steps.add("Entered First Name in Billing Info Popup : " + firstName);
		groupLogger.info("Entered First Name in Billing Info Popup : " + firstName);

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_LastName, driver);
		group.Group_Folio_BillingInfo_LastName.clear();
		group.Group_Folio_BillingInfo_LastName.sendKeys(lastName);
		test_steps.add("Entered Last Name in Billing Info Popup : " + lastName);
		groupLogger.info("Entered Last Name in Billing Info Popup : " + lastName);

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_Country, driver);
		new Select(group.Group_Folio_BillingInfo_Country).selectByVisibleText(country);
		test_steps.add("Selected Country in Billing Info Popup : " + country);
		groupLogger.info("Selected Country in Billing Info Popup : " + country);

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_State, driver);
		new Select(group.Group_Folio_BillingInfo_State).selectByVisibleText(state);
		test_steps.add("Selected State in Billing Info Popup : " + state);
		groupLogger.info("Selected State in Billing Info Popup : " + state);

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_City, driver);
		group.Group_Folio_BillingInfo_City.clear();
		group.Group_Folio_BillingInfo_City.sendKeys(city);
		test_steps.add("Entered City in Billing Info Popup : " + city);
		groupLogger.info("Entered City in Billing Info Popup : " + city);

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_PostalCode, driver);
		group.Group_Folio_BillingInfo_PostalCode.clear();
		group.Group_Folio_BillingInfo_PostalCode.sendKeys(postalCode);
		test_steps.add("Entered Postal Code in Billing Info Popup : " + postalCode);
		groupLogger.info("Entered Postal Code in Billing Info Popup : " + postalCode);

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_PaymentMethod, driver);
		new Select(group.Group_Folio_BillingInfo_PaymentMethod).selectByVisibleText(paymentMethod);
		test_steps.add("Selected Payment Method : " + paymentMethod);
		groupLogger.info("Selected Payment Method : " + paymentMethod);

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_AccountNumber, driver);
		group.Group_Folio_BillingInfo_AccountNumber.clear();
		group.Group_Folio_BillingInfo_AccountNumber.sendKeys(cardNo);
		test_steps.add("Entered Card No in Billing Info Popup : " + cardNo);
		groupLogger.info("Entered Card No in Billing Info Popup : " + cardNo);

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_ExpiryDate, driver);
		group.Group_Folio_BillingInfo_ExpiryDate.clear();
		group.Group_Folio_BillingInfo_ExpiryDate.sendKeys(expiryDate);
		test_steps.add("Entered Expiry Date in Billing Info Popup : " + expiryDate);
		groupLogger.info("Entered Expiry Date in Billing Info Popup : " + expiryDate);

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_SaveButton, driver);
		Utility.ScrollToElement(group.Group_Folio_BillingInfo_SaveButton, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_BillingInfo_SaveButton, driver);
		group.Group_Folio_BillingInfo_SaveButton.click();
		test_steps.add("Billing Info Save Button Clicked");
		groupLogger.info("Billing Info Save Button Clicked");
		loadingImage(driver);
		driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> verifyPaymentInfoCountry(WebDriver driver, String country, String state, String city,
			String postalCode, String paymentMethod, String cardNo, String expiryDate) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		driver.switchTo().frame("dialog-body0");
		Wait.waitUntilPresenceOfElementLocated("//*[@id='frmFolioBillingDetail']", driver);

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_Country, driver);
		String selectedCountry = new Select(group.Group_Folio_BillingInfo_Country).getFirstSelectedOption().getText();
		assertEquals(selectedCountry, country, "Failed to Match Selected Country");
		test_steps.add("Successfully Verified Country in Payment Billing Info");
		groupLogger.info("Successfully Verified Country in Payment Billing Info");

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_State, driver);
		String selectedState = new Select(group.Group_Folio_BillingInfo_State).getFirstSelectedOption().getText();
		assertEquals(selectedState, state, "Failed to Match Selected State");
		test_steps.add("Successfully Verified State in Payment Billing Info");
		groupLogger.info("Successfully Verified State in Payment Billing Info");

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_City, driver);
		String cityValue = group.Group_Folio_BillingInfo_City.getAttribute("value");
		assertEquals(cityValue, city, "Failed to Match Entered City");
		test_steps.add("Successfully Verified City in Payment Billing Info");
		groupLogger.info("Successfully Verified City in Payment Billing Info");

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_PostalCode, driver);
		String foundPostalCode = group.Group_Folio_BillingInfo_PostalCode.getAttribute("value");
		assertEquals(foundPostalCode, postalCode, "Failed to Match Entered Postal Code");
		test_steps.add("Successfully Verified Postal Code in Payment Billing Info");
		groupLogger.info("Successfully Verified Postal Code in Payment Billing Info");

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_PaymentMethod, driver);
		String foundPaymentMethod = new Select(group.Group_Folio_BillingInfo_PaymentMethod).getFirstSelectedOption()
				.getText();
		assertEquals(foundPaymentMethod, paymentMethod, "Failed to Match Selected Payment Method");
		test_steps.add("Successfully Verified Payment Method in Payment Billing Info");
		groupLogger.info("Successfully Verified Payment Method in Payment Billing Info");

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_AccountNumber, driver);
		String foundCardNo = group.Group_Folio_BillingInfo_AccountNumber.getAttribute("value");
		assertEquals(foundCardNo.substring(foundCardNo.length() - 4), cardNo.substring(cardNo.length() - 4),
				"Failed to Match Entered Card No");
		test_steps.add("Successfully Verified Card No in Payment Billing Info");
		groupLogger.info("Successfully Verified Card No in Payment Billing Info");

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_ExpiryDate, driver);
		String foundExpiryDate = group.Group_Folio_BillingInfo_ExpiryDate.getAttribute("value");
		assertEquals(foundExpiryDate, expiryDate, "Failed to Match Entered Expiry Date");
		test_steps.add("Successfully Verified Expiry Date in Payment Billing Info");
		groupLogger.info("Successfully Verified Expiry Date in Payment Billing Info");

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_SaveButton, driver);
		Utility.ScrollToElement(group.Group_Folio_BillingInfo_SaveButton, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_BillingInfo_SaveButton, driver);
		Utility.ScrollToElement(group.Group_Folio_BillingInfo_SaveButton, driver);
		group.Group_Folio_BillingInfo_SaveButton.click();
		test_steps.add("Billing Info Save Button Clicked");
		groupLogger.info("Billing Info Save Button Clicked");
		loadingImage(driver);
		driver.switchTo().defaultContent();
		return test_steps;
	}

	public String getAvailableRooms(WebDriver driver, String RoomClass) {

		String avilRoomPath = "//a[text()='" + RoomClass + "']/parent::div/parent::div/preceding-sibling::div[5]/div";

		Wait.waitUntilPresenceOfElementLocated(avilRoomPath, driver);
		Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(avilRoomPath)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(avilRoomPath)), driver);

		return driver.findElement(By.xpath(avilRoomPath)).getText();
	}

	public ArrayList<String> bookIconClick(WebDriver driver, String RoomClass) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		String bookIconPath = "//a[text()='" + RoomClass
				+ "']/parent::div/parent::div/following-sibling::div[4]//div[@title='Book']";

		Wait.waitUntilPresenceOfElementLocated(bookIconPath, driver);
		Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(bookIconPath)), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(bookIconPath)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(bookIconPath)), driver);
		driver.findElement(By.xpath(bookIconPath)).click();

		test_steps.add("Book Icon Clicked");
		groupLogger.info("Book Icon Clicked");

		return test_steps;
	}

	public ArrayList<String> bookIconClick1(WebDriver driver, String RoomClass) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		String bookIconPath = "//*[@id='row1JQGrid']/div[13]/div/div";

		driver.findElement(By.xpath(bookIconPath)).click();

		test_steps.add("Book Icon Clicked");
		groupLogger.info("Book Icon Clicked");

		return test_steps;
	}

	public String getBookIconClass(WebDriver driver, String RoomClass) {

		String bookIconPath = "//a[text()='" + RoomClass
				+ "']/parent::div/parent::div/following-sibling::div[4]//div[@title='Book']";

		Wait.waitUntilPresenceOfElementLocated(bookIconPath, driver);
		Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(bookIconPath)), driver);

		return driver.findElement(By.xpath(bookIconPath)).getAttribute("class");
	}

	public String getFolioLinesItemCount(WebDriver driver) {

		String path = "//*[@id='MainContent_Folio1_dgLineItems']/tbody/tr";

		Wait.waitUntilPresenceOfElementLocated(path, driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(path)), driver);
		return (driver.findElements(By.xpath(path)).size() - 1) + "";
	}

	public ArrayList<String> lineItemExist(WebDriver driver, String lineCategory, boolean isExist) {
		ArrayList<String> test_steps = new ArrayList<String>();

		String path = "//tbody/tr/td/span[contains(text(),'" + lineCategory + "')]";

		if (isExist) {
			try {
				Wait.waitUntilPresenceOfElementLocated(path, driver);
				Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
				assertTrue(driver.findElement(By.xpath(path)).isDisplayed(), "Failed: Line Item Not Displayed");
				test_steps.add("Successfully Verified Line Item : Exist");
				groupLogger.info("Successfully Verified Line Item : Exist");
			} catch (Exception e) {
				assertTrue(false, "Failed: Line Item Not Displayed");
			}
		} else {
			try {
				// Wait.waitUntilPresenceOfElementLocated(path, driver);
				// Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)),
				// driver);
				assertTrue(!driver.findElement(By.xpath(path)).isDisplayed(), "Failed: Line Item is Displayed");
				test_steps.add("Successfully Verified Line Item : Not Exist");
				groupLogger.info("Successfully Verified Line Item : Not Exist");
			} catch (Exception e) {
				assertTrue(true);
				test_steps.add("Successfully Verified Line Item : Not Exist");
				groupLogger.info("Successfully Verified Line Item : Not Exist");
			}
		}

		return test_steps;
	}

	public ArrayList<String> verifyFullyPaidIcon(WebDriver driver, String lineCategory, boolean isEnable) {
		ArrayList<String> test_steps = new ArrayList<>();

		// String paidIconPath =
		// "//tbody/tr/td/span[contains(text(),'"+lineCategory+"')]//parent::td/../td[11]/img";
		// //"MainContent_Folio1_dgLineItems_imgItemStatus_" + LineNo;

		if (isEnable) {
			String paidIconPath = "//tbody/tr/td/span[contains(text(),'" + lineCategory
					+ "')]//parent::td/../td[11]/img[@title='Fully Paid']"; // "MainContent_Folio1_dgLineItems_imgItemStatus_"
																			// +
																			// LineNo;
			Wait.explicit_wait_xpath(paidIconPath, driver);
			Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(paidIconPath)), driver);
			String src = driver.findElement(By.xpath(paidIconPath)).getAttribute("src");
			String title = driver.findElement(By.xpath(paidIconPath)).getAttribute("title");
			assertTrue(src.contains("Folio_Images/fully-paid.png"), "Failed Icon Source not matched");
			assertEquals(title, "Fully Paid", "Failed to Match Title");
			test_steps.add("Successfully Verified Icon Source and Title : Fully Paid");
			groupLogger.info("Successfully Verified Icon Source and Title : Fully Paid");
		} else {
			String paidIconPath = "//tbody/tr/td/span[contains(text(),'" + lineCategory
					+ "')]//parent::td/../td[11]/img";
			try {
				assertTrue(!driver.findElement(By.xpath(paidIconPath)).isDisplayed(), "Failed Icon Source not matched");
			} catch (Exception e) {
				System.out.println("Paid Icon not available");
			}
			test_steps.add("Successfully Verified Fully Paid Icon not displayed");
			groupLogger.info("Successfully Verified Fully Paid Icon not displayed");
		}

		return test_steps;
	}

	public ArrayList<String> checkAdvanceDepositVisibility(WebDriver driver, boolean isVisible) {
		ArrayList<String> test_steps = new ArrayList<>();

		String advanceDepositLinkPath = "//*[@id='MainContent_Folio1_fSummary1_lbtnDisplaycaption']";
		if (isVisible) {
			Wait.explicit_wait_xpath(advanceDepositLinkPath, driver);
			Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(advanceDepositLinkPath)), driver);
			assertTrue(driver.findElement(By.xpath(advanceDepositLinkPath)).isDisplayed(),
					"Failed Advance Deposit Link Not Displayed");
			test_steps.add("Successfully Verified Advance Deposit Link is displayed");
			groupLogger.info("Successfully Verified Advance Deposit Link is displayed");
		} else {
			try {
				assertTrue(!driver.findElement(By.xpath(advanceDepositLinkPath)).isDisplayed(),
						"Failed Advance Deposit Link is Displayed");
			} catch (Exception e) {
				test_steps.add("Successfully Verified Advance Deposit Link not displayed");
				groupLogger.info("Successfully Verified Advance Deposit Link not displayed");
			}
		}

		return test_steps;
	}

	public ArrayList<String> clickAdvanceDepositLink(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();

		Elements_Groups group = new Elements_Groups(driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Group_Folio_AdvanceDeposit, driver);
		Utility.ScrollToElement(group.Group_Folio_AdvanceDeposit, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_AdvanceDeposit, driver);
		group.Group_Folio_AdvanceDeposit.click();
		groupLogger.info("Click Advance Deposit Balance");
		test_steps.add("Click Advance Deposit Balance");

		return test_steps;
	}

	public ArrayList<String> checkOutStandingItemsCheckBox(WebDriver driver, String item) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String checkBox = "//*[@id='dgPaymentDetails']//a[contains(text(),'" + item
				+ "')]/parent::td/preceding-sibling::td[1]/input[@type='checkbox']";
		// driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));

		Wait.waitUntilPresenceOfElementLocated(checkBox, driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(checkBox)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(checkBox)), driver);
		if (!driver.findElement(By.xpath(checkBox)).isSelected()) {
			driver.findElement(By.xpath(checkBox)).click();
		}
		groupLogger.info("Item Selected : " + item);
		test_steps.add("Item Selected : " + item);

		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> clickAutoApply(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		// driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		// Wait.wait3Second();
		loadingImage(driver);
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_AdvanceDepositAutoApply, driver);
		// Wait.WaitForElement(driver, OR.Group_Folio_AdvanceDepositAutoApply);
		// Wait.explicit_wait_xpath(OR.Group_Folio_AdvanceDepositAutoApply);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_AdvanceDepositAutoApply, driver);
		Utility.ScrollToElement(group.Group_Folio_AdvanceDepositAutoApply, driver);
		group.Group_Folio_AdvanceDepositAutoApply.click();
		test_steps.add("Click Group_Folio_AdvanceDepositAutoApply");
		groupLogger.info("Click Group_Folio_AdvanceDepositAutoApply");
		// Wait.wait2Second();
		// driver.switchTo().defaultContent();

		return test_steps;
	}

	public ArrayList<String> clickAdd_AdvanceDeposit(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		// driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		// Wait.wait3Second();
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_AdvanceDepositAdd, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_AdvanceDepositAdd, driver);
		Utility.ScrollToElement_NoWait(group.Group_Folio_AdvanceDepositAdd, driver);
		group.Group_Folio_AdvanceDepositAdd.click();
		test_steps.add("Click Group Folio AdvanceDeposit Add");
		groupLogger.info("Click Group Folio AdvanceDeposit Add");
		try {
			Wait.explicit_wait_visibilityof_webelement(
					driver.findElement(By.xpath("//div[@aria-describedby='divadvanceDepostBalance']")), driver);
			test_steps.add("Apply Advance Deposit Dialog Appear");
			groupLogger.info("Apply Advance Deposit Dialog Appear");
			Wait.explicit_wait_visibilityof_webelement(
					driver.findElement(
							By.xpath("//div[contains(@class,'ui-dialog-buttonpane')]//button[text()='Continue']")),
					driver);
			Wait.explicit_wait_elementToBeClickable(
					driver.findElement(
							By.xpath("//div[contains(@class,'ui-dialog-buttonpane')]//button[text()='Continue']")),
					driver);
			Utility.ScrollToElement_NoWait(
					driver.findElement(
							By.xpath("//div[contains(@class,'ui-dialog-buttonpane')]//button[text()='Continue']")),
					driver);
			driver.findElement(By.xpath("//div[contains(@class,'ui-dialog-buttonpane')]//button[text()='Continue']"))
					.click();
			test_steps.add("Click Apply Advance Deposit Dialog Continue");
			groupLogger.info("Click Apply Advance Deposit Dialog Continue");
		} catch (Exception e) {
			test_steps.add("Apply Advance Deposit Dialog not Appear");
			groupLogger.info("Apply Advance Deposit Dialog not Appear");
		}

		// driver.switchTo().defaultContent();

		return test_steps;
	}

	public ArrayList<String> clickContinue_AdvanceDeposit(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		// driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		// Wait.wait3Second();

		try {
			Wait.explicit_wait_visibilityof_webelement(group.AdvanceDepositConfirmPopup, driver);
			group.AdvanceDepositConfirmPopup_Yes.click();
			test_steps.add("Clicking Advance Deposit Yes");
			groupLogger.info("Clicking Advance Deposit Yes");
		} catch (Exception e) {
			test_steps.add("NO Advance Deposit Popup");
			groupLogger.info("NO Advance Deposit Popup");
		}
		Wait.explicit_wait_visibilityof_webelement(group.Group_Folio_AdvanceDepositContinue, driver);
		Utility.ScrollToElement(group.Group_Folio_AdvanceDepositContinue, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_AdvanceDepositContinue, driver);
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();",
		// group.Group_Folio_AdvanceDepositContinue);
		group.Group_Folio_AdvanceDepositContinue.click();
		test_steps.add("Click Group Folio Continue");
		groupLogger.info("Click Group Folio Continue");

		loadingImage(driver);
		// driver.switchTo().defaultContent();
		// loadingImage(driver);
		return test_steps;
	}

	public ArrayList<String> clickPay(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		// Click Add Line Item
		Wait.WaitForElement(driver, OR.Group_Folio_Pay);
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_Pay, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_Pay, driver);
		Utility.ScrollToElement_NoWait(group.Group_Folio_Pay, driver);
		group.Group_Folio_Pay.click();
		test_steps.add("Click on Pay Line item");
		groupLogger.info("Click on Pay Line item");
		return test_steps;
	}

	public ArrayList<String> selectPaymentMethodAndAmount(WebDriver driver, String PaymentMethod, String PayAmount)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		Wait.wait5Second();
		// Wait.explicit_wait_xpath("//*[@id='dialog-body0']", driver);
		// driver.switchTo().frame("dialog-body0");

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_PaymentMethod, driver);
		Select payMethod = new Select(group.Group_Folio_PaymentMethod);
		payMethod.selectByVisibleText(PaymentMethod);

		test_steps.add("Selcted Category : " + PaymentMethod);
		groupLogger.info("Selcted Category : " + PaymentMethod);
		loadingImage(driver);
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_Amount, driver);
		Utility.ScrollToElement(group.Group_Folio_Amount, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_Amount, driver);
		try {
			((JavascriptExecutor) driver).executeAsyncScript("arguments[0].value='" + PayAmount + "'",
					group.Group_Folio_Amount);
			// group.Group_Folio_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"),
			// PayAmount);
		} catch (Exception e) {
			System.out.println("Payment catch");
			group.Group_Folio_Amount.click();
			group.Group_Folio_Amount.clear();
			group.Group_Folio_Amount.sendKeys(PayAmount);
		}
		test_steps.add("Entered Amount : " + PayAmount);
		groupLogger.info("Entered Amount : " + PayAmount);

		// driver.switchTo().defaultContent();

		return test_steps;
	}

	public ArrayList<String> clickAddContinue_Payment(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		// driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		// Wait.wait3Second();
		loadingImage(driver);
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_AdvanceDepositAdd, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_AdvanceDepositAdd, driver);
		Utility.ScrollToElement(group.Group_Folio_AdvanceDepositAdd, driver);
		group.Group_Folio_AdvanceDepositAdd.click();
		test_steps.add("Click Group Folio Add");
		groupLogger.info("Click Group Folio Add");
		try {
			Wait.explicit_wait_visibilityof_webelement(
					driver.findElement(By.xpath("//div[@aria-describedby='divAdvanceDepostConfirm']")), driver);
			test_steps.add("Apply Advance Deposit Dialog Appear");
			groupLogger.info("Apply Advance Deposit Dialog Appear");
		} catch (Exception e) {
			// test_steps.add("Apply Advance Deposit Dialog Appear");
			groupLogger.info("Apply Advance Deposit Dialog not Appear");
		}
		try {

			Wait.explicit_wait_visibilityof_webelement_120(
					driver.findElement(
							By.xpath("//div[contains(@class,'ui-dialog-buttonpane')]//button[text()='Continue']")),
					driver);
			Wait.explicit_wait_elementToBeClickable(
					driver.findElement(
							By.xpath("//div[contains(@class,'ui-dialog-buttonpane')]//button[text()='Continue']")),
					driver);
			Utility.ScrollToElement(
					driver.findElement(
							By.xpath("//div[contains(@class,'ui-dialog-buttonpane')]//button[text()='Continue']")),
					driver);
			driver.findElement(By.xpath("//div[contains(@class,'ui-dialog-buttonpane')]//button[text()='Continue']"))
					.click();
			test_steps.add("Click Apply Advance Deposit Dialog Continue");
			groupLogger.info("Click Apply Advance Deposit Dialog Continue");
		} catch (Exception e) {
			test_steps.add("Apply Advance Deposit Dialog not Appear");
			groupLogger.info("Apply Advance Deposit Dialog not Appear");
		}

		try {
			Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_AdvanceDepositContinue, driver);
			Wait.explicit_wait_elementToBeClickable(group.Group_Folio_AdvanceDepositContinue, driver);
			Utility.ScrollToElement(group.Group_Folio_AdvanceDepositContinue, driver);
			group.Group_Folio_AdvanceDepositContinue.click();
		} catch (Exception e) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", group.Group_Folio_AdvanceDepositContinue);
		}
		test_steps.add("Click Group Folio Payment Continue");
		groupLogger.info("Click Group Folio Payment Continue");
		// driver.switchTo().defaultContent();
		loadingImage(driver);
		return test_steps;
	}

	public ArrayList<String> verifyPostedIcon(WebDriver driver, String lineCategory, boolean isEnable) {
		ArrayList<String> test_steps = new ArrayList<>();

		// String paidIconPath =
		// "//tbody/tr/td/span[contains(text(),'"+lineCategory+"')]//parent::td/../td[11]/img";
		// //"MainContent_Folio1_dgLineItems_imgItemStatus_" + LineNo;

		if (isEnable) {
			String paidIconPath = "//tbody/tr/td/span[contains(text(),'" + lineCategory
					+ "')]//parent::td/../td[2]/img"; // "MainContent_Folio1_dgLineItems_imgItemStatus_"
														// + LineNo;
			Wait.explicit_wait_xpath(paidIconPath, driver);
			Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(paidIconPath)), driver);
			String src = driver.findElement(By.xpath(paidIconPath)).getAttribute("src");
			assertTrue(src.contains("Folio_Images/2.gif"), "Failed Posted Icon Source not matched");

			test_steps.add("Successfully Verified Icon Source : Posted");
			groupLogger.info("Successfully Verified Icon Source : Posted");
		} else {
			// String paidIconPath =
			// "//tbody/tr/td/span[contains(text(),'"+lineCategory+"')]//parent::td/../td[11]/img";
			// assertTrue(!driver.findElement(By.xpath(paidIconPath)).isDisplayed(),
			// "Failed Icon Source not matched");
			// test_steps.add("Successfully Verified Fully Paid Icon not
			// displayed");
			// groupLogger.info("Successfully Verified Fully Paid Icon not
			// displayed");
		}

		return test_steps;
	}

	public ArrayList<String> navigateFolioOption(WebDriver driver, ArrayList<String> test_steps) {
		Elements_Groups group = new Elements_Groups(driver);

		// WebDriverWait wait = new WebDriverWait(driver, 90);
		Wait.explicit_wait_visibilityof_webelement_350(group.Group_FolioOptions, driver);
		// Wait.WaitForElement(driver, OR.Group_Folio);
		group.Group_FolioOptions.click();
		test_steps.add("Click on Group Folio Options");
		groupLogger.info("Click on Group Folio Options");
		return test_steps;

	}

	public ArrayList<String> selectChargeRouting(WebDriver driver, String chargeRoutingOption) {

		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);

		// Wait.scrollUntilTheElementIsVisible(driver,
		// group.Group_FolioOptions_ChargeRouting);
		new Select(group.Group_FolioOptions_ChargeRouting).selectByVisibleText(chargeRoutingOption);

		test_steps.add("Charge Routing Selected : " + chargeRoutingOption);
		groupLogger.info("Charge Routing Selected : " + chargeRoutingOption);
		return test_steps;

	}

	public ArrayList<String> verifySelectedChargeRouting(WebDriver driver, String chargeRoutingOption) {

		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);

		// Wait.scrollUntilTheElementIsVisible(driver,
		// group.Group_FolioOptions_ChargeRouting);
		String selectedOption = new Select(group.Group_FolioOptions_ChargeRouting).getFirstSelectedOption().getText();

		assertEquals(selectedOption, chargeRoutingOption, "Failed to Verify Selected Option");
		test_steps.add("Successfully Verifed Selected Charge Routing : " + chargeRoutingOption);
		groupLogger.info("Successfully Verifed Selected Charge Routing : " + chargeRoutingOption);
		return test_steps;

	}

	public ArrayList<String> clickYesApplyCharge(WebDriver driver) {
		ArrayList<String> test_steps = new ArrayList<>();
		try {
			String path = "//button[text()='Yes']";
			Wait.explicit_wait_xpath(path, driver);
			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
			Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(path)), driver);
			driver.findElement(By.xpath(path)).click();
			test_steps.add("Click Apply Charge Yes ");
			groupLogger.info("Click Apply Charge Yes");
		} catch (Exception e) {
			System.out.println("Apply charge not available");
		}
		return test_steps;

	}

	public ArrayList<String> createNewBlock(WebDriver driver, String blockName, String roomPerNight, String RoomClass)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_xpath(OR.Click_New_Block_button, driver);
		Wait.explicit_wait_visibilityof_webelement_150(group.Click_New_Block_button, driver);
		Utility.ScrollToElement(group.Click_New_Block_button, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_New_Block_button, driver);
		group.Click_New_Block_button.click();
		test_steps.add("New Block Button Clicked");
		groupLogger.info("New Block Button Clicked");

		Wait.explicit_wait_xpath(OR.Enter_Block_Name, driver);
		Wait.explicit_wait_visibilityof_webelement_600(group.Enter_Block_Name, driver);
		group.Enter_Block_Name.sendKeys(blockName);
		test_steps.add("Entered New Block Name : " + blockName);
		groupLogger.info("Entered New Block Name : " + blockName);

		Wait.explicit_wait_xpath(OR.Click_Ok, driver);
		Wait.explicit_wait_visibilityof_webelement_150(group.Click_Ok, driver);
		Utility.ScrollToElement(group.Click_Ok, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Ok, driver);
		group.Click_Ok.click();
		test_steps.add("OK Button Clicked");
		groupLogger.info("OK Button Clicked");

		// String calPath = "//*[@id='trArrive']/td[2]/img";
		Wait.explicit_wait_xpath(OR.Select_Arrival_Date_Groups, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Select_Arrival_Date_Groups, driver);
		Wait.explicit_wait_elementToBeClickable(group.Select_Arrival_Date_Groups, driver);
		group.Select_Arrival_Date_Groups.click();
		test_steps.add("Select Arrival Date");
		groupLogger.info("Select Arrival Date");

		// String todayPath = "//div[1]/table/tfoot/tr[1]/th[text()='Today']";
		Wait.explicit_wait_xpath(OR.Click_Today_Arrival_Groups, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Click_Today_Arrival_Groups, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Today_Arrival_Groups, driver);
		group.Click_Today_Arrival_Groups.click();
		test_steps.add("Today Clicked as Arrival Date");
		groupLogger.info("Today Clicked as Arrival Date");

		Wait.explicit_wait_xpath(OR.Enter_No_of_Nigts, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Enter_No_of_Nigts, driver);
		Wait.explicit_wait_elementToBeClickable(group.Enter_No_of_Nigts, driver);
		group.Enter_No_of_Nigts.click();
		group.Enter_No_of_Nigts.sendKeys(roomPerNight);
		test_steps.add("Entered Room Per Night : " + roomPerNight);
		groupLogger.info("Entered Room Per Night : " + roomPerNight);

		Wait.explicit_wait_xpath(OR.Click_Search_Group, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Click_Search_Group, driver);
		Utility.ScrollToElement(group.Click_Search_Group, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Search_Group, driver);
		group.Click_Search_Group.click();
		test_steps.add("Search Group Button Clicked");
		groupLogger.info("Search Group Button Clicked");
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		List<WebElement> GetRoomclassNames = AdvGroup.GetRoomclasses;
		groupLogger.info("GetRoomclassNames" + GetRoomclassNames.size());
		for (int i = 0; i < GetRoomclassNames.size(); i++) {
			if (GetRoomclassNames.get(i).getText().equals(RoomClass)) {
				int index = i + 1;
				driver.findElement(By.xpath(
						"//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index + "]/td[3]/input"))
						.sendKeys(Keys.chord(Keys.CONTROL, "a"), roomPerNight);
				Wait.wait1Second();
				test_steps.add("Select room block for : " + RoomClass + " for : " + roomPerNight + " days");
				groupLogger.info("Select room block for : " + RoomClass + " for : " + roomPerNight + " days");
				// break;
			} else {
				int index = i + 1;
				driver.findElement(By.xpath(
						"//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index + "]/td[3]/input"))
						.sendKeys(Keys.chord(Keys.CONTROL, "a"), "0");
				Wait.wait1Second();
			}
		}

		return test_steps;
	}

	public ArrayList<String> clickCreateBlock(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_xpath(OR.Click_Create_Block, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Click_Create_Block, driver);
		Utility.ScrollToElement(group.Click_Create_Block, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Create_Block, driver);
		group.Click_Create_Block.click();
		test_steps.add("Create Block Button Clicked");
		groupLogger.info("Create Block Button Clicked");

		try {
			Wait.explicit_wait_xpath(OR.Click_Ok_On_Rel_Popup, driver);
			if (group.Click_Ok_On_Rel_Popup.isDisplayed()) {
				Wait.explicit_wait_visibilityof_webelement_200(group.Click_Ok_On_Rel_Popup, driver);
				Utility.ScrollToElement(group.Click_Ok_On_Rel_Popup, driver);
				Wait.explicit_wait_elementToBeClickable(group.Click_Ok_On_Rel_Popup, driver);
				group.Click_Ok_On_Rel_Popup.click();
				test_steps.add("Release Date OK Button Clicked");
				groupLogger.info("Release Date OK Button Clicked");
			}
		} catch (Exception e) {
			System.out.println("Release Date Popup not Displayed");
		}

		Wait.wait2Second();
		return test_steps;
	}

	public String getRoomBlocked_RoomBlockDetatil(WebDriver driver) {

		String path = "//span[contains(text(),'Rooms Blocked')]";

		Wait.explicit_wait_xpath(path, driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		String roomBlocked = driver.findElement(By.xpath(path)).getText();
		StringTokenizer str = new StringTokenizer(roomBlocked, "Rooms Blocked:");
		String value = str.nextToken();

		return value.replaceAll("\\s", "");
	}

	public String getPickUpPercentage_RoomBlockDetatil(WebDriver driver) {

		String path = "//span[contains(text(),'Picked up %:')]";

		Wait.explicit_wait_xpath(path, driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		String pickUpPercentage = driver.findElement(By.xpath(path)).getText();
		StringTokenizer str = new StringTokenizer(pickUpPercentage, "Picked up %:");
		String value = str.nextToken();

		return value.replaceAll("\\s", "");
	}

	public String getTotalRoomNights_RoomBlockDetail(WebDriver driver) {
		String path = "//span[contains(text(),'Total Room Nights: ')]/span";
		Wait.explicit_wait_xpath(path, driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		String value = driver.findElement(By.xpath(path)).getText();

		return value;
	}

	public String getExpectedRevenue_RoomBlockDetail(WebDriver driver) {
		String path = "//span[contains(text(),'Expected Revenue: ')]/span";
		Wait.explicit_wait_xpath(path, driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		String value = driver.findElement(By.xpath(path)).getText();

		return value.replace("$", "");
	}

	public String getExpectedRevenue_GroupInfo(WebDriver driver) {
		Elements_Groups group = new Elements_Groups(driver);
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_RoomBlock_GroupInfo_ExpectedRevenue, driver);

		return group.Group_RoomBlock_GroupInfo_ExpectedRevenue.getText().replace("$", "");
	}

	public ArrayList<String> clickRoomBlockEdit(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_visibilityof_webelement_120(group.RoomDetailsPage_EditButton, driver);
		Wait.explicit_wait_elementToBeClickable(group.RoomDetailsPage_EditButton, driver);
		Utility.ScrollToElement(group.RoomDetailsPage_EditButton, driver);
		group.RoomDetailsPage_EditButton.click();
		test_steps.add("Edit Button Clicked");
		groupLogger.info("Edit Button Clicked");
		return test_steps;
	}

	public ArrayList<String> clickPreviewFolio_EditBlock(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);

		// Wait.explicit_wait_xpath("//*[@id='MainContent_Iframe_accountpicker']",
		// driver);
		// driver.switchTo().frame("MainContent_Iframe_accountpicker");

		Wait.explicit_wait_visibilityof_webelement_120(group.BlockDetailsPage_PreviewFolioButton, driver);
		Wait.explicit_wait_elementToBeClickable(group.BlockDetailsPage_PreviewFolioButton, driver);
		Utility.ScrollToElement(group.BlockDetailsPage_PreviewFolioButton, driver);
		group.BlockDetailsPage_PreviewFolioButton.click();
		test_steps.add("Preview Folio Link Clicked");
		groupLogger.info("Preview Folio Link Clicked");
		try {
			String loading = "//*[@id='InnerFreezePane']/img";
			WebElement loadingImage = driver.findElement(By.xpath(loading));
			Wait.waitForElementToBeGone(driver, loadingImage, 600000);

		} catch (Exception e) {
			System.out.println("No Loading");
		}

		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> addLineItem_PreviewFolio(WebDriver driver, String LineCategory, String qty,
			String LineAmount, String lineNo) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);

		// Wait.explicit_wait_xpath("//*[@id='MainContent_Iframe_accountpicker']",
		// driver);
		// driver.switchTo().frame("MainContent_Iframe_accountpicker");

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupPreviewFolio_AddButton, driver);
		Wait.explicit_wait_elementToBeClickable(group.GroupPreviewFolio_AddButton, driver);
		Utility.ScrollToElement(group.GroupPreviewFolio_AddButton, driver);
		group.GroupPreviewFolio_AddButton.click();
		test_steps.add("Preview Folio ADD Button Clicked");
		groupLogger.info("Preview Folio ADD Button Clicked");

		try {
			String loading = "//*[@id='InnerFreezePane']/img";
			WebElement loadingImage = driver.findElement(By.xpath(loading));
			Wait.waitForElementToBeGone(driver, loadingImage, 600000);

		} catch (Exception e) {
			System.out.println("No Loading");
		}

		String category = "//*[@id='MainContent_ucPreviewFolio_dgPreviewFolioLineItems_drpLedgeraccountname_" + lineNo
				+ "']";
		// Select Category
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(category)));
		Select cat = new Select(driver.findElement(By.xpath(category)));
		cat.selectByVisibleText(LineCategory);

		test_steps.add("Selcted Category : " + LineCategory);
		groupLogger.info("Selcted Category : " + LineCategory);
		Wait.wait5Second();
		try {
			String loading = "//*[@id='InnerFreezePane']/img";
			WebElement loadingImage = driver.findElement(By.xpath(loading));
			Wait.waitForElementToBeGone(driver, loadingImage, 600000);

		} catch (Exception e) {
			System.out.println("No Loading");
		}
		Wait.wait5Second();
		// Enter QTY
		String qtyPath = "//*[@id='MainContent_ucPreviewFolio_dgPreviewFolioLineItems_txtQuantity_" + lineNo + "']";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(qtyPath)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(qtyPath)), driver);
		driver.findElement(By.xpath(qtyPath)).sendKeys(qty);
		if (!driver.findElement(By.xpath(qtyPath)).getAttribute("value").equals(qty)) {
			groupLogger.info("qty not entered tring again...");
			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(qtyPath)), driver);
			Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(qtyPath)), driver);
			driver.findElement(By.xpath(qtyPath)).sendKeys(qty);
		}
		test_steps.add("Entered QTY : " + qty);
		groupLogger.info("Entered QTY : " + qty);
		Wait.wait2Second();
		// Enter Amount
		String amount = "//*[@id='MainContent_ucPreviewFolio_dgPreviewFolioLineItems_txtAmount_" + lineNo + "']";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(amount)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(amount)), driver);
		driver.findElement(By.xpath(amount)).sendKeys(LineAmount);
		if (!driver.findElement(By.xpath(amount)).getAttribute("value").equals(LineAmount)) {
			groupLogger.info("Line Amount not entered tring again...");
			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(amount)), driver);
			Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(amount)), driver);
			driver.findElement(By.xpath(amount)).sendKeys(LineAmount);
		}
		test_steps.add("Entered Folio Line Item Amount : " + LineAmount);
		groupLogger.info("Entered Folio Line Item Amount : " + LineAmount);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupPreviewFolio_CommitButton, driver);
		Wait.explicit_wait_elementToBeClickable(group.GroupPreviewFolio_CommitButton, driver);
		Utility.ScrollToElement(group.GroupPreviewFolio_CommitButton, driver);
		group.GroupPreviewFolio_CommitButton.click();
		test_steps.add("Preview Folio Commit Button Clicked");
		groupLogger.info("Preview Folio Commit Button Clicked");

		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> clickSave_PreviewFolio(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);

		// Wait.explicit_wait_xpath("//*[@id='MainContent_Iframe_accountpicker']",
		// driver);
		// driver.switchTo().frame("MainContent_Iframe_accountpicker");

		Wait.explicit_wait_visibilityof_webelement_120(group.Document_Save, driver);
		Wait.explicit_wait_elementToBeClickable(group.Document_Save, driver);
		Utility.ScrollToElement(group.Document_Save, driver);
		group.Document_Save.click();
		test_steps.add("Preview Folio Save Button Clicked");
		groupLogger.info("Preview Folio Save Button Clicked");

		try {
			if (driver.findElements(By.xpath(OR.Click_Continue_Block_Night)).size() > 0) {
				group.Click_Continue_Block_Night.click();
				test_steps.add("Clicking on continue");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			String loading = "//*[@id='InnerFreezePane']/img";
			WebElement loadingImage = driver.findElement(By.xpath(loading));
			if (loadingImage.isDisplayed()) {
				Wait.waitForElementToBeGone(driver, loadingImage, 600000);
			}
		} catch (Exception e) {
			System.out.println("No Loading");
		}

		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> clickDone_PreviewFolio(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);

		// Wait.explicit_wait_xpath("//*[@id='MainContent_Iframe_accountpicker']",
		// driver);
		// driver.switchTo().frame("MainContent_Iframe_accountpicker");

		Wait.explicit_wait_visibilityof_webelement_120(group.Document_Done, driver);
		Wait.explicit_wait_elementToBeClickable(group.Document_Done, driver);
		Utility.ScrollToElement(group.Document_Done, driver);
		group.Document_Done.click();
		test_steps.add("Preview Folio Done Button Clicked");
		groupLogger.info("Preview Folio Done Button Clicked");

		// try{
		// String loading = "//*[@id='InnerFreezePane']/img";
		// WebElement loadingImage = driver.findElement(By.xpath(loading));
		// if(loadingImage.isDisplayed()){
		// Wait.waitForElementToBeGone(driver, loadingImage, 600000);
		// }
		// }catch (Exception e) {
		// System.out.println("No Loading");
		// }
		loadingImage(driver);
		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public String getTotalCharges_PreviewFolio(WebDriver driver) {

		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);

		// Wait.explicit_wait_xpath("//*[@id='MainContent_Iframe_accountpicker']",
		// driver);
		// driver.switchTo().frame("MainContent_Iframe_accountpicker");
		Wait.explicit_wait_visibilityof_webelement_120(group.GroupPreviewFolio_TotalCharges, driver);
		String value = group.GroupPreviewFolio_TotalCharges.getText();

		// driver.switchTo().defaultContent();
		return value;
	}

	public ArrayList<String> verifyReservationCount(WebDriver driver, int expectedCount) {
		ArrayList<String> test_steps = new ArrayList<>();
		String path = "//*[@id='MainContent_ucReservationControl1_dgReservationList']/tbody/tr";

		try {
			// Wait.scrollUntilTheElementIsVisible(driver,
			// driver.findElement(By.xpath(path)));
			List<WebElement> ele = driver.findElements(By.xpath(path));
			if (ele.size() > 0) {
				assertEquals(ele.size() - 3, expectedCount, "Failed Reservation Count Not Matched");
				test_steps.add("Successfully Verified No of Reservation : " + (ele.size() - 3));
				groupLogger.info("Successfully Verified No of Reservation : " + (ele.size() - 3));
			} else {
				assertEquals(ele.size(), expectedCount, "Failed Reservation Count Not Matched");
				test_steps.add("Successfully Verified No of Reservation : " + (ele.size()));
				groupLogger.info("Successfully Verified No of Reservation : " + (ele.size()));
			}
		} catch (Exception e) {
			System.out.println("Reservation Table Not Found");
			assertTrue(false);
		}
		return test_steps;
	}

	public ArrayList<String> billinginfo(WebDriver driver, String paymentType, String accNo, String expiryDate,
			boolean isUseMailingAdd, boolean isCopyPickupRes) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);

		WebDriverWait wait = new WebDriverWait(driver, 90);
		Utility.ScrollToElement(group.Check_Mailing_Info, driver);
		Wait.WaitForElement(driver, OR.Check_Mailing_Info);
		wait.until(ExpectedConditions.visibilityOf(group.Check_Mailing_Info));
		if (isUseMailingAdd) {
			if (!group.Check_Mailing_Info.isSelected()) {
				group.Check_Mailing_Info.click();
			}
		} else {
			if (group.Check_Mailing_Info.isSelected()) {
				group.Check_Mailing_Info.click();
			}
		}
		test_steps.add("Check Same as Mailing Address : " + isUseMailingAdd);
		groupLogger.info("Check Same as Mailing Address : " + isUseMailingAdd);

		Utility.ScrollToElement(group.GroupBillInfoCopyToPickUpReservation, driver);
		Wait.WaitForElement(driver, OR.GroupBillInfoCopyToPickUpReservation);
		wait.until(ExpectedConditions.visibilityOf(group.GroupBillInfoCopyToPickUpReservation));
		if (isCopyPickupRes) {
			if (!group.GroupBillInfoCopyToPickUpReservation.isSelected()) {
				group.GroupBillInfoCopyToPickUpReservation.click();
			}
		} else {
			if (group.GroupBillInfoCopyToPickUpReservation.isSelected()) {
				group.GroupBillInfoCopyToPickUpReservation.click();
			}
		}
		test_steps.add("Check Copy PickUp Reservation : " + isCopyPickupRes);
		groupLogger.info("Check Copy PickUp Reservation : " + isCopyPickupRes);

		Wait.explicit_wait_visibilityof_webelement_120(oldGroup.oldGroup_PaymentMethod, driver);
		new Select(oldGroup.oldGroup_PaymentMethod).selectByVisibleText(paymentType);
		test_steps.add("Selected Payment Method : " + paymentType);
		groupLogger.info("Selected Payment Method : " + paymentType);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupBilling_AccountNo, driver);
		group.GroupBilling_AccountNo.sendKeys(Keys.chord(Keys.CONTROL, "a"), accNo);
		test_steps.add("Entered Account No : " + accNo);
		groupLogger.info("Entered Account No : " + accNo);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupBilling_ExpiryDate, driver);
		group.GroupBilling_ExpiryDate.sendKeys(Keys.chord(Keys.CONTROL, "a"), expiryDate);
		test_steps.add("Entered Expiry Date : " + expiryDate);
		groupLogger.info("Entered Expiry Date : " + expiryDate);

		return test_steps;

	}

	public ArrayList<String> roomingListBillingInfoClick(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_OldGroups group = new Elements_OldGroups(driver);

		// driver.switchTo().frame("dialog-body0");

		Wait.waitUntilPresenceOfElementLocated(OR.Rooming_List_Popup, driver);
		Wait.explicit_wait_visibilityof_webelement_120(group.oldGroups_ClickBillingInfo_Roominglistpopup, driver);
		Utility.ScrollToElement(group.oldGroups_ClickBillingInfo_Roominglistpopup, driver);
		Wait.explicit_wait_elementToBeClickable(group.oldGroups_ClickBillingInfo_Roominglistpopup, driver);
		group.oldGroups_ClickBillingInfo_Roominglistpopup.click();
		test_steps.add("Billing Info Button Clicked");
		groupLogger.info("Billing Info Button Clicked");

		// driver.switchTo().defaultContent();
		return test_steps;

	}

	public ArrayList<String> verifyRoomingListBillingInfo(WebDriver driver, String firstName, String lastName,
			String phonenumber, String address1, String city, String country, String state, String postalcode,
			String paymentMethod, String accountNumber, String expiryDate, boolean isAvailable)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);

		// driver.switchTo().frame("dialog-body1");
		Wait.waitUntilPresenceOfElementLocated("//*[@id='frmRoomingListBillingInfo']", driver);

		if (isAvailable) {
			Wait.explicit_wait_visibilityof_webelement_120(group.RoomingListBillingInfo_FirstName, driver);
			String enteredFirstName = group.RoomingListBillingInfo_FirstName.getAttribute("value");
			assertEquals(enteredFirstName, firstName, "Failed : First Name Not Matched");

			test_steps.add("Successfully Verified First Name in Billing Info Popup: " + enteredFirstName);
			groupLogger.info("Successfully Verified First Name in Billing Info Popup: " + enteredFirstName);

			Wait.explicit_wait_visibilityof_webelement_120(group.RoomingListBillingInfo_LastName, driver);
			String enteredLastName = group.RoomingListBillingInfo_LastName.getAttribute("value");
			assertEquals(enteredLastName, lastName, "Failed : Last Name Not Matched");

			test_steps.add("Successfully Verified Last Name in Billing Info Popup: " + enteredLastName);
			groupLogger.info("Successfully Verified Last Name in Billing Info Popup: " + enteredLastName);

			Wait.explicit_wait_visibilityof_webelement_120(group.RoomingListBillingInfo_PhoneNo, driver);
			String enteredPhoneNo = group.RoomingListBillingInfo_PhoneNo.getAttribute("value");
			enteredPhoneNo = enteredPhoneNo.replace("(", "");
			enteredPhoneNo = enteredPhoneNo.replace(")", "");
			enteredPhoneNo = enteredPhoneNo.replace("-", "");
			enteredPhoneNo = enteredPhoneNo.replace(" ", "");
			assertEquals(enteredPhoneNo, phonenumber, "Failed : PhoneNo Not Matched");

			test_steps.add("Successfully Verified PhoneNo in Billing Info Popup: " + enteredPhoneNo);
			groupLogger.info("Successfully Verified PhoneNo in Billing Info Popup: " + enteredPhoneNo);

			Wait.explicit_wait_visibilityof_webelement_120(group.RoomingListBillingInfo_Address1, driver);
			String enteredAddress1 = group.RoomingListBillingInfo_Address1.getAttribute("value");
			assertEquals(enteredAddress1, address1, "Failed : Address Not Matched");

			test_steps.add("Successfully Verified Address in Billing Info Popup: " + enteredAddress1);
			groupLogger.info("Successfully Verified Address in Billing Info Popup: " + enteredAddress1);

			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.id("drpBilling_countryID")), driver);
			String selectedCountry = new Select(driver.findElement(By.id("drpBilling_countryID")))
					.getFirstSelectedOption().getText();
			assertEquals(selectedCountry, country, "Failed Selected Country Not Matched");

			test_steps.add("Successfully Verified Selected Country in Billing Info Popup: " + selectedCountry);
			groupLogger.info("Successfully Verified Selected Country in Billing Info Popup: " + selectedCountry);

			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.id("drpBilling_territoryID")), driver);
			String selectedState = new Select(driver.findElement(By.id("drpBilling_territoryID")))
					.getFirstSelectedOption().getText();
			assertEquals(selectedState, state, "Failed Selected State Not Matched");

			test_steps.add("Successfully Verified Selected Sate in Billing Info Popup: " + selectedState);
			groupLogger.info("Successfully Verified Selected State in Billing Info Popup: " + selectedState);

			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.id("txtBilling_city")), driver);
			String enteredCity = driver.findElement(By.id("txtBilling_city")).getAttribute("value");
			assertEquals(enteredCity, city, "Failed : City Not Matched");

			test_steps.add("Successfully Verified City in Billing Info Popup: " + enteredCity);
			groupLogger.info("Successfully Verified City in Billing Info Popup: " + enteredCity);

			Elements_AdvanceGroups group1 = new Elements_AdvanceGroups(driver);

			Wait.explicit_wait_visibilityof_webelement_120(group1.BillingInfo_PaymentMethod, driver);
			String selectedPaymentMethod = new Select(group1.BillingInfo_PaymentMethod).getFirstSelectedOption()
					.getText();
			assertEquals(selectedPaymentMethod, paymentMethod, "Failed Selected Payment Method Not Matched");

			test_steps.add(
					"Successfully Verified Selected Payment Method in Billing Info Popup: " + selectedPaymentMethod);
			groupLogger.info(
					"Successfully Verified Selected Payment Method in Billing Info Popup: " + selectedPaymentMethod);

			Wait.explicit_wait_visibilityof_webelement_120(group.RoomingListBilling_AccountNo, driver);
			String enteredAccNo = group.RoomingListBilling_AccountNo.getAttribute("value");
			assertEquals(enteredAccNo.substring(enteredAccNo.length() - 4),
					accountNumber.substring(accountNumber.length() - 4), "Failed : Account Number Not Matched");

			// assertEquals(enteredAccNo.substring(enteredAccNo.length()-4),
			// accountNumber.substring(accountNumber.length()-4),"Failed :
			// Account Number Not Matched");

			test_steps.add("Successfully Verified Account Number in Billing Info Popup: " + enteredAccNo);
			groupLogger.info("Successfully Verified Account Number in Billing Info Popup: " + enteredAccNo);

			Wait.explicit_wait_visibilityof_webelement_120(group.RoomingListBilling_ExpiryDate, driver);
			String enteredExpiryDate = group.RoomingListBilling_ExpiryDate.getAttribute("value");
			assertEquals(enteredExpiryDate, expiryDate, "Failed : Expiry Date Not Matched");

			test_steps.add("Successfully Verified Expiry Date in Billing Info Popup: " + enteredExpiryDate);
			groupLogger.info("Successfully Verified Expiry Date in Billing Info Popup: " + enteredExpiryDate);
		} else {

			Wait.explicit_wait_visibilityof_webelement_120(group.RoomingListBillingInfo_PhoneNo, driver);
			String enteredPhoneNo = group.RoomingListBillingInfo_PhoneNo.getAttribute("value");
			enteredPhoneNo = enteredPhoneNo.replace("(", "");
			enteredPhoneNo = enteredPhoneNo.replace(")", "");
			enteredPhoneNo = enteredPhoneNo.replace("-", "");
			enteredPhoneNo = enteredPhoneNo.replace(" ", "");
			assertNotEquals(enteredPhoneNo, phonenumber, "Failed : PhoneNo is Matched");

			test_steps.add("Successfully Verified PhoneNo in Billing Info Popup");
			groupLogger.info("Successfully Verified PhoneNo in Billing Info Popup");

			Wait.explicit_wait_visibilityof_webelement_120(group.RoomingListBillingInfo_Address1, driver);
			String enteredAddress1 = group.RoomingListBillingInfo_Address1.getAttribute("value");
			assertNotEquals(enteredAddress1, address1, "Failed : Address is Matched");

			test_steps.add("Successfully Verified Address in Billing Info Popup");
			groupLogger.info("Successfully Verified Address in Billing Info Popup");

			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.id("drpBilling_territoryID")), driver);
			String selectedState = new Select(driver.findElement(By.id("drpBilling_territoryID")))
					.getFirstSelectedOption().getText();
			assertNotEquals(selectedState, state, "Failed Selected State is Matched");

			test_steps.add("Successfully Verified Selected Sate in Billing Info Popup");
			groupLogger.info("Successfully Verified Selected State in Billing Info Popup");

			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.id("txtBilling_city")), driver);
			String enteredCity = driver.findElement(By.id("txtBilling_city")).getAttribute("value");
			assertNotEquals(enteredCity, city, "Failed : City is Matched");

			test_steps.add("Successfully Verified City in Billing Info Popup");
			groupLogger.info("Successfully Verified City in Billing Info Popup");

			Elements_AdvanceGroups group1 = new Elements_AdvanceGroups(driver);

			Wait.explicit_wait_visibilityof_webelement_120(group1.BillingInfo_PaymentMethod, driver);
			String selectedPaymentMethod = new Select(group1.BillingInfo_PaymentMethod).getFirstSelectedOption()
					.getText();
			assertNotEquals(selectedPaymentMethod, paymentMethod, "Failed Selected Payment Method is Matched");

			test_steps.add("Successfully Verified Selected Payment Method in Billing Info Popup");
			groupLogger.info("Successfully Verified Selected Payment Method in Billing Info Popup");

			Wait.explicit_wait_visibilityof_webelement_120(group.RoomingListBilling_AccountNo, driver);
			String enteredAccNo = group.RoomingListBilling_AccountNo.getAttribute("value");
			assertNotEquals(enteredAccNo, accountNumber, "Failed : Account Number is Matched");

			test_steps.add("Successfully Verified Account Number in Billing Info Popup");
			groupLogger.info("Successfully Verified Account Number in Billing Info Popup");

			Wait.explicit_wait_visibilityof_webelement_120(group.RoomingListBilling_ExpiryDate, driver);
			String enteredExpiryDate = group.RoomingListBilling_ExpiryDate.getAttribute("value");
			assertNotEquals(enteredExpiryDate, expiryDate, "Failed : Expiry Date is Matched");

			test_steps.add("Successfully Verified Expiry Date in Billing Info Popup");
			groupLogger.info("Successfully Verified Expiry Date in Billing Info Popup");
		}
		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> roomingListBillingInfoSaveClick(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();

		Elements_AdvanceGroups group1 = new Elements_AdvanceGroups(driver);

		// driver.switchTo().frame("dialog-body1");
		Wait.explicit_wait_visibilityof_webelement_120(group1.BillingInfo_Save, driver);
		Utility.ScrollToElement(group1.BillingInfo_Save, driver);
		Wait.explicit_wait_elementToBeClickable(group1.BillingInfo_Save, driver);
		group1.BillingInfo_Save.click();
		test_steps.add("Billing Info Save Button Clicked");
		groupLogger.info("Billing Info Save Button Clicked");

		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> roomingListPopup_RoomPickup(WebDriver driver, String FirstName, String LastName,
			String RoomClass, String Amount) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_OldGroups group = new Elements_OldGroups(driver);

		// driver.switchTo().frame("dialog-body0");

		Wait.waitUntilPresenceOfElementLocated(OR.Rooming_List_Popup, driver);

		Wait.waitUntilPresenceOfElementLocated(OR.oldGroups_Enter_FirstName_Roominglistpopup, driver);
		group.oldGroups_Enter_FirstName_Roominglistpopup.sendKeys(FirstName);
		test_steps.add("Entered First Name in Rooming List Popup : " + FirstName);
		groupLogger.info("Entered First Name in Rooming List Popup : " + FirstName);

		Wait.waitUntilPresenceOfElementLocated(OR.oldGroups_Enter_LastName_Roominglistpoup, driver);
		group.oldGroups_Enter_LastName_Roominglistpoup.sendKeys(LastName);
		test_steps.add("Entered Last Name in Rooming List Popup : " + LastName);
		groupLogger.info("Entered Last Name in Rooming List Popup : " + LastName);

		Wait.explicit_wait_visibilityof_webelement_120(group.oldGroups_Select_Roomclass_Roominglistpoup, driver);
		new Select(group.oldGroups_Select_Roomclass_Roominglistpoup).selectByVisibleText(RoomClass);
		test_steps.add("Selected RoomClass in Rooming List Popup : " + RoomClass);
		groupLogger.info("Selected RoomClass in Rooming List Popup : " + RoomClass);

		String roomnum = OR.oldGroups_Select_RoomNo_Roominglistpopup + "/option";
		System.out.println(roomnum);
		int count = driver.findElements(By.xpath(roomnum)).size();
		Random random = new Random();
		int randomNumber = random.nextInt(count - 1) + 1;
		System.out.println("count : " + count);
		System.out.println("randomNumber : " + randomNumber);
		new Select(group.oldGroups_Select_RoomNo_Roominglistpopup).selectByIndex(randomNumber);

		test_steps.add("Selected RoomNo in Rooming List Popup : " + randomNumber);
		groupLogger.info("Selected RoomNo in Rooming List Popup : " + randomNumber);

		// Wait.waitUntilPresenceOfElementLocated(OR.oldGroups_Amount_Roominglistpopup,
		// driver);
		Wait.explicit_wait_visibilityof_webelement_120(group.oldGroups_Amount_Roominglistpopup, driver);
		Utility.ScrollToElement(group.oldGroups_Amount_Roominglistpopup, driver);
		Wait.explicit_wait_elementToBeClickable(group.oldGroups_Amount_Roominglistpopup, driver);
		group.oldGroups_Amount_Roominglistpopup.click();
		group.oldGroups_Amount_Roominglistpopup.clear();
		group.oldGroups_Amount_Roominglistpopup.sendKeys(Amount);
		test_steps.add("Entered Amount in Rooming List Popup : " + Amount);
		groupLogger.info("Entered Amount in Rooming List Popup : " + Amount);

		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public String pickUp_getResNo(WebDriver driver) throws InterruptedException {

		// driver.switchTo().frame("dialog-body1");
		Wait.waitUntilPresenceOfElementLocated("//*[@id='frmRoomingListSummary']", driver);
		String resNoPath = "//*[@id='dgRoomingList']/tbody/tr[2]/td[1]";
		Wait.explicit_wait_visibilityof_webelement_600(driver.findElement(By.xpath(resNoPath)), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(resNoPath)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(resNoPath)), driver);
		String resNo = driver.findElement(By.xpath(resNoPath)).getText();

		// driver.switchTo().defaultContent();
		return resNo;
	}

	public ArrayList<String> checkCopyToPickUpReservation(WebDriver driver, boolean isCopyPickupRes)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		Utility.ScrollToElement(group.GroupBillInfoCopyToPickUpReservation, driver);
		Wait.WaitForElement(driver, OR.GroupBillInfoCopyToPickUpReservation);
		Wait.explicit_wait_visibilityof_webelement_120(group.GroupBillInfoCopyToPickUpReservation, driver);
		if (isCopyPickupRes) {
			if (!group.GroupBillInfoCopyToPickUpReservation.isSelected()) {
				group.GroupBillInfoCopyToPickUpReservation.click();
			}
		} else {
			if (group.GroupBillInfoCopyToPickUpReservation.isSelected()) {
				group.GroupBillInfoCopyToPickUpReservation.click();
			}
		}
		test_steps.add("Check Copy PickUp Reservation : " + isCopyPickupRes);
		groupLogger.info("Check Copy PickUp Reservation : " + isCopyPickupRes);

		return test_steps;
	}

	public ArrayList<String> verifyCopyToPickUpReservation(WebDriver driver, boolean isCopyPickupRes)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		Utility.ScrollToElement(group.GroupBillInfoCopyToPickUpReservation, driver);
		Wait.WaitForElement(driver, OR.GroupBillInfoCopyToPickUpReservation);
		Wait.explicit_wait_visibilityof_webelement_120(group.GroupBillInfoCopyToPickUpReservation, driver);
		assertEquals(group.GroupBillInfoCopyToPickUpReservation.isSelected(), isCopyPickupRes,
				"Failed To verify Copy To Pickup Reservation");
		test_steps.add("Successfully Verified Copy To Pickup Reservation : " + isCopyPickupRes);
		groupLogger.info("Successfully Verified Copy To Pickup Reservation : " + isCopyPickupRes);

		return test_steps;
	}

	public ArrayList<String> roomingListPickUpCancel(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		// driver.switchTo().frame("dialog-body0");

		Wait.waitUntilPresenceOfElementLocated(OR.Rooming_List_Popup, driver);

		Wait.waitUntilPresenceOfElementLocated(OR.oldGroup_NewBlock_Cancel, driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(OR.oldGroup_NewBlock_Cancel)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(OR.oldGroup_NewBlock_Cancel)), driver);
		driver.findElement(By.xpath(OR.oldGroup_NewBlock_Cancel)).click();
		test_steps.add("PickUp Cancel Button Clicked");
		groupLogger.info("PickUp Cancel Button Clicked");
		loadingImage(driver);
		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> roomingListBillingInfoCancel(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		// driver.switchTo().frame("dialog-body1");

		Wait.waitUntilPresenceOfElementLocated("//*[@id='frmRoomingListBillingInfo']", driver);

		Wait.waitUntilPresenceOfElementLocated(OR.oldGroup_NewBlock_Cancel, driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(OR.oldGroup_NewBlock_Cancel)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(OR.oldGroup_NewBlock_Cancel)), driver);
		driver.findElement(By.xpath(OR.oldGroup_NewBlock_Cancel)).click();
		test_steps.add("Rooming List Billing Info Cancel Button Clicked");
		groupLogger.info("Rooming List Billing Info Cancel Button Clicked");

		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> verifyReservationInResTab(WebDriver driver, String ReservationNumber, int lineNo) {
		ArrayList<String> test_steps = new ArrayList<>();
		int headerLines = 2;
		lineNo = lineNo + headerLines;

		String resNoPath = "//*[@id='MainContent_ucReservationControl1_dgReservationList']/tbody/tr[" + lineNo
				+ "]/td[2]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(resNoPath)), driver);

		String resNo = driver.findElement(By.xpath(resNoPath)).getText();
		assertEquals(resNo, ReservationNumber, "Failed Reservation Not Matched");
		test_steps.add("Successfully Verified Reservation Number " + resNo);
		groupLogger.info("Successfully Verified Reservation Number " + resNo);

		return test_steps;
	}

	public ArrayList<String> verifyLineItems(WebDriver driver, String LineCategory, String LineDescription,
			String LineAmount, String LineNO) {
		ArrayList<String> test_steps = new ArrayList<>();

		Wait.explicit_wait_visibilityof_webelement_120(
				driver.findElement(By.id("MainContent_Folio1_dgLineItems_lbldateeffective_" + LineNO)), driver);
		String lineDate = driver.findElement(By.id("MainContent_Folio1_dgLineItems_lbldateeffective_" + LineNO))
				.getText();
		assertEquals(Utility.parseDate(lineDate, "EEE, dd-MMM-yyyy", "EEE, dd-MMM-yyyy"),
				Utility.getCurrentDate("EEE, dd-MMM-yyyy"), "Failed to Verify Folio Line Date");
		test_steps.add("Successfully Verified Date Verified");
		groupLogger.info("Successfully Verified Date Verified");

		String lineCat = driver.findElement(By.id("MainContent_Folio1_dgLineItems_lblLedgeraccountname_" + LineNO))
				.getText();
		assertEquals(lineCat, LineCategory, "Failed to Verify Folio Line Category");
		test_steps.add("Successfully Verified Line Category");
		groupLogger.info("Successfully Verified Line Category");

		String lineDesc = driver.findElement(By.id("MainContent_Folio1_dgLineItems_lbtnDisplaycaption_" + LineNO))
				.getText();
		assertTrue(lineDesc.contains(LineDescription), "Failed to Verify Folio Line Description");
		test_steps.add("Successfully Verified Line Description");
		groupLogger.info("Successfully Verified Line Description");

		String lineAmount = driver.findElement(By.id("MainContent_Folio1_dgLineItems_lblAmount_" + LineNO)).getText();
		assertEquals(Float.parseFloat(lineAmount), Float.parseFloat(LineAmount), "Failed to Verify Folio Line Amount");
		test_steps.add("Successfully Verified Line Amount");
		groupLogger.info("Successfully Verified Line Amount");

		return test_steps;
	}

	public ArrayList<String> checkVaryRoomsByDate(WebDriver driver, boolean isCheck) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);

		// driver.switchTo().frame("MainContent_Iframe_accountpicker");

		Wait.WaitForElement(driver, OR.CheckVaryRoomsByDate);
		Wait.explicit_wait_visibilityof_webelement_120(group.CheckVaryRoomsByDate, driver);
		Utility.ScrollToElement(group.CheckVaryRoomsByDate, driver);
		if (isCheck) {
			if (!group.CheckVaryRoomsByDate.isSelected()) {
				group.CheckVaryRoomsByDate.click();
			}
		} else {
			if (group.CheckVaryRoomsByDate.isSelected()) {
				group.CheckVaryRoomsByDate.click();
			}
		}

		test_steps.add("Check Vary Rooms By Date : " + isCheck);
		groupLogger.info("Check Vary Rooms By Date : " + isCheck);

		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> increaseRoomBlockedCount(WebDriver driver, String RoomClass, int increaseBy)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);

		// driver.switchTo().frame("MainContent_Iframe_accountpicker");

		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		List<WebElement> GetRoomclassNames = AdvGroup.GetRoomclasses;

		int index = 1;

		for (int i = 0; i < GetRoomclassNames.size(); i++) {
			if (GetRoomclassNames.get(i).getText().equals(RoomClass)) {
				index = i + 1;
				driver.findElement(By.xpath(
						"//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index + "]/td[3]/input"));
				Wait.wait1Second();
				// test_steps.add("Select room block for : Junior Suites for : 1
				// days");

				break;
			}
		}

		String inputPath = "//*[@id='rateQuoteGridWithPlainKO']//table/tbody/tr[" + index + "]/td[2]/input";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(inputPath)), driver);
		String previousValue = driver.findElement(By.xpath(inputPath)).getAttribute("value");
		int newValue = Integer.parseInt(previousValue) + increaseBy;
		driver.findElement(By.xpath(inputPath)).sendKeys(Keys.chord(Keys.CONTROL, "a"), newValue + "");
		test_steps.add("Rooms Blocked Increased : " + newValue);
		groupLogger.info("Rooms Blocked Increased : " + newValue);

		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> decreaseRoomBlockedCount(WebDriver driver, String RoomClass, int decreaseBy)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);

		// driver.switchTo().frame("MainContent_Iframe_accountpicker");

		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		List<WebElement> GetRoomclassNames = AdvGroup.GetRoomclasses;

		int index = 1;

		for (int i = 0; i < GetRoomclassNames.size(); i++) {
			if (GetRoomclassNames.get(i).getText().equals(RoomClass)) {
				index = i + 1;
				driver.findElement(By.xpath(
						"//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index + "]/td[3]/input"));
				Wait.wait1Second();
				// test_steps.add("Select room block for : Junior Suites for : 1
				// days");

				break;
			}
		}

		String inputPath = "//*[@id='rateQuoteGridWithPlainKO']//table/tbody/tr[" + index + "]/td[2]/input";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(inputPath)), driver);
		String previousValue = driver.findElement(By.xpath(inputPath)).getAttribute("value");
		int newValue = Integer.parseInt(previousValue) - decreaseBy;
		driver.findElement(By.xpath(inputPath)).sendKeys(Keys.chord(Keys.CONTROL, "a"), newValue + "");
		test_steps.add("Rooms Blocked Decreased : " + newValue);
		groupLogger.info("Rooms Blocked Decreased : " + newValue);

		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> editDialogSave(WebDriver driver) throws InterruptedException {
		// driver.switchTo().frame("MainContent_Iframe_accountpicker");
		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.Group_Save);
		Wait.wait3Second();
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Save, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Save, driver);
		Utility.ScrollToElement(group.Group_Save, driver);
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();", group.Group_Save);
		//
		//
		group.Group_Save.click();

		test_steps.add("Click on Save");
		groupLogger.info("Click on Save");
		try {
			loadingImage(driver);
		} catch (Exception e) {
			System.out.println("No Loading");
		}
		// driver.switchTo().defaultContent();
		return test_steps;

	}

	public ArrayList<String> editBlockRoomNightDialog(WebDriver driver) throws InterruptedException {
		// *[@id='btnContinue']
		// driver.switchTo().frame("MainContent_Iframe_accountpicker");
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		try {
			Wait.explicit_wait_visibilityof_webelement_120(group.Click_Continue_Block_Night, driver);
			Wait.explicit_wait_elementToBeClickable(group.Click_Continue_Block_Night, driver);
			Utility.ScrollToElement(group.Click_Continue_Block_Night, driver);
			group.Click_Continue_Block_Night.click();
			test_steps.add("Click on Countinue Block Night");
			groupLogger.info("Click on Countinue Block Night");
		} catch (Exception e) {
			System.out.println("Block Room Night Dialog not appear");
		}
		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> editDialogDone(WebDriver driver) throws InterruptedException {
		// driver.switchTo().frame("MainContent_Iframe_accountpicker");
		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.Group_Done);
		Wait.wait3Second();
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Done, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Done, driver);
		Utility.ScrollToElement(group.Group_Done, driver);
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();", group.Group_Save);
		//
		//
		group.Group_Done.click();

		test_steps.add("Click on Done");
		groupLogger.info("Click on Done");
		try {
			loadingImage(driver);
		} catch (Exception e) {
			System.out.println("No Loading");
		}
		// driver.switchTo().defaultContent();
		return test_steps;

	}

	public void loadingImage(WebDriver driver) {
		try {
			String path = "//*[@id='InnerFreezePane']/img";
			Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(path)), 120);
			if (driver.findElement(By.xpath(path)).isDisplayed()) {
				Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(path)), 120);
			}
		} catch (Exception e) {
			System.out.println("No Loading");
		}
	}

	public String getBlocked(WebDriver driver, String RoomClass) {

		String blockedPath = "//a[text()='" + RoomClass + "']/parent::div/parent::div/preceding-sibling::div[4]/div";

		Wait.waitUntilPresenceOfElementLocated(blockedPath, driver);
		Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(blockedPath)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(blockedPath)), driver);

		return driver.findElement(By.xpath(blockedPath)).getText();
	}

	public ArrayList<String> verifyRoomingListSummaryInfo(WebDriver driver, String BlockName, String Status,
			String PickUp) {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		// driver.switchTo().frame("dialog-body1");
		Wait.waitUntilPresenceOfElementLocated("//*[@id='frmRoomingListSummary']", driver);

		Wait.explicit_wait_visibilityof_webelement_120(group.RoomingListPickupSummary_BlockName, driver);
		String foundBlockName = group.RoomingListPickupSummary_BlockName.getText();
		assertEquals(foundBlockName, BlockName, "Failed Block Name Not Matched in Rooming List Summary");
		test_steps.add("Successfully Verified Block Name in Rooming List Summary : " + foundBlockName);
		groupLogger.info("Successfully Verified Block Name in Rooming List Summary : " + foundBlockName);

		Wait.explicit_wait_visibilityof_webelement_120(group.RoomingListPickupSummary_ArivalDate, driver);
		String foundArivalDate = group.RoomingListPickupSummary_ArivalDate.getText();
		assertEquals(Utility.parseDate(foundArivalDate, "MMM dd, yyyy", "MMM dd, yyyy"),
				Utility.getCurrentDate("MMM dd, yyyy"), "Failed Arival Date Not Matched in Rooming List Summary");
		test_steps.add("Successfully Verified Arival Date in Rooming List Summary : " + foundArivalDate);
		groupLogger.info("Successfully Verified Arival Date in Rooming List Summary : " + foundArivalDate);

		Wait.explicit_wait_visibilityof_webelement_120(group.RoomingListPickupSummary_DepartDate, driver);
		String foundDepartDate = group.RoomingListPickupSummary_DepartDate.getText();
		assertEquals(Utility.parseDate(foundDepartDate, "MMM dd, yyyy", "MMM dd, yyyy"),
				Utility.GetNextDate(1, "MMM dd, yyyy"), "Failed Depart Date Not Matched in Rooming List Summary");
		test_steps.add("Successfully Verified Depart Date in Rooming List Summary : " + foundDepartDate);
		groupLogger.info("Successfully Verified Depart Date in Rooming List Summary : " + foundDepartDate);

		Wait.explicit_wait_visibilityof_webelement_120(group.RoomingListPickupSummary_Status, driver);
		String foundStatus = group.RoomingListPickupSummary_Status.getText();
		assertEquals(foundStatus, Status, "Failed Status Not Matched in Rooming List Summary");
		test_steps.add("Successfully Verified Status in Rooming List Summary : " + foundStatus);
		groupLogger.info("Successfully Verified Status in Rooming List Summary : " + foundStatus);

		Wait.explicit_wait_visibilityof_webelement_120(group.RoomingListPickupSummary_PickUp, driver);
		String foundPickUp = group.RoomingListPickupSummary_PickUp.getText();
		foundPickUp = foundPickUp.replace("%", "");
		assertEquals(foundPickUp, PickUp, "Failed PickUp Not Matched in Rooming List Summary");
		test_steps.add("Successfully Verified PickUp in Rooming List Summary : " + foundPickUp);
		groupLogger.info("Successfully Verified PickUp in Rooming List Summary : " + foundPickUp);

		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> verifyRoomingListPickUpSummary_Reservation(WebDriver driver, String RoomClassName,
			String RoomNo, String paymentMethod, String accountNumber, String expiryDate) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		// driver.switchTo().frame("dialog-body1");
		Wait.waitUntilPresenceOfElementLocated("//*[@id='frmRoomingListSummary']", driver);

		String arivalDatePath = "//*[@id='dgRoomingList']/tbody/tr[2]/td[3]";
		String departDatePath = "//*[@id='dgRoomingList']/tbody/tr[2]/td[4]";
		String classNameRoomNoPath = "//*[@id='dgRoomingList']/tbody/tr[2]/td[5]";
		String payMethodPath = "//*[@id='dgRoomingList']/tbody/tr[2]/td[6]";
		String cardNoPath = "//*[@id='dgRoomingList']/tbody/tr[2]/td[7]/span";
		String expiryDatePath = "//*[@id='dgRoomingList']/tbody/tr[2]/td[8]";

		Wait.explicit_wait_visibilityof_webelement_600(driver.findElement(By.xpath(arivalDatePath)), driver);
		String foundArivalDate = driver.findElement(By.xpath(arivalDatePath)).getText();
		assertEquals(Utility.parseDate(foundArivalDate, "MMM dd, yyyy", "MMM dd, yyyy"),
				Utility.getCurrentDate("MMM dd, yyyy"), "Failed Arival Date Not Matched in Rooming List Summary");
		test_steps.add("Successfully Verified Arival Date in Rooming List Summary Reservation : " + foundArivalDate);
		groupLogger.info("Successfully Verified Arival Date in Rooming List Summary Reservation : " + foundArivalDate);

		Wait.explicit_wait_visibilityof_webelement_600(driver.findElement(By.xpath(departDatePath)), driver);
		String foundDepartDate = driver.findElement(By.xpath(departDatePath)).getText();
		assertEquals(Utility.parseDate(foundDepartDate, "MMM dd, yyyy", "MMM dd, yyyy"),
				Utility.GetNextDate(1, "MMM dd, yyyy"), "Failed Arival Date Not Matched in Rooming List Summary");
		test_steps.add("Successfully Verified Depart Date in Rooming List Summary Reservation : " + foundDepartDate);
		groupLogger.info("Successfully Verified Depart Date in Rooming List Summary Reservation : " + foundDepartDate);

		Wait.explicit_wait_visibilityof_webelement_600(driver.findElement(By.xpath(classNameRoomNoPath)), driver);
		String foundClassRoom = driver.findElement(By.xpath(classNameRoomNoPath)).getText();
		assertEquals(foundClassRoom, RoomClassName + " : " + RoomNo, "Failed RoomClass and RoomNO not Matched");
		test_steps.add(
				"Successfully Verified RoomClass and RoomNO in Rooming List Summary Reservation : " + foundClassRoom);
		groupLogger.info(
				"Successfully Verified RoomClass and RoomNO in Rooming List Summary Reservation : " + foundClassRoom);

		Wait.explicit_wait_visibilityof_webelement_600(driver.findElement(By.xpath(payMethodPath)), driver);
		String foundPayMethod = driver.findElement(By.xpath(payMethodPath)).getText();
		assertEquals(foundPayMethod, paymentMethod, "Failed Payment Method not Matched");
		test_steps.add("Successfully Verified Payment Method in Rooming List Summary Reservation : " + foundPayMethod);
		groupLogger
				.info("Successfully Verified Payment Method in Rooming List Summary Reservation : " + foundPayMethod);

		Wait.explicit_wait_visibilityof_webelement_600(driver.findElement(By.xpath(cardNoPath)), driver);
		String foundCardNo = driver.findElement(By.xpath(cardNoPath)).getText();
		assertEquals(foundCardNo.substring(foundCardNo.length() - 4),
				accountNumber.substring(accountNumber.length() - 4), "Failed Card No not Matched");
		test_steps.add("Successfully Verified Card NO in Rooming List Summary Reservation : " + foundCardNo);
		groupLogger.info("Successfully Verified Card No in Rooming List Summary Reservation : " + foundCardNo);

		Wait.explicit_wait_visibilityof_webelement_600(driver.findElement(By.xpath(expiryDatePath)), driver);
		String foundExpiryDate = driver.findElement(By.xpath(expiryDatePath)).getText();
		assertEquals(foundExpiryDate, expiryDate, "Failed Card No not Matched");
		test_steps.add("Successfully Verified Expiry Date in Rooming List Summary Reservation : " + foundExpiryDate);
		groupLogger.info("Successfully Verified Expiry Date in Rooming List Summary Reservation : " + foundExpiryDate);

		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public String getSelectedRoomNo(WebDriver driver) {

		// driver.switchTo().frame("dialog-body0");
		Elements_OldGroups group = new Elements_OldGroups(driver);

		Wait.waitUntilPresenceOfElementLocated(OR.Rooming_List_Popup, driver);

		Wait.explicit_wait_visibilityof_webelement_150(group.oldGroups_Select_RoomNo_Roominglistpopup, driver);
		String roomNo = new Select(group.oldGroups_Select_RoomNo_Roominglistpopup).getFirstSelectedOption().getText();

		// driver.switchTo().defaultContent();

		return roomNo;
	}

	public ArrayList<String> VerifyRoomingListPopup_RoomPickup(WebDriver driver, String Country, String State,
			String City, String paymentMethod, String accountNumber, String expiryDate) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();

		driver.switchTo().frame("dialog-body1");
		Wait.waitUntilPresenceOfElementLocated("//*[@id='frmRoomingListBillingInfo']", driver);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.id("drpBilling_countryID")), driver);
		Assert.assertEquals(
				new Select(driver.findElement(By.id("drpBilling_countryID"))).getFirstSelectedOption().getText(),
				Country, "Failed Country Not Matched in Billing Info");
		test_steps.add("Successfully Verified Selected Country in Billing Info Popup: " + Country);
		groupLogger.info("Successfully Verified Selected Country in Billing Info Popup: " + Country);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.id("drpBilling_territoryID")), driver);
		Assert.assertEquals(
				new Select(driver.findElement(By.id("drpBilling_territoryID"))).getFirstSelectedOption().getText(),
				State, "Failed State Not Matched in Billing Info");
		test_steps.add("Successfully Verified Selected State in Billing Info Popup: " + State);
		groupLogger.info("Successfully Verified Selected State in Billing Info Popup: " + State);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.id("txtBilling_city")), driver);
		String foundCity = driver.findElement(By.id("txtBilling_city")).getAttribute("value");
		assertEquals(foundCity, City, "Failed : City in Billing Info Popup");
		test_steps.add("Successfully Verified City : " + City);
		groupLogger.info("Successfully Verified City : " + City);

		Elements_AdvanceGroups group1 = new Elements_AdvanceGroups(driver);

		Wait.explicit_wait_visibilityof_webelement_120(group1.BillingInfo_PaymentMethod, driver);
		String selectedPayType = new Select(group1.BillingInfo_PaymentMethod).getFirstSelectedOption().getText();
		assertEquals(selectedPayType, paymentMethod, "Failed : Payment Method Not Matched");
		test_steps.add("Successfully Verified Payment Method : " + paymentMethod);
		groupLogger.info("Successfully Verified Payment Method : " + paymentMethod);

		Elements_Groups grp = new Elements_Groups(driver);
		Wait.explicit_wait_visibilityof_webelement_120(grp.RoomingListBilling_AccountNo, driver);
		String enteredAccNo = grp.RoomingListBilling_AccountNo.getAttribute("value");
		enteredAccNo = enteredAccNo.substring(enteredAccNo.length() - 4);
		assertEquals(enteredAccNo, accountNumber.substring(accountNumber.length() - 4),
				"Failed : Account Number Not Matched");

		test_steps.add("Successfully Verified Account Number in Billing Info Popup: " + enteredAccNo);
		groupLogger.info("Successfully Verified Account Number in Billing Info Popup: " + enteredAccNo);

		Wait.explicit_wait_visibilityof_webelement_120(grp.RoomingListBilling_ExpiryDate, driver);
		String enteredExpiryDate = grp.RoomingListBilling_ExpiryDate.getAttribute("value");
		assertEquals(enteredExpiryDate, expiryDate, "Failed : Expiry Date Not Matched");

		test_steps.add("Successfully Verified Expiry Date in Billing Info Popup: " + enteredExpiryDate);
		groupLogger.info("Successfully Verified Expiry Date in Billing Info Popup: " + enteredExpiryDate);
		Wait.explicit_wait_visibilityof_webelement_120(group1.BillingInfo_Save, driver);
		Utility.ScrollToElement(group1.BillingInfo_Save, driver);
		Wait.explicit_wait_elementToBeClickable(group1.BillingInfo_Save, driver);
		group1.BillingInfo_Save.click();
		test_steps.add("Billing Info Save Button Clicked");
		groupLogger.info("Billing Info Save Button Clicked");

		driver.switchTo().defaultContent();
		return test_steps;
	}

	public String getStatus_RoomBlockDetatil(WebDriver driver) {

		String path = "//span[contains(text(),'Status:')]";

		Wait.explicit_wait_xpath(path, driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		String pickUpPercentage = driver.findElement(By.xpath(path)).getText();
		System.out.println(pickUpPercentage);
		StringTokenizer str = new StringTokenizer(pickUpPercentage, "Status: ");
		String value = str.nextToken();
		System.out.println(value);
		return pickUpPercentage;
	}

	public ArrayList<String> verifyArriveDepartDate_RoomBlockDetail(WebDriver driver) {
		ArrayList<String> test_steps = new ArrayList<>();
		String path = "//table/tbody/tr[2]/td[1]/table/tbody/tr[2]/td/span";
		Wait.explicit_wait_xpath(path, driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(path)), driver);
		String dates = driver.findElement(By.xpath(path)).getText();
		System.out.println(dates);
		dates = dates.replace(" to ", "-");
		System.out.println(dates);
		StringTokenizer str = new StringTokenizer(dates, "-");
		String arriveDate = str.nextToken();
		String departate = str.nextToken();
		System.out.println(arriveDate);
		System.out.println(departate);
		assertEquals(Utility.parseDate(arriveDate, "MMM dd, yyyy", "MMM dd, yyyy"),
				Utility.getCurrentDate("MMM dd, yyyy"), "Failed Arival Date Not Matched in Room Block Detail");
		test_steps.add("Successfully Verified Arival Date Room Block Detail : " + arriveDate);
		groupLogger.info("Successfully Verified Arival Date Room Block Detail : " + arriveDate);

		assertEquals(Utility.parseDate(departate, "MMM dd, yyyy", "MMM dd, yyyy"),
				Utility.GetNextDate(1, "MMM dd, yyyy"), "Failed Arival Date Not Matched in Room Block Detail");
		test_steps.add("Successfully Verified Depart Date Room Block Detail : " + departate);
		groupLogger.info("Successfully Verified Depart Date Room Block Detail : " + departate);

		return test_steps;
	}

	public ArrayList<String> verifyPaymentMethodInPaymentDetailPopup(WebDriver driver, String PaymentMethod) {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		// Wait.explicit_wait_xpath("//*[@id='dialog-body0']", driver);
		// driver.switchTo().frame("dialog-body0");
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_PaymentMethod, driver);
		String foundPayMethod = new Select(group.Group_Folio_PaymentMethod).getFirstSelectedOption().getText();
		assertEquals(foundPayMethod, PaymentMethod, "Failed Payment Method not matched in Payment Detail Popup");

		test_steps.add("Successfully Verified Pyament Method in Payment Detail Popup : " + PaymentMethod);
		groupLogger.info("Successfully Verified Pyament Method in Payment Detail Popup : " + PaymentMethod);

		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> clickButtonCardInfo_PaymentDetailPopup(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		// Wait.explicit_wait_xpath("//*[@id='dialog-body0']", driver);
		// driver.switchTo().frame("dialog-body0");
		try {
			Wait.explicit_wait_visibilityof_webelement_120(group.GroupFolio_PaymentDetailPopup_CardInfoButton, driver);
			Wait.explicit_wait_elementToBeClickable(group.GroupFolio_PaymentDetailPopup_CardInfoButton, driver);
			Utility.ScrollToElement(group.GroupFolio_PaymentDetailPopup_CardInfoButton, driver);
			group.GroupFolio_PaymentDetailPopup_CardInfoButton.click();
		} catch (Exception e) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", group.GroupFolio_PaymentDetailPopup_CardInfoButton);
		}
		test_steps.add("Click on Card Info Button");
		groupLogger.info("Click on Card Info Button");

		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> verifyPaymentDetail_CardInfo(WebDriver driver, String cardName, String cardNo,
			String cardExpiry, String city, String stateCode, String postalCode) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		Wait.explicit_wait_xpath("//*[@id='dialog-body1']", driver);
		driver.switchTo().frame("dialog-body1");

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupFolio_PaymentDetail_EncriptDecriptButton, driver);
		Wait.explicit_wait_elementToBeClickable(group.GroupFolio_PaymentDetail_EncriptDecriptButton, driver);
		Utility.ScrollToElement(group.GroupFolio_PaymentDetail_EncriptDecriptButton, driver);
		group.GroupFolio_PaymentDetail_EncriptDecriptButton.click();
		test_steps.add("Click on Decript Button");
		groupLogger.info("Click on Decript Button");

		loadingImage(driver);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupFolio_PaymentDetail_NameOnCard, driver);
		String foundName = group.GroupFolio_PaymentDetail_NameOnCard.getAttribute("value");
		assertEquals(foundName, cardName, "Failed Name On Card not matched");
		test_steps.add("Successfully Verified Name on Card in Payment Detail : " + foundName);
		groupLogger.info("Successfully Verified Name on Card in Payment Detail : " + foundName);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupFolio_PaymentDetail_CardNO, driver);
		String foundCardNo = group.GroupFolio_PaymentDetail_CardNO.getAttribute("value");
		assertEquals(foundCardNo, cardNo, "Failed Card No not Matched");
		test_steps.add("Successfully Verified Card No in Payment Detail : " + foundCardNo);
		groupLogger.info("Successfully Verified Card No in Payment Detail : " + foundCardNo);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupFolio_PaymentDetail_ExpiryDate, driver);
		String foundCardExpiry = group.GroupFolio_PaymentDetail_ExpiryDate.getAttribute("value");
		assertEquals(foundCardExpiry, cardExpiry, "Failed Card Expiry not Matched");
		test_steps.add("Successfully Verified Card Expiry in Payment Detail : " + foundCardExpiry);
		groupLogger.info("Successfully Verified Card Expiry in Payment Detail : " + foundCardExpiry);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupFolio_PaymentDetail_City, driver);
		String foundCity = group.GroupFolio_PaymentDetail_City.getAttribute("value");
		assertEquals(foundCity, city, "Failed City not Matched");
		test_steps.add("Successfully Verified City in Payment Detail : " + foundCity);
		groupLogger.info("Successfully Verified City in Payment Detail : " + foundCity);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupFolio_PaymentDetail_State, driver);
		String foundState = group.GroupFolio_PaymentDetail_State.getAttribute("value");
		assertEquals(foundState, stateCode, "Failed State Code not Matched");
		test_steps.add("Successfully Verified State Code in Payment Detail : " + foundState);
		groupLogger.info("Successfully Verified State Code in Payment Detail : " + foundState);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupFolio_PaymentDetail_PostalCode, driver);
		String foundPostalCode = group.GroupFolio_PaymentDetail_PostalCode.getAttribute("value");
		assertEquals(foundPostalCode, postalCode, "Failed Postal Code not Matched");
		test_steps.add("Successfully Verified Postal Code in Payment Detail : " + foundPostalCode);
		groupLogger.info("Successfully Verified Postal Code in Payment Detail : " + foundPostalCode);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupFolio_PaymentDetail_ButtonOK, driver);
		Wait.explicit_wait_elementToBeClickable(group.GroupFolio_PaymentDetail_ButtonOK, driver);
		Utility.ScrollToElement(group.GroupFolio_PaymentDetail_ButtonOK, driver);
		group.GroupFolio_PaymentDetail_ButtonOK.click();
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", group.GroupFolio_PaymentDetail_ButtonOK);
		} catch (Exception e) {
			System.out.println("OK button already clicked");
		}
		test_steps.add("Click on OK Button");
		groupLogger.info("Click on OK Button");

		loadingImage(driver);

		driver.switchTo().defaultContent();
		return test_steps;
	}

	public String getStateCodeFromPaymentInfo(WebDriver driver) throws InterruptedException {

		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_PickBillingInfo_Button, driver);
		Utility.ScrollToElement(group.Group_Folio_PickBillingInfo_Button, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_PickBillingInfo_Button, driver);
		group.Group_Folio_PickBillingInfo_Button.click();

		Wait.explicit_wait_xpath("//*[@id='dialog-body0']", driver);
		driver.switchTo().frame("dialog-body0");
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_State, driver);
		String selectedStateCode = new Select(group.Group_Folio_BillingInfo_State).getFirstSelectedOption()
				.getAttribute("value");

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_SaveButton, driver);
		Utility.ScrollToElement(group.Group_Folio_BillingInfo_SaveButton, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_BillingInfo_SaveButton, driver);
		Utility.ScrollToElement(group.Group_Folio_BillingInfo_SaveButton, driver);
		group.Group_Folio_BillingInfo_SaveButton.click();
		groupLogger.info("Billing Info Save Button Clicked");

		loadingImage(driver);
		driver.switchTo().defaultContent();
		return selectedStateCode;

	}

	public ArrayList<String> setPaymentAmount(WebDriver driver, String PayAmount) {

		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		// Wait.explicit_wait_xpath("//*[@id='dialog-body0']", driver);
		// driver.switchTo().frame("dialog-body0");
		try {
			Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_Amount, driver);
			Wait.explicit_wait_elementToBeClickable(group.Group_Folio_Amount, driver);
			Utility.ScrollToElement(group.Group_Folio_Amount, driver);
			group.Group_Folio_Amount.click();
			group.Group_Folio_Amount.clear();
			group.Group_Folio_Amount.sendKeys(PayAmount);

		} catch (Exception e) {
			group.Group_Folio_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), PayAmount);
		}
		test_steps.add("Entered Amount : " + PayAmount);
		groupLogger.info("Entered Amount : " + PayAmount);
		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> clickProcess_PaymentDetail(WebDriver driver) throws InterruptedException {
		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		// Wait.explicit_wait_xpath("//*[@id='dialog-body0']", driver);
		// driver.switchTo().frame("dialog-body0");

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_Process, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_Process, driver);
		Utility.ScrollToElement(group.Group_Folio_Process, driver);

		group.Group_Folio_Process.click();
		groupLogger.info("Click Process");
		test_steps.add("Click Process");
		loadingImage(driver);
		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> checkSetMainPaymentMethodForAccount_PaymentInfo(WebDriver driver)
			throws InterruptedException {

		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_PickBillingInfo_Button, driver);
		Utility.ScrollToElement(group.Group_Folio_PickBillingInfo_Button, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_PickBillingInfo_Button, driver);
		group.Group_Folio_PickBillingInfo_Button.click();
		groupLogger.info("Click Pick Billing Info");
		test_steps.add("Click Pick Billing Info");

		Wait.explicit_wait_xpath("//*[@id='dialog-body0']", driver);
		driver.switchTo().frame("dialog-body0");
		Wait.explicit_wait_visibilityof_webelement_120(group.GroupFolio_BillingInfo_SetMainPaymentMethod_Check, driver);
		Utility.ScrollToElement(group.GroupFolio_BillingInfo_SetMainPaymentMethod_Check, driver);
		Wait.explicit_wait_elementToBeClickable(group.GroupFolio_BillingInfo_SetMainPaymentMethod_Check, driver);
		group.GroupFolio_BillingInfo_SetMainPaymentMethod_Check.click();
		groupLogger.info("Check Set Main Payment Method");
		test_steps.add("Check Set Main Payment Method");

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_BillingInfo_SaveButton, driver);
		Utility.ScrollToElement(group.Group_Folio_BillingInfo_SaveButton, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_BillingInfo_SaveButton, driver);
		group.Group_Folio_BillingInfo_SaveButton.click();
		groupLogger.info("Billing Info Save Button Clicked");
		test_steps.add("Billing Info Save Button Clicked");

		loadingImage(driver);
		driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> verifyBillingInfo(WebDriver driver, String paymentType, String accNo, String expiryDate)
			throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.explicit_wait_visibilityof_webelement_120(oldGroup.oldGroup_PaymentMethod, driver);
		String foundPaymentMethod = new Select(oldGroup.oldGroup_PaymentMethod).getFirstSelectedOption().getText();
		assertEquals(foundPaymentMethod, paymentType, "Failed Payment Method not Matched");
		test_steps.add("Successfully Verified Payment Method : " + paymentType);
		groupLogger.info("Successfully Verified Payment Method : " + paymentType);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupBilling_AccountNo, driver);
		String foundAccNo = group.GroupBilling_AccountNo.getAttribute("value");
		assertEquals(foundAccNo.substring(foundAccNo.length() - 4), accNo.substring(accNo.length() - 4),
				"Failed Account No not Matched");
		test_steps.add("Successfully Verified Account No : " + foundAccNo);
		groupLogger.info("Successfully Verified Account No : " + foundAccNo);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupBilling_ExpiryDate, driver);
		String foundExpry = group.GroupBilling_ExpiryDate.getAttribute("value");
		assertEquals(foundExpry, expiryDate, "Failed Expiry Date not Matched");
		test_steps.add("Successfully Verified Expiry Date : " + foundExpry);
		groupLogger.info("Successfully Verified Expiry Date : " + foundExpry);

		return test_steps;
	}

	public ArrayList<String> navigateAccount(WebDriver driver) throws InterruptedException {
		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		// WebDriverWait wait = new WebDriverWait(driver, 90);
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Account, driver);
		// Wait.WaitForElement(driver, OR.Group_Folio);
		Utility.ScrollToElement(group.Group_Account, driver);
		group.Group_Account.click();
		test_steps.add("Click on Group Account");
		groupLogger.info("Click on Group Account");
		return test_steps;

	}

	public ArrayList<String> verifyCardInfoInPaymentDetailTransaction(WebDriver driver, String cardNo)
			throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);

		driver.switchTo().frame("dialog-body0");
		String paymnentDetailColumnPath = "//*[@id='dgTransactionPayList_lbtnDisplaycaption_0']";
		Wait.explicit_wait_xpath(paymnentDetailColumnPath, driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(paymnentDetailColumnPath)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(paymnentDetailColumnPath)), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(paymnentDetailColumnPath)), driver);
		driver.findElement(By.xpath(paymnentDetailColumnPath)).click();
		test_steps.add("Click on Group Account");
		groupLogger.info("Click on Group Account");
		loadingImage(driver);
		driver.switchTo().defaultContent();

		driver.switchTo().frame("dialog-body1");

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupFolio_PaymentDetail_EncriptDecriptButton, driver);
		Wait.explicit_wait_elementToBeClickable(group.GroupFolio_PaymentDetail_EncriptDecriptButton, driver);
		Utility.ScrollToElement(group.GroupFolio_PaymentDetail_EncriptDecriptButton, driver);
		group.GroupFolio_PaymentDetail_EncriptDecriptButton.click();
		test_steps.add("Click on Decript Button");
		groupLogger.info("Click on Decript Button");
		loadingImage(driver);

		String cardNoPath = "//*[@id='lblCardNumber']";
		Wait.explicit_wait_xpath(cardNoPath, driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(cardNoPath)), driver);
		String foundCardNo = driver.findElement(By.xpath(cardNoPath)).getText();
		assertEquals(foundCardNo.substring(foundCardNo.length() - 4), cardNo.substring(cardNo.length() - 4),
				"Failed Card NO Not matched");
		test_steps.add("Successfully Verified Card NO : " + cardNo);
		groupLogger.info("Successfully Verified Card NO : " + cardNo);

		String cancelPath = "//*[@id='btnCancel']";
		Wait.explicit_wait_xpath(cancelPath, driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(cancelPath)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(cancelPath)), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(cancelPath)), driver);
		driver.findElement(By.xpath(cancelPath)).click();
		test_steps.add("Click on Cancel");
		groupLogger.info("Click on Cancel");
		loadingImage(driver);
		driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> verifyLineItemsCategory(WebDriver driver, String LineCategory, String LineNO) {
		ArrayList<String> test_steps = new ArrayList<>();

		Wait.explicit_wait_visibilityof_webelement_120(
				driver.findElement(By.id("MainContent_Folio1_dgLineItems_lbldateeffective_" + LineNO)), driver);

		String lineCat = driver.findElement(By.id("MainContent_Folio1_dgLineItems_lblLedgeraccountname_" + LineNO))
				.getText();
		assertEquals(lineCat, LineCategory, "Failed to Verify Folio Line Category");
		test_steps.add("Successfully Verified Line Category : " + lineCat);
		groupLogger.info("Successfully Verified Line Category : " + lineCat);

		return test_steps;
	}

	public ArrayList<String> createBlockWithRate(WebDriver driver, String blockName, String roomPerNight,
			String rackRate) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_xpath(OR.Click_New_Block_button, driver);
		Wait.explicit_wait_visibilityof_webelement_150(group.Click_New_Block_button, driver);
		Utility.ScrollToElement(group.Click_New_Block_button, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_New_Block_button, driver);
		group.Click_New_Block_button.click();
		test_steps.add("New Block Button Clicked");
		groupLogger.info("New Block Button Clicked");

		Wait.explicit_wait_xpath(OR.Enter_Block_Name, driver);
		Wait.explicit_wait_visibilityof_webelement_600(group.Enter_Block_Name, driver);
		group.Enter_Block_Name.sendKeys(blockName);
		test_steps.add("Entered New Block Name : " + blockName);
		groupLogger.info("Entered New Block Name : " + blockName);

		Wait.explicit_wait_xpath(OR.Click_Ok, driver);
		Wait.explicit_wait_visibilityof_webelement_150(group.Click_Ok, driver);
		Utility.ScrollToElement(group.Click_Ok, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Ok, driver);
		group.Click_Ok.click();
		test_steps.add("OK Button Clicked");
		groupLogger.info("OK Button Clicked");

		// String calPath = "//*[@id='trArrive']/td[2]/img";
		Wait.explicit_wait_xpath(OR.Select_Arrival_Date_Groups, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Select_Arrival_Date_Groups, driver);
		Wait.explicit_wait_elementToBeClickable(group.Select_Arrival_Date_Groups, driver);
		group.Select_Arrival_Date_Groups.click();
		test_steps.add("Select Arrival Date");
		groupLogger.info("Select Arrival Date");

		// String todayPath = "//div[1]/table/tfoot/tr[1]/th[text()='Today']";
		Wait.explicit_wait_xpath(OR.Click_Today_Arrival_Groups, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Click_Today_Arrival_Groups, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Today_Arrival_Groups, driver);
		group.Click_Today_Arrival_Groups.click();
		test_steps.add("Today Clicked as Arrival Date");
		groupLogger.info("Today Clicked as Arrival Date");

		Wait.explicit_wait_xpath(OR.Enter_No_of_Nigts, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Enter_No_of_Nigts, driver);
		Wait.explicit_wait_elementToBeClickable(group.Enter_No_of_Nigts, driver);
		group.Enter_No_of_Nigts.click();
		group.Enter_No_of_Nigts.sendKeys(roomPerNight);
		test_steps.add("Entered Room Per Night : " + roomPerNight);
		groupLogger.info("Entered Room Per Night : " + roomPerNight);

		Wait.explicit_wait_visibilityof_webelement_150(group.ratePlan, driver);
		new Select(group.ratePlan).selectByVisibleText(rackRate);
		test_steps.add("Selected Rate Plan : " + rackRate);
		groupLogger.info("Selected Rate Plan : " + rackRate);

		Wait.explicit_wait_xpath(OR.Click_Search_Group, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Click_Search_Group, driver);
		Utility.ScrollToElement(group.Click_Search_Group, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Search_Group, driver);
		group.Click_Search_Group.click();
		test_steps.add("Search Group Button Clicked");
		groupLogger.info("Search Group Button Clicked");

		return test_steps;
	}

	public ArrayList<String> verifyRoomQGRAndTotal(WebDriver driver, String qgr, String totalAmount) {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupBlockRoomQGR, driver);
		String foundQGR = group.GroupBlockRoomQGR.getText();
		assertEquals(Float.parseFloat(foundQGR), Float.parseFloat(qgr), "Failed QGR is not matched");
		test_steps.add("Successfully Verified QGR : " + foundQGR);
		groupLogger.info("Successfully Verified QGR : " + foundQGR);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupBlockRoomTotal, driver);
		String foundTotal = group.GroupBlockRoomTotal.getText();
		assertEquals(Float.parseFloat(foundTotal), Float.parseFloat(totalAmount), "Failed Total Amount is not matched");
		test_steps.add("Successfully Verified Total Amount : " + foundTotal);
		groupLogger.info("Successfully Verified Total Amount : " + foundTotal);

		return test_steps;
	}

	public ArrayList<String> setMaxBlock(WebDriver driver, String RoomClassName, String roomPerNight)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		List<WebElement> GetRoomclassNames = AdvGroup.GetRoomclasses;
		groupLogger.info("GetRoomclassNames" + GetRoomclassNames.size());
		for (int i = 0; i < GetRoomclassNames.size(); i++) {
			if (GetRoomclassNames.get(i).getText().equals(RoomClassName)) {
				int index = i + 1;
				driver.findElement(By.xpath(
						"//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index + "]/td[3]/input"))
						.sendKeys(Keys.chord(Keys.CONTROL, "a"), roomPerNight);
				Wait.wait1Second();
				test_steps.add("Select room block for : " + RoomClassName + " for : " + roomPerNight + " days");
				groupLogger.info("Select room block for : " + RoomClassName + " for : " + roomPerNight + " days");
				// break;
			} else {
				int index = i + 1;
				driver.findElement(By.xpath(
						"//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index + "]/td[3]/input"))
						.sendKeys(Keys.chord(Keys.CONTROL, "a"), "0");
				Wait.wait1Second();
			}
		}
		return test_steps;
	}

	public ArrayList<String> changeRaeBlockedRoom(WebDriver driver, String RoomClassName, String rateAmount)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();

		// driver.switchTo().frame("MainContent_Iframe_accountpicker");

		Wait.WaitForElement(driver, OR.CheckVaryRoomsByDate);
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		List<WebElement> GetRoomclassNames = AdvGroup.GetRoomclasses;
		groupLogger.info("GetRoomclassNames" + GetRoomclassNames.size());
		for (int i = 0; i < GetRoomclassNames.size(); i++) {
			if (GetRoomclassNames.get(i).getText().equals(RoomClassName)) {
				int index = i + 1;
				driver.findElement(By.xpath(
						"//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index + "]/td[4]/input"))
						.sendKeys(Keys.chord(Keys.CONTROL, "a"), rateAmount);
				Wait.wait1Second();
				test_steps.add("RoomClass : " + RoomClassName + " Rate : " + rateAmount);
				groupLogger.info("RoomClass : " + RoomClassName + " Rate : " + rateAmount);
				// break;
			} else {
				int index = i + 1;
				driver.findElement(By.xpath(
						"//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index + "]/td[4]/input"))
						.sendKeys(Keys.chord(Keys.CONTROL, "a"), "0");
				Wait.wait1Second();
			}
		}

		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public String getNightlyRateValue(WebDriver driver, String RoomClass) throws InterruptedException {

		String pickupPath = "//a[text()='" + RoomClass + "']/parent::div/parent::div/following-sibling::div[1]/div";
		Wait.explicit_wait_xpath(pickupPath, driver);
		Wait.waitUntilPresenceOfElementLocated(pickupPath, driver);
		Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(pickupPath)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(pickupPath)), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(pickupPath)), driver);
		return driver.findElement(By.xpath(pickupPath)).getText().replace("$ ", "");
	}

	public String getTotalRateValue(WebDriver driver, String RoomClass) throws InterruptedException {

		String pickupPath = "//a[text()='" + RoomClass + "']/parent::div/parent::div/following-sibling::div[2]/div";
		Wait.explicit_wait_xpath(pickupPath, driver);
		Wait.waitUntilPresenceOfElementLocated(pickupPath, driver);
		Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(pickupPath)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(pickupPath)), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(pickupPath)), driver);
		return driver.findElement(By.xpath(pickupPath)).getText().replace("$ ", "");
	}

	public String getReservationCount(WebDriver driver) {

		String path = "//*[@id='MainContent_ucReservationControl1_dgReservationList']/tbody/tr";

		Wait.explicit_wait_visibilityof_webelement_120(
				driver.findElement(By.id("MainContent_ucReservationControl1_drpSourceList")), driver);
		List<WebElement> ele = driver.findElements(By.xpath(path));

		return ele.size() + "";
	}

	public ArrayList<String> fillBlockFields(WebDriver driver, String blockName, String roomPerNight)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_xpath(OR.Click_New_Block_button, driver);
		Wait.explicit_wait_visibilityof_webelement_150(group.Click_New_Block_button, driver);
		Utility.ScrollToElement(group.Click_New_Block_button, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_New_Block_button, driver);
		group.Click_New_Block_button.click();
		test_steps.add("New Block Button Clicked");
		groupLogger.info("New Block Button Clicked");

		Wait.explicit_wait_xpath(OR.Enter_Block_Name, driver);
		Wait.explicit_wait_visibilityof_webelement_600(group.Enter_Block_Name, driver);
		group.Enter_Block_Name.sendKeys(blockName);
		test_steps.add("Entered New Block Name : " + blockName);
		groupLogger.info("Entered New Block Name : " + blockName);

		Wait.explicit_wait_xpath(OR.Click_Ok, driver);
		Wait.explicit_wait_visibilityof_webelement_150(group.Click_Ok, driver);
		Utility.ScrollToElement(group.Click_Ok, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Ok, driver);
		group.Click_Ok.click();
		test_steps.add("OK Button Clicked");
		groupLogger.info("OK Button Clicked");

		// String calPath = "//*[@id='trArrive']/td[2]/img";
		Wait.explicit_wait_xpath(OR.Select_Arrival_Date_Groups, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Select_Arrival_Date_Groups, driver);
		Wait.explicit_wait_elementToBeClickable(group.Select_Arrival_Date_Groups, driver);
		group.Select_Arrival_Date_Groups.click();
		test_steps.add("Select Arrival Date");
		groupLogger.info("Select Arrival Date");

		// String todayPath = "//div[1]/table/tfoot/tr[1]/th[text()='Today']";
		Wait.explicit_wait_xpath(OR.Click_Today_Arrival_Groups, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Click_Today_Arrival_Groups, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Today_Arrival_Groups, driver);
		group.Click_Today_Arrival_Groups.click();
		test_steps.add("Today Clicked as Arrival Date");
		groupLogger.info("Today Clicked as Arrival Date");

		Wait.explicit_wait_xpath(OR.Enter_No_of_Nigts, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Enter_No_of_Nigts, driver);
		Wait.explicit_wait_elementToBeClickable(group.Enter_No_of_Nigts, driver);
		group.Enter_No_of_Nigts.click();
		group.Enter_No_of_Nigts.sendKeys(roomPerNight);
		test_steps.add("Entered Room Per Night : " + roomPerNight);
		groupLogger.info("Entered Room Per Night : " + roomPerNight);

		// Wait.explicit_wait_visibilityof_webelement_150(group.ratePlan,
		// driver);
		// new Select(group.ratePlan).selectByVisibleText(rackRate);
		// test_steps.add("Selected Rate Plan : " + rackRate);
		// groupLogger.info("Selected Rate Plan : " + rackRate);

		return test_steps;
	}

	public ArrayList<String> addPreShoulder(WebDriver driver, String value, boolean isIncrease)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		if (isIncrease) {
			int val = Integer.parseInt(value);
			for (int i = 0; i < val; i++) {
				Wait.wait2Second();
				Wait.explicit_wait_visibilityof_webelement_120(group.GroupPreShoulder_Add, driver);
				Utility.ScrollToElement(group.GroupPreShoulder_Add, driver);
				group.GroupPreShoulder_Add.click();
				Wait.wait1Second();
			}
			groupLogger.info("Pre Shoulder Added : " + value);
			test_steps.add("Pre Shoulder Added : " + value);
		} else {
			int val = Integer.parseInt(value);
			for (int i = 0; i < val; i++) {
				Wait.wait2Second();
				Wait.explicit_wait_visibilityof_webelement_120(group.GroupPreShoulder_Remove, driver);
				Utility.ScrollToElement(group.GroupPreShoulder_Remove, driver);
				group.GroupPreShoulder_Remove.click();
				Wait.wait1Second();
			}
			groupLogger.info("Pre Shoulder Removed : " + value);
			test_steps.add("Pre Shoulder Removed : " + value);
		}
		return test_steps;
	}

	public ArrayList<String> addPostShoulder(WebDriver driver, String value, boolean isIncrease)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		if (isIncrease) {
			int val = Integer.parseInt(value);
			for (int i = 0; i < val; i++) {
				Wait.wait2Second();
				Wait.explicit_wait_visibilityof_webelement_120(group.GroupPostShoulder_Add, driver);
				Utility.ScrollToElement(group.GroupPostShoulder_Add, driver);
				group.GroupPostShoulder_Add.click();
				Wait.wait1Second();
			}
			groupLogger.info("Post Shoulder Added : " + value);
			test_steps.add("Post Shoulder Added : " + value);
		} else {
			int val = Integer.parseInt(value);
			for (int i = 0; i < val; i++) {
				Wait.wait2Second();
				Wait.explicit_wait_visibilityof_webelement_120(group.GroupPostShoulder_Remove, driver);
				Utility.ScrollToElement(group.GroupPostShoulder_Remove, driver);
				group.GroupPostShoulder_Remove.click();
				Wait.wait1Second();
			}
			groupLogger.info("Post Shoulder Removed : " + value);
			test_steps.add("Post Shoulder Removed : " + value);
		}
		return test_steps;
	}

	public ArrayList<String> clickBlockSearch(WebDriver driver) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_xpath(OR.Click_Search_Group, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Click_Search_Group, driver);
		Utility.ScrollToElement(group.Click_Search_Group, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Search_Group, driver);
		group.Click_Search_Group.click();
		test_steps.add("Search Group Button Clicked");
		groupLogger.info("Search Group Button Clicked");
		loadingImage(driver);
		return test_steps;
	}

	public ArrayList<String> verifyPreShoulder(WebDriver driver, String value) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupPreShoulder_Text, driver);
		Utility.ScrollToElement(group.GroupPreShoulder_Text, driver);
		String shoulderText = group.GroupPreShoulder_Text.getAttribute("value");
		assertEquals(Integer.parseInt(shoulderText), Integer.parseInt(value), "Failed Pre Shoulder not Matched");
		groupLogger.info("Successfully Verified Pre Shoulder : " + value);
		test_steps.add("Successfully Verified Pre Shoulder : " + value);

		return test_steps;
	}

	public ArrayList<String> verifyPostShoulder(WebDriver driver, String value) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupPostShoulder_Text, driver);
		Utility.ScrollToElement(group.GroupPostShoulder_Text, driver);
		String shoulderText = group.GroupPostShoulder_Text.getAttribute("value");
		assertEquals(Integer.parseInt(shoulderText), Integer.parseInt(value), "Failed Post Shoulder not Matched");
		groupLogger.info("Successfully Verified Post Shoulder : " + value);
		test_steps.add("Successfully Verified Post Shoulder : " + value);

		return test_steps;
	}

	public ArrayList<String> clickSecondBlock(WebDriver driver, String blockName) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String path = "//*[@id='mycarousel']/li//span[contains(text(),'" + blockName + "')]";
		System.out.println(path);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(path)), driver);
		driver.findElement(By.xpath(path)).click();
		groupLogger.info(blockName + " Selected ");
		test_steps.add(blockName + " Selected");
		return test_steps;
	}

	public String getRoomBlocked_RoomBlockDetatil(WebDriver driver, String blockName) {

		String path = "//*[@id='mycarousel']/li//span[contains(text(),'" + blockName
				+ "')]/parent::td/parent::tr/following-sibling::tr//span[contains(text(),'Rooms Blocked')]";

		Wait.explicit_wait_xpath(path, driver);
		Wait.explicit_wait_visibilityof_webelement_150(driver.findElement(By.xpath(path)), driver);
		String roomBlocked = driver.findElement(By.xpath(path)).getText();
		StringTokenizer str = new StringTokenizer(roomBlocked, "Rooms Blocked:");
		String value = str.nextToken();

		return value.replaceAll("\\s", "");
	}

	public String getPickUpPercentage_RoomBlockDetatil(WebDriver driver, String blockName) {

		String path = "//*[@id='mycarousel']/li//span[contains(text(),'" + blockName
				+ "')]/parent::td/parent::tr/following-sibling::tr//span[contains(text(),'Picked up %:')]";

		Wait.explicit_wait_xpath(path, driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		String pickUpPercentage = driver.findElement(By.xpath(path)).getText();
		StringTokenizer str = new StringTokenizer(pickUpPercentage, "Picked up %:");
		String value = str.nextToken();

		return value.replaceAll("\\s", "");
	}

	public String getTotalRoomNights_RoomBlockDetail(WebDriver driver, String blockName) {
		String path = "//*[@id='mycarousel']/li//span[contains(text(),'" + blockName
				+ "')]/parent::td/parent::tr/following-sibling::tr//span[contains(text(),'Total Room Nights: ')]/span";
		Wait.explicit_wait_xpath(path, driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		String value = driver.findElement(By.xpath(path)).getText();

		return value;
	}

	public String getExpectedRevenue_RoomBlockDetail(WebDriver driver, String blockName) {
		String path = "//*[@id='mycarousel']/li//span[contains(text(),'" + blockName
				+ "')]/parent::td/parent::tr/following-sibling::tr//span[contains(text(),'Expected Revenue: ')]/span";
		Wait.explicit_wait_xpath(path, driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		String value = driver.findElement(By.xpath(path)).getText();

		return value.replace("$", "");
	}

	public ArrayList<String> openResDialogFromResTab(WebDriver driver, int lineNo) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		int headerLines = 2;
		lineNo = lineNo + headerLines;

		String resPath = "//*[@id='MainContent_ucReservationControl1_dgReservationList']/tbody/tr[" + lineNo
				+ "]/td[3]/a";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(resPath)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(resPath)), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(resPath)), driver);
		driver.findElement(By.xpath(resPath)).click();

		test_steps.add("Reservation Guest Name Link Clicked");
		groupLogger.info("Reservation Guest Name Link Clicked");

		return test_steps;
	}

	public ArrayList<String> closeDialoge(WebDriver driver, String id) {
		ArrayList<String> test_steps = new ArrayList<>();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", driver.findElement(By.id(id)));
		test_steps.add("Dialog Closed");
		groupLogger.info("Dialog Closed");
		return test_steps;
	}

	public ArrayList<String> openItemDetail_FolioLineItem(WebDriver driver, String LineNO) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();

		Wait.explicit_wait_visibilityof_webelement_120(
				driver.findElement(By.id("MainContent_Folio1_dgLineItems_lbtnDisplaycaption_" + LineNO)), driver);
		Wait.explicit_wait_elementToBeClickable(
				driver.findElement(By.id("MainContent_Folio1_dgLineItems_lbtnDisplaycaption_" + LineNO)), driver);
		Utility.ScrollToElement(
				driver.findElement(By.id("MainContent_Folio1_dgLineItems_lbtnDisplaycaption_" + LineNO)), driver);

		driver.findElement(By.id("MainContent_Folio1_dgLineItems_lbtnDisplaycaption_" + LineNO)).click();

		test_steps.add("Folio Line Item Description Link Clicked");
		groupLogger.info("Folio Line Item Description Link Clicked");

		return test_steps;
	}

	public ArrayList<String> openResDialogFromFolioItemDetail(WebDriver driver, String desc)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();

		Wait.explicit_wait_visibilityof_webelement_600(driver.findElement(By.xpath("//*[@id='dialog-body0']")), driver);
		driver.switchTo().frame("dialog-body0");

		String path = "//a[contains(text(),'" + desc + "')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(path)), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(path)), driver);

		driver.findElement(By.xpath(path)).click();

		test_steps.add("Item Detail Description Link Clicked");
		groupLogger.info("Item Detail Description Link Clicked");

		driver.switchTo().defaultContent();

		return test_steps;
	}

	public ArrayList<String> selectAuthorizationType(WebDriver driver, String Authorizationtype)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		// Click Add Line Item

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_AuthType, driver);

		new Select(group.Group_Folio_AuthType).selectByVisibleText(Authorizationtype);
		test_steps.add("Selected Authorization Type : " + Authorizationtype);
		groupLogger.info("Selected Authorization Type : " + Authorizationtype);
		return test_steps;
	}

	public ArrayList<String> fillPaymentDetail(WebDriver driver, String CardName, String CardNo, String ExpiryDate,
			String CVVCard) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		Wait.explicit_wait_xpath("//*[@id='dialog-body1']", driver);
		driver.switchTo().frame("dialog-body1");

		loadingImage(driver);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupFolio_PaymentDetail_NameOnCard, driver);
		group.GroupFolio_PaymentDetail_NameOnCard.sendKeys(CardName);
		test_steps.add("Payment Detail Name On Card : " + CardName);
		groupLogger.info("Payment Detail Name On Card : " + CardName);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupFolio_PaymentDetail_CardNO, driver);
		group.GroupFolio_PaymentDetail_CardNO.sendKeys(CardNo);
		test_steps.add("Payment Detail Card No : " + CardNo);
		groupLogger.info("Payment Detail Card No : " + CardNo);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupFolio_PaymentDetail_ExpiryDate, driver);
		group.GroupFolio_PaymentDetail_ExpiryDate.sendKeys(ExpiryDate);
		test_steps.add("Payment Detail Card Exipry Date : " + ExpiryDate);
		groupLogger.info("Payment Detail Card Exipry Date : " + ExpiryDate);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupFolio_PaymentDetail_CVVCode, driver);
		group.GroupFolio_PaymentDetail_CVVCode.sendKeys(CVVCard);
		test_steps.add("Payment Detail CVV Card : " + CVVCard);
		groupLogger.info("Payment Detail CVV Card : " + CVVCard);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupFolio_PaymentDetail_ButtonOK, driver);
		Wait.explicit_wait_elementToBeClickable(group.GroupFolio_PaymentDetail_ButtonOK, driver);
		Utility.ScrollToElement(group.GroupFolio_PaymentDetail_ButtonOK, driver);
		group.GroupFolio_PaymentDetail_ButtonOK.click();
		test_steps.add("Click on OK Button");
		groupLogger.info("Click on OK Button");

		loadingImage(driver);

		driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> applyAdvanceDeposit_YesClick(WebDriver driver, String paymentAmount)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		// switch to dialog
		try {
			if (driver.findElement(By.id("divAdvanceDepostConfirm")).isDisplayed()) {
				test_steps.add("Apply Advance Deposit Popup Appear");
				groupLogger.info("Apply Advance Deposit Popup Appear");

				String foundAdvanceDepostAmount = Utility.convertDollarToNormalAmount(driver,
						driver.findElement(By.id("lblAdvanceDepositAmount")).getText());
				assertEquals(Float.parseFloat(foundAdvanceDepostAmount), Float.parseFloat(paymentAmount),
						"Failed Advance Deposit Popup Payment Amount Not Matched");
				test_steps.add("Successfully Verified Apply Advance Deposit Popup Payment Amount : "
						+ foundAdvanceDepostAmount);
				groupLogger.info("Successfully Verified Apply Advance Deposit Popup Payment Amount : "
						+ foundAdvanceDepostAmount);

				Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath("//button[text()='Yes']")),
						driver);
				Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath("//button[text()='Yes']")), driver);
				Utility.ScrollToElement(driver.findElement(By.xpath("//button[text()='Yes']")), driver);
				driver.findElement(By.xpath("//button[text()='Yes']")).click();
				test_steps.add("Click on Yes Button");
				groupLogger.info("Click on Yes Button");
			} else {
				test_steps.add("Apply Advance Deposit Popup not Appear");
				groupLogger.info("Apply Advance Deposit Popup not Appear");
			}

		} catch (Exception e) {
			test_steps.add("Apply Advance Deposit Popup not Appear");
			groupLogger.info("Apply Advance Deposit Popup not Appear");
		}

		return test_steps;
	}

	public ArrayList<String> verifyTransactionPaymentDetail(WebDriver driver, String PaymentMethod, String PayAmount,
			int LineNo) {
		ArrayList<String> test_steps = new ArrayList<>();
		// switch to dialog
		LineNo = LineNo + 1;// header
		String tablePath = "//*[@id='dgTransactionPayList']/tbody/tr[" + LineNo + "]";
		Wait.explicit_wait_xpath(tablePath, driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(tablePath + "/td[3]")), driver);
		String lineDate = driver.findElement(By.xpath(tablePath + "/td[3]")).getText();
		assertEquals(Utility.parseDate(lineDate, "MMM dd, yyyy", "MMM dd, yyyy"),
				Utility.getCurrentDate("MMM dd, yyyy"), "Failed to Verify Date in Payment Line Item");

		test_steps.add("Successfully Verified Date : " + lineDate);
		groupLogger.info("Successfully Verified Date : " + lineDate);

		String linePayType = driver.findElement(By.xpath(tablePath + "/td[4]")).getText();
		assertTrue(linePayType.contains(PaymentMethod), "Failed to Verify Payment Type in Payment Line Item");

		test_steps.add("Successfully Verified Payment Type : " + linePayType);
		groupLogger.info("Successfully Verified Payment Type : " + linePayType);

		String linePayAmount = driver.findElement(By.xpath(tablePath + "/td[7]")).getText();
		assertEquals(Float.parseFloat(linePayAmount), Float.parseFloat(PayAmount),
				"Failed to Verify Amount in Payment Line Item");

		test_steps.add("Successfully Verified Payment Amount : " + PayAmount);
		groupLogger.info("Successfully Verified Payment Amount : " + PayAmount);

		return test_steps;
	}

	public ArrayList<String> clickAdvDepLedgerLink_TransactionPaymentDetail(WebDriver driver)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		// switch to dialog

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath("//*[@id='lnkAdvDepLedger']")),
				driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath("//*[@id='lnkAdvDepLedger']")), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath("//*[@id='lnkAdvDepLedger']")), driver);
		driver.findElement(By.xpath("//*[@id='lnkAdvDepLedger']")).click();
		test_steps.add("Transaction Adv Dep Ledger Selected");
		Utility.app_logs.info("Transaction Adv Dep Ledger Selected");

		return test_steps;
	}

	public ArrayList<String> switchToTransactionTab(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		// Click Add Line Item

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupFolio_PaymentDetail_TransactionTab, driver);
		Wait.explicit_wait_elementToBeClickable(group.GroupFolio_PaymentDetail_TransactionTab, driver);
		Utility.ScrollToElement(group.GroupFolio_PaymentDetail_TransactionTab, driver);
		group.GroupFolio_PaymentDetail_TransactionTab.click();

		test_steps.add("Successfully Switched to Transaction Tab ");
		groupLogger.info("Successfully Switched to Transaction Tab ");
		return test_steps;
	}

	public String getTaxesAndServiceCharges(WebDriver driver) throws InterruptedException {
		String amt = "0";
		try {
			Wait.explicit_wait_visibilityof_webelement(
					driver.findElement(By.id("MainContent_Folio1_fSummary1_lblAccTaxSurCharge")), driver);
			amt = driver.findElement(By.id("MainContent_Folio1_fSummary1_lblAccTaxSurCharge")).getText();
		} catch (Exception e) {
			groupLogger.info("No Taxes and Service Charges Found");
		}
		return amt;
	}

	public ArrayList<String> advanceDepositBalancePopup(WebDriver driver, String paymentAmount)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();

		// switch to dialog
		try {
			if (driver.findElement(By.id("divadvanceDepostBalance")).isDisplayed()) {
				test_steps.add("Apply Advance Deposit Popup Appear");
				groupLogger.info("Apply Advance Deposit Popup Appear");

				String foundAdvanceDepostAmount = Utility.convertDollarToNormalAmount(driver,
						driver.findElement(By.id("spnADremaining")).getText());
				assertEquals(foundAdvanceDepostAmount, paymentAmount,
						"Failed Advance Deposit Popup Payment Amount Not Matched");
				test_steps.add("Successfully Verified Apply Advance Deposit Popup Payment Amount : "
						+ foundAdvanceDepostAmount);
				groupLogger.info("Successfully Verified Apply Advance Deposit Popup Payment Amount : "
						+ foundAdvanceDepostAmount);

				Wait.explicit_wait_visibilityof_webelement_120(
						driver.findElement(By.xpath("//button[text()='Continue']")), driver);
				Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath("//button[text()='Continue']")),
						driver);
				Utility.ScrollToElement(driver.findElement(By.xpath("//button[text()='Continue']")), driver);
				driver.findElement(By.xpath("//button[text()='Continue']")).click();
				test_steps.add("Click on Continue Button");
				groupLogger.info("Click on Continue Button");
			} else {
				test_steps.add("Apply Advance Deposit Popup not Appear");
				groupLogger.info("Apply Advance Deposit Popup not Appear");
			}

		} catch (Exception e) {
			test_steps.add("Apply Advance Deposit Popup not Appear");
			groupLogger.info("Apply Advance Deposit Popup not Appear");
		}
		return test_steps;
	}

	public ArrayList<String> verifyPaymentAmount(WebDriver driver, String PayAmount) throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		// Wait.explicit_wait_xpath("//*[@id='dialog-body0']", driver);
		// driver.switchTo().frame("dialog-body0");

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_Amount, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_Amount, driver);
		Utility.ScrollToElement(group.Group_Folio_Amount, driver);
		String foundPayAmnt = group.Group_Folio_Amount.getAttribute("value");
		assertEquals(Float.parseFloat(foundPayAmnt), Float.parseFloat(PayAmount), "Failed to Match Payment Amount");

		test_steps.add("Successfully Verified Payment Amount : " + PayAmount);
		groupLogger.info("Successfully Verified Payment Amount : " + PayAmount);
		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> paymentDetailCancelClicked(WebDriver driver) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);

		Wait.explicit_wait_visibilityof_webelement_200(group.GroupFolio_PaymentDetail_CancelButton, driver);
		Utility.ScrollToElement(group.GroupFolio_PaymentDetail_CancelButton, driver);
		Wait.explicit_wait_elementToBeClickable(group.GroupFolio_PaymentDetail_CancelButton, driver);
		group.GroupFolio_PaymentDetail_CancelButton.click();
		test_steps.add("Payment Detail Button Clicked");
		groupLogger.info("Payment Detail Button Clicked");
		loadingImage(driver);
		return test_steps;
	}

	public String getAddPayingAmount(WebDriver driver) {
		String addPayingAmount = "0";
		try {
			if (driver.findElement(By.id("divadvanceDepostBalance")).isDisplayed()) {

				addPayingAmount = Utility.convertDollarToNormalAmount(driver,
						driver.findElement(By.id("spnADpaying")).getText());
				addPayingAmount = Utility.convertDollarToNormalAmount(driver, addPayingAmount);
				groupLogger.info("Added Paying Amount is : " + addPayingAmount);
			}

		} catch (Exception e) {

			groupLogger.info("Apply Advance Deposit Popup not Appear");
		}

		return addPayingAmount;
	}

	public void createNewGroupBlock(WebDriver driver, String NumberofNights, ArrayList<String> test_steps)
			throws InterruptedException, ParseException {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		// Wait.waitUntilPresenceOfElementLocated(OR.Rate_Quote_Tittle, driver);
		// Wait.explicit_wait_visibilityof_webelement_150(AdvGroup.Select_Arrival_Date_Groups,
		// driver);
		Wait.explicit_wait_elementToBeClickable(AdvGroup.Select_Arrival_Date_Groups, driver);
		AdvGroup.Select_Arrival_Date_Groups.click();
		test_steps.add("Select Arrival Date");
		groupLogger.info("Select Arrival Date");

		test_steps.add("Arrival_Date");
		groupLogger.info("Arrival_Date");
		test_steps.add("SelectArrival date");
		groupLogger.info("SelectArrival date");
		// Wait.waitUntilPresenceOfElementLocated(OR.Click_Today_Arrival_Groups,
		// driver);
		Wait.explicit_wait_elementToBeClickable(AdvGroup.Click_Today_Arrival_Groups, driver);
		AdvGroup.Click_Today_Arrival_Groups.click();

		test_steps.add("Today Clicked as Arrival Date");
		groupLogger.info("Today Clicked as Arrival Date");
		Wait.explicit_wait_visibilityof_webelement_200(AdvGroup.Enter_No_of_Nigts, driver);
		// Wait.explicit_wait_elementToBeClickable(AdvGroup.Enter_No_of_Nigts,
		// driver);
		AdvGroup.Enter_No_of_Nigts.click();
		AdvGroup.Enter_No_of_Nigts.sendKeys(NumberofNights);
		String NoOfNinghts = AdvGroup.Enter_No_of_Nigts.getText();

		test_steps.add("Entered Room Per Night : " + NumberofNights);
		groupLogger.info("Entered Room Per Night : " + NumberofNights);

		/*
		 * int pre = Integer.parseInt(PreScheduler); int post =
		 * Integer.parseInt(PostScheduler);
		 * 
		 * 
		 * for (int i = 0; i < pre; i++) { //Wait.WaitForElement(driver,
		 * OR.Group_PreScheduler);
		 * //Wait.waitUntilPresenceOfElementLocated(OR.Group_PreScheduler,
		 * driver);
		 * Wait.explicit_wait_elementToBeClickable(AdvGroup.Group_PreScheduler,
		 * driver); AdvGroup.Group_PreScheduler.click(); Wait.wait1Second();
		 * 
		 * } test_steps.add("Select prescheduler as : " + pre);
		 * advGroupsLogger.info("Select prescheduler as : " + pre);
		 * 
		 * 
		 * for (int i = 0; i < post; i++) { //Wait.WaitForElement(driver,
		 * OR.Group_PostScheduler);
		 * //Wait.waitUntilPresenceOfElementLocated(OR.Group_PostScheduler,
		 * driver);
		 * Wait.explicit_wait_elementToBeClickable(AdvGroup.Group_PostScheduler,
		 * driver); AdvGroup.Group_PostScheduler.click(); Wait.wait1Second(); }
		 * 
		 * test_steps.add("Select postscheduler as : " + post);
		 * advGroupsLogger.info("Select postscheduler as : " + post);
		 */

		// Wait.explicit_wait_xpath(OR.Click_Search_Group, driver);
		// Wait.explicit_wait_visibilityof_webelement_200(AdvGroup.Click_Search_Group,
		// driver);
		// Utility.ScrollToElement(AdvGroup.Click_Search_Group, driver);
		Wait.explicit_wait_elementToBeClickable(AdvGroup.Click_Search_Group, driver);
		AdvGroup.Click_Search_Group.click();
		test_steps.add("Search Group Button Clicked");
		groupLogger.info("Search Group Button Clicked");

		Wait.explicit_wait_visibilityof_webelement_120(AdvGroup.Rate_Quote_Room_Ninghts, driver);
		String NoofRoomNinghtsInRate = AdvGroup.Rate_Quote_Room_Ninghts.getText();
		test_steps.add("No of Room Ninghts In Rate" + NoofRoomNinghtsInRate);
		groupLogger.info("No of Room Ninghts In Rate" + NoofRoomNinghtsInRate);

		Wait.explicit_wait_visibilityof_webelement_120(AdvGroup.Summary_Room_Ninght, driver);
		String NoofRoomNinghtsInsummary = AdvGroup.Summary_Room_Ninght.getText();
		int summaryninght = Integer.valueOf(NoofRoomNinghtsInsummary);
		int rateninghint = Integer.valueOf(NoofRoomNinghtsInRate.replace(" room nights", ""));

		test_steps.add("No of Room Ninghts In Summary" + NoofRoomNinghtsInsummary);
		groupLogger.info("\"No of Room Ninghts In summary" + NoofRoomNinghtsInsummary);
		assertEquals(rateninghint, summaryninght);

		Wait.explicit_wait_visibilityof_webelement_120(AdvGroup.Summary_Room_Blocks, driver);
		String summaryRoomBlocks = AdvGroup.Summary_Room_Blocks.getText();
		test_steps.add("summary RoomB locks" + summaryRoomBlocks);
		groupLogger.info("summary RoomB locks" + summaryRoomBlocks);
		assertEquals(summaryRoomBlocks, NumberofNights);

	}

	public void Save(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_Groups group = new Elements_Groups(driver);

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Save, driver);
		Wait.wait1Second();

		while (true) {
			if ((!driver.findElement(By.xpath("//div[@id='InnerFreezePane']/img")).isDisplayed())) {
				System.out.println("in if");
				break;
			} else {
				System.out.println("Waiting for frame");
				Wait.wait5Second();
			}
		}

		// Wait.explicit_wait_xpath(OR.Group_Save, driver);
		// Wait.explicit_wait_visibilityof_webelement_120(group.Group_Save,
		// driver);
		// Utility.ScrollToElement(group.Group_Save, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Save, driver);
		Wait.wait3Second();
		while (true) {
			if (!driver.findElement(By.xpath("//div[@id='InnerFreezePane']/img")).isDisplayed()) {
				System.out.println("in if");
				Wait.wait3Second();
				break;
			} else {
				System.out.println("Waiting for frame");
				Wait.wait5Second();
			}
		}
		Wait.WaitForElement(driver, OR.Group_Save);
		group.Group_Save.click();
		test_steps.add("Click on Save button");
		groupLogger.info("Click on Save button");
		//

	}

	public void click_PreviewFolio(WebDriver driver, ArrayList<String> test_steps) throws Exception {
		Elements_Groups group = new Elements_Groups(driver);
		// Wait.wait1Second();
		// Wait.explicit_wait_visibilityof_webelement_120(group.Group_PreviewFolio,
		// driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath("//a[@id='MainContent_lnkPreviewFolio']")),
				driver);
		test_steps.add("Click on Preview");
		groupLogger.info("Click on Preview");
		// Wait.WaitForElement(driver, OR.Group_PreviewFolio);
		group.Group_PreviewFolio.click();
		//

		Wait.explicit_wait_visibilityof_webelement_120(group.Edit_block_details, driver);
		groupLogger.info("find edit block details");

	}

	public void previewFolio_AddLineItem(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_PreviewFolio_Add, driver);
		// Wait.WaitForElement(driver, OR.Group_PreviewFolio_Add);
		group.Group_PreviewFolio_Add.click();

		Wait.explicit_wait_visibilityof_webelement_120(group.Edit_block_details, driver);
		assertTrue(group.Edit_block_details.isDisplayed(), "edit block details find");
		test_steps.add("Click on add");
		groupLogger.info("Click on add");

		String lineItem = "//select[starts-with(@id,'MainContent_ucPreviewFolio_dgPreviewFolioLineItems_drpLedgeraccountname')]";
		String qty = "//input[starts-with(@id,'MainContent_ucPreviewFolio_dgPreviewFolioLineItems_txtQuantity')]";
		String amount = "//input[starts-with(@id,'MainContent_ucPreviewFolio_dgPreviewFolioLineItems_txtAmount')]";
		String commit = "//input[@id='MainContent_ucPreviewFolio_btnDone']";
		String done = "//table[@id='Table2']/tbody/tr/td[2]";

		// Wait.WaitForElement(driver, lineItem);
		new Select(driver.findElement(By.xpath(lineItem))).selectByIndex(7);
		Wait.WaitForElement(driver, qty);
		Wait.wait2Second();
		driver.findElement(By.xpath(qty)).sendKeys("1");
		// Wait.WaitForElement(driver, amount);
		Wait.wait1Second();
		driver.findElement(By.xpath(amount)).sendKeys("100");

		// Wait.WaitForElement(driver, commit);
		Wait.wait1Second();
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(commit)), driver);
		driver.findElement(By.xpath(commit)).click();
		test_steps.add("Click on commit");
		groupLogger.info("Click on commit");

		Wait.explicit_wait_visibilityof_webelement_120(group.LineItem_act_Amount, driver);
		String LineItem_act_Amount = group.LineItem_act_Amount.getText();
		groupLogger.info("Line iteme actuval amount: " + LineItem_act_Amount);
		test_steps.add("Line iteme actuval amount: : " + LineItem_act_Amount);

		Wait.explicit_wait_visibilityof_webelement_120(group.LineItem_add_Amount, driver);
		String LineItem_add_Amount = group.LineItem_add_Amount.getText();
		groupLogger.info("LineItem_add_Amount: " + LineItem_add_Amount);
		test_steps.add("LineItem_add_Amount: : " + LineItem_add_Amount);

		Wait.explicit_wait_visibilityof_webelement_120(group.LineItem_Room_charges, driver);
		String LineItem_Room_charges = group.LineItem_Room_charges.getText();
		groupLogger.info("LineItem_Room_charges: " + LineItem_Room_charges);
		test_steps.add("LineItem_Room_charges: : " + LineItem_Room_charges);

		Wait.explicit_wait_visibilityof_webelement_120(group.LineItem_Incidentals, driver);
		String LineItem_Incidentals = group.LineItem_Incidentals.getText();
		groupLogger.info("LineItem_Incidentals: " + LineItem_Incidentals);
		test_steps.add("LineItem_Incidentals: : " + LineItem_Incidentals);

		Wait.explicit_wait_visibilityof_webelement_120(group.LineItem_total_charges, driver);
		String LineItem_total_charges = group.LineItem_total_charges.getText();
		groupLogger.info("LineItem_total_charges: " + LineItem_total_charges);
		test_steps.add("LineItem_total_charges: : " + LineItem_total_charges);
		Double actAmount = Double.parseDouble(LineItem_act_Amount);
		Double addAmount = Double.parseDouble(LineItem_add_Amount);
		Double RoomCharges = Double.parseDouble(LineItem_total_charges);
		Double totalAmount = actAmount + addAmount;
		if (RoomCharges >= totalAmount) {
			groupLogger.info("Line item total charges greterthan to total amount");
			test_steps.add("Line item total greterthan to total amount");

		} else {
			groupLogger.info("Line item total lessthan to total amount");
			test_steps.add("Line item total lessthan to total amount");

		}

		Wait.explicit_wait_visibilityof_webelement_120(group.Block_Details_PickedUP_Revenue, driver);
		String Block_Details_PickedUP_Revenue = group.Block_Details_PickedUP_Revenue.getText();
		groupLogger.info("Block Details PickedUP Revenue: " + Block_Details_PickedUP_Revenue);
		test_steps.add("Block Details PickedUP Revenue: : " + Block_Details_PickedUP_Revenue);

		Wait.explicit_wait_visibilityof_webelement_120(group.Block_Details_PickedUP_percentage, driver);
		String Block_Details_PickedUP_percentage = group.Block_Details_PickedUP_percentage.getText();
		groupLogger.info("Block Details PickedUP percentage: " + Block_Details_PickedUP_percentage);
		test_steps.add("Block Details PickedUP percentage: : " + Block_Details_PickedUP_percentage);

		// Wait.WaitForElement(driver, done);
		Wait.wait3Second();
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(done)), driver);
		driver.findElement(By.xpath(done)).click();
		test_steps.add("Click on done");
		groupLogger.info("Click on done");

	}

	public String Verify_Room_Block_Promo_code(WebDriver driver, ArrayList<String> test_steps) {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_visibilityof_webelement_120(AdvGroup.Room_Block_PromoCod, driver);
		String Room_Block_PromoCod = AdvGroup.Room_Block_PromoCod.getAttribute("value");
		groupLogger.info("Room Block PromoCod: " + Room_Block_PromoCod);
		test_steps.add("Room Block PromoCod : " + Room_Block_PromoCod);
		return Room_Block_PromoCod;

	}

	public String GetGroupsEndingBalance(WebDriver driver) throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);
		Wait.explicit_wait_visibilityof_webelement(group.GroupsEndingBalance, driver);
		return group.GroupsEndingBalance.getText();

	}

	public ArrayList<String> verifyCreatedAccCount(WebDriver driver, String AccName) {
		ArrayList<String> test_steps = new ArrayList<>();
		String path = "//*[@id='MainContent_dgAccountList']/tbody//a[text()='" + AccName + "']";
		int size = driver.findElements(By.xpath(path)).size();
		assertTrue(size == 1, "Failed expected 1 but found " + size);
		groupLogger.info("Successfully Verified Group Accounts Not duplicating when the users click Save twice ");
		test_steps.add("Successfully Verified Group Accounts Not duplicating when the users click Save twice"
				+ "<br/><a href='https://innroad.atlassian.net/browse/NG-4199' target='_blank'>"
				+ "Verified : NG-4199</a><br/>");
		return test_steps;
	}

	public ArrayList<String> billinginfo(WebDriver driver, String Property, String paymentType, String accNo,
			String expiryDate, boolean isUseMailingAdd, boolean isCopyPickupRes) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);

		WebDriverWait wait = new WebDriverWait(driver, 90);
		Utility.ScrollToElement(group.Check_Mailing_Info, driver);
		Wait.WaitForElement(driver, OR.Check_Mailing_Info);
		wait.until(ExpectedConditions.visibilityOf(group.Check_Mailing_Info));
		if (isUseMailingAdd) {
			if (!group.Check_Mailing_Info.isSelected()) {
				group.Check_Mailing_Info.click();
			}
		} else {
			if (group.Check_Mailing_Info.isSelected()) {
				group.Check_Mailing_Info.click();
			}
		}
		test_steps.add("Check Same as Mailing Address : " + isUseMailingAdd);
		groupLogger.info("Check Same as Mailing Address : " + isUseMailingAdd);

		Utility.ScrollToElement(group.GroupBillInfoCopyToPickUpReservation, driver);
		Wait.WaitForElement(driver, OR.GroupBillInfoCopyToPickUpReservation);
		wait.until(ExpectedConditions.visibilityOf(group.GroupBillInfoCopyToPickUpReservation));
		if (isCopyPickupRes) {
			if (!group.GroupBillInfoCopyToPickUpReservation.isSelected()) {
				group.GroupBillInfoCopyToPickUpReservation.click();
			}
		} else {
			if (group.GroupBillInfoCopyToPickUpReservation.isSelected()) {
				group.GroupBillInfoCopyToPickUpReservation.click();
			}
		}
		test_steps.add("Check Copy PickUp Reservation : " + isCopyPickupRes);
		groupLogger.info("Check Copy PickUp Reservation : " + isCopyPickupRes);

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_AdvGroup_SelectProperty, driver);
		Utility.ScrollToElement(group.Group_AdvGroup_SelectProperty, driver);
		new Select(group.Group_AdvGroup_SelectProperty).selectByVisibleText(Property);
		test_steps.add("Selected Property : " + Property);
		groupLogger.info("Selected Property : " + Property);

		Wait.explicit_wait_visibilityof_webelement_120(oldGroup.oldGroup_PaymentMethod, driver);
		new Select(oldGroup.oldGroup_PaymentMethod).selectByVisibleText(paymentType);
		test_steps.add("Selected Payment Method : " + paymentType);
		groupLogger.info("Selected Payment Method : " + paymentType);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupBilling_AccountNo, driver);
		group.GroupBilling_AccountNo.sendKeys(Keys.chord(Keys.CONTROL, "a"), accNo);
		test_steps.add("Entered Account No : " + accNo);
		groupLogger.info("Entered Account No : " + accNo);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupBilling_ExpiryDate, driver);
		group.GroupBilling_ExpiryDate.sendKeys(Keys.chord(Keys.CONTROL, "a"), expiryDate);
		test_steps.add("Entered Expiry Date : " + expiryDate);
		groupLogger.info("Entered Expiry Date : " + expiryDate);

		return test_steps;

	}

	// Select Property in PaymentDetails
	public ArrayList<String> SelectProperty_PaymentDetail(WebDriver driver, String PropertyName)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Groups group = new Elements_Groups(driver);
		Wait.wait10Second();

		String selectedOption = "";
		loadingImage(driver);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		Wait.WaitForElement(driver, OR.Group_AdvGroup_PaymentDetail_SelectProperty);
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_AdvGroup_PaymentDetail_SelectProperty, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_AdvGroup_PaymentDetail_SelectProperty, driver);
		Utility.ScrollToElement(group.Group_AdvGroup_PaymentDetail_SelectProperty, driver);
		new Select(group.Group_AdvGroup_PaymentDetail_SelectProperty).selectByVisibleText(PropertyName);
		test_steps.add(PropertyName + " selected in Payment Details");
		selectedOption = new Select(group.Group_AdvGroup_PaymentDetail_SelectProperty).getFirstSelectedOption()
				.getText();

		Assert.assertEquals(PropertyName, selectedOption);
		test_steps.add("Selected Property Verified in Payment Details");

		return test_steps;

	}

	public ArrayList<String> Make_Payment(WebDriver driver, ExtentTest test, String PaymentType,
			String Authorizationtype, String ChangeAmountValue, String Property, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> steps = new ArrayList<>();

		// Select Property in Property Details
		steps = SelectProperty_PaymentDetail(driver, Property);
		test_steps.addAll(steps);
		loadingImage(driver);
		// try {
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_AuthType, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_AuthType, driver);
		Utility.ScrollToElement(group.Group_Folio_AuthType, driver);
		Wait.wait5Second();
		Select dropdown = new Select(group.Group_Folio_AuthType);

		dropdown.selectByVisibleText(Authorizationtype);
		groupLogger.info("Select Authorization Type : " + Authorizationtype);
		test_steps.add("Select Authorization Type : " + Authorizationtype);
		Wait.explicit_wait_visibilityof_webelement(group.Group_Folio_AuthType, driver);
		Utility.app_logs.info(new Select(group.Group_Folio_AuthType).getFirstSelectedOption().getText());
		assertTrue(
				new Select(group.Group_Folio_AuthType).getFirstSelectedOption().getText().contains(Authorizationtype),
				"Failed : Authorization type is not selected");
		// }
		loadingImage(driver);
		// Wait.WaitForElement(driver, OR.Group_Folio_Amount);
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_Amount, driver);
		Utility.ScrollToElement(group.Group_Folio_Amount, driver);
		Wait.wait2Second();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		group.Group_Folio_Amount.clear();
		try {
			Wait.wait2Second();
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].setAttribute('value', '" + ChangeAmountValue + "')",
					group.Group_Folio_Amount);
			group.Group_Folio_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
			test_steps.add("Enter Amount to pay " + ChangeAmountValue);
			groupLogger.info("Enter Amount to pay " + ChangeAmountValue);
			// Extar Assert
			Utility.app_logs.info(new Select(group.Group_Folio_AuthType).getFirstSelectedOption().getText());
			assertTrue(new Select(group.Group_Folio_AuthType).getFirstSelectedOption().getText()
					.contains(Authorizationtype), "Failed : Authorization type is not selected");
			// Extar Assert
			Wait.explicit_wait_elementToBeClickable(group.Group_Folio_Process, driver);
			Wait.WaitForElement(driver, OR.Group_Folio_Process);
			group.Group_Folio_Process.click();
			test_steps.add("Clicking on Process");
			groupLogger.info("Clicking on Process");
			loadingImage(driver);
		} catch (Exception e) {
			String str_length = group.Group_Folio_Amount.getAttribute("value");
			for (int i = 0; i < str_length.length(); i++) {
				group.Group_Folio_Amount.sendKeys(Keys.BACK_SPACE);
			}
			group.Group_Folio_Amount.sendKeys(ChangeAmountValue);
			groupLogger.info("Enter Amount to pay " + ChangeAmountValue);
			Wait.WaitForElement(driver, OR.Group_Folio_Process);
			Wait.explicit_wait_elementToBeClickable(group.Group_Folio_Process, driver);
			Utility.ScrollToElement(group.Group_Folio_Process, driver);
			group.Group_Folio_Process.click();
			test_steps.add("Clicking on Process");
			groupLogger.info("Clicking on Process");
		}

		try {
			Wait.explicit_wait_visibilityof_webelement(group.AdvanceDepositConfirmPopup, driver);
			test_steps.add("Advance Deposit Popup Appears");
			groupLogger.info("Advance Deposit Popup Appears");
			Wait.explicit_wait_elementToBeClickable(group.AdvanceDepositConfirmPopup_Yes, driver);
			group.AdvanceDepositConfirmPopup_Yes.click();
			test_steps.add("Clicking Advance Deposit Yes Button");
			groupLogger.info("Clicking Advance Deposit Yes Button");
		} catch (Exception e) {
			// test_steps.add("NO Advance Deposit Popup Appear");
			groupLogger.info("NO Advance Deposit Popup");
		}

		// Wait.explicit_wait_elementToBeClickable(group.Click_Account_Pay_Continue,
		// driver);
		return test_steps;
	}

	public void VerifyPaymentPopup_LineItemWithIcon(WebDriver driver, String PaymentType, String Icon, String Amount,
			String Description, boolean link, boolean open) throws InterruptedException {
		// Verify payment
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		String Desc_Path = "(//div[@id='paymentDetails_DIV']//following-sibling::tbody)[11]//td[text()='" + Description
				+ "']/following-sibling::td//span[text()='" + Description + "']";
		if (link) {
			Desc_Path = "(//div[@id='paymentDetails_DIV']//following-sibling::tbody)[11]//td[text()='" + PaymentType
					+ "']/following-sibling::td/a";
		}
		String IconPath = "(//div[@id='paymentDetails_DIV']//following-sibling::tbody)[11]//td[text()='" + PaymentType
				+ "']/preceding-sibling::td/img";
		String AmountPath = "(//div[@id='paymentDetails_DIV']//following-sibling::tbody)[11]//td[text()='" + PaymentType
				+ "']/following-sibling::td/span";
		System.out.print(" DescPath:" + Description + " Path:" + Desc_Path);
		Wait.wait5Second();

		Utility.ScrollToElement((driver.findElement(By.xpath(Desc_Path))), driver);
		// Wait.WaitForElement(driver, Desc_Path);
		List<WebElement> Desc = driver.findElements(By.xpath(Desc_Path));
		List<WebElement> Icons = driver.findElements(By.xpath(IconPath));
		List<WebElement> Amounts = driver.findElements(By.xpath(AmountPath));
		// if (!Amount.contains("$")) {
		// Amount = "$ " + Amount.replaceAll(" ", "");
		// }
		int LineItem_Size = Desc.size();
		groupLogger.info("LineItem_Size : " + LineItem_Size);
		groupLogger.info("Expected Payment Type : " + PaymentType);
		groupLogger.info("Expected Line Item Icon : " + Icon);
		groupLogger.info("Expected Line Item Description : " + Description);
		groupLogger.info("Expected Line Item Amount : " + Amount);
		// int lineNumber = LineItem_Size - 1;
		// groupLogger.info("LineNumber : " + lineNumber);
		boolean found = false;
		int descIndex = 0;
		for (int lineNumber = 0; lineNumber <= LineItem_Size - 1; lineNumber++) {
			Utility.ScrollToElement(Desc.get(descIndex), driver);
			String desc = Desc.get(descIndex).getText();
			String IconSrc = Icons.get(0).getAttribute("src");
			String LineItemAmount = Amounts.get(0).getText();

			// Utility.app_logs.info("line Item Icon src: " + IconSrc);
			System.out.print("Amount :" + Amount.contains(LineItemAmount));
			System.out.print("Description :" + desc.contains(Description));
			System.out.print("IconSrc :" + IconSrc.contains(Icon) + "IconScr: " + IconSrc + "Icon:" + Icon);
			System.out.print(Amount.contains(LineItemAmount) && desc.contains(Description) && IconSrc.contains(Icon));

			if (Amount.contains(LineItemAmount) && desc.contains(Description) && IconSrc.contains(Icon)) {
				System.out.print("here in found");
				found = true;

				break;
			}
			if (link) {
				descIndex++;
			} else {
				descIndex = descIndex + 2;
			}
		}
		if (!found) {
			assertTrue(false, "Failed: Required item not found");
		}
		if (open) {
			System.out.println(" DescIndex:" + descIndex);
			Wait.explicit_wait_visibilityof_webelement_120(Desc.get(descIndex), driver);
			Wait.explicit_wait_elementToBeClickable(Desc.get(descIndex), driver);
			Utility.ScrollToElement(Desc.get(descIndex), driver);
			Desc.get(descIndex).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body1")));
			Wait.wait10Second();
			String TransactionDetailPath = "//div[@id='pnlTitle']//font[text()='Transaction Details']";
			WebElement TransactionDetail = driver.findElement(By.xpath(TransactionDetailPath));
			Wait.WaitForElement(driver, TransactionDetailPath);
			Wait.explicit_wait_visibilityof_webelement_150(TransactionDetail, driver);

			assertEquals(TransactionDetail.isDisplayed(), true, " Transaction Detail page didn't display");
		}
	}

	// Testing

	public void VerifyPaymentPopupLineItemWithIcon(WebDriver driver, String PaymentType, String Icon, String Amount,
			String Description, boolean link, boolean open) throws InterruptedException {
		// Verify payment
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		String Desc_Path = "(//div[@id='paymentDetails_DIV']//following-sibling::tbody)[11]//td[text()='" + Description
				+ "']/following-sibling::td//span[text()='" + Description + "']";
		if (link) {
			Desc_Path = "(//div[@id='paymentDetails_DIV']//following-sibling::tbody)[11]//td[text()='" + PaymentType
					+ "']/preceding-sibling::td/img[@src='" + Icon + "']/parent::td/following-sibling::td/a";
		}
		String IconPath = "(//div[@id='paymentDetails_DIV']//following-sibling::tbody)[11]//td[text()='" + PaymentType
				+ "']/preceding-sibling::td/img";
		String AmountPath = "(//div[@id='paymentDetails_DIV']//following-sibling::tbody)[11]//td[text()='" + PaymentType
				+ "']/following-sibling::td/span";
		System.out.print(" DescPath:" + Description + " Path:" + Desc_Path);
		Wait.wait5Second();
		Wait.WaitForElement(driver, Desc_Path);
		// Utility.ScrollToElement((driver.findElement(By.xpath(Desc_Path))),
		// driver);
		WebElement Desc = driver.findElement(By.xpath(Desc_Path));
		WebElement Icons = driver.findElement(By.xpath(IconPath));
		WebElement Amounts = driver.findElement(By.xpath(AmountPath));
		// if (!Amount.contains("$")) {
		// Amount = "$ " + Amount.replaceAll(" ", "");
		// }
		// int LineItem_Size = Desc.size();
		// groupLogger.info("LineItem_Size : " + LineItem_Size);
		groupLogger.info("Expected Payment Type : " + PaymentType);
		groupLogger.info("Expected Line Item Icon : " + Icon);
		groupLogger.info("Expected Line Item Description : " + Description);
		groupLogger.info("Expected Line Item Amount : " + Amount);
		// int lineNumber = LineItem_Size - 1;
		// groupLogger.info("LineNumber : " + lineNumber);
		boolean found = false;
		int descIndex = 0;

		if (Amounts.getText().contains(Amount.replace("$", "").replace(" ", ""))
				&& Desc.getText().replaceAll(" ", "").contains(Description.replaceAll(" ", ""))
				&& Icons.getText().contains(Icon)) {
			System.out.print("here in found");
			found = true;

			// break;
		}
		if (link) {
			descIndex++;
		} else {
			descIndex = descIndex + 2;
		}

		if (open)

		{
			System.out.println(" DescIndex:" + descIndex);
			Wait.explicit_wait_visibilityof_webelement_120(Desc, driver);
			Wait.explicit_wait_elementToBeClickable(Desc, driver);
			Utility.ScrollToElement(Desc, driver);
			Desc.click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body1")));
			Wait.wait10Second();
			String TransactionDetailPath = "//div[@id='pnlTitle']//font[text()='Transaction Details']";
			WebElement TransactionDetail = driver.findElement(By.xpath(TransactionDetailPath));
			Wait.WaitForElement(driver, TransactionDetailPath);
			Wait.explicit_wait_visibilityof_webelement_150(TransactionDetail, driver);

			assertEquals(TransactionDetail.isDisplayed(), true, " Transaction Detail page didn't display");
		}
	}

	public ArrayList<String> VerifyTransactionDetail(WebDriver driver, String CardName, String Authorizationtype,
			String Amount) {

		ArrayList<String> test_steps = new ArrayList<String>();
		WebElement GetCardNumber = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td//span[text()='Card number:']/ancestor::td/following-sibling::td/span"));
		WebElement GetCardName = driver.findElement(By
				.xpath("//div[@id='paymentTransDetails_DIV']//td[text()='Name On Card:']/following-sibling::td//span"));
		WebElement GetAuthorizationtype = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td//span[text()='Authorization Type:']/ancestor::td/following-sibling::td/span"));
		WebElement GetApprovalCode = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td//span[text()='Approval Code:']/ancestor::td/following-sibling::td/span"));
		WebElement GetAmount = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td//span[text()='Amount:']/ancestor::td/following-sibling::td/span"));
		WebElement GetCVVStatus = driver.findElement(
				By.xpath("//div[@id='paymentTransDetails_DIV']//td[text()='CVV Status:']/following-sibling::td/span"));
		WebElement GetAVSResponse = driver.findElement(By
				.xpath("//div[@id='paymentTransDetails_DIV']//td[text()='AVS Response:']/following-sibling::td/span"));
		WebElement GetResponseCodeText = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td[text()='Response Code Text:']/following-sibling::td/span"));
		WebElement GetTransactionCondition = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td[text()='Transaction Condition:']/following-sibling::td/span"));
		// WebElement GetOrderID = driver.findElement(By.xpath(
		// "//div[contains(@class,'AccountPaymentTransDetail')]//label[contains(@data-bind,'text:
		// orderID')]"));

		// Verification Code
		// try {
		// if (!GetOrderID.equals("")) {
		assertEquals(CardName.equalsIgnoreCase(GetCardName.getText()), true, "Card Name not verified");
		Utility.app_logs.info("Card Name : " + GetCardName.getText() + " : Verified");
		test_steps.add("Card Name : " + GetCardName.getText() + " : Verified");

		assertEquals(Authorizationtype.equalsIgnoreCase(GetAuthorizationtype.getText()), true,
				"Authorizationtype not verified");
		Utility.app_logs.info("Authorization Type :" + GetAuthorizationtype.getText() + " : Verified");
		test_steps.add("Authorization Type :" + GetAuthorizationtype.getText() + " : Verified");
		if (!Amount.contains("$"))
			Amount = "$ " + Amount;
		Utility.app_logs.info(GetAmount.getText());
		Utility.app_logs.info(Amount);
		Utility.app_logs.info("Amount : " + GetAmount.getText() + ": Verified");
		test_steps.add("Amount : " + GetAmount.getText() + ": Verified");
		assertEquals(Amount.equals(GetAmount.getText()), true, "Amount notverified");
		//
		// assertEquals(GetApprovalCode.getText().equalsIgnoreCase("TEST"),
		// true, "Approval Code not verified");
		// Utility.app_logs.info("Approval Code :" + GetApprovalCode.getText() +
		// ": Verified");
		// test_steps.add("Approval Code :" + GetApprovalCode.getText() + ":
		// Verified");
		//
		// assertEquals(
		// GetCVVStatus.getText().equals("") ||
		// GetCVVStatus.getText().equalsIgnoreCase(
		// "ISSUER HAS NOT CERTIFIED FOR CVV2 OR ISSUER HAS NOT PROVIDED VISA
		// WITH THE CVV2 ENCRYPTION KEYS"),
		// true, "Approval Code not verified");
		// Utility.app_logs.info("CVV Status : " + GetCVVStatus.getText() + "
		// :Verified");
		// test_steps.add("CVV Status : " + GetCVVStatus.getText() + "
		// :Verified");
		//
		// assertEquals(
		// GetAVSResponse.getText().equals("") ||
		// GetAVSResponse.getText().equalsIgnoreCase(
		// "No Match - Street address, 5-digit ZIP code, and 9-digit ZIP code
		// all do not match"),
		// true, "AVS Response not verified");
		// Utility.app_logs.info("AVS Response :" + GetAVSResponse.getText() +
		// ": Verified");
		// test_steps.add("AVS Response :" + GetAVSResponse.getText() + ":
		// Verified");
		//
		assertEquals(GetResponseCodeText.getText().equalsIgnoreCase("APPROVED"), true,
				"Response Code Text not verified");
		Utility.app_logs.info("Response Code :" + GetResponseCodeText.getText() + " : Verified");
		test_steps.add("Response Code :" + GetResponseCodeText.getText() + ": Verified");

		assertEquals(GetTransactionCondition.getText().equalsIgnoreCase("CARDHOLDER NOT PRESENT. TELEPHONE ORDER"),
				true, "Transaction Condition not verified");
		Utility.app_logs.info("Transaction Condtion" + GetTransactionCondition.getText() + " : Verified");
		test_steps.add("Transaction Condtion" + GetTransactionCondition.getText() + " : Verified");
		// test_steps.add(GetOrderID.getText());
		// }
		return test_steps;
	}

	public ArrayList<String> VerifyTransactionDetail_Credit(WebDriver driver, String CardName, String Authorizationtype,
			String Amount) {

		ArrayList<String> test_steps = new ArrayList<String>();
		WebElement GetCardNumber = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td//span[text()='Card number:']/ancestor::td/following-sibling::td/span"));
		WebElement GetCardName = driver.findElement(By
				.xpath("//div[@id='paymentTransDetails_DIV']//td[text()='Name On Card:']/following-sibling::td//span"));
		WebElement GetAuthorizationtype = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td//span[text()='Authorization Type:']/ancestor::td/following-sibling::td/span"));
		WebElement GetApprovalCode = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td//span[text()='Approval Code:']/ancestor::td/following-sibling::td/span"));
		WebElement GetAmount = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td//span[text()='Amount:']/ancestor::td/following-sibling::td/span"));
		WebElement GetCVVStatus = driver.findElement(
				By.xpath("//div[@id='paymentTransDetails_DIV']//td[text()='CVV Status:']/following-sibling::td/span"));
		WebElement GetAVSResponse = driver.findElement(By
				.xpath("//div[@id='paymentTransDetails_DIV']//td[text()='AVS Response:']/following-sibling::td/span"));
		WebElement GetResponseCodeText = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td[text()='Response Code Text:']/following-sibling::td/span"));
		WebElement GetTransactionCondition = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td[text()='Transaction Condition:']/following-sibling::td/span"));
		WebElement GetOrderID = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td//span[text()='Order Number:']/ancestor::td/following-sibling::td/span"));

		// Verification Code
		// try {
		// if (!GetOrderID.equals("")) {
		assertEquals(CardName.equalsIgnoreCase(GetCardName.getText()), true, "Card Name not verified");
		Utility.app_logs.info("Card Name : " + GetCardName.getText() + " : Verified");
		test_steps.add("Card Name : " + GetCardName.getText() + " : Verified");

		assertEquals(Authorizationtype.equalsIgnoreCase(GetAuthorizationtype.getText()), true,
				"Authorizationtype not verified");
		Utility.app_logs.info("Authorization Type :" + GetAuthorizationtype.getText() + " : Verified");
		test_steps.add("Authorization Type :" + GetAuthorizationtype.getText() + " : Verified");
		if (!Amount.contains("$"))
			Amount = "$ " + Amount;
		Utility.app_logs.info(GetAmount.getText());
		Utility.app_logs.info(Amount);
		Utility.app_logs.info("Amount : " + GetAmount.getText() + ": Verified");
		test_steps.add("Amount : " + GetAmount.getText() + ": Verified");
		assertEquals(Amount.equals(GetAmount.getText()), true, "Amount notverified");
		//
		// assertEquals(GetApprovalCode.getText().equalsIgnoreCase("TEST"),
		// true, "Approval Code not verified");
		// Utility.app_logs.info("Approval Code :" + GetApprovalCode.getText() +
		// ": Verified");
		// test_steps.add("Approval Code :" + GetApprovalCode.getText() + ":
		// Verified");
		//
		// assertEquals(
		// GetCVVStatus.getText().equals("") ||
		// GetCVVStatus.getText().equalsIgnoreCase(
		// "ISSUER HAS NOT CERTIFIED FOR CVV2 OR ISSUER HAS NOT PROVIDED VISA
		// WITH THE CVV2 ENCRYPTION KEYS"),
		// true, "Approval Code not verified");
		// Utility.app_logs.info("CVV Status : " + GetCVVStatus.getText() + "
		// :Verified");
		// test_steps.add("CVV Status : " + GetCVVStatus.getText() + "
		// :Verified");
		//
		// assertEquals(
		// GetAVSResponse.getText().equals("") ||
		// GetAVSResponse.getText().equalsIgnoreCase(
		// "No Match - Street address, 5-digit ZIP code, and 9-digit ZIP code
		// all do not match"),
		// true, "AVS Response not verified");
		// Utility.app_logs.info("AVS Response :" + GetAVSResponse.getText() +
		// ": Verified");
		// test_steps.add("AVS Response :" + GetAVSResponse.getText() + ":
		// Verified");
		//
		assertEquals(GetResponseCodeText.getText().equalsIgnoreCase("APPROVED"), true,
				"Response Code Text not verified");
		Utility.app_logs.info("Response Code :" + GetResponseCodeText.getText() + " : Verified");
		test_steps.add("Response Code :" + GetResponseCodeText.getText() + ": Verified");

		assertEquals(GetTransactionCondition.getText().equalsIgnoreCase("CARDHOLDER NOT PRESENT. TELEPHONE ORDER"),
				true, "Transaction Condition not verified");
		Utility.app_logs.info("Transaction Condtion" + GetTransactionCondition.getText() + " : Verified");
		test_steps.add("Transaction Condtion" + GetTransactionCondition.getText() + " : Verified");
		test_steps.add(GetOrderID.getText());
		// }
		return test_steps;
	}

	public ArrayList<String> VerifyTransactionDetail_Diffcard(WebDriver driver, String CardName,
			String Authorizationtype, String Amount, String ApprovalCode, String CVVStatus, String AVSResponse,
			String ResponseCode, String TransactionCondition) {

		ArrayList<String> test_steps = new ArrayList<String>();
		WebElement GetCardNumber = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td//span[text()='Card number:']/ancestor::td/following-sibling::td/span"));
		WebElement GetCardName = driver.findElement(By
				.xpath("//div[@id='paymentTransDetails_DIV']//td[text()='Name On Card:']/following-sibling::td//span"));
		WebElement GetAuthorizationtype = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td//span[text()='Authorization Type:']/ancestor::td/following-sibling::td/span"));
		WebElement GetApprovalCode = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td//span[text()='Approval Code:']/ancestor::td/following-sibling::td/span"));
		WebElement GetAmount = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td//span[text()='Amount:']/ancestor::td/following-sibling::td/span"));
		WebElement GetCVVStatus = driver.findElement(
				By.xpath("//div[@id='paymentTransDetails_DIV']//td[text()='CVV Status:']/following-sibling::td/span"));
		WebElement GetAVSResponse = driver.findElement(By
				.xpath("//div[@id='paymentTransDetails_DIV']//td[text()='AVS Response:']/following-sibling::td/span"));
		WebElement GetResponseCodeText = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td[text()='Response Code Text:']/following-sibling::td/span"));
		WebElement GetTransactionCondition = driver.findElement(By.xpath(
				"//div[@id='paymentTransDetails_DIV']//td[text()='Transaction Condition:']/following-sibling::td/span"));
		// WebElement GetOrderID = driver.findElement(By.xpath(
		// "//div[contains(@class,'AccountPaymentTransDetail')]//label[contains(@data-bind,'text:
		// orderID')]"));

		// Verification Code
		// try {
		// if (!GetOrderID.equals("")) {
		assertEquals(CardName.equalsIgnoreCase(GetCardName.getText()), true, "Card Name not verified");
		Utility.app_logs.info("Card Name : " + GetCardName.getText() + " : Verified");
		test_steps.add("Card Name : " + GetCardName.getText() + " : Verified");

		assertEquals(Authorizationtype.equalsIgnoreCase(GetAuthorizationtype.getText()), true,
				"Authorizationtype not verified");
		Utility.app_logs.info("Authorization Type :" + GetAuthorizationtype.getText() + " : Verified");
		test_steps.add("Authorization Type :" + GetAuthorizationtype.getText() + " : Verified");
		if (!Amount.contains("$"))
			Amount = "$ " + Amount;
		Utility.app_logs.info(GetAmount.getText());
		Utility.app_logs.info(Amount);
		Utility.app_logs.info("Amount : " + GetAmount.getText() + ": Verified");
		test_steps.add("Amount : " + GetAmount.getText() + ": Verified");
		assertEquals(Amount.equals(GetAmount.getText()), true, "Amount notverified");
		//
		// assertEquals(GetApprovalCode.getText().equalsIgnoreCase("TEST"),
		// true, "Approval Code not verified");
		// Utility.app_logs.info("Approval Code :" + GetApprovalCode.getText() +
		// ": Verified");
		// test_steps.add("Approval Code :" + GetApprovalCode.getText() + ":
		// Verified");
		//
		// assertEquals(
		// GetCVVStatus.getText().equals("") ||
		// GetCVVStatus.getText().equalsIgnoreCase(
		// "ISSUER HAS NOT CERTIFIED FOR CVV2 OR ISSUER HAS NOT PROVIDED VISA
		// WITH THE CVV2 ENCRYPTION KEYS"),
		// true, "Approval Code not verified");
		// Utility.app_logs.info("CVV Status : " + GetCVVStatus.getText() + "
		// :Verified");
		// test_steps.add("CVV Status : " + GetCVVStatus.getText() + "
		// :Verified");
		//
		// assertEquals(
		// GetAVSResponse.getText().equals("") ||
		// GetAVSResponse.getText().equalsIgnoreCase(
		// "No Match - Street address, 5-digit ZIP code, and 9-digit ZIP code
		// all do not match"),
		// true, "AVS Response not verified");
		// Utility.app_logs.info("AVS Response :" + GetAVSResponse.getText() +
		// ": Verified");
		// test_steps.add("AVS Response :" + GetAVSResponse.getText() + ":
		// Verified");
		//
		assertEquals(GetResponseCodeText.getText().equalsIgnoreCase("APPROVED"), true,
				"Response Code Text not verified");
		Utility.app_logs.info("Response Code :" + GetResponseCodeText.getText() + " : Verified");
		test_steps.add("Response Code :" + GetResponseCodeText.getText() + ": Verified");

		assertEquals(GetTransactionCondition.getText().equalsIgnoreCase("CARDHOLDER NOT PRESENT. TELEPHONE ORDER"),
				true, "Transaction Condition not verified");
		Utility.app_logs.info("Transaction Condtion" + GetTransactionCondition.getText() + " : Verified");
		test_steps.add("Transaction Condtion" + GetTransactionCondition.getText() + " : Verified");
		// test_steps.add(GetOrderID.getText());
		// }
		return test_steps;
	}

	public void CloseTransactionDetails(WebDriver driver) {
		WebElement CloseButton = driver.findElement(By.xpath("//input[@id='btnCancel']"));
		CloseButton.click();
		// Wait.waitForElementToBeGone(driver, CloseButton, 60);
	}

	public void payDetailContinueBtn(WebDriver driver) throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		Wait.explicit_wait_visibilityof_webelement_150(group.Group_Folio_Continue, driver);
		Utility.ScrollToElement(group.Group_Folio_Continue, driver);
		group.Group_Folio_Continue.click();
		Utility.app_logs.info("Clicked on continue button");
		try {
			Wait.waitForElementToBeGone(driver, group.Group_Folio_Continue, 90);
		} catch (Exception e) {

		}
		driver.switchTo().defaultContent();

	}

	public void payDetailCloseBtn(WebDriver driver) throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		Wait.explicit_wait_visibilityof_webelement_150(group.Group_Folio_Close, driver);
		Utility.ScrollToElement(group.Group_Folio_Close, driver);
		group.Group_Folio_Close.click();
		Utility.app_logs.info("Clicked on close button");
		try {
			Wait.waitForElementToBeGone(driver, group.Group_Folio_Close, 90);
		} catch (Exception e) {

		}
		driver.switchTo().defaultContent();

	}

	public void Verify_LineItemWithIcon(WebDriver driver, String PaymentType, String Icon, String Category,
			String Amount, String Description, boolean OpenDescription) throws InterruptedException {
		// Verify payment
		// if (!Amount.contains("$")) {
		// Amount = "$ " + Amount.replaceAll(" ", "");
		// }
		Wait.wait2Second();
		List<WebElement> AddedLineItems_Category = driver.findElements(By.xpath(
				"//div[@id='MainContent_Folio1_Ultrawebgrid3_div']//tbody//td//span[text()='" + PaymentType + "']"));
		List<WebElement> AddedLineItems_Description = driver
				.findElements(By.xpath("//div[@id='MainContent_Folio1_Ultrawebgrid3_div']//tbody//td//span[text()='"
						+ PaymentType + "']/parent::td/following-sibling::td//a"));
		List<WebElement> AddedLineItems_Icon = driver
				.findElements(By.xpath("//div[@id='MainContent_Folio1_Ultrawebgrid3_div']//tbody//td//span[text()='"
						+ PaymentType + "']/parent::td/preceding-sibling::td//input[@type='image']"));
		WebElement AddedLineItems_Amount = driver
				.findElement(By.xpath("//div[@id='MainContent_Folio1_Ultrawebgrid3_div']//tbody//td//span[text()='"
						+ PaymentType + "']/parent::td/following-sibling::td//span[text()='" + Amount + "']"));
		// Wait.explicit_wait_visibilityof_webelement_350(AddedLineItems_Category.get(0),
		// driver);
		int LineItem_Size = AddedLineItems_Category.size();
		groupLogger.info("LineItem_Size : " + LineItem_Size);
		groupLogger.info("Expected Line Item Category : " + Category);
		groupLogger.info("Expected Line Item Icon : " + Icon);
		groupLogger.info("Expected Line Item Description : " + Description);
		groupLogger.info("Expected Line Item Amount : " + Amount);
		// int lineNumber = LineItem_Size - 1;
		boolean found = false;
		Wait.explicit_wait_visibilityof_webelement_350(AddedLineItems_Category.get(LineItem_Size - 1), driver);
		for (int lineNumber = 0; lineNumber <= LineItem_Size - 1; lineNumber++) {
			groupLogger.info("LineNumber : " + lineNumber);

			String desc = AddedLineItems_Description.get(lineNumber).getText();
			String IconSrc = AddedLineItems_Icon.get(lineNumber).getAttribute("src");
			String LineItemAmount = AddedLineItems_Amount.getText();
			String LineItemCategory = AddedLineItems_Category.get(lineNumber).getText();
			groupLogger.info("LineNumber : " + lineNumber);
			groupLogger.info("Category : " + LineItemCategory);
			groupLogger.info("Amount : " + LineItemAmount);
			groupLogger.info("Description : " + desc);
			Utility.app_logs.info("line Item Icon  src: " + IconSrc);
			// System.out.print(LineItemCategory.equalsIgnoreCase(Category));
			System.out.print(desc.contains(Description));
			System.out.print(LineItemAmount.contains(Amount));
			System.out.print(IconSrc.contains(Icon));
			// System.out.print("des****:" + desc.replaceAll(" ", ""));
			// System.out.print("Desc****:" + Description.replaceAll(" ", ""));

			if (LineItemCategory.equalsIgnoreCase(Category) && LineItemAmount.contains(Amount)
					&& desc.contains(Description) && IconSrc.contains(Icon)) {
				Utility.ScrollToElement(AddedLineItems_Description.get(lineNumber), driver);
				found = true;
				if (OpenDescription) {
					AddedLineItems_Description.get(lineNumber).click();
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
					Wait.WaitForElement(driver, OR.Group_AdvGroup_PaymentDetail_SelectProperty);
					Wait.explicit_wait_visibilityof_webelement(
							driver.findElement(By.xpath(OR.Group_AdvGroup_PaymentDetail_SelectProperty)), driver);
				}
				break;
			}
		}
		if (!found) {
			assertTrue(false, "Failed: Required item not found");
		}
	}

	public ArrayList<String> CancelAuthorizePaymentAccounts(WebDriver driver, String Authorizationtype,
			boolean isChangeAmount, String ChangeAmountValue, String Note) {

		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Groups group = new Elements_Groups(driver);

		Elements_Accounts AccFolio = new Elements_Accounts(driver);
		try {
			Wait.explicit_wait_elementToBeClickable(group.Group_Folio_AuthType, driver);
			new Select(group.Group_Folio_AuthType).selectByVisibleText(Authorizationtype);
			groupLogger.info("Select Authorization Type : " + Authorizationtype);
			test_steps.add("Select Authorization Type : " + Authorizationtype);
			Wait.wait3Second();
			Utility.app_logs.info(new Select(group.Group_Folio_AuthType).getFirstSelectedOption().getText());
			assertTrue(new Select(group.Group_Folio_AuthType).getFirstSelectedOption().getText()
					.contains(Authorizationtype), "Failed : Authorization type is not selected");

			Utility.ScrollToElement(group.AdvGroup_Notes, driver);
			group.AdvGroup_Notes.sendKeys(Note);

			Utility.app_logs.info("Enter Cancel Notes in Payment popup : " + Note);
			test_steps.add("Enter Cancel Notes in Payment popup : " + Note);
			Utility.ScrollToElement(group.Group_Folio_Process, driver);
			group.Group_Folio_Process.click();
			Utility.app_logs.info("Clicked on Process Button");
			test_steps.add("Click Process Button");
			try {
				Wait.explicit_wait_elementToBeClickable(group.Group_Folio_Continue, driver);
			} catch (Exception e) {
				Utility.ScrollToElement(group.Group_Folio_Continue, driver);
				group.Group_Folio_Continue.click();
				Utility.app_logs.info("Again Clicked on Process Button");
				test_steps.add("Click Process Button");
				Wait.explicit_wait_elementToBeClickable(group.Group_Folio_Continue, driver);
			}
		} catch (Exception e) {

			Utility.app_logs.info("Exception occured while paying using MC \n");
			e.printStackTrace();
		}
		return test_steps;
	}

	public ArrayList<String> clickSwipeCardIcon(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		Wait.explicit_wait_visibilityof_webelement_120(group.GroupSwipeIcon, driver);
		Wait.explicit_wait_elementToBeClickable(group.GroupSwipeIcon, driver);
		Utility.ScrollToElement(group.GroupSwipeIcon, driver);
		group.GroupSwipeIcon.click();
		groupLogger.info("Swipe Icon Clicked");
		test_steps.add("Swipe Icon Clicked");
		return test_steps;
	}

	public ArrayList<String> swipeCard(WebDriver driver, String SwipeCardNo) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupSwipeTextTrackData, driver);
		group.GroupSwipeTextTrackData.clear();
		group.GroupSwipeTextTrackData.sendKeys(SwipeCardNo);
		groupLogger.info("Successfully Card Swiped");
		test_steps.add("Successfully Card Swiped");

		Wait.explicit_wait_visibilityof_webelement_120(group.GroupSwipeSubmitButton, driver);
		Wait.explicit_wait_elementToBeClickable(group.GroupSwipeSubmitButton, driver);
		Utility.ScrollToElement(group.GroupSwipeSubmitButton, driver);
		group.GroupSwipeSubmitButton.click();
		groupLogger.info("Swipe Submit Clicked");
		test_steps.add("Swipe Submit Clicked");

		return test_steps;
	}

	public ArrayList<String> roomingListPopup_BillingInfo(WebDriver driver, String firstName, String lastName,
			String phonenumber, String address1, String Country, String State, String City)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();

		// driver.switchTo().frame("dialog-body0");
		Elements_Groups group2 = new Elements_Groups(driver);
		Wait.waitUntilPresenceOfElementLocated("//*[@id='frmRoomingListBillingInfo']", driver);

		Wait.explicit_wait_visibilityof_webelement_120(group2.RoomingListBillingInfo_FirstName, driver);
		group2.RoomingListBillingInfo_FirstName.clear();
		group2.RoomingListBillingInfo_FirstName.sendKeys(firstName);

		test_steps.add("Entered First Name in Billing Info Popup: " + firstName);
		groupLogger.info("Entered First Name in Billing Info Popup: " + firstName);

		Wait.explicit_wait_visibilityof_webelement_120(group2.RoomingListBillingInfo_LastName, driver);
		group2.RoomingListBillingInfo_LastName.clear();
		group2.RoomingListBillingInfo_LastName.sendKeys(lastName);

		test_steps.add("Entered Last Name in Billing Info Popup: " + lastName);
		groupLogger.info("Entered Last Name in Billing Info Popup: " + lastName);

		Wait.explicit_wait_visibilityof_webelement_120(group2.RoomingListBillingInfo_PhoneNo, driver);
		group2.RoomingListBillingInfo_PhoneNo.sendKeys(phonenumber);

		test_steps.add("Entered PhoneNo in Billing Info Popup: " + phonenumber);
		groupLogger.info("Entered PhoneNo in Billing Info Popup: " + phonenumber);

		Wait.explicit_wait_visibilityof_webelement_120(group2.RoomingListBillingInfo_Address1, driver);
		group2.RoomingListBillingInfo_Address1.sendKeys(address1);

		test_steps.add("Entered Address in Billing Info Popup: " + address1);
		groupLogger.info("Entered Address in Billing Info Popup: " + address1);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.id("drpBilling_countryID")), driver);
		new Select(driver.findElement(By.id("drpBilling_countryID"))).selectByVisibleText(Country);
		test_steps.add("Selected Country in Billing Info Popup : " + Country);
		groupLogger.info("Selected Country in Billing Info Popup : " + Country);

		Assert.assertEquals(
				new Select(driver.findElement(By.id("drpBilling_countryID"))).getFirstSelectedOption().getText(),
				Country, "Failed Country Not Matched in Billing Info");
		test_steps.add("Successfully Verified Selected Country in Billing Info Popup: " + Country);
		groupLogger.info("Successfully Verified Selected Country in Billing Info Popup: " + Country);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.id("drpBilling_territoryID")), driver);
		new Select(driver.findElement(By.id("drpBilling_territoryID"))).selectByVisibleText(State);
		test_steps.add("Selected State in Billing Info Popup : " + State);
		groupLogger.info("Selected State in Billing Info Popup : " + State);

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.id("txtBilling_city")), driver);
		driver.findElement(By.id("txtBilling_city")).clear();
		driver.findElement(By.id("txtBilling_city")).sendKeys(City);
		test_steps.add("Entered City in Billing Info Popup : " + City);
		groupLogger.info("Entered City in Billing Info Popup : " + City);

		return test_steps;
	}

	public ArrayList<String> roomingListPopup_BillingInfoPayments(WebDriver driver, String paymentMethod)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group1 = new Elements_AdvanceGroups(driver);

		Wait.explicit_wait_visibilityof_webelement_120(group1.BillingInfo_PaymentMethod, driver);
		new Select(group1.BillingInfo_PaymentMethod).selectByVisibleText(paymentMethod);

		test_steps.add("Successfully Selected Payment Method : " + paymentMethod);
		groupLogger.info("Successfully Selected Payment Method : " + paymentMethod);

		return test_steps;
	}

	public ArrayList<String> roomingListPopup_BillingInfoExpiry(WebDriver driver, String expiryDate)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();

		Elements_Groups grp = new Elements_Groups(driver);

		Wait.explicit_wait_visibilityof_webelement_120(grp.RoomingListBilling_ExpiryDate, driver);
		grp.RoomingListBilling_ExpiryDate.clear();
		grp.RoomingListBilling_ExpiryDate.sendKeys(expiryDate);

		test_steps.add("Entered Expiry Date in Billing Info Popup: " + expiryDate);
		groupLogger.info("Entered Expiry Date in Billing Info Popup: " + expiryDate);

		return test_steps;
	}

	public ArrayList<String> clickNewBlock(WebDriver driver, String blockName) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_xpath(OR.Click_New_Block_button, driver);
		Wait.explicit_wait_visibilityof_webelement_150(group.Click_New_Block_button, driver);
		Utility.ScrollToElement(group.Click_New_Block_button, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_New_Block_button, driver);
		group.Click_New_Block_button.click();
		test_steps.add("New Block Button Clicked");
		groupLogger.info("New Block Button Clicked");

		Wait.explicit_wait_xpath(OR.Enter_Block_Name, driver);
		Wait.explicit_wait_visibilityof_webelement_600(group.Enter_Block_Name, driver);
		group.Enter_Block_Name.sendKeys(blockName);
		test_steps.add("Entered New Block Name : " + blockName);
		groupLogger.info("Entered New Block Name : " + blockName);

		Wait.explicit_wait_xpath(OR.Click_Ok, driver);
		Wait.explicit_wait_visibilityof_webelement_150(group.Click_Ok, driver);
		Utility.ScrollToElement(group.Click_Ok, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Ok, driver);
		group.Click_Ok.click();
		test_steps.add("OK Button Clicked");
		groupLogger.info("OK Button Clicked");

		return test_steps;
	}

	public ArrayList<String> fillReqBlockFeilds(WebDriver driver, boolean isDepartSet, int departDay,
			String roomPerNight) throws InterruptedException, ParseException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		// String calPath = "//*[@id='trArrive']/td[2]/img";
		Wait.explicit_wait_xpath(OR.Select_Arrival_Date_Groups, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Select_Arrival_Date_Groups, driver);
		Wait.explicit_wait_elementToBeClickable(group.Select_Arrival_Date_Groups, driver);
		group.Select_Arrival_Date_Groups.click();
		test_steps.add("Select Arrival Date");
		groupLogger.info("Select Arrival Date");

		// String todayPath = "//div[1]/table/tfoot/tr[1]/th[text()='Today']";
		Wait.explicit_wait_xpath(OR.Click_Today_Arrival_Groups, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Click_Today_Arrival_Groups, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Today_Arrival_Groups, driver);
		group.Click_Today_Arrival_Groups.click();
		test_steps.add("Today Clicked as Arrival Date");
		groupLogger.info("Today Clicked as Arrival Date");

		if (isDepartSet) {
			Wait.explicit_wait_xpath(OR.Select_Depart_Date_Groups, driver);
			Wait.explicit_wait_visibilityof_webelement_200(group.Select_Depart_Date_Groups, driver);
			Wait.explicit_wait_elementToBeClickable(group.Select_Depart_Date_Groups, driver);
			group.Select_Depart_Date_Groups.click();
			test_steps.add("Select Depart Date");
			groupLogger.info("Select Depart Date");

			String nextDate = ESTTimeZone.getDateforAccountBalance(departDay);
			String todayDate = ESTTimeZone.getDateforAccountBalance(0);
			test_steps.add("Server date : " + todayDate);
			test_steps.add("Searched date : " + nextDate);

			String select_date = "//td[@title='" + nextDate + "']";
			System.out.println(select_date);
			WebElement element = driver.findElement(By.xpath(select_date));
			element.click();
		}

		Wait.explicit_wait_xpath(OR.Enter_No_of_Nigts, driver);
		Wait.explicit_wait_visibilityof_webelement_200(group.Enter_No_of_Nigts, driver);
		Wait.explicit_wait_elementToBeClickable(group.Enter_No_of_Nigts, driver);
		group.Enter_No_of_Nigts.click();
		group.Enter_No_of_Nigts.sendKeys(roomPerNight);
		test_steps.add("Entered Room Per Night : " + roomPerNight);
		groupLogger.info("Entered Room Per Night : " + roomPerNight);

		// Wait.explicit_wait_visibilityof_webelement_150(group.ratePlan,
		// driver);
		// new Select(group.ratePlan).selectByVisibleText(rackRate);
		// test_steps.add("Selected Rate Plan : " + rackRate);
		// groupLogger.info("Selected Rate Plan : " + rackRate);

		return test_steps;
	}

	public ArrayList<String> modify_LineItem(WebDriver driver, String DetailCategory, String DetailAmount,
			String DetailDescription, String LineNo) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups adGrp = new Elements_AdvanceGroups(driver);
		String text = "//input[@id='MainContent_Folio1_dgLineItems_chkSelectFolioItem_" + LineNo
				+ "']/../../td[7]/table/tbody/tr/td/a";
		Wait.WaitForElement(driver, text);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(text)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(text)), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(text)), driver);
		driver.findElement(By.xpath(text)).click();
		test_steps.add("Clicking on Item link");
		groupLogger.info("Clicking on Item link");
		loadingImage(driver);
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		loadingImage(driver);

		Wait.explicit_wait_visibilityof_webelement_120(adGrp.Select_CategoryItemDetail, driver);
		new Select(adGrp.Select_CategoryItemDetail).selectByVisibleText(DetailCategory);

		Wait.explicit_wait_visibilityof_webelement_120(adGrp.DescriptionItemDetail, driver);
		adGrp.DescriptionItemDetail.clear();
		adGrp.DescriptionItemDetail.sendKeys(DetailDescription);

		Wait.explicit_wait_visibilityof_webelement_120(adGrp.AmountItemDetail, driver);
		adGrp.AmountItemDetail.clear();
		adGrp.AmountItemDetail.sendKeys(DetailAmount);

		Wait.explicit_wait_visibilityof_webelement_120(adGrp.AddItemDetail, driver);
		Wait.explicit_wait_elementToBeClickable(adGrp.AddItemDetail, driver);
		Utility.ScrollToElement(adGrp.AddItemDetail, driver);
		adGrp.AddItemDetail.click();
		test_steps.add("Clicking on Add Button");
		groupLogger.info("Clicking on Add Button");

		String detailCatPath = "//*[@id='dgTransactionList']//td[contains(text(),'" + DetailCategory + "')]";
		String detailAmountPath = "//*[@id='dgTransactionList']//td//span[contains(text(),'" + DetailAmount + "')]";

		assertTrue(driver.findElement(By.xpath(detailCatPath)).getText().contains(DetailCategory),
				"Detail Category Not Matched");
		test_steps.add("Successfully Verified Detail Category : " + DetailCategory);
		groupLogger.info("Successfully Verified Detail Category : " + DetailCategory);
		assertTrue(driver.findElement(By.xpath(detailAmountPath)).getText().contains(DetailAmount),
				"Detail Amount Not Matched");
		test_steps.add("Successfully Verified Detail Amount : " + DetailAmount);
		groupLogger.info("Successfully Verified Detail Amount : " + DetailAmount);
		String continuee = "//input[@value='Continue']";

		try {

			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(continuee)), driver);
			Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(continuee)), driver);
			Utility.ScrollToElement(driver.findElement(By.xpath(continuee)), driver);
			driver.findElement(By.xpath(continuee)).click();
		} catch (Exception e) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", driver.findElement(By.xpath(continuee)));
		}

		test_steps.add("Clicking on continue");
		groupLogger.info("Clicking on continue");
		loadingImage(driver);
		driver.switchTo().defaultContent();
		loadingImage(driver);
		return test_steps;
	}

	public ArrayList<String> verifyPaymentMethod(WebDriver driver, String option, boolean isFound) {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group1 = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_visibilityof_webelement_120(group1.BillingInfo_PaymentMethod, driver);
		List<WebElement> payOptions = new Select(group1.BillingInfo_PaymentMethod).getOptions();
		boolean isMatched = false;
		for (int i = 0; i < payOptions.size(); i++) {

			String foundOption = payOptions.get(i).getText();
			groupLogger.info("Option " + (i + 1) + " : " + foundOption);
			test_steps.add("Option " + (i + 1) + " : " + foundOption);
			if (foundOption.equalsIgnoreCase(option)) {
				isMatched = true;
				break;
			}
		}
		if (isFound) {
			assertTrue(isMatched, "Failed Not Found");

			test_steps.add("Successfully Verified Option Found : " + option);
			groupLogger.info("Successfully Verified Option Found : " + option);
		} else {
			assertFalse(isMatched, "Failed " + option + " Matched");
			test_steps.add("Successfully Verified Option not Found : " + option);
			groupLogger.info("Successfully Verified Option not Found : " + option);
		}

		return test_steps;
	}

	public ArrayList<String> changeAriveDepartDate(WebDriver driver, boolean isAriveSet, int AriveDay,
			boolean isDepartSet, int departDay) throws ParseException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);

		if (isAriveSet) {
			Wait.explicit_wait_xpath(OR.Select_Arrival_Date_Groups, driver);
			Wait.explicit_wait_visibilityof_webelement_200(group.Select_Arrival_Date_Groups, driver);
			Wait.explicit_wait_elementToBeClickable(group.Select_Arrival_Date_Groups, driver);
			group.Select_Arrival_Date_Groups.click();
			test_steps.add("Select Arival Date");
			groupLogger.info("Select Arival Date");

			String nextDate = ESTTimeZone.getDateforAccountBalance(AriveDay);
			// String todayDate = ESTTimeZone.getDaetforAccountBalance(0);
			// test_steps.add("Server date : " + todayDate);
			test_steps.add("Searched Arival date : " + nextDate);

			String select_date = "//td[@title='" + nextDate + "']";
			System.out.println(select_date);
			WebElement element = driver.findElement(By.xpath(select_date));
			element.click();
		}

		if (isDepartSet) {
			Wait.explicit_wait_xpath(OR.Select_Depart_Date_Groups, driver);
			Wait.explicit_wait_visibilityof_webelement_200(group.Select_Depart_Date_Groups, driver);
			Wait.explicit_wait_elementToBeClickable(group.Select_Depart_Date_Groups, driver);
			group.Select_Depart_Date_Groups.click();
			test_steps.add("Select Depart Date");
			groupLogger.info("Select Depart Date");

			String nextDate = ESTTimeZone.getDateforAccountBalance(departDay);
			String todayDate = ESTTimeZone.getDateforAccountBalance(0);
			// test_steps.add("Server date : " + todayDate);
			test_steps.add("Searched Depart date : " + nextDate);

			String select_date = "//td[@title='" + nextDate + "']";
			System.out.println(select_date);
			WebElement element = driver.findElement(By.xpath(select_date));
			element.click();
		}
		return test_steps;
	}

	public ArrayList<String> errorMsg(WebDriver driver) {
		ArrayList<String> test_steps = new ArrayList<>();
		String msgpath = "//*[@id='MainContent_lblErrorSummary']";

		try {
			Wait.WaitForElement(driver, msgpath);
			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(msgpath)), driver);
			assertTrue(driver.findElement(By.xpath(msgpath)).isDisplayed(), "Failed no error Msg Displayed");

			test_steps.add("Successfully Verified Error Message Displayed");
			test_steps.add(driver.findElement(By.xpath(msgpath)).getText());
			groupLogger.info("Successfully Verified Error Message Displayed");
			groupLogger.info(driver.findElement(By.xpath(msgpath)).getText());

		} catch (Exception e) {
			assertTrue(false, "Failed no error Msg Displayed");
		}

		return test_steps;
	}

	public ArrayList<String> editDialogCancel(WebDriver driver) throws InterruptedException {
		// driver.switchTo().frame("MainContent_Iframe_accountpicker");
		Elements_OldGroups group = new Elements_OldGroups(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		// Wait.WaitForElement(driver, OR.BlockDetails_CancelBtn);
		Wait.wait3Second();
		Wait.explicit_wait_visibilityof_webelement_120(group.BlockDetails_CancelBtn, driver);
		Wait.explicit_wait_elementToBeClickable(group.BlockDetails_CancelBtn, driver);
		Utility.ScrollToElement(group.BlockDetails_CancelBtn, driver);
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();", group.Group_Save);
		//
		//
		group.BlockDetails_CancelBtn.click();

		test_steps.add("Click on Cancel");
		groupLogger.info("Click on Cancel");
		loadingImage(driver);
		// driver.switchTo().defaultContent();
		return test_steps;

	}

	public void DisplayVoidItemButton(WebDriver driver) throws InterruptedException {
		// driver.switchTo().frame("MainContent_Iframe_accountpicker");
		Elements_OldGroups group = new Elements_OldGroups(driver);
		group.DisplayVoidItemButton.click();
		Wait.wait2Second();
	}

	public ArrayList<String> editDialogName(WebDriver driver, String newName) throws InterruptedException {
		// driver.switchTo().frame("MainContent_Iframe_accountpicker");
		Elements_OldGroups group = new Elements_OldGroups(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		// Wait.WaitForElement(driver, OR.BlockDetails_CancelBtn);
		Wait.wait3Second();
		Wait.explicit_wait_visibilityof_webelement_120(group.BtnEditBlockName, driver);
		Wait.explicit_wait_elementToBeClickable(group.BtnEditBlockName, driver);
		Utility.ScrollToElement(group.BtnEditBlockName, driver);
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();", group.Group_Save);
		//
		//
		group.BtnEditBlockName.click();

		test_steps.add("Click on BtnEditBlockName");
		groupLogger.info("Click on BtnEditBlockName");
		loadingImage(driver);

		Wait.explicit_wait_visibilityof_webelement_600(group.TxtEditBlockName, driver);
		group.TxtEditBlockName.clear();
		group.TxtEditBlockName.sendKeys(newName);
		test_steps.add("Entered New Block Name : " + newName);
		groupLogger.info("Entered New Block Name : " + newName);

		Wait.explicit_wait_visibilityof_webelement_150(group.Click_Ok, driver);
		Utility.ScrollToElement(group.Click_Ok, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Ok, driver);
		group.Click_Ok.click();
		test_steps.add("OK Button Clicked");
		groupLogger.info("OK Button Clicked");
		loadingImage(driver);

		Wait.explicit_wait_visibilityof_webelement_600(group.LblEditBlockName, driver);
		assertEquals(group.LblEditBlockName.getText(), newName, "Failed new block name not matched");

		test_steps.add("Successfully Verified New Block Name : " + newName);
		groupLogger.info("Successfully Verified New Block Name : " + newName);

		return test_steps;

	}

	public ArrayList<String> verifyEditDialogSave(WebDriver driver, boolean isEnable) throws InterruptedException {
		// driver.switchTo().frame("MainContent_Iframe_accountpicker");
		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.Group_Save);
		Wait.wait3Second();
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Save, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Save, driver);
		Utility.ScrollToElement(group.Group_Save, driver);
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();", group.Group_Save);
		//
		//
		assertEquals(group.Group_Save.isEnabled(), isEnable, "Failed Group Save");

		test_steps.add("Successfully Verified Save Button : " + isEnable);
		groupLogger.info("Successfully Verified Save Button : " + isEnable);
		try {
			loadingImage(driver);
		} catch (Exception e) {
			System.out.println("No Loading");
		}
		// driver.switchTo().defaultContent();
		return test_steps;

	}

	public ArrayList<String> verifyEditDialogDone(WebDriver driver, boolean isEnable) throws InterruptedException {
		// driver.switchTo().frame("MainContent_Iframe_accountpicker");
		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.Group_Done);
		Wait.wait3Second();
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Done, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Done, driver);
		Utility.ScrollToElement(group.Group_Done, driver);
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();", group.Group_Save);
		//
		//
		assertEquals(group.Group_Done.isEnabled(), isEnable, "Failed Group Done");

		test_steps.add("Successfully Verified Done Button : " + isEnable);
		groupLogger.info("Successfully Verified Done Button : " + isEnable);
		try {
			loadingImage(driver);
		} catch (Exception e) {
			System.out.println("No Loading");
		}
		// driver.switchTo().defaultContent();
		return test_steps;

	}

	public ArrayList<String> MakeAuthorizePayment(WebDriver driver, String Authorizationtype, boolean isChangeAmount,
			String ChangeAmountValue, String CardName, String CardNum, String ExpDate, String CVVCode) {

		ArrayList<String> test_steps = new ArrayList<String>();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		Elements_Groups group = new Elements_Groups(driver);

		try {
			Wait.explicit_wait_visibilityof_webelement_120(group.Select_Group_paymethod, driver);
			Wait.explicit_wait_elementToBeClickable(group.Select_Group_paymethod, driver);
			Utility.ScrollToElement(group.Select_Group_paymethod, driver);
			new Select(group.Select_Group_paymethod).selectByVisibleText("Visa");
			new Select(group.Select_Group_paymethod).selectByVisibleText(CardName);
			Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_AuthType, driver);
			Wait.explicit_wait_elementToBeClickable(group.Group_Folio_AuthType, driver);
			Utility.ScrollToElement(group.Group_Folio_AuthType, driver);
			new Select(group.Group_Folio_AuthType).selectByVisibleText(Authorizationtype);
			test_steps.add("Select Authorization Type : " + Authorizationtype);
			Wait.wait3Second();

			Utility.app_logs.info(new Select(group.Group_Folio_AuthType).getFirstSelectedOption().getText());
			assertTrue(new Select(group.Group_Folio_AuthType).getFirstSelectedOption().getText()

					.contains("Authorization"), "Failed : Authorization is not selected");
			Wait.wait3Second();
			Utility.ScrollToElement(group.Click_Group_Card_info, driver);
			Wait.wait15Second();
			group.Click_Group_Card_info.click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body1")));
			Wait.explicit_wait_visibilityof_webelement(group.Enter_Group_Card_Name, driver);
			group.Enter_Group_Card_Name.sendKeys(CardName);
			group.Enter_CC_Group_Number.sendKeys(CardNum);
			group.Enter_ExpiryDate_Group.sendKeys(ExpDate);
			group.Enter_CCV_Group.sendKeys(CVVCode);

			test_steps.add("Enter Card Details : " + CardName + "/" + CardNum + "/" + ExpDate + "/" + CVVCode);
			// elements_All_Payments.Enter_ApprovalCode.sendKeys(AuthCode);
			group.Click_Ok_Group.click();
			// Wait.waitForElementToBeGone(driver, group.Click_Ok_Group, 60);

			/*
			 * try { group.Click_Ok_Group.click();
			 * Wait.waitForElementToBeGone(driver, group.Click_Ok_Group, 60);
			 * 
			 * } catch (Exception e) {
			 * Utility.app_logs.info("Again Click Card OK Button");
			 * group.Click_Ok_Group.click(); Wait.waitForElementToBeGone(driver,
			 * group.Click_Ok_Group, 60);
			 * test_steps.add("Click OK Button of Card info popup"); }
			 */
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
			if (isChangeAmount) {
				Wait.wait10Second();
				Wait.explicit_wait_visibilityof_webelement_120(group.Enter_Group_Amount, driver);
				Wait.explicit_wait_elementToBeClickable(group.Enter_Group_Amount, driver);
				Utility.ScrollToElement(group.Enter_Group_Amount, driver);
				JavascriptExecutor jse1 = (JavascriptExecutor) driver;
				jse1.executeScript("arguments[0].value='" + ChangeAmountValue + ".00';", group.Enter_Group_Amount);
				group.Enter_Group_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
				test_steps.add("Enter Amount : " + ChangeAmountValue);
			} else {
				Utility.app_logs.info("Processing the amount displayed");
			}
			Wait.wait15Second();
			Wait.explicit_wait_visibilityof_webelement_350(group.Click_Process_Group, driver);
			Utility.ScrollToElement(group.Click_Process_Group, driver);
			group.Click_Process_Group.click();
			Utility.app_logs.info("Clicked on Process Button");
			test_steps.add("Click Process Button");
			/*
			 * try { Wait.explicit_wait_visibilityof_webelement(group.
			 * AdvanceDepositConfirmPopup_Yes, driver);
			 * 
			 * } catch (Exception e) {
			 * Wait.explicit_wait_visibilityof_webelement_120(group.
			 * Group_Folio_Continue, driver);
			 * Wait.explicit_wait_elementToBeClickable(group.
			 * Group_Folio_Continue, driver); Wait.wait5Second();
			 * Utility.ScrollToElement(group.Group_Folio_Continue, driver);
			 * group.Group_Folio_Continue.click();
			 * Utility.app_logs.info("Again Clicked on Conitue Button");
			 * test_steps.add("Click Continue Button");
			 * //Wait.explicit_wait_visibilityof_webelement(group.
			 * AdvanceDepositConfirmPopup_Yes, driver); }
			 */
		} catch (Exception e) {

			Utility.app_logs.info("Exception occured while paying using MC \n");
			e.printStackTrace();
		}
		return test_steps;
	}

	public ArrayList<String> MakeCapturePayment(WebDriver driver, String Authorizationtype, boolean isChangeAmount,
			String ChangeAmountValue, String CardName, String CardNum, String ExpDate, String CVVCode) {

		ArrayList<String> test_steps = new ArrayList<String>();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		Elements_Groups group = new Elements_Groups(driver);

		try {
			Wait.explicit_wait_visibilityof_webelement_120(group.Select_Group_paymethod, driver);
			Wait.explicit_wait_elementToBeClickable(group.Select_Group_paymethod, driver);
			Utility.ScrollToElement(group.Select_Group_paymethod, driver);
			new Select(group.Select_Group_paymethod).selectByVisibleText("Visa");
			new Select(group.Select_Group_paymethod).selectByVisibleText(CardName);
			Wait.wait15Second();
			Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_AuthType, driver);
			Wait.explicit_wait_elementToBeClickable(group.Group_Folio_AuthType, driver);
			Utility.ScrollToElement(group.Group_Folio_AuthType, driver);
			Wait.wait15Second();

			new Select(group.Group_Folio_AuthType).selectByVisibleText(Authorizationtype);
			test_steps.add("Select Authorization Type : " + Authorizationtype);
			Wait.wait10Second();
			Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_AuthType, driver);
			Wait.explicit_wait_elementToBeClickable(group.Group_Folio_AuthType, driver);
			Utility.app_logs.info(new Select(group.Group_Folio_AuthType).getFirstSelectedOption().getText());
			assertTrue(new Select(group.Group_Folio_AuthType).getFirstSelectedOption().getText().contains("Capture"),
					"Failed : Capture is not selected");
			Wait.wait3Second();
			Utility.ScrollToElement(group.Click_Group_Card_info, driver);
			Wait.wait15Second();
			group.Click_Group_Card_info.click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body1")));
			Wait.explicit_wait_visibilityof_webelement(group.Enter_Group_Card_Name, driver);
			group.Enter_Group_Card_Name.sendKeys(CardName);
			group.Enter_CC_Group_Number.sendKeys(CardNum);
			group.Enter_ExpiryDate_Group.sendKeys(ExpDate);
			group.Enter_CCV_Group.sendKeys(CVVCode);

			test_steps.add("Enter Card Details : " + CardName + "/" + CardNum + "/" + ExpDate + "/" + CVVCode);
			// elements_All_Payments.Enter_ApprovalCode.sendKeys(AuthCode);
			group.Click_Ok_Group.click();

			// Wait.waitForElementToBeGone(driver, group.Click_Ok_Group, 60);

			/*
			 * try { group.Click_Ok_Group.click();
			 * Wait.waitForElementToBeGone(driver, group.Click_Ok_Group, 60);
			 * 
			 * } catch (Exception e) {
			 * Utility.app_logs.info("Again Click Card OK Button");
			 * group.Click_Ok_Group.click(); Wait.waitForElementToBeGone(driver,
			 * group.Click_Ok_Group, 60);
			 * test_steps.add("Click OK Button of Card info popup"); }
			 */
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
			if (isChangeAmount) {
				Wait.wait10Second();
				Wait.explicit_wait_visibilityof_webelement_120(group.Enter_Group_Amount, driver);
				Wait.explicit_wait_elementToBeClickable(group.Enter_Group_Amount, driver);
				Utility.ScrollToElement(group.Enter_Group_Amount, driver);
				JavascriptExecutor jse1 = (JavascriptExecutor) driver;
				jse1.executeScript("arguments[0].value='" + ChangeAmountValue + ".00';", group.Enter_Group_Amount);
				group.Enter_Group_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
				test_steps.add("Enter Amount : " + ChangeAmountValue);
			} else {
				Utility.app_logs.info("Processing the amount displayed");
			}
			Wait.explicit_wait_visibilityof_webelement_350(group.Click_Process_Group, driver);
			Utility.ScrollToElement(group.Click_Process_Group, driver);
			group.Click_Process_Group.click();
			Utility.app_logs.info("Clicked on Process Button");
			test_steps.add("Click Process Button");

			try {
				Wait.explicit_wait_visibilityof_webelement(group.AdvanceDepositConfirmPopup_Yes, driver);
				Wait.explicit_wait_visibilityof_webelement_120(group.AdvanceDepositConfirmPopup_Yes, driver);
				Wait.explicit_wait_elementToBeClickable(group.AdvanceDepositConfirmPopup_Yes, driver);
				Wait.wait5Second();
				Utility.ScrollToElement(group.AdvanceDepositConfirmPopup_Yes, driver);
				group.AdvanceDepositConfirmPopup_Yes.click();

			} catch (Exception e) {
				Wait.explicit_wait_visibilityof_webelement_120(group.Click_Process_Group, driver);
				Wait.explicit_wait_elementToBeClickable(group.Click_Process_Group, driver);
				Wait.wait5Second();
				Utility.ScrollToElement(group.Click_Process_Group, driver);
				group.Click_Process_Group.click();
				Utility.app_logs.info("Again Clicked on Process Button");
				test_steps.add("Click Process Button");
				Wait.explicit_wait_visibilityof_webelement(group.AdvanceDepositConfirmPopup_Yes, driver);
			}
		} catch (Exception e) {

			Utility.app_logs.info("Exception occured while paying using MC \n");
			e.printStackTrace();
		}
		return test_steps;
	}

	public ArrayList<String> verifyUpdatedUser(WebDriver driver, String user) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String userPath = "//*[@id='NSF_PymentDetails']/table/tbody/tr/td[1]";

		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(userPath)), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(userPath)), driver);
		String foundText = driver.findElement(By.xpath(userPath)).getText();
		assertTrue(foundText.contains(user),
				"Failed to verify Updated User: Found [" + foundText + "] expected [" + user + "]");
		test_steps.add("Successfully Verified Updated User : " + user);
		groupLogger.info("Successfully Verified Updated User : " + user);

		return test_steps;
	}

	public ArrayList<String> verifyRbtManualOrForced(WebDriver driver, String expected, boolean isDisplayed) {
		ArrayList<String> test_steps = new ArrayList<>();
		String userPath = "//*[@id='trAuthorizationtype']/td[2]/label[contains(text(),'" + expected + "')]";
		if (isDisplayed) {
			try {
				Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(userPath)), driver);
				Utility.ScrollToElement(driver.findElement(By.xpath(userPath)), driver);
				assertTrue(driver.findElement(By.xpath(userPath)).isDisplayed(),
						"Failed " + expected + " is Not Displaed");
				test_steps.add("Successfully Verified " + expected + "is Displayed");
				groupLogger.info("Successfully Verified " + expected + "is Displayed");
			} catch (Exception e) {
				assertTrue(false, "Failed Not Displayed");
			}
		} else {
			try {
				Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(userPath)), driver);
				Utility.ScrollToElement(driver.findElement(By.xpath(userPath)), driver);
				assertTrue(!driver.findElement(By.xpath(userPath)).isDisplayed(),
						"Failed " + expected + " is Displaed");

			} catch (Exception e) {
				assertTrue(true, "Failed is Displayed");
				test_steps.add("Successfully Verified " + expected + " is not Displayed");
				groupLogger.info("Successfully Verified " + expected + " is not Displayed");
			}
		}
		return test_steps;
	}

	public void payDetailContinue_Btn(WebDriver driver) throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		Wait.explicit_wait_visibilityof_webelement_350(group.Click_Continue_Group, driver);
		Wait.explicit_wait_elementToBeClickable(group.Click_Continue_Group, driver);
		Utility.ScrollToElement(group.Click_Continue_Group, driver);
		Wait.wait5Second();
		group.Click_Continue_Group.click();
		Utility.app_logs.info("Clicked on continue button");
		try {
			Wait.waitForElementToBeGone(driver, group.Click_Continue_Group, 90);
		} catch (Exception e) {

		}
		driver.switchTo().defaultContent();

	}

	public ArrayList<String> click_Transactions(WebDriver driver, ExtentTest test1, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		Wait.explicit_wait_visibilityof_webelement_350(group.Click_Transition_Group, driver);
		Utility.ScrollToElement(group.Click_Transition_Group, driver);
		Wait.wait15Second();
		group.Click_Transition_Group.click();
		Utility.app_logs.info("Clicked on Transition button");
		try {
			Wait.waitForElementToBeGone(driver, group.Click_Transition_Group, 90);
		} catch (Exception e) {

		}
		driver.switchTo().defaultContent();
		// driver.switchTo().frame(driver.findElement(By.id("dialog-body1")));
		return test_steps;

	}

	public ArrayList<String> Make_RefundPayment(WebDriver driver, String Property, String Authorizationtype,
			String voidMsg, ArrayList<String> test_steps) throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> steps = new ArrayList<>();

		// // Select Property in Property Details
		// steps = SelectProperty_PaymentDetail(driver, Property);
		// test_steps.addAll(steps);

		// try {
		loadingImage(driver);
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_AuthType, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_AuthType, driver);
		Utility.ScrollToElement(group.Group_Folio_AuthType, driver);
		Select dropdown = new Select(group.Group_Folio_AuthType);
		dropdown.selectByVisibleText(Authorizationtype);
		groupLogger.info("Select Authorization Type : " + Authorizationtype);
		test_steps.add("Select Authorization Type : " + Authorizationtype);
		Wait.explicit_wait_visibilityof_webelement(group.Group_Folio_AuthType, driver);
		Utility.app_logs.info(new Select(group.Group_Folio_AuthType).getFirstSelectedOption().getText());
		assertTrue(
				new Select(group.Group_Folio_AuthType).getFirstSelectedOption().getText().contains(Authorizationtype),
				"Failed : Authorization type is not selected");
		Wait.explicit_wait_visibilityof_webelement_350(group.Click_Outstanding_Group, driver);
		Utility.ScrollToElement(group.Click_Outstanding_Group, driver);
		group.Click_Outstanding_Group.click();
		Utility.app_logs.info("Clicked on Outstanding Tab button");
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_Process, driver);
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_Void_Notes, driver);
		Utility.ScrollToElement(group.Group_Folio_Void_Notes, driver);
		group.Group_Folio_Void_Notes.sendKeys(voidMsg);

		test_steps.add("Entered Void Notes : " + voidMsg);
		groupLogger.info("Entered Void Notes : " + voidMsg);
		Utility.ScrollToElement(group.Group_Folio_Process, driver);
		group.Group_Folio_Process.click();
		test_steps.add("Clicking on Process");
		groupLogger.info("Clicking on Process");
		return test_steps;

	}

	public ArrayList<String> selectCheckInPolicy(WebDriver driver, String policyName) {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups groups = new Elements_Groups(driver);

		Wait.explicit_wait_visibilityof_webelement_120(groups.FolioOptionCheckInPolicy, driver);
		new Select(groups.FolioOptionCheckInPolicy).selectByVisibleText(policyName);
		test_steps.add("Selected Check In Policy : " + policyName);
		groupLogger.info("Selected Check In Policy : " + policyName);
		return test_steps;
	}

	public ArrayList<String> verifyCheckInPolicy(WebDriver driver, String policyName) {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups groups = new Elements_Groups(driver);

		Wait.explicit_wait_visibilityof_webelement_120(groups.FolioOptionCheckInPolicy, driver);
		String foundOption = new Select(groups.FolioOptionCheckInPolicy).getFirstSelectedOption().getText();
		assertEquals(foundOption, policyName, "Failed to verify seleted checkin policy");
		test_steps.add("Successfully Verified Check In Policy : " + policyName);
		groupLogger.info("Successfully Verified Check In Policy : " + policyName);
		return test_steps;
	}

	public ArrayList<String> selectCancellationPolicy(WebDriver driver, String policyName) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups groups = new Elements_Groups(driver);
		Wait.explicit_wait_visibilityof_webelement_120(groups.FolioOptionCancellationPolicy, driver);
		Wait.explicit_wait_elementToBeClickable(groups.FolioOptionCancellationPolicy, driver);
		Utility.ScrollToElement(groups.FolioOptionCancellationPolicy, driver);
		groups.FolioOptionCancellationPolicy.click();
		Wait.explicit_wait_visibilityof_webelement_120(groups.FolioOptionCancellationPolicyListSource, driver);
		new Select(groups.FolioOptionCancellationPolicyListSource).selectByVisibleText(policyName);
		Wait.explicit_wait_visibilityof_webelement_120(groups.FolioOptionCancellationPolicyPickBtn, driver);
		Wait.explicit_wait_elementToBeClickable(groups.FolioOptionCancellationPolicyPickBtn, driver);
		Utility.ScrollToElement(groups.FolioOptionCancellationPolicyPickBtn, driver);
		groups.FolioOptionCancellationPolicyPickBtn.click();
		Wait.explicit_wait_visibilityof_webelement_120(groups.FolioOptionCancellationPolicyDoneBtn, driver);
		Wait.explicit_wait_elementToBeClickable(groups.FolioOptionCancellationPolicyDoneBtn, driver);
		Utility.ScrollToElement(groups.FolioOptionCancellationPolicyDoneBtn, driver);
		groups.FolioOptionCancellationPolicyDoneBtn.click();
		test_steps.add("Selected Cancellation Policy : " + policyName);
		groupLogger.info("Selected Cancellation Policy : " + policyName);
		return test_steps;
	}

	public ArrayList<String> verifyCancellationPolicy(WebDriver driver, String policyName) {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups groups = new Elements_Groups(driver);

		Wait.explicit_wait_visibilityof_webelement_120(groups.FolioOptionCancellationPolicy, driver);
		String foundOption = groups.FolioOptionCancellationPolicy.getAttribute("value");
		assertEquals(foundOption, policyName, "Failed to verify seleted Cancellation policy");
		test_steps.add("Successfully Verified Cancellation Policy : " + policyName);
		groupLogger.info("Successfully Verified Cancellation Policy : " + policyName);
		return test_steps;
	}

	public ArrayList<String> groupPay_Advance(WebDriver driver, ExtentTest test1, String AccountType,
			String PaymentType, String CardName, String CCNumber, String CCExpiry, String CCVCode,
			String Authorizationtype, String ChangeAmount, String ChangeAmountValue, String voidMsg,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Groups groups = new Elements_Groups(driver);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		Wait.wait5Second();
		Wait.explicit_wait_visibilityof_webelement_120(groups.Enter_Group_Amount, driver);
		Utility.ScrollToElement(groups.Enter_Group_Amount, driver);
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		jse1.executeScript("arguments[0].value='" + ChangeAmountValue + ".00';", groups.Enter_Group_Amount);
		groups.Enter_Group_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
		test_steps.add("Enter Amount to pay " + ChangeAmountValue);
		groupLogger.info("Enter Amount to pay " + ChangeAmountValue);

		Wait.explicit_wait_visibilityof_webelement_120(groups.Select_Group_paymethod, driver);
		Utility.ScrollToElement(groups.Select_Group_paymethod, driver);
		new Select(groups.Select_Group_paymethod).selectByVisibleText(PaymentType);
		test_steps.add("Select Payment type " + PaymentType);
		groupLogger.info("Select Payment type " + PaymentType);
		Wait.wait10Second();
		Wait.explicit_wait_elementToBeClickable(groups.Group_Folio_AuthType, driver);
		new Select(groups.Group_Folio_AuthType).selectByVisibleText(Authorizationtype);
		String AuthType = new Select(groups.Group_Folio_AuthType).getFirstSelectedOption().getText();
		test_steps.add("Verified AuthorizationType is " + AuthType);
		assertEquals(AuthType, Authorizationtype, "Failed Authorization Type is not " + Authorizationtype);

		Wait.explicit_wait_visibilityof_webelement_120(groups.Group_Folio_Void_Notes, driver);
		Utility.ScrollToElement(groups.Group_Folio_Void_Notes, driver);
		groups.Group_Folio_Void_Notes.sendKeys(voidMsg);

		test_steps.add("Entered Void Notes : " + voidMsg);
		groupLogger.info("Entered Void Notes : " + voidMsg);
		if (PaymentType.contains("Cash")) {
			Wait.WaitForElement(driver, OR.Click_AddPayment_Account);
			groups.Group_Folio_Add.click();
			groupLogger.info("Click on ADD");
			test_steps.add("Click on ADD");
		} else {
			Wait.WaitForElement(driver, OR.Click_Process_Group);
			Wait.explicit_wait_visibilityof_webelement_120(groups.Click_Process_Group, driver);
			Wait.explicit_wait_elementToBeClickable(groups.Click_Process_Group, driver);
			Utility.ScrollToElement(groups.Click_Process_Group, driver);
			groups.Click_Process_Group.click();
			groupLogger.info("Click on Process");
			test_steps.add("Click on Process");
		}
		Wait.wait3Second();
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(groups.Group_Folio_Continue));
		Wait.explicit_wait_visibilityof_webelement(groups.Group_Folio_Continue, driver);
		Utility.ScrollToElement(groups.Group_Folio_Continue, driver);
		Wait.WaitForElement(driver, OR.Group_Folio_Continue);
		groups.Group_Folio_Continue.click();
		test_steps.add("Click on Account_Pay_Continue Button");
		groupLogger.info("Click on Account_Pay_Continue Button");

		return test_steps;
	}

	public ArrayList<String> checkTaxExmpt(WebDriver driver, boolean isTaxExmpt, String TaxExmptId)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Groups groups = new Elements_Groups(driver);

		if (isTaxExmpt) {
			if (!groups.Check_TaxExempt.isSelected()) {
				Wait.explicit_wait_visibilityof_webelement_120(groups.Check_TaxExempt, driver);
				Wait.explicit_wait_elementToBeClickable(groups.Check_TaxExempt, driver);
				Utility.ScrollToElement(groups.Check_TaxExempt, driver);
				groups.Check_TaxExempt.click();
				test_steps.add("Tax Exempt Successfully Checked");
				groupLogger.info("Tax Exempt Successfully Checked");
				Wait.loadingImage(driver);
				test_steps.addAll(alreadHasFolioItemsCheck(driver));
				Wait.loadingImage(driver);
				driver.switchTo().defaultContent();
				Wait.explicit_wait_visibilityof_webelement_120(groups.Txt_TaxExempt, driver);
				groups.Txt_TaxExempt.clear();
				groups.Txt_TaxExempt.sendKeys(TaxExmptId);
				test_steps.add("Entered Tax Exempt Id : " + TaxExmptId);
				groupLogger.info("Entered Tax Exempt Id : " + TaxExmptId);
			}
		} else {
			if (groups.Check_TaxExempt.isSelected()) {
				Wait.explicit_wait_visibilityof_webelement_120(groups.Check_TaxExempt, driver);
				Wait.explicit_wait_elementToBeClickable(groups.Check_TaxExempt, driver);
				Utility.ScrollToElement(groups.Check_TaxExempt, driver);
				groups.Check_TaxExempt.click();
				test_steps.add("Tax Exempt Successfully UnChecked");
				groupLogger.info("Tax Exempt Successfully UnChecked");
				Wait.loadingImage(driver);
			}
		}

		return test_steps;
	}

	public ArrayList<String> selectDepositPolicy(WebDriver driver, String policyName) {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups groups = new Elements_Groups(driver);
		Wait.explicit_wait_visibilityof_webelement_120(groups.FolioOptionDepositPolicy, driver);
		new Select(groups.FolioOptionDepositPolicy).selectByVisibleText(policyName);
		test_steps.add("Selected Deposit Policy : " + policyName);
		groupLogger.info("Selected Deposit Policy : " + policyName);
		return test_steps;
	}

	public ArrayList<String> verifyDepositPolicy(WebDriver driver, String policyName) {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups groups = new Elements_Groups(driver);

		Wait.explicit_wait_visibilityof_webelement_120(groups.FolioOptionDepositPolicy, driver);
		String foundOption = new Select(groups.FolioOptionDepositPolicy).getFirstSelectedOption().getText();
		assertEquals(foundOption, policyName, "Failed to verify seleted Deposit policy");
		test_steps.add("Successfully Verified Deposit Policy : " + policyName);
		groupLogger.info("Successfully Verified Deposit Policy : " + policyName);
		return test_steps;
	}

	public ArrayList<String> enterCustomRate(WebDriver driver, String RoomClass, String customRate)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();

		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		List<WebElement> GetRoomclassNames = AdvGroup.GetRoomclasses;
		// groupLogger.info("GetRoomclassNames" + GetRoomclassNames.size());
		for (int i = 0; i < GetRoomclassNames.size(); i++) {
			if (GetRoomclassNames.get(i).getText().equals(RoomClass)) {
				int index = i + 1;
				driver.findElement(By.xpath(
						"//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index + "]/td[4]/input"))
						.sendKeys(Keys.chord(Keys.CONTROL, "a"), customRate);
				Wait.wait1Second();
				groupLogger.info(customRate + " : Custom Rate for : " + RoomClass);
				test_steps.add(customRate + " : Custom Rate for : " + RoomClass);

				break;
			}
		}
		return test_steps;
	}

	public ArrayList<String> verifyCurrentBalance(WebDriver driver, String balance) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();

		String foundValue = driver.findElement(By.id("lblCurrentBalance")).getText();
		assertEquals(Float.parseFloat(foundValue), Float.parseFloat(balance), "Failed to Verify Balance not negetive");
		test_steps.add("Successfully Verified Balance is Negetive : " + balance);
		groupLogger.info("Successfully Verified Balance is Negetive : " + balance);
		return test_steps;
	}

	public ArrayList<String> verifyAuthorizationType(WebDriver driver, String Authorizationtype)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		// Click Add Line Item

		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_AuthType, driver);

		String foundType = new Select(group.Group_Folio_AuthType).getFirstSelectedOption().getText();
		assertEquals(foundType, Authorizationtype, "Failed to verify Authorization Type");
		test_steps.add("Successfully Verified Authorization Type : " + Authorizationtype);
		groupLogger.info("Successfully Verified Authorization Type : " + Authorizationtype);
		return test_steps;
	}

	public ArrayList<String> setPaymentMethodInPaymentDetailPopup(WebDriver driver, String PaymentMethod)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		// Wait.explicit_wait_xpath("//*[@id='dialog-body0']", driver);
		// driver.switchTo().frame("dialog-body0");
		Wait.WaitForElement(driver, OR.Group_Folio_PaymentMethod);
		Utility.ScrollToElement(group.Group_Folio_PaymentMethod, driver);
		assertEquals(group.Group_Folio_PaymentMethod.getText().contains(PaymentMethod), true,
				"Failed: Billing Type Dropdown doesn't contain " + PaymentMethod);
		Utility.app_logs.info("Billing Type Dropdown contains " + PaymentMethod);
		// Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_PaymentMethod,
		// driver);
		new Select(group.Group_Folio_PaymentMethod).selectByVisibleText(PaymentMethod);

		test_steps.add("Payment Method : " + PaymentMethod);
		groupLogger.info("Payment Method : " + PaymentMethod);

		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> addPaymentDetailClick(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups group = new Elements_Groups(driver);
		Wait.explicit_wait_visibilityof_webelement_120(group.Group_Folio_Add, driver);
		Wait.explicit_wait_elementToBeClickable(group.Group_Folio_Add, driver);
		Utility.ScrollToElement(group.Group_Folio_Add, driver);
		group.Group_Folio_Add.click();
		groupLogger.info("Click on ADD");
		test_steps.add("Click on ADD");
		return test_steps;
	}

	public ArrayList<String> enterAdvanceDepositAmount(WebDriver driver, String amount) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups adGrp = new Elements_AdvanceGroups(driver);

		Wait.explicit_wait_visibilityof_webelement_120(adGrp.AmountItemDetail, driver);
		adGrp.AmountItemDetail.clear();
		adGrp.AmountItemDetail.sendKeys(amount);
		test_steps.add("Payment Amount : " + amount);
		groupLogger.info("Payment Amount : " + amount);

		return test_steps;
	}

	public ArrayList<String> enterOutStandingItemsAmount(WebDriver driver, String item, String Amount)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String txtAmount = "//*[@id='dgPaymentDetails']//a[contains(text(),'" + item
				+ "')]/parent::td/following-sibling::td[6]//input[contains(@name,'PayAmount')]";
		// driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		Wait.waitUntilPresenceOfElementLocated(txtAmount, driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(txtAmount)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(txtAmount)), driver);
		driver.findElement(By.xpath(txtAmount)).clear();
		driver.findElement(By.xpath(txtAmount)).sendKeys(Amount);
		test_steps.add("Payment Amount : " + Amount);
		groupLogger.info("Payment Amount : " + Amount);

		// driver.switchTo().defaultContent();
		return test_steps;
	}

	public ArrayList<String> verifyArivalDate(WebDriver driver, String arivalDate) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String path = "//*[@id='MainContent_dgAccountList']/tbody/tr[2]/td[4]";
		Wait.waitUntilPresenceOfElementLocated(path, driver);
		String foundTxt = driver.findElement(By.xpath(path)).getText();
		assertEquals(Utility.parseDate(foundTxt, "MMM dd, yyyy", "MMM dd, yyyy"),
				Utility.parseDate(arivalDate, "MMM dd, yyyy", "MMM dd, yyyy"), "Failed To verify Arival Date");
		test_steps.add("Successfully Verified Arival Date : " + arivalDate);
		groupLogger.info("Successfully Verified Arival Date : " + arivalDate);
		return test_steps;
	}

	public ArrayList<String> verifyDepartDate(WebDriver driver, String departDate) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String path = "//*[@id='MainContent_dgAccountList']/tbody/tr[2]/td[5]";
		Wait.waitUntilPresenceOfElementLocated(path, driver);
		String foundTxt = driver.findElement(By.xpath(path)).getText();
		assertEquals(Utility.parseDate(foundTxt, "MMM dd, yyyy", "MMM dd, yyyy"),
				Utility.parseDate(departDate, "MMM dd, yyyy", "MMM dd, yyyy"), "Failed To verify Depart Date");
		test_steps.add("Successfully Verified Depart Date : " + departDate);
		groupLogger.info("Successfully Verified Depart Date : " + departDate);
		return test_steps;
	}

	public ArrayList<String> verifyRoomNights(WebDriver driver, String roomNights) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String path = "//*[@id='MainContent_dgAccountList']/tbody/tr[2]/td[6]";
		Wait.waitUntilPresenceOfElementLocated(path, driver);
		String foundTxt = driver.findElement(By.xpath(path)).getText();
		assertEquals(foundTxt, roomNights, "Failed To verify RoomNights");
		test_steps.add("Successfully Verified Room Nights : " + roomNights);
		groupLogger.info("Successfully Verified Room Nights : " + roomNights);
		return test_steps;
	}

	public ArrayList<String> verifyGroupCount(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String path = "//*[@id='MainContent_dgAccountList']/tbody/tr";
		Wait.waitUntilPresenceOfElementLocated(path, driver);
		List<WebElement> listEle = driver.findElements(By.xpath(path));
		int count = listEle.size() - 2; // header and footer(pagenation)
		assertEquals(count, 1, "Failed To verify Searched Group");
		test_steps.add("Successfully Verified Searched Group count : 1");
		groupLogger.info("Successfully Verified Searched Group count : 1");
		return test_steps;
	}

	public ArrayList<String> alreadHasFolioItemsCheck(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String lbMsg = "//*[@id='lblMessage']";
		String ok = "//*[@id='btnSave']";
		try {
			driver.switchTo().frame("dialog-body0");
			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(lbMsg)), driver);
			String msg = driver.findElement(By.xpath(lbMsg)).getText();
			test_steps.add(msg);
			groupLogger.info(msg);
			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(ok)), driver);
			Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(ok)), driver);
			Utility.ScrollToElement(driver.findElement(By.xpath(ok)), driver);
			driver.findElement(By.xpath(ok)).click();
			Wait.loadingImage(driver);
			msg = "OK button clicked";
			test_steps.add(msg);
			groupLogger.info(msg);
			Wait.wait5Second();
		} catch (Exception e) {
			groupLogger.info("No Message Found");
		} finally {
			driver.switchTo().defaultContent();
		}
		return test_steps;
	}

	public ArrayList<String> errorMsg(WebDriver driver, boolean isDisplay) {
		ArrayList<String> test_steps = new ArrayList<>();
		String msgpath = "//*[@id='MainContent_lblErrorSummary']";

		try {
			Wait.WaitForElement(driver, msgpath);
			Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(msgpath)), driver);
			String msg = driver.findElement(By.xpath(msgpath)).getText();
			// test_steps.add("Successfully Verified Error Message Displayed");
			test_steps.add(driver.findElement(By.xpath(msgpath)).getText());
			// groupLogger.info("Successfully Verified Error Message
			// Displayed");
			groupLogger.info(driver.findElement(By.xpath(msgpath)).getText());

			assertEquals(driver.findElement(By.xpath(msgpath)).isDisplayed(), isDisplay, "Failed : " + msg + " : ");

		} catch (Exception e) {
			assertEquals(false, isDisplay, "Failed error Msg Displayed : ");
			test_steps.add("Successfully Verified Error Message Displayed : " + isDisplay);
			groupLogger.info("Successfully Verified Error Message Displayed : " + isDisplay);
		}

		return test_steps;
	}

	public String getLineItemTax(WebDriver driver, int LineNO) {
		String tax = "0";
		try {
			tax = driver.findElement(By.id("MainContent_Folio1_dgLineItems_lblTax_" + LineNO)).getText();
		} catch (Exception e) {
			groupLogger.info("No tax");
		}

		return tax;
	}

	public boolean isIncludeTaxInLineAmount(WebDriver driver) {
		String id = "MainContent_Folio1_chkResIncludeTax";
		WebElement ele = driver.findElement(By.id(id));
		return ele.isSelected();
	}

	public ArrayList<String> enterBillingType(WebDriver driver, String PaymentType) throws InterruptedException {

		Elements_Groups groups = new Elements_Groups(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		Wait.WaitForElement(driver, OR.oldGroup_PaymentMethod);
		Utility.ScrollToElement(oldGroup.oldGroup_PaymentMethod, driver);
		assertEquals(oldGroup.oldGroup_PaymentMethod.getText().contains(PaymentType), true,
				"Failed: Billing Type Dropdown doesn't contain " + PaymentType);
		Utility.app_logs.info("Billing Type Dropdown contains " + PaymentType);
		test_steps.add("Billing Type Dropdown contains " + PaymentType);
		new Select(oldGroup.oldGroup_PaymentMethod).selectByVisibleText(PaymentType);
		Utility.app_logs.info("Select billing type: " + PaymentType);
		test_steps.add("Select billing type: " + PaymentType);
		return test_steps;
	}

	public ArrayList<String> SearchGiftCertificate(WebDriver driver, String GiftCertificateNum)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);

		Wait.WaitForElement(driver, OR.Grp_GiftCertificatPicker);
		Wait.explicit_wait_elementToBeClickable(oldGroup.Grp_GiftCertificatPicker, driver);
		Utility.ScrollToElement(oldGroup.Grp_GiftCertificatPicker, driver);
		assertEquals(oldGroup.Grp_GiftCertificatPicker.getText(), "Gift Certificate Picker",
				"Failed: Gift Certificate Picker  didn't showup");
		Utility.app_logs.info("Gift Certificate Picker Page Displayed");
		test_steps.add("Gift Certificate Picker Page Displayed");
		Utility.ScrollToElement(oldGroup.Grp_GCPickerSearchField, driver);
		oldGroup.Grp_GCPickerSearchField.clear();
		oldGroup.Grp_GCPickerSearchField.sendKeys(GiftCertificateNum);
		test_steps.add("Gift Certificate Value " + GiftCertificateNum + " Entered");
		Utility.app_logs.info("Gift Certificate Value " + GiftCertificateNum + " Entered");
		oldGroup.Grp_GCPickerGoButton.click();
		Utility.app_logs.info("GO Button is Clicked");
		test_steps.add("GO Button is Clicked");
		return test_steps;

	}

	public ArrayList<String> VerifySearchedGiftCertificate(WebDriver driver, String GiftCertificateNum, String Amount,
			String Balance, String ExpireDate) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();

		String GiftCertificateNumPath = "//td[text()='" + GiftCertificateNum + "']";
		String GiftCertificateAmtPath = "//td[text()='" + GiftCertificateNum + "']//following-sibling::td[text()='"
				+ Amount + ".00']";
		String GiftCertificateBalancePath = "//td[text()='" + GiftCertificateNum
				+ "']//following-sibling::td//span[@id='dgGCsList_lblBalance_0']";
		String GiftCertificateExpireDatePath = "//td[text()='" + GiftCertificateNum
				+ "']//following-sibling::td[text()='" + ExpireDate + "']";

		WebElement GiftCertificateNumEle = driver.findElement(By.xpath(GiftCertificateNumPath));
		WebElement GiftCertificateAmtEle = driver.findElement(By.xpath(GiftCertificateAmtPath));
		WebElement GiftCertificateBalanceEle = driver.findElement(By.xpath(GiftCertificateBalancePath));
		WebElement GiftCertificateExpireDateEle = driver.findElement(By.xpath(GiftCertificateExpireDatePath));
		Wait.WaitForElement(driver, GiftCertificateNumPath);
		Utility.ScrollToElement(GiftCertificateNumEle, driver);
		assertEquals(GiftCertificateNumEle.getText().contains(GiftCertificateNum), true,
				"Failed: Gift Certificate Number didn't match");
		Utility.app_logs.info("Gift Certificate Number: " + GiftCertificateNum + " Verified");
		test_steps.add("Gift Certificate Number: " + GiftCertificateNum + " Verified");
		assertEquals(GiftCertificateAmtEle.getText().contains(Amount), true,
				"Failed: Gift Certificate Amount didn't match");
		Utility.app_logs.info("Gift Certificate Amount: " + Amount + " Verified");
		test_steps.add("Gift Certificate Amount: " + Amount + " Verified");
		assertEquals(GiftCertificateBalanceEle.getText().contains(Balance), true,
				"Failed: Gift Certificate Balance didn't match");
		Utility.app_logs.info("Gift Certificate Balance: " + Balance + " Verified");
		test_steps.add("Gift Certificate Balance: " + Balance + " Verified");
		System.out.print(GiftCertificateExpireDateEle.getText() + "?" + ExpireDate);

		assertEquals(GiftCertificateExpireDateEle.getText().contains(ExpireDate), true,
				"Failed: Gift Certificate Expiry Date didn't match");
		Utility.app_logs.info("Gift Certificate Expiry Date: " + ExpireDate + " Verified");
		test_steps.add("Gift Certificate Expiry Date: " + ExpireDate + " Verified");

		return test_steps;

	}

	public ArrayList<String> VerifyGroup_PaymentMethodInfoDetails(WebDriver driver, String PaymentMethod,
			String PaymentInfo, String Amount) throws InterruptedException {
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.explicit_wait_visibilityof_webelement(oldGroup.GroupPayment_Info_Textarea, driver);
		Wait.WaitForElement(driver, OR.GroupPayment_Info_Textarea);
		Utility.ScrollToElement(oldGroup.GroupPayment_Info_Textarea, driver);
		String Type = new Select(group.Group_Folio_PaymentMethod).getFirstSelectedOption().getText();
		assertEquals(group.Group_Folio_Amount.getAttribute("value").contains(Amount), true,
				"Failed: Gift Certificate Amount didn't match");
		Utility.app_logs.info("Gift Certificate Amount: " + Amount + " Verified");
		test_steps.add("Gift Certificate Amount: " + Amount + " Verified");
		// assertEquals(Folio.Account_PaymentInfoTextArea.getText().contains(PaymentInfo),
		// true,
		// "Failed: Gift Certificate PaymentInfo didn't match");
		// folioLogger.info("Gift Certificate PaymentInfo: " + PaymentInfo +
		// "Verified");
		// test_steps.add("Gift Certificate PaymentInfo: " + PaymentInfo +
		// "Verified");

		assertEquals(Type.contains(PaymentMethod), true, "Failed: Gift Certificate PaymentMethod didn't match");
		Utility.app_logs.info("Gift Certificate PaymentMethod: " + PaymentMethod + " Verified");
		test_steps.add("Gift Certificate PaymentMethod: " + PaymentMethod + " Verified");
		return test_steps;

	}

	public ArrayList<String> enterPaymentDetailsAmount(WebDriver driver, String Amount) throws InterruptedException {
		ArrayList<String> test_step = new ArrayList<>();
		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		Wait.WaitForElement(driver, OR.PaymentDetail_Enter_Amount);
		elements_All_Payments.PaymentDetail_Enter_Amount.clear();
		if (Float.parseFloat(Amount) < 0.0) {
			assertTrue(false, "Failed: Amount is less then 0.0");
		}
		String str_length = elements_All_Payments.PaymentDetail_Enter_Amount.getAttribute("value");
		for (int i = 0; i < str_length.length(); i++) {
			elements_All_Payments.PaymentDetail_Enter_Amount.sendKeys(Keys.BACK_SPACE);
		}
		elements_All_Payments.PaymentDetail_Enter_Amount.sendKeys(Amount);
		Utility.app_logs.info("Enter Amount : " + Amount);
		test_step.add("Enter Amount : " + Amount);

		return test_step;
	}

	public void ClickPaymentDetailAddButton(WebDriver driver) throws InterruptedException {
		Elements_All_Payments pay = new Elements_All_Payments(driver);

		Wait.WaitForElement(driver, OR.Add_Pay_Button);
		Wait.explicit_wait_elementToBeClickable(pay.Add_Pay_Button, driver);
		Utility.ScrollToElement(pay.Add_Pay_Button, driver);
		pay.Add_Pay_Button.click();
		Utility.app_logs.info("Add Button is Clicked");
		// Wait.wait3Second();
		// Wait.WaitForElement(driver, OR.Apply_Advance_Deposite);
		//
		// Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.Apply_Advance_Deposite)),
		// driver);
		// Account.Click_Continue_Adv_Deposite.click();
		// Utility.app_logs.info("Click Advance Deposit link");

	}

	public ArrayList<String> GroupFoliobillingInformation(WebDriver driver, String FirstName, String LastName,
			String PaymentMethod, boolean EnterName, boolean SaveFolio) throws InterruptedException {
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> test_steps = new ArrayList<String>();

		if (EnterName) {
			oldGroup.Grp_FolioBillingInfoFirstName.sendKeys(FirstName);
			Utility.app_logs.info("First Name : " + FirstName);
			test_steps.add("Fist Name Entered: " + FirstName + " in Folio Billing Information");

			oldGroup.Grp_FolioBillingInfoLastName.sendKeys(LastName);
			Utility.app_logs.info("Last Name : " + LastName);
			test_steps.add("Last Name Entered: " + LastName + " in Folio Billing Information");

		}
		new Select(oldGroup.Grp_FolioBillingInfoPaymentMethod).selectByVisibleText(PaymentMethod);
		Wait.wait5Second();
		Utility.app_logs.info("Payment Method " + PaymentMethod + " Selected");
		test_steps.add("Payment Method " + PaymentMethod + " Selected");

		if (SaveFolio) {
			String GetPaymentMethod = oldGroup.Grp_FolioBillingInfoPaymentMethod.getAttribute("value");

			assertTrue(GetPaymentMethod.contains(PaymentMethod), "particular reservation details isn't populated");
			Utility.app_logs.info("Payment Method " + PaymentMethod + " Verified");
			test_steps.add("Payment Method " + PaymentMethod + " Verified");
			oldGroup.Grp_FolioBillingInfoSaveButton.click();
			Utility.app_logs.info("Save Folio Billing Info Button Clicked");
			test_steps.add("Save Folio Billing Info Button Clicked");
		}
		return test_steps;
	}

	public ArrayList<String> GroupFoliobillingInformationCancelButtonClick(WebDriver driver)
			throws InterruptedException {
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		ArrayList<String> test_steps = new ArrayList<String>();
		Wait.WaitForElement(driver, OR.Grp_FolioBillingInfoCancelButton);
		Wait.explicit_wait_elementToBeClickable(oldGroup.Grp_FolioBillingInfoCancelButton, driver);
		Utility.ScrollToElement(oldGroup.Grp_FolioBillingInfoCancelButton, driver);
		oldGroup.Grp_FolioBillingInfoCancelButton.click();
		Utility.app_logs.info("Cancel Folio Billing Info Button Clicked");
		test_steps.add("Cancel Folio Billing Info Button Clicked");
		return test_steps;
	}

	public ArrayList<String> SelectReferral(WebDriver driver, String Referral) {

		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> test_steps = new ArrayList<>();

		Wait.WaitForElement(driver, OR.Referrls);
		Wait.explicit_wait_visibilityof_webelement(group.Referrls, driver);
		Wait.explicit_wait_elementToBeClickable(group.Referrls, driver);

		Select select = new Select(group.Referrls);
		select.selectByVisibleText(Referral);
		test_steps.add("Select Referral : " + Referral);
		groupLogger.info("Select Referral : " + Referral);
		WebElement option = select.getFirstSelectedOption();
		assertEquals(option.getText(), Referral, "Failed: Referral is mismatching in new group");
		return test_steps;
	}

	public void clickNewAcccount(WebDriver driver) {

		Elements_Groups elements_Groups = new Elements_Groups(driver);
		Wait.WaitForElement(driver, OR.btnNewGroup);
		Wait.explicit_wait_visibilityof_webelement(elements_Groups.btnNewGroup, driver);
		Wait.explicit_wait_elementToBeClickable(elements_Groups.btnNewGroup, driver);
		elements_Groups.btnNewGroup.click();

	}

	public ArrayList<String> done(WebDriver driver) throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.Group_Done);
		Wait.wait3Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", group.Group_Done);

		// group.Group_Save.click();

		test_steps.add("Click on Done");
		return test_steps;

	}

	public ArrayList<String> verifyReservationLine(WebDriver driver, String resNo, String guestName, String blockName,
			String adultCount, String childCount, String resStatus, String roomClassRoomNo, String arivalDate,
			String departDate, String totalNights) {
		ArrayList<String> test_steps = new ArrayList<>();

		String guestNamePath = "//td[contains(@class,'guestName')]/a[text()='" + guestName + "']";
		String resNoPath = guestNamePath + "/../preceding-sibling::td[1][text()='" + resNo + "']";
		String blockNamePath = resNoPath + "/../td[4]";
		String adultCountPath = resNoPath + "/../td[5]";
		String childCountPath = resNoPath + "/../td[6]";
		String resStatusPath = resNoPath + "/../td[7]";
		String roomClassRoomNoPath = resNoPath + "/../td[8]";
		String arivalDatePath = resNoPath + "/../td[9]/span";
		String departDatePath = resNoPath + "/../td[10]/span";
		String totalNightsPath = resNoPath + "/../td[11]";

		String foundResNo = driver.findElement(By.xpath(resNoPath)).getText();
		assertEquals(foundResNo, resNo, "Failed To Verify Reservation No");
		groupLogger.info("Successfully Verified Reservation No : " + foundResNo);
		test_steps.add("Successfully Verified Reservation No : " + foundResNo);

		String foundGuestName = driver.findElement(By.xpath(guestNamePath)).getText();
		assertEquals(foundGuestName, guestName, "Failed To Verify Guest Name");
		groupLogger.info("Successfully Verified Guest Name : " + foundGuestName);
		test_steps.add("Successfully Verified Guest Name : " + foundGuestName);

		String foundBlockName = driver.findElement(By.xpath(blockNamePath)).getText();
		assertEquals(foundBlockName, blockName, "Failed To Verify Block Name");
		groupLogger.info("Successfully Verified Block Name : " + foundBlockName);
		test_steps.add("Successfully Verified Block Name : " + foundBlockName);

		String foundAdultCount = driver.findElement(By.xpath(adultCountPath)).getText();
		assertEquals(foundAdultCount, adultCount, "Failed To Verify Adult Count");
		groupLogger.info("Successfully Verified Adult Count : " + foundAdultCount);
		test_steps.add("Successfully Verified Adult Count : " + foundAdultCount);

		String foundChildCount = driver.findElement(By.xpath(childCountPath)).getText();
		assertEquals(foundChildCount, childCount, "Failed To Verify Child Count");
		groupLogger.info("Successfully Verified Child Count : " + foundChildCount);
		test_steps.add("Successfully Verified Child Count : " + foundChildCount);

		String foundResStatus = driver.findElement(By.xpath(resStatusPath)).getText();
		assertEquals(foundResStatus, resStatus, "Failed To Verify Reservation Status");
		groupLogger.info("Successfully Verified Reservation Status : " + foundResStatus);
		test_steps.add("Successfully Verified Reservation Status : " + foundResStatus);

		String foundRoomDetail = driver.findElement(By.xpath(roomClassRoomNoPath)).getText();
		assertEquals(foundRoomDetail, roomClassRoomNo, "Failed To Verify Room Detail");
		groupLogger.info("Successfully Verified Room Detail : " + foundRoomDetail);
		test_steps.add("Successfully Verified Room Detail : " + foundRoomDetail);

		String foundArivalDate = driver.findElement(By.xpath(arivalDatePath)).getText();
		assertEquals(foundArivalDate, arivalDate, "Failed To Verify Arival Date");
		groupLogger.info("Successfully Verified Arival Date : " + foundArivalDate);
		test_steps.add("Successfully Verified Arival Date : " + foundArivalDate);

		String foundDepartDate = driver.findElement(By.xpath(departDatePath)).getText();
		assertEquals(foundDepartDate, departDate, "Failed To Verify Depart Date");
		groupLogger.info("Successfully Verified Depart Date : " + foundDepartDate);
		test_steps.add("Successfully Verified Depart Date : " + foundDepartDate);

		String foundTotalNights = driver.findElement(By.xpath(totalNightsPath)).getText();
		assertEquals(foundTotalNights, totalNights, "Failed To Verify Total Nights");
		groupLogger.info("Successfully Verified Total Nights : " + foundTotalNights);
		test_steps.add("Successfully Verified Total Nights : " + foundTotalNights);

		return test_steps;
	}

	public ArrayList<String> verifyMarketSegmentDropDownGroups(WebDriver driver, ArrayList<String> test_steps,
			String MarketSegment, boolean isContains) throws InterruptedException {
		Elements_Groups group = new Elements_Groups(driver);
		Wait.WaitForElement(driver, OR.Market_Segment);
		Utility.ScrollToElement(group.Market_Segment, driver);
		test_steps.add("Market Segment Dropdown Opened");
		groupLogger.info("Market Segment Dropdown Opened");

		boolean isMarketSegmentFound = false;
		Select dropdown = new Select(group.Market_Segment);

		// Get all options
		List<WebElement> options = dropdown.getOptions();

		for (int i = 0; i < options.size(); i++) {

			if (isContains) {
				isMarketSegmentFound = options.get(i).getText().equals(MarketSegment);

				if (isMarketSegmentFound) {
					assertEquals(isMarketSegmentFound, true);
					test_steps.add("Market Segment Dropdown Contains Active: " + MarketSegment);
					groupLogger.info("Market Segment Dropdown Contains Active: " + MarketSegment);
					break;
				}
			} else {
				if (!isMarketSegmentFound) {
					isMarketSegmentFound = options.get(i).getText().contains(MarketSegment);
					assertEquals(isMarketSegmentFound, false);
					test_steps.add("Market Segment Dropdown Doesn't Contain InActive/Obsolete: " + MarketSegment);
					groupLogger.info("Market Segment Dropdown Doesn't Contain InActive/Obsolete: " + MarketSegment);
					break;
				}

			}
		}
		return test_steps;
	}

	public ArrayList<String> enterrGroupName(WebDriver driver, String GroupName) throws InterruptedException {
		Elements_Groups group = new Elements_Groups(driver);

		ArrayList<String> testSteps = new ArrayList<>();
		testSteps = clickOnNewAccount(driver);
		Wait.WaitForElement(driver, OR.Group_Name);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Group_Name), driver);

		group.Group_Name.sendKeys(GroupName);
		testSteps.add("Enter group name : " + GroupName);
		groupLogger.info("Enter group name : " + GroupName);
		return testSteps;
	}

	public ArrayList<String> clickOnNewAccount(WebDriver driver) {

		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.New_Account_Btn);
		Wait.waitForElementToBeVisibile(By.xpath(OR.New_Account_Btn), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.New_Account_Btn), driver);
		group.New_Account_Btn.click();
		testSteps.add("Click on new account button");
		return testSteps;
	}

	public ArrayList<String> selectAccountAttributes(WebDriver driver, String MarketSegment, String Referral) {

		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.Market_Segment);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Market_Segment), driver);

		new Select(group.Market_Segment).selectByVisibleText(MarketSegment);
		testSteps.add("Select market segment : " + MarketSegment);
		groupLogger.info("Select market segment : " + MarketSegment);

		new Select(group.Referrls).selectByVisibleText(Referral);
		testSteps.add("Select referral : " + Referral);
		groupLogger.info("Select referral : " + Referral);
		return testSteps;
	}

	public ArrayList<String> enterMailingAddress(WebDriver driver, String firstName, String lastName, String phone,
			String address, String city, String state, String country, String postalCode) throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> testSteps = new ArrayList<>();

		group.FirstName.sendKeys(firstName);
		testSteps.add("Enter first name : " + firstName);
		groupLogger.info("Enter First Name : " + firstName);

		group.LastName.sendKeys(lastName);
		testSteps.add("Enter last name : " + lastName);
		groupLogger.info("Enter Last Name : " + lastName);

		Utility.ScrollToElement_NoWait(group.Phone, driver);
		group.Phone.sendKeys(phone);
		testSteps.add("Enter phone number : " + phone);
		groupLogger.info("Enter Phone Number : " + phone);

		Utility.ScrollToElement_NoWait(group.Address1, driver);
		group.Address1.sendKeys(address);
		testSteps.add("Enter Address : " + address);
		groupLogger.info("Enter Address : " + address);

		group.City.sendKeys(city);
		testSteps.add("Enter city : " + city);
		groupLogger.info("Enter City : " + city);

		Utility.ScrollToElement_NoWait(group.State, driver);
		new Select(group.State).selectByVisibleText(state);
		testSteps.add("Select state : " + state);
		groupLogger.info("Select State : " + state);

		group.PostalCode.sendKeys(postalCode);
		testSteps.add("Enter postal code : " + postalCode);
		groupLogger.info("Enter postal code : " + postalCode);

		Utility.ScrollToElement_NoWait(group.Country, driver);
		new Select(group.Country).selectByVisibleText(country);
		testSteps.add("Select country : " + country);
		groupLogger.info("Select Country : " + country);

		return testSteps;
	}

	public ArrayList<String> Billinginfo(WebDriver driver) throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.Check_Mailing_Info);
		Wait.waitForElementToBeClickable(By.xpath(OR.Check_Mailing_Info), driver);
		Utility.ScrollToElement_NoWait(group.Check_Mailing_Info, driver);
		if (!group.Check_Mailing_Info.isSelected()) {
			group.Check_Mailing_Info.click();
			testSteps.add("Click same as mailing address");
			groupLogger.info("Click same as mailing address");
		} else {
			testSteps.add("Same as mailing address by defult checked");
		}
		return testSteps;

	}

	public ArrayList<String> clickOnSave(WebDriver driver) throws InterruptedException {

		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		group.Group_Save.click();
		testSteps.add("Click on save button");
		Wait.waitForElementToBeVisibile(By.xpath(OR.Group_Save), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Group_Save), driver);
		Wait.WaitForElement(driver, OR.Group_Save);
		return testSteps;

	}

	public void clickOnGroupsReservationTab(WebDriver driver) {
		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.Groups_ReservationsTab);
		group.Groups_ReservationsTab.click();
		test_steps.add("Click on New Reservation Tab in Group");
		groupLogger.info("Click on New Reservation Tab in Group");
	}

	public ArrayList<String> navigateRoomBlock(WebDriver driver) {
		Elements_Groups group = new Elements_Groups(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement_ID(driver, OR.RoomBlocksTab);
		Wait.waitForElementToBeVisibile(By.id(OR.RoomBlocksTab), driver);
		Wait.waitForElementToBeClickable(By.id(OR.RoomBlocksTab), driver);
		group.RoomBlocksTab.click();
		testSteps.add("Click on new block");
		groupLogger.info("Click on new block");
		return testSteps;

	}

	public String getRoomBlockedInRoomBlockDetatil(WebDriver driver) {

		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		Wait.explicit_wait_xpath(OR.RoomBlockInBlockDetails, driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.RoomBlockInBlockDetails), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.RoomBlockInBlockDetails), driver);
		String roomBlocked = groups.RoomBlockInBlockDetails.getText();
		StringTokenizer str = new StringTokenizer(roomBlocked, "Rooms Blocked:");
		String value = str.nextToken();
		return value.replaceAll("\\s", "");
	}

	public String getPickUpPercentageInRoomBlockDetatil(WebDriver driver) {

		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);

		Wait.WaitForElement(driver, OR.PickedupInBlockInfo);
		Wait.waitForElementToBeVisibile(By.xpath(OR.PickedupInBlockInfo), driver);
		String pickUpPercentage = groups.PickedupInBlockInfo.getText();
		StringTokenizer str = new StringTokenizer(pickUpPercentage, "Picked up %:");
		String value = str.nextToken();
		return value.replaceAll("\\s", "");
	}

	public String getTotalRoomNightsInRoomBlockDetail(WebDriver driver) {

		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		Wait.WaitForElement(driver, OR.RoomPerNightInBlockDetails);
		Wait.waitForElementToBeVisibile(By.xpath(OR.RoomPerNightInBlockDetails), driver);
		String value = groups.RoomPerNightInBlockDetails.getText();
		return value;
	}

	public String getExpectedRevenueInRoomBlockDetail(WebDriver driver) {
		String path = "//span[contains(text(),'Expected Revenue: ')]/span";
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		String value = driver.findElement(By.xpath(path)).getText();
		return value.replace("$", "");
	}

	public String getExpectedRevenueInGroupInfo(WebDriver driver) {
		Elements_Groups group = new Elements_Groups(driver);
		Wait.WaitForElement(driver, OR.Group_RoomBlock_GroupInfo_ExpectedRevenue);
		return group.Group_RoomBlock_GroupInfo_ExpectedRevenue.getText().replace("$", "");
	}

	public ArrayList<String> searchGroupAccount(WebDriver driver, String accountName, String accountNumber,
			boolean isFound, boolean isClick) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups elements_Groups = new Elements_Groups(driver);

		elements_Groups.Resgroups_AccountName.clear();
		elements_Groups.Resgroups_AccountName.sendKeys(accountName);
		test_steps.add("Enter account name: " + accountName);
		groupLogger.info("Enter account name : " + accountName);

		elements_Groups.Resgroups_AccountNumber.clear();
		elements_Groups.Resgroups_AccountNumber.sendKeys(accountNumber);
		test_steps.add("Enter account #: " + accountNumber);
		groupLogger.info("Enter account #: " + accountNumber);

		elements_Groups.Resgroups_GoButton.click();
		test_steps.add("Click on go button");
		groupLogger.info("Click on go button");

		Wait.WaitForElement(driver, OR.Resgroups_ReservToSelect);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Resgroups_ReservToSelect), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Resgroups_ReservToSelect), driver);
		// Wait.wait2Second();
		if (isFound) {
			try {
				if (elements_Groups.Resgroups_ReservToSelect.isDisplayed()) {
					assertTrue(elements_Groups.Resgroups_ReservToSelect.isDisplayed(),
							"Searched group account isn't diplayed");
					test_steps.add(accountName + " Account Found");
					groupLogger.info(accountName + " Account Found");
					if (isClick) {
						elements_Groups.Resgroups_ReservToSelect.click();
						Wait.wait2Second();
						assertTrue(elements_Groups.Resgroups_AccountDetailsPage.isDisplayed(),
								"Account Detail Page isn't open and tabs aren't displayed");
					}
				} else {
					test_steps.add(accountName + " Account Not Found");
					groupLogger.info(accountName + " Account Not Found");
				}
			} catch (Exception e) {
				try {
					if (driver.findElement(By.id("MainContent_lblMessage")).isDisplayed()) {
						test_steps.add(accountName + " Account Not Found");
						groupLogger.info(accountName + " Account Not Found");
					}
				} catch (Exception exception) {
					System.out.println("Message not appaer");
				}
			}
		}
		return test_steps;
	}

	public String getReservationDetails(WebDriver driver, String reservationNumber, int index) {
		String path = "//td[text()='" + reservationNumber + "']//following-sibling::td[" + index + "]";
		if (index == 7 || index == 8) {
			path = path + "/span";
		}
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);
		WebElement element = driver.findElement(By.xpath(path));
		return element.getText();
	}

	public void ClickReservationName_VerifyPopup(WebDriver driver, String reservationNumber, ArrayList<String> steps)
			throws InterruptedException {
		Elements_Groups group = new Elements_Groups(driver);
		String path = "//td[text()='" + reservationNumber + "']//following-sibling::td[1]";
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement_NoWait(element, driver);

		element.click();
		steps.add("Click on guest name");
		groupLogger.info("Click on guest name");

		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		groupLogger.info(" Switched to Frame ");
		steps.add("Swith Iframe");

		Wait.WaitForElement(driver, OR.reservationDetailPopup);
		Wait.waitForElementToBeVisibile(By.xpath(OR.reservationDetailPopup), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.reservationDetailPopup), driver);
		assertTrue(group.reservationDetailPopup.isDisplayed(), "Failed: Reservation Popup not displayed");
		steps.add("Verify that reservation details is shown within the popup");
		groupLogger.info("Verify that Reservation details is shown within the popup");

		driver.switchTo().defaultContent();
		groupLogger.info(" Switched to Default Content ");

		Wait.WaitForElement(driver, OR.reservationDetailPopup_Close);
		Wait.waitForElementToBeVisibile(By.xpath(OR.reservationDetailPopup_Close), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.reservationDetailPopup_Close), driver);
		group.reservationDetailPopup_Close.click();
		steps.add("Click on reservation detail popup close button");
		groupLogger.info("Click on reservation detail popup close button");

	}

	public String getReleaseDateInRoomBlockDetatil(WebDriver driver) {

		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		Wait.WaitForElement(driver, OR.ReleaseDateInBlockDetails);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ReleaseDateInBlockDetails), driver);
		String pickUpPercentage = groups.ReleaseDateInBlockDetails.getText();

		String[] strSplit = pickUpPercentage.split(":");
		String value = strSplit[1].trim();
		return value;
	}

	public void clickOnSelectRooms(WebDriver driver) throws InterruptedException {

		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		Wait.WaitForElement_ID(driver, OR.SelectRoomsButton);
		Wait.waitForElementToBeVisibile(By.id(OR.SelectRoomsButton), driver);
		Wait.waitForElementToBeClickable(By.id(OR.SelectRoomsButton), driver);
		Utility.ScrollToElement_NoWait(groups.SelectRoomsButton, driver);
		groups.SelectRoomsButton.click();
	}

	public String getRomNumberFromSelectRooms(WebDriver driver, String roomClassAbreviation, String RoomClassName)
			throws InterruptedException {

		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);

		driver.switchTo().frame(driver.findElement(By.id("MainContent_Iframe_AssignRooms")));
		Wait.WaitForElement(driver, OR.SelectRoomHeading);
		Wait.waitForElementToBeVisibile(By.xpath(OR.SelectRoomHeading), driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.SelectRoomHeading), driver);
		new Select(groups.SelectRoomClassInSelectRooms).selectByVisibleText(RoomClassName);

		String path = "(//div[text()='" + roomClassAbreviation + "']//..//following-sibling::div)[2]";
		System.out.println(path);
		WebElement element = driver.findElement(By.xpath(path));
		String getRoomNumber = element.getAttribute("title");
		System.out.println(getRoomNumber);
		String pathAssigned = "(//div[text()='" + roomClassAbreviation + "']//..//following-sibling::div)[7]";
		System.out.println(pathAssigned);
		WebElement elementAssigned = driver.findElement(By.xpath(pathAssigned));
		Utility.ScrollToElement_NoWait(element, driver);
		elementAssigned.click();
		groups.ClickOnDoneInSelectRooms.click();
		driver.switchTo().defaultContent();
		return getRoomNumber;
	}

	public String getPickedupRevenueInRoomBlockDetail(WebDriver driver) {

		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		Wait.WaitForElement(driver, OR.PickedupInBlockDetails);
		Wait.waitForElementToBeVisibile(By.xpath(OR.PickedupInBlockDetails), driver);
		String value = groups.PickedupInBlockDetails.getText();
		return value.replace("$", "");
	}

	// 22nd Methods for RoomingList
	public ArrayList<String> clickOnRoomingListButton(WebDriver driver) throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<>();
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		Wait.WaitForElement_ID(driver, OR.RoomingListButton);
		Wait.waitForElementToBeVisibile(By.id(OR.RoomingListButton), driver);
		Wait.waitForElementToBeClickable(By.id(OR.RoomingListButton), driver);
		Utility.ScrollToElement_NoWait(groups.RoomingListButton, driver);
		groups.RoomingListButton.click();
		testSteps.add("Click on rooming list button");
		groupLogger.info("Click on rooming list button");

		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		testSteps.add("Swictehd to iframe");
		groupLogger.info("Swictehd to iframe");

		return testSteps;

	}

	public String getBlockName(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.blockName), driver);
		String blockName = groups.blockName.getText();
		groupLogger.info(blockName);
		return blockName;

	}

	public String getArriveDate(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.arrive), driver);
		String arrive = groups.arrive.getText();
		groupLogger.info(arrive);
		return arrive;

	}

	public String getDepartDate(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		String depart = groups.depart.getText();
		groupLogger.info(depart);
		return depart;
	}

	public String getQgr(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		String qgr = groups.qgr.getText();
		groupLogger.info(qgr);
		return qgr;
	}

	public String getReservationStatus(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		String reservationStatus = groups.reservationStatus.getText();
		groupLogger.info(reservationStatus);
		return reservationStatus;
	}

	public String getExpectedRevenue(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		String expectedRevenue = groups.expectedRevenue.getText();
		groupLogger.info(expectedRevenue);
		return expectedRevenue;
	}

	public String getPickupRevenue(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		String pickupRevenue = groups.pickupRevenue.getText();
		groupLogger.info(pickupRevenue);
		return pickupRevenue;
	}

	public String getPickupPercent(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		String pickupPercent = groups.pickupPercent.getText();
		groupLogger.info(pickupPercent);
		return pickupPercent;
	}

	public ArrayList<String> enterReservationContentIntoRoomListingPopup(WebDriver driver, String firstName,
			String lastName, String amount, String roomClass) throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<>();
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);

		Wait.WaitForElement(driver, OR.firstNameInput);
		Utility.ScrollToElement_NoWait(groups.firstNameInput, driver);
		groups.firstNameInput.clear();
		groups.firstNameInput.sendKeys(firstName);
		testSteps.add("Entered first name : " + firstName);
		groupLogger.info("Entered first name : " + firstName);

		groups.lastNameInput.clear();
		groups.lastNameInput.sendKeys(lastName);
		testSteps.add("Entered last name : " + lastName);
		groupLogger.info("Entered last name : " + lastName);

		new Select(groups.selectRoomClass).selectByVisibleText(roomClass);
		testSteps.add("Selected Room Class : " + roomClass);
		groupLogger.info("Selected Room Class : " + roomClass);

		new Select(groups.selectRoomNumber).selectByIndex(1);
		testSteps.add("Selected Room Number");
		groupLogger.info("Selected Room Number");

		groups.amountInput.clear();
		groups.amountInput.sendKeys(amount);
		testSteps.add("Entered amount : " + amount);
		groupLogger.info("Entered amount : " + amount);

		return testSteps;

	}
	public String getSelecteRoomNumber(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		Select  select = new Select(groups.selectRoomNumber);
		WebElement opton = select.getFirstSelectedOption();
		
		groupLogger.info("Selected Room Number:"+opton.getText());
		return opton.getText();
	}
	public ArrayList<String> clickOnBillingInfoIcon(WebDriver driver) throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<>();
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);

		Wait.WaitForElement(driver, OR.billingInfoIcon);
		Utility.ScrollToElement(groups.billingInfoIcon, driver);
		groups.billingInfoIcon.click();
		testSteps.add("Click on billing info icon");
		groupLogger.info("Click on billing info icon");

		driver.switchTo().defaultContent();
		testSteps.add("Swictehd to default window");
		groupLogger.info("Swictehd to default window");

		driver.switchTo().frame(driver.findElement(By.id("dialog-body1")));
		testSteps.add("Swictehd to iframe");
		groupLogger.info("Swictehd to iframe");

		return testSteps;

	}

	public ArrayList<String> clickOnPickupButton(WebDriver driver) throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<>();
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		Wait.WaitForElement(driver, OR.pickupButton);
		Wait.waitForElementToBeVisibile(By.xpath(OR.pickupButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.pickupButton), driver);
		Utility.ScrollToElement_NoWait(groups.pickupButton, driver);
		groups.pickupButton.click();
		testSteps.add("Click on pickup button");
		groupLogger.info("Click on pickup button");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("dialog-body1")));
		testSteps.add("Switch to rooming list pickup summery popup");
		return testSteps;

	}

	public ArrayList<String> enterBillingInfoInRoomListing(WebDriver driver, String salulation, String paymentMethod,
			String cardNumber, String cardExpiryDate) throws InterruptedException {
		ArrayList<String> testSteps = new ArrayList<>();
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);

		Wait.WaitForElement(driver, OR.headingBillingINfo);
		assertTrue(groups.headingBillingINfo.isDisplayed(), "headingBillingINfo popup is not dipalyed");
		testSteps.add("billing info popup opened successfully");
		groupLogger.info("billing info popup opened successfully");

		new Select(groups.selectSalulation).selectByVisibleText(salulation);
		testSteps.add("Selected salulation : " + salulation);
		groupLogger.info("Selected salulation : " + salulation);

		new Select(groups.selectPaymentMethod).selectByVisibleText(paymentMethod);
		testSteps.add("Selected paymentMethod : " + paymentMethod);
		groupLogger.info("Selected paymentMethod : " + paymentMethod);

		groups.accountInput.clear();
		groups.accountInput.sendKeys(cardNumber);
		testSteps.add("Entered cardNumber : " + cardNumber);
		groupLogger.info("Entered cardNumber : " + cardNumber);

		groups.expiryDateInput.clear();
		groups.expiryDateInput.sendKeys(cardExpiryDate);
		testSteps.add("Entered card expiry date :  " + cardExpiryDate);
		groupLogger.info("Entered card expiry date : " + cardExpiryDate);

		groups.saveBillingInfoInput.click();
		testSteps.add("Click on save button");
		groupLogger.info("Click on save button");

		driver.switchTo().defaultContent();
		Wait.wait3Second();
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		testSteps.add("Swictehd to iframe");
		groupLogger.info("Swictehd to iframe");

		Wait.WaitForElement(driver, OR.imgTick);
		Wait.waitForElementToBeVisibile(By.xpath(OR.imgTick), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.imgTick), driver);
		Utility.ScrollToElement_NoWait(groups.imgTick, driver);
		if (groups.imgTick.isDisplayed()) {
			testSteps.add("verified tick icon");
			groupLogger.info("verified tick icon");

		} else {
			Assert.assertTrue(false, "Tick Icon doesn't exist in roomlisting popup");
		}
		return testSteps;
	}

	public String replaceCurrency(String str) {
		str = str.replace("$", "");
		str = str.trim();
		return str;
	}

	public String getBlockNameInRoomingListSummary(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		Wait.WaitForElement_ID(driver, OR.RoomingListSummary_BlockName);
		Wait.waitForElementToBeVisibile(By.id(OR.RoomingListSummary_BlockName), driver);
		Wait.waitForElementToBeClickable(By.id(OR.RoomingListSummary_BlockName), driver);
		return groups.RoomingListSummaryBlockName.getText();

	}

	public String getStatusInRoomingListSummary(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		return groups.RoomingListSummaryStatus.getText();

	}

	public String getArriveDateInRoomingListSummary(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		return groups.RoomingListSummaryArriveDate.getText();

	}

	public String getDepartDateInRoomingListSummary(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		return groups.RoomingListSummaryDepartDate.getText();

	}

	public String getQGRInRoomingListSummary(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		return groups.RoomingListSummaryQgr.getText();

	}

	public String getExpectedRevenueInRoomingListSummary(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		return groups.RoomingListSummaryExpectedRevenue.getText();

	}

	public String getPickedupRevenueInRoomingListSummary(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		return groups.RoomingListSummaryPickedupRdevenue.getText();

	}

	public String getPickedupPercentageInRoomingListSummary(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		return groups.RoomingListSummaryPickedupPercentage.getText();

	}

	public String getReservationDetailsfromRoomingListSummary(WebDriver driver, String resservationNumber, int index)
			throws InterruptedException {

		String path = "(//td[text()='" + resservationNumber + "']//following-sibling::td)[" + index + "]";
		WebElement element = driver.findElement(By.xpath(path));
		return element.getText();

	}

	public String getReservationNumberfromRoomingListSummary(WebDriver driver,String guestName) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		
		String path = "(//a[text()='"+guestName+"']//..//..//..//tr)[2]//td";
		WebElement element = driver.findElement(By.xpath(path));
		String getNumber = element.getText();
		return getNumber;
	}
	public String getGuestNamefromRoomingListSummary(WebDriver driver, String resservationNumber,boolean isClick)
			throws InterruptedException {

		String path = "//td[text()='" + resservationNumber + "']//following-sibling::td//a";
		WebElement element = driver.findElement(By.xpath(path));
		String getName = element.getText();
		if(isClick){
			element.click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body2")));
		}
		return getName;

	}
	
	public String getReservationDetailsfromPopup(WebDriver driver,WebElement element,String elementPath) throws InterruptedException {

		Wait.WaitForElement(driver, elementPath);
		Wait.waitForElementToBeVisibile(By.xpath(elementPath), driver);
		Utility.ScrollToElement_NoWait(element, driver);
		return element.getText();
	}
	
	public void verifyStartCheckInButton(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		Utility.ScrollToElement(groups.StartCheckInButtonInReservationDetails, driver);
		assertEquals(groups.StartCheckInButtonInReservationDetails.isDisplayed(), true,"Faile: Start check-in button is not displayed");
	}
	public void clickOnCloseReservationDetailsPopup(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		driver.switchTo().defaultContent();
		//driver.switchTo().frame(driver.findElement(By.id("dialog-body1")));
		Utility.ScrollToElement(groups.CloseReservationDetailsPopup, driver);
		groups.CloseReservationDetailsPopup.click();
		groupLogger.info("Switch frame");
	}
	
	public void clickonCloseRoomingListPopup(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		driver.switchTo().defaultContent();
		Wait.waitForElementToBeClickable(By.xpath(OR.CloseRoomingListPopup), driver);
		groups.CloseRoomingListPopup.click();
		groupLogger.info("Switch to default frame");

	}
	
	public void clickonClosePickedupSummary(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups groups = new Elements_AdvanceGroups(driver);
		driver.switchTo().defaultContent();
	//	driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		Wait.waitForElementToBeClickable(By.xpath(OR.ClosePickedupSummary), driver);
		groups.ClosePickedupSummary.click();

	}

	public ArrayList<String> enterGroupName(WebDriver driver, String GroupName) throws InterruptedException {
		Elements_Groups group = new Elements_Groups(driver);

		ArrayList<String> testSteps = new ArrayList<>();
		testSteps = clickOnNewAccount(driver);
		Wait.WaitForElement(driver, OR.Group_Name);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Group_Name), driver);

		group.Group_Name.sendKeys(GroupName);
		testSteps.add("Enter group name : " + GroupName);
		groupLogger.info("Enter group name : " + GroupName);
		return testSteps;
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <clickGroupFolioTab> 
	 * ' Description: <This method will click on folio tab in group> 
	 * ' Input parameters: <WebDriver driver> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Farhan Ghaffar>
	 * ' Created On:  <06/10/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String> clickGroupFolioTab(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.WaitForElement_ID(driver, OR.groupFolioTab);
		Wait.waitForElementToBeVisibile(By.id(OR.groupFolioTab), driver);
		Wait.waitForElementToBeClickable(By.id(OR.groupFolioTab), driver);
		Utility.ScrollToElement(group.groupFolioTab, driver);
		group.groupFolioTab.click();
		test_steps.add("Click Folio Option Button");
		groupLogger.info("Click Folio Option Button");
		return test_steps;
	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <selectNoShowPolicy> 
	 * ' Description: <This method will select noshow policy inside group> 
	 * ' Input parameters: <WebDriver driver, String policyName> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Farhan Ghaffar>
	 * ' Created On:  <06/10/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String> selectNoShowPolicy(WebDriver driver, String policyName) {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups groups = new Elements_Groups(driver);
		Wait.WaitForElement_ID(driver, OR.folioOptionNoShowPolicy);
		Wait.waitForElementToBeVisibile(By.id(OR.folioOptionNoShowPolicy), driver);
		Wait.waitForElementToBeClickable(By.id(OR.folioOptionNoShowPolicy), driver);
		new Select(groups.folioOptionNoShowPolicy).selectByVisibleText(policyName);
		test_steps.add("Selected No Show Policy : " + policyName);
		groupLogger.info("Selected No Show Policy : " + policyName);
		return test_steps;
	}
	
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <verifyNoShowPolicy> 
	 * ' Description: <This method will verify selected noshow policy inside group> 
	 * ' Input parameters: <WebDriver driver, String policyName> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Farhan Ghaffar>
	 * ' Created On:  <06/10/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String> verifyNoShowPolicy(WebDriver driver, String policyName) {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups groups = new Elements_Groups(driver);

		Wait.WaitForElement_ID(driver, OR.folioOptionNoShowPolicy);
		Wait.waitForElementToBeVisibile(By.id(OR.folioOptionNoShowPolicy), driver);
		Wait.waitForElementToBeClickable(By.id(OR.folioOptionNoShowPolicy), driver);
		String foundOption = new Select(groups.folioOptionNoShowPolicy).getFirstSelectedOption().getText();
		assertEquals(foundOption, policyName, "Failed to verify seleted NoShow policy");
		test_steps.add("Successfully Verified NoShow Policy : " + policyName);
		groupLogger.info("Successfully Verified NoShow Policy : " + policyName);
		return test_steps;
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <selectCancelationPolicy> 
	 * ' Description: <This method will first delete all pre-selected policies then select cancelation policy inside group> 
	 * ' Input parameters: <WebDriver driver, String policyName> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Farhan Ghaffar>
	 * ' Created On:  <06/11/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String> selectCancelationPolicy(WebDriver driver, String policyName) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Groups groups = new Elements_Groups(driver);
	
		Wait.WaitForElement(driver, OR.FolioOptionCancellationPolicy);
		Wait.waitForElementToBeVisibile(By.xpath(OR.FolioOptionCancellationPolicy), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.FolioOptionCancellationPolicy), driver);
		Utility.ScrollToElement_NoWait(groups.FolioOptionCancellationPolicy, driver);
		while(!groups.FolioOptionCancellationPolicyListSource.isDisplayed()) {
			groups.FolioOptionCancellationPolicy.click();
			groupLogger.info("FolioOptionCancellationPolicy click3d");
				
		}
		
		Wait.WaitForElement_ID(driver, OR.FolioOptionCancelationPolicyDeleteAllBtn);
		Wait.waitForElementToBeVisibile(By.id(OR.FolioOptionCancelationPolicyDeleteAllBtn), driver);
		Wait.waitForElementToBeClickable(By.id(OR.FolioOptionCancelationPolicyDeleteAllBtn), driver);
		groups.FolioOptionCancelationPolicyDeleteAllBtn.click();
		
		Wait.WaitForElement(driver, OR.FolioOptionCancellationPolicyListSource);
		Wait.waitForElementToBeVisibile(By.xpath(OR.FolioOptionCancellationPolicyListSource), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.FolioOptionCancellationPolicyListSource), driver);
		new Select(groups.FolioOptionCancellationPolicyListSource).selectByVisibleText(policyName);
		
		Wait.WaitForElement(driver, OR.FolioOptionCancellationPolicyPickBtn);
		Wait.waitForElementToBeVisibile(By.xpath(OR.FolioOptionCancellationPolicyPickBtn), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.FolioOptionCancellationPolicyPickBtn), driver);
		Utility.ScrollToElement_NoWait(groups.FolioOptionCancellationPolicyPickBtn, driver);
		groups.FolioOptionCancellationPolicyPickBtn.click();
		
		Wait.WaitForElement(driver, OR.FolioOptionCancellationPolicyDoneBtn);
		Wait.waitForElementToBeVisibile(By.xpath(OR.FolioOptionCancellationPolicyDoneBtn), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.FolioOptionCancellationPolicyDoneBtn), driver);		
		Utility.ScrollToElement(groups.FolioOptionCancellationPolicyDoneBtn, driver);
		groups.FolioOptionCancellationPolicyDoneBtn.click();
		test_steps.add("Selected Cancellation Policy : " + policyName);
		groupLogger.info("Selected Cancellation Policy : " + policyName);
		return test_steps;
	
	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <clickGroupFolioOption> 
	 * ' Description: <This method will click on folio option in folio section of group> 
	 * ' Input parameters: <WebDriver driver> 
	 * ' Return value: <ArrayList<String>>
	 * ' Created By: <Farhan Ghaffar>
	 * ' Created On:  <06/10/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */	
	public ArrayList<String> clickGroupFolioOption(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.WaitForElement_ID(driver, OR.GroupFolioOptionButton);
		Wait.waitForElementToBeVisibile(By.id(OR.GroupFolioOptionButton), driver);
		Wait.waitForElementToBeClickable(By.id(OR.GroupFolioOptionButton), driver);
		Utility.ScrollToElement(group.GroupFolioOptionButton, driver);
		group.GroupFolioOptionButton.click();
		test_steps.add("Click Folio Option Button");
		groupLogger.info("Click Folio Option Button");
		return test_steps;
	}
	
}
