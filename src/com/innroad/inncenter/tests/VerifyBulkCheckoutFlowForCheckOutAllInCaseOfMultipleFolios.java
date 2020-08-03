package com.innroad.inncenter.tests;

import static org.testng.Assert.assertEquals;

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
import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Folio;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class VerifyBulkCheckoutFlowForCheckOutAllInCaseOfMultipleFolios extends TestCore {

	private WebDriver driver = null;
	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();
	ArrayList<String> testName = new ArrayList<>();
	ArrayList<String> testDescription = new ArrayList<>();
	ArrayList<String> testCatagory = new ArrayList<>();

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() throws InterruptedException, IOException {

		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);

	}

	@Test(dataProvider = "getData", groups = "Reservations")
	public void verifyBulkCheckoutFlowForCheckOutAllInCaseOfMultipleFolios(String referral, String secondGuestFirstName,
			String secondGuestLastName, String adult, String children, String isAssign, String isChecked,
			String salutation, String paymentType, String cardNumber, String cardName, String cardExpDate,
			String roomClassName, String roomClassAbbreviation, String bedsCount, String maxAdults, String maxPersons,
			String roomQuantity, String additionalAdult, String additionalChild, String baseAmount, String rateName,
			String ratePolicy, String rateDescription, String reservedListSize, String quoteListSize,
			String associateSession, String accountType, String accountName, String accountFirstName,
			String accountLastName, String phoneNumber, String alternativeNumber, String firstAddress,
			String secondAddress, String thirdAddress, String email, String city, String state, String postalcode,
			String ratePlan, String firstLineItemCategory, String firstLineItemAmount, String secondLineItemCategory,
			String secondLineItemAmount, String rollBackText, String firstLineItemDesciption,
			String secondLineItemDesciption,String status,String nights, String departed, String roomChargeCategory, String inCenter) throws InterruptedException {
		
		String scriptName = "VerifyBulkCheckoutFlowForCheckOutAllInCaseOfMultipleFolios";
		String description = "Verify bulk checkout flow for check out all in case of multiple folios<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682465' target='_blank'>"
				+ "Click here to open TestRail: C682465</a>";
		String testcatagory = "Reservations";
		testName.add(scriptName);
		testCatagory.add(testcatagory);
		testDescription.add(description);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + scriptName + " TEST.");
		app_logs.info("##################################################################################");

		CPReservationPage reservation = new CPReservationPage();
		Navigation navigation = new Navigation();
		ReservationSearch reservationSearch = new ReservationSearch();
		RoomClass roomClass = new RoomClass();
		Account newAccount = new Account();
		Folio folio = new Folio();
		Rate rate = new Rate();
		String randomNumber = Utility.GenerateRandomNumber();
		secondGuestFirstName = secondGuestFirstName + randomNumber;
		secondGuestLastName = secondGuestLastName + randomNumber;
		accountFirstName = accountFirstName + randomNumber;
		accountLastName = accountLastName + randomNumber;
		String tempraryRoomClass = roomClassName;
		String firstGuestName = accountFirstName + " " + accountLastName;
		String secondGuestName = secondGuestFirstName + " " + secondGuestLastName;
		String account = "";
		String firstReservationNumber = "";
		String secondReservationNumber = "";
		String firstRoomNumber = "";
		String secondRoomNumber = "";
		String accountNumber = "";
		String getAccountName = "";
		String roomCharges = "";
		String allReservation = "";
		Double firstLineItemCategoryTax = null;
		Double secondLineItemCategoryTax = null;
		String getFirstLineItemAmount = null;
		String getFirstLineItemTax = null;
		String getSecondLineItemAmount = null;
		String getSecondLineItemTax = null;
		
		Double firstDblLineItemAmount = Double.parseDouble(firstLineItemAmount);
		Double secondDblLineItemAmount = Double.parseDouble(secondLineItemAmount);
		String firstReservationStatus = null;
		String secondReservationStatus = null;
		String getRoomCharegsWithTax= null;
		String getSecondRoomCharegsWithTax = null;
		String roomChargeTax = null;
		String checkIn="";
		String checkOut="";

		String tripSummaryTripTotal = null,tripSummaryBalance = null;
		String tripSummaryTripTotal2 = null,tripSummaryBalance2 = null;
		accountNumber = Utility.GenerateRandomString15Digit();
		accountName = accountName + accountNumber;
		rateName = rateName + randomNumber;

		try {
			if (!Utility.insertTestName.containsKey(scriptName)) {
				Utility.insertTestName.put(scriptName, scriptName);
				Utility.reTry.put(scriptName, 0);

			} else {
				Utility.reTry.replace(scriptName, 1);
			}
			driver = getDriver();
			login_CP(driver);
			testSteps.add("Logged into the application");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			testSteps.add("==========DELETE ROOM CLASSE==========");

			navigation.Setup(driver);
			testSteps.add("Click on setup");

			navigation.RoomClass(driver);
			testSteps.add("Click on room classes");

			boolean isRoomClassExist = roomClass.searchClass(driver, roomClassName);
			app_logs.info("Search");
			if (isRoomClassExist) {
				roomClass.deleteRoomClass(driver, roomClassName);
				testSteps.add("Delete room class successfully");
				app_logs.info("Delete room class successfully");
			}

		}

		catch (Exception e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
			Utility.updateReport(e, "Failed to delete room class", scriptName, "RoomClasses", driver);
		} catch (Error e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
			Utility.updateReport(e, "Failed to delete room class", scriptName, "RoomClasses", driver);
		}

		try {
			testSteps.add("========== CREATE NEW ROOM CLASS ==========");

			navigation.NewRoomClass(driver);
			testSteps.add("Click on new room class button");

			roomClassName = roomClassName + randomNumber;
			roomClassAbbreviation = roomClassAbbreviation + randomNumber;
			try {
				getTestSteps.clear();
				getTestSteps = roomClass.createRoomClass(driver, roomClassName, roomClassAbbreviation, bedsCount,
						maxAdults, maxPersons, roomQuantity, test, getTestSteps);
				testSteps.addAll(getTestSteps);
			} catch (Exception e) {
				getTestSteps.clear();

				roomClass.roomClassInformation(driver, roomClassName, roomClassAbbreviation, bedsCount, maxAdults,
						maxPersons, roomQuantity, test, getTestSteps);
				testSteps.addAll(getTestSteps);

			}

			testSteps.add("Sccessfully Created New RoomClass " + roomClassName + " Abb : " + roomClassAbbreviation);
			app_logs.info("Sccessfully Created New RoomClass" + roomClassName + " Abb : " + roomClassAbbreviation);

			roomClass.closeRoomClassTab(driver);
			testSteps.add("Close created room class tab");
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create room class successfully", scriptName, "NewRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create room class successfully", scriptName, "NewRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {

			testSteps.add("==========DELETE ALL RATES THAT START WITH NAME OF" + rateName + " ==========");
			
			navigation.Inventory(driver);
			testSteps.add("Navigate to Inventory");
			app_logs.info("Navigate to Inventory");

			getTestSteps.clear();
			getTestSteps = navigation.secondaryRatesMenuItem(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.deleteRates(driver, rateName);
			testSteps.addAll(getTestSteps);
			testSteps.add("New Rate has been Deleted successfully");
			app_logs.info("New Rate has been Deleted successfully");

			rate.verifyDeleteRate(driver, rateName);
			testSteps.add("Verify the Deleted Rate : " + rateName);
			app_logs.info("Verify the Deleted Rate " + rateName);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to delete a rate ", scriptName, "DeleteRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to delete a rate ", scriptName, "DeleteRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Create Rate with
		try {
			testSteps.add("==========CREATE NEW RATE==========");
			getTestSteps.clear();
			getTestSteps = rate.ClickNewRate(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.EnterRateName(driver, rateName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.SelectRatePlan(driver, ratePlan);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.EnterMaxAdults(driver, maxAdults);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.EnterMaxPersons(driver, maxPersons);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.EnterBaseAmount(driver, baseAmount);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.EnterAdditionalAdult(driver, additionalAdult);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.EnterAdditionalChild(driver, additionalChild);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.EnterRateDisplayName(driver, rateName);
			testSteps.addAll(getTestSteps);
		
			getTestSteps.clear();
			getTestSteps = rate.EnterRatePolicy(driver, ratePolicy);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.EnterRateDescription(driver, rateName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.AssociateSeason(driver, associateSession);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.AssociateRoomClass(driver, roomClassName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.AssociateSource(driver, inCenter);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.Save_DoneRate(driver);
			testSteps.addAll(getTestSteps);

			rate.SearchRate(driver, rateName, false);
			testSteps.add("New Rate With Name : " + rateName + " Created With & Verified ");
			app_logs.info("New Rate With Name : " + rateName + " Created With & Verified ");
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create  a rate ", scriptName, "NewRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create  a rate ", scriptName, "NewRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("====================CREATE NEW ACCOUNT==============");
			app_logs.info("====================CREATE NEW ACCOUNT==============");

			navigation.navigateToReservations(driver);

			navigation.Accounts_NavIcon(driver);

			getTestSteps.clear();
			newAccount.ClickNewAccountbutton(driver, accountType);
			testSteps.addAll(getTestSteps);

			newAccount.AccountDetails(driver, accountType, accountName);
			testSteps.add("Enter Account name : " + accountName);

			newAccount.enterAccountNumber(driver, accountNumber);

			getTestSteps.clear();
			getTestSteps = newAccount.mailingInfo(driver, accountFirstName, accountLastName, phoneNumber, firstAddress,
					email, city, state, postalcode);
			testSteps.addAll(getTestSteps);

			newAccount.Billinginfo(driver);

			newAccount.Save(driver);
			testSteps.add("click on save");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create  account ", scriptName, "Account", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create  account ", scriptName, "Account", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// create first reservation
		try {
			testSteps.add(
					"====================CREATE FIRST NEW RESERVATION AND ATTACHED ABOVE CREATED ACCOUNT==============");
			app_logs.info(
					"====================CREATE FIRST NEW RESERVATION AND ATTACHED ABOVE CREATED ACCOUNT==============");

			navigation.navigateToReservations(driver);
			
			String CheckInDate = Utility.getCurrentDate("MM/dd/yyyy");
			checkIn = ESTTimeZone.parseDate(CheckInDate, "MM/dd/yyyy", "MMM dd, yyyy");

			String CheckoutDate = Utility.GetNextDate(1, "MM/dd/yyyy");
			checkOut = ESTTimeZone.parseDate(CheckoutDate, "MM/dd/yyyy", "MMM dd, yyyy");

			getTestSteps.clear();
			reservation.click_NewReservation(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkInDate(driver, 0);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkOutDate(driver, +1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.enter_Adults(driver, getTestSteps, adult);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.enter_Children(driver, getTestSteps, children);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.select_Rateplan(driver, getTestSteps, ratePlan, "");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickOnFindRooms(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.selectRoom(driver, getTestSteps, roomClassName, isAssign, account);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.enter_GuestName(driver, getTestSteps, salutation, accountFirstName, accountLastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.uncheck_CreateGuestProfile(driver, getTestSteps, "No");
			testSteps.addAll(getTestSteps);

			app_logs.info("==========ASSOCIATE ACCOUNT==========");
			testSteps.add("==========ASSOCIATE ACCOUNT==========");
			reservation.selectAccount(driver, getTestSteps, accountName, accountType);

			testSteps.add("Add account number" + accountNumber);
			testSteps.add("Verified account number is attached with reservation: " + accountNumber);

			getTestSteps.clear();
			reservation.selectReferral(driver, referral);
			testSteps.add("Successfully Selected Referral as :" + referral);
			
			getTestSteps.clear();
			reservation.enterPaymentDetails(driver, getTestSteps, paymentType, cardNumber, cardName, cardExpDate);
			testSteps.addAll(getTestSteps);
			
			getTestSteps.clear();
			reservation.clickBookNow(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			firstReservationNumber = reservation.getReservationConfirmationNumber(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickCloseReservationSavePopup(driver, getTestSteps);
			testSteps.addAll(getTestSteps);
			
			firstReservationStatus = reservation.getReservationStatusInDetailsSection(driver);
			
			getAccountName = reservation.getAccountName(driver);
			testSteps.add("Expected account name: " + accountName);
			testSteps.add("Found: " + getAccountName);
			assertEquals(getAccountName, accountName, "Failed to verify attached account");
			testSteps.add("Verified account is attached");

			getTestSteps.clear();
			firstRoomNumber = roomClassAbbreviation + " : " + reservation.getRoomNo_ResDetail(driver);
			testSteps.add("Room number is:" + firstRoomNumber);
			app_logs.info("Room number is:" + firstRoomNumber);

			getTestSteps.clear();
			roomCharges = reservation.getRoomCharge_TripSummary(driver);
			testSteps.add("Room charges is: " + roomCharges);

			tripSummaryTripTotal = reservation.get_TripSummaryTripTotalChargesWithCurrency(driver, testSteps);
			tripSummaryTripTotal = reservation.replaceCurrency(tripSummaryTripTotal);

			tripSummaryBalance = reservation.get_TripSummaryBalanceWithCurrency(driver, testSteps);
			tripSummaryBalance = reservation.replaceCurrency(tripSummaryBalance);
		
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create first reservation", scriptName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create first reservation ", scriptName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try{
			
			getTestSteps.clear();
			reservation.click_Folio(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getRoomCharegsWithTax = folio.getAmount(driver, roomChargeCategory, 1);
			app_logs.info("getRoomCharegsWithTax is:" + getRoomCharegsWithTax);
			roomChargeTax = folio.getTax(driver, roomChargeCategory);
			app_logs.info("roomChargeTax is:" + roomChargeTax);

			getTestSteps.clear();
			getTestSteps = folio.changeFolioOption(driver, accountName);
			testSteps.addAll(getTestSteps);
			testSteps.add("==========ADDING FOLIO LINE ITEM(" + firstLineItemCategory
					+ ")IN " + accountName + " FOLIO  ==========");
			app_logs.info("==========ADDING OF FOLIO LINE ITEM(" + firstLineItemCategory
					+ ")IN " + accountName + " FOLIO  ==========");
			getTestSteps.clear();
			reservation.AddLineItem(driver, getTestSteps, firstLineItemCategory, firstLineItemAmount);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.changeFolioOption(driver, accountName);
			testSteps.addAll(getTestSteps);

			testSteps.add("==========VERIFICATION OF FOLIO LINE ITEM(" + firstLineItemCategory
					+ ")IN RESERVATION  ==========");
			app_logs.info("==========VERIFICATION OF FOLIO LINE ITEM(" + firstLineItemCategory
					+ ")IN RESERVATION  ==========");
			getTestSteps.clear();
			getTestSteps = folio.LineItemDate(driver, firstLineItemCategory);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = folio.LineItemCategory(driver, firstLineItemCategory);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.getDescroption(driver, firstLineItemCategory, firstLineItemDesciption, false);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemQuentity(driver, firstLineItemCategory, "1");
			testSteps.addAll(getTestSteps);

			getFirstLineItemAmount = folio.getAmount(driver, firstLineItemCategory);
			app_logs.info("getFirstLineItemAmount : " + getFirstLineItemAmount);
			getFirstLineItemTax = folio.getTax(driver, firstLineItemCategory);
			getFirstLineItemTax = reservation.replaceString(getFirstLineItemTax);
			Double getFirstLineItemTaxInDouble = Double.parseDouble(getFirstLineItemTax);

			firstLineItemCategoryTax = getFirstLineItemTaxInDouble;

			app_logs.info(firstLineItemCategory + "'s Tax : " + getFirstLineItemTaxInDouble);
			testSteps.add(firstLineItemCategory + "'s Tax : " + getFirstLineItemTaxInDouble);
			String getExpectedAmount = folio.AddValue(firstLineItemAmount, getFirstLineItemTax);

			getExpectedAmount = "$ " + getExpectedAmount;
			testSteps.add("Expected amount: " + getExpectedAmount);
			testSteps.add("Found : " + getFirstLineItemAmount);
			System.out.println(getFirstLineItemAmount + " expected amount :" + getExpectedAmount);
			assertEquals(getFirstLineItemAmount, getExpectedAmount, "Failed: Amount is mismatching!");
			testSteps.add("Verified amount after");

			String folioIncidentalsTotal = reservation.get_InceidentalsWithCurrency(driver, getTestSteps);
			folioIncidentalsTotal = reservation.replaceString(folioIncidentalsTotal);
			Double dblFolioIncidentalsTotal = Double.parseDouble(folioIncidentalsTotal);
			testSteps.add("Expected incidental  amount : " + firstDblLineItemAmount);
			app_logs.info("Expected incidental  amount : " + firstDblLineItemAmount);
			testSteps.add("Found : " + dblFolioIncidentalsTotal);
			app_logs.info("Found : " + dblFolioIncidentalsTotal);
			Assert.assertEquals(dblFolioIncidentalsTotal, firstDblLineItemAmount, "Incidental Amount Mismatches");
			testSteps.add("Incidental amount verified");
			app_logs.info("Incidental amount verified");

			reservation.closeReservationTab(driver);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to add and verify lien item", scriptName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to add and verify lien item ", scriptName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// create second reservation
		try {
			testSteps.add(
					"====================CREATE SECOND NEW RESERVATION AND ATTACHED ABOVE CREATED ACCOUNT==============");
			app_logs.info(
					"====================CREATE SECOND NEW RESERVATION AND ATTACHED ABOVE CREATED ACCOUNT==============");

			getTestSteps.clear();
			reservation.click_NewReservation(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkInDate(driver, 0);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkOutDate(driver, +1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.enter_Adults(driver, getTestSteps, adult);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.enter_Children(driver, getTestSteps, children);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.select_Rateplan(driver, getTestSteps, ratePlan, "");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickOnFindRooms(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.selectRoom(driver, getTestSteps, roomClassName, isAssign, account);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.enter_GuestName(driver, getTestSteps, salutation, secondGuestFirstName, secondGuestLastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.uncheck_CreateGuestProfile(driver, getTestSteps, "No");
			testSteps.addAll(getTestSteps);

			app_logs.info("==========ASSOCIATE ACCOUNT==========");
			testSteps.add("==========ASSOCIATE ACCOUNT==========");
			reservation.selectAccount(driver, getTestSteps, accountName, accountType);

			testSteps.add("Add account number" + accountNumber);
			testSteps.add("Verified account number is attached with reservation: " + accountNumber);

			getTestSteps.clear();
			reservation.selectReferral(driver, referral);
			testSteps.add("Successfully Selected Referral as :" + referral);

			getTestSteps.clear();
			reservation.enterPaymentDetails(driver, getTestSteps, paymentType, cardNumber, cardName, cardExpDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickBookNow(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			secondReservationNumber = reservation.getReservationConfirmationNumber(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.clickCloseReservationSavePopup(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			secondReservationStatus = reservation.getReservationStatusInDetailsSection(driver);
			
			getTestSteps.clear();
			secondRoomNumber = roomClassAbbreviation + " : " + reservation.getRoomNo_ResDetail(driver);
			testSteps.add("Room number is:" + secondRoomNumber);
			app_logs.info("Room number is:" + secondRoomNumber);

			getTestSteps.clear();
			roomCharges = reservation.getRoomCharge_TripSummary(driver);
			testSteps.add("Room charges is: " + roomCharges);

			tripSummaryTripTotal2 = reservation.get_TripSummaryTripTotalChargesWithCurrency(driver, testSteps);
			tripSummaryTripTotal2 = reservation.replaceCurrency(tripSummaryTripTotal2);

			tripSummaryBalance2 = reservation.get_TripSummaryBalanceWithCurrency(driver, testSteps);
			tripSummaryBalance2 = reservation.replaceCurrency(tripSummaryBalance2);


		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create second reservation", scriptName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create second reservation ", scriptName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try{
			

			getTestSteps.clear();
			reservation.click_Folio(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getSecondRoomCharegsWithTax = folio.getAmount(driver, roomChargeCategory, 1);
			app_logs.info("getSecondRoomCharegsWithTax : " + getSecondRoomCharegsWithTax);
			
			getTestSteps.clear();
			getTestSteps = folio.changeFolioOption(driver, accountName);
			testSteps.addAll(getTestSteps);

			testSteps.add("==========ADDING FOLIO LINE ITEM(" + secondLineItemCategory
					+ ")IN " + accountName + " FOLIO  ==========");
			app_logs.info("==========ADDING OF FOLIO LINE ITEM(" + secondLineItemCategory
					+ ")IN " + accountName + " FOLIO  ==========");
			getTestSteps.clear();
			reservation.AddLineItem(driver, getTestSteps, secondLineItemCategory, secondLineItemAmount);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.changeFolioOption(driver, accountName);
			testSteps.addAll(getTestSteps);

			testSteps.add("==========VERIFICATION OF FOLIO LINE ITEM(" + secondLineItemCategory
					+ ")IN RESERVATION  ==========");
			app_logs.info("==========VERIFICATION OF FOLIO LINE ITEM(" + secondLineItemCategory
					+ ")IN RESERVATION  ==========");
			getTestSteps.clear();
			getTestSteps = folio.LineItemDate(driver, secondLineItemCategory);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = folio.LineItemCategory(driver, secondLineItemCategory);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.getDescroption(driver, secondLineItemCategory, secondLineItemDesciption, false);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemQuentity(driver, secondLineItemCategory, "1");
			testSteps.addAll(getTestSteps);

			getSecondLineItemAmount = folio.getAmount(driver, secondLineItemCategory);
			app_logs.info(getSecondLineItemAmount + " : " + getSecondLineItemAmount);
			getSecondLineItemTax = folio.getTax(driver, secondLineItemCategory);
			getSecondLineItemTax = reservation.replaceString(getSecondLineItemTax);
			Double getSecondLineItemTaxInDouble = Double.parseDouble(getSecondLineItemTax);

			secondLineItemCategoryTax = getSecondLineItemTaxInDouble;

			app_logs.info(secondLineItemCategory + "'s Tax : " + getSecondLineItemTaxInDouble);
			testSteps.add(secondLineItemCategory + "'s Tax : " + getSecondLineItemTaxInDouble);
			String getExpectedAmount = folio.AddValue(secondLineItemAmount, getSecondLineItemTax);

			getExpectedAmount = "$ " + getExpectedAmount;
			testSteps.add("Expected amount: " + getExpectedAmount);
			testSteps.add("Found : " + getSecondLineItemAmount);
			System.out.println(getSecondLineItemAmount + " expected amount :" + getExpectedAmount);
			assertEquals(getSecondLineItemAmount, getExpectedAmount, "Failed: Amount is mismatching!");
			testSteps.add("getSecondLineItemAmount amount after");

			String folioIncidentalsTotal = reservation.get_InceidentalsWithCurrency(driver, getTestSteps);
			folioIncidentalsTotal = reservation.replaceString(folioIncidentalsTotal);
			Double dblFolioIncidentalsTotal = Double.parseDouble(folioIncidentalsTotal);
			testSteps.add("Expected incidental  amount : " + secondDblLineItemAmount);
			app_logs.info("Expected incidental  amount : " + secondDblLineItemAmount);
			testSteps.add("Found : " + dblFolioIncidentalsTotal);
			app_logs.info("Found : " + dblFolioIncidentalsTotal);
			Assert.assertEquals(dblFolioIncidentalsTotal, secondDblLineItemAmount, "Incidental Amount Mismatches");
			testSteps.add("Incidental amount verified");
			app_logs.info("Incidental amount verified");

			reservation.closeReservationTab(driver);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to add and verify line item in second reservation", scriptName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to add and verify line item in second reservation", scriptName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Search for Above Reservations

		try {

			testSteps.add("============SEARCH FOR CREATED RESERVATIONS============");
			allReservation = firstReservationNumber + "," + secondReservationNumber;

			reservationSearch.multipleSearchReservationNumber(driver, allReservation);
			testSteps.add("Successfully Search(" + allReservation +") with Multiple Reservation Numbers");

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to Search all created reservation", scriptName, "SearchReservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to Search all created reservation", scriptName, "SearchReservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			testSteps.add("============BULK OPTION CHECK IN============");
			app_logs.info("============BULK OPTION CHECK IN============");

			getTestSteps.clear();
			getTestSteps = reservation.selectAllSearchedReservationCheckBox(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationSearch.clickBulkOptionCheckInAndVerifyPopUp(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.clickOnProcessButtonInBulkCheckInPopUp(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.closeBulkActionPopUp(driver);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to perform bulk checkin action", scriptName, "BulkCheckIn", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to perform bulk checkin action", scriptName, "BulkCheckIn", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			driver.navigate().refresh();
			reservationSearch.multipleSearchReservationNumber(driver, allReservation);
			testSteps.add("Searched all Four Restervations at same search");

			testSteps.add("============VERIFY " + firstGuestName + " RESERVATION IN SEARCH RESULTS============");
			app_logs.info("============VERIFY " + firstGuestName + " RESERVATION IN SEARCH RESULTS============");

			String getGuestNameSearch = reservation.getReservationSearchGuestName(driver, 1);
			testSteps.add("Expected : " + firstGuestName);
			app_logs.info("Expected : " + firstGuestName);
			testSteps.add("Found: " + getGuestNameSearch);
			app_logs.info("Found: " + getGuestNameSearch);
			assertEquals(getGuestNameSearch, firstGuestName, "Failed to verify guest name: " + getGuestNameSearch);
			testSteps.add("Verified guest name");

			
			String getAccountNameSearch = reservation.getReservationSearchDetails(driver, 1, 4);
			testSteps.add("Expected : " + accountName);
			testSteps.add("Found: " + getAccountName);
			assertEquals(getAccountNameSearch, accountName, "Failed to verify account name: " + getAccountNameSearch);
			testSteps.add("Verified account name");

			String getReservationNumber = reservation.getReservationSearchDetails(driver, 1, 5);
			testSteps.add("Expected : " + firstReservationNumber);
			testSteps.add("Found: " + getReservationNumber);
			assertEquals(getReservationNumber, firstReservationNumber,
					"Failed to verify reservation number: " + getReservationNumber);
			testSteps.add("Verified reservation number");

			String getAdults = reservation.getReservationSearchDetails(driver, 1, 9);
			testSteps.add("Expected : " + adult);
			testSteps.add("Found: " + getAdults);
			assertEquals(getAdults, adult, "Failed to verify adults: " + getAdults);
			testSteps.add("Verified adult");

			String getChildren = reservation.getReservationSearchDetails(driver, 1, 10);
			testSteps.add("Expected : " + children);
			testSteps.add("Found: " + getChildren);
			assertEquals(getChildren, children, "Failed to verify children: " + getChildren);
			testSteps.add("Verified children");

			String getStatus = reservation.getReservationSearchDetails(driver, 1, 11);
			testSteps.add("Expected : " + status);
			testSteps.add("Found: " + getStatus);
			assertEquals(getStatus, status, "Failed to verify status: " + getStatus);
			testSteps.add("Verified status");

			String getRoom = reservation.getReservationSearchDetails(driver, 1, 12);
			testSteps.add("Expected : " + firstRoomNumber);
			testSteps.add("Found: " + getRoom);
			assertEquals(getRoom, firstRoomNumber, "Failed to verify room number: " + getRoom);
			testSteps.add("Verified room number");
			
			String getArrive = reservation.getReservationSearchDetails(driver, 1, 13);
			testSteps.add("Expected : " + checkIn);
			testSteps.add("Found: " + getArrive);
			assertEquals(getArrive, checkIn, "Failed to verify arrive: " + getArrive);
			testSteps.add("Verified arrive");
			
			String getDepart = reservation.getReservationSearchDetails(driver, 1, 14);
			testSteps.add("Expected : " + checkOut);
			testSteps.add("Found: " + getDepart);
			assertEquals(getDepart, checkOut, "Failed to verify depart: " + getDepart);
			testSteps.add("Verified depart");
			
			String getNights = reservation.getReservationSearchDetails(driver, 1, 15);
			testSteps.add("Expected : " + nights);
			testSteps.add("Found: " + getNights);
			assertEquals(getNights, nights, "Failed to verify night: " + getNights);
			testSteps.add("Verified nights");
			
			
//second guest verification
			testSteps.add("============VERIFY " + secondGuestName +" RESERVATION IN SEARCH RESULTS============");
			app_logs.info("============VERIFY " + secondGuestName +" RESERVATION IN SEARCH RESULTS============");

			getGuestNameSearch = reservation.getReservationSearchGuestName(driver, 2);
			testSteps.add("Expected : " + secondGuestName);
			app_logs.info("Expected : " + secondGuestName);
			testSteps.add("Found: " + getGuestNameSearch);
			app_logs.info("Found: " + getGuestNameSearch);
			assertEquals(getGuestNameSearch, secondGuestName, "Failed to verify guest name: " + getGuestNameSearch);
			testSteps.add("Verified guest name");
			
			getAccountNameSearch = reservation.getReservationSearchDetails(driver, 2, 4);
			testSteps.add("Expected : " + accountName);
			testSteps.add("Found: " + getAccountName);
			assertEquals(getAccountNameSearch, accountName, "Failed to verify account name: " + getAccountNameSearch);
			testSteps.add("Verified account name");

			getReservationNumber = reservation.getReservationSearchDetails(driver, 2, 5);
			testSteps.add("Expected : " + secondReservationNumber);
			testSteps.add("Found: " + getReservationNumber);
			assertEquals(getReservationNumber, secondReservationNumber,
					"Failed to verify reservation number: " + getReservationNumber);
			testSteps.add("Verified reservation number");

			getAdults = reservation.getReservationSearchDetails(driver, 2, 9);
			testSteps.add("Expected : " + adult);
			testSteps.add("Found: " + getAdults);
			assertEquals(getAdults, adult, "Failed to verify adults: " + getAdults);
			testSteps.add("Verified adult");

			getChildren = reservation.getReservationSearchDetails(driver, 2, 10);
			testSteps.add("Expected : " + children);
			testSteps.add("Found: " + getChildren);
			assertEquals(getChildren, children, "Failed to verify children: " + getChildren);
			testSteps.add("Verified children");

			getStatus = reservation.getReservationSearchDetails(driver, 2, 11);
			testSteps.add("Expected : " + status);
			testSteps.add("Found: " + getStatus);
			assertEquals(getStatus, status, "Failed to verify status: " + getStatus);
			testSteps.add("Verified status");

			getRoom = reservation.getReservationSearchDetails(driver, 2, 12);
			testSteps.add("Expected : " + secondRoomNumber);
			testSteps.add("Found: " + getRoom);
			assertEquals(getRoom, secondRoomNumber, "Failed to verify room number: " + getRoom);
			testSteps.add("Verified room number");
			
			getArrive = reservation.getReservationSearchDetails(driver, 2, 13);
			testSteps.add("Expected : " + checkIn);
			testSteps.add("Found: " + getArrive);
			assertEquals(getArrive, checkIn, "Failed to verify arrive: " + getArrive);
			testSteps.add("Verified arrive");
			
			getDepart = reservation.getReservationSearchDetails(driver, 2, 14);
			testSteps.add("Expected : " + checkOut);
			testSteps.add("Found: " + getDepart);
			assertEquals(getDepart, checkOut, "Failed to verify depart: " + getDepart);
			testSteps.add("Verified depart");
			
			getNights = reservation.getReservationSearchDetails(driver, 2, 15);
			testSteps.add("Expected : " + nights);
			testSteps.add("Found: " + getNights);
			assertEquals(getNights, nights, "Failed to verify night: " + getNights);
			testSteps.add("Verified nights");
			
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify reservation details in search results", scriptName, "SearchReservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify reservation details in search results", scriptName, "SearchReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try{
			
			testSteps.add("===========BULK CHECK OUT===========");
			app_logs.info("===========BULK CHECK OUT===========");

			getTestSteps.clear();
			getTestSteps = reservation.selectAllSearchedReservationCheckBox(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationSearch.selectBulkCheckOut(driver);
			testSteps.addAll(getTestSteps);
			
			
			testSteps.add("============VERIFY RESERVATION DETAIL ON CHECKOUT POPUP BEFORE CLICK PROCESS============");
			app_logs.info("============VERIFY RESERVATION DETAIL ON CHECKOUT POPUP BEFORE CLICK PROCESS============");

			testSteps.add("============VERIFY " + secondGuestName+ " RESERVATION DETAILS IN CHECKOUT POPUP============");
			app_logs.info("============VERIFY " + secondGuestName+ " RESERVATION DETAILS IN CHECKOUT POPUP============");

			String getGuestNameSearch = reservation.getGuestNameInChekoutPopup(driver,secondGuestName, 1);
			testSteps.add("Expected : " + secondGuestName);
			app_logs.info("Expected : " + secondGuestName);
			testSteps.add("Found: " + getGuestNameSearch);
			app_logs.info("Found: " + getGuestNameSearch);
			assertEquals(getGuestNameSearch, secondGuestName, "Failed to verify guest name: " + getGuestNameSearch);
			testSteps.add("Verified guest name");

		
			String getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, secondGuestName, 1, 1);
			testSteps.add("Expected : " + secondReservationNumber);
			app_logs.info("Expected : " + secondReservationNumber);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, secondReservationNumber, "Failed to verify reservation number: " + getCheckoutPopupValue);
			testSteps.add("Verified reservation number");

			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, secondGuestName, 1, 2);
			testSteps.add("Expected : " + secondRoomNumber);
			app_logs.info("Expected : " + secondRoomNumber);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, secondRoomNumber, "Failed to verify room number: " + getCheckoutPopupValue);
			testSteps.add("Verified reservation number");

			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, secondGuestName, 1, 3);
			testSteps.add("Expected : " + paymentType);
			app_logs.info("Expected : " + paymentType);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, paymentType, "Failed to verify paymentType: " + getCheckoutPopupValue);
			testSteps.add("Verified payment type");

			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, secondGuestName, 1, 5);
			String expectedCradNumber  ="XXXX"+ cardNumber.substring(cardNumber.length() - 4);
			testSteps.add("Expected : " + expectedCradNumber);
			app_logs.info("Expected : " + expectedCradNumber);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, expectedCradNumber, "Failed to verify cardNumber: " + getCheckoutPopupValue);
			testSteps.add("Verified card number");

			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, secondGuestName, 1, 6);
			testSteps.add("Expected : " + cardExpDate);
			app_logs.info("Expected : " + cardExpDate);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, cardExpDate, "Failed to verify cardExpDate: " + getCheckoutPopupValue);
			testSteps.add("Verified card expiry date");	
					
			String getCharges = reservation.getChargesInChekoutPopup(driver,secondGuestName, 1);			
			getCharges = reservation.replaceCurrency(getCharges);
			getSecondRoomCharegsWithTax = reservation.replaceCurrency(getSecondRoomCharegsWithTax);
			testSteps.add("Expected RoomCharges : " + getSecondRoomCharegsWithTax);
			app_logs.info("Expected RoomCharges : " + getSecondRoomCharegsWithTax);
			testSteps.add("Found: " + getCharges + ".00");
			app_logs.info("Found: " + getCharges + ".00");
			assertEquals(getCharges + ".00", getSecondRoomCharegsWithTax, "Failed to verify roomcharges: " + getCharges);
			testSteps.add("Verified room charges for " + secondGuestName);
			

			getCharges = reservation.getChargesInChekoutPopup(driver,secondGuestName, 2);			
			getCharges = reservation.replaceCurrency(getCharges);			
			String lineItemTaxInString = String.format("%.2f", secondLineItemCategoryTax);
			String totalLineItemAmount = folio.addTwoValue(secondLineItemAmount, lineItemTaxInString);
			testSteps.add("Expected Line Item Amount : " + totalLineItemAmount);
			app_logs.info("Expected Line Item Amount : " + totalLineItemAmount);
			testSteps.add("Found: " + getCharges + ".00");
			app_logs.info("Found: " + getCharges + ".00");
			assertEquals(getCharges + ".00", totalLineItemAmount, "Failed to verify line item amount: " + getCharges);
			testSteps.add("Verified line item amount for " + secondGuestName);
			
			testSteps.add("============VERIFY " + firstGuestName+ " RESERVATION DETAILS IN CHECKOUT POPUP============");
			app_logs.info("============VERIFY " + firstGuestName+ " RESERVATION DETAILS IN CHECKOUT POPUP============");

			getGuestNameSearch = reservation.getGuestNameInChekoutPopup(driver, firstGuestName, 1);
			testSteps.add("Expected : " + firstGuestName);
			app_logs.info("Expected : " + firstGuestName);
			testSteps.add("Found: " + getGuestNameSearch);
			app_logs.info("Found: " + getGuestNameSearch);
			assertEquals(getGuestNameSearch, firstGuestName, "Failed to verify guest name: " + getGuestNameSearch);
			testSteps.add("Verified guest name");
			

			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, firstGuestName, 1, 1);
			testSteps.add("Expected : " + firstReservationNumber);
			app_logs.info("Expected : " + firstReservationNumber);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, firstReservationNumber, "Failed to verify reservation number: " + getCheckoutPopupValue);
			testSteps.add("Verified reservation number");

			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, firstGuestName, 1, 2);
			testSteps.add("Expected : " + firstRoomNumber);
			app_logs.info("Expected : " + firstRoomNumber);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, firstRoomNumber, "Failed to verify room number: " + getCheckoutPopupValue);
			testSteps.add("Verified reservation number");

			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, firstGuestName, 1, 3);
			testSteps.add("Expected : " + paymentType);
			app_logs.info("Expected : " + paymentType);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, paymentType, "Failed to verify paymentType: " + getCheckoutPopupValue);
			testSteps.add("Verified payment type");

			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, firstGuestName, 1, 5);
			testSteps.add("Expected : " + expectedCradNumber);
			app_logs.info("Expected : " + expectedCradNumber);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, expectedCradNumber, "Failed to verify cardNumber: " + getCheckoutPopupValue);
			testSteps.add("Verified card number");

			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, firstGuestName, 1, 6);
			testSteps.add("Expected : " + cardExpDate);
			app_logs.info("Expected : " + cardExpDate);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, cardExpDate, "Failed to verify cardExpDate: " + getCheckoutPopupValue);
			testSteps.add("Verified card expiry date");	
			
			getCharges = reservation.getChargesInChekoutPopup(driver,firstGuestName, 1);
			getCharges = reservation.replaceCurrency(getCharges);
			getRoomCharegsWithTax = reservation.replaceCurrency(getRoomCharegsWithTax);
			testSteps.add("Expected RoomCharges : " + getRoomCharegsWithTax);
			app_logs.info("Expected RoomCharges : " + getRoomCharegsWithTax);
			testSteps.add("Found: " + getCharges + ".00");
			app_logs.info("Found: " + getCharges + ".00");
			assertEquals(getCharges + ".00", getRoomCharegsWithTax, "Failed to verify roomcharges: " + getCharges);
			testSteps.add("Verified room charges for " + firstGuestName);			

			getCharges = reservation.getChargesInChekoutPopup(driver, firstGuestName, 2);
			getCharges = reservation.replaceCurrency(getCharges);
			lineItemTaxInString = String.format("%.2f", firstLineItemCategoryTax);
			totalLineItemAmount = folio.addTwoValue(firstLineItemAmount, lineItemTaxInString);
			testSteps.add("Expected Line Item Amount : " + totalLineItemAmount);
			app_logs.info("Expected Line Item Amount : " + totalLineItemAmount);
			testSteps.add("Found: " + getCharges + ".00");
			app_logs.info("Found: " + getCharges + ".00");
			assertEquals(getCharges + ".00", totalLineItemAmount, "Failed to verify line item amount: " + getCharges);
			testSteps.add("Verified line item amount for " + firstGuestName);
						
			getTestSteps.clear();
			getTestSteps = reservation.clickOnProcessInBulkCheckOutPopUp(driver);
			testSteps.addAll(getTestSteps);

			testSteps.add("============VERIFY RESERVATION DETAIL ON CHECKOUT POPUP BEFORE CLICK CLOSE============");
			app_logs.info("============VERIFY RESERVATION DETAIL ON CHECKOUT POPUP BEFORE CLICK CLOSE============");
			
			getTestSteps.clear();
			getTestSteps = reservation.verifyBulkCheckOutSuccessHeadng(driver);
			testSteps.addAll(getTestSteps);
			
			getCheckoutPopupValue = reservation.getReservationCountCheckoutSuccessPopup(driver);
			testSteps.add("Expected : " + "4");
			app_logs.info("Expected : " + "4");
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, "4", "Failed to verify reservation count: " + "4");
			testSteps.add("Verified reservation count");

			testSteps.add("============VERIFY " + secondGuestName+ " RESERVATION DETAILS IN CHECKOUT SUCCESS POPUP============");
			app_logs.info("============VERIFY " + secondGuestName+ " RESERVATION DETAILS IN CHECKOUT SUCCESS POPUP============");
	
			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, secondGuestName, 3, 1);
			testSteps.add("Expected : " + secondReservationNumber);
			app_logs.info("Expected : " + secondReservationNumber);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, secondReservationNumber, "Failed to verify reservation number: " + getCheckoutPopupValue);
			testSteps.add("Verified reservation number");

			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, secondGuestName, 3, 2);
			testSteps.add("Expected : " + secondRoomNumber);
			app_logs.info("Expected : " + secondRoomNumber);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, secondRoomNumber, "Failed to verify room number: " + getCheckoutPopupValue);
			testSteps.add("Verified reservation number");

			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, secondGuestName, 3, 3);
			testSteps.add("Expected : " + paymentType);
			app_logs.info("Expected : " + paymentType);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, paymentType, "Failed to verify paymentType: " + getCheckoutPopupValue);
			testSteps.add("Verified payment type");

			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, secondGuestName, 3, 5);
			expectedCradNumber  ="XXXX"+ cardNumber.substring(cardNumber.length() - 4);
			testSteps.add("Expected : " + expectedCradNumber);
			app_logs.info("Expected : " + expectedCradNumber);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, expectedCradNumber, "Failed to verify cardNumber: " + getCheckoutPopupValue);
			testSteps.add("Verified card number");

			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, secondGuestName, 3, 6);
			testSteps.add("Expected : " + cardExpDate);
			app_logs.info("Expected : " + cardExpDate);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, cardExpDate, "Failed to verify cardExpDate: " + getCheckoutPopupValue);
			testSteps.add("Verified card expiry date");	

			getCharges = reservation.getChargesInChekoutSuccessPopup(driver,secondGuestName, 1);			
			getCharges = reservation.replaceCurrency(getCharges);
			getSecondRoomCharegsWithTax = reservation.replaceCurrency(getSecondRoomCharegsWithTax);
			testSteps.add("Expected RoomCharges : " + getSecondRoomCharegsWithTax);
			app_logs.info("Expected RoomCharges : " + getSecondRoomCharegsWithTax);
			testSteps.add("Found: " + getCharges + ".00");
			app_logs.info("Found: " + getCharges + ".00");
			assertEquals(getCharges + ".00", getSecondRoomCharegsWithTax, "Failed to verify roomcharges: " + getCharges);
			testSteps.add("Verified room charges for " + secondGuestName);
			

			getCharges = reservation.getChargesInChekoutSuccessPopup(driver,secondGuestName, 2);			
			getCharges = reservation.replaceCurrency(getCharges);
			lineItemTaxInString = String.format("%.2f", secondLineItemCategoryTax);
			totalLineItemAmount = folio.addTwoValue(secondLineItemAmount, lineItemTaxInString);
			testSteps.add("Expected Line Item Amount : " + totalLineItemAmount);
			app_logs.info("Expected Line Item Amount : " + totalLineItemAmount);
			testSteps.add("Found: " + getCharges + ".00");
			app_logs.info("Found: " + getCharges + ".00");
			assertEquals(getCharges + ".00", totalLineItemAmount, "Failed to verify line item amount: " + getCharges);
			testSteps.add("Verified line item amount for " + secondGuestName);
			
			testSteps.add("============VERIFY " + firstGuestName+ " RESERVATION DETAILS IN CHECKOUT SUCCESS POPUP============");
			app_logs.info("============VERIFY " + firstGuestName+ " RESERVATION DETAILS IN CHECKOUT SUCCESS POPUP============");
	

			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, firstGuestName, 3, 1);
			testSteps.add("Expected : " + firstReservationNumber);
			app_logs.info("Expected : " + firstReservationNumber);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, firstReservationNumber, "Failed to verify reservation number: " + getCheckoutPopupValue);
			testSteps.add("Verified reservation number");

			
			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, firstGuestName, 3, 2);
			testSteps.add("Expected : " + firstRoomNumber);
			app_logs.info("Expected : " + firstRoomNumber);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, firstRoomNumber, "Failed to verify room number: " + getCheckoutPopupValue);
			testSteps.add("Verified reservation number");

			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, firstGuestName, 3, 3);
			testSteps.add("Expected : " + paymentType);
			app_logs.info("Expected : " + paymentType);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, paymentType, "Failed to verify paymentType: " + getCheckoutPopupValue);
			testSteps.add("Verified payment type");

			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, firstGuestName, 3, 5);
			testSteps.add("Expected : " + expectedCradNumber);
			app_logs.info("Expected : " + expectedCradNumber);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, expectedCradNumber, "Failed to verify cardNumber: " + getCheckoutPopupValue);
			testSteps.add("Verified card number");

			getCheckoutPopupValue = reservation.verifyChekoutPopup(driver, firstGuestName, 3, 6);
			testSteps.add("Expected : " + cardExpDate);
			app_logs.info("Expected : " + cardExpDate);
			testSteps.add("Found: " + getCheckoutPopupValue);
			app_logs.info("Found: " + getCheckoutPopupValue);
			assertEquals(getCheckoutPopupValue, cardExpDate, "Failed to verify cardExpDate: " + getCheckoutPopupValue);
			testSteps.add("Verified card expiry date");	
			
			getCharges = reservation.getChargesInChekoutSuccessPopup(driver, firstGuestName, 1);
			getCharges = reservation.replaceCurrency(getCharges);
			getRoomCharegsWithTax = reservation.replaceCurrency(getRoomCharegsWithTax);
			testSteps.add("Expected RoomCharges : " + getRoomCharegsWithTax);
			app_logs.info("Expected RoomCharges : " + getRoomCharegsWithTax);
			testSteps.add("Found: " + getCharges + ".00");
			app_logs.info("Found: " + getCharges + ".00");
			assertEquals(getCharges + ".00", getRoomCharegsWithTax, "Failed to verify roomcharges: " + getCharges);
			testSteps.add("Verified room charges for " + firstGuestName);

			getCharges = reservation.getChargesInChekoutSuccessPopup(driver, firstGuestName, 2);
			getCharges = reservation.replaceCurrency(getCharges);
			lineItemTaxInString = String.format("%.2f", firstLineItemCategoryTax);
			totalLineItemAmount = folio.addTwoValue(firstLineItemAmount, lineItemTaxInString);
			testSteps.add("Expected Line Item Amount : " + totalLineItemAmount);
			app_logs.info("Expected Line Item Amount : " + totalLineItemAmount);
			testSteps.add("Found: " + getCharges + ".00");
			app_logs.info("Found: " + getCharges + ".00");
			assertEquals(getCharges + ".00", totalLineItemAmount, "Failed to verify line item amount: " + getCharges);
			testSteps.add("Verified line item amount for " + firstGuestName);

			getTestSteps.clear();
			getTestSteps = reservation.closeBulkCheckoutActionPopUp(driver);
			testSteps.addAll(getTestSteps);

			testSteps.add("============VERIFY RESERVATION STATUS AS DEPARTED============");
			app_logs.info("============VERIFY RESERVATION STATUS AS DEPARTED============");

			String reservationStatus = reservation.getReservationSearchStatus(driver, 1);
			testSteps.add("Expected reservation status : Departed");
			app_logs.info("Expected reservation status : Departed");
			testSteps.add("Found: " + reservationStatus);
			app_logs.info("Found: " + reservationStatus);
			assertEquals(reservationStatus, "Departed", "Failed to verify guest name: " + reservationStatus);
			testSteps.add("Verified reservation status for " + firstGuestName);

			reservationStatus = reservation.getReservationSearchStatus(driver, 2);
			testSteps.add("Expected reservation status : "  +"Departed");
			app_logs.info("Expected reservation status : " + "Departed");
			testSteps.add("Found: " + reservationStatus);
			app_logs.info("Found: " + reservationStatus);
			assertEquals(reservationStatus, "Departed", "Failed to verify guest name: " + reservationStatus);
			testSteps.add("Verified reservation status for " + secondGuestName);

			
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to perform bulk checkout action", scriptName, "BulkCheckOut", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to perform bulk checkout action", scriptName, "BulkCheckOut", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			getTestSteps.clear();
			getTestSteps = reservation.clickOnGuestNameInSearchReaservation(driver, firstReservationNumber);
			testSteps.addAll(getTestSteps);
			app_logs.info("Reservation with number (" + firstReservationNumber + ") opened");
			testSteps.add("Reservation with number (" + firstReservationNumber + ") opened");
			
			getTestSteps.clear();
			reservation.click_Folio(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========VERIFY PAYMENT LINE ITEM DETAIL IN GUEST FOLIO==========");
			testSteps.add("==========VERIFY PAYMENT LINE ITEM DETAIL IN GUEST FOLIO==========");
			
			String paymentLineItempDescription = "Name: " + firstGuestName + " Account #: XXXX"
					+ cardNumber.substring(cardNumber.length() - 4) + " Exp. Date: " + cardExpDate;
			app_logs.info("paymentLineItempDescription : " + paymentLineItempDescription);

			getTestSteps.clear();
			getTestSteps = folio.LineItemDate(driver, paymentType, 0, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemCategory(driver, paymentType, 2);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.getDescroption(driver, paymentType, paymentLineItempDescription, false, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemQuentity(driver, paymentType, "0", 1);
			testSteps.addAll(getTestSteps);

			String getAmount = folio.getAmount(driver, paymentType, 1);
			testSteps.add("Expected amount after added tax: " + "$ " + getRoomCharegsWithTax);
			testSteps.add("Found : " + getAmount);
			assertEquals("$ " + getRoomCharegsWithTax, getAmount, "Failed: Amount is mismatching " + getAmount);
			testSteps.add("Verified amount after included tax");
			
			String folioTotalCharges = folio.getTotalCharges(driver);
			folioTotalCharges = reservation.replaceCurrency(folioTotalCharges);
			String folioPayment = folio.getPaymentAmount(driver);
			folioPayment = reservation.replaceCurrency(folioPayment);
			String expectedFolioBalance = folio.MinseTwoValue(folioTotalCharges, folioPayment);
			String folioBalance = folio.getToatalBalance(driver);
			folioBalance = reservation.replaceCurrency(folioBalance);
			testSteps.add("Expected folio balance : "  +expectedFolioBalance);
			app_logs.info("Expected Line Item Amount : " + expectedFolioBalance);
			testSteps.add("Found: " + folioBalance);
			app_logs.info("Found: " + folioBalance);
			assertEquals(folioBalance, expectedFolioBalance, "Failed to verify balance amount : " + folioBalance);
			testSteps.add("Verified folio balance");
			app_logs.info("Verified folio balance");
			
			getTestSteps.clear();
			getTestSteps = folio.changeFolioOption(driver, accountName);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========VERIFY PAYMENT LINE ITEM DETAIL IN ACCOUNT FOLIO==========");
			testSteps.add("==========VERIFY PAYMENT LINE ITEM DETAIL IN ACCOUNT FOLIO==========");

			getTestSteps.clear();
			getTestSteps = folio.LineItemDate(driver, paymentType, 0, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemCategory(driver, paymentType, 2);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.getDescroption(driver, paymentType, paymentLineItempDescription, false, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemQuentity(driver, paymentType, "0", 1);
			testSteps.addAll(getTestSteps);

			getAmount = folio.getAmount(driver, paymentType, 1);
			testSteps.add("Expected amount after added tax: " +  getFirstLineItemAmount);
			testSteps.add("Found : " + getAmount);
			assertEquals(getFirstLineItemAmount, getAmount, "Failed: Amount is mismatching " + getAmount);
			testSteps.add("Verified amount after included tax");

			folioTotalCharges = folio.getTotalCharges(driver);
			folioTotalCharges = reservation.replaceCurrency(folioTotalCharges);
			folioPayment = folio.getPaymentAmount(driver);
			folioPayment = reservation.replaceCurrency(folioPayment);
			expectedFolioBalance = folio.MinseTwoValue(folioTotalCharges, folioPayment);
			folioBalance = folio.getToatalBalance(driver);
			folioBalance = reservation.replaceCurrency(folioBalance);
			testSteps.add("Expected folio balance : "  +expectedFolioBalance);
			app_logs.info("Expected Line Item Amount : " + expectedFolioBalance);
			testSteps.add("Found: " + folioBalance);
			app_logs.info("Found: " + folioBalance);
			assertEquals(folioBalance, expectedFolioBalance, "Failed to verify balance amount : " + folioBalance);
			testSteps.add("Verified folio balance");
			app_logs.info("Verified folio balance");

			app_logs.info("VERIFY ROLL BACK IN  RESERVATION WITH NUMBER (" + firstReservationNumber + ")");
			testSteps.add("VERIFY ROLL BACK IN  RESERVATION WITH NUMBER (" + firstReservationNumber + ")");
			
			getTestSteps.clear();
			reservation.click_RollBackButton(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			String getPopUpText = reservation.getTextFromRollBackChangesPopUp(driver);
			testSteps.add("Found : " + getPopUpText);
			testSteps.add("Expected : " + rollBackText);
			assertEquals(getPopUpText, rollBackText);
			testSteps.add("Verified roll back popup text");

			
			getTestSteps.clear();
			reservation.acceptRollBackChange(driver);
			testSteps.addAll(getTestSteps);

			testSteps.add("============VERIFY RESRAVTION STATUS AS CONFIRMED============");
			app_logs.info("============VERIFY RESRAVTION STATUS AS CONFIRMED============");
			Wait.wait10Second();
			String reservationStatus = reservation.getReservationStatusInDetailsSection(driver);
			testSteps.add("Expected reservation status : "  + firstReservationStatus);
			app_logs.info("Expected reservation status : " + firstReservationStatus);
			testSteps.add("Found: " + reservationStatus);
			app_logs.info("Found: " + reservationStatus);
			assertEquals(reservationStatus, firstReservationStatus, "Failed to verify reservation status: " + reservationStatus);
			testSteps.add("Verified reservation status for " + firstGuestName);
			//verify status and balance amount changes to previous 
			
			reservation.closeReservationTab(driver);
			testSteps.add("Successfully close opened tab");
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to perform roll back in first reservation", scriptName, "RollBack", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to perform roll back in first reservation", scriptName, "RollBack", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
			try{
				
	
			reservationSearch.multipleSearchReservationNumber(driver, allReservation);
			testSteps.add("Successfully Search with Multiple Reservation Numbers");

			getTestSteps.clear();
			getTestSteps = reservation.clickOnGuestNameInSearchReaservation(driver, secondReservationNumber);
			testSteps.addAll(getTestSteps);
			app_logs.info("Reservation with number (" + secondReservationNumber + ") opened");
			testSteps.add("Reservation with number (" + secondReservationNumber + ") opened");
			
			getTestSteps.clear();
			reservation.click_Folio(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========VERIFY PAYMENT LINE ITEM DETAIL IN GUEST FOLIO==========");
			testSteps.add("==========VERIFY PAYMENT LINE ITEM DETAIL IN GUEST FOLIO==========");

			String paymentLineItempDescriptionTwo = "Name: " + firstGuestName + " Account #: XXXX"
					+ cardNumber.substring(cardNumber.length() - 4) + " Exp. Date: " + cardExpDate;
			app_logs.info("paymentLineItempDescriptionTwo : " + paymentLineItempDescriptionTwo);

			getTestSteps.clear();
			getTestSteps = folio.LineItemDate(driver, paymentType, 0, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemCategory(driver, paymentType, 2);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.getDescroption(driver, paymentType, paymentLineItempDescriptionTwo, false, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemQuentity(driver, paymentType, "0", 1);
			testSteps.addAll(getTestSteps);

			String getAmount = folio.getAmount(driver, paymentType, 1);
			testSteps.add("Expected amount after added tax: " + "$ " + getSecondRoomCharegsWithTax);
			testSteps.add("Found : " + getAmount);
			assertEquals("$ " + getSecondRoomCharegsWithTax, getAmount, "Failed: Amount is mismatching " + getAmount);
			testSteps.add("Verified amount after included tax");
			
			String folioTotalCharges = folio.getTotalCharges(driver);
			folioTotalCharges = reservation.replaceCurrency(folioTotalCharges);
			String folioPayment = folio.getPaymentAmount(driver);
			folioPayment = reservation.replaceCurrency(folioPayment);
			String expectedFolioBalance = folio.MinseTwoValue(folioTotalCharges, folioPayment);
			String folioBalance = folio.getToatalBalance(driver);
			folioBalance = reservation.replaceCurrency(folioBalance);
			testSteps.add("Expected folio balance : "  +expectedFolioBalance);
			app_logs.info("Expected Line Item Amount : " + expectedFolioBalance);
			testSteps.add("Found: " + folioBalance);
			app_logs.info("Found: " + folioBalance);
			assertEquals(folioBalance, expectedFolioBalance, "Failed to verify balance amount : " + folioBalance);
			testSteps.add("Verified folio balance");
			app_logs.info("Verified folio balance");

			
			getTestSteps.clear();
			getTestSteps = folio.changeFolioOption(driver, accountName);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========VERIFY PAYMENT LINE ITEM DETAIL IN ACCOUNT FOLIO==========");
			testSteps.add("==========VERIFY PAYMENT LINE ITEM DETAIL IN ACCOUNT FOLIO==========");

			getTestSteps.clear();
			getTestSteps = folio.LineItemDate(driver, paymentType, 0, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemCategory(driver, paymentType, 2);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.getDescroption(driver, paymentType, paymentLineItempDescriptionTwo, false, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemQuentity(driver, paymentType, "0", 1);
			testSteps.addAll(getTestSteps);

			getAmount = folio.getAmount(driver, paymentType, 1);
			testSteps.add("Expected amount after added tax: " + getSecondLineItemAmount);
			testSteps.add("Found : " + getAmount);
			assertEquals(getSecondLineItemAmount, getAmount, "Failed: Amount is mismatching " + getAmount);
			testSteps.add("Verified amount after included tax");

			folioTotalCharges = folio.getTotalCharges(driver);
			folioTotalCharges = reservation.replaceCurrency(folioTotalCharges);
			folioPayment = folio.getPaymentAmount(driver);
			folioPayment = reservation.replaceCurrency(folioPayment);
			expectedFolioBalance = folio.MinseTwoValue(folioTotalCharges, folioPayment);
			folioBalance = folio.getToatalBalance(driver);
			folioBalance = reservation.replaceCurrency(folioBalance);
			testSteps.add("Expected folio balance : "  +expectedFolioBalance);
			app_logs.info("Expected Line Item Amount : " + expectedFolioBalance);
			testSteps.add("Found: " + folioBalance);
			app_logs.info("Found: " + folioBalance);
			assertEquals(folioBalance, expectedFolioBalance, "Failed to verify balance amount : " + folioBalance);
			testSteps.add("Verified folio balance");
			app_logs.info("Verified folio balance");

			app_logs.info("VERIFY ROLL BACK IN  RESERVATION WITH NUMBER (" + secondReservationNumber + ")");
			testSteps.add("VERIFY ROLL BACK IN  RESERVATION WITH NUMBER (" + secondReservationNumber + ")");
			
			getTestSteps.clear();
			reservation.click_RollBackButton(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			String getPopUpText = reservation.getTextFromRollBackChangesPopUp(driver);
			testSteps.add("Found : " + getPopUpText);
			testSteps.add("Expected : " + rollBackText);
			assertEquals(getPopUpText, rollBackText);
			testSteps.add("Verified roll back popup text");

			getTestSteps.clear();
			reservation.acceptRollBackChange(driver);
			testSteps.addAll(getTestSteps);

			testSteps.add("============VERIFY RESRAVTION STATUS AS CONFIRMED============");
			app_logs.info("============VERIFY RESRAVTION STATUS AS CONFIRMED============");

			Wait.wait10Second();
			String reservationStatus = reservation.getReservationStatusInDetailsSection(driver);
			testSteps.add("Expected reservation status : "  + secondReservationStatus);
			app_logs.info("Expected reservation status : " + secondReservationStatus);
			testSteps.add("Found: " + reservationStatus);
			app_logs.info("Found: " + reservationStatus);
			assertEquals(reservationStatus, secondReservationStatus, "Failed to verify guest name: " + reservationStatus);
			testSteps.add("Verified reservation status for " + secondGuestName);
			
			reservation.closeReservationTab(driver);
			testSteps.add("Successfully close opened tab");

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to perform roll back in second reservation", scriptName, "RollBack", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to perform roll back in second reservation", scriptName, "RollBack", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			testSteps.add("==========DELETE RATE==========");

			navigation.Inventory(driver);
			testSteps.add("Navigate to Inventory");
			app_logs.info("Navigate to Inventory");

			getTestSteps.clear();
			getTestSteps = navigation.secondaryRatesMenuItem(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.deleteRates(driver, rateName);
			testSteps.addAll(getTestSteps);
			testSteps.add("New Rate has been Deleted successfully");
			app_logs.info("New Rate has been Deleted successfully");

			rate.verifyDeleteRate(driver, rateName);
			testSteps.add("Verify the Deleted Rate : " + rateName);
			app_logs.info("Verify the Deleted Rate " + rateName);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to delete a rate ", scriptName, "DeleteRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to delete a rate ", scriptName, "DeleteRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Delete Room Class try
		try {
			testSteps.add("==========DELETE ROOM CLLASS==========");

			navigation.Setup(driver);
			testSteps.add("Click on setup");

			navigation.RoomClass(driver);
			testSteps.add("Click on room classes");
			roomClass.searchClass(driver, tempraryRoomClass);
			app_logs.info("Search");
			getTestSteps.clear();
			getTestSteps = roomClass.deleteRoomClass(driver, tempraryRoomClass);
			testSteps.addAll(testSteps);
			testSteps.add("Delete room class successfully");
			app_logs.info("Delete room class successfully");
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);

		}

		catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to delete room class", scriptName, "RoomClasses", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to delete room class", scriptName, "RoomClasses", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {

		return Utility.getData("VerifyBulkCheckoutMultiFolio", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		// driver.quit();

	}
}
