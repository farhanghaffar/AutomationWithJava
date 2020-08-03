package com.innroad.inncenter.tests;

import java.util.ArrayList;
import java.util.List;

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
import com.innroad.inncenter.pageobjects.Reports;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyMRBGuestRegistrationReport extends TestCore {

	private WebDriver driver = null;
	ArrayList<String> test_catagory = new ArrayList<>();
	ArrayList<String> test_description = new ArrayList<>();
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> test_name = new ArrayList<>();

	String testName = this.getClass().getSimpleName().trim();
	String roomClassNameWithoutNum;
	String rateNameWithoutNum;
	String policyNameWithoutNum;
	String typeToValidate;
	String policyDesc;
	String policyText;
	ArrayList<String> totalNights = new ArrayList<>();
	ArrayList<String> roomBaseAmount = new ArrayList<>();
	String taxes;
	Double depositAmount = 0.0;
	Double paidDeposit = 0.0;
	String reservation = null;
	String status = null;
	// String guestFullName;
	ArrayList<String> roomNumber = new ArrayList<>();
	ArrayList<String> getRoomNumbers = new ArrayList<>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	Reports report = new Reports();
	String arrivalDateToValidate, depatureDateToValidate;

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Policy")
	public void verifyMRBGuestRegistrationReport(String checkInDate, String checkOutDate, String adultsForRes,
			String childrenForRes, String ratePlan, String roomClassName, String salutation, String guestFirstName,
			String guestLastName, String phoneNumber, String altenativePhone, String email, String account,
			String accountType, String address1, String address2, String address3, String city, String country,
			String state, String postalCode, String isGuesProfile, String paymentMethod, String cardNumber,
			String nameOnCard, String referral) throws Exception {

		String testDescription = "Verify Guest Registration report for Multi room reservationbr>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682473' target='_blank'>"
				+ "Click here to open TestRail: C682473</a><br/>";
		String testCatagory = "Reports";

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		CPReservationPage reservationPage = new CPReservationPage();
		Navigation navigation = new Navigation();
		Folio folio = new Folio();
		String primaryUser = null, address = null, cityStatePostalCode = null;
		String validateRoomwithRoomNumber1 = null, validateRoomwithRoomNumber2 = null,validereservationNumberInReport=null,
				roomNumber1 = null,roomNumber2=null, category = null, qty = null, tax = null, amount = null, description = null;
		String propertyDetailsAddress = null, proprtyAddress = null, propertyPhoneNumber = null, popertyFax=null,propertyFax =null, propertyEmail = null;
		new ArrayList<String>();
		driver = getDriver();
		
		{

			guestLastName = guestLastName + Utility.GenerateRandomNumber();
			;
			nameOnCard = guestFirstName + " " + guestLastName;
			primaryUser = guestFirstName.split("\\|")[0] + " " + guestLastName.split("\\|")[0];
			System.out.println(primaryUser);
			address = address1 + "," + " " + address2 + "," + " " + "-" + " " + address3;
			System.out.println(address);

			cityStatePostalCode = city + "," + " " + state.replace("Alaska", "AK") + " " + postalCode;
			System.out.println(cityStatePostalCode);
			String phoneNum = "Phone : " + phoneNumber;
			System.out.println(phoneNum);

			if (!(Utility.validateInput(checkInDate))) {
				for (int i = 0; i < guestFirstName.split("\\|").length; i++) {
					checkInDates.add(Utility.getCurrentDate("dd/MM/yyyy"));
					checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(2), "MM/dd/yyyy", "dd/MM/yyyy"));
				}
			} else {
				checkInDates = Utility.splitInputData(checkInDate);
				checkOutDates = Utility.splitInputData(checkOutDate);
			}
			for (int i = 0; i < checkInDates.size(); i++) {
				totalNights.add(Utility.differenceBetweenDates(checkInDates.get(i), checkOutDates.get(i)));
			}
			checkInDate = Utility.addInputDataToStringWithSeperator(checkInDates);
			checkOutDate = Utility.addInputDataToStringWithSeperator(checkOutDates);
		}
		ArrayList<String> dates = reservationPage.verifyReport(checkInDates.get(0), checkOutDates.get(0));
		arrivalDateToValidate = dates.get(0);
		depatureDateToValidate = dates.get(1);
		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);
			} else {
				Utility.reTry.replace(testName, 1);
			}
			login_CP(driver, test_steps);
			for (int i = 0; i < dates.size(); i++) {
				System.out.println(dates.get(i));
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		String masterCardExpDate = Utility.getFutureMonthAndYearForMasterCard(driver, test_steps);
		test_steps.add("========== Get the properties details==========");
		try {
			navigation.Setup(driver);
			navigation.Properties(driver);
			List<String> properttDetails = reservationPage.getPropertyDetails(driver, test_steps);
			System.out.println("properttDetails" + properttDetails);
			reservationPage.contactName(driver, test_steps);
			propertyEmail = reservationPage.propertyEmail(driver, test_steps);
			propertyPhoneNumber = reservationPage.propertyDetailsPhoneNumber(driver, test_steps);
			popertyFax = reservationPage.propertyDetailsPhoneNumber(driver, test_steps);
			propertyPhoneNumber ="Phone"+" :"+" "+"+1"+" "+propertyPhoneNumber;
			propertyFax ="Fax"+" :"+" "+"+1"+" "+popertyFax;
			System.out.println(propertyPhoneNumber);
			System.out.println(propertyFax);
			propertyDetailsAddress = reservationPage.propertyDetailsAddress(driver, test_steps);
			String propertyDetailsCity = reservationPage.propertyDetailsCity(driver, test_steps);
			reservationPage.propertyDetailsCountry(driver, test_steps);
			String propertyDetailsState = reservationPage.propertyDetailsState(driver, test_steps);
			String propertyDetailsPostalCode = reservationPage.propertyDetailsPostalCode(driver, test_steps);
			proprtyAddress = propertyDetailsCity + ","+" " + propertyDetailsState.replace("New York", "NY") + " " + propertyDetailsPostalCode.trim();
			//System.out.println(proprtyAddress);
			navigation.reservation(driver);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			test_steps.add("========== Creating new Reservation ==========");

			reservationPage.click_NewReservation(driver, test_steps);
			reservationPage.select_Dates(driver, test_steps, checkInDate, checkOutDate, adultsForRes, childrenForRes,
					ratePlan, "", "");
			reservationPage.clickOnFindRooms(driver, test_steps);
			reservationPage.selectRoom(driver, test_steps, roomClassName.split("\\|")[0], Utility.RoomNo,
					"");
			reservationPage.selectRoom(driver, test_steps, roomClassName.split("\\|")[1], Utility.RoomNo,
					"");
			depositAmount = reservationPage.deposit(driver, test_steps, "", "");
			reservationPage.clickNext(driver, test_steps);
			taxes = reservationPage.getTaxesAndServiceCharges_TripSummary(driver);
			depositAmount = reservationPage.deposit(driver, test_steps, "", "");

			reservationPage.enter_MRB_MailingAddress(driver, test_steps, salutation, guestFirstName, guestLastName,
					phoneNumber, altenativePhone, "", "", accountType, address1, address2, address3, city, country,
					state, postalCode, isGuesProfile, "", "", roomNumber);

			if ((account.equalsIgnoreCase("") || account.isEmpty())) {
				reservationPage.enter_PaymentDetails(driver, test_steps, paymentMethod, cardNumber, nameOnCard,
						masterCardExpDate);
			}
			reservationPage.enter_MarketSegmentDetails(driver, test_steps, "", "", referral);
			reservationPage.clickBookNow(driver, test_steps);
			reservation = reservationPage.get_ReservationConfirmationNumber(driver, test_steps);
			
			validereservationNumberInReport = " Reservation # :"+" "+ reservation;
			System.out.println(validereservationNumberInReport);
			status = reservationPage.get_ReservationStatus(driver, test_steps);
			reservationPage.clickCloseReservationSavePopup(driver, test_steps);
			reservationPage.get_TripSummaryRoomChargesWithCurrency(driver, test_steps);
			roomNumber1 = reservationPage.get_RoomNumber(driver, test_steps, "");
			roomNumber.add(roomNumber1);
			validateRoomwithRoomNumber1 = "Room :" +" "+ roomClassName.split("\\|")[0] +" "+ ":" +" "+ roomNumber1;
			
			System.out.println(validateRoomwithRoomNumber1);
			
			 roomNumber2 = reservationPage.getSecondRoomNumber(driver, test_steps, "");
			roomNumber.add(roomNumber2);
			validateRoomwithRoomNumber2 = "Room :" +" "+ roomClassName.split("\\|")[1] +" "+ ":" +" "+ roomNumber2;
			System.out.println(validateRoomwithRoomNumber2);
			
			reservationPage.getFristReservationRoomNumber(driver, test_steps);
			reservationPage.getFristReservationRoomCharge(driver, test_steps);
			reservationPage.getSecondReservationRoomNumber(driver, test_steps);
			reservationPage.getSecondReservationRoomCharge(driver, test_steps);
			reservationPage.click_Folio(driver, test_steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		test_steps.add("========== Get  the reservation  folio line item details==========");
		try {
			category = folio.verifyLineItemCategory(driver, "Room Charge", test_steps);
			folio.lineItemDate(driver, "Room Charge", 0, 1);
			qty = folio.lineItemQuentity(driver, "Room Charge", "1", 1);
			tax = folio.getLineTaxAmount(driver, "Room Charge");
			amount = folio.getAmount(driver, "Room Charge", 1);
			description = folio.getLineItemDescription(driver, category, 1, test_steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		test_steps.add("========== Verify the guest statement report details ==========");
		try {
			reservationPage.clickOnDropDownIcon(driver, test_steps);
			reservationPage.selectGuestStatements(driver, test_steps);
			reservationPage.selectAllRoom(driver, test_steps);
			String fileName = Utility.download_status(driver);
			report.verifyGuestStement(driver, fileName, propertyDetailsAddress, proprtyAddress, propertyPhoneNumber,propertyFax,
					propertyEmail, primaryUser, address, cityStatePostalCode, phoneNumber, arrivalDateToValidate,
					depatureDateToValidate, validateRoomwithRoomNumber1, validateRoomwithRoomNumber2, reservation, roomNumber1,roomNumber2,
					category, qty, tax, amount, description,checkInDates.get(0), checkOutDates.get(0), test_steps);
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, test_steps);
				Utility.updateReport(e, "Failed to click New reservation button", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {
		return Utility.getData("verifyMRBGuestRegistrationRep", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
