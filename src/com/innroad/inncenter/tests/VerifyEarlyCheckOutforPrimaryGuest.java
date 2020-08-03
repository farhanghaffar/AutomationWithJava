package com.innroad.inncenter.tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.innroad.inncenter.pageobjects.Policies;
import com.innroad.inncenter.pageobjects.Properties;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.TaskManagement;
import com.innroad.inncenter.pageobjects.Tax;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;


public class VerifyEarlyCheckOutforPrimaryGuest extends TestCore {

	private WebDriver driver = null;
	public static String test_name = "";
	public static String test_description = "";
	public static String test_catagory = "";
	String testName = null;
	ArrayList<String> test_steps = new ArrayList<>();
	CPReservationPage res = new CPReservationPage();
	Navigation nav = new Navigation();
	String reservation = null, panelGuestName = null, status = null, stayInfoSecondayGuestName = null,
			checkOutAmount = null, roomClassName = null, balanceAmount = null, tripTotalAmounts = null,
			taxAmount = null, yearDate = null, policyName = null, timeZone = null, propertyName = null,
			checkInAmount = null, getAmount = null, paidAmount = null;
	TaskManagement task_mang = new TaskManagement();
	ArrayList<String> roomCost = new ArrayList<String>();
	ArrayList<String> rooms = new ArrayList<String>();
	ArrayList<String> roomAbbri = new ArrayList<String>();
	ArrayList<String> newRooms = new ArrayList<String>();
	List<String> roomCharges = new ArrayList<String>();
	List<String> guestNames = new ArrayList<String>();
	List<String> roomNos = new ArrayList<String>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	Policies policies = new Policies();
	RoomClass rc = new RoomClass();
	Properties properties = new Properties();
	Tax tax = new Tax();
	int halfAmountPaid = 0, checkoutBalance = 0;
	String buttonText = null, paymentMethod = null;
	Date currentDate = null, previousDate = null;

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void earlyCheckOutforPrimaryGuest(String URL, String ClientId, String UserName, String Password,
			String RoomClass, String CheckInDate, String CheckOutDate, String Adults, String Children, String Rateplan,
			String PromoCode, String IsSplitRes, String IsAssign, String Account, String Salutation,
			String GuestFirstName, String GuestLastName, String PhoneNumber, String AltenativePhone, String Email,
			String AccountType, String Address1, String Address2, String Address3, String City, String Country,
			String State, String PostalCode, String IsGuesProfile, String IsAddMoreGuestInfo, String PaymentType,
			String CardNumber, String NameOnCard, String TravelAgent, String MarketSegment, String Referral,
			String Notes, String CheckOutRoom, String SetPaymentMethod, String CheckOutNote, String TakePaymentType) {
		test_name = "VerifyEarlyCheckOutforPrimaryGuest";
		test_description = "Verify Early CheckOut for Primary Guest<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/681835' target='_blank'>"
				+ "Click here to open TestRail: C681835</a><br>";
		test_catagory = "CPReservation_CheckOutPrimaryGuest";
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
					checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(4), "MM/dd/yyyy", "dd/MM/yyyy"));
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
			currentDate = res.getTodaysDate("MM/dd/yyyy", timeZone);
			previousDate = res.getPreviousDate("MM/dd/yyyy", timeZone);
			System.out.println(currentDate + " " + previousDate);


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

//Get Abbreviations
		try {
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
			stayInfoSecondayGuestName = res.getStayInfoSecondGuestName(driver);
			roomCharges = res.getStayInfoRoomCharges(driver, test_steps);
			roomNos = res.getStayInfoRoomNo(driver, test_steps);
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
				app_logs.info("Policy Exist");
			} else {
				test_steps.add("Policy Doesn't Exist");
				app_logs.info("Policy Doesn't Exist");
			}
			test_steps.add("<b>****Start CheckIn Reservation****</b>");
			res.clickCheckInAllButton(driver, test_steps);
			res.generatGuestReportToggle(driver, test_steps, "No");
			res.completeCheckInProcess(driver, test_steps);
			

			if (!policyName.equalsIgnoreCase("No Policy")) {
				res.checkedOverRideCheckInAmountCheckBox(driver, test_steps, "Yes");
				// Change Payment Method and Set as Main Payment
				checkInAmount = res.get_Checkin_Amount(driver, test_steps);
				System.out.println("CheckIn Half Amount : " + checkInAmount);
				res.inputAmountWhileCheckINAndCheckOut(driver, test_steps, checkInAmount);
				res.selectType(driver, test_steps, TakePaymentType);
				// Added Notes and Click Log button
				res.addNotesAndClickLogORPayORAuthorizedButton(driver, test_steps, Notes);
				res.clickCloseButtonOfSuccessModelPopup(driver, test_steps);
			}

			
			app_logs.info("Room Reserved Status: "+ res.isRoomReserved);
			app_logs.info("Room UnAssigned Status: "+ res.isRoomUnAssigned);
			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.getRoomsOnDetailPage(driver, newRooms);
				app_logs.info(newRooms);
			}

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

//CheckOut Primary Guest
		try {
			status = res.getPanelStatusStatusName(driver);
			test_steps.add("<b>****Start Verifying In-House Status****</b>");
			res.verifyReservationStatusStatus(driver, test_steps, "In-House");
			test_steps.add("<b>****Start Verifying Check-Out All Button ****</b>");
			res.verifyCheckOutAllButton(driver, test_steps);
			tripTotalAmounts = res.get_TripSummaryTripTotalChargesWithoutCurrency(driver, test_steps);
			System.out.println("Trip Summary Total Amount : " + tripTotalAmounts);
			System.out.println("Stay Info Room Charges" + roomCharges);

			res.click_Folio(driver, test_steps);
			taxAmount = res.get_Taxes(driver, test_steps);
			System.out.println("Folio -Tax is : " + taxAmount);
			res.click_DeatilsTab(driver, test_steps);

			// get balance
			String balance = res.get_TripSummaryBalance_Amount(driver, test_steps);
			double balanceOne = Double.valueOf(balance);
			checkoutBalance = (int) balanceOne;
			test_steps.add("Get Balance Before Check Out <b>" + checkoutBalance + "</b>");
			app_logs.info(checkoutBalance);

			if (res.isRoomReserved || res.isRoomUnAssigned) {
				test_steps.add("<b>****Start Check-Out Room****</b>");
				res.stayInfoCheckOutRoom(driver, test_steps, CheckOutRoom, roomCharges, newRooms, roomAbbri, guestNames,
						PhoneNumber, Email);
			} else {
				test_steps.add("<b>****Start Check-Out Room****</b>");
				res.stayInfoCheckOutRoom(driver, test_steps, CheckOutRoom, roomCharges, rooms, roomAbbri, guestNames,
						PhoneNumber, Email);
			}
			System.out.println(guestNames);

			String[] room = rooms.get(0).split(":");
			String checkOutRoom = room[1].trim();
			System.out.println(" Room No: " + checkOutRoom);
			String roomClass = room[0].trim();
			System.out.println(" Room Class: " + roomClass);
			String message = "Do you wish to settle all guest's remaining charges against the primary guest's folio?";
			res.clickYesButtonOfCheckOutAllConfirmationMsg(driver, test_steps, message, "CheckOutAll", checkOutRoom,
					roomClass);
			test_steps.add("<b>****Check-Out Window****</b>");
			res.verifyReservationPopWindow(driver, "Check Out", guestNames.get(0), "In-House", reservation, test_steps,
					"Check Out");
			String checkOutMsg = "Are you sure you want to check out this reservation?";
			res.verifyMessageOfCheckOutWindow(driver, test_steps, checkOutMsg);
			res.Verify_VoidRoomCharge(driver, test_steps, "Check Out");
			res.checkedVoidRoomChargeCheckBox(driver, test_steps);
			res.generatGuestReportToggle(driver, test_steps, "No");
			res.clickCheckOutButton(driver, test_steps, checkoutBalance);
			double checkOutPaidAmount = Double.valueOf(roomCharges.get(0).replace("$", "").trim())
					+ Double.valueOf(taxAmount);
			int amtCheckOutPaid = (int) checkOutPaidAmount;
			String checkOutPaidAmounts = String.valueOf(amtCheckOutPaid);
			System.out.println("Actual Room Charges: " + checkOutPaidAmounts);

			int totalAmountPaidYet = 0;
			totalAmountPaidYet = halfAmountPaid + Integer.parseInt(checkOutPaidAmounts);
			String totalAmountPaid = String.valueOf(totalAmountPaidYet);
			System.out.println("Total Paid Amount: " + totalAmountPaid);
			if (checkoutBalance > 0) {
				test_steps.add("<b>****Start Verifying Check-Out Payment Widnow ****</b>");
				checkOutAmount = res.verifyCheckOutPaymentWindow(driver, test_steps, "Check Out Payment", PaymentType,
						TakePaymentType, checkOutPaidAmounts, "5454", NameOnCard, yearDate);
				getAmount = res.getAmountFromPaymentVerificationPage(driver);
				app_logs.info("Current Date: "+ currentDate);
				app_logs.info("Previous Date: "+ previousDate);
				// Change Payment Method and Set as Main Payment
				res.setAsMainPayment(driver, test_steps, PaymentType, SetPaymentMethod, currentDate, "Check Out");

				paymentMethod = res.getPaymentMethod(driver, test_steps);
				// Added Notes and Click Log button
				res.addNotesAndClickLogORPayORAuthorizedButton(driver, test_steps, CheckOutNote);
				test_steps.add("<b>****Start Verifying Check-Out Success Widnow ****</b>");
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
				String pastDate = dateFormat.format(currentDate);
				System.out.println("Previous Date: " + pastDate);
				res.commonMethodForSuccessfullWindowVerification(driver, test_steps, "Check out Successful", "Approved",
						SetPaymentMethod, CheckOutNote, "0", TakePaymentType, checkOutAmount, "Check Out", pastDate);
				res.clickCloseButtonOfSuccessModelPopup(driver, test_steps);
			}
			
			String[] roomClassName = rooms.get(0).split(":");
			String checkOutRoomClassName = roomClassName[0].trim();
			System.out.println(" Room No: " + checkOutRoomClassName);
			test_steps.add("<b>****Start Verifying Stay Info Roll Back Button****</b>");
			res.verifyStayInforRollBackButton(driver, test_steps, checkOutRoomClassName);
			test_steps.add("<b>****Start Verifying DEPARTED Status****</b>");
			res.verifyReservationStatusStatus(driver, test_steps, "DEPARTED");
			test_steps.add("<b>****Start Verifying Roll Back All Button After Check-Out Room****</b>");
			res.verifyRollBackButton(driver, test_steps);

			String paidAmt = res.getTripSummaryPaidAmount(driver, test_steps);
			app_logs.info(paidAmt);
			double tripTotalAmount = Double.valueOf(tripTotalAmounts);
			app_logs.info(tripTotalAmount);
			String balanceAmount = null;
			
			balanceAmount = String.format("%.2f", (tripTotalAmount - Double.valueOf(paidAmt)));
			app_logs.info("Total Balance Amount  : " + balanceAmount);

			System.out.print(guestNames);
			String tripSummaryPaidAmount = res.getTripSummaryPaidAmount(driver, test_steps);
			app_logs.info(tripSummaryPaidAmount);
			res.click_Folio(driver, test_steps);
			String taxAmount = res.get_Taxes(driver, test_steps);
			app_logs.info("Folio -Tax  : " + taxAmount);
			String roomChargesAmount = res.getFolioRoomCharges(driver);
			app_logs.info("Folio -Tax  : " + roomChargesAmount);

			if (checkoutBalance > 0) {
				System.out.print(guestNames);
				test_steps.add("<b>****Start Verifying Folio Details Line Item****</b>");
				res.click_FolioDetail_DropDownBox(driver, test_steps);
				String[] abbs = roomAbbri.get(0).split(":");
				String Finalabb = abbs[1].trim();
				System.out.println(" Abb No: " + Finalabb);
				if (res.isRoomReserved || res.isRoomUnAssigned) {
					res.clickFolioDetailOptionValue(driver, test_steps, Finalabb, newRooms.get(0));
				} else {
					res.clickFolioDetailOptionValue(driver, test_steps, Finalabb, rooms.get(0));
				}

				// Verify Line Item

				Date current_Date=new SimpleDateFormat("dd/MM/yyyy").parse(checkInDates.get(0));  
				app_logs.info(current_Date);
				res.verifyFolioLineItem(driver, test_steps, current_Date, SetPaymentMethod, SetPaymentMethod,
						checkOutAmount);


				test_steps.add("<b>****Start Verifying Negative  Payment  For Secondary Guest ****</b>");
				String[] secondaryGuestName = guestNames.get(1).split("Mr.");
				String description = "Res #" + reservation + " -" + secondaryGuestName[1];
				System.out.println(description);
				double paidAmount = Double.valueOf(tripSummaryPaidAmount);
				double secondGAmout = paidAmount - (Double.valueOf(roomChargesAmount) + Double.valueOf(taxAmount));
				System.out.println(secondGAmout);
				String secondCharges = String.format("%.2f", secondGAmout);
				String secondaryGuestAmount = "-" + secondCharges;
				System.out.println(secondaryGuestAmount);
    			res.verifyFolioLineItem(driver, test_steps, current_Date, "Reservation", description,
						secondaryGuestAmount);

				test_steps.add("<b>****Start Verifying Void Room Charges for Unused Nights Label****</b>");
				res.checkedDisplayVoidItem(driver, test_steps);
				List<LocalDate> date = res.getDatesBetweenFromAndToDate(driver, test_steps, CheckInDate, CheckOutDate);
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("E MMM dd, yyyy");
				for (int i = 0; i < date.size(); i++) {
					String Date = date.get(i).format(dateFormat);
					res.verifyDisplayVoidItems(driver, test_steps, Date, "Early Checkout", "0");

				}
			} else {
				System.out.print(guestNames);
				test_steps.add("<b>****Start Verifying Folio Details Line Item****</b>");
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

				// Verify Line Item
				Date date=new SimpleDateFormat("dd/MM/yyyy").parse(checkInDates.get(0));  
				app_logs.info(date);
				
				res.verifyFolioLineItem(driver, test_steps, date, PaymentType, NameOnCard, checkOutAmount);
				
				System.out.print(guestNames);
				test_steps.add("<b>****Start Verifying Negative  Payment  For Secondary Guest ****</b>");

				System.out.print(guestNames);
				String[] secondaryGuestName = guestNames.get(1).split("Mr.");
				String description = "Res #" + reservation + " -" + secondaryGuestName[1];
				System.out.println(description);
				double paidAmount = Double.valueOf(tripSummaryPaidAmount);

				double secondGAmout = paidAmount - (Double.valueOf(roomChargesAmount) + Double.valueOf(taxAmount));
				System.out.println(secondGAmout);
				String secondCharges = String.format("%.2f", secondGAmout);
				String secondaryGuestAmount = "-" + secondCharges;
				System.out.println(secondaryGuestAmount);
				res.verifyFolioLineItem(driver, test_steps, date, "Reservation", description,
						secondaryGuestAmount);
			}

			double totalRoomCalculate = Double.valueOf(taxAmount) + Double.valueOf(roomChargesAmount);
			app_logs.info(totalRoomCalculate);
			String totalRoomCharge = String.format("%.2f", totalRoomCalculate);
			app_logs.info("Folio -Tax  : " + totalRoomCharge);

			test_steps.add("<b>****Start Verifying History****</b>");
			// Click History Tab
			res.click_History(driver, test_steps);
			String historyDesc = "Checked out this reservation";
			if (res.isRoomReserved || res.isRoomUnAssigned) {

				res.verifyHistoryForCheckin(driver, test_steps, historyDesc, roomAbbri.get(0), newRooms.get(0),
						"CheckOutSingleRoom", roomAbbri, newRooms);
			} else {

				res.verifyHistoryForCheckin(driver, test_steps, historyDesc, roomAbbri.get(0), rooms.get(0),
						"CheckOutSingleRoom", roomAbbri, newRooms);
			}

			if (res.confirmCheckOutWithPayment) {
				String[] abbs = roomAbbri.get(0).split(":");
				String Finalabb = abbs[1].trim();
				System.out.println(" Room No: " + Finalabb);
				if (res.isRoomReserved || res.isRoomUnAssigned) {

					res.verifyHistoryDescriptionWithPayment(driver, test_steps, getAmount, "Check In", Finalabb,
							newRooms.get(0), paymentMethod);

				} else {

					res.verifyHistoryDescriptionWithPayment(driver, test_steps, getAmount, "Check In", Finalabb,
							rooms.get(0), paymentMethod);

					String roomCharge = String.valueOf(checkOutPaidAmount);
					app_logs.info(roomCharge);
					res.verifyChangedRoomChargesOnHistory(driver, test_steps, roomCharge, totalRoomCharge);

					DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy");
					DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("MMM d, yyyy");
					int CD = res.StartDate.plusDays(1).getDayOfMonth();
					String Day = String.valueOf(CD);
					int CD1 = res.EndDate.getDayOfMonth();
					String Day1 = String.valueOf(CD1);
					String StartDate = null, EndDate = null;
					if (Day.length() == 1) {
						StartDate = res.StartDate.plusDays(1).format(dateFormat1);
						System.out.println(StartDate);
					} else {
						StartDate = res.StartDate.plusDays(1).format(dateFormat);
						System.out.println(StartDate);
					}

					if (Day1.length() == 1) {
						EndDate = res.EndDate.format(dateFormat1);
						System.out.println(EndDate);
					} else {
						EndDate = res.EndDate.format(dateFormat);
						System.out.println(EndDate);
					}

					res.verifyChangedCheckOutDateOnHistoryTab(driver, test_steps, EndDate, StartDate);

				}

			}
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Check Out", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Check Out", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getData("VerifyEarlyCheckOutforPrimaryGu", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}
}
