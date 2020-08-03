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

public class VerifyHighestCheckInPolicyAmountApplied extends TestCore{

	
	private WebDriver driver = null;
	ArrayList<String> test_catagory = new ArrayList<>();
	ArrayList<String> test_description = new ArrayList<>();
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> test_name = new ArrayList<>();
	ArrayList<String> totalNights = new ArrayList<>();
	ArrayList<String> baseAmounts = new ArrayList<>();
	ArrayList<String> policyAmounts = new ArrayList<>();
	ArrayList<String> policyNames = new ArrayList<>();
	ArrayList<String> policyTexts = new ArrayList<>();
	ArrayList<String> policyDescs = new ArrayList<>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	ArrayList<String> policiesFor = new ArrayList<>();
	ArrayList<String> randomNumbers = new ArrayList<>();
	ArrayList<String> roomNumber = new ArrayList<>();	
	ArrayList<String> roomNumberAssigned = new ArrayList<>();	

	String testName = this.getClass().getSimpleName().trim();


	String randomNum, roomClassNameWithoutNum, rateNameWithoutNum, policyNameWithoutNum, typeToValidate,
		paymentIconInFolio, numberOfPolicies[], taxes, reservation, status, guestFullName;
	Double depositAmount=0.0, paidDeposit=0.0;

	int highestPolicyAmount = 0;
	int indexOfHighestPolicy = 0;

	@BeforeTest(alwaysRun=true)
	public void checkRunMode() {
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Policy")
	public void verifyHighestCheckInPolicyAmountApplied(String roomClassName, String roomClassAbb, String maxAdults, 
			String maxPersons, String roomQuantity, String rateName, String ratePlan, String baseAmount,String additionalAdult, 
			String additionalChild, String displayName, String ratePolicy, String rateDescription, 
			String associateSeason, String policyName, String policyType, String policyAmount, String policyFor,
			String source, String policySeason, String checkInDate, String checkOutDate, String adultsForRes, String childrenForRes,
			String salutation, String guestFirstName,String guestLastName, String phoneNumber, String altenativePhone, String email,
			String account, String accountType, String address1, String address2, String address3, String city, String country,
			String state, String postalCode, String isGuesProfile, String paymentMethod, String cardNumber, String nameOnCard, 
			String cardExpDate, String referral, String authorizeIconSrc) throws Exception {
		
		String testDescription = "Verify the behavior when more than one Check-in policy is applied at the time of check-in<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682470' target='_blank'>"
				+ "Click here to open TestRail: C682470</a><br/>";
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
    		if ( !(Utility.validateInput(checkInDate)) ) {
    			for (int i = 0; i < guestFirstName.split("\\|").length; i++) {
    				checkInDates.add(Utility.getCurrentDate("dd/MM/yyyy"));
    				checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(2), "MM/dd/yyyy", "dd/MM/yyyy"));
    			}
    		}else {
    			checkInDates = Utility.splitInputData(checkInDate);
    			checkOutDates = Utility.splitInputData(checkOutDate);
    		}
    		for (int i = 0; i < checkInDates.size(); i++) {
    			totalNights.add(Utility.differenceBetweenDates(checkInDates.get(i), checkOutDates.get(i)));
    		}
    		randomNum = Utility.GenerateRandomNumber();
    		roomClassNameWithoutNum = roomClassName.replace("RandomNum", "");
    		roomClassName = roomClassName.replace("RandomNum", randomNum);
    		roomClassAbb = roomClassAbb.replace("RandomNum", randomNum);
    		baseAmounts.add(baseAmount);
    		rateNameWithoutNum = rateName.replace("RandomNum", "");
    		rateName = rateName.replace("RandomNum", randomNum);
    		guestLastName = guestLastName+randomNum;
    		guestFullName = guestFirstName+" "+guestLastName;
    		policyNameWithoutNum = policyName.replace("ClassForRandomNum", "");
    		numberOfPolicies = policyAmount.split("\\|");
    		for (int i = 0; i < numberOfPolicies.length; i++) {
    			policyAmounts.add(policyAmount.split("\\|")[i]);
    			policiesFor.add(policyFor.split("\\|")[i]);
    			randomNumbers.add(randomNum+i);
    			String policyName1 = policyName.replace("ClassFor", policiesFor.get(i));
    			policyName1 = policyName1.replace("RandomNum", randomNumbers.get(i));
    			policyNames.add(policyName1);
    			policyTexts.add(policyNames.get(i)+"_Text");
    			policyDescs.add(policyNames.get(i)+"_Description");
    		}
    		for (int i = 0; i < numberOfPolicies.length; i++) {
    			if (highestPolicyAmount < Integer.parseInt(policyAmounts.get(i))) {
    				highestPolicyAmount = Integer.parseInt(policyAmounts.get(i));
    				indexOfHighestPolicy = i;
    			}
    		}
    		if (policiesFor.get(indexOfHighestPolicy).equalsIgnoreCase("Authorize")) {
    			typeToValidate = "Authorization Only";
    			authorizeIconSrc = "/Folio_Images/7.gif";
    			paymentIconInFolio = "A";
    		}else if (policiesFor.get(indexOfHighestPolicy).equalsIgnoreCase("Capture")) {
    			typeToValidate = "Capture";
    			authorizeIconSrc = "/Folio_Images/8.gif";
    			paymentIconInFolio = "Capture";
    		}			
		}catch (Exception e) {
			System.out.println(e);
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
			test_steps.add("========== Creating new room class ==========");
 			navigation.Setup(driver, test_steps);
			navigation.RoomClass(driver, test_steps);
			roomClass.deleteAllRoomClasses(driver, roomClassNameWithoutNum, test_steps);
			navigation.clickOnNewRoomClassButton(driver, test_steps);
			roomClass.CreateRoomClass(driver, roomClassName, roomClassAbb, null,
					maxAdults, maxPersons, roomQuantity, test, test_steps);    
			roomNumberAssigned.add(Utility.RoomNo);
			roomClass.CloseOpenedRoomClass(driver, roomClassName, test_steps);
			
		}catch (Exception e) {
			System.out.println(e);
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create new room class", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create new room class", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		try {
			test_steps.add("========== Creating a new rate plan and Associating with created room class ==========");
			navigation.Inventory(driver);
			navigation.Rates1(driver);			
			rate.deleteAllRates(driver, test_steps, rateNameWithoutNum);
			rate.new_Rate(driver, rateName, ratePlan, maxAdults, maxPersons, baseAmounts.get(0),
					additionalAdult, additionalChild, displayName, associateSeason, ratePolicy, 
					rateDescription, roomClassName, test_steps);
			
		}catch (Exception e) {
			System.out.println(e);
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create new rate plan", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create new rate plan", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		try {
 			navigation.Inventory(driver, test_steps);
			navigation.policies(driver, test_steps);
			policies.deleteAllPolicies(driver, test_steps, policyType, policyNameWithoutNum);
			policies.createMultiplePolicies(driver, test_steps, numberOfPolicies, policyNames, 
					policyType, policiesFor, policyAmounts, source, policySeason, roomClassName, 
					ratePlan, policyTexts, policyDescs);

			test_steps.add("===================== Capturing highest amount of check-in policy details should be applied =====================");
			test_steps.add("<b>"+policyAmounts.get(indexOfHighestPolicy)+"</b> % amount of total balance for <b>"+policiesFor.get(indexOfHighestPolicy)+"</b>"
					+ " is the highest amount of check-in policy from all inputs");
			test_steps.add("highest amount of check-in policy name is : <b>"+policyNames.get(indexOfHighestPolicy)+"</b>");
			test_steps.add("highest amount of check-in policy text is : <b>"+policyTexts.get(indexOfHighestPolicy)+"</b>");
			test_steps.add("highest amount of check-in policy Description is : <b>"+policyDescs.get(indexOfHighestPolicy)+"</b>");
			test_steps.add("highest amount of check-in policy applied for : <b>"+policiesFor.get(indexOfHighestPolicy)+"</b>");
			
		}catch (Exception e) {
			System.out.println(e);
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create multiple policies", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create multiple policies", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		
		try {
			try {
				navigation.Reservation_Backward_1(driver);
			} catch (Exception e) {
				navigation.Reservation_Backward_3(driver);
			}	
			test_steps.add("===================== Creating a new reservation for created room class =====================");

			reservationPage.click_NewReservation(driver, test_steps);
			reservationPage.select_CheckInDate(driver, test_steps, checkInDates.get(0));
			reservationPage.select_CheckoutDate(driver, test_steps, checkOutDates.get(0));
			reservationPage.enter_Adults(driver, test_steps, adultsForRes);
			reservationPage.enter_Children(driver, test_steps, childrenForRes);
			reservationPage.select_Rateplan(driver, test_steps, ratePlan,"");
			reservationPage.clickOnFindRooms(driver, test_steps);
			reservationPage.selectRoom(driver, test_steps, roomClassName, Utility.RoomNo, "");
			depositAmount=reservationPage.deposit(driver, test_steps, "", "");		
			reservationPage.clickNext(driver, test_steps);
			taxes = reservationPage.getTaxesFromTripSummary(driver);
			test_steps.add("Taxes and service charges captured during reservation creation is <b>"+taxes+"</b>");
			reservationPage.enter_MailingAddress(driver, test_steps, salutation, guestFirstName, guestLastName,
					phoneNumber, altenativePhone, email, account, accountType, address1, address2, address3, city,
					country, state, postalCode, isGuesProfile);
				if((account.equalsIgnoreCase("")||account.isEmpty())) {
				reservationPage.enter_PaymentDetails(driver, test_steps, paymentMethod, cardNumber, guestFullName, cardExpDate);
			}
			reservationPage.enter_MarketSegmentDetails(driver, test_steps, "", "", referral);
			reservationPage.clickBookNow(driver, test_steps);
			reservation=reservationPage.get_ReservationConfirmationNumber(driver, test_steps);
			status=reservationPage.get_ReservationStatus(driver, test_steps);
			reservationPage.clickCloseReservationSavePopup(driver, test_steps);
			
			String roomNumber1 = reservationPage.get_RoomNumber(driver, test_steps, "");
			roomNumber.add(roomNumber1);
						
		}catch (Exception e) {
			System.out.println(e);
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create a new reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to create a new reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		try {
			test_steps.add("===================== Verifying associated check-in policy details at Policies And Disclaimers tab =====================");
			reservationPage.verifyCheckInPolicy(driver, test_steps, policyNames.get(indexOfHighestPolicy), policyTexts.get(indexOfHighestPolicy));
			test_steps.add("===================== Verifying guest and policy details on payment check-in popup =====================");
			reservationPage.Click_CheckInButton(driver, test_steps);
			reservationPage.selectRoomAtPaymentCheckInPopup(driver, test_steps, roomNumberAssigned);
			reservationPage.disableGenerateGuestReportToggle(driver, test_steps);
			reservationPage.clickOnProceedToCheckInPaymentButton(driver, test_steps);
			ArrayList<String> amountDetails = reservationPage.paymentCheckInPopupVerify(driver, test_steps, "Check In Payment", 
					cardExpDate, guestFullName, "XXXX "+cardNumber.substring(cardNumber.length()-4), paymentMethod, 
					typeToValidate, policyAmounts.get(indexOfHighestPolicy), policiesFor.get(indexOfHighestPolicy), policyNames.get(indexOfHighestPolicy), 
					policyTexts.get(indexOfHighestPolicy), baseAmounts, totalNights, taxes, null, null);
			reservationPage.clickOnPayButtonOnPaymentPopup(driver, test_steps);
			String abc = paymentMethod+" - "+Utility.getCardNumberHidden(cardNumber)+" ("+guestFullName+") (Exp. "+cardExpDate+")";
			test_steps.add("===================== Verifying guest and policy details in payment success popup =====================");
			reservationPage.checkInPaymentSuccessPopupVerify(driver, test_steps, "Check-In Successful", "Approved", abc, 
					amountDetails.get(0), typeToValidate, amountDetails.get(1), policyNames.get(indexOfHighestPolicy), 
					policiesFor.get(indexOfHighestPolicy), policyTexts.get(indexOfHighestPolicy));
			reservationPage.checkInPaymentSuccessPopupClose(driver, test_steps);	
			test_steps.add("===================== Verifying associated check-in policy details at Policies And Disclaimers tab =====================");
			reservationPage.close_FirstOpenedReservation(driver, test_steps);
			reservationPage.searchWithGuestName(driver, test_steps, guestFullName);
			reservationPage.clickOnGuestName(driver, test_steps);
			
			test_steps.add("===================== Verifying trip amount details at trip summary =====================");
			reservationPage.verifyTripSummeryAmount(driver, test_steps, baseAmounts, totalNights, amountDetails.get(0), amountDetails.get(1), 
					policiesFor.get(indexOfHighestPolicy));
			test_steps.add("===================== Verifying trip amount details in trip summary header=====================");
			reservationPage.verifyTripAmountDetailsAtTop(driver, test_steps, amountDetails.get(0), amountDetails.get(1), policiesFor.get(indexOfHighestPolicy));
			reservationPage.click_Folio(driver, test_steps);
			test_steps.add("===================== Verifying folio line items and amount after check-in =====================");
			reservationPage.verifyFolioLineItem(driver, test_steps, baseAmounts, totalNights, amountDetails.get(0), paymentMethod, amountDetails.get(1), 
					policiesFor.get(indexOfHighestPolicy));			
			folio.verifyLineItemIcon(driver, test_steps, paymentMethod, TestCore.envURL + authorizeIconSrc,
					" <b>"+paymentIconInFolio+"</b> icon at folio line item");
			reservationPage.click_History(driver, test_steps);
			String expDesc = " payment for $"+amountDetails.get(1)+" using ("+paymentMethod+"-"+
			Utility.getCardNumberHidden(cardNumber)+" "+cardExpDate+")";
			reservationPage.verifyAuthAmountInFolioHistory(driver, test_steps, expDesc, "PAYMENT", policiesFor.get(indexOfHighestPolicy));
						
		}catch (Exception e) {
			System.out.println(e);
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to verify associated policy and payment details", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to verify associated policy and payment details", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			test_steps.add("===================== Deleting policies created during test run =====================");
			navigation.Inventory(driver, test_steps);
			navigation.policies(driver, test_steps);
			policies.deleteAllPolicies(driver, test_steps, policyType, policyNameWithoutNum);
//			for (int i = 0; i < numberOfPolicies.length; i++) {
//				policies.deleteAllPolicies(driver, test_steps, policyType, policyNames.get(i));
//			}		
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
		}catch (Exception e) {
			System.out.println(e);
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to delete test data created during test execution", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to delete test data created during test execution", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
	}
	

	@DataProvider
	public Object[][] getData() {
		return Utility.getData("verifyHighestCheckInPolicy", excel);
	}

	@AfterClass(alwaysRun=true)
    public void closeDriver() {
        driver.quit();
    }

}
