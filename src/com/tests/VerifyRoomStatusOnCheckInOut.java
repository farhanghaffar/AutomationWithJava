package com.innroad.inncenter.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.NightAudit;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.RoomStatus;
import com.innroad.inncenter.pageobjects.TaskManagement;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class VerifyRoomStatusOnCheckInOut extends TestCore{

	private WebDriver driver = null;
	public static String test_name = "";
	public static String test_nameOne = "";
	public static String test_description = "";
	public static String test_catagory = "";
	public static String roomClassName;
	public static String roomClassAbbrivation;
	String reservation=null, houseKeepingNoBeforeCheckIN=null, houseKeepingNoAfterCheckIN=null,houseKeepingNoAfterCheckOut=null,checkInDate=null,checkOutDate=null,
			guestFirstName=null,guestLastName=null,status=null, yearDate=null,rateName=null, rateDisplayName=null;
	boolean click=true;
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	Navigation nav = new Navigation();
	RoomClass roomClass= new RoomClass();
	Rate rate = new Rate();
	TaskManagement task_mang = new TaskManagement();
	CPReservationPage res = new CPReservationPage();
	RoomStatus roomstatus = new RoomStatus();
	NightAudit naudit= new NightAudit();
	 ReservationSearch revSearch= new ReservationSearch();
	 String loading = "(//div[@class='ir-loader-in'])";
	 
	@BeforeTest(alwaysRun=true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}
	
	@Test(dataProvider = "getData", groups = "Reservation")
	public void verifyRoomStatusOnCheckInOut(Map<Object, Object> map)
	{
		String testName="";
		if(map.get("WithCheckINCheckOutToggle").toString().equals("Yes"))
		{
			test_name = "VerifyRoomStatusOnCheck-In/Check-OutIfToggleEnabled "; 
			test_description = "Verify RoomStatus On Enabled Toggle -Set Rooms To Dirty On Check-In/Check-Out<br>"
					+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682451' target='_blank'>"
					+ "Click here to open TestRail: C682451</a>"; 
			test_catagory = "CPReservation_CheckIN";
			testName = test_name;

			app_logs.info("##################################################################################");
			app_logs.info("EXECUTING: " + testName + " TEST.");
			app_logs.info("##################################################################################");
			 test_steps.add("**Verify Night Audit <b> HouseKeeping No </b> After Enable Toggle <b>Set Rooms To Dirty On Check-In/Check-Out</b>**");
			
		}else if(map.get("WithCheckINCheckOutToggle").toString().equals("No"))
		{
			test_name = "VerifyRoomStatusOnCheck-In/Check-OutIfToggleDisabled"; 
			test_description = "Verify RoomStatus On Disabled Toggle -Set Rooms To Dirty On Check-In/Check-Out<br>"
					+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682451' target='_blank'>"
					+ "Click here to open TestRail: C682451</a>"; 
			test_catagory = "CPReservation_CheckIN";
			testName = test_name;

			app_logs.info("##################################################################################");
			app_logs.info("EXECUTING: " + testName + " TEST.");
			app_logs.info("##################################################################################");
			 test_steps.add("**Verify Night Audit <b> HouseKeeping No </b>After Disable Toggle <b>Set Rooms To Dirty On Check-In/Check-Out</b>**");
				
		}
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
		
		// Get checkIN and Checkout Date
		try {
			if (!(Utility.validateInput(map.get("CheckInDate").toString()))) {
				for (int i = 0; i < map.get("GuestFirstName").toString().split("\\|").length; i++) {
					checkInDates.add(Utility.getCurrentDate("dd/MM/yyyy"));
					checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(1), "MM/dd/yyyy", "dd/MM/yyyy"));
				}
			} else {
				checkInDates = Utility.splitInputData(map.get("CheckInDate").toString());
				checkOutDates = Utility.splitInputData(map.get("CheckOutDate").toString());
			}
			checkInDate = checkInDates.get(0);
			checkOutDate = checkOutDates.get(0);
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
		
		// Create Room Class		
		 try
			{
			   test_steps.add("<b>****Start Creating New Room Class****</b>");
				nav.Setup(driver);
				test_steps.add("Navigated to Setup");
				nav.RoomClass(driver);
				test_steps.add("Navigated to Room Class");
				roomClass.deleteAllRoomClasses(driver, map.get("RoomClass").toString(), test_steps);
				nav.NewRoomClass(driver);
				test_steps.add("Navigated to New Room Class");
				app_logs.info("Navigated to New Room Class");	
				roomClassName = map.get("RoomClass").toString() + Utility.getTimeStamp();
				roomClassAbbrivation = map.get("RoomClassAbbrivation").toString() + Utility.getTimeStamp();					
				roomClass.create_RoomClass(driver, roomClassName, roomClassAbbrivation, map.get("BedsCount").toString(), map.get("MaxAdults").toString(), 
						map.get("MaxPersopns").toString(), map.get("RoomQuantity").toString(),test, test_steps);
			
				test_steps.add(" Room Class Created: <b>"+ roomClassName +"</b>");
				app_logs.info(" Room Class Created: " +roomClassName);	
			}catch(Exception e)
			{
				if (Utility.reTry.get(testName) == Utility.count) {
	                RetryFailedTestCases.count = Utility.reset_count;
	                Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
	                Utility.updateReport(e, "Failed to Create Room Class",  testName, "Room class", driver);
	            } else {
	                Assert.assertTrue(false);
	            }
			}catch(Error e)
			{
				if (Utility.reTry.get(testName) == Utility.count) {
	                RetryFailedTestCases.count = Utility.reset_count;
	                Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
	                Utility.updateReport(e, "Failed to Create Room Class",  testName, "Room class", driver);
	            } else {
	                Assert.assertTrue(false);
	            }
			}  
			
			//Create Rates and Associate Room Class
			try
			{
			   test_steps.add("<b>****Start Create New Rates and Associate Room Class with Rates****</b>");
				nav.Inventory(driver);
				test_steps.add("Navigate to Inventory");
				app_logs.info("Navigate to Inventory");	
				nav.Rates1(driver);
				test_steps.add("Navigate to Rates");	
				rate.deleteAllRates(driver, test_steps, map.get("RateName").toString());
				rateName=map.get("RateName").toString()+Utility.getTimeStamp();
				rateDisplayName=map.get("DisplayName").toString()+Utility.getTimeStamp();
				rate.new_Rate(driver, rateName, "Rack Rate", map.get("MaxAdults").toString(),
						map.get("MaxPersopns").toString(), map.get("Amout").toString(), map.get("MaxAdults").toString(),
						map.get("MaxPersopns").toString(), rateDisplayName, "All Year Season", map.get("RatePolicy").toString(), map.get("RateDescription").toString(), roomClassName, test_steps);
							
				test_steps.add("Successfull Created Rate: <b>" +rateName + "</b>");
				app_logs.info("Successfull Created Rate: " +rateName);	
			}catch(Exception e)
			{
				if (Utility.reTry.get(testName) == Utility.count) {
	                RetryFailedTestCases.count = Utility.reset_count;
	                Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
	                Utility.updateReport(e, "Failed to Create Rates and Associate Room Class", testName, "Room class", driver);
	            } else {
	                Assert.assertTrue(false);
	            }
			}
			catch(Error e)
			{
				if (Utility.reTry.get(testName) == Utility.count) {
	                RetryFailedTestCases.count = Utility.reset_count;
	                Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
	                Utility.updateReport(e, "Failed to Create Rates and Associate Room Class", testName, "Room class", driver);
	            } else {
	                Assert.assertTrue(false);
	            }
			}
			
			
	
	
		
		//Enable Toggle Set Rooms To Dirty On Check-In/Check-Out
		try
		{
			test_steps.add("<b>****Set Rooms To Dirty On Check-In/Check-Out****</b>");
			nav.Setup(driver);						
			test_steps.add("Navigated to Setup");
			nav.TaskManagement_TabExist(driver);
			test_steps.add("Task Management Tab Exist");
			nav.TaskManagement(driver);
			test_steps.add("Click on Task Management");
			app_logs.info("Click on Task Management");		
			task_mang.getToggleStatus_CheckInCheckOut(driver);
			if(map.get("WithCheckINCheckOutToggle").toString().equals("Yes"))
			{
				if(Utility.toggle==true)
				{
					test_steps.add("Set Rooms To Dirty On Check-In/Check-Out Toggle Already Enabled");
					app_logs.info("Set Rooms To Dirty On Check-In/Check-Out Toggle Already Enabled");
				}
				else if(Utility.toggle==false)
				{
					task_mang.SetRoomsToDirtyFlag(driver, true);
					test_steps.add("Successfully Enabled Toggle Set Rooms To Dirty On Check-In/Check-Out ");
					app_logs.info("Successfully Enabled Toggle Set Rooms To Dirty On Check-In/Check-Out");
				}
			}else if(map.get("WithCheckINCheckOutToggle").toString().equals("No"))
			{
				if(Utility.toggle==true)
				{
					task_mang.SetRoomsToDirtyFlag(driver, false);
					test_steps.add("Successfully Disabled Toggle Set Rooms To Dirty On Check-In/Check-Out ");
					app_logs.info("Successfully Disabled Toggle Set Rooms To Dirty On Check-In/Check-Out");
					
				}
				else if(Utility.toggle==false)
				{
					test_steps.add("Set Rooms To Dirty On Check-In/Check-Out Toggle Already Disabled");
					app_logs.info("Set Rooms To Dirty On Check-In/Check-Out Toggle Already Disabled");
					
				}
			}
			
	
		}catch(Exception e)
		{
			if (Utility.reTry.get(testName) == Utility.count) {
                RetryFailedTestCases.count = Utility.reset_count;
                Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
                Utility.updateReport(e, "Failed to Enabled Toggle Set Rooms To Dirty On Check-In/Check-Out", testName, "Task Management", driver);
            } else {
                Assert.assertTrue(false);
            }
		}
		catch(Error e)
		{
			if (Utility.reTry.get(testName) == Utility.count) {
                RetryFailedTestCases.count = Utility.reset_count;
                Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
                Utility.updateReport(e, "Failed to Enabled Toggle Set Rooms To Dirty On Check-In/Check-Out", testName, "Task Management", driver);
            } else {
                Assert.assertTrue(false);
            }
		}
	

		//Start Creating Reservation 
		
		try
		{
			
			
			    nav.Reservation_Backward_2(driver);
				app_logs.info("Navigate to Reservation");
				test_steps.add("<b>****Start Creating New Reservation****</b>");
				res.click_NewReservation(driver, test_steps);
				res.select_CheckInDate(driver, test_steps, checkInDate);
				res.select_CheckoutDate(driver, test_steps, checkOutDate);
				res.enter_Adults(driver, test_steps, map.get("MaxAdults").toString());
				res.clickOnFindRooms(driver, test_steps);
				res.select_SpecificRoom(driver, test_steps, roomClassName , map.get("IsAssign").toString() ,map.get("Account").toString() );
				String TripAmount=res.getTripSummaryTripTotalAmountBeforeReservation(driver, test_steps);
				res.InputdepositDue(driver, test_steps,  TripAmount.trim());
				res.clickNext(driver, test_steps);
			    yearDate=Utility.getFutureMonthAndYearForMasterCard(driver, test_steps);
			    String RN = Utility.GenerateRandomNumber();
				guestFirstName=map.get("GuestFirstName").toString()+RN;
			    guestLastName=map.get("GuestLastName").toString()+RN;
				res.enter_MailingAddress_OnUnchecked_CreateGuestProfile(driver, test_steps, map.get("Salutation").toString(), guestFirstName, guestLastName,map.get("IsGuesProfile").toString());
				if((map.get("Account").toString().equalsIgnoreCase("")||map.get("Account").toString().isEmpty())) {
					res.enter_PaymentDetails(driver, test_steps,map.get("PaymentType").toString() , map.get("CardNumber").toString(), map.get("NameOnCard").toString(), yearDate);
				}
				res.enter_MarketSegmentDetails(driver, test_steps, map.get("TravelAgent").toString() ,map.get("MarketSegment").toString()  , map.get("Referral").toString() );
				res.clickBookNow(driver, test_steps);                                           
				reservation=res.get_ReservationConfirmationNumber(driver, test_steps);		
				res.clickCloseReservationSavePopup(driver, test_steps); 
				Wait.wait5Second();
				revSearch.closeReservation(driver, guestFirstName);
				
		}catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e,  "Failed to Create New reservation and Check In", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e,  "Failed to  Create New reservation and Check In ", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		//Get Night Audit HouseKeeping Number
				try
				{
					 test_steps.add("<b>****Get Night Audit HouseKeeping Number****</b>");
					 nav.NightAudit(driver);
					 test_steps.add("Navigated to Night Audit");
					 naudit.EnterAuditDate(driver,Utility.RoomNo);
					 test_steps.add("Click Audit Date Picker");
					 app_logs.info("Click Audit Date Picker");
					 naudit.GoButtonClick(driver);
					 test_steps.add("Click Go Button");
					 app_logs.info("Click Go Button");
					 Wait.explicit_wait_xpath(OR.Period_status, driver);
					 naudit.Get_NightAuditDate(driver,test_steps);
					 houseKeepingNoBeforeCheckIN=null;
					 houseKeepingNoBeforeCheckIN=naudit.GetHouseKeepingCount(driver);
					 System.out.println("Night Audit House Keeping No Is: "+ houseKeepingNoBeforeCheckIN);
					 test_steps.add("Night Audit House Keeping No Is: <b>"+ houseKeepingNoBeforeCheckIN + " </b>");
					 app_logs.info("Night Audit House Keeping No Is: "+ houseKeepingNoBeforeCheckIN);	
				}
				catch(Exception e)
				{
					if (Utility.reTry.get(testName) == Utility.count) {
		                RetryFailedTestCases.count = Utility.reset_count;
		                Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
		                Utility.updateReport(e, "Failed to Get Night Audit HouseKeeping Number",  testName, "Night Audit", driver);
		            } else {
		                Assert.assertTrue(false);
		            }
				}catch(Error e)
				{
					if (Utility.reTry.get(testName) == Utility.count) {
		                RetryFailedTestCases.count = Utility.reset_count;
		                Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
		                Utility.updateReport(e, "Failed to Get Night Audit HouseKeeping Number",  testName, "Night Audit", driver);
		            } else {
		                Assert.assertTrue(false);
		            }
				}
				
		
		//Check IN
		try
		{
			nav.Reservation_Backward(driver);
			test_steps.add("<b>****Search Reservation****</b>");			
			// revSearch.basicSearchWithResNumber(driver, reservation, test_steps);
			 revSearch.basicSearch_WithResNumber(driver, reservation);
			test_steps.add("Search Reservation By Reservation Number <b>" + reservation + "<b>");
			app_logs.info("Search Reservation By Reservation Number " + reservation);
			test_steps.add("<b>****Start CheckIn Reservation****</b>");
			res.clickCheckInButton(driver, test_steps);
			res.generatGuestReportToggle(driver, test_steps,map.get("IsGuesRegistration").toString());	
			List<String> Rooms= new ArrayList<String>();
			Rooms.add(Utility.RoomNo);
			res.checkInProcess(driver, test_steps);
			Wait.wait10Second();
			res.verifyCheckOutButton(driver, test_steps);
			revSearch.closeReservation(driver, guestFirstName);
				
		}catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e,  "Failed to Create New reservation and Check In", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e,  "Failed to  Create New reservation and Check In ", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
	//Verified Room Status After Check-IN	
		try
		{
			
			
			test_steps.add("<b>****Start Verifying Room Status ON GS After Check-IN****</b>");
			nav.Guestservices(driver);
			test_steps.add("Navigated to Guestservices");
			app_logs.info("Navigated to Guestservices");
			roomstatus.verifyRoomStatusTabEnabled(driver,test_steps);
			roomstatus.verifiedGuestServicesDataLoadedOrNot(driver, test_steps);
			roomstatus.searchByRoomHashVerifyRoomStatus(driver, Utility.RoomNo,roomClassName,map.get("DirtyStatus").toString(),map.get("CleanStatus").toString(),map.get("VacantStatus").toString() ,map.get("OccupiedStatus").toString() ,test_steps);

		}
		catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e,  "Failed to Verified Room Status After Check-IN	 ", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e,  "Failed to  Verified Room Status After Check-IN	 ", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		//Verified Night Audit HouseKeeping Number 
		try
		{    
			 nav.Reservation_Backward_2(driver);
			 test_steps.add("<b>****Verify Night Audit HouseKeeping Number After Check-IN****</b>");
			 nav.NightAuditIcon(driver);
			 test_steps.add("Navigated to Night Audit");
			 naudit.EnterAuditDate(driver,Utility.RoomNo);
			 test_steps.add("Click Audit Date Picker");
			 app_logs.info("Click Audit Date Picker");
			 naudit.GoButtonClick(driver);
			 test_steps.add("Click Go Button");
			 app_logs.info("Click Go Button");
			 Wait.explicit_wait_xpath(OR.Period_status, driver);
			 naudit.Get_NightAuditDate(driver,test_steps);			 
			 houseKeepingNoAfterCheckIN=naudit.GetHouseKeepingCount(driver);
			 System.out.println("After CheckIn HouseKeeping No Is: " +houseKeepingNoAfterCheckIN);
			 test_steps.add("Night Audit House Keeping No Is Before Check In: <b>"+ houseKeepingNoBeforeCheckIN + " </b>");
			 app_logs.info("Night Audit House Keeping No Is Before Check In: "+ houseKeepingNoBeforeCheckIN);	
			 naudit.Verified_HouseKeeping_No_Increase(driver, test_steps, Integer.parseInt(houseKeepingNoBeforeCheckIN), roomstatus.OccupiedNo, houseKeepingNoAfterCheckIN);

			 
		}catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e,  "Failed to Verified Night Audit HouseKeeping Number  ", testName, "Night Audit", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e,  "Failed to  Verified Night Audit HouseKeeping Number  ", testName, "Night Audit", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
	// Change Room Status 
		try
		
		{
			test_steps.add("<b>****Start Update Room Status ON GS After Check-IN****</b>");
			nav.Guestservices(driver);
			test_steps.add("Navigated to Guestservices");
			app_logs.info("Navigated to Guestservices");
			roomstatus.verifyRoomStatusTabEnabled(driver,test_steps);
			roomstatus.verifiedGuestServicesDataLoadedOrNot(driver, test_steps);
			if(map.get("WithCheckINCheckOutToggle").toString().equals("Yes"))
			{
				app_logs.info("Yes");
			roomstatus.searchByRoomHashAndChangeRoomStatus(driver, Utility.RoomNo,roomClassName, map.get("CleanStatus").toString(), test_steps);
			}else if(map.get("WithCheckINCheckOutToggle").toString().equals("No"))
			{
				app_logs.info("No");
				roomstatus.searchByRoomHashAndChangeRoomStatus(driver, Utility.RoomNo,roomClassName, map.get("DirtyStatus").toString(), test_steps);
				
			}
		}
		catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e,  "Failed to Verified Night Audit HouseKeeping Number  ", testName, "Night Audit", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e,  "Failed to  Verified Night Audit HouseKeeping Number  ", testName, "Night Audit", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
	//CheckOut Reservation	
		try
		{
			
			 nav.Reservation_Backward_2(driver);
			 app_logs.info("Navigate to Reservation");
				//   revSearch.basicSearchWithResNumber(driver, reservation, test_steps);
			// revSearch.closeReservation(driver, guestFirstName);
				   revSearch.basicSearch_WithResNumber(driver, reservation);
         		test_steps.add("<b>****Start CheckOut Reservation****</b>");
       			res.clickCheckOutButton(driver, test_steps);
      			res.generatGuestReportToggle(driver, test_steps,map.get("IsGuesRegistration").toString());	
				res.clickCheckOutButtons(driver, test_steps,  map.get("CheckOut").toString(), map.get("CheckOutWithPayment").toString());
				test_steps.add("<b>****Start Verifying Roll Back Button****</b>");
				res.verifyRollBackButton(driver, test_steps);
	
		}
		catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e,  "Failed to CheckOut  Reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e,  "Failed to CheckOut  Reservation ", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		//Verified Room Status After Check-Out	
				try
				{
					test_steps.add("<b>****Start Verifying Room Status ON GS After Check-Out****</b>");
					nav.Guestservices(driver);
					test_steps.add("Navigated to Guestservices");
					roomstatus.verifyRoomStatusTabEnabled(driver,test_steps);
					roomstatus.verifiedGuestServicesDataLoadedOrNot(driver, test_steps);
					roomstatus.searchByRoomHashVerifyRoomStatus(driver, Utility.RoomNo,roomClassName,map.get("DirtyStatus").toString(),map.get("CleanStatus").toString(),map.get("VacantStatus").toString() ,map.get("OccupiedStatus").toString() ,test_steps);

				}
				catch (Exception e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
						Utility.updateReport(e,  "Failed to Verified Room Status After Check-Out	 ", testName, "GS", driver);
					} else {
						Assert.assertTrue(false);
					}
				} catch (Error e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.updateReport(e,  "Failed to  Verified Room Status After Check-Out	 ", testName, "GS", driver);
					} else {
						Assert.assertTrue(false);
					}
				}
				
				//Verified Night Audit HouseKeeping Number 
				try
				{    
					 test_steps.add("<b>****Verify Night Audit HouseKeeping Number After Check-Out****</b>");
					 nav.NightAuditIcon(driver);
					 test_steps.add("Navigated to Night Audit");
					 naudit.EnterAuditDate(driver,Utility.RoomNo);
					 test_steps.add("Click Audit Date Picker");
					 app_logs.info("Click Audit Date Picker");
					 naudit.GoButtonClick(driver);
					 test_steps.add("Click Go Button");
					 app_logs.info("Click Go Button");
					 Wait.explicit_wait_xpath(OR.Period_status, driver);
					 naudit.Get_NightAuditDate(driver,test_steps);
					 houseKeepingNoAfterCheckOut=null;
					 houseKeepingNoAfterCheckOut=naudit.GetHouseKeepingCount(driver);
					 System.out.println("After Check-Out HouseKeeping No Is: " +houseKeepingNoAfterCheckOut);
					 test_steps.add("Night Audit House Keeping No Is Before Check-Out: <b>"+ houseKeepingNoAfterCheckIN + " </b>");
					 app_logs.info("Night Audit House Keeping No Is Before Check-Out: "+ houseKeepingNoAfterCheckIN);	
					 naudit.Verified_HouseKeeping_No_Decrease(driver, test_steps, Integer.parseInt(houseKeepingNoAfterCheckIN), roomstatus.VacantNo, houseKeepingNoAfterCheckOut);
						 
				}catch (Exception e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
						Utility.updateReport(e,  "Failed to Verified Night Audit HouseKeeping Number  ", testName, "Night Audit", driver);
					} else {
						Assert.assertTrue(false);
					}
				} catch (Error e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.updateReport(e,  "Failed to  Verified Night Audit HouseKeeping Number  ", testName, "Night Audit", driver);
					} else {
						Assert.assertTrue(false);
					}
				}
			//Delete Rate	
				try
				{
					test_steps.add("<b>****Start Deleting Rates****</b>");
					nav.Inventory(driver);
					test_steps.add("Navigate to Inventory");
					app_logs.info("Navigate to Inventory");	
					nav.Rates1(driver);
		     		rate.deleteRates(driver, map.get("RateName").toString());
						test_steps.add("All Rate Deleted Successfully With Name: <b>" + map.get("RateName").toString() + " </b>");
						app_logs.info("All Rate Deleted Successfully With Name: " + map.get("RateName").toString());
				 
					
				}catch (Exception e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
						Utility.updateReport(e,  "Failed to Delete Rates ", testName, "Night Audit", driver);
					} else {
						Assert.assertTrue(false);
					}
				} catch (Error e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.updateReport(e,  "Failed to Delete Rates", testName, "Night Audit", driver);
					} else {
						Assert.assertTrue(false);
					}
				}
				//Delete Room Class
				try
				{
					 test_steps.add("<b>****Delete Room Class****</b>");
						nav.Setup(driver);
						test_steps.add("Navigated to Setup");
						nav.RoomClass(driver);
						roomClass.SearchButtonClick(driver);
							 test_steps.add("Click on Search Button");
							app_logs.info("Click on Search Button");
							roomClass.SearchRoomClass(driver, map.get("RoomClass").toString(), test_steps);
							roomClass.deleteRoomClass(driver, map.get("RoomClass").toString());							
							test_steps.add("All Room Class Deleted Successfully With Name: <b>" + map.get("RoomClass").toString() + " </b>");
							app_logs.info("All Room Class Deleted Successfully With Name: " + map.get("RoomClass").toString());

						
					     RetryFailedTestCases.count = Utility.reset_count;
					     Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);	

				}
				catch (Exception e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
						Utility.updateReport(e,  "Failed to Delete Room Class ", testName, "Night Audit", driver);
					} else {
						Assert.assertTrue(false);
					}
				} catch (Error e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.updateReport(e,  "Failed to Delete Room Class ", testName, "Night Audit", driver);
					} else {
						Assert.assertTrue(false);
					}
				}
				
				
	}

	
	@DataProvider
	public Object[][] getData() {
	
		return Utility.getDataMap("VerifyRoomStatusOnCheckInOut", excel);
	}

	@AfterClass(alwaysRun=true)
	public void closeDriver() {
		driver.quit();
	}
}
