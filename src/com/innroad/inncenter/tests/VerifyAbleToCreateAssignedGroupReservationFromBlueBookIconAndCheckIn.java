package com.innroad.inncenter.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.AdvGroups;
import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Folio;
import com.innroad.inncenter.pageobjects.Groups;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.Reservation;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.Tapechart;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyAbleToCreateAssignedGroupReservationFromBlueBookIconAndCheckIn extends TestCore {

	// Automation-1467
	private WebDriver driver = null;

	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();
	ArrayList<String> scriptName = new ArrayList<>();
	ArrayList<String> testCategory = new ArrayList<>();
	ArrayList<String> testDescription = new ArrayList<>();

	public static String test_description = "";
	public static String test_catagory = "";

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {

		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Inventory")
	public void verifyAbleToCreateAssignedGroupReservationFromBlueBookIconAndCheckIn(String rateName, String baseAmount,
			String addtionalAdult, String additionalChild, String displayName, String associateSession,
			String ratePolicy, String rateDescription, String roomClassAbbreviation, String roomClassName,
			String bedsCount, String maxAdults, String maxPersons, String roomQuantity, String startRoomNumber,
			String ratePlan, String rateType, String rateAttributes, String interval, String source, String adults,
			String child, String marketSegment, String groupReferral, String groupFirstName, String groupLastName,
			String groupPhone, String groupAddress, String groupCity, String groupCountry, String groupState,
			String groupPostalcode, String blockName, String roomPerNight, String firstName, String lastName,
			String updatedBlockedCount, String roomBlockCount, String lineItemDescription, String roomCharge,
			String postedState, String itemRow, String spanTag, String guestFolio, String pendingState,
			String blueBookClass, String yellowBookClass, String yelloFirstName, String yellowLastName,
			String isChecked, String HistoryCategory, String paymentType, String cardNumber, String cardName,
			String cardExpDate) throws InterruptedException, IOException {

		String testName = "VerifyAbleToCreateAssignedGroupReservationFromBlueBookIconAndCheckIn";
		test_description = "Verify check in functionality when reservation is picked from group block.<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682468' target='_blank'>"
				+ "Click here to open TestRail: C682468</a>";
		test_catagory = "Groups";
		scriptName.add(testName);
		testDescription.add(test_description);
		testCategory.add(test_catagory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Rate rate = new Rate();
		Navigation navigation = new Navigation();
		CPReservationPage reservation = new CPReservationPage();
		Folio folio = new Folio();
		RoomClass roomClass = new RoomClass();
		String tempraryRoomClassName = roomClassName;
		String tempraryRateName = rateName;
		String randomNumber = Utility.GenerateRandomNumber();
		roomClassName = roomClassName + randomNumber;
		roomClassAbbreviation = roomClassAbbreviation + randomNumber;
		rateName = rateName + randomNumber;
		displayName = rateName;
		groupLastName = groupLastName + randomNumber;
		String accountName = groupFirstName + groupLastName;
		lastName = lastName + randomNumber;
		String blueReservationFirstName = "Blue" + firstName;
		String accountNumber = null;
		String saluation = "Mr.";
		String reservationNumber = null;
		Groups group = new Groups();
		AdvGroups advanceGroup = new AdvGroups();
		blockName = blockName + randomNumber;
		String tax = null;
		String expectedRevenue = null;
		yellowLastName = yellowLastName + randomNumber;

		String beforeAvailableRoom = null;
		String beforePickupValue = null;
		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);

			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver = getDriver();
			login_CP(driver);
			testSteps.add("Logged into the application ");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			app_logs.info("==========CREATE A NEW ROOM CLASS==========");
			testSteps.add("==========CREATE A NEW ROOM CLASS==========");

			navigation.mainSetupManu(driver);
			app_logs.info("Navigate Setup");
			testSteps.add("Navigate Setup");

			navigation.RoomClass(driver);
			app_logs.info("Navigate Room Class");
			testSteps.add("Navigate Room Class");

			navigation.clickOnNewRoomClass(driver);
			getTestSteps.clear();
			getTestSteps = roomClass.createRoomClass(driver, roomClassName, roomClassAbbreviation, bedsCount, maxAdults,
					maxPersons, roomQuantity, test, getTestSteps);
			testSteps.addAll(getTestSteps);

			testSteps.add("Sccessfully Created New RoomClass " + roomClassName + " Abb : " + roomClassAbbreviation);
			app_logs.info("Sccessfully Created New RoomClass" + roomClassName + " Abb : " + roomClassAbbreviation);

			roomClass.closeRoomClassTab(driver);
			testSteps.add("Close created room class tab");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create room class successfully", testName, "NewRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create room class successfully", testName, "NewRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Create New Rate and Attach RoomClass

		try {
			testSteps.add("==========CREATE A NEW RATE==========");
			app_logs.info("==========CREATE A NEW RATE==========");

			navigation.Inventory(driver);
			app_logs.info("Navigate Inventory");
			testSteps.add("Navigate Inventory");

			getTestSteps.clear();
			getTestSteps = navigation.secondaryRatesMenuItem(driver);
			testSteps.addAll(getTestSteps);

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
			getTestSteps = rate.AssociateSeason(driver, associateSession);
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

			// rate.SearchRate(driver, rateName, false);

			testSteps.add("New Rate '" + rateName + "' Created & Verified ");
			app_logs.info("New Rate '" + rateName + "' Created & Verified");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create new rate", testName, "CreateNewRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create new rate", testName, "CreateNewRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Clicking on Groups
		try {
			navigation.navigateToReservations(driver);
			testSteps.add("Navigate Reservations");
			app_logs.info("Navigate Reservations");

			navigation.secondaryGroupsManu(driver);
			testSteps.add("Navigate Groups");
			app_logs.info(" Navigate Groups");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Navigate AdvGroup", testName, "NavigateAdvGroup", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Navigate AdvGroup", testName, "NavigateAdvGroup", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Click New Account and Enter Account Name
		try {
			app_logs.info("==========CREATE NEW GROUP ACCOUNT==========");
			testSteps.add("==========CREATE NEW GROUP ACCOUNT==========");
			getTestSteps.clear();
			getTestSteps = group.enterrGroupName(driver, accountName);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to click on new account button", testName, "EnterAccountName", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to click new account and enter account name", testName,
						"EnterAccountName", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Enter Account Number
		try {
			accountNumber = Utility.GenerateRandomString15Digit();
			getTestSteps.clear();
			getTestSteps = group.enterAccountNo(driver, accountNumber);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Enter Account Number", testName, "EnterAccountNumber", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Enter Account Number", testName, "EnterAccountNumber", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Enter Account Attributes
		try {
			getTestSteps.clear();
			getTestSteps = group.selectAccountAttributes(driver, marketSegment, groupReferral);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select account attributes", testName, "AccountAttributes", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select account attributes", testName, "AccountAttributes", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Enter Account Mailing Address
		try {
			getTestSteps.clear();
			getTestSteps = group.enterMailingAddress(driver, groupFirstName, groupLastName, groupPhone, groupAddress,
					groupCity, groupState, groupCountry, groupPostalcode);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Enter Account Mailing Address", testName,
						"EnterAccountMailingAddress", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Enter Account Mailing Address", testName,
						"EnterAccountMailingAddress", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Check Mailing Info CheckBox
		try {
			getTestSteps.clear();
			getTestSteps = group.Billinginfo(driver);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Check Mailing Info CheckBox", testName, "CheckMailingInfoCheckBox",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Check Mailing Info CheckBox", testName, "CheckMailingInfoCheckBox",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Save Account
		try {
			getTestSteps.clear();
			getTestSteps = group.clickOnSave(driver);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Save  Account", testName, "SaveAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Save Account", testName, "SaveAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Navigate to Reservation and check count
		try {
			app_logs.info("==========GET RESERVATION COUNT==========");
			testSteps.add("==========GET RESERVATION COUNT==========");
			getTestSteps.clear();
			group.clickOnGroupsReservationTab(driver);
			testSteps.addAll(getTestSteps);

			String initialResCount = group.getReservationCount(driver);
			testSteps.add("Initial Reservation Count : " + initialResCount);
			Utility.app_logs.info("Initial Reservation Count : " + initialResCount);
			assertEquals(initialResCount, "0", "Failed: Initial count is not zero");
			testSteps.add("Verified initial account");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Navigate Folio", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Navigate Folio", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========CREATE NEW BLOCK==========");
			testSteps.add("==========CREATE NEW BLOCK==========");

			getTestSteps.clear();
			getTestSteps = group.navigateRoomBlock(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = group.ClickNewBlock(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = group.EnterBlockName(driver, blockName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = group.ClickOkay_CreateNewBlock(driver);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========SEARCH ROOMS==========");
			testSteps.add("==========SEARCH ROOMS==========");

			getTestSteps.clear();
			getTestSteps = group.SelectArrivalDepartureDates(driver, Utility.getCurrentDate("MMM dd, yyyy"),
					Utility.GetNextDate(1, "MMM dd, yyyy"));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = group.SelectRatePlan(driver, ratePlan);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = group.SelectAdults(driver, adults);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = group.SelectChilds(driver, child);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = group.EnterNights(driver, roomPerNight);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = group.ClickSearchGroup(driver);
			testSteps.addAll(getTestSteps);

			advanceGroup.updatedAutomaticallyAssignedRooms(driver, updatedBlockedCount);
			advanceGroup.BlockRoomForSelectedRoomclass(driver, roomBlockCount, roomClassName);

			getTestSteps.clear();
			getTestSteps = advanceGroup.ClickCreateBlock(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========GET AND VERIFY ROOM BLOCKS TAB DETAILS==========");
			testSteps.add("==========GET AND VERIFY ROOM BLOCKS TAB DETAILS==========");

			String RoomBlocked = group.getRoomBlockedInRoomBlockDetatil(driver);
			Utility.app_logs.info("Room Block Detail Room Blocked  : " + RoomBlocked);
			testSteps.add("Room Block Detail Room Blocked  : " + RoomBlocked);
			assertEquals(RoomBlocked, roomPerNight, "Failed Room Blocked Not Matched");

			String totalRoomNight = group.getTotalRoomNightsInRoomBlockDetail(driver);
			Utility.app_logs.info("Room Block Detail Total Room Nights  : " + totalRoomNight);
			testSteps.add("Room Block Detail Total Room Nights  : " + totalRoomNight);
			assertEquals(totalRoomNight, roomPerNight, "Failed Room Blocked Not Matched");

			String expectedRevenueDetail = group.getExpectedRevenueInRoomBlockDetail(driver);
			Utility.app_logs.info("Room Block Detail Expected Revenue  : " + expectedRevenueDetail);
			testSteps.add("Room Block Detail ExpectedRevenue  : " + expectedRevenueDetail);

			String expectedRevenueInfo = group.getExpectedRevenueInGroupInfo(driver);
			Utility.app_logs.info("Group Info Expected Revenue  : " + expectedRevenueInfo);
			testSteps.add("Group Info ExpectedRevenue  : " + expectedRevenueInfo);
			assertEquals(expectedRevenueDetail, expectedRevenueInfo, "Failed Expected Revenue Not Matched");

			expectedRevenue = expectedRevenueInfo;
			String pickUpPercentage = group.getPickUpPercentageInRoomBlockDetatil(driver);
			Utility.app_logs.info("Room Block Detail PickUp Percentage  : " + pickUpPercentage);
			testSteps.add("Room Block Detail PickUp Percentage  : " + pickUpPercentage);
			assertEquals(pickUpPercentage, "0", "Failed Pickup Percentage missmatched");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Create Block and Verify RoomClass", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Create Block and Verify RoomClass", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		String getRoomNumber = "";
		try {

			testSteps.add("==========SELECT ROOM NUMBER==========");
			app_logs.info("==========SELECT ROOM NUMBER==========");
			group.clickOnSelectRooms(driver);
			testSteps.add("Click on select room button");
			getRoomNumber = group.getRomNumberFromSelectRooms(driver, roomClassAbbreviation, roomClassName);
			app_logs.info("Select room number: " + getRoomNumber);
			testSteps.add("");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Create Block and Verify RoomClass", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Create Block and Verify RoomClass", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			String beforeBookIconClass = null;

			app_logs.info("==========ROOMING LIST DETAILS==========");
			testSteps.add("==========ROOMING LIST DETAILS==========");

			Utility.app_logs.info("Book Room From RoomClass : " + roomClassName);
			testSteps.add("Book Room From RoomClass : " + roomClassName);

			beforePickupValue = group.getPickUpValue(driver, roomClassName);
			Utility.app_logs.info("Pickup Value : " + beforePickupValue);
			testSteps.add("Pickup value : " + beforePickupValue);

			beforeAvailableRoom = group.getAvailableRooms(driver, roomClassName);
			Utility.app_logs.info("Available Rooms : " + beforeAvailableRoom);
			testSteps.add("Available Rooms : " + beforeAvailableRoom);

			beforeBookIconClass = group.getBookIconClass(driver, roomClassName);
			testSteps.add("BookIcon class : " + beforeBookIconClass);
			assertEquals(beforeBookIconClass, blueBookClass, "Failed Book Icon is not Blue");

			String blockedCount = group.getBlocked(driver, roomClassName);
			testSteps.add("Blocked count  : " + blockedCount);
			assertEquals(blockedCount, roomBlockCount, "Failed Room Blocked Not Matched");

			group.bookIconClick(driver, roomClassName);
			Utility.app_logs.info("Click <b>Blue Book Icon</b> of Room Class  : " + roomClassName);
			testSteps.add("Click <b>Blue Book Icon</b> of Room Class  : " + roomClassName);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Process Book Icon", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Process Book Icon", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Create Reservation
		try {
			app_logs.info("==========ENTER RESERVATION DETAILS CREATED FROM <b>YELLOW BOOK ICON</b>==========");
			testSteps.add("==========ENTER RESERVATION DETAILS CREATED FROM <b>YELLOW BOOK ICON</b>==========");

			String getRoomNumberAndClass = reservation.getRoomNumberWithClass(driver);
			String expectedRoomNumberAndClass = roomClassName + " : " + getRoomNumber + " (BLOCK)";
			testSteps.add("Expected room class, room number and block: " + expectedRoomNumberAndClass);
			testSteps.add("Found: " + getRoomNumberAndClass);
			Assert.assertEquals(getRoomNumberAndClass, expectedRoomNumberAndClass,
					"Failed: Reservation is not unassigned");
			testSteps.add("Verified room class and room number");

			String foundRoomCharges = reservation.getRoomChargesInTripSummaryBeforeCreateReservation(driver);
			foundRoomCharges = folio.splitString(foundRoomCharges);
			String temprayAmount = baseAmount;
			temprayAmount = temprayAmount + ".00";
			testSteps.add("Exected room charges: " + temprayAmount);
			testSteps.add("Found: " + foundRoomCharges);
			assertEquals(foundRoomCharges, temprayAmount, "Failed: Room charges are mismatching!");
			testSteps.add("Verified room charges");

			String foundTaxAndsServices = reservation.getTaxandServicesInTripSummaryBeforeCreateReservation(driver);
			testSteps.add("Tax and services: " + foundTaxAndsServices);

			getTestSteps.clear();
			getTestSteps = reservation.verifyAssociatedAccount_ResHeader(driver, accountName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.VerifyGuestProfileCheckox(driver, Boolean.parseBoolean(isChecked));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.enter_GuestName(driver, getTestSteps, saluation, yelloFirstName, yellowLastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.enterPaymentDetails(driver, getTestSteps, paymentType, cardNumber, cardName, cardExpDate);
			testSteps.addAll(getTestSteps);

			reservation.selectReferral(driver, groupReferral);

			reservation.clickBookNow(driver, getTestSteps);
			reservationNumber = reservation.get_ReservationConfirmationNumber(driver, getTestSteps);
			String ReservationStatus = reservation.get_ReservationStatus(driver, getTestSteps);
			Assert.assertEquals(ReservationStatus, "Reserved", "Failed: Reservation Status missmatched");
			reservation.clickCloseReservationSavePopup(driver, getTestSteps);
			getTestSteps.clear();
			getTestSteps = reservation.verifyAssociatedAccount(driver, accountName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.clickOnCheckInButton(driver, false, true);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create a new new reservation from group", testName, "Reservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create a new new reservation from group", testName, "Reservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			testSteps.add("==========VERIFICATION OF RESERVATION IN HISTORY==========");

			getTestSteps.clear();
			getTestSteps = reservation.ClickOnHistory(driver);
			testSteps.addAll(getTestSteps);

			String getHistoryCategory = reservation.getHistoryCategory(driver, 0);
			testSteps.add("Expected category: " + HistoryCategory);
			testSteps.add("Found: " + getHistoryCategory);
			assertEquals(getHistoryCategory, HistoryCategory, "Failed: History category is mismatching!");
			testSteps.add("Verified category");

			String getHistoryDate = reservation.gettHistoryDate(driver, 0);
			String getDate = ESTTimeZone.DateFormateForLineItem(0);
			testSteps.add("Expected date: " + getDate);
			testSteps.add("Found: " + getHistoryDate);
			assertTrue(getDate.contains(getHistoryDate), "Failed: History date is mismatching!");
			testSteps.add("Verified date");

			String gettHistoryDescription = reservation.getHistoryDescription(driver, 0);
			String description = "Created reservation with Confirmation Number: " + reservationNumber;
			testSteps.add("Expected description: " + description);
			testSteps.add("Found: " + gettHistoryDescription);
			assertEquals(gettHistoryDescription, description, "Failed: History description is mismatching!");
			testSteps.add("Verified description");

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify incidental in hostory", testName, "Incidental", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify incidental in hostory", testName, "Incidental", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			folio.FolioTab(driver);
			Utility.app_logs.info("Click Folio Tab");
			testSteps.add("Click Folio Tab");

			tax = folio.getTax(driver, roomCharge);

			testSteps.add("Room Charges Tax : " + tax);
			app_logs.info("Room Charges Tax : " + tax);

			getTestSteps.clear();
			getTestSteps = folio.FolioExist(driver, guestFolio, accountName, true);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Verify Folio Options", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Verify Folio Options", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			app_logs.info("==========VERIFY LINE ITEM DETAIL IN GUEST FOLIO==========");
			testSteps.add("==========VERIFY LINE ITEM DETAIL IN GUEST FOLIO==========");

			getTestSteps.clear();
			getTestSteps = folio.changeFolioOption(driver, guestFolio);
			testSteps.addAll(getTestSteps);

			folio.VerifyLineItems_State(driver, roomCharge, pendingState, 1);
			testSteps.add("Verify line itme in pending state after added");

			getTestSteps.clear();
			getTestSteps = folio.LineItemDate(driver, roomCharge, 0, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemCategory(driver, roomCharge, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.getDescroption(driver, roomCharge, lineItemDescription, false, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemQuentity(driver, roomCharge, "1", 1);
			testSteps.addAll(getTestSteps);

			String getTax = folio.getTax(driver, roomCharge);
			String totalAmountWithTax = folio.AddValue(baseAmount, getTax);
			totalAmountWithTax = "$ " + totalAmountWithTax;

			String getAmount = folio.getAmount(driver, roomCharge, 1);
			getAmount = "$ " + getAmount;
			testSteps.add("Expected amount after added tax: " + totalAmountWithTax);
			testSteps.add("Found : " + getAmount);
			assertEquals(totalAmountWithTax, totalAmountWithTax, "Failed: Amount is mismatching!");
			testSteps.add("Verified amount after included tax");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Verify Folio Options", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Verify Folio Options", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========VERIFY LINE ITEM DETAIL IN ACCOUNT FOLIO==========");
			testSteps.add("==========VERIFY LINE ITEM DETAIL IN ACCOUNT FOLIO==========");

			getTestSteps.clear();
			getTestSteps = folio.changeFolioOption(driver, accountName);
			testSteps.addAll(getTestSteps);

			int rowsSize = folio.getLineItemRows(driver);
			app_logs.info("BeforeRowsSize: " + rowsSize);
			assertEquals(rowsSize, 0, "Failed: Line item is showing after selected account folio");
			testSteps.add("Verified no lien item is displaying in folio line item after selected " + accountName);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify folio", testName, "FolioVerification", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify folio", testName, "FolioVerification", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Start verify item in item details pop up
		String getAmount = "";
		try {
			app_logs.info("==========VERIFY 'ITEM DETAIL' POPUP LINE ITEMS==========");
			testSteps.add("==========VERIFY 'ITEM DETAIL' POPUP LINE ITEMS==========");

			getTestSteps.clear();
			getTestSteps = folio.changeFolioOption(driver, guestFolio);
			testSteps.addAll(getTestSteps);

			getTestSteps = folio.getDescroption(driver, roomCharge, lineItemDescription, true);
			testSteps.add("Click Line Item Description : " + lineItemDescription);
			app_logs.info("Click Line Item Description : " + lineItemDescription);
			folio.ItemDetails_Category_State(driver, roomCharge, postedState, 1);
			testSteps.add("Verify line itme in pending state");
			app_logs.info("Verify line itme in pending state");

			getTestSteps.clear();
			getTestSteps = folio.DateItemDetails(driver, roomCharge, 1, itemRow);

			getTestSteps.clear();
			getTestSteps = folio.ItemDetailsCategory(driver, roomCharge, 1, itemRow);

			getTestSteps.clear();
			getTestSteps = folio.itemDetails_Descroption(driver, roomCharge, lineItemDescription, false, 1, itemRow,
					spanTag);

			testSteps.add("Successfully Verified Line item exist");
			app_logs.info("Successfully Verified Line item exist");
			getAmount = folio.getAmount_ItemDetails(driver, roomCharge, 1);
			testSteps.add("Expected amount: $ " + baseAmount + ".00");
			app_logs.info("Expected amount: $ " + baseAmount + ".00");
			testSteps.add("Found: " + getAmount);
			app_logs.info("Found: " + getAmount);
			assertEquals(getAmount, "$ " + baseAmount + ".00",
					"Failed: manual override rate value is mismatching in item row");

			testSteps.add("Line Item 'Date : " + Utility.getCurrentDate("E, dd-MMM-yyyy") + "', 'Category : "
					+ roomCharge + "', Description : " + lineItemDescription + "' and Amount : " + baseAmount + "'");
			app_logs.info("Line Item 'Date : " + Utility.getCurrentDate("E, dd-MMM-yyyy") + "', 'Category : "
					+ roomCharge + "', Description : " + lineItemDescription + "' and Amount : " + baseAmount + "'");

			app_logs.info("==========VERIFY 'ITEM DETAIL' POPUP ROOM CHARGES==========");
			testSteps.add("==========VERIFY 'ITEM DETAIL' POPUP ROOM CHARGES==========");

			String itemDetails_RoomChares = folio.Itemdetails_RoomChares(driver);
			testSteps.add("Expected room chares: $ " + baseAmount + ".00");
			app_logs.info("Expected room chares: $ " + baseAmount + ".00");
			testSteps.add("Found: " + itemDetails_RoomChares);
			app_logs.info("Found: " + itemDetails_RoomChares);
			assertEquals(itemDetails_RoomChares, "$ " + baseAmount + ".00",
					"Failed: room charges are mismatching in item details popup");
			testSteps.add("Verified room charges in item detail popup");
			app_logs.info("Verified room charges in item detail popup");

			getTestSteps.clear();
			getTestSteps = folio.CancelPopupButton(driver, true, false);
			testSteps.addAll(getTestSteps);

			reservation.closeReservationTab(driver);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify item details popup", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify item details popup", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			app_logs.info("==========VERIFY RESERVATION IN TAPE CHART==========");
			testSteps.add("==========VERIFY RESERVATION IN TAPE CHART==========");

			navigation.navigateToTapeChartFromReservations(driver);
			testSteps.add("Navigate TapeChart");
			app_logs.info("Navigate TapeChart");

			Tapechart tapechart = new Tapechart();
			String groupName = groupFirstName + "" + groupLastName;
			String fullName = groupName + " : " + yelloFirstName + " " + yellowLastName;
			app_logs.info(fullName);
			getTestSteps.clear();
			getTestSteps = tapechart.verifyReservationExist(driver, getRoomNumber, roomClassName, roomClassAbbreviation,
					fullName);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify reservation in tape chart", testName, "TapeChart", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify reservation in tape chart", testName, "TapeChart", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// Navigate to Groups
		try {
			navigation.secondaryGroupsManu(driver);
			app_logs.info("Navigate Groups");
			testSteps.add("Navigate Groups");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Navigate", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Navigate", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========SEARCH CREATED GROUP ACCOUNT==========");
			testSteps.add("==========SEARCH CREATED GROUP ACCOUNT==========");
			getTestSteps.clear();
			getTestSteps = group.searchGroupAccount(driver, accountName, accountNumber, true, true);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Search Account ", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Search Account ", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// navigate Group reservation tab
		try {
			app_logs.info("==========VERIFY RESERVATION CREATED GROUP==========");
			testSteps.add("==========VERIFY RESERVATION CREATED GROUP==========");
			getTestSteps.clear();
			advanceGroup.clickOnGroupsReservationTab(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = group.verifyReservationCount(driver, 1);
			testSteps.addAll(getTestSteps);

			String text = group.getReservationDetails(driver, reservationNumber, 1);
			assertEquals(text, yelloFirstName + " " + yellowLastName, "Failed: Guest name Missmatched");
			text = group.getReservationDetails(driver, reservationNumber, 2);
			assertEquals(text, blockName, "Failed: Block name Missmatched");
			text = group.getReservationDetails(driver, reservationNumber, 3);
			assertEquals(text, adults, "Failed: Adults Missmatched");
			text = group.getReservationDetails(driver, reservationNumber, 4);
			assertEquals(text, child, "Failed: Children Missmatched");
			text = group.getReservationDetails(driver, reservationNumber, 5);
			assertEquals(text, "Reserved", "Failed: Res Status Missmatched");
			text = group.getReservationDetails(driver, reservationNumber, 6);

			assertEquals(text, roomClassAbbreviation + " : " + getRoomNumber, "Failed: Room Class Name Missmatched");
			text = group.getReservationDetails(driver, reservationNumber, 7);
			assertEquals(text, Utility.getCurrentDate("MMM d, yyyy"), "Failed: Arrival Date Missmatched");
			text = group.getReservationDetails(driver, reservationNumber, 8);
			assertEquals(text, Utility.GetNextDate(1, "MMM d, yyyy"), "Failed: Departure Date Missmatched");
			text = group.getReservationDetails(driver, reservationNumber, 9);
			assertEquals(text, roomPerNight, "Failed: Nights Missmatched");

			testSteps.add("Successfully Verified Reservation Exist");
			app_logs.info("Successfully Verified Line item is added");

			testSteps.add("'Reservation Number : " + reservationNumber + "', 'Guest Name : " + yelloFirstName + " "
					+ yellowLastName + "', 'BlockName : " + blockName + "', 'Room Class : " + roomClassName + "'");

			testSteps.add("'Adults : " + adults + "', 'Children : " + child + "', 'Arrival Date : "
					+ Utility.getCurrentDate("MMM d, yyyy") + "', 'Departure Date : "
					+ Utility.GetNextDate(1, "MMM d, yyyy") + "'");
			app_logs.info("'Adults : " + adults + "', 'Children : " + child + "', 'Arrival Date : "
					+ Utility.getCurrentDate("MMM d, yyyy") + "', 'Departure Date : "
					+ Utility.GetNextDate(1, "MMM d, yyyy") + "'");

			group.ClickReservationName_VerifyPopup(driver, reservationNumber, testSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify item details popup", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify item details popup", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// here to verify block verification

		try {
			app_logs.info("==========VERIFY BLOCK DETAILS AFTER CREATED RESERVATION==========");
			testSteps.add("==========VERIFY BLOCK DETAILS AFTER CREATED RESERVATION==========");

			getTestSteps.clear();
			getTestSteps = group.navigateRoomBlock(driver);
			testSteps.addAll(getTestSteps);

			String todayDate = ESTTimeZone.DateFormateForLineItem(0);
			todayDate = Utility.parseDate(todayDate, "MM/dd/yyyy", "MMM d, yyyy");
			String getRelaseDate = group.getReleaseDateInRoomBlockDetatil(driver);
			testSteps.add("Expected realse date: " + todayDate);
			testSteps.add("Found: " + getRelaseDate);
			assertEquals(getRelaseDate, todayDate, "Failed: Realse date is mismatching");

			String totalRoomNight = group.getTotalRoomNightsInRoomBlockDetail(driver);
			testSteps.add("Expecetd total room nights: " + roomPerNight);
			testSteps.add("Found: " + totalRoomNight);
			assertEquals(totalRoomNight, roomPerNight, "Failed: per night is mismathching");
			testSteps.add("Verified room nights");

			expectedRevenue = folio.AddValue(baseAmount, tax);
			String expectedRevenueDetail = group.getExpectedRevenueInRoomBlockDetail(driver);
			testSteps.add("Expected revenue: $" + expectedRevenue);
			testSteps.add("Found: $" + expectedRevenueDetail);
			assertEquals(expectedRevenueDetail, expectedRevenue, "Faied: Expected revenue is mismatching");
			testSteps.add("Verified expected revenue");

			String getPickedupRevenue = group.getPickedupRevenueInRoomBlockDetail(driver);
			testSteps.add("Pickedup revenue: $" + expectedRevenue);
			testSteps.add("Found: $" + getPickedupRevenue);
			assertEquals(getPickedupRevenue, expectedRevenue, "Faied: Pickedup revenue is mismatching");
			testSteps.add("Verified pickedup revenue");

			String afterExpectedRevenue = group.getExpectedRevenueInGroupInfo(driver);
			Utility.app_logs.info("Expected revenue in block info: " + getRelaseDate);
			testSteps.add("Expected revenue  : " + getRelaseDate);
			testSteps.add("Found: $" + afterExpectedRevenue);
			assertEquals(afterExpectedRevenue, expectedRevenue, "Failed Expected revenue is mismatching in block info");

			String pickUpPercentage = group.getPickUpPercentageInRoomBlockDetatil(driver);
			testSteps.add("Expected pickup percentage: 100");
			testSteps.add("Found: " + pickUpPercentage);
			assertEquals(pickUpPercentage, "100", "Failed Pickup Percentage missmatched");
			testSteps.add("Verified pickup percentage");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify room block details", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify room block details", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			app_logs.info("==========ROOMING LIST DETAILS AFTER CREATED NEW RESERVATION==========");
			testSteps.add("==========ROOMING LIST DETAILS AFTER CREATED NEW RESERVATION==========");

			String afterPickupValue = group.getPickUpValue(driver, roomClassName);
			testSteps.add("Expected pickup: 1");
			testSteps.add("Found: " + afterPickupValue);
			assertEquals(afterPickupValue, "1", "Failed: Pickup value is mismatching");
			testSteps.add("Verified pickup value");

			String afterAvailableRoom = group.getAvailableRooms(driver, roomClassName);
			roomQuantity = folio.MinseValue(roomQuantity, "1");
			testSteps.add("Expected available rooms: " + roomQuantity);
			testSteps.add("Found : " + afterAvailableRoom);
			assertEquals(afterAvailableRoom, roomQuantity, "Failed: Available rooms is mismatching");
			testSteps.add("Verified available rooms");

			String bookClass = group.getBookIconClass(driver, roomClassName);
			assertEquals(bookClass, yellowBookClass, "Failed Room book Icon is still blue");
			testSteps.add("Verified room book icon color changed from blue to yellow");

			String blockedCount = group.getBlocked(driver, roomClassName);
			testSteps.add("Expected block count: " + roomBlockCount);
			testSteps.add("Found: " + blockedCount);
			assertEquals(blockedCount, roomBlockCount, "Failed Room Blocked Not Matched");
			testSteps.add("Verified block count");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify rooming list", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify rooming list", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {

			app_logs.info("==========SEARCH RESERVATION==========");
			testSteps.add("==========SEARCH RESERVATION==========");
			navigation.navigateToReservations(driver);
			ReservationSearch reservationSearch = new ReservationSearch();
			reservationSearch.multipleSearchReservationNumber(driver, reservationNumber);
			testSteps.add("Search reservation using reservation number");

			getTestSteps.clear();
			getTestSteps = reservation.clickOnGuestNameInSearchReaservation(driver, reservationNumber);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to search reservation", testName, "Reservations", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to search reservation", testName, "Reservations", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			app_logs.info("==========CHECKIN RESERVATION==========");
			testSteps.add("==========CHECKIN RESERVATION==========");
			getTestSteps.clear();
			getTestSteps = reservation.clickOnCheckInButton(driver, true, true);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.clickOnConfirmCheckInButton(driver);
			testSteps.addAll(getTestSteps);

			reservation.verifySpinerLoading(driver);

			reservation.switchTab(driver);

			String reservationStatus = reservation.reservationStatus(driver);
			assertEquals(reservationStatus, "IN-HOUSE", "Failed: after check in reservation status did not change");
			testSteps.add("Verified reservation status has been changed from Reserved to In-House after checkin");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to checkin reservation", testName, "Reservations", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to checkin reservation", testName, "Reservations", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			testSteps.add("==========VERIFICATION OF RESERVATION IN HISTORY AFTER CHECKED IN==========");

			getTestSteps.clear();
			getTestSteps = reservation.ClickOnHistory(driver);
			testSteps.addAll(getTestSteps);

			String getHistoryCategory = reservation.getHistoryCategory(driver, 0);
			testSteps.add("Expected category: " + HistoryCategory);
			testSteps.add("Found: " + getHistoryCategory);
			assertEquals(getHistoryCategory, HistoryCategory, "Failed: History category is mismatching!");
			testSteps.add("Verified category");

			String getHistoryDate = reservation.gettHistoryDate(driver, 0);
			String getDate = ESTTimeZone.DateFormateForLineItem(0);
			testSteps.add("Expected date: " + getDate);
			testSteps.add("Found: " + getHistoryDate);
			assertTrue(getDate.contains(getHistoryDate), "Failed: History date is mismatching!");
			testSteps.add("Verified date");

			String gettHistoryDescription = reservation.getHistoryDescription(driver, 0);
			String description = "Checked in this reservation";
			testSteps.add("Expected description: " + description);
			testSteps.add("Found: " + gettHistoryDescription);
			assertEquals(gettHistoryDescription, description, "Failed: History description is mismatching!");
			testSteps.add("Verified description");

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify reservation in hostory", testName, "History", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify reservation in hostory", testName, "History", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Navigate to Groups
		try {
			navigation.secondaryGroupsManu(driver);
			app_logs.info("Navigate Groups");
			testSteps.add("Navigate Groups");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Navigate", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Navigate", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========SEARCH CREATED GROUP ACCOUNT AFTER CHECKED IN==========");
			testSteps.add("==========SEARCH CREATED GROUP ACCOUNT AFTER CHECKED IN==========");
			getTestSteps.clear();
			getTestSteps = group.searchGroupAccount(driver, accountName, accountNumber, true, true);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Search Account ", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Search Account ", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// navigate Group reservation tab
		try {
			app_logs.info("==========VERIFY RESERVATION  CHECKED IN==========");
			testSteps.add("==========VERIFY RESERVATION CHECKED IN==========");
			getTestSteps.clear();
			advanceGroup.clickOnGroupsReservationTab(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = group.verifyReservationCount(driver, 1);
			testSteps.addAll(getTestSteps);

			String text = group.getReservationDetails(driver, reservationNumber, 1);
			assertEquals(text, yelloFirstName + " " + yellowLastName, "Failed: Guest name Missmatched");
			text = group.getReservationDetails(driver, reservationNumber, 2);
			assertEquals(text, blockName, "Failed: Block name Missmatched");
			text = group.getReservationDetails(driver, reservationNumber, 3);
			assertEquals(text, adults, "Failed: Adults Missmatched");
			text = group.getReservationDetails(driver, reservationNumber, 4);
			assertEquals(text, child, "Failed: Children Missmatched");
			text = group.getReservationDetails(driver, reservationNumber, 5);
			assertEquals(text, "In-House", "Failed: Res Status Missmatched");
			text = group.getReservationDetails(driver, reservationNumber, 6);
			assertEquals(text, roomClassAbbreviation + " : " + getRoomNumber, "Failed: Room Class Name Missmatched");
			text = group.getReservationDetails(driver, reservationNumber, 7);
			assertEquals(text, Utility.getCurrentDate("MMM d, yyyy"), "Failed: Arrival Date Missmatched");
			text = group.getReservationDetails(driver, reservationNumber, 8);
			assertEquals(text, Utility.GetNextDate(1, "MMM d, yyyy"), "Failed: Departure Date Missmatched");
			text = group.getReservationDetails(driver, reservationNumber, 9);
			assertEquals(text, roomPerNight, "Failed: Nights Missmatched");

			testSteps.add("Successfully Verified Reservation Exist");
			app_logs.info("Successfully Verified Line item is added");

			testSteps.add("'Reservation Number : " + reservationNumber + "', 'Guest Name : " + yelloFirstName + " "
					+ yellowLastName + "', 'BlockName : " + blockName + "', 'Room Class : " + roomClassName + "'");
			testSteps.add("Verified reservation status");
			app_logs.info("'Reservation Number : " + reservationNumber + "', 'Guest Name : " + yelloFirstName + " "
					+ yellowLastName + "', 'BlockName : " + blockName + "', 'Room Class : " + roomClassName + "'");

			testSteps.add("'Adults : " + adults + "', 'Children : " + child + "', 'Arrival Date : "
					+ Utility.getCurrentDate("MMM d, yyyy") + "', 'Departure Date : "
					+ Utility.GetNextDate(1, "MMM d, yyyy") + "'");
			app_logs.info("'Adults : " + adults + "', 'Children : " + child + "', 'Arrival Date : "
					+ Utility.getCurrentDate("MMM d, yyyy") + "', 'Departure Date : "
					+ Utility.GetNextDate(1, "MMM d, yyyy") + "'");

			group.ClickReservationName_VerifyPopup(driver, reservationNumber, testSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify item details popup", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify item details popup", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// here to verify block verification

		try {
			app_logs.info("==========VERIFY BLOCK DETAILS AFTER CHECKED IN RESERVATION==========");
			testSteps.add("==========VERIFY BLOCK DETAILS AFTER CHECKED IN RESERVATION==========");

			getTestSteps.clear();
			getTestSteps = group.navigateRoomBlock(driver);
			testSteps.addAll(getTestSteps);

			String todayDate = ESTTimeZone.DateFormateForLineItem(0);
			todayDate = Utility.parseDate(todayDate, "MM/dd/yyyy", "MMM d, yyyy");
			String getRelaseDate = group.getReleaseDateInRoomBlockDetatil(driver);
			testSteps.add("Expected realse date: " + todayDate);
			testSteps.add("Found: " + getRelaseDate);
			assertEquals(getRelaseDate, todayDate, "Failed: Realse date is mismatching");

			String totalRoomNight = group.getTotalRoomNightsInRoomBlockDetail(driver);
			testSteps.add("Expecetd total room nights: " + roomPerNight);
			testSteps.add("Found: " + totalRoomNight);
			assertEquals(totalRoomNight, roomPerNight, "Failed: per night is mismathching");
			testSteps.add("Verified room nights");

			// expectedRevenue = folio.AddValue(baseAmount, tax);
			String expectedRevenueDetail = group.getExpectedRevenueInRoomBlockDetail(driver);
			testSteps.add("Expected revenue: $" + expectedRevenue);
			testSteps.add("Found: $" + expectedRevenueDetail);
			assertEquals(expectedRevenueDetail, expectedRevenue, "Faied: Expected revenue is mismatching");
			testSteps.add("Verified expected revenue");

			String getPickedupRevenue = group.getPickedupRevenueInRoomBlockDetail(driver);
			testSteps.add("Pickedup revenue: $" + expectedRevenue);
			testSteps.add("Found: $" + getPickedupRevenue);
			assertEquals(getPickedupRevenue, expectedRevenue, "Faied: Pickedup revenue is mismatching");
			testSteps.add("Verified pickedup revenue");

			String afterExpectedRevenue = group.getExpectedRevenueInGroupInfo(driver);
			Utility.app_logs.info("Expected revenue in block info: " + getRelaseDate);
			testSteps.add("Expected revenue  : " + getRelaseDate);
			testSteps.add("Found: $" + afterExpectedRevenue);
			assertEquals(afterExpectedRevenue, expectedRevenue, "Failed Expected revenue is mismatching in block info");

			String pickUpPercentage = group.getPickUpPercentageInRoomBlockDetatil(driver);
			testSteps.add("Expected pickup percentage: 100");
			testSteps.add("Found: " + pickUpPercentage);
			assertEquals(pickUpPercentage, "100", "Failed Pickup Percentage missmatched");
			testSteps.add("Verified pickup percentage");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify room block details", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify room block details", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			app_logs.info("==========ROOMING LIST DETAILS AFTER CREATED NEW RESERVATION==========");
			testSteps.add("==========ROOMING LIST DETAILS AFTER CREATED NEW RESERVATION==========");

			String afterPickupValue = group.getPickUpValue(driver, roomClassName);
			testSteps.add("Expected pickup: 1");
			testSteps.add("Found: " + afterPickupValue);
			assertEquals(afterPickupValue, "1", "Failed: Pickup value is mismatching");
			testSteps.add("Verified pickup value");

			String afterAvailableRoom = group.getAvailableRooms(driver, roomClassName);
			testSteps.add("Expected available rooms: " + roomQuantity);
			testSteps.add("Found : " + afterAvailableRoom);
			assertEquals(afterAvailableRoom, roomQuantity, "Failed: Available rooms is mismatching");
			testSteps.add("Verified available rooms");

			String bookClass = group.getBookIconClass(driver, roomClassName);
			assertEquals(bookClass, yellowBookClass, "Failed Room book Icon is still blue");
			testSteps.add("Verified room book icon color changed from blue to yellow");

			String blockedCount = group.getBlocked(driver, roomClassName);
			testSteps.add("Expected block count: " + roomBlockCount);
			testSteps.add("Found: " + blockedCount);
			assertEquals(blockedCount, roomBlockCount, "Failed Room Blocked Not Matched");
			testSteps.add("Verified block count");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify rooming list", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify rooming list", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			navigation.navigateToReservations(driver);
			ReservationSearch reservationSearch = new ReservationSearch();
			reservationSearch.multipleSearchReservationNumber(driver, reservationNumber);
			testSteps.add("Search reservation using reservation number");

			getTestSteps.clear();
			getTestSteps = reservation.clickOnGuestNameInSearchReaservation(driver, reservationNumber);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to search reservation", testName, "Reservations", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to search reservation", testName, "Reservations", driver);
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
			navigation.secondaryRatesMenuItem(driver);
			app_logs.info("Navigate Rate");
			testSteps.add("Navigate Rate");

			rate.deleteRates(driver, tempraryRateName);
			testSteps.add("New Rate has been Deleted successfully");
			app_logs.info("New Rate has been Deleted successfully");

			rate.verifyDeleteRate(driver, tempraryRateName);
			testSteps.add("Verify the Deleted Rate : " + tempraryRateName);
			app_logs.info("Verify the Deleted Rate " + tempraryRateName);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Delete rate", testName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Delete rate", testName, "Rates", driver);
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

			boolean isRoomClassExist = roomClass.searchClass(driver, tempraryRoomClassName);
			app_logs.info("Search");
			if (isRoomClassExist) {

				getTestSteps.clear();
				getTestSteps = roomClass.deleteRoomClass(driver, tempraryRoomClassName);
				testSteps.addAll(getTestSteps);

			}

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete room class", testName, "RoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete room class", testName, "RoomClass", driver);
			}
		}
	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheet name provided
		return Utility.getData("CreateAssignedResFromBlueIcon", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}
}
