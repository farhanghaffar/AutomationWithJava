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

public class VerifyHighestCheckInPolicyForMRBRes extends TestCore{

	
	private WebDriver driver = null;
	ArrayList<String> test_catagory = new ArrayList<>();
	ArrayList<String> test_description = new ArrayList<>();
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> test_name = new ArrayList<>();
	ArrayList<String> totalNights = new ArrayList<>();
	ArrayList<String> baseAmounts = new ArrayList<>();
	ArrayList<String> policyAmounts = new ArrayList<>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	ArrayList<String> roomNumber = new ArrayList<>();	
	ArrayList<String> roomNumberAssigned = new ArrayList<>();	

	String testName = this.getClass().getSimpleName().trim();
	String policyFor = "Capture", roomClassName1, roomClassName2, roomClassAbb1, roomClassAbb2, rateName1, rateName2,
	policyName1, policyName2, policyText1, policyDesc1, policyText2, policyDesc2, roomClassNameWithoutNum,
	rateNameWithoutNum, policyNameWithoutNum, typeToValidate, policyDesc, policyText, reservation=null,
	status=null, ratePlan1, paymentIconInFolio, taxes;
	Double depositAmount=0.0, paidDeposit=0.0;

	
	@BeforeTest(alwaysRun=true)
	public void checkRunMode() {
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Policy")
	public void verifyHighestCheckInPolicyForMRBRes(String roomClassName, String roomClassAbb, String maxAdults, 
			String maxPersons, String roomQuantity, String rateName, String ratePlan, String baseAmount,String additionalAdult, 
			String additionalChild, String displayName, String ratePolicy, String rateDescription, 
			String associateSeason, String policyName, String policyType, String policyAmount, String source, String policySeason,
			String checkInDate, String checkOutDate, String adultsForRes, String childrenForRes,
			String salutation, String guestFirstName,String guestLastName, String phoneNumber, String altenativePhone, String email,
			String account, String accountType, String address1, String address2, String address3, String city, String country,
			String state, String postalCode, String isGuesProfile, String paymentMethod, String cardNumber, String nameOnCard, 
			String cardExpDate, String referral, String authorizeIconSrc) throws Exception {
		
		String testDescription = "Verify check-in functionality when there is check in policy different for each room class<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682462' target='_blank'>"
				+ "Click here to open TestRail: C682462</a><br/>";
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
			
			roomClassName1 = roomClassName.replace("ClassFor", policyFor);
			roomClassNameWithoutNum = roomClassName1.replace("RandomNum", "");
			roomClassName1 = roomClassName1.replace("RandomNum", randomNum1);
			roomClassAbb1 = roomClassAbb.replace("RandomNum", randomNum1);

			roomClassName2 = roomClassName.replace("ClassFor", policyFor);
			roomClassName2 = roomClassName2.replace("RandomNum", randomNum2);
			roomClassAbb2 = roomClassAbb.replace("RandomNum", randomNum2);

			rateName1 = rateName.replace("ClassFor", policyFor);
			rateNameWithoutNum = rateName1.replace("RandomNum", "");
			rateName1 = rateName1.replace("RandomNum", randomNum1);
			
			ratePlan1 = ratePlan.split("\\|")[0];
			rateName2 = rateName.replace("ClassFor", policyFor);
			rateName2 = rateName2.replace("RandomNum", randomNum2);

			policyName1 = policyName.replace("ClassFor", policyFor);
			policyNameWithoutNum = policyName1.replace("RandomNum", "");
			policyName1 = policyName1.replace("RandomNum", randomNum1);

			policyName2 = policyName.replace("ClassFor", policyFor);
			policyNameWithoutNum = policyName2.replace("RandomNum", "");
			policyName2 = policyName2.replace("RandomNum", randomNum2);

			policyText1 = policyName1+"_Text";
			policyDesc1 = policyName1+"_Description";

			policyText2 = policyName2+"_Text";
			policyDesc2 = policyName2+"_Description";

			guestLastName = guestLastName.replace("User", "User"+randomNum1);
			String baseAmountSize[] = baseAmount.split("\\|");
			for (int i = 0; i < baseAmountSize.length; i++) {
				baseAmounts.add(baseAmountSize[i]);
			}
			String policyAmountSize[] = policyAmount.split("\\|");
			for (int i = 0; i < policyAmountSize.length; i++) {
				policyAmounts.add(policyAmountSize[i]);
			}
			if (policyFor.equalsIgnoreCase("Authorize")) {
				typeToValidate = "Authorization Only";
				authorizeIconSrc = "/Folio_Images/7.gif";
				paymentIconInFolio = "A";
			}else if (policyFor.equalsIgnoreCase("Capture")) {
				typeToValidate = "Capture";
				authorizeIconSrc = "/Folio_Images/8.gif";
				paymentIconInFolio = "Capture";
				}
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

		try {
			driver = getDriver();
            login_CP(driver, test_steps);
			test_steps.add("========== Creating 1st room class ==========");
 			navigation.Setup(driver, test_steps);
			navigation.RoomClass(driver, test_steps);
			roomClass.deleteAllRoomClasses(driver, roomClassNameWithoutNum, test_steps);
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
			for (int i = 0; i <Integer.parseInt(roomQuantity) ; i++) {
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
			rate.deleteAllRates(driver, test_steps, rateNameWithoutNum);
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
			policies.deleteAllPolicies(driver, test_steps, policyType, policyNameWithoutNum);
			policies.createPolicy(driver, test_steps, policyName1, policyType, policyFor, 
					policyAmounts.get(0), null, null, source, policySeason, roomClassName1, ratePlan1, policyText1, policyDesc1);			

			test_steps.add("========== Creating 2nd policy and Associating with 2nd room class ==========");
			navigation.Inventory(driver, test_steps);
			navigation.policies(driver, test_steps);
			policies.createPolicy(driver, test_steps, policyName2, policyType, policyFor, 
					policyAmounts.get(1), null, null, source, policySeason, roomClassName2, ratePlan1, policyText2, policyDesc2);			

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
			depositAmount=reservationPage.deposit(driver, test_steps, "", "");		
			reservationPage.clickNext(driver, test_steps);
			taxes = reservationPage.getTaxesFromTripSummary(driver);
			depositAmount=reservationPage.deposit(driver, test_steps, "", "");		
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
			int policyAmountCal1 = Integer.parseInt(policyAmounts.get(0));
			int policyAmountCal2 = Integer.parseInt(policyAmounts.get(1));
			if (policyAmountCal1>policyAmountCal2) {
				policyText = policyText1;
				policyName = policyName1;
				policyAmount = policyAmounts.get(0);
			}else {
				policyText = policyText2;
				policyName = policyName2;
				policyAmount = policyAmounts.get(1);
			}
			test_steps.add("===================== Verifying associated check-in policy details at Policies And Disclaimers tab =====================");
			reservationPage.verifyCheckInPolicy(driver, test_steps, policyName, policyText);
			test_steps.add("===================== Verifying guest and policy details on payment check-in popup =====================");
			reservationPage.clickCheckInAllButton(driver, test_steps);

			reservationPage.selectRoomAtPaymentCheckInPopup(driver, test_steps, roomNumberAssigned);
			reservationPage.disableGenerateGuestReportToggle(driver, test_steps);
			reservationPage.clickOnProceedToCheckInPaymentButton(driver, test_steps);
			ArrayList<String> amountDetails = reservationPage.paymentCheckInPopupVerify(driver, test_steps, "Check In Payment", 
					cardExpDate, nameOnCard, "XXXX "+cardNumber.substring(cardNumber.length()-4), paymentMethod, 
					typeToValidate, policyAmount, policyFor, policyName, policyText, baseAmounts, totalNights, taxes, null, null);
			reservationPage.clickOnPayButtonOnPaymentPopup(driver, test_steps);
			String abc = paymentMethod+" - "+Utility.getCardNumberHidden(cardNumber)+" ("+nameOnCard+") (Exp. "+cardExpDate+")";
			test_steps.add("===================== Verifying guest and policy details in payment success popup =====================");
			reservationPage.checkInPaymentSuccessPopupVerify(driver, test_steps, "Check-In Successful", "Approved", abc, 
					amountDetails.get(0), typeToValidate, amountDetails.get(1), policyName, policyFor, policyText);
			reservationPage.checkInPaymentSuccessPopupClose(driver, test_steps);	
			test_steps.add("===================== Verifying associated check-in policy details at Policies And Disclaimers tab =====================");
			reservationPage.close_FirstOpenedReservation(driver, test_steps);
			String firstGuestName = guestFirstName.split("\\|")[0]+" "+guestLastName.split("\\|")[0];
			reservationPage.searchWithGuestName(driver, test_steps, firstGuestName);
			reservationPage.clickOnGuestName(driver, test_steps);
			reservationPage.verifyCheckInPolicy(driver, test_steps, policyName, policyText);

			test_steps.add("===================== Verifying trip amount details at trip summary =====================");
			reservationPage.verifyTripSummeryAmount(driver, test_steps, baseAmounts, totalNights, amountDetails.get(0), amountDetails.get(1), policyFor);
			test_steps.add("===================== Verifying trip amount details in trip summary header=====================");
			reservationPage.verifyTripAmountDetailsAtTop(driver, test_steps, amountDetails.get(0), amountDetails.get(1), policyFor);
		
			reservationPage.click_Folio(driver, test_steps);
			test_steps.add("===================== Verifying folio line items and amount after check-in =====================");
			reservationPage.verifyFolioLineItem(driver, test_steps, baseAmounts, totalNights, amountDetails.get(0), paymentMethod, amountDetails.get(1), policyFor);			
			folio.verifyLineItemIcon(driver, test_steps, paymentMethod, TestCore.envURL + authorizeIconSrc,
					" <b>"+paymentIconInFolio+"</b> icon at folio line item");
			reservationPage.click_History(driver, test_steps);
			String expDesc = " payment for $"+amountDetails.get(1)+" using ("+paymentMethod+"-"+
			Utility.getCardNumberHidden(cardNumber)+" "+cardExpDate+")";
			reservationPage.verifyAuthAmountInFolioHistory(driver, test_steps, expDesc, "PAYMENT", policyFor);
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
		return Utility.getData("verifyCheckInPolicyMRB", excel);
	}

	@AfterClass(alwaysRun=true)
    public void closeDriver() {
        driver.quit();
    }

}
