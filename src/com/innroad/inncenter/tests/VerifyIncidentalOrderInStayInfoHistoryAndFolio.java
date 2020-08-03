package com.innroad.inncenter.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyIncidentalOrderInStayInfoHistoryAndFolio extends TestCore {

	private WebDriver driver = null;
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> getTest_Steps = new ArrayList<>();
	ArrayList<String> test_name = new ArrayList<>();
	ArrayList<String> test_description = new ArrayList<>();
	ArrayList<String> test_catagory = new ArrayList<>();

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {

		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);

	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void verifyIncidentalOrderInStayInfoHistoryAndFolio(String Amount, String Referral,
			String FirstName, String LastName, String Account, String RoomClassName, 
			String IncidentalName,
			String Quentity, String PendingState,
			
			String Notes, String VoidState, String Description, String Adult,
			String Children, String IsAssign, String isChecked, String Salutation, 
			String HistoryCategory)
			throws InterruptedException, IOException {

		String testname = "VerifyIncidentalOrderInStayInfoHistoryAndFolio";
		String testdescription = "Verify incidental to display in sort order<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682414' target='_blank'>"
				+ "Click here to open TestRail: C682414</a><br>" + "Verify remove icon for Incidentals<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682416' target='_blank'>"
				+ "Click here to open TestRail: C682416</a>";
		String testcatagory = "Reservation_Folio";
		test_name.add(testname);
		test_catagory.add(testcatagory);
		test_description.add(testdescription);
		String testName = testname;

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		Folio folio = new Folio();
		CPReservationPage cpReservationPage = new CPReservationPage();
		String totalAmount = "";

		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);

			} else {
				Utility.reTry.replace(testName, 1);
			}
			driver = getDriver();
			login_CP(driver);
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
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

		// Create Reservation
		try {
			test_steps.add("==========CREATE NEW RESERVATION==========");

			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.click_NewReservation(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.checkInDate(driver, 0);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.checkOutDate(driver, +1);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.enterAdult(driver, Adult);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.enterChildren(driver, Children);
			test_steps.addAll(getTest_Steps);

			
			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.clickOnFindRooms(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			
			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.selectRoom(driver, getTest_Steps, RoomClassName, IsAssign, Account);
			test_steps.addAll(getTest_Steps);
			
			
			
			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.clickNext(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			
			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.VerifyGuestProfileCheckox(driver, Boolean.parseBoolean(isChecked));
			test_steps.addAll(getTest_Steps);
			
			LastName = LastName + Utility.GenerateRandomNumber();
			
			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.enter_GuestName(driver, getTest_Steps, Salutation, FirstName, LastName);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.selectReferral(driver, Referral);
			test_steps.addAll(getTest_Steps);

			//getTest_Steps.clear();
			 cpReservationPage.clickBookNow(driver, getTest_Steps);
			//test_steps.addAll(getTest_Steps);
			String reservationConfirmationNumber = cpReservationPage.get_ReservationConfirmationNumber(driver,
					getTest_Steps);
			test_steps.add("Reservation confirmation number: " + reservationConfirmationNumber);

			// cpReservationPage.get_ReservationStatus(driver, test_steps);
			//getTest_Steps.clear();
			cpReservationPage.clickCloseReservationSavePopup(driver, getTest_Steps);
			//test_steps.addAll(getTest_Steps);

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create new reservation", testName, "CreateReservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create new reservation", testName, "CreateReservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// ADD INCIDENTAL IN STAY INFO
		try {
			test_steps.add("==========ADD INCIDENTAL IN STAY INFO WITH PAST DATE==========");

			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.ButtonAddIncidental(driver);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.EnterAddOn_Incidental(driver, -1, IncidentalName, Amount, Quentity);
			test_steps.addAll(getTest_Steps);

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to add incidental with past date", testName, "AddIncidental", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to add incidental with past date", testName, "AddIncidental", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		
		// Verify INCIDENTAL IN STAY INFO
		try {
			test_steps.add("==========VERIFICATION PAST INCIDENTAL ARE SHOWING AT TOP IN INCIDENTAL SECTION==========");

			String getIncidentalDate = cpReservationPage.getIncidentalDate(driver, 0);
			String getPastDate = ESTTimeZone.getDateIncidental(-1, true);
			test_steps.add("Expected date: " + getPastDate);
			test_steps.add("Found: " + getIncidentalDate);
			assertEquals(getIncidentalDate, getPastDate, "Failed: Past date is mismatching in stay info!");
			test_steps.add("Verified past date");

			String getIncidentalCategory = cpReservationPage.getIncidentalCategory(driver, 0);
			test_steps.add("Expected incidental category: " + IncidentalName);
			test_steps.add("Found: " + getIncidentalCategory);
			assertEquals(getIncidentalCategory, IncidentalName,
					"Failed: Incidental categiry is mismatching in stay info!");
			test_steps.add("Verified incidental category");

			String getIncidentalDescritption = cpReservationPage.getIncidentalDescritption(driver, 0);
			test_steps.add("Expected incidental description: " + Description);
			test_steps.add("Found: " + getIncidentalDescritption);
			assertEquals(getIncidentalDescritption, Description, "Failed: Description is mismatching in stay info!");
			test_steps.add("Verified incidental description");

			String getIncidentalPerUnit = cpReservationPage.getIncidentalPerUnit(driver, 0);
			test_steps.add("Expected per unit: " + Amount);
			test_steps.add("Found: " + getIncidentalPerUnit);
			assertEquals(getIncidentalPerUnit, "$ " + Amount, "Failed: Per unit is mismatching in stay info!");
			test_steps.add("Verified per unit");

			String getIncidentalTax = cpReservationPage.getIncidentalTax(driver, 0);
			test_steps.add("Found tax: " + getIncidentalTax);

			String getIncidentalTotalAmount = cpReservationPage.getIncidentalTotalAmount(driver, 0);
			String totalamount = folio.Incidenatl(Amount, Quentity);
			app_logs.info("totalamount: " + totalamount);
			totalAmount = folio.AddValue(totalamount, folio.splitString(getIncidentalTax));
			app_logs.info("totalamount: " + totalamount);
			totalAmount = "$ " + totalAmount;
			test_steps.add("Expected Amount after included tax: " + totalAmount);
			test_steps.add("Found: " + getIncidentalTotalAmount);
			assertEquals(getIncidentalTotalAmount, totalAmount, "Failed: Amount is mismatching in stay info!");
			test_steps.add("Verified amount after include tax");

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify paste incidental at 0 index in stay info", testName,
						"Incidental", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify paste incidental at 0 index in stay info", testName,
						"Incidental", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			test_steps.add("==========ADD INCIDENTAL IN STAY INFO WITH PRESENT DATE==========");

			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.ButtonAddIncidental(driver);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.EnterAddOn_Incidental(driver, 0, IncidentalName, Amount, Quentity);
			test_steps.addAll(getTest_Steps);

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to add incidental with present date", testName, "AddIncidental",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to add incidental with present date", testName, "AddIncidental",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Verify INCIDENTAL IN STAY INFO
		try {
			test_steps.add("==========VERIFICATION PRESENT INCIDENTAL ARE SHOWING AFTER PAST IN STAY INFO==========");

			String getIncidentalDate = cpReservationPage.getIncidentalDate(driver, 1);
			String getPastDate = ESTTimeZone.getDateIncidental(0, true);
			test_steps.add("Expected date: " + getPastDate);
			test_steps.add("Found: " + getIncidentalDate);
			assertEquals(getIncidentalDate, getPastDate, "Failed: Past date is mismatching in stay info!");
			test_steps.add("Verified past date");

			String getIncidentalCategory = cpReservationPage.getIncidentalCategory(driver, 1);
			test_steps.add("Expected incidental category: " + IncidentalName);
			test_steps.add("Found: " + getIncidentalCategory);
			assertEquals(getIncidentalCategory, IncidentalName,
					"Failed: Incidental categiry is mismatching in stay info!");
			test_steps.add("Verified incidental category");

			String getIncidentalDescritption = cpReservationPage.getIncidentalDescritption(driver, 1);
			test_steps.add("Expected incidental description: " + Description);
			test_steps.add("Found: " + getIncidentalDescritption);
			assertEquals(getIncidentalDescritption, Description, "Failed: Description is mismatching in stay info!");
			test_steps.add("Verified incidental description");

			String getIncidentalPerUnit = cpReservationPage.getIncidentalPerUnit(driver, 1);
			test_steps.add("Expected per unit: " + Amount);
			test_steps.add("Found: " + getIncidentalPerUnit);
			assertEquals(getIncidentalPerUnit, "$ " + Amount, "Failed: Per unit is mismatching in stay info!");
			test_steps.add("Verified per unit");

			String getIncidentalTax = cpReservationPage.getIncidentalTax(driver, 1);
			test_steps.add("Found tax: " + getIncidentalTax);

			String getIncidentalTotalAmount = cpReservationPage.getIncidentalTotalAmount(driver, 1);
			test_steps.add("Expected Amount after included tax: " + totalAmount);
			test_steps.add("Found: " + getIncidentalTotalAmount);
			assertEquals(getIncidentalTotalAmount, totalAmount, "Failed: Amount is mismatching in stay info!");
			test_steps.add("Verified amount after include tax");

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify present incidental at 1 index in stay info", testName,
						"Incidental", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify present incidental at 1 index in stay info", testName,
						"Incidental", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			test_steps.add("==========ADD INCIDENTAL IN STAY INFO WITH FUTURE DATE==========");

			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.ButtonAddIncidental(driver);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.EnterAddOn_Incidental(driver, +1, IncidentalName, Amount, Quentity);
			test_steps.addAll(getTest_Steps);

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to add incidental with future date", testName, "AddIncidental", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to add incidental with future date", testName, "AddIncidental", driver);
			} else {
				Assert.assertTrue(false);
			}
		}


		// Verify INCIDENTAL IN STAY INFO
		try {
			test_steps
					.add("==========VERIFICATION FUTURE INCIDENTAL ARE SHOWING AFTER PRESENT  IN STAY INFO==========");

			String getIncidentalDate = cpReservationPage.getIncidentalDate(driver, 2);
			String getPastDate = ESTTimeZone.getDateIncidental(+1, true);
			test_steps.add("Expected date: " + getPastDate);
			test_steps.add("Found: " + getIncidentalDate);
			assertEquals(getIncidentalDate, getPastDate, "Failed: Future date is mismatching in stay info!");
			test_steps.add("Verified future date");

			String getIncidentalCategory = cpReservationPage.getIncidentalCategory(driver, 2);
			test_steps.add("Expected incidental category: " + IncidentalName);
			test_steps.add("Found: " + getIncidentalCategory);
			assertEquals(getIncidentalCategory, IncidentalName,
					"Failed: Incidental categiry is mismatching in stay info!");
			test_steps.add("Verified incidental category");

			String getIncidentalDescritption = cpReservationPage.getIncidentalDescritption(driver, 2);
			test_steps.add("Expected incidental description: " + Description);
			test_steps.add("Found: " + getIncidentalDescritption);
			assertEquals(getIncidentalDescritption, Description, "Failed: Description is mismatching in stay info!");
			test_steps.add("Verified incidental description");

			String getIncidentalPerUnit = cpReservationPage.getIncidentalPerUnit(driver, 2);
			test_steps.add("Expected per unit: " + Amount);
			test_steps.add("Found: " + getIncidentalPerUnit);
			assertEquals(getIncidentalPerUnit, "$ " + Amount, "Failed: Per unit is mismatching in stay info!");
			test_steps.add("Verified per unit");

			String getIncidentalTax = cpReservationPage.getIncidentalTax(driver, 2);
			test_steps.add("Found tax: " + getIncidentalTax);

			String getIncidentalTotalAmount = cpReservationPage.getIncidentalTotalAmount(driver, 2);
			test_steps.add("Expected Amount after included tax: " + totalAmount);
			test_steps.add("Found: " + getIncidentalTotalAmount);
			assertEquals(getIncidentalTotalAmount, totalAmount, "Failed: Amount is mismatching in stay info!");
			test_steps.add("Verified amount after include tax");

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify future incidental at end of inncidenatl section", testName,
						"Incidental", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify future incidental at end of inncidenatl section", testName,
						"Incidental", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// INCIDENTAL IN HISTORY
		try {
			test_steps.add("==========VERIFICATION OF INCIDENTAL IN HISTORY==========");

			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.ClickOnHistory(driver);
			test_steps.addAll(getTest_Steps);

			String getHistoryCategory = cpReservationPage.getHistoryCategory(driver, 0);
			test_steps.add("Expected category: " + HistoryCategory);
			test_steps.add("Found: " + getHistoryCategory);
			assertEquals(getHistoryCategory, HistoryCategory, "Failed: History category is mismatching!");
			test_steps.add("Verified category");

			String getHistoryDate = cpReservationPage.gettHistoryDate(driver, 0);
			String getDate = ESTTimeZone.DateFormateForLineItem(0);
			test_steps.add("Expected date: " + getDate);
			test_steps.add("Found: " + getHistoryDate);
			assertTrue(getDate.contains(getHistoryDate), "Failed: History date is mismatching!");
			test_steps.add("Verified date");

			String gettHistoryDescription = cpReservationPage.getHistoryDescription(driver, 0);
			String description = "Added " + IncidentalName + " item " + Description + " to Guest Folio";
			test_steps.add("Expected description: " + description);
			test_steps.add("Found: " + getHistoryDate);
			assertEquals(gettHistoryDescription, description, "Failed: History description is mismatching!");
			test_steps.add("Verified description");

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify incidental in hostory", testName, "Incidental", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify incidental in hostory", testName, "Incidental", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		int BeforeRowsSize = folio.getLineItemRows(driver);

		try {
			test_steps.add("==========VERIFICATION PASTE INCIDENTAL ARE SHOWING AT TOP IN FOLIO LINE ITEM==========");

			getTest_Steps.clear();
			getTest_Steps = folio.folioTab(driver);
			test_steps.addAll(getTest_Steps);

			BeforeRowsSize = folio.getLineItemRows(driver);
			app_logs.info("BeforeRowsSize: " + BeforeRowsSize);

			folio.VerifyLineItems_State(driver, IncidentalName, PendingState, 1);
			test_steps.add("Verify line itme in pending state after added");

			getTest_Steps.clear();
			getTest_Steps = folio.LineItemDate(driver, IncidentalName, -1, 1);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.LineItemCategory(driver, IncidentalName, 1);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.getDescroption(driver, IncidentalName, Description, false, 1);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.LineItemQuentity(driver, IncidentalName, Quentity, 1);
			test_steps.addAll(getTest_Steps);

			String getAmount = folio.getAmount(driver, IncidentalName, 1);
			test_steps.add("Expected amount after added tax: " + totalAmount);
			test_steps.add("Found : " + getAmount);
			assertEquals(getAmount, totalAmount, "Failed: Amount is mismatching!");
			test_steps.add("Verified amount after included tax");

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
			test_steps.add(
					"==========VERIFICATION PRESENT INCIDENTAL ARE SHOWING AFTER PAST DATE IN FOLIO LINE ITEM==========");

			getTest_Steps.clear();
			getTest_Steps = folio.folioTab(driver);
			test_steps.addAll(getTest_Steps);

			folio.VerifyLineItems_State(driver, IncidentalName, PendingState, 2);
			test_steps.add("Verify line itme in pending state after added");

			getTest_Steps.clear();
			getTest_Steps = folio.LineItemDate(driver, IncidentalName, 0, 2);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.LineItemCategory(driver, IncidentalName, 2);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.getDescroption(driver, IncidentalName, Description, false, 2);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.LineItemQuentity(driver, IncidentalName, Quentity, 2);
			test_steps.addAll(getTest_Steps);

			String getAmount = folio.getAmount(driver, IncidentalName, 2);
			test_steps.add("Expected amount after added tax: " + totalAmount);
			test_steps.add("Found : " + getAmount);
			assertEquals(getAmount, totalAmount, "Failed: Amount is mismatching!");
			test_steps.add("Verified amount after included tax");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify present incidental in line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify present incidental in line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			test_steps.add(
					"==========VERIFICATION FUTURE INCIDENTAL ARE SHOWING AFTER PRESENT DATE IN FOLIO LINE ITEM==========");

			getTest_Steps.clear();
			getTest_Steps = folio.folioTab(driver);
			test_steps.addAll(getTest_Steps);

			folio.VerifyLineItems_State(driver, IncidentalName, PendingState, 3);
			test_steps.add("Verify line itme in pending state after added");

			getTest_Steps.clear();
			getTest_Steps = folio.LineItemDate(driver, IncidentalName, +1, 3);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.LineItemCategory(driver, IncidentalName, 3);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.getDescroption(driver, IncidentalName, Description, false, 3);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.LineItemQuentity(driver, IncidentalName, Quentity, 3);
			test_steps.addAll(getTest_Steps);

			String getAmount = folio.getAmount(driver, IncidentalName, 3);
			test_steps.add("Expected amount after added tax: " + totalAmount);
			test_steps.add("Found : " + getAmount);
			assertEquals(getAmount, totalAmount, "Failed: Amount is mismatching!");
			test_steps.add("Verified amount after included tax");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify present incidental in line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify present incidental in line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Delete INCIDENTAL IN STAY INFO
		try {
			test_steps.add("==========VERIFICATION DELETED FUTURE INCIDENTAL IN STAY INFO==========");

			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.ClickOnDetails(driver);
			test_steps.addAll(getTest_Steps);

			int getNumberOfIncidentalRowsBeforeDelete = cpReservationPage.getNumberOfIncidentalRows(driver);
			app_logs.info("getNumberOfIncidentalRowsBeforeDelete : " + getNumberOfIncidentalRowsBeforeDelete);

			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.ClickOnDeleteOncidentalButton(driver, 2);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.IncidentalVoidpopup(driver, Notes, 0);
			test_steps.addAll(getTest_Steps);

			int getNumberOfIncidentalRowsAfterDelete = cpReservationPage.getNumberOfIncidentalRows(driver);
			app_logs.info("getNumberOfIncidentalRowsAfterDelete : " + getNumberOfIncidentalRowsAfterDelete);
			assertEquals(getNumberOfIncidentalRowsAfterDelete, getNumberOfIncidentalRowsBeforeDelete - 1,
					"Failed: Inciental did not remove");

			test_steps.add("Verified one row removed from incidental in stay info");

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify delete incidental from saty info", testName, "Incidental",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify delete incidental from saty info", testName, "Incidental",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Delete INCIDENTAL IN HISTORY
		try {
			test_steps.add("==========VERIFICATION OF DELETEd INCIDENTAL IN HISTORY==========");

			getTest_Steps.clear();
			getTest_Steps = cpReservationPage.ClickOnHistory(driver);
			test_steps.addAll(getTest_Steps);

			String getHistoryCategory = cpReservationPage.getHistoryCategory(driver, 0);
			test_steps.add("Expected category: " + HistoryCategory);
			test_steps.add("Found: " + getHistoryCategory);
			assertEquals(getHistoryCategory, HistoryCategory, "Failed: History category is mismatching!");
			test_steps.add("Verified category");

			String getHistoryDate = cpReservationPage.gettHistoryDate(driver, 0);
			String getDate = ESTTimeZone.DateFormateForLineItem(0);
			test_steps.add("Expected date: " + getDate);
			test_steps.add("Found: " + getHistoryDate);
			assertTrue(getDate.contains(getHistoryDate), "Failed: History date is mismatching!");
			test_steps.add("Verified date");

			String gettHistoryDescription = cpReservationPage.getHistoryDescription(driver, 0);
			String description = "Removed " + IncidentalName + " item " + Description + " from Guest Folio";
			test_steps.add("Expected description: " + description);
			test_steps.add("Found: " + getHistoryDate);
			assertEquals(gettHistoryDescription, description, "Failed: History description is mismatching!");
			test_steps.add("Verified description");

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify deleted incidental in hostory", testName, "History", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify deleted incidental in hostory", testName, "History", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			test_steps.add("==========VERIFICATION DELETED INCIDENTAL IN FOLIO LINE ITEM==========");

			getTest_Steps.clear();
			getTest_Steps = folio.folioTab(driver);
			test_steps.addAll(getTest_Steps);

			
			folio.CheckboxDisplayVoidItem(driver, true);
			folio.VerifyLineItems_State(driver, IncidentalName, VoidState, 1);
			test_steps.add("Verified deleted incidenatl is showing in folio line item in void state");
			
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);

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

	}
	@DataProvider
	public Object[][] getData() {

		// return test data from the sheet name provided
		return Utility.getData("VerifyIncidental_ResFolio", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();

	}

}
