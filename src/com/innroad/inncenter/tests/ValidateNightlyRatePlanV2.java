package com.innroad.inncenter.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.Distribution;
import com.innroad.inncenter.pageobjects.Login;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.NightlyRate;
import com.innroad.inncenter.pageobjects.Policies;
import com.innroad.inncenter.pageobjects.RatesGrid;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class ValidateNightlyRatePlanV2 extends TestCore {
	private WebDriver driver = null;
	public static String test_name = "";
	public static String test_description = "";
	public static String test_catagory = "";
	ArrayList<String> test_steps = new ArrayList<>();
	// ArrayList<String> getTest_Steps = new ArrayList<>();

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData")
	public void validateNightlyRatePlanV2(String delim, String RatePlanName, String ExpectedValue,
			String RatePlanNameTextLength, String isExpectedReqError, String isCheckSameValueForFolioDisplayName,
			String NextButtonStateIsClickAble, String FolioDisplayName, String FolioDisplayExpectedValue,
			String FolioDisplayNameTextLength, String isExpectedReqErrorFolioDisplay,
			String NextButtonStateIsClickAbleAfterFolioDisplay, String Description, String ExpectedDescValue,
			String DescCharLength, String isVerifyAllRoomClasses, String isVerifyAllPolicies,
			String isVerifyDistributionChannel, String Channels, String RoomClasses, String isRatePlanRistrictionReq,
			String RistrictionType, String isMinStay, String MinNights, String isMaxStay, String MaxNights,
			String isMoreThanDaysReq, String MoreThanDaysCount, String isWithInDaysReq, String WithInDaysCount,
			String PromoCode, String isPolicesReq, String PoliciesType, String PoliciesName)
			throws InterruptedException, IOException {

		Utility.DELIM = delim;

		test_name = "ValidateNightlyRatePlanV2";
		test_description = "ValidateNightlyRatePlanV2 <br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/' target='_blank'>"
				+ "Click here to open TestRail: C</a>";
		test_catagory = "ValidateNightlyRatePlanV2";
		String testName = test_name;

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Navigation navigation = new Navigation();
		RatesGrid ratesGrid = new RatesGrid();
		NightlyRate nightlyRate = new NightlyRate();
		Distribution distribution = new Distribution();
		Policies policies = new Policies();
		RoomClass roomClass = new RoomClass();
		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);
			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver = getDriver();
			Login login = new Login();

			try {
				login.login(driver, envURL, "wpi", "autouser", "Auto@123");
			} catch (Exception e) {
				driver.switchTo().alert().accept();
				Actions actions = new Actions(driver);

				actions.sendKeys(Keys.ENTER);
				login.login(driver, envURL, "wpi", "autouser", "Auto@123");
			}
			test_steps.add("Logged into the application");
			app_logs.info("Logged into the application");
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		ArrayList<String> allActiveRoomClasses = new ArrayList<String>();
		try {
			if (Boolean.parseBoolean(isVerifyAllRoomClasses)) {
				test_steps.add("=================== GET ALL ACTIVE ROOM CLASSES ======================");
				app_logs.info("=================== GET ALL ACTIVE ROOM CLASSES ======================");
				navigation.mainSetupManu(driver);
				app_logs.info("Navigate Setup");
				test_steps.add("Navigate Setup");

				navigation.roomClass(driver);
				app_logs.info("Navigate Room Class");
				test_steps.add("Navigate Room Class");

				List<String>[] roomClassesArray = roomClass.getAllActiveRoomClasses(driver);
				allActiveRoomClasses = (ArrayList<String>) roomClassesArray[0];

				test_steps.add("Found Room Classes : " + allActiveRoomClasses.size());
				app_logs.info("Found Room Classes : " + allActiveRoomClasses.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		ArrayList<String> allActiveChannels = new ArrayList<String>();

		try {
			if (Boolean.parseBoolean(isVerifyDistributionChannel)) {
				test_steps.add("=================== GET ALL ACTIVE CHANNELS ======================");
				app_logs.info("=================== GET ALL ACTIVE CHANNELS ======================");
				navigation.Inventory(driver, test_steps);
				navigation.Distribution(driver);
				test_steps.add("Click on Distribution");
				app_logs.info("Click on Distribution");
				allActiveChannels = distribution.getAllActiveChannelDetails(driver);

				test_steps.add("Found Distribution Channels : " + allActiveChannels.size());
				app_logs.info("Found Distribution Channels : " + allActiveChannels.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		ArrayList<String> allCancelationPolicies = new ArrayList<String>();
		ArrayList<String> allDepositPolicies = new ArrayList<String>();
		ArrayList<String> allCheckInPolicies = new ArrayList<String>();
		ArrayList<String> allNoShowPolicies = new ArrayList<String>();
		try {
			if (Boolean.parseBoolean(isVerifyAllPolicies)) {
				test_steps.add("=================== GET ALL POLICIES ======================");
				app_logs.info("=================== GET ALL POLICIES ======================");
				navigation.Inventory(driver, test_steps);
				navigation.policies(driver, test_steps);
				allCancelationPolicies = policies.getCancelationPolicies(driver);
				test_steps.add("Found Cancelation Policies : " + allCancelationPolicies.size());
				app_logs.info("Found Cancelation Policies : " + allCancelationPolicies.size());
				allDepositPolicies = policies.getDepositPolicies(driver);
				test_steps.add("Found Deposit Policies : " + allDepositPolicies.size());
				app_logs.info("Found Deposit Policies : " + allDepositPolicies.size());
				allCheckInPolicies = policies.getCheckInPolicies(driver);
				test_steps.add("Found CheckIn Policies : " + allCheckInPolicies.size());
				app_logs.info("Found CheckIn Policies : " + allCheckInPolicies.size());
				allNoShowPolicies = policies.getNoShowPolicies(driver);
				test_steps.add("Found NoShow Policies : " + allNoShowPolicies.size());
				app_logs.info("Found NoShow Policies : " + allNoShowPolicies.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			// After login
			test_steps.add("=================== NAVIGATE TO RATE GRID ======================");
			app_logs.info("=================== NAVIGATE TO RATE GRID ======================");
			navigation.Inventory(driver, test_steps);
			navigation.RatesGrid(driver);
			test_steps.add("Navigated to RatesGrid");
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		String existingRateName = "";
		try {
			test_steps.add("=================== VALIDATE RATE PLAN V2 ======================");
			app_logs.info("=================== VALIDATE RATE PLAN V2  ======================");
			ratesGrid.clickRatePlanArrow(driver, test_steps);
			 ArrayList<String> ratePlanNames=ratesGrid.getRatePlanNames(driver);
			 existingRateName =ratePlanNames.get(0);
			ratesGrid.clickRateGridAddRatePlan(driver);
			ratesGrid.clickRateGridAddRatePlanOption(driver, "Nightly rate plan");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			test_steps.add("=================== RATE PLAN INITIAL STATE ======================");
			app_logs.info("=================== RATE PLAN INITIAL STATE ======================");

			nightlyRate.verifyTitleSummaryValue(driver, "Rate plan type", "Nightly rate plan", test_steps);
			nightlyRate.verifyNextButton(driver, false, true, test_steps);
			nightlyRate.verifyRatePlanNameVisibility(driver, true, true, test_steps);
			nightlyRate.verifyRatePlanFolioDisplayNameVisibility(driver, true, true, test_steps);
			nightlyRate.verifyRatePlanDescriptionVisibility(driver, true, true, test_steps);
			nightlyRate.verifyCharCountRatePlanDescription(driver, "0", test_steps);

			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			test_steps.add(
					"=================== VERIFY RATE PLAN NAME, FOLIO NAME, DESCRIPTION & NEXT BUTTON ======================");
			app_logs.info(
					"=================== VERIFY RATE PLAN NAME, FOLIO NAME, DESCRIPTION & NEXT BUTTON ======================");

			nightlyRate.verifyRatePlanRequiredFeild(driver, existingRateName, true, test_steps);
			
			nightlyRate.enterRatePlanName(driver, RatePlanName, test_steps);
			nightlyRate.verifyRatePlanNameFeildValue(driver, ExpectedValue, true, test_steps);

			int foundFeildLenght = nightlyRate.getRatePlanInputFeiledLength(driver, test_steps);
			assertEquals(foundFeildLenght, Integer.parseInt(RatePlanNameTextLength),
					"Failed To Verify Rate Plan Feild Length");
			test_steps.add("Successfully Verified Rate Plan Name Text Length : " + foundFeildLenght);
			app_logs.info("Successfully Verified Rate Plan Name Text Length : " + foundFeildLenght);
			if (Boolean.parseBoolean(isExpectedReqError)) {
				nightlyRate.verifyRatePlanRequiredFeild(driver, ExpectedValue,false, test_steps);
			}
			if (Boolean.parseBoolean(isCheckSameValueForFolioDisplayName)) {
				nightlyRate.verifyRatePlanFolioDisplayNameFeildValue(driver, ExpectedValue, true, test_steps);
				int foundFolioDisplayFeildLenght = nightlyRate.getRatePlanFolioDisplayNameInputFeiledLength(driver,
						test_steps);
				assertEquals(foundFolioDisplayFeildLenght, Integer.parseInt(RatePlanNameTextLength),
						"Failed To Verify Rate Plan Folio Display Name Feild Length");
				test_steps.add("Successfully Verified Rate Plan Folio Display Name Text Length : "
						+ foundFolioDisplayFeildLenght);
				app_logs.info("Successfully Verified Rate Plan Folio Display Name Text Length : "
						+ foundFolioDisplayFeildLenght);
			}

			nightlyRate.verifyNextButton(driver, Boolean.parseBoolean(NextButtonStateIsClickAble), true, test_steps);

		} catch (Exception e) {e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			nightlyRate.enterRateFolioDisplayName(driver, FolioDisplayName, test_steps);
			nightlyRate.verifyRatePlanFolioDisplayNameFeildValue(driver, FolioDisplayExpectedValue, true, test_steps);
			int foundFolioDisplayFeildLenght = nightlyRate.getRatePlanFolioDisplayNameInputFeiledLength(driver,
					test_steps);
			assertEquals(foundFolioDisplayFeildLenght, Integer.parseInt(FolioDisplayNameTextLength),
					"Failed To Verify Rate Plan Folio Display Name Feild Length");
			test_steps.add(
					"Successfully Verified Rate Plan Folio Display Name Text Length : " + foundFolioDisplayFeildLenght);
			app_logs.info(
					"Successfully Verified Rate Plan Folio Display Name Text Length : " + foundFolioDisplayFeildLenght);

			if (Boolean.parseBoolean(isExpectedReqErrorFolioDisplay)) {
				nightlyRate.verifyRatePlanFolioDisplayNameRequiredFeild(driver, FolioDisplayExpectedValue, test_steps);
			}

			nightlyRate.verifyNextButton(driver, Boolean.parseBoolean(NextButtonStateIsClickAbleAfterFolioDisplay),
					true, test_steps);
		} catch (Exception e) {e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			nightlyRate.enterRatePlanDescription(driver, Description, test_steps);
			nightlyRate.verifyRatePlanDescriptionFeildValue(driver, ExpectedDescValue, true, test_steps);
			int foundDescLength = nightlyRate.getRatePlanDescriptionInputFeiledLength(driver, test_steps);
			assertEquals(foundDescLength, Integer.parseInt(DescCharLength),
					"Failed To Verify Rate Plan Description Feild Length");
			test_steps.add("Successfully Verified Rate Plan Description Text Length : " + foundDescLength);
			app_logs.info("Successfully Verified Rate Plan Description Text Length : " + foundDescLength);
			nightlyRate.verifyCharCountRatePlanDescription(driver, DescCharLength, test_steps);

			nightlyRate.clickNextButton(driver, test_steps);
		} catch (Exception e) {e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			test_steps.add("=================== DISTRIBUTION CHANNEL INITIAL STATE ======================");
			app_logs.info("===================  DISTRIBUTION CHANNEL INITIAL STATE ======================");

			nightlyRate.verifyTitleSummaryValue(driver, "Rate plan type", "Nightly rate plan", test_steps);
			nightlyRate.verifyTitleSummaryValue(driver, "Rate plan name", ExpectedValue, test_steps);
			nightlyRate.verifySelectedChannels(driver, "innCenter",true, test_steps);
			if (Boolean.parseBoolean(isVerifyDistributionChannel)) {
			nightlyRate.verifyDisplayedDistributionChannels(driver, allActiveChannels, test_steps);
			}
			test_steps.add(
					"=================== SELECT DISTRIBUTED CHANNELS ======================");
			app_logs.info(
					"=================== SELECT DISTRIBUTED CHANNELS ======================");
			
			nightlyRate.selectChannels(driver, Channels,true, test_steps);
			nightlyRate.verifySelectedChannels(driver, Channels,true, test_steps);
			String summaryChannels = nightlyRate.generateTitleSummaryValueForChannels(driver);
			nightlyRate.clickNextButton(driver, test_steps);
			
			test_steps.add("=================== ROOM CLASS PAGE INITIAL STATE ======================");
			app_logs.info("===================  ROOM CLASS PAGE INITIAL STATE ======================");

			
			nightlyRate.verifyTitleSummaryValue(driver, "Rate plan type", "Nightly rate plan", test_steps);
			nightlyRate.verifyTitleSummaryValue(driver, "Rate plan name", ExpectedValue, test_steps);
			nightlyRate.verifyTitleSummaryValue(driver, "Channel", summaryChannels, test_steps);
			if (Boolean.parseBoolean(isVerifyAllRoomClasses)) {
			nightlyRate.verifySelectedRoomClasses(driver, Utility.convertArrayListToToken(allActiveRoomClasses, delim), false, test_steps);
			nightlyRate.verifyDisplayedRoomClasses(driver, allActiveRoomClasses, test_steps);
			}
			test_steps.add(
					"=================== SELECT ROOM CLASSES ======================");
			app_logs.info(
					"=================== SELECT ROOM CLASSES ======================");
			nightlyRate.selectRoomClasses(driver, RoomClasses,true, test_steps);
			nightlyRate.verifySelectedRoomClasses(driver, RoomClasses, true, test_steps);
			String summaryRoomClasses = nightlyRate.generateTitleSummaryValueForRoomClass(driver);
			nightlyRate.clickNextButton(driver, test_steps);
			
			test_steps.add("=================== RESTRICTION PAGE INITIAL STATE ======================");
			app_logs.info("===================  RESTRICTION PAGE INITIAL STATE ======================");
			
			nightlyRate.verifyTitleSummaryValue(driver, "Rate plan type", "Nightly rate plan", test_steps);
			nightlyRate.verifyTitleSummaryValue(driver, "Rate plan name", ExpectedValue, test_steps);
			nightlyRate.verifyTitleSummaryValue(driver, "Channel", summaryChannels, test_steps);
			nightlyRate.verifyTitleSummaryValue(driver, "Room class", summaryRoomClasses, test_steps);
			
			nightlyRate.verifySelectedRestriction(driver, "Length of stay,Booking window,Promo code", false, test_steps);
			nightlyRate.verifyRestrictionsTypesCheckBoxes(driver, "Length of stay,Booking window,Promo code", test_steps);
			
			nightlyRate.verifyToolTipBookingWindow(driver, test_steps);
			nightlyRate.verifyToolTipPromoCode(driver, test_steps);
			if(Boolean.parseBoolean(isRatePlanRistrictionReq)) {
				
				test_steps.add(
						"=================== SELECT RESTRICTIONS TO QUALIFY RATE ======================");
				app_logs.info(
						"=================== SELECT RESTRICTIONS TO QUALIFY RATE ======================");
			nightlyRate.selectRestrictionTypes(driver, RistrictionType, true, test_steps);
			nightlyRate.lengthOfStay(driver, RistrictionType, Boolean.parseBoolean(isMinStay), MinNights, Boolean.parseBoolean(isMaxStay), MaxNights, test_steps);
			nightlyRate.bookingWindow(driver, RistrictionType, Boolean.parseBoolean(isMoreThanDaysReq), MoreThanDaysCount, Boolean.parseBoolean(isWithInDaysReq), WithInDaysCount, test_steps);
			nightlyRate.promoCode(driver, RistrictionType, PromoCode, test_steps);
			
			String foundMsg = nightlyRate.generateRestrictionsToQualifyRate(RistrictionType, Boolean.parseBoolean(isMinStay), MinNights, Boolean.parseBoolean(isMaxStay), MaxNights, Boolean.parseBoolean(isMoreThanDaysReq), MoreThanDaysCount, Boolean.parseBoolean(isWithInDaysReq), WithInDaysCount, PromoCode);
			nightlyRate.verfiyRestrictionsToQualifyRateMsg(driver, foundMsg, test_steps);
			}else {
				test_steps.add(
						"=================== NO RESTRICTIONS TO QUALIFY RATE ======================");
				app_logs.info(
						"=================== NO RESTRICTIONS TO QUALIFY RATE ======================");
			}
			String restrictionsSummary = nightlyRate.getRestrictionsToQualifyRate(driver, test_steps);
			nightlyRate.clickNextButton(driver, test_steps);
			
			test_steps.add("=================== POLICIES PAGE INITIAL STATE ======================");
			app_logs.info("===================  POLICIES PAGE INITIAL STATE ======================");
			nightlyRate.verifyTitleSummaryValue(driver, "Rate plan type", "Nightly rate plan", test_steps);
			nightlyRate.verifyTitleSummaryValue(driver, "Rate plan name", ExpectedValue, test_steps);
			nightlyRate.verifyTitleSummaryValue(driver, "Channel", summaryChannels, test_steps);
			nightlyRate.verifyTitleSummaryValue(driver, "Room class", summaryRoomClasses, test_steps);
			nightlyRate.verifyTitleSummaryValue(driver, "Restrictions", restrictionsSummary, test_steps);
			
			nightlyRate.verifyAllPolicies(driver, "Cancellation", allCancelationPolicies, test_steps);
			nightlyRate.verifyAllPolicies(driver, "Deposit", allDepositPolicies, test_steps);
			nightlyRate.verifyAllPolicies(driver, "Check-in", allCheckInPolicies, test_steps);
			nightlyRate.verifyAllPolicies(driver, "No Show", allNoShowPolicies, test_steps);
			
			if(Boolean.parseBoolean(isPolicesReq)) {
				test_steps.add(
						"=================== SELECT POLICIES ======================");
				app_logs.info(
						"=================== SELECT POLICIES ======================");
				nightlyRate.selectPolicy(driver, PoliciesType,true, test_steps);
				nightlyRate.selectPolicy(driver, PoliciesName,true, test_steps);
				
			}else {
				test_steps.add(
						"=================== NO POLICIES ======================");
				app_logs.info(
						"=================== NO POLICIES ======================");
				
			}
			
			HashMap<String, String> allPolicyDesc = nightlyRate.getAllPolicyDescriptions(driver, PoliciesName, Boolean.parseBoolean(isPolicesReq), test_steps);
			nightlyRate.clickNextButton(driver, test_steps);
			
			nightlyRate.verifyTitleSummaryValue(driver, "Rate plan type", "Nightly rate plan", test_steps);
			nightlyRate.verifyTitleSummaryValue(driver, "Rate plan name", ExpectedValue, test_steps);
			nightlyRate.verifyTitleSummaryValue(driver, "Channel", summaryChannels, test_steps);
			nightlyRate.verifyTitleSummaryValue(driver, "Room class", summaryRoomClasses, test_steps);
			nightlyRate.verifyTitleSummaryValue(driver, "Restrictions", restrictionsSummary, test_steps);
			
			nightlyRate.verifyPolicyTitleSummaryValue(driver, PoliciesName, allPolicyDesc,Boolean.parseBoolean(isPolicesReq), test_steps);
			
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
		} catch (Exception e) {e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed", testName, "RatesV2", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getData("ValidateNightlyRatePlanV2", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}
}
