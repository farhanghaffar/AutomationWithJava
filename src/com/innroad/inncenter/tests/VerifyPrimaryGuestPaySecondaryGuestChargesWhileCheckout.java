package com.innroad.inncenter.tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Navigation;
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

public class VerifyPrimaryGuestPaySecondaryGuestChargesWhileCheckout extends TestCore {
	private WebDriver driver = null;
	public static String test_name = null;
	public static String test_nameOne = null;
	public static String test_description = null;
	public static String test_catagory = null;
	ArrayList<String> test_steps = new ArrayList<>();
	String testName = null;
	RoomClass rc = new RoomClass();
	Navigation nav = new Navigation();
	RoomStatus roomstatus = new RoomStatus();
	CPReservationPage res = new CPReservationPage();
	ArrayList<String> roomCost = new ArrayList<String>();
	ArrayList<String> rooms = new ArrayList<String>();
	ArrayList<String> roomAbbri = new ArrayList<String>();
	ArrayList<String> newRooms = new ArrayList<String>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	TaskManagement task_mang = new TaskManagement();
	List<String> roomCharges = new ArrayList<String>();
	List<String> guestNames = new ArrayList<String>();
	List<String> roomNos = new ArrayList<String>();
	Properties properties = new Properties();
	String reportContant = null;

	Reports report = new Reports();
	Tax tax = new Tax();
	Double depositAmount = 0.0;
	String reservation = null, panelGuestName = null, status = null, getTripTotalAmount = null, paymentMethod = null,
			taxAmount = null, checkInAmount = null, buttonName = null, checkOutAmount = null, yearDate = null,
			policyName = null, timeZone = null, propertyName = null, paidAmount = null, balance_Amount = null,

			getAmount = null;
	int checkoutBalance = 0;
	boolean generateGuestStatementStatus = false;
	Date currentDate = null, previousDate = null;

	double checkout_Balance = 0.00, halfAmount_Paid = 0.00, checkIn_Amount = 0.00;

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void verifyPrimaryGuestPaySecondaryGuestChargesWhileCheckout(String URL, String ClientId, String UserName,
			String Password, String RoomClass, String CheckInDate, String CheckOutDate, String Adults, String Children,
			String Rateplan, String PromoCode, String IsAssign, String Salutation, String GuestFirstName,
			String GuestLastName, String PhoneNumber, String AltenativePhone, String Email, String Account,
			String AccountType, String Address1, String Address2, String Address3, String City, String Country,
			String State, String PostalCode, String IsGuesProfile, String IsAddMoreGuestInfo, String IsSplitRes,
			String PaymentType, String CardNumber, String NameOnCard, String TravelAgent, String MarketSegment,
			String Referral, String IsGuesRegistration, String CheckOutRoom, String SetPaymentMethod,
			String CheckOutNote, String Notes, String TakePaymentType) throws InterruptedException {

		test_name = "VerifyPrimaryGuestPaySecondaryGuestChargesWhileCheckout";
		test_description = "Verify CP MRB Check-Out Room Reservation <br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682471' target='_blank'>"
				+ "Click here to open TestRail: C682471</a><br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682487' target='_blank'>"
				+ "Click here to open TestRail: C682487</a><br>"
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
		// Get checkIN and Checkout Date
		try {
			if (!(Utility.validateInput(CheckInDate))) {
				for (int i = 0; i < GuestFirstName.split("\\|").length; i++) {
					checkInDates.add(Utility.getCurrentDate("dd/MM/yyyy"));
					checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(1), "MM/dd/yyyy", "dd/MM/yyyy"));
				}
			} else {
				checkInDates = Utility.splitInputData(CheckInDate);
				checkOutDates = Utility.splitInputData(CheckOutDate);
			}
			CheckInDate = checkInDates.get(0) + "|" + checkInDates.get(1);
			CheckOutDate = checkOutDates.get(0) + "|" + checkOutDates.get(1);
			app_logs.info(CheckInDate);
			app_logs.info(CheckOutDate);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Get CheckIN CheckOutDate", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Get CheckIN CheckOutDate", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Get Time Zone and Property Name

		try {
			propertyName = properties.getProperty(driver, test_steps);
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
		// Get Abbreviations
		try {
			nav.RoomClass(driver);
			test_steps.add("<b>****Getting Abbreviations****</b>");
			rc.getRoomClassAbbrivations(driver, test_steps, roomAbbri, RoomClass);
			app_logs.info(roomAbbri);
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

		// Create New Reservation
		try {
			nav.Setup(driver);
			nav.Reservation_Backward_1(driver);
			test_steps.add("<b>****Start Creating New Reservation****</b>");
			res.click_NewReservation(driver, test_steps);
			res.select_Dates(driver, test_steps, CheckInDate, CheckOutDate, Adults, Children, Rateplan, PromoCode,
					IsSplitRes);
			res.clickOnFindRooms(driver, test_steps);
			res.select_MRBRooms(driver, test_steps, RoomClass, IsAssign, Account);
			String loading = "(//div[@class='ir-loader-in'])";
			Wait.waitTillElementDisplayed(driver, loading);
			String TripAmount = res.getTripSummaryTripTotalAmountBeforeReservation(driver, test_steps);
			halfAmount_Paid = (Double.parseDouble(TripAmount) / 2);
			app_logs.info("Override Deposit Due Amount : " + halfAmount_Paid);

			res.InputdepositDue(driver, test_steps, String.valueOf(halfAmount_Paid));
			res.clickNext(driver, test_steps);
			yearDate = Utility.getFutureMonthAndYearForMasterCard(driver, test_steps);
			Random random = new Random();
			int x = random.nextInt(900);
			res.enter_MRB_MailingAddressForMRB(driver, test_steps, Salutation, GuestFirstName + x, GuestLastName + x,
					PhoneNumber, AltenativePhone, Email, Account, AccountType, Address1, Address2, Address3, City,
					Country, State, PostalCode, IsGuesProfile, IsAddMoreGuestInfo, IsSplitRes, rooms);
			if ((Account.equalsIgnoreCase("") || Account.isEmpty())) {
				res.enter_PaymentDetails(driver, test_steps, PaymentType, CardNumber, NameOnCard, yearDate);
			}
			app_logs.info(rooms);
			res.enter_MarketSegmentDetails(driver, test_steps, TravelAgent, MarketSegment, Referral);
			res.clickBookNow(driver, test_steps);
			reservation = res.get_ReservationConfirmationNumber(driver, test_steps);
			res.clickCloseReservationSavePopup(driver, test_steps);
			panelGuestName = res.getPanelStatusGuestName(driver);
			app_logs.info(panelGuestName);
			roomCharges = res.getStayInfoRoomCharges(driver, test_steps);
			app_logs.info(roomCharges);
			roomNos = res.getStayInfoRoomNo(driver, test_steps);
			app_logs.info(roomNos);
			guestNames = res.getStayInfoGuestName(driver, test_steps);
			app_logs.info(guestNames);
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
			generateGuestStatementStatus = res.generatGuestReportToggle(driver, test_steps, IsGuesRegistration);
			res.completeCheckInProcess(driver, test_steps);
			currentDate = res.getTodaysDate("MM/dd/yyyy", timeZone);
			previousDate = res.getPreviousDate("MM/dd/yyyy", timeZone);
			app_logs.info(currentDate + " " + previousDate);
			if (!policyName.equalsIgnoreCase("No Policy")) {

				// Added Notes and Click Log button
				checkIn_Amount = res.getCheckInAmount(driver, test_steps);
				app_logs.info(checkIn_Amount);
				Wait.wait10Second();
				res.addNotesAndClickLogORPayORAuthorizedButton(driver, test_steps, Notes);
				Wait.wait10Second();
				res.clickCloseButtonOfSuccessModelPopup(driver, test_steps);
				Wait.wait10Second();
			}

			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.getRoomsOnDetailPage(driver, newRooms);
				app_logs.info(newRooms);
			}

			test_steps.add("<b>****Start Verifying In-House Status****</b>");
			res.verifyReservationStatusStatus(driver, test_steps, "In-House");
			test_steps.add("<b>****Start Verifying Check-Out All Button ****</b>");
			res.verifyCheckOutAllButton(driver, test_steps);
			getTripTotalAmount = res.get_TripSummaryTripTotalChargesWithoutCurrency(driver, test_steps);
			app_logs.info("Trip Summary Total Amount : " + getTripTotalAmount);
			res.click_Folio(driver, test_steps);
			taxAmount = res.get_Taxes(driver, test_steps);
			app_logs.info("Folio -Tax is : " + taxAmount);
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
			app_logs.info(checkoutBalance);

			checkout_Balance = balanceOne;
			app_logs.info(checkout_Balance);

			checkout_Balance = balanceOne;
			app_logs.info(checkout_Balance);
			test_steps.add("Get Balance Before Check Out <b>" + checkout_Balance + "</b>");

			test_steps.add("<b>****Start Check-Out Room****</b>");
			res.stayInfoCheckOutRoom(driver, test_steps, CheckOutRoom, roomCharges, rooms, roomAbbri, guestNames,
					PhoneNumber, Email);
			String[] room = rooms.get(0).split(":");
			String checkOutRoom = room[1].trim();
			app_logs.info(" Room No: " + checkOutRoom);
			String roomClass = room[0].trim();
			app_logs.info(" Room Class: " + roomClass);
			String Message = "Do you wish to settle all guest's remaining charges against the primary guest's folio?";
			res.clickYesButtonOfCheckOutAllConfirmationMsg(driver, test_steps, Message, "CheckOutAll", checkOutRoom,
					roomClass);
			test_steps.add("<b>****Check-Out Window****</b>");
			res.verifyReservationPopWindow(driver, "Check Out", guestNames.get(0), "In-House", reservation, test_steps,
					"Check Out");

			test_steps.add("Successfully  Verified   Header bar In Check Out Pop Up While Checking out "
					+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/682487' target='_blank'>"
					+ "Click here to open TestRail: C682487</a> <br>");

			String checkOutMessage = "Are you sure you want to check out this reservation?";
			res.verifyMessageOfCheckOutWindow(driver, test_steps, checkOutMessage);
			generateGuestStatementStatus = res.generatGuestReportToggle(driver, test_steps, "Yes");
			res.clickCheckOutButton(driver, test_steps, checkoutBalance);
			/*
			 * #########################################################################
			 * #########################################################################
			 * ######################## Calculating Paid Amount, Balance
			 */
			double roomChargesAmount = Double.valueOf(roomCharges.get(0).replace("$", "").trim())
					+ Double.valueOf(taxAmount);
			app_logs.info(roomChargesAmount);

			int roomChagresConversion = (int) roomChargesAmount;
			String roomCharge = String.valueOf(roomChagresConversion);

			double tripTotalAmount = Double.valueOf(getTripTotalAmount);
			app_logs.info(tripTotalAmount);
			double totalAmount_Charged = 0.00;

			if (!policyName.equalsIgnoreCase("No Policy")) {

				totalAmount_Charged = halfAmount_Paid + checkIn_Amount;
			} else {
				totalAmount_Charged = halfAmount_Paid;
			}

			app_logs.info("Total Paid Amount After CheckIn : " + totalAmount_Charged);

			if (checkoutBalance > 0) {
				test_steps.add("<b>****Start Verifying Check-Out Payment Widnow ****</b>");
				 Wait.wait10Second();
				checkOutAmount = res.verifyCheckOutPaymentWindow(driver, test_steps, "Check Out Payment", PaymentType,
						TakePaymentType, roomCharge, "5454", NameOnCard, yearDate);

				getAmount = res.getAmountFromPaymentVerificationPage(driver);
				// Change Payment Method and Set as Main Payment
				res.setAsMainPayment(driver, test_steps, PaymentType, SetPaymentMethod, previousDate, "Check Out");
				paymentMethod = res.getPaymentMethod(driver, test_steps);
				// Added Notes and Click Log button
				res.addNotesAndClickLogORPayORAuthorizedButton(driver, test_steps, CheckOutNote);
				res.switchToTabOne(driver, test_steps);
				test_steps.add("<b>****Start Verifying Check-Out Success Widnow ****</b>");
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
				String pastDate = dateFormat.format(previousDate);
				app_logs.info("Previous Date: " + pastDate);

				balance_Amount = String.format("%.2f",
						(tripTotalAmount - (totalAmount_Charged + Double.parseDouble(getAmount))));
				app_logs.info("Total Balance Amount  : " + balance_Amount);
                 Wait.wait10Second();
				res.verificationSuccessfullWindow(driver, test_steps, "Check out Successful", "Approved",
						SetPaymentMethod, CheckOutNote, balance_Amount, TakePaymentType, getAmount, "Check Out",
						pastDate);
				res.clickCloseButtonOfSuccessModelPopup(driver, test_steps);

				paidAmount = String.format("%.2f", (totalAmount_Charged + Double.parseDouble(getAmount)));
				app_logs.info("Total Paid Amount  : " + paidAmount);

			} else {

				res.switchToTabOne(driver, test_steps);
				balance_Amount = String.format("%.2f", (tripTotalAmount - totalAmount_Charged));
				app_logs.info("Total Balance Amount  : " + balance_Amount);
				paidAmount = String.format("%.2f", totalAmount_Charged);
				app_logs.info("Total Paid Amount  : " + paidAmount);

			}
			String[] roomClassName = rooms.get(0).split(":");
			String checkOutRoomClassName = roomClassName[0].trim();
			app_logs.info(" Room No: " + checkOutRoomClassName);

			test_steps.add("<b>****Start Verifying Stay Info Rool Back Button****</b>");
			res.verifyStayInforRollBackButton(driver, test_steps, checkOutRoomClassName);

			test_steps.add("<b>****Start Verifying DEPARTED Status****</b>");
			res.verifyReservationStatusStatus(driver, test_steps, "DEPARTED");
			test_steps.add("<b>****Start Verifying Roll Back All Button After Check-Out Room****</b>");
			res.verifyRollBackButton(driver, test_steps);
			// Verified Trip Summary Total

			test_steps.add("<b>****Start Verifying Trip Summary****</b>");

			String tripSummaryTotal = res.get_TripSummaryTripTotalChargesWithoutCurrency(driver, test_steps);
			app_logs.info("Trip Summary Total: " + tripSummaryTotal);

			String tripSummaryPaidAgain = res.getTripSummaryPaidAmount(driver, test_steps);
			app_logs.info("Trip Summary Paid: " + tripSummaryPaidAgain);
			res.verifyTripSummaryPaidAmount(driver, test_steps, tripSummaryPaidAgain, paidAmount);

			String tripSummaryBalance = res.get_TripSummaryBalance_Amount(driver, test_steps);
			app_logs.info("Trip Summary Balance: " + tripSummaryBalance);
			res.verifyTripSummaryBalanceAmount(driver, test_steps, tripSummaryBalance, balance_Amount);

			test_steps.add("<b>****Start Verifying Reservation Status Balance****</b>");
			res.verifyReservationStatusBalanceForCheckOut(driver, test_steps, balance_Amount);

			if (checkoutBalance > 0) {
				test_steps.add("<b>****Start Verifying Folio Details Line Item****</b>");
				res.switchToTabOne(driver, test_steps);
				res.click_Folio(driver, test_steps);
				res.click_FolioDetail_DropDownBox(driver, test_steps);
				String[] abbs = roomAbbri.get(0).split(":");
				String Finalabb = abbs[1].trim();
				app_logs.info(" Abb No: " + Finalabb);
				if (res.isRoomReserved || res.isRoomUnAssigned) {
					res.clickFolioDetailOptionValue(driver, test_steps, Finalabb, newRooms.get(0));
				} else {
					res.clickFolioDetailOptionValue(driver, test_steps, Finalabb, rooms.get(0));
				}

				// Verify Line Item
				res.verifyFolioLineItem(driver, test_steps, previousDate, SetPaymentMethod, SetPaymentMethod,
						getAmount);

				test_steps.add("<b>****Start Verifying Primary Guest Pay Secondary Guest Charges****</b>");
				System.out.print(guestNames);
				String[] secondaryGuestName = guestNames.get(1).split("Mr.");
				String description = "Res #" + reservation + " -" + secondaryGuestName[1];
				app_logs.info(description);
				double paidAmount = Double.valueOf(tripSummaryPaidAgain);
				double secondGAmout = paidAmount - roomChargesAmount;
				app_logs.info(secondGAmout);
				String secondCharges = String.format("%.2f", secondGAmout);

				String secondaryGuestAmount = "-" + secondCharges;
				app_logs.info(secondaryGuestAmount);
				res.verifyFolioLineItem(driver, test_steps, previousDate, "Reservation", description,
						secondaryGuestAmount);

			}

			test_steps.add("<b>****Start Verifying History****</b>");
			// Click History Tab
			res.click_History(driver, test_steps);
			String historyDesc = "Checked out this reservation";
			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.verifyHistoryForCheckin(driver, test_steps, historyDesc, roomAbbri.get(0), newRooms.get(0),
						"CheckOutSingleRoom", roomAbbri, newRooms);

			} else {
				res.verifyHistoryForCheckin(driver, test_steps, historyDesc, roomAbbri.get(0), rooms.get(0),
						"CheckOutSingleRoom", roomAbbri, rooms);

			}
			if (checkoutBalance > 0) {
				String[] abbs = roomAbbri.get(0).split(":");
				String abbreviation = abbs[1].trim();
				app_logs.info(" Room No: " + abbreviation);

				if (res.isRoomReserved || res.isRoomUnAssigned) {

					res.verifyHistoryDescriptionWithPayment(driver, test_steps, getAmount, "Check In", abbreviation,
							newRooms.get(0), paymentMethod);

				} else {

					res.verifyHistoryDescriptionWithPayment(driver, test_steps, getAmount, "Check In", abbreviation,
							rooms.get(0), paymentMethod);

				}

			}

			test_steps.add("<b>****Start Verifying Updated By on Payment Item Detail Popup****</b>");
			res.click_Folio(driver, test_steps);
			res.click_FolioDetail_DropDownBox(driver, test_steps);
			String[] abbs = roomAbbri.get(0).split(":");
			String abbreviation = abbs[1].trim();
			app_logs.info(" Abb No: " + abbreviation);
			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.clickFolioDetailOptionValue(driver, test_steps, abbreviation, newRooms.get(0));
			} else {
				res.clickFolioDetailOptionValue(driver, test_steps, abbreviation, rooms.get(0));
			}
			res.verifyPaymentDetailUpdateBy(driver, NameOnCard, UserName, PaymentType);
			res.clickPaymentDetailCancel(driver, test_steps);
			test_steps.add("Successfully  Verified  Update By : <b>" + "autouser" + "</b> "
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

				DateFormat dateFormat = new SimpleDateFormat("E, MMM dd, yyyy");
				dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
				String myDate = dateFormat.format(currentDate);
				app_logs.info(myDate);
				String[] name = guestNames.get(0).split(" ");
				String actualName = name[1] + " " + name[2];
				app_logs.info(actualName);
				String[] roomClassOne = rooms.get(0).split(":");
				String roomNo = roomClassOne[1].trim();
				String room_Class = roomClassOne[0].trim();
				app_logs.info(" Room No: " + roomNo);
				app_logs.info(" Room Class: " + room_Class);

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
				report.Report_Verify_ReportContent(driver, reportContant, room_Class, test_steps);
				test_steps.add("Room :  <b>" + room_Class + "</b>");
				Utility.app_logs.info("Room  :  " + room_Class);
				report.Report_Verify_ReportContent(driver, reportContant, roomNo, test_steps);
				test_steps.add("Room No:  <b>" + roomNo + "</b>");
				Utility.app_logs.info("Room No:  " + roomNo);
				report.Report_Verify_ReportContent(driver, reportContant, reservation, test_steps);
				test_steps.add("Reservation #  :  <b>" + reservation + "</b>");
				Utility.app_logs.info("Reservation #  :  " + reservation);
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

		return Utility.getData("VerifyPrimaryGuestPaySecondaryG", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
