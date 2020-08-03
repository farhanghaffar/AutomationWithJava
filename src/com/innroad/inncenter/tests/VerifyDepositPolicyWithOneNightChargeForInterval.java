package com.innroad.inncenter.tests;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.Admin;
import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Folio;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Policies;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;


public class VerifyDepositPolicyWithOneNightChargeForInterval extends TestCore{

	private WebDriver driver = null;
	ArrayList<String> test_catagory = new ArrayList<>();
	ArrayList<String> test_description = new ArrayList<>();
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> test_name = new ArrayList<>();
	ArrayList<String> totalNights = new ArrayList<>();
	ArrayList<String> roomBaseAmounts = new ArrayList<>();
	ArrayList<String> roomNumber = new ArrayList<>();	
	ArrayList<String> getRoomNumbers = new ArrayList<>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();

	Double depositAmount=0.0;
	Double paidDeposit=0.0;

	String testName = this.getClass().getSimpleName().trim();

	String roomClassNameWithoutNum, rateNameWithoutNum, policyNameWithoutNum, typeToValidate, policyDesc, policyText, 
	cardExpDate, tripTaxes, reservation=null, status=null, tripRoomCharges, tripTotal, tripPaid, tripBalance, amountToBePaid;

	@BeforeTest(alwaysRun=true)
	public void checkRunMode() {
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Policy")
	public void verifyDepositPolicyWithOneNightChargeForInterval(String roomClassName, String roomClassAbb, String maxAdults, 
			String maxPersons, String roomQuantity, String rateName, String ratePlan, String rateInterval, String baseAmount,String additionalAdult, 
			String additionalChild, String displayName, String ratePolicy, String rateDescription, 
			String associateSeason, String policyName, String policyType, String policyAttr1, String policyAttrValue1,
			String source, String policySeason,
			String checkInDate, String checkOutDate, String adultsForRes, String childrenForRes,
			String salutation, String guestFirstName,String guestLastName, String phoneNumber, String altenativePhone,
			String account, String accountType, String address1, String address2, String address3, String city, String country,
			String state, String postalCode, String isGuesProfile, String paymentMethod, String cardNumber, String nameOnCard,
			String referral, String amountPaidIcon) throws Exception {
	
		String testDescription = "Verify deposit policy amount for 1 night room charge with interval rate<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682503' target='_blank'>"
				+ "Click here to open TestRail: C682503</a><br/>";
		String testCatagory = "Policies";

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Policies policies = new Policies();
		CPReservationPage reservationPage = new CPReservationPage();
		Navigation navigation = new Navigation();
		Rate rate = new Rate();
		RoomClass roomClass = new RoomClass();
		Admin admin = new Admin();
		Folio folio = new Folio();
		try {
            if (!Utility.insertTestName.containsKey(testName)) {
                Utility.insertTestName.put(testName, testName);
                Utility.reTry.put(testName, 0);
            } else {
                Utility.reTry.replace(testName, 1);
            }
    		String randomNum = Utility.GenerateRandomNumber();
    		cardExpDate = Utility.getFutureMonthAndYearForMasterCard(driver, test_steps);
    		roomClassNameWithoutNum = roomClassName;
    		roomClassName = roomClassName+randomNum;
    		roomClassAbb = roomClassAbb+randomNum;
    		rateNameWithoutNum = rateName;
    		rateName = rateName+randomNum;
    		
    		policyNameWithoutNum = policyName;
    		policyName = policyName+randomNum;
    		policyText = policyName+"_Text";
    		policyDesc = policyName+"_Description";

    		guestLastName = guestLastName+randomNum;
    		nameOnCard = guestFirstName+" "+guestLastName;
    		roomBaseAmounts =Utility.splitInputData(baseAmount);
    		if ( !(Utility.validateInput(checkInDate)) ) {
    			for (int i = 0; i < guestFirstName.split("\\|").length; i++) {
    				checkInDates.add(Utility.getCurrentDate("dd/MM/yyyy"));
    				checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(3), "MM/dd/yyyy", "dd/MM/yyyy"));
    			}
    		}else {
    			checkInDates = Utility.splitInputData(checkInDate);
    			checkOutDates = Utility.splitInputData(checkOutDate);
    		}
    		for (int i = 0; i < checkInDates.size(); i++) {
    			totalNights.add(Utility.differenceBetweenDates(checkInDates.get(i), checkOutDates.get(i)));
    		}

		}catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to update test data before executing test scripts", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to update test data before executing test scripts", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
    		driver = getDriver();
            login_CP(driver, test_steps);
			test_steps.add("========== Selecting <b>Inventory Sub System</b> option from client info details ==========");
 			navigation.navigateToAdminPage(driver, test_steps);
 			navigation.navigateToClientInfoPage(driver, test_steps);
 			navigation.openClientDetails(driver, test_steps);
 			navigation.openClientDetailsOptionsTab(driver, test_steps);
 			boolean checkBoxSelected = admin.selectInventorySubSystemCheckBox(driver, test_steps);
 			if (!checkBoxSelected) {
				admin.logout(driver, test_steps);
		        login_CP(driver, test_steps);
			}
		}catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to select <b>Inventory Sub System</b> checkbox in client details page", 
						testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to select <b>Inventory Sub System</b> checkbox in client details page", 
						testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("========== Creating a new room class ==========");
			test_steps.add("========== Creating a new room class ==========");
 			navigation.Setup(driver, test_steps);
			navigation.RoomClass(driver, test_steps);
			roomClass.deleteAllRoomClasses(driver, roomClassNameWithoutNum, test_steps);
			navigation.clickOnNewRoomClassButton(driver, test_steps);
			roomClass.CreateRoomClass(driver, roomClassName, roomClassAbb, null,
					maxAdults, maxPersons, roomQuantity, test, test_steps);            
			getRoomNumbers.add(Utility.RoomNo);
			roomClass.CloseOpenedRoomClass(driver, roomClassName, test_steps);
		}catch (Exception e) {
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
			rate.deleteAllRates(driver, test_steps, rateNameWithoutNum);
			rate.createRate(driver, test_steps, rateName, ratePlan, rateInterval, maxAdults, maxPersons, baseAmount,
					additionalAdult, additionalChild, displayName, associateSeason, ratePolicy, 
					rateDescription, roomClassName);
		}catch (Exception e) {
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
					null, null, source, policySeason, roomClassName, ratePlan, policyText, policyDesc);
			
			policies.closeOpenedPolicyTab(driver, test_steps);
			policies.verifySearchToaster(driver, test_steps, policyNameWithoutNum+"_Invalid", false);
			policies.verifySearchToaster(driver, test_steps, policyName, true);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create a new policy", testName, "InventoryNavigation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create a new policy", testName, "InventoryNavigation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

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
			reservationPage.select_Rateplan(driver, test_steps, ratePlan,"");
			reservationPage.clickOnFindRooms(driver, test_steps);
			reservationPage.selectRoom(driver, test_steps, roomClassName, getRoomNumbers.get(0), "");
//			depositAmount=reservationPage.deposit(driver, test_steps, "", "");		
			reservationPage.clickNext(driver, test_steps);
			reservationPage.enter_MailingAddress(driver, test_steps, salutation, guestFirstName, guestLastName,
					phoneNumber, altenativePhone, "", account, accountType, address1, address2, address3, city,
					country, state, postalCode, isGuesProfile);
				if((account.equalsIgnoreCase("")||account.isEmpty())) {
				reservationPage.enter_PaymentDetails(driver, test_steps, paymentMethod, cardNumber, nameOnCard, cardExpDate);
			}
			reservationPage.enter_MarketSegmentDetails(driver, test_steps, "", "", referral);
			reservationPage.clickBookNow(driver, test_steps);
			reservation=reservationPage.get_ReservationConfirmationNumber(driver, test_steps);
			status=reservationPage.get_ReservationStatus(driver, test_steps);
			reservationPage.clickCloseReservationSavePopup(driver, test_steps);
			String roomNumber1 = reservationPage.get_RoomNumber(driver, test_steps, "");
			roomNumber.add(roomNumber1);
			test_steps.add("========== Displaying amount details on trip summary ========");
			test_steps.add("Total nights for reservation is captured as <b>"+totalNights.get(0)+" </b>based on "
					+ "input check-in/out dates provided for reservation");
			tripRoomCharges = reservationPage.getRoomChargeUnderTripSummary(driver, test_steps);
			tripTaxes = reservationPage.getTaxesFromTripSummary(driver);
			test_steps.add("Taxes and service charges captured at trip summary is : <b>"+tripTaxes+"</b>");			
			tripTotal = reservationPage.get_TripSummaryTripTotalChargesWithoutCurrency(driver, test_steps);
			tripPaid = reservationPage.get_TripSummaryPaidAmount(driver, test_steps);
			tripBalance = reservationPage.get_TripSummaryBalance_Amount(driver, test_steps);	
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create a new reservation", testName, "InventoryNavigation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create a new reservation", testName, "InventoryNavigation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			amountToBePaid = reservationPage.calculationOfDeposiAmountToBePaid(tripTotal, totalNights.get(0));			
			test_steps.add("===================== Verifying associated deposit policy details at Policies And Disclaimers tab =====================");
			reservationPage.verifyDepositPolicyDetailsAtPoliciesAndDisclaimers(driver, test_steps, policyName, policyText);
			test_steps.add("===================== Verifying amount details and icon at trip summary & folio tab =====================");
			reservationPage.verifyTripSummaryPaidAmount(driver, test_steps, amountToBePaid);
			reservationPage.click_Folio(driver, test_steps);
			reservationPage.verifyTripPaidAmountAtFolio(driver, test_steps, amountToBePaid);
			folio.verifyLineItemIcon(driver, test_steps, paymentMethod, TestCore.envURL + amountPaidIcon,
					" <b>Passed payment</b> icon at folio line item");		
		}catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to verify associated policies and other guest details", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to verify associated policies and other guest details", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		try {

			test_steps.add("========== Reverting settings from client info details ==========");
 			navigation.navigateToAdminPage(driver, test_steps);
 			navigation.navigateToClientInfoPage(driver, test_steps);
 			navigation.openClientDetails(driver, test_steps);
 			navigation.openClientDetailsOptionsTab(driver, test_steps);
 			admin.selectBothInventorySubSystemAndRateCheckBox(driver, test_steps);

			test_steps.add("===================== Deleting policy created during test run =====================");
			navigation.Inventory(driver, test_steps);
			navigation.policies(driver, test_steps);
			policies.deleteAllPolicies(driver, test_steps, policyType, policyName);
		
			test_steps.add("===================== Deleting rate created during test run =====================");
			navigation.Inventory(driver);
			navigation.Rates1(driver);			
			rate.deleteAllRates(driver, test_steps, rateName);

			test_steps.add("===================== Deleting room class created during test run =====================");
			navigation.Setup(driver, test_steps);
			navigation.RoomClass(driver, test_steps);
			roomClass.deleteAllRoomClasses(driver, roomClassName, test_steps);

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
		return Utility.getData("verifyOneNightDepositPolicy", excel);
	}
	
	@AfterClass(alwaysRun=true)
    public void closeDriver() {
        driver.quit();
    }
	
	
}
