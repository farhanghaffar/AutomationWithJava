package com.innroad.inncenter.tests;

import java.util.ArrayList;
import java.util.Random;

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
import com.innroad.inncenter.pageobjects.Properties;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.Reports;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.properties.OR_Reservation;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class VerifyAuthorizationMRBReservationCheckoutAll extends TestCore{
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
	Folio folio = new Folio();
	CPReservationPage reservationPage = new CPReservationPage();
	ArrayList<String> roomNumberAssigned = new ArrayList<>();
	ArrayList<String> roomAbbri = new ArrayList<String>();
	ArrayList<String> roomNumber = new ArrayList<>();	
	String  propertyName = null, roomClassNames = null, roomClassAbbs = null,
			 rateNameWithoutNum = null, randomNum = null,
			rateName = null, yearDate = null, reservation = null,roomClassName1=null, roomClassName2=null, roomClassAbb1=null, roomClassAbb2=null,
			 date=null,last4Digit=null,folioBalance=null, dateHistory=null, authDate=null;
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
	public void verifyAuthorizationMRBReservationCheckoutAll(String roomClassName, String roomClassAbbrivation, String maxAdults,
			String maxPersons, String roomQuantity, String ratesNames, String displayName, String baseAmount,
			String ratePolicy, String rateDescription, String source, 
			String seasons, String checkInDate, String checkOutDate, String adults,String children,String ratePlan, String salutation,
			String guestFirstName, String guestLastName, String phoneNumber, String paymentType, String cardNumber, String nameOnCard,
			String marketSegment, String referral, String autorizedIcon, String notes) {
			test_description = testName + "<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682399' target='_blank'>"
				+ "Click here to open TestRail: C682399</a><br>";
		test_catagory = "CPReservation_CheckOut";
		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		
		
		randomNum = Utility.GenerateRandomNumber();
		roomClassName1 = roomClassName+"One_"+randomNum;
		roomClassAbb1 = roomClassAbbrivation+"One_"+randomNum;
		roomClassName2 = roomClassName+"Two_"+randomNum;
		roomClassAbb2 = roomClassAbbrivation+"Two_"+randomNum;
		rateNameWithoutNum = ratesNames;
		rateName = rateNameWithoutNum+"_" + randomNum;
	
		

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
			date=Utility.parseDate(checkInDates.get(0), "dd/MM/yyyy", "E MMM d, yyyy");
			app_logs.info(date);
			authDate=Utility.parseDate(checkInDate, "dd/MM/yyyy", "MM/dd/yyyy");
			app_logs.info(authDate);
			dateHistory=Utility.parseDate(checkInDate, "dd/MM/yyyy", "MM/dd/yy");
			app_logs.info(dateHistory);    
	
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

			test_steps.add("<b>========== Creating 1st room class ==========</b>");
				navigation.mainSetupManu(driver);
			navigation.roomClass(driver, test_steps);
			roomClass.deleteAllRoomClasses(driver, roomClassName, test_steps);
			navigation.clickOnNewRoomClassButton(driver, test_steps);
			roomClass.create_RoomClass(driver, roomClassName1, roomClassAbb1, null, maxAdults, maxPersons, roomQuantity,
					test, test_steps);
			roomNumberAssigned.add(Utility.RoomNo);
			roomClass.closeOpenedRoomClass(driver, roomClassName1, test_steps);
			navigation.newRoomClass(driver);
			test_steps.add("<b>========== Creating 2nd room class ==========</b>");
			roomClass.create_RoomClass(driver, roomClassName2, roomClassAbb2, null, maxAdults, maxPersons, roomQuantity,
					test, test_steps);
			roomNumberAssigned.add(Utility.RoomNo);
			for (int i = 0; i < roomNumberAssigned.size(); i++) {
				roomNumberAssigned.get(i);
			}

			app_logs.info(roomNumberAssigned);
			roomAbbri.add(roomClassName1 + ":" + roomClassAbb1);
			roomAbbri.add(roomClassName2 + ":" + roomClassAbb2);
			app_logs.info(roomAbbri);
			

		}catch (Exception e) {
			handelingCatchException(e, "Failed to Create Room Class", testName, "Setup");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Create Room Class", testName, "Setup");
		}
		
		try
		{
			test_steps.add("<b>======== Creating rate plan and Associating with room class ========</b>");
			navigation.Inventory(driver);
			navigation.Rates1(driver);
			rate.deleteAllRates(driver, test_steps, rateNameWithoutNum);
			rate.create_Rate(driver, rateName, maxAdults, maxPersons, baseAmount, maxAdults, maxPersons, displayName,
					ratePolicy, rateDescription, roomClassName1, roomClassName2, test_steps);
		}catch (Exception e) {
			handelingCatchException(e, "Failed to Create Rates", testName, "Rates");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Create Rates", testName, "Rates");
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
			String roomclass = roomClassName1 + "|" + roomClassName2;
			app_logs.info(roomclass);

			reservationPage.selectRoom(driver, test_steps, roomClassName1, roomNumberAssigned.get(0), "");
			reservationPage.selectRoom(driver, test_steps, roomClassName2, roomNumberAssigned.get(1), "");
			reservationPage.clickNext(driver, test_steps);
			yearDate = Utility.getFutureMonthAndYearForMasterCard();
			guestFirstName = guestFirstName + randomNum;
			guestLastName = guestLastName + randomNum;
			reservationPage.enter_MRB_MailingAddressForMRB(driver, test_steps, salutation, guestFirstName, guestLastName, phoneNumber,
					"", "", "", "", "", "", "", "", "", "", "", 
					config.getProperty("flagOff"), "", "",roomNumber);
				reservationPage.enter_PaymentDetails(driver, test_steps, paymentType,
						cardNumber, nameOnCard, yearDate);
			reservationPage.enter_MarketSegmentDetails(driver, test_steps, "",
					marketSegment, referral);
			reservationPage.clickBookNow(driver, test_steps);
			reservation = reservationPage.get_ReservationConfirmationNumber(driver, test_steps);
			reservationPage.clickCloseReservationSavePopup(driver, test_steps);
			
			
		}catch (Exception e) {
			handelingCatchException(e, "Failed to Create Reservation", testName, "Reservation");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Create Reservation", testName, "Reservation");
		}
		
		try
		{
			test_steps.add("<b>==========Start Check In==========</b>");
			reservationPage.clickCheckInAllButton(driver, test_steps);
			reservationPage.generatGuestReportToggle(driver, test_steps, config.getProperty("flagOff"));
			reservationPage.checkInProcess(driver, test_steps);
			test_steps.add("<b>==========Start Verifying Check Out All Button ==========</b>");
			reservationPage.verifyCheckOutAllButton(driver, test_steps);
			test_steps.add("<b>==========Start Verifying IN-HOUSE Status==========</b>");
			reservationPage.verifyReservationStatusStatus(driver, test_steps, config.getProperty("inHouse"));
		}catch (Exception e) {
			handelingCatchException(e, "Failed to Check In", testName, "Reservation");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Check In", testName, "Reservation");
		}
		
			
		try
		{
			test_steps.add("<b>==========Start Take Payment==========</b>");
			reservationPage.click_Folio(driver, test_steps);
			String folioname="Guest Folio For "+roomClassAbb1+": "+roomNumberAssigned.get(0)+"";
			app_logs.info("Folio Name is:"+folioname );
			folioBalance=reservationPage.get_Balance(driver, test_steps);
			reservationPage.click_Pay(driver, test_steps);
			last4Digit=cardNumber.substring(cardNumber.length() - 4);
			app_logs.info("Card NO is:"+last4Digit );
			String cardNo="XXXX "+last4Digit+"";
			app_logs.info("Card NO is:"+cardNo );
			reservationPage.paymentCheckInPopupVerify(driver, test_steps, config.getProperty("takePaymentWindowHeader"), yearDate, nameOnCard, cardNo, paymentType, "", "", ""
					, "", "", test_steps, test_steps, "", "", "");
			reservationPage.takePaymentAuthorization(driver, test_steps, config.getProperty("takePaymentWindowHeader"), config.getProperty("authorizedPolicy"), folioBalance,folioname);
		    reservationPage.addNotesAndClickLogORPayORAuthorizedButton(driver, test_steps, notes);		   
		    reservationPage.authorizedSuccessVerification(driver, test_steps, config.getProperty("authorizationSuccess"), authDate, folioname, folioBalance, folioBalance, config.getProperty("authorizedPolicy"), paymentType, last4Digit, nameOnCard, yearDate, config.getProperty("approvedStatus"), notes);
		    reservationPage.clickCloseButtonOfAuthorizedSuccess(driver, test_steps); 
		    reservationPage.saveReservation(driver,test_steps);
		    Wait.explicit_wait_absenceofelement(OR_Reservation.loading, driver);
		}catch (Exception e) {
			handelingCatchException(e, "Failed to take payment", testName, "Reservation");
		} catch (Error e) {
			handelingCatchError(e, "Failed to take payment", testName, "Reservation");
		}
		
		
		try
		{
			test_steps.add("<b>======= Verifying amount details and icon at folio tab ========</b>");			
			folio.verifyLineItemIcon(driver, test_steps, paymentType, autorizedIcon,
					" <b>Icon</b>  at folio line item");	
			folio.verifyFolioLineItem(driver, test_steps, date, paymentType, null, folioBalance, nameOnCard, last4Digit, yearDate);
		}catch (Exception e) {
			handelingCatchException(e, "Failed to Verify Folio line item", testName, "Reservation");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Verify Folio line item", testName, "Reservation");
		}
		try
		{
			test_steps.add("<b>========Start Verifying History========</b>");
			reservationPage.click_History(driver, test_steps);
			String roomNos=""+roomClassAbb1+": "+roomNumberAssigned.get(0)+"";
			app_logs.info("History Room Verification: "+roomNos);
			reservationPage.verifyHistoryTabForAuthorizedPayment(driver, test_steps, dateHistory, folioBalance,false, null, paymentType, last4Digit, yearDate,roomNos);
		}catch (Exception e) {
			handelingCatchException(e, "Failed to Verify History", testName, "Reservation");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Verify History", testName, "Reservation");
		}
		
		
		try
		{
			test_steps.add("<b>========Start CheckOut All Info========</b>");
			reservationPage.click_DeatilsTab(driver, test_steps);
			String balance=reservationPage.get_TripSummaryBalance_Amount(driver, test_steps);
			reservationPage.clickCheckOutAllButton(driver, test_steps);
			reservationPage.clickYesButtonOfCheckOutAllConfirmationMsg(driver, test_steps, config.getProperty("checkOutAllMsg"), "CheckOutAll", "", "");
			reservationPage.generatGuestReportToggle(driver, test_steps, config.getProperty("flagOff"));
			double balanceOne = Double.valueOf(balance);
			int balanceAmt=(int) balanceOne;
			reservationPage.clickCheckOutButton(driver, test_steps, balanceAmt);
			reservationPage.verifyCheckoutPaymentForAuthorization(driver, test_steps, config.getProperty("checkoutHeader"), config.getProperty("capturedType"), balance);
		
		}
		catch (Exception e) {
			handelingCatchException(e, "Failed to Verify Checkout All", testName, "Reservation");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Verify Checkout All", testName, "Reservation");
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

		return Utility.getData("VerifyAuthorizationMRBCheckoutA", excel);

	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
