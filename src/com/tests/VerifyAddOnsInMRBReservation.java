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
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.RatePackage;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;

public class VerifyAddOnsInMRBReservation extends TestCore {
	private WebDriver driver = null;
	public static String testName = "";
	public static String testDescription = "";
	public static String testCatagory = "";
	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();

	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void verifyAddOnsInMRBReservation(String adults, String children, String rateplan, String promoCode,
			String roomCharge, String roomClass, String IsAssign, String salutation, String guestFirstName,
			String guestLastName, String phoneNumber, String altenativePhone, String email, String account,
			String accountType, String address1, String address2, String address3, String city, String country,
			String state, String postalCode, String isGuesProfile, String PaymentType, String CardNumber,
			String NameOnCard, String CardExpDate, String IsChangeInPayAmount, String ChangedAmountValue,
			String TravelAgent, String MarketSegment, String referral, String IsGuesRegistration,
			String IsRoomClassWithRoomNO, String CheckInStatus, String lineItemCategory, String lineItemDesciption,
			String packageName, String packageDescription, String packageAmount, String packageCategory,
			String addOnsQuentity, String historyCategory, String pendingState, String notes, String voidState)
			throws InterruptedException, IOException {

		testName = "VerifyAddOnsInMRBReservation";
		testDescription = "Verify Addons module in the Reservations page with Addons<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682415' target='_blank'>"
				+ "Click here to open TestRail: C682412</a><br>" + "Verify click on remove icon for Add-on<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682415' target='_blank'>"
				+ "Click here to open TestRail: C682415</a><br>"
				+ "Verify the Drop Down should be shown to specify Room on adding an Add-ons & Incidental<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682456' target='_blank'>"
				+ "Click here to open TestRail: C682456</a>";
		testCatagory = "AddOns";

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		CPReservationPage reservation = new CPReservationPage();
		Folio folio = new Folio();
		Navigation navigation = new Navigation();
		RatePackage packageRate = new RatePackage();
		Rate rate = new Rate();

		String randomNumber = Utility.GenerateRandomNumber();
		guestLastName = guestLastName + randomNumber;
		String reservationNumber = null;
		String status = "";
		String firstRoomNumber = "";
		String secondRoomNumber = "";
		String roomClassAbv = "DBR";
		String totalamount = "";
		String firstIndcidentalAmount = "";
		String totalAmoutForSecondRoom = "";
		String secondIndcidentalAmount = "";
		String folioName = "";
		String itemRow = "Item row";

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
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
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

		// Delete rate
		try {

			app_logs.info("==========DELETE ALL RATES THAT START WITH NAME OF " + packageName + "==========");
			testSteps.add("==========DELETE ALL RATES THAT START WITH NAME OF " + packageName + "==========");

			navigation.Inventory(driver);
			testSteps.add("Click on Inventory");

			// create new method
			getTestSteps.clear();
			getTestSteps = navigation.secondaryRatesMenuItem(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = packageRate.clickOnPackageTab(driver);
			testSteps.addAll(getTestSteps);

			// start new method for delete rate
			getTestSteps.clear();
			getTestSteps = rate.deleteRates(driver, packageName);
			testSteps.addAll(getTestSteps);
			testSteps.add("New Rate has been Deleted successfully");
			app_logs.info("New Rate has been Deleted successfully");

			rate.verifyDeleteRate(driver, packageName);
			testSteps.add("Verify the Deleted Rates that started with name of : " + packageName);
			app_logs.info("Verify the Deleted Rates that started with name of : " + packageName);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to Delete rate", testName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to Delete rate", testName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========CREATE NEW PACKAGE RATE WITH WITH ADD ONS==========");
			testSteps.add("==========CREATE NEW PACKAGE RATE WITH WITH ADD ONS==========");
			packageName = packageName + randomNumber;
			getTestSteps.clear();
			getTestSteps = packageRate.packageDetails(driver, packageName, rateplan);
			testSteps.addAll(getTestSteps);

		}

		catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to Enter Package Details", testName, "PackageDetails", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to Enter Package Details", testName, "PackageDetails", driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		try

		{

			getTestSteps.clear();
			getTestSteps = packageRate.packageComponentWithAddOn(driver, lineItemCategory, packageAmount);
			testSteps.addAll(getTestSteps);
		}

		catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to Enter Package components", testName, "PackageComponents", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to Enter Package components", testName, "PackageComponents", driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			getTestSteps.clear();
			getTestSteps = packageRate.packageDescriptiveInformation(driver, packageName, packageName, packageName);
			testSteps.addAll(getTestSteps);

		}

		catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to Enter Package descriptive information", testName,
						"PackageDescriptiveInformation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to Enter Package descriptive information", testName,
						"PackageDescriptiveInformation", driver);
			} else {
				Assert.assertTrue(false);
			}

		}

		try {
			getTestSteps.clear();
			getTestSteps = packageRate.clickOnAlwaysAvailableRate(driver);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to associated the Rate", testName, "Package", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to associated the Rate", testName, "Package", driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			getTestSteps.clear();
			getTestSteps = packageRate.SavePackageRate(driver);
			testSteps.addAll(getTestSteps);

			rate.SearchRate(driver, packageName, false);
			testSteps.add("New Rate With Name : " + packageName + " Created With & Verified ");
			app_logs.info("New Rate With Name : " + packageName + " Created With & Verified ");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to save Package", testName, "Package", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to save Package ", testName, "Package", driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========CREATING RESERVATION==========");
			testSteps.add("==========CREATING RESERVATION==========");

			navigation.navigateToReservations(driver);
			testSteps.add("Navigate to reservation");
			app_logs.info("Navigate to reservation");
			reservation.click_NewReservation(driver, testSteps);

			getTestSteps.clear();
			getTestSteps = reservation.clickAddARoom(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkInDates(driver, 0, 0);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkOutDates(driver, +1, 0);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkInDates(driver, +1, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkOutDates(driver, +2, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.clickOnFindRooms(driver, testSteps);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.selectRoom(driver, getTestSteps, roomClass, IsAssign, account);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.selectRoom(driver, getTestSteps, roomClass, IsAssign, account);
			testSteps.addAll(getTestSteps);

			reservation.moveToAddARoom(driver);
			getTestSteps.clear();
			getTestSteps = reservation.clickNext(driver, getTestSteps);
			testSteps.addAll(getTestSteps);

			firstRoomNumber = reservation.selectRoomNumberInMRB(driver, roomClass, 0, 0);
			String SplitStr[] = firstRoomNumber.split(":");
			firstRoomNumber = SplitStr[1].trim();
			app_logs.info("First room number: " + firstRoomNumber);

			getTestSteps.clear();
			getTestSteps = reservation.verifyGuestProfileCheckoxChecked(driver, Boolean.parseBoolean(isGuesProfile));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			reservation.enter_GuestName(driver, getTestSteps, salutation, guestFirstName, guestLastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.checkboxCopyGuest(driver);
			testSteps.addAll(getTestSteps);

			secondRoomNumber = reservation.selectRoomNumberInMRB(driver, roomClass, 0, 1);
			SplitStr = secondRoomNumber.split(":");
			secondRoomNumber = SplitStr[1].trim();
			app_logs.info("SecondReservationNumber: " + secondRoomNumber);

			getTestSteps.clear();
			getTestSteps = reservation.selectReferral(driver, referral);
			testSteps.addAll(getTestSteps);

			reservation.clickBookNow(driver, testSteps);
			reservationNumber = reservation.get_ReservationConfirmationNumber(driver, testSteps);
			status = reservation.get_ReservationStatus(driver, testSteps);
			reservation.clickCloseReservationSavePopup(driver, testSteps);

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create new reservation ", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to create new reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========ADDING ADD ONS IN " + roomClassAbv + ": " + firstRoomNumber + "==========");
			testSteps.add("==========ADDING ADD ONS IN " + roomClassAbv + ": " + firstRoomNumber + "==========");

			getTestSteps.clear();
			getTestSteps = reservation.clickAddOnsButton(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.addingAddOns(driver, packageName, packageAmount, addOnsQuentity, true,
					roomClassAbv + ": " + firstRoomNumber);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to ad new add-on", testName, "Add-On", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to ad new add-on", testName, "Add-On", driver);

			} else {
				Assert.assertTrue(false);
			}
		}
		// Verify ADD ONS IN STAY INFO

		try {
			testSteps.add("==========VERIFICATION ADDONS IN STAY INFO FOR " + roomClassAbv + ": " + secondRoomNumber
					+ "==========");
			String Date = reservation.getIncidentalDate(driver, 0);
			String getPastDate = ESTTimeZone.getDateIncidental(0, true);
			testSteps.add("Expected date: " + getPastDate);
			testSteps.add("Found: " + Date);
			assertEquals(Date, getPastDate, "Failed: date is mismatching in stay info!");
			testSteps.add("Verified date");

			String expectedRoomNumber = firstRoomNumber + " (" + roomClassAbv + ")";
			String RoomNumber = reservation.getRoomNumber(driver, 0);
			testSteps.add("Expected room number: " + expectedRoomNumber);
			testSteps.add("Found: " + RoomNumber);
			assertEquals(RoomNumber, expectedRoomNumber, "Failed: categiry is mismatching in stay info!");
			testSteps.add("Verified room number with room class abbreviation");

			String category = reservation.getIncidentalCategory(driver, 0);
			testSteps.add("Expected category: " + packageCategory);
			testSteps.add("Found: " + category);
			assertEquals(category, packageCategory, "Failed: categiry is mismatching in stay info!");
			testSteps.add("Verified category");

			String descritption = reservation.getIncidentalDescritption(driver, 0);
			testSteps.add("Expected description: " + packageName);
			testSteps.add("Found: " + descritption);
			assertEquals(descritption, packageName, "Failed: Description is mismatching in stay info!");
			testSteps.add("Verified description");

			firstIndcidentalAmount = folio.Incidenatl(packageAmount, addOnsQuentity);

			String perUnit = reservation.getIncidentalPerUnit(driver, 0);
			testSteps.add("Expected per unit: " + packageAmount);
			testSteps.add("Found: " + perUnit);
			assertEquals(perUnit, "$ " + packageAmount, "Failed: Per unit is mismatching in stay info!");
			testSteps.add("Verified per unit");

			String tax = folio.getIncidentalTax(driver, 0);
			testSteps.add("Found tax: " + tax);

			String getIncidentalTotalAmount = folio.getIncidentalTotalAmount(driver, 0);
			totalamount = folio.AddValue(firstIndcidentalAmount, folio.splitString(tax));
			app_logs.info("totalamount: " + totalamount);
			totalamount = "$ " + totalamount;

			testSteps.add("Expected Amount after included tax: " + totalamount);
			testSteps.add("Found: " + getIncidentalTotalAmount);
			assertEquals(getIncidentalTotalAmount, totalamount, "Failed: Amount is mismatching in stay info!");
			testSteps.add("Verified amount after include tax");

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify AddOns in stay info", testName, "AddONS", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify AddOns in stay info", testName, "AddONS", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			app_logs.info("==========ADDING ADD ONS IN " + roomClassAbv + ": " + secondRoomNumber + "==========");
			testSteps.add("==========ADDING ADD ONS IN " + roomClassAbv + ": " + secondRoomNumber + "==========");

			getTestSteps.clear();
			getTestSteps = reservation.clickAddOnsButton(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.addingAddOns(driver, packageName, packageAmount, addOnsQuentity, true,
					roomClassAbv + ": " + secondRoomNumber);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to ad new add-on", testName, "Add-On", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to ad new add-on", testName, "Add-On", driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			testSteps.add("==========VERIFICATION ADDONS IN STAY INFO FOR " + roomClassAbv + ": " + secondRoomNumber
					+ "==========");
			String date = reservation.getIncidentalDate(driver, 0);
			String getPastDate = ESTTimeZone.getDateIncidental(0, true);
			testSteps.add("Expected date: " + getPastDate);
			testSteps.add("Found: " + date);
			assertEquals(date, getPastDate, "Failed: date is mismatching in stay info!");
			testSteps.add("Verified date");

			String expectedRoomNumber = firstRoomNumber + " (" + roomClassAbv + ")";
			String roomNumber = reservation.getRoomNumber(driver, 0);
			testSteps.add("Expected room number: " + expectedRoomNumber);
			testSteps.add("Found: " + roomNumber);
			assertEquals(roomNumber, expectedRoomNumber, "Failed: categiry is mismatching in stay info!");
			testSteps.add("Verified room number with room class abbreviation");

			String category = reservation.getIncidentalCategory(driver, 0);
			testSteps.add("Expected category: " + packageCategory);
			testSteps.add("Found: " + category);
			assertEquals(category, packageCategory, "Failed: categiry is mismatching in stay info!");
			testSteps.add("Verified category");

			String descritption = reservation.getIncidentalDescritption(driver, 0);
			testSteps.add("Expected description: " + packageName);
			testSteps.add("Found: " + descritption);
			assertEquals(descritption, packageName, "Failed: Description is mismatching in stay info!");
			testSteps.add("Verified description");

			secondIndcidentalAmount = folio.Incidenatl(packageAmount, addOnsQuentity);

			String perUnit = reservation.getIncidentalPerUnit(driver, 0);
			testSteps.add("Expected per unit: " + packageAmount);
			testSteps.add("Found: " + perUnit);
			assertEquals(perUnit, "$ " + packageAmount, "Failed: Per unit is mismatching in stay info!");
			testSteps.add("Verified per unit");

			String tax = folio.getIncidentalTax(driver, 0);
			testSteps.add("Found tax: " + tax);

			String getIncidentalTotalAmount = folio.getIncidentalTotalAmount(driver, 0);
			totalAmoutForSecondRoom = folio.AddValue(secondIndcidentalAmount, folio.splitString(tax));
			app_logs.info("totalamount: " + totalAmoutForSecondRoom);
			totalAmoutForSecondRoom = "$ " + totalAmoutForSecondRoom;

			testSteps.add("Expected Amount after included tax: " + totalAmoutForSecondRoom);
			testSteps.add("Found: " + getIncidentalTotalAmount);
			assertEquals(getIncidentalTotalAmount, totalAmoutForSecondRoom,
					"Failed: Amount is mismatching in stay info!");
			testSteps.add("Verified amount after include tax");

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify AddOns in second room number", testName, "AddONS", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify AddOns in second room number", testName, "AddONS", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		String ExpectedIncidenatl = "";

		try {
			testSteps.add("==========VERIFICATION OF INCIDENTILA IN TRIP SUMMERY==========");

			String getIncidentalInTripSummery = reservation.getIncidentalInTripSummery(driver);
			ExpectedIncidenatl = folio.AddValue(firstIndcidentalAmount, secondIndcidentalAmount);
			testSteps.add("Expected incidenatl in trip summery: $ " + ExpectedIncidenatl);
			testSteps.add("Found: " + getIncidentalInTripSummery);
			assertEquals(getIncidentalInTripSummery, "$ " + ExpectedIncidenatl, "Failed: Incidentail is mismatching!");
			testSteps.add("Verify incidential in trip summery");

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify incidental in trip summery", testName, "ADDONS", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify incidental in trip summery", testName, "ADDONS", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// ADD ONS IN HISTORY
		try {
			testSteps.add("==========VERIFICATION OF ADD ONS IN HISTORY FOR " + roomClassAbv + ": " + secondRoomNumber
					+ "==========");

			getTestSteps.clear();
			getTestSteps = reservation.clickOnHistory(driver);
			testSteps.addAll(getTestSteps);

			String getHistoryCategory = reservation.getHistoryCategory(driver, 0);
			testSteps.add("Expected category: " + historyCategory);
			testSteps.add("Found: " + getHistoryCategory);
			assertEquals(getHistoryCategory, historyCategory, "Failed: History category is mismatching!");
			testSteps.add("Verified category");

			String getHistoryDate = reservation.gettHistoryDate(driver, 0);
			String getDate = ESTTimeZone.DateFormateForLineItem(0);
			testSteps.add("Expected date: " + getDate);
			testSteps.add("Found: " + getHistoryDate);
			assertTrue(getDate.contains(getHistoryDate), "Failed: History date is mismatching!");
			testSteps.add("Verified date");

			String gettHistoryDescription = reservation.getHistoryDescription(driver, 0);
			String description = "Added " + packageCategory + " item " + packageName + " to Guest Folio";
			testSteps.add("Expected description: " + description);
			testSteps.add("Found: " + getHistoryDate);
			assertEquals(gettHistoryDescription, description, "Failed: History description is mismatching!");
			testSteps.add("Verified description");

			String expectedRoom = roomClassAbv + ": " + secondRoomNumber;
			String getHistoryRoom = reservation.gettHistoryRoom(driver, 0);
			testSteps.add("Expected room: " + expectedRoom);
			testSteps.add("Found: " + getHistoryRoom);
			assertEquals(getHistoryRoom, expectedRoom, "Failed: History room is mismatching!");
			testSteps.add("Verified room");

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify AddONS in hostory for second room number", testName, "ADDONS",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify AddONS in hostory for second room number", testName, "ADDONS",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// ADD ONS IN HISTORY
		try {
			testSteps.add("==========VERIFICATION OF ADD ONS IN HISTORY FOR " + roomClassAbv + ": " + firstRoomNumber
					+ "==========");

			String getHistoryCategory = reservation.getHistoryCategory(driver, 1);
			testSteps.add("Expected category: " + historyCategory);
			testSteps.add("Found: " + getHistoryCategory);
			assertEquals(getHistoryCategory, historyCategory, "Failed: History category is mismatching!");
			testSteps.add("Verified category");

			String getHistoryDate = reservation.gettHistoryDate(driver, 1);
			String getDate = ESTTimeZone.DateFormateForLineItem(0);
			testSteps.add("Expected date: " + getDate);
			testSteps.add("Found: " + getHistoryDate);
			assertTrue(getDate.contains(getHistoryDate), "Failed: History date is mismatching!");
			testSteps.add("Verified date");

			String gettHistoryDescription = reservation.getHistoryDescription(driver, 1);
			String description = "Added " + packageCategory + " item " + packageName + " to Guest Folio";
			testSteps.add("Expected description: " + description);
			testSteps.add("Found: " + getHistoryDate);
			assertEquals(gettHistoryDescription, description, "Failed: History description is mismatching!");
			testSteps.add("Verified description");

			String expectedRoom = roomClassAbv + ": " + firstRoomNumber;
			String getHistoryRoom = reservation.gettHistoryRoom(driver, 1);
			testSteps.add("Expected room: " + expectedRoom);
			testSteps.add("Found: " + getHistoryRoom);
			assertEquals(getHistoryRoom, expectedRoom, "Failed: History room is mismatching!");
			testSteps.add("Verified room");

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify AddONS in hostory", testName, "ADDONS", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify AddONS in hostory", testName, "ADDONS", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			testSteps.add("==========VERIFICATION IF ADD ONS IN FOLIO LINE ITEM==========");

			getTestSteps.clear();
			getTestSteps = folio.folioTab(driver);
			testSteps.addAll(getTestSteps);

			folio.verifyLineItemsState(driver, packageCategory, pendingState, 1);
			testSteps.add("Verify line itme in pending state after added");

			getTestSteps.clear();
			getTestSteps = folio.LineItemDate(driver, packageCategory, 0, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemCategory(driver, packageCategory);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.getDescroption(driver, packageCategory, packageName, false);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemQuentity(driver, packageCategory, addOnsQuentity);
			testSteps.addAll(getTestSteps);

			String getAmount = folio.getAmount(driver, packageCategory);
			testSteps.add("Expected amount after added tax: " + totalamount);
			testSteps.add("Found : " + getAmount);
			assertEquals(getAmount, totalamount, "Failed: Amount is mismatching!");
			testSteps.add("Verified amount after included tax");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to find incidental in line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to find incidental in line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			testSteps.add("=====VERIFICATION OF ADD 0NS IN ITEM DETAIL POPUP=====");
			folio.getDescroption(driver, packageCategory, packageName, true);
			testSteps.add("Click on item description");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to click on description", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to click on description", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// start verify item and tax row
		try {

			testSteps.add("==========VERIFICATION OF PACKAGE IN LINE ITEM DETAILS POPUP==========");

			folio.itemDetailsCategoryState(driver, packageCategory, pendingState, 1);
			testSteps.add("Verify line itme in pending state in ");

			getTestSteps.clear();
			getTestSteps = folio.dateItemDetails(driver, packageCategory, 1, itemRow, 0);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.ItemDetailsCategory(driver, packageCategory, 1, itemRow);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.itemDetailsDescroption(driver, packageCategory, packageName, false, 1, itemRow,
					"span");
			testSteps.addAll(getTestSteps);

			String getAmount = folio.getAmountItemDetails(driver, packageCategory, 1);
			testSteps.add("Expected amount: $ " + "0.00");
			testSteps.add("Found: " + getAmount);
			assertEquals(getAmount, "$ " + "0.00", "Failed: package amount is mismatching in item row");
			testSteps.add("Verified item amount");

			testSteps.add("==========VERIFICATION OF INCIDETAL IN LINE ITEM DETAILS POPUP==========");
			getTestSteps.clear();
			getTestSteps = folio.dateItemDetails(driver, lineItemCategory, 1, itemRow, 0);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.ItemDetailsCategory(driver, lineItemCategory, 1, itemRow);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.itemDetailsDescroption(driver, lineItemCategory, lineItemDesciption, false, 1, itemRow,
					"span");
			testSteps.addAll(getTestSteps);

			getAmount = folio.getAmountItemDetails(driver, lineItemCategory, 1);
			testSteps.add("Expected amount: $" + firstIndcidentalAmount);
			testSteps.add("Found: " + getAmount);
			assertEquals(getAmount, "$ " + firstIndcidentalAmount,
					"Failed: line item amount is mismatching in item row");
			testSteps.add("Verified item amount");

			String itemDetails_Incidental = folio.itemDetailsIncientals(driver);
			testSteps.add("Expected incidental amount: $ " + firstIndcidentalAmount);
			testSteps.add("Found: " + itemDetails_Incidental);
			assertEquals(itemDetails_Incidental, "$ " + firstIndcidentalAmount,
					"Failed: Incidental amount is mismatching in item details popup");

			getTestSteps.clear();
			getTestSteps = folio.cancelPopupButton(driver, true, false);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify item details popup", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify item details popup", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// select guest folio
		try {
			testSteps.add("==========CHANGE FOLIO==========");
			app_logs.info("==========CHANGE FOLIO==========");
			folioName = "Guest Folio For " + roomClassAbv + " : " + secondRoomNumber;
			getTestSteps.clear();
			getTestSteps = folio.changeFolioOption(driver, folioName);
			testSteps.addAll(getTestSteps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to change folio", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to change folio", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			testSteps.add("==========VERIFICATION IF ADD ONS IN FOLIO LINE ITEM FOR " + folioName + "==========");

			getTestSteps.clear();
			getTestSteps = folio.folioTab(driver);
			testSteps.addAll(getTestSteps);

			folio.verifyLineItemsState(driver, packageCategory, pendingState, 1);
			testSteps.add("Verify line itme in pending state after added");

			getTestSteps.clear();
			getTestSteps = folio.LineItemDate(driver, packageCategory, +1, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemCategory(driver, packageCategory);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.getDescroption(driver, packageCategory, packageName, false);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.LineItemQuentity(driver, packageCategory, addOnsQuentity);
			testSteps.addAll(getTestSteps);

			String getAmount = folio.getAmount(driver, packageCategory);
			testSteps.add("Expected amount: " + totalAmoutForSecondRoom);
			testSteps.add("Found : " + getAmount);
			assertEquals(getAmount, totalAmoutForSecondRoom, "Failed: Amount is mismatching!");
			testSteps.add("Verified amount");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to find incidental in line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to find incidental in line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			testSteps.add("=====VERIFICATION OF ADD 0NS IN ITEM DETAIL POPUP=====");
			folio.getDescroption(driver, packageCategory, packageName, true);
			testSteps.add("Click on item description");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to click on description", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to click on description", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// start verify item and tax row
		try {

			testSteps.add("==========VERIFICATION OF PACKAGE IN LINE ITEM DETAILS POPUP==========");

			folio.itemDetailsCategoryState(driver, packageCategory, pendingState, 1);
			testSteps.add("Verify line itme in pending state in ");

			getTestSteps.clear();
			getTestSteps = folio.dateItemDetails(driver, packageCategory, 1, itemRow, +1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.ItemDetailsCategory(driver, packageCategory, 1, itemRow);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.itemDetailsDescroption(driver, packageCategory, packageName, false, 1, itemRow,
					"span");
			testSteps.addAll(getTestSteps);

			String getAmount = folio.getAmountItemDetails(driver, packageCategory, 1);
			testSteps.add("Expected amount: $ " + "0.00");
			testSteps.add("Found: " + getAmount);
			assertEquals(getAmount, "$ " + "0.00", "Failed: package amount is mismatching in item row");
			testSteps.add("Verified item amount");

			testSteps.add("==========VERIFICATION OF INCIDETAL IN LINE ITEM DETAILS POPUP==========");
			getTestSteps.clear();
			getTestSteps = folio.dateItemDetails(driver, lineItemCategory, 1, itemRow, +1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.ItemDetailsCategory(driver, lineItemCategory, 1, itemRow);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = folio.itemDetailsDescroption(driver, lineItemCategory, lineItemDesciption, false, 1, itemRow,
					"span");
			testSteps.addAll(getTestSteps);

			getAmount = folio.getAmountItemDetails(driver, lineItemCategory, 1);
			testSteps.add("Expected amount: $ " + secondIndcidentalAmount);
			testSteps.add("Found: " + getAmount);
			assertEquals(getAmount, "$ " + secondIndcidentalAmount,
					"Failed: line item amount is mismatching in item row");
			testSteps.add("Verified item amount");

			String itemDetails_Incidental = folio.itemDetailsIncientals(driver);
			testSteps.add("Expected incidental amount: $ " + secondIndcidentalAmount);
			testSteps.add("Found: " + itemDetails_Incidental);
			assertEquals(itemDetails_Incidental, "$ " + secondIndcidentalAmount,
					"Failed: Incidental amount is mismatching in item details popup");

			getTestSteps.clear();
			getTestSteps = folio.cancelPopupButton(driver, true, false);
			testSteps.addAll(getTestSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify item details popup", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify item details popup", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// Delete INCIDENTAL IN STAY INFO
		try {
			testSteps.add("==========VERIFICATION DELETED ADD ONS IN STAY INFO==========");

			getTestSteps.clear();
			getTestSteps = reservation.clickOnDetails(driver);
			testSteps.addAll(getTestSteps);

			int getNumberOfIncidentalRowsBeforeDelete = reservation.getNumberOfIncidentalRows(driver);
			app_logs.info("getNumberOfIncidentalRowsBeforeDelete : " + getNumberOfIncidentalRowsBeforeDelete);

			getTestSteps.clear();
			getTestSteps = reservation.clickOnDeleteOncidentalButton(driver, 1);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservation.incidentalVoidpopup(driver, notes, 0);
			testSteps.addAll(getTestSteps);

			int getNumberOfIncidentalRowsAfterDelete = reservation.getNumberOfIncidentalRows(driver);
			app_logs.info("getNumberOfIncidentalRowsAfterDelete : " + getNumberOfIncidentalRowsAfterDelete);
			assertEquals(getNumberOfIncidentalRowsAfterDelete, getNumberOfIncidentalRowsBeforeDelete - 1,
					"Failed: Inciental did not remove");

			testSteps.add("Verified one row removed from AADD-ONS & INCIDENTALS in stay info");

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify delete incidental from saty info", testName, "Incidental",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify delete incidental from saty info", testName, "Incidental",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			testSteps.add("==========VERIFICATION OF INCIDENTILA IN TRIP SUMMERY AFTER DELETED ADD ONS==========");

			String getIncidentalInTripSummery = reservation.getIncidentalInTripSummery(driver);
			ExpectedIncidenatl = folio.AddValue(firstIndcidentalAmount, secondIndcidentalAmount);
			ExpectedIncidenatl = folio.MinseTwoValue(ExpectedIncidenatl, secondIndcidentalAmount);
			testSteps.add("Expected incidenatl in trip summery: $ " + ExpectedIncidenatl);
			testSteps.add("Found: " + getIncidentalInTripSummery);
			assertEquals(getIncidentalInTripSummery, "$ " + ExpectedIncidenatl, "Failed: Incidentail is mismatching!");
			testSteps.add("Verify incidential in trip summery");

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify incidental in trip summery", testName, "ADDONS", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify incidental in trip summery", testName, "ADDONS", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			testSteps.add("==========VERIFICATION OF DELETED ADD ONS IN HISTORY FOR " + roomClassAbv + ": "
					+ secondRoomNumber + "==========");

			getTestSteps.clear();
			getTestSteps = reservation.clickOnHistory(driver);
			testSteps.addAll(getTestSteps);

			String getHistoryCategory = reservation.getHistoryCategory(driver, 0);
			testSteps.add("Expected category: " + historyCategory);
			testSteps.add("Found: " + getHistoryCategory);
			assertEquals(getHistoryCategory, historyCategory, "Failed: History category is mismatching!");
			testSteps.add("Verified category");

			String getHistoryDate = reservation.gettHistoryDate(driver, 0);
			String getDate = ESTTimeZone.DateFormateForLineItem(0);
			testSteps.add("Expected date: " + getDate);
			testSteps.add("Found: " + getHistoryDate);
			assertTrue(getDate.contains(getHistoryDate), "Failed: History date is mismatching!");
			testSteps.add("Verified date");

			String gettHistoryDescription = reservation.getHistoryDescription(driver, 0);
			String description = "Removed " + packageCategory + " item " + packageName + " from Guest Folio";
			testSteps.add("Expected description: " + description);
			testSteps.add("Found: " + getHistoryDate);
			assertEquals(gettHistoryDescription, description, "Failed: History description is mismatching!");
			testSteps.add("Verified description");

			String expectedRoom = roomClassAbv + ": " + secondRoomNumber;
			String getHistoryRoom = reservation.gettHistoryRoom(driver, 0);
			testSteps.add("Expected room: " + expectedRoom);
			testSteps.add("Found: " + getHistoryRoom);
			assertEquals(getHistoryRoom, expectedRoom, "Failed: History room is mismatching!");
			testSteps.add("Verified room");

		} catch (Exception e) {

			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify AddONS in hostory for second room number", testName, "ADDONS",
						driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to verify AddONS in hostory for second room number", testName, "ADDONS",
						driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		// select guest folio
		try {

			getTestSteps.clear();
			getTestSteps = folio.folioTab(driver);
			testSteps.addAll(getTestSteps);

			testSteps.add("==========CHANGE FOLIO==========");
			app_logs.info("==========CHANGE FOLIO==========");
			folio.changeFolioOption(driver, folioName);
			getTestSteps.clear();

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to change folio", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to change folio", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			testSteps.add("==========VERIFICATION DELETED ADD ONS IN FOLIO LINE ITEM==========");

			folio.CheckboxDisplayVoidItem(driver, true);
			folio.verifyLineItemsState(driver, packageCategory, voidState, 1);
			testSteps.add("Verified deleted Add Ons is showing in folio line item in void state");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to find incidental in line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to find incidental in line item", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			reservation.closeReservationTab(driver);
			testSteps.add("Successfully close opened tab");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to close reservation", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to close reservation", testName, "Folio", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			app_logs.info("==========DELETE RATE==========");
			testSteps.add("==========DELETE RATE==========");

			navigation.Inventory(driver);
			app_logs.info("Navigate Inventory");
			testSteps.add("Navigate Inventory");

			getTestSteps.clear();
			getTestSteps = navigation.secondaryRatesMenuItem(driver);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = packageRate.clickOnPackageTab(driver);
			testSteps.addAll(getTestSteps);

			// start new method for delete rate
			getTestSteps.clear();
			getTestSteps = rate.deleteRates(driver, packageName);
			testSteps.addAll(getTestSteps);
			testSteps.add("New Rate has been Deleted successfully");
			app_logs.info("New Rate has been Deleted successfully");

			rate.verifyDeleteRate(driver, packageName);
			testSteps.add("Verify the Deleted Rates that started with name of : " + packageName);
			app_logs.info("Verify the Deleted Rates that started with name of : " + packageName);

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to Delete rate", testName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, testDescription, testCatagory, testSteps);
				Utility.updateReport(e, "Failed to Delete rate", testName, "Rates", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the sheetname provided
		return Utility.getData("VerifyAddOnsInMRBReservation", excel);
	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}

}
