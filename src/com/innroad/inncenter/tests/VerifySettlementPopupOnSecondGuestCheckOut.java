package com.innroad.inncenter.tests;

import java.util.ArrayList;
import java.util.List;

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
import com.innroad.inncenter.properties.OR_Reservation;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class VerifySettlementPopupOnSecondGuestCheckOut extends TestCore
{
	private WebDriver driver = null;
	public static String test_name = null;
	public static String test_nameOne = null;
	public static String test_description = null;
	public static String test_catagory = null;
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	Navigation navigation = new Navigation();
	Rate rate = new Rate();
	Policies policies = new Policies();
	Folio folio = new Folio();
	CPReservationPage reservationPage = new CPReservationPage();
	ArrayList<String> roomNumberAssigned = new ArrayList<>();
	ArrayList<String> roomAbbri = new ArrayList<String>();
	ArrayList<String> roomNumber = new ArrayList<>();	
	List<String> roomNos = new ArrayList<String>();
	RoomClass roomClass = new RoomClass();
	String   roomClassNames = null, roomClassAbbs = null,
			 rateNameWithoutNum = null, randomNum = null,
			rateName = null, yearDate = null, reservation = null, policyNameWithoutNum=null,policyname=null,
					balanceAmount=null,testName=null;			
	
	
	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		testName="";
		testName=  this.getClass().getSimpleName().trim();
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
	public void verifySettlementPopupOnSecondGuestCheckOut(String withBalance,String roomClassName, String roomClassAbbrivation, String maxAdults,
			String maxPersons, String roomQuantity, String ratesNames, String displayName, String baseAmount,
			String ratePolicy, String rateDescription, String policyType,String policyName,String polictAttr, String attrValue,
			String ratePlanName,String policyText,String policyDesc,String source, 
			String seasons,String checkInDate, String checkOutDate,String adults,String children,String ratePlan,String salutation,
			String guestFirstName, String guestLastName, String paymentType, String cardNumber, String nameOnCard,
			String marketSegment, String referral, String secondRoomNo, String checkOutRoom,String firstRoomNo, String balanceAmt)
	{
		testName="";
		testName=  this.getClass().getSimpleName().trim();
		if(withBalance.equals("No"))
		{
			
		testName=testName + "withoutbalance";
		test_description = testName + "withoutbalance <br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682528' target='_blank'>"
				+ "Click here to open TestRail: C682528</a><br>";
		test_catagory = "CPReservation_CheckOut";
		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");
		}
		else if(withBalance.equals("Yes"))
		{
			testName=testName + "withbalance";
			test_description = testName + "<br>"
					+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682527' target='_blank'>"
					+ "Click here to open TestRail: C682527</a><br>";
			test_catagory = "CPReservation_CheckOut";
			app_logs.info("##################################################################################");
			app_logs.info("EXECUTING: " + testName + " TEST.");
			app_logs.info("##################################################################################");
		}
		
		randomNum = Utility.GenerateRandomNumber();		
		roomClassNames = roomClassName+"_"+randomNum;
		roomClassAbbs = roomClassAbbrivation+"_"+randomNum;
		rateNameWithoutNum = ratesNames;
		rateName = rateNameWithoutNum+"_" + randomNum;
		policyNameWithoutNum = policyName;
		roomNumberAssigned.removeAll(roomNumberAssigned);
			
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
			checkInDate = checkInDates.get(0) + "|" + checkInDates.get(1);
			checkOutDate = checkOutDates.get(0) + "|" + checkOutDates.get(1);
			app_logs.info(checkInDate);
			app_logs.info(checkOutDate);
			
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
			
			} catch (Exception e) {
			handelingCatchException(e, "Failed to login", testName, "Login");
		} catch (Error e) {
			handelingCatchError(e, "Failed to login", testName, "Login");
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
			String roomNO=Utility.RoomNo;
			for (int i = 1; i < Integer.parseInt(roomQuantity); i++) {
				int rooms=Integer.parseInt(roomNO)+i;
				roomNumberAssigned.add(String.valueOf(rooms));
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
		try
		{
			navigation.reservation_Backward_1(driver);
			app_logs.info("Navigate to Reservation");
			test_steps.add("<b>==========Start Creating New Reservation==========</b>");
			reservationPage.click_NewReservation(driver, test_steps);
			reservationPage.select_Dates(driver, test_steps, checkInDate, checkOutDate, adults,
					children, ratePlan, "","");					
			reservationPage.clickOnFindRooms(driver, test_steps);
			reservationPage.selectRoom1(driver, test_steps, roomClassNames, roomNumberAssigned.get(0), "");
			reservationPage.selectRoom1(driver, test_steps, roomClassNames, roomNumberAssigned.get(2), "");
			
			reservationPage.clickNext(driver, test_steps);
			yearDate = Utility.getFutureMonthAndYearForMasterCard();
			guestFirstName = guestFirstName + randomNum;
			guestLastName = guestLastName + randomNum;
			reservationPage.enter_MRB_MailingAddressForMRB(driver, test_steps, salutation, guestFirstName, guestLastName, config.getProperty("flagOff"), roomNumber);
			reservationPage.enter_PaymentDetails(driver, test_steps, paymentType,
						cardNumber, nameOnCard, yearDate);
			reservationPage.enter_MarketSegmentDetails(driver, test_steps, "",
					marketSegment, referral);
			reservationPage.clickBookNow(driver, test_steps);
			reservation = reservationPage.get_ReservationConfirmationNumber(driver, test_steps);
			reservationPage.clickCloseReservationSavePopup(driver, test_steps);
			roomNos = reservationPage.getStayInfoRoomNo(driver, test_steps);
			
		}catch (Exception e) {
			handelingCatchException(e, "Failed to Create Reservation", testName, "Reservation");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Create Reservation", testName, "Reservation");
		}
	
		try
		{
			test_steps.add("<b>==========Start Check In Secondary Guest==========</b>");
			reservationPage.clickStayInfoCheckIn(driver, test_steps, roomNos.get(1));
			reservationPage.generatGuestReportToggle(driver, test_steps, config.getProperty("flagOff"));
			reservationPage.checkInProcess(driver, test_steps);
			Wait.waitTillElementDisplayed(driver, OR_Reservation.loading);	
			Wait.explicit_wait_absenceofelement(OR_Reservation.loading, driver);
			app_logs.info("Loading Done");			
			reservationPage.verifyStayInfoCheckOutButtonForMRB(driver, test_steps, roomNos.get(1));
			
			
		}catch (Exception e) {
			handelingCatchException(e, "Failed to Check In Ssecondary Guest", testName, "Reservation");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Check In Ssecondary Guest", testName, "Reservation");
		}
			
		try
		{
			
			if(withBalance.equals("No"))
			{
				test_steps.add("<b>==========Captured Balance at Folio Before Checkout Secondary Guest==========</b>");
				reservationPage.click_Folio(driver, test_steps);
				String parimaryGuestBalance=reservationPage.get_Balance(driver, test_steps);
				test_steps.add("Captured Primary Guest Balance at Folio Before Checkout: <b>" + parimaryGuestBalance+"</b>");
				app_logs.info("Captured Primary Guest Balance at Folio Before Checkout: " + parimaryGuestBalance);
				reservationPage.click_FolioDetail_DropDownBox(driver, test_steps);
				reservationPage.clickFolioDetailOptionValue(driver, test_steps, roomClassAbbs, roomNos.get(1));
				String secondaryGuestBalance=reservationPage.get_Balance(driver, test_steps);
				test_steps.add("Captured Secondary  Guest Balance at Folio Before Checkout: <b>" + secondaryGuestBalance+"</b>");
				app_logs.info("Captured Secondary  Guest Balance at Folio Before Checkout: " + secondaryGuestBalance);
				reservationPage.clickOnDetails(driver);
			}
			test_steps.add("<b>==========Start Check Out Secondary Guest And Settle All Charges==========</b>");
			reservationPage.clickStayInfoCheckOutRoom(driver, test_steps, roomNos.get(1));
			reservationPage.verifySettlementMsg(driver, test_steps, roomNos.get(1),
					roomClassNames);
			if(withBalance.equals("Yes"))
					{
				reservationPage.clickSettlementYesButton(driver, test_steps);
				reservationPage.generatGuestReportToggle(driver, test_steps, config.getProperty("flagOff"));
				reservationPage.checkOutPayment(driver);
				test_steps.add("Clicking on  Proceed to Check-Out Payment Button");
				app_logs.info("Clicking on Proceed to Check-Out Payment Button");
				Wait.waitTillElementDisplayed(driver, OR_Reservation.loading);	
				app_logs.info("Loading Done");	
				Wait.WaitForElement(driver, OR_Reservation.checkoutPayment);
				balanceAmount=reservationPage.getAmountFromPaymentVerificationPage(driver);
				app_logs.info(balanceAmount);
				reservationPage.clickLogORPayAuthorizedButton(driver, test_steps);
				Wait.waitTillElementDisplayed(driver, OR_Reservation.loading);			
				app_logs.info("Loading Done");
				reservationPage.clickCloseButtonOfSuccessModelPopup(driver, test_steps);
					}
			else if(withBalance.equals("No"))
			{
				reservationPage.clickSettlementNoButton(driver, test_steps);
				reservationPage.generatGuestReportToggle(driver, test_steps, config.getProperty("flagOff"));
				reservationPage.checkOutPayment(driver);
				test_steps.add("Clicking on  Confirm Check-Out Button");
				app_logs.info("Clicking on Confirm  Check-Out Button");
				Wait.waitTillElementDisplayed(driver, OR_Reservation.loading);	
				app_logs.info("Loading Done");	
			}
			
			reservationPage.verifyStayInforRollBackButton(driver, test_steps, roomNos.get(1));
			
				
			if(withBalance.equals("No"))
			{
				test_steps.add("<b>==========Captured Balance at Folio After Checkout Secondary Guest==========</b>");
				reservationPage.click_Folio(driver, test_steps);
				reservationPage.verifyBalanceAmountAtFolio(driver, test_steps, balanceAmt);
				reservationPage.click_FolioDetail_DropDownBox(driver, test_steps);
				reservationPage.clickFolioDetailOptionValue(driver, test_steps, roomClassAbbs, roomNos.get(1));
				reservationPage.verifyBalanceAmountAtFolio(driver, test_steps, balanceAmt);	
				reservationPage.clickOnDetails(driver);
			}
		}
		catch (Exception e) {
			handelingCatchException(e, "Failed to Check Out Secondary Guest And Settle All Charges", testName, "Reservation");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Check Out Secondary Guest And Settle All Charges", testName, "Reservation");
		}
		try
		{
			test_steps.add("<b>==========Start Check In Primary Guest==========</b>");
			reservationPage.clickStayInfoCheckIn(driver, test_steps, roomNos.get(0));
			reservationPage.generatGuestReportToggle(driver, test_steps, config.getProperty("flagOff"));
			reservationPage.checkInProcess(driver, test_steps);
			Wait.waitTillElementDisplayed(driver, OR_Reservation.loading);	
			Wait.explicit_wait_absenceofelement(OR_Reservation.loading, driver);
			app_logs.info("Loading Done");	
			reservationPage.verifyStayInfoCheckOutButtonForMRB(driver, test_steps, roomNos.get(0));
		}catch (Exception e) {
			handelingCatchException(e, "Failed to Check In Primary Guest", testName, "Reservation");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Check In Primary Guest", testName, "Reservation");
		}
		
		try
		{
			if(withBalance.equals("Yes"))
			{
			test_steps.add("<b>==========Start Check Out Primary Guest and Verify Take Me to Folio==========</b>");
			reservationPage.clickStayInfoCheckOutRoom(driver, test_steps, roomNos.get(0));
			reservationPage.generatGuestReportToggle(driver, test_steps, config.getProperty("flagOff"));			
			reservationPage.clickCheckoutProceedButton(driver, test_steps, config.getProperty("proceedButton"));
			reservationPage.verifyCheckoutProceedMessage(driver, test_steps, config.getProperty("settlementMsg"));
			reservationPage.verifytakeMeToFolioButton(driver, test_steps, config.getProperty("takemeFolio"));
			reservationPage.clicktakeMeToFolioButton(driver, test_steps);
			reservationPage.verifytakeMeToFolio(driver, test_steps);
			reservationPage.verifyFoiloRefund(driver, test_steps, balanceAmount);
			reservationPage.clickOnDetails(driver);
			reservationPage.verifyStayInfoCheckOutButtonForMRB(driver, test_steps, roomNos.get(0));
			}
		}
		catch (Exception e) {
			handelingCatchException(e, "Failed to Check Out Primary Guest and Verify Take Me to Folio", testName, "Reservation");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Check Out Primary Guest and Verify Take Me to Folio", testName, "Reservation");
		}
		try
		{
			if(withBalance.equals("Yes"))
			{
			test_steps.add("<b>==========Start Check Out Primary Guest and Confirm Checkout Without Refund==========</b>");		
			reservationPage.clickStayInfoCheckOutRoom(driver, test_steps, roomNos.get(0));
			reservationPage.generatGuestReportToggle(driver, test_steps, config.getProperty("flagOff"));
			reservationPage.clickCheckoutProceedButton(driver, test_steps, config.getProperty("proceedButton"));
			reservationPage.verifyCheckoutProceedMessage(driver, test_steps, config.getProperty("settlementMsg"));
			reservationPage.clickConfirmCheckoutWithoutRefundButton(driver, test_steps);
			reservationPage.verifytakemeToDetail(driver, test_steps);
			reservationPage.verifyRefundOnDetail(driver, test_steps, balanceAmount);
			reservationPage.verifyStayInforRollBackButton(driver, test_steps, roomNos.get(0));
			}
		}catch (Exception e) {
			handelingCatchException(e, "Failed to Check Out Primary Guest and  Confirm Checkout Without Refund", testName, "Reservation");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Check Out Primary Guest and  Confirm Checkout Without Refund", testName, "Reservation");
		}
		
		try
		{
			if(withBalance.equals("No"))
			{
				test_steps.add("<b>==========Start Check Out Primary Guest and Confirm Checkout==========</b>");		
				reservationPage.clickStayInfoCheckOutRoom(driver, test_steps, roomNos.get(0));
				reservationPage.generatGuestReportToggle(driver, test_steps, config.getProperty("flagOff"));
				reservationPage.checkOutPayment(driver);
				test_steps.add("Clicking on  Confirm Check-Out Button");
				app_logs.info("Clicking on Confirm  Check-Out Button");
				Wait.waitTillElementDisplayed(driver, OR_Reservation.loading);	
				app_logs.info("Loading Done");	
				reservationPage.verifyStayInforRollBackButton(driver, test_steps, roomNos.get(0));
				String getBalance=reservationPage.get_TripSummaryBalance_Amount(driver, test_steps);
				reservationPage.verifyTripSummaryBalanceAmount(driver, test_steps, getBalance, balanceAmt);

			}
		}catch (Exception e) {
			handelingCatchException(e, "Failed to Check Out Primary Guest and  Confirm Checkout Without Refund", testName, "Reservation");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Check Out Primary Guest and  Confirm Checkout Without Refund", testName, "Reservation");
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

		return Utility.getData("VerifySettlementPopup", excel);

	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}
}
