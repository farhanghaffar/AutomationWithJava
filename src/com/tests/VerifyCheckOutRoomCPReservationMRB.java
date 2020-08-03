package com.innroad.inncenter.tests;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Policies;
import com.innroad.inncenter.pageobjects.Properties;
import com.innroad.inncenter.pageobjects.Reports;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.RoomStatus;
import com.innroad.inncenter.pageobjects.TaskManagement;
import com.innroad.inncenter.pageobjects.Tax;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.relevantcodes.extentreports.ExtentTest;

public class VerifyCheckOutRoomCPReservationMRB extends TestCore {
	private WebDriver driver = null;
	public static String test_name = null;
	public static String test_nameOne = null;
	public static String test_description = null;
	public static String test_catagory = null;
	ArrayList<String> test_steps = new ArrayList<>();
	String testName = null, reportContant = null;
	RoomClass rc = new RoomClass();
	Navigation nav = new Navigation();
	RoomStatus roomstatus = new RoomStatus();
	CPReservationPage res = new CPReservationPage();
	ArrayList<String> roomCost = new ArrayList<String>();
	ArrayList<String> rooms = new ArrayList<String>();
	ArrayList<String> roomAbbri = new ArrayList<String>();
	ArrayList<String> newRooms = new ArrayList<String>();
	List<String> roomCharges = new ArrayList<String>();
	List<String> guestNames = new ArrayList<String>();

	TaskManagement task_mang = new TaskManagement();
	Properties properties = new Properties();
	boolean generateGuestStatementStatus = false;

	Reports report = new Reports();
	Tax tax = new Tax();
	Double depositAmount = 0.0;
	String reservation = null, panelGuestName = null, status = null, tripTotalAmounts = null, paymentMethod = null,
			timeZone = null, propertyName = null, policyName = null, yearDate = null,  checkOutAmount=null, checkInAmount=null,taxAmount=null;
	int halfAmountPaid = 0, checkoutBalance = 0;
	Date currentDate = null, previousDate = null;
	String balanceAmount = null;
	int TotalAmountPaid = 0;
	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void CheckIN_Room( String URL, String ClientCode, String UserName, String Password,
			String RoomClass, String CheckInDate, String CheckOutDate, String Adults, String Children, String Rateplan,
			String PromoCode, String IsSplitRes, String IsAssign, String Salutation, String GuestFirstName,
			String GuestLastName, String PhoneNumber, String AltenativePhone, String Email, String Account,
			String AccountType, String Address1, String Address2, String Address3, String City, String Country,
			String State, String PostalCode, String IsGuesProfile, String IsAddMoreGuestInfo, String PaymentType,
			String CardNumber, String NameOnCard, String TravelAgent, String MarketSegment, String Referral,
			String IsGuesRegistration, String IsOverRideCheckIN, String SetPaymentMethod, String Notes,
			String CheckOutNote, String TakePaymentType, String CheckOutRoom) throws InterruptedException {
		test_name = "Verify Primary Guest Pay Secondary Guest Charges When Primary Guest Select 'Yes' While Checking-Out in CP MRB Reservation";
		test_description = "Verify CP MRB Check-Out Room Reservation <br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682471' target='_blank'>"
				+ "Click here to open TestRail: C682471</a><br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682506' target='_blank'>"
				+ "Click here to open TestRail: C682506</a><br>";
		test_catagory = "CPReservation_CheckOut";
		testName = test_name;

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
			test_steps.add("Logged into the application");
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
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Get Abbreviations
		try {
			nav.Setup(driver);
			nav.RoomClass(driver);
			test_steps.add("<b>****Getting Abbreviations****</b>");
			rc.getRoomClassAbbrivations(driver, test_steps, roomAbbri, RoomClass);
			System.out.println(roomAbbri);
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to get room class abbrivations", testName, "Room class", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to get room class abbrivations", testName, "Room class", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Get PropertyName
		try {
			propertyName = properties.getProperty(driver, test_steps);

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
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// get timeZone
		try {
			nav.Setup(driver);
			nav.Properties(driver);
			nav.open_Property(driver, test_steps, propertyName);
			nav.click_PropertyOptions(driver, test_steps);
			timeZone = nav.get_Property_TimeZone(driver);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Create New Reservation
		try {
			nav.Setup(driver);
			nav.Reservation_Backward_1(driver);
			test_steps.add("<b>****Start Creating New Reservation****</b>");
			res.click_NewReservation(driver, test_steps);
			res.select_Dates(driver, test_steps, CheckInDate, CheckOutDate, Adults, Children, Rateplan, PromoCode,
					IsSplitRes);
			res.clickOnFindRooms(driver, test_steps);
			roomCost = res.select_MRBRooms(driver, test_steps, RoomClass, IsAssign, Account);
			String TripAmount = res.getTripSummaryTripTotalAmountBeforeReservation(driver, test_steps);
			halfAmountPaid = (int) (Double.parseDouble(TripAmount) / 2);
			System.out.println("Override Deposit Due Amount : " + halfAmountPaid);
			res.InputdepositDue(driver, test_steps, String.valueOf(halfAmountPaid));
			res.clickNext(driver, test_steps);
			yearDate = Utility.getFutureMonthAndYearForMasterCard(driver, test_steps);
			Random random = new Random();
			int x = random.nextInt(900);
			res.enter_MRB_MailingAddressForMRB(driver, test_steps, Salutation, GuestFirstName + x, GuestLastName + x,
					PhoneNumber, AltenativePhone, Email, Account, AccountType, Address1, Address2, Address3, City,
					Country, State, PostalCode, IsGuesProfile, IsAddMoreGuestInfo, IsSplitRes, rooms);
			if (Account.equalsIgnoreCase("") || Account.isEmpty()) {
				res.enter_PaymentDetails(driver, test_steps, PaymentType, CardNumber, NameOnCard, yearDate);
			}
			System.out.println(rooms);
			res.enter_MarketSegmentDetails(driver, test_steps, TravelAgent, MarketSegment, Referral);
			res.clickBookNow(driver, test_steps);
			reservation = res.get_ReservationConfirmationNumber(driver, test_steps);
			res.clickCloseReservationSavePopup(driver, test_steps);
			panelGuestName = res.getPanelStatusGuestName(driver);
			roomCharges = res.getStayInfoRoomCharges(driver, test_steps);
			res.getStayInfoRoomNo(driver, test_steps);
			guestNames = res.getStayInfoGuestName(driver, test_steps);
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Check In Policy
		try {

			test_steps.add("<b>****Start Verifying Check In Policy Exist or Not ****</b>");
			res.clickPolicyCollapseIcon(driver, test_steps, "Check In", "No");
			policyName = res.getPolicyfromDetailTab(driver, test_steps);
			if (!policyName.equalsIgnoreCase("No Policy")) {
				test_steps.add("Policy Exist");
			} else {
				test_steps.add("Policy Doesn't Exist");
			}
			test_steps.add("<b>****Start CheckIn Reservation****</b>");
			res.clickCheckInAllButton(driver, test_steps);
			generateGuestStatementStatus = res.generatGuestReportToggle(driver, test_steps,
					IsGuesRegistration);
			res.completeCheckInProcess(driver, test_steps);
			currentDate = res.getTodaysDate("MM/dd/yyyy", timeZone);
			previousDate = res.getPreviousDate("MM/dd/yyyy", timeZone);
			System.out.println(currentDate + "" + previousDate);
			if (!policyName.equalsIgnoreCase("No Policy")) {
				res.checkedOverRideCheckInAmountCheckBox(driver, test_steps, "Yes");
					checkInAmount = res.get_Checkin_Amount(driver, test_steps);
				
				System.out.println("CheckIn Half Amount : " + checkInAmount);
				res.inputAmountWhileCheckINAndCheckOut(driver, test_steps, checkInAmount);
				res.selectType(driver, test_steps, TakePaymentType);
				// Added Notes and Click Log button
				res.addNotesAndClickLogORPayORAuthorizedButton(driver, test_steps, Notes);
				res.clickCloseButtonOfSuccessModelPopup(driver, test_steps);
			}

			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.getRoomsOnDetailPage(driver, newRooms);
				System.out.println(newRooms);
			}

			status = res.getPanelStatusStatusName(driver);
			test_steps.add("<b>****Start Verifying In-House Status****</b>");
			res.verifyReservationStatusStatus(driver, test_steps, "In-House");
			test_steps.add("<b>****Start Verifying Check-Out All Button ****</b>");
			res.verifyCheckOutAllButton(driver, test_steps);
			tripTotalAmounts = res.get_TripSummaryTripTotalChargesWithoutCurrency(driver, test_steps);
			System.out.println("Trip Summary Total Amount : " + tripTotalAmounts);
			res.click_Folio(driver, test_steps);
			taxAmount = res.get_Taxes(driver, test_steps);
			System.out.println("Folio -Tax is : " + taxAmount);
			res.click_DeatilsTab(driver, test_steps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to CheckIn", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to CheckIN", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// CheckOut Room
		try {
			// get balance
			String balance = res.get_TripSummaryBalance_Amount(driver, test_steps);
			double balanceOne = Double.valueOf(balance);
			checkoutBalance = (int) balanceOne;
			test_steps.add("Get Balance Before Check Out <b>" + checkoutBalance + "</b>");
			app_logs.info(checkoutBalance);

			
			test_steps.add("<b>****Start Check-Out Room****</b>");
			res.stayInfoCheckOutRoom(driver, test_steps, CheckOutRoom, roomCharges, rooms, roomAbbri, guestNames,
					PhoneNumber, Email);

			String[] room = res.actualRoomClassCheckOut.split(":");
			String checkOutRoom = room[1].trim();
			System.out.println(" Room No: " + checkOutRoom);

			String[] roomClass = res.actualRoomClassCheckOut.split(":");
			String roomClassname = roomClass[0].trim();
			System.out.println(" Room No: " + roomClassname);

			if (CheckOutRoom.equals("1")) {
				res.clickYesButtonOfCheckOutAllConfirmationMsg(driver, test_steps,
						"Do you wish to settle all guest's remaining charges against the primary guest's folio?",
						"CheckOutAll", checkOutRoom, roomClassname);

			} else {
				String message = null;
				res.clickYesButtonOfCheckOutAllConfirmationMsg(driver, test_steps, message, "CheckOutRoom",
						checkOutRoom, roomClassname);

			}
			test_steps.add("<b>****Check-Out Window****</b>");
			res.verifyReservationPopWindow(driver, "Check Out", res.actualGuestNameCheckOut, status, reservation,
					test_steps, "Check Out");
			res.verifyMessageOfCheckOutWindow(driver, test_steps,
					"Are you sure you want to check out this reservation?");
			generateGuestStatementStatus = res.generatGuestReportToggle(driver, test_steps, "Yes");

			res.clickCheckOutButton(driver, test_steps, checkoutBalance);

			
			
			double checkOutPaidAmount = Double.valueOf(res.actualRoomCheckOutCharge);
			int amtCheckOutPaid = (int) checkOutPaidAmount;
			String CheckOutPaidAmount = String.valueOf(amtCheckOutPaid);
			System.out.println("Total Amount Remaining : " + CheckOutPaidAmount);

			double tripTotalAmount = Double.valueOf(tripTotalAmounts);
			int tripTotal = (int) tripTotalAmount;
			
	
			int taxAmt=0;
			double tax=Double.valueOf(taxAmount);
			 taxAmt=(int)tax;
			
			
			
	/*	if (!policyName.equalsIgnoreCase("No Policy")) {
				TotalAmountPaid = halfAmountPaid + Integer.parseInt(checkInAmount)
						+ Integer.parseInt(CheckOutPaidAmount)+taxAmt;
				Balance = String.valueOf(tripTotal-TotalAmountPaid );
			} else {
				TotalAmountPaid = halfAmountPaid + Integer.parseInt(CheckOutPaidAmount)+taxAmt;
				Balance = String.valueOf(tripTotal - TotalAmountPaid);
			}*/
		
		if (!policyName.equalsIgnoreCase("No Policy")) {
			TotalAmountPaid = halfAmountPaid + Integer.parseInt(checkInAmount);
		//	Balance = String.valueOf(tripTotal-TotalAmountPaid );
		} else {
			TotalAmountPaid = halfAmountPaid ;
		//	Balance = String.valueOf(tripTotal - TotalAmountPaid);
		}
				
		//	String totalAmountPaid = String.valueOf(TotalAmountPaid);
			System.out.println("Total Paid Amount After Check In: " + TotalAmountPaid);
		//	System.out.println("Total Balance Amount  : " + Balance);
			
			double tripAmount = Double.valueOf(tripTotalAmounts);
			int amt = (int) tripAmount;
			String tripAmountRemaining = String.valueOf(amt - TotalAmountPaid);
			System.out.println("Total Amount Remaining : " + tripAmountRemaining);
			
			
		String totalAmountPaid=null;
			
			if (checkoutBalance > 0) {
				test_steps.add("<b>****Start Verifying Check-Out Payment Widnow ****</b>");
				checkOutAmount = res.verifyCheckOutPaymentWindow(driver, test_steps, "Check Out Payment",
						PaymentType, TakePaymentType, CheckOutPaidAmount, "5454", NameOnCard, yearDate);
				// Change Payment Method and Set as Main Payment
				res.setAsMainPayment(driver, test_steps, PaymentType, SetPaymentMethod, previousDate, "Check Out");
				paymentMethod = res.getPaymentMethod(driver, test_steps);
				// Added Notes and Click Log button
				res.addNotesAndClickLogORPayORAuthorizedButton(driver, test_steps, CheckOutNote);
				
				
				
				int totalpaidAfterCheckout = TotalAmountPaid + Integer.parseInt(checkOutAmount);
				balanceAmount = String.valueOf(tripTotal - totalpaidAfterCheckout);
				System.out.println("Total Balance Amount  : " + balanceAmount);
				
					res.switchToTabOne(driver, test_steps);
				test_steps.add("<b>****Start Verifying Check-Out Success Widnow ****</b>");
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
				String pastDate = dateFormat.format(previousDate);
				System.out.println("Previous Date: " + pastDate);
			res.commonMethodForSuccessfullWindowVerification(driver, test_steps, "Check out Successful", "Approved",
						SetPaymentMethod, CheckOutNote, "0", TakePaymentType, res.CheckOutAmount, "Check Out",
						pastDate);
				
				res.switchToTabOne(driver, test_steps);
				res.clickCloseButtonOfSuccessModelPopup(driver, test_steps);
				
				totalAmountPaid = String.valueOf(totalpaidAfterCheckout);
				System.out.println("Trip Total Amount Calculated After Checkout: " + totalAmountPaid);
	
			} else {
				res.switchToTabOne(driver, test_steps);
				balanceAmount = String.valueOf((Integer.parseInt(tripAmountRemaining)));
				System.out.println("Total Balance Amount  : " + balanceAmount);
			}

			String[] roomClassName = res.actualRoomClassCheckOut.split(":");
			String checkOutRoomClassName = roomClassName[0].trim();
			System.out.println(" Room No: " + checkOutRoomClassName);

			test_steps.add("<b>****Start Verifying Stay Info Rool Back Button****</b>");
			res.verifyStayInforRollBackButton(driver, test_steps, checkOutRoomClassName);

			if (CheckOutRoom.equals("1")) {
				test_steps.add("<b>****Start Verifying DEPARTED Status****</b>");
				res.verifyReservationStatusStatus(driver, test_steps, "DEPARTED");

			} else {
				test_steps.add("<b>****Start Verifying IN-HOUSE Status****</b>");
				res.verifyReservationStatusStatus(driver, test_steps, status);

			}

			if (CheckOutRoom.equals("1")) {
				test_steps.add("<b>****Start Verifying Roll Back All Button After Check-Out Room****</b>");
				res.verifyRollBackButton(driver, test_steps);
			} else {
				test_steps.add("<b>****Start Verifying Check-Out All Button After Check-Out Room****</b>");
				res.verifyCheckOutAllButton(driver, test_steps);
			}
			// Verified Trip Summary Total

			test_steps.add("<b>****Start Verifying Trip Summary****</b>");

			String tripSummaryPaidAgain = res.getTripSummaryPaidAmount(driver, test_steps);
			System.out.println("Trip Summary Paid: " + tripSummaryPaidAgain);
			res.verifyTripSummaryPaid(driver, test_steps, tripSummaryPaidAgain, totalAmountPaid);
	

			String tripSummaryBalance = res.get_TripSummaryBalance_Amount(driver, test_steps);
			System.out.println("Trip Summary Balance: " + tripSummaryBalance);
			res.Verify_TripSummary_Balance_ForCheckOut(driver, test_steps, tripSummaryBalance, balanceAmount);

			test_steps.add("<b>****Start Verifying Reservation Status Balance****</b>");
			res.verifyReservationStatusBalance(driver, test_steps, balanceAmount);

			if (checkoutBalance > 0) {
				test_steps.add("<b>****Start Verifying Folio Details Line Item****</b>");
				res.click_Folio(driver, test_steps);
				res.click_FolioDetail_DropDownBox(driver, test_steps);

				String[] abbs = res.actualRoomAbbCheckOut.split(":");
				String Finalabb = abbs[1].trim();
				System.out.println(" Abb No: " + Finalabb);
				if (res.isRoomReserved || res.isRoomUnAssigned) {
					res.clickFolioDetailOptionValue(driver, test_steps, Finalabb, newRooms.get(res.actualRoomCheckOut));

				} else {
					res.clickFolioDetailOptionValue(driver, test_steps, Finalabb, res.actualRoomClassCheckOut);
				}
				res.verifyFolioLineItem(driver, test_steps, previousDate, SetPaymentMethod,
						SetPaymentMethod, res.CheckOutAmount);

				test_steps.add("<b>****Start Verifying Primary Guest Pay Secondary Guest Charges****</b>");
				res.click_Folio(driver, test_steps);
				res.click_FolioDetail_DropDownBox(driver, test_steps);

				String[] abbs1 = roomAbbri.get(1).split(":");
				String Finalabb1 = abbs1[1].trim();
				System.out.println(" Abb No: " + Finalabb1);
				if (res.isRoomReserved || res.isRoomUnAssigned) {
					res.clickFolioDetailOptionValue(driver, test_steps, Finalabb, newRooms.get(1));
				} else {
					res.clickFolioDetailOptionValue(driver, test_steps, Finalabb1, rooms.get(1));
				}
				res.VerifyLineItemSecondGuestAlreadyPayAmountByPrimaryOne(driver, test_steps,
						SetPaymentMethod);

			}

			test_steps.add("<b>****Start Verifying History****</b>");
			// Click History Tab
			res.click_History(driver, test_steps);
			String historyDesc = "Checked out this reservation";
			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.verifyHistoryForCheckin(driver, test_steps, historyDesc,
						res.actualRoomAbbCheckOut, newRooms.get(res.actualRoomCheckOut),
						"CheckOutSingleRoom", roomAbbri, newRooms);

			} else {
				res.verifyHistoryForCheckin(driver, test_steps, historyDesc,
						res.actualRoomAbbCheckOut, res.actualRoomClassCheckOut, "CheckOutSingleRoom",
						roomAbbri, rooms);

			}

			if (checkoutBalance > 0) {
				String[] abbs = roomAbbri.get(0).split(":");
				String Finalabb = abbs[1].trim();
				System.out.println(" Room No: " + Finalabb);

				if (res.isRoomReserved || res.isRoomUnAssigned) {
					res.verifyHistoryTabPaymentDesc(driver, test_steps, checkOutAmount,
							"Check In", Finalabb, newRooms.get(0), paymentMethod);

				} else {
					res.verifyHistoryTabPaymentDesc(driver, test_steps, checkOutAmount,
							"Check In", Finalabb, rooms.get(0), paymentMethod);

				}

			}

			test_steps.add("<b>****Start Verifying Updated By on Payment Item Detail Popup****</b>");
			res.click_Folio(driver, test_steps);
			res.click_FolioDetail_DropDownBox(driver, test_steps);
			String[] abbs = roomAbbri.get(0).split(":");
			String Finalabb = abbs[1].trim();
			System.out.println(" Abb No: " + Finalabb);
			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.clickFolioDetailOptionValue(driver, test_steps, Finalabb, newRooms.get(0));
			} else {
				res.clickFolioDetailOptionValue(driver, test_steps, Finalabb, rooms.get(0));
			}
			
			
			res.verifyPaymentDetailUpdateBy(driver, NameOnCard, Utility.userId,
					PaymentType);
			/*res.verifyPaymentDetailUpdateBy(driver, NameOnCard, UserName,
					PaymentType);*/
			res.clickPaymentDetailCancel(driver, test_steps);
			test_steps.add("Successfully  Verified  Update By : <b>" + Utility.userId + "</b> "
					+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/682506' target='_blank'>"
					+ "Click here to open TestRail: C682506</a> <br>");

			test_steps.add("<b>****Start Verifying Documents****</b>");
			res.click_Documents(driver, test_steps);
			res.verifyDocuments(driver, test_steps, reservation);

			res.click_Folio(driver, test_steps);
			String totalCharges = res.get_Payments(driver, test_steps);
			test_steps.add("<b>****Start Verifying Guest Statement Report***</b>");

			res.switchToTabTwo(driver, test_steps);
			reportContant = report.verifyGuestStatementReport(driver, test_steps);

			if (generateGuestStatementStatus) {

				DateFormat dateFormat = new SimpleDateFormat("E, MMM d, yyyy");
				dateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
				dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
				String myDate = dateFormat.format(currentDate);
				// String myDate = dateFormat.format(res.current_Date);
				System.out.println(myDate);
				String[] name = res.actualGuestNameCheckOut.split(" ");
				String actualName = name[1] + " " + name[2];
				System.out.println(actualName);
				String[] roomClassOne = res.actualRoomClassCheckOut.split(":");
				String FinalRoom = roomClassOne[1].trim();
				String FinalRoomClass = roomClassOne[0].trim();
				System.out.println(" Room No: " + FinalRoom);
				System.out.println(" Room Class: " + FinalRoomClass);

				report.Report_Verify_ReportContent(driver, reportContant, "Guest Statement", test_steps);
				test_steps.add("Report Name: <b>" + "Guest Statement" + "</b>");
				Utility.app_logs.info("Report Name: " + "Guest Statement");
				report.Report_Verify_ReportContent(driver, reportContant, "Guest Folio", test_steps);
				test_steps.add("Folio Name: <b>" + "Guest Folio" + "</b>");
				Utility.app_logs.info("Folio Name: " + "Guest Folio");
				report.Report_Verify_ReportContent(driver, reportContant, myDate, test_steps);
				test_steps.add("Date :  <b>" + myDate + "</b>");
				Utility.app_logs.info("Date :  " + myDate);
				report.Report_Verify_ReportContent(driver, reportContant, actualName, test_steps);
				test_steps.add("Primary Gust Name :  <b>" + actualName + "</b>");
				Utility.app_logs.info("Primary Gust Name  :  " + actualName);
				report.Report_Verify_ReportContent(driver, reportContant, FinalRoomClass, test_steps);
				test_steps.add("Room :  <b>" + FinalRoomClass + "</b>");
				Utility.app_logs.info("Room  :  " + FinalRoomClass);
				report.Report_Verify_ReportContent(driver, reportContant, FinalRoom, test_steps);
				test_steps.add("Room No:  <b>" + FinalRoom + "</b>");
				Utility.app_logs.info("Room No:  " + FinalRoom);
				report.Report_Verify_ReportContent(driver, reportContant, totalCharges, test_steps);
				test_steps.add("Room Charges  :  <b>" + totalCharges + "</b>");
				Utility.app_logs.info("Room Charges   :  " + totalCharges);
				report.Report_Verify_ReportContent(driver, reportContant, SetPaymentMethod, test_steps);
				test_steps.add("Payment Method :  <b>" + SetPaymentMethod + "</b>");
				Utility.app_logs.info("Payment Method   :  " + SetPaymentMethod);
				test_steps.add("Verified the Generate Guest Statement Report Successfully"
						+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/682479' target='_blank'>"
						+ "Click here to open TestRail: C682479</a><br>");

			}

			Utility.deleteFile(Utility.fileName);
			test_steps.add("Deleted File  :  <b>" + Utility.fileName + "</b>");
			Utility.app_logs.info("Deleted File   :  " + Utility.fileName);

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Check Out Room", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Check Out Room", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
	}

	@DataProvider
	public Object[][] getData() {

		return Utility.getData("VerifyCheckOutRoomCPResMRB", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
