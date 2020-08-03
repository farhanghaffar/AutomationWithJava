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
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.Policies;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.Reservation;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.webelements.WebElements_Create_Reservation;

//Automation-1204
public class VerifyChangeStayDetailsOptionMRB extends TestCore{
	private WebDriver driver = null;
	public static String test_name = "";
	public static String test_description = "";
	public static String test_catagory = "";
	ArrayList<String> test_steps = new ArrayList<>();
	ArrayList<String> getTest_Steps = new ArrayList<>();
	ArrayList<String> checkInDates = new ArrayList<>();
	ArrayList<String> checkOutDates = new ArrayList<>();
	ArrayList<String> checkOutDates1 = new ArrayList<>();

	@BeforeTest(alwaysRun=true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "Reservation")
	public void verify_ChangeStayDetailsOptionMRB(String url, String ClientCode, 
			String Username, String Password,String CPolicyName,
			String CPolicyType,String CPolicy_attribute,String Percentage,String CPolicyText, 
			String CPolicyDesc,String Season,String RatePlan, String RoomClassName,
			String RoomClassAbb,String SecondRoomClass,String SecondmaxAdults,String SecondmaxPersons,	
			String SecondbaseAmount,String SecondAdditionalAdult,String SecondRoomClassAbb,
			String bedsCount, String roomQuantity, String maxAdults, String maxPersons,
			String CheckInDate,String CheckOutDate,String Adults,String Children,String PromoCode,String IsSplitRes,
			String IsAssign,String IsDepositOverride,String DepositOverrideAmount,String IsAddMoreGuestInfo,String Salutation,
			String GuestFirstName,String GuestLastName,String PhoneNumber,String AltenativePhone,String Email,String Account,String AccountType,
			String Address1,String Address2,String Address3,String City,String Country,String State,String PostalCode,String IsGuesProfile,String PaymentType,
			String CardNumber,String NameOnCard,String CardExpDate,String IsChangeInPayAmount,String ChangedAmountValue,
			String TravelAgent,String MarketSegment,String Referral,String IsAddNotes,String NoteType,String Subject,String Description,
			String RateName, String maxRateAdults, String MaxRatePersons, String RateAmount,String AdditionalRateAdult,String AdditionalRateChild,String RateDisplayName,String RatePolicy,
			String RateDescription,String RoomClass,String CheckInDateNewRoom, String CheckOutDateNewRoom, 
			String AdultsNewRoom, String ChildrenNewRoom, String RatePlanNewRoom, String RoomNewClass,String RateAmountNew

			)throws InterruptedException, IOException {

		test_name = "Verify_ChangeStayDetailsOptionMRB";
		test_description = "Verify the functionality of Change Stay Details option For Multi room<br>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/681842' target='_blank'>"
				+ "Click here to open TestRail: C681842</a>"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/682453' target='_blank'>"
				+ ", C682453</a>";
		test_catagory = "CPReservationCardPayments";
		String testName = test_name;

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		driver = getDriver();
		String RandomNumber  = Utility.GenerateRandomNumber();
		String rateNameToDelete = RateName;
		String policyNameToDelete = CPolicyName;
		String roomClassToDelete = RoomClass;
		Navigation nav = new Navigation();
		Policies policies = new Policies();
		RoomClass room_class = new RoomClass();
		CPReservationPage cpRes = new CPReservationPage();
		ReservationSearch search = new ReservationSearch();
		Rate rate = new Rate();

		boolean isPolicyExist = false;

		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);
			} else {
				Utility.reTry.replace(testName, 1);
			}
			
		    // Get CheckIN and Checkout Date
			if (!(Utility.validateInput(CheckInDate))) {
				for (int i = 0; i < GuestFirstName.split("\\|").length; i++) {
					checkInDates.add(Utility.getCurrentDate("dd/MM/yyyy"));
					checkOutDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(1), "MM/dd/yyyy", "dd/MM/yyyy"));
					checkOutDates1.add(Utility.parseDate(Utility.getDatePast_FutureDate(3), "MM/dd/yyyy", "dd/MM/yyyy"));
				}
			} else {
				checkInDates = Utility.splitInputData(CheckInDate);
				checkOutDates = Utility.splitInputData(CheckOutDate);
			}
			CheckInDate = checkInDates.get(0) + "|" + checkOutDates.get(0);
			CheckOutDate = checkOutDates.get(0) + "|" + checkOutDates1.get(1);
			CheckInDateNewRoom = checkInDates.get(0);
			CheckOutDateNewRoom = checkOutDates.get(0);
			
			app_logs.info(CheckInDate);
			app_logs.info(CheckOutDate);
			
		} catch (Exception e) {
	        e.printStackTrace();
	        if (Utility.reTry.get(testName) == Utility.count) {
	            RetryFailedTestCases.count = Utility.reset_count;
	            Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
	            Utility.updateReport(e, "Failed to Get CheckIN CheckOutDate", testName, "Reservation", driver);
	        } else {
	            Assert.assertTrue(false);
	        }
	    } catch (Error e) {
	        if (Utility.reTry.get(testName) == Utility.count) {
	            RetryFailedTestCases.count = Utility.reset_count;
	            Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
	            Utility.updateReport(e, "Failed to Get CheckIN CheckOutDate", testName, "Reservation", driver);
	        } else {
	            Assert.assertTrue(false);
	        }
	    }

		// Login
		try {
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

		// *********Delete the Rate if exist****************//

		try {
			test_steps.add("=====Delete Rate if exist=====");
			nav.Inventory(driver);
			nav.Rates(driver);
			test_steps.add("Navigated to Inventory_Rate");
			app_logs.info("Navigated to Inventory_Rate");

			getTest_Steps.clear();
			getTest_Steps = rate.deleteRateIfExist(driver, RateName, getTest_Steps);
			test_steps.addAll(getTest_Steps);
		}

		catch (Exception e) {
			if (Utility.reTry.get(test_name) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Delete Rate", testName, "DeleteRate", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(test_name) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Delete Rate", testName, "DeleteRate", driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		/*Delete if room class already exist 
		 *Create 2 new room class and assign one room class new policies*/

		try {
			test_steps.add("=====Delete Room Class if exist=====");
			nav.navigateToReservations(driver);
			test_steps.add("Navigate Reservation");
			app_logs.info("Navigate Reservation");

			nav.Setup(driver);
			test_steps.add("Navigate to Setup");
			app_logs.info("Navigate to Setup");

			nav.RoomClass(driver);
			test_steps.add("Navigate RoomClass");
			app_logs.info("Navigate RoomClass");

			room_class.SearchButtonClick(driver);
			test_steps.add("Click Search");
			app_logs.info("Click Search");

			getTest_Steps.clear();
			getTest_Steps = room_class.deleteRoomClassIfExist(driver, RoomClassName, getTest_Steps);
			test_steps.addAll(getTest_Steps);
		}

		catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Delete Room Class", testName, "DeleteRoomClass", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Delete Room Class", testName, "DeleteRoomClass", driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		// Search and delete policies if exist
		try {
			test_steps.add("==VERIFY ANY POLCIY EXIST WITH SPECIFIC NAME AND DELETE IT==");
			nav.Inventory(driver);
			test_steps.add("Navigated to Inventory");
			app_logs.info("Navigated to Inventory");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to navigate Inventory page", testName, "InventoryNavigation", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to navigate Inventory page", testName, "InventoryNavigation", driver);

			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			policies.ClickOnPolicies(driver);
			test_steps.add("Navigated to Policies");
			app_logs.info("Navigated to Policies");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to navigate to Policies page", testName, "PoliciesNavigation", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to navigate to Policies page", testName, "PoliciesNavigation", driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			// start method here for deleting room classes
			isPolicyExist = policies.SearchPolicyWithName(driver, CPolicyName);
			if (isPolicyExist) {
				getTest_Steps.clear();
				getTest_Steps = policies.DeletePolicy_1(driver, CPolicyName);
				test_steps.addAll(getTest_Steps);

			} else {
				test_steps.add("No policy exist with name of " + CPolicyName);
			}
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to search and delete policies", testName, "SearchPolicies", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "ailed to search and delete policies", testName, "SearchPolicies", driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		// *******************Create RoomClass **************** //
		try {
			nav.Setup(driver);
			app_logs.info("=====CREATE ROOM CLASS=====");
			test_steps.add("=====CREATE ROOM CLASS=====");
			nav.RoomClass(driver);
			RoomClassName = RoomClassName + RandomNumber;
			RoomClassAbb = RoomClassAbb + RandomNumber;
			try {
				// Old Page Layout
				Utility.app_logs.info("try");

				nav.NewRoomClass(driver);
				getTest_Steps.clear();
				getTest_Steps = room_class.CreateRoomClass(driver, RoomClassName, RoomClassAbb, bedsCount, maxAdults,
						maxPersons, roomQuantity, test, getTest_Steps);
				test_steps.addAll(getTest_Steps);

				test_steps.add("Sccessfully Created New RoomClass " + RoomClassName + " Abb : " + RoomClassAbb);
				app_logs.info("Sccessfully Created New RoomClass" + RoomClassName + " Abb : " + RoomClassAbb);

			} catch (Exception e) {
				// New Page Layout
				Utility.app_logs.info("catch: "+e);
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create room class successfully", testName, "NewRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create room class successfully", testName, "NewRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// *******************Create Second RoomClass ****************/

		try {
			nav.Setup(driver);
			app_logs.info("=====CREATE Second ROOM CLASS=====");
			test_steps.add("=====CREATE Second ROOM CLASS=====");
			nav.RoomClass(driver);
			SecondRoomClass = SecondRoomClass + RandomNumber;
			SecondRoomClassAbb = SecondRoomClassAbb + RandomNumber;

			try {
				// Old Page Layout
				Utility.app_logs.info("try");

				nav.NewRoomClass(driver);
				getTest_Steps.clear();
				getTest_Steps = room_class.CreateRoomClass(driver, SecondRoomClass, SecondRoomClassAbb, bedsCount, SecondmaxAdults,
						SecondmaxPersons, roomQuantity, test, getTest_Steps);
				test_steps.addAll(getTest_Steps);

				test_steps.add("Sccessfully Created Second RoomClass " + SecondRoomClass + " Abb : " + SecondRoomClassAbb);
				app_logs.info("Sccessfully Created Second RoomClass" + SecondRoomClass + " Abb : " + SecondRoomClassAbb);
			} catch (Exception e) {
				// New Page Layout
				Utility.app_logs.info("catch:"+e);
			}

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create second room class successfully", testName, "NewRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to create second room class successfully", testName, "NewRoomClass", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		/*Create new rate*/

		// ******Navigate to Rate******//

		try {
			app_logs.info("=====CREATE NEW RATE=====");
			test_steps.add("=====CREATE NEW RATE=====");
			try {
				nav.Reservation_Backward_1(driver);
			} catch (Exception f) {
				nav.Reservation_Backward_3(driver);
			}
			nav.Inventory(driver);
			test_steps.add("Click On Inventory");
			app_logs.info("Click On Inventory");
			nav.Rate(driver);
			test_steps.add("Click On Rate");
			app_logs.info("Click On Rate");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Navigate rate", testName, "Navigation", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Navigate rate", testName, "Navigation", driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		//Enter the required details in create new rate//
		try {
			RateName = RateName + RandomNumber;
			getTest_Steps.clear();
			getTest_Steps = rate.EnterBasicInfo(driver, RateName, maxAdults, maxPersons, RateAmount, AdditionalRateAdult,
					AdditionalRateChild, RateName, RatePolicy, RateDescription);

			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = rate.SelectSeason(driver);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = rate.SelectRoomClass(driver, RoomClassName);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = rate.SelectRoomClass(driver, SecondRoomClass);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			getTest_Steps = rate.AttachSource(driver);
			test_steps.addAll(getTest_Steps);

			rate.SaveButton(driver);
			test_steps.add("Click on Save button");

			rate.Done_SaveButton(driver);
			test_steps.add("Click on Done button");

			rate.SearchRate(driver, RateName, false);
			test_steps.add("New Rate With Name : " + RateName + " Created With & Verified ");
			app_logs.info("New Rate With Name : " + RateName + " Created With & Verified ");
		}

		catch (Exception e) {
			if (Utility.reTry.get(test_name) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Create Rate", testName, "CreateRate", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(test_name) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Create Rate", testName, "CreateRate", driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		/* Creating new policy (each for CheckIn, No Show, Cancellation)*/
		try {
			nav.Inventory(driver);
			test_steps.add("Navigated to Inventory");
			app_logs.info("Navigated to Inventory");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to navigate Inventory page", testName, "InventoryNavigation", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to navigate Inventory page", testName, "InventoryNavigation", driver);

			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			policies.ClickOnPolicies(driver);
			test_steps.add("Navigated to Policies");
			app_logs.info("Navigated to Policies");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to navigate to Policies page", testName, "PoliciesNavigation", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to navigate to Policies page", testName, "PoliciesNavigation", driver);

			} else {
				Assert.assertTrue(false);
			}
		}
		// Create CheckIn Policy
		try {
			test_steps.add("==========CREATE NEW CHECKIN POLICY==========");

			// Create CheckIn Policy
			String CPolicy_checkin = CPolicyName+"_Checkin" + RandomNumber;
			CPolicyText = CPolicyText + "_Checkin";
			CPolicyDesc = CPolicyDesc + "_Checkin";

			getTest_Steps.clear();
			getTest_Steps = policies.NewPolicybutton(driver, CPolicyType, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			policies.Enter_Policy_Name(driver, CPolicy_checkin, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			test_steps.add("Enter check-in policy name : " + CPolicy_checkin);

			policies.Enter_Checkin_Policy_Attributes_1(driver, CPolicy_attribute, Percentage,CPolicyType);
			test_steps.add("Enter check-in policy attribute : " + CPolicy_attribute);
			test_steps.add("Enter check-in policy percentage : " + Percentage);

			policies.Enter_Policy_Desc(driver, CPolicyText, CPolicyDesc);
			test_steps.add("Enter check-in policy Policy Description : " + CPolicyDesc);

			policies.Associate_Sources(driver);
			test_steps.add("Attach associate source with check in policy");

			policies.AssociateSingle_Seasons(driver, Season);
			test_steps.add("Attach Season with check-in policy : " + Season);

			policies.AssociateSingle_RoomClass(driver, SecondRoomClass);
			test_steps.add("Attach RoomClass with check-in policy : " + SecondRoomClass);

			String[] ratePlan = RatePlan.split("\\|");
			policies.AssociateSingle_RatePlan(driver, ratePlan[0]);
			test_steps.add("Attach RatePlan with check-in policy : " + RatePlan);

			String message = policies.Save_Policy(driver);
			test_steps.add("Click on save button after enter all details for new check-in policy");
			test_steps.add("After click on save button, message appear  :" + message);

			policies.Close_Policy_Tab(driver);
			test_steps.add("Click on close tab and search created policy");

			policies.Verify_Policy(driver, CPolicy_checkin);
			test_steps.add("Successfully verify new created policy " + CPolicy_checkin);
			app_logs.info("Successfully verify new created policy " + CPolicy_checkin);

			test_steps.addAll(getTest_Steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to save the policy", testName, "PolicyDetails", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to save the policy", testName, "PolicyDetails", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Create Cancellation Policy
		try {
			test_steps.add("==========CREATE CANCELLATION POLICY==========");

			String CPolicy_cancellation = CPolicyName+"_Cancellation" + RandomNumber;
			String CPolicyType_cancel = "Cancellation";
			CPolicy_cancellation = CPolicy_cancellation + RandomNumber;
			CPolicyText = CPolicyText + "_Cancellation";
			CPolicyDesc = CPolicyDesc + "_Cancellation";

			getTest_Steps.clear();
			getTest_Steps = policies.NewPolicybutton(driver, CPolicyType_cancel, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			policies.Enter_Policy_Name(driver, CPolicy_cancellation, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			test_steps.add("Enter check-in policy name : " + CPolicy_cancellation);

			policies.Enter_Checkin_Policy_Attributes_1(driver, CPolicy_attribute, Percentage,CPolicyType_cancel);
			test_steps.add("Enter check-in policy attribute : " + CPolicy_attribute);
			test_steps.add("Enter check-in policy percentage : " + Percentage);

			policies.Enter_Policy_Desc(driver, CPolicyText, CPolicyDesc);
			test_steps.add("Enter check-in policy Policy Description : " + CPolicyDesc);

			policies.Associate_Sources(driver);
			test_steps.add("Attach associate source with check in policy");

			policies.AssociateSingle_Seasons(driver, Season);
			test_steps.add("Attach Season with check-in policy : " + Season);

			policies.AssociateSingle_RoomClass(driver, SecondRoomClass);
			test_steps.add("Attach RoomClass with check-in policy : " + SecondRoomClass);

			String[] ratePlan = RatePlan.split("\\|");
			policies.AssociateSingle_RatePlan(driver, ratePlan[0]);
			test_steps.add("Attach RatePlan with check-in policy : " + RatePlan);

			String message = policies.Save_Policy(driver);
			test_steps.add("Click on save button after enter all details for new check-in policy");
			test_steps.add("After click on save button, message appear  :" + message);

			policies.Close_Policy_Tab(driver);
			test_steps.add("Click on close tab and search created policy");

			policies.Verify_Policy(driver, CPolicy_cancellation);
			test_steps.add("Successfully verify new created policy " + CPolicy_cancellation);
			app_logs.info("Successfully verify new created policy " + CPolicy_cancellation);

			test_steps.addAll(getTest_Steps);
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to save the policy", testName, "PolicyDetails", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to save the policy", testName, "PolicyDetails", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		// Create No Show Policy
		try {
			test_steps.add("==========CREATE NO SHOW POLICY==========");

			// Create No Show Policy
			String CPolicy_noShow = CPolicyName+"_NoShow" + RandomNumber;
			String CPolicyType_cancel = "No Show";
			CPolicy_noShow = CPolicy_noShow + RandomNumber;
			CPolicyText = CPolicyText + "_NoShow";
			CPolicyDesc = CPolicyDesc + "_NoShow";

			getTest_Steps.clear();
			getTest_Steps = policies.NewPolicybutton(driver, CPolicyType_cancel, getTest_Steps);
			test_steps.addAll(getTest_Steps);

			getTest_Steps.clear();
			policies.Enter_Policy_Name(driver, CPolicy_noShow, getTest_Steps);
			test_steps.addAll(getTest_Steps);
			test_steps.add("Enter check-in policy name : " + CPolicy_noShow);

			policies.Enter_Checkin_Policy_Attributes_1(driver, CPolicy_attribute, Percentage,CPolicyType_cancel);
			test_steps.add("Enter check-in policy attribute : " + CPolicy_attribute);
			test_steps.add("Enter check-in policy percentage : " + Percentage);

			policies.Enter_Policy_Desc(driver, CPolicyText, CPolicyDesc);
			test_steps.add("Enter check-in policy Policy Description : " + CPolicyDesc);

			policies.Associate_Sources(driver);
			test_steps.add("Attach associate source with check in policy");

			policies.AssociateSingle_Seasons(driver, Season);
			test_steps.add("Attach Season with check-in policy : " + Season);

			policies.AssociateSingle_RoomClass(driver, SecondRoomClass);
			test_steps.add("Attach RoomClass with check-in policy : " + SecondRoomClass);

			String[] ratePlan = RatePlan.split("\\|");
			policies.AssociateSingle_RatePlan(driver, ratePlan[0]);
			test_steps.add("Attach RatePlan with check-in policy : " + RatePlan);

			String message = policies.Save_Policy(driver);
			test_steps.add("Click on save button after enter all details for new check-in policy");
			test_steps.add("After click on save button, message appear  :" + message);

			policies.Close_Policy_Tab(driver);
			test_steps.add("Click on close tab and search created policy");

			policies.Verify_Policy(driver, CPolicy_noShow);
			test_steps.add("Successfully verify new created policy " + CPolicy_noShow);
			app_logs.info("Successfully verify new created policy " + CPolicy_noShow);

			test_steps.addAll(getTest_Steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to save the policy", testName, "PolicyDetails", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to save the policy", testName, "PolicyDetails", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		//Create multi room reservation with created room classes.
		String reservation=null,status=null,TripSummaryRoomCharges=null,TripSummaryTaxes=null,TripSummaryIncidentals=null,TripSummaryTripTotal=null,TripSummaryPaid=null,TripSummaryBalance=null;
		Double depositAmount=0.0;
		ArrayList<String> Rooms = new ArrayList<String>();
		ArrayList<String> roomCost = new ArrayList<String>();

		// Reservation
		try {
			
			test_steps.add("===Creating Multi Room Reservation Details===");
			app_logs.info("===Creating Multi Room Reservation Details===");
			
			nav.navigateToReservations(driver);
			cpRes.click_NewReservation(driver, test_steps);
			test_steps.add("Click new Reservation button");
			app_logs.info("Click new Reservation button");

			cpRes.select_Dates(driver, test_steps, CheckInDate, CheckOutDate, Adults, Children, RatePlan, PromoCode,IsSplitRes);
			test_steps.add("Select Check In Date date " + CheckInDate+" and Check Out Date: "+CheckOutDate);
			app_logs.info("Select Check In Date date " + CheckInDate+" and Check Out Date: "+CheckOutDate);

			cpRes.clickOnFindRooms(driver, test_steps);
			test_steps.add("Click find rooms button");
			app_logs.info("Click find rooms button");

			String RoomClasses = RoomClassName+"|"+SecondRoomClass;
			app_logs.info("Select Room Class: "+RoomClasses);

			roomCost=cpRes.select_MRBRooms(driver, test_steps, RoomClasses, IsAssign,Account);
			test_steps.add("Select Room Class: "+RoomClasses);
			app_logs.info("Select Room Class: "+RoomClasses);

			depositAmount=cpRes.deposit(driver, test_steps, IsDepositOverride, DepositOverrideAmount);
			test_steps.add("Deposit Amount: "+depositAmount);
			app_logs.info("Deposit Amount: "+depositAmount );

			cpRes.clickNext(driver, test_steps);
			test_steps.add("Click next rooms button");
			app_logs.info("Click next rooms button");

			cpRes.enter_MRB_MailingAddress(driver, test_steps, Salutation, GuestFirstName, GuestLastName, PhoneNumber, AltenativePhone, Email, Account, AccountType, Address1, Address2, Address3, City, Country, State, PostalCode, IsGuesProfile, IsAddMoreGuestInfo, IsSplitRes,Rooms);
			test_steps.add("Enter Mailing Address");
			app_logs.info("Enter Mailing Address");

			if((Account.equalsIgnoreCase("")||Account.isEmpty())) {
				cpRes.enter_PaymentDetails(driver, test_steps, PaymentType, CardNumber, NameOnCard, CardExpDate);
			}
			//System.out.println(Rooms);

			cpRes.enter_MarketSegmentDetails(driver, test_steps, TravelAgent, MarketSegment, Referral);
			test_steps.add("Enter Market Segment Details");
			app_logs.info("Enter Market Segment Details");

			cpRes.clickBookNow(driver, test_steps);
			test_steps.add("Click Book Now Button");
			app_logs.info("Click Book Now Button");

			reservation=cpRes.get_ReservationConfirmationNumber(driver, test_steps);
			test_steps.add("Successfully Created Multi Room Reservation: " + reservation);
			app_logs.info("Successfully Created Multi Room Reservation: " + reservation);

			status=cpRes.get_ReservationStatus(driver, test_steps);
			test_steps.add("Reservation Status: "+status);
			app_logs.info("Reservation Status: "+status);

			cpRes.clickCloseReservationSavePopup(driver, test_steps);

			test_steps.add("===Verifying Multi Room Reservation Details===");
			app_logs.info("===Verifying Multi Room Reservation Details===");
						
			TripSummaryRoomCharges=cpRes.get_TripSummaryRoomChargesWithCurrency(driver, test_steps);
			test_steps.add("Trip Summary Room Charges With Currency: "+TripSummaryRoomCharges);
			app_logs.info("Trip Summary Room Charges With Currency: "+TripSummaryRoomCharges);

			TripSummaryTaxes=cpRes.get_TripSummaryTaxesWithCurrency(driver, test_steps);
			test_steps.add("Trip Summary Taxes: "+TripSummaryTaxes);
			app_logs.info("Trip Summary Taxes: "+TripSummaryTaxes);

			TripSummaryIncidentals=cpRes.get_TripSummaryInceidentalsWithCurrency(driver, test_steps);
			test_steps.add("Trip Summary Incidentals: " + TripSummaryIncidentals);
			app_logs.info("Trip Summary Incidentals: " + TripSummaryIncidentals);

			TripSummaryTripTotal=cpRes.get_TripSummaryTripTotalChargesWithCurrency(driver, test_steps);
			test_steps.add("Trip Summary Trip Total: " + TripSummaryTripTotal);
			app_logs.info("Trip Summary Trip Total: " + TripSummaryTripTotal);

			TripSummaryPaid=cpRes.get_TripSummaryPaidWithCurrency(driver, test_steps);
			test_steps.add("Trip Summary Paid: " + TripSummaryPaid);
			app_logs.info("Trip Summary Paid: " + TripSummaryPaid);

			TripSummaryBalance=cpRes.get_TripSummaryBalanceWithCurrency(driver, test_steps);
			test_steps.add("Trip Summary Balance: " + TripSummaryBalance);
			app_logs.info("Trip Summary Balance: " + TripSummaryBalance);

			cpRes.verify_MRB_StayInfo(driver, test_steps, CheckInDate, CheckOutDate, Adults, Children, RoomClasses, TripSummaryRoomCharges,roomCost,IsAssign,RatePlan);
			test_steps.add("Successfully Verify MRB StayInfo");
			app_logs.info("Successfully Verify MRB StayInfo");

			cpRes.validate_MRB_GuestInfo(driver, test_steps, Salutation, GuestFirstName, GuestLastName, PhoneNumber, AltenativePhone, Email, Country, Account, Address1, Address2, Address3, State, City, PostalCode);
			test_steps.add("Successfully validate MRB GuestInfo");
			app_logs.info("Successfully validate MRB GuestInfo");

			cpRes.validate_MRB_AdditionalGuestInfo(driver, test_steps, Salutation, GuestFirstName, GuestLastName, PhoneNumber, Email, Country);
			test_steps.add("Successfully validate MRB AdditionalGuestInfo");
			app_logs.info("Successfully validate MRB AdditionalGuestInfo");

			cpRes.verify_UpdatedByUser(driver, test_steps, Utility.login_CP.get(2));
			test_steps.add("Successfully verify Updated By User");
			app_logs.info("Successfully verify Updated By User");

			cpRes.get_AssociatedPoliciesToReservation(driver, test_steps);
			test_steps.add("Successfully Associated Policies To Reservation");
			app_logs.info("Successfully Associated Policies To Reservation");

			cpRes.click_History(driver, test_steps);
			cpRes.verify_ReservationInHistoryTab(driver, test_steps, reservation);
			test_steps.add("Successfully verify Reservation In History Tab");
			app_logs.info("Successfully verify Reservation In History Tab");

			if(depositAmount>0) {
				//cpRes.verify_DepositPaymentInHistoryTab(driver, test_steps, depositAmount);
				cpRes.verifyDepositPaymentInHistoryTab(driver, test_steps, depositAmount);
			}	

			cpRes.verify_GuestReportLabelsValidations(driver, test_steps);
			test_steps.add("Successfully verify Guest Report Labels Validations");
			app_logs.info("Successfully verify Guest Report Labels Validations");

			cpRes.close_FirstOpenedReservation(driver, test_steps);
			test_steps.add("Successfully close First Opened Reservation");
			app_logs.info("Successfully close First Opened Reservation");

			search.basicSearch_WithReservationNumber(driver, reservation);
			test_steps.add("Successfully Search reservation:  " + reservation);
			app_logs.info("Successfully Search reservation: " + reservation);

			cpRes.verify_MR_ToolTip(driver, test_steps, reservation);
			test_steps.add("Successfully verify tool tip ");
			app_logs.info("Successfully verify tool tip ");
			

		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Create Multi Room Reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Create Multi Room Reservation", testName, "Reservation", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		//Add another Room in reservation and verify Pop up Appear for cost Change
		try{
			test_steps.add("===Add another Room in reservation and verify Pop up===");
			app_logs.info("===Add another Room in reservation and verify Pop up===");
			
			//Open Reservation
			cpRes.openReservation(driver, test_steps);
			
			//Click on Details tab
			cpRes.click_DeatilsTab(driver, getTest_Steps);
			test_steps.add("Click on Details tab Successfull");
			app_logs.info("Click on Details tab Successfull");

			//Click on Add Rooms
			getTest_Steps.clear();
			getTest_Steps = cpRes.clickAddRooms(driver,getTest_Steps);
			test_steps.addAll(getTest_Steps);		

			cpRes.select_CheckInDate(driver, test_steps, CheckInDateNewRoom);		
			cpRes.select_CheckoutDate(driver, test_steps, CheckOutDateNewRoom);
			cpRes.enter_Adults(driver, test_steps, AdultsNewRoom);
			cpRes.enter_Children(driver, test_steps, ChildrenNewRoom);
			cpRes.select_Rateplan(driver, test_steps, RatePlanNewRoom,PromoCode);
			cpRes.clickOnFindRooms(driver, test_steps);
			cpRes.selectRoom(driver, test_steps, RoomNewClass, IsAssign,Account);
			cpRes.clickSaveButton(driver);
			test_steps.add("Save button Clicked");
			app_logs.info("Save button Clicked");

			//Verifying Pop up display dialog for Cost change 
			test_steps.add("===Verifying Pop up display dialog for Cost change===");
			app_logs.info("===Verifying Pop up display dialog for Cost change===");

			getTest_Steps.clear();
			getTest_Steps = cpRes.verifyPopupChangeInCost(driver,getTest_Steps);
			test_steps.addAll(getTest_Steps);
			app_logs.info(getTest_Steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to change room", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to change room", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		String TripSummaryRoomChargesAfter=null,TripSummaryIncidentalsAfter=null,
				TripSummaryTripTotalAfter=null,	TripSummaryPaidAfter=null,TripSummaryBalanceAfter=null;

		//Verify Newly Added Room Details
		try{	
			test_steps.add("===Verify New Added Room in Stay Info section===");
			app_logs.info("===Verify New Added Room in Stay Info section===");
			cpRes.verifyStayInfoNewRoom(driver, test_steps, CheckInDateNewRoom, CheckOutDateNewRoom, AdultsNewRoom, ChildrenNewRoom, RoomNewClass, RateAmountNew);

			test_steps.add("===Verify Additional Guest Info same as Primary Room's guest===");
			app_logs.info("===Verify Additional Guest Info same as Primary Room's guest===");
			cpRes.validateMRBAdditionalGuestInfo(driver, test_steps, Salutation, GuestFirstName, GuestLastName, PhoneNumber, Email, Country);
			
			test_steps.add("===Trip Summary Details After adding New Room Becomes===");
			app_logs.info("===Trip Summary Details After adding New Room Becomes===");
			TripSummaryRoomChargesAfter = cpRes.get_TripSummaryRoomChargesWithCurrency(driver, test_steps);
			TripSummaryIncidentalsAfter = cpRes.get_TripSummaryInceidentalsWithCurrency(driver, test_steps);			
			TripSummaryTripTotalAfter   = cpRes.get_TripSummaryTripTotalChargesWithCurrency(driver, test_steps);			
			TripSummaryPaidAfter        = cpRes.get_TripSummaryPaidWithCurrency(driver, test_steps);			
			TripSummaryBalanceAfter     = cpRes.get_TripSummaryBalanceWithCurrency(driver, test_steps);			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to change room", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to change room", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		//Verify In Folio's new newly added Room should be added 
		String newRoomNo = "";
		try{	
			test_steps.add("===Verify In Folio's new Room is added ===");
			app_logs.info("===Verify In Folio's new Room is added ===");
			cpRes.click_Folio(driver, getTest_Steps);
			cpRes.click_FolioDetail_DropDownBox(driver, getTest_Steps);
			newRoomNo = cpRes.VerifyFolioDropDownItem(driver, getTest_Steps);			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to verify In Folio's new Room is added", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to verify In Folio's new Room is added", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		//Verify Add Room To Reservation On History Tab
		try{	
			cpRes.click_History(driver, test_steps);
			test_steps.add("Clicked on History Tab Successfull");
			app_logs.info("Clicked on History Tab Successfull");

			test_steps.add("===Verify Add Room To Reservation On History Tab===");
			app_logs.info("===Verify Add Room To Reservation On History Tab===");
			cpRes.verifyAddRoomChangeInHistoryTab(driver, test_steps);

			test_steps.add("===Verify Cost Change In History Tab===");
			app_logs.info("===Verify  Cost Change In History Tab===");
			cpRes.verifyCostChangeInHistoryTab(driver, test_steps);
			
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to change room", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to change room", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		//Click on "Change Stay Details" and select any room number of selected room class
		String roomNo = "";
		String roomNoBefore = "";
		try {
			test_steps.add("===Click on Change Stay Details and Select New Room No===");
			app_logs.info("===Click on Change Stay Details and Select New Room No===");

			cpRes.click_DeatilsTab(driver, getTest_Steps);
			test_steps.add("Clicked on History Tab Successfull");
			app_logs.info("Clicked on History Tab Successfull");
		
			roomNo= cpRes.AssignNewRoomNumber(driver);
			roomNoBefore = roomNo.split("-")[0];
			roomNo = roomNo.split("-")[1];
			test_steps.add("Successfully changed room number from : "+roomNoBefore+" to "+roomNo);
			app_logs.info("Successfully assigned room number from : "+roomNoBefore+" to "+roomNo);
	
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to change room", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to change room", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		//Click on Verify Room Change in History Tab and verify change room status
		try{		
			test_steps.add("===Verify Room Change in History Tab===");
			app_logs.info("===Verify Room Change in History Tab===");

			cpRes.click_History(driver, test_steps);
			test_steps.add("Clicked on History Tab Successful");
			app_logs.info("Clicked on History Tab Successful");

			cpRes.verifyRoomChangeInHistoryTab(driver, test_steps,roomNoBefore,roomNo);
			test_steps.add("Successfully verify Room Change in History Tab");
			app_logs.info("Successfully verify Room Change in History Tab");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to change room", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to change room", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		//Click on Verify Room Change in History Tab and verify change room status
		try{		
			test_steps.add("===Verify Message to Confirm Apply The New Policy(ies) to the Reservation===");
			app_logs.info("===Verify Message to Confirm Apply The New Policy(ies) to the Reservation===");

			cpRes.click_DeatilsTab(driver, test_steps);
			test_steps.add("Clicked on Details Tab Successful");
			app_logs.info("Clicked on Details Tab Successful");

			cpRes.VerifyChangeOfPolicyMessageForRoomChange(driver,RoomNewClass, IsAssign,Account,test_steps);
			test_steps.add("Verify Message to Confirm Apply The New Policy(ies) to the Reservation Successful");
			app_logs.info("Verify Message to Confirm Apply The New Policy(ies) to the Reservation Successful");
		
			//Verifying Pop up display dialog for Cost change 
			test_steps.add("===Verifying Pop up display dialog for Cost change===");
			app_logs.info("===Verifying Pop up display dialog for Cost change===");
		
			getTest_Steps.clear();
			getTest_Steps = cpRes.verifyPopupChangeInCost(driver,getTest_Steps);
			test_steps.addAll(getTest_Steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to change room", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to Verify Pop up display dialog for Cost change", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		// *********Delete the Rate if exist****************//

		try {
			test_steps.add("=====Delete Rate if exist=====");
			nav.Inventory(driver);
			nav.Rates(driver);
			test_steps.add("Navigated to Inventory_Rate");
			app_logs.info("Navigated to Inventory_Rate");

			getTest_Steps.clear();
			getTest_Steps = rate.deleteRateIfExist(driver, rateNameToDelete, getTest_Steps);
			test_steps.addAll(getTest_Steps);
		}

		catch (Exception e) {
			if (Utility.reTry.get(test_name) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Delete Rate", testName, "DeleteRate", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(test_name) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Delete Rate", testName, "DeleteRate", driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		/*Delete if room class already exist 
		 *Create 2 new room class and assign one room class new policies*/

		try {
			test_steps.add("=====Delete Room Class if exist=====");
			nav.navigateToReservations(driver);
			test_steps.add("Navigate Reservation");
			app_logs.info("Navigate Reservation");

			nav.Setup(driver);
			test_steps.add("Navigate to Setup");
			app_logs.info("Navigate to Setup");

			nav.RoomClass(driver);
			test_steps.add("Navigate RoomClass");
			app_logs.info("Navigate RoomClass");

			room_class.SearchButtonClick(driver);
			test_steps.add("Click Search");
			app_logs.info("Click Search");

			getTest_Steps.clear();
			getTest_Steps = room_class.deleteRoomClassIfExist(driver, roomClassToDelete, getTest_Steps);
			test_steps.addAll(getTest_Steps);
		}

		catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Delete Room Class", testName, "DeleteRoomClass", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to Delete Room Class", testName, "DeleteRoomClass", driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		// Search and delete policies if exist
		try {
			test_steps.add("==VERIFY ANY POLCIY EXIST WITH SPECIFIC NAME AND DELETE IT==");
			nav.Inventory(driver);
			test_steps.add("Navigated to Inventory");
			app_logs.info("Navigated to Inventory");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to navigate Inventory page", policyNameToDelete, "InventoryNavigation", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to navigate Inventory page", testName, "InventoryNavigation", driver);

			} else {
				Assert.assertTrue(false);
			}
		}
		try {
			policies.ClickOnPolicies(driver);
			test_steps.add("Navigated to Policies");
			app_logs.info("Navigated to Policies");
		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to navigate to Policies page", testName, "PoliciesNavigation", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to navigate to Policies page", testName, "PoliciesNavigation", driver);

			} else {
				Assert.assertTrue(false);
			}
		}

		try {
			// start method here for deleting room classes
			isPolicyExist = policies.SearchPolicyWithName(driver, CPolicyName);
			if (isPolicyExist) {
				getTest_Steps.clear();
				getTest_Steps = policies.DeletePolicy_1(driver, CPolicyName);
				test_steps.addAll(getTest_Steps);

			} else {
				test_steps.add("No policy exist with name of " + CPolicyName);
			}
			
			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, test_steps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "Failed to search and delete policies", testName, "SearchPolicies", driver);

			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(test_name, test_description, test_catagory, test_steps);
				Utility.updateReport(e, "ailed to search and delete policies", testName, "SearchPolicies", driver);

			} else {
				Assert.assertTrue(false);
			}
		}
	}

	@DataProvider
	public Object[][] getData() {
		// return test data from the SheetName provided
		return Utility.getData("Verify_ChangeStayOptionMRB", excel);
	}

	@AfterClass(alwaysRun=true)
	public void closeDriver() {
		driver.quit();
	}
}
