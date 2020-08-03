package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.innroad.inncenter.interfaces.IRoomStatus;
import com.innroad.inncenter.pages.RoomStatusPage;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_GuestServices;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_On_All_Navigation;
import com.innroad.inncenter.webelements.WebElementsRoomStatus;
import com.innroad.inncenter.webelements.WebElements_RoomStatus;

public class RoomStatus implements IRoomStatus {

	public static Logger RoomStatusLogger = Logger.getLogger("RoomStatus");
	public int OccupiedNo = 0, VacantNo = 0;

	@Override
	public void SearchRoomZoonOccupancy(WebDriver driver, String Value) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		System.out.println("heere" + driver.findElement(By.xpath("//li[@class='blue active']//span")).getText());
		if (!driver.findElement(By.xpath("//li[@class='blue active']//span")).getText().equals("0")) {
			driver.findElements(By.cssSelector(".form-control.gs-input.ng-untouched.ng-pristine.ng-valid")).get(1)
					.sendKeys(Value);
			roomstatus.RoomStatus_SearchButon.click();
			assertTrue(driver.findElement(By.cssSelector(".col-sm-12.mobPadLR-0")).isDisplayed(),
					"Failed:Searched didn't work");
		}
	}

	@Override
	public void VerifyQuickStats(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);

		assertTrue(roomstatus.VerifyRoomStats_Dirty.isDisplayed(), "Failed: Dirty Stats not Displayed");
		assertTrue(roomstatus.VerifyRoomStats_Clean.isDisplayed(), "Failed: Clean Stats not Displayed");
		assertTrue(roomstatus.VerifyRoomStats_Inspection.isDisplayed(), "Failed: Inspection Stats not Displayed");
		assertTrue(roomstatus.VerifyRoomStats_OutofOrder.isDisplayed(), "Failed: OutofOrder Stats not Displayed");
		assertTrue(roomstatus.VerifyRoomStats_All.isDisplayed(), "Failed: All Stats not Displayed");

	}

	@Override
	public void VerifySortFuntionality(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		roomstatus.VerifySortFuncButton.click();
		assertTrue(roomstatus.VerifySortFunc_RoomNum.isDisplayed(), "Failed: Room# not displayed");
		assertTrue(roomstatus.VerifySortFunc_Zone.isDisplayed(), "Failed: Zone not displayed");
		assertTrue(roomstatus.VerifySortFunc_ArrivalDue.isDisplayed(), "Failed: Arrival Due not displayed");
	}

	public void verifySortFuntionality1(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		roomstatus.VerifySortFuncButton1.click();
		assertTrue(roomstatus.VerifySortFunc_RoomNum.isDisplayed(), "Failed: Room# not displayed");
		assertTrue(roomstatus.VerifySortFunc_Zone.isDisplayed(), "Failed: Zone not displayed");
		assertTrue(roomstatus.VerifySortFunc_ArrivalDue.isDisplayed(), "Failed: Arrival Due not displayed");
	}

	public void SortByZone(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		roomstatus.VerifySortFuncButton.click();
		Thread.sleep(10000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", roomstatus.VerifySortFunc_Zone);

	}

	public void SortByArrivalDue1(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		roomstatus.VerifySortFuncButton1.click();
		Thread.sleep(10000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", roomstatus.VerifySortFunc_ArrivalDue);
	}

	public void SortByRoomNum1(WebDriver driver) throws InterruptedException {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		roomstatus.VerifySortFuncButton1.click();
		Thread.sleep(10000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", roomstatus.VerifySortFunc_RoomNum);

	}

	public void SortByZone1(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		roomstatus.VerifySortFuncButton1.click();
		Thread.sleep(10000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", roomstatus.VerifySortFunc_Zone);

	}

	@Override
	public void SortByArrivalDue(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		roomstatus.VerifySortFuncButton.click();
		Thread.sleep(10000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", roomstatus.VerifySortFunc_ArrivalDue);
	}

	@Override
	public void SortByRoomNum(WebDriver driver) throws InterruptedException {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		roomstatus.VerifySortFuncButton.click();
		Thread.sleep(10000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", roomstatus.VerifySortFunc_RoomNum);

	}

	@Override
	public void MouserHoverOverElemet(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);

		WebElement ele = driver.findElement(By.xpath("//div[contains(@data-sortorder,'1')]"));
		// Create object 'action' of an Actions class
		Actions action = new Actions(driver);
		// Mouseover on an element
		action.moveToElement(ele).perform();

		// assertTrue(roomstatus.RoomStatus_RoomDirtyLabel.isDisplayed(),
		// "Failed: Diry/Clean DropDown is not displayed");
		// assertTrue(roomstatus.RoomStatus_RoomTaskLabel.isDisplayed(),
		// "Failed: Task DropDown is not displayed");

		GetUpdateStatus_States(driver, "Dirty");

	}

	@Override
	public void ClickCleanDropDownButton(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		roomstatus.RoomStatus_RoomDirtyLabelDropDownButton.click();
		assertTrue(roomstatus.RoomStatus_RoomDirtyLabelButton_DirtyCate.isDisplayed(),
				"Failed: Dirty DropDown ColorName is not displayed");
		assertTrue(roomstatus.RoomStatus_RoomDirtyLabelButton_CleanCate.isDisplayed(),
				"Failed: Clean DropDown ColorName is not displayed");
		// assertTrue(roomstatus.RoomStatus_RoomDirtyLabelButton_InspectionCate.isDisplayed(),
		// "Failed: Inspection DropDown ColorName is not displayed");

	}

	@Override
	public void SelectOneRoomStatusCategory(WebDriver driver, String ColorName) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		try {
			if (ColorName.equals("Clean") || ColorName.equalsIgnoreCase("clean")) {
				roomstatus.RoomStatus_RoomDirtyLabelButton_CleanCate.click();
				Wait.wait5Second();
				assertTrue(
						roomstatus.RoomStatus_Element.getCssValue("background-color").equals("green")
								|| roomstatus.RoomStatus_Element.getCssValue("background-color").equals("Green"),
						"Failed: Specified  Color is not correct");

			} else if (ColorName.equals("Dirty") || ColorName.equalsIgnoreCase("dirty")) {
				roomstatus.RoomStatus_RoomDirtyLabelButton_DirtyCate.click();
				Wait.wait5Second();
				assertTrue(
						roomstatus.RoomStatus_Element.getCssValue("background-color").equals("red")
								| roomstatus.RoomStatus_Element.getCssValue("background-color").equals("Red"),
						"Failed: Specified  Color is not correct");
			}

			else if (ColorName.equals("Inspection") || ColorName.equalsIgnoreCase("inspection")) {
				roomstatus.RoomStatus_RoomDirtyLabelButton_InspectionCate.click();
				Wait.wait5Second();
				assertTrue(
						roomstatus.RoomStatus_Element.getCssValue("background-color").equals("orange")
								|| roomstatus.RoomStatus_Element.getCssValue("background-color").equals("Orange"),
						"Failed: Specified  Color is not correct");
			}
		} catch (Exception e) {
			Assert.assertTrue(false);

		} catch (Error e) {
			Assert.assertTrue(false);
		}

	}

	@Override
	public void ClickFirstElementRadioButton(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		Actions action = new Actions(driver);
		// Mouseover on an element
		action.moveToElement(roomstatus.RoomStatus_Room_RadioButton).perform();
		assertTrue(roomstatus.RoomStatus_Room_RadioButton.isEnabled(), "Failed: Radio Button is not Displayed");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", roomstatus.RoomStatus_Room_RadioButton);

	}

	@Override
	public void ClickUpdateStatusButton(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		try {

			Utility.ElementFinderUntillNotShow(By.xpath(OR.UpdateStatusButton), driver);
			roomstatus.UpdateStatusButton.click();
		} catch (Exception e) {

			Utility.ElementFinderUntillNotShow(By.xpath(OR.UpdateStatusButton1), driver);
			roomstatus.UpdateStatusButton1.click();
		}
		assertTrue(roomstatus.UpdateStatus_Clean.isDisplayed(), "Failed: Clean Button is not Displayed");
		assertTrue(roomstatus.UpdateStatus_Dirty.isDisplayed(), "Failed: Dirty Button is not Displayed");
		// assertTrue(roomstatus.UpdateStatus_Inspection.isDisplayed(), "Failed:
		// Inspection Button is not Displayed");

	}

	@Override
	public void ClickUpdateStatus_CleanButton(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		// roomstatus.UpdateStatusButton.click();
		// Thread.sleep(10000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", roomstatus.UpdateStatus_Dirty);

		// MouserHoverOverElemet(driver);
		// assertTrue(roomstatus.RoomStatus_RoomDirtyLabelButton_DirtyCate.isDisplayed(),
		// "Failed: Dirty DropDown ColorName is not displayed");
		// assertTrue(roomstatus.UpdateStatus_Dirty.isDisplayed(), "Failed:
		// Clean Button is not Displayed");

	}

	static String StatsBefore = "";
	static String StatsAfter = "";

	public void updateStatusAndCategoryStatsVerification(WebDriver driver) throws InterruptedException {

		StatsBefore = GetUpdateStatus_States(driver, "Dirty");
		Wait.wait10Second();
		StatsAfter = GetUpdateStatus_States(driver, "Dirty");
		assertTrue(StatsBefore != StatsAfter, "Failed Dirty Stats not Updated");

	}

	public String GetUpdateStatus_States(WebDriver driver, String Category) throws InterruptedException {
		if (Category.equals("Dirty")) {
			String DirtyStats = driver.findElement(By.className("color-red")).getText();
			return DirtyStats;
		} else if (Category.equals("Clean")) {
			String CleanStats = driver.findElement(By.className("color-green")).getText();
			return CleanStats;
		} else if (Category.equals("Inspection")) {
			String InspectionStats = driver.findElement(By.className("color-orange")).getText();
			return InspectionStats;
		}

		return "";
	}

	public void Reports(WebDriver driver) {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		Wait.explicit_wait_xpath(OR.Report, driver);
		roomstatus.Report.click();
		assertTrue(driver.findElement(By.xpath(OR.Report_RoomStatus)).isDisplayed(),
				"Failed: Room Status does not exist");
		assertTrue(driver.findElement(By.xpath(OR.Report_RoomStatusTask)).isDisplayed(),
				"Failed: Room Status with Task does not exist");
		RoomStatusLogger.info("Click Reports");

	}

	public void Report_RoomStatus(WebDriver driver) throws InterruptedException {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		Wait.explicit_wait_xpath(OR.Report_RoomStatus, driver);
		roomstatus.Report_RoomStatus.click();
		Wait.wait3Second();
		RoomStatusLogger.info("Click Room Status");
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		RoomStatusLogger.info("Switch to report tab");

		VerifyLoading(driver);

		try {
			Wait.WaitForElement(driver, OR.ReportPage);
			Wait.waitForElementToBeVisibile(By.xpath(OR.ReportPage), driver);

			assertTrue(driver.findElement(By.xpath(OR.ReportPage)).isDisplayed(), "Failed: Room Status Report Page");
			assertTrue(driver.findElement(By.xpath(OR.ReportPage_Table)).isDisplayed(), "Failed: Room Status Report");
			RoomStatusLogger.info("Report: Room Status ");
		} catch (Exception e) {
			// TODO: handle exception
		}

		driver.close();
		RoomStatusLogger.info("Close Tab");
		driver.switchTo().window(tabs2.get(0));
		Wait.explicit_wait_xpath(OR.RoomStatusPage, driver);
		RoomStatusLogger.info("Room Status Page");

	}

	public void Report_RoomStatusWithTask(WebDriver driver) throws InterruptedException {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		Wait.explicit_wait_xpath(OR.Report_RoomStatusTask, driver);
		roomstatus.Report_RoomStatusTask.click();
		Wait.wait2Second();
		RoomStatusLogger.info("Click Room Status with Task");
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		RoomStatusLogger.info("Switch to report tab");
		Wait.wait2Second();

		VerifyLoading(driver);

		Wait.WaitForElement(driver, OR.ReportPage);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ReportPage), driver);

		assertTrue(driver.findElement(By.xpath(OR.ReportPage)).isDisplayed(),
				"Failed: Room Status with Task Report Page");
		assertTrue(driver.findElement(By.xpath(OR.ReportPage_Table)).isDisplayed(),
				"Failed: Room Status with Task Report");
		RoomStatusLogger.info("Report: Room Status with Task");
		driver.close();
		RoomStatusLogger.info("Close Tab");
		driver.switchTo().window(tabs2.get(0));
		Wait.explicit_wait_xpath(OR.RoomStatusPage, driver);
		RoomStatusLogger.info("Room Status Page");

	}

	public void VerifyInspectionQuickStats(WebDriver driver, boolean inspectionstatus) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		if (inspectionstatus)
			assertTrue(roomstatus.VerifyRoomStats_Inspection.isDisplayed(), "Failed: Inspection Stats not Displayed");
		// else
		// assertTrue(!roomstatus.VerifyRoomStats_Inspection.isDisplayed(),
		// "Failed: Inspection Stats Displayed");
	}

	public void ClickStatesRoomStatus(WebDriver driver, String RoomStatus) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		if (RoomStatus.equals("Clean") || RoomStatus.equalsIgnoreCase("clean")) {
			roomstatus.VerifyRoomStats_Clean.click();
			Wait.wait5Second();
			String one = roomstatus.VerifyRoomStats_Clean.getCssValue("color");
			System.out.println("Color" + one);
			String Path = "(//div[contains(@data-sortorder,'1')])[1]/div";
			// System.out.println("Room Color : " +
			// driver.findElement(By.xpath(Path)).getCssValue("background-color"));
			assertTrue(driver.findElement(By.xpath(Path)).getCssValue("background-color").equals(one),
					"Failed: Specified  Color is not correct");

		} else if (RoomStatus.equals("Dirty") || RoomStatus.equalsIgnoreCase("dirty")) {

			roomstatus.VerifyRoomStats_Dirty.click();
			Wait.wait5Second();
			String one = roomstatus.VerifyRoomStats_Dirty.getCssValue("color");
			System.out.println("Color" + one);
			String Path = "(//div[contains(@data-sortorder,'1')])[1]/div";
			// System.out.println("Room Color : " +
			// driver.findElement(By.xpath(Path)).getCssValue("background-color"));
			assertTrue(driver.findElement(By.xpath(Path)).getCssValue("background-color").equals(one),
					"Failed: Specified  Color is not correct");
		}

		else if (RoomStatus.equals("Inspection") || RoomStatus.equalsIgnoreCase("inspection")) {
			roomstatus.VerifyRoomStats_Inspection.click();
			Wait.wait5Second();
			String one = roomstatus.VerifyRoomStats_Inspection.getCssValue("color");
			System.out.println("Color" + one);
			String Path = "(//div[contains(@data-sortorder,'1')])[1]/div";
			// System.out.println("Room Color : " +
			// driver.findElement(By.xpath(Path)).getCssValue("background-color"));
			assertTrue(driver.findElement(By.xpath(Path)).getCssValue("background-color").equals(one),
					"Failed: Specified  Color is not correct");
		}
	}

	public void VerifyRoomStatus(WebDriver driver, String RoomStatus, String RoomClassName, String RoomNumber,
			String RoomClassNumber) throws InterruptedException {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		driver.navigate().refresh();
		Wait.wait3Second();
		String StatusPath = "//span[text()='" + RoomStatus + "']";
		WebElement Status = driver.findElement(By.xpath(StatusPath));
		Utility.ScrollToElement(Status, driver);
		Status.click();
		Wait.wait5Second();
		roomstatus.DirtyTab.click();
		Wait.wait5Second();
		String RoomPath = "";
		WebElement Room = null;
		try {
			RoomPath = "//div[@data-roomclass='" + RoomClassNumber + "'and @data-roomname='"
					+ RoomNumber.replaceAll("\\s+", "") + "' and @data-roomclassname='"
					+ RoomClassName.toLowerCase().replaceAll("\\s+", "") + "']";
			Room = driver.findElement(By.xpath(RoomPath));
		} catch (Exception e) {
			RoomPath = "//div[@data-roomname='" + RoomNumber.replaceAll("\\s+", "") + "' and @data-roomclassname='"
					+ RoomClassName.toLowerCase() + "']";
			Room = driver.findElement(By.xpath(RoomPath));
		}

		Utility.ScrollToElement(Room, driver);
		assertTrue(Room.isDisplayed(), "Failed : Room : " + RoomClassName + " " + RoomNumber + " is not " + RoomStatus);

	}

	public void MovetoRoom_ChangeStatus(WebDriver driver, String RoomStatus, String RoomClassName,
			String RoomClassNumber, String RoomNumber) throws InterruptedException {
		String RoomPath = "//span[text()='vacant']//ancestor::div[@data-roomclass='" + RoomClassNumber
				+ "' and @data-roomname='" + RoomNumber + "' and @data-roomclassname='" + RoomClassName.toLowerCase()
				+ "']";
		System.out.println(RoomPath);
		WebElement Room = driver.findElement(By.xpath(RoomPath));
		Utility.ScrollToElement(Room, driver);
		Actions builder = new Actions(driver);
		builder.moveToElement(Room).build().perform();
		WebDriverWait wait1 = new WebDriverWait(driver, 5);
		String StatusPath = "(//span[text()='vacant']//ancestor::div[@data-roomclass='" + RoomClassNumber
				+ "' and @data-roomname='" + RoomNumber + "' and @data-roomclassname='" + RoomClassName.toLowerCase()
				+ "'])[1]//following-sibling::i[contains(@class,'fa fa-angle')]";
		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(StatusPath)));
		driver.findElement(By.xpath(StatusPath)).click();
		Wait.wait2Second();
		RoomNumber = Room.getAttribute("data-roomname");
		String Path = "((//span[text()='vacant']//ancestor::div[@data-roomclass='" + RoomClassNumber
				+ "' and @data-roomname='" + RoomNumber + "' and @data-roomclassname='" + RoomClassName.toLowerCase()
				+ "'])[1]//following-sibling::a[contains(text(),'" + RoomStatus + "')])[last()]";
		System.out.println(Path);
		driver.findElement(By.xpath(Path)).click();
		Wait.wait2Second();

	}

	public String MovetoRoom_ChangeStatus(WebDriver driver, String RoomStatus, String RoomClassName,
			String RoomClassNumber) throws InterruptedException {
		String RoomNumber = "";
		String RoomPath = "(//span[text()='vacant']//ancestor::div[@data-roomclass='" + RoomClassNumber
				+ "' and @data-roomclassname='" + RoomClassName.toLowerCase().replaceAll("\\s+", "") + "'])[1]";
		WebElement Room = driver.findElement(By.xpath(RoomPath));
		Actions builder = new Actions(driver);
		builder.moveToElement(Room).build().perform();
		WebDriverWait wait1 = new WebDriverWait(driver, 5);
		String StatusPath = "(//span[text()='vacant']//ancestor::div[@data-roomclassname='"
				+ RoomClassName.toLowerCase().replaceAll("\\s+", "")
				+ "'])[1]//following-sibling::i[contains(@class,'fa fa-angle')]";
		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(StatusPath)));
		driver.findElement(By.xpath(StatusPath)).click();
		Wait.wait2Second();
		RoomNumber = Room.getAttribute("data-roomname");
		String Path = "(//span[text()='vacant']//ancestor::div[@data-roomclassname='"
				+ RoomClassName.toLowerCase().replaceAll("\\s+", "") + "'])[1]//following-sibling::";
		if (RoomStatus.contains("Clean")) {
			Path = Path + "li[3]/a";
		} else if (RoomStatus.contains("Inspection")) {
			Path = Path + "li[1]/a";
		} else if (RoomStatus.contains("Dirty")) {
			Path = Path + "li[2]/a";
		}
		driver.findElement(By.xpath(Path)).click();
		Wait.wait2Second();
		return RoomNumber;

	}

	public void verifyRoomStatusTabEnabled(WebDriver driver, ArrayList<String> test_steps) {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		Wait.WaitForElement(driver, OR.RoomStatus);
		Wait.explicit_wait_visibilityof_webelement(roomstatus.RoomStatusTab, driver);
		assertTrue(roomstatus.RoomStatusTab.isEnabled(), " Failed: to verify RoomStatus Tab");
		test_steps.add("RoomStatus Tab Enabled");
		RoomStatusLogger.info("RoomStatus Tab Enabled");

	}

	public void verifiedGuestServicesDataLoadedOrNot(WebDriver driver, ArrayList<String> test_steps) {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		Wait.WaitForElement(driver, OR_GuestServices.RoomStatus_AllStatNumber);
		String getNumberofAllQuickStats = roomstatus.RoomStatus_AllQuickStatsNumber.getText();
		boolean isExist = !getNumberofAllQuickStats.equals("0");
		if (isExist) {
			test_steps.add("Successfully Data Loaded on Guest Services Screen");
			RoomStatusLogger.info("Successfully Data Loaded on Guest Services Screen");
		} else {
			test_steps.add("Failed to Load Data on Guest Services Screen");
			RoomStatusLogger.info("Failed to Load Data on Guest Services Screen");
		}
	}

	public void verifySearchTestBoxPresented(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		assertTrue(roomstatus.RoomStatus_SearchField.isDisplayed(), "Failed:Search Text Box not displayed");
		// test_steps.add(" Search Text Box Displayed.");
		RoomStatusLogger.info("Search  Text Box Displayed.");
	}

	private void inputSearchAndClickSearchButton(WebDriver driver, String Value, ArrayList<String> test_steps)
			throws InterruptedException {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		Wait.WaitForElement(driver, OR.RoomStatus_SearchField);
		roomstatus.RoomStatus_SearchField.clear();
		test_steps.add("Clear Search Field.");
		RoomStatusLogger.info("Clear Search Field.");
		roomstatus.RoomStatus_SearchField.sendKeys(Value);
		test_steps.add("Input Search Value : " + "<b>" + Value + "</b>");
		RoomStatusLogger.info("Input Search Value : " + Value);
		Utility.ScrollToElement(roomstatus.RoomStatus_SearchButon, driver);
		roomstatus.RoomStatus_SearchButon.click();
		test_steps.add("Click Search Button");
		RoomStatusLogger.info("Click Search Button.");
		/*
		 * String
		 * path="//div[@class='subTitle'][contains(text(),'Loading...')]"; //
		 * Wait.waitTillElementDisplayed(driver, path);
		 * Wait.explicit_wait_absenceofelement(path, 20, driver);
		 */
		Wait.wait60Second();
	}

	public void searchByRoomHash(WebDriver driver, String RoomNO, String RoomName, ArrayList<String> test_steps)
			throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		inputSearchAndClickSearchButton(driver, RoomNO, test_steps);
		Wait.explicit_wait_visibilityof_webelement(roomstatus.RoomStatus_RoomCardList.get(0), driver);
		assertTrue(!(roomstatus.RoomStatus_AllQuickStatsNumber.getText().equals("0")), "Failed:Search not work");
		test_steps.add("Search  working.");
		RoomStatusLogger.info("Search  working.");
		assertEquals(roomstatus.RoomStatus_AllQuickStatsNumber.getText(),
				(Integer.toString(roomstatus.RoomStatus_RoomCardList.size())),
				"Failed:to Match Room Tile with Quick Stats Number");
		test_steps.add("Total All Quick Stats NO : <b>" + roomstatus.RoomStatus_RoomCardList.size()
				+ "</b> Matched with Total Room Tile NO : <b>" + roomstatus.RoomStatus_AllQuickStatsNumber.getText()
				+ "</b>");
		RoomStatusLogger.info("Total All Quick Stats NO : " + roomstatus.RoomStatus_RoomCardList.size()
				+ " Matched with Total Room Tile NO : " + roomstatus.RoomStatus_AllQuickStatsNumber.getText());

		String path = "//room-card/div[(@data-roomclassname='" + RoomName.toLowerCase() + "')][@data-roomname='"
				+ RoomNO + "']";
		WebElement element = driver.findElement(By.xpath(path));
		assertTrue(element.isDisplayed(), "Failed:Room Number Not Matched");
		test_steps.add("Room Number Matched <b>" + RoomNO + "</b>");
		RoomStatusLogger.info("Room Number Matched " + RoomNO);

	}

	public int getRoomCardSize(WebDriver driver) {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		int size = roomstatus.RoomStatus_RoomCardList.size();
		System.out.println("Total Room Card Is: " + size);
		return size;
	}

	public void clickONRoomStatusForToday(WebDriver driver) {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		roomstatus.RoomStatus_Today.click();
	}

	public void changeStatusMultipleRoom(WebDriver driver, ArrayList<String> test_steps, String status,
			int RoomTileSize, ArrayList<String> roomNo) throws InterruptedException {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);

		for (int i = 0; i < RoomTileSize; i++) {
			WebElement ele = driver.findElement(By.xpath("//div[contains(@data-sortorder,'" + roomNo.get(i) + "')]"));
			// Create object 'action' of an Actions class
			Actions action = new Actions(driver);
			// Mouseover on an element
			action.moveToElement(ele).clickAndHold().perform();
			test_steps.add("Mouse Hover  Room Tile " + roomNo.get(i));
			RoomStatusLogger.info("Mouse Hover  Room Tile " + roomNo.get(i));
			Wait.WaitForElement(driver, OR.RoomStatus_RoomTileDropDownButton);
			roomstatus.RoomStatus_RoomTileDropDownButton.get(i).click();
			test_steps.add("Click on Room Tile Drop Down Box " + roomNo.get(i));
			RoomStatusLogger.info("Click on Room Tile Drop Down Box " + roomNo.get(i));
			String path = "//div[contains(@data-sortorder,'" + roomNo.get(i)
					+ "')]//div[@class='grid-content']//div[contains(@class,'dropdown')]//ul//li/a[contains(text(),'"
					+ status + "')]";
			WebElement element = driver.findElement(By.xpath(path));
			Wait.waitUntilPresenceOfElementLocated(path, driver);
			element.click();
			test_steps.add("Select " + "<b>" + status + "</b>" + " Status from Room Tile Drop Down Box ");
			RoomStatusLogger.info("Select " + status + " Status from Room Tile Drop Down Box ");
			verifyTosterMessageSingleRoom(driver, test_steps, roomNo.get(i));
			Wait.wait5Second();

		}
	}

	public List<String> getStatusofSingleRoomClass(WebDriver driver, ArrayList<String> testSteps, String roomName,
			String roomNO) {
		List<String> statusName = new ArrayList<String>();
		String path = "//room-card/div[(@data-roomclassname='" + roomName.toLowerCase() + "')][@data-roomname='"
				+ roomNO + "']";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
		String clickDropDown = "//room-card/div[(@data-roomclassname='" + roomName.toLowerCase()
				+ "')][@data-roomname='" + roomNO
				+ "']//div[@class='grid-content']//div[contains(@class,'dropdown')]/a/i";
		Wait.WaitForElement(driver, clickDropDown);
		WebElement elementDropDownBox = driver.findElement(By.xpath(clickDropDown));
		elementDropDownBox.click();
		String pathStatus = "//room-card/div[(@data-roomclassname='" + roomName.toLowerCase() + "')][@data-roomname='"
				+ roomNO + "']//div[@class='grid-content']//div[contains(@class,'dropdown')]//ul//li/a";
		Wait.waitUntilPresenceOfElementLocated(pathStatus, driver);
		List<WebElement> elementStatus = driver.findElements(By.xpath(pathStatus));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		for (WebElement str : elementStatus) {
			String value = (String) js.executeScript("return  arguments[0].text", str);
			statusName.add(value.trim());

		}

		return statusName;

	}

	public void searchByRoomHashAndChangeRoomStatus(WebDriver driver, String RoomNO, String RoomName, String Status,
			ArrayList<String> test_steps) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		inputSearchAndClickSearchButton(driver, RoomNO, test_steps);
		Wait.explicit_wait_visibilityof_webelement(roomstatus.RoomStatus_RoomCardList.get(0), driver);
		assertTrue(!(roomstatus.RoomStatus_AllQuickStatsNumber.getText().equals("0")), "Failed:Search not work");

		test_steps.add("Search  working.");
		RoomStatusLogger.info("Search  working.");
		assertEquals(roomstatus.RoomStatus_AllQuickStatsNumber.getText(),
				(Integer.toString(roomstatus.RoomStatus_RoomCardList.size())),
				"Failed:to Match Room Tile with Quick Stats Number");
		test_steps.add("Total All Quick Stats NO : <b>" + roomstatus.RoomStatus_RoomCardList.size()
				+ "</b> Matched with Total Room Tile NO : <b>" + roomstatus.RoomStatus_AllQuickStatsNumber.getText()
				+ "</b>");
		RoomStatusLogger.info("Total All Quick Stats NO : " + roomstatus.RoomStatus_RoomCardList.size()
				+ " Matched with Total Room Tile NO : " + roomstatus.RoomStatus_AllQuickStatsNumber.getText());
		String path = "//room-card/div[(@data-roomclassname='" + RoomName.toLowerCase() + "')][@data-roomname='"
				+ RoomNO + "']";
		RoomStatusLogger.info(path);
		WebElement element = driver.findElement(By.xpath(path));
		assertTrue(element.isEnabled(), "Failed:Room Number Not Matched with Status");
		RoomStatusLogger.info("Room Enabled");
		RoomStatusLogger.info("Verified Enable");
		Actions action = new Actions(driver);
		action.moveToElement(element).clickAndHold().perform();
		RoomStatusLogger.info("Click Toggle");
		String ClickDropDown = "//room-card/div[(@data-roomclassname='" + RoomName.toLowerCase()
				+ "')][@data-roomname='" + RoomNO
				+ "']//div[@class='grid-content']//div[contains(@class,'dropdown')]/a/i";
		WebElement elementDropDownBox = driver.findElement(By.xpath(ClickDropDown));
		elementDropDownBox.click();
		RoomStatusLogger.info("Click Drop Down Box");
		String pathStatus = "//div[@class='grid-content']//div[contains(@class,'dropdown')]//ul//li/a[contains(text(),'"
				+ Status + "')]";
		WebElement elementStatus = driver.findElement(By.xpath(pathStatus));
		Wait.wait1Second();
		RoomStatusLogger.info("Waiting");
		Utility.ScrollToElement(elementStatus, driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", elementStatus);
		RoomStatusLogger.info("Select Status");
		// elementStatus.click();
		test_steps.add("Change " + "<b> " + Status + "</b> Status" + "For Room and Room Class <b>" + RoomNO + " - "
				+ RoomName + "</b>");
		RoomStatusLogger.info("Change " + Status + "Status For Room " + RoomNO + " - " + RoomName);
		Utility.ScrollToElement(roomstatus.RoomStatus_Today, driver);
		action.moveToElement(roomstatus.RoomStatus_Today).perform();
		RoomStatusLogger.info("Action Performed");
		Wait.wait5Second();

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyRoomStatusWithSpecificRoomNo> ' Description:
	 * <Verified Room Status Like Out of Order, Clean, Dirty,Inspection and All
	 * for Specific Room No> ' Input parameters: < String Status like Out of
	 * Order, Clean, Dirty, Inspection and All> ' Return value: <array list> '
	 * Created Room No and Room Class By: <Gangotri Sikheria> ' Created On:
	 * <05/04/2020>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ <mm/dd/yyyy>:<Developer name>:
	 * 1.Step modified 2.Step modified
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void verifyRoomStatusWithSpecificRoomNo(WebDriver driver, String RoomNO, String RoomName, String Status,
			ArrayList<String> test_steps) {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		String OOTpath = "//room-card/div[(@data-roomclassname='" + RoomName.toLowerCase() + "')][@data-roomname='"
				+ RoomNO + "']//span[(text()='" + roomstatus.OOOTab.getText().toLowerCase() + "')]";
		if (roomstatus.OOOTab.getText().toLowerCase().trim().equals(Status.toLowerCase().trim())) {
			Wait.WaitForElement(driver, OOTpath);
			WebElement ooTElement = driver.findElement(By.xpath(OOTpath));
			// Verified Out of Order Quick Stats
			assertTrue(
					roomstatus.RoomStatus_OutOfOrderStatSize.getText()
							.equals(Integer.toString(roomstatus.RoomStatus_OORRoomCard.size())),
					"Failed:to Match Room Tile with Quick Stats Out Of Order");
			test_steps.add("Room Class : <b> " + RoomName + "</b> Room No: <b> " + RoomNO + "</b> is Out of Order :");
			RoomStatusLogger.info("Room Class :  " + RoomName + " Room No: <b> " + RoomNO + " is Out of Order :");
			// Verified All Quick Stats
			assertTrue(
					roomstatus.RoomStatus_AllQuickStatsNumber.getText()
							.equals(Integer.toString(roomstatus.RoomStatus_RoomCardList.size())),
					"Failed:to Match Room Tile with Quick Stats Out Of Order");
			test_steps.add("Total All No Is: <b>" + roomstatus.RoomStatus_AllQuickStatsNumber.getText()
					+ "</b> Total Room Tile No Is : <b>" + roomstatus.RoomStatus_RoomCardList.size() + "</b>");
			RoomStatusLogger.info("Total All No Is: " + roomstatus.RoomStatus_AllQuickStatsNumber.getText()
					+ " Total Room Tile No Is : " + roomstatus.RoomStatus_RoomCardList.size());

			// Verified Out of Order is Display or Not
			assertTrue(ooTElement.isDisplayed(), "Failed:to Verify Out of Order");
			test_steps.add("Verified" + "<b> " + Status + "</b> Status For Room and Room Class <b>" + RoomName + " - "
					+ RoomNO + "</b>");
			RoomStatusLogger.info("Verified " + Status + "Status For Room " + RoomNO + " - " + RoomName);

		}

	}

	public void clickRoomStatusTab(WebDriver driver) throws InterruptedException {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		Utility.ScrollToElement(roomstatus.RoomStatusTabOne, driver);
		roomstatus.RoomStatusTabOne.click();
		Wait.WaitForElement(driver, OR.RoomStatus);
		Wait.explicit_wait_visibilityof_webelement(roomstatus.RoomStatusTab, driver);

	}

	public void searchByRoom(WebDriver driver, String Value, ArrayList<String> test_steps) throws InterruptedException {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		inputSearchAndClickSearchButton(driver, Value, test_steps);
		Wait.explicit_wait_visibilityof_webelement(roomstatus.RoomStatus_RoomCardList.get(0), driver);
		assertTrue(!(roomstatus.RoomStatus_AllQuickStatsNumber.getText().equals("0")), "Failed:Search not work");
		test_steps.add("Passed:Search  working.");
		RoomStatusLogger.info("Passed:Search  working.");
		assertEquals(roomstatus.RoomStatus_AllQuickStatsNumber.getText(),
				(Integer.toString(roomstatus.RoomStatus_RoomCardList.size())),
				"Failed:to Match Room Tile with Quick Stats Number");
		test_steps.add("Total All Quick Stats NO : <b>" + roomstatus.RoomStatus_RoomCardList.size()
				+ "</b> Matched with Total Room Tile NO : <b>" + roomstatus.RoomStatus_AllQuickStatsNumber.getText()
				+ "</b>");
		RoomStatusLogger.info("Total All Quick Stats NO : " + roomstatus.RoomStatus_RoomCardList.size()
				+ " Matched with Total Room Tile NO : " + roomstatus.RoomStatus_AllQuickStatsNumber.getText());

		WebElement element = driver
				.findElement(By.xpath("//room-card/div[contains(@data-roomclassname,'" + Value.toLowerCase() + "')]"));
		assertTrue(element.isDisplayed(), "Rooms not available");
		test_steps.add("Verified Rooms: <b>" + Value + " </b> ");
		RoomStatusLogger.info("  Verified Rooms: " + Value);

	}

	public void roomTileVerifyColor(WebDriver driver, String RoomStatus, ArrayList<String> test_steps) {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		String ColorName = null, backgroundColor = null;
		if (RoomStatus.equals("Clean") || RoomStatus.equalsIgnoreCase("clean")) {

			ColorName = roomstatus.RoomStatus_CleanStatSize.getCssValue("color");
			RoomStatusLogger.info("Get Color :" + ColorName);
			backgroundColor = verifyRoomColor(roomstatus.RoomStatus_CleanRoomCardS, ColorName);

		} else if (RoomStatus.equals("Dirty") || RoomStatus.equalsIgnoreCase("dirty")) {
			ColorName = roomstatus.RoomStatus_DirtyStatSize.getCssValue("color");
			RoomStatusLogger.info("Get Color :" + ColorName);
			backgroundColor = verifyRoomColor(roomstatus.RoomStatus_DirtyStatTotalRoomCardStatus, ColorName);
		} else if (RoomStatus.equals("Inspection") || RoomStatus.equalsIgnoreCase("inspection")) {
			ColorName = roomstatus.RoomStatus_InspectionStatSize.getCssValue("color");
			RoomStatusLogger.info("Get Color :" + ColorName);
			backgroundColor = verifyRoomColor(roomstatus.RoomStatus_InspectionRoomCardS, ColorName);
		} else if (RoomStatus.equals("Out of Order") || RoomStatus.equalsIgnoreCase("out of order")) {
			ColorName = roomstatus.RoomStatus_OutOfOrderStatSize.getCssValue("color");
			RoomStatusLogger.info("Get Color :" + ColorName);
			backgroundColor = verifyRoomColor(roomstatus.RoomStatus_OORRoomCardS, ColorName);
		}

		test_steps.add(RoomStatus + " Quick Status Color " + "<b>" + ColorName + "</b>" + "Matched With"
				+ " Room Tiles BackGround Color " + "<b>" + backgroundColor + "</b>");
		RoomStatusLogger.info(RoomStatus + " Quick Status Color " + "<b>" + ColorName + "</b>" + "Matched With"
				+ " Room Tiles BackGround Color " + "<b>" + backgroundColor + "</b>");

	}

	public void clickRoomTileRadioButton(WebDriver driver, ArrayList<String> test_steps, int roomTileSize) {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		for (int i = 0; i < roomTileSize; i++) {
			Actions action = new Actions(driver);
			// Mouseover on an element
			action.moveToElement(roomstatus.RoomStatus_RoomTile_RadioButtons.get(i)).perform();
			assertTrue(roomstatus.RoomStatus_RoomTile_RadioButtons.get(i).isEnabled(),
					"Failed: Radio Button is not Displayed");
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", roomstatus.RoomStatus_RoomTile_RadioButtons.get(i));
			test_steps.add("Click on All Room Tile Radio Button " + i);
			RoomStatusLogger.info("Click on Room Tile Radio Button " + i);
		}

	}

	public String verifyTosterMessageSingleRoom(WebDriver driver, ArrayList<String> test_steps,
			String itemToBeUpdated) {
		String message = null;
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		String messageText = itemToBeUpdated + " has been updated.";
		RoomStatusLogger.info(messageText);
		RoomStatusLogger.info(roomstatus.tosterMsg.getText());
		assertEquals(roomstatus.tosterMsg.getText().toLowerCase().trim(), (messageText.toLowerCase().trim()),
				"Failed: To verify Message task to be updated");
		test_steps.add("Verified : <b>" + messageText + "</b>");
		RoomStatusLogger.info("Verified :" + messageText);
		return message;
	}

	public String verifyTosterMessageBulkUpdate(WebDriver driver, ArrayList<String> test_steps,
			String itemToBeUpdated) {
		String message = null;
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		String messageText = itemToBeUpdated + " Room(s) have been updated.";
		RoomStatusLogger.info(messageText);
		RoomStatusLogger.info(roomstatus.tosterMsg.getText());
		assertEquals(roomstatus.tosterMsg.getText().toLowerCase().trim(), (messageText.toLowerCase().trim()),
				"Failed: To verify Message task to be updated");
		test_steps.add("Verified : <b>" + messageText + "</b>");
		RoomStatusLogger.info("Verified :" + messageText);
		return message;
	}

	public void searchByZone(WebDriver driver, String Value, ArrayList<String> test_steps) throws InterruptedException {

		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		inputSearchAndClickSearchButton(driver, Value, test_steps);
		Wait.explicit_wait_visibilityof_webelement(roomstatus.RoomStatus_RoomCardList.get(0), driver);
		assertTrue(!(roomstatus.RoomStatus_AllQuickStatsNumber.getText().equals("0")), "Failed:Search not work");
		test_steps.add("Search  working.");
		RoomStatusLogger.info("Search  working.");

		assertEquals(roomstatus.RoomStatus_AllQuickStatsNumber.getText(),
				(Integer.toString(roomstatus.RoomStatus_RoomCardList.size())),
				"Failed:to Match Room Tile with Quick Stats Number");
		test_steps.add("Total All Quick Stats NO : <b>" + roomstatus.RoomStatus_RoomCardList.size()

				+ "</b> Matched with Total Room Tile NO : <b>" + roomstatus.RoomStatus_AllQuickStatsNumber.getText()
				+ "</b>");
		RoomStatusLogger.info("Total All Quick Stats NO : " + roomstatus.RoomStatus_RoomCardList.size()
				+ " Matched with Total Room Tile NO : " + roomstatus.RoomStatus_AllQuickStatsNumber.getText());

		WebElement element = driver
				.findElement(By.xpath("//room-card/div[contains(@data-zone,'" + Value.toLowerCase() + "')]"));
		assertTrue(element.isDisplayed(), "Rooms not available as per search zone");
		test_steps.add("Verified Zone : <b>" + Value + " </b>");
		RoomStatusLogger.info(" Verified Zone : " + Value);
	}

	public void searchByVacantOccupied(WebDriver driver, String Value, ArrayList<String> test_steps)
			throws InterruptedException {

		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		inputSearchAndClickSearchButton(driver, Value, test_steps);
		Wait.explicit_wait_visibilityof_webelement(roomstatus.RoomStatus_RoomCardList.get(0), driver);
		assertTrue(!(roomstatus.RoomStatus_AllQuickStatsNumber.getText().equals("0")), "Failed:Search not work");
		test_steps.add("Search  working.");
		RoomStatusLogger.info("Search  working.");

		assertEquals(roomstatus.RoomStatus_AllQuickStatsNumber.getText(),
				(Integer.toString(roomstatus.RoomStatus_RoomCardList.size())),
				"Failed:to Match Room Tile with Quick Stats Number");
		test_steps.add("Total All Quick Stats NO : <b>" + roomstatus.RoomStatus_RoomCardList.size()

				+ " </b>Matched with Total Room Tile NO : <b>" + roomstatus.RoomStatus_AllQuickStatsNumber.getText()
				+ "</b>");
		RoomStatusLogger.info("Total All Quick Stats NO : " + roomstatus.RoomStatus_RoomCardList.size()
				+ " Matched with Total Room Tile NO : " + roomstatus.RoomStatus_AllQuickStatsNumber.getText());

		assertEquals(roomstatus.RoomStatus_Vacant.getText().toString().toLowerCase(), Value.toLowerCase(),
				"Faild : To Search By " + Value);
		test_steps.add(" Searchd by <b>" + Value + " </b>: Verified <b>" + Value + "</b> Rooms.");
		RoomStatusLogger.info("Searchd by " + Value + " : Verified " + Value + " Rooms.");
	}

	public boolean verifiedByArrivalDue(WebDriver driver, String Value, ArrayList<String> test_steps)
			throws InterruptedException {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		inputSearchAndClickSearchButton(driver, Value, test_steps);
		String getNumberofAllQuickStats = roomstatus.RoomStatus_AllQuickStatsNumber.getText();
		RoomStatusLogger.info(getNumberofAllQuickStats);
		boolean isExist = !getNumberofAllQuickStats.equals("0");

		if (isExist) {
			test_steps.add("<b>****Verify Arrival Due****</b>");
			Wait.explicit_wait_visibilityof_webelement(roomstatus.RoomStatus_RoomCardList.get(0), driver);
			assertTrue(!(roomstatus.RoomStatus_AllQuickStatsNumber.getText().equals("0")), "Failed:Search not work");
			test_steps.add("Search working.");
			RoomStatusLogger.info("Search working.");

			assertEquals(roomstatus.RoomStatus_AllQuickStatsNumber.getText(),
					(Integer.toString(roomstatus.RoomStatus_RoomCardList.size())),
					"Failed:to Match Room Tile with Quick Stats Number");
			test_steps.add("Total All Quick Stats NO : " + roomstatus.RoomStatus_RoomCardList.size()
					+ " Matched with Total Room Tile NO : " + roomstatus.RoomStatus_AllQuickStatsNumber.getText());
			RoomStatusLogger.info("Total All Quick Stats NO : " + roomstatus.RoomStatus_RoomCardList.size()
					+ " Matched with Total Room Tile NO : " + roomstatus.RoomStatus_AllQuickStatsNumber.getText());

			assertEquals(roomstatus.RoomStatus_ArrivalDue.getText().toString().toLowerCase(), Value.toLowerCase(),
					"Rooms are not Vacant");
			test_steps.add("Searchd by <b>" + Value + "</b> : Verified <b>" + Value + "</b> Rooms.");
			RoomStatusLogger.info("Searchd by " + Value + " : Verified " + Value + " Rooms.");
		}
		return isExist;

	}

	public void searchByArrivalDue(WebDriver driver, String Value, ArrayList<String> test_steps)
			throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		Wait.explicit_wait_visibilityof_webelement(roomstatus.RoomStatus_Today, driver);
		inputSearchAndClickSearchButton(driver, Value, test_steps);
		Wait.explicit_wait_visibilityof_webelement(roomstatus.RoomStatus_RoomCardList.get(0), driver);
		assertTrue(!(roomstatus.RoomStatus_AllQuickStatsNumber.getText().equals("0")), "Failed:Search not work");

		test_steps.add("Search working.");
		RoomStatusLogger.info("Search working.");

		assertEquals(roomstatus.RoomStatus_AllQuickStatsNumber.getText(),
				(Integer.toString(roomstatus.RoomStatus_RoomCardList.size())),
				"Failed:to Match Room Tile with Quick Stats Number");
		test_steps.add("Total All Quick Stats NO : <b>" + roomstatus.RoomStatus_RoomCardList.size()
				+ "</b> Matched with Total Room Tile NO : <b>" + roomstatus.RoomStatus_AllQuickStatsNumber.getText()
				+ "</b>");
		RoomStatusLogger.info("Total All Quick Stats NO : " + roomstatus.RoomStatus_RoomCardList.size()
				+ " Matched with Total Room Tile NO : " + roomstatus.RoomStatus_AllQuickStatsNumber.getText());

		assertEquals(roomstatus.RoomStatus_ArrivalDue.getText().toString().toLowerCase(), Value.toLowerCase(),
				"Failed : to Search Arrival Due");
		String dataArrivalDueValue = roomstatus.RoomStatus_RoomCardList.get(0).getAttribute("data-arrivaldue")
				.toString();
		assertTrue(dataArrivalDueValue.equals("0"), "Failed to Search Arrival Due Attribute Value");
		test_steps.add(" Searchd by <b>" + Value + " </b>: Verified <b>" + Value + "</b> Rooms.");
		RoomStatusLogger.info(" Searchd by " + Value + " : Verified " + Value + " Rooms.");

	}

	public boolean verifyByOccupied(WebDriver driver, String Value, ArrayList<String> test_steps)
			throws InterruptedException {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		inputSearchAndClickSearchButton(driver, Value, test_steps);
		String getNumberofAllQuickStats = roomstatus.RoomStatus_AllQuickStatsNumber.getText();
		RoomStatusLogger.info(getNumberofAllQuickStats);
		boolean isExist = !getNumberofAllQuickStats.equals("0");
		if (isExist) {
			test_steps.add("<b>****Verify Occupied****</b>");
			Wait.explicit_wait_visibilityof_webelement(roomstatus.RoomStatus_RoomCardList.get(0), driver);
			assertTrue(!(roomstatus.RoomStatus_AllQuickStatsNumber.getText().equals("0")), "Failed:Search not work");
			test_steps.add("Search  working.");
			RoomStatusLogger.info("Search  working.");
			assertEquals(roomstatus.RoomStatus_AllQuickStatsNumber.getText(),
					(Integer.toString(roomstatus.RoomStatus_RoomCardList.size())),
					"Failed:to Match Room Tile with Quick Stats Number");
			test_steps.add("Total All Quick Stats NO : " + roomstatus.RoomStatus_RoomCardList.size()
					+ " Matched with Total Room Tile NO : " + roomstatus.RoomStatus_AllQuickStatsNumber.getText());
			RoomStatusLogger.info("Total All Quick Stats NO : " + roomstatus.RoomStatus_RoomCardList.size()
					+ " Matched with Total Room Tile NO : " + roomstatus.RoomStatus_AllQuickStatsNumber.getText());
			assertEquals(roomstatus.RoomStatus_Vacant.getText().toString().toLowerCase(), Value.toLowerCase(),
					"Rooms are not Vacant");
			test_steps.add(" Searchd by <b>" + Value + " :</b> Verified <b>" + Value + "</b> Rooms.");
			RoomStatusLogger.info(" Searchd by " + Value + " :Verified " + Value + " Rooms.");
		}
		return isExist;
	}

	public String clickQuickStatsAndVerifyColor(WebDriver driver, String RoomStatus, ArrayList<String> test_steps)
			throws InterruptedException {
		String Statscount = null;
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		if (RoomStatus.equals("Clean") || RoomStatus.equalsIgnoreCase("clean")) {

			roomstatus.VerifyRoomStats_Clean.click();
			// Wait.wait5Second();
			Wait.waitforPageLoad(5, driver);
			test_steps.add("Click on Clean Quick Stats");
			RoomStatusLogger.info("Click on Clean Quick Stats");
			Statscount = roomstatus.RoomStatus_CleanStatSize.getText();
			/*
			 * String one =
			 * roomstatus.RoomStatus_CleanStatSize.getCssValue("color");
			 * RoomStatusLogger.info("Get Color :" + one);
			 * VerifyRoomColor(roomstatus.RoomStatus_CleanRoomCardS, one);
			 * test_steps.add("Verified Color");
			 * RoomStatusLogger.info("Verified Color");
			 */

		} else if (RoomStatus.equals("Dirty") || RoomStatus.equalsIgnoreCase("dirty")) {

			roomstatus.VerifyRoomStats_Dirty.click();
			// Wait.wait5Second();
			Wait.waitforPageLoad(5, driver);
			test_steps.add("Click on Dirty Quick Stats");
			RoomStatusLogger.info("Click on Dirty Quick Stats");
			Statscount = roomstatus.RoomStatus_DirtyStatSize.getText();
			String one = roomstatus.RoomStatus_DirtyStatSize.getCssValue("color");
			RoomStatusLogger.info("Get Color :" + one);
			verifyRoomColor(roomstatus.RoomStatus_DirtyStatTotalRoomCardStatus, one);
			test_steps.add("Verified Color<b> " + one + "</b>");
			RoomStatusLogger.info("Verified Color " + one);
		}

		else if (RoomStatus.equals("Inspection") || RoomStatus.equalsIgnoreCase("inspection")) {

			roomstatus.VerifyRoomStats_Inspection.click();
			// Wait.wait5Second();
			Wait.waitforPageLoad(5, driver);
			test_steps.add("Click on Inspection Quick Stats");
			RoomStatusLogger.info("Click on Inspection Quick Stats");
			Statscount = roomstatus.RoomStatus_InspectionStatSize.getText();
			String one = roomstatus.RoomStatus_InspectionStatSize.getCssValue("color");
			RoomStatusLogger.info("Get Color :" + one);
			verifyRoomColor(roomstatus.RoomStatus_InspectionRoomCardS, one);
			test_steps.add("Verified Color<b> " + one + "</b>");
			RoomStatusLogger.info("Verified Color " + one);
		} else if (RoomStatus.equals("Out of Order") || RoomStatus.equalsIgnoreCase("out of order")) {
			roomstatus.VerifyRoomStats_OutofOrder.click();
			// Wait.wait5Second();
			Wait.waitforPageLoad(5, driver);
			test_steps.add("Click on Out of Order Quick Stats");
			RoomStatusLogger.info("Click on Out of Order Quick Stats");
			Statscount = roomstatus.RoomStatus_OutOfOrderStatSize.getText();
			/*
			 * String one =
			 * roomstatus.RoomStatus_OutOfOrderStatSize.getCssValue("color");
			 * RoomStatusLogger.info("Get Color :" + one);
			 * VerifyRoomColor(roomstatus.RoomStatus_OORRoomCardS, one);
			 * test_steps.add("Verified Color");
			 * RoomStatusLogger.info("Verified Color");
			 */
		}
		return Statscount;
	}

	public void verifyTotalRoomCard(WebDriver driver, ArrayList<String> test_steps, String RoomStatus,
			List<WebElement> RoomCardsNo, WebElement QuickStatsNo) throws InterruptedException {
		// TODO Auto-generated method stub
		int Actual = RoomCardsNo.size();
		int Expected = Integer.parseInt(QuickStatsNo.getText());
		assertEquals(Actual, Expected, "Total room card  is not matched with " + RoomStatus + "quick stats number ");
		test_steps.add("Total Room Card : " + RoomCardsNo.size() + " Matched with " + RoomStatus
				+ " Quick stats Number: " + QuickStatsNo.getText());
		RoomStatusLogger.info("Total Room Card : " + RoomCardsNo.size() + " Matched with " + RoomStatus
				+ " Quick stats Number: " + QuickStatsNo.getText());
		test_steps.add("Total " + QuickStatsNo.getText() + " Room Card Enabled");
		RoomStatusLogger.info("Total " + QuickStatsNo.getText() + " Room Card Enabled");
	}

	public void verifyAllStat(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_GuestServices.RoomStatus_AllTab), driver, 5);
		roomstatus.RoomStatus_AllTab.click();
		Wait.waitforPageLoad(5, driver);
		// Wait.wait25Second();
		test_steps.add("Click on All Quick stats");
		RoomStatusLogger.info("Click on All Quick stats");
		int DirtyNumber = Integer.parseInt(roomstatus.RoomStatus_DirtyStatSize.getText());
		RoomStatusLogger.info("Total Dirty Rooms " + DirtyNumber);
		int InspectiomnNumber = Integer.parseInt(roomstatus.RoomStatus_InspectionStatSize.getText());
		RoomStatusLogger.info("Total Inspection Rooms " + InspectiomnNumber);
		int CleanNumber = Integer.parseInt(roomstatus.RoomStatus_CleanStatSize.getText());
		RoomStatusLogger.info("Total Clean Rooms " + CleanNumber);
		int OutOfOrderNumber = Integer.parseInt(roomstatus.RoomStatus_OutOfOrderStatSize.getText());
		RoomStatusLogger.info("Total Out Of Order Rooms " + OutOfOrderNumber);
		int SumofAll = DirtyNumber + InspectiomnNumber + CleanNumber + OutOfOrderNumber;
		RoomStatusLogger.info("Sum of  Quick Stats " + SumofAll);
		int Actual = Integer.parseInt(roomstatus.RoomStatus_AllQuickStatsNumber.getText());
		RoomStatusLogger.info("Actual Quick Stat Number " + Actual);
		assertEquals(Actual, SumofAll, "Sum of Dirty + Inspection + Clean+ Out of Order stats are: " + SumofAll
				+ " should not Equal to All quick stat number " + Actual);
		test_steps.add("Sum of Dirty + Inspection + Clean + Out of Order stats are " + SumofAll
				+ " Equal to All quick stats  " + Actual);
		RoomStatusLogger.info("Sum of Dirty + Inspection + Clean+Out of Order stats number " + SumofAll
				+ " Equal to All quick stat number " + Actual);
		test_steps.add("Total " + roomstatus.RoomStatus_AllQuickStats.getText() + " Room Card Enabled");
		RoomStatusLogger.info("Total " + roomstatus.RoomStatus_AllQuickStats.getText() + " Room Card Enabled");
	}

	public boolean getQuickStatSize(WebDriver driver, String status) {
		boolean isExist = false;
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		if (status.equals("Inspection") || status.equalsIgnoreCase("inspection")) {
			String getNumberofAllQuickStats = roomstatus.RoomStatus_InspectionStatSize.getText();
			isExist = !getNumberofAllQuickStats.equals("0");
		} else if (status.equals("Clean") || status.equalsIgnoreCase("clean")) {
			String getNumberofAllQuickStats = roomstatus.RoomStatus_CleanStatSize.getText();
			isExist = !getNumberofAllQuickStats.equals("0");

		} else if (status.equals("Out of Order") || status.equalsIgnoreCase("out of order")) {
			String getNumberofAllQuickStats = roomstatus.RoomStatus_OutOfOrderStatSize.getText();
			isExist = !getNumberofAllQuickStats.equals("0");

		}

		return isExist;
	}

	private String verifyRoomColor(List<WebElement> element, String Value) {
		String backgroundColor = null;
		for (WebElement e : element) {

			backgroundColor = e.getCssValue("background-color");
			assertEquals(backgroundColor, (Value), "Failed: Specified  Color is not correct");

		}
		RoomStatusLogger.info("Verifid Color: " + backgroundColor);

		return backgroundColor;
	}

	public void searchByRoomHashVerifyRoomStatus(WebDriver driver, String Value, String RoomName, String DirtyStatus,
			String CleanStatus, String VacantStatus, String OccupiedStatus, ArrayList<String> test_steps)
			throws InterruptedException {
		// TODO Auto-generated method stub
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		inputSearchAndClickSearchButton(driver, Value, test_steps);
		Wait.explicit_wait_visibilityof_webelement(roomstatus.RoomStatus_RoomCardList.get(0), driver);
		assertTrue(!(roomstatus.RoomStatus_AllQuickStatsNumber.getText().equals("0")), "Failed:Search not work");
		test_steps.add("Search  working.");
		RoomStatusLogger.info("Search  working.");
		assertEquals(roomstatus.RoomStatus_AllQuickStatsNumber.getText(),
				(Integer.toString(roomstatus.RoomStatus_RoomCardList.size())),
				"Failed:to Match Room Tile with Quick Stats Number");
		test_steps.add("Total All Quick Stats NO : <b>" + roomstatus.RoomStatus_RoomCardList.size()
				+ "</b> Matched with Total Room Tile NO : <b>" + roomstatus.RoomStatus_AllQuickStatsNumber.getText()
				+ "</b>");
		RoomStatusLogger.info("Total All Quick Stats NO : " + roomstatus.RoomStatus_RoomCardList.size()
				+ " Matched with Total Room Tile NO : " + roomstatus.RoomStatus_AllQuickStatsNumber.getText());
		String path = "//room-card/div[(@data-roomclassname='" + RoomName.toLowerCase() + "')][@data-roomname='" + Value
				+ "']/..//div[contains(@class,'dropdown')]/a[contains(text(),'" + DirtyStatus
				+ "')]|//room-card/div[(@data-roomclassname='" + RoomName.toLowerCase() + "')][@data-roomname='" + Value
				+ "']/..//div[contains(@class,'dropdown')]/a[contains(text(),'" + CleanStatus + "')]";
		System.out.println(path);
		WebElement element = driver.findElement(By.xpath(path));
		assertTrue(element.isEnabled(), "Failed:Room Number Not Matched with Status");
		RoomStatusLogger.info("Enable");
		Actions action = new Actions(driver);
		Action mouseOverHome = action.moveToElement(element).build();
		mouseOverHome.perform();
		RoomStatusLogger.info("Perform Mouse Over");
		String Status = element.getText().toLowerCase();
		System.out.println("Status Is: " + Status);
		if (element.getText().toLowerCase().trim().equals(DirtyStatus.toLowerCase().trim())) {
			test_steps.add(
					"Room Status Of Room <b> " + RoomName + " - " + Value + "</b> is : <b>" + DirtyStatus + " </b>");
			RoomStatusLogger.info("Room Status Of Room " + RoomName + " - " + Value + " is : " + DirtyStatus);

		} else if (element.getText().toLowerCase().trim().equals(CleanStatus.toLowerCase().trim())) {
			test_steps.add(
					"Room Status Of Room <b>" + RoomName + " - " + Value + "</b> is : <b>" + CleanStatus + " </b>");
			RoomStatusLogger.info("Room Status Of Room" + RoomName + " - " + Value + " is : " + CleanStatus);
		}

		String status = "//room-card/div[(@data-roomclassname='" + RoomName.toLowerCase() + "')][@data-roomname='"
				+ Value + "']/..//div/span[contains(@class,'status')][contains(text(),'"
				+ VacantStatus.toLowerCase().trim() + "')]|//room-card/div[(@data-roomclassname='"
				+ RoomName.toLowerCase() + "')][@data-roomname='" + Value
				+ "']/..//div/span[contains(@class,'status')][contains(text(),'" + OccupiedStatus.toLowerCase().trim()
				+ "')]";
		System.out.println(status);
		WebElement elementStatus = driver.findElement(By.xpath(status));
		assertTrue(elementStatus.isDisplayed(), "Failed:Room Number Not Matched with RoomStatus");
		if (elementStatus.getText().toLowerCase().trim().equals(VacantStatus.toLowerCase().trim())) {
			VacantNo = 1;
			test_steps.add("Room  <b>" + RoomName + " - " + Value + "</b> is : <b>" + VacantStatus + " </b>");
			RoomStatusLogger.info("Room " + RoomName + " - " + Value + " is : " + VacantStatus);

		} else if (elementStatus.getText().toLowerCase().trim().equals(OccupiedStatus.toLowerCase().trim())) {
			OccupiedNo = 1;
			test_steps.add("Room  <b>" + RoomName + " - " + Value + "</b> is : <b>" + OccupiedStatus + " </b>");
			RoomStatusLogger.info("Room " + RoomName + " - " + Value + " is : " + OccupiedStatus);
		}

	}

	public void VerifyLoading(WebDriver driver) throws InterruptedException {
		WebElementsRoomStatus elementsRoomStatus = new WebElementsRoomStatus(driver);
		try {
			int count = 0;
			while (count <= 40) {
				if (!elementsRoomStatus.Loading.isDisplayed()) {
					break;
				}
				count++;
				Wait.wait1Second();
				System.out.println("in a loop: " + count);
			}
			// Wait.waitForElementToBeGone(driver,elementsRoomStatus.Loading,
			// 10);
		} catch (Exception e) {
			System.out.println("in catch");
			// Wait.wait2Second();
		}
	}

	public void SearchRoomZoneOccupancyOthers(WebDriver driver, String Input) throws InterruptedException {
		WebElementsRoomStatus elementsRoomStatus = new WebElementsRoomStatus(driver);
		VerifyLoading(driver);
		Wait.WaitForElement(driver, RoomStatusPage.InputSearch);
		Wait.explicit_wait_visibilityof_webelement(elementsRoomStatus.InputSearch, driver);
		Wait.explicit_wait_elementToBeClickable(elementsRoomStatus.InputSearch, driver);
		elementsRoomStatus.InputSearch.sendKeys(Input);
		elementsRoomStatus.ButtonSearch.click();
		VerifyLoading(driver);

	}

	public ArrayList<String> VerifyTask(WebDriver driver, String RoomClassName, String RoomNumber, int ExpectedTask,
			boolean isClick) {

		ArrayList<String> test_step = new ArrayList<>();
		RoomClassName = RoomClassName.toLowerCase();
		System.out.println(RoomClassName);
		String RoomClassSlotPath = "//div[@data-roomname='" + RoomNumber + "' and @data-roomclassname='" + RoomClassName
				+ "']";
		Wait.WaitForElement(driver, RoomClassSlotPath);
		WebElement elementRoomSlot = driver.findElement(By.xpath(RoomClassSlotPath));
		Wait.explicit_wait_visibilityof_webelement_120(elementRoomSlot, driver);
		Wait.explicit_wait_elementToBeClickable(elementRoomSlot, driver);
		Actions actions = new Actions(driver);
		actions.moveToElement(elementRoomSlot).perform();

		String TaskPath = "(" + RoomClassSlotPath + "//a[contains(@class,'btn-link-border')])[2]";
		System.out.println(TaskPath);
		Wait.WaitForElement(driver, TaskPath);
		Wait.waitForElementToBeVisibile(By.xpath(TaskPath), driver);
		WebElement elementTask = driver.findElement(By.xpath(TaskPath));
		Wait.waitForElementToBeClickableUsingClass(By.xpath(TaskPath), driver);
		Wait.explicit_wait_visibilityof_webelement(elementTask, driver);
		Wait.explicit_wait_elementToBeClickable(elementTask, driver);
		String getTotalTask = elementTask.getText();
		System.out.println(getTotalTask);
		String strSplit[] = getTotalTask.split(" ");
		System.out.println(strSplit.length);
		getTotalTask = strSplit[0];
		int convertTaskintoInt = Integer.parseInt(getTotalTask);
		System.out.println("convertTaskintoInt: " + convertTaskintoInt);
		test_step.add("Expected task: " + ExpectedTask);
		test_step.add("Found: " + convertTaskintoInt);
		assertEquals(convertTaskintoInt, ExpectedTask, "Failed: Task is mismatching!");
		if (isClick) {
			elementTask.click();
			test_step.add("Click on task");
		}
		test_step.add("Verified task against room class " + RoomClassName + " and room number " + RoomNumber);
		return test_step;
	}

	public String getTaskImage(WebDriver driver, int index) throws InterruptedException {
		WebElementsRoomStatus elementsRoomStatus = new WebElementsRoomStatus(driver);
		Wait.WaitForElement(driver, RoomStatusPage.GetCategoryImage);
		Wait.explicit_wait_visibilityof_webelement(elementsRoomStatus.GetCategoryImage.get(index), driver);
		Wait.explicit_wait_elementToBeClickable(elementsRoomStatus.GetCategoryImage.get(index), driver);
		String[] strSplit = elementsRoomStatus.GetCategoryImage.get(index).getAttribute("class").split("-");

		String getImage = strSplit[strSplit.length - 1];
		System.out.println(strSplit[strSplit.length - 1]);

		return getImage;

	}

	public String getTaskName(WebDriver driver, int index) throws InterruptedException {
		WebElementsRoomStatus elementsRoomStatus = new WebElementsRoomStatus(driver);
		return elementsRoomStatus.GetCategoryName.get(index).getText();

	}

	public String getTaskDueOnAssignedAndStatus(WebDriver driver, int index) throws InterruptedException {
		WebElementsRoomStatus elementsRoomStatus = new WebElementsRoomStatus(driver);
		return elementsRoomStatus.GetTaskDueOnAssignedAndStatus.get(index).getText();

	}

	public String getRoomStatus(WebDriver driver) throws InterruptedException {
		WebElementsRoomStatus elementsRoomStatus = new WebElementsRoomStatus(driver);
		return elementsRoomStatus.RoomStatus.getText();

	}

	public void ButtonClosPopup(WebDriver driver) throws InterruptedException {
		WebElementsRoomStatus elementsRoomStatus = new WebElementsRoomStatus(driver);
		elementsRoomStatus.ButtonClosPopup.click();
	}

	public String GetRoomStatus(WebDriver driver, String ColorName) throws InterruptedException {

		String Pth = "//li[contains(@class,'" + ColorName + "')]//span[contains(@class,'gs-statsValue')]";
		System.out.println(Pth);
		Wait.WaitForElement(driver, Pth);
		WebElement element = driver.findElement(By.xpath(Pth));
		Wait.explicit_wait_visibilityof_webelement(element, driver);
		Wait.explicit_wait_elementToBeClickable(element, driver);
		return element.getText();

	}

	public ArrayList<String> SelectReports(WebDriver driver, String ReportsType) throws InterruptedException {
		WebElements_RoomStatus roomstatus = new WebElements_RoomStatus(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.explicit_wait_xpath(OR.Report_RoomStatus, driver);
		roomstatus.Report.click();
		RoomStatusLogger.info("Click on reports");
		testSteps.add("Click on reports");

		String reportTypePath = "(//ul[contains(@class,'dropdown-menu')]//a[contains(text(),'" + ReportsType
				+ "')])[1]";
		Wait.WaitForElement(driver, reportTypePath);
		WebElement element = driver.findElement(By.xpath(reportTypePath));
		Wait.explicit_wait_elementToBeClickable(element, driver);
		element.click();
		testSteps.add("Selet reports type as " + ReportsType);

		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		VerifyLoading(driver);
		RoomStatusLogger.info("Switch to report tab");
		Wait.WaitForElement(driver, OR.ReportPage);
		Wait.explicit_wait_visibilityof_webelement(roomstatus.ReportPage, driver);
		Wait.explicit_wait_elementToBeClickable(roomstatus.ReportPage, driver);

		assertTrue(roomstatus.ReportPage.isDisplayed(), "Failed: Room Status Report Page");
		RoomStatusLogger.info("Report: Room Status ");
		testSteps.add("Verified room status page is displaying");
		VerifyLoading(driver);
		return testSteps;
	}

	public String GetRoomStatusInSecondWindows(WebDriver driver, int index) throws InterruptedException {

		WebElementsRoomStatus elementsRoomStatus = new WebElementsRoomStatus(driver);
		return elementsRoomStatus.RoomStatusInSecondWindow.get(index).getText();

	}

	public void CloseSecondWindow(WebDriver driver) throws InterruptedException {
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.close();
		RoomStatusLogger.info("Close Tab");
		driver.switchTo().window(tabs2.get(0));
		Wait.WaitForElement(driver, OR.RoomStatusPage);
		Wait.explicit_wait_xpath(OR.RoomStatusPage, driver);
		RoomStatusLogger.info("Room Status Page");

	}

	public String GetRoomStatusInSecondWindow(WebDriver driver, String RoomClassName, int Index)
			throws InterruptedException {

		String path = "(//td[text()='" + RoomClassName + "']//..//td)[" + Index + "]";
		System.out.println(path);
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);
		WebElement element = driver.findElement(By.xpath(path));
		return element.getText();

	}

	public String GetRoomStatusFor(WebDriver driver) throws InterruptedException {

		WebElementsRoomStatus elementsRoomStatus = new WebElementsRoomStatus(driver);
		return elementsRoomStatus.RoomStatusFor.getText();

	}

	public String getTaskImageInRoomStatus(WebDriver driver, int index) throws InterruptedException {
		WebElementsRoomStatus elementsRoomStatus = new WebElementsRoomStatus(driver);
		Wait.WaitForElement(driver, RoomStatusPage.GetTaskImage);
		Wait.explicit_wait_visibilityof_webelement(elementsRoomStatus.GetTaskImage.get(index), driver);
		Wait.explicit_wait_elementToBeClickable(elementsRoomStatus.GetTaskImage.get(index), driver);
		String[] strSplit = elementsRoomStatus.GetTaskImage.get(index).getAttribute("class").split("-");

		String getImage = strSplit[strSplit.length - 1];
		System.out.println(strSplit[strSplit.length - 1]);

		return getImage;

	}

	public String getTaskNameRoomStatus(WebDriver driver, int index) throws InterruptedException {
		WebElementsRoomStatus elementsRoomStatus = new WebElementsRoomStatus(driver);
		return elementsRoomStatus.GetTaskName.get(index).getText();

	}

	public String TaskDueOnAssignedAndStatusInRioomStatus(WebDriver driver, String TaskName, int index)
			throws InterruptedException {
		String Path = "//span[text()='" + TaskName + "']//..//..//following-sibling::div";
		List<WebElement> element = driver.findElements(By.xpath(Path));
		return element.get(index).getText();

	}

}
