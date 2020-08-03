package com.innroad.inncenter.tests;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Folio;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Reports;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyLedgerBalanceReport extends TestCore {
	// Before Test
	private WebDriver driver = null;
	public static String testName = "";
	public static String testDescription = "";
	public static String testCategory = "";
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> getTest_Steps = new ArrayList<>();

	@BeforeTest(alwaysRun=true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reports")
	public void verifyLedgerBalanceReport(String adults,String children,String ratePlan,String promoCode,String isSplitRes,
			String roomClassName,String isAssign,String isAddMoreGuestInfo, String salutation,String guestFirstName,String guestLastName,String phoneNumber,String altenativePhone,String email,String account,String accountType,
			String address1,String address2,String address3,String city,String country,String state,String postalCode,String isGuesProfile,String referral,
			String pendingState, String postedState, String incidentalName, String description,String amount, 
			String incidentalName2, String description2,String amount2,String incidentalName3, String description3,String amount3, String groupBy, String reportDetail) {

		testName = "VerifyLedgerBalanceReport";
		testDescription = "Verify Ledger Balance Report for a particular Item Status<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682481' target='_blank'>"
				+ "Click here to open TestRail: C682481'</a>"+
				"Verify Ledger balance report when room assignment is changed<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682520' target='_blank'>"+
				"Click here to open TestRail: C682520'</a>";
		testCategory = "Reports";
		
		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Navigation navigation = new Navigation();
		Reports reports = new Reports();
		Folio folio = new Folio();
		CPReservationPage reservationPage = new CPReservationPage();
		ReservationSearch reservationSearch = new ReservationSearch();
		RoomClass roomClass = new RoomClass();
		
		String firstRoomNumber = null;
		String secondRoomNumber = null;
		String updatedRoomNumber = null;
		String roomClassAbb = null;
		String reservation=null;
		String status=null;
		ArrayList<String> Rooms = new ArrayList<String>();
		String checkInDate = null;
		String checkOutDate = null;
		String ledgerReportFromDate = null;
		String ledgerReportToDate = null;

		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);

			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver = getDriver();
			login_NONGS(driver);
			test_steps.add("Logged into the application");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			test_steps.add("========== GETTING ROOMCLASS ABBREVIATION ==========");
			app_logs.info("========== GETTING ROOMCLASS ABBREVIATION ==========");

			navigation.Setup(driver);
			test_steps.add("Navigate to setup");
			app_logs.info("Navigate to setup");
			navigation.RoomClass(driver);
			test_steps.add("Navigate to roomclass");
			app_logs.info("Navigate to roomclass");
			
			roomClassAbb = roomClass.getRoomClassAbbrivation(driver, roomClassName);
			String[] rc=roomClassName.split("\\|");
			test_steps.add("getting abbrivation room class : " + rc[0] + " is : "+roomClassAbb);
			System.out.println("roomClassAbb :  " + roomClassAbb);
			
			navigation.Reservation_Backward_1(driver);
		}catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to get room class abbrivations", testName, "Room class", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to get room class abbrivations", testName, "Room class", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Reservation
		try {
			test_steps.add("========== CREATING RESERVATION ==========");
			app_logs.info("========== CREATING RESERVATION ==========");

			reservationPage.click_NewReservation(driver, test_steps);
			
			checkInDate	= reservationPage.CalculateDatesForMRCheckInCheckOut(0);
			checkOutDate = reservationPage.CalculateDatesForMRCheckInCheckOut(1);
			app_logs.info("checkInDates : " + checkInDate);
			app_logs.info("checkOutDates : " + checkOutDate);
			reservationPage.select_Dates(driver, test_steps, checkInDate, checkOutDate, adults, children, ratePlan, promoCode,isSplitRes);
			
			if(isSplitRes.equalsIgnoreCase("Yes")) {
				reservationPage.enter_Adults(driver, test_steps, adults);
				reservationPage.enter_Children(driver, test_steps, children);
				reservationPage.select_Rateplan(driver, test_steps, ratePlan, promoCode);
			}
			
			reservationPage.click_FindRooms(driver, test_steps);
			
			reservationPage.select_MRBRooms(driver, test_steps, roomClassName, isAssign,account);
			
			reservationPage.click_Next(driver, test_steps);
			
			reservationPage.enter_MRB_MailingAddress(driver, test_steps, salutation, guestFirstName, guestLastName, phoneNumber, altenativePhone, email, account, accountType, address1, address2, address3, city, country, state, postalCode, isGuesProfile, isAddMoreGuestInfo, isSplitRes,Rooms);
			app_logs.info(Rooms);
		
			getTest_Steps.clear();
			getTest_Steps = reservationPage.SelectReferral(driver, referral);
			test_steps.addAll(getTest_Steps);


			reservationPage.click_BookNow(driver, test_steps);

			reservation=reservationPage.get_ReservationConfirmationNumber(driver, getTest_Steps);
			test_steps.add("Successfully Created Multi Room Reservation: " + reservation);
			app_logs.info("Successfully Created Multi Room Reservation: " + reservation);
			getTest_Steps.clear();
			
			status=reservationPage.get_ReservationStatus(driver, getTest_Steps);
			test_steps.add("Reservation Status: "+status);
			app_logs.info("Reservation Status: "+status);			
			getTest_Steps.clear();
			
			reservationPage.clickCloseReservationSavePopup(driver, getTest_Steps);
			test_steps.add("Clicking on close reservation save popup");
			app_logs.info("Clicking on close reservation save popup");
			getTest_Steps.clear();
			
			firstRoomNumber =  reservationPage.getMRBRoomNumber(driver, isAssign, 1);
			app_logs.info("Primary Room Number : "+firstRoomNumber);
			test_steps.add("Primary Room Number : "+firstRoomNumber);

			secondRoomNumber =  reservationPage.getMRBRoomNumber(driver, isAssign, 2);
			app_logs.info("Secondary Room Number : "+secondRoomNumber);
			test_steps.add("Secondary Room Number : "+secondRoomNumber);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		//Add another Room in reservation
		try{
			test_steps.add("========== Updating Room in reservation ==========");
			app_logs.info("========== Updating Room in reservation ==========");

			String roomNumber = reservationPage.AssignNewRoomNumber(driver);
			app_logs.info("Room number: " + roomNumber);

			updatedRoomNumber =  reservationPage.getMRBRoomNumber(driver, isAssign, 2);
			app_logs.info("Updated Room Number : "+updatedRoomNumber);
			test_steps.add("Updated Room Number : "+updatedRoomNumber);
			
			reservationPage.close_FirstOpenedReservation(driver, test_steps);
		
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to change room", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to change room", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			reservationSearch.basicSearch_WithReservationNumber(driver, reservation);
			test_steps.add("Searched reservation:  " + reservation);
			app_logs.info("Searched reservation: " + reservation);

			reservationPage.verify_MR_ToolTip(driver, test_steps, reservation);
			test_steps.add("Successfully verify tool tip ");
			app_logs.info("Successfully verify tool tip ");
			
			reservationPage.openReservation(driver, test_steps);
			test_steps.add("Reservation opened successfully");
			app_logs.info("Reservation opened successfully");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to verify reservation tooltip", testName, "MRToolTip", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to verify reservation tooltip", testName, "MRToolTip", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			folio.FolioTab(driver);
			test_steps.add("Clicked Folio Tab");
			app_logs.info("Clicked Folio Tab");			
		
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to click folio tab", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to click folio tab", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			test_steps.add("========== ADDING NEW LINE ITEM CATEGORY : " + incidentalName + " IN FIRSTROOM FOLIO ==========");
			app_logs.info("========== ADDING NEW LINE ITEM CATEGORY : " + incidentalName + " IN FIRSTROOM FOLIO ==========");
			
			getTest_Steps.clear();		
			getTest_Steps = folio.clickAddLineItemButton(driver);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();		
			getTest_Steps = folio.AddFolioLineItem(driver, incidentalName, amount);
			test_steps.addAll(getTest_Steps);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to Add Line Item", testName, "AddLineItem", driver);
		
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to Add Line Item", testName, "AddLineItem", driver);
		
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			test_steps.add("========== ADDING NEW LINE ITEM CATEGORY : " + incidentalName2 + " IN FIRSTROOM FOLIO ==========");
			app_logs.info("========== ADDING NEW LINE ITEM CATEGORY : " + incidentalName2 + " IN FIRSTROOM FOLIO ==========");
			
			getTest_Steps.clear();		
			getTest_Steps = folio.clickAddLineItemButton(driver);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();		
			getTest_Steps = folio.AddFolioLineItem(driver, incidentalName2, amount2);
			test_steps.addAll(getTest_Steps);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to Add Line Item", testName, "AddLineItem", driver);
		
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to Add Line Item", testName, "AddLineItem", driver);
		
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			test_steps.add("========== MAKING LINE ITEM (" + incidentalName2 + ") STATUS TO POSTED ==========");
			app_logs.info("========== MAKING LINE ITEM (" + incidentalName2 + ") STATUS TO POSTED ==========");

			folio.PostedLineItems(driver, incidentalName2, pendingState, postedState);
			test_steps.add("Verified line itme " + incidentalName2 + " in posted state after click on post line item");
			app_logs.info("Verified line itme " + incidentalName2 + " in posted state after click on post line item");
		
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to posted line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to posted line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			test_steps.add("========== CHANGE TO CUSTOM FOLIO ==========");
			app_logs.info("========== CHANGE TO CUSTOM FOLIO ==========");
			String guestFolio = "Guest Folio For ";
			if(firstRoomNumber.equalsIgnoreCase("Unassigned")){
				firstRoomNumber = "";
			}
			if(updatedRoomNumber.equalsIgnoreCase("Unassigned")){
				secondRoomNumber = "";
			}
			String firstFolio = guestFolio + roomClassAbb + " : " + firstRoomNumber;
			String secondFolio = guestFolio + roomClassAbb + " : " + updatedRoomNumber;
			
			getTest_Steps.clear();	
			getTest_Steps =	folio.ChangeFolio(driver, firstFolio, secondFolio);
			test_steps.addAll(getTest_Steps);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to change folio", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to change folio", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		try {
			test_steps.add("========== ADDING NEW LINE ITEM CATEGORY : " + incidentalName3 + " IN SECONDROOM FOLIO ==========");
			app_logs.info("========== ADDING NEW LINE ITEM CATEGORY : " + incidentalName3 + " IN SECONDROOM FOLIO ==========");
			
			getTest_Steps.clear();		
			getTest_Steps = folio.clickAddLineItemButton(driver);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();		
			getTest_Steps = folio.AddFolioLineItem(driver, incidentalName3, amount3);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.ClickSaveFolioItemsButton(driver);
			test_steps.addAll(getTest_Steps);

	
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to Add Line Item", testName, "AddLineItem", driver);
		
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to Add Line Item", testName, "AddLineItem", driver);
		
			} else {
				Assert.assertTrue(false);
			}
		}
		
		// Summary
		try {
			test_steps.add("========== VERIFYING LEDGER BALANCE REPORT WITH SINGLE INCIDENTALS ==========");
			app_logs.info("========== VERIFYING LEDGER BALANCE REPORT WITH SINGLE INCIDENTALS ==========");
			
			navigation.Reports(driver);
			test_steps.add("Navigate to reports");
			app_logs.info("Navigate to reports");
			
			getTest_Steps.clear();
			getTest_Steps = reports.BalanceLedgerTab(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			String[] checkIn = checkInDate.split("\\|");
			ledgerReportFromDate = checkIn[0];
			String[] checkOut = checkOutDate.split("\\|");
			ledgerReportToDate = checkOut[1];			
			app_logs.info(ledgerReportFromDate + " " + ledgerReportToDate);

			getTest_Steps.clear();
			getTest_Steps = reports.SelectLedgerBalanceFromDate(driver, ledgerReportFromDate);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = reports.SelectLedgerBalanceToDate(driver, ledgerReportToDate);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = reports.selectGroupBy(driver, groupBy);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = reports.clickIncidentalsCheckBoxes(driver, "Incidental", false);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = reports.clickIncidentalsCheckBoxes(driver, incidentalName, true);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = reports.clickItemStatus(driver, "Pending");
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = reports.clickItemStatus(driver, "Posted");
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = reports.clickIncidentalsCheckBoxes(driver, reportDetail, true);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = reports.BalanceLedgerGoButton(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e,
						"Failed to enter prerequisite for ledger balance report", testName,
						"LedgerBalance", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e,
						"Failed to enter prerequisite for ledger balance report", testName,
						"LedgerBalance", driver);
			} else {
				Assert.assertTrue(false);
			}

		}

		try{			
			getTest_Steps.clear();
			getTest_Steps = reports.verifyLegderBalanceReport(driver, description, description2, description3, firstRoomNumber,updatedRoomNumber);
			test_steps.addAll(getTest_Steps);

			test_steps.add("Ledger Balance Report Verified");
			app_logs.info("Ledger Balance Report Verified");
	
			} catch (Exception e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
					Utility.updateReport(e,
							"Failed to verify report", testName,
							"LedgerBalance", driver);
				} else {
					Assert.assertTrue(false);
				}
			} catch (Error e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
					Utility.updateReport(e,
							"Failed to to verify report", testName,
							"LedgerBalance", driver);
				} else {
					Assert.assertTrue(false);
				}
		
			}
		try {
			test_steps.add("========== VERIFYING LEDGER BALANCE REPORT WITH MULTIPLE INCIDENTALS ==========");
			app_logs.info("========== VERIFYING LEDGER BALANCE REPORT WITH MULTIPLE INCIDENTALS ==========");

			getTest_Steps.clear();
			getTest_Steps = reports.BalanceLedgerTab(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = reports.SelectLedgerBalanceFromDate(driver, ledgerReportFromDate);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = reports.SelectLedgerBalanceToDate(driver, ledgerReportToDate);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = reports.selectGroupBy(driver, groupBy);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = reports.clickIncidentalsCheckBoxes(driver, "Incidental", false);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = reports.clickIncidentalsCheckBoxes(driver, incidentalName, true);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = reports.clickIncidentalsCheckBoxes(driver, incidentalName2, true);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = reports.clickIncidentalsCheckBoxes(driver, incidentalName3, true);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = reports.clickItemStatus(driver, "Pending");
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = reports.clickItemStatus(driver, "Posted");
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = reports.clickIncidentalsCheckBoxes(driver, reportDetail, true);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = reports.BalanceLedgerGoButton(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e,
						"Failed to enter prerequisite for ledger balance report", testName,
						"LedgerBalance", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e,
						"Failed to enter prerequisite for ledger balance report", testName,
						"LedgerBalance", driver);
			} else {
				Assert.assertTrue(false);
			}

		}

		try{			
			getTest_Steps.clear();
			getTest_Steps = reports.verifyLegderBalanceReport(driver, description, description2, description3, firstRoomNumber,updatedRoomNumber);
			test_steps.addAll(getTest_Steps);

			test_steps.add("Ledger Balance Report Verified");
			app_logs.info("Ledger Balance Report Verified");
			
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
	
			} catch (Exception e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
					Utility.updateReport(e,
							"Failed to verify report", testName,
							"LedgerBalance", driver);
				} else {
					Assert.assertTrue(false);
				}
			} catch (Error e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
					Utility.updateReport(e,
							"Failed to to verify report", testName,
							"LedgerBalance", driver);
				} else {
					Assert.assertTrue(false);
				}
		
			}


	}

	// Data provider to read the data from excel
	@DataProvider
	public Object[][] getData() {
		return Utility.getData("VerifyLedgerBalanceReport", excel);
	}
	
	@AfterClass(alwaysRun=true)
	public void closeDriver() {
		driver.quit();
	}
}
