package com.innroad.inncenter.tests;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Properties;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.Reports;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class VerifyCheckOutWithNonZeroBalance extends TestCore {

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
	CPReservationPage reservationPage = new CPReservationPage();
	ArrayList<String> roomNumberAssigned = new ArrayList<>();
	String  propertyName = null, roomClassNames = null, roomClassAbbs = null,
			roomClassNameWithoutNum = null, roomClassAvvWithoutNum = null, rateNameWithoutNum = null, randomNum = null,
			rateName = null, yearDate = null, reservation = null, balance = null,windowName=null;
	int tableSize = 0;

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
	public void verifyCheckOutWithNonZeroBalance(String roomClassName, String roomClassAbbrivation, String maxAdults,
			String maxPersons, String roomQuantity, String ratesNames, String displayName, String baseAmount,
			String ratePolicy, String rateDescription, String checkInDate, String checkOutDate, String ratePlanName,String salutation,
			String guestFirstName, String guestLastName, String paymentType, String cardNumber, String nameOnCard,
			String marketSegment, String referral, String zeroAmount) {
			test_description = testName + "<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682467' target='_blank'>"
				+ "Click here to open TestRail: C682467</a><br>";
		test_catagory = "CPReservation_CheckOut";
		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		roomClassNameWithoutNum = roomClassName;
		roomClassAvvWithoutNum = roomClassAbbrivation;
		rateNameWithoutNum = ratesNames;
		randomNum = Utility.GenerateRandomNumber();
		roomClassNames = roomClassNameWithoutNum + randomNum;
		roomClassAbbs = roomClassAvvWithoutNum + randomNum;
		rateName = rateNameWithoutNum + randomNum;

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
			 navigation.setup(driver);
			navigation.properties(driver);
			navigation.open_Property(driver, test_steps, propertyName);
			navigation.click_PropertyOptions(driver, test_steps);
			properties.allowNonZeroBalanceDuringCheckout(driver, test_steps, "Yes");
			properties.PublishButton(driver);
			test_steps.add("Click Publish Button");
		} catch (Exception e) {
			handelingCatchException(e, "Failed to Select Option form Properties", testName, "Properties");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Select Option form Properties", testName, "Properties");
		}

		// Logged Out Login Again
		try {
			
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
			test_steps.add("========== Creating Room Class ==========");
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
			test_steps.add("========== Creating Rate Plan and Associating with Room Class ==========");
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
			reservationPage.clickNext(driver, test_steps);
			yearDate = Utility.getFutureMonthAndYearForMasterCard(driver, test_steps);
			guestFirstName = guestFirstName + randomNum;
			guestLastName = guestLastName + randomNum;
			reservationPage.enter_MailingAddress_OnUnchecked_CreateGuestProfile(driver, test_steps, salutation,
					guestFirstName, guestLastName, "No");
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
		// Check In
		try {
			test_steps.add("<b>==========Start Check In==========</b>");
			windowName=reservationPage.getMainWindow(driver, test_steps);
			reservationPage.clickCheckInButton(driver, test_steps);
			reservationPage.generatGuestReportToggle(driver, test_steps, "No");
			reservationPage.checkInProcess(driver, test_steps);
			String loading = "(//div[@class='ir-loader-in'])";
			Wait.explicit_wait_absenceofelement(loading, driver);
			test_steps.add("<b>==========Start Verifying In-House Status==========</b>");
			reservationPage.verifyReservationStatusStatus(driver, test_steps, "IN-HOUSE");
			test_steps.add("<b>==========Start Verifying Check-Out Button ==========</b>");
			reservationPage.verifyCheckOutButton(driver, test_steps);

		} catch (Exception e) {
			handelingCatchException(e, "Failed to Check In Reservation", testName, "Reservation");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Check In Reservation", testName, "Reservation");
		}
		// Check out with Zero
		try {
			test_steps.add("<b>==========Get Balance Before Checkout==========</b>");
			balance = reservationPage.get_TripSummaryBalance_Amount(driver, test_steps);
			test_steps.add("<b>==========Get Folio Line Item Table Size Before Check Out==========</b>");
			reservationPage.click_Folio(driver, test_steps);
			tableSize = reservationPage.getFolioLineItemTRSize(driver, test_steps);
			reservationPage.click_DeatilsTab(driver, test_steps);
			test_steps.add("<b>==========Start Check-Out With Zero Amount ==========</b>");
			reservationPage.clickCheckOutButton(driver, test_steps);
			reservationPage.generatGuestReportToggle(driver, test_steps, "Yes");
			reservationPage.proceedToCheckOutPayment(driver, test_steps);
			reservationPage.inputAmountWhileCheckINAndCheckOut(driver, test_steps, zeroAmount);
			reservationPage.clickCheckoutWithoutPaymentButton(driver, test_steps);
			test_steps.add("<b>==========Start Verifying Report Generated in New Tab ==========</b>");
			reservationPage.switchToChildWindow(driver, test_steps);
			report.verifyGuestStatementReportGenerated(driver, test_steps);
			reservationPage.switchToMainWindow(driver, test_steps, windowName);
		
		} catch (Exception e) {
			handelingCatchException(e, "Failed to Check In Reservation", testName, "Reservation");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Check In Reservation", testName, "Reservation");
		}

		try {
			test_steps.add("<b>==========Start Verifying Roll Back Button ==========</b>");
			reservationPage.verifyRollBackButton(driver, test_steps);
			test_steps.add("<b>==========Start Verifying DEPARTED Status==========</b>");
			reservationPage.verifyReservationStatusStatus(driver, test_steps, "DEPARTED");
			test_steps.add("<b>==========Start Verifying Balance==========</b>");
			String getBalance = reservationPage.get_TripSummaryBalance_Amount(driver, test_steps);
			reservationPage.verifyTripSummaryBalanceAmount(driver, test_steps, getBalance, balance);
			test_steps.add("<b>==========Start Verifying History==========</b>");
			reservationPage.click_History(driver, test_steps);
			reservationPage.verifyPaymentDescriptionAtGuestHistory(driver, test_steps, "Reservation",
					"Checked out this reservation");
			test_steps.add("<b>==========Start Verifying Folio==========</b>");
			reservationPage.click_Folio(driver, test_steps);
			reservationPage.checkedDisplayVoidItem(driver, test_steps);
			reservationPage.verifyBalanceAmountAtFolio(driver, test_steps, balance);
			reservationPage.verifyFolioLineItemTRSize(driver, test_steps, tableSize);

		} catch (Exception e) {
			handelingCatchException(e, "Failed to Check Out Reservation with Zero Balance ", testName, "Reservation");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Check Out Reservation with Zero Balance", testName, "Reservation");
		}

		try {
			test_steps.add("<b>****Start Deleting Rates****</b>");
			navigation.inventory(driver);
			test_steps.add("Navigate to Inventory");
			app_logs.info("Navigate to Inventory");
			navigation.rates1(driver);
			rate.deleteRates(driver, rateNameWithoutNum);
			test_steps.add("All Rate Deleted Successfully With Name: <b>" + rateNameWithoutNum + " </b>");
			app_logs.info("All Rate Deleted Successfully With Name: " + rateNameWithoutNum);

		} catch (Exception e) {
			handelingCatchException(e, "Failed to Delete Rate ", testName, "Rate");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Delete Rate", testName, "Rate");
		}

		try {

			test_steps.add("<b>****Delete Room Class****</b>");
			navigation.setup(driver);
			test_steps.add("Navigated to Setup");
			navigation.roomClass(driver, test_steps);
			roomClass.searchButtonClick(driver);
			test_steps.add("Click on Search Button");
			app_logs.info("Click on Search Button");
			roomClass.searchRoomClass(driver, roomClassNameWithoutNum, test_steps);
			roomClass.deleteRoomClass(driver, roomClassNameWithoutNum);
			test_steps.add("All Room Class Deleted Successfully With Name: <b>" + roomClassNameWithoutNum + " </b>");
			app_logs.info("All Room Class Deleted Successfully With Name: " + roomClassNameWithoutNum);

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
		} catch (Exception e) {
			handelingCatchException(e, "Failed to Delete Room Class ", testName, "Rooom Class");
		} catch (Error e) {
			handelingCatchError(e, "Failed to Delete Room Class", testName, "Rooom Class");
		}
	}

	@DataProvider
	public Object[][] getData() {

		return Utility.getData("VerifyCheckOutWithNonZeroBalanc", excel);

	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
