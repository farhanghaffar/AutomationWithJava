package com.innroad.inncenter.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.innroad.inncenter.pageobjects.Properties;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.TaskManagement;
import com.innroad.inncenter.pageobjects.Tax;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;


public class VerifyCancelSecondGuestOnPrimaryGuestStatusInHouse extends TestCore {
	private WebDriver driver = null;
	public static String test_name = "";
	public static String test_description = "";
	public static String test_catagory = "";
	String testName=null;
	ArrayList<String> test_steps = new ArrayList<>();
	CPReservationPage res = new CPReservationPage();
	Navigation nav = new Navigation();
	String reservation=null, panelGuestName=null,status=null,StayInfoSecondayGuestName=null,RoomClassName=null, 
			Balance=null,paymentMethod=null,Tax=null, yearDate=null,propertyName = null;
	TaskManagement task_mang = new TaskManagement();
	ArrayList<String> roomCost = new ArrayList<String>();
	ArrayList<String> Rooms = new ArrayList<String>();
	ArrayList<String> RoomAbbri = new ArrayList<String>();
	ArrayList<String> NewRooms = new ArrayList<String>();
	List<String> roomCharges = new ArrayList<String>();
	List<String> roomNos = new ArrayList<String>();
	List<String> guestNames= new ArrayList<String>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	Policies policies= new Policies();
	RoomClass rc = new RoomClass();
	Properties properties = new Properties();
	Tax tax= new Tax();
	int halfAmountPaid=0;
	boolean isPolicyGridExit=false;
	String checkInDate=null,checkOutDate=null;
	@BeforeTest(alwaysRun=true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}
	
	@Test(dataProvider = "getData", groups = "Reservation")
	public void verifyStatusOnSecondGuestForInHouseStatus(Map<Object, Object> map)
	{
		test_name = "VerifyCancelSecondGuestOnPrimaryGuestStatusInHouse"; 
		test_description = "Verify Header Bar in Cancel PopUp While Canceling Secondary Guest When Status Of Primary Guest Is 'In House'<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682486' target='_blank'>"
				+ "Click here to open TestRail: C682486</a><br>";
				test_catagory = "CPReservation_CheckOut";
		 testName = test_name;

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");
		
		//Login
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
	
		 //Get checkIN and Checkout Date
		try
		{
							
			if ( !(Utility.validateInput(map.get("CheckInDate").toString())) ) {
				for (int i = 0; i < map.get("GuestFirstName").toString().split("\\|").length; i++) {
					checkInDates.add(Utility.getCurrentDate("dd/MM/yyyy"));
					checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(1), "MM/dd/yyyy", "dd/MM/yyyy"));
				}
			}else {
				checkInDates = Utility.splitInputData(map.get("CheckInDate").toString());
				checkOutDates = Utility.splitInputData(map.get("CheckOutDate").toString());
			}
			checkInDate=checkInDates.get(0)+"|"+checkInDates.get(1);
			checkOutDate=checkOutDates.get(0)+"|"+checkOutDates.get(1);
			app_logs.info(checkInDate);
			app_logs.info(checkOutDate);
			
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
					properties.uncheck_GuaranteedCheckBoxProperties(driver, test_steps, "No");
					properties.SaveButton(driver);
	      
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
							rc.getRoomClassAbbrivations(driver, test_steps, RoomAbbri,  map.get("RoomClass").toString());
							System.out.println(RoomAbbri);
						}catch (Exception e) {
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
		
		//Create New Reservation
		try
		{
			nav.Setup(driver);
   	        nav.Reservation_Backward_1(driver);
   	   	    test_steps.add("<b>****Start Creating New Reservation****</b>");
			res.click_NewReservation(driver, test_steps);
			res.select_Dates(driver, test_steps, checkInDate, checkOutDate, map.get("Adults").toString(), map.get("Children").toString(), map.get("Rateplan").toString(), map.get("PromoCode").toString(),map.get("IsSplitRes").toString());
			res.clickOnFindRooms(driver, test_steps);
			res.select_MRBRooms(driver, test_steps, map.get("RoomClass").toString(), map.get("IsAssign").toString(),map.get("Account").toString());
			String TripAmount=res.getTripSummaryTripTotalAmountBeforeReservation(driver, test_steps);
		    halfAmountPaid=(int)(Double.parseDouble(TripAmount)/2);
		    System.out.println("Override Deposit Due Amount : " +halfAmountPaid);
			res.InputdepositDue(driver, test_steps,  String.valueOf(halfAmountPaid));
            res.clickNext(driver, test_steps);
			 yearDate=Utility.getFutureMonthAndYearForMasterCard(driver, test_steps);
			 Random random = new Random();
			 int x = random.nextInt(900);
			res.enter_MRB_MailingAddressForMRB(driver, test_steps, map.get("Salutation").toString(), map.get("GuestFirstName").toString()+x,map.get("GuestLastName").toString()+x, 
	map.get("PhoneNumber").toString(), map.get("AltenativePhone").toString(),map.get("Email").toString() ,map.get("Account").toString() ,
					map.get("AccountType").toString(), map.get("Address1").toString(), map.get("Address2").toString(),  map.get("Address3").toString(),
					map.get("City").toString(), map.get("Country").toString(), map.get("State").toString(), map.get("PostalCode").toString(),
					map.get("IsGuesProfile").toString(), map.get("IsAddMoreGuestInfo").toString(), map.get("IsSplitRes").toString(),Rooms);
			if((map.get("Account").toString().equalsIgnoreCase("")||map.get("Account").toString().isEmpty())) {
				res.enter_PaymentDetails(driver, test_steps, map.get("PaymentType").toString(), map.get("CardNumber").toString(), 
						map.get("NameOnCard").toString(), yearDate);
			}
			System.out.println(Rooms);
			res.enter_MarketSegmentDetails(driver, test_steps, map.get("TravelAgent").toString(), map.get("MarketSegment").toString(), map.get("Referral").toString());
			res.clickBookNow(driver, test_steps);                                           
			reservation=res.get_ReservationConfirmationNumber(driver, test_steps);		
			res.clickCloseReservationSavePopup(driver, test_steps);     
			panelGuestName=res.getPanelStatusGuestName(driver);
			StayInfoSecondayGuestName=res.getStayInfoSecondGuestName(driver);
			guestNames=res.getStayInfoGuestName(driver, test_steps);
			roomCharges=res.getStayInfoRoomCharges(driver, test_steps);
			roomNos=res.getStayInfoRoomNo(driver, test_steps);
			}
		catch (Exception e) {
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
//Check IN Primary Room Room
		
		try
		{
			
			
			test_steps.add("<b>****Start CheckIn Room****</b>");
			res.stayInfoCheckIn(driver, test_steps, map.get("CheckInRoom").toString(),roomCharges,Rooms,RoomAbbri);
			test_steps.add("<b>****CheckIn Window ****</b>");
			res.generatGuestReportToggle(driver, test_steps,map.get("IsGuesRegistration").toString());		
			res.completeCheckInProcess(driver, test_steps);
			if(res.confirmCheckInWithPayment)
			{
				res.checkedOverRideCheckInAmountCheckBox(driver, test_steps, map.get("IsOverRideCheckIN").toString());
				res.selectTypeWhileCheckIN(driver, test_steps, map.get("Type").toString());
				//Added Notes and Click Log button
				res.addNotesAndClickLogORPayORAuthorizedButton(driver, test_steps, map.get("Notes").toString());
				res.clickCloseButtonOfSuccessModelPopup(driver, test_steps); 
				
			}
			
			 if(res.isRoomReserved||res.isRoomUnAssigned)
				{
		         res.getRoomsOnDetailPage(driver, NewRooms);
		         System.out.println(NewRooms);
				}
			 
            Wait.wait10Second();
			test_steps.add("<b>****Start Verifying In-House Status****</b>");
			res.verifyReservationStatusStatus(driver, test_steps, map.get("Status").toString() );
			test_steps.add("<b>****Start Verifying Check-Out All Button ****</b>");  				
			res.verifyCheckOutAllButton(driver, test_steps);  
			
			 res.click_Folio(driver, test_steps);
			    Tax=res.get_Taxes(driver, test_steps);
			    System.out.println("Folio -Tax is : " + Tax);
			    res.click_DeatilsTab(driver, test_steps);
			
		}
		catch (Exception e) {
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
		//Cancel Second Guest
		try
		{
			
			Balance= res.get_TripSummaryBalance_Amount(driver, test_steps);
			double tripAmount=Double.valueOf(Balance);
			int amt=(int)tripAmount;
			Balance=String.valueOf(amt);			
			res.getTodaysANDYesturdayDate();
			test_steps.add("<b>****Start Cancel Room****</b>");
	    	res.clickSTAYINFOThreeDots(driver, test_steps, map.get("CancelStatus").toString(),map.get("CancelRoomNo").toString(),Rooms ,RoomAbbri );				   		    
	    	test_steps.add("<b>****Start Verifying Cancel Room Window****</b>");
	    	res.verifyReservationPopWindow(driver, map.get("CancelRoom").toString(), StayInfoSecondayGuestName, map.get("ReservedStatus").toString(), reservation, test_steps, map.get("PolicyTypeCheckOut").toString());
	        res.unCheckedVoidRoomChargeCheckBox(driver, test_steps);
	       	res.inputReason(driver, test_steps, map.get("Reason").toString());
	       	res.clickProceedToCancelPaymentButton(driver, test_steps);
	        test_steps.add("<b>****Start Verifying Cancellation Payment Window****</b>");
			//Verification 
			res.commonMethodForPaymentPopUpVerification(driver, test_steps, map.get("CancellationPayment").toString(),yearDate,
					map.get("NameOnCard").toString(),map.get("CardNo").toString(),map.get("PaymentType").toString(),map.get("TypeCapture").toString() ,Balance,res.TripSummary_TaxServices,res.TripSummary_RoomCharges,map.get("CancelStatus").toString(),
					res.AmountPaid);   

			paymentMethod=res.getPaymentMethod(driver, test_steps);
			res.addNotesAndClickLogORPayORAuthorizedButton(driver, test_steps, map.get("CancelNote").toString());
			
			test_steps.add("<b>****Start Verifying Cancellation Successful Window****</b>");
			res.commonMethodForSuccessfullWindowVerification(driver, test_steps,map.get("SuccessMsg").toString() ,map.get("StatusTwo").toString() ,map.get("PaymentType").toString() ,map.get("CancelNote").toString(),
					map.get("BalanceMoney").toString(),map.get("TypeCapture").toString(),res.Amount,map.get("PolicyTypeCheckOut").toString(),res.Current_Date);
	
			res.clickCloseButtonOfSuccessModelPopup(driver, test_steps); 
			
			test_steps.add("<b>****Start Verifying In-House Status****</b>");
			res.verifyReservationStatusStatus(driver, test_steps, map.get("Status").toString() );
			test_steps.add("<b>****Start Verifying Check-Out All Button ****</b>");  				
			res.verifyCheckOutAllButton(driver, test_steps);  
			
			test_steps.add("<b>****Start Verifying Check-Out  Button ****</b>");  
			res.verifyStayInfoCheckOutButton(driver, test_steps, res.actualRoomCheckIn);		
			test_steps.add("<b>****Start Verifying Roll Back Button ****</b>");                            		
			String[] room=res.stayInfoActualRoom.split(":");			
			 RoomClassName=room[0].trim();
			 System.out.println(" Room Class: "+RoomClassName);		 
			//Verified Roll Back Button Enabled
			res.verifyStayInforRollBackButton(driver, test_steps,RoomClassName);
			
			test_steps.add("<b>****Start Verifying Notes****</b>");   
			res.verifyCPReservationNotes(driver, test_steps, map.get("CancelStatus").toString(),map.get("PolicyType").toString(), res.stayInfoActualAbb, res.stayInfoActualRoom, map.get("Subject").toString(), map.get("Reason").toString(), res.currentDay,res.Current_Date);
			
			test_steps.add("<b>****Start Verifying Folio Details Line Item****</b>");
			
			res.click_Folio(driver, test_steps);
			res.click_FolioDetail_DropDownBox(driver, test_steps);
			String[] abb=res.stayInfoActualAbb.split(":");
		    String FinalAbb=abb[1].trim();
		    System.out.println(" Room No: "+FinalAbb);
		    
		    if(res.isRoomReserved||res.isRoomUnAssigned)
			{
				res.clickFolioDetailOptionValue(driver, test_steps, FinalAbb, NewRooms.get(res.actualRoom));
			}
		    else
		    {
			res.clickFolioDetailOptionValue(driver, test_steps, FinalAbb, res.stayInfoActualRoom);
		    }
				//Verify Line Item
			res.verifyLineItem(driver, test_steps, res.Current_Date,res.currentDay,paymentMethod,map.get("NameOnCard").toString(), res.Amount);
			test_steps.add("<b>****Start Verifying History Page****</b>");    
			//Click History Tab
			res.click_History(driver, test_steps);
			res.verifyHistoryTabDescription(driver, test_steps,map.get("CancelStatus").toString());    
			res.verifyHistoryTabDescriptionWithPayment(driver, test_steps,res.Amount,map.get("CancelStatus").toString(),paymentMethod);
		
			 String Finalabb=null,FinaRoom=null;
			if(res.isRoomReserved||res.isRoomUnAssigned)
			{
				 String[] abbs=res.stayInfoActualAbb.split(":");
				  Finalabb=abbs[1].trim();
				 System.out.println(" Room No: "+Finalabb);
				 
				 String[] rooms=NewRooms.get(res.actualRoom).split(":");
				  FinaRoom=rooms[1].trim();
				 System.out.println(" Room No: "+FinaRoom);
				
			}
			else
			{
				 String[] abbs=res.stayInfoActualAbb.split(":");
				  Finalabb=abbs[1].trim();
				 System.out.println(" Room No: "+Finalabb);
				 
				 String[] rooms=res.stayInfoActualRoom.split(":");
				  FinaRoom=rooms[1].trim();
				 System.out.println(" Room No: "+FinaRoom);
				
			}
			
			 
			res.commonMethodToverifyRoomsOnHistoryTab(driver, test_steps,Finalabb,FinaRoom);  
		
		
			
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);



		}
		catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Header bar in Cancel pop up while canceling Secondary guest", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify Header bar in Cancel pop up while canceling Secondary guest", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} 
	}
	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getDataMap("VerifyCancelSGuestStatusInHouse", excel);
	}

	@AfterClass(alwaysRun=true)
	public void closeDriver() {
		driver.quit();
	}

}
