package com.innroad.inncenter.tests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.RatesGrid;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class RateGrid_Verification extends TestCore {
	private WebDriver driver = null;
	public static String test_name = null;
	public static String test_description = null;
	public static String test_catagory = null;
	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> ratePlanNames = new ArrayList<>();
	ArrayList<String> activeRatePlanNames = new ArrayList<>();
	ArrayList<String> derivedRatePlanNames = new ArrayList<>();
	ArrayList<String> inactiveRatePlanNames = new ArrayList<>();
	ArrayList<String> ratePlanColor = new ArrayList<>();
	ArrayList<String> roomClass = new ArrayList<>();
	ArrayList<String> channelName = new ArrayList<>();
	ArrayList<String> ruleHeaders = new ArrayList<>();
	ArrayList<String> ruleLabels = new ArrayList<>();
	ArrayList<String> ruleImages = new ArrayList<>();
	ArrayList<String> ruleIndicationLabels = new ArrayList<>();
	ArrayList<String> bestRates = new ArrayList<>();
	ArrayList<String> getRoomClasses = new ArrayList<>();

	RoomClass roomClass1 = new RoomClass();
	Navigation navigation = new Navigation();
	RatesGrid rateGrid = new RatesGrid();
	String testName = this.getClass().getSimpleName().trim();
	String ratePlanType = null, bestAvailable = null, expandRoomClass = null;
	int size = 0;
	String dayNum = "";
	String weekDay = "";

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		app_logs.info(ratesConfig.getProperty("previousPrice"));
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);

	}

	@Test(dataProvider = "getData", groups = "RateGrid")
	public void rateGridVerification(String ratePlanName, String promoCode, String night, String minDay, String maxDay,
			String activeRatePlanColor, String derivedRatePlanColor, String inactiveRatePlanColor) {

		test_description = testName + "<br>";
		test_catagory = "RateGrid";
		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");
		// Login
		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);
			} else {
				Utility.reTry.replace(testName, 1);
			}

			driver = getDriver();
			login_CP(driver);
			testSteps.add("Logged into the application");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			Utility.catchException(driver, e, "Failed to login", "Login", "Login", testName, test_description,
					test_catagory, testSteps);
		} catch (Error e) {
			Utility.catchError(driver, e, "Failed to login", "Login", "Login", testName, test_description,
					test_catagory, testSteps);

		}
		try {
			testSteps.add("<b>============Get Active Room Classes============</b>");
			navigation.mainSetupManu(driver);
			app_logs.info("Navigate Setup");
			testSteps.add("Navigate Setup");
			navigation.roomClass(driver);
			testSteps.add("Navigate Room Class");
			roomClass1.selectRoomClassStatus(driver, "Active");
			List<String>[] arrayOfList = new List[3];
			arrayOfList = roomClass1.getAllActiveRoomClasses(driver);
			getRoomClasses = (ArrayList<String>) arrayOfList[0];
			testSteps.add((getRoomClasses.toString().replace("[", "").replace("]", "")));
			app_logs.info(getRoomClasses);

		} catch (Exception e) {
			Utility.catchException(driver, e, "Failed to Get Room Classes", "Room Class", "Room Class", testName,
					test_description, test_catagory, testSteps);
		} catch (Error e) {
			Utility.catchError(driver, e, "Failed to Get Room Classes", "Room Class", "Room Class", testName,
					test_description, test_catagory, testSteps);

		}
		try {

			navigation.inventory_Backward_1(driver);
			testSteps.add("Navigated to Inventory");
			navigation.ratesGrid(driver, testSteps);

			testSteps.add("<b>============Verify Edit Icon of Rate Plan ============</b>");
			rateGrid.verifyEditIcon(driver, testSteps);
			testSteps.add("<b>============Verify Delete Icon of Rate Plan ============</b>");
			rateGrid.verifyDeleteIcon(driver, testSteps);
			testSteps.add("<b>============Get Default Rate Plan ============</b>");
			rateGrid.getDefaultRatePlan(driver, testSteps);

			testSteps.add("<b>============Total Rate Plan============</b>");
			rateGrid.clickRatePlanArrow(driver, testSteps);
			size = rateGrid.sizeOfAllRatePlan(driver);
			testSteps.add("Total Rate Plans: <b>" + size + "</b>");

			testSteps.add("<b>============Get All Active Rate Plan List============</b>");
			activeRatePlanNames = rateGrid.getRatePlanNamesCategoryWise(driver, activeRatePlanColor);
			testSteps.add("Get List of All Active Rate Plans: <b>"
					+ (activeRatePlanNames.toString().replace("[", "").replace("]", "")) + "</b>");
			testSteps.add("<b>============Get All Derived Rate Plan List============</b>");
			derivedRatePlanNames = rateGrid.getRatePlanNamesCategoryWise(driver, derivedRatePlanColor);
			testSteps.add("Get List of All Derived Rate Plans: <b>"
					+ (derivedRatePlanNames.toString().replace("[", "").replace("]", "")) + "</b>");

			testSteps.add("<b>============Get All Inactive Rate Plan List============</b>");
			inactiveRatePlanNames = rateGrid.getRatePlanNamesCategoryWise(driver, inactiveRatePlanColor);
			testSteps.add("Get List of All Inactive Rate Plans: <b>"
					+ (inactiveRatePlanNames.toString().replace("[", "").replace("]", "")) + "</b>");

			testSteps.add("<b>============Get All Active Rate Plan Color============</b>");
			String activeRatePlanColorName = rateGrid.getRatePlanColorCategoryWise(driver, activeRatePlanNames,
					activeRatePlanColor);
			testSteps.add(" Color of All Active Rate Plans: <b>" + activeRatePlanColorName + "</b>");

			testSteps.add("<b>============Get All Derived Rate Plan Color============</b>");
			String deriverRatePlanColorName = rateGrid.getRatePlanColorCategoryWise(driver, derivedRatePlanNames,
					derivedRatePlanColor);
			testSteps.add(" Color of All Derived Rate Plans: <b>" + deriverRatePlanColorName + "</b>");

			testSteps.add("<b>============Get All Inactive Rate Plan Color============</b>");
			String inactiveRatePlanColorName = rateGrid.getRatePlanColorCategoryWise(driver, inactiveRatePlanNames,
					inactiveRatePlanColor);
			testSteps.add(" Color of All Inactive Rate Plans: <b>" + inactiveRatePlanColorName + "</b>");

			testSteps.add("<b>============Select Rate Plan from Drop Down Box============</b>");
			rateGrid.selectAndReturnSpecificRatePlan(driver, testSteps, ratePlanName);
			Wait.waitUntilPageLoadNotCompleted(driver, 50);
			testSteps.add("<b>============Edit Rate Plan============</b>");
			rateGrid.clickEditIcon(driver, testSteps);
			testSteps.add("<b>============Verified Rate Plan in Edit Mode============</b>");
			rateGrid.verifyRatePlaninEditMode(driver, testSteps, ratePlanName);
			rateGrid.closeRatePlan(driver, testSteps, ratePlanName);
			testSteps.add("<b>============Search and Select Rate Plan from Drop Down Box============</b>");
			rateGrid.clickRatePlanArrow(driver, testSteps);
			rateGrid.searchRateAndSelectRate(driver, testSteps, ratePlanName);
			testSteps.add("<b>============Get Rate Plan Description============</b>");
			ratePlanType = rateGrid.getRatePlanDescription(driver, testSteps);
			rateGrid.getRateConditionsDescription(driver, testSteps);

			testSteps.add("<b>============Collapse Rate Plan ============</b>");
			rateGrid.clickCollapseIconOfRatePlan(driver, testSteps);
			testSteps.add("<b>============Get Best Available Rates And Total Room Class ============</b>");
			rateGrid.getRegularRates(driver, testSteps);
			rateGrid.getBestAvailableRoomClass(driver, testSteps);
			testSteps.add("<b>============Expand Rate Plan ============</b>");
			rateGrid.clickExpandIconOfRatePlan(driver, testSteps);
			testSteps.add("============Get Room Class Size of Rate Plan <b>" + ratePlanName + "============</b>");
			int roomClassSize = rateGrid.getSizeOfRateGridRoomClass(driver);
			testSteps.add("Total Room Classes <b>" + roomClassSize + "</b>");
			testSteps.add("============Get Room Class List of Rate Plan <b>" + ratePlanName + "============</b>");
			roomClass = rateGrid.getrateGridRoomClass(driver);
			testSteps.add("<b>" + (roomClass.toString().replace("[", "").replace("]", "")) + "</b>");
			testSteps.add("<b>======Verify  All Active Room Classes========</b>");
			rateGrid.verifyRoomClasses(driver, getRoomClasses, testSteps);
			testSteps.add("<b>======Get Room Class Value========</b>");
			for (int i = 0; i < getRoomClasses.size(); i++) {
				ArrayList<String> data = rateGrid.getRoomClassValues(driver, i, getRoomClasses.get(i));
				String classData = getRoomClasses.get(i) + "--: <b>"
						+ (data.toString().replace("[", "").replace("]", "") + "</b>");
				testSteps.add(classData);
				app_logs.info(classData);
			}

			testSteps.add("<b>======Get Room Class Availability Value ========</b>");
			for (int i = 0; i < getRoomClasses.size(); i++) {
				ArrayList<String> data = rateGrid.getRoomClassAvailibilityDataValues(driver, i, getRoomClasses.get(i));
				String availabilityData = getRoomClasses.get(i) + " Availability--: <b>"
						+ (data.toString().replace("[", "").replace("]", "") + "</b>");
				testSteps.add(availabilityData);
				app_logs.info(availabilityData);
			}

			for (int i = 0; i < getRoomClasses.size(); i++) {
				testSteps.add("<b>============" + getRoomClasses.get(i) + " =========</b>");
				rateGrid.expandRoomClass(driver, testSteps, getRoomClasses.get(i));
				channelName = rateGrid.getChannelofAllRoomClass(driver, testSteps, getRoomClasses.get(i));
				testSteps
						.add("Chennals Are--:<b> " + channelName.toString().replace("[", "").replace("]", "") + "</b>");
				for (int j = 0; j < channelName.size(); j++) {
					ArrayList<String> channelData = rateGrid.getChannelDataValues(driver, j, getRoomClasses.get(i),
							channelName.get(j));
					testSteps.add(channelName.get(j) + " Data  <b>--: "
							+ (channelData.toString().replace("[", "").replace("]", "") + "</b>"));
					rateGrid.expandChannel(driver, testSteps, getRoomClasses.get(i), channelName.get(j));
					ruleHeaders.removeAll(ruleHeaders);
					ruleHeaders = rateGrid.getRuleHeader(driver, testSteps, getRoomClasses.get(i), channelName.get(j));
					testSteps.add("Rule Headerds are: <b>" + (ruleHeaders.toString().replace("[", "").replace("]", ""))
							+ "</b>");
					for (int k = 0; k < ruleHeaders.size(); k++) {
						if (ruleHeaders.get(k).equalsIgnoreCase(ratesConfig.getProperty("minStay"))) {
							ArrayList<String> value = rateGrid.getRuleDataValues(driver, i, getRoomClasses.get(i),
									channelName.get(j), ruleHeaders.get(k));
							testSteps.add("<b>" + ruleHeaders.get(k) + " </b>Value is -- <b>"
									+ (value.toString().replace("[", "").replace("]", "")) + "</b>");

						} else {
							ArrayList<String> value = rateGrid.getRuleDataValue(driver, i, getRoomClasses.get(i),
									channelName.get(i), ruleHeaders.get(k));
							testSteps.add("<b>" + ruleHeaders.get(k) + " </b>Value is -- <b>"
									+ (value.toString().replace("[", "").replace("]", "")) + "</b>");

						}

					}

					rateGrid.collapseChannel(driver, testSteps, getRoomClasses.get(i), channelName.get(j));
				}
				rateGrid.collapseRoomClass(driver, testSteps, getRoomClasses.get(i));
			}

			for (int i = 0; i < getRoomClasses.size(); i++) {
				rateGrid.getRuleAppliedForRoomClass(driver, getRoomClasses.get(i), testSteps);

			}
			for (int i = 0; i < getRoomClasses.size(); i++) {
				rateGrid.getOverRideAndNonOverrideValueForRoomClass(driver, getRoomClasses.get(i), testSteps);
			}

			testSteps.add("<b>======Get and Verify Color of All Room Classes========</b>");
			String roomClassColorName = null;
			for (String str : roomClass) {
				roomClassColorName = rateGrid.getRoomClassColor(driver, str);
				rateGrid.verifyRoomClassColor(driver, str, roomClassColorName);
			}
			testSteps.add("<b> Color of All the Room Class is: " + roomClassColorName + "</b>");

			for (String str : getRoomClasses) {
				rateGrid.getRateColor(driver, testSteps, str);
			}

			String roomClass1 = null, oldRateIs = null, newRateIs = null;
			ArrayList<String> updateRate = new ArrayList<String>();
			for (String str : getRoomClasses) {
				roomClass1 = str;
				updateRate = rateGrid.updateRate(driver, testSteps, str);
				app_logs.info(updateRate);
				oldRateIs = updateRate.get(0);
				newRateIs = updateRate.get(1);
				if (newRateIs.contains(".")) {
					if (Double.parseDouble(newRateIs) > 0.00) {
						break;
					}
				} else {
					if (Integer.parseInt(newRateIs) > 0) {
						break;
					}
				}

			}

			testSteps.add("<b>======Get OverRide Data========</b>");
			rateGrid.hoverRuleRate(driver, roomClass1, newRateIs, testSteps);
			rateGrid.getRuleDate(driver, testSteps);
			rateGrid.getRuleRate(driver, testSteps);
			ruleLabels = rateGrid.getRulesLabels(driver);
			ruleImages = rateGrid.getRuleImages(driver);

			for (int i = 0; i < ruleImages.size(); i++) {
				String label = rateGrid.verifyRulesLabels(driver, ruleLabels.get(i));

				if (ruleLabels.get(i).contains(ratesConfig.getProperty("updateBY"))) {

					String updateBy = rateGrid.getOverRideValues(driver, ratesConfig.getProperty("updateBY"));
					testSteps.add("<img src='" + ruleImages.get(i) + "' width='15' height='15'>" + "<b> " + label + " "
							+ updateBy + " </b>");
					rateGrid.verifyChangeValue(driver, updateBy);
					testSteps.add("Verified " + "<b> " + label + " " + updateBy + " </b>");
				} else if (ruleLabels.get(i).contains(ratesConfig.getProperty("updateOn"))) {
					String updateOn = rateGrid.getOverRideValues(driver, ratesConfig.getProperty("updateOn"));
					testSteps.add("<img src='" + ruleImages.get(i) + "' width='15' height='15'>" + " <b> " + label + " "
							+ updateOn + " </b>");
					rateGrid.verifyChangeValue(driver, updateOn);
					testSteps.add("Verified " + "<b> " + label + " " + updateOn + " </b>");
				} else if (ruleLabels.get(i).contains(ratesConfig.getProperty("previousPrice"))) {

					String changeValue = rateGrid.getOverRideValues(driver, ratesConfig.getProperty("previousPrice"));
					testSteps.add("<img src='" + ruleImages.get(i) + "' width='15' height='15'>" + "<b> " + label + " "
							+ changeValue + " </b>");
					rateGrid.verifyPreviousPfice(driver, changeValue);
					testSteps.add("Verified " + "<b> " + label + " " + changeValue + " </b>");
				} else {
					testSteps.add(
							"<img src='" + ruleImages.get(i) + "' width='15' height='15'>" + " <b> " + label + " </b>");
				}

			}

			testSteps.add("<b>======Verify Color of Override Rate========</b>");
			String colorName = rateGrid.getOverrideRateColor(driver, testSteps, roomClass1, newRateIs);
			rateGrid.verifyOverrideRateColor(driver, testSteps, roomClass1, newRateIs, colorName);
			testSteps.add("<b>======Update Rule and Verify Rule Color========</b>");

			for (int i = 0; i < getRoomClasses.size(); i++) {
				rateGrid.expandRoomClass(driver, testSteps, getRoomClasses.get(i));
				channelName = rateGrid.getChannelofAllRoomClass(driver, testSteps, getRoomClasses.get(i));
				for (int j = 0; j < channelName.size(); j++) {
					rateGrid.expandChannel(driver, testSteps, getRoomClasses.get(i), channelName.get(j));
					boolean isExist = rateGrid.getUpdateBoolValue(driver, getRoomClasses.get(i), channelName.get(j),
							ratesConfig.getProperty("minStay"));
					if (isExist) {
						rateGrid.updateRuleForMinStay(driver, testSteps, getRoomClasses.get(i), channelName.get(j),
								ratesConfig.getProperty("minStay"));
						rateGrid.updateRuleForNoCheckInAndOut(driver, testSteps, getRoomClasses.get(i),
								channelName.get(j), ratesConfig.getProperty("checkinRule"));
						rateGrid.getCheckInAndCheckoutColor(driver, testSteps, getRoomClasses.get(i),
								channelName.get(j), ratesConfig.getProperty("checkinRule"));
						rateGrid.updateRuleForNoCheckInAndOut(driver, testSteps, getRoomClasses.get(i),
								channelName.get(j), ratesConfig.getProperty("checkoutRule"));
						rateGrid.getCheckInAndCheckoutColor(driver, testSteps, getRoomClasses.get(i),
								channelName.get(j), ratesConfig.getProperty("checkoutRule"));
						// rateGrid.collapseChannel(driver, testSteps,
						// getRoomClasses.get(i), channelName.get(j));

						break;
					}
				}

				// rateGrid.collapseRoomClass(driver, testSteps,
				// getRoomClasses.get(i));
				break;

			}

			testSteps.add("<b>======Verify Removed OverRide========</b>");
			rateGrid.removeOverRide(driver, testSteps, roomClass1, newRateIs, oldRateIs);

			testSteps.add("<b>======Delete Rate Plan========</b>");
			rateGrid.clickDeleteIcon(driver, testSteps);
			rateGrid.verifyDeletedMsg(driver, testSteps, ratesConfig.getProperty("deleteRatePlanMsg"));
			rateGrid.clickDeleteButton(driver, testSteps);
			rateGrid.clickRatePlanArrow(driver, testSteps);
			ratePlanNames = rateGrid.getRatePlanNames(driver);
			rateGrid.verifyDeletedRatePlan(driver, testSteps, ratePlanName, ratePlanNames);

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);

		} catch (Exception e) {
			Utility.catchException(driver, e, "Failed to Get Reterive Rate Plan Name and Color", "RatePlanName",
					"RatePlanName", testName, test_description, test_catagory, testSteps);
		} catch (Error e) {
			Utility.catchError(driver, e, "Failed to Get Reterive Rate Plan Name Color", "RatePlanName", "RatePlanName",
					testName, test_description, test_catagory, testSteps);

		}

	}

	@DataProvider
	public Object[][] getData() {

		return Utility.getData("RateGridVerification", excel);

	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
