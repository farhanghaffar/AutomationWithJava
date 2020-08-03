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
import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Folio;
import com.innroad.inncenter.pageobjects.GuestHistory;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Policies;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyReservationCreationWhileInitialPaymentMethodInvalid extends TestCore {

	private WebDriver driver = null;

	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();
	ArrayList<String> scriptName = new ArrayList<>();
	ArrayList<String> testCategory = new ArrayList<>();
	ArrayList<String> testDescription = new ArrayList<>();


	public static String test_description = "";
	public static String test_catagory = "";

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {

		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Inventory")
	public void verifyReservationCreationWhileInitialPaymentMethodInvalid(String rateName, String baseAmount, String addtionalAdult,
			String additionalChild, String displayName, String associateSeason, String ratePolicy,
			String rateDescription, String roomClassAbbreviation, String roomClassName, String bedsCount,
			String maxAdults, String maxPersons, String roomQuantity, String startRoomNumber, String ratePlan,
			String rateType, String rateAttributes, String interval, String source, String adults, String child,
			String marketSegment, String referral,String phone, String address, String address2,String address3,String city, String country,
			String state, String postalCode, String roomPerNight, String saluation, String firstName,	String lastName, String roomCharge,
			String guestFolio, String IsAssign, String isChecked, String policyType,String depositPolicyName, String policyAmount,
			String paymentType, String invalidCardNumber, String cardNumber, String nameOnCard, String HistoryCategory, String altenativePhone,String email,String account,String accountType,String isGuesProfile, String displayVoidItemsCheckBox) throws InterruptedException, IOException {

		String testName = "VerifyReservationCreationWhileInitialPaymentMethodInvalid";
		test_description = "Verify creating reservation if initial payment method is invalid<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682524' target='_blank'>"
				+ "Click here to open TestRail: C682524</a>";
		test_catagory = "GuestProfile";
		scriptName.add(testName);
		testDescription.add(test_description);
		testCategory.add(test_catagory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Rate rate = new Rate();
		Navigation navigation = new Navigation();
		Policies policies = new Policies();
		CPReservationPage reservationPage = new CPReservationPage();
		Folio folio = new Folio();
		RoomClass roomClass = new RoomClass();
		GuestHistory guestHistory = new GuestHistory();

		
		String tempraryRoomClassName = roomClassName;
		String tempraryRateName = rateName;
		String randomNumber = Utility.GenerateRandomNumber();
		roomClassName = roomClassName + randomNumber;
		roomClassAbbreviation = roomClassAbbreviation + randomNumber;
		rateName = rateName + randomNumber;
		String depositPolicyNameWithoutNum = depositPolicyName;
		String policyText = ratePolicy + "_Text";
		String policyDesc = ratePolicy + "_Description";
		ratePolicy = ratePolicy + randomNumber;
		depositPolicyName = depositPolicyName + randomNumber;
		String policyFor = "Room Charges";
		displayName = rateName;		
		lastName = lastName + randomNumber;
		String reservationNumber = null;
		String roomNumber = null;
		String guestProfileccountNumber = null;
		String paymentLineItempDescription = null;
		String tripSummaryRoomCharges = null, tripSummaryTaxes = null, tripSummaryTripTotal = null,tripSummaryBalance = null;

		String timeZone = "America/New_York";
		String cardExpDate= Utility.getNextDate(365, "MM/yy", timeZone);
		app_logs.info("Card Expiry " + cardExpDate);


		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);

			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver = getDriver();
			login_CP(driver);
			testSteps.add("Logged into the application ");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		
		try {

			app_logs.info("==========CREATE A NEW ROOM CLASS==========");
			testSteps.add("==========CREATE A NEW ROOM CLASS==========");

			navigation.mainSetupManu(driver);
			app_logs.info("Navigate Setup");
			testSteps.add("Navigate Setup");

			navigation.RoomClass(driver);
			app_logs.info("Navigate Room Class");
			testSteps.add("Navigate Room Class");

			boolean isRoomClassExist = roomClass.searchClass(driver, tempraryRoomClassName);
			app_logs.info("Search");
			if (isRoomClassExist) {
				app_logs.info("Deleting room class with name " + tempraryRoomClassName);
				testSteps.add("Deleting room class with name " + tempraryRoomClassName);
				getTestSteps.clear();
				getTestSteps = roomClass.deleteRoomClass(driver, tempraryRoomClassName);
				testSteps.addAll(getTestSteps);

			}
			
			navigation.clickOnNewRoomClass(driver);
			getTestSteps.clear();
			getTestSteps = roomClass.createRoomClass(driver, roomClassName, roomClassAbbreviation, bedsCount, maxAdults,
					maxPersons, roomQuantity, test, getTestSteps);
			testSteps.addAll(getTestSteps);
			
			testSteps.add("Sccessfully Created New RoomClass " + roomClassName + " Abb : " + roomClassAbbreviation);
			app_logs.info("Sccessfully Created New RoomClass" + roomClassName + " Abb : " + roomClassAbbreviation);

			roomClass.closeRoomClassTab(driver);
			testSteps.add("Close created room class tab");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create room class successfully", testName, "NewRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create room class successfully", testName, "NewRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		
		try {
			testSteps.add("==========CREATE A NEW RATE==========");
			app_logs.info("==========CREATE A NEW RATE==========");

			navigation.Inventory(driver);
			app_logs.info("Navigate Inventory");
			testSteps.add("Navigate Inventory");

			getTestSteps.clear();
			getTestSteps = navigation.secondaryRatesMenuItem(driver);
			testSteps.addAll(getTestSteps);

			app_logs.info("Deleting rate with name " + tempraryRoomClassName  + "  if exist");
			testSteps.add("Deleting rate with name " + tempraryRoomClassName+ "  if exist");
			
			getTestSteps.clear();
			getTestSteps = rate.deleteRateIfExist(driver, tempraryRateName, getTestSteps);
			testSteps.addAll(getTestSteps);
			
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
			getTestSteps = rate.EnterAdditionalAdult(driver, addtionalAdult);
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
			getTestSteps = rate.AssociateSeason(driver, associateSeason);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.AssociateRoomClass(driver, roomClassName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.AssociateSource(driver, source);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.Save_DoneRate(driver);
			testSteps.addAll(getTestSteps);

			rate.SearchRate(driver, rateName, false);
			testSteps.add("New Rate '" + rateName + "' Created & Verified ");
			app_logs.info("New Rate '" + rateName + "' Created & Verified");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create new rate", testName, "CreateNewRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create new rate", testName, "CreateNewRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		
		try {

			app_logs.info("========== CREATING NEW DEPOSIT POLICY ==========");
			testSteps.add("========== CREATING NEW DEPOSIT POLICY ==========");
			
			getTestSteps.clear();
			getTestSteps = navigation.Inventory(driver, getTestSteps);
			testSteps.addAll(getTestSteps);
			
			getTestSteps.clear();
			getTestSteps = navigation.policies(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			app_logs.info("Deleting policy with name " + depositPolicyNameWithoutNum  + "  if exist");
			testSteps.add("Deleting policy with name " + depositPolicyNameWithoutNum+ "  if exist");

			getTestSteps.clear();
			getTestSteps = policies.deleteAllPolicies(driver, getTestSteps, policyType, depositPolicyNameWithoutNum);
			testSteps.addAll(getTestSteps);
			
			
			getTestSteps.clear();
			getTestSteps = policies.createPolicy(driver, getTestSteps, depositPolicyName, policyType, policyFor, policyAmount, null, null,
					source, associateSeason, roomClassName, ratePlan, policyText, policyDesc);
			testSteps.addAll(getTestSteps);
			
			getTestSteps.clear();
			getTestSteps = policies.closeOpenedPolicyTab(driver, getTestSteps);
			testSteps.addAll(getTestSteps);
			
			getTestSteps.clear();
			getTestSteps = policies.verifySearchToaster(driver, getTestSteps, depositPolicyName, true);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create a new deposit policy", testName, "PolicyCreation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create a new deposit policy", testName, "PolicyCreation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

			try {
			testSteps.add("==========CREATE NEW RESERVATION==========");
			app_logs.info("==========CREATE NEW RESERVATION==========");

			navigation.navigateToReservations(driver);
			testSteps.add("Navigate to reservations");
			app_logs.info("Navigate to reservations");

			
			getTestSteps.clear();
			getTestSteps = reservationPage.click_NewReservation(driver, getTestSteps);
			testSteps.addAll(getTestSteps);
			
			getTestSteps.clear();
			getTestSteps = reservationPage.checkInDate(driver, 0);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.checkOutDate(driver, +1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterAdult(driver, adults);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterChildren(driver, child);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnFindRooms(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectRoom(driver, getTestSteps, roomClassName, IsAssign, account);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enter_MailingAddress(driver, getTestSteps, saluation, firstName, lastName,phone,altenativePhone,email,account,accountType,address, address2, address3,city,country,state,postalCode,isGuesProfile);
			testSteps.addAll(getTestSteps);
			
			getTestSteps.clear();
			getTestSteps = reservationPage.enter_PaymentDetails(driver, getTestSteps, paymentType, invalidCardNumber, nameOnCard, cardExpDate);
			testSteps.addAll(getTestSteps);
			
			getTestSteps.clear();
			getTestSteps = reservationPage.selectReferral(driver, referral);
			testSteps.addAll(getTestSteps);
			
			getTestSteps.clear();
			getTestSteps = reservationPage.clickBookNow(driver, getTestSteps);
			testSteps.addAll(getTestSteps);
			
			getTestSteps.clear();
			getTestSteps = reservationPage.getDepositPaymentPopupHeading(driver);
			testSteps.addAll(getTestSteps);
			

			getTestSteps.clear();
			getTestSteps = reservationPage.getDepositPaymentPopupTransactionDecline(driver);
			testSteps.addAll(getTestSteps);
			
			testSteps.add("Expected Deposit Policy : " + depositPolicyName);
			app_logs.info("Expected Deposit Policy : " + depositPolicyName);
			String actualDepositPolicy = reservationPage.getDepositPaymentPopupPolicy(driver);
			testSteps.add("Found : " + actualDepositPolicy);
			app_logs.info("Found : " + actualDepositPolicy);		
			testSteps.add("Verified deposit policy");
			app_logs.info("Verified deposit policy");		
			
			String depositBalance = reservationPage.getDepositPaymentPopupBalance(driver);
			testSteps.add("Balance : " + depositBalance);
			app_logs.info("Balance : " + depositBalance);
			
			String depositAmount = reservationPage.getDepositPaymentPopupAmount(driver);
			testSteps.add("Deposit Amount : " + depositAmount);
			app_logs.info("Deposit Amount : " + depositAmount);	
			
			getTestSteps.clear();
			getTestSteps = reservationPage.clickCancelDepositPaymentPopupButton(driver);
			testSteps.addAll(getTestSteps);
			
			
			getTestSteps.clear();
			getTestSteps = reservationPage.clickSaveAsQuoteButton(driver);
			testSteps.addAll(getTestSteps);
			
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create a reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create a reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		
	try{
		
			reservationNumber = reservationPage.getReservationNumberOnDetailSection(driver);
			testSteps.add("Reservation number : " + reservationNumber);
			app_logs.info("Reservation number : " + reservationNumber);
		
			String reservationStatus = reservationPage.getReservationStatusOnDetailSection(driver);
			testSteps.add("Expected reservation status : " + "QUOTE");
			app_logs.info("Expected reservation status : " + "QUOTE");
			testSteps.add("Found : " + reservationStatus);
			app_logs.info("Found : " + reservationStatus);
			assertEquals(reservationStatus, "QUOTE", "Failed to verify reservation status");			
			testSteps.add("Verify reservation status");
			app_logs.info("Verify reservation status");
			
			roomNumber = reservationPage.getRoomNumberDetail(driver);
			app_logs.info("Room Number is : " + roomNumber);
			testSteps.add("Room number for quote reservation : " + roomNumber);
			tripSummaryRoomCharges = reservationPage.getRoomChargesInTripSummary(driver);
			tripSummaryRoomCharges = reservationPage.replaceCurrency(tripSummaryRoomCharges);
			tripSummaryTaxes = reservationPage.getTaxandServicesInTripSummary(driver);
			tripSummaryTaxes = reservationPage.replaceCurrency(tripSummaryTaxes);
			//getIncidentalsInTripSummary
			String tripSummaryIncidental = reservationPage.getIncidentalsInTripSummary(driver);
			testSteps.add("TripSummary Incidentals : " + tripSummaryIncidental);
			app_logs.info("TripSummary Incidentals : " + tripSummaryIncidental);
			
			tripSummaryTripTotal = reservationPage.get_TripSummaryTripTotalChargesWithCurrency(driver, testSteps);
			tripSummaryTripTotal = reservationPage.replaceCurrency(tripSummaryTripTotal);

			
			Double expectedDepositAmount = reservationPage.calculationDepositePercenatge(tripSummaryTripTotal,policyAmount);
			testSteps.add("Expected Deposit Amount : " + expectedDepositAmount);
			app_logs.info("Expected Deposit Amount : " + expectedDepositAmount);
			String depositDue = reservationPage.getDepositDue(driver);
			depositDue = reservationPage.replaceCurrency(depositDue);
			
			Double depositDueInDouble = Double.parseDouble(depositDue);
			testSteps.add("Found : " + depositDueInDouble);
			app_logs.info("Found : " + depositDueInDouble);
			assertEquals(depositDueInDouble, expectedDepositAmount, "Failed to verify deposit amount");
			testSteps.add("Verified deposit amount");
			app_logs.info("Verified deposit amount");

			tripSummaryBalance = reservationPage.get_TripSummaryBalanceWithCurrency(driver, testSteps);
			tripSummaryBalance = reservationPage.replaceCurrency(tripSummaryBalance);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify deposit amount", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify deposit amount", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}		
		try {
			testSteps.add("==========VERIFICATION OF RESERVATION IN HISTORY==========");
			app_logs.info("==========VERIFICATION OF RESERVATION IN HISTORY==========");

			getTestSteps.clear();
			getTestSteps = reservationPage.ClickOnHistory(driver);
			testSteps.addAll(getTestSteps);

			String gettHistoryDescription = reservationPage.getHistoryDescription(driver, 0);
			String description = "Saved quote with Confirmation Number: " + reservationNumber;
			testSteps.add("Expected description: " + description);
			app_logs.info("Expected description: " + description);
			testSteps.add("Found: " + gettHistoryDescription);
			app_logs.info("Found: " + gettHistoryDescription);
			assertEquals(gettHistoryDescription, description, "Failed: History description is mismatching!");
			testSteps.add("Verified description");
			app_logs.info("Verified description");

			testSteps.add("Verified that declined payment is not displaying in history tab");
			app_logs.info("Verified that declined payment is not displaying in history tab");

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify declined payment in history", testName, "History", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify declined payment in history", testName, "History", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			folio.FolioTab(driver);
			app_logs.info("Click Folio Tab");
			testSteps.add("Click Folio Tab");
		
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to navigate to folio", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to navigate to folio", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			app_logs.info("==========VERIFY LINE ITEM DETAIL IN GUEST FOLIO==========");
			testSteps.add("==========VERIFY LINE ITEM DETAIL IN GUEST FOLIO==========");

			getTestSteps.clear();
			getTestSteps = folio.LineItemDate(driver, roomCharge, 0, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemCategory(driver, roomCharge, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.getDescroption(driver, roomCharge, rateName, false, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemQuentity(driver, roomCharge, "1", 1);
			testSteps.addAll(getTestSteps);

			String getTax = folio.getTax(driver, roomCharge);
			String totalAmountWithTax = folio.AddValue(tripSummaryRoomCharges, getTax);
			totalAmountWithTax = "$ " + totalAmountWithTax;

			String getAmount = folio.getAmount(driver, roomCharge, 1);
			testSteps.add("Expected amount after added tax: " + totalAmountWithTax);
			testSteps.add("Found : " + getAmount);
			assertEquals(getAmount, totalAmountWithTax, "Failed: Amount is mismatching!");
			testSteps.add("Verified amount after included tax");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Verify Folio Options", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Verify Folio Options", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			app_logs.info("==========VERIFY PAYMENT LINE ITEM DETAIL IN GUEST FOLIO==========");
			testSteps.add("==========VERIFY PAYMENT LINE ITEM DETAIL IN GUEST FOLIO==========");

			getTestSteps.clear();
			getTestSteps = folio.clickFolioDetailsCheckBoxes(driver, displayVoidItemsCheckBox, true);
			testSteps.addAll(getTestSteps);

			paymentLineItempDescription = "Name: " + nameOnCard + " Account #: XXXX"
					+ invalidCardNumber.substring(invalidCardNumber.length() - 4) + " Exp. Date: " + cardExpDate;
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

			String getTax = folio.getTax(driver, paymentType);
			String totalAmountWithTax = folio.AddValue("0.00", getTax);
			totalAmountWithTax = "$ " + totalAmountWithTax;

			String getAmount = folio.getAmount(driver, paymentType, 1);
			testSteps.add("Expected amount after added tax: " + totalAmountWithTax);
			app_logs.info("Expected amount after added tax: " + totalAmountWithTax);
			testSteps.add("Found : " + getAmount);
			app_logs.info("Found : " + getAmount);
			assertEquals(getAmount, totalAmountWithTax, "Failed: Amount is mismatching!");
			testSteps.add("Verified amount after included tax");
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify payment line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify payment line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			app_logs.info("==========UPDATING CARD NUMBER ==========");
			testSteps.add("==========UPDATING CARD NUMBER ==========");

			getTestSteps.clear();
			getTestSteps = reservationPage.click_DeatilsTab(driver, getTestSteps);
			testSteps.addAll(getTestSteps);
			
			getTestSteps.clear();
			getTestSteps = reservationPage.updateCCNumberNumber(driver, cardNumber);
			testSteps.addAll(getTestSteps);


			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to update card number", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to update card number", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			app_logs.info("==========SEARCHING GUEST PROFILE IN GUEST HISTORY==========");
			testSteps.add("==========SEARCHING GUEST PROFILE IN GUEST HISTORY==========");

			navigation.navigateToReservations(driver);
			testSteps.add("Navigate to reservations");
			app_logs.info("Navigate to reservations");
			
			navigation.GuestHistory(driver);
			testSteps.add("Navigate to guest history");
			app_logs.info("Navigate to guest history");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to naviagte to guest history", testName, "GuestHistory", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to naviagte to guest history", testName, "GuestHistory", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			getTestSteps.clear();
			getTestSteps = guestHistory.searchAccount(driver, firstName, lastName);
			testSteps.addAll(getTestSteps);

			guestProfileccountNumber = guestHistory.getAccountNumber(driver);
			testSteps.add("Guest profile account number : " + guestProfileccountNumber);
			app_logs.info("Guest profile account number : " + guestProfileccountNumber);
			
			getTestSteps.clear();
			getTestSteps = guestHistory.openSearchedGuestAccount(driver);
			testSteps.addAll(getTestSteps);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to open searched guest profile", testName, "GuestHistory", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to open searched guest profile", testName, "GuestHistory", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			app_logs.info("========== VERIFYING UPDATED CRAD NUMBER IN GUEST PROFILE ==========");
			testSteps.add("========== VERIFYING UPDATED CRAD NUMBER IN GUEST PROFILE ==========");
			
			getTestSteps.clear();
			getTestSteps = guestHistory.verifyCardNumber(driver, cardNumber);
			testSteps.addAll(getTestSteps);
		
			getTestSteps.clear();
			getTestSteps = guestHistory.clickSave(driver);
			testSteps.addAll(getTestSteps);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify card number in guest history", testName, "GuestHistory", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to  verify card number in guest history", testName, "GuestHistory", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		
		try {
			testSteps.add("===================== Deleting policy =====================");
			getTestSteps.clear();
			getTestSteps = navigation.Inventory(driver, getTestSteps);
			testSteps.addAll(getTestSteps);
			
			getTestSteps.clear();
			getTestSteps = navigation.policies(driver, getTestSteps);
			testSteps.addAll(getTestSteps);
			
			getTestSteps.clear();
			getTestSteps = policies.deleteAllPolicies(driver, getTestSteps, policyType, depositPolicyNameWithoutNum);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete policy", testName, "Policy",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete policy", testName, "Policy",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Delete rate
		try {
			app_logs.info("==========DELETE RATE==========");
			testSteps.add("==========DELETE RATE==========");
			
			navigation.Inventory(driver);
			app_logs.info("Navigate Inventory");
			testSteps.add("Navigate Inventory");
			
			navigation.secondaryRatesMenuItem(driver);
			app_logs.info("Navigate Rate");
			testSteps.add("Navigate Rate");

			getTestSteps.clear();
			getTestSteps = rate.deleteRates(driver, tempraryRateName);
			testSteps.addAll(getTestSteps);
			
			testSteps.add("New Rate has been Deleted successfully");
			app_logs.info("New Rate has been Deleted successfully");

			getTestSteps.clear();
			getTestSteps = rate.verifyDeleteRate(driver, tempraryRateName);
			testSteps.addAll(getTestSteps);
			testSteps.add("Verify the Deleted Rate : " + tempraryRateName);
			app_logs.info("Verify the Deleted Rate " + tempraryRateName);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Delete rate", testName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Delete rate", testName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Delete Room Class
		try {

			app_logs.info("==========DELETE ROOM CLASS==========");
			testSteps.add("==========DELETE ROOM CLASS==========");

			navigation.mainSetupManu(driver);
			app_logs.info("Navigate Setup");
			testSteps.add("Navigate Setup");

			navigation.RoomClass(driver);
			app_logs.info("Navigate Room Class");
			testSteps.add("Navigate Room Class");

			boolean isRoomClassExist = roomClass.searchClass(driver, tempraryRoomClassName);
			app_logs.info("Search");
			if (isRoomClassExist) {

				getTestSteps.clear();
				getTestSteps = roomClass.deleteRoomClass(driver, tempraryRoomClassName);
				testSteps.addAll(getTestSteps);

			}
			testSteps.add("Deleted Room Classes : " + tempraryRoomClassName);
			app_logs.info("Deleted Room Classes :  " + tempraryRoomClassName);
		
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete room class", testName, "RoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete room class", testName, "RoomClass", driver);
			}
		}
		
	}

	@DataProvider
	public Object[][] getData() {
		return Utility.getData("VerifyResInvalidPaymentMethod", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		// driver.quit();
	}
}
