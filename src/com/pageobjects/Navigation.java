package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.innroad.inncenter.interfaces.INavigation;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_GuestServices;
import com.innroad.inncenter.properties.OR_Inventory;
import com.innroad.inncenter.properties.OR_RateGrid;
import com.innroad.inncenter.properties.OR_Reservation;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_CPReservation;
import com.innroad.inncenter.webelements.Elements_Inventory;
import com.innroad.inncenter.webelements.Elements_On_All_Navigation;
import com.innroad.inncenter.webelements.Elements_TapeChart;
import com.innroad.inncenter.webelements.WebElements_RoomStatus;
import com.relevantcodes.extentreports.ExtentTest;

public class Navigation implements INavigation {

	public static Logger navigationLogger = Logger.getLogger("Navigation");

	public void navTapeChart(WebDriver driver, ExtentTest test) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		
		try {
			Wait.WaitForElement(driver, OR.MoveToTapeChart);
			Wait.waitForElementToBeVisibile(By.xpath(OR.MoveToTapeChart), driver);
			Wait.waitForElementToBeClickable(By.xpath(OR.MoveToTapeChart), driver);
			Utility.ScrollToElement_NoWait(Navigate.MoveToTapeChart, driver);
			Navigate.MoveToTapeChart.click();
			
		} catch (Exception e) {
			
			Utility.ScrollToElement_NoWait(Navigate.Tape_Chart, driver);
			Navigate.Tape_Chart.click();
		}
		
		navigationLogger.info("Clicked on tape chart sub-menu option");
		Wait.waitUntilPresenceOfElementLocated(OR.tapeChartGridLayOut, driver);
	}

	public void TapeChart(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].click();", Navigate.Tape_Chart_1);

		} catch (Exception e) {
			jse.executeScript("arguments[0].click();", Navigate.TapeChart);

		}
		// Navigate.Tape_Chart.click();
		Wait.WaitForElement(driver, OR.Enter_Adults_Tapehchart);
		navigationLogger.info("Navigated to Tapechart");

	}

	// Tapechart Navigation
	public void ClickTapeChart(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// Wait.explicit_wait_visibilityof_webelement(Navigate.TapeChart,
		// driver);
		Wait.WaitForElement(driver, OR.TapeChart);
		Utility.ScrollToElement(Navigate.TapeChart, driver);
		Navigate.TapeChart.click();
		navigationLogger.info("Navigated to Tapechart");
		// Wait.explicit_wait_xpath(OR.Tape_Chart_Grid1);
		Wait.waitUntilPresenceOfElementLocated(OR.tapeChartGridLayOut, driver);
	}

	// New Quote

	public void NewQuote(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);

		try {
			Navigate.New_QuoteIcon.click();
		} catch (Exception e) {
			Navigate.New_Quote.click();
		}
		/*
		 * Wait.explicit_wait_xpath(OR.New_Quote_Search, driver);
		 * Wait.WaitForElement(driver, OR.New_Quote_Search);
		 * navigationLogger.info("Click on New Quote");
		 */
		Wait.WaitForElement(driver, OR.Enter_RateQuoteNights);
		// Wait.explicit_wait_xpath(OR.New_Quote_Search, driver);
		navigationLogger.info("Navigated to New Quote");
	}

	// Guest History

	public void GuestHistory(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Navigate.Guest_HistoryIcon.click();
		} catch (Exception e) {
			Navigate.Guest_History.click();
		}

		Wait.explicit_wait_xpath(OR.Accountype_Label, driver);
		navigationLogger.info("Navigated to GuestHistory");
	}

	// Groups

	public void Groups(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Navigate.Groups.click();
		} catch (Exception e) {
			Navigate.Groups1.click();
		}
		Wait.WaitForElement(driver, OR.GroupsNewAccount);
		Wait.explicit_wait_xpath(OR.Groups_text, driver);
		navigationLogger.info("Navigated to Groups");
	}

	// Groups

	/*
	 * public void Groups_Backward(WebDriver driver) {
	 * Elements_On_All_Navigation Navigate = new
	 * Elements_On_All_Navigation(driver); try {
	 * Navigate.Groups_Backward.click(); } catch (Exception e) {
	 * Navigate.Groups1.click(); } Wait.explicit_wait_xpath(OR.Groups_text,
	 * driver); }
	 * 
	 * public void Groups_BackwardReservation(WebDriver driver) {
	 * Elements_On_All_Navigation Navigate = new
	 * Elements_On_All_Navigation(driver); try {
	 * Navigate.Groups_Backward_Reservation.click(); } catch (Exception e) {
	 * Navigate.Groups1.click(); } Wait.explicit_wait_xpath(OR.Groups_text,
	 * driver); }
	 */

	// Accounts

	public void Accounts(WebDriver driver) throws InterruptedException {

		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {

			Navigate.Accounts2.click();

		} catch (Exception e) {
			Navigate.Accounts.click();
		}
		Wait.explicit_wait_xpath(OR.Click_New_Account, driver);
		// Wait.explicit_wait_visibilityof_webelement_150(Navigate.Accounts_sec_Nav,
		// driver);
		navigationLogger.info("Navigated to Accounts");
	}

	public void Accounts_NavIcon(WebDriver driver) throws InterruptedException {

		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Wait.explicit_wait_visibilityof_webelement(Navigate.Accounts_NavIcon, driver);
			Utility.ScrollToElement(Navigate.Accounts_NavIcon, driver);
			Navigate.Accounts_NavIcon.click();
		} catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(Navigate.Accounts2, driver);
			Utility.ScrollToElement(Navigate.Accounts2, driver);
			Navigate.Accounts2.click();
		}

		Wait.explicit_wait_visibilityof_webelement_150(Navigate.Accounts_sec_Nav, driver);

	}

	public void AccountsNavIcon(WebDriver driver) throws InterruptedException {

		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Utility.ScrollToElement(Navigate.Accounts_NavIcon, driver);
		Navigate.Accounts_NavIcon.click();

		Wait.explicit_wait_visibilityof_webelement_150(Navigate.Accounts_sec_Nav, driver);

	}

	public void Accounts_sec_Nav(WebDriver driver) {

		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.Accounts_sec_Nav.click();
		Wait.explicit_wait_xpath(OR.Click_New_Account, driver);
		navigationLogger.info("Navigated to Accounts");
	}

	// Statements

	public void Statements(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Navigate.Statements_SecNav.click();
		} catch (Exception e) {
			Navigate.Statements.click();
		}

		Wait.WaitForElement(driver, OR.Statement_Grid);
		navigationLogger.info("Navigated to Statements");
	}

	// Unit owner Account

	public void UnitownerAccount(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);

		try {
			Navigate.UnitOwner.click();
		} catch (Exception e) {
			Navigate.Unit_owner.click();
		}
		Wait.WaitForElement(driver, OR.Unit_owner_Account);
		navigationLogger.info("Navigated to UnitownerAccount");

	}

	// Travel Agent

	public void TravelAgent(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.Travel_Agent.click();
		Wait.WaitForElement(driver, OR.Travel_Agent_Grid);
		// Wait.explicit_wait_xpath(OR.Travel_Agent_Grid, driver);
		navigationLogger.info("Navigated to TravelAgent");
	}

	// Management Transfers

	public void ManagementTransfers(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.Manafement_Transfers.click();
		Wait.explicit_wait_xpath(OR.Manafement_Transfers_Grid, driver);
		// Wait.explicit_wait_xpath(OR.Management_Items_Title, driver);
		navigationLogger.info("Navigated to ManagementTransfers");
	}

	// Account Distribution

	public void AccountDistribution(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.Account_Distribution.click();
		Wait.explicit_wait_xpath(OR.Account_Distribution_grid, driver);
		navigationLogger.info("Navigated to AccountDistribution");
	}

	// Guest services

	public void Guestservices(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);		 
		Wait.WaitForElement(driver, OR.Guest_Services_3);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].click();", Navigate.Guest_Services_3);
			Wait.waitTillElementDisplayed(driver, OR_GuestServices.GS_Loading);
			//Wait.WaitForElement(driver, OR.Guest_Services_grid);
			// Wait.explicit_wait_xpath(OR.GuestServicesPage, driver);
		} catch (Exception e) {
			//jse.executeScript("arguments[0].click();", Navigate.Guest_Services);
		//	Wait.WaitForElement(driver, OR.Guest_Services_grid);
          
		}
		
		
	}

	public void Guestservices__TaskManagement(WebDriver driver) {

		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.WaitForElement(driver, OR.Guest_Services_3);
		Navigate.Guest_Services_3.click();
	}

	public void Guestservices_1(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.Guest_Services_1.click();
		Wait.explicit_wait_xpath(OR.GuestServicesPage, driver);

	}

	public void Guestservices_2(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.Guest_Services_2);
		try {

			Wait.explicit_wait_xpath(OR.GuestServicesPage, driver);
		} catch (Exception e) {

			Wait.explicit_wait_xpath(OR.Guest_Services_grid, driver);
		}

	}

	public void Guestservices_3(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Navigate.Guest_Services_3, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.Guest_Services_3);
		/*
		 * try {
		 * 
		 * Wait.explicit_wait_xpath(OR.GuestServicesPage); } catch (Exception e)
		 * {
		 * 
		 * Wait.explicit_wait_xpath(OR.Guest_Services_grid); }
		 */
	}

	public void GuestServices(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(Navigate.Guest_Services_3, driver);
		Utility.ScrollToElement(Navigate.Guest_Services_3, driver);
		Navigate.Guest_Services_3.click();
		try {
			Wait.explicit_wait_xpath(OR.GuestServicesPage, driver);
		} catch (Exception e) {
			Wait.explicit_wait_xpath(OR.Guest_Services_grid, driver);
		}

	}

	// HouseKeeping Status

	public void HouseKeepingStatus(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.House_Keeping.click();
		Wait.explicit_wait_xpath(OR.House_Kepping_status, driver);
		navigationLogger.info("Navigated to HouseKeepingStatus");
	}

	// Task List

	public void TaskList(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].click();", Navigate.TaskListIcon);
			// Wait.explicit_wait_xpath(OR.TaskListPage, driver);
			Wait.explicit_wait_xpath(OR.Task_List_grid, driver);
		} catch (Exception e) {
			Navigate.Task_List.click();
			Wait.explicit_wait_xpath(OR.Task_List_grid, driver);

		}

		navigationLogger.info("Navigated to TaskList");
	}

	// Task List

	public void Task_List(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].click();", Navigate.TaskListIcon);
			Wait.WaitForElement(driver, OR.TaskListPage);
			// System.out.println("try");
		} catch (Exception e) {
			jse.executeScript("arguments[0].click();", Navigate.TaskList);
			Wait.WaitForElement(driver, OR.TaskListPage);
			// System.out.println("catch");

		}

	}

	public void Task_List_New(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.wait2Second();
		Wait.WaitForElement(driver, OR.TaskListPage);
		Navigate.TaskList.click();

	}

	public void Nav_TaskList(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.TaskListIcon);
		Wait.explicit_wait_xpath(OR.TaskListPage, driver);
		System.out.println("catch");

	}

	// Room Status

	public void RoomStatus(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.wait10Second();
		Wait.explicit_wait_visibilityof_webelement_120(Navigate.RoomStatus, driver);
		Utility.ScrollToElement(Navigate.RoomStatus, driver);
		Navigate.RoomStatus.click();
		Wait.explicit_wait_xpath(OR.RoomStatusPage, driver);
	}

	// Room Maintenance Navigation

	public void RoomMaintenance(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			// Wait.explicit_wait_visibilityof_webelement(Navigate.RoomMaintanence,
			// driver);
			Utility.ScrollToElement(Navigate.RoomMaintanence, driver);
			jse.executeScript("arguments[0].click();", Navigate.RoomMaintanence);
			// Utility.app_logs.info("Clicked Room Maintenence tab");
		} catch (Exception e) {

			// Utility.app_logs.info("Catch");
			jse.executeScript("arguments[0].click();", Navigate.Room_Maintanence);
			// Navigate.Room_Maintanence.click();
		}
		// Wait.explicit_wait_xpath(OR.Room_Maintanence_Grid, driver);
		Wait.explicit_wait_xpath(OR.Room_Maintenance_Fromdate, driver);
		navigationLogger.info("Navigated to RoomMaintenance");
	}

	// Inventory Navigation

	public void Inventory(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].click();", Navigate.Inventory);
			Wait.waitForElementToBeVisibile(By.xpath(OR.Inventory_Grid), driver);
		} catch (Exception e) {
			driver.navigate().refresh();
			jse.executeScript("arguments[0].click();", Navigate.Inventory2);
			Wait.waitForElementToBeVisibile(By.xpath(OR.Inventory_Grid), driver);
		}
		Wait.waitForElementToBeVisibile(By.xpath(OR.Inventory_Menu_Title), driver);
		navigationLogger.info("Navigated to Inventory");
	}

	public ArrayList<String> Inventory(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		try{
			Inventory(driver);
		}catch (Exception e) {
			Inventory_Backward_1(driver);
		}
		test_steps.add("Navigate to Inventory");
		return test_steps;
	}

	public void Inventory_Backward_1(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.Inventory_Backward_1);
	}

	public void inventory_Backward_1(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.Inventory_Backward_1);
	}
	public void Inventory_Backward(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.Inventory_Backward);
	}

	public void Inventory_Backward_2(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.Inventory_Backward_2);
	}
	public void Overview(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].click();", Navigate.Overview);
			// Navigate.Overview.click();
		}

		catch (Exception e) {
			jse.executeScript("arguments[0].click();", Navigate.Overview1);
		}

		// Wait.WaitForElement(driver, OR.OverviewBulkUpdate);
		Wait.WaitForElement(driver, OR.Overview_grid);
		navigationLogger.info("Navigated to Overview");

	}

	public ArrayList<String> RatesGrid(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		/*
		 * Navigate.Rategrid_Inventory.click(); //
		 * Wait.explicit_wait_xpath(OR.Overview_grid);
		 * Wait.explicit_wait_xpath(OR.OverViewTab, driver);
		 */
		Wait.WaitForElement(driver, OR.RatesGrid);
		Wait.waitForElementToBeVisibile(By.xpath(OR.RatesGrid), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.RatesGrid), driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].click();", Navigate.RatesGrid);
			// Navigate.Overview.click();
		}

		catch (Exception e) {
			jse.executeScript("arguments[0].click();", Navigate.RatesGrid1);
		}

		// Wait.WaitForElement(driver, OR.OverviewBulkUpdate);
	//	Wait.WaitForElement(driver, OR.Overview_grid);
		navigationLogger.info("Navigated to Overview");
		testSteps.add("Navigated to Overview");
		
		return testSteps;

	}
	// Overview

	public void Overview1(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.Overview);
		// Navigate.Overview.click();
		// Wait.explicit_wait_xpath(OR.Overview_grid);

	}

	public void Rategrid_Inventory(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].click();", Navigate.RatesGrid);
			// Navigate.Overview.click();
		}

		catch (Exception e) {
			jse.executeScript("arguments[0].click();", Navigate.RatesGrid1);
		}

		// Wait.WaitForElement(driver, OR.OverviewBulkUpdate);
		Wait.WaitForElement(driver, OR.Overview_grid);
		navigationLogger.info("Navigated to Overview");

	}

	public void Rates_Grid(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.explicit_wait_visibilityof_webelement(Navigate.RatesGrid, driver);
		Wait.explicit_wait_elementToBeClickable(Navigate.RatesGrid, driver);
		Utility.ScrollToElement(Navigate.RatesGrid, driver);
		Navigate.RatesGrid.click();

	}
	// Seasons

	@Override
	public void Seasons(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);

		try {
			Navigate.Seasons.click();
		} catch (Exception e) {
			Navigate.Seasons1.click();
		}
		Wait.explicit_wait_xpath(OR.Seasons_button, driver);
		navigationLogger.info("Navigated to Seasons");
	}

	public void Seasons1(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.Seasons1.click();
	}

	// Rates

	public void Rate(WebDriver driver) {
		try {
			Rates1(driver);
		} catch (Exception e) {
			Rates(driver);
		}
	}

	public void Rate(WebDriver driver, ArrayList<String> test_steps) {
		Rate(driver);
		test_steps.add("Navigate to Rates");
	}

	// Navigate to Rates
	public void Rates(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].click();", Navigate.Rates);
		} catch (Exception e) {
			jse.executeScript("arguments[0].click();", Navigate.Rates1);
		}
		Wait.explicit_wait_xpath(OR.Rates_Grid, driver);
		navigationLogger.info("Navigated to Rates");
	}

	public void Rates1(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);

		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			Utility.ScrollToElement(Navigate.Rates1, driver);
			jse.executeScript("arguments[0].click();", Navigate.Rates1);
			// Navigate.Rates1.click();
			// Wait.explicit_wait_xpath(OR.Rates_Grid);
			Wait.explicit_wait_visibilityof_webelement_120(Navigate.Rates_Grid, driver);
			navigationLogger.info("Navigated to Rates");
		} catch (Exception e) {
			Navigate.rates2.click();
			Wait.explicit_wait_visibilityof_webelement_120(Navigate.Rates_Grid, driver);
			navigationLogger.info("Navigated to Rates");
		}
	}

	public void Rates2(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Utility.ScrollToElement(Navigate.Rates2, driver);
		Navigate.Rates2.click();
		// Wait.explicit_wait_xpath(OR.Rates_Grid);
		Wait.explicit_wait_visibilityof_webelement_120(Navigate.Rates_Grid, driver);
	}
	// Rules

	public void Rules(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);

		try {
			Navigate.Rules.click();
		} catch (Exception e) {
			Navigate.Rules1.click();
		}
		// Navigate.Rules.click();
		Wait.explicit_wait_xpath(OR.Rules_Grid, driver);
		navigationLogger.info("Navigated to Rules");
	}

	// Distribution

	public void Distribution(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		try {

			// jse.executeScript("arguments[0].click();",
			// Navigate.Distribution);
			Navigate.Distribution.click();
		} catch (Exception e) {
			// jse.executeScript("arguments[0].click();",
			// Navigate.Distribution1);
			Navigate.Distribution1.click();
		}
		try {
			String loading = "//div[@class='dvLoading']";
			Wait.waitTillElementDisplayed(driver, loading);
		}catch (Exception e) {
			// TODO: handle exception
		}
		Wait.WaitForElement(driver, OR.DistributionChannel);
		//Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.DistributionChannel)), driver);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(OR.DistributionChannel)), driver);
		navigationLogger.info("Navigated to Distribution");
		}

	// Distribution_syndication

	public void Distribution_syndication(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);

		Navigate.Distribution_syndication.click();
		Wait.WaitForElement(driver, OR.LoginModuleLoding);
		Wait.waitForElementToBeGone(driver, Navigate.LoginModuleLoding, 300);
		Wait.explicit_wait_xpath(OR.Syndication_Month_Label, driver);
		navigationLogger.info("Navigated to Distribution_syndication");
	}

	// Distribution Blackouts

	public void DistributionBlackouts(WebDriver driver) {

		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Navigate.Distribution_Blackouts.click();
			Wait.explicit_wait_xpath(OR.BlackOuts_Source_Label, driver);
			navigationLogger.info("Navigated to DistributionBlackouts");
		} catch (Exception e) {
			navigationLogger.info("Distribution_Blackouts is not displayed");
		}

	}

	// policies

	public void policies(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].click();", Navigate.policies);
		} catch (Exception e) {
			jse.executeScript("arguments[0].click();", Navigate.policies1);
		}
		//Wait.waitForElementToBeVisibile(By.xpath(OR.Policy_Button), driver);
		navigationLogger.info(" Navigated to Policies ");
	}


	public ArrayList<String> policies(WebDriver driver, ArrayList<String> test_steps) {
		policies(driver);
		test_steps.add("Navigated to Policies");
		return test_steps;
	}


	// Setup

	public void Setup(WebDriver driver) throws Exception {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.WaitForElement(driver, OR.SetupIcon);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			Utility.ScrollToElement_roomClass(Navigate.SetupIcon, driver);
			jse.executeScript("arguments[0].click();", Navigate.SetupIcon);
		} catch (Exception e) {
			Utility.ScrollToElement_roomClass(Navigate.Setup, driver);
			jse.executeScript("arguments[0].click();", Navigate.Setup);
		}
		try {
			Wait.explicit_wait_xpath(OR.Setup_Grid, driver);
		} catch (Exception e) {
			driver.navigate().refresh();
			Wait.explicit_wait_xpath(OR.Setup_Grid, driver);
		}
		Wait.explicit_wait_xpath(OR.Setup_Menu_Title, driver);
		navigationLogger.info("Navigated to Setup");
	}

	public void setup(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.WaitForElement(driver, OR.SetupIcon);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].click();", Navigate.SetupIcon);
		} catch (Exception e) {
			jse.executeScript("arguments[0].click();", Navigate.Setup);
		}
		try {
			Wait.explicit_wait_xpath(OR.Setup_Grid, driver);
		} catch (Exception e) {
			driver.navigate().refresh();
			Wait.explicit_wait_xpath(OR.Setup_Grid, driver);
		}
		Wait.explicit_wait_xpath(OR.Setup_Menu_Title, driver);
		navigationLogger.info("Navigated to Setup");
	}
	
	public void Setup(WebDriver driver, ArrayList<String> test_steps) {
		setup(driver);
		test_steps.add("Navigate to Setup");
		navigationLogger.info("Navigate to Setup");
	}

	// GS_SETUP
	public void GS_Setup(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Utility.ScrollToElement(Navigate.GS_Setup, driver);
		Navigate.GS_Setup.click();
		Wait.explicit_wait_xpath(OR.Setup_Grid, driver);
		navigationLogger.info("Click on Setup");

	}

	// Properties

	public void Properties(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);

		Navigate.Propeties.click();
		Wait.explicit_wait_xpath(OR.Propety_Grid, driver);
		navigationLogger.info("Navigated to Properties");

	}

	// Taxes

	public void Taxes(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Navigate.Taxes.click();
		} catch (Exception e) {
			Navigate.Taxes1.click();
		}

		Wait.explicit_wait_xpath(OR.Taxes_Grid, driver);
		navigationLogger.info("Navigated to Taxes");
	}

	public void clickOnTaxesTab(WebDriver driver, ArrayList<String> test_steps) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Wait.waitForElementToBeClickable(By.xpath(OR.Taxes), driver);
			Navigate.Taxes.click();
			test_steps.add("Navigated to taxes window");
		} catch (Exception e) {
			Navigate.Taxes1.click();
		}

		Wait.explicit_wait_xpath(OR.Taxes_Grid, driver);
		navigationLogger.info("Navigated to Taxes");
	}

	// Taxes

	public void ClickTaxesTab(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.WaitForElement(driver, OR.Taxes1);
		try {
			Navigate.Taxes1.click();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Wait.explicit_wait_xpath(OR.Taxes_Grid, driver);
		navigationLogger.info("Navigated to Taxes");
	}

	// Ledger Accounts
	public void LedgerAccounts(WebDriver driver) {

		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Navigate.Ledger_Accounts.click();
		} catch (Exception e) {
			Navigate.Ledger_Accounts1.click();
		}
		Wait.explicit_wait_xpath(OR.Ledger_Account_Grid, driver);
		navigationLogger.info("Navigated to LedgerAccounts");

	}

	// Merchant services
	public void Merchantservices(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Navigate.Merchant_Services.click();
		} catch (Exception e) {

			Navigate.Merchant_Services1.click();
		}
		Wait.explicit_wait_xpath(OR.Merchant_Services_Grid, driver);
		navigationLogger.info("Navigated to Merchantservices");

	}

	// Document Template
	public void DocumentTemplate(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Navigate.Document_Template.click();
		} catch (Exception e) {
			Navigate.Document_Template1.click();
		}

		Wait.explicit_wait_xpath(OR.Documnet_Template_Grid, driver);

		navigationLogger.info("Navigated to  DocumentTemplates");
	}

	// List Managemnet

	public void ListManagemnet(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);

		Navigate.List_Management.click();

		Wait.explicit_wait_xpath(OR.List_Management_Grid, driver);
		navigationLogger.info("Navigated to  List Management ");
	}

	// Task Managemnet

	public void TaskManagement(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.Task_Management.click();
		Wait.explicit_wait_xpath(OR.TaskManagement_Tab, driver);

	}

	// Task Management Tab Exist
	public void TaskManagement_TabExist(WebDriver driver) {
		if (!driver.findElements(By.xpath(OR.Task_Management)).isEmpty()) {
			navigationLogger.info("Task_Management tab Exist");
		} else {
			assertTrue(false, "Task_Management Tab does not exist");
		}
	}

	// Admin

	public void Admin(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Navigate.AdminIcon.click();
		} catch (Exception e) {
			Navigate.Admin.click();
		}
		Wait.waitForElementToBeVisibile(By.xpath(OR.Admin_Grid), driver);
		navigationLogger.info("Navigated to Admin");
	}

	public void navigateToAdminPage(WebDriver driver, ArrayList<String> test_steps) {
		Admin(driver);
		test_steps.add("Navigated to Admin page");
	}

	// Client info

	public void Clientinfo(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Navigate.Client_info.click();
		} catch (Exception e) {
			Navigate.Client_info1.click();
		}
		Wait.waitForElementToBeVisibile(By.xpath(OR.Client_info_grid), driver);
		navigationLogger.info("Navigated to Client Info");
	}

	public void navigateToClientInfoPage(WebDriver driver, ArrayList<String> test_steps) {
		Clientinfo(driver);
		test_steps.add("Navigated to Client Info Page");
	}
	
	public void openClientDetails(WebDriver driver, ArrayList<String> test_steps) {
		try {
			Wait.waitForElementToBeClickable(By.xpath("//div[@id='MainContent_Clients_DIV']//a"), driver);
//			Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@id='MainContent_Clients_DIV']//a")));
//			driver.findElement(By.xpath("//td/a")).click();
			Wait.waitForElementToBeVisibile(By.xpath(OR.clientDetailsHeader), driver);
			test_steps.add("Clicked on client and opened client details tab");

		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public void openClientDetailsOptionsTab(WebDriver driver, ArrayList<String> test_steps) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.clientDetailsOptionsTab), driver);
		Navigate.clientDetailsOptionsTab.click();
	}

	// Users
	public void Users(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Navigate.Users.click();
		} catch (Exception e) {
			Navigate.Users1.click();
		}
		Wait.explicit_wait_xpath(OR.Users_grid, driver);
		navigationLogger.info("Navigated to Users");
	}

	// Roles

	public void Roles(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Navigate.Roles.click();
		} catch (Exception e) {
			Navigate.Roles1.click();
		}
		Wait.explicit_wait_xpath(OR.Roles_page, driver);
		navigationLogger.info("Navigated to Roles");
	}

	// Night Audit

	public void NightAudit(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Navigate.NightAudit.click();
			System.out.println("in try night audit");
		} catch (Exception e) {
			System.out.println("in catch night audit");
			Navigate.Night_audit.click();
		}
		Wait.explicit_wait_xpath(OR.Period_status, driver);
		navigationLogger.info("Navigated to Night Audit");
	}

	public void NightAuditIcon(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Navigate.NightAuditIcon.click();
		} catch (Exception e) {
			Navigate.Night_audit.click();
		}
		Wait.explicit_wait_xpath(OR.Period_status, driver);
	}
	// Reports

	public void Reports(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].click();", Navigate.ReportsIcon);
		} catch (Exception e) {
			jse.executeScript("arguments[0].click();", Navigate.Reports);
		}
		// Navigate.Reports.click();
		Wait.explicit_wait_xpath(OR.Reports_Grid, driver);
		navigationLogger.info("Navigated to Reports");
	}

	public void Reports_Backward(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.Reports_Backward);
		// Navigate.Reports.click();
		Wait.explicit_wait_xpath(OR.Reports_Grid, driver);
	}
	// Account Balances

	public void AccountBalances(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.Account_Balance.click();
		Wait.explicit_wait_xpath(OR.Account_Balance_Grid, driver);
		// assertTrue(driver.findElement(By.xpath(OR.Account_Balance_Grid)).isDisplayed(),"Failed
		// : Advance Balance Page is not displayed");
		navigationLogger.info(" Navigated to Reports>> Account Balances ");
	}

	// Ledger Balances

	public void LedgerBalances(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.Ledger_Balances.click();
		Wait.explicit_wait_xpath(OR.Ledger_Balances_Grid, driver);
		// assertTrue(driver.findElement(By.xpath(OR.Ledger_Balances_Grid)).isDisplayed(),"Failed
		// : Ledger Balances Page is not displayed");
		navigationLogger.info(" Navigated to Reports>> Ledger Balances ");
	}

	// Merchant Trans

	public void MerchantTrans(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.Merchant_Trans.click();
		Wait.explicit_wait_xpath(OR.Merchant_Trans_grid, driver);
		// assertTrue(driver.findElement(By.xpath(OR.Merchant_Trans_grid)).isDisplayed(),"Failed
		// : Merchant Transaction Page is not displayed");
		navigationLogger.info(" Navigated to Reports>> Merchant Trans ");
	}

	// Daily Flash

	public void DailyFalsh(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.Daily_flash.click();
		Wait.explicit_wait_xpath(OR.Daily_flash_grid, driver);
		// assertTrue(driver.findElement(By.xpath(OR.Daily_flash_grid)).isDisplayed(),"Failed
		// : Daily Flash Page is not displayed");
		navigationLogger.info(" Navigated to Reports>> Daily Flash ");
	}

	// Room Forecast

	public void RoomForecast(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.Room_Forecast.click();
		Wait.explicit_wait_xpath(OR.Room_Forecast_grid, driver);
		// assertTrue(driver.findElement(By.xpath(OR.Room_Forecast_grid)).isDisplayed(),"Failed
		// : Room Forecast Page is not displayed");
		navigationLogger.info(" Navigated to Reports>> RoomForecast ");
	}

	// Net Sales

	public void NetSales(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Navigate.Net_Sales.click();
		} catch (Exception e) {
			Navigate.Net_Sales_1.click();

		}
		Wait.explicit_wait_xpath(OR.Net_Sales_Grid, driver);

		// assertTrue(driver.findElement(By.xpath(OR.Net_Sales_Grid)).isDisplayed(),"Failed
		// : Net Sales Page is not displayed");
		navigationLogger.info(" Navigated to Reports>> NetSales ");
	}

	// Advance Deposite
	public void AdvanceDeposite(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.Advance_Depos.click();
		Wait.explicit_wait_xpath(OR.Advance_Depos_grid, driver);
		// assertTrue(driver.findElement(By.xpath(OR.Advance_Depos_grid)).isDisplayed(),"Failed
		// : Advance Deposite Page is not displayed");
		navigationLogger.info(" Navigated to Reports>> Advance Deposite ");
	}

	public void RoomClass(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].click();", Navigate.Roomclass);
		} catch (Exception e) {
			jse.executeScript("arguments[0].click();", Navigate.Roomclass1);
		}
		Wait.explicit_wait_xpath(OR.Roomclass_grid, driver);
		navigationLogger.info("Navigated to Room Class");
	}

	public void RoomClass(WebDriver driver, ArrayList<String> test_steps) {
		RoomClass(driver);
		test_steps.add("Navigate to Room Class");
		navigationLogger.info("Navigate to Room Class");
	}

	public void NewRoomClass(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
        	Wait.WaitForElement(driver, OR.NewRoomClass);
			Wait.waitForElementToBeVisibile(By.xpath(OR.NewRoomClass), driver);
			Wait.waitForElementToBeClickable(By.xpath(OR.NewRoomClass), driver);
			Navigate.NewRoomClass.click();
		} catch (Exception e) {
			Utility.app_logs.info("catch");
			Utility.ScrollToElement(Navigate.New_RoomClass, driver);
			//Navigate.New_RoomClass.click();
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", Navigate.New_RoomClass);
		}
		Wait.explicit_wait_xpath(OR.New_RoomClass_Deails, driver);
	}

	public void clickOnNewRoomClassButton(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		NewRoomClass(driver);
		test_steps.add("Navigated to room class creation page");
	}

	public void NewRoomClass1(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Thread.sleep(3000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.New_RoomClass1);
		Wait.explicit_wait_xpath(OR.New_RoomClass_Page, driver);
	}

	

	public void Reservation_Backward(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.Reservation_Backward);
		// Navigate.Reservation_Backward.click();
		Wait.explicit_wait_xpath(OR.Verify_Reservation_Page, driver);
	}

	public void Reservation_Backward_1(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Wait.explicit_wait_visibilityof_webelement(Navigate.Reservation_Backward_1, driver);
		Utility.ScrollToElement(Navigate.Reservation_Backward_1, driver);
		jse.executeScript("arguments[0].click();", Navigate.Reservation_Backward_1);
		Wait.explicit_wait_xpath(OR.Verify_Reservation_Page, driver);
	}

	public void Reservation_Backward_2(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.Reservation_Backward_2);
		Wait.explicit_wait_xpath(OR.Verify_Reservation_Page, driver);
	}

	public void Reservation_Backward_3(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.Reservation_Backward_3);
		Wait.explicit_wait_xpath(OR.Verify_Reservation_Page, driver);
	}

	public void Reservation_Backward_4(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.Reservation_Backward_4);
		Wait.explicit_wait_xpath(OR.Verify_Reservation_Page, driver);
	}

	public void Guest_info(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.Guest_Info);
		// Navigate.Guest_Info.click();
		Wait.explicit_wait_xpath(OR.Verify_Guest_Info, driver);
	}

	public void TaxesAfterCloseReservation(WebDriver driver) throws InterruptedException {

		Wait.WaitForElement(driver, "//a[contains(text(),'Taxes\')]");
		driver.findElement(By.xpath("//a[contains(text(),'Taxes\')]")).click();
		Wait.explicit_wait_xpath(OR.Taxes_Grid, driver);
	}

	public void SearchRoomClass(WebDriver driver) throws InterruptedException {

		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.RoomClass_SearchButton, driver);
		Navigate.RoomClass_SearchButton.click();
	}

	public void DeleteRoomClass(WebDriver driver) throws InterruptedException {

		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.SelectAllRecord, driver);
		Utility.ScrollToElement(Navigate.SelectAllRecord, driver);
		new Select(Navigate.SelectAllRecord).selectByVisibleText("100");
		Wait.wait2Second();

		Utility.ScrollToElement(Navigate.RoomClass_Checkbox.get(Navigate.RoomClass_Checkbox.size() - 1), driver);
		Navigate.RoomClass_Checkbox.get(Navigate.RoomClass_Checkbox.size() - 1).click();
		Navigate.DeleteRoomClass_Button.click();

		Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
		System.out.println(Navigate.Toaster_Message.getText());
	}

	public void DeleteRoomClass(WebDriver driver, String RoomClassName) throws InterruptedException {

		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.SelectAllRecord, driver);
		Utility.ScrollToElement(Navigate.SelectAllRecord, driver);
		new Select(Navigate.SelectAllRecord).selectByVisibleText("100");
		Wait.wait2Second();

		String RoomPath = "(//*[text()='" + RoomClassName + "']//ancestor::tr//input)[1]";
		WebElement RoomClass = driver.findElement(By.xpath(RoomPath));
		Utility.ScrollToElement(RoomClass, driver);
		RoomClass.click();
		Utility.ScrollToElement(Navigate.DeleteRoomClass_Button, driver);
		Navigate.DeleteRoomClass_Button.click();
		Wait.explicit_wait_visibilityof_webelement(Navigate.Toaster_Message, driver);
		System.out.println(Navigate.Toaster_Message.getText());
	}
	// QA Property

	public void SelectAllQAProperty(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.QAProperty.click();
		Wait.wait2Second();
		WebElement QAPropertyElement = driver.findElement(By.xpath("//div[text()='--All--']"));
		QAPropertyElement.click();
		Wait.wait2Second();
		String QAPropertyType = Navigate.QAProperty.getText();
		assertTrue(QAPropertyType.equals("--All--"), "Property not selected to All");

	}

	public void SelectAndVerifyQAProperty(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.QAProperty.click();
		// Wait.wait2Second();
		// WebElement QAPropertyElement =
		// driver.findElement(By.xpath("//div[text()='--All--']"));
		// QAPropertyElement.click();
		// Wait.wait2Second();
		// String QAPropertyType = Navigate.QAProperty.getText();
		// assertTrue(QAPropertyType.equals("--All--"), "Property not selected
		// to All");

	}

	public void CheckNumberOfPropertiesAndSetAllProperty(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.QAProperty.click();
		Wait.wait2Second();
		List<WebElement> PropertyElements = driver.findElements(By.xpath("//*[@id='select2-results-2']/li"));
		assertTrue(PropertyElements.size() > 2,
				"Failed : This client has just " + PropertyElements.size() + " Properties");
		WebElement QAPropertyElement = driver.findElement(By.xpath("//div[text()='--All--']"));
		QAPropertyElement.click();
		Wait.wait2Second();
		String QAPropertyType = Navigate.QAProperty.getText();
		assertTrue(QAPropertyType.equals("--All--"), "Property not selected to All");

	}

	public String SelectedQAProperty(WebDriver driver, ArrayList<String> getTest_Steps) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.QAProperty.click();
		Wait.wait2Second();
		WebElement QAPropertyElement = driver
				.findElement(By.xpath("(//div[@class='select2-result-label' and text()='" + getTest_Steps + "'])[1]"));
		QAPropertyElement.click();
		Wait.wait2Second();
		String QAPropertyType = Navigate.QAProperty.getText();
		assertTrue(QAPropertyType.equals(getTest_Steps), getTest_Steps + " not selected");
		return QAPropertyType;

	}

	public void GoToPage(WebDriver driver) throws InterruptedException {
		String url = Utility.envURL;
		url = url.replace("login.html", "");
		if (url.contains("com/")) {
			url = url + "index.html#reservationSearch";
		} else {
			url = url + "/index.html#reservationSearch";
		}
		driver.get(url);
		Wait.explicit_wait_xpath(OR.Verify_Reservation_Page, driver);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.Verify_Reservation_Page)), driver);
	}
	/*
	 * public String SelectedQAProperty(WebDriver driver, ArrayList<String>
	 * test_steps) { Elements_On_All_Navigation Navigate = new
	 * Elements_On_All_Navigation(driver); String QAPropertyType = ""; try {
	 * QAPropertyType = Navigate.QAProperty.getText();
	 * Utility.app_logs.info("Verify Property : " + QAPropertyType);
	 * test_steps.add("Verify Property : " + QAPropertyType); } catch (Exception
	 * e) { assertTrue(false); } return QAPropertyType; }
	 */

	public void secNavigation_Reservation(WebDriver driver, ArrayList test_steps) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.WaitForElement(driver, OR.secNav_Reservation);
		Navigate.secNav_Reservation.click();
		test_steps.add("Navigated to reservation");
	}

	public void Reservation_FromGiftAccount(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.WaitForElement(driver, "(//div[@class='innCenterNavigation']//span[.='Reservations'])[3]");
		driver.findElement(By.xpath("(//div[@class='innCenterNavigation']//span[.='Reservations'])[3]")).click();
		Wait.explicit_wait_xpath(driver, OR.Verify_Reservation_Page);
	}

	public void SelectQAProperty(WebDriver driver, String PropertyName) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Navigate.QAProperty.click();
		Wait.wait2Second();
		WebElement QAPropertyElement = driver
				.findElement(By.xpath("(//div[@class='select2-result-label' and text()='" + PropertyName + "'])[1]"));
		QAPropertyElement.click();
		Wait.wait2Second();
		String QAPropertyType = Navigate.QAProperty.getText();
		assertTrue(QAPropertyType.equals(PropertyName), PropertyName + " not selected");
	}

	public void Groups_Backward(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Navigate.Groups_Backward.click();
		} catch (Exception e) {
			Navigate.Groups1.click();
		}
		Wait.explicit_wait_xpath(OR.Groups_text, driver);
	}

	public void Groups_BackwardReservation(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Navigate.Groups_Backward_Reservation.click();
		} catch (Exception e) {
			Navigate.Groups1.click();
		}
		Wait.explicit_wait_xpath(OR.Groups_text, driver);
	}

	public void Inventory_RuleClick(WebDriver driver) {
		Elements_Inventory newRule = new Elements_Inventory(driver);
		Wait.explicit_wait_visibilityof_webelement(newRule.click_Rules, driver);
		newRule.click_Rules.click();
	}

	public void ClickOnSetup(WebDriver driver) {

		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.WaitForElement(driver, OR.SetupIcon);
		Navigate.SetupIcon.click();
		navigationLogger.info("Click on Setup");

	}

	public void open_Property(WebDriver driver, ArrayList test_steps, String PropertyName) {
		String prop = "//a[text()='" + PropertyName.trim() + "']";
		Wait.WaitForElement(driver, prop);
		driver.findElement(By.xpath(prop)).click();
		navigationLogger.info("Click on Property : " + PropertyName);
	}


	
	public void click_PropertyOptions(WebDriver driver,ArrayList<String> test_steps) {
		String options="//input[@id='MainContent_btnPropertyAdvance']";
		WebElement element=driver.findElement(By.xpath(options));
		Wait.WaitForElement(driver, options);
        element.click();
		test_steps.add("Click on Property options");
		navigationLogger.info("Click on Property options");
	}

	public String get_Property_TimeZone(WebDriver driver) {

		String zone = "//select[@id='MainContent_drpTimeZone']";
		String timeZone = new Select(driver.findElement(By.xpath(zone))).getFirstSelectedOption().getText();
		navigationLogger.info("Getting Time zone : " + timeZone);
		return timeZone;
	}

	public void Click_ReservationMenu(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.wait3Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.Click_Reservation_Menu);
		Wait.wait3Second();
		Wait.explicit_wait_xpath(OR.Verify_Reservation_Page, driver);

	}


	public void Inventory_Ratesgrid_Tab(WebDriver driver, ArrayList<String> test_steps) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].click();", Navigate.Rates_GridTab);
			test_steps.add("Navigated to Rates Grid Tab");
			navigationLogger.info("Navigated to Rates Grid Tab");
		}

		catch (Exception e) {
			test_steps.add("Failed to Navigated to Rates Grid Tab");
			navigationLogger.info("Failed to Navigated to Rates Grid Tab");
		}

			Wait.WaitForElement(driver, OR_Inventory.RatsTab);

	}
	
	
	// Availability Tab

		public void AvailabilityTab(WebDriver driver) {
			Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", Navigate.AvailabilityTab);
			navigationLogger.info("Click on Availability Tab");
			
		}
		
		public void navigateGuestservices(WebDriver driver) {
			Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			try {
				jse.executeScript("arguments[0].click();", Navigate.Guest_Services_3);
				Wait.explicit_wait_xpath(OR.GS_DateRangeLabel, driver);
				navigationLogger.info("Navigated to Guestservices");
			} catch (Exception e) {
				navigationLogger.info("Unable to Navigate to Guestservices");
			}

		}

	public void navigateSetup(WebDriver driver) {
		Elements_On_All_Navigation navigate = new Elements_On_All_Navigation(driver);
		Wait.WaitForElement(driver, OR.SetupIcon);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].click();", navigate.SetupIcon);
		} catch (Exception e) {
			jse.executeScript("arguments[0].click();", navigate.Setup);
		}
		try {
			Wait.explicit_wait_xpath(OR.Setup_Grid, driver);
		} catch (Exception e) {
			driver.navigate().refresh();
			Wait.explicit_wait_xpath(OR.Setup_Grid, driver);
		}
		Wait.explicit_wait_xpath(OR.Setup_Menu_Title, driver);
		navigationLogger.info("Navigated to Setup");
	}

	public void navigateRoomClass(WebDriver driver) {
		Elements_On_All_Navigation navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].click();", navigate.Roomclass);
		} catch (Exception e) {
			jse.executeScript("arguments[0].click();", navigate.Roomclass1);
		}
		Wait.explicit_wait_xpath(OR.Roomclass_grid, driver);
		navigationLogger.info("Navigated to Room Class");
	}

	public void newRoomClass(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.WaitForElement(driver, OR.NewRoomClass);
		Wait.waitForElementToBeVisibile(By.xpath(OR.NewRoomClass), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.NewRoomClass), driver);
		Navigate.NewRoomClass.click();

		Wait.WaitForElement(driver, OR.New_RoomClass_Deails);
		Wait.waitForElementToBeVisibile(By.xpath(OR.New_RoomClass_Deails), driver);
	}

	public void backToReservation(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Wait.explicit_wait_visibilityof_webelement(Navigate.Reservation_Backward_1, driver);
		Utility.ScrollToElement(Navigate.Reservation_Backward_1, driver);
		jse.executeScript("arguments[0].click();", Navigate.Reservation_Backward_1);
		Wait.explicit_wait_xpath(OR.Verify_Reservation_Page, driver);
	}

	public void reservationBackward3(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.Reservation_Backward_3);
		Wait.explicit_wait_xpath(OR.Verify_Reservation_Page, driver);
	}

	public ArrayList<String> secondaryRatesMenuItem(WebDriver driver) throws InterruptedException {

		Elements_On_All_Navigation navigation = new Elements_On_All_Navigation(driver);
		ArrayList<String> testStesp = new ArrayList<>();

		Wait.WaitForElement_ID(driver, OR.secondaryRatesMenuItem);
		Wait.waitForElementToBeVisibile(By.id(OR.secondaryRatesMenuItem), driver);
		Wait.waitForElementToBeClickable(By.id(OR.secondaryRatesMenuItem), driver);
		navigation.secondaryRatesMenuItem.click();
		testStesp.add("Click on rates tab");

		Wait.WaitForElement(driver, OR.Rates_Grid);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Rates_Grid), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Rates_Grid), driver);
		return testStesp;
	}

	public void Click_ReservationMenuFromTaxPage(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
	Wait.waitUntilPresenceOfElementLocated(OR.Reservation_Menu_FromTax, driver);
	Wait.explicit_wait_elementToBeClickable(Navigate.Click_ReservationMenu_fromTax, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.Click_ReservationMenu_fromTax);
		Wait.wait3Second();
		Wait.explicit_wait_xpath(OR.Verify_Reservation_Page, driver);

		// img[@class='nav-reservation nav_icon']
	}

	public void cpReservation_Backward(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.CPReservationBackward), driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.CPReservationBackward);
		// Wait.explicit_wait_xpath(OR.CPReservation_Page, driver);
	}
	
	public void cpReservationBackward(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.CP_Reservation_Backward), driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.CPReservationBackward);
		// Wait.explicit_wait_xpath(OR.CPReservation_Page, driver);
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectProperty> ' Description: <Select QA Property> '
	 * Input parameters: <WebDriver driver, String qaProperty> ' Return value:
	 * <ArrayList<String>> ' Created By: <Jamal Nasir> ' Created On:
	 * <05/06/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> selectProperty(WebDriver driver, String propertyName) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		ArrayList<String> testSteps = new ArrayList<String>();
		Wait.WaitForElementUsingClassName(driver, OR.QAProperty);
		Wait.waitForElementToBeVisibile(By.className(OR.QAProperty), driver);
		String defText = Navigate.QAProperty.getText();
		navigationLogger.info("Pre Selected QA Property : " + defText);

		if (propertyName.equals(defText)) {
			testSteps.add(propertyName + " already selected");
			navigationLogger.info(propertyName + " already selected");

		} else {
			Navigate.QAProperty.click();
			testSteps.add("Clicked QA Property");
			navigationLogger.info("Clicked QA Property");

			String QAPropertPath = "//div[@id='select2-drop']//*[contains(text(),'" + propertyName + "')]";
			Wait.WaitForElement(driver, QAPropertPath);

			WebElement QAPropertyElement = driver.findElement(By.xpath(QAPropertPath));
			QAPropertyElement.click();
			testSteps.add("Select property");

			Wait.WaitForElementUsingClassName(driver, OR.QAProperty);
			Wait.waitForElementToBeVisibile(By.className(OR.QAProperty), driver);
			String propertyType = Navigate.QAProperty.getText();
			assertTrue(propertyType.contains(propertyName), propertyName + " not selected");
			testSteps.add("Selected QA Property : " + propertyType);
			navigationLogger.info("Selected Property : " + propertyType);

		}
		return testSteps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <GenerateDailyFlashReport> ' Description: <Select today
	 * date in daily flash report> ' Input parameters: <WebDriver driver> '
	 * Return value: <ArrayList> ' Created By: <Jamal Nasir> ' Created On:
	 * <05/06/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> GenerateDailyFlashReport(WebDriver driver) throws InterruptedException 
	{

		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();

		Wait.WaitForElement(driver, OR.DailyFlashReportDate);
		Navigate.DailyFlashReportDate.click();
		test_steps.add("Clicked Date Icon");
		navigationLogger.info("Clicked Date Icon");

		Wait.WaitForElement(driver, OR.DailyFlashReportTodayDate);
		Navigate.DailyFlashReportTodayDate.click();
		test_steps.add("Selected Today's Date");
		navigationLogger.info("Selected Today's Date");

		Wait.WaitForElement(driver, OR.DailyFlashReportGoButton);
		Navigate.DailyFlashReportGoButton.click();
		test_steps.add("Clicked Go");
		navigationLogger.info("Clicked Go");

		return test_steps;

	}
	
	/*
	##########################################################################################################################################################################
	 * 
	 * ' Method Name: <mainSetupManu> 
	 * ' Description: <click steup> 
	 * ' Input parameters: <webdriver>
	 * ' Return value: <void>  ( void / String / ArrayList<String>  ...etc
	 * ' Created By: <Full name of developer>
	 * ' Created On:  <MM/DD/YYYY>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ 
	 * <MM/DD/YYYY>:<Farhan Ghaffar>:
	 * 1.Step modified: updated waits and xpath
	 * 2.Step modified  
	 * 
	 * ##########################################################################################################################################################################
	 */
	
	public void mainSetupManu(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation navigate = new Elements_On_All_Navigation(driver);
		Wait.WaitForElement(driver, OR.SetUP);
		Wait.waitForElementToBeVisibile(By.xpath(OR.SetUP), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.SetUP), driver);
		Utility.ScrollToElement_NoWait(navigate.setUp, driver);
		navigate.setUp.click();
		
		Wait.WaitForElement(driver, OR.SecondaryTitle);
		Wait.waitForElementToBeVisibile(By.xpath(OR.SecondaryTitle), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.SecondaryTitle), driver);
		
		navigationLogger.info("Navigated to Setup");
	}
	/*
	##########################################################################################################################################################################
	 * 
	 * ' Method Name: <clickOnNewRoomClass> 
	 * ' Description: <click on new room class button inside room classes> 
	 * ' Input parameters: <webdriver>
	 * ' Return value: <void>  ( void / String / ArrayList<String>  ...etc
	 * ' Created By: <Full name of developer>
	 * ' Created On:  <MM/DD/YYYY>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ 
	 * <MM/DD/YYYY>:<Farhan Ghaffar>:
	 * 1.Step modified: updated waits and xpath 
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void clickOnNewRoomClass(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		
		Wait.WaitForElement(driver, OR.NewRoomClass);
		Wait.waitForElementToBeVisibile(By.xpath(OR.NewRoomClass), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.NewRoomClass), driver);
		Navigate.NewRoomClass.click();
	}
	/*
	##########################################################################################################################################################################
	 * 
	 * ' Method Name: <navigateToReservations> 
	 * ' Description: <navigate to reservations> 
	 * ' Input parameters: <webdriver>
	 * ' Return value: <void>  ( void / String / ArrayList<String>  ...etc
	 * ' Created By: <Full name of developer>
	 * ' Created On:  <MM/DD/YYYY>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ 
	 * <MM/DD/YYYY>:<Farhan Ghaffar>:
	 * 1.Step modified: updated waits
	 * 2.Step modified  
	 * 
	 * ##########################################################################################################################################################################
	 */

	public void navigateToReservations(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation navigate = new Elements_On_All_Navigation(driver);

		Wait.WaitForElement(driver, OR.Click_Reservation);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Click_Reservation), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Click_Reservation), driver);
		Utility.ScrollToElement(navigate.Click_Reservation, driver);
		//navigate.Click_Reservation.click();
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].click();", navigate.Click_Reservation);
		} catch (Exception e) {
			jse.executeScript("arguments[0].click();",navigate.Click_Reservation);
		}
		
		Wait.WaitForElement(driver, OR.Verify_Reservation_Page);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Verify_Reservation_Page), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Verify_Reservation_Page), driver);
	}
	/*
	##########################################################################################################################################################################
	 * 
	 * ' Method Name: <secondaryGroupsManu> 
	 * ' Description: <navigate to groups> 
	 * ' Input parameters: <webdriver>
	 * ' Return value: <void>  ( void / String / ArrayList<String>  ...etc
	 * ' Created By: <Full name of developer>
	 * ' Created On:  <MM/DD/YYYY>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ 
	 * <MM/DD/YYYY>:<Farhan Ghaffar>:
	 * 1.Step modified: updated waits and xpath
	 * 2.Step modified  
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void secondaryGroupsManu(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		
		Wait.WaitForElement(driver, OR.Groups);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Groups), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Groups), driver);
		Utility.ScrollToElement(Navigate.Groups, driver);
		Navigate.Groups.click();
		
		Wait.WaitForElement(driver, OR.Groups_text);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Groups_text), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Groups_text), driver);
		navigationLogger.info("Navigated to Groups");
		
	}

	public void navigateToTapeChartFromReservations(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation navigation = new Elements_On_All_Navigation(driver);
		Wait.WaitForElement(driver, OR.MoveToTapeChart);
		Wait.waitForElementToBeVisibile(By.xpath(OR.MoveToTapeChart), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.MoveToTapeChart), driver);
		Utility.ScrollToElement_NoWait(navigation.MoveToTapeChart, driver);
		navigation.MoveToTapeChart.click();
		navigationLogger.info("Clicked on tape chart sub-menu option");
		Wait.waitUntilPresenceOfElementLocated(OR.tapeChartGridLayOut, driver);
	}

	public void navigateRoomClassTab(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation navigate = new Elements_On_All_Navigation(driver);
		Wait.WaitForElement(driver, OR.Roomclass2);
		Utility.ScrollToElement(navigate.Roomclass2, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", navigate.Roomclass2);
		Wait.explicit_wait_xpath(OR.Roomclass_grid, driver);
		navigationLogger.info("Navigated to Room Class");
	}
	public void clickRoomStatusTab(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Wait.WaitForElement(driver,OR.RoomStatusTab);
		Utility.ScrollToElement(Navigate.RoomStatusTab, driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.RoomStatusTab), driver);
		Navigate.RoomStatusTab.click();
		Wait.explicit_wait_xpath(OR.RoomStatusPage, driver);
	}



	
	// Document Template
	public void navigateDocumentTemplate(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		try {
			Wait.WaitForElement(driver, OR.Document_Template);
			Wait.waitForElementToBeVisibile(By.xpath(OR.Document_Template), driver);
			Wait.waitForElementToBeClickable(By.xpath(OR.Document_Template), driver);
			Navigate.Document_Template.click();
		} catch (Exception e) {
			Navigate.Document_Template1.click();
		}

		Wait.explicit_wait_xpath(OR.Documnet_Template_Grid, driver);

		navigationLogger.info("Navigated to  DocumentTemplates");
	}

	public void reservation(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		Utility.ScrollToElement(Navigate.Click_Reservation, driver);
		Wait.WaitForElement(driver, OR.Click_Reservation);
		Utility.ScrollToElement(Navigate.Click_Reservation, driver);

		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.elementToBeClickable(Navigate.Click_Reservation));

		Navigate.Click_Reservation.click();
		Wait.explicit_wait_xpath(OR.Verify_Reservation_Page, driver);
		Wait.WaitForElement(driver, OR.Verify_Reservation_Page);
	}
	public void navigateTapeChart(WebDriver driver, ExtentTest test) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("arguments[0].click();", Navigate.Tape_Chart_1);
		} catch (Exception e) {
			jse.executeScript("arguments[0].click();", Navigate.TapeChart);
		}
		navigationLogger.info("Clicked on tape chart sub-menu option");
		Wait.waitUntilPresenceOfElementLocated(OR.tapeChartGridLayOut, driver);
	}

	public void clickOnTapeChart(WebDriver driver) throws InterruptedException {
		Elements_On_All_Navigation navigate = new Elements_On_All_Navigation(driver);
		
		
			Wait.WaitForElement(driver, OR.Tape_Chart);
			Wait.waitForElementToBeVisibile(By.xpath(OR.Tape_Chart), driver);
			Wait.waitForElementToBeClickable(By.xpath(OR.Tape_Chart), driver);
			Utility.ScrollToElement_NoWait(navigate.Tape_Chart, driver);
			navigate.Tape_Chart.click();
			navigationLogger.info("Clicked on tape chart sub-menu option");
			Wait.waitUntilPresenceOfElementLocated(OR.tapeChartGridLayOut, driver);
	}
	
	public boolean isPropertyExist(WebDriver driver, String propertyName) throws InterruptedException{
		
		Elements_On_All_Navigation navigate = new Elements_On_All_Navigation(driver);
		Wait.WaitForElementUsingClassName(driver, OR.QAProperty);
		Wait.waitForElementToBeVisibile(By.className(OR.QAProperty), driver);
		Wait.waitForElementToBeClickable(By.className(OR.QAProperty), driver);
		Utility.ScrollToElement_NoWait(navigate.QAProperty, driver);
		navigate.QAProperty.click();
		navigationLogger.info("Clicked Property");
		
		Wait.WaitForElement(driver, OR.PropertyList);
		Wait.waitForElementToBeVisibile(By.xpath(OR.PropertyList), driver);
		navigationLogger.info("size of list: "+navigate.PropertyList.size());
		
		boolean isProperty = false;
		for (int i = 0; i < navigate.PropertyList.size();i++) {
			String getPropertyName = navigate.PropertyList.get(i).getText();
			getPropertyName = getPropertyName.trim();	
			if (getPropertyName.equals(propertyName)) {
				isProperty = true;
				String propertPath = "//div[@id='select2-drop']//*[contains(text(),'" + propertyName + "')]";
				WebElement element = driver.findElement(By.xpath(propertPath));
				element.click();
				break;
			}
		}
		
		driver.navigate().refresh();
		
		return isProperty;
	}

	public String getDefaultProperty(WebDriver driver) throws InterruptedException{
		
		Elements_On_All_Navigation navigate = new Elements_On_All_Navigation(driver);
		Wait.WaitForElementUsingClassName(driver, OR.QAProperty);
		Wait.waitForElementToBeVisibile(By.className(OR.QAProperty), driver);
		Wait.waitForElementToBeClickable(By.className(OR.QAProperty), driver);
		Utility.ScrollToElement_NoWait(navigate.QAProperty, driver);
		return navigate.GetDefultProperty.getText();
	}

		public void roomClass(WebDriver driver, ArrayList<String> test_steps) {
		RoomClass(driver);
		test_steps.add("Navigate to Room Class");
		navigationLogger.info("Navigate to Room Class");
	}
	
	// Properties

		public void properties(WebDriver driver) {
			Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);

			Navigate.Propeties.click();
			Wait.explicit_wait_xpath(OR.Propety_Grid, driver);
			navigationLogger.info("Navigated to Properties");

		}
		
		// Inventory Navigation

		public void inventory(WebDriver driver) throws InterruptedException {
			Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			try {
				jse.executeScript("arguments[0].click();", Navigate.Inventory);
				Wait.waitForElementToBeVisibile(By.xpath(OR.Inventory_Grid), driver);
			} catch (Exception e) {
				driver.navigate().refresh();
				jse.executeScript("arguments[0].click();", Navigate.Inventory2);
				Wait.waitForElementToBeVisibile(By.xpath(OR.Inventory_Grid), driver);
			}
			Wait.waitForElementToBeVisibile(By.xpath(OR.Inventory_Menu_Title), driver);
			navigationLogger.info("Navigated to Inventory");
		}
		
		public void rates1(WebDriver driver) throws InterruptedException {
			Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);

			try {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				Utility.ScrollToElement(Navigate.Rates1, driver);
				jse.executeScript("arguments[0].click();", Navigate.Rates1);
				// Navigate.Rates1.click();
				// Wait.explicit_wait_xpath(OR.Rates_Grid);
				Wait.explicit_wait_visibilityof_webelement_120(Navigate.Rates_Grid, driver);
				navigationLogger.info("Navigated to Rates");
			} catch (Exception e) {
				Navigate.rates2.click();
				Wait.explicit_wait_visibilityof_webelement_120(Navigate.Rates_Grid, driver);
				navigationLogger.info("Navigated to Rates");
			}
		}
		
		public void reservation_Backward_1(WebDriver driver) throws InterruptedException {
			Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			Wait.explicit_wait_visibilityof_webelement(Navigate.Reservation_Backward_1, driver);
			Utility.ScrollToElement(Navigate.Reservation_Backward_1, driver);
			jse.executeScript("arguments[0].click();", Navigate.Reservation_Backward_1);
			Wait.explicit_wait_xpath(OR.Verify_Reservation_Page, driver);
		}
		
		// Task Management Tab Exist
		public void taskManagement_TabExist(WebDriver driver) {
			if (!driver.findElements(By.xpath(OR.Task_Management)).isEmpty()) {
				navigationLogger.info("Task_Management tab Exist");
			} else {
				assertTrue(false, "Task_Management Tab does not exist");
			}
		}
		

		// Task Managemnet

		public void taskManagement(WebDriver driver) {
			Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
			Navigate.Task_Management.click();
		//	Wait.waitTillElementDisplayed(driver, OR_GuestServices.GS_Loading);
			Wait.explicit_wait_xpath(OR.TaskManagement_Tab, driver);

		}
		
		public void reservation_Backward_2(WebDriver driver) throws InterruptedException {
			Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
			Wait.WaitForElement(driver, OR.Reservation_Backward_2);
			Utility.ScrollToElement(Navigate.Reservation_Backward_2, driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", Navigate.Reservation_Backward_2);
			Wait.explicit_wait_xpath(OR.Verify_Reservation_Page, driver);
		}

	public void Reservation_BackwardAdmin(WebDriver driver) {
		Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", Navigate.Reservation_Backward_Admin);
		// Navigate.Reservation_Backward.click();
		Wait.explicit_wait_xpath(OR.Verify_Reservation_Page, driver);
	}

public void Reservation(WebDriver driver) throws InterruptedException {
	Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
	Utility.ScrollToElement(Navigate.Click_Reservation, driver);
	Wait.WaitForElement(driver, OR.Click_Reservation);
	Utility.ScrollToElement(Navigate.Click_Reservation, driver);
	
	WebDriverWait wait = new WebDriverWait(driver, 90);
	wait.until(ExpectedConditions.elementToBeClickable(Navigate.Click_Reservation));

	Navigate.Click_Reservation.click();
	Wait.explicit_wait_xpath(OR.Verify_Reservation_Page, driver);
	Wait.WaitForElement(driver, OR.Verify_Reservation_Page);

}
public ArrayList<String> ratesGrid(WebDriver driver) {
	Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
	ArrayList<String> testSteps = new ArrayList<>();
	/*
	 * Navigate.Rategrid_Inventory.click(); //
	 * Wait.explicit_wait_xpath(OR.Overview_grid);
	 * Wait.explicit_wait_xpath(OR.OverViewTab, driver);
	 */
	Wait.WaitForElement(driver, OR.RatesGrid);
	Wait.waitForElementToBeVisibile(By.xpath(OR.RatesGrid), driver);
	Wait.waitForElementToBeClickable(By.xpath(OR.RatesGrid), driver);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	try {
		jse.executeScript("arguments[0].click();", Navigate.RatesGrid);
		// Navigate.Overview.click();
	}

	catch (Exception e) {
		jse.executeScript("arguments[0].click();", Navigate.RatesGrid1);
	}

	// Wait.WaitForElement(driver, OR.OverviewBulkUpdate);
	Wait.WaitForElement(driver, OR.rateGridBulkUpdateButton);
	navigationLogger.info("Navigated to Overview");
	testSteps.add("Navigated to Overview");
	
	return testSteps;

}
public void roomClass(WebDriver driver) {
	Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	try {
		jse.executeScript("arguments[0].click();", Navigate.Roomclass);
	} catch (Exception e) {
		jse.executeScript("arguments[0].click();", Navigate.Roomclass1);
	}
	navigationLogger.info("Navigated to Room Class");
}


public void ratesGrid(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {
	Elements_On_All_Navigation Navigate = new Elements_On_All_Navigation(driver);
	Wait.WaitForElement(driver, OR.RatesGrid);
	Utility.ScrollToElement(Navigate.RatesGrid, driver);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	jse.executeScript("arguments[0].click();", Navigate.RatesGrid);	
	Wait.WaitForElement(driver, OR_RateGrid.ratesTab);
	Wait.waitUntilPageLoadNotCompleted(driver, 30);
	test_steps.add("Navigated to RateGrid");
	navigationLogger.info("Navigated to RateGrid");

}

}
