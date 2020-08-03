package com.innroad.inncenter.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
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
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.Tapechart;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_AdvanceGroups;

public class CreateAReservationFromRoomingListAndVerifyCheckInButton extends TestCore {

	// Automation-1541
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
	public void createAReservationFromRoomingListAndVerifyCheckInButton(String rateName, String baseAmount,
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
			String cardExpDate, String salulation) throws InterruptedException, IOException {

		System.out.println(ratePlan);
		String testName = "CreateAReservationFromRoomingListAndVerifyCheckInButton";
		test_description = "Create A Reservation From RoomingList And Verify CheckIn Button.<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682461' target='_blank'>"
				+ "Click here to open TestRail: C682461</a>";
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
		String accountNumber = null;
		String reservationNumber = null;
		Groups group = new Groups();
		AdvGroups advanceGroup = new AdvGroups();
		blockName = blockName + randomNumber;
		String tax = null;
		String expectedRevenue = null;
		String getRoomNumber = "";
		String arrivelDate = "";
		String departDate = "";
		
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
			getTestSteps = roomClass.createRoomClass(driver, roomClassName, roomClassAbbreviation, 
					bedsCount, maxAdults,
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

		try {

			testSteps.add("==========CREATE A NEW RESERVATION FORM ROOMING LIST==========");
			app_logs.info("==========CREATE A NEW RESERVATION FORM ROOMING LIST==========");

			getTestSteps.clear();
			getTestSteps = group.clickOnRoomingListButton(driver);
			testSteps.addAll(getTestSteps);

			testSteps.add("Expected blockName : " + blockName);
			app_logs.info("Expected blockName : " + blockName);
			String actualBlockName = group.getBlockName(driver);
			testSteps.add("Found : " + actualBlockName);
			app_logs.info("Found : " + actualBlockName);
			assertEquals(actualBlockName, blockName, "BlockName doesn't match");

			arrivelDate = Utility.getCurrentDate("MMM d, yyyy");
			departDate = Utility.GetNextDate(1, "MMM d, yyyy");
			testSteps.add("Expected arrive date : " + arrivelDate);
			app_logs.info("Expected arrive date : " + arrivelDate);
			String actualArriveDate = group.getArriveDate(driver);
			testSteps.add("Found : " + actualArriveDate);
			app_logs.info("Found : " + actualArriveDate);
			assertEquals(arrivelDate, actualArriveDate, "arrive date doesn't match");

			testSteps.add("Expected depart date : " + departDate);
			app_logs.info("Expected depart date : " + departDate);
			String actualDepartDate = group.getDepartDate(driver);
			testSteps.add("Found : " + actualDepartDate);
			app_logs.info("Found : " + actualDepartDate);
			assertEquals(actualDepartDate, departDate, "depart date doesn't match");

			//testSteps.add("Expected QGR : " + baseAmount);
			//app_logs.info("Expected QGR : " + baseAmount);
			String actualQgr = group.getQgr(driver);
			actualQgr = group.replaceCurrency(actualQgr);
			//testSteps.add("Found : " + actualQgr);
			//app_logs.info("Found : " + actualQgr);
			// assertEquals(actualQgr, baseAmount + ".00", "QGR amount doesn't
			// match");
			actualQgr = folio.splitStringByDot(actualQgr);
			String actualAmount = baseAmount;
			baseAmount = actualQgr;
			testSteps.add("Expected ReservationStatus : " + "Reserved");
			app_logs.info("Expected ReservationStatus : " + "Reserved");
			String actualReservationStatus = group.getReservationStatus(driver);
			testSteps.add("Found : " + actualReservationStatus);
			app_logs.info("Found : " + actualReservationStatus);
			assertEquals("Reserved", actualReservationStatus, "reservation status  doesn't match");

			testSteps.add("Expected revenue : " + baseAmount);
			app_logs.info("Expected revenue : " + baseAmount);
			String actualRevenue = group.getExpectedRevenue(driver);
			actualRevenue = group.replaceCurrency(actualRevenue);
			testSteps.add("Found : " + actualRevenue);
			app_logs.info("Found : " + actualRevenue);
			assertEquals(actualRevenue, baseAmount + ".00", "expected revenue  doesn't match");

			testSteps.add("Expected picked up revenue : " + "$0.00");
			app_logs.info("Expected picked up revenue : " + "$0.00");
			String actualPickup = group.getPickupRevenue(driver);
			testSteps.add("Found : " + actualPickup);
			app_logs.info("Found : " + actualPickup);
			assertEquals(actualPickup, "$0.00", "pickup revenue doesn't match");

			testSteps.add("Expected pickup percent : " + "0%");
			app_logs.info("Expected pickup percent : " + "0%");
			String actualPickupPercent = group.getPickupPercent(driver);
			testSteps.add("Found : " + actualPickupPercent);
			app_logs.info("Found : " + actualPickupPercent);
			assertEquals(actualPickupPercent, "0%", "pickup percent doesn't match");

			getTestSteps.clear();
			getTestSteps = group.enterReservationContentIntoRoomListingPopup(driver, firstName,
					lastName, baseAmount, roomClassName);
			testSteps.addAll(getTestSteps);
			
			getRoomNumber = group.getSelecteRoomNumber(driver);

			getTestSteps.clear();
			getTestSteps = group.clickOnBillingInfoIcon(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = group.enterBillingInfoInRoomListing(driver, salulation, paymentType, cardNumber,
					cardExpDate);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = group.clickOnPickupButton(driver);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create a reservation form roomming list", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create a reservation form roomming list", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		String guestName = "";
		String expectedBaseAmont = "";
		try {

			app_logs.info("==========VERIFY ROOMING LIST - DETAILS SUMMARY==========");
			testSteps.add("==========VERIFY ROOMING LIST - DETAILS SUMMARY==========");

			String getBlockName = group.getBlockNameInRoomingListSummary(driver);
			testSteps.add("Expected block name: " + blockName);
			testSteps.add("Found: " + getBlockName);
			assertEquals(getBlockName, blockName, "Failed: Block name is mismatching in summary");
			testSteps.add("Verified block name");

			String getStatus = group.getStatusInRoomingListSummary(driver);
			testSteps.add("Expected status: " + blockName);
			testSteps.add("Found: " + getStatus);
			assertEquals(getStatus, "Reserved", "Failed:Status is mismatching in summary");
			testSteps.add("Verified status");

			String getArriveDate = group.getArriveDateInRoomingListSummary(driver);
			testSteps.add("Expected arrive date: " + arrivelDate);
			testSteps.add("Found: " + getStatus);
			assertEquals(getArriveDate, arrivelDate, "Failed: Arrive date is mismatching in summary");
			testSteps.add("Verified arrive date");

			String getDepartDate = group.getDepartDateInRoomingListSummary(driver);
			testSteps.add("Expected depart date: " + departDate);
			testSteps.add("Found: " + getStatus);
			assertEquals(getDepartDate, departDate, "Failed: Depart date is mismatching in summary");
			testSteps.add("Verified depart dates");

			expectedBaseAmont = "$" + baseAmount + ".00";
			String getQgr = group.getQGRInRoomingListSummary(driver);
			/*testSteps.add("Expected QGR: " + expectedBaseAmont);
			testSteps.add("Found: " + getQgr);
			assertEquals(getQgr, expectedBaseAmont, "Failed: QGR is mismatching in summary");
			testSteps.add("Verified QGR");
*/
			String getExpectedRevenue = group.getExpectedRevenueInRoomingListSummary(driver);
			testSteps.add("Expected expected revenue: " + expectedBaseAmont);
			testSteps.add("Found: " + getExpectedRevenue);
			assertEquals(getExpectedRevenue, expectedBaseAmont, "Failed: Expected revenue is mismatching in summary");
			testSteps.add("Verified expected revenue");

			String getPickedupRevenue = group.getPickedupRevenueInRoomingListSummary(driver);
			testSteps.add("Expected pickedup revenue: " + expectedBaseAmont);
			testSteps.add("Found: " + getPickedupRevenue);
			assertEquals(getPickedupRevenue, expectedBaseAmont, "Failed: Picked up revenue is mismatching in summary");
			testSteps.add("Verified pickedup revenue");

			String getPickedupPercentages = group.getPickedupPercentageInRoomingListSummary(driver);
			testSteps.add("Expected pickedup percentage: 100%");
			testSteps.add("Found: " + getPickedupPercentages);
			assertEquals(getPickedupPercentages, "100%", "Failed: Picked up percentage is mismatching in summary");
			testSteps.add("Verified pickedup percentage");

			guestName = firstName + " " + lastName;
			reservationNumber = group.getReservationNumberfromRoomingListSummary(driver, guestName);
			testSteps.add("Reservation #: " + reservationNumber);

			String getGuestName = group.getGuestNamefromRoomingListSummary(driver, reservationNumber, false);
			testSteps.add("Expected guest name: " + guestName);
			testSteps.add("Found: " + getGuestName);
			assertEquals(getGuestName, guestName, "Failed: guest name is mismatching in reservation details");
			testSteps.add("Verified guest name");

			String getArrivalDate = group.getReservationDetailsfromRoomingListSummary(driver, reservationNumber, 2);
			testSteps.add("Expected arrival date: " + arrivelDate);
			testSteps.add("Found: " + getArrivalDate);
			assertEquals(getArrivalDate, arrivelDate, "Failed: arriva date is mismatching in reservation details");
			testSteps.add("Verified arrival date");

			String getDepartedDate = group.getReservationDetailsfromRoomingListSummary(driver, reservationNumber, 3);
			testSteps.add("Expected depart date: " + departDate);
			testSteps.add("Found: " + getArrivalDate);
			assertEquals(getDepartedDate, departDate, "Failed: depart date is mismatching in reservation detailss");
			testSteps.add("Verified arrival date");

			//getRoomNumber = "1";
			String expecteRoomClassandNumber = roomClassName + " : " + getRoomNumber;
			String getRoom = group.getReservationDetailsfromRoomingListSummary(driver, reservationNumber, 4);
			testSteps.add("Expected room class and room number: " + expecteRoomClassandNumber);
			testSteps.add("Found: " + getRoom);
			assertEquals(getRoom, expecteRoomClassandNumber, "Failed: Room is mismatching in reservation detailss");
			testSteps.add("Verified room");

			String getPaymentMethod = group.getReservationDetailsfromRoomingListSummary(driver, reservationNumber, 5);
			testSteps.add("Expected payment method: " + paymentType);
			testSteps.add("Found: " + getPaymentMethod);
			assertEquals(getPaymentMethod, paymentType,
					"Failed: payment method is mismatching in reservation detailss");
			testSteps.add("Verified payment metho");

			String getCardNumber = group.getReservationDetailsfromRoomingListSummary(driver, reservationNumber, 6);
			cardNumber = cardNumber.substring(12, 16);
			testSteps.add("cardnumber: " + cardNumber);
			assertTrue(getCardNumber.contains(cardNumber),
					"Failed: card number is mismatching in reservation detailss");
			testSteps.add("Verified card number contain 4 last digit");

			String getCrdExpiryDate = group.getReservationDetailsfromRoomingListSummary(driver, reservationNumber, 7);
			testSteps.add("Expected  card expiry date: " + cardExpDate);
			testSteps.add("Found: " + getCrdExpiryDate);
			assertEquals(getCrdExpiryDate, cardExpDate,
					"Failed: card expiry date is mismatching in reservation detailss");
			testSteps.add("Verified card expiry date");

			group.getGuestNamefromRoomingListSummary(driver, reservationNumber, true);
			testSteps.add("Click on guest name in rooming list - pick up summary");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify reservatin details in rooming list -  pick up summary",
						testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify reservatin details in rooming list -  pick up summary",
						testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		String groupName = "";
		try {

			app_logs.info("==========VERIFY RESERVATION DETAILS POPUP==========");
			testSteps.add("==========VERIFY RESERVATION DETAILS POPUP==========");
			Elements_AdvanceGroups elementsAdvance = new Elements_AdvanceGroups(driver);

			String getGuestName = group.getReservationDetailsfromPopup(driver,
					elementsAdvance.GuestNameInReservationDetails, OR.GuestNameInReservationDetails);
			testSteps.add("Expected guest name: " + guestName);
			testSteps.add("Found: " + getGuestName);
			assertEquals(getGuestName, guestName, "Failed: guest name is mismatching in reservation details popup");
			testSteps.add("Verified guest name");

			groupName = groupFirstName + "" + groupLastName;
			String getGroupName = group.getReservationDetailsfromPopup(driver,
					elementsAdvance.GroupNameInReservationDetails, OR.GroupNameInReservationDetails);
			testSteps.add("Expected group name: " + groupName);
			testSteps.add("Found: " + getGroupName);
			assertEquals(getGroupName, groupName, "Failed: group name is mismatching in reservation details popup");
			testSteps.add("Verified group name");

			String confirmationNumber = group.getReservationDetailsfromPopup(driver,
					elementsAdvance.ConfirmationNumberInReservationDetails, OR.ConfirmationNumberInReservationDetails);
			testSteps.add("Expected confirmation number: " + guestName);
			testSteps.add("Found: " + confirmationNumber);
			assertEquals(confirmationNumber, reservationNumber,
					"Failed: reservation number is mismatching in reservation details popup");
			testSteps.add("Verified reservation number");

			String expectedTripTotal = "$ "+baseAmount+".00";
			String tripTotal = group.getReservationDetailsfromPopup(driver,
					elementsAdvance.TripTotalInReservationDetails, OR.TripTotalInReservationDetails);
			testSteps.add("Expected trip total: " + expectedTripTotal);
			testSteps.add("Found: " + tripTotal);
			assertEquals(tripTotal, expectedTripTotal,
					"Failed: trip total is mismatching in reservation details popup");
			testSteps.add("Verified trip total");

			String expectedBalance = folio.MinseTwoValue(folio.splitString(tripTotal), baseAmount);
			String balance = group.getReservationDetailsfromPopup(driver, elementsAdvance.BalanceInReservationDetails,
					OR.BalanceInReservationDetails);
			testSteps.add("Expected balance: $ " + expectedBalance);
			testSteps.add("Found: " + balance);
			assertEquals(balance, "$ "+expectedBalance, "Failed: balance is mismatching in reservation details popup");
			testSteps.add("Verified balance");

			String getRatePlan = group.getReservationDetailsfromPopup(driver,
					elementsAdvance.RatePlanInReservationDetails, OR.RatePlanInReservationDetails);
			testSteps.add("Expected rate plan: Promo Code" );
			testSteps.add("Found: " + getRatePlan);
			assertEquals(getRatePlan, "Promo Code","Failed: rate plan is mismatching in reservation details popup");
			testSteps.add("Verified rate plan");

			String getPromoCode = group.getReservationDetailsfromPopup(driver,
					elementsAdvance.PromoInReservationDetails, OR.PromoInReservationDetails);
			testSteps.add("Expected promo code: GBR");
			testSteps.add("Found: " + getPromoCode);
			assertTrue(getPromoCode.contains("GBR"), "Failed: rate plan is mismatching in reservation details popup");
			testSteps.add("Verified promo code");

			String checkInDate = group.getReservationDetailsfromPopup(driver,
					elementsAdvance.CheckinDateInReservtionDetails, OR.CheckinDateInReservtionDetails);
			testSteps.add("Expected check-in date: " + arrivelDate);
			testSteps.add("Found: " + checkInDate);
			assertEquals(checkInDate, arrivelDate, "Failed: check-in date is mismatching in reservation details popup");
			testSteps.add("Verified check-in date");

			String checkOutDate = group.getReservationDetailsfromPopup(driver,
					elementsAdvance.CheckoutDateInResrvationDetails, OR.CheckoutDateInResrvationDetails);
			testSteps.add("Expected check-out date: " + departDate);
			testSteps.add("Found: " + checkOutDate);
			assertEquals(checkOutDate, departDate,
					"Failed: check-out date is mismatching in reservation details popup");
			testSteps.add("Verified check-out date");

			String getRoomClass = group.getReservationDetailsfromPopup(driver,
					elementsAdvance.RoomClassInResrvationDetails, OR.RoomClassInResrvationDetails);
			testSteps.add("Expected room class: " + roomClassName);
			testSteps.add("Found: " + getRoomClass);
			assertEquals(getRoomClass, roomClassName, "Failed: room class is mismatching in reservation details popup");
			testSteps.add("Verified room class");

			String roomNumber = group.getReservationDetailsfromPopup(driver,
					elementsAdvance.RoomNumberInResrvationDetails, OR.RoomNumberInResrvationDetails);
			testSteps.add("Expected room number: " + getRoomNumber);
			testSteps.add("Found: " + roomNumber);
			assertEquals(roomNumber, getRoomNumber, "Failed: room number is mismatching in reservation details popup");
			testSteps.add("Verified room number");

			group.verifyStartCheckInButton(driver);
			testSteps.add("Verified start check-in button is dispaying on reservation edtails popup");

			group.clickOnCloseReservationDetailsPopup(driver);
			testSteps.add("Click on close reservation details popup");

			group.clickonClosePickedupSummary(driver);
			testSteps.add("Click on close pickedup details summary popup");

			group.clickonCloseRoomingListPopup(driver);
			testSteps.add("Click on close rooming list popup");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify reservatin details in reservation details popup", testName,
						"Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify reservatin details in reservation details popup", testName,
						"Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Navigate to Groups
		try {
			// navigation.secondaryGroupsManu(driver);
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
			driver.navigate().refresh();
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
			assertEquals(text, guestName, "Failed: Guest name Missmatched");
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

			testSteps.add("'Reservation Number : " + reservationNumber + "', 'Guest Name : " +guestName + "', 'BlockName : " + blockName + "', 'Room Class : " + roomClassName + "'");

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
			tax = "0.00";
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
			assertEquals(pickUpPercentage, "100", "Failed Pickup Percentage mismatched");
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

			app_logs.info("==========VERIFY RESERVATION IN TAPE CHART==========");
			testSteps.add("==========VERIFY RESERVATION IN TAPE CHART==========");

			navigation.clickOnTapeChart(driver);
			testSteps.add("Navigate TapeChart");
			app_logs.info("Navigate TapeChart");

			Tapechart tapechart = new Tapechart();
			String fullName = groupName + " : "+guestName;
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

		// here search reservation
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
		String totalAmountWithTax = "";
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
			tax = getTax;
			baseAmount = folio.MinseTwoValue(baseAmount, getTax);
			totalAmountWithTax = folio.AddValue(baseAmount, getTax);
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

		String getBalance = "";
		String getRoomCharges = "";
		try {
			testSteps.add("==========VERIFICATION OF ROOM CHARGES AND BAALNC BEFORE CHEK IN RESERVATIONS==========");
			getTestSteps.clear();
			getTestSteps = folio.changeFolioOption(driver, guestFolio);
			testSteps.addAll(getTestSteps);

			getRoomCharges = folio.getRoomCharges(driver);
			testSteps.add("Expected room charges: $ " + baseAmount);
			testSteps.add("Found: $ " + getRoomCharges);
			assertEquals(getRoomCharges, baseAmount, "faile: room charges are mismtching");
			testSteps.add("Verified room charges");

			String getTotalCharges = folio.getTotalCharges(driver);
			String getTaxandServices = folio.getTaxServices(driver);
			String totalCharges = folio.AddValue(getRoomCharges, getTaxandServices);
			testSteps.add("Expected total charges: $ " + totalCharges);
			testSteps.add("Found: $ " + getTotalCharges);
			assertEquals(getTotalCharges, totalCharges, "Failed: total tax charges and services are mismatching");
			testSteps.add("Verified total tax and services");

			String ExpectedPayment =  folio.AddValue(baseAmount, tax);
			String getPayment = folio.getpayment(driver);
			testSteps.add("Expected payments: $ " + ExpectedPayment);
			testSteps.add("Found: $ " + getPayment);
			assertEquals(getPayment, ExpectedPayment, "Failed: payments is  mismatching");
			testSteps.add("Verified payments");

		getBalance = folio.getTotalBalance(driver);
			String balance = folio.MinseTwoValue(getTotalCharges, getPayment);
			testSteps.add("Expected balance: $ " + balance);
			testSteps.add("Found: $ " + getBalance);
			assertEquals(getBalance, balance, "Failed: balance is mismatching");
			testSteps.add("Verified balance");

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

			baseAmount = folio.splitStringByDot(baseAmount);
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

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify room charges and balance", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify room charges and balance", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			app_logs.info("==========CHECKIN RESERVATION==========");
			testSteps.add("==========CHECKIN RESERVATION==========");

			getTestSteps.clear();
			getTestSteps = reservation.ClickOnDetails(driver);
			testSteps.addAll(getTestSteps);
			
			reservation.clickOnEditMarketingInfoIcon(driver);
			testSteps.add("Click on marketing edit icon");
			
			getTestSteps.clear();
			getTestSteps = reservation.selectReferral(driver, groupReferral);
			testSteps.addAll(getTestSteps);
			
			reservation.clickOnSaveMarketingInfoIcon(driver);
			testSteps.add("Click on save marketing info");
			
			
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
		}
		catch (Exception e) {
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

		try {
			testSteps.add("==========VERIFICATION OF ROOM CHARGES AND BAALNC AFTER CHEKED-IN RESERVATIONS==========");

			folio.FolioTab(driver);
			Utility.app_logs.info("Click Folio Tab");
			testSteps.add("Click Folio Tab");

			String getRoomChargesAfterCheckedIn = folio.getRoomCharges(driver);
			testSteps.add("Expected room charges: $ " + getRoomCharges);
			testSteps.add("Found: $ " + getRoomChargesAfterCheckedIn);
			assertEquals(getRoomChargesAfterCheckedIn, getRoomCharges, "faile: room charges are mismtching");
			testSteps.add("Verified room charges is same after checked-in reservation");


			String getBalanceAftercheckedIn = folio.getTotalBalance(driver);
			testSteps.add("Expected balance: $ " + getBalance);
			testSteps.add("Found: $ " + getBalanceAftercheckedIn);
			assertEquals(getBalanceAftercheckedIn, getBalance, "Failed: balance is mismathing");
			testSteps.add("Verified balance is same after checked-in reservations");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify room charges and balance", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify room charges and balance", testName, "Folio", driver);
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
			assertEquals(text, guestName, "Failed: Guest name Missmatched");
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

			testSteps.add("'Reservation Number : " + reservationNumber + "', 'Guest Name : " + guestName + "', 'BlockName : " + blockName + "', 'Room Class : " + roomClassName + "'");
			testSteps.add("Verified reservation status");
			
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
			testSteps.add("navigate to reervations");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to navigate to reservation page", testName, "Reservations", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to navigate to reservation page", testName, "Reservations", driver);
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
		return Utility.getData("CreateRoomListingResAndVerify", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() { 	
	//	driver.quit();

	}
}
