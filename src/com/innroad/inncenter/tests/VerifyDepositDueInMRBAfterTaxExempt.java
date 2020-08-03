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
import com.innroad.inncenter.pageobjects.Policies;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.Tax;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class VerifyDepositDueInMRBAfterTaxExempt extends TestCore {

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
	public void verifyDepositDueInMRBAfterTaxExempt(String url, String clientCode, String userName, String password,
			String checkInDate, String checkOutDate, String adults, String children, String ratePlan, String promoCode,
			String isSplitRes, String roomClass, String isAssign, String isDepositOverride,
			String depositOverrideAmount, String isAddMoreGuestInfo, String salutation, String guestFirstName,
			String guestLastName, String phoneNumber, String altenativePhone, String email, String account,
			String accountType, String address1, String address2, String address3, String city, String country,
			String state, String postalCode, String isGuesProfile, String paymentType, String cardNumber,
			String nameOnCard, String cardExpDate, String isChangeInPayAmount, String changedAmountValue,
			String travelAgent, String marketSegment, String referral, String isAddNotes, String noteType,
			String subject, String description, String isTask, String taskCategory, String taskType, String taskDetails,
			String taskRemarks, String taskDueon, String taskAssignee, String taskStatus, String bedsCount,
			String maxAdults, String maxPersons, String roomQuantity, String ratename, String maxadults,
			String maxpersopns, String baseAmount, String additionalAdult, String additionalChild, String taxName,
			String value, String category, String taxLedgerAccount, String excludeTaxExempts, String percentage,
			String vat, String taxExcemptId, String policyName, String policyType, String chargestype, String number,
			String policyText, String policyDesc, String season) {
		test_name = "VerifyDepositDueInMRBAfterTaxExempt";
		test_description = "Verify DepositDue In MRB Reservation After Tax Exempt<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682496' target='_blank'>"
				+ "Click here to open TestRail: 682496</a>";
		test_category = "VerifyDepositDueInMRBAfterTaxExempt";
		String testName = test_name;

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");
		CPReservationPage res = new CPReservationPage();
		Navigation nav = new Navigation();
		RoomClass roomClas = new RoomClass();
		Rate rate = new Rate();
		Tax tax = new Tax();
		String randomString = "";
		String roomClassName = "";
		String rcDelete = "";
		String rateDelete = "";
		String rateName = "";
		double totaltax = 0.0;
		boolean isdefaultTaxAvailable = false;
		// String taxName = "";
		Policies pol = new Policies();
		ArrayList<String> roomNumber = new ArrayList<String>();

		String policyNames = "";
		String tripSummaryPaid = "";
		String tripSummaryRoomCharges = "";
		String tripSummaryTaxes = "";
		String depositDue = "";
		String deleteTaxesName = "";
		int stayDays = 0;
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
			String rcNaming = roomClass;
			rcDelete = rcNaming.replace("XXX", "");
			randomString = Utility.GenerateRandomNumber();
			nav.NewRoomClass(driver);
			roomClassName = roomClass.replace("XXX", randomString);
			roomClas.Create_RoomClass(driver, roomClassName, roomClassName, bedsCount, maxAdults, maxPersons,
					roomQuantity, test, test_steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to Create Roomclass", "NONGS_Login", "RoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to Create Roomclass", "NONGS_Login", "RoomClass", driver);
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
				Utility.updateReport(e, "Failed to create rate", "NONGS_Login", "create rate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to create rate", "NONGS_Login", "create rate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			test_steps.add("******************* Creating policy **********************");
			app_logs.info("******************* Creating policy **********************");
			// nav.Inventory(driver);
			nav.policies(driver);
			pol.delete_Policies(driver, policyType);
			app_logs.info("deleted all exisitng deposit policies");
			test_steps.add("deleted all exisitng deposit policies");
			pol.ClickNewPolicybutton(driver);
			randomString = Utility.GenerateRandomNumber();
			policyNames = policyName.replace("XXX", randomString);
			pol.Enter_Policy_Name(driver, policyNames, test_steps);
			pol.Enter_Policy_Type(driver, policyType);
			pol.Deposit_Policy_Attributes(driver, chargestype, number, test_steps);
			pol.Enter_Policy_Desc(driver, policyNames, policyNames);
			pol.Associate_Sources(driver);
			pol.Associate_Seasons(driver, season);

			pol.Associate_RoomClasses(driver, roomClassName);
			app_logs.info("Associating Room Class to policy : " + roomClassName);
			test_steps.add("Associating Room Class to policy : " + roomClassName);
			String[] rates = ratePlan.split("\\|");
			String policyRatePlan = rates[0];
			pol.Associate_RatePlan(driver, policyRatePlan);
			app_logs.info("Associating Rateplan to policy : " + policyRatePlan);
			test_steps.add("Associating Rateplan to policy : " + policyRatePlan);
			pol.Save_Policy(driver);
			pol.Close_Policy_Tab(driver);
			test_steps.add("Created Policy : " + policyNames);
			app_logs.info("Created Policy : " + policyNames);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to create policy", "NONGS_Login", "Policy", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to create policy", "NONGS_Login", "Policy", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			test_steps.add("<b>************** Making Tax Setup*******************</b>");
			nav.Setup(driver);
			test_steps.add("Navigate to setup");
			nav.Taxes(driver);
			test_steps.add("Navigate to taxes page");
			test_steps.add(
					"<b>**************Removing Ledger Account as Room Charge from existing Taxes except default tax*******************</b>");
			// Remove room charge for existing taxes
			tax.removeLedgerAccInExistingTaxes(driver, taxLedgerAccount, test_steps);
			isdefaultTaxAvailable = tax.isExistingDefaultTaxAvailable(driver, taxLedgerAccount);
			if (isdefaultTaxAvailable) {
				tax.openTax(driver, "Sales Tax @ 6%");
				test_steps.add("Open tax: " + "<b>Sales Tax @ 6%</b>");
				tax.checkExcludeTaxExempt(driver, test, test_steps);
				test_steps.add("Exclude tax exempt checkbox checked for default Tax: " + "<b>Sales Tax @ 6%</b>");
				tax.clickDoneOnTaxPage(driver);
				test_steps.add("Click at done button");
			}

			// Create Tax

			// tax.getTaxItemName(driver);
			// create new tax------------------
			test_steps.add("<b>********** Creating New percent Tax****************</b>");
			String deleteTaxname = taxName;
			deleteTaxesName = deleteTaxname.replace("XXX", "").trim();
			randomString = Utility.GenerateRandomNumber();
			taxName = taxName.replace("XXX", randomString);
			tax.Click_NewItem(driver, test_steps);
			test_steps.add("Click at new tax item button");
			test_steps.add("<br>Create new taxes</br>");
			boolean Percentage = true;
			boolean excludeTaxExempt = true;
			boolean Vat = false;
			test_steps.add("percent Tax name is: " + "<b>" + taxName + " </b>");

			tax.createTax(driver, test, taxName, taxName, taxName, value, category, taxLedgerAccount, excludeTaxExempt,
					Percentage, Vat);

			test_steps.add("Click at vat checkbox");
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
			// create reservation
			test_steps.add("<b>*****************Creating MRB reservation</b>***************************");
			nav.Click_ReservationMenuFromTaxPage(driver);
			test_steps.add("Click at resrevation menu link ");
			res.click_NewReservation(driver, test_steps);
			res.select_Dates(driver, test_steps, checkInDate, checkOutDate, adults, children, ratePlan, promoCode,
					isSplitRes);
			if (isSplitRes.equalsIgnoreCase("Yes")) {
				res.enter_Adults(driver, test_steps, adults);
				res.enter_Children(driver, test_steps, children);
				res.select_Rateplan(driver, test_steps, ratePlan, promoCode);
			}
			res.click_FindRooms(driver, test_steps);
			res.selectRoom(driver, test_steps, roomClassName, Utility.RoomNo, "");
			res.selectRoom(driver, test_steps, roomClassName, Utility.RoomNo, "");

			// depositAmount = res.deposit(driver, test_steps, isDepositOverride,
			// depositOverrideAmount);
			res.clickNext(driver, test_steps);
			randomString = Utility.GenerateRandomNumber();
			String gLastName = guestLastName.replace("XXX", randomString);
			res.enter_MRB_MailingAddress(driver, test_steps, salutation, guestFirstName, gLastName, phoneNumber,
					altenativePhone, email, account, accountType, address1, address2, address3, city, country, state,
					postalCode, isGuesProfile, isAddMoreGuestInfo, isSplitRes, roomNumber);
			if ((account.equalsIgnoreCase("") || account.isEmpty())) {
				res.enter_PaymentDetails(driver, test_steps, paymentType, cardNumber, nameOnCard, cardExpDate);
			}
			// System.out.println(Rooms);
			res.createTaxExempt(driver, taxExcemptId, test_steps);

			test_steps.add("deposit due captured in Reservation before save is: "+ "<b>"+depositDue+"</b>");
			res.enter_MarketSegmentDetails(driver, test_steps, travelAgent, marketSegment, referral);
			depositDue = res.getDepositDueAmount(driver);
			res.clickBookNow(driver, test_steps);

			res.get_ReservationConfirmationNumber(driver, test_steps);
			res.get_ReservationStatus(driver, test_steps);
			res.clickCloseReservationSavePopup(driver, test_steps);
			Wait.wait3Second();
			tripSummaryRoomCharges = res.get_TripSummaryRoomChargesWithCurrency(driver, test_steps);
			tripSummaryTaxes = res.get_TripSummaryTaxesWithCurrency(driver, test_steps);
			// tripSummaryTripTotal=res.get_TripSummaryTripTotalChargesWithCurrency(driver,
			// test_steps);
			tripSummaryPaid = res.get_TripSummaryPaidWithCurrency(driver, test_steps);
			res.get_AssociatedPoliciesToReservation(driver, test_steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to create reservation", "NONGS_Login", "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to create reservation", "NONGS_Login", "Resevation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			// calculate tax
			String[] checkin = checkInDate.split("\\|");
			String[] checkout = checkOutDate.split("\\|");
			String checkinDay = checkin[0];
			String checkOutDay = checkout[0];

			stayDays = Utility.getStayDays(checkinDay, checkOutDay);
			// Verify DepositDue calculation and assertion
			test_steps.add("<b>*****************Verifying DepositDue Amount</b>***************************");
			String calculatedDepositDue = res.getPercentcalcvalueSingleItem(driver, number,
					tripSummaryRoomCharges.replace("$", "").trim(), stayDays);
			double calcDeposit = Double.parseDouble(calculatedDepositDue) * stayDays;
			Assert.assertEquals(Double.parseDouble(depositDue), calcDeposit);
			test_steps.add("Verified DepositDue Amount over calculated: " + "</b>" + calcDeposit + "</b>");

			test_steps.add("<b>*****************Verifying Paid Amount</b>***************************");
			String paidAmount = res.get_TripSummaryPaid(driver, test_steps);
			Assert.assertEquals(Double.parseDouble(paidAmount), calcDeposit);
			test_steps.add("Verified paid Amount over calculated: " + "</b>" + calcDeposit + "</b>");

			test_steps.add("<b>*****************Verify Total Tax in TripSummary</b>***************************");
			Assert.assertEquals(Double.parseDouble(tripSummaryTaxes.replace("$", "").trim()), totaltax);
			test_steps.add("Verified Total tax Amount over calculated: " + "</b>" + totaltax + "</b>");

			test_steps.add(
					"<b>*****************Verify tax get exempted in reservation folio</b>***************************");
			res.click_Folio(driver, test_steps);
			int guestFolioItem = res.getTotalGuestFolioItem(driver, test_steps);
			for (int i = 1; i <= guestFolioItem; i++) {
				res.click_FolioDetail_DropDownBox(driver, test_steps);
				res.selectGuestFolioItemWithIndexInRes(driver, i);
				test_steps.add("Selected option from Guest detail dropdown: " + "<b>" + i + "</b>");
				Wait.wait3Second();
				boolean isTaxExist = res.isTaxPresentAfterTaxExempt(driver, test_steps, taxLedgerAccount, taxName);
				Assert.assertEquals(isTaxExist, false);
			}
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to make deposit due/tax assertion", "NONGS_Login", "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to make deposit due/tax assertion", "NONGS_Login", "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			//Delete created records for the script
			test_steps.add(
					"<b>*****************Deleting created Records for Script********************</b>");
			nav.Inventory(driver);
			test_steps.add("Navigate to Inventory");
			nav.Rate(driver);
			test_steps.add("Navigate to Rate");
			rate.deleteRates(driver, rateDelete);
			test_steps.add("Rates Deleted starting with name: "+ "<b>"+rateDelete+"</b>");
			nav.Setup(driver);
			test_steps.add("Navigate to Setup");
			nav.RoomClass(driver);
			test_steps.add("Navigate to Room Classes");
			roomClas.SearchRoomClass(driver, roomClassName, test_steps);
			test_steps.add("Searched room class: "+ "<b>"+roomClassName+"</b>");
			//roomClas.Delete_RoomClass(driver, rcDelete);
			roomClas.deleteRoomClass(driver, rcDelete);
			test_steps.add("Room Classes deleted starting from name : "+ "<b>"+rcDelete+"</b>");
			nav.Taxes(driver);
			test_steps.add("Navigate to Tax page");
			tax.deleteTaxStartsWithSameName(driver, deleteTaxesName);
			test_steps.add("Room Classes deleted starting from name : "+ "<b>"+deleteTaxesName+"</b>");
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(test_name, test_description, test_category, test_steps);
		} catch(Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to delete room class and rate", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch(Error e) {
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
		return Utility.getData("VerifyDepositDueInMRBAfterTaxEx", excel);

	}
	@AfterClass(alwaysRun=true)
	public void closeDriver() {
		driver.quit();
	}
}
