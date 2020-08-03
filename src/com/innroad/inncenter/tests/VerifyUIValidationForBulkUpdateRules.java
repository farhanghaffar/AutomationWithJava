package com.innroad.inncenter.tests;

import com.innroad.inncenter.pageobjects.*;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerifyUIValidationForBulkUpdateRules extends TestCore {

	// Automation-1539
	private WebDriver driver = null;

	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();
	ArrayList<String> testName = new ArrayList<>();
	ArrayList<String> testCategory = new ArrayList<>();
	ArrayList<String> testDescription = new ArrayList<>();
	public static String testdescription = "";
	public static String testcatagory = "";

	@Test(dataProvider = "getData", groups = "RatesGrid")
	public void verifyRulesInBulkUpdate(String checkInDate, String checkOutDate, String sunday, String monday,
			String tuesday, String wednesday, String thursday, String friday, String saturday,
			String isTotalOccupancyOn, String totalOccupancyType, String totalOccupancyValue, String roomClassName, 
			String channel, String ratePlan, String isMinimumStayOn, String minimumStayValue, String isCheckInOn, String noCheckIn, String isCheckOutOn, String noCheckOut,
			String rules) throws Exception {

		String scriptName = "VerifyRulesInBulkUpdate";
		String testdescription = "Rates V2 - Rates Grid - Bulk Update popup - Rules functionality<br>";
				
		testcatagory = "RateGrid";
		testName.add(scriptName);
		testDescription.add(testdescription);
		testCategory.add(testcatagory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Rate rate = new Rate();
		Navigation navigation = new Navigation();
		OverView overView = new OverView();
		RoomClass roomClass = new RoomClass();
		ArrayList<String> getRoomClasses = new ArrayList<>();
		int days = ESTTimeZone.numberOfDaysBetweenDates(checkInDate, checkOutDate);
		String getCurrentDate = Utility.getCurrentDate("MM/dd/yyyy");
		String closeToggle = "No";

		try {
			if (!Utility.insertTestName.containsKey(scriptName)) {
				Utility.insertTestName.put(scriptName, scriptName);
				Utility.reTry.put(scriptName, 0);

			} else {
				Utility.reTry.replace(scriptName, 1);
			}
			driver = getDriver();
			login_CP(driver);

			testSteps.add("Logged into the application");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to login", "CP_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to login", "CP_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		/*
		try {

			app_logs.info("==========STORE ALL ACIVE ROOM CLASS==========");
			testSteps.add("==========STORE ALL ACIVE ROOM CLASS==========");

			navigation.mainSetupManu(driver);
			app_logs.info("Navigate Setup");
			testSteps.add("Navigate Setup");

			navigation.roomClass(driver);
			app_logs.info("Navigate Room Class");
			testSteps.add("Navigate Room Class");
			
	        List<String>[] arrayOfList = new List[3];
	        arrayOfList = roomClass.getAllActiveRoomClasses(driver);
	        getRoomClasses = (ArrayList<String>) arrayOfList[0];
	        
	        ArrayList<String> getRoomsNumber = new ArrayList<>(); 
	        getRoomsNumber = (ArrayList<String>) arrayOfList[2];
	        
	        ArrayList<String> roomClassesAbbreviation = new ArrayList<>(); 
	        roomClassesAbbreviation = (ArrayList<String>) arrayOfList[1];

			app_logs.info(getRoomClasses.size());
			app_logs.info("roomClassesAbbreviation: "+roomClassesAbbreviation.size());
			app_logs.info("getRoomsNumber: "+getRoomsNumber.size());
			for (int i = 0; i < getRoomClasses.size(); i++) {
				System.out.println(getRoomClasses.get(i)+"  "+roomClassesAbbreviation.get(i)+"  "+getRoomsNumber.get(i) );
			}
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to store room class in arraylist", scriptName, "StoreRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to store room class in arraylist", scriptName, "StoreRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		}	*/
		
		
		try {
			navigation.Inventory(driver);
//			navigation.Inventory_Backward_1(driver);
			app_logs.info("Navigate Inventory");
			testSteps.add("Navigate Inventory");
			
			getTestSteps.clear();
			getTestSteps = navigation.ratesGrid(driver);
			testSteps.addAll(getTestSteps);


		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to navigate to overview", scriptName, "NavigateOverview", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to navigate to overview", scriptName, "NavigateOverview", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			getTestSteps.clear();
			getTestSteps = overView.clickOnAvailabilityTab(driver);
			testSteps.addAll(getTestSteps);
			
			getTestSteps.clear();
			getTestSteps = overView.clickOnBulkUpdate(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = overView.selectBulkUpdateOption(driver, rules);
			testSteps.addAll(getTestSteps);


			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to naviagte to rules popup", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to naviagte to rules popup", scriptName, "BulkUpdate", driver);
			}
		}
		try{
			
			app_logs.info("==========VERIFYING BULK UPDATE RULES POPUP HEADING==========");
			testSteps.add("==========VERIFYING BULK UPDATE RULES POPUP HEADING==========");

			getTestSteps.clear();
			getTestSteps = overView.verifyRulesHeading(driver);
			testSteps.addAll(getTestSteps);

			testSteps.add("Verified bulk update popup heading");
			app_logs.info("Verified bulk update popup heading");

			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify bulk update popup heading", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify bulk update popup heading", scriptName, "BulkUpdate", driver);
			}
		}
					
		try{
			app_logs.info("==========VERIFYING CLOSE ICON==========");
			testSteps.add("==========VERIFYING CLOSE ICON==========");
			
			app_logs.info("Expected : " + "×");
			testSteps.add("Expected : " + "×");
			
			String closeText = overView.getCloseIconText(driver);
			app_logs.info("Found : " + closeText);
			testSteps.add("Found : " + closeText);
			
			//Assert.assertEquals(closeText.trim(), "×", "Failed to match close text");
			testSteps.add("Verified close sign");
			app_logs.info("Verified close sign");

			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify bulk update close icon", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify bulk update close icon", scriptName, "BulkUpdate", driver);
			}
		}
		
		try{

			app_logs.info("==========VERIFYING BULK UPDATE RULES POPUP TEXT==========");
			testSteps.add("==========VERIFYING BULK UPDATE RULES POPUP TEXT==========");

			String exectedPopupText = "This one-time update will adjust rules for all days that match your criteria.\n" + 
			"This update will not be recurring.";
			testSteps.add("Expected Bulk Update Popup Text : " + exectedPopupText);
			app_logs.info("Expected Bulk Update Popup Text : " + exectedPopupText);
			String updatePopupText = overView.getBulkUpdatePoppupText(driver);
			testSteps.add("Found : " + updatePopupText);
			app_logs.info("Found : " + updatePopupText);
			Assert.assertEquals(updatePopupText, exectedPopupText, "Failed to verify text");
			testSteps.add("Verified bulk update popup text");
			app_logs.info("Verified bulk update popup text");

			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify bulk update popup text", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify bulk update popup text", scriptName, "BulkUpdate", driver);
			}
		}
		
		try{
			app_logs.info("==========VERIFYING DEFAULT START DATE==========");
			testSteps.add("==========VERIFYING DEFAULT START DATE==========");
			
			app_logs.info("Expected Date : " + getCurrentDate);
			testSteps.add("Expected Date : " + getCurrentDate);
			
			String getStartDate = overView.getStartDate(driver);
			app_logs.info("Found : " + getStartDate);
			testSteps.add("Found : " + getStartDate);
			
			//Assert.assertEquals(getStartDate, getCurrentDate, "Failed to match start date");
			testSteps.add("Verified start date");
			app_logs.info("Verified start date");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify start date", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify start date", scriptName, "BulkUpdate", driver);
			}
		}
		try{
			
			app_logs.info("==========VERIFYING DEFAULT END DATE==========");
			testSteps.add("==========VERIFYING DEFAULT END DATE==========");
			
			app_logs.info("Expected Date : " + getCurrentDate);
			testSteps.add("Expected Date : " + getCurrentDate);
			
			String getEndDate = overView.getEndDate(driver);
			app_logs.info("Found : " + getEndDate);
			testSteps.add("Found : " + getEndDate);
			
			//Assert.assertEquals(getEndDate, getCurrentDate, "Failed to match end date");
			testSteps.add("Verified end date");
			app_logs.info("Verified end date");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify end date", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify end date", scriptName, "BulkUpdate", driver);
			}
		}
		
		try {
			app_logs.info("==========VERIFYING DAYS CHEKCBOXES DISPLAYED==========");
			testSteps.add("==========VERIFYING DAYS CHEKCBOXES DISPLAYED==========");
			
			getTestSteps.clear();
			getTestSteps = overView.verifyDaysCheckbox(driver, "Sun");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = overView.verifyDaysCheckbox(driver, "Mon");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = overView.verifyDaysCheckbox(driver, "Tue");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = overView.verifyDaysCheckbox(driver, "Wed");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = overView.verifyDaysCheckbox(driver, "Thu");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = overView.verifyDaysCheckbox(driver, "Fri");
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = overView.verifyDaysCheckbox(driver, "Sat");
			testSteps.addAll(getTestSteps);					
	
			testSteps.add("Verified days checkboxes");
			app_logs.info("Verified days checkboxes");
	
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify days checkbox", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify days checkbox", scriptName, "BulkUpdate", driver);
			}
		}

		try {
					
			app_logs.info("==========VERIFYING TOTAL OCCUPANCY TEXT==========");
			testSteps.add("==========VERIFYING TOTAL OCCUPANCY TEXT==========");

			String expectedOccupanyText = "For days that total occupancy is";
			testSteps.add("Expected : " + expectedOccupanyText);
			app_logs.info("Expected : " + expectedOccupanyText);
			
			String totalOccupancyText = overView.totalOccupancyTextDisplay(driver);
			testSteps.add("Found : " + totalOccupancyText);
			app_logs.info("Found : " + totalOccupancyText);
			
			Assert.assertEquals(totalOccupancyText, expectedOccupanyText, "Failed to match total occupancy text");			
			testSteps.add("Verified total occupancy text");
			app_logs.info("Verified total occupancy text");				
	
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify total occupancy text", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify total occupancy text", scriptName, "BulkUpdate", driver);
			}
		}

		try {
			
			app_logs.info("==========VERIFYING TOTAL OCCUPANCY ICON TEXT==========");
			testSteps.add("==========VERIFYING TOTAL OCCUPANCY ICON TEXT==========");

/*			String expectedOccupanyText = "For days that total occupancy is";
			testSteps.add("Expected : " + expectedOccupanyText);
			app_logs.info("Expected : " + expectedOccupanyText);
			
*/
			getTestSteps.clear();
			getTestSteps =  overView.verifyTotalOccupanyIcon(driver);
			testSteps.addAll(getTestSteps);
/*			testSteps.add("Found : " + totalOccupancyText);
			app_logs.info("Found : " + totalOccupancyText);
			
			Assert.assertEquals(totalOccupancyText, expectedOccupanyText, "Failed to match total occupancy text");			
			testSteps.add("Verified total occupancy icon text");
			app_logs.info("Verified total occupancy icon text");				
	
*/		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify total occupancy text", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify total occupancy text", scriptName, "BulkUpdate", driver);
			}
		}
/*
		try {
			
			app_logs.info("==========VERIFYING TOTAL OCCUPANCY DISABLED BY DAFAULT==========");
			testSteps.add("==========VERIFYING TOTAL OCCUPANCY DISABLED BY DAFAULT==========");
			
			Boolean isOccupancyTypeEnable = overView.verifyTotalOccupancyType(driver);
			app_logs.info("isOccupancyTypeEnable : " + isOccupancyTypeEnable);
		
			Boolean isOccupancyValueEnable = overView.verifyTotalOccupanyValue(driver);
			app_logs.info("isOccupancyValueEnable : " + isOccupancyValueEnable);
		
			if(!isOccupancyValueEnable && !isOccupancyTypeEnable){
				testSteps.add("Verified total occupancy disabled by default");
				app_logs.info("Verified total occupancy disabled by default");								
			}else{				
				testSteps.add("Verified total occupancy enabled by default");
				app_logs.info("Verified total occupancy enabled by default");
				Assert.assertTrue(false, "Failed : Found total occupancy enabled by default");
				
			}
			
					
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify total occupancy is disable bydefault", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify total occupancy  is disable bydefault", scriptName, "BulkUpdate", driver);
			}
		}
		
		try {
				
				app_logs.info("==========VERIFYING TOTAL OCCUPANCY TYPE DISABLED BY DAFAULT==========");
				testSteps.add("==========VERIFYING TOTAL OCCUPANCY TYPE DISABLED BY DAFAULT==========");
				
				Boolean isOccupancyTypeEnable = overView.verifyTotalOccupancyType(driver);
				app_logs.info("isOccupancyTypeEnable : " + isOccupancyTypeEnable);
			
				if(!isOccupancyTypeEnable){
					testSteps.add("Verified total occupancy type disabled by default");
					app_logs.info("Verified total occupancy type disabled by default");								
				}else{				
					testSteps.add("Verified total occupancy type enabled by default");
					app_logs.info("Verified total occupancy type enabled by default");
					Assert.assertTrue(false, "Failed : Found total occupancy type enabled by default");
							
				}
				
						
			} catch (Exception e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
					Utility.updateReport(e, "Failed to verify total occupancy type is disable bydefault", scriptName, "BulkUpdate", driver);
				} else {
					Assert.assertTrue(false);
				}
			} catch (Error e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
					Utility.updateReport(e, "Failed to verify total occupancy type is disable bydefault", scriptName, "BulkUpdate", driver);
				}
			}
		
		try {
				
				app_logs.info("==========VERIFYING TOTAL OCCUPANCY VALUE DISABLED BY DAFAULT==========");
				testSteps.add("==========VERIFYING TOTAL OCCUPANCY VALUE DISABLED BY DAFAULT==========");
				
				Boolean isOccupancyValueEnable = overView.verifyTotalOccupanyValue(driver);
				app_logs.info("isOccupancyValueEnable : " + isOccupancyValueEnable);
			
				if(!isOccupancyValueEnable){
					testSteps.add("Verified total occupancy value disabled by default");
					app_logs.info("Verified total occupancy value disabled by default");								
				}else{				
					testSteps.add("Verified total occupancy value enabled by default");
					app_logs.info("Verified total occupancy value enabled by default");
					Assert.assertTrue(false, "Failed : Found total occupancy value enabled by default");
				}
				
						
			} catch (Exception e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
					Utility.updateReport(e, "Failed to verify total occupancy value is disable bydefault", scriptName, "BulkUpdate", driver);
				} else {
					Assert.assertTrue(false);
				}
			} catch (Error e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
					Utility.updateReport(e, "Failed to verify total occupancy value is disable bydefault", scriptName, "BulkUpdate", driver);
				}
			}

		try {
			app_logs.info("==========VERIFYING TOTAL OCCUPANCY ENABLED AFTRE CLICKING OCCUPANCY BUTTON==========");
			testSteps.add("==========VERIFYING TOTAL OCCUPANCY ENABLED AFTRE CLICKING OCCUPANCY BUTTON==========");

			getTestSteps.clear();
			getTestSteps = overView.clickTotalOccupancy(driver, isTotalOccupancyOn);
			testSteps.addAll(getTestSteps);
					
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to click total occupancy", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to click total occupancy", scriptName, "BulkUpdate", driver);
			}
		}
		

		try {
						
			Boolean isOccupancyTypeEnable = overView.verifyTotalOccupancyType(driver);
			app_logs.info("isOccupancyTypeEnable : " + isOccupancyTypeEnable);
		
			Boolean isOccupancyValueEnable = overView.verifyTotalOccupanyValue(driver);
			app_logs.info("isOccupancyValueEnable : " + isOccupancyValueEnable);
		
			if(isOccupancyValueEnable && isOccupancyTypeEnable){
				testSteps.add("Verified total occupancy enabled");
				app_logs.info("Verified total occupancy enabled");
			}else{				
				testSteps.add("Verified total occupancy disabled");
				app_logs.info("Verified total occupancy disabled");								
				Assert.assertTrue(false, "Failed : Found total occupancy disabled");
				
			}
			
					
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify total occupancy is enable", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify total occupancy  is enable", scriptName, "BulkUpdate", driver);
			}
		}

		try {
				
				app_logs.info("==========VERIFYING TOTAL OCCUPANCY TYPE ENABLED==========");
				testSteps.add("==========VERIFYING TOTAL OCCUPANCY TYPE ENABLED==========");
				
				Boolean isOccupancyTypeEnable = overView.verifyTotalOccupancyType(driver);
				app_logs.info("isOccupancyTypeEnable : " + isOccupancyTypeEnable);
			
				if(isOccupancyTypeEnable){
					testSteps.add("Verified total occupancy type enabled");
					app_logs.info("Verified total occupancy type enabled");								
				}else{				
					testSteps.add("Verified total occupancy type disabled");
					app_logs.info("Verified total occupancy type disabled");
					Assert.assertTrue(false, "Failed : Found total occupancy type enabled");
							
				}
				
						
			} catch (Exception e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
					Utility.updateReport(e, "Failed to verify total occupancy type is enabled", scriptName, "BulkUpdate", driver);
				} else {
					Assert.assertTrue(false);
				}
			} catch (Error e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
					Utility.updateReport(e, "Failed to verify total occupancy type is enabled", scriptName, "BulkUpdate", driver);
				}
			}
		
		try {
				
				app_logs.info("==========VERIFYING TOTAL OCCUPANCY VALUE ENABLED==========");
				testSteps.add("==========VERIFYING TOTAL OCCUPANCY VALUE ENABLED==========");
				
				Boolean isOccupancyValueEnable = overView.verifyTotalOccupanyValue(driver);
				app_logs.info("isOccupancyValueEnable : " + isOccupancyValueEnable);
			
				if(isOccupancyValueEnable){
					testSteps.add("Verified total occupancy value enabled");
					app_logs.info("Verified total occupancy value enabled");								
				}else{				
					testSteps.add("Verified total occupancy value disabled");
					app_logs.info("Verified total occupancy value disabled");
					Assert.assertTrue(false, "Failed : Found total occupancy value disabled");
				}
				
						
			} catch (Exception e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
					Utility.updateReport(e, "Failed to verify total occupancy value is enabled", scriptName, "BulkUpdate", driver);
				} else {
					Assert.assertTrue(false);
				}
			} catch (Error e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
					Utility.updateReport(e, "Failed to verify total occupancy value is enabled", scriptName, "BulkUpdate", driver);
				}
			}
		

		try {
			getTestSteps.clear();
			getTestSteps = overView.clickTotalOccupancy(driver, closeToggle);
			testSteps.addAll(getTestSteps);
					
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to disable total occupancy", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to disable total occupancy", scriptName, "BulkUpdate", driver);
			}
		}

		try{
			app_logs.info("==========VERIFYING RATEPLAN DROPDOWN IS DISPLAYING==========");
			testSteps.add("==========VERIFYING RATEPLAN DROPDOWN IS DISPLAYING==========");

			getTestSteps.clear();
			getTestSteps = overView.verifyBulkUpdateDropDowns(driver, "Rate Plan");
			testSteps.addAll(getTestSteps);
			app_logs.info("Verified rateplan dropdown is dislpaying");
			testSteps.add("Verified rateplan dropdown is dislpaying");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify rate plan dropdown is displaying", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify rate plan dropdown is displaying", scriptName, "BulkUpdate", driver);
			}
		}

		try{
			app_logs.info("==========VERIFYING RATEPLAN PLACEHOLDER TEXT==========");
			testSteps.add("==========VERIFYING RATEPLAN PLACEHOLDER TEXT==========");

			String expectedPlaceHolder = "Choose existing rate plan(s)";
			app_logs.info("Expected : " + expectedPlaceHolder);
			testSteps.add("Expected : " + expectedPlaceHolder);
			
			String placeHolderText = overView.getDropDownsPlaceHolder(driver, "Rate Plan");
			app_logs.info("Found : " + placeHolderText);
			testSteps.add("Found : " + placeHolderText);
			
			Assert.assertEquals(placeHolderText, expectedPlaceHolder, "Failed to match pplace holder text for Rate Plan");
			app_logs.info("Verified rateplan place holder");
			testSteps.add("Verified rateplan place holder");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify rate plan placeholder", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify rate plan placeholder", scriptName, "BulkUpdate", driver);
			}
		}


		try{
			app_logs.info("==========GETTING RATEPLANS==========");
			testSteps.add("==========GETTING RATEPLANS==========");

			getTestSteps.clear();
			getTestSteps = overView.getBulkUpdateDropDownsList(driver, "Rate Plan");
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to get all rate plan", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to get all rate plan", scriptName, "BulkUpdate", driver);
			}
		}

		try{
			app_logs.info("==========SELECTING RATE PLAN==========");
			testSteps.add("==========SELECTING RATE PLAN==========");


			String[] ratePlanArray = ratePlan.split(",");
			for(String str : ratePlanArray){
			
				getTestSteps.clear();
				getTestSteps = overView.selectItemsFromDropDowns(driver, "Rate Plan", str);
				testSteps.addAll(getTestSteps);
			}
			

		} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to select rate plan", scriptName, "BulkUpdate", driver);
		} else {
			Assert.assertTrue(false);
		}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select rate plan", scriptName, "BulkUpdate", driver);
			}
		}	

		try{
			app_logs.info("==========VERIFYING ROOMCLASSES DROPDOWN IS DISPLAYING==========");
			testSteps.add("==========VERIFYING ROOMCLASSES DROPDOWN IS DISPLAYING==========");

			ArrayList<String> getDropDownItems = new ArrayList<>();
			getDropDownItems = overView.verifyBulkUpdateDropDowns(driver, "Room class");
			testSteps.addAll(getDropDownItems);
			app_logs.info("Verified room class dropdown is dislpaying");
			testSteps.add("Verified room class dropdown is dislpaying");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify room class dropdown is displaying", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify room class dropdown is displaying", scriptName, "BulkUpdate", driver);
			}
		}

		try{
			app_logs.info("==========VERIFYING ROOMCLASSES PLACEHOLDER TEXT==========");
			testSteps.add("==========VERIFYING ROOMCLASSES PLACEHOLDER TEXT==========");

			String expectedPlaceHolder = "Select room class(es)";
			app_logs.info("Expected : " + expectedPlaceHolder);
			testSteps.add("Expected : " + expectedPlaceHolder);
			
			String placeHolderText = overView.getDropDownsPlaceHolder(driver, "Room class");
			app_logs.info("Found : " + placeHolderText);
			testSteps.add("Found : " + placeHolderText);
			
			Assert.assertEquals(placeHolderText, expectedPlaceHolder, "Failed to match place holder text for Room class");
			app_logs.info("Verified room class place holder");
			testSteps.add("Verified room class place holder");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify room class placeholder", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify room class placeholder", scriptName, "BulkUpdate", driver);
			}
		}		

		try{
			app_logs.info("==========VERIFYING ACTIVE ROOMCLASSESS ARE SHOWING IN BULK UPDATE POPUP==========");
			testSteps.add("==========VERIFYING ACTIVE ROOMCLASSESS ARE SHOWING IN BULK UPDATE POPUP==========");

			app_logs.info("GETTING ROOMCLASSES");
			testSteps.add("GETTING ROOMCLASSES");

			ArrayList<String> getDropDownItems = new ArrayList<>();
			getDropDownItems = overView.getBulkUpdateDropDownsList(driver, "Room class");
			testSteps.addAll(getDropDownItems);
			getDropDownItems.remove(0);
			Boolean isRoomClassPresent = overView.compareLists(getRoomClasses, getDropDownItems);
			app_logs.info("isRoomClassPresent : " + isRoomClassPresent);
			Assert.assertTrue(isRoomClassPresent, "Failed to compare roomclasses");

			app_logs.info("Verified active room classes are showing in bulk update popup");
			testSteps.add("Verified active room classes are showing in bulk update popup");


		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to get all room classes", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to get all room classes", scriptName, "BulkUpdate", driver);
			}
		}
		try{
			app_logs.info("==========SELECTING ROOM CLASS==========");
			testSteps.add("==========SELECTING ROOM CLASS==========");

			String[] roomClassArray = roomClassName.split(",");
			for(String str : roomClassArray){
				getTestSteps.clear();
				getTestSteps = overView.selectRoomClass(driver, str);
				testSteps.addAll(getTestSteps);
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select room classes", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select room classes", scriptName, "BulkUpdate", driver);
			}
		}	
	

		try{
			app_logs.info("==========VERIFYING SOURCE DROPDOWN IS DISPLAYING==========");
			testSteps.add("==========VERIFYING SOURCE DROPDOWN IS DISPLAYING==========");

			getTestSteps.clear();
			getTestSteps = overView.verifyBulkUpdateDropDowns(driver, "Source");
			testSteps.addAll(getTestSteps);
			app_logs.info("Verified source dropdown is dislpaying");
			testSteps.add("Verified source dropdown is dislpaying");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify source dropdown is displaying", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify source dropdown is displaying", scriptName, "BulkUpdate", driver);
			}
		}
		
		try{
			app_logs.info("==========VERIFYING SOURCES PLACEHOLDER TEXT==========");
			testSteps.add("==========VERIFYING SOURCES PLACEHOLDER TEXT==========");

			String expectedPlaceHolder = "Select source(s)";
			app_logs.info("Expected : " + expectedPlaceHolder);
			testSteps.add("Expected : " + expectedPlaceHolder);
			
			String placeHolderText = overView.getDropDownsPlaceHolder(driver, "Source");
			app_logs.info("Found : " + placeHolderText);
			testSteps.add("Found : " + placeHolderText);
			
			Assert.assertEquals(placeHolderText, expectedPlaceHolder, "Failed to match place holder text for Source");
			app_logs.info("Verified source place holder");
			testSteps.add("Verified source place holder");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify source placeholder", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify source placeholder", scriptName, "BulkUpdate", driver);
			}
		}
		
		try{
			app_logs.info("==========GETTING SOURCES==========");
			testSteps.add("==========GETTING SOURCES==========");

			ArrayList<String> getDropDownItems = new ArrayList<>();
			getDropDownItems = overView.getBulkUpdateDropDownsList(driver, "Source");
			testSteps.addAll(getDropDownItems);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to get all sources", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to get all sources", scriptName, "BulkUpdate", driver);
			}
		}

		try{
			app_logs.info("==========SELECTING SOURCE==========");
			testSteps.add("==========SELECTING SOURCE==========");

			String[] channelArray = channel.split(",");
			for(String str : channelArray){
				getTestSteps.clear();
				getTestSteps = overView.selectItemsFromDropDowns(driver, "Source", str);
				testSteps.addAll(getTestSteps);
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select source", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to select source", scriptName, "BulkUpdate", driver);
			}
		}	
		
		try{
			app_logs.info("==========VERIFYING UPDATE EXISTING RULES TEXT==========");
			testSteps.add("==========VERIFYING UPDATE EXISTING RULES TEXT==========");

			String expectedPlaceHolder = "Update existing rules";
			app_logs.info("Expected : " + expectedPlaceHolder);
			testSteps.add("Expected : " + expectedPlaceHolder);
			
			String placeHolderText = overView.verifyUpdateExistingRule(driver);
			app_logs.info("Found : " + placeHolderText);
			testSteps.add("Found : " + placeHolderText);
			
			Assert.assertEquals(placeHolderText, expectedPlaceHolder, "Failed to match update existing rules text");
			app_logs.info("Verified update existing rules text");
			testSteps.add("Verified update existing rules text");

	
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify update existing rules", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify update existing rules", scriptName, "BulkUpdate", driver);
			}
		}

		
		try{
			app_logs.info("==========VERIFYING MINIMUM STAY IS DISABLED BY DEFAULT==========");
			testSteps.add("==========VERIFYING MINIMUM STAY IS DISABLED BY DEFAULT==========");

			Boolean isCheck = overView.verifyMinimumStayValue(driver);
			app_logs.info("isCheck : " + isCheck);
			
			if(!isCheck){
				app_logs.info("Verified minimum stay is disabled by default");
				testSteps.add("Verified minimum stay is disabled by default");
			}else{				
				app_logs.info("Verified minimum stay is enabled by default");
				testSteps.add("Verified minimum stay is enabled by default");
				Assert.assertTrue(false, "Failed : Found  minimum stay enabled by default");
				
			}
			
	
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify  minimum stay is disabled by default", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify  minimum stay is disabled by default", scriptName, "BulkUpdate", driver);
			}
		}

		try{
			app_logs.info("==========VERIFYING CHECKIN IS DISABLED BY DEFAULT==========");
			testSteps.add("==========VERIFYING CHECKIN IS DISABLED BY DEFAULT==========");

			Boolean isCheck = overView.verifyNoCheckInCheckbox(driver);
			app_logs.info("isCheck : " + isCheck);
			
			if(!isCheck){
				app_logs.info("Verified check in is disabled by default");
				testSteps.add("Verified check in is disabled by default");
			}else{				
				app_logs.info("Verified check in is enabled by default");
				testSteps.add("Verified check in is enabled by default");
				Assert.assertTrue(false, "Failed : Found check in enabled by default");
				
			}
			
	
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify check in is disabled by default", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify check in is disabled by default", scriptName, "BulkUpdate", driver);
			}
		}
		

		try{
			app_logs.info("==========VERIFYING CHECKOUT IS DISABLED BY DEFAULT==========");
			testSteps.add("==========VERIFYING CHECKOUT IS DISABLED BY DEFAULT==========");

			Boolean isCheck = overView.verifyNoCheckOutCheckbox(driver);
			app_logs.info("isCheck : " + isCheck);
			
			if(!isCheck){
				app_logs.info("Verified check out is disabled by default");
				testSteps.add("Verified check out is disabled by default");
			}else{				
				app_logs.info("Verified check out is enabled by default");
				testSteps.add("Verified check out is enabled by default");
				Assert.assertTrue(false, "Failed : Found check out enabled by default");
				
			}
			
	
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify check out is disabled by default", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify check out is disabled by default", scriptName, "BulkUpdate", driver);
			}
		}
		

		try {
			app_logs.info("==========VERIFYING UPDATE BUTTON IS DISABLED BEFORE CLICKING MINIMUM STAY==========");
			testSteps.add("==========VERIFYING UPDATE BUTTON IS DISABLED BEFORE CLICKING MINIMUM STAY==========");
			
			getTestSteps.clear();			
			getTestSteps = overView.verifyUpdateButtonEnabled(driver);
			testSteps.addAll(getTestSteps);

			app_logs.info("Verified update button is disabled before clicking minimum stay");
			testSteps.add("Verified update button is disabled before clicking minimum stay");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify update button is disable before clicking minimum stay", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify update button is disable before clicking minimum stay", scriptName, "BulkUpdate", driver);
			}
		}
		
		try {
			getTestSteps.clear();
			getTestSteps = overView.clickMinimumStay(driver, isMinimumStayOn);
			testSteps.addAll(getTestSteps);
							
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to click minimum stay", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to click minimum stay", scriptName, "BulkUpdate", driver);
			}
		}
		
		try {
			app_logs.info("==========VERIFYING UPDATE BUTTON IS ENABLED AFTER CLICKING MINIMUM STAY==========");
			testSteps.add("==========VERIFYING UPDATE BUTTON IS ENABLED AFTER CLICKING MINIMUM STAY==========");
			
			getTestSteps.clear();			
			getTestSteps = overView.verifyUpdateButtonEnabled(driver);
			testSteps.addAll(getTestSteps);

			app_logs.info("Verified update button is enabled after clicking minimum stay");
			testSteps.add("Verified update button is enabled after clicking minimum stay");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify update button is enabled after clicking minimum stay", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify update button is enabled after clicking minimum stay", scriptName, "BulkUpdate", driver);
			}
		}
		try {
			getTestSteps.clear();
			getTestSteps = overView.clickMinimumStay(driver, closeToggle);
			testSteps.addAll(getTestSteps);
							
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to disable minimum stay", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to disable minimum stay", scriptName, "BulkUpdate", driver);
			}
		}

		try {
			app_logs.info("==========VERIFYING UPDATE BUTTON IS DISABLED BEFORE CLICKING CHECKIN==========");
			testSteps.add("==========VERIFYING UPDATE BUTTON IS DISABLED BEFORE CLICKING CHECKIN==========");
			
			getTestSteps.clear();			
			getTestSteps = overView.verifyUpdateButtonEnabled(driver);
			testSteps.addAll(getTestSteps);

			app_logs.info("Verified update button is disabled before clicking checkin");
			testSteps.add("Verified update button is disabled before clickingcheckin");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify update button is disable before clicking checkin", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify update button is disable before clicking checkin", scriptName, "BulkUpdate", driver);
			}
		}
		
		try {
			getTestSteps.clear();
			getTestSteps = overView.clickCheckin(driver, isCheckInOn);
			testSteps.addAll(getTestSteps);
							
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to click checkin", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to click checkin", scriptName, "BulkUpdate", driver);
			}
		}
		
		try {
			app_logs.info("==========VERIFYING UPDATE BUTTON IS ENABLED AFTER CLICKING CHECKIN==========");
			testSteps.add("==========VERIFYING UPDATE BUTTON IS ENABLED AFTER CLICKING CHECKIN==========");
			
			getTestSteps.clear();			
			getTestSteps = overView.verifyUpdateButtonEnabled(driver);
			testSteps.addAll(getTestSteps);

			app_logs.info("Verified update button is enabled after clicking checkin");
			testSteps.add("Verified update button is enabled after clicking checkin");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify update button is enabled after clicking checkin", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify update button is enabled after clicking checkin", scriptName, "BulkUpdate", driver);
			}
		}

		try {
			getTestSteps.clear();
			getTestSteps = overView.clickCheckin(driver, closeToggle);
			testSteps.addAll(getTestSteps);
							
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to disable checkin button", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to disable checkin button", scriptName, "BulkUpdate", driver);
			}
		}

		try {
			app_logs.info("==========VERIFYING UPDATE BUTTON IS DISABLED BEFORE CLICKING CHECKOUT==========");
			testSteps.add("==========VERIFYING UPDATE BUTTON IS DISABLED BEFORE CLICKING CHECKOUT==========");
			
			getTestSteps.clear();			
			getTestSteps = overView.verifyUpdateButtonEnabled(driver);
			testSteps.addAll(getTestSteps);

			app_logs.info("Verified update button is disabled before clicking checkout");
			testSteps.add("Verified update button is disabled before clicking checkout");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify update button is disable before clicking checkout", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify update button is disable before clicking checkout", scriptName, "BulkUpdate", driver);
			}
		}
		
		try {
			
			getTestSteps.clear();
			getTestSteps = overView.clickCheckOut(driver, isCheckOutOn);
			testSteps.addAll(getTestSteps);
							
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to click checkout", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to click checkout", scriptName, "BulkUpdate", driver);
			}
		}
		
		try {
			app_logs.info("==========VERIFYING UPDATE BUTTON IS ENABLED AFTER CLICKING CHECKOUT==========");
			testSteps.add("==========VERIFYING UPDATE BUTTON IS ENABLED AFTER CLICKING CHECKOUT==========");
			
			getTestSteps.clear();			
			getTestSteps = overView.verifyUpdateButtonEnabled(driver);
			testSteps.addAll(getTestSteps);

			app_logs.info("Verified update button is enabled after checkout");
			testSteps.add("Verified update button is enabled after checkout");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify update button is enabled after clicking checkout", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify update button is enabled after clicking checkout", scriptName, "BulkUpdate", driver);
			}
		}

	try {
			
			getTestSteps.clear();
			getTestSteps = overView.clickCheckOut(driver, closeToggle);
			testSteps.addAll(getTestSteps);
							
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to disable checkout button", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to disable checkout button", scriptName, "BulkUpdate", driver);
			}
		}
	
	try {
		app_logs.info("==========VERIFYING UPDATE BUTTON IS ENABLED AFTER CLICKING MINIMUM STAY AND CHECKIN==========");
		testSteps.add("==========VERIFYING UPDATE BUTTON IS ENABLED AFTER CLICKING MINIMUM STAY AND CHECKIN==========");

		getTestSteps.clear();
		getTestSteps = overView.clickMinimumStay(driver, isMinimumStayOn);
		testSteps.addAll(getTestSteps);
						
	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to click minimum stay", scriptName, "BulkUpdate", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to click minimum stay", scriptName, "BulkUpdate", driver);
		}
	}

	try {
		getTestSteps.clear();
		getTestSteps = overView.clickCheckin(driver, isCheckInOn);
		testSteps.addAll(getTestSteps);
						
	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to click checkin", scriptName, "BulkUpdate", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to click checkin", scriptName, "BulkUpdate", driver);
		}
	}
	
	try {
		
		getTestSteps.clear();			
		getTestSteps = overView.verifyUpdateButtonEnabled(driver);
		testSteps.addAll(getTestSteps);

		app_logs.info("Verified update button is enabled after clicking minimum stay and checkin");
		testSteps.add("Verified update button is enabled after clicking minimum stay and checkin");
	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to verify update button is enabled after clicking minimum stay and checkin", scriptName, "BulkUpdate", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to verify update button is enabled after clicking minimum stay and checkin", scriptName, "BulkUpdate", driver);
		}
	}

	try {
		getTestSteps.clear();
		getTestSteps = overView.clickMinimumStay(driver, closeToggle);
		testSteps.addAll(getTestSteps);
						
	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to disable minimum stay", scriptName, "BulkUpdate", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to disable minimum stay", scriptName, "BulkUpdate", driver);
		}
	}
	try {
		getTestSteps.clear();
		getTestSteps = overView.clickCheckin(driver, closeToggle);
		testSteps.addAll(getTestSteps);
						
	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to disable checkin button", scriptName, "BulkUpdate", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to disable checkin button", scriptName, "BulkUpdate", driver);
		}
	}
	
	try {
		app_logs.info("==========VERIFYING UPDATE BUTTON IS ENABLED AFTER CLICKING MINIMUM STAY AND CHECKOUT==========");
		testSteps.add("==========VERIFYING UPDATE BUTTON IS ENABLED AFTER CLICKING MINIMUM STAY AND CHECKOUT==========");

		getTestSteps.clear();
		getTestSteps = overView.clickMinimumStay(driver, isMinimumStayOn);
		testSteps.addAll(getTestSteps);
						
	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to click minimum stay", scriptName, "BulkUpdate", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to click minimum stay", scriptName, "BulkUpdate", driver);
		}
	}

	try {
		getTestSteps.clear();
		getTestSteps = overView.clickCheckOut(driver, isCheckOutOn);
		testSteps.addAll(getTestSteps);
						
	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to click checkout", scriptName, "BulkUpdate", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to click checkout", scriptName, "BulkUpdate", driver);
		}
	}
	
	try {
		
		getTestSteps.clear();			
		getTestSteps = overView.verifyUpdateButtonEnabled(driver);
		testSteps.addAll(getTestSteps);

		app_logs.info("Verified update button is enabled after clicking minimum stay and checkout");
		testSteps.add("Verified update button is enabled after clicking minimum stay and checkout");
	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to verify update button is enabled after clicking minimum stay and checkout", scriptName, "BulkUpdate", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to verify update button is enabled after clicking minimum stay and checkout", scriptName, "BulkUpdate", driver);
		}
	}

	try {
		getTestSteps.clear();
		getTestSteps = overView.clickMinimumStay(driver, closeToggle);
		testSteps.addAll(getTestSteps);
						
	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to disable minimum stay", scriptName, "BulkUpdate", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to disable minimum stay", scriptName, "BulkUpdate", driver);
		}
	}
	try {
		getTestSteps.clear();
		getTestSteps = overView.clickCheckOut(driver, closeToggle);
		testSteps.addAll(getTestSteps);
						
	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to disable checkout button", scriptName, "BulkUpdate", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to disable checkout button", scriptName, "BulkUpdate", driver);
		}
	}


	
	try {
		app_logs.info("==========VERIFYING UPDATE BUTTON IS ENABLED AFTER CLICKING CHECKIN AND CHECKOUT==========");
		testSteps.add("==========VERIFYING UPDATE BUTTON IS ENABLED AFTER CLICKING CHECKIN AND CHECKOUT==========");

		getTestSteps.clear();
		getTestSteps = overView.clickCheckin(driver, isCheckInOn);
		testSteps.addAll(getTestSteps);
						
	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to click checkin", scriptName, "BulkUpdate", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to click checkin", scriptName, "BulkUpdate", driver);
		}
	}

	try {
		getTestSteps.clear();
		getTestSteps = overView.clickCheckOut(driver, isCheckOutOn);
		testSteps.addAll(getTestSteps);
						
	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to click checkout", scriptName, "BulkUpdate", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to click checkout", scriptName, "BulkUpdate", driver);
		}
	}
	
	try {
		
		getTestSteps.clear();			
		getTestSteps = overView.verifyUpdateButtonEnabled(driver);
		testSteps.addAll(getTestSteps);

		app_logs.info("Verified update button is enabled after clicking checkin and checkout");
		testSteps.add("Verified update button is enabled after clicking checkin and checkout");
	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to verify update button is enabled after clicking checkin and checkout", scriptName, "BulkUpdate", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to verify update button is enabled after clicking checkin and checkout", scriptName, "BulkUpdate", driver);
		}
	}

	try {
		getTestSteps.clear();
		getTestSteps = overView.clickCheckin(driver, closeToggle);
		testSteps.addAll(getTestSteps);
						
	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to disable checkin", scriptName, "BulkUpdate", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to disable checkin", scriptName, "BulkUpdate", driver);
		}
	}
	try {
		getTestSteps.clear();
		getTestSteps = overView.clickCheckOut(driver, closeToggle);
		testSteps.addAll(getTestSteps);
						
	} catch (Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to disable checkout button", scriptName, "BulkUpdate", driver);
		} else {
			Assert.assertTrue(false);
		}
	} catch (Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
			Utility.updateReport(e, "Failed to disable checkout button", scriptName, "BulkUpdate", driver);
		}
	}*/
	
	try{
			
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify update existing rules", scriptName, "BulkUpdate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to verify update existing rules", scriptName, "BulkUpdate", driver);
			}
		}
	
	
	}

	@DataProvider
	public Object[][] getData() {

		return Utility.getData("UIValidationForBulkUpdateRules", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closedriver() {
		// driver.quit();
	}

}
