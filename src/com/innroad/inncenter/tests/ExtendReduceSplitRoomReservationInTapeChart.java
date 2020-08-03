package com.innroad.inncenter.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
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
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.Reservation;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.Tapechart;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class ExtendReduceSplitRoomReservationInTapeChart extends TestCore {

	// Automation-1468
	private WebDriver driver = null;

	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();
	ArrayList<String> testName = new ArrayList<>();
	ArrayList<String> testCategory = new ArrayList<>();
	ArrayList<String> testDescription = new ArrayList<>();
	public static String testdescription = "";
	public static String testcatagory = "";

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {

		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "TapeChart")
	public void extendReduceSplitRoomReservationInTapeChart(String rateName, String baseAmount, String addtionalAdult,
			String additionalChild, String displayName, String associateSeason, String rateRPolicy,
			String rateDescription, String roomClassAbb, String roomClassName, String bedsCount, String maxAdults,
			String maxPersons, String roomQuantity, String startRoomNumber, String ratePlan, String rateType,
			String rateAttributes, String interval, String source, String adults, String child, String marketSegment,
			String referral, String firstName, String lastName, String salutation, String roomCharge)
			throws InterruptedException, IOException {

		String scriptName = "ExtendReduceSplitRoomReservationInTapeChart";
		testdescription = "Extend Or Reduce Split room Reservation in Tape Chart<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682408' target='_blank'>"
				+ "Click here to open TestRail: C682408</a>";

		testcatagory = "TapeChart";
		testName.add(scriptName);
		testDescription.add(testdescription);
		testCategory.add(testcatagory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + scriptName + " TEST.");
		app_logs.info("##################################################################################");

		Rate rate = new Rate();
		Navigation navigation = new Navigation();
		Tapechart tapeChart = new Tapechart();
		ReservationSearch reservationSearch = new ReservationSearch();
		CPReservationPage reservation = new CPReservationPage();
		RoomClass roomClass = new RoomClass();
		String randomNumber = Utility.GenerateRandomNumber();
		Folio folio = new Folio();
		String reservationNumber = null;
		lastName = lastName + randomNumber;
		String tripTotal = null;
		String balance = null;
		String taxes = null;
		String roomA = null;
		String roomB = null;
		String movedRoomA = null;
		String movedRoomB = null;
		String amountwithTax = null;
		String amount = null;
		String twoNightsAmountwithTax = null;
		String twoNightsAmount = null;
		String threeNightsAmountwithTax = null;
		String threeNightsAmount = null;
		String tempraryRoomClassname = roomClassName;

		try {
			if (!Utility.insertTestName.containsKey(scriptName)) {
				Utility.insertTestName.put(scriptName, scriptName);
				Utility.reTry.put(scriptName, 0);

			} else {
				Utility.reTry.replace(scriptName, 1);
			}
			driver = getDriver();
			login_CP(driver);
			testSteps.add("Entered appication URL : " + TestCore.envURL);
			testSteps.add("Logged into the application ");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testdescription, testcatagory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testdescription, testcatagory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Room Class
		try {

			app_logs.info("==========DELETE ROOM CLASS==========");
			testSteps.add("==========DELETE ROOM CLASS==========");

			navigation.navigateSetup(driver);
			app_logs.info("Navigate Setup");
			testSteps.add("Navigate Setup");

			navigation.navigateRoomClass(driver);
			app_logs.info("Navigate Room Class");
			testSteps.add("Navigate Room Class");

			// start room classes method here
			boolean isRoomClassShowing = roomClass.searchClass(driver, roomClassName);
			app_logs.info("Search");
			if (isRoomClassShowing) {

				roomClass.deleteRoomClass(driver, roomClassName);
				testSteps.add("Delete room class successfully");
				app_logs.info("Delete room class successfully");
			} else {
				testSteps.add("No room class exist");
			}

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete room class", scriptName, "RoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete room class", scriptName, "RoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		try {

			app_logs.info("==========CREATE ROOM CLASS==========");
			testSteps.add("==========CREATE ROOM CLASS==========");
			roomClassName = roomClassName + randomNumber;
			roomClassAbb = roomClassAbb + randomNumber;

			navigation.clickOnNewRoomClass(driver);
			testSteps.add("Click on new roo class button");
			
			getTestSteps = roomClass.createRoomClass(driver, roomClassName, roomClassAbb, bedsCount, maxAdults,
					maxPersons, roomQuantity, test, getTestSteps);
			testSteps.addAll(getTestSteps);

			testSteps.add("Successfully Created New RoomClass " + roomClassName);
			app_logs.info("Successfully Created New RoomClass" + roomClassName);
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create room class successfully", scriptName, "NewRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create room class successfully", scriptName, "NewRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Create New Rate and Attach RoomClass
		try {

			navigation.Inventory(driver);
			app_logs.info("Navigate Inventory");
			testSteps.add("Navigate Inventory");

			app_logs.info("==========DELETE RATE==========");
			testSteps.add("==========DELETE RATE==========");

			getTestSteps.clear();
			getTestSteps = navigation.secondaryRatesMenuItem(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.deleteRates(driver, rateName);
			testSteps.addAll(getTestSteps);
			testSteps.add("New Rate has been Deleted successfully");
			app_logs.info("New Rate has been Deleted successfully");

			rate.verifyDeleteRate(driver, rateName);
			testSteps.add("Verify the Deleted Rate : " + rateName);
			app_logs.info("Verify the Deleted Rate " + rateName);

			app_logs.info("==========CREATE RATE==========");
			testSteps.add("==========CREATE RATE==========");

			rateName = rateName + randomNumber;
			displayName = rateName;

			getTestSteps.clear();
			getTestSteps = rate.ClickNewRate(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.EnterRateName(driver, rateName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.SelectRatePlan(driver, ratePlan);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.EnterMaxAdults(driver, maxAdults);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.EnterMaxPersons(driver, maxPersons);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.EnterBaseAmount(driver, baseAmount);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.EnterAdditionalAdult(driver, addtionalAdult);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.EnterAdditionalChild(driver, additionalChild);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.EnterRateDisplayName(driver, rateName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.EnterRatePolicy(driver, rateName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.EnterRateDescription(driver, rateName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.AssociateSeason(driver, associateSeason);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.AssociateRoomClass(driver, roomClassName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.AssociateSource(driver, source);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.Save_DoneRate(driver);
			testSteps.addAll(getTestSteps);

			// rate.searchRate(driver, rateName, false);
			testSteps.add("New Rate '" + rateName + "' Created & Verified ");
			app_logs.info("New Rate '" + rateName + "' Created & Verified");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create new rate", scriptName, "CreateNewRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create new rate", scriptName, "CreateNewRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Create split reservation
		try {

			app_logs.info("==========CREATE SPLIT ROOM RESERVATION==========");
			testSteps.add("==========CREATE SPLIT ROOM RESERVATION==========");

			navigation.navigateToReservations(driver);
			testSteps.add("Navigate Reservations");
			app_logs.info("Navigate Reservations");

			getTestSteps.clear();
			getTestSteps = reservation.click_NewReservation(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.clickAddARoom(driver);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========ENTER STAY INFO AND SEARCH ROOMS==========");
			testSteps.add("==========ENTER STAY INFO AND SEARCH ROOMS==========");
			String checkInDate = Utility.getCurrentDate("MM/dd/yyyy");
			String checkoutDate = Utility.GetNextDate(1, "MM/dd/yyyy");
			reservation.enterDate(driver, "start", checkInDate, "1");
			testSteps.add("Select First Room CheckIn Date : " + checkInDate);
			app_logs.info("Select First Room CheckIn Date : " + checkInDate);
			reservation.enterDate(driver, "end", checkoutDate, "1");
			testSteps.add("Select First Room CheckOut Date : " + checkoutDate);
			app_logs.info("Select First Room CheckOut Date : " + checkoutDate);

			checkInDate = Utility.GetNextDate(1, "MM/dd/yyyy");
			checkoutDate = Utility.GetNextDate(2, "MM/dd/yyyy");
			reservation.enterDate(driver, "start", checkInDate, "2");
			testSteps.add("Select Second Room CheckIn Date : " + checkInDate);
			app_logs.info("Select Second Room CheckIn Date : " + checkInDate);
			reservation.enterDate(driver, "end", checkoutDate, "2");
			testSteps.add("Select Second Room CheckOut Date : " + checkoutDate);
			app_logs.info("Select Second Room CheckOut Date : " + checkoutDate);

			getTestSteps.clear();
			getTestSteps = reservation.splitRoomCheckbox(driver, true);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.enter_Adults(driver, getTestSteps, adults);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.enter_Children(driver, getTestSteps, child);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.select_Rateplan(driver, getTestSteps, ratePlan, "");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.clickOnFindRooms(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			app_logs.info("room class: " + roomClassName);
			app_logs.info("==========SELECT SPLIT ROOM : A==========");
			testSteps.add("==========SELECT SPLIT ROOM : A==========");

			getTestSteps.clear();
			getTestSteps = reservation.selectSplitRoom(driver, roomClassName, 2, 1);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========SELECT SPLIT ROOM : B==========");
			testSteps.add("==========SELECT SPLIT ROOM : B==========");
			getTestSteps.clear();
			getTestSteps = reservation.selectSplitRoom(driver, roomClassName, 3, 2);
			testSteps.addAll(getTestSteps);

			reservation.moveToAddARoom(driver);
			getTestSteps = reservation.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);
			Wait.wait5Second();

			app_logs.info("==========ENTER GUEST INFO==========");
			testSteps.add("==========ENTER GUEST INFO==========");
			reservation.enterMailingAddressOnUncheckedCreateGuestProfile(driver, getTestSteps, salutation, firstName,
					lastName, "no");

			app_logs.info("==========ENTER MARKETING INFO==========");
			testSteps.add("==========ENTER MARKETING INFO==========");
			getTestSteps.clear();
			getTestSteps = reservation.selectReferral(driver, referral);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========SAVE RESERVATION==========");
			testSteps.add("==========SAVE RESERVATION==========");
			reservation.clickBookNow(driver, testSteps);

			String confirmationNumber = reservation.getReservationConfirmationNumber(driver, testSteps);
			testSteps.add("Confirmation Number : <b>" + confirmationNumber + "</b>");
			reservationNumber = confirmationNumber.trim();
			String ReservationStatus = reservation.getReservationStatus(driver);
			Assert.assertEquals(ReservationStatus, "Reserved", "Failed: Reservation Status missmatched");
			reservation.clickCloseReservationSavePopup(driver, testSteps);

			roomA = reservation.getSplitRoomNumber(driver, "yes", 1);
			testSteps.add("Room Number A : " + roomA);
			app_logs.info("Room Number A : " + roomA);
			roomB = reservation.getSplitRoomNumber(driver, "yes", 2);
			testSteps.add("Room Number B : " + roomB);
			app_logs.info("Room Number B : " + roomB);

		} catch (Exception e) {

			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Create split reservation", scriptName, "TapeChart", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Create split reservation", scriptName, "TapeChart", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verification
		try {
			app_logs.info("==========VERIFY RESERVATION DETAIL==========");
			testSteps.add("==========VERIFY RESERVATION DETAIL==========");

			getTestSteps.clear();
			getTestSteps = folio.folioTab(driver);
			testSteps.addAll(getTestSteps);

			amountwithTax = folio.getLineItemAmount(driver, roomCharge, true);
			testSteps.add("One Night Room Charges Amount with Tax : " + amountwithTax);
			app_logs.info("One Night Room Charges Amount with Tax : " + amountwithTax);

			amount = folio.getLineItemAmount(driver, roomCharge, false);
			testSteps.add("One Night Room Charges Amount without Tax : " + amount);
			app_logs.info("One Night Room Charges Amount without Tax : " + amount);

			twoNightsAmountwithTax = String.format("%.2f", (Float.parseFloat(amountwithTax) * 2));
			testSteps.add("Two Nights Room Charges Amount with Tax : " + twoNightsAmountwithTax);
			app_logs.info("Two Nights Room Charges Amount with Tax : " + twoNightsAmountwithTax);

			twoNightsAmount = String.format("%.2f", (Float.parseFloat(amount) * 2));
			testSteps.add("Two Nights Room Charges Amount without Tax : " + twoNightsAmount);
			app_logs.info("Two Nights Room Charges Amount without Tax : " + twoNightsAmount);

			balance = reservation.getBalanceHeader(driver);

			assertEquals(Float.parseFloat(balance), Float.parseFloat(twoNightsAmountwithTax),
					"Failed to verify Balance");
			testSteps.add("Successfully Verified Balance : " + balance);
			app_logs.info("Successfully Verified Balance : " + balance);
			tripTotal = reservation.getTripTotalHeader(driver);
			assertEquals(Float.parseFloat(tripTotal), Float.parseFloat(twoNightsAmountwithTax),
					"Failed to verify Trip Total");
			testSteps.add("Successfully Verified Trip Total : " + tripTotal);
			app_logs.info("Successfully Verified Trip Total : " + tripTotal);
			String date = Utility.getCurrentDate("E MMM dd, yyyy");

			app_logs.info("==========VERIFY LINE ITEM FOR ROOM A==========");
			testSteps.add("==========VERIFY LINE ITEM FOR ROOM A==========");

			folio.includeTaxinLIneItemCheckbox(driver, true);

			getTestSteps.clear();
			getTestSteps = folio.LineItemDate(driver, roomCharge, 0, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.getDescroption(driver, roomCharge, rateName, false, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemCategory(driver, roomCharge, 1);
			testSteps.addAll(getTestSteps);

			String getAmount = folio.getAmount(driver, roomCharge, 1);
			testSteps.add("Expected amount for room A: $ " + amountwithTax);
			testSteps.add("Found: " + getAmount);
			assertEquals(getAmount, "$ " + amountwithTax, "Failed: amount is mismatching in room A");

			app_logs.info("==========VERIFY LINE ITEM FOR ROOM B==========");
			testSteps.add("==========VERIFY LINE ITEM FOR ROOM B==========");

			getTestSteps.clear();
			getTestSteps = folio.LineItemDate(driver, roomCharge, +1, 2);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.getDescroption(driver, roomCharge, rateName, false, 2);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemCategory(driver, roomCharge, 2);
			testSteps.addAll(getTestSteps);

			getAmount = folio.getAmount(driver, roomCharge, 2);
			testSteps.add("Expected amount for room B: $ " + amountwithTax);
			testSteps.add("Found: " + getAmount);
			assertEquals(getAmount, "$ " + amountwithTax, "Failed: amount is mismatching in room B");

			String getRoomCharge = reservation.getRoomCharge(driver, getTestSteps);
			assertEquals(Float.parseFloat(getRoomCharge), (Float.parseFloat(twoNightsAmount)),
					"Failed to verify Room Charges");
			testSteps.add("Successfully Verified Room Charge : " + getRoomCharge);
			app_logs.info("Successfully Verified Room Charge : " + getRoomCharge);
			taxes = reservation.getTax(driver, getTestSteps);
			testSteps.add("Taxes : " + taxes);
			app_logs.info("Taxes : " + taxes);
			String getTotalCharges = folio.getTotalCharges(driver);
			assertEquals(Float.parseFloat(getTotalCharges), (Float.parseFloat(twoNightsAmountwithTax)),
					"Failed to verify Total Charges");
			testSteps.add("Successfully Verified Total Charge : " + getTotalCharges);
			app_logs.info("Successfully Verified Total Charge : " + getTotalCharges);
			String totalBalance = folio.getToatalBalance(driver);
			totalBalance = folio.splitString(totalBalance);

			assertEquals(Float.parseFloat(totalBalance), Float.parseFloat(twoNightsAmountwithTax),
					"Failed to verify folio Balance");
			testSteps.add("Successfully Verified Folio Balance : " + totalBalance);
			app_logs.info("Successfully Verified Folio Balance : " + totalBalance);
			reservation.closeReservationTab(driver);
			testSteps.add("Close Reservation");
			app_logs.info("Close Reservation");
		} catch (Exception e) {

			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify reservation values", scriptName, "Verification", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify reservation values", scriptName, "Verification", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verify Reservation View Limit
		try {

			driver.navigate().refresh();
			/*
			 * navigation.reservation(driver);
			 * testSteps.add("Navigate Reservations");
			 */

			app_logs.info("Navigate Reservations");
			app_logs.info("==========VERIFY RESERVATION HANDLES==========");
			testSteps.add("==========VERIFY RESERVATION HANDLES==========");

			navigation.navigateToTapeChartFromReservations(driver);
			testSteps.add("Navigate TapeChart");
			app_logs.info("Navigate TapeChart");

			getTestSteps.clear();
			getTestSteps = tapeChart.verifyResevationhandles(driver, roomA, roomClassAbb, false, true, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = tapeChart.verifyResevationhandles(driver, roomB, roomClassAbb, true, false, getTestSteps);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Verify Reservation View Limit", scriptName, "Verification", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Verify Reservation View Limit", scriptName, "Verification", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// Extend Reservation
		try {

			threeNightsAmountwithTax = String.format("%.2f", (Float.parseFloat(amountwithTax) * 3));
			threeNightsAmount = String.format("%.2f", (Float.parseFloat(amount) * 3));
			driver.navigate().refresh();

			// navigation.reservation(driver);
			// testSteps.add("Navigate Reservations");
			// app_logs.info("Navigate Reservations");

			app_logs.info("==========EXTEND RESERVATION==========");

			testSteps.add("==========EXTEND RESERVATION==========");
			navigation.navTapeChart(driver, test);
			testSteps.add("Navigate TapeChart");
			app_logs.info("Navigate TapeChart");

			int preWidth = tapeChart.extendReservation(driver, roomB, roomClassAbb, firstName + " " + lastName);
			testSteps.add("Extend reservation in tapechart successfully");
			app_logs.info("Extend reservation in tapechart successfully");

			app_logs.info("==========VERIFY RESERVATION UPDATE POPUP DETAIL==========");
			testSteps.add("==========VERIFY RESERVATION UPDATE POPUP DETAIL==========");
			getTestSteps.clear();
			getTestSteps = tapeChart.verifyReservationNameReservationUpdate(driver, firstName + " " + lastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = tapeChart.verifyCheckInTimeReservationUpdate(driver, Utility.getCurrentDate("MMM dd, yyyy"));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = tapeChart.verifyCheckOutTimeReservationUpdate(driver, Utility.GetNextDate(3, "MMM dd, yyyy"),
					Utility.GetNextDate(2, "MMM dd, yyyy"));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = tapeChart.verifyRoomClassReservationUpdate(driver, roomClassName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = tapeChart.verifyTripTotalReservationUpdate(driver, threeNightsAmountwithTax,
					twoNightsAmountwithTax);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = tapeChart.verifyBalanceDueReservationUpdate(driver, threeNightsAmountwithTax,
					twoNightsAmountwithTax);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = tapeChart.clickConfirmReservationUpdate(driver);
			testSteps.addAll(getTestSteps);
			app_logs.info("==========VERIFY EXTENDED RESERVATION==========");
			testSteps.add("==========VERIFY EXTENDED RESERVATION==========");
			tapeChart.verifyExtendedReservation(driver, roomB, roomClassAbb, firstName + " " + lastName, preWidth);
			testSteps.add("Extend corporate reservation in tapechart successfully");
			app_logs.info("Extend corporate reservation in tapechart successfully");
			app_logs.info("==========VERIFY EXTENDED RESERVATION TOOL TIP==========");
			testSteps.add("==========VERIFY EXTENDED RESERVATION TOOL TIP==========");

			getTestSteps.clear();
			getTestSteps = tapeChart.toolTipHeaderVerification(driver, roomA, roomClassName, firstName + " " + lastName,
					threeNightsAmountwithTax, threeNightsAmountwithTax, reservationNumber, roomClassAbb);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========VERIFY TOOL TIP LINE 1(ROOM A)==========");
			testSteps.add("==========VERIFY TOOL TIP LINE 1(ROOM A)==========");
			getTestSteps.clear();
			getTestSteps = tapeChart.toolTipVerificationLine(driver, roomA, roomClassName, firstName + " " + lastName,
					Utility.getCurrentDate("MMM dd, yyyy"), Utility.GetNextDate(1, "MMM dd, yyyy"), "1N", 1);
			testSteps.addAll(getTestSteps);
			app_logs.info("==========VERIFY TOOL TIP LINE 2(ROOM B)==========");
			testSteps.add("==========VERIFY TOOL TIP LINE 2(ROOM B)==========");
			getTestSteps.clear();
			getTestSteps = tapeChart.toolTipVerificationLine(driver, roomB, roomClassName, firstName + " " + lastName,
					Utility.GetNextDate(1, "MMM dd, yyyy"), Utility.GetNextDate(3, "MMM dd, yyyy"), "2N", 2);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Extend Reservation", scriptName, "ExtendReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Extend Reservation", scriptName, "ExtendReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========VERIFY RESERVATION DETAIL AFTER EXTEND==========");
			testSteps.add("==========VERIFY RESERVATION DETAIL AFTER EXTEND==========");

			navigation.navigateToReservations(driver);
			reservationSearch.multipleSearchReservationNumber(driver, reservationNumber);
			testSteps.add("Search reservation using reservation number");

			getTestSteps.clear();
			getTestSteps = reservation.clickOnGuestNameInSearchReaservation(driver, reservationNumber);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.folioTab(driver);
			testSteps.addAll(getTestSteps);

			reservation.verifyStayDateAfterReservation(driver, Utility.getCurrentDate("MMM d"),
					Utility.GetNextDate(3, "MMM d"));

			balance = reservation.getBalanceHeader(driver);

			assertEquals(Float.parseFloat(balance), Float.parseFloat(threeNightsAmountwithTax),
					"Failed to verify Balance");
			testSteps.add("Successfully Verified Balance : " + balance);
			app_logs.info("Successfully Verified Balance : " + balance);
			tripTotal = reservation.getTripTotalHeader(driver);
			assertEquals(Float.parseFloat(tripTotal), Float.parseFloat(threeNightsAmountwithTax),
					"Failed to verify Trip Total");
			testSteps.add("Successfully Verified Trip Total : " + tripTotal);
			app_logs.info("Successfully Verified Trip Total : " + tripTotal);
			String date = Utility.GetNextDate(2, "E MMM dd, yyyy");

			app_logs.info("==========VERIFY LINE ITEM==========");
			testSteps.add("==========VERIFY LINE ITEM==========");

			folio.verifyLineItem(driver, date, roomCharge, amountwithTax, rateName, roomB, true);
			testSteps.add("Successfully Verified Line item is  added");
			app_logs.info("Successfully Verified Line item is added");
			testSteps.add("Line Item 'Date : " + date + "', 'Room : " + roomB + "', 'Category : " + roomCharge
					+ "', Description : " + rateDescription + "' and Amount : " + amountwithTax + "'");
			app_logs.info("Line Item 'Date : " + date + "', 'Room : " + roomB + "', 'Category : " + roomCharge
					+ "', Description : " + rateDescription + "' and Amount : " + amountwithTax + "'");
			String getRoomCharge = reservation.getRoomCharge(driver, getTestSteps);
			assertEquals(Float.parseFloat(getRoomCharge), (Float.parseFloat(threeNightsAmount)),
					"Failed to verify Room Charges");
			testSteps.add("Successfully Verified Room Charge : " + getRoomCharge);
			app_logs.info("Successfully Verified Room Charge : " + getRoomCharge);
			taxes = reservation.getTax(driver, getTestSteps);
			app_logs.info("Taxes : " + taxes);
			String getTotalCharges = folio.getTotalCharges(driver);
			assertEquals(Float.parseFloat(getTotalCharges), (Float.parseFloat(threeNightsAmountwithTax)),
					"Failed to verify Total Charges");
			testSteps.add("Successfully Verified Total Charge : " + getTotalCharges);
			app_logs.info("Successfully Verified Total Charge : " + getTotalCharges);
			// String totalBalance = res1.getBalanceFolio(driver);
			String totalBalance = folio.getToatalBalance(driver);
			totalBalance = folio.splitString(totalBalance);
			assertEquals(Float.parseFloat(totalBalance), Float.parseFloat(threeNightsAmountwithTax),
					"Failed to verify folio Balance");
			testSteps.add("Successfully Verified Folio Balance : " + totalBalance);
			app_logs.info("Successfully Verified Folio Balance : " + totalBalance);

			reservation.closeReservationTab(driver);
			testSteps.add("Close Reservation");
			app_logs.info("Close Reservation");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify details after extend reservation", scriptName, "TapeChart",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify details after extend reservation", scriptName, "TapeChart",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Reduce Reservation
		try {

			app_logs.info("==========REDUCE RESERVATION==========");
			testSteps.add("==========REDUCE RESERVATION==========");
			navigation.navTapeChart(driver, test);
			testSteps.add("Navigate TapeChart");
			app_logs.info("Navigate TapeChart");
			int prewidth = tapeChart.reduceReservation(driver, roomB, roomClassAbb, firstName + " " + lastName);
			testSteps.add("Reduced corporate reservation in tapechart successfully");
			app_logs.info("Reduced corporate reservation in tapechart successfully");
			app_logs.info("==========VERIFY RESERVATION UPDATE POPUP DETAIL==========");
			testSteps.add("==========VERIFY RESERVATION UPDATE POPUP DETAIL==========");
			getTestSteps.clear();
			getTestSteps = tapeChart.verifyReservationNameReservationUpdate(driver, firstName + " " + lastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = tapeChart.verifyCheckInTimeReservationUpdate(driver, Utility.getCurrentDate("MMM dd, yyyy"));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = tapeChart.verifyCheckOutTimeReservationUpdate(driver, Utility.GetNextDate(2, "MMM dd, yyyy"),
					Utility.GetNextDate(3, "MMM dd, yyyy"));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = tapeChart.verifyRoomClassReservationUpdate(driver, roomClassName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = tapeChart.verifyTripTotalReservationUpdate(driver, twoNightsAmountwithTax,
					threeNightsAmountwithTax);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = tapeChart.verifyBalanceDueReservationUpdate(driver, twoNightsAmountwithTax,
					threeNightsAmountwithTax);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = tapeChart.clickConfirmReservationUpdate(driver);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========VERIFY REDUCED RESERVATION==========");
			testSteps.add("==========VERIFY REDUCED RESERVATION==========");
			tapeChart.verifyReducedReservation(driver, roomB, roomClassAbb, firstName + " " + lastName, prewidth);
			testSteps.add("Reduced corporate reservation in tapechart successfully");
			app_logs.info("Reduced corporate reservation in tapechart successfully");
			app_logs.info("==========VERIFY REDUCED RESERVATION TOOL TIP==========");
			testSteps.add("==========VERIFY REDUCED RESERVATION TOOL TIP==========");
			getTestSteps.clear();
			getTestSteps = tapeChart.toolTipHeaderVerification(driver, roomA, roomClassName, firstName + " " + lastName,
					twoNightsAmountwithTax, twoNightsAmountwithTax, reservationNumber, roomClassAbb);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========VERIFY TOOL TIP LINE 1(ROOM A)==========");
			testSteps.add("==========VERIFY TOOL TIP LINE 1(ROOM A)==========");
			getTestSteps.clear();
			getTestSteps = tapeChart.toolTipVerificationLine(driver, roomA, roomClassName, firstName + " " + lastName,
					Utility.getCurrentDate("MMM dd, yyyy"), Utility.GetNextDate(1, "MMM dd, yyyy"), "1N", 1);
			testSteps.addAll(getTestSteps);
			app_logs.info("==========VERIFY TOOL TIP LINE 2(ROOM B)==========");
			testSteps.add("==========VERIFY TOOL TIP LINE 2(ROOM B)==========");
			getTestSteps.clear();
			getTestSteps = tapeChart.toolTipVerificationLine(driver, roomB, roomClassName, firstName + " " + lastName,
					Utility.GetNextDate(1, "MMM dd, yyyy"), Utility.GetNextDate(2, "MMM dd, yyyy"), "1N", 2);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Reduce Reservation", scriptName, "ReduceReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Reduce Reservation", scriptName, "ReduceReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========VERIFY RESERVATION DETAIL AFTER REDUCED==========");
			testSteps.add("==========VERIFY RESERVATION DETAIL AFTER REDUCED==========");
			navigation.navigateToReservations(driver);

			reservationSearch.multipleSearchReservationNumber(driver, reservationNumber);
			testSteps.add("Search reservation using reservation number");

			getTestSteps.clear();
			getTestSteps = reservation.clickOnGuestNameInSearchReaservation(driver, reservationNumber);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.folioTab(driver);
			testSteps.addAll(getTestSteps);

			reservation.verifyStayDateAfterReservation(driver, Utility.getCurrentDate("MMM d"),
					Utility.GetNextDate(2, "MMM d"));

			balance = reservation.getBalanceHeader(driver);

			assertEquals(Float.parseFloat(balance), Float.parseFloat(twoNightsAmountwithTax),
					"Failed to verify Balance");
			testSteps.add("Successfully Verified Balance : " + balance);
			app_logs.info("Successfully Verified Balance : " + balance);
			tripTotal = reservation.getTripTotalHeader(driver);
			assertEquals(Float.parseFloat(tripTotal), Float.parseFloat(twoNightsAmountwithTax),
					"Failed to verify Trip Total");
			testSteps.add("Successfully Verified Trip Total : " + tripTotal);
			app_logs.info("Successfully Verified Trip Total : " + tripTotal);
			String Date = Utility.GetNextDate(2, "E MMM dd, yyyy");
			app_logs.info("==========VERIFY LINE ITEM==========");
			testSteps.add("==========VERIFY LINE ITEM==========");
			folio.verifyLineItem(driver, Date, roomCharge, amountwithTax, rateName, roomB, false);
			testSteps.add("Successfully Verified Line item not Exist having following details");
			app_logs.info("Successfully Verified Line item is not Exist Exist having following details");
			testSteps.add("Line Item 'Date : " + Date + "', 'Room : " + roomB + "', 'Category : " + roomCharge
					+ "', Description : " + rateDescription + "' and Amount : " + amountwithTax + "'");
			app_logs.info("Line Item 'Date : " + Date + "', 'Room : " + roomB + "', 'Category : " + roomCharge
					+ "', Description : " + rateDescription + "' and Amount : " + amountwithTax + "'");
			String getRoomCharge = reservation.getRoomCharge(driver, getTestSteps);
			assertEquals(Float.parseFloat(getRoomCharge), (Float.parseFloat(twoNightsAmount)),
					"Failed to verify Room Charges");
			testSteps.add("Successfully Verified Room Charge : " + getRoomCharge);
			app_logs.info("Successfully Verified Room Charge : " + getRoomCharge);
			taxes = reservation.getTax(driver, getTestSteps);
			app_logs.info("Taxes : " + taxes);
			String getTotalCharges = folio.getTotalCharges(driver);
			assertEquals(Float.parseFloat(getTotalCharges), (Float.parseFloat(twoNightsAmountwithTax)),
					"Failed to verify Total Charges");
			testSteps.add("Successfully Verified Total Charge : " + getTotalCharges);
			app_logs.info("Successfully Verified Total Charge : " + getTotalCharges);
			// String totalBalance = res1.getBalanceFolio(driver);
			String totalBalance = folio.getToatalBalance(driver);
			totalBalance = folio.splitString(totalBalance);
			assertEquals(Float.parseFloat(totalBalance), Float.parseFloat(twoNightsAmountwithTax),
					"Failed to verify folio Balance");
			testSteps.add("Successfully Verified Folio Balance : " + totalBalance);
			app_logs.info("Successfully Verified Folio Balance : " + totalBalance);

			reservation.closeReservationTab(driver);
			testSteps.add("Close Reservation");
			app_logs.info("Close Reservation");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify details after reduce reservation", scriptName, "TapeChart",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify details after reduce reservation", scriptName, "TapeChart",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// MOVE Reservation
		try {
			driver.navigate().refresh();
			app_logs.info("Navigate Reservations");
			app_logs.info("==========VERIFY SPLIT RESERVATION(ROOM A) MOVE HORIZONTAL OR NOT==========");
			testSteps.add("==========VERIFY SPLIT RESERVATION(ROOM A) MOVE HORIZONTAL OR NOT==========");
			navigation.navTapeChart(driver, test);
			app_logs.info("Navigate TapeChart");
			int index = 3;
			String cellDate = tapeChart.getTapeChartFirstCellDate(driver);
			app_logs.info(cellDate);
			if (cellDate.equals(Utility.getCurrentDate("d"))) {
				index = 2;
			}
			movedRoomA = tapeChart.moveReservations(driver, roomA, roomClassAbb, firstName + " " + lastName, 2, index);
			testSteps.add("Try to Move reservation in tapechart");
			app_logs.info("Try to Move reservation in tapechart");
			driver.navigate().refresh();
			app_logs.info("==========VERIFY RESERVATION TOOL TIP==========");
			testSteps.add("==========VERIFY RESERVATION TOOL TIP==========");
			getTestSteps.clear();
			getTestSteps = tapeChart.toolTipHeaderVerification(driver, roomA, roomClassName, firstName + " " + lastName,
					twoNightsAmountwithTax, twoNightsAmountwithTax, reservationNumber, roomClassAbb);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========VERIFY TOOL TIP LINE 1(ROOM A)==========");
			testSteps.add("==========VERIFY TOOL TIP LINE 1(ROOM A)==========");

			getTestSteps.clear();
			getTestSteps = tapeChart.toolTipVerificationLine(driver, roomA, roomClassName, firstName + " " + lastName,
					Utility.getCurrentDate("MMM dd, yyyy"), Utility.GetNextDate(1, "MMM dd, yyyy"), "1N", 1);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========VERIFY TOOL TIP LINE 2(ROOM B)==========");
			testSteps.add("==========VERIFY TOOL TIP LINE 2(ROOM B)==========");

			getTestSteps.clear();
			getTestSteps = tapeChart.toolTipVerificationLine(driver, roomB, roomClassName, firstName + " " + lastName,
					Utility.GetNextDate(1, "MMM dd, yyyy"), Utility.GetNextDate(2, "MMM dd, yyyy"), "1N", 2);
			testSteps.addAll(getTestSteps);
			testSteps.add("Verified Reservation did not moved");
			app_logs.info("Verified Reservation did not moved");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to MOVE Reservation", scriptName, "MOVEReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to MOVE Reservation", scriptName, "MOVEReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// MOVE Reservation
		try {
			driver.navigate().refresh();
			app_logs.info("==========VERIFY SPLIT RESERVATION(ROOM B) MOVE HORIZONTAL OR NOT==========");
			testSteps.add("==========VERIFY SPLIT RESERVATION(ROOM B) MOVE HORIZONTAL OR NOT==========");
			navigation.navTapeChart(driver, test);
			app_logs.info("Navigate TapeChart");
			int index = 4;
			String cellDate = tapeChart.getTapeChartFirstCellDate(driver);
			app_logs.info(cellDate);
			if (cellDate.equals(Utility.getCurrentDate("d"))) {
				index = 3;
			}
			movedRoomB = tapeChart.moveReservations(driver, roomB, roomClassAbb, firstName + " " + lastName, 1, index);
			testSteps.add("Try to Move reservation in tapechart");
			app_logs.info("Try to Move reservation in tapechart");
			driver.navigate().refresh();
			app_logs.info("==========VERIFY RESERVATION TOOL TIP==========");
			testSteps.add("==========VERIFY RESERVATION TOOL TIP==========");
			getTestSteps.clear();
			getTestSteps = tapeChart.toolTipHeaderVerification(driver, roomA, roomClassName, firstName + " " + lastName,
					twoNightsAmountwithTax, twoNightsAmountwithTax, reservationNumber, roomClassAbb);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========VERIFY TOOL TIP LINE 1(ROOM A)==========");
			testSteps.add("==========VERIFY TOOL TIP LINE 1(ROOM A)==========");
			getTestSteps.clear();
			getTestSteps = tapeChart.toolTipVerificationLine(driver, roomA, roomClassName, firstName + " " + lastName,
					Utility.getCurrentDate("MMM dd, yyyy"), Utility.GetNextDate(1, "MMM dd, yyyy"), "1N", 1);
			testSteps.addAll(getTestSteps);
			app_logs.info("==========VERIFY TOOL TIP LINE 2(ROOM B)==========");
			testSteps.add("==========VERIFY TOOL TIP LINE 2(ROOM B)==========");
			getTestSteps.clear();
			getTestSteps = tapeChart.toolTipVerificationLine(driver, roomB, roomClassName, firstName + " " + lastName,
					Utility.GetNextDate(1, "MMM dd, yyyy"), Utility.GetNextDate(2, "MMM dd, yyyy"), "1N", 2);
			testSteps.addAll(getTestSteps);

			testSteps.add("Verified Reservation did not moved");
			app_logs.info("Verified Reservation did not moved");
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to MOVE Reservation", scriptName, "MOVEReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to MOVE Reservation", scriptName, "MOVEReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// MOVE Reservation
		try {
			driver.navigate().refresh();
			app_logs.info("Navigate Reservations");
			app_logs.info("==========MOVE ROOM A (VERTICALLY) FROM TAPE CHART==========");
			testSteps.add("==========MOVE ROOM A (VERTICALLY) FROM TAPE CHART==========");
			navigation.navTapeChart(driver, test);
			app_logs.info("Navigate TapeChart");
			int index = 2;
			String cellDate = tapeChart.getTapeChartFirstCellDate(driver);
			app_logs.info(cellDate);
			if (cellDate.contains(Utility.getCurrentDate("d"))) {
				index = 1;
			}
			app_logs.info("index: " + index);
			movedRoomA = tapeChart.moveReservations(driver, roomA, roomClassAbb, firstName + " " + lastName, 2, index);
			testSteps.add("Moved Split Reservation from 'Room : " + roomA + "' to 'Room : " + movedRoomA + "'");
			app_logs.info("Moved Split Reservation from 'Room : " + roomA + "' to 'Room : " + movedRoomA + "'");
			driver.navigate().refresh();
			app_logs.info("==========VERIFY RESERVATION TOOL TIP==========");
			testSteps.add("==========VERIFY RESERVATION TOOL TIP==========");
			getTestSteps.clear();
			// start here
			getTestSteps = tapeChart.toolTipHeaderVerification(driver, movedRoomA, roomClassName,
					firstName + " " + lastName, twoNightsAmountwithTax, twoNightsAmountwithTax, reservationNumber,
					roomClassAbb);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========VERIFY TOOL TIP LINE 1(ROOM A)==========");
			testSteps.add("==========VERIFY TOOL TIP LINE 1(ROOM A)==========");
			getTestSteps.clear();
			getTestSteps = tapeChart.toolTipVerificationLine(driver, movedRoomA, roomClassName,
					firstName + " " + lastName, Utility.getCurrentDate("MMM dd, yyyy"),
					Utility.GetNextDate(1, "MMM dd, yyyy"), "1N", 1);
			testSteps.addAll(getTestSteps);
			app_logs.info("==========VERIFY TOOL TIP LINE 2(ROOM B)==========");
			testSteps.add("==========VERIFY TOOL TIP LINE 2(ROOM B)==========");
			getTestSteps.clear();
			getTestSteps = tapeChart.toolTipVerificationLine(driver, roomB, roomClassName, firstName + " " + lastName,
					Utility.GetNextDate(1, "MMM dd, yyyy"), Utility.GetNextDate(2, "MMM dd, yyyy"), "1N", 2);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to MOVE Reservation", scriptName, "MOVEReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to MOVE Reservation", scriptName, "MOVEReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// MOVE Reservation
		try {
			driver.navigate().refresh();
			app_logs.info("==========MOVE ROOM B (VERTICALLY) FROM TAPE CHART==========");
			testSteps.add("==========MOVE ROOM B (VERTICALLY) FROM TAPE CHART==========");
			navigation.navTapeChart(driver, test);
			app_logs.info("Navigate TapeChart");
			int index = 3;
			String cellDate = tapeChart.getTapeChartFirstCellDate(driver);
			app_logs.info(cellDate);
			if (cellDate.contains(Utility.getCurrentDate("d"))) {
				index = 2;
			}
			movedRoomB = tapeChart.moveReservations(driver, roomB, roomClassAbb, firstName + " " + lastName, 2, index);
			testSteps.add("Moved Split Reservation from 'Room : " + roomB + "' to 'Room : " + movedRoomB + "'");
			app_logs.info("Moved Split Reservation from 'Room : " + roomB + "' to 'Room : " + movedRoomB + "'");

			driver.navigate().refresh();
			app_logs.info("==========VERIFY RESERVATION TOOL TIP==========");
			testSteps.add("==========VERIFY RESERVATION TOOL TIP==========");
			getTestSteps.clear();
			getTestSteps = tapeChart.toolTipHeaderVerification(driver, movedRoomA, roomClassName,
					firstName + " " + lastName, twoNightsAmountwithTax, twoNightsAmountwithTax, reservationNumber,
					roomClassAbb);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========VERIFY TOOL TIP LINE 1(ROOM A)==========");
			testSteps.add("==========VERIFY TOOL TIP LINE 1(ROOM A)==========");
			getTestSteps.clear();
			getTestSteps = tapeChart.toolTipVerificationLine(driver, movedRoomA, roomClassName,
					firstName + " " + lastName, Utility.getCurrentDate("MMM dd, yyyy"),
					Utility.GetNextDate(1, "MMM dd, yyyy"), "1N", 1);
			testSteps.addAll(getTestSteps);
			app_logs.info("==========VERIFY TOOL TIP LINE 2(ROOM B)==========");
			testSteps.add("==========VERIFY TOOL TIP LINE 2(ROOM B)==========");
			getTestSteps.clear();
			getTestSteps = tapeChart.toolTipVerificationLine(driver, movedRoomB, roomClassName,
					firstName + " " + lastName, Utility.GetNextDate(1, "MMM dd, yyyy"),
					Utility.GetNextDate(2, "MMM dd, yyyy"), "1N", 2);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to MOVE Reservation", scriptName, "MOVEReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to MOVE Reservation", scriptName, "MOVEReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========VERIFY RESERVATION DETAIL AFTER MOVED==========");
			testSteps.add("==========VERIFY RESERVATION DETAIL AFTER MOVED==========");

			navigation.navigateToReservations(driver);
			reservationSearch.multipleSearchReservationNumber(driver, reservationNumber);
			testSteps.add("Search reservation using reservation number");

			getTestSteps.clear();
			getTestSteps = reservation.clickOnGuestNameInSearchReaservation(driver, reservationNumber);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.folioTab(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.verifyStayDateAfterReservation(driver, Utility.getCurrentDate("MMM d"),
					Utility.GetNextDate(2, "MMM d"));

			balance = reservation.getBalanceHeader(driver);
			assertEquals(Float.parseFloat(balance), Float.parseFloat(twoNightsAmountwithTax),
					"Failed to verify Balance");
			testSteps.add("Successfully Verified Balance : " + balance);
			app_logs.info("Successfully Verified Balance : " + balance);
			tripTotal = reservation.getTripTotalHeader(driver);
			assertEquals(Float.parseFloat(tripTotal), Float.parseFloat(twoNightsAmountwithTax),
					"Failed to verify Trip Total");
			testSteps.add("Successfully Verified Trip Total : " + tripTotal);
			app_logs.info("Successfully Verified Trip Total : " + tripTotal);
			String Date = Utility.getCurrentDate("E MMM dd, yyyy");
			app_logs.info("==========VERIFY LINE ITEM==========");
			testSteps.add("==========VERIFY LINE ITEM==========");
			folio.verifyLineItem(driver, Date, roomCharge, amountwithTax, rateName, movedRoomA, true);
			testSteps.add("Successfully Verified Line item is  added");
			app_logs.info("Successfully Verified Line item is added");
			testSteps.add("Line Item 'Date : " + Date + "', 'Room : " + movedRoomA + "', 'Category : " + roomCharge
					+ "', Description : " + rateDescription + "' and Amount : " + amountwithTax + "'");
			app_logs.info("Line Item 'Date : " + Date + "', 'Room : " + movedRoomA + "', 'Category : " + roomCharge
					+ "', Description : " + rateDescription + "' and Amount : " + amountwithTax + "'");
			Date = Utility.GetNextDate(1, "E MMM dd, yyyy");
			app_logs.info("==========VERIFY LINE ITEM==========");
			testSteps.add("==========VERIFY LINE ITEM==========");
			folio.verifyLineItem(driver, Date, roomCharge, amountwithTax, rateName, movedRoomB, true);
			testSteps.add("Successfully Verified Line item is  added");
			app_logs.info("Successfully Verified Line item is added");
			testSteps.add("Line Item 'Date : " + Date + "', 'Room : " + movedRoomB + "', 'Category : " + roomCharge
					+ "', Description : " + rateDescription + "' and Amount : " + amountwithTax + "'");
			app_logs.info("Line Item 'Date : " + Date + "', 'Room : " + movedRoomB + "', 'Category : " + roomCharge
					+ "', Description : " + rateDescription + "' and Amount : " + amountwithTax + "'");
			String getRoomCharge = reservation.getRoomCharge(driver, getTestSteps);
			assertEquals(Float.parseFloat(getRoomCharge), (Float.parseFloat(twoNightsAmount)),
					"Failed to verify Room Charges");
			testSteps.add("Successfully Verified Room Charge : " + getRoomCharge);
			app_logs.info("Successfully Verified Room Charge : " + getRoomCharge);
			taxes = reservation.getTax(driver, getTestSteps);
			app_logs.info("Taxes : " + taxes);
			String getTotalCharges = folio.getTotalCharges(driver);
			assertEquals(Float.parseFloat(getTotalCharges), (Float.parseFloat(twoNightsAmountwithTax)),
					"Failed to verify Total Charges");
			testSteps.add("Successfully Verified Total Charge : " + getTotalCharges);
			app_logs.info("Successfully Verified Total Charge : " + getTotalCharges);
			// String totalBalance = res1.getBalanceFolio(driver);
			String totalBalance = folio.getToatalBalance(driver);
			totalBalance = folio.splitString(totalBalance);
			assertEquals(Float.parseFloat(totalBalance), Float.parseFloat(twoNightsAmountwithTax),
					"Failed to verify folio Balance");
			testSteps.add("Successfully Verified Folio Balance : " + totalBalance);
			app_logs.info("Successfully Verified Folio Balance : " + totalBalance);

			reservation.closeReservationTab(driver);
			testSteps.add("Close Reservation");
			app_logs.info("Close Reservation");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify details after move reservation", scriptName, "TapeChart",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify details after move reservation", scriptName, "TapeChart",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Delete rate
		try {
			app_logs.info("==========DELETE RATE==========");
			testSteps.add("==========DELETE RATE==========");
			navigation.Inventory(driver);
			app_logs.info("Navigate Inventory");
			testSteps.add("Navigate Inventory");

			getTestSteps.clear();
			getTestSteps = navigation.secondaryRatesMenuItem(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.deleteRates(driver, rateName);
			testSteps.addAll(getTestSteps);
			testSteps.add("New Rate has been Deleted successfully");
			app_logs.info("New Rate has been Deleted successfully");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete rate", scriptName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete rate", scriptName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Delete Room Class
		try {
			app_logs.info("==========DELETE ROOM CLASS==========");
			testSteps.add("==========DELETE ROOM CLASS==========");

			navigation.navigateSetup(driver);
			app_logs.info("Navigate Setup");
			testSteps.add("Navigate Setup");

			navigation.navigateRoomClass(driver);
			app_logs.info("Navigate Room Class");
			testSteps.add("Navigate Room Class");

			roomClass.searchClass(driver, tempraryRoomClassname);
			app_logs.info("Search");
			roomClass.deleteRoomClass(driver, tempraryRoomClassname);
			testSteps.add("Delete room class successfully");
			app_logs.info("Delete room class successfully");

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete room class", scriptName, "RoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete room class", scriptName, "RoomClass", driver);
			} else {
				Assert.assertTrue(false);

			}
		}

	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheet name provided
		return Utility.getData("ExtendReduceSplitResInTapeChart", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		// driver.quit();
	}
}
