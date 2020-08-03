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
import com.innroad.inncenter.pageobjects.NightAudit;
import com.innroad.inncenter.pageobjects.Properties;
import com.innroad.inncenter.pageobjects.Reservation;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.Tax;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class LongStayFunctionality extends TestCore {

	private WebDriver driver = null;

	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();

	public static ArrayList<String> test_name = new ArrayList<>();
	public static ArrayList<String> test_description = new ArrayList<>();
	public static ArrayList<String> test_catagory = new ArrayList<>();

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String scriptName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + scriptName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(scriptName, excel))
			throw new SkipException("Skipping the test - " + scriptName);

	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void longStayFunctionality(
			String propertyName, String MarketeSegment, String referral, String firstName, 
			String lastName,
			String phoneNumber, String alternativeNumber, String address1, String address2, 
			String address3, String lineItemCategory, String RoomClass, String account,
			String saluation, String isTaxExempt, String taxEmptext, String nightValue,
			String nightStay, String enterAmount,
			String permanentTaxName, String nightsBack, String isAssign, String child, String adult, String salutation,
			String isChecked, String paymentType, String cardNumber, String nameOnCard)
			throws InterruptedException, IOException {

		String scriptName = "LongStayFunctionality";
		String description = "Long Stay Functionality<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682404' target='_blank'>"
				+ "Click here to open TestRail: C51104</a>";
		String catagory = "Reservation";

		test_name.add(scriptName);
		test_description.add(description);
		test_catagory.add(catagory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + scriptName + " TEST.");
		app_logs.info("##################################################################################");

		Navigation navigation = new Navigation();
		Tax tax = new Tax();
		Properties properties = new Properties();
		NightAudit nightAudit = new NightAudit();
		String beforeCheckInLongStay = "";
		String afterCheckInLongStay = "";
		String reservationNumber = "";
		Folio folio = new Folio();
		CPReservationPage reservationPage = new CPReservationPage();

		String timeZone = "America/New_York";
		String cardExpDate = Utility.getNextDate(365, "MM/yy", timeZone);
		app_logs.info("Card Expiry " + cardExpDate);
		ReservationSearch reservationSearch = new ReservationSearch();

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
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Delete Tax
		try {
			navigation.Setup(driver);
			app_logs.info("Navigate Setup");
			testSteps.add("Navigate Setup");
			tax.ClickTaxes(driver);
			getTestSteps.clear();
			getTestSteps = tax.DeleteAllTaxExcept(driver, permanentTaxName, getTestSteps);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Delete Tax", scriptName, "DeleteTax", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to Delete Tax ", scriptName, "DeleteTax", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Setup
		try {

			navigation.Setup(driver);
			testSteps.add("Navigate to Setup");
			app_logs.info("Navigate to Setup");
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to navigate to Setup", scriptName, "Setup", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to navigate to Setup", scriptName, "Setup", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			getTestSteps.clear();
			getTestSteps = properties.LongStay(driver, propertyName, nightValue, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify long stay taxt exempt", scriptName, "Properties", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify long stay taxt exempt", scriptName, "Properties", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Reservation
		try {

			navigation.Reservation(driver);
			testSteps.add("Click on Reservations");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to click on Reservations", scriptName, "Reservations", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to click on Reservations", scriptName, "Reservations", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Night Audit
		try {

			navigation.NightAudit(driver);
			testSteps.add("Navigate to Night Audit");
			app_logs.info("Navigate to Night Audit");
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to navigate to Night Audit", scriptName, "NightAudit", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to navigate to Night Audit", scriptName, "NightAudit", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// verify tax exempt value in night audit
		try {

			beforeCheckInLongStay = nightAudit.LongStay(driver);
			testSteps.add("Before check in long stay value : " + beforeCheckInLongStay);
			app_logs.info("Before check in long stay value : " + beforeCheckInLongStay);
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify long stay value in night aduit", scriptName, "NightAudit",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify long stay value in night aduit", scriptName, "NightAudit",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// here create new resrevation

		// Create Reservation
		try {
			testSteps.add("==========CREATE NEW RESERVATION==========");

			navigation.Reservation(driver);
			testSteps.add("Click on Reservations");
			app_logs.info("Click on Reservations");

			getTestSteps.clear();
			getTestSteps = reservationPage.click_NewReservation(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.checkInDate(driver, -4);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.checkOutDate(driver, 0);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterAdult(driver, adult);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterChildren(driver, child);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnFindRooms(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectRoom(driver, getTestSteps, RoomClass, isAssign, account);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.VerifyGuestProfileCheckox(driver, Boolean.parseBoolean(isChecked));
			testSteps.addAll(getTestSteps);

			lastName = lastName + Utility.GenerateRandomNumber();

			getTestSteps.clear();
			getTestSteps = reservationPage.enter_GuestName(driver, getTestSteps, salutation, firstName, lastName);
			testSteps.addAll(getTestSteps);

			reservationPage.enter_PaymentDetails(driver, getTestSteps, paymentType, cardNumber, nameOnCard,
					cardExpDate);

			getTestSteps.clear();
			getTestSteps = reservationPage.selectReferral(driver, referral);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.clickBookNow(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			reservationNumber = reservationPage.get_ReservationConfirmationNumber(driver, getTestSteps);
			testSteps.add("Reservation confirmation number: " + reservationNumber);

			reservationPage.clickCloseReservationSavePopup(driver, getTestSteps);
			testSteps.add("Close reservation confirmation popup");

			String getReservationStatus = reservationPage.reservationStatus(driver);
			app_logs.info("Reservation status: " + getReservationStatus);

		} catch (Exception e) {

			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create new reservation", scriptName, "CreateReservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create new reservation", scriptName, "CreateReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// here verify line item tax
		try {
			testSteps.add("==========VERIFICATION OF TAX EXEMPT IN FOLIO==========");
			folio.folioTab(driver);
			folio.VerifyTaxExempt(driver, false);
			testSteps.add("Verified tax exempt not applied on folio Tax line item");
		}

		catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify tax item in folio line item", scriptName, "FolioLineItem",
						driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify tax item in folio line item", scriptName, "FolioLineItem",
						driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		// check in reservation
		try {
			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnCheckInButton(driver, true, true);
			testSteps.addAll(getTestSteps);
			
			reservationPage.verifyGenerateGuestReportToggle(driver);
			testSteps.add("Click on toggle button for report generate");
			app_logs.info("Click on toggle button for report generate");
			
			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnConfirmCheckInButton(driver);
			testSteps.addAll(getTestSteps);

			String reservationStatus = reservationPage.reservationStatus(driver);
			app_logs.info("Reservation status after checkin: " + reservationStatus);
			assertEquals(reservationStatus, "IN-HOUSE", "Failed: Reservation status is mismatching after checkin");
			testSteps.add("Verified reservation status has been changed from reserved to " + reservationStatus);
			app_logs.info("Verified reservation status has been changed from reserved to " + reservationStatus);

			testSteps.add("Checkin Reservation Successfully");
			app_logs.info("Checkin Reservation Successfully");

		}

		catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Fail to checkin reservation", scriptName, "CheckInReservation", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Fail to checkin reservation", scriptName, "CheckInReservation", driver);

			} else {
				Assert.assertTrue(false);
			}

		}

		
		// verified amount after tax exempt
		ArrayList<String> amount = new ArrayList<>();
		ArrayList<String> getTax = new ArrayList<>();
		
		try {
			getTestSteps = folio.folioTab(driver);
			testSteps.addAll(getTestSteps);
			
			amount  = folio.getAmountWithIndex(driver, lineItemCategory);
			testSteps.add("Get amount with tax");
			
			getTax  = folio.getTaxWithIndex(driver, lineItemCategory);
			testSteps.add("Get taxes against each amount");
		
		}

		catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify tax item in folio line item", scriptName, "FolioLineItem",
						driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify tax item in folio line item", scriptName, "FolioLineItem",
						driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			reservationPage.closeReservationTab(driver);
			testSteps.add("Closed the Reservation");
			app_logs.info("Closed the Reservation");
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Fail to close the Reservation", scriptName, "closeReservation", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Fail to close the Reservation", scriptName, "closeReservation", driver);

			} else {
				Assert.assertTrue(false);
			}
		}
		// Night Audit
		try {

			navigation.NightAudit(driver);
			testSteps.add("Navigate to Night Audit");
			app_logs.info("Navigate to Night Audit");
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to navigate to Night Audit", scriptName, "NightAudit", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to navigate to Night Audit", scriptName, "NightAudit", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// verify tax exempt value in night audit
		try {

			afterCheckInLongStay = nightAudit.LongStay(driver);
			testSteps.add("After check in long stay value : " + afterCheckInLongStay);
			app_logs.info("Before check in long stay value : " + afterCheckInLongStay);

			int beforeValue = Integer.parseInt(beforeCheckInLongStay);
			beforeValue = beforeValue + 1;
			int afterValue = Integer.parseInt(afterCheckInLongStay);

			testSteps.add("Long stay value after check in " + afterValue);

			assertEquals(beforeValue, afterValue, "Long stay value did not increase");
			testSteps.add("Long stay value has been increased by one after check in " + afterValue);
			app_logs.info("Long stay value has been increased by one after check in " + afterValue);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify long stay value", scriptName, "NightAudit", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify long stay value", scriptName, "NightAudit", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			getTestSteps.clear();
			getTestSteps = nightAudit.LongStay_SetNow(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to click on set now button", scriptName, "NightAudit", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to click on set now button", scriptName, "NightAudit", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {

			navigation.Reservation(driver);
			testSteps.add("Click on Reservations");

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to click on Reservations", scriptName, "Reservations", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to click on Reservations", scriptName, "Reservations", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Search for Above Reservations
		try {

			testSteps.add("============SEARCH FOR CREATED RESERVATIONS============");
			reservationSearch.multipleSearchReservationNumber(driver, reservationNumber);
			testSteps.add("Successfully search reservation");

			getTestSteps.clear();
			getTestSteps = reservationPage.clickOnGuestNameInSearchReaservation(driver, reservationNumber);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, description, catagory, testSteps);
				Utility.updateReport(e, "Failed to search created reservation", scriptName, "ReservationSearch", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, description, catagory, testSteps);
				Utility.updateReport(e, "Failed to search created reservation", scriptName, "ReservationSearch", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// verify long stay check box in detail reservation
		try {

			testSteps.add("============VERIFICATION OF LONG STAY CHECKBOX IN DETAILS RESERVATION============");
			getTestSteps.clear();
			getTestSteps = reservationPage.verifyLongStayCheckbox(driver);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, description, catagory, testSteps);
				Utility.updateReport(e, "Failed to verify long stay checkbox in details", scriptName, "DetailsReservtion",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(scriptName, description, catagory, testSteps);
				Utility.updateReport(e, "Failed to verify long stay checkbox in details", scriptName, "DetailsReservtion",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		
		// verified amount after tax exempt
		try {
			getTestSteps.clear();
			getTestSteps = folio.folioTab(driver);
			testSteps.addAll(getTestSteps);
			
			folio.VerifyAmountAfterTaxExempt(driver, amount, getTax, lineItemCategory);
			testSteps.add("Verified folio items has been been set as Tax Exempt");
			
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
			
		}

		catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify tax item in folio line item", scriptName, "FolioLineItem",
						driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to verify tax item in folio line item", scriptName, "FolioLineItem",
						driver);

			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {

		return Utility.getData("LongStayFunctionality", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closedriver() {
		// driver.quit();
	}

}
