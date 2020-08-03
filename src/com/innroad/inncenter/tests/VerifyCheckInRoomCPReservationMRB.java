package com.innroad.inncenter.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Policies;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.RoomStatus;
import com.innroad.inncenter.pageobjects.Tapechart;
import com.innroad.inncenter.pageobjects.TaskManagement;
import com.innroad.inncenter.pageobjects.Tax;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.WebElements_Policies;

public class VerifyCheckInRoomCPReservationMRB extends TestCore{

	//NOTE:- IF WANT TO EXECUTE CHECK-IN WITH 'CHECK-IN POLICY' THEN CHANGE FLAG VALUE "WithPolicy=Yes" IN EXCEL SHEET.
		private WebDriver driver = null;
		public static String test_name = null;
		public static String test_nameOne = null;
		public static String test_description = null;
		public static String test_catagory = null;
		ArrayList<String> test_steps = new ArrayList<>();
		ArrayList<String> RoomAbbri = new ArrayList<String>();
		ArrayList<String> roomCost = new ArrayList<String>();
		ArrayList<String> Rooms = new ArrayList<String>();
		ArrayList<String> NewRooms = new ArrayList<String>();
		ArrayList<String> RoomButtonStatus = new ArrayList<String>();
		ArrayList<String> checkInDates = new ArrayList<>();
		ArrayList<String> checkOutDates = new ArrayList<>();
		List<String> roomCharges = new ArrayList<String>();
		List<String> roomNos = new ArrayList<String>();
		public static Logger reslogger = Logger.getLogger("VerifyCheckInRoomCPReservationMRB");
		RoomClass rc = new RoomClass();
		Navigation nav = new Navigation();
		RoomStatus roomstatus = new RoomStatus();
		CPReservationPage res = new CPReservationPage();
		Policies policies= new Policies();
		Tapechart tapeChart= new Tapechart();
		TaskManagement task_mang = new TaskManagement();
		Tax tax= new Tax();
		Double depositAmount=0.0;
		String reservation=null, panelGuestName=null,status=null,StayInfoSecondayGuestName=null, TripSummaryRoomCharges=null;
	    String GuestFirstName = null,GuestLastName = null,guestNameCheckInRoom=null, yearDate=null,checkInDate=null,checkOutDate=null;
		String Policyname=null,guestFirstName=null,guestLastName=null;
		String amount = null, paymentMethod=null;
		@BeforeTest(alwaysRun=true)
		public void checkRunMode() {
			String testName = this.getClass().getSimpleName().trim();
			app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
			if (!Utility.isExecutable(testName, excel))
				throw new SkipException("Skipping the test - " + testName);
		}
		String testNameTwo=null;
		String testName=null;
		
		@Test(dataProvider = "getData", groups = "Reservation")
		public void CheckIN_Room(Map<Object, Object> map) throws InterruptedException
		{
			
			
			if(map.get("WithPolicy").toString().equals("No"))
			{
				test_name = "VerifyCheckInRoomWithoutPolicy"; 
				test_description = "Verify CP MRB Check-In Room Without Check-In Policy <br>"
						+ "<a href='https://innroad.testrail.io/index.php?/cases/view/681833' target='_blank'>"
						+ "Click here to open TestRail: C681833</a><br>"
						+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682469' target='_blank'>"
						+ "Click here to open TestRail: C682469</a><br>"
						+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682505' target='_blank'>"
						+ "Click here to open TestRail: C682505</a> "
						+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/681834' target='_blank'>"
						+ "Click here to open TestRail: C681834</a> <br>"; 
				test_catagory = "CPReservation_CheckIN";
				 testName = test_name;

				app_logs.info("##################################################################################");
				app_logs.info("EXECUTING: " + testName + " TEST.");
				app_logs.info("##################################################################################");
				test_steps.add("<b>==========================Verifyig Check-In Room Functionality WithOut Policy==========================</b>");
			}else if(map.get("WithPolicy").toString().equals("Yes"))
			{
				test_name = "VerifyCheckInRoomWithPolicy"; 
				test_description = "Verify CP MRB Check-In Room With Check-In Policy <br>"
						+ "<a href='https://innroad.testrail.io/index.php?/cases/view/681833' target='_blank'>"
						+ "Click here to open TestRail: C681833</a><br>"
						+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682469' target='_blank'>"
						+ "Click here to open TestRail: C682469</a><br>"
						+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682505' target='_blank'>"
						+ "Click here to open TestRail: C682505</a> "
						+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/681834' target='_blank'>"
						+ "Click here to open TestRail: C681834</a> <br>"; 
				test_catagory = "CPReservation_CheckIN";
				 testName = test_name;

				app_logs.info("##################################################################################");
				app_logs.info("EXECUTING: " + testName + " TEST.");
				app_logs.info("##################################################################################");
				 test_steps.add("<b>===============================Verifyig Check-In Room With Policy===============================</b>");
			}
			
			//Removed All Elements from List
			Rooms.removeAll(Rooms); 
			roomCharges.removeAll(roomCharges);
			roomNos.removeAll(roomNos);
			RoomAbbri.removeAll(RoomAbbri);
			rc.Abbreviation.removeAll(rc.Abbreviation);
			RoomButtonStatus.removeAll(RoomButtonStatus);
			
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
			
		/*	//Delete Tax
			try		
			{
				test_steps.add("<b>****Delete Tax****</b>");
			//	Wait.wait5Second();
				nav.ClickTaxesTab(driver);
				test_steps.add("Navigate to Taxes");
				tax.DeleteAllTaxExcept(driver, map.get("TaxName").toString(), test_steps);
			}
			catch (Exception e) {
				e.printStackTrace();
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
					Utility.updateReport(e, "Failed to Create New Tax", testName, "Reservation", driver);
				} else {
					Assert.assertTrue(false);
				}
			} catch (Error e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
					Utility.updateReport(e, "Failed to Create New Tax", testName, "Reservation", driver);
				} else {
					Assert.assertTrue(false);
				}
			}
			*/
			
			//Verify existing policy
			try
			{
				   test_steps.add("<b>****Check and Delete Policy if Already Exist****</b>");
					nav.Inventory(driver);
					test_steps.add("Navigate To Inventory");
					nav.policies(driver);
					test_steps.add("Navigate To Policies Tab");
					policies.Select_PolicyType(driver, map.get("PolicyType").toString(), test_steps);
					policies.ClickSearchPolicy(driver,test_steps);
					WebElements_Policies policiesElement= new WebElements_Policies(driver);
					boolean isPolicyGridExit=policiesElement.Policy_TableShow.isDisplayed();
	                if(isPolicyGridExit)	
	                {
	                  	boolean isPolicyExist=policiesElement.Table_PolicyTypeColumn.size()>1;
	                	if(isPolicyExist)
	                	{
	                		boolean isExist=false;
	                		test_steps.add("More Than One Policies Exist ");
	        				app_logs.info(" More Than One Policies Exist");
	        				if(!isExist)
	                    	{
	        				policies.DeleteAllPolicies(driver, isExist);
	        				test_steps.add("Deleted All Policies");
	        				app_logs.info("Deleted All  Policies");
	                    	}
	                	}
	                	else
	                	{
	                		test_steps.add("Single  policies  Exist ");
	        				app_logs.info(" Single policies  Exist");
	        				policies.DeleteAllPolicies(driver, isPolicyExist);
	        				test_steps.add("Deleted Policy");
	        				app_logs.info("Deleted  Policy");
	                	}
	                	
	                }
	                else
	                {
	                	test_steps.add("Policy Doesn't Exist");
	    				app_logs.info("Policy Doesn't Exist");
	                }			
		
			}catch (Exception e) {
				e.printStackTrace();
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
					Utility.updateReport(e, "Failed to delete Policies", testName, "Room class", driver);
				} else {
					Assert.assertTrue(false);
				}
			} catch (Error e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
					Utility.updateReport(e, "Failed to delete Policies", testName, "Room class", driver);
				} else {
					Assert.assertTrue(false);
				}
			}
			
			
			if(map.get("WithPolicy").toString().equals("Yes"))
			{
			//Create new Policy	
				test_steps.add("<b>****Start Creating New Policy****</b>");
					try
					{
						policies.Select_PolicyType(driver, map.get("PolicyTypeAll").toString(), test_steps);
						test_steps.add("<b>****Start Creating New Policy****</b>");		
						policies.NewPolicybutton(driver,map.get("PolicyType").toString(),test_steps);
					    Policyname=map.get("PolicyName").toString()+Utility.getTimeStamp();
						policies.Enter_Policy_Name(driver, Policyname,test_steps);
						policies.Enter_Policy_Desc(driver, map.get("PolicyText").toString(), map.get("PolicyDesc").toString());
						test_steps.add("Enter Policy Text and Description: <b>"+map.get("PolicyText").toString()+" AND "+ map.get("PolicyDesc").toString()+"</b>");		
						policies.Enter_Checkin_Policy_Attributes(driver, map.get("PolicyAttributes").toString(), map.get("Percentage").toString());
						policies.Associate_Sources(driver);
						test_steps.add(" Associate Sources");
						policies.Associate_Seasons(driver);
						test_steps.add(" Associate Seasons");
						policies.Associate_RoomClasses(driver);
						test_steps.add(" Associate Room Classes");
						policies.Associate_RatePlans(driver);
						test_steps.add(" Associate Rate Plans");
						policies.Save_Policy(driver);
						test_steps.add("Click Save Button");
						test_steps.add("Policy Saved Successfully: <b>"+Policyname+"</b>");
					}catch (Exception e) {
						e.printStackTrace();
						if (Utility.reTry.get(testName) == Utility.count) {
							RetryFailedTestCases.count = Utility.reset_count;
							Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
							Utility.updateReport(e, "Failed to New Policy", testName, "Reservation", driver);
						} else {
							Assert.assertTrue(false);
						}
					} catch (Error e) {
						if (Utility.reTry.get(testName) == Utility.count) {
							RetryFailedTestCases.count = Utility.reset_count;
							Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
							Utility.updateReport(e, "Failed to New Policy", testName, "Reservation", driver);
						} else {
							Assert.assertTrue(false);
						}
					}   

			}
			
					//Create New Reservation
					try
					{
			    	    nav.Reservation_Backward_1(driver);
						app_logs.info("Navigate to Reservation");
						test_steps.add("<b>****Start Creating New Reservation****</b>");
						res.click_NewReservation(driver, test_steps);
						res.select_Dates(driver, test_steps, checkInDate, checkOutDate, map.get("Adults").toString(), map.get("Children").toString(), map.get("Rateplan").toString(), map.get("PromoCode").toString(),map.get("IsSplitRes").toString());
						res.clickOnFindRooms(driver, test_steps);
						res.select_MRBRooms(driver, test_steps, map.get("RoomClass").toString(), map.get("IsAssign").toString(),map.get("Account").toString());
						depositAmount=res.deposit(driver, test_steps, map.get("IsDepositOverride").toString(), map.get("DepositOverrideAmount").toString());
						res.clickNext(driver, test_steps);
						 yearDate=Utility.getFutureMonthAndYearForMasterCard(driver, test_steps);
						 Random random = new Random();
						 int x = random.nextInt(900);
						 guestFirstName= map.get("GuestFirstName").toString()+x;
						 guestLastName=map.get("GuestLastName").toString()+x;
						res.enter_MRB_MailingAddressForMRB(driver, test_steps, map.get("Salutation").toString(), guestFirstName,guestLastName, 

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
						status=res.getPanelStatusStatusName(driver);
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
					
					// Check In Policy 		
					try
					{
						
						res.click_DeatilsTab(driver, test_steps);
						test_steps.add("<b>****Start Verifying Check-In Policy In Detail Page****</b>");
						if(map.get("WithPolicy").toString().equals("No"))
						{
						res.verifyPOLICIESANDDISCLAIMERSNoShowPolicy(driver, test_steps,map.get("NoPolicy").toString(),map.get("PolicyType").toString(),map.get("Percentage").toString());
						}
						else if(map.get("WithPolicy").toString().equals("Yes"))
						{
							res.verifyCPReservationDetailTabPolicies(driver, test_steps,map.get("PolicyType").toString(),map.get("Percentage").toString(),Policyname,map.get("PolicyText").toString());

						}
						test_steps.add("<b>****Start CheckIn Room****</b>");
						int totalRooms=res.getTotalStayInfoRooms(driver);
						res.stayInfoCheckIn(driver, test_steps, map.get("CheckInRoom").toString(),roomCharges,Rooms,RoomAbbri);
						test_steps.add("<b>****CheckIn All Window Verifying GUEST CONTACT INFO ****</b>");
						res.clickGuestContactInfoCollapseIcon(driver, test_steps);
						guestNameCheckInRoom=res.verifyGuestContactInfoForCheckInRoom(driver, test_steps,res.TotalRoom,map.get("CheckInRoom").toString(), map.get("Salutation").toString(),guestFirstName, guestLastName, map.get("Email").toString(), map.get("PhoneNumber").toString());					
						res.verifyReservationPopWindow(driver, map.get("PolicyType").toString(), guestNameCheckInRoom, status, reservation, test_steps, map.get("PolicyTypeCheckOut").toString());
						test_steps.add("<b>****CheckIn All Window Verifying STAY INFO ****</b>");
						res.verifyStayInfoForCheckInRoom(driver, test_steps,totalRooms,map.get("CheckInRoom").toString(), checkInDate, checkOutDate,  map.get("Adults").toString(),
								 map.get("Children").toString(), map.get("RoomClass").toString(), roomCharges, map.get("Salutation").toString(),
								 guestFirstName, guestLastName,map.get("Rateplan").toString());
						res.generatGuestReportToggle(driver, test_steps,map.get("IsGuesRegistration").toString());		
						res.completeCheckInProcess(driver, test_steps);
						if(res.confirmCheckIn)
						{
							
							if(res.isRoomDirty) 
							{
								test_steps.add("Successfully Verify The Dirty Room Status Pop-Up Message"
								+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/682469' target='_blank'>"
								+ "Click here to open TestRail: C682469</a><br>");
							}
						}
						else if(res.confirmCheckInWithPayment)
						{
							if(res.isRoomDirty) 
							{
								test_steps.add("Successfully Verify The Dirty Room Status Pop-Up Message"
								+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/682469' target='_blank'>"
								+ "Click here to open TestRail: C682469</a><br>");
							}
							double tripAmount=Double.valueOf(res.actualRoomCheckInCharge);
							int amt=(int)tripAmount;
							String tripAmounts=String.valueOf(amt);

							//Verification No Show Payment
							res.commonMethodForPaymentPopUpVerification(driver, test_steps, map.get("CheckInPaymentMsg").toString(),yearDate,
									map.get("NameOnCard").toString(),map.get("CardNo").toString(),map.get("PaymentType").toString(),map.get("Authorize").toString() ,tripAmounts,res.TripSummary_TaxServices,res.TripSummary_RoomCharges,map.get("PolicyType").toString(),res.AmountPaid );   
							res.verifyReservationPopWindowPolicyName(driver, test_steps, Policyname,map.get("PolicyType").toString());
							res.verifyPolicyToolTip(driver, test_steps, Policyname, map.get("PolicyText").toString(),map.get("PolicyType").toString());
							res.getTodaysANDYesturdayDate();
							System.out.println(res.oneDayBefore + res.PreviousDay);
							//Change Payment Method and Set as Main Payment 
							res.SetAsMainPaymentMethod(driver, test_steps, map.get("PaymentType").toString(), map.get("PaymentTypeTwo").toString(),res.oneDayBefore,res.PreviousDay,map.get("PolicyType").toString());
							paymentMethod=res.getPaymentMethod(driver, test_steps);
								//Added Notes and Click Log button
							res.addNotesAndClickLogORPayORAuthorizedButton(driver, test_steps, map.get("Notes").toString());
							test_steps.add("<b>****Start Verifying Successful Window****</b>");
							//Verification No Show Successful Window
							amount=res.Amount;
							System.out.println(amount);
						res.commonMethodForSuccessfullWindowVerification(driver, test_steps,map.get("SuccessMsg").toString() ,map.get("StatusApproved").toString() ,map.get("PaymentTypeTwo").toString() ,map.get("Notes").toString(),
									map.get("BalanceMoney").toString(),map.get("Capture").toString(),amount,map.get("PolicyTypeCheckOut").toString(),res.previousDate);

						}
						if(res.confirmCheckInWithPayment)
						{
							//Click Close button of No Show Successful Window
							res.clickCloseButtonOfSuccessModelPopup(driver, test_steps); 
						}
						
						 if(res.isRoomReserved||res.isRoomUnAssigned)
							{
					         res.getRoomsOnDetailPage(driver, NewRooms);
					         System.out.println(NewRooms);
							}
						
						 String statusPath = "//span[@class='ng-status'][contains(text(),'Reserved')]";
							Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(statusPath)), driver);
						test_steps.add("<b>****Start Verifying RESERVED Status****</b>");
						res.verifyReservationStatusStatus(driver, test_steps, status );
						test_steps.add("<b>****Start Verifying Check-Out  Button ****</b>");  
						res.verifyStayInfoCheckOutButton(driver, test_steps, res.actualRoomCheckIn);
						if(res.confirmCheckInWithPayment)
						{
							test_steps.add("<b>****Start Verifying Folio Details Line Item****</b>");
							res.click_Folio(driver, test_steps);
							res.click_FolioDetail_DropDownBox(driver, test_steps);
							 String[] abbs=res.actualRoomAbbCheckIn.split(":");
							 String Finalabb=abbs[1].trim();
							 System.out.println(" Room No: "+Finalabb);
							 if(res.isRoomReserved||res.isRoomUnAssigned)
								{
                               res.clickFolioDetailOptionValue(driver, test_steps, Finalabb, NewRooms.get(res.actualRoomCheckIn));
								}
							 else
							 {
							res.clickFolioDetailOptionValue(driver, test_steps, Finalabb, res.actualRoomClassCheckIn);
							 }
							
							//Verify Line Item
							res.verifyLineItem(driver, test_steps, res.previousDate,res.PreviousDay,map.get("PaymentTypeTwo").toString(),map.get("PaymentTypeTwo").toString(), amount);
					
						}
					
						//Click History Tab
						res.click_History(driver, test_steps);
						if(res.isRoomReserved||res.isRoomUnAssigned)
						{
							res.verifyHistoryForCheckin(driver, test_steps, map.get("HistoryDesc").toString(), res.actualRoomAbbCheckIn, NewRooms.get(res.actualRoomCheckIn),map.get("SingleRoom").toString(),RoomAbbri ,Rooms);
							}
						else
						{
						res.verifyHistoryForCheckin(driver, test_steps, map.get("HistoryDesc").toString(), res.actualRoomAbbCheckIn, res.actualRoomClassCheckIn,map.get("SingleRoom").toString(),RoomAbbri ,Rooms);
						}
						if(res.confirmCheckInWithPayment)
						{
							 String[] abbs=res.actualRoomAbbCheckIn.split(":");
							 String Finalabb=abbs[1].trim();
							 System.out.println(" Room No: "+Finalabb);
							 if(res.isRoomReserved||res.isRoomUnAssigned)
								{
								 res.commonMethodToverifyHistoryTabDescriptionForCheckINCheckOut(driver, test_steps, amount, map.get("PolicyType").toString(), Finalabb, NewRooms.get(res.actualRoomCheckIn), paymentMethod);
									
								}
							 else
							 {
							res.commonMethodToverifyHistoryTabDescriptionForCheckINCheckOut(driver, test_steps, amount, map.get("PolicyType").toString(), Finalabb, res.actualRoomClassCheckIn, paymentMethod);
							 }
							
						}
						 
					if(map.get("WithPolicy").toString().equals("Yes"))
							
						{
						test_steps.add("<b>****Start Verifying Updated By on Payment Item Detail Popup****</b>");
							res.click_Folio(driver, test_steps);
							res.click_FolioDetail_DropDownBox(driver, test_steps);
							 String[] abbs=res.actualRoomAbbCheckIn.split(":");
							 String Finalabb=abbs[1].trim();
							 System.out.println(" Abb No: "+Finalabb);
							 if(res.isRoomReserved||res.isRoomUnAssigned)
								{
								      res.clickFolioDetailOptionValue(driver, test_steps, Finalabb, NewRooms.get(res.actualRoomCheckIn));
										
								}
							 else
							 {
							res.clickFolioDetailOptionValue(driver, test_steps, Finalabb, res.actualRoomClassCheckIn);
							 }
						
							 if(res.confirmCheckIn)
								{
									
									res.verifyPaymentDetailUpdateBy(driver, map.get("NameOnCard").toString(),
											map.get("UpdateByName").toString(), map.get("PaymentType").toString());
									res.clickPaymentDetailCancel(driver, test_steps);
									
								}
							 else if(res.confirmCheckInWithPayment)
								{
									res.verifyPaymentDetailUpdateBy(driver, map.get("NameOnCard").toString(), map.get("UpdateByName").toString(),paymentMethod);
									 res.clickPaymentDetailClose(driver, test_steps);
							 }
							   
							test_steps.add("Successfully  Verified  Update By : <b>"+map.get("UpdateByName").toString()+"</b> "
									+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/682505' target='_blank'>"
									+ "Click here to open TestRail: C682505</a> <br>");
						}
						
							test_steps.add("<b>****Start Verifying Room Status ON GS****</b>");
							nav.Guestservices(driver);
							test_steps.add("Navigated to Guestservices");
							roomstatus.verifyRoomStatusTabEnabled(driver,test_steps);
							roomstatus.verifiedGuestServicesDataLoadedOrNot(driver, test_steps);
							res.verifyRoomStatusAfterCheckedInRoom(driver, test_steps,Rooms ,RoomAbbri,map.get("DirtyStatus").toString(),map.get("CleanStatus").toString(),map.get("RoomStatus").toString() ,map.get("OccupiedStatus").toString() );							
							nav.Click_ReservationMenu(driver);							
							app_logs.info("Navigate to Reservation");
							test_steps.add("<b>****Start Verifying Room Status ON TapeChart****</b>");							
							nav.TapeChart(driver);
							test_steps.add("Successfully Navigate to TapeChart");
							tapeChart.TapeChart_Search_MRB(driver,map.get("CheckInDate").toString(), map.get("Adults").toString(), test_steps);
						    if(res.isRoomReserved||res.isRoomUnAssigned)
						    {
						    	tapeChart.Verify_CheckedIn_RoomsStatus(driver, test_steps,  NewRooms, RoomAbbri, map.get("DirtyStatus").toString(), map.get("CleanStatus").toString());
						    }
						    else
						    {
							tapeChart.Verify_CheckedIn_RoomsStatus(driver, test_steps,  Rooms, RoomAbbri, map.get("DirtyStatus").toString(), map.get("CleanStatus").toString());
						    }					
							test_steps.add("Successfully  Verified  Room Status on GS and Tape Chart"
									+ "<br><a href='https://innroad.testrail.io/index.php?/cases/view/681834' target='_blank'>"
									+ "Click here to open TestRail: C681834</a> <br>");						
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);

					    
					}
						catch (Exception e) {
							e.printStackTrace();
							if (Utility.reTry.get(testName) == Utility.count) {
								RetryFailedTestCases.count = Utility.reset_count;
								Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
								Utility.updateReport(e, "Failed to Verify CheckIn", testName, "Reservation", driver);
							} else {
								Assert.assertTrue(false);
							}
						} catch (Error e) {
							if (Utility.reTry.get(testName) == Utility.count) {
								RetryFailedTestCases.count = Utility.reset_count;
								Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
								Utility.updateReport(e, "Failed to Verify CheckIn", testName, "Reservation", driver);
							} else {
								Assert.assertTrue(false);
							}
					}
					
		}
		@DataProvider
		public Object[][] getData() {
		
			return Utility.getDataMap("VerifyCheckInRoomCPReservationM", excel);
		}

		@AfterClass(alwaysRun=true)
		public void closeDriver() {
			driver.quit();
		}
		
		
}
