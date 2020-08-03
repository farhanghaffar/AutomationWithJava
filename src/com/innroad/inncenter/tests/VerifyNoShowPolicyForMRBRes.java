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

public class VerifyNoShowPolicyForMRBRes extends TestCore {

	private WebDriver driver = null;
	ArrayList<String> test_catagory = new ArrayList<>();
	ArrayList<String> test_description = new ArrayList<>();
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> test_name = new ArrayList<>();
	ArrayList<String> baseAmounts = new ArrayList<>();
	ArrayList<String> policyAttributes1 = new ArrayList<>();
	ArrayList<String> policyAttributeValues1 = new ArrayList<>();
	ArrayList<String> policyAttributes2 = new ArrayList<>();
	ArrayList<String> policyAttributeValues2 = new ArrayList<>();
	ArrayList<String> policyNames = new ArrayList<>();
	ArrayList<String> policyTexts = new ArrayList<>();
	ArrayList<String> policyDescs = new ArrayList<>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	ArrayList<String> policyAmounts = new ArrayList<>();

	ArrayList<String> totalNights = new ArrayList<>();

	int max = 0;
	int index = 0;

	String testName = this.getClass().getSimpleName().trim();
	String roomClassName1, roomClassName2, roomClassAbb1, roomClassAbb2, rateName1, rateName2, policyName1, policyName2,
			policyText1, policyDesc1, policyText2, policyDesc2, roomClassNameWithoutNum, rateNameWithoutNum,
			policyNameWithoutNum, typeToValidate, policyDesc, policyText, reservation = null, status = null, ratePlan1,
			paymentIconInFolio, taxes, cardExpDate, percentageOfAmount, tripTaxes,tripRoomCharges, tripTotal, 
			tripPaid, tripBalance;

	Double depositAmount = 0.0, paidDeposit = 0.0;
	ArrayList<String> roomNumber = new ArrayList<>();
	ArrayList<String> roomNumberAssigned = new ArrayList<>();

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Policy")
	public void verifyNoShowPolicyForMRBRes(String roomClassName, String roomClassAbb, String maxAdults,
			String maxPersons, String roomQuantity, String rateName, String ratePlan, String baseAmount,
			String additionalAdult, String additionalChild, String displayName, String ratePolicy,
			String rateDescription, String associateSeason, String policyName, String policyType, String policyAmount,
			String policyAttr1, String policyAttrValue1, String policyAttr2, String policyAttrValue2, String source,
			String policySeason, String checkInDate, String checkOutDate, String adultsForRes, String childrenForRes,
			String salutation, String guestFirstName, String guestLastName, String phoneNumber, String altenativePhone,
			String email, String account, String accountType, String address1, String address2, String address3,
			String city, String country, String state, String postalCode, String isGuesProfile, String paymentMethod,
			String cardNumber, String nameOnCard, String referral, String cancelReason, String paymentType)
			throws Exception {

		String testDescription = "Verify No Show policy calculation for Multi room booking, when each room has different percentages of Room charges/Total Charges<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682493' target='_blank'>"
				+ "Click here to open TestRail: C682493</a><br/>";
		String testCatagory = "Policies";

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Policies policies = new Policies();
		CPReservationPage reservationPage = new CPReservationPage();
		Navigation navigation = new Navigation();
		Rate rate = new Rate();
		RoomClass roomClass = new RoomClass();
		Folio folio = new Folio();

		driver = getDriver();
		// =============== Updating test data with random numbere ===============
		{
			
			String randomNum1 = Utility.GenerateRandomNumber();
			String randomNum2 = Utility.GenerateRandomNumber();

			roomClassNameWithoutNum = roomClassName.replace("RandomNum", "");
			roomClassName1 = roomClassName.replace("RandomNum", randomNum1);
			roomClassAbb1 = roomClassAbb.replace("RandomNum", randomNum1);
			roomClassName2 = roomClassName.replace("RandomNum", randomNum2);
			roomClassAbb2 = roomClassAbb.replace("RandomNum", randomNum2);

			rateNameWithoutNum = rateName.replace("RandomNum", "");
			rateName1 = rateName.replace("RandomNum", randomNum1);
			ratePlan1 = ratePlan.split("\\|")[0];
			rateName2 = rateName.replace("RandomNum", randomNum2);

			policyNameWithoutNum = policyName.replace("RandomNum", "");
			policyName1 = policyName.replace("RandomNum", randomNum1);
			policyName2 = policyName.replace("RandomNum", randomNum2);
			policyText1 = policyName1 + "_Text";
			policyDesc1 = policyName1 + "_Desc";
			policyText2 = policyName2 + "_Text";
			policyDesc2 = policyName2 + "_Desc";
			policyNames.add(policyName1);
			policyNames.add(policyName2);
			policyTexts.add(policyText1);
			policyTexts.add(policyText2);
			policyDescs.add(policyDesc1);
			policyDescs.add(policyDesc2);

			guestLastName = guestLastName.replace("User", "User" + randomNum1);
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
			String policyAmountSize[] = policyAmount.split("\\|");
			for (int i = 0; i < policyAmountSize.length; i++) {
				policyAmounts.add(policyAmountSize[i]);
			}
			baseAmounts = Utility.splitInputData(baseAmount);
			policyAttributes1 = Utility.splitInputData(policyAttr1);
			policyAttributeValues1 = Utility.splitInputData(policyAttrValue1);
			policyAttributes2 = Utility.splitInputData(policyAttr2);
			policyAttributeValues2 = Utility.splitInputData(policyAttrValue2);
			for (int i = 0; i < policyAttributeValues1.size(); i++) {
				if (max < Integer.parseInt(policyAttributeValues1.get(i))) {
					max = Integer.parseInt(policyAttributeValues1.get(i));
					index = i;
					percentageOfAmount = policyAttributeValues1.get(i);
				}
			}
			checkInDate = Utility.addInputDataToStringWithSeperator(checkInDates);
			checkOutDate = Utility.addInputDataToStringWithSeperator(checkOutDates);
		}

		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);
			} else {
				Utility.reTry.replace(testName, 1);
			}
			login_CP(driver, test_steps);
			test_steps.add("========== Remove Existing Rates And RoomClasses ==========");
			navigation.Inventory(driver);
			navigation.Rate(driver);
			rate.deleteRates(driver, rateNameWithoutNum);
			navigation.Setup(driver, test_steps);
			navigation.RoomClass(driver, test_steps);
			roomClass.SearchClasses(driver, roomClassNameWithoutNum);
			roomClass.deleteRoomClass(driver, roomClassNameWithoutNum);
			
			test_steps.add("========== Creating 1st room class ==========");
			
			navigation.clickOnNewRoomClassButton(driver, test_steps);
			roomClass.CreateRoomClass(driver, roomClassName1, roomClassAbb1, null, maxAdults, maxPersons, roomQuantity,
					test, test_steps);
			roomNumberAssigned.add(Utility.RoomNo);
			roomClass.CloseOpenedRoomClass(driver, roomClassName1, test_steps);
			navigation.NewRoomClass(driver);
			test_steps.add("========== Creating 2nd room class ==========");
			roomClass.CreateRoomClass(driver, roomClassName2, roomClassAbb2, null, maxAdults, maxPersons, roomQuantity,
					test, test_steps);
			roomNumberAssigned.add(Utility.RoomNo);
			for (int i = 0; i < roomNumberAssigned.size(); i++) {
				System.out.println(roomNumberAssigned.get(i));
			}
			test_steps.add("========== Creating 1st rate plan and Associating with 1st room class ==========");
			try {
				navigation.Reservation_Backward_1(driver);
			} catch (Exception e) {
				navigation.Reservation_Backward_3(driver);
			}
			navigation.Inventory(driver);
			navigation.Rates1(driver);
			//rate.deleteAllRates(driver, test_steps, rateNameWithoutNum);
			rate.new_Rate(driver, rateName1, ratePlan1, maxAdults, maxPersons, baseAmounts.get(0), additionalAdult,
					additionalChild, displayName, associateSeason, ratePolicy, rateDescription, roomClassName1,
					test_steps);

			test_steps.add("========== Creating 2nd rate plan and Associating with 2nd room class ==========");
			rate.new_Rate(driver, rateName2, ratePlan1, maxAdults, maxPersons, baseAmounts.get(1), additionalAdult,
					additionalChild, displayName, associateSeason, ratePolicy, rateDescription, roomClassName2,
					test_steps);

			test_steps.add("========== Creating 1st policy and Associating with 1st room class ==========");
			navigation.Inventory(driver, test_steps);
			navigation.policies(driver, test_steps);
			policies.deleteAllPolicies(driver, test_steps, policyType, policyNameWithoutNum);
			policies.createPolicy(driver, test_steps, policyName1, policyType, policyAttributes1.get(0),
					policyAttributeValues1.get(0), policyAttributes2.get(0), policyAttributeValues2.get(0), source,
					policySeason, roomClassName1, ratePlan1, policyText1, policyDesc1);
			policies.closeOpenedPolicyTab(driver, test_steps);
			test_steps.add("========== Creating 2nd policy and Associating with 2nd room class ==========");
			policies.createPolicy(driver, test_steps, policyName2, policyType, policyAttributes1.get(1),
					policyAttributeValues1.get(1), policyAttributes2.get(1), policyAttributeValues2.get(1), source,
					policySeason, roomClassName2, ratePlan1, policyText2, policyDesc2);
			test_steps.add("========== Creating new Reservation ==========");
			String masterCardExpDate = Utility.getFutureMonthAndYearForMasterCard(driver, test_steps);
			try {
				navigation.Reservation_Backward_1(driver);
			} catch (Exception e) {
				navigation.Reservation_Backward_3(driver);
			}
			reservationPage.click_NewReservation(driver, test_steps);
			reservationPage.select_Dates(driver, test_steps, checkInDate, checkOutDate, adultsForRes, childrenForRes,
					ratePlan, "", "");
			reservationPage.clickOnFindRooms(driver, test_steps);

			reservationPage.selectRoom(driver, test_steps, roomClassName1, Utility.RoomNo, "");
			reservationPage.selectRoom(driver, test_steps, roomClassName2, Utility.RoomNo, "");
			depositAmount = reservationPage.deposit(driver, test_steps, "", "");
			reservationPage.clickNext(driver, test_steps);
			taxes = reservationPage.getTaxesAndServiceCharges_TripSummary(driver);
			depositAmount = reservationPage.deposit(driver, test_steps, "", "");

			reservationPage.enter_MRB_MailingAddress(driver, test_steps, salutation, guestFirstName, guestLastName,
					phoneNumber, altenativePhone, "", "", accountType, address1, address2, address3, city, country,
					state, postalCode, isGuesProfile, "", "", roomNumber);

			if ((account.equalsIgnoreCase("") || account.isEmpty())) {
				reservationPage.enter_PaymentDetails(driver, test_steps, paymentMethod, cardNumber, nameOnCard,
						masterCardExpDate);
			}
			reservationPage.enter_MarketSegmentDetails(driver, test_steps, "", "", referral);
			reservationPage.clickBookNow(driver, test_steps);
			reservation = reservationPage.get_ReservationConfirmationNumber(driver, test_steps);
			status = reservationPage.get_ReservationStatus(driver, test_steps);
			reservationPage.clickCloseReservationSavePopup(driver, test_steps);

			String roomNumber1 = reservationPage.get_RoomNumber(driver, test_steps, "");
			roomNumber.add(roomNumber1);
			String roomNumber2 = reservationPage.getSecondRoomNumber(driver, test_steps, "");
			
			test_steps.add("========== Displaying amount details on trip summary ========");
            tripRoomCharges = reservationPage.getRoomChargeUnderTripSummary(driver, test_steps);
            tripTaxes = reservationPage.getTaxesFromTripSummary(driver);
            test_steps.add("Taxes and service charges captured at trip summary is : <b>"+tripTaxes+"</b>");           
            tripTotal = reservationPage.get_TripSummaryTripTotalChargesWithoutCurrency(driver, test_steps);
            tripPaid = reservationPage.getTripSummaryPaidAmount(driver, test_steps);
            tripBalance = reservationPage.get_TripSummaryBalance_Amount(driver, test_steps);
			try {
				int policyAmountCal1 = Integer.parseInt(policyAmounts.get(0));
				int policyAmountCal2 = Integer.parseInt(policyAmounts.get(1));
				if (policyAmountCal1 > policyAmountCal2) {
					policyText = policyText1;
					policyName = policyName1;
					policyAmount = policyAmounts.get(0);
				} else {
					policyText = policyText2;
					policyName = policyName2;
					policyAmount = policyAmounts.get(1);
				}
				test_steps.add("========== Capturing highest fixed amount of deposit policy details should be applied ==========");
				test_steps.add("Highest fixed amount from both two policis is : <b>"+policyAmount+"</b>");
				test_steps.add("Highest amount of Deposit policy name is : <b>"+policyName+"</b>");
				test_steps.add("Highest amount of Deposit policy text is : <b>"+policyText+"</b>");
				test_steps.add("highest amount of check-in policy Description is : <b>"+policyDesc+"</b>");
				test_steps.add(
						"===================== Verifying associated No Show policy details at Policies And Disclaimers tab =====================");
				reservationPage.verifyNoShowPolicy(driver, test_steps, policyName, policyText);
				test_steps.add("===================== Make  Reservation as No Show =====================");
				reservationPage.makeReservationNoShow(driver, test_steps);
				test_steps.add("===================== verifying NoShowReservation PopUp =====================");
				reservationPage.verifyNoShowReservationPopUp(driver, policyName, test_steps);
				test_steps.add("===================== Verifying No Show payment popup =====================");
				String amountToBePaid = reservationPage.calculationOfNoShowAmountToBePaid(baseAmounts, totalNights, policyAttributes1.get(0),
						percentageOfAmount, tripTaxes, tripRoomCharges);
				System.out.println(amountToBePaid);
				ArrayList<String> cancelPaymentWithTaxes = new ArrayList<>();
				reservationPage.verifyNoShowPaymentPopUp(driver, "No Show Payment", masterCardExpDate, nameOnCard,
						"XXXX " + cardNumber.substring(cardNumber.length() - 4), paymentMethod, "Capture",amountToBePaid,baseAmounts,totalNights, taxes, test_steps);
				String abc = paymentMethod + " - " + Utility.getCardNumberHidden(cardNumber) + " (" + nameOnCard
						+ ") (Exp. " + masterCardExpDate + ")";
				test_steps.add("===================== Verifying guest and payment details in Noshow payment success popup =====================");
				reservationPage.verifyNoShowPaymentSuccessPopupVerify(driver, cancelPaymentWithTaxes,
						"No Show Successful", "Processed", abc, paymentType, "0.00");
				reservationPage.clickOnPayButtonOnPaymentPopup(driver, test_steps);
				reservationPage.checkInPaymentSuccessPopupClose(driver, test_steps);
				reservationPage.close_FirstOpenedReservation(driver, test_steps);
				String firstGuestName = getFirstValueFromTestData(guestFirstName) + " "
						+ getFirstValueFromTestData(guestLastName);
				reservationPage.searchWithGuestName(driver, test_steps, firstGuestName);
				reservationPage.clickOnGuestName(driver, test_steps);
				reservationPage.verifyReservationStatusStatus(driver, test_steps, "No Show");
				test_steps
						.add("===================== Verifying reservation Trip summary details =====================");
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
				reservationPage.checkedDisplayVoidItem(driver, test_steps);
				reservationPage.verifyPrimaryUserFolioDetails(driver, roomNumber1,roomClassName1, test_steps);
				reservationPage.verifySecondaryUserFolioDetails(driver, roomNumber2,roomClassName2, test_steps);

			} catch (Exception e) {
				System.out.println(e);
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
					Utility.updateReport(e, "Failed to NoShow reservation and verify associated cancellation and other "
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

			test_steps.add("===================== Deleting policy created during test run =====================");
			try {
				navigation.Reservation_Backward_1(driver);
			} catch (Exception e) {
				navigation.Reservation_Backward_3(driver);
			}
			navigation.Inventory(driver, test_steps);
			navigation.policies(driver, test_steps);
			policies.deleteAllPolicies(driver, test_steps, policyType, policyNameWithoutNum);

			test_steps.add("===================== Deleting rate created during test run =====================");
			try {
				navigation.Reservation_Backward_1(driver);
			} catch (Exception e) {
				navigation.Reservation_Backward_3(driver);
			}
			navigation.Inventory(driver);
			navigation.Rates1(driver);
			rate.deleteRates(driver, rateNameWithoutNum);

			test_steps.add("===================== Deleting room class created during test run =====================");
			try {
				navigation.Reservation_Backward_1(driver);
			} catch (Exception e) {
				navigation.Reservation_Backward_3(driver);
			}
			navigation.Setup(driver, test_steps);
			navigation.RoomClass(driver, test_steps);
			roomClass.SearchClasses(driver, roomClassNameWithoutNum);
			roomClass.deleteRoomClass(driver, roomClassNameWithoutNum);

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
		} catch (Exception e) {
			System.out.println(e);
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

	public String getFirstValueFromTestData(String input) {
		String output = input.split("\\|")[0];
		return output;
	}

	@DataProvider
	public Object[][] getData() {
		return Utility.getData("verifyNoShowPolicyMRBRes", excel);
	}

	//@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
