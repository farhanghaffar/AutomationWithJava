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
import com.innroad.inncenter.pageobjects.Properties;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.Reports;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyDepositRequiredAndPaymentSuccess extends TestCore{
	private WebDriver driver = null;
	public static String test_name = null;
	public static String test_nameOne = null;
	public static String test_description = null;
	public static String test_catagory = null;
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	Navigation navigation = new Navigation();
	Properties properties = new Properties();
	RoomClass roomClass = new RoomClass();
	Reports report = new Reports();
	Rate rate = new Rate();
	Policies policies = new Policies();
	CPReservationPage reservationPage = new CPReservationPage();
	ArrayList<String> roomNumberAssigned = new ArrayList<>();
	String  propertyName = null, roomClassNames = null, roomClassAbbs = null,
			roomClassNameWithoutNum = null, roomClassAvvWithoutNum = null, rateNameWithoutNum = null, randomNum = null,
			rateName = null, yearDate = null, reservation = null, balance = null,policyname=null,policyNameWithoutNum=null,
			tripTaxes = null, tripTotal = null, depositAmount = null, date=null;
String testName = this.getClass().getSimpleName().trim();
	
	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
		
	}
	
	private void handelingCatchError(Error e, String desc, String category, String module) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
			Utility.updateReport(e, desc, category, module, driver);
		} else {
			Assert.assertTrue(false);
		}
	}

	private void handelingCatchException(Exception e, String desc, String category, String module) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
			Utility.updateReport(e, desc, category, module, driver);
		} else {
			Assert.assertTrue(false);
		}
	}
	@Test(dataProvider = "getData", groups = "Reservation")
	public void verifyDepositRequiredAndPaymentSuccess(String roomClassName, String roomClassAbbrivation, String maxAdults,
			String maxPersons, String roomQuantity, String ratesNames, String displayName, String baseAmount,
			String ratePolicy, String rateDescription, String policyType,String policyName,String polictAttr, String attrValue,
			String taxAmout,String ratePlanName,String policyText,String policyDesc,String source, 
			String seasons, String checkInDate, String checkOutDate, String salutation,
			String guestFirstName, String guestLastName, String paymentType, String cardNumber, String nameOnCard,
			String marketSegment, String referral) {
			test_description = testName + "<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682424' target='_blank'>"
				+ "Click here to open TestRail: C682424</a><br>";
		test_catagory = "CPReservation_CheckOut";
		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		roomClassNameWithoutNum = roomClassName;
		roomClassAvvWithoutNum = roomClassAbbrivation;
		rateNameWithoutNum = ratesNames;
		randomNum = Utility.GenerateRandomNumber();
		roomClassNames = roomClassNameWithoutNum+"_" + randomNum;
		roomClassAbbs = roomClassAvvWithoutNum +"_"+ randomNum;
		rateName = rateNameWithoutNum+"_" + randomNum;
		 policyNameWithoutNum = policyName;
	

		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);
			} else {
				Utility.reTry.replace(testName, 1);
			}

		
			// Get CheckIN and Checkout Date

			if (!(Utility.validateInput(checkInDate))) {
				for (int i = 0; i < guestFirstName.split("\\|").length; i++) {
					checkInDates.add(Utility.getCurrentDate("dd/MM/yyyy"));
					checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(1), "MM/dd/yyyy", "dd/MM/yyyy"));
				}
			} else {
				checkInDates = Utility.splitInputData(checkInDate);
				checkOutDates = Utility.splitInputData(checkOutDate);
			}
			checkInDate = checkInDates.get(0);
			checkOutDate = checkOutDates.get(0);
			app_logs.info(checkInDate);
			app_logs.info(checkOutDate);
			date=Utility.parseDate(checkInDate, "dd/MM/yyyy", "MM/dd/yy");
			app_logs.info(date);

		} catch (Exception e) {
			handelingCatchException(e, "Failed to Get Check In Date and Checkout Date", testName, "Pre-Requisites");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Get Check In Date and Checkout Date", testName, "Pre-Requisites");
		}

		// Login
		try {
			driver = getDriver();
			login_CP(driver);
			test_steps.add("Logged into the application");
			app_logs.info("Logged into the application");
			
			// Get Property Name
			propertyName = properties.getProperty(driver, test_steps);


		} catch (Exception e) {
			handelingCatchException(e, "Failed to login", testName, "Login");
		} catch (Error e) {
			handelingCatchError(e, "Failed to login", testName, "Login");
		}
		// Select Allow non-zero balance at the time of check-out from Properties
		try {
			test_steps.add("<b>========== Set Properties========</b>");
			 navigation.setup(driver);
			 test_steps.add("Navigated to Setup");
			navigation.properties(driver);
			test_steps.add("Navigated to Properties");
			navigation.open_Property(driver, test_steps, propertyName);
			navigation.click_PropertyOptions(driver, test_steps);
			properties.depositRequiredForSaveGaurenteedReservationCheckbox(driver, true, test_steps);
			properties.uncheck_GuaranteedCheckBoxProperties(driver, test_steps, config.getProperty("flagOn"));
			properties.PublishButton(driver);
			test_steps.add("Click Publish Button");
		} catch (Exception e) {
			handelingCatchException(e, "Failed to Select Option form Properties", testName, "Properties");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Select Option form Properties", testName, "Properties");
		}

		// Logged Out Login Again
		try {
			driver.navigate().refresh();
			Utility.logoutToInnCenter(driver, test_steps);
			login_CP(driver);
			test_steps.add("Logged into the application");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			handelingCatchException(e, "Failed to Logout Login", testName, "Login");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Logout Login", testName, "Login");
		}

		try {
			test_steps.add("<b>========== Creating Room Class ==========</b>");
			navigation.mainSetupManu(driver);
			navigation.roomClass(driver, test_steps);
			roomClass.deleteAllRoomClasses(driver, roomClassName, test_steps);
			navigation.clickOnNewRoomClassButton(driver, test_steps);
			roomClass.create_RoomClass(driver, roomClassNames, roomClassAbbs, null, maxAdults, maxPersons, roomQuantity,
					test, test_steps);
			roomNumberAssigned.add(Utility.RoomNo);
			for (int i = 0; i < roomNumberAssigned.size(); i++) {
				roomNumberAssigned.get(i);
			}

			app_logs.info("Rooms Are: " + roomNumberAssigned);

		} catch (Exception e) {
			handelingCatchException(e, "Failed to Create Room Class", testName, "Room Class");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Create Room Class", testName, "Room Class");
		}
		// Create Rates
		try {
			test_steps.add("<b>========== Creating Rate Plan and Associating with Room Class ==========</b>");
			navigation.inventory(driver);
			navigation.rates1(driver);
			rate.deleteAllRates(driver, test_steps, rateNameWithoutNum);
			rate.createRate(driver, rateName, maxAdults, maxPersons, baseAmount, maxAdults, maxPersons, displayName,
					ratePolicy, rateDescription, roomClassNames, test_steps);
		}

		catch (Exception e) {
			handelingCatchException(e, "Failed to Create Rates", testName, "Rates");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Create Rates", testName, "Rates");
		}
		
		try
		{
			test_steps.add("<b>========== Creating Policy ==========</b>");
			navigation.inventory(driver);
			test_steps.add("Navigate to Inventory");
			navigation.policies(driver, test_steps);
			policyname = policyName +"_"+ randomNum;
			policies.deleteAllPolicies(driver, test_steps, policyType, policyNameWithoutNum);
			policies.createPolicy(driver, test_steps, policyname, policyType, polictAttr, attrValue, null, null,source, seasons, roomClassNames, ratePlanName, policyText, policyDesc);
	
		}catch (Exception e) {
			handelingCatchException(e, "Failed to Create Policy", testName, "Rates");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Create Policy", testName, "Rates");
		}
		// Create Reservation
		try {
			test_steps.add("<b>==========Start Creating Reservation==========</b>");
			navigation.reservation_Backward_1(driver);
			app_logs.info("Navigate to Reservation");
			reservationPage.click_NewReservation(driver, test_steps);
			reservationPage.select_CheckInDate(driver, test_steps, checkInDate);
			reservationPage.select_CheckoutDate(driver, test_steps, checkOutDate);
			reservationPage.enter_Adults(driver, test_steps, maxAdults);
			reservationPage.clickOnFindRooms(driver, test_steps);
			reservationPage.select_SpecificRoom(driver, test_steps, roomClassNames, "", "");
			tripTotal = reservationPage.getTripSummaryTripTotalAmountBeforeReservation(driver, test_steps);
			app_logs.info("Trip Total" +tripTotal);
			test_steps.add("Trip Total captured at trip summary is : <b>"+tripTotal+"</b>");
			test_steps.add("<b>==========Verify Deposit Due Amount while Creating Reservation==========</b>");
			depositAmount=reservationPage.calculationOfDeposiAmountToBePaid(null, polictAttr, attrValue, taxAmout, tripTotal);			
			reservationPage.verifyDepositDueAmount(driver, test_steps, depositAmount);
			reservationPage.clickNext(driver, test_steps);
			yearDate = Utility.getFutureMonthAndYearForMasterCard(driver, test_steps);
			guestFirstName = guestFirstName + randomNum;
			guestLastName = guestLastName + randomNum;
			reservationPage.enter_MailingAddress_OnUnchecked_CreateGuestProfile(driver, test_steps, salutation,
					guestFirstName, guestLastName, config.getProperty("flagOff"));
			reservationPage.enter_PaymentDetails(driver, test_steps, paymentType, cardNumber, nameOnCard, yearDate);
			reservationPage.enter_MarketSegmentDetails(driver, test_steps, "", marketSegment, referral);
			reservationPage.clickBookNow(driver, test_steps);
			reservation = reservationPage.get_ReservationConfirmationNumber(driver, test_steps);
			reservationPage.clickCloseReservationSavePopup(driver, test_steps);
		} catch (Exception e) {
			handelingCatchException(e, "Failed to Create Reservation", testName, "Reservation");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Create Reservation", testName, "Reservation");
		}
		
		
		try
		{
			test_steps.add("<b>========Start Verifying Policies and Disclaimers========</b>");
			reservationPage.verifyDepositPolicyDetailsAtPoliciesAndDisclaimers(driver, test_steps, policyname, policyText);
			test_steps.add("<b>========Start Verifying Guaranteed Status========</b>");
			reservationPage.verifyReservationStatusStatus(driver, test_steps, config.getProperty("guaranteedStatus"));
			test_steps.add("<b>========Start Verifying Trip Summary========</b>");
			String tripSummaryPaid = reservationPage.getTripSummaryPaidAmount(driver, test_steps);
			reservationPage.verifyTripSummaryPaidAmount(driver, test_steps, tripSummaryPaid, depositAmount);
			double balance=Double.valueOf(tripTotal) -Double.valueOf(depositAmount);
			String balanceAmount=String.format("%.2f", balance);
			app_logs.info(balanceAmount);
			String tripSummaryBalance = reservationPage.get_TripSummaryBalance_Amount(driver, test_steps);
			reservationPage.verifyTripSummaryBalanceAmount(driver, test_steps, tripSummaryBalance, balanceAmount);
			test_steps.add("<b>========Start Verifying Folio Amount========</b>");
			reservationPage.click_Folio(driver, test_steps);
			reservationPage.verifyTripPaidAmountAtFolio(driver, test_steps, depositAmount);
			test_steps.add("<b>========Start Verifying History========</b>");
			reservationPage.click_History(driver, test_steps);
			app_logs.info(cardNumber.substring(cardNumber.length() - 4));
			reservationPage.verifyHistoryTabForPaymentDetail(driver, test_steps, date, depositAmount, false, null, paymentType, cardNumber.substring(cardNumber.length() - 4), yearDate);
		}catch (Exception e) {
			handelingCatchException(e, "Failed to Verify policy in reservation page", testName, "Reservation");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Verify policy in reservation page", testName, "Reservation");
		}
		
		try
		{
			test_steps.add("<b>========Start Deleting Rates========</b>");
			navigation.inventory(driver);
			navigation.rates1(driver);
			rate.deleteRates(driver, ratesNames);
			test_steps.add("All Rate Deleted Successfully With Name: <b>" + ratesNames + " </b>");
			app_logs.info("All Rate Deleted Successfully With Name: " + ratesNames);

		}catch (Exception e) {
			handelingCatchException(e, "Failed to Delete Rates", testName, "Inventory");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Delete Rates", testName, "Inventory");
		}
		
		try
		{
			test_steps.add("<b>========Delete Room Class========</b>");
			navigation.setup(driver);
			test_steps.add("Navigated to Setup");
			navigation.roomClass(driver, test_steps);
			roomClass.searchButtonClick(driver);
			test_steps.add("Click on Search Button");
			app_logs.info("Click on Search Button");
			roomClass.searchRoomClass(driver, roomClassName, test_steps);
			roomClass.deleteRoomClass(driver, roomClassName);
			test_steps.add("All Room Class Deleted Successfully With Name: <b>" + roomClassName + " </b>");
			app_logs.info("All Room Class Deleted Successfully With Name: " + roomClassName);

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
		}catch (Exception e) {
			handelingCatchException(e, "Failed to Delete Room Class", testName, "Setup");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Delete Room Class", testName, "Setup");
		}

	}
	
	
	
	@DataProvider
	public Object[][] getData() {

		return Utility.getData("VerifyDepositRequiredAndPayment", excel);

	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}


}
