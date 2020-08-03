package com.innroad.inncenter.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Folio;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Tapechart;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyManualOverrideRateInNewReservationTapeChart extends TestCore{
	private WebDriver driver = null;
	public static String test_name = "";
	public static String test_description = "";
	public static String test_catagory = "";
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> getTest_Steps = new ArrayList<>();

	@BeforeTest(alwaysRun=true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void verifyManualOverrideRateInNewReservationTapeChart(String Adults,String Children,String Rateplan,String PromoCode,
			String ManualRate, String Category, String PostedState, String ItemRow, String SpanTag,
			String RoomClass,String IsAssign,String IsDepositOverride,String DepositOverrideAmount,String Salutation,String GuestFirstName,String GuestLastName,String PhoneNumber,String AltenativePhone,String Email,String Account,String AccountType,
			String Address1,String Address2,String Address3,String City,String Country,String State,String PostalCode,String IsGuesProfileCreate,
			
			String Referral) throws InterruptedException, IOException {

		test_name = "VerifyManualOverrideRateInNewReservationTapeChart";
		test_description = "CP Reservation Creation wiht Manual Rate Override.<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682418' target='_blank'>"
				+ "Click here to open TestRail: C682418</a>";
		test_catagory = "CPReservation";
		String testName = test_name;

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		CPReservationPage res = new CPReservationPage();
		Navigation nav = new Navigation();
		Tapechart tapechart = new Tapechart();
		
		Folio folio =new Folio();
		String RandomNumber = Utility.GenerateRandomNumber();
		GuestLastName = GuestLastName + RandomNumber;
		Double depositAmount=0.0;
		Double paidDeposit=0.0;
		String reservation=null;
		String status=null;
		String FilioRoomCharges=null,FilioTaxes=null,FilioIncidentals=null,FilioTripTotal=null,
				FilioPaid=null,FilioBalance=null;
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

		// Reservation
		try {
			test_steps.add("==========CREATING NEW RESERVATION==========");
			app_logs.info("==========CREATING NEW RESERVATION==========");
			
			res.click_NewReservation(driver, test_steps);

			getTest_Steps.clear();
			getTest_Steps = res.checkInDate(driver, 0);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = res.checkOutDate(driver, +1);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();			
			res.enter_Adults(driver, getTest_Steps, Adults);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();			
			res.enter_Children(driver, getTest_Steps, Children);
			test_steps.addAll(getTest_Steps);
			
			
			getTest_Steps.clear();			
			getTest_Steps = res.select_Rateplan(driver, getTest_Steps, Rateplan,PromoCode);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = res.EnterManualRateAmount(driver ,ManualRate);
			test_steps.addAll(getTest_Steps);
			
			
			getTest_Steps.clear();			
			getTest_Steps = res.clickOnFindRooms(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();			
			getTest_Steps = res.selectRoom(driver, getTest_Steps, RoomClass, IsAssign,Account);
			test_steps.addAll(getTest_Steps);
			
			test_steps.add("==========Verifying that room class(" + RoomClass + ") rate override with manual rate==========");
			app_logs.info("==========Verifying that room class(" + RoomClass + ") rate override with manual rate==========");
			
			getTest_Steps.clear();			
			getTest_Steps = res.VerifyRoomClassManualRateAmount(driver, RoomClass, ManualRate);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();			
			depositAmount=res.deposit(driver, getTest_Steps, IsDepositOverride, DepositOverrideAmount);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();			
			res.clickNext(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
	
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click Next button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click Next button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Guest profile
		try {
	
			getTest_Steps.clear();
			getTest_Steps = res.VerifyGuestProfileCheckox(driver, Boolean.parseBoolean(IsGuesProfileCreate));
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			res.enter_GuestName(driver, getTest_Steps, Salutation, GuestFirstName, GuestLastName);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = res.selectReferral(driver, Referral);
			test_steps.addAll(getTest_Steps);

			
			getTest_Steps.clear();
			res.clickBookNow(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			reservation=res.get_ReservationConfirmationNumber(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			status=res.get_ReservationStatus(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			
			getTest_Steps.clear();
			res.clickCloseReservationSavePopup(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to fill complete guest profile info", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to fill complete guest profile info", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			test_steps.add("==========VERIFICATION MANUAL ORVERRIDE RATE PLAN IN FOLIO LINE ITEM==========");
			app_logs.info("==========VERIFICATION MANUAL ORVERRIDE RATE PLAN IN FOLIO LINE ITEM==========");
			getTest_Steps.clear();
			getTest_Steps = folio.folioTab(driver);
			test_steps.addAll(getTest_Steps);
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click on folio tab", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click on folio tab", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			folio.VerifyLineItems_State(driver, Category, PostedState);
			test_steps.add("Verify line itme in pending state after added");
			
			getTest_Steps.clear();
			getTest_Steps = folio.LineItemCategory(driver, Category);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.getDescroption(driver, Category, Rateplan, false);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.LineItemQuentity(driver, Category, "1");
			test_steps.addAll(getTest_Steps);

			String getAmount = folio.getAmount(driver, Category);
			String getTax = folio.getTax(driver, Category);
			String getExpectedAmount = folio.AddValue(ManualRate, getTax);

			getExpectedAmount = "$ " + getExpectedAmount;
			test_steps.add("Expected amount after added tax: " + getExpectedAmount);
			test_steps.add("Found : " + getAmount);
			assertEquals(getAmount, getExpectedAmount, "Failed: Amount is  mismatching!");
			test_steps.add("Verified line item amount after selecting rate plan as " + Rateplan);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to find incidental in line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to find incidental in line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			test_steps.add("==========VERIFICATION MANUAL ORVERRIDE RATE IN ITEM DETAILS POPUPs==========");
			getTest_Steps.clear();
			getTest_Steps = folio.getDescroption(driver, Category, Rateplan, true);
			test_steps.addAll(getTest_Steps);
			

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify MANUAL ORVERRIDE RATE IN ITEM DETAILS POPUPs", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify MANUAL ORVERRIDE RATE IN ITEM DETAILS POPUPs", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Start verify item and tax row
		try {
			folio.ItemDetails_Category_State(driver, Category, PostedState, 1);
			test_steps.add("Verify line itme in pending state");

			getTest_Steps.clear();
			getTest_Steps = folio.DateItemDetails(driver, Category, 1, ItemRow);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.ItemDetailsCategory(driver, Category, 1, ItemRow);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.itemDetails_Descroption(driver, Category, Rateplan, false, 1, ItemRow, SpanTag);
			test_steps.addAll(getTest_Steps);

			String getAmount = folio.getAmount_ItemDetails(driver, Category, 1);
			test_steps.add("Expected amount: $ " + ManualRate + ".00");
			test_steps.add("Found: " + getAmount);
			assertEquals(getAmount, "$ " + ManualRate + ".00", "Failed: manual override rate value is mismatching in item row");
			test_steps.add("Verified item amount");

			test_steps.add("==========VERIFICATION OF ROOM CHARGES==========");

			String itemDetails_RoomChares = folio.Itemdetails_RoomChares(driver);
			test_steps.add("Expected room chares: $ " + ManualRate + ".00");			
			test_steps.add("Found: " + itemDetails_RoomChares);
			assertEquals(itemDetails_RoomChares, "$ " + ManualRate + ".00",
					"Failed: room charges are mismatching in item details popup");
			test_steps.add("Verified room charges in item detail popup");

			getTest_Steps.clear();
			getTest_Steps = folio.CancelPopupButton(driver, true, false);
			test_steps.addAll(getTest_Steps);

		
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify item details popup", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify item details popup", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// check this section
		try {
			app_logs.info("==========Verify Manual Override Rate in Total Section==========");
			test_steps.add("==========Verify Manual Override Rate in Total Section==========");
			
			String manualRate = "$ " + ManualRate + ".00";
			app_logs.info("Expected Room Charges : " + manualRate);
			test_steps.add("Expected Room Charges : " + manualRate);
			
			FilioRoomCharges=res.get_RoomChargeWithCurrency(driver, getTest_Steps);
			getTest_Steps.clear();
			app_logs.info("Found : " + FilioRoomCharges);
			test_steps.add("Found : " + FilioRoomCharges);			
			Assert.assertEquals(manualRate, FilioRoomCharges,"Failed: RoomCharges didn't matches");
			app_logs.info("Verified room charges");
			test_steps.add("Verified room charges");						
			
			getTest_Steps.clear();
			FilioIncidentals=res.get_InceidentalsWithCurrency(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			FilioTaxes=res.get_TaxesWithCurrency(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			FilioTripTotal= res.get_TotalChargesWithCurrency(driver, getTest_Steps);
			
			getTest_Steps.clear();
			Double totalCharges  = res.verify_TotalCharges(driver, test_steps, FilioRoomCharges, FilioTaxes, FilioTripTotal);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();			
			FilioPaid=res.get_PaymentsWithCurrency(driver, getTest_Steps);
			
			getTest_Steps.clear();			
			FilioBalance=res.get_BalanceWithCurrency(driver, getTest_Steps);
			
			getTest_Steps.clear();			
			res.VerifyBalance(driver, totalCharges, FilioPaid, FilioBalance);
			test_steps.addAll(getTest_Steps);
			
						
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify info in total section", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify info in total section", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			
			res.closeReservationTab(driver);
			test_steps.add("Closed reservation tab");

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to close opened reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to close opened reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		
		try {

			test_steps.add(
					"==========SEARCH MANULA OVERRIED RATE PLAN IN TAPE CHART AND CLICK ON AVAILABLE ROOM==========");
			app_logs.info(
					"==========SEARCH MANULA OVERRIED RATE PLAN IN TAPE CHART AND CLICK ON AVAILABLE ROOM==========");

			nav.navTapeChart(driver, test);
			test_steps.add("Navigate TapeChart");
			app_logs.info("Navigate TapeChart");

			tapechart.CheckInCheckOutDate(driver);
			test_steps.add("Select Today's Data");

			tapechart.EnterAdult(driver, Adults);
			test_steps.add("Enter Adult: " + Adults);
			app_logs.info("Enter Adult: " + Adults);

			tapechart.SelectRatePlan(driver, Rateplan);
			test_steps.add("Selected rate plan: " + Rateplan);
			app_logs.info("Selected rate plan: " + Rateplan);

			tapechart.EnterRateAmount(driver, ManualRate);
			test_steps.add("Enter amount: " + ManualRate);
			app_logs.info("Enter amount: " + ManualRate);

			tapechart.ClickOnSearch(driver);
			test_steps.add("Click on search ");
			app_logs.info("Click on search ");

			tapechart.ClickOnAvailableRoom(driver);
			test_steps.add("Click on available room");
			app_logs.info("Click on available room");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to searcha and click on available room from tapechart", testName,
						"Tapechart", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to searcha and click on available room from tapechart", testName,
						"Tapechart", driver);
			} else {
				Assert.assertTrue(false);
			}
		}


		// Reservation
		try {
			getTest_Steps.clear();
			getTest_Steps = res.VerifyGuestProfileCheckox(driver, Boolean.parseBoolean(IsGuesProfileCreate));
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			res.enter_GuestName(driver, getTest_Steps, Salutation, GuestFirstName, GuestLastName);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = res.selectReferral(driver, Referral);
			test_steps.addAll(getTest_Steps);


			getTest_Steps.clear();
			res.clickBookNow(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			reservation=res.get_ReservationConfirmationNumber(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			status=res.get_ReservationStatus(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			res.clickCloseReservationSavePopup(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			/*res.closeReservationTab(driver);
			test_steps.add("Closed reservation tab");
			*/
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click Close Reservation Save Popup", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click Close Reservation Save Popup", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			test_steps.add("==========VERIFICATION MANUAL ORVERRIDE RATE PLAN IN FOLIO LINE ITEM==========");
			app_logs.info("==========VERIFICATION MANUAL ORVERRIDE RATE PLAN IN FOLIO LINE ITEM==========");

			getTest_Steps.clear();
			getTest_Steps = folio.folioTab(driver);
			test_steps.addAll(getTest_Steps);
			
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click on folio tab", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to click on folio tab", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			folio.VerifyLineItems_State(driver, Category, PostedState);
			test_steps.add("Verify line itme in pending state after added");
			
			getTest_Steps.clear();
			getTest_Steps = folio.LineItemCategory(driver, Category);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.getDescroption(driver, Category, Rateplan, false);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.LineItemQuentity(driver, Category, "1");
			test_steps.addAll(getTest_Steps);

			String getAmount = folio.getAmount(driver, Category);
			String getTax = folio.getTax(driver, Category);
			String getExpectedAmount = folio.AddValue(ManualRate, getTax);

			getExpectedAmount = "$ " + getExpectedAmount;
			test_steps.add("Expected amount after added tax: " + getExpectedAmount);
			test_steps.add("Found : " + getAmount);
			assertEquals(getAmount, getExpectedAmount, "Failed: Amount is  mismatching!");
			test_steps.add("Verified line item amount after selecting rate plan as " + Rateplan);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to find incidental in line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to find incidental in line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			test_steps.add("==========VERIFICATION MANUAL ORVERRIDE RATE IN ITEM DETAILS POPUPs==========");
			
			getTest_Steps.clear();
			getTest_Steps = folio.getDescroption(driver, Category, Rateplan, true);
			test_steps.addAll(getTest_Steps);
			

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify MANUAL ORVERRIDE RATE IN ITEM DETAILS POPUPs", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Verify MANUAL ORVERRIDE RATE IN ITEM DETAILS POPUPs", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Start verify item and tax row
		try {
			folio.ItemDetails_Category_State(driver, Category, PostedState, 1);
			test_steps.add("Verify line itme in pending state");

			getTest_Steps.clear();
			getTest_Steps = folio.DateItemDetails(driver, Category, 1, ItemRow);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.ItemDetailsCategory(driver, Category, 1, ItemRow);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.itemDetails_Descroption(driver, Category, Rateplan, false, 1, ItemRow, SpanTag);
			test_steps.addAll(getTest_Steps);

			String getAmount = folio.getAmount_ItemDetails(driver, Category, 1);
			test_steps.add("Expected amount: $ " + ManualRate + ".00");
			test_steps.add("Found: " + getAmount);
			assertEquals(getAmount, "$ " + ManualRate + ".00", "Failed: manual override rate value is mismatching in item row");
			test_steps.add("Verified item amount");

			test_steps.add("==========VERIFICATION OF ROOM CHARGES==========");

			String itemDetails_RoomChares = folio.Itemdetails_RoomChares(driver);
			test_steps.add("Expected room chares: $ " + ManualRate + ".00");			
			test_steps.add("Found: " + itemDetails_RoomChares);
			assertEquals(itemDetails_RoomChares, "$ " + ManualRate + ".00",
					"Failed: room charges are mismatching in item details popup");
			test_steps.add("Verified room charges in item detail popup");

			getTest_Steps.clear();
			getTest_Steps = folio.CancelPopupButton(driver, true, false);
			test_steps.addAll(getTest_Steps);

		
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify item details popup", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify item details popup", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			app_logs.info("==========Verify Manual Override Rate in Total Section==========");
			test_steps.add("==========Verify Manual Override Rate in Total Section==========");
			
			String manualRate = "$ " + ManualRate + ".00";
			app_logs.info("Expected Room Charges : " + manualRate);
			test_steps.add("Expected Room Charges : " + manualRate);
			
			FilioRoomCharges=res.get_RoomChargeWithCurrency(driver, getTest_Steps);
			getTest_Steps.clear();			
			app_logs.info("Found : " + FilioRoomCharges);
			test_steps.add("Found : " + FilioRoomCharges);			
			
			Assert.assertEquals(manualRate, FilioRoomCharges,"Failed: RoomCharges didn't matches");
			app_logs.info("Verified room charges");
			test_steps.add("Verified room charges");						
			
			
			getTest_Steps.clear();
			FilioIncidentals=res.get_InceidentalsWithCurrency(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
		
			getTest_Steps.clear();
			FilioTaxes=res.get_TaxesWithCurrency(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			FilioTripTotal= res.get_TotalChargesWithCurrency(driver, getTest_Steps);
			getTest_Steps.clear();
			
			getTest_Steps.clear();
			Double totalCharges  = res.verify_TotalCharges(driver, getTest_Steps, FilioRoomCharges, FilioTaxes, FilioTripTotal);
			test_steps.addAll(getTest_Steps);
			
			FilioPaid=res.get_PaymentsWithCurrency(driver, getTest_Steps);
			getTest_Steps.clear();
			
			FilioBalance=res.get_BalanceWithCurrency(driver, getTest_Steps);
			getTest_Steps.clear();
			
			res.VerifyBalance(driver, totalCharges, FilioPaid, FilioBalance);
			getTest_Steps.clear();
			
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
			
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify info in total section", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify info in total section", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getData("VerifyManualOverrideRateInRese", excel);
	}

	@AfterClass(alwaysRun=true)
	public void closeDriver() {
	//	driver.quit();
	}

}
