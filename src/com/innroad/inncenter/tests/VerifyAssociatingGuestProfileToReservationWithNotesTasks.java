package com.innroad.inncenter.tests;

import java.io.IOException;
import java.util.ArrayList;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.GuestHistory;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.TaskList;
import com.innroad.inncenter.properties.OR_Reservation;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_CPReservation;

public class VerifyAssociatingGuestProfileToReservationWithNotesTasks extends TestCore {

	private WebDriver driver = null;
	public static String test_name = "";
	public static String testDescription = "";
	public static String testCategory = "";
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> getTest_Steps = new ArrayList<>();
	ArrayList<String> TestName = new ArrayList<>();
	ArrayList<String> TestCategory = new ArrayList<>();
	ArrayList<String> TestDescription = new ArrayList<>();

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void verifyAssociatingGuestProfileToReservationWithNotesTasks(String url, String ClientCode, String Username,
			String Password, String CheckInDate, String CheckOutDate, String Adults, String Children, String Rateplan,
			String PromoCode, String IsAssign, String IsDepositOverride, String DepositOverrideAmount,
			String Salutation, String GuestFirstName, String GuestLastName, String FirstName, String LastName,
			String PhoneNumber, String AlternativePhone, String Email, String Account, String AccountType,
			String Address1, String Address2, String Address3, String City, String Country, String State,
			String PostalCode, String IsGuesProfile, String PaymentType, String CardNumber, String NameOnCard,
			String CardExpDate, String IsChangeInPayAmount, String ChangedAmountValue, String TravelAgent,
			String MarketSegment, String Referral, String IsAddNotes, String NoteType, String Subject,
			String Description, String IsTask, String TaskCategory, String TaskType, String TaskDetails,
			String TaskRemarks, String TaskDueon, String TaskAssignee, String TaskStatus, String ReplaceTheGuestInfo,
			String guestProfileName, String GuestPhoneNumber, String GuestAlternativePhone, String GuestEmail,
			String GuestAddressLine1, String GuestAddressLine2, String GuestAddressLine3, String GuestCity,
			String GuestCountry, String GuestState, String GuestPostalCode, String GuestMarketSegment,
			String GuestReferral, String RoomClassAbb, String RoomClassName, String bedsCount, String maxAdults,
			String maxPersons, String roomQuantity, String Season, String RatePlan, String RateName, String BaseAmount,
			String AddtionalAdult, String AdditionalChild, String DisplayName, String AssociateSource,
			String RatePolicy, String RateDescription) throws InterruptedException, IOException {

		test_name = "VerifyAssociatingGuestProfileToReservationWithNotesTasks";
		testDescription = "Verify Associating GuestProfile To Reservation With Notes  & Tasks<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682523' target='_blank'><br>"
				+ "Click here to open TestRail: C682523</a>";

		testCategory = "VerifyGuestProfileBehavior";
		String testName = test_name;

		TestName.add(testName);
		TestDescription.add(testDescription);
		TestCategory.add(testCategory);
		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		ReservationSearch reser_search = new ReservationSearch();
		CPReservationPage res = new CPReservationPage();
		Navigation navigation = new Navigation();
		GuestHistory guestHistory = new GuestHistory();
		RoomClass room_class = new RoomClass();
		Rate rate = new Rate();
		Double depositAmount = 0.0;
		String reservationNumber = null;
		String roomNumber = null;
		String reservationStatus = null;
		GuestLastName = GuestLastName + Utility.GenerateRandomNumber();
		RateName = RateName + Utility.GenerateRandomNumber();
		RoomClassName = RoomClassName + Utility.GenerateRandomNumber();
		String Date = Utility.getCurrentDate("MM/dd/ YY");

		try {

			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);

			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver = getDriver();
			login_CP(driver);
			test_steps.add("Logged into the application");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to login", "GS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);

			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to login", "GS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("======Create RoomClass=======");
			test_steps.add("======Create RoomClass=======");
			navigation.Setup(driver);
			app_logs.info("Navigate Setup");
			test_steps.add("Navigate Setup");
			navigation.RoomClass(driver);
			app_logs.info("Navigate RoomClass");
			test_steps.add("Navigate RoomClass");
			navigation.NewRoomClass(driver);
			app_logs.info("NewRoomClass Button Clicked");
			test_steps.add("NewRoomClass Button Clicked");
			getTest_Steps.clear();
			getTest_Steps = room_class.Create_RoomClass(driver, RoomClassName, RoomClassAbb, bedsCount, maxAdults,
					maxPersons, roomQuantity, test, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			room_class.CloseOpenedRoomClass(driver, RoomClassName, getTest_Steps);
			test_steps.add("Sccessfully Created New RoomClass  : " + RoomClassName);
			app_logs.info("Sccessfully Created New RoomClass :  " + RoomClassName);
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
		try {
			app_logs.info("======Create Rate=======");
			test_steps.add("======Create Rate=======");
			navigation.Reservation_Backward_1(driver);
		
			test_steps.add("==========CREATE A NEW RATE==========");
			app_logs.info("==========CREATE A NEW RATE==========");
			
			
			navigation.Inventory(driver);
			app_logs.info("Navigate Inventory");
			test_steps.add("Navigate Inventory");

			getTest_Steps.clear();
			getTest_Steps = navigation.secondaryRatesMenuItem(driver);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = rate.ClickNewRate(driver);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = rate.EnterRateName(driver,RateName);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = rate.SelectRatePlan(driver, RatePlan);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = rate.EnterMaxAdults(driver, maxAdults);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = rate.EnterMaxPersons(driver, maxPersons);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = rate.EnterBaseAmount(driver, BaseAmount);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = rate.EnterAdditionalAdult(driver, AddtionalAdult);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = rate.EnterAdditionalChild(driver, AdditionalChild);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = rate.EnterRateDisplayName(driver, RateName);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = rate.EnterRatePolicy(driver, RateName);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = rate.EnterRateDescription(driver, RateName);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = rate.AssociateSeason(driver, Season);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = rate.AssociateRoomClass(driver, RoomClassName);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = rate.AssociateSource(driver,AssociateSource);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = rate.Save_DoneRate(driver);
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
		// Create Reservation

		try {
			app_logs.info("======CP Reservation Creation=======");
			test_steps.add("======CP Reservation Creation=======");
			navigation.Reservation_Backward_1(driver);
			res.click_NewReservation(driver, test_steps);
			res.CheckInDate(driver, 1);
			res.CheckOutDate(driver, 1);
			res.enter_Adults(driver, test_steps, Adults);
			res.enter_Children(driver, test_steps, Children);
			res.select_Rateplan(driver, test_steps, Rateplan, PromoCode);
			res.clickOnFindRooms(driver, test_steps);
			System.out.print("ISAssigne:" + IsAssign);
			res.selectRoom(driver, RoomClassName, IsAssign);
			depositAmount = res.deposit(driver, test_steps, IsDepositOverride, DepositOverrideAmount);
			res.clickNext(driver, test_steps);
			res.enter_ContactName(driver, getTest_Steps, "", FirstName, LastName);
			res.enterMailingAddressWithEmail(driver, test_steps, Salutation, GuestFirstName, GuestLastName, PhoneNumber,
					AlternativePhone, Email, Account, AccountType, Address1, Address2, Address3, City, Country, State,
					PostalCode, IsGuesProfile);
			res.enter_PaymentDetails(driver, test_steps, PaymentType, CardNumber, NameOnCard, CardExpDate);
			res.enter_MarketSegmentDetails(driver, test_steps, TravelAgent, MarketSegment, Referral);
			res.enter_Notes(driver, test_steps, IsAddNotes, NoteType, Subject, Description);
			res.clickBookNow(driver, test_steps);
			reservationNumber = res.get_ReservationConfirmationNumber(driver, test_steps);
			reservationStatus = res.get_ReservationStatus(driver, test_steps);
			res.clickCloseReservationSavePopup(driver, test_steps);
			roomNumber = res.get_RoomNumber(driver, test_steps, IsAssign);
			res.closeFirstOpenedReservationUnSavedData(driver, test_steps);

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Create Reservation ", testName, "CreateReservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Create Reservation", testName, "CreateReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		GuestFirstName = GuestFirstName + Utility.GenerateRandomNumber();
		GuestLastName = GuestLastName + Utility.GenerateRandomNumber();
		guestProfileName = GuestFirstName + " " + GuestLastName;
		String accountNumber;
		try {
			app_logs.info("======Guest Profile Creation=======");
			test_steps.add("======Guest Profile Creation=======");
			navigation.GuestHistory(driver);
			app_logs.info("Navigate To GuestProfile");
			test_steps.add("Navigate To GuestProfile");
			guestHistory.guestHistory_NewAccount(driver);
			app_logs.info("Click To New Account Button");
			test_steps.add("Click To New Account Button");
			guestHistory.enterFirstLastName(driver, GuestFirstName, GuestLastName, test_steps);
			//guestHistory.EnterFirstLastNameSeperately(driver, GuestFirstName, GuestLastName, test_steps);
			guestHistory.accountAttributes(driver, GuestMarketSegment, GuestReferral, test_steps);
			getTest_Steps.clear();
			getTest_Steps = guestHistory.Mailinginfo(driver, GuestFirstName, GuestLastName, GuestPhoneNumber,
					GuestAlternativePhone, GuestAddressLine1, GuestAddressLine2, GuestAddressLine3, GuestEmail,
					GuestCity, GuestState, GuestPostalCode);
			test_steps.addAll(getTest_Steps);
			guestHistory.Billinginfo(driver, test_steps);
			guestHistory.Save(driver);
			app_logs.info("Save Button Clicked");
			test_steps.add("Save Button Clicked");
			accountNumber = guestHistory.GetAccountNumber(driver);
			app_logs.info("Successfully Created Guest History Account " + accountNumber);
			test_steps.add("Successfully Created Guest History Account " + accountNumber);
			res.closeFirstOpenedReservationUnSavedData(driver, test_steps);

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Create Guest Histroy Account ", testName, "GuestHistoryAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Create Guest Histroy Account", testName, "GuestHistoryAccount",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			navigation.Reservation_Backward_1(driver);
			app_logs.info("Navigate To Reservation");
			test_steps.add("Navigate To Reservation");
			reser_search.SearchAndOpenRes(driver, reservationNumber);
			app_logs.info("Successfully Searched & Opened Reservation " + reservationNumber);
			test_steps.add("Successfully Searched &Opened Reservation " + reservationNumber);
			app_logs.info("======Add Task & Verify=======");
			test_steps.add("=====Add  Task & Verify=======");
			res.createTaskWithVerification(driver, test_steps, false, true, TaskCategory, TaskType, TaskDetails,
					TaskRemarks, TaskDueon, TaskAssignee, TaskStatus);
			res.close_FirstOpenedReservation(driver, test_steps);
			driver.navigate().refresh();
			reser_search.SearchAndOpenRes(driver, reservationNumber);
			res.VerifyCreatedTask(driver, TaskType, TaskDetails, TaskStatus, test_steps);
			res.closeFirstOpenedReservationUnSavedData(driver, test_steps);

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Attach Guest Profile To Reservation ", testName, "GuestProfile",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Attach Guest Profile To Reservation", testName, "GuestProfile",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("======Attach Guest Profile=======");
			test_steps.add("=====Attach Guest Profile=======");
			driver.navigate().refresh();
			reser_search.SearchAndOpenRes(driver, reservationNumber);
			app_logs.info("Successfully Searched & Opened Reservation " + reservationNumber);
			test_steps.add("Successfully Searched &Opened Reservation " + reservationNumber);
			res.clickGuestInfoEditDetailsButton(driver, test_steps);
			res.attachGuestProfile(driver, test_steps, guestProfileName, ReplaceTheGuestInfo);
			getTest_Steps.clear();
			getTest_Steps = res.verfifyGuestProfileAttachedInfo(driver, guestProfileName, guestProfileName,
					GuestAddressLine1, GuestAddressLine2, GuestAddressLine3, GuestEmail, GuestPhoneNumber,
					GuestAlternativePhone, GuestCity, GuestCountry, GuestState, GuestPostalCode, GuestMarketSegment,
					GuestReferral);
			test_steps.addAll(getTest_Steps);
			app_logs.info("Successfully Verified Attched GuestProfile: " + guestProfileName);
			test_steps.add("Successfully Verified Attched GuestProfile: " + guestProfileName);
			res.closeFirstOpenedReservationUnSavedData(driver, test_steps);
			reser_search.SearchAndOpenRes(driver, reservationNumber);
			app_logs.info("Successfully Searched & Opened Reservation " + reservationNumber);
			test_steps.add("Successfully Searched & Opened Reservation " + reservationNumber);
			Subject = "New Notes Subject";
			Description = "New Notes Description";
			res.enter_Notes(driver, test_steps, IsAddNotes, NoteType, Subject, Description);
			getTest_Steps.clear();
			getTest_Steps = res.verifyNotes(driver, NoteType, Subject, Description);
			test_steps.addAll(getTest_Steps);
			res.VerifyCreatedTask(driver, TaskType, TaskDetails, TaskStatus, test_steps);
			res.closeFirstOpenedReservationUnSavedData(driver, test_steps);

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify Notes & Task To Reservation ", testName, "GuestProfile",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify Notes & Task To Reservation", testName, "GuestProfile",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		String emailSubject;
		String emailDescription;
		try {
			app_logs.info("======Send Email=======");
			test_steps.add("=====Send Email=======");
			driver.navigate().refresh();
			reser_search.SearchAndOpenRes(driver, reservationNumber);
			app_logs.info("Successfully Searched & Opened Reservation " + reservationNumber);
			test_steps.add("Successfully Searched &Opened Reservation " + reservationNumber);
			res.clickSendEmailButton(driver, test_steps);
			emailSubject =  reservationNumber;
			emailDescription = res.getEmailDescription(driver);
			Email = "innRoadTestEmail@innroad.com";
			NoteType = "Email";
			res.SendEmail(driver, Email, test_steps);
			app_logs.info("======Verify Auto Generated Email Note=======");
			test_steps.add("=====Verify Auto Generated Email Note=======");
			getTest_Steps = res.verifyAutoGeneratedNotes(driver, NoteType, emailSubject, emailDescription, Date,
					"auto guest");
			test_steps.addAll(getTest_Steps);
			app_logs.info("======Change Reservation Status=======");
			test_steps.add("=====Change Reservation Status=======");
			reservationStatus = "Cancel";
			res.change_ReservationStatus(driver, test_steps, reservationStatus);
			NoteType = "Cancellation";
			Subject = "Cancellation Reason";
			app_logs.info("======Verify Auto Generated Cancellation Note=======");
			test_steps.add("=====Verify Auto Generated Cancellation Note=======");
			getTest_Steps.clear();
			getTest_Steps = res.verifyAutoGeneratedNotes(driver, NoteType, Subject, reservationStatus, Date,
					"auto guest");
			test_steps.addAll(getTest_Steps);

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify Notes & Task To Reservation ", testName, "GuestProfile",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify Notes & Task To Reservation", testName, "GuestProfile",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("======Delete Rate=======");
			test_steps.add("======Delete Rate=======");
			driver.navigate().refresh();
			navigation.Inventory(driver);
			test_steps.add("Navigate to Inventory");
			app_logs.info("Navigate to Inventory");

			// create new method
			getTest_Steps.clear();
			getTest_Steps = navigation.secondaryRatesMenuItem(driver);
			test_steps.addAll(getTest_Steps);

			// start new method for delete rate
			getTest_Steps.clear();
			getTest_Steps = rate.deleteRates(driver, RateName);
			getTest_Steps.addAll(getTest_Steps);

			test_steps.add("New Rate has been Deleted successfully");
			app_logs.info("New Rate has been Deleted successfully");

			rate.verifyDeleteRate(driver, RateName);
			test_steps.add("Verify the Deleted Rate : " + RateName);
			app_logs.info("Verify the Deleted Rate " + RateName);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to delete a rate ", testName, "DeleteRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to delete a rate ", testName, "DeleteRate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("======Delete RoomClass=======");
			test_steps.add("======Delete RoomClass=======");
			navigation.Setup(driver);
			navigation.RoomClass(driver);
			room_class.searchClass(driver, RoomClassName);
			app_logs.info("Search");
			room_class.deleteRoomClass(driver, RoomClassName);

			test_steps.add("Sccessfully Deleted RoomClass  : " + RoomClassName);
			app_logs.info("Sccessfully Deleted RoomClass :  " + RoomClassName);
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to delete room class ", testName, "RoomClass Delete", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to delete room class successfully", testName, "RoomClass Delete",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {

		// return test data from the sheet name provided

		return Utility.getData("VerifyAssociatGuestProfToRes", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		 driver.quit();
	}

}
