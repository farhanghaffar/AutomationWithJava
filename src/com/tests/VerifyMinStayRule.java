package com.innroad.inncenter.tests;

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
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.OverView;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.Rules;
import com.innroad.inncenter.pageobjects.Season;
import com.innroad.inncenter.pageobjects.Tapechart;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyMinStayRule extends TestCore {

	// Automation-1453
	private WebDriver driver = null;

	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();
	ArrayList<String> testName = new ArrayList<>();
	ArrayList<String> testCategory = new ArrayList<>();
	ArrayList<String> testDescription = new ArrayList<>();
	String scriptName = "VerifyMinStayRule";
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
	public void verifyMinStayRule(String rateName, String baseAmount, String addtionalAdult,
			String additionalChild,
			String roomClassAbb, String roomClassName, String bedsCount, String maxAdults, 
			String maxPersons,
			String roomQuantity, String startRoomNumber, String ratePlan, String source, String marketSegment,
			String referral, String firstName, String lastName, String phonenumber, String address, 
			String city,
			String country, String state, String postalcode, String noRuleSeasonName, String ruleSeasonName,
			String seasonStartDate, String seasonEndDate, String noRuleSeasonStartDate,
			String noRuleSeasonEndDate,
			String ruleName, String ruleValue, String ruleType, String status, String salutation, 
			String expectedReservationStatus, String propertyName)
			throws InterruptedException, IOException {

		test_description = "Create rule<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682406' target='_blank'>"
				+ "Click here to open TestRail: C682406</a>";

		test_catagory = "Rules";
		testName.add(scriptName);
		testDescription.add(test_description);
		testCategory.add(test_catagory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + scriptName + " TEST.");
		app_logs.info("##################################################################################");

		Rate rate = new Rate();
		OverView overview = new OverView();
		Navigation navigation = new Navigation();
		Rules rules = new Rules();
		Tapechart tap = new Tapechart();
		Season season = new Season();
		CPReservationPage reservation = new CPReservationPage();
		RoomClass room_class = new RoomClass();
		String tempraryRoomClassName = roomClassName;

		String randomNumber = Utility.GenerateRandomNumber();
		roomClassName = roomClassName + randomNumber;
		roomClassAbb = roomClassAbb + randomNumber;
		rateName = rateName + randomNumber;
		ruleName = ruleName + randomNumber;
		ruleSeasonName = ruleSeasonName + randomNumber;
		noRuleSeasonName = noRuleSeasonName + randomNumber;
		lastName = lastName + randomNumber;
		String reservationStatus = null;
		String reservationNumber = null;
		String tripTotal = null;
		String timeZone = null;
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
				Utility.AddTest_IntoReport(scriptName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
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
					navigation.open_Property(driver, testSteps, propertyName);
					testSteps.add("Open Property : " + propertyName);
					app_logs.info("Open Property : " + propertyName);
					navigation.click_PropertyOptions(driver, testSteps);
					timeZone = navigation.get_Property_TimeZone(driver);
					navigation.Reservation_Backward(driver);
					testSteps.add("Time Zone " + timeZone);
					app_logs.info("Time Zone " + timeZone);
					app_logs.info("Curret Time " + Utility.getCurrentDate("MMM dd,yyy: H:m:s", timeZone));

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
			navigation.Setup(driver);
			app_logs.info("==========CREATE ROOM CLASS==========");
			testSteps.add("==========CREATE ROOM CLASS==========");
			app_logs.info("Navigate Setup");
			testSteps.add("Navigate Setup");
			
			navigation.RoomClass(driver);
			app_logs.info("Navigate Room Class");
			testSteps.add("Navigate Room Class");
			
			navigation.NewRoomClass(driver);
			app_logs.info("Click on create new room class buton");
			testSteps.add("Click on create new room class buton");
			/*try {
				// Old Page Layout
				Utility.app_logs.info("try");
				navigation.NewRoomClass(driver);
				getTestSteps.clear();
				getTestSteps = room_class.CreateRoomClass(driver, roomClassName, roomClassAbb, bedsCount, maxAdults,
						maxPersons, roomQuantity, startRoomNumber, getTestSteps);
				testSteps.addAll(getTestSteps);

			} catch (Exception e) {
				// New Page Layout
				Utility.app_logs.info("catch");
				navigation.NewRoomClass1(driver);
				room_class.roomClassInfoNewPage(driver, roomClassName, roomClassAbb, bedsCount, maxAdults, maxPersons,
						roomQuantity, test);
			}*/
			
			try {
				getTestSteps.clear();
				getTestSteps = room_class.CreateRoomClass(driver, roomClassName, roomClassAbb, bedsCount, maxAdults,
						maxPersons, roomQuantity, startRoomNumber, getTestSteps);
				testSteps.addAll(getTestSteps);
			} catch (Exception e) {
				getTestSteps.clear();

				room_class.roomClassInformation(driver, roomClassName, roomClassAbb, bedsCount, 
						maxAdults, maxPersons,
						roomQuantity, test, getTestSteps);
				testSteps.addAll(getTestSteps);

			}
			
			testSteps.add("Sccessfully Created New RoomClass " + roomClassName + " Abb : " + roomClassAbb);
			app_logs.info("Sccessfully Created New RoomClass" + roomClassName + " Abb : " + roomClassAbb);
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

		// navigate to Inventory->Seasons
		try {
			navigation.Inventory(driver);
			testSteps.add("Navigate Inventory");
			app_logs.info("Navigate Inventory");
			navigation.Seasons(driver);
			testSteps.add("Navigate to Seasons");
			app_logs.info("Navigate to Seasons");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Navigate Seasons", scriptName, "Navigation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Navigate Seasons", scriptName, "Navigation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Click New Season Button
		try {

			app_logs.info("==========CREATE RULE SEASON==========");
			testSteps.add("==========CREATE RULE SEASON==========");
			season.NewSeasonButtonClick(driver);
			testSteps.add("Successfully Clicked New Season button");
			app_logs.info("Successfully Clicked New Season button");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Click New Season button", scriptName, "SeasonCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Click New Season button", scriptName, "SeasonCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			season.SeasonDeatilsPositive(driver, ruleSeasonName, status);
			testSteps.add("Enter Season Name '" + ruleSeasonName + "'");
			app_logs.info("Enter Season Name '" + ruleSeasonName + "'");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to add season details", scriptName, "SeasonCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to add season details", scriptName, "SeasonCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Fill Season Attributes
		try {
			getTestSteps.clear();
			getTestSteps = season.SetSeasonPeriod(driver,
					Utility.getNextDate(Integer.parseInt(seasonStartDate), "MM/dd/yyyy",timeZone),
					Utility.getNextDate(Integer.parseInt(seasonEndDate), "MM/dd/yyyy",timeZone), getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = season.CheckEffectiveDay(driver, "Monday", true, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = season.CheckEffectiveDay(driver, "Tuesday", true, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = season.CheckEffectiveDay(driver, "Wednesday", true, getTestSteps);
			getTestSteps.clear();
			getTestSteps = season.CheckEffectiveDay(driver, "Thursday", true, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = season.CheckEffectiveDay(driver, "Friday", true, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = season.CheckEffectiveDay(driver, "Saturday", true, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = season.CheckEffectiveDay(driver, "Sunday", true, getTestSteps);
			testSteps.addAll(getTestSteps);
			testSteps.add("Successfully Added Season Attributes");
			app_logs.info("Successfully Added Season Attributes");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Add Season Attributes", scriptName, "SeasonCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Add Season Attributes", scriptName, "SeasonCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Save Season
		try {
			season.SaveSeason(driver, ruleSeasonName);
			testSteps.add("Successfully Create Season '" + ruleSeasonName + "'");
			app_logs.info("Successfully Create Season: " + ruleSeasonName);
			reservation.closeReservationTab(driver);
			testSteps.add("Close Season");
			app_logs.info("Close Season");
			season.SearchButtonClick(driver);
			Utility.app_logs.info("Click Search Button");
			testSteps.add("Click Search Button");
			Assert.assertTrue(season.SearchSeason(driver, ruleSeasonName, false), "Failed: Created Season not found");
			testSteps.add("Verified Season '" + ruleSeasonName + "' Exist");
			app_logs.info("Verified Season '" + ruleSeasonName + "' Exist");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Create New Season", scriptName, "SeasonCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Create New Season", scriptName, "SeasonCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Click New Season Button
		try {
			app_logs.info("==========CREATE NO RULE SEASON==========");
			testSteps.add("==========CREATE NO RULE SEASON==========");
			season.NewSeasonButtonClick(driver);
			testSteps.add("Successfully Clicked New Season button");
			app_logs.info("Successfully Clicked New Season button");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Click New Season button", scriptName, "SeasonCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Click New Season button", scriptName, "SeasonCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			season.SeasonDeatilsPositive(driver, noRuleSeasonName, status);
			testSteps.add("Enter Season Name '" + noRuleSeasonName + "'");
			app_logs.info("Enter Season Name '" + noRuleSeasonName + "'");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to add season details", scriptName, "SeasonCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to add season details", scriptName, "SeasonCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Fill Season Attributes
		try {
			getTestSteps.clear();
			getTestSteps = season.SetSeasonPeriod(driver,
					Utility.getNextDate(Integer.parseInt(noRuleSeasonStartDate), "MM/dd/yyyy",timeZone),
					Utility.getNextDate(Integer.parseInt(noRuleSeasonEndDate), "MM/dd/yyyy",timeZone), getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = season.CheckEffectiveDay(driver, "Monday", true, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = season.CheckEffectiveDay(driver, "Tuesday", true, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = season.CheckEffectiveDay(driver, "Wednesday", true, getTestSteps);
			getTestSteps.clear();
			getTestSteps = season.CheckEffectiveDay(driver, "Thursday", true, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = season.CheckEffectiveDay(driver, "Friday", true, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = season.CheckEffectiveDay(driver, "Saturday", true, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = season.CheckEffectiveDay(driver, "Sunday", true, getTestSteps);
			testSteps.addAll(getTestSteps);
			testSteps.add("Successfully Added Season Attributes");
			app_logs.info("Successfully Added Season Attributes");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Add Season Attributes", scriptName, "SeasonCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Add Season Attributes", scriptName, "SeasonCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Save Season
		try {
			season.SaveSeason(driver, noRuleSeasonName);
			testSteps.add("Successfully Create Season '" + noRuleSeasonName + "'");
			app_logs.info("Successfully Create Season: " + noRuleSeasonName);
			reservation.closeReservationTab(driver);
			testSteps.add("Close Season");
			app_logs.info("Close Season");
			season.SearchButtonClick(driver);
			Utility.app_logs.info("Click Search Button");
			testSteps.add("Click Search Button");
			Assert.assertTrue(season.SearchSeason(driver, noRuleSeasonName, false), "Failed: Created Season not found");
			testSteps.add("Verified Season '" + noRuleSeasonName + "' Exist");
			app_logs.info("Verified Season '" + noRuleSeasonName + "' Exist");
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Create New Season", scriptName, "SeasonCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Create New Season", scriptName, "SeasonCreation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		//
		// Create New Rate and Attach RoomClass
		try {
			app_logs.info("==========CREATE RATE==========");
			testSteps.add("==========CREATE RATE==========");
			try {
				navigation.Reservation_Backward_1(driver);
			} catch (Exception f) {
				navigation.Reservation_Backward_3(driver);
			}
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

			rate.clickOnNewRateButton(driver, testSteps);
			rate.EnterRateName(driver, rateName, testSteps);
			rate.SelectRatePlan(driver, ratePlan, testSteps);
			rate.GetRateType(driver, testSteps);
			rate.EnterMaxAdults(driver, maxAdults, testSteps);
			rate.EnterMaxPersons(driver, maxPersons, testSteps);
			rate.EnterBaseAmount(driver, baseAmount, testSteps);
			rate.EnterAdditionalAdult(driver, addtionalAdult, testSteps);
			rate.EnterAdditionalChild(driver, additionalChild, testSteps);
			rate.EnterRateDisplayName(driver, rateName, testSteps);
			rate.EnterRatePolicy(driver, rateName, testSteps);
			rate.EnterRateDescription(driver, rateName, testSteps);
			getTestSteps.clear();
			getTestSteps = rate.AssociateSeason(driver, noRuleSeasonName);
			testSteps.addAll(getTestSteps);
			
			getTestSteps.clear();
			getTestSteps = rate.AssociateSeason(driver, ruleSeasonName);
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

		// Rules
		try {
			app_logs.info("==========CREATE RULE==========");
			testSteps.add("==========CREATE RULE==========");
			navigation.Inventory(driver);
			testSteps.add("Navigate Inventory");
			app_logs.info("Navigate Inventory");
			navigation.Rules(driver);
			testSteps.add("Navigate Rules");
			app_logs.info("Navigate Rules");
			rules.ClickCreateRule(driver, testSteps);
			rules.EnterRuleName(driver, ruleName, testSteps);
			rules.SelectRuleType(driver, ruleType, ruleValue, testSteps);
			rules.EnterRuleDescription(driver, ruleName, testSteps);
			rules.SelectSeason(driver, ruleSeasonName, testSteps);
			rules.SelectRoomClass(driver, roomClassName, testSteps);
			rules.SelectSource(driver, source, testSteps);
			rules.SelectRatePlan(driver, ratePlan, testSteps);
			rules.SaveRule(driver, ruleName, testSteps);
			rules.CloseTab(driver, testSteps);
			rules.ClickSearch(driver);
			testSteps.add("Click Search Button");
			app_logs.info("Click Search button");
			boolean ruleexist = rules.SearchRule(driver, ruleName);
			Assert.assertTrue(ruleexist, "Failed : Rule '" + ruleName + "' not found");
			testSteps.add("Verified Rule '" + ruleName + "' Exist");
			app_logs.info("Verified Rule '" + ruleName + "' Exist");
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create new rule", scriptName, "NewRule", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to create new rule", scriptName, "NewRule", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verify RULE in Tape Chart
		try {
			app_logs.info("==========VERIFY CREATED RULE IN TAPECHART==========");
			testSteps.add("==========VERIFY CREATED RULE IN TAPECHART==========");
			navigation.navigateToReservations(driver);
			testSteps.add("Navigate Reservations");
			app_logs.info("Navigate Reservations");
			navigation.ClickTapeChart(driver);
			testSteps.add("Navigate TapeChart");
			app_logs.info("Navigate TapeChart");
			getTestSteps.clear();
			getTestSteps = tap.VerifyRuleRow(driver, roomClassAbb, ruleType, ruleValue, getTestSteps);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to VERIFY CREATED RULE IN TAPECHART", scriptName, "VerifyRULE", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to VERIFY CREATED RULE IN TAPECHART", scriptName, "VerifyRULE", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verify Rule on creating New Reservation From Tapechart cell
		try {
			
			app_logs.info("==========ENTER CHECK IN DETAILS AND SEARCH ROOMS==========");
			testSteps.add("==========ENTER CHECK IN DETAILS AND SEARCH ROOMS==========");
			getTestSteps.clear();
			getTestSteps = tap.tapeChartSearch(driver, Utility.getNextDate(1, "MM/dd/yyyy",timeZone),
					Utility.getNextDate(2, "MM/dd/yyyy",timeZone), "1", "0", ratePlan, getTestSteps);
			testSteps.addAll(getTestSteps);
			app_logs.info("==========VERIFY CREATED RULE ON SELECTING ONE NIGHT FROM TAPECHART==========");
			testSteps.add("==========VERIFY CREATED RULE ON SELECTING ONE NIGHT FROM TAPECHART==========");
			getTestSteps.clear();
			getTestSteps = tap.ClickCell(driver, roomClassAbb, startRoomNumber, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = tap.VerifyRuleBroken(driver, ruleName, ruleName, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = tap.ClickBookRuleBroken(driver, getTestSteps);
			testSteps.addAll(getTestSteps);
			reservation.enter_MailingAddress_OnUnchecked_CreateGuestProfile(driver, testSteps, salutation, firstName, lastName,
					"no");
			getTestSteps.clear();
			getTestSteps = reservation.selectReferral(driver, referral);
			testSteps.addAll(testDescription);
			
			reservation.clickBookNow(driver, testSteps);
			reservationNumber = reservation.get_ReservationConfirmationNumber(driver, testSteps);
			reservationStatus = reservation.get_ReservationStatus(driver, testSteps);
			Assert.assertEquals(reservationStatus, expectedReservationStatus, "Failed: Reservation Status missmatched");
			reservation.clickCloseReservationSavePopup(driver, testSteps);
			String roomnumber = reservation.get_RoomNumber(driver, testSteps, "yes");
			Assert.assertEquals(roomnumber, startRoomNumber, "Failed: Reservation Number missmatched");
			tripTotal = reservation.get_TripSummaryTripTotalChargesWithoutCurrency(driver, testSteps);
			Utility.app_logs.info("Trip Total '" + tripTotal + "'");
			Assert.assertNotEquals(tripTotal, "", "Failed: Trip Total missmatched");
			
			app_logs.info("==========EDIT STAY INFO AND VERIFY RULE BROKEN==========");
			testSteps.add("==========EDIT STAY INFO AND VERIFY RULE BROKEN==========");
			getTestSteps.clear();
			getTestSteps = reservation.ClickEditStayInfo(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.ClickStayInfo_ChangeDetails(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.VerifyRoomClass_Rule(driver, roomClassName, ruleName, true, getTestSteps);
			testSteps.addAll(getTestSteps);

			reservation.closeReservationTab(driver);
			testSteps.add("Close Reservation");
			app_logs.info("Close Reservation");
		} catch (Exception e) {
			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to VERIFY Verify Rule on creating New Reservation From Tapechart cell",
						scriptName, "VerifyRULE", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Verify Rule on creating New Reservation From Tapechart cell",
						scriptName, "VerifyRULE", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Extend Reservation
		try {
			driver.navigate().refresh();
			navigation.navigateToReservations(driver);
			testSteps.add("Navigate Reservations");
			app_logs.info("Navigate Reservations");
			app_logs.info("==========VERIFY RULE BROKEN ON EXTEND RESERVATION FROM TAPE CHART==========");
			testSteps.add("==========VERIFY RULE BROKEN ON EXTEND RESERVATION FROM TAPE CHART==========");
			navigation.ClickTapeChart(driver);
			testSteps.add("Navigate TapeChart");
			app_logs.info("Navigate TapeChart");
			int preWidth = tap.ExtendReservation(driver, startRoomNumber, roomClassAbb, firstName + " " + lastName);
			testSteps.add("Extend reservation in tapechart successfully");
			app_logs.info("Extend reservation in tapechart successfully");
			getTestSteps.clear();
			getTestSteps = tap.VerifyRuleBroken(driver, ruleName, ruleName, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = tap.ClickCancelRuleBroken(driver, getTestSteps);
			testSteps.addAll(getTestSteps);
			driver.navigate().refresh();
			app_logs.info("==========VERIFY RESERVATION TOOL TIP==========");
			testSteps.add("==========VERIFY RESERVATION TOOL TIP==========");
			getTestSteps.clear();
			getTestSteps = tap.toolTip_Verification(driver, startRoomNumber, roomClassName, firstName + " " + lastName,
					Utility.getCurrentDate("MMM dd, yyyy",timeZone), Utility.getNextDate(1, "MMM dd, yyyy",timeZone), "1N", tripTotal,
					tripTotal, reservationNumber, roomClassAbb);
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

		// MOVE Reservation
		try {
			driver.navigate().refresh();
			app_logs.info("Navigate Reservations");
			app_logs.info("==========VERIFY RULE BROKEN ON MOVE RESERVATION FROM TAPE CHART==========");
			testSteps.add("==========VERIFY RULE BROKEN ON MOVE RESERVATION FROM TAPE CHART==========");
			navigation.ClickTapeChart(driver);
			app_logs.info("Navigate TapeChart");
			String RoomNumber = tap.MoveReservations(driver, startRoomNumber, roomClassAbb);
			testSteps.add("MOVE reservation in tapechart successfully");
			app_logs.info("MOVE reservation in tapechart successfully");
			getTestSteps.clear();
			getTestSteps = tap.VerifyRuleBroken(driver, ruleName, ruleName, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();
			getTestSteps = tap.ClickCancelRuleBroken(driver, getTestSteps);
			testSteps.addAll(getTestSteps);
			app_logs.info("==========VERIFY RESERVATION TOOL TIP==========");
			testSteps.add("==========VERIFY RESERVATION TOOL TIP==========");
			getTestSteps.clear();
			getTestSteps = tap.toolTip_Verification(driver, startRoomNumber, roomClassName, firstName + " " + lastName,
					Utility.getCurrentDate("MMM dd, yyyy",timeZone), Utility.getNextDate(1, "MMM dd, yyyy",timeZone), "1N", tripTotal,
					tripTotal, reservationNumber, roomClassAbb);
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

		// Verify Rate in Tape Chart
		try {
			app_logs.info("==========CREATE NEW RESERVATION FROM 'NEW RESERVATION' BUTTON==========");
			testSteps.add("==========CREATE NEW RESERVATION FROM 'NEW RESERVATION' BUTTON==========");
			navigation.navigateToReservations(driver);
			testSteps.add("Navigate Reservations");
			app_logs.info("Navigate Reservations");
			reservation.click_NewReservation(driver, testSteps);

			app_logs.info("==========SELECT NIGHTS LESS THAN MIN STAY RULE VALUE==========");
			testSteps.add("==========SELECT NIGHTS LESS THAN MIN STAY RULE VALUE==========");
			String CheckInDate = Utility.getCurrentDate("MM/dd/yyyy",timeZone);
			String CheckoutDate = Utility.getNextDate(1, "MM/dd/yyyy",timeZone);
			reservation.EnterDate(driver, "start", CheckInDate);
			testSteps.add("Select CheckIn date : " + CheckInDate);
			app_logs.info("Selecting checkin date : " + CheckInDate);
			reservation.EnterDate(driver, "end", CheckoutDate);
			testSteps.add("Select Checkout date : " + CheckoutDate);
			app_logs.info("Selecting checkin date : " + CheckoutDate);
			reservation.select_Rateplan(driver, testSteps, ratePlan, "");
			reservation.clickOnFindRooms(driver, testSteps);
			app_logs.info("==========VERIFY RULE BROKEN==========");
			testSteps.add("==========VERIFY RULE BROKEN==========");
			getTestSteps.clear();
			getTestSteps = reservation.VerifyRoomClass_Rule(driver, roomClassName, ruleName, true, getTestSteps);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========SELECT NIGHTS EQUAL OR GREATER THAN MIN STAY RULE VALUE==========");
			testSteps.add("==========SELECT NIGHTS EQUAL OR GREATER THAN MIN STAY RULE VALUE==========");
			CheckoutDate = Utility.getNextDate(3, "MM/dd/yyyy",timeZone);
			reservation.EnterDate(driver, "end", CheckoutDate);
			testSteps.add("Select Checkout date : " + CheckoutDate);
			app_logs.info("Selecting checkin date : " + CheckoutDate);
			reservation.clickOnFindRooms(driver, testSteps);
			app_logs.info("==========VERIFY RULE SATISFIED==========");
			testSteps.add("==========VERIFY RULE SATISFIED==========");
			getTestSteps.clear();
			getTestSteps = reservation.VerifyRoomClass_Rule(driver, roomClassName, ruleName, false, getTestSteps);
			testSteps.addAll(getTestSteps);
			reservation.closeReservationTab(driver);
			testSteps.add("Close Reservation");
			app_logs.info("Close Reservation");
		} catch (Exception e) {
			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to VERIFY CREATED RATE IN RESERVATION", scriptName, "VerifyRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to VERIFY CREATED RATE IN Reservation", scriptName, "VerifyRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// navigate to Inventory->Overview
		try {
			app_logs.info("==========VERIFY CREATED RULE IN RATES GRID==========");
			testSteps.add("==========VERIFY CREATED RULE IN RATES GRID==========");
			navigation.Inventory(driver);
			testSteps.add("Navigate Inventory");
			app_logs.info("Navigate Inventory");
			navigation.Rates_Grid(driver);
			testSteps.add("Navigate Rates Grid");
			app_logs.info("Navigate Rates Grid");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Navigate Rates Grid", scriptName, "Navigation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Navigate Rates Grid", scriptName, "Navigation", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// Check Room Classes
		try {
			getTestSteps.clear();
			getTestSteps = overview.ClickExpandRoomClass(driver, roomClassName);
			testSteps.addAll(getTestSteps);
			app_logs.info("==========VERIFY MIN STAY VALUE FOR RULE SEASON==========");
			testSteps.add("==========VERIFY MIN STAY VALUE FOR RULE SEASON==========");
//			for (int i = Integer.parseInt(seasonStartDate); i <= Integer.parseInt(seasonEndDate); i++) {
//				overview.VerifyMinStayValue(driver, roomClassName, i, ruleValue);
//				testSteps.add("Verified Min Stay Value '" + ruleValue + "' for Date '"
//						+ Utility.getNextDate(i, "MMM dd,yyyy",timeZone));
//				Utility.app_logs.info("Verified Min Stay Value '" + ruleValue + "' for Date '"
//						+ Utility.getNextDate(i, "MMM dd,yyyy",timeZone));
//			}
			app_logs.info("==========VERIFY MIN STAY VALUE FOR NO RULE SEASON==========");
			testSteps.add("==========VERIFY MIN STAY VALUE FOR NO RULE SEASON==========");
//			for (int i = Integer.parseInt(noRuleSeasonStartDate); i <= Integer.parseInt(noRuleSeasonEndDate); i++) {
//				overview.VerifyMinStayValue(driver, roomClassName, i, "");
//				testSteps.add("Verified Min Stay has no Value for Date '" + Utility.getNextDate(i, "MMM dd,yyyy",timeZone));
//				Utility.app_logs
//						.info("Verified Min Stay has no Value for Date '" + Utility.getNextDate(i, "MMM dd,yyyy",timeZone));
//			}
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Check RoomClasses", scriptName, "Verification", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Check RoomClasses", scriptName, "Verification", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Delete NewRule
		try {
			app_logs.info("==========DELETE RULE==========");
			testSteps.add("==========DELETE RULE==========");
			navigation.Inventory_Backward_1(driver);
			testSteps.add("Navigate Inventory");
			app_logs.info("Navigate Inventory");
			navigation.Rules(driver);
			testSteps.add("Navigate Rules");
			app_logs.info("Navigate Rules");
			rules.ClickSearch(driver);
			testSteps.add("Click Search Button");
			app_logs.info("Click Search button");
			boolean ruleexist = rules.SearchRule(driver, ruleName);
			Assert.assertTrue(ruleexist, "Failed : Rule '" + ruleName + "' not found");
			testSteps.add("Search Rule '" + ruleName + "'");
			app_logs.info("Search Rule '" + ruleName + "'");
			rules.SelectCheckBox(driver, ruleName);
			testSteps.add("Select Rule '" + ruleName + "' delete CheckBox");
			app_logs.info("Select Rule '" + ruleName + "' delete CheckBox");
			rules.ClickDeleteButton(driver);
			testSteps.add("Click Delete Button");
			app_logs.info("Click Delete Button");
			ruleexist = rules.SearchRule(driver, ruleName);
			Assert.assertTrue(!ruleexist, "Failed : Rule '" + ruleName + "' found");
			testSteps.add("Verified Rule '" + ruleName + "' has been deleted successfully");
			app_logs.info("Verified Rule '" + ruleName + "' has been deleted successfully");
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete rule", scriptName, "Rule", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to delete rule", scriptName, "Rule", driver);
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
			navigation.secondaryRatesMenuItem(driver);
			testSteps.addAll(getTestSteps);

			// start new method for delete rate
			getTestSteps.clear();
			getTestSteps = rate.deleteRates(driver, rateName);
			testSteps.addAll(getTestSteps);
			testSteps.add("New Rate has been Deleted successfully");
			app_logs.info("New Rate has been Deleted successfully");

			rate.verifyDeleteRate(driver, rateName);
			testSteps.add("Verify the Deleted Rate : " + rateName);
			app_logs.info("Verify the Deleted Rate " + rateName);
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Delete rate", scriptName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Delete rate", scriptName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Search and Delete New Season
		try {
			app_logs.info("==========DELETE SEASONS==========");
			testSteps.add("==========DELETE SEASONS==========");
			navigation.Inventory(driver);
			app_logs.info("Navigate Inventory");
			testSteps.add("Navigate Inventory");
			navigation.Seasons(driver);
			app_logs.info("Navigate Seasons");
			testSteps.add("Navigate Seasons");
			season.SearchButtonClick(driver);
			Utility.app_logs.info("Searching " + ruleSeasonName + " to Delete");
			testSteps.add("Searching " + ruleSeasonName + " to Delete");

			getTestSteps.clear();
			getTestSteps = season.DeleteSeason(driver, ruleSeasonName, getTestSteps);
			testSteps.addAll(getTestSteps);
			Assert.assertFalse(season.SearchSeason(driver, ruleSeasonName, false), "Failed: Created Season not found");
			testSteps.add("Verified Season '" + ruleSeasonName + "' Not Exist");
			app_logs.info("Verified Season '" + ruleSeasonName + "' not Exist");
			Utility.app_logs.info("Seccessfully Delete Season '" + ruleSeasonName + "'");
			testSteps.add("Seccessfully Delete Season '" + ruleSeasonName + "'");
			season.SearchButtonClick(driver);
			Utility.app_logs.info("Searching " + noRuleSeasonName + " to Delete");
			testSteps.add("Searching " + noRuleSeasonName + " to Delete");

			getTestSteps.clear();
			getTestSteps = season.DeleteSeason(driver, noRuleSeasonName, getTestSteps);
			testSteps.addAll(getTestSteps);

			Utility.app_logs.info("Seccessfully Delete Season '" + noRuleSeasonName + "'");
			testSteps.add("Seccessfully Delete Season '" + noRuleSeasonName + "'");

			Assert.assertFalse(season.SearchSeason(driver, ruleSeasonName, false), "Failed: Created Season not found");
			testSteps.add("Verified Season '" + ruleSeasonName + "' Not Exist");
			app_logs.info("Verified Season '" + ruleSeasonName + "' not Exist");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Deleted New Season", scriptName, "SeasonSelection", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Deleted New Season", scriptName, "SeasonSelection", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Delete Room Class
		try {
			app_logs.info("==========DELETE ROOM CLASS==========");
			testSteps.add("==========DELETE ROOM CLASS==========");
			navigation.Setup(driver);
			app_logs.info("Navigate Setup");
			testSteps.add("Navigate Setup");
			navigation.RoomClass(driver);
			app_logs.info("Navigate Room Class");
			testSteps.add("Navigate Room Class");

			// start room classes method here
			boolean isRoomClassShowing = room_class.searchClass(driver, tempraryRoomClassName);
			app_logs.info("Search Room Class : " + tempraryRoomClassName);
			testSteps.add("Search Room Class : " + tempraryRoomClassName);

			getTestSteps.clear();
			getTestSteps = room_class.deleteRoomClass(driver, tempraryRoomClassName);
			testSteps.addAll(getTestSteps);
			app_logs.info("Delete Room Class : " + tempraryRoomClassName);
			testSteps.add("Delete Room Class : " + tempraryRoomClassName);

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
			}
		}
	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheet name provided
		return Utility.getData("VerifyMinStayRule", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}
}
