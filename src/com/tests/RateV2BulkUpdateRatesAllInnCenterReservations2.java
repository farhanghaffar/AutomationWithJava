package com.innroad.inncenter.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.Account;
import com.innroad.inncenter.pageobjects.Admin;
import com.innroad.inncenter.pageobjects.AdvGroups;
import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Distribution;
import com.innroad.inncenter.pageobjects.Folio;
import com.innroad.inncenter.pageobjects.Groups;
import com.innroad.inncenter.pageobjects.Login;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.RatesGrid;
import com.innroad.inncenter.pageobjects.Reservation;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.Tapechart;
import com.innroad.inncenter.pageobjects.Tax;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class RateV2BulkUpdateRatesAllInnCenterReservations2 extends TestCore {
	private WebDriver driver = null;
	public static String test_name;
	public static String test_description;
	public static String test_category;
	public static ArrayList<String> TestName = new ArrayList<>();
	public static ArrayList<String> testDescription = new ArrayList<>();
	public static ArrayList<String> testCategory = new ArrayList<>();
	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void rateV2BulkUpdateRatesAllInnCenterReservations(String bulkUpdateType, String dateFormatType,
			String checkInDate, String checkOutDate, String sunday, String monday, String tuesday, String wednesday,
			String thursday, String friday, String saturday, String isTotalOccupancyOn, String totalOccupancyType,
			String totalOccupancyValue, String ratePlansName, String roomClassAbb, String roomClassName, String channel,
			String updateRatesType, String updateRateByRoomClass, String nightlyRate, String additionalAdults,
			String additionalChild, String rateChangeValue, String rateCurrencyType, String reservationFrom,
			String reservationType, String subTypeReservationFrom, String adultsCount, String childrenCount,
			String isAssign, String isDepositOverride, String depositOverrideAmount, String salutation,
			String accountName, String firstName, String lastName, String isGuesProfile, String paymentType,
			String marketSegment, String referral, String promoCode, String isSplitCheck, String city, String country,
			String state, String postalCode, String address, String email, String phoneNumber, String blockName,
			String roomPerNight, String updatedBlockedCount, String roomBlockCount, String bookClassColor,
			String taxName) throws InterruptedException, IOException, ParseException {

		// test_name = "RateV2BulkUpdateRatesAllInnCenterReservations";
		test_description = "Rates V2 - Bulk Update (Rates) - All Inncenter reservations<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682418' target='_blank'>"
				+ "Click here to open TestRail: C682418</a>";
		test_category = "RateV2_RatesGrid";
		String testName = "RateV2BulkUpdateRatesAllInnCenterReservations";
		TestName.add(testName);
		testDescription.add(test_description);
		testCategory.add(test_category);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Login login = new Login();
		Navigation navigation = new Navigation();
		RoomClass roomClass = new RoomClass();
		Distribution distribution = new Distribution();
		RatesGrid ratesGrid = new RatesGrid();
		CPReservationPage reservation = new CPReservationPage();
		Admin admin = new Admin();
		Tapechart tapeChart = new Tapechart();
		Account account = new Account();
		Groups group = new Groups();
		AdvGroups advgroup = new AdvGroups();
		Folio folio = new Folio();
		Tax tax = new Tax();

		List<String>[] roomClassList = new List[3];
		ArrayList<String> activeChannelsList = new ArrayList<String>();
		ArrayList<String> activeRoomClassesNames = new ArrayList<String>();
		ArrayList<String> activeRatePlanNames = new ArrayList<>();
		ArrayList<String> inactiveRatePlanNames = new ArrayList<>();

		String Day = Utility.getCurrentDate("EEE,MM/dd/YYYY");
		Day = Day.split(",")[0];
		Double depositAmount = 0.0;
		String reservationNumber = "";
		String clientDateFormatType = "";
		String clientDateFormat = "";
		String firstRoomNumber = "";
		String secondRoomNumber = "";
		String accountNumber = "";
		String numberofDayss = "";
		int numberofDays = 0;
		accountName = reservationFrom + subTypeReservationFrom + Utility.generateRandomStringWithGivenLength(3);
		lastName = lastName + "_" + subTypeReservationFrom + Utility.generateRandomStringWithGivenLength(3);
		int taxApplied = 0;

		String[] ratePlanArray = ratePlansName.split("\\|");
		String[] roomClassArray = roomClassName.split("\\|");
		String[] channelArray = channel.split("\\|");

		int ratePlanSize = ratePlanArray.length;
		int roomClassNameSize = roomClassArray.length;
		int channelSize = channelArray.length;

		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);
			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver = getDriver();
			login.login(driver, "https://www.app.qainnroad.com", "autocp", "autouser", "Auto@123");
			testSteps.add("Logged into the application");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
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

//		try {
//			// Client Info Data
//
//			testSteps.add("==========GET DEFAULT DATE FORMANT AND CURRENCY IN CLIENT INFO========");
//			app_logs.info("==========GET DEFAULT DATE FORMANT AND CURRENCY IN CLIENT INFO==========");
//			navigation.Admin(driver);
//			app_logs.info("Navigate Admin");
//			testSteps.add("Navigate Admin");
//			navigation.Clientinfo(driver);
//			app_logs.info("Navigate ClientInfo");
//			testSteps.add("Navigate ClientInfo");
//			admin.clickClientName(driver);
//			app_logs.info("Clicked on Client");
//			testSteps.add("Clicked on Client");
//			admin.clickClientOption(driver);
//			app_logs.info("Clicked on Client Options Tab");
//			testSteps.add("Clicked on Client Options Tab");
//			Utility.clientCurrency = admin.getDefaultClientCurrency(driver);
//
//			Utility.clientCurrencySymbol = Utility.clientCurrency.split("\\(")[1].replace(")", "").replace(" ", "");
//
//			Utility.clientCurrency = Utility.clientCurrency.split("\\(")[0].replace(" ", "");
//
//			app_logs.info(
//					"Default Client Currency is:" + Utility.clientCurrency + " Symbol:" + Utility.clientCurrencySymbol);
//			testSteps.add(
//					"Default Client Currency is:" + Utility.clientCurrency + " Symbol:" + Utility.clientCurrencySymbol);
//			clientDateFormatType = admin.getClientDateFormat(driver);
//			if (!dateFormatType.equalsIgnoreCase(clientDateFormatType)) {
//				app_logs.info("DateFormat Type requried & find didn't match");
//				testSteps.add("DateFormat Type requried & find didn't match");
//				getTestSteps.clear();
//				getTestSteps = admin.selectClientDateFormat(driver, dateFormatType);
//				testSteps.addAll(getTestSteps);
//				admin.adminSaveButton(driver);
//				app_logs.info("Click Save");
//				testSteps.add("Click Save");
//				admin.adminDoneButton(driver);
//				app_logs.info("Click Done");
//				testSteps.add("Click Done");
//				admin.logout(driver);
//				app_logs.info("Log Out");
//				testSteps.add("Log Out");
//				login.login(driver, "https://www.app.qainnroad.com", "autorate", "autouser", "Auto@123");
//				testSteps.add("Logged into the application again");
//				app_logs.info("Logged into the application again");
//				navigation.Admin(driver);
//				app_logs.info("Navigate Admin again");
//				testSteps.add("Navigate Admin again");
//				navigation.Clientinfo(driver);
//				app_logs.info("Navigate ClientInfo again");
//				testSteps.add("Navigate ClientInfo again");
//				admin.clickClientName(driver);
//				app_logs.info("Clicked on Client again");
//				testSteps.add("Clicked on Client again");
//				admin.clickClientOption(driver);
//				app_logs.info("Clicked on Client Options Tab again");
//				testSteps.add("Clicked on Client Options Tab again");
//				clientDateFormatType = admin.getClientDateFormat(driver);
//
//			}
//			assertEquals(clientDateFormatType, dateFormatType,
//					"Failed: the dateFormat Type requried & find didn't match");
//			app_logs.info("Verified: Default Client Date Format Type is:" + clientDateFormatType);
//			testSteps.add("Verified: Default Client  Date Format Type is:" + clientDateFormatType);
//			clientDateFormat = ESTTimeZone.getDateFormatBasedOnClientDateFormartType(clientDateFormatType);
//			app_logs.info("Client Date Format To Use: " + clientDateFormat);
//			testSteps.add("Client Date Format To Use: " + clientDateFormat);
//			checkInDate = Utility.getCurrentDate(clientDateFormat);
//			app_logs.info("Check In date : " + checkInDate);
//
//			checkOutDate = Utility.GetNextDate(4, clientDateFormat);
//			app_logs.info("checkOut date: " + checkOutDate);
//			numberofDayss = Utility.differenceBetweenDates(Utility.parseDate(checkInDate, "MM/dd/yyyy", "dd/MM/yyyy"),
//					Utility.parseDate(checkOutDate, "MM/dd/yyyy", "dd/MM/yyyy"));
//			numberofDays = Integer.parseInt(numberofDayss);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			if (Utility.reTry.get(testName) == Utility.count) {
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
//				Utility.updateReport(e, "Failed to get Pre-Requisits", testName, "Channels/RoomClasses", driver);
//			} else {
//				Assert.assertTrue(false);
//			}
//		} catch (Error e) {
//			if (Utility.reTry.get(testName) == Utility.count) {
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
//				Utility.updateReport(e, "Failed toget Pre-Requisits", testName, "Channels/RoomClasses", driver);
//			} else {
//				Assert.assertTrue(false);
//			}
//		}
//
//		try {
//
//			navigation.setup(driver);
//			testSteps.add("Navigate To Setup");
//			navigation.Taxes(driver);
//			testSteps.add("Navigate To Taxes");
//			tax.openTax(driver, taxName);
//			testSteps.add("Open tax:" + taxName);
//			taxApplied = Integer.parseInt(tax.getTaxVal(driver, taxName).split("\\.")[0]);
//			tax.clickDoneOnTaxPage(driver);
//			testSteps.add("Tax Applied Value:" + taxName);
//
//		} catch (Exception e) {
//			if (Utility.reTry.get(testName) == Utility.count) {
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
//				Utility.updateReport(e, "Failed to Get Tax", "Taxes", "Taxes", driver);
//			} else {
//				Assert.assertTrue(false);
//			}
//		} catch (Error e) {
//			if (Utility.reTry.get(testName) == Utility.count) {
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.updateReport(e, "Failed to Get Tax", "Taxes", "Taxes", driver);
//			} else {
//				Assert.assertTrue(false);
//			}
//		}
//
//		// Get All RoomClasses/Distribution
//		try {
//
//			testSteps.add("==========GET ALL ACTIVE CHANNEL=========");
//			app_logs.info("==========GET ALL ACTIVE CHANNEL==========");
//
//			navigation.Inventory(driver);
//			testSteps.add("Navigate Setup");
//			navigation.Distribution(driver);
//			testSteps.add("Navigate Distribution");
//			activeChannelsList = distribution.getAllActiveChannelDetails(driver);
//			testSteps.add("Active Channel:" + activeChannelsList);
//			app_logs.info("Active Channel:" + activeChannelsList);
//			testSteps.add("==========GET ALL ROOMCLASSES=========");
//			app_logs.info("==========GET ALL ROOMCLASSES==========");
//			navigation.Setup(driver);
//			testSteps.add("Navigate Setup");
//			navigation.RoomClass(driver);
//			testSteps.add("Navigate RoomClass");
//			try {
//				roomClass.searchButtonClick(driver);
//				activeRoomClassesNames = roomClass.getAllRoomClasses(driver);
//				testSteps.add("Active RoomClasses:" + activeRoomClassesNames);
//				app_logs.info("Active RoomClasses:" + activeRoomClassesNames);
//			} catch (Exception e) {
//				roomClassList = roomClass.getAllActiveRoomClasses(driver);
//				activeRoomClassesNames = (ArrayList<String>) roomClassList[0];
//
//				testSteps.add("Active RoomClasses:" + activeRoomClassesNames);
//				app_logs.info("Active RoomClasses:" + activeRoomClassesNames);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			if (Utility.reTry.get(testName) == Utility.count) {
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
//				Utility.updateReport(e, "Failed to get Pre-Requisits", testName, "Channels/RoomClasses", driver);
//			} else {
//				Assert.assertTrue(false);
//			}
//		} catch (Error e) {
//			if (Utility.reTry.get(testName) == Utility.count) {
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
//				Utility.updateReport(e, "Failed toget Pre-Requisits", testName, "Channels/RoomClasses", driver);
//			} else {
//				Assert.assertTrue(false);
//			}
//		}
//
//		try {
//
//			testSteps.add("==========PRE-VERIFICATION=========");
//			app_logs.info("==========PRE-VERIFICATION==========");
//			navigation.Inventory(driver);
//			testSteps.add("Navigate Inventory");
//			navigation.Rates_Grid(driver);
//			app_logs.info("Navigate RatesGrid");
//			testSteps.add("Navigate RatesGrid");
//			getTestSteps.clear();
//			getTestSteps = ratesGrid.clickSettingButton(driver);
//			testSteps.addAll(getTestSteps);
//			getTestSteps.clear();
//			getTestSteps = ratesGrid.changeStateShowAdditionalAdultAdditionalChilToggalSettingContainer(driver, true);
//			testSteps.addAll(getTestSteps);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			if (Utility.reTry.get(testName) == Utility.count) {
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
//				Utility.updateReport(e, "Failed to get Pre-Requisits", testName, "Rates", driver);
//			} else {
//				Assert.assertTrue(false);
//			}
//		} catch (Error e) {
//			if (Utility.reTry.get(testName) == Utility.count) {
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
//				Utility.updateReport(e, "Failed to click Next button", testName, "Rates", driver);
//			} else {
//				Assert.assertTrue(false);
//			}
//		}

		// Get Rate Plan Name and Color
//		try {
//			testSteps.add("==========GETTING RATE PLANS=========");
//			app_logs.info("==========GETTING RATE PLANS==========");
//			ratesGrid.clickRatePlanArrow(driver, testSteps);
//
//			activeRatePlanNames = ratesGrid.getActiveRatePlanNames(driver, "active");
//			app_logs.info("rate plan size for active: " + activeRatePlanNames.size());
//
//			inactiveRatePlanNames = ratesGrid.getInActiveRatePlanNames(driver, "inactive");
//			app_logs.info("rate plan size for inactive: " + inactiveRatePlanNames.size());
//
//		} catch (Exception e) {
//			if (Utility.reTry.get(testName) == Utility.count) {
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
//				Utility.updateReport(e, "Failed to get rateplans", test_name, "Rateplans", driver);
//			} else {
//				Assert.assertTrue(false);
//			}
//		} catch (Error e) {
//			if (Utility.reTry.get(testName) == Utility.count) {
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
//				Utility.updateReport(e, "Failed to get rateplans", test_name, "Rateplans", driver);
//			}
//		}
		// Get The Data From the RatesGrid Before Updating
		ArrayList<String> beforeUpdateRateValues = new ArrayList<>();
		ArrayList<String> afterUpdateRateValues = new ArrayList<>();
		app_logs.info(numberofDays);
		int range = numberofDays / 20;
		app_logs.info(range);
		int rangeRemain = numberofDays % 20;
		app_logs.info(rangeRemain);
		if (rangeRemain > 0) {
			range = range + 1;
			app_logs.info(range);
		}
		if (range < 0) {
			range = 1;
			app_logs.info(range);
		}

		try {

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickCalendar(driver);
			testSteps.addAll(getTestSteps);

			ratesGrid.selectDateFromDatePicker(driver, checkInDate, clientDateFormat, testSteps);
			Wait.waitUntilPageLoadNotCompleted(driver, 50);

			for (int j = 0; j < ratePlanSize; j++) {
				ratesGrid.clickRatePlanArrow(driver, testSteps);
				ratesGrid.selectAndReturnSpecificRatePlan(driver, testSteps, ratePlanArray[j]);

				Wait.waitUntilPageLoadNotCompleted(driver, 50);
				for (int k = 0; k < roomClassNameSize; k++) {
					ratesGrid.expandRoomClassWithoutMinus(driver, testSteps, roomClassArray[k]);

					for (int l = 0; l < channelSize; l++) {
						try {
							ratesGrid.expandChannelWithoutMinus(driver, testSteps, roomClassArray[k], channelArray[l]);
							beforeUpdateRateValues
									.addAll(ratesGrid.getChannelDataValues(driver, roomClassArray[k], channelArray[l]));
							ratesGrid.clickMinusChannel(driver, roomClassArray[k], channelArray[l]);
							ratesGrid.clickMinusroomClass(driver, roomClassArray[k]);

						} catch (Exception e) {
							continue;
						}

					}
				}
				for (int x = 0; x < range - 1; x++) {
					app_logs.info("value of x : " + x);
					ratesGrid.clickCalendarArrow(driver, "right");

					for (int k = 0; k < roomClassNameSize; k++) {
						ratesGrid.expandRoomClassWithoutMinus(driver, testSteps, roomClassArray[k]);

						for (int l = 0; l < channelSize; l++) {
							try {
								ratesGrid.expandChannelWithoutMinus(driver, testSteps, roomClassArray[k],
										channelArray[l]);
								beforeUpdateRateValues.addAll(
										ratesGrid.getChannelDataValues(driver, roomClassArray[k], channelArray[l]));
								ratesGrid.clickMinusChannel(driver, roomClassArray[k], channelArray[l]);
								ratesGrid.clickMinusroomClass(driver, roomClassArray[k]);

							} catch (Exception e) {
								continue;
							}

						}
					}
				}
				for (int x = 0; x < range - 1; x++) {
					ratesGrid.clickCalendarArrow(driver, "left");

				}

			}

			app_logs.info("Rate Grid Values Before Update:" + beforeUpdateRateValues);
			testSteps.add("Rate Grid Values Before Update:" + beforeUpdateRateValues);
			// if (counter == 0 && updateRateType.eq) {
			// System.out.print(" Rate Value is:" + beforeUpdateRateValues);
			// setBaseRateValues(driver, beforeUpdateRateValues);
			// } else {
			// beforeUpdateRateValues = getBaseRateValues(driver);
			// }
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Get Data From Rate Grid", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Get Data From Rate Grid", test_name, "BulkUpdate", driver);
			}
		}

		try {

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickOnBulkUpdate(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.selectBulkUpdateOption(driver, bulkUpdateType);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePoppupHeading(driver, bulkUpdateType);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to navigate to rules popup", test_name, "BulkUpdate", driver);
			}
		}

		try {

			testSteps.add("==========SEELCT START DATE==========");
			app_logs.info("==========SEELCT START DATE==========");
			getTestSteps.clear();
			getTestSteps = ratesGrid.startDate(driver, checkInDate);
			testSteps.addAll(getTestSteps);

			testSteps.add("==========SEELCT END DATE==========");
			app_logs.info("==========SEELCT END DATE==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.endDate(driver, checkOutDate);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select Date", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select Date", test_name, "BulkUpdate", driver);
			}
		}

		try {
			app_logs.info("==========CHECKING/UNCHECKING DAYS==========");
			testSteps.add("==========CHECKING/UNCHECKING DAYS==========");

			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Sun", sunday);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Mon", monday);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Tue", tuesday);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Wed", wednesday);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Thu", thursday);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Fri", friday);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePoppupDayCheck(driver, "Sat", saturday);
			testSteps.addAll(getTestSteps);
			// If Current Day is not checked
			getTestSteps.clear();
			getTestSteps = ratesGrid.bulkUpdatePoppupDayCheck(driver, Day, "yes");
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to check/uncheck days", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to check/uncheck days", test_name, "BulkUpdate", driver);
			}
		}

		// Occupancy
		try {

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickTotalOccupancy(driver, isTotalOccupancyOn);
			testSteps.addAll(getTestSteps);

			if (isTotalOccupancyOn.equalsIgnoreCase("Yes")) {

				getTestSteps.clear();
				getTestSteps = ratesGrid.selectTotalOccupancyType(driver, totalOccupancyType);
				testSteps.addAll(getTestSteps);

				getTestSteps.clear();
				getTestSteps = ratesGrid.enterOccupancyValue(driver, totalOccupancyValue);
				testSteps.addAll(getTestSteps);
			}

		} catch (

		Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select total occupancy type", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select total occupancy type", test_name, "BulkUpdate", driver);
			}
		}

		try {
			app_logs.info("==========SELECTING RATE PLAN==========");
			testSteps.add("==========SELECTING RATE PLAN==========");

			for (String str : ratePlanArray) {
				if (str.equalsIgnoreCase("All Active Rate Plans")) {
					str = str + " (" + (activeRatePlanNames.size() - 1) + ")";

				}

				getTestSteps.clear();
				getTestSteps = ratesGrid.selectItemsFromDropDowns(driver, "Rate Plan", str);
				testSteps.addAll(getTestSteps);

			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select rate plan", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select rate plan", test_name, "BulkUpdate", driver);
			}
		}
		try {

			for (String str : roomClassArray) {
				if (str.equalsIgnoreCase("All room classes")) {
					str = str + " (" + activeRoomClassesNames.size() + ")";
				}
				getTestSteps.clear();
				getTestSteps = ratesGrid.selectRoomClass(driver, str);
				testSteps.addAll(getTestSteps);

			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select roomclass", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select roomclass", test_name, "BulkUpdate", driver);
			}
		}

		try {

			app_logs.info("==========SELECTING SOURCE==========");
			testSteps.add("==========SELECTING SOURCE==========");

			for (String str : channelArray) {
				if (str.equalsIgnoreCase("All sources")) {
					str = str + " (" + activeChannelsList.size() + ")";
				}
				getTestSteps.clear();
				getTestSteps = ratesGrid.selectItemsFromDropDowns(driver, "Source", str);
				testSteps.addAll(getTestSteps);

			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select source", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select source", test_name, "BulkUpdate", driver);
			}
		}

		try {
			app_logs.info("==========UPDATE RATES==========");
			testSteps.add("==========UPDATE RATES==========");

			// Checks Rate Update Type
			if (updateRatesType.equalsIgnoreCase("EnterNewRate")) {
				getTestSteps.clear();
				getTestSteps = ratesGrid.selectBulkUpdateRatesOption(driver, 0);
				testSteps.addAll(getTestSteps);

				getTestSteps.clear();
				getTestSteps = ratesGrid.updateRoomsByRoomClassToggle(driver,
						Boolean.parseBoolean(updateRateByRoomClass));
				testSteps.addAll(getTestSteps);

				// Verification of Symbol
				getTestSteps.clear();
				getTestSteps = ratesGrid.bulkUpdateRateGridSymbolVerification(driver, Utility.clientCurrencySymbol);
				testSteps.addAll(getTestSteps);

				String[] nightlyRateArray = nightlyRate.split("\\|");
				int nightArrayLength = 1;
				if (updateRateByRoomClass.equalsIgnoreCase("True")) {
					nightArrayLength = nightlyRateArray.length;
				}
				String[] additionalAdultArray = additionalAdults.split("\\|");
				String[] additionalChildArray = additionalAdults.split("\\|");
				// Check Length of NightlyRate List and Input Values
				for (int i = 0; i < nightArrayLength; i++) {

					getTestSteps.clear();
					getTestSteps = ratesGrid.updateNightlyRate(driver, i, nightlyRateArray[i]);
					testSteps.addAll(getTestSteps);

					getTestSteps.clear();
					getTestSteps = ratesGrid.updateAdditionalAdultRate(driver, i, additionalAdultArray[i]);
					testSteps.addAll(getTestSteps);

					getTestSteps.clear();
					getTestSteps = ratesGrid.updateAdditionalChildRate(driver, i, additionalChildArray[i]);
					testSteps.addAll(getTestSteps);

				}

			} else if (updateRatesType.equalsIgnoreCase("Increase") || updateRatesType.equalsIgnoreCase("Decrease")) {
				getTestSteps.clear();
				getTestSteps = ratesGrid.selectBulkUpdateRatesOption(driver, 1);
				testSteps.addAll(getTestSteps);

				getTestSteps.clear();
				getTestSteps = ratesGrid.selectRateIncreaseDecreaseOption(driver, updateRatesType);
				testSteps.addAll(getTestSteps);

				getTestSteps.clear();
				getTestSteps = ratesGrid.enterRateValueForUpdateRate(driver, rateChangeValue);
				testSteps.addAll(getTestSteps);

				getTestSteps.clear();
				getTestSteps = ratesGrid.selectRateCurrencyOption(driver, rateCurrencyType);
				testSteps.addAll(getTestSteps);

			} else {

				getTestSteps.clear();
				getTestSteps = ratesGrid.selectBulkUpdateRatesOption(driver, 2);
				testSteps.addAll(getTestSteps);

			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select update rate option", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select update rate option", test_name, "BulkUpdate", driver);
			}
		}

		try {

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickBulkUpdatePopupUpdateButton(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickYesUpdateButton(driver);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to click update", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to click update", test_name, "BulkUpdate", driver);
			}
		}

		try {

			getTestSteps.clear();
			getTestSteps = ratesGrid.clickCalendar(driver);
			testSteps.addAll(getTestSteps);

			ratesGrid.selectDateFromDatePicker(driver, checkInDate, clientDateFormat, testSteps);
			Wait.waitUntilPageLoadNotCompleted(driver, 50);

			for (int j = 0; j < ratePlanSize; j++) {
				ratesGrid.clickRatePlanArrow(driver, testSteps);
				ratesGrid.selectAndReturnSpecificRatePlan(driver, testSteps, ratePlanArray[j]);

				Wait.waitUntilPageLoadNotCompleted(driver, 50);
				for (int k = 0; k < roomClassNameSize; k++) {
					ratesGrid.expandRoomClassWithoutMinus(driver, testSteps, roomClassArray[k]);

					for (int l = 0; l < channelSize; l++) {
						try {
							ratesGrid.expandChannelWithoutMinus(driver, testSteps, roomClassArray[k], channelArray[l]);
							afterUpdateRateValues
									.addAll(ratesGrid.getChannelDataValues(driver, roomClassArray[k], channelArray[l]));
							ratesGrid.clickMinusChannel(driver, roomClassArray[k], channelArray[l]);
							ratesGrid.clickMinusroomClass(driver, roomClassArray[k]);

						} catch (Exception e) {
							continue;
						}

					}
				}
				for (int x = 0; x < range - 1; x++) {
					app_logs.info("value of x : " + x);
					ratesGrid.clickCalendarArrow(driver, "right");

					for (int k = 0; k < roomClassNameSize; k++) {
						ratesGrid.expandRoomClassWithoutMinus(driver, testSteps, roomClassArray[k]);

						for (int l = 0; l < channelSize; l++) {
							try {
								ratesGrid.expandChannelWithoutMinus(driver, testSteps, roomClassArray[k],
										channelArray[l]);
								afterUpdateRateValues.addAll(
										ratesGrid.getChannelDataValues(driver, roomClassArray[k], channelArray[l]));
								ratesGrid.clickMinusChannel(driver, roomClassArray[k], channelArray[l]);
								ratesGrid.clickMinusroomClass(driver, roomClassArray[k]);

							} catch (Exception e) {
								continue;
							}

						}
					}
				}
				for (int x = 0; x < range - 1; x++) {
					ratesGrid.clickCalendarArrow(driver, "left");

				}

			}

			// Verification
			getTestSteps.clear();
			getTestSteps = ratesGrid.verifyRateUpdate(driver, numberofDays, nightlyRate, updateRatesType,
					rateCurrencyType, rateChangeValue, beforeUpdateRateValues, afterUpdateRateValues);
			testSteps.addAll(getTestSteps);

		} catch (

		Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Get Data From Rate Grid", test_name, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Get Data From Rate Grid", test_name, "BulkUpdate", driver);
			}
		}

		// For Reservation From Account

		try {

			testSteps.add("==========GET RATES OF EACH ROOM CLASS IN RESERVATION=========");
			app_logs.info("==========GET RATES OF EACH ROOM CLASS IN RESERVATION==========");
			navigation.Inventory_Backward_1(driver);
			navigation.Reservation_Backward(driver);
			if (reservationFrom.equalsIgnoreCase("Account")) {
				navigation.Accounts(driver);
				testSteps.add("Navigate to Accounts");
				app_logs.info("Navigate to Accounts");
				testSteps.add("========== Creating account ==========");
				app_logs.info("========== Creating account ==========");
				if (subTypeReservationFrom.equalsIgnoreCase("")) {
					subTypeReservationFrom = "Corporate/Member Accounts";

				}
				account.ClickNewAccountbutton(driver, subTypeReservationFrom);
				testSteps.add("Clicked On New Account button for:" + subTypeReservationFrom);
				app_logs.info("Clicked On New Account button for:" + subTypeReservationFrom);
				account.AccountDetails(driver, subTypeReservationFrom, accountName);
				testSteps.add("Account Name:" + accountName);
				app_logs.info("Account Name:" + accountName);
				accountNumber = Utility.GenerateRandomString15Digit();
				account.ChangeAccountNumber(driver, accountNumber);
				testSteps.add("Account Number:" + accountNumber);
				app_logs.info("Account Number:" + accountNumber);
				if (subTypeReservationFrom.equalsIgnoreCase("Corporate/Member Accounts")
						|| subTypeReservationFrom.equalsIgnoreCase("Travel Agent")
						|| subTypeReservationFrom.equalsIgnoreCase("Unit Owners")) {

					getTestSteps.clear();
					getTestSteps = account.AccountAttributes(driver, test, marketSegment, referral, getTestSteps);
					testSteps.addAll(getTestSteps);
					getTestSteps.clear();
					getTestSteps = account.Mailinginfo(driver, test, firstName, lastName, phoneNumber, phoneNumber,
							address, address, address, email, city, state, postalCode, testSteps);
					testSteps.addAll(getTestSteps);
					getTestSteps.clear();
					getTestSteps = account.Billinginfo(driver, test, getTestSteps);
					testSteps.addAll(getTestSteps);
					getTestSteps.clear();
					getTestSteps = account.AccountSave(driver, test, accountName, getTestSteps);
					testSteps.addAll(getTestSteps);
					account.CP_NewReservationButton(driver, test);
					testSteps.add("New Reservation Button Clicked For:" + subTypeReservationFrom);
					app_logs.info("New Reservation Button Clicked For:" + subTypeReservationFrom);
				} else {

					getTestSteps.clear();
					getTestSteps = account.AccountSave(driver, test, accountName, getTestSteps);
					testSteps.addAll(getTestSteps);
					account.close_Account(driver);
					testSteps.add("Account Close");
					app_logs.info("Account Close");
					navigation.Reservation_Backward_1(driver);
					getTestSteps.clear();
					getTestSteps = reservation.click_NewReservation(driver, testSteps);
					testSteps.addAll(getTestSteps);

				}

			}

		} catch (

		Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to get Pre-Requisits", testName, "Channels/RoomClasses", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed toget Pre-Requisits", testName, "Channels/RoomClasses", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Reservation From Groups

		try {
			// navigation.navigateToReservations(driver);
			// testSteps.add("Navigate Reservations");
			// app_logs.info("Navigate Reservations");
			if (reservationFrom.equals("Group")) {

				navigation.secondaryGroupsManu(driver);
				testSteps.add("Navigate Groups");
				app_logs.info(" Navigate Groups");

				app_logs.info("==========CREATE NEW GROUP ACCOUNT==========");
				testSteps.add("==========CREATE NEW GROUP ACCOUNT==========");
				getTestSteps.clear();
				getTestSteps = group.enterrGroupName(driver, accountName);
				testSteps.addAll(getTestSteps);

				accountNumber = Utility.GenerateRandomString15Digit();
				getTestSteps.clear();
				getTestSteps = group.enterAccountNo(driver, accountNumber);
				testSteps.addAll(getTestSteps);

				getTestSteps.clear();
				getTestSteps = group.selectAccountAttributes(driver, marketSegment, referral);
				testSteps.addAll(getTestSteps);

				getTestSteps.clear();
				getTestSteps = group.enterMailingAddress(driver, firstName, lastName, phoneNumber, address, city, state,
						country, postalCode);
				testSteps.addAll(getTestSteps);

				getTestSteps.clear();
				getTestSteps = group.Billinginfo(driver);
				testSteps.addAll(getTestSteps);
				getTestSteps.clear();
				getTestSteps = group.clickOnSave(driver);
				testSteps.addAll(getTestSteps);
				if (subTypeReservationFrom.equals("New Reservation")) {

					getTestSteps.clear();
					getTestSteps = group.newReservation(driver);
					testSteps.addAll(getTestSteps);

				}

				else if (subTypeReservationFrom.equals("Block")) {
					app_logs.info("==========CREATE NEW BLOCK==========");
					testSteps.add("==========CREATE NEW BLOCK==========");
					String expectedRevenue = null;

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
					getTestSteps = group.SelectRatePlan(driver, ratePlansName);
					testSteps.addAll(getTestSteps);

					getTestSteps.clear();
					getTestSteps = group.SelectAdults(driver, adultsCount);
					testSteps.addAll(getTestSteps);

					getTestSteps.clear();
					getTestSteps = group.SelectChilds(driver, childrenCount);
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

					group.bookIconClick(driver, roomClassName);
					Utility.app_logs.info("Click <b>Blue Book Icon</b> of Room Class  : " + roomClassName);
					testSteps.add("Click <b>Blue Book Icon</b> of Room Class  : " + roomClassName);

				}
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Navigate AdvGroup", test_name, "NavigateAdvGroup", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Navigate AdvGroup", test_name, "NavigateAdvGroup", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

//		// Reservation From TapeCahrt
//
//		try {
//			if (reservationFrom.equalsIgnoreCase("TapeChart")) {
//				app_logs.info("==========GET UNASSIGNED RESERVATIONS IN UNASSIGNED COLUMN(PARKING LOT)==========");
//				testSteps.add("==========GET UNASSIGNED RESERVATIONS IN UNASSIGNED COLUMN(PARKING LOT)==========");
//				navigation.navTapeChart(driver, test);
//				testSteps.add("Navigate TapeChart");
//				app_logs.info("Navigate TapeChart");
//				getTestSteps.clear();
//				getTestSteps = tapeChart.TapeChartSearch(driver, checkInDate, checkOutDate, adultsCount, childrenCount,
//						ratePlansName, getTestSteps);
//				testSteps.addAll(getTestSteps);
//				app_logs.info("==========SELECT ROOM==========");
//				testSteps.add("==========SELECT ROOM==========");
//				if (subTypeReservationFrom.equalsIgnoreCase("Unassigned")) {
//					tapeChart.ClickUnassignedButton(driver);
//					tapeChart.ClickUnassignedRoomClass(driver, roomClassAbb, testSteps);
//
//				} else {
//					tapeChart.clickAvailableSlot(driver, roomClassAbb);
//					testSteps.add("Click available room of Room Class '" + roomClassAbb + "'");
//					app_logs.info("Click on available room");
//					testSteps.add("New Reservation page is opened");
//					app_logs.info("New Reservation Page is Opened");
//
//				}
//			}
//
//		} catch (Exception e) {
//			if (Utility.reTry.get(testName) == Utility.count) {
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
//				Utility.updateReport(e, "Failed to Observe parking lots(Unassigned Reservations) in TapeChart",
//						testName, "TapeChart", driver);
//			} else {
//				Assert.assertTrue(false);
//			}
//
//		} catch (Error e) {
//			if (Utility.reTry.get(testName) == Utility.count) {
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
//				Utility.updateReport(e, "Failed to Observe parking lots(Unassigned Reservations) in TapeChart",
//						testName, "TapeChart", driver);
//			} else {
//				Assert.assertTrue(false);
//			}
//		}
//		boolean isPassed = false;
//		try {
//			String rateFound = "";
//
//			testSteps.add("==========GET RATES OF EACH ROOM CLASS IN RESERVATION=========");
//			app_logs.info("==========GET RATES OF EACH ROOM CLASS IN RESERVATION==========");
//			// navigation.Reservation_Backward(driver);
//			// Reservation Click Page Condition
//			if (reservationFrom.equalsIgnoreCase("Reservation")) {
//
//				getTestSteps.clear();
//				getTestSteps = reservation.click_NewReservation(driver, testSteps);
//				testSteps.addAll(getTestSteps);
//
//			}
//			// Condition to check from which page reservation is coming
//			if (reservationFrom.equalsIgnoreCase("Reservation") || reservationFrom.equalsIgnoreCase("Account")
//					|| reservationFrom.equalsIgnoreCase("Group") || reservationFrom.equalsIgnoreCase("TapeChart")) {
//
//				int rateExpected = 0;
//				// Condition Which Type of Reservation need to be created
//				if (reservationType.equalsIgnoreCase("SingleOneNight")
//						|| reservationType.equalsIgnoreCase("SingleMoreNight") || reservationType.equals("")
//						|| reservationFrom.equalsIgnoreCase("TapeChart")) {
//
//					if (!reservationFrom.equalsIgnoreCase("TapeChart")) {
//						getTestSteps.clear();
//						getTestSteps = reservation.checkInDate(driver, 0);
//						testSteps.addAll(getTestSteps);
//						getTestSteps.clear();
//						getTestSteps = reservation.checkOutDate(driver, numberofDays);
//						testSteps.addAll(getTestSteps);
//						reservation.enter_Adults(driver, testSteps, adultsCount);
//						reservation.enter_Children(driver, testSteps, childrenCount);
//						reservation.select_Rateplan(driver, testSteps, ratePlansName, promoCode);
//						reservation.clickOnFindRooms(driver, testSteps);
//						// Verification
//						HashMap<String, String> ratesAgainstRoomClasses = new HashMap<String, String>();
//						for (int i = 0; i < roomClassArray.length; i++) {
//
//							ratesAgainstRoomClasses = reservation.getRatesOfAvailableRoomAgainstRatePlan(driver,
//									ratePlansName);
//							// Convert SingleDay Rate to More Days
//							rateFound = ratesAgainstRoomClasses.get(roomClassArray[i]);
//
//							// verification in Reservation FindRoom Page
//							nightlyRate = Integer.toString((Integer.parseInt(nightlyRate)) * numberofDays);
//							getTestSteps.clear();
//							getTestSteps = reservation.verifyRateUpdate(driver, numberofDays, rateFound, nightlyRate,
//									updateRatesType, rateChangeValue, rateCurrencyType, beforeUpdateRateValues);
//							testSteps.addAll(getTestSteps);
//
//							reservation.selectRoom(driver, roomClassArray[i], isAssign);
//							try {
//								getTestSteps.clear();
//								getTestSteps = reservation.verifyPopupChangeInPolicy(driver, testSteps);
//								testSteps.addAll(getTestSteps);
//							} catch (Exception e) {
//								e.printStackTrace();
//
//							}
//
//							depositAmount = reservation.deposit(driver, testSteps, isDepositOverride,
//									depositOverrideAmount);
//							reservation.clickNext(driver, testSteps);
//						}
//						reservation.enterGuestName(driver, testSteps, firstName, lastName);
//						reservation.enter_ContactName(driver, testSteps, "", firstName, lastName);
//
//					}
//				}
//				if (!reservationFrom.equalsIgnoreCase("TapeChart")) {
//
//					if (reservationType.equalsIgnoreCase("MRB")) {
//						int daysToAddInFirstMRBRoom = 2;
//						int daysToAddInSecondMRBRoom = 4;
//
//						getTestSteps.clear();
//						getTestSteps = reservation.checkInDates(driver, 0, 0);
//						testSteps.addAll(getTestSteps);
//
//						getTestSteps.clear();
//						getTestSteps = reservation.checkOutDates(driver, daysToAddInFirstMRBRoom, 0);
//						testSteps.addAll(getTestSteps);
//						reservation.select_Rateplan(driver, testSteps, ratePlansName, promoCode);
//
//						getTestSteps.clear();
//						getTestSteps = reservation.clickAddARoom(driver);
//						testSteps.addAll(getTestSteps);
//
//						getTestSteps.clear();
//						getTestSteps = reservation.checkInDates(driver, daysToAddInFirstMRBRoom, 1);
//						testSteps.addAll(getTestSteps);
//
//						getTestSteps.clear();
//						getTestSteps = reservation.checkOutDates(driver, daysToAddInSecondMRBRoom, 1);
//						testSteps.addAll(getTestSteps);
//						reservation.select_Rateplan(driver, testSteps, ratePlansName, promoCode);
//
//						// getTestSteps.clear();
//						// getTestSteps = reservation.splitRoomCheckbox(driver,
//						// Boolean.parseBoolean(isSplitCheck));
//						// testSteps.addAll(getTestSteps);
//
//						getTestSteps.clear();
//						getTestSteps = reservation.clickOnFindRooms(driver, testSteps);
//						testSteps.addAll(getTestSteps);
//
//						// Verification
//						HashMap<String, String> ratesAgainstRoomClasses = new HashMap<String, String>();
//
//						ratesAgainstRoomClasses = reservation.getRatesOfAvailableRoomAgainstRatePlan(driver,
//								ratePlansName);
//						// Convert SingleDay Rate to MRB
//						rateFound = ratesAgainstRoomClasses.get(roomClassName);
//						rateFound = rateFound.split("\\.")[0];
//						nightlyRate = Integer.toString(
//								(Integer.parseInt(nightlyRate)) * (daysToAddInSecondMRBRoom - daysToAddInFirstMRBRoom));
//
//						// verification in Reservation FindRoom Page For First
//						// Room
//						getTestSteps.clear();
//						getTestSteps = reservation.verifyRateUpdate(driver, numberofDays, rateFound, nightlyRate,
//								updateRatesType, rateChangeValue, rateCurrencyType, beforeUpdateRateValues);
//						testSteps.addAll(getTestSteps);
//
//						getTestSteps.clear();
//						getTestSteps = reservation.selectRoom(driver, roomClassName, isAssign);
//						testSteps.addAll(getTestSteps);
//
//						try {
//							getTestSteps.clear();
//							getTestSteps = reservation.verifyPopupChangeInPolicy(driver, testSteps);
//							testSteps.addAll(getTestSteps);
//
//						} catch (Exception e) {
//							e.printStackTrace();
//
//						}
//						// For 2nd Room
//						ratesAgainstRoomClasses = reservation.getRatesOfAvailableRoomAgainstRatePlan(driver,
//								ratePlansName);
//						// Convert SingleDay Rate to MRB
//						rateFound = ratesAgainstRoomClasses.get(roomClassName);
//						rateFound = rateFound.split("\\.")[0];
//						nightlyRate = Integer.toString(
//								(Integer.parseInt(nightlyRate)) * (daysToAddInSecondMRBRoom - daysToAddInFirstMRBRoom));
//
//						// verification in Reservation FindRoom Page
//						getTestSteps.clear();
//						getTestSteps = reservation.verifyRateUpdate(driver, numberofDays, rateFound, nightlyRate,
//								updateRatesType, rateChangeValue, rateCurrencyType, beforeUpdateRateValues);
//						testSteps.addAll(getTestSteps);
//						getTestSteps.clear();
//						getTestSteps = reservation.selectRoom(driver, roomClassName, isAssign);
//						testSteps.addAll(getTestSteps);
//
//						getTestSteps.clear();
//						getTestSteps = reservation.clickNext(driver, getTestSteps);
//						testSteps.addAll(getTestSteps);
//
//						if (isSplitCheck.equals("false")) {
//
//							firstRoomNumber = reservation.selectRoomNumberInMRB(driver, roomClassName, 0, 0);
//							String SplitStr[] = firstRoomNumber.split(":");
//							firstRoomNumber = SplitStr[1].trim();
//							app_logs.info("First room number: " + firstRoomNumber);
//
//							secondRoomNumber = reservation.selectRoomNumberInMRB(driver, roomClassName, 0, 1);
//							SplitStr = secondRoomNumber.split(":");
//							secondRoomNumber = SplitStr[1].trim();
//							app_logs.info("SecondReservationNumber: " + secondRoomNumber);
//
//							getTestSteps.clear();
//							getTestSteps = reservation.enterSecondaryGuestInfo(driver, testSteps, salutation, firstName,
//									lastName);
//						}
//
//						reservation.enterGuestName(driver, testSteps, firstName, lastName);
//						reservation.enter_ContactName(driver, testSteps, "", firstName, lastName);
//						reservation.uncheckCreateGuestProfile(driver, testSteps, isGuesProfile);
//
//						getTestSteps.clear();
//						getTestSteps = reservation.selectReferral(driver, referral);
//						testSteps.addAll(getTestSteps);
//
//					}
//				}
//				reservation.uncheckCreateGuestProfile(driver, testSteps, isGuesProfile);
//				if ((reservationFrom.equalsIgnoreCase("Account"))
//						&& (subTypeReservationFrom.equalsIgnoreCase("House Account")
//								|| !subTypeReservationFrom.equalsIgnoreCase("Gift Certificate"))) {
//
//					reservation.select_HouseAccoutAsPayment(driver, testSteps, accountName);
//				} else {
//
//					reservation.enterPaymentDetailsWithReservationPaymentMethod(driver, testSteps, paymentType,
//							reservationNumber);
//				}
//				reservation.selectReferral(driver, referral);
//				// Verification in Reservation Page
//
//				rateFound = reservation.getRoomCharge_TripSummary(driver);
//				rateFound = rateFound.split("\\.")[0];
//
//				// verification in Reservation Trip Summary
//				nightlyRate = Integer.toString((Integer.parseInt(nightlyRate)) * numberofDays);
//
//				getTestSteps.clear();
//				getTestSteps = reservation.verifyRateUpdate(driver, numberofDays, rateFound, nightlyRate,
//						updateRatesType, rateChangeValue, rateCurrencyType, beforeUpdateRateValues);
//				testSteps.addAll(getTestSteps);
//
//				reservation.clickBookNow(driver, testSteps);
//				reservationNumber = reservation.get_ReservationConfirmationNumber(driver, testSteps);
//				reservation.clickCloseReservationSavePopup(driver, testSteps);
//				// Verification in Folio Page
//
//				reservation.click_Folio(driver, testSteps);
//
//				rateFound = folio.getRoomCharges(driver);
//				rateFound = rateFound.split("\\.")[0];
//				app_logs.info("Nighlty Rate Plan Found In Folio Summary:" + rateFound);
//				testSteps.add("Nighlty Rate Plan Found In Folio Summary:" + rateFound);
//				nightlyRate = Integer.toString((Integer.parseInt(nightlyRate)) * numberofDays);
//				getTestSteps.clear();
//				getTestSteps = reservation.verifyRateUpdate(driver, numberofDays, rateFound, nightlyRate,
//						updateRatesType, rateChangeValue, rateCurrencyType, beforeUpdateRateValues);
//				testSteps.addAll(getTestSteps);
//
//				folio.includeTaxinLIneItemCheckbox(driver, false);
//				app_logs.info("Tax Line Item CheckBox UnChecked");
//				testSteps.add("Tax Line Item CheckBox UnChecked");
//				// Verification in Folio Line Amount
//
//				rateFound = folio.getAmount(driver, "Room Charge");
//				rateFound = rateFound.split("\\.")[0];
//
//				app_logs.info("Nighlty Rate Plan Found In Folio LineItem Amount:" + rateFound);
//				testSteps.add("Nighlty Rate Plan Found In Folio LineItem Amount:" + rateFound);
//				nightlyRate = Integer.toString((Integer.parseInt(nightlyRate)) * numberofDays);
//
//				getTestSteps.clear();
//				getTestSteps = reservation.verifyRateUpdate(driver, numberofDays, rateFound, nightlyRate,
//						updateRatesType, rateChangeValue, rateCurrencyType, beforeUpdateRateValues);
//				testSteps.addAll(getTestSteps);
//
//				reservation.close_FirstOpenedReservation(driver, getTestSteps);
//
//				reservation.click_NewReservation(driver, testSteps);
//
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			if (Utility.reTry.get(testName) == Utility.count) {
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
//				Utility.updateReport(e, "Failed to get Pre-Requisits", testName, "Channels/RoomClasses", driver);
//			} else {
//				Assert.assertTrue(false);
//			}
//		} catch (Error e) {
//			if (Utility.reTry.get(testName) == Utility.count) {
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
//				Utility.updateReport(e, "Failed toget Pre-Requisits", testName, "Channels/RoomClasses", driver);
//			} else {
//				Assert.assertTrue(false);
//			}
//		}
//		try {
//			navigation.navTapeChart(driver, test);
//			testSteps.add("Navigate TapeChart");
//			app_logs.info("Navigate TapeChart");
//			getTestSteps.clear();
//			getTestSteps = tapeChart.TapeChartSearch(driver, checkInDate, checkOutDate, adultsCount, childrenCount,
//					ratePlansName, getTestSteps);
//			testSteps.addAll(getTestSteps);
//			app_logs.info("==========SELECT ROOM==========");
//			testSteps.add("==========SELECT ROOM==========");
//			// Get the HasMap of Date/Rate From CheckIn To end CheckOut Date
//			HashMap<String, String> ratesAgainstDateTapeChart = new HashMap<String, String>();
//			ratesAgainstDateTapeChart = tapeChart.getTapChartRates(driver, roomClassAbb, checkInDate, checkOutDate,
//					adultsCount, childrenCount, ratePlansName, dateFormatType, numberofDays);
//			// Just Save the Value In the After ArrayList
//			for (String value : ratesAgainstDateTapeChart.values()) {
//
//				afterUpdateRateValues.add(value);
//			}
//			// Verify Rates Update In TapeChart
//			tapeChart.verifyRateUpdate(driver, numberofDays, nightlyRate, updateRatesType, rateCurrencyType,
//					rateChangeValue, beforeUpdateRateValues, afterUpdateRateValues);
//
//			// Move Reservation
//			Wait.wait60Second();
//			// tapeChart.moveReservations(driver, "103", "JS", "", 1, 1) ;
//
//			// tapeChart.verifyRateChangeInMoveExtendPopUp(driver, changeType,
//			// moveFromRate, moveToRate, taxApplied);
//
//			RetryFailedTestCases.count = Utility.reset_count;
//			Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
//
//		} catch (
//
//		Exception e) {
//			if (Utility.reTry.get(testName) == Utility.count) {
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
//				Utility.updateReport(e, "Failed to Observe parking lots(Unassigned Reservations) in TapeChart",
//						testName, "TapeChart", driver);
//			} else {
//				Assert.assertTrue(false);
//			}
//
//		} catch (Error e) {
//			if (Utility.reTry.get(testName) == Utility.count) {
//				RetryFailedTestCases.count = Utility.reset_count;
//				Utility.AddTest_IntoReport(TestName, testDescription, testCategory, testSteps);
//				Utility.updateReport(e, "Failed to Observe parking lots(Unassigned Reservations) in TapeChart",
//						testName, "TapeChart", driver);
//			} else {
//				Assert.assertTrue(false);
//			}
//		}

	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getData("BulkUpdateReservationGroup", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		// driver.quit();
	}

}
