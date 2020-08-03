package com.innroad.inncenter.tests;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.innroad.inncenter.pageobjects.Account;
import com.innroad.inncenter.pageobjects.Folio;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Reports;
import com.innroad.inncenter.pageobjects.Reservation;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyAccountBalanceCityLedgerReportWithIncludePendingItemsUnchecked extends TestCore {

	// Automation-1490
	private WebDriver driver = null;
	ArrayList<String> testNames = new ArrayList<>();
	ArrayList<String> testDescriptions = new ArrayList<>();
	ArrayList<String> testCategories = new ArrayList<>();
	public static String testName = "VerifyAccountBalanceCityLedgerReportWithIncludePendingItemsUnchecked";
	public static String testDescription = "";
	public static String testCatagory = "";
	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = { "Accounts", "Reports" })
	public void verifyAccountBalanceCityLedgerReportWithIncludePendingItemsUnchecked(String marketSegment,
			String referral, String accountName, String accountFirstName, String accountLastName, String phonenumber,
			String alternativeNumber, String addressLineOne, String addressLineTwo, String addressLineThree,
			String email, String city, String state, String postalCode, String travelAgentAccount, String status,
			String bar, String corporateAccount, String unitOwnersAccount, String giftCertificateAccount,
			String houseAccount, String corporateAmount, String houseAmount, String travelAgentAmount,
			String unitOwnersAmount, String giftCertificateAmount, String spaAmount, String spa, String property)
			throws InterruptedException, IOException {

		testDescription = "Verify City Ledger with 'Include pending Items' checkbox is unchecked<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682480' target='_blank'>"
				+ "Click here to open TestRail: C682480</a>";
		testCatagory = "Accounts";

		testNames.add(testName);
		testDescriptions.add(testDescription);
		testCategories.add(testCatagory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Navigation navigation = new Navigation();
		Account account = new Account();
		Reservation reservation = new Reservation();
		Folio folio = new Folio();
		Reports reports = new Reports();
		String ledgerType = "City Ledger";
		String includePendingItems = "Include Pending Items";
		String groups = "Group";
		String advancedDeposit = "Advanced Deposits";

		String randomNumber = Utility.GenerateRandomNumber(999);
		accountName = accountName + randomNumber;
		accountLastName = accountLastName + randomNumber;
		String corporateAccountLastName = "Corporate" + accountLastName;
		String travelAgentAccountLastName = "TravelAgent" + accountLastName;
		String houseAccountLastName = "House" + accountLastName;
		String unitOwnersAccountLastName = "UnitOwners" + accountLastName;
		String giftCertificateAccountLastName = "GiftCertificate" + accountLastName;
		String corporateAccountName = accountFirstName + corporateAccountLastName;
		String travelAgentAccountName = accountFirstName + travelAgentAccountLastName;
		String houseAccountName = accountFirstName + houseAccountLastName;
		String unitOwnersAccountName = accountFirstName + unitOwnersAccountLastName;
		String giftCertificateAccountName = accountFirstName + giftCertificateAccountLastName;
		String corporateAccountNumber = null;
		String travelAgentAccountNumber = null;
		String houseAccountNumber = null;
		String unitOwnersAccountNumber = null;
		String giftCertificateAccountNumber = null;
		String iconSource = null;
		String icon = null;
		String corporateAmountWithTax = null;
		String houseAmountWithTax = null;
		String travelAgentAmountWithTax = null;
		String unitOwnersAmountWithTax = null;
		String giftCertificateAmountWithTax = null;
		String spaAmountWithTax = null;

		String endingBalanceLabel = "Ending Balance: ";
		String endingBalanceAmount = null;
		String beginningBalanceLabel = "Beginning Balance: ";
		String beginningBalanceAmount = null;
		String paymentsLabel = "Payments: ";
		String paymentsAmount = null;
		String newChargesLabel = "New Charges: ";
		String newChargesAmount = null;
		String taxAndServiceChargesLabel = "Taxes & Service Charges:";
		String taxAndServiceChargesAmount = null;
		String revenuesLabel = "Revenues: ";
		String revenuesAmount = null;
		String expensesLabel = "Expenses: ";
		String expensesAmount = null;
		String netForPeriodLabel = "Net for Period: ";
		String netForPeriodAmount = null;
		String amountSoldLabel = "Amount Sold: ";
		String amountSoldAmount = null;
		String redeemedLabel = "Redeemed: ";
		String redeemedAmount = null;
		String balanceLabel = "Balance: ";
		String balanceAmount = null;

		String distributionLabel = "Distribution: ";
		String distributionAmount = null;
		String fileName = null;
		String lines[] = null;
		int i = 0;

		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);

			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver = getDriver();
			login_CP(driver);
			testSteps.add("Logged into the application");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Select Property
		try {

			String defaultProperty = navigation.getDefaultProperty(driver);

			if (!defaultProperty.equals(Utility.ReportsProperty)) {
				boolean isPropertyExist = navigation.isPropertyExist(driver, property);
				if (!isPropertyExist) {
					property = defaultProperty;

				}
			}

			testSteps.add("Selected property: " + property);
			app_logs.info("Selected property: " + property);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Select Property", testName, "SelectProperty", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Select Property", testName, "SelectProperty", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// navigate to accounts
		try {
			navigation.Accounts(driver);
			testSteps.add("Navigate Accounts");
			app_logs.info("Navigate Accounts");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Navigate Accounts", testName, "NavigateAccounts", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Navigate Accounts", testName, "NavigateAccounts", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// Corporate New account
		try {
			app_logs.info("==========CREATE '" + corporateAccount.toUpperCase() + "' ACCOUNT==========");
			testSteps.add("==========CREATE '" + corporateAccount.toUpperCase() + "' ACCOUNT==========");

			account.ClickNewAccountbutton(driver, corporateAccount);
			testSteps.add("Click New Account Button");
			app_logs.info("Click New Account Button");

			account.AccountDetails(driver, corporateAccount, corporateAccountName);
			testSteps.add("Select Account Type : " + corporateAccount);
			app_logs.info("Select Account Type : " + corporateAccount);
			testSteps.add("Enter Account Name : " + corporateAccountName);
			app_logs.info("Enter Account Name : " + corporateAccountName);
			String number = Utility.GenerateRandomString15Digit();
			account.enterAccountNumber(driver, number);
			testSteps.add("Change Account Number : " + number);
			app_logs.info("Change Account Number : " + number);
			getTestSteps.clear();
			getTestSteps = account.AccountAttributes(driver, test, marketSegment, referral, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = account.Mailinginfo(driver, test, accountFirstName, corporateAccountLastName, phonenumber,
					alternativeNumber, addressLineOne, addressLineTwo, addressLineThree, email, city, state, postalCode,
					getTestSteps);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Create New Corporate Account", testName, "NewCorporateAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Create New Corporate Account", testName, "NewCorporateAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Check Mailing Info CheckBox
		try {
			getTestSteps.clear();
			getTestSteps = account.Billinginfo(driver, test, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = account.selectPropertyFromAccount(driver, property);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Check Mailing Info CheckBox", testName, "CheckMailingInfoCheckBox",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Check Mailing Info CheckBox", testName, "CheckMailingInfoCheckBox",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Save Account
		try {
			getTestSteps.clear();
			getTestSteps = account.AccountSave(driver, test, corporateAccountName, getTestSteps);
			testSteps.addAll(getTestSteps);
			corporateAccountNumber = account.getAccountNum(driver);
			testSteps.add("Corporate Account Number '" + corporateAccountNumber + "'");
			app_logs.info("Corporate Account Number '" + corporateAccountNumber + "'");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Save  Account", testName, "SaveAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Save Account", testName, "SaveAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// click on folio tab
		try {

			account.navigateFolio(driver);
			app_logs.info("Navigate Folio");
			testSteps.add("Navigate Folio");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to click on folio tab", testName, "FolioTab", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to click on folio tab", testName, "FolioTab", driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		// Add Line items

		try {

			app_logs.info("==========ADD LINE ITEMS==========");
			testSteps.add("==========ADD LINE ITEMS==========");
			getTestSteps.clear();
			getTestSteps = folio.addLineItemAccount(driver, bar, corporateAmount, getTestSteps, property, 2);
			testSteps.addAll(getTestSteps);
			account.saveAfterAccount(driver, corporateAccountName, getTestSteps);
			corporateAmountWithTax = reservation.getLineItemAmount(driver, bar, true);
			iconSource = Utility.LineItemIconSource.get(3);
			icon = Utility.LineItemIconSource.get(2);
			folio.verifyAddedLineItem(driver, iconSource, Utility.getCurrentDate("E MMM dd, yyyy"), bar,
					corporateAmountWithTax, bar);
			testSteps.add("Verified '" + icon + "' Line item '" + bar + "' has been added successfully");
			app_logs.info("Verified '" + icon + "' Line item '" + bar + "' has been added successfully");
			app_logs.info("==========POST LINE ITEMS==========");
			testSteps.add("==========POST LINE ITEMS==========");
			folio.verifyLineItemClickIcon(driver, iconSource, bar, corporateAmountWithTax, bar, true,
					Utility.LineItemIconSource.get(5));
			testSteps.add("Click '" + icon + "' Line Item Icon");
			app_logs.info("Click '" + icon + "' Line Item Icon");
			iconSource = Utility.LineItemIconSource.get(5);
			icon = Utility.LineItemIconSource.get(4);
			folio.verifyAddedLineItem(driver, iconSource, Utility.getCurrentDate("E MMM dd, yyyy"), bar,
					corporateAmountWithTax, bar);
			testSteps.add("Successfully Verified '" + Utility.LineItemIconSource.get(2) + "' Line Item has been '"
					+ icon + "'");
			app_logs.info("Successfully Verified '" + Utility.LineItemIconSource.get(2) + "' Line Item has been '"
					+ icon + "'");

			app_logs.info("==========ADD LINE ITEMS==========");
			testSteps.add("==========ADD LINE ITEMS==========");
			getTestSteps.clear();
			getTestSteps = folio.addLineItemAccount(driver, spa, spaAmount, getTestSteps, property, 2);
			testSteps.addAll(getTestSteps);
			spaAmountWithTax = reservation.getLineItemAmount(driver, spa, true);
			iconSource = Utility.LineItemIconSource.get(3);
			icon = Utility.LineItemIconSource.get(2);
			folio.verifyAddedLineItem(driver, iconSource, Utility.getCurrentDate("E MMM dd, yyyy"), spa,
					spaAmountWithTax, spa);
			testSteps.add("Verified '" + icon + "' Line item '" + spa + "' has been added successfully");
			app_logs.info("Verified '" + icon + "' Line item '" + spa + "' has been added successfully");
			account.saveAfterAccount(driver, corporateAccountName, getTestSteps);

			beginningBalanceAmount = account.getBalance(driver, beginningBalanceLabel);
			testSteps.add(beginningBalanceLabel + " " + beginningBalanceAmount);
			app_logs.info(beginningBalanceLabel + " " + beginningBalanceAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(beginningBalanceAmount), "0.00",
					"Failed: Beginning Balance amount mismatched");

			paymentsAmount = account.getBalance(driver, paymentsLabel);
			testSteps.add(paymentsLabel + " " + paymentsAmount);
			app_logs.info(paymentsLabel + " " + paymentsAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(paymentsAmount), "0.00",
					"Failed: Payment amount mismatched");

			newChargesAmount = account.getBalance(driver, newChargesLabel);
			testSteps.add(newChargesLabel + " " + newChargesAmount);
			app_logs.info(newChargesLabel + " " + newChargesAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(newChargesAmount),
					String.format("%.2f", (Float.parseFloat(corporateAmount) + Float.parseFloat(spaAmount))),
					"Failed: New Charges  amount mismatched");

			taxAndServiceChargesAmount = account.getBalance(driver, taxAndServiceChargesLabel);
			testSteps.add(taxAndServiceChargesLabel + " " + taxAndServiceChargesAmount);
			app_logs.info(taxAndServiceChargesLabel + " " + taxAndServiceChargesAmount);

			endingBalanceAmount = account.getBalance(driver, endingBalanceLabel);
			testSteps.add(endingBalanceLabel + " " + endingBalanceAmount);
			app_logs.info(endingBalanceLabel + " " + endingBalanceAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(endingBalanceAmount), String.format("%.2f",
					(Float.parseFloat(Utility.removeDollarBracketsAndSpaces(newChargesAmount))
							+ Float.parseFloat(Utility.removeDollarBracketsAndSpaces(taxAndServiceChargesAmount)))),
					"Failed: Ending Balance  amount mismatched");
			account.close_Account(driver);
			testSteps.add("Close Account");
			app_logs.info("Close Account");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to add Line Item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to add Line Item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verify account
		try {
			app_logs.info("==========SEARCH '" + corporateAccount.toUpperCase() + "' ACCOUNT==========");
			testSteps.add("==========SEARCH '" + corporateAccount.toUpperCase() + "' ACCOUNT==========");
			getTestSteps.clear();
			getTestSteps = account.searchAccount(driver, test, corporateAccount, corporateAccountName,
					corporateAccountNumber, status, getTestSteps);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify Corporate Account", testName, "VerifyCorporateAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify Corporate Account", testName, "VerifyCorporateAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// House New account
		try {
			app_logs.info("==========CREATE '" + houseAccount.toUpperCase() + "' ACCOUNT==========");
			testSteps.add("==========CREATE '" + houseAccount.toUpperCase() + "' ACCOUNT==========");
			account.NewAccountbutton(driver, houseAccount);
			testSteps.add("Click New Account Button");
			app_logs.info("Click New Account Button");
			account.AccountDetails(driver, houseAccount, houseAccountName);
			testSteps.add("Select Account Type : " + houseAccount);
			app_logs.info("Select Account Type : " + houseAccount);
			testSteps.add("Enter Account Name : " + houseAccountName);
			app_logs.info("Enter Account Name : " + houseAccountName);

			String number = Utility.GenerateRandomString15Digit();
			account.enterAccountNumber(driver, number);
			testSteps.add("Change Account Number : " + number);
			app_logs.info("Change Account Number : " + number);

			getTestSteps.clear();
			getTestSteps = account.AccountSave(driver, test, houseAccountName, getTestSteps);
			testSteps.addAll(getTestSteps);
			houseAccountNumber = account.getAccountNum(driver);
			testSteps.add("House Account Number '" + houseAccountNumber + "'");
			app_logs.info("House Account Number '" + houseAccountNumber + "'");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Create New House Account", testName, "NewHouseAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Create New House Account", testName, "NewHouseAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// click on folio tab
		try {

			account.navigateFolio(driver);
			app_logs.info("Navigate Folio");
			testSteps.add("Navigate Folio");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to click on folio tab", testName, "FolioTab", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to click on folio tab", testName, "FolioTab", driver);

			} else {
				Assert.assertTrue(false);
			}
		}
		try {

			app_logs.info("==========ADD LINE ITEMS==========");
			testSteps.add("==========ADD LINE ITEMS==========");
			getTestSteps.clear();
			getTestSteps = folio.addLineItemAccount(driver, bar, houseAmount, getTestSteps, property, 1);
			testSteps.addAll(getTestSteps);
			account.saveAfterAccount(driver, houseAccountName, getTestSteps);
			houseAmountWithTax = reservation.getLineItemAmount(driver, bar, true);
			iconSource = Utility.LineItemIconSource.get(3);
			icon = Utility.LineItemIconSource.get(2);
			folio.verifyAddedLineItem(driver, iconSource, Utility.getCurrentDate("E MMM dd, yyyy"), bar,
					houseAmountWithTax, bar);
			testSteps.add("Verified '" + icon + "' Line item '" + bar + "' has been added successfully");
			app_logs.info("Verified '" + icon + "' Line item '" + bar + "' has been added successfully");
			app_logs.info("==========POST LINE ITEMS==========");
			testSteps.add("==========POST LINE ITEMS==========");
			folio.verifyLineItemClickIcon(driver, iconSource, bar, houseAmountWithTax, bar, true,
					Utility.LineItemIconSource.get(5));
			testSteps.add("Click '" + icon + "' Line Item Icon");
			app_logs.info("Click '" + icon + "' Line Item Icon");
			iconSource = Utility.LineItemIconSource.get(5);
			icon = Utility.LineItemIconSource.get(4);
			folio.verifyAddedLineItem(driver, iconSource, Utility.getCurrentDate("E MMM dd, yyyy"), bar,
					houseAmountWithTax, bar);
			testSteps.add("Successfully Verified '" + Utility.LineItemIconSource.get(2) + "' Line Item has been '"
					+ icon + "'");
			app_logs.info("Successfully Verified '" + Utility.LineItemIconSource.get(2) + "' Line Item has been '"
					+ icon + "'");
			app_logs.info("==========ADD LINE ITEMS==========");
			testSteps.add("==========ADD LINE ITEMS==========");
			getTestSteps.clear();
			getTestSteps = folio.addLineItemAccount(driver, spa, spaAmount, getTestSteps, property, 1);
			testSteps.addAll(getTestSteps);
			spaAmountWithTax = reservation.getLineItemAmount(driver, spa, true);
			iconSource = Utility.LineItemIconSource.get(3);
			icon = Utility.LineItemIconSource.get(2);
			folio.verifyAddedLineItem(driver, iconSource, Utility.getCurrentDate("E MMM dd, yyyy"), spa,
					spaAmountWithTax, spa);
			testSteps.add("Verified '" + icon + "' Line item '" + spa + "' has been added successfully");
			app_logs.info("Verified '" + icon + "' Line item '" + spa + "' has been added successfully");
			account.saveAfterAccount(driver, houseAccountName, getTestSteps);

			beginningBalanceAmount = account.getBalance(driver, beginningBalanceLabel);
			testSteps.add(beginningBalanceLabel + " " + beginningBalanceAmount);
			app_logs.info(beginningBalanceLabel + " " + beginningBalanceAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(beginningBalanceAmount), "0.00",
					"Failed: Beginning Balance amount mismatched");

			paymentsAmount = account.getBalance(driver, paymentsLabel);
			testSteps.add(paymentsLabel + " " + paymentsAmount);
			app_logs.info(paymentsLabel + " " + paymentsAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(paymentsAmount), "0.00",
					"Failed: Payment amount mismatched");

			newChargesAmount = account.getBalance(driver, newChargesLabel);
			testSteps.add(newChargesLabel + " " + newChargesAmount);
			app_logs.info(newChargesLabel + " " + newChargesAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(newChargesAmount),
					String.format("%.2f", (Float.parseFloat(houseAmount) + Float.parseFloat(spaAmount))),
					"Failed: New Charges  amount mismatched");

			taxAndServiceChargesAmount = account.getBalance(driver, taxAndServiceChargesLabel);
			testSteps.add(taxAndServiceChargesLabel + " " + taxAndServiceChargesAmount);
			app_logs.info(taxAndServiceChargesLabel + " " + taxAndServiceChargesAmount);

			endingBalanceAmount = account.getBalance(driver, endingBalanceLabel);
			testSteps.add(endingBalanceLabel + " " + endingBalanceAmount);
			app_logs.info(endingBalanceLabel + " " + endingBalanceAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(endingBalanceAmount), String.format("%.2f",
					(Float.parseFloat(Utility.removeDollarBracketsAndSpaces(newChargesAmount))
							+ Float.parseFloat(Utility.removeDollarBracketsAndSpaces(taxAndServiceChargesAmount)))),
					"Failed: Ending Balance  amount mismatched");
			account.close_Account(driver);
			testSteps.add("Close Account");
			app_logs.info("Close Account");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to add Line Item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to add Line Item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verify account
		try {
			app_logs.info("==========SEARCH '" + houseAccount.toUpperCase() + "' ACCOUNT==========");
			testSteps.add("==========SEARCH '" + houseAccount.toUpperCase() + "' ACCOUNT==========");
			getTestSteps.clear();
			getTestSteps = account.searchAccount(driver, test, houseAccount, houseAccountName, houseAccountNumber,
					status, getTestSteps);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify House Account", testName, "VerifyHouseAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify House Account", testName, "VerifyHouseAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Travel Agent New account
		try {
			app_logs.info("==========CREATE '" + travelAgentAccount.toUpperCase() + "' ACCOUNT==========");
			testSteps.add("==========CREATE '" + travelAgentAccount.toUpperCase() + "' ACCOUNT==========");
			account.NewAccountbutton(driver, travelAgentAccount);
			testSteps.add("Click New Account Button");
			app_logs.info("Click New Account Button");
			account.AccountDetails(driver, travelAgentAccount, travelAgentAccountName);
			testSteps.add("Select Account Type : " + travelAgentAccount);
			app_logs.info("Select Account Type : " + travelAgentAccount);
			testSteps.add("Enter Account Name : " + travelAgentAccountName);
			app_logs.info("Enter Account Name : " + travelAgentAccountName);
			String number = Utility.GenerateRandomString15Digit();
			account.enterAccountNumber(driver, number);
			testSteps.add("Change Account Number : " + number);
			app_logs.info("Change Account Number : " + number);
			getTestSteps.clear();
			getTestSteps = account.AccountAttributes(driver, test, marketSegment, referral, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = account.Mailinginfo(driver, test, accountFirstName, travelAgentAccountLastName, phonenumber,
					alternativeNumber, addressLineOne, addressLineTwo, addressLineThree, email, city, state, postalCode,
					getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = account.Billinginfo(driver, test, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = account.selectPropertyFromAccount(driver, property);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = account.AccountSave(driver, test, travelAgentAccountName, getTestSteps);
			testSteps.addAll(getTestSteps);
			travelAgentAccountNumber = account.getAccountNum(driver);
			testSteps.add("Travel Agent Account Number '" + travelAgentAccountNumber + "'");
			app_logs.info("Travel Agent Account Number '" + travelAgentAccountNumber + "'");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Create New Travel Agent Account", testName, "NewTravelAgentAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Create New Travel Agent Account", testName, "NewTravelAgentAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			app_logs.info("==========ADD LINE ITEMS==========");
			testSteps.add("==========ADD LINE ITEMS==========");
			account.navigateFolio(driver);
			app_logs.info("Navigate Folio");
			testSteps.add("Navigate Folio");
			getTestSteps.clear();
			getTestSteps = folio.addLineItemAccount(driver, bar, travelAgentAmount, getTestSteps, property, 1);
			testSteps.addAll(getTestSteps);
			account.saveAfterAccount(driver, travelAgentAccountName, getTestSteps);
			travelAgentAmountWithTax = reservation.getLineItemAmount(driver, bar, true);
			iconSource = Utility.LineItemIconSource.get(3);
			icon = Utility.LineItemIconSource.get(2);
			folio.verifyAddedLineItem(driver, iconSource, Utility.getCurrentDate("E MMM dd, yyyy"), bar,
					travelAgentAmountWithTax, bar);
			testSteps.add("Verified '" + icon + "' Line item '" + bar + "' has been added successfully");
			app_logs.info("Verified '" + icon + "' Line item '" + bar + "' has been added successfully");
			app_logs.info("==========POST LINE ITEMS==========");
			testSteps.add("==========POST LINE ITEMS==========");
			folio.verifyLineItemClickIcon(driver, iconSource, bar, travelAgentAmountWithTax, bar, true,
					Utility.LineItemIconSource.get(5));
			testSteps.add("Click '" + icon + "' Line Item Icon");
			app_logs.info("Click '" + icon + "' Line Item Icon");
			iconSource = Utility.LineItemIconSource.get(5);
			icon = Utility.LineItemIconSource.get(4);
			folio.verifyAddedLineItem(driver, iconSource, Utility.getCurrentDate("E MMM dd, yyyy"), bar,
					travelAgentAmountWithTax, bar);
			testSteps.add("Successfully Verified '" + Utility.LineItemIconSource.get(2) + "' Line Item has been '"
					+ icon + "'");
			app_logs.info("Successfully Verified '" + Utility.LineItemIconSource.get(2) + "' Line Item has been '"
					+ icon + "'");
			app_logs.info("==========ADD LINE ITEMS==========");
			testSteps.add("==========ADD LINE ITEMS==========");
			getTestSteps.clear();
			getTestSteps = folio.addLineItemAccount(driver, spa, spaAmount, getTestSteps, property, 1);
			testSteps.addAll(getTestSteps);
			spaAmountWithTax = reservation.getLineItemAmount(driver, spa, true);
			iconSource = Utility.LineItemIconSource.get(3);
			icon = Utility.LineItemIconSource.get(2);
			folio.verifyAddedLineItem(driver, iconSource, Utility.getCurrentDate("E MMM dd, yyyy"), spa,
					spaAmountWithTax, spa);
			testSteps.add("Verified '" + icon + "' Line item '" + spa + "' has been added successfully");
			app_logs.info("Verified '" + icon + "' Line item '" + spa + "' has been added successfully");
			account.saveAfterAccount(driver, travelAgentAccountName, getTestSteps);

			beginningBalanceAmount = account.getBalance(driver, beginningBalanceLabel);
			testSteps.add(beginningBalanceLabel + " " + beginningBalanceAmount);
			app_logs.info(beginningBalanceLabel + " " + beginningBalanceAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(beginningBalanceAmount), "0.00",
					"Failed: Beginning Balance amount mismatched");

			revenuesAmount = account.getBalance(driver, revenuesLabel);
			testSteps.add(revenuesLabel + " " + revenuesAmount);
			app_logs.info(revenuesLabel + " " + revenuesAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(revenuesAmount), "0.00",
					"Failed: Revenues amount mismatched");

			expensesAmount = account.getBalance(driver, expensesLabel);
			testSteps.add(expensesLabel + " " + expensesAmount);
			app_logs.info(expensesLabel + " " + expensesAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(expensesAmount),
					String.format("%.2f", (Float.parseFloat(travelAgentAmount) + Float.parseFloat(spaAmount))),
					"Failed: Expenses  amount mismatched");

			taxAndServiceChargesAmount = account.getBalance(driver, taxAndServiceChargesLabel);
			testSteps.add(taxAndServiceChargesLabel + " " + taxAndServiceChargesAmount);
			app_logs.info(taxAndServiceChargesLabel + " " + taxAndServiceChargesAmount);

			netForPeriodAmount = account.getBalance(driver, netForPeriodLabel);
			testSteps.add(netForPeriodLabel + " " + netForPeriodAmount);
			app_logs.info(netForPeriodLabel + " " + netForPeriodAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(netForPeriodAmount), String.format("%.2f",
					(Float.parseFloat(Utility.removeDollarBracketsAndSpaces(expensesAmount))
							- Float.parseFloat(Utility.removeDollarBracketsAndSpaces(taxAndServiceChargesAmount)))),
					"Failed: Net For Period  amount mismatched");

			distributionAmount = account.getBalance(driver, distributionLabel);
			testSteps.add(distributionLabel + " " + distributionAmount);
			app_logs.info(distributionLabel + " " + distributionAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(distributionAmount), "0.00",
					"Failed: Distribution amount mismatched");

			endingBalanceAmount = account.getBalance(driver, endingBalanceLabel);
			testSteps.add(endingBalanceLabel + " " + endingBalanceAmount);
			app_logs.info(endingBalanceLabel + " " + endingBalanceAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(endingBalanceAmount), String.format("%.2f",
					(Float.parseFloat(Utility.removeDollarBracketsAndSpaces(expensesAmount))
							- Float.parseFloat(Utility.removeDollarBracketsAndSpaces(taxAndServiceChargesAmount)))),
					"Failed: Ending Balance  amount mismatched");

			account.close_Account(driver);
			testSteps.add("Close Account");
			app_logs.info("Close Account");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to add Line Item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to add Line Item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verify account
		try {
			app_logs.info("==========SEARCH '" + travelAgentAccount.toUpperCase() + "' ACCOUNT==========");
			testSteps.add("==========SEARCH '" + travelAgentAccount.toUpperCase() + "' ACCOUNT==========");
			getTestSteps.clear();
			getTestSteps = account.searchAccount(driver, test, travelAgentAccount, travelAgentAccountName,
					travelAgentAccountNumber, status, getTestSteps);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify Travel Agent Account", testName, "VerifyTravelAgentAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify Travel Agent Account", testName, "VerifyTravelAgentAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Unit Owners New account
		try {
			app_logs.info("==========CREATE '" + unitOwnersAccount.toUpperCase() + "' ACCOUNT==========");
			testSteps.add("==========CREATE '" + unitOwnersAccount.toUpperCase() + "' ACCOUNT==========");
			account.ClickNewAccountbutton(driver, unitOwnersAccount);
			testSteps.add("Click New Account Button");
			app_logs.info("Click New Account Button");
			account.AccountDetails(driver, unitOwnersAccount, unitOwnersAccountName);
			testSteps.add("Select Account Type : " + unitOwnersAccount);
			app_logs.info("Select Account Type : " + unitOwnersAccount);
			testSteps.add("Enter Account Name : " + unitOwnersAccountName);
			app_logs.info("Enter Account Name : " + unitOwnersAccountName);
			String number = Utility.GenerateRandomString15Digit();
			account.enterAccountNumber(driver, number);
			testSteps.add("Change Account Number : " + number);
			app_logs.info("Change Account Number : " + number);
			getTestSteps.clear();
			getTestSteps = account.AccountAttributes(driver, test, marketSegment, referral, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = account.Mailinginfo(driver, test, accountFirstName, unitOwnersAccountLastName, phonenumber,
					alternativeNumber, addressLineOne, addressLineTwo, addressLineThree, email, city, state, postalCode,
					getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = account.Billinginfo(driver, test, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();

			getTestSteps.clear();
			getTestSteps = account.selectPropertyFromAccount(driver, property);
			testSteps.addAll(getTestSteps);

			getTestSteps = account.AccountSave(driver, test, unitOwnersAccountName, getTestSteps);
			testSteps.addAll(getTestSteps);
			unitOwnersAccountNumber = account.getAccountNum(driver);
			testSteps.add("Unit Owners Account Number '" + unitOwnersAccountNumber + "'");
			app_logs.info("Unite Owners Account Number '" + unitOwnersAccountNumber + "'");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Create New Unit Owners Account", testName, "NewUnitOwnersAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Create New Unit Owners Account", testName, "NewUnitOwnersAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			app_logs.info("==========ADD LINE ITEMS==========");
			testSteps.add("==========ADD LINE ITEMS==========");
			account.navigateFolio(driver);
			app_logs.info("Navigate Folio");
			testSteps.add("Navigate Folio");
			getTestSteps.clear();
			getTestSteps = folio.addLineItemAccount(driver, bar, unitOwnersAmount, getTestSteps, property, 1);
			testSteps.addAll(getTestSteps);
			account.saveAfterAccount(driver, unitOwnersAccountName, getTestSteps);
			unitOwnersAmountWithTax = reservation.getLineItemAmount(driver, bar, true);
			iconSource = Utility.LineItemIconSource.get(3);
			icon = Utility.LineItemIconSource.get(2);
			folio.verifyAddedLineItem(driver, iconSource, Utility.getCurrentDate("E MMM dd, yyyy"), bar,
					unitOwnersAmountWithTax, bar);
			testSteps.add("Verified '" + icon + "' Line item '" + bar + "' has been added successfully");
			app_logs.info("Verified '" + icon + "' Line item '" + bar + "' has been added successfully");
			app_logs.info("==========POST LINE ITEMS==========");
			testSteps.add("==========POST LINE ITEMS==========");
			folio.verifyLineItemClickIcon(driver, iconSource, bar, unitOwnersAmountWithTax, bar, true,
					Utility.LineItemIconSource.get(5));
			testSteps.add("Click '" + icon + "' Line Item Icon");
			app_logs.info("Click '" + icon + "' Line Item Icon");
			iconSource = Utility.LineItemIconSource.get(5);
			icon = Utility.LineItemIconSource.get(4);
			folio.verifyAddedLineItem(driver, iconSource, Utility.getCurrentDate("E MMM dd, yyyy"), bar,
					unitOwnersAmountWithTax, bar);
			testSteps.add("Successfully Verified '" + Utility.LineItemIconSource.get(2) + "' Line Item has been '"
					+ icon + "'");
			app_logs.info("Successfully Verified '" + Utility.LineItemIconSource.get(2) + "' Line Item has been '"
					+ icon + "'");
			app_logs.info("==========ADD LINE ITEMS==========");
			testSteps.add("==========ADD LINE ITEMS==========");
			getTestSteps.clear();
			getTestSteps = folio.addLineItemAccount(driver, spa, spaAmount, getTestSteps, property, 1);
			testSteps.addAll(getTestSteps);
			spaAmountWithTax = reservation.getLineItemAmount(driver, spa, true);
			iconSource = Utility.LineItemIconSource.get(3);
			icon = Utility.LineItemIconSource.get(2);
			folio.verifyAddedLineItem(driver, iconSource, Utility.getCurrentDate("E MMM dd, yyyy"), spa,
					spaAmountWithTax, spa);
			testSteps.add("Verified '" + icon + "' Line item '" + spa + "' has been added successfully");
			app_logs.info("Verified '" + icon + "' Line item '" + spa + "' has been added successfully");
			account.saveAfterAccount(driver, unitOwnersAccountName, getTestSteps);

			beginningBalanceAmount = account.getBalance(driver, beginningBalanceLabel);
			testSteps.add(beginningBalanceLabel + " " + beginningBalanceAmount);
			app_logs.info(beginningBalanceLabel + " " + beginningBalanceAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(beginningBalanceAmount), "0.00",
					"Failed: Beginning Balance amount mismatched");

			revenuesAmount = account.getBalance(driver, revenuesLabel);
			testSteps.add(revenuesLabel + " " + revenuesAmount);
			app_logs.info(revenuesLabel + " " + revenuesAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(revenuesAmount), "0.00",
					"Failed: Revenues amount mismatched");

			expensesAmount = account.getBalance(driver, expensesLabel);
			testSteps.add(expensesLabel + " " + expensesAmount);
			app_logs.info(expensesLabel + " " + expensesAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(expensesAmount),
					String.format("%.2f", (Float.parseFloat(unitOwnersAmount) + Float.parseFloat(spaAmount))),
					"Failed: Expenses  amount mismatched");

			taxAndServiceChargesAmount = account.getBalance(driver, taxAndServiceChargesLabel);
			testSteps.add(taxAndServiceChargesLabel + " " + taxAndServiceChargesAmount);
			app_logs.info(taxAndServiceChargesLabel + " " + taxAndServiceChargesAmount);

			netForPeriodAmount = account.getBalance(driver, netForPeriodLabel);
			testSteps.add(netForPeriodLabel + " " + netForPeriodAmount);
			app_logs.info(netForPeriodLabel + " " + netForPeriodAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(netForPeriodAmount), String.format("%.2f",
					(Float.parseFloat(Utility.removeDollarBracketsAndSpaces(expensesAmount))
							+ Float.parseFloat(Utility.removeDollarBracketsAndSpaces(taxAndServiceChargesAmount)))),
					"Failed: Net For Period  amount mismatched");

			distributionAmount = account.getBalance(driver, distributionLabel);
			testSteps.add(distributionLabel + " " + distributionAmount);
			app_logs.info(distributionLabel + " " + distributionAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(distributionAmount), "0.00",
					"Failed: Distribution amount mismatched");

			endingBalanceAmount = account.getBalance(driver, endingBalanceLabel);
			testSteps.add(endingBalanceLabel + " " + endingBalanceAmount);
			app_logs.info(endingBalanceLabel + " " + endingBalanceAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(endingBalanceAmount), String.format("%.2f",
					(Float.parseFloat(Utility.removeDollarBracketsAndSpaces(expensesAmount))
							+ Float.parseFloat(Utility.removeDollarBracketsAndSpaces(taxAndServiceChargesAmount)))),
					"Failed: Ending Balance  amount mismatched");

			account.close_Account(driver);
			testSteps.add("Close Account");
			app_logs.info("Close Account");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to add Line Item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to add Line Item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Gift Certificate New account
		try {
			app_logs.info("==========CREATE '" + giftCertificateAccount.toUpperCase() + "' ACCOUNT==========");
			testSteps.add("==========CREATE '" + giftCertificateAccount.toUpperCase() + "' ACCOUNT==========");
			account.ClickNewAccountbutton(driver, giftCertificateAccount);
			testSteps.add("Click New Account Button");
			app_logs.info("Click New Account Button");
			account.AccountDetails(driver, giftCertificateAccount, giftCertificateAccountName);
			testSteps.add("Select Account Type : " + giftCertificateAccount);
			app_logs.info("Select Account Type : " + giftCertificateAccount);
			testSteps.add("Enter Account Name : " + giftCertificateAccountName);
			app_logs.info("Enter Account Name : " + giftCertificateAccountName);
			String number = Utility.GenerateRandomString15Digit();
			account.enterAccountNumber(driver, number);
			testSteps.add("Change Account Number : " + number);
			app_logs.info("Change Account Number : " + number);

			getTestSteps = account.AccountSave(driver, test, giftCertificateAccountName, getTestSteps);
			testSteps.addAll(getTestSteps);
			giftCertificateAccountNumber = account.getAccountNum(driver);
			testSteps.add("Gift Certificate Account Number '" + giftCertificateAccountNumber + "'");
			app_logs.info("Gift Certificate Account Number '" + giftCertificateAccountNumber + "'");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Create New Gift Certificate Account", testName,
						"NewGiftCertificateAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Create New Gift Certificate Account", testName,
						"NewGiftCertificateAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			app_logs.info("==========ADD LINE ITEMS==========");
			testSteps.add("==========ADD LINE ITEMS==========");
			account.navigateFolio(driver);
			app_logs.info("Navigate Folio");
			testSteps.add("Navigate Folio");
			getTestSteps.clear();
			getTestSteps = folio.addLineItemAccount(driver, giftCertificateAccount, giftCertificateAmount, getTestSteps,
					property, 1);
			testSteps.addAll(getTestSteps);
			account.saveAfterAccount(driver, giftCertificateAccountName, getTestSteps);

			iconSource = Utility.LineItemIconSource.get(3);
			icon = Utility.LineItemIconSource.get(2);
			giftCertificateAmountWithTax = folio.getLineItemAmount(driver, giftCertificateAccount, iconSource, true);

			folio.verifyAddedLineItem(driver, iconSource, Utility.getCurrentDate("E MMM dd, yyyy"),
					giftCertificateAccount, giftCertificateAmountWithTax, giftCertificateAccount);
			testSteps.add(
					"Verified '" + icon + "' Line item '" + giftCertificateAccount + "' has been added successfully");
			app_logs.info(
					"Verified '" + icon + "' Line item '" + giftCertificateAccount + "' has been added successfully");
			app_logs.info("==========POST LINE ITEMS==========");
			testSteps.add("==========POST LINE ITEMS==========");
			folio.verifyLineItemClickIcon(driver, iconSource, giftCertificateAccount, giftCertificateAmountWithTax,
					giftCertificateAccount, true, Utility.LineItemIconSource.get(5));
			testSteps.add("Click '" + icon + "' Line Item Icon");
			app_logs.info("Click '" + icon + "' Line Item Icon");
			iconSource = Utility.LineItemIconSource.get(5);
			icon = Utility.LineItemIconSource.get(4);
			folio.verifyAddedLineItem(driver, iconSource, Utility.getCurrentDate("E MMM dd, yyyy"),
					giftCertificateAccount, giftCertificateAmountWithTax, giftCertificateAccount);
			testSteps.add("Successfully Verified '" + Utility.LineItemIconSource.get(2) + "' Line Item has been '"
					+ icon + "'");
			app_logs.info("Successfully Verified '" + Utility.LineItemIconSource.get(2) + "' Line Item has been '"
					+ icon + "'");
			app_logs.info("==========ADD LINE ITEMS==========");
			testSteps.add("==========ADD LINE ITEMS==========");
			getTestSteps.clear();
			getTestSteps = folio.addLineItemAccount(driver, giftCertificateAccount, spaAmount, getTestSteps, property,
					1);
			testSteps.addAll(getTestSteps);
			iconSource = Utility.LineItemIconSource.get(3);
			icon = Utility.LineItemIconSource.get(2);
			spaAmountWithTax = folio.getLineItemAmount(driver, giftCertificateAccount, iconSource, true);

			folio.verifyAddedLineItem(driver, iconSource, Utility.getCurrentDate("E MMM dd, yyyy"),
					giftCertificateAccount, spaAmountWithTax, giftCertificateAccount);
			testSteps.add(
					"Verified '" + icon + "' Line item '" + giftCertificateAccount + "' has been added successfully");
			app_logs.info(
					"Verified '" + icon + "' Line item '" + giftCertificateAccount + "' has been added successfully");
			account.saveAfterAccount(driver, giftCertificateAccountName, getTestSteps);

			amountSoldAmount = account.getBalance(driver, amountSoldLabel);
			testSteps.add(amountSoldLabel + " " + amountSoldAmount);
			app_logs.info(amountSoldLabel + " " + amountSoldAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(amountSoldAmount),
					String.format("%.2f", (Float.parseFloat(giftCertificateAmount) + Float.parseFloat(spaAmount))),
					"Failed: Amount Sold  amount mismatched");

			redeemedAmount = account.getBalance(driver, redeemedLabel);
			testSteps.add(redeemedLabel + " " + redeemedAmount);
			app_logs.info(redeemedLabel + " " + redeemedAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(redeemedAmount), "0.00",
					"Failed: Redeemed amount mismatched");

			balanceAmount = account.getBalance(driver, balanceLabel);
			testSteps.add(balanceLabel + " " + balanceAmount);
			app_logs.info(balanceLabel + " " + balanceAmount);
			Assert.assertEquals(Utility.removeDollarBracketsAndSpaces(balanceAmount),
					Utility.removeDollarBracketsAndSpaces(amountSoldAmount), "Failed: Balance  amount mismatched");
			account.close_Account(driver);
			testSteps.add("Close Account");
			app_logs.info("Close Account");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to add Line Item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to add Line Item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verify account
		try {
			app_logs.info("==========SEARCH '" + giftCertificateAccount.toUpperCase() + "' ACCOUNT==========");
			testSteps.add("==========SEARCH '" + giftCertificateAccount.toUpperCase() + "' ACCOUNT==========");
			getTestSteps.clear();
			getTestSteps = account.searchAccount(driver, test, giftCertificateAccount, giftCertificateAccountName,
					giftCertificateAccountNumber, status, getTestSteps);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify Gift Certificate Account", testName,
						"VerifyGiftCertificateAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to Verify Gift Certificate Account", testName,
						"VerifyGiftCertificateAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		app_logs.info("corporateAccount " + corporateAccountNumber);
		app_logs.info("houseAccount " + houseAccountNumber);
		app_logs.info("tracelAgetntAccount " + travelAgentAccountNumber);
		app_logs.info("UnitOwnersAccount " + unitOwnersAccountNumber);
		app_logs.info("GCAccount " + giftCertificateAccountNumber);
		app_logs.info("corporateAccountName " + corporateAccountName);

		// Verify City Ledger Type
		try {
			app_logs.info("==========VERIFYING ACCOUNT BALANCES 'CITY LEDGER' REPORT==========");
			testSteps.add("==========VERIFYING ACCOUNT BALANCES 'CITY LEDGER' REPORT==========");
			navigation.Reports(driver);
			testSteps.add("Navigate Reports");
			app_logs.info("Navigate Reports");
			reports.navigateAccountBalance(driver);
			testSteps.add("Navigate Account Balances");
			app_logs.info("Navigate Account Balances");
			reports.selectLedgerType(driver, ledgerType);
			testSteps.add("Select Ledger Type : " + ledgerType);
			app_logs.info("Select Ledger Type : " + ledgerType);
			getTestSteps.clear();
			getTestSteps = reports.selectAccountTypeCheckBox(driver, corporateAccount, true, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reports.selectAccountTypeCheckBox(driver, travelAgentAccount, true, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reports.selectAccountTypeCheckBox(driver, unitOwnersAccount, true, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reports.selectAccountTypeCheckBox(driver, houseAccount, true, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reports.selectAccountTypeCheckBox(driver, giftCertificateAccount, true, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reports.selectAccountTypeCheckBox(driver, groups, false, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reports.selectAccountTypeCheckBox(driver, advancedDeposit, false, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reports.selectAccountTypeCheckBox(driver, includePendingItems, false, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = reports.AccountBalanceGoButton(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to verify city ledger type", testName, "AccountBalance", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to verify city ledger type", testName, "AccountBalance", driver);
			} else {
				Assert.assertTrue(false);
			}

		}

		// Download Report
		try {
			app_logs.info("==========DOWNLOAD REPORT==========");
			testSteps.add("==========DOWNLOAD REPORT==========");
			reports.downloadReportFile(driver);
			fileName = Utility.download_status(driver);
			lines = Utility.checkPDFArray(fileName);

			System.out.println("line size : " + lines.length);
			i = 0;
			for (String line : lines) {
				Utility.app_logs.info("line : " + i + " " + line);
				i++;
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to download report", testName, "downloadReport", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to download report", testName, "DownloadReport", driver);
			} else {
				Assert.assertTrue(false);
			}

		}

		// Verify Corporate Account
		try {
			app_logs.info("==========VERIFY '" + corporateAccount.toUpperCase() + "' ACCOUNT==========");
			testSteps.add("==========VERIFY '" + corporateAccount.toUpperCase() + "' ACCOUNT==========");
			i = 0;
			String lineData = null;
			for (String line : lines) {
				if (line.contains(corporateAccountNumber)) {
					Utility.app_logs.info("line : " + i + " " + lines[i]);
					Utility.app_logs.info("line : " + (i + 1) + " " + lines[i + 1]);
					lineData = lines[i].trim() + lines[i + 1].trim();
					break;
				}
				i++;
			}
			Utility.app_logs.info(lineData);
			Assert.assertTrue(lineData.contains(corporateAccountName), "Failed: Corporate Account Name not found");
			testSteps.add("Verified  Account Name : " + corporateAccountName);
			app_logs.info("Verified  Account Name : " + corporateAccountName);
			lineData = lineData.replaceAll(corporateAccountName, "");
			Utility.app_logs.info(lineData);

			Assert.assertTrue(lineData.contains(corporateAccountNumber), "Failed: Corporate Account Number not found");
			testSteps.add("Verified Account Number : " + corporateAccountNumber);
			app_logs.info("Verified Account Number : " + corporateAccountNumber);
			lineData = lineData.replaceAll(corporateAccountNumber, "");
			Utility.app_logs.info(lineData);

			String foundAdvanceDeposit = Utility.removeDollarBracketsAndSpaces(lineData.split("-")[1]);
			Utility.app_logs.info(foundAdvanceDeposit);
			foundAdvanceDeposit = "-" + foundAdvanceDeposit;
			Utility.app_logs.info(foundAdvanceDeposit);
			Assert.assertTrue(foundAdvanceDeposit.contains("-0.00"),
					"Failed: Corporate Account Advance Deposit  not found");
			testSteps.add("Verified Advance Deposit : $ " + "-0.00");
			app_logs.info("Verified Advance Deposit : $ " + "-0.00");
			Utility.app_logs.info(lineData);

			String foundCurrentAmount = lineData.split(" ")[2];

			Utility.app_logs.info(foundCurrentAmount);
			Assert.assertTrue(foundCurrentAmount.contains(corporateAmountWithTax),
					"Failed: Corporate Account Current Amount  not found");
			testSteps.add("Verified Current Amount : " + corporateAmountWithTax);
			app_logs.info("Verified Current Amount : " + corporateAmountWithTax);
			Utility.app_logs.info(lineData);

			String foundTotalDue = lineData.split(" ")[0];
			Utility.app_logs.info(foundTotalDue);
			foundTotalDue = "-" + Utility.removeDollarBracketsAndSpaces(foundTotalDue);
			Assert.assertTrue(foundTotalDue.contains(corporateAmountWithTax),
					"Failed: Corporate Account Total Due not found");
			testSteps.add("Verified Total Due : $ " + corporateAmountWithTax);
			app_logs.info("Verified Total Due : $ " + corporateAmountWithTax);

			app_logs.info("Successfully verified Only Posted  Line Item exist in Report");
			testSteps.add("Successfully verified Only Posted  Line Item exist in Report");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to verify Corporate account", testName, "CorporateAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to verify Corporate account", testName, "CorporateAccount", driver);
			} else {
				Assert.assertTrue(false);
			}

		}

		// Verify House Account
		try {
			app_logs.info("==========VERIFY '" + houseAccount.toUpperCase() + "' ACCOUNT==========");
			testSteps.add("==========VERIFY '" + houseAccount.toUpperCase() + "' ACCOUNT==========");
			i = 0;
			String lineData = null;
			for (String line : lines) {
				if (line.contains(houseAccountNumber)) {
					Utility.app_logs.info("line : " + i + " " + lines[i]);
					Utility.app_logs.info("line : " + (i + 1) + " " + lines[i + 1]);
					lineData = lines[i].trim() + lines[i + 1].trim();
					break;
				}
				i++;
			}
			Utility.app_logs.info(lineData);
			String foundName = lineData.split(" ")[1].trim();
			Utility.app_logs.info(foundName);
			Assert.assertTrue(foundName.contains(houseAccountName), "Failed: Account Name not found");
			testSteps.add("Verified  Account Name : " + houseAccountName);
			app_logs.info("Verified  Account Name : " + houseAccountName);
			lineData = lineData.replaceAll(houseAccountName, "");
			Utility.app_logs.info(lineData);

			String foundNumber = lineData;
			Utility.app_logs.info(foundNumber);
			Assert.assertTrue(foundNumber.contains(houseAccountNumber), "Failed: Account Number not found");
			testSteps.add("Verified Account Number : " + houseAccountNumber);
			app_logs.info("Verified Account Number : " + houseAccountNumber);
			lineData = lineData.replaceAll(houseAccountNumber, "");
			Utility.app_logs.info(lineData);

			String foundTotalDue = lineData;
			foundTotalDue = Utility.removeDollarBracketsAndSpaces(foundTotalDue);

			Utility.app_logs.info(foundTotalDue);
			Assert.assertTrue(foundTotalDue.contains(houseAmountWithTax), "Failed: Total Due not found");
			testSteps.add("Verified Total Due : $ " + houseAmountWithTax);
			app_logs.info("Verified Total Due : $ " + houseAmountWithTax);

			app_logs.info("Successfully verified Only Posted  Line Item exist in Report");
			testSteps.add("Successfully verified Only Posted  Line Item exist in Report");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to verify House Account", testName, "HouseAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to verify House Account", testName, "HouseAccount", driver);
			} else {
				Assert.assertTrue(false);
			}

		}

		// Verify Travel Agent Account
		try {
			app_logs.info("==========VERIFY '" + travelAgentAccount.toUpperCase() + "' ACCOUNT==========");
			testSteps.add("==========VERIFY '" + travelAgentAccount.toUpperCase() + "' ACCOUNT==========");
			i = 0;
			String lineData = null;
			for (String line : lines) {
				if (line.contains(travelAgentAccountNumber)) {
					Utility.app_logs.info("line : " + i + " " + lines[i]);
					Utility.app_logs.info("line : " + (i + 1) + " " + lines[i + 1]);
					lineData = lines[i].trim() + lines[i + 1].trim();
					break;
				}
				i++;
			}
			Utility.app_logs.info(lineData);
			Utility.app_logs.info(lineData);
			String foundName = lineData.split(" ")[1].trim();
			Utility.app_logs.info(foundName);
			Assert.assertTrue(foundName.contains(travelAgentAccountName), "Failed: Account Name not found");
			testSteps.add("Verified  Account Name : " + travelAgentAccountName);
			app_logs.info("Verified  Account Name : " + travelAgentAccountName);
			lineData = lineData.replaceAll(travelAgentAccountName, "");
			Utility.app_logs.info(lineData);

			String foundNumber = lineData;
			Utility.app_logs.info(foundNumber);
			Assert.assertTrue(foundNumber.contains(travelAgentAccountNumber), "Failed: Account Number not found");
			testSteps.add("Verified Account Number : " + travelAgentAccountNumber);
			app_logs.info("Verified Account Number : " + travelAgentAccountNumber);
			lineData = lineData.replaceAll(travelAgentAccountNumber, "");
			Utility.app_logs.info(lineData);

			String foundTotalDue = lineData;
			foundTotalDue = Utility.removeDollarBracketsAndSpaces(foundTotalDue);
			Utility.app_logs.info(foundTotalDue);
			Assert.assertTrue(foundTotalDue.contains("-" + travelAgentAmountWithTax), "Failed: Total Due mismatched");
			testSteps.add("Verified Total Due : $ " + "-" + travelAgentAmountWithTax);
			app_logs.info("Verified Total Due : $ " + "-" + travelAgentAmountWithTax);

			app_logs.info("Successfully verified Only Posted  Line Item exist in Report");
			testSteps.add("Successfully verified Only Posted  Line Item exist in Report");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to verify Travel Agent Account", testName, "TravelAgentAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to verify Travel Agent Account", testName, "TravelAgentAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		}

		// Verify Unit Owners Account
		try {
			app_logs.info("==========VERIFY '" + unitOwnersAccount.toUpperCase() + "' ACCOUNT==========");
			testSteps.add("==========VERIFY '" + unitOwnersAccount.toUpperCase() + "' ACCOUNT==========");
			i = 0;
			String lineData = null;
			for (String line : lines) {
				if (line.contains(unitOwnersAccountNumber)) {
					Utility.app_logs.info("line : " + i + " " + lines[i]);
					Utility.app_logs.info("line : " + (i + 1) + " " + lines[i + 1]);
					lineData = lines[i].trim() + lines[i + 1].trim();
					break;
				}
				i++;
			}
			Utility.app_logs.info(lineData);
			String foundName = lineData.split(" ")[1].trim();
			Utility.app_logs.info(foundName);
			Assert.assertTrue(foundName.contains(unitOwnersAccountName), "Failed: Account Name not found");
			testSteps.add("Verified  Account Name : " + unitOwnersAccountName);
			app_logs.info("Verified  Account Name : " + unitOwnersAccountName);
			lineData = lineData.replaceAll(unitOwnersAccountName, "");
			Utility.app_logs.info(lineData);
			lineData = lineData.split(" ")[0].trim();
			String foundNumber = lineData;
			Utility.app_logs.info(foundNumber);
			Assert.assertTrue(foundNumber.contains(unitOwnersAccountNumber), "Failed: Account Number not found");
			testSteps.add("Verified Account Number : " + unitOwnersAccountNumber);
			app_logs.info("Verified Account Number : " + unitOwnersAccountNumber);
			lineData = lineData.replaceAll(unitOwnersAccountNumber, "");
			Utility.app_logs.info(lineData);

			String foundTotalDue = lineData;
			foundTotalDue = Utility.removeDollarBracketsAndSpaces(foundTotalDue);

			Utility.app_logs.info(foundTotalDue);
			Assert.assertTrue(foundTotalDue.contains("-" + unitOwnersAmountWithTax), "Failed: Total Due mismatched");
			testSteps.add("Verified Total Due : $ " + "-" + unitOwnersAmountWithTax);
			app_logs.info("Verified Total Due : $ " + "-" + unitOwnersAmountWithTax);

			app_logs.info("Successfully verified Only Posted  Line Item exist in Report");
			testSteps.add("Successfully verified Only Posted  Line Item exist in Report");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to verify Unit Owners Account", testName, "UnitOwnersAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to verify Unit Owners Account", testName, "UnitOwnersAccount", driver);
			} else {
				Assert.assertTrue(false);
			}

		}

		// Verify Gift Certificate Account
		try {
			app_logs.info("==========VERIFY '" + giftCertificateAccount.toUpperCase() + "' ACCOUNT==========");
			testSteps.add("==========VERIFY '" + giftCertificateAccount.toUpperCase() + "' ACCOUNT==========");
			i = 0;
			String lineData = null;
			for (String line : lines) {
				if (line.contains(giftCertificateAccountNumber)) {
					Utility.app_logs.info("line : " + i + " " + lines[i]);
					Utility.app_logs.info("line : " + (i + 1) + " " + lines[i + 1]);
					lineData = lines[i].trim() + lines[i + 1].trim();
					break;
				}
				i++;
			}
			Utility.app_logs.info(lineData);
			String foundName = lineData.split(" ")[1].trim();
			Utility.app_logs.info(foundName);
			Assert.assertTrue(foundName.contains(giftCertificateAccountName), "Failed: Account Name not found");
			testSteps.add("Verified  Account Name : " + giftCertificateAccountName);
			app_logs.info("Verified  Account Name : " + giftCertificateAccountName);
			lineData = lineData.replaceAll(giftCertificateAccountName, "");
			Utility.app_logs.info(lineData);

			String foundNumber = lineData;
			Utility.app_logs.info(foundNumber);
			Assert.assertTrue(foundNumber.contains(giftCertificateAccountNumber), "Failed: Account Number not found");
			testSteps.add("Verified Account Number : " + giftCertificateAccountNumber);
			app_logs.info("Verified Account Number : " + giftCertificateAccountNumber);
			lineData = lineData.replaceAll(giftCertificateAccountNumber, "");
			Utility.app_logs.info(lineData);

			String foundTotalDue = lineData;
			foundTotalDue = Utility.removeDollarBracketsAndSpaces(foundTotalDue);
			Utility.app_logs.info(foundTotalDue);
			Assert.assertTrue(foundTotalDue.contains(giftCertificateAmountWithTax), "Failed: Total Due mismatched");
			testSteps.add("Verified Total Due : $ " + giftCertificateAmountWithTax);
			app_logs.info("Verified Total Due : $ " + giftCertificateAmountWithTax);

			app_logs.info("Successfully verified Only Posted  Line Item exist in Report");
			testSteps.add("Successfully verified Only Posted  Line Item exist in Report");

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to verify Gift Certificate Account", testName, "GiftCertificateAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testNames, testDescriptions, testCategories, testSteps);
				Utility.updateReport(e, "Failed to verify Gift Certificate Account", testName, "GiftCertificateAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		}

	}

	@DataProvider
	public Object[][] getData() {

		// return test data from the sheetname provided
		return Utility.getData("VerifyAccountBalanceCityLedgerR", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		 driver.quit();

	}

}
