package com.innroad.inncenter.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.Account;
import com.innroad.inncenter.pageobjects.AdvGroups;
import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Folio;
import com.innroad.inncenter.pageobjects.Groups;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.Reservation;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class VerifyAdvanceGroupMRBReservation extends TestCore {

	private WebDriver driver = null;

	ArrayList<String> TestCategory = new ArrayList<>();
	ArrayList<String> TestDescription = new ArrayList<>();
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> getTest_Steps = new ArrayList<>();
	ArrayList<String> TestName = new ArrayList<>();

	// Before Test
	@BeforeTest
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	public void login(String testName) {

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
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {

				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@Test(dataProvider = "getData", groups = { "groups" })
	public void verifyAdvanceGroupMRBReservation(String url, String clientcode, String username, String password,
			String AccountName, String MargetSegment, String Referral, String AccountFirstName, String AccountLastName,
			String Phonenumber, String Address1, String city, String Country, String State, String Postalcode,
			String BlockName, String RoomPerNight, String roomClassName, String RoomClassAbb, String bedsCount,
			String maxAdults, String maxPersons, String roomQuantity, String RateName, String BaseAmount,
			String AddtionalAdult, String AdditionalChild, String DisplayName, String AssociateSession,
			String RatePolicy, String RateDescription, String PrimaryGuestFirstName, String PrimaryGuestLastName,
			String SecondaryGuestFirstName, String SecondaryGuestLastName) throws InterruptedException, IOException {

		String testName = "VerifyAdvanceGroupMRBReservation";
		String test_description = "Verify workflow for creating MRB from Group Block.<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682475' target='_blank'>"
				+ "Click here to open TestRail: C682475</a><br/>";
		String test_catagory = "Groups";
		TestName.add(testName);
		TestDescription.add(test_description);
		TestCategory.add(test_catagory);
		String backupBaseRate = BaseAmount;
		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		login(testName);

		Navigation Nav = new Navigation();
		Groups group = new Groups();
		CPReservationPage res = new CPReservationPage();
		Navigation nav = new Navigation();
		RoomClass room_class = new RoomClass();
		Rate rate = new Rate();
		Folio folio = new Folio();
		AdvGroups advGrp = new AdvGroups();
		// roomClassName = "GroupMRBSuite2342020104820";
		Account acc = new Account();
		try {
			app_logs.info("==========ROOM CLASS CREATION==========");
			test_steps.add("==========ROOM CLASS CREATION==========");
			nav.Setup(driver);
			test_steps.add("Navigate to Setup");
			app_logs.info("Navigate to Setup");
			nav.RoomClass(driver);
			test_steps.add("Navigate to Room Class");
			app_logs.info("Navigate to Room Class");
			roomClassName = (roomClassName + Utility.getTimeStamp()).replaceAll("_", "");
			RoomClassAbb = (RoomClassAbb + Utility.getTimeStamp()).replaceAll("_", "");
			try {
				// Old Page Layout
				Utility.app_logs.info("try");

				nav.NewRoomClass(driver);
				try {
					getTest_Steps.clear();
					getTest_Steps = room_class.CreateRoomClass(driver, roomClassName, RoomClassAbb, bedsCount,
							maxAdults, maxPersons, roomQuantity, test, getTest_Steps);
					test_steps.addAll(getTest_Steps);
				} catch (Exception e) {
					getTest_Steps.clear();

					room_class.roomClassInfo1(driver, roomClassName, RoomClassAbb, bedsCount, maxAdults, maxPersons,
							roomQuantity, test, getTest_Steps);
					test_steps.addAll(getTest_Steps);

				}

			} catch (Exception e) {
				// New Page Layout
				Utility.app_logs.info("catch");
				nav.NewRoomClass1(driver);
				room_class.roomClassInfoNewPage(driver, roomClassName, RoomClassAbb, bedsCount, maxAdults, maxPersons,
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
			app_logs.info("==========ASSOCIATE ROOM CLASS TO NEW RATE==========");
			test_steps.add("==========ASSOCIATE ROOM CLASS TO NEW RATE==========");
			try {
				nav.Reservation_Backward_1(driver);
			} catch (Exception e) {
				nav.Reservation_Backward_3(driver);
			}
			nav.Inventory(driver);
			test_steps.add("Navigate to Inventory");
			app_logs.info("Navigate to Inventory");
			nav.Rates1(driver);
			test_steps.add("Navigate to Rates");
			app_logs.info("Navigate to Rates");
			RateName = (RateName + Utility.getTimeStamp()).replaceAll("_", "");
			DisplayName = RateName;
			getTest_Steps.clear();
			getTest_Steps = rate.enterRateInfo(driver, RateName, maxAdults, maxPersons, BaseAmount, AddtionalAdult,
					AdditionalChild, DisplayName, RatePolicy, RateDescription, roomClassName, AssociateSession);
			test_steps.addAll(getTest_Steps);
			test_steps.add("Enter all require details and save");
			app_logs.info("Enter all require details and save");
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

		// Create New Groups
		String AccountNo = "0";
		try {
			app_logs.info("==========CREATE GROUP ACCOUNT==========");
			test_steps.add("==========CREATE GROUP ACCOUNT==========");
			
			Nav.navigateToReservations(driver);
			Nav.Groups(driver);

//			getTest_Steps.clear();
//			getTest_Steps = group.Search_Account(driver, AccountName, "", true, true);
//			test_steps.addAll(getTest_Steps);
//			
//			getTest_Steps.clear();
//			getTest_Steps = group.changeActiveStatus(driver, "Inactive");
//			test_steps.addAll(getTest_Steps);
//
//			getTest_Steps.clear();
//			group.done(driver);
//			test_steps.addAll(getTest_Steps);

			AccountName = AccountName + Utility.GenerateRandomString15Digit();
			getTest_Steps.clear();
			group.type_GroupName(driver, test, AccountName, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			AccountNo = Utility.GenerateRandomString15Digit();
			getTest_Steps.clear();
			getTest_Steps = group.enterAccountNo(driver, AccountNo);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			group.type_AccountAttributes(driver, test, MargetSegment, Referral, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			AccountFirstName = AccountFirstName + Utility.GenerateRandomNumber();
			AccountLastName = AccountLastName + Utility.GenerateRandomNumber();
			
			getTest_Steps.clear();
			group.type_MailingAddrtess(driver, test, AccountFirstName, AccountLastName, Phonenumber, Address1, city,
					State, Country, Postalcode, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			group.Billinginfo(driver, test, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			group.Save(driver, test, getTest_Steps);
			test_steps.addAll(getTest_Steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Create Group", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Create Group", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Create RoomBlock

		try {
			app_logs.info("==========CREATE ROOM BLOCK==========");
			test_steps.add("==========CREATE ROOM BLOCK==========");
			
			group.navigateRoomBlock(driver, test);
			Utility.app_logs.info("Navigate to Room Block Tab");
			test_steps.add("Navigate to Room Block Tab");
			
			BlockName = BlockName + Utility.GenerateRandomNumber();
			
			getTest_Steps.clear();
			getTest_Steps = group.createNewBlock(driver, BlockName, RoomPerNight, roomClassName);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = group.clickCreateBlock(driver);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			group.Save(driver, test, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			group.navigateRoomBlock(driver, test);
			Utility.app_logs.info("Navigate to Room Block Tab");
			test_steps.add("Navigate to Room Block Tab");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========VERIFY BLOCK DETAIL==========");
			test_steps.add("==========VERIFY BLOCK DETAIL==========");

			String RoomBlocked = group.getRoomBlocked_RoomBlockDetatil(driver);
			Utility.app_logs.info("Room Block Detail Room Blocked  : " + RoomBlocked);
			test_steps.add("Room Block Detail Room Blocked  : " + RoomBlocked);
			assertEquals(RoomBlocked, RoomPerNight, "Failed Room Blocked Not Matched");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {

			test_steps.add(e.toString());

		}

		try {
			String totalRoomNight = group.getTotalRoomNights_RoomBlockDetail(driver);
			Utility.app_logs.info("Room Block Detail Total Room Nights  : " + totalRoomNight);
			test_steps.add("Room Block Detail Total Room Nights  : " + totalRoomNight);
			assertEquals(totalRoomNight, RoomPerNight, "Failed Total Room Nights Not Matched");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {

			test_steps.add(e.toString());

		}

		try {
			String expectedRevenueDetail = group.getExpectedRevenue_RoomBlockDetail(driver);
			Utility.app_logs.info("Room Block Detail Expected Revenue  : " + expectedRevenueDetail);
			test_steps.add("Room Block Detail ExpectedRevenue  : " + expectedRevenueDetail);

			String expectedRevenueInfo = group.getExpectedRevenue_GroupInfo(driver);
			Utility.app_logs.info("Before Group Info Expected Revenue  : " + expectedRevenueInfo);
			test_steps.add("Before Group Info ExpectedRevenue  : " + expectedRevenueInfo);
			assertEquals(expectedRevenueDetail, expectedRevenueInfo, "Failed Expected Revenue Not Matched");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {

			test_steps.add(e.toString());

		}

		try {
			String pickUpPercentage = group.getPickUpPercentage_RoomBlockDetatil(driver);
			Utility.app_logs.info("Room Block Detail PickUp Percentage  : " + pickUpPercentage);
			test_steps.add("Room Block Detail PickUp Percentage  : " + pickUpPercentage);
			assertEquals(pickUpPercentage, "0","Failed To Verify PickUp Percentage");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {

			test_steps.add(e.toString());

		}
		
		try {
			String BookIconClass = group.getBookIconClass(driver, roomClassName);
			Utility.app_logs.info("Before BookIcon Class : " + BookIconClass);
			test_steps.add("Before BookIcon Class : " + BookIconClass);
			assertEquals(BookIconClass, "book", "Book Icon Color Not Matched");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {

			test_steps.add(e.toString());

		}
	

		// navigate Group reservation tab
		try {
			getTest_Steps.clear();
			advGrp.click_GroupsReservationTab(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = group.verifyReservationCount(driver, 0);
			test_steps.addAll(getTest_Steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify Resrvation", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify Resrvation", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========BOOK ICON CLICK==========");
			test_steps.add("==========BOOK ICON CLICK==========");
			
			group.navigateRoomBlock(driver, test);
			Utility.app_logs.info("Navigate to Room Block Tab");
			test_steps.add("Navigate to Room Block Tab");

			getTest_Steps.clear();
			getTest_Steps = group.bookIconClick(driver, roomClassName);
			test_steps.addAll(getTest_Steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		String resNo = null, status = null;
		try {
			app_logs.info("==========CREATE RESERVATION==========");
			test_steps.add("==========CREATE RESERVATION==========");
			
			getTest_Steps.clear();
			getTest_Steps = res.verifyAssociatedAccount_ResHeader(driver, AccountName);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = res.clickPencilIcon_GoBackToStayInfo(driver);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.clickAddSRoomButtton(driver);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.clickOnFindRooms(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			res.selectRoomNumber(driver, roomClassName, "1", false, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.clickSelectRoomButtton(driver, roomClassName);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.newPolicesApplicableYesBtn(driver);
			test_steps.addAll(getTest_Steps);

			Wait.waitForCPReservationLoading(driver);

			getTest_Steps.clear();
			res.selectRoomNumber(driver, roomClassName, "2", false, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.clickSelectRoomButtton(driver, roomClassName);
			test_steps.addAll(getTest_Steps);

			res.clickNext(driver, test_steps);

			getTest_Steps.clear();
			getTest_Steps = res.selectPrimaryRoom(driver, roomClassName, "1");
			test_steps.addAll(getTest_Steps);

			PrimaryGuestFirstName = PrimaryGuestFirstName + Utility.GenerateRandomNumber();
			PrimaryGuestLastName = PrimaryGuestLastName + Utility.GenerateRandomNumber();
			
//			getTest_Steps.clear();
//			getTest_Steps = res.selectSalutation(driver, "Mr.");
//			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = res.enterPrimaryGuestName(driver, PrimaryGuestFirstName, PrimaryGuestLastName);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.selectAdditionalRoom(driver, roomClassName, "2");
			test_steps.addAll(getTest_Steps);

			SecondaryGuestFirstName = SecondaryGuestFirstName + Utility.GenerateRandomNumber();
			SecondaryGuestLastName = SecondaryGuestLastName + Utility.GenerateRandomNumber();
			
			getTest_Steps.clear();
			getTest_Steps = res.enterAdditionalGuestName(driver, SecondaryGuestFirstName, SecondaryGuestLastName);
			test_steps.addAll(getTest_Steps);

			res.clickBookNow(driver, test_steps);
			resNo = res.get_ReservationConfirmationNumber(driver, test_steps);
			status = res.get_ReservationStatus(driver, test_steps);
			res.clickCloseReservationSavePopup(driver, test_steps);

			getTest_Steps.clear();
			getTest_Steps = res.verifyAccountName(driver, AccountName,false);
			test_steps.addAll(getTest_Steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed  ", testName, "GroupCPReservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed ", testName, "GroupCPReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		try {
			app_logs.info("==========VERIFY GUEST DETAIL SAME AS ACCOUNT==========");
			test_steps.add("==========VERIFY GUEST DETAIL SAME AS ACCOUNT==========");
			
			getTest_Steps.clear();
			getTest_Steps = res.verfifyGuestinfo_ResDetail(driver, PrimaryGuestFirstName + " " + PrimaryGuestLastName, Address1, city, State, Country, Postalcode, "-", "1" + Phonenumber);
			test_steps.addAll(getTest_Steps);
			
			String foundContactName = res.getContactName_ResDetail(driver);
			assertEquals(foundContactName, AccountFirstName + " " + AccountLastName,"Failed to Verrify Contact Name ");
			app_logs.info("Successfully Verified Contact Name in Guest Detail same as Accounts : " + foundContactName);
			test_steps.add("Successfully Verified Contact Name in Guest Detail same as Accounts : " + foundContactName);
			
			String foundAccount = res.getAccount_ResDetail(driver);
			assertEquals(foundAccount, AccountName,"Failed To Verify Account Name");
			app_logs.info("Successfully Verified Account Name in Guest Detail : " + foundAccount);
			test_steps.add("Successfully Verified Account Name in Guest Detail : " + foundAccount);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed  ", testName, "GroupCPReservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed ", testName, "GroupCPReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			res.click_Folio(driver, test_steps);

			String folioCount = res.getFolioOptionCount(driver);
			assertEquals(folioCount, "3", "Failed to Vrify Total Folio Options Count");
			test_steps.add("Successfully Verified Folio Option Count : " + folioCount);
			Utility.app_logs.info("Successfully Verified Folio Option Count : " + folioCount);

			app_logs.info("==========VERIFY ACCOUNT FOLIO LINE ITEM==========");
			test_steps.add("==========VERIFY ACCOUNT FOLIO LINE ITEM==========");

			getTest_Steps.clear();
			getTest_Steps = res.selectFolioOption(driver, AccountName);
			test_steps.addAll(getTest_Steps);

			int folioLineitemCount = folio.verifyLineItemCount(driver);
			assertEquals(folioLineitemCount, 0, "Failed to Vrify Total Folio Line Item Count");
			test_steps.add("Successfully Verified Group Account Folio Line Item Count : " + folioLineitemCount);
			Utility.app_logs.info("Successfully Verified Group Account Folio Line Item Count : " + folioLineitemCount);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed  ", testName, "GroupCPReservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed ", testName, "GroupCPReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		String foundPaymentMethod = "";
		String lineCat = "Room Charge";
		try {
			getTest_Steps.clear();
			getTest_Steps = res.selectFolioOption(driver, 1);
			test_steps.addAll(getTest_Steps);

			app_logs.info("==========VERIFY FOLIO LINE ITEM==========");
			test_steps.add("==========VERIFY FOLIO LINE ITEM==========");

			int folioLineitemCount = folio.verifyLineItemCount(driver);
			assertEquals(folioLineitemCount, 1, "Failed to Vrify Total Folio Line Item Count");
			test_steps.add("Successfully Verified Guest Folio Line Item Count : " + folioLineitemCount);
			Utility.app_logs.info("Successfully Verified Guest Folio Line Item Count : " + folioLineitemCount);

			getTest_Steps.clear();
			foundPaymentMethod = folio.verifyLineItemCategory(driver, lineCat, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			String tax = folio.getLineTaxAmount(driver, foundPaymentMethod);
			BaseAmount = (Float.parseFloat(BaseAmount) + Float.parseFloat(tax)) + "";
			getTest_Steps.clear();
			getTest_Steps = folio.verifyLineItemDate(driver, foundPaymentMethod);
			test_steps.addAll(getTest_Steps);

//			getTest_Steps.clear();
//			getTest_Steps = folio.verifyLineItemDesc(driver, foundPaymentMethod,DisplayName);
//			test_steps.addAll(getTest_Steps);
//			
			getTest_Steps.clear();
			getTest_Steps = folio.verifyLineItemAmount(driver, foundPaymentMethod, BaseAmount);
			test_steps.addAll(getTest_Steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed  ", testName, "GroupCPReservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed ", testName, "GroupCPReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			getTest_Steps.clear();
			getTest_Steps = res.selectFolioOption(driver, 2);
			test_steps.addAll(getTest_Steps);

			app_logs.info("==========VERIFY FOLIO LINE ITEM==========");
			test_steps.add("==========VERIFY FOLIO LINE ITEM==========");

			int folioLineitemCount = folio.verifyLineItemCount(driver);
			assertEquals(folioLineitemCount, 1, "Failed to Vrify Total Folio Line Item Count");
			test_steps.add("Successfully Verified Guest Folio Line Item Count : " + folioLineitemCount);
			Utility.app_logs.info("Successfully Verified Guest Folio Line Item Count : " + folioLineitemCount);

			getTest_Steps.clear();
			foundPaymentMethod = folio.verifyLineItemCategory(driver, lineCat, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			// String tax = folio.getLineTaxAmount(driver, foundPaymentMethod);
			// BaseAmount = (Float.parseFloat(BaseAmount)+Float.parseFloat(tax))+"";
			getTest_Steps.clear();
			getTest_Steps = folio.verifyLineItemDate(driver, foundPaymentMethod);
			test_steps.addAll(getTest_Steps);

//			getTest_Steps.clear();
//			getTest_Steps = folio.verifyLineItemDesc(driver, foundPaymentMethod,DisplayName);
//			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.verifyLineItemAmount(driver, foundPaymentMethod, BaseAmount);
			test_steps.addAll(getTest_Steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed  ", testName, "GroupCPReservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed ", testName, "GroupCPReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			 res.click_DeatilsTab(driver, test_steps);
			 app_logs.info("==========VERIFY RESERVATION HEADER DETAIL==========");
				test_steps.add("==========VERIFY RESERVATION HEADER DETAIL==========");
				
				getTest_Steps.clear();
				getTest_Steps = res.verifyGuestNameAfterReservation(driver, PrimaryGuestFirstName + " " + PrimaryGuestLastName);
				test_steps.addAll(getTest_Steps);
				
				getTest_Steps.clear();
				getTest_Steps = res.verifyAccountName(driver, AccountName,false);
				test_steps.addAll(getTest_Steps);
				
				getTest_Steps.clear();
				getTest_Steps = res.verifyStatusAfterReservation(driver, status);
				test_steps.addAll(getTest_Steps);
				
				getTest_Steps.clear();
				getTest_Steps = res.verifyConfirmationNoAfterReservation(driver, resNo);
				test_steps.addAll(getTest_Steps);
				
				getTest_Steps.clear();
				getTest_Steps = res.verifyStayDateAfterReservation(driver, Utility.getCurrentDate("MMM dd"), Utility.GetNextDate(1, "MMM dd"));
				test_steps.addAll(getTest_Steps);
				
				String foundTripTotalHeader = res.getTripTotal_Header(driver);
				assertEquals(Float.parseFloat(foundTripTotalHeader), (Float.parseFloat(BaseAmount)*2), "Failed TripTotal Missmatched");
				Utility.app_logs.info("Successfully Verified Header TripTotal : " + foundTripTotalHeader);
				test_steps.add("Successfully Verified Header TripTotal : " + foundTripTotalHeader);
				
				String foundBalanceHeader = res.getBalance_Header(driver);
				assertEquals(Float.parseFloat(foundBalanceHeader), (Float.parseFloat(BaseAmount)*2), "Failed Balance Missmatched");
				Utility.app_logs.info("Successfully Verified Header Balance : " + foundBalanceHeader);
				test_steps.add("Successfully Verified Header Balance : " + foundBalanceHeader);
				
				app_logs.info("==========VERIFY RESERVATION TRIP SUMMARY==========");
				test_steps.add("==========VERIFY RESERVATION TRIP SUMMARY==========");
				
				String foundRoomCharge = res.getRoomCharge_TripSummary(driver);
				assertEquals(Float.parseFloat(foundRoomCharge), (Float.parseFloat(backupBaseRate)*2), "Failed RoomCahrge Missmatched");
				Utility.app_logs.info("Successfully Verified RoomCharge : " + foundRoomCharge);
				test_steps.add("Successfully Verified RoomCharge : " + foundRoomCharge);
				
				String foundTripTotal = res.getTripTotal_TripSummary(driver);
				assertEquals(Float.parseFloat(foundTripTotal), (Float.parseFloat(BaseAmount)*2), "Failed TripTotal Missmatched");
				Utility.app_logs.info("Successfully Verified TripTotal : " + foundTripTotal);
				test_steps.add("Successfully Verified TripTotal : " + foundTripTotal);
				
				String foundBalance = res.getBalance_TripSummary(driver);
				assertEquals(Float.parseFloat(foundBalance), (Float.parseFloat(BaseAmount)*2), "Failed Balance Missmatched");
				Utility.app_logs.info("Successfully Verified Balance : " + foundBalance);
				test_steps.add("Successfully Verified Balance : " + foundBalance);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed  ", testName, "GroupCPReservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed ", testName, "GroupCPReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Navigate to Groups
		try {
			Nav.Groups(driver);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Navigate", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Navigate", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// searching and navigate to Room Block
		try {
			app_logs.info("==========GROUP SEARCHING==========");
			test_steps.add("==========GROUP SEARCHING==========");
			
			getTest_Steps.clear();
			getTest_Steps = group.Search_Account(driver, AccountName, AccountNo, true, true);
			test_steps.addAll(getTest_Steps);

			group.navigateRoomBlock(driver, test);
			Utility.app_logs.info("Navigate to Room Block Tab");
			test_steps.add("Navigate to Room Block Tab");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Search Account ", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Search Account ", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		String PickupValue = null;
		String AvailableRoom = null;
		String BookIconClass = null;
		try {

			app_logs.info("==========VERIFING BLOCK DETAIL AFTER RESERVATION==========");
			test_steps.add("==========VERIFING BLOCK DETAIL AFTER RESERVATION==========");
			
			PickupValue = group.getPickUpValue(driver, roomClassName);
			Utility.app_logs.info("After Pickup Value : " + PickupValue);
			test_steps.add("After Pickup Value : " + PickupValue);
			assertEquals(Integer.parseInt(PickupValue), 2, "Failed PickUp Value Not increased");

			BookIconClass = group.getBookIconClass(driver, roomClassName);
			Utility.app_logs.info("After BookIcon Class : " + BookIconClass);
			test_steps.add("After BookIcon Class : " + BookIconClass);
			assertEquals(BookIconClass, "bookred", "Book Icon Color Not Matched");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Process Book Icon", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Process Book Icon", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			AvailableRoom = group.getAvailableRooms(driver, roomClassName);
			Utility.app_logs.info("After Available Rooms : " + AvailableRoom);
			test_steps.add("After Available Rooms : " + AvailableRoom);
			assertEquals(Integer.parseInt(AvailableRoom), 0, "Failed to verfy Availalbe Rooms not Matched");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Process Book Icon", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {

			test_steps.add(e.toString());

		}


		// navigate Group reservation tab
		try {
			app_logs.info("==========VERIFING RESERVATION RECORD==========");
			test_steps.add("==========VERIFING RESERVATION RECORD==========");
			
			getTest_Steps.clear();
			advGrp.click_GroupsReservationTab(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = group.verifyReservationCount(driver, 2);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = group.verifyReservationLine(driver, resNo, PrimaryGuestFirstName + " " + PrimaryGuestLastName, BlockName, "1", "0", status, RoomClassAbb + " : 1", Utility.getCurrentDate("MMM dd, yyyy"), Utility.GetNextDate(1, "MMM dd, yyyy"), "1");
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = group.verifyReservationLine(driver, resNo, SecondaryGuestFirstName + " " + SecondaryGuestLastName, BlockName, "1", "0", status, RoomClassAbb + " : 2", Utility.getCurrentDate("MMM dd, yyyy"), Utility.GetNextDate(1, "MMM dd, yyyy"), "1");
			test_steps.addAll(getTest_Steps);
			
			app_logs.info("==========OPEN FIRST RESERVATION RECORD==========");
			test_steps.add("==========OPEN FIRST RESERVATION RECORD==========");
			
			getTest_Steps.clear();
			getTest_Steps = group.openResDialogFromResTab(driver, 1);
			test_steps.addAll(getTest_Steps);

			Wait.explicit_wait_visibilityof_webelement_600(driver.findElement(By.xpath("//*[@id='dialog-body0']")),
					driver);
			driver.switchTo().frame("dialog-body0");
			
			app_logs.info("==========VERIFY RESERVATION HEADER DETAIL==========");
			test_steps.add("==========VERIFY RESERVATION HEADER DETAIL==========");
			
			getTest_Steps.clear();
			getTest_Steps = res.verifyGuestNameAfterReservation(driver, PrimaryGuestFirstName + " " + PrimaryGuestLastName);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = res.verifyAccountName(driver, AccountName,false);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = res.verifyStatusAfterReservation(driver, status);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = res.verifyConfirmationNoAfterReservation(driver, resNo);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = res.verifyStayDateAfterReservation(driver, Utility.getCurrentDate("MMM dd"), Utility.GetNextDate(1, "MMM dd"));
			test_steps.addAll(getTest_Steps);
			
			String foundTripTotalHeader = res.getTripTotal_Header(driver);
			assertEquals(Float.parseFloat(foundTripTotalHeader), (Float.parseFloat(BaseAmount)*2), "Failed TripTotal Missmatched");
			Utility.app_logs.info("Successfully Verified Header TripTotal : " + foundTripTotalHeader);
			test_steps.add("Successfully Verified Header TripTotal : " + foundTripTotalHeader);
			
			String foundBalanceHeader = res.getBalance_Header(driver);
			assertEquals(Float.parseFloat(foundBalanceHeader), (Float.parseFloat(BaseAmount)*2), "Failed Balance Missmatched");
			Utility.app_logs.info("Successfully Verified Header Balance : " + foundBalanceHeader);
			test_steps.add("Successfully Verified Header Balance : " + foundBalanceHeader);
			
			app_logs.info("==========VERIFY RESERVATION TRIP SUMMARY==========");
			test_steps.add("==========VERIFY RESERVATION TRIP SUMMARY==========");
			
			String foundRoomCharge = res.getRoomCharge_TripSummary(driver);
			assertEquals(Float.parseFloat(foundRoomCharge), (Float.parseFloat(backupBaseRate)*2), "Failed RoomCahrge Missmatched");
			Utility.app_logs.info("Successfully Verified RoomCharge : " + foundRoomCharge);
			test_steps.add("Successfully Verified RoomCharge : " + foundRoomCharge);
			
			String foundTripTotal = res.getTripTotal_TripSummary(driver);
			assertEquals(Float.parseFloat(foundTripTotal), (Float.parseFloat(BaseAmount)*2), "Failed TripTotal Missmatched");
			Utility.app_logs.info("Successfully Verified TripTotal : " + foundTripTotal);
			test_steps.add("Successfully Verified TripTotal : " + foundTripTotal);
			
			String foundBalance = res.getBalance_TripSummary(driver);
			assertEquals(Float.parseFloat(foundBalance), (Float.parseFloat(BaseAmount)*2), "Failed Balance Missmatched");
			Utility.app_logs.info("Successfully Verified Balance : " + foundBalance);
			test_steps.add("Successfully Verified Balance : " + foundBalance);
			
			driver.switchTo().defaultContent();

			getTest_Steps.clear();
			getTest_Steps = group.closeDialoge(driver, "dialog-close0");
			test_steps.addAll(getTest_Steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify Resrvation", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify Resrvation", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			
			app_logs.info("==========OPEN SECOND RESERVATION RECORD==========");
			test_steps.add("==========OPEN SECOND RESERVATION RECORD==========");
			
			getTest_Steps.clear();
			getTest_Steps = group.openResDialogFromResTab(driver, 2);
			test_steps.addAll(getTest_Steps);

			Wait.explicit_wait_visibilityof_webelement_600(driver.findElement(By.xpath("//*[@id='dialog-body0']")),
					driver);
			driver.switchTo().frame("dialog-body0");
			
			app_logs.info("==========VERIFY RESERVATION HEADER DETAIL==========");
			test_steps.add("==========VERIFY RESERVATION HEADER DETAIL==========");
			
			getTest_Steps.clear();
			getTest_Steps = res.verifyGuestNameAfterReservation(driver, PrimaryGuestFirstName + " " + PrimaryGuestLastName);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = res.verifyAccountName(driver, AccountName,false);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = res.verifyStatusAfterReservation(driver, status);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = res.verifyConfirmationNoAfterReservation(driver, resNo);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = res.verifyStayDateAfterReservation(driver, Utility.getCurrentDate("MMM dd"), Utility.GetNextDate(1, "MMM dd"));
			test_steps.addAll(getTest_Steps);
			
			String foundTripTotalHeader = res.getTripTotal_Header(driver);
			assertEquals(Float.parseFloat(foundTripTotalHeader), (Float.parseFloat(BaseAmount)), "Failed TripTotal Missmatched");
			Utility.app_logs.info("Successfully Verified Header TripTotal : " + foundTripTotalHeader);
			test_steps.add("Successfully Verified Header TripTotal : " + foundTripTotalHeader);
			
			String foundBalanceHeader = res.getBalance_Header(driver);
			assertEquals(Float.parseFloat(foundBalanceHeader), (Float.parseFloat(BaseAmount)), "Failed Balance Missmatched");
			Utility.app_logs.info("Successfully Verified Header Balance : " + foundBalanceHeader);
			test_steps.add("Successfully Verified Header Balance : " + foundBalanceHeader);
			
			app_logs.info("==========VERIFY RESERVATION TRIP SUMMARY==========");
			test_steps.add("==========VERIFY RESERVATION TRIP SUMMARY==========");
			
			String foundRoomCharge = res.getRoomCharge_TripSummary(driver);
			assertEquals(Float.parseFloat(foundRoomCharge), (Float.parseFloat(backupBaseRate)), "Failed RoomCahrge Missmatched");
			Utility.app_logs.info("Successfully Verified RoomCharge : " + foundRoomCharge);
			test_steps.add("Successfully Verified RoomCharge : " + foundRoomCharge);
			
			String foundTripTotal = res.getTripTotal_TripSummary(driver);
			assertEquals(Float.parseFloat(foundTripTotal), (Float.parseFloat(BaseAmount)), "Failed TripTotal Missmatched");
			Utility.app_logs.info("Successfully Verified TripTotal : " + foundTripTotal);
			test_steps.add("Successfully Verified TripTotal : " + foundTripTotal);
			
			String foundBalance = res.getBalance_TripSummary(driver);
			assertEquals(Float.parseFloat(foundBalance), (Float.parseFloat(BaseAmount)), "Failed Balance Missmatched");
			Utility.app_logs.info("Successfully Verified Balance : " + foundBalance);
			test_steps.add("Successfully Verified Balance : " + foundBalance);
			
			app_logs.info("==========VERIFY ACCOUNT LINK==========");
			test_steps.add("==========VERIFY ACCOUNT LINK==========");
			
			getTest_Steps.clear();
			getTest_Steps = res.verifyAccountName(driver, AccountName,true);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps =acc.verifyAccountDetails(driver, "Group", AccountName, AccountNo, "Active");
			test_steps.addAll(getTest_Steps);
			
			driver.switchTo().defaultContent();

			getTest_Steps.clear();
			getTest_Steps = group.closeDialoge(driver, "dialog-close0");
			test_steps.addAll(getTest_Steps);

			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify Resrvation", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
				Utility.updateReport(e, "Failed to Verify Resrvation", testName, "Group", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// account inActive
		try {
			getTest_Steps.clear();
			group.Save(driver, test, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			app_logs.info("==========DELETING TEMP GROUPS, RATES AND ROOMCLASSES==========");
			test_steps.add("==========DELETING TEMP GROUPS, RATES AND ROOMCLASSES==========");
			
			getTest_Steps.clear();
			getTest_Steps = group.changeActiveStatus(driver, "Inactive");
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			group.Save(driver, test, getTest_Steps);
			test_steps.addAll(getTest_Steps);

		} catch (Exception e) {
			
		}
		
		try {

			nav.Inventory(driver);
			nav.Rates1(driver);
			rate.SearchRate(driver, RateName, false);
			test_steps.add("New Rate has been Searched successfully");
			app_logs.info("New Rate has been Searched successfully");
			rate.delete_rate_1(driver, RateName);
			test_steps.add("New Rate has been Deleted successfully");
			app_logs.info("New Rate has been Deleted successfully");

		} catch (Exception e) {
			
		}
		// Delete Room Class
		try {
			nav.Setup(driver);
			nav.RoomClass(driver);
			try {
				room_class.SearchRoomClass(driver, roomClassName, false);
				room_class.Delete_RoomClass(driver, roomClassName);
			} catch (Exception b) {
				room_class.SelectItemsPerPage(driver);
				room_class.Search_Delete_RoomClass(driver, roomClassName);
			}
			test_steps.add("Delete room class successfully");
			app_logs.info("Delete room class successfully");
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
		} catch (Exception e) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(TestName, TestDescription, TestCategory, test_steps);
		}
	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getData("VerifyAdvanceGroupMRBReser", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		 driver.quit();
	}
}
