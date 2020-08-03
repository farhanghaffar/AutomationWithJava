/**
 * 
 */
package com.innroad.inncenter.tests;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.Tax;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

/**
 * @author tejpratap.pandey
 *
 */
public class VerifyTaxesInCPReservation extends TestCore {
	private WebDriver driver;

	public static String test_description = "";
	public static String test_category = "";
	public static String test_name = "";
	public static ArrayList<String> test_steps = new ArrayList<>();
	public static ArrayList<String> getTest_Steps = new ArrayList<>();

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void verifyCpReservationWithTaxes(String url, String ClientCode, String Username, String Password,
			String CheckInDate, String CheckOutDate, String Adults, String Children, String Rateplan, String PromoCode,
			String RoomClass, String IsAssign, String IsDepositOverride, String DepositOverrideAmount,
			String Salutation, String GuestFirstName, String GuestLastName, String PhoneNumber, String AltenativePhone,
			String Email, String Account, String AccountType, String Address1, String Address2, String Address3,
			String City, String Country, String State, String PostalCode, String IsGuesProfile, String PaymentType,
			String CardNumber, String NameOnCard, String CardExpDate, String IsChangeInPayAmount,
			String ChangedAmountValue, String TravelAgent, String MarketSegment, String Referral, String IsAddNotes,
			String NoteType, String Subject, String Description, String IsTask, String TaskCategory, String TaskType,
			String TaskDetails, String TaskRemarks, String TaskDueon, String TaskAssignee, String TaskStatus,
			String TaxName, String taxLedgerAccount, String displayName, String description, String value,
			String category, String IsPercent, String bedsCount, String maxAdults, String maxPersopns, String roomQuantity,
            String ratename, String baseAmount, String additionalAdult, String additionalChild) {
		test_name = "VerifyTaxesInCPReservation";
		test_description = "<br>Verify taxes In CPReservation<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682497' target='_blank'>"
				+ "Click here to open TestRail: 682497</a>";
		test_category = "Verify_taxesIn_CPReservation";
		String testName = test_name;

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");
		CPReservationPage res = new CPReservationPage();
		String randomString = "";
		Tax tax = new Tax();
		Navigation nav = new Navigation();
		RoomClass roomclass = new RoomClass();
		Rate rate = new Rate();
		Double depositAmount = 0.0;
		String reservation = null;
		boolean excludeTaxExempt = true;
		boolean vat = false;
		double tripsummarytaxesafterressave = 0.0;
		String roomClassName = "";
		String totalRoomChargesBeforeResSave = null;
		String totalTripChargesbeforesave = null;
		String totalTaxChargesbeforesave = null;
		String createdpercent = "";
		String rateName = "";
		String tName = "";
		String tripSummaryTaxesWithCurrency = "";
		String roomCharge = "";
		String TripTotalCharge = "";
		String RcDelete = "";
		String rateDelete = "";
		DecimalFormat f = new DecimalFormat("##.00");
		
	
		try {
			test_steps.add("<b> ************Logging in to the Application</b>****************");
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
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
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
			test_steps.add("<b> ************Create Room Class</b>****************");
			//create room class
			nav.Setup(driver);
			test_steps.add("Navigate to Setup");
			nav.RoomClass(driver);
			test_steps.add("Navigate to RoomClasses tab");
			String RcNaming = RoomClass;
			RcDelete = RcNaming.replace("XXX", "");
			randomString = Utility.GenerateRandomNumber();
		    nav.NewRoomClass(driver);
			roomClassName = RoomClass.replace("XXX", randomString);
			roomclass.Create_RoomClass(driver, roomClassName, roomClassName, bedsCount, maxAdults, maxPersopns, roomQuantity, test, test_steps);
		
	}catch(Exception e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
			Utility.updateReport(e, "Failed to Create room class", "NONGS_Login", "RoomClass", driver);
		} else {
			Assert.assertTrue(false);
		}
	}catch(Error e) {
		if (Utility.reTry.get(testName) == Utility.count) {
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
			Utility.updateReport(e, "Failed to Create room class", "NONGS_Login", "RoomClass", driver);
		} else {
			Assert.assertTrue(false);
		}
	}
		try {
			test_steps.add("<b> ************Create Rate/b>****************");
			//ceate rate 
			String rateNaming = ratename;
			rateDelete = rateNaming.replace("XXX", "");
			randomString =Utility.GenerateRandomNumber();
		    rateName = ratename.replace("XXX", randomString);
			nav.Inventory(driver);
			test_steps.add("Navigate to Inventory");
			nav.Rate(driver);
			test_steps.add("Navigate to Rate");
			
			rate.new_Rate(driver, rateName, maxAdults, maxPersopns, baseAmount, additionalAdult, additionalChild, rateName, rateName, rateName, roomClassName);
			test_steps.add("Rate name: "+ "<b>"+rateName+"</b>");
			test_steps.add("Room class name selected in the rate: "+ "<b>"+roomClassName+"</b>");
			test_steps.add("Rate created");
		}catch(Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to create Rate", "NONGS_Login", "rate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}catch(Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to create Rate", "NONGS_Login", "rate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			// Create Tax
			test_steps.add("<b>************** Making Tax Setup*******************</b>");
			nav.Setup(driver);
			test_steps.add("Navigate to setup");
			nav.Taxes(driver);
			test_steps.add("Navigate to taxes page");
				// create new tax------------------
				test_steps.add("<b>********** Creating New percent Taxe****************</b>");
				randomString = Utility.GenerateRandomNumber();
				tName = TaxName.replace("XXX", randomString);
				tax.Click_NewItem(driver, test_steps);
				test_steps.add("Click at new tax item button");
				test_steps.add("<br>Create new taxes</br>");
						boolean percentage = true;
						test_steps.add("percent Tax name is: " + "<b>" + tName + " </b>");
						tax.createTax(driver, test, tName.trim(), tName.trim(), tName.trim(), value, category, taxLedgerAccount,
								excludeTaxExempt, percentage, vat);
						test_steps.add("Created tax with percent amount: " + "<b>" + value + "</b>");


		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to create tax", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to create tax", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Reservation
		try {
			test_steps.add("<b>*****************Creating CP reservation</b>***************************");
			nav.Click_ReservationMenuFromTaxPage(driver);
			test_steps.add("Click at resrevation menue link ");

			res.click_NewReservation(driver, test_steps);

/*			res.select_CheckInDate(driver, test_steps, CheckInDate);
			res.select_CheckoutDate(driver, test_steps, CheckOutDate);*/
			getTest_Steps.clear();
			getTest_Steps = res.checkInDate(driver, 0);
			test_steps.addAll(getTest_Steps);
			getTest_Steps.clear();
			getTest_Steps = res.checkOutDate(driver, +2);
			test_steps.addAll(getTest_Steps);
			res.enter_Adults(driver, test_steps, Adults);
			res.enter_Children(driver, test_steps, Children);
			res.select_Rateplan(driver, test_steps, Rateplan, PromoCode);
			res.clickOnFindRooms(driver, test_steps);
			res.selectRoom(driver, test_steps, roomClassName, IsAssign, Account);
			depositAmount = res.deposit(driver, test_steps, IsDepositOverride, DepositOverrideAmount);
			res.clickNext(driver, test_steps);
			randomString = Utility.GenerateRandomNumber();
			String gLastName = GuestLastName.replace("XXX", randomString);
			res.enter_MailingAddress(driver, test_steps, Salutation,GuestFirstName, gLastName, PhoneNumber,
					AltenativePhone, Email, Account, AccountType, Address1, Address2, Address3, City, Country, State,
					PostalCode, IsGuesProfile);
			if ((Account.equalsIgnoreCase("") || Account.isEmpty())) {
				res.enter_PaymentDetails(driver, test_steps, PaymentType, CardNumber, NameOnCard, CardExpDate);
			}
			res.enter_MarketSegmentDetails(driver, test_steps, TravelAgent, MarketSegment, Referral);

			
			/* * * res.verify_NotesSections(driver, test_steps); boolean
			 * falg=res.verify_TaskSections(driver, test_steps); res.enter_Notes(driver,
			 * test_steps, IsAddNotes, NoteType, Subject,Description); if(falg) {
			 * res.enter_Task(driver, test_steps, IsTask, TaskCategory, TaskType,
			 * TaskDetails, TaskRemarks, TaskDueon, TaskAssignee, TaskStatus); }*/
			 

			totalTaxChargesbeforesave = res.getTotaltaxBeforeSaveRes(driver);
			app_logs.info("totalTaxChargesbeforesave :" + totalTaxChargesbeforesave);
			test_steps.add(
					"Total tax charges captured before save reservation: " + "<b>" + totalTaxChargesbeforesave + "</b>");
			totalTripChargesbeforesave = res.getTotalTripTotalBeforeSaveRes(driver);
			app_logs.info("totalTripCharges:" + totalTripChargesbeforesave);
			test_steps.add("Total trip charges captured before reservation saved: " + "<b>" + totalTripChargesbeforesave
					+ "</b>");
			totalRoomChargesBeforeResSave = res.getRoomChargesbeforeResSave(driver);
			app_logs.info("totalRoomChargesBeforeResSave:" + "<b>" + totalRoomChargesBeforeResSave + "<b>");
			test_steps.add("Total room charges captured before resrevation saved: " + "<b>"
					+ totalRoomChargesBeforeResSave + "<b>");
			res.clickBookNow(driver, test_steps);
			reservation = res.get_ReservationConfirmationNumber(driver, test_steps);
			res.clickCloseReservationSavePopup(driver, test_steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to create reservation", "NONGS_Login", "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to create reservation", "NONGS_Login", "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			// After reservation save verify the validation
			test_steps.add("<b>*********** Calculating Overall Taxes************</b>");
			Wait.wait10Second();
			tripSummaryTaxesWithCurrency = res.get_TripSummaryTaxesWithCurrency(driver, test_steps);
			roomCharge = res.getRoomChargeUnderTripSummary(driver, getTest_Steps);
			TripTotalCharge = res.get_TripSummaryTripTotalChargesWithCurrency(driver, test_steps);
				// Calculate tax percent which we have created
                      int stayDays = 2;
						createdpercent = res.getPercentcalcvalueSingleItem(driver, value,
								totalRoomChargesBeforeResSave.replace("$", "").trim(), stayDays);
					

		// ------------------------------------
			// Making assertion of charges after reservation save and before save
			
						test_steps.add(
					"<b>*************Making assertion of Charges Before and After Reservation Save **************<b>");
			tripsummarytaxesafterressave = Double.parseDouble(tripSummaryTaxesWithCurrency.replace("$", "").trim());
			double tripTotalAfterResSave = Double.parseDouble(TripTotalCharge.replace("$", "").trim());
			double roomChargeAfterResSave = Double.parseDouble(roomCharge);
			double totalTripAmountBeforeResSave = Double
					.parseDouble(totalTripChargesbeforesave.replace("$", "").trim());
			//double totalTaxbeforeressave = Double.parseDouble(totalTaxChargesbeforesave.replace("$", "").trim());
			// assert calculatedtax and aftersavedtax
            double taxAfterSave = Double.parseDouble(totalTaxChargesbeforesave.replace("$", "").trim());
				Assert.assertEquals(tripsummarytaxesafterressave, taxAfterSave);
				app_logs.info("Verifying before save tax and after save tax");
				test_steps.add("Verifying calculated existing tax: " + "<b>" + taxAfterSave + "</b>");

				test_steps.add("<br>Making assertion of charges before and after saved the reservation");
				double totalRoomCharges = Double.parseDouble(totalRoomChargesBeforeResSave.replace("$", "").trim());
				Assert.assertEquals(roomChargeAfterResSave, totalRoomCharges);
			test_steps.add("Verifying total room charges before and after reservation saved: " + "<b>" + totalRoomCharges
					+ "</b>");
			Assert.assertEquals(tripTotalAfterResSave, totalTripAmountBeforeResSave);
			test_steps.add("Verifying totalTrip charges before before and after saved: " + "<b>"
					+ totalTripAmountBeforeResSave + "</b>");
				} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to verify charges after reservation", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to verify charges after reservation", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try{
			
			test_steps.add(
					"<b>*************Making assertion of tax in Folio **************<b>");
						String createdPercentCalc = f.format(Double.parseDouble(createdpercent));
						double taxValCreated = Double.parseDouble(createdPercentCalc);
						res.click_Folio(driver, test_steps);
			 res.verifyChildLineItemTaxes(driver, test_steps, taxLedgerAccount,tName.trim(),taxValCreated);
			 
				
				test_steps.add(
						"<b>*************Making assertion after Calculating each Taxes and with Total Captured tax **************<b>");
			 
			 res.getTotalTaxInResFolios(driver, taxLedgerAccount, test_steps);
		}catch(Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to verify tax in reservation folio", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}	
		}catch(Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to verify tax in reservation folio", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			//Delete created records for the script
			test_steps.add(
					"<b>*****************Deleting created Records for Script********************</b>");
			nav.Inventory(driver);
			test_steps.add("Navigate to Inventory");
			nav.Rate(driver);
			test_steps.add("Navigate to Rate");
			rate.DeleteRate(driver, rateDelete);
			test_steps.add("Rate Deleted starting from: "+ "<b>"+rateDelete+"</b>");
			nav.Setup(driver);
			test_steps.add("Navigate to Setup");
			nav.RoomClass(driver);
			test_steps.add("Navigate to Room Classes");
			roomclass.SearchRoomClass(driver, RcDelete, test_steps);
			test_steps.add("Searched room class: "+ "<b>"+RcDelete+"</b>");
			roomclass.deleteRoomClass(driver, RcDelete);
			test_steps.add("Room Class deleted starting from: "+ "<b>"+RcDelete+"</b>");
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(test_name, test_description, test_category, test_steps);
			} catch(Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to delete room class and rate", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch(Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_category, test_steps);
				Utility.updateReport(e, "Failed to delete room class and rate", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getData("VerifyTaxesInCPReservation", excel);
	}
	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		 driver.quit();

	}
}
