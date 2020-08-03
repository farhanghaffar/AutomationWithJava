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
import com.innroad.inncenter.pageobjects.ListManagement;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.RatePackage;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.Tax;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class VerifyTaxesOnPackageInCPRes extends TestCore {
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
	public void verifyTaxesOnPackageInCPRes(String url, String ClientCode, String Username, String Password,
			String CheckInDate, String CheckOutDate, String Adults, String Children, String Rateplan, String PromoCode,
			String RoomClass, String IsAssign, String IsDepositOverride, String DepositOverrideAmount,
			String Salutation, String GuestFirstName, String GuestLastName, String PhoneNumber, String AltenativePhone,
			String Email, String Account, String AccountType, String Address1, String Address2, String Address3,
			String City, String Country, String State, String PostalCode, String IsGuesProfile, String PaymentType,
			String CardNumber, String NameOnCard, String CardExpDate, String IsChangeInPayAmount,
			String ChangedAmountValue, String TravelAgent, String MarketSegment, String Referral, String IsAddNotes,
			String NoteType, String Subject, String Description, String IsTask, String TaskCategory, String TaskType,
			String TaskDetails, String TaskRemarks, String TaskDueon, String TaskAssignee, String TaskStatus,
			String PackageName, String PackageDisplayName, String PackageAmount, String PackageCategory,
			String PackagePolicy, String PackageDescription, String PackageCompDescription, String ratename,
			String maxAdults, String maxPersons, String baseAmount, String additionalAdult, String additionalChild,
			String rateDisplayName, String ratePolicy, String rateDescription, String RatePlanName, String RatePlnDesc,
			String RatePlanStatus, String TaxName, String taxLedgerAccount, String displayName, String description,
			String value, String category, String IsPercent, String bedsCount, String MaxAdults, String maxPersopns, String roomQuantity) throws InterruptedException {
		test_name = "VerifyTaxesOnPackageInCPRes";
		test_description = "Verify Taxes On Package In CP Res<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682515' target='_blank'>"
				+ "Click here to open TestRail: 682515</a>";
		test_category = "VerifyTaxes_OnPackage_InCPRes";
		String testName = test_name;

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");
		CPReservationPage res = new CPReservationPage();
		Navigation nav = new Navigation();
		RatePackage ratePackage = new RatePackage();
		Rate rate = new Rate();
		RoomClass roomclass = new RoomClass();
		ListManagement listManageMent = new ListManagement();
		Tax tax = new Tax();
		String randomString = "";
		String rateName = "";
		String rateplanName = "";
		ArrayList<String> taxCategories = new ArrayList<String>();
		String reservation = null;
		boolean excludeTaxExempt = true;
		boolean vat = false;
		double depositAmount = 0.0;
		String totalTaxChargesbeforesave = "";
		String totalRoomChargesBeforeResSave = "";
		double tripsummarytaxesafterressave = 0.0;
		String totalTripChargesbeforesave = "";
		double packagerate = 0.0;
		String roomClassName = "";
		String createdpercent = "";
		String tripSummaryTaxesWithCurrency = "";
		String TripTotalCharge = "";
		double totalRoomCharges = 0.0;
		String roomCharge = "";
		String packageName = "";
		String RcDelete = "";
		String rateDelete = "";
		String tName = "";
		ArrayList<String> createdTaxDescriptions = new ArrayList<String>(); 
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
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to create rate", "NONGS_Login", "create rate", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to create rate", "NONGS_Login", "Create rate", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			// create rate plan to be selected in package
			test_steps.add("<b> ************Creating RatePlan to Create Package</b>****************");
			nav.Setup(driver);
			test_steps.add("Navigate to setup page");
			nav.ListManagemnet(driver);
			test_steps.add("Navigate to List Management");
			randomString =Utility.GenerateRandomNumber();
			rateplanName = RatePlanName.replace("XXX", randomString);
			test_steps.add("rate plan to be created: "+ "<b>"+rateplanName+"</b>");
			listManageMent.ClickRatePlan(driver);
			test_steps.add("Click at rate plan link under List management");
			listManageMent.CreateRatePlan(driver, rateplanName, rateplanName, RatePlanStatus);
			
			listManageMent.SaveRatePlan(driver);
			test_steps.add("Click save button");
			test_steps.add("Rate plan created: "+ "<b>"+rateplanName+"</b>");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to create rate plan", "NONGS_Login", "Rate plan creation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to create rate plan", "NONGS_Login", "Rate plan creation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			// create package rate
			test_steps.add("<b> ************Creating Package Rate</b>****************");
			nav.Inventory(driver);
			test_steps.add("Navigate to Inventory");
			nav.Rate(driver);
			test_steps.add("Navigate to Rate");
			randomString =Utility.GenerateRandomNumber();
			packageName = PackageName.replace("XXX", randomString);
			test_steps.add("Package name to be created: "+ "<b>"+packageName+"</b>");
			ratePackage.package_details(driver, packageName, test_steps);
			ratePackage.selectRatePlan(driver, rateplanName);
			test_steps.add("Rate plan selected: "+ "<b>"+rateplanName+"</b>");
			// PackageCompDescription
			ratePackage.package_components(driver, PackageAmount, PackageCategory, test_steps);
			test_steps.add("Package category: "+ "<b>"+PackageCategory+"</b>");
			ratePackage.package_descriptiveInformation(driver, packageName, PackagePolicy, PackageDescription);
			ratePackage.assoCiateRate(driver, rateName, test_steps, roomClassName);
			 double packageamount= Double.parseDouble(PackageAmount.trim());
			 int adult = Integer.parseInt(Adults);
			 packagerate = packageamount * adult;
			 test_steps.add("Calculated pacckage rate for each day: "+"<b>"+packagerate+"</b>");
			 
			 //calculate package rate per adult perinterval
			 
			 //packagerate

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to create package rate", "NONGS_Login", "Create package", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to create package rate", "NONGS_Login", "Create package", driver);
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
							test_steps.add("<b>********** Creating New percent Tax****************</b>");
							randomString = Utility.GenerateRandomNumber();
							tName = TaxName.replace("XXX", randomString);
							tax.Click_NewItem(driver, test_steps);
							test_steps.add("Click at new tax item button");
							test_steps.add("<br>Create new taxes</br>");
									boolean percentage = true;
									test_steps.add("percent Tax name is: " + "<b>" + tName + " </b>");
									tax.createTax(driver, test, tName, tName, tName, value, category, taxLedgerAccount,
											excludeTaxExempt, percentage, vat);
									test_steps.add("Created tax Category: " + "<b>" + category + "</b>");
									createdTaxDescriptions.add(tName);
									test_steps.add("Created tax with percent amount: " + "<b>" + value + "</b>");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to create tax", "NONGS_Login", "create tax", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to create tax", "NONGS_Login", "create tax", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			// Reservation
				test_steps.add("<b>*****************Creating CP reservation</b>***************************");
				nav.Click_ReservationMenuFromTaxPage(driver);
				test_steps.add("Click at resrevation menue link ");

				res.click_NewReservation(driver, test_steps);
				getTest_Steps.clear();
				getTest_Steps = res.checkInDate(driver, 0);
				test_steps.addAll(getTest_Steps);
				getTest_Steps.clear();
				getTest_Steps = res.checkOutDate(driver, +2);
				test_steps.addAll(getTest_Steps);
				res.enter_Adults(driver, test_steps, Adults);
				res.enter_Children(driver, test_steps, Children);
				res.select_Rateplan(driver, test_steps, rateplanName, PromoCode);
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
				Utility.updateReport(e, "Failed to create Reservation", "NONGS_Login", "create reservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to create Reservation", "NONGS_Login", "create reservation", driver);
			} else {
				Assert.assertTrue(false);
			}

		}
		try {
			// After reservation save verify the validation
						test_steps.add("<b>*********** Calculating Overall Taxes************</b>");
						Wait.wait10Second();
						tripSummaryTaxesWithCurrency = res.get_TripSummaryTaxesWithCurrency(driver, test_steps);
						roomCharge = res.getRoomChargeUnderTripSummary(driver, test_steps);
						TripTotalCharge = res.get_TripSummaryTripTotalChargesWithCurrency(driver, test_steps);
							// Calculate tax percent which we have created
                                   int stayDays = 2;
									createdpercent = res.getPercentcalcvalueSingleItem(driver, value,
											totalRoomChargesBeforeResSave.replace("$", "").trim(), stayDays);
								

					// ------------------------------------
						// Making assertion of charges after reservation save and before save
									test_steps.add("<b>*******Verifying Incidental Assertion*******</b>");	
													
									String getIncidentalCaptured = res.getTotalIncidentals(driver);
									double totalIncidentalcaptured = Double.parseDouble(getIncidentalCaptured.replace("$", "").trim());
									double calculatedTotalIncidentals = packagerate * stayDays;
									Assert.assertEquals(totalIncidentalcaptured, calculatedTotalIncidentals);
									test_steps.add("Verifying total Incidental calculated over captured: " + "<b>" + calculatedTotalIncidentals
											+ "</b>");
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
							
							test_steps.add("Verifying totalTax after and before Reservation save: " + "<b>" + taxAfterSave + "</b>");
							totalRoomCharges = Double.parseDouble(totalRoomChargesBeforeResSave.replace("$", "").trim());
							Assert.assertEquals(roomChargeAfterResSave, totalRoomCharges);
						test_steps.add("Verifying total room charges before and after reservation saved: " + "<b>" + totalRoomCharges
								+ "</b>");
						Assert.assertEquals(tripTotalAfterResSave, totalTripAmountBeforeResSave);
						test_steps.add("Verifying totalTrip charges before before and after saved: " + "<b>"
								+ totalTripAmountBeforeResSave + "</b>");

		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Calculated total tax charge", "NONGS_Login", "tax claculation", driver);
			} else {
				Assert.assertTrue(false);
			}

		}
		try {
			//verify tax charges in folio tab
			test_steps.add(
					"<b>*************Making assertion of Created tax in Folio **************<b>");
						String createdPercentCalc = f.format(Double.parseDouble(createdpercent));
						double taxValCreated = Double.parseDouble(createdPercentCalc);
						res.click_Folio(driver, test_steps);
			 res.verifyChildLineItemTaxes(driver, test_steps, taxLedgerAccount,tName,taxValCreated);
			 
			test_steps.add("<b>*****************Verifying package is not Taxable********************</b>");
		    res.click_Folio(driver, test_steps);
		    
		    res.verifyLedgerAccountnotPresentInTaxDetailPopup( driver,test_steps, createdTaxDescriptions,PackageCategory);
				test_steps.add(
						"<b>*************Making assertion after Calculating each Taxes and with Total Captured tax **************<b>");
			 
			 res.getTotalTaxInResFolios(driver, taxLedgerAccount, test_steps);
				test_steps.add(
						"<b>*****************Verifying Charges of each Package Folio line itemms under Folio Tab********************</b>");
				res.verifypackagecharges(driver, test_steps, packagerate, PackageCategory);

				//----------------------Verifying Laundry is not available in tax ----------------
				

		}catch(Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed verify charges in each folio", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}catch(Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed verify charges in each folio", "NONGS_Login", "Login", driver);
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
			
			// added by Farhan
			getTest_Steps.clear();
			getTest_Steps = nav.secondaryRatesMenuItem(driver);
			test_steps.addAll(getTest_Steps);
			
			ratePackage.clickPackage(driver);
			test_steps.add("Click at package tab");
			rate.delete_rate(driver, packageName);
			test_steps.add("Deleted package"+ "<b>"+packageName+"</b>");
			nav.Rate(driver);
			rate.DeleteRate(driver, rateDelete);
			test_steps.add("Rate Deleted starting from: "+ "<b>"+rateDelete+"</b>");
			nav.Setup(driver);
			test_steps.add("Navigate to Setup");
			nav.RoomClass(driver);
			test_steps.add("Navigate to Room Classes");
			
			// updated by Farhan with new functions
			roomclass.searchClass(driver, RcDelete);
			test_steps.add("Searched room class: "+ "<b>"+RcDelete+"</b>");
			roomclass.deleteRoomClass(driver, RcDelete);
			test_steps.add("Room Class deleted starting from: "+ "<b>"+RcDelete+"</b>");
			// updated by Farhan with new functions
			
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
		return Utility.getData("VerifyTaxesOnPackageInCPRes", excel);
	}
	
	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		 driver.quit();

	}

}
