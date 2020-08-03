package com.innroad.inncenter.tests;

import java.util.ArrayList;
import java.util.Random;

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
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.Tax;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.relevantcodes.extentreports.ExtentTest;

public class VerifyCancelCPReservationMRB extends TestCore {

	private WebDriver driver = null;
	public static String test_name = "";
	public static String test_description = "";
	public static String test_catagory = "";
	ArrayList<String> testSteps = new ArrayList<>();
	CPReservationPage res = new CPReservationPage();
	Navigation nav = new Navigation();
	RoomClass rc = new RoomClass();
	Policies policies = new Policies();
	ArrayList<String> roomAbbri = new ArrayList<String>();
	ArrayList<String> roomCost = new ArrayList<String>();
	ArrayList<String> rooms = new ArrayList<String>();
	ArrayList<String> newRooms = new ArrayList<String>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	RoomClass roomClass = new RoomClass();
	Rate rate = new Rate();
	Tax tax = new Tax();
	String policyname = null, yearDate = null, checkInDate = null, checkOutDate = null;
	Double depositAmount = 0.0;
	String reservation = null, panelGuestName = null, status = null, stayInfoSecondayGuestName = null;
	String tripSummaryRoomCharges = null, roomClassName = null, stayInforAdditionalRoomCharges = null,
			stayInfoPrimaryRoomCharges = null, tripSummaryPaidAmount = null;
	String foiloRoomCharges = null, folioBalance = null, paymentMethod = null;

	
	String roomClassNameWithoutNum = "RoomClass";
	String roomClassAvvWithoutNum = "RoomAbb";
	String rateNameWithoutNum = "AutoRate_";
	String randomNum1 = null;
	String randomNum2 = null;
	String roomClassName1 = null;
	String roomClassAbb1 = null;
	String maxAdults = "2", maxPersons = "2", roomQuantity = "2";
	String roomClassName2 = null;
	String roomClassAbb2 = null, testName=null ;
	ArrayList<String> roomNumberAssigned = new ArrayList<>();
	String rateName = null;
	
	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void verifyCancelCPReservationMRB(String URL,String ClientId ,String	UserName,
			String Password	,String PolicyType	,String PolicyTypeAll,String PolicyName,String PolicyText,
			String PolicyDesc	,String RoomChargesPercentage,String TaxName,String DisplayName,String	TaxDescription	,
			String TaxPercent	,String TaxCategory,String	TaxLedgerAccount,String	RoomClass,String	CheckInDate,
			String CheckOutDate	,String Adults,String	Children,String	Rateplan,String	PromoCode,String IsAssign,String IsDepositOverride,
			String DepositOverrideAmount,String	Salutation,String	GuestFirstName,String	GuestLastName,String	PhoneNumber	,
			String AltenativePhone	,String Email,String	Account,String	AccountType,String	Address1,
			String Address2,String	Address3,String	City,String	Country	,String State,String 	PostalCode	,String IsGuesProfile,
			String IsAddMoreGuestInfo,String	IsSplitRes,String	PaymentType,String	CardNumber,
			String NameOnCard,String	CardExpDate	,String TravelAgent	,String MarketSegment,String	Referral,String	CancelPolicyType,
			String HeaderTitle,String	Reason,String	ValidationMsg,String	CancellationPayment	,String CardNo,String	Type,String	PaymentTypeTwo,
			String Message,String	Notes,String	SuccessMsg	,String Status	,String BalanceMoney,String	CancelledStatus	,
			String Subject	,String SingleCancel,String	CancelRoom	,String CancelRoomNo	,String PolicyTypeCheckOut,String WithPolicy) {

		
		rooms.removeAll(rooms); 
		roomNumberAssigned.removeAll(roomNumberAssigned);
		roomAbbri.removeAll(roomAbbri);
		
		if (WithPolicy.equals("Yes"))
		{
		test_name = "VerifyCancelCPMRBReservation";
		test_description = "Verify CP MRB  Cancel Reservation <br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/681844' target='_blank'>"
				+ "Click here to open TestRail: C681844</a>";
		test_catagory = "CPReservation_Cancel";
		 testName = test_name;

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");
		}
		else if(WithPolicy.equals("No"))
		{
			test_name = "VerifyCPMRBReservationCancelRoom";
			test_description = "Verify CP MRB Reservation Cancel Room <br>"
					+ "<a href='https://innroad.testrail.io/index.php?/cases/view/681844' target='_blank'>"
					+ "Click here to open TestRail: C681844</a>";
			test_catagory = "CPReservation_Cancel";
			 testName = test_name;

			app_logs.info("##################################################################################");
			app_logs.info("EXECUTING: " + testName + " TEST.");
			app_logs.info("##################################################################################");
		}
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
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
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
			checkInDate = checkInDates.get(0) + "|" + checkInDates.get(1);
			checkOutDate = checkOutDates.get(0) + "|" + checkOutDates.get(1);
			app_logs.info(checkInDate);
			app_logs.info(checkOutDate);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Get CheckIN CheckOutDate", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Get CheckIN CheckOutDate", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		try {

			 String policyNameWithoutNum = PolicyName;
			 randomNum1 = Utility.GenerateRandomNumber();
			 randomNum2 = Utility.GenerateRandomNumber();
			 roomClassName1 = roomClassNameWithoutNum + "One_" + randomNum1;
			 roomClassAbb1 = roomClassAvvWithoutNum+"One_" + randomNum1;
			 roomClassName2 = roomClassNameWithoutNum + "Two_" + randomNum2;
			 roomClassAbb2 = roomClassAvvWithoutNum+"Two_" + randomNum2;
			 rateName = rateNameWithoutNum + randomNum1;
			
			
			testSteps.add("========== Creating 1st room class ==========");
			nav.Setup(driver, testSteps);
			nav.RoomClass(driver, testSteps);
			roomClass.SearchButtonClick(driver);
			testSteps.add("Click on Search Button");
			app_logs.info("Click on Search Button");
			roomClass.SearchRoomClass(driver, roomClassNameWithoutNum, testSteps);
			roomClass.deleteRoomClass(driver, roomClassNameWithoutNum);
			nav.clickOnNewRoomClassButton(driver, testSteps);
			/*roomClass.CreateRoomClass(driver, roomClassName1, roomClassAbb1, null, maxAdults, maxPersons, roomQuantity,
					test, testSteps);*/
			roomClass.create_RoomClass(driver, roomClassName1, roomClassAbb1, null, maxAdults, maxPersons, roomQuantity,
					test, testSteps);
			roomNumberAssigned.add(Utility.RoomNo);
			roomClass.CloseOpenedRoomClass(driver, roomClassName1, testSteps);
			nav.NewRoomClass(driver);
			testSteps.add("========== Creating 2nd room class ==========");
			roomClass.create_RoomClass(driver, roomClassName2, roomClassAbb2, null, maxAdults, maxPersons, roomQuantity,
					test, testSteps);
			roomNumberAssigned.add(Utility.RoomNo);
			for (int i = 0; i < roomNumberAssigned.size(); i++) {
				roomNumberAssigned.get(i);
			}

			app_logs.info(roomNumberAssigned);
			roomAbbri.add(roomClassName1 + ":" + roomClassAbb1);
			roomAbbri.add(roomClassName2 + ":" + roomClassAbb2);
			app_logs.info(roomAbbri);
			testSteps.add("========== Creating rate plan and Associating with room class ==========");
			nav.Inventory(driver);
			nav.Rates1(driver);

			rate.deleteRates(driver, rateNameWithoutNum);			

			rate.create_Rate(driver, rateName, maxAdults, maxPersons, "100", maxAdults, maxPersons, "RateDisplay",
					"AutomationRatePolicy", "AutomationRateDescription", roomClassName1, roomClassName2, testSteps);

				testSteps.add("========== Creating policy and Associating with  room class ==========");
				nav.Inventory(driver, testSteps);
				nav.policies(driver, testSteps);
				policies.deleteAllPolicies(driver, testSteps, PolicyType, policyNameWithoutNum);
				policies.Select_PolicyType(driver, "All", testSteps);
				policies.NewPolicybutton(driver, PolicyType, testSteps);
				policyname = policyNameWithoutNum + randomNum1;
				policies.Enter_Policy_Name(driver, policyname, testSteps);
				policies.Enter_Policy_Desc(driver, PolicyText,PolicyDesc);
				testSteps.add("Enter Policy Text and Description: <b>" + PolicyText + " AND "
						+ PolicyDesc+ "</b>");
				policies.Cancellation_policy_Attributes(driver, "Room Charges", RoomChargesPercentage,
						"Beyond", "0", testSteps);
				policies.Associate_Sources(driver);
				testSteps.add(" Associate Sources");
				policies.Associate_Seasons(driver, "All Year Season");
				testSteps.add(" Associate Seasons");
				policies.Associate_RoomClasses(driver, roomClassName1);
				testSteps.add(" Associate Room Classes: " + roomClassName1);
				policies.Associate_RoomClasses(driver, roomClassName2);
				testSteps.add(" Associate Room Classes: " + roomClassName2);
				String rateplan[] = Rateplan.split("\\|");
				policies.Associate_RatePlan(driver, rateplan[0]);
				testSteps.add(" Associate Rate Plans: " + rateplan[0]);
				policies.Save_Policy(driver);
				testSteps.add("Click Save Button");
				testSteps.add("Policy Saved Successfully: <b>" + policyname + "</b>");
				
		

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Create room class and Rates", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Create room class and Rates", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		// Create Tax for Cancel Fee
		try {
			if (WithPolicy.equals("Yes"))
			{
			testSteps.add("<b>****Start Creating Tax****</b>");
			nav.Setup(driver);
			testSteps.add("Navigate to Setup");
			nav.Taxes(driver);
			testSteps.add("Navigate to Taxes");
			boolean isExist = tax.verifyTaxExist(driver);
			if (isExist) {
				testSteps.add("Taxes Exist No Need to Create Tax");
				tax.openTax(driver);
				boolean isExistLeadger = tax.verifyLegerAccountExist(driver, TaxLedgerAccount);
				if (!isExistLeadger) {
					tax.AttachLegderAccount(driver, TaxLedgerAccount);
				}
			} else {
				tax.Click_NewItem(driver, testSteps);
				String TaxName1 = TaxName + Utility.getTimeStamp();
				ExtentTest test1 = null;
				tax.createTax(driver, test1, TaxName1, DisplayName,
						TaxDescription, TaxPercent,
						TaxCategory, TaxLedgerAccount, false, true, false);
				testSteps.add("Successfull Created Tax :<b> " + TaxName1 + "</b>");
			}

			}
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Create New Tax", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Create New Tax", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}


		// Create New Reservation
		try {
			nav.Reservation_Backward_1(driver);
			app_logs.info("Navigate to Reservation");

			testSteps.add("<b>****Start Creating New Reservation****</b>");
			res.click_NewReservation(driver, testSteps);
			res.select_Dates(driver, testSteps, checkInDate, checkOutDate, Adults,
					Children, Rateplan,PromoCode,
					IsSplitRes);
			res.clickOnFindRooms(driver, testSteps);
			res.selectRoom1(driver, testSteps, roomClassName1, roomNumberAssigned.get(0), "");
			res.selectRoom1(driver, testSteps, roomClassName2, roomNumberAssigned.get(1), "");
			
			depositAmount = res.deposit(driver, testSteps, IsDepositOverride,
					DepositOverrideAmount);
			res.clickNext(driver, testSteps);
			yearDate = Utility.getFutureMonthAndYearForMasterCard(driver, testSteps);
			Random random = new Random();
			int x = random.nextInt(900);
			res.enter_MRB_MailingAddressForMRB(driver, testSteps,Salutation,

	GuestFirstName + x, GuestLastName + x,PhoneNumber,AltenativePhone,Email, Account, AccountType,Address1, Address2, Address3,

					City, Country,State,
					PostalCode,IsGuesProfile,
					IsAddMoreGuestInfo, IsSplitRes, rooms);
			if (Account.equalsIgnoreCase("") || Account.isEmpty()) {
				res.enter_PaymentDetails(driver, testSteps, PaymentType,
						CardNumber, NameOnCard, yearDate);
			}
			app_logs.info(rooms);

			res.enter_MarketSegmentDetails(driver, testSteps, TravelAgent,
					MarketSegment, Referral);
			res.clickBookNow(driver, testSteps);
			reservation = res.get_ReservationConfirmationNumber(driver, testSteps);
			res.clickCloseReservationSavePopup(driver, testSteps);			
			panelGuestName = res.getPanelStatusGuestName(driver);
			status = res.getPanelStatusStatusName(driver);
			
			if (WithPolicy.equals("Yes"))
			{
			tripSummaryRoomCharges = res.get_TripSummaryRoomChargesWithoutCurrency(driver, testSteps);
			app_logs.info("Trip Summary Room Charge: " + tripSummaryRoomCharges);
			tripSummaryPaidAmount = res.getTripSummaryPaidAmount(driver, testSteps);
			app_logs.info("Trip Summary Paid: " + tripSummaryPaidAmount);
			res.click_Folio(driver, testSteps);
			foiloRoomCharges = res.get_RoomCharge(driver, testSteps);
			app_logs.info("Folio Tab Room Charge: " + foiloRoomCharges);
			folioBalance = res.get_Balance(driver, testSteps);
			app_logs.info("Balance: " + folioBalance);
			}
			
			if (WithPolicy.equals("No"))
			{
			stayInfoSecondayGuestName = res.getStayInfoSecondGuestName(driver);	
			String[] room = res.additionalRoomNo.split(":");
			roomClassName = room[0].trim();
			app_logs.info(" Room Class: " + roomClassName);
			stayInforAdditionalRoomCharges = res.get_STAYINFORoomChargesWithoutCurrency(driver, testSteps,
					roomClassName);
			app_logs.info("Stay Info Room Charge: " + stayInforAdditionalRoomCharges);
			tripSummaryPaidAmount = res.getTripSummaryPaidAmount(driver, testSteps);
			app_logs.info("Trip Summary Paid: " + tripSummaryPaidAmount);
			res.click_Folio(driver, testSteps);
			foiloRoomCharges = res.get_RoomCharge(driver, testSteps);
			app_logs.info("Folio Tab Room Charge: " + foiloRoomCharges);
			folioBalance = res.get_Balance(driver, testSteps);
			app_logs.info("Balance: " + folioBalance);
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Create New reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Create New reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		String TotalNoShowFee = "", amount = "";
		// Verifying Cancellation Reservation
		try {
			if (WithPolicy.equals("Yes"))
			{
			res.click_DeatilsTab(driver, testSteps);
			testSteps.add("<b>****Start Verifying POLICIES AND DISCLAIMERS****</b>");
			res.verifyCPReservationDetailTabPolicies(driver, testSteps, CancelPolicyType,
					RoomChargesPercentage, policyname, PolicyText);
			// Select Cancellation
			res.reservationStatusPanelSelectStatus(driver, CancelPolicyType, testSteps);
			testSteps.add("<b>****Start Verifying Cancel Reservation****</b>");
			Wait.wait5Second();
			res.verifyReservationPopWindow(driver, HeaderTitle, panelGuestName, status,
					reservation, testSteps, PolicyTypeCheckOut);
			res.verifyReservationPopWindowPolicyName(driver, testSteps, policyname,
					CancelPolicyType);
			res.verifyPolicyToolTip(driver, testSteps, policyname, PolicyText,
					SingleCancel);
			res.verifyReservationPopWindowPostLabel(driver, testSteps);
			res.Verify_VoidRoomCharge(driver, testSteps, CancelPolicyType);
			res.addCancelationReson(driver, testSteps, Reason,
					ValidationMsg);
			double TripRoomCharges = Double.valueOf(tripSummaryRoomCharges);
			int TripRoomChargesint = (int) TripRoomCharges;
			double TripPaidChagres = Double.valueOf(tripSummaryPaidAmount);
			int TripPaid = (int) TripPaidChagres;
			TotalNoShowFee = res.CalculationFee(driver, TripRoomChargesint,
					Integer.parseInt(RoomChargesPercentage),
					Integer.parseInt(TaxPercent), TripPaid);
			res.commonMethodForPaymentPopUpVerification(driver, testSteps, CancellationPayment,
					yearDate, NameOnCard, CardNo,
					PaymentType, Type, TotalNoShowFee,
					res.TripSummary_TaxServices, res.TripSummary_RoomCharges, CancelPolicyType,
					res.AmountPaid);
			res.getTodaysANDYesturdayDate();
			app_logs.info(res.oneDayBefore + res.PreviousDay);
			res.SetAsMainPaymentMethod(driver, testSteps, PaymentType,
					PaymentTypeTwo, res.oneDayBefore, res.PreviousDay,
					CancelPolicyType);
			paymentMethod = res.getPaymentMethod(driver, testSteps);
			testSteps.add("<b>****Payment Window Close Verification****</b>");

			res.clickCloseButtonOfPaymentWindow(driver, testSteps);
			res.closeWindowVerificationOfPaymentPopup(driver, testSteps, Message); 
			res.addNotesAndClickLogORPayORAuthorizedButton(driver, testSteps, Notes);
			
			String getAmount=res.getAmountFromPaymentVerificationPage(driver);
			double value= Double.valueOf(getAmount);
			getAmount=(String.format("%.2f",value));
			app_logs.info(getAmount);
			
			testSteps.add("<b>****Start Verifying Cancellation Successful Window****</b>");
			amount = res.Amount;
			app_logs.info(amount);
			app_logs.info(TotalNoShowFee);
			Wait.wait5Second();
			res.commonMethodForSuccessfullWindowVerification(driver, testSteps, SuccessMsg,
					Status, PaymentTypeTwo, Notes,
					BalanceMoney, Type, amount,
					PolicyTypeCheckOut, res.previousDate);

			res.clickCloseButtonOfSuccessModelPopup(driver, testSteps);
			testSteps.add("<b>****Start Verifying Roll Back Button ****</b>");
			res.verifyRollBackButton(driver, testSteps);
			testSteps.add("<b>****Start Verifying Cancelled Status****</b>");
			res.verifyReservationStatusStatus(driver, testSteps, CancelledStatus);

			String tripTotal = res.get_TripSummaryTripTotalChargesWithoutCurrency(driver, testSteps);
			String tripPaid = res.getTripSummaryPaidAmount(driver, testSteps);
			double TripTotal = Double.valueOf(tripTotal);
			double TripPaids = Double.valueOf(tripPaid);
			double TotalBalance = TripTotal - TripPaids;
			app_logs.info((int) (TotalBalance));
			testSteps.add("<b>****Start Verifying Balance****</b>");
			res.verifyReservationStatusBalance(driver, testSteps, String.valueOf((int) (TotalBalance)));
			testSteps.add("<b>****Start Verifying Trip Summary****</b>");

			// Verified TripSummary Balance
			String tripSummaryBalance = res.get_TripSummaryBalance_Amount(driver, testSteps);
			app_logs.info("Trip Summary Balance: " + tripSummaryBalance);
			res.verifyTripSummaryBalance(driver, testSteps, tripSummaryBalance, String.valueOf((int) (TotalBalance)));

			// Verified TripSummary Paid
			String tripSummaryPaid = res.getTripSummaryPaidAmount(driver, testSteps);
			app_logs.info("Trip Summary Paid: " + tripSummaryPaid);
				res.verifyTripSummaryPaid(driver, testSteps, tripSummaryPaid, amount);

			testSteps.add("<b>****Start Verifying Notes****</b>");
			String abb1 = roomAbbri.get(0).toString();
			app_logs.info(abb1);
			app_logs.info(roomAbbri.get(1).toString());
			for (int i = 0; i < rooms.size(); i++) {

				res.verifyCPReservationNotes(driver, testSteps, CancelPolicyType,
						PolicyType, roomAbbri.get(i).toString(), rooms.get(i).toString(),
						Subject,Reason, res.currentDay, res.Current_Date);

			}

			res.click_Folio(driver, testSteps);
			testSteps.add("<b>****Start Verifying line Item****</b>"); // Verify Line Item
			res.verifyLineItem(driver, testSteps, res.previousDate, res.PreviousDay,
					PaymentTypeTwo, PaymentTypeTwo, amount);
			testSteps.add("<b>****Start Verifying History Page****</b>");
			res.click_History(driver, testSteps);
			Wait.wait5Second();
			res.verifyHistoryTabDescription(driver, testSteps, CancelPolicyType);
			res.verifyHistoryDescForNoShowCancel(driver, testSteps, getAmount, CancelPolicyType, paymentMethod);
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Verifying Cancellation Reservation ", testName, "Reservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Verifying Cancellation Reservation ", testName, "Reservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		
		// Verify Cancel Room
		try {
			if (WithPolicy.equals("No"))
			{
			res.click_DeatilsTab(driver, testSteps);
			String tripSummaryPaid = res.getTripSummaryPaidAmount(driver, testSteps);
			app_logs.info("Trip Summary Paid: " + tripSummaryPaid);
			testSteps.add("<b>****Start Cancel Room****</b>");
			res.clickSTAYINFOThreeDots(driver, testSteps, SingleCancel,
					CancelRoomNo, rooms, roomAbbri);

			testSteps.add("<b>****Start Verifying Cancel Room Window****</b>");
			Wait.wait10Second();
			res.verifyReservationPopWindow(driver, CancelRoom, stayInfoSecondayGuestName, status,
					reservation, testSteps, PolicyTypeCheckOut);
			res.verifyReservationPopWindowPolicyName(driver, testSteps, policyname,
					CancelPolicyType);
			res.verifyPolicyToolTip(driver, testSteps, policyname, PolicyText,
					SingleCancel);
			res.verifyReservationPopWindowPostLabel(driver, testSteps);
			res.Verify_VoidRoomCharge(driver, testSteps, CancelPolicyType);
			res.addCancelationReson(driver, testSteps, Reason,
					ValidationMsg);

			double StayInfoRoomCharges = Double.valueOf(stayInforAdditionalRoomCharges);
			int StayInfoRoomChargesint = (int) StayInfoRoomCharges;
			double TripPaidChagres = Double.valueOf(tripSummaryPaidAmount);
			int TripPaid = (int) TripPaidChagres;
			String TotalFee = res.CalculationFee(driver, StayInfoRoomChargesint,
					Integer.parseInt(RoomChargesPercentage),
					Integer.parseInt(TaxPercent), TripPaid);
			testSteps.add("<b>****Start Verifying Cancellation Payment Window****</b>");
			// Verification No Show Payment
			res.commonMethodForPaymentPopUpVerification(driver, testSteps, CancellationPayment,
					yearDate, NameOnCard, CardNo,
					PaymentType, Type, TotalFee,
					res.TripSummary_TaxServices, res.TripSummary_RoomCharges,CancelPolicyType,
					res.AmountPaid);
			res.getTodaysANDYesturdayDate();
			app_logs.info(res.oneDayBefore + res.PreviousDay);
			// Change Payment Method and Set as Main Payment
			res.SetAsMainPaymentMethod(driver, testSteps, PaymentType,
					PaymentTypeTwo, res.oneDayBefore, res.PreviousDay,
					CancelPolicyType);
			paymentMethod = res.getPaymentMethod(driver, testSteps);
			// Added Notes and Click Log button
			res.addNotesAndClickLogORPayORAuthorizedButton(driver, testSteps, Notes);
			
			String getAmount=res.getAmountFromPaymentVerificationPage(driver);
			double value= Double.valueOf(getAmount);
		   getAmount=(String.format("%.2f",value));
			app_logs.info(getAmount);
			
			testSteps.add("<b>****Start Verifying Cancellation Successful Window****</b>");
			// Verification No Show Successful Window
			String amountOne = res.Amount;
			app_logs.info(amountOne);
			app_logs.info(TotalFee);
			res.commonMethodForSuccessfullWindowVerification(driver, testSteps, SuccessMsg,
					Status, PaymentTypeTwo,Notes,
					BalanceMoney, Type, amountOne,
					PolicyTypeCheckOut, res.previousDate);
			// Click Close button of No Show Successful Window
			res.clickCloseButtonOfSuccessModelPopup(driver, testSteps);
			testSteps.add("<b>****Start Verifying Roll Back Button ****</b>");

			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.getRoomsOnDetailPage(driver, newRooms);
				app_logs.info(newRooms);
			}

			// Verified Roll Back Button Enabled
			res.verifyStayInforRollBackButton(driver, testSteps, roomClassName);
			testSteps.add("<b>****Start Verifying Cancelled Status****</b>");
			res.verifyReservationStatusStatus(driver, testSteps, status);
			String[] roomPrimary = res.primary.split(":");
			String PrimaryRoomClassName = roomPrimary[0].trim();
			app_logs.info(" Primary Room Class: " + PrimaryRoomClassName);
			stayInfoPrimaryRoomCharges = res.get_STAYINFORoomChargesWithoutCurrency(driver, testSteps,
					PrimaryRoomClassName);
			app_logs.info(" Room Class: " + roomClassName);
			stayInforAdditionalRoomCharges = res.get_STAYINFORoomChargesWithoutCurrency(driver, testSteps,
					roomClassName);

			double PrimaryRoomAmount = Double.valueOf(stayInfoPrimaryRoomCharges);
			double SecondaryRoomAmount = Double.valueOf(stayInforAdditionalRoomCharges);
			double AdditionOfAmount = PrimaryRoomAmount + SecondaryRoomAmount;
			String StayInforRoomChangesAddition = String.valueOf(AdditionOfAmount);
			app_logs.info(StayInforRoomChangesAddition);
			testSteps.add("<b>****Start Verifying Trip Summary****</b>");

			// Verified Trip Summary Tax Service
			String tripSummaryTaxChages = res.get_TripSummaryTaxesWithoutCurrency(driver, testSteps);
			app_logs.info("Trip Summary Tax: " + tripSummaryTaxChages);

			// Verified Trip Summary Total
			double tripTotal = Double.parseDouble(StayInforRoomChangesAddition)
					+ Double.parseDouble(tripSummaryTaxChages);
			app_logs.info("Trip Summary Total calculatiomn: " + tripTotal);
			String tripSummaryTotal = res.get_TripSummaryTripTotalChargesWithoutCurrency(driver, testSteps);
			app_logs.info("Trip Summary Total: " + tripSummaryTotal);

			// Verified TripSummary Paid
			String tripSummaryPaidAgain = res.getTripSummaryPaidAmount(driver, testSteps);
			app_logs.info("Trip Summary Paid: " + tripSummaryPaidAgain);
			String paid = String.format("%.2f", res.amount);
			app_logs.info("Trip Summary Paid Calculation: " + paid);
			res.verifyTripSummaryPaidAmount(driver, testSteps, tripSummaryPaidAgain, paid);

			double tripBalance = tripTotal - Double.parseDouble(paid);
			app_logs.info("Trip Summary Balance Calculate: " + tripBalance);

			String tripSummaryBalance = res.get_TripSummaryBalance_Amount(driver, testSteps);
			app_logs.info("Trip Summary Balance: " + tripSummaryBalance);
			res.verifyTripSummaryBalanceAmount(driver, testSteps, tripSummaryBalance,
					String.format("%.2f", tripBalance));

			testSteps.add("<b>****Start Verifying Balance****</b>");
			res.verifyReservationPanelBalance(driver, testSteps, String.format("%.2f", tripBalance));

			testSteps.add("<b>****Start Verifying Notes****</b>");
			String abb1 = roomAbbri.get(0);
			app_logs.info(abb1);
			app_logs.info(roomAbbri.get(1).toString());
			res.verifyCPReservationNotes(driver, testSteps, CancelPolicyType,
					PolicyType, res.stayInfoActualAbb, res.stayInfoActualRoom,
					Subject, Reason, res.currentDay, res.Current_Date);

			testSteps.add("<b>****Start Verifying Folio Details Line Item****</b>");

			res.click_Folio(driver, testSteps);
			res.click_FolioDetail_DropDownBox(driver, testSteps);
			String[] abb = res.stayInfoActualAbb.split(":");
			String FinalAbb = abb[1].trim();
			app_logs.info(" Room No: " + FinalAbb);
			if (res.isRoomReserved || res.isRoomUnAssigned) {
				res.clickFolioDetailOptionValue(driver, testSteps, FinalAbb, newRooms.get(res.actualRoom));
			} else {
				res.clickFolioDetailOptionValue(driver, testSteps, FinalAbb, res.stayInfoActualRoom);
			}

			Wait.wait5Second();
			foiloRoomCharges = res.get_RoomCharge(driver, testSteps);
			app_logs.info("Folio Tab Room Charge: " + foiloRoomCharges);
			String tax = res.get_Taxes(driver, testSteps);
			app_logs.info("Folio Tab Tax Charge: " + tax);
			String cancelFee = String.format("%.2f", Double.valueOf(foiloRoomCharges) + Double.valueOf(tax));
			app_logs.info(cancelFee);
			// Verify Line Item
			res.verifyLineItem(driver, testSteps, res.previousDate, res.PreviousDay,
					PaymentTypeTwo, PaymentTypeTwo, amountOne);

			app_logs.info(res.Current_Date);
			app_logs.info(res.currentDay);
			Wait.wait5Second();

			res.verifyLineItem(driver, testSteps, res.Current_Date, res.currentDay,
					TaxLedgerAccount, TaxLedgerAccount, cancelFee);

			testSteps.add("<b>****Start Verifying History Page****</b>");
			// Click History Tab
			res.click_History(driver, testSteps);
			Wait.wait5Second();
			res.verifyHistoryTabDescription(driver, testSteps, CancelPolicyType);
			res.verifyHistoryDescForNoShowCancel(driver, testSteps, getAmount, CancelPolicyType, paymentMethod);
			
			String[] room = res.stayInfoActualRoom.split(":");
			String finalRoomNo = room[1].trim();
			System.out.println(" Room No: " + finalRoomNo);
			res.commonMethodToverifyRoomsOnHistoryTab(driver, testSteps, FinalAbb, finalRoomNo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Verify Cancel Room", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Verify Cancel Room", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		}
		
		//Delete Rate	
		try
		{
			testSteps.add("<b>****Start Deleting Rates****</b>");
			nav.Inventory(driver);
			testSteps.add("Navigate to Inventory");
			app_logs.info("Navigate to Inventory");	
			nav.Rates1(driver);
     		rate.deleteRates(driver, rateNameWithoutNum);
				testSteps.add("All Rate Deleted Successfully With Name: <b>" + rateNameWithoutNum+ " </b>");
				app_logs.info("All Rate Deleted Successfully With Name: " + rateNameWithoutNum);
		 
			
		}catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e,  "Failed to Delete Rates ", testName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e,  "Failed to Delete Rates", testName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		//Delete Room Class
		try
		{
			 testSteps.add("<b>****Delete Room Class****</b>");
				nav.Setup(driver);
				testSteps.add("Navigated to Setup");
				nav.RoomClass(driver);
				roomClass.SearchButtonClick(driver);
					 testSteps.add("Click on Search Button");
					app_logs.info("Click on Search Button");
					roomClass.SearchRoomClass(driver, roomClassNameWithoutNum, testSteps);
					roomClass.deleteRoomClass(driver, roomClassNameWithoutNum);							
					testSteps.add("All Room Class Deleted Successfully With Name: <b>" +roomClassNameWithoutNum + " </b>");
					app_logs.info("All Room Class Deleted Successfully With Name: " + roomClassNameWithoutNum);

				
			     RetryFailedTestCases.count = Utility.reset_count;
			     Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);	

		}
		catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e,  "Failed to Delete Room Class ", testName, "Room Class", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e,  "Failed to Delete Room Class ", testName, "Room Class", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}


	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getData("VerifyCancelCPReservationMRB", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
