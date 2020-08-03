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

public class VerifyHighestDepositPolicyForMRBRes extends TestCore{

	
	private WebDriver driver = null;
	ArrayList<String> test_catagory = new ArrayList<>();
	ArrayList<String> test_description = new ArrayList<>();
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> test_name = new ArrayList<>();
	ArrayList<String> totalNights = new ArrayList<>();
	ArrayList<String> baseAmounts = new ArrayList<>();
	ArrayList<String> policyFixedAmounts = new ArrayList<>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	ArrayList<String> roomNumber = new ArrayList<>();	
	ArrayList<String> roomNumberAssigned = new ArrayList<>();	

	String testName = this.getClass().getSimpleName().trim();
	String roomClassName1, roomClassName2, roomClassAbb1, roomClassAbb2, rateName1, rateName2,
	policyName1, policyName2, policyText1, policyDesc1, policyText2, policyDesc2, roomClassNameWithoutNum,
	rateNameWithoutNum, policyNameWithoutNum, typeToValidate, policyDesc, policyText, reservation=null,
	status=null, ratePlan1, paymentIconInFolio, policyAmount, tripTaxes,tripRoomCharges, tripTotal, tripPaid, tripBalance;
	Double depositAmount=0.0, paidDeposit=0.0;

	
	@BeforeTest(alwaysRun=true)
	public void checkRunMode() {
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Policy")
	public void verifyHighestDepositPolicyForMRBRes(String roomClassName, String roomClassAbb, String maxAdults, 
			String maxPersons, String roomQuantity, String rateName, String ratePlan, String baseAmount,String additionalAdult, 
			String additionalChild, String displayName, String ratePolicy, String rateDescription, 
			String associateSeason, String policyName, String policyType, String attr, String attrValue, String source, String policySeason,
			String checkInDate, String checkOutDate, String adultsForRes, String childrenForRes,
			String salutation, String guestFirstName,String guestLastName, String phoneNumber, String altenativePhone, String email,
			String account, String accountType, String address1, String address2, String address3, String city, String country,
			String state, String postalCode, String isGuesProfile, String paymentMethod, String cardNumber, String nameOnCard, 
			String cardExpDate, String referral, String amountPaidIcon) throws Exception {
		
		String testDescription = "Verifying highest amount of deposit policy applied for MRB reservartion "
				+ "when there are different deposit policies for each room class<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682490' target='_blank'>"
				+ "Click here to open TestRail: C682490</a><br/>";
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

		try {
         if (!Utility.insertTestName.containsKey(testName)) {
                Utility.insertTestName.put(testName, testName);
                Utility.reTry.put(testName, 0);
            }else {
                Utility.reTry.replace(testName, 1);
            }
	   		String randomNum1 = Utility.GenerateRandomNumber();
			String randomNum2 = Utility.GenerateRandomNumber();
			
			roomClassName1 = roomClassName+randomNum1;
			roomClassAbb1 = roomClassAbb+randomNum1;

			roomClassName2 = roomClassName+randomNum2;
			roomClassAbb2 = roomClassAbb+randomNum2;

			rateName1 = rateName+randomNum1;
			rateName2 = rateName+randomNum2;
			ratePlan1 = ratePlan.split("\\|")[0];

			policyName1 = policyName+randomNum1;
			policyName2 = policyName+randomNum2;

			policyText1 = policyName1+"_Text";
			policyDesc1 = policyName1+"_Description";

			policyText2 = policyName2+"_Text";
			policyDesc2 = policyName2+"_Description";

			guestLastName = guestLastName.replace("User", "User"+randomNum1);
			baseAmounts = Utility.splitInputData(baseAmount);
			policyFixedAmounts = Utility.splitInputData(attrValue);
			if ( !(Utility.validateInput(checkInDate)) ) {
				for (int i = 0; i < guestFirstName.split("\\|").length; i++) {
					checkInDates.add(Utility.getCurrentDate("dd/MM/yyyy"));
					checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(2), "MM/dd/yyyy", "dd/MM/yyyy"));
				}
			}else {
				checkInDates = Utility.splitInputData(checkInDate);
				checkOutDates = Utility.splitInputData(checkOutDate);
			}for (int i = 0; i < checkInDates.size(); i++) {
				totalNights.add(Utility.differenceBetweenDates(checkInDates.get(i), checkOutDates.get(i)));
			}
			checkInDate = Utility.addInputDataToStringWithSeperator(checkInDates);
			checkOutDate = Utility.addInputDataToStringWithSeperator(checkOutDates);

		}catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to update test data before execute test scripts", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to update test data before execute test scripts", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

//		try {
//			driver = getDriver();
//            login_CP(driver, test_steps);
//			test_steps.add("========== Deleting existing taxes from inncenter ==========");
// 			navigation.Setup(driver, test_steps);
//			navigation.clickOnTaxesTab(driver, test_steps);
//
//		}catch (Exception e) {
//			if (Utility.reTry.get(testName) == Utility.count) {
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
//				Utility.updateReport(e, "Failed to delete existing taxes", testName, "Reservation", driver);
//			} else {
//				Assert.assertTrue(false);
//			}
//		} catch (Error e) {
//			if (Utility.reTry.get(testName) == Utility.count) {
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
//				Utility.updateReport(e, "Failed to delete existing taxes", testName, "Reservation", driver);
//			} else {
//				Assert.assertTrue(false);
//			}
//		}

		try {
			driver = getDriver();
            login_CP(driver, test_steps);
			test_steps.add("========== Creating 1st room class ==========");
 			navigation.Setup(driver, test_steps);
			navigation.RoomClass(driver, test_steps);
			roomClass.deleteAllRoomClasses(driver, roomClassName, test_steps);
			navigation.clickOnNewRoomClassButton(driver, test_steps);
			roomClass.CreateRoomClass(driver, roomClassName1, roomClassAbb1, null,
					maxAdults, maxPersons, roomQuantity, test, test_steps);    
			roomNumberAssigned.add(Utility.RoomNo);
			roomClass.CloseOpenedRoomClass(driver, roomClassName1, test_steps);
			navigation.NewRoomClass(driver);
			test_steps.add("========== Creating 2nd room class ==========");
			roomClass.CreateRoomClass(driver, roomClassName2, roomClassAbb2, null,
					maxAdults, maxPersons, roomQuantity, test, test_steps);       
			roomNumberAssigned.add(Utility.RoomNo);
			for (int i = 0; i < roomNumberAssigned.size(); i++) {
				System.out.println(roomNumberAssigned.get(i));
			}
		}catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create two room classes", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create two room classes", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		try {
			test_steps.add("========== Creating 1st rate plan and Associating with 1st room class ==========");
			navigation.Inventory(driver);
			navigation.Rates1(driver);			
			rate.deleteAllRates(driver, test_steps, rateName);
			rate.new_Rate(driver, rateName1, ratePlan1, maxAdults, maxPersons, baseAmounts.get(0),
					additionalAdult, additionalChild, displayName, associateSeason, ratePolicy, 
					rateDescription, roomClassName1, test_steps);

			test_steps.add("========== Creating 2nd rate plan and Associating with 2nd room class ==========");
			rate.new_Rate(driver, rateName2, ratePlan1, maxAdults, maxPersons, baseAmounts.get(1),
					additionalAdult, additionalChild, displayName, associateSeason, ratePolicy, 
					rateDescription, roomClassName2, test_steps);
		}catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create two rate plans", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create two rate plans", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		try {
			test_steps.add("========== Creating 1st policy and Associating with 1st room class ==========");
			navigation.Inventory(driver, test_steps);
			navigation.policies(driver, test_steps);
			policies.deleteAllPolicies(driver, test_steps, policyType, policyName);
			policies.createPolicy(driver, test_steps, policyName1, policyType, attr, 
					policyFixedAmounts.get(0), null, null, source, policySeason, roomClassName1, ratePlan1, policyText1, policyDesc1);			

			test_steps.add("========== Creating 2nd policy and Associating with 2nd room class ==========");
			navigation.Inventory(driver, test_steps);
			navigation.policies(driver, test_steps);
			policies.createPolicy(driver, test_steps, policyName2, policyType, attr, 
					policyFixedAmounts.get(1), null, null, source, policySeason, roomClassName2, ratePlan1, policyText2, policyDesc2);			

			int policyAmountCal1 = Integer.parseInt(policyFixedAmounts.get(0));
			int policyAmountCal2 = Integer.parseInt(policyFixedAmounts.get(1));
			if (policyAmountCal1>policyAmountCal2) {
				policyText = policyText1;
				policyName = policyName1;
				policyAmount = policyFixedAmounts.get(0);
				policyDesc = policyDesc1;
			}else {
				policyText = policyText2;
				policyName = policyName2;
				policyAmount = policyFixedAmounts.get(1);
				policyDesc = policyDesc2;
			}
			test_steps.add("========== Capturing highest fixed amount of deposit policy details should be applied ==========");
			test_steps.add("Highest fixed amount from both two inputs is : <b>"+policyAmount+"</b>");
			test_steps.add("Highest amount of Deposit policy name is : <b>"+policyName+"</b>");
			test_steps.add("Highest amount of Deposit policy text is : <b>"+policyText+"</b>");
			test_steps.add("highest amount of check-in policy Description is : <b>"+policyDesc+"</b>");
		}catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create two policies", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create two policies", testName, "Reservation", driver);
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
			reservationPage.select_Dates(driver, test_steps, checkInDate, checkOutDate, adultsForRes, childrenForRes, ratePlan, "","");
			reservationPage.clickOnFindRooms(driver, test_steps);		
	
			reservationPage.selectRoom(driver, test_steps, roomClassName1, Utility.RoomNo, "");
			reservationPage.selectRoom(driver, test_steps, roomClassName2, Utility.RoomNo, "");
			reservationPage.clickNext(driver, test_steps);
//			depositAmount=reservationPage.deposit(driver, test_steps, "", "");		
			reservationPage.enter_MRB_MailingAddress(driver, test_steps, salutation, guestFirstName, guestLastName, phoneNumber,
					altenativePhone, email, "", accountType, address1, address2, address3, city, country, state, postalCode, 
					isGuesProfile, "", "",roomNumber);
			if((account.equalsIgnoreCase("")||account.isEmpty())) {
				reservationPage.enter_PaymentDetails(driver, test_steps, paymentMethod, cardNumber, nameOnCard, cardExpDate);
			}
			reservationPage.enter_MarketSegmentDetails(driver, test_steps, "", "", referral);
			reservationPage.clickBookNow(driver, test_steps);
			reservation=reservationPage.get_ReservationConfirmationNumber(driver, test_steps);
			status=reservationPage.get_ReservationStatus(driver, test_steps);
			reservationPage.clickCloseReservationSavePopup(driver, test_steps);
			test_steps.add("========== Displaying amount details on trip summary ========");
			tripRoomCharges = reservationPage.getRoomChargeUnderTripSummary(driver, test_steps);
			tripTaxes = reservationPage.getTaxesFromTripSummary(driver);
			test_steps.add("Taxes and service charges captured at trip summary is : <b>"+tripTaxes+"</b>");			
			tripTotal = reservationPage.get_TripSummaryTripTotalChargesWithoutCurrency(driver, test_steps);
			tripPaid = reservationPage.getTripSummaryPaidAmount(driver, test_steps);
			tripBalance = reservationPage.get_TripSummaryBalance_Amount(driver, test_steps);	
		}catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create an MRB Reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create an MRB Reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		try {
			
			test_steps.add("===================== Verifying associated deposit policy details at Policies And Disclaimers tab =====================");
			reservationPage.verifyDepositPolicyDetailsAtPoliciesAndDisclaimers(driver, test_steps, policyName, policyText);
			test_steps.add("===================== Verifying amount details and icon at trip summary & folio tab =====================");
			reservationPage.verifyTripSummaryPaidAmount(driver, test_steps, policyAmount);
			reservationPage.click_Folio(driver, test_steps);
			reservationPage.verifyTripPaidAmountAtFolio(driver, test_steps, policyAmount);
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
			test_steps.add("===================== Deleting all policies created during test run =====================");
			navigation.Inventory(driver, test_steps);
			navigation.policies(driver, test_steps);
			policies.deleteAllPolicies(driver, test_steps, policyType, policyName1);
			policies.deleteAllPolicies(driver, test_steps, policyType, policyName2);
			
			test_steps.add("===================== Deleting all rate plans created during test run =====================");
			navigation.Inventory(driver);
			navigation.Rates1(driver);			
			rate.deleteAllRates(driver, test_steps, rateName1);
			rate.deleteAllRates(driver, test_steps, rateName2);

			test_steps.add("===================== Deleting all room classes created during test run =====================");
			navigation.Setup(driver, test_steps);
			navigation.RoomClass(driver, test_steps);
			roomClass.deleteAllRoomClasses(driver, roomClassName1, test_steps);
			roomClass.deleteAllRoomClasses(driver, roomClassName2, test_steps);
			
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
		}catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to delete test data created during execution", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to delete test data created during execution", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
	}
			
	@DataProvider
	public Object[][] getData() {
		return Utility.getData("verifyDepositPolicyMRB", excel);
	}

	@AfterClass(alwaysRun=true)
    public void closeDriver() {
        driver.quit();
    }

}
