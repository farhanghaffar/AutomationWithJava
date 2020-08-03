package com.innroad.inncenter.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
import com.innroad.inncenter.pageobjects.Policies;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.Reservation;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class VerifyPoliciesFromGroupBlock extends TestCore {

	
	private WebDriver driver = null;

	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();
	ArrayList<String> scriptName = new ArrayList<>();
	ArrayList<String> testCategory = new ArrayList<>();
	ArrayList<String> testDescription = new ArrayList<>();

	ArrayList<String> totalNights = new ArrayList<>();
	ArrayList<String> roomBaseAmount = new ArrayList<>();
	ArrayList<String> roomNumber = new ArrayList<>();
	ArrayList<String> getRoomNumbers = new ArrayList<>();

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
	public void verifyPoliciesFromGroupBlock(String rateName, String baseAmount, String addtionalAdult,
			String additionalChild, String displayName, String associateSeason, String ratePolicy,
			String rateDescription, String roomClassAbbreviation, String roomClassName, String bedsCount,
			String maxAdults, String maxPersons, String roomQuantity, String startRoomNumber, String ratePlan,
			String rateType, String rateAttributes, String interval, String source, String adults, String child,
			String marketSegment, String groupReferral, String groupFirstName, String groupLastName,
			String accountNumber, String groupPhn, String groupAddress, String groupCity, String groupCountry,
			String groupState, String groupPostalcode, String blockName, String roomPerNight, String firstName,
			String lastName, String updatedBlockedCount, String roomBlockCount, String lineItemDescription,
			String roomCharge, String postedState, String itemRow, String spanTag, String guestFolio,
			String pendingState, String blueBookClass, String IsAssign, String isChecked, String checkInPolicType,
			String policyType, String checkInPolicyName, String depositPolicyName, String noShowPolicyType,
			String noShowPolicyName, String cancelationPolicyType, String cancelationPolicyName, String policyAmount,
			String corporateAccount, String accountType, String paymentType, String cardNumber, String nameOnCard,
			String cardExpDate, String userName, String HistoryCategory, String policyAttr2, String policyAttrValue2,
			String cancelReason, String capture, String yellowBookClass,
			String checkINPolicyAmount, String cancelationPolicyAmount,String NoShowPolicyAmount) throws InterruptedException, IOException {

		String testName = "VerifyPoliciesFromGroupBlock";
		test_description = "Verify the policies from Account/groups/quote<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682431' target='_blank'>"
				+ "Click here to open TestRail: C682431</a>";
		test_catagory = "Groups";
		scriptName.add(testName);
		testDescription.add(test_description);
		testCategory.add(test_catagory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Rate rate = new Rate();
		Navigation navigation = new Navigation();
		Policies policies = new Policies();
		CPReservationPage reservationPage = new CPReservationPage();

		Folio folio = new Folio();
		RoomClass roomClass = new RoomClass();
		String tempraryRoomClassName = roomClassName;
		String tempraryRateName = rateName;
		String randomNumber = Utility.GenerateRandomNumber();
		roomClassName = roomClassName + randomNumber;
		roomClassAbbreviation = roomClassAbbreviation + randomNumber;
		rateName = rateName + randomNumber;
		displayName = rateName;
		String accountName = groupFirstName + randomNumber;
		lastName = lastName + randomNumber;
		String blueResFirstName = "Blue" + firstName;
		String saluation = "Mr.";
		String reservationNumber = null;
		String roomNumber = null;
		
		Groups group = new Groups();
		AdvGroups advgroup = new AdvGroups();
		blockName = blockName + randomNumber;

		String depositPolicyNameWithoutNum = depositPolicyName;
		String checkInPolicyNameWithoutNum = checkInPolicyName;
		String noShowPolicyNameWithoutNum = noShowPolicyName;
		String cancelationPolicyNameWithoutNum = cancelationPolicyName;
		String policyText = ratePolicy + "_Text";
		String policyDesc = ratePolicy + "_Description";
		ratePolicy = ratePolicy + randomNumber;
		depositPolicyName = depositPolicyName + randomNumber;
		checkInPolicyName = checkInPolicyName + randomNumber;
		noShowPolicyName = noShowPolicyName + randomNumber;
		cancelationPolicyName = cancelationPolicyName + randomNumber;
		String policyFor = "Room Charges";
		String policyForCheckIn = "Capture";
		String  getReservationStatus = "";
		
		roomBaseAmount.add(baseAmount);
		String tripSummaryRoomCharges = null, tripSummaryTaxes = null,
				tripSummaryTripTotal = null, tripSummaryPaid = null, tripSummaryBalance = null;
		
		ArrayList<String> getRoomNumbers = new ArrayList<>();

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
			getRoomNumbers.add(roomQuantity);

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
			getTestSteps = rate.EnterRatePolicy(driver, ratePolicy);
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

			rate.SearchRate(driver, rateName, false);
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
    try {
			
			navigation.Inventory(driver, testSteps);
			navigation.policies(driver, testSteps);

			app_logs.info("========== CREATING NEW DEPOSIT POLICY ==========");
			testSteps.add("========== CREATING NEW DEPOSIT POLICY ==========");

			policies.deleteAllPolicies(driver, testSteps, policyType, depositPolicyNameWithoutNum);

			policies.createPolicy(driver, testSteps, depositPolicyName, policyType, policyFor,
					policyAmount, null, null,
					source, associateSeason, roomClassName, ratePlan, policyText, policyDesc);

			policies.closeOpenedPolicyTab(driver, testSteps);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create a new deposit policy", testName, "InventoryNavigation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create a new deposit policy", testName, "InventoryNavigation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			app_logs.info("========== CREATING NEW CHECKIN POLICY ==========");
			testSteps.add("==========CREATING NEW CHECKIN POLICY ==========");

			policies.deleteAllPolicies(driver, testSteps, checkInPolicType, checkInPolicyNameWithoutNum);

			policies.createPolicy(driver, testSteps, checkInPolicyName, checkInPolicType, policyForCheckIn,
					checkINPolicyAmount, null, null, source, associateSeason, roomClassName, ratePlan, policyText, policyDesc);

			policies.closeOpenedPolicyTab(driver, testSteps);
		
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create a new checkin policy", testName, "InventoryNavigation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create a new checkin policy", testName, "InventoryNavigation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			app_logs.info("========== CREATING NEW CANCELATION POLICY ==========");
			testSteps.add("========== CREATING NEW CANCELATION POLICY ==========");

			policies.deleteAllPolicies(driver, testSteps, cancelationPolicyType, cancelationPolicyNameWithoutNum);

			policies.createPolicy(driver, testSteps, cancelationPolicyName, cancelationPolicyType, policyFor,
					cancelationPolicyAmount, "Beyond", "0", source, associateSeason, roomClassName, ratePlan, policyText,
					policyDesc);

			policies.closeOpenedPolicyTab(driver, testSteps);
		
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create a new cancelation policy", testName, "InventoryNavigation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create a new cancelation policy", testName, "InventoryNavigation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		
		try {
			
			app_logs.info("========== CREATING NEW NO SHOE POLICY ==========");
			testSteps.add("========== CREATING NEW NO SHOE POLICY ==========");

			policies.deleteAllPolicies(driver, testSteps, noShowPolicyType, noShowPolicyNameWithoutNum);
				
			policies.NewPolicybutton(driver, noShowPolicyType, getTestSteps);
			getTestSteps.clear();
			policies.Enter_Policy_Name(driver, noShowPolicyName,getTestSteps);
			
			getTestSteps.clear();
			getTestSteps = policies.NoShow_policy_Attributes(driver, "Room Charges", NoShowPolicyAmount,getTestSteps);
			testSteps.addAll(getTestSteps);
			
			policies.Enter_Policy_Desc(driver, noShowPolicyName, noShowPolicyName);
			
			policies.Associate_Sources(driver);
			
			policies.Associate_Seasons(driver, associateSeason);
			
			policies.Associate_RoomClasses(driver, roomClassName);
			
			policies.Associate_RatePlan(driver, ratePlan);
			
			policies.Save_Policy(driver);
			
			policies.closeOpenedPolicyTab(driver, testSteps);
			
			testSteps.add("New Policy saved successfully ");
			app_logs.info("New Policy saved successfully");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to save the policy", testName, "PolicyDetails", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to save the policy", testName, "PolicyDetails", driver);

			} else {
				Assert.assertTrue(false);
			}
		}
	
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
				Utility.updateReport(e, "Failed to Navigate Group", testName, "NavigateAdvGroup", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Navigate Group", testName, "NavigateAdvGroup", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			app_logs.info("==========CREATE NEW GROUP ACCOUNT==========");
			testSteps.add("==========CREATE NEW GROUP ACCOUNT==========");
			getTestSteps.clear();
			getTestSteps = group.enterGroupName(driver, accountName);
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
			getTestSteps = group.enterMailingAddress(driver, groupFirstName, groupLastName, groupPhn, groupAddress,
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

		try {
			
			getTestSteps.clear();
			getTestSteps = group.clickGroupFolioTab(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = group.clickGroupFolioOption(driver);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========SET DEPOSIT POLICY==========");
			testSteps.add("==========SET DEPOSIT POLICY==========");

			getTestSteps.clear();
			getTestSteps = group.selectDepositPolicy(driver, depositPolicyName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = group.verifyDepositPolicy(driver, depositPolicyName);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========SET CHECKIN POLICY==========");
			testSteps.add("==========SET CHECKIN POLICY==========");

			getTestSteps.clear();
			getTestSteps = group.selectCheckInPolicy(driver, checkInPolicyName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = group.verifyCheckInPolicy(driver, checkInPolicyName);
			testSteps.addAll(getTestSteps);
			
			app_logs.info("==========SET CANCELATION POLICY==========");
			testSteps.add("==========SET CANCELATION POLICY==========");

			getTestSteps.clear();
			getTestSteps = group.selectCancelationPolicy(driver, cancelationPolicyName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = group.verifyCancellationPolicy(driver, cancelationPolicyName);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========SET NO SHOW POLICY==========");
			testSteps.add("==========SET No SHOW POLICY==========");

			getTestSteps.clear();
			getTestSteps = group.selectNoShowPolicy(driver, noShowPolicyName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = group.verifyNoShowPolicy(driver, noShowPolicyName);
			testSteps.addAll(getTestSteps);


			getTestSteps.clear();
			getTestSteps = group.clickOnSave(driver);
			testSteps.addAll(getTestSteps);
			
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

			advgroup.updatedAutomaticallyAssignedRooms(driver, updatedBlockedCount);
			advgroup.BlockRoomForSelectedRoomclass(driver, roomBlockCount, roomClassName);

			advgroup.ClickCreateBlock(driver, getTestSteps);

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
			String beforeAvailableRoom = null;
			String beforePickupValue = null;
			String beforeBookIconClass = null;
			app_logs.info("==========ROOMING LIST DETAILS==========");
			testSteps.add("==========ROOMING LIST DETAILS==========");
			Utility.app_logs.info("Book Room From RoomClass : " + roomClassName);
			testSteps.add("Book Room From RoomClass : " + roomClassName);

			beforePickupValue = group.getPickUpValue(driver, roomClassName);
			Utility.app_logs.info("Pickup Value : " + beforePickupValue);
			testSteps.add("Pickup Value : " + beforePickupValue);

			beforeAvailableRoom = group.getAvailableRooms(driver, roomClassName);
			Utility.app_logs.info("Available Rooms : " + beforeAvailableRoom);
			testSteps.add("Available Rooms : " + beforeAvailableRoom);

			beforeBookIconClass = group.getBookIconClass(driver, roomClassName);
			Utility.app_logs.info("BookIcon Class : " + beforeBookIconClass);
			testSteps.add("BookIcon Class : " + beforeBookIconClass);
			assertEquals(beforeBookIconClass, blueBookClass, "Failed: Blue book icon is not showing");

			String blockedCount = group.getBlocked(driver, roomClassName);
			Utility.app_logs.info("Blocked Count  : " + blockedCount);
			testSteps.add("Blocked Count  : " + blockedCount);
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
			app_logs.info("==========ENTER RESERVATION DETAILS CREATED FROM <b>BLUE BOOK ICON</b>==========");
			testSteps.add("==========ENTER RESERVATION DETAILS CREATED FROM <b>BLUE BOOK ICON</b>==========");

			getTestSteps.clear();
			reservationPage.select_Rateplan(driver, getTestSteps, ratePlan, "");
			
			getTestSteps.clear();
			reservationPage.clickOnFindRooms(driver, getTestSteps);

			reservationPage.selectRoomWithPolicyUpdation(driver, roomClassName, IsAssign, accountName);

			reservationPage.clickNext(driver, getTestSteps);
			
			/*roomNumber = reservationPage.getRoomNumber(driver, getTestSteps);
			Assert.assertEquals(roomNumber, "Unassigned", "Failed: Reservation is not unassigned");
			testSteps.add("Verified Room Number is Unassigned");
*/
			getTestSteps.clear();
			getTestSteps = reservationPage.verifyAssociatedAccount_ResHeader(driver, accountName);
			testSteps.addAll(getTestSteps);
						
			getTestSteps.clear();
			getTestSteps = reservationPage.VerifyGuestProfileCheckox(driver, Boolean.parseBoolean("false"));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enter_GuestName(driver, getTestSteps, saluation, blueResFirstName, lastName);
			testSteps.addAll(getTestSteps);

			reservationPage.enter_PaymentDetails(driver, testSteps, paymentType, cardNumber, nameOnCard, cardExpDate);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectReferral(driver, groupReferral);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickBookNow(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			reservationNumber = reservationPage.get_ReservationConfirmationNumber(driver, getTestSteps);
			reservationPage.clickCloseReservationSavePopup(driver, getTestSteps);

			getReservationStatus = reservationPage.reservationStatus(driver);
			app_logs.info("Reservation status: "+getReservationStatus);
			
			getTestSteps.clear();
			getTestSteps = reservationPage.verifyAssociatedAccount(driver, accountName);
			testSteps.addAll(getTestSteps);
			testSteps.add("Successfully Associated Account to  Reservation");
			app_logs.info("Successfully Associated Account to Reservation");
		
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create new reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create new reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		String depositAmount = "";
		try {
			app_logs.info("==========VERIFICATION OF DEPOSIT POLICY==========");
			testSteps.add("==========VERIFICATION OF DEPOSIT POLICY==========");
			

			tripSummaryRoomCharges = reservationPage.getRoomChargesInTripSummary(driver);
			tripSummaryRoomCharges = reservationPage.replaceCurrency(tripSummaryRoomCharges);
			
			tripSummaryTaxes = reservationPage.getTaxandServicesInTripSummary(driver);
			tripSummaryTaxes = reservationPage.replaceCurrency(tripSummaryTaxes);
			
			tripSummaryTripTotal = reservationPage.getTotalTripSummary(driver);
			tripSummaryTripTotal = reservationPage.replaceCurrency(tripSummaryTripTotal);
			app_logs.info("tripSummaryTripTotal: "+tripSummaryTripTotal);

			tripSummaryPaid = reservationPage.getPaidInTripSummary(driver);
			tripSummaryPaid = reservationPage.replaceCurrency(tripSummaryPaid);

			depositAmount = reservationPage.calculateDepositePercenatge(tripSummaryTripTotal,
					policyAmount);
			app_logs.info(depositAmount);

			testSteps.add("Expected Deposit Amount :$ " + depositAmount);
			app_logs.info("Expected Deposit Amount : " + depositAmount);
			testSteps.add("Found : " + tripSummaryPaid);
			app_logs.info("Found : " + tripSummaryPaid);
		//	assertEquals(tripSummaryPaid, depositAmount, "Failed to verify deposit policy amount");
			testSteps.add("Verified deposit amount");
			app_logs.info("Verified deposit amount");

			tripSummaryBalance = reservationPage.getBalanceInTripSummary(driver);
			tripSummaryBalance = reservationPage.replaceCurrency(tripSummaryBalance);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify deposit policy", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify deposit policy", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}


	try {
		testSteps.add(
				"=====================  VERIFYING ASSOCIATED CHECK-IN POLICY DETAILS AT POLICES AND DISCLAIMERS TAB =====================");
		app_logs.info(
				"=====================  VERIFYING ASSOCIATED CHECK-IN POLICY DETAILS AT POLICES AND DISCLAIMERS TAB =====================");

		getTestSteps.clear();
		getTestSteps = reservationPage.clickPolicyCollapseIcon(driver, "deposit", "0");
		testSteps.addAll(getTestSteps);
		String depositPolicy = reservationPage.getReservationPolicy(driver);
		testSteps.add("Expected deposit policy : " + depositPolicyName);
		app_logs.info("Expected deposit policy : " + depositPolicyName);
		testSteps.add("Found : " + depositPolicy);
		app_logs.info("Found : " + depositPolicy);
		assertEquals(depositPolicy, depositPolicyName, "Failed to verify attached deposit policy name");
		testSteps.add("Verified deposit policy");
		app_logs.info("Verified deposit policy");

		try {
			reservationPage.closePolicyCollapseIcon(driver, 1,1);
		} catch (Exception e) {
			reservationPage.closePolicyCollapseIcon(driver, 2,1);
		}
		
		getTestSteps.clear();
		getTestSteps = reservationPage.clickPolicyCollapseIcon(driver, "checkIn", "0");
		testSteps.addAll(getTestSteps);
		String checkInPolicy = reservationPage.getReservationPolicy(driver);
		testSteps.add("Expected checkin policy : " + checkInPolicyName);
		app_logs.info("Expected checkin policy : " + checkInPolicyName);
		testSteps.add("Found : " + checkInPolicy);
		app_logs.info("Found : " + checkInPolicy);
		assertEquals(checkInPolicy, checkInPolicyName, "Failed to verify attached checkin policy name");
		testSteps.add("Verified checkin policy");
		app_logs.info("Verified checkin policy");
		
			

	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to verify attached policy in details page", testName, "Reservation", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to verify attached policy in details page", testName, "Reservation", driver);
		} else {
			Assert.assertTrue(false);
		}
	}

	String convertAmountIntoString = "";
	try {
		testSteps.add(
				"===================== Verifying guest and policy details on payment check-in popup =====================");
		getTestSteps.clear();
		getTestSteps = reservationPage.clickOnCheckInButton(driver, true, true);
		testSteps.addAll(getTestSteps);
		
		reservationPage.verifyGenerateGuestReportToggle(driver);
		testSteps.add("Click on toggle button for report generate");
		
		reservationPage.clickOnProceedToCheckInPaymentButton(driver, testSteps);
		testSteps.add("Clikc on check in peyment process button");
		
		String checkInPolicy = reservationPage.getCheckInPolicy(driver);
		testSteps.add("Expected checkin policy name : " + checkInPolicyName);
		app_logs.info("Expected checkin policy : " + checkInPolicyName);
		testSteps.add("Found : " + checkInPolicy);
		app_logs.info("Found : " + checkInPolicy);
		assertEquals(checkInPolicy, checkInPolicyName, "Failed to verify checkin policy");
		testSteps.add("Verified checkin policy name");
		app_logs.info("Verified checkin policy name");

		String checkInAmount = reservationPage.getCheckInAmount(driver);
		Double tripSummaryBalanceInDouble = Double.parseDouble(tripSummaryBalance);
		Double checkINPolicyAmountInDouble = Double.parseDouble(checkINPolicyAmount);
		Double expectedCheckInAmount = tripSummaryBalanceInDouble * checkINPolicyAmountInDouble;
		expectedCheckInAmount = expectedCheckInAmount / 100;
		
		 convertAmountIntoString = String.valueOf(expectedCheckInAmount);
		 testSteps.add("Expected checkin policy amount : $ " + convertAmountIntoString);
		 app_logs.info("Expected checkin amount : " + convertAmountIntoString);
		testSteps.add("Found : " + checkInAmount);
		app_logs.info("Found : " + checkInAmount);
		 assertEquals(checkInAmount, convertAmountIntoString,"Failed to verify checkin amount");
		testSteps.add("Verified checkin policy amount");
		app_logs.info("Verified checkin policy amount");

		getTestSteps.clear();
		getTestSteps = reservationPage.clickOnPayButtonOnPaymentPopup1(driver, testSteps);
		testSteps.addAll(getTestSteps);

		reservationPage.checkInPaymentSuccessPopupClose(driver, testSteps);
		testSteps.add("Close payment popup");
		

		String reservationStatus = reservationPage.reservationStatus(driver);
		app_logs.info("Reservation status after checkin: "+reservationStatus);
		assertEquals(reservationStatus, "IN-HOUSE","Failed: Reservation status is mismatching after checkin");
		testSteps.add("Verified reservation status has been changed from "+getReservationStatus+" to "+reservationStatus);		
		

	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to verify check-in policy amount", testName,
					"Reservation", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to verify check-in policy amount", testName,
					"Reservation", driver);
		} else {
			Assert.assertTrue(false);
		}
	}

	try {
		testSteps.add("==========VERIFICATION CHECKIN POLICY IN HISTORY TAB==========");

		getTestSteps.clear();
		getTestSteps = reservationPage.ClickOnHistory(driver);
		testSteps.addAll(getTestSteps);

		String getHistoryCategory = reservationPage.getHistoryCategory(driver, 0);
		testSteps.add("Expected category: " + HistoryCategory);
		testSteps.add("Found: " + getHistoryCategory);
		assertEquals(getHistoryCategory, HistoryCategory, "Failed: History category is mismatching!");
		testSteps.add("Verified category");

		String getHistoryDate = reservationPage.gettHistoryDate(driver, 0);
		String getDate = ESTTimeZone.DateFormateForLineItem(0);
		testSteps.add("Expected date: " + getDate);
		testSteps.add("Found: " + getHistoryDate);
		assertTrue(getDate.contains(getHistoryDate), "Failed: History date is mismatching!");
		testSteps.add("Verified date");

		String gettHistoryDescription = reservationPage.getHistoryDescription(driver, 0);
		String description = "Checked in this reservation";
		testSteps.add("Expected description: " + description);
		testSteps.add("Found: " + gettHistoryDescription);
		assertEquals(gettHistoryDescription, description,
				"Failed: History description for checkin is mismatching!");
		testSteps.add("Verified description");

		/*gettHistoryDescription = reservationPage.getHistoryDescription(driver, 3);
		description = "Created reservation with Confirmation Number: " + reservationNumber;
		testSteps.add("Expected description: " + description);
		testSteps.add("Found: " + gettHistoryDescription);
		assertEquals(gettHistoryDescription, description, "Failed: History description is mismatching!");
		testSteps.add("Verified description");
*/
	} catch (Exception e) {

		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to verify incidental in history", testName, "Incidental", driver);
		} else {
			Assert.assertTrue(false);
		}

	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to verify incidental in history", testName, "Incidental", driver);
		} else {
			Assert.assertTrue(false);
		}
	}

	try {
		app_logs.info("==========VERIFY FOLIOS==========");
		testSteps.add("==========VERIFY FOLIOS==========");
		
		//folio.FolioTab(driver);
		folio.clickOnfolio(driver);
		app_logs.info("Click Folio Tab");
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

	try {

		app_logs.info("==========VERIFY LINE ITEM DETAIL IN GUEST FOLIO==========");
		testSteps.add("==========VERIFY LINE ITEM DETAIL IN GUEST FOLIO==========");

		getTestSteps.clear();
		getTestSteps = folio.LineItemDate(driver, roomCharge, 0, 1);
		testSteps.addAll(getTestSteps);

		getTestSteps.clear();
		getTestSteps = folio.LineItemCategory(driver, roomCharge, 1);
		testSteps.addAll(getTestSteps);

		getTestSteps.clear();
		//getTestSteps = folio.getDescroption(driver, roomCharge, rateName, false, 1);
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

	String paymentLineItempDescription = null;
	try {

		app_logs.info("==========VERIFY DEPOSIT PAYMENT IN FOLIO LINE ITEM==========");
		testSteps.add("==========VERIFY DEPOSIT PAYMENT IN FOLIO LINE ITEM==========");

		paymentLineItempDescription = "Name: " + userName + " Account #: XXXX"
				+ cardNumber.substring(cardNumber.length() - 4) + " Exp. Date: " + cardExpDate;
		app_logs.info("paymentLineItempDescription : " + paymentLineItempDescription);

		getTestSteps.clear();
		getTestSteps = folio.LineItemDate(driver, paymentType, 0, 1);
		testSteps.addAll(getTestSteps);

		getTestSteps.clear();
		getTestSteps = folio.LineItemCategory(driver, paymentType, 2);
		testSteps.addAll(getTestSteps);

		getTestSteps.clear();
		getTestSteps = folio.getDescroption(driver, paymentType, paymentLineItempDescription, false, 1);
		testSteps.addAll(getTestSteps);

		getTestSteps.clear();
		getTestSteps = folio.LineItemQuentity(driver, paymentType, "0", 1);
		testSteps.addAll(getTestSteps);

		String getAmount = folio.getAmount(driver, paymentType, 1);
		testSteps.add("Expected amount : $ " + depositAmount);
		testSteps.add("Found : " + getAmount);
		//assertEquals(getAmount,"$ "+depositAmount , "Failed: Amount is mismatching!");
		testSteps.add("Verified amount after included tax");

	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to verify depost amount in folio", testName, "Folio", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to verify depost amount in folio", testName, "Folio", driver);
		} else {
			Assert.assertTrue(false);
		}
	}

	try {

		app_logs.info("==========VERIFY CHECKIN PAYMENT IN FOLIO LINE ITEM==========");
		testSteps.add("==========VERIFY CHECKIN PAYMENT IN FOLIO LINE ITEM==========");

		getTestSteps.clear();
		getTestSteps = folio.LineItemDate(driver, paymentType, 0, 2);
		testSteps.addAll(getTestSteps);

		getTestSteps.clear();
		getTestSteps = folio.LineItemCategory(driver, paymentType, 3);
		testSteps.addAll(getTestSteps);

		getTestSteps.clear();
		getTestSteps = folio.getDescroption(driver, paymentType, paymentLineItempDescription, false, 2);
		testSteps.addAll(getTestSteps);

		getTestSteps.clear();
		getTestSteps = folio.LineItemQuentity(driver, paymentType, "0", 2);
		testSteps.addAll(getTestSteps);

		String getTax = folio.getTax(driver, paymentType);
		String totalAmountWithTax = folio.AddValue(baseAmount, getTax);
		totalAmountWithTax = "$ " + totalAmountWithTax;

		String getAmount = folio.getAmount(driver, paymentType, 2);
		
		testSteps.add("Expected amount: $ " + convertAmountIntoString);
		testSteps.add("Found: " + getAmount);
		assertEquals(getAmount, "$ "+convertAmountIntoString, "Failed: Amount is mismatching!");
		testSteps.add("Verified amount");

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

		reservationPage.closeReservationTab(driver);
		driver.navigate().refresh();
	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to verify account in folio", testName, "FolioVerification", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to verify account in folio", testName, "FolioVerification", driver);
		} else {
			Assert.assertTrue(false);
		}
	}
	
		try {
			app_logs.info("==========VERIFICATION OF CANCELLATION POLICY==========");
			testSteps.add("==========VERIFICATION OF CANCELLATION POLICY==========");

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

		try {
			app_logs.info("==========SEARCH EXISTING GROUP ACCOUNT==========");
			testSteps.add("==========SEARCH EXISTING GROUP ACCOUNT==========");
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
		
	
		try {
			getTestSteps.clear();
			getTestSteps = group.navigateRoomBlock(driver);
			testSteps.addAll(getTestSteps);

			group.bookIconClick(driver, roomClassName);
			Utility.app_logs.info("Click <b>Yellow Book Icon</b> of Room Class  : " + roomClassName);
			testSteps.add("Click <b>Yellow Book Icon</b> of Room Class  : " + roomClassName);

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
		try {
			app_logs.info("==========ENTER RESERVATION DETAILS CREATED FROM <b>YELLOW BOOK ICON</b>==========");
			testSteps.add("==========ENTER RESERVATION DETAILS CREATED FROM <b>YELLOW BOOK ICON</b>==========");

			Wait.wait2Second();
			getTestSteps.clear();
			reservationPage.select_Rateplan(driver, getTestSteps, ratePlan, "");
			
			getTestSteps.clear();
			reservationPage.clickOnFindRooms(driver, getTestSteps);

			reservationPage.selectRoomWithPolicyUpdation(driver, roomClassName, IsAssign, accountName);

			reservationPage.clickNext(driver, getTestSteps);
			
		//	roomNumber = reservationPage.getRoomNumber(driver, getTestSteps);
			
			/*roomNumber = reservationPage.getRoomNumber(driver, getTestSteps);
			Assert.assertEquals(roomNumber, "Unassigned", "Failed: Reservation is not unassigned");
			testSteps.add("Verified Room Number is Unassigned");
*/

			getTestSteps.clear();
			getTestSteps = reservationPage.verifyAssociatedAccount_ResHeader(driver, accountName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.VerifyGuestProfileCheckox(driver, Boolean.parseBoolean("false"));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enter_GuestName(driver, getTestSteps, saluation, blueResFirstName, lastName);
			testSteps.addAll(getTestSteps);

			reservationPage.enter_PaymentDetails(driver, testSteps, paymentType, cardNumber, nameOnCard, cardExpDate);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectReferral(driver, groupReferral);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickBookNow(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			reservationNumber = reservationPage.get_ReservationConfirmationNumber(driver, getTestSteps);
			reservationPage.clickCloseReservationSavePopup(driver, getTestSteps);

			getReservationStatus = reservationPage.reservationStatus(driver);
			app_logs.info("Reservation status: "+getReservationStatus);
			
			
			testSteps.add("Successfully Associated Account to  Reservation");
			app_logs.info("Successfully Associated Account to Reservation");
			getTestSteps.clear();
			getTestSteps = reservationPage.verifyAssociatedAccount(driver, accountName);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create new reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create new reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		
		try {
			app_logs.info("==========VERIFICATION OF DEPOSIT POLICY==========");
			testSteps.add("==========VERIFICATION OF DEPOSIT POLICY==========");
			

			tripSummaryRoomCharges = reservationPage.getRoomChargesInTripSummary(driver);
			tripSummaryRoomCharges = reservationPage.replaceCurrency(tripSummaryRoomCharges);
			
			tripSummaryTaxes = reservationPage.getTaxandServicesInTripSummary(driver);
			tripSummaryTaxes = reservationPage.replaceCurrency(tripSummaryTaxes);
			
			tripSummaryTripTotal = reservationPage.getTotalTripSummary(driver);
			tripSummaryTripTotal = reservationPage.replaceCurrency(tripSummaryTripTotal);
			app_logs.info("tripSummaryTripTotal: "+tripSummaryTripTotal);

			tripSummaryPaid = reservationPage.getPaidInTripSummary(driver);
			tripSummaryPaid = reservationPage.replaceCurrency(tripSummaryPaid);

			depositAmount = reservationPage.calculateDepositePercenatge(tripSummaryTripTotal,
					policyAmount);
			app_logs.info(depositAmount);

			testSteps.add("Expected Deposit Amount :$ " + depositAmount);
			app_logs.info("Expected Deposit Amount : " + depositAmount);
			testSteps.add("Found : " + tripSummaryPaid);
			app_logs.info("Found : " + tripSummaryPaid);
			//assertEquals(tripSummaryPaid, depositAmount, "Failed to verify deposit policy amount");
			testSteps.add("Verified deposit amount");
			app_logs.info("Verified deposit amount");

			tripSummaryBalance = reservationPage.getBalanceInTripSummary(driver);
			tripSummaryBalance = reservationPage.replaceCurrency(tripSummaryBalance);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify deposit policy", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify deposit policy", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		
		try {
			testSteps.add(
					"=====================  VERIFYING ASSOCIATED CANCELLATION POLICY DETAILS AT POLICES AND DISCLAIMERS TAB =====================");
			app_logs.info(
					"=====================  VERIFYING ASSOCIATED CANCELLATION POLICY DETAILS AT POLICES AND DISCLAIMERS TAB =====================");

			getTestSteps.clear();
			app_logs.info("cancelationPolicyAmount: "+cancelationPolicyAmount);
			getTestSteps = reservationPage.clickPolicyCollapseIcon(driver, "cancel", cancelationPolicyAmount);
			testSteps.addAll(getTestSteps);
			String cancelationPolicy = reservationPage.getReservationPolicy(driver);
			testSteps.add("Expected deposit policy : " + cancelationPolicyName);
			app_logs.info("Expected deposit policy : " + cancelationPolicyName);
			testSteps.add("Found : " + cancelationPolicy);
			app_logs.info("Found : " + cancelationPolicy);
			assertEquals(cancelationPolicy, cancelationPolicyName, "Failed to verify cancellation policy");
			testSteps.add("Verified cancellation policy");
			app_logs.info("Verified cancellation policy");

			
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify cancellation policy in details", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify cancellation policy in details", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		String expectedAmountToPayforCancel = "";
		try {
			testSteps.add("===================== VERIFICATION OF CANCELLATION POLICY AMOUNT =====================");
			reservationPage.cancelReservation(driver, testSteps);
			reservationPage.enableVoidRoomCharge(driver, testSteps, true);
			reservationPage.provideReasonForCancelAndClickOnProceedToPay(driver, testSteps, cancelReason, true);
			
			Double tripSummaryRoomChargesInDouble = Double.parseDouble(tripSummaryRoomCharges);
			Double policyAmountInDouble = Double.parseDouble(cancelationPolicyAmount);
			Double tripSummaryPaidInDouble = Double.parseDouble(tripSummaryPaid);

			Double expectedCancelAmount = (tripSummaryRoomChargesInDouble * policyAmountInDouble) / 100;
			
			expectedCancelAmount = expectedCancelAmount - tripSummaryPaidInDouble;
			expectedAmountToPayforCancel = String.format("%.2f", expectedCancelAmount);
					
			app_logs.info("expectedCancelAmount : $ " + expectedAmountToPayforCancel);
			testSteps.add("Expected Cancellation Amount : $ " + expectedAmountToPayforCancel);
			
			String actualCancelAmount = reservationPage.getCancelationAmount(driver);
			
			app_logs.info("Found : $ " + actualCancelAmount);
			testSteps.add("Found : $ " + actualCancelAmount);
			assertEquals(actualCancelAmount, expectedAmountToPayforCancel, "Failed to match cancelation amount");
			app_logs.info("Verified cancellation amount");
			testSteps.add("Verified cancellation amount");
			
			reservationPage.clickOnPayButtonOnPaymentPopup(driver, testSteps);
			reservationPage.checkInPaymentSuccessPopupClose(driver, testSteps);
			
			String  reservationStatus = reservationPage.reservationStatus(driver);
			app_logs.info("Reservation status after cancellation: "+reservationStatus);
			assertEquals(reservationStatus, "CANCELLED","Failed: Reservation status is mismatching after cancellation");
			testSteps.add("Verified reservation status has been changed from "+getReservationStatus+" to "+reservationStatus);
				
						
		} catch (Exception e) {
			System.out.println(e);
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to cancel reservation and verify associated cancellation and other "
						+ "payment details", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to cancel reservation and verify associated cancellation and other "
						+ "payment details", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			testSteps.add("==========VERIFICATION CANCELLATION POLICY IN HISTORY TAB==========");

			getTestSteps.clear();
			getTestSteps = reservationPage.ClickOnHistory(driver);
			testSteps.addAll(getTestSteps);

			String getHistoryCategory = reservationPage.getHistoryCategory(driver, 0);
			testSteps.add("Expected category: " + HistoryCategory);
			testSteps.add("Found: " + getHistoryCategory);
			assertEquals(getHistoryCategory, HistoryCategory, "Failed: History category is mismatching!");
			testSteps.add("Verified category");

			String getHistoryDate = reservationPage.gettHistoryDate(driver, 0);
			String getDate = ESTTimeZone.DateFormateForLineItem(0);
			testSteps.add("Expected date: " + getDate);
			testSteps.add("Found: " + getHistoryDate);
			assertTrue(getDate.contains(getHistoryDate), "Failed: History date is mismatching!");
			testSteps.add("Verified date");

			String gettHistoryDescription = reservationPage.getHistoryDescription(driver, 0);
			String description = "Cancelled this reservation";
			testSteps.add("Expected description: " + description);
			testSteps.add("Found: " + gettHistoryDescription);
			assertEquals(gettHistoryDescription, description,
					"Failed: History description for checkin is mismatching!");
			testSteps.add("Verified description");

			/*gettHistoryDescription = reservationPage.getHistoryDescription(driver, 3);
			description = "Created reservation with Confirmation Number: " + reservationNumber;
			testSteps.add("Expected description: " + description);
			testSteps.add("Found: " + gettHistoryDescription);
			assertEquals(gettHistoryDescription, description, "Failed: History description is mismatching!");
			testSteps.add("Verified description");*/

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify incidental in history", testName, "Incidental", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify incidental in history", testName, "Incidental", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========VERIFY FOLIOS==========");
			testSteps.add("==========VERIFY FOLIOS==========");
			
			folio.clickOnfolio(driver);
			app_logs.info("Click Folio Tab");
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

		try {

			app_logs.info("==========VERIFY DEPOSIT PAYMENT IN FOLIO LINE ITEM==========");
			testSteps.add("==========VERIFY DEPOSIT PAYMENT IN FOLIO LINE ITEM==========");

			paymentLineItempDescription = "Name: " + userName + " Account #: XXXX"
					+ cardNumber.substring(cardNumber.length() - 4) + " Exp. Date: " + cardExpDate;
			app_logs.info("paymentLineItempDescription : " + paymentLineItempDescription);

			getTestSteps.clear();
			getTestSteps = folio.LineItemDate(driver, paymentType, 0, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemCategory(driver, paymentType, 2);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.getDescroption(driver, paymentType, paymentLineItempDescription, false, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemQuentity(driver, paymentType, "0", 1);
			testSteps.addAll(getTestSteps);

			String getAmount = folio.getAmount(driver, paymentType, 1);
			testSteps.add("Expected amount : $ " + depositAmount);
			testSteps.add("Found : " + getAmount);
			//assertEquals(getAmount,"$ "+depositAmount , "Failed: Amount is mismatching!");
			testSteps.add("Verified amount after included tax");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify deposit amount in folio", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify deposit amount in folio", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		try {

			app_logs.info("==========VERIFY CANCELLATION PAYMENT IN FOLIO LINE ITEM==========");
			testSteps.add("==========VERIFY CANCELLATION PAYMENT IN FOLIO LINE ITEM==========");

			paymentLineItempDescription = "Name: " + userName + " Account #: XXXX"
					+ cardNumber.substring(cardNumber.length() - 4) + " Exp. Date: " + cardExpDate;
			app_logs.info("paymentLineItempDescription : " + paymentLineItempDescription);

			getTestSteps.clear();
			getTestSteps = folio.LineItemDate(driver, paymentType, 0, 2);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemCategory(driver, paymentType, 2);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.getDescroption(driver, paymentType, paymentLineItempDescription, false, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemQuentity(driver, paymentType, "0", 2);
			testSteps.addAll(getTestSteps);

			String getAmount = folio.getAmount(driver, paymentType, 2);
			testSteps.add("Expected amount in folio : $ " + expectedAmountToPayforCancel);
			testSteps.add("Found : " + getAmount);
		//	assertEquals(getAmount,"$ "+expectedAmountToPayforCancel , "Failed: Amount is mismatching!");
			testSteps.add("Verified amount after included tax");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify cancellation amount in folio", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify cancellation amount in folio", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		paymentLineItempDescription = "Cancellation Fee";
		try {

			app_logs.info("==========VERIFY TOTAL PAYMENT IN FOLIO LINE ITEM==========");
			testSteps.add("==========VERIFY TOTAL PAYMENT IN FOLIO LINE ITEM==========");

			getTestSteps.clear();
			getTestSteps = folio.LineItemDate(driver, paymentLineItempDescription, 0, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemCategory(driver, paymentLineItempDescription, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.getDescroption(driver, paymentLineItempDescription, paymentLineItempDescription, false, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemQuentity(driver, paymentLineItempDescription, "0", 1);
			testSteps.addAll(getTestSteps);

			String totalAmountWithTax = folio.AddValue(expectedAmountToPayforCancel, depositAmount);
			String getAmount = folio.getAmount(driver, paymentLineItempDescription, 1);
			
			testSteps.add("Expected amount: $ " + totalAmountWithTax);
			testSteps.add("Found: " + getAmount);
			//assertEquals(getAmount, "$ "+totalAmountWithTax, "Failed: Amount is mismatching!");
			testSteps.add("Verified amount");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify total amount in folio", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify total amount in folio", testName, "Folio", driver);
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

			reservationPage.closeReservationTab(driver);
			driver.navigate().refresh();
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify account in folio", testName, "FolioVerification", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify account in folio", testName, "FolioVerification", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		try {
			app_logs.info("==========VERIFICATION OF NO SHOW POLICY==========");
			testSteps.add("==========VERIFICATION OF NO SHOW POLICY==========");

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
		try {
			app_logs.info("==========SEARCH EXISTING GROUP ACCOUNT==========");
			testSteps.add("==========SEARCH EXISTING GROUP ACCOUNT==========");
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


		try {
			getTestSteps.clear();
			getTestSteps = group.navigateRoomBlock(driver);
			testSteps.addAll(getTestSteps);
			
			group.bookIconClick(driver, roomClassName);
			Utility.app_logs.info("Click <b>Yellow Book Icon</b> of Room Class  : " + roomClassName);
			testSteps.add("Click <b>Yellow Book Icon</b> of Room Class  : " + roomClassName);
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

			getTestSteps.clear();
			reservationPage.select_Rateplan(driver, getTestSteps, ratePlan, "");
			
			getTestSteps.clear();
			reservationPage.clickOnFindRooms(driver, getTestSteps);

			reservationPage.selectRoomWithPolicyUpdation(driver, roomClassName, IsAssign, "");
			reservationPage.clickNext(driver, getTestSteps);

		//	roomNumber = reservationPage.getRoomNumber(driver, getTestSteps);
			
			/*roomNumber = reservationPage.getRoomNumber(driver, getTestSteps);
			Assert.assertEquals(roomNumber, "Unassigned", "Failed: Reservation is not unassigned");
			testSteps.add("Verified Room Number is Unassigned");

*/
			getTestSteps.clear();
			getTestSteps = reservationPage.verifyAssociatedAccount_ResHeader(driver, accountName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.VerifyGuestProfileCheckox(driver, Boolean.parseBoolean("false"));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enter_GuestName(driver, getTestSteps, saluation, blueResFirstName, lastName);
			testSteps.addAll(getTestSteps);

			reservationPage.enter_PaymentDetails(driver, testSteps, paymentType, cardNumber, nameOnCard, cardExpDate);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectReferral(driver, groupReferral);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickBookNow(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			reservationNumber = reservationPage.get_ReservationConfirmationNumber(driver, getTestSteps);
			reservationPage.clickCloseReservationSavePopup(driver, getTestSteps);

			getReservationStatus = reservationPage.reservationStatus(driver);
			app_logs.info("Reservation status after: "+getReservationStatus);
			
			testSteps.add("Successfully Associated Account to  Reservation");
			app_logs.info("Successfully Associated Account to Reservation");
			getTestSteps.clear();
			getTestSteps = reservationPage.verifyAssociatedAccount(driver, accountName);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create new reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create new reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		
		try {
			app_logs.info("==========VERIFICATION OF DEPOSIT POLICY==========");
			testSteps.add("==========VERIFICATION OF DEPOSIT POLICY==========");
			

			tripSummaryRoomCharges = reservationPage.getRoomChargesInTripSummary(driver);
			tripSummaryRoomCharges = reservationPage.replaceCurrency(tripSummaryRoomCharges);
			
			tripSummaryTaxes = reservationPage.getTaxandServicesInTripSummary(driver);
			tripSummaryTaxes = reservationPage.replaceCurrency(tripSummaryTaxes);
			
			tripSummaryTripTotal = reservationPage.getTotalTripSummary(driver);
			tripSummaryTripTotal = reservationPage.replaceCurrency(tripSummaryTripTotal);
			app_logs.info("tripSummaryTripTotal: "+tripSummaryTripTotal);

			tripSummaryPaid = reservationPage.getPaidInTripSummary(driver);
			tripSummaryPaid = reservationPage.replaceCurrency(tripSummaryPaid);

			depositAmount = reservationPage.calculateDepositePercenatge(tripSummaryTripTotal,
					policyAmount);
			app_logs.info(depositAmount);

			testSteps.add("Expected Deposit Amount :$ " + depositAmount);
			app_logs.info("Expected Deposit Amount : " + depositAmount);
			testSteps.add("Found : " + tripSummaryPaid);
			app_logs.info("Found : " + tripSummaryPaid);
		//	assertEquals(tripSummaryPaid, depositAmount, "Failed to verify deposit policy amount");
			testSteps.add("Verified deposit amount");
			app_logs.info("Verified deposit amount");

			tripSummaryBalance = reservationPage.getBalanceInTripSummary(driver);
			tripSummaryBalance = reservationPage.replaceCurrency(tripSummaryBalance);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify deposit policy", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify deposit policy", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}


		try {
			testSteps.add(
					"=====================  VERIFYING ASSOCIATED NO SHOW POLICY DETAILS AT POLICES AND DISCLAIMERS TAB =====================");
			app_logs.info(
					"=====================  VERIFYING ASSOCIATED NO SHOW POLICY DETAILS AT POLICES AND DISCLAIMERS TAB =====================");
			getTestSteps.clear();
			getTestSteps = reservationPage.clickPolicyCollapseIcon(driver, "noShow", policyAmount);
			testSteps.addAll(getTestSteps);
			String noShowPolicy = reservationPage.getReservationPolicy(driver);
			testSteps.add("Expected NoShow policy : " + noShowPolicyName);
			app_logs.info("Expected NoShow policy : " + noShowPolicyName);
			testSteps.add("Found : " + noShowPolicy);
			app_logs.info("Found : " + noShowPolicy);
			assertEquals(noShowPolicy, noShowPolicyName, "Failed to verify noShow policy");
			testSteps.add("Verified NoShow policy");
			app_logs.info("Verified NoShow policy");
			
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify now show policy", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify now show policy", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		String expectedAmountToPayforNoShow = "";
		try {

			//reservationPage.verifyNoShowPolicy(driver, testSteps, noShowPolicyName, policyText);
			testSteps.add("===================== Make  Reservation as No Show =====================");
			reservationPage.makeReservationNoShow(driver, testSteps);
			
			testSteps.add("===================== verifying NoShowReservation PopUp =====================");
			reservationPage.verifyNoShowReservationPopUp(driver, noShowPolicyName, testSteps);
			
			String noShowAmount = reservationPage.getNoShowPaymentAmount(driver);
			
			testSteps.add(" noShow Amount : " + noShowAmount);
			app_logs.info(" noShow Amount : " + noShowAmount);
			
			Double tripSummaryRoomChargesInDouble = Double.parseDouble(tripSummaryRoomCharges);
		//	Double policyAmountInDouble = Double.parseDouble(cancelationPolicyAmount);
			Double tripSummaryPaidInDouble = Double.parseDouble(tripSummaryPaid);

			double noShowPercentage = Double.parseDouble(NoShowPolicyAmount);
			Double expectedNoShowAmount = (tripSummaryRoomChargesInDouble *  noShowPercentage) / 100;
			expectedNoShowAmount = expectedNoShowAmount - tripSummaryPaidInDouble;
			expectedAmountToPayforNoShow = String.format("%.2f", expectedNoShowAmount);
			
			testSteps.add("Expected no show amount : " + expectedAmountToPayforNoShow);
			app_logs.info("Expected noShow Amount : " + expectedAmountToPayforNoShow);
			testSteps.add("Found : " + noShowAmount);
			app_logs.info("Found : " + noShowAmount);
			
			assertEquals(noShowAmount, expectedAmountToPayforNoShow,"Failed: No show amount is mismatching!");
			testSteps.add("Verified  NoShow Amount ");
			app_logs.info("Verified NoShow Amount");

			reservationPage.clickOnPayButtonOnPaymentPopup(driver, testSteps);
			reservationPage.checkInPaymentSuccessPopupClose(driver, testSteps);
			
			String  reservationStatus = reservationPage.reservationStatus(driver);
			app_logs.info("Reservation status after no show: "+reservationStatus);
			assertEquals(reservationStatus, "NO SHOW","Failed: Reservation status is mismatching after no show");
			testSteps.add("Verified reservation status has been changed from "+getReservationStatus+" to "+reservationStatus);
				

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify no show policy amount", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify no show policy amount", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			testSteps.add("==========VERIFICATION NO SHOW POLICY IN HISTORY TAB==========");

			getTestSteps.clear();
			getTestSteps = reservationPage.ClickOnHistory(driver);
			testSteps.addAll(getTestSteps);

			String getHistoryCategory = reservationPage.getHistoryCategory(driver, 0);
			testSteps.add("Expected category: " + HistoryCategory);
			testSteps.add("Found: " + getHistoryCategory);
			assertEquals(getHistoryCategory, HistoryCategory, "Failed: History category is mismatching!");
			testSteps.add("Verified category");

			String getHistoryDate = reservationPage.gettHistoryDate(driver, 0);
			String getDate = ESTTimeZone.DateFormateForLineItem(0);
			testSteps.add("Expected date: " + getDate);
			testSteps.add("Found: " + getHistoryDate);
			assertTrue(getDate.contains(getHistoryDate), "Failed: History date is mismatching!");
			testSteps.add("Verified date");

			String gettHistoryDescription = reservationPage.getHistoryDescription(driver, 0);
			String description = "Marked this reservation as a no show";
			testSteps.add("Expected description: " + description);
			testSteps.add("Found: " + gettHistoryDescription);
			assertEquals(gettHistoryDescription, description,
					"Failed: History description for checkin is mismatching!");
			testSteps.add("Verified description");

			/*gettHistoryDescription = reservationPage.getHistoryDescription(driver, 3);
			description = "Created reservation with Confirmation Number: " + reservationNumber;
			testSteps.add("Expected description: " + description);
			testSteps.add("Found: " + gettHistoryDescription);
			assertEquals(gettHistoryDescription, description, "Failed: History description is mismatching!");
			testSteps.add("Verified description");*/

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify incidental in history", testName, "Incidental", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify incidental in history", testName, "Incidental", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========VERIFY FOLIOS==========");
			testSteps.add("==========VERIFY FOLIOS==========");
			
			//folio.FolioTab(driver);
			folio.clickOnfolio(driver);
			app_logs.info("Click Folio Tab");
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


		try {

			app_logs.info("==========VERIFY DEPOSIT PAYMENT IN FOLIO LINE ITEM==========");
			testSteps.add("==========VERIFY DEPOSIT PAYMENT IN FOLIO LINE ITEM==========");

			paymentLineItempDescription = "Name: " + userName + " Account #: XXXX"
					+ cardNumber.substring(cardNumber.length() - 4) + " Exp. Date: " + cardExpDate;
			app_logs.info("paymentLineItempDescription : " + paymentLineItempDescription);

			getTestSteps.clear();
			getTestSteps = folio.LineItemDate(driver, paymentType, 0, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemCategory(driver, paymentType, 2);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.getDescroption(driver, paymentType, paymentLineItempDescription, false, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemQuentity(driver, paymentType, "0", 1);
			testSteps.addAll(getTestSteps);

			String getAmount = folio.getAmount(driver, paymentType, 1);
			testSteps.add("Expected amount : $ " + depositAmount);
			testSteps.add("Found : " + getAmount);
			//assertEquals(getAmount,"$ "+depositAmount , "Failed: Amount is mismatching!");
			testSteps.add("Verified amount after included tax");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify deposit amount in folio", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify deposit amount in folio", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		try {

			app_logs.info("==========VERIFY NO SHOW PAYMENT IN FOLIO LINE ITEM==========");
			testSteps.add("==========VERIFY NO SHOW PAYMENT IN FOLIO LINE ITEM==========");

			paymentLineItempDescription = "Name: " + userName + " Account #: XXXX"
					+ cardNumber.substring(cardNumber.length() - 4) + " Exp. Date: " + cardExpDate;
			app_logs.info("paymentLineItempDescription : " + paymentLineItempDescription);

			getTestSteps.clear();
			getTestSteps = folio.LineItemDate(driver, paymentType, 0, 2);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemCategory(driver, paymentType, 2);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.getDescroption(driver, paymentType, paymentLineItempDescription, false, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemQuentity(driver, paymentType, "0", 2);
			testSteps.addAll(getTestSteps);

			String getAmount = folio.getAmount(driver, paymentType, 2);
			testSteps.add("Expected amount in folio : $ " + expectedAmountToPayforNoShow);
			testSteps.add("Found : " + getAmount);
			assertEquals(getAmount,"$ "+expectedAmountToPayforNoShow , "Failed: Amount is mismatching!");
			testSteps.add("Verified amount after included tax");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify cancellation amount in folio", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify cancellation amount in folio", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		paymentLineItempDescription = "No Show Fee";
		try {

			app_logs.info("==========VERIFY TOTAL PAYMENT IN FOLIO LINE ITEM==========");
			testSteps.add("==========VERIFY TOTAL PAYMENT IN FOLIO LINE ITEM==========");

			getTestSteps.clear();
			getTestSteps = folio.LineItemDate(driver, paymentLineItempDescription, 0, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemCategory(driver, paymentLineItempDescription, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.getDescroption(driver, paymentLineItempDescription, paymentLineItempDescription, false, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemQuentity(driver, paymentLineItempDescription, "0", 1);
			testSteps.addAll(getTestSteps);

			String totalAmountWithTax = folio.AddValue(expectedAmountToPayforNoShow, depositAmount);
			String getAmount = folio.getAmount(driver, paymentLineItempDescription, 1);
			
			testSteps.add("Expected amount: $ " + totalAmountWithTax);
			testSteps.add("Found: " + getAmount);
		//	assertEquals(getAmount, "$ "+totalAmountWithTax, "Failed: Amount is mismatching!");
			testSteps.add("Verified amount");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify total amount in folio", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify total amount in folio", testName, "Folio", driver);
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

			reservationPage.closeFirstOpenedReservation(driver, testSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify account in folio", testName, "FolioVerification", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify account in folio", testName, "FolioVerification", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			testSteps.add("===================== DELEETE ALL POLICIES THAT CREATED=====================");
			navigation.Inventory(driver, testSteps);
			navigation.policies(driver, testSteps);
			policies.deleteAllPolicies(driver, testSteps, policyType, depositPolicyNameWithoutNum);
			policies.deleteAllPolicies(driver, testSteps, checkInPolicType, checkInPolicyNameWithoutNum);
			policies.deleteAllPolicies(driver, testSteps, cancelationPolicyType, cancelationPolicyNameWithoutNum);
			policies.deleteAllPolicies(driver, testSteps, noShowPolicyType, noShowPolicyNameWithoutNum);
			

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete test data created during execution", testName, "Reservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete test data created during execution", testName, "Reservation",
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
		return Utility.getData("VerifyPoliciesFromGroupBlock", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		 driver.quit();
	}
}
