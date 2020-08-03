package com.innroad.inncenter.tests;

import java.io.IOException;
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
import com.innroad.inncenter.webelements.WebElements_Policies;
import com.relevantcodes.extentreports.ExtentTest;

public class VerifyNoShowCPReservationMRB extends TestCore{
	private WebDriver driver = null;
	public static String test_name = "";
	public static String test_description = "";
	public static String test_catagory = "";
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	CPReservationPage res = new CPReservationPage();
	Navigation nav = new Navigation();
	RoomClass rc = new RoomClass();
	Policies policies= new Policies();
	RoomClass roomClass = new RoomClass();
	Rate rate = new Rate();
	Tax tax= new Tax();
	String paymentMethod=null;
	
	String reservation=null;
	String panelGuestName=null;
	String status=null, yearDate=null;
	ArrayList<String> rooms = new ArrayList<String>();
	ArrayList<String> roomAbbri = new ArrayList<String>();
		String policyname="";
	String balance="";	
	String folioTab_RoomCharge="";
	String tripSummary_RomCharges="", tripSummary_Paid="";
	
	
	String roomClassNameWithoutNum = "RoomClass";
	String roomClassAvvWithoutNum = "RoomAbb";
	String rateNameWithoutNum = "AutoRate_";
	String randomNum1 = null;
	String randomNum2 = null;
	String roomClassName1 = null;
	String roomClassAbb1 = null;
	String maxAdults = "2", maxPersons = "2", roomQuantity = "2";
	String roomClassName2 = null;
	String roomClassAbb2 = null;
	ArrayList<String> roomNumberAssigned = new ArrayList<>();
	String rateName = null;
	
	@BeforeTest(alwaysRun=true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void verify_NoShow_WithoutPolicy(String url, String ClientCode, String Username, String Password,String CheckInDate,String CheckOutDate,String Adults,String Children,String Rateplan,String PromoCode,String IsSplitRes,
			String RoomClass,String IsAssign,String IsDepositOverride,String DepositOverrideAmount,String IsAddMoreGuestInfo,String Salutation,String GuestFirstName,String GuestLastName,String PhoneNumber,String AltenativePhone,String Email,String Account,String AccountType,
			String Address1,String Address2,String Address3,String City,String Country,String State,String PostalCode,String IsGuesProfile,String PaymentType,String CardNumber,String NameOnCard,String CardExpDate,String IsChangeInPayAmount,String ChangedAmountValue,
			String TravelAgent,String MarketSegment,String Referral,String IsAddNotes,String NoteType,String Subject,String Description,
			String IsTask, String TaskCategory, String TaskType,String TaskDetails,String TaskRemarks,String TaskDueon,String TaskAssignee,String TaskStatus,
			String PolicyType, String NoShowTitle, String NoShowPayment, String Msg,String CardNo,
			String ConfirmNoShowMsg,String Category,String Qty, String RollBackMsg, String NoShowSuccess,String Status, String Notes,
			String PolicyName,String PolicyText, String PolicyDesc, String RoomChargesPercentage,String PaymentTypeTwo,
			String Type,String BalanceMoney,String NoShowFee, String TaxName, String DisplayName,String TaxDescription,String TaxPercent,String TaxCategory,
			String TaxLedgerAccount, String Percentage,String NotesType, String NoPolicy,String IsDepositOverride1, String DepositOverrideAmount1, String PolicyTypeCheckOut,
			String WithPolicy) throws InterruptedException, IOException {
		
		rooms.removeAll(rooms); 
		roomNumberAssigned.removeAll(roomNumberAssigned);
		roomAbbri.removeAll(roomAbbri);
		
		if (WithPolicy.equals("No")) {
			test_name = "VerifyNoShowCPReservationMRBWithoutPolicy "; 
		}
		
		else if(WithPolicy.equals("Yes"))
		{
			test_name = "VerifyNoShowCPReservationMRBWithPolicy "; 
		}

		
		test_description = "Verify No Show For CP MRB Reservation <br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/681843' target='_blank'>"
				+ "Click here to open TestRail: C681843</a>"; 
		test_catagory = "CPReservation_NoShow";
		String testName = test_name;

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		

		
		
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
     
		try {

			 String policyNameWithoutNum = PolicyName;
			 randomNum1 = Utility.GenerateRandomNumber();
			 randomNum2 = Utility.GenerateRandomNumber();
			 roomClassName1 = roomClassNameWithoutNum + "One_" + randomNum1;
			 roomClassAbb1 = roomClassAvvWithoutNum+"One_" + randomNum1;
			 roomClassName2 = roomClassNameWithoutNum + "Two_" + randomNum2;
			 roomClassAbb2 = roomClassAvvWithoutNum+"Two_" + randomNum2;
			 rateName = rateNameWithoutNum + randomNum1;
			
			
			test_steps.add("========== Creating 1st room class ==========");
			nav.Setup(driver, test_steps);
			nav.RoomClass(driver, test_steps);
			roomClass.SearchButtonClick(driver);
			test_steps.add("Click on Search Button");
			app_logs.info("Click on Search Button");
			roomClass.SearchRoomClass(driver, roomClassNameWithoutNum, test_steps);
			roomClass.deleteRoomClass(driver, roomClassNameWithoutNum);
			nav.clickOnNewRoomClassButton(driver, test_steps);
			/*roomClass.CreateRoomClass(driver, roomClassName1, roomClassAbb1, null, maxAdults, maxPersons, roomQuantity,
					test, test_steps);*/
			roomClass.create_RoomClass(driver, roomClassName1, roomClassAbb1, null, maxAdults, maxPersons, roomQuantity,
					test, test_steps);
			roomNumberAssigned.add(Utility.RoomNo);
			roomClass.CloseOpenedRoomClass(driver, roomClassName1, test_steps);
			nav.NewRoomClass(driver);
			test_steps.add("========== Creating 2nd room class ==========");
			/*roomClass.CreateRoomClass(driver, roomClassName2, roomClassAbb2, null, maxAdults, maxPersons, roomQuantity,
					test, test_steps);*/
			roomClass.create_RoomClass(driver, roomClassName2, roomClassAbb2, null, maxAdults, maxPersons, roomQuantity,
					test, test_steps);
			roomNumberAssigned.add(Utility.RoomNo);
			for (int i = 0; i < roomNumberAssigned.size(); i++) {
				roomNumberAssigned.get(i);
			}

			app_logs.info(roomNumberAssigned);
			roomAbbri.add(roomClassName1 + ":" + roomClassAbb1);
			roomAbbri.add(roomClassName2 + ":" + roomClassAbb2);
			app_logs.info(roomAbbri);
			test_steps.add("========== Creating rate plan and Associating with room class ==========");
			nav.Inventory(driver);
			nav.Rates1(driver);
			rate.deleteRates(driver, rateNameWithoutNum);

			rate.create_Rate(driver, rateName, maxAdults, maxPersons, "100", maxAdults, maxPersons, "RateDisplay",
					"AutomationRatePolicy", "AutomationRateDescription", roomClassName1, roomClassName2, test_steps);
			 
			if (WithPolicy.equals("Yes")) {

				test_steps.add("========== Creating policy and Associating with  room class ==========");
				nav.Inventory(driver, test_steps);
				nav.policies(driver, test_steps);
				policies.deleteAllPolicies(driver, test_steps, PolicyType, policyNameWithoutNum);
				policies.Select_PolicyType(driver, "All", test_steps);
				policies.NewPolicybutton(driver, PolicyType, test_steps);
				policyname = PolicyName + Utility.getTimeStamp();
				policies.Enter_Policy_Name(driver, policyname, test_steps);
				policies.Enter_Policy_Desc(driver, PolicyText, PolicyDesc);
				test_steps.add("Enter Policy Text and Description: <b>" + PolicyText + " AND "
						+ PolicyDesc+ "</b>");
				policies.Enter_NoShow_policy_Attributes_RC_Percentage(driver, RoomChargesPercentage);				
				policies.Associate_Sources(driver);
				test_steps.add(" Associate Sources");
				policies.Associate_Seasons(driver);
				test_steps.add(" Associate Seasons");
				policies.Associate_RoomClasses(driver, roomClassName1);
				test_steps.add(" Associate Room Classes: " + roomClassName1);
				policies.Associate_RoomClasses(driver, roomClassName2);
				test_steps.add(" Associate Room Classes: " + roomClassName2);
				String rateplan[] = Rateplan.split("\\|");
				policies.Associate_RatePlan(driver, rateplan[0]);
				test_steps.add(" Associate Rate Plans: " + Rateplan);
				policies.Save_Policy(driver);
				test_steps.add("Click Save Button");
				test_steps.add("Policy Saved Successfully: <b>" + policyname + "</b>");
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Create room class and Rates", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Create room class and Rates", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		 //Get checkIN and Checkout Date
		try
		{
			if ( !(Utility.validateInput(CheckInDate)) ) {
				for (int i = 0; i < GuestFirstName.split("\\|").length; i++) {
					checkInDates.add(Utility.getCurrentDate("dd/MM/yyyy"));
					checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(1), "MM/dd/yyyy", "dd/MM/yyyy"));
				}
			}else {
				checkInDates = Utility.splitInputData(CheckInDate);
				checkOutDates = Utility.splitInputData(CheckOutDate);
			}
			CheckInDate=checkInDates.get(0)+"|"+checkInDates.get(1);
			CheckOutDate=checkOutDates.get(0)+"|"+checkOutDates.get(1);
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
	

		// Reservation and No Show Policy with "No Policy Exist"
		try {
			nav.Reservation_Backward_1(driver);
			test_steps.add("<b>****Start Creating New Reservation****</b>");
			res.click_NewReservation(driver, test_steps);
			res.select_Dates(driver, test_steps, CheckInDate, CheckOutDate, Adults, Children, Rateplan, PromoCode,IsSplitRes);
			if(IsSplitRes.equalsIgnoreCase("Yes")) {
				res.enter_Adults(driver, test_steps, Adults);
				res.enter_Children(driver, test_steps, Children);
				res.select_Rateplan(driver, test_steps, Rateplan, PromoCode);
			}
			res.clickOnFindRooms(driver, test_steps);
			res.selectRoom1(driver, test_steps, roomClassName1, roomNumberAssigned.get(0), "");
			res.selectRoom1(driver, test_steps, roomClassName2, roomNumberAssigned.get(1), "");
			
			res.deposit(driver, test_steps, IsDepositOverride, DepositOverrideAmount);

			res.clickNext(driver, test_steps);
			 yearDate=Utility.getFutureMonthAndYearForMasterCard(driver, test_steps);
			 Random random = new Random();
			 int x = random.nextInt(900);
			res.enter_MRB_MailingAddressForMRB(driver, test_steps, Salutation, GuestFirstName+x, GuestLastName+x, PhoneNumber, AltenativePhone, Email, Account, AccountType, Address1, Address2, Address3, City, Country, State, PostalCode, IsGuesProfile, IsAddMoreGuestInfo, IsSplitRes,rooms);

			
			if((Account.equalsIgnoreCase("")||Account.isEmpty())) {
				res.enter_PaymentDetails(driver, test_steps, PaymentType, CardNumber, NameOnCard, yearDate);
			}
			app_logs.info(rooms);
			res.enter_MarketSegmentDetails(driver, test_steps, TravelAgent, MarketSegment, Referral);
			res.clickBookNow(driver, test_steps);
			reservation=res.get_ReservationConfirmationNumber(driver, test_steps);		
			res.clickCloseReservationSavePopup(driver, test_steps);               
			panelGuestName=res.getPanelStatusGuestName(driver);
			status=res.getPanelStatusStatusName(driver);
			
			
			//New Added
			
			tripSummary_RomCharges=res.get_TripSummaryRoomChargesWithoutCurrency(driver, test_steps);
			app_logs.info("Trip Summary Room Charge: "+tripSummary_RomCharges);
			tripSummary_Paid=res.getTripSummaryPaidAmount(driver, test_steps);
			app_logs.info("Trip Summary Paid: "+tripSummary_Paid);
			res.click_Folio(driver, test_steps);
			folioTab_RoomCharge=res.get_RoomCharge(driver, test_steps);
			app_logs.info("Folio Tab Room Charge: "+folioTab_RoomCharge);
			balance=res.get_Balance(driver, test_steps);
			app_logs.info("Balance: "+balance);
		}
		catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Create New reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Create New reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}                 
		//Verify Confirm No Show
		try
		{
			if (WithPolicy.equals("No")) {
			res.click_DeatilsTab(driver, test_steps);
			test_steps.add("<b>****Start Verifying No Show Policy In Detail Page****</b>");
			res.verifyPOLICIESANDDISCLAIMERSNoShowPolicy(driver, test_steps,NoPolicy,PolicyType,RoomChargesPercentage);     
			//Select No Show
			res.reservationStatusPanelSelectStatus(driver, PolicyType,test_steps);   
			test_steps.add("<b>****Start Verifying New Reservation Popup Window****</b>");
			//Verify No Show Reservation Window
			res.verifyReservationPopWindow(driver, NoShowTitle, panelGuestName, status, reservation, test_steps, PolicyTypeCheckOut); 
				
			//Verify Void Room Charge 
			res.voidRoomChargeVerificationOfNoShow(driver, test_steps);  
    		test_steps.add("<b>****Confirm No Show****</b>");
			//Click Confirm No Show
			res.clickConfirmNoShowButton(driver, test_steps);
			boolean isExist=res.verifyConfirmYesButton(driver);
			app_logs.info("Confirm Message Displayed or Not : " + isExist);
			if(isExist)
			{
			res.clickYesButtonOfConfirmTheNoShowProcessPopupModel(driver, test_steps);
			}

			boolean isSuccessExist=res.verifyNoShowSuccessFull(driver);
			 app_logs.info("Confirm Success Window Displayed or Not : " + isSuccessExist);
			if(isSuccessExist)
			{
				res.noShowSuccessfullyWindowVerification(driver, test_steps, NoShowSuccess, reservation);
			}
			
				//Click Folio Tab
			res.click_Folio(driver, test_steps);
			test_steps.add("<b>****Start Verifying Line Item****</b>");
			//Verify Display Void Line Item
			res.verifyDisplayVoidLineItems(driver, test_steps,  PolicyType,Category);
			test_steps.add("<b>****Start Verifying History Page****</b>");
			//Click History Tab
			res.click_History(driver, test_steps);
			//Verify No Show on History Tab
			res.verifyHistoryTabDescription(driver, test_steps,PolicyType);
			
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Confirm No Show", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Confirm No Show", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}         
 
		
	                     
		//Verified No Show with Existing Policy
		try
		{
			if(WithPolicy.equals("Yes"))
			{
			test_steps.add("<b>****Start Verifying Policy in Detail Page****</b>");
			res.click_DeatilsTab(driver, test_steps);
			res.verifyCPReservationDetailTabPolicies(driver, test_steps,PolicyType,Percentage,policyname,PolicyText); 				
			//Select No Show
			res.reservationStatusPanelSelectStatus(driver, PolicyType,test_steps);   
			test_steps.add("<b>****Start Verifying No Show Reservation Window****</b>");
			res.verifyReservationPopWindowPolicyName(driver, test_steps, policyname,PolicyType);
			res.verifyPolicyToolTip(driver, test_steps, policyname, PolicyText,PolicyType);
			res.verifyReservationPopWindowPostLabel(driver, test_steps);
			//Click Proceed To No Show Payment Button
			res.clickProceedToNoShowPaymentButton(driver, test_steps);
			test_steps.add("<b>****Start Verifying No Show Payment Window****</b>");  
			double TripRoomCharges=Double.valueOf(tripSummary_RomCharges);
			int TripRoomChargesint=(int)TripRoomCharges;
			double TripPaidChagres=Double.valueOf(tripSummary_Paid);
			int TripPaid=(int)TripPaidChagres;
			String TotalNoShowFee=res.CalculationFee(driver,TripRoomChargesint,Integer.parseInt(RoomChargesPercentage), Integer.parseInt(TaxPercent),TripPaid);
			
			//Verification No Show Payment
			res.commonMethodForPaymentPopUpVerification(driver, test_steps, NoShowPayment,yearDate,NameOnCard,CardNo,PaymentType, Type,TotalNoShowFee,res.TripSummary_TaxServices,res.TripSummary_RoomCharges,PolicyType,res.AmountPaid);   			
			res.getTodaysANDYesturdayDate();
			//Change Payment Method and Set as Main Payment 
			res.SetAsMainPaymentMethod(driver, test_steps, PaymentType, PaymentTypeTwo,res.oneDayBefore,res.PreviousDay,PolicyType);
			test_steps.add("<b>****Payment Window Close Verification****</b>");
			//Click Close Icon of No Show Payment window
			res.clickCloseButtonOfPaymentWindow(driver, test_steps);
			//Verification Close Window Verification
			res.closeWindowVerificationOfPaymentPopup(driver, test_steps, Msg);		
			paymentMethod=res.getPaymentMethod(driver, test_steps);
			//Added Notes and Click Log button
			res.addNotesAndClickLogORPayORAuthorizedButton(driver, test_steps, Notes);
			test_steps.add("<b>****Start Verifying No Show Successful Window****</b>");
			//Verification No Show Successful Window
			
			String getAmount=res.getAmountFromPaymentVerificationPage(driver);
			app_logs.info(getAmount);
			
			String amount=res.Amount;
			app_logs.info(amount);
			app_logs.info(TotalNoShowFee);
			res.commonMethodForSuccessfullWindowVerification(driver, test_steps,NoShowSuccess, Status, PaymentTypeTwo,Notes,BalanceMoney,Type,amount,PolicyTypeCheckOut,res.previousDate);
			//Click Close button of No Show Successful Window
			res.clickCloseButtonOfSuccessModelPopup(driver, test_steps); 
			test_steps.add("<b>****Start Verifying Roll Back Button ****</b>");                            
			//Verified Roll Back Button Enabled
			res.verifyRollBackButton(driver, test_steps);
			test_steps.add("<b>****Start Verifying No Show Status****</b>");
				res.verifyReservationStatusStatus(driver, test_steps, PolicyType);				
				String tripTotal=res.get_TripSummaryTripTotalChargesWithoutCurrency(driver, test_steps);
				double TripTotal=Double.valueOf(tripTotal);
					double TotalBalance=TripTotal-Double.valueOf(getAmount);
				
				
				app_logs.info(String.format("%.2f",TotalBalance));
				test_steps.add("<b>****Start Verifying Balance****</b>");
				
				res.verifyReservationPanelBalance(driver, test_steps, String.format("%.2f",TotalBalance));
				
				test_steps.add("<b>****Start Verifying Trip Summary****</b>");
             //Verified TripSummary Balance
				String tripSummaryBalance= res.get_TripSummaryBalance_Amount(driver, test_steps);
				app_logs.info("Trip Summary Balance: "+tripSummaryBalance);
				
				res.verifyTripSummaryBalanceAmount(driver, test_steps, tripSummaryBalance, String.format("%.2f",TotalBalance));
				
				//Verified TripSummary Paid
				String tripSummaryPaid=res.getTripSummaryPaidAmount(driver, test_steps);
				app_logs.info("Trip Summary Paid: "+tripSummaryPaid);
				
				res.verifyTripSummaryPaidAmount(driver, test_steps, tripSummaryPaid, String.format("%.2f",Double.valueOf(getAmount)));
				

				test_steps.add("<b>****Start Verifying Notes****</b>");
				for(int i=0;i<rooms.size();i++)
				{
						res.verifyCPReservationNotes(driver, test_steps,PolicyType,NotesType, roomAbbri.get(i).toString(), rooms.get(i).toString(), PolicyType,Subject, res.currentDay,res.Current_Date);
					
			
				}
				//Click Folio Tab
			res.click_Folio(driver, test_steps);
			test_steps.add("<b>****Start Verifying line Item****</b>");
			res.verifyLineItem(driver, test_steps, res.previousDate,res.PreviousDay,PaymentTypeTwo, PaymentTypeTwo,getAmount);
			
			test_steps.add("<b>****Start Verifying History Page****</b>");
			//Click History Tab
			res.click_History(driver, test_steps);
			Wait.wait5Second();
			res.verifyHistoryTabDescription(driver, test_steps,PolicyType);     		
			res.verifyHistoryDescForNoShowCancel(driver, test_steps, getAmount, PolicyType, paymentMethod);
			
			
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);

			}
			
		}catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify No Show ", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify No Show ", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
	}
	
	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getData("VerifyNoShowCPReservationMRB", excel);
	}

	@AfterClass(alwaysRun=true)
	public void closeDriver() {
		driver.quit();
	}

}
