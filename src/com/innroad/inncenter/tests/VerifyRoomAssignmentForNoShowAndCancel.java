package com.innroad.inncenter.tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

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
import com.innroad.inncenter.pageobjects.Properties;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class VerifyRoomAssignmentForNoShowAndCancel extends TestCore{
	private WebDriver driver = null;
	public static String test_name = null;
	public static String test_nameOne = null;
	public static String test_description = null;
	public static String test_catagory = null;
	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	List<String> roomNos = new ArrayList<String>();
	List<String> roomNosTwo = new ArrayList<String>();
	List<String> roomNosThree = new ArrayList<String>();
	List<String> roomNosFour = new ArrayList<String>();
	Navigation navigation = new Navigation();
	CPReservationPage reservation = new CPReservationPage();
	ReservationSearch reservationSearch= new ReservationSearch();
	Properties properties = new Properties();
	Rate rate = new Rate();
	RoomClass roomClass= new RoomClass();
	Date currentDate = null, previousDate = null;
	String testName = null,yearDate=null,firstReservation=null, secondReservation=null, rateNames=null,rateDisplayName=null,roomClassNames=null,roomClassAbbrivations=null,
			guestFirstOne=null,guestFirstTwo=null,guestSecondOne=null,guestSecondTwo=null,timeZone = null,propertyName = null;
	String loading = "(//div[@class='ir-loader-in'])";
	String status="Reserved";
	String statusPath = "//span[@class='ng-status'][contains(text(),'" + status + "')]";


	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}
	@Test(dataProvider = "getData", groups = "Reservation")
	public void verifyRoomAssignmentForNoShowAndCancel(String uRL, String clientId, String userName,
			String password, String roomClassName, String roomClassAbbrivation,String maxAdults,String maxPersopns,String roomQuantity,
			String rateName,String displayName,String amount,String ratePolicy,String rateDescription,String checkInDate, 
			String checkOutDate, String adults, String children,String ratePlan,String salutation,String guestFirstName, String guestLastName,
			String isGuesProfile, String paymentType,String cardNumber, String nameOnCard, String travelAgent, String marketSegment, String referral, String reason,
			String notes, String notesNoShow) {
		test_name = "VerifyRoomAssignmentForNoShowAndCancel";
		test_description = "Verify If Original Room Class And Room Number Are Not Available, Set Default To Unassigned Within The Original Room Class<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682433' target='_blank'>"
				+ "Click here to open TestRail: C682433</a><br>";
		test_catagory = "CPReservation";
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
				
				// Get PropertyName
				try {
					propertyName = properties.getProperty(driver, testSteps);

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

				try {
					navigation.Setup(driver);
					navigation.Properties(driver);
					navigation.open_Property(driver, testSteps, propertyName);
					navigation.click_PropertyOptions(driver, testSteps);
					timeZone = navigation.get_Property_TimeZone(driver);
					currentDate = reservation.getTodaysDate("MM/dd/yyyy", timeZone);
					previousDate = reservation.getPreviousDate("MM/dd/yyyy", timeZone);
					System.out.println(currentDate + "" + previousDate);

				} catch (Exception e) {
					e.printStackTrace();
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
						Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
					} else {
						Assert.assertTrue(false);
					}
				} catch (Error e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
						Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
					} else {
						Assert.assertTrue(false);
					}
				}
				// Create Room Class		
				try {
					
					testSteps.add("<b>****Start Creating New Room Class****</b>");
					navigation.Setup(driver);
					testSteps.add("navigationigated to Setup");
					navigation.RoomClass(driver);
					testSteps.add("Navigated to Room Class");
					roomClassNames = roomClassName + Utility.getTimeStamp();
					roomClassAbbrivations = roomClassAbbrivation + Utility.getTimeStamp();
					navigation.NewRoomClass(driver);
					testSteps.add("Navigated to New Room Class");
					app_logs.info("Navigated to New Room Class");
					/*roomClass.CreateRoomClass(driver, roomClassNames, roomClassAbbrivations, "", maxAdults, maxPersopns,
							roomQuantity, test, testSteps);*/
					roomClass.create_RoomClass(driver, roomClassNames, roomClassAbbrivations, "", maxAdults, maxPersopns,
							roomQuantity, test, testSteps);
					testSteps.add("Room Class Created: <b>"+roomClassNames+"</>");
					app_logs.info(" Room Class Created: "+roomClassNames);
				} catch (Exception e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
						Utility.updateReport(e, "Failed to Create Room Class", testName, "Room class", driver);
					} else {
						Assert.assertTrue(false);
					}
				} catch (Error e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
						Utility.updateReport(e, "Failed to Create Room Class", testName, "Room class", driver);
					} else {
						Assert.assertTrue(false);
					}
				}

				// Create Rates and Associate Room Class
				try {
					testSteps.add("<b>****Start Create New Rates and Associate Room Class with Rates****</b>");
					navigation.Inventory(driver);
					testSteps.add("Navigate to Inventory");
					app_logs.info("Navigate to Inventory");
					navigation.Rates1(driver);
					testSteps.add("Navigate to Rates");
					rateNames = rateName + Utility.getTimeStamp();
					rateDisplayName = displayName + Utility.getTimeStamp();
					rate.CreateRate(driver, rateNames, maxAdults, maxPersopns, amount, maxAdults, maxPersopns, rateDisplayName,
							ratePolicy, rateDescription, roomClassNames, testSteps);			
					testSteps.add("Successfull Created Rate: <b>" + rateNames + "</b>");
					app_logs.info("Successfull Created Rate: " + rateNames);
			

				} catch (Exception e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
						Utility.updateReport(e, "Failed to Search Rates and Associate Room Class", testName, "Room class",
								driver);
					} else {
						Assert.assertTrue(false);
					}
				} catch (Error e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
						Utility.updateReport(e, "Failed to Search Rates and Associate Room Class", testName, "Room class",
								driver);
					} else {
						Assert.assertTrue(false);
					}
				}
					
				
				// Get checkIN and Checkout Date
				try {
					if (!(Utility.validateInput(checkInDate))) {
						for (int i = 0; i < guestFirstName.split("\\|").length; i++) {
							checkInDates.add(Utility.getCurrentDate("dd/MM/yyyy"));
							checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(1), "MM/dd/yyyy", "dd/MM/yyyy"));
						}
					} else {
						checkInDates = Utility.splitInputData(checkInDate);
						checkOutDates = Utility.splitInputData(checkOutDate);
					}
					checkInDate = checkInDates.get(0);
					checkOutDate = checkOutDates.get(0);
					app_logs.info(checkInDate);
					app_logs.info(checkOutDate);
					Date date=new SimpleDateFormat("dd/MM/yyyy").parse(checkInDate);  
					app_logs.info(date);

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
				
				
				//Start Creating Reservation 
				
				try
				{
						
					    navigation.Reservation_Backward_1(driver);
						testSteps.add("<b>****Start Creating New Reservation****</b>");
						reservation.click_NewReservation(driver, testSteps);	
						reservation.select_CheckInDate(driver, testSteps, checkInDate);
						reservation.select_CheckoutDate(driver, testSteps, checkOutDate);
						reservation.enter_Adults(driver, testSteps, adults);
						reservation.enter_Children(driver, testSteps, children);
						reservation.select_Rateplan(driver, testSteps, ratePlan,"");
						reservation.clickOnFindRooms(driver, testSteps);
						reservation.select_SpecificRoom(driver, testSteps, roomClassNames , "" ,"" );
						reservation.clickNext(driver, testSteps);
					    yearDate=Utility.getFutureMonthAndYearForMasterCard(driver, testSteps);
					    Random random = new Random();
						int x = random.nextInt(900);
						guestFirstOne=guestFirstName+x;
						guestSecondOne=guestLastName.toString()+x;
						reservation.enter_MailingAddress_OnUnchecked_CreateGuestProfile(driver, testSteps, salutation, guestFirstOne, guestSecondOne,isGuesProfile);
						reservation.enter_PaymentDetails(driver, testSteps, paymentType, cardNumber, nameOnCard, yearDate);
						reservation.enter_MarketSegmentDetails(driver, testSteps, travelAgent, marketSegment, referral);
						reservation.clickBookNow(driver, testSteps);                                           
						firstReservation=reservation.get_ReservationConfirmationNumber(driver, testSteps);		
						reservation.clickCloseReservationSavePopup(driver, testSteps); 
						roomNos = reservation.getStayInfoRoomNo(driver, testSteps);
						app_logs.info(roomNos);
						
				}catch (Exception e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
						Utility.updateReport(e,  "Failed to Create New reservation", testName, "Reservation", driver);
					} else {
						Assert.assertTrue(false);
					}
				} catch (Error e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.updateReport(e,  "Failed to  Create New reservation", testName, "Reservation", driver);
					} else {
						Assert.assertTrue(false);
					}
				}
				//Cancel The reservation
				try
				{
					testSteps.add("<b>****Start Cancel The Reservation ****</b>");
					//Select Cancellation 
					reservation.reservationStatusPanelSelectStatus(driver, "Cancel",testSteps);   
					reservation.addResonOnCancelModelPopup(driver, testSteps, reason);
					reservation.CompleteCancelProcess(driver, testSteps);
					boolean isCancelReservationExist = reservation.getCancelReservationHeaderWindow(driver);
					if (isCancelReservationExist) {
						reservation.addNotesAndClickLogORPayORAuthorizedButton(driver, testSteps, notes);
						reservation.clickCloseButtonOfSuccessModelPopup(driver, testSteps);
					}				
					testSteps.add("<b>****Start Verifying Roll Back Button ****</b>");
					// Verified Roll Back Button Enabled
					reservation.verifyRollBackButton(driver, testSteps);
					testSteps.add("<b>****Start Verifying Cancelled Status****</b>");
					reservation.verifyReservationStatusStatus(driver, testSteps, "Cancelled");
					testSteps.add("<b>****Start Verifying Cancelled Notes****</b>");   
					Date date=new SimpleDateFormat("dd/MM/yyyy").parse(checkInDate);  
					app_logs.info(date);
					reservation.verifyNoteForSingleReservation(driver, testSteps, "Cancellation", "Cancellation Reason", reason, date,"Cancel");
					
					testSteps.add("<b>****Start Verifying History****</b>");
					// Click History Tab
					reservation.click_History(driver, testSteps);
					reservation.verifyHistoryTabDescription(driver, testSteps,"Cancel");  
					

					
				}
				catch (Exception e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
						Utility.updateReport(e,  "Failed to Cancel the reservation ", testName, "Reservation", driver);
					} else {
						Assert.assertTrue(false);
					}
				} catch (Error e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.updateReport(e,  "Failed to Cancel the reservation  ", testName, "Reservation", driver);
					} else {
						Assert.assertTrue(false);
					}
				}
				
    //Start Creating Another Reservation 
				
				try
				{
					   navigation.Setup(driver);
					    navigation.Reservation_Backward_1(driver);
					   	testSteps.add("<b>****Start Creating Another Reservation****</b>");
						reservation.click_NewReservation(driver, testSteps);
						reservation.select_CheckInDate(driver, testSteps, checkInDate);
						reservation.select_CheckoutDate(driver, testSteps, checkOutDate);
						reservation.enter_Adults(driver, testSteps, adults);
						reservation.enter_Children(driver, testSteps, children);
						reservation.select_Rateplan(driver, testSteps, ratePlan,"");
						reservation.clickOnFindRooms(driver, testSteps);
						reservation.select_SpecificRoom(driver, testSteps, roomClassNames , "" ,"" );
						reservation.clickNext(driver, testSteps);
					    yearDate=Utility.getFutureMonthAndYearForMasterCard(driver, testSteps);
					    Random random = new Random();
						int x = random.nextInt(900);
						guestFirstTwo=guestFirstName+x;
						guestSecondTwo=guestLastName.toString()+x;
						reservation.enter_MailingAddress_OnUnchecked_CreateGuestProfile(driver, testSteps, salutation, guestFirstTwo, guestSecondTwo,isGuesProfile);
						reservation.enter_PaymentDetails(driver, testSteps, paymentType, cardNumber, nameOnCard, yearDate);
						reservation.enter_MarketSegmentDetails(driver, testSteps, travelAgent, marketSegment, referral);
						reservation.clickBookNow(driver, testSteps);                                           
						secondReservation=reservation.get_ReservationConfirmationNumber(driver, testSteps);		
						reservation.clickCloseReservationSavePopup(driver, testSteps);  
						roomNosThree = reservation.getStayInfoRoomNo(driver, testSteps);
						app_logs.info(roomNosThree);
				}catch (Exception e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
						Utility.updateReport(e,  "Failed to Create New reservation Agin", testName, "Reservation", driver);
					} else {
						Assert.assertTrue(false);
					}
				} catch (Error e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.updateReport(e,  "Failed to  Create New reservation Again", testName, "Reservation", driver);
					} else {
						Assert.assertTrue(false);
					}
				}
				
				
				
			//Verified UnAssigned for Cancellation	
				try
				{
					navigation.Reservation_Backward_1(driver);
					testSteps.add("<b>****Start Searching the First Reservation ****</b>");
					app_logs.info("****Start Searching the First Reservation ****");										
					reservationSearch.basicSearchWithResNumber(driver, firstReservation,testSteps);
					testSteps.add("Original Room " + roomNos.get(0)+"</b>");	
					app_logs.info("Original  Room  " + roomNos.get(0));
					reservation.clickRollBackButton(driver, testSteps);
					reservation.verifyRollBackMsg(driver, testSteps, "Are you sure you want to re-open this reservation?");
					reservation.clickYesButtonRollBackMsg(driver, testSteps);
					testSteps.add("<b>****Start Verifying Room If Not Available For Cancellation****</b>");
				    String unassignedMessage="There are 0 Rooms Available for "+roomClassNames+",Are you sure you want to overbook this room class?";
					reservation.verifyUnassignedMsg(driver, testSteps, unassignedMessage);
					reservation.clickYesButtonRollBackMsg(driver, testSteps);
					Wait.explicit_wait_absenceofelement(loading, driver);
					Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(statusPath)), driver);
					roomNosTwo = reservation.getStayInfoRoomNo(driver, testSteps);
					app_logs.info(roomNosTwo);
					reservation.verifyStayInfoRoomNo(driver, testSteps, roomNosTwo);
					testSteps.add("<b>****Start Verifying History****</b>");
					// Click History Tab
					reservation.click_History(driver, testSteps);
					reservation.verifyHistoryTabDescriptionForRollBack(driver, testSteps);					
					reservation.verifyHistoryForChangedToUnAssigned(driver, testSteps, roomNos.get(0), roomClassNames);
					
				}
				catch (Exception e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
						Utility.updateReport(e,  "Failed to Verify Unassigend for Cancel", testName, "Reservation", driver);
					} else {
						Assert.assertTrue(false);
					}
				} catch (Error e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.updateReport(e,  "Failed to  Verify Unassigned for Cancel", testName, "Reservation", driver);
					} else {
						Assert.assertTrue(false);
					}
				}
			// Verified Unassigned for No Show	
				try
				{
					navigation.Reservation_Backward_1(driver);
					testSteps.add("<b>****Start Searching the Second Reservation ****</b>");
					reservationSearch.basicSearchWithResNumber(driver, secondReservation,testSteps);
					reservation.reservationStatusPanelSelectStatus(driver, "No Show", testSteps);
					reservation.CompleteNoShowProcess(driver, testSteps);

					boolean isExist = reservation.verifyConfirmYesButton(driver);

					 app_logs.info("Confirm Message Displayed or Not : " + isExist);
					if (isExist) {
						reservation.clickYesButtonOfConfirmTheNoShowProcessPopupModel(driver, testSteps);
					}
					  boolean isSuccessExist = reservation.verifyNoShowSuccessFull(driver); 
					  app_logs.info("Confirm Success Window Displayed or Not : " + isSuccessExist);
					  if(isSuccessExist)
					  { reservation.clickCloseButtonOfSuccessModelPopup(driver, testSteps);
					  }
					 

					boolean isNoShowWPaymentExist = reservation.getNoShowHeaderWindow(driver);
					if (isNoShowWPaymentExist) {
						reservation.addNotesAndClickLogORPayORAuthorizedButton(driver, testSteps, notesNoShow);
						reservation.clickCloseButtonOfSuccessModelPopup(driver, testSteps);
					}
					
					testSteps.add("  Original Room  " + roomNosThree.get(0)+"</b>");					
					testSteps.add("<b>****Start Verifying Roll Back Button ****</b>");
					// Verified Roll Back Button Enabled
					reservation.verifyRollBackButton(driver, testSteps);
					testSteps.add("<b>****Start Verifying No Show Status****</b>");
					reservation.verifyReservationStatusStatus(driver, testSteps, "NO SHOW");
					testSteps.add("<b>****Start Verifying No Show Notes****</b>"); 
					Date date=new SimpleDateFormat("dd/MM/yyyy").parse(checkInDate);  
					app_logs.info(date);					
					reservation.verifyNoteForSingleReservation(driver, testSteps, "Internal", "No Show", "", date,"No Show");
					testSteps.add("<b>****Start Verifying History****</b>");
					// Click History Tab
					reservation.click_History(driver, testSteps);
					reservation.verifyHistoryTabDescription(driver, testSteps,"No Show");  
					reservation.clickRollBackButton(driver, testSteps);
					reservation.verifyRollBackMsg(driver, testSteps, "Are you sure you want to re-open this reservation?");
					reservation.clickYesButtonRollBackMsg(driver, testSteps);
					testSteps.add("<b>****Start Verifying Room If Not Available For No Show****</b>");
				    String unassignedMessage="There are 0 Rooms Available for "+roomClassNames+",Are you sure you want to overbook this room class?";
					reservation.verifyUnassignedMsg(driver, testSteps, unassignedMessage);
					reservation.clickYesButtonRollBackMsg(driver, testSteps);	
					Wait.explicit_wait_absenceofelement(loading, driver);
					Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(statusPath)), driver);					
					roomNosFour = reservation.getStayInfoRoomNo(driver, testSteps);
					app_logs.info(roomNosFour);
					reservation.verifyStayInfoRoomNo(driver, testSteps, roomNosFour);
					testSteps.add("<b>****Start Verifying History****</b>");
					// Click History Tab
					reservation.click_History(driver, testSteps);
					reservation.verifyHistoryTabDescriptionForRollBack(driver, testSteps);
					reservation.verifyHistoryForChangedToUnAssigned(driver, testSteps, roomNos.get(0), roomClassNames);
				
					
				}
				catch (Exception e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
						Utility.updateReport(e,  "Failed to Create New reservation Agin", testName, "Reservation", driver);
					} else {
						Assert.assertTrue(false);
					}
				} catch (Error e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.updateReport(e,  "Failed to  Create New reservation Again", testName, "Reservation", driver);
					} else {
						Assert.assertTrue(false);
					}
				}
				
				// Delete Rate
				try {
					testSteps.add("<b>****Start Deleting Rates****</b>");
					navigation.Inventory(driver);
					navigation.Rates1(driver);
					rate.deleteRates(driver, rateName);
					testSteps.add("All Rate Deleted Successfully With Name: <b>" + rateName + " </b>");
					app_logs.info("All Rate Deleted Successfully With Name: " + rateName);

				} catch (Exception e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
						Utility.updateReport(e, "Failed to Delete Rates ", testName, "Rate", driver);
					} else {
						Assert.assertTrue(false);
					}
				} catch (Error e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.updateReport(e, "Failed to Delete Rates", testName, "Rate", driver);
					} else {
						Assert.assertTrue(false);
					}
				}
				// Delete Room Class
				try {
					testSteps.add("<b>****Delete Room Class****</b>");
					navigation.Setup(driver);
					testSteps.add("Navigated to Setup");
					navigation.RoomClass(driver);
					roomClass.SearchButtonClick(driver);
					testSteps.add("Click on Search Button");
					app_logs.info("Click on Search Button");
					roomClass.SearchRoomClass(driver, roomClassName, testSteps);
					roomClass.deleteRoomClass(driver, roomClassName);
					testSteps.add("All Room Class Deleted Successfully With Name: <b>" + roomClassName + " </b>");
					app_logs.info("All Room Class Deleted Successfully With Name: " + roomClassName);

					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);

				} catch (Exception e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
						Utility.updateReport(e, "Failed to Delete Room Class ", testName, "Night Audit", driver);
					} else {
						Assert.assertTrue(false);
					}
				} catch (Error e) {
					if (Utility.reTry.get(testName) == Utility.count) {
						RetryFailedTestCases.count = Utility.reset_count;
						Utility.updateReport(e, "Failed to Delete Room Class ", testName, "Night Audit", driver);
					} else {
						Assert.assertTrue(false);
					}
				}
	}
	

	@DataProvider
	public Object[][] getData() {

		return Utility.getData("VerifyRoomAssignmentNoShowCance", excel);

	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
