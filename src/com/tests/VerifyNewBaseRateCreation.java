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
import com.innroad.inncenter.pageobjects.Groups;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.OverView;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.RoomMaintenance;
import com.innroad.inncenter.pageobjects.Tapechart;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyNewBaseRateCreation extends TestCore {

	// Automation-1452
	private WebDriver driver = null;

	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> getTest_Steps = new ArrayList<>();
	ArrayList<String> TestName = new ArrayList<>();
	ArrayList<String> TestCategory = new ArrayList<>();
	ArrayList<String> TestDescription = new ArrayList<>();
	String test_name = "VerifyNewBaseRateCreation";
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
	public void verifyNewBaseRateCreation(String RateName, String BaseAmount, String AddtionalAdult, String AdditionalChild,
			String DisplayName, String AssociateSession, String RatePolicy, String RateDescription, String RoomClassAbb,
			String roomClassName, String bedsCount, String maxAdults, String maxPersons, String roomQuantity,
			String StartRoomNumber, String RatePlan, String RateType, String RateAttributes, String Interval,
			String Source, String Adults, String Child, String MarketSegment, String GroupReferral,
			String GroupFirstName, String GroupLastName, String GroupPhn, String GroupAddress, String Groupcity,
			String Groupcountry, String Groupstate, String GroupPostalcode, String BlockName, String RoomPerNight)
			throws InterruptedException, IOException {

		test_description = "Create a rate<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682405' target='_blank'>"
				+ "Click here to open TestRail: C682405</a>";

		test_catagory = "TapeChart";
		String testName = test_name;
		TestName.add(testName);
		TestDescription.add(test_description);
		TestCategory.add(test_catagory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Rate rate = new Rate();
		OverView overview = new OverView();
		Navigation nav = new Navigation();
		Tapechart tap = new Tapechart();
		CPReservationPage cpres = new CPReservationPage();
		RoomClass roomClass = new RoomClass();
		String tempraryRoomClassName = roomClassName;
		String randomNumber = Utility.GenerateRandomNumber();
		roomClassName = roomClassName + randomNumber;
		RoomClassAbb = RoomClassAbb + randomNumber;
		RateName = RateName + randomNumber;
		DisplayName = RateName;
		String accountName = GroupFirstName + GroupLastName + randomNumber;
		String accountNumber = null;

		Groups group = new Groups();

		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);

			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver = getDriver();
			login_CP(driver);
			test_steps.add("Entered appication URL : " + TestCore.envURL);
			test_steps.add("Logged into the application ");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
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
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Room Class
		try {
			nav.Setup(driver);
			app_logs.info("==========CREATE ROOM CLASS==========");
			test_steps.add("==========CREATE ROOM CLASS==========");
			app_logs.info("Navigate Setup");
			test_steps.add("Navigate Setup");
			
			nav.RoomClass(driver);
			app_logs.info("Navigate Room Class");
			test_steps.add("Navigate Room Class");
			
			try {
				// Old Page Layout
				Utility.app_logs.info("try");
				nav.NewRoomClass(driver);
				getTest_Steps.clear();
				getTest_Steps = roomClass.CreateRoomClass(driver, roomClassName, RoomClassAbb, bedsCount, maxAdults,
						maxPersons, roomQuantity, StartRoomNumber, getTest_Steps);
				test_steps.addAll(getTest_Steps);

			} catch (Exception e) {
				// New Page Layout
				Utility.app_logs.info("catch");
				nav.NewRoomClass1(driver);
				roomClass.roomClassInfoNewPage(driver, roomClassName, RoomClassAbb, bedsCount, maxAdults, maxPersons,
						roomQuantity, test);
			}
			test_steps.add("Sccessfully Created New RoomClass " + roomClassName + " Abb : " + RoomClassAbb);
			app_logs.info("Sccessfully Created New RoomClass" + roomClassName + " Abb : " + RoomClassAbb);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to create room class successfully", testName, "NewRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to create room class successfully", testName, "NewRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Create New Rate and Attach RoomClass
		try {
			app_logs.info("==========CREATE RATE==========");
			test_steps.add("==========CREATE RATE==========");
			/*try {
				nav.Reservation_Backward_1(driver);
			} catch (Exception f) {
				nav.Reservation_Backward_3(driver);
			}
*/			nav.Inventory(driver);
			app_logs.info("Navigate Inventory");
			test_steps.add("Navigate Inventory");

			getTest_Steps.clear();
			getTest_Steps = nav.secondaryRatesMenuItem(driver);
			test_steps.addAll(getTest_Steps);
			
			app_logs.info("Navigate Rate");
			test_steps.add("Navigate Rate");
			
			getTest_Steps.clear();
			getTest_Steps = rate.CreateRate(driver, RateName, maxAdults, maxPersons, BaseAmount, AddtionalAdult,
					AdditionalChild, DisplayName, RatePolicy, RateDescription, roomClassName, RatePlan);
			test_steps.addAll(getTest_Steps);
			rate.SearchRate(driver, RateName, false);
			test_steps.add("New Rate '" + RateName + "' Created & Verified ");
			app_logs.info("New Rate '" + RateName + "' Created & Verified");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to create new rate", testName, "CreateNewRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to create new rate", testName, "CreateNewRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Verify Rate in Tape Chart
		try {
			app_logs.info("==========VERIFY CREATED RATE IN TAPECHART==========");
			test_steps.add("==========VERIFY CREATED RATE IN TAPECHART==========");
			nav.navigateToReservations(driver);
			test_steps.add("Navigate Reservations");
			app_logs.info("Navigate Reservations");
			nav.ClickTapeChart(driver);
			test_steps.add("Navigate TapeChart");
			app_logs.info("Navigate TapeChart");
			tap.TapeChartRatePlanSearch(driver, test_steps, maxAdults, RatePlan);
			getTest_Steps.clear();
			getTest_Steps = tap.VerifyRate_TapeChart(driver, BaseAmount, RoomClassAbb, RateName, getTest_Steps);
			test_steps.addAll(getTest_Steps);
		} catch (Exception e) {
			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to VERIFY CREATED RATE IN TAPECHART", testName, "VerifyRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to VERIFY CREATED RATE IN TAPECHART", testName, "VerifyRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// VERIFY RATE DETAILS IN RATE DETAIL POPUP
		try {
			app_logs.info("==========VERIFY RATE DETAILS IN RATE DETAIL POPUP==========");
			test_steps.add("==========VERIFY RATE DETAILS IN RATE DETAIL POPUP==========");
			getTest_Steps.clear();
			getTest_Steps = tap.ClickOnRate(driver, RoomClassAbb, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tap.VerifyRateDetailPopup_RateName(driver, RateName, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tap.VerifyRateDetailPopup_RatePlan(driver, RatePlan, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tap.VerifyRateDetailPopup_RateType(driver, RateType, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tap.VerifyRateDetailPopup_RateAttributes(driver, RateAttributes, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tap.VerifyRateDetailPopup_Interval(driver, Interval, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tap.VerifyRateDetailPopup_MaxAdults(driver, maxAdults, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tap.VerifyRateDetailPopup_MaxPersons(driver, maxPersons, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tap.VerifyRateDetailPopup_BaseAmount(driver, BaseAmount + ".00", getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tap.VerifyRateDetailPopup_AdditionalAdult(driver, AddtionalAdult + ".00", getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tap.VerifyRateDetailPopup_AdditionalChild(driver, AdditionalChild + ".00", getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tap.VerifyRateDetailPopup_DisplayName(driver, DisplayName, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tap.VerifyRateDetailPopup_Policy(driver, RatePolicy, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tap.VerifyRateDetailPopup_Description(driver, RateDescription, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tap.VerifyRateDetailPopup_Season(driver, AssociateSession, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tap.VerifyRateDetailPopup_RoomClass(driver, roomClassName, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tap.VerifyRateDetailPopup_Source(driver, Source, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = tap.ClickRateDetailPopupCancelButton(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
		} catch (Exception e) {
			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to VERIFY RATE DETAILS IN RATE DETAIL POPUP", testName,
						"VerifyRateDetails", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to VERIFY RATE DETAILS IN RATE DETAIL POPUP", testName,
						"VerifyRateDetails", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// navigate to Inventory->Overview
		try {
			app_logs.info("==========VERIFY CREATED RATE IN RATES GRID==========");
			test_steps.add("==========VERIFY CREATED RATE IN RATES GRID==========");
			nav.navigateToReservations(driver);
			test_steps.add("Navigate Reservations");
			app_logs.info("Navigate Reservations");
			nav.Inventory(driver);
			test_steps.add("Navigate Inventory");
			app_logs.info("Navigate Inventory");
			nav.Rates_Grid(driver);
			test_steps.add("Navigate Rates Grid");
			app_logs.info("Navigate Rates Grid");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Navigate Rates Grid", testName, "Navigation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Navigate Rates Grid", testName, "Navigation", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// Check Room Classes
		try {
			getTest_Steps.clear();
			getTest_Steps = overview.VerifyRoomClass(driver, roomClassName, BaseAmount, true);
			test_steps.addAll(getTest_Steps);
			app_logs.info("==========VERIFY CREATED RATE VALUES==========");
			test_steps.add("==========VERIFY CREATED RATE VALUES==========");
			getTest_Steps.clear();
			getTest_Steps = overview.VerifyRatePopup(driver, BaseAmount, AddtionalAdult, AdditionalChild);
			test_steps.addAll(getTest_Steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Check RoomClasses", testName, "Verification", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Check RoomClasses", testName, "Verification", driver);
			} else {

				Assert.assertTrue(false);
			}
		}

		// Verify Rate in Tape Chart
		try {
			app_logs.info("==========VERIFY CREATED RATE IN NEW RESERVATION==========");
			test_steps.add("==========VERIFY CREATED RATE IN NEW RESERVATION==========");
			nav.Inventory_Backward_1(driver);
			nav.navigateToReservations(driver);
			test_steps.add("Navigate Reservations");
			app_logs.info("Navigate Reservations");
			cpres.click_NewReservation(driver, test_steps);

			String CheckInDate = Utility.getCurrentDate("MM/dd/yyyy");
			String CheckoutDate = Utility.GetNextDate(1, "MM/dd/yyyy");
			cpres.EnterDate(driver, "start", CheckInDate);
			test_steps.add("Select CheckIn date : " + CheckInDate);
			app_logs.info("Selecting checkin date : " + CheckInDate);
			cpres.EnterDate(driver, "end", CheckoutDate);
			test_steps.add("Select Checkout date : " + CheckoutDate);
			app_logs.info("Selecting checkin date : " + CheckoutDate);
			cpres.enter_Adults(driver, test_steps, Adults);
			cpres.enter_Children(driver, test_steps, Child);
			cpres.select_Rateplan(driver, test_steps, RatePlan, "");
			cpres.clickOnFindRooms(driver, test_steps);
			cpres.VerifyRoomDisplayed(driver, roomClassName, test_steps);
			cpres.VerifyAvgPerNight(driver, roomClassName, BaseAmount, true, test_steps);
			cpres.VerifyRoomClassDescription_Date(driver, roomClassName, Utility.getCurrentDate("MMM dd yyyy"),
					test_steps);
			cpres.VerifyRoomClassDescription_Rate(driver, roomClassName, RateName, test_steps);
			cpres.VerifyRoomClassDescription_Amount(driver, roomClassName, BaseAmount+".00", test_steps);
			cpres.closeReservationTab(driver);
			test_steps.add("Close Reservation");
			app_logs.info("Close Reservation");
		} catch (Exception e) {
			if (Utility.count == RetryFailedTestCases.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to VERIFY CREATED RATE IN RESERVATION", testName, "VerifyRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to VERIFY CREATED RATE IN Reservation", testName, "VerifyRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Clicking on Groups
		try {
			app_logs.info("==========VERIFY CREATED RATE IN GROUP ACCOUNT==========");
			test_steps.add("==========VERIFY CREATED RATE IN GROUP ACCOUNT==========");
			nav.navigateToReservations(driver);
			nav.Groups(driver);
			test_steps.add("Navigate Groups");
			app_logs.info(" Navigate Groups");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Navigate AdvGroup", testName, "NavigateAdvGroup", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Navigate AdvGroup", testName, "NavigateAdvGroup", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Click New Account and Enter Account Name
		try {
			app_logs.info("==========CREATE GROUP ACCOUNT==========");
			test_steps.add("==========CREATE GROUP ACCOUNT==========");
			getTest_Steps.clear();
			group.type_GroupName(driver, test, accountName, getTest_Steps);
			test_steps.addAll(getTest_Steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to click new account and enter account name", testName,
						"EnterAccountName", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to click new account and enter account name", testName,
						"EnterAccountName", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Enter Account Number
		try {
			accountNumber = Utility.GenerateRandomString15Digit();
			getTest_Steps.clear();
			getTest_Steps = group.enterAccountNo(driver, accountNumber);
			test_steps.addAll(getTest_Steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Enter Account Number", testName, "EnterAccountNumber", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Enter Account Number", testName, "EnterAccountNumber", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Enter Account Attributes
		try {
			getTest_Steps.clear();
			group.type_AccountAttributes(driver, test, MarketSegment, GroupReferral, getTest_Steps);
			test_steps.addAll(getTest_Steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Enter Account Attributes", testName, "EnterAccountAttributes",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Enter Account Attributes", testName, "EnterAccountAttributes",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Enter Account Mailing Address
		try {
			getTest_Steps.clear();
			group.type_MailingAddrtess(driver, test, GroupFirstName, GroupLastName, GroupPhn, GroupAddress, Groupcity,
					Groupstate, Groupcountry, GroupPostalcode, getTest_Steps);
			test_steps.addAll(getTest_Steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Enter Account Mailing Address", testName,
						"EnterAccountMailingAddress", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Enter Account Mailing Address", testName,
						"EnterAccountMailingAddress", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Check Mailing Info CheckBox
		try {
			getTest_Steps.clear();
			group.Billinginfo(driver, test, getTest_Steps);
			test_steps.addAll(getTest_Steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Check Mailing Info CheckBox", testName, "CheckMailingInfoCheckBox",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Check Mailing Info CheckBox", testName, "CheckMailingInfoCheckBox",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Save Account
		try {
			getTest_Steps.clear();
			getTest_Steps = group.Save(driver, test, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			test_steps.add("Save group account Successfully");
			app_logs.info("Save group account Successfully");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Save  Account", testName, "SaveAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Save Account", testName, "SaveAccount", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		try {
			app_logs.info("==========CREATE NEW BLOCK==========");
			test_steps.add("==========CREATE NEW BLOCK==========");
			group.navigateRoomBlock(driver, test);
			getTest_Steps.clear();
			getTest_Steps = group.ClickNewBlock(driver);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			getTest_Steps = group.EnterBlockName(driver, BlockName);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			getTest_Steps = group.ClickOkay_CreateNewBlock(driver);
			test_steps.addAll(getTest_Steps);

			app_logs.info("==========SEARCH ROOMS==========");
			test_steps.add("==========SEARCH ROOMS==========");
			getTest_Steps.clear();
			getTest_Steps = group.SelectArrivalDepartureDates(driver, Utility.getCurrentDate("MMM dd, yyyy"),
					Utility.GetNextDate(1, "MMM dd, yyyy"));
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			getTest_Steps = group.SelectRatePlan(driver, RatePlan);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			getTest_Steps = group.SelectAdults(driver, Adults);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			getTest_Steps = group.SelectChilds(driver, Child);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			getTest_Steps = group.EnterNights(driver, RoomPerNight);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			getTest_Steps = group.ClickSearchGroup(driver);
			test_steps.addAll(getTest_Steps);

			app_logs.info("==========VERIFY ROOM CLASS==========");
			test_steps.add("==========VERIFY ROOM CLASS==========");
			getTest_Steps.clear();
			getTest_Steps = group.VerifyRoomClass(driver, roomClassName, BaseAmount);
			test_steps.addAll(getTest_Steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Create Block and Verufy RoomClass", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Create Block and Verufy RoomClass", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Delete rate
		try {
			app_logs.info("==========DELETE RATE==========");
			test_steps.add("==========DELETE RATE==========");
			nav.Inventory(driver);
			app_logs.info("Navigate Inventory");
			test_steps.add("Navigate Inventory");
			
			getTest_Steps.clear();
			getTest_Steps = nav.secondaryRatesMenuItem(driver);
			test_steps.addAll(getTest_Steps);
			
			rate.SearchRate(driver, RateName, false);
			test_steps.add("New Rate has been Searched successfully");
			app_logs.info("New Rate has been Searched successfully");
			
			rate.deleteRates(driver, RateName);
			test_steps.add("New Rate has been Deleted successfully");
			app_logs.info("New Rate has been Deleted successfully");
			
			rate.verifyDeleteRate(driver, RateName);
			test_steps.add("Verify the Deleted Rate : " + RateName);
			app_logs.info("Verify the Deleted Rate " + RateName);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Delete rate", testName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Delete rate", testName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Delete Room Class
		try {
			app_logs.info("==========DELETE ROOM CLASS==========");
			test_steps.add("==========DELETE ROOM CLASS==========");
			nav.Setup(driver);
			app_logs.info("Navigate Setup");
			test_steps.add("Navigate Setup");
			
			nav.RoomClass(driver);
			app_logs.info("Navigate Room Class");
			test_steps.add("Navigate Room Class");
			
			roomClass.searchClass(driver, tempraryRoomClassName);
			app_logs.info("Search");
			roomClass.deleteRoomClass(driver, tempraryRoomClassName);
			test_steps.add("Delete room classes that start with name of "+tempraryRoomClassName);
			app_logs.info("Delete room classes that start with name of "+tempraryRoomClassName);
			
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to delete room class", testName, "RoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to delete room class", testName, "RoomClass", driver);
			}
		}
	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheet name provided
		return Utility.getData("VerifyNewBaseRateCreation", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		 driver.quit();
	}
}
