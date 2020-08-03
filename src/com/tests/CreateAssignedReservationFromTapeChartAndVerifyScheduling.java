package com.innroad.inncenter.tests;

import java.io.File;
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
import com.innroad.inncenter.pageobjects.DocumnetTemplates;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Tapechart;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.EmailUtils;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class CreateAssignedReservationFromTapeChartAndVerifyScheduling extends TestCore {

	private WebDriver driver = null;
	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();
	ArrayList<String> testName = new ArrayList<>();
	ArrayList<String> testCategory = new ArrayList<>();
	ArrayList<String> testDescription = new ArrayList<>();
	public static String testdescription = "";
	public static String testcatagory = "";

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void createAssignedReservationFromTapeChartAndVerifyScheduling(String adults, String children,
			String ratePlan, String roomClassAbb, String roomClass, String isAssign, String salutation,
			String guestFirstName, String guestLastName, String phoneNumber, String altenativePhone, String email,
			String isGuesProfileCreate, String referral, String documentName, String documentDescription,
			String associateProperties, String functionTrigger, String functionEvent, String functionAttachment,
			String user) throws InterruptedException, IOException {

		app_logs.info("email: "+email);
		String scriptName = "CreateAssignedReservationFromTapeChartAndVerifyScheduling";
		testdescription = "Create Assigned Reservation -> Tape Chart and verify scheduling<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682442' target='_blank'>"
				+ "Click here to open TestRail: C682442</a>";

		testcatagory = "TapeChart";
		testName.add(scriptName);
		testDescription.add(testdescription);
		testCategory.add(testcatagory);

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + scriptName + " TEST.");
		app_logs.info("##################################################################################");

		CPReservationPage reservation = new CPReservationPage();
		Navigation navigation = new Navigation();
		Tapechart tapechart = new Tapechart();
		DocumnetTemplates documentTemplates = new DocumnetTemplates();
		String randomNumber = Utility.GenerateRandomNumber();
		guestLastName = guestLastName + randomNumber;
		documentDescription = documentDescription + randomNumber;
		documentName = documentName + randomNumber;
		String roomNumber = "";
		String reservationNumber = "";
		String status = "";
		String selectedRoomClass = null;
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
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			testSteps.add("==========CREATE DOCUMENT TEMPLATE==========");
			app_logs.info("==========CREATE DOCUMENT TEMPLATE==========");
			navigation.navigateSetup(driver);
			testSteps.add("Navigate Setup");
			app_logs.info("Navigate Setup");
			navigation.navigateDocumentTemplate(driver);
			testSteps.add("Navigate DocumentTemplate");
			app_logs.info("Navigate DocumentTemplate");

			getTestSteps.clear();
			documentTemplates.clickNewDocument(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			documentTemplates.enterDocumnetName(driver, documentName);
			testSteps.add("Entered DocumentName : " + documentName);
			app_logs.info("Entered DocumentName : " + documentName);

			documentTemplates.selectDefaultDocumentCheckBox(driver);
			testSteps.add("Default Template Checked");
			app_logs.info("Default Template Checked");

			getTestSteps.clear();
			documentTemplates.enterDocumnetDescription(driver, documentDescription, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			documentTemplates.associateSources(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			documentTemplates.associateProperties(driver, associateProperties, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			documentTemplates.associateFunction(driver, functionTrigger, functionEvent, functionAttachment,
					getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			documentTemplates.saveDocument(driver, getTestSteps);
			testSteps.addAll(getTestSteps);
			getTestSteps.clear();

		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to search and click on available room from tapechart", scriptName,
						"Tapechart", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to search and click on available room from tapechart", scriptName,
						"Tapechart", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			testSteps.add("==========CREATE ASSIGNED RESERVATION FROM TAPE CHART==========");
			app_logs.info("==========CREATE ASSIGNED RESERVATION FROM TAPE CHART==========");

			navigation.reservation(driver);
			testSteps.add("Navigate Reservation");
			app_logs.info("Navigate Reservation");

			navigation.navigateTapeChart(driver, test);
			testSteps.add("Navigate TapeChart");
			app_logs.info("Navigate TapeChart");
			app_logs.info("==========ENTER CHECK IN DETAILS AND SEARCH ROOMS==========");
			testSteps.add("==========ENTER CHECK IN DETAILS AND SEARCH ROOMS==========");
			getTestSteps.clear();
			getTestSteps = tapechart.tapeChartSearch(driver, Utility.getCurrentDate("MM/dd/yyyy"),
					Utility.getNextDate(1, "MM/dd/yyyy"), adults, children, ratePlan, getTestSteps);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========SELECT ROOM==========");
			testSteps.add("==========SELECT ROOM==========");
			tapechart.clickAvailableSlot(driver, roomClassAbb);
			testSteps.add("Click available room of Room Class '" + roomClassAbb + "'");
			app_logs.info("Click on available room");
			testSteps.add("New Reservation page is opened");
			app_logs.info("New Reservation Page is Opened");
		} catch (Exception e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to search and click on available room from tapechart", scriptName,
						"Tapechart", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to search and click on available room from tapechart", scriptName,
						"Tapechart", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Guest profile
		try {
			String room = reservation.getRoomSelectedFromTapeChart(driver, testSteps);
			Assert.assertTrue(room.contains(roomClass), "Failed: Room is not selected");
			testSteps.add("Verified Room Class is '" + roomClass + "'");

			app_logs.info("==========ENTER GUEST INFO==========");
			testSteps.add("==========ENTER GUEST INFO==========");
			reservation.enterGuestName(driver, testSteps, salutation, guestFirstName, guestLastName);
			reservation.uncheckCreateGuestProfile(driver, testSteps, isGuesProfileCreate);
			reservation.enterEmail(driver, testSteps, email);
			app_logs.info("==========ENTER MARKETING INFO==========");
			testSteps.add("==========ENTER MARKETING INFO==========");
			getTestSteps.clear();
			getTestSteps = reservation.SelectReferral(driver, referral);
			testSteps.addAll(getTestSteps);
			app_logs.info("==========SAVE RESERVATION==========");
			testSteps.add("==========SAVE RESERVATION==========");
			getTestSteps.clear();
			reservation.clickBookNow(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			app_logs.info("==========VERIFY RESERVATION CONFIRMATION POPUP DETAILS==========");
			testSteps.add("==========VERIFY RESERVATION CONFIRMATION POPUP DETAILS==========");
			getTestSteps.clear();
			getTestSteps = reservation.getReservationEmail(driver, email);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservationNumber = reservation.getReservationConfirmationNumber(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			status = reservation.getReservationStatus(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			Assert.assertEquals(status, "Reserved", "Failed: Reservation Status missmatched");

			getTestSteps.clear();
			reservation.clickCloseReservationSavePopup(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			roomNumber = reservation.getRoomNumber(driver, getTestSteps, isAssign);

			testSteps.add("Selected Room Number : " + roomNumber);
			app_logs.info("Selected Room Number : " + roomNumber);

			selectedRoomClass = reservation.getRoomClassResDetail(driver);
			testSteps.add("Selected RoomClass : " + selectedRoomClass);
			app_logs.info("Selected RoomClass : " + selectedRoomClass);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to fill complete guest profile info", scriptName, "Reservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to fill complete guest profile info", scriptName, "Reservation",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {

			app_logs.info("==========CLOSE RESERVATION==========");
			testSteps.add("==========CLOSE RESERVATION==========");
			getTestSteps.clear();
			reservation.closeFirstOpenedReservation(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to close opened reservation", scriptName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to close opened reservation", scriptName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// verifying email verification
		try {

			app_logs.info("==========VERIFY EMAIL SUBJECT==========");
			testSteps.add("==========VERIFY EMAIL SUBJECT==========");
			Wait.wait60Second();
			EmailUtils emailUtils = new EmailUtils(EmailUtils.EmailFolder.INBOX);
			EmailUtils.setTargetDirectory(System.getProperty("user.dir") + File.separator + "externalFiles"
					+ File.separator + "downloadFiles" + File.separator + "GuestRegEmails");

			app_logs.info("==========DOWNLOAD EMAIL ATTACHMENT==========");
			testSteps.add("==========DOWNLOAD EMAIL ATTACHMENT==========");
			emailUtils.downloadEmailAttachments("Reservation #: " + reservationNumber, true, 3);
			testSteps.add("Verified Email Subject 'Reservation #: " + reservationNumber + "'");
			app_logs.info("Verified Email Subject 'Reservation #: " + reservationNumber + "'");
			testSteps.add("Email Attachment Downloaded Successfully");
			app_logs.info("Email Attachment Downloaded Successfully");
			String pdfFileText = reservation.checkPDF("GuestRegEmails");
			app_logs.info("==========VERIFYING RESERVATION DETALS==========");
			testSteps.add("==========VERIFYING RESERVATION DETALS==========");
			if (pdfFileText != null) {
				app_logs.info(pdfFileText);

				Assert.assertTrue(pdfFileText.contains("Guest Registration"), "Failed: Guest Registration Not Found");
				testSteps.add("Verified On the top right 'Guest Registration' is displayed");
				app_logs.info("Verified On the top right 'Guest Registration' is displayed");

				testSteps.add("Room '" + roomClass + " : " + roomNumber + "'");
				app_logs.info("Room '" + roomClass + " : " + roomNumber + "'");
				Assert.assertTrue(pdfFileText.contains("Room : " + roomClass + " : " + roomNumber),
						"Failed: Room Not Found");
				testSteps.add("Verified Room number shown is the chosen Room class and number");
				app_logs.info("Verified Room number shown is the chosen Room class and number");

				testSteps.add("Email '" + email + "'");
				app_logs.info("Email '" + email + "'");
				Assert.assertTrue(pdfFileText.contains(email), "Failed: Email Not Found");
				testSteps.add("Verified Email id was the one stated within the Reservation");
				app_logs.info("Verified Email id was the one stated within the Reservation");

				testSteps.add("'Property " + associateProperties + ":'");
				app_logs.info("'Property " + associateProperties + ":'");
				Assert.assertTrue(pdfFileText.contains("Property " + associateProperties + ":"),
						"Failed: Property Not Found");
				testSteps.add("Verified Property");
				app_logs.info("Verified Property");

				String date = Utility.getCurrentDate("E MMM dd,yyyy");

				testSteps.add("'Arrival : " + date + "'");
				app_logs.info("'Arrival : " + date + "'");
				Assert.assertTrue(pdfFileText.contains("Arrival : " + date), "Failed: Arrival Date Not Found");
				testSteps.add("Verified Arrival Date");
				app_logs.info("Verified Arrival Date");

				date = Utility.getNextDate(1, "E MMM dd,yyyy");
				testSteps.add("'Departure : " + date + "'");
				app_logs.info("'Departure : " + date + "'");
				Assert.assertTrue(pdfFileText.contains("Departure : " + date), "Failed: Departure Date Not Found");
				testSteps.add("Verified Departure Date");
				app_logs.info("Verified  Departure Date");

				date = Utility.getCurrentDate("E MMM dd,yyyy");
				testSteps.add("'Booked on : " + date + "'");
				app_logs.info("'Booked on : " + date + "'");
				Assert.assertTrue(pdfFileText.contains("Booked on : " + date), "Failed: Booked on Date Not Found");
				testSteps.add("Verified Booked on Date");
				app_logs.info("Verified Booked on Date");

				testSteps.add("'Printed on :  " + date + "'");
				app_logs.info("'Printed on :  " + date + "'");
				Assert.assertTrue(pdfFileText.contains("Printed on :  " + date), "Failed: Printed on Date Not Found");
				testSteps.add("Verified Printed on Date");
				app_logs.info("Verified Printed on Date");

				testSteps.add("'Booked By : " + user + "'");
				app_logs.info("'Booked By: " + user + "'");
				Assert.assertTrue(pdfFileText.contains("Booked By : " + user), "Failed: Booked BY Date Not Found");
				testSteps.add("Verified Booked By user");
				app_logs.info("Verified Booked By user");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to Gust", scriptName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to close opened reservation", scriptName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			testSteps.add("==========DELETING DOCUMENT TEMPLATE==========");
			app_logs.info("==========DELETING DOCUMENT TEMPLATE==========");

			navigation.navigateSetup(driver);
			testSteps.add("Navigate Setup");
			app_logs.info("Navigate Setup");

			navigation.navigateDocumentTemplate(driver);
			testSteps.add("Navigate Document Template");
			app_logs.info("Navigate Document Template");

			getTestSteps.clear();
			documentTemplates.deleteDocument(driver, documentName, getTestSteps);
			testSteps.addAll(getTestSteps);
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to close opened reservation", scriptName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(scriptName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, testSteps);
				Utility.updateReport(e, "Failed to close opened reservation", scriptName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getData("TapChartSchedulingInAssignedRes", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		 driver.quit();
	}

}
