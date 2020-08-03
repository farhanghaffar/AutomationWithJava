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
import com.innroad.inncenter.pageobjects.Folio;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Policies;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyNoShowPolicyWithOneNightCharge extends TestCore {

	private WebDriver driver = null;
	ArrayList<String> test_catagory = new ArrayList<>();
	ArrayList<String> test_description = new ArrayList<>();
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> test_name = new ArrayList<>();
	ArrayList<String> totalNights = new ArrayList<>();
	ArrayList<String> roomBaseAmount = new ArrayList<>();
	ArrayList<String> roomNumber = new ArrayList<>();
	ArrayList<String> getRoomNumbers = new ArrayList<>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();

	Double depositAmount = 0.0;
	Double paidDeposit = 0.0;
	Navigation navigation = new Navigation();
	Folio folio = new Folio();
	String testName = this.getClass().getSimpleName().trim();

	String roomClassNameWithoutNum, rateNameWithoutNum, policyNameWithoutNum, typeToValidate, policyDesc, policyText,
			taxes, reservation = null, status = null, tripTotal = null, roomNumber1 = null;

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Policy")
	public void verifyNoShowPolicyWithOneNightCharge(String roomClassName, String roomClassAbb, String maxAdults,
			String maxPersons, String roomQuantity, String rateName, String ratePlan, String baseAmount,
			String additionalAdult, String additionalChild, String displayName, String ratePolicy,
			String rateDescription, String associateSeason, String policyName, String policyType, String policyAttr1,
			String policyAttrValue1, String policyAttr2, String policyAttrValue2, String source, String policySeason,
			String checkInDate, String checkOutDate, String adultsForRes, String childrenForRes, String salutation,
			String guestFirstName, String guestLastName, String phoneNumber, String altenativePhone, String email,
			String account, String accountType, String address1, String address2, String address3, String city,
			String country, String state, String postalCode, String isGuesProfile, String paymentMethod,
			String cardNumber, String nameOnCard, String cardExpDate, String referral, String cancelReason,
			String paymentType) throws Exception {

		String testDescription = "Verify No Show policy for 1 night room charges for single reservation<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682501' target='_blank'>"
				+ "Click here to open TestRail: C682501</a><br/>";
		String testCatagory = "Policies";

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Policies policies = new Policies();
		CPReservationPage reservationPage = new CPReservationPage();
		Navigation navigation = new Navigation();
		Rate rate = new Rate();
		RoomClass roomClass = new RoomClass();

		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);
			} else {
				Utility.reTry.replace(testName, 1);
			}
			String randomNum = Utility.GenerateRandomNumber();
			roomClassNameWithoutNum = roomClassName;
			roomClassName = roomClassName + randomNum;
			roomClassAbb = roomClassAbb + randomNum;
			rateNameWithoutNum = rateName;
			rateName = rateName + randomNum;

			policyNameWithoutNum = policyName;
			policyName = policyName + randomNum;
			policyText = policyName + "_Text";
			policyDesc = policyName + "_Description";

			guestLastName = guestLastName + randomNum;
			nameOnCard = guestFirstName + " " + guestLastName;
			roomBaseAmount.add(baseAmount);
			if (!(Utility.validateInput(checkInDate))) {
				for (int i = 0; i < guestFirstName.split("\\|").length; i++) {
					checkInDates.add(Utility.getCurrentDate("dd/MM/yyyy"));
					checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(2), "MM/dd/yyyy", "dd/MM/yyyy"));
				}
			} else {
				checkInDates = Utility.splitInputData(checkInDate);
				checkOutDates = Utility.splitInputData(checkOutDate);
			}
			for (int i = 0; i < checkInDates.size(); i++) {
				totalNights.add(Utility.differenceBetweenDates(checkInDates.get(i), checkOutDates.get(i)));
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to update test data before executing test scripts", testName,
						"Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to update test data before executing test scripts", testName,
						"Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			driver = getDriver();
			login_CP(driver, test_steps);
			navigation.Inventory(driver);
			navigation.Rate(driver);
			rate.deleteRates(driver, rateNameWithoutNum);
			app_logs.info("========== Creating a new room class ==========");
			test_steps.add("========== Creating a new room class ==========");
			navigation.Setup(driver);
			navigation.RoomClass(driver);
			roomClass.SearchClasses(driver, roomClassNameWithoutNum);
			roomClass.deleteRoomClass(driver, roomClassNameWithoutNum);
			navigation.clickOnNewRoomClassButton(driver, test_steps);
			roomClass.CreateRoomClass(driver, roomClassName, roomClassAbb, null, maxAdults, maxPersons, roomQuantity,
					test, test_steps);
			getRoomNumbers.add(Utility.RoomNo);
			roomClass.CloseOpenedRoomClass(driver, roomClassName, test_steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create a new room class", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create a new room class", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("========== Creating and Associating a new rate plan with room class ==========");
			test_steps.add("========== Creating and Associating a new rate plan with room class ==========");
			navigation.Inventory(driver);
			navigation.Rates1(driver);
			rate.new_Rate(driver, rateName, ratePlan, maxAdults, maxPersons, baseAmount, additionalAdult,
					additionalChild, displayName, associateSeason, ratePolicy, rateDescription, roomClassName,
					test_steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create a new rate plan", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create a new rate plan", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("========== Creating a new policy ==========");
			test_steps.add("========== Creating a new policy ==========");

			navigation.Inventory(driver, test_steps);
			navigation.policies(driver, test_steps);
			policies.deleteAllPolicies(driver, test_steps, policyType, policyNameWithoutNum);
			policies.createPolicy(driver, test_steps, policyName, policyType, policyAttr1, policyAttrValue1,
					policyAttr2, policyAttrValue2, source, policySeason, roomClassName, ratePlan, policyText,
					policyDesc);

			policies.closeOpenedPolicyTab(driver, test_steps);

			policies.verifySearchToaster(driver, test_steps, policyNameWithoutNum + "_Invalid", false);
			policies.verifySearchToaster(driver, test_steps, policyName, true);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create a new policy", testName, "InventoryNavigation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create a new policy", testName, "InventoryNavigation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		String masterCardExpDate = Utility.getFutureMonthAndYearForMasterCard(driver, test_steps);
		try {
			test_steps.add("========== Creating new Reservation ==========");
			try {
				navigation.Reservation_Backward_1(driver);
			} catch (Exception e) {
				navigation.Reservation_Backward_3(driver);
			}
			reservationPage.click_NewReservation(driver, test_steps);
			reservationPage.select_CheckInDate(driver, test_steps, checkInDates.get(0));
			reservationPage.select_CheckoutDate(driver, test_steps, checkOutDates.get(0));
			reservationPage.enter_Adults(driver, test_steps, adultsForRes);
			reservationPage.enter_Children(driver, test_steps, childrenForRes);
			reservationPage.select_Rateplan(driver, test_steps, ratePlan, "");
			reservationPage.clickOnFindRooms(driver, test_steps);
			reservationPage.selectRoom(driver, test_steps, roomClassName, Utility.RoomNo, "");
			depositAmount = reservationPage.deposit(driver, test_steps, "", "");
			reservationPage.clickNext(driver, test_steps);
			taxes = reservationPage.getTaxesFromTripSummary(driver);
			test_steps.add("Taxes and service charges captured during reservation creation is <b>" + taxes + "</b>");
			reservationPage.enter_MailingAddress(driver, test_steps, salutation, guestFirstName, guestLastName,
					phoneNumber, altenativePhone, email, account, accountType, address1, address2, address3, city,
					country, state, postalCode, isGuesProfile);
			if ((account.equalsIgnoreCase("") || account.isEmpty())) {
				reservationPage.enter_PaymentDetails(driver, test_steps, paymentMethod, cardNumber, nameOnCard,
						masterCardExpDate);
			}
			reservationPage.enter_MarketSegmentDetails(driver, test_steps, "", "", referral);
			reservationPage.clickBookNow(driver, test_steps);
			reservation = reservationPage.get_ReservationConfirmationNumber(driver, test_steps);
			status = reservationPage.get_ReservationStatus(driver, test_steps);
			reservationPage.clickCloseReservationSavePopup(driver, test_steps);
			roomNumber1 = reservationPage.get_RoomNumber(driver, test_steps, "");
			roomNumber.add(roomNumber1);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create a new reservation", testName, "InventoryNavigation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create a new reservation", testName, "InventoryNavigation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			test_steps.add(
					"===================== Verifying associated No Show policy details at Policies And Disclaimers tab =====================");
			reservationPage.verifyNoShowPolicy(driver, test_steps, policyName, policyText);
			test_steps.add("===================== Make  Reservation as No Show =====================");
			reservationPage.makeReservationNoShow(driver, test_steps);
			test_steps.add("===================== verifying NoShowReservation PopUp =====================");
			reservationPage.verifyNoShowReservationPopUp(driver, policyName, test_steps);
			test_steps.add("===================== Verifying No Show payment popup =====================");
			ArrayList<String> noShowPaymentWithTaxes = new ArrayList<>();
			reservationPage.verifyNoShowPaymentPopUp(driver, "No Show Payment", baseAmount, masterCardExpDate,
					nameOnCard, "XXXX " + cardNumber.substring(cardNumber.length() - 4), paymentMethod, "Capture",
					test_steps);
			String abc = paymentMethod + " - " + Utility.getCardNumberHidden(cardNumber) + " (" + nameOnCard
					+ ") (Exp. " + masterCardExpDate + ")";
			test_steps.add(
					"===================== Verifying guest and payment details in Noshow payment success popup =====================");
			reservationPage.verifyNoShowPaymentSuccessPopupVerify(driver, noShowPaymentWithTaxes, "No Show Successful",
					"Processed", abc, paymentType, "0.00");
			reservationPage.checkInPaymentSuccessPopupClose(driver, test_steps);
			reservationPage.verifyReservationStatusStatus(driver, test_steps, "No Show");
			test_steps.add("===================== Verifying reservation Trip summary details =====================");
			String tripTotal = reservationPage.get_TripSummaryTripTotalChargesWithoutCurrency(driver, test_steps);
			tripTotal = tripTotal.replace("$", "").trim();
			String tripPaid = reservationPage.getTripSummaryPaidAmount(driver, test_steps);
			double TripTotal = Double.valueOf(tripTotal);
			double TripPaids = Double.valueOf(tripPaid);
			double TotalBalance = TripTotal - TripPaids;
			app_logs.info((int) (TotalBalance));
			test_steps.add(
					"===================== Verifying No show coming as note in guest details section =====================");
			reservationPage.verifyNoShowNotesDetails(driver, test_steps, policyType, "", null, "auto guest");
			test_steps.add("===================== Verifying No show History details =====================");
			reservationPage.click_History(driver, test_steps);
			reservationPage.verify_ReservationInHistoryTab(driver, test_steps, reservation);
			test_steps.add("===================== Verifying No show Folio LineItem Details =====================");
			reservationPage.click_Folio(driver, test_steps);
			reservationPage.CheckedDisplayVoidItem(driver, test_steps);
			reservationPage.verifyReservationFolioDetails(driver, roomNumber1, roomClassName, test_steps);

		} catch (Exception e) {
			System.out.println(e);
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to cancel reservation and verify associated cancellation and other "
						+ "payment details", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to cancel reservation and verify associated cancellation and other "
						+ "payment details", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			test_steps.add("===================== Deleting policy created during test run =====================");
			navigation.Inventory(driver, test_steps);
			navigation.policies(driver, test_steps);
			policies.deleteAllPolicies(driver, test_steps, policyType, policyName);

			test_steps.add("===================== Deleting rate created during test run =====================");
			navigation.Inventory(driver);
			navigation.Rates1(driver);
			rate.deleteRates(driver, rateNameWithoutNum);

			test_steps.add("===================== Deleting room class created during test run =====================");
			navigation.Setup(driver, test_steps);
			navigation.RoomClass(driver, test_steps);
			roomClass.SearchRoomClass(driver, roomClassNameWithoutNum, test_steps);
			roomClass.deleteRoomClass(driver, roomClassNameWithoutNum);

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
	}

	@DataProvider
	public Object[][] getData() {
		return Utility.getData("verifyOneNinghtNoShowPolicy", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
