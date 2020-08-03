package com.innroad.inncenter.tests;

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
import com.innroad.inncenter.pageobjects.Reports;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyReportsGeneratedIncludeFolioOptions extends TestCore{
	private WebDriver driver = null;
	public static String testName = "";
	public static String testDescription = "";
	public static String testCategory = "";
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
	public void verifyReportsGeneratedIncludeFolioOptions(String adults,String children,String ratePlan,String promoCode,
			String roomClassName, String roomClassDescription, String isAssign,String salutation,String guestFirstName,String guestLastName,String phoneNumber,String altenativePhone,String email,String account,String accountType,
			String address1,String address2,String address3,String city,String country,String state,String postalCode,String isGuesProfile,String paymentType,String cardNumber,String nameOnCard,String cardExpDate, String paymentTypeOption, String referral, String incidentalName, String description, String amount,
			String note, String includeTaxesInLineItemsCheckBox, String displayPendingItemsCheckBox, String includePendingItemsInTotalCheckBox, String displayVoidItemsCheckBox, String displayCCNumberInReportsCheckBox, String showAuthorizationsInReportCheckBox, String guestRegistration, String guestStatement) throws InterruptedException, IOException {

		testName = "VerifyReportsGeneratedIncludeFolioOptions";
		testDescription = "Verify the Reports generated include Folio options<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682438' target='_blank'>"
				+ "Click here to open TestRail: C682438</a>";
		testCategory = "SystemReport";
	
		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		CPReservationPage reservationPage = new CPReservationPage();
		Folio folio = new Folio();
		Reports reports = new Reports();

		String reservationNumber=null;
		String roomNumber = null;
		String randomNumber = Utility.GenerateRandomNumber();
		guestFirstName = guestFirstName + randomNumber;
		guestLastName = guestLastName + randomNumber;
		String folioRoomCharges=null,folioTaxes=null,folioIncidentals=null,folioTripTotal=null,folioBalance=null;

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
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
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
			test_steps.add("========== CREATE NEW RESERVATION ==========");
			app_logs.info("========== CREATE NEW RESERVATION ==========");

			getTest_Steps.clear();
			getTest_Steps = reservationPage.click_NewReservation(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = reservationPage.checkInDate(driver, 0);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = reservationPage.checkOutDate(driver, +1);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = reservationPage.enterAdult(driver, adults);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = reservationPage.enterChildren(driver, children);
			test_steps.addAll(getTest_Steps);

			
			getTest_Steps.clear();
			getTest_Steps = reservationPage.clickOnFindRooms(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			
			getTest_Steps.clear();
			getTest_Steps = reservationPage.selectRoom(driver, roomClassName, isAssign);
			test_steps.addAll(getTest_Steps);
			
			
			
			getTest_Steps.clear();
			getTest_Steps = reservationPage.clickNext(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			
			getTest_Steps.clear();
			getTest_Steps = reservationPage.VeriGuestProfileCheckox(driver, Boolean.parseBoolean(isGuesProfile));
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = reservationPage.enter_GuestName(driver, getTest_Steps, salutation, guestFirstName, guestLastName);
			test_steps.addAll(getTest_Steps);
			
			if((account.equalsIgnoreCase("")||account.isEmpty())) {
				reservationPage.enter_PaymentDetails(driver, test_steps, paymentType, cardNumber, nameOnCard, cardExpDate);
			}
			
			getTest_Steps.clear();
			getTest_Steps = reservationPage.selectReferral(driver, referral);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			reservationPage.clickBookNow(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			reservationNumber = reservationPage.get_ReservationConfirmationNumber(driver,
					getTest_Steps);
			test_steps.add("Reservation confirmation number: " + reservationNumber);
			
			reservationPage.get_ReservationStatus(driver, test_steps);
			
			getTest_Steps.clear();
			reservationPage.clickCloseReservationSavePopup(driver, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			
			roomNumber = reservationPage.get_RoomNumber(driver, test_steps, isAssign);	
			
			getTest_Steps.clear();
			getTest_Steps = reservationPage.verifyGuestReportLabelsValidations(driver);
			test_steps.addAll(getTest_Steps);

			folio.folioTab(driver);
			test_steps.add("Clicked Folio Tab");
			app_logs.info("Clicked Folio Tab");			
			
			
			folioRoomCharges=reservationPage.get_RoomChargeWithCurrency(driver, test_steps);
			folioRoomCharges = reservationPage.replaceCurrency(folioRoomCharges);
			
			folioIncidentals=reservationPage.get_InceidentalsWithCurrency(driver, test_steps);
			folioIncidentals = reservationPage.replaceCurrency(folioIncidentals);
			
			folioTaxes=reservationPage.get_TaxesWithCurrency(driver, test_steps);
			folioTaxes = reservationPage.replaceCurrency(folioTaxes);
			
			folioTripTotal= reservationPage.get_TotalChargesWithCurrency(driver, test_steps);
			folioTripTotal = reservationPage.replaceCurrency(folioTripTotal);
			
			folioBalance=reservationPage.get_BalanceWithCurrency(driver, test_steps);
			folioBalance = reservationPage.replaceCurrency(folioBalance);
			
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to create new reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to create new reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		try {
			test_steps.add("========== ADDING NEW LINE ITEM CATEGORY : " + incidentalName + " IN GUEST FOLIO ==========");
			app_logs.info("========== ADDING NEW LINE ITEM CATEGORY : " + incidentalName + " IN GUEST FOLIO ==========");
			
			getTest_Steps.clear();		
			getTest_Steps = folio.clickAddLineItemButton(driver);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();		
			getTest_Steps = folio.AddFolioLineItem(driver, incidentalName, amount);
			test_steps.addAll(getTest_Steps);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to Add Line Item", testName, "AddLineItem", driver);
		
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to Add Line Item", testName, "AddLineItem", driver);
		
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			test_steps.add("========== MAKING LINE ITEM (" + incidentalName + ") STATUS TO VOID ==========");
			app_logs.info("========== MAKING LINE ITEM (" + incidentalName + ") STATUS TO VOID ==========");

			getTest_Steps.clear();		
			getTest_Steps = folio.voidLineItem(driver, incidentalName,note);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.ClickSaveFolioItemsButton(driver);
			test_steps.addAll(getTest_Steps);

			test_steps.add("========== MAKING PAYMENT ==========");
			app_logs.info("========== MAKING PAYMENT ==========");

			getTest_Steps.clear();
			getTest_Steps = folio.makePayment(driver, paymentTypeOption);
			test_steps.addAll(getTest_Steps);

		
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to void line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to void line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			test_steps.add("========== UnCheck All CheckBoxes ==========");
			app_logs.info("========== UnCheck All CheckBoxes ==========");
			
			getTest_Steps.clear();
			getTest_Steps = folio.clickFolioDetailsCheckBoxes(driver, includeTaxesInLineItemsCheckBox, false);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = folio.clickFolioDetailsCheckBoxes(driver, displayPendingItemsCheckBox, false);
			test_steps.addAll(getTest_Steps);		

			getTest_Steps.clear();
			getTest_Steps = folio.clickFolioDetailsCheckBoxes(driver, includePendingItemsInTotalCheckBox, false);
			test_steps.addAll(getTest_Steps);		

			getTest_Steps.clear();
			getTest_Steps = folio.clickFolioDetailsCheckBoxes(driver, displayVoidItemsCheckBox, false);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.clickFolioDetailsCheckBoxes(driver, displayCCNumberInReportsCheckBox, false);
			test_steps.addAll(getTest_Steps);		

			getTest_Steps.clear();
			getTest_Steps = folio.clickFolioDetailsCheckBoxes(driver, showAuthorizationsInReportCheckBox, false);
			test_steps.addAll(getTest_Steps);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to uncheck all checkboxes", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to uncheck all checkboxes", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try{			
			test_steps.add("========== VERIFYING GUEST STATEMENT WITH TAX BREAKDOWN REPORT WHILE ALL CHECKBOXES ARE UNCHECKED IN FOLIO DETAIL SECTION ==========");
			app_logs.info("========== VERIFYING GUEST STATEMENT WITH TAX BREAKDOWN REPORT WHILE ALL CHECKBOXES ARE UNCHECKED IN FOLIO DETAIL SECTION ==========");

			getTest_Steps.clear();
			getTest_Steps = reservationPage.clickGuestStatementWithTaxBreakdown(driver);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = reports.verifyGuestsReport(driver, guestStatement, incidentalName, description, amount, reservationNumber,roomClassName, roomClassDescription,roomNumber,folioTaxes, folioRoomCharges, paymentType);
			test_steps.addAll(getTest_Steps);

			test_steps.add("Guest Statement with Tax Breakdown report verified");
			app_logs.info("Guest Statement with Tax Breakdown report verified");
			
			} catch (Exception e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
					Utility.updateReport(e,
							"Failed to verify guestStatement report while all checkboxes are unchecked", testName,
							"SystemReport", driver);
				} else {
					Assert.assertTrue(false);
				}
			} catch (Error e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
					Utility.updateReport(e,
							"Failed to verify guestStatement report while all checkboxes are unchecked", testName,
							"SystemReport", driver);
				} else {
					Assert.assertTrue(false);
				}
		
			}


		try{			
			test_steps.add("========== VERIFYING GUEST REGISTRATION WITH TAX BREAKDOWN REPORT WHILE ALL CHECKBOXES ARE UNCHECKED IN FOLIO DETAIL SECTION ==========");
			app_logs.info("========== VERIFYING GUEST REGISTRATION WITH TAX BREAKDOWN REPORT WHILE ALL CHECKBOXES ARE UNCHECKED IN FOLIO DETAIL SECTION ==========");


			getTest_Steps.clear();
			getTest_Steps = reservationPage.clickGuestRegistartionTaxBreakdown(driver);
			test_steps.addAll(getTest_Steps);
			
			
			getTest_Steps.clear();
			getTest_Steps = reports.verifyGuestsReport(driver, guestRegistration, incidentalName, description, amount, reservationNumber,roomClassName, roomClassDescription,roomNumber,folioTaxes, folioRoomCharges, paymentType);
			test_steps.addAll(getTest_Steps);

			test_steps.add("Guest Registration with Tax Breakdown report verified");
			app_logs.info("Guest Registration with Tax Breakdown report verified");
			
			} catch (Exception e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
					Utility.updateReport(e,
							"Failed to verify guestRegistation report while all checkboxes are unchecked", testName,
							"SystemReport", driver);
				} else {
					Assert.assertTrue(false);
				}
			} catch (Error e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
					Utility.updateReport(e,
							"Failed to verify guestRegistation report while all checkboxes are unchecked", testName,
							"SystemReport", driver);
				} else {
					Assert.assertTrue(false);
				}
		
			}


		try {
			test_steps.add("========== Check All CheckBoxes ==========");
			app_logs.info("========== Check All CheckBoxes ==========");
			
			getTest_Steps.clear();
			getTest_Steps = folio.clickFolioDetailsCheckBoxes(driver, includeTaxesInLineItemsCheckBox, true);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = folio.clickFolioDetailsCheckBoxes(driver, displayPendingItemsCheckBox, true);
			test_steps.addAll(getTest_Steps);		

			getTest_Steps.clear();
			getTest_Steps = folio.clickFolioDetailsCheckBoxes(driver, includePendingItemsInTotalCheckBox, true);
			test_steps.addAll(getTest_Steps);		

			getTest_Steps.clear();
			getTest_Steps = folio.clickFolioDetailsCheckBoxes(driver, displayVoidItemsCheckBox, true);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = folio.clickFolioDetailsCheckBoxes(driver, displayCCNumberInReportsCheckBox, true);
			test_steps.addAll(getTest_Steps);		

			getTest_Steps.clear();
			getTest_Steps = folio.clickFolioDetailsCheckBoxes(driver, showAuthorizationsInReportCheckBox, true);
			test_steps.addAll(getTest_Steps);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to check all checkboxes", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
				Utility.updateReport(e, "Failed to check all checkboxes", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		try{			
			test_steps.add("========== VERIFYING GUEST REGISTRATION WITH TAX BREAKDOWN REPORT WHILE ALL CHECKBOXES ARE CHECKED IN FOLIO DETAIL SECTION ==========");
			app_logs.info("========== VERIFYING GUEST REGISTRATION WITH TAX BREAKDOWN REPORT WHILE ALL CHECKBOXES ARE CHECKED IN FOLIO DETAIL SECTION ==========");


			getTest_Steps.clear();
			getTest_Steps = reservationPage.clickGuestRegistartionTaxBreakdown(driver);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = reports.verifyGuestsReport(driver, guestRegistration, incidentalName, description, amount, reservationNumber,roomClassName, roomClassDescription,roomNumber,folioTaxes, folioRoomCharges, paymentType);
			test_steps.addAll(getTest_Steps);

			test_steps.add("Guest Registration With Tax Breakdown report Verified");
			app_logs.info("Guest Registration With Tax Breakdown report Verified");
			
			} catch (Exception e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
					Utility.updateReport(e,
							"Failed to verify guestRegistation report while all checkboxes are checked", testName,
							"SystemReport", driver);
				} else {
					Assert.assertTrue(false);
				}
			} catch (Error e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
					Utility.updateReport(e,
							"Failed to verify guestRegistation report while all checkboxes are checked", testName,
							"SystemReport", driver);
				} else {
					Assert.assertTrue(false);
				}
		
			}


		try{			
			test_steps.add("========== VERIFYING GUEST STATEMENT WITH TAX BREAKDOWN REPORT WHILE ALL CHECKBOXES ARE CHECKED IN FOLIO DETAIL SECTION ==========");
			app_logs.info("========== VERIFYING GUEST STATEMENT WITH TAX BREAKDOWN REPORT WHILE ALL CHECKBOXES ARE CHECKED IN FOLIO DETAIL SECTION ==========");

		
			getTest_Steps.clear();
			getTest_Steps = reservationPage.clickGuestStatementWithTaxBreakdown(driver);
			test_steps.addAll(getTest_Steps);
			
			getTest_Steps.clear();
			getTest_Steps = reports.verifyGuestsReport(driver, guestStatement, incidentalName, description, amount, reservationNumber,roomClassName, roomClassDescription,roomNumber,folioTaxes, folioRoomCharges, paymentType);
			test_steps.addAll(getTest_Steps);

			test_steps.add("Guest Statement With Tax Breakdown report Verified");
			app_logs.info("Guest Statement With Tax Breakdown report Verified");
			
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);

			} catch (Exception e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
					Utility.updateReport(e,
							"Failed to verify guestStatement report while all checkboxes are checked", testName,
							"SystemReport", driver);
				} else {
					Assert.assertTrue(false);
				}
			} catch (Error e) {
				if (Utility.reTry.get(testName) == Utility.count) {
					RetryFailedTestCases.count = Utility.reset_count;
					Utility.AddTest_IntoReport(testName, testDescription, testCategory, test_steps);
					Utility.updateReport(e,
							"Failed to verify guestStatement report while all checkboxes are checked", testName,
							"SystemReport", driver);
				} else {
					Assert.assertTrue(false);
				}
		
			}


	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getData("ReportsGeneratedIncludeFolio", excel);
	}

	@AfterClass(alwaysRun=true)
	public void closeDriver() {
		driver.quit();
	}

}
