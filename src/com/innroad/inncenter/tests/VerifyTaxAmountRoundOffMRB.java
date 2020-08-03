package com.innroad.inncenter.tests;

import java.text.DecimalFormat;
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
import com.innroad.inncenter.waits.Wait;

public class VerifyTaxAmountRoundOffMRB extends TestCore {
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
	public void verifyTaxAmountRoundOffMRB(String url, String clientCode, String userName, String password,
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
			String maxpersopns, String baseAmount, String additionalAdult, String additionalChild, String TaxName,
			String value, String category, String taxLedgerAccount, String excludeTaxExempts, String percentage,
			String vat) {
		test_name = "VerifyTaxAmountRoundOffMRB";
		test_description = "Verify Tax amount roundOff<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682498' target='_blank'>"
				+ "Click here to open TestRail: 682498</a>";
		test_category = "TaxAmountRoundOff";
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
		String taxName = "";
		String roomClassName = "";
		String rcDelete = "";
		String rateDelete = "";
		String rateName = "";

		String totalTaxChargesbeforesave = "";
		String totalTripChargesbeforesave = "";
		String totalRoomChargesBeforeResSave = "";
		double depositAmount = 0.0;
		double tripsummarytaxesafterressave = 0.0;
		ArrayList<String> roomNumber = new ArrayList<String>();
		boolean isdefaultTaxAvailable = false;
		boolean isRcLedgerAssociated = false;
		String defaulttaxValue = "";
		double totalRoomCharges = 0.0;
		String roomCharge = "";
		String deleteTaxesName = "";
		double defaultTaxCalculatedAmountPerDay = 0.0;
		int stayDays = 0;
		ArrayList<String> createdTaxValue = new ArrayList<String>();
		ArrayList<String> createdTaxDescriptions = new ArrayList<String>();
		DecimalFormat f = new DecimalFormat("##.00");
		try {
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
				test_steps.add("Open tax: " + "Sales Tax @ 6%");
				defaulttaxValue = tax.getTaxVal(driver, "Sales Tax @ 6%");
				test_steps.add("Existing default tax value: " + "<b>" + defaulttaxValue + "</b>");
				boolean ispercent = tax.checkIfPercentSelected(driver, test_steps);
				if (!ispercent) {
					tax.selectPercentCheckbox(driver, test_steps);
					test_steps.add("Select percent checkbox for tax: " + "Sales Tax @ 6%");
					tax.clickDoneOnTaxPage(driver);
					test_steps.add("Click at done button");
				}
				tax.clickDoneOnTaxPage(driver);
				test_steps.add("Click at done button");
			}

			// Create Tax

			// tax.getTaxItemName(driver);
			// create new tax------------------
			test_steps.add("<b>********** Creating New percent Tax****************</b>");
			String deleteTaxname = TaxName;
			deleteTaxesName = deleteTaxname.replace("XXX", "").trim();
			randomString = Utility.GenerateRandomNumber();
			taxName = TaxName.replace("XXX", randomString);
			tax.Click_NewItem(driver, test_steps);
			test_steps.add("Click at new tax item button");
			test_steps.add("<br>Create new taxes</br>");
			boolean Percentage = true;
			boolean excludeTaxExempt = true;
			boolean Vat = false;
			test_steps.add("percent Tax name is: " + "<b>" + taxName + " </b>");

			tax.createTax(driver, test, taxName, taxName, taxName, value, category, taxLedgerAccount, excludeTaxExempt,
					Percentage, Vat);
			createdTaxDescriptions.add(taxName);
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
			res.clickOnFindRooms(driver, test_steps);
			res.selectRoom(driver, test_steps, roomClassName, Utility.RoomNo, "");
			res.selectRoom(driver, test_steps, roomClassName, Utility.RoomNo, "");

			depositAmount = res.deposit(driver, test_steps, isDepositOverride, depositOverrideAmount);
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

			res.enter_MarketSegmentDetails(driver, test_steps, travelAgent, marketSegment, referral);
			totalTaxChargesbeforesave = res.getTotaltaxBeforeSaveRes(driver);
			app_logs.info("totalTaxChargesbeforesave :" + totalTaxChargesbeforesave);
			test_steps.add("Total tax charges captured before save reservation: " + "<b>" + totalTaxChargesbeforesave
					+ "</b>");
			totalTripChargesbeforesave = res.getTotalTripTotalBeforeSaveRes(driver);
			app_logs.info("totalTripCharges:" + totalTripChargesbeforesave);
			test_steps.add("Total trip charges captured before reservation saved: " + "<b>" + totalTripChargesbeforesave
					+ "</b>");
			totalRoomChargesBeforeResSave = res.getRoomChargesbeforeResSave(driver);
			app_logs.info("totalRoomChargesBeforeResSave:" + "<b>" + totalRoomChargesBeforeResSave + "<b>");
			test_steps.add("Total room charges captured before resrevation saved: " + "<b>"
					+ totalRoomChargesBeforeResSave + "<b>");
			res.clickBookNow(driver, test_steps);

			res.get_ReservationConfirmationNumber(driver, test_steps);
			res.get_ReservationStatus(driver, test_steps);
			res.clickCloseReservationSavePopup(driver, test_steps);
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
			// Making assertion of after and before
			Wait.wait10Second();
			String tripSummaryTaxesWithCurrency = res.get_TripSummaryTaxesWithCurrency(driver, test_steps);
			roomCharge = res.getRoomChargesInTripSummary(driver);
			String TripTotalCharge = res.get_TripSummaryTripTotalChargesWithCurrency(driver, test_steps);

			totalRoomCharges = Double.parseDouble(totalRoomChargesBeforeResSave.replace("$", "").trim());

			tripsummarytaxesafterressave = Double.parseDouble(tripSummaryTaxesWithCurrency.replace("$", "").trim());
			double tripTotalAfterResSave = Double.parseDouble(TripTotalCharge.replace("$", "").trim());
			double roomChargeAfterResSave = Double.parseDouble(roomCharge.replace("$", "").trim());
			double totalTripAmountBeforeResSave = Double
					.parseDouble(totalTripChargesbeforesave.replace("$", "").trim());
			double totalTaxbeforeressave = Double.parseDouble(totalTaxChargesbeforesave.replace("$", "").trim());

			test_steps.add("<br>Making assertion of charges before and after saved the reservation");
			Assert.assertEquals(roomChargeAfterResSave, totalRoomCharges);
			test_steps.add("Verifying total room charges before and after reservation saved: " + "<b>"
					+ totalRoomCharges + "</b>");
			Assert.assertEquals(tripTotalAfterResSave, totalTripAmountBeforeResSave);
			test_steps.add("Verifying totalTrip charges before before and after saved: " + "<b>"
					+ totalTripAmountBeforeResSave + "</b>");
			Assert.assertEquals(tripsummarytaxesafterressave, totalTaxbeforeressave);
			test_steps.add("Verifying totalTaxes before and after reservation saved: " + "<b>"
					+ tripsummarytaxesafterressave + "<b>");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to make charges assertion", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to make charges assertion", "NONGS_Login", "Login", driver);
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

			if (isdefaultTaxAvailable) {
				String defaultTaxCalculatedVal = res.getPercentcalcvalueSingleItem(driver, defaulttaxValue, roomCharge.replace("$", "").trim(),
						stayDays);
				defaultTaxCalculatedAmountPerDay = Double.parseDouble(defaultTaxCalculatedVal);
			}
			String getCalculatedValofCreatedTax = res.getPercentcalcvalueSingleItem(driver, value, roomCharge.replace("$", "").trim(),
					stayDays);
			double calculatedValofCreatedTax = Double.parseDouble(getCalculatedValofCreatedTax);
			// int guestFolioItems = res.getTotalGuestFolioItem(driver, test_steps);
			double getCalculateEachDayperGuestTax = calculatedValofCreatedTax / stayDays;
			double roundOffCalculatedTaxCreated = Double.parseDouble(f.format(getCalculateEachDayperGuestTax))
					* stayDays;

			// Verify calculated tax over captured tax
			test_steps.add(
					"<b>*****************Verifying Calculated Total Over Captured in Reservation</b>***************************");
			double totalCalculatedTax = (roundOffCalculatedTaxCreated + defaultTaxCalculatedAmountPerDay) * stayDays;

			double roundOffCalculatedTotalTax = Double.parseDouble(f.format(totalCalculatedTax));
			

			res.click_Folio(driver, test_steps);

			int guestFolioItem = res.getTotalGuestFolioItem(driver, test_steps);
			if(stayDays==1) {
				double taxcalculated = totalCalculatedTax / guestFolioItem ;
				double taxroundOff = Double.parseDouble(f.format(taxcalculated)) * guestFolioItem;
				Assert.assertEquals(tripsummarytaxesafterressave, taxroundOff);
				test_steps.add("Verified assertion of totalcalculated tax over captured in reservation: " + "<b>"
						+ taxroundOff + "<b>");
			}else {
				
				Assert.assertEquals(tripsummarytaxesafterressave, roundOffCalculatedTotalTax);
				test_steps.add("Verified assertion of totalcalculated tax over captured in reservation: " + "<b>"
						+ roundOffCalculatedTotalTax + "<b>");
			}
			// making assertion in Reservation folio
			test_steps.add(
					"<b>*****************Verifying Created tax in Reservation Folios</b>***************************");
			//res.click_Folio(driver, test_steps);
			
			double eachFolioTaxCalculated = calculatedValofCreatedTax / guestFolioItem;
			for (int i = 1; i <= guestFolioItem; i++) {
				res.click_FolioDetail_DropDownBox(driver, test_steps);
				res.selectGuestFolioItemWithIndexInRes(driver, i);
				test_steps.add("Selected option from Guest detail dropdown: " + "<b>" + i + "</b>");
				Wait.wait5Second();

				res.verifyChildLineItemTaxes(driver, test_steps, taxLedgerAccount, taxName, eachFolioTaxCalculated);
				String totalTaxCapturedInFolio = res.get_Taxes(driver, test_steps);
				double totalTaxCalculatedInFolio = totalCalculatedTax / guestFolioItem;
				String totalTaxCalculatedroundOffInFolio = f.format(totalTaxCalculatedInFolio);
				Assert.assertEquals(Double.parseDouble(totalTaxCapturedInFolio),
						Double.parseDouble(totalTaxCalculatedroundOffInFolio));
				test_steps.add("Verified total tax value in guest folio : " + "<b>" + i + "</b>is : " + "<b>"
						+ Double.parseDouble(totalTaxCapturedInFolio) + "</b>");
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to verify tax in folion", "NONGS_Login", "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to verify tax in folio", "NONGS_Login", "Reservation", driver);
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
			rate.deleteRates(driver, rateDelete);
			test_steps.add("Rates Deleted starting with name: " + "<b>" + rateDelete + "</b>");
			nav.Setup(driver);
			test_steps.add("Navigate to Setup");
			nav.RoomClass(driver);
			test_steps.add("Navigate to Room Classes");
			roomClas.SearchRoomClass(driver, roomClassName, test_steps);
			test_steps.add("Searched room class: " + "<b>" + roomClassName + "</b>");
			roomClas.deleteRoomClass(driver, rcDelete);
			test_steps.add("Room Classes deleted starting from name : " + "<b>" + rcDelete + "</b>");
			nav.Taxes(driver);
			test_steps.add("Navigate to Tax page");
			tax.deleteTaxStartsWithSameName(driver, deleteTaxesName);
			test_steps.add("Taxes deleted starting from name : "+ "<b>"+deleteTaxesName+"</b>");
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

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getData("VerifyTaxAmountRoundOffMRB", excel);
	}
   @AfterClass(alwaysRun=true)
	public void closeDriver() {
		driver.quit();
	}
}
