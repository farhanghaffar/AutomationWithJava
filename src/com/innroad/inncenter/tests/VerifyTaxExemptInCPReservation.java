package com.innroad.inncenter.tests;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.Tax;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyTaxExemptInCPReservation extends TestCore {
	private WebDriver driver;

	public static String test_description = "";
	public static String test_category = "";
	public static String test_name = "";
	public static ArrayList<String> test_steps = new ArrayList<>();
	public static ArrayList<String> getTest_Steps = new ArrayList<>();

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);

	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void verifyTaxExemptInCPReservation(String url, String ClientCode, String Username, String Password,
			String Adults, String Children, String Rateplan, String PromoCode, String RoomClass, String IsAssign,
			String IsDepositOverride, String DepositOverrideAmount, String Salutation, String GuestFirstName,
			String GuestLastName, String PhoneNumber, String AltenativePhone, String Email, String Account,
			String AccountType, String Address1, String Address2, String Address3, String City, String Country,
			String State, String PostalCode, String IsGuesProfile, String PaymentType, String CardNumber,
			String NameOnCard, String CardExpDate, String IsChangeInPayAmount, String ChangedAmountValue,
			String TravelAgent, String MarketSegment, String Referral, String IsAddNotes, String NoteType,
			String Subject, String Description, String IsTask, String TaskCategory, String TaskType, String TaskDetails,
			String TaskRemarks, String TaskDueon, String TaskAssignee, String TaskStatus, String TaxName,
			String TaxDescription, String value, String category, String TaxDisplayName, String Vat, String TaxExcempt,
			String Percent, String taxLedgerAccount, String bedsCount, String maxAdults, String maxPersopns,
			String roomQuantity, String ratename, String maxadults, String maxpersopns, String baseAmount,
			String additionalAdult, String additionalChild, String taxExcemptId) throws InterruptedException {

		test_name = "VerifyTaxExemptInCPReservation";
		test_description = "Verify Tax Exempt In CP Reservation<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682449' target='_blank'>"
				+ "Click here to open TestRail: 682449</a>";
		test_category = "TaxExemptInCPReservation";
		String testName = test_name;

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");
		CPReservationPage res = new CPReservationPage();
		Navigation nav = new Navigation();
		RoomClass roomClass = new RoomClass();
		Rate rate = new Rate();
		Tax tax = new Tax();
		String randomString = "";
		String taxName = "";
		String roomClassName = "";
		String rcDelete = "";
		String rateDelete = "";
		String rateName = "";
		String deleteTaxesName = "";
		try {
			test_steps.add("<b> ************Logging in to the Application</b>****************");
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);
			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver = getDriver();
			login_CP(driver);
			test_steps.add("Logged into the application");
			app_logs.info("Logged into the application");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			// Create Room Class
			test_steps.add("<b> ************Create Room Class</b>****************");
			// create room class
			nav.Setup(driver);
			test_steps.add("Navigate to Setup");
			nav.RoomClass(driver);
			test_steps.add("Navigate to RoomClasses tab");
			String RcNaming = RoomClass;
			rcDelete = RcNaming.replace("XXX", "");
			randomString = Utility.GenerateRandomNumber();
			nav.NewRoomClass(driver);
			roomClassName = RoomClass.replace("XXX", randomString);
			roomClass.Create_RoomClass(driver, roomClassName, roomClassName, bedsCount, maxAdults, maxPersopns,
					roomQuantity, test, test_steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			// Create Rate
			test_steps.add("<b> ************Create Rate/b>****************");
			// ceate rate
			String rateNaming = ratename;
			rateDelete = rateNaming.replace("XXX", "");
			randomString = Utility.GenerateRandomNumber();
			rateName = ratename.replace("XXX", randomString);
			nav.Inventory(driver);
			test_steps.add("Navigate to Inventory");
			nav.Rate(driver);
			test_steps.add("Navigate to Rate");

			rate.new_Rate(driver, rateName, maxadults, maxpersopns, baseAmount, additionalAdult, additionalChild,
					rateName, rateName, rateName, roomClassName);
			test_steps.add("Rate name: " + "<b>" + rateName + "</b>");
			test_steps.add("Room class name selected in the rate: " + "<b>" + roomClassName + "</b>");
			test_steps.add("Rate created");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to Create Rate", "NONGS_Login", "Rate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to Create Rate", "NONGS_Login", "Rate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			// Create Tax
			test_steps.add("<b>************** Making Tax Setup*******************</b>");
			nav.Setup(driver);
			test_steps.add("Navigate to setup");
			nav.Taxes(driver);
			test_steps.add("Navigate to taxes page");
			// create new tax------------------
			test_steps.add("<b>********** Creating New percent Tax****************</b>");
			String deleteTaxname = TaxName;
			deleteTaxesName = deleteTaxname.replace("XXX", "");
			randomString = Utility.GenerateRandomNumber();
			taxName = TaxName.replace("XXX", randomString);
			tax.Click_NewItem(driver, test_steps);
			test_steps.add("Click at new tax item button");
			test_steps.add("<br>Create new taxes</br>");
			boolean percentage = true;
			boolean excludeTaxExempts = true;
			test_steps.add("percent Tax name is: " + "<b>" + taxName + " </b>");
			if (Vat.contentEquals("Y")) {
				boolean vat = true;
				tax.createTax(driver, test, taxName, taxName, taxName, value, category, taxLedgerAccount,
						excludeTaxExempts, percentage, vat);
				test_steps.add("Click at vat checkbox");

			} else {
				boolean vat = false;
				tax.createTax(driver, test, taxName, taxName, taxName, value, category, taxLedgerAccount,
						excludeTaxExempts, percentage, vat);
			}
			test_steps.add("Tax Category: " + "<b>" + category + "</b>");
			test_steps.add("Created tax with percent amount: " + "<b>" + value + "</b>");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to create tax", "NONGS_Login", "create tax", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to create tax", "NONGS_Login", "create tax", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			test_steps.add("<b>*****************Creating CP reservation</b>***************************");
			nav.Click_ReservationMenuFromTaxPage(driver);
			test_steps.add("Click at resrevation menue link ");

			res.click_NewReservation(driver, test_steps);
			getTest_Steps.clear();
			getTest_Steps = res.checkInDate(driver, 0);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			getTest_Steps = res.checkOutDate(driver, +2);
			test_steps.addAll(getTest_Steps);
			res.enter_Adults(driver, test_steps, Adults);
			res.enter_Children(driver, test_steps, Children);
			res.select_Rateplan(driver, test_steps, Rateplan, PromoCode);
			res.clickOnFindRooms(driver, test_steps);
			res.selectRoom(driver, test_steps, roomClassName, IsAssign, Account);
			res.deposit(driver, test_steps, IsDepositOverride, DepositOverrideAmount);
			res.clickNext(driver, test_steps);
			randomString = Utility.GenerateRandomNumber();
			String gLastName = GuestLastName.replace("XXX", randomString);
			res.enter_MailingAddress(driver, test_steps, Salutation, GuestFirstName, gLastName, PhoneNumber,
					AltenativePhone, Email, Account, AccountType, Address1, Address2, Address3, City, Country, State,
					PostalCode, IsGuesProfile);
			if ((Account.equalsIgnoreCase("") || Account.isEmpty())) {
				res.enter_PaymentDetails(driver, test_steps, PaymentType, CardNumber, NameOnCard, CardExpDate);
			}
			res.createTaxExempt(driver, taxExcemptId, test_steps);
			res.enter_MarketSegmentDetails(driver, test_steps, TravelAgent, MarketSegment, Referral);

			/*
			 * * * res.verify_NotesSections(driver, test_steps); boolean
			 * falg=res.verify_TaskSections(driver, test_steps); res.enter_Notes(driver,
			 * test_steps, IsAddNotes, NoteType, Subject,Description); if(falg) {
			 * res.enter_Task(driver, test_steps, IsTask, TaskCategory, TaskType,
			 * TaskDetails, TaskRemarks, TaskDueon, TaskAssignee, TaskStatus); }
			 */
			res.clickBookNow(driver, test_steps);
			res.get_ReservationConfirmationNumber(driver, test_steps);
			res.clickCloseReservationSavePopup(driver, test_steps);
		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to create Reservation", "NONGS_Login", "Create Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to create Reservation", "NONGS_Login", "Create Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			test_steps.add(
					"<b>*****************Verify tax get exempted in reservation folio</b>***************************");
			res.click_Folio(driver, test_steps);
			boolean isTaxExist = res.isTaxPresentAfterTaxExempt(driver, test_steps, taxLedgerAccount, taxName);
			Assert.assertEquals(isTaxExist, false);
		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to Verify Tax Exempt", "NONGS_Login", "Create Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to Verify Tax Exempt", "NONGS_Login", "Create Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			// Delete created records for the script
			test_steps.add("<b>*****************Deleting created Records for Script********************</b>");
			nav.Inventory(driver);
			test_steps.add("Navigate to Inventory");
			nav.Rate(driver);
			test_steps.add("Navigate to Rate");
			rate.DeleteRate(driver, rateDelete);
			test_steps.add("Rates Deleted starting with name: " + "<b>" + rateDelete + "</b>");
			nav.Setup(driver);
			test_steps.add("Navigate to Setup");
			nav.RoomClass(driver);
			test_steps.add("Navigate to Room Classes");
			roomClass.SearchRoomClass(driver, roomClassName, test_steps);
			test_steps.add("Searched room class: " + "<b>" + roomClassName + "</b>");
			roomClass.deleteRoomClass(driver, rcDelete);
			test_steps.add("Room Classes deleted starting from name : " + "<b>" + rcDelete + "</b>");
			nav.Taxes(driver);
			test_steps.add("Navigate to Tax page");
			System.out.println("deleteTaxesName" + deleteTaxesName);
			tax.deleteTaxStartsWithSameName(driver, deleteTaxesName);

			test_steps.add("Tax deleted starting from name : " + "<b>" + deleteTaxesName + "</b>");
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(test_name, test_description, test_category, test_steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to delete room class and rate", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to delete room class and rate", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider()
	public Object[][] getData() {
		return Utility.getData("VerifyTaxExemptInCPReservation", excel);

	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();

	}
	
}