package com.innroad.inncenter.tests;

import com.innroad.inncenter.pageobjects.*;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class VerifyUpdatedByFieldPaymentModa extends TestCore {

	// Automation-1539
	private WebDriver driver = null;

	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();
	ArrayList<String> testName = new ArrayList<>();
	ArrayList<String> testCategory = new ArrayList<>();
	ArrayList<String> testDescription = new ArrayList<>();
	public static String testdescription = "";
	public static String testcatagory = "";

	@Test(dataProvider = "getData", groups = "Payment")
	public void verifyUpdatedByFieldPaymentModa(String roomClassAbb, String roomClassName, String bedsCount,
			String maxAdults, String maxPersons, String roomQuantity, String rateName, String baseAmount,
			String addtionalAdult, String additionalChild, String ratePolicy, String rateDescription,
			String displayName, String policyType, String policyName, String number, String policyText,
			String policyDescription, String season, String ratePlan, String adults, String children, String promoCode,
			String guestFirstName, String guestLastName, String travelAgent, String marketSegment, String referral,
			String isGuestProfile, String paymentType, String cardNumber, String nameOnCard, String amount,
			String manualRate, String source, String chargesType, String checkPaymentMethod, String reservationLastName,
			String guestPhoneNumber, String guestAlternativePhone, String guestEmail, String guestAddressLine1,
			String guestAddressLine2, String guestAddressLine3, String guestCity, String guestCountry,
			String guestState, String guestPostalCode, String salutation, String guestAmount,
			String newReservationAmount) throws Exception {

		String scriptName = "VerifyUpdatedByFieldPaymentModa";
		String testdescription = "Verify that 'Updated By' field is displayed correctly for Deposit payment line item made for reservation created from tape chart<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682504' target='_blank'>"
				+ "Click here to open TestRail: C682504</a>"
				+ "<br>Verify the 'Updated By' field for payment line item in the reservation created through reservation tab/Guest history<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682516' target='_blank'>"
				+ "Click here to open TestRail: C682516</a>";

		testcatagory = "TapeChart";
		testName.add(scriptName);
		testDescription.add(testdescription);
		testCategory.add(testcatagory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Navigation nav = new Navigation();
		Rate rate = new Rate();
		Navigation navigation = new Navigation();
		Tapechart tapechart = new Tapechart();
		ReservationSearch reservationSearch = new ReservationSearch();
		CPReservationPage reservationPage = new CPReservationPage();

		GuestHistory guestHistory = new GuestHistory();
		Reservation reservation = new Reservation();
		RoomClass roomClass = new RoomClass();
		Folio folio = new Folio();
		String randomNumber = Utility.GenerateRandomNumber(999);
		// randomNumber = "340";

		String roomClassAbbWithPolicy = roomClassAbb + "P";
		String roomClassNameWithPolicy = roomClassName + "Policy";
		guestLastName = guestLastName + randomNumber;
		reservationLastName = reservationLastName + randomNumber;

		String property = Utility.ReportsProperty;
		String timeZone = null;
		Policies policy = new Policies();
		String cardExpDate = null;
		String iconSource = null;
		String icon = null;
		String amountWithTax = null;
		String user = Utility.login_CP.get(2);
		String otherUser = Utility.UserName;
		String description = null;
		String tapeChartReservationNumber = null;
		String guestProfileReservationNumber = null;
		String newReservationNumber = null;

		String accountNumber = null;
		timeZone = "America/New_York";
		cardExpDate = Utility.getNextDate(365, "MM/yy", timeZone);

		try {
			if (!Utility.insertTestName.containsKey(scriptName)) {
				Utility.insertTestName.put(scriptName, scriptName);
				Utility.reTry.put(scriptName, 0);

			} else {
				Utility.reTry.replace(scriptName, 1);
			}
			driver = getDriver();
			login_CP(driver, user);

			testSteps.add("Logged into the application with User : <b>" + user + "</b>");
			app_logs.info("Logged into the application with User : <b>" + user + "</b>");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Select Property
		try {

			String defaultProperty = navigation.getDefaultProperty(driver);

			if (!defaultProperty.equals(Utility.ReportsProperty)) {
				boolean isPropertyExist = navigation.isPropertyExist(driver, property);
				if (!isPropertyExist) {
					property = defaultProperty;

				}
			}

			testSteps.add("Selected property: " + property);
			app_logs.info("Selected property: " + property);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Select Property", scriptName, "SelectProperty", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Select Property", scriptName, "SelectProperty", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// Get Time Zone
		try {
			testSteps.add("===================GET PROPERTY TIME ZONE======================");
			app_logs.info("===================GET PROPERTY TIME ZONE======================");
			navigation.Setup(driver);
			testSteps.add("Navigate Setup");
			app_logs.info("Navigate Setup");
			navigation.Properties(driver);
			testSteps.add("Navigat Properties");
			app_logs.info("Navigat Properties");
			navigation.open_Property(driver, testSteps, property);
			testSteps.add("Open Property : " + property);
			app_logs.info("Open Property : " + property);
			navigation.click_PropertyOptions(driver, testSteps);
			timeZone = navigation.get_Property_TimeZone(driver);
			navigation.Reservation_Backward(driver);
			testSteps.add("Property Time Zone " + timeZone);
			app_logs.info("Property Time Zone " + timeZone);
			app_logs.info("Curret Time " + Utility.getCurrentDate("MMM dd,yyy: H:m:s", timeZone));
			testSteps.add("Curret Time " + Utility.getCurrentDate("MMM dd,yyy: H:m:s", timeZone));
			timeZone = "America/New_York";

			testSteps.add("Time Zone " + timeZone);
			app_logs.info("Time Zone " + timeZone);
			app_logs.info("Curret Time " + Utility.getCurrentDate("MMM dd,yyy: H:m:s", timeZone));
			testSteps.add("Curret Time " + Utility.getCurrentDate("MMM dd,yyy: H:m:s", timeZone));
			cardExpDate = Utility.getNextDate(365, "MM/yy", timeZone);
			app_logs.info("Card Expiry " + cardExpDate);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to get Time Zone", scriptName, "getTimeZone", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to get Time Zone", scriptName, "getTimeZone", driver);

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
				testSteps.add("Deleted all room classes named with <b>"+roomClassName +"</b>");
				app_logs.info("Deleted all room classes named with <b>"+roomClassName +"</b>"); 
			} else {
				 testSteps.add("No previous room class to delete for <b>"+roomClassName+"</b>");
				 app_logs.info("No previous room class to delete for <b>"+roomClassName+"</b>");
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
			roomClassAbbWithPolicy = roomClassAbbWithPolicy + randomNumber;
			roomClassNameWithPolicy = roomClassNameWithPolicy + randomNumber;
			navigation.clickOnNewRoomClass(driver);
			testSteps.add("Click on new room class button");

			getTestSteps = roomClass.createRoomClass(driver, roomClassNameWithPolicy, roomClassAbbWithPolicy, bedsCount, maxAdults,
					maxPersons, roomQuantity, test, getTestSteps);
			testSteps.addAll(getTestSteps);

			testSteps.add("Successfully Created New RoomClass " + roomClassNameWithPolicy);
			app_logs.info("Successfully Created New RoomClass" + roomClassNameWithPolicy);
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

	
		try {
			app_logs.info("==========CREATE ROOM CLASS==========");
			testSteps.add("==========CREATE ROOM CLASS==========");
			navigation.navigateSetup(driver);
			navigation.navigateRoomClass(driver);
			app_logs.info("Navigate Room Class");
			testSteps.add("Navigate Room Class");
			roomClassAbb = roomClassAbb + randomNumber;
			roomClassName = roomClassName + randomNumber;
			navigation.clickOnNewRoomClass(driver);
			testSteps.add("Click on new room class button");

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
			testSteps.add("Deleted all Rates named with <b>"+rateName +"</b>");
			app_logs.info("Deleted all Rates named with <b>"+rateName +"</b>"); 
			rate.verifyDeleteRate(driver, rateName);
			testSteps.add("Verify the Deleted Rate : " + rateName);
			app_logs.info("Verify the Deleted Rate " + rateName);

			app_logs.info("==========CREATE RATE==========");
			testSteps.add("==========CREATE RATE==========");

			rateName = rateName + randomNumber;
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
			getTestSteps = rate.AssociateSeason(driver, season);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = rate.AssociateRoomClass(driver, roomClassNameWithPolicy);
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

			rate.SearchRate(driver, rateName, false);
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

		try {
			testSteps.add("========== DELETE POLICIES ==========");
			nav.Inventory(driver, testSteps);
			nav.policies(driver, testSteps);
			policyName = policyName + randomNumber;
			policy.deleteAllPolicies(driver, testSteps, policyType, policyName);
			testSteps.add("========== CREATE POLICY ==========");
			getTestSteps.clear();
			getTestSteps = policy.NewPolicybutton(driver, policyType, getTestSteps);
			testSteps.addAll(getTestSteps);
			policy.Enter_Policy_Name(driver, policyName, testSteps);
			policy.Enter_Deposit_Policy_Attributes(driver, chargesType, number);
			policy.Enter_Policy_Desc(driver, policyText, policyDescription);
			policy.Associate_Sources(driver);
			policy.AssociateSingle_Seasons(driver, season);
			policy.AssociateSingle_RoomClass(driver, roomClassNameWithPolicy);
			policy.AssociateSingle_RatePlan(driver, ratePlan);
			policy.Save_Policy(driver);
			policy.Close_Policy_Tab(driver);
			testSteps.add(policyType + "Policy '" + policyName + "' Created successfully");
			app_logs.info(policyType + "Policy '" + policyName + "' Created successfully");
			policy.Verify_Policy(driver, policyName);

			testSteps.add("Successfully Verify Policy " + policyName);
			app_logs.info("Successfully Verify Policy " + policyName);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create two policies", scriptName, "Policy", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create two policies", scriptName, "Policy", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			testSteps.add("==========CREATE ASSIGNED RESERVATION FROM <b>TAPE CHART</b>==========");
			app_logs.info("==========CREATE ASSIGNED RESERVATION FROM <b>TAPE CHART</b>==========");
			navigation.navigateToReservations(driver);
			testSteps.add("Navigate Reservations");
			app_logs.info("Navigate Reservations");
			navigation.navigateTapeChart(driver, test);
			testSteps.add("Navigate TapeChart");
			app_logs.info("Navigate TapeChart");
			app_logs.info("==========ENTER CHECK IN DETAILS AND SEARCH ROOMS==========");
			testSteps.add("==========ENTER CHECK IN DETAILS AND SEARCH ROOMS==========");
			getTestSteps.clear();
			getTestSteps = tapechart.tapeChartSearch(driver, Utility.getCurrentDate("MM/dd/yyyy", timeZone),
					Utility.getNextDate(1, "MM/dd/yyyy", timeZone), adults, children, ratePlan, getTestSteps);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========SELECT ROOM==========");
			testSteps.add("==========SELECT ROOM==========");
			tapechart.clickAvailableSlot(driver, roomClassAbbWithPolicy);
			testSteps.add("Click available room of Room Class '" + roomClassAbbWithPolicy + "'");
			app_logs.info("Click on available room");
			testSteps.add("New Reservation page is opened");
			app_logs.info("New Reservation Page is Opened");
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to click New Tape Chart button", scriptName, "Tape Chart", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to click New Tape Chart button", scriptName, "Tape Chart", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Reservation
		try {
			reservationPage.uncheck_CreateGuestProfile(driver, testSteps, isGuestProfile);
			reservationPage.enterGuestName(driver, testSteps, guestFirstName, reservationLastName);
			reservationPage.enter_PaymentDetails(driver, testSteps, paymentType, cardNumber, nameOnCard, cardExpDate);
			reservationPage.enter_MarketSegmentDetails(driver, testSteps, travelAgent, marketSegment, referral);
			reservationPage.clickBookNow(driver, testSteps);
			tapeChartReservationNumber = reservationPage.get_ReservationConfirmationNumber(driver, testSteps);
			reservationPage.get_ReservationStatus(driver, testSteps);
			reservationPage.clickCloseReservationSavePopup(driver, testSteps);
			testSteps.add("===================== VERIFY DEPOSIT POLICY =====================");
			app_logs.info("===================== VERIFY DEPOSIT POLICY =====================");
			reservationPage.verifyDepositPolicyDetailsAtPoliciesAndDisclaimers(driver, testSteps, policyName,
					policyText);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to click New reservation button", scriptName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to click New reservation button", scriptName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Select Payment Method Type MC
		try {
			reservationPage.click_Folio(driver, testSteps);
			amountWithTax = folio.getLineItemAmount(driver, paymentType, true);
			amountWithTax = Utility.removeDollarBracketsAndSpaces(amountWithTax);
			testSteps.add("Line item '" + paymentType + "' amount with Tax '" + amountWithTax + "'");
			app_logs.info("Line item '" + paymentType + "' amount with Tax '" + amountWithTax + "'");
			testSteps.add("========== VERIFY PAYMENT LINE ITEM ==========");
			app_logs.info("========== VERIFY PAYMENT LINE ITEM ==========");
			iconSource = "Folio_Images/8.gif";
			// Utility.LineItemIconSource.get(5);
			icon = Utility.LineItemIconSource.get(4);
			description = "Name: " + nameOnCard + " Account #: XXXX" + cardNumber.substring(cardNumber.length() - 4)
					+ " Exp. Date: " + cardExpDate;
			folio.verifyLineItemClickDescription(driver, iconSource, paymentType, amountWithTax, description, true);

			testSteps.add("Verified '" + icon + "' Line item '" + paymentType + "' has been added successfully");
			app_logs.info("Verified '" + icon + "' Line item '" + paymentType + "' has been added successfully");
			testSteps.add("Click  Line item '" + paymentType + "' Description");
			app_logs.info("CLick  Line item '" + paymentType + "' Description");
			getTestSteps.clear();
			getTestSteps = reservationPage.verifyUpdatedByInItemDetailPopUp(driver, user, getTestSteps);
			testSteps.addAll(getTestSteps);
			reservation.closePaymentDetailPopup(driver, "Cancel");
			testSteps.add("Successfully Close Payment Details popup");
			app_logs.info("Successfully Close Payment Details popup");
			reservation.CloseOpenedReservation(driver);
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Select Payment Method Type MC", scriptName, "Payment Method",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Select Payment Method Type MC", scriptName, "Payment Method",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("======CREATE GUEST PROFILE ACCOUNT=======");
			testSteps.add("======CREATE GUEST PROFILE ACCOUNT=======");
			navigation.navigateToReservations(driver);
			testSteps.add("Navigate Reservations");
			app_logs.info("Navigate Reservations");
			navigation.GuestHistory(driver);
			app_logs.info("Navigate To Guest History");
			testSteps.add("Navigate To Guest History");
			guestHistory.guestHistory_NewAccount(driver);
			app_logs.info("Click To New Account Button");
			testSteps.add("Click To New Account Button");

			guestHistory.enterFirstLastName(driver, guestFirstName, guestLastName, testSteps);

			guestHistory.accountAttributes(driver, marketSegment, referral, testSteps);
			getTestSteps.clear();
			getTestSteps = guestHistory.Mailinginfo(driver, guestFirstName, guestLastName, guestPhoneNumber,
					guestAlternativePhone, guestAddressLine1, guestAddressLine2, guestAddressLine3, guestEmail,
					guestCity, guestState, guestPostalCode);
			testSteps.addAll(getTestSteps);
			guestHistory.Billinginfo(driver, testSteps);
			guestHistory.Save(driver);
			app_logs.info("Save Button Clicked");
			testSteps.add("Save Button Clicked");
			accountNumber = guestHistory.GetAccountNumber(driver);
			app_logs.info("Successfully Created Guest History Account " + accountNumber);
			testSteps.add("Successfully Created Guest History Account " + accountNumber);

		} catch (Exception e) {

			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Create Guest Histroy Account ", scriptName, "GuestHistoryAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Create Guest Histroy Account", scriptName, "GuestHistoryAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========CREATE NEW RESERVATION FROM <b>GUEST PROFILE ACCOUNT</b>==========");
			testSteps.add("==========CREATE NEW RESERVATION FROM <b>GUEST PROFILE ACCOUNT</b>==========");

			guestHistory.clickNewReservation(driver);
			testSteps.add("Click New Reservation");
			app_logs.info("Click New Reservation");

			String checkInDate = Utility.getCurrentDate("MM/dd/yyyy", timeZone);
			String checkoutDate = Utility.getNextDate(1, "MM/dd/yyyy", timeZone);
			reservationPage.EnterDate(driver, "start", checkInDate);
			testSteps.add("Select CheckIn date : " + checkInDate);
			app_logs.info("Selecting checkin date : " + checkInDate);
			reservationPage.EnterDate(driver, "end", checkoutDate);
			testSteps.add("Select Checkout date : " + checkoutDate);
			app_logs.info("Selecting checkin date : " + checkoutDate);
			getTestSteps.clear();
			getTestSteps = reservationPage.enterAdult(driver, adults);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterChildren(driver, children);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnFindRooms(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectRoom(driver, getTestSteps, roomClassName, "Yes", "");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			reservationPage.enter_PaymentDetails(driver, testSteps, paymentType, cardNumber, nameOnCard, cardExpDate);
			reservationPage.clickBookNow(driver, testSteps);
			guestProfileReservationNumber = reservationPage.get_ReservationConfirmationNumber(driver, testSteps);
			reservationPage.get_ReservationStatus(driver, testSteps);
			reservationPage.clickCloseReservationSavePopup(driver, testSteps);
			reservationPage.closeReservationTab(driver);
			testSteps.add("Close Reservation");
			app_logs.info("Close Reservation");
			reservationPage.closeReservationTab(driver);
			testSteps.add("Close Guest Profile Account");
			app_logs.info("Close Guest Profile Account");

		} catch (Exception e) {

			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Create Reservation ", scriptName, "CreateReservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Create Reservation", scriptName, "CreateReservationt", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			driver.navigate().refresh();
			app_logs.info("==========CREATE NEW RESERVATION FROM <b>NEW RESERVATION</b> BUTTON==========");
			testSteps.add("==========CREATE NEW RESERVATION FROM <b>NEW RESERVATION</b> BUTTON==========");
			navigation.navigateToReservations(driver);
			testSteps.add("Navigate Reservations");
			app_logs.info("Navigate Reservations");
			reservationPage.click_NewReservation(driver, testSteps);

			String CheckInDate = Utility.getCurrentDate("MM/dd/yyyy", timeZone);
			String CheckoutDate = Utility.getNextDate(1, "MM/dd/yyyy", timeZone);
			try {
				reservationPage.EnterDate(driver, "start", CheckInDate);
			} catch (Exception newReservation) {
				reservationPage.click_NewReservation(driver, getTestSteps);
				reservationPage.EnterDate(driver, "start", CheckInDate);
			}
			testSteps.add("Select CheckIn date : " + CheckInDate);
			app_logs.info("Selecting checkin date : " + CheckInDate);
			reservationPage.EnterDate(driver, "end", CheckoutDate);
			testSteps.add("Select Checkout date : " + CheckoutDate);
			app_logs.info("Selecting checkin date : " + CheckoutDate);
			getTestSteps.clear();
			getTestSteps = reservationPage.enterAdult(driver, adults);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterChildren(driver, children);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnFindRooms(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectRoom(driver, getTestSteps, roomClassName, "Yes", "");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.verifyGuestProfileCheckoxChecked(driver, false);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterGuestName(driver, getTestSteps, salutation, guestFirstName,
					reservationLastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectReferral(driver, referral);
			testSteps.addAll(getTestSteps);

			reservationPage.enter_PaymentDetails(driver, testSteps, paymentType, cardNumber, nameOnCard, cardExpDate);
			reservationPage.clickBookNow(driver, testSteps);
			newReservationNumber = reservationPage.get_ReservationConfirmationNumber(driver, testSteps);
			reservationPage.get_ReservationStatus(driver, testSteps);
			reservationPage.clickCloseReservationSavePopup(driver, testSteps);
			reservationPage.closeReservationTab(driver);
			testSteps.add("Close Reservation");
			app_logs.info("Close Reservation");

		} catch (Exception e) {

			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Create Reservation ", scriptName, "CreateReservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Create Reservation", scriptName, "CreateReservationt", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========<b>LOGGED OUT</b>==========");
			testSteps.add("==========<b>LOGGED OUT</b>==========");
			logout(driver);
			testSteps.add("Logged out the application");
			app_logs.info("Logged out the application");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========<b>LOGGED IN</b>==========");
			testSteps.add("==========<b>LOGGED IN</b>==========");
			login_CP(driver, otherUser);
			testSteps.add("Logged into the application with User : <b>" + otherUser + "</b>");
			app_logs.info("Logged into the application with User : <b>" + otherUser + "</b>");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// for testing
		// SEARCH RESERVATION
		try {
			app_logs.info("==========SEARCH RESERVATION CREATED FROM <b>TAPE CHART</b>==========");
			testSteps.add("==========SEARCH RESERVATION CREATED FROM <b>TAPE CHART</b>==========");
			reservationSearch.basicSearchWithResNumber(driver, tapeChartReservationNumber, true);

			testSteps.add("Reservation Successfully Searched : " + tapeChartReservationNumber);
			app_logs.info("Reservation Successfully Searched : " + tapeChartReservationNumber);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to search and open tapchart reservation", scriptName,
						"SearchReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to search and open tapchart reservation", scriptName,
						"SearchReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Select Payment Method Type MC
		try {
			testSteps.add("========== MAKE PAYMENT USING PAYMENT METHOD : </b>" + checkPaymentMethod.toUpperCase()
					+ "</b> ==========");

			reservationPage.click_Folio(driver, testSteps);
			reservationPage.selectPaymentMethodType(driver, testSteps, checkPaymentMethod, cardNumber, nameOnCard,
					cardExpDate, reservationPage.getReservationNumber(driver), amount);
			reservationPage.clickSavePaymentButton(driver);
			testSteps.add("Save Folio");
			app_logs.info("Save Folio");
			amountWithTax = folio.getLineItemAmount(driver, checkPaymentMethod, true);
			amountWithTax = Utility.removeDollarBracketsAndSpaces(amountWithTax);
			testSteps.add("Line item '" + checkPaymentMethod + "' amount with Tax '" + amountWithTax + "'");
			app_logs.info("Line item '" + checkPaymentMethod + "' amount with Tax '" + amountWithTax + "'");
			testSteps.add("========== VERIFY PAYMENT LINE ITEM ==========");
			app_logs.info("========== VERIFY PAYMENT LINE ITEM ==========");
			iconSource = Utility.LineItemIconSource.get(3);
			icon = Utility.LineItemIconSource.get(2);
			folio.verifyLineItemClickDescription(driver, iconSource, checkPaymentMethod, amountWithTax,
					checkPaymentMethod, true);
			testSteps.add("Verified '" + icon + "' Line item '" + checkPaymentMethod + "' has been added successfully");
			app_logs.info("Verified '" + icon + "' Line item '" + checkPaymentMethod + "' has been added successfully");
			testSteps.add("========== VERIFY UPDATED BY <b>USER NAME</b> ==========");
			testSteps.add("Click  Line item '" + checkPaymentMethod + "' Description");
			app_logs.info("CLick  Line item '" + checkPaymentMethod + "' Description");
			getTestSteps.clear();
			getTestSteps = reservationPage.verifyUpdatedByInItemDetailPopUp(driver, otherUser, getTestSteps);
			testSteps.addAll(getTestSteps);
			reservation.closePaymentDetailPopup(driver, "Close");
			testSteps.add("Successfully Close Payment Details popup");
			app_logs.info("Successfully Close Payment Details popup");

			reservationPage.closeReservationTab(driver);
			testSteps.add("Close Reservation");
			app_logs.info("Close Reservation");

			driver.navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to search tapchart reservationC", scriptName, "Payment Method", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to search tapchart reservation", scriptName, "Payment Method", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			app_logs.info("==========SEARCH RESERVATION CREATED FORM <b>GUEST PROFILE ACCOUNT</b>==========");
			testSteps.add("==========SEARCH RESERVATION CREATED FORM <b>GUEST PROFILE ACCOUNT</b>==========");
			reservationSearch.basicSearchWithResNumber(driver, guestProfileReservationNumber, true);

			testSteps.add("Reservation Successfully Searched : " + guestProfileReservationNumber);
			app_logs.info("Reservation Successfully Searched : " + guestProfileReservationNumber);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to make payment and verify updated by username", scriptName,
						"UpdateByUser", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to make payment and verify updated by username", scriptName,
						"UpdateByUser", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Select Payment Method Type MC
		try {
			testSteps.add("========== MAKE PAYMENT USING PAYMENT METHOD : </b>" + paymentType.toUpperCase()
					+ "</b> ==========");

			reservationPage.click_Folio(driver, testSteps);
			reservationPage.makePayment(driver, testSteps, guestAmount);
			reservationPage.clickSavePaymentButton(driver);
			testSteps.add("Save Folio");
			app_logs.info("Save Folio");
			amountWithTax = folio.getLineItemAmount(driver, paymentType, true);
			amountWithTax = Utility.removeDollarBracketsAndSpaces(amountWithTax);
			testSteps.add("Line item '" + paymentType + "' amount with Tax '" + amountWithTax + "'");
			app_logs.info("Line item '" + paymentType + "' amount with Tax '" + amountWithTax + "'");
			testSteps.add("========== VERIFY PAYMENT LINE ITEM ==========");
			app_logs.info("========== VERIFY PAYMENT LINE ITEM ==========");
			iconSource = "Folio_Images/8.gif";
			// Utility.LineItemIconSource.get(5);
			icon = Utility.LineItemIconSource.get(4);
			description = "Name: " + nameOnCard + " Account #: XXXX" + cardNumber.substring(cardNumber.length() - 4)
					+ " Exp. Date: " + cardExpDate;
			folio.verifyLineItemClickDescription(driver, iconSource, paymentType, amountWithTax, description, true);
			testSteps.add("Verified '" + icon + "' Line item '" + paymentType + "' has been added successfully");
			app_logs.info("Verified '" + icon + "' Line item '" + paymentType + "' has been added successfully");
			testSteps.add("========== VERIFY UPDATED BY <b>" + otherUser + "</b> ==========");
			testSteps.add("Click  Line item '" + paymentType + "' Description");
			app_logs.info("CLick  Line item '" + paymentType + "' Description");
			getTestSteps.clear();
			getTestSteps = reservationPage.verifyUpdatedByInItemDetailPopUp(driver, otherUser, getTestSteps);
			testSteps.addAll(getTestSteps);
			reservation.closePaymentDetailPopup(driver, "Cancel");
			testSteps.add("Successfully Close Payment Details popup");
			app_logs.info("Successfully Close Payment Details popup");

			reservationPage.closeReservationTab(driver);
			testSteps.add("Close Reservation");
			app_logs.info("Close Reservation");

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to make payment and verify updated by username", scriptName,
						"UpdateByUser", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to make payment and verify updated by username", scriptName,
						"UpdateByUser", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			app_logs.info("==========SEARCH RESERVATION CREATED FORM <b>NEW RESERVATION</b> BUTTON==========");
			testSteps.add("==========SEARCH RESERVATION CREATED FORM <b>NEW RESERVATION</b> BUTTON==========");
			reservationSearch.basicSearchWithResNumber(driver, newReservationNumber, true);

			testSteps.add("Reservation Successfully Searched : " + newReservationNumber);
			app_logs.info("Reservation Successfully Searched : " + newReservationNumber);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to make payment and verify updated by username", scriptName,
						"UpdateByUser", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to make payment and verify updated by username", scriptName,
						"UpdateByUser", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Select Payment Method Type MC
		try {
			testSteps.add("========== MAKE PAYMENT USING PAYMENT METHOD : </b>" + checkPaymentMethod.toUpperCase()
					+ "</b> ==========");

			reservationPage.click_Folio(driver, testSteps);
			reservationPage.selectPaymentMethodType(driver, testSteps, checkPaymentMethod, cardNumber, nameOnCard,
					cardExpDate, reservationPage.getReservationNumber(driver), newReservationAmount);
			reservationPage.clickSavePaymentButton(driver);
			testSteps.add("Save Folio");
			app_logs.info("Save Folio");
			amountWithTax = folio.getLineItemAmount(driver, checkPaymentMethod, true);
			amountWithTax = Utility.removeDollarBracketsAndSpaces(amountWithTax);
			testSteps.add("Line item '" + checkPaymentMethod + "' amount with Tax '" + amountWithTax + "'");
			app_logs.info("Line item '" + checkPaymentMethod + "' amount with Tax '" + amountWithTax + "'");
			testSteps.add("========== VERIFY PAYMENT LINE ITEM ==========");
			app_logs.info("========== VERIFY PAYMENT LINE ITEM ==========");
			iconSource = Utility.LineItemIconSource.get(3);
			icon = Utility.LineItemIconSource.get(2);
			folio.verifyLineItemClickDescription(driver, iconSource, checkPaymentMethod, amountWithTax,
					checkPaymentMethod, true);
			testSteps.add("Verified '" + icon + "' Line item '" + checkPaymentMethod + "' has been added successfully");
			app_logs.info("Verified '" + icon + "' Line item '" + checkPaymentMethod + "' has been added successfully");
			testSteps.add("========== VERIFY UPDATED BY <b>USER NAME</b> ==========");
			testSteps.add("Click  Line item '" + checkPaymentMethod + "' Description");
			app_logs.info("CLick  Line item '" + checkPaymentMethod + "' Description");
			getTestSteps.clear();
			getTestSteps = reservationPage.verifyUpdatedByInItemDetailPopUp(driver, otherUser, getTestSteps);
			testSteps.addAll(getTestSteps);
			reservation.closePaymentDetailPopup(driver, "Close");
			testSteps.add("Successfully Close Payment Details popup");
			app_logs.info("Successfully Close Payment Details popup");

			reservationPage.closeReservationTab(driver);
			testSteps.add("Close Reservation");
			app_logs.info("Close Reservation");

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to make payment and verify updated by username", scriptName,
						"UpdateByUser", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to make payment and verify updated by username", scriptName,
						"UpdateByUser", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			testSteps.add("===================== Deleting policy =====================");

			navigation.Inventory(driver);
			app_logs.info("Navigate Inventory");
			testSteps.add("Navigate Inventory");

			getTestSteps.clear();
			navigation.policies(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = policy.deleteAllPolicies(driver, getTestSteps, policyType, policyName);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete policy", scriptName, "Policy", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete policy", scriptName, "Policy", driver);
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

			rate.verifyDeleteRate(driver, rateName);
			testSteps.add("Verify the Deleted Rate : " + rateName);
			app_logs.info("Verify the Deleted Rate " + rateName);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Delete rate", scriptName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Delete rate", scriptName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Delete Room Class
		try {

			app_logs.info("==========DELETE ROOM CLASS==========");
			testSteps.add("==========DELETE ROOM CLASS==========");

			navigation.mainSetupManu(driver);
			app_logs.info("Navigate Setup");
			testSteps.add("Navigate Setup");

			navigation.RoomClass(driver);
			app_logs.info("Navigate Room Class");
			testSteps.add("Navigate Room Class");

			boolean isRoomClassExist = roomClass.searchClass(driver, roomClassNameWithPolicy);
			app_logs.info("Search");
			if (isRoomClassExist) {

				getTestSteps.clear();
				getTestSteps = roomClass.deleteRoomClass(driver, roomClassNameWithPolicy);
				testSteps.addAll(getTestSteps);

			}
			testSteps.add("Deleted Room Class : " + roomClassNameWithPolicy);
			app_logs.info("Deleted Room Class :  " + roomClassNameWithPolicy);
			
			isRoomClassExist = roomClass.searchClass(driver, roomClassName);
			app_logs.info("Search");
			if (isRoomClassExist) {

				getTestSteps.clear();
				getTestSteps = roomClass.deleteRoomClass(driver, roomClassName);
				testSteps.addAll(getTestSteps);

			}
			testSteps.add("Deleted Room Class : " + roomClassName);
			app_logs.info("Deleted Room Class :  " + roomClassName);

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete room class", scriptName, "RoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete room class", scriptName, "RoomClass", driver);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {

		return Utility.getData("VerifyUpdatedByFieldPaymentModa", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closedriver() {
		 driver.quit();
	}

}
